/*      */ package com.ibm.mq.jmqi.remote.impl;
/*      */ 
/*      */ import com.ibm.mq.constants.MQConstants;
/*      */ import com.ibm.mq.exits.MQCD;
/*      */ import com.ibm.mq.exits.MQCSP;
/*      */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*      */ import com.ibm.mq.jmqi.JmqiException;
/*      */ import com.ibm.mq.jmqi.JmqiMQ;
/*      */ import com.ibm.mq.jmqi.JmqiObject;
/*      */ import com.ibm.mq.jmqi.MQSCO;
/*      */ import com.ibm.mq.jmqi.handles.Hconn;
/*      */ import com.ibm.mq.jmqi.internal.Configuration;
/*      */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*      */ import com.ibm.mq.jmqi.internal.JmqiTools;
/*      */ import com.ibm.mq.jmqi.internal.QueueManagerInfo;
/*      */ import com.ibm.mq.jmqi.remote.api.RemoteFAP;
/*      */ import com.ibm.mq.jmqi.remote.exit.RemoteExitChain;
/*      */ import com.ibm.mq.jmqi.remote.rfp.RfpCAUT;
/*      */ import com.ibm.mq.jmqi.remote.rfp.RfpESH;
/*      */ import com.ibm.mq.jmqi.remote.rfp.RfpID;
/*      */ import com.ibm.mq.jmqi.remote.rfp.RfpSOCKACT;
/*      */ import com.ibm.mq.jmqi.remote.rfp.RfpStructure;
/*      */ import com.ibm.mq.jmqi.remote.rfp.RfpTSH;
/*      */ import com.ibm.mq.jmqi.remote.rfp.RfpUID;
/*      */ import com.ibm.mq.jmqi.remote.util.RemoteCommsBuffer;
/*      */ import com.ibm.mq.jmqi.remote.util.RemoteConstantDecoder;
/*      */ import com.ibm.mq.jmqi.remote.util.RemotePPA;
/*      */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*      */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*      */ import com.ibm.mq.jmqi.system.JmqiTls;
/*      */ import com.ibm.msg.client.commonservices.Log.Log;
/*      */ import com.ibm.msg.client.commonservices.Utils;
/*      */ import com.ibm.msg.client.commonservices.cssystem.CSSystem;
/*      */ import com.ibm.msg.client.commonservices.locking.TraceableReentrantLock;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.commonservices.util.ConcurrentWeakHashSet;
/*      */ import com.ibm.msg.client.commonservices.util.Cruise;
/*      */ import java.io.PrintWriter;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.ByteOrder;
/*      */ import java.security.AccessController;
/*      */ import java.security.PrivilegedAction;
/*      */ import java.security.SecureRandom;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.HashMap;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Random;
/*      */ import java.util.TreeMap;
/*      */ import java.util.concurrent.ExecutorService;
/*      */ import java.util.concurrent.Executors;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class RemoteConnection
/*      */   extends JmqiObject
/*      */ {
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/impl/RemoteConnection.java";
/*      */   
/*      */   static {
/*   95 */     if (Trace.isOn) {
/*   96 */       Trace.data("com.ibm.mq.jmqi.remote.impl.RemoteConnection", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/impl/RemoteConnection.java");
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
/*  109 */   private static final byte[] PADDING_BUFFER = new byte[3];
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int TSHM_CONVID_PRIMARY = 1;
/*      */ 
/*      */ 
/*      */   
/*  118 */   private final int MAX_BIND_ATTEMPTS = 10;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static final int CONVERSATION_ALLOCATION_SUCCESS = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static final int CONVERSATION_ALLOCATION_FAILURE = 1;
/*      */ 
/*      */ 
/*      */   
/*      */   static final int CONVERSATION_ALLOCATION_RETRY = 2;
/*      */ 
/*      */ 
/*      */   
/*      */   static final int CONVERSATION_ALLOCATION_WAIT = 3;
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int HEARTBEAT_REQUEST = 1;
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int HEARTBEAT_RESPONSE = 2;
/*      */ 
/*      */ 
/*      */   
/*  148 */   private final Object sessionsMutex = new SessionsMutex();
/*      */ 
/*      */   
/*  151 */   protected static final ConcurrentWeakHashSet<RemoteConnection> allConnections = new ConcurrentWeakHashSet();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  156 */   protected int fapLevel = 16;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  170 */   int maxTransmissionSize = 32748;
/*      */   
/*  172 */   private int roundedTransmissionSize = this.maxTransmissionSize;
/*      */ 
/*      */ 
/*      */   
/*      */   protected volatile int reconnectCycle;
/*      */ 
/*      */   
/*  179 */   private int flags = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  184 */   private int flags2 = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  189 */   private int flags3 = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean serverSecurityExit = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  205 */   private int localCcsid = 819;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private JmqiCodepage codepage;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean swap = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int remoteEncoding;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int remoteMQEncoding;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  230 */   private int curHdrCompression = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  235 */   private int curMsgCompression = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean compressionRequired = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean ppaRequired = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  250 */   private int secureKeyResetCount = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  256 */   private int bytesSinceKeyReset = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean heartbeatKeyResetRequired = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  267 */   private Object sslResetCountSync = new SslResetCountSync();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  273 */   private SendMutex sendMutex = new SendMutex();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  278 */   private RcvThreadSendMutex rcvThreadSendMutex = new RcvThreadSendMutex();
/*      */ 
/*      */   
/*      */   public static final int NO_KEY_RESET = 0;
/*      */ 
/*      */   
/*      */   public static final int CLIENT_KEY_RESET = 1;
/*      */ 
/*      */   
/*      */   public static final int SERVER_KEY_RESET = 2;
/*      */ 
/*      */   
/*  290 */   private volatile int keyReset = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean keyResetComplete = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  300 */   public Object keyResetSemaphore = new KeyResetSemaphore();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  305 */   private int compressionLevel = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected MQCD negotiatedChannel;
/*      */ 
/*      */ 
/*      */   
/*  314 */   private RemoteExitChain channelSecurityExit = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  321 */   private RemoteTagPool idTagPool = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  327 */   private String remoteQMID = "CANNED_DATA";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int connectionOptions;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected MQCD clientConn;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected MQSCO sslConfig;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int jmqiFlags;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean connected = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean connectionBroken = false;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean disconnected = false;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean multiplexingEnabled = false;
/*      */ 
/*      */ 
/*      */   
/*  369 */   private RemoteRcvThread rcvThread = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Map<Integer, RemoteSession> sessions;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  381 */   private int lastAllocatedConvId = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean convIdsWrapped = false;
/*      */ 
/*      */ 
/*      */   
/*  390 */   private Object asyncTshLock = new AsyncTshLock();
/*      */ 
/*      */   
/*  393 */   private LinkedList<RfpTSH> asyncTshQueue = new LinkedList<>();
/*      */ 
/*      */   
/*  396 */   protected volatile Throwable asyncFailure = null;
/*      */ 
/*      */   
/*  399 */   String channelName = null;
/*      */ 
/*      */   
/*  402 */   private String remoteProductId = null;
/*      */ 
/*      */   
/*      */   protected RemoteFAP fap;
/*      */ 
/*      */   
/*      */   protected String qMgrName;
/*      */ 
/*      */   
/*      */   private boolean reconnectable = false;
/*      */ 
/*      */   
/*      */   private boolean globallyShareable = true;
/*      */   
/*      */   private boolean reconnectRequested = false;
/*      */   
/*      */   protected RemoteConnectionSpecification remoteConnectionSpec;
/*      */   
/*      */   private static final long PAUSE_DATA_SENDS_TIME_OUT = 10000L;
/*      */   
/*  422 */   protected long lastDataSend = 0L;
/*  423 */   protected long lastDataRecv = 0L;
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean flowMQCSPData;
/*      */ 
/*      */   
/*  430 */   private byte[] r = new byte[24];
/*      */ 
/*      */   
/*      */   private int[] pal;
/*      */ 
/*      */   
/*  436 */   private static final int[] PAL_NUL_AND_DES = new int[] { 1, 0 };
/*  437 */   private static final int[] PAL_DES_ONLY = new int[] { 1 };
/*  438 */   private static final int[] PAL_NULL_ONLY = new int[] { 0 };
/*  439 */   private static final int[] PAL_ALL_NON_NULL = new int[] { 1 };
/*      */ 
/*      */   
/*      */   private Random rg;
/*      */ 
/*      */   
/*  445 */   private int ppa = 65535;
/*      */ 
/*      */ 
/*      */   
/*      */   private JmqiDC dc;
/*      */ 
/*      */   
/*      */   private boolean ignoreCertLabel;
/*      */ 
/*      */   
/*      */   private Configuration clientCfg;
/*      */ 
/*      */   
/*      */   private QueueManagerInfo info;
/*      */ 
/*      */   
/*  461 */   private final Object QueueManagerInfoLock = new QueueManagerInfoLock();
/*      */   
/*      */   private class QueueManagerInfoLock {
/*      */     private QueueManagerInfoLock() {}
/*      */   }
/*      */   
/*      */   private static class SessionLock
/*      */     extends TraceableReentrantLock {
/*      */     private static final long serialVersionUID = 667553517661594502L;
/*      */     
/*      */     private SessionLock() {}
/*      */   }
/*  473 */   private SessionLock sessionLock = new SessionLock();
/*      */   
/*  475 */   private ExecutorService exeService = null;
/*      */ 
/*      */   
/*      */   private boolean sessionBeingCreated;
/*      */   
/*      */   protected final int traceIdentifier;
/*      */   
/*  482 */   protected int remoteProcessId = -1;
/*  483 */   protected int remoteThreadId = -1;
/*  484 */   protected int remoteTraceIdentifier = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean rebalancedByResourceAdapter = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean shouldDisconnect;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected RemoteConnection(JmqiEnvironment env, int jmqiFlags) throws JmqiException {
/*  630 */     super(env);
/*  631 */     if (Trace.isOn) {
/*  632 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "<init>(JmqiEnvironment,int)", new Object[] { env, 
/*  633 */             Integer.valueOf(jmqiFlags) });
/*      */     }
/*      */     
/*  636 */     this.traceIdentifier = RemoteFAP.getUniqueTraceIdentifier();
/*      */     
/*  638 */     if (env instanceof JmqiSystemEnvironment) {
/*  639 */       this.dc = ((JmqiSystemEnvironment)env).getDC();
/*      */     }
/*      */     
/*  642 */     this.clientCfg = env.getConfiguration();
/*      */     
/*  644 */     this.ignoreCertLabel = this.clientCfg.getBoolValue(Configuration.IGNORE_CERT_LABEL);
/*      */     
/*  646 */     String rndOption = this.clientCfg.getChoiceValue(Configuration.AMQ_RANDOM_NUMBER_TYPE);
/*  647 */     if (rndOption.equals("FAST")) {
/*  648 */       this.rg = new Random();
/*      */     } else {
/*      */       
/*  651 */       this.rg = new SecureRandom();
/*      */     } 
/*      */ 
/*      */     
/*  655 */     byte[] cr = new byte[12];
/*  656 */     this.rg.nextBytes(cr);
/*  657 */     System.arraycopy(cr, 0, this.r, 0, 12);
/*      */ 
/*      */ 
/*      */     
/*  661 */     if (CSSystem.currentPlatform().equals(CSSystem.Platform.OS_NSS)) {
/*  662 */       this.fapLevel = 10;
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  667 */       this.codepage = JmqiCodepage.getJmqiCodepage(env, this.localCcsid);
/*      */       
/*  669 */       if (this.codepage == null) {
/*  670 */         UnsupportedEncodingException traceRet2 = new UnsupportedEncodingException(Integer.toString(this.localCcsid));
/*  671 */         if (Trace.isOn) {
/*  672 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "<init>(JmqiEnvironment,int)", traceRet2, 1);
/*      */         }
/*      */         
/*  675 */         throw traceRet2;
/*      */       }
/*      */     
/*      */     }
/*  679 */     catch (UnsupportedEncodingException e) {
/*  680 */       if (Trace.isOn) {
/*  681 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "<init>(JmqiEnvironment,int)", e);
/*      */       }
/*      */ 
/*      */       
/*  685 */       HashMap<String, Object> info1 = new HashMap<>();
/*  686 */       info1.put("localCcsid", Integer.valueOf(this.localCcsid));
/*  687 */       info1.put("Description", "Unable to find character set supported by JRE for CCSID");
/*      */       
/*  689 */       Trace.ffst(this, "<init>(JmqiEnvironment)", "01", info1, null);
/*  690 */       JmqiException traceRet1 = new JmqiException(env, -1, null, 2, 2195, null);
/*      */ 
/*      */ 
/*      */       
/*  694 */       if (Trace.isOn) {
/*  695 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "<init>(JmqiEnvironment,int)", (Throwable)traceRet1, 2);
/*      */       }
/*      */       
/*  698 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  705 */     this.sessions = new TreeMap<>();
/*      */     
/*  707 */     if ((jmqiFlags & 0x100) != 0) {
/*  708 */       if ((jmqiFlags & 0x80) != 0) {
/*  709 */         this.flowMQCSPData = true;
/*      */       } else {
/*      */         
/*  712 */         Configuration config = env.getConfiguration();
/*  713 */         this.flowMQCSPData = config.getBoolValue(Configuration.USE_MQCSP_AUTHENTICATION);
/*  714 */         if (config.wasDefaulted((Configuration.CfgProperty)Configuration.USE_MQCSP_AUTHENTICATION)) {
/*  715 */           this.flowMQCSPData = false;
/*      */         }
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  722 */       this.flowMQCSPData = ((jmqiFlags & 0x80) != 0);
/*      */     } 
/*      */     
/*  725 */     if (Trace.isOn) {
/*  726 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "<init>", "jmqiFlags: " + jmqiFlags + ", flowMQCSPData: " + this.flowMQCSPData);
/*      */     }
/*      */ 
/*      */     
/*  730 */     initialiseExecutorService();
/*      */     
/*  732 */     allConnections.add(this);
/*      */     
/*  734 */     if (Trace.isOn) {
/*  735 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "<init>(JmqiEnvironment,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void initialiseExecutorService() {
/*  742 */     if (this.exeService == null) {
/*  743 */       this.exeService = Executors.newSingleThreadExecutor();
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
/*      */   RemoteSession assignSession() throws JmqiException {
/*  763 */     if (Trace.isOn) {
/*  764 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "assignSession()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  773 */     RemoteSession session = null;
/*      */ 
/*      */     
/*  776 */     synchronized (this.sessionsMutex) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  781 */       if (this.asyncFailure != null) {
/*  782 */         throwAsyncFailureException();
/*      */       }
/*      */       
/*  785 */       if (this.disconnected) {
/*  786 */         JmqiException je = new JmqiException(this.env, -1, null, 2, 2009, null);
/*      */         
/*  788 */         if (Trace.isOn) {
/*  789 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "assignSession()", (Throwable)je, 1);
/*      */         }
/*      */         
/*  792 */         throw je;
/*      */       } 
/*      */       
/*  795 */       if (!this.shouldDisconnect && (
/*  796 */         !isMultiplexed() || this.sessions
/*  797 */         .size() < this.negotiatedChannel.getSharingConversations())) {
/*      */ 
/*      */         
/*  800 */         session = new RemoteSession(this.env, this);
/*      */         
/*  802 */         session.configureCompression(this.compressionLevel);
/*      */ 
/*      */ 
/*      */         
/*  806 */         if (isMultiplexed()) {
/*  807 */           int convId = this.lastAllocatedConvId + 2;
/*      */           
/*  809 */           if (convId < this.lastAllocatedConvId) {
/*  810 */             this.convIdsWrapped = true;
/*      */           }
/*      */           
/*  813 */           if (this.convIdsWrapped) {
/*  814 */             while (this.sessions.get(Integer.valueOf(convId)) != null) {
/*  815 */               convId += 2;
/*      */             }
/*      */           }
/*      */           
/*  819 */           session.setConversationId(convId);
/*  820 */           this.lastAllocatedConvId = convId;
/*      */ 
/*      */           
/*  823 */           this.sessions.put(Integer.valueOf(session.getConversationId()), session);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  829 */     if (Trace.isOn) {
/*  830 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "assignSession()", session);
/*      */     }
/*      */     
/*  833 */     return session;
/*      */   }
/*      */   
/*      */   boolean isMultiplexed() {
/*  837 */     boolean result = (this.negotiatedChannel.getSharingConversations() > 0);
/*  838 */     if (Trace.isOn) {
/*  839 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "isMultiplexed()", "getter", 
/*  840 */           Boolean.valueOf(result));
/*      */     }
/*  842 */     return result;
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
/*      */   void removeSession(RemoteTls tls, RemoteSession sessionP, boolean informQmgr) throws JmqiException {
/*  859 */     if (Trace.isOn) {
/*  860 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "removeSession(RemoteTls,RemoteSession,boolean)", new Object[] { tls, sessionP, 
/*      */             
/*  862 */             Boolean.valueOf(informQmgr) });
/*      */     }
/*      */     
/*  865 */     getSessionLock().lock();
/*  866 */     this.shouldDisconnect = false;
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  871 */       RemoteSession session = sessionP;
/*  872 */       int conversationId = session.getConversationId();
/*      */       
/*  874 */       synchronized (this.sessionsMutex) {
/*  875 */         if (isMultiplexed()) {
/*  876 */           if (!this.sessions.containsKey(Integer.valueOf(conversationId))) {
/*      */ 
/*      */             
/*  879 */             if (Trace.isOn) {
/*  880 */               Trace.data(this, "removeSession(RemoteTls,RemoteSession,boolean)", "Session has already been removed.", session);
/*      */               
/*  882 */               Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "removeSession(RemoteTls,RemoteSession,boolean)", 1);
/*      */             } 
/*      */             
/*      */             return;
/*      */           } 
/*      */           
/*  888 */           if (Trace.isOn) {
/*  889 */             Trace.data(this, "removeSession(RemoteTls,RemoteSession,boolean)", "sessions ", 
/*  890 */                 Integer.valueOf(this.sessions.size()));
/*      */           }
/*      */           
/*  893 */           if (this.sessions.size() == 1) {
/*      */ 
/*      */ 
/*      */             
/*  897 */             this.shouldDisconnect = true;
/*  898 */             if (this.multiplexingEnabled) {
/*  899 */               this.rcvThread.setDisconnecting();
/*      */             }
/*      */           } 
/*  902 */           this.sessions.remove(Integer.valueOf(conversationId));
/*      */         
/*      */         }
/*  905 */         else if (!session.isEndRequested() && !getSessionBeingCreated()) {
/*  906 */           this.shouldDisconnect = true;
/*      */         } 
/*      */ 
/*      */         
/*  910 */         session.setEndRequested(true);
/*      */         
/*  912 */         if (informQmgr) {
/*  913 */           confirmSessionEndToQMgr(session, tls, this.shouldDisconnect);
/*      */         }
/*      */       } 
/*      */     } finally {
/*      */       
/*  918 */       if (Trace.isOn) {
/*  919 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "removeSession(RemoteTls,RemoteSession,boolean)", 1);
/*      */       }
/*      */       
/*  922 */       getSessionLock().unlock();
/*      */     } 
/*      */     
/*  925 */     if (this.shouldDisconnect) {
/*  926 */       disconnect(tls);
/*      */     }
/*  928 */     else if (!this.reconnectable && this.globallyShareable) {
/*  929 */       this.remoteConnectionSpec.storeConnection(this);
/*      */     } 
/*      */     
/*  932 */     if (Trace.isOn) {
/*  933 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "removeSession(RemoteTls,RemoteSession,boolean)", 2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void confirmSessionEndToQMgr(RemoteSession session, RemoteTls tls, boolean noMoreSessionsOnThisConnection) throws JmqiException {
/*  941 */     if (Trace.isOn) {
/*  942 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "confirmSessionEndToQMgr(RemoteSession,RemoteTls,boolean)", new Object[] { session, tls, 
/*      */             
/*  944 */             Boolean.valueOf(noMoreSessionsOnThisConnection) });
/*      */     }
/*  946 */     if (this.multiplexingEnabled) {
/*  947 */       int controlFlags = 0;
/*  948 */       RfpTSH sTSH = allocateTSH(1, 12, null);
/*      */       
/*  950 */       sTSH.setTransLength(sTSH.hdrSize() + 20);
/*      */ 
/*      */ 
/*      */       
/*  954 */       RfpSOCKACT sockAct = new RfpSOCKACT(this.env, sTSH.getRfpBuffer(), sTSH.getRfpOffset() + sTSH.hdrSize());
/*      */       
/*  956 */       sockAct.setConversationId(session.getConversationId(), this.swap);
/*  957 */       sockAct.setRequestId(0, this.swap);
/*  958 */       sockAct.setType(2, this.swap);
/*      */ 
/*      */       
/*  961 */       if (session.isTerminatedByExit() && getFapLevel() >= 10) {
/*  962 */         controlFlags |= 0x1;
/*      */       }
/*      */       
/*  965 */       if (noMoreSessionsOnThisConnection == true) {
/*  966 */         controlFlags |= 0x8;
/*      */       }
/*  968 */       sTSH.setControlFlags1(controlFlags);
/*      */       try {
/*  970 */         sendTSH(tls, sTSH, null);
/*      */       }
/*  972 */       catch (JmqiException je) {
/*  973 */         if (Trace.isOn) {
/*  974 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "confirmSessionEndToQMgr(RemoteSession,RemoteTls,boolean)", (Throwable)je);
/*      */         }
/*      */         
/*  977 */         if (je.getReason() != 2009) {
/*  978 */           if (Trace.isOn) {
/*  979 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "confirmSessionEndToQMgr(RemoteSession,RemoteTls,boolean)", (Throwable)je);
/*      */           }
/*      */           
/*  982 */           throw je;
/*      */         } 
/*      */       } 
/*      */     } 
/*  986 */     if (Trace.isOn) {
/*  987 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "confirmSessionEndToQMgr(RemoteSession,RemoteTls,boolean)", 1);
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
/*      */   void connect(RemoteTls tls, MQCSP securityParms) throws JmqiException {
/* 1004 */     if (Trace.isOn) {
/* 1005 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "connect(RemoteTls,MQCSP)", new Object[] { tls, securityParms });
/*      */     }
/*      */     
/* 1008 */     boolean protocolConnected = false;
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1013 */       protocolConnect();
/* 1014 */       protocolConnected = true;
/*      */       
/* 1016 */       initSess();
/*      */       
/* 1018 */       this.connected = true;
/*      */     }
/* 1020 */     catch (JmqiException e) {
/* 1021 */       if (Trace.isOn) {
/* 1022 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "connect(RemoteTls,MQCSP)", (Throwable)e, 1);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1028 */       if (this.asyncFailure == null) {
/* 1029 */         this.asyncFailure = (Throwable)e;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1034 */       if (protocolConnected) {
/*      */         try {
/* 1036 */           protocolDisconnect();
/*      */         }
/* 1038 */         catch (Exception exception) {
/* 1039 */           if (Trace.isOn) {
/* 1040 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "connect(RemoteTls,MQCSP)", exception, 2);
/*      */           }
/*      */         } 
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1048 */       if (Trace.isOn) {
/* 1049 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "connect(RemoteTls,MQCSP)", (Throwable)e);
/*      */       }
/*      */       
/* 1052 */       throw e;
/*      */     } finally {
/*      */       
/* 1055 */       if (Trace.isOn) {
/* 1056 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "connect(RemoteTls,MQCSP)");
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1061 */     if (Trace.isOn) {
/* 1062 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "connect(RemoteTls,MQCSP)");
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
/*      */   void disconnect(RemoteTls tls) throws JmqiException {
/* 1077 */     if (Trace.isOn) {
/* 1078 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "disconnect(RemoteTls)", new Object[] { tls });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1085 */     removeSelf();
/*      */ 
/*      */     
/* 1088 */     synchronized (this.sessionsMutex) {
/* 1089 */       for (RemoteSession session : this.sessions.values()) {
/* 1090 */         session.setDisconnected();
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1096 */     JmqiException exceptionToReThrow = null;
/*      */     try {
/* 1098 */       if (this.channelSecurityExit != null) {
/* 1099 */         this.channelSecurityExit.termExits(this.negotiatedChannel, this.fapLevel, this.remoteProductId);
/*      */       }
/*      */     }
/* 1102 */     catch (JmqiException jmqiE) {
/* 1103 */       if (Trace.isOn) {
/* 1104 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "disconnect(RemoteTls)", (Throwable)jmqiE, 1);
/*      */       }
/*      */       
/* 1107 */       exceptionToReThrow = jmqiE;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1113 */     if (!this.multiplexingEnabled) {
/*      */       try {
/* 1115 */         RfpTSH tsh = allocateTSH(0, 5, null);
/*      */         
/* 1117 */         tsh.setControlFlags1(8);
/* 1118 */         tsh.setTransLength(tsh.hdrSize());
/* 1119 */         sendTSH(tls, tsh, null);
/*      */       }
/* 1121 */       catch (JmqiException e) {
/* 1122 */         if (Trace.isOn) {
/* 1123 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "disconnect(RemoteTls)", (Throwable)e, 2);
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1131 */     if (this.multiplexingEnabled) {
/* 1132 */       this.rcvThread.setDisconnecting();
/*      */     }
/*      */ 
/*      */     
/* 1136 */     protocolDisconnect();
/*      */     
/* 1138 */     if (this.exeService != null) {
/* 1139 */       AccessController.doPrivileged(new PrivilegedAction()
/*      */           {
/*      */             public Object run()
/*      */             {
/* 1143 */               RemoteConnection.this.exeService.shutdownNow();
/* 1144 */               return null;
/*      */             }
/*      */           });
/*      */     }
/*      */     
/* 1149 */     this.connected = false;
/* 1150 */     this.disconnected = true;
/* 1151 */     allConnections.remove(this);
/*      */     
/* 1153 */     if (exceptionToReThrow != null) {
/* 1154 */       if (Trace.isOn) {
/* 1155 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "disconnect(RemoteTls)", (Throwable)exceptionToReThrow, 1);
/*      */       }
/*      */       
/* 1158 */       throw exceptionToReThrow;
/*      */     } 
/*      */     
/* 1161 */     if (Trace.isOn) {
/* 1162 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "disconnect(RemoteTls)");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   void terminate() {
/* 1168 */     protocolTerminate();
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
/*      */   public RfpTSH allocInitialDataTsh(int segmentType) throws JmqiException {
/*      */     RfpTSH sTSH;
/* 1187 */     if (Trace.isOn) {
/* 1188 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "allocInitialDataTsh(int)", new Object[] { RemoteConstantDecoder.decodeSingleOption(segmentType, "rfpTST_") });
/*      */     }
/*      */     
/* 1191 */     if (isMultiplexingEnabled()) {
/* 1192 */       sTSH = allocateTSH(2, segmentType, null);
/* 1193 */       sTSH.setConvId(1);
/* 1194 */       sTSH.setRequestId(0);
/*      */     } else {
/*      */       
/* 1197 */       sTSH = allocateTSH(0, segmentType, null);
/*      */     } 
/*      */     
/* 1200 */     if (Trace.isOn) {
/* 1201 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "allocInitialDataTsh(int)", sTSH);
/*      */     }
/*      */     
/* 1204 */     return sTSH;
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
/*      */   private void initSess() throws JmqiException {
/* 1217 */     if (Trace.isOn) {
/* 1218 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "initSess()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1223 */     if (!this.ignoreCertLabel && this.clientConn.getCertificateLabel() != null) {
/* 1224 */       JmqiException traceRet = new JmqiException(this.env, -1, null, 2, 2596, null);
/*      */ 
/*      */       
/* 1227 */       if (Trace.isOn) {
/* 1228 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "initSess()", (Throwable)traceRet, 1);
/*      */       }
/*      */       
/* 1231 */       throw traceRet;
/*      */     } 
/*      */     
/* 1234 */     int maximumMessagesPerBatch = 0;
/*      */     
/* 1236 */     int remoteCCSID = -1;
/* 1237 */     byte[] luwid = new byte[8];
/* 1238 */     Arrays.fill(luwid, 0, 8, (byte)0);
/*      */     
/* 1240 */     JmqiSystemEnvironment sysenv = (JmqiSystemEnvironment)this.env;
/* 1241 */     RemoteTls tls = (RemoteTls)sysenv.getComponentTls(this.fap.getJmqiCompId());
/* 1242 */     JmqiTls jTls = sysenv.getJmqiTls(tls);
/*      */     
/* 1244 */     if (this.sslConfig != null) {
/* 1245 */       this.secureKeyResetCount = this.sslConfig.getKeyResetCount();
/*      */     }
/*      */ 
/*      */     
/* 1249 */     this.flags |= 0x2;
/*      */     
/* 1251 */     this.flags |= 0x20;
/*      */     
/* 1253 */     this.flags2 |= 0x50;
/*      */     
/* 1255 */     this.flags2 |= 0x1;
/*      */     
/* 1257 */     this.flags2 &= 0xFFFFFF7F;
/*      */     
/* 1259 */     if (this.fapLevel >= 13) {
/* 1260 */       this.flags3 |= 0x8;
/*      */     }
/*      */     
/* 1263 */     if (this.fapLevel >= 14) {
/* 1264 */       this.flags3 |= 0x20;
/*      */     }
/*      */     
/* 1267 */     if (protocolSupportsAsyncMode()) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1272 */       if ((this.connectionOptions & 0x10000) != 0) {
/* 1273 */         this.negotiatedChannel.setSharingConversations(1);
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 1278 */       this.negotiatedChannel.setSharingConversations(0);
/*      */     } 
/*      */     
/* 1281 */     this.channelName = this.negotiatedChannel.getChannelName();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1287 */     boolean binding = true;
/* 1288 */     boolean rejected = true;
/*      */     int attempt;
/* 1290 */     for (attempt = 10; attempt > 0 && binding; attempt--) {
/*      */       RfpTSH rTSH;
/* 1292 */       if (rejected) {
/* 1293 */         int tshFlowSize; RfpTSH sTSH = allocInitialDataTsh(1);
/*      */         
/* 1295 */         switch (this.fapLevel) {
/*      */           case 1:
/*      */           case 2:
/* 1298 */             tshFlowSize = sTSH.hdrSize() + 44;
/*      */             break;
/*      */           case 3:
/* 1301 */             tshFlowSize = sTSH.hdrSize() + 48;
/*      */             break;
/*      */           case 4:
/*      */           case 5:
/*      */           case 6:
/*      */           case 7:
/*      */           case 8:
/* 1308 */             tshFlowSize = sTSH.hdrSize() + 128;
/*      */             break;
/*      */           case 9:
/* 1311 */             tshFlowSize = sTSH.hdrSize() + 160;
/*      */             break;
/*      */           case 10:
/*      */           case 11:
/*      */           case 12:
/* 1316 */             tshFlowSize = sTSH.hdrSize() + 208;
/*      */             break;
/*      */           case 13:
/* 1319 */             tshFlowSize = sTSH.hdrSize() + 240;
/*      */             break;
/*      */           default:
/* 1322 */             tshFlowSize = sTSH.hdrSize() + 240;
/*      */             break;
/*      */         } 
/*      */         
/* 1326 */         sTSH.setTransLength(tshFlowSize);
/* 1327 */         sTSH.setControlFlags1(1);
/*      */ 
/*      */         
/* 1330 */         RfpID sInitData = new RfpID(this.env, sTSH.getRfpBuffer(), sTSH.getRfpOffset() + sTSH.hdrSize());
/* 1331 */         sInitData.initEyecatcher();
/* 1332 */         sInitData.setFapLevel(this.fapLevel);
/* 1333 */         sInitData.setIDFlags(this.flags);
/* 1334 */         sInitData.setIDEFlags(0);
/* 1335 */         sInitData.setErrFlags(0);
/* 1336 */         sInitData.setMaxMessageSize(this.negotiatedChannel
/* 1337 */             .getMaxMsgLength(), this.swap);
/* 1338 */         sInitData.setMaxMessagesPerBatch(0, this.swap);
/* 1339 */         sInitData.setMaxTransmissionSize(this.maxTransmissionSize, this.swap);
/* 1340 */         sInitData.setMessageSequenceWrapValue(0, this.swap);
/*      */         
/* 1342 */         sInitData.setChannelName(this.channelName, this.codepage, jTls);
/*      */         
/* 1344 */         if (this.fapLevel >= 3) {
/* 1345 */           sInitData.setQueueManagerName(this.qMgrName, this.codepage, jTls);
/* 1346 */           sInitData.setCcsid(this.localCcsid, this.swap);
/* 1347 */           sInitData.setIDFlags2(this.flags2);
/* 1348 */           sInitData.setIDEFlags2(0);
/*      */         } 
/*      */         
/* 1351 */         if (this.fapLevel >= 4) {
/* 1352 */           sInitData.setHeartbeatInterval(this.negotiatedChannel
/* 1353 */               .getHeartbeatInterval(), this.swap);
/* 1354 */           sInitData.setEFLLength(0, this.swap);
/*      */         } 
/*      */         
/* 1357 */         if (this.fapLevel >= 8) {
/*      */           
/* 1359 */           int[] hdrCompList = this.negotiatedChannel.getHdrCompList();
/* 1360 */           sInitData.setHdrCompList(hdrCompList);
/* 1361 */           this.curHdrCompression = hdrCompList[0];
/*      */           
/* 1363 */           int[] msgCompList = this.negotiatedChannel.getMsgCompList();
/* 1364 */           sInitData.setMsgCompList(msgCompList);
/* 1365 */           this.curMsgCompression = msgCompList[0];
/*      */           
/* 1367 */           if ((this.curHdrCompression != 0 && this.curHdrCompression != -1) || (this.curMsgCompression != 0 && this.curMsgCompression != -1)) {
/*      */             
/* 1369 */             this.compressionRequired = true;
/*      */           } else {
/*      */             
/* 1372 */             this.compressionRequired = false;
/*      */           } 
/*      */           
/* 1375 */           sInitData.setSSLKeyReset(this.secureKeyResetCount, this.swap);
/*      */           
/* 1377 */           sInitData.setErrFlags2(0);
/* 1378 */           sInitData.setEFLLength(this.swap);
/*      */         } 
/*      */         
/* 1381 */         if (this.fapLevel >= 9) {
/*      */           
/* 1383 */           sInitData.setConvPerSocket(this.negotiatedChannel
/* 1384 */               .getSharingConversations(), this.swap);
/* 1385 */           sInitData.setIDFlags3(this.flags3);
/* 1386 */           sInitData.setIDEFlags3(0);
/*      */           
/* 1388 */           sInitData.setProcessIdentifier(Utils.getJVMProcessId(), this.swap);
/*      */           
/* 1390 */           sInitData.setThreadIdentifier(Utils.getThreadId(), this.swap);
/* 1391 */           sInitData.setTraceIdentifier(this.traceIdentifier, this.swap);
/* 1392 */           String prodId = this.env.getProductIdentifier();
/* 1393 */           sInitData.setProductIdentifier(prodId, this.codepage, jTls);
/*      */         } 
/*      */         
/* 1396 */         if (this.fapLevel >= 10) {
/* 1397 */           sInitData.setQueueManagerId(this.remoteQMID, this.codepage, jTls);
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1979 */     if (attempt == 0) {
/*      */ 
/*      */       
/* 1982 */       JmqiException traceRet8 = new JmqiException(this.env, 9503, new String[] { null, null, getChannelName() }, 2, 2539, null);
/*      */ 
/*      */       
/* 1985 */       if (Trace.isOn) {
/* 1986 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "initSess()", (Throwable)traceRet8, 14);
/*      */       }
/*      */       
/* 1989 */       throw traceRet8;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1995 */     protocolSetHeartbeatInterval(this.negotiatedChannel.getHeartbeatInterval());
/*      */ 
/*      */ 
/*      */     
/* 1999 */     if (this.channelSecurityExit == null) {
/* 2000 */       sendUidFlow(tls);
/*      */     } else {
/*      */       
/* 2003 */       this.channelSecurityExit.sendSecurityFlows(tls, this.negotiatedChannel, this);
/*      */     } 
/*      */     
/* 2006 */     if (Trace.isOn) {
/* 2007 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "initSess()");
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
/*      */   private void configurePasswordProtectionAlgorithms(RfpID rInitData) throws JmqiException {
/* 2022 */     readPasswordProtectionAlgorithmsFromInitDataFlow(rInitData);
/* 2023 */     validatePasswordProtectionAlgorithm(rInitData.getFapLevel());
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
/*      */   private void readPasswordProtectionAlgorithmsFromInitDataFlow(RfpID rInitData) throws JmqiException {
/* 2035 */     if (Trace.isOn) {
/* 2036 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "readPasswordProtectionAlgorithmsFromInitDataFlow(RfpID)", new Object[] { rInitData });
/*      */     }
/*      */ 
/*      */     
/* 2040 */     if (rInitData.getFapLevel() < 13) {
/* 2041 */       this.ppa = 0;
/*      */     }
/*      */     else {
/*      */       
/* 2045 */       if ((rInitData.getIDEFlags3() & 0x10) != 0) {
/*      */ 
/*      */         
/* 2048 */         JmqiException traceRet = new JmqiException(this.env, 9503, new String[] { null, null, getChannelName() }, 2, 2594, null);
/*      */ 
/*      */ 
/*      */         
/* 2052 */         if (Trace.isOn) {
/* 2053 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "readPasswordProtectionAlgorithmsFromInitDataFlow(RfpID)", (Throwable)traceRet, 1);
/*      */         }
/*      */         
/* 2056 */         throw traceRet;
/*      */       } 
/*      */       
/* 2059 */       this.ppa = rInitData.getPal(this.swap)[0];
/* 2060 */       System.arraycopy(rInitData.getR(), 0, this.r, 12, 12);
/*      */     } 
/* 2062 */     if (Trace.isOn) {
/* 2063 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "readPasswordProtectionAlgorithmsFromInitDataFlow(RfpID)");
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
/*      */   private void validatePasswordProtectionAlgorithm(int qmgrFapLevel) throws JmqiException {
/* 2078 */     if (Trace.isOn)
/* 2079 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "validatePasswordProtectionAlgorithm(int)", new Object[] {
/* 2080 */             Integer.valueOf(qmgrFapLevel)
/*      */           }); 
/* 2082 */     if (this.ppa == 65535) {
/*      */ 
/*      */       
/* 2085 */       JmqiException traceRet = new JmqiException(this.env, 9503, new String[] { null, null, getChannelName() }, 2, 2594, null);
/*      */ 
/*      */ 
/*      */       
/* 2089 */       if (Trace.isOn) {
/* 2090 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "validatePasswordProtectionAlgorithm(int)", (Throwable)traceRet, 1);
/*      */       }
/*      */       
/* 2093 */       throw traceRet;
/*      */     } 
/*      */ 
/*      */     
/* 2097 */     if (this.ppa == 0) {
/* 2098 */       switch (this.clientCfg.getChoiceValue(Configuration.PASSWORD_PROTECTION)) {
/*      */         case "OPTIONAL":
/*      */           break;
/*      */         case "COMPATIBLE":
/* 2102 */           if (qmgrFapLevel < 13) {
/*      */             break;
/*      */           }
/*      */ 
/*      */         
/*      */         default:
/* 2108 */           if (!isEncrypted()) {
/*      */ 
/*      */             
/* 2111 */             JmqiException traceRet = new JmqiException(this.env, 9503, new String[] { null, null, getChannelName() }, 2, 2594, null);
/*      */ 
/*      */ 
/*      */             
/* 2115 */             if (Trace.isOn) {
/* 2116 */               Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "validatePasswordProtectionAlgorithm(int)", (Throwable)traceRet, 2);
/*      */             }
/*      */             
/* 2119 */             throw traceRet;
/*      */           } 
/*      */           break;
/*      */       } 
/*      */     
/*      */     } else {
/* 2125 */       boolean ppaIsGood = false;
/* 2126 */       for (int goodPpa : PAL_ALL_NON_NULL) {
/* 2127 */         if (goodPpa == this.ppa) {
/* 2128 */           ppaIsGood = true;
/*      */           break;
/*      */         } 
/*      */       } 
/* 2132 */       if (!ppaIsGood) {
/*      */ 
/*      */         
/* 2135 */         JmqiException traceRet = new JmqiException(this.env, 9503, new String[] { null, null, getChannelName() }, 2, 2594, null);
/*      */ 
/*      */ 
/*      */         
/* 2139 */         if (Trace.isOn) {
/* 2140 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "validatePasswordProtectionAlgorithm(int)", (Throwable)traceRet, 3);
/*      */         }
/*      */         
/* 2143 */         throw traceRet;
/*      */       } 
/*      */     } 
/* 2146 */     if (Trace.isOn) {
/* 2147 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "validatePasswordProtectionAlgorithm(int)");
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
/*      */   private void sendUidFlow(RemoteTls tls) throws JmqiException {
/* 2162 */     if (Trace.isOn) {
/* 2163 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "sendUidFlow(RemoteTls)", new Object[] { tls });
/*      */     }
/*      */     
/* 2166 */     JmqiTls jTls = ((JmqiSystemEnvironment)this.env).getJmqiTls(tls);
/*      */ 
/*      */     
/* 2169 */     RfpTSH sTSH = allocInitialDataTsh(8);
/*      */ 
/*      */     
/* 2172 */     RfpUID uidFlow = new RfpUID(this.env, sTSH.getRfpBuffer(), sTSH.getRfpOffset() + sTSH.hdrSize());
/* 2173 */     uidFlow.clearUIDStructure(this.fapLevel);
/* 2174 */     uidFlow.initEyecatcher();
/* 2175 */     String userIdForFlow = null;
/* 2176 */     String passwordForFlow = null;
/* 2177 */     if (!this.flowMQCSPData) {
/*      */ 
/*      */       
/* 2180 */       userIdForFlow = this.remoteConnectionSpec.getUidFlowUserId();
/* 2181 */       passwordForFlow = this.remoteConnectionSpec.getUidFlowPassword();
/*      */     } 
/* 2183 */     if (userIdForFlow == null || userIdForFlow.length() == 0) {
/* 2184 */       userIdForFlow = JmqiTools.getUsername();
/* 2185 */       if (Trace.isOn) {
/* 2186 */         Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "sendUidFlow(RemoteTls)", "Blanking the password and using the value of Java System Property user.name as  the User Identifier: ", userIdForFlow);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2194 */       passwordForFlow = "";
/*      */     } 
/* 2196 */     uidFlow.setUserIdentifier(userIdForFlow, this.codepage, jTls);
/* 2197 */     uidFlow.setPassword(passwordForFlow, this.codepage, jTls);
/* 2198 */     int tshFlowSize = sTSH.hdrSize();
/* 2199 */     if (this.fapLevel < 5) {
/* 2200 */       tshFlowSize += 28;
/*      */     } else {
/*      */       
/* 2203 */       uidFlow.setLongUserId(userIdForFlow, this.codepage, jTls);
/*      */       
/* 2205 */       tshFlowSize += 132;
/*      */     } 
/* 2207 */     sTSH.setTransLength(tshFlowSize);
/*      */     
/* 2209 */     sendTSH(tls, sTSH, null, uidFlow.getPasswordOffset());
/*      */     
/* 2211 */     if (this.serverSecurityExit) {
/*      */       
/* 2213 */       boolean securityChecksComplete = false;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2218 */       while (!securityChecksComplete) {
/*      */         
/* 2220 */         RfpTSH rTSH = receiveTSH(null, tls, null);
/* 2221 */         switch (rTSH.getSegmentType()) {
/*      */ 
/*      */           
/*      */           case 6:
/* 2225 */             sTSH = allocInitialDataTsh(6);
/*      */ 
/*      */             
/* 2228 */             sTSH.setTransLength(sTSH.tshHdrSize() + 4);
/*      */             
/* 2230 */             ((JmqiSystemEnvironment)this.env)
/* 2231 */               .getDC()
/* 2232 */               .writeI32(0, sTSH
/*      */                 
/* 2234 */                 .getRfpBuffer(), sTSH
/* 2235 */                 .tshHdrSize(), 
/* 2236 */                 !(ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN));
/*      */ 
/*      */             
/* 2239 */             sendTSH(tls, sTSH, null);
/*      */             continue;
/*      */ 
/*      */ 
/*      */           
/*      */           case 5:
/* 2245 */             securityChecksComplete = true;
/*      */             continue;
/*      */         } 
/*      */ 
/*      */         
/* 2250 */         HashMap<String, Object> info1 = new HashMap<>();
/* 2251 */         info1.put("SegmentType", Integer.valueOf(rTSH.getSegmentType()));
/* 2252 */         info1.put("ControlFlags1", Integer.valueOf(rTSH.getControlFlags1()));
/* 2253 */         info1.put("Description", "Unexpected flow received during negotiation");
/*      */         
/* 2255 */         Trace.ffst(this, "sendUidFlow(RemoteTls)", "02", info1, null);
/* 2256 */         JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*      */ 
/*      */ 
/*      */         
/* 2260 */         if (Trace.isOn) {
/* 2261 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "sendUidFlow(RemoteTls)", (Throwable)traceRet1);
/*      */         }
/*      */         
/* 2264 */         throw traceRet1;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2270 */     if (Trace.isOn) {
/* 2271 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "sendUidFlow(RemoteTls)");
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
/*      */   private void setUpAsyncMode() throws JmqiException {
/* 2285 */     if (Trace.isOn) {
/* 2286 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "setUpAsyncMode()");
/*      */     }
/*      */     
/* 2289 */     protocolSetupAsyncMode();
/*      */     
/* 2291 */     this.rcvThread = new RemoteRcvThread(this.env, this);
/* 2292 */     this.env.getThreadPoolFactory().getThreadPool().enqueue(this.rcvThread);
/* 2293 */     this.multiplexingEnabled = true;
/*      */     
/* 2295 */     if (Trace.isOn) {
/* 2296 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "setUpAsyncMode()");
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
/*      */   void initSecurityExits(MQCD channelDef, Object securityExitP, String securityExitUserData, String exitClassPath) throws JmqiException {
/* 2318 */     if (Trace.isOn) {
/* 2319 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "initSecurityExits(MQCD,Object,String,String)", new Object[] { channelDef, securityExitP, securityExitUserData, exitClassPath });
/*      */     }
/*      */ 
/*      */     
/* 2323 */     Object securityExit = securityExitP;
/*      */ 
/*      */ 
/*      */     
/* 2327 */     if (securityExit instanceof String) {
/* 2328 */       String securityExitString = (String)securityExit;
/* 2329 */       if (securityExitString.trim().length() == 0) {
/* 2330 */         securityExit = null;
/*      */       }
/*      */     } 
/*      */     
/* 2334 */     String secExit = channelDef.getSecurityExit();
/* 2335 */     if (securityExit != null || (secExit != null && secExit.trim().length() > 0)) {
/* 2336 */       this.channelSecurityExit = new RemoteExitChain(this.env, 11);
/*      */       
/* 2338 */       this.channelSecurityExit.loadExits(channelDef, securityExit, this, securityExitUserData, exitClassPath);
/*      */       
/* 2340 */       if (securityExitUserData != null && securityExitUserData.trim().length() > 0) {
/* 2341 */         channelDef.setSecurityUserData(securityExitUserData);
/*      */       }
/*      */       
/* 2344 */       this.channelSecurityExit.initExits(channelDef, this.fapLevel, this.remoteProductId, true);
/*      */       
/* 2346 */       if (this.channelSecurityExit.getExitFapLevel() != 0 && this.channelSecurityExit
/* 2347 */         .getExitFapLevel() < this.fapLevel) {
/* 2348 */         this.fapLevel = this.channelSecurityExit.getExitFapLevel();
/*      */       }
/*      */     } 
/*      */     
/* 2352 */     if (Trace.isOn) {
/* 2353 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "initSecurityExits(MQCD,Object,String,String)");
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
/*      */   public void initOAMUserAuth(RemoteTls tls, MQCSP securityParmsP, RemoteSession session) throws JmqiException {
/* 2375 */     if (Trace.isOn) {
/* 2376 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "initOAMUserAuth(RemoteTls,MQCSP,RemoteSession)", new Object[] { tls, securityParmsP, session });
/*      */     }
/*      */ 
/*      */     
/* 2380 */     JmqiTls jTls = ((JmqiSystemEnvironment)this.env).getJmqiTls(tls);
/* 2381 */     String userId = null;
/* 2382 */     String newUserId = null;
/* 2383 */     int userIdLen = 0;
/* 2384 */     int userIdMaxLen = 0;
/* 2385 */     String password = null;
/* 2386 */     String newPassword = null;
/* 2387 */     int passwordLen = 0;
/* 2388 */     int passwordMaxLen = 0;
/* 2389 */     MQCSP securityParms = securityParmsP;
/* 2390 */     if (securityParms != null) {
/*      */ 
/*      */ 
/*      */       
/* 2394 */       userId = securityParms.getCspUserId();
/* 2395 */       if (userId != null) {
/* 2396 */         userIdLen = userId.length();
/* 2397 */         userIdMaxLen = userIdLen;
/*      */       } 
/* 2399 */       password = securityParms.getCspPassword();
/* 2400 */       if (password != null) {
/* 2401 */         passwordLen = password.length();
/* 2402 */         passwordMaxLen = passwordLen;
/*      */       } 
/*      */     } 
/* 2405 */     if (this.channelSecurityExit != null) {
/*      */ 
/*      */       
/* 2408 */       securityParms = this.channelSecurityExit.callScyExitSecParms(tls, securityParms, this.negotiatedChannel, this, session);
/*      */ 
/*      */       
/* 2411 */       if (securityParms != null) {
/* 2412 */         newUserId = securityParms.getCspUserId();
/* 2413 */         if (newUserId != null) {
/* 2414 */           userId = newUserId;
/* 2415 */           userIdLen = userId.length();
/* 2416 */           if (userIdLen > userIdMaxLen) {
/* 2417 */             userIdMaxLen = userIdLen;
/*      */           }
/*      */         } 
/* 2420 */         newPassword = securityParms.getCspPassword();
/* 2421 */         if (newPassword != null) {
/* 2422 */           password = newPassword;
/* 2423 */           passwordLen = password.length();
/* 2424 */           if (passwordLen > passwordMaxLen) {
/* 2425 */             passwordMaxLen = passwordLen;
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 2431 */     if (securityParms == null) {
/*      */ 
/*      */       
/* 2434 */       if (Trace.isOn) {
/* 2435 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "initOAMUserAuth(RemoteTls,MQCSP,RemoteSession)", 1);
/*      */       }
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */ 
/*      */     
/* 2443 */     if (this.channelSecurityExit == null)
/*      */     {
/*      */       
/* 2446 */       if (!this.flowMQCSPData) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2452 */         if (Trace.isOn) {
/* 2453 */           Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "initOAMUserAuth(RemoteTls,MQCSP,RemoteSession)", 2);
/*      */         }
/*      */ 
/*      */         
/*      */         return;
/*      */       } 
/*      */     }
/*      */     
/* 2461 */     RfpTSH sTSH = session.allocateTSH(10, 0, null);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2468 */     RfpCAUT sCAUT = new RfpCAUT(this.env, sTSH.getRfpBuffer(), sTSH.getRfpOffset() + sTSH.hdrSize());
/*      */     
/* 2470 */     sCAUT.initEyecatcher();
/* 2471 */     sCAUT.setAuthType(securityParms.getAuthenticationType(), this.swap);
/* 2472 */     sCAUT.setUserIDMaxLen(userIdMaxLen, this.swap);
/* 2473 */     sCAUT.setPasswdMaxLen(passwordMaxLen, this.swap);
/* 2474 */     sCAUT.setUserIDLen(userIdLen, this.swap);
/* 2475 */     int pwlo = sCAUT.setPasswordLen(passwordLen, this.swap);
/*      */     
/* 2477 */     if (userIdLen != 0) {
/* 2478 */       sCAUT.setUserId(userId, this.codepage, jTls);
/*      */     }
/*      */     
/* 2481 */     int passwordOffset = -1;
/* 2482 */     if (passwordLen != 0)
/*      */     {
/*      */       
/* 2485 */       passwordOffset = sCAUT.setPassword(password, userIdLen, this.codepage, jTls);
/*      */     }
/*      */     
/* 2488 */     int newPwl = RemotePPA.finishWritingAuthFlow(sTSH.getRfpBuffer(), pwlo, passwordOffset, this.r, this.ppa, this.dc, this.swap);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2493 */     if (newPwl < 0) {
/* 2494 */       if (Trace.isOn) {
/* 2495 */         Trace.data(this, "initOAMUserAuth(RemoteTls,MQCSP,RemoteSession)", "MQCSP password length error.  Obfuscated password length unexpectedly calculated as " + newPwl + " Disconnecting RemoteSession.", null);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 2501 */         session.disconnect(tls);
/*      */       }
/* 2503 */       catch (JmqiException je) {
/*      */ 
/*      */         
/* 2506 */         if (Trace.isOn) {
/* 2507 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "initOAMUserAuth(RemoteTls,MQCSP,RemoteSession)", (Throwable)je, 1);
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2513 */       HashMap<String, Object> info1 = new HashMap<>();
/* 2514 */       info1.put("Description", "MQCSP password length error. Obfuscated password length unexpectedly calculated as " + newPwl + ".");
/*      */       
/* 2516 */       info1.put("PPA", Integer.valueOf(this.ppa));
/* 2517 */       Trace.ffst(this, "initOAMUserAuth(RemoteTls,MQCSP,RemoteSession)", "01", info1, null);
/* 2518 */       JmqiException traceRet = new JmqiException(this.env, -1, null, 2, 2195, null);
/*      */       
/* 2520 */       if (Trace.isOn) {
/* 2521 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "initOAMUserAuth(RemoteTls,MQCSP,RemoteSession)", (Throwable)traceRet, 1);
/*      */       }
/*      */       
/* 2524 */       throw traceRet;
/*      */     } 
/*      */     
/* 2527 */     sTSH.setTransLength(sTSH.hdrSize() + 24 + userIdLen + newPwl);
/*      */ 
/*      */     
/* 2530 */     session.sendTSH(tls, sTSH, passwordOffset);
/*      */ 
/*      */     
/* 2533 */     RfpTSH rTSH = session.receiveTSH(tls, null);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 2539 */       if (rTSH.getSegmentType() == 5) {
/*      */ 
/*      */         
/* 2542 */         if ((rTSH.getControlFlags1() & 0x2) != 0) {
/* 2543 */           analyseErrorSegment(rTSH);
/*      */         }
/*      */       } else {
/*      */         
/* 2547 */         HashMap<String, Object> info1 = new HashMap<>();
/* 2548 */         info1.put("SegmentType", Integer.valueOf(rTSH.getSegmentType()));
/* 2549 */         info1.put("ControlFlags1", Integer.valueOf(rTSH.getControlFlags1()));
/* 2550 */         info1.put("Description", "Unknown response fron other side during OAM flows");
/*      */         
/* 2552 */         Trace.ffst(this, "initOAMUserAuth(RemoteTls,MQCSP,RemoteSession)", "02", info1, null);
/*      */ 
/*      */         
/* 2555 */         JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*      */ 
/*      */         
/* 2558 */         if (Trace.isOn) {
/* 2559 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "initOAMUserAuth(RemoteTls,MQCSP,RemoteSession)", (Throwable)traceRet1, 2);
/*      */         }
/*      */         
/* 2562 */         throw traceRet1;
/*      */       } 
/*      */     } finally {
/*      */       
/* 2566 */       if (Trace.isOn) {
/* 2567 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "initOAMUserAuth(RemoteTls,MQCSP,RemoteSession)");
/*      */       }
/*      */ 
/*      */       
/* 2571 */       releaseReceivedTSH(rTSH);
/*      */     } 
/*      */     
/* 2574 */     if (Trace.isOn) {
/* 2575 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "initOAMUserAuth(RemoteTls,MQCSP,RemoteSession)", 3);
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
/*      */   public void sendTSH(RemoteTls tls, RfpTSH tsh, RemoteSession remoteSession) throws JmqiException {
/* 2597 */     if (Trace.isOn) {
/* 2598 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "sendTSH(RemoteTls,RfpTSH,RemoteSession)", new Object[] { tls, tsh, remoteSession });
/*      */     }
/*      */     
/* 2601 */     sendTSH(tls, tsh, remoteSession, -1);
/* 2602 */     if (Trace.isOn) {
/* 2603 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "sendTSH(RemoteTls,RfpTSH,RemoteSession)");
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
/*      */   public void sendTSH(RemoteTls tls, RfpTSH tsh, RemoteSession remoteSession, int passwordOffsetP) throws JmqiException {
/* 2618 */     if (Trace.isOn) {
/* 2619 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "sendTSH(RemoteTls,RfpTSH,RemoteSession,int)", new Object[] { tls, tsh, remoteSession, 
/*      */             
/* 2621 */             Integer.valueOf(passwordOffsetP) });
/*      */     }
/* 2623 */     int passwordOffset = passwordOffsetP;
/*      */     
/* 2625 */     checkCorrectTshTypeForConnection(tsh);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2633 */     if (!tls.isReceiveThread) {
/* 2634 */       requestSendLock();
/*      */     }
/*      */     
/* 2637 */     int segmentType = tsh.getSegmentType();
/*      */ 
/*      */     
/* 2640 */     boolean compressionRequired = isTshCompressionRequired(remoteSession, tsh);
/*      */ 
/*      */     
/* 2643 */     if (isSecure()) {
/* 2644 */       requestRcvThreadSendLock();
/*      */     }
/*      */ 
/*      */     
/* 2648 */     if (tsh.getUserDataBuffer() != null || tsh.getNumUserDataBuffers() != 0) {
/* 2649 */       HashMap<String, Object> info1 = new HashMap<>();
/* 2650 */       info1.put("Description", "Unexpected attached buffers");
/* 2651 */       info1.put("Single buffer", tsh.getUserDataBuffer());
/* 2652 */       info1.put("Multiple buffer count", Integer.valueOf(tsh.getNumUserDataBuffers()));
/* 2653 */       Trace.ffst(this, "sendTSH(RemoteTls,RfpTSH,RemoteSession)", "04", info1, null);
/*      */       
/* 2655 */       JmqiException e = new JmqiException(this.env, -1, null, 2, 2195, null);
/*      */ 
/*      */       
/* 2658 */       if (Trace.isOn) {
/* 2659 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "sendTSH(RemoteTls,RfpTSH,RemoteSession,int)", (Throwable)e, 4);
/*      */       }
/*      */       
/* 2662 */       throw e;
/*      */     } 
/*      */ 
/*      */     
/* 2666 */     int tshHdrSize = tsh.tshHdrSize();
/* 2667 */     byte[] tshBuffer = tsh.getRfpBuffer();
/* 2668 */     int tshOffset = tsh.getRfpOffset();
/*      */ 
/*      */     
/* 2671 */     int bytesInTshBuffer = tsh.getTransLength();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2677 */     int tshFlowsSent = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 2683 */       int tshBytesAvailable = this.roundedTransmissionSize - bytesInTshBuffer;
/*      */ 
/*      */       
/* 2686 */       int padding = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2698 */       if (tshBytesAvailable < 0) {
/* 2699 */         if (Trace.isOn) {
/* 2700 */           Trace.data(this, "sendTSH(RemoteTls,RfpTSH,RemoteSession)", "Multiple TSH segments to send, tshBytesAvailable: " + tshBytesAvailable + ", bytesInTshBuffer: " + bytesInTshBuffer, null);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2706 */         tshBytesAvailable = -tshBytesAvailable;
/* 2707 */         byte[] buffer = obtainTshBufferFromTls(tls, this.roundedTransmissionSize);
/* 2708 */         int offset = tshOffset + tshHdrSize;
/* 2709 */         int length = this.roundedTransmissionSize - tshHdrSize;
/*      */ 
/*      */         
/* 2712 */         tsh.setControlFlags1(16);
/* 2713 */         tsh.setTransLength(this.roundedTransmissionSize);
/* 2714 */         tsh.hardenTransLength();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2720 */         while (tshBytesAvailable > 0) {
/*      */ 
/*      */ 
/*      */           
/* 2724 */           System.arraycopy(tshBuffer, tshOffset, buffer, 0, tshHdrSize);
/*      */ 
/*      */ 
/*      */           
/* 2728 */           System.arraycopy(tshBuffer, offset, buffer, tshHdrSize, length);
/*      */ 
/*      */           
/* 2731 */           wrapSend(tls, compressionRequired, remoteSession, buffer, 0, this.roundedTransmissionSize, segmentType, tsh
/*      */               
/* 2733 */               .getTshType(), passwordOffset);
/* 2734 */           passwordOffset = -1;
/*      */           
/* 2736 */           offset += length;
/* 2737 */           tshFlowsSent++;
/* 2738 */           tshBytesAvailable -= length;
/*      */           
/* 2740 */           tsh.setControlFlags1(0);
/* 2741 */           if (Trace.isOn) {
/* 2742 */             Trace.data(this, "sendTSH(RemoteTls,RfpTSH,RemoteSession)", "Completed TSH send, tshBytesAvailable: " + tshBytesAvailable + " flows sent: " + tshFlowsSent, null);
/*      */           }
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2751 */         bytesInTshBuffer -= offset;
/* 2752 */         System.arraycopy(tshBuffer, offset, tshBuffer, tshOffset + tshHdrSize, bytesInTshBuffer);
/* 2753 */         bytesInTshBuffer += tshHdrSize;
/* 2754 */         tsh.setControlFlags1(32);
/*      */ 
/*      */         
/* 2757 */         if (!isMultiplexingEnabled()) {
/* 2758 */           padding = 0;
/*      */         } else {
/*      */           
/* 2761 */           padding = (bytesInTshBuffer + 3 & 0xFFFFFFFC) - bytesInTshBuffer;
/*      */         } 
/* 2763 */         bytesInTshBuffer += padding;
/*      */       } 
/*      */       
/* 2766 */       int tshFlowSize = bytesInTshBuffer;
/*      */ 
/*      */       
/* 2769 */       int controlFlags1 = tsh.getControlFlags1();
/*      */       
/* 2771 */       if (tshFlowsSent++ == 0) {
/* 2772 */         controlFlags1 |= 0x10;
/*      */       }
/*      */       
/* 2775 */       controlFlags1 |= 0x20;
/*      */       
/* 2777 */       tsh.setControlFlags1(controlFlags1);
/*      */       
/* 2779 */       if (this.multiplexingEnabled) {
/*      */         
/* 2781 */         padding = (tshFlowSize + 3 & 0xFFFFFFFC) - tshFlowSize;
/* 2782 */         tshFlowSize += padding;
/*      */       } 
/*      */       
/* 2785 */       performKeyResetIfRequired(tshFlowSize, tls);
/*      */ 
/*      */       
/* 2788 */       tsh.setTransLength(tshFlowSize);
/* 2789 */       tsh.hardenTransLength();
/*      */ 
/*      */       
/* 2792 */       wrapSend(tls, compressionRequired, remoteSession, tshBuffer, tshOffset, bytesInTshBuffer, segmentType, tsh
/* 2793 */           .getTshType(), passwordOffset);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2799 */       tsh.setControlFlags1(0);
/*      */ 
/*      */ 
/*      */       
/* 2803 */       bytesInTshBuffer = tshHdrSize;
/*      */       
/* 2805 */       if (padding > 0) {
/* 2806 */         send(PADDING_BUFFER, 0, padding, segmentType, tsh.getTshType(), passwordOffset);
/*      */       }
/*      */     } finally {
/*      */       
/* 2810 */       if (Trace.isOn) {
/* 2811 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "sendTSH(RemoteTls,RfpTSH,RemoteSession,int)");
/*      */       }
/*      */ 
/*      */       
/* 2815 */       if (isSecure()) {
/* 2816 */         releaseRcvThreadSendLock();
/*      */       }
/*      */       
/* 2819 */       if (!tls.isReceiveThread) {
/* 2820 */         releaseSendLock();
/*      */       }
/*      */ 
/*      */       
/* 2824 */       tsh.getParentBuffer().free();
/*      */ 
/*      */       
/* 2827 */       tsh.setParentBuffer(null);
/* 2828 */       tsh.setRfpBuffer(null);
/*      */     } 
/*      */     
/* 2831 */     if (Trace.isOn) {
/* 2832 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "sendTSH(RemoteTls,RfpTSH,RemoteSession,int)");
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
/*      */   private void checkCorrectTshTypeForConnection(RfpTSH tsh) throws JmqiException {
/* 2846 */     switch (tsh.getTshType()) {
/*      */       case 0:
/* 2848 */         if (isMultiplexingEnabled()) {
/*      */           
/* 2850 */           HashMap<String, Object> hashMap = new HashMap<>();
/* 2851 */           hashMap.put("Description", "Attempt to send TSH flow over multiplexed channel");
/*      */           
/* 2853 */           Trace.ffst(this, "checkCorrectTshTypeForConnection(RfpTSH)", "01", hashMap, null);
/*      */           
/* 2855 */           JmqiException jmqiException = new JmqiException(this.env, -1, null, 2, 2195, null);
/*      */ 
/*      */ 
/*      */           
/* 2859 */           if (Trace.isOn) {
/* 2860 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "checkCorrectTshTypeForConnection(RfpTSH)", (Throwable)jmqiException, 1);
/*      */           }
/*      */           
/* 2863 */           throw jmqiException;
/*      */         } 
/*      */         return;
/*      */ 
/*      */ 
/*      */       
/*      */       case 1:
/*      */       case 2:
/* 2871 */         if (!isMultiplexingEnabled()) {
/*      */           
/* 2873 */           HashMap<String, Object> hashMap = new HashMap<>();
/* 2874 */           hashMap.put("Description", "Attempt to send TSHC/TSHM flow over non-multiplexed channel");
/*      */           
/* 2876 */           Trace.ffst(this, "checkCorrectTshTypeForConnection(RfpTSH)", "02", hashMap, null);
/*      */           
/* 2878 */           JmqiException jmqiException = new JmqiException(this.env, -1, null, 2, 2195, null);
/*      */ 
/*      */           
/* 2881 */           if (Trace.isOn) {
/* 2882 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "checkCorrectTshTypeForConnection(RfpTSH)", (Throwable)jmqiException, 2);
/*      */           }
/*      */           
/* 2885 */           throw jmqiException;
/*      */         } 
/*      */         return;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2893 */     HashMap<String, Object> info = new HashMap<>();
/* 2894 */     info.put("TSH type", Integer.valueOf(tsh.getTshType()));
/* 2895 */     info.put("Description", "Invalid TSH type");
/* 2896 */     Trace.ffst(this, "checkCorrectTshTypeForConnection(RfpTSH)", "03", info, null);
/*      */     
/* 2898 */     JmqiException e = new JmqiException(this.env, -1, null, 2, 2195, null);
/*      */ 
/*      */     
/* 2901 */     if (Trace.isOn) {
/* 2902 */       Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "checkCorrectTshTypeForConnection(RfpTSH)", (Throwable)e, 3);
/*      */     }
/*      */     
/* 2905 */     throw e;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isTshCompressionRequired(RemoteSession remoteSession, RfpTSH tsh) {
/* 2911 */     boolean compressionReq = false;
/* 2912 */     int segmentType = tsh.getSegmentType();
/* 2913 */     if (remoteSession != null)
/*      */     {
/* 2915 */       compressionReq = (this.compressionRequired && (segmentType == 129 || segmentType == 131 || segmentType == 134 || segmentType == 135 || segmentType == 133));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2921 */     if (Trace.isOn) {
/* 2922 */       Trace.data(this, "isTshCompressionRequired(RemoteSession,RfpTSH)", "getter", Boolean.valueOf(compressionReq));
/*      */     }
/* 2924 */     return compressionReq;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void performKeyResetIfRequired(int tshFlowSize, RemoteTls tls) throws JmqiException {
/* 2931 */     if (this.keyReset == 0 && 
/* 2932 */       this.secureKeyResetCount > 0 && isSecure()) {
/* 2933 */       synchronized (this.sslResetCountSync) {
/* 2934 */         this.bytesSinceKeyReset += tshFlowSize;
/*      */       } 
/* 2936 */       if (this.bytesSinceKeyReset >= this.secureKeyResetCount || this.heartbeatKeyResetRequired) {
/* 2937 */         if (isMultiplexingEnabled()) {
/* 2938 */           performSecureKeyResetMpx(tls);
/*      */         } else {
/*      */           
/* 2941 */           performSecureKeyReset();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void wrapSend(RemoteTls tls, boolean compressionRequired1, RemoteSession remoteSession, byte[] bufferP, int offsetP, int sizeP, int segmentType, int tshType, int passwordOffset) throws JmqiException {
/* 2967 */     if (Trace.isOn) {
/* 2968 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "wrapSend(RemoteTls,boolean,RemoteSession,byte [ ],int,int,int,int,int)", new Object[] { tls, 
/*      */             
/* 2970 */             Boolean.valueOf(this.compressionRequired), remoteSession, sanitizeBuffer(bufferP, sizeP, passwordOffset), 
/* 2971 */             Integer.valueOf(offsetP), Integer.valueOf(sizeP), Integer.valueOf(segmentType), Integer.valueOf(tshType), 
/* 2972 */             Integer.valueOf(passwordOffset) });
/*      */     }
/* 2974 */     byte[] buffer = bufferP;
/* 2975 */     int offset = offsetP;
/* 2976 */     int size = sizeP;
/*      */     
/* 2978 */     if (compressionRequired1 || (remoteSession != null && remoteSession.hasSendExits())) {
/* 2979 */       RfpTSH sendTSH = allocateTSH(tshType, segmentType, null);
/* 2980 */       sendTSH.setRfpBuffer(buffer);
/* 2981 */       sendTSH.setTransLength(size);
/* 2982 */       sendTSH.setRfpOffset(offset);
/*      */ 
/*      */       
/* 2985 */       if (compressionRequired1) {
/* 2986 */         buffer = remoteSession.compress(sendTSH);
/* 2987 */         size = sendTSH.getTransLength();
/* 2988 */         sendTSH.setRfpBuffer(buffer);
/* 2989 */         sendTSH.setTransLength(size);
/* 2990 */         sendTSH.setRfpOffset(0);
/* 2991 */         offset = 0;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2996 */       if (remoteSession.hasSendExits()) {
/*      */         
/*      */         try {
/* 2999 */           sendTSH = remoteSession.callSendExits(sendTSH);
/*      */         }
/* 3001 */         catch (JmqiException jmqe) {
/* 3002 */           if (Trace.isOn) {
/* 3003 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "wrapSend(RemoteTls,boolean,RemoteSession,byte [ ],int,int,int,int,int)", (Throwable)jmqe);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 3008 */           if (jmqe.getReason() == 2538 || jmqe.getReason() == 2059 || jmqe.getReason() == 2063)
/*      */           {
/* 3010 */             remoteSession.disconnect(remoteSession
/* 3011 */                 .getParentConnection().getRemoteFap().getTls());
/*      */           }
/* 3013 */           if (Trace.isOn) {
/* 3014 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "wrapSend(RemoteTls,boolean,RemoteSession,byte [ ],int,int,int,int,int)", (Throwable)jmqe);
/*      */           }
/*      */           
/* 3017 */           throw jmqe;
/*      */         } 
/*      */       }
/*      */       
/* 3021 */       size = sendTSH.getTransLength();
/* 3022 */       buffer = sendTSH.getRfpBuffer();
/* 3023 */       offset = sendTSH.getRfpOffset();
/*      */     } 
/*      */     
/* 3026 */     send(buffer, offset, size, segmentType, tshType, passwordOffset);
/*      */     
/* 3028 */     if (Trace.isOn) {
/* 3029 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "wrapSend(RemoteTls,boolean,RemoteSession,byte [ ],int,int,int,int,int)");
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
/*      */   private byte[] obtainTshBufferFromTls(RemoteTls tls, int bufferSize) {
/* 3045 */     if (Trace.isOn) {
/* 3046 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "obtainTshBufferFromTls(RemoteTls,int)", new Object[] { tls, 
/* 3047 */             Integer.valueOf(bufferSize) });
/*      */     }
/* 3049 */     boolean newBufferCreated = false;
/* 3050 */     if (tls.tshBuffer == null || tls.tshBuffer.length < bufferSize) {
/* 3051 */       tls.tshBuffer = new byte[bufferSize];
/* 3052 */       newBufferCreated = true;
/*      */     } 
/* 3054 */     if (Trace.isOn) {
/* 3055 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "obtainTshBufferFromTls(RemoteTls,int)", "newBufferCreated: " + newBufferCreated);
/*      */     }
/*      */     
/* 3058 */     return tls.tshBuffer;
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
/*      */   private void performSecureKeyResetMpx(RemoteTls tls) throws JmqiException {
/* 3071 */     if (Trace.isOn) {
/* 3072 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "performSecureKeyResetMpx(RemoteTls)", new Object[] { tls });
/*      */     }
/*      */     
/* 3075 */     this.heartbeatKeyResetRequired = false;
/*      */     
/* 3077 */     RfpTSH satTSH = allocateTSH(1, 12, null);
/*      */ 
/*      */     
/* 3080 */     satTSH.setTransLength(satTSH.hdrSize() + 20);
/* 3081 */     satTSH.hardenTransLength();
/*      */ 
/*      */     
/* 3084 */     RfpSOCKACT sat = new RfpSOCKACT(this.env, satTSH.getRfpBuffer(), satTSH.offset + satTSH.hdrSize());
/* 3085 */     sat.setConversationId(0, this.swap);
/* 3086 */     sat.setRequestId(0, this.swap);
/* 3087 */     sat.setType(4, this.swap);
/*      */     
/* 3089 */     this.keyReset = 1;
/*      */     try {
/* 3091 */       this.keyResetComplete = false;
/*      */       
/* 3093 */       send(satTSH.getRfpBuffer(), satTSH.offset, satTSH.hdrSize() + 20, 12, satTSH
/* 3094 */           .getTshType(), -1);
/*      */       
/* 3096 */       satTSH.getParentBuffer().free();
/* 3097 */       satTSH.setParentBuffer(null);
/* 3098 */       satTSH.setRfpBuffer(null);
/* 3099 */       releaseRcvThreadSendLock();
/*      */       try {
/* 3101 */         synchronized (this.keyResetSemaphore) {
/* 3102 */           boolean waiting = true;
/* 3103 */           long endTime = System.currentTimeMillis() + 10000L;
/* 3104 */           long waitTime = 10000L;
/* 3105 */           for (; waiting && waitTime > 0L; 
/* 3106 */             waitTime = endTime - System.currentTimeMillis()) {
/*      */             
/*      */             try {
/* 3109 */               this.keyResetSemaphore.wait(waitTime);
/*      */               
/* 3111 */               waiting = false;
/*      */             }
/* 3113 */             catch (InterruptedException ie) {
/* 3114 */               if (Trace.isOn) {
/* 3115 */                 Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "performSecureKeyResetMpx(RemoteTls)", ie);
/*      */               }
/*      */             } 
/*      */           } 
/*      */ 
/*      */           
/* 3121 */           if (!this.keyResetComplete) {
/*      */             
/* 3123 */             JmqiException traceRet1 = new JmqiException(this.env, 9213, null, 2, 2538, null);
/*      */ 
/*      */             
/* 3126 */             if (Trace.isOn) {
/* 3127 */               Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "performSecureKeyResetMpx(RemoteTls)", (Throwable)traceRet1);
/*      */             }
/*      */             
/* 3130 */             throw traceRet1;
/*      */           } 
/*      */         } 
/*      */       } finally {
/*      */         
/* 3135 */         requestRcvThreadSendLock();
/*      */       } 
/*      */     } finally {
/*      */       
/* 3139 */       this.keyReset = 0;
/*      */     } 
/* 3141 */     if (Trace.isOn) {
/* 3142 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "performSecureKeyResetMpx(RemoteTls)");
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
/*      */   public void performSecureKeyReset() throws JmqiException {
/* 3155 */     if (Trace.isOn) {
/* 3156 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "performSecureKeyReset()");
/*      */     }
/*      */     
/* 3159 */     protocolSecureKeyReset();
/*      */     
/* 3161 */     synchronized (this.sslResetCountSync) {
/* 3162 */       this.bytesSinceKeyReset = 0;
/*      */     } 
/* 3164 */     this.heartbeatKeyResetRequired = false;
/*      */     
/* 3166 */     if (Trace.isOn) {
/* 3167 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "performSecureKeyReset()");
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
/*      */   public RfpTSH receiveTSH(RemoteSession session, RemoteTls tls, RfpTSH rTSHP) throws JmqiException {
/* 3192 */     if (Trace.isOn) {
/* 3193 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "receiveTSH(RemoteSession,RemoteTls,RfpTSH)", new Object[] { session, tls, rTSHP });
/*      */     }
/*      */     
/* 3196 */     RfpTSH rTSH = rTSHP;
/*      */ 
/*      */     
/* 3199 */     if (isMultiplexingEnabled()) {
/*      */       
/* 3201 */       rTSH = receiveAsyncTsh();
/*      */       
/* 3203 */       if (Trace.isOn) {
/* 3204 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "receiveTSH(RemoteSession,RemoteTls,RfpTSH)", rTSH, 1);
/*      */       }
/*      */       
/* 3207 */       return rTSH;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3217 */     boolean tshRead = false;
/*      */     
/* 3219 */     RemoteCommsBuffer commsBuffer = null;
/* 3220 */     int tshPosition = 0;
/* 3221 */     int bytesInBuffer = 0;
/* 3222 */     int tshSize = 0;
/*      */ 
/*      */     
/* 3225 */     boolean nonHBFlowReceived = false;
/* 3226 */     while (!nonHBFlowReceived) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3234 */       int capacity = this.maxTransmissionSize;
/*      */       
/* 3236 */       if (isMultiplexingEnabled()) {
/* 3237 */         capacity += 8;
/*      */       }
/*      */       
/* 3240 */       commsBuffer = (this.fap.getTls()).receiveBufferPool.allocBuffer(capacity);
/*      */ 
/*      */       
/* 3243 */       byte[] receiveBuffer = commsBuffer.getBuffer();
/*      */       
/* 3245 */       if (rTSH == null) {
/* 3246 */         rTSH = new RfpTSH(this.env, receiveBuffer, -1);
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 3251 */         System.arraycopy(rTSH.getRfpBuffer(), rTSH.getParentBuffer()
/* 3252 */             .getDataPosition(), receiveBuffer, 0, rTSH
/* 3253 */             .getParentBuffer().getDataAvailable());
/* 3254 */         rTSH.setRfpBuffer(receiveBuffer);
/* 3255 */         rTSH.setRfpOffset(0);
/* 3256 */         commsBuffer.setDataAvailable(rTSH.getParentBuffer()
/* 3257 */             .getDataAvailable());
/* 3258 */         commsBuffer.setDataPosition(rTSH.getParentBuffer()
/* 3259 */             .getDataPosition());
/*      */       } 
/*      */       
/* 3262 */       rTSH.setParentBuffer(commsBuffer);
/*      */ 
/*      */ 
/*      */       
/* 3266 */       tshPosition = commsBuffer.getDataPosition();
/* 3267 */       bytesInBuffer = commsBuffer.getDataAvailable();
/* 3268 */       tshSize = 0;
/*      */ 
/*      */       
/* 3271 */       if (bytesInBuffer == 0) {
/* 3272 */         tshPosition = 0;
/*      */       }
/*      */ 
/*      */       
/* 3276 */       while (!tshRead) {
/*      */         
/* 3278 */         if (bytesInBuffer < 0 || tshPosition < 0) {
/* 3279 */           HashMap<String, Object> info1 = new HashMap<>();
/* 3280 */           info1.put("bytesInBuffer", Integer.valueOf(bytesInBuffer));
/* 3281 */           info1.put("tshPosition", Integer.valueOf(tshPosition));
/* 3282 */           info1.put("Description", "Unexpected buffer state");
/* 3283 */           Trace.ffst(this, "receiveTSH(RemoteSession,RemoteTls,RfpTSH)", "01", info1, null);
/*      */ 
/*      */           
/* 3286 */           JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*      */ 
/*      */           
/* 3289 */           if (Trace.isOn) {
/* 3290 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "receiveTSH(RemoteSession,RemoteTls,RfpTSH)", (Throwable)traceRet1, 1);
/*      */           }
/*      */           
/* 3293 */           throw traceRet1;
/*      */         } 
/*      */ 
/*      */         
/* 3297 */         if (bytesInBuffer >= 28) {
/*      */ 
/*      */           
/* 3300 */           rTSH.setRfpOffset(tshPosition);
/* 3301 */           rTSH.checkEyecatcher();
/*      */ 
/*      */           
/* 3304 */           if (bytesInBuffer >= (tshSize = rTSH.getTransLength()))
/*      */           {
/*      */             
/* 3307 */             tshRead = true;
/*      */           }
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3316 */         if (!tshRead && tshPosition + bytesInBuffer >= receiveBuffer.length && 
/* 3317 */           tshPosition != 0) {
/* 3318 */           for (int i = tshPosition; i < tshPosition + bytesInBuffer; i++) {
/* 3319 */             receiveBuffer[i - tshPosition] = receiveBuffer[tshPosition];
/*      */           }
/*      */           
/* 3322 */           tshPosition = 0;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3328 */         if (!tshRead) {
/*      */           
/*      */           try {
/*      */             
/* 3332 */             int bytesRead = receive(receiveBuffer, bytesInBuffer, receiveBuffer.length - bytesInBuffer);
/*      */ 
/*      */             
/* 3335 */             if (bytesRead < 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 3341 */               JmqiException traceRet3 = new JmqiException(this.env, 9208, new String[] { Integer.toString(bytesRead), Integer.toHexString(bytesRead), getRemoteHostDescr(), getTrpType() }, 2, 2009, null);
/*      */ 
/*      */               
/* 3344 */               if (Trace.isOn) {
/* 3345 */                 Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "receiveTSH(RemoteSession,RemoteTls,RfpTSH)", (Throwable)traceRet3, 2);
/*      */               }
/*      */               
/* 3348 */               throw traceRet3;
/*      */             } 
/*      */             
/* 3351 */             bytesInBuffer += bytesRead;
/*      */ 
/*      */             
/* 3354 */             if (this.secureKeyResetCount > 0 && isSecure()) {
/* 3355 */               synchronized (this.sslResetCountSync) {
/* 3356 */                 this.bytesSinceKeyReset += bytesRead;
/*      */               }
/*      */             
/*      */             }
/* 3360 */           } catch (JmqiException e) {
/* 3361 */             if (Trace.isOn) {
/* 3362 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "receiveTSH(RemoteSession,RemoteTls,RfpTSH)", (Throwable)e);
/*      */             }
/*      */ 
/*      */             
/* 3366 */             disconnect(tls);
/*      */             
/* 3368 */             if (Trace.isOn) {
/* 3369 */               Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "receiveTSH(RemoteSession,RemoteTls,RfpTSH)", (Throwable)e, 3);
/*      */             }
/*      */             
/* 3372 */             throw e;
/*      */           } 
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3380 */       if (tshSize < 0 || tshPosition + tshSize > bytesInBuffer) {
/* 3381 */         HashMap<String, Object> info1 = new HashMap<>();
/* 3382 */         info1.put("bytesInBuffer", Integer.valueOf(bytesInBuffer));
/* 3383 */         info1.put("tshSize", Integer.valueOf(tshSize));
/* 3384 */         info1.put("tshPosition", Integer.valueOf(tshPosition));
/* 3385 */         info1.put("Description", "Unexpected buffer state");
/* 3386 */         Trace.ffst(this, "receiveTSH(RemoteSession,RemoteTls,RfpTSH)", "02", info1, null);
/*      */         
/* 3388 */         JmqiException traceRet4 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*      */ 
/*      */         
/* 3391 */         if (Trace.isOn) {
/* 3392 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "receiveTSH(RemoteSession,RemoteTls,RfpTSH)", (Throwable)traceRet4, 4);
/*      */         }
/*      */         
/* 3395 */         throw traceRet4;
/*      */       } 
/*      */       
/* 3398 */       if (session != null) {
/* 3399 */         if (Trace.isOn) {
/* 3400 */           ByteBuffer wrapper = ByteBuffer.wrap(rTSH.getRfpBuffer());
/* 3401 */           wrapper.position(rTSH.getRfpOffset());
/* 3402 */           wrapper.limit(rTSH.getRfpOffset() + tshSize);
/* 3403 */           Trace.data(this, "receiveTSH(RemoteSession,RemoteTls,RfpTSH)", "Received data", wrapper);
/*      */         } 
/*      */ 
/*      */         
/* 3407 */         session.processReceivedTsh(tls, rTSH);
/* 3408 */         if (Trace.isOn) {
/* 3409 */           ByteBuffer wrapper = ByteBuffer.wrap(rTSH.getRfpBuffer());
/* 3410 */           wrapper.position(rTSH.getRfpOffset());
/* 3411 */           wrapper.limit(rTSH.getRfpOffset() + tshSize);
/* 3412 */           Trace.data(this, "receiveTSH(RemoteSession,RemoteTls,RfpTSH)", "postProcessed data", wrapper);
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3420 */       if ((rTSH.getControlFlags1() & 0x8) != 0) {
/* 3421 */         analyseErrorSegment(rTSH);
/*      */       }
/* 3423 */       else if (rTSH.getSegmentType() == 9) {
/*      */         
/* 3425 */         releaseReceivedTSH(rTSH);
/*      */ 
/*      */ 
/*      */         
/* 3429 */         if (this.fapLevel < 8 && this.secureKeyResetCount > 0 && isSecure()) {
/* 3430 */           performSecureKeyReset();
/*      */         }
/*      */ 
/*      */         
/* 3434 */         RfpTSH heartbeatTSH = allocateTSH(rTSH.getTshType(), 9, null);
/*      */ 
/*      */         
/* 3437 */         heartbeatTSH.setTransLength(rTSH.hdrSize());
/*      */         
/* 3439 */         sendTSH(tls, heartbeatTSH, null);
/*      */         
/* 3441 */         tshRead = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3447 */         if (this.fapLevel >= 8 && this.secureKeyResetCount > 0 && isSecure()) {
/* 3448 */           this.heartbeatKeyResetRequired = true;
/*      */         }
/*      */       }
/* 3451 */       else if (rTSH.getSegmentType() == 11) {
/*      */ 
/*      */         
/* 3454 */         releaseReceivedTSH(rTSH);
/*      */ 
/*      */         
/* 3457 */         performSecureKeyReset();
/*      */ 
/*      */         
/* 3460 */         RfpTSH keyResetTSH = allocateTSH(rTSH.getTshType(), 5, null);
/*      */ 
/*      */         
/* 3463 */         keyResetTSH.setTransLength(rTSH.hdrSize());
/* 3464 */         keyResetTSH.setControlFlags1(64);
/*      */         
/* 3466 */         sendTSH(tls, keyResetTSH, null);
/*      */         
/* 3468 */         tshRead = false;
/*      */       }
/*      */       else {
/*      */         
/* 3472 */         nonHBFlowReceived = true;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 3477 */       bytesInBuffer -= tshSize;
/*      */       
/* 3479 */       if (bytesInBuffer == 0) {
/* 3480 */         tshPosition = 0;
/*      */         continue;
/*      */       } 
/* 3483 */       tshPosition += tshSize;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 3488 */     commsBuffer.setDataPosition(tshPosition);
/* 3489 */     commsBuffer.setDataAvailable(bytesInBuffer);
/*      */ 
/*      */     
/* 3492 */     if (this.remoteEncoding != rTSH.getEncoding()) {
/* 3493 */       this.remoteEncoding = rTSH.getEncoding();
/*      */       
/* 3495 */       if (this.remoteEncoding == 1) {
/* 3496 */         this.swap = false;
/*      */       }
/* 3498 */       else if (this.remoteEncoding == 2) {
/* 3499 */         this.swap = true;
/*      */       }
/*      */       else {
/*      */         
/* 3503 */         HashMap<String, Object> info1 = new HashMap<>();
/* 3504 */         info1.put("remoteEncoding", Integer.valueOf(this.remoteEncoding));
/* 3505 */         info1.put("Description", "Unknown encoding received from queue manager");
/*      */         
/* 3507 */         Trace.ffst(this, "receiveTSH(RemoteSession,RemoteTls,RfpTSH)", "03", info1, null);
/*      */         
/* 3509 */         JmqiException traceRet9 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*      */ 
/*      */ 
/*      */         
/* 3513 */         if (Trace.isOn) {
/* 3514 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "receiveTSH(RemoteSession,RemoteTls,RfpTSH)", (Throwable)traceRet9, 5);
/*      */         }
/*      */         
/* 3517 */         throw traceRet9;
/*      */       } 
/*      */     } 
/* 3520 */     if (Trace.isOn) {
/* 3521 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "receiveTSH(RemoteSession,RemoteTls,RfpTSH)", rTSH, 2);
/*      */     }
/*      */     
/* 3524 */     return rTSH;
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
/*      */   public RfpTSH allocateTSH(int tshType, int segmentType, RfpTSH tshHeaderP) throws JmqiException {
/* 3543 */     if (Trace.isOn)
/* 3544 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "allocateTSH(int,int,RfpTSH)", new Object[] {
/* 3545 */             Integer.valueOf(tshType), 
/* 3546 */             RemoteConstantDecoder.decodeSingleOption(segmentType, "rfpTST_"), tshHeaderP
/*      */           }); 
/* 3548 */     RfpTSH tshHeader = tshHeaderP;
/*      */     
/* 3550 */     int capacity = this.roundedTransmissionSize;
/* 3551 */     if (isMultiplexingEnabled()) {
/* 3552 */       capacity += 8;
/*      */     }
/* 3554 */     RemoteCommsBuffer commsBuffer = (this.fap.getTls()).sendBufferPool.allocBuffer(capacity);
/*      */ 
/*      */     
/* 3557 */     if (tshHeader == null) {
/* 3558 */       tshHeader = new RfpTSH(this.env, commsBuffer.getBuffer(), 0);
/*      */     } else {
/*      */       
/* 3561 */       tshHeader.setRfpBuffer(commsBuffer.getBuffer());
/* 3562 */       tshHeader.setRfpOffset(0);
/*      */     } 
/* 3564 */     tshHeader.setParentBuffer(commsBuffer);
/*      */     
/* 3566 */     tshHeader.setTshType(tshType);
/*      */     
/* 3568 */     tshHeader.setUserDataSingle(null, 0);
/* 3569 */     tshHeader.setUserDataMulti(null, 0);
/* 3570 */     tshHeader.initEyecatcher(tshType);
/* 3571 */     tshHeader.clearLuwid(tshType);
/* 3572 */     tshHeader.setSegmentType(segmentType);
/* 3573 */     tshHeader.setEncoding(this.swap ? 2 : 1);
/* 3574 */     tshHeader.setMQEncoding(273, this.swap);
/* 3575 */     tshHeader.setControlFlags1(0);
/* 3576 */     tshHeader.setControlFlags2(0);
/* 3577 */     tshHeader.setCcsid(this.localCcsid, this.swap);
/*      */     
/* 3579 */     if (Trace.isOn) {
/* 3580 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "allocateTSH(int,int,RfpTSH)", tshHeader);
/*      */     }
/*      */     
/* 3583 */     return tshHeader;
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
/*      */   public void releaseReceivedTSH(RfpTSH rTSH) throws JmqiException {
/* 3597 */     if (Trace.isOn) {
/* 3598 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "releaseReceivedTSH(RfpTSH)", new Object[] { rTSH });
/*      */     }
/*      */ 
/*      */     
/* 3602 */     RemoteCommsBuffer parent = rTSH.getParentBuffer();
/* 3603 */     if (parent != null) {
/*      */       
/* 3605 */       parent.free();
/*      */       
/* 3607 */       rTSH.setParentBuffer(null);
/*      */     
/*      */     }
/* 3610 */     else if (Trace.isOn) {
/* 3611 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "releaseReceivedTSH(RfpTSH)", "WARNING: An attempt was made to free a TSH that has already been freed. This is tolerated in this method, but it is not expected.");
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3618 */     rTSH.setRfpBuffer(null);
/*      */     
/* 3620 */     if (Trace.isOn) {
/* 3621 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "releaseReceivedTSH(RfpTSH)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void requestSendLock() {
/* 3631 */     if (Trace.isOn) {
/* 3632 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "requestSendLock()");
/*      */     }
/* 3634 */     this.sendMutex.lock();
/*      */     
/* 3636 */     if (Trace.isOn) {
/* 3637 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "requestSendLock()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void releaseSendLock() {
/* 3646 */     if (Trace.isOn) {
/* 3647 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "releaseSendLock()");
/*      */     }
/* 3649 */     this.sendMutex.unlock();
/*      */     
/* 3651 */     if (Trace.isOn) {
/* 3652 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "releaseSendLock()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void requestRcvThreadSendLock() {
/* 3661 */     if (Trace.isOn) {
/* 3662 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "requestRcvThreadSendLock()");
/*      */     }
/*      */     
/* 3665 */     this.rcvThreadSendMutex.lock();
/*      */     
/* 3667 */     if (Trace.isOn) {
/* 3668 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "requestRcvThreadSendLock()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void releaseRcvThreadSendLock() {
/* 3678 */     if (Trace.isOn) {
/* 3679 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "releaseRcvThreadSendLock()");
/*      */     }
/*      */     
/* 3682 */     this.rcvThreadSendMutex.unlock();
/*      */     
/* 3684 */     if (Trace.isOn) {
/* 3685 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "releaseRcvThreadSendLock()");
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
/*      */   public String getThreadIdentifier() {
/* 3701 */     StringBuffer traceBuff = new StringBuffer();
/* 3702 */     getThreadIdentifier(traceBuff);
/* 3703 */     traceBuff.append("]");
/* 3704 */     return traceBuff.toString();
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
/*      */   public void getThreadIdentifier(StringBuffer traceBuff) {
/* 3720 */     traceBuff.append(getClass().getName());
/* 3721 */     traceBuff.append("@");
/* 3722 */     traceBuff.append(System.identityHashCode(this));
/* 3723 */     traceBuff.append("[qmid=");
/* 3724 */     traceBuff.append(this.remoteQMID.trim());
/* 3725 */     traceBuff.append(",fap=");
/* 3726 */     traceBuff.append(this.fapLevel);
/* 3727 */     traceBuff.append(",");
/* 3728 */     traceBuff.append("channel=");
/* 3729 */     traceBuff.append(this.channelName);
/* 3730 */     traceBuff.append(",ccsid=");
/* 3731 */     traceBuff.append(this.localCcsid);
/* 3732 */     traceBuff.append(",sharecnv=");
/* 3733 */     traceBuff.append((this.negotiatedChannel == null) ? "N/A" : 
/* 3734 */         Integer.toString(this.negotiatedChannel.getSharingConversations()));
/* 3735 */     traceBuff.append(",hbint=");
/* 3736 */     traceBuff.append((this.negotiatedChannel == null) ? "N/A" : 
/* 3737 */         Integer.toString(this.negotiatedChannel.getHeartbeatInterval()));
/* 3738 */     writeTraceInfo(traceBuff);
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
/*      */   public String toString() {
/* 3750 */     StringBuffer traceBuff = new StringBuffer();
/* 3751 */     getThreadIdentifier(traceBuff);
/*      */     
/* 3753 */     traceBuff.append(",hConns=");
/* 3754 */     traceBuff.append(this.sessions.size());
/* 3755 */     traceBuff.append(",LastDataSend=");
/* 3756 */     traceBuff.append(this.lastDataSend);
/* 3757 */     traceBuff.append(" (");
/* 3758 */     traceBuff.append(System.currentTimeMillis() - this.lastDataSend);
/* 3759 */     traceBuff.append("ms ago),LastDataRecv=");
/* 3760 */     traceBuff.append(this.lastDataRecv);
/* 3761 */     traceBuff.append(" (");
/* 3762 */     traceBuff.append(System.currentTimeMillis() - this.lastDataRecv);
/* 3763 */     traceBuff.append("ms ago)");
/* 3764 */     traceBuff.append(String.format(",Connect Options 0x%x(%s)", new Object[] { Integer.valueOf(this.connectionOptions), MQConstants.decodeOptionsForTrace(this.connectionOptions, "MQCNO_.*") }));
/* 3765 */     traceBuff.append(",remotePID=");
/* 3766 */     traceBuff.append(this.remoteProcessId);
/* 3767 */     traceBuff.append(",remoteTID=");
/* 3768 */     traceBuff.append(this.remoteThreadId);
/* 3769 */     traceBuff.append("]");
/* 3770 */     return traceBuff.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private RfpTSH receiveAsyncTsh() throws JmqiException {
/*      */     RfpTSH rTSH;
/* 3781 */     if (Trace.isOn) {
/* 3782 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "receiveAsyncTsh()");
/*      */     }
/*      */     
/* 3785 */     synchronized (this.asyncTshLock) {
/* 3786 */       while (this.asyncTshQueue.isEmpty() && this.asyncFailure == null) {
/*      */         try {
/* 3788 */           this.asyncTshLock.wait();
/*      */         }
/* 3790 */         catch (InterruptedException e) {
/* 3791 */           if (Trace.isOn) {
/* 3792 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "receiveAsyncTsh()", e);
/*      */           }
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3800 */       if (this.asyncFailure != null) {
/* 3801 */         throwAsyncFailureException();
/*      */       }
/*      */       
/* 3804 */       rTSH = this.asyncTshQueue.removeFirst();
/*      */     } 
/*      */     
/* 3807 */     if (Trace.isOn) {
/* 3808 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "receiveAsyncTsh()", rTSH);
/*      */     }
/* 3810 */     return rTSH;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void throwAsyncFailureException() throws JmqiException {
/* 3816 */     if (this.asyncFailure instanceof JmqiException) {
/*      */       
/* 3818 */       JmqiException traceRet1 = (JmqiException)this.asyncFailure;
/* 3819 */       if (Trace.isOn) {
/* 3820 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "throwAsyncFailureException()", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/* 3823 */       throw traceRet1;
/*      */     } 
/* 3825 */     if (this.asyncFailure instanceof Error) {
/*      */       
/* 3827 */       Error traceRet2 = (Error)this.asyncFailure;
/* 3828 */       if (Trace.isOn) {
/* 3829 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "throwAsyncFailureException()", traceRet2, 2);
/*      */       }
/*      */       
/* 3832 */       throw traceRet2;
/*      */     } 
/*      */ 
/*      */     
/* 3836 */     RuntimeException traceRet3 = new RuntimeException(this.asyncFailure);
/* 3837 */     if (Trace.isOn) {
/* 3838 */       Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "throwAsyncFailureException()", traceRet3, 3);
/*      */     }
/*      */     
/* 3841 */     throw traceRet3;
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
/*      */   void deliverTSH(RfpTSH rTSH) {
/* 3855 */     if (Trace.isOn) {
/* 3856 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "deliverTSH(RfpTSH)", new Object[] { rTSH });
/*      */     }
/*      */ 
/*      */     
/* 3860 */     synchronized (this.asyncTshLock) {
/* 3861 */       this.asyncTshQueue.addLast(rTSH);
/* 3862 */       this.asyncTshLock.notify();
/*      */     } 
/*      */     
/* 3865 */     if (Trace.isOn) {
/* 3866 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "deliverTSH(RfpTSH)");
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
/*      */   @Cruise("This method used to have the signature: asyncFailureNotify()")
/*      */   void asyncConnectionBroken(RemoteTls tls, Throwable nestedException, String qmName, String qmId) throws JmqiException {
/* 3886 */     if (Trace.isOn) {
/* 3887 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "asyncConnectionBroken(RemoteTls,Throwable,String,String)", new Object[] { tls, nestedException, qmName, qmId });
/*      */     }
/*      */     
/* 3890 */     JmqiException je = new JmqiException(this.env, -1, null, 2, 2009, nestedException);
/* 3891 */     synchronized (this.asyncTshLock) {
/*      */ 
/*      */       
/* 3894 */       if (this.connectionBroken) {
/* 3895 */         if (Trace.isOn) {
/* 3896 */           Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "asyncConnectionBroken(RemoteTls,Throwable)", new Object[] { "Previous async failure already reported", this.asyncFailure }, 2);
/*      */         }
/*      */ 
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/* 3903 */       if (Log.isJeeOn()) {
/* 3904 */         Log.logNLS(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "asyncConnectionBroken(RemoteTls,Throwable,String,String)", 
/* 3905 */             String.format("Reconnecting with qmName: %s qmId: %s", new Object[] { qmName, qmId }));
/*      */       }
/* 3907 */       if (qmId == null && qmName == null) {
/*      */         
/* 3909 */         this.asyncFailure = (Throwable)je;
/* 3910 */         this.asyncTshLock.notifyAll();
/*      */       } 
/* 3912 */       this.connectionBroken = true;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3918 */     List<RemoteSession> sessionsSnapshot = new ArrayList<>(this.sessions.size());
/* 3919 */     synchronized (this.sessionsMutex) {
/* 3920 */       if (Trace.isOn) {
/* 3921 */         Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "asyncConnectionBroken(RemoteTls,Throwable,String,String)", "Got lock on sessionsMutex", this.sessionsMutex);
/*      */       }
/*      */       
/* 3924 */       for (RemoteSession session : this.sessions.values()) {
/* 3925 */         sessionsSnapshot.add(session);
/*      */       }
/* 3927 */       if (Trace.isOn) {
/* 3928 */         Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "asyncConnectionBroken(RemoteTls,Throwable,String,String)", "sessionsMutex released", this.sessionsMutex);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 3933 */     if (Trace.isOn) {
/* 3934 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "asyncConnectionBroken(RemoteTls,Throwable,String,String)", "processing unparented hconns", null);
/*      */     }
/*      */ 
/*      */     
/* 3938 */     for (RemoteSession session : sessionsSnapshot) {
/* 3939 */       if (Trace.isOn) {
/* 3940 */         Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "asyncConnectionBroken(RemoteTls,Throwable,String,String)", "processing session", session);
/*      */       }
/*      */ 
/*      */       
/* 3944 */       session.deliverConnectionBroken((Throwable)je, qmName, qmId);
/*      */     } 
/*      */     
/* 3947 */     if (qmId == null && qmName == null) {
/*      */       
/* 3949 */       if (Trace.isOn) {
/* 3950 */         Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "asyncConnectionBroken(RemoteTls,Throwable,String,String)", "removing ourself from the connectionSpec", null);
/*      */       }
/*      */       
/* 3953 */       removeSelf();
/*      */ 
/*      */ 
/*      */       
/* 3957 */       this.connected = false;
/*      */       
/* 3959 */       if (!isReconnectable()) {
/*      */         
/* 3961 */         if (Trace.isOn) {
/* 3962 */           Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "asyncConnectionBroken(RemoteTls,Throwable,String,String)", "disconnecting", null);
/*      */         }
/*      */         
/* 3965 */         disconnect(tls);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3973 */     if (Trace.isOn)
/*      */     {
/*      */       
/* 3976 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "asyncConnectionBroken(RemoteTls,Throwable,String,String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeSpec() {
/* 3986 */     if (Trace.isOn) {
/* 3987 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "removeSpec()");
/*      */     }
/*      */ 
/*      */     
/* 3991 */     this.remoteConnectionSpec.removeSelf();
/*      */     
/* 3993 */     if (Trace.isOn) {
/* 3994 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "removeSpec()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeSelf() {
/* 4004 */     if (Trace.isOn) {
/* 4005 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "removeSelf()");
/*      */     }
/*      */ 
/*      */     
/* 4009 */     this.remoteConnectionSpec.removeConnection(this);
/*      */     
/* 4011 */     if (Trace.isOn) {
/* 4012 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "removeSelf()");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   Throwable getAsyncFailure() {
/* 4018 */     synchronized (this.asyncTshLock) {
/* 4019 */       if (Trace.isOn) {
/* 4020 */         Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "getAsyncFailure()", "getter", this.asyncFailure);
/*      */       }
/*      */       
/* 4023 */       return this.asyncFailure;
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
/*      */   void qmQuiescing() throws JmqiException {
/* 4035 */     if (Trace.isOn) {
/* 4036 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "qmQuiescing()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 4041 */     synchronized (this.sessionsMutex) {
/* 4042 */       for (RemoteSession session : this.sessions.values()) {
/* 4043 */         session.qmQuiescing();
/*      */       }
/*      */     } 
/*      */     
/* 4047 */     if (Trace.isOn) {
/* 4048 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "qmQuiescing()");
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
/*      */   void chlQuiescing() throws JmqiException {
/* 4060 */     if (Trace.isOn) {
/* 4061 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "chlQuiescing()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 4066 */     synchronized (this.sessionsMutex) {
/* 4067 */       for (RemoteSession session : this.sessions.values()) {
/* 4068 */         session.chlQuiescing();
/*      */       }
/*      */     } 
/*      */     
/* 4072 */     if (Trace.isOn) {
/* 4073 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "chlQuiescing()");
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
/*      */   public void sendHeartbeat(RemoteTls tls, int heartBeatType) throws JmqiException {
/* 4091 */     if (Trace.isOn) {
/* 4092 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "sendHeartbeat(RemoteTls,int)", new Object[] { tls, 
/* 4093 */             Integer.valueOf(heartBeatType) });
/*      */     }
/* 4095 */     RfpTSH hbTSH = allocateTSH(isMultiplexingEnabled() ? 1 : 0, 9, null);
/*      */ 
/*      */     
/* 4098 */     hbTSH.setTransLength(hbTSH.hdrSize());
/* 4099 */     if (this.fapLevel >= 10 || !isMultiplexingEnabled()) {
/*      */ 
/*      */       
/* 4102 */       switch (heartBeatType) {
/*      */         case 1:
/* 4104 */           hbTSH.setControlFlags1(hbTSH.getControlFlags1() | 0x1);
/*      */           break;
/*      */         
/*      */         case 2:
/* 4108 */           hbTSH.setControlFlags1(hbTSH.getControlFlags1() & 0xFFFFFFFE);
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     } else {
/* 4116 */       switch (heartBeatType) {
/*      */         
/*      */         case 1:
/* 4119 */           hbTSH.setControlFlags1(hbTSH.getControlFlags1() & 0xFFFFFFFE);
/*      */           break;
/*      */         case 2:
/* 4122 */           hbTSH.setControlFlags1(hbTSH.getControlFlags1() | 0x1);
/*      */           break;
/*      */       } 
/*      */ 
/*      */     
/*      */     } 
/* 4128 */     sendTSH(tls, hbTSH, null);
/*      */     
/* 4130 */     if (Trace.isOn) {
/* 4131 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "sendHeartbeat(RemoteTls,int)");
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
/*      */   public MQCD getClientConn() {
/* 4144 */     if (Trace.isOn) {
/* 4145 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "getClientConn()", "getter", this.clientConn);
/*      */     }
/*      */     
/* 4148 */     return this.clientConn;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isDistListCapable() {
/* 4157 */     boolean traceRet1 = ((this.flags2 & 0x1) != 0);
/*      */     
/* 4159 */     if (Trace.isOn) {
/* 4160 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "isDistListCapable()", "getter", 
/* 4161 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 4163 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSPISupported() {
/* 4172 */     boolean traceRet1 = ((this.flags2 & 0x40) != 0);
/* 4173 */     if (Trace.isOn) {
/* 4174 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "isSPISupported()", "getter", 
/* 4175 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 4177 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isMultiplexSyncGetCapable() {
/* 4186 */     boolean traceRet1 = ((this.flags3 & 0x8) != 0);
/*      */     
/* 4188 */     if (Trace.isOn) {
/* 4189 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "isMultiplexSyncGetCapable()", "getter", 
/* 4190 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 4192 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isGenerateConntagCapable() {
/* 4201 */     boolean traceRet1 = ((this.flags3 & 0x20) != 0);
/*      */     
/* 4203 */     if (Trace.isOn) {
/* 4204 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "isGenerateConntagCapable()", "getter", 
/* 4205 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 4207 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getFapLevel() {
/* 4217 */     if (Trace.isOn) {
/* 4218 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "getFapLevel()", "getter", 
/* 4219 */           Integer.valueOf(this.fapLevel));
/*      */     }
/* 4221 */     return this.fapLevel;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   long getMaximumMessageLength() {
/* 4230 */     long traceRet1 = this.negotiatedChannel.getMaxMsgLength();
/*      */     
/* 4232 */     if (Trace.isOn) {
/* 4233 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "getMaximumMessageLength()", "getter", 
/* 4234 */           Long.valueOf(traceRet1));
/*      */     }
/* 4236 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getRemoteQmgrName() {
/* 4245 */     String traceRet1 = this.negotiatedChannel.getQMgrName();
/*      */     
/* 4247 */     if (Trace.isOn) {
/* 4248 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "getRemoteQmgrName()", "getter", traceRet1);
/*      */     }
/*      */     
/* 4251 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCurHdrCompression() {
/* 4261 */     if (Trace.isOn) {
/* 4262 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "getCurHdrCompression()", "getter", 
/* 4263 */           Integer.valueOf(this.curHdrCompression));
/*      */     }
/* 4265 */     return this.curHdrCompression;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCurMsgCompression() {
/* 4275 */     if (Trace.isOn) {
/* 4276 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "getCurMsgCompression()", "getter", 
/* 4277 */           Integer.valueOf(this.curMsgCompression));
/*      */     }
/* 4279 */     return this.curMsgCompression;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQCD getNegotiatedChannel() {
/* 4290 */     if (Trace.isOn) {
/* 4291 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "getNegotiatedChannel()", "getter", this.negotiatedChannel);
/*      */     }
/*      */     
/* 4294 */     return this.negotiatedChannel;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxTransmissionSize() {
/* 4304 */     if (Trace.isOn) {
/* 4305 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "getMaxTransmissionSize()", "getter", 
/* 4306 */           Integer.valueOf(this.maxTransmissionSize));
/*      */     }
/* 4308 */     return this.maxTransmissionSize;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   String getRemoteQMID() {
/* 4318 */     if (Trace.isOn) {
/* 4319 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "getRemoteQMID()", "getter", this.remoteQMID);
/*      */     }
/*      */     
/* 4322 */     return this.remoteQMID;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getRemoteMQEncoding() {
/* 4332 */     if (Trace.isOn) {
/* 4333 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "getRemoteMQEncoding()", "getter", 
/* 4334 */           Integer.valueOf(this.remoteMQEncoding));
/*      */     }
/* 4336 */     return this.remoteMQEncoding;
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
/*      */   RemoteTagPool getIdTagPool() throws JmqiException {
/* 4348 */     if (this.remoteQMID == null) {
/* 4349 */       HashMap<String, Object> info1 = new HashMap<>();
/* 4350 */       info1.put("Description", "Unexpected state: QMID not yet resolved.");
/* 4351 */       Trace.ffst(this, "getIdTagPool()", "01", info1, null);
/* 4352 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*      */ 
/*      */ 
/*      */       
/* 4356 */       if (Trace.isOn) {
/* 4357 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "getIdTagPool()", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 4360 */       throw traceRet1;
/*      */     } 
/*      */     
/* 4363 */     if (this.idTagPool == null) {
/* 4364 */       this.idTagPool = RemoteTagPool.getInstance(this.env, 24, this.remoteQMID);
/*      */     }
/*      */     
/* 4367 */     if (Trace.isOn) {
/* 4368 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "getIdTagPool()", "getter", this.idTagPool);
/*      */     }
/*      */     
/* 4371 */     return this.idTagPool;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getSecureKeyResetCount() {
/* 4381 */     if (Trace.isOn) {
/* 4382 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "getSecureKeyResetCount()", "getter", 
/* 4383 */           Integer.valueOf(this.secureKeyResetCount));
/*      */     }
/* 4385 */     return this.secureKeyResetCount;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getBytesSinceKeyReset() {
/* 4394 */     synchronized (this.sslResetCountSync) {
/*      */       
/* 4396 */       if (Trace.isOn) {
/* 4397 */         Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "getBytesSinceKeyReset()", "getter", 
/* 4398 */             Integer.valueOf(this.bytesSinceKeyReset));
/*      */       }
/* 4400 */       return this.bytesSinceKeyReset;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setBytesSinceKeyReset(int bytesSinceKeyReset) {
/* 4411 */     if (Trace.isOn) {
/* 4412 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "setBytesSinceKeyReset(int)", "setter", 
/* 4413 */           Integer.valueOf(bytesSinceKeyReset));
/*      */     }
/*      */     
/* 4416 */     synchronized (this.sslResetCountSync) {
/* 4417 */       this.bytesSinceKeyReset = bytesSinceKeyReset;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setHeartbeatKeyResetRequired(boolean heartbeatKeyResetRequired) {
/* 4428 */     if (Trace.isOn) {
/* 4429 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "setHeartbeatKeyResetRequired(boolean)", "setter", 
/*      */           
/* 4431 */           Boolean.valueOf(heartbeatKeyResetRequired));
/*      */     }
/*      */     
/* 4434 */     this.heartbeatKeyResetRequired = heartbeatKeyResetRequired;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isMultiplexingEnabled() {
/* 4444 */     return this.multiplexingEnabled;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   RemoteSession getSessionByConvId(int convId) {
/*      */     RemoteSession session;
/* 4456 */     if (Trace.isOn) {
/* 4457 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "getSessionByConvId(int)", new Object[] {
/* 4458 */             Integer.valueOf(convId)
/*      */           });
/*      */     }
/* 4461 */     synchronized (this.sessionsMutex) {
/* 4462 */       session = this.sessions.get(Integer.valueOf(convId));
/*      */     } 
/*      */     
/* 4465 */     if (Trace.isOn) {
/* 4466 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "getSessionByConvId(int)", session);
/*      */     }
/*      */     
/* 4469 */     return session;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setNegotiatedChannel(MQCD negotiatedChannel) {
/* 4479 */     if (Trace.isOn) {
/* 4480 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "setNegotiatedChannel(MQCD)", "setter", negotiatedChannel);
/*      */     }
/*      */     
/* 4483 */     this.negotiatedChannel = negotiatedChannel;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RemoteFAP getRemoteFap() {
/* 4492 */     if (Trace.isOn) {
/* 4493 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "getRemoteFap()", "getter", this.fap);
/*      */     }
/*      */     
/* 4496 */     return this.fap;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void checkAndSetCcsidOverride(int ccsidOverride) {
/* 4506 */     if (Trace.isOn) {
/* 4507 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "checkAndSetCcsidOverride(int)", new Object[] {
/* 4508 */             Integer.valueOf(ccsidOverride)
/*      */           });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4515 */     if (ccsidOverride > 0) {
/*      */       
/*      */       try {
/* 4518 */         JmqiCodepage newCodepage = JmqiCodepage.getJmqiCodepage(this.env, ccsidOverride);
/*      */ 
/*      */         
/* 4521 */         if (newCodepage == null) {
/* 4522 */           UnsupportedEncodingException traceRet1 = new UnsupportedEncodingException(Integer.toString(ccsidOverride));
/* 4523 */           if (Trace.isOn) {
/* 4524 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "checkAndSetCcsidOverride(int)", traceRet1);
/*      */           }
/*      */           
/* 4527 */           throw traceRet1;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 4532 */         this.localCcsid = ccsidOverride;
/* 4533 */         this.codepage = newCodepage;
/*      */       }
/* 4535 */       catch (UnsupportedEncodingException e) {
/* 4536 */         if (Trace.isOn) {
/* 4537 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "checkAndSetCcsidOverride(int)", e);
/*      */         }
/*      */ 
/*      */         
/* 4541 */         if (Trace.isOn) {
/* 4542 */           Trace.data(this, "checkAndSetCcsidOverride(int)", "Unable to determine Java codepage for CCSID override " + ccsidOverride);
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 4548 */     if (Trace.isOn) {
/* 4549 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "checkAndSetCcsidOverride(int)");
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
/*      */   JmqiCodepage getCp() {
/* 4562 */     return this.codepage;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isSwap() {
/* 4572 */     return this.swap;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setSwap(boolean swap) {
/* 4583 */     this.swap = swap;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getChannelName() {
/* 4592 */     String retval = this.channelName;
/* 4593 */     if (this.channelName == null && this.negotiatedChannel != null)
/*      */     {
/* 4595 */       this.channelName = this.negotiatedChannel.getChannelName();
/*      */     }
/* 4597 */     if (Trace.isOn) {
/* 4598 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "getChannelName()", "getter", retval);
/*      */     }
/*      */     
/* 4601 */     return retval;
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
/*      */   public void analyseErrorSegment(RfpTSH rTSH) throws JmqiException {
/*      */     int exceptionNumber, exceptionReason;
/* 4622 */     if (Trace.isOn) {
/* 4623 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "analyseErrorSegment(RfpTSH)", new Object[] { rTSH });
/*      */     }
/*      */     
/* 4626 */     int eshRetCode = -1;
/* 4627 */     int tshSize = rTSH.hdrSize();
/* 4628 */     if (rTSH.getTransLength() >= tshSize + 8) {
/* 4629 */       RfpESH esh = (RfpESH)RfpStructure.newRfp(this.env, 21, rTSH
/* 4630 */           .getRfpBuffer(), rTSH
/* 4631 */           .getRfpOffset() + tshSize);
/* 4632 */       eshRetCode = esh.getReturnCode((rTSH.getEncoding() == 2));
/*      */     } 
/*      */ 
/*      */     
/* 4636 */     if (Trace.isOn) {
/* 4637 */       Trace.data(this, "analyseErrorSegment(RfpTSH)", "eshRetcode=", 
/* 4638 */           Integer.valueOf(eshRetCode));
/*      */     }
/*      */ 
/*      */     
/* 4642 */     switch (eshRetCode) {
/*      */       case 2:
/* 4644 */         exceptionNumber = 9547;
/* 4645 */         exceptionReason = 2539;
/*      */         break;
/*      */       case 23:
/* 4648 */         exceptionNumber = 9496;
/* 4649 */         exceptionReason = 2537;
/*      */         break;
/*      */       case 22:
/* 4652 */         exceptionNumber = 9558;
/* 4653 */         exceptionReason = 2537;
/*      */         break;
/*      */       case 1:
/* 4656 */         exceptionNumber = 9520;
/* 4657 */         exceptionReason = 2540;
/*      */         break;
/*      */       case 3:
/* 4660 */         exceptionNumber = 9524;
/* 4661 */         exceptionReason = this.connected ? 2009 : 2059;
/*      */         break;
/*      */ 
/*      */       
/*      */       case 5:
/* 4666 */         exceptionNumber = 9525;
/* 4667 */         exceptionReason = this.connected ? 2009 : 2059;
/*      */         break;
/*      */ 
/*      */       
/*      */       case 7:
/* 4672 */         exceptionNumber = 9528;
/* 4673 */         exceptionReason = this.connected ? 2009 : 2059;
/*      */         break;
/*      */ 
/*      */       
/*      */       case 24:
/* 4678 */         exceptionNumber = 9641;
/* 4679 */         exceptionReason = 2397;
/*      */         break;
/*      */       case 25:
/* 4682 */         exceptionNumber = 9643;
/* 4683 */         exceptionReason = 2397;
/*      */         break;
/*      */       case 28:
/* 4686 */         exceptionNumber = 9714;
/* 4687 */         exceptionReason = 2397;
/*      */         break;
/*      */       case 32:
/* 4690 */         exceptionNumber = 9487;
/* 4691 */         exceptionReason = 2543;
/*      */         break;
/*      */       case 37:
/* 4694 */         exceptionNumber = 9694;
/* 4695 */         exceptionReason = 2025;
/*      */         break;
/*      */       case 10:
/*      */       case 11:
/*      */       case 12:
/*      */       case 13:
/*      */       case 14:
/*      */       case 16:
/*      */       case 18:
/* 4704 */         exceptionNumber = 9523;
/* 4705 */         exceptionReason = 2539;
/*      */         break;
/*      */       default:
/* 4708 */         if (this.connected) {
/* 4709 */           exceptionNumber = 9999;
/* 4710 */           exceptionReason = 2009;
/*      */           break;
/*      */         } 
/* 4713 */         exceptionNumber = 9503;
/* 4714 */         exceptionReason = 2059;
/*      */         break;
/*      */     } 
/*      */ 
/*      */     
/* 4719 */     JmqiException traceRet1 = new JmqiException(this.env, exceptionNumber, new String[] { null, null, getChannelName() }, 2, exceptionReason, null);
/*      */ 
/*      */     
/* 4722 */     if (Trace.isOn) {
/* 4723 */       Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "analyseErrorSegment(RfpTSH)", (Throwable)traceRet1);
/*      */     }
/*      */     
/* 4726 */     throw traceRet1;
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
/*      */   public void completeClose(RemoteTls tls, RemoteSession remoteSession) throws JmqiException {
/* 4738 */     if (Trace.isOn) {
/* 4739 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "completeClose(RemoteTls,RemoteSession)", new Object[] { tls, remoteSession });
/*      */     }
/*      */ 
/*      */     
/* 4743 */     remoteSession.disconnect(tls);
/*      */     
/* 4745 */     if (Trace.isOn) {
/* 4746 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "completeClose(RemoteTls,RemoteSession)");
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
/*      */   private static class SessionsMutex
/*      */   {
/*      */     SessionsMutex() {
/* 4762 */       if (Trace.isOn) {
/* 4763 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.SessionsMutex", "<init>()");
/*      */       }
/* 4765 */       if (Trace.isOn) {
/* 4766 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.SessionsMutex", "<init>()");
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
/*      */ 
/*      */   
/*      */   private static class SslResetCountSync
/*      */   {
/*      */     SslResetCountSync() {
/* 4782 */       if (Trace.isOn) {
/* 4783 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.SslResetCountSync", "<init>()");
/*      */       }
/* 4785 */       if (Trace.isOn) {
/* 4786 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.SslResetCountSync", "<init>()");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static class SendMutex
/*      */     extends TraceableReentrantLock
/*      */   {
/*      */     private static final long serialVersionUID = -4541134927816199590L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     SendMutex() {
/* 4805 */       if (Trace.isOn) {
/* 4806 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.SendMutex", "<init>()");
/*      */       }
/* 4808 */       if (Trace.isOn) {
/* 4809 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.SendMutex", "<init>()");
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
/*      */   private static class RcvThreadSendMutex
/*      */     extends TraceableReentrantLock
/*      */   {
/*      */     private static final long serialVersionUID = 214479400117212891L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     RcvThreadSendMutex() {
/* 4831 */       if (Trace.isOn) {
/* 4832 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RcvThreadSendMutex", "<init>()");
/*      */       }
/* 4834 */       if (Trace.isOn) {
/* 4835 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RcvThreadSendMutex", "<init>()");
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
/*      */ 
/*      */   
/*      */   private static class KeyResetSemaphore
/*      */   {
/*      */     KeyResetSemaphore() {
/* 4851 */       if (Trace.isOn) {
/* 4852 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.KeyResetSemaphore", "<init>()");
/*      */       }
/* 4854 */       if (Trace.isOn) {
/* 4855 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.KeyResetSemaphore", "<init>()");
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
/*      */ 
/*      */   
/*      */   private static class AsyncTshLock
/*      */   {
/*      */     AsyncTshLock() {
/* 4871 */       if (Trace.isOn) {
/* 4872 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.AsyncTshLock", "<init>()");
/*      */       }
/* 4874 */       if (Trace.isOn) {
/* 4875 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.AsyncTshLock", "<init>()");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getRemoteEncoding() {
/* 4885 */     if (Trace.isOn) {
/* 4886 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "getRemoteEncoding()", "getter", 
/* 4887 */           Integer.valueOf(this.remoteEncoding));
/*      */     }
/* 4889 */     return this.remoteEncoding;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setRemoteEncoding(int remoteEncoding) {
/* 4896 */     if (Trace.isOn) {
/* 4897 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "setRemoteEncoding(int)", "setter", 
/* 4898 */           Integer.valueOf(remoteEncoding));
/*      */     }
/* 4900 */     this.remoteEncoding = remoteEncoding;
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
/*      */   void notifyReconnect(RemoteTls tls, boolean reconnect, String qmName, String qmId) {
/* 4917 */     if (Trace.isOn) {
/* 4918 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "notifyReconnect(RemoteTls,boolean,String,String)", new Object[] { tls, 
/* 4919 */             Boolean.valueOf(reconnect), qmName, qmId });
/*      */     }
/* 4921 */     if (reconnect) {
/*      */       
/*      */       try {
/* 4924 */         this.reconnectRequested = true;
/* 4925 */         asyncConnectionBroken(tls, null, qmName, qmId);
/*      */       }
/* 4927 */       catch (JmqiException e1) {
/* 4928 */         if (Trace.isOn) {
/* 4929 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "notifyReconnect(RemoteTls,boolean,String,String)", (Throwable)e1, 1);
/*      */         
/*      */         }
/*      */       
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 4938 */       synchronized (this.sessionsMutex) {
/* 4939 */         for (RemoteSession session : this.sessions.values()) {
/* 4940 */           session.disableReconnect();
/*      */         }
/*      */       } 
/*      */       
/* 4944 */       JmqiException je = new JmqiException(this.env, 9524, new String[] { null, null, getChannelName() }, 2, 2009, null);
/*      */       
/*      */       try {
/* 4947 */         asyncConnectionBroken(tls, (Throwable)je, null, null);
/*      */       }
/* 4949 */       catch (JmqiException e) {
/* 4950 */         if (Trace.isOn) {
/* 4951 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "notifyReconnect(RemoteTls,boolean,String,String)", (Throwable)e, 2);
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4959 */     if (Trace.isOn) {
/* 4960 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "notifyReconnect(RemoteTls,boolean,String,String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isReconnectRequested() {
/* 4970 */     if (Trace.isOn) {
/* 4971 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "isReconnectRequested()", "getter", 
/* 4972 */           Boolean.valueOf(this.reconnectRequested));
/*      */     }
/* 4974 */     return this.reconnectRequested;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getRemoteProductId() {
/* 4981 */     if (Trace.isOn) {
/* 4982 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "getRemoteProductId()", "getter", this.remoteProductId);
/*      */     }
/*      */     
/* 4985 */     return this.remoteProductId;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsReconnection() {
/* 4993 */     if (Trace.isOn) {
/* 4994 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "supportsReconnection()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5000 */     boolean supportsReconnection = false;
/* 5001 */     if (this.fapLevel >= 10 && this.multiplexingEnabled) {
/* 5002 */       supportsReconnection = true;
/*      */     }
/*      */     
/* 5005 */     if (Trace.isOn) {
/* 5006 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "supportsReconnection()", 
/* 5007 */           Boolean.valueOf(supportsReconnection));
/*      */     }
/* 5009 */     return supportsReconnection;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isConnected() {
/* 5016 */     if (Trace.isOn) {
/* 5017 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "isConnected()", "getter", 
/* 5018 */           Boolean.valueOf(this.connected));
/*      */     }
/* 5020 */     return this.connected;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setKeyReset(int keyReset) {
/* 5027 */     if (Trace.isOn) {
/* 5028 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "setKeyReset(int)", "setter", 
/* 5029 */           Integer.valueOf(keyReset));
/*      */     }
/* 5031 */     this.keyReset = keyReset;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getKeyReset() {
/* 5038 */     if (Trace.isOn) {
/* 5039 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "getKeyReset()", "getter", 
/* 5040 */           Integer.valueOf(this.keyReset));
/*      */     }
/* 5042 */     return this.keyReset;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void notifyKeyReset() {
/* 5049 */     if (Trace.isOn) {
/* 5050 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "notifyKeyReset()");
/*      */     }
/* 5052 */     synchronized (this.keyResetSemaphore) {
/* 5053 */       this.keyResetComplete = true;
/* 5054 */       this.keyResetSemaphore.notify();
/*      */     } 
/*      */     
/* 5057 */     if (Trace.isOn) {
/* 5058 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "notifyKeyReset()");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   int getFreeConversations() {
/* 5064 */     int traceRet1 = 0;
/* 5065 */     synchronized (this.sessionsMutex) {
/* 5066 */       traceRet1 = this.negotiatedChannel.getSharingConversations() - this.sessions.size();
/*      */     } 
/*      */     
/* 5069 */     if (Trace.isOn) {
/* 5070 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "getFreeConversations()", "getter", 
/* 5071 */           Integer.valueOf(traceRet1));
/*      */     }
/* 5073 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void dump(PrintWriter pw, int level) {
/* 5081 */     if (Trace.isOn) {
/* 5082 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "dump(PrintWriter,int)", new Object[] { pw, 
/* 5083 */             Integer.valueOf(level) });
/*      */     }
/* 5085 */     String prefix = Trace.buildPrefix(level);
/* 5086 */     pw.format("%s%s%n", new Object[] { prefix, toString() });
/* 5087 */     this.sendMutex.dump("sendMutex", pw, level + 1);
/* 5088 */     this.rcvThreadSendMutex.dump("rcvThreadSendMutex", pw, level + 1);
/* 5089 */     if (isMultiplexed()) {
/* 5090 */       this.rcvThread.dump(pw, level + 1);
/* 5091 */       pw.println();
/*      */     } 
/* 5093 */     synchronized (this.sessionsMutex) {
/* 5094 */       for (RemoteSession session : this.sessions.values()) {
/* 5095 */         session.dump(pw, level + 1);
/* 5096 */         pw.println();
/*      */       } 
/*      */     } 
/* 5099 */     if (Trace.isOn) {
/* 5100 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "dump(PrintWriter,int)");
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
/*      */   protected static byte[] sanitizeBuffer(byte[] buffer, int bufferSize, int passwordOffset) {
/*      */     byte[] traceBuffer;
/* 5113 */     if (Trace.isOn) {
/* 5114 */       Trace.entry("com.ibm.mq.jmqi.remote.impl.RemoteConnection", "sanitizeBuffer(byte [ ],int,int)", new Object[] { buffer, 
/* 5115 */             Integer.valueOf(bufferSize), 
/* 5116 */             Integer.valueOf(passwordOffset) });
/*      */     }
/*      */     
/* 5119 */     if (passwordOffset == -1) {
/* 5120 */       traceBuffer = buffer;
/*      */     } else {
/*      */       
/* 5123 */       traceBuffer = Arrays.copyOf(buffer, bufferSize);
/*      */       
/* 5125 */       int passwordLimit = Math.min(passwordOffset + 12, bufferSize);
/* 5126 */       for (int pos = passwordOffset; pos < passwordLimit; pos++) {
/* 5127 */         traceBuffer[pos] = 42;
/*      */       }
/*      */     } 
/* 5130 */     if (Trace.isOn) {
/* 5131 */       Trace.exit("com.ibm.mq.jmqi.remote.impl.RemoteConnection", "sanitizeBuffer(byte [ ],int,int)", traceBuffer);
/*      */     }
/*      */     
/* 5134 */     return traceBuffer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Iterable<RemoteConnection> getAllConnections() {
/* 5141 */     if (Trace.isOn) {
/* 5142 */       Trace.data("com.ibm.mq.jmqi.remote.impl.RemoteConnection", "getAllConnections()", "getter", allConnections);
/*      */     }
/*      */     
/* 5145 */     return (Iterable<RemoteConnection>)allConnections;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void invalidate(int reason) {
/* 5152 */     if (Trace.isOn)
/* 5153 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "invalidate(int)", new Object[] {
/* 5154 */             Integer.valueOf(reason)
/*      */           }); 
/* 5156 */     this.asyncFailure = (Throwable)new JmqiException(this.env, 9213, null, 2, reason, null);
/* 5157 */     removeSpec();
/* 5158 */     if (Trace.isOn) {
/* 5159 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "invalidate(int)");
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
/*      */   protected QueueManagerInfo getQMgrInfo(Hconn hconn) throws JmqiException {
/* 5176 */     if (Trace.isOn) {
/* 5177 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "getQMgrInfo(RemoteHconn)", new Object[] { hconn });
/*      */     }
/* 5179 */     synchronized (this.QueueManagerInfoLock) {
/* 5180 */       if (this.info == null) {
/* 5181 */         if (hconn != null) {
/* 5182 */           this.info = JmqiTools.getQueueManagerInfo(this.env, (JmqiMQ)getRemoteFap(), hconn);
/*      */         } else {
/*      */           
/* 5185 */           JmqiException e = new JmqiException(this.env, -1, null, 2, 2009, null);
/*      */ 
/*      */           
/* 5188 */           if (Trace.isOn) {
/* 5189 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "getQMgrInfo(RemoteHconn)", (Throwable)e);
/*      */           }
/* 5191 */           throw e;
/*      */         } 
/*      */       }
/*      */     } 
/* 5195 */     if (Trace.isOn) {
/* 5196 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnection", "getQMgrInfo(RemoteHconn)", this.info);
/*      */     }
/* 5198 */     return this.info;
/*      */   }
/*      */ 
/*      */   
/*      */   public TraceableReentrantLock getSessionLock() {
/* 5203 */     if (Trace.isOn) {
/* 5204 */       Trace.data(this, "getSesssionLock()", "getter", this.sessionLock);
/*      */     }
/* 5206 */     return this.sessionLock;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isReconnectable() {
/* 5213 */     if (Trace.isOn) {
/* 5214 */       Trace.data(this, "isReconnectable()", "getter", Boolean.valueOf(this.reconnectable));
/*      */     }
/* 5216 */     return this.reconnectable;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setReconnectable(boolean reconnectable) {
/* 5223 */     if (Trace.isOn) {
/* 5224 */       Trace.data(this, "setReconnectable()", "setter", Boolean.valueOf(reconnectable));
/*      */     }
/* 5226 */     this.reconnectable = reconnectable;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setGloballyShareable(boolean globallyShareable) {
/* 5234 */     this.globallyShareable = globallyShareable;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setSessionBeingCreated(boolean sessionBeingCreated) {
/* 5239 */     if (Trace.isOn) {
/* 5240 */       Trace.data(this, "setSessionBeingCreated(boolean)", "setter", Boolean.valueOf(sessionBeingCreated));
/*      */     }
/* 5242 */     this.sessionBeingCreated = sessionBeingCreated;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getSessionBeingCreated() {
/* 5248 */     if (Trace.isOn) {
/* 5249 */       Trace.data(this, "getSessionBeingCreated()", "getter", Boolean.valueOf(this.sessionBeingCreated));
/*      */     }
/* 5251 */     return this.sessionBeingCreated;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getReconnectCycle() {
/* 5257 */     if (Trace.isOn) {
/* 5258 */       Trace.data(this, "getReconnectCycle()", "getter", Integer.valueOf(this.reconnectCycle));
/*      */     }
/* 5260 */     return this.reconnectCycle;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getTraceIdentifier() {
/* 5265 */     if (Trace.isOn) {
/* 5266 */       Trace.data(this, "getTraceIdentifier()", "getter", Integer.valueOf(this.traceIdentifier));
/*      */     }
/* 5268 */     return this.traceIdentifier;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getRemoteTraceIdentifier() {
/* 5273 */     if (Trace.isOn) {
/* 5274 */       Trace.data(this, "getRemoteTraceIdentifier()", "getter", Integer.valueOf(this.remoteTraceIdentifier));
/*      */     }
/* 5276 */     return this.remoteTraceIdentifier;
/*      */   }
/*      */   
/*      */   boolean isRebalancedByResourceAdapter() {
/* 5280 */     if (Trace.isOn) {
/* 5281 */       Trace.data(this, "isRebalancedByResourceAdapter()", "getter", Boolean.valueOf(this.rebalancedByResourceAdapter));
/*      */     }
/* 5283 */     return this.rebalancedByResourceAdapter;
/*      */   }
/*      */   
/*      */   void setRebalancedByResourceAdapter(boolean rebalancedByResourceAdapter) {
/* 5287 */     if (Trace.isOn) {
/* 5288 */       Trace.data(this, "setRebalancedByResourceAdapter(boolean)", "setter", Boolean.valueOf(rebalancedByResourceAdapter));
/*      */     }
/* 5290 */     this.rebalancedByResourceAdapter = rebalancedByResourceAdapter;
/*      */   }
/*      */   
/*      */   abstract void protocolConnect() throws JmqiException;
/*      */   
/*      */   abstract void protocolDisconnect();
/*      */   
/*      */   abstract void protocolTerminate();
/*      */   
/*      */   protected abstract void protocolSecureKeyReset() throws JmqiException;
/*      */   
/*      */   protected abstract boolean protocolSupportsAsyncMode() throws JmqiException;
/*      */   
/*      */   protected abstract void protocolSetupAsyncMode() throws JmqiException;
/*      */   
/*      */   protected abstract void protocolSetHeartbeatInterval(int paramInt) throws JmqiException;
/*      */   
/*      */   protected abstract void send(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) throws JmqiException;
/*      */   
/*      */   public abstract int receive(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws JmqiException;
/*      */   
/*      */   public abstract boolean isSecure();
/*      */   
/*      */   public abstract String getRemoteCertIssuerDN();
/*      */   
/*      */   public abstract void writeTraceInfo(StringBuffer paramStringBuffer);
/*      */   
/*      */   abstract String getRemoteHostDescr();
/*      */   
/*      */   abstract boolean isEncrypted();
/*      */   
/*      */   abstract String getTrpType();
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\impl\RemoteConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */