/*     */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiXAResource;
/*     */ import com.ibm.mq.jms.SessionClosedException;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.provider.ProviderSession;
/*     */ import com.ibm.msg.client.provider.ProviderXASession;
/*     */ import com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager;
/*     */ import java.io.IOException;
/*     */ import java.io.NotSerializableException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import javax.jms.IllegalStateException;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.TransactionInProgressException;
/*     */ import javax.transaction.xa.XAException;
/*     */ import javax.transaction.xa.XAResource;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQXASession
/*     */   extends MQSession
/*     */   implements ProviderXASession
/*     */ {
/*     */   private static final long serialVersionUID = -5044711013870900284L;
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQXASession.java";
/*     */   private XAResource resource;
/*     */   
/*     */   static {
/*  94 */     if (Trace.isOn) {
/*  95 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.MQXASession", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQXASession.java");
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
/* 117 */   private MQSession session = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQXASession(MQConnection connection, MQQueueManager qm, MQSession session, JmqiXAResource resource) throws JMSException {
/* 128 */     super(connection, qm, true, 0);
/* 129 */     if (Trace.isOn) {
/* 130 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQXASession", "<init>(MQConnection,MQQueueManager,MQSession,JmqiXAResource)", new Object[] { connection, qm, session, resource });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 135 */     this.session = session;
/* 136 */     setQM(qm);
/* 137 */     this.resource = (XAResource)resource;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 148 */     if (Trace.isOn) {
/* 149 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQXASession", "Set MQXASession resolved variables to those within the session");
/*     */     }
/*     */     
/* 152 */     if (Trace.isOn) {
/* 153 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQXASession", "<init>(MQConnection,MQQueueManager,MQSession,JmqiXAResource)");
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
/*     */   public XAResource getXAResource() {
/* 170 */     if (Trace.isOn) {
/* 171 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQXASession", "getXAResource()", "getter", this.resource);
/*     */     }
/*     */     
/* 174 */     return this.resource;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isXASessionActive() {
/* 185 */     boolean isActive = false;
/* 186 */     if (this.resource != null) {
/* 187 */       isActive = ((JmqiXAResource)this.resource).getActiveState();
/*     */     }
/* 189 */     if (Trace.isOn) {
/* 190 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQXASession", "isXASessionActive()", "getter", 
/* 191 */           Boolean.valueOf(isActive));
/*     */     }
/* 193 */     return isActive;
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
/*     */   public boolean getTransacted() throws JMSException {
/* 207 */     if (Trace.isOn) {
/* 208 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQXASession", "getTransacted()", "getter", 
/* 209 */           Boolean.valueOf(true));
/*     */     }
/* 211 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void close(boolean closingFromConnection) throws JMSException {
/* 222 */     if (Trace.isOn) {
/* 223 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQXASession", "close(boolean)", new Object[] {
/* 224 */             Boolean.valueOf(closingFromConnection)
/*     */           });
/*     */     }
/*     */     
/*     */     try {
/* 229 */       ((JmqiXAResource)this.resource).close();
/*     */     }
/* 231 */     catch (XAException e) {
/* 232 */       if (Trace.isOn) {
/* 233 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQXASession", "close(boolean)", e, 1);
/*     */       }
/*     */       
/* 236 */       JMSException je = ConfigEnvironment.newException("MQJMS2012");
/* 237 */       je.setLinkedException(e);
/*     */       
/* 239 */       if (Trace.isOn) {
/* 240 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQXASession", "close(boolean)", (Throwable)je, 1);
/*     */       }
/*     */       
/* 243 */       throw je;
/*     */     } finally {
/*     */       
/* 246 */       if (Trace.isOn) {
/* 247 */         Trace.finallyBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQXASession", "close(boolean)");
/*     */       }
/*     */ 
/*     */       
/*     */       try {
/* 252 */         this.session.close(closingFromConnection);
/*     */ 
/*     */         
/* 255 */         this.session.discQM();
/*     */       
/*     */       }
/* 258 */       catch (JMSException e) {
/* 259 */         if (Trace.isOn) {
/* 260 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQXASession", "close(boolean)", (Throwable)e, 2);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 265 */         JMSException je = ConfigEnvironment.newException("MQJMS2012");
/* 266 */         je.setLinkedException((Exception)e);
/*     */         
/* 268 */         if (Trace.isOn) {
/* 269 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQXASession", "close(boolean)", (Throwable)je, 2);
/*     */         }
/*     */         
/* 272 */         throw je;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 278 */     if (Trace.isOn) {
/* 279 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQXASession", "close(boolean)");
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
/*     */   void acknowledge() throws JMSException {
/* 304 */     if (Trace.isOn) {
/* 305 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQXASession", "acknowledge()");
/*     */     }
/*     */     
/* 308 */     JMSException traceRet1 = ConfigEnvironment.newException("MQJMS1016", "MQXASession.acknowledge");
/* 309 */     if (Trace.isOn) {
/* 310 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQXASession", "acknowledge()", (Throwable)traceRet1);
/*     */     }
/*     */     
/* 313 */     throw traceRet1;
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
/*     */   public void commit() throws JMSException {
/* 329 */     if (Trace.isOn) {
/* 330 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQXASession", "commit()");
/*     */     }
/* 332 */     String msg = ConfigEnvironment.getErrorMessage("MQJMS1069");
/* 333 */     TransactionInProgressException traceRet1 = new TransactionInProgressException(msg);
/* 334 */     if (Trace.isOn) {
/* 335 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQXASession", "commit()", (Throwable)traceRet1);
/*     */     }
/*     */     
/* 338 */     throw traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void rollback() throws JMSException {
/* 349 */     if (Trace.isOn) {
/* 350 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQXASession", "rollback()");
/*     */     }
/* 352 */     String msg = ConfigEnvironment.getErrorMessage("MQJMS1069");
/* 353 */     TransactionInProgressException traceRet1 = new TransactionInProgressException(msg);
/* 354 */     if (Trace.isOn) {
/* 355 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQXASession", "rollback()", (Throwable)traceRet1);
/*     */     }
/*     */     
/* 358 */     throw traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void recover() throws JMSException {
/* 369 */     if (Trace.isOn) {
/* 370 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQXASession", "recover()");
/*     */     }
/* 372 */     String msg = ConfigEnvironment.getErrorMessage("MQJMS1069");
/* 373 */     IllegalStateException traceRet1 = new IllegalStateException(msg);
/* 374 */     if (Trace.isOn) {
/* 375 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQXASession", "recover()", (Throwable)traceRet1);
/*     */     }
/*     */     
/* 378 */     throw traceRet1;
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
/*     */   public ProviderSession getSession() throws JMSException {
/* 396 */     if (Trace.isOn) {
/* 397 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQXASession", "getSession()", "getter", this.session);
/*     */     }
/*     */     
/* 400 */     return this.session;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isStarted() {
/* 409 */     boolean traceRet1 = this.session.isStarted();
/* 410 */     if (Trace.isOn) {
/* 411 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQXASession", "isStarted()", "getter", 
/* 412 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 414 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isStopped() {
/* 423 */     boolean traceRet1 = this.session.isStopped();
/* 424 */     if (Trace.isOn) {
/* 425 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQXASession", "isStopped()", "getter", 
/* 426 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 428 */     return traceRet1;
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
/*     */   public void start() throws SessionClosedException, JMSException {
/* 444 */     if (Trace.isOn) {
/* 445 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQXASession", "start()");
/*     */     }
/*     */     
/* 448 */     super.start();
/* 449 */     this.session.start();
/* 450 */     if (Trace.isOn) {
/* 451 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQXASession", "start()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void stop() throws SessionClosedException, JMSException {
/* 461 */     if (Trace.isOn) {
/* 462 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQXASession", "stop()");
/*     */     }
/*     */     
/* 465 */     super.stop();
/* 466 */     this.session.stop();
/* 467 */     if (Trace.isOn) {
/* 468 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQXASession", "stop()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 478 */     if (Trace.isOn) {
/* 479 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQXASession", "readObject(ObjectInputStream)", new Object[] { in });
/*     */     }
/*     */     
/* 482 */     NotSerializableException traceRet1 = new NotSerializableException();
/* 483 */     if (Trace.isOn) {
/* 484 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQXASession", "readObject(ObjectInputStream)", traceRet1);
/*     */     }
/*     */     
/* 487 */     throw traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 496 */     if (Trace.isOn) {
/* 497 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQXASession", "writeObject(ObjectOutputStream)", new Object[] { out });
/*     */     }
/*     */     
/* 500 */     NotSerializableException traceRet1 = new NotSerializableException();
/* 501 */     if (Trace.isOn) {
/* 502 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQXASession", "writeObject(ObjectOutputStream)", traceRet1);
/*     */     }
/*     */     
/* 505 */     throw traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 514 */     if (Trace.isOn) {
/* 515 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQXASession", "hashCode()");
/*     */     }
/* 517 */     int traceRet1 = super.hashCode();
/* 518 */     if (Trace.isOn) {
/* 519 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQXASession", "hashCode()", 
/* 520 */           Integer.valueOf(traceRet1));
/*     */     }
/* 522 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 531 */     if (Trace.isOn) {
/* 532 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQXASession", "equals(Object)", new Object[] { o });
/*     */     }
/*     */     
/* 535 */     boolean traceRet1 = super.equals(o);
/* 536 */     if (Trace.isOn) {
/* 537 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQXASession", "equals(Object)", 
/* 538 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 540 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\MQXASession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */