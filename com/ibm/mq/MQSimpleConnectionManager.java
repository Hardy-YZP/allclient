/*     */ package com.ibm.mq;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class MQSimpleConnectionManager
/*     */   extends JmqiObject
/*     */   implements MQConnectionManager
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQSimpleConnectionManager.java";
/*     */   
/*     */   static {
/*  44 */     if (Trace.isOn) {
/*  45 */       Trace.data("com.ibm.mq.MQSimpleConnectionManager", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQSimpleConnectionManager.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   private ManagedConnectionStore mcs = new ManagedConnectionStore();
/*  55 */   private PoolScavenger scavenger = new PoolScavenger();
/*  56 */   private Vector<StoredManagedConnection> ownedSMCs = new Vector<>();
/*     */   
/*     */   private boolean active = false;
/*     */   
/*     */   private volatile int mode;
/*  61 */   private MQPoolServices poolServices = new MQPoolServices();
/*  62 */   private PSAdapter adapter = new PSAdapter();
/*  63 */   private int maxCons = 0;
/*  64 */   private int gestating = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int MODE_AUTO = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int MODE_ACTIVE = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int MODE_INACTIVE = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQSimpleConnectionManager() {
/*  95 */     super(MQSESSION.getJmqiEnv());
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.entry(this, "com.ibm.mq.MQSimpleConnectionManager", "<init>()");
/*     */     }
/*  99 */     this.mode = 0;
/* 100 */     this.poolServices.addMQPoolServicesEventListener(this.adapter);
/*     */     
/* 102 */     if (Trace.isOn) {
/* 103 */       Trace.exit(this, "com.ibm.mq.MQSimpleConnectionManager", "<init>()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object allocateConnection(MQManagedConnectionFactory mcf, MQConnectionRequestInfo cxRequestInfo) throws MQResourceException {
/* 125 */     if (Trace.isOn) {
/* 126 */       Trace.entry(this, "com.ibm.mq.MQSimpleConnectionManager", "allocateConnection(MQManagedConnectionFactory,MQConnectionRequestInfo)", new Object[] { mcf, cxRequestInfo });
/*     */     }
/*     */ 
/*     */     
/* 130 */     String fid = "allocateConnection(MQManagedConnectionFactory,javax.resource.spi.ConnectionRequestInfo)";
/* 131 */     String methodName = "allocateConnection";
/* 132 */     StoredManagedConnection smc = null;
/* 133 */     Object connection = null;
/* 134 */     boolean fromPool = true;
/*     */     
/* 136 */     while (fromPool) {
/* 137 */       smc = this.mcs.chooseOne(mcf, cxRequestInfo);
/* 138 */       if (smc != null && smc.mqManCon != null)
/*     */         
/*     */         try {
/* 141 */           connection = smc.mqManCon.getConnection(cxRequestInfo);
/*     */           
/*     */           break;
/* 144 */         } catch (MQResourceException mqe) {
/* 145 */           if (Trace.isOn) {
/* 146 */             Trace.catchBlock(this, "com.ibm.mq.MQSimpleConnectionManager", "allocateConnection(MQManagedConnectionFactory,MQConnectionRequestInfo)", mqe, 1);
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 154 */           smc.destroy();
/*     */           continue;
/*     */         }  
/* 157 */       fromPool = false;
/*     */     } 
/*     */ 
/*     */     
/* 161 */     if (!fromPool) {
/*     */       
/* 163 */       if (this.maxCons > 0) {
/*     */         
/* 165 */         int toShed = 1 + currentSize() + this.gestating - this.maxCons;
/* 166 */         if (toShed > 0) {
/* 167 */           if (Trace.isOn) {
/* 168 */             Trace.data(this, "allocateConnection(MQManagedConnectionFactory,javax.resource.spi.ConnectionRequestInfo)", "Scavenger asked to lose " + toShed + " connections", "");
/*     */           }
/* 170 */           this.scavenger.destroyNext(toShed);
/*     */         } 
/*     */       } 
/*     */       
/* 174 */       smc = null;
/*     */ 
/*     */       
/* 177 */       boolean createNewSMC = false;
/*     */       
/* 179 */       synchronized (this) {
/* 180 */         if (this.maxCons == 0 || this.maxCons > currentSize() + this.gestating) {
/* 181 */           createNewSMC = true;
/* 182 */           this.gestating++;
/* 183 */           if (Trace.isOn) {
/* 184 */             Trace.data(this, "allocateConnection(MQManagedConnectionFactory,javax.resource.spi.ConnectionRequestInfo)", "New SMC will be created, maxCons: " + this.maxCons + ", currentSize: " + currentSize() + ", gestating: " + this.gestating, "");
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 192 */       if (createNewSMC) {
/*     */         try {
/* 194 */           smc = new StoredManagedConnection(mcf, cxRequestInfo, this.mcs, this.scavenger, this);
/*     */ 
/*     */ 
/*     */           
/* 198 */           synchronized (this) {
/* 199 */             smc.initializePoolActive(this.active);
/*     */           } 
/* 201 */           connection = smc.mqManCon.getConnection(cxRequestInfo);
/*     */         }
/* 203 */         catch (MQResourceException mqe) {
/* 204 */           if (Trace.isOn) {
/* 205 */             Trace.catchBlock(this, "com.ibm.mq.MQSimpleConnectionManager", "allocateConnection(MQManagedConnectionFactory,MQConnectionRequestInfo)", mqe, 2);
/*     */           }
/*     */ 
/*     */           
/* 209 */           if (smc != null) {
/* 210 */             smc.destroy();
/*     */           }
/* 212 */           if (Trace.isOn) {
/* 213 */             Trace.throwing(this, "com.ibm.mq.MQSimpleConnectionManager", "allocateConnection(MQManagedConnectionFactory,MQConnectionRequestInfo)", mqe, 1);
/*     */           }
/*     */           
/* 216 */           throw mqe;
/*     */         } finally {
/*     */           
/* 219 */           if (Trace.isOn) {
/* 220 */             Trace.finallyBlock(this, "com.ibm.mq.MQSimpleConnectionManager", "allocateConnection(MQManagedConnectionFactory,MQConnectionRequestInfo)");
/*     */           }
/*     */ 
/*     */           
/* 224 */           synchronized (this) {
/* 225 */             this.gestating--;
/*     */           } 
/*     */         } 
/*     */       } else {
/* 229 */         MQConnectionAllocationException traceRet1 = new MQConnectionAllocationException(2, 2273, this, MQException.getNLSMsg("MQRESOURCE_ERROR", methodName));
/*     */         
/* 231 */         if (Trace.isOn) {
/* 232 */           Trace.throwing(this, "com.ibm.mq.MQSimpleConnectionManager", "allocateConnection(MQManagedConnectionFactory,MQConnectionRequestInfo)", traceRet1, 2);
/*     */         }
/*     */ 
/*     */         
/* 236 */         throw traceRet1;
/*     */       } 
/*     */     } 
/*     */     
/* 240 */     if (Trace.isOn) {
/* 241 */       Trace.exit(this, "com.ibm.mq.MQSimpleConnectionManager", "allocateConnection(MQManagedConnectionFactory,MQConnectionRequestInfo)", connection);
/*     */     }
/*     */     
/* 244 */     return connection;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object recycleConnection(MQManagedConnectionFactory mcf, MQConnectionRequestInfo cxRequestInfo) {
/* 262 */     if (Trace.isOn) {
/* 263 */       Trace.entry(this, "com.ibm.mq.MQSimpleConnectionManager", "recycleConnection(MQManagedConnectionFactory,MQConnectionRequestInfo)", new Object[] { mcf, cxRequestInfo });
/*     */     }
/*     */ 
/*     */     
/* 267 */     StoredManagedConnection smc = null;
/* 268 */     Object connection = null;
/*     */     
/*     */     while (true) {
/* 271 */       smc = this.mcs.chooseOne(mcf, cxRequestInfo);
/* 272 */       if (smc == null || smc.mqManCon == null) {
/*     */         break;
/*     */       }
/*     */ 
/*     */       
/*     */       try {
/* 278 */         connection = smc.mqManCon.getConnection(cxRequestInfo);
/*     */         
/*     */         break;
/* 281 */       } catch (MQResourceException mqe) {
/* 282 */         if (Trace.isOn) {
/* 283 */           Trace.catchBlock(this, "com.ibm.mq.MQSimpleConnectionManager", "recycleConnection(MQManagedConnectionFactory,MQConnectionRequestInfo)", mqe);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 291 */         smc.destroy();
/*     */       } 
/*     */     } 
/*     */     
/* 295 */     if (Trace.isOn) {
/* 296 */       Trace.exit(this, "com.ibm.mq.MQSimpleConnectionManager", "recycleConnection(MQManagedConnectionFactory,MQConnectionRequestInfo)", connection);
/*     */     }
/*     */     
/* 299 */     return connection;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object createConnection(MQManagedConnectionFactory mcf, MQConnectionRequestInfo cxRequestInfo) throws MQResourceException {
/* 318 */     if (Trace.isOn) {
/* 319 */       Trace.entry(this, "com.ibm.mq.MQSimpleConnectionManager", "createConnection(MQManagedConnectionFactory,MQConnectionRequestInfo)", new Object[] { mcf, cxRequestInfo });
/*     */     }
/*     */ 
/*     */     
/* 323 */     String fid = "createConnection(MQManagedConnectionFactory,javax.resource.spi.ConnectionRequestInfo)";
/* 324 */     String methodName = "createConnection";
/* 325 */     StoredManagedConnection smc = null;
/* 326 */     Object connection = null;
/*     */     
/* 328 */     if (this.maxCons > 0) {
/*     */       
/* 330 */       int toShed = 1 + currentSize() + this.gestating - this.maxCons;
/* 331 */       if (toShed > 0) {
/* 332 */         if (Trace.isOn) {
/* 333 */           Trace.data(this, "createConnection(MQManagedConnectionFactory,javax.resource.spi.ConnectionRequestInfo)", "Scavenger asked to lose " + toShed + " connections", "");
/*     */         }
/* 335 */         this.scavenger.destroyNext(toShed);
/*     */       } 
/*     */     } 
/* 338 */     smc = null;
/*     */     
/* 340 */     boolean createNewSMC = false;
/* 341 */     synchronized (this) {
/* 342 */       if (this.maxCons == 0 || this.maxCons > currentSize() + this.gestating) {
/* 343 */         createNewSMC = true;
/* 344 */         this.gestating++;
/* 345 */         if (Trace.isOn) {
/* 346 */           Trace.data(this, "createConnection(MQManagedConnectionFactory,javax.resource.spi.ConnectionRequestInfo)", "New SMC will be created, maxCons: " + this.maxCons + ", currentSize: " + currentSize() + ", gestating: " + this.gestating, "");
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 353 */     if (createNewSMC) {
/*     */       try {
/* 355 */         smc = new StoredManagedConnection(mcf, cxRequestInfo, this.mcs, this.scavenger, this);
/*     */ 
/*     */ 
/*     */         
/* 359 */         synchronized (this) {
/* 360 */           smc.initializePoolActive(this.active);
/*     */         } 
/* 362 */         connection = smc.mqManCon.getConnection(cxRequestInfo);
/*     */       }
/* 364 */       catch (MQResourceException mqe) {
/* 365 */         if (Trace.isOn) {
/* 366 */           Trace.catchBlock(this, "com.ibm.mq.MQSimpleConnectionManager", "createConnection(MQManagedConnectionFactory,MQConnectionRequestInfo)", mqe);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 371 */         if (smc != null) {
/* 372 */           smc.destroy();
/*     */         }
/* 374 */         if (Trace.isOn) {
/* 375 */           Trace.throwing(this, "com.ibm.mq.MQSimpleConnectionManager", "createConnection(MQManagedConnectionFactory,MQConnectionRequestInfo)", mqe, 1);
/*     */         }
/*     */         
/* 378 */         throw mqe;
/*     */       } finally {
/*     */         
/* 381 */         if (Trace.isOn) {
/* 382 */           Trace.finallyBlock(this, "com.ibm.mq.MQSimpleConnectionManager", "createConnection(MQManagedConnectionFactory,MQConnectionRequestInfo)");
/*     */         }
/*     */ 
/*     */         
/* 386 */         synchronized (this) {
/* 387 */           this.gestating--;
/*     */         } 
/*     */       } 
/*     */     } else {
/* 391 */       MQResourceException traceRet1 = new MQResourceException(2, 2273, this, MQException.getNLSMsg("MQRESOURCE_ERROR", methodName));
/*     */       
/* 393 */       if (Trace.isOn) {
/* 394 */         Trace.throwing(this, "com.ibm.mq.MQSimpleConnectionManager", "createConnection(MQManagedConnectionFactory,MQConnectionRequestInfo)", traceRet1, 2);
/*     */       }
/*     */ 
/*     */       
/* 398 */       throw traceRet1;
/*     */     } 
/*     */     
/* 401 */     if (Trace.isOn) {
/* 402 */       Trace.exit(this, "com.ibm.mq.MQSimpleConnectionManager", "createConnection(MQManagedConnectionFactory,MQConnectionRequestInfo)", connection);
/*     */     }
/*     */     
/* 405 */     return connection;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void setActive(int mode) {
/* 420 */     if (Trace.isOn) {
/* 421 */       Trace.data(this, "com.ibm.mq.MQSimpleConnectionManager", "setActive(int)", "setter", 
/* 422 */           Integer.valueOf(mode));
/*     */     }
/* 424 */     if (mode != 0 && mode != 1 && mode != 2) {
/* 425 */       IllegalArgumentException traceRet1 = new IllegalArgumentException();
/*     */       
/* 427 */       if (Trace.isOn) {
/* 428 */         Trace.throwing(this, "com.ibm.mq.MQSimpleConnectionManager", "setActive(int)", traceRet1);
/*     */       }
/* 430 */       throw traceRet1;
/*     */     } 
/*     */     
/* 433 */     this.mode = mode;
/* 434 */     switch (mode) {
/*     */       case 0:
/* 436 */         if (MQEnvironment.defaultMQCxManager == this && this.poolServices.getTokenCount() > 0) {
/* 437 */           _setActive(true);
/*     */         } else {
/* 439 */           _setActive(false);
/*     */         } 
/* 441 */         this.poolServices.addMQPoolServicesEventListener(this.adapter);
/*     */         break;
/*     */       
/*     */       case 1:
/* 445 */         _setActive(true);
/* 446 */         this.poolServices.removeMQPoolServicesEventListener(this.adapter);
/*     */         break;
/*     */       
/*     */       case 2:
/* 450 */         _setActive(false);
/* 451 */         this.poolServices.removeMQPoolServicesEventListener(this.adapter);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized int getActive() {
/* 468 */     if (Trace.isOn) {
/* 469 */       Trace.data(this, "com.ibm.mq.MQSimpleConnectionManager", "getActive()", "getter", 
/* 470 */           Integer.valueOf(this.mode));
/*     */     }
/* 472 */     return this.mode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTimeout(long timeout) {
/* 483 */     if (Trace.isOn) {
/* 484 */       Trace.data(this, "com.ibm.mq.MQSimpleConnectionManager", "setTimeout(long)", "setter", 
/* 485 */           Long.valueOf(timeout));
/*     */     }
/* 487 */     String fid = "setTimeout(long)";
/* 488 */     if (timeout < 0L) {
/* 489 */       if (Trace.isOn) {
/* 490 */         Trace.data(this, "setTimeout(long)", "setTimeout called with timeout < 0", "");
/*     */       }
/* 492 */       IllegalArgumentException traceRet1 = new IllegalArgumentException();
/*     */       
/* 494 */       if (Trace.isOn) {
/* 495 */         Trace.throwing(this, "com.ibm.mq.MQSimpleConnectionManager", "setTimeout(long)", traceRet1);
/*     */       }
/*     */       
/* 498 */       throw traceRet1;
/*     */     } 
/* 500 */     this.scavenger.setTimeout(timeout);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getTimeout() {
/* 511 */     long traceRet1 = this.scavenger.getTimeout();
/* 512 */     if (Trace.isOn) {
/* 513 */       Trace.data(this, "com.ibm.mq.MQSimpleConnectionManager", "getTimeout()", "getter", 
/* 514 */           Long.valueOf(traceRet1));
/*     */     }
/* 516 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxUnusedConnections(int limit) {
/* 527 */     if (Trace.isOn) {
/* 528 */       Trace.data(this, "com.ibm.mq.MQSimpleConnectionManager", "setMaxUnusedConnections(int)", "setter", 
/* 529 */           Integer.valueOf(limit));
/*     */     }
/* 531 */     String fid = "setMaxUnusedConnections(int)";
/* 532 */     if (limit < 0) {
/* 533 */       if (Trace.isOn) {
/* 534 */         Trace.data(this, "setMaxUnusedConnections(int)", "setHighThreshold called with thresdhold < 0", "");
/*     */       }
/* 536 */       IllegalArgumentException traceRet1 = new IllegalArgumentException();
/*     */       
/* 538 */       if (Trace.isOn) {
/* 539 */         Trace.throwing(this, "com.ibm.mq.MQSimpleConnectionManager", "setMaxUnusedConnections(int)", traceRet1);
/*     */       }
/*     */       
/* 542 */       throw traceRet1;
/*     */     } 
/*     */     
/* 545 */     this.scavenger.setMaxUnusedConnections(limit);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setHighThreshold(int limit) {
/* 557 */     if (Trace.isOn) {
/* 558 */       Trace.data(this, "com.ibm.mq.MQSimpleConnectionManager", "setHighThreshold(int)", "setter", 
/* 559 */           Integer.valueOf(limit));
/*     */     }
/* 561 */     String fid = "setHighThreshold(int)";
/* 562 */     if (limit < 0) {
/* 563 */       if (Trace.isOn) {
/* 564 */         Trace.data(this, "setHighThreshold(int)", "setHighThreshold called with thresdhold < 0", "");
/*     */       }
/* 566 */       IllegalArgumentException traceRet1 = new IllegalArgumentException();
/*     */       
/* 568 */       if (Trace.isOn) {
/* 569 */         Trace.throwing(this, "com.ibm.mq.MQSimpleConnectionManager", "setHighThreshold(int)", traceRet1);
/*     */       }
/*     */       
/* 572 */       throw traceRet1;
/*     */     } 
/*     */     
/* 575 */     this.scavenger.setMaxUnusedConnections(limit);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxUnusedConnections() {
/* 585 */     int traceRet1 = this.scavenger.getMaxUnusedConnections();
/* 586 */     if (Trace.isOn) {
/* 587 */       Trace.data(this, "com.ibm.mq.MQSimpleConnectionManager", "getMaxUnusedConnections()", "getter", 
/* 588 */           Integer.valueOf(traceRet1));
/*     */     }
/* 590 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public int getHighThreshold() {
/* 602 */     int traceRet1 = this.scavenger.getMaxUnusedConnections();
/* 603 */     if (Trace.isOn) {
/* 604 */       Trace.data(this, "com.ibm.mq.MQSimpleConnectionManager", "getHighThreshold()", "getter", 
/* 605 */           Integer.valueOf(traceRet1));
/*     */     }
/* 607 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void _setActive(boolean active) {
/*     */     Vector<StoredManagedConnection> smcs;
/* 619 */     if (Trace.isOn) {
/* 620 */       Trace.entry(this, "com.ibm.mq.MQSimpleConnectionManager", "_setActive(boolean)", new Object[] {
/* 621 */             Boolean.valueOf(active)
/*     */           });
/*     */     }
/* 624 */     synchronized (this) {
/* 625 */       this.active = active;
/* 626 */       smcs = (Vector<StoredManagedConnection>)this.ownedSMCs.clone();
/* 627 */       for (StoredManagedConnection smc : smcs) {
/* 628 */         smc.poolActive(active);
/*     */       }
/* 630 */       if (active == true) {
/* 631 */         this.scavenger.start();
/*     */       } else {
/* 633 */         this.scavenger.quit();
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 638 */     if (!active) {
/* 639 */       for (StoredManagedConnection smc : smcs) {
/* 640 */         smc.destroyIfMarked();
/*     */       }
/*     */     }
/*     */     
/* 644 */     if (Trace.isOn) {
/* 645 */       Trace.exit(this, "com.ibm.mq.MQSimpleConnectionManager", "_setActive(boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void addStoredManagedConnection(StoredManagedConnection smc) {
/* 658 */     if (Trace.isOn) {
/* 659 */       Trace.entry(this, "com.ibm.mq.MQSimpleConnectionManager", "addStoredManagedConnection(StoredManagedConnection)", new Object[] { smc });
/*     */     }
/*     */     
/* 662 */     this.ownedSMCs.addElement(smc);
/*     */     
/* 664 */     if (Trace.isOn) {
/* 665 */       Trace.exit(this, "com.ibm.mq.MQSimpleConnectionManager", "addStoredManagedConnection(StoredManagedConnection)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void removeStoredManagedConnection(StoredManagedConnection smc) {
/* 678 */     if (Trace.isOn) {
/* 679 */       Trace.entry(this, "com.ibm.mq.MQSimpleConnectionManager", "removeStoredManagedConnection(StoredManagedConnection)", new Object[] { smc });
/*     */     }
/*     */     
/* 682 */     this.ownedSMCs.removeElement(smc);
/*     */     
/* 684 */     if (Trace.isOn) {
/* 685 */       Trace.exit(this, "com.ibm.mq.MQSimpleConnectionManager", "removeStoredManagedConnection(StoredManagedConnection)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int currentSize() {
/* 698 */     if (Trace.isOn) {
/* 699 */       Trace.entry(this, "com.ibm.mq.MQSimpleConnectionManager", "currentSize()");
/*     */     }
/* 701 */     int traceRet1 = this.ownedSMCs.size();
/*     */     
/* 703 */     if (Trace.isOn) {
/* 704 */       Trace.exit(this, "com.ibm.mq.MQSimpleConnectionManager", "currentSize()", 
/* 705 */           Integer.valueOf(traceRet1));
/*     */     }
/* 707 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   private int inPool() {
/* 712 */     if (Trace.isOn) {
/* 713 */       Trace.entry(this, "com.ibm.mq.MQSimpleConnectionManager", "inPool()");
/*     */     }
/* 715 */     int traceRet1 = this.scavenger.inPool();
/*     */     
/* 717 */     if (Trace.isOn) {
/* 718 */       Trace.exit(this, "com.ibm.mq.MQSimpleConnectionManager", "inPool()", 
/* 719 */           Integer.valueOf(traceRet1));
/*     */     }
/* 721 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxConnections(int newLimit) throws IllegalArgumentException {
/* 732 */     if (Trace.isOn) {
/* 733 */       Trace.data(this, "com.ibm.mq.MQSimpleConnectionManager", "setMaxConnections(int)", "setter", 
/* 734 */           Integer.valueOf(newLimit));
/*     */     }
/*     */     
/* 737 */     if (newLimit < 0) {
/* 738 */       IllegalArgumentException traceRet1 = new IllegalArgumentException();
/*     */       
/* 740 */       if (Trace.isOn) {
/* 741 */         Trace.throwing(this, "com.ibm.mq.MQSimpleConnectionManager", "setMaxConnections(int)", traceRet1);
/*     */       }
/*     */       
/* 744 */       throw traceRet1;
/*     */     } 
/*     */     
/* 747 */     int actualNum = currentSize();
/* 748 */     this.maxCons = newLimit;
/* 749 */     int inPool = inPool();
/* 750 */     int toShed = 0;
/*     */     
/* 752 */     if (newLimit < actualNum - inPool) {
/* 753 */       toShed = actualNum - inPool;
/* 754 */     } else if (newLimit < actualNum) {
/* 755 */       toShed = actualNum - newLimit;
/*     */     } 
/*     */     
/* 758 */     this.scavenger.destroyNext(toShed);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxConnections() {
/* 768 */     if (Trace.isOn) {
/* 769 */       Trace.data(this, "com.ibm.mq.MQSimpleConnectionManager", "getMaxConnections()", "getter", 
/* 770 */           Integer.valueOf(this.maxCons));
/*     */     }
/* 772 */     return this.maxCons;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   synchronized void _tokenSetChanged(MQPoolServicesEvent event) {
/* 783 */     if (Trace.isOn) {
/* 784 */       Trace.entry(this, "com.ibm.mq.MQSimpleConnectionManager", "_tokenSetChanged(MQPoolServicesEvent)", new Object[] { event });
/*     */     }
/*     */     
/* 787 */     if (this.mode == 0) {
/* 788 */       if (MQEnvironment.defaultMQCxManager == this && this.poolServices.getTokenCount() > 0) {
/* 789 */         if (!this.active)
/*     */         {
/* 791 */           _setActive(true);
/*     */         }
/*     */       }
/* 794 */       else if (this.active == true) {
/*     */         
/* 796 */         _setActive(false);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 801 */     if (Trace.isOn) {
/* 802 */       Trace.exit(this, "com.ibm.mq.MQSimpleConnectionManager", "_tokenSetChanged(MQPoolServicesEvent)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private class PSAdapter
/*     */     implements MQPoolServicesEventListener
/*     */   {
/*     */     private PSAdapter() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void tokenAdded(MQPoolServicesEvent event) {
/* 822 */       if (Trace.isOn) {
/* 823 */         Trace.entry(this, "com.ibm.mq.PSAdapter", "tokenAdded(MQPoolServicesEvent)", new Object[] { event });
/*     */       }
/*     */       
/* 826 */       MQSimpleConnectionManager.this._tokenSetChanged(event);
/*     */       
/* 828 */       if (Trace.isOn) {
/* 829 */         Trace.exit(this, "com.ibm.mq.PSAdapter", "tokenAdded(MQPoolServicesEvent)");
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void tokenRemoved(MQPoolServicesEvent event) {
/* 842 */       if (Trace.isOn) {
/* 843 */         Trace.entry(this, "com.ibm.mq.PSAdapter", "tokenRemoved(MQPoolServicesEvent)", new Object[] { event });
/*     */       }
/*     */       
/* 846 */       MQSimpleConnectionManager.this._tokenSetChanged(event);
/*     */       
/* 848 */       if (Trace.isOn) {
/* 849 */         Trace.exit(this, "com.ibm.mq.PSAdapter", "tokenRemoved(MQPoolServicesEvent)");
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void defaultConnectionManagerChanged(MQPoolServicesEvent event) {
/* 863 */       if (Trace.isOn) {
/* 864 */         Trace.entry(this, "com.ibm.mq.PSAdapter", "defaultConnectionManagerChanged(MQPoolServicesEvent)", new Object[] { event });
/*     */       }
/*     */       
/* 867 */       MQSimpleConnectionManager.this._tokenSetChanged(event);
/*     */       
/* 869 */       if (Trace.isOn)
/* 870 */         Trace.exit(this, "com.ibm.mq.PSAdapter", "defaultConnectionManagerChanged(MQPoolServicesEvent)"); 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQSimpleConnectionManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */