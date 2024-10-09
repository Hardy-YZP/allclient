/*    */ package com.ibm.msg.client.jms.internal;
/*    */ 
/*    */ import com.ibm.msg.client.commonservices.trace.Trace;
/*    */ import javax.jms.CompletionListener;
/*    */ import javax.jms.Message;
/*    */ import javax.jms.Session;
/*    */ 
/*    */ class JmsCompletionListenerWrapper implements CompletionListener {
/*    */   protected CompletionListener delegate;
/*    */   
/*    */   static {
/* 12 */     if (Trace.isOn) {
/* 13 */       Trace.data("com.ibm.msg.client.jms.internal.JmsCompletionListenerWrapper", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsCompletionListenerWrapper.java");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected Session session;
/*    */ 
/*    */   
/*    */   JmsCompletionListenerWrapper(CompletionListener delegate, Session session) {
/* 23 */     if (Trace.isOn) {
/* 24 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsCompletionListenerWrapper", "<init>(CompletionListener,Session)", new Object[] { delegate, session });
/*    */     }
/*    */ 
/*    */     
/* 28 */     this.delegate = delegate;
/* 29 */     this.session = session;
/*    */     
/* 31 */     if (Trace.isOn) {
/* 32 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsCompletionListenerWrapper", "<init>(CompletionListener,Session)");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   CompletionListener getDelegate() {
/* 39 */     if (Trace.isOn) {
/* 40 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsCompletionListenerWrapper", "getDelegate()", "getter", this.delegate);
/*    */     }
/*    */     
/* 43 */     return this.delegate;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onCompletion(Message message) {
/* 48 */     if (Trace.isOn) {
/* 49 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsCompletionListenerWrapper", "onCompletion(Message)", new Object[] { message });
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 54 */     JmsTls tls = JmsTls.getInstance();
/* 55 */     tls.inCompletionListener(true, this.session);
/* 56 */     this.delegate.onCompletion(message);
/* 57 */     tls.inCompletionListener(false, null);
/*    */     
/* 59 */     if (Trace.isOn) {
/* 60 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsCompletionListenerWrapper", "onCompletion(Message)");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onException(Message message, Exception exception) {
/* 68 */     if (Trace.isOn) {
/* 69 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsCompletionListenerWrapper", "onException(Message,Exception)", new Object[] { message, exception });
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 74 */     JmsTls tls = JmsTls.getInstance();
/* 75 */     tls.inCompletionListener(true, this.session);
/* 76 */     this.delegate.onException(message, exception);
/* 77 */     tls.inCompletionListener(false, null);
/*    */     
/* 79 */     if (Trace.isOn)
/* 80 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsCompletionListenerWrapper", "onException(Message,Exception)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsCompletionListenerWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */