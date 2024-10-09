/*    */ package com.ibm.mq.jms;
/*    */ 
/*    */ import com.ibm.msg.client.commonservices.trace.Trace;
/*    */ import com.ibm.msg.client.jms.JmsMessageReference;
/*    */ import com.ibm.msg.client.jms.JmsMessageReferenceHandler;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MQMessageReferenceHandler
/*    */   implements JmsMessageReferenceHandler
/*    */ {
/*    */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQMessageReferenceHandler.java";
/*    */   private MessageReferenceHandler mrh;
/*    */   
/*    */   static {
/* 42 */     if (Trace.isOn) {
/* 43 */       Trace.data("com.ibm.mq.jms.MQMessageReferenceHandler", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQMessageReferenceHandler.java");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public MQMessageReferenceHandler(MessageReferenceHandler mrh) {
/* 60 */     if (Trace.isOn) {
/* 61 */       Trace.entry(this, "com.ibm.mq.jms.MQMessageReferenceHandler", "<init>(MessageReferenceHandler)", new Object[] { mrh });
/*    */     }
/*    */     
/* 64 */     this.mrh = mrh;
/* 65 */     if (Trace.isOn) {
/* 66 */       Trace.exit(this, "com.ibm.mq.jms.MQMessageReferenceHandler", "<init>(MessageReferenceHandler)");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void handleMessageReference(JmsMessageReference messageReference) {
/* 77 */     if (Trace.isOn) {
/* 78 */       Trace.entry(this, "com.ibm.mq.jms.MQMessageReferenceHandler", "handleMessageReference(JmsMessageReference)", new Object[] { messageReference });
/*    */     }
/*    */     
/* 81 */     MessageReference mr = new MQMessageReference(messageReference);
/* 82 */     this.mrh.handleMessageReference(mr);
/* 83 */     if (Trace.isOn) {
/* 84 */       Trace.exit(this, "com.ibm.mq.jms.MQMessageReferenceHandler", "handleMessageReference(JmsMessageReference)");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void endDeliver() {
/* 94 */     if (Trace.isOn) {
/* 95 */       Trace.entry(this, "com.ibm.mq.jms.MQMessageReferenceHandler", "endDeliver()");
/*    */     }
/* 97 */     this.mrh.endDeliver();
/* 98 */     if (Trace.isOn)
/* 99 */       Trace.exit(this, "com.ibm.mq.jms.MQMessageReferenceHandler", "endDeliver()"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQMessageReferenceHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */