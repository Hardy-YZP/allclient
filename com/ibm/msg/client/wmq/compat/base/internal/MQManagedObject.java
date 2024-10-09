/*     */ package com.ibm.msg.client.wmq.compat.base.internal;
/*     */ 
/*     */ import com.ibm.mq.MQException;
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.handles.Hconn;
/*     */ import com.ibm.mq.jmqi.handles.Phobj;
/*     */ import com.ibm.mq.jmqi.handles.Pint;
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.nio.charset.CharacterCodingException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQManagedObject
/*     */ {
/*     */   static {
/*  55 */     if (Trace.isOn) {
/*  56 */       Trace.data("com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQManagedObject.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   private static JmqiEnvironment jmqiEnv = MQSESSION.getJmqiEnv();
/*     */ 
/*     */   
/*     */   protected static final int MQ_DESC_LENGTH = 64;
/*     */ 
/*     */   
/*     */   protected static final int MQCA_PROCESS_DESC = 2011;
/*     */   
/*     */   protected static final int MQCA_Q_DESC = 2013;
/*     */   
/*     */   protected static final int MQCA_Q_MGR_DESC = 2014;
/*     */   
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQManagedObject.java";
/*     */   
/*  78 */   public String alternateUserId = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   public int closeOptions = 0;
/*     */   
/*  89 */   protected Pint completionCode = jmqiEnv.newPint();
/*     */ 
/*     */ 
/*     */   
/*     */   protected volatile boolean connected;
/*     */ 
/*     */   
/*  96 */   public MQQueueManager connectionReference = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected volatile Hconn hconn;
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public boolean isOpen = false;
/*     */ 
/*     */ 
/*     */   
/* 110 */   protected int mqca_description = 2014;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 116 */   public String name = "";
/*     */ 
/*     */ 
/*     */   
/* 120 */   public int openOptions = 32;
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean openStatus = false;
/*     */ 
/*     */   
/* 127 */   protected volatile MQSESSION osession = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 132 */   volatile MQQueueManager parentQmgr = null;
/*     */ 
/*     */   
/*     */   protected Phobj phobj;
/*     */ 
/*     */   
/*     */   protected String pszName;
/*     */   
/* 140 */   protected Pint reason = jmqiEnv.newPint();
/*     */ 
/*     */ 
/*     */   
/*     */   protected volatile boolean resourceOpen;
/*     */ 
/*     */ 
/*     */   
/*     */   protected MQManagedObject() {
/* 149 */     if (Trace.isOn) {
/* 150 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "<init>()");
/*     */     }
/*     */     
/* 153 */     this.resourceOpen = false;
/* 154 */     this.connected = false;
/* 155 */     this.hconn = null;
/* 156 */     this.phobj = jmqiEnv.newPhobj();
/* 157 */     if (Trace.isOn) {
/* 158 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "<init>()");
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
/*     */   public void close() throws MQException {
/* 171 */     if (Trace.isOn) {
/* 172 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "close()");
/*     */     }
/*     */     
/* 175 */     MQException mqe = null;
/* 176 */     synchronized (this) {
/*     */       
/* 178 */       if (this.osession == null && 
/* 179 */         this.connectionReference != null) {
/* 180 */         this.osession = this.connectionReference.getSession();
/*     */       }
/*     */ 
/*     */       
/* 184 */       if (!this.connected || this.osession == null) {
/* 185 */         if (Trace.isOn) {
/* 186 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "close()", 1);
/*     */         }
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 192 */       if (this.resourceOpen) {
/*     */         
/* 194 */         this.osession.MQCLOSE(this.hconn, this.phobj, this.closeOptions, this.completionCode, this.reason);
/*     */ 
/*     */         
/* 197 */         if (this.completionCode.x == 0 && this.reason.x == 0) {
/* 198 */           this.resourceOpen = false;
/*     */         } else {
/* 200 */           mqe = new MQException(this.completionCode.x, this.reason.x, this);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 206 */     if (mqe != null) {
/* 207 */       this.parentQmgr.errorOccurred(mqe);
/*     */       
/* 209 */       if (Trace.isOn) {
/* 210 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "close()", (Throwable)mqe);
/*     */       }
/*     */       
/* 213 */       throw mqe;
/*     */     } 
/*     */ 
/*     */     
/* 217 */     this.isOpen = false;
/* 218 */     this.openStatus = false;
/*     */     
/* 220 */     if (Trace.isOn) {
/* 221 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "close()", 2);
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
/*     */   public final String getAttributeString(int aSelector, int length) throws MQException {
/* 242 */     if (Trace.isOn) {
/* 243 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "getAttributeString(int,int)", new Object[] {
/* 244 */             Integer.valueOf(aSelector), 
/* 245 */             Integer.valueOf(length)
/*     */           });
/*     */     }
/* 248 */     String result = getString(aSelector, length);
/*     */     
/* 250 */     if (Trace.isOn) {
/* 251 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "getAttributeString(int,int)", result);
/*     */     }
/*     */     
/* 254 */     return result;
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
/*     */   public String getDescription() throws MQException {
/* 266 */     String traceRet1 = getString(this.mqca_description, 64);
/* 267 */     if (Trace.isOn) {
/* 268 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "getDescription()", "getter", traceRet1);
/*     */     }
/*     */     
/* 271 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Hconn getHconn() {
/* 279 */     if (Trace.isOn) {
/* 280 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "getHconn()", "getter", this.hconn);
/*     */     }
/*     */     
/* 283 */     return this.hconn;
/*     */   }
/*     */   
/*     */   protected final int getInt(int aSelector) throws MQException {
/* 287 */     if (Trace.isOn) {
/* 288 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "getInt(int)", new Object[] {
/* 289 */             Integer.valueOf(aSelector)
/*     */           });
/*     */     }
/* 292 */     int[] selectors = new int[1];
/* 293 */     selectors[0] = aSelector;
/*     */     
/* 295 */     int[] intAttrs = new int[1];
/* 296 */     inquire(selectors, intAttrs, (byte[])null);
/*     */     
/* 298 */     if (Trace.isOn) {
/* 299 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "getInt(int)", 
/* 300 */           Integer.valueOf(intAttrs[0]));
/*     */     }
/* 302 */     return intAttrs[0];
/*     */   }
/*     */ 
/*     */   
/*     */   protected final String getString(int aSelector, int length) throws MQException {
/* 307 */     if (Trace.isOn) {
/* 308 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "getString(int,int)", new Object[] {
/* 309 */             Integer.valueOf(aSelector), Integer.valueOf(length)
/*     */           });
/*     */     }
/*     */ 
/*     */     
/* 314 */     if (this.osession == null && 
/* 315 */       this.connectionReference != null) {
/* 316 */       this.osession = this.connectionReference.getSession();
/*     */     }
/*     */ 
/*     */     
/* 320 */     if (!this.connected || this.osession == null) {
/*     */       
/* 322 */       MQException traceRet1 = new MQException(2, 2018, this, "MQJI002");
/*     */ 
/*     */ 
/*     */       
/* 326 */       if (Trace.isOn) {
/* 327 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "getString(int,int)", (Throwable)traceRet1, 1);
/*     */       }
/*     */       
/* 330 */       throw traceRet1;
/*     */     } 
/*     */ 
/*     */     
/* 334 */     String result = this.osession.MQINQ(this.hconn, this.phobj.getHobj(), aSelector, length, this.completionCode, this.reason);
/*     */     
/* 336 */     if (this.completionCode.x != 0 || this.reason.x != 0) {
/* 337 */       MQException mqe = new MQException(this.completionCode.x, this.reason.x, this);
/* 338 */       this.parentQmgr.errorOccurred(mqe);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 343 */       if (Trace.isOn) {
/* 344 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "getString(int,int)", (Throwable)mqe, 2);
/*     */       }
/*     */       
/* 347 */       throw mqe;
/*     */     } 
/*     */     
/* 350 */     if (Trace.isOn) {
/* 351 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "getString(int,int)", result);
/*     */     }
/*     */     
/* 354 */     return result;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void inquire(int[] selectors, int[] intAttrs, char[] charAttrs) throws MQException {
/* 382 */     if (Trace.isOn) {
/* 383 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "inquire(int[], int[], char[])", new Object[] { selectors, intAttrs, charAttrs });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 390 */     byte[] byteAttrs = new byte[charAttrs.length];
/*     */ 
/*     */     
/*     */     try {
/* 394 */       inquire(selectors, intAttrs, byteAttrs);
/*     */       
/* 396 */       if (Trace.isOn) {
/* 397 */         Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "inquire(int[], int[], char[])", "byteAttrs", byteAttrs);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 405 */       int ccsid = this.hconn.getCcsid();
/* 406 */       JmqiCodepage cp = JmqiCodepage.getJmqiCodepage(jmqiEnv, ccsid);
/*     */       
/* 408 */       if (cp != null) {
/* 409 */         String stringAttrs = cp.bytesToString(byteAttrs);
/* 410 */         if (Trace.isOn) {
/* 411 */           Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "inquire(int[], int[], char[])", "Converted string:", stringAttrs);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 422 */         char[] tempCharAttrs = stringAttrs.toCharArray();
/* 423 */         if (tempCharAttrs.length <= charAttrs.length) {
/*     */           
/* 425 */           System.arraycopy(tempCharAttrs, 0, charAttrs, 0, tempCharAttrs.length);
/* 426 */           if (Trace.isOn) {
/* 427 */             Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "inquire(int[], int[], char[])", "Converted charAttrs: ", new String(charAttrs));
/*     */ 
/*     */           
/*     */           }
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */           
/* 436 */           System.arraycopy(tempCharAttrs, 0, charAttrs, 0, charAttrs.length);
/* 437 */           MQException traceRet1 = new MQException(1, 2008, this);
/* 438 */           if (Trace.isOn) {
/* 439 */             Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "inquire(int[], int[], char[])", "Returned char array bigger than supplied char array. Supplied length: " + charAttrs.length + ". Returned length: " + tempCharAttrs.length + ".  Converted charAttrs: ", new String(charAttrs));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 446 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "inquire(int[], int[], char[])", (Throwable)traceRet1, 1);
/*     */           } 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 452 */           throw traceRet1;
/*     */         } 
/*     */       } else {
/*     */         
/* 456 */         MQException traceRet2 = new MQException(2, 2330, this);
/* 457 */         if (Trace.isOn) {
/* 458 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "inquire(int[], int[], char[])", (Throwable)traceRet2, 2);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 464 */         throw traceRet2;
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 469 */     catch (JmqiException jmqie) {
/* 470 */       if (Trace.isOn) {
/* 471 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "inquire(int[], int[], char[])", (Throwable)jmqie, 3);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 477 */       MQException traceRet3 = new MQException(2, 2330, this, (Throwable)jmqie);
/* 478 */       if (Trace.isOn) {
/* 479 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "inquire(int[], int[], char[])", (Throwable)traceRet3, 3);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 485 */       throw traceRet3;
/*     */     
/*     */     }
/* 488 */     catch (CharacterCodingException cce) {
/* 489 */       if (Trace.isOn) {
/* 490 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "inquire(int[], int[], char[])", cce, 4);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 498 */       JmqiException jmqie = new JmqiException(jmqiEnv, -1, null, 2, 2195, cce);
/* 499 */       MQException traceRet4 = new MQException(2, 2340, this, (Throwable)jmqie);
/* 500 */       if (Trace.isOn) {
/* 501 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "inquire(int[], int[], char[])", (Throwable)traceRet4, 4);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 507 */       throw traceRet4;
/*     */     } 
/* 509 */     if (Trace.isOn) {
/* 510 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "inquire(int[], int[], char[])");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void inquire(int[] selectors, int[] intAttrs, byte[] charAttrs) throws MQException {
/* 538 */     if (Trace.isOn) {
/* 539 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "inquire(int [ ],int [ ],byte [ ])", new Object[] { selectors, intAttrs, charAttrs });
/*     */     }
/*     */ 
/*     */     
/* 543 */     if (this.osession == null && 
/* 544 */       this.connectionReference != null) {
/* 545 */       this.osession = this.connectionReference.getSession();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 551 */     if (!this.connected || this.osession == null) {
/* 552 */       MQException traceRet1 = new MQException(2, 2018, this, "MQJI002");
/* 553 */       if (Trace.isOn) {
/* 554 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "inquire(int [ ],int [ ],byte [ ])", (Throwable)traceRet1, 1);
/*     */       }
/*     */       
/* 557 */       throw traceRet1;
/*     */     } 
/*     */     
/* 560 */     if (!this.resourceOpen) {
/* 561 */       MQException traceRet2 = new MQException(2, 2019, this, "MQJI016");
/* 562 */       if (Trace.isOn) {
/* 563 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "inquire(int [ ],int [ ],byte [ ])", (Throwable)traceRet2, 2);
/*     */       }
/*     */       
/* 566 */       throw traceRet2;
/*     */     } 
/*     */     
/* 569 */     if (selectors == null || selectors.length == 0) {
/* 570 */       MQException traceRet3 = new MQException(2, 2067, this, "MQJI017");
/* 571 */       if (Trace.isOn) {
/* 572 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "inquire(int [ ],int [ ],byte [ ])", (Throwable)traceRet3, 3);
/*     */       }
/*     */       
/* 575 */       throw traceRet3;
/*     */     } 
/*     */     
/* 578 */     int numInts = 0;
/* 579 */     if (intAttrs != null) {
/* 580 */       numInts = intAttrs.length;
/*     */     }
/*     */     
/* 583 */     int numChars = 0;
/* 584 */     if (charAttrs != null) {
/* 585 */       numChars = charAttrs.length;
/*     */     }
/*     */     
/* 588 */     this.osession.MQINQ(this.hconn, this.phobj
/* 589 */         .getHobj(), selectors.length, selectors, numInts, intAttrs, numChars, charAttrs, this.completionCode, this.reason);
/*     */     
/* 591 */     if (this.completionCode.x != 0 || this.reason.x != 0) {
/* 592 */       MQException mqe = new MQException(this.completionCode.x, this.reason.x, this);
/*     */       
/* 594 */       this.parentQmgr.errorOccurred(mqe);
/*     */       
/* 596 */       if (Trace.isOn) {
/* 597 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "inquire(int [ ],int [ ],byte [ ])", (Throwable)mqe, 4);
/*     */       }
/*     */       
/* 600 */       throw mqe;
/*     */     } 
/*     */ 
/*     */     
/* 604 */     if (Trace.isOn) {
/* 605 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "inquire(int [ ],int [ ],byte [ ])");
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
/*     */   public boolean isOpen() {
/* 619 */     if (Trace.isOn) {
/* 620 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "isOpen()", "getter", 
/* 621 */           Boolean.valueOf(this.openStatus));
/*     */     }
/* 623 */     return this.openStatus;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(int[] selectors, int[] intAttrs, byte[] charAttrs) throws MQException {
/* 648 */     if (Trace.isOn) {
/* 649 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "set(int [ ],int [ ],byte [ ])", new Object[] { selectors, intAttrs, charAttrs });
/*     */     }
/*     */ 
/*     */     
/* 653 */     MQException mqe = null;
/*     */     
/* 655 */     synchronized (this) {
/*     */       
/* 657 */       if (this.osession == null && 
/* 658 */         this.connectionReference != null) {
/* 659 */         this.osession = this.connectionReference.getSession();
/*     */       }
/*     */ 
/*     */       
/* 663 */       if (!this.connected || this.osession == null) {
/*     */         
/* 665 */         MQException traceRet1 = new MQException(2, 2018, this, "MQJI002");
/* 666 */         if (Trace.isOn) {
/* 667 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "set(int [ ],int [ ],byte [ ])", (Throwable)traceRet1, 1);
/*     */         }
/*     */         
/* 670 */         throw traceRet1;
/*     */       } 
/*     */       
/* 673 */       if (!this.resourceOpen) {
/*     */         
/* 675 */         MQException traceRet2 = new MQException(2, 2019, this, "MQJI016");
/* 676 */         if (Trace.isOn) {
/* 677 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "set(int [ ],int [ ],byte [ ])", (Throwable)traceRet2, 2);
/*     */         }
/*     */         
/* 680 */         throw traceRet2;
/*     */       } 
/*     */       
/* 683 */       if (selectors == null || selectors.length == 0) {
/*     */         
/* 685 */         MQException traceRet3 = new MQException(2, 2067, this, "MQJI017");
/* 686 */         if (Trace.isOn) {
/* 687 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "set(int [ ],int [ ],byte [ ])", (Throwable)traceRet3, 3);
/*     */         }
/*     */         
/* 690 */         throw traceRet3;
/*     */       } 
/*     */       
/* 693 */       int numInts = 0;
/* 694 */       if (intAttrs != null) {
/* 695 */         numInts = intAttrs.length;
/*     */       }
/*     */       
/* 698 */       int numChars = 0;
/* 699 */       if (charAttrs != null) {
/* 700 */         numChars = charAttrs.length;
/*     */       }
/*     */       
/* 703 */       this.osession.MQSET(this.hconn, this.phobj
/* 704 */           .getHobj(), selectors.length, selectors, numInts, intAttrs, numChars, charAttrs, this.completionCode, this.reason);
/*     */       
/* 706 */       if (this.completionCode.x != 0 || this.reason.x != 0) {
/* 707 */         mqe = new MQException(this.completionCode.x, this.reason.x, this);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 712 */     if (mqe != null) {
/* 713 */       this.parentQmgr.errorOccurred(mqe);
/*     */       
/* 715 */       if (Trace.isOn) {
/* 716 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "set(int [ ],int [ ],byte [ ])", (Throwable)mqe, 4);
/*     */       }
/*     */       
/* 719 */       throw mqe;
/*     */     } 
/*     */ 
/*     */     
/* 723 */     if (Trace.isOn) {
/* 724 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "set(int [ ],int [ ],byte [ ])");
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
/*     */   public final void setAttributeString(int aSelector, String aValue, int length) throws MQException {
/* 744 */     if (Trace.isOn)
/* 745 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "setAttributeString(int,String,int)", new Object[] {
/* 746 */             Integer.valueOf(aSelector), aValue, 
/* 747 */             Integer.valueOf(length)
/*     */           }); 
/* 749 */     setString(aSelector, aValue, length);
/* 750 */     if (Trace.isOn) {
/* 751 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "setAttributeString(int,String,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final void setInt(int aSelector, int aValue) throws MQException {
/* 759 */     if (Trace.isOn) {
/* 760 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "setInt(int,int)", new Object[] {
/* 761 */             Integer.valueOf(aSelector), Integer.valueOf(aValue)
/*     */           });
/*     */     }
/* 764 */     int[] selectors = new int[1];
/* 765 */     selectors[0] = aSelector;
/*     */     
/* 767 */     int[] intAttrs = new int[1];
/* 768 */     intAttrs[0] = aValue;
/* 769 */     set(selectors, intAttrs, null);
/*     */     
/* 771 */     if (Trace.isOn) {
/* 772 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "setInt(int,int)");
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
/*     */   protected final void setString(int aSelector, String aValue, int length) throws MQException {
/* 793 */     if (Trace.isOn) {
/* 794 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "setString(int,String,int)", new Object[] {
/* 795 */             Integer.valueOf(aSelector), aValue, 
/* 796 */             Integer.valueOf(length)
/*     */           });
/*     */     }
/* 799 */     if (this.osession == null && 
/* 800 */       this.connectionReference != null) {
/* 801 */       this.osession = this.connectionReference.getSession();
/*     */     }
/*     */ 
/*     */     
/* 805 */     if (!this.connected || this.osession == null) {
/*     */       
/* 807 */       MQException traceRet1 = new MQException(2, 2018, this, "MQJI002");
/*     */ 
/*     */ 
/*     */       
/* 811 */       if (Trace.isOn) {
/* 812 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "setString(int,String,int)", (Throwable)traceRet1, 1);
/*     */       }
/*     */       
/* 815 */       throw traceRet1;
/*     */     } 
/*     */ 
/*     */     
/* 819 */     if (!this.resourceOpen) {
/* 820 */       MQException traceRet2 = new MQException(2, 2019, this, "MQJI016");
/* 821 */       if (Trace.isOn) {
/* 822 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "setString(int,String,int)", (Throwable)traceRet2, 2);
/*     */       }
/*     */       
/* 825 */       throw traceRet2;
/*     */     } 
/*     */     
/* 828 */     this.osession.MQSET(this.hconn, this.phobj.getHobj(), aSelector, aValue, length, this.completionCode, this.reason);
/*     */     
/* 830 */     if (this.completionCode.x != 0 || this.reason.x != 0) {
/* 831 */       MQException mqe = new MQException(this.completionCode.x, this.reason.x, this);
/* 832 */       this.parentQmgr.errorOccurred(mqe);
/*     */       
/* 834 */       if (Trace.isOn) {
/* 835 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "setString(int,String,int)", (Throwable)mqe, 3);
/*     */       }
/*     */       
/* 838 */       throw mqe;
/*     */     } 
/*     */     
/* 841 */     if (Trace.isOn)
/* 842 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject", "setString(int,String,int)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\base\internal\MQManagedObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */