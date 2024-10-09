/*     */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface JMSC
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/JMSC.java";
/*     */   public static final String PS_CONTROL_QUEUE = "SYSTEM.BROKER.CONTROL.QUEUE";
/*     */   public static final String PS_DEFAULT_STREAM_QUEUE = "SYSTEM.BROKER.DEFAULT.STREAM";
/*     */   public static final String PS_ADMIN_QUEUE = "SYSTEM.JMS.ADMIN.QUEUE";
/*     */   public static final String PS_STATUS_QUEUE = "SYSTEM.JMS.PS.STATUS.QUEUE";
/*     */   public static final String PS_MODEL_QUEUE = "SYSTEM.JMS.MODEL.QUEUE";
/*     */   public static final String PS_REPORT_QUEUE = "SYSTEM.JMS.REPORT.QUEUE";
/*     */   public static final String PS_D_PREFIX = "SYSTEM.JMS.D.";
/*     */   public static final String PS_D_PREFIX_STAR = "SYSTEM.JMS.D.*";
/*     */   public static final String PS_ND_PREFIX = "SYSTEM.JMS.ND.";
/*     */   public static final String PS_ND_PREFIX_STAR = "SYSTEM.JMS.ND.*";
/*     */   public static final int MQRCCF_TOPIC_ERROR = 3072;
/*     */   public static final int MQRCCF_NOT_REGISTERED = 3073;
/*     */   public static final int MQRCCF_DUPLICATE_IDENTITY = 3078;
/*     */   public static final int MQRCCF_REG_OPTIONS_ERROR = 3083;
/*     */   public static final int MQRCCF_DUPLICATE_SUBSCRIPTION = 3152;
/*     */   public static final int MQRCCF_SUB_NAME_ERROR = 3153;
/*     */   public static final int MQRCCF_SUB_IDENTITY_ERROR = 3154;
/*     */   public static final int MQRCCF_SUBSCRIPTION_IN_USE = 3155;
/*     */   public static final int MQRCCF_SUBSCRIPTION_LOCKED = 3156;
/*     */   public static final int MQRCCF_ALREADY_JOINED = 3157;
/*     */   public static final int MQRCCF_NOT_AUTHORIZED = 3081;
/*     */   public static final String PS_DEF_ND_SHARED_QUEUE = "SYSTEM.JMS.ND.SUBSCRIBER.QUEUE";
/*     */   public static final String PS_DEF_D_SHARED_QUEUE = "SYSTEM.JMS.D.SUBSCRIBER.QUEUE";
/*     */   public static final String CC_DEF_ND_SHARED_QUEUE = "SYSTEM.JMS.ND.CC.SUBSCRIBER.QUEUE";
/*     */   public static final String CC_DEF_D_SHARED_QUEUE = "SYSTEM.JMS.D.CC.SUBSCRIBER.QUEUE";
/*     */   public static final int MSG_PRIORITY_MIN = 0;
/*     */   public static final int MSG_PRIORITY_MAX = 9;
/* 220 */   public static final byte[] PS_JVM_REFMSG_CORRELID = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 22, 7, 76 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 228 */   public static final byte[] PS_TERMMSG_CORRELID = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 22, 7, 77 };
/*     */   public static final int MQJMS_CLIENT_JMS_COMPLIANT = 0;
/*     */   public static final int MQJMS_CLIENT_NONJMS_MQ = 1;
/*     */   public static final int MQJMS_EXP_APP = -2;
/*     */   public static final int MQJMS_EXP_UNLIMITED = 0;
/*     */   public static final int MQJMS_PRI_APP = -2;
/*     */   public static final int MQJMS_PRI_QDEF = -1;
/*     */   public static final int MQJMS_PER_APP = -2;
/*     */   public static final int MQJMS_PER_QDEF = -1;
/*     */   public static final int MQJMS_PER_PER = 2;
/*     */   public static final int MQJMS_PER_NON = 1;
/*     */   public static final int MQJMS_PER_NPHIGH = 3;
/*     */   public static final int MQJMS_ENCODING_INTEGER_NORMAL = 1;
/*     */   public static final int MQJMS_ENCODING_INTEGER_REVERSED = 2;
/*     */   public static final int MQJMS_ENCODING_DECIMAL_NORMAL = 16;
/*     */   public static final int MQJMS_ENCODING_DECIMAL_REVERSED = 32;
/*     */   public static final int MQJMS_ENCODING_FLOAT_IEEE_NORMAL = 256;
/*     */   public static final int MQJMS_ENCODING_FLOAT_IEEE_REVERSED = 512;
/*     */   public static final int MQJMS_ENCODING_FLOAT_S390 = 768;
/*     */   public static final int MQJMS_ENCODING_NATIVE = 273;
/*     */   public static final int MQJMS_BROKER_UNSPECIFIED = -1;
/*     */   public static final int MQJMS_BROKER_V1 = 0;
/*     */   public static final int MQJMS_BROKER_V2 = 1;
/*     */   public static final int MQJMS_MSEL_CLIENT = 0;
/*     */   public static final int MQJMS_MSEL_BROKER = 1;
/*     */   public static final int MQJMS_TP_BINDINGS_MQ = 0;
/*     */   public static final int MQJMS_TP_CLIENT_MQ_TCPIP = 1;
/*     */   public static final int MQJMS_TP_DIRECT_TCPIP = 2;
/*     */   public static final int MQJMS_TP_MQJD = 3;
/*     */   public static final int MQJMS_TP_DIRECT_HTTP = 4;
/*     */   public static final int MQJMS_MRET_NO = 0;
/*     */   public static final int MQJMS_MRET_YES = 1;
/*     */   public static final int MQJMS_FIQ_NO = 0;
/*     */   public static final int MQJMS_FIQ_YES = 1;
/*     */   public static final int MQJMS_RCVISOL_COMMITTED = 0;
/*     */   public static final int MQJMS_RCVISOL_UNCOMMITTED = 1;
/*     */   public static final int MQJMS_RCVISOL_DEFAULT = 0;
/*     */   public static final int MQJMS_PROCESSING_UNKNOWN = 0;
/*     */   public static final int MQJMS_PROCESSING_SHORT = 1;
/*     */   public static final int MQJMS_PROCESSING_DEFAULT = 0;
/*     */   public static final int MQJMS_GENERIC_NO = 0;
/*     */   public static final int MQJMS_GENERIC_YES = 1;
/*     */   public static final char SUBSTATE_UNKNOWN = 'u';
/*     */   public static final char SUBSTATE_INACTIVE = 'i';
/*     */   public static final char SUBSTATE_TEMP = 't';
/*     */   public static final String FORMAT_PROPERTY = "JMS_IBM_Format";
/*     */   public static final String MSG_TYPE_PROPERTY = "JMS_IBM_MsgType";
/*     */   public static final String FEEDBACK_PROPERTY = "JMS_IBM_Feedback";
/*     */   public static final String PUT_APPL_TYPE_PROPERTY = "JMS_IBM_PutApplType";
/*     */   public static final String REPORT_EXCEPTION_PROPERTY = "JMS_IBM_Report_Exception";
/*     */   public static final String REPORT_EXPIRATION_PROPERTY = "JMS_IBM_Report_Expiration";
/*     */   public static final String REPORT_COA_PROPERTY = "JMS_IBM_Report_COA";
/*     */   public static final String REPORT_COD_PROPERTY = "JMS_IBM_Report_COD";
/*     */   public static final String REPORT_PAN_PROPERTY = "JMS_IBM_Report_PAN";
/*     */   public static final String REPORT_NAN_PROPERTY = "JMS_IBM_Report_NAN";
/*     */   public static final String REPORT_MSGID_PROPERTY = "JMS_IBM_Report_Pass_Msg_ID";
/*     */   public static final String REPORT_CORRELID_PROPERTY = "JMS_IBM_Report_Pass_Correl_ID";
/*     */   public static final String REPORT_DISCARD_PROPERTY = "JMS_IBM_Report_Discard_Msg";
/*     */   public static final String LAST_MSG_IN_GROUP_PROPERTY = "JMS_IBM_Last_Msg_In_Group";
/*     */   public static final String PUT_DATE_PROPERTY = "JMS_IBM_PutDate";
/*     */   public static final String PUT_TIME_PROPERTY = "JMS_IBM_PutTime";
/*     */   public static final String ARM_CORRELATOR_PROPERTY = "JMS_IBM_ArmCorrelator";
/*     */   public static final String WAS_RM_CORRELATOR_PROPERTY = "JMS_IBM_RMCorrelator";
/*     */   public static final String ENCODING_PROPERTY = "JMS_IBM_Encoding";
/*     */   public static final String CHARSET_PROPERTY = "JMS_IBM_Character_Set";
/*     */   public static final int MQJMS_SUBSTORE_QUEUE = 0;
/*     */   public static final int MQJMS_SUBSTORE_BROKER = 1;
/*     */   public static final int MQJMS_SUBSTORE_MIGRATE = 2;
/*     */   public static final int MQJMS_CLEANUP_AS_PROPERTY = -1;
/*     */   public static final int MQJMS_CLEANUP_NONE = 0;
/*     */   public static final int MQJMS_CLEANUP_SAFE = 1;
/*     */   public static final int MQJMS_CLEANUP_STRONG = 2;
/*     */   public static final int MQJMS_CLEANUP_FORCE = 3;
/*     */   public static final int MQJMS_CLEANUP_NONDUR = 4;
/*     */   public static final int MQJMS_CLONE_DISABLED = 0;
/*     */   public static final int MQJMS_CLONE_ENABLED = 1;
/*     */   public static final int MULTICAST_ENABLED = 1;
/*     */   public static final int MULTICAST_FALLBACK = 2;
/*     */   public static final int MULTICAST_RELIABLE = 4;
/*     */   public static final int MQJMS_MULTICAST_DISABLED = 0;
/*     */   public static final int MQJMS_MULTICAST_AS_CF = -1;
/*     */   public static final int MQJMS_MULTICAST_NOT_RELIABLE = 3;
/*     */   public static final int MQJMS_MULTICAST_RELIABLE = 5;
/*     */   public static final int MQJMS_MULTICAST_ENABLED = 7;
/*     */   public static final int MQJMS_DIRECTAUTH_BASIC = 0;
/*     */   public static final int MQJMS_DIRECTAUTH_CERTIFICATE = 1;
/*     */   public static final int MQJMS_COMPHDR_NONE = 0;
/*     */   public static final int MQJMS_COMPHDR_SYSTEM = 8;
/*     */   public static final int MQJMS_COMPMSG_NONE = 0;
/*     */   public static final int MQJMS_COMPMSG_RLE = 1;
/*     */   public static final int MQJMS_COMPMSG_ZLIBFAST = 2;
/*     */   public static final int MQJMS_COMPMSG_ZLIBHIGH = 4;
/*     */   public static final boolean MAP_NAME_STYLE_STANDARD = true;
/*     */   public static final boolean MAP_NAME_STYLE_COMPATIBLE = false;
/*     */   @Deprecated
/*     */   public static final int MQCNO_SERIALIZE_CONN_TAG_Q_MGR = 2;
/*     */   @Deprecated
/*     */   public static final int MQCNO_SERIALIZE_CONN_TAG_QSG = 4;
/*     */   public static final int MQCNO_RESTRICT_CONN_TAG_Q_MGR = 8;
/*     */   public static final int MQCNO_RESTRICT_CONN_TAG_QSG = 16;
/*     */   public static final int MQCNO_STANDARD_BINDING = 0;
/*     */   public static final int MQCNO_FASTPATH_BINDING = 1;
/*     */   public static final int MQCNO_SHARED_BINDING = 256;
/*     */   public static final int MQCNO_ISOLATED_BINDING = 512;
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\JMSC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */