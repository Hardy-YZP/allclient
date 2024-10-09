/*    */ package com.ibm.jms;
/*    */ 
/*    */ import com.ibm.msg.client.commonservices.trace.Trace;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class JMSCInternal
/*    */ {
/*    */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/jms/JMSCInternal.java";
/*    */   static final String SERIALIZE_MESSAGE_MAP_DATA_KEY = "mapdata";
/*    */   static final String SERIALIZE_MESSAGE_OBJECT_DATA_KEY = "messageBytes";
/*    */   static final String SERIALIZE_MESSAGE_TEXT_DATA_KEY = "messageText";
/*    */   static final String SERIALIZE_MESSAGE_STREAM_BUFFER_DATA_KEY = "dataWritten";
/*    */   static final String SERIALIZE_MESSAGE_STREAM_STRING_DATA_KEY = "streamBody";
/*    */   static final String SERIALIZE_MESSAGE_CORRELATION_ID_KEY = "correlationId";
/*    */   static final String SERIALIZE_MESSAGE_NATIVE_CORRELATION_ID_KEY = "nativeCorrelId";
/*    */   static final String SERIALIZE_MESSAGE_DELIVERY_MODE_KEY = "deliveryMode";
/*    */   static final String SERIALIZE_MESSAGE_EXPIRATION_KEY = "expiration";
/*    */   static final String SERIALIZE_MESSAGE_TIME_TO_LIVE_KEY = "timeToLive";
/*    */   static final String SERIALIZE_MESSAGE_PRIORITY_KEY = "priority";
/*    */   static final String SERIALIZE_MESSAGE_REDELIVERED_KEY = "redelivered";
/*    */   static final String SERIALIZE_MESSAGE_TIMESTAMP_KEY = "timestamp";
/*    */   static final String SERIALIZE_MESSAGE_TYPE_KEY = "type";
/*    */   static final String SERIALIZE_MESSAGE_DESTINATION_KEY = "destination";
/*    */   static final String SERIALIZE_MESSAGE_REPLY_TO_KEY = "replyTo";
/*    */   static final String SERIALIZE_MESSAGE_PROPERTIES_KEY = "properties";
/*    */   static final String SERIALIZE_MESSAGE_ID_KEY = "messageId";
/*    */   static final String SERIALIZE_MESSAGE_BYTE_DATA_KEY = "dataBuffer";
/*    */   static final String SERIALIZE_MESSAGE_DELIVERY_DELAY_KEY = "deliveryDelay";
/*    */   
/*    */   static {
/* 34 */     if (Trace.isOn)
/* 35 */       Trace.data("com.ibm.jms.JMSCInternal", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/jms/JMSCInternal.java"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\jms\JMSCInternal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */