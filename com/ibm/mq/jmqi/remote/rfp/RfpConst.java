package com.ibm.mq.jmqi.remote.rfp;

public interface RfpConst {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpConst.java";
  
  public static final int rfpPTR_BYTES = 4;
  
  public static final int rfpFAP_LEVEL = 16;
  
  public static final int rfpLOCAL_ENC = 1;
  
  public static final long rfpMAX_MESSAGE_SIZE = 4194304L;
  
  public static final int rfpIN = 1;
  
  public static final int rfpOUT = 2;
  
  public static final int rfpFAIL = 3;
  
  public static final int rfpLUWID_LENGTH = 8;
  
  public static final int rfpCHANNEL_NAME_LENGTH = 20;
  
  public static final int rfpHDRCOMPLIST_LENGTH = 2;
  
  public static final int rfpMSGCOMPLIST_LENGTH = 16;
  
  public static final int rfpPROT_ALGORITHM_LIST_LENGTH = 10;
  
  public static final int rfpRANDOM_LENGTH = 12;
  
  public static final int rfpPROT_ALGORITHM_NULL = 0;
  
  public static final int rfpPROT_ALGORITHM_3DES = 1;
  
  public static final int rfpPROT_ALGORITHM_UNUSED = 65535;
  
  public static final int rfpTST_INITIAL_INFO = 1;
  
  public static final int rfpTST_RESYNCH = 2;
  
  public static final int rfpTST_RESET = 3;
  
  public static final int rfpTST_MESSAGE_DATA = 4;
  
  public static final int rfpTST_STATUS_INFO = 5;
  
  public static final int rfpTST_SECURITY_DATA = 6;
  
  public static final int rfpTST_PING_DATA = 7;
  
  public static final int rfpTST_USERID_DATA = 8;
  
  public static final int rfpTST_HEARTBEATS = 9;
  
  public static final int rfpTST_CONAUTH_INFO = 10;
  
  public static final int rfpTST_RENEGOTIATE_DATA = 11;
  
  public static final int rfpTST_SOCKET_ACTION = 12;
  
  public static final int rfpTST_ASYNC_MESSAGE = 13;
  
  public static final int rfpTST_REQUEST_MSGS = 14;
  
  public static final int rfpTST_NOTIFICATION = 15;
  
  public static final int rfpTST_MQCONN = 129;
  
  public static final int rfpTST_MQDISC = 130;
  
  public static final int rfpTST_MQOPEN = 131;
  
  public static final int rfpTST_MQCLOSE = 132;
  
  public static final int rfpTST_MQGET = 133;
  
  public static final int rfpTST_MQPUT = 134;
  
  public static final int rfpTST_MQPUT1 = 135;
  
  public static final int rfpTST_MQSET = 136;
  
  public static final int rfpTST_MQINQ = 137;
  
  public static final int rfpTST_MQCMIT = 138;
  
  public static final int rfpTST_MQBACK = 139;
  
  public static final int rfpTST_SPI = 140;
  
  public static final int rfpTST_MQSTAT = 141;
  
  public static final int rfpTST_MQSUB = 142;
  
  public static final int rfpTST_MQSUBRQ = 143;
  
  public static final int rfpTST_MQCONN_REPLY = 145;
  
  public static final int rfpTST_MQDISC_REPLY = 146;
  
  public static final int rfpTST_MQOPEN_REPLY = 147;
  
  public static final int rfpTST_MQCLOSE_REPLY = 148;
  
  public static final int rfpTST_MQGET_REPLY = 149;
  
  public static final int rfpTST_MQPUT_REPLY = 150;
  
  public static final int rfpTST_MQPUT1_REPLY = 151;
  
  public static final int rfpTST_MQSET_REPLY = 152;
  
  public static final int rfpTST_MQINQ_REPLY = 153;
  
  public static final int rfpTST_MQCMIT_REPLY = 154;
  
  public static final int rfpTST_MQBACK_REPLY = 155;
  
  public static final int rfpTST_SPI_REPLY = 156;
  
  public static final int rfpTST_MQSTAT_REPLY = 157;
  
  public static final int rfpTST_MQSUB_REPLY = 158;
  
  public static final int rfpTST_MQSUBRQ_REPLY = 159;
  
  public static final int rfpTST_XA_START = 161;
  
  public static final int rfpTST_XA_END = 162;
  
  public static final int rfpTST_XA_OPEN = 163;
  
  public static final int rfpTST_XA_CLOSE = 164;
  
  public static final int rfpTST_XA_PREPARE = 165;
  
  public static final int rfpTST_XA_COMMIT = 166;
  
  public static final int rfpTST_XA_ROLLBACK = 167;
  
  public static final int rfpTST_XA_FORGET = 168;
  
  public static final int rfpTST_XA_RECOVER = 169;
  
  public static final int rfpTST_XA_COMPLETE = 170;
  
  public static final int rfpTST_XA_START_REPLY = 177;
  
  public static final int rfpTST_XA_END_REPLY = 178;
  
  public static final int rfpTST_XA_OPEN_REPLY = 179;
  
  public static final int rfpTST_XA_CLOSE_REPLY = 180;
  
  public static final int rfpTST_XA_PREPARE_REPLY = 181;
  
  public static final int rfpTST_XA_COMMIT_REPLY = 182;
  
  public static final int rfpTST_XA_ROLLBACK_REPLY = 183;
  
  public static final int rfpTST_XA_FORGET_REPLY = 184;
  
  public static final int rfpTST_XA_RECOVER_REPLY = 185;
  
  public static final int rfpTST_XA_COMPLETE_REPLY = 186;
  
  public static final int rfpTCF_CONFIRM_REQUEST = 1;
  
  public static final int rfpTCF_ERROR = 2;
  
  public static final int rfpTCF_REQUEST_CLOSE = 4;
  
  public static final int rfpTCF_CLOSE_CHANNEL = 8;
  
  public static final int rfpTCF_FIRST = 16;
  
  public static final int rfpTCF_LAST = 32;
  
  public static final int rfpTCF_REQUEST_ACCEPTED = 64;
  
  public static final int rfpTCF_DLQ_USED = 128;
  
  public static final int rfpTCF2_HDRCOMP = 1;
  
  public static final int rfpTCF2_MSGCOMP = 2;
  
  public static final int rfpTCF2_CSH = 4;
  
  public static final int rfpTCF2_CMIT_INTERVAL = 8;
  
  public static final int rfpICF_MSG_SEQ_NO = 1;
  
  public static final int rfpICF_CONVERSION_CAPABLE = 2;
  
  public static final int rfpICF_SPLIT_MESSAGES = 4;
  
  public static final int rfpICF_REQUEST_INITIATION = 8;
  
  public static final int rfpICF_REQUEST_SECURITY = 16;
  
  public static final int rfpICF_MQREQUEST = 32;
  
  public static final int rfpICF_SVRCONN_SECURITY = 64;
  
  public static final int rfpICF_RUNTIME_APP = 128;
  
  public static final int rfpICF2_DIST_LIST_CAPABLE = 1;
  
  public static final int rfpICF2_FAST_MESSAGES_REQUIRED = 2;
  
  public static final int rfpICF2_RESPONDER_CONVERSION = 4;
  
  public static final int rfpICF2_XAREQUEST = 16;
  
  public static final int rfpICF2_XARUNTIME_APP = 32;
  
  public static final int rfpICF2_SPIREQUEST = 64;
  
  public static final int rfpICF2_DUAL_UOW = 8;
  
  public static final int rfpICF2_TRACE_ROUTE_CAPABLE = 128;
  
  public static final int rfpICF3_MSG_PROP_CAPABLE = 1;
  
  public static final int rfpICF3_MULTIPLEX_SYNCGET = 8;
  
  public static final int rfpICF3_PWD_PROT_ALWAYS = 16;
  
  public static final int rfpICF3_RET_CONTAG_CAPABLE = 32;
  
  public static final int rfpICF3_UNUSED_BITS = 192;
  
  public static final int rfpIEF_CCSID_NOT_SUPPORTED = 1;
  
  public static final int rfpIEF_ENCODING_INVALID = 2;
  
  public static final int rfpIEF_MAX_TRANSMISSION_SIZE = 4;
  
  public static final int rfpIEF_FAP_LEVEL = 8;
  
  public static final int rfpIEF_MAX_MSG_SIZE = 16;
  
  public static final int rfpIEF_MAX_MSG_PER_BATCH = 32;
  
  public static final int rfpIEF_SEQ_WRAP_VALUE = 64;
  
  public static final int rfpIEF_HEARTBEAT_INTERVAL = 128;
  
  public static final int rfpIEF2_HDRCOMPLIST = 1;
  
  public static final int rfpIEF2_MSGCOMPLIST = 2;
  
  public static final int rfpIEF2_SSL_RESET = 4;
  
  public static final int rfpIEF3_UNUSED_BITS = 192;
  
  public static final int rfpIEF3_MSG_PROP_CAPABLE = 1;
  
  public static final int rfpIEF3_MULTICAST_CAPABLE = 2;
  
  public static final int rfpIEF3_MSG_PROP_INT_SEPARATE = 4;
  
  public static final int rfpIEF3_MULTIPLEX_SYNCGET = 8;
  
  public static final int rfpIEF3_PROT_ALGORITHMS = 16;
  
  public static final int rfpIEF3_RET_CONTAG_CAPABLE = 32;
  
  public static final int rfpERR_NO_CHANNEL = 1;
  
  public static final int rfpERR_CHANNEL_WRONG_TYPE = 2;
  
  public static final int rfpERR_QM_UNAVAILABLE = 3;
  
  public static final int rfpERR_MSG_SEQUENCE_ERROR = 4;
  
  public static final int rfpERR_QM_TERMINATING = 5;
  
  public static final int rfpERR_CAN_NOT_STORE = 6;
  
  public static final int rfpERR_USER_CLOSED = 7;
  
  public static final int rfpERR_TIMEOUT_EXPIRED = 8;
  
  public static final int rfpERR_TARGET_Q_UNKNOWN = 9;
  
  public static final int rfpERR_PROTOCOL_SEGMENT_TYPE = 10;
  
  public static final int rfpERR_PROTOCOL_LENGTH_ERROR = 11;
  
  public static final int rfpERR_PROTOCOL_INVALID_DATA = 12;
  
  public static final int rfpERR_PROTOCOL_SEGMENT_ERROR = 13;
  
  public static final int rfpERR_PROTOCOL_ID_ERROR = 14;
  
  public static final int rfpERR_PROTOCOL_MSH_ERROR = 15;
  
  public static final int rfpERR_PROTOCOL_GENERAL = 16;
  
  public static final int rfpERR_BATCH_FAILURE = 17;
  
  public static final int rfpERR_MESSAGE_LENGTH_ERROR = 18;
  
  public static final int rfpERR_SEGMENT_NUMBER_ERROR = 19;
  
  public static final int rfpERR_SECURITY_FAILURE = 20;
  
  public static final int rfpERR_WRAP_VALUE_ERROR = 21;
  
  public static final int rfpERR_CHANNEL_UNAVAILABLE = 22;
  
  public static final int rfpERR_CLOSED_BY_EXIT = 23;
  
  public static final int rfpERR_CIPHER_SPEC = 24;
  
  public static final int rfpERR_PEER_NAME = 25;
  
  public static final int rfpERR_SSL_CLIENT_CERTIFICATE = 26;
  
  public static final int rfpERR_RMT_RSRCS_IN_RECOVERY = 27;
  
  public static final int rfpERR_SSL_REFRESHING = 28;
  
  public static final int rfpERR_INVALID_HOBJ = 29;
  
  public static final int rfpERR_CONV_ID_ERROR = 30;
  
  public static final int rfpERR_SOCKET_ACTION_TYPE = 31;
  
  public static final int rfpERR_STANDBY_Q_MGR = 32;
  
  public static final int rfpERR_PASSWORD_PROTECTION = 36;
  
  public static final int rfpERR_MAX_CONNS_LIMIT_REACHED = 37;
  
  public static final int rfpMQF_REPLY = 1;
  
  public static final int rfpOPT_VERSIONS_SUPPORTED = 1;
  
  public static final int rfpOPT_MQCONNX = 2;
  
  public static final int rfpOPT_RECONNECTING = 4;
  
  public static final int rfpOPT_CLIENT_CONNTAG = 8;
  
  public static final int rfpVB_FIRST = 1;
  
  public static final int rfpVB_QUERYSPI = 1;
  
  public static final int rfpVB_PUT = 2;
  
  public static final int rfpVB_GET = 3;
  
  public static final int rfpVB_ACTIVATE = 4;
  
  public static final int rfpVB_SYNCPOINT = 5;
  
  public static final int rfpVB_RESERVE = 6;
  
  public static final int rfpVB_SUBSCRIBE = 7;
  
  public static final int rfpVB_UNSUBSCRIBE = 8;
  
  public static final int rfpVB_UNUSED1 = 9;
  
  public static final int rfpVB_UNUSED2 = 10;
  
  public static final int rfpVB_NOTIFY = 11;
  
  public static final int rfpVB_OPEN = 12;
  
  public static final int rfpVB_LAST = 12;
  
  public static final int rfpSAT_START_CONV = 1;
  
  public static final int rfpSAT_END_CONV = 2;
  
  public static final int rfpSAT_STOP_QUIESCE = 3;
  
  public static final int rfpSAT_PAUSE_DATA_SENDS = 4;
  
  public static final int rfpSAT_DATA_SENDS_PAUSED = 5;
  
  public static final int rfpSAT_RESUME_DATA_SENDS = 6;
  
  public static final int rfpSAT_QM_QUIESCE = 7;
  
  public static final int rfpSAT_ACCEPT_CONV = 8;
  
  public static final int rfpSAT_RECONNECT_CLIENTS = 9;
  
  public static final int rfpRMF_TXN_ALLOWED = 1;
  
  public static final int rfpRMF_CHECK_MSG = 2;
  
  public static final int rfpRMF_STOP_CONSUME = 4;
  
  public static final int rfpRMF_MQGET_WAITING = 8;
  
  public static final int rfpRMF_SET_SELECTION = 16;
  
  public static final int rfpNC_GET_INHIBITED = 1;
  
  public static final int rfpNC_GET_ALLOWED = 2;
  
  public static final int rfpNC_CONN_STATE = 3;
  
  public static final int rfpNC_CONN_STATE_REPLY = 4;
  
  public static final int rfpNC_Q_STATE = 5;
  
  public static final int rfpNC_Q_STATE_REPLY = 6;
  
  public static final int rfpNC_QM_QUIESCING = 7;
  
  public static final int rfpNC_TXN_ALLOWED = 8;
  
  public static final int rfpNC_TXN_REVOKE = 9;
  
  public static final int rfpNC_TXN_REVOKE_REPLY = 10;
  
  public static final int rfpNC_CHECK_MSG = 11;
  
  public static final int rfpNC_BROWSE_FIRST = 12;
  
  public static final int rfpNC_MESSAGE_TOO_LARGE = 13;
  
  public static final int rfpNC_STREAMING_FAILURE = 14;
  
  public static final int rfpNC_CLIENT_ASYNC_EMPTY = 15;
  
  public static final int rfpNC_STREAMING_TXN_PAUSED = 16;
  
  public static final int rfpNC_RECONNECTION_COMPLETE = 17;
  
  public static final long rfpMH_MPH = 1L;
  
  public static final long rfpMH_HANDLE = 2L;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\RfpConst.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */