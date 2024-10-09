/*      */ package com.ibm.msg.client.wmq.compat.jms.internal.services;
/*      */ 
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class MQJMS_Messages
/*      */ {
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/services/MQJMS_Messages.java";
/*      */   public static final String MQJMS_PRODUCT_PREFIX = "MQJMS";
/*      */   public static final String NAMESPACE = "MQJMS";
/*      */   public static final String MQJMS_EXCEPTION_ILLEGAL_STATE = "MQJMS0000";
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
/*      */   public static final String MQJMS_EXCEPTION_INVALID_QUEUE_URI = "MQJMS0012";
/*      */   public static final String MQJMS_EXCEPTION_MSG_CREATE_ERROR = "MQJMS1000";
/*      */   public static final String MQJMS_EXCEPTION_UNKNOWN_ACK_MODE = "MQJMS1001";
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
/*      */   public static final String MQJMS_E_NON_LOCAL_RXQ = "MQJMS1017";
/*      */   
/*      */   static {
/*   42 */     if (Trace.isOn) {
/*   43 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.services.MQJMS_Messages", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/services/MQJMS_Messages.java");
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
/*      */   
/*      */   public static final String MQJMS_E_BAD_DEST_STR = "MQJMS1027";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_EXCEPTION_NULL_PROPERTY_NAME = "MQJMS1029";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_EXCEPTION_UNEXPECTED_ERROR = "MQJMS1031";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_E_START_FAILED = "MQJMS1033";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_E_DELIVERY_MODE_INVALID = "MQJMS1042";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_E_S390_DOUBLE_TOO_BIG = "MQJMS1045";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_E_INVALID_MAP_MESSAGE = "MQJMS1047";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_E_BYTE_TO_STRING = "MQJMS1049";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_MSG_CLASS = "MQJMS1051";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_E_INVALID_SURROGATE = "MQJMS1053";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_E_BAD_TYPE = "MQJMS1055";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_E_NO_SESSION = "MQJMS1057";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_E_NO_UTF8 = "MQJMS1059";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_E_DESERIALISE_FAILED = "MQJMS1061";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_CHARS_OMITTED = "MQJMS1063";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_E_COULD_NOT_WRITE = "MQJMS1065";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_E_BAD_TIMEOUT = "MQJMS1067";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_E_NOT_ALLOWED_WITH_XA = "MQJMS1069";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_E_QUEUE_NOT_LOCAL_OR_ALIAS = "MQJMS1073";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_E_DLH_WRITE_FAILED = "MQJMS1075";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_E_CONN_DEST_MISMATCH = "MQJMS1077";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_E_DLQ_FAILED = "MQJMS1079";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_E_REQUEUE_FAILED = "MQJMS1081";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_E_RFH_WRITE_FAILED = "MQJMS1085";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_E_RFH_CONTENTS_ERROR = "MQJMS1087";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_E_READING_MSG = "MQJMS1089";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_E_NULL_PARAMETER = "MQJMS1093";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_E_INVALID_MESSAGE_REFERENCE = "MQJMS1096";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_E_INVALID_THREAD_VERSION = "MQJMS1099";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_E_MULTICAST_LOST_MESSAGES = "MQJMS1103";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_E_MULTICAST_PORT_INVALID = "MQJMS1105";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_EXP_LSTNR_EVENT = "MQJMS1107";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_E_11_SERVICES_NOT_SETUP = "MQJMS1111";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_E_11_INVALID_CROSS_DOMAIN = "MQJMS1113";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_E_ALIAS_BASE_INQUIRE_FAIL = "MQJMS1115";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_MESSAGE_MOVED_TO_DLQ = "MQJMS1116";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_MESSAGE_DISCARDED = "MQJMS1117";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_EXCEPTION_MQ_NULL_Q = "MQJMS2001";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_EXCEPTION_QMDISC_FAILED = "MQJMS2003";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_EXCEPTION_QMGR_FAILED = "MQJMS2005";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_EXCEPTION_PUT_MSG_FAILED = "MQJMS2007";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_EXCEPTION_MQ_QM_COMMIT_FAILED = "MQJMS2009";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_EXCEPTION_MQ_Q_INQUIRE_FAILED = "MQJMS2011";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_EXCEPTION_AUTHENTICATION_FAILED = "MQJMS2013";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_E_TMPQ_FAILED = "MQJMS3000";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_E_TMPQ_INUSE = "MQJMS3002";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_E_TMPQ_DEL_FAILED = "MQJMS3004";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_PS_TOPIC_NULL = "MQJMS3006";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_PS_COMMAND_MSG_FAILED = "MQJMS3009";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_PS_PUBLISH_MSG_FAILED = "MQJMS3011";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_PS_SUB_Q_OPEN_FAILED = "MQJMS3014";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_PS_UNKNOWN_DS = "MQJMS3018";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_E_TMPT_OUTOFSCOPE = "MQJMS3020";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_PS_SUBQ_REQUEUE = "MQJMS3022";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_PS_NULL_CLIENTID = "MQJMS3024";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_ERR_QSENDER_CLOSED = "MQJMS3026";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_CLIENTID_FIXED = "MQJMS3031";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_QRECEIVER_CLOSED = "MQJMS3033";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_MESSAGEPRODUCER_CLOSED = "MQJMS3037";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_PS_NULL_NAME = "MQJMS3039";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_E_ALREADY_SET = "MQJMS3041";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_E_CLEANUP_REP_BAD_LEVEL = "MQJMS3043";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_E_CLEANUP_Q_OPEN_1 = "MQJMS3045";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_PS_SUBSTORE_NOT_SUPPORTED = "MQJMS3047";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_PS_WRONG_SUBSCRIPTION_TYPE = "MQJMS3049";
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_PS_INVALID_SUB_NAME = "MQJMS3051";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_PS_CLEANUP_STARTED = "MQJMS3052";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_PS_CLEANUP_FINISHED = "MQJMS3053";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQJMS_PS_CLEANUP_WILL_START_AGAIN_IN_XX_MINUTES = "MQJMS3054";
/*      */ 
/*      */ 
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
/*      */ 
/*      */   
/*      */   public static final String MQJMS_ADMIN_INV_PROP = "MQJMS4125";
/*      */ 
/*      */ 
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
/*      */ 
/*      */   
/*      */   public static final String MQJMS_UTIL_PS_NO_BROKER = "MQJMS5053";
/*      */ 
/*      */ 
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
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_IMB_BADSOCKNAME = "MQJMS6040";
/*      */ 
/*      */ 
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
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MIN_NOMORE = "MQJMS6056";
/*      */ 
/*      */ 
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
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MIN_BADGET = "MQJMS6058";
/*      */ 
/*      */ 
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
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MIN_UNXEXC = "MQJMS6060";
/*      */ 
/*      */ 
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
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MIN_EOF = "MQJMS6062";
/*      */ 
/*      */ 
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
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MIN_BADMSG = "MQJMS6064";
/*      */ 
/*      */ 
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
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MIN_INTERR = "MQJMS6066";
/*      */ 
/*      */ 
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
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MIN_NOTTEXT = "MQJMS6068";
/*      */ 
/*      */ 
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
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MIN_NOTMAP = "MQJMS6070";
/*      */ 
/*      */ 
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
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MIN_UNVPRO = "MQJMS6072";
/*      */ 
/*      */ 
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
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MIN_NOQOP = "MQJMS6074";
/*      */ 
/*      */ 
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
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_JMS_FMTINT = "MQJMS6081";
/*      */ 
/*      */ 
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
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_JMS_NEXCLIS = "MQJMS6085";
/*      */ 
/*      */ 
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
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_JMS_LSTACT = "MQJMS6090";
/*      */ 
/*      */ 
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
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_JMS_RUNKEXC = "MQJMS6093";
/*      */ 
/*      */ 
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
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_JMS_BADID = "MQJMS6097";
/*      */ 
/*      */ 
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
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_JMS_BADNUM = "MQJMS6106";
/*      */ 
/*      */ 
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
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_JMS_CLOSED = "MQJMS6116";
/*      */ 
/*      */ 
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
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_JMS_PBNOWLD = "MQJMS6118";
/*      */ 
/*      */ 
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
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_JMS_TMPVIO = "MQJMS6120";
/*      */ 
/*      */ 
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
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_JMS_TSBADMTC = "MQJMS6232";
/*      */ 
/*      */ 
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
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MTCH_NULRM = "MQJMS6234";
/*      */ 
/*      */ 
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
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MTCH_BDTYP = "MQJMS6236";
/*      */ 
/*      */ 
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
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MTCH_BDMSG = "MQJMS6238";
/*      */ 
/*      */ 
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
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MTCH_ECNMIN = "MQJMS6240";
/*      */ 
/*      */ 
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
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MTCH_DUPDET = "MQJMS6242";
/*      */ 
/*      */ 
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
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MTCH_NOSUB = "MQJMS6244";
/*      */ 
/*      */ 
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
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MTCH_BDWLD = "MQJMS6246";
/*      */ 
/*      */ 
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
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MTCH_CNTLD = "MQJMS6248";
/*      */ 
/*      */ 
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
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MTCH_BDESC = "MQJMS6250";
/*      */ 
/*      */ 
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
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MTCH_UNXTYP = "MQJMS6252";
/*      */ 
/*      */ 
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
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MIN_QOPDIS = "MQJMS6229";
/*      */ 
/*      */ 
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
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MIN_NOXASUP = "MQJMS6311";
/*      */ 
/*      */ 
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
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MIN_TSBADSYN = "MQJMS6351";
/*      */ 
/*      */ 
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
/*      */ 
/*      */   
/*      */   public static final String MQJMS_DIR_MIN_TTL_NOT_SUPPORTED = "MQJMS6402";
/*      */ 
/*      */ 
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
/*      */   
/*      */   public static final String MQJMS_JMS_2_NOT_SUPPORTED = "MQJMS6405";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String CICS_PVER6_NOT_SUPPORTED = "MQJMS6406";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String IMS_PVER6_NOT_SUPPORTED = "MQJMS6407";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   MQJMS_Messages() {
/* 2037 */     if (Trace.isOn) {
/* 2038 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.services.MQJMS_Messages", "<init>()");
/*      */     }
/*      */ 
/*      */     
/* 2042 */     if (Trace.isOn)
/* 2043 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.services.MQJMS_Messages", "<init>()"); 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\services\MQJMS_Messages.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */