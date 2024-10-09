/*    */ package com.ibm.msg.client.wmq.internal;
/*    */ 
/*    */ import com.ibm.msg.client.commonservices.trace.Trace;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JMSWMQ_Messages
/*    */ {
/*    */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq/src/com/ibm/msg/client/wmq/internal/JMSWMQ_Messages.java";
/*    */   public static final String ASF_EXCEPTION = "JMSWMQ0036";
/*    */   public static final String ASYNC_PUT_ERROR = "JMSWMQ0028";
/*    */   public static final String BAD_ENCODING = "JMSWMQ0027";
/*    */   public static final String CANT_CONVERT_BORQ = "JMSWMQ0033";
/*    */   public static final String CANT_CONVERT_DLQ = "JMSWMQ0034";
/*    */   public static final String COMMAND_LEVEL_ERROR = "JMSWMQ0010";
/*    */   public static final String CONNECT_FAILED = "JMSWMQ0018";
/*    */   public static final String CONNECT_FAILED_CCDT = "JMSWMQ2020";
/*    */   public static final String DEREGISTER_CALLBACK_FAILED = "JMSWMQ0021";
/*    */   public static final String DEREGISTER_EXCEPTION_LISTENER_FAILED = "JMSWMQ2017";
/*    */   public static final String DISCONNECT_FAILED = "JMSWMQ0019";
/*    */   public static final String END_OF_STREAM = "JMSWMQ0017";
/*    */   public static final String EXCEPTION_LISTENER = "JMSWMQ1107";
/*    */   public static final String GET_MSG_FAILED = "JMSWMQ2002";
/*    */   public static final String IGNORE_EX_NO_EXPT_LSTNR = "JMSWMQ2018";
/*    */   public static final String INVALID_DEST_FOR_NPMHIGH = "JMSWMQ2010";
/*    */   public static final String JMQI_INIT_ERROR = "JMSWMQ0011";
/*    */   public static final String METHOD_FAILED_FOR_DEST = "JMSWMQ0030";
/*    */   
/*    */   static {
/* 32 */     if (Trace.isOn)
/* 33 */       Trace.data("com.ibm.msg.client.wmq.internal.JMSWMQ_Messages", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq/src/com/ibm/msg/client/wmq/internal/JMSWMQ_Messages.java"); 
/*    */   }
/*    */   
/*    */   public static final String METHOD_FAILED_FOR_QM = "JMSWMQ0031";
/*    */   public static final String MQ_ASYNC_Q_CLOSE_FAILED = "JMSWMQ2001";
/*    */   public static final String MQ_AUTHENTICATION_FAILED = "JMSWMQ2013";
/*    */   public static final String MQ_AUTHENTICATION_FAILED_CCDT = "JMSWMQ2021";
/*    */   public static final String MQ_NONLOCAL_Q_OPEN_FAILED = "JMSWMQ1017";
/*    */   public static final String MQ_Q_CLOSE_FAILED = "JMSWMQ2000";
/*    */   public static final String MQ_Q_OPEN_FAILED = "JMSWMQ2008";
/*    */   public static final String MQ_MODEL_Q_OPEN_FAILED = "JMSWMQ2022";
/*    */   public static final String MQ_ERORR_MSG = "JMSWMQ2023";
/*    */   public static final String MQ_TMP_Q_INUSE = "JMSWMQ2009";
/*    */   public static final String MQ_TOPIC_CLOSE_FAILED = "JMSWMQ2003";
/*    */   public static final String MQ_TOPIC_OPEN_FAILED = "JMSWMQ2006";
/*    */   public static final String MQ_UNEXPECTED_FAILURE = "JMSWMQ2019";
/*    */   public static final String MQASYNC_EVENT = "JMSWMQ2014";
/*    */   public static final String MQCTL_RESUME_FAILED = "JMSWMQ0024";
/*    */   public static final String MQCTL_START_FAILED = "JMSWMQ0022";
/*    */   public static final String MQCTL_SUSPEND_FAILED = "JMSWMQ0023";
/*    */   public static final String MQJMS_E_BAD_CCSID = "JMSWMQ1046";
/*    */   public static final String MQJMS_E_BAD_TIMEOUT = "JMSWMQ1067";
/*    */   public static final String MQJMS_E_BAD_TYPE = "JMSWMQ1055";
/*    */   public static final String MQJMS_E_DLQ_FAILED = "JMSWMQ1079";
/*    */   public static final String MQJMS_E_DLQ_FAILED_EX = "JMSWMQ0035";
/*    */   public static final String MQJMS_E_INVALID_HEX_STRING = "JMSWMQ1044";
/*    */   public static final String MQJMS_E_INVALID_MESSAGE_REFERENCE = "JMSWMQ1096";
/*    */   public static final String MQJMS_E_NO_BORQ = "JMSWMQ1080";
/*    */   public static final String MQJMS_E_NO_UTF8 = "JMSWMQ1059";
/*    */   public static final String MQJMS_E_NO_XARESOURCE = "JMSWMQ1068";
/*    */   public static final String MQJMS_EXCEPTION_BAD_VALUE = "JMSWMQ1006";
/*    */   public static final String MQJMS_EXCEPTION_MESSAGE_FORMAT = "JMSWMQ0006";
/*    */   public static final String MQJMS_EXCEPTION_MESSAGE_NOT_READABLE = "JMSWMQ0007";
/*    */   public static final String MQJMS_EXCEPTION_MESSAGE_NOT_WRITABLE = "JMSWMQ0008";
/*    */   public static final String MQJMS_EXCEPTION_NULL_PROPERTY_NAME = "JMSWMQ1000";
/*    */   public static final String MQJMS_EXCEPTION_RESOURCE_ALLOCATION = "JMSWMQ0009";
/*    */   public static final String MQJMS_EXCEPTION_XACLOSE_FAILED = "JMSWMQ2012";
/*    */   public static final String MQJMS_MSG_CLASS = "JMSWMQ1051";
/*    */   public static final String MQSTAT_INFO = "JMSWMQ0002";
/*    */   public static final String NAMESPACE = "JMSWMQ";
/*    */   public static final String NO_DLQ = "JMSWMQ0032";
/*    */   public static final String NULL_NUMBER = "JMSWMQ0014";
/*    */   public static final String NULL_VALUE = "JMSWMQ0015";
/*    */   public static final String POISON_EXCEPTION = "JMSWMQ0037";
/*    */   public static final String PUT_MSG_FAILED = "JMSWMQ2007";
/*    */   public static final String PUT_MSG_FAILED_WITH_DELIVERY_DELAY = "JMSWMQ2024";
/*    */   public static final String REGISTER_CALLBACK_FAILED = "JMSWMQ0020";
/*    */   public static final String REGISTER_EXCEPTION_LISTENER_FAILED = "JMSWMQ2016";
/*    */   public static final String SEND_REPORT_FAILED = "JMSWMQ0029";
/*    */   public static final String SERIALIZATION_FAILED = "JMSWMQ0016";
/*    */   public static final String SUBSCRIBE_FAILED = "JMSWMQ0026";
/*    */   public static final String SUBSCRIPTION_NAME_USED_LOCALLY = "JMSWMQ2011";
/*    */   public static final String UNSUBSCRIBE_FAILED = "JMSWMQ0025";
/*    */   public static final String WRONG_QUEUEMANAGER_VERSION = "JMSWMQ0003";
/*    */   public static final String MESSAGE_MOVED_TO_DLQ = "JMSWMQ1116";
/*    */   public static final String MESSAGE_DISCARDED = "JMSWMQ1117";
/*    */   public static final String CONSUMER_NOW_GET_ENABLED = "JMSWMQ1118";
/*    */   public static final String CONSUMER_NOW_GET_INHIBITED = "JMSWMQ1119";
/*    */   public static final String MOVED_TO_BACKOUT_FAILED = "JMSWMQ1120";
/*    */   public static final String SUBSCRIPTION_SHARABILITY_NOT_CHANGEABLE = "JMSWMQ2025";
/*    */   public static final String NOT_AUTHORIZED_TO_INQUIRE_TARGET_QUEUE = "JMSWMQ2026";
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\internal\JMSWMQ_Messages.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */