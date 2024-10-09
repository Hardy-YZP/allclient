/*     */ package com.ibm.mq;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.mq.jmqi.handles.Phconn;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class StoredManagedConnection
/*     */   extends JmqiObject
/*     */   implements MQConnectionEventListener
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/StoredManagedConnection.java";
/*     */   MQManagedConnectionJ11 mqManCon;
/*     */   ManagedConnectionStore pool;
/*     */   PoolScavenger scavenger;
/*     */   MQManagedConnectionFactory mqMcf;
/*     */   MQSimpleConnectionManager owner;
/*     */   MQConnectionRequestInfo cxReqInf;
/*     */   
/*     */   static {
/*  54 */     if (Trace.isOn) {
/*  55 */       Trace.data("com.ibm.mq.StoredManagedConnection", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/StoredManagedConnection.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean inuse = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   volatile boolean poolActive = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   volatile boolean destroyMark = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   StoredManagedConnection(MQManagedConnectionFactory mqMcf, MQConnectionRequestInfo cxReqInf, ManagedConnectionStore pool, PoolScavenger scavenger, MQSimpleConnectionManager owner) throws MQResourceException {
/*  86 */     super(MQSESSION.getJmqiEnv());
/*  87 */     if (Trace.isOn) {
/*  88 */       Trace.entry(this, "com.ibm.mq.StoredManagedConnection", "<init>(MQManagedConnectionFactory,MQConnectionRequestInfo,ManagedConnectionStore,PoolScavenger,MQSimpleConnectionManager)", new Object[] { mqMcf, cxReqInf, pool, scavenger, owner });
/*     */     }
/*     */ 
/*     */     
/*  92 */     this.pool = pool;
/*  93 */     this.scavenger = scavenger;
/*  94 */     this.mqMcf = mqMcf;
/*  95 */     this.cxReqInf = cxReqInf;
/*  96 */     this.owner = owner;
/*  97 */     this.mqManCon = mqMcf.createManagedConnection(cxReqInf);
/*  98 */     this.inuse = true;
/*  99 */     this.mqManCon.addMQConnectionEventListener(this);
/*     */     
/* 101 */     if (Trace.isOn) {
/* 102 */       Trace.exit(this, "com.ibm.mq.StoredManagedConnection", "<init>(MQManagedConnectionFactory,MQConnectionRequestInfo,ManagedConnectionStore,PoolScavenger,MQSimpleConnectionManager)");
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
/*     */   boolean use() {
/* 116 */     if (Trace.isOn) {
/* 117 */       Trace.entry(this, "com.ibm.mq.StoredManagedConnection", "use()");
/*     */     }
/* 119 */     String fid = "use()";
/* 120 */     synchronized (this) {
/* 121 */       if (Trace.isOn) {
/* 122 */         Trace.data(this, "use()", "use - owns synclock", "");
/*     */       }
/* 124 */       if (!this.inuse) {
/* 125 */         this.pool.deregister(this);
/* 126 */         this.scavenger.deregister(this);
/* 127 */         this.inuse = true;
/*     */         
/* 129 */         if (Trace.isOn) {
/* 130 */           Trace.exit(this, "com.ibm.mq.StoredManagedConnection", "use()", Boolean.valueOf(true), 1);
/*     */         }
/*     */         
/* 133 */         return true;
/*     */       } 
/*     */       
/* 136 */       if (Trace.isOn) {
/* 137 */         Trace.exit(this, "com.ibm.mq.StoredManagedConnection", "use()", Boolean.valueOf(false), 2);
/*     */       }
/*     */       
/* 140 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void initializePoolActive(boolean active) {
/* 149 */     if (Trace.isOn)
/* 150 */       Trace.entry(this, "com.ibm.mq.StoredManagedConnection", "initializePoolActive(boolean)", new Object[] {
/* 151 */             Boolean.valueOf(active)
/*     */           }); 
/* 153 */     this.poolActive = active;
/* 154 */     this.owner.addStoredManagedConnection(this);
/*     */     
/* 156 */     if (Trace.isOn) {
/* 157 */       Trace.exit(this, "com.ibm.mq.StoredManagedConnection", "initializePoolActive(boolean)");
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
/*     */   synchronized void poolActive(boolean active) {
/* 169 */     if (Trace.isOn)
/* 170 */       Trace.entry(this, "com.ibm.mq.StoredManagedConnection", "poolActive(boolean)", new Object[] {
/* 171 */             Boolean.valueOf(active)
/*     */           }); 
/* 173 */     this.poolActive = active;
/* 174 */     if (!active && !this.inuse) {
/* 175 */       this.destroyMark = true;
/* 176 */       this.pool.deregister(this);
/* 177 */       this.scavenger.deregister(this);
/* 178 */       this.inuse = true;
/*     */     } 
/*     */     
/* 181 */     if (Trace.isOn) {
/* 182 */       Trace.exit(this, "com.ibm.mq.StoredManagedConnection", "poolActive(boolean)");
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
/*     */   void destroyIfMarked() {
/* 194 */     if (Trace.isOn) {
/* 195 */       Trace.entry(this, "com.ibm.mq.StoredManagedConnection", "destroyIfMarked()");
/*     */     }
/* 197 */     if (this.destroyMark) {
/* 198 */       destroy();
/*     */     }
/*     */     
/* 201 */     if (Trace.isOn) {
/* 202 */       Trace.exit(this, "com.ibm.mq.StoredManagedConnection", "destroyIfMarked()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void trigger() {
/* 210 */     if (Trace.isOn) {
/* 211 */       Trace.entry(this, "com.ibm.mq.StoredManagedConnection", "trigger()");
/*     */     }
/* 213 */     String fid = "trigger()";
/* 214 */     boolean act = false;
/* 215 */     synchronized (this) {
/* 216 */       if (Trace.isOn) {
/* 217 */         Trace.data(this, "trigger()", "trigger owns synclock", "");
/*     */       }
/* 219 */       if (!this.inuse) {
/* 220 */         act = true;
/* 221 */         this.pool.deregister(this);
/* 222 */         this.scavenger.deregister(this);
/* 223 */         this.inuse = true;
/*     */       } 
/* 225 */       if (Trace.isOn) {
/* 226 */         Trace.data(this, "trigger()", "trigger releasing synclock", "");
/*     */       }
/*     */     } 
/* 229 */     if (act) {
/* 230 */       destroy();
/*     */     }
/*     */     
/* 233 */     if (Trace.isOn) {
/* 234 */       Trace.exit(this, "com.ibm.mq.StoredManagedConnection", "trigger()");
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
/*     */   void destroy() {
/* 246 */     if (Trace.isOn) {
/* 247 */       Trace.entry(this, "com.ibm.mq.StoredManagedConnection", "destroy()");
/*     */     }
/*     */     
/* 250 */     MQManagedConnectionJ11 mqManCon = this.mqManCon;
/* 251 */     if (mqManCon != null) {
/* 252 */       this.owner.removeStoredManagedConnection(this);
/* 253 */       mqManCon.removeMQConnectionEventListener(this);
/*     */       try {
/* 255 */         mqManCon.destroy();
/*     */       }
/* 257 */       catch (MQResourceException mqe) {
/* 258 */         if (Trace.isOn) {
/* 259 */           Trace.catchBlock(this, "com.ibm.mq.StoredManagedConnection", "destroy()", mqe);
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 265 */       this.mqManCon = null;
/*     */     } 
/*     */     
/* 268 */     if (Trace.isOn) {
/* 269 */       Trace.exit(this, "com.ibm.mq.StoredManagedConnection", "destroy()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void connectionClosed(MQManagedConnectionJ11 mqManCon, MQQueueManager qm, boolean calledFromFinalizer) {
/* 277 */     if (Trace.isOn) {
/* 278 */       Trace.entry(this, "com.ibm.mq.StoredManagedConnection", "connectionClosed(MQManagedConnectionJ11,MQQueueManager,boolean)", new Object[] { mqManCon, qm, 
/*     */             
/* 280 */             Boolean.valueOf(calledFromFinalizer) });
/*     */     }
/* 282 */     String fid = "connectionClosed(MQManagedConnectionJ11,MQQueueManager,boolean)";
/* 283 */     qm.setExceptionForDisconnect(null);
/* 284 */     boolean alreadyDestroyed = false;
/*     */     try {
/* 286 */       MQSESSION session = mqManCon.getMQSESSION();
/* 287 */       Pint cc = new Pint();
/* 288 */       Pint rc = new Pint();
/*     */       
/* 290 */       if (!calledFromFinalizer) {
/* 291 */         Phconn hConn = mqManCon.getHConn();
/* 292 */         session.MQCMIT(hConn.getHconn(), cc, rc);
/*     */ 
/*     */ 
/*     */         
/* 296 */         if (cc.x == 2 && rc.x == 2012) {
/*     */ 
/*     */           
/* 299 */           cc.x = 0;
/* 300 */           rc.x = 0;
/*     */         } 
/* 302 */         if (cc.x == 2) {
/* 303 */           MQException traceRet1 = new MQException(cc.x, rc.x, this, session.getLastJmqiException());
/*     */           
/* 305 */           if (Trace.isOn) {
/* 306 */             Trace.throwing(this, "com.ibm.mq.StoredManagedConnection", "connectionClosed(MQManagedConnectionJ11,MQQueueManager,boolean)", traceRet1, 1);
/*     */           }
/*     */           
/* 309 */           throw traceRet1;
/*     */         } 
/*     */       } 
/*     */       
/* 313 */       mqManCon.cleanup();
/* 314 */       synchronized (this) {
/* 315 */         if (Trace.isOn) {
/* 316 */           Trace.data(this, "connectionClosed(MQManagedConnectionJ11,MQQueueManager,boolean)", "connectionClosed owns synclock", "");
/*     */         }
/* 318 */         if (this.poolActive) {
/* 319 */           this.inuse = false;
/* 320 */           this.pool.register(this);
/* 321 */           this.scavenger.register(this);
/*     */         } else {
/* 323 */           destroy();
/* 324 */           alreadyDestroyed = true;
/*     */         } 
/* 326 */         if (Trace.isOn) {
/* 327 */           Trace.data(this, "connectionClosed(MQManagedConnectionJ11,MQQueueManager,boolean)", "connectionClosed releasing synclock", "");
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 332 */       if (cc.x == 1) {
/* 333 */         MQException traceRet2 = new MQException(cc.x, rc.x, this, session.getLastJmqiException());
/* 334 */         if (Trace.isOn) {
/* 335 */           Trace.throwing(this, "com.ibm.mq.StoredManagedConnection", "connectionClosed(MQManagedConnectionJ11,MQQueueManager,boolean)", traceRet2, 2);
/*     */         }
/*     */         
/* 338 */         throw traceRet2;
/*     */       }
/*     */     
/* 341 */     } catch (MQResourceException mqe) {
/* 342 */       if (Trace.isOn) {
/* 343 */         Trace.catchBlock(this, "com.ibm.mq.StoredManagedConnection", "connectionClosed(MQManagedConnectionJ11,MQQueueManager,boolean)", mqe, 1);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 349 */       destroy();
/*     */ 
/*     */     
/*     */     }
/* 353 */     catch (MQException mqe) {
/* 354 */       if (Trace.isOn) {
/* 355 */         Trace.catchBlock(this, "com.ibm.mq.StoredManagedConnection", "connectionClosed(MQManagedConnectionJ11,MQQueueManager,boolean)", mqe, 2);
/*     */       }
/*     */ 
/*     */       
/* 359 */       qm.setExceptionForDisconnect(mqe);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 365 */       if (!alreadyDestroyed) {
/* 366 */         destroy();
/*     */       }
/*     */     } 
/*     */     
/* 370 */     if (Trace.isOn) {
/* 371 */       Trace.exit(this, "com.ibm.mq.StoredManagedConnection", "connectionClosed(MQManagedConnectionJ11,MQQueueManager,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void connectionErrorOccurred(MQManagedConnectionJ11 mqManCon, MQQueueManager qm, Exception ex) {
/* 379 */     if (Trace.isOn) {
/* 380 */       Trace.entry(this, "com.ibm.mq.StoredManagedConnection", "connectionErrorOccurred(MQManagedConnectionJ11,MQQueueManager,Exception)", new Object[] { mqManCon, qm, ex });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 387 */     if (Trace.isOn)
/* 388 */       Trace.exit(this, "com.ibm.mq.StoredManagedConnection", "connectionErrorOccurred(MQManagedConnectionJ11,MQQueueManager,Exception)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\StoredManagedConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */