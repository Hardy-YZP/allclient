/*     */ package com.ibm.msg.client.jms;
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
/*     */ public interface JmsConstants
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms/src/com/ibm/msg/client/jms/JmsConstants.java";
/*     */   public static final String PROVIDER_VRMF = "COMPONENT_VRMF";
/*     */   public static final String WMQ_PROVIDER = "com.ibm.msg.client.wmq";
/*     */   public static final String JAKARTA_WMQ_PROVIDER = "com.ibm.msg.client.jakarta.wmq";
/*     */   public static final String MQTT_PROVIDER = "com.ibm.msg.client.mqtt";
/*     */   public static final String WPM_PROVIDER = "com.ibm.msg.client.wpm";
/*     */   public static final String TRT_PROVIDER = "com.ibm.msg.client.tempore";
/* 100 */   public static final String[] providerNames = new String[] { "", "com.ibm.msg.client.wmq", "com.ibm.msg.client.wpm", "com.ibm.msg.client.mqtt", "com.ibm.msg.client.tempore", "com.ibm.msg.client.jakarta.wmq" };
/*     */   public static final int CT_UNKNOWN_PROVIDER = -1;
/*     */   public static final int CT_WMQ = 1;
/*     */   public static final int CT_RTT = 2;
/*     */   public static final int CT_WPM = 3;
/*     */   public static final int CT_MQTT = 4;
/*     */   public static final int CT_TRT = 5;
/*     */   public static final int CT_JWMQ = 6;
/*     */   public static final String J2SE_COMMONSERVICE = "com.ibm.msg.client.commonservices.j2se";
/*     */   public static final String J2ME_COMMONSERVICE = "com.ibm.msg.client.commonservices.j2me";
/*     */   public static final String ADMIN_OBJECT_TYPE = "XMSC_ADMIN_OBJECT_TYPE";
/*     */   public static final short ADMIN_CONNECTION_FACTORY = 16;
/*     */   public static final short ADMIN_DESTINATION = 32;
/*     */   public static final short ADMIN_XA = 64;
/*     */   public static final short ADMIN_TEMPORARY = 128;
/*     */   public static final short ADMIN_RRS = 256;
/*     */   public static final short ADMIN_QUEUE_DOMAIN = 1;
/*     */   public static final short ADMIN_TOPIC_DOMAIN = 2;
/*     */   public static final short ADMIN_CROSS_DOMAIN = 4;
/*     */   public static final String ASYNC_EXCEPTIONS = "XMSC_ASYNC_EXCEPTIONS";
/*     */   public static final int ASYNC_EXCEPTIONS_ALL = -1;
/*     */   public static final int ASYNC_EXCEPTIONS_CONNECTIONBROKEN = 1;
/*     */   public static final int ASYNC_EXCEPTIONS_DEFAULT = 1;
/*     */   public static final String CAPABILITY_TRANSACTIONS_XA = "XMSC_CAPABILITY_TRANSACTIONS_XA";
/*     */   public static final String CAPABILITY_TRANSACTIONS_LOCAL = "XMSC_CAPABILITY_TRANSACTIONS_LOCAL";
/*     */   public static final String CAPABILITY_ASF = "XMSC_CAPABILITY_ASF";
/*     */   public static final String CAPABILITY_POINT_TO_POINT = "XMSC_CAPABILITY_POINT_TO_POINT";
/*     */   public static final String CAPABILITY_PUBLISH_SUBSCRIBE = "XMSC_CAPABILITY_PUBLISH_SUBSCRIBE";
/*     */   public static final String CAPABILITY_USERNAME_PASSWORD = "XMSC_CAPABILITY_USERNAME_PASSWORD";
/*     */   public static final String CAPABILITY_JMS2_FUNCTION = "XMSC_CAPABILITY_JMS2_FUNCTION";
/*     */   public static final String CAPABILITY_JMS2_API = "XMSC_CAPABILITY_JMS2_API";
/*     */   public static final String CONNECTION_TYPE = "XMSC_CONNECTION_TYPE";
/*     */   public static final String CONNECTION_TYPE_NAME = "XMSC_CONNECTION_TYPE_NAME";
/*     */   public static final String USERID = "XMSC_USERID";
/*     */   public static final String PASSWORD = "XMSC_PASSWORD";
/*     */   public static final String PASSWORD_WAS_NULL = "XMSC_PASSWORD_WAS_NULL";
/*     */   public static final String USER_AUTHENTICATION_MQCSP = "XMSC_USER_AUTHENTICATION_MQCSP";
/*     */   public static final String CLIENT_ID = "XMSC_CLIENT_ID";
/*     */   public static final String JMS_MAJOR_VERSION = "XMSC_JMS_MAJOR_VERSION";
/*     */   public static final String JMS_MINOR_VERSION = "XMSC_JMS_MINOR_VERSION";
/*     */   public static final String JMS_VERSION = "XMSC_JMS_VERSION";
/*     */   public static final String PROVIDER_NAME = "XMSC_PROVIDER_NAME";
/*     */   public static final String MAJOR_VERSION = "XMSC_MAJOR_VERSION";
/*     */   public static final String MINOR_VERSION = "XMSC_MINOR_VERSION";
/*     */   public static final String VERSION = "XMSC_VERSION";
/*     */   public static final String IS_Z_SERIES = "XMSC_IS_Z_SERIES";
/*     */   public static final String IS_JEE = "XMSC_IS_JEE";
/*     */   public static final String DELIVERY_DELAY = "deliveryDelay";
/*     */   public static final String TIME_TO_LIVE = "timeToLive";
/*     */   public static final String DELIVERY_MODE = "deliveryMode";
/*     */   public static final String PRIORITY = "priority";
/*     */   public static final String DESTINATION_NAME = "XMSC_DESTINATION_NAME";
/*     */   public static final String SELECTOR_STRING = "XMSC_SELECTOR_STRING";
/*     */   public static final String DISABLE_MSG_ID = "XMSC_DISABLE_MSG_ID";
/*     */   public static final String DISABLE_MSG_TS = "XMSC_DISABLE_MSG_TS";
/*     */   public static final String NOLOCAL = "XMSC_NOLOCAL";
/*     */   public static final String JMS_DESTINATION = "JMSDestination";
/*     */   public static final String JMS_DELIVERY_MODE = "JMSDeliveryMode";
/*     */   public static final String JMS_EXPIRATION = "JMSExpiration";
/*     */   public static final String JMS_PRIORITY = "JMSPriority";
/*     */   public static final String JMS_MESSAGEID = "JMSMessageID";
/*     */   public static final String JMS_TIMESTAMP = "JMSTimestamp";
/*     */   public static final String JMS_CORRELATIONID = "JMSCorrelationID";
/*     */   public static final String JMS_REPLYTO = "JMSReplyto";
/*     */   public static final String JMS_TYPE = "JMSType";
/*     */   public static final String JMS_REDELIVERED = "JMSRedelivered";
/*     */   public static final String JMSX_USERID = "JMSXUserID";
/*     */   public static final String JMSX_APPID = "JMSXAppID";
/*     */   public static final String JMSX_DELIVERY_COUNT = "JMSXDeliveryCount";
/*     */   public static final String JMSX_GROUPID = "JMSXGroupID";
/*     */   public static final String JMSX_GROUPSEQ = "JMSXGroupSeq";
/*     */   public static final String JMSX_STATE = "JMSXState";
/*     */   public static final String JMSX_PRODUCER_TXID = "JMSXProducerTXID";
/*     */   public static final String JMSX_CONSUMER_TXID = "JMSXConsumerTXID";
/*     */   public static final String JMSX_RCV_TIMESTAMP = "JMSXRcvTimestamp";
/*     */   public static final String JMS_IBM_REPORT_EXCEPTION = "JMS_IBM_Report_Exception";
/*     */   public static final String JMS_IBM_REPORT_EXPIRATION = "JMS_IBM_Report_Expiration";
/*     */   public static final String JMS_IBM_REPORT_COA = "JMS_IBM_Report_COA";
/*     */   public static final String JMS_IBM_REPORT_COD = "JMS_IBM_Report_COD";
/*     */   public static final String JMS_IBM_REPORT_NAN = "JMS_IBM_Report_NAN";
/*     */   public static final String JMS_IBM_REPORT_PAN = "JMS_IBM_Report_PAN";
/*     */   public static final String JMS_IBM_REPORT_PASS_MSG_ID = "JMS_IBM_Report_Pass_Msg_ID";
/*     */   public static final String JMS_IBM_REPORT_PASS_CORREL_ID = "JMS_IBM_Report_Pass_Correl_ID";
/*     */   public static final String JMS_IBM_REPORT_DISCARD_MSG = "JMS_IBM_Report_Discard_Msg";
/*     */   public static final String JMS_IBM_REPORT_PASS_DISCARD_AND_EXPIRY = "JMS_IBM_Report_Pass_Discard_And_Expiry";
/*     */   public static final String JMS_IBM_MSGTYPE = "JMS_IBM_MsgType";
/*     */   public static final String JMS_IBM_FEEDBACK = "JMS_IBM_Feedback";
/*     */   public static final String JMS_IBM_FORMAT = "JMS_IBM_Format";
/*     */   public static final String JMS_IBM_PUTAPPLTYPE = "JMS_IBM_PutApplType";
/*     */   public static final String JMS_IBM_ENCODING = "JMS_IBM_Encoding";
/*     */   public static final String JMS_IBM_CHARACTER_SET = "JMS_IBM_Character_Set";
/*     */   public static final String JMS_IBM_UNMAPPABLE_ACTION = "JMS_IBM_Unmappable_Action";
/*     */   public static final String JMS_IBM_UNMAPPABLE_REPLACEMENT = "JMS_IBM_Unmappable_Replacement";
/*     */   public static final String JMS_IBM_PUTDATE = "JMS_IBM_PutDate";
/*     */   public static final String JMS_IBM_PUTTIME = "JMS_IBM_PutTime";
/*     */   public static final String JMS_IBM_LAST_MSG_IN_GROUP = "JMS_IBM_Last_Msg_In_Group";
/*     */   @Deprecated
/*     */   public static final String JMS_IBM_ARM_CORRELATOR = "JMS_IBM_ArmCorrelator";
/*     */   public static final String JMS_TOG_ARM_CORRELATOR = "JMS_TOG_ARM_Correlator";
/*     */   public static final String JMS_IBM_WAS_RM_CORRELATOR = "JMS_IBM_RMCorrelator";
/*     */   public static final String JMS_IBM_CONNECTIONID = "JMS_IBM_ConnectionID";
/*     */   public static final String JMS_IBM_RETAIN = "JMS_IBM_Retain";
/*     */   public static final String JMS_IBM_SUBSCRIPTION_USER_DATA = "JMS_IBM_SubscriptionUserData";
/*     */   public static final String JMS_IBM_MQMD_REPORT = "JMS_IBM_MQMD_Report";
/*     */   public static final String JMS_IBM_MQMD_MSGTYPE = "JMS_IBM_MQMD_MsgType";
/*     */   public static final String JMS_IBM_MQMD_EXPIRY = "JMS_IBM_MQMD_Expiry";
/*     */   public static final String JMS_IBM_MQMD_FEEDBACK = "JMS_IBM_MQMD_Feedback";
/*     */   public static final String JMS_IBM_MQMD_ENCODING = "JMS_IBM_MQMD_Encoding";
/*     */   public static final String JMS_IBM_MQMD_CODEDCHARSETID = "JMS_IBM_MQMD_CodedCharSetId";
/*     */   public static final String JMS_IBM_MQMD_FORMAT = "JMS_IBM_MQMD_Format";
/*     */   public static final String JMS_IBM_MQMD_PRIORITY = "JMS_IBM_MQMD_Priority";
/*     */   public static final String JMS_IBM_MQMD_PERSISTENCE = "JMS_IBM_MQMD_Persistence";
/*     */   public static final String JMS_IBM_MQMD_MSGID = "JMS_IBM_MQMD_MsgId";
/*     */   public static final String JMS_IBM_MQMD_CORRELID = "JMS_IBM_MQMD_CorrelId";
/*     */   public static final String JMS_IBM_MQMD_BACKOUTCOUNT = "JMS_IBM_MQMD_BackoutCount";
/*     */   public static final String JMS_IBM_MQMD_REPLYTOQ = "JMS_IBM_MQMD_ReplyToQ";
/*     */   public static final String JMS_IBM_MQMD_REPLYTOQMGR = "JMS_IBM_MQMD_ReplyToQMgr";
/*     */   public static final String JMS_IBM_MQMD_USERIDENTIFIER = "JMS_IBM_MQMD_UserIdentifier";
/*     */   public static final String JMS_IBM_MQMD_ACCOUNTINGTOKEN = "JMS_IBM_MQMD_AccountingToken";
/*     */   public static final String JMS_IBM_MQMD_APPLIDENTITYDATA = "JMS_IBM_MQMD_ApplIdentityData";
/*     */   public static final String JMS_IBM_MQMD_PUTAPPLTYPE = "JMS_IBM_MQMD_PutApplType";
/*     */   public static final String JMS_IBM_MQMD_PUTAPPLNAME = "JMS_IBM_MQMD_PutApplName";
/*     */   public static final String JMS_IBM_MQMD_PUTDATE = "JMS_IBM_MQMD_PutDate";
/*     */   public static final String JMS_IBM_MQMD_PUTTIME = "JMS_IBM_MQMD_PutTime";
/*     */   public static final String JMS_IBM_MQMD_APPLORIGINDATA = "JMS_IBM_MQMD_ApplOriginData";
/*     */   public static final String JMS_IBM_MQMD_GROUPID = "JMS_IBM_MQMD_GroupId";
/*     */   public static final String JMS_IBM_MQMD_MSGSEQNUMBER = "JMS_IBM_MQMD_MsgSeqNumber";
/*     */   public static final String JMS_IBM_MQMD_OFFSET = "JMS_IBM_MQMD_Offset";
/*     */   public static final String JMS_IBM_MQMD_MSGFLAGS = "JMS_IBM_MQMD_MsgFlags";
/*     */   public static final String JMS_IBM_MQMD_ORIGINALLENGTH = "JMS_IBM_MQMD_OriginalLength";
/*     */   public static final String ACKNOWLEDGE_MODE = "XMSC_ACKNOWLEDGE_MODE";
/*     */   public static final String TRANSACTED = "XMSC_TRANSACTED";
/*     */   public static final int CCSID_UTF8 = 1208;
/*     */   public static final int CCSID_UTF16 = 1202;
/*     */   public static final int CCSID_UTF32 = 1234;
/*     */   public static final int DELIVERY_AS_APP = -2;
/*     */   public static final int DELIVERY_AS_DEST = -1;
/*     */   public static final int DELIVERY_NOT_PERSISTENT = 1;
/*     */   public static final int DELIVERY_PERSISTENT = 2;
/*     */   public static final int RETAIN_PUBLICATION = 1;
/*     */   public static final int TIME_TO_LIVE_UNLIMITED = 0;
/*     */   public static final int TIME_TO_LIVE_AS_APP = -2;
/*     */   public static final int PRIORITY_AS_DEST = -1;
/*     */   public static final int PRIORITY_AS_APP = -2;
/*     */   public static final int SESSION_TRANSACTED = 0;
/*     */   public static final int AUTO_ACKNOWLEDGE = 1;
/*     */   public static final int CLIENT_ACKNOWLEDGE = 2;
/*     */   public static final int DUPS_OK_ACKNOWLEDGE = 3;
/*     */   public static final String INSERT_EXCEPTION = "XMSC_INSERT_EXCEPTION";
/*     */   public static final String INSERT_METHOD = "XMSC_INSERT_METHOD";
/*     */   public static final String INSERT_OBJECT = "XMSC_INSERT_OBJECT";
/*     */   public static final String INSERT_BATCH_SIZE = "XMSC_INSERT_BATCH_SIZE";
/*     */   public static final String INSERT_SESSION = "XMSC_INSERT_SESSION";
/*     */   public static final String INSERT_NAME = "XMSC_INSERT_NAME";
/*     */   public static final String INSERT_VALUE = "XMSC_INSERT_VALUE";
/*     */   public static final String INSERT_PROPERTY = "XMSC_INSERT_PROPERTY";
/*     */   public static final String INSERT_TYPE = "XMSC_INSERT_TYPE";
/*     */   public static final String INSERT_OTHER_TYPE = "XMSC_INSERT_OTHER_TYPE";
/*     */   public static final String INSERT_FIELD = "XMSC_INSERT_FIELD";
/*     */   public static final String INSERT_DESTINATION_NAME = "XMSC_INSERT_DESTINATION_NAME";
/*     */   public static final String INSERT_TIMEOUT = "XMSC_INSERT_TIMEOUT";
/*     */   public static final String OBJECT_IDENTITY = "XMSC_OBJECT_IDENTITY";
/*     */   public static final String INSERT_QUEUE_MANAGER_NAME = "XMSC_INSERT_QUEUE_MANAGER_NAME";
/*     */   public static final String INSERT_MESSAGE_ID = "XMSC_INSERT_MESSAGE_ID";
/*     */   public static final String INSERT_TIMESTAMP = "XMSC_INSERT_TIMESTAMP";
/*     */   public static final String INSERT_DATESTAMP = "XMSC_INSERT_DATESTAMP";
/*     */   public static final String INSERT_ALIAS_DESTINATION_NAME = "XMSC_INSERT_ALIAS_DESTINATION_NAME";
/*     */   public static final String SUPPORT_MQ_EXTENSIONS = "com.ibm.mq.jms.SupportMQExtensions";
/*     */   public static final String BYTE_STREAM_READ_ONLY_AFTER_SEND = "com.ibm.msg.client.jms.ByteStreamReadOnlyAfterSend";
/*     */   public static final String SUBSCRIPTION_NAME = "XMSC_SUBSCRIPTION_NAME";
/*     */   public static final String CAPABILITY_NATIVE_CICS_UNMANAGED = "XMSC_CAPABILITY_NATIVE_CICS_UNMANAGED";
/*     */   public static final String CAPABILITY_NATIVE_IMS = "XMSC_CAPABILITY_NATIVE_IMS";
/*     */   public static final String OVERRIDE_CF_FROM_PROPERTYSTORE = "com.ibm.msg.client.jms.overrideConnectionFactory";
/*     */   public static final String OVERRIDE_CF_PREFIX = "jmscf.";
/*     */   public static final String INSERT_PROPERTY_OVERRIDE_LIST = "XMSC_INSERT_PROPERTY_OVERRIDE_LIST";
/*     */   public static final String INSERT_OVERRIDDEN_PROPERTY = "XMSC_OVERRIDDEN_PROPERTY";
/*     */   public static final String INSERT_ORIGINAL__PROPERTY_VALUE = "XMSC_ORIGINAL__PROPERTY_VALUE";
/*     */   public static final String INSERT_NEW_PROPERTY_VALUE = "XMSC_NEW_PROPERTY_VALUE";
/*     */   public static final String ASF_MESSAGE_WENT_MISSING = "ASF_Message_Went_Missing";
/*     */   public static final String NON_ASF_NO_MSG_AVAILABLE = "Non_ASF_No_Message_Available";
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\JmsConstants.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */