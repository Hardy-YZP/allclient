/*     */ package com.ibm.mq;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.mq.jmqi.JmqiXA;
/*     */ import com.ibm.mq.jmqi.JmqiXAResource;
/*     */ import com.ibm.mq.jmqi.handles.Hconn;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import javax.transaction.xa.XAException;
/*     */ import javax.transaction.xa.XAResource;
/*     */ import javax.transaction.xa.Xid;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class spiMQXAResource
/*     */   extends JmqiObject
/*     */   implements XAResource
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/spiMQXAResource.java";
/*     */   static final String copyright_notice = "Licensed Materials - Property of IBM 5724-H72, 5655-R36, 5724-L26, 5655-L82                (c) Copyright IBM Corp. 2008, 2011 All Rights Reserved. US Government Users Restricted Rights - Use, duplication or disclosure restricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   Hconn hConn;
/*     */   private JmqiXAResource jmqiXaResource;
/*     */   
/*     */   static {
/*  67 */     if (Trace.isOn) {
/*  68 */       Trace.data("com.ibm.mq.MQXAResource", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/spiMQXAResource.java");
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
/*     */ 
/*     */   
/*     */   protected spiMQXAResource() {
/*  98 */     super(null);
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
/*     */   protected spiMQXAResource(MQSESSION session, Hconn hConn) throws XAException {
/* 111 */     super(MQSESSION.getJmqiEnv());
/* 112 */     if (Trace.isOn) {
/* 113 */       Trace.entry(this, "com.ibm.mq.MQXAResource", "<init>(MQSESSION,Hconn)", new Object[] { session, hConn });
/*     */     }
/*     */ 
/*     */     
/* 117 */     this.hConn = hConn;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 122 */     JmqiXA xaApi = (JmqiXA)session.getJmqi();
/* 123 */     this.jmqiXaResource = MQSESSION.getJmqiEnv().newJmqiXAResource(xaApi, hConn);
/*     */     
/* 125 */     if (Trace.isOn) {
/* 126 */       Trace.exit(this, "com.ibm.mq.MQXAResource", "<init>(MQSESSION,Hconn)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private JmqiXAResource getJmqiXAResource() {
/* 137 */     if (Trace.isOn) {
/* 138 */       Trace.data(this, "com.ibm.mq.MQXAResource", "getJmqiXAResource()", "getter", this.jmqiXaResource);
/*     */     }
/*     */     
/* 141 */     return this.jmqiXaResource;
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
/*     */   public void close() throws XAException {
/* 155 */     if (Trace.isOn) {
/* 156 */       Trace.entry(this, "com.ibm.mq.MQXAResource", "close()");
/*     */     }
/*     */     
/* 159 */     this.jmqiXaResource.close();
/*     */     
/* 161 */     if (Trace.isOn) {
/* 162 */       Trace.exit(this, "com.ibm.mq.MQXAResource", "close()");
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
/*     */   public int prepare(Xid xid) throws XAException {
/* 188 */     if (Trace.isOn) {
/* 189 */       Trace.entry(this, "com.ibm.mq.MQXAResource", "prepare(Xid)", new Object[] { xid });
/*     */     }
/*     */     
/* 192 */     int retval = this.jmqiXaResource.prepare(xid);
/*     */     
/* 194 */     if (Trace.isOn) {
/* 195 */       Trace.exit(this, "com.ibm.mq.MQXAResource", "prepare(Xid)", Integer.valueOf(retval));
/*     */     }
/* 197 */     return retval;
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
/*     */   public void commit(Xid xid, boolean onePhase) throws XAException {
/* 219 */     if (Trace.isOn) {
/* 220 */       Trace.entry(this, "com.ibm.mq.MQXAResource", "commit(Xid,boolean)", new Object[] { xid, 
/* 221 */             Boolean.valueOf(onePhase) });
/*     */     }
/*     */     
/* 224 */     this.jmqiXaResource.commit(xid, onePhase);
/*     */     
/* 226 */     if (Trace.isOn) {
/* 227 */       Trace.exit(this, "com.ibm.mq.MQXAResource", "commit(Xid,boolean)");
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
/*     */   public void rollback(Xid xid) throws XAException {
/* 242 */     if (Trace.isOn) {
/* 243 */       Trace.entry(this, "com.ibm.mq.MQXAResource", "rollback(Xid)", new Object[] { xid });
/*     */     }
/*     */     
/* 246 */     this.jmqiXaResource.rollback(xid);
/*     */     
/* 248 */     if (Trace.isOn) {
/* 249 */       Trace.exit(this, "com.ibm.mq.MQXAResource", "rollback(Xid)");
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
/*     */   public void start(Xid xid, int flags) throws XAException {
/* 266 */     if (Trace.isOn) {
/* 267 */       Trace.entry(this, "com.ibm.mq.MQXAResource", "start(Xid,int)", new Object[] { xid, 
/* 268 */             Integer.valueOf(flags) });
/*     */     }
/*     */     
/* 271 */     this.jmqiXaResource.start(xid, flags);
/*     */     
/* 273 */     if (Trace.isOn) {
/* 274 */       Trace.exit(this, "com.ibm.mq.MQXAResource", "start(Xid,int)");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void end(Xid xid, int flags) throws XAException {
/* 308 */     if (Trace.isOn) {
/* 309 */       Trace.entry(this, "com.ibm.mq.MQXAResource", "end(Xid,int)", new Object[] { xid, 
/* 310 */             Integer.valueOf(flags) });
/*     */     }
/*     */     
/* 313 */     this.jmqiXaResource.end(xid, flags);
/*     */     
/* 315 */     if (Trace.isOn) {
/* 316 */       Trace.exit(this, "com.ibm.mq.MQXAResource", "end(Xid,int)");
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
/*     */   public void forget(Xid xid) throws XAException {
/* 332 */     if (Trace.isOn) {
/* 333 */       Trace.entry(this, "com.ibm.mq.MQXAResource", "forget(Xid)", new Object[] { xid });
/*     */     }
/*     */     
/* 336 */     this.jmqiXaResource.forget(xid);
/*     */     
/* 338 */     if (Trace.isOn) {
/* 339 */       Trace.exit(this, "com.ibm.mq.MQXAResource", "forget(Xid)");
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
/*     */   public Xid[] recover(int flags) throws XAException {
/* 365 */     if (Trace.isOn) {
/* 366 */       Trace.entry(this, "com.ibm.mq.MQXAResource", "recover(int)", new Object[] {
/* 367 */             Integer.valueOf(flags)
/*     */           });
/*     */     }
/* 370 */     Xid[] xids = this.jmqiXaResource.recover(flags);
/*     */     
/* 372 */     if (Trace.isOn) {
/* 373 */       Trace.exit(this, "com.ibm.mq.MQXAResource", "recover(int)", xids);
/*     */     }
/* 375 */     return xids;
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
/*     */   public boolean isSameRM(XAResource xares) throws XAException {
/*     */     boolean retval;
/* 389 */     if (Trace.isOn) {
/* 390 */       Trace.entry(this, "com.ibm.mq.MQXAResource", "isSameRM(XAResource)", new Object[] { xares });
/*     */     }
/* 392 */     String fid = "isSameRM(XAResource)";
/*     */     
/* 394 */     JmqiXAResource jmqiXAResource2 = null;
/* 395 */     if (xares instanceof JmqiXAResource) {
/*     */       
/* 397 */       jmqiXAResource2 = (JmqiXAResource)xares;
/*     */     }
/* 399 */     else if (xares instanceof spiMQXAResource) {
/*     */       
/* 401 */       jmqiXAResource2 = ((spiMQXAResource)xares).getJmqiXAResource();
/*     */     } 
/*     */ 
/*     */     
/* 405 */     if (jmqiXAResource2 == null) {
/* 406 */       if (Trace.isOn) {
/* 407 */         Trace.data(this, "isSameRM(XAResource)", "Unable to obtain JMQI XAResource from " + xares.toString());
/*     */       }
/* 409 */       retval = false;
/*     */     }
/*     */     else {
/*     */       
/* 413 */       retval = this.jmqiXaResource.isSameRM((XAResource)jmqiXAResource2);
/*     */     } 
/*     */     
/* 416 */     if (Trace.isOn) {
/* 417 */       Trace.exit(this, "com.ibm.mq.MQXAResource", "isSameRM(XAResource)", Boolean.valueOf(retval));
/*     */     }
/*     */     
/* 420 */     return retval;
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
/*     */   public void setDebugXA(boolean x) {
/* 434 */     if (Trace.isOn) {
/* 435 */       Trace.data(this, "com.ibm.mq.MQXAResource", "setDebugXA(boolean)", "setter", 
/* 436 */           Boolean.valueOf(x));
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
/*     */   public String getResourceString() {
/*     */     String uid;
/* 455 */     if (Trace.isOn) {
/* 456 */       Trace.entry(this, "com.ibm.mq.MQXAResource", "getResourceString()");
/*     */     }
/*     */     
/*     */     try {
/* 460 */       uid = this.hConn.getUid();
/*     */     }
/* 462 */     catch (Exception e) {
/* 463 */       if (Trace.isOn) {
/* 464 */         Trace.catchBlock(this, "com.ibm.mq.MQXAResource", "getResourceString()", e);
/*     */       }
/*     */       
/* 467 */       uid = "";
/*     */     } 
/* 469 */     if (Trace.isOn) {
/* 470 */       Trace.exit(this, "com.ibm.mq.MQXAResource", "getResourceString()", uid);
/*     */     }
/* 472 */     return uid;
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
/*     */   public boolean setTransactionTimeout(int seconds) throws XAException {
/* 489 */     if (Trace.isOn)
/* 490 */       Trace.entry(this, "com.ibm.mq.MQXAResource", "setTransactionTimeout(int)", new Object[] {
/* 491 */             Integer.valueOf(seconds)
/*     */           }); 
/* 493 */     if (Trace.isOn) {
/* 494 */       Trace.exit(this, "com.ibm.mq.MQXAResource", "setTransactionTimeout(int)", 
/* 495 */           Boolean.valueOf(false));
/*     */     }
/* 497 */     return false;
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
/*     */   public int getTransactionTimeout() throws XAException {
/* 512 */     int traceRet1 = -1;
/* 513 */     if (Trace.isOn) {
/* 514 */       Trace.data(this, "com.ibm.mq.MQXAResource", "getTransactionTimeout()", "getter", 
/* 515 */           Integer.valueOf(traceRet1));
/*     */     }
/* 517 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\spiMQXAResource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */