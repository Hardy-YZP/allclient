package com.ibm.mq.constants;

public interface CMQCFC {
  public static final String copyright_notice = "Licensed Materials - Property of IBM (c) Copyright IBM Corp. 2008, 2023 All Rights Reserved. US Government Users Restricted Rights - Use, duplication or disclosure restricted by GSA ADP Schedule Contract with IBM Corp.";
  
  public static final String sccsid = "%Z% %W% %I% %E% %U%";
  
  public static final int MQ_ARCHIVE_PFX_LENGTH = 36;
  
  public static final int MQ_ARCHIVE_UNIT_LENGTH = 8;
  
  public static final int MQ_ASID_LENGTH = 4;
  
  public static final int MQ_AUTH_PROFILE_NAME_LENGTH = 48;
  
  public static final int MQ_CF_LEID_LENGTH = 12;
  
  public static final int MQ_COMMAND_MQSC_LENGTH = 32768;
  
  public static final int MQ_DATA_SET_NAME_LENGTH = 44;
  
  public static final int MQ_DB2_NAME_LENGTH = 4;
  
  public static final int MQ_DSG_NAME_LENGTH = 8;
  
  public static final int MQ_ENTITY_NAME_LENGTH = 1024;
  
  public static final int MQ_ENV_INFO_LENGTH = 96;
  
  public static final int MQ_GROUP_ADDRESS_LENGTH = 264;
  
  public static final int MQ_HOST_NAME_LENGTH = 256;
  
  public static final int MQ_IP_ADDRESS_LENGTH = 48;
  
  public static final int MQ_LOG_CORREL_ID_LENGTH = 8;
  
  public static final int MQ_LOG_EXTENT_NAME_LENGTH = 24;
  
  public static final int MQ_LOG_PATH_LENGTH = 1024;
  
  public static final int MQ_LRSN_LENGTH = 12;
  
  public static final int MQ_LSN_LENGTH = 64;
  
  public static final int MQ_NHA_REPL_ADDRESS_LENGTH = 264;
  
  public static final int MQ_ORIGIN_NAME_LENGTH = 8;
  
  public static final int MQ_PSB_NAME_LENGTH = 8;
  
  public static final int MQ_PST_ID_LENGTH = 8;
  
  public static final int MQ_Q_MGR_CPF_LENGTH = 4;
  
  public static final int MQ_Q_MGR_DATA_PATH_LENGTH = 1024;
  
  public static final int MQ_RESPONSE_ID_LENGTH = 24;
  
  public static final int MQ_RBA_LENGTH = 16;
  
  public static final int MQ_REMOTE_PRODUCT_LENGTH = 4;
  
  public static final int MQ_REMOTE_VERSION_LENGTH = 8;
  
  public static final int MQ_SECURITY_PROFILE_LENGTH = 40;
  
  public static final int MQ_SERVICE_COMPONENT_LENGTH = 48;
  
  public static final int MQ_SUB_NAME_LENGTH = 10240;
  
  public static final int MQ_SYSP_SERVICE_LENGTH = 32;
  
  public static final int MQ_SYSTEM_NAME_LENGTH = 8;
  
  public static final int MQ_TASK_NUMBER_LENGTH = 8;
  
  public static final int MQ_TPIPE_PFX_LENGTH = 4;
  
  public static final int MQ_UOW_ID_LENGTH = 256;
  
  public static final int MQ_USER_DATA_LENGTH = 10240;
  
  public static final int MQ_VOLSER_LENGTH = 6;
  
  public static final int MQCFOP_LESS = 1;
  
  public static final int MQCFOP_EQUAL = 2;
  
  public static final int MQCFOP_GREATER = 4;
  
  public static final int MQCFOP_NOT_LESS = 6;
  
  public static final int MQCFOP_NOT_EQUAL = 5;
  
  public static final int MQCFOP_NOT_GREATER = 3;
  
  public static final int MQCFOP_LIKE = 18;
  
  public static final int MQCFOP_NOT_LIKE = 21;
  
  public static final int MQCFOP_CONTAINS = 10;
  
  public static final int MQCFOP_EXCLUDES = 13;
  
  public static final int MQCFOP_CONTAINS_GEN = 26;
  
  public static final int MQCFOP_EXCLUDES_GEN = 29;
  
  public static final int MQCFT_NONE = 0;
  
  public static final int MQCFT_COMMAND = 1;
  
  public static final int MQCFT_RESPONSE = 2;
  
  public static final int MQCFT_INTEGER = 3;
  
  public static final int MQCFT_STRING = 4;
  
  public static final int MQCFT_INTEGER_LIST = 5;
  
  public static final int MQCFT_STRING_LIST = 6;
  
  public static final int MQCFT_EVENT = 7;
  
  public static final int MQCFT_USER = 8;
  
  public static final int MQCFT_BYTE_STRING = 9;
  
  public static final int MQCFT_TRACE_ROUTE = 10;
  
  public static final int MQCFT_REPORT = 12;
  
  public static final int MQCFT_INTEGER_FILTER = 13;
  
  public static final int MQCFT_STRING_FILTER = 14;
  
  public static final int MQCFT_BYTE_STRING_FILTER = 15;
  
  public static final int MQCFT_COMMAND_XR = 16;
  
  public static final int MQCFT_XR_MSG = 17;
  
  public static final int MQCFT_XR_ITEM = 18;
  
  public static final int MQCFT_XR_SUMMARY = 19;
  
  public static final int MQCFT_GROUP = 20;
  
  public static final int MQCFT_STATISTICS = 21;
  
  public static final int MQCFT_ACCOUNTING = 22;
  
  public static final int MQCFT_INTEGER64 = 23;
  
  public static final int MQCFT_INTEGER64_LIST = 25;
  
  public static final int MQCFT_APP_ACTIVITY = 26;
  
  public static final int MQCFT_STATUS = 27;
  
  public static final int MQOPMODE_COMPAT = 0;
  
  public static final int MQOPMODE_NEW_FUNCTION = 1;
  
  public static final int MQBACF_FIRST = 7001;
  
  public static final int MQBACF_EVENT_ACCOUNTING_TOKEN = 7001;
  
  public static final int MQBACF_EVENT_SECURITY_ID = 7002;
  
  public static final int MQBACF_RESPONSE_SET = 7003;
  
  public static final int MQBACF_RESPONSE_ID = 7004;
  
  public static final int MQBACF_EXTERNAL_UOW_ID = 7005;
  
  public static final int MQBACF_CONNECTION_ID = 7006;
  
  public static final int MQBACF_GENERIC_CONNECTION_ID = 7007;
  
  public static final int MQBACF_ORIGIN_UOW_ID = 7008;
  
  public static final int MQBACF_Q_MGR_UOW_ID = 7009;
  
  public static final int MQBACF_ACCOUNTING_TOKEN = 7010;
  
  public static final int MQBACF_CORREL_ID = 7011;
  
  public static final int MQBACF_GROUP_ID = 7012;
  
  public static final int MQBACF_MSG_ID = 7013;
  
  public static final int MQBACF_CF_LEID = 7014;
  
  public static final int MQBACF_DESTINATION_CORREL_ID = 7015;
  
  public static final int MQBACF_SUB_ID = 7016;
  
  public static final int MQBACF_ALTERNATE_SECURITYID = 7019;
  
  public static final int MQBACF_MESSAGE_DATA = 7020;
  
  public static final int MQBACF_MQBO_STRUCT = 7021;
  
  public static final int MQBACF_MQCB_FUNCTION = 7022;
  
  public static final int MQBACF_MQCBC_STRUCT = 7023;
  
  public static final int MQBACF_MQCBD_STRUCT = 7024;
  
  public static final int MQBACF_MQCD_STRUCT = 7025;
  
  public static final int MQBACF_MQCNO_STRUCT = 7026;
  
  public static final int MQBACF_MQGMO_STRUCT = 7027;
  
  public static final int MQBACF_MQMD_STRUCT = 7028;
  
  public static final int MQBACF_MQPMO_STRUCT = 7029;
  
  public static final int MQBACF_MQSD_STRUCT = 7030;
  
  public static final int MQBACF_MQSTS_STRUCT = 7031;
  
  public static final int MQBACF_SUB_CORREL_ID = 7032;
  
  public static final int MQBACF_XA_XID = 7033;
  
  public static final int MQBACF_XQH_CORREL_ID = 7034;
  
  public static final int MQBACF_XQH_MSG_ID = 7035;
  
  public static final int MQBACF_REQUEST_ID = 7036;
  
  public static final int MQBACF_PROPERTIES_DATA = 7037;
  
  public static final int MQBACF_CONN_TAG = 7038;
  
  public static final int MQBACF_MQBNO_STRUCT = 7039;
  
  public static final int MQBACF_LAST_USED = 7039;
  
  public static final int MQIAMO_FIRST = 701;
  
  public static final int MQIAMO_AVG_BATCH_SIZE = 702;
  
  public static final int MQIAMO_AVG_Q_TIME = 703;
  
  public static final int MQIAMO64_AVG_Q_TIME = 703;
  
  public static final int MQIAMO_BACKOUTS = 704;
  
  public static final int MQIAMO_BROWSES = 705;
  
  public static final int MQIAMO_BROWSE_MAX_BYTES = 706;
  
  public static final int MQIAMO_BROWSE_MIN_BYTES = 707;
  
  public static final int MQIAMO_BROWSES_FAILED = 708;
  
  public static final int MQIAMO_CLOSES = 709;
  
  public static final int MQIAMO_COMMITS = 710;
  
  public static final int MQIAMO_COMMITS_FAILED = 711;
  
  public static final int MQIAMO_CONNS = 712;
  
  public static final int MQIAMO_CONNS_MAX = 713;
  
  public static final int MQIAMO_DISCS = 714;
  
  public static final int MQIAMO_DISCS_IMPLICIT = 715;
  
  public static final int MQIAMO_DISC_TYPE = 716;
  
  public static final int MQIAMO_EXIT_TIME_AVG = 717;
  
  public static final int MQIAMO_EXIT_TIME_MAX = 718;
  
  public static final int MQIAMO_EXIT_TIME_MIN = 719;
  
  public static final int MQIAMO_FULL_BATCHES = 720;
  
  public static final int MQIAMO_GENERATED_MSGS = 721;
  
  public static final int MQIAMO_GETS = 722;
  
  public static final int MQIAMO_GET_MAX_BYTES = 723;
  
  public static final int MQIAMO_GET_MIN_BYTES = 724;
  
  public static final int MQIAMO_GETS_FAILED = 725;
  
  public static final int MQIAMO_INCOMPLETE_BATCHES = 726;
  
  public static final int MQIAMO_INQS = 727;
  
  public static final int MQIAMO_MSGS = 728;
  
  public static final int MQIAMO_NET_TIME_AVG = 729;
  
  public static final int MQIAMO_NET_TIME_MAX = 730;
  
  public static final int MQIAMO_NET_TIME_MIN = 731;
  
  public static final int MQIAMO_OBJECT_COUNT = 732;
  
  public static final int MQIAMO_OPENS = 733;
  
  public static final int MQIAMO_PUT1S = 734;
  
  public static final int MQIAMO_PUTS = 735;
  
  public static final int MQIAMO_PUT_MAX_BYTES = 736;
  
  public static final int MQIAMO_PUT_MIN_BYTES = 737;
  
  public static final int MQIAMO_PUT_RETRIES = 738;
  
  public static final int MQIAMO_Q_MAX_DEPTH = 739;
  
  public static final int MQIAMO_Q_MIN_DEPTH = 740;
  
  public static final int MQIAMO_Q_TIME_AVG = 741;
  
  public static final int MQIAMO64_Q_TIME_AVG = 741;
  
  public static final int MQIAMO_Q_TIME_MAX = 742;
  
  public static final int MQIAMO64_Q_TIME_MAX = 742;
  
  public static final int MQIAMO_Q_TIME_MIN = 743;
  
  public static final int MQIAMO64_Q_TIME_MIN = 743;
  
  public static final int MQIAMO_SETS = 744;
  
  public static final int MQIAMO64_BROWSE_BYTES = 745;
  
  public static final int MQIAMO64_BYTES = 746;
  
  public static final int MQIAMO64_GET_BYTES = 747;
  
  public static final int MQIAMO64_PUT_BYTES = 748;
  
  public static final int MQIAMO_CONNS_FAILED = 749;
  
  public static final int MQIAMO_OPENS_FAILED = 751;
  
  public static final int MQIAMO_INQS_FAILED = 752;
  
  public static final int MQIAMO_SETS_FAILED = 753;
  
  public static final int MQIAMO_PUTS_FAILED = 754;
  
  public static final int MQIAMO_PUT1S_FAILED = 755;
  
  public static final int MQIAMO_CLOSES_FAILED = 757;
  
  public static final int MQIAMO_MSGS_EXPIRED = 758;
  
  public static final int MQIAMO_MSGS_NOT_QUEUED = 759;
  
  public static final int MQIAMO_MSGS_PURGED = 760;
  
  public static final int MQIAMO_SUBS_DUR = 764;
  
  public static final int MQIAMO_SUBS_NDUR = 765;
  
  public static final int MQIAMO_SUBS_FAILED = 766;
  
  public static final int MQIAMO_SUBRQS = 767;
  
  public static final int MQIAMO_SUBRQS_FAILED = 768;
  
  public static final int MQIAMO_CBS = 769;
  
  public static final int MQIAMO_CBS_FAILED = 770;
  
  public static final int MQIAMO_CTLS = 771;
  
  public static final int MQIAMO_CTLS_FAILED = 772;
  
  public static final int MQIAMO_STATS = 773;
  
  public static final int MQIAMO_STATS_FAILED = 774;
  
  public static final int MQIAMO_SUB_DUR_HIGHWATER = 775;
  
  public static final int MQIAMO_SUB_DUR_LOWWATER = 776;
  
  public static final int MQIAMO_SUB_NDUR_HIGHWATER = 777;
  
  public static final int MQIAMO_SUB_NDUR_LOWWATER = 778;
  
  public static final int MQIAMO_TOPIC_PUTS = 779;
  
  public static final int MQIAMO_TOPIC_PUTS_FAILED = 780;
  
  public static final int MQIAMO_TOPIC_PUT1S = 781;
  
  public static final int MQIAMO_TOPIC_PUT1S_FAILED = 782;
  
  public static final int MQIAMO64_TOPIC_PUT_BYTES = 783;
  
  public static final int MQIAMO_PUBLISH_MSG_COUNT = 784;
  
  public static final int MQIAMO64_PUBLISH_MSG_BYTES = 785;
  
  public static final int MQIAMO_UNSUBS_DUR = 786;
  
  public static final int MQIAMO_UNSUBS_NDUR = 787;
  
  public static final int MQIAMO_UNSUBS_FAILED = 788;
  
  public static final int MQIAMO_INTERVAL = 789;
  
  public static final int MQIAMO_MSGS_SENT = 790;
  
  public static final int MQIAMO_BYTES_SENT = 791;
  
  public static final int MQIAMO_REPAIR_BYTES = 792;
  
  public static final int MQIAMO_FEEDBACK_MODE = 793;
  
  public static final int MQIAMO_RELIABILITY_TYPE = 794;
  
  public static final int MQIAMO_LATE_JOIN_MARK = 795;
  
  public static final int MQIAMO_NACKS_RCVD = 796;
  
  public static final int MQIAMO_REPAIR_PKTS = 797;
  
  public static final int MQIAMO_HISTORY_PKTS = 798;
  
  public static final int MQIAMO_PENDING_PKTS = 799;
  
  public static final int MQIAMO_PKT_RATE = 800;
  
  public static final int MQIAMO_MCAST_XMIT_RATE = 801;
  
  public static final int MQIAMO_MCAST_BATCH_TIME = 802;
  
  public static final int MQIAMO_MCAST_HEARTBEAT = 803;
  
  public static final int MQIAMO_DEST_DATA_PORT = 804;
  
  public static final int MQIAMO_DEST_REPAIR_PORT = 805;
  
  public static final int MQIAMO_ACKS_RCVD = 806;
  
  public static final int MQIAMO_ACTIVE_ACKERS = 807;
  
  public static final int MQIAMO_PKTS_SENT = 808;
  
  public static final int MQIAMO_TOTAL_REPAIR_PKTS = 809;
  
  public static final int MQIAMO_TOTAL_PKTS_SENT = 810;
  
  public static final int MQIAMO_TOTAL_MSGS_SENT = 811;
  
  public static final int MQIAMO_TOTAL_BYTES_SENT = 812;
  
  public static final int MQIAMO_NUM_STREAMS = 813;
  
  public static final int MQIAMO_ACK_FEEDBACK = 814;
  
  public static final int MQIAMO_NACK_FEEDBACK = 815;
  
  public static final int MQIAMO_PKTS_LOST = 816;
  
  public static final int MQIAMO_MSGS_RCVD = 817;
  
  public static final int MQIAMO_MSG_BYTES_RCVD = 818;
  
  public static final int MQIAMO_MSGS_DELIVERED = 819;
  
  public static final int MQIAMO_PKTS_PROCESSED = 820;
  
  public static final int MQIAMO_PKTS_DELIVERED = 821;
  
  public static final int MQIAMO_PKTS_DROPPED = 822;
  
  public static final int MQIAMO_PKTS_DUPLICATED = 823;
  
  public static final int MQIAMO_NACKS_CREATED = 824;
  
  public static final int MQIAMO_NACK_PKTS_SENT = 825;
  
  public static final int MQIAMO_REPAIR_PKTS_RQSTD = 826;
  
  public static final int MQIAMO_REPAIR_PKTS_RCVD = 827;
  
  public static final int MQIAMO_PKTS_REPAIRED = 828;
  
  public static final int MQIAMO_TOTAL_MSGS_RCVD = 829;
  
  public static final int MQIAMO_TOTAL_MSG_BYTES_RCVD = 830;
  
  public static final int MQIAMO_TOTAL_REPAIR_PKTS_RCVD = 831;
  
  public static final int MQIAMO_TOTAL_REPAIR_PKTS_RQSTD = 832;
  
  public static final int MQIAMO_TOTAL_MSGS_PROCESSED = 833;
  
  public static final int MQIAMO_TOTAL_MSGS_SELECTED = 834;
  
  public static final int MQIAMO_TOTAL_MSGS_EXPIRED = 835;
  
  public static final int MQIAMO_TOTAL_MSGS_DELIVERED = 836;
  
  public static final int MQIAMO_TOTAL_MSGS_RETURNED = 837;
  
  public static final int MQIAMO64_HIGHRES_TIME = 838;
  
  public static final int MQIAMO_MONITOR_CLASS = 839;
  
  public static final int MQIAMO_MONITOR_TYPE = 840;
  
  public static final int MQIAMO_MONITOR_ELEMENT = 841;
  
  public static final int MQIAMO_MONITOR_DATATYPE = 842;
  
  public static final int MQIAMO_MONITOR_FLAGS = 843;
  
  public static final int MQIAMO64_QMGR_OP_DURATION = 844;
  
  public static final int MQIAMO64_MONITOR_INTERVAL = 845;
  
  public static final int MQIAMO_LAST_USED = 845;
  
  public static final int MQIAMO_MONITOR_FLAGS_NONE = 0;
  
  public static final int MQIAMO_MONITOR_FLAGS_OBJNAME = 1;
  
  public static final int MQIAMO_MONITOR_UNIT = 1;
  
  public static final int MQIAMO_MONITOR_DELTA = 2;
  
  public static final int MQIAMO_MONITOR_HUNDREDTHS = 100;
  
  public static final int MQIAMO_MONITOR_KB = 1024;
  
  public static final int MQIAMO_MONITOR_PERCENT = 10000;
  
  public static final int MQIAMO_MONITOR_MICROSEC = 1000000;
  
  public static final int MQIAMO_MONITOR_MB = 1048576;
  
  public static final int MQIAMO_MONITOR_GB = 100000000;
  
  public static final int MQIACF_FIRST = 1001;
  
  public static final int MQIACF_Q_MGR_ATTRS = 1001;
  
  public static final int MQIACF_Q_ATTRS = 1002;
  
  public static final int MQIACF_PROCESS_ATTRS = 1003;
  
  public static final int MQIACF_NAMELIST_ATTRS = 1004;
  
  public static final int MQIACF_FORCE = 1005;
  
  public static final int MQIACF_REPLACE = 1006;
  
  public static final int MQIACF_PURGE = 1007;
  
  public static final int MQIACF_QUIESCE = 1008;
  
  public static final int MQIACF_MODE = 1008;
  
  public static final int MQIACF_ALL = 1009;
  
  public static final int MQIACF_EVENT_APPL_TYPE = 1010;
  
  public static final int MQIACF_EVENT_ORIGIN = 1011;
  
  public static final int MQIACF_PARAMETER_ID = 1012;
  
  public static final int MQIACF_ERROR_ID = 1013;
  
  public static final int MQIACF_ERROR_IDENTIFIER = 1013;
  
  public static final int MQIACF_SELECTOR = 1014;
  
  public static final int MQIACF_CHANNEL_ATTRS = 1015;
  
  public static final int MQIACF_OBJECT_TYPE = 1016;
  
  public static final int MQIACF_ESCAPE_TYPE = 1017;
  
  public static final int MQIACF_ERROR_OFFSET = 1018;
  
  public static final int MQIACF_AUTH_INFO_ATTRS = 1019;
  
  public static final int MQIACF_REASON_QUALIFIER = 1020;
  
  public static final int MQIACF_COMMAND = 1021;
  
  public static final int MQIACF_OPEN_OPTIONS = 1022;
  
  public static final int MQIACF_OPEN_TYPE = 1023;
  
  public static final int MQIACF_PROCESS_ID = 1024;
  
  public static final int MQIACF_THREAD_ID = 1025;
  
  public static final int MQIACF_Q_STATUS_ATTRS = 1026;
  
  public static final int MQIACF_UNCOMMITTED_MSGS = 1027;
  
  public static final int MQIACF_HANDLE_STATE = 1028;
  
  public static final int MQIACF_AUX_ERROR_DATA_INT_1 = 1070;
  
  public static final int MQIACF_AUX_ERROR_DATA_INT_2 = 1071;
  
  public static final int MQIACF_CONV_REASON_CODE = 1072;
  
  public static final int MQIACF_BRIDGE_TYPE = 1073;
  
  public static final int MQIACF_INQUIRY = 1074;
  
  public static final int MQIACF_WAIT_INTERVAL = 1075;
  
  public static final int MQIACF_OPTIONS = 1076;
  
  public static final int MQIACF_BROKER_OPTIONS = 1077;
  
  public static final int MQIACF_REFRESH_TYPE = 1078;
  
  public static final int MQIACF_SEQUENCE_NUMBER = 1079;
  
  public static final int MQIACF_INTEGER_DATA = 1080;
  
  public static final int MQIACF_REGISTRATION_OPTIONS = 1081;
  
  public static final int MQIACF_PUBLICATION_OPTIONS = 1082;
  
  public static final int MQIACF_CLUSTER_INFO = 1083;
  
  public static final int MQIACF_Q_MGR_DEFINITION_TYPE = 1084;
  
  public static final int MQIACF_Q_MGR_TYPE = 1085;
  
  public static final int MQIACF_ACTION = 1086;
  
  public static final int MQIACF_SUSPEND = 1087;
  
  public static final int MQIACF_BROKER_COUNT = 1088;
  
  public static final int MQIACF_APPL_COUNT = 1089;
  
  public static final int MQIACF_ANONYMOUS_COUNT = 1090;
  
  public static final int MQIACF_REG_REG_OPTIONS = 1091;
  
  public static final int MQIACF_DELETE_OPTIONS = 1092;
  
  public static final int MQIACF_CLUSTER_Q_MGR_ATTRS = 1093;
  
  public static final int MQIACF_REFRESH_INTERVAL = 1094;
  
  public static final int MQIACF_REFRESH_REPOSITORY = 1095;
  
  public static final int MQIACF_REMOVE_QUEUES = 1096;
  
  public static final int MQIACF_OPEN_INPUT_TYPE = 1098;
  
  public static final int MQIACF_OPEN_OUTPUT = 1099;
  
  public static final int MQIACF_OPEN_SET = 1100;
  
  public static final int MQIACF_OPEN_INQUIRE = 1101;
  
  public static final int MQIACF_OPEN_BROWSE = 1102;
  
  public static final int MQIACF_Q_STATUS_TYPE = 1103;
  
  public static final int MQIACF_Q_HANDLE = 1104;
  
  public static final int MQIACF_Q_STATUS = 1105;
  
  public static final int MQIACF_SECURITY_TYPE = 1106;
  
  public static final int MQIACF_CONNECTION_ATTRS = 1107;
  
  public static final int MQIACF_CONNECT_OPTIONS = 1108;
  
  public static final int MQIACF_CONN_INFO_TYPE = 1110;
  
  public static final int MQIACF_CONN_INFO_CONN = 1111;
  
  public static final int MQIACF_CONN_INFO_HANDLE = 1112;
  
  public static final int MQIACF_CONN_INFO_ALL = 1113;
  
  public static final int MQIACF_AUTH_PROFILE_ATTRS = 1114;
  
  public static final int MQIACF_AUTHORIZATION_LIST = 1115;
  
  public static final int MQIACF_AUTH_ADD_AUTHS = 1116;
  
  public static final int MQIACF_AUTH_REMOVE_AUTHS = 1117;
  
  public static final int MQIACF_ENTITY_TYPE = 1118;
  
  public static final int MQIACF_COMMAND_INFO = 1120;
  
  public static final int MQIACF_CMDSCOPE_Q_MGR_COUNT = 1121;
  
  public static final int MQIACF_Q_MGR_SYSTEM = 1122;
  
  public static final int MQIACF_Q_MGR_EVENT = 1123;
  
  public static final int MQIACF_Q_MGR_DQM = 1124;
  
  public static final int MQIACF_Q_MGR_CLUSTER = 1125;
  
  public static final int MQIACF_QSG_DISPS = 1126;
  
  public static final int MQIACF_UOW_STATE = 1128;
  
  public static final int MQIACF_SECURITY_ITEM = 1129;
  
  public static final int MQIACF_CF_STRUC_STATUS = 1130;
  
  public static final int MQIACF_UOW_TYPE = 1132;
  
  public static final int MQIACF_CF_STRUC_ATTRS = 1133;
  
  public static final int MQIACF_EXCLUDE_INTERVAL = 1134;
  
  public static final int MQIACF_CF_STATUS_TYPE = 1135;
  
  public static final int MQIACF_CF_STATUS_SUMMARY = 1136;
  
  public static final int MQIACF_CF_STATUS_CONNECT = 1137;
  
  public static final int MQIACF_CF_STATUS_BACKUP = 1138;
  
  public static final int MQIACF_CF_STRUC_TYPE = 1139;
  
  public static final int MQIACF_CF_STRUC_SIZE_MAX = 1140;
  
  public static final int MQIACF_CF_STRUC_SIZE_USED = 1141;
  
  public static final int MQIACF_CF_STRUC_ENTRIES_MAX = 1142;
  
  public static final int MQIACF_CF_STRUC_ENTRIES_USED = 1143;
  
  public static final int MQIACF_CF_STRUC_BACKUP_SIZE = 1144;
  
  public static final int MQIACF_MOVE_TYPE = 1145;
  
  public static final int MQIACF_MOVE_TYPE_MOVE = 1146;
  
  public static final int MQIACF_MOVE_TYPE_ADD = 1147;
  
  public static final int MQIACF_Q_MGR_NUMBER = 1148;
  
  public static final int MQIACF_Q_MGR_STATUS = 1149;
  
  public static final int MQIACF_DB2_CONN_STATUS = 1150;
  
  public static final int MQIACF_SECURITY_ATTRS = 1151;
  
  public static final int MQIACF_SECURITY_TIMEOUT = 1152;
  
  public static final int MQIACF_SECURITY_INTERVAL = 1153;
  
  public static final int MQIACF_SECURITY_SWITCH = 1154;
  
  public static final int MQIACF_SECURITY_SETTING = 1155;
  
  public static final int MQIACF_STORAGE_CLASS_ATTRS = 1156;
  
  public static final int MQIACF_USAGE_TYPE = 1157;
  
  public static final int MQIACF_BUFFER_POOL_ID = 1158;
  
  public static final int MQIACF_USAGE_TOTAL_PAGES = 1159;
  
  public static final int MQIACF_USAGE_UNUSED_PAGES = 1160;
  
  public static final int MQIACF_USAGE_PERSIST_PAGES = 1161;
  
  public static final int MQIACF_USAGE_NONPERSIST_PAGES = 1162;
  
  public static final int MQIACF_USAGE_RESTART_EXTENTS = 1163;
  
  public static final int MQIACF_USAGE_EXPAND_COUNT = 1164;
  
  public static final int MQIACF_PAGESET_STATUS = 1165;
  
  public static final int MQIACF_USAGE_TOTAL_BUFFERS = 1166;
  
  public static final int MQIACF_USAGE_DATA_SET_TYPE = 1167;
  
  public static final int MQIACF_USAGE_PAGESET = 1168;
  
  public static final int MQIACF_USAGE_DATA_SET = 1169;
  
  public static final int MQIACF_USAGE_BUFFER_POOL = 1170;
  
  public static final int MQIACF_MOVE_COUNT = 1171;
  
  public static final int MQIACF_EXPIRY_Q_COUNT = 1172;
  
  public static final int MQIACF_CONFIGURATION_OBJECTS = 1173;
  
  public static final int MQIACF_CONFIGURATION_EVENTS = 1174;
  
  public static final int MQIACF_SYSP_TYPE = 1175;
  
  public static final int MQIACF_SYSP_DEALLOC_INTERVAL = 1176;
  
  public static final int MQIACF_SYSP_MAX_ARCHIVE = 1177;
  
  public static final int MQIACF_SYSP_MAX_READ_TAPES = 1178;
  
  public static final int MQIACF_SYSP_IN_BUFFER_SIZE = 1179;
  
  public static final int MQIACF_SYSP_OUT_BUFFER_SIZE = 1180;
  
  public static final int MQIACF_SYSP_OUT_BUFFER_COUNT = 1181;
  
  public static final int MQIACF_SYSP_ARCHIVE = 1182;
  
  public static final int MQIACF_SYSP_DUAL_ACTIVE = 1183;
  
  public static final int MQIACF_SYSP_DUAL_ARCHIVE = 1184;
  
  public static final int MQIACF_SYSP_DUAL_BSDS = 1185;
  
  public static final int MQIACF_SYSP_MAX_CONNS = 1186;
  
  public static final int MQIACF_SYSP_MAX_CONNS_FORE = 1187;
  
  public static final int MQIACF_SYSP_MAX_CONNS_BACK = 1188;
  
  public static final int MQIACF_SYSP_EXIT_INTERVAL = 1189;
  
  public static final int MQIACF_SYSP_EXIT_TASKS = 1190;
  
  public static final int MQIACF_SYSP_CHKPOINT_COUNT = 1191;
  
  public static final int MQIACF_SYSP_OTMA_INTERVAL = 1192;
  
  public static final int MQIACF_SYSP_Q_INDEX_DEFER = 1193;
  
  public static final int MQIACF_SYSP_DB2_TASKS = 1194;
  
  public static final int MQIACF_SYSP_RESLEVEL_AUDIT = 1195;
  
  public static final int MQIACF_SYSP_ROUTING_CODE = 1196;
  
  public static final int MQIACF_SYSP_SMF_ACCOUNTING = 1197;
  
  public static final int MQIACF_SYSP_SMF_STATS = 1198;
  
  public static final int MQIACF_SYSP_SMF_INTERVAL = 1199;
  
  public static final int MQIACF_SYSP_SMF_STAT_TIME_MINS = 1199;
  
  public static final int MQIACF_SYSP_TRACE_CLASS = 1200;
  
  public static final int MQIACF_SYSP_TRACE_SIZE = 1201;
  
  public static final int MQIACF_SYSP_WLM_INTERVAL = 1202;
  
  public static final int MQIACF_SYSP_ALLOC_UNIT = 1203;
  
  public static final int MQIACF_SYSP_ARCHIVE_RETAIN = 1204;
  
  public static final int MQIACF_SYSP_ARCHIVE_WTOR = 1205;
  
  public static final int MQIACF_SYSP_BLOCK_SIZE = 1206;
  
  public static final int MQIACF_SYSP_CATALOG = 1207;
  
  public static final int MQIACF_SYSP_COMPACT = 1208;
  
  public static final int MQIACF_SYSP_ALLOC_PRIMARY = 1209;
  
  public static final int MQIACF_SYSP_ALLOC_SECONDARY = 1210;
  
  public static final int MQIACF_SYSP_PROTECT = 1211;
  
  public static final int MQIACF_SYSP_QUIESCE_INTERVAL = 1212;
  
  public static final int MQIACF_SYSP_TIMESTAMP = 1213;
  
  public static final int MQIACF_SYSP_UNIT_ADDRESS = 1214;
  
  public static final int MQIACF_SYSP_UNIT_STATUS = 1215;
  
  public static final int MQIACF_SYSP_LOG_COPY = 1216;
  
  public static final int MQIACF_SYSP_LOG_USED = 1217;
  
  public static final int MQIACF_SYSP_LOG_SUSPEND = 1218;
  
  public static final int MQIACF_SYSP_OFFLOAD_STATUS = 1219;
  
  public static final int MQIACF_SYSP_TOTAL_LOGS = 1220;
  
  public static final int MQIACF_SYSP_FULL_LOGS = 1221;
  
  public static final int MQIACF_LISTENER_ATTRS = 1222;
  
  public static final int MQIACF_LISTENER_STATUS_ATTRS = 1223;
  
  public static final int MQIACF_SERVICE_ATTRS = 1224;
  
  public static final int MQIACF_SERVICE_STATUS_ATTRS = 1225;
  
  public static final int MQIACF_Q_TIME_INDICATOR = 1226;
  
  public static final int MQIACF_OLDEST_MSG_AGE = 1227;
  
  public static final int MQIACF_AUTH_OPTIONS = 1228;
  
  public static final int MQIACF_Q_MGR_STATUS_ATTRS = 1229;
  
  public static final int MQIACF_CONNECTION_COUNT = 1230;
  
  public static final int MQIACF_Q_MGR_FACILITY = 1231;
  
  public static final int MQIACF_CHINIT_STATUS = 1232;
  
  public static final int MQIACF_CMD_SERVER_STATUS = 1233;
  
  public static final int MQIACF_ROUTE_DETAIL = 1234;
  
  public static final int MQIACF_RECORDED_ACTIVITIES = 1235;
  
  public static final int MQIACF_MAX_ACTIVITIES = 1236;
  
  public static final int MQIACF_DISCONTINUITY_COUNT = 1237;
  
  public static final int MQIACF_ROUTE_ACCUMULATION = 1238;
  
  public static final int MQIACF_ROUTE_DELIVERY = 1239;
  
  public static final int MQIACF_OPERATION_TYPE = 1240;
  
  public static final int MQIACF_BACKOUT_COUNT = 1241;
  
  public static final int MQIACF_COMP_CODE = 1242;
  
  public static final int MQIACF_ENCODING = 1243;
  
  public static final int MQIACF_EXPIRY = 1244;
  
  public static final int MQIACF_FEEDBACK = 1245;
  
  public static final int MQIACF_MSG_FLAGS = 1247;
  
  public static final int MQIACF_MSG_LENGTH = 1248;
  
  public static final int MQIACF_MSG_TYPE = 1249;
  
  public static final int MQIACF_OFFSET = 1250;
  
  public static final int MQIACF_ORIGINAL_LENGTH = 1251;
  
  public static final int MQIACF_PERSISTENCE = 1252;
  
  public static final int MQIACF_PRIORITY = 1253;
  
  public static final int MQIACF_REASON_CODE = 1254;
  
  public static final int MQIACF_REPORT = 1255;
  
  public static final int MQIACF_VERSION = 1256;
  
  public static final int MQIACF_UNRECORDED_ACTIVITIES = 1257;
  
  public static final int MQIACF_MONITORING = 1258;
  
  public static final int MQIACF_ROUTE_FORWARDING = 1259;
  
  public static final int MQIACF_SERVICE_STATUS = 1260;
  
  public static final int MQIACF_Q_TYPES = 1261;
  
  public static final int MQIACF_USER_ID_SUPPORT = 1262;
  
  public static final int MQIACF_INTERFACE_VERSION = 1263;
  
  public static final int MQIACF_AUTH_SERVICE_ATTRS = 1264;
  
  public static final int MQIACF_USAGE_EXPAND_TYPE = 1265;
  
  public static final int MQIACF_SYSP_CLUSTER_CACHE = 1266;
  
  public static final int MQIACF_SYSP_DB2_BLOB_TASKS = 1267;
  
  public static final int MQIACF_SYSP_WLM_INT_UNITS = 1268;
  
  public static final int MQIACF_TOPIC_ATTRS = 1269;
  
  public static final int MQIACF_PUBSUB_PROPERTIES = 1271;
  
  public static final int MQIACF_DESTINATION_CLASS = 1273;
  
  public static final int MQIACF_DURABLE_SUBSCRIPTION = 1274;
  
  public static final int MQIACF_SUBSCRIPTION_SCOPE = 1275;
  
  public static final int MQIACF_VARIABLE_USER_ID = 1277;
  
  public static final int MQIACF_REQUEST_ONLY = 1280;
  
  public static final int MQIACF_PUB_PRIORITY = 1283;
  
  public static final int MQIACF_SUB_ATTRS = 1287;
  
  public static final int MQIACF_WILDCARD_SCHEMA = 1288;
  
  public static final int MQIACF_SUB_TYPE = 1289;
  
  public static final int MQIACF_MESSAGE_COUNT = 1290;
  
  public static final int MQIACF_Q_MGR_PUBSUB = 1291;
  
  public static final int MQIACF_Q_MGR_VERSION = 1292;
  
  public static final int MQIACF_SUB_STATUS_ATTRS = 1294;
  
  public static final int MQIACF_TOPIC_STATUS = 1295;
  
  public static final int MQIACF_TOPIC_SUB = 1296;
  
  public static final int MQIACF_TOPIC_PUB = 1297;
  
  public static final int MQIACF_RETAINED_PUBLICATION = 1300;
  
  public static final int MQIACF_TOPIC_STATUS_ATTRS = 1301;
  
  public static final int MQIACF_TOPIC_STATUS_TYPE = 1302;
  
  public static final int MQIACF_SUB_OPTIONS = 1303;
  
  public static final int MQIACF_PUBLISH_COUNT = 1304;
  
  public static final int MQIACF_CLEAR_TYPE = 1305;
  
  public static final int MQIACF_CLEAR_SCOPE = 1306;
  
  public static final int MQIACF_SUB_LEVEL = 1307;
  
  public static final int MQIACF_ASYNC_STATE = 1308;
  
  public static final int MQIACF_SUB_SUMMARY = 1309;
  
  public static final int MQIACF_OBSOLETE_MSGS = 1310;
  
  public static final int MQIACF_PUBSUB_STATUS = 1311;
  
  public static final int MQIACF_PS_STATUS_TYPE = 1314;
  
  public static final int MQIACF_PUBSUB_STATUS_ATTRS = 1318;
  
  public static final int MQIACF_SELECTOR_TYPE = 1321;
  
  public static final int MQIACF_LOG_COMPRESSION = 1322;
  
  public static final int MQIACF_GROUPUR_CHECK_ID = 1323;
  
  public static final int MQIACF_MULC_CAPTURE = 1324;
  
  public static final int MQIACF_PERMIT_STANDBY = 1325;
  
  public static final int MQIACF_OPERATION_MODE = 1326;
  
  public static final int MQIACF_COMM_INFO_ATTRS = 1327;
  
  public static final int MQIACF_CF_SMDS_BLOCK_SIZE = 1328;
  
  public static final int MQIACF_CF_SMDS_EXPAND = 1329;
  
  public static final int MQIACF_USAGE_FREE_BUFF = 1330;
  
  public static final int MQIACF_USAGE_FREE_BUFF_PERC = 1331;
  
  public static final int MQIACF_CF_STRUC_ACCESS = 1332;
  
  public static final int MQIACF_CF_STATUS_SMDS = 1333;
  
  public static final int MQIACF_SMDS_ATTRS = 1334;
  
  public static final int MQIACF_USAGE_SMDS = 1335;
  
  public static final int MQIACF_USAGE_BLOCK_SIZE = 1336;
  
  public static final int MQIACF_USAGE_DATA_BLOCKS = 1337;
  
  public static final int MQIACF_USAGE_EMPTY_BUFFERS = 1338;
  
  public static final int MQIACF_USAGE_INUSE_BUFFERS = 1339;
  
  public static final int MQIACF_USAGE_LOWEST_FREE = 1340;
  
  public static final int MQIACF_USAGE_OFFLOAD_MSGS = 1341;
  
  public static final int MQIACF_USAGE_READS_SAVED = 1342;
  
  public static final int MQIACF_USAGE_SAVED_BUFFERS = 1343;
  
  public static final int MQIACF_USAGE_TOTAL_BLOCKS = 1344;
  
  public static final int MQIACF_USAGE_USED_BLOCKS = 1345;
  
  public static final int MQIACF_USAGE_USED_RATE = 1346;
  
  public static final int MQIACF_USAGE_WAIT_RATE = 1347;
  
  public static final int MQIACF_SMDS_OPENMODE = 1348;
  
  public static final int MQIACF_SMDS_STATUS = 1349;
  
  public static final int MQIACF_SMDS_AVAIL = 1350;
  
  public static final int MQIACF_MCAST_REL_INDICATOR = 1351;
  
  public static final int MQIACF_CHLAUTH_TYPE = 1352;
  
  public static final int MQIACF_MQXR_DIAGNOSTICS_TYPE = 1354;
  
  public static final int MQIACF_CHLAUTH_ATTRS = 1355;
  
  public static final int MQIACF_OPERATION_ID = 1356;
  
  public static final int MQIACF_API_CALLER_TYPE = 1357;
  
  public static final int MQIACF_API_ENVIRONMENT = 1358;
  
  public static final int MQIACF_TRACE_DETAIL = 1359;
  
  public static final int MQIACF_HOBJ = 1360;
  
  public static final int MQIACF_CALL_TYPE = 1361;
  
  public static final int MQIACF_MQCB_OPERATION = 1362;
  
  public static final int MQIACF_MQCB_TYPE = 1363;
  
  public static final int MQIACF_MQCB_OPTIONS = 1364;
  
  public static final int MQIACF_CLOSE_OPTIONS = 1365;
  
  public static final int MQIACF_CTL_OPERATION = 1366;
  
  public static final int MQIACF_GET_OPTIONS = 1367;
  
  public static final int MQIACF_RECS_PRESENT = 1368;
  
  public static final int MQIACF_KNOWN_DEST_COUNT = 1369;
  
  public static final int MQIACF_UNKNOWN_DEST_COUNT = 1370;
  
  public static final int MQIACF_INVALID_DEST_COUNT = 1371;
  
  public static final int MQIACF_RESOLVED_TYPE = 1372;
  
  public static final int MQIACF_PUT_OPTIONS = 1373;
  
  public static final int MQIACF_BUFFER_LENGTH = 1374;
  
  public static final int MQIACF_TRACE_DATA_LENGTH = 1375;
  
  public static final int MQIACF_SMDS_EXPANDST = 1376;
  
  public static final int MQIACF_STRUC_LENGTH = 1377;
  
  public static final int MQIACF_ITEM_COUNT = 1378;
  
  public static final int MQIACF_EXPIRY_TIME = 1379;
  
  public static final int MQIACF_CONNECT_TIME = 1380;
  
  public static final int MQIACF_DISCONNECT_TIME = 1381;
  
  public static final int MQIACF_HSUB = 1382;
  
  public static final int MQIACF_SUBRQ_OPTIONS = 1383;
  
  public static final int MQIACF_XA_RMID = 1384;
  
  public static final int MQIACF_XA_FLAGS = 1385;
  
  public static final int MQIACF_XA_RETCODE = 1386;
  
  public static final int MQIACF_XA_HANDLE = 1387;
  
  public static final int MQIACF_XA_RETVAL = 1388;
  
  public static final int MQIACF_STATUS_TYPE = 1389;
  
  public static final int MQIACF_XA_COUNT = 1390;
  
  public static final int MQIACF_SELECTOR_COUNT = 1391;
  
  public static final int MQIACF_SELECTORS = 1392;
  
  public static final int MQIACF_INTATTR_COUNT = 1393;
  
  public static final int MQIACF_INT_ATTRS = 1394;
  
  public static final int MQIACF_SUBRQ_ACTION = 1395;
  
  public static final int MQIACF_NUM_PUBS = 1396;
  
  public static final int MQIACF_POINTER_SIZE = 1397;
  
  public static final int MQIACF_REMOVE_AUTHREC = 1398;
  
  public static final int MQIACF_XR_ATTRS = 1399;
  
  public static final int MQIACF_APPL_FUNCTION_TYPE = 1400;
  
  public static final int MQIACF_AMQP_ATTRS = 1401;
  
  public static final int MQIACF_EXPORT_TYPE = 1402;
  
  public static final int MQIACF_EXPORT_ATTRS = 1403;
  
  public static final int MQIACF_SYSTEM_OBJECTS = 1404;
  
  public static final int MQIACF_CONNECTION_SWAP = 1405;
  
  public static final int MQIACF_AMQP_DIAGNOSTICS_TYPE = 1406;
  
  public static final int MQIACF_BUFFER_POOL_LOCATION = 1408;
  
  public static final int MQIACF_LDAP_CONNECTION_STATUS = 1409;
  
  public static final int MQIACF_SYSP_MAX_ACE_POOL = 1410;
  
  public static final int MQIACF_PAGECLAS = 1411;
  
  public static final int MQIACF_AUTH_REC_TYPE = 1412;
  
  public static final int MQIACF_SYSP_MAX_CONC_OFFLOADS = 1413;
  
  public static final int MQIACF_SYSP_ZHYPERWRITE = 1414;
  
  public static final int MQIACF_Q_MGR_STATUS_LOG = 1415;
  
  public static final int MQIACF_ARCHIVE_LOG_SIZE = 1416;
  
  public static final int MQIACF_MEDIA_LOG_SIZE = 1417;
  
  public static final int MQIACF_RESTART_LOG_SIZE = 1418;
  
  public static final int MQIACF_REUSABLE_LOG_SIZE = 1419;
  
  public static final int MQIACF_LOG_IN_USE = 1420;
  
  public static final int MQIACF_LOG_UTILIZATION = 1421;
  
  public static final int MQIACF_LOG_REDUCTION = 1422;
  
  public static final int MQIACF_IGNORE_STATE = 1423;
  
  public static final int MQIACF_MOVABLE_APPL_COUNT = 1424;
  
  public static final int MQIACF_APPL_INFO_ATTRS = 1425;
  
  public static final int MQIACF_APPL_MOVABLE = 1426;
  
  public static final int MQIACF_REMOTE_QMGR_ACTIVE = 1427;
  
  public static final int MQIACF_APPL_INFO_TYPE = 1428;
  
  public static final int MQIACF_APPL_INFO_APPL = 1429;
  
  public static final int MQIACF_APPL_INFO_QMGR = 1430;
  
  public static final int MQIACF_APPL_INFO_LOCAL = 1431;
  
  public static final int MQIACF_APPL_IMMOVABLE_COUNT = 1432;
  
  public static final int MQIACF_BALANCED = 1433;
  
  public static final int MQIACF_BALSTATE = 1434;
  
  public static final int MQIACF_APPL_IMMOVABLE_REASON = 1435;
  
  public static final int MQIACF_DS_ENCRYPTED = 1436;
  
  public static final int MQIACF_CUR_Q_FILE_SIZE = 1437;
  
  public static final int MQIACF_CUR_MAX_FILE_SIZE = 1438;
  
  public static final int MQIACF_BALANCING_TYPE = 1439;
  
  public static final int MQIACF_BALANCING_OPTIONS = 1440;
  
  public static final int MQIACF_BALANCING_TIMEOUT = 1441;
  
  public static final int MQIACF_SYSP_SMF_STAT_TIME_SECS = 1442;
  
  public static final int MQIACF_SYSP_SMF_ACCT_TIME_MINS = 1443;
  
  public static final int MQIACF_SYSP_SMF_ACCT_TIME_SECS = 1444;
  
  public static final int MQIACF_Q_MGR_STATUS_INFO_TYPE = 1445;
  
  public static final int MQIACF_Q_MGR_STATUS_INFO_Q_MGR = 1446;
  
  public static final int MQIACF_Q_MGR_STATUS_INFO_NHA = 1447;
  
  public static final int MQIACF_AUTO_CLUSTER_TYPE = 1448;
  
  public static final int MQIACF_DATA_FS_IN_USE = 1449;
  
  public static final int MQIACF_DATA_FS_SIZE = 1450;
  
  public static final int MQIACF_LOG_EXTENT_SIZE = 1451;
  
  public static final int MQIACF_LOG_FS_IN_USE = 1452;
  
  public static final int MQIACF_LOG_FS_SIZE = 1453;
  
  public static final int MQIACF_LOG_PRIMARIES = 1454;
  
  public static final int MQIACF_LOG_SECONDARIES = 1455;
  
  public static final int MQIACF_LOG_TYPE = 1456;
  
  public static final int MQIACF_NHA_INSTANCE_ACTV_CONNS = 1457;
  
  public static final int MQIACF_NHA_INSTANCE_BACKLOG = 1458;
  
  public static final int MQIACF_NHA_INSTANCE_IN_SYNC = 1459;
  
  public static final int MQIACF_NHA_INSTANCE_ROLE = 1460;
  
  public static final int MQIACF_NHA_IN_SYNC_INSTANCES = 1461;
  
  public static final int MQIACF_NHA_TOTAL_INSTANCES = 1462;
  
  public static final int MQIACF_Q_MGR_FS_ENCRYPTED = 1463;
  
  public static final int MQIACF_Q_MGR_FS_IN_USE = 1464;
  
  public static final int MQIACF_Q_MGR_FS_SIZE = 1465;
  
  public static final int MQIACF_LAST_USED = 1465;
  
  public static final int MQCFACCESS_ENABLED = 0;
  
  public static final int MQCFACCESS_SUSPENDED = 1;
  
  public static final int MQCFACCESS_DISABLED = 2;
  
  public static final int MQS_OPENMODE_NONE = 0;
  
  public static final int MQS_OPENMODE_READONLY = 1;
  
  public static final int MQS_OPENMODE_UPDATE = 2;
  
  public static final int MQS_OPENMODE_RECOVERY = 3;
  
  public static final int MQS_STATUS_CLOSED = 0;
  
  public static final int MQS_STATUS_CLOSING = 1;
  
  public static final int MQS_STATUS_OPENING = 2;
  
  public static final int MQS_STATUS_OPEN = 3;
  
  public static final int MQS_STATUS_NOTENABLED = 4;
  
  public static final int MQS_STATUS_ALLOCFAIL = 5;
  
  public static final int MQS_STATUS_OPENFAIL = 6;
  
  public static final int MQS_STATUS_STGFAIL = 7;
  
  public static final int MQS_STATUS_DATAFAIL = 8;
  
  public static final int MQS_AVAIL_NORMAL = 0;
  
  public static final int MQS_AVAIL_ERROR = 1;
  
  public static final int MQS_AVAIL_STOPPED = 2;
  
  public static final int MQBPLOCATION_BELOW = 0;
  
  public static final int MQBPLOCATION_ABOVE = 1;
  
  public static final int MQBPLOCATION_SWITCHING_ABOVE = 2;
  
  public static final int MQBPLOCATION_SWITCHING_BELOW = 3;
  
  public static final int MQPAGECLAS_4KB = 0;
  
  public static final int MQPAGECLAS_FIXED4KB = 1;
  
  public static final int MQS_EXPANDST_NORMAL = 0;
  
  public static final int MQS_EXPANDST_FAILED = 1;
  
  public static final int MQS_EXPANDST_MAXIMUM = 2;
  
  public static final int MQUSAGE_SMDS_AVAILABLE = 0;
  
  public static final int MQUSAGE_SMDS_NO_DATA = 1;
  
  public static final int MQIACH_FIRST = 1501;
  
  public static final int MQIACH_XMIT_PROTOCOL_TYPE = 1501;
  
  public static final int MQIACH_BATCH_SIZE = 1502;
  
  public static final int MQIACH_DISC_INTERVAL = 1503;
  
  public static final int MQIACH_SHORT_TIMER = 1504;
  
  public static final int MQIACH_SHORT_RETRY = 1505;
  
  public static final int MQIACH_LONG_TIMER = 1506;
  
  public static final int MQIACH_LONG_RETRY = 1507;
  
  public static final int MQIACH_PUT_AUTHORITY = 1508;
  
  public static final int MQIACH_SEQUENCE_NUMBER_WRAP = 1509;
  
  public static final int MQIACH_MAX_MSG_LENGTH = 1510;
  
  public static final int MQIACH_CHANNEL_TYPE = 1511;
  
  public static final int MQIACH_DATA_COUNT = 1512;
  
  public static final int MQIACH_NAME_COUNT = 1513;
  
  public static final int MQIACH_MSG_SEQUENCE_NUMBER = 1514;
  
  public static final int MQIACH_DATA_CONVERSION = 1515;
  
  public static final int MQIACH_IN_DOUBT = 1516;
  
  public static final int MQIACH_MCA_TYPE = 1517;
  
  public static final int MQIACH_SESSION_COUNT = 1518;
  
  public static final int MQIACH_ADAPTER = 1519;
  
  public static final int MQIACH_COMMAND_COUNT = 1520;
  
  public static final int MQIACH_SOCKET = 1521;
  
  public static final int MQIACH_PORT = 1522;
  
  public static final int MQIACH_CHANNEL_INSTANCE_TYPE = 1523;
  
  public static final int MQIACH_CHANNEL_INSTANCE_ATTRS = 1524;
  
  public static final int MQIACH_CHANNEL_ERROR_DATA = 1525;
  
  public static final int MQIACH_CHANNEL_TABLE = 1526;
  
  public static final int MQIACH_CHANNEL_STATUS = 1527;
  
  public static final int MQIACH_INDOUBT_STATUS = 1528;
  
  public static final int MQIACH_LAST_SEQ_NUMBER = 1529;
  
  public static final int MQIACH_LAST_SEQUENCE_NUMBER = 1529;
  
  public static final int MQIACH_CURRENT_MSGS = 1531;
  
  public static final int MQIACH_CURRENT_SEQ_NUMBER = 1532;
  
  public static final int MQIACH_CURRENT_SEQUENCE_NUMBER = 1532;
  
  public static final int MQIACH_SSL_RETURN_CODE = 1533;
  
  public static final int MQIACH_MSGS = 1534;
  
  public static final int MQIACH_BYTES_SENT = 1535;
  
  public static final int MQIACH_BYTES_RCVD = 1536;
  
  public static final int MQIACH_BYTES_RECEIVED = 1536;
  
  public static final int MQIACH_BATCHES = 1537;
  
  public static final int MQIACH_BUFFERS_SENT = 1538;
  
  public static final int MQIACH_BUFFERS_RCVD = 1539;
  
  public static final int MQIACH_BUFFERS_RECEIVED = 1539;
  
  public static final int MQIACH_LONG_RETRIES_LEFT = 1540;
  
  public static final int MQIACH_SHORT_RETRIES_LEFT = 1541;
  
  public static final int MQIACH_MCA_STATUS = 1542;
  
  public static final int MQIACH_STOP_REQUESTED = 1543;
  
  public static final int MQIACH_MR_COUNT = 1544;
  
  public static final int MQIACH_MR_INTERVAL = 1545;
  
  public static final int MQIACH_NPM_SPEED = 1562;
  
  public static final int MQIACH_HB_INTERVAL = 1563;
  
  public static final int MQIACH_BATCH_INTERVAL = 1564;
  
  public static final int MQIACH_NETWORK_PRIORITY = 1565;
  
  public static final int MQIACH_KEEP_ALIVE_INTERVAL = 1566;
  
  public static final int MQIACH_BATCH_HB = 1567;
  
  public static final int MQIACH_SSL_CLIENT_AUTH = 1568;
  
  public static final int MQIACH_ALLOC_RETRY = 1570;
  
  public static final int MQIACH_ALLOC_FAST_TIMER = 1571;
  
  public static final int MQIACH_ALLOC_SLOW_TIMER = 1572;
  
  public static final int MQIACH_DISC_RETRY = 1573;
  
  public static final int MQIACH_PORT_NUMBER = 1574;
  
  public static final int MQIACH_HDR_COMPRESSION = 1575;
  
  public static final int MQIACH_MSG_COMPRESSION = 1576;
  
  public static final int MQIACH_CLWL_CHANNEL_RANK = 1577;
  
  public static final int MQIACH_CLWL_CHANNEL_PRIORITY = 1578;
  
  public static final int MQIACH_CLWL_CHANNEL_WEIGHT = 1579;
  
  public static final int MQIACH_CHANNEL_DISP = 1580;
  
  public static final int MQIACH_INBOUND_DISP = 1581;
  
  public static final int MQIACH_CHANNEL_TYPES = 1582;
  
  public static final int MQIACH_ADAPS_STARTED = 1583;
  
  public static final int MQIACH_ADAPS_MAX = 1584;
  
  public static final int MQIACH_DISPS_STARTED = 1585;
  
  public static final int MQIACH_DISPS_MAX = 1586;
  
  public static final int MQIACH_SSLTASKS_STARTED = 1587;
  
  public static final int MQIACH_SSLTASKS_MAX = 1588;
  
  public static final int MQIACH_CURRENT_CHL = 1589;
  
  public static final int MQIACH_CURRENT_CHL_MAX = 1590;
  
  public static final int MQIACH_CURRENT_CHL_TCP = 1591;
  
  public static final int MQIACH_CURRENT_CHL_LU62 = 1592;
  
  public static final int MQIACH_ACTIVE_CHL = 1593;
  
  public static final int MQIACH_ACTIVE_CHL_MAX = 1594;
  
  public static final int MQIACH_ACTIVE_CHL_PAUSED = 1595;
  
  public static final int MQIACH_ACTIVE_CHL_STARTED = 1596;
  
  public static final int MQIACH_ACTIVE_CHL_STOPPED = 1597;
  
  public static final int MQIACH_ACTIVE_CHL_RETRY = 1598;
  
  public static final int MQIACH_LISTENER_STATUS = 1599;
  
  public static final int MQIACH_SHARED_CHL_RESTART = 1600;
  
  public static final int MQIACH_LISTENER_CONTROL = 1601;
  
  public static final int MQIACH_BACKLOG = 1602;
  
  public static final int MQIACH_XMITQ_TIME_INDICATOR = 1604;
  
  public static final int MQIACH_NETWORK_TIME_INDICATOR = 1605;
  
  public static final int MQIACH_EXIT_TIME_INDICATOR = 1606;
  
  public static final int MQIACH_BATCH_SIZE_INDICATOR = 1607;
  
  public static final int MQIACH_XMITQ_MSGS_AVAILABLE = 1608;
  
  public static final int MQIACH_CHANNEL_SUBSTATE = 1609;
  
  public static final int MQIACH_SSL_KEY_RESETS = 1610;
  
  public static final int MQIACH_COMPRESSION_RATE = 1611;
  
  public static final int MQIACH_COMPRESSION_TIME = 1612;
  
  public static final int MQIACH_MAX_XMIT_SIZE = 1613;
  
  public static final int MQIACH_DEF_CHANNEL_DISP = 1614;
  
  public static final int MQIACH_SHARING_CONVERSATIONS = 1615;
  
  public static final int MQIACH_MAX_SHARING_CONVS = 1616;
  
  public static final int MQIACH_CURRENT_SHARING_CONVS = 1617;
  
  public static final int MQIACH_MAX_INSTANCES = 1618;
  
  public static final int MQIACH_MAX_INSTS_PER_CLIENT = 1619;
  
  public static final int MQIACH_CLIENT_CHANNEL_WEIGHT = 1620;
  
  public static final int MQIACH_CONNECTION_AFFINITY = 1621;
  
  public static final int MQIACH_AUTH_INFO_TYPES = 1622;
  
  public static final int MQIACH_RESET_REQUESTED = 1623;
  
  public static final int MQIACH_BATCH_DATA_LIMIT = 1624;
  
  public static final int MQIACH_MSG_HISTORY = 1625;
  
  public static final int MQIACH_MULTICAST_PROPERTIES = 1626;
  
  public static final int MQIACH_NEW_SUBSCRIBER_HISTORY = 1627;
  
  public static final int MQIACH_MC_HB_INTERVAL = 1628;
  
  public static final int MQIACH_USE_CLIENT_ID = 1629;
  
  public static final int MQIACH_MQTT_KEEP_ALIVE = 1630;
  
  public static final int MQIACH_IN_DOUBT_IN = 1631;
  
  public static final int MQIACH_IN_DOUBT_OUT = 1632;
  
  public static final int MQIACH_MSGS_SENT = 1633;
  
  public static final int MQIACH_MSGS_RECEIVED = 1634;
  
  public static final int MQIACH_MSGS_RCVD = 1634;
  
  public static final int MQIACH_PENDING_OUT = 1635;
  
  public static final int MQIACH_AVAILABLE_CIPHERSPECS = 1636;
  
  public static final int MQIACH_MATCH = 1637;
  
  public static final int MQIACH_USER_SOURCE = 1638;
  
  public static final int MQIACH_WARNING = 1639;
  
  public static final int MQIACH_DEF_RECONNECT = 1640;
  
  public static final int MQIACH_CHANNEL_SUMMARY_ATTRS = 1642;
  
  public static final int MQIACH_PROTOCOL = 1643;
  
  public static final int MQIACH_AMQP_KEEP_ALIVE = 1644;
  
  public static final int MQIACH_SECURITY_PROTOCOL = 1645;
  
  public static final int MQIACH_SPL_PROTECTION = 1646;
  
  public static final int MQIACH_LAST_USED = 1646;
  
  public static final int MQCAMO_FIRST = 2701;
  
  public static final int MQCAMO_CLOSE_DATE = 2701;
  
  public static final int MQCAMO_CLOSE_TIME = 2702;
  
  public static final int MQCAMO_CONN_DATE = 2703;
  
  public static final int MQCAMO_CONN_TIME = 2704;
  
  public static final int MQCAMO_DISC_DATE = 2705;
  
  public static final int MQCAMO_DISC_TIME = 2706;
  
  public static final int MQCAMO_END_DATE = 2707;
  
  public static final int MQCAMO_END_TIME = 2708;
  
  public static final int MQCAMO_OPEN_DATE = 2709;
  
  public static final int MQCAMO_OPEN_TIME = 2710;
  
  public static final int MQCAMO_START_DATE = 2711;
  
  public static final int MQCAMO_START_TIME = 2712;
  
  public static final int MQCAMO_MONITOR_CLASS = 2713;
  
  public static final int MQCAMO_MONITOR_TYPE = 2714;
  
  public static final int MQCAMO_MONITOR_DESC = 2715;
  
  public static final int MQCAMO_LAST_USED = 2715;
  
  public static final int MQCACF_FIRST = 3001;
  
  public static final int MQCACF_FROM_Q_NAME = 3001;
  
  public static final int MQCACF_TO_Q_NAME = 3002;
  
  public static final int MQCACF_FROM_PROCESS_NAME = 3003;
  
  public static final int MQCACF_TO_PROCESS_NAME = 3004;
  
  public static final int MQCACF_FROM_NAMELIST_NAME = 3005;
  
  public static final int MQCACF_TO_NAMELIST_NAME = 3006;
  
  public static final int MQCACF_FROM_CHANNEL_NAME = 3007;
  
  public static final int MQCACF_TO_CHANNEL_NAME = 3008;
  
  public static final int MQCACF_FROM_AUTH_INFO_NAME = 3009;
  
  public static final int MQCACF_TO_AUTH_INFO_NAME = 3010;
  
  public static final int MQCACF_Q_NAMES = 3011;
  
  public static final int MQCACF_PROCESS_NAMES = 3012;
  
  public static final int MQCACF_NAMELIST_NAMES = 3013;
  
  public static final int MQCACF_ESCAPE_TEXT = 3014;
  
  public static final int MQCACF_LOCAL_Q_NAMES = 3015;
  
  public static final int MQCACF_MODEL_Q_NAMES = 3016;
  
  public static final int MQCACF_ALIAS_Q_NAMES = 3017;
  
  public static final int MQCACF_REMOTE_Q_NAMES = 3018;
  
  public static final int MQCACF_SENDER_CHANNEL_NAMES = 3019;
  
  public static final int MQCACF_SERVER_CHANNEL_NAMES = 3020;
  
  public static final int MQCACF_REQUESTER_CHANNEL_NAMES = 3021;
  
  public static final int MQCACF_RECEIVER_CHANNEL_NAMES = 3022;
  
  public static final int MQCACF_OBJECT_Q_MGR_NAME = 3023;
  
  public static final int MQCACF_APPL_NAME = 3024;
  
  public static final int MQCACF_USER_IDENTIFIER = 3025;
  
  public static final int MQCACF_AUX_ERROR_DATA_STR_1 = 3026;
  
  public static final int MQCACF_AUX_ERROR_DATA_STR_2 = 3027;
  
  public static final int MQCACF_AUX_ERROR_DATA_STR_3 = 3028;
  
  public static final int MQCACF_BRIDGE_NAME = 3029;
  
  public static final int MQCACF_STREAM_NAME = 3030;
  
  public static final int MQCACF_TOPIC = 3031;
  
  public static final int MQCACF_PARENT_Q_MGR_NAME = 3032;
  
  public static final int MQCACF_CORREL_ID = 3033;
  
  public static final int MQCACF_PUBLISH_TIMESTAMP = 3034;
  
  public static final int MQCACF_STRING_DATA = 3035;
  
  public static final int MQCACF_SUPPORTED_STREAM_NAME = 3036;
  
  public static final int MQCACF_REG_TOPIC = 3037;
  
  public static final int MQCACF_REG_TIME = 3038;
  
  public static final int MQCACF_REG_USER_ID = 3039;
  
  public static final int MQCACF_CHILD_Q_MGR_NAME = 3040;
  
  public static final int MQCACF_REG_STREAM_NAME = 3041;
  
  public static final int MQCACF_REG_Q_MGR_NAME = 3042;
  
  public static final int MQCACF_REG_Q_NAME = 3043;
  
  public static final int MQCACF_REG_CORREL_ID = 3044;
  
  public static final int MQCACF_EVENT_USER_ID = 3045;
  
  public static final int MQCACF_OBJECT_NAME = 3046;
  
  public static final int MQCACF_EVENT_Q_MGR = 3047;
  
  public static final int MQCACF_AUTH_INFO_NAMES = 3048;
  
  public static final int MQCACF_EVENT_APPL_IDENTITY = 3049;
  
  public static final int MQCACF_EVENT_APPL_NAME = 3050;
  
  public static final int MQCACF_EVENT_APPL_ORIGIN = 3051;
  
  public static final int MQCACF_SUBSCRIPTION_NAME = 3052;
  
  public static final int MQCACF_REG_SUB_NAME = 3053;
  
  public static final int MQCACF_SUBSCRIPTION_IDENTITY = 3054;
  
  public static final int MQCACF_REG_SUB_IDENTITY = 3055;
  
  public static final int MQCACF_SUBSCRIPTION_USER_DATA = 3056;
  
  public static final int MQCACF_REG_SUB_USER_DATA = 3057;
  
  public static final int MQCACF_APPL_TAG = 3058;
  
  public static final int MQCACF_DATA_SET_NAME = 3059;
  
  public static final int MQCACF_UOW_START_DATE = 3060;
  
  public static final int MQCACF_UOW_START_TIME = 3061;
  
  public static final int MQCACF_UOW_LOG_START_DATE = 3062;
  
  public static final int MQCACF_UOW_LOG_START_TIME = 3063;
  
  public static final int MQCACF_UOW_LOG_EXTENT_NAME = 3064;
  
  public static final int MQCACF_PRINCIPAL_ENTITY_NAMES = 3065;
  
  public static final int MQCACF_GROUP_ENTITY_NAMES = 3066;
  
  public static final int MQCACF_AUTH_PROFILE_NAME = 3067;
  
  public static final int MQCACF_ENTITY_NAME = 3068;
  
  public static final int MQCACF_SERVICE_COMPONENT = 3069;
  
  public static final int MQCACF_RESPONSE_Q_MGR_NAME = 3070;
  
  public static final int MQCACF_CURRENT_LOG_EXTENT_NAME = 3071;
  
  public static final int MQCACF_RESTART_LOG_EXTENT_NAME = 3072;
  
  public static final int MQCACF_MEDIA_LOG_EXTENT_NAME = 3073;
  
  public static final int MQCACF_LOG_PATH = 3074;
  
  public static final int MQCACF_COMMAND_MQSC = 3075;
  
  public static final int MQCACF_Q_MGR_CPF = 3076;
  
  public static final int MQCACF_USAGE_LOG_RBA = 3078;
  
  public static final int MQCACF_USAGE_LOG_LRSN = 3079;
  
  public static final int MQCACF_COMMAND_SCOPE = 3080;
  
  public static final int MQCACF_ASID = 3081;
  
  public static final int MQCACF_PSB_NAME = 3082;
  
  public static final int MQCACF_PST_ID = 3083;
  
  public static final int MQCACF_TASK_NUMBER = 3084;
  
  public static final int MQCACF_TRANSACTION_ID = 3085;
  
  public static final int MQCACF_Q_MGR_UOW_ID = 3086;
  
  public static final int MQCACF_ORIGIN_NAME = 3088;
  
  public static final int MQCACF_ENV_INFO = 3089;
  
  public static final int MQCACF_SECURITY_PROFILE = 3090;
  
  public static final int MQCACF_CONFIGURATION_DATE = 3091;
  
  public static final int MQCACF_CONFIGURATION_TIME = 3092;
  
  public static final int MQCACF_FROM_CF_STRUC_NAME = 3093;
  
  public static final int MQCACF_TO_CF_STRUC_NAME = 3094;
  
  public static final int MQCACF_CF_STRUC_NAMES = 3095;
  
  public static final int MQCACF_FAIL_DATE = 3096;
  
  public static final int MQCACF_FAIL_TIME = 3097;
  
  public static final int MQCACF_BACKUP_DATE = 3098;
  
  public static final int MQCACF_BACKUP_TIME = 3099;
  
  public static final int MQCACF_SYSTEM_NAME = 3100;
  
  public static final int MQCACF_CF_STRUC_BACKUP_START = 3101;
  
  public static final int MQCACF_CF_STRUC_BACKUP_END = 3102;
  
  public static final int MQCACF_CF_STRUC_LOG_Q_MGRS = 3103;
  
  public static final int MQCACF_FROM_STORAGE_CLASS = 3104;
  
  public static final int MQCACF_TO_STORAGE_CLASS = 3105;
  
  public static final int MQCACF_STORAGE_CLASS_NAMES = 3106;
  
  public static final int MQCACF_DSG_NAME = 3108;
  
  public static final int MQCACF_DB2_NAME = 3109;
  
  public static final int MQCACF_SYSP_CMD_USER_ID = 3110;
  
  public static final int MQCACF_SYSP_OTMA_GROUP = 3111;
  
  public static final int MQCACF_SYSP_OTMA_MEMBER = 3112;
  
  public static final int MQCACF_SYSP_OTMA_DRU_EXIT = 3113;
  
  public static final int MQCACF_SYSP_OTMA_TPIPE_PFX = 3114;
  
  public static final int MQCACF_SYSP_ARCHIVE_PFX1 = 3115;
  
  public static final int MQCACF_SYSP_ARCHIVE_UNIT1 = 3116;
  
  public static final int MQCACF_SYSP_LOG_CORREL_ID = 3117;
  
  public static final int MQCACF_SYSP_UNIT_VOLSER = 3118;
  
  public static final int MQCACF_SYSP_Q_MGR_TIME = 3119;
  
  public static final int MQCACF_SYSP_Q_MGR_DATE = 3120;
  
  public static final int MQCACF_SYSP_Q_MGR_RBA = 3121;
  
  public static final int MQCACF_SYSP_LOG_RBA = 3122;
  
  public static final int MQCACF_SYSP_SERVICE = 3123;
  
  public static final int MQCACF_FROM_LISTENER_NAME = 3124;
  
  public static final int MQCACF_TO_LISTENER_NAME = 3125;
  
  public static final int MQCACF_FROM_SERVICE_NAME = 3126;
  
  public static final int MQCACF_TO_SERVICE_NAME = 3127;
  
  public static final int MQCACF_LAST_PUT_DATE = 3128;
  
  public static final int MQCACF_LAST_PUT_TIME = 3129;
  
  public static final int MQCACF_LAST_GET_DATE = 3130;
  
  public static final int MQCACF_LAST_GET_TIME = 3131;
  
  public static final int MQCACF_OPERATION_DATE = 3132;
  
  public static final int MQCACF_OPERATION_TIME = 3133;
  
  public static final int MQCACF_ACTIVITY_DESC = 3134;
  
  public static final int MQCACF_APPL_IDENTITY_DATA = 3135;
  
  public static final int MQCACF_APPL_ORIGIN_DATA = 3136;
  
  public static final int MQCACF_PUT_DATE = 3137;
  
  public static final int MQCACF_PUT_TIME = 3138;
  
  public static final int MQCACF_REPLY_TO_Q = 3139;
  
  public static final int MQCACF_REPLY_TO_Q_MGR = 3140;
  
  public static final int MQCACF_RESOLVED_Q_NAME = 3141;
  
  public static final int MQCACF_STRUC_ID = 3142;
  
  public static final int MQCACF_VALUE_NAME = 3143;
  
  public static final int MQCACF_SERVICE_START_DATE = 3144;
  
  public static final int MQCACF_SERVICE_START_TIME = 3145;
  
  public static final int MQCACF_SYSP_OFFLINE_RBA = 3146;
  
  public static final int MQCACF_SYSP_ARCHIVE_PFX2 = 3147;
  
  public static final int MQCACF_SYSP_ARCHIVE_UNIT2 = 3148;
  
  public static final int MQCACF_TO_TOPIC_NAME = 3149;
  
  public static final int MQCACF_FROM_TOPIC_NAME = 3150;
  
  public static final int MQCACF_TOPIC_NAMES = 3151;
  
  public static final int MQCACF_SUB_NAME = 3152;
  
  public static final int MQCACF_DESTINATION_Q_MGR = 3153;
  
  public static final int MQCACF_DESTINATION = 3154;
  
  public static final int MQCACF_SUB_USER_ID = 3156;
  
  public static final int MQCACF_SUB_USER_DATA = 3159;
  
  public static final int MQCACF_SUB_SELECTOR = 3160;
  
  public static final int MQCACF_LAST_PUB_DATE = 3161;
  
  public static final int MQCACF_LAST_PUB_TIME = 3162;
  
  public static final int MQCACF_FROM_SUB_NAME = 3163;
  
  public static final int MQCACF_TO_SUB_NAME = 3164;
  
  public static final int MQCACF_LAST_MSG_TIME = 3167;
  
  public static final int MQCACF_LAST_MSG_DATE = 3168;
  
  public static final int MQCACF_SUBSCRIPTION_POINT = 3169;
  
  public static final int MQCACF_FILTER = 3170;
  
  public static final int MQCACF_NONE = 3171;
  
  public static final int MQCACF_ADMIN_TOPIC_NAMES = 3172;
  
  public static final int MQCACF_ROUTING_FINGER_PRINT = 3173;
  
  public static final int MQCACF_APPL_DESC = 3174;
  
  public static final int MQCACF_Q_MGR_START_DATE = 3175;
  
  public static final int MQCACF_Q_MGR_START_TIME = 3176;
  
  public static final int MQCACF_FROM_COMM_INFO_NAME = 3177;
  
  public static final int MQCACF_TO_COMM_INFO_NAME = 3178;
  
  public static final int MQCACF_CF_OFFLOAD_SIZE1 = 3179;
  
  public static final int MQCACF_CF_OFFLOAD_SIZE2 = 3180;
  
  public static final int MQCACF_CF_OFFLOAD_SIZE3 = 3181;
  
  public static final int MQCACF_CF_SMDS_GENERIC_NAME = 3182;
  
  public static final int MQCACF_CF_SMDS = 3183;
  
  public static final int MQCACF_RECOVERY_DATE = 3184;
  
  public static final int MQCACF_RECOVERY_TIME = 3185;
  
  public static final int MQCACF_CF_SMDSCONN = 3186;
  
  public static final int MQCACF_CF_STRUC_NAME = 3187;
  
  public static final int MQCACF_ALTERNATE_USERID = 3188;
  
  public static final int MQCACF_CHAR_ATTRS = 3189;
  
  public static final int MQCACF_DYNAMIC_Q_NAME = 3190;
  
  public static final int MQCACF_HOST_NAME = 3191;
  
  public static final int MQCACF_MQCB_NAME = 3192;
  
  public static final int MQCACF_OBJECT_STRING = 3193;
  
  public static final int MQCACF_RESOLVED_LOCAL_Q_MGR = 3194;
  
  public static final int MQCACF_RESOLVED_LOCAL_Q_NAME = 3195;
  
  public static final int MQCACF_RESOLVED_OBJECT_STRING = 3196;
  
  public static final int MQCACF_RESOLVED_Q_MGR = 3197;
  
  public static final int MQCACF_SELECTION_STRING = 3198;
  
  public static final int MQCACF_XA_INFO = 3199;
  
  public static final int MQCACF_APPL_FUNCTION = 3200;
  
  public static final int MQCACF_XQH_REMOTE_Q_NAME = 3201;
  
  public static final int MQCACF_XQH_REMOTE_Q_MGR = 3202;
  
  public static final int MQCACF_XQH_PUT_TIME = 3203;
  
  public static final int MQCACF_XQH_PUT_DATE = 3204;
  
  public static final int MQCACF_EXCL_OPERATOR_MESSAGES = 3205;
  
  public static final int MQCACF_CSP_USER_IDENTIFIER = 3206;
  
  public static final int MQCACF_AMQP_CLIENT_ID = 3207;
  
  public static final int MQCACF_ARCHIVE_LOG_EXTENT_NAME = 3208;
  
  public static final int MQCACF_APPL_IMMOVABLE_DATE = 3209;
  
  public static final int MQCACF_APPL_IMMOVABLE_TIME = 3210;
  
  public static final int MQCACF_NHA_INSTANCE_NAME = 3211;
  
  public static final int MQCACF_Q_MGR_DATA_PATH = 3212;
  
  public static final int MQCACF_UNIFORM_CLUSTER_NAME = 3213;
  
  public static final int MQCACF_LOG_START_DATE = 3214;
  
  public static final int MQCACF_LOG_START_LSN = 3215;
  
  public static final int MQCACF_LOG_START_TIME = 3216;
  
  public static final int MQCACF_NHA_GROUP_INITIAL_DATE = 3217;
  
  public static final int MQCACF_NHA_GROUP_INITIAL_LSN = 3218;
  
  public static final int MQCACF_NHA_GROUP_INITIAL_TIME = 3219;
  
  public static final int MQCACF_NHA_REPL_ADDRESS = 3220;
  
  public static final int MQCACF_LAST_USED = 3220;
  
  public static final int MQCACH_FIRST = 3501;
  
  public static final int MQCACH_CHANNEL_NAME = 3501;
  
  public static final int MQCACH_DESC = 3502;
  
  public static final int MQCACH_MODE_NAME = 3503;
  
  public static final int MQCACH_TP_NAME = 3504;
  
  public static final int MQCACH_XMIT_Q_NAME = 3505;
  
  public static final int MQCACH_CONNECTION_NAME = 3506;
  
  public static final int MQCACH_MCA_NAME = 3507;
  
  public static final int MQCACH_SEC_EXIT_NAME = 3508;
  
  public static final int MQCACH_MSG_EXIT_NAME = 3509;
  
  public static final int MQCACH_SEND_EXIT_NAME = 3510;
  
  public static final int MQCACH_RCV_EXIT_NAME = 3511;
  
  public static final int MQCACH_CHANNEL_NAMES = 3512;
  
  public static final int MQCACH_SEC_EXIT_USER_DATA = 3513;
  
  public static final int MQCACH_MSG_EXIT_USER_DATA = 3514;
  
  public static final int MQCACH_SEND_EXIT_USER_DATA = 3515;
  
  public static final int MQCACH_RCV_EXIT_USER_DATA = 3516;
  
  public static final int MQCACH_USER_ID = 3517;
  
  public static final int MQCACH_PASSWORD = 3518;
  
  public static final int MQCACH_LOCAL_ADDRESS = 3520;
  
  public static final int MQCACH_LOCAL_NAME = 3521;
  
  public static final int MQCACH_LAST_MSG_TIME = 3524;
  
  public static final int MQCACH_LAST_MSG_DATE = 3525;
  
  public static final int MQCACH_MCA_USER_ID = 3527;
  
  public static final int MQCACH_CHANNEL_START_TIME = 3528;
  
  public static final int MQCACH_CHANNEL_START_DATE = 3529;
  
  public static final int MQCACH_MCA_JOB_NAME = 3530;
  
  public static final int MQCACH_LAST_LUWID = 3531;
  
  public static final int MQCACH_CURRENT_LUWID = 3532;
  
  public static final int MQCACH_FORMAT_NAME = 3533;
  
  public static final int MQCACH_MR_EXIT_NAME = 3534;
  
  public static final int MQCACH_MR_EXIT_USER_DATA = 3535;
  
  public static final int MQCACH_SSL_CIPHER_SPEC = 3544;
  
  public static final int MQCACH_SSL_PEER_NAME = 3545;
  
  public static final int MQCACH_SSL_HANDSHAKE_STAGE = 3546;
  
  public static final int MQCACH_SSL_SHORT_PEER_NAME = 3547;
  
  public static final int MQCACH_REMOTE_APPL_TAG = 3548;
  
  public static final int MQCACH_SSL_CERT_USER_ID = 3549;
  
  public static final int MQCACH_SSL_CERT_ISSUER_NAME = 3550;
  
  public static final int MQCACH_LU_NAME = 3551;
  
  public static final int MQCACH_IP_ADDRESS = 3552;
  
  public static final int MQCACH_TCP_NAME = 3553;
  
  public static final int MQCACH_LISTENER_NAME = 3554;
  
  public static final int MQCACH_LISTENER_DESC = 3555;
  
  public static final int MQCACH_LISTENER_START_DATE = 3556;
  
  public static final int MQCACH_LISTENER_START_TIME = 3557;
  
  public static final int MQCACH_SSL_KEY_RESET_DATE = 3558;
  
  public static final int MQCACH_SSL_KEY_RESET_TIME = 3559;
  
  public static final int MQCACH_REMOTE_VERSION = 3560;
  
  public static final int MQCACH_REMOTE_PRODUCT = 3561;
  
  public static final int MQCACH_GROUP_ADDRESS = 3562;
  
  public static final int MQCACH_JAAS_CONFIG = 3563;
  
  public static final int MQCACH_CLIENT_ID = 3564;
  
  public static final int MQCACH_SSL_KEY_PASSPHRASE = 3565;
  
  public static final int MQCACH_CONNECTION_NAME_LIST = 3566;
  
  public static final int MQCACH_CLIENT_USER_ID = 3567;
  
  public static final int MQCACH_MCA_USER_ID_LIST = 3568;
  
  public static final int MQCACH_SSL_CIPHER_SUITE = 3569;
  
  public static final int MQCACH_WEBCONTENT_PATH = 3570;
  
  public static final int MQCACH_TOPIC_ROOT = 3571;
  
  public static final int MQCACH_TEMPORARY_MODEL_Q = 3572;
  
  public static final int MQCACH_TEMPORARY_Q_PREFIX = 3573;
  
  public static final int MQCACH_LAST_USED = 3573;
  
  public static final int MQGACF_FIRST = 8001;
  
  public static final int MQGACF_COMMAND_CONTEXT = 8001;
  
  public static final int MQGACF_COMMAND_DATA = 8002;
  
  public static final int MQGACF_TRACE_ROUTE = 8003;
  
  public static final int MQGACF_OPERATION = 8004;
  
  public static final int MQGACF_ACTIVITY = 8005;
  
  public static final int MQGACF_EMBEDDED_MQMD = 8006;
  
  public static final int MQGACF_MESSAGE = 8007;
  
  public static final int MQGACF_MQMD = 8008;
  
  public static final int MQGACF_VALUE_NAMING = 8009;
  
  public static final int MQGACF_Q_ACCOUNTING_DATA = 8010;
  
  public static final int MQGACF_Q_STATISTICS_DATA = 8011;
  
  public static final int MQGACF_CHL_STATISTICS_DATA = 8012;
  
  public static final int MQGACF_ACTIVITY_TRACE = 8013;
  
  public static final int MQGACF_APP_DIST_LIST = 8014;
  
  public static final int MQGACF_MONITOR_CLASS = 8015;
  
  public static final int MQGACF_MONITOR_TYPE = 8016;
  
  public static final int MQGACF_MONITOR_ELEMENT = 8017;
  
  public static final int MQGACF_APPL_STATUS = 8018;
  
  public static final int MQGACF_CHANGED_APPLS = 8019;
  
  public static final int MQGACF_ALL_APPLS = 8020;
  
  public static final int MQGACF_APPL_BALANCE = 8021;
  
  public static final int MQGACF_LAST_USED = 8021;
  
  public static final int MQACT_FORCE_REMOVE = 1;
  
  public static final int MQACT_ADVANCE_LOG = 2;
  
  public static final int MQACT_COLLECT_STATISTICS = 3;
  
  public static final int MQACT_PUBSUB = 4;
  
  public static final int MQACT_ADD = 5;
  
  public static final int MQACT_REPLACE = 6;
  
  public static final int MQACT_REMOVE = 7;
  
  public static final int MQACT_REMOVEALL = 8;
  
  public static final int MQACT_FAIL = 9;
  
  public static final int MQACT_REDUCE_LOG = 10;
  
  public static final int MQACT_ARCHIVE_LOG = 11;
  
  public static final int MQIS_NO = 0;
  
  public static final int MQIS_YES = 1;
  
  public static final int MQAPPL_IMMOVABLE = 0;
  
  public static final int MQAPPL_MOVABLE = 1;
  
  public static final int MQACTIVE_NO = 0;
  
  public static final int MQACTIVE_YES = 1;
  
  public static final int MQBALANCED_NO = 0;
  
  public static final int MQBALANCED_YES = 1;
  
  public static final int MQBALANCED_NOT_APPLICABLE = 2;
  
  public static final int MQBALANCED_UNKNOWN = 3;
  
  public static final int MQBALSTATE_NOT_APPLICABLE = 0;
  
  public static final int MQBALSTATE_LOW = 1;
  
  public static final int MQBALSTATE_OK = 2;
  
  public static final int MQBALSTATE_HIGH = 3;
  
  public static final int MQBALSTATE_UNKNOWN = 4;
  
  public static final int MQIMMREASON_NONE = 0;
  
  public static final int MQIMMREASON_NOT_CLIENT = 1;
  
  public static final int MQIMMREASON_NOT_RECONNECTABLE = 2;
  
  public static final int MQIMMREASON_MOVING = 3;
  
  public static final int MQIMMREASON_APPLNAME_CHANGED = 4;
  
  public static final int MQIMMREASON_IN_TRANSACTION = 5;
  
  public static final int MQIMMREASON_AWAITS_REPLY = 6;
  
  public static final int MQIMMREASON_NO_REDIRECT = 7;
  
  public static final int MQAS_NONE = 0;
  
  public static final int MQAS_STARTED = 1;
  
  public static final int MQAS_START_WAIT = 2;
  
  public static final int MQAS_STOPPED = 3;
  
  public static final int MQAS_SUSPENDED = 4;
  
  public static final int MQAS_SUSPENDED_TEMPORARY = 5;
  
  public static final int MQAS_ACTIVE = 6;
  
  public static final int MQAS_INACTIVE = 7;
  
  public static final int MQAUTH_NONE = 0;
  
  public static final int MQAUTH_ALT_USER_AUTHORITY = 1;
  
  public static final int MQAUTH_BROWSE = 2;
  
  public static final int MQAUTH_CHANGE = 3;
  
  public static final int MQAUTH_CLEAR = 4;
  
  public static final int MQAUTH_CONNECT = 5;
  
  public static final int MQAUTH_CREATE = 6;
  
  public static final int MQAUTH_DELETE = 7;
  
  public static final int MQAUTH_DISPLAY = 8;
  
  public static final int MQAUTH_INPUT = 9;
  
  public static final int MQAUTH_INQUIRE = 10;
  
  public static final int MQAUTH_OUTPUT = 11;
  
  public static final int MQAUTH_PASS_ALL_CONTEXT = 12;
  
  public static final int MQAUTH_PASS_IDENTITY_CONTEXT = 13;
  
  public static final int MQAUTH_SET = 14;
  
  public static final int MQAUTH_SET_ALL_CONTEXT = 15;
  
  public static final int MQAUTH_SET_IDENTITY_CONTEXT = 16;
  
  public static final int MQAUTH_CONTROL = 17;
  
  public static final int MQAUTH_CONTROL_EXTENDED = 18;
  
  public static final int MQAUTH_PUBLISH = 19;
  
  public static final int MQAUTH_SUBSCRIBE = 20;
  
  public static final int MQAUTH_RESUME = 21;
  
  public static final int MQAUTH_SYSTEM = 22;
  
  public static final int MQAUTH_ALL = -1;
  
  public static final int MQAUTH_ALL_ADMIN = -2;
  
  public static final int MQAUTH_ALL_MQI = -3;
  
  public static final int MQAUTHOPT_ENTITY_EXPLICIT = 1;
  
  public static final int MQAUTHOPT_ENTITY_SET = 2;
  
  public static final int MQAUTHOPT_NAME_EXPLICIT = 16;
  
  public static final int MQAUTHOPT_NAME_ALL_MATCHING = 32;
  
  public static final int MQAUTHOPT_NAME_AS_WILDCARD = 64;
  
  public static final int MQAUTHOPT_CUMULATIVE = 256;
  
  public static final int MQAUTHOPT_EXCLUDE_TEMP = 512;
  
  public static final int MQBT_OTMA = 1;
  
  public static final int MQCFO_REFRESH_REPOSITORY_YES = 1;
  
  public static final int MQCFO_REFRESH_REPOSITORY_NO = 0;
  
  public static final int MQCFO_REMOVE_QUEUES_YES = 1;
  
  public static final int MQCFO_REMOVE_QUEUES_NO = 0;
  
  public static final int MQCAUT_ALL = 0;
  
  public static final int MQCAUT_BLOCKUSER = 1;
  
  public static final int MQCAUT_BLOCKADDR = 2;
  
  public static final int MQCAUT_SSLPEERMAP = 3;
  
  public static final int MQCAUT_ADDRESSMAP = 4;
  
  public static final int MQCAUT_USERMAP = 5;
  
  public static final int MQCAUT_QMGRMAP = 6;
  
  public static final int MQCFSTATUS_NOT_FOUND = 0;
  
  public static final int MQCFSTATUS_ACTIVE = 1;
  
  public static final int MQCFSTATUS_IN_RECOVER = 2;
  
  public static final int MQCFSTATUS_IN_BACKUP = 3;
  
  public static final int MQCFSTATUS_FAILED = 4;
  
  public static final int MQCFSTATUS_NONE = 5;
  
  public static final int MQCFSTATUS_UNKNOWN = 6;
  
  public static final int MQCFSTATUS_RECOVERED = 7;
  
  public static final int MQCFSTATUS_EMPTY = 8;
  
  public static final int MQCFSTATUS_NEW = 9;
  
  public static final int MQCFSTATUS_ADMIN_INCOMPLETE = 20;
  
  public static final int MQCFSTATUS_NEVER_USED = 21;
  
  public static final int MQCFSTATUS_NO_BACKUP = 22;
  
  public static final int MQCFSTATUS_NOT_FAILED = 23;
  
  public static final int MQCFSTATUS_NOT_RECOVERABLE = 24;
  
  public static final int MQCFSTATUS_XES_ERROR = 25;
  
  public static final int MQCFTYPE_APPL = 0;
  
  public static final int MQCFTYPE_ADMIN = 1;
  
  public static final int MQCHIDS_NOT_INDOUBT = 0;
  
  public static final int MQCHIDS_INDOUBT = 1;
  
  public static final int MQCHLD_ALL = -1;
  
  public static final int MQCHLD_DEFAULT = 1;
  
  public static final int MQCHLD_SHARED = 2;
  
  public static final int MQCHLD_PRIVATE = 4;
  
  public static final int MQCHLD_FIXSHARED = 5;
  
  public static final int MQUCI_YES = 1;
  
  public static final int MQUCI_NO = 0;
  
  public static final int MQCHS_INACTIVE = 0;
  
  public static final int MQCHS_BINDING = 1;
  
  public static final int MQCHS_STARTING = 2;
  
  public static final int MQCHS_RUNNING = 3;
  
  public static final int MQCHS_STOPPING = 4;
  
  public static final int MQCHS_RETRYING = 5;
  
  public static final int MQCHS_STOPPED = 6;
  
  public static final int MQCHS_REQUESTING = 7;
  
  public static final int MQCHS_PAUSED = 8;
  
  public static final int MQCHS_DISCONNECTED = 9;
  
  public static final int MQCHS_INITIALIZING = 13;
  
  public static final int MQCHS_SWITCHING = 14;
  
  public static final int MQCHSSTATE_OTHER = 0;
  
  public static final int MQCHSSTATE_END_OF_BATCH = 100;
  
  public static final int MQCHSSTATE_SENDING = 200;
  
  public static final int MQCHSSTATE_RECEIVING = 300;
  
  public static final int MQCHSSTATE_SERIALIZING = 400;
  
  public static final int MQCHSSTATE_RESYNCHING = 500;
  
  public static final int MQCHSSTATE_HEARTBEATING = 600;
  
  public static final int MQCHSSTATE_IN_SCYEXIT = 700;
  
  public static final int MQCHSSTATE_IN_RCVEXIT = 800;
  
  public static final int MQCHSSTATE_IN_SENDEXIT = 900;
  
  public static final int MQCHSSTATE_IN_MSGEXIT = 1000;
  
  public static final int MQCHSSTATE_IN_MREXIT = 1100;
  
  public static final int MQCHSSTATE_IN_CHADEXIT = 1200;
  
  public static final int MQCHSSTATE_NET_CONNECTING = 1250;
  
  public static final int MQCHSSTATE_SSL_HANDSHAKING = 1300;
  
  public static final int MQCHSSTATE_NAME_SERVER = 1400;
  
  public static final int MQCHSSTATE_IN_MQPUT = 1500;
  
  public static final int MQCHSSTATE_IN_MQGET = 1600;
  
  public static final int MQCHSSTATE_IN_MQI_CALL = 1700;
  
  public static final int MQCHSSTATE_COMPRESSING = 1800;
  
  public static final int MQCHSH_RESTART_NO = 0;
  
  public static final int MQCHSH_RESTART_YES = 1;
  
  public static final int MQCHSR_STOP_NOT_REQUESTED = 0;
  
  public static final int MQCHSR_STOP_REQUESTED = 1;
  
  public static final int MQCHRR_RESET_NOT_REQUESTED = 0;
  
  public static final int MQCHTAB_Q_MGR = 1;
  
  public static final int MQCHTAB_CLNTCONN = 2;
  
  public static final int MQCLRS_LOCAL = 1;
  
  public static final int MQCLRS_GLOBAL = 2;
  
  public static final int MQCLRT_RETAINED = 1;
  
  public static final int MQCMDI_CMDSCOPE_ACCEPTED = 1;
  
  public static final int MQCMDI_CMDSCOPE_GENERATED = 2;
  
  public static final int MQCMDI_CMDSCOPE_COMPLETED = 3;
  
  public static final int MQCMDI_QSG_DISP_COMPLETED = 4;
  
  public static final int MQCMDI_COMMAND_ACCEPTED = 5;
  
  public static final int MQCMDI_CLUSTER_REQUEST_QUEUED = 6;
  
  public static final int MQCMDI_CHANNEL_INIT_STARTED = 7;
  
  public static final int MQCMDI_RECOVER_STARTED = 11;
  
  public static final int MQCMDI_BACKUP_STARTED = 12;
  
  public static final int MQCMDI_RECOVER_COMPLETED = 13;
  
  public static final int MQCMDI_SEC_TIMER_ZERO = 14;
  
  public static final int MQCMDI_REFRESH_CONFIGURATION = 16;
  
  public static final int MQCMDI_SEC_SIGNOFF_ERROR = 17;
  
  public static final int MQCMDI_IMS_BRIDGE_SUSPENDED = 18;
  
  public static final int MQCMDI_DB2_SUSPENDED = 19;
  
  public static final int MQCMDI_DB2_OBSOLETE_MSGS = 20;
  
  public static final int MQCMDI_SEC_UPPERCASE = 21;
  
  public static final int MQCMDI_SEC_MIXEDCASE = 22;
  
  public static final int MQDISCONNECT_NORMAL = 0;
  
  public static final int MQDISCONNECT_IMPLICIT = 1;
  
  public static final int MQDISCONNECT_Q_MGR = 2;
  
  public static final int MQET_MQSC = 1;
  
  public static final int MQEVO_OTHER = 0;
  
  public static final int MQEVO_CONSOLE = 1;
  
  public static final int MQEVO_INIT = 2;
  
  public static final int MQEVO_MSG = 3;
  
  public static final int MQEVO_MQSET = 4;
  
  public static final int MQEVO_INTERNAL = 5;
  
  public static final int MQEVO_MQSUB = 6;
  
  public static final int MQEVO_CTLMSG = 7;
  
  public static final int MQEVO_REST = 8;
  
  public static final int MQEVR_DISABLED = 0;
  
  public static final int MQEVR_ENABLED = 1;
  
  public static final int MQEVR_EXCEPTION = 2;
  
  public static final int MQEVR_NO_DISPLAY = 3;
  
  public static final int MQEVR_API_ONLY = 4;
  
  public static final int MQEVR_ADMIN_ONLY = 5;
  
  public static final int MQEVR_USER_ONLY = 6;
  
  public static final int MQFC_YES = 1;
  
  public static final int MQFC_NO = 0;
  
  public static final int MQHSTATE_INACTIVE = 0;
  
  public static final int MQHSTATE_ACTIVE = 1;
  
  public static final int MQINBD_Q_MGR = 0;
  
  public static final int MQINBD_GROUP = 3;
  
  public static final int MQIDO_COMMIT = 1;
  
  public static final int MQIDO_BACKOUT = 2;
  
  public static final int MQMATCH_GENERIC = 0;
  
  public static final int MQMATCH_RUNCHECK = 1;
  
  public static final int MQMATCH_EXACT = 2;
  
  public static final int MQMATCH_ALL = 3;
  
  public static final int MQMCAS_STOPPED = 0;
  
  public static final int MQMCAS_RUNNING = 3;
  
  public static final int MQMODE_FORCE = 0;
  
  public static final int MQMODE_QUIESCE = 1;
  
  public static final int MQMODE_TERMINATE = 2;
  
  public static final int MQMLP_TOLERATE_UNPROTECTED_NO = 0;
  
  public static final int MQMLP_TOLERATE_UNPROTECTED_YES = 1;
  
  public static final int MQMLP_ENCRYPTION_ALG_NONE = 0;
  
  public static final int MQMLP_ENCRYPTION_ALG_RC2 = 1;
  
  public static final int MQMLP_ENCRYPTION_ALG_DES = 2;
  
  public static final int MQMLP_ENCRYPTION_ALG_3DES = 3;
  
  public static final int MQMLP_ENCRYPTION_ALG_AES128 = 4;
  
  public static final int MQMLP_ENCRYPTION_ALG_AES256 = 5;
  
  public static final int MQMLP_SIGN_ALG_NONE = 0;
  
  public static final int MQMLP_SIGN_ALG_MD5 = 1;
  
  public static final int MQMLP_SIGN_ALG_SHA1 = 2;
  
  public static final int MQMLP_SIGN_ALG_SHA224 = 3;
  
  public static final int MQMLP_SIGN_ALG_SHA256 = 4;
  
  public static final int MQMLP_SIGN_ALG_SHA384 = 5;
  
  public static final int MQMLP_SIGN_ALG_SHA512 = 6;
  
  public static final int MQPO_YES = 1;
  
  public static final int MQPO_NO = 0;
  
  public static final int MQPSCT_NONE = -1;
  
  public static final int MQPSST_ALL = 0;
  
  public static final int MQPSST_LOCAL = 1;
  
  public static final int MQPSST_PARENT = 2;
  
  public static final int MQPSST_CHILD = 3;
  
  public static final int MQPS_STATUS_INACTIVE = 0;
  
  public static final int MQPS_STATUS_STARTING = 1;
  
  public static final int MQPS_STATUS_STOPPING = 2;
  
  public static final int MQPS_STATUS_ACTIVE = 3;
  
  public static final int MQPS_STATUS_COMPAT = 4;
  
  public static final int MQPS_STATUS_ERROR = 5;
  
  public static final int MQPS_STATUS_REFUSED = 6;
  
  public static final int MQQMDT_EXPLICIT_CLUSTER_SENDER = 1;
  
  public static final int MQQMDT_AUTO_CLUSTER_SENDER = 2;
  
  public static final int MQQMDT_AUTO_EXP_CLUSTER_SENDER = 4;
  
  public static final int MQQMDT_CLUSTER_RECEIVER = 3;
  
  public static final int MQQMFAC_IMS_BRIDGE = 1;
  
  public static final int MQQMFAC_DB2 = 2;
  
  public static final int MQQMSTA_STARTING = 1;
  
  public static final int MQQMSTA_RUNNING = 2;
  
  public static final int MQQMSTA_QUIESCING = 3;
  
  public static final int MQQMSTA_STANDBY = 4;
  
  public static final int MQQMT_NORMAL = 0;
  
  public static final int MQQMT_REPOSITORY = 1;
  
  public static final int MQQO_YES = 1;
  
  public static final int MQQO_NO = 0;
  
  public static final int MQQSIE_NONE = 0;
  
  public static final int MQQSIE_HIGH = 1;
  
  public static final int MQQSIE_OK = 2;
  
  public static final int MQQSOT_ALL = 1;
  
  public static final int MQQSOT_INPUT = 2;
  
  public static final int MQQSOT_OUTPUT = 3;
  
  public static final int MQQSGS_UNKNOWN = 0;
  
  public static final int MQQSGS_CREATED = 1;
  
  public static final int MQQSGS_ACTIVE = 2;
  
  public static final int MQQSGS_INACTIVE = 3;
  
  public static final int MQQSGS_FAILED = 4;
  
  public static final int MQQSGS_PENDING = 5;
  
  public static final int MQQSO_NO = 0;
  
  public static final int MQQSO_YES = 1;
  
  public static final int MQQSO_SHARED = 1;
  
  public static final int MQQSO_EXCLUSIVE = 2;
  
  public static final int MQQSUM_YES = 1;
  
  public static final int MQQSUM_NO = 0;
  
  public static final int MQRAR_YES = 1;
  
  public static final int MQRAR_NO = 0;
  
  public static final int MQRP_YES = 1;
  
  public static final int MQRP_NO = 0;
  
  public static final int MQRQ_CONN_NOT_AUTHORIZED = 1;
  
  public static final int MQRQ_OPEN_NOT_AUTHORIZED = 2;
  
  public static final int MQRQ_CLOSE_NOT_AUTHORIZED = 3;
  
  public static final int MQRQ_CMD_NOT_AUTHORIZED = 4;
  
  public static final int MQRQ_Q_MGR_STOPPING = 5;
  
  public static final int MQRQ_Q_MGR_QUIESCING = 6;
  
  public static final int MQRQ_CHANNEL_STOPPED_OK = 7;
  
  public static final int MQRQ_CHANNEL_STOPPED_ERROR = 8;
  
  public static final int MQRQ_CHANNEL_STOPPED_RETRY = 9;
  
  public static final int MQRQ_CHANNEL_STOPPED_DISABLED = 10;
  
  public static final int MQRQ_BRIDGE_STOPPED_OK = 11;
  
  public static final int MQRQ_BRIDGE_STOPPED_ERROR = 12;
  
  public static final int MQRQ_SSL_HANDSHAKE_ERROR = 13;
  
  public static final int MQRQ_SSL_CIPHER_SPEC_ERROR = 14;
  
  public static final int MQRQ_SSL_CLIENT_AUTH_ERROR = 15;
  
  public static final int MQRQ_SSL_PEER_NAME_ERROR = 16;
  
  public static final int MQRQ_SUB_NOT_AUTHORIZED = 17;
  
  public static final int MQRQ_SUB_DEST_NOT_AUTHORIZED = 18;
  
  public static final int MQRQ_SSL_UNKNOWN_REVOCATION = 19;
  
  public static final int MQRQ_SYS_CONN_NOT_AUTHORIZED = 20;
  
  public static final int MQRQ_CHANNEL_BLOCKED_ADDRESS = 21;
  
  public static final int MQRQ_CHANNEL_BLOCKED_USERID = 22;
  
  public static final int MQRQ_CHANNEL_BLOCKED_NOACCESS = 23;
  
  public static final int MQRQ_MAX_ACTIVE_CHANNELS = 24;
  
  public static final int MQRQ_MAX_CHANNELS = 25;
  
  public static final int MQRQ_SVRCONN_INST_LIMIT = 26;
  
  public static final int MQRQ_CLIENT_INST_LIMIT = 27;
  
  public static final int MQRQ_CAF_NOT_INSTALLED = 28;
  
  public static final int MQRQ_CSP_NOT_AUTHORIZED = 29;
  
  public static final int MQRQ_FAILOVER_PERMITTED = 30;
  
  public static final int MQRQ_FAILOVER_NOT_PERMITTED = 31;
  
  public static final int MQRQ_STANDBY_ACTIVATED = 32;
  
  public static final int MQRQ_REPLICA_ACTIVATED = 33;
  
  public static final int MQRT_CONFIGURATION = 1;
  
  public static final int MQRT_EXPIRY = 2;
  
  public static final int MQRT_NSPROC = 3;
  
  public static final int MQRT_PROXYSUB = 4;
  
  public static final int MQRT_SUB_CONFIGURATION = 5;
  
  public static final int MQSCO_Q_MGR = 1;
  
  public static final int MQSCO_CELL = 2;
  
  public static final int MQSECITEM_ALL = 0;
  
  public static final int MQSECITEM_MQADMIN = 1;
  
  public static final int MQSECITEM_MQNLIST = 2;
  
  public static final int MQSECITEM_MQPROC = 3;
  
  public static final int MQSECITEM_MQQUEUE = 4;
  
  public static final int MQSECITEM_MQCONN = 5;
  
  public static final int MQSECITEM_MQCMDS = 6;
  
  public static final int MQSECITEM_MXADMIN = 7;
  
  public static final int MQSECITEM_MXNLIST = 8;
  
  public static final int MQSECITEM_MXPROC = 9;
  
  public static final int MQSECITEM_MXQUEUE = 10;
  
  public static final int MQSECITEM_MXTOPIC = 11;
  
  public static final int MQSECSW_PROCESS = 1;
  
  public static final int MQSECSW_NAMELIST = 2;
  
  public static final int MQSECSW_Q = 3;
  
  public static final int MQSECSW_TOPIC = 4;
  
  public static final int MQSECSW_CONTEXT = 6;
  
  public static final int MQSECSW_ALTERNATE_USER = 7;
  
  public static final int MQSECSW_COMMAND = 8;
  
  public static final int MQSECSW_CONNECTION = 9;
  
  public static final int MQSECSW_SUBSYSTEM = 10;
  
  public static final int MQSECSW_COMMAND_RESOURCES = 11;
  
  public static final int MQSECSW_Q_MGR = 15;
  
  public static final int MQSECSW_QSG = 16;
  
  public static final int MQSECSW_OFF_FOUND = 21;
  
  public static final int MQSECSW_ON_FOUND = 22;
  
  public static final int MQSECSW_OFF_NOT_FOUND = 23;
  
  public static final int MQSECSW_ON_NOT_FOUND = 24;
  
  public static final int MQSECSW_OFF_ERROR = 25;
  
  public static final int MQSECSW_ON_OVERRIDDEN = 26;
  
  public static final int MQSECTYPE_AUTHSERV = 1;
  
  public static final int MQSECTYPE_SSL = 2;
  
  public static final int MQSECTYPE_CLASSES = 3;
  
  public static final int MQSECTYPE_CONNAUTH = 4;
  
  public static final int MQCHK_OPTIONAL = 0;
  
  public static final int MQCHK_NONE = 1;
  
  public static final int MQCHK_REQUIRED_ADMIN = 2;
  
  public static final int MQCHK_REQUIRED = 3;
  
  public static final int MQCHK_AS_Q_MGR = 4;
  
  public static final int MQADPCTX_NO = 0;
  
  public static final int MQADPCTX_YES = 1;
  
  public static final int MQSECCOMM_NO = 0;
  
  public static final int MQSECCOMM_YES = 1;
  
  public static final int MQSECCOMM_ANON = 2;
  
  public static final int MQLDAP_AUTHORMD_OS = 0;
  
  public static final int MQLDAP_AUTHORMD_SEARCHGRP = 1;
  
  public static final int MQLDAP_AUTHORMD_SEARCHUSR = 2;
  
  public static final int MQLDAP_AUTHORMD_SRCHGRPSN = 3;
  
  public static final int MQLDAP_NESTGRP_NO = 0;
  
  public static final int MQLDAP_NESTGRP_YES = 1;
  
  public static final int MQAUTHENTICATE_OS = 0;
  
  public static final int MQAUTHENTICATE_PAM = 1;
  
  public static final int MQLDAPC_INACTIVE = 0;
  
  public static final int MQLDAPC_CONNECTED = 1;
  
  public static final int MQLDAPC_ERROR = 2;
  
  public static final int MQSELTYPE_NONE = 0;
  
  public static final int MQSELTYPE_STANDARD = 1;
  
  public static final int MQSELTYPE_EXTENDED = 2;
  
  public static final int MQCHLA_DISABLED = 0;
  
  public static final int MQCHLA_ENABLED = 1;
  
  public static final int MQRDNS_ENABLED = 0;
  
  public static final int MQRDNS_DISABLED = 1;
  
  public static final int MQCLROUTE_DIRECT = 0;
  
  public static final int MQCLROUTE_TOPIC_HOST = 1;
  
  public static final int MQCLROUTE_NONE = 2;
  
  public static final int MQCLST_ACTIVE = 0;
  
  public static final int MQCLST_PENDING = 1;
  
  public static final int MQCLST_INVALID = 2;
  
  public static final int MQCLST_ERROR = 3;
  
  public static final int MQCLXQ_SCTQ = 0;
  
  public static final int MQCLXQ_CHANNEL = 1;
  
  public static final int MQSUS_YES = 1;
  
  public static final int MQSUS_NO = 0;
  
  public static final int MQSYNCPOINT_YES = 0;
  
  public static final int MQSYNCPOINT_IFPER = 1;
  
  public static final int MQSYSP_NO = 0;
  
  public static final int MQSYSP_YES = 1;
  
  public static final int MQSYSP_EXTENDED = 2;
  
  public static final int MQSYSP_TYPE_INITIAL = 10;
  
  public static final int MQSYSP_TYPE_SET = 11;
  
  public static final int MQSYSP_TYPE_LOG_COPY = 12;
  
  public static final int MQSYSP_TYPE_LOG_STATUS = 13;
  
  public static final int MQSYSP_TYPE_ARCHIVE_TAPE = 14;
  
  public static final int MQSYSP_ALLOC_BLK = 20;
  
  public static final int MQSYSP_ALLOC_TRK = 21;
  
  public static final int MQSYSP_ALLOC_CYL = 22;
  
  public static final int MQSYSP_STATUS_BUSY = 30;
  
  public static final int MQSYSP_STATUS_PREMOUNT = 31;
  
  public static final int MQSYSP_STATUS_AVAILABLE = 32;
  
  public static final int MQSYSP_STATUS_UNKNOWN = 33;
  
  public static final int MQSYSP_STATUS_ALLOC_ARCHIVE = 34;
  
  public static final int MQSYSP_STATUS_COPYING_BSDS = 35;
  
  public static final int MQSYSP_STATUS_COPYING_LOG = 36;
  
  public static final int MQEXT_ALL = 0;
  
  public static final int MQEXT_OBJECT = 1;
  
  public static final int MQEXT_AUTHORITY = 2;
  
  public static final int MQEXTATTRS_ALL = 0;
  
  public static final int MQEXTATTRS_NONDEF = 1;
  
  public static final int MQSYSOBJ_YES = 0;
  
  public static final int MQSYSOBJ_NO = 1;
  
  public static final int MQSUBTYPE_API = 1;
  
  public static final int MQSUBTYPE_ADMIN = 2;
  
  public static final int MQSUBTYPE_PROXY = 3;
  
  public static final int MQSUBTYPE_ALL = -1;
  
  public static final int MQSUBTYPE_USER = -2;
  
  public static final int MQDOPT_RESOLVED = 0;
  
  public static final int MQDOPT_DEFINED = 1;
  
  public static final int MQTIME_UNIT_MINS = 0;
  
  public static final int MQTIME_UNIT_SECS = 1;
  
  public static final int MQUIDSUPP_NO = 0;
  
  public static final int MQUIDSUPP_YES = 1;
  
  public static final int MQUNDELIVERED_NORMAL = 0;
  
  public static final int MQUNDELIVERED_SAFE = 1;
  
  public static final int MQUNDELIVERED_DISCARD = 2;
  
  public static final int MQUNDELIVERED_KEEP = 3;
  
  public static final int MQUOWST_NONE = 0;
  
  public static final int MQUOWST_ACTIVE = 1;
  
  public static final int MQUOWST_PREPARED = 2;
  
  public static final int MQUOWST_UNRESOLVED = 3;
  
  public static final int MQUOWT_Q_MGR = 0;
  
  public static final int MQUOWT_CICS = 1;
  
  public static final int MQUOWT_RRS = 2;
  
  public static final int MQUOWT_IMS = 3;
  
  public static final int MQUOWT_XA = 4;
  
  public static final int MQUSAGE_PS_AVAILABLE = 0;
  
  public static final int MQUSAGE_PS_DEFINED = 1;
  
  public static final int MQUSAGE_PS_OFFLINE = 2;
  
  public static final int MQUSAGE_PS_NOT_DEFINED = 3;
  
  public static final int MQUSAGE_PS_SUSPENDED = 4;
  
  public static final int MQUSAGE_EXPAND_USER = 1;
  
  public static final int MQUSAGE_EXPAND_SYSTEM = 2;
  
  public static final int MQUSAGE_EXPAND_NONE = 3;
  
  public static final int MQUSAGE_DS_OLDEST_ACTIVE_UOW = 10;
  
  public static final int MQUSAGE_DS_OLDEST_PS_RECOVERY = 11;
  
  public static final int MQUSAGE_DS_OLDEST_CF_RECOVERY = 12;
  
  public static final int MQMCP_REPLY = 2;
  
  public static final int MQMCP_USER = 1;
  
  public static final int MQMCP_NONE = 0;
  
  public static final int MQMCP_ALL = -1;
  
  public static final int MQMCP_COMPAT = -2;
  
  public static final int MQNSH_NONE = 0;
  
  public static final int MQNSH_ALL = -1;
  
  public static final int MQLR_ONE = 1;
  
  public static final int MQLR_AUTO = -1;
  
  public static final int MQLR_MAX = -2;
  
  public static final int MQAUTOCLUS_TYPE_NONE = 0;
  
  public static final int MQAUTOCLUS_TYPE_UNIFORM = 1;
  
  public static final int MQFS_SHARED = -1;
  
  public static final int MQFSENC_NO = 0;
  
  public static final int MQFSENC_YES = 1;
  
  public static final int MQFSENC_UNKNOWN = 2;
  
  public static final int MQLOGTYPE_CIRCULAR = 0;
  
  public static final int MQLOGTYPE_LINEAR = 1;
  
  public static final int MQLOGTYPE_REPLICATED = 2;
  
  public static final int MQNHACONNACTV_NO = 0;
  
  public static final int MQNHACONNACTV_YES = 1;
  
  public static final int MQNHABACKLOG_UNKNOWN = -1;
  
  public static final int MQNHAROLE_UNKNOWN = 0;
  
  public static final int MQNHAROLE_ACTIVE = 1;
  
  public static final int MQNHAROLE_REPLICA = 2;
  
  public static final int MQNHAINSYNC_NO = 0;
  
  public static final int MQNHAINSYNC_YES = 1;
  
  public static final int MQOPER_SYSTEM_FIRST = 0;
  
  public static final int MQOPER_UNKNOWN = 0;
  
  public static final int MQOPER_BROWSE = 1;
  
  public static final int MQOPER_DISCARD = 2;
  
  public static final int MQOPER_GET = 3;
  
  public static final int MQOPER_PUT = 4;
  
  public static final int MQOPER_PUT_REPLY = 5;
  
  public static final int MQOPER_PUT_REPORT = 6;
  
  public static final int MQOPER_RECEIVE = 7;
  
  public static final int MQOPER_SEND = 8;
  
  public static final int MQOPER_TRANSFORM = 9;
  
  public static final int MQOPER_PUBLISH = 10;
  
  public static final int MQOPER_EXCLUDED_PUBLISH = 11;
  
  public static final int MQOPER_DISCARDED_PUBLISH = 12;
  
  public static final int MQOPER_SYSTEM_LAST = 65535;
  
  public static final int MQOPER_APPL_FIRST = 65536;
  
  public static final int MQOPER_APPL_LAST = 999999999;
  
  public static final int MQROUTE_UNLIMITED_ACTIVITIES = 0;
  
  public static final int MQROUTE_DETAIL_LOW = 2;
  
  public static final int MQROUTE_DETAIL_MEDIUM = 8;
  
  public static final int MQROUTE_DETAIL_HIGH = 32;
  
  public static final int MQROUTE_FORWARD_ALL = 256;
  
  public static final int MQROUTE_FORWARD_IF_SUPPORTED = 512;
  
  public static final int MQROUTE_FORWARD_REJ_UNSUP_MASK = -65536;
  
  public static final int MQROUTE_DELIVER_YES = 4096;
  
  public static final int MQROUTE_DELIVER_NO = 8192;
  
  public static final int MQROUTE_DELIVER_REJ_UNSUP_MASK = -65536;
  
  public static final int MQROUTE_ACCUMULATE_NONE = 65539;
  
  public static final int MQROUTE_ACCUMULATE_IN_MSG = 65540;
  
  public static final int MQROUTE_ACCUMULATE_AND_REPLY = 65541;
  
  public static final int MQDELO_NONE = 0;
  
  public static final int MQDELO_LOCAL = 4;
  
  public static final int MQPUBO_NONE = 0;
  
  public static final int MQPUBO_CORREL_ID_AS_IDENTITY = 1;
  
  public static final int MQPUBO_RETAIN_PUBLICATION = 2;
  
  public static final int MQPUBO_OTHER_SUBSCRIBERS_ONLY = 4;
  
  public static final int MQPUBO_NO_REGISTRATION = 8;
  
  public static final int MQPUBO_IS_RETAINED_PUBLICATION = 16;
  
  public static final int MQREGO_NONE = 0;
  
  public static final int MQREGO_CORREL_ID_AS_IDENTITY = 1;
  
  public static final int MQREGO_ANONYMOUS = 2;
  
  public static final int MQREGO_LOCAL = 4;
  
  public static final int MQREGO_DIRECT_REQUESTS = 8;
  
  public static final int MQREGO_NEW_PUBLICATIONS_ONLY = 16;
  
  public static final int MQREGO_PUBLISH_ON_REQUEST_ONLY = 32;
  
  public static final int MQREGO_DEREGISTER_ALL = 64;
  
  public static final int MQREGO_INCLUDE_STREAM_NAME = 128;
  
  public static final int MQREGO_INFORM_IF_RETAINED = 256;
  
  public static final int MQREGO_DUPLICATES_OK = 512;
  
  public static final int MQREGO_NON_PERSISTENT = 1024;
  
  public static final int MQREGO_PERSISTENT = 2048;
  
  public static final int MQREGO_PERSISTENT_AS_PUBLISH = 4096;
  
  public static final int MQREGO_PERSISTENT_AS_Q = 8192;
  
  public static final int MQREGO_ADD_NAME = 16384;
  
  public static final int MQREGO_NO_ALTERATION = 32768;
  
  public static final int MQREGO_FULL_RESPONSE = 65536;
  
  public static final int MQREGO_JOIN_SHARED = 131072;
  
  public static final int MQREGO_JOIN_EXCLUSIVE = 262144;
  
  public static final int MQREGO_LEAVE_ONLY = 524288;
  
  public static final int MQREGO_VARIABLE_USER_ID = 1048576;
  
  public static final int MQREGO_LOCKED = 2097152;
  
  public static final int MQUA_FIRST = 65536;
  
  public static final int MQUA_LAST = 999999999;
  
  public static final int MQGUR_DISABLED = 0;
  
  public static final int MQGUR_ENABLED = 1;
  
  public static final int MQMULC_STANDARD = 0;
  
  public static final int MQMULC_REFINED = 1;
  
  public static final int MQSTDBY_NOT_PERMITTED = 0;
  
  public static final int MQSTDBY_PERMITTED = 1;
  
  public static final int MQCFH_STRUC_LENGTH = 36;
  
  public static final int MQCFH_VERSION_1 = 1;
  
  public static final int MQCFH_VERSION_2 = 2;
  
  public static final int MQCFH_VERSION_3 = 3;
  
  public static final int MQCFH_CURRENT_VERSION = 3;
  
  public static final int MQCMD_NONE = 0;
  
  public static final int MQCMD_CHANGE_Q_MGR = 1;
  
  public static final int MQCMD_INQUIRE_Q_MGR = 2;
  
  public static final int MQCMD_CHANGE_PROCESS = 3;
  
  public static final int MQCMD_COPY_PROCESS = 4;
  
  public static final int MQCMD_CREATE_PROCESS = 5;
  
  public static final int MQCMD_DELETE_PROCESS = 6;
  
  public static final int MQCMD_INQUIRE_PROCESS = 7;
  
  public static final int MQCMD_CHANGE_Q = 8;
  
  public static final int MQCMD_CLEAR_Q = 9;
  
  public static final int MQCMD_COPY_Q = 10;
  
  public static final int MQCMD_CREATE_Q = 11;
  
  public static final int MQCMD_DELETE_Q = 12;
  
  public static final int MQCMD_INQUIRE_Q = 13;
  
  public static final int MQCMD_REFRESH_Q_MGR = 16;
  
  public static final int MQCMD_RESET_Q_STATS = 17;
  
  public static final int MQCMD_INQUIRE_Q_NAMES = 18;
  
  public static final int MQCMD_INQUIRE_PROCESS_NAMES = 19;
  
  public static final int MQCMD_INQUIRE_CHANNEL_NAMES = 20;
  
  public static final int MQCMD_CHANGE_CHANNEL = 21;
  
  public static final int MQCMD_COPY_CHANNEL = 22;
  
  public static final int MQCMD_CREATE_CHANNEL = 23;
  
  public static final int MQCMD_DELETE_CHANNEL = 24;
  
  public static final int MQCMD_INQUIRE_CHANNEL = 25;
  
  public static final int MQCMD_PING_CHANNEL = 26;
  
  public static final int MQCMD_RESET_CHANNEL = 27;
  
  public static final int MQCMD_START_CHANNEL = 28;
  
  public static final int MQCMD_STOP_CHANNEL = 29;
  
  public static final int MQCMD_START_CHANNEL_INIT = 30;
  
  public static final int MQCMD_START_CHANNEL_LISTENER = 31;
  
  public static final int MQCMD_CHANGE_NAMELIST = 32;
  
  public static final int MQCMD_COPY_NAMELIST = 33;
  
  public static final int MQCMD_CREATE_NAMELIST = 34;
  
  public static final int MQCMD_DELETE_NAMELIST = 35;
  
  public static final int MQCMD_INQUIRE_NAMELIST = 36;
  
  public static final int MQCMD_INQUIRE_NAMELIST_NAMES = 37;
  
  public static final int MQCMD_ESCAPE = 38;
  
  public static final int MQCMD_RESOLVE_CHANNEL = 39;
  
  public static final int MQCMD_PING_Q_MGR = 40;
  
  public static final int MQCMD_INQUIRE_Q_STATUS = 41;
  
  public static final int MQCMD_INQUIRE_CHANNEL_STATUS = 42;
  
  public static final int MQCMD_CONFIG_EVENT = 43;
  
  public static final int MQCMD_Q_MGR_EVENT = 44;
  
  public static final int MQCMD_PERFM_EVENT = 45;
  
  public static final int MQCMD_CHANNEL_EVENT = 46;
  
  public static final int MQCMD_DELETE_PUBLICATION = 60;
  
  public static final int MQCMD_DEREGISTER_PUBLISHER = 61;
  
  public static final int MQCMD_DEREGISTER_SUBSCRIBER = 62;
  
  public static final int MQCMD_PUBLISH = 63;
  
  public static final int MQCMD_REGISTER_PUBLISHER = 64;
  
  public static final int MQCMD_REGISTER_SUBSCRIBER = 65;
  
  public static final int MQCMD_REQUEST_UPDATE = 66;
  
  public static final int MQCMD_BROKER_INTERNAL = 67;
  
  public static final int MQCMD_ACTIVITY_MSG = 69;
  
  public static final int MQCMD_INQUIRE_CLUSTER_Q_MGR = 70;
  
  public static final int MQCMD_RESUME_Q_MGR_CLUSTER = 71;
  
  public static final int MQCMD_SUSPEND_Q_MGR_CLUSTER = 72;
  
  public static final int MQCMD_REFRESH_CLUSTER = 73;
  
  public static final int MQCMD_RESET_CLUSTER = 74;
  
  public static final int MQCMD_TRACE_ROUTE = 75;
  
  public static final int MQCMD_REFRESH_SECURITY = 78;
  
  public static final int MQCMD_CHANGE_AUTH_INFO = 79;
  
  public static final int MQCMD_COPY_AUTH_INFO = 80;
  
  public static final int MQCMD_CREATE_AUTH_INFO = 81;
  
  public static final int MQCMD_DELETE_AUTH_INFO = 82;
  
  public static final int MQCMD_INQUIRE_AUTH_INFO = 83;
  
  public static final int MQCMD_INQUIRE_AUTH_INFO_NAMES = 84;
  
  public static final int MQCMD_INQUIRE_CONNECTION = 85;
  
  public static final int MQCMD_STOP_CONNECTION = 86;
  
  public static final int MQCMD_INQUIRE_AUTH_RECS = 87;
  
  public static final int MQCMD_INQUIRE_ENTITY_AUTH = 88;
  
  public static final int MQCMD_DELETE_AUTH_REC = 89;
  
  public static final int MQCMD_SET_AUTH_REC = 90;
  
  public static final int MQCMD_LOGGER_EVENT = 91;
  
  public static final int MQCMD_RESET_Q_MGR = 92;
  
  public static final int MQCMD_CHANGE_LISTENER = 93;
  
  public static final int MQCMD_COPY_LISTENER = 94;
  
  public static final int MQCMD_CREATE_LISTENER = 95;
  
  public static final int MQCMD_DELETE_LISTENER = 96;
  
  public static final int MQCMD_INQUIRE_LISTENER = 97;
  
  public static final int MQCMD_INQUIRE_LISTENER_STATUS = 98;
  
  public static final int MQCMD_COMMAND_EVENT = 99;
  
  public static final int MQCMD_CHANGE_SECURITY = 100;
  
  public static final int MQCMD_CHANGE_CF_STRUC = 101;
  
  public static final int MQCMD_CHANGE_STG_CLASS = 102;
  
  public static final int MQCMD_CHANGE_TRACE = 103;
  
  public static final int MQCMD_ARCHIVE_LOG = 104;
  
  public static final int MQCMD_BACKUP_CF_STRUC = 105;
  
  public static final int MQCMD_CREATE_BUFFER_POOL = 106;
  
  public static final int MQCMD_CREATE_PAGE_SET = 107;
  
  public static final int MQCMD_CREATE_CF_STRUC = 108;
  
  public static final int MQCMD_CREATE_STG_CLASS = 109;
  
  public static final int MQCMD_COPY_CF_STRUC = 110;
  
  public static final int MQCMD_COPY_STG_CLASS = 111;
  
  public static final int MQCMD_DELETE_CF_STRUC = 112;
  
  public static final int MQCMD_DELETE_STG_CLASS = 113;
  
  public static final int MQCMD_INQUIRE_ARCHIVE = 114;
  
  public static final int MQCMD_INQUIRE_CF_STRUC = 115;
  
  public static final int MQCMD_INQUIRE_CF_STRUC_STATUS = 116;
  
  public static final int MQCMD_INQUIRE_CMD_SERVER = 117;
  
  public static final int MQCMD_INQUIRE_CHANNEL_INIT = 118;
  
  public static final int MQCMD_INQUIRE_QSG = 119;
  
  public static final int MQCMD_INQUIRE_LOG = 120;
  
  public static final int MQCMD_INQUIRE_SECURITY = 121;
  
  public static final int MQCMD_INQUIRE_STG_CLASS = 122;
  
  public static final int MQCMD_INQUIRE_SYSTEM = 123;
  
  public static final int MQCMD_INQUIRE_THREAD = 124;
  
  public static final int MQCMD_INQUIRE_TRACE = 125;
  
  public static final int MQCMD_INQUIRE_USAGE = 126;
  
  public static final int MQCMD_MOVE_Q = 127;
  
  public static final int MQCMD_RECOVER_BSDS = 128;
  
  public static final int MQCMD_RECOVER_CF_STRUC = 129;
  
  public static final int MQCMD_RESET_TPIPE = 130;
  
  public static final int MQCMD_RESOLVE_INDOUBT = 131;
  
  public static final int MQCMD_RESUME_Q_MGR = 132;
  
  public static final int MQCMD_REVERIFY_SECURITY = 133;
  
  public static final int MQCMD_SET_ARCHIVE = 134;
  
  public static final int MQCMD_SET_LOG = 136;
  
  public static final int MQCMD_SET_SYSTEM = 137;
  
  public static final int MQCMD_START_CMD_SERVER = 138;
  
  public static final int MQCMD_START_Q_MGR = 139;
  
  public static final int MQCMD_START_TRACE = 140;
  
  public static final int MQCMD_STOP_CHANNEL_INIT = 141;
  
  public static final int MQCMD_STOP_CHANNEL_LISTENER = 142;
  
  public static final int MQCMD_STOP_CMD_SERVER = 143;
  
  public static final int MQCMD_STOP_Q_MGR = 144;
  
  public static final int MQCMD_STOP_TRACE = 145;
  
  public static final int MQCMD_SUSPEND_Q_MGR = 146;
  
  public static final int MQCMD_INQUIRE_CF_STRUC_NAMES = 147;
  
  public static final int MQCMD_INQUIRE_STG_CLASS_NAMES = 148;
  
  public static final int MQCMD_CHANGE_SERVICE = 149;
  
  public static final int MQCMD_COPY_SERVICE = 150;
  
  public static final int MQCMD_CREATE_SERVICE = 151;
  
  public static final int MQCMD_DELETE_SERVICE = 152;
  
  public static final int MQCMD_INQUIRE_SERVICE = 153;
  
  public static final int MQCMD_INQUIRE_SERVICE_STATUS = 154;
  
  public static final int MQCMD_START_SERVICE = 155;
  
  public static final int MQCMD_STOP_SERVICE = 156;
  
  public static final int MQCMD_DELETE_BUFFER_POOL = 157;
  
  public static final int MQCMD_DELETE_PAGE_SET = 158;
  
  public static final int MQCMD_CHANGE_BUFFER_POOL = 159;
  
  public static final int MQCMD_CHANGE_PAGE_SET = 160;
  
  public static final int MQCMD_INQUIRE_Q_MGR_STATUS = 161;
  
  public static final int MQCMD_CREATE_LOG = 162;
  
  public static final int MQCMD_STATISTICS_MQI = 164;
  
  public static final int MQCMD_STATISTICS_Q = 165;
  
  public static final int MQCMD_STATISTICS_CHANNEL = 166;
  
  public static final int MQCMD_ACCOUNTING_MQI = 167;
  
  public static final int MQCMD_ACCOUNTING_Q = 168;
  
  public static final int MQCMD_INQUIRE_AUTH_SERVICE = 169;
  
  public static final int MQCMD_CHANGE_TOPIC = 170;
  
  public static final int MQCMD_COPY_TOPIC = 171;
  
  public static final int MQCMD_CREATE_TOPIC = 172;
  
  public static final int MQCMD_DELETE_TOPIC = 173;
  
  public static final int MQCMD_INQUIRE_TOPIC = 174;
  
  public static final int MQCMD_INQUIRE_TOPIC_NAMES = 175;
  
  public static final int MQCMD_INQUIRE_SUBSCRIPTION = 176;
  
  public static final int MQCMD_CREATE_SUBSCRIPTION = 177;
  
  public static final int MQCMD_CHANGE_SUBSCRIPTION = 178;
  
  public static final int MQCMD_DELETE_SUBSCRIPTION = 179;
  
  public static final int MQCMD_COPY_SUBSCRIPTION = 181;
  
  public static final int MQCMD_INQUIRE_SUB_STATUS = 182;
  
  public static final int MQCMD_INQUIRE_TOPIC_STATUS = 183;
  
  public static final int MQCMD_CLEAR_TOPIC_STRING = 184;
  
  public static final int MQCMD_INQUIRE_PUBSUB_STATUS = 185;
  
  public static final int MQCMD_INQUIRE_SMDS = 186;
  
  public static final int MQCMD_CHANGE_SMDS = 187;
  
  public static final int MQCMD_RESET_SMDS = 188;
  
  public static final int MQCMD_CREATE_COMM_INFO = 190;
  
  public static final int MQCMD_INQUIRE_COMM_INFO = 191;
  
  public static final int MQCMD_CHANGE_COMM_INFO = 192;
  
  public static final int MQCMD_COPY_COMM_INFO = 193;
  
  public static final int MQCMD_DELETE_COMM_INFO = 194;
  
  public static final int MQCMD_PURGE_CHANNEL = 195;
  
  public static final int MQCMD_MQXR_DIAGNOSTICS = 196;
  
  public static final int MQCMD_START_SMDSCONN = 197;
  
  public static final int MQCMD_STOP_SMDSCONN = 198;
  
  public static final int MQCMD_INQUIRE_SMDSCONN = 199;
  
  public static final int MQCMD_INQUIRE_MQXR_STATUS = 200;
  
  public static final int MQCMD_START_CLIENT_TRACE = 201;
  
  public static final int MQCMD_STOP_CLIENT_TRACE = 202;
  
  public static final int MQCMD_SET_CHLAUTH_REC = 203;
  
  public static final int MQCMD_INQUIRE_CHLAUTH_RECS = 204;
  
  public static final int MQCMD_INQUIRE_PROT_POLICY = 205;
  
  public static final int MQCMD_CREATE_PROT_POLICY = 206;
  
  public static final int MQCMD_DELETE_PROT_POLICY = 207;
  
  public static final int MQCMD_CHANGE_PROT_POLICY = 208;
  
  public static final int MQCMD_SET_PROT_POLICY = 208;
  
  public static final int MQCMD_ACTIVITY_TRACE = 209;
  
  public static final int MQCMD_RESET_CF_STRUC = 213;
  
  public static final int MQCMD_INQUIRE_XR_CAPABILITY = 214;
  
  public static final int MQCMD_INQUIRE_AMQP_CAPABILITY = 216;
  
  public static final int MQCMD_AMQP_DIAGNOSTICS = 217;
  
  public static final int MQCMD_INTER_Q_MGR_STATUS = 218;
  
  public static final int MQCMD_INTER_Q_MGR_BALANCE = 219;
  
  public static final int MQCMD_INQUIRE_APPL_STATUS = 220;
  
  public static final int MQCFC_LAST = 1;
  
  public static final int MQCFC_NOT_LAST = 0;
  
  public static final int MQRCCF_CFH_TYPE_ERROR = 3001;
  
  public static final int MQRCCF_CFH_LENGTH_ERROR = 3002;
  
  public static final int MQRCCF_CFH_VERSION_ERROR = 3003;
  
  public static final int MQRCCF_CFH_MSG_SEQ_NUMBER_ERR = 3004;
  
  public static final int MQRCCF_CFH_CONTROL_ERROR = 3005;
  
  public static final int MQRCCF_CFH_PARM_COUNT_ERROR = 3006;
  
  public static final int MQRCCF_CFH_COMMAND_ERROR = 3007;
  
  public static final int MQRCCF_COMMAND_FAILED = 3008;
  
  public static final int MQRCCF_CFIN_LENGTH_ERROR = 3009;
  
  public static final int MQRCCF_CFST_LENGTH_ERROR = 3010;
  
  public static final int MQRCCF_CFST_STRING_LENGTH_ERR = 3011;
  
  public static final int MQRCCF_FORCE_VALUE_ERROR = 3012;
  
  public static final int MQRCCF_STRUCTURE_TYPE_ERROR = 3013;
  
  public static final int MQRCCF_CFIN_PARM_ID_ERROR = 3014;
  
  public static final int MQRCCF_CFST_PARM_ID_ERROR = 3015;
  
  public static final int MQRCCF_MSG_LENGTH_ERROR = 3016;
  
  public static final int MQRCCF_CFIN_DUPLICATE_PARM = 3017;
  
  public static final int MQRCCF_CFST_DUPLICATE_PARM = 3018;
  
  public static final int MQRCCF_PARM_COUNT_TOO_SMALL = 3019;
  
  public static final int MQRCCF_PARM_COUNT_TOO_BIG = 3020;
  
  public static final int MQRCCF_Q_ALREADY_IN_CELL = 3021;
  
  public static final int MQRCCF_Q_TYPE_ERROR = 3022;
  
  public static final int MQRCCF_MD_FORMAT_ERROR = 3023;
  
  public static final int MQRCCF_CFSL_LENGTH_ERROR = 3024;
  
  public static final int MQRCCF_REPLACE_VALUE_ERROR = 3025;
  
  public static final int MQRCCF_CFIL_DUPLICATE_VALUE = 3026;
  
  public static final int MQRCCF_CFIL_COUNT_ERROR = 3027;
  
  public static final int MQRCCF_CFIL_LENGTH_ERROR = 3028;
  
  public static final int MQRCCF_QUIESCE_VALUE_ERROR = 3029;
  
  public static final int MQRCCF_MODE_VALUE_ERROR = 3029;
  
  public static final int MQRCCF_MSG_SEQ_NUMBER_ERROR = 3030;
  
  public static final int MQRCCF_PING_DATA_COUNT_ERROR = 3031;
  
  public static final int MQRCCF_PING_DATA_COMPARE_ERROR = 3032;
  
  public static final int MQRCCF_CFSL_PARM_ID_ERROR = 3033;
  
  public static final int MQRCCF_CHANNEL_TYPE_ERROR = 3034;
  
  public static final int MQRCCF_PARM_SEQUENCE_ERROR = 3035;
  
  public static final int MQRCCF_XMIT_PROTOCOL_TYPE_ERR = 3036;
  
  public static final int MQRCCF_BATCH_SIZE_ERROR = 3037;
  
  public static final int MQRCCF_DISC_INT_ERROR = 3038;
  
  public static final int MQRCCF_SHORT_RETRY_ERROR = 3039;
  
  public static final int MQRCCF_SHORT_TIMER_ERROR = 3040;
  
  public static final int MQRCCF_LONG_RETRY_ERROR = 3041;
  
  public static final int MQRCCF_LONG_TIMER_ERROR = 3042;
  
  public static final int MQRCCF_SEQ_NUMBER_WRAP_ERROR = 3043;
  
  public static final int MQRCCF_MAX_MSG_LENGTH_ERROR = 3044;
  
  public static final int MQRCCF_PUT_AUTH_ERROR = 3045;
  
  public static final int MQRCCF_PURGE_VALUE_ERROR = 3046;
  
  public static final int MQRCCF_CFIL_PARM_ID_ERROR = 3047;
  
  public static final int MQRCCF_MSG_TRUNCATED = 3048;
  
  public static final int MQRCCF_CCSID_ERROR = 3049;
  
  public static final int MQRCCF_ENCODING_ERROR = 3050;
  
  public static final int MQRCCF_QUEUES_VALUE_ERROR = 3051;
  
  public static final int MQRCCF_DATA_CONV_VALUE_ERROR = 3052;
  
  public static final int MQRCCF_INDOUBT_VALUE_ERROR = 3053;
  
  public static final int MQRCCF_ESCAPE_TYPE_ERROR = 3054;
  
  public static final int MQRCCF_REPOS_VALUE_ERROR = 3055;
  
  public static final int MQRCCF_CHANNEL_TABLE_ERROR = 3062;
  
  public static final int MQRCCF_MCA_TYPE_ERROR = 3063;
  
  public static final int MQRCCF_CHL_INST_TYPE_ERROR = 3064;
  
  public static final int MQRCCF_CHL_STATUS_NOT_FOUND = 3065;
  
  public static final int MQRCCF_CFSL_DUPLICATE_PARM = 3066;
  
  public static final int MQRCCF_CFSL_TOTAL_LENGTH_ERROR = 3067;
  
  public static final int MQRCCF_CFSL_COUNT_ERROR = 3068;
  
  public static final int MQRCCF_CFSL_STRING_LENGTH_ERR = 3069;
  
  public static final int MQRCCF_BROKER_DELETED = 3070;
  
  public static final int MQRCCF_STREAM_ERROR = 3071;
  
  public static final int MQRCCF_TOPIC_ERROR = 3072;
  
  public static final int MQRCCF_NOT_REGISTERED = 3073;
  
  public static final int MQRCCF_Q_MGR_NAME_ERROR = 3074;
  
  public static final int MQRCCF_INCORRECT_STREAM = 3075;
  
  public static final int MQRCCF_Q_NAME_ERROR = 3076;
  
  public static final int MQRCCF_NO_RETAINED_MSG = 3077;
  
  public static final int MQRCCF_DUPLICATE_IDENTITY = 3078;
  
  public static final int MQRCCF_INCORRECT_Q = 3079;
  
  public static final int MQRCCF_CORREL_ID_ERROR = 3080;
  
  public static final int MQRCCF_NOT_AUTHORIZED = 3081;
  
  public static final int MQRCCF_UNKNOWN_STREAM = 3082;
  
  public static final int MQRCCF_REG_OPTIONS_ERROR = 3083;
  
  public static final int MQRCCF_PUB_OPTIONS_ERROR = 3084;
  
  public static final int MQRCCF_UNKNOWN_BROKER = 3085;
  
  public static final int MQRCCF_Q_MGR_CCSID_ERROR = 3086;
  
  public static final int MQRCCF_DEL_OPTIONS_ERROR = 3087;
  
  public static final int MQRCCF_CLUSTER_NAME_CONFLICT = 3088;
  
  public static final int MQRCCF_REPOS_NAME_CONFLICT = 3089;
  
  public static final int MQRCCF_CLUSTER_Q_USAGE_ERROR = 3090;
  
  public static final int MQRCCF_ACTION_VALUE_ERROR = 3091;
  
  public static final int MQRCCF_COMMS_LIBRARY_ERROR = 3092;
  
  public static final int MQRCCF_NETBIOS_NAME_ERROR = 3093;
  
  public static final int MQRCCF_BROKER_COMMAND_FAILED = 3094;
  
  public static final int MQRCCF_CFST_CONFLICTING_PARM = 3095;
  
  public static final int MQRCCF_PATH_NOT_VALID = 3096;
  
  public static final int MQRCCF_PARM_SYNTAX_ERROR = 3097;
  
  public static final int MQRCCF_PWD_LENGTH_ERROR = 3098;
  
  public static final int MQRCCF_FILTER_ERROR = 3150;
  
  public static final int MQRCCF_WRONG_USER = 3151;
  
  public static final int MQRCCF_DUPLICATE_SUBSCRIPTION = 3152;
  
  public static final int MQRCCF_SUB_NAME_ERROR = 3153;
  
  public static final int MQRCCF_SUB_IDENTITY_ERROR = 3154;
  
  public static final int MQRCCF_SUBSCRIPTION_IN_USE = 3155;
  
  public static final int MQRCCF_SUBSCRIPTION_LOCKED = 3156;
  
  public static final int MQRCCF_ALREADY_JOINED = 3157;
  
  public static final int MQRCCF_OBJECT_IN_USE = 3160;
  
  public static final int MQRCCF_UNKNOWN_FILE_NAME = 3161;
  
  public static final int MQRCCF_FILE_NOT_AVAILABLE = 3162;
  
  public static final int MQRCCF_DISC_RETRY_ERROR = 3163;
  
  public static final int MQRCCF_ALLOC_RETRY_ERROR = 3164;
  
  public static final int MQRCCF_ALLOC_SLOW_TIMER_ERROR = 3165;
  
  public static final int MQRCCF_ALLOC_FAST_TIMER_ERROR = 3166;
  
  public static final int MQRCCF_PORT_NUMBER_ERROR = 3167;
  
  public static final int MQRCCF_CHL_SYSTEM_NOT_ACTIVE = 3168;
  
  public static final int MQRCCF_ENTITY_NAME_MISSING = 3169;
  
  public static final int MQRCCF_PROFILE_NAME_ERROR = 3170;
  
  public static final int MQRCCF_AUTH_VALUE_ERROR = 3171;
  
  public static final int MQRCCF_AUTH_VALUE_MISSING = 3172;
  
  public static final int MQRCCF_OBJECT_TYPE_MISSING = 3173;
  
  public static final int MQRCCF_CONNECTION_ID_ERROR = 3174;
  
  public static final int MQRCCF_LOG_TYPE_ERROR = 3175;
  
  public static final int MQRCCF_PROGRAM_NOT_AVAILABLE = 3176;
  
  public static final int MQRCCF_PROGRAM_AUTH_FAILED = 3177;
  
  public static final int MQRCCF_NONE_FOUND = 3200;
  
  public static final int MQRCCF_SECURITY_SWITCH_OFF = 3201;
  
  public static final int MQRCCF_SECURITY_REFRESH_FAILED = 3202;
  
  public static final int MQRCCF_PARM_CONFLICT = 3203;
  
  public static final int MQRCCF_COMMAND_INHIBITED = 3204;
  
  public static final int MQRCCF_OBJECT_BEING_DELETED = 3205;
  
  public static final int MQRCCF_STORAGE_CLASS_IN_USE = 3207;
  
  public static final int MQRCCF_OBJECT_NAME_RESTRICTED = 3208;
  
  public static final int MQRCCF_OBJECT_LIMIT_EXCEEDED = 3209;
  
  public static final int MQRCCF_OBJECT_OPEN_FORCE = 3210;
  
  public static final int MQRCCF_DISPOSITION_CONFLICT = 3211;
  
  public static final int MQRCCF_Q_MGR_NOT_IN_QSG = 3212;
  
  public static final int MQRCCF_ATTR_VALUE_FIXED = 3213;
  
  public static final int MQRCCF_NAMELIST_ERROR = 3215;
  
  public static final int MQRCCF_NO_CHANNEL_INITIATOR = 3217;
  
  public static final int MQRCCF_CHANNEL_INITIATOR_ERROR = 3218;
  
  public static final int MQRCCF_COMMAND_LEVEL_CONFLICT = 3222;
  
  public static final int MQRCCF_Q_ATTR_CONFLICT = 3223;
  
  public static final int MQRCCF_EVENTS_DISABLED = 3224;
  
  public static final int MQRCCF_COMMAND_SCOPE_ERROR = 3225;
  
  public static final int MQRCCF_COMMAND_REPLY_ERROR = 3226;
  
  public static final int MQRCCF_FUNCTION_RESTRICTED = 3227;
  
  public static final int MQRCCF_PARM_MISSING = 3228;
  
  public static final int MQRCCF_PARM_VALUE_ERROR = 3229;
  
  public static final int MQRCCF_COMMAND_LENGTH_ERROR = 3230;
  
  public static final int MQRCCF_COMMAND_ORIGIN_ERROR = 3231;
  
  public static final int MQRCCF_LISTENER_CONFLICT = 3232;
  
  public static final int MQRCCF_LISTENER_STARTED = 3233;
  
  public static final int MQRCCF_LISTENER_STOPPED = 3234;
  
  public static final int MQRCCF_CHANNEL_ERROR = 3235;
  
  public static final int MQRCCF_CF_STRUC_ERROR = 3236;
  
  public static final int MQRCCF_UNKNOWN_USER_ID = 3237;
  
  public static final int MQRCCF_UNEXPECTED_ERROR = 3238;
  
  public static final int MQRCCF_NO_XCF_PARTNER = 3239;
  
  public static final int MQRCCF_CFGR_PARM_ID_ERROR = 3240;
  
  public static final int MQRCCF_CFIF_LENGTH_ERROR = 3241;
  
  public static final int MQRCCF_CFIF_OPERATOR_ERROR = 3242;
  
  public static final int MQRCCF_CFIF_PARM_ID_ERROR = 3243;
  
  public static final int MQRCCF_CFSF_FILTER_VAL_LEN_ERR = 3244;
  
  public static final int MQRCCF_CFSF_LENGTH_ERROR = 3245;
  
  public static final int MQRCCF_CFSF_OPERATOR_ERROR = 3246;
  
  public static final int MQRCCF_CFSF_PARM_ID_ERROR = 3247;
  
  public static final int MQRCCF_TOO_MANY_FILTERS = 3248;
  
  public static final int MQRCCF_LISTENER_RUNNING = 3249;
  
  public static final int MQRCCF_LSTR_STATUS_NOT_FOUND = 3250;
  
  public static final int MQRCCF_SERVICE_RUNNING = 3251;
  
  public static final int MQRCCF_SERV_STATUS_NOT_FOUND = 3252;
  
  public static final int MQRCCF_SERVICE_STOPPED = 3253;
  
  public static final int MQRCCF_CFBS_DUPLICATE_PARM = 3254;
  
  public static final int MQRCCF_CFBS_LENGTH_ERROR = 3255;
  
  public static final int MQRCCF_CFBS_PARM_ID_ERROR = 3256;
  
  public static final int MQRCCF_CFBS_STRING_LENGTH_ERR = 3257;
  
  public static final int MQRCCF_CFGR_LENGTH_ERROR = 3258;
  
  public static final int MQRCCF_CFGR_PARM_COUNT_ERROR = 3259;
  
  public static final int MQRCCF_CONN_NOT_STOPPED = 3260;
  
  public static final int MQRCCF_SERVICE_REQUEST_PENDING = 3261;
  
  public static final int MQRCCF_NO_START_CMD = 3262;
  
  public static final int MQRCCF_NO_STOP_CMD = 3263;
  
  public static final int MQRCCF_CFBF_LENGTH_ERROR = 3264;
  
  public static final int MQRCCF_CFBF_PARM_ID_ERROR = 3265;
  
  public static final int MQRCCF_CFBF_OPERATOR_ERROR = 3266;
  
  public static final int MQRCCF_CFBF_FILTER_VAL_LEN_ERR = 3267;
  
  public static final int MQRCCF_LISTENER_STILL_ACTIVE = 3268;
  
  public static final int MQRCCF_DEF_XMIT_Q_CLUS_ERROR = 3269;
  
  public static final int MQRCCF_TOPICSTR_ALREADY_EXISTS = 3300;
  
  public static final int MQRCCF_SHARING_CONVS_ERROR = 3301;
  
  public static final int MQRCCF_SHARING_CONVS_TYPE = 3302;
  
  public static final int MQRCCF_SECURITY_CASE_CONFLICT = 3303;
  
  public static final int MQRCCF_TOPIC_TYPE_ERROR = 3305;
  
  public static final int MQRCCF_MAX_INSTANCES_ERROR = 3306;
  
  public static final int MQRCCF_MAX_INSTS_PER_CLNT_ERR = 3307;
  
  public static final int MQRCCF_TOPIC_STRING_NOT_FOUND = 3308;
  
  public static final int MQRCCF_SUBSCRIPTION_POINT_ERR = 3309;
  
  public static final int MQRCCF_SUB_ALREADY_EXISTS = 3311;
  
  public static final int MQRCCF_UNKNOWN_OBJECT_NAME = 3312;
  
  public static final int MQRCCF_REMOTE_Q_NAME_ERROR = 3313;
  
  public static final int MQRCCF_DURABILITY_NOT_ALLOWED = 3314;
  
  public static final int MQRCCF_HOBJ_ERROR = 3315;
  
  public static final int MQRCCF_DEST_NAME_ERROR = 3316;
  
  public static final int MQRCCF_INVALID_DESTINATION = 3317;
  
  public static final int MQRCCF_PUBSUB_INHIBITED = 3318;
  
  public static final int MQRCCF_GROUPUR_CHECKS_FAILED = 3319;
  
  public static final int MQRCCF_COMM_INFO_TYPE_ERROR = 3320;
  
  public static final int MQRCCF_USE_CLIENT_ID_ERROR = 3321;
  
  public static final int MQRCCF_CLIENT_ID_NOT_FOUND = 3322;
  
  public static final int MQRCCF_CLIENT_ID_ERROR = 3323;
  
  public static final int MQRCCF_PORT_IN_USE = 3324;
  
  public static final int MQRCCF_SSL_ALT_PROVIDER_REQD = 3325;
  
  public static final int MQRCCF_CHLAUTH_TYPE_ERROR = 3326;
  
  public static final int MQRCCF_CHLAUTH_ACTION_ERROR = 3327;
  
  public static final int MQRCCF_POLICY_NOT_FOUND = 3328;
  
  public static final int MQRCCF_ENCRYPTION_ALG_ERROR = 3329;
  
  public static final int MQRCCF_SIGNATURE_ALG_ERROR = 3330;
  
  public static final int MQRCCF_TOLERATION_POL_ERROR = 3331;
  
  public static final int MQRCCF_POLICY_VERSION_ERROR = 3332;
  
  public static final int MQRCCF_RECIPIENT_DN_MISSING = 3333;
  
  public static final int MQRCCF_POLICY_NAME_MISSING = 3334;
  
  public static final int MQRCCF_CHLAUTH_USERSRC_ERROR = 3335;
  
  public static final int MQRCCF_WRONG_CHLAUTH_TYPE = 3336;
  
  public static final int MQRCCF_CHLAUTH_ALREADY_EXISTS = 3337;
  
  public static final int MQRCCF_CHLAUTH_NOT_FOUND = 3338;
  
  public static final int MQRCCF_WRONG_CHLAUTH_ACTION = 3339;
  
  public static final int MQRCCF_WRONG_CHLAUTH_USERSRC = 3340;
  
  public static final int MQRCCF_CHLAUTH_WARN_ERROR = 3341;
  
  public static final int MQRCCF_WRONG_CHLAUTH_MATCH = 3342;
  
  public static final int MQRCCF_IPADDR_RANGE_CONFLICT = 3343;
  
  public static final int MQRCCF_CHLAUTH_MAX_EXCEEDED = 3344;
  
  public static final int MQRCCF_IPADDR_ERROR = 3345;
  
  public static final int MQRCCF_ADDRESS_ERROR = 3345;
  
  public static final int MQRCCF_IPADDR_RANGE_ERROR = 3346;
  
  public static final int MQRCCF_PROFILE_NAME_MISSING = 3347;
  
  public static final int MQRCCF_CHLAUTH_CLNTUSER_ERROR = 3348;
  
  public static final int MQRCCF_CHLAUTH_NAME_ERROR = 3349;
  
  public static final int MQRCCF_CHLAUTH_RUNCHECK_ERROR = 3350;
  
  public static final int MQRCCF_CF_STRUC_ALREADY_FAILED = 3351;
  
  public static final int MQRCCF_CFCONLOS_CHECKS_FAILED = 3352;
  
  public static final int MQRCCF_SUITE_B_ERROR = 3353;
  
  public static final int MQRCCF_CHANNEL_NOT_STARTED = 3354;
  
  public static final int MQRCCF_CUSTOM_ERROR = 3355;
  
  public static final int MQRCCF_BACKLOG_OUT_OF_RANGE = 3356;
  
  public static final int MQRCCF_CHLAUTH_DISABLED = 3357;
  
  public static final int MQRCCF_SMDS_REQUIRES_DSGROUP = 3358;
  
  public static final int MQRCCF_PSCLUS_DISABLED_TOPDEF = 3359;
  
  public static final int MQRCCF_PSCLUS_TOPIC_EXISTS = 3360;
  
  public static final int MQRCCF_SSL_CIPHER_SUITE_ERROR = 3361;
  
  public static final int MQRCCF_SOCKET_ERROR = 3362;
  
  public static final int MQRCCF_CLUS_XMIT_Q_USAGE_ERROR = 3363;
  
  public static final int MQRCCF_CERT_VAL_POLICY_ERROR = 3364;
  
  public static final int MQRCCF_INVALID_PROTOCOL = 3365;
  
  public static final int MQRCCF_REVDNS_DISABLED = 3366;
  
  public static final int MQRCCF_CLROUTE_NOT_ALTERABLE = 3367;
  
  public static final int MQRCCF_CLUSTER_TOPIC_CONFLICT = 3368;
  
  public static final int MQRCCF_DEFCLXQ_MODEL_Q_ERROR = 3369;
  
  public static final int MQRCCF_CHLAUTH_CHKCLI_ERROR = 3370;
  
  public static final int MQRCCF_CERT_LABEL_NOT_ALLOWED = 3371;
  
  public static final int MQRCCF_Q_MGR_ATTR_CONFLICT = 3372;
  
  public static final int MQRCCF_ENTITY_TYPE_MISSING = 3373;
  
  public static final int MQRCCF_CLWL_EXIT_NAME_ERROR = 3374;
  
  public static final int MQRCCF_SERVICE_NAME_ERROR = 3375;
  
  public static final int MQRCCF_REMOTE_CHL_TYPE_ERROR = 3376;
  
  public static final int MQRCCF_TOPIC_RESTRICTED = 3377;
  
  public static final int MQRCCF_CURRENT_LOG_EXTENT = 3378;
  
  public static final int MQRCCF_LOG_EXTENT_NOT_FOUND = 3379;
  
  public static final int MQRCCF_LOG_NOT_REDUCED = 3380;
  
  public static final int MQRCCF_LOG_EXTENT_ERROR = 3381;
  
  public static final int MQRCCF_ACCESS_BLOCKED = 3382;
  
  public static final int MQRCCF_PS_REQUIRED_MQUC = 3383;
  
  public static final int MQRCCF_STREAMQ_DEST_NOT_SUPP = 3384;
  
  public static final int MQRCCF_STREAMQ_DEST_CONFLICT = 3385;
  
  public static final int MQRCCF_STREAMQ_NOT_SUPPORTED = 3386;
  
  public static final int MQRCCF_STREAMQ_CONFLICT = 3387;
  
  public static final int MQRCCF_OBJECT_ALREADY_EXISTS = 4001;
  
  public static final int MQRCCF_OBJECT_WRONG_TYPE = 4002;
  
  public static final int MQRCCF_LIKE_OBJECT_WRONG_TYPE = 4003;
  
  public static final int MQRCCF_OBJECT_OPEN = 4004;
  
  public static final int MQRCCF_ATTR_VALUE_ERROR = 4005;
  
  public static final int MQRCCF_UNKNOWN_Q_MGR = 4006;
  
  public static final int MQRCCF_Q_WRONG_TYPE = 4007;
  
  public static final int MQRCCF_OBJECT_NAME_ERROR = 4008;
  
  public static final int MQRCCF_ALLOCATE_FAILED = 4009;
  
  public static final int MQRCCF_HOST_NOT_AVAILABLE = 4010;
  
  public static final int MQRCCF_CONFIGURATION_ERROR = 4011;
  
  public static final int MQRCCF_CONNECTION_REFUSED = 4012;
  
  public static final int MQRCCF_ENTRY_ERROR = 4013;
  
  public static final int MQRCCF_SEND_FAILED = 4014;
  
  public static final int MQRCCF_RECEIVED_DATA_ERROR = 4015;
  
  public static final int MQRCCF_RECEIVE_FAILED = 4016;
  
  public static final int MQRCCF_CONNECTION_CLOSED = 4017;
  
  public static final int MQRCCF_NO_STORAGE = 4018;
  
  public static final int MQRCCF_NO_COMMS_MANAGER = 4019;
  
  public static final int MQRCCF_LISTENER_NOT_STARTED = 4020;
  
  public static final int MQRCCF_BIND_FAILED = 4024;
  
  public static final int MQRCCF_CHANNEL_INDOUBT = 4025;
  
  public static final int MQRCCF_MQCONN_FAILED = 4026;
  
  public static final int MQRCCF_MQOPEN_FAILED = 4027;
  
  public static final int MQRCCF_MQGET_FAILED = 4028;
  
  public static final int MQRCCF_MQPUT_FAILED = 4029;
  
  public static final int MQRCCF_PING_ERROR = 4030;
  
  public static final int MQRCCF_CHANNEL_IN_USE = 4031;
  
  public static final int MQRCCF_CHANNEL_NOT_FOUND = 4032;
  
  public static final int MQRCCF_UNKNOWN_REMOTE_CHANNEL = 4033;
  
  public static final int MQRCCF_REMOTE_QM_UNAVAILABLE = 4034;
  
  public static final int MQRCCF_REMOTE_QM_TERMINATING = 4035;
  
  public static final int MQRCCF_MQINQ_FAILED = 4036;
  
  public static final int MQRCCF_NOT_XMIT_Q = 4037;
  
  public static final int MQRCCF_CHANNEL_DISABLED = 4038;
  
  public static final int MQRCCF_USER_EXIT_NOT_AVAILABLE = 4039;
  
  public static final int MQRCCF_COMMIT_FAILED = 4040;
  
  public static final int MQRCCF_WRONG_CHANNEL_TYPE = 4041;
  
  public static final int MQRCCF_CHANNEL_ALREADY_EXISTS = 4042;
  
  public static final int MQRCCF_DATA_TOO_LARGE = 4043;
  
  public static final int MQRCCF_CHANNEL_NAME_ERROR = 4044;
  
  public static final int MQRCCF_XMIT_Q_NAME_ERROR = 4045;
  
  public static final int MQRCCF_MCA_NAME_ERROR = 4047;
  
  public static final int MQRCCF_SEND_EXIT_NAME_ERROR = 4048;
  
  public static final int MQRCCF_SEC_EXIT_NAME_ERROR = 4049;
  
  public static final int MQRCCF_MSG_EXIT_NAME_ERROR = 4050;
  
  public static final int MQRCCF_RCV_EXIT_NAME_ERROR = 4051;
  
  public static final int MQRCCF_XMIT_Q_NAME_WRONG_TYPE = 4052;
  
  public static final int MQRCCF_MCA_NAME_WRONG_TYPE = 4053;
  
  public static final int MQRCCF_DISC_INT_WRONG_TYPE = 4054;
  
  public static final int MQRCCF_SHORT_RETRY_WRONG_TYPE = 4055;
  
  public static final int MQRCCF_SHORT_TIMER_WRONG_TYPE = 4056;
  
  public static final int MQRCCF_LONG_RETRY_WRONG_TYPE = 4057;
  
  public static final int MQRCCF_LONG_TIMER_WRONG_TYPE = 4058;
  
  public static final int MQRCCF_PUT_AUTH_WRONG_TYPE = 4059;
  
  public static final int MQRCCF_KEEP_ALIVE_INT_ERROR = 4060;
  
  public static final int MQRCCF_MISSING_CONN_NAME = 4061;
  
  public static final int MQRCCF_CONN_NAME_ERROR = 4062;
  
  public static final int MQRCCF_MQSET_FAILED = 4063;
  
  public static final int MQRCCF_CHANNEL_NOT_ACTIVE = 4064;
  
  public static final int MQRCCF_TERMINATED_BY_SEC_EXIT = 4065;
  
  public static final int MQRCCF_DYNAMIC_Q_SCOPE_ERROR = 4067;
  
  public static final int MQRCCF_CELL_DIR_NOT_AVAILABLE = 4068;
  
  public static final int MQRCCF_MR_COUNT_ERROR = 4069;
  
  public static final int MQRCCF_MR_COUNT_WRONG_TYPE = 4070;
  
  public static final int MQRCCF_MR_EXIT_NAME_ERROR = 4071;
  
  public static final int MQRCCF_MR_EXIT_NAME_WRONG_TYPE = 4072;
  
  public static final int MQRCCF_MR_INTERVAL_ERROR = 4073;
  
  public static final int MQRCCF_MR_INTERVAL_WRONG_TYPE = 4074;
  
  public static final int MQRCCF_NPM_SPEED_ERROR = 4075;
  
  public static final int MQRCCF_NPM_SPEED_WRONG_TYPE = 4076;
  
  public static final int MQRCCF_HB_INTERVAL_ERROR = 4077;
  
  public static final int MQRCCF_HB_INTERVAL_WRONG_TYPE = 4078;
  
  public static final int MQRCCF_CHAD_ERROR = 4079;
  
  public static final int MQRCCF_CHAD_WRONG_TYPE = 4080;
  
  public static final int MQRCCF_CHAD_EVENT_ERROR = 4081;
  
  public static final int MQRCCF_CHAD_EVENT_WRONG_TYPE = 4082;
  
  public static final int MQRCCF_CHAD_EXIT_ERROR = 4083;
  
  public static final int MQRCCF_CHAD_EXIT_WRONG_TYPE = 4084;
  
  public static final int MQRCCF_SUPPRESSED_BY_EXIT = 4085;
  
  public static final int MQRCCF_BATCH_INT_ERROR = 4086;
  
  public static final int MQRCCF_BATCH_INT_WRONG_TYPE = 4087;
  
  public static final int MQRCCF_NET_PRIORITY_ERROR = 4088;
  
  public static final int MQRCCF_NET_PRIORITY_WRONG_TYPE = 4089;
  
  public static final int MQRCCF_CHANNEL_CLOSED = 4090;
  
  public static final int MQRCCF_Q_STATUS_NOT_FOUND = 4091;
  
  public static final int MQRCCF_SSL_CIPHER_SPEC_ERROR = 4092;
  
  public static final int MQRCCF_SSL_PEER_NAME_ERROR = 4093;
  
  public static final int MQRCCF_SSL_CLIENT_AUTH_ERROR = 4094;
  
  public static final int MQRCCF_RETAINED_NOT_SUPPORTED = 4095;
  
  public static final int MQRCCF_KWD_VALUE_WRONG_TYPE = 4096;
  
  public static final int MQRCCF_APPL_STATUS_NOT_FOUND = 4097;
  
  public static final int MQRCCF_NHA_NOT_AVAILABLE = 4098;
  
  public static final int MQRCCF_Q_MGR_STATUS_NOT_FOUND = 4099;
  
  public static final int MQCFBF_STRUC_LENGTH_FIXED = 20;
  
  public static final int MQCFBS_STRUC_LENGTH_FIXED = 16;
  
  public static final int MQCFGR_STRUC_LENGTH = 16;
  
  public static final int MQCFIF_STRUC_LENGTH = 20;
  
  public static final int MQCFIL_STRUC_LENGTH_FIXED = 16;
  
  public static final int MQCFIL64_STRUC_LENGTH_FIXED = 16;
  
  public static final int MQCFIN_STRUC_LENGTH = 16;
  
  public static final int MQCFIN64_STRUC_LENGTH = 24;
  
  public static final int MQCFSF_STRUC_LENGTH_FIXED = 24;
  
  public static final int MQCFSL_STRUC_LENGTH_FIXED = 24;
  
  public static final int MQCFST_STRUC_LENGTH_FIXED = 20;
  
  public static final String MQEPH_STRUC_ID = "EPH ";
  
  public static final int MQEPH_STRUC_LENGTH_FIXED = 68;
  
  public static final int MQEPH_VERSION_1 = 1;
  
  public static final int MQEPH_CURRENT_VERSION = 1;
  
  public static final int MQEPH_LENGTH_1 = 68;
  
  public static final int MQEPH_CURRENT_LENGTH = 68;
  
  public static final int MQEPH_NONE = 0;
  
  public static final int MQEPH_CCSID_EMBEDDED = 1;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\constants\CMQCFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */