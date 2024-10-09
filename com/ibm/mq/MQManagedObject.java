/*      */ package com.ibm.mq;
/*      */ 
/*      */ import com.ibm.mq.jmqi.JmqiException;
/*      */ import com.ibm.mq.jmqi.JmqiObject;
/*      */ import com.ibm.mq.jmqi.handles.Phconn;
/*      */ import com.ibm.mq.jmqi.handles.Phobj;
/*      */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.io.IOException;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.nio.charset.CharacterCodingException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class MQManagedObject
/*      */   extends JmqiObject
/*      */ {
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQManagedObject.java";
/*      */   protected Phobj Hobj;
/*      */   protected Phconn Hconn;
/*      */   protected String pszName;
/*      */   protected boolean connected;
/*      */   protected boolean resourceOpen;
/*      */   protected boolean closeAttempted;
/*      */   protected static final int MQCA_Q_DESC = 2013;
/*      */   protected static final int MQCA_Q_MGR_DESC = 2014;
/*      */   protected static final int MQCA_PROCESS_DESC = 2011;
/*      */   protected static final int MQ_DESC_LENGTH = 64;
/*      */   
/*      */   static {
/*   72 */     if (Trace.isOn) {
/*   73 */       Trace.data("com.ibm.mq.MQManagedObject", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQManagedObject.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  102 */   protected volatile MQSESSION osession = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  107 */   MQQueueManager parentQmgr = null;
/*      */ 
/*      */   
/*  110 */   protected int mqca_description = 2014;
/*      */ 
/*      */   
/*  113 */   private Pint completionCode = new Pint();
/*  114 */   private Pint reason = new Pint();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*  125 */   public String alternateUserId = "";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*  134 */   public String name = "";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*  143 */   public int openOptions = 32;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public boolean isOpen = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean openStatus = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*  164 */   public MQQueueManager connectionReference = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*  173 */   public int closeOptions = 0;
/*      */ 
/*      */   
/*  176 */   private int resolvedType = 0;
/*      */   
/*      */   private String resolvedQName;
/*      */   
/*      */   private String resolvedObjectString;
/*      */ 
/*      */   
/*      */   protected MQManagedObject() {
/*  184 */     super(MQSESSION.getJmqiEnv());
/*  185 */     if (Trace.isOn) {
/*  186 */       Trace.entry(this, "com.ibm.mq.MQManagedObject", "<init>()");
/*      */     }
/*  188 */     this.resourceOpen = false;
/*  189 */     this.connected = false;
/*  190 */     this.Hconn = MQSESSION.getJmqiEnv().newPhconn();
/*  191 */     this.Hobj = MQSESSION.getJmqiEnv().newPhobj();
/*      */ 
/*      */     
/*  194 */     if (Trace.isOn) {
/*  195 */       Trace.exit(this, "com.ibm.mq.MQManagedObject", "<init>()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void inquire(int[] selectors, int[] intAttrs, char[] charAttrs) throws MQException {
/*  227 */     if (Trace.isOn) {
/*  228 */       Trace.entry(this, "com.ibm.mq.MQManagedObject", "inquire(int [ ],int [ ],char [ ])", new Object[] { selectors, intAttrs, charAttrs });
/*      */     }
/*      */     
/*  231 */     String fid = "inquire(int [ ],int [ ],char [ ]";
/*      */     
/*  233 */     if (Trace.isOn) {
/*  234 */       Trace.data(this, "inquire(int [ ],int [ ],char [ ]", "inquire() Entry {char[]}", null);
/*      */     }
/*      */     
/*  237 */     byte[] byteAttrs = new byte[charAttrs.length];
/*      */     
/*      */     try {
/*      */       JmqiCodepage cp;
/*  241 */       inquire(selectors, intAttrs, byteAttrs);
/*  242 */       if (Trace.isOn) {
/*  243 */         Trace.data(this, "inquire(int [ ],int [ ],char [ ]", "inquire(): returned byteAttrs: ", byteAttrs);
/*      */       }
/*      */ 
/*      */       
/*  247 */       int ccsid = this.Hconn.getHconn().getCcsid();
/*      */       
/*      */       try {
/*  250 */         cp = JmqiCodepage.getJmqiCodepage(this.env, ccsid);
/*      */         
/*  252 */         if (cp == null) {
/*  253 */           UnsupportedEncodingException traceRet2 = new UnsupportedEncodingException(Integer.toString(ccsid));
/*  254 */           if (Trace.isOn) {
/*  255 */             Trace.throwing(this, "com.ibm.mq.MQManagedObject", "inquire(int [ ],int [ ],char [ ])", traceRet2, 1);
/*      */           }
/*      */           
/*  258 */           throw traceRet2;
/*      */         }
/*      */       
/*      */       }
/*  262 */       catch (UnsupportedEncodingException e) {
/*  263 */         if (Trace.isOn) {
/*  264 */           Trace.catchBlock(this, "com.ibm.mq.MQManagedObject", "inquire(int [ ],int [ ],char [ ])", e, 1);
/*      */         }
/*      */ 
/*      */         
/*  268 */         MQException traceRet1 = new MQException(2, 2330, this);
/*  269 */         if (Trace.isOn) {
/*  270 */           Trace.throwing(this, "com.ibm.mq.MQManagedObject", "inquire(int [ ],int [ ],char [ ])", traceRet1, 2);
/*      */         }
/*      */         
/*  273 */         throw traceRet1;
/*      */       } 
/*      */       
/*  276 */       String stringAttrs = cp.bytesToString(byteAttrs);
/*      */       
/*  278 */       if (Trace.isOn) {
/*  279 */         Trace.data(this, "inquire(int [ ],int [ ],char [ ]", "inquire(): stringAttrs: ", stringAttrs);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  284 */       char[] tempCharAttrs = stringAttrs.toCharArray();
/*  285 */       if (tempCharAttrs.length <= charAttrs.length) {
/*      */         
/*  287 */         System.arraycopy(tempCharAttrs, 0, charAttrs, 0, tempCharAttrs.length);
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  292 */         System.arraycopy(tempCharAttrs, 0, charAttrs, 0, charAttrs.length);
/*  293 */         MQException traceRet1 = new MQException(1, 2008, this);
/*  294 */         if (Trace.isOn) {
/*  295 */           Trace.data(this, "inquire(int [ ],int [ ],char [ ]", "inquire(): returned char array bigger than supplied char array: supplied length: " + charAttrs.length + " returned length: " + tempCharAttrs.length, "");
/*      */         }
/*      */ 
/*      */         
/*  299 */         if (Trace.isOn) {
/*  300 */           Trace.throwing(this, "com.ibm.mq.MQManagedObject", "inquire(int [ ],int [ ],char [ ])", traceRet1, 3);
/*      */         }
/*      */         
/*  303 */         throw traceRet1;
/*      */       } 
/*  305 */       if (Trace.isOn) {
/*  306 */         Trace.data(this, "inquire(int [ ],int [ ],char [ ]", "inquire(): charAttrs: ", new String(charAttrs));
/*      */       }
/*      */     }
/*  309 */     catch (JmqiException e) {
/*  310 */       if (Trace.isOn) {
/*  311 */         Trace.catchBlock(this, "com.ibm.mq.MQManagedObject", "inquire(int [ ],int [ ],char [ ])", (Throwable)e, 2);
/*      */       }
/*      */       
/*  314 */       MQException traceRet3 = new MQException(2, 2330, this, e);
/*  315 */       if (Trace.isOn) {
/*  316 */         Trace.throwing(this, "com.ibm.mq.MQManagedObject", "inquire(int [ ],int [ ],char [ ])", traceRet3, 4);
/*      */       }
/*      */       
/*  319 */       throw traceRet3;
/*      */     }
/*  321 */     catch (CharacterCodingException e) {
/*  322 */       if (Trace.isOn) {
/*  323 */         Trace.catchBlock(this, "com.ibm.mq.MQManagedObject", "inquire(int [ ],int [ ],char [ ])", e, 3);
/*      */       }
/*      */       
/*  326 */       MQException traceRet4 = new MQException(2, 2330, this);
/*  327 */       if (Trace.isOn) {
/*  328 */         Trace.throwing(this, "com.ibm.mq.MQManagedObject", "inquire(int [ ],int [ ],char [ ])", traceRet4, 5);
/*      */       }
/*      */       
/*  331 */       throw traceRet4;
/*      */     } 
/*  333 */     if (Trace.isOn) {
/*  334 */       Trace.data(this, "inquire(int [ ],int [ ],char [ ]", "inquire() Exit {char[]}", null);
/*      */     }
/*  336 */     if (Trace.isOn) {
/*  337 */       Trace.exit(this, "com.ibm.mq.MQManagedObject", "inquire(int [ ],int [ ],char [ ])");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void inquire(int[] selectors, int[] intAttrs, byte[] charAttrs) throws MQException {
/*  364 */     if (Trace.isOn) {
/*  365 */       Trace.entry(this, "com.ibm.mq.MQManagedObject", "inquire(int [ ],int [ ],byte [ ])", new Object[] { selectors, intAttrs, charAttrs });
/*      */     }
/*      */     
/*  368 */     if (this.osession == null && 
/*  369 */       this.connectionReference != null) {
/*  370 */       this.osession = this.connectionReference.getSession();
/*      */     }
/*      */ 
/*      */     
/*  374 */     if (!this.connected || this.osession == null) {
/*  375 */       MQException traceRet1 = new MQException(2, 2018, this, "MQJI002");
/*      */       
/*  377 */       if (Trace.isOn) {
/*  378 */         Trace.throwing(this, "com.ibm.mq.MQManagedObject", "inquire(int [ ],int [ ],byte [ ])", traceRet1, 1);
/*      */       }
/*      */       
/*  381 */       throw traceRet1;
/*      */     } 
/*      */     
/*  384 */     if (!this.resourceOpen) {
/*  385 */       MQException traceRet2 = new MQException(2, 2019, this, "MQJI016");
/*  386 */       if (Trace.isOn) {
/*  387 */         Trace.throwing(this, "com.ibm.mq.MQManagedObject", "inquire(int [ ],int [ ],byte [ ])", traceRet2, 2);
/*      */       }
/*      */       
/*  390 */       throw traceRet2;
/*      */     } 
/*      */     
/*  393 */     if (selectors == null || selectors.length == 0) {
/*  394 */       MQException traceRet3 = new MQException(2, 2067, this, "MQJI017");
/*  395 */       if (Trace.isOn) {
/*  396 */         Trace.throwing(this, "com.ibm.mq.MQManagedObject", "inquire(int [ ],int [ ],byte [ ])", traceRet3, 3);
/*      */       }
/*      */       
/*  399 */       throw traceRet3;
/*      */     } 
/*      */     
/*  402 */     int numInts = 0;
/*  403 */     if (intAttrs != null) {
/*  404 */       numInts = intAttrs.length;
/*      */     }
/*      */     
/*  407 */     int numChars = 0;
/*  408 */     if (charAttrs != null) {
/*  409 */       numChars = charAttrs.length;
/*      */     }
/*      */     
/*  412 */     this.osession.MQINQ(this.Hconn.getHconn(), this.Hobj.getHobj(), selectors.length, selectors, numInts, intAttrs, numChars, charAttrs, this.completionCode, this.reason);
/*      */     
/*  414 */     if (this.completionCode.x != 0 || this.reason.x != 0) {
/*  415 */       MQException mqe = new MQException(this.completionCode.x, this.reason.x, this);
/*      */       
/*  417 */       this.parentQmgr.errorOccurred(mqe);
/*  418 */       if (Trace.isOn) {
/*  419 */         Trace.throwing(this, "com.ibm.mq.MQManagedObject", "inquire(int [ ],int [ ],byte [ ])", mqe, 4);
/*      */       }
/*      */       
/*  422 */       throw mqe;
/*      */     } 
/*      */ 
/*      */     
/*  426 */     if (Trace.isOn) {
/*  427 */       Trace.exit(this, "com.ibm.mq.MQManagedObject", "inquire(int [ ],int [ ],byte [ ])");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized void set(int[] selectors, int[] intAttrs, byte[] charAttrs) throws MQException {
/*  455 */     if (Trace.isOn) {
/*  456 */       Trace.entry(this, "com.ibm.mq.MQManagedObject", "set(int [ ],int [ ],byte [ ])", new Object[] { selectors, intAttrs, charAttrs });
/*      */     }
/*      */     
/*  459 */     if (this.osession == null && 
/*  460 */       this.connectionReference != null) {
/*  461 */       this.osession = this.connectionReference.getSession();
/*      */     }
/*      */     
/*  464 */     if (!this.connected || this.osession == null) {
/*  465 */       MQException traceRet1 = new MQException(2, 2018, this, "MQJI002");
/*      */       
/*  467 */       if (Trace.isOn) {
/*  468 */         Trace.throwing(this, "com.ibm.mq.MQManagedObject", "set(int [ ],int [ ],byte [ ])", traceRet1, 1);
/*      */       }
/*      */       
/*  471 */       throw traceRet1;
/*      */     } 
/*      */     
/*  474 */     if (!this.resourceOpen) {
/*  475 */       MQException traceRet2 = new MQException(2, 2019, this, "MQJI016");
/*  476 */       if (Trace.isOn) {
/*  477 */         Trace.throwing(this, "com.ibm.mq.MQManagedObject", "set(int [ ],int [ ],byte [ ])", traceRet2, 2);
/*      */       }
/*      */       
/*  480 */       throw traceRet2;
/*      */     } 
/*      */     
/*  483 */     if (selectors == null || selectors.length == 0) {
/*  484 */       MQException traceRet3 = new MQException(2, 2067, this, "MQJI017");
/*  485 */       if (Trace.isOn) {
/*  486 */         Trace.throwing(this, "com.ibm.mq.MQManagedObject", "set(int [ ],int [ ],byte [ ])", traceRet3, 3);
/*      */       }
/*      */       
/*  489 */       throw traceRet3;
/*      */     } 
/*      */     
/*  492 */     int numInts = 0;
/*  493 */     if (intAttrs != null) {
/*  494 */       numInts = intAttrs.length;
/*      */     }
/*      */     
/*  497 */     int numChars = 0;
/*  498 */     if (charAttrs != null) {
/*  499 */       numChars = charAttrs.length;
/*      */     }
/*      */     
/*  502 */     this.osession.MQSET(this.Hconn.getHconn(), this.Hobj.getHobj(), selectors.length, selectors, numInts, intAttrs, numChars, charAttrs, this.completionCode, this.reason);
/*      */     
/*  504 */     if (this.completionCode.x != 0 || this.reason.x != 0) {
/*      */       
/*  506 */       MQException mqe = new MQException(this.completionCode.x, this.reason.x, this, this.osession.getLastJmqiException());
/*  507 */       this.parentQmgr.errorOccurred(mqe);
/*  508 */       if (Trace.isOn) {
/*  509 */         Trace.throwing(this, "com.ibm.mq.MQManagedObject", "set(int [ ],int [ ],byte [ ])", mqe, 4);
/*      */       }
/*  511 */       throw mqe;
/*      */     } 
/*      */ 
/*      */     
/*  515 */     if (Trace.isOn) {
/*  516 */       Trace.exit(this, "com.ibm.mq.MQManagedObject", "set(int [ ],int [ ],byte [ ])");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void close() throws MQException {
/*  529 */     if (Trace.isOn) {
/*  530 */       Trace.entry(this, "com.ibm.mq.MQManagedObject", "close()");
/*      */     }
/*      */     try {
/*  533 */       close(false);
/*      */     }
/*  535 */     catch (MQException mqe) {
/*  536 */       if (Trace.isOn) {
/*  537 */         Trace.catchBlock(this, "com.ibm.mq.MQManagedObject", "close()", mqe);
/*      */       }
/*  539 */       throw mqe;
/*      */     } finally {
/*      */       
/*  542 */       if (Trace.isOn) {
/*  543 */         Trace.exit(this, "com.ibm.mq.MQManagedObject", "close()");
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private synchronized void close(boolean finalizing) throws MQException {
/*  551 */     if (Trace.isOn) {
/*  552 */       Trace.entry(this, "com.ibm.mq.MQManagedObject", "close(boolean)", new Object[] {
/*  553 */             Boolean.valueOf(finalizing)
/*      */           });
/*      */     }
/*      */     
/*  557 */     this.closeAttempted = true;
/*      */     
/*  559 */     if (this.osession == null && 
/*  560 */       this.connectionReference != null) {
/*  561 */       this.osession = this.connectionReference.getSession();
/*      */     }
/*      */     
/*  564 */     if (!this.connected || this.osession == null) {
/*      */       
/*  566 */       if (Trace.isOn) {
/*  567 */         Trace.exit(this, "com.ibm.mq.MQManagedObject", "close(boolean)", 1);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*  572 */     if (this.resourceOpen) {
/*      */       
/*  574 */       this.osession.MQCLOSE(this.Hconn.getHconn(), this.Hobj, this.closeOptions, this.completionCode, this.reason);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  581 */       if ((this.completionCode.x != 0 || this.reason.x != 0) && !finalizing) {
/*      */         
/*  583 */         MQException mqe = new MQException(this.completionCode.x, this.reason.x, this, this.osession.getLastJmqiException());
/*  584 */         this.parentQmgr.errorOccurred(mqe);
/*  585 */         if (Trace.isOn) {
/*  586 */           Trace.throwing(this, "com.ibm.mq.MQManagedObject", "close(boolean)", mqe);
/*      */         }
/*  588 */         throw mqe;
/*      */       } 
/*      */ 
/*      */       
/*  592 */       this.resourceOpen = false;
/*      */     } 
/*      */     
/*  595 */     this.isOpen = false;
/*  596 */     this.openStatus = false;
/*      */     
/*  598 */     if (Trace.isOn) {
/*  599 */       Trace.exit(this, "com.ibm.mq.MQManagedObject", "close(boolean)", 2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final int getInt(int aSelector) throws MQException {
/*  615 */     if (Trace.isOn) {
/*  616 */       Trace.entry(this, "com.ibm.mq.MQManagedObject", "getInt(int)", new Object[] {
/*  617 */             Integer.valueOf(aSelector)
/*      */           });
/*      */     }
/*  620 */     int[] selectors = new int[1];
/*  621 */     selectors[0] = aSelector;
/*  622 */     int[] intAttrs = new int[1];
/*      */     
/*      */     try {
/*  625 */       inquire(selectors, intAttrs, (byte[])null);
/*      */     }
/*  627 */     catch (MQException mqe) {
/*  628 */       if (Trace.isOn) {
/*  629 */         Trace.catchBlock(this, "com.ibm.mq.MQManagedObject", "getInt(int)", mqe);
/*      */       }
/*  631 */       throw mqe;
/*      */     } finally {
/*      */       
/*  634 */       if (Trace.isOn) {
/*  635 */         Trace.exit(this, "com.ibm.mq.MQManagedObject", "getInt(int)", 
/*  636 */             Integer.valueOf(intAttrs[0]));
/*      */       }
/*      */     } 
/*  639 */     return intAttrs[0];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final void setInt(int aSelector, int aValue) throws MQException {
/*  651 */     if (Trace.isOn) {
/*  652 */       Trace.entry(this, "com.ibm.mq.MQManagedObject", "setInt(int,int)", new Object[] {
/*  653 */             Integer.valueOf(aSelector), Integer.valueOf(aValue)
/*      */           });
/*      */     }
/*  656 */     int[] selectors = new int[1];
/*  657 */     selectors[0] = aSelector;
/*  658 */     int[] intAttrs = new int[1];
/*  659 */     intAttrs[0] = aValue;
/*      */     
/*      */     try {
/*  662 */       set(selectors, intAttrs, null);
/*      */     }
/*  664 */     catch (MQException mqe) {
/*  665 */       if (Trace.isOn) {
/*  666 */         Trace.catchBlock(this, "com.ibm.mq.MQManagedObject", "setInt(int,int)", mqe);
/*      */       }
/*  668 */       throw mqe;
/*      */     } finally {
/*      */       
/*  671 */       if (Trace.isOn) {
/*  672 */         Trace.exit(this, "com.ibm.mq.MQManagedObject", "setInt(int,int)");
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final String getString(int aSelector, int length) throws MQException {
/*  689 */     if (Trace.isOn)
/*  690 */       Trace.entry(this, "com.ibm.mq.MQManagedObject", "getString(int,int)", new Object[] {
/*  691 */             Integer.valueOf(aSelector), Integer.valueOf(length)
/*      */           }); 
/*  693 */     String result = null;
/*  694 */     if (this.osession == null && 
/*  695 */       this.connectionReference != null) {
/*  696 */       this.osession = this.connectionReference.getSession();
/*      */     }
/*      */     
/*  699 */     if (!this.connected || this.osession == null) {
/*  700 */       MQException traceRet1 = new MQException(2, 2018, this, "MQJI002");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  705 */       if (Trace.isOn) {
/*  706 */         Trace.throwing(this, "com.ibm.mq.MQManagedObject", "getString(int,int)", traceRet1, 1);
/*      */       }
/*  708 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/*  712 */     int charEncoding = this.osession.getCharEncoding();
/*      */     
/*  714 */     if (charEncoding == 1) {
/*      */       
/*  716 */       int[] selectors = new int[1];
/*  717 */       selectors[0] = aSelector;
/*      */       
/*  719 */       byte[] charAttrs = new byte[length];
/*      */       
/*  721 */       inquire(selectors, (int[])null, charAttrs);
/*      */       
/*      */       try {
/*  724 */         int ccsid = this.Hconn.getHconn().getCcsid();
/*  725 */         JmqiCodepage cp = JmqiCodepage.getJmqiCodepage(this.env, ccsid);
/*      */         
/*  727 */         if (cp == null) {
/*  728 */           UnsupportedEncodingException traceRet5 = new UnsupportedEncodingException(Integer.toString(ccsid));
/*  729 */           if (Trace.isOn) {
/*  730 */             Trace.throwing(this, "com.ibm.mq.MQManagedObject", "getString(int,int)", traceRet5, 2);
/*      */           }
/*  732 */           throw traceRet5;
/*      */         } 
/*      */         
/*  735 */         result = cp.bytesToString(charAttrs);
/*      */       }
/*  737 */       catch (JmqiException e) {
/*  738 */         if (Trace.isOn) {
/*  739 */           Trace.catchBlock(this, "com.ibm.mq.MQManagedObject", "getString(int,int)", (Throwable)e, 1);
/*      */         }
/*  741 */         MQException traceRet3 = new MQException(2, 2330, this, e);
/*  742 */         if (Trace.isOn) {
/*  743 */           Trace.throwing(this, "com.ibm.mq.MQManagedObject", "getString(int,int)", traceRet3, 3);
/*      */         }
/*  745 */         throw traceRet3;
/*      */       }
/*  747 */       catch (CharacterCodingException|UnsupportedEncodingException e) {
/*  748 */         if (Trace.isOn) {
/*  749 */           Trace.catchBlock(this, "com.ibm.mq.MQManagedObject", "getString(int,int)", e, 2);
/*      */         }
/*  751 */         MQException traceRet4 = new MQException(2, 2330, this);
/*  752 */         if (Trace.isOn) {
/*  753 */           Trace.throwing(this, "com.ibm.mq.MQManagedObject", "getString(int,int)", traceRet4, 4);
/*      */         }
/*  755 */         throw traceRet4;
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  761 */       if (!this.resourceOpen) {
/*  762 */         MQException traceRet2 = new MQException(2, 2019, this, "MQJI016");
/*      */ 
/*      */ 
/*      */         
/*  766 */         if (Trace.isOn) {
/*  767 */           Trace.throwing(this, "com.ibm.mq.MQManagedObject", "getString(int,int)", traceRet2, 5);
/*      */         }
/*      */         
/*  770 */         throw traceRet2;
/*      */       } 
/*      */ 
/*      */       
/*  774 */       result = this.osession.MQINQ(this.Hconn.getHconn(), this.Hobj.getHobj(), aSelector, length, this.completionCode, this.reason);
/*      */ 
/*      */ 
/*      */       
/*  778 */       if (this.completionCode.x != 0 || this.reason.x != 0) {
/*  779 */         MQException mqe = new MQException(this.completionCode.x, this.reason.x, this);
/*      */         
/*  781 */         this.parentQmgr.errorOccurred(mqe);
/*  782 */         if (Trace.isOn) {
/*  783 */           Trace.throwing(this, "com.ibm.mq.MQManagedObject", "getString(int,int)", mqe, 6);
/*      */         }
/*  785 */         throw mqe;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  790 */     if (Trace.isOn) {
/*  791 */       Trace.exit(this, "com.ibm.mq.MQManagedObject", "getString(int,int)", result);
/*      */     }
/*  793 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getAttributeString(int aSelector, int length) throws MQException {
/*  812 */     String result = null;
/*      */     
/*  814 */     if (Trace.isOn)
/*  815 */       Trace.entry(this, "com.ibm.mq.MQManagedObject", "getAttributeString(int,int)", new Object[] {
/*  816 */             Integer.valueOf(aSelector), Integer.valueOf(length)
/*      */           }); 
/*  818 */     if (Trace.isOn)
/*      */     {
/*  820 */       Trace.data(this, "getAttributeString(int,int)", "selector = ", Integer.toString(aSelector));
/*      */     }
/*      */     
/*      */     try {
/*  824 */       result = getString(aSelector, length);
/*      */     } finally {
/*      */       
/*  827 */       if (Trace.isOn) {
/*  828 */         Trace.exit(this, "com.ibm.mq.MQManagedObject", "getAttributeString(int,int)", result);
/*      */       }
/*      */     } 
/*  831 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final void setString(int aSelector, String aValue, int length) throws MQException {
/*  850 */     if (Trace.isOn)
/*  851 */       Trace.entry(this, "com.ibm.mq.MQManagedObject", "setString(int,String,int)", new Object[] {
/*  852 */             Integer.valueOf(aSelector), aValue, Integer.valueOf(length)
/*      */           }); 
/*  854 */     if (this.osession == null && 
/*  855 */       this.connectionReference != null) {
/*  856 */       this.osession = this.connectionReference.getSession();
/*      */     }
/*      */     
/*  859 */     if (!this.connected || this.osession == null) {
/*  860 */       MQException traceRet1 = new MQException(2, 2018, this, "MQJI002");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  865 */       if (Trace.isOn) {
/*  866 */         Trace.throwing(this, "com.ibm.mq.MQManagedObject", "setString(int,String,int)", traceRet1, 1);
/*      */       }
/*      */       
/*  869 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/*  873 */     int charEncoding = this.osession.getCharEncoding();
/*      */     
/*  875 */     if (charEncoding == 1) {
/*      */       
/*  877 */       int[] selectors = new int[1];
/*  878 */       selectors[0] = aSelector;
/*  879 */       int padStart = -1;
/*      */       
/*  881 */       byte[] charAttrs = new byte[length];
/*      */       
/*  883 */       if (aValue != null) {
/*      */ 
/*      */         
/*      */         try {
/*  887 */           int ccsid = this.Hconn.getHconn().getCcsid();
/*  888 */           JmqiCodepage cp = JmqiCodepage.getJmqiCodepage(this.env, ccsid);
/*      */           
/*  890 */           if (cp == null) {
/*  891 */             UnsupportedEncodingException traceRet5 = new UnsupportedEncodingException(Integer.toString(ccsid));
/*  892 */             if (Trace.isOn) {
/*  893 */               Trace.throwing(this, "com.ibm.mq.MQManagedObject", "setString(int,String,int)", traceRet5, 2);
/*      */             }
/*      */             
/*  896 */             throw traceRet5;
/*      */           } 
/*      */           
/*  899 */           byte[] strBytes = cp.stringToBytes(aValue);
/*      */           try {
/*  901 */             System.arraycopy(strBytes, 0, charAttrs, 0, Math.min(length, aValue.length()));
/*      */           }
/*  903 */           catch (Exception ex) {
/*  904 */             if (Trace.isOn) {
/*  905 */               Trace.catchBlock(this, "com.ibm.mq.MQManagedObject", "setString(int,String,int)", ex, 1);
/*      */             }
/*      */           }
/*      */         
/*  909 */         } catch (JmqiException e) {
/*  910 */           if (Trace.isOn) {
/*  911 */             Trace.catchBlock(this, "com.ibm.mq.MQManagedObject", "setString(int,String,int)", (Throwable)e, 2);
/*      */           }
/*  913 */           MQException traceRet3 = new MQException(2, 2330, this, e);
/*  914 */           if (Trace.isOn) {
/*  915 */             Trace.throwing(this, "com.ibm.mq.MQManagedObject", "setString(int,String,int)", traceRet3, 3);
/*      */           }
/*      */           
/*  918 */           throw traceRet3;
/*      */         }
/*  920 */         catch (UnsupportedEncodingException|CharacterCodingException e) {
/*  921 */           if (Trace.isOn) {
/*  922 */             Trace.catchBlock(this, "com.ibm.mq.MQManagedObject", "setString(int,String,int)", e, 3);
/*      */           }
/*  924 */           MQException traceRet4 = new MQException(2, 2330, this);
/*  925 */           if (Trace.isOn) {
/*  926 */             Trace.throwing(this, "com.ibm.mq.MQManagedObject", "setString(int,String,int)", traceRet4, 4);
/*      */           }
/*      */           
/*  929 */           throw traceRet4;
/*      */         } 
/*      */         
/*  932 */         if (aValue.length() < length) {
/*  933 */           padStart = aValue.length();
/*      */         }
/*      */       } else {
/*      */         
/*  937 */         padStart = 0;
/*      */       } 
/*      */       
/*  940 */       if (padStart != -1) {
/*  941 */         for (int i = padStart; i < length; i++) {
/*  942 */           charAttrs[i] = 32;
/*      */         }
/*      */       }
/*      */       
/*  946 */       set(selectors, null, charAttrs);
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  951 */       if (!this.resourceOpen) {
/*  952 */         MQException traceRet2 = new MQException(2, 2019, this, "MQJI016");
/*      */ 
/*      */ 
/*      */         
/*  956 */         if (Trace.isOn) {
/*  957 */           Trace.throwing(this, "com.ibm.mq.MQManagedObject", "setString(int,String,int)", traceRet2, 5);
/*      */         }
/*      */         
/*  960 */         throw traceRet2;
/*      */       } 
/*      */ 
/*      */       
/*  964 */       this.osession.MQSET(this.Hconn.getHconn(), this.Hobj.getHobj(), aSelector, aValue, length, this.completionCode, this.reason);
/*      */ 
/*      */       
/*  967 */       if (this.completionCode.x != 0 || this.reason.x != 0) {
/*      */         
/*  969 */         MQException mqe = new MQException(this.completionCode.x, this.reason.x, this, this.osession.getLastJmqiException());
/*  970 */         this.parentQmgr.errorOccurred(mqe);
/*  971 */         if (Trace.isOn) {
/*  972 */           Trace.throwing(this, "com.ibm.mq.MQManagedObject", "setString(int,String,int)", mqe, 6);
/*      */         }
/*  974 */         throw mqe;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  979 */     if (Trace.isOn) {
/*  980 */       Trace.exit(this, "com.ibm.mq.MQManagedObject", "setString(int,String,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAttributeString(int aSelector, String aValue, int length) throws MQException {
/*  997 */     if (Trace.isOn)
/*  998 */       Trace.entry(this, "com.ibm.mq.MQManagedObject", "setAttributeString(int,String,int)", new Object[] {
/*  999 */             Integer.valueOf(aSelector), aValue, Integer.valueOf(length)
/*      */           }); 
/*      */     try {
/* 1002 */       setString(aSelector, aValue, length);
/*      */     } finally {
/*      */       
/* 1005 */       if (Trace.isOn) {
/* 1006 */         Trace.exit(this, "com.ibm.mq.MQManagedObject", "setAttributeString(int,String,int)");
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDescription() throws MQException {
/* 1022 */     String traceRet1 = null;
/*      */     
/*      */     try {
/* 1025 */       traceRet1 = getString(this.mqca_description, 64);
/*      */     } finally {
/*      */       
/* 1028 */       if (Trace.isOn) {
/* 1029 */         Trace.data(this, "com.ibm.mq.MQManagedObject", "getDescription()", "getter", traceRet1);
/*      */       }
/*      */     } 
/* 1032 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getAlternateUserId() throws MQException {
/* 1043 */     if (Trace.isOn) {
/* 1044 */       Trace.data(this, "com.ibm.mq.MQManagedObject", "getAlternateUserId()", "getter", this.alternateUserId);
/*      */     }
/*      */     
/* 1047 */     return this.alternateUserId;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setAlternateUserId(String alternateUserId) throws MQException {
/* 1057 */     if (Trace.isOn) {
/* 1058 */       Trace.data(this, "com.ibm.mq.MQManagedObject", "setAlternateUserId(String)", "setter", alternateUserId);
/*      */     }
/*      */     
/* 1061 */     this.alternateUserId = (alternateUserId == null) ? "" : alternateUserId;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getName() throws MQException {
/* 1073 */     if (Trace.isOn) {
/* 1074 */       Trace.data(this, "com.ibm.mq.MQManagedObject", "getName()", "getter", this.name);
/*      */     }
/* 1076 */     return this.name;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setName(String name) throws MQException {
/* 1086 */     if (Trace.isOn) {
/* 1087 */       Trace.data(this, "com.ibm.mq.MQManagedObject", "setName(String)", "setter", name);
/*      */     }
/* 1089 */     this.name = name;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getOpenOptions() throws MQException {
/* 1102 */     if (Trace.isOn) {
/* 1103 */       Trace.data(this, "com.ibm.mq.MQManagedObject", "getOpenOptions()", "getter", 
/* 1104 */           Integer.valueOf(this.openOptions));
/*      */     }
/* 1106 */     return this.openOptions;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setOpenOptions(int openOptions) throws MQException {
/* 1116 */     if (Trace.isOn) {
/* 1117 */       Trace.data(this, "com.ibm.mq.MQManagedObject", "setOpenOptions(int)", "setter", 
/* 1118 */           Integer.valueOf(openOptions));
/*      */     }
/* 1120 */     this.openOptions = openOptions;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQQueueManager getConnectionReference() throws MQException {
/* 1130 */     if (Trace.isOn) {
/* 1131 */       Trace.data(this, "com.ibm.mq.MQManagedObject", "getConnectionReference()", "getter", this.connectionReference);
/*      */     }
/*      */     
/* 1134 */     return this.connectionReference;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isOpen() {
/* 1148 */     if (Trace.isOn) {
/* 1149 */       Trace.data(this, "com.ibm.mq.MQManagedObject", "isOpen()", "getter", 
/* 1150 */           Boolean.valueOf(this.openStatus));
/*      */     }
/* 1152 */     return this.openStatus;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setOpen(boolean open) throws MQException {
/* 1164 */     if (Trace.isOn) {
/* 1165 */       Trace.data(this, "com.ibm.mq.MQManagedObject", "setOpen(boolean)", "setter", 
/* 1166 */           Boolean.valueOf(open));
/*      */     }
/* 1168 */     this.isOpen = open;
/* 1169 */     this.openStatus = open;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setConnectionReference(MQQueueManager connectionReference) throws MQException {
/* 1179 */     if (Trace.isOn) {
/* 1180 */       Trace.data(this, "com.ibm.mq.MQManagedObject", "setConnectionReference(MQQueueManager)", "setter", connectionReference);
/*      */     }
/*      */     
/* 1183 */     this.connectionReference = connectionReference;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCloseOptions() throws MQException {
/* 1194 */     if (Trace.isOn) {
/* 1195 */       Trace.data(this, "com.ibm.mq.MQManagedObject", "getCloseOptions()", "getter", 
/* 1196 */           Integer.valueOf(this.closeOptions));
/*      */     }
/* 1198 */     return this.closeOptions;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCloseOptions(int closeOptions) throws MQException {
/* 1228 */     if (Trace.isOn) {
/* 1229 */       Trace.data(this, "com.ibm.mq.MQManagedObject", "setCloseOptions(int)", "setter", 
/* 1230 */           Integer.valueOf(closeOptions));
/*      */     }
/* 1232 */     this.closeOptions = closeOptions;
/*      */   }
/*      */   
/*      */   void setResolvedType(int resolvedType) {
/* 1236 */     if (Trace.isOn) {
/* 1237 */       Trace.data(this, "com.ibm.mq.MQManagedObject", "setResolvedType(int)", "setter", 
/* 1238 */           Integer.valueOf(resolvedType));
/*      */     }
/* 1240 */     this.resolvedType = resolvedType;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getResolvedType() {
/* 1247 */     if (Trace.isOn) {
/* 1248 */       Trace.data(this, "com.ibm.mq.MQManagedObject", "getResolvedType()", "getter", 
/* 1249 */           Integer.valueOf(this.resolvedType));
/*      */     }
/* 1251 */     return this.resolvedType;
/*      */   }
/*      */   
/*      */   void setResolvedQName(String resolvedQName) {
/* 1255 */     if (Trace.isOn) {
/* 1256 */       Trace.data(this, "com.ibm.mq.MQManagedObject", "setResolvedQName(String)", "setter", resolvedQName);
/*      */     }
/*      */     
/* 1259 */     this.resolvedQName = resolvedQName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getResolvedQName() {
/* 1266 */     if (Trace.isOn) {
/* 1267 */       Trace.data(this, "com.ibm.mq.MQManagedObject", "getResolvedQName()", "getter", this.resolvedQName);
/*      */     }
/* 1269 */     return this.resolvedQName;
/*      */   }
/*      */   
/*      */   void setResolvedObjectString(String resolvedObjectString) {
/* 1273 */     if (Trace.isOn) {
/* 1274 */       Trace.data(this, "com.ibm.mq.MQManagedObject", "setResolvedObjectString(String)", "setter", resolvedObjectString);
/*      */     }
/*      */     
/* 1277 */     this.resolvedObjectString = resolvedObjectString;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getResolvedObjectString() {
/* 1284 */     if (Trace.isOn) {
/* 1285 */       Trace.data(this, "com.ibm.mq.MQManagedObject", "getResolvedObjectString()", "getter", this.resolvedObjectString);
/*      */     }
/*      */     
/* 1288 */     return this.resolvedObjectString;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQManagedObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */