/*      */ package com.ibm.mq.exits;
/*      */ 
/*      */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*      */ import com.ibm.mq.jmqi.JmqiException;
/*      */ import com.ibm.mq.jmqi.internal.AbstractMqiStructure;
/*      */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*      */ import com.ibm.mq.jmqi.internal.JmqiStructureFormatter;
/*      */ import com.ibm.mq.jmqi.internal.JmqiTools;
/*      */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*      */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*      */ import com.ibm.mq.jmqi.system.JmqiTls;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.util.Arrays;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class MQCD
/*      */   extends AbstractMqiStructure
/*      */   implements Cloneable
/*      */ {
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/exits/MQCD.java";
/*      */   private static final int SIZEOF_CHANNEL_NAME = 20;
/*      */   private static final int SIZEOF_VERSION = 4;
/*      */   private static final int SIZEOF_CHANNEL_TYPE = 4;
/*      */   private static final int SIZEOF_TRANSPORT_TYPE = 4;
/*      */   private static final int SIZEOF_DESC = 64;
/*      */   private static final int SIZEOF_Q_MGR_NAME = 48;
/*      */   private static final int SIZEOF_XMIQ_Q_NAME = 48;
/*      */   private static final int SIZEOF_SHORT_CONNECTION_NAME = 20;
/*      */   private static final int SIZEOF_MCA_NAME = 20;
/*      */   private static final int SIZEOF_MODE_NAME = 8;
/*      */   private static final int SIZEOF_TP_NAME = 64;
/*      */   private static final int SIZEOF_BATCH_SIZE = 4;
/*      */   private static final int SIZEOF_DISC_INTERVAL = 4;
/*      */   private static final int SIZEOF_SHORT_RETRY_COUNT = 4;
/*      */   private static final int SIZEOF_SHORT_RETRY_INTERVAL = 4;
/*      */   private static final int SIZEOF_LONG_RETRY_COUNT = 4;
/*      */   private static final int SIZEOF_LONG_RETRY_INTERVAL = 4;
/*      */   
/*      */   static {
/*   48 */     if (Trace.isOn) {
/*   49 */       Trace.data("com.ibm.mq.exits.MQCD", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/exits/MQCD.java");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   78 */   private static int SIZEOF_SECURITY_EXIT = JmqiEnvironment.getMqExitNameLength();
/*   79 */   private static int SIZEOF_MSG_EXIT = JmqiEnvironment.getMqExitNameLength();
/*   80 */   private static int SIZEOF_SEND_EXIT = JmqiEnvironment.getMqExitNameLength();
/*   81 */   private static int SIZEOF_RECEIVE_EXIT = JmqiEnvironment.getMqExitNameLength();
/*      */   
/*      */   private static final int SIZEOF_SEQ_NUMBER_WRAP = 4;
/*      */   
/*      */   private static final int SIZEOF_MAX_MSG_LENGTH = 4;
/*      */   private static final int SIZEOF_PUT_AUTHORITY = 4;
/*      */   private static final int SIZEOF_DATA_CONVERSION = 4;
/*      */   private static final int SIZEOF_SECURITY_USER_DATA = 32;
/*      */   private static final int SIZEOF_MSG_USER_DATA = 32;
/*      */   private static final int SIZEOF_SEND_USER_DATA = 32;
/*      */   private static final int SIZEOF_RECEIVE_USER_DATA = 32;
/*      */   private static final int SIZEOF_USER_IDENTIFIER = 12;
/*      */   private static final int SIZEOF_PASSWORD = 12;
/*      */   private static final int SIZEOF_MCA_USER_IDENTIFIER = 12;
/*      */   private static final int SIZEOF_MCA_TYPE = 4;
/*      */   private static final int SIZEOF_CONNECTION_NAME = 264;
/*      */   private static final int SIZEOF_REMOTE_USER_IDENTIFIER = 12;
/*      */   private static final int SIZEOF_REMOTE_PASSWORD = 12;
/*   99 */   private static int SIZEOF_MSG_RETRY_EXIT = JmqiEnvironment.getMqExitNameLength();
/*      */   
/*      */   private static final int SIZEOF_MSG_RETRY_USER_DATA = 32;
/*      */   
/*      */   private static final int SIZEOF_MSG_RETRY_COUNT = 4;
/*      */   
/*      */   private static final int SIZEOF_MSG_RETRY_INTERVAL = 4;
/*      */   
/*      */   private static final int SIZEOF_HEARTBEAT_INTERVAL = 4;
/*      */   
/*      */   private static final int SIZEOF_BATCH_INTERVAL = 4;
/*      */   
/*      */   private static final int SIZEOF_NON_PERSISTENT_MSG_SPEED = 4;
/*      */   
/*      */   private static final int SIZEOF_STRUC_LENGTH = 4;
/*      */   
/*      */   private static final int SIZEOF_EXIT_NAME_LENGTH = 4;
/*      */   
/*      */   private static final int SIZEOF_EXIT_DATA_LENGTH = 4;
/*      */   
/*      */   private static final int SIZEOF_MSG_EXITS_DEFINED = 4;
/*      */   
/*      */   private static final int SIZEOF_SEND_EXITS_DEFINED = 4;
/*      */   
/*      */   private static final int SIZEOF_RECEIVE_EXITS_DEFINED = 4;
/*      */   
/*      */   private static final int SIZEOF_CLUSTERS_DEFINED = 4;
/*      */   
/*      */   private static final int SIZEOF_NETWORK_PRIORITY = 4;
/*      */   
/*      */   private static final int SIZEOF_LONG_MCA_USER_ID_LENGTH = 4;
/*      */   
/*      */   private static final int SIZEOF_LONG_REMOTE_USER_ID_LENGTH = 4;
/*      */   
/*      */   private static final int SIZEOF_MCA_SECURITY_ID = 40;
/*      */   
/*      */   private static final int SIZEOF_REMOTE_SECURITY_ID = 40;
/*      */   
/*      */   private static final int SIZEOF_SSL_CIPHER_SPEC = 32;
/*      */   
/*      */   private static final int SIZEOF_SSL_PEER_NAME_LENGTH = 4;
/*      */   
/*      */   private static final int SIZEOF_SSL_CLIENT_AUTH = 4;
/*      */   
/*      */   private static final int SIZEOF_KEEP_ALIVE_INTERVAL = 4;
/*      */   
/*      */   private static final int SIZEOF_LOCAL_ADDRESS = 48;
/*      */   
/*      */   private static final int SIZEOF_BATCH_HEARTBEAT = 4;
/*      */   
/*      */   private static final int SIZEOF_HDR_COMP_LIST_ENTRY = 4;
/*      */   
/*      */   private static final int SIZEOF_MSG_COMP_LIST_ENTRY = 4;
/*      */   
/*      */   private static final int SIZEOF_CLWL_CHANNEL_RANK = 4;
/*      */   
/*      */   private static final int SIZEOF_CLWL_CHANNEL_PRIORITY = 4;
/*      */   
/*      */   private static final int SIZEOF_CLWL_CHANNEL_WEIGHT = 4;
/*      */   
/*      */   private static final int SIZEOF_CHANNEL_MONITORING = 4;
/*      */   
/*      */   private static final int SIZEOF_CHANNEL_STATISTICS = 4;
/*      */   
/*      */   private static final int SIZEOF_SHARING_CONVERSATIONS = 4;
/*      */   
/*      */   private static final int SIZEOF_PROPERTY_CONTROL = 4;
/*      */   
/*      */   private static final int SIZEOF_MAX_INSTANCES = 4;
/*      */   private static final int SIZEOF_MAX_INSTANCES_PER_CLIENT = 4;
/*      */   private static final int SIZEOF_CLIENT_CHANNEL_WEIGHT = 4;
/*      */   private static final int SIZEOF_CONNECTION_AFFINITY = 4;
/*      */   private static final int SIZEOF_BATCHDATALIMIT = 4;
/*      */   private static final int SIZEOF_USE_DLQ = 4;
/*      */   private static final int SIZEOF_DEF_RECONNECT = 4;
/*      */   private static final int SIZEOF_CERTIFICATE_LABEL = 64;
/*      */   private static final int SIZEOF_SPL_PROTECTION = 4;
/*      */   private static final int SIZEOF_PRIV_NEW_SEQ_NUM = 4;
/*      */   private static final int SIZEOF_PRIV_ENCRYPT = 4;
/*      */   private static final int SIZEOF_PRIV_EXITS_LIST_LEN = 4;
/*      */   private static final int SIZEOF_PRIV_FLAGS = 4;
/*      */   private static final int SIZEOF_PRIV_CLUSTER = 48;
/*      */   private static final int SIZEOF_PRIV_ALTER_TIME = 4;
/*      */   private static final int SIZEOF_PRIV_LONG_MCA_USER = 64;
/*      */   private static final int PRIV_NEW_SEQ_NUM_OFFSET = 0;
/*      */   private static final int PRIV_ENCRYPT_OFFSET = 4;
/*      */   private static final int PRIV_EXITS_LIST_LEN_OFFSET = 8;
/*      */   private static final int PRIV_FLAGS_OFFSET = 12;
/*      */   private static final int PRIV_CLUSTER_OFFSET = 16;
/*      */   private static final int PRIV_ALTER_TIME_OFFSET = 64;
/*      */   private static final int PRIV_LONG_MCA_USER_OFFSET = 68;
/*      */   private static final int PRIV_EXITS_LIST_OFFSET = 132;
/*      */   private static final byte SEG_SEPARATOR = 1;
/*      */   private static final byte SEC_SEPARATOR = 2;
/*      */   
/*      */   private static int getFieldSizeV1(int ptrSize) {
/*  195 */     return 328 + SIZEOF_SECURITY_EXIT + SIZEOF_MSG_EXIT + SIZEOF_SEND_EXIT + SIZEOF_RECEIVE_EXIT + 4 + 4 + 4 + 4 + 32 + 32 + 32 + 32;
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
/*      */   
/*      */   public static int getSizeV1(int ptrSize) {
/*  209 */     return getFieldSizeV1(ptrSize);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getFieldSizeV2(int ptrSize) {
/*  218 */     return getFieldSizeV1(ptrSize) + 12 + 12 + 12 + 4 + 264 + 12 + 12;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getSizeV2(int ptrSize) {
/*  229 */     return getFieldSizeV2(ptrSize);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getFieldSizeV3(int ptrSize) {
/*  238 */     return getFieldSizeV2(ptrSize) + SIZEOF_MSG_RETRY_EXIT + 32 + 4 + 4;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getSizeV3(int ptrSize) {
/*  248 */     return getFieldSizeV3(ptrSize);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getFieldSizeV4(int ptrSize) {
/*  257 */     int size = getFieldSizeV3(ptrSize);
/*  258 */     size += 4;
/*  259 */     size += 4;
/*  260 */     size += 4;
/*  261 */     size += 4;
/*  262 */     size += 4;
/*  263 */     size += 4;
/*  264 */     size += 4;
/*  265 */     size += 4;
/*  266 */     size += 4;
/*  267 */     size += JmqiTools.alignToGrain(ptrSize, size);
/*  268 */     size += 6 * ptrSize;
/*  269 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getSizeV4(int ptrSize) {
/*  279 */     return getFieldSizeV4(ptrSize);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getFieldSizeV5(int ptrSize) {
/*  288 */     return getFieldSizeV4(ptrSize) + ptrSize + 4 + 4;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getSizeV5(int ptrSize) {
/*  298 */     return getFieldSizeV5(ptrSize);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getFieldSizeV6(int ptrSize) {
/*  307 */     return getFieldSizeV5(ptrSize) + 4 + 4 + 2 * ptrSize + 40 + 40;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getSizeV6(int ptrSize) {
/*  317 */     return getFieldSizeV6(ptrSize);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getFieldSizeV7(int ptrSize) {
/*  326 */     return getFieldSizeV6(ptrSize) + 32 + ptrSize + 4 + 4 + 4 + 48 + 4;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getSizeV7(int ptrSize) {
/*  337 */     return getFieldSizeV7(ptrSize);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getFieldSizeV8(int ptrSize) {
/*  346 */     return getFieldSizeV7(ptrSize) + 8 + 64 + 4 + 4 + 4 + 4 + 4;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getSizeV8(int ptrSize) {
/*  357 */     int size = getFieldSizeV8(ptrSize);
/*  358 */     size += JmqiTools.alignToGrain(ptrSize, size);
/*  359 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getFieldSizeV9(int ptrSize) {
/*  368 */     int size = getFieldSizeV8(ptrSize);
/*  369 */     size += 4;
/*  370 */     size += 4;
/*  371 */     size += 4;
/*  372 */     size += 4;
/*  373 */     size += 4;
/*  374 */     size += 4;
/*  375 */     size += JmqiTools.alignToGrain(ptrSize, size);
/*  376 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getSizeV9(int ptrSize) {
/*  386 */     int size = getFieldSizeV9(ptrSize);
/*  387 */     size += JmqiTools.alignToGrain(ptrSize, size);
/*  388 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getFieldSizeV10(int ptrSize) {
/*  397 */     int size = getFieldSizeV9(ptrSize);
/*  398 */     size += 4;
/*  399 */     size += 4;
/*  400 */     size += 4;
/*  401 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getSizeV10(int ptrSize) {
/*  411 */     int size = getFieldSizeV10(ptrSize);
/*  412 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getFieldSizeV11(int ptrSize) {
/*  423 */     int size = getFieldSizeV10(ptrSize);
/*  424 */     size += 64;
/*      */     
/*  426 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getSizeV11(int ptrSize) {
/*  436 */     int size = getFieldSizeV11(ptrSize);
/*      */     
/*  438 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getFieldSizeV12(int ptrSize) {
/*  449 */     int size = getFieldSizeV11(ptrSize);
/*  450 */     size += 4;
/*      */     
/*  452 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getSizeV12(int ptrSize) {
/*  462 */     int size = getFieldSizeV12(ptrSize);
/*  463 */     size += JmqiTools.alignToGrain(ptrSize, size);
/*      */     
/*  465 */     return size;
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
/*      */   private static int getSize(JmqiEnvironment env, int ptrSize, int version) throws JmqiException {
/*  477 */     int size = 0;
/*      */     
/*  479 */     switch (version) {
/*      */       case 1:
/*  481 */         size = getSizeV1(ptrSize);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  522 */         return size;case 2: size = getSizeV2(ptrSize); return size;case 3: size = getSizeV3(ptrSize); return size;case 4: size = getSizeV4(ptrSize); return size;case 5: size = getSizeV5(ptrSize); return size;case 6: size = getSizeV6(ptrSize); return size;case 7: size = getSizeV7(ptrSize); return size;case 8: size = getSizeV8(ptrSize); return size;case 9: size = getSizeV9(ptrSize); return size;case 10: size = getSizeV10(ptrSize); return size;case 11: size = getSizeV11(ptrSize); return size;case 12: size = getSizeV12(ptrSize); return size;
/*      */     } 
/*      */     JmqiException e = new JmqiException(env, -1, null, 2, 2277, null);
/*      */     throw e;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*  530 */   private String channelName = null;
/*  531 */   private int version = 12;
/*  532 */   private int channelType = 6;
/*  533 */   private int transportType = 2;
/*  534 */   private String desc = null;
/*  535 */   private String qMgrName = null;
/*  536 */   private String xmitQName = null;
/*      */ 
/*      */   
/*  539 */   private String connectionName = null;
/*  540 */   private String mcaName = null;
/*  541 */   private String modeName = null;
/*  542 */   private String tpName = null;
/*  543 */   private int batchSize = 50;
/*  544 */   private int discInterval = 6000;
/*  545 */   private int shortRetryCount = 10;
/*  546 */   private int shortRetryInterval = 60;
/*  547 */   private int longRetryCount = 999999999;
/*  548 */   private int longRetryInterval = 1200;
/*  549 */   private String securityExit = null;
/*  550 */   private String msgExit = null;
/*  551 */   private String sendExit = null;
/*  552 */   private String receiveExit = null;
/*  553 */   private int seqNumberWrap = 99999999;
/*  554 */   private int maxMsgLength = 4194304;
/*  555 */   private int putAuthority = 1;
/*  556 */   private int dataConversion = 0;
/*  557 */   private int exitDataLength = 32;
/*  558 */   private byte[] securityUserData = new byte[this.exitDataLength];
/*  559 */   private byte[] msgUserData = new byte[this.exitDataLength];
/*  560 */   private byte[] sendUserData = new byte[this.exitDataLength];
/*  561 */   private byte[] receiveUserData = new byte[this.exitDataLength];
/*  562 */   private String userIdentifier = null;
/*  563 */   private String password = null;
/*  564 */   private String mcaUserIdentifier = null;
/*  565 */   private int mcaType = 1;
/*      */ 
/*      */   
/*  568 */   private String remoteUserIdentifier = null;
/*  569 */   private String remotePassword = null;
/*  570 */   private String msgRetryExit = null;
/*  571 */   private String msgRetryUserData = null;
/*  572 */   private int msgRetryCount = 10;
/*  573 */   private int msgRetryInterval = 1000;
/*  574 */   private int heartbeatInterval = 1;
/*  575 */   private int batchInterval = 0;
/*  576 */   private int nonPersistentMsgSpeed = 2;
/*      */ 
/*      */ 
/*      */   
/*  580 */   private int strucLength = 1876;
/*  581 */   private int exitNameLength = JmqiEnvironment.getMqExitNameLength();
/*  582 */   private int msgExitsDefined = 0;
/*  583 */   private int sendExitsDefined = 0;
/*  584 */   private int receiveExitsDefined = 0;
/*  585 */   private byte[] msgExitPtr = null;
/*  586 */   private byte[] msgUserDataPtr = null;
/*  587 */   private byte[] sendExitPtr = null;
/*  588 */   private byte[] sendUserDataPtr = null;
/*  589 */   private byte[] receiveExitPtr = null;
/*  590 */   private byte[] receiveUserDataPtr = null;
/*  591 */   private int clustersDefined = 0;
/*  592 */   private int networkPriority = 0;
/*      */ 
/*      */ 
/*      */   
/*  596 */   private byte[] mcaSecurityId = new byte[40];
/*  597 */   private byte[] remoteSecurityId = new byte[40];
/*  598 */   private String sslCipherSpec = null;
/*  599 */   private String sslPeerName = null;
/*  600 */   private int sslClientAuth = 0;
/*  601 */   private int keepAliveInterval = -1;
/*  602 */   private String localAddress = null;
/*  603 */   private int batchHeartbeat = 0;
/*  604 */   private int[] hdrCompList = new int[2];
/*  605 */   private int[] msgCompList = new int[16];
/*  606 */   private int clwlChannelRank = 0;
/*  607 */   private int clwlChannelPriority = 0;
/*  608 */   private int clwlChannelWeight = 50;
/*  609 */   private int channelMonitoring = 0;
/*  610 */   private int channelStatistics = 0;
/*      */ 
/*      */ 
/*      */   
/*  614 */   private int SharingConversations = 999999999;
/*      */   
/*  616 */   private int propertyControl = 0;
/*  617 */   private int maxInstances = 999999999;
/*  618 */   private int maxInstancesPerClient = 999999999;
/*  619 */   private int clientChannelWeight = 0;
/*  620 */   private int connectionAffinity = 1;
/*  621 */   private int batchDataLimit = 5000;
/*  622 */   private int useDLQ = 2;
/*  623 */   private int defReconnect = 0;
/*  624 */   private String certificateLabel = "";
/*  625 */   private int splProtection = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQCD(JmqiEnvironment env) {
/*  633 */     super(env);
/*  634 */     if (Trace.isOn) {
/*  635 */       Trace.entry(this, "com.ibm.mq.exits.MQCD", "<init>(JmqiEnvironment)", new Object[] { env });
/*      */     }
/*  637 */     Arrays.fill(this.hdrCompList, -1);
/*  638 */     this.hdrCompList[0] = 0;
/*  639 */     Arrays.fill(this.msgCompList, -1);
/*  640 */     this.msgCompList[0] = 0;
/*  641 */     Arrays.fill(this.mcaSecurityId, (byte)0);
/*  642 */     Arrays.fill(this.remoteSecurityId, (byte)0);
/*      */     
/*  644 */     if (Trace.isOn) {
/*  645 */       Trace.exit(this, "com.ibm.mq.exits.MQCD", "<init>(JmqiEnvironment)");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object clone() throws CloneNotSupportedException {
/*  664 */     if (Trace.isOn) {
/*  665 */       Trace.entry(this, "com.ibm.mq.exits.MQCD", "clone()");
/*      */     }
/*      */     
/*  668 */     Object newObject = super.clone();
/*  669 */     MQCD newMQCD = (MQCD)newObject;
/*      */ 
/*      */     
/*  672 */     if (this.transportType != 2) {
/*  673 */       CloneNotSupportedException traceRet1 = new CloneNotSupportedException("Transport type not TCP/IP");
/*      */       
/*  675 */       if (Trace.isOn) {
/*  676 */         Trace.throwing(this, "com.ibm.mq.exits.MQCD", "clone()", traceRet1);
/*      */       }
/*  678 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  683 */     newMQCD.setChannelName(this.channelName);
/*  684 */     newMQCD.setVersion(this.version);
/*  685 */     newMQCD.setChannelType(this.channelType);
/*  686 */     newMQCD.setTransportType(this.transportType);
/*  687 */     newMQCD.setConnectionName(this.connectionName);
/*  688 */     newMQCD.setSecurityExit(this.securityExit);
/*  689 */     newMQCD.securityUserData = cloneByteArray(this.securityUserData);
/*  690 */     newMQCD.setMaxMsgLength(this.maxMsgLength);
/*  691 */     newMQCD.setHeartbeatInterval(this.heartbeatInterval);
/*  692 */     newMQCD.setExitNameLength(this.exitNameLength);
/*  693 */     newMQCD.setExitDataLength(this.exitDataLength);
/*  694 */     newMQCD.setSendExitsDefined(this.sendExitsDefined);
/*  695 */     newMQCD.setReceiveExitsDefined(this.receiveExitsDefined);
/*  696 */     newMQCD.sendExitPtr = cloneByteArray(this.sendExitPtr);
/*  697 */     newMQCD.sendUserDataPtr = cloneByteArray(this.sendUserDataPtr);
/*  698 */     newMQCD.receiveExitPtr = cloneByteArray(this.receiveExitPtr);
/*  699 */     newMQCD.receiveUserDataPtr = cloneByteArray(this.receiveUserDataPtr);
/*  700 */     newMQCD.setSslCipherSpec(this.sslCipherSpec);
/*  701 */     newMQCD.setSslPeerName(this.sslPeerName);
/*  702 */     newMQCD.setLocalAddress(this.localAddress);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  708 */     newMQCD.hdrCompList = (int[])this.hdrCompList.clone();
/*  709 */     newMQCD.msgCompList = (int[])this.msgCompList.clone();
/*      */     
/*  711 */     newMQCD.setXmitQName(this.xmitQName);
/*  712 */     newMQCD.setMcaName(this.mcaName);
/*  713 */     newMQCD.setModeName(this.modeName);
/*  714 */     newMQCD.setTpName(this.tpName);
/*  715 */     newMQCD.setBatchSize(this.batchSize);
/*  716 */     newMQCD.setDiscInterval(this.discInterval);
/*  717 */     newMQCD.setShortRetryCount(this.shortRetryCount);
/*  718 */     newMQCD.setShortRetryInterval(this.shortRetryInterval);
/*  719 */     newMQCD.setLongRetryCount(this.longRetryCount);
/*  720 */     newMQCD.setLongRetryInterval(this.longRetryInterval);
/*  721 */     newMQCD.setMsgExit(this.msgExit);
/*  722 */     newMQCD.setSendExit(this.sendExit);
/*  723 */     newMQCD.setReceiveExit(this.receiveExit);
/*  724 */     newMQCD.setSeqNumberWrap(this.seqNumberWrap);
/*  725 */     newMQCD.setPutAuthority(this.putAuthority);
/*  726 */     newMQCD.setDataConversion(this.dataConversion);
/*  727 */     newMQCD.msgUserData = cloneByteArray(this.msgUserData);
/*  728 */     newMQCD.sendUserData = cloneByteArray(this.sendUserData);
/*  729 */     newMQCD.receiveUserData = cloneByteArray(this.receiveUserData);
/*  730 */     newMQCD.setMcaUserIdentifier(this.mcaUserIdentifier);
/*  731 */     newMQCD.setMcaType(this.mcaType);
/*  732 */     newMQCD.setRemoteUserIdentifier(this.remoteUserIdentifier);
/*  733 */     newMQCD.setRemotePassword(this.remotePassword);
/*  734 */     newMQCD.setMsgRetryExit(this.msgRetryExit);
/*  735 */     newMQCD.setMsgRetryUserData(this.msgRetryUserData);
/*  736 */     newMQCD.setMsgRetryCount(this.msgRetryCount);
/*  737 */     newMQCD.setMsgRetryInterval(this.msgRetryInterval);
/*  738 */     newMQCD.setBatchInterval(this.batchInterval);
/*  739 */     newMQCD.setNonPersistentMsgSpeed(this.nonPersistentMsgSpeed);
/*  740 */     newMQCD.setStrucLength(this.strucLength);
/*  741 */     newMQCD.setMsgExitsDefined(this.msgExitsDefined);
/*  742 */     newMQCD.msgExitPtr = cloneByteArray(this.msgExitPtr);
/*  743 */     newMQCD.msgUserDataPtr = cloneByteArray(this.msgUserDataPtr);
/*  744 */     newMQCD.setClustersDefined(this.clustersDefined);
/*  745 */     newMQCD.setNetworkPriority(this.networkPriority);
/*  746 */     newMQCD.mcaSecurityId = cloneByteArray(this.mcaSecurityId);
/*  747 */     newMQCD.remoteSecurityId = cloneByteArray(this.remoteSecurityId);
/*  748 */     newMQCD.setSslClientAuth(this.sslClientAuth);
/*  749 */     newMQCD.setBatchHeartbeat(this.batchHeartbeat);
/*  750 */     newMQCD.setClwlChannelRank(this.clwlChannelRank);
/*  751 */     newMQCD.setClwlChannelPriority(this.clwlChannelPriority);
/*  752 */     newMQCD.setClwlChannelWeight(this.clwlChannelWeight);
/*  753 */     newMQCD.setChannelMonitoring(this.channelMonitoring);
/*  754 */     newMQCD.setChannelStatistics(this.channelStatistics);
/*  755 */     newMQCD.setSharingConversations(this.SharingConversations);
/*  756 */     newMQCD.setPropertyControl(this.propertyControl);
/*  757 */     newMQCD.setMaxInstances(this.maxInstances);
/*  758 */     newMQCD.setMaxInstancesPerClient(this.maxInstancesPerClient);
/*  759 */     newMQCD.setClientChannelWeight(this.clientChannelWeight);
/*  760 */     newMQCD.setConnectionAffinity(this.connectionAffinity);
/*  761 */     newMQCD.setBatchDataLimit(this.batchDataLimit);
/*  762 */     newMQCD.setCertificateLabel(this.certificateLabel);
/*  763 */     newMQCD.setSPLProtection(this.splProtection);
/*      */     
/*  765 */     if (Trace.isOn) {
/*  766 */       Trace.exit(this, "com.ibm.mq.exits.MQCD", "clone()", newObject);
/*      */     }
/*  768 */     return newObject;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private byte[] cloneByteArray(byte[] toClone) {
/*  774 */     if (Trace.isOn) {
/*  775 */       Trace.entry(this, "com.ibm.mq.exits.MQCD", "cloneByteArray(byte [ ])", new Object[] { toClone });
/*      */     }
/*      */     
/*  778 */     if (toClone != null) {
/*  779 */       byte[] tempByte = new byte[toClone.length];
/*  780 */       System.arraycopy(toClone, 0, tempByte, 0, toClone.length);
/*  781 */       if (Trace.isOn) {
/*  782 */         Trace.exit(this, "com.ibm.mq.exits.MQCD", "cloneByteArray(byte [ ])", tempByte, 1);
/*      */       }
/*  784 */       return tempByte;
/*      */     } 
/*  786 */     if (Trace.isOn) {
/*  787 */       Trace.exit(this, "com.ibm.mq.exits.MQCD", "cloneByteArray(byte [ ])", null, 2);
/*      */     }
/*  789 */     return null;
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
/*      */   public int hashCode() {
/*  802 */     if (Trace.isOn) {
/*  803 */       Trace.entry(this, "com.ibm.mq.exits.MQCD", "hashCode()");
/*      */     }
/*  805 */     int hashCode = 0;
/*  806 */     hashCode += 31 * stringHashCode(this.channelName);
/*  807 */     hashCode += 37 * this.version;
/*  808 */     hashCode += 43 * this.channelType;
/*  809 */     hashCode += 47 * this.transportType;
/*  810 */     hashCode += 53 * stringHashCode(this.connectionName);
/*  811 */     hashCode += 59 * stringHashCode(this.securityExit);
/*  812 */     hashCode += 61 * Arrays.hashCode(this.securityUserData);
/*  813 */     hashCode += 73 * this.exitNameLength;
/*  814 */     hashCode += 79 * this.exitDataLength;
/*  815 */     hashCode += 83 * this.sendExitsDefined;
/*  816 */     hashCode += 89 * this.receiveExitsDefined;
/*  817 */     hashCode += 97 * Arrays.hashCode(this.sendExitPtr);
/*  818 */     hashCode += 103 * Arrays.hashCode(this.sendUserDataPtr);
/*  819 */     hashCode += 107 * Arrays.hashCode(this.receiveExitPtr);
/*  820 */     hashCode += 109 * Arrays.hashCode(this.receiveUserDataPtr);
/*  821 */     hashCode += 113 * stringHashCode(this.sslCipherSpec);
/*  822 */     hashCode += 127 * stringHashCode(this.sslPeerName);
/*  823 */     hashCode += 131 * stringHashCode(this.localAddress);
/*  824 */     hashCode += 137 * Arrays.hashCode(this.hdrCompList);
/*  825 */     hashCode += 149 * Arrays.hashCode(this.msgCompList);
/*  826 */     hashCode += 241 * stringHashCode(this.desc);
/*      */     
/*  828 */     hashCode += 257 * stringHashCode(this.userIdentifier);
/*  829 */     hashCode += 263 * stringHashCode(this.password, true);
/*  830 */     hashCode += 269 * this.keepAliveInterval;
/*  831 */     hashCode += 271 * stringHashCode(this.xmitQName);
/*  832 */     hashCode += 277 * stringHashCode(this.mcaName);
/*  833 */     hashCode += 281 * stringHashCode(this.modeName);
/*  834 */     hashCode += 283 * stringHashCode(this.tpName);
/*  835 */     hashCode += 293 * this.batchSize;
/*  836 */     hashCode += 307 * this.discInterval;
/*  837 */     hashCode += 311 * this.shortRetryCount;
/*  838 */     hashCode += 313 * this.shortRetryInterval;
/*  839 */     hashCode += 317 * this.longRetryCount;
/*  840 */     hashCode += 331 * this.longRetryInterval;
/*  841 */     hashCode += 337 * stringHashCode(this.msgExit);
/*  842 */     hashCode += 347 * stringHashCode(this.sendExit);
/*  843 */     hashCode += 349 * stringHashCode(this.receiveExit);
/*  844 */     hashCode += 353 * this.seqNumberWrap;
/*  845 */     hashCode += 359 * this.putAuthority;
/*  846 */     hashCode += 367 * this.dataConversion;
/*  847 */     hashCode += 373 * Arrays.hashCode(this.msgUserData);
/*  848 */     hashCode += 379 * Arrays.hashCode(this.sendUserData);
/*  849 */     hashCode += 383 * Arrays.hashCode(this.receiveUserData);
/*  850 */     hashCode += 389 * stringHashCode(this.mcaUserIdentifier);
/*  851 */     hashCode += 397 * this.mcaType;
/*  852 */     hashCode += 401 * stringHashCode(this.remoteUserIdentifier);
/*  853 */     hashCode += 409 * stringHashCode(this.remotePassword, true);
/*  854 */     hashCode += 419 * stringHashCode(this.msgRetryExit);
/*  855 */     hashCode += 421 * stringHashCode(this.msgRetryUserData);
/*  856 */     hashCode += 431 * this.msgRetryCount;
/*  857 */     hashCode += 433 * this.msgRetryInterval;
/*  858 */     hashCode += 439 * this.batchInterval;
/*  859 */     hashCode += 443 * this.nonPersistentMsgSpeed;
/*  860 */     hashCode += 457 * this.msgExitsDefined;
/*  861 */     hashCode += 461 * Arrays.hashCode(this.msgExitPtr);
/*  862 */     hashCode += 463 * Arrays.hashCode(this.msgUserDataPtr);
/*  863 */     hashCode += 467 * this.clustersDefined;
/*  864 */     hashCode += 479 * this.networkPriority;
/*  865 */     hashCode += 487 * this.sslClientAuth;
/*  866 */     hashCode += 491 * this.batchHeartbeat;
/*  867 */     hashCode += 499 * this.clwlChannelRank;
/*  868 */     hashCode += 503 * this.clwlChannelPriority;
/*  869 */     hashCode += 509 * this.clwlChannelWeight;
/*  870 */     hashCode += 521 * this.channelMonitoring;
/*  871 */     hashCode += 523 * this.channelStatistics;
/*  872 */     hashCode += 541 * Arrays.hashCode(this.mcaSecurityId);
/*  873 */     hashCode += 809 * Arrays.hashCode(this.remoteSecurityId);
/*  874 */     hashCode += 1093 * this.propertyControl;
/*  875 */     hashCode += 1097 * this.maxInstances;
/*  876 */     hashCode += 1103 * this.maxInstancesPerClient;
/*  877 */     hashCode += 1109 * this.clientChannelWeight;
/*  878 */     hashCode += 1117 * this.connectionAffinity;
/*  879 */     hashCode += 1123 * this.batchDataLimit;
/*  880 */     hashCode += 1129 * stringHashCode(this.certificateLabel);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  897 */     if (Trace.isOn) {
/*  898 */       Trace.data(this, "hashCode()", "HashCode computes to", Integer.valueOf(hashCode));
/*      */     }
/*      */     
/*  901 */     if (Trace.isOn) {
/*  902 */       Trace.exit(this, "com.ibm.mq.exits.MQCD", "hashCode()", Integer.valueOf(hashCode));
/*      */     }
/*  904 */     return hashCode;
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
/*      */   private static int stringHashCode(String s, boolean isPassword) {
/*  917 */     if (Trace.isOn) {
/*  918 */       Trace.entry("com.ibm.mq.exits.MQCD", "stringHashCode(String, boolean)", new Object[] { isPassword ? "********" : s, Boolean.valueOf(isPassword) });
/*      */     }
/*  920 */     int traceRet1 = (s == null) ? 0 : s.trim().hashCode();
/*  921 */     if (Trace.isOn) {
/*  922 */       Trace.exit("com.ibm.mq.exits.MQCD", "stringHashCode(String, boolean)", Integer.valueOf(traceRet1));
/*      */     }
/*  924 */     return traceRet1;
/*      */   }
/*      */ 
/*      */   
/*      */   private static int stringHashCode(String s) {
/*  929 */     if (Trace.isOn) {
/*  930 */       Trace.entry("com.ibm.mq.exits.MQCD", "stringHashCode(String)", new Object[] { s });
/*      */     }
/*  932 */     int traceRet1 = stringHashCode(s, false);
/*  933 */     if (Trace.isOn) {
/*  934 */       Trace.exit("com.ibm.mq.exits.MQCD", "stringHashCode(String)", Integer.valueOf(traceRet1));
/*      */     }
/*  936 */     return traceRet1;
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
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean equals(Object x) {
/*  952 */     if (Trace.isOn) {
/*  953 */       Trace.entry(this, "com.ibm.mq.exits.MQCD", "equals(Object)", new Object[] { x });
/*      */     }
/*  955 */     boolean areEqual = true;
/*  956 */     if (!(x instanceof MQCD)) {
/*  957 */       areEqual = false;
/*      */     } else {
/*      */       
/*  960 */       MQCD cd2 = (MQCD)x;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1039 */       areEqual = (stringEqual(this.desc, cd2.getDesc()) && stringEqual(this.channelName, cd2.getChannelName()) && this.version == cd2.getVersion() && this.channelType == cd2.getChannelType() && this.transportType == cd2.getTransportType() && stringEqual(this.connectionName, cd2.getConnectionName()) && stringEqual(this.securityExit, cd2.getSecurityExit()) && Arrays.equals(this.securityUserData, cd2.getSecurityUserDataBytes()) && stringEqual(this.password, cd2.getPassword(), true) && stringEqual(this.userIdentifier, cd2.getUserIdentifier()) && this.keepAliveInterval == cd2.getKeepAliveInterval() && this.exitNameLength == cd2.getExitNameLength() && this.exitDataLength == cd2.getExitDataLength() && this.sendExitsDefined == cd2.getSendExitsDefined() && this.receiveExitsDefined == cd2.getReceiveExitsDefined() && Arrays.equals(this.sendExitPtr, cd2.getSendExitPtrBytes()) && Arrays.equals(this.sendUserDataPtr, cd2.getSendUserDataPtrBytes()) && Arrays.equals(this.receiveExitPtr, cd2.getReceiveExitPtrBytes()) && Arrays.equals(this.receiveUserDataPtr, cd2.getReceiveUserDataPtrBytes()) && stringEqual(this.sslCipherSpec, cd2.getSslCipherSpec()) && stringEqualNonNull(this.sslPeerName, cd2.getSslPeerName()) && stringEqual(this.localAddress, cd2.getLocalAddress()) && Arrays.equals(this.hdrCompList, cd2.getHdrCompList()) && Arrays.equals(this.msgCompList, cd2.getMsgCompList()) && stringEqual(this.xmitQName, cd2.getXmitQName()) && stringEqual(this.mcaName, cd2.getMcaName()) && stringEqual(this.modeName, cd2.getModeName()) && stringEqual(this.tpName, cd2.getTpName()) && this.batchSize == cd2.getBatchSize() && this.discInterval == cd2.getDiscInterval() && this.shortRetryCount == cd2.getShortRetryCount() && this.shortRetryInterval == cd2.getShortRetryInterval() && this.longRetryCount == cd2.getLongRetryCount() && this.longRetryInterval == cd2.getLongRetryInterval() && stringEqual(this.msgExit, cd2.getMsgExit()) && stringEqual(this.sendExit, cd2.getSendExit()) && stringEqual(this.receiveExit, cd2.getReceiveExit()) && this.seqNumberWrap == cd2.getSeqNumberWrap() && this.putAuthority == cd2.getPutAuthority() && this.dataConversion == cd2.getDataConversion() && Arrays.equals(this.msgUserData, cd2.getMsgUserDataBytes()) && Arrays.equals(this.sendUserData, cd2.getSendUserDataBytes()) && Arrays.equals(this.receiveUserData, cd2.getReceiveUserDataBytes()) && stringEqual(this.mcaUserIdentifier, cd2.getMcaUserIdentifier()) && this.mcaType == cd2.getMcaType() && stringEqual(this.remoteUserIdentifier, cd2.getRemoteUserIdentifier()) && stringEqual(this.remotePassword, cd2.getRemotePassword(), true) && stringEqual(this.msgRetryExit, cd2.getMsgRetryExit()) && stringEqual(this.msgRetryUserData, cd2.getMsgRetryUserData()) && this.msgRetryCount == cd2.getMsgRetryCount() && this.msgRetryInterval == cd2.getMsgRetryInterval() && this.batchInterval == cd2.getBatchInterval() && this.nonPersistentMsgSpeed == cd2.getNonPersistentMsgSpeed() && this.msgExitsDefined == cd2.getMsgExitsDefined() && Arrays.equals(this.msgExitPtr, cd2.getMsgExitPtrBytes()) && Arrays.equals(this.msgUserDataPtr, cd2.getMsgUserDataPtrBytes()) && this.clustersDefined == cd2.getClustersDefined() && this.networkPriority == cd2.getNetworkPriority() && Arrays.equals(this.mcaSecurityId, cd2.getMcaSecurityId()) && Arrays.equals(this.remoteSecurityId, cd2.getRemoteSecurityId()) && this.sslClientAuth == cd2.getSslClientAuth() && this.batchHeartbeat == cd2.getBatchHeartbeat() && this.clwlChannelRank == cd2.getClwlChannelRank() && this.clwlChannelPriority == cd2.getClwlChannelPriority() && this.clwlChannelWeight == cd2.getClwlChannelWeight() && this.channelMonitoring == cd2.getChannelMonitoring() && this.channelStatistics == cd2.getChannelStatistics() && this.propertyControl == cd2.getPropertyControl() && this.maxInstances == cd2.getMaxInstances() && this.maxInstancesPerClient == cd2.getMaxInstancesPerClient() && this.clientChannelWeight == cd2.getClientChannelWeight() && this.connectionAffinity == cd2.getConnectionAffinity() && this.batchDataLimit == cd2.getBatchDataLimit() && stringEqual(this.certificateLabel, cd2.getCertificateLabel()) && this.splProtection == cd2.getSPLProtection());
/*      */     } 
/*      */     
/* 1042 */     if (Trace.isOn) {
/* 1043 */       Trace.exit(this, "com.ibm.mq.exits.MQCD", "equals(Object)", Boolean.valueOf(areEqual));
/*      */     }
/* 1045 */     return areEqual;
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
/*      */ 
/*      */   
/*      */   private static boolean stringEqual(String s1, String s2, boolean isPassword) {
/* 1060 */     if (Trace.isOn) {
/* 1061 */       Trace.entry("com.ibm.mq.exits.MQCD", "stringEqual(String,String,boolean)", new Object[] { isPassword ? "********" : s1, isPassword ? "********" : s2, 
/*      */             
/* 1063 */             Boolean.valueOf(isPassword) });
/*      */     }
/* 1065 */     if (s1 == null && s2 == null) {
/* 1066 */       if (Trace.isOn) {
/* 1067 */         Trace.exit("com.ibm.mq.exits.MQCD", "stringEqual(String,String,boolean)", Boolean.valueOf(true), 1);
/*      */       }
/* 1069 */       return true;
/*      */     } 
/*      */     
/* 1072 */     String ts1 = (s1 == null) ? "" : s1.trim();
/* 1073 */     String ts2 = (s2 == null) ? "" : s2.trim();
/*      */     
/* 1075 */     boolean retval = ts1.equals(ts2);
/* 1076 */     if (Trace.isOn) {
/* 1077 */       Trace.exit("com.ibm.mq.exits.MQCD", "stringEqual(String,String,boolean)", Boolean.valueOf(retval), 2);
/*      */     }
/* 1079 */     return retval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean stringEqual(String s1, String s2) {
/* 1088 */     if (Trace.isOn) {
/* 1089 */       Trace.entry("com.ibm.mq.exits.MQCD", "stringEqual(String,String)", new Object[] { s1, s2 });
/*      */     }
/* 1091 */     boolean traceRet1 = stringEqual(s1, s2, false);
/* 1092 */     if (Trace.isOn) {
/* 1093 */       Trace.exit("com.ibm.mq.exits.MQCD", "stringEqual(String,String)", Boolean.valueOf(traceRet1));
/*      */     }
/* 1095 */     return traceRet1;
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
/*      */   
/*      */   private static boolean stringEqualNonNull(String s1, String s2, boolean isPassword) {
/* 1109 */     if (Trace.isOn) {
/* 1110 */       Trace.entry("com.ibm.mq.exits.MQCD", "stringEqualNonNull(String,String,boolean)", new Object[] { isPassword ? "********" : s1, isPassword ? "********" : s2, 
/*      */             
/* 1112 */             Boolean.valueOf(isPassword) });
/*      */     }
/* 1114 */     if (s1 == null && s2 == null) {
/* 1115 */       if (Trace.isOn) {
/* 1116 */         Trace.exit("com.ibm.mq.exits.MQCD", "stringEqualNonNull(String,String,boolean)", Boolean.valueOf(true), 1);
/*      */       }
/* 1118 */       return true;
/*      */     } 
/*      */     
/* 1121 */     if (s1 == null || s2 == null) {
/* 1122 */       if (Trace.isOn) {
/* 1123 */         Trace.exit("com.ibm.mq.exits.MQCD", "stringEqualNonNull(String,String,boolean)", Boolean.valueOf(false), 2);
/*      */       }
/* 1125 */       return false;
/*      */     } 
/*      */     
/* 1128 */     String ts1 = s1.trim();
/* 1129 */     String ts2 = s2.trim();
/*      */     
/* 1131 */     boolean retval = ts1.equals(ts2);
/* 1132 */     if (Trace.isOn) {
/* 1133 */       Trace.exit("com.ibm.mq.exits.MQCD", "stringEqualNonNull(String,String,boolean)", Boolean.valueOf(retval), 3);
/*      */     }
/*      */     
/* 1136 */     return retval;
/*      */   }
/*      */   
/*      */   private static boolean stringEqualNonNull(String s1, String s2) {
/* 1140 */     if (Trace.isOn) {
/* 1141 */       Trace.entry("com.ibm.mq.exits.MQCD", "stringEqualNonNull(String,String)", new Object[] { s1, s2 });
/*      */     }
/*      */     
/* 1144 */     boolean traceRet1 = stringEqualNonNull(s1, s2, false);
/* 1145 */     if (Trace.isOn) {
/* 1146 */       Trace.exit("com.ibm.mq.exits.MQCD", "stringEqualNonNull(String,String)", 
/* 1147 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 1149 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getBatchHeartbeat() {
/* 1158 */     if (Trace.isOn) {
/* 1159 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getBatchHeartbeat()", "getter", 
/* 1160 */           Integer.valueOf(this.batchHeartbeat));
/*      */     }
/* 1162 */     return this.batchHeartbeat;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBatchHeartbeat(int batchHeartbeat) {
/* 1171 */     if (Trace.isOn) {
/* 1172 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setBatchHeartbeat(int)", "setter", 
/* 1173 */           Integer.valueOf(batchHeartbeat));
/*      */     }
/* 1175 */     this.batchHeartbeat = batchHeartbeat;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getBatchInterval() {
/* 1185 */     if (Trace.isOn) {
/* 1186 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getBatchInterval()", "getter", 
/* 1187 */           Integer.valueOf(this.batchInterval));
/*      */     }
/* 1189 */     return this.batchInterval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBatchInterval(int batchInterval) {
/* 1198 */     if (Trace.isOn) {
/* 1199 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setBatchInterval(int)", "setter", 
/* 1200 */           Integer.valueOf(batchInterval));
/*      */     }
/* 1202 */     this.batchInterval = batchInterval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getBatchSize() {
/* 1212 */     if (Trace.isOn) {
/* 1213 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getBatchSize()", "getter", 
/* 1214 */           Integer.valueOf(this.batchSize));
/*      */     }
/* 1216 */     return this.batchSize;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBatchSize(int batchSize) {
/* 1225 */     if (Trace.isOn) {
/* 1226 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setBatchSize(int)", "setter", 
/* 1227 */           Integer.valueOf(batchSize));
/*      */     }
/* 1229 */     this.batchSize = batchSize;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getChannelMonitoring() {
/* 1239 */     if (Trace.isOn) {
/* 1240 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getChannelMonitoring()", "getter", 
/* 1241 */           Integer.valueOf(this.channelMonitoring));
/*      */     }
/* 1243 */     return this.channelMonitoring;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setChannelMonitoring(int channelMonitoring) {
/* 1252 */     if (Trace.isOn) {
/* 1253 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setChannelMonitoring(int)", "setter", 
/* 1254 */           Integer.valueOf(channelMonitoring));
/*      */     }
/* 1256 */     this.channelMonitoring = channelMonitoring;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getChannelName() {
/* 1266 */     if (Trace.isOn) {
/* 1267 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getChannelName()", "getter", this.channelName);
/*      */     }
/* 1269 */     return this.channelName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setChannelName(String channelName) {
/* 1278 */     if (Trace.isOn) {
/* 1279 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setChannelName(String)", "setter", channelName);
/*      */     }
/* 1281 */     this.channelName = channelName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getChannelStatistics() {
/* 1291 */     if (Trace.isOn) {
/* 1292 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getChannelStatistics()", "getter", 
/* 1293 */           Integer.valueOf(this.channelStatistics));
/*      */     }
/* 1295 */     return this.channelStatistics;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setChannelStatistics(int channelStatistics) {
/* 1304 */     if (Trace.isOn) {
/* 1305 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setChannelStatistics(int)", "setter", 
/* 1306 */           Integer.valueOf(channelStatistics));
/*      */     }
/* 1308 */     this.channelStatistics = channelStatistics;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getChannelType() {
/* 1318 */     if (Trace.isOn) {
/* 1319 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getChannelType()", "getter", 
/* 1320 */           Integer.valueOf(this.channelType));
/*      */     }
/* 1322 */     return this.channelType;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setChannelType(int channelType) {
/* 1331 */     if (Trace.isOn) {
/* 1332 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setChannelType(int)", "setter", 
/* 1333 */           Integer.valueOf(channelType));
/*      */     }
/* 1335 */     this.channelType = channelType;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getClustersDefined() {
/* 1345 */     if (Trace.isOn) {
/* 1346 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getClustersDefined()", "getter", 
/* 1347 */           Integer.valueOf(this.clustersDefined));
/*      */     }
/* 1349 */     return this.clustersDefined;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setClustersDefined(int clustersDefined) {
/* 1358 */     if (Trace.isOn) {
/* 1359 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setClustersDefined(int)", "setter", 
/* 1360 */           Integer.valueOf(clustersDefined));
/*      */     }
/* 1362 */     this.clustersDefined = clustersDefined;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getClwlChannelPriority() {
/* 1372 */     if (Trace.isOn) {
/* 1373 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getClwlChannelPriority()", "getter", 
/* 1374 */           Integer.valueOf(this.clwlChannelPriority));
/*      */     }
/* 1376 */     return this.clwlChannelPriority;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setClwlChannelPriority(int clwlChannelPriority) {
/* 1385 */     if (Trace.isOn) {
/* 1386 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setClwlChannelPriority(int)", "setter", 
/* 1387 */           Integer.valueOf(clwlChannelPriority));
/*      */     }
/* 1389 */     this.clwlChannelPriority = clwlChannelPriority;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getClwlChannelRank() {
/* 1399 */     if (Trace.isOn) {
/* 1400 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getClwlChannelRank()", "getter", 
/* 1401 */           Integer.valueOf(this.clwlChannelRank));
/*      */     }
/* 1403 */     return this.clwlChannelRank;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setClwlChannelRank(int clwlChannelRank) {
/* 1412 */     if (Trace.isOn) {
/* 1413 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setClwlChannelRank(int)", "setter", 
/* 1414 */           Integer.valueOf(clwlChannelRank));
/*      */     }
/* 1416 */     this.clwlChannelRank = clwlChannelRank;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getClwlChannelWeight() {
/* 1426 */     if (Trace.isOn) {
/* 1427 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getClwlChannelWeight()", "getter", 
/* 1428 */           Integer.valueOf(this.clwlChannelWeight));
/*      */     }
/* 1430 */     return this.clwlChannelWeight;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setClwlChannelWeight(int clwlChannelWeight) {
/* 1439 */     if (Trace.isOn) {
/* 1440 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setClwlChannelWeight(int)", "setter", 
/* 1441 */           Integer.valueOf(clwlChannelWeight));
/*      */     }
/* 1443 */     this.clwlChannelWeight = clwlChannelWeight;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getConnectionName() {
/* 1453 */     if (Trace.isOn) {
/* 1454 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getConnectionName()", "getter", this.connectionName);
/*      */     }
/* 1456 */     return this.connectionName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setConnectionName(String connectionName) {
/* 1465 */     if (Trace.isOn) {
/* 1466 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setConnectionName(String)", "setter", connectionName);
/*      */     }
/*      */     
/* 1469 */     this.connectionName = connectionName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getDataConversion() {
/* 1479 */     if (Trace.isOn) {
/* 1480 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getDataConversion()", "getter", 
/* 1481 */           Integer.valueOf(this.dataConversion));
/*      */     }
/* 1483 */     return this.dataConversion;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDataConversion(int dataConversion) {
/* 1492 */     if (Trace.isOn) {
/* 1493 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setDataConversion(int)", "setter", 
/* 1494 */           Integer.valueOf(dataConversion));
/*      */     }
/* 1496 */     this.dataConversion = dataConversion;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDesc() {
/* 1506 */     if (Trace.isOn) {
/* 1507 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getDesc()", "getter", this.desc);
/*      */     }
/* 1509 */     return this.desc;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDesc(String desc) {
/* 1518 */     if (Trace.isOn) {
/* 1519 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setDesc(String)", "setter", desc);
/*      */     }
/* 1521 */     this.desc = desc;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getDiscInterval() {
/* 1531 */     if (Trace.isOn) {
/* 1532 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getDiscInterval()", "getter", 
/* 1533 */           Integer.valueOf(this.discInterval));
/*      */     }
/* 1535 */     return this.discInterval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDiscInterval(int discInterval) {
/* 1544 */     if (Trace.isOn) {
/* 1545 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setDiscInterval(int)", "setter", 
/* 1546 */           Integer.valueOf(discInterval));
/*      */     }
/* 1548 */     this.discInterval = discInterval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int[] getHdrCompList() {
/* 1558 */     if (Trace.isOn) {
/* 1559 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getHdrCompList()", "getter", this.hdrCompList);
/*      */     }
/* 1561 */     return this.hdrCompList;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setHdrCompList(int[] hdrCompList) {
/* 1570 */     if (Trace.isOn) {
/* 1571 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setHdrCompList(int [ ])", "setter", hdrCompList);
/*      */     }
/*      */     
/* 1574 */     if (hdrCompList == null) {
/* 1575 */       Arrays.fill(this.hdrCompList, -1);
/* 1576 */       this.hdrCompList[0] = 0;
/*      */     }
/*      */     else {
/*      */       
/* 1580 */       for (int i = 0; i < this.hdrCompList.length; i++) {
/* 1581 */         if (hdrCompList.length > i) {
/* 1582 */           this.hdrCompList[i] = (hdrCompList[i] >= 255) ? -1 : hdrCompList[i];
/*      */         } else {
/*      */           
/* 1585 */           this.hdrCompList[i] = -1;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getHeartbeatInterval() {
/* 1598 */     if (Trace.isOn) {
/* 1599 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getHeartbeatInterval()", "getter", 
/* 1600 */           Integer.valueOf(this.heartbeatInterval));
/*      */     }
/* 1602 */     return this.heartbeatInterval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setHeartbeatInterval(int heartbeatInterval) {
/* 1611 */     if (Trace.isOn) {
/* 1612 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setHeartbeatInterval(int)", "setter", 
/* 1613 */           Integer.valueOf(heartbeatInterval));
/*      */     }
/* 1615 */     this.heartbeatInterval = heartbeatInterval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getKeepAliveInterval() {
/* 1625 */     if (Trace.isOn) {
/* 1626 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getKeepAliveInterval()", "getter", 
/* 1627 */           Integer.valueOf(this.keepAliveInterval));
/*      */     }
/* 1629 */     return this.keepAliveInterval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setKeepAliveInterval(int keepAliveInterval) {
/* 1638 */     if (Trace.isOn) {
/* 1639 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setKeepAliveInterval(int)", "setter", 
/* 1640 */           Integer.valueOf(keepAliveInterval));
/*      */     }
/* 1642 */     this.keepAliveInterval = keepAliveInterval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getLocalAddress() {
/* 1652 */     if (Trace.isOn) {
/* 1653 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getLocalAddress()", "getter", this.localAddress);
/*      */     }
/* 1655 */     return this.localAddress;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLocalAddress(String localAddress) {
/* 1664 */     if (Trace.isOn) {
/* 1665 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setLocalAddress(String)", "setter", localAddress);
/*      */     }
/* 1667 */     this.localAddress = localAddress;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getLongRetryCount() {
/* 1677 */     if (Trace.isOn) {
/* 1678 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getLongRetryCount()", "getter", 
/* 1679 */           Integer.valueOf(this.longRetryCount));
/*      */     }
/* 1681 */     return this.longRetryCount;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLongRetryCount(int longRetryCount) {
/* 1690 */     if (Trace.isOn) {
/* 1691 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setLongRetryCount(int)", "setter", 
/* 1692 */           Integer.valueOf(longRetryCount));
/*      */     }
/* 1694 */     this.longRetryCount = longRetryCount;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getLongRetryInterval() {
/* 1704 */     if (Trace.isOn) {
/* 1705 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getLongRetryInterval()", "getter", 
/* 1706 */           Integer.valueOf(this.longRetryInterval));
/*      */     }
/* 1708 */     return this.longRetryInterval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLongRetryInterval(int longRetryInterval) {
/* 1717 */     if (Trace.isOn) {
/* 1718 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setLongRetryInterval(int)", "setter", 
/* 1719 */           Integer.valueOf(longRetryInterval));
/*      */     }
/* 1721 */     this.longRetryInterval = longRetryInterval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxMsgLength() {
/* 1731 */     if (Trace.isOn) {
/* 1732 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getMaxMsgLength()", "getter", 
/* 1733 */           Integer.valueOf(this.maxMsgLength));
/*      */     }
/* 1735 */     return this.maxMsgLength;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMaxMsgLength(int maxMsgLength) {
/* 1744 */     if (Trace.isOn) {
/* 1745 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setMaxMsgLength(int)", "setter", 
/* 1746 */           Integer.valueOf(maxMsgLength));
/*      */     }
/* 1748 */     this.maxMsgLength = maxMsgLength;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getMcaName() {
/* 1758 */     if (Trace.isOn) {
/* 1759 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getMcaName()", "getter", this.mcaName);
/*      */     }
/* 1761 */     return this.mcaName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMcaName(String mcaName) {
/* 1770 */     if (Trace.isOn) {
/* 1771 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setMcaName(String)", "setter", mcaName);
/*      */     }
/* 1773 */     this.mcaName = mcaName;
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
/*      */   public byte[] getMcaSecurityId() {
/* 1785 */     if (Trace.isOn) {
/* 1786 */       Trace.data(this, "getMcaSecurityId()", this.mcaSecurityId);
/*      */     }
/* 1788 */     return this.mcaSecurityId;
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
/*      */   public void setMcaSecurityId(byte[] mcaSecurityId) {
/* 1801 */     if (Trace.isOn) {
/* 1802 */       Trace.data(this, "setMcaSecurityId(byte [ ])", mcaSecurityId);
/*      */     }
/* 1804 */     JmqiTools.byteArrayCopy(mcaSecurityId, 0, this.mcaSecurityId, 0, 40);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMcaType() {
/* 1813 */     if (Trace.isOn) {
/* 1814 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getMcaType()", "getter", Integer.valueOf(this.mcaType));
/*      */     }
/* 1816 */     return this.mcaType;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMcaType(int mcaType) {
/* 1825 */     if (Trace.isOn) {
/* 1826 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setMcaType(int)", "setter", 
/* 1827 */           Integer.valueOf(mcaType));
/*      */     }
/* 1829 */     this.mcaType = mcaType;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getMcaUserIdentifier() {
/* 1839 */     if (Trace.isOn) {
/* 1840 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getMcaUserIdentifier()", "getter", this.mcaUserIdentifier);
/*      */     }
/*      */     
/* 1843 */     return this.mcaUserIdentifier;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMcaUserIdentifier(String mcaUserIdentifier) {
/* 1852 */     if (Trace.isOn) {
/* 1853 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setMcaUserIdentifier(String)", "setter", mcaUserIdentifier);
/*      */     }
/*      */     
/* 1856 */     this.mcaUserIdentifier = mcaUserIdentifier;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getModeName() {
/* 1866 */     if (Trace.isOn) {
/* 1867 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getModeName()", "getter", this.modeName);
/*      */     }
/* 1869 */     return this.modeName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setModeName(String modeName) {
/* 1878 */     if (Trace.isOn) {
/* 1879 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setModeName(String)", "setter", modeName);
/*      */     }
/* 1881 */     this.modeName = modeName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int[] getMsgCompList() {
/* 1891 */     if (Trace.isOn) {
/* 1892 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getMsgCompList()", "getter", this.msgCompList);
/*      */     }
/* 1894 */     return this.msgCompList;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMsgCompList(int[] msgCompList) {
/* 1903 */     if (Trace.isOn) {
/* 1904 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setMsgCompList(int [ ])", "setter", msgCompList);
/*      */     }
/*      */     
/* 1907 */     if (msgCompList == null) {
/* 1908 */       Arrays.fill(this.msgCompList, -1);
/* 1909 */       this.msgCompList[0] = 0;
/*      */     }
/*      */     else {
/*      */       
/* 1913 */       for (int i = 0; i < this.msgCompList.length; i++) {
/* 1914 */         if (msgCompList.length > i) {
/* 1915 */           this.msgCompList[i] = (msgCompList[i] >= 255) ? -1 : msgCompList[i];
/*      */         } else {
/*      */           
/* 1918 */           this.msgCompList[i] = -1;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMsgExitsDefined() {
/* 1931 */     if (Trace.isOn) {
/* 1932 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getMsgExitsDefined()", "getter", 
/* 1933 */           Integer.valueOf(this.msgExitsDefined));
/*      */     }
/* 1935 */     return this.msgExitsDefined;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMsgExitsDefined(int msgExitsDefined) {
/* 1944 */     if (Trace.isOn) {
/* 1945 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setMsgExitsDefined(int)", "setter", 
/* 1946 */           Integer.valueOf(msgExitsDefined));
/*      */     }
/* 1948 */     this.msgExitsDefined = msgExitsDefined;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMsgRetryCount() {
/* 1958 */     if (Trace.isOn) {
/* 1959 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getMsgRetryCount()", "getter", 
/* 1960 */           Integer.valueOf(this.msgRetryCount));
/*      */     }
/* 1962 */     return this.msgRetryCount;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMsgRetryCount(int msgRetryCount) {
/* 1971 */     if (Trace.isOn) {
/* 1972 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setMsgRetryCount(int)", "setter", 
/* 1973 */           Integer.valueOf(msgRetryCount));
/*      */     }
/* 1975 */     this.msgRetryCount = msgRetryCount;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getMsgRetryExit() {
/* 1985 */     if (Trace.isOn) {
/* 1986 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getMsgRetryExit()", "getter", this.msgRetryExit);
/*      */     }
/* 1988 */     return this.msgRetryExit;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMsgRetryExit(String msgRetryExit) {
/* 1997 */     if (Trace.isOn) {
/* 1998 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setMsgRetryExit(String)", "setter", msgRetryExit);
/*      */     }
/* 2000 */     this.msgRetryExit = msgRetryExit;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMsgRetryInterval() {
/* 2010 */     if (Trace.isOn) {
/* 2011 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getMsgRetryInterval()", "getter", 
/* 2012 */           Integer.valueOf(this.msgRetryInterval));
/*      */     }
/* 2014 */     return this.msgRetryInterval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMsgRetryInterval(int msgRetryInterval) {
/* 2023 */     if (Trace.isOn) {
/* 2024 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setMsgRetryInterval(int)", "setter", 
/* 2025 */           Integer.valueOf(msgRetryInterval));
/*      */     }
/* 2027 */     this.msgRetryInterval = msgRetryInterval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getMsgRetryUserData() {
/* 2037 */     if (Trace.isOn) {
/* 2038 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getMsgRetryUserData()", "getter", this.msgRetryUserData);
/*      */     }
/*      */     
/* 2041 */     return this.msgRetryUserData;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMsgRetryUserData(String msgRetryUserData) {
/* 2049 */     if (Trace.isOn) {
/* 2050 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setMsgRetryUserData(String)", "setter", msgRetryUserData);
/*      */     }
/*      */     
/* 2053 */     this.msgRetryUserData = msgRetryUserData;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNetworkPriority() {
/* 2063 */     if (Trace.isOn) {
/* 2064 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getNetworkPriority()", "getter", 
/* 2065 */           Integer.valueOf(this.networkPriority));
/*      */     }
/* 2067 */     return this.networkPriority;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setNetworkPriority(int networkPriority) {
/* 2076 */     if (Trace.isOn) {
/* 2077 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setNetworkPriority(int)", "setter", 
/* 2078 */           Integer.valueOf(networkPriority));
/*      */     }
/* 2080 */     this.networkPriority = networkPriority;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNonPersistentMsgSpeed() {
/* 2090 */     if (Trace.isOn) {
/* 2091 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getNonPersistentMsgSpeed()", "getter", 
/* 2092 */           Integer.valueOf(this.nonPersistentMsgSpeed));
/*      */     }
/* 2094 */     return this.nonPersistentMsgSpeed;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setNonPersistentMsgSpeed(int nonPersistentMsgSpeed) {
/* 2103 */     if (Trace.isOn) {
/* 2104 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setNonPersistentMsgSpeed(int)", "setter", 
/* 2105 */           Integer.valueOf(nonPersistentMsgSpeed));
/*      */     }
/* 2107 */     this.nonPersistentMsgSpeed = nonPersistentMsgSpeed;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getPassword() {
/* 2118 */     if (Trace.isOn) {
/* 2119 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getPassword()", "getter", (this.password == null) ? null : "********");
/*      */     }
/* 2121 */     return this.password;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPassword(String password) {
/* 2131 */     if (Trace.isOn) {
/* 2132 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setPassword(String)", "setter", (password == null) ? null : "********");
/*      */     }
/* 2134 */     this.password = password;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPutAuthority() {
/* 2144 */     if (Trace.isOn) {
/* 2145 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getPutAuthority()", "getter", 
/* 2146 */           Integer.valueOf(this.putAuthority));
/*      */     }
/* 2148 */     return this.putAuthority;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPutAuthority(int putAuthority) {
/* 2157 */     if (Trace.isOn) {
/* 2158 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setPutAuthority(int)", "setter", 
/* 2159 */           Integer.valueOf(putAuthority));
/*      */     }
/* 2161 */     this.putAuthority = putAuthority;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getQMgrName() {
/* 2171 */     if (Trace.isOn) {
/* 2172 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getQMgrName()", "getter", this.qMgrName);
/*      */     }
/* 2174 */     return this.qMgrName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setQMgrName(String qMgrName) {
/* 2183 */     if (Trace.isOn) {
/* 2184 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setQMgrName(String)", "setter", qMgrName);
/*      */     }
/* 2186 */     this.qMgrName = qMgrName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getReceiveExitsDefined() {
/* 2196 */     if (Trace.isOn) {
/* 2197 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getReceiveExitsDefined()", "getter", 
/* 2198 */           Integer.valueOf(this.receiveExitsDefined));
/*      */     }
/* 2200 */     return this.receiveExitsDefined;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReceiveExitsDefined(int receiveExitsDefined) {
/* 2209 */     if (Trace.isOn) {
/* 2210 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setReceiveExitsDefined(int)", "setter", 
/* 2211 */           Integer.valueOf(receiveExitsDefined));
/*      */     }
/* 2213 */     this.receiveExitsDefined = receiveExitsDefined;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getRemotePassword() {
/* 2224 */     if (Trace.isOn) {
/* 2225 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getRemotePassword()", "getter", (this.remotePassword == null) ? null : "********");
/*      */     }
/* 2227 */     return this.remotePassword;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRemotePassword(String remotePassword) {
/* 2237 */     if (Trace.isOn) {
/* 2238 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setRemotePassword(String)", "setter", (remotePassword == null) ? null : "********");
/*      */     }
/*      */ 
/*      */     
/* 2242 */     this.remotePassword = remotePassword;
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
/*      */   public byte[] getRemoteSecurityId() {
/* 2254 */     if (Trace.isOn) {
/* 2255 */       Trace.data(this, "getRemoteSecurityId()", this.remoteSecurityId);
/*      */     }
/* 2257 */     return this.remoteSecurityId;
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
/*      */   public void setRemoteSecurityId(byte[] remoteSecurityId) {
/* 2270 */     if (Trace.isOn) {
/* 2271 */       Trace.data(this, "setRemoteSecurityId(byte [ ])", remoteSecurityId);
/*      */     }
/* 2273 */     JmqiTools.byteArrayCopy(remoteSecurityId, 0, this.remoteSecurityId, 0, 40);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getRemoteUserIdentifier() {
/* 2282 */     if (Trace.isOn) {
/* 2283 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getRemoteUserIdentifier()", "getter", this.remoteUserIdentifier);
/*      */     }
/*      */     
/* 2286 */     return this.remoteUserIdentifier;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRemoteUserIdentifier(String remoteUserIdentifier) {
/* 2295 */     if (Trace.isOn) {
/* 2296 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setRemoteUserIdentifier(String)", "setter", remoteUserIdentifier);
/*      */     }
/*      */     
/* 2299 */     this.remoteUserIdentifier = remoteUserIdentifier;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getSecurityExit() {
/* 2309 */     if (Trace.isOn) {
/* 2310 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getSecurityExit()", "getter", this.securityExit);
/*      */     }
/* 2312 */     return this.securityExit;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSecurityExit(String securityExit) {
/* 2321 */     if (Trace.isOn) {
/* 2322 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setSecurityExit(String)", "setter", securityExit);
/*      */     }
/* 2324 */     this.securityExit = securityExit;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getSecurityUserData() {
/* 2334 */     if (Trace.isOn) {
/* 2335 */       Trace.entry(this, "com.ibm.mq.exits.MQCD", "getSecurityUserData()");
/*      */     }
/*      */     try {
/* 2338 */       String traceRet1 = (this.securityUserData == null) ? null : newString(this.securityUserData);
/*      */       
/* 2340 */       if (Trace.isOn) {
/* 2341 */         Trace.exit(this, "com.ibm.mq.exits.MQCD", "getSecurityUserData()", traceRet1, 1);
/*      */       }
/* 2343 */       return traceRet1;
/*      */     }
/* 2345 */     catch (Exception e) {
/* 2346 */       if (Trace.isOn) {
/* 2347 */         Trace.catchBlock(this, "com.ibm.mq.exits.MQCD", "getSecurityUserData()", e);
/*      */       }
/*      */       
/* 2350 */       if (Trace.isOn) {
/* 2351 */         Trace.exit(this, "com.ibm.mq.exits.MQCD", "getSecurityUserData()", null, 2);
/*      */       }
/* 2353 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getSecurityUserDataBytes() {
/* 2363 */     if (Trace.isOn) {
/* 2364 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getSecurityUserDataBytes()", "getter", this.securityUserData);
/*      */     }
/*      */     
/* 2367 */     return this.securityUserData;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSecurityUserData(String securityUserData) {
/* 2375 */     if (Trace.isOn) {
/* 2376 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setSecurityUserData(String)", "setter", securityUserData);
/*      */     }
/*      */     
/* 2379 */     spaceFilledByteCopy(securityUserData, this.securityUserData);
/*      */   }
/*      */   
/*      */   void setSecurityUserData(byte[] securityUserData) {
/* 2383 */     if (Trace.isOn) {
/* 2384 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setSecurityUserData(byte [ ])", "setter", securityUserData);
/*      */     }
/*      */     
/* 2387 */     System.arraycopy(securityUserData, 0, this.securityUserData, 0, this.exitDataLength);
/*      */   }
/*      */   
/* 2390 */   private static byte[] nullByteArray = new byte[0]; private static byte aSpace;
/*      */   
/*      */   private void spaceFilledByteCopy(String s, byte[] destination) {
/* 2393 */     if (Trace.isOn) {
/* 2394 */       Trace.entry(this, "com.ibm.mq.exits.MQCD", "spaceFilledByteCopy(String,byte [ ])", new Object[] { s, destination });
/*      */     }
/*      */     
/* 2397 */     byte[] source = nullByteArray;
/* 2398 */     if (s != null) {
/* 2399 */       source = getBytes(s);
/*      */     }
/* 2401 */     for (int i = 0; i < destination.length; i++) {
/* 2402 */       if (i < source.length) {
/* 2403 */         destination[i] = source[i];
/*      */       } else {
/*      */         
/* 2406 */         destination[i] = 32;
/*      */       } 
/*      */     } 
/*      */     
/* 2410 */     if (Trace.isOn) {
/* 2411 */       Trace.exit(this, "com.ibm.mq.exits.MQCD", "spaceFilledByteCopy(String,byte [ ])");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getSendExitsDefined() {
/* 2422 */     if (Trace.isOn) {
/* 2423 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getSendExitsDefined()", "getter", 
/* 2424 */           Integer.valueOf(this.sendExitsDefined));
/*      */     }
/* 2426 */     return this.sendExitsDefined;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSendExitsDefined(int sendExitsDefined) {
/* 2435 */     if (Trace.isOn) {
/* 2436 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setSendExitsDefined(int)", "setter", 
/* 2437 */           Integer.valueOf(sendExitsDefined));
/*      */     }
/* 2439 */     this.sendExitsDefined = sendExitsDefined;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getSeqNumberWrap() {
/* 2449 */     if (Trace.isOn) {
/* 2450 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getSeqNumberWrap()", "getter", 
/* 2451 */           Integer.valueOf(this.seqNumberWrap));
/*      */     }
/* 2453 */     return this.seqNumberWrap;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSeqNumberWrap(int seqNumberWrap) {
/* 2462 */     if (Trace.isOn) {
/* 2463 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setSeqNumberWrap(int)", "setter", 
/* 2464 */           Integer.valueOf(seqNumberWrap));
/*      */     }
/* 2466 */     this.seqNumberWrap = seqNumberWrap;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getShortRetryCount() {
/* 2476 */     if (Trace.isOn) {
/* 2477 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getShortRetryCount()", "getter", 
/* 2478 */           Integer.valueOf(this.shortRetryCount));
/*      */     }
/* 2480 */     return this.shortRetryCount;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setShortRetryCount(int shortRetryCount) {
/* 2489 */     if (Trace.isOn) {
/* 2490 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setShortRetryCount(int)", "setter", 
/* 2491 */           Integer.valueOf(shortRetryCount));
/*      */     }
/* 2493 */     this.shortRetryCount = shortRetryCount;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getShortRetryInterval() {
/* 2503 */     if (Trace.isOn) {
/* 2504 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getShortRetryInterval()", "getter", 
/* 2505 */           Integer.valueOf(this.shortRetryInterval));
/*      */     }
/* 2507 */     return this.shortRetryInterval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setShortRetryInterval(int shortRetryInterval) {
/* 2516 */     if (Trace.isOn) {
/* 2517 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setShortRetryInterval(int)", "setter", 
/* 2518 */           Integer.valueOf(shortRetryInterval));
/*      */     }
/* 2520 */     this.shortRetryInterval = shortRetryInterval;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getSPLProtection() {
/* 2525 */     if (Trace.isOn) {
/* 2526 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getSPLProtection()", "getter", 
/* 2527 */           Integer.valueOf(this.splProtection));
/*      */     }
/* 2529 */     return this.splProtection;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setSPLProtection(int splProtection) {
/* 2534 */     if (Trace.isOn) {
/* 2535 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setSPLProtection(int)", "setter", 
/* 2536 */           Integer.valueOf(splProtection));
/*      */     }
/* 2538 */     this.splProtection = splProtection;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getSslCipherSpec() {
/* 2548 */     if (Trace.isOn) {
/* 2549 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getSslCipherSpec()", "getter", this.sslCipherSpec);
/*      */     }
/* 2551 */     return this.sslCipherSpec;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSslCipherSpec(String sslCipherSpec) {
/* 2560 */     if (Trace.isOn) {
/* 2561 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setSslCipherSpec(String)", "setter", sslCipherSpec);
/*      */     }
/*      */     
/* 2564 */     this.sslCipherSpec = sslCipherSpec;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getSslClientAuth() {
/* 2574 */     if (Trace.isOn) {
/* 2575 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getSslClientAuth()", "getter", 
/* 2576 */           Integer.valueOf(this.sslClientAuth));
/*      */     }
/* 2578 */     return this.sslClientAuth;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSslClientAuth(int sslClientAuth) {
/* 2587 */     if (Trace.isOn) {
/* 2588 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setSslClientAuth(int)", "setter", 
/* 2589 */           Integer.valueOf(sslClientAuth));
/*      */     }
/* 2591 */     this.sslClientAuth = sslClientAuth;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getSslPeerName() {
/* 2601 */     if (Trace.isOn) {
/* 2602 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getSslPeerName()", "getter", this.sslPeerName);
/*      */     }
/* 2604 */     return this.sslPeerName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public int getStrucLength() {
/* 2616 */     if (Trace.isOn) {
/* 2617 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getStrucLength()", "getter", 
/* 2618 */           Integer.valueOf(this.strucLength));
/*      */     }
/* 2620 */     return this.strucLength;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void setStrucLength(int strucLength) {
/* 2631 */     if (Trace.isOn) {
/* 2632 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setStrucLength(int)", "setter", 
/* 2633 */           Integer.valueOf(strucLength));
/*      */     }
/* 2635 */     this.strucLength = strucLength;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSslPeerName(String sslPeerName) {
/* 2644 */     if (Trace.isOn) {
/* 2645 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setSslPeerName(String)", "setter", sslPeerName);
/*      */     }
/* 2647 */     this.sslPeerName = sslPeerName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getTpName() {
/* 2657 */     if (Trace.isOn) {
/* 2658 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getTpName()", "getter", this.tpName);
/*      */     }
/* 2660 */     return this.tpName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTpName(String tpName) {
/* 2669 */     if (Trace.isOn) {
/* 2670 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setTpName(String)", "setter", tpName);
/*      */     }
/* 2672 */     this.tpName = tpName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getTransportType() {
/* 2682 */     if (Trace.isOn) {
/* 2683 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getTransportType()", "getter", 
/* 2684 */           Integer.valueOf(this.transportType));
/*      */     }
/* 2686 */     return this.transportType;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTransportType(int transportType) {
/* 2695 */     if (Trace.isOn) {
/* 2696 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setTransportType(int)", "setter", 
/* 2697 */           Integer.valueOf(transportType));
/*      */     }
/* 2699 */     this.transportType = transportType;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getUserIdentifier() {
/* 2709 */     if (Trace.isOn) {
/* 2710 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getUserIdentifier()", "getter", this.userIdentifier);
/*      */     }
/* 2712 */     return this.userIdentifier;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUserIdentifier(String userIdentifier) {
/* 2721 */     if (Trace.isOn) {
/* 2722 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setUserIdentifier(String)", "setter", userIdentifier);
/*      */     }
/*      */     
/* 2725 */     this.userIdentifier = userIdentifier;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getVersion() {
/* 2736 */     if (Trace.isOn) {
/* 2737 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getVersion()", "getter", Integer.valueOf(this.version));
/*      */     }
/* 2739 */     return this.version;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setVersion(int version) {
/* 2749 */     if (Trace.isOn) {
/* 2750 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setVersion(int)", "setter", 
/* 2751 */           Integer.valueOf(version));
/*      */     }
/* 2753 */     this.version = version;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getXmitQName() {
/* 2763 */     if (Trace.isOn) {
/* 2764 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getXmitQName()", "getter", this.xmitQName);
/*      */     }
/* 2766 */     return this.xmitQName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setXmitQName(String xmitQName) {
/* 2775 */     if (Trace.isOn) {
/* 2776 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setXmitQName(String)", "setter", xmitQName);
/*      */     }
/* 2778 */     this.xmitQName = xmitQName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getMsgExit() {
/* 2788 */     if (Trace.isOn) {
/* 2789 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getMsgExit()", "getter", this.msgExit);
/*      */     }
/* 2791 */     return this.msgExit;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMsgExit(String msgExit) {
/* 2800 */     if (Trace.isOn) {
/* 2801 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setMsgExit(String)", "setter", msgExit);
/*      */     }
/* 2803 */     this.msgExit = msgExit;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getMsgExitPtr() {
/* 2813 */     if (Trace.isOn) {
/* 2814 */       Trace.entry(this, "com.ibm.mq.exits.MQCD", "getMsgExitPtr()");
/*      */     }
/*      */     try {
/* 2817 */       String traceRet1 = (this.msgExitPtr == null) ? null : newString(this.msgExitPtr);
/*      */       
/* 2819 */       if (Trace.isOn) {
/* 2820 */         Trace.exit(this, "com.ibm.mq.exits.MQCD", "getMsgExitPtr()", traceRet1, 1);
/*      */       }
/* 2822 */       return traceRet1;
/*      */     }
/* 2824 */     catch (Exception e) {
/* 2825 */       if (Trace.isOn) {
/* 2826 */         Trace.catchBlock(this, "com.ibm.mq.exits.MQCD", "getMsgExitPtr()", e);
/*      */       }
/*      */       
/* 2829 */       if (Trace.isOn) {
/* 2830 */         Trace.exit(this, "com.ibm.mq.exits.MQCD", "getMsgExitPtr()", null, 2);
/*      */       }
/* 2832 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getMsgExitPtrBytes() {
/* 2842 */     if (Trace.isOn) {
/* 2843 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getMsgExitPtrBytes()", "getter", this.msgExitPtr);
/*      */     }
/* 2845 */     return this.msgExitPtr;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMsgExitPtr(String msgExitPtr) {
/* 2854 */     if (Trace.isOn) {
/* 2855 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setMsgExitPtr(String)", "setter", msgExitPtr);
/*      */     }
/* 2857 */     if (msgExitPtr != null) {
/* 2858 */       this.msgExitPtr = getBytes(msgExitPtr);
/*      */     } else {
/*      */       
/* 2861 */       this.msgExitPtr = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMsgExitPtr(byte[] msgExitPtr) {
/* 2871 */     if (Trace.isOn) {
/* 2872 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setMsgExitPtr(byte [ ])", "setter", msgExitPtr);
/*      */     }
/* 2874 */     this.msgExitPtr = msgExitPtr;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getMsgUserData() {
/* 2884 */     if (Trace.isOn) {
/* 2885 */       Trace.entry(this, "com.ibm.mq.exits.MQCD", "getMsgUserData()");
/*      */     }
/*      */     try {
/* 2888 */       String traceRet1 = (this.msgUserData == null) ? null : newString(this.msgUserData);
/*      */       
/* 2890 */       if (Trace.isOn) {
/* 2891 */         Trace.exit(this, "com.ibm.mq.exits.MQCD", "getMsgUserData()", traceRet1, 1);
/*      */       }
/* 2893 */       return traceRet1;
/*      */     }
/* 2895 */     catch (Exception e) {
/* 2896 */       if (Trace.isOn) {
/* 2897 */         Trace.catchBlock(this, "com.ibm.mq.exits.MQCD", "getMsgUserData()", e);
/*      */       }
/*      */       
/* 2900 */       if (Trace.isOn) {
/* 2901 */         Trace.exit(this, "com.ibm.mq.exits.MQCD", "getMsgUserData()", null, 2);
/*      */       }
/* 2903 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getMsgUserDataBytes() {
/* 2912 */     if (Trace.isOn) {
/* 2913 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getMsgUserDataBytes()", "getter", this.msgUserData);
/*      */     }
/* 2915 */     return this.msgUserData;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMsgUserData(String msgUserData) {
/* 2923 */     if (Trace.isOn) {
/* 2924 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setMsgUserData(String)", "setter", msgUserData);
/*      */     }
/* 2926 */     spaceFilledByteCopy(msgUserData, this.msgUserData);
/*      */   }
/*      */   
/*      */   void setMsgUserData(byte[] msgUserData) {
/* 2930 */     if (Trace.isOn) {
/* 2931 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setMsgUserData(byte [ ])", "setter", msgUserData);
/*      */     }
/* 2933 */     System.arraycopy(msgUserData, 0, this.msgUserData, 0, this.exitDataLength);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getMsgUserDataPtr() {
/* 2942 */     if (Trace.isOn) {
/* 2943 */       Trace.entry(this, "com.ibm.mq.exits.MQCD", "getMsgUserDataPtr()");
/*      */     }
/*      */     try {
/* 2946 */       String traceRet1 = (this.msgUserDataPtr == null) ? null : newString(this.msgUserDataPtr);
/*      */       
/* 2948 */       if (Trace.isOn) {
/* 2949 */         Trace.exit(this, "com.ibm.mq.exits.MQCD", "getMsgUserDataPtr()", traceRet1, 1);
/*      */       }
/* 2951 */       return traceRet1;
/*      */     }
/* 2953 */     catch (Exception e) {
/* 2954 */       if (Trace.isOn) {
/* 2955 */         Trace.catchBlock(this, "com.ibm.mq.exits.MQCD", "getMsgUserDataPtr()", e);
/*      */       }
/*      */       
/* 2958 */       if (Trace.isOn) {
/* 2959 */         Trace.exit(this, "com.ibm.mq.exits.MQCD", "getMsgUserDataPtr()", null, 2);
/*      */       }
/* 2961 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getMsgUserDataPtrBytes() {
/* 2970 */     if (Trace.isOn) {
/* 2971 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getMsgUserDataPtrBytes()", "getter", this.msgUserDataPtr);
/*      */     }
/*      */     
/* 2974 */     return this.msgUserDataPtr;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMsgUserDataPtr(String msgUserDataPtr) {
/* 2982 */     if (Trace.isOn) {
/* 2983 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setMsgUserDataPtr(String)", "setter", msgUserDataPtr);
/*      */     }
/*      */     
/* 2986 */     if (msgUserDataPtr != null) {
/* 2987 */       this.msgUserDataPtr = getBytes(msgUserDataPtr);
/*      */     } else {
/*      */       
/* 2990 */       this.msgUserDataPtr = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void setMsgUserDataPtr(byte[] msgUserDataPtr) {
/* 2996 */     if (Trace.isOn) {
/* 2997 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setMsgUserDataPtr(byte [ ])", "setter", msgUserDataPtr);
/*      */     }
/*      */     
/* 3000 */     if (msgUserDataPtr != null) {
/* 3001 */       this.msgUserDataPtr = (byte[])msgUserDataPtr.clone();
/*      */     } else {
/*      */       
/* 3004 */       this.msgUserDataPtr = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getReceiveExit() {
/* 3015 */     if (Trace.isOn) {
/* 3016 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getReceiveExit()", "getter", this.receiveExit);
/*      */     }
/* 3018 */     return this.receiveExit;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReceiveExit(String receiveExit) {
/* 3027 */     if (Trace.isOn) {
/* 3028 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setReceiveExit(String)", "setter", receiveExit);
/*      */     }
/* 3030 */     this.receiveExit = receiveExit;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getReceiveExitPtr() {
/* 3040 */     if (Trace.isOn) {
/* 3041 */       Trace.entry(this, "com.ibm.mq.exits.MQCD", "getReceiveExitPtr()");
/*      */     }
/*      */     try {
/* 3044 */       String traceRet1 = (this.receiveExitPtr == null) ? null : newString(this.receiveExitPtr);
/*      */       
/* 3046 */       if (Trace.isOn) {
/* 3047 */         Trace.exit(this, "com.ibm.mq.exits.MQCD", "getReceiveExitPtr()", traceRet1, 1);
/*      */       }
/* 3049 */       return traceRet1;
/*      */     }
/* 3051 */     catch (Exception e) {
/* 3052 */       if (Trace.isOn) {
/* 3053 */         Trace.catchBlock(this, "com.ibm.mq.exits.MQCD", "getReceiveExitPtr()", e);
/*      */       }
/*      */       
/* 3056 */       if (Trace.isOn) {
/* 3057 */         Trace.exit(this, "com.ibm.mq.exits.MQCD", "getReceiveExitPtr()", null, 2);
/*      */       }
/* 3059 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getReceiveExitPtrBytes() {
/* 3068 */     if (Trace.isOn) {
/* 3069 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getReceiveExitPtrBytes()", "getter", this.receiveExitPtr);
/*      */     }
/*      */     
/* 3072 */     return this.receiveExitPtr;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReceiveExitPtr(String receiveExitPtr) {
/* 3081 */     if (Trace.isOn) {
/* 3082 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setReceiveExitPtr(String)", "setter", receiveExitPtr);
/*      */     }
/*      */     
/* 3085 */     if (receiveExitPtr != null) {
/* 3086 */       this.receiveExitPtr = getBytes(receiveExitPtr);
/*      */     } else {
/*      */       
/* 3089 */       this.receiveExitPtr = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReceiveExitPtr(byte[] receiveExitPtr) {
/* 3098 */     if (Trace.isOn) {
/* 3099 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setReceiveExitPtr(byte [ ])", "setter", receiveExitPtr);
/*      */     }
/*      */     
/* 3102 */     this.receiveExitPtr = receiveExitPtr;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getReceiveUserData() {
/* 3111 */     if (Trace.isOn) {
/* 3112 */       Trace.entry(this, "com.ibm.mq.exits.MQCD", "getReceiveUserData()");
/*      */     }
/*      */     try {
/* 3115 */       String traceRet1 = (this.receiveUserData == null) ? null : newString(this.receiveUserData);
/*      */       
/* 3117 */       if (Trace.isOn) {
/* 3118 */         Trace.exit(this, "com.ibm.mq.exits.MQCD", "getReceiveUserData()", traceRet1, 1);
/*      */       }
/* 3120 */       return traceRet1;
/*      */     }
/* 3122 */     catch (Exception e) {
/* 3123 */       if (Trace.isOn) {
/* 3124 */         Trace.catchBlock(this, "com.ibm.mq.exits.MQCD", "getReceiveUserData()", e);
/*      */       }
/*      */       
/* 3127 */       if (Trace.isOn) {
/* 3128 */         Trace.exit(this, "com.ibm.mq.exits.MQCD", "getReceiveUserData()", null, 2);
/*      */       }
/* 3130 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getReceiveUserDataBytes() {
/* 3139 */     if (Trace.isOn) {
/* 3140 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getReceiveUserDataBytes()", "getter", this.receiveUserData);
/*      */     }
/*      */     
/* 3143 */     return this.receiveUserData;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReceiveUserData(String receiveUserData) {
/* 3151 */     if (Trace.isOn) {
/* 3152 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setReceiveUserData(String)", "setter", receiveUserData);
/*      */     }
/*      */     
/* 3155 */     spaceFilledByteCopy(receiveUserData, this.receiveUserData);
/*      */   }
/*      */   
/*      */   void setReceiveUserData(byte[] receiveUserData) {
/* 3159 */     if (Trace.isOn) {
/* 3160 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setReceiveUserData(byte [ ])", "setter", receiveUserData);
/*      */     }
/*      */     
/* 3163 */     System.arraycopy(receiveUserData, 0, this.receiveUserData, 0, this.exitDataLength);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getReceiveUserDataPtr() {
/* 3172 */     if (Trace.isOn) {
/* 3173 */       Trace.entry(this, "com.ibm.mq.exits.MQCD", "getReceiveUserDataPtr()");
/*      */     }
/*      */     try {
/* 3176 */       String traceRet1 = (this.receiveUserDataPtr == null) ? null : newString(this.receiveUserDataPtr);
/*      */       
/* 3178 */       if (Trace.isOn) {
/* 3179 */         Trace.exit(this, "com.ibm.mq.exits.MQCD", "getReceiveUserDataPtr()", traceRet1, 1);
/*      */       }
/* 3181 */       return traceRet1;
/*      */     }
/* 3183 */     catch (Exception e) {
/* 3184 */       if (Trace.isOn) {
/* 3185 */         Trace.catchBlock(this, "com.ibm.mq.exits.MQCD", "getReceiveUserDataPtr()", e);
/*      */       }
/*      */       
/* 3188 */       if (Trace.isOn) {
/* 3189 */         Trace.exit(this, "com.ibm.mq.exits.MQCD", "getReceiveUserDataPtr()", null, 2);
/*      */       }
/* 3191 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getReceiveUserDataPtrBytes() {
/* 3200 */     if (Trace.isOn) {
/* 3201 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getReceiveUserDataPtrBytes()", "getter", this.receiveUserDataPtr);
/*      */     }
/*      */     
/* 3204 */     return this.receiveUserDataPtr;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReceiveUserDataPtr(String receiveUserDataPtr) {
/* 3212 */     if (Trace.isOn) {
/* 3213 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setReceiveUserDataPtr(String)", "setter", receiveUserDataPtr);
/*      */     }
/*      */     
/* 3216 */     if (receiveUserDataPtr != null) {
/* 3217 */       this.receiveUserDataPtr = getBytes(receiveUserDataPtr);
/*      */     } else {
/*      */       
/* 3220 */       this.receiveUserDataPtr = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReceiveUserDataPtr(byte[] receiveUserDataPtr) {
/* 3230 */     if (Trace.isOn) {
/* 3231 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setReceiveUserDataPtr(byte [ ])", "setter", receiveUserDataPtr);
/*      */     }
/*      */     
/* 3234 */     if (receiveUserDataPtr != null) {
/* 3235 */       this.receiveUserDataPtr = (byte[])receiveUserDataPtr.clone();
/*      */     } else {
/*      */       
/* 3238 */       this.receiveUserDataPtr = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getSendExit() {
/* 3249 */     if (Trace.isOn) {
/* 3250 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getSendExit()", "getter", this.sendExit);
/*      */     }
/* 3252 */     return this.sendExit;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSendExit(String sendExit) {
/* 3261 */     if (Trace.isOn) {
/* 3262 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setSendExit(String)", "setter", sendExit);
/*      */     }
/* 3264 */     this.sendExit = sendExit;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getSendExitPtr() {
/* 3274 */     if (Trace.isOn) {
/* 3275 */       Trace.entry(this, "com.ibm.mq.exits.MQCD", "getSendExitPtr()");
/*      */     }
/*      */     try {
/* 3278 */       String traceRet1 = (this.sendExitPtr == null) ? null : newString(this.sendExitPtr);
/*      */       
/* 3280 */       if (Trace.isOn) {
/* 3281 */         Trace.exit(this, "com.ibm.mq.exits.MQCD", "getSendExitPtr()", traceRet1, 1);
/*      */       }
/* 3283 */       return traceRet1;
/*      */     }
/* 3285 */     catch (Exception e) {
/* 3286 */       if (Trace.isOn) {
/* 3287 */         Trace.catchBlock(this, "com.ibm.mq.exits.MQCD", "getSendExitPtr()", e);
/*      */       }
/*      */       
/* 3290 */       if (Trace.isOn) {
/* 3291 */         Trace.exit(this, "com.ibm.mq.exits.MQCD", "getSendExitPtr()", null, 2);
/*      */       }
/* 3293 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getSendExitPtrBytes() {
/* 3302 */     if (Trace.isOn) {
/* 3303 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getSendExitPtrBytes()", "getter", this.sendExitPtr);
/*      */     }
/* 3305 */     return this.sendExitPtr;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSendExitPtr(String sendExitPtr) {
/* 3313 */     if (Trace.isOn) {
/* 3314 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setSendExitPtr(String)", "setter", sendExitPtr);
/*      */     }
/* 3316 */     if (sendExitPtr != null) {
/* 3317 */       this.sendExitPtr = getBytes(sendExitPtr);
/*      */     } else {
/*      */       
/* 3320 */       this.sendExitPtr = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSendExitPtr(byte[] sendExitPtr) {
/* 3329 */     if (Trace.isOn) {
/* 3330 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setSendExitPtr(byte [ ])", "setter", sendExitPtr);
/*      */     }
/* 3332 */     this.sendExitPtr = sendExitPtr;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getSendUserData() {
/* 3341 */     if (Trace.isOn) {
/* 3342 */       Trace.entry(this, "com.ibm.mq.exits.MQCD", "getSendUserData()");
/*      */     }
/*      */     try {
/* 3345 */       String traceRet1 = (this.sendUserData == null) ? null : newString(this.sendUserData);
/*      */       
/* 3347 */       if (Trace.isOn) {
/* 3348 */         Trace.exit(this, "com.ibm.mq.exits.MQCD", "getSendUserData()", traceRet1, 1);
/*      */       }
/* 3350 */       return traceRet1;
/*      */     }
/* 3352 */     catch (Exception e) {
/* 3353 */       if (Trace.isOn) {
/* 3354 */         Trace.catchBlock(this, "com.ibm.mq.exits.MQCD", "getSendUserData()", e);
/*      */       }
/*      */       
/* 3357 */       if (Trace.isOn) {
/* 3358 */         Trace.exit(this, "com.ibm.mq.exits.MQCD", "getSendUserData()", null, 2);
/*      */       }
/* 3360 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getSendUserDataBytes() {
/* 3370 */     if (Trace.isOn) {
/* 3371 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getSendUserDataBytes()", "getter", this.sendUserData);
/*      */     }
/* 3373 */     return this.sendUserData;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSendUserData(String sendUserData) {
/* 3381 */     if (Trace.isOn) {
/* 3382 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setSendUserData(String)", "setter", sendUserData);
/*      */     }
/* 3384 */     spaceFilledByteCopy(sendUserData, this.sendUserData);
/*      */   }
/*      */   
/*      */   void setSendUserData(byte[] sendUserData) {
/* 3388 */     if (Trace.isOn) {
/* 3389 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setSendUserData(byte [ ])", "setter", sendUserData);
/*      */     }
/*      */     
/* 3392 */     System.arraycopy(sendUserData, 0, this.sendUserData, 0, this.exitDataLength);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getSendUserDataPtr() {
/* 3401 */     if (Trace.isOn) {
/* 3402 */       Trace.entry(this, "com.ibm.mq.exits.MQCD", "getSendUserDataPtr()");
/*      */     }
/*      */     try {
/* 3405 */       String traceRet1 = (this.sendUserDataPtr == null) ? null : newString(this.sendUserDataPtr);
/*      */       
/* 3407 */       if (Trace.isOn) {
/* 3408 */         Trace.exit(this, "com.ibm.mq.exits.MQCD", "getSendUserDataPtr()", traceRet1, 1);
/*      */       }
/* 3410 */       return traceRet1;
/*      */     }
/* 3412 */     catch (Exception e) {
/* 3413 */       if (Trace.isOn) {
/* 3414 */         Trace.catchBlock(this, "com.ibm.mq.exits.MQCD", "getSendUserDataPtr()", e);
/*      */       }
/*      */       
/* 3417 */       if (Trace.isOn) {
/* 3418 */         Trace.exit(this, "com.ibm.mq.exits.MQCD", "getSendUserDataPtr()", null, 2);
/*      */       }
/* 3420 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getSendUserDataPtrBytes() {
/* 3429 */     if (Trace.isOn) {
/* 3430 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getSendUserDataPtrBytes()", "getter", this.sendUserDataPtr);
/*      */     }
/*      */     
/* 3433 */     return this.sendUserDataPtr;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSendUserDataPtr(String sendUserDataPtr) {
/* 3441 */     if (Trace.isOn) {
/* 3442 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setSendUserDataPtr(String)", "setter", sendUserDataPtr);
/*      */     }
/*      */     
/* 3445 */     if (sendUserDataPtr != null) {
/* 3446 */       this.sendUserDataPtr = getBytes(sendUserDataPtr);
/*      */     } else {
/*      */       
/* 3449 */       this.sendUserDataPtr = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSendUserDataPtr(byte[] sendUserDataPtr) {
/* 3458 */     if (Trace.isOn) {
/* 3459 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setSendUserDataPtr(byte [ ])", "setter", sendUserDataPtr);
/*      */     }
/*      */     
/* 3462 */     if (sendUserDataPtr != null) {
/* 3463 */       this.sendUserDataPtr = (byte[])sendUserDataPtr.clone();
/*      */     } else {
/*      */       
/* 3466 */       this.sendUserDataPtr = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setExitDataLength(int exitDataLength) {
/* 3475 */     if (Trace.isOn) {
/* 3476 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setExitDataLength(int)", "setter", 
/* 3477 */           Integer.valueOf(exitDataLength));
/*      */     }
/* 3479 */     this.exitDataLength = exitDataLength;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getExitDataLength() {
/* 3489 */     if (Trace.isOn) {
/* 3490 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getExitDataLength()", "getter", 
/* 3491 */           Integer.valueOf(this.exitDataLength));
/*      */     }
/* 3493 */     return this.exitDataLength;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setExitNameLength(int exitNameLength) {
/* 3502 */     if (Trace.isOn) {
/* 3503 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setExitNameLength(int)", "setter", 
/* 3504 */           Integer.valueOf(exitNameLength));
/*      */     }
/* 3506 */     this.exitNameLength = exitNameLength;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getExitNameLength() {
/* 3516 */     if (Trace.isOn) {
/* 3517 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getExitNameLength()", "getter", 
/* 3518 */           Integer.valueOf(this.exitNameLength));
/*      */     }
/* 3520 */     return this.exitNameLength;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getSharingConversations() {
/* 3530 */     if (Trace.isOn) {
/* 3531 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getSharingConversations()", "getter", 
/* 3532 */           Integer.valueOf(this.SharingConversations));
/*      */     }
/* 3534 */     return this.SharingConversations;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSharingConversations(int sharingConversations) {
/* 3542 */     if (Trace.isOn) {
/* 3543 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setSharingConversations(int)", "setter", 
/* 3544 */           Integer.valueOf(sharingConversations));
/*      */     }
/* 3546 */     this.SharingConversations = sharingConversations;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getClientChannelWeight() {
/* 3555 */     if (Trace.isOn) {
/* 3556 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getClientChannelWeight()", "getter", 
/* 3557 */           Integer.valueOf(this.clientChannelWeight));
/*      */     }
/* 3559 */     return this.clientChannelWeight;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setClientChannelWeight(int clientChannelWeight) {
/* 3567 */     if (Trace.isOn) {
/* 3568 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setClientChannelWeight(int)", "setter", 
/* 3569 */           Integer.valueOf(clientChannelWeight));
/*      */     }
/* 3571 */     this.clientChannelWeight = clientChannelWeight;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getConnectionAffinity() {
/* 3580 */     if (Trace.isOn) {
/* 3581 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getConnectionAffinity()", "getter", 
/* 3582 */           Integer.valueOf(this.connectionAffinity));
/*      */     }
/* 3584 */     return this.connectionAffinity;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setConnectionAffinity(int connectionAffinity) {
/* 3592 */     if (Trace.isOn) {
/* 3593 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setConnectionAffinity(int)", "setter", 
/* 3594 */           Integer.valueOf(connectionAffinity));
/*      */     }
/* 3596 */     this.connectionAffinity = connectionAffinity;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getBatchDataLimit() {
/* 3603 */     if (Trace.isOn) {
/* 3604 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getBatchDataLimit()", "getter", 
/* 3605 */           Integer.valueOf(this.batchDataLimit));
/*      */     }
/* 3607 */     return this.batchDataLimit;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBatchDataLimit(int batchDataLimit) {
/* 3614 */     if (Trace.isOn) {
/* 3615 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setBatchDataLimit(int)", "setter", 
/* 3616 */           Integer.valueOf(batchDataLimit));
/*      */     }
/* 3618 */     this.batchDataLimit = batchDataLimit;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxInstances() {
/* 3627 */     if (Trace.isOn) {
/* 3628 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getMaxInstances()", "getter", 
/* 3629 */           Integer.valueOf(this.maxInstances));
/*      */     }
/* 3631 */     return this.maxInstances;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMaxInstances(int maxInstances) {
/* 3639 */     if (Trace.isOn) {
/* 3640 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setMaxInstances(int)", "setter", 
/* 3641 */           Integer.valueOf(maxInstances));
/*      */     }
/* 3643 */     this.maxInstances = maxInstances;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxInstancesPerClient() {
/* 3652 */     if (Trace.isOn) {
/* 3653 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getMaxInstancesPerClient()", "getter", 
/* 3654 */           Integer.valueOf(this.maxInstancesPerClient));
/*      */     }
/* 3656 */     return this.maxInstancesPerClient;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMaxInstancesPerClient(int maxInstancesPerClient) {
/* 3664 */     if (Trace.isOn) {
/* 3665 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setMaxInstancesPerClient(int)", "setter", 
/* 3666 */           Integer.valueOf(maxInstancesPerClient));
/*      */     }
/* 3668 */     this.maxInstancesPerClient = maxInstancesPerClient;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPropertyControl() {
/* 3677 */     if (Trace.isOn) {
/* 3678 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getPropertyControl()", "getter", 
/* 3679 */           Integer.valueOf(this.propertyControl));
/*      */     }
/* 3681 */     return this.propertyControl;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPropertyControl(int propertyControl) {
/* 3689 */     if (Trace.isOn) {
/* 3690 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setPropertyControl(int)", "setter", 
/* 3691 */           Integer.valueOf(propertyControl));
/*      */     }
/* 3693 */     this.propertyControl = propertyControl;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getRequiredBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/* 3704 */     int size = getSize(this.env, ptrSize, this.version);
/* 3705 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getCertificateLabel() {
/* 3713 */     return this.certificateLabel;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCertificateLabel(String certificateLabel) {
/* 3721 */     if (certificateLabel != null) {
/* 3722 */       String trimmedLabel = certificateLabel.trim();
/* 3723 */       if (trimmedLabel.length() == 0) {
/* 3724 */         this.certificateLabel = null;
/*      */       } else {
/*      */         
/* 3727 */         this.certificateLabel = trimmedLabel;
/*      */       } 
/*      */     } else {
/*      */       
/* 3731 */       this.certificateLabel = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 3741 */     if (Trace.isOn) {
/* 3742 */       Trace.entry(this, "com.ibm.mq.exits.MQCD", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*      */             
/* 3744 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*      */     }
/* 3746 */     int pos = offset;
/* 3747 */     int startPos = pos;
/* 3748 */     int strucLengthPos = -1;
/* 3749 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*      */     
/* 3751 */     dc.writeMQField(this.channelName, buffer, pos, 20, cp, tls);
/* 3752 */     pos += 20;
/*      */     
/* 3754 */     dc.writeI32(this.version, buffer, pos, swap);
/* 3755 */     pos += 4;
/*      */     
/* 3757 */     dc.writeI32(this.channelType, buffer, pos, swap);
/* 3758 */     pos += 4;
/*      */     
/* 3760 */     dc.writeI32(this.transportType, buffer, pos, swap);
/* 3761 */     pos += 4;
/*      */     
/* 3763 */     dc.writeField(this.desc, buffer, pos, 64, cp, tls);
/* 3764 */     pos += 64;
/*      */     
/* 3766 */     dc.writeMQField(this.qMgrName, buffer, pos, 48, cp, tls);
/* 3767 */     pos += 48;
/*      */     
/* 3769 */     dc.writeMQField(this.xmitQName, buffer, pos, 48, cp, tls);
/* 3770 */     pos += 48;
/*      */     
/* 3772 */     dc.writeField(this.connectionName, buffer, pos, 20, cp, tls);
/* 3773 */     pos += 20;
/*      */     
/* 3775 */     dc.writeField(this.mcaName, buffer, pos, 20, cp, tls);
/* 3776 */     pos += 20;
/*      */     
/* 3778 */     dc.writeField(this.modeName, buffer, pos, 8, cp, tls);
/* 3779 */     pos += 8;
/*      */     
/* 3781 */     dc.writeField(this.tpName, buffer, pos, 64, cp, tls);
/* 3782 */     pos += 64;
/*      */     
/* 3784 */     dc.writeI32(this.batchSize, buffer, pos, swap);
/* 3785 */     pos += 4;
/*      */     
/* 3787 */     dc.writeI32(this.discInterval, buffer, pos, swap);
/* 3788 */     pos += 4;
/*      */     
/* 3790 */     dc.writeI32(this.shortRetryCount, buffer, pos, swap);
/* 3791 */     pos += 4;
/*      */     
/* 3793 */     dc.writeI32(this.shortRetryInterval, buffer, pos, swap);
/* 3794 */     pos += 4;
/*      */     
/* 3796 */     dc.writeI32(this.longRetryCount, buffer, pos, swap);
/* 3797 */     pos += 4;
/*      */     
/* 3799 */     dc.writeI32(this.longRetryInterval, buffer, pos, swap);
/* 3800 */     pos += 4;
/*      */     
/* 3802 */     dc.writeField(this.securityExit, buffer, pos, SIZEOF_SECURITY_EXIT, cp, tls);
/* 3803 */     pos += SIZEOF_SECURITY_EXIT;
/*      */     
/* 3805 */     dc.writeField(this.msgExit, buffer, pos, SIZEOF_MSG_EXIT, cp, tls);
/* 3806 */     pos += SIZEOF_MSG_EXIT;
/*      */     
/* 3808 */     dc.writeField(this.sendExit, buffer, pos, SIZEOF_SEND_EXIT, cp, tls);
/* 3809 */     pos += SIZEOF_SEND_EXIT;
/*      */     
/* 3811 */     dc.writeField(this.receiveExit, buffer, pos, SIZEOF_RECEIVE_EXIT, cp, tls);
/* 3812 */     pos += SIZEOF_RECEIVE_EXIT;
/*      */     
/* 3814 */     dc.writeI32(this.seqNumberWrap, buffer, pos, swap);
/* 3815 */     pos += 4;
/*      */     
/* 3817 */     dc.writeI32(this.maxMsgLength, buffer, pos, swap);
/* 3818 */     pos += 4;
/*      */     
/* 3820 */     dc.writeI32(this.putAuthority, buffer, pos, swap);
/* 3821 */     pos += 4;
/*      */     
/* 3823 */     dc.writeI32(this.dataConversion, buffer, pos, swap);
/* 3824 */     pos += 4;
/*      */     
/* 3826 */     System.arraycopy(this.securityUserData, 0, buffer, pos, 32);
/* 3827 */     pos += 32;
/*      */     
/* 3829 */     System.arraycopy(this.msgUserData, 0, buffer, pos, 32);
/* 3830 */     pos += 32;
/*      */     
/* 3832 */     System.arraycopy(this.sendUserData, 0, buffer, pos, 32);
/* 3833 */     pos += 32;
/*      */     
/* 3835 */     System.arraycopy(this.receiveUserData, 0, buffer, pos, 32);
/* 3836 */     pos += 32;
/* 3837 */     if (this.version >= 2) {
/*      */       
/* 3839 */       dc.writeField(this.userIdentifier, buffer, pos, 12, cp, tls);
/* 3840 */       pos += 12;
/*      */       
/* 3842 */       dc.writeField(this.password, buffer, pos, 12, cp, tls, true);
/* 3843 */       pos += 12;
/*      */       
/* 3845 */       dc.writeField(this.mcaUserIdentifier, buffer, pos, 12, cp, tls);
/* 3846 */       pos += 12;
/*      */       
/* 3848 */       dc.writeI32(this.mcaType, buffer, pos, swap);
/* 3849 */       pos += 4;
/*      */       
/* 3851 */       dc.writeField(this.connectionName, buffer, pos, 264, cp, tls);
/* 3852 */       pos += 264;
/*      */       
/* 3854 */       dc.writeField(this.remoteUserIdentifier, buffer, pos, 12, cp, tls);
/* 3855 */       pos += 12;
/*      */       
/* 3857 */       dc.writeField(this.remotePassword, buffer, pos, 12, cp, tls);
/* 3858 */       pos += 12;
/*      */     } 
/* 3860 */     if (this.version >= 3) {
/*      */       
/* 3862 */       dc.writeField(this.msgRetryExit, buffer, pos, SIZEOF_MSG_RETRY_EXIT, cp, tls);
/* 3863 */       pos += SIZEOF_MSG_RETRY_EXIT;
/*      */       
/* 3865 */       dc.writeField(this.msgRetryUserData, buffer, pos, 32, cp, tls);
/* 3866 */       pos += 32;
/*      */       
/* 3868 */       dc.writeI32(this.msgRetryCount, buffer, pos, swap);
/* 3869 */       pos += 4;
/*      */       
/* 3871 */       dc.writeI32(this.msgRetryInterval, buffer, pos, swap);
/* 3872 */       pos += 4;
/*      */     } 
/* 3874 */     if (this.version >= 4) {
/*      */       
/* 3876 */       dc.writeI32(this.heartbeatInterval, buffer, pos, swap);
/* 3877 */       pos += 4;
/*      */       
/* 3879 */       dc.writeI32(this.batchInterval, buffer, pos, swap);
/* 3880 */       pos += 4;
/*      */       
/* 3882 */       dc.writeI32(this.nonPersistentMsgSpeed, buffer, pos, swap);
/* 3883 */       pos += 4;
/*      */       
/* 3885 */       strucLengthPos = pos;
/* 3886 */       pos += 4;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3894 */       int skip = 20;
/* 3895 */       dc.clear(buffer, pos, skip);
/* 3896 */       pos += skip;
/*      */       
/* 3898 */       int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 3899 */       dc.clear(buffer, pos, padding);
/* 3900 */       pos += padding;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3907 */       skip = 6 * ptrSize;
/* 3908 */       dc.clear(buffer, pos, skip);
/* 3909 */       pos += skip;
/*      */     } 
/* 3911 */     if (this.version >= 5) {
/*      */       
/* 3913 */       dc.clear(buffer, pos, ptrSize);
/* 3914 */       pos += ptrSize;
/*      */       
/* 3916 */       dc.writeI32(this.clustersDefined, buffer, pos, swap);
/* 3917 */       pos += 4;
/*      */       
/* 3919 */       dc.writeI32(this.networkPriority, buffer, pos, swap);
/* 3920 */       pos += 4;
/*      */     } 
/*      */     
/* 3923 */     if (this.version >= 6) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3928 */       dc.clear(buffer, pos, 8 + ptrSize + ptrSize);
/* 3929 */       pos += 8 + ptrSize + ptrSize;
/*      */       
/* 3931 */       System.arraycopy(this.mcaSecurityId, 0, buffer, pos, 40);
/* 3932 */       pos += 40;
/*      */       
/* 3934 */       System.arraycopy(this.remoteSecurityId, 0, buffer, pos, 40);
/* 3935 */       pos += 40;
/*      */     } 
/* 3937 */     if (this.version >= 7) {
/*      */       
/* 3939 */       dc.writeField(this.sslCipherSpec, buffer, pos, 32, cp, tls);
/* 3940 */       pos += 32;
/*      */ 
/*      */ 
/*      */       
/* 3944 */       dc.clear(buffer, pos, 4 + ptrSize);
/* 3945 */       pos += 4 + ptrSize;
/*      */       
/* 3947 */       dc.writeI32(this.sslClientAuth, buffer, pos, swap);
/* 3948 */       pos += 4;
/*      */       
/* 3950 */       dc.writeI32(this.keepAliveInterval, buffer, pos, swap);
/* 3951 */       pos += 4;
/*      */       
/* 3953 */       dc.writeField(this.localAddress, buffer, pos, 48, cp, tls);
/* 3954 */       pos += 48;
/*      */       
/* 3956 */       dc.writeI32(this.batchHeartbeat, buffer, pos, swap);
/* 3957 */       pos += 4;
/*      */     } 
/* 3959 */     if (this.version >= 8) {
/*      */       int i;
/* 3961 */       for (i = 0; i < 2; i++) {
/* 3962 */         dc.writeI32(this.hdrCompList[i], buffer, pos, swap);
/* 3963 */         pos += 4;
/*      */       } 
/*      */       
/* 3966 */       for (i = 0; i < 16; i++) {
/* 3967 */         dc.writeI32(this.msgCompList[i], buffer, pos, swap);
/* 3968 */         pos += 4;
/*      */       } 
/*      */       
/* 3971 */       dc.writeI32(this.clwlChannelRank, buffer, pos, swap);
/* 3972 */       pos += 4;
/*      */       
/* 3974 */       dc.writeI32(this.clwlChannelPriority, buffer, pos, swap);
/* 3975 */       pos += 4;
/*      */       
/* 3977 */       dc.writeI32(this.clwlChannelWeight, buffer, pos, swap);
/* 3978 */       pos += 4;
/*      */       
/* 3980 */       dc.writeI32(this.channelMonitoring, buffer, pos, swap);
/* 3981 */       pos += 4;
/*      */       
/* 3983 */       dc.writeI32(this.channelStatistics, buffer, pos, swap);
/* 3984 */       pos += 4;
/*      */     } 
/* 3986 */     if (this.version == 8) {
/*      */       
/* 3988 */       int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 3989 */       pos += padding;
/*      */     } 
/* 3991 */     if (this.version >= 9) {
/*      */       
/* 3993 */       dc.writeI32(this.SharingConversations, buffer, pos, swap);
/* 3994 */       pos += 4;
/*      */       
/* 3996 */       dc.writeI32(this.propertyControl, buffer, pos, swap);
/* 3997 */       pos += 4;
/*      */       
/* 3999 */       dc.writeI32(this.maxInstances, buffer, pos, swap);
/* 4000 */       pos += 4;
/*      */       
/* 4002 */       dc.writeI32(this.maxInstancesPerClient, buffer, pos, swap);
/* 4003 */       pos += 4;
/*      */       
/* 4005 */       dc.writeI32(this.clientChannelWeight, buffer, pos, swap);
/* 4006 */       pos += 4;
/*      */       
/* 4008 */       dc.writeI32(this.connectionAffinity, buffer, pos, swap);
/* 4009 */       pos += 4;
/*      */     } 
/* 4011 */     if (this.version == 9) {
/*      */       
/* 4013 */       int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 4014 */       pos += padding;
/*      */     } 
/* 4016 */     if (this.version >= 10) {
/*      */       
/* 4018 */       dc.writeI32(this.batchDataLimit, buffer, pos, swap);
/* 4019 */       pos += 4;
/*      */       
/* 4021 */       dc.writeI32(this.useDLQ, buffer, pos, swap);
/* 4022 */       pos += 4;
/*      */       
/* 4024 */       dc.writeI32(this.defReconnect, buffer, pos, swap);
/* 4025 */       pos += 4;
/*      */     } 
/* 4027 */     if (this.version >= 11) {
/*      */       
/* 4029 */       dc.writeField(this.certificateLabel, buffer, pos, 64, cp, tls);
/* 4030 */       pos += 64;
/*      */     } 
/*      */ 
/*      */     
/* 4034 */     if (strucLengthPos > 0) {
/* 4035 */       dc.writeI32(pos - startPos, buffer, strucLengthPos, swap);
/*      */     }
/*      */     
/* 4038 */     if (Trace.isOn) {
/* 4039 */       Trace.exit(this, "com.ibm.mq.exits.MQCD", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 4040 */           Integer.valueOf(pos));
/*      */     }
/* 4042 */     return pos;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int readFromBuffer(byte[] buffer, int pos, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 4051 */     if (Trace.isOn) {
/* 4052 */       Trace.entry(this, "com.ibm.mq.exits.MQCD", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*      */             
/* 4054 */             Integer.valueOf(pos), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*      */     }
/* 4056 */     int readPos = pos;
/* 4057 */     readPos = readFromBuffer(buffer, pos, buffer.length - pos, false, ptrSize, swap, cp, tls);
/*      */     
/* 4059 */     if (Trace.isOn) {
/* 4060 */       Trace.exit(this, "com.ibm.mq.exits.MQCD", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 4061 */           Integer.valueOf(readPos));
/*      */     }
/* 4063 */     return readPos;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int readFromBuffer(byte[] buffer, int offset, int maxLen, boolean fromCCDTparam, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 4089 */     if (Trace.isOn) {
/* 4090 */       Trace.entry(this, "com.ibm.mq.exits.MQCD", "readFromBuffer(byte [ ],int,int,boolean,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*      */             
/* 4092 */             Integer.valueOf(offset), Integer.valueOf(maxLen), Boolean.valueOf(fromCCDTparam), 
/* 4093 */             Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*      */     }
/* 4095 */     boolean fromCCDT = fromCCDTparam;
/* 4096 */     int pos = offset;
/* 4097 */     int startPos = pos;
/* 4098 */     int limit = startPos + maxLen;
/* 4099 */     int sslPeerNameLength = -1;
/* 4100 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*      */     
/* 4102 */     this.channelName = dc.readMQField(buffer, pos, 20, cp, tls);
/* 4103 */     pos += 20;
/*      */     
/* 4105 */     this.version = dc.readI32(buffer, pos, swap);
/* 4106 */     pos += 4;
/*      */     
/* 4108 */     this.channelType = dc.readI32(buffer, pos, swap);
/* 4109 */     pos += 4;
/*      */     
/* 4111 */     this.transportType = dc.readI32(buffer, pos, swap);
/* 4112 */     pos += 4;
/*      */     
/* 4114 */     this.desc = dc.readField(buffer, pos, 64, cp, tls);
/* 4115 */     pos += 64;
/*      */     
/* 4117 */     this.qMgrName = dc.readMQField(buffer, pos, 48, cp, tls);
/* 4118 */     pos += 48;
/*      */     
/* 4120 */     this.xmitQName = dc.readMQField(buffer, pos, 48, cp, tls);
/* 4121 */     pos += 48;
/*      */     
/* 4123 */     this.connectionName = dc.readField(buffer, pos, 20, cp, tls);
/* 4124 */     pos += 20;
/*      */     
/* 4126 */     this.mcaName = dc.readField(buffer, pos, 20, cp, tls);
/* 4127 */     pos += 20;
/*      */     
/* 4129 */     this.modeName = dc.readField(buffer, pos, 8, cp, tls);
/* 4130 */     pos += 8;
/*      */     
/* 4132 */     this.tpName = dc.readField(buffer, pos, 64, cp, tls);
/* 4133 */     pos += 64;
/*      */     
/* 4135 */     this.batchSize = dc.readI32(buffer, pos, swap);
/* 4136 */     pos += 4;
/*      */     
/* 4138 */     this.discInterval = dc.readI32(buffer, pos, swap);
/* 4139 */     pos += 4;
/*      */     
/* 4141 */     this.shortRetryCount = dc.readI32(buffer, pos, swap);
/* 4142 */     pos += 4;
/*      */     
/* 4144 */     this.shortRetryInterval = dc.readI32(buffer, pos, swap);
/* 4145 */     pos += 4;
/*      */     
/* 4147 */     this.longRetryCount = dc.readI32(buffer, pos, swap);
/* 4148 */     pos += 4;
/*      */     
/* 4150 */     this.longRetryInterval = dc.readI32(buffer, pos, swap);
/* 4151 */     pos += 4;
/*      */     
/* 4153 */     this.securityExit = readExit(dc, buffer, pos, SIZEOF_SECURITY_EXIT, cp, tls);
/* 4154 */     if (fromCCDT) {
/* 4155 */       pos += 128;
/*      */     } else {
/*      */       
/* 4158 */       pos += SIZEOF_SECURITY_EXIT;
/*      */     } 
/*      */     
/* 4161 */     this.msgExit = readExit(dc, buffer, pos, SIZEOF_MSG_EXIT, cp, tls);
/* 4162 */     if (fromCCDT) {
/* 4163 */       pos += 128;
/*      */     } else {
/*      */       
/* 4166 */       pos += SIZEOF_MSG_EXIT;
/*      */     } 
/*      */     
/* 4169 */     this.sendExit = readExit(dc, buffer, pos, SIZEOF_SEND_EXIT, cp, tls);
/* 4170 */     if (fromCCDT) {
/* 4171 */       pos += 128;
/*      */     } else {
/*      */       
/* 4174 */       pos += SIZEOF_SEND_EXIT;
/*      */     } 
/*      */     
/* 4177 */     this.receiveExit = readExit(dc, buffer, pos, SIZEOF_RECEIVE_EXIT, cp, tls);
/* 4178 */     if (fromCCDT) {
/* 4179 */       pos += 128;
/*      */     } else {
/*      */       
/* 4182 */       pos += SIZEOF_RECEIVE_EXIT;
/*      */     } 
/*      */     
/* 4185 */     this.seqNumberWrap = dc.readI32(buffer, pos, swap);
/* 4186 */     pos += 4;
/*      */     
/* 4188 */     this.maxMsgLength = dc.readI32(buffer, pos, swap);
/* 4189 */     pos += 4;
/*      */     
/* 4191 */     this.putAuthority = dc.readI32(buffer, pos, swap);
/* 4192 */     pos += 4;
/*      */     
/* 4194 */     this.dataConversion = dc.readI32(buffer, pos, swap);
/* 4195 */     pos += 4;
/*      */     
/* 4197 */     System.arraycopy(buffer, pos, this.securityUserData, 0, 32);
/* 4198 */     pos += 32;
/*      */     
/* 4200 */     System.arraycopy(buffer, pos, this.msgUserData, 0, 32);
/* 4201 */     pos += 32;
/*      */     
/* 4203 */     System.arraycopy(buffer, pos, this.sendUserData, 0, 32);
/* 4204 */     pos += 32;
/*      */     
/* 4206 */     System.arraycopy(buffer, pos, this.receiveUserData, 0, 32);
/* 4207 */     pos += 32;
/* 4208 */     if (this.version >= 2) {
/*      */       
/* 4210 */       this.userIdentifier = dc.readField(buffer, pos, 12, cp, tls);
/* 4211 */       pos += 12;
/*      */       
/* 4213 */       this.password = dc.readField(buffer, pos, 12, cp, tls);
/* 4214 */       pos += 12;
/*      */       
/* 4216 */       this.mcaUserIdentifier = dc.readField(buffer, pos, 12, cp, tls);
/* 4217 */       pos += 12;
/*      */       
/* 4219 */       this.mcaType = dc.readI32(buffer, pos, swap);
/* 4220 */       pos += 4;
/*      */       
/* 4222 */       this.connectionName = dc.readField(buffer, pos, 264, cp, tls);
/* 4223 */       pos += 264;
/*      */       
/* 4225 */       this.remoteUserIdentifier = dc.readField(buffer, pos, 12, cp, tls);
/* 4226 */       pos += 12;
/*      */       
/* 4228 */       this.remotePassword = dc.readField(buffer, pos, 12, cp, tls);
/* 4229 */       pos += 12;
/*      */     } 
/* 4231 */     if (this.version >= 3) {
/*      */       
/* 4233 */       this.msgRetryExit = dc.readField(buffer, pos, SIZEOF_MSG_RETRY_EXIT, cp, tls);
/* 4234 */       if (fromCCDT) {
/* 4235 */         pos += 128;
/*      */       } else {
/*      */         
/* 4238 */         pos += SIZEOF_MSG_RETRY_EXIT;
/*      */       } 
/*      */       
/* 4241 */       this.msgRetryUserData = dc.readField(buffer, pos, 32, cp, tls);
/* 4242 */       pos += 32;
/*      */       
/* 4244 */       this.msgRetryCount = dc.readI32(buffer, pos, swap);
/* 4245 */       pos += 4;
/*      */       
/* 4247 */       this.msgRetryInterval = dc.readI32(buffer, pos, swap);
/* 4248 */       pos += 4;
/*      */     } 
/* 4250 */     if (this.version >= 4) {
/*      */       
/* 4252 */       this.heartbeatInterval = dc.readI32(buffer, pos, swap);
/* 4253 */       pos += 4;
/*      */       
/* 4255 */       this.batchInterval = dc.readI32(buffer, pos, swap);
/* 4256 */       pos += 4;
/*      */       
/* 4258 */       this.nonPersistentMsgSpeed = dc.readI32(buffer, pos, swap);
/* 4259 */       pos += 4;
/*      */       
/* 4261 */       this.strucLength = dc.readI32(buffer, pos, swap);
/* 4262 */       pos += 4;
/*      */ 
/*      */ 
/*      */       
/* 4266 */       pos += 4;
/*      */       
/* 4268 */       pos += 4;
/*      */       
/* 4270 */       this.msgExitsDefined = dc.readI32(buffer, pos, swap);
/* 4271 */       pos += 4;
/*      */       
/* 4273 */       this.sendExitsDefined = dc.readI32(buffer, pos, swap);
/* 4274 */       pos += 4;
/*      */       
/* 4276 */       this.receiveExitsDefined = dc.readI32(buffer, pos, swap);
/* 4277 */       pos += 4;
/*      */       
/* 4279 */       pos += JmqiTools.alignToGrain(ptrSize, pos);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4288 */       pos += 6 * ptrSize;
/*      */     } 
/* 4290 */     if (this.version >= 5) {
/*      */       
/* 4292 */       pos += ptrSize;
/*      */       
/* 4294 */       this.clustersDefined = dc.readI32(buffer, pos, swap);
/* 4295 */       pos += 4;
/*      */       
/* 4297 */       this.networkPriority = dc.readI32(buffer, pos, swap);
/* 4298 */       pos += 4;
/*      */     } 
/* 4300 */     if (this.version >= 6) {
/*      */ 
/*      */ 
/*      */       
/* 4304 */       pos += 8 + ptrSize + ptrSize;
/*      */       
/* 4306 */       System.arraycopy(buffer, pos, this.mcaSecurityId, 0, 40);
/* 4307 */       pos += 40;
/*      */       
/* 4309 */       System.arraycopy(buffer, pos, this.remoteSecurityId, 0, 40);
/* 4310 */       pos += 40;
/*      */     } 
/* 4312 */     if (this.version >= 7) {
/*      */       
/* 4314 */       this.sslCipherSpec = dc.readField(buffer, pos, 32, cp, tls);
/* 4315 */       pos += 32;
/*      */       
/* 4317 */       this.sslPeerName = null;
/* 4318 */       pos += ptrSize;
/*      */       
/* 4320 */       sslPeerNameLength = dc.readI32(buffer, pos, swap);
/* 4321 */       pos += 4;
/*      */       
/* 4323 */       this.sslClientAuth = dc.readI32(buffer, pos, swap);
/* 4324 */       pos += 4;
/*      */       
/* 4326 */       this.keepAliveInterval = dc.readI32(buffer, pos, swap);
/* 4327 */       pos += 4;
/*      */       
/* 4329 */       this.localAddress = dc.readField(buffer, pos, 48, cp, tls);
/* 4330 */       pos += 48;
/*      */       
/* 4332 */       this.batchHeartbeat = dc.readI32(buffer, pos, swap);
/* 4333 */       pos += 4;
/*      */     } 
/* 4335 */     if (this.version >= 8) {
/*      */       int i;
/* 4337 */       for (i = 0; i < 2; i++) {
/* 4338 */         this.hdrCompList[i] = dc.readI32(buffer, pos, swap);
/* 4339 */         pos += 4;
/*      */       } 
/*      */       
/* 4342 */       for (i = 0; i < 16; i++) {
/* 4343 */         this.msgCompList[i] = dc.readI32(buffer, pos, swap);
/* 4344 */         pos += 4;
/*      */       } 
/*      */       
/* 4347 */       this.clwlChannelRank = dc.readI32(buffer, pos, swap);
/* 4348 */       pos += 4;
/*      */       
/* 4350 */       this.clwlChannelPriority = dc.readI32(buffer, pos, swap);
/* 4351 */       pos += 4;
/*      */       
/* 4353 */       this.clwlChannelWeight = dc.readI32(buffer, pos, swap);
/* 4354 */       pos += 4;
/*      */       
/* 4356 */       this.channelMonitoring = dc.readI32(buffer, pos, swap);
/* 4357 */       pos += 4;
/*      */       
/* 4359 */       this.channelStatistics = dc.readI32(buffer, pos, swap);
/* 4360 */       pos += 4;
/*      */     } 
/* 4362 */     if (this.version == 8)
/*      */     {
/* 4364 */       pos += JmqiTools.alignToGrain(ptrSize, pos);
/*      */     }
/* 4366 */     if (this.version >= 9) {
/*      */       
/* 4368 */       this.SharingConversations = dc.readI32(buffer, pos, swap);
/* 4369 */       pos += 4;
/*      */       
/* 4371 */       this.propertyControl = dc.readI32(buffer, pos, swap);
/* 4372 */       pos += 4;
/*      */       
/* 4374 */       this.maxInstances = dc.readI32(buffer, pos, swap);
/* 4375 */       pos += 4;
/*      */       
/* 4377 */       this.maxInstancesPerClient = dc.readI32(buffer, pos, swap);
/* 4378 */       pos += 4;
/*      */       
/* 4380 */       this.clientChannelWeight = dc.readI32(buffer, pos, swap);
/* 4381 */       pos += 4;
/*      */       
/* 4383 */       this.connectionAffinity = dc.readI32(buffer, pos, swap);
/* 4384 */       pos += 4;
/*      */     } 
/* 4386 */     if (this.version == 9)
/*      */     {
/* 4388 */       pos += JmqiTools.alignToGrain(ptrSize, pos);
/*      */     }
/* 4390 */     if (this.version >= 10) {
/*      */       
/* 4392 */       this.batchDataLimit = dc.readI32(buffer, pos, swap);
/* 4393 */       pos += 4;
/*      */       
/* 4395 */       this.useDLQ = dc.readI32(buffer, pos, swap);
/* 4396 */       pos += 4;
/*      */       
/* 4398 */       this.defReconnect = dc.readI32(buffer, pos, swap);
/* 4399 */       pos += 4;
/*      */     } 
/* 4401 */     if (this.version >= 11) {
/*      */       
/* 4403 */       setCertificateLabel(dc.readField(buffer, pos, 64, cp, tls));
/* 4404 */       pos += 64;
/*      */     } 
/* 4406 */     if (this.version >= 12) {
/*      */       
/* 4408 */       this.splProtection = dc.readI32(buffer, pos, swap);
/* 4409 */       pos += 4;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4418 */     int privateBase = (this.version >= 4) ? (offset + this.strucLength) : pos;
/*      */     
/* 4420 */     int lengthVariable = 0;
/* 4421 */     int lengthExitsVariable = 0;
/* 4422 */     if (fromCCDT && this.version >= 4)
/*      */     {
/* 4424 */       if (limit <= privateBase) {
/* 4425 */         fromCCDT = false;
/*      */       } else {
/*      */         
/* 4428 */         lengthExitsVariable = dc.readI32(buffer, privateBase + 8, swap);
/* 4429 */         lengthVariable = lengthExitsVariable;
/* 4430 */         if (this.version >= 7) {
/* 4431 */           lengthVariable += sslPeerNameLength;
/*      */         }
/*      */         
/* 4434 */         if (this.version >= 6) {
/* 4435 */           this.mcaUserIdentifier = dc.readField(buffer, privateBase + 68, 64, cp, tls);
/*      */         }
/*      */       } 
/*      */     }
/*      */     
/* 4440 */     int variableBase = privateBase + 132;
/*      */     
/* 4442 */     this.exitNameLength = JmqiEnvironment.getMqExitNameLength();
/* 4443 */     this.exitDataLength = 32;
/*      */ 
/*      */     
/* 4446 */     if (fromCCDT && this.version >= 5 && lengthVariable > 0) {
/*      */       
/* 4448 */       pos = variableBase;
/*      */ 
/*      */       
/* 4451 */       byte[] exitsPart = new byte[lengthExitsVariable];
/* 4452 */       System.arraycopy(buffer, pos, exitsPart, 0, lengthExitsVariable);
/* 4453 */       pos += lengthExitsVariable;
/*      */       
/* 4455 */       byte[][][] exitsInCCDT = parseExitBytes(exitsPart);
/*      */ 
/*      */       
/* 4458 */       if (exitsInCCDT.length != 6) {
/*      */         
/* 4460 */         JmqiException e = new JmqiException(this.env, -1, null, 2, 2277, null);
/* 4461 */         if (Trace.isOn) {
/* 4462 */           Trace.throwing(this, "com.ibm.mq.exits.MQCD", "readFromBuffer(byte [ ],int,int,boolean,int,boolean,JmqiCodepage,JmqiTls)", (Throwable)e);
/*      */         }
/*      */         
/* 4465 */         throw e;
/*      */       } 
/*      */ 
/*      */       
/* 4469 */       byte[][] msgExitsArray = exitsInCCDT[0];
/* 4470 */       this.msgExitsDefined = msgExitsArray.length;
/* 4471 */       this.msgExitPtr = new byte[this.msgExitsDefined * JmqiEnvironment.getMqExitNameLength()];
/* 4472 */       for (int i = 0; i < this.msgExitsDefined; i++) {
/* 4473 */         paddedArrayCopy(msgExitsArray[i], 0, this.msgExitPtr, i * JmqiEnvironment.getMqExitNameLength(), JmqiEnvironment.getMqExitNameLength());
/*      */       }
/*      */       
/* 4476 */       byte[][] msgUserDataArray = exitsInCCDT[1];
/* 4477 */       this.msgUserDataPtr = new byte[this.msgExitsDefined * 32];
/* 4478 */       for (int j = 0; j < this.msgExitsDefined; j++) {
/* 4479 */         if (msgUserDataArray.length > j) {
/* 4480 */           paddedArrayCopy(msgUserDataArray[j], 0, this.msgUserDataPtr, j * 32, 32);
/*      */         } else {
/*      */           
/* 4483 */           int fromIndex = j * 32;
/* 4484 */           int toIndex = fromIndex + 32 - 1;
/* 4485 */           Arrays.fill(this.msgUserDataPtr, fromIndex, toIndex, (byte)0);
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 4490 */       byte[][] sendExitsArray = exitsInCCDT[2];
/* 4491 */       this.sendExitsDefined = sendExitsArray.length;
/* 4492 */       this.sendExitPtr = new byte[this.sendExitsDefined * JmqiEnvironment.getMqExitNameLength()];
/* 4493 */       for (int k = 0; k < this.sendExitsDefined; k++) {
/* 4494 */         paddedArrayCopy(sendExitsArray[k], 0, this.sendExitPtr, k * JmqiEnvironment.getMqExitNameLength(), JmqiEnvironment.getMqExitNameLength());
/*      */       }
/*      */ 
/*      */       
/* 4498 */       if (getSendExitPtr().trim().length() == 0) {
/* 4499 */         if (Trace.isOn) {
/* 4500 */           Trace.data(this, "readFromBuffer(byte[],int,int,boolean,int,boolean,JmqiCodepage,JmqiTls)", "sendExitsDefined=" + 
/*      */               
/* 4502 */               String.valueOf(this.sendExitsDefined) + " sendExitPtr = " + getSendExitPtr());
/*      */         }
/* 4504 */         this.sendExitsDefined = 0;
/* 4505 */         this.sendExitPtr = new byte[0];
/*      */       } 
/*      */ 
/*      */       
/* 4509 */       byte[][] sendUserDataArray = exitsInCCDT[3];
/* 4510 */       this.sendUserDataPtr = new byte[this.sendExitsDefined * 32];
/* 4511 */       for (int m = 0; m < this.sendExitsDefined; m++) {
/* 4512 */         if (sendUserDataArray.length > m) {
/* 4513 */           paddedArrayCopy(sendUserDataArray[m], 0, this.sendUserDataPtr, m * 32, 32);
/*      */         } else {
/*      */           
/* 4516 */           int fromIndex = m * 32;
/* 4517 */           int toIndex = fromIndex + 32 - 1;
/* 4518 */           Arrays.fill(this.sendUserDataPtr, fromIndex, toIndex, (byte)0);
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 4523 */       byte[][] receiveExitsArray = exitsInCCDT[4];
/* 4524 */       this.receiveExitsDefined = receiveExitsArray.length;
/* 4525 */       this.receiveExitPtr = new byte[this.receiveExitsDefined * JmqiEnvironment.getMqExitNameLength()];
/* 4526 */       for (int n = 0; n < this.receiveExitsDefined; n++) {
/* 4527 */         paddedArrayCopy(receiveExitsArray[n], 0, this.receiveExitPtr, n * 
/*      */ 
/*      */             
/* 4530 */             JmqiEnvironment.getMqExitNameLength(), 
/* 4531 */             JmqiEnvironment.getMqExitNameLength());
/*      */       }
/*      */ 
/*      */       
/* 4535 */       if (getReceiveExitPtr().trim().length() == 0) {
/* 4536 */         if (Trace.isOn) {
/* 4537 */           Trace.data(this, "readFromBuffer(byte[],int,int,boolean,int,boolean,JmqiCodepage,JmqiTls)", "receiveExitsDefined=" + 
/*      */               
/* 4539 */               String.valueOf(this.receiveExitsDefined) + " receiveExitPtr = " + getReceiveExitPtr());
/*      */         }
/* 4541 */         this.receiveExitsDefined = 0;
/* 4542 */         this.receiveExitPtr = new byte[0];
/*      */       } 
/*      */ 
/*      */       
/* 4546 */       byte[][] receiveUserDataArray = exitsInCCDT[5];
/* 4547 */       this.receiveUserDataPtr = new byte[this.receiveExitsDefined * 32];
/* 4548 */       for (int i1 = 0; i1 < this.receiveExitsDefined; i1++) {
/* 4549 */         if (receiveUserDataArray.length > i1) {
/* 4550 */           paddedArrayCopy(receiveUserDataArray[i1], 0, this.receiveUserDataPtr, i1 * 32, 32);
/*      */         } else {
/*      */           
/* 4553 */           int fromIndex = i1 * 32;
/* 4554 */           int toIndex = fromIndex + 32 - 1;
/* 4555 */           Arrays.fill(this.receiveUserDataPtr, fromIndex, toIndex, (byte)0);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 4562 */     if (sslPeerNameLength > 0) {
/*      */ 
/*      */       
/* 4565 */       short s = (short)dc.readU16(buffer, pos, swap);
/* 4566 */       if (s == 0 && 
/* 4567 */         pos % 4 != 0) {
/* 4568 */         pos += 4 - pos % 4;
/*      */       }
/*      */       
/* 4571 */       if (pos + sslPeerNameLength <= limit) {
/* 4572 */         this.sslPeerName = dc.readField(buffer, pos, sslPeerNameLength, cp, tls);
/* 4573 */         pos += sslPeerNameLength;
/*      */       } 
/*      */     } 
/*      */     
/* 4577 */     if (Trace.isOn) {
/* 4578 */       Trace.exit(this, "com.ibm.mq.exits.MQCD", "readFromBuffer(byte [ ],int,int,boolean,int,boolean,JmqiCodepage,JmqiTls)", 
/*      */           
/* 4580 */           Integer.valueOf(pos));
/*      */     }
/* 4582 */     return pos;
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String readExit(JmqiDC dc, byte[] buffer, int pos, int nameLength, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 4599 */     if (Trace.isOn) {
/* 4600 */       Trace.entry(this, "com.ibm.mq.exits.MQCD", "readExit(JmqiDC,byte [ ],int,int,JmqiCodepage,JmqiTls)", new Object[] { dc, buffer, 
/*      */             
/* 4602 */             Integer.valueOf(pos), Integer.valueOf(nameLength), cp, tls });
/*      */     }
/* 4604 */     String exitName = dc.readField(buffer, pos, nameLength, cp, tls);
/* 4605 */     char[] exitNameChars = exitName.toCharArray();
/* 4606 */     for (int i = exitNameChars.length - 1; i >= 0 && (
/* 4607 */       exitNameChars[i] == '\000' || exitNameChars[i] == ''); i--)
/*      */     {
/*      */ 
/*      */       
/* 4611 */       exitNameChars[i] = ' ';
/*      */     }
/* 4613 */     String traceRet1 = newString(exitNameChars);
/* 4614 */     if (Trace.isOn) {
/* 4615 */       Trace.exit(this, "com.ibm.mq.exits.MQCD", "readExit(JmqiDC,byte [ ],int,int,JmqiCodepage,JmqiTls)", traceRet1);
/*      */     }
/*      */     
/* 4618 */     return traceRet1;
/*      */   }
/*      */   
/*      */   private static String newString(char[] data) {
/* 4622 */     return new String(data);
/*      */   }
/*      */ 
/*      */   
/*      */   private static String newString(byte[] data) {
/* 4627 */     String result = null;
/*      */     try {
/* 4629 */       result = new String(data, "US-ASCII");
/*      */     }
/* 4631 */     catch (UnsupportedEncodingException unsupportedEncodingException) {}
/*      */ 
/*      */     
/* 4634 */     return result;
/*      */   }
/*      */   
/*      */   private static byte[] getBytes(String data) {
/* 4638 */     byte[] result = null;
/*      */     try {
/* 4640 */       result = data.getBytes("US-ASCII");
/*      */     }
/* 4642 */     catch (UnsupportedEncodingException unsupportedEncodingException) {}
/*      */ 
/*      */     
/* 4645 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   static {
/*      */     try {
/* 4651 */       aSpace = " ".getBytes("US-ASCII")[0];
/*      */     }
/* 4653 */     catch (UnsupportedEncodingException unsupportedEncodingException) {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void paddedArrayCopy(byte[] src, int srcOffset, byte[] dest, int destOffset, int length) {
/* 4660 */     int lengthToCopy = Math.min(src.length, length);
/* 4661 */     System.arraycopy(src, srcOffset, dest, destOffset, lengthToCopy);
/*      */ 
/*      */     
/* 4664 */     Arrays.fill(dest, destOffset + lengthToCopy, destOffset + length, aSpace);
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
/*      */   public void addFieldsToFormatter(JmqiStructureFormatter fmt) {
/* 4676 */     fmt.add("channelName", this.channelName);
/* 4677 */     fmt.add("version", this.version);
/* 4678 */     fmt.add("channelType", this.channelType);
/* 4679 */     fmt.add("transportType", this.transportType);
/* 4680 */     fmt.add("desc", this.desc);
/* 4681 */     fmt.add("qMgrName", this.qMgrName);
/* 4682 */     fmt.add("xmitQName", this.xmitQName);
/* 4683 */     fmt.add("connectionName", this.connectionName);
/* 4684 */     fmt.add("mcaName", this.mcaName);
/* 4685 */     fmt.add("modeName", this.modeName);
/* 4686 */     fmt.add("tpName", this.tpName);
/* 4687 */     fmt.add("batchSize", this.batchSize);
/* 4688 */     fmt.add("discInterval", this.discInterval);
/* 4689 */     fmt.add("shortRetryCount", this.shortRetryCount);
/* 4690 */     fmt.add("shortRetryInterval", this.shortRetryInterval);
/* 4691 */     fmt.add("longRetryCount", this.longRetryCount);
/* 4692 */     fmt.add("longRetryInterval", this.longRetryInterval);
/* 4693 */     fmt.add("seqNumberWrap", this.seqNumberWrap);
/* 4694 */     fmt.add("maxMsgLength", this.maxMsgLength);
/* 4695 */     fmt.add("putAuthority", this.putAuthority);
/* 4696 */     fmt.add("dataConversion", this.dataConversion);
/* 4697 */     fmt.add("userIdentifier", this.userIdentifier);
/* 4698 */     fmt.add("password", JmqiTools.tracePassword(this.password));
/* 4699 */     fmt.add("mcaUserIdentifier", this.mcaUserIdentifier);
/* 4700 */     fmt.add("mcaType", this.mcaType);
/* 4701 */     fmt.add("remoteUserIdentifier", this.remoteUserIdentifier);
/* 4702 */     fmt.add("msgRetryExit", this.msgRetryExit);
/* 4703 */     fmt.add("msgRetryUserData", this.msgRetryUserData);
/* 4704 */     fmt.add("msgRetryCount", this.msgRetryCount);
/* 4705 */     fmt.add("heartbeatInterval", this.heartbeatInterval);
/* 4706 */     fmt.add("batchInterval", this.batchInterval);
/* 4707 */     fmt.add("nonPersistentMsgSpeed", this.nonPersistentMsgSpeed);
/* 4708 */     fmt.add("clustersDefined", this.clustersDefined);
/* 4709 */     fmt.add("networkPriority", this.networkPriority);
/* 4710 */     fmt.add("mcaSecurityId", this.mcaSecurityId);
/* 4711 */     fmt.add("remoteSecurityId", this.remoteSecurityId);
/* 4712 */     fmt.add("sslCipherSpec", this.sslCipherSpec);
/* 4713 */     fmt.add("sslPeerName", this.sslPeerName);
/* 4714 */     fmt.add("sslClientAuth", this.sslClientAuth);
/* 4715 */     fmt.add("keepAliveInterval", this.keepAliveInterval);
/* 4716 */     fmt.add("localAddress", this.localAddress);
/* 4717 */     fmt.add("batchHeartbeat", this.batchHeartbeat);
/* 4718 */     fmt.add("hdrCompList", this.hdrCompList);
/* 4719 */     fmt.add("msgCompList", this.msgCompList);
/* 4720 */     fmt.add("clwlChannelRank", this.clwlChannelRank);
/* 4721 */     fmt.add("clwlChannelPriority", this.clwlChannelPriority);
/* 4722 */     fmt.add("clwlChannelWeight", this.clwlChannelWeight);
/* 4723 */     fmt.add("channelMonitoring", this.channelMonitoring);
/* 4724 */     fmt.add("channelStatistics", this.channelStatistics);
/* 4725 */     fmt.add("exitNameLength", this.exitNameLength);
/* 4726 */     fmt.add("exitDataLength", this.exitDataLength);
/* 4727 */     fmt.add("sendExitsDefined", this.sendExitsDefined);
/* 4728 */     fmt.add("sendExit", this.sendExit);
/* 4729 */     fmt.add("sendUserData", this.sendUserData);
/* 4730 */     fmt.add("sendExitPtr", this.sendExitPtr);
/* 4731 */     fmt.add("sendUserDataPtr", this.sendUserDataPtr);
/* 4732 */     fmt.add("receiveExitsDefined", this.receiveExitsDefined);
/* 4733 */     fmt.add("receiveExit", this.receiveExit);
/* 4734 */     fmt.add("receiveUserData", this.receiveUserData);
/* 4735 */     fmt.add("receiveExitPtr", this.receiveExitPtr);
/* 4736 */     fmt.add("ReceiveUserDataPtr", this.receiveUserDataPtr);
/* 4737 */     fmt.add("SharingConversations", this.SharingConversations);
/* 4738 */     fmt.add("propertyControl", this.propertyControl);
/* 4739 */     fmt.add("maxInstances", this.maxInstances);
/* 4740 */     fmt.add("maxInstancesPerClient", this.maxInstancesPerClient);
/* 4741 */     fmt.add("clientChannelWeight", this.clientChannelWeight);
/* 4742 */     fmt.add("connectionAffinity", this.connectionAffinity);
/* 4743 */     fmt.add("batchDataLimit", this.batchDataLimit);
/* 4744 */     fmt.add("useDLQ", this.useDLQ);
/* 4745 */     fmt.add("defReconnect", this.defReconnect);
/* 4746 */     fmt.add("certificateLabel", this.certificateLabel);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static byte[][][] parseExitBytes(byte[] buffer) throws JmqiException {
/* 4757 */     if (Trace.isOn) {
/* 4758 */       Trace.entry("com.ibm.mq.exits.MQCD", "parseExitBytes(byte [ ])", new Object[] { buffer });
/*      */     }
/*      */     
/* 4761 */     int numSegments = 0;
/* 4762 */     for (int i = 0; i < buffer.length; i++) {
/* 4763 */       if (buffer[i] == 1) {
/* 4764 */         numSegments++;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 4769 */     int[] segmentSizeArray = new int[numSegments];
/* 4770 */     Arrays.fill(segmentSizeArray, 0);
/* 4771 */     int currentSegment = 0;
/* 4772 */     for (int j = 0; j < buffer.length; j++) {
/* 4773 */       if (buffer[j] == 1) {
/* 4774 */         currentSegment++;
/*      */       }
/* 4776 */       else if (buffer[j] == 2) {
/* 4777 */         segmentSizeArray[currentSegment] = segmentSizeArray[currentSegment] + 1;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 4782 */     byte[][][] segmentArray = new byte[numSegments][][];
/* 4783 */     for (int k = 0; k < segmentArray.length; k++) {
/* 4784 */       segmentArray[k] = new byte[segmentSizeArray[k]][];
/*      */     }
/*      */ 
/*      */     
/* 4788 */     int sectionStart = 0;
/* 4789 */     int currentSection = 0;
/*      */     
/* 4791 */     currentSegment = 0;
/* 4792 */     for (int currentByte = 0; currentByte < buffer.length; currentByte++) {
/* 4793 */       if (buffer[currentByte] == 1) {
/* 4794 */         currentSegment++;
/* 4795 */         currentSection = 0;
/* 4796 */         sectionStart = currentByte + 1;
/*      */       }
/* 4798 */       else if (buffer[currentByte] == 2) {
/* 4799 */         int sectionSize = currentByte - sectionStart;
/* 4800 */         byte[] currentSectionBytes = new byte[sectionSize];
/* 4801 */         System.arraycopy(buffer, sectionStart, currentSectionBytes, 0, sectionSize);
/* 4802 */         segmentArray[currentSegment][currentSection] = currentSectionBytes;
/* 4803 */         currentSection++;
/* 4804 */         sectionStart = currentByte + 1;
/*      */       } 
/*      */     } 
/* 4807 */     if (Trace.isOn) {
/* 4808 */       Trace.exit("com.ibm.mq.exits.MQCD", "parseExitBytes(byte [ ])", segmentArray);
/*      */     }
/* 4810 */     return segmentArray;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setupForTest(int ptrSize) {
/* 4818 */     if (Trace.isOn) {
/* 4819 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "setupForTest(int)", "setter", 
/* 4820 */           Integer.valueOf(ptrSize));
/*      */     }
/*      */     
/* 4823 */     int exitNamelength = 128;
/* 4824 */     if (ptrSize == 16) {
/* 4825 */       exitNamelength = 20;
/*      */     }
/* 4827 */     setExitNameSizes(exitNamelength);
/*      */   }
/*      */   
/*      */   private static void setExitNameSizes(int exitNamelength) {
/* 4831 */     SIZEOF_SECURITY_EXIT = exitNamelength;
/* 4832 */     SIZEOF_MSG_EXIT = exitNamelength;
/* 4833 */     SIZEOF_SEND_EXIT = exitNamelength;
/* 4834 */     SIZEOF_RECEIVE_EXIT = exitNamelength;
/* 4835 */     SIZEOF_MSG_RETRY_EXIT = exitNamelength;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getFirstVersion() {
/* 4843 */     if (Trace.isOn) {
/* 4844 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getFirstVersion()", "getter", 
/* 4845 */           Integer.valueOf(1));
/*      */     }
/* 4847 */     return 1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCurrentVersion() {
/* 4855 */     if (Trace.isOn) {
/* 4856 */       Trace.data(this, "com.ibm.mq.exits.MQCD", "getCurrentVersion()", "getter", 
/* 4857 */           Integer.valueOf(12));
/*      */     }
/* 4859 */     return 12;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\exits\MQCD.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */