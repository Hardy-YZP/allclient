/*     */ package com.ibm.msg.client.jms.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.nls.NLSServices;
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
/*     */ public class JmsErrorMessages
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsErrorMessages.java";
/*     */   public static final String NAMESPACE = "JMSCC";
/*     */   public static final String INITIALIZATION_ERROR = "JMSCC0001";
/*     */   public static final String INVALID_CLIENTID = "JMSCC0002";
/*     */   public static final String INVALID_DESTINATION_VALUE = "JMSCC0003";
/*     */   public static final String RECEIVE_ON_A_STOPPED_CONNECTION = "JMSCC0004";
/*     */   public static final String INVALID_VALUE = "JMSCC0005";
/*     */   public static final String EXCEPTION_RECEIVED = "JMSCC0007";
/*     */   public static final String CONNECTION_CLOSED = "JMSCC0008";
/*     */   public static final String MGD_ENV = "JMSCC0011";
/*     */   public static final String INVALID_OP_FOR_LISTENER = "JMSCC0012";
/*     */   public static final String INVALID_OP_FOR_NONTRANS_SESSION = "JMSCC0014";
/*     */   public static final String SESSION_CLOSED = "JMSCC0020";
/*     */   public static final String INVALID_OP_FOR_TRANS_SESSION = "JMSCC0021";
/*     */   public static final String PRODUCER_CLOSED = "JMSCC0026";
/*     */   
/*     */   static {
/*  38 */     if (Trace.isOn) {
/*  39 */       Trace.data("com.ibm.msg.client.jms.internal.JmsErrorMessages", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsErrorMessages.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String NO_DEST_SPECIFIED_ON_SEND = "JMSCC0029";
/*     */ 
/*     */   
/*     */   public static final String DEST_SPECIFIED_ON_SEND = "JMSCC0030";
/*     */ 
/*     */   
/*     */   public static final String NULL_COMPLETION_LISTENER = "JMSCC0031";
/*     */ 
/*     */   
/*     */   public static final String CONSUMER_CLOSED = "JMSCC0032";
/*     */ 
/*     */   
/*     */   public static final String ASYNC_IN_PROGRESS = "JMSCC0033";
/*     */ 
/*     */   
/*     */   public static final String ASYNC_ASF_INCONSISTENT_STATE = "JMSCC0034";
/*     */ 
/*     */   
/*     */   public static final String ML_THREW_EXCPTN = "JMSCC0037";
/*     */ 
/*     */   
/*     */   public static final String INVALID_OBJECT_TYPE = "JMSCC0039";
/*     */ 
/*     */   
/*     */   public static final String INBOUND_MSG_ERROR = "JMSCC0040";
/*     */ 
/*     */   
/*     */   public static final String INVALID_TYPE_CONVERSION = "JMSCC0041";
/*     */ 
/*     */   
/*     */   public static final String FIELD_NOT_SET = "JMSCC0042";
/*     */ 
/*     */   
/*     */   public static final String INVALID_FIELD_NAME = "JMSCC0043";
/*     */ 
/*     */   
/*     */   public static final String READ_ONLY_MESSAGE_BODY = "JMSCC0044";
/*     */ 
/*     */   
/*     */   public static final String READ_ONLY_MESSAGE_PROPERTY = "JMSCC0045";
/*     */ 
/*     */   
/*     */   public static final String WRITE_ONLY_MESSAGE_BODY = "JMSCC0046";
/*     */ 
/*     */   
/*     */   public static final String MSG_CREATE_FAILED = "JMSCC0048";
/*     */ 
/*     */   
/*     */   public static final String INVALID_PROPNAME = "JMSCC0049";
/*     */ 
/*     */   
/*     */   public static final String RESERVED_PROPNAME = "JMSCC0050";
/*     */ 
/*     */   
/*     */   public static final String JMS_IBM_INVALID_TYPE = "JMSCC0051";
/*     */ 
/*     */   
/*     */   public static final String SERIALIZATION_EXCEPTION = "JMSCC0052";
/*     */ 
/*     */   
/*     */   public static final String DESERIALIZATION_EXCEPTION = "JMSCC0053";
/*     */ 
/*     */   
/*     */   public static final String NO_MESSAGE_AVAILABLE = "JMSCC0054";
/*     */ 
/*     */   
/*     */   public static final String BROWSER_CLOSED = "JMSCC0055";
/*     */ 
/*     */   
/*     */   public static final String NULL_BUFFER = "JMSCC0059";
/*     */ 
/*     */   
/*     */   public static final String END_STREAM = "JMSCC0060";
/*     */ 
/*     */   
/*     */   public static final String INCOMPLETE_BYTE_ARRAY = "JMSCC0061";
/*     */ 
/*     */   
/*     */   public static final String NULL_CHAR = "JMSCC0062";
/*     */ 
/*     */   
/*     */   public static final String BAD_ENCODING = "JMSCC0063";
/*     */ 
/*     */   
/*     */   public static final String DATA_STREAM_PROBLEM = "JMSCC0064";
/*     */ 
/*     */   
/*     */   public static final String END_BYTESMESSAGE = "JMSCC0065";
/*     */ 
/*     */   
/*     */   public static final String UTF8_CONV = "JMSCC0066";
/*     */ 
/*     */   
/*     */   public static final String WRITE_PROBLEM = "JMSCC0067";
/*     */ 
/*     */   
/*     */   public static final String INVALID_TYPE_CONVERSION_ON_SET = "JMSCC0068";
/*     */ 
/*     */   
/*     */   public static final String INVALID_VALUE_CONVERSION_ON_SET = "JMSCC0069";
/*     */ 
/*     */   
/*     */   public static final String MESSAGE_PROPERTY_SET_FAIL = "JMSCC0070";
/*     */ 
/*     */   
/*     */   public static final String BAD_OBJECT = "JMSCC0083";
/*     */ 
/*     */   
/*     */   public static final String PROPERTY_NOT_SET = "JMSCC0084";
/*     */ 
/*     */   
/*     */   public static final String DESTINATION_NOT_SPECIFIED = "JMSCC0085";
/*     */ 
/*     */   
/*     */   public static final String INVALID_BATCH_SIZE = "JMSCC0086";
/*     */ 
/*     */   
/*     */   public static final String SESSIONPOOL_NOT_SPECIFIED = "JMSCC0087";
/*     */ 
/*     */   
/*     */   public static final String NO_SESSION_AVAILABLE = "JMSCC0088";
/*     */ 
/*     */   
/*     */   public static final String INVALID_SESSION = "JMSCC0089";
/*     */ 
/*     */   
/*     */   public static final String UNABLE_TO_LOAD_PROVIDER_FACTORY = "JMSCC0091";
/*     */ 
/*     */   
/*     */   public static final String INVALID_CONNECTION_TYPE = "JMSCC0092";
/*     */ 
/*     */   
/*     */   public static final String INVALID_DEST_FOR_DURABLE = "JMSCC0093";
/*     */ 
/*     */   
/*     */   public static final String CONN_BROWSER_CLOSED = "JMSCC0094";
/*     */ 
/*     */   
/*     */   public static final String INVALID_DEST_NAME = "JMSCC0095";
/*     */ 
/*     */   
/*     */   public static final String BAD_ACKMODE = "JMSCC0097";
/*     */ 
/*     */   
/*     */   public static final String NULL_MESSAGE = "JMSCC0098";
/*     */ 
/*     */   
/*     */   public static final String BAD_DESTINATION = "JMSCC0099";
/*     */   
/*     */   public static final String NULL_SUBSCRIPTION_NAME = "JMSCC0100";
/*     */   
/*     */   public static final String NULL_CLIENTID = "JMSCC0101";
/*     */   
/*     */   public static final String INVALID_MESSAGE_PROPNAME = "JMSCC0102";
/*     */   
/*     */   public static final String NULL_OBJECT = "JMSCC0103";
/*     */   
/*     */   public static final String INVALID_MESSAGE_TYPE_CONVERSION = "JMSCC0105";
/*     */   
/*     */   public static final String NO_XA_RESOURCE = "JMSCC0106";
/*     */   
/*     */   public static final String BAD_TIMEOUT = "JMSCC0107";
/*     */   
/*     */   public static final String NO_ASF_MESSAGE = "JMSCC0108";
/*     */   
/*     */   public static final String MDB_THREW_EXCEPTION = "JMSCC0109";
/*     */   
/*     */   public static final String EXCEPTION_DELIVERING_TO_MDB = "JMSCC0110";
/*     */   
/*     */   public static final String DUPLICATE_CLIENTID = "JMSCC0111";
/*     */   
/*     */   public static final String DELIVERY_DELAY_UNSUPPORTED = "JMSCC0112";
/*     */   
/*     */   public static final String DELIVERY_DELAY_TTL_INCONSISTENT = "JMSCC0113";
/*     */   
/*     */   public static final String DELIVERY_DELAY_RETAINED_PUB = "JMSCC0114";
/*     */   
/*     */   public static final String DELIVERY_DELAY_GROUP = "JMSCC0115";
/*     */   
/*     */   public static final String EXCEPTION_LISTENER_EXCEPTION_THROWN = "JMSCC1026";
/*     */   
/*     */   public static final String EXCEPTION_LISTENER_FAILED = "JMSCC1027";
/*     */   
/*     */   public static final String MQJMS_E_NOT_ALLOWED_WITH_XA = "JMSCC1069";
/*     */   
/*     */   public static final String BATCH_SIZE = "JMSCC1083";
/*     */   
/*     */   public static final String NULL_POOL = "JMSCC1084";
/*     */   
/*     */   public static final String NULL_PARAMETER = "JMSCC1093";
/*     */   
/*     */   public static final String INVALID_QUANTITY_HINT = "JMSCC1094";
/*     */   
/*     */   public static final String INVALID_MESSAGE_REFERENCE = "JMSCC1096";
/*     */   
/*     */   public static final String INVALID_DOMAIN_SPECIFIC_OPERATION = "JMSCC1112";
/*     */   
/*     */   public static final String INVALID_DOMAIN_SPECIFIC_OPERATION_SESSION = "JMSCC1113";
/*     */   
/*     */   public static final String TEMP_QUEUE_DEST_INUSE = "JMSCC3002";
/*     */   
/*     */   public static final String INVALID_USE_OF_TEMP_DEST = "JMSCC3003";
/*     */   
/*     */   public static final String TEMP_DEST_DELETED = "JMSCC3019";
/*     */   
/*     */   public static final String TEMP_TOPIC_DEST_INUSE = "JMSCC3025";
/*     */   
/*     */   public static final String CLIENTID_FIXED = "JMSCC3031";
/*     */   
/*     */   public static final String CLIENTID_NO_RESET = "JMSCC3032";
/*     */   
/*     */   public static final String CLOSE_IN_MSG_LISTENER = "JMSCC3033";
/*     */   
/*     */   public static final String IGNORE_EX_NO_EXPT_LSTNR = "JMSCC3034";
/*     */   
/*     */   public static final String EX_TYPE_NOT_REQUIRED_FOR_EXPT_LSTNR = "JMSCC3035";
/*     */   
/*     */   public static final String EXPT_LSTNR_FIRED = "JMSCC3036";
/*     */   
/*     */   public static final String MQJMS_PS_NULL_NAME = "JMSCC3039";
/*     */   
/*     */   public static final String MQJMS_METADATA_READ_ONLY = "JMSCC3040";
/*     */   
/*     */   public static final String MQJMS_BATCH_PROPERTY_VALIDATION_FAIL = "JMSCC3041";
/*     */   
/*     */   public static final String MQJMS_VERSION = "JMSCC3042";
/*     */   
/*     */   public static final String MQJMS_XA_CAPABILITY_NOT_SUPPORTED = "JMSCC4001";
/*     */   
/*     */   public static final String MQJMS_GET_BODY_STREAM = "JMSCC5001";
/*     */   
/*     */   public static final String MQJMS_GET_BODY_INVALID_ASSIGN = "JMSCC5002";
/*     */   
/*     */   public static final String MQJMS_GET_BODY_OBJECT_SERIALIZE_FAIL = "JMSCC5003";
/*     */   
/*     */   public static final String MQJMS_RECEIVE_MESSAGE_NO_BODY = "JMSCC5004";
/*     */   
/*     */   public static final String READ_ONLY_MESSAGE_PROPERTY_JMSPRODUCER = "JMSCC5005";
/*     */   
/*     */   public static final String ASYNC_SEND_BUFFER_FULL = "JMSCC5006";
/*     */   
/*     */   public static final String JMS2_API_NOT_SUPPORTED = "JMSCC5007";
/*     */   
/*     */   public static final String JMS2_FUNCTION_NOT_SUPPORTED = "JMSCC5008";
/*     */   
/*     */   public static final String ASYNC_SEND_NO_RESPONSE = "JMSCC5009";
/*     */   
/*     */   public static final String CONNECTION_FACTORY_PROPERTY_OVERRIDE_HEADER = "JMSCC5010";
/*     */   
/*     */   public static final String CF_OVERRIDDEN_PROPERTY = "JMSCC5011";
/*     */   
/*     */   public static final String CICS_FUNCTION_NOT_SUPPORTED = "JMSCC6001";
/*     */   
/*     */   public static final String CICS_FUNCTION_NOT_SUPPORTED_ISE = "JMSCC6002";
/*     */   
/*     */   public static final String CICS_TOO_MANY_SESSIONS = "JMSCC6003";
/*     */   
/*     */   public static final String CICS_USERID_NOT_SUPPORTED = "JMSCC6004";
/*     */   
/*     */   public static final String IMS_FUNCTION_NOT_SUPPORTED = "JMSCC6011";
/*     */   
/*     */   public static final String IMS_FUNCTION_NOT_SUPPORTED_ISE = "JMSCC6012";
/*     */   
/*     */   public static final String IMS_TOO_MANY_SESSIONS = "JMSCC6013";
/*     */   
/*     */   public static final String IMS_USERID_NOT_SUPPORTED = "JMSCC6014";
/*     */   
/*     */   public static final String J2EE_FUNCTION_NOT_SUPPORTED = "JMSCC6015";
/*     */ 
/*     */   
/*     */   public static String getMessage(String key, Object[] inserts) {
/* 316 */     if (Trace.isOn) {
/* 317 */       Trace.entry("com.ibm.msg.client.jms.internal.JmsErrorMessages", "getMessage(String,Object [ ])", new Object[] { key, inserts });
/*     */     }
/*     */     
/* 320 */     String traceRet1 = NLSServices.getMessage(key, inserts);
/* 321 */     if (Trace.isOn) {
/* 322 */       Trace.exit("com.ibm.msg.client.jms.internal.JmsErrorMessages", "getMessage(String,Object [ ])", traceRet1);
/*     */     }
/*     */     
/* 325 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsErrorMessages.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */