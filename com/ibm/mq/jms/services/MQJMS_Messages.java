/*      */ package com.ibm.mq.jms.services;
/*      */ 
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class MQJMS_Messages
/*      */ {
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/services/MQJMS_Messages.java";
/*      */   static final String MQJMS_PRODUCT_PREFIX = "MQJMS";
/*      */   public static final String MQJMS_EXCEPTION_ILLEGAL_STATE = "MQJMS0000";
/*      */   public static final String MQJMS_EXCEPTION_SECURITY = "MQJMS0001";
/*      */   public static final String MQJMS_EXCEPTION_INVALID_CLIENTID = "MQJMS0002";
/*      */   public static final String MQJMS_EXCEPTION_INVALID_DESTINATION = "MQJMS0003";
/*      */   public static final String MQJMS_EXCEPTION_INVALID_SELECTOR = "MQJMS0004";
/*      */   public static final String MQJMS_EXCEPTION_MESSAGE_EOF = "MQJMS0005";
/*      */   public static final String MQJMS_EXCEPTION_MESSAGE_FORMAT = "MQJMS0006";
/*      */   public static final String MQJMS_EXCEPTION_MESSAGE_NOT_READABLE = "MQJMS0007";
/*      */   public static final String MQJMS_EXCEPTION_MESSAGE_NOT_WRITABLE = "MQJMS0008";
/*      */   public static final String MQJMS_EXCEPTION_RESOURCE_ALLOCATION = "MQJMS0009";
/*      */   public static final String MQJMS_EXCEPTION_TRANSACTION_IN_PROGRESS = "MQJMS0010";
/*      */   public static final String MQJMS_EXCEPTION_TRANSACTION_ROLLED_BACK = "MQJMS0011";
/*      */   public static final String MQJMS_EXCEPTION_MSG_CREATE_ERROR = "MQJMS1000";
/*      */   public static final String MQJMS_EXCEPTION_UNKNOWN_ACK_MODE = "MQJMS1001";
/*      */   public static final String MQJMS_PRODUCT_NAME = "MQJMS1002";
/*      */   public static final String MQJMS_PRODUCT_COPYRIGHT = "MQJMS1003";
/*      */   public static final String MQJMS_EXCEPTION_CONNECTION_CLOSED = "MQJMS1004";
/*      */   public static final String MQJMS_EXCEPTION_BAD_STATE_TRANSITION = "MQJMS1005";
/*      */   public static final String MQJMS_EXCEPTION_BAD_VALUE = "MQJMS1006";
/*      */   public static final String MQJMS_E_BAD_EXIT_CLASS = "MQJMS1007";
/*      */   public static final String MQJMS_E_UNKNOWN_TRANSPORT = "MQJMS1008";
/*      */   public static final String MQJMS_E_NO_STR_CONSTRUCTOR = "MQJMS1009";
/*      */   public static final String MQJMS_E_NOT_IMPLEMENTED = "MQJMS1010";
/*      */   public static final String MQJMS_E_SECURITY_CREDS_INVALID = "MQJMS1011";
/*      */   public static final String MQJMS_E_NO_MSG_LISTENER = "MQJMS1012";
/*      */   public static final String MQJMS_E_SESSION_ASYNC = "MQJMS1013";
/*      */   public static final String MQJMS_E_IDENT_PRO_INVALID_OP = "MQJMS1014";
/*      */   public static final String MQJMS_E_UNKNOWN_TARGET_CLIENT = "MQJMS1015";
/*      */   public static final String MQJMS_E_INTERNAL_ERROR = "MQJMS1016";
/*      */   
/*      */   static {
/*   45 */     if (Trace.isOn) {
/*   46 */       Trace.data("com.ibm.mq.jms.services.MQJMS_Messages", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/services/MQJMS_Messages.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_NON_LOCAL_RXQ = "MQJMS1017";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_NULL_CONNECTION = "MQJMS1018";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_SESSION_NOT_TRANSACTED = "MQJMS1019";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_SESSION_IS_TRANSACTED = "MQJMS1020";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_RECOVER_BO_FAILED = "MQJMS1021";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_REDIRECT_FAILED = "MQJMS1022";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_ROLLBACK_FAILED = "MQJMS1023";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_SESSION_CLOSED = "MQJMS1024";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_BROWSE_MSG_FAILED = "MQJMS1025";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_EXCP_LSTNR_FAILED = "MQJMS1026";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_BAD_DEST_STR = "MQJMS1027";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_EXCEPTION_NULL_ELEMENT_NAME = "MQJMS1028";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_EXCEPTION_NULL_PROPERTY_NAME = "MQJMS1029";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_EXCEPTION_BUFFER_TOO_SMALL = "MQJMS1030";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_EXCEPTION_UNEXPECTED_ERROR = "MQJMS1031";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_CLOSE_FAILED = "MQJMS1032";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_START_FAILED = "MQJMS1033";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_MSG_LSTNR_FAILED = "MQJMS1034";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_INVALID_MESSAGE = "MQJMS1035";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_RESOURCE_BUNDLE_NOT_FOUND = "MQJMS1036";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_LOG_INITIALIZATION_FAILED = "MQJMS1037";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_LOG_ERROR = "MQJMS1038";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_TRACE_FILE_NOT_FOUND = "MQJMS1039";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_TRACE_STREAM_ERROR = "MQJMS1040";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_SYSTEM_PROPERTY_NOT_FOUND = "MQJMS1041";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_DELIVERY_MODE_INVALID = "MQJMS1042";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_JNDI_GENERAL_ERROR = "MQJMS1043";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_INVALID_HEX_STRING = "MQJMS1044";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_S390_DOUBLE_TOO_BIG = "MQJMS1045";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_BAD_CCSID = "MQJMS1046";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_INVALID_MAP_MESSAGE = "MQJMS1047";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_INVALID_STREAM_MESSAGE = "MQJMS1048";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_BYTE_TO_STRING = "MQJMS1049";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_BAD_RFH2 = "MQJMS1050";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_MSG_CLASS = "MQJMS1051";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_BAD_MSG_CLASS = "MQJMS1052";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_INVALID_SURROGATE = "MQJMS1053";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_INVALID_ESCAPE = "MQJMS1054";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_BAD_TYPE = "MQJMS1055";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_UNSUPPORTED_TYPE = "MQJMS1056";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_NO_SESSION = "MQJMS1057";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_BAD_PROPERTY_NAME = "MQJMS1058";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_NO_UTF8 = "MQJMS1059";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_SERIALISE_FAILED = "MQJMS1060";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_DESERIALISE_FAILED = "MQJMS1061";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_EXCEPTION_HAPPENED = "MQJMS1062";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_CHARS_OMITTED = "MQJMS1063";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ENCODINGS = "MQJMS1064";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_COULD_NOT_WRITE = "MQJMS1065";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_BAD_ELEMENT_NAME = "MQJMS1066";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_BAD_TIMEOUT = "MQJMS1067";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_NO_XARESOURCE = "MQJMS1068";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_NOT_ALLOWED_WITH_XA = "MQJMS1069";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_WSAE_ENLIST_FAILED = "MQJMS1070";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_WSAE_UNK_INFO = "MQJMS1071";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_QMGR_NAME_INQUIRE_FAILED = "MQJMS1072";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_QUEUE_NOT_LOCAL_OR_ALIAS = "MQJMS1073";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_NULL_MESSAGE = "MQJMS1074";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_DLH_WRITE_FAILED = "MQJMS1075";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_DLH_READ_FAILED = "MQJMS1076";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_CONN_DEST_MISMATCH = "MQJMS1077";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_INVALID_SESSION = "MQJMS1078";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_DLQ_FAILED = "MQJMS1079";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_NO_BORQ = "MQJMS1080";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_REQUEUE_FAILED = "MQJMS1081";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_DISCARD_FAILED = "MQJMS1082";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_BATCH_SIZE = "MQJMS1083";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_NULL_POOL = "MQJMS1084";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_RFH_WRITE_FAILED = "MQJMS1085";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_RFH_READ_FAILED = "MQJMS1086";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_RFH_CONTENTS_ERROR = "MQJMS1087";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_CC_MIXED_DOMAIN = "MQJMS1088";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_READING_MSG = "MQJMS1089";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_MQCF_NOT_SERIALIZABLE = "MQJMS1090";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_UNIDENT_PRO_INVALID_OP = "MQJMS1091";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_USERNAME_TOO_LONG = "MQJMS1092";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_NULL_PARAMETER = "MQJMS1093";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_INVALID_QUANTITY_HINT = "MQJMS1094";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_INVALID_DATA_QUANTITY = "MQJMS1095";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_INVALID_MESSAGE_REFERENCE = "MQJMS1096";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_INVALID_MSG_REF_HEADER = "MQJMS1097";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_INVALID_MSG_REF_VERSION = "MQJMS1098";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_INVALID_THREAD_VERSION = "MQJMS1099";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_NULL_MSG_REF = "MQJMS1100";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_NULL_MSG_REF_HANDLER = "MQJMS1101";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_MULTICAST_NOT_AVAILABLE = "MQJMS1102";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_MULTICAST_LOST_MESSAGES = "MQJMS1103";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_MULTICAST_HEARTBEAT_TIMEOUT = "MQJMS1104";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_MULTICAST_PORT_INVALID = "MQJMS1105";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_PGM_LIB_NOT_FOUND = "MQJMS1106";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_11_NOTSUPPORTED = "MQJMS1110";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_11_SERVICES_NOT_SETUP = "MQJMS1111";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_11_INVALID_DOMAIN_SPECIFIC = "MQJMS1112";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_11_INVALID_CROSS_DOMAIN = "MQJMS1113";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_11_INVALID_ATTRIBUTE = "MQJMS1114";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_EXCEPTION_MQ_Q_CLOSE_FAILED = "MQJMS2000";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_EXCEPTION_MQ_NULL_Q = "MQJMS2001";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_EXCEPTION_GET_MSG_FAILED = "MQJMS2002";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_EXCEPTION_QMDISC_FAILED = "MQJMS2003";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_EXCEPTION_MQ_NULL_QMGR = "MQJMS2004";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_EXCEPTION_QMGR_FAILED = "MQJMS2005";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_EXCEPTION_SOME_PROBLEM = "MQJMS2006";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_EXCEPTION_PUT_MSG_FAILED = "MQJMS2007";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_EXCEPTION_MQ_Q_OPEN_FAILED = "MQJMS2008";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_EXCEPTION_MQ_QM_COMMIT_FAILED = "MQJMS2009";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_EXCEPTION_MQ_UNKNOWN_DEFTYPE = "MQJMS2010";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_EXCEPTION_MQ_Q_INQUIRE_FAILED = "MQJMS2011";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_EXCEPTION_XACLOSE_FAILED = "MQJMS2012";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_EXCEPTION_AUTHENTICATION_FAILED = "MQJMS2013";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_EXCEPTION_XACLIENT_FAILED = "MQJMS2014";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_TMPQ_FAILED = "MQJMS3000";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_TMPQ_CLOSED = "MQJMS3001";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_TMPQ_INUSE = "MQJMS3002";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_TMPQ_DEL_STATIC = "MQJMS3003";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_TMPQ_DEL_FAILED = "MQJMS3004";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_PS_GENERAL_ERROR = "MQJMS3005";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_PS_TOPIC_NULL = "MQJMS3006";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_PS_COMMAND_INVALID = "MQJMS3007";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_PS_COMMAND_MSG_BUILD = "MQJMS3008";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_PS_COMMAND_MSG_FAILED = "MQJMS3009";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_PS_PUBLISH_MSG_BUILD = "MQJMS3010";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_PS_PUBLISH_MSG_FAILED = "MQJMS3011";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_PS_PUBLISH_PARAMETER = "MQJMS3012";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_PS_STORE_ADMIN_ENTRY = "MQJMS3013";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_PS_SUB_Q_OPEN_FAILED = "MQJMS3014";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_PS_SUB_Q_CREATE_FAILED = "MQJMS3015";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_PS_SUBSCRIBE_PARAMETER = "MQJMS3016";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_PS_SUB_Q_DELETE_FAILED = "MQJMS3017";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_PS_UNKNOWN_DS = "MQJMS3018";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_TMPT_DELETED = "MQJMS3019";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_TMPT_OUTOFSCOPE = "MQJMS3020";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_PS_INVALID_SUBQ_PREFIX = "MQJMS3021";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_PS_SUBQ_REQUEUE = "MQJMS3022";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_PS_SUB_ACTIVE = "MQJMS3023";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_PS_NULL_CLIENTID = "MQJMS3024";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_TMPT_IN_USE = "MQJMS3025";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ERR_QSENDER_CLOSED = "MQJMS3026";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_LOCAL_XA_CLASH = "MQJMS3027";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_PUBLISHER_CLOSED = "MQJMS3028";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_EXC_ENLIST_FAILED = "MQJMS3029";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ENLIST_FAILED = "MQJMS3030";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_CLIENTID_FIXED = "MQJMS3031";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_CLIENTID_NO_RESET = "MQJMS3032";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_QRECEIVER_CLOSED = "MQJMS3033";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_SUBSCRIBER_CLOSED = "MQJMS3034";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_SEEK0_FAILED = "MQJMS3035";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_MSEL_AND_BVER_INCOMPATIBLE = "MQJMS3036";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_MESSAGEPRODUCER_CLOSED = "MQJMS3037";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_MESSAGECONSUMER_CLOSED = "MQJMS3038";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_PS_NULL_NAME = "MQJMS3039";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_BROKER_MESSAGE_CONTENT = "MQJMS3040";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_ALREADY_SET = "MQJMS3041";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_UNREC_BROKER_MESSAGE = "MQJMS3042";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_CLEANUP_REP_BAD_LEVEL = "MQJMS3043";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_CLEANUP_NONE_REQUESTED = "MQJMS3044";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_CLEANUP_Q_OPEN_1 = "MQJMS3045";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_CLEANUP_Q_OPEN_2 = "MQJMS3046";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_PS_SUBSTORE_NOT_SUPPORTED = "MQJMS3047";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_PS_INCORRECT_SUBSTORE = "MQJMS3048";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_PS_WRONG_SUBSCRIPTION_TYPE = "MQJMS3049";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_PS_SUBSCRIPTION_IN_USE = "MQJMS3050";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_PS_INVALID_SUB_NAME = "MQJMS3051";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_START = "MQJMS4000";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_STOP = "MQJMS4001";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_INIT = "MQJMS4002";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_DONE = "MQJMS4003";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_FAILED = "MQJMS4004";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_OK = "MQJMS4005";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_CANCEL = "MQJMS4006";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_INITCTX = "MQJMS4007";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_ERROR = "MQJMS4008";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_CTX_NOT_EMPTY = "MQJMS4009";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_BND_NOT_FOUND = "MQJMS4010";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_GUI_START = "MQJMS4011";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_WELCOME = "MQJMS4012";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_NEW_BND = "MQJMS4013";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_EDIT_BND = "MQJMS4014";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_GENERAL = "MQJMS4015";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_GENERAL_PROPS = "MQJMS4016";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_TRANS = "MQJMS4017";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_TRANS_PROPS = "MQJMS4018";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_BROKER = "MQJMS4019";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_BROKER_PROPS = "MQJMS4020";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_ADDRESSING = "MQJMS4021";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_DEST_ADDR = "MQJMS4022";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_CLIENTID = "MQJMS4023";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_BROKER_NAME = "MQJMS4024";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_CONTROL_Q = "MQJMS4025";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_QUEUE = "MQJMS4026";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_QMGR = "MQJMS4027";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_PUBSUBQ = "MQJMS4028";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_BROKER_ATTR = "MQJMS4029";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_ADDRESS = "MQJMS4030";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_COMPLIANT = "MQJMS4031";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_TRADITIONAL = "MQJMS4032";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_CLIENT_TYPE = "MQJMS4033";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_CHARSET = "MQJMS4034";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_ENCODING = "MQJMS4035";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_FORMAT = "MQJMS4036";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_ADDR_ATTR = "MQJMS4037";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_HOSTNAME = "MQJMS4038";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_PORT = "MQJMS4039";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_CHANNEL = "MQJMS4040";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_CCSID = "MQJMS4041";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_REC_EXIT = "MQJMS4042";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_SEC_EXIT = "MQJMS4043";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_SEND_EXIT = "MQJMS4044";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_FASTPATH = "MQJMS4045";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_TEMP_MODELQ = "MQJMS4046";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_TRANS_ATTR = "MQJMS4047";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_OBJ_ALIAS = "MQJMS4048";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_VERSION = "MQJMS4049";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_ID = "MQJMS4050";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_BINDINGS = "MQJMS4051";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_CLIENT = "MQJMS4052";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_TRANS_TYPE = "MQJMS4053";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_NO_BROKER = "MQJMS4054";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_ARGO = "MQJMS4055";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_FUJI = "MQJMS4056";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_BROKER_TYPE = "MQJMS4057";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_EXPIRY = "MQJMS4058";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_PRIORITY = "MQJMS4059";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_OVERRIDE_WITH = "MQJMS4060";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_OVERRIDE_JMS = "MQJMS4061";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_QNAME = "MQJMS4062";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_TNAME = "MQJMS4063";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_DNAME = "MQJMS4064";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_CLICK_STORE = "MQJMS4065";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_CLICK_CANCEL = "MQJMS4066";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_DIR_SETTINGS = "MQJMS4067";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_STORE = "MQJMS4068";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_CLICK_CONT = "MQJMS4069";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_CLICK_ABORT = "MQJMS4070";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_OBJ_TYPE = "MQJMS4071";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_SELECT_CLASS = "MQJMS4072";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_CONT_ARROW = "MQJMS4073";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_NEW_SUBCTX = "MQJMS4074";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_NEW_CTX = "MQJMS4075";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_CTX_NAME = "MQJMS4076";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_REM_SUBCTX = "MQJMS4077";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_UPD_BND = "MQJMS4078";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_REM_BND = "MQJMS4079";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_CONFIG_DIR = "MQJMS4080";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_OBTAIN_HELP = "MQJMS4081";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_QUIT_TOOL = "MQJMS4082";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_REM_CTX_SUCC = "MQJMS4083";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_REM_BND_SUCC = "MQJMS4084";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_WELCOME_CLI = "MQJMS4085";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_ERROR_CMD = "MQJMS4086";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_UNKNOWN_CMD = "MQJMS4087";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_VALID_SYNTAX = "MQJMS4088";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_CONTENTS_OF = "MQJMS4089";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_TCPIP = "MQJMS4090";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_HTTP = "MQJMS4091";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_MQ = "MQJMS4092";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_MQHAWTHORNE = "MQJMS4093";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_ICF_ID = "MQJMS4094";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_PURL_ID = "MQJMS4095";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_BND_NONADMIN = "MQJMS4096";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_CTX_NOTFND = "MQJMS4097";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_OBJ_S = "MQJMS4098";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_CTX_S = "MQJMS4099";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_BND_S = "MQJMS4100";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_ADMINISTERED = "MQJMS4101";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_UNCONF_SERV = "MQJMS4102";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_SP_UNSUPP = "MQJMS4103";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_OBJ_INACTIVE = "MQJMS4104";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_CONF_MISSING = "MQJMS4105";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_NON_MQJMS = "MQJMS4106";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_INV_CMD_FMT = "MQJMS4107";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_UNIMPLEMENTED = "MQJMS4108";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_NOT_IMPL = "MQJMS4109";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_JNDI_INITFAIL = "MQJMS4110";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_NEW_CTX_FAIL = "MQJMS4111";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_VAL_OBJ_FAIL = "MQJMS4112";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_BIND_FAIL = "MQJMS4113";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_GUI_DISABLED = "MQJMS4114";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_INVALID_NAME = "MQJMS4115";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_UNKNOWN_ERROR = "MQJMS4116";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_PERSISTENCE = "MQJMS4117";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_TOOL_NAME = "MQJMS4118";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_NOSTART = "MQJMS4119";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_SYN_ERR = "MQJMS4120";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_CNT_OPEN_CFG = "MQJMS4121";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_MV_SEMIFAIL = "MQJMS4122";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_LEXERR = "MQJMS4123";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_PROPVAL_NULL = "MQJMS4124";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_INV_PROP = "MQJMS4125";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_MISS_PROP = "MQJMS4126";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_INV_PROP_CTX = "MQJMS4127";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_INV_PROP_VAL = "MQJMS4128";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_PROP_UNK = "MQJMS4129";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_CTX_NOTFNDU = "MQJMS4130";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_OBJTYPE_MISMATCH = "MQJMS4131";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_CLASH_CLIENT = "MQJMS4132";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_CLASH_EXITINIT = "MQJMS4133";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_USERDN = "MQJMS4134";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_USERPW = "MQJMS4135";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_XACF_TRANS = "MQJMS4136";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_WS_INST = "MQJMS4137";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_NONSHARED_BRKR_Q = "MQJMS4138";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_INVALID_AUTH_TYPE = "MQJMS4139";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_CONVERT_CIPHER = "MQJMS4140";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_NOCONVERT_CIPHER = "MQJMS4141";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_ICF_NOT_FOUND = "MQJMS4142";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_CNT_PARSE_OBJECT = "MQJMS4143";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_IVTNAME = "MQJMS5000";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_IGNOREURL = "MQJMS5001";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_IGNOREICF = "MQJMS5002";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_IGNOREUNK = "MQJMS5003";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_SPECIFYURL = "MQJMS5004";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_USINGADMINOBJ = "MQJMS5005";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_INITCTXFAIL = "MQJMS5006";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_QCFCREATE = "MQJMS5007";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_QCFGET = "MQJMS5008";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_QCFFAIL = "MQJMS5009";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_CONNCREATE = "MQJMS5010";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_SESSCREATE = "MQJMS5011";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_QCREATE = "MQJMS5012";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_QGET = "MQJMS5013";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_QFAIL = "MQJMS5014";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_QSCREATE = "MQJMS5015";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_QRCREATE = "MQJMS5016";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_MSGCREATE = "MQJMS5017";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_MSGSEND = "MQJMS5018";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_MSGGET = "MQJMS5019";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_MSGFAIL = "MQJMS5020";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_EXCMSGFAIL = "MQJMS5021";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_MSGGOT = "MQJMS5022";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_MSGEQUALS = "MQJMS5023";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_MSGDIFF = "MQJMS5024";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_MSGORIG = "MQJMS5025";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_MSGREPLY = "MQJMS5026";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_MSGNOTTEXT = "MQJMS5027";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_MSGTYPE = "MQJMS5028";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_QRCLOSE = "MQJMS5029";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_QSCLOSE = "MQJMS5030";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_SESSCLOSE = "MQJMS5031";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_CONNCLOSE = "MQJMS5032";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_IVTOK = "MQJMS5033";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_EXCCAUGHT = "MQJMS5034";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_IVTFINISH = "MQJMS5035";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_NO_PARAM_VALUE = "MQJMS5036";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_NO_CLASS = "MQJMS5037";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_NO_JNDI_PROV = "MQJMS5038";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_PS_NAME = "MQJMS5039";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_PS_RETREIVE_TCF = "MQJMS5040";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_PS_NO_TCF = "MQJMS5041";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_PS_RETRIEVE_TOPIC = "MQJMS5042";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_PS_NO_TOPIC = "MQJMS5043";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_PS_CREATE_TCF = "MQJMS5044";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_PS_CREATE_TCF_FAILED = "MQJMS5045";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_PS_CREATE_TOPIC = "MQJMS5046";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_PS_CREATE_TOPIC_FAILED = "MQJMS5047";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_PS_CREATE_PUB = "MQJMS5048";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_PS_CREATE_SUB = "MQJMS5049";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_PS_ADD_TXT = "MQJMS5050";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_PS_PUB_MSG = "MQJMS5051";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_PS_WAITING = "MQJMS5052";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_PS_NO_BROKER = "MQJMS5053";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_PS_NO_MSG = "MQJMS5054";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_PS_FAILED = "MQJMS5055";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_PS_CLOSE_SUB = "MQJMS5056";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_PS_CLOSE_PUB = "MQJMS5057";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_PS_LINKED_E = "MQJMS5058";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_PS_FINISHED = "MQJMS5059";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_PS_NO_QM = "MQJMS5060";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_PS_NO_BRK_Q = "MQJMS5061";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_PS_INSTALL_BRK = "MQJMS5062";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_PS_BIR_EXCPTN = "MQJMS5063";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_PORT_NOT_NUMBER = "MQJMS5064";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_USAGE_INTRO = "MQJMS5065";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_USAGE_LINE1 = "MQJMS5066";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_USAGE_LINE2 = "MQJMS5067";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_USAGE_LINE3 = "MQJMS5068";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_PS_USAGE_INTRO = "MQJMS5069";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_PS_USAGE_LINE1 = "MQJMS5070";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_PS_USAGE_LINE2 = "MQJMS5071";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_PS_USAGE_LINE3 = "MQJMS5072";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_NON_NUMERIC = "MQJMS5073";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_UNREC_PARAM = "MQJMS5074";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_NO_ARGUMENT = "MQJMS5075";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_CLEANUP_NO_LEVEL = "MQJMS5076";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_CLEANUP_NO_HOSTNAME = "MQJMS5077";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_CLEANUP_CLIENT_NOT_SET = "MQJMS5078";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_CLEANUP_NAME = "MQJMS5079";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_CLEANUP_USAGE_INTRO = "MQJMS5080";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_CLEANUP_USAGE_LINE1 = "MQJMS5081";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_CLEANUP_USAGE_LINE2 = "MQJMS5082";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_CLEANUP_USAGE_LINE3 = "MQJMS5083";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_CLEANUP_COMPLETE = "MQJMS5084";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_CLEANUP_EXCEPTION = "MQJMS5085";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_CLEANUP_LINKED = "MQJMS5086";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_PS_INTERNALQ = "MQJMS5087";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_IMB_BADSOCKNAME = "MQJMS6040";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_IMB_NOCLASS = "MQJMS6041";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MIN_NOMORE = "MQJMS6056";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MIN_BADSET = "MQJMS6057";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MIN_BADGET = "MQJMS6058";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MIN_SECLDERR = "MQJMS6059";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MIN_UNXEXC = "MQJMS6060";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MIN_BADTOP = "MQJMS6061";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MIN_EOF = "MQJMS6062";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MIN_BRKERR = "MQJMS6063";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MIN_BADMSG = "MQJMS6064";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MIN_BADFIELD = "MQJMS6065";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MIN_INTERR = "MQJMS6066";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MIN_NOTBYTES = "MQJMS6067";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MIN_NOTTEXT = "MQJMS6068";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MIN_NOTSTREAM = "MQJMS6069";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MIN_NOTMAP = "MQJMS6070";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MIN_BADBRKMSG = "MQJMS6071";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MIN_UNVPRO = "MQJMS6072";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MIN_AUTHREJ = "MQJMS6073";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MIN_NOQOP = "MQJMS6074";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_JMS_NOTHDPOOL = "MQJMS6079";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_JMS_FMTINT = "MQJMS6081";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_JMS_THDEXC = "MQJMS6083";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_JMS_NEXCLIS = "MQJMS6085";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_JMS_KILLMON = "MQJMS6088";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_JMS_LSTACT = "MQJMS6090";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_JMS_TCSTSTP = "MQJMS6091";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_JMS_RUNKEXC = "MQJMS6093";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_JMS_INVPRI = "MQJMS6096";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_JMS_BADID = "MQJMS6097";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_JMS_NOMORE = "MQJMS6105";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_JMS_BADNUM = "MQJMS6106";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_JMS_TCFLERR = "MQJMS6115";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_JMS_CLOSED = "MQJMS6116";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_JMS_BDTOPIMPL = "MQJMS6117";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_JMS_PBNOWLD = "MQJMS6118";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_JMS_PBIOERR = "MQJMS6119";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_JMS_TMPVIO = "MQJMS6120";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_JMS_TSIOERR = "MQJMS6121";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_JMS_TSBADMTC = "MQJMS6232";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MTCH_UNKEXC = "MQJMS6233";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MTCH_NULRM = "MQJMS6234";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MTCH_NULCH = "MQJMS6235";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MTCH_BDTYP = "MQJMS6236";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MTCH_UNKNM = "MQJMS6237";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MTCH_BDMSG = "MQJMS6238";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MTCH_ECPREP = "MQJMS6239";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MTCH_ECNMIN = "MQJMS6240";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MTCH_TOMNY = "MQJMS6241";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MTCH_DUPDET = "MQJMS6242";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MTCH_NOTPK = "MQJMS6243";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MTCH_NOSUB = "MQJMS6244";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MTCH_NLTOP = "MQJMS6245";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MTCH_BDWLD = "MQJMS6246";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MTCH_BDSEP = "MQJMS6247";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MTCH_CNTLD = "MQJMS6248";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MTCH_PSTPER = "MQJMS6249";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MTCH_BDESC = "MQJMS6250";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MTCH_BDESCL = "MQJMS6251";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MTCH_UNXTYP = "MQJMS6252";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MIN_AUTHEXC = "MQJMS6228";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MIN_QOPDIS = "MQJMS6229";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MIN_NOSUB = "MQJMS6312";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MIN_NOXASUP = "MQJMS6311";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MIN_NOTOBJECT = "MQJMS6350";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MIN_TSBADSYN = "MQJMS6351";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MIN_PER_NOT_SUPPORTED = "MQJMS6401";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MIN_TTL_NOT_SUPPORTED = "MQJMS6402";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MIN_EXP_NOT_SUPPORTED = "MQJMS6403";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MIN_ACK_NOT_SUPPORTED = "MQJMS6404";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   MQJMS_Messages() {
/* 3530 */     if (Trace.isOn) {
/* 3531 */       Trace.entry(this, "com.ibm.mq.jms.services.MQJMS_Messages", "<init>()");
/*      */     }
/*      */     
/* 3534 */     if (Trace.isOn)
/* 3535 */       Trace.exit(this, "com.ibm.mq.jms.services.MQJMS_Messages", "<init>()"); 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\services\MQJMS_Messages.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */