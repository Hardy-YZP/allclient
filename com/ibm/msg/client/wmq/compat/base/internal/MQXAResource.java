/*      */ package com.ibm.msg.client.wmq.compat.base.internal;
/*      */ 
/*      */ import com.ibm.mq.MQException;
/*      */ import com.ibm.mq.jmqi.internal.JmqiResource;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.util.Vector;
/*      */ import javax.transaction.xa.XAException;
/*      */ import javax.transaction.xa.XAResource;
/*      */ import javax.transaction.xa.Xid;
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
/*      */ public class MQXAResource
/*      */   implements XAResource
/*      */ {
/*      */   static {
/*   69 */     if (Trace.isOn) {
/*   70 */       Trace.data("com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQXAResource.java");
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
/*   82 */   private static int nextRMID = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQXAResource.java";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static Exception createXAException(int errorCode, String msg) {
/*   95 */     if (Trace.isOn)
/*   96 */       Trace.entry("com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "createXAException(int,String)", new Object[] {
/*   97 */             Integer.valueOf(errorCode), msg
/*      */           }); 
/*   99 */     XAException xae = new XAException(msg);
/*  100 */     xae.errorCode = errorCode;
/*  101 */     if (Trace.isOn) {
/*  102 */       Trace.exit("com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "createXAException(int,String)", xae);
/*      */     }
/*      */     
/*  105 */     return xae;
/*      */   }
/*      */ 
/*      */   
/*      */   private static synchronized int getNextRMID() {
/*  110 */     int traceRet1 = nextRMID++;
/*  111 */     if (Trace.isOn) {
/*  112 */       Trace.data("com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "getNextRMID()", "getter", 
/*  113 */           Integer.valueOf(traceRet1));
/*      */     }
/*  115 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean closed = false;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean debugXA = false;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean impEndFlag = false;
/*      */ 
/*      */   
/*      */   private String qmgrName;
/*      */ 
/*      */   
/*      */   private int rmid;
/*      */ 
/*      */   
/*  138 */   private MQXAVerbs session = null;
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
/*      */   public MQXAResource(MQXAVerbs session, String qmgrName) throws XAException {
/*  150 */     if (Trace.isOn) {
/*  151 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "<init>(MQXAVerbs,String)", new Object[] { session, qmgrName });
/*      */     }
/*      */     
/*      */     try {
/*      */       int res;
/*      */       
/*  157 */       this.qmgrName = qmgrName;
/*  158 */       this.session = session;
/*      */ 
/*      */       
/*  161 */       this.rmid = getNextRMID();
/*      */       
/*      */       try {
/*  164 */         res = session.XAOPEN(qmgrName, this.rmid, 0);
/*      */       }
/*  166 */       catch (RuntimeException e) {
/*  167 */         if (Trace.isOn) {
/*  168 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "<init>(MQXAVerbs,String)", e, 1);
/*      */         }
/*      */         
/*  171 */         if (Trace.isOn) {
/*  172 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "<init>(MQXAVerbs,String)", e, 1);
/*      */         }
/*      */         
/*  175 */         throw e;
/*      */       }
/*  177 */       catch (Exception e) {
/*  178 */         if (Trace.isOn) {
/*  179 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "<init>(MQXAVerbs,String)", e, 2);
/*      */         }
/*      */         
/*  182 */         XAException traceRet1 = (XAException)e;
/*  183 */         if (Trace.isOn) {
/*  184 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "<init>(MQXAVerbs,String)", traceRet1, 2);
/*      */         }
/*      */         
/*  187 */         throw traceRet1;
/*      */       } 
/*  189 */       if (res != 0) {
/*  190 */         String[] inserts = { "xa_open", Integer.toString(res) };
/*  191 */         String msg = JmqiResource.getString("MQJE114", inserts);
/*  192 */         XAException e = new XAException(msg);
/*  193 */         e.errorCode = res;
/*  194 */         if (Trace.isOn) {
/*  195 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "<init>(MQXAVerbs,String)", e, 3);
/*      */         }
/*      */         
/*  198 */         throw e;
/*      */       }
/*      */     
/*      */     }
/*  202 */     catch (XAException e) {
/*  203 */       if (Trace.isOn) {
/*  204 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "<init>(MQXAVerbs,String)", e, 3);
/*      */       }
/*      */       
/*  207 */       if (Trace.isOn) {
/*  208 */         Trace.traceData(this, "errorCode=" + e.errorCode, null);
/*      */       }
/*  210 */       if (Trace.isOn) {
/*  211 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "<init>(MQXAVerbs,String)", e, 4);
/*      */       }
/*      */       
/*  214 */       throw e;
/*      */     } 
/*      */     
/*  217 */     if (Trace.isOn) {
/*  218 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "<init>(MQXAVerbs,String)");
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
/*      */   public void close() throws XAException {
/*  236 */     if (Trace.isOn) {
/*  237 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "close()");
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  242 */       if (this.debugXA) {
/*  243 */         System.out.println(this + " close()");
/*      */       }
/*      */       
/*  246 */       if (!this.closed) {
/*      */         int res; try {
/*  248 */           res = this.session.XACLOSE(this.qmgrName, this.rmid, 0);
/*      */         }
/*  250 */         catch (RuntimeException e) {
/*  251 */           if (Trace.isOn) {
/*  252 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "close()", e, 1);
/*      */           }
/*      */           
/*  255 */           if (Trace.isOn) {
/*  256 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "close()", e, 1);
/*      */           }
/*      */           
/*  259 */           throw e;
/*      */         }
/*  261 */         catch (Exception e) {
/*  262 */           if (Trace.isOn) {
/*  263 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "close()", e, 2);
/*      */           }
/*      */           
/*  266 */           XAException traceRet1 = (XAException)e;
/*  267 */           if (Trace.isOn) {
/*  268 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "close()", traceRet1, 2);
/*      */           }
/*      */           
/*  271 */           throw traceRet1;
/*      */         } 
/*  273 */         if (res != 0) {
/*  274 */           String[] inserts = { "xa_close", Integer.toString(res) };
/*  275 */           String msg = JmqiResource.getString("MQJE114", inserts);
/*  276 */           XAException e = new XAException(msg);
/*  277 */           e.errorCode = res;
/*  278 */           if (Trace.isOn) {
/*  279 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "close()", e, 3);
/*      */           }
/*      */           
/*  282 */           throw e;
/*      */         } 
/*  284 */         this.closed = true;
/*      */         
/*  286 */         this.session = null;
/*      */       } else {
/*      */         
/*  289 */         if (this.debugXA) {
/*  290 */           System.out.println(this + " was already closed");
/*      */         }
/*  292 */         if (Trace.isOn) {
/*  293 */           Trace.traceData(this, " already closed", null);
/*      */         }
/*      */       }
/*      */     
/*      */     }
/*  298 */     catch (XAException e) {
/*  299 */       if (Trace.isOn) {
/*  300 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "close()", e, 3);
/*      */       }
/*      */       
/*  303 */       if (Trace.isOn) {
/*  304 */         Trace.traceData(this, "errorCode=" + e.errorCode, null);
/*      */       }
/*  306 */       if (Trace.isOn) {
/*  307 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "close()", e, 4);
/*      */       }
/*      */       
/*  310 */       throw e;
/*      */     } 
/*      */     
/*  313 */     if (Trace.isOn) {
/*  314 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "close()");
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
/*      */   public void commit(Xid xid, boolean onePhase) throws XAException {
/*  341 */     if (Trace.isOn) {
/*  342 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "commit(Xid,boolean)", new Object[] { xid, 
/*  343 */             Boolean.valueOf(onePhase) });
/*      */     }
/*      */     
/*      */     try {
/*      */       int flags;
/*      */       int res;
/*  349 */       if (this.debugXA) {
/*  350 */         System.out.println(this + " commit(" + xid + ", " + onePhase + ")");
/*      */       }
/*      */       
/*  353 */       if (this.closed) {
/*  354 */         String msg = MQException.getNLSMsg("MID_ResourceClosed");
/*  355 */         XAException e = new XAException(msg);
/*  356 */         e.errorCode = -6;
/*  357 */         if (Trace.isOn) {
/*  358 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "commit(Xid,boolean)", e, 1);
/*      */         }
/*      */         
/*  361 */         throw e;
/*      */       } 
/*      */       
/*  364 */       if (onePhase) {
/*  365 */         flags = 1073741824;
/*      */       } else {
/*  367 */         flags = 0;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  375 */         res = this.session.XACOMMIT(xid, this.rmid, flags);
/*      */       }
/*  377 */       catch (RuntimeException e) {
/*  378 */         if (Trace.isOn) {
/*  379 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "commit(Xid,boolean)", e, 1);
/*      */         }
/*      */         
/*  382 */         if (Trace.isOn) {
/*  383 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "commit(Xid,boolean)", e, 2);
/*      */         }
/*      */         
/*  386 */         throw e;
/*      */       }
/*  388 */       catch (Exception e) {
/*  389 */         if (Trace.isOn) {
/*  390 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "commit(Xid,boolean)", e, 2);
/*      */         }
/*      */         
/*  393 */         XAException traceRet1 = (XAException)e;
/*  394 */         if (Trace.isOn) {
/*  395 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "commit(Xid,boolean)", traceRet1, 3);
/*      */         }
/*      */         
/*  398 */         throw traceRet1;
/*      */       } 
/*      */       
/*  401 */       if (res != 0 && res != 3)
/*      */       {
/*      */         
/*  404 */         String[] inserts = { "xa_commit", Integer.toString(res) };
/*  405 */         String msg = JmqiResource.getString("MQJE114", inserts);
/*  406 */         XAException e = new XAException(msg);
/*  407 */         e.errorCode = res;
/*  408 */         if (Trace.isOn) {
/*  409 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "commit(Xid,boolean)", e, 4);
/*      */         }
/*      */         
/*  412 */         throw e;
/*      */       }
/*      */     
/*      */     }
/*  416 */     catch (XAException e) {
/*  417 */       if (Trace.isOn) {
/*  418 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "commit(Xid,boolean)", e, 3);
/*      */       }
/*      */       
/*  421 */       if (Trace.isOn) {
/*  422 */         Trace.traceData(this, "errorCode=" + e.errorCode, null);
/*      */       }
/*  424 */       if (Trace.isOn) {
/*  425 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "commit(Xid,boolean)", e, 5);
/*      */       }
/*      */       
/*  428 */       throw e;
/*      */     } 
/*  430 */     if (Trace.isOn) {
/*  431 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "commit(Xid,boolean)");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void end(Xid xid, int flags) throws XAException {
/*  468 */     if (Trace.isOn) {
/*  469 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "end(Xid,int)", new Object[] { xid, 
/*  470 */             Integer.valueOf(flags) });
/*      */     }
/*      */     
/*      */     try {
/*      */       int res;
/*      */       
/*  476 */       if (this.debugXA) {
/*  477 */         System.out.println(this + " end(" + xid + ")");
/*      */       }
/*      */       
/*  480 */       if (this.closed) {
/*  481 */         String msg = MQException.getNLSMsg("MID_ResourceClosed");
/*  482 */         XAException e = new XAException(msg);
/*  483 */         e.errorCode = -6;
/*  484 */         if (Trace.isOn) {
/*  485 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "end(Xid,int)", e, 1);
/*      */         }
/*      */         
/*  488 */         throw e;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  495 */         res = this.session.XAEND(xid, this.rmid, flags);
/*      */       }
/*  497 */       catch (RuntimeException e) {
/*  498 */         if (Trace.isOn) {
/*  499 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "end(Xid,int)", e, 1);
/*      */         }
/*      */         
/*  502 */         if (Trace.isOn) {
/*  503 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "end(Xid,int)", e, 2);
/*      */         }
/*      */         
/*  506 */         throw e;
/*      */       }
/*  508 */       catch (Exception e) {
/*  509 */         if (Trace.isOn) {
/*  510 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "end(Xid,int)", e, 2);
/*      */         }
/*      */         
/*  513 */         XAException traceRet1 = (XAException)e;
/*  514 */         if (Trace.isOn) {
/*  515 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "end(Xid,int)", traceRet1, 3);
/*      */         }
/*      */         
/*  518 */         throw traceRet1;
/*      */       } 
/*      */       
/*  521 */       if (res != 0) {
/*  522 */         String[] inserts = { "xa_end", Integer.toString(res) };
/*  523 */         String msg = JmqiResource.getString("MQJE114", inserts);
/*  524 */         XAException e = new XAException(msg);
/*  525 */         e.errorCode = res;
/*  526 */         if (Trace.isOn) {
/*  527 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "end(Xid,int)", e, 4);
/*      */         }
/*      */         
/*  530 */         throw e;
/*      */       }
/*      */     
/*      */     }
/*  534 */     catch (XAException e) {
/*  535 */       if (Trace.isOn) {
/*  536 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "end(Xid,int)", e, 3);
/*      */       }
/*      */       
/*  539 */       if (Trace.isOn) {
/*  540 */         Trace.traceData(this, "errorCode=" + e.errorCode, null);
/*      */       }
/*  542 */       if (Trace.isOn) {
/*  543 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "end(Xid,int)", e, 5);
/*      */       }
/*      */       
/*  546 */       throw e;
/*      */     } 
/*      */ 
/*      */     
/*  550 */     if (Trace.isOn) {
/*  551 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "end(Xid,int)");
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
/*      */   public void forget(Xid xid) throws XAException {
/*  568 */     if (Trace.isOn) {
/*  569 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "forget(Xid)", new Object[] { xid });
/*      */     }
/*      */     
/*      */     try {
/*      */       int res;
/*      */       
/*  575 */       if (this.debugXA) {
/*  576 */         System.out.println(this + " forget(" + xid + ")");
/*      */       }
/*      */       
/*  579 */       if (this.closed) {
/*  580 */         String msg = MQException.getNLSMsg("MID_ResourceClosed");
/*  581 */         XAException e = new XAException(msg);
/*  582 */         e.errorCode = -6;
/*  583 */         if (Trace.isOn) {
/*  584 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "forget(Xid)", e, 1);
/*      */         }
/*      */         
/*  587 */         throw e;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  594 */         res = this.session.XAFORGET(xid, this.rmid, 0);
/*      */       }
/*  596 */       catch (RuntimeException e) {
/*  597 */         if (Trace.isOn) {
/*  598 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "forget(Xid)", e, 1);
/*      */         }
/*      */         
/*  601 */         if (Trace.isOn) {
/*  602 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "forget(Xid)", e, 2);
/*      */         }
/*      */         
/*  605 */         throw e;
/*      */       }
/*  607 */       catch (Exception e) {
/*  608 */         if (Trace.isOn) {
/*  609 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "forget(Xid)", e, 2);
/*      */         }
/*      */         
/*  612 */         XAException traceRet1 = (XAException)e;
/*  613 */         if (Trace.isOn) {
/*  614 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "forget(Xid)", traceRet1, 3);
/*      */         }
/*      */         
/*  617 */         throw traceRet1;
/*      */       } 
/*      */       
/*  620 */       if (res != 0) {
/*  621 */         String[] inserts = { "xa_forget", Integer.toString(res) };
/*  622 */         String msg = JmqiResource.getString("MQJE114", inserts);
/*  623 */         XAException e = new XAException(msg);
/*  624 */         e.errorCode = res;
/*  625 */         if (Trace.isOn) {
/*  626 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "forget(Xid)", e, 4);
/*      */         }
/*      */         
/*  629 */         throw e;
/*      */       }
/*      */     
/*      */     }
/*  633 */     catch (XAException e) {
/*  634 */       if (Trace.isOn) {
/*  635 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "forget(Xid)", e, 3);
/*      */       }
/*      */       
/*  638 */       if (Trace.isOn) {
/*  639 */         Trace.traceData(this, "errorCode=" + e.errorCode, null);
/*      */       }
/*  641 */       if (Trace.isOn) {
/*  642 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "forget(Xid)", e, 5);
/*      */       }
/*      */       
/*  645 */       throw e;
/*      */     } 
/*      */     
/*  648 */     if (Trace.isOn) {
/*  649 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "forget(Xid)");
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
/*      */   public String getResourceString() {
/*  667 */     if (Trace.isOn) {
/*  668 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "getResourceString()", "getter", this.qmgrName);
/*      */     }
/*      */     
/*  671 */     return this.qmgrName;
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
/*      */   public int getTransactionTimeout() throws XAException {
/*  684 */     int traceRet1 = -1;
/*  685 */     if (Trace.isOn) {
/*  686 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "getTransactionTimeout()", "getter", 
/*  687 */           Integer.valueOf(traceRet1));
/*      */     }
/*  689 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSameRM(XAResource xares) throws XAException {
/*  700 */     if (Trace.isOn) {
/*  701 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "isSameRM(XAResource)", new Object[] { xares });
/*      */     }
/*      */     
/*  704 */     if (this.debugXA) {
/*  705 */       System.out.println(this + " isSameRM()");
/*      */     }
/*      */     
/*  708 */     if (this.closed) {
/*  709 */       String msg = MQException.getNLSMsg("MID_ResourceClosed");
/*  710 */       XAException e = new XAException(msg);
/*  711 */       e.errorCode = -6;
/*  712 */       if (Trace.isOn) {
/*  713 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "isSameRM(XAResource)", e);
/*      */       }
/*      */       
/*  716 */       throw e;
/*      */     } 
/*      */     
/*  719 */     if (xares instanceof MQXAResource) {
/*  720 */       boolean traceRet1 = this.qmgrName.equals(((MQXAResource)xares).qmgrName);
/*  721 */       if (Trace.isOn) {
/*  722 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "isSameRM(XAResource)", 
/*  723 */             Boolean.valueOf(traceRet1), 1);
/*      */       }
/*  725 */       return traceRet1;
/*      */     } 
/*      */     
/*  728 */     if (Trace.isOn) {
/*  729 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "isSameRM(XAResource)", 
/*  730 */           Boolean.valueOf(false), 2);
/*      */     }
/*  732 */     return false;
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
/*      */   public int prepare(Xid xid) throws XAException {
/*  755 */     if (Trace.isOn) {
/*  756 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "prepare(Xid)", new Object[] { xid });
/*      */     }
/*      */     
/*      */     try {
/*      */       int res;
/*      */       
/*  762 */       if (this.debugXA) {
/*  763 */         System.out.println(this + " prepare(" + xid + ")");
/*      */       }
/*      */       
/*  766 */       if (this.closed) {
/*  767 */         String msg = MQException.getNLSMsg("MID_ResourceClosed");
/*  768 */         XAException e = new XAException(msg);
/*  769 */         e.errorCode = -6;
/*  770 */         if (Trace.isOn) {
/*  771 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "prepare(Xid)", e, 1);
/*      */         }
/*      */         
/*  774 */         throw e;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  781 */         res = this.session.XAPREPARE(xid, this.rmid, 0);
/*      */       }
/*  783 */       catch (RuntimeException e) {
/*  784 */         if (Trace.isOn) {
/*  785 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "prepare(Xid)", e, 1);
/*      */         }
/*      */         
/*  788 */         if (Trace.isOn) {
/*  789 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "prepare(Xid)", e, 2);
/*      */         }
/*      */         
/*  792 */         throw e;
/*      */       }
/*  794 */       catch (Exception e) {
/*  795 */         if (Trace.isOn) {
/*  796 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "prepare(Xid)", e, 2);
/*      */         }
/*      */         
/*  799 */         XAException traceRet1 = (XAException)e;
/*  800 */         if (Trace.isOn) {
/*  801 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "prepare(Xid)", traceRet1, 3);
/*      */         }
/*      */         
/*  804 */         throw traceRet1;
/*      */       } 
/*      */       
/*  807 */       if (res != 0 && res != 3) {
/*  808 */         String[] inserts = { "xa_prepare", Integer.toString(res) };
/*  809 */         String msg = JmqiResource.getString("MQJE114", inserts);
/*  810 */         XAException e = new XAException(msg);
/*  811 */         e.errorCode = res;
/*  812 */         if (Trace.isOn) {
/*  813 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "prepare(Xid)", e, 4);
/*      */         }
/*      */         
/*  816 */         throw e;
/*      */       } 
/*  818 */       if (Trace.isOn) {
/*  819 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "prepare(Xid)", 
/*  820 */             Integer.valueOf(res));
/*      */       }
/*  822 */       return res;
/*      */     
/*      */     }
/*  825 */     catch (XAException e) {
/*  826 */       if (Trace.isOn) {
/*  827 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "prepare(Xid)", e, 3);
/*      */       }
/*      */       
/*  830 */       if (Trace.isOn) {
/*  831 */         Trace.traceData(this, "errorCode=" + e.errorCode, null);
/*      */       }
/*  833 */       if (Trace.isOn) {
/*  834 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "prepare(Xid)", e, 5);
/*      */       }
/*      */       
/*  837 */       throw e;
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
/*      */   public Xid[] recover(int flag) throws XAException {
/*  889 */     if (Trace.isOn) {
/*  890 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "recover(int)", new Object[] {
/*  891 */             Integer.valueOf(flag)
/*      */           });
/*      */     }
/*  894 */     Xid[] xids = new Xid[10];
/*      */     
/*      */     try {
/*      */       int res;
/*      */       
/*      */       Xid[] result;
/*      */       int internalFlag;
/*  901 */       if (this.debugXA) {
/*  902 */         System.out.println(this + " recover()");
/*      */       }
/*      */       
/*  905 */       if (this.closed) {
/*  906 */         String msg = MQException.getNLSMsg("MID_ResourceClosed");
/*  907 */         XAException e = new XAException(msg);
/*  908 */         e.errorCode = -6;
/*  909 */         if (Trace.isOn) {
/*  910 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "recover(int)", e, 1);
/*      */         }
/*      */         
/*  913 */         throw e;
/*      */       } 
/*      */ 
/*      */       
/*  917 */       boolean prevImpEndFlag = this.impEndFlag;
/*  918 */       this.impEndFlag = false;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  923 */       switch (flag) {
/*      */         case 8388608:
/*  925 */           internalFlag = 0;
/*      */           break;
/*      */         case 25165824:
/*  928 */           internalFlag = 16777216;
/*      */           break;
/*      */         default:
/*  931 */           internalFlag = flag;
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  939 */         res = this.session.XARECOVER(xids, this.rmid, internalFlag);
/*      */       }
/*  941 */       catch (RuntimeException e) {
/*  942 */         if (Trace.isOn) {
/*  943 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "recover(int)", e, 1);
/*      */         }
/*      */         
/*  946 */         if (Trace.isOn) {
/*  947 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "recover(int)", e, 2);
/*      */         }
/*      */         
/*  950 */         throw e;
/*      */       }
/*  952 */       catch (Exception e) {
/*  953 */         if (Trace.isOn) {
/*  954 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "recover(int)", e, 2);
/*      */         }
/*      */         
/*  957 */         XAException traceRet1 = (XAException)e;
/*  958 */         if (Trace.isOn) {
/*  959 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "recover(int)", traceRet1, 3);
/*      */         }
/*      */         
/*  962 */         throw traceRet1;
/*      */       } 
/*      */       
/*  965 */       if (res < 0) {
/*  966 */         if (prevImpEndFlag && res == -5 && flag == 0) {
/*      */           
/*  968 */           Xid[] traceRet2 = new Xid[0];
/*  969 */           if (Trace.isOn) {
/*  970 */             Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "recover(int)", traceRet2, 1);
/*      */           }
/*      */           
/*  973 */           return traceRet2;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  978 */         String[] inserts = { "xa_recover", Integer.toString(res) };
/*  979 */         String msg = JmqiResource.getString("MQJE114", inserts);
/*  980 */         XAException e = new XAException(msg);
/*  981 */         e.errorCode = res;
/*  982 */         if (Trace.isOn) {
/*  983 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "recover(int)", e, 4);
/*      */         }
/*      */         
/*  986 */         throw e;
/*      */       } 
/*      */       
/*  989 */       if (res != xids.length) {
/*      */         
/*  991 */         result = new Xid[res];
/*  992 */         System.arraycopy(xids, 0, result, 0, res);
/*  993 */         if ((flag & 0x800000) == 0)
/*      */         {
/*  995 */           this.impEndFlag = true;
/*      */         
/*      */         }
/*      */       }
/*      */       else {
/*      */         
/* 1001 */         Vector<Xid> xidVec = new Vector<>();
/* 1002 */         boolean gotLastXid = false;
/*      */ 
/*      */         
/* 1005 */         internalFlag = 0;
/*      */         
/* 1007 */         while (!gotLastXid) {
/*      */           
/* 1009 */           for (int j = 0; j < res; j++) {
/* 1010 */             xidVec.addElement(xids[j]);
/* 1011 */             xids[j] = null;
/*      */           } 
/*      */ 
/*      */           
/*      */           try {
/* 1016 */             res = this.session.XARECOVER(xids, this.rmid, internalFlag);
/*      */           }
/* 1018 */           catch (RuntimeException e) {
/* 1019 */             if (Trace.isOn) {
/* 1020 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "recover(int)", e, 3);
/*      */             }
/*      */ 
/*      */             
/* 1024 */             if (Trace.isOn) {
/* 1025 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "recover(int)", e, 5);
/*      */             }
/*      */             
/* 1028 */             throw e;
/*      */           
/*      */           }
/* 1031 */           catch (Exception e) {
/* 1032 */             if (Trace.isOn) {
/* 1033 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "recover(int)", e, 4);
/*      */             }
/*      */ 
/*      */             
/* 1037 */             XAException traceRet3 = (XAException)e;
/* 1038 */             if (Trace.isOn) {
/* 1039 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "recover(int)", traceRet3, 6);
/*      */             }
/*      */             
/* 1042 */             throw traceRet3;
/*      */           } 
/*      */ 
/*      */           
/* 1046 */           if (res < 0) {
/* 1047 */             String[] inserts = { "xa_recover", Integer.toString(res) };
/* 1048 */             String msg = JmqiResource.getString("MQJE114", inserts);
/* 1049 */             XAException e = new XAException(msg);
/* 1050 */             e.errorCode = res;
/* 1051 */             if (Trace.isOn) {
/* 1052 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "recover(int)", e, 7);
/*      */             }
/*      */             
/* 1055 */             throw e;
/*      */           } 
/*      */           
/* 1058 */           if (res != xids.length) {
/* 1059 */             gotLastXid = true;
/* 1060 */             if ((flag & 0x800000) == 0)
/*      */             {
/*      */               
/* 1063 */               this.impEndFlag = true;
/*      */             }
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/* 1069 */         for (int i = 0; i < res; i++) {
/* 1070 */           xidVec.addElement(xids[i]);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1076 */         result = xidVec.<Xid>toArray(xids);
/*      */       } 
/*      */       
/* 1079 */       if (Trace.isOn) {
/* 1080 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "recover(int)", result, 2);
/*      */       }
/*      */       
/* 1083 */       return result;
/*      */     
/*      */     }
/* 1086 */     catch (XAException e) {
/* 1087 */       if (Trace.isOn) {
/* 1088 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "recover(int)", e, 5);
/*      */       }
/*      */       
/* 1091 */       if (Trace.isOn) {
/* 1092 */         Trace.traceData(this, "errorCode=" + e.errorCode, null);
/*      */       }
/* 1094 */       if (Trace.isOn) {
/* 1095 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "recover(int)", e, 8);
/*      */       }
/*      */       
/* 1098 */       throw e;
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
/*      */   public void rollback(Xid xid) throws XAException {
/* 1116 */     if (Trace.isOn) {
/* 1117 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "rollback(Xid)", new Object[] { xid });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*      */       int res;
/*      */       
/* 1124 */       if (this.debugXA) {
/* 1125 */         System.out.println(this + " rollback(" + xid + ")");
/*      */       }
/*      */       
/* 1128 */       if (this.closed) {
/* 1129 */         String msg = MQException.getNLSMsg("MID_ResourceClosed");
/* 1130 */         XAException e = new XAException(msg);
/* 1131 */         e.errorCode = -6;
/* 1132 */         if (Trace.isOn) {
/* 1133 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "rollback(Xid)", e, 1);
/*      */         }
/*      */         
/* 1136 */         throw e;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1144 */         res = this.session.XAROLLBACK(xid, this.rmid, 0);
/*      */       }
/* 1146 */       catch (RuntimeException e) {
/* 1147 */         if (Trace.isOn) {
/* 1148 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "rollback(Xid)", e, 1);
/*      */         }
/*      */         
/* 1151 */         if (Trace.isOn) {
/* 1152 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "rollback(Xid)", e, 2);
/*      */         }
/*      */         
/* 1155 */         throw e;
/*      */       }
/* 1157 */       catch (Exception e) {
/* 1158 */         if (Trace.isOn) {
/* 1159 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "rollback(Xid)", e, 2);
/*      */         }
/*      */         
/* 1162 */         XAException traceRet1 = (XAException)e;
/* 1163 */         if (Trace.isOn) {
/* 1164 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "rollback(Xid)", traceRet1, 3);
/*      */         }
/*      */         
/* 1167 */         throw traceRet1;
/*      */       } 
/*      */       
/* 1170 */       if (res != 0) {
/* 1171 */         String[] inserts = { "xa_rollback", Integer.toString(res) };
/* 1172 */         String msg = JmqiResource.getString("MQJE114", inserts);
/* 1173 */         XAException e = new XAException(msg);
/* 1174 */         e.errorCode = res;
/* 1175 */         if (Trace.isOn) {
/* 1176 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "rollback(Xid)", e, 4);
/*      */         }
/*      */         
/* 1179 */         throw e;
/*      */       }
/*      */     
/*      */     }
/* 1183 */     catch (XAException e) {
/* 1184 */       if (Trace.isOn) {
/* 1185 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "rollback(Xid)", e, 3);
/*      */       }
/*      */       
/* 1188 */       if (Trace.isOn) {
/* 1189 */         Trace.traceData(this, "errorCode=" + e.errorCode, null);
/*      */       }
/* 1191 */       if (Trace.isOn) {
/* 1192 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "rollback(Xid)", e, 5);
/*      */       }
/*      */       
/* 1195 */       throw e;
/*      */     } 
/* 1197 */     if (Trace.isOn) {
/* 1198 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "rollback(Xid)");
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
/*      */   public void setDebugXA(boolean x) {
/* 1214 */     if (Trace.isOn) {
/* 1215 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "setDebugXA(boolean)", "setter", 
/* 1216 */           Boolean.valueOf(x));
/*      */     }
/* 1218 */     this.debugXA = x;
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
/*      */   public boolean setTransactionTimeout(int seconds) throws XAException {
/* 1235 */     if (Trace.isOn)
/* 1236 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "setTransactionTimeout(int)", new Object[] {
/* 1237 */             Integer.valueOf(seconds)
/*      */           }); 
/* 1239 */     if (Trace.isOn) {
/* 1240 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "setTransactionTimeout(int)", 
/* 1241 */           Boolean.valueOf(false));
/*      */     }
/* 1243 */     return false;
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
/*      */   public void start(Xid xid, int flags) throws XAException {
/* 1262 */     if (Trace.isOn) {
/* 1263 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "start(Xid,int)", new Object[] { xid, 
/* 1264 */             Integer.valueOf(flags) });
/*      */     }
/*      */     
/*      */     try {
/*      */       int res;
/*      */       
/* 1270 */       if (this.debugXA) {
/* 1271 */         System.out.println(this + " start(" + xid + ")");
/*      */       }
/*      */       
/* 1274 */       if (this.closed) {
/* 1275 */         String msg = MQException.getNLSMsg("MID_ResourceClosed");
/* 1276 */         XAException e = new XAException(msg);
/* 1277 */         e.errorCode = -6;
/* 1278 */         if (Trace.isOn) {
/* 1279 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "start(Xid,int)", e, 1);
/*      */         }
/*      */         
/* 1282 */         throw e;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1289 */         res = this.session.XASTART(xid, this.rmid, flags);
/*      */       }
/* 1291 */       catch (RuntimeException e) {
/* 1292 */         if (Trace.isOn) {
/* 1293 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "start(Xid,int)", e, 1);
/*      */         }
/*      */         
/* 1296 */         if (Trace.isOn) {
/* 1297 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "start(Xid,int)", e, 2);
/*      */         }
/*      */         
/* 1300 */         throw e;
/*      */       }
/* 1302 */       catch (Exception e) {
/* 1303 */         if (Trace.isOn) {
/* 1304 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "start(Xid,int)", e, 2);
/*      */         }
/*      */         
/* 1307 */         XAException traceRet1 = (XAException)e;
/* 1308 */         if (Trace.isOn) {
/* 1309 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "start(Xid,int)", traceRet1, 3);
/*      */         }
/*      */         
/* 1312 */         throw traceRet1;
/*      */       } 
/*      */       
/* 1315 */       if (res != 0) {
/* 1316 */         String[] inserts = { "xa_start", Integer.toString(res) };
/* 1317 */         String msg = JmqiResource.getString("MQJE114", inserts);
/* 1318 */         XAException e = new XAException(msg);
/* 1319 */         e.errorCode = res;
/* 1320 */         if (Trace.isOn) {
/* 1321 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "start(Xid,int)", e, 4);
/*      */         }
/*      */         
/* 1324 */         throw e;
/*      */       }
/*      */     
/*      */     }
/* 1328 */     catch (XAException e) {
/* 1329 */       if (Trace.isOn) {
/* 1330 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "start(Xid,int)", e, 3);
/*      */       }
/*      */       
/* 1333 */       if (Trace.isOn) {
/* 1334 */         Trace.traceData(this, "errorCode=" + e.errorCode, null);
/*      */       }
/* 1336 */       if (Trace.isOn) {
/* 1337 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "start(Xid,int)", e, 5);
/*      */       }
/*      */       
/* 1340 */       throw e;
/*      */     } 
/*      */     
/* 1343 */     if (Trace.isOn)
/* 1344 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAResource", "start(Xid,int)"); 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\base\internal\MQXAResource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */