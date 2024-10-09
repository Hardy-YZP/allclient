/*      */ package com.ibm.mq.constants;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public interface CMQXC
/*      */ {
/*      */   public static final String copyright_notice = "Licensed Materials - Property of IBM (c) Copyright IBM Corp. 2008, 2023 All Rights Reserved. US Government Users Restricted Rights - Use, duplication or disclosure restricted by GSA ADP Schedule Contract with IBM Corp.";
/*      */   public static final String sccsid = "%Z% %W% %I% %E% %U%";
/*      */   public static final int MQXT_API_CROSSING_EXIT = 1;
/*      */   public static final int MQXT_API_EXIT = 2;
/*      */   public static final int MQXT_CHANNEL_SEC_EXIT = 11;
/*      */   public static final int MQXT_CHANNEL_MSG_EXIT = 12;
/*      */   public static final int MQXT_CHANNEL_SEND_EXIT = 13;
/*      */   public static final int MQXT_CHANNEL_RCV_EXIT = 14;
/*      */   public static final int MQXT_CHANNEL_MSG_RETRY_EXIT = 15;
/*      */   public static final int MQXT_CHANNEL_AUTO_DEF_EXIT = 16;
/*      */   public static final int MQXT_CLUSTER_WORKLOAD_EXIT = 20;
/*      */   public static final int MQXT_PUBSUB_ROUTING_EXIT = 21;
/*      */   public static final int MQXT_PUBLISH_EXIT = 22;
/*      */   public static final int MQXT_PRECONNECT_EXIT = 23;
/*      */   public static final int MQXR_BEFORE = 1;
/*      */   public static final int MQXR_AFTER = 2;
/*      */   public static final int MQXR_CONNECTION = 3;
/*      */   public static final int MQXR_BEFORE_CONVERT = 4;
/*      */   public static final int MQXR_INIT = 11;
/*      */   public static final int MQXR_TERM = 12;
/*      */   public static final int MQXR_MSG = 13;
/*      */   public static final int MQXR_XMIT = 14;
/*      */   public static final int MQXR_SEC_MSG = 15;
/*      */   public static final int MQXR_INIT_SEC = 16;
/*      */   public static final int MQXR_RETRY = 17;
/*      */   public static final int MQXR_AUTO_CLUSSDR = 18;
/*      */   public static final int MQXR_AUTO_RECEIVER = 19;
/*      */   public static final int MQXR_CLWL_OPEN = 20;
/*      */   public static final int MQXR_CLWL_PUT = 21;
/*      */   public static final int MQXR_CLWL_MOVE = 22;
/*      */   public static final int MQXR_CLWL_REPOS = 23;
/*      */   public static final int MQXR_CLWL_REPOS_MOVE = 24;
/*      */   public static final int MQXR_END_BATCH = 25;
/*      */   public static final int MQXR_ACK_RECEIVED = 26;
/*      */   public static final int MQXR_AUTO_SVRCONN = 27;
/*      */   public static final int MQXR_AUTO_CLUSRCVR = 28;
/*      */   public static final int MQXR_SEC_PARMS = 29;
/*      */   public static final int MQXR_PUBLICATION = 30;
/*      */   public static final int MQXR_PRECONNECT = 31;
/*      */   public static final int MQXCC_OK = 0;
/*      */   public static final int MQXCC_SUPPRESS_FUNCTION = -1;
/*      */   public static final int MQXCC_SKIP_FUNCTION = -2;
/*      */   public static final int MQXCC_SEND_AND_REQUEST_SEC_MSG = -3;
/*      */   public static final int MQXCC_SEND_SEC_MSG = -4;
/*      */   public static final int MQXCC_SUPPRESS_EXIT = -5;
/*      */   public static final int MQXCC_CLOSE_CHANNEL = -6;
/*      */   public static final int MQXCC_REQUEST_ACK = -7;
/*      */   public static final int MQXCC_FAILED = -8;
/*  351 */   public static final byte[] MQXUA_NONE = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQCLCT_STATIC = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQCLCT_DYNAMIC = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQMCEV_PACKET_LOSS = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQMCEV_HEARTBEAT_TIMEOUT = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQMCEV_VERSION_CONFLICT = 3;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQMCEV_RELIABILITY = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQMCEV_CLOSED_TRANS = 5;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQMCEV_STREAM_ERROR = 6;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQMCEV_NEW_SOURCE = 10;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQMCEV_RECEIVE_QUEUE_TRIMMED = 11;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQMCEV_PACKET_LOSS_NACK_EXPIRE = 12;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQMCEV_ACK_RETRIES_EXCEEDED = 13;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQMCEV_STREAM_SUSPEND_NACK = 14;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQMCEV_STREAM_RESUME_NACK = 15;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQMCEV_STREAM_EXPELLED = 16;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQMCEV_FIRST_MESSAGE = 20;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQMCEV_LATE_JOIN_FAILURE = 21;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQMCEV_MESSAGE_LOSS = 22;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQMCEV_SEND_PACKET_FAILURE = 23;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQMCEV_REPAIR_DELAY = 24;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQMCEV_MEMORY_ALERT_ON = 25;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQMCEV_MEMORY_ALERT_OFF = 26;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQMCEV_NACK_ALERT_ON = 27;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQMCEV_NACK_ALERT_OFF = 28;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQMCEV_REPAIR_ALERT_ON = 29;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQMCEV_REPAIR_ALERT_OFF = 30;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQMCEV_RELIABILITY_CHANGED = 31;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQMCEV_SHM_DEST_UNUSABLE = 80;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQMCEV_SHM_PORT_UNUSABLE = 81;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQMCEV_CCT_GETTIME_FAILED = 110;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQMCEV_DEST_INTERFACE_FAILURE = 120;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQMCEV_DEST_INTERFACE_FAILOVER = 121;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQMCEV_PORT_INTERFACE_FAILURE = 122;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQMCEV_PORT_INTERFACE_FAILOVER = 123;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQCD_VERSION_1 = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQCD_VERSION_2 = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQCD_VERSION_3 = 3;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQCD_VERSION_4 = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQCD_VERSION_5 = 5;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQCD_VERSION_6 = 6;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQCD_VERSION_7 = 7;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQCD_VERSION_8 = 8;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQCD_VERSION_9 = 9;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQCD_VERSION_10 = 10;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQCD_VERSION_11 = 11;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQCD_VERSION_12 = 12;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQCD_CURRENT_VERSION = 12;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQCD_LENGTH_1 = 984;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQCD_LENGTH_2 = 1312;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQCD_LENGTH_3 = 1480;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQCHT_SENDER = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQCHT_SERVER = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQCHT_RECEIVER = 3;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQCHT_REQUESTER = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQCHT_ALL = 5;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQCHT_CLNTCONN = 6;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQCHT_SVRCONN = 7;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQCHT_CLUSRCVR = 8;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQCHT_CLUSSDR = 9;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQCHT_MQTT = 10;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQCHT_AMQP = 11;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQCOMPRESS_NOT_AVAILABLE = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQCOMPRESS_NONE = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQCOMPRESS_RLE = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQCOMPRESS_ZLIBFAST = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQCOMPRESS_ZLIBHIGH = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQCOMPRESS_SYSTEM = 8;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQCOMPRESS_ANY = 268435455;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQXPT_ALL = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQXPT_LOCAL = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQXPT_LU62 = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQXPT_TCP = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQXPT_NETBIOS = 3;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQXPT_SPX = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQXPT_DECNET = 5;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQXPT_UDP = 6;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQPA_DEFAULT = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQPA_CONTEXT = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQPA_ONLY_MCA = 3;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQPA_ALTERNATE_OR_MCA = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQCDC_SENDER_CONVERSION = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQCDC_NO_SENDER_CONVERSION = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQMCAT_PROCESS = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQMCAT_THREAD = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQNPMS_NORMAL = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQNPMS_FAST = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQSCA_REQUIRED = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQSCA_OPTIONAL = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQSCA_NEVER_REQUIRED = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQKAI_AUTO = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQCAFTY_NONE = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQCAFTY_PREFERRED = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQRCN_NO = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQRCN_YES = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQRCN_Q_MGR = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQRCN_DISABLED = 3;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQPROTO_MQTTV3 = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQPROTO_HTTP = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQPROTO_AMQP = 3;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQPROTO_MQTTV311 = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQSECPROT_NONE = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQSECPROT_SSLV30 = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQSECPROT_TLSV10 = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQSECPROT_TLSV12 = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQSECPROT_TLSV13 = 8;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQSPL_PASSTHRU = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQSPL_REMOVE = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQSPL_AS_POLICY = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQACH_STRUC_ID = "ACH ";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQACH_VERSION_1 = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQACH_CURRENT_VERSION = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQAXC_STRUC_ID = "AXC ";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQAXC_VERSION_1 = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQAXC_VERSION_2 = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQAXC_CURRENT_VERSION = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQXE_OTHER = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQXE_MCA = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQXE_MCA_SVRCONN = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQXE_COMMAND_SERVER = 3;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQXE_MQSC = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQXE_MCA_CLNTCONN = 5;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQAXP_STRUC_ID = "AXP ";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQAXP_VERSION_1 = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQAXP_VERSION_2 = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQAXP_CURRENT_VERSION = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQXACT_EXTERNAL = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQXACT_INTERNAL = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1019 */   public static final byte[] MQXPDA_NONE = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
/*      */   public static final int MQXF_INIT = 1;
/*      */   public static final int MQXF_TERM = 2;
/*      */   public static final int MQXF_CONN = 3;
/*      */   public static final int MQXF_CONNX = 4;
/*      */   public static final int MQXF_DISC = 5;
/*      */   public static final int MQXF_OPEN = 6;
/*      */   public static final int MQXF_CLOSE = 7;
/*      */   public static final int MQXF_PUT1 = 8;
/*      */   public static final int MQXF_PUT = 9;
/*      */   public static final int MQXF_GET = 10;
/*      */   public static final int MQXF_DATA_CONV_ON_GET = 11;
/*      */   public static final int MQXF_INQ = 12;
/*      */   public static final int MQXF_SET = 13;
/*      */   public static final int MQXF_BEGIN = 14;
/*      */   public static final int MQXF_CMIT = 15;
/*      */   public static final int MQXF_BACK = 16;
/*      */   public static final int MQXF_STAT = 18;
/*      */   public static final int MQXF_CB = 19;
/*      */   public static final int MQXF_CTL = 20;
/*      */   public static final int MQXF_CALLBACK = 21;
/*      */   public static final int MQXF_SUB = 22;
/*      */   public static final int MQXF_SUBRQ = 23;
/*      */   public static final int MQXF_XACLOSE = 24;
/*      */   public static final int MQXF_XACOMMIT = 25;
/*      */   public static final int MQXF_XACOMPLETE = 26;
/*      */   public static final int MQXF_XAEND = 27;
/*      */   public static final int MQXF_XAFORGET = 28;
/*      */   public static final int MQXF_XAOPEN = 29;
/*      */   public static final int MQXF_XAPREPARE = 30;
/*      */   public static final int MQXF_XARECOVER = 31;
/*      */   public static final int MQXF_XAROLLBACK = 32;
/*      */   public static final int MQXF_XASTART = 33;
/*      */   public static final int MQXF_AXREG = 34;
/*      */   public static final int MQXF_AXUNREG = 35;
/*      */   public static final int MQCDC_VERSION_1 = 1;
/*      */   public static final int MQCDC_VERSION_2 = 2;
/*      */   public static final int MQCDC_VERSION_3 = 3;
/*      */   public static final int MQCDC_VERSION_4 = 4;
/*      */   public static final int MQCDC_VERSION_5 = 5;
/*      */   public static final int MQCDC_VERSION_6 = 6;
/*      */   public static final int MQCDC_VERSION_7 = 7;
/*      */   public static final int MQCDC_VERSION_8 = 8;
/*      */   public static final int MQCDC_VERSION_9 = 9;
/*      */   public static final int MQCDC_VERSION_10 = 10;
/*      */   public static final int MQCDC_VERSION_11 = 11;
/*      */   public static final int MQCDC_VERSION_12 = 12;
/*      */   public static final int MQCDC_CURRENT_VERSION = 12;
/*      */   public static final int MQCDC_LENGTH_1 = 984;
/*      */   public static final int MQCDC_LENGTH_2 = 1312;
/*      */   public static final int MQCDC_LENGTH_3 = 1480;
/*      */   public static final String MQCXP_STRUC_ID = "CXP ";
/*      */   public static final int MQCXP_VERSION_1 = 1;
/*      */   public static final int MQCXP_VERSION_2 = 2;
/*      */   public static final int MQCXP_VERSION_3 = 3;
/*      */   public static final int MQCXP_VERSION_4 = 4;
/*      */   public static final int MQCXP_VERSION_5 = 5;
/*      */   public static final int MQCXP_VERSION_6 = 6;
/*      */   public static final int MQCXP_VERSION_7 = 7;
/*      */   public static final int MQCXP_VERSION_8 = 8;
/*      */   public static final int MQCXP_VERSION_9 = 9;
/*      */   public static final int MQCXP_CURRENT_VERSION = 9;
/*      */   public static final int MQCXP_LENGTH_3 = 156;
/*      */   public static final int MQCXP_LENGTH_4 = 156;
/*      */   public static final int MQCXP_LENGTH_5 = 160;
/*      */   public static final int MQXR2_PUT_WITH_DEF_ACTION = 0;
/*      */   public static final int MQXR2_PUT_WITH_DEF_USERID = 1;
/*      */   public static final int MQXR2_PUT_WITH_MSG_USERID = 2;
/*      */   public static final int MQXR2_USE_AGENT_BUFFER = 0;
/*      */   public static final int MQXR2_USE_EXIT_BUFFER = 4;
/*      */   public static final int MQXR2_DEFAULT_CONTINUATION = 0;
/*      */   public static final int MQXR2_CONTINUE_CHAIN = 8;
/*      */   public static final int MQXR2_SUPPRESS_CHAIN = 16;
/*      */   public static final int MQXR2_STATIC_CACHE = 0;
/*      */   public static final int MQXR2_DYNAMIC_CACHE = 32;
/*      */   public static final int MQCF_NONE = 0;
/*      */   public static final int MQCF_DIST_LISTS = 1;
/*      */   public static final String MQDXP_STRUC_ID = "DXP ";
/*      */   public static final int MQDXP_VERSION_1 = 1;
/*      */   public static final int MQDXP_VERSION_2 = 2;
/*      */   public static final int MQDXP_CURRENT_VERSION = 2;
/*      */   public static final int MQDXP_LENGTH_1 = 44;
/*      */   public static final int MQXDR_OK = 0;
/*      */   public static final int MQXDR_CONVERSION_FAILED = 1;
/*      */   public static final String MQNXP_STRUC_ID = "NXP ";
/*      */   public static final int MQNXP_VERSION_1 = 1;
/*      */   public static final int MQNXP_VERSION_2 = 2;
/*      */   public static final int MQNXP_CURRENT_VERSION = 2;
/*      */   public static final String MQPBC_STRUC_ID = "PBC ";
/*      */   public static final int MQPBC_VERSION_1 = 1;
/*      */   public static final int MQPBC_VERSION_2 = 2;
/*      */   public static final int MQPBC_CURRENT_VERSION = 2;
/*      */   public static final String MQPSXP_STRUC_ID = "PSXP";
/*      */   public static final int MQPSXP_VERSION_1 = 1;
/*      */   public static final int MQPSXP_VERSION_2 = 2;
/*      */   public static final int MQPSXP_CURRENT_VERSION = 2;
/*      */   public static final String MQSBC_STRUC_ID = "SBC ";
/*      */   public static final int MQSBC_VERSION_1 = 1;
/*      */   public static final int MQSBC_CURRENT_VERSION = 1;
/*      */   public static final String MQWDR_STRUC_ID = "WDR ";
/*      */   public static final int MQWDR_VERSION_1 = 1;
/*      */   public static final int MQWDR_VERSION_2 = 2;
/*      */   public static final int MQWDR_CURRENT_VERSION = 2;
/*      */   public static final int MQWDR_LENGTH_1 = 124;
/*      */   public static final int MQWDR_LENGTH_2 = 136;
/*      */   public static final int MQWDR_CURRENT_LENGTH = 136;
/*      */   public static final int MQQMF_REPOSITORY_Q_MGR = 2;
/*      */   public static final int MQQMF_CLUSSDR_USER_DEFINED = 8;
/*      */   public static final int MQQMF_CLUSSDR_AUTO_DEFINED = 16;
/*      */   public static final int MQQMF_AVAILABLE = 32;
/*      */   public static final int MQWDR1_LENGTH_1 = 124;
/*      */   public static final int MQWDR1_CURRENT_LENGTH = 124;
/*      */   public static final int MQWDR2_LENGTH_1 = 124;
/*      */   public static final int MQWDR2_LENGTH_2 = 136;
/*      */   public static final int MQWDR2_CURRENT_LENGTH = 136;
/*      */   public static final String MQWQR_STRUC_ID = "WQR ";
/*      */   public static final int MQWQR_VERSION_1 = 1;
/*      */   public static final int MQWQR_VERSION_2 = 2;
/*      */   public static final int MQWQR_VERSION_3 = 3;
/*      */   public static final int MQWQR_VERSION_4 = 4;
/*      */   public static final int MQWQR_CURRENT_VERSION = 4;
/*      */   public static final int MQWQR_LENGTH_1 = 200;
/*      */   public static final int MQWQR_LENGTH_2 = 208;
/*      */   public static final int MQWQR_LENGTH_3 = 212;
/*      */   public static final int MQWQR_LENGTH_4 = 216;
/*      */   public static final int MQWQR_CURRENT_LENGTH = 216;
/*      */   public static final int MQQF_LOCAL_Q = 1;
/*      */   public static final int MQQF_CLWL_USEQ_ANY = 64;
/*      */   public static final int MQQF_CLWL_USEQ_LOCAL = 128;
/*      */   public static final int MQWQR1_LENGTH_1 = 200;
/*      */   public static final int MQWQR1_CURRENT_LENGTH = 200;
/*      */   public static final int MQWQR2_LENGTH_1 = 200;
/*      */   public static final int MQWQR2_LENGTH_2 = 208;
/*      */   public static final int MQWQR2_CURRENT_LENGTH = 208;
/*      */   public static final int MQWQR3_LENGTH_1 = 200;
/*      */   public static final int MQWQR3_LENGTH_2 = 208;
/*      */   public static final int MQWQR3_LENGTH_3 = 212;
/*      */   public static final int MQWQR3_CURRENT_LENGTH = 212;
/*      */   public static final int MQWQR4_LENGTH_1 = 200;
/*      */   public static final int MQWQR4_LENGTH_2 = 208;
/*      */   public static final int MQWQR4_LENGTH_3 = 212;
/*      */   public static final int MQWQR4_LENGTH_4 = 216;
/*      */   public static final int MQWQR4_CURRENT_LENGTH = 216;
/*      */   public static final String MQWXP_STRUC_ID = "WXP ";
/*      */   public static final int MQWXP_VERSION_1 = 1;
/*      */   public static final int MQWXP_VERSION_2 = 2;
/*      */   public static final int MQWXP_VERSION_3 = 3;
/*      */   public static final int MQWXP_VERSION_4 = 4;
/*      */   public static final int MQWXP_CURRENT_VERSION = 4;
/*      */   public static final int MQWXP_PUT_BY_CLUSTER_CHL = 2;
/*      */   public static final String MQXEPO_STRUC_ID = "XEPO";
/*      */   public static final int MQXEPO_VERSION_1 = 1;
/*      */   public static final int MQXEPO_CURRENT_VERSION = 1;
/*      */   public static final int MQXEPO_NONE = 0;
/*      */   public static final String MQXP_STRUC_ID = "XP  ";
/*      */   public static final int MQXP_VERSION_1 = 1;
/*      */   public static final int MQXP_LENGTH_1 = 44;
/*      */   public static final int MQXP_CURRENT_LENGTH = 44;
/*      */   public static final int MQXC_MQOPEN = 1;
/*      */   public static final int MQXC_MQCLOSE = 2;
/*      */   public static final int MQXC_MQGET = 3;
/*      */   public static final int MQXC_MQPUT = 4;
/*      */   public static final int MQXC_MQPUT1 = 5;
/*      */   public static final int MQXC_MQINQ = 6;
/*      */   public static final int MQXC_MQSET = 8;
/*      */   public static final int MQXC_MQBACK = 9;
/*      */   public static final int MQXC_MQCMIT = 10;
/*      */   public static final int MQXC_MQSUB = 42;
/*      */   public static final int MQXC_MQSUBRQ = 43;
/*      */   public static final int MQXC_MQCB = 44;
/*      */   public static final int MQXC_MQCTL = 45;
/*      */   public static final int MQXC_MQSTAT = 46;
/*      */   public static final int MQXC_CALLBACK = 48;
/*      */   public static final String MQXWD_STRUC_ID = "XWD ";
/*      */   public static final int MQXWD_VERSION_1 = 1;
/*      */   public static final int MQXWD_LENGTH_1 = 24;
/*      */   public static final int MQXWD_CURRENT_LENGTH = 24;
/*      */   public static final String MQTRANSPORTEXIT = "MQ_TRANSPORT_EXIT";
/*      */   public static final int MQDCC_DEFAULT_CONVERSION = 1;
/*      */   public static final int MQDCC_FILL_TARGET_BUFFER = 2;
/*      */   public static final int MQDCC_INT_DEFAULT_CONVERSION = 4;
/*      */   public static final int MQDCC_SOURCE_ENC_NATIVE = 16;
/*      */   public static final int MQDCC_SOURCE_ENC_NORMAL = 16;
/*      */   public static final int MQDCC_SOURCE_ENC_REVERSED = 32;
/*      */   public static final int MQDCC_SOURCE_ENC_UNDEFINED = 0;
/*      */   public static final int MQDCC_TARGET_ENC_NATIVE = 256;
/*      */   public static final int MQDCC_TARGET_ENC_NORMAL = 256;
/*      */   public static final int MQDCC_TARGET_ENC_REVERSED = 512;
/*      */   public static final int MQDCC_TARGET_ENC_UNDEFINED = 0;
/*      */   public static final int MQDCC_NONE = 0;
/*      */   public static final int MQDCC_SOURCE_ENC_MASK = 240;
/*      */   public static final int MQDCC_TARGET_ENC_MASK = 3840;
/*      */   public static final int MQDCC_SOURCE_ENC_FACTOR = 16;
/*      */   public static final int MQDCC_TARGET_ENC_FACTOR = 256;
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\constants\CMQXC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */