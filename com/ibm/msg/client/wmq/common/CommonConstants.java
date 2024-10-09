package com.ibm.msg.client.wmq.common;

import com.ibm.msg.client.jms.JmsConstants;

public interface CommonConstants extends JmsConstants {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/CommonConstants.java";
  
  public static final String WMQ_APPLICATIONNAME = "XMSC_WMQ_APPNAME";
  
  public static final String WMQ_BROKER_CC_DUR_SUBQ = "brokerCCDurSubQueue";
  
  public static final String WMQ_BROKER_CC_SUBQ = "XMSC_WMQ_BROKER_CC_SUBQ";
  
  public static final String WMQ_BROKER_CONTROLQ = "XMSC_WMQ_BROKER_CONTROLQ";
  
  public static final String WMQ_BROKER_DUR_SUBQ = "brokerDurSubQueue";
  
  public static final String WMQ_BROKER_PUBQ = "XMSC_WMQ_BROKER_PUBQ";
  
  public static final String WMQ_BROKER_PUBQ_QMGR = "XMSC_WMQ_BROKER_PUBQ_QMGR";
  
  public static final String WMQ_BROKER_QMGR = "XMSC_WMQ_BROKER_QMGR";
  
  public static final String WMQ_BROKER_SUBQ = "XMSC_WMQ_BROKER_SUBQ";
  
  public static final String WMQ_BROKER_VERSION = "brokerVersion";
  
  public static final String WMQ_CCSID = "CCSID";
  
  public static final String WMQ_CCDTURL = "XMSC_WMQ_CCDTURL";
  
  public static final String WMQ_RECEIVE_CCSID = "receiveCCSID";
  
  public static final String WMQ_RECEIVE_CONVERSION = "receiveConversion";
  
  public static final int WMQ_RECEIVE_CCSID_JVM_DEFAULT = 0;
  
  public static final int WMQ_RECEIVE_CONVERSION_CLIENT_MSG = 1;
  
  public static final int WMQ_RECEIVE_CONVERSION_QMGR = 2;
  
  public static final String WMQ_CF_DESCRIPTION = "XMSC_WMQ_CF_DESCRIPTION";
  
  public static final String WMQ_CHANNEL = "XMSC_WMQ_CHANNEL";
  
  public static final String WMQ_CLEANUP_INTERVAL = "XMSC_WMQ_CLEANUP_INTERVAL";
  
  public static final String WMQ_CLEANUP_LEVEL = "XMSC_WMQ_CLEANUP_LEVEL";
  
  public static final String WMQ_CLONE_SUPPORT = "XMSC_WMQ_CLONE_SUPPORT";
  
  public static final String WMQ_CONNECT_OPTIONS = "XMSC_WMQ_CONNECT_OPTIONS";
  
  public static final String WMQ_CONNECTION_MODE = "XMSC_WMQ_CONNECTION_MODE";
  
  public static final String WMQ_CONNECTION_TAG = "XMSC_WMQ_CONNECTION_TAG";
  
  public static final String WMQ_CONNECTION_ID = "XMSC_WMQ_CONNECTION_ID";
  
  public static final String WMQ_DEST_DESCRIPTION = "destDescription";
  
  public static final String WMQ_DUR_SUBQ = "brokerDurSubQueue";
  
  public static final String WMQ_ENCODING = "encoding";
  
  public static final String WMQ_EXPIRY = "expiry";
  
  public static final String WMQ_EOQ_TIMEOUT = "XMSC_WMQ_EOQ_TIMEOUT";
  
  public static final String WMQ_FAIL_IF_QUIESCE = "failIfQuiesce";
  
  public static final String WMQ_HEADER_COMP = "XMSC_WMQ_HEADER_COMP";
  
  public static final String WMQ_HOST_NAME = "XMSC_WMQ_HOST_NAME";
  
  public static final String WMQ_LOCAL_ADDRESS = "XMSC_WMQ_LOCAL_ADDRESS";
  
  public static final String WMQ_MAP_NAME_STYLE = "XMSC_WMQ_MAP_NAME_STYLE";
  
  public static final String WMQ_MESSAGE_RETENTION = "XMSC_WMQ_MESSAGE_RETENTION";
  
  public static final String WMQ_MESSAGE_SELECTION = "XMSC_WMQ_MESSAGE_SELECTION";
  
  public static final String WMQ_MSG_BATCH_SIZE = "XMSC_WMQ_MSG_BATCH_SIZE";
  
  public static final String WMQ_MSG_COMP = "XMSC_WMQ_MSG_COMP";
  
  public static final String WMQ_ALTERNATE_USER_ID = "alternateUserId";
  
  public static final String WMQ_MQMD_MESSAGE_CONTEXT = "mdMessageContext";
  
  public static final String WMQ_MQMD_READ_ENABLED = "mdReadEnabled";
  
  public static final String WMQ_MQMD_WRITE_ENABLED = "mdWriteEnabled";
  
  public static final String WMQ_MESSAGE_BODY = "messageBody";
  
  @Deprecated
  public static final String WMQ_OPT_PUB = "XMSC_WMQ_OPT_PUB";
  
  @Deprecated
  public static final String WMQ_OUTCOME_NOTIFICATION = "XMSC_WMQ_OUTCOME_NOTIFICATION";
  
  public static final String WMQ_PERSISTENCE = "persistence";
  
  public static final String WMQ_POLLING_INTERVAL = "XMSC_WMQ_POLLING_INTERVAL";
  
  public static final String WMQ_PORT = "XMSC_WMQ_PORT";
  
  public static final String WMQ_PRIORITY = "priority";
  
  @Deprecated
  public static final String WMQ_PROCESS_DURATION = "XMSC_WMQ_PROCESS_DURATION";
  
  public static final String WMQ_PROVIDER_VERSION = "XMSC_WMQ_PROVIDER_VERSION";
  
  public static final String JAKARTA_WMQ_PROVIDER_VERSION = "XMSC_WMQ_PROVIDER_VERSION";
  
  public static final String WMQ_PUB_ACK_INTERVAL = "XMSC_WMQ_PUB_ACK_INTERVAL";
  
  public static final String WMQ_PUT_ASYNC_ALLOWED = "putAsyncAllowed";
  
  public static final String WMQ_QMGR_CCSID = "XMSC_WMQ_QMGR_CCSID";
  
  public static final String WMQ_QUEUE_MANAGER = "XMSC_WMQ_QUEUE_MANAGER";
  
  public static final String WMQ_RESOLVED_QUEUE_MANAGER = "XMSC_WMQ_RESOLVED_QUEUE_MANAGER";
  
  public static final String WMQ_RESOLVED_QUEUE_MANAGER_ID = "XMSC_WMQ_RESOLVED_QUEUE_MANAGER_ID";
  
  public static final String WMQ_RESOLVED_QUEUE_SHARING_GROUP_NAME = "XMSC_WMQ_RESOLVED_QUEUE_SHARING_GROUP_NAME";
  
  public static final String WMQ_RESOLVED_CONNECTION_TAG = "XMSC_WMQ_RESOLVED_CONNECTION_TAG";
  
  public static final String WMQ_QUEUE_MANAGER_ID_PREFIX = "QMID:";
  
  public static final String WMQ_QUEUE_MANAGER_OVERRIDE_QUEUE = "XMSC_WMQ_QUEUE_MANAGER_OVERRIDE_QUEUE";
  
  public static final String WMQ_QUEUE_SHARING_GROUP_NAME_PREFIX = "QSGNAME:";
  
  public static final String WMQ_COMMAND_LEVEL = "XMSC_WMQ_COMMAND_LEVEL";
  
  public static final String WMQ_REQUIRED_QUEUE_MANAGER_ID = "XMSC_WMQ_REQUIRED_QUEUE_MANAGER_ID";
  
  public static final String WMQ_REQUIRED_QUEUE_MANAGER_THREADLOCAL = "XMSC_WMQ_REQUIRED_QUEUE_MANAGER_THREADLOCAL";
  
  public static final String WMQ_REQUIRED_QUEUE_SHARING_GROUP_NAME = "XMSC_WMQ_REQUIRED_QUEUE_SHARING_GROUP_NAME";
  
  public static final String WMQ_READ_AHEAD_ALLOWED = "readAheadAllowed";
  
  public static final String WMQ_READ_AHEAD_CLOSE_POLICY = "readAheadClosePolicy";
  
  public static final String WMQ_RECEIVE_EXIT = "XMSC_WMQ_RECEIVE_EXIT";
  
  public static final String WMQ_RECEIVE_EXIT_INIT = "XMSC_WMQ_RECEIVE_EXIT_INIT";
  
  public static final String WMQ_RECEIVE_ISOLATION = "XMSC_WMQ_RECEIVE_ISOLATION";
  
  public static final String WMQ_RESCAN_INTERVAL = "XMSC_WMQ_RESCAN_INTERVAL";
  
  public static final String WMQ_SECURITY_EXIT = "XMSC_WMQ_SECURITY_EXIT";
  
  public static final String WMQ_SECURITY_EXIT_INIT = "XMSC_WMQ_SECURITY_EXIT_INIT";
  
  public static final String WMQ_SEND_CHECK_COUNT = "XMSC_WMQ_SEND_CHECK_COUNT";
  
  public static final String WMQ_SEND_EXIT = "XMSC_WMQ_SEND_EXIT";
  
  public static final String WMQ_SEND_EXIT_INIT = "XMSC_WMQ_SEND_EXIT_INIT";
  
  public static final String WMQ_SHARE_CONV_ALLOWED = "XMSC_WMQ_SHARE_CONV_ALLOWED";
  
  public static final String WMQ_SPARSE_SUBSCRIPTIONS = "XMSC_WMQ_SPARSE_SUBSCRIPTIONS";
  
  public static final String WMQ_SSL_CERT_STORES_COL = "XMSC_WMQ_SSL_CERT_STORES_COL";
  
  public static final String WMQ_SSL_CERT_STORES_STR = "XMSC_WMQ_SSL_CERT_STORES_STR";
  
  public static final String WMQ_SSL_CIPHER_SPEC = "XMSC_WMQ_SSL_CIPHER_SPEC";
  
  public static final String WMQ_SSL_CIPHER_SUITE = "XMSC_WMQ_SSL_CIPHER_SUITE";
  
  public static final String WMQ_SSL_CRYPTO_HW = "XMSC_WMQ_SSL_CRYPTO_HW";
  
  public static final String WMQ_SSL_FIPS_REQUIRED = "XMSC_WMQ_SSL_FIPS_REQUIRED";
  
  public static final String WMQ_SSL_KEY_REPOSITORY = "XMSC_WMQ_SSL_KEY_REPOSITORY";
  
  public static final String WMQ_SSL_KEY_RESETCOUNT = "XMSC_WMQ_SSL_KEY_RESETCOUNT";
  
  public static final String WMQ_SSL_PEER_NAME = "XMSC_WMQ_SSL_PEER_NAME";
  
  public static final String WMQ_SSL_SOCKET_FACTORY = "XMSC_WMQ_SSL_SOCKET_FACTORY";
  
  public static final String WMQ_STATUS_REFRESH_INTERVAL = "XMSC_WMQ_STATUS_REFRESH_INTERVAL";
  
  public static final String WMQ_SUBSCRIPTION_STORE = "XMSC_WMQ_SUBSCRIPTION_STORE";
  
  public static final String WMQ_SYNCPOINT_ALL_GETS = "XMSC_WMQ_SYNCPOINT_ALL_GETS";
  
  public static final String WMQ_TARGET_CLIENT = "targetClient";
  
  public static final String WMQ_TARGET_CLIENT_MATCHING = "XMSC_WMQ_TARGET_CLIENT_MATCHING";
  
  public static final String WMQ_TEMP_Q_PREFIX = "XMSC_WMQ_TEMP_Q_PREFIX";
  
  public static final String WMQ_TEMP_TOPIC_PREFIX = "XMSC_WMQ_TEMP_TOPIC_PREFIX";
  
  public static final String WMQ_TEMPORARY_MODEL = "XMSC_WMQ_TEMPORARY_MODEL";
  
  public static final String WMQ_USE_CONNECTION_POOLING = "XMSC_WMQ_USE_CONNECTION_POOLING";
  
  public static final String WMQ_USER_PROPERTIES = "userProperties";
  
  public static final String WMQ_VERSION = "version";
  
  public static final String WMQ_WILDCARD_FORMAT = "wildcardFormat";
  
  public static final String IS_SUBSCRIPTION_MULTICAST = "XMSC_IS_SUBSCRIPTION_MULTICAST";
  
  public static final String IS_SUBSCRIPTION_RELIABLE_MULTICAST = "XMSC_IS_SUBSCRIPTION_RELIABLE_MULTICAST";
  
  public static final String RTT_BROKER_PING_INTERVAL = "XMSC_RTT_BROKER_PING_INTERVAL";
  
  public static final String RTT_CONNECTION_PROTOCOL = "XMSC_RTT_CONNECTION_PROTOCOL";
  
  public static final String RTT_DIRECT_AUTH = "XMSC_RTT_DIRECT_AUTH";
  
  public static final String RTT_HOST_NAME = "XMSC_RTT_HOST_NAME";
  
  public static final String RTT_LOCAL_ADDRESS = "XMSC_RTT_LOCAL_ADDRESS";
  
  public static final String RTT_MULTICAST = "multicast";
  
  public static final String RTT_PORT = "XMSC_RTT_PORT";
  
  public static final String RTT_PROXY_HOSTNAME = "XMSC_RTT_PROXY_HOSTNAME";
  
  public static final String RTT_PROXY_PORT = "XMSC_RTT_PROXY_PORT";
  
  public static final String WMQ_REMOTE_QMGR_QSGNAME = "XMSC_WMQ_REMOTE_QMGR_QSGNAME";
  
  public static final int RTT_DIRECT_AUTH_BASIC = 0;
  
  public static final int RTT_DIRECT_AUTH_CERTIFICATE = 1;
  
  public static final int RTT_DIRECT_AUTH_DEFAULT = 0;
  
  public static final int WMQ_BROKER_UNSPECIFIED = -1;
  
  public static final int WMQ_BROKER_V1 = 0;
  
  public static final int WMQ_BROKER_V2 = 1;
  
  public static final int WMQ_BROKER_DEFAULT = 0;
  
  public static final int WMQ_CLEANUP_AS_PROPERTY = -1;
  
  public static final int WMQ_CLEANUP_NONE = 0;
  
  public static final int WMQ_CLEANUP_SAFE = 1;
  
  public static final int WMQ_CLEANUP_STRONG = 2;
  
  public static final int WMQ_CLEANUP_FORCE = 3;
  
  public static final int WMQ_CLEANUP_NONDUR = 4;
  
  public static final int WMQ_CLEANUP_DEFAULT = 1;
  
  public static final int WMQ_CLONE_DISABLED = 0;
  
  public static final int WMQ_CLONE_ENABLED = 1;
  
  public static final int WMQ_CLONE_DEFAULT = 0;
  
  public static final int WMQ_CM_BINDINGS = 0;
  
  public static final int WMQ_CM_CLIENT = 1;
  
  public static final int WMQ_CM_CLIENT_UNMANAGED = 5;
  
  public static final int WMQ_CM_DIRECT_HTTP = 4;
  
  public static final int WMQ_CM_DIRECT_TCPIP = 2;
  
  public static final int WMQ_CM_BINDINGS_THEN_CLIENT = 8;
  
  public static final int WMQ_COMPHDR_NONE = 0;
  
  public static final int WMQ_COMPHDR_SYSTEM = 8;
  
  public static final int WMQ_COMPHDR_DEFAULT = 0;
  
  public static final int WMQ_DEFAULT_CLIENT_PORT = 1414;
  
  public static final int WMQ_ENCODING_INTEGER_NORMAL = 1;
  
  public static final int WMQ_ENCODING_INTEGER_REVERSED = 2;
  
  public static final int WMQ_ENCODING_DECIMAL_NORMAL = 16;
  
  public static final int WMQ_ENCODING_DECIMAL_REVERSED = 32;
  
  public static final int WMQ_ENCODING_FLOAT_IEEE_NORMAL = 256;
  
  public static final int WMQ_ENCODING_FLOAT_IEEE_REVERSED = 512;
  
  public static final int WMQ_ENCODING_FLOAT_S390 = 768;
  
  public static final int WMQ_ENCODING_NATIVE = 273;
  
  public static final int WMQ_EXP_APP = -2;
  
  public static final int WMQ_EXP_UNLIMITED = 0;
  
  public static final int WMQ_FIQ_NO = 0;
  
  public static final int WMQ_FIQ_YES = 1;
  
  public static final int WMQ_FIQ_DEFAULT = 1;
  
  public static final boolean WMQ_MAP_NAME_STYLE_STANDARD = true;
  
  public static final boolean WMQ_MAP_NAME_STYLE_COMPATIBLE = false;
  
  public static final boolean WMQ_MAP_NAME_STYLE_DEFAULT = true;
  
  public static final int WMQ_MRET_NO = 0;
  
  public static final int WMQ_MRET_YES = 1;
  
  public static final int WMQ_MRET_DEFAULT = 1;
  
  public static final int WMQ_MSEL_BROKER = 1;
  
  public static final int WMQ_MSEL_CLIENT = 0;
  
  public static final int WMQ_MSEL_DEFAULT = 0;
  
  public static final int WMQ_MDCTX_DEFAULT = 0;
  
  public static final int WMQ_MDCTX_SET_IDENTITY_CONTEXT = 1;
  
  public static final int WMQ_MDCTX_SET_ALL_CONTEXT = 2;
  
  public static final int WMQ_MESSAGE_BODY_JMS = 0;
  
  public static final int WMQ_MESSAGE_BODY_MQ = 1;
  
  public static final int WMQ_MESSAGE_BODY_UNSPECIFIED = 2;
  
  public static final int WMQ_COMPMSG_NONE = 0;
  
  public static final int WMQ_COMPMSG_RLE = 1;
  
  public static final int WMQ_COMPMSG_ZLIBFAST = 2;
  
  public static final int WMQ_COMPMSG_ZLIBHIGH = 4;
  
  public static final int WMQ_COMPMSG_DEFAULT = 0;
  
  public static final int WMQ_PRI_APP = -2;
  
  public static final int WMQ_PRI_QDEF = -1;
  
  public static final int WMQ_PER_APP = -2;
  
  public static final int WMQ_PER_QDEF = -1;
  
  public static final int WMQ_PER_NON = 1;
  
  public static final int WMQ_PER_PER = 2;
  
  public static final int WMQ_PER_NPHIGH = 3;
  
  public static final String WMQ_PS_CONTROL_QUEUE = "SYSTEM.BROKER.CONTROL.QUEUE";
  
  public static final String WMQ_PS_DEFAULT_STREAM_QUEUE = "SYSTEM.BROKER.DEFAULT.STREAM";
  
  public static final String WMQ_PS_D_PREFIX = "SYSTEM.JMS.D.";
  
  public static final String WMQ_PS_D_PREFIX_STAR = "SYSTEM.JMS.D.*";
  
  public static final String WMQ_PS_DEF_D_SHARED_QUEUE = "SYSTEM.JMS.D.SUBSCRIBER.QUEUE";
  
  public static final String WMQ_PS_DEF_ND_SHARED_QUEUE = "SYSTEM.JMS.ND.SUBSCRIBER.QUEUE";
  
  public static final String WMQ_CC_DEF_D_SHARED_QUEUE = "SYSTEM.JMS.D.CC.SUBSCRIBER.QUEUE";
  
  public static final String WMQ_CC_DEF_ND_SHARED_QUEUE = "SYSTEM.JMS.ND.CC.SUBSCRIBER.QUEUE";
  
  @Deprecated
  public static final int WMQ_PROCESSING_UNKNOWN = 0;
  
  @Deprecated
  public static final int WMQ_PROCESSING_SHORT = 1;
  
  @Deprecated
  public static final int WMQ_PROCESSING_DEFAULT = 0;
  
  public static final String WMQ_PROVIDER_VERSION_DEFAULT = "unspecified";
  
  public static final String JAKARTA_WMQ_PROVIDER_VERSION_DEFAULT = "unspecified";
  
  public static final String WMQ_PS_ADMIN_QUEUE = "SYSTEM.JMS.ADMIN.QUEUE";
  
  public static final String WMQ_PS_STATUS_QUEUE = "SYSTEM.JMS.PS.STATUS.QUEUE";
  
  public static final String WMQ_PS_MODEL_QUEUE = "SYSTEM.JMS.MODEL.QUEUE";
  
  public static final String WMQ_PS_REPORT_QUEUE = "SYSTEM.JMS.REPORT.QUEUE";
  
  public static final String WMQ_PS_ND_PREFIX = "SYSTEM.JMS.ND.";
  
  public static final String WMQ_PS_ND_PREFIX_STAR = "SYSTEM.JMS.ND.*";
  
  public static final int WMQ_PUT_ASYNC_ALLOWED_AS_DEST = -1;
  
  public static final int WMQ_PUT_ASYNC_ALLOWED_AS_Q_DEF = -1;
  
  public static final int WMQ_PUT_ASYNC_ALLOWED_AS_TOPIC_DEF = -1;
  
  public static final int WMQ_PUT_ASYNC_ALLOWED_DEFAULT = -1;
  
  public static final int WMQ_PUT_ASYNC_ALLOWED_DISABLED = 0;
  
  public static final int WMQ_PUT_ASYNC_ALLOWED_ENABLED = 1;
  
  @Deprecated
  public static final int WMQ_RCVISOL_COMMITTED = 0;
  
  @Deprecated
  public static final int WMQ_RCVISOL_UNCOMMITTED = 1;
  
  @Deprecated
  public static final int WMQ_RCVISOL_DEFAULT = 0;
  
  public static final int WMQ_READ_AHEAD_ALLOWED_AS_DEST = -1;
  
  public static final int WMQ_READ_AHEAD_ALLOWED_AS_Q_DEF = -1;
  
  public static final int WMQ_READ_AHEAD_ALLOWED_AS_TOPIC_DEF = -1;
  
  public static final int WMQ_READ_AHEAD_ALLOWED_DEFAULT = -1;
  
  public static final int WMQ_READ_AHEAD_ALLOWED_DISABLED = 0;
  
  public static final int WMQ_READ_AHEAD_ALLOWED_ENABLED = 1;
  
  public static final int WMQ_READ_AHEAD_DELIVERALL = 2;
  
  public static final int WMQ_READ_AHEAD_DELIVERCURRENT = 1;
  
  public static final int WMQ_READ_AHEAD_DEFAULT = 2;
  
  public static final String WMQ_REPORT_QUEUE = "SYSTEM.JMS.REPORT.QUEUE";
  
  public static final int WMQ_SEND_CHECK_COUNT_DEFAULT = 0;
  
  public static final String WMQ_REPLYTO_STYLE = "XMSC_WMQ_REPLYTO_STYLE";
  
  public static final int WMQ_REPLYTO_STYLE_DEFAULT = 0;
  
  public static final int WMQ_REPLYTO_STYLE_MQMD_VALUES = 1;
  
  public static final int WMQ_REPLYTO_STYLE_RFH2_VALUES = 2;
  
  public static final int WMQ_SHARE_CONV_ALLOWED_NO = 0;
  
  public static final int WMQ_SHARE_CONV_ALLOWED_YES = 1;
  
  public static final int WMQ_SHARE_CONV_ALLOWED_DEFAULT = 1;
  
  public static final int WMQ_SUBSTORE_QUEUE = 0;
  
  public static final int WMQ_SUBSTORE_BROKER = 1;
  
  public static final int WMQ_SUBSTORE_MIGRATE = 2;
  
  public static final int WMQ_SUBSTORE_DEFAULT = 2;
  
  public static final boolean WMQ_SYNCP_ALL_GETS_NO = false;
  
  public static final boolean WMQ_SYNCP_ALL_GETS_YES = true;
  
  public static final boolean WMQ_SYNCP_ALL_GETS_DEFAULT = false;
  
  public static final int WMQ_TARGET_DEST_JMS = 0;
  
  public static final int WMQ_TARGET_DEST_MQ = 1;
  
  public static final int WMQ_TARGET_DEST_DEFAULT = 0;
  
  public static final int WMQ_CLIENT_JMS_COMPLIANT = 0;
  
  public static final int WMQ_CLIENT_NONJMS_MQ = 1;
  
  public static final int WMQ_CLIENT_RECONNECT = 16777216;
  
  public static final int WMQ_CLIENT_RECONNECT_AS_DEF = 0;
  
  public static final int WMQ_CLIENT_RECONNECT_DISABLED = 33554432;
  
  public static final String WMQ_CLIENT_RECONNECT_OPTIONS = "XMSC_WMQ_CLIENT_RECONNECT_OPTIONS";
  
  public static final String WMQ_CLIENT_RECONNECT_TIMEOUT = "XMSC_WMQ_CLIENT_RECONNECT_TIMEOUT";
  
  public static final int WMQ_CLIENT_RECONNECT_TIMEOUT_DEFAULT = 1800;
  
  public static final int WMQ_CLIENT_RECONNECT_Q_MGR = 67108864;
  
  public static final String WMQ_REBALANCING_APPLICATION_TYPE = "XMSC_WMQ_REBALANCING_APPLICATION_TYPE";
  
  public static final int WMQ_REBALANCING_APPLICATION_TYPE_SIMPLE = 0;
  
  public static final int WMQ_REBALANCING_APPLICATION_TYPE_REQUEST_REPLY = 1;
  
  public static final int WMQ_REBALANCING_APPLICATION_TYPE_MANAGED = 65536;
  
  public static final String WMQ_REBALANCING_LISTENER = "XMSC_WMQ_REBALANCING_LISTENER";
  
  public static final String WMQ_CONNECTION_NAME_LIST = "XMSC_WMQ_CONNECTION_NAME_LIST";
  
  public static final String WMQ_CONNECTION_NAME_LIST_INT = "XMSC_WMQ_CONNECTION_NAME_LIST_INT";
  
  public static final int WMQ_WILDCARD_TOPIC_ONLY = 0;
  
  public static final int WMQ_WILDCARD_CHAR_ONLY = 1;
  
  public static final int WMQ_WILDCARD_DEFAULT = 0;
  
  public static final String WMQ_JMQI_OPTIONS = "XMSC_WMQ_JMQI_OPTIONS";
  
  public static final String WMQ_MAX_BUFFER_SIZE = "XMSC_WMQ_MAX_BUFFER_SIZE";
  
  public static final int RTT_CP_TCP = 1;
  
  public static final int RTT_CP_HTTP = 4;
  
  @Deprecated
  public static final int RTT_DEFAULT_PORT = 1506;
  
  @Deprecated
  public static final int RTT_MULTICAST_ASCF = -1;
  
  @Deprecated
  public static final int RTT_MULTICAST_DISABLED = 0;
  
  @Deprecated
  public static final int RTT_MULTICAST_ENABLED = 7;
  
  @Deprecated
  public static final int RTT_MULTICAST_NOT_RELIABLE = 3;
  
  @Deprecated
  public static final int RTT_MULTICAST_RELIABLE = 5;
  
  @Deprecated
  public static final int RTT_MULTICAST_DEFAULT = 0;
  
  public static final String QUEUE_PREFIX = "queue://";
  
  public static final String TOPIC_PREFIX = "topic://";
  
  public static final int DELIVERY_MODE_BAD = -1;
  
  public static final int DELIVERY_MODE_NONE = -2;
  
  public static final int DELIVERY_MODE_UNKNOWN = -3;
  
  public static final String INSERT_REASON = "XMSC_INSERT_REASON";
  
  public static final String INSERT_COMP_CODE = "XMSC_INSERT_COMP_CODE";
  
  public static final String INSERT_QUEUE_MANAGER = "XMSC_INSERT_QUEUE_MANAGER";
  
  public static final String INSERT_COMMAND_LEVEL = "XMSC_INSERT_COMMAND_LEVEL";
  
  public static final String INSERT_CHARACTER = "XMSC_INSERT_CHARACTER";
  
  public static final String INSERT_HEX_STRING = "XMSC_INSERT_HEX_STRING";
  
  public static final String INSERT_STRING = "XMSC_INSERT_STRING";
  
  public static final String XMSC_MESSAGE_ID = "XMSC_MESSAGE_ID";
  
  public static final String XMSC_CORRELATION_ID = "XMSC_CORRELATION_ID";
  
  public static final String XMSC_MESSAGE_BUFFER = "XMSC_MESSAGE_BUFFER";
  
  public static final String XMSC_MESSAGE_BUFFER_POSITION = "XMSC_MESSAGE_BUFFER_POSITION";
  
  public static final String XMSC_SUBSCRIBER_Q_NAME = "XMSC_SUBSCRIBER_Q_NAME";
  
  public static final String WMQ_CAPABILITY_ADVANCED = "XMSC_WMQ_CAPABILITY_ADVANCED";
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\common\CommonConstants.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */