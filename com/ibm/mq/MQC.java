/*      */ package com.ibm.mq;
/*      */ 
/*      */ import com.ibm.mq.constants.CMQC;
/*      */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public interface MQC
/*      */ {
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQC.java";
/*      */   @Deprecated
/*      */   public static final int MQGMO_WAIT = 1;
/*      */   @Deprecated
/*      */   public static final int MQGMO_NO_WAIT = 0;
/*      */   @Deprecated
/*      */   public static final int MQGMO_SYNCPOINT = 2;
/*      */   @Deprecated
/*      */   public static final int MQGMO_NO_SYNCPOINT = 4;
/*      */   @Deprecated
/*      */   public static final int MQGMO_BROWSE_FIRST = 16;
/*      */   @Deprecated
/*      */   public static final int MQGMO_BROWSE_NEXT = 32;
/*      */   @Deprecated
/*      */   public static final int MQGMO_BROWSE_MSG_UNDER_CURSOR = 2048;
/*      */   @Deprecated
/*      */   public static final int MQGMO_MSG_UNDER_CURSOR = 256;
/*      */   @Deprecated
/*      */   public static final int MQGMO_LOCK = 512;
/*      */   @Deprecated
/*      */   public static final int MQGMO_UNLOCK = 1024;
/*      */   @Deprecated
/*      */   public static final int MQGMO_ACCEPT_TRUNCATED_MSG = 64;
/*      */   @Deprecated
/*      */   public static final int MQGMO_FAIL_IF_QUIESCING = 8192;
/*      */   @Deprecated
/*      */   public static final int MQGMO_CONVERT = 16384;
/*      */   public static final int MQGMO_UNMARKED_BROWSE_MSG = 16777216;
/*      */   public static final int MQGMO_MARK_BROWSE_HANDLE = 1048576;
/*      */   public static final int MQGMO_MARK_BROWSE_CO_OP = 2097152;
/*      */   public static final int MQGMO_UNMARK_BROWSE_CO_OP = 4194304;
/*      */   public static final int MQGMO_UNMARK_BROWSE_HANDLE = 8388608;
/*      */   @Deprecated
/*      */   public static final int MQGMO_NONE = 0;
/*      */   @Deprecated
/*      */   public static final int MQWI_UNLIMITED = -1;
/*      */   @Deprecated
/*      */   public static final int MQ_ACCOUNTING_TOKEN_LENGTH = 32;
/*      */   @Deprecated
/*      */   public static final int MQ_APPL_IDENTITY_DATA_LENGTH = 32;
/*      */   @Deprecated
/*      */   public static final int MQ_APPL_NAME_LENGTH = 28;
/*      */   @Deprecated
/*      */   public static final int MQ_PUT_APPL_NAME_LENGTH = 28;
/*      */   @Deprecated
/*      */   public static final int MQ_APPL_ORIGIN_DATA_LENGTH = 4;
/*      */   @Deprecated
/*      */   public static final int MQ_CHANNEL_NAME_LENGTH = 20;
/*      */   @Deprecated
/*      */   public static final int MQ_CONN_NAME_LENGTH = 264;
/*      */   @Deprecated
/*      */   public static final int MQ_CORREL_ID_LENGTH = 24;
/*      */   @Deprecated
/*      */   public static final int MQ_EXIT_DATA_LENGTH = 32;
/*      */   @Deprecated
/*  313 */   public static final int MQ_EXIT_NAME_LENGTH = JmqiEnvironment.getMqExitNameLength();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQ_EXIT_USER_AREA_LENGTH = 16;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQ_FORMAT_LENGTH = 8;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQ_GROUP_ID_LENGTH = 24;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQ_CONN_TAG_LENGTH = 128;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQ_MSG_HEADER_LENGTH = 4000;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQ_MSG_ID_LENGTH = 24;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQ_NAMELIST_DESC_LENGTH = 64;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQ_NAMELIST_NAME_LENGTH = 48;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQ_PASSWORD_LENGTH = 12;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQ_PROCESS_APPL_ID_LENGTH = 256;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQ_PROCESS_DESC_LENGTH = 64;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQ_PROCESS_ENV_DATA_LENGTH = 128;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQ_PROCESS_NAME_LENGTH = 48;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQ_PROCESS_USER_DATA_LENGTH = 128;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQ_Q_DESC_LENGTH = 64;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQ_Q_MGR_DESC_LENGTH = 64;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQ_Q_MGR_NAME_LENGTH = 48;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQ_Q_NAME_LENGTH = 48;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQ_QSG_NAME_LENGTH = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQ_SECURITY_ID_LENGTH = 40;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQ_STORAGE_CLASS_LENGTH = 8;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQ_TRIGGER_DATA_LENGTH = 64;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQ_USER_ID_LENGTH = 12;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQ_MSG_TOKEN_LENGTH = 16;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRO_EXCEPTION = 16777216;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRO_EXCEPTION_WITH_DATA = 50331648;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRO_EXCEPTION_WITH_FULL_DATA = 117440512;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRO_EXPIRATION = 2097152;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRO_EXPIRATION_WITH_DATA = 6291456;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRO_EXPIRATION_WITH_FULL_DATA = 14680064;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRO_COA = 256;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRO_COA_WITH_DATA = 768;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRO_COA_WITH_FULL_DATA = 1792;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRO_COD = 2048;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRO_COD_WITH_DATA = 6144;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRO_COD_WITH_FULL_DATA = 14336;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRO_COPY_MSG_ID_TO_CORREL_ID = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRO_PASS_CORREL_ID = 64;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRO_NEW_MSG_ID = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRO_PASS_MSG_ID = 128;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRO_DEAD_LETTER_Q = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRO_DISCARD_MSG = 134217728;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRO_PASS_DISCARD_AND_EXPIRY = 16384;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRO_NONE = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRO_REJECT_UNSUP_MASK = 270270464;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRO_ACCEPT_UNSUP_MASK = -270532353;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRO_ACCEPT_UNSUP_IF_XMIT_MASK = 261888;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRO_ACTIVITY = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQMT_SYSTEM_FIRST = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQMT_REQUEST = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQMT_REPLY = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQMT_DATAGRAM = 8;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQMT_REPORT = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQMT_SYSTEM_LAST = 65535;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQMT_APPL_FIRST = 65536;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQMT_APPL_LAST = 999999999;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQEI_UNLIMITED = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQFB_NONE = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQFB_SYSTEM_FIRST = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQFB_EXPIRATION = 258;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQFB_COA = 259;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQFB_COD = 260;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQFB_QUIT = 256;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQFB_CHANNEL_COMPLETED = 262;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQFB_CHANNEL_FAIL_RETRY = 263;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MQFB_CHANNEL_FAIL = 264;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQFB_APPL_CANNOT_BE_STARTED = 265;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQFB_TM_ERROR = 266;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQFB_APPL_TYPE_ERROR = 267;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQFB_STOPPED_BY_MSG_EXIT = 268;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQFB_XMIT_Q_MSG_ERROR = 271;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQFB_SYSTEM_LAST = 65535;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQFB_APPL_FIRST = 65536;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQFB_APPL_LAST = 999999999;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQFB_ACTIVITY = 269;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQFB_MAX_ACTIVITIES = 282;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQFB_NOT_FORWARDED = 283;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQFB_NOT_DELIVERED = 284;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQFB_UNSUPPORTED_FORWARDING = 285;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQFB_UNSUPPORTED_DELIVERY = 286;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQENC_NATIVE = 273;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQENC_INTEGER_MASK = 15;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQENC_DECIMAL_MASK = 240;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQENC_FLOAT_MASK = 3840;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQENC_RESERVED_MASK = -4096;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQENC_INTEGER_UNDEFINED = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQENC_INTEGER_NORMAL = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQENC_INTEGER_REVERSED = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQENC_DECIMAL_UNDEFINED = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQENC_DECIMAL_NORMAL = 16;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQENC_DECIMAL_REVERSED = 32;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQENC_FLOAT_UNDEFINED = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQENC_FLOAT_IEEE_NORMAL = 256;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQENC_FLOAT_IEEE_REVERSED = 512;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQENC_FLOAT_S390 = 768;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQENC_FLOAT_TNS = 1024;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCCSI_DEFAULT = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCCSI_Q_MGR = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCCSI_INHERIT = -2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final String MQFMT_NONE = "        ";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final String MQFMT_ADMIN = "MQADMIN ";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final String MQFMT_CHANNEL_COMPLETED = "MQCHCOM ";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final String MQFMT_COMMAND_1 = "MQCMD1  ";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final String MQFMT_COMMAND_2 = "MQCMD2  ";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final String MQFMT_DEAD_LETTER_HEADER = "MQDEAD  ";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final String MQFMT_EVENT = "MQEVENT ";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final String MQFMT_PCF = "MQPCF   ";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final String MQFMT_STRING = "MQSTR   ";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final String MQFMT_TRIGGER = "MQTRIG  ";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final String MQFMT_XMIT_Q_HEADER = "MQXMIT  ";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final String MQFMT_CICS = "MQCICS  ";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final String MQFMT_IMS = "MQIMS   ";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final String MQFMT_IMS_VAR_STRING = "MQIMSVS ";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final String MQFMT_RF_HEADER = "MQHRF   ";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final String MQFMT_RF_HEADER_1 = "MQHRF   ";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final String MQFMT_RF_HEADER_2 = "MQHRF2  ";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final String MQFMT_DIST_HEADER = "MQHDIST ";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final String MQFMT_MD_EXTENSION = "MQHMDE  ";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final String MQFMT_REF_MSG_HEADER = "MQHREF  ";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final String MQRFH_STRUC_ID = "RFH ";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRFH_VERSION_1 = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRFH_VERSION_2 = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRFH_NO_FLAGS = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRFH_STRUC_LENGTH_FIXED = 32;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRFH_STRUC_LENGTH_FIXED_2 = 36;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQPRI_PRIORITY_AS_Q_DEF = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQPER_PERSISTENT = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQPER_NOT_PERSISTENT = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQPER_PERSISTENCE_AS_Q_DEF = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQMF_REJECT_UNSUP_MASK = 4095;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQMF_ACCEPT_UNSUP_MASK = -1048576;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQMF_ACCEPT_UNSUP_IF_XMIT_MASK = 1044480;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/* 1388 */   public static final byte[] MQMI_NONE = CMQC.MQMI_NONE;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQCI_NONE_STRING = "\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000";
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/* 1398 */   public static final byte[] MQCI_NONE = CMQC.MQCI_NONE;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/* 1407 */   public static final byte[] MQCI_NEW_SESSION = new byte[] { 65, 77, 81, 33, 78, 69, 87, 95, 83, 69, 83, 83, 73, 79, 78, 95, 67, 79, 82, 82, 69, 76, 73, 68 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/* 1415 */   public static final byte[] MQACT_NONE = CMQC.MQACT_NONE;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final byte MQACTT_UNKNOWN = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final byte MQACTT_CICS_LUOW_ID = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final byte MQACTT_OS2_DEFAULT = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final byte MQACTT_DOS_DEFAULT = 5;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final byte MQACTT_UNIX_NUMERIC_ID = 6;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final byte MQACTT_OS400_ACCOUNT_TOKEN = 8;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final byte MQACTT_WINDOWS_DEFAULT = 9;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final byte MQACTT_NT_SECURITY_ID = 11;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final byte MQACTT_USER = 25;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQAT_UNKNOWN = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQAT_NO_CONTEXT = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQAT_CICS = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQAT_MVS = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQAT_IMS = 3;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQAT_OS2 = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQAT_DOS = 5;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQAT_AIX = 6;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQAT_UNIX = 6;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQAT_QMGR = 7;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQAT_OS400 = 8;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQAT_WINDOWS = 9;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQAT_CICS_VSE = 10;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQAT_VMS = 12;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQAT_GUARDIAN = 13;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQAT_VOS = 14;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQAT_JAVA = 28;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQAT_DEFAULT = 28;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQAT_USER_FIRST = 65536;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQAT_USER_LAST = 999999999;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQAT_NSK = 13;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQAT_CICS_BRIDGE = 21;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQAT_NOTES_AGENT = 22;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQAT_WINDOWS_NT = 11;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQAT_IMS_BRIDGE = 19;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQAT_XCF = 20;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQPMO_SYNCPOINT = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQPMO_NO_SYNCPOINT = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQPMO_NO_CONTEXT = 16384;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQPMO_DEFAULT_CONTEXT = 32;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQPMO_PASS_IDENTITY_CONTEXT = 256;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQPMO_PASS_ALL_CONTEXT = 512;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQPMO_SET_IDENTITY_CONTEXT = 1024;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQPMO_SET_ALL_CONTEXT = 2048;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQPMO_ALTERNATE_USER_AUTHORITY = 4096;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQPMO_FAIL_IF_QUIESCING = 8192;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQPMO_RESOLVE_LOCAL_Q = 262144;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQPMO_ASYNC_RESPONSE = 65536;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQPMO_SYNC_RESPONSE = 131072;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQPMO_RESPONSE_AS_Q_DEF = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQPMO_RESPONSE_AS_TOPIC_DEF = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQPMO_NONE = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCO_NONE = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCO_DELETE_PURGE = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCO_DELETE = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCO_IMMEDIATE = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCO_QUIESCE = 32;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQQT_LOCAL = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQQT_MODEL = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQQT_ALIAS = 3;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQQT_REMOTE = 6;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQQT_CLUSTER = 7;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQQDT_PREDEFINED = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQQDT_PERMANENT_DYNAMIC = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQQDT_TEMPORARY_DYNAMIC = 3;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQQA_GET_INHIBITED = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQQA_GET_ALLOWED = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQQA_PUT_INHIBITED = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQQA_PUT_ALLOWED = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQQA_SHAREABLE = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQQA_NOT_SHAREABLE = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQQA_BACKOUT_HARDENED = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQQA_BACKOUT_NOT_HARDENED = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQMDS_PRIORITY = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQMDS_FIFO = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQTC_OFF = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQTC_ON = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQTT_NONE = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQTT_FIRST = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQTT_EVERY = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQTT_DEPTH = 3;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQUS_NORMAL = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQUS_TRANSMISSION = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOO_INPUT_AS_Q_DEF = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOO_INPUT_SHARED = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOO_INPUT_EXCLUSIVE = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOO_BROWSE = 8;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOO_OUTPUT = 16;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOO_SAVE_ALL_CONTEXT = 128;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOO_ALTERNATE_USER_AUTHORITY = 4096;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOO_FAIL_IF_QUIESCING = 8192;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOO_PASS_IDENTITY_CONTEXT = 256;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOO_PASS_ALL_CONTEXT = 512;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOO_SET_IDENTITY_CONTEXT = 1024;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOO_SET_ALL_CONTEXT = 2048;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOO_INQUIRE = 32;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOO_SET = 64;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOO_BIND_ON_OPEN = 16384;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOO_BIND_NOT_FIXED = 32768;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOO_CO_OP = 131072;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOO_BIND_AS_Q_DEF = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOO_RESOLVE_NAMES = 65536;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOO_RESOLVE_LOCAL_Q = 262144;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOO_READ_AHEAD = 1048576;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOO_NO_READ_AHEAD = 524288;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOO_READ_AHEAD_AS_Q_DEF = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQPL_MVS = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQPL_OS2 = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQPL_AIX = 3;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQPL_UNIX = 3;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQPL_OS400 = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQPL_WINDOWS = 5;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQPL_WINDOWS_NT = 11;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQPL_NSK = 13;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQSP_AVAILABLE = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQSP_NOT_AVAILABLE = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCSP_VERSION_1 = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCSP_AUTH_NONE = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCSP_AUTH_USER_ID_AND_PWD = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQSCO_VERSION_1 = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQSCO_VERSION_2 = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCNO_VERSION_1 = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCNO_VERSION_2 = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCNO_VERSION_3 = 3;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCNO_VERSION_4 = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCNO_VERSION_5 = 5;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCNO_VERSION_6 = 6;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCNO_STANDARD_BINDING = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCNO_FASTPATH_BINDING = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCNO_NONE = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCNO_SHARED_BINDING = 256;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCNO_ISOLATED_BINDING = 512;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCNO_SERIALIZE_CONN_TAG_Q_MGR = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCNO_SERIALIZE_CONN_TAG_QSG = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCNO_RESTRICT_CONN_TAG_Q_MGR = 8;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCNO_RESTRICT_CONN_TAG_QSG = 16;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCNO_NO_CONV_SHARING = 65536;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCNO_ALL_CONVS_SHARE = 262144;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCMDL_LEVEL_1 = 100;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCMDL_LEVEL_101 = 101;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCMDL_LEVEL_110 = 110;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCMDL_LEVEL_114 = 114;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCMDL_LEVEL_120 = 120;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCMDL_LEVEL_200 = 200;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCMDL_LEVEL_201 = 201;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCMDL_LEVEL_210 = 210;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCMDL_LEVEL_221 = 221;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCMDL_LEVEL_230 = 230;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCMDL_LEVEL_320 = 320;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCMDL_LEVEL_500 = 500;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCMDL_LEVEL_510 = 510;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCMDL_LEVEL_520 = 520;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCMDL_LEVEL_530 = 530;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCMDL_LEVEL_531 = 531;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCMDL_LEVEL_600 = 600;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCMDL_LEVEL_700 = 700;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCMDL_LEVEL_701 = 701;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQDL_NOT_SUPPORTED = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQDL_SUPPORTED = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQBND_BIND_ON_OPEN = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQBND_BIND_NOT_FIXED = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final String MQGI_NONE_STRING = "\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/* 2740 */   public static final byte[] MQGI_NONE = CMQC.MQGI_NONE;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQGMO_MARK_SKIP_BACKOUT = 128;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQGMO_SYNCPOINT_IF_PERSISTENT = 4096;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQGMO_LOGICAL_ORDER = 32768;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQGMO_COMPLETE_MSG = 65536;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQGMO_ALL_MSGS_AVAILABLE = 131072;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQGMO_ALL_SEGMENTS_AVAILABLE = 262144;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQGMO_VERSION_1 = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQGMO_VERSION_2 = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQGMO_VERSION_3 = 3;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final char MQGS_NOT_IN_GROUP = ' ';
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final char MQGS_MSG_IN_GROUP = 'G';
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final char MQGS_LAST_MSG_IN_GROUP = 'L';
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQIAV_UNDEFINED = -2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQIAV_NOT_APPLICABLE = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQMD_VERSION_1 = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQMD_VERSION_2 = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQMF_SEGMENTATION_INHIBITED = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQMF_NONE = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQMF_SEGMENTATION_ALLOWED = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQMF_SEGMENT = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQMF_LAST_SEGMENT = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQMF_MSG_IN_GROUP = 8;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQMF_LAST_MSG_IN_GROUP = 16;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final byte MQSIDT_NONE = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final byte MQSIDT_NT_SECURITY_ID = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQMO_NONE = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQMO_MATCH_MSG_ID = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQMO_MATCH_CORREL_ID = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQMO_MATCH_GROUP_ID = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQMO_MATCH_MSG_SEQ_NUMBER = 8;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQMO_MATCH_OFFSET = 16;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQMO_MATCH_MSG_TOKEN = 32;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/* 3033 */   public static final byte[] MQMTOK_NONE = CMQC.MQMTOK_NONE;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOL_UNDEFINED = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOT_Q = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOT_NAMELIST = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOT_PROCESS = 3;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOT_STORAGE_CLASS = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOT_Q_MGR = 5;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOT_CHANNEL = 6;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOT_AUTH_INFO = 7;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOT_CF_STRUC = 10;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOT_RESERVED_1 = 999;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOT_ALL = 1001;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOT_ALIAS_Q = 1002;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOT_MODEL_Q = 1003;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOT_LOCAL_Q = 1004;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOT_REMOTE_Q = 1005;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOT_SENDER_CHANNEL = 1007;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOT_SERVER_CHANNEL = 1008;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOT_REQUESTER_CHANNEL = 1009;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOT_RECEIVER_CHANNEL = 1010;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOT_CURRENT_CHANNEL = 1011;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOT_SAVED_CHANNEL = 1012;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOT_SVRCONN_CHANNEL = 1013;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQOT_CLNTCONN_CHANNEL = 1014;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQPMO_NEW_MSG_ID = 64;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQPMO_NEW_CORREL_ID = 128;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQPMO_LOGICAL_ORDER = 32768;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQPMO_VERSION_1 = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQPMO_VERSION_2 = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQPMRF_NONE = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQPMRF_MSG_ID = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQPMRF_CORREL_ID = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQPMRF_GROUP_ID = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQPMRF_FEEDBACK = 8;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQPMRF_ACCOUNTING_TOKEN = 16;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRO_NAN = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRO_PAN = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQFB_PAN = 275;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQFB_NAN = 276;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQFB_DATA_LENGTH_ZERO = 291;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQFB_DATA_LENGTH_NEGATIVE = 292;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQFB_DATA_LENGTH_TOO_BIG = 293;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQFB_BUFFER_OVERFLOW = 294;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQFB_LENGTH_OFF_BY_ONE = 295;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQFB_IIH_ERROR = 296;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final char MQSEG_INHIBITED = ' ';
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final char MQSEG_ALLOWED = 'A';
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final char MQSS_NOT_A_SEGMENT = ' ';
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final char MQSS_LAST_SEGMENT = 'L';
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final char MQSS_SEGMENT = 'S';
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQRL_UNDEFINED = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MQCF_DIST_LISTS = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int HDRCOMPLIST_LENGTH = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int MSGCOMPLIST_LENGTH = 16;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/* 3368 */   public static final byte[] MQCT_NONE = CMQC.MQCT_NONE;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3378 */   public static final char[] MQCT_NONE_ARRAY = new char[] { Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE };
/*      */   @Deprecated
/*      */   public static final int MQCA_ALTERATION_DATE = 2027;
/*      */   @Deprecated
/*      */   public static final int MQCA_ALTERATION_TIME = 2028;
/*      */   @Deprecated
/*      */   public static final int MQCA_APPL_ID = 2001;
/*      */   @Deprecated
/*      */   public static final int MQCA_AUTH_INFO_CONN_NAME = 2053;
/*      */   @Deprecated
/*      */   public static final int MQCA_AUTH_INFO_DESC = 2046;
/*      */   @Deprecated
/*      */   public static final int MQCA_AUTH_INFO_NAME = 2045;
/*      */   @Deprecated
/*      */   public static final int MQCA_BACKOUT_REQ_Q_NAME = 2019;
/*      */   @Deprecated
/*      */   public static final int MQCA_BASE_Q_NAME = 2002;
/*      */   @Deprecated
/*      */   public static final int MQCA_BATCH_INTERFACE_ID = 2068;
/*      */   @Deprecated
/*      */   public static final int MQCA_CF_STRUC_DESC = 2052;
/*      */   @Deprecated
/*      */   public static final int MQCA_CF_STRUC_NAME = 2039;
/*      */   @Deprecated
/*      */   public static final int MQCA_CHANNEL_AUTO_DEF_EXIT = 2026;
/*      */   @Deprecated
/*      */   public static final int MQCA_CICS_FILE_NAME = 2060;
/*      */   @Deprecated
/*      */   public static final int MQCA_CLUSTER_DATE = 2037;
/*      */   @Deprecated
/*      */   public static final int MQCA_CLUSTER_NAME = 2029;
/*      */   @Deprecated
/*      */   public static final int MQCA_CLUSTER_NAMELIST = 2030;
/*      */   @Deprecated
/*      */   public static final int MQCA_CLUSTER_Q_MGR_NAME = 2031;
/*      */   @Deprecated
/*      */   public static final int MQCA_CLUSTER_TIME = 2038;
/*      */   @Deprecated
/*      */   public static final int MQCA_CLUSTER_WORKLOAD_DATA = 2034;
/*      */   @Deprecated
/*      */   public static final int MQCA_CLUSTER_WORKLOAD_EXIT = 2033;
/*      */   @Deprecated
/*      */   public static final int MQCA_COMMAND_INPUT_Q_NAME = 2003;
/*      */   @Deprecated
/*      */   public static final int MQCA_COMMAND_REPLY_Q_NAME = 2067;
/*      */   @Deprecated
/*      */   public static final int MQCA_CREATION_DATE = 2004;
/*      */   @Deprecated
/*      */   public static final int MQCA_CREATION_TIME = 2005;
/*      */   @Deprecated
/*      */   public static final int MQCA_DEAD_LETTER_Q_NAME = 2006;
/*      */   @Deprecated
/*      */   public static final int MQCA_DEF_XMIT_Q_NAME = 2025;
/*      */   @Deprecated
/*      */   public static final int MQCA_ENV_DATA = 2007;
/*      */   @Deprecated
/*      */   public static final int MQCA_FIRST = 2001;
/*      */   @Deprecated
/*      */   public static final int MQCA_IGQ_USER_ID = 2041;
/*      */   @Deprecated
/*      */   public static final int MQCA_INITIATION_Q_NAME = 2008;
/*      */   @Deprecated
/*      */   public static final int MQCA_LAST = 4000;
/*      */   @Deprecated
/*      */   public static final int MQCA_LAST_USED = 2138;
/*      */   @Deprecated
/*      */   public static final int MQCA_LDAP_PASSWORD = 2048;
/*      */   @Deprecated
/*      */   public static final int MQCA_LDAP_USER_NAME = 2047;
/*      */   @Deprecated
/*      */   public static final int MQCA_MONITOR_Q_NAME = 2066;
/*      */   @Deprecated
/*      */   public static final int MQCA_NAMELIST_DESC = 2009;
/*      */   @Deprecated
/*      */   public static final int MQCA_NAMELIST_NAME = 2010;
/*      */   @Deprecated
/*      */   public static final int MQCA_NAMES = 2020;
/*      */   @Deprecated
/*      */   public static final int MQCA_PROCESS_DESC = 2011;
/*      */   @Deprecated
/*      */   public static final int MQCA_PROCESS_NAME = 2012;
/*      */   @Deprecated
/*      */   public static final int MQCA_Q_DESC = 2013;
/*      */   @Deprecated
/*      */   public static final int MQCA_Q_MGR_DESC = 2014;
/*      */   @Deprecated
/*      */   public static final int MQCA_Q_MGR_IDENTIFIER = 2032;
/*      */   @Deprecated
/*      */   public static final int MQCA_Q_MGR_NAME = 2015;
/*      */   @Deprecated
/*      */   public static final int MQCA_Q_NAME = 2016;
/*      */   @Deprecated
/*      */   public static final int MQCA_QSG_NAME = 2040;
/*      */   @Deprecated
/*      */   public static final int MQCA_REMOTE_Q_MGR_NAME = 2017;
/*      */   @Deprecated
/*      */   public static final int MQCA_REMOTE_Q_NAME = 2018;
/*      */   @Deprecated
/*      */   public static final int MQCA_REPOSITORY_NAME = 2035;
/*      */   @Deprecated
/*      */   public static final int MQCA_REPOSITORY_NAMELIST = 2036;
/*      */   @Deprecated
/*      */   public static final int MQCA_SERVICE_NAME = 2077;
/*      */   @Deprecated
/*      */   public static final int MQCA_SERVICE_DESC = 2078;
/*      */   @Deprecated
/*      */   public static final int MQCA_SERVICE_START_COMMAND = 2079;
/*      */   @Deprecated
/*      */   public static final int MQCA_SERVICE_START_ARGS = 2080;
/*      */   @Deprecated
/*      */   public static final int MQCA_SERVICE_STOP_COMMAND = 2081;
/*      */   @Deprecated
/*      */   public static final int MQCA_SERVICE_STOP_ARGS = 2082;
/*      */   @Deprecated
/*      */   public static final int MQCA_STDOUT_DESTINATION = 2083;
/*      */   @Deprecated
/*      */   public static final int MQCA_STDERR_DESTINATION = 2084;
/*      */   @Deprecated
/*      */   public static final int MQCA_SSL_CRL_NAMELIST = 2050;
/*      */   @Deprecated
/*      */   public static final int MQCA_SSL_CRYPTO_HARDWARE = 2051;
/*      */   @Deprecated
/*      */   public static final int MQCA_SSL_KEY_LIBRARY = 2069;
/*      */   @Deprecated
/*      */   public static final int MQCA_SSL_KEY_MEMBER = 2070;
/*      */   @Deprecated
/*      */   public static final int MQCA_SSL_KEY_REPOSITORY = 2049;
/*      */   @Deprecated
/*      */   public static final int MQCA_STORAGE_CLASS = 2022;
/*      */   @Deprecated
/*      */   public static final int MQCA_STORAGE_CLASS_DESC = 2042;
/*      */   @Deprecated
/*      */   public static final int MQCA_SYSTEM_LOG_Q_NAME = 2065;
/*      */   @Deprecated
/*      */   public static final int MQCA_TRIGGER_CHANNEL_NAME = 2064;
/*      */   @Deprecated
/*      */   public static final int MQCA_TRIGGER_DATA = 2023;
/*      */   @Deprecated
/*      */   public static final int MQCA_TRIGGER_PROGRAM_NAME = 2062;
/*      */   @Deprecated
/*      */   public static final int MQCA_TRIGGER_TERM_ID = 2063;
/*      */   @Deprecated
/*      */   public static final int MQCA_TRIGGER_TRANS_ID = 2061;
/*      */   @Deprecated
/*      */   public static final int MQCA_USER_DATA = 2021;
/*      */   @Deprecated
/*      */   public static final int MQCA_USER_LIST = 4000;
/*      */   @Deprecated
/*      */   public static final int MQCA_XCF_GROUP_NAME = 2043;
/*      */   @Deprecated
/*      */   public static final int MQCA_XCF_MEMBER_NAME = 2044;
/*      */   @Deprecated
/*      */   public static final int MQCA_XMIT_Q_NAME = 2024;
/*      */   @Deprecated
/*      */   public static final int MQIA_APPL_TYPE = 1;
/*      */   @Deprecated
/*      */   public static final int MQIA_ARCHIVE = 60;
/*      */   @Deprecated
/*      */   public static final int MQIA_AUTH_INFO_TYPE = 66;
/*      */   @Deprecated
/*      */   public static final int MQIA_AUTHORITY_EVENT = 47;
/*      */   @Deprecated
/*      */   public static final int MQIA_BACKOUT_THRESHOLD = 22;
/*      */   @Deprecated
/*      */   public static final int MQIA_BATCH_INTERFACE_AUTO = 86;
/*      */   @Deprecated
/*      */   public static final int MQIA_CF_LEVEL = 70;
/*      */   @Deprecated
/*      */   public static final int MQIA_CF_RECOVER = 71;
/*      */   @Deprecated
/*      */   public static final int MQIA_CHANNEL_AUTO_DEF = 55;
/*      */   @Deprecated
/*      */   public static final int MQIA_CHANNEL_AUTO_DEF_EVENT = 56;
/*      */   @Deprecated
/*      */   public static final int MQIA_CLUSTER_Q_TYPE = 59;
/*      */   @Deprecated
/*      */   public static final int MQIA_CLUSTER_WORKLOAD_LENGTH = 58;
/*      */   @Deprecated
/*      */   public static final int MQIA_CLWL_Q_RANK = 95;
/*      */   @Deprecated
/*      */   public static final int MQIA_CLWL_Q_PRIORITY = 96;
/*      */   @Deprecated
/*      */   public static final int MQIA_CLWL_MRU_CHANNELS = 97;
/*      */   @Deprecated
/*      */   public static final int MQIA_CLWL_USEQ = 98;
/*      */   @Deprecated
/*      */   public static final int MQIA_CMD_SERVER_AUTO = 87;
/*      */   @Deprecated
/*      */   public static final int MQIA_CMD_SERVER_CONVERT_MSG = 88;
/*      */   @Deprecated
/*      */   public static final int MQIA_CMD_SERVER_DLQ_MSG = 89;
/*      */   @Deprecated
/*      */   public static final int MQIA_CODED_CHAR_SET_ID = 2;
/*      */   @Deprecated
/*      */   public static final int MQIA_COMMAND_LEVEL = 31;
/*      */   @Deprecated
/*      */   public static final int MQIA_CONFIGURATION_EVENT = 51;
/*      */   @Deprecated
/*      */   public static final int MQIA_CPI_LEVEL = 27;
/*      */   @Deprecated
/*      */   public static final int MQIA_CURRENT_Q_DEPTH = 3;
/*      */   @Deprecated
/*      */   public static final int MQIA_DEF_BIND = 61;
/*      */   @Deprecated
/*      */   public static final int MQIA_DEF_INPUT_OPEN_OPTION = 4;
/*      */   @Deprecated
/*      */   public static final int MQIA_DEF_PERSISTENCE = 5;
/*      */   @Deprecated
/*      */   public static final int MQIA_DEF_PRIORITY = 6;
/*      */   @Deprecated
/*      */   public static final int MQIA_DEF_PUT_RESPONSE_TYPE = 184;
/*      */   @Deprecated
/*      */   public static final int MQIA_DEFINITION_TYPE = 7;
/*      */   @Deprecated
/*      */   public static final int MQIA_DIST_LISTS = 34;
/*      */   @Deprecated
/*      */   public static final int MQIA_EXPIRY_INTERVAL = 39;
/*      */   @Deprecated
/*      */   public static final int MQIA_FIRST = 1;
/*      */   @Deprecated
/*      */   public static final int MQIA_HARDEN_GET_BACKOUT = 8;
/*      */   @Deprecated
/*      */   public static final int MQIA_HIGH_Q_DEPTH = 36;
/*      */   @Deprecated
/*      */   public static final int MQIA_IGQ_PUT_AUTHORITY = 65;
/*      */   @Deprecated
/*      */   public static final int MQIA_INDEX_TYPE = 57;
/*      */   @Deprecated
/*      */   public static final int MQIA_INHIBIT_EVENT = 48;
/*      */   @Deprecated
/*      */   public static final int MQIA_INHIBIT_GET = 9;
/*      */   @Deprecated
/*      */   public static final int MQIA_INHIBIT_PUT = 10;
/*      */   @Deprecated
/*      */   public static final int MQIA_INTRA_GROUP_QUEUING = 64;
/*      */   @Deprecated
/*      */   public static final int MQIA_LAST = 2000;
/*      */   @Deprecated
/*      */   public static final int MQIA_LISTENER_PORT_NUMBER = 85;
/*      */   @Deprecated
/*      */   public static final int MQIA_LOCAL_EVENT = 49;
/*      */   @Deprecated
/*      */   public static final int MQIA_MAX_GLOBAL_LOCKS = 83;
/*      */   @Deprecated
/*      */   public static final int MQIA_MAX_HANDLES = 11;
/*      */   @Deprecated
/*      */   public static final int MQIA_MAX_LOCAL_LOCKS = 84;
/*      */   @Deprecated
/*      */   public static final int MQIA_MAX_MSG_LENGTH = 13;
/*      */   @Deprecated
/*      */   public static final int MQIA_MAX_OPEN_Q = 80;
/*      */   @Deprecated
/*      */   public static final int MQIA_MAX_PRIORITY = 14;
/*      */   @Deprecated
/*      */   public static final int MQIA_MAX_Q_DEPTH = 15;
/*      */   @Deprecated
/*      */   public static final int MQIA_MAX_Q_TRIGGERS = 90;
/*      */   @Deprecated
/*      */   public static final int MQIA_MAX_UNCOMMITTED_MSGS = 33;
/*      */   @Deprecated
/*      */   public static final int MQIA_MONITOR_INTERVAL = 81;
/*      */   @Deprecated
/*      */   public static final int MQIA_MONITORING_CHANNEL = 122;
/*      */   @Deprecated
/*      */   public static final int MQIA_MONITORING_Q = 123;
/*      */   @Deprecated
/*      */   public static final int MQIA_MONITORING_AUTO_CLUSSDR = 124;
/*      */   @Deprecated
/*      */   public static final int MQIA_MSG_DELIVERY_SEQUENCE = 16;
/*      */   @Deprecated
/*      */   public static final int MQIA_MSG_DEQ_COUNT = 38;
/*      */   @Deprecated
/*      */   public static final int MQIA_MSG_ENQ_COUNT = 37;
/*      */   @Deprecated
/*      */   public static final int MQIA_NAME_COUNT = 19;
/*      */   @Deprecated
/*      */   public static final int MQIA_NAMELIST_TYPE = 72;
/*      */   @Deprecated
/*      */   public static final int MQIA_NPM_CLASS = 78;
/*      */   @Deprecated
/*      */   public static final int MQIA_OPEN_INPUT_COUNT = 17;
/*      */   @Deprecated
/*      */   public static final int MQIA_OPEN_OUTPUT_COUNT = 18;
/*      */   @Deprecated
/*      */   public static final int MQIA_PAGESET_ID = 62;
/*      */   @Deprecated
/*      */   public static final int MQIA_PERFORMANCE_EVENT = 53;
/*      */   @Deprecated
/*      */   public static final int MQIA_PLATFORM = 32;
/*      */   @Deprecated
/*      */   public static final int MQIA_Q_DEPTH_HIGH_EVENT = 43;
/*      */   @Deprecated
/*      */   public static final int MQIA_Q_DEPTH_HIGH_LIMIT = 40;
/*      */   @Deprecated
/*      */   public static final int MQIA_Q_DEPTH_LOW_EVENT = 44;
/*      */   @Deprecated
/*      */   public static final int MQIA_Q_DEPTH_LOW_LIMIT = 41;
/*      */   @Deprecated
/*      */   public static final int MQIA_Q_DEPTH_MAX_EVENT = 42;
/*      */   @Deprecated
/*      */   public static final int MQIA_Q_SERVICE_INTERVAL = 54;
/*      */   @Deprecated
/*      */   public static final int MQIA_Q_SERVICE_INTERVAL_EVENT = 46;
/*      */   @Deprecated
/*      */   public static final int MQIA_Q_TYPE = 20;
/*      */   @Deprecated
/*      */   public static final int MQIA_Q_USERS = 82;
/*      */   @Deprecated
/*      */   public static final int MQIA_QSG_DISP = 63;
/*      */   @Deprecated
/*      */   public static final int MQIA_REMOTE_EVENT = 50;
/*      */   @Deprecated
/*      */   public static final int MQIA_RETENTION_INTERVAL = 21;
/*      */   @Deprecated
/*      */   public static final int MQIA_SCOPE = 45;
/*      */   @Deprecated
/*      */   public static final int MQIA_SHAREABILITY = 23;
/*      */   @Deprecated
/*      */   public static final int MQIA_SSL_EVENT = 75;
/*      */   @Deprecated
/*      */   public static final int MQIA_SSL_FIPS_REQUIRED = 92;
/*      */   @Deprecated
/*      */   public static final int MQIA_SSL_RESET_COUNT = 76;
/*      */   @Deprecated
/*      */   public static final int MQIA_SSL_TASKS = 69;
/*      */   @Deprecated
/*      */   public static final int MQIA_START_STOP_EVENT = 52;
/*      */   @Deprecated
/*      */   public static final int MQIA_SYNCPOINT = 30;
/*      */   @Deprecated
/*      */   public static final int MQIA_TIME_SINCE_RESET = 35;
/*      */   @Deprecated
/*      */   public static final int MQIA_TRIGGER_CONTROL = 24;
/*      */   @Deprecated
/*      */   public static final int MQIA_TRIGGER_DEPTH = 29;
/*      */   @Deprecated
/*      */   public static final int MQIA_TRIGGER_INTERVAL = 25;
/*      */   @Deprecated
/*      */   public static final int MQIA_TRIGGER_MSG_PRIORITY = 26;
/*      */   @Deprecated
/*      */   public static final int MQIA_TRIGGER_TYPE = 28;
/*      */   @Deprecated
/*      */   public static final int MQIA_TRIGGER_RESTART = 91;
/*      */   @Deprecated
/*      */   public static final int MQIA_USAGE = 12;
/*      */   @Deprecated
/*      */   public static final int MQIA_CHANNEL_EVENT = 73;
/*      */   @Deprecated
/*      */   public static final int MQIA_IP_ADDRESS_VERSION = 93;
/*      */   @Deprecated
/*      */   public static final int MQIA_LOGGER_EVENT = 94;
/*      */   @Deprecated
/*      */   public static final int MQIA_COMMAND_EVENT = 99;
/*      */   @Deprecated
/*      */   public static final int MQIA_CHINIT_CONTROL = 119;
/*      */   @Deprecated
/*      */   public static final int MQIA_CMD_SERVER_CONTROL = 120;
/*      */   @Deprecated
/*      */   public static final int MQIA_SERVICE_TYPE = 121;
/*      */   @Deprecated
/*      */   public static final int MQIA_STATISTICS_MQI = 127;
/*      */   @Deprecated
/*      */   public static final int MQIA_STATISTICS_Q = 128;
/*      */   @Deprecated
/*      */   public static final int MQIA_STATISTICS_CHANNEL = 129;
/*      */   @Deprecated
/*      */   public static final int MQIA_STATISTICS_AUTO_CLUSSDR = 130;
/*      */   @Deprecated
/*      */   public static final int MQIA_STATISTICS_INTERVAL = 131;
/*      */   @Deprecated
/*      */   public static final int MQIA_ACCOUNTING_MQI = 133;
/*      */   @Deprecated
/*      */   public static final int MQIA_ACCOUNTING_Q = 134;
/*      */   @Deprecated
/*      */   public static final int MQIA_ACCOUNTING_INTERVAL = 135;
/*      */   @Deprecated
/*      */   public static final int MQIA_ACCOUNTING_CONN_OVERRIDE = 136;
/*      */   @Deprecated
/*      */   public static final int MQIA_TRACE_ROUTE_RECORDING = 137;
/*      */   @Deprecated
/*      */   public static final int MQIA_ACTIVITY_RECORDING = 138;
/*      */   @Deprecated
/*      */   public static final int MQIA_SERVICE_CONTROL = 139;
/*      */   @Deprecated
/*      */   public static final int MQIA_LAST_USED = 276;
/*      */   @Deprecated
/*      */   public static final int MQIA_USER_LIST = 2000;
/*      */   @Deprecated
/*      */   public static final int MQXT_CHANNEL_SEC_EXIT = 11;
/*      */   @Deprecated
/*      */   public static final int MQXT_CHANNEL_SEND_EXIT = 13;
/*      */   @Deprecated
/*      */   public static final int MQXT_CHANNEL_RCV_EXIT = 14;
/*      */   @Deprecated
/*      */   public static final int MQXR_INIT = 11;
/*      */   @Deprecated
/*      */   public static final int MQXR_TERM = 12;
/*      */   @Deprecated
/*      */   public static final int MQXR_XMIT = 14;
/*      */   @Deprecated
/*      */   public static final int MQXR_SEC_MSG = 15;
/*      */   @Deprecated
/*      */   public static final int MQXR_INIT_SEC = 16;
/*      */   @Deprecated
/*      */   public static final int MQXR_SEC_PARMS = 29;
/*      */   @Deprecated
/*      */   public static final int MQXCC_OK = 0;
/*      */   @Deprecated
/*      */   public static final int MQXCC_SUPPRESS_FUNCTION = -1;
/*      */   @Deprecated
/*      */   public static final int MQXCC_SEND_AND_REQUEST_SEC_MSG = -3;
/*      */   @Deprecated
/*      */   public static final int MQXCC_SEND_SEC_MSG = -4;
/*      */   @Deprecated
/*      */   public static final int MQXCC_SUPPRESS_EXIT = -5;
/*      */   @Deprecated
/*      */   public static final int MQXCC_CLOSE_CHANNEL = -6;
/*      */   @Deprecated
/*      */   public static final String CCSID_PROPERTY = "CCSID";
/*      */   @Deprecated
/*      */   public static final String CHANNEL_PROPERTY = "channel";
/*      */   @Deprecated
/*      */   public static final String CONNECT_OPTIONS_PROPERTY = "connectOptions";
/*      */   @Deprecated
/*      */   public static final String CONNTAG_PROPERTY = "ConnTag Property";
/*      */   @Deprecated
/*      */   public static final String HOST_NAME_PROPERTY = "hostname";
/*      */   @Deprecated
/*      */   public static final String ORB_PROPERTY = "ORB";
/*      */   @Deprecated
/*      */   public static final String PASSWORD_PROPERTY = "password";
/*      */   @Deprecated
/*      */   public static final String PORT_PROPERTY = "port";
/*      */   @Deprecated
/*      */   public static final String RECEIVE_EXIT_PROPERTY = "receiveExit";
/*      */   @Deprecated
/*      */   public static final String RECEIVE_EXIT_USER_DATA_PROPERTY = "receiveExitUserData";
/*      */   @Deprecated
/*      */   public static final String CHANNEL_RECEIVE_EXIT_PROPERTY = "channelReceiveExit";
/*      */   @Deprecated
/*      */   public static final String CHANNEL_RECEIVE_EXIT_USER_DATA_PROPERTY = "channelReceiveExitUserData";
/*      */   @Deprecated
/*      */   public static final String SECURITY_EXIT_PROPERTY = "securityExit";
/*      */   @Deprecated
/*      */   public static final String SECURITY_EXIT_USER_DATA_PROPERTY = "securityExitUserData";
/*      */   @Deprecated
/*      */   public static final String CHANNEL_SECURITY_EXIT_PROPERTY = "channelSecurityExit";
/*      */   @Deprecated
/*      */   public static final String CHANNEL_SECURITY_EXIT_USER_DATA_PROPERTY = "channelSecurityExitUserData";
/*      */   @Deprecated
/*      */   public static final String SEND_EXIT_PROPERTY = "sendExit";
/*      */   @Deprecated
/*      */   public static final String SEND_EXIT_USER_DATA_PROPERTY = "sendExitUserData";
/*      */   @Deprecated
/*      */   public static final String CHANNEL_SEND_EXIT_PROPERTY = "channelSendExit";
/*      */   @Deprecated
/*      */   public static final String CHANNEL_SEND_EXIT_USER_DATA_PROPERTY = "channelSendExitUserData";
/*      */   @Deprecated
/*      */   public static final String EXIT_CLASSPATH_PROPERTY = "exitClasspath";
/*      */   public static final String SHARING_CONVERSATIONS_PROPERTY = "sharingConversations";
/*      */   @Deprecated
/*      */   public static final String TRANSPORT_PROPERTY = "transport";
/*      */   @Deprecated
/*      */   public static final String TRANSPORT_MQSERIES = "MQSeries";
/*      */   @Deprecated
/*      */   public static final String TRANSPORT_MQSERIES_CLIENT = "MQSeries Client";
/*      */   @Deprecated
/*      */   public static final String TRANSPORT_MQSERIES_BINDINGS = "MQSeries Bindings";
/*      */   @Deprecated
/*      */   public static final String TRANSPORT_MQJD = "MQJD";
/*      */   @Deprecated
/*      */   public static final String USER_ID_PROPERTY = "userID";
/*      */   @Deprecated
/*      */   public static final String THREAD_ACCESS = "Thread access";
/*      */   @Deprecated
/*      */   public static final String MULTI_THREAD = "MULTI_THREAD";
/*      */   @Deprecated
/*      */   public static final String SINGLE_THREAD = "SINGLE_THREAD";
/*      */   @Deprecated
/*      */   public static final String THREAD_AFFINITY = "Thread affinity";
/*      */   @Deprecated
/*      */   public static final String THREAD_AFFINITY_PROPERTY = "Thread affinity";
/*      */   @Deprecated
/*      */   public static final String MQ_QMGR_ASSOCIATION_PROPERTY = "QMgr_Association";
/*      */   @Deprecated
/*      */   public static final String GROUP_PROPERTY = "Group";
/*      */   @Deprecated
/*      */   public static final String XA_REQ_PROPERTY = "XAReq";
/*      */   @Deprecated
/*      */   public static final String USE_QM_CCSID_PROPERTY = "Use QM CCSID";
/*      */   @Deprecated
/*      */   public static final String SSL_CIPHER_SUITE_PROPERTY = "SSL Cipher Suite";
/*      */   @Deprecated
/*      */   public static final String SSL_PEER_NAME_PROPERTY = "SSL Peer Name";
/*      */   @Deprecated
/*      */   public static final String SSL_CERT_STORE_PROPERTY = "SSL CertStores";
/*      */   @Deprecated
/*      */   public static final String SSL_SOCKET_FACTORY_PROPERTY = "SSL Socket Factory";
/*      */   @Deprecated
/*      */   public static final String SSL_RESET_COUNT_PROPERTY = "KeyResetCount";
/*      */   @Deprecated
/*      */   public static final String SSL_FIPS_REQUIRED_PROPERTY = "SSL Fips Required";
/*      */   @Deprecated
/*      */   public static final String LOCAL_ADDRESS_PROPERTY = "Local Address Property";
/*      */   @Deprecated
/*      */   public static final String LOCAL_ADDRESS_MARKER = "LastUsed Port Marker";
/*      */   @Deprecated
/*      */   public static final String BINDINGS_AUTHENTICATE = "Bindings Authentication";
/*      */   @Deprecated
/*      */   public static final int MQCOMPRESS_NOT_AVAILABLE = -1;
/*      */   @Deprecated
/*      */   public static final int MQCOMPRESS_NONE = 0;
/*      */   @Deprecated
/*      */   public static final int MQCOMPRESS_RLE = 1;
/*      */   @Deprecated
/*      */   public static final int MQCOMPRESS_ZLIBFAST = 2;
/*      */   @Deprecated
/*      */   public static final int MQCOMPRESS_ZLIBHIGH = 4;
/*      */   @Deprecated
/*      */   public static final int MQCOMPRESS_SYSTEM = 8;
/*      */   public static final String HEADER_COMPRESSION_PROPERTY = "Header Compression Property";
/*      */   public static final String MESSAGE_COMPRESSION_PROPERTY = "Message Compression Property";
/*      */   @Deprecated
/*      */   public static final int ASSOCIATE_NONE = 0;
/*      */   @Deprecated
/*      */   public static final int ASSOCIATE_THREAD = 8;
/*      */   @Deprecated
/*      */   public static final int ASSOCIATE_ALL = 32;
/*      */   public static final String CCDT_URL_PROPERTY = "CCDT URL";
/*      */   public static final String JMQI_FLAGS_PROPERTY = "JMQI FLAGS";
/*      */   public static final String NAMESPACE = "MQJI";
/*      */   public static final String INSERT_PROPERTY_OVERRIDE_LIST = "XMSC_INSERT_PROPERTY_OVERRIDE_LIST";
/*      */   public static final String CONNECTION_PROPERTY_OVERRIDE = "MQJIMQPROPERTY_OVERRIDE";
/*      */   public static final String INSERT_INDIRECT_PROPERTY_OVERRIDE = "XMSC_INSERT_INDIRECT_PROPERTY_OVERRIDE";
/*      */   public static final String PROPERTY_OVERRIDE_LOOKUP_FAILED = "MQJIMQPROPERTY_OVERRIDE_LOOKUP_FAILED";
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */