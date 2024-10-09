/*      */ package com.ibm.mq.jmqi.system;
/*      */ 
/*      */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*      */ import com.ibm.mq.jmqi.JmqiException;
/*      */ import com.ibm.mq.jmqi.internal.AbstractMqiStructure;
/*      */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*      */ import com.ibm.mq.jmqi.internal.JmqiStructureFormatter;
/*      */ import com.ibm.mq.jmqi.internal.JmqiTools;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.lang.ref.Reference;
/*      */ import java.lang.ref.SoftReference;
/*      */ import java.lang.reflect.Field;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class LpiPrivConnStruct
/*      */   extends AbstractMqiStructure
/*      */ {
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/LpiPrivConnStruct.java";
/*      */   public static final String lpiPrivConnSTRUCID = "LCNO";
/*      */   public static final int lpiPrivConnVERSION1 = 1;
/*      */   public static final int lpiPrivConnVERSION2 = 2;
/*      */   public static final int lpiPrivConnVERSION3 = 3;
/*      */   public static final int lpiPrivConnVERSION4 = 4;
/*      */   public static final int lpiPrivConnVERSION = 4;
/*      */   public static final int lpiPrivOpt_INTERNAL = 1;
/*      */   public static final int lpiPrivOpt_SEGMENT_NOCONVERT = 2;
/*      */   public static final int lpiPrivOpt_TRUSTED = 4;
/*      */   public static final int lpiPrivOpt_NO_API_EXITS = 8;
/*      */   public static final int lpiPrivOpt_NORUNAWAY = 16;
/*      */   public static final int lpiPrivOpt_SHUTDOWN_ENABLED = 32;
/*      */   public static final int lpiPrivOpt_ALLOW_PENDING_DISC = 64;
/*      */   public static final int lpiPrivOpt_RUNTIME = 128;
/*      */   public static final int lpiPrivOpt_MOVER = 256;
/*      */   public static final int lpiPrivOpt_REPOS_SUBSCRIBE = 512;
/*      */   public static final int lpiPrivOpt_PUT_BY_UUID = 1024;
/*      */   public static final int lpiPrivOpt_NO_CLUSTER_EXIT = 2048;
/*      */   public static final int lpiPrivOpt_NO_SUBSCRIBE = 4096;
/*      */   public static final int lpiPrivOpt_PUT_BY_OFFSET = 8192;
/*      */   public static final int lpiPrivOpt_USER_APP = 16384;
/*      */   public static final int lpiPrivOpt_PRESUME_ABORT = 32768;
/*      */   public static final int lpiPrivOpt_EXIT_ON_QMEND = 32768;
/*      */   public static final int lpiPrivOpt_AS400CMTCTRLACTIVE = 65536;
/*      */   public static final int lpiPrivOpt_CREATING = 131072;
/*      */   public static final int lpiPrivOpt_ALLOW_CANCEL = 524288;
/*      */   public static final int lpiPrivOpt_IGNORE_FORCED_SHUTDOWN = 1048576;
/*      */   public static final int lpiPrivOpt_QUIESCE_NOWAIT = 2097152;
/*      */   public static final int lpiPrivOpt_EARLY = 4194304;
/*      */   public static final int lpiPrivOpt_TRUSTED_SHARED_HCONN = 8388608;
/*      */   public static final int lpiPrivOpt_RECONNECTING = 16777216;
/*      */   public static final int lpiPrivOpt_RECONNECTABLE = 33554432;
/*      */   public static final int lpiPrivOpt_lpiSPIMQConnect = 67108864;
/*      */   public static final int lpiPrivOpt_CLUSRCVR_CHL = 134217728;
/*      */   public static final int lpiPrivOpt_ASYNC_COMMIT = 268435456;
/*      */   public static final int lpiPrivOpt_NO_MONITORING = 536870912;
/*      */   public static final int lpiPrivOpt_INHIBIT_STOP_CONN = 1073741824;
/*      */   public static final int lpiPrivOpt_HIDDEN = -2147483648;
/*      */   public static final int lpiCONNECTION_CLASS_DEFAULT = 0;
/*      */   public static final int lpiCONNECTION_CLASS_SERVER = 1;
/*      */   public static final int lpiCONNECTION_CLASS_CLIENT = 2;
/*      */   public static final int lpiAUTH_TOKEN_NULL = -1;
/*      */   public static final int lpiPrivType_UNIDENTIFIED = 0;
/*      */   public static final int lpiPrivType_MQCONN = 1;
/*      */   public static final int lpiPrivType_MQCONNX = 2;
/*      */   public static final int lpiPrivType_AMQLDMPA = 3;
/*      */   public static final int lpiPrivType_AMQLREPA = 4;
/*      */   public static final int lpiPrivType_WRKMQM = 5;
/*      */   public static final int lpiPrivType_ENDMQCSV = 6;
/*      */   public static final int lpiPrivType_WRKMQMQST = 7;
/*      */   public static final int lpiPrivType_CMD_SERVER = 8;
/*      */   public static final int lpiPrivType_REMOTE_CMD_PROCESSOR = 9;
/*      */   public static final int lpiPrivType_CHINIT = 10;
/*      */   public static final int lpiPrivType_REPOSITORY = 11;
/*      */   public static final int lpiPrivType_RUNMQSC = 12;
/*      */   public static final int lpiPrivType_DEFERRED_MSG = 13;
/*      */   public static final int lpiPrivType_OAM = 14;
/*      */   public static final int lpiPrivType_BROWSE_MARK_SCANNER = 15;
/*      */   public static final int lpiPrivType_NAMELIST_CACHE = 16;
/*      */   public static final int lpiPrivType_STATISTICS_COLLECTOR = 17;
/*      */   public static final int lpiPrivType_ENDMQM = 18;
/*      */   public static final int lpiPrivType_PUBSUB_INITIATOR = 19;
/*      */   public static final int lpiPrivType_CONTENT_FILTERING = 20;
/*      */   public static final int lpiPrivType_ANCILLARY_PROCESS_START = 21;
/*      */   public static final int lpiPrivType_ANCILLARY_PROCESS_STOP = 22;
/*      */   public static final int lpiPrivType_RECONCILE_DURABLE_SUBS = 23;
/*      */   public static final int lpiPrivType_CONN_AUTH_EVENT = 24;
/*      */   public static final int lpiPrivType_START_STOP_EVENT = 25;
/*      */   public static final int lpiPrivType_PUT_QMGR_MSG = 26;
/*      */   public static final int lpiPrivType_CLEANUP_MANAGED_DESTS = 27;
/*      */   public static final int lpiPrivType_Q_DELETION = 28;
/*      */   public static final int lpiPrivType_EXPIRER = 29;
/*      */   public static final int lpiPrivType_LOGGER_EVENT = 30;
/*      */   public static final int lpiPrivType_RESTORE_RETAINED_PUBS = 31;
/*      */   public static final int lpiPrivType_POST_INIT_SETUP = 32;
/*      */   public static final int lpiPrivType_CREATE_DEFAULT_OBJS = 33;
/*      */   public static final int lpiPrivType_DISTRIBUTED_PUBSUB = 34;
/*      */   public static final int lpiPrivType_PUBSUB_CONTROLLER = 35;
/*      */   public static final int lpiPrivType_MOVER = 36;
/*      */   public static final int lpiPrivType_JMQI = 37;
/*      */   public static final int lpiPrivType_PUBSUB_WORKER = 38;
/*      */   public static final int lpiPrivType_MIGRATE_WMB_PUBSUB = 39;
/*      */   public static final int lpiPrivType_PUBSUB_RESTARTABLE = 40;
/*      */   public static final int lpiPrivType_DISTRIBUTED_PUBSUB_CMD = 41;
/*      */   public static final int lpiPrivType_DISTRIBUTED_PUBSUB_FAN = 42;
/*      */   public static final int lpiPrivType_DISTRIBUTED_PUBSUB_PUB = 43;
/*      */   public static final int lpiPrivType_MULTICAST = 44;
/*      */   public static final int lpiPrivType_DSPMQFLS = 45;
/*      */   public static final int lpiPrivType_RCDMQIMG = 46;
/*      */   public static final int lpiPrivType_RCRMQOBJ = 47;
/*      */   public static final int lpiPrivType_DSPMQCSV = 48;
/*      */   public static final int lpiPrivType_DMPMQAUT = 49;
/*      */   public static final int lpiPrivType_SETMQAUT = 50;
/*      */   public static final int lpiPrivType_DSPMQAUT = 51;
/*      */   public static final int lpiPrivType_AMQZSLF0 = 52;
/*      */   public static final int lpiPrivType_RSVMQTRN = 53;
/*      */   public static final int lpiPrivType_DSPMQTRN = 54;
/*      */   public static final int lpiPrivType_MQXR = 55;
/*      */   public static final int lpiPrivType_SETMQSPL = 56;
/*      */   public static final int lpiPrivType_DSPMQSPL = 57;
/*      */   public static final int lpiPrivType_SWITCH_XMITQ = 58;
/*      */   public static final int lpiPrivType_DLTMQBRK = 59;
/*      */   public static final int lpiPrivType_RUNSWCHL = 60;
/*      */   public static final int lpiPrivType_AMQRFDM = 61;
/*      */   public static final int lpiPrivType_RESTARTABLE = 62;
/*      */   public static final int lpiPrivType_DELAYED_DELIVERY = 63;
/*      */   public static final int lpiPrivType_ACTVTRC_COLLECTOR = 64;
/*      */   public static final int lpiPrivType_RESOURCE_MONITOR = 65;
/*      */   public static final int lpiPrivType_AMQP = 66;
/*      */   public static final int lpiPrivType_TOPIC_TREE = 67;
/*      */   public static final int lpiPrivType_Q_MGR_STATUS = 68;
/*      */   public static final int lpiPrivType_WEB_ADMIN = 69;
/*      */   public static final int lpiPrivType_CLOUD_NOTIFY = 70;
/*      */   public static final int lpiPrivType_ENDMQSDE = 71;
/*      */   public static final int lpiPrivType_IQM_TASK = 72;
/*      */   public static final int lpiPrivType_AUTO_CONFIG = 73;
/*      */   public static final int lpiPrivType_NATIVEHA_REBUILD_CCDT = 74;
/*      */   public static final int lpiPrivType_LAST = 74;
/*      */   public static final int lpiPrivOpt_DEFAULT = 536871040;
/*      */   public static final int lpiPrivConnStruct_LENGTH_1_32BIT = 128;
/*      */   public static final int lpiPrivConnStruct_LENGTH_1_64BIT = 144;
/*      */   public static final int lpiPrivConnStruct_LENGTH_1_128BIT = 176;
/*      */   public static final int lpiPrivConnStruct_LENGTH_2_32BIT = 160;
/*      */   public static final int lpiPrivConnStruct_LENGTH_2_64BIT = 192;
/*      */   public static final int lpiPrivConnStruct_LENGTH_2_128BIT = 256;
/*      */   public static final int lpiPrivConnStruct_LENGTH_3_32BIT = 168;
/*      */   public static final int lpiPrivConnStruct_LENGTH_3_64BIT = 200;
/*      */   public static final int lpiPrivConnStruct_LENGTH_3_128BIT = 272;
/*      */   public static final int lpiPrivConnStruct_LENGTH_4_32BIT = 172;
/*      */   public static final int lpiPrivConnStruct_LENGTH_4_64BIT = 208;
/*      */   public static final int lpiPrivConnStruct_LENGTH_4_128BIT = 288;
/*      */   public static final int lpiPrivConnStruct_CURRENT_LENGTH_32BIT = 172;
/*      */   public static final int lpiPrivConnStruct_CURRENT_LENGTH_64BIT = 208;
/*      */   public static final int lpiPrivConnStruct_CURRENT_LENGTH_128BIT = 288;
/*      */   private static final int SIZEOF_STRUC_ID = 4;
/*      */   private static final int SIZEOF_VERSION = 4;
/*      */   private static final int SIZEOF_OPTIONS = 4;
/*      */   private static final int SIZEOF_CONNECTION_CLASS = 4;
/*      */   private static final int SIZEOF_AUTH_TOKEN = 4;
/*      */   private static final int SIZEOF_UUID = 48;
/*      */   private static final int SIZEOF_CLUSTER = 48;
/*      */   private static final int SIZEOF_QM_OFFSET = 4;
/*      */   private static final int SIZEOF_CMD_LEVEL = 4;
/*      */   private static final int SIZEOF_SUB_CMD_LEVEL = 16;
/*      */   private static final int SIZEOF_APPL_TYPE = 4;
/*  571 */   private int version = 4;
/*  572 */   private int options = 536871040;
/*  573 */   private int connectionClass = 0;
/*  574 */   private int authToken = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  582 */   private int environment = -1;
/*  583 */   private String uuid = "";
/*  584 */   private String cluster = "";
/*  585 */   private int qmOffset = 0;
/*  586 */   private int cmdLevel = -1;
/*  587 */   private int[] subCmdLevel = new int[] { 0, 0, 0, 0 };
/*  588 */   private int applType = 0;
/*      */ 
/*      */   
/*      */   private static Reference<Field[]> fieldsRef;
/*      */ 
/*      */ 
/*      */   
/*      */   protected LpiPrivConnStruct(JmqiEnvironment env) {
/*  596 */     super(env);
/*  597 */     if (Trace.isOn) {
/*  598 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiPrivConnStruct", "<init>(JmqiEnvironment)", new Object[] { env });
/*      */     }
/*      */     
/*  601 */     if (Trace.isOn) {
/*  602 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiPrivConnStruct", "<init>(JmqiEnvironment)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCurrentVersion() {
/*  612 */     if (Trace.isOn) {
/*  613 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiPrivConnStruct", "getCurrentVersion()", "getter", 
/*  614 */           Integer.valueOf(4));
/*      */     }
/*  616 */     return 4;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getFieldSizeV1(JmqiEnvironment env, int ptrSize) {
/*  627 */     int size = 0;
/*  628 */     size += 4;
/*  629 */     size += 4;
/*  630 */     size += 4;
/*  631 */     size += 4;
/*  632 */     size += 4;
/*  633 */     size += 48;
/*  634 */     size += 48;
/*  635 */     size += JmqiTools.alignToGrain(ptrSize, size);
/*  636 */     size += ptrSize;
/*  637 */     size += 4;
/*  638 */     size += JmqiTools.alignToGrain(ptrSize, size);
/*  639 */     size += ptrSize;
/*      */     
/*  641 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getSizeV1(JmqiEnvironment env, int ptrSize) {
/*  652 */     int size = getFieldSizeV1(env, ptrSize);
/*      */     
/*  654 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getFieldSizeV2(JmqiEnvironment env, int ptrSize) {
/*  665 */     int size = getFieldSizeV1(env, ptrSize);
/*  666 */     size += ptrSize;
/*  667 */     size += ptrSize;
/*  668 */     size += ptrSize;
/*  669 */     size += 4;
/*  670 */     size += 16;
/*      */     
/*  672 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getSizeV2(JmqiEnvironment env, int ptrSize) {
/*  683 */     int size = getFieldSizeV2(env, ptrSize);
/*  684 */     size += JmqiTools.alignToGrain(ptrSize, size);
/*      */     
/*  686 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getFieldSizeV3(JmqiEnvironment env, int ptrSize) {
/*  697 */     int size = getFieldSizeV2(env, ptrSize);
/*  698 */     size += 4;
/*  699 */     size += JmqiTools.alignToGrain(ptrSize, size);
/*  700 */     size += ptrSize;
/*      */     
/*  702 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getSizeV3(JmqiEnvironment env, int ptrSize) {
/*  713 */     int size = getFieldSizeV3(env, ptrSize);
/*      */ 
/*      */ 
/*      */     
/*  717 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getFieldSizeV4(JmqiEnvironment env, int ptrSize) {
/*  728 */     int size = getFieldSizeV3(env, ptrSize);
/*  729 */     size += ptrSize;
/*      */     
/*  731 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getSizeV4(JmqiEnvironment env, int ptrSize) {
/*  742 */     int size = getFieldSizeV4(env, ptrSize);
/*      */     
/*  744 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getSize(int ptrSize) throws JmqiException {
/*  755 */     if (Trace.isOn)
/*  756 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiPrivConnStruct", "getSize(int)", new Object[] {
/*  757 */             Integer.valueOf(ptrSize)
/*      */           }); 
/*  759 */     int traceRet1 = getSize(this.env, this.version, ptrSize);
/*  760 */     if (Trace.isOn) {
/*  761 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiPrivConnStruct", "getSize(int)", 
/*  762 */           Integer.valueOf(traceRet1));
/*      */     }
/*  764 */     return traceRet1;
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
/*      */   public static int getSize(JmqiEnvironment env, int version, int ptrSize) throws JmqiException {
/*      */     int size;
/*  780 */     switch (version) {
/*      */       case 1:
/*  782 */         size = getSizeV1(env, ptrSize);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  803 */         return size;case 2: size = getSizeV2(env, ptrSize); return size;case 3: size = getSizeV3(env, ptrSize); return size;case 4: size = getSizeV4(env, ptrSize); return size;
/*      */     } 
/*      */     JmqiException e = new JmqiException(env, -1, null, 2, 6128, null);
/*      */     throw e;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getRequiredBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/*  816 */     int size = getSize(this.env, this.version, ptrSize);
/*      */     
/*  818 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getVersion() {
/*  826 */     if (Trace.isOn) {
/*  827 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiPrivConnStruct", "getVersion()", "getter", 
/*  828 */           Integer.valueOf(this.version));
/*      */     }
/*  830 */     return this.version;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setVersion(int version) {
/*  838 */     if (Trace.isOn) {
/*  839 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiPrivConnStruct", "setVersion(int)", "setter", 
/*  840 */           Integer.valueOf(version));
/*      */     }
/*  842 */     this.version = version;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getOptions() {
/*  849 */     if (Trace.isOn) {
/*  850 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiPrivConnStruct", "getOptions()", "getter", 
/*  851 */           Integer.valueOf(this.options));
/*      */     }
/*  853 */     return this.options;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setOptions(int options) {
/*  860 */     if (Trace.isOn) {
/*  861 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiPrivConnStruct", "setOptions(int)", "setter", 
/*  862 */           Integer.valueOf(options));
/*      */     }
/*  864 */     this.options = options;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getConnectionClass() {
/*  871 */     if (Trace.isOn) {
/*  872 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiPrivConnStruct", "getConnectionClass()", "getter", 
/*  873 */           Integer.valueOf(this.connectionClass));
/*      */     }
/*  875 */     return this.connectionClass;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setConnectionClass(int connectionClass) {
/*  882 */     if (Trace.isOn) {
/*  883 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiPrivConnStruct", "setConnectionClass(int)", "setter", 
/*  884 */           Integer.valueOf(connectionClass));
/*      */     }
/*  886 */     this.connectionClass = connectionClass;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getAuthToken() {
/*  893 */     if (Trace.isOn) {
/*  894 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiPrivConnStruct", "getAuthToken()", "getter", 
/*  895 */           Integer.valueOf(this.authToken));
/*      */     }
/*  897 */     return this.authToken;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAuthToken(int authToken) {
/*  904 */     if (Trace.isOn) {
/*  905 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiPrivConnStruct", "setAuthToken(int)", "setter", 
/*  906 */           Integer.valueOf(authToken));
/*      */     }
/*  908 */     this.authToken = authToken;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getUuid() {
/*  915 */     if (Trace.isOn) {
/*  916 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiPrivConnStruct", "getUuid()", "getter", this.uuid);
/*      */     }
/*  918 */     return this.uuid;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUuid(String uuid) {
/*  925 */     if (Trace.isOn) {
/*  926 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiPrivConnStruct", "setUuid(String)", "setter", uuid);
/*      */     }
/*      */     
/*  929 */     this.uuid = uuid;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getCluster() {
/*  936 */     if (Trace.isOn) {
/*  937 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiPrivConnStruct", "getCluster()", "getter", this.cluster);
/*      */     }
/*      */     
/*  940 */     return this.cluster;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCluster(String cluster) {
/*  947 */     if (Trace.isOn) {
/*  948 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiPrivConnStruct", "setCluster(String)", "setter", cluster);
/*      */     }
/*      */     
/*  951 */     this.cluster = cluster;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getQmOffset() {
/*  958 */     if (Trace.isOn) {
/*  959 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiPrivConnStruct", "getQmOffset()", "getter", 
/*  960 */           Integer.valueOf(this.qmOffset));
/*      */     }
/*  962 */     return this.qmOffset;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setQmOffset(int qmOffset) {
/*  969 */     if (Trace.isOn) {
/*  970 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiPrivConnStruct", "setQmOffset(int)", "setter", 
/*  971 */           Integer.valueOf(qmOffset));
/*      */     }
/*  973 */     this.qmOffset = qmOffset;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCmdLevel() {
/*  980 */     if (Trace.isOn) {
/*  981 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiPrivConnStruct", "getCmdLevel()", "getter", 
/*  982 */           Integer.valueOf(this.cmdLevel));
/*      */     }
/*  984 */     return this.cmdLevel;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCmdLevel(int cmdLevel) {
/*  991 */     if (Trace.isOn) {
/*  992 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiPrivConnStruct", "setCmdLevel(int)", "setter", 
/*  993 */           Integer.valueOf(cmdLevel));
/*      */     }
/*  995 */     this.cmdLevel = cmdLevel;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int[] getSubCmdLevel() {
/* 1002 */     if (Trace.isOn) {
/* 1003 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiPrivConnStruct", "getSubCmdLevel()", "getter", this.subCmdLevel);
/*      */     }
/*      */     
/* 1006 */     return this.subCmdLevel;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSubCmdLevel(int[] subCmdLevel) {
/* 1013 */     if (Trace.isOn) {
/* 1014 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiPrivConnStruct", "setSubCmdLevel(int [ ])", "setter", subCmdLevel);
/*      */     }
/*      */     
/* 1017 */     this.subCmdLevel = subCmdLevel;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getApplType() {
/* 1024 */     if (Trace.isOn) {
/* 1025 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiPrivConnStruct", "getApplType()", "getter", 
/* 1026 */           Integer.valueOf(this.applType));
/*      */     }
/* 1028 */     return this.applType;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setApplType(int applType) {
/* 1035 */     if (Trace.isOn) {
/* 1036 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiPrivConnStruct", "setApplType(int)", "setter", 
/* 1037 */           Integer.valueOf(applType));
/*      */     }
/* 1039 */     this.applType = applType;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 1047 */     if (Trace.isOn) {
/* 1048 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiPrivConnStruct", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*      */             
/* 1050 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*      */     }
/*      */ 
/*      */     
/* 1054 */     int pos = offset;
/* 1055 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*      */ 
/*      */     
/* 1058 */     dc.writeMQField("LCNO", buffer, pos, 4, cp, tls);
/* 1059 */     pos += 4;
/*      */ 
/*      */     
/* 1062 */     dc.writeI32(this.version, buffer, pos, swap);
/* 1063 */     pos += 4;
/*      */ 
/*      */     
/* 1066 */     dc.writeI32(this.options, buffer, pos, swap);
/* 1067 */     pos += 4;
/*      */ 
/*      */     
/* 1070 */     dc.writeI32(this.connectionClass, buffer, pos, swap);
/* 1071 */     pos += 4;
/*      */ 
/*      */     
/* 1074 */     dc.writeI32(this.authToken, buffer, pos, swap);
/* 1075 */     pos += 4;
/*      */ 
/*      */     
/* 1078 */     dc.writeMQField(this.uuid, buffer, pos, 48, cp, tls);
/* 1079 */     pos += 48;
/*      */ 
/*      */     
/* 1082 */     dc.writeMQField(this.cluster, buffer, pos, 48, cp, tls);
/* 1083 */     pos += 48;
/*      */ 
/*      */     
/* 1086 */     int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 1087 */     dc.clear(buffer, pos, padding);
/* 1088 */     pos += padding;
/*      */ 
/*      */     
/* 1091 */     dc.clear(buffer, pos, ptrSize);
/* 1092 */     pos += ptrSize;
/*      */ 
/*      */     
/* 1095 */     dc.writeI32(this.qmOffset, buffer, pos, swap);
/* 1096 */     pos += 4;
/*      */ 
/*      */     
/* 1099 */     padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 1100 */     dc.clear(buffer, pos, padding);
/* 1101 */     pos += padding;
/*      */ 
/*      */     
/* 1104 */     dc.clear(buffer, pos, ptrSize);
/* 1105 */     pos += ptrSize;
/*      */     
/* 1107 */     if (this.version >= 2) {
/*      */       
/* 1109 */       dc.clear(buffer, pos, ptrSize);
/* 1110 */       pos += ptrSize;
/*      */ 
/*      */       
/* 1113 */       dc.clear(buffer, pos, ptrSize);
/* 1114 */       pos += ptrSize;
/*      */ 
/*      */       
/* 1117 */       dc.clear(buffer, pos, ptrSize);
/* 1118 */       pos += ptrSize;
/*      */ 
/*      */       
/* 1121 */       dc.writeI32(this.cmdLevel, buffer, pos, swap);
/* 1122 */       pos += 4;
/*      */ 
/*      */       
/* 1125 */       for (int i = 0; i < 4; i++) {
/* 1126 */         dc.writeI32(this.subCmdLevel[i], buffer, pos + 4 * i, swap);
/*      */       }
/* 1128 */       pos += 16;
/*      */ 
/*      */       
/* 1131 */       if (this.version == 2) {
/* 1132 */         padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 1133 */         dc.clear(buffer, pos, padding);
/* 1134 */         pos += padding;
/*      */       } 
/*      */     } 
/*      */     
/* 1138 */     if (this.version >= 3) {
/*      */       
/* 1140 */       dc.writeI32(this.applType, buffer, pos, swap);
/* 1141 */       pos += 4;
/*      */ 
/*      */       
/* 1144 */       padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 1145 */       dc.clear(buffer, pos, padding);
/* 1146 */       pos += padding;
/*      */ 
/*      */       
/* 1149 */       dc.clear(buffer, pos, ptrSize);
/* 1150 */       pos += ptrSize;
/*      */     } 
/*      */     
/* 1153 */     if (this.version >= 4) {
/*      */       
/* 1155 */       dc.clear(buffer, pos, ptrSize);
/* 1156 */       pos += ptrSize;
/*      */     } 
/*      */     
/* 1159 */     if (Trace.isOn) {
/* 1160 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiPrivConnStruct", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 1161 */           Integer.valueOf(pos));
/*      */     }
/* 1163 */     return pos;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 1171 */     if (Trace.isOn) {
/* 1172 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiPrivConnStruct", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*      */             
/* 1174 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*      */     }
/*      */ 
/*      */     
/* 1178 */     int pos = offset;
/* 1179 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*      */ 
/*      */     
/* 1182 */     String strucId = dc.readMQField(buffer, pos, 4, cp, tls);
/* 1183 */     if (!strucId.equals("LCNO")) {
/*      */       
/* 1185 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 6107, null);
/* 1186 */       if (Trace.isOn) {
/* 1187 */         Trace.throwing(this, "com.ibm.mq.jmqi.system.LpiPrivConnStruct", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 1190 */       throw traceRet1;
/*      */     } 
/* 1192 */     pos += 4;
/*      */ 
/*      */     
/* 1195 */     this.version = dc.readI32(buffer, pos, swap);
/* 1196 */     pos += 4;
/*      */ 
/*      */     
/* 1199 */     this.options = dc.readI32(buffer, pos, swap);
/* 1200 */     pos += 4;
/*      */ 
/*      */     
/* 1203 */     this.connectionClass = dc.readI32(buffer, pos, swap);
/* 1204 */     pos += 4;
/*      */ 
/*      */     
/* 1207 */     this.authToken = dc.readI32(buffer, pos, swap);
/* 1208 */     pos += 4;
/*      */ 
/*      */     
/* 1211 */     this.uuid = dc.readMQField(buffer, pos, 48, cp, tls);
/* 1212 */     pos += 48;
/*      */ 
/*      */     
/* 1215 */     this.cluster = dc.readMQField(buffer, pos, 48, cp, tls);
/* 1216 */     pos += 48;
/*      */ 
/*      */     
/* 1219 */     int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 1220 */     pos += padding;
/*      */ 
/*      */     
/* 1223 */     pos += ptrSize;
/*      */ 
/*      */     
/* 1226 */     this.qmOffset = dc.readI32(buffer, pos, swap);
/* 1227 */     pos += 4;
/*      */ 
/*      */     
/* 1230 */     padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 1231 */     pos += padding;
/*      */ 
/*      */     
/* 1234 */     pos += ptrSize;
/*      */     
/* 1236 */     if (this.version >= 2) {
/*      */       
/* 1238 */       pos += ptrSize;
/*      */ 
/*      */       
/* 1241 */       pos += ptrSize;
/*      */ 
/*      */       
/* 1244 */       pos += ptrSize;
/*      */ 
/*      */       
/* 1247 */       this.cmdLevel = dc.readI32(buffer, pos, swap);
/* 1248 */       pos += 4;
/*      */ 
/*      */       
/* 1251 */       for (int i = 0; i < 4; i++) {
/* 1252 */         this.subCmdLevel[i] = dc.readI32(buffer, pos + 4 * i, swap);
/*      */       }
/* 1254 */       pos += 16;
/*      */ 
/*      */       
/* 1257 */       if (this.version == 2) {
/* 1258 */         padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 1259 */         pos += padding;
/*      */       } 
/*      */     } 
/*      */     
/* 1263 */     if (this.version >= 3) {
/*      */       
/* 1265 */       this.applType = dc.readI32(buffer, pos, swap);
/* 1266 */       pos += 4;
/*      */ 
/*      */       
/* 1269 */       padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 1270 */       pos += padding;
/*      */ 
/*      */       
/* 1273 */       pos += ptrSize;
/*      */     } 
/*      */     
/* 1276 */     if (this.version >= 4)
/*      */     {
/* 1278 */       pos += ptrSize;
/*      */     }
/*      */     
/* 1281 */     if (Trace.isOn) {
/* 1282 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiPrivConnStruct", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 1283 */           Integer.valueOf(pos));
/*      */     }
/* 1285 */     return pos;
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
/* 1297 */     fmt.add("version", this.version);
/* 1298 */     fmt.add("options", this.options);
/* 1299 */     String optionDescription = formatOptions(this.options, getFields(), "lpiPrivOpt_");
/* 1300 */     fmt.add("option flags", optionDescription);
/* 1301 */     fmt.add("connectionClass", this.connectionClass);
/* 1302 */     fmt.add("authToken", this.authToken);
/* 1303 */     fmt.add("uuid", this.uuid);
/* 1304 */     fmt.add("cluster", this.cluster);
/* 1305 */     fmt.add("qmOffset", this.qmOffset);
/* 1306 */     fmt.add("cmdLevel", this.cmdLevel);
/* 1307 */     fmt.add("subCmdLevel", this.subCmdLevel);
/* 1308 */     fmt.add("applType", this.applType);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public int getQMOffset() {
/* 1317 */     int traceRet1 = getQmOffset();
/* 1318 */     if (Trace.isOn) {
/* 1319 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiPrivConnStruct", "getQMOffset()", "getter", 
/* 1320 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1322 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void setQMOffset(int offset) {
/* 1331 */     if (Trace.isOn) {
/* 1332 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiPrivConnStruct", "setQMOffset(int)", "setter", 
/* 1333 */           Integer.valueOf(offset));
/*      */     }
/* 1335 */     setQmOffset(offset);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static synchronized Field[] getFields() {
/* 1345 */     Reference<Field[]> ref = fieldsRef;
/*      */     
/*      */     Field[] fields;
/* 1348 */     if (ref == null || (fields = ref.get()) == null) {
/* 1349 */       fieldsRef = (Reference)new SoftReference<>(fields = LpiPrivConnStruct.class.getFields());
/*      */     }
/*      */     
/* 1352 */     if (Trace.isOn) {
/* 1353 */       Trace.data("com.ibm.mq.jmqi.system.LpiPrivConnStruct", "getFields()", "getter", fields);
/*      */     }
/* 1355 */     return fields;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getEnvironment() {
/* 1362 */     return this.environment;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEnvironment(int environment) {
/* 1369 */     this.environment = environment;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\LpiPrivConnStruct.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */