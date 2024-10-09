/*    */ package com.ibm.msg.client.wmq.common.internal.messages;
/*    */ 
/*    */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*    */ import com.ibm.msg.client.commonservices.trace.Trace;
/*    */ import javax.jms.JMSException;
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
/*    */ public class WMQNullMessage
/*    */   extends WMQMessage
/*    */ {
/*    */   private static final long serialVersionUID = -432180110949376183L;
/*    */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/messages/WMQNullMessage.java";
/*    */   
/*    */   static {
/* 36 */     if (Trace.isOn) {
/* 37 */       Trace.data("com.ibm.msg.client.wmq.common.internal.messages.WMQNullMessage", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/messages/WMQNullMessage.java");
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
/*    */ 
/*    */ 
/*    */   
/*    */   public byte[] _exportBody(int encoding, JmqiCodepage codepage) throws JMSException {
/* 57 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void _importBody(byte[] wireformat, int startIndex, int endIndex, int encoding, JmqiCodepage codepage) throws JMSException {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasBody() {
/* 73 */     if (Trace.isOn) {
/* 74 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQNullMessage", "hasBody()");
/*    */     }
/*    */     
/* 77 */     if (Trace.isOn) {
/* 78 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQNullMessage", "hasBody()", 
/* 79 */           Boolean.valueOf(false));
/*    */     }
/* 81 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\common\internal\messages\WMQNullMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */