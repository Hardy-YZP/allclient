/*      */ package com.ibm.mq.jmqi.remote.impl;
/*      */ 
/*      */ import com.ibm.mq.constants.CMQC;
/*      */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*      */ import com.ibm.mq.jmqi.JmqiException;
/*      */ import com.ibm.mq.jmqi.JmqiObject;
/*      */ import com.ibm.mq.jmqi.JmqiUtils;
/*      */ import com.ibm.mq.jmqi.MQCBC;
/*      */ import com.ibm.mq.jmqi.MQCBD;
/*      */ import com.ibm.mq.jmqi.MQConsumer;
/*      */ import com.ibm.mq.jmqi.MQGMO;
/*      */ import com.ibm.mq.jmqi.MQMD;
/*      */ import com.ibm.mq.jmqi.handles.Hconn;
/*      */ import com.ibm.mq.jmqi.handles.Hobj;
/*      */ import com.ibm.mq.jmqi.handles.Pint;
/*      */ import com.ibm.mq.jmqi.internal.Configuration;
/*      */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*      */ import com.ibm.mq.jmqi.internal.JmqiTools;
/*      */ import com.ibm.mq.jmqi.remote.api.RemoteFAP;
/*      */ import com.ibm.mq.jmqi.remote.api.RemoteHconn;
/*      */ import com.ibm.mq.jmqi.remote.api.RemoteHobj;
/*      */ import com.ibm.mq.jmqi.remote.api.RemoteParentHconn;
/*      */ import com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE;
/*      */ import com.ibm.mq.jmqi.remote.rfp.RfpMPH;
/*      */ import com.ibm.mq.jmqi.remote.rfp.RfpMQAPI;
/*      */ import com.ibm.mq.jmqi.remote.rfp.RfpMQCLOSE;
/*      */ import com.ibm.mq.jmqi.remote.rfp.RfpNOTIFICATION;
/*      */ import com.ibm.mq.jmqi.remote.rfp.RfpREQUEST_MSGS;
/*      */ import com.ibm.mq.jmqi.remote.rfp.RfpStructure;
/*      */ import com.ibm.mq.jmqi.remote.rfp.RfpTSH;
/*      */ import com.ibm.mq.jmqi.remote.util.HconnLock;
/*      */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*      */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*      */ import com.ibm.mq.jmqi.system.JmqiTls;
/*      */ import com.ibm.mq.jmqi.system.SpiGetOptions;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.io.PrintWriter;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.util.Arrays;
/*      */ import java.util.EnumSet;
/*      */ import java.util.HashMap;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class RemoteProxyQueue
/*      */   extends JmqiObject
/*      */ {
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/impl/RemoteProxyQueue.java";
/*      */   private static final int rpqNONSTREAMING_GMO_OPTS = 1930;
/*      */   private static final int rpqIMMUTABLE_GMO_OPTS = 536334404;
/*      */   private static final int rpqGMO_BROWSE_OPTS = 2096;
/*      */   protected JmqiSystemEnvironment sysenv;
/*      */   private volatile int status;
/*      */   
/*      */   static {
/*   74 */     if (Trace.isOn) {
/*   75 */       Trace.data("com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/impl/RemoteProxyQueue.java");
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  108 */   private Object statusSync = new StatusSync();
/*      */ 
/*      */   
/*      */   private int queueStatus;
/*      */   
/*      */   private RemoteHobj hobj;
/*      */   
/*      */   private RemoteHconn hconn;
/*      */   
/*      */   private int lastReceivedIndex;
/*      */   
/*      */   private String lastResolvedQName;
/*      */   
/*      */   private volatile boolean logicallyRemoved = false;
/*      */   
/*      */   private RemoteProxyMessage progressMsg;
/*      */   
/*      */   private int progressMsgOffset;
/*      */   
/*      */   private int progressMsgIndex;
/*      */   
/*      */   private ProxyQueueLock proxyQueueLock;
/*      */ 
/*      */   
/*      */   private static class ProxyQueueLock
/*      */     extends HconnLock
/*      */   {
/*      */     private static final long serialVersionUID = -2839430739976529818L;
/*      */ 
/*      */     
/*      */     ProxyQueueLock(RemoteFAP fap) {
/*  139 */       super(fap);
/*  140 */       if (Trace.isOn) {
/*  141 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.ProxyQueueLock", "<init>(RemoteFAP)", new Object[] { fap });
/*      */       }
/*  143 */       if (Trace.isOn) {
/*  144 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.ProxyQueueLock", "<init>(RemoteFAP)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  152 */   private Object getterEventMonitor = new GetterEventMonitor();
/*      */ 
/*      */   
/*      */   private int lastCompCode;
/*      */ 
/*      */   
/*      */   private int lastReason;
/*      */   
/*  160 */   private EnumSet<RemoteConstants.Event> eventsRaised = EnumSet.noneOf(RemoteConstants.Event.class);
/*      */ 
/*      */   
/*      */   private int selectionIndex;
/*      */   
/*  165 */   private byte[] currentMsgId = new byte[24];
/*      */   
/*      */   private boolean currentMsgIdSet;
/*      */   
/*  169 */   private byte[] currentCorrelId = new byte[24];
/*      */   
/*      */   private boolean currentCorrelIdSet;
/*      */   
/*  173 */   private byte[] currentGroupId = new byte[24];
/*      */   
/*      */   private boolean currentGroupIdSet;
/*      */   
/*  177 */   private byte[] currentMsgToken = new byte[16];
/*      */ 
/*      */   
/*      */   private boolean currentMsgTokenSet;
/*      */ 
/*      */   
/*      */   private int getMsgOptions;
/*      */ 
/*      */   
/*      */   private int codedCharSetId;
/*      */ 
/*      */   
/*      */   private int encoding;
/*      */ 
/*      */   
/*      */   private int maxMsgLength;
/*      */   
/*      */   private int mqmdVersion;
/*      */   
/*      */   private int matchOptions;
/*      */   
/*      */   private int offset;
/*      */   
/*      */   private int msgSeqNumber;
/*      */   
/*      */   private int immutableGmoOpts;
/*      */   
/*      */   private int highwaterMark;
/*      */   
/*      */   private int lowwaterMark;
/*      */   
/*      */   private int currentBytes;
/*      */   
/*      */   private int receivedBytes;
/*      */   
/*      */   private String queueManagerBytesReceivedFrom;
/*      */   
/*      */   private int firstSegmentIndicatedMessageSize;
/*      */   
/*      */   private int firstSegmentCalculatedMphLength;
/*      */   
/*      */   private int processedBytes;
/*      */   
/*      */   private int requestedBytes;
/*      */   
/*      */   private long backlog;
/*      */   
/*      */   private int purgeTime;
/*      */   
/*      */   private long lastCheckPurge;
/*      */   
/*      */   private MQGMO mqcbGmo;
/*      */   
/*      */   private MQMD mqcbMD;
/*      */   
/*      */   private MQCBD mqcbCBD;
/*      */   
/*      */   private MQCBC callbackCBC;
/*      */   
/*      */   private int asyncSelectionIndex;
/*      */   
/*      */   private long noMsgTime;
/*      */   
/*      */   private boolean cbAlreadyRegistered = false;
/*      */   
/*  242 */   private Throwable connectionFailure = null;
/*      */ 
/*      */ 
/*      */   
/*      */   private RemoteProxyMessage queueNewest;
/*      */ 
/*      */ 
/*      */   
/*      */   private RemoteProxyMessage queueOldest;
/*      */ 
/*      */ 
/*      */   
/*      */   private volatile RemoteProxyMessage queueTop;
/*      */ 
/*      */ 
/*      */   
/*      */   protected RemoteProxyMessage lastBrowsed;
/*      */ 
/*      */   
/*  261 */   private int appBufferLength = 0;
/*  262 */   private int appBufferUsed = 0;
/*  263 */   private byte[] appBuffer = null;
/*  264 */   private Pint appDataLength = null;
/*  265 */   private MQMD appMD = null;
/*  266 */   private Pint appCompCode = null;
/*  267 */   private Pint appReason = null;
/*  268 */   private MQGMO appGmo = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean messagePropertiesAsMPH = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  294 */   private long lastRequestTime = 0L;
/*  295 */   private long lastPhysicalAddTime = 0L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RemoteProxyQueue(JmqiEnvironment env, RemoteHconn hconn) throws JmqiException {
/*  306 */     super(env);
/*  307 */     if (Trace.isOn) {
/*  308 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "<init>(JmqiEnvironment,RemoteHconn)", new Object[] { env, hconn });
/*      */     }
/*      */     
/*  311 */     this.hconn = hconn;
/*  312 */     this.proxyQueueLock = new ProxyQueueLock(hconn.getFap());
/*  313 */     if (env instanceof JmqiSystemEnvironment) {
/*  314 */       this.sysenv = (JmqiSystemEnvironment)env;
/*      */     }
/*      */     
/*  317 */     this.mqmdVersion = 1;
/*      */ 
/*      */     
/*  320 */     this.status = 2048;
/*      */ 
/*      */     
/*  323 */     Configuration clientCfg = env.getConfiguration();
/*  324 */     this.highwaterMark = clientCfg.getIntValue(Configuration.MESSAGEBUFFER_MAXIMUMSIZE);
/*  325 */     this.highwaterMark *= 1024;
/*  326 */     this.lowwaterMark = clientCfg.getIntValue(Configuration.MESSAGEBUFFER_UPDATEPERCENTAGE);
/*  327 */     this.lowwaterMark = this.lowwaterMark * this.highwaterMark / 100;
/*  328 */     this.backlog = 0L;
/*  329 */     this.purgeTime = clientCfg.getIntValue(Configuration.MESSAGEBUFFER_PURGETIME);
/*  330 */     this.lastCheckPurge = getTimeInSeconds();
/*      */     
/*  332 */     this.queueStatus = 0;
/*  333 */     if (Trace.isOn) {
/*  334 */       Trace.data(this, "<init>(JmqiEnvironment,RemoteHconn)", "Queue status changed to", decodeQueueStatusNoTrace());
/*      */     }
/*      */     
/*  337 */     if (Trace.isOn) {
/*  338 */       Trace.data(env, "<init>(JmqiEnvironment,RemoteHconn)", "Highwater mark", Integer.valueOf(this.highwaterMark));
/*  339 */       Trace.data(env, "<init>(JmqiEnvironment,RemoteHconn)", "Lowwater mark", Integer.valueOf(this.lowwaterMark));
/*  340 */       Trace.data(env, "<init>(JmqiEnvironment,RemoteHconn)", "Purge time", Integer.valueOf(this.purgeTime));
/*  341 */       Trace.data(env, "<init>(JmqiEnvironment,RemoteHconn)", "RPQ status changed to", decodeStatusNoTrace());
/*      */     } 
/*      */     
/*  344 */     if (Trace.isOn) {
/*  345 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "<init>(JmqiEnvironment,RemoteHconn)");
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
/*      */   public void requestMutex(RemoteTls tls) throws JmqiException {
/*  360 */     if (Trace.isOn) {
/*  361 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "requestMutex(RemoteTls)", new Object[] { tls });
/*      */     }
/*      */ 
/*      */     
/*  365 */     this.hconn.checkForReconnectIfNecessary(this, null, tls);
/*      */     
/*  367 */     this.proxyQueueLock.lock();
/*      */     
/*  369 */     if (Trace.isOn) {
/*  370 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "requestMutex(RemoteTls)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void releaseMutex() {
/*  380 */     if (Trace.isOn) {
/*  381 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "releaseMutex()");
/*      */     }
/*      */     
/*  384 */     this.proxyQueueLock.unlock();
/*      */     
/*  386 */     if (Trace.isOn) {
/*  387 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "releaseMutex()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean haveMutex() {
/*  396 */     if (Trace.isOn) {
/*  397 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "haveMutex()");
/*      */     }
/*  399 */     boolean traceRet1 = this.proxyQueueLock.isHeldByCurrentThread();
/*  400 */     if (Trace.isOn) {
/*  401 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "haveMutex()", 
/*  402 */           Boolean.valueOf(traceRet1));
/*      */     }
/*  404 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setIdentifier(RemoteTls tls, RemoteHobj hobj) {
/*  414 */     if (Trace.isOn) {
/*  415 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "setIdentifier(RemoteTls,RemoteHobj)", new Object[] { tls, hobj });
/*      */     }
/*      */ 
/*      */     
/*  419 */     this.hobj = hobj;
/*      */     
/*  421 */     int readAheadOpt = hobj.getOpenOptions() & 0x180000;
/*      */     
/*  423 */     if (hobj.getDefaultReadAhead() == 2) {
/*  424 */       readAheadOpt = 524288;
/*  425 */       if (Trace.isOn) {
/*  426 */         Trace.data(this.env, "setIdentifier(RemoteTls,RemoteHobj)", "Read-ahead disabled at server. DefReadAhead", Integer.valueOf(hobj.getDefaultReadAhead()));
/*      */       }
/*      */     } 
/*      */     
/*  430 */     if (readAheadOpt == 0) {
/*  431 */       readAheadOpt = (hobj.getDefaultReadAhead() == 1) ? 1048576 : 524288;
/*  432 */       if (Trace.isOn) {
/*  433 */         Trace.data(this.env, "setIdentifier(RemoteTls,RemoteHobj)", "Read-ahead taken from object def. DefReadAhead", Integer.valueOf(hobj.getDefaultReadAhead()));
/*      */       }
/*      */     } 
/*  436 */     if (Trace.isOn) {
/*  437 */       Trace.data(this.env, "setIdentifier(RemoteTls,RemoteHobj)", "Requested read-ahead", Integer.valueOf(readAheadOpt));
/*      */     }
/*      */ 
/*      */     
/*  441 */     synchronized (this.statusSync) {
/*  442 */       if (readAheadOpt == 1048576 && this.highwaterMark != 0) {
/*  443 */         this.status |= 0x4000;
/*  444 */         if (Trace.isOn) {
/*  445 */           Trace.data(this, "setIdentifier(RemoteTls,RemoteHobj)", "RPQ status changed to", decodeStatusNoTrace());
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  450 */     if (Trace.isOn) {
/*  451 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "setIdentifier(RemoteTls,RemoteHobj)");
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
/*      */   private RemoteProxyMessage allocMessage(RemoteTls tls, int msgLength) {
/*  465 */     if (Trace.isOn) {
/*  466 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "allocMessage(RemoteTls,int)", new Object[] { tls, 
/*  467 */             Integer.valueOf(msgLength) });
/*      */     }
/*  469 */     RemoteProxyMessage message = new RemoteProxyMessage(this.env, msgLength);
/*      */     
/*  471 */     if (Trace.isOn) {
/*  472 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "allocMessage(RemoteTls,int)", message);
/*      */     }
/*      */     
/*  475 */     return message;
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
/*      */   protected boolean checkSelectionCriteria(RemoteTls tls, MQMD msgDesc, MQGMO getMsgOptionsParam, int maxMsgLengthParam) throws JmqiException {
/*      */     int localMatchOptions;
/*  491 */     if (Trace.isOn) {
/*  492 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "checkSelectionCriteria(RemoteTls,MQMD,MQGMO,int)", new Object[] { tls, msgDesc, getMsgOptionsParam, 
/*      */             
/*  494 */             Integer.valueOf(maxMsgLengthParam) });
/*      */     }
/*  496 */     assert haveMutex();
/*      */     
/*  498 */     boolean changed = false;
/*      */     
/*  500 */     if (this.mqmdVersion != msgDesc.getVersion()) {
/*  501 */       if ((this.status & 0x200) != 0 && (this.status & 0x400) != 0) {
/*  502 */         JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2026, null);
/*      */         
/*  504 */         if (Trace.isOn) {
/*  505 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "checkSelectionCriteria(RemoteTls,MQMD,MQGMO,int)", (Throwable)traceRet1);
/*      */         }
/*      */         
/*  508 */         throw traceRet1;
/*      */       } 
/*  510 */       this.mqmdVersion = msgDesc.getVersion();
/*  511 */       changed = true;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  517 */     if (getMsgOptionsParam.getVersion() >= 2) {
/*  518 */       localMatchOptions = getMsgOptionsParam.getMatchOptions();
/*      */ 
/*      */       
/*  521 */       if ((localMatchOptions & 0x20) != 0 && getMsgOptionsParam.getVersion() < 3) {
/*  522 */         localMatchOptions &= 0xFFFFFFDF;
/*      */       }
/*      */     } else {
/*      */       
/*  526 */       localMatchOptions = 0;
/*      */       
/*  528 */       if (!Arrays.equals(msgDesc.getCorrelId(), CMQC.MQCI_NONE)) {
/*  529 */         localMatchOptions |= 0x2;
/*      */       }
/*      */       
/*  532 */       if (!Arrays.equals(msgDesc.getMsgId(), CMQC.MQMI_NONE)) {
/*  533 */         localMatchOptions |= 0x1;
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  540 */     if (localMatchOptions != this.matchOptions) {
/*  541 */       this.matchOptions = localMatchOptions;
/*  542 */       changed = true;
/*      */     } 
/*      */ 
/*      */     
/*  546 */     if ((getMsgOptionsParam.getOptions() & 0x4000) != 0) {
/*      */ 
/*      */       
/*  549 */       if (msgDesc.getCodedCharSetId() != this.codedCharSetId) {
/*  550 */         this.codedCharSetId = msgDesc.getCodedCharSetId();
/*  551 */         changed = true;
/*      */       } 
/*      */ 
/*      */       
/*  555 */       if (msgDesc.getEncoding() != this.encoding) {
/*  556 */         this.encoding = msgDesc.getEncoding();
/*  557 */         changed = true;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  562 */     if ((localMatchOptions & 0x2) != 0) {
/*  563 */       if (!Arrays.equals(msgDesc.getCorrelId(), this.currentCorrelId)) {
/*  564 */         System.arraycopy(msgDesc.getCorrelId(), 0, this.currentCorrelId, 0, 24);
/*      */         
/*  566 */         if (Arrays.equals(this.currentCorrelId, CMQC.MQCI_NONE)) {
/*  567 */           this.currentCorrelIdSet = false;
/*      */         } else {
/*      */           
/*  570 */           this.currentCorrelIdSet = true;
/*      */         } 
/*  572 */         changed = true;
/*      */       } 
/*      */     } else {
/*      */       
/*  576 */       this.currentCorrelIdSet = false;
/*      */     } 
/*      */ 
/*      */     
/*  580 */     if ((localMatchOptions & 0x1) != 0) {
/*  581 */       if (!Arrays.equals(msgDesc.getMsgId(), this.currentMsgId)) {
/*  582 */         System.arraycopy(msgDesc.getMsgId(), 0, this.currentMsgId, 0, 24);
/*      */         
/*  584 */         if (Arrays.equals(this.currentMsgId, CMQC.MQMI_NONE)) {
/*  585 */           this.currentMsgIdSet = false;
/*      */         } else {
/*      */           
/*  588 */           this.currentMsgIdSet = true;
/*      */         } 
/*  590 */         changed = true;
/*      */       } 
/*      */     } else {
/*      */       
/*  594 */       this.currentMsgIdSet = false;
/*      */     } 
/*      */ 
/*      */     
/*  598 */     if ((localMatchOptions & 0x20) != 0) {
/*  599 */       if (!Arrays.equals(getMsgOptionsParam.getMsgToken(), this.currentMsgToken)) {
/*  600 */         System.arraycopy(getMsgOptionsParam.getMsgToken(), 0, this.currentMsgToken, 0, 16);
/*      */         
/*  602 */         if (Arrays.equals(this.currentMsgToken, CMQC.MQMTOK_NONE)) {
/*  603 */           this.currentMsgTokenSet = false;
/*      */         } else {
/*      */           
/*  606 */           this.currentMsgTokenSet = true;
/*      */         } 
/*  608 */         changed = true;
/*      */       } 
/*      */     } else {
/*      */       
/*  612 */       this.currentMsgTokenSet = false;
/*      */     } 
/*      */ 
/*      */     
/*  616 */     if (msgDesc.getVersion() != this.mqmdVersion) {
/*  617 */       this.mqmdVersion = msgDesc.getVersion();
/*  618 */       changed = true;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  623 */     if (msgDesc.getVersion() >= 2) {
/*      */ 
/*      */       
/*  626 */       if ((localMatchOptions & 0x4) != 0) {
/*  627 */         if (!Arrays.equals(msgDesc.getGroupId(), this.currentGroupId)) {
/*  628 */           System.arraycopy(msgDesc.getGroupId(), 0, this.currentGroupId, 0, 24);
/*      */           
/*  630 */           if (Arrays.equals(this.currentGroupId, CMQC.MQGI_NONE)) {
/*  631 */             this.currentGroupIdSet = false;
/*      */           } else {
/*      */             
/*  634 */             this.currentGroupIdSet = true;
/*      */           } 
/*  636 */           changed = true;
/*      */         } 
/*      */       } else {
/*      */         
/*  640 */         this.currentGroupIdSet = false;
/*      */       } 
/*      */ 
/*      */       
/*  644 */       if ((localMatchOptions & 0x8) != 0 && 
/*  645 */         msgDesc.getMsgSeqNumber() != this.msgSeqNumber) {
/*  646 */         this.msgSeqNumber = msgDesc.getMsgSeqNumber();
/*  647 */         changed = true;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  652 */       if ((localMatchOptions & 0x10) != 0 && 
/*  653 */         msgDesc.getOffset() != this.offset) {
/*  654 */         this.offset = msgDesc.getOffset();
/*  655 */         changed = true;
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  661 */       this.currentGroupIdSet = false;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  666 */     this.maxMsgLength = maxMsgLengthParam;
/*  667 */     synchronized (this.statusSync) {
/*  668 */       this.status |= 0x200;
/*      */     } 
/*  670 */     if (Trace.isOn) {
/*  671 */       Trace.data(this, "checkSelectionCriteria(RemoteTls,MQMD,MQGMO,int)", "RPQ status changed to", decodeStatusNoTrace());
/*      */     }
/*      */     
/*  674 */     if (changed) {
/*      */ 
/*      */       
/*  677 */       this.selectionIndex++;
/*  678 */       this.queueTop = this.queueOldest;
/*  679 */       synchronized (this.statusSync) {
/*  680 */         this.status |= 0x10000;
/*      */       } 
/*  682 */       if (Trace.isOn) {
/*  683 */         Trace.data(this, "checkSelectionCriteria(RemoteTls,MQMD,MQGMO,int)", "RPQ status changed to", decodeStatusNoTrace());
/*      */       }
/*  685 */       this.backlog = 0L;
/*      */     } 
/*      */     
/*  688 */     if (Trace.isOn) {
/*  689 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "checkSelectionCriteria(RemoteTls,MQMD,MQGMO,int)", 
/*  690 */           Boolean.valueOf(changed));
/*      */     }
/*  692 */     return changed;
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
/*      */   protected void requestMessages(RemoteTls tls, boolean check, int waitInterval, boolean mqgetCallWaiting, boolean asyncConsumer) throws JmqiException {
/*  707 */     if (Trace.isOn) {
/*  708 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "requestMessages(RemoteTls,boolean,int,boolean,boolean)", new Object[] { tls, 
/*      */             
/*  710 */             Boolean.valueOf(check), Integer.valueOf(waitInterval), Boolean.valueOf(mqgetCallWaiting), 
/*  711 */             Boolean.valueOf(asyncConsumer) });
/*      */     }
/*  713 */     assert haveMutex();
/*      */     
/*  715 */     RemoteSession session = this.hconn.getSession(this);
/*      */     try {
/*  717 */       requestMessagesReconnectable(tls, check, waitInterval, mqgetCallWaiting, asyncConsumer, session);
/*      */     }
/*  719 */     catch (JmqiException je) {
/*  720 */       if (Trace.isOn) {
/*  721 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "requestMessages(RemoteTls,boolean,int,boolean,boolean)", (Throwable)je);
/*      */       }
/*      */ 
/*      */       
/*  725 */       if (this.hconn.shouldReconnect(je) && !mqgetCallWaiting) {
/*      */ 
/*      */         
/*      */         try {
/*  729 */           releaseMutex();
/*  730 */           this.hconn.initiateReconnection(session);
/*      */         } finally {
/*      */           
/*  733 */           requestMutex(tls);
/*      */         } 
/*  735 */         requestMessages(tls, check, waitInterval, mqgetCallWaiting, asyncConsumer);
/*      */       } else {
/*      */         
/*  738 */         if (Trace.isOn) {
/*  739 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "requestMessages(RemoteTls,boolean,int,boolean,boolean)", (Throwable)je);
/*      */         }
/*      */         
/*  742 */         throw je;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  747 */     if (Trace.isOn) {
/*  748 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "requestMessages(RemoteTls,boolean,int,boolean,boolean)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void requestMessagesReconnectable(RemoteTls tls, boolean check, int waitInterval, boolean mqgetCallWaiting, boolean asyncConsumer, RemoteSession session) throws JmqiException {
/*  756 */     if (Trace.isOn) {
/*  757 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "requestMessagesReconnectable(RemoteTls,boolean,int,boolean,boolean,RemoteSession)", new Object[] { tls, 
/*      */             
/*  759 */             Boolean.valueOf(check), Integer.valueOf(waitInterval), 
/*  760 */             Boolean.valueOf(mqgetCallWaiting), Boolean.valueOf(asyncConsumer), session });
/*      */     }
/*      */     
/*  763 */     String destinationQueueManager = session.getParentConnection().getRemoteQmgrName();
/*      */     
/*  765 */     if (!destinationQueueManager.equals(this.queueManagerBytesReceivedFrom) && 
/*  766 */       this.receivedBytes != 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  771 */       if (Trace.isOn) {
/*  772 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "requestMessagesReconnectable(RemoteTls,boolean,int,boolean,boolean,RemoteSession)", 0);
/*      */       }
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */ 
/*      */     
/*  780 */     boolean mutexReleased = false;
/*  781 */     RfpTSH rTsh = null;
/*      */     
/*      */     try {
/*  784 */       synchronized (session.getRequestMessagesMutex()) {
/*  785 */         this.lastRequestTime = System.currentTimeMillis();
/*      */ 
/*      */         
/*  788 */         if (session.isDisconnected()) {
/*  789 */           JmqiException traceRet2 = null;
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  794 */           if (session.connectionBroken() || session.reconnectDisabled() || this.hconn.isReconnectable()) {
/*  795 */             if (Trace.isOn) {
/*  796 */               Trace.data(this, "requestMessagesReconnectable(RemoteTls,boolean,int,boolean,boolean,RemoteSession)", "Session disconnected, indicated CONN_BROKEN", null);
/*      */             }
/*  798 */             traceRet2 = new JmqiException(this.env, -1, null, 2, 2009, null);
/*      */           }
/*      */           else {
/*      */             
/*  802 */             traceRet2 = new JmqiException(this.env, -1, null, 2, 2018, null);
/*      */           } 
/*      */ 
/*      */           
/*  806 */           if (Trace.isOn) {
/*  807 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "requestMessagesReconnectable(RemoteTls,boolean,int,boolean,boolean,RemoteSession)", (Throwable)traceRet2, 1);
/*      */           }
/*      */ 
/*      */           
/*  811 */           throw traceRet2;
/*      */         } 
/*      */         
/*  814 */         boolean swap = session.isSwap();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  820 */         if ((this.status & 0x8) != 0) {
/*  821 */           this.status &= 0xFFFFFFF7;
/*      */         }
/*  823 */         if (Trace.isOn) {
/*  824 */           Trace.data(this, "requestMessagesReconnectable(RemoteTls,boolean,int,boolean,boolean,RemoteSession)", "RPQ status changed to", decodeStatusNoTrace());
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  832 */         if (asyncConsumer && (this.status & 0x2000000) != 0) {
/*  833 */           checkGetMsgOptions(tls, asyncConsumer, this.mqcbMD, this.mqcbGmo);
/*      */           
/*  835 */           checkSelectionCriteria(tls, this.mqcbMD, this.mqcbGmo, this.mqcbCBD.getMaxMsgLength());
/*      */           
/*  837 */           synchronized (this.statusSync) {
/*  838 */             this.status &= 0xFDFFFFFF;
/*      */           } 
/*  840 */           if (Trace.isOn) {
/*  841 */             Trace.data(this, "requestMessagesReconnectable(RemoteTls,boolean,int,boolean,boolean,RemoteSession)", "RPQ status changed to", decodeStatusNoTrace());
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/*  846 */         RfpTSH sTsh = session.allocateTSH(14, 1, null);
/*      */ 
/*      */         
/*  849 */         RfpREQUEST_MSGS request = (RfpREQUEST_MSGS)RfpStructure.newRfp(this.env, 19, sTsh.getRfpBuffer(), sTsh.getRfpOffset() + sTsh.hdrSize());
/*      */ 
/*      */         
/*  852 */         sTsh.setControlFlags1(48);
/*      */         
/*  854 */         request.setVersion(1, swap);
/*  855 */         request.setHobj(this.hobj.getIntegerHandle(), swap);
/*      */ 
/*      */         
/*  858 */         request.setReceivedBytes(this.receivedBytes, swap);
/*  859 */         this.receivedBytes = 0;
/*  860 */         this.queueManagerBytesReceivedFrom = null;
/*  861 */         this.processedBytes = 0;
/*      */ 
/*      */         
/*  864 */         if ((this.status & 0x400) != 0) {
/*  865 */           this.requestedBytes = this.highwaterMark - this.currentBytes;
/*      */           
/*  867 */           if (this.requestedBytes < 1) {
/*  868 */             this.requestedBytes = 0;
/*      */           }
/*      */ 
/*      */           
/*  872 */           if (this.highwaterMark > 0 && 10L * this.backlog / this.highwaterMark > 8L) {
/*  873 */             this.queueStatus |= 0x2;
/*  874 */             if (Trace.isOn) {
/*  875 */               Trace.data(this, "requestMessagesReconnectable(RemoteTls,boolean,int,boolean,boolean,RemoteSession)", "Queue status changed to", decodeQueueStatusNoTrace());
/*      */             }
/*      */           } else {
/*      */             
/*  879 */             this.queueStatus &= 0xFFFFFFFD;
/*  880 */             if (Trace.isOn) {
/*  881 */               Trace.data(this, "requestMessagesReconnectable(RemoteTls,boolean,int,boolean,boolean,RemoteSession)", "Queue status changed to", decodeQueueStatusNoTrace());
/*      */             }
/*      */           } 
/*      */           
/*  885 */           synchronized (this.statusSync) {
/*  886 */             this.status &= 0xFFFFF7FF;
/*      */           } 
/*  888 */           if (Trace.isOn) {
/*  889 */             Trace.data(this, "requestMessagesReconnectable(RemoteTls,boolean,int,boolean,boolean,RemoteSession)", "RPQ status changed to", decodeStatusNoTrace());
/*      */           }
/*      */         }
/*      */         else {
/*      */           
/*  894 */           this.requestedBytes = 1;
/*      */ 
/*      */ 
/*      */           
/*  898 */           synchronized (this.statusSync) {
/*  899 */             this.status |= 0x800;
/*      */           } 
/*  901 */           if (Trace.isOn) {
/*  902 */             Trace.data(this, "requestMessagesReconnectable(RemoteTls,boolean,int,boolean,boolean,RemoteSession)", "RPQ status changed to", decodeStatusNoTrace());
/*      */           }
/*      */         } 
/*      */         
/*  906 */         request.setRequestedBytes(this.requestedBytes, swap);
/*  907 */         request.setMaxMsgLength(this.maxMsgLength, swap);
/*  908 */         request.setWaitInterval(waitInterval, swap);
/*  909 */         request.setGetMsgOptions(this.getMsgOptions, swap);
/*  910 */         request.setQueueStatus(this.queueStatus, swap);
/*      */ 
/*      */ 
/*      */         
/*  914 */         if ((this.getMsgOptions & 0x10) != 0 && (this.getMsgOptions & 0x20) != 0) {
/*  915 */           this.getMsgOptions &= 0xFFFFFFEF;
/*      */         }
/*      */ 
/*      */         
/*  919 */         int requestFlags = 0;
/*      */ 
/*      */         
/*  922 */         if (mqgetCallWaiting) {
/*  923 */           requestFlags |= 0x8;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*  930 */         else if (this.hconn.isStarted() && !this.hconn.isSuspended() && !this.hconn.isSuspending() && this.queueTop == null && 
/*  931 */           this.hconn.checkClientEmpty()) {
/*  932 */           requestFlags |= 0x1;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  941 */         request.setGlobalMessageIndex(session.getGlobalMessageIndex(), swap);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  949 */         if (request.getCodedCharSetId(swap) != this.codedCharSetId && this.maxMsgLength == -1) {
/*  950 */           request.setCodedCharSetId(this.codedCharSetId, swap);
/*  951 */           request.setEncoding(this.encoding, swap);
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  956 */         if (this.messagePropertiesAsMPH) {
/*  957 */           requestFlags |= 0x20;
/*      */           
/*  959 */           if (Trace.isOn) {
/*  960 */             Trace.data(this, "requestMessagesReconnectable(RemoteTls,boolean,int,boolean,boolean,RemoteSession)", "Requesting rfpRMF_MSG_PROPERTIES_REQUESTED", 
/*  961 */                 Integer.valueOf(requestFlags));
/*      */           }
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  969 */         if ((this.status & 0x10000) != 0) {
/*  970 */           request.setRequestFlags(requestFlags |= 0x10, swap);
/*      */           
/*  972 */           request.setSelectionIndex(this.selectionIndex, swap);
/*  973 */           request.setMQMDVersion(this.mqmdVersion, swap);
/*  974 */           request.setCodedCharSetId(this.codedCharSetId, swap);
/*  975 */           request.setEncoding(this.encoding, swap);
/*  976 */           request.setOffset(this.offset, swap);
/*  977 */           request.setMsgSeqNumber(this.msgSeqNumber, swap);
/*  978 */           request.setMatchOptions(this.matchOptions, swap);
/*      */           
/*  980 */           int pos = 64;
/*      */           
/*  982 */           if ((this.matchOptions & 0x1) != 0) {
/*  983 */             request.setMsgId(this.currentMsgId, pos);
/*  984 */             pos += 24;
/*      */           } 
/*      */           
/*  987 */           if ((this.matchOptions & 0x2) != 0) {
/*  988 */             request.setCorrelId(this.currentCorrelId, pos);
/*  989 */             pos += 24;
/*      */           } 
/*      */           
/*  992 */           if ((this.matchOptions & 0x4) != 0) {
/*  993 */             request.setGroupId(this.currentGroupId, pos);
/*  994 */             pos += 24;
/*      */           } 
/*      */           
/*  997 */           if ((this.matchOptions & 0x20) != 0) {
/*  998 */             request.setMsgToken(this.currentMsgToken, pos);
/*  999 */             pos += 16;
/*      */           } 
/* 1001 */           sTsh.setTransLength(sTsh.hdrSize() + pos);
/*      */         } else {
/*      */           
/* 1004 */           sTsh.setTransLength(sTsh.hdrSize() + 40);
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1010 */         synchronized (this.statusSync) {
/* 1011 */           this.status &= 0xFFFEDFFF;
/*      */         } 
/* 1013 */         if (Trace.isOn) {
/* 1014 */           Trace.data(this, "requestMessagesReconnectable(RemoteTls,boolean,int,boolean,boolean,RemoteSession)", "RPQ status changed to", decodeStatusNoTrace());
/*      */         }
/*      */ 
/*      */         
/* 1018 */         if (check) {
/* 1019 */           requestFlags |= 0x2;
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1024 */           if ((this.status & 0x400) == 0) {
/* 1025 */             requestFlags |= 0x4;
/*      */           }
/*      */ 
/*      */           
/* 1029 */           request.setRequestFlags(requestFlags, swap);
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1034 */           releaseMutex();
/* 1035 */           mutexReleased = true;
/*      */ 
/*      */ 
/*      */           
/* 1039 */           rTsh = session.exchangeTSH(tls, sTsh);
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */           
/* 1045 */           request.setRequestFlags(requestFlags, swap);
/*      */           
/* 1047 */           releaseMutex();
/* 1048 */           mutexReleased = true;
/*      */ 
/*      */           
/* 1051 */           session.sendTSH(tls, sTsh);
/*      */         
/*      */         }
/*      */       
/*      */       }
/*      */     
/*      */     }
/*      */     finally {
/*      */       
/* 1060 */       if (Trace.isOn) {
/* 1061 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "requestMessagesReconnectable(RemoteTls,boolean,int,boolean,boolean,RemoteSession)", 3);
/*      */       }
/*      */ 
/*      */       
/* 1065 */       if (mutexReleased)
/*      */       {
/* 1067 */         requestMutex(tls);
/*      */       }
/*      */       
/* 1070 */       if (check)
/*      */       {
/* 1072 */         if (rTsh != null) {
/* 1073 */           if (rTsh.getSegmentType() != 15) {
/* 1074 */             StringBuffer dumpBuffer = new StringBuffer();
/* 1075 */             JmqiTools.hexDump(rTsh.getRfpBuffer(), null, rTsh.getRfpOffset(), rTsh.getTransLength(), dumpBuffer);
/* 1076 */             HashMap<String, Object> info = new HashMap<>();
/* 1077 */             info.put("SegmentType()", Integer.valueOf(rTsh.getSegmentType()));
/* 1078 */             info.put("Reply Buffer", dumpBuffer);
/* 1079 */             info.put("Description", "Unexpected reply received");
/* 1080 */             Trace.ffst(this, "sendTSH(RemoteTls,RfpTSH,RemoteSession)", "01", info, null);
/*      */             
/* 1082 */             JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2195, null);
/* 1083 */             if (Trace.isOn) {
/* 1084 */               Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "requestMessagesReconnectable(RemoteTls,boolean,int,boolean,boolean,RemoteSession)", (Throwable)traceRet1, 2);
/*      */             }
/*      */ 
/*      */             
/* 1088 */             throw traceRet1;
/*      */           } 
/* 1090 */           session.releaseReceivedTSH(rTsh);
/*      */         } 
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1097 */     if (Trace.isOn) {
/* 1098 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "requestMessagesReconnectable(RemoteTls,boolean,int,boolean,boolean,RemoteSession)", 1);
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
/*      */   private boolean matchSelection(RemoteTls tls, RemoteProxyMessage message) {
/* 1113 */     if (Trace.isOn) {
/* 1114 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "matchSelection(RemoteTls,RemoteProxyMessage)", new Object[] { tls, message });
/*      */     }
/*      */     
/* 1117 */     assert haveMutex();
/* 1118 */     boolean matches = true;
/* 1119 */     MQMD msgDesc = message.getMsgDesc();
/*      */     
/* 1121 */     if (matches && (this.matchOptions & 0x1) != 0 && this.currentMsgIdSet) {
/* 1122 */       matches = Arrays.equals(this.currentMsgId, msgDesc.getMsgId());
/*      */     }
/*      */     
/* 1125 */     if (matches && (this.matchOptions & 0x2) != 0 && this.currentCorrelIdSet) {
/* 1126 */       matches = Arrays.equals(this.currentCorrelId, msgDesc.getCorrelId());
/*      */     }
/*      */     
/* 1129 */     if (matches && (this.matchOptions & 0x4) != 0 && this.currentGroupIdSet) {
/* 1130 */       matches = Arrays.equals(this.currentGroupId, msgDesc.getGroupId());
/*      */     }
/*      */     
/* 1133 */     if (matches && (this.matchOptions & 0x20) != 0 && this.currentMsgTokenSet) {
/* 1134 */       matches = Arrays.equals(this.currentMsgToken, message.getMsgToken());
/*      */     }
/*      */     
/* 1137 */     if (matches && (this.matchOptions & 0x10) != 0) {
/* 1138 */       matches = (this.offset == msgDesc.getOffset());
/*      */     }
/*      */     
/* 1141 */     if (matches && (this.matchOptions & 0x8) != 0) {
/* 1142 */       matches = (this.msgSeqNumber == msgDesc.getMsgSeqNumber());
/*      */     }
/*      */     
/* 1145 */     if (Trace.isOn) {
/* 1146 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "matchSelection(RemoteTls,RemoteProxyMessage)", 
/* 1147 */           Boolean.valueOf(matches));
/*      */     }
/* 1149 */     return matches;
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
/*      */   protected RemoteProxyMessage findNextMessage(RemoteTls tls, int maxMsgLen, boolean msgOnly) throws JmqiException {
/* 1165 */     if (Trace.isOn) {
/* 1166 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "findNextMessage(RemoteTls,int,boolean)", new Object[] { tls, 
/* 1167 */             Integer.valueOf(maxMsgLen), 
/* 1168 */             Boolean.valueOf(msgOnly) });
/*      */     }
/* 1170 */     assert haveMutex();
/* 1171 */     RemoteProxyMessage message = this.queueTop;
/* 1172 */     RemoteProxyMessage nextMsg = null;
/* 1173 */     while (message != null) {
/*      */       
/* 1175 */       if ((message.getType() & 0x1) == 0) {
/*      */         
/* 1177 */         if (!msgOnly) {
/*      */           break;
/*      */         }
/*      */         
/* 1181 */         nextMsg = message.getNewer();
/* 1182 */         removeMessage(tls, true, false, message);
/*      */         
/* 1184 */         message = nextMsg;
/*      */ 
/*      */         
/*      */         continue;
/*      */       } 
/*      */ 
/*      */       
/* 1191 */       if (message.getReason() == 2080 && (
/* 1192 */         maxMsgLen == -1 || maxMsgLen > message.getMsgLength())) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1197 */         nextMsg = message.getNewer();
/* 1198 */         removeMessage(tls, true, false, message);
/*      */ 
/*      */ 
/*      */         
/* 1202 */         message = nextMsg;
/*      */         
/*      */         continue;
/*      */       } 
/*      */       
/* 1207 */       if (message.getSelectionIndex() == this.selectionIndex) {
/*      */         break;
/*      */       }
/*      */       
/* 1211 */       if (matchSelection(tls, message)) {
/*      */         break;
/*      */       }
/* 1214 */       this.backlog += (message.getMsgLength() + message.getMsgDescByteSize());
/* 1215 */       message = message.getNewer();
/*      */     } 
/* 1217 */     this.queueTop = message;
/*      */     
/* 1219 */     if (Trace.isOn) {
/* 1220 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "findNextMessage(RemoteTls,int,boolean)", message);
/*      */     }
/*      */     
/* 1223 */     return message;
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
/*      */   private void checkGmoOptions(RemoteTls tls, MQGMO gmo, boolean asyncConsumer, int mdVersion) throws JmqiException {
/* 1237 */     if (Trace.isOn) {
/* 1238 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "checkGmoOptions(RemoteTls,MQGMO,boolean,int)", new Object[] { tls, gmo, 
/*      */             
/* 1240 */             Boolean.valueOf(asyncConsumer), Integer.valueOf(mdVersion) });
/*      */     }
/*      */     
/* 1243 */     int gmoOptions = gmo.getOptions();
/* 1244 */     int gmoVersion = gmo.getVersion();
/* 1245 */     int gmoMatchOptions = gmo.getMatchOptions();
/* 1246 */     int gmoWaitInterval = gmo.getWaitInterval();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1251 */     if ((gmoOptions & 0xE0080088) != 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1257 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2046, null);
/*      */       
/* 1259 */       if (Trace.isOn) {
/* 1260 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "checkGmoOptions(RemoteTls,MQGMO,boolean,int)", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/* 1263 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1269 */     if (gmoVersion > 1 && (
/* 1270 */       gmoMatchOptions & 0xFFFFFFC0) != 0) {
/*      */       
/* 1272 */       JmqiException traceRet2 = new JmqiException(this.env, -1, null, 2, 2046, null);
/*      */       
/* 1274 */       if (Trace.isOn) {
/* 1275 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "checkGmoOptions(RemoteTls,MQGMO,boolean,int)", (Throwable)traceRet2, 2);
/*      */       }
/*      */       
/* 1278 */       throw traceRet2;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1285 */     if (gmoVersion > 1 && (
/* 1286 */       gmoMatchOptions & 0x20) != 0 && 
/* 1287 */       gmoVersion < 3) {
/* 1288 */       JmqiException traceRet3 = new JmqiException(this.env, -1, null, 2, 2256, null);
/*      */       
/* 1290 */       if (Trace.isOn) {
/* 1291 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "checkGmoOptions(RemoteTls,MQGMO,boolean,int)", (Throwable)traceRet3, 3);
/*      */       }
/*      */       
/* 1294 */       throw traceRet3;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1299 */     if (gmoOptions != 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1304 */       if ((gmoOptions & 0x2) != 0 && (gmoOptions & 0x1004) != 0) {
/*      */ 
/*      */ 
/*      */         
/* 1308 */         JmqiException traceRet4 = new JmqiException(this.env, -1, null, 2, 2046, null);
/*      */         
/* 1310 */         if (Trace.isOn) {
/* 1311 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "checkGmoOptions(RemoteTls,MQGMO,boolean,int)", (Throwable)traceRet4, 4);
/*      */         }
/*      */         
/* 1314 */         throw traceRet4;
/*      */       } 
/* 1316 */       if ((gmoOptions & 0x4) != 0 && (gmoOptions & 0x1000) != 0) {
/*      */ 
/*      */ 
/*      */         
/* 1320 */         JmqiException traceRet5 = new JmqiException(this.env, -1, null, 2, 2046, null);
/*      */         
/* 1322 */         if (Trace.isOn) {
/* 1323 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "checkGmoOptions(RemoteTls,MQGMO,boolean,int)", (Throwable)traceRet5, 5);
/*      */         }
/*      */         
/* 1326 */         throw traceRet5;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1332 */       if ((gmoOptions & 0x1000) != 0 && (gmoOptions & 0x10000) != 0) {
/* 1333 */         JmqiException traceRet6 = new JmqiException(this.env, -1, null, 2, 2046, null);
/*      */         
/* 1335 */         if (Trace.isOn) {
/* 1336 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "checkGmoOptions(RemoteTls,MQGMO,boolean,int)", (Throwable)traceRet6, 6);
/*      */         }
/*      */         
/* 1339 */         throw traceRet6;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1345 */       int browseOptCount = 0;
/* 1346 */       if ((gmoOptions & 0x830) != 0) {
/* 1347 */         if ((gmoOptions & 0x800) != 0) {
/* 1348 */           browseOptCount++;
/*      */         }
/* 1350 */         if ((gmoOptions & 0x20) != 0) {
/* 1351 */           browseOptCount++;
/*      */         }
/* 1353 */         if ((gmoOptions & 0x10) != 0) {
/* 1354 */           browseOptCount++;
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1360 */       if (asyncConsumer) {
/*      */ 
/*      */ 
/*      */         
/* 1364 */         if ((gmoOptions & 0x800) != 0) {
/* 1365 */           if ((gmoOptions & 0x30) != 0) {
/* 1366 */             JmqiException traceRet7 = new JmqiException(this.env, -1, null, 2, 2046, null);
/*      */             
/* 1368 */             if (Trace.isOn) {
/* 1369 */               Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "checkGmoOptions(RemoteTls,MQGMO,boolean,int)", (Throwable)traceRet7, 7);
/*      */             }
/*      */             
/* 1372 */             throw traceRet7;
/*      */ 
/*      */           
/*      */           }
/*      */ 
/*      */         
/*      */         }
/* 1379 */         else if ((gmoOptions & 0x30) == 48 && (
/* 1380 */           gmoOptions & 0x300000) != 0) {
/* 1381 */           JmqiException traceRet8 = new JmqiException(this.env, -1, null, 2, 2046, null);
/*      */           
/* 1383 */           if (Trace.isOn) {
/* 1384 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "checkGmoOptions(RemoteTls,MQGMO,boolean,int)", (Throwable)traceRet8, 8);
/*      */           }
/*      */           
/* 1387 */           throw traceRet8;
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*      */ 
/*      */ 
/*      */       
/*      */       }
/* 1396 */       else if (browseOptCount > 1) {
/*      */ 
/*      */ 
/*      */         
/* 1400 */         JmqiException traceRet9 = new JmqiException(this.env, -1, null, 2, 2046, null);
/*      */         
/* 1402 */         if (Trace.isOn) {
/* 1403 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "checkGmoOptions(RemoteTls,MQGMO,boolean,int)", (Throwable)traceRet9, 9);
/*      */         }
/*      */         
/* 1406 */         throw traceRet9;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1413 */       if ((gmoOptions & 0x100) > 0 && browseOptCount > 0) {
/* 1414 */         JmqiException traceRet10 = new JmqiException(this.env, -1, null, 2, 2046, null);
/*      */         
/* 1416 */         if (Trace.isOn) {
/* 1417 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "checkGmoOptions(RemoteTls,MQGMO,boolean,int)", (Throwable)traceRet10, 10);
/*      */         }
/*      */         
/* 1420 */         throw traceRet10;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1427 */       if ((gmoOptions & 0x1002) != 0 && browseOptCount > 0) {
/* 1428 */         JmqiException traceRet11 = new JmqiException(this.env, -1, null, 2, 2046, null);
/*      */         
/* 1430 */         if (Trace.isOn) {
/* 1431 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "checkGmoOptions(RemoteTls,MQGMO,boolean,int)", (Throwable)traceRet11, 11);
/*      */         }
/*      */         
/* 1434 */         throw traceRet11;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1440 */       if ((gmoOptions & 0x200) > 0 && browseOptCount == 0) {
/* 1441 */         JmqiException traceRet12 = new JmqiException(this.env, -1, null, 2, 2046, null);
/*      */         
/* 1443 */         if (Trace.isOn) {
/* 1444 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "checkGmoOptions(RemoteTls,MQGMO,boolean,int)", (Throwable)traceRet12, 12);
/*      */         }
/*      */         
/* 1447 */         throw traceRet12;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1453 */       if ((gmoOptions & 0x400) > 0 && (gmoOptions & 0xFFFFFBFB) > 0) {
/* 1454 */         JmqiException traceRet13 = new JmqiException(this.env, -1, null, 2, 2046, null);
/*      */         
/* 1456 */         if (Trace.isOn) {
/* 1457 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "checkGmoOptions(RemoteTls,MQGMO,boolean,int)", (Throwable)traceRet13, 13);
/*      */         }
/*      */         
/* 1460 */         throw traceRet13;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1466 */       if ((gmoOptions & 0x1F00000) != 0 && (gmoOptions & 0x78200) != 0) {
/*      */ 
/*      */         
/* 1469 */         JmqiException traceRet14 = new JmqiException(this.env, -1, null, 2, 2046, null);
/*      */         
/* 1471 */         if (Trace.isOn) {
/* 1472 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "checkGmoOptions(RemoteTls,MQGMO,boolean,int)", (Throwable)traceRet14, 14);
/*      */         }
/*      */         
/* 1475 */         throw traceRet14;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1483 */       if ((gmoOptions & 0xF00000) != 0)
/*      */       {
/*      */ 
/*      */         
/* 1487 */         if (browseOptCount == 0) {
/* 1488 */           JmqiException traceRet15 = new JmqiException(this.env, -1, null, 2, 2046, null);
/*      */           
/* 1490 */           if (Trace.isOn) {
/* 1491 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "checkGmoOptions(RemoteTls,MQGMO,boolean,int)", (Throwable)traceRet15, 15);
/*      */           }
/*      */           
/* 1494 */           throw traceRet15;
/*      */         } 
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1503 */       if ((gmoOptions & 0x800000) != 0 && (gmoOptions & 0x1100000) != 0) {
/* 1504 */         JmqiException traceRet16 = new JmqiException(this.env, -1, null, 2, 2046, null);
/*      */         
/* 1506 */         if (Trace.isOn) {
/* 1507 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "checkGmoOptions(RemoteTls,MQGMO,boolean,int)", (Throwable)traceRet16, 16);
/*      */         }
/*      */         
/* 1510 */         throw traceRet16;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1518 */       if ((gmoOptions & 0x400000) != 0 && (gmoOptions & 0x1200000) != 0) {
/* 1519 */         JmqiException traceRet17 = new JmqiException(this.env, -1, null, 2, 2046, null);
/*      */         
/* 1521 */         if (Trace.isOn) {
/* 1522 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "checkGmoOptions(RemoteTls,MQGMO,boolean,int)", (Throwable)traceRet17, 17);
/*      */         }
/*      */         
/* 1525 */         throw traceRet17;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1531 */       if ((gmoOptions & 0x8000) != 0) {
/* 1532 */         if (gmoVersion < 2) {
/* 1533 */           JmqiException traceRet18 = new JmqiException(this.env, -1, null, 2, 2256, null);
/*      */           
/* 1535 */           if (Trace.isOn) {
/* 1536 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "checkGmoOptions(RemoteTls,MQGMO,boolean,int)", (Throwable)traceRet18, 18);
/*      */           }
/*      */           
/* 1539 */           throw traceRet18;
/*      */         } 
/*      */         
/* 1542 */         if (mdVersion < 2) {
/* 1543 */           JmqiException traceRet19 = new JmqiException(this.env, -1, null, 2, 2257, null);
/*      */           
/* 1545 */           if (Trace.isOn) {
/* 1546 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "checkGmoOptions(RemoteTls,MQGMO,boolean,int)", (Throwable)traceRet19, 19);
/*      */           }
/*      */           
/* 1549 */           throw traceRet19;
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1557 */       if (gmoVersion > 1 && (
/* 1558 */         gmoOptions & 0x409) != 0 && (
/* 1559 */         gmoMatchOptions & 0x20) != 0) {
/* 1560 */         JmqiException traceRet20 = new JmqiException(this.env, -1, null, 2, 2331, null);
/*      */         
/* 1562 */         if (Trace.isOn) {
/* 1563 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "checkGmoOptions(RemoteTls,MQGMO,boolean,int)", (Throwable)traceRet20, 20);
/*      */         }
/*      */         
/* 1566 */         throw traceRet20;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1575 */     if (gmoWaitInterval >= 0 || gmoWaitInterval == -1) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1593 */       if (Trace.isOn) {
/* 1594 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "checkGmoOptions(RemoteTls,MQGMO,boolean,int)");
/*      */       }
/*      */       return;
/*      */     } 
/*      */     JmqiException traceRet21 = new JmqiException(this.env, -1, null, 2, 2090, null);
/*      */     if (Trace.isOn) {
/*      */       Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "checkGmoOptions(RemoteTls,MQGMO,boolean,int)", (Throwable)traceRet21, 21);
/*      */     }
/*      */     throw traceRet21;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void checkGetMsgOptions(RemoteTls tls, boolean asyncConsumer, MQMD msgDesc, MQGMO gmo) throws JmqiException {
/* 1611 */     if (Trace.isOn) {
/* 1612 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "checkGetMsgOptions(RemoteTls,boolean,MQMD,MQGMO)", new Object[] { tls, 
/*      */             
/* 1614 */             Boolean.valueOf(asyncConsumer), msgDesc, gmo });
/*      */     }
/* 1616 */     assert haveMutex();
/* 1617 */     boolean canStream = false;
/* 1618 */     boolean stopStreaming = false;
/*      */ 
/*      */ 
/*      */     
/* 1622 */     checkGmoOptions(tls, gmo, asyncConsumer, msgDesc.getVersion());
/* 1623 */     int gmoOptions = gmo.getOptions();
/*      */     
/* 1625 */     if ((gmoOptions & 0x830) != 0) {
/*      */       
/* 1627 */       if ((this.hobj.getOpenOptions() & 0x8) == 0) {
/* 1628 */         JmqiException traceRet4 = new JmqiException(this.env, -1, null, 2, 2036, null);
/*      */         
/* 1630 */         if (Trace.isOn) {
/* 1631 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "checkGetMsgOptions(RemoteTls,boolean,MQMD,MQGMO)", (Throwable)traceRet4, 1);
/*      */         }
/*      */         
/* 1634 */         throw traceRet4;
/*      */       
/*      */       }
/*      */     
/*      */     }
/* 1639 */     else if ((this.hobj.getOpenOptions() & 0x7) == 0) {
/* 1640 */       JmqiException traceRet5 = new JmqiException(this.env, -1, null, 2, 2037, null);
/* 1641 */       if (Trace.isOn) {
/* 1642 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "checkGetMsgOptions(RemoteTls,boolean,MQMD,MQGMO)", (Throwable)traceRet5, 2);
/*      */       }
/*      */       
/* 1645 */       throw traceRet5;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1650 */     canStream = ((this.status & 0x4000) != 0);
/* 1651 */     canStream = (canStream && (gmoOptions & 0x78A) == 0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1657 */     if (asyncConsumer && (
/* 1658 */       gmoOptions & 0x10) != 0 && (gmoOptions & 0x1300020) == 0)
/*      */     {
/* 1660 */       stopStreaming = true;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1667 */     if ((gmoOptions & 0x830) != 0 || (gmoOptions & 0x4) != 0) {
/* 1668 */       synchronized (this.statusSync) {
/* 1669 */         this.status &= 0xEFFFFFFF;
/*      */       } 
/* 1671 */       if (Trace.isOn) {
/* 1672 */         Trace.data(this, "checkGetMsgOptions(RemoteTls,boolean,MQMD,MQGMO)", "RPQ status changed to", decodeStatusNoTrace());
/*      */       }
/*      */     } else {
/*      */       
/* 1676 */       synchronized (this.statusSync) {
/* 1677 */         this.status |= 0x10000000;
/*      */       } 
/* 1679 */       if (Trace.isOn) {
/* 1680 */         Trace.data(this, "checkGetMsgOptions(RemoteTls,boolean,MQMD,MQGMO)", "RPQ status changed to", decodeStatusNoTrace());
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1686 */     if ((this.status & 0x200) != 0) {
/*      */       
/* 1688 */       if ((this.status & 0x400) == 0) {
/* 1689 */         this.getMsgOptions = gmoOptions;
/*      */         
/* 1691 */         if ((gmoOptions & 0x830) != 0) {
/* 1692 */           synchronized (this.statusSync) {
/* 1693 */             this.status |= 0x20;
/* 1694 */             this.status &= 0xFFFDFFFF;
/*      */           } 
/* 1696 */           if (Trace.isOn) {
/* 1697 */             Trace.data(this, "checkGetMsgOptions(RemoteTls,boolean,MQMD,MQGMO)", "RPQ status changed to", decodeStatusNoTrace());
/*      */           }
/*      */         } else {
/*      */           
/* 1701 */           synchronized (this.statusSync) {
/* 1702 */             this.status |= 0x20000;
/* 1703 */             this.status &= 0xFFFFFFDF;
/*      */           } 
/* 1705 */           if (Trace.isOn) {
/* 1706 */             Trace.data(this, "checkGetMsgOptions(RemoteTls,boolean,MQMD,MQGMO)", "RPQ status changed to", decodeStatusNoTrace());
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/* 1711 */         if (this.encoding != msgDesc.getEncoding()) {
/* 1712 */           this.status |= 0x10000;
/* 1713 */           this.encoding = msgDesc.getEncoding();
/* 1714 */           if (Trace.isOn) {
/* 1715 */             Trace.data(this, "checkGetMsgOptions(RemoteTls,boolean,MQMD,MQGMO)", "RPQ status changed to", decodeStatusNoTrace());
/*      */           }
/*      */         } 
/* 1718 */         if (this.codedCharSetId != msgDesc.getCodedCharSetId()) {
/* 1719 */           this.status |= 0x10000;
/* 1720 */           this.codedCharSetId = msgDesc.getCodedCharSetId();
/* 1721 */           if (Trace.isOn) {
/* 1722 */             Trace.data(this, "checkGetMsgOptions(RemoteTls,boolean,MQMD,MQGMO)", "RPQ status changed to", decodeStatusNoTrace());
/*      */           }
/*      */         } 
/* 1725 */         if (Trace.isOn) {
/* 1726 */           Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "checkGetMsgOptions(RemoteTls,boolean,MQMD,MQGMO)", 1);
/*      */         }
/*      */ 
/*      */         
/*      */         return;
/*      */       } 
/*      */ 
/*      */       
/* 1734 */       if (!canStream) {
/* 1735 */         JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2046, null);
/*      */         
/* 1737 */         if (Trace.isOn) {
/* 1738 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "checkGetMsgOptions(RemoteTls,boolean,MQMD,MQGMO)", (Throwable)traceRet1, 3);
/*      */         }
/*      */         
/* 1741 */         throw traceRet1;
/*      */       } 
/*      */ 
/*      */       
/* 1745 */       if ((gmoOptions & 0x830) != 0) {
/* 1746 */         if ((this.status & 0x20000) != 0) {
/* 1747 */           JmqiException traceRet6 = new JmqiException(this.env, -1, null, 2, 2457, null);
/* 1748 */           if (Trace.isOn) {
/* 1749 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "checkGetMsgOptions(RemoteTls,boolean,MQMD,MQGMO)", (Throwable)traceRet6, 4);
/*      */           }
/*      */           
/* 1752 */           throw traceRet6;
/*      */         }
/*      */       
/*      */       }
/* 1756 */       else if ((this.status & 0x20) != 0) {
/* 1757 */         JmqiException traceRet7 = new JmqiException(this.env, -1, null, 2, 2457, null);
/* 1758 */         if (Trace.isOn) {
/* 1759 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "checkGetMsgOptions(RemoteTls,boolean,MQMD,MQGMO)", (Throwable)traceRet7, 5);
/*      */         }
/*      */         
/* 1762 */         throw traceRet7;
/*      */       } 
/*      */ 
/*      */       
/* 1766 */       if ((gmoOptions & 0x1FF7D044) != this.immutableGmoOpts) {
/* 1767 */         JmqiException traceRet2 = new JmqiException(this.env, -1, null, 2, 2457, null);
/* 1768 */         if (Trace.isOn) {
/* 1769 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "checkGetMsgOptions(RemoteTls,boolean,MQMD,MQGMO)", (Throwable)traceRet2, 6);
/*      */         }
/*      */         
/* 1772 */         throw traceRet2;
/*      */       } 
/*      */ 
/*      */       
/* 1776 */       if ((gmoOptions & 0x4000) != 0 && (msgDesc.getEncoding() != this.encoding || msgDesc.getCodedCharSetId() != this.codedCharSetId)) {
/* 1777 */         JmqiException traceRet3 = new JmqiException(this.env, -1, null, 2, 2457, null);
/* 1778 */         if (Trace.isOn) {
/* 1779 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "checkGetMsgOptions(RemoteTls,boolean,MQMD,MQGMO)", (Throwable)traceRet3, 7);
/*      */         }
/*      */         
/* 1782 */         throw traceRet3;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1787 */       if (stopStreaming) {
/* 1788 */         synchronized (this.statusSync) {
/* 1789 */           this.status &= 0xFFFFFBFF;
/*      */         } 
/* 1791 */         if (Trace.isOn) {
/* 1792 */           Trace.data(this, "checkGetMsgOptions(RemoteTls,boolean,MQMD,MQGMO)", "RPQ status changed to", decodeStatusNoTrace());
/*      */         
/*      */         }
/*      */       
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 1801 */       if ((gmoOptions & 0x830) != 0) {
/* 1802 */         synchronized (this.statusSync) {
/* 1803 */           this.status |= 0x20;
/* 1804 */           this.status &= 0xFFFDFFFF;
/*      */         } 
/* 1806 */         if (Trace.isOn) {
/* 1807 */           Trace.data(this, "checkGetMsgOptions(RemoteTls,boolean,MQMD,MQGMO)", "RPQ status changed to", decodeStatusNoTrace());
/*      */         }
/*      */       } else {
/*      */         
/* 1811 */         synchronized (this.statusSync) {
/* 1812 */           this.status |= 0x20000;
/* 1813 */           this.status &= 0xFFFFFFDF;
/*      */         } 
/* 1815 */         if (Trace.isOn) {
/* 1816 */           Trace.data(this, "checkGetMsgOptions(RemoteTls,boolean,MQMD,MQGMO)", "RPQ status changed to", decodeStatusNoTrace());
/*      */         }
/*      */       } 
/*      */       
/* 1820 */       if (canStream && !stopStreaming) {
/* 1821 */         synchronized (this.statusSync) {
/* 1822 */           this.status |= 0x400;
/*      */         } 
/* 1824 */         if (Trace.isOn) {
/* 1825 */           Trace.data(this, "checkGetMsgOptions(RemoteTls,boolean,MQMD,MQGMO)", "RPQ status changed to", decodeStatusNoTrace());
/*      */         }
/*      */       } else {
/*      */         
/* 1829 */         synchronized (this.statusSync) {
/* 1830 */           this.status &= 0xFFFFFBFF;
/*      */         } 
/* 1832 */         if (Trace.isOn) {
/* 1833 */           Trace.data(this, "checkGetMsgOptions(RemoteTls,boolean,MQMD,MQGMO)", "RPQ status changed to", decodeStatusNoTrace());
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 1838 */       if ((gmoOptions & 0x4000) != 0) {
/* 1839 */         if (this.encoding != msgDesc.getEncoding() || this.codedCharSetId != msgDesc.getCodedCharSetId()) {
/* 1840 */           this.status |= 0x10000;
/*      */         }
/* 1842 */         if (Trace.isOn) {
/* 1843 */           Trace.data(this, "checkGetMsgOptions(RemoteTls,boolean,MQMD,MQGMO)", "RPQ status changed to", decodeStatusNoTrace());
/*      */         }
/*      */       } 
/* 1846 */       this.encoding = msgDesc.getEncoding();
/* 1847 */       this.codedCharSetId = msgDesc.getCodedCharSetId();
/* 1848 */       this.immutableGmoOpts = gmoOptions & 0x1FF7D044;
/*      */ 
/*      */       
/* 1851 */       if ((this.status & 0x4000) == 16384) {
/* 1852 */         if (canStream) {
/* 1853 */           this.queueStatus |= 0x1;
/* 1854 */           if (Trace.isOn) {
/* 1855 */             Trace.data(this, "checkGetMsgOptions(RemoteTls,boolean,MQMD,MQGMO)", "Queue status changed to", decodeQueueStatusNoTrace());
/*      */           }
/*      */         } else {
/*      */           
/* 1859 */           this.queueStatus |= 0x4;
/* 1860 */           if (Trace.isOn) {
/* 1861 */             Trace.data(this, "checkGetMsgOptions(RemoteTls,boolean,MQMD,MQGMO)", "Queue status changed to", decodeQueueStatusNoTrace());
/*      */           }
/*      */         } 
/*      */       }
/*      */       
/* 1866 */       if (Trace.isOn) {
/* 1867 */         Trace.data(this, "checkGetMsgOptions(RemoteTls,boolean,MQMD,MQGMO)", "CanStream", Boolean.valueOf(canStream));
/* 1868 */         Trace.data(this, "checkGetMsgOptions(RemoteTls,boolean,MQMD,MQGMO)", "ImmutableGmoOpts", Integer.valueOf(this.immutableGmoOpts));
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1873 */     this.getMsgOptions = gmoOptions;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1880 */     if (gmo.getVersion() >= 4 && (gmoOptions & 0x8000000) == 134217728) {
/* 1881 */       this.messagePropertiesAsMPH = true;
/*      */     } else {
/*      */       
/* 1884 */       this.messagePropertiesAsMPH = false;
/*      */     } 
/*      */     
/* 1887 */     if (Trace.isOn) {
/* 1888 */       Trace.data(this, "checkGetMsgOptions(RemoteTls,boolean,MQMD,MQGMO)", "Request message properties as RfpMPH", Boolean.valueOf(this.messagePropertiesAsMPH));
/*      */     }
/*      */     
/* 1891 */     if (Trace.isOn) {
/* 1892 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "checkGetMsgOptions(RemoteTls,boolean,MQMD,MQGMO)", 2);
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
/*      */   private void flushQueue(RemoteTls tls, boolean mqgetWaiting) throws JmqiException {
/* 1907 */     if (Trace.isOn) {
/* 1908 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "flushQueue(RemoteTls,boolean)", new Object[] { tls, 
/* 1909 */             Boolean.valueOf(mqgetWaiting) });
/*      */     }
/* 1911 */     assert haveMutex();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1916 */     requestMessages(tls, true, 0, mqgetWaiting, false);
/*      */     
/* 1918 */     if (Trace.isOn) {
/* 1919 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "flushQueue(RemoteTls,boolean)");
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
/*      */   protected void removeMessage(RemoteTls tls, boolean refill, boolean keepBrowse, RemoteProxyMessage message) throws JmqiException {
/* 1935 */     if (Trace.isOn) {
/* 1936 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "removeMessage(RemoteTls,boolean,boolean,RemoteProxyMessage)", new Object[] { tls, 
/*      */             
/* 1938 */             Boolean.valueOf(refill), Boolean.valueOf(keepBrowse), message });
/*      */     }
/* 1940 */     assert haveMutex();
/*      */     
/* 1942 */     int size = message.getMsgDescByteSize() + message.getMsgLength() - message.getMessagePropertiesLength();
/* 1943 */     this.currentBytes -= size;
/* 1944 */     this.processedBytes += size;
/*      */     
/* 1946 */     RemoteProxyMessage older = message.getOlder();
/* 1947 */     RemoteProxyMessage newer = message.getNewer();
/* 1948 */     if (newer != null) {
/* 1949 */       newer.setOlder(older);
/*      */     }
/* 1951 */     if (older != null) {
/* 1952 */       older.setNewer(newer);
/*      */     }
/*      */     
/* 1955 */     if (this.queueTop == message) {
/* 1956 */       this.queueTop = newer;
/*      */     }
/* 1958 */     if (this.queueNewest == message) {
/* 1959 */       this.queueNewest = older;
/*      */     }
/* 1961 */     if (this.queueOldest == message) {
/* 1962 */       this.queueOldest = newer;
/*      */     }
/* 1964 */     if (refill)
/*      */     {
/*      */       
/* 1967 */       if ((this.status & 0x400) != 0 && (this.status & 0x80) == 0 && (this.status & 0x8000) == 0)
/*      */       {
/*      */ 
/*      */ 
/*      */         
/* 1972 */         if (this.currentBytes < this.highwaterMark && 
/* 1973 */           this.processedBytes >= this.highwaterMark - this.lowwaterMark) {
/* 1974 */           requestMessages(tls, false, 0, false, false);
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1982 */     if (keepBrowse && (message.getType() & 0x1) != 0)
/*      */     {
/* 1984 */       this.lastBrowsed = message;
/*      */     }
/*      */     
/* 1987 */     if (Trace.isOn && message.isPersistent()) {
/* 1988 */       Trace.data(this, "removeMessage(RemoteTls,boolean,boolean,RemoteProxyMessage)", "Persistent message removed", message.getMsgDesc().toString());
/*      */     }
/*      */     
/* 1991 */     if (Trace.isOn) {
/* 1992 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "removeMessage(RemoteTls,boolean,boolean,RemoteProxyMessage)");
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
/*      */   private void removeAllMessages(RemoteTls tls) throws JmqiException {
/* 2005 */     if (Trace.isOn) {
/* 2006 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "removeAllMessages(RemoteTls)", new Object[] { tls });
/*      */     }
/*      */     
/* 2009 */     assert haveMutex();
/* 2010 */     while (this.queueTop != null) {
/* 2011 */       removeMessage(tls, false, false, this.queueTop);
/*      */     }
/* 2013 */     if (this.lastBrowsed != null) {
/* 2014 */       this.lastBrowsed = null;
/*      */     }
/*      */     
/* 2017 */     if (Trace.isOn) {
/* 2018 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "removeAllMessages(RemoteTls)");
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
/*      */   private void resetBrowseFirst(RemoteTls tls) throws JmqiException {
/* 2032 */     if (Trace.isOn) {
/* 2033 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "resetBrowseFirst(RemoteTls)", new Object[] { tls });
/*      */     }
/*      */     
/* 2036 */     assert haveMutex();
/*      */ 
/*      */     
/* 2039 */     removeAllMessages(tls);
/*      */     
/* 2041 */     synchronized (this.statusSync) {
/* 2042 */       this.status |= 0x1000;
/*      */     } 
/*      */     
/* 2045 */     synchronized (this.statusSync) {
/* 2046 */       this.status |= 0x800;
/*      */     } 
/* 2048 */     if (Trace.isOn) {
/* 2049 */       Trace.data(this, "resetBrowseFirst(RemoteTls)", "RPQ status changed to", decodeStatusNoTrace());
/*      */     }
/*      */     
/* 2052 */     if (Trace.isOn) {
/* 2053 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "resetBrowseFirst(RemoteTls)");
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
/*      */   private void copyOutMessage(RemoteTls tls, RemoteProxyMessage message, MQMD userMd, MQGMO userGmo, int bufferLength, byte[] buffer, Pint pDataLength, Pint pCompCode, Pint pReason) throws JmqiException {
/* 2077 */     if (Trace.isOn) {
/* 2078 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "copyOutMessage(RemoteTls,RemoteProxyMessage,MQMD,MQGMO,int,byte [ ],Pint,Pint,Pint)", new Object[] { tls, message, userMd, userGmo, 
/*      */             
/* 2080 */             Integer.valueOf(bufferLength), buffer, pDataLength, pCompCode, pReason });
/*      */     }
/*      */     
/* 2083 */     assert haveMutex();
/* 2084 */     boolean remove = false;
/* 2085 */     boolean keepBrowse = false;
/* 2086 */     if ((message.getType() & 0x1) != 0) {
/*      */       int msgLength;
/*      */ 
/*      */       
/*      */       boolean truncated;
/*      */ 
/*      */       
/* 2093 */       keepBrowse = ((this.status & 0x20) != 0 && (this.status & 0x400) != 0);
/* 2094 */       MQMD msgMd = message.getMsgDesc();
/*      */       
/* 2096 */       copyMDToUserMD(userMd, msgMd);
/* 2097 */       if (message.getMsgLength() > bufferLength) {
/*      */ 
/*      */ 
/*      */         
/* 2101 */         msgLength = bufferLength;
/* 2102 */         truncated = true;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2107 */         if ((userGmo.getOptions() & 0x40) != 0) {
/* 2108 */           remove = true;
/*      */         
/*      */         }
/* 2111 */         else if (message.getActualMsgLength() > message.getMsgLength()) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2117 */           remove = true;
/*      */ 
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */           
/* 2124 */           remove = false;
/*      */         
/*      */         }
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 2132 */         msgLength = message.getMsgLength();
/* 2133 */         truncated = false;
/* 2134 */         remove = true;
/*      */       } 
/*      */       
/* 2137 */       byte[] msgData = message.getMsgData();
/* 2138 */       System.arraycopy(msgData, 0, buffer, 0, msgLength);
/*      */       
/* 2140 */       if (truncated) {
/* 2141 */         if ((userGmo.getOptions() & 0x40) != 0) {
/* 2142 */           pReason.x = 2079;
/*      */         } else {
/*      */           
/* 2145 */           pReason.x = 2080;
/*      */         } 
/*      */       } else {
/*      */         
/* 2149 */         pReason.x = message.getReason();
/*      */       } 
/* 2151 */       if (pReason.x != 0) {
/* 2152 */         pCompCode.x = 1;
/*      */       } else {
/*      */         
/* 2155 */         pCompCode.x = 0;
/*      */       } 
/* 2157 */       pDataLength.x = message.getActualMsgLength();
/*      */ 
/*      */ 
/*      */       
/* 2161 */       userGmo.setResolvedQName(message.getResolvedQName());
/* 2162 */       if (userGmo.getVersion() >= 2) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2169 */         userGmo.setGroupStatus(32);
/* 2170 */         userGmo.setSegmentStatus(32);
/* 2171 */         userGmo.setSegmentation(32);
/*      */         
/* 2173 */         if ((message.getStatus() & 0x3E) != 0) {
/*      */ 
/*      */           
/* 2176 */           if ((message.getStatus() & 0x10) != 0) {
/* 2177 */             userGmo.setGroupStatus(71);
/*      */           }
/* 2179 */           if ((message.getStatus() & 0x20) != 0) {
/* 2180 */             userGmo.setGroupStatus(76);
/*      */           }
/*      */ 
/*      */           
/* 2184 */           if ((message.getStatus() & 0x4) != 0) {
/* 2185 */             userGmo.setSegmentStatus(83);
/*      */           }
/* 2187 */           if ((message.getStatus() & 0x8) != 0) {
/* 2188 */             userGmo.setSegmentStatus(76);
/*      */           }
/*      */ 
/*      */           
/* 2192 */           if ((message.getStatus() & 0x2) != 0) {
/* 2193 */             userGmo.setSegmentation(65);
/*      */           }
/*      */         } 
/* 2196 */         if (userGmo.getVersion() >= 3) {
/* 2197 */           userGmo.setMsgToken(message.getMsgToken());
/* 2198 */           userGmo.setReturnedLength(msgLength);
/*      */         }
/*      */       
/*      */       } 
/*      */     } else {
/*      */       
/* 2204 */       pCompCode.x = message.getCompCode();
/* 2205 */       pReason.x = message.getReason();
/* 2206 */       remove = true;
/* 2207 */       keepBrowse = false;
/*      */     } 
/*      */     
/* 2210 */     if (remove && message != this.lastBrowsed) {
/* 2211 */       removeMessage(tls, true, keepBrowse, message);
/*      */     }
/*      */     
/* 2214 */     if (Trace.isOn) {
/* 2215 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "copyOutMessage(RemoteTls,RemoteProxyMessage,MQMD,MQGMO,int,byte [ ],Pint,Pint,Pint)");
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
/*      */   private void addPhysicalMessage(RemoteTls tls, RemoteProxyMessage message) throws JmqiException {
/* 2230 */     if (Trace.isOn) {
/* 2231 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "addPhysicalMessage(RemoteTls,RemoteProxyMessage)", new Object[] { tls, message });
/*      */     }
/*      */     
/* 2234 */     assert haveMutex();
/* 2235 */     boolean wasEmpty = false;
/* 2236 */     this.lastPhysicalAddTime = System.currentTimeMillis();
/* 2237 */     message.setNewer(null);
/* 2238 */     message.setOlder(this.queueNewest);
/* 2239 */     if (this.queueNewest != null) {
/* 2240 */       this.queueNewest.setNewer(message);
/*      */     }
/* 2242 */     this.queueNewest = message;
/* 2243 */     if (this.queueTop == null) {
/* 2244 */       this.queueTop = message;
/* 2245 */       wasEmpty = true;
/*      */     } 
/* 2247 */     if (this.queueOldest == null) {
/* 2248 */       this.queueOldest = message;
/*      */     }
/* 2250 */     if (Trace.isOn) {
/* 2251 */       Trace.data(this, "addPhysicalMessage(RemoteTls,RemoteProxyMessage)", "wasEmpty = " + wasEmpty + " mqcbCBD = " + this.mqcbCBD, null);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2257 */     if (wasEmpty && this.mqcbCBD != null && this.mqcbCBD.getCallbackFunction() != null && (this.status & 0x40000) == 0) {
/* 2258 */       this.hconn.checkDispatchable(this);
/*      */     }
/* 2260 */     Trace.data(this, "addPhysicalMessage(RemoteTls,RemoteProxyMessage)", "status", Integer.valueOf(this.status));
/*      */     
/* 2262 */     if ((this.status & 0x2) != 0 && (this.status & 0x4) == 0)
/*      */     {
/* 2264 */       synchronized (this.getterEventMonitor) {
/*      */         
/* 2266 */         synchronized (this.statusSync) {
/* 2267 */           this.status |= 0x4;
/* 2268 */           if (Trace.isOn) {
/* 2269 */             Trace.data(this, "addPhysicalMessage(RemoteTls,RemoteProxyMessage)", "RPQ status changed to", decodeStatusNoTrace());
/*      */           }
/*      */         } 
/* 2272 */         this.getterEventMonitor.notifyAll();
/*      */       } 
/*      */     }
/* 2275 */     if (Trace.isOn) {
/* 2276 */       Trace.data(this, "addPhysicalMessage(RemoteTls,RemoteProxyMessage)", "Message added to queue.", null);
/*      */     }
/*      */     
/* 2279 */     if (Trace.isOn && message.isPersistent()) {
/* 2280 */       Trace.data(this, "addPhysicalMessage(RemoteTls,RemoteProxyMessage)", "Persistent message added to queue.", message.getMsgDesc().toString());
/*      */     }
/*      */     
/* 2283 */     if (Trace.isOn) {
/* 2284 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "addPhysicalMessage(RemoteTls,RemoteProxyMessage)");
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
/*      */   public void proxyMQGET(RemoteTls tls, MQMD msgDesc, MQGMO getMsgOpts, int bufferLength, byte[] buffer, Pint pDataLength, SpiGetOptions getData, Pint pCompCode, Pint pReason) throws JmqiException {
/* 2311 */     if (Trace.isOn) {
/* 2312 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "proxyMQGET(RemoteTls,MQMD,MQGMO,int,byte [ ],Pint,SpiGetOptions,Pint,Pint)", new Object[] { tls, msgDesc, getMsgOpts, 
/*      */             
/* 2314 */             Integer.valueOf(bufferLength), buffer, pDataLength, getData, pCompCode, pReason });
/*      */     }
/*      */     
/* 2317 */     boolean makeRequest = false;
/* 2318 */     boolean timedOut = false;
/* 2319 */     boolean requestMade = false;
/* 2320 */     boolean doWait = true;
/* 2321 */     boolean locked = false;
/* 2322 */     int maxMsgLen = bufferLength;
/* 2323 */     int waitInterval = getMsgOpts.getWaitInterval();
/*      */     
/* 2325 */     pCompCode.x = 0;
/* 2326 */     pReason.x = 0;
/*      */     
/*      */     try {
/* 2329 */       if (getData != null) {
/* 2330 */         int validOptions = 128;
/* 2331 */         if ((getData.getOptions() & (validOptions ^ 0xFFFFFFFF)) != 0) {
/* 2332 */           pCompCode.x = 2;
/* 2333 */           pReason.x = 2298;
/*      */           
/* 2335 */           if (Trace.isOn) {
/* 2336 */             Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "proxyMQGET(RemoteTls,MQMD,MQGMO,int,byte [ ],Pint,SpiGetOptions,Pint,Pint)", 1);
/*      */           }
/*      */ 
/*      */           
/*      */           return;
/*      */         } 
/*      */         
/* 2343 */         if ((getData.getOptions() & 0x80) != 0)
/*      */         {
/*      */ 
/*      */           
/* 2347 */           maxMsgLen = -1;
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2356 */       if ((this.status & 0x400) != 0) {
/* 2357 */         if (waitInterval == 0) {
/* 2358 */           waitInterval = 10;
/*      */         
/*      */         }
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 2365 */         this.appMD = msgDesc;
/* 2366 */         this.appGmo = getMsgOpts;
/* 2367 */         this.appBufferLength = bufferLength;
/* 2368 */         this.appBuffer = buffer;
/* 2369 */         this.appDataLength = pDataLength;
/* 2370 */         this.appCompCode = pCompCode;
/* 2371 */         this.appReason = pReason;
/* 2372 */         this.appBufferUsed = 0;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2378 */       if ((getMsgOpts.getOptions() & 0x1) != 0) {
/* 2379 */         if (waitInterval < -1) {
/* 2380 */           pCompCode.x = 2;
/* 2381 */           pReason.x = 2090;
/* 2382 */           if (Trace.isOn) {
/* 2383 */             Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "proxyMQGET(RemoteTls,MQMD,MQGMO,int,byte [ ],Pint,SpiGetOptions,Pint,Pint)", 2);
/*      */           }
/*      */           
/*      */           return;
/*      */         } 
/*      */         
/* 2389 */         doWait = (waitInterval != 0);
/*      */       } else {
/*      */         
/* 2392 */         doWait = false;
/*      */       } 
/*      */       
/* 2395 */       if (bufferLength < 0) {
/* 2396 */         pCompCode.x = 2;
/* 2397 */         pReason.x = 2005;
/* 2398 */         if (Trace.isOn) {
/* 2399 */           Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "proxyMQGET(RemoteTls,MQMD,MQGMO,int,byte [ ],Pint,SpiGetOptions,Pint,Pint)", 3);
/*      */         }
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/* 2405 */       if (bufferLength > this.hconn.getMaximumMessageLength()) {
/* 2406 */         pCompCode.x = 2;
/* 2407 */         pReason.x = 2010;
/* 2408 */         pDataLength.x = (int)this.hconn.getMaximumMessageLength();
/* 2409 */         if (Trace.isOn) {
/* 2410 */           Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "proxyMQGET(RemoteTls,MQMD,MQGMO,int,byte [ ],Pint,SpiGetOptions,Pint,Pint)", 4);
/*      */         }
/*      */ 
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/* 2417 */       requestMutex(tls);
/*      */       
/* 2419 */       locked = true;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2424 */       synchronized (this.statusSync) {
/* 2425 */         this.status |= 0x6000000;
/*      */       } 
/* 2427 */       if (Trace.isOn) {
/* 2428 */         Trace.data(this, "proxyMQGET(RemoteTls,MQMD,MQGMO,int,byte [ ],Pint,SpiGetOptions,Pint,Pint)", "RPQ status changed to", decodeStatusNoTrace());
/*      */       }
/*      */ 
/*      */       
/* 2432 */       checkGetMsgOptions(tls, false, msgDesc, getMsgOpts);
/*      */ 
/*      */       
/* 2435 */       if ((this.status & 0x1) != 0) {
/* 2436 */         pCompCode.x = 2;
/* 2437 */         pReason.x = 2101;
/*      */         
/* 2439 */         if (Trace.isOn) {
/* 2440 */           Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "proxyMQGET(RemoteTls,MQMD,MQGMO,int,byte [ ],Pint,SpiGetOptions,Pint,Pint)", 5);
/*      */         }
/*      */ 
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/* 2447 */       if ((getMsgOpts.getOptions() & 0x2000) != 0) {
/* 2448 */         if ((this.status & 0x100) != 0) {
/* 2449 */           pCompCode.x = 2;
/* 2450 */           pReason.x = 2161;
/* 2451 */           if (Trace.isOn) {
/* 2452 */             Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "proxyMQGET(RemoteTls,MQMD,MQGMO,int,byte [ ],Pint,SpiGetOptions,Pint,Pint)", 6);
/*      */           }
/*      */           
/*      */           return;
/*      */         } 
/*      */         
/* 2458 */         if (this.hconn.isQuiescing()) {
/* 2459 */           pCompCode.x = 2;
/* 2460 */           pReason.x = 2202;
/* 2461 */           if (Trace.isOn) {
/* 2462 */             Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "proxyMQGET(RemoteTls,MQMD,MQGMO,int,byte [ ],Pint,SpiGetOptions,Pint,Pint)", 7);
/*      */           }
/*      */ 
/*      */           
/*      */           return;
/*      */         } 
/*      */       } 
/*      */       
/* 2470 */       if ((this.status & 0x10000000) != 0 && !this.hconn.inTransaction()) {
/* 2471 */         switch (getMsgOpts.getOptions() & 0x1006) {
/*      */           case 2:
/* 2473 */             this.hconn.setInTransaction();
/*      */             break;
/*      */           case 4096:
/* 2476 */             if (msgDesc.getPersistence() == 1) {
/* 2477 */               this.hconn.setInTransaction();
/*      */             }
/*      */             break;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/* 2487 */       checkSelectionCriteria(tls, msgDesc, getMsgOpts, maxMsgLen);
/*      */ 
/*      */       
/* 2490 */       if ((getMsgOpts.getOptions() & 0x800) != 0 && (this.status & 0x400) != 0) {
/* 2491 */         if (this.lastBrowsed == null) {
/* 2492 */           pCompCode.x = 2;
/* 2493 */           pReason.x = 2034;
/* 2494 */           if (Trace.isOn) {
/* 2495 */             Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "proxyMQGET(RemoteTls,MQMD,MQGMO,int,byte [ ],Pint,SpiGetOptions,Pint,Pint)", 8);
/*      */           }
/*      */ 
/*      */           
/*      */           return;
/*      */         } 
/*      */         
/* 2502 */         copyOutMessage(tls, this.lastBrowsed, msgDesc, getMsgOpts, bufferLength, buffer, pDataLength, pCompCode, pReason);
/*      */         
/* 2504 */         if (Trace.isOn) {
/* 2505 */           Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "proxyMQGET(RemoteTls,MQMD,MQGMO,int,byte [ ],Pint,SpiGetOptions,Pint,Pint)", 9);
/*      */         }
/*      */ 
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/* 2512 */       if ((getMsgOpts.getOptions() & 0x10) != 0 && (
/* 2513 */         getMsgOpts.getOptions() & 0x300000) == 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2519 */         this.getMsgOptions |= 0x20;
/* 2520 */         resetBrowseFirst(tls);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*      */       while (true) {
/* 2526 */         if (this.queueTop != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2531 */           RemoteProxyMessage message = findNextMessage(tls, maxMsgLen, true);
/*      */ 
/*      */           
/* 2534 */           if (message != null) {
/* 2535 */             copyOutMessage(tls, message, msgDesc, getMsgOpts, bufferLength, buffer, pDataLength, pCompCode, pReason);
/*      */ 
/*      */             
/* 2538 */             if (Trace.isOn) {
/* 2539 */               Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "proxyMQGET(RemoteTls,MQMD,MQGMO,int,byte [ ],Pint,SpiGetOptions,Pint,Pint)", 10);
/*      */             }
/*      */ 
/*      */             
/*      */             return;
/*      */           } 
/*      */         } 
/*      */         
/* 2547 */         if ((this.status & 0x80) != 0) {
/* 2548 */           pCompCode.x = 2;
/* 2549 */           if (isEmpty()) {
/* 2550 */             pReason.x = 2518;
/*      */           } else {
/*      */             
/* 2553 */             pReason.x = 2517;
/*      */           } 
/* 2555 */           if (Trace.isOn) {
/* 2556 */             Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "proxyMQGET(RemoteTls,MQMD,MQGMO,int,byte [ ],Pint,SpiGetOptions,Pint,Pint)", 11);
/*      */           }
/*      */ 
/*      */           
/*      */           return;
/*      */         } 
/*      */         
/* 2563 */         if ((this.status & 0x10000) != 0) {
/* 2564 */           makeRequest = true;
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 2569 */         if (!requestMade)
/*      */         {
/*      */ 
/*      */           
/* 2573 */           if (doWait) {
/*      */             
/* 2575 */             if ((this.status & 0x800) != 0) {
/* 2576 */               makeRequest = true;
/*      */             }
/* 2578 */             if ((this.status & 0x400) == 0) {
/* 2579 */               makeRequest = true;
/*      */             }
/* 2581 */             if ((this.status & 0x2000) != 0) {
/* 2582 */               makeRequest = true;
/*      */             }
/* 2584 */             if ((this.status & 0x40000000) != 0) {
/* 2585 */               makeRequest = true;
/*      */             }
/* 2587 */             if ((this.status & 0x8) != 0) {
/* 2588 */               makeRequest = true;
/*      */             }
/*      */ 
/*      */             
/* 2592 */             if (makeRequest) {
/* 2593 */               requestMessages(tls, false, getMsgOpts.getWaitInterval(), true, false);
/* 2594 */               requestMade = true;
/* 2595 */               this.lastCompCode = 0;
/*      */             } 
/*      */           } 
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2603 */         if ((this.status & 0x2000) != 0 && this.lastCompCode != 0) {
/* 2604 */           pCompCode.x = this.lastCompCode;
/* 2605 */           pReason.x = this.lastReason;
/* 2606 */           this.lastCompCode = 0;
/* 2607 */           if (Trace.isOn) {
/* 2608 */             Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "proxyMQGET(RemoteTls,MQMD,MQGMO,int,byte [ ],Pint,SpiGetOptions,Pint,Pint)", 12);
/*      */           }
/*      */ 
/*      */           
/*      */           return;
/*      */         } 
/*      */ 
/*      */         
/* 2616 */         if ((getMsgOpts.getOptions() & 0x2000) != 0) {
/* 2617 */           if ((this.status & 0x100) != 0) {
/* 2618 */             pCompCode.x = 2;
/* 2619 */             pReason.x = 2161;
/* 2620 */             if (Trace.isOn) {
/* 2621 */               Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "proxyMQGET(RemoteTls,MQMD,MQGMO,int,byte [ ],Pint,SpiGetOptions,Pint,Pint)", 13);
/*      */             }
/*      */             
/*      */             return;
/*      */           } 
/* 2626 */           if (this.hconn.isQuiescing()) {
/* 2627 */             pCompCode.x = 2;
/* 2628 */             pReason.x = 2202;
/* 2629 */             if (Trace.isOn) {
/* 2630 */               Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "proxyMQGET(RemoteTls,MQMD,MQGMO,int,byte [ ],Pint,SpiGetOptions,Pint,Pint)", 14);
/*      */             }
/*      */ 
/*      */             
/*      */             return;
/*      */           } 
/*      */         } 
/*      */         
/* 2638 */         if (timedOut) {
/* 2639 */           pCompCode.x = 2;
/* 2640 */           pReason.x = 2033;
/* 2641 */           if (Trace.isOn) {
/* 2642 */             Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "proxyMQGET(RemoteTls,MQMD,MQGMO,int,byte [ ],Pint,SpiGetOptions,Pint,Pint)", 15);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           return;
/*      */         } 
/*      */ 
/*      */         
/* 2651 */         if ((this.status & 0x8000000) != 0 && this.progressMsgIndex == 0) {
/* 2652 */           if (Trace.isOn) {
/* 2653 */             Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "proxyMQGET(RemoteTls,MQMD,MQGMO,int,byte [ ],Pint,SpiGetOptions,Pint,Pint)", 16);
/*      */           }
/*      */ 
/*      */           
/*      */           return;
/*      */         } 
/*      */ 
/*      */         
/* 2661 */         if (doWait) {
/*      */ 
/*      */           
/* 2664 */           if (this.queueTop != null) {
/*      */             continue;
/*      */           }
/*      */           
/* 2668 */           synchronized (this.statusSync) {
/* 2669 */             this.status &= 0xFFFFFFFB;
/* 2670 */             this.status |= 0x2;
/*      */           } 
/* 2672 */           if (Trace.isOn) {
/* 2673 */             Trace.data(this, "proxyMQGET(RemoteTls,MQMD,MQGMO,int,byte [ ],Pint,SpiGetOptions,Pint,Pint)", "RPQ status changed to", decodeStatusNoTrace());
/*      */           }
/*      */ 
/*      */           
/* 2677 */           releaseMutex();
/* 2678 */           locked = false;
/*      */ 
/*      */ 
/*      */           
/*      */           try {
/* 2683 */             synchronized (this.getterEventMonitor) {
/* 2684 */               Trace.data(this, "proxyMQGET(RemoteTls,MQMD,MQGMO,int,byte [ ],Pint,SpiGetOptions,Pint,Pint)", "status", Integer.valueOf(this.status));
/* 2685 */               if (this.connectionFailure == null && (
/* 2686 */                 this.status & 0x4) == 0) {
/* 2687 */                 if (waitInterval > 0) {
/* 2688 */                   this.getterEventMonitor.wait(waitInterval);
/*      */                 }
/*      */                 else {
/*      */                   
/* 2692 */                   this.getterEventMonitor.wait();
/*      */                 }
/*      */               
/*      */               }
/*      */             }
/*      */           
/* 2698 */           } catch (InterruptedException e) {
/* 2699 */             if (Trace.isOn) {
/* 2700 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "proxyMQGET(RemoteTls,MQMD,MQGMO,int,byte [ ],Pint,SpiGetOptions,Pint,Pint)", e);
/*      */             }
/*      */ 
/*      */ 
/*      */             
/* 2705 */             HashMap<String, Object> info = new HashMap<>();
/* 2706 */             info.put("Description", "Thread interrupted while waiting for lock");
/* 2707 */             Trace.ffst(this, "proxyMQGET(RemoteTls,MQMD,MQGMO,int,byte [ ],Pint,SpiGetOptions,Pint,Pint)", "01", info, null);
/*      */             
/* 2709 */             pCompCode.x = 2;
/* 2710 */             pReason.x = 2195;
/* 2711 */             if (Trace.isOn) {
/* 2712 */               Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "proxyMQGET(RemoteTls,MQMD,MQGMO,int,byte [ ],Pint,SpiGetOptions,Pint,Pint)", 17);
/*      */             }
/*      */             
/*      */             return;
/*      */           } 
/*      */           
/* 2718 */           if (this.connectionFailure != null) {
/*      */             
/* 2720 */             JmqiException jmqiExc = new JmqiException(this.env, 9213, null, 2, 2009, this.connectionFailure);
/* 2721 */             this.connectionFailure = null;
/*      */             
/* 2723 */             if (Trace.isOn) {
/* 2724 */               Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "proxyMQGET(RemoteTls,MQMD,MQGMO,int,byte [ ],Pint,SpiGetOptions,Pint,Pint)", (Throwable)jmqiExc);
/*      */             }
/*      */ 
/*      */             
/* 2728 */             throw jmqiExc;
/*      */           } 
/*      */ 
/*      */           
/* 2732 */           requestMutex(tls);
/* 2733 */           locked = true;
/*      */ 
/*      */           
/* 2736 */           if ((this.status & 0x4) != 0)
/*      */           {
/*      */ 
/*      */             
/* 2740 */             synchronized (this.statusSync) {
/* 2741 */               this.status &= 0xFFFFFFFB;
/*      */             } 
/* 2743 */             if (Trace.isOn) {
/* 2744 */               Trace.data(this, "proxyMQGET(RemoteTls,MQMD,MQGMO,int,byte [ ],Pint,SpiGetOptions,Pint,Pint)", "RPQ status changed to", decodeStatusNoTrace());
/*      */             }
/*      */           }
/*      */           else
/*      */           {
/* 2749 */             timedOut = true;
/*      */ 
/*      */           
/*      */           }
/*      */ 
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */           
/* 2759 */           timedOut = true;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 2764 */         if ((getMsgOpts.getOptions() & 0x2000) != 0) {
/* 2765 */           if ((this.status & 0x100) != 0) {
/* 2766 */             pCompCode.x = 2;
/* 2767 */             pReason.x = 2161;
/* 2768 */             if (Trace.isOn) {
/* 2769 */               Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "proxyMQGET(RemoteTls,MQMD,MQGMO,int,byte [ ],Pint,SpiGetOptions,Pint,Pint)", 18);
/*      */             }
/*      */             
/*      */             return;
/*      */           } 
/* 2774 */           if (this.hconn.isQuiescing()) {
/* 2775 */             pCompCode.x = 2;
/* 2776 */             pReason.x = 2202;
/* 2777 */             if (Trace.isOn) {
/* 2778 */               Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "proxyMQGET(RemoteTls,MQMD,MQGMO,int,byte [ ],Pint,SpiGetOptions,Pint,Pint)", 19);
/*      */             }
/*      */ 
/*      */             
/*      */             return;
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/* 2787 */         if (timedOut) {
/* 2788 */           flushQueue(tls, true);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 2793 */         if ((this.status & 0x8) != 0) {
/* 2794 */           pCompCode.x = 2;
/* 2795 */           pReason.x = 2016;
/*      */           
/*      */           break;
/*      */         } 
/*      */         
/* 2800 */         if ((this.status & 0x8000000) != 0 && this.progressMsgIndex == 0) {
/*      */           break;
/*      */         }
/*      */ 
/*      */         
/* 2805 */         synchronized (this.statusSync) {
/* 2806 */           this.status &= 0xFFFFFFFD;
/* 2807 */           this.status &= 0xFFFFFFFB;
/*      */         } 
/* 2809 */         if (Trace.isOn) {
/* 2810 */           Trace.data(this, "proxyMQGET(RemoteTls,MQMD,MQGMO,int,byte [ ],Pint,SpiGetOptions,Pint,Pint)", "RPQ status changed to", decodeStatusNoTrace());
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2817 */         if ((this.status & 0x40000000) != 0) {
/* 2818 */           requestMade = false;
/*      */         }
/*      */       }
/*      */     
/*      */     } finally {
/*      */       
/* 2824 */       if (Trace.isOn) {
/* 2825 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "proxyMQGET(RemoteTls,MQMD,MQGMO,int,byte [ ],Pint,SpiGetOptions,Pint,Pint)");
/*      */       }
/*      */ 
/*      */       
/* 2829 */       this.status &= 0xF3FFFFFF;
/* 2830 */       if (Trace.isOn) {
/* 2831 */         Trace.data(this, "proxyMQGET(RemoteTls,MQMD,MQGMO,int,byte [ ],Pint,SpiGetOptions,Pint,Pint)", "RPQ status changed to", decodeStatusNoTrace());
/*      */       }
/*      */       
/* 2834 */       if (locked) {
/* 2835 */         releaseMutex();
/*      */       }
/*      */     } 
/*      */     
/* 2839 */     if (Trace.isOn) {
/* 2840 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "proxyMQGET(RemoteTls,MQMD,MQGMO,int,byte [ ],Pint,SpiGetOptions,Pint,Pint)", 20);
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
/*      */   public void proxyMQCLOSE(RemoteTls tls, int closeOptions, Pint pCompCode, Pint pReason) throws JmqiException {
/* 2858 */     if (Trace.isOn) {
/* 2859 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "proxyMQCLOSE(RemoteTls,int,Pint,Pint)", new Object[] { tls, 
/* 2860 */             Integer.valueOf(closeOptions), pCompCode, pReason });
/*      */     }
/*      */ 
/*      */     
/* 2864 */     pCompCode.x = 0;
/* 2865 */     pReason.x = 0;
/* 2866 */     RemoteSession session = this.hconn.getSession(this);
/* 2867 */     boolean swap = session.isSwap();
/* 2868 */     boolean quiescing = ((closeOptions & 0x20) != 0);
/* 2869 */     if (this.hobj != null) {
/*      */       
/* 2871 */       if (quiescing) {
/*      */         
/* 2873 */         if ((this.status & 0x80) != 0)
/*      */         {
/*      */ 
/*      */           
/* 2877 */           if (!isEmpty()) {
/* 2878 */             pCompCode.x = 1;
/* 2879 */             pReason.x = 2458;
/*      */             
/* 2881 */             if (Trace.isOn) {
/* 2882 */               Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "proxyMQCLOSE(RemoteTls,int,Pint,Pint)", 1);
/*      */             }
/*      */             
/*      */             return;
/*      */           } 
/*      */         }
/* 2888 */         synchronized (this.statusSync) {
/* 2889 */           this.status |= 0x80;
/*      */ 
/*      */           
/* 2892 */           this.status |= 0x1000000;
/*      */         } 
/* 2894 */         if (Trace.isOn) {
/* 2895 */           Trace.data(this, "proxyMQCLOSE(RemoteTls,int,Pint,Pint)", "RPQ status changed to", decodeStatusNoTrace());
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 2900 */       RfpMQAPI sMQAPI = session.allocateMQAPI(132);
/* 2901 */       int writePos = sMQAPI.getRfpOffset() + sMQAPI.hdrSize();
/*      */       
/* 2903 */       sMQAPI.setControlFlags1(48);
/* 2904 */       sMQAPI.setHandle(this.hobj.getIntegerHandle(), swap);
/*      */       
/* 2906 */       RfpMQCLOSE mqCloseDetails = (RfpMQCLOSE)session.getRfp(5, (RfpStructure)sMQAPI, writePos);
/*      */       
/* 2908 */       writePos += 4;
/*      */       
/* 2910 */       mqCloseDetails.setOptions(closeOptions, swap);
/*      */       
/* 2912 */       int requestLength = writePos - sMQAPI.getRfpOffset();
/*      */       
/* 2914 */       sMQAPI.setTransLength(requestLength);
/* 2915 */       sMQAPI.setCallLength(requestLength);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2921 */       if (quiescing && !isEmpty()) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2926 */         sMQAPI.setLastReceivedIndex(0, swap);
/*      */       } else {
/*      */         
/* 2929 */         sMQAPI.setLastReceivedIndex(this.lastReceivedIndex, swap);
/*      */       } 
/*      */ 
/*      */       
/* 2933 */       session.sendTSH(tls, (RfpTSH)sMQAPI);
/*      */ 
/*      */       
/* 2936 */       RfpMQAPI rMQAPI = session.receiveMQIFlow(tls);
/*      */       
/*      */       try {
/* 2939 */         pCompCode.x = rMQAPI.getCompCode(swap);
/* 2940 */         pReason.x = rMQAPI.getReasonCode(swap);
/*      */         
/* 2942 */         if (rMQAPI.getSegmentType() != 148) {
/* 2943 */           HashMap<String, Object> info = new HashMap<>();
/* 2944 */           info.put("SegmentType", Integer.valueOf(rMQAPI.getSegmentType()));
/* 2945 */           info.put("ControlFlags1", Integer.valueOf(rMQAPI.getControlFlags1()));
/* 2946 */           info.put("Description", "Unexpected flow received from server");
/* 2947 */           Trace.ffst(this, "proxyMQCLOSE(RemoteTls,int,Pint,Pint)", "01", info, null);
/*      */           
/* 2949 */           JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*      */           
/* 2951 */           if (Trace.isOn) {
/* 2952 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "proxyMQCLOSE(RemoteTls,int,Pint,Pint)", (Throwable)traceRet1);
/*      */           }
/*      */           
/* 2955 */           throw traceRet1;
/*      */         }
/*      */       
/*      */       } finally {
/*      */         
/* 2960 */         if (Trace.isOn) {
/* 2961 */           Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "proxyMQCLOSE(RemoteTls,int,Pint,Pint)");
/*      */         }
/*      */         
/* 2964 */         session.releaseReceivedTSH((RfpTSH)rMQAPI);
/*      */       } 
/*      */       
/* 2967 */       if (pCompCode.x == 2) {
/* 2968 */         if (Trace.isOn) {
/* 2969 */           Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "proxyMQCLOSE(RemoteTls,int,Pint,Pint)", 2);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         return;
/*      */       } 
/*      */ 
/*      */       
/* 2978 */       if (quiescing && 
/* 2979 */         !isEmpty()) {
/* 2980 */         pCompCode.x = 1;
/* 2981 */         pReason.x = 2458;
/* 2982 */         if (Trace.isOn) {
/* 2983 */           Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "proxyMQCLOSE(RemoteTls,int,Pint,Pint)", 3);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         return;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 2993 */     synchronized (this.statusSync) {
/* 2994 */       this.status |= 0x20000000;
/*      */     } 
/* 2996 */     if (Trace.isOn) {
/* 2997 */       Trace.data(this, "proxyMQCLOSE(RemoteTls,int,Pint,Pint)", "RPQ status changed to", decodeStatusNoTrace());
/*      */     }
/*      */     
/* 3000 */     this.hconn.getProxyQueueManager().deleteProxyQueue(tls, this);
/*      */ 
/*      */     
/* 3003 */     if (isCbAlreadyRegistered()) {
/* 3004 */       mqcbDeRegisterMC(!tls.isDispatchThread);
/*      */     }
/*      */     
/* 3007 */     if (Trace.isOn) {
/* 3008 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "proxyMQCLOSE(RemoteTls,int,Pint,Pint)", 4);
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
/*      */   public void addMessage(RemoteTls tls, RfpTSH tsh, RfpASYNC_MESSAGE async, RemoteSession remoteSession) throws JmqiException {
/* 3026 */     if (Trace.isOn) {
/* 3027 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "addMessage(RemoteTls,RfpTSH,RfpASYNC_MESSAGE)", new Object[] { tls, tsh, async });
/*      */     }
/*      */     
/* 3030 */     boolean locked = false;
/* 3031 */     RemoteSession session = this.hconn.getSession(this);
/* 3032 */     String addingQueueManager = session.getParentConnection().getRemoteQmgrName();
/*      */     
/* 3034 */     if (session == remoteSession) {
/* 3035 */       boolean swap = session.isSwap();
/* 3036 */       JmqiCodepage cp = session.getCp();
/* 3037 */       JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/* 3038 */       JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*      */       
/* 3040 */       requestMutex(tls);
/* 3041 */       locked = true;
/* 3042 */       int mdSize = 0;
/* 3043 */       int readPos = 0;
/* 3044 */       int segmentIndex = async.getSegmentIndex(swap);
/* 3045 */       int segmentLength = async.getSegmentLength(swap);
/* 3046 */       MQMD msgDesc = null;
/*      */       try {
/* 3048 */         byte[] tshBuffer = async.getRfpBuffer();
/*      */         
/* 3050 */         if ((tsh.getControlFlags1() & 0x10) != 0) {
/*      */ 
/*      */ 
/*      */           
/* 3054 */           int qNameLen = async.getResolvedQNameLen();
/*      */ 
/*      */ 
/*      */           
/* 3058 */           readPos = async.getRfpOffset() + 55;
/* 3059 */           if (qNameLen != 0) {
/* 3060 */             if (qNameLen < 48) {
/*      */               
/* 3062 */               this.lastResolvedQName = dc.readField(tshBuffer, readPos, qNameLen - 1, cp, jTls);
/*      */             } else {
/*      */               
/* 3065 */               this.lastResolvedQName = dc.readField(tshBuffer, readPos, qNameLen, cp, jTls);
/*      */             } 
/*      */           }
/*      */           
/* 3069 */           readPos += (qNameLen + 2 & 0xFFFFFFFC) + 1;
/* 3070 */           msgDesc = this.env.newMQMD();
/*      */           
/* 3072 */           mdSize = msgDesc.readFromBuffer(tshBuffer, readPos, 4, swap, cp, jTls) - readPos;
/* 3073 */           readPos += mdSize;
/* 3074 */           if (Trace.isOn && msgDesc.getPersistence() == 1) {
/* 3075 */             Trace.data(this, "addMessage(RemoteTls,RfpTSH,RfpASYNC_MESSAGE)", "Persistent message first segment received", msgDesc.toString());
/*      */           }
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 3081 */         if (segmentIndex != this.progressMsgIndex) {
/* 3082 */           JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*      */           
/* 3084 */           if (Trace.isOn) {
/* 3085 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "addMessage(RemoteTls,RfpTSH,RfpASYNC_MESSAGE)", (Throwable)traceRet1, 1);
/*      */           }
/*      */           
/* 3088 */           throw traceRet1;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3094 */         this.progressMsgIndex++;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3100 */         short asyncStatus = async.getStatus(swap);
/*      */         
/* 3102 */         RfpMPH mph = null;
/* 3103 */         int mphLength = 0;
/*      */ 
/*      */         
/* 3106 */         if ((asyncStatus & 0x40) != 0 && (tsh.getControlFlags1() & 0x10) != 0) {
/*      */           
/* 3108 */           int messagePropertiesLength = 0;
/*      */ 
/*      */           
/* 3111 */           mph = (RfpMPH)RfpStructure.newRfp(this.env, 22, tshBuffer, readPos);
/* 3112 */           messagePropertiesLength = mph.getMPHLength(4, swap, cp, jTls);
/*      */ 
/*      */           
/* 3115 */           int headerSize = mph.getHeaderSizeV1();
/* 3116 */           mphLength = messagePropertiesLength - headerSize;
/* 3117 */           readPos += headerSize;
/*      */ 
/*      */           
/* 3120 */           int previousTotalMsgLength = async.getTotalMsgLength(swap);
/* 3121 */           async.setTotalMsgLength(previousTotalMsgLength + mphLength, swap);
/*      */           
/* 3123 */           int previousActualMsgLength = async.getActualMsgLength(swap);
/* 3124 */           async.setActualMsgLength(previousActualMsgLength + mphLength, swap);
/*      */           
/* 3126 */           int previousSegmentLength = segmentLength;
/* 3127 */           segmentLength = Math.min(previousSegmentLength + mphLength, tshBuffer.length - readPos);
/* 3128 */           async.setSegmentLength(segmentLength, swap);
/*      */           
/* 3130 */           if (Trace.isOn) {
/* 3131 */             Trace.data(this, "addMessage(RemoteTls,RfpTSH,RfpASYNC_MESSAGE)", "rfpMS_MSG_PROPERTIES_RETURNED true. MPH length (excl. header size)", Integer.valueOf(mphLength));
/*      */           }
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3142 */         synchronized (this.statusSync) {
/* 3143 */           if ((this.status & 0x4000000) != 0 && (this.status & 0x400) == 0 && (tsh
/* 3144 */             .getControlFlags1() & 0x10) != 0 && async
/* 3145 */             .getTotalMsgLength(swap) <= this.appBufferLength) {
/* 3146 */             this.status |= 0x8000000;
/* 3147 */             if (Trace.isOn) {
/* 3148 */               Trace.data(this, "addMessage(RemoteTls,RfpTSH,RfpASYNC_MESSAGE)", "RPQ status changed to", decodeStatusNoTrace());
/*      */             }
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 3155 */         if ((this.status & 0x8000000) != 0) {
/* 3156 */           int size = 0;
/* 3157 */           if ((tsh.getControlFlags1() & 0x10) != 0) {
/*      */             
/* 3159 */             size = async.getTotalMsgLength(swap) + mdSize;
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 3164 */             this.firstSegmentIndicatedMessageSize = size;
/* 3165 */             this.firstSegmentCalculatedMphLength = mphLength;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 3172 */             copyMDToUserMD(this.appMD, msgDesc);
/* 3173 */             if (segmentLength > 0) {
/* 3174 */               size = Math.min(this.appBufferLength, segmentLength);
/*      */             } else {
/*      */               
/* 3177 */               size = 0;
/*      */             } 
/*      */             
/* 3180 */             if (async.getReasonCode(swap) != 0) {
/* 3181 */               this.appCompCode.x = 1;
/*      */             } else {
/*      */               
/* 3184 */               this.appCompCode.x = 0;
/*      */             } 
/* 3186 */             this.appReason.x = async.getReasonCode(swap);
/* 3187 */             this.appDataLength.x = async.getActualMsgLength(swap);
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 3192 */             this.appGmo.setResolvedQName(this.lastResolvedQName);
/*      */             
/* 3194 */             int appGmoVersion = this.appGmo.getVersion();
/* 3195 */             if (appGmoVersion >= 2)
/*      */             {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 3202 */               this.appGmo.setGroupStatus(32);
/* 3203 */               if ((asyncStatus & 0x10) != 0) {
/* 3204 */                 this.appGmo.setGroupStatus(71);
/*      */               }
/* 3206 */               if ((asyncStatus & 0x20) != 0) {
/* 3207 */                 this.appGmo.setGroupStatus(76);
/*      */               }
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 3213 */               this.appGmo.setSegmentStatus(32);
/*      */               
/* 3215 */               if ((asyncStatus & 0x4) != 0) {
/* 3216 */                 this.appGmo.setSegmentStatus(83);
/*      */               }
/* 3218 */               if ((asyncStatus & 0x8) != 0) {
/* 3219 */                 this.appGmo.setSegmentStatus(76);
/*      */               }
/*      */ 
/*      */               
/* 3223 */               this.appGmo.setSegmentation(32);
/* 3224 */               if ((asyncStatus & 0x2) != 0) {
/* 3225 */                 this.appGmo.setSegmentation(65);
/*      */               }
/*      */               
/* 3228 */               if (appGmoVersion >= 3) {
/* 3229 */                 async.getMsgToken(this.appGmo.getMsgToken());
/*      */                 
/* 3231 */                 this.appGmo.setReturnedLength(async.getTotalMsgLength(swap));
/*      */               }
/*      */             
/*      */             }
/*      */           
/*      */           } else {
/*      */             
/* 3238 */             size = this.appBufferLength - this.appBufferUsed;
/* 3239 */             if (size > 0 && segmentLength > 0) {
/* 3240 */               size = Math.min(size, segmentLength);
/*      */             } else {
/*      */               
/* 3243 */               size = 0;
/*      */             } 
/* 3245 */             readPos = async.getRfpOffset() + 24;
/*      */           } 
/* 3247 */           System.arraycopy(tshBuffer, readPos, this.appBuffer, this.appBufferUsed, size);
/* 3248 */           this.appBufferUsed += size;
/*      */           
/* 3250 */           if ((tsh.getControlFlags1() & 0x20) != 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 3256 */             this.receivedBytes += this.firstSegmentIndicatedMessageSize - this.firstSegmentCalculatedMphLength;
/* 3257 */             this.queueManagerBytesReceivedFrom = addingQueueManager;
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 3262 */             session.setGlobalMessageIndex(async.getGlobalMessageIndex(swap));
/* 3263 */             this.progressMsgIndex = 0;
/*      */             
/* 3265 */             this.lastReceivedIndex = async.getMessageIndex(swap);
/*      */             
/* 3267 */             Trace.data(this, "addMessage(RemoteTls,RfpTSH,RfpASYNC_MESSAGE)", "status", Integer.valueOf(this.status));
/*      */             
/* 3269 */             if ((this.status & 0x2) != 0 && (this.status & 0x4) == 0)
/*      */             {
/* 3271 */               synchronized (this.getterEventMonitor) {
/*      */                 
/* 3273 */                 synchronized (this.statusSync) {
/* 3274 */                   this.status |= 0x4;
/* 3275 */                   if (Trace.isOn) {
/* 3276 */                     Trace.data(this, "addMessage(RemoteTls,RfpTSH,RfpASYNC_MESSAGE)", "RPQ status changed to", decodeStatusNoTrace());
/*      */                   }
/*      */                 } 
/* 3279 */                 this.getterEventMonitor.notifyAll();
/*      */               } 
/*      */             }
/*      */           } 
/*      */           
/* 3284 */           if (Trace.isOn) {
/* 3285 */             Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "addMessage(RemoteTls,RfpTSH,RfpASYNC_MESSAGE)", 1);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           return;
/*      */         } 
/*      */ 
/*      */         
/* 3294 */         if ((tsh.getControlFlags1() & 0x10) != 0) {
/*      */           
/* 3296 */           if (segmentIndex != 0) {
/* 3297 */             HashMap<String, Object> info = new HashMap<>();
/* 3298 */             info.put("Description", "Segment index nonzero");
/* 3299 */             Trace.ffst(this, "addMessage(RemoteTls,RfpTSH,RfpASYNC_MESSAGE)", "01", info, null);
/* 3300 */             JmqiException traceRet2 = new JmqiException(this.env, -1, null, 2, 2195, null);
/* 3301 */             if (Trace.isOn) {
/* 3302 */               Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "addMessage(RemoteTls,RfpTSH,RfpASYNC_MESSAGE)", (Throwable)traceRet2, 2);
/*      */             }
/*      */             
/* 3305 */             throw traceRet2;
/*      */           } 
/*      */ 
/*      */           
/* 3309 */           RemoteProxyMessage message = allocMessage(tls, async.getTotalMsgLength(swap));
/*      */ 
/*      */           
/* 3312 */           async.getMsgToken(message.getMsgToken());
/*      */           
/* 3314 */           message.setMsgDescByteSize(mdSize);
/* 3315 */           message.setMsgDesc(msgDesc);
/* 3316 */           message.setMessagePropertiesLength(mphLength);
/*      */ 
/*      */           
/* 3319 */           System.arraycopy(tshBuffer, readPos, message.getMsgData(), 0, segmentLength);
/*      */ 
/*      */           
/* 3322 */           message.setActualMsgLength(async.getActualMsgLength(swap));
/* 3323 */           message.setMsgLength(async.getTotalMsgLength(swap));
/* 3324 */           message.setSelectionIndex(segmentIndex);
/* 3325 */           message.setReason(async.getReasonCode(swap));
/* 3326 */           message.setStatus(async.getStatus(swap));
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 3331 */           message.setResolvedQName(this.lastResolvedQName);
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 3336 */           if (this.purgeTime > 0) {
/* 3337 */             message.setAddedTime(getTimeInSeconds());
/*      */ 
/*      */             
/* 3340 */             if (this.queueOldest != null)
/*      */             {
/* 3342 */               if (message.getAddedTime() >= this.lastCheckPurge + this.purgeTime) {
/* 3343 */                 checkPurge(tls, message.getAddedTime());
/*      */               }
/*      */             }
/*      */           } else {
/*      */             
/* 3348 */             message.setAddedTime(0L);
/*      */           } 
/*      */           
/* 3351 */           if (message.getReason() != 0) {
/* 3352 */             message.setCompCode(1);
/*      */           } else {
/*      */             
/* 3355 */             message.setCompCode(0);
/*      */           } 
/* 3357 */           message.setType(1);
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 3362 */           if ((this.status & 0x10000000) == 268435456) {
/* 3363 */             String remoteProductId; switch (this.getMsgOptions & 0x1006) {
/*      */               case 0:
/* 3365 */                 remoteProductId = this.hconn.getParentConnection(this).getRemoteProductId();
/* 3366 */                 if (remoteProductId != null && remoteProductId.startsWith("MQMV")) {
/* 3367 */                   message.setTransactional();
/*      */                 }
/*      */                 break;
/*      */               case 2:
/* 3371 */                 message.setTransactional();
/*      */                 break;
/*      */               case 4096:
/* 3374 */                 if (message.isPersistent()) {
/* 3375 */                   message.setTransactional();
/*      */                 }
/*      */                 break;
/*      */             } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           } 
/* 3386 */           this.progressMsg = message;
/* 3387 */           this.progressMsgOffset = segmentLength;
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 3392 */           if ((async.getStatus(swap) & 0x1) != 0) {
/* 3393 */             synchronized (this.statusSync) {
/* 3394 */               this.status |= 0x2000;
/* 3395 */               if (Trace.isOn) {
/* 3396 */                 Trace.data(this, "addMessage(RemoteTls,RfpTSH,RfpASYNC_MESSAGE)", "RPQ status changed to", decodeStatusNoTrace());
/*      */               }
/*      */             } 
/*      */ 
/*      */ 
/*      */             
/* 3402 */             if (message.getReason() != 0)
/*      */             {
/*      */               
/* 3405 */               if (message.getReason() != 2079) {
/* 3406 */                 synchronized (this.statusSync) {
/* 3407 */                   this.status |= 0x8000;
/* 3408 */                   if (Trace.isOn) {
/* 3409 */                     Trace.data(this, "addMessage(RemoteTls,RfpTSH,RfpASYNC_MESSAGE)", "RPQ status changed to", decodeStatusNoTrace());
/*      */                   }
/*      */                 } 
/*      */               }
/*      */             }
/*      */           } else {
/*      */             
/* 3416 */             synchronized (this.statusSync) {
/* 3417 */               this.status &= 0xBFFF5FFF;
/* 3418 */               if (Trace.isOn) {
/* 3419 */                 Trace.data(this, "addMessage(RemoteTls,RfpTSH,RfpASYNC_MESSAGE)", "RPQ status changed to", decodeStatusNoTrace());
/*      */               }
/*      */             }
/*      */           
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/* 3427 */           if (this.progressMsg == null) {
/* 3428 */             HashMap<String, Object> info = new HashMap<>();
/* 3429 */             info.put("Description", "No message in progress");
/* 3430 */             Trace.ffst(this, "addMessage(RemoteTls,RfpTSH,RfpASYNC_MESSAGE)", "01", info, null);
/* 3431 */             JmqiException traceRet3 = new JmqiException(this.env, -1, null, 2, 2195, null);
/* 3432 */             if (Trace.isOn) {
/* 3433 */               Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "addMessage(RemoteTls,RfpTSH,RfpASYNC_MESSAGE)", (Throwable)traceRet3, 3);
/*      */             }
/*      */             
/* 3436 */             throw traceRet3;
/*      */           } 
/*      */ 
/*      */           
/* 3440 */           readPos = async.getRfpOffset() + 24;
/*      */           
/* 3442 */           System.arraycopy(tshBuffer, readPos, this.progressMsg.getMsgData(), this.progressMsgOffset, segmentLength);
/*      */ 
/*      */           
/* 3445 */           this.progressMsgOffset += segmentLength;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 3450 */         if ((tsh.getControlFlags1() & 0x20) != 0) {
/* 3451 */           RemoteProxyMessage message = this.progressMsg;
/*      */ 
/*      */           
/* 3454 */           int messageByteSize = message.getMsgLength() + message.getMsgDescByteSize() - message.getMessagePropertiesLength();
/*      */           
/* 3456 */           this.queueManagerBytesReceivedFrom = addingQueueManager;
/* 3457 */           this.progressMsg = null;
/* 3458 */           this.receivedBytes += messageByteSize;
/* 3459 */           this.currentBytes += messageByteSize;
/* 3460 */           this.progressMsgIndex = 0;
/*      */           
/* 3462 */           addPhysicalMessage(tls, message);
/*      */ 
/*      */           
/* 3465 */           session.setGlobalMessageIndex(async.getGlobalMessageIndex(swap));
/*      */ 
/*      */           
/* 3468 */           this.lastReceivedIndex = async.getMessageIndex(swap);
/*      */         }
/*      */       
/*      */       } finally {
/*      */         
/* 3473 */         if (Trace.isOn) {
/* 3474 */           Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "addMessage(RemoteTls,RfpTSH,RfpASYNC_MESSAGE)");
/*      */         }
/*      */         
/* 3477 */         if (locked) {
/* 3478 */           releaseMutex();
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 3483 */     if (Trace.isOn) {
/* 3484 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "addMessage(RemoteTls,RfpTSH,RfpASYNC_MESSAGE)", 2);
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
/*      */   public void callConsumer(int callType, int compCode, int reasonP) throws JmqiException {
/* 3498 */     if (Trace.isOn)
/* 3499 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "callConsumer(int,int,int)", new Object[] {
/* 3500 */             Integer.valueOf(callType), 
/* 3501 */             Integer.valueOf(compCode), Integer.valueOf(reasonP)
/*      */           }); 
/* 3503 */     int reason = reasonP;
/* 3504 */     if (this.mqcbCBD.getCallbackFunction() == null)
/*      */     {
/* 3506 */       this.mqcbCBD.setCallbackFunction((MQConsumer)JmqiUtils.loadAndInstantiateClass(this.env, this.mqcbCBD.getCallbackName()));
/*      */     }
/* 3508 */     this.callbackCBC.setHobj((Hobj)getHobj());
/* 3509 */     this.callbackCBC.setCallbackArea(this.mqcbCBD.getCallbackArea());
/* 3510 */     this.callbackCBC.setConnectionArea(this.hconn.getConnectionArea());
/*      */     
/* 3512 */     switch (callType) {
/*      */       case 1:
/* 3514 */         synchronized (this.statusSync) {
/* 3515 */           this.status |= 0x400000;
/* 3516 */           this.status &= 0xFF7FFFFF;
/* 3517 */           if (Trace.isOn) {
/* 3518 */             Trace.data(this, "callConsumer(int,int,int)", "RPQ status changed to", decodeStatusNoTrace());
/*      */           }
/*      */         } 
/*      */         
/* 3522 */         if ((this.mqcbCBD.getOptions() & 0x1) == 0) {
/*      */           
/* 3524 */           if (Trace.isOn) {
/* 3525 */             Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "callConsumer(int,int,int)", 1);
/*      */           }
/*      */           return;
/*      */         } 
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 2:
/* 3534 */         synchronized (this.statusSync) {
/* 3535 */           this.status |= 0x800000;
/* 3536 */           if (Trace.isOn) {
/* 3537 */             Trace.data(this, "callConsumer(int,int,int)", "RPQ status changed to", decodeStatusNoTrace());
/*      */           }
/*      */         } 
/*      */         break;
/*      */ 
/*      */       
/*      */       case 3:
/*      */       case 4:
/*      */       case 5:
/*      */         break;
/*      */ 
/*      */       
/*      */       default:
/* 3550 */         if (Trace.isOn) {
/* 3551 */           Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "callConsumer(int,int,int)", 2);
/*      */         }
/*      */         return;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3559 */     this.callbackCBC.setFlags(0);
/* 3560 */     this.callbackCBC.setCallType(callType);
/* 3561 */     this.callbackCBC.setState(0);
/*      */ 
/*      */     
/* 3564 */     if ((this.status & 0x80) != 0) {
/*      */       
/* 3566 */       if (isEmpty()) {
/* 3567 */         this.callbackCBC.setFlags(this.callbackCBC.getFlags() | 0x1);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3573 */       if (reason == 2033) {
/* 3574 */         if (isEmpty()) {
/* 3575 */           reason = 2518;
/*      */         } else {
/*      */           
/* 3578 */           reason = 2517;
/*      */         } 
/*      */       }
/*      */     } 
/* 3582 */     this.callbackCBC.setCompCode(compCode);
/* 3583 */     this.callbackCBC.setReason(reason);
/* 3584 */     this.callbackCBC.setDataLength(0);
/* 3585 */     this.callbackCBC.setBufferLength(0);
/*      */ 
/*      */     
/* 3588 */     if (reason != 0) {
/* 3589 */       switch (reason) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 2031:
/*      */         case 2033:
/*      */         case 2079:
/*      */         case 2109:
/*      */         case 2110:
/*      */         case 2111:
/*      */         case 2112:
/*      */         case 2113:
/*      */         case 2114:
/*      */         case 2115:
/*      */         case 2116:
/*      */         case 2117:
/*      */         case 2118:
/*      */         case 2119:
/*      */         case 2120:
/*      */         case 2143:
/*      */         case 2144:
/*      */         case 2145:
/*      */         case 2146:
/*      */         case 2150:
/*      */         case 2161:
/*      */         case 2190:
/*      */         case 2202:
/*      */         case 2209:
/*      */         case 2241:
/*      */         case 2242:
/*      */         case 2243:
/*      */         case 2244:
/*      */         case 2245:
/*      */         case 2272:
/*      */         case 2494:
/*      */         case 2517:
/*      */         case 2518:
/* 3627 */           this.callbackCBC.setState(0);
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 2016:
/* 3641 */           this.callbackCBC.setState(1);
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 2024:
/*      */         case 2034:
/*      */         case 2062:
/*      */         case 2080:
/*      */         case 2246:
/*      */         case 2255:
/*      */         case 2274:
/*      */         case 2297:
/*      */         case 2351:
/*      */         case 2352:
/*      */         case 2353:
/*      */         case 2354:
/*      */         case 2355:
/*      */         case 2374:
/* 3671 */           synchronized (this.statusSync) {
/* 3672 */             if (Trace.isOn) {
/* 3673 */               Trace.data(this, "callConsumer(int,int,int)", "Setting state suspended in callConsumer() - 1");
/*      */             }
/* 3675 */             this.status |= 0x40000;
/* 3676 */             if (Trace.isOn) {
/* 3677 */               Trace.data(this, "callConsumer(int,int,int)", "RPQ status changed to", decodeStatusNoTrace());
/*      */             }
/* 3679 */             this.callbackCBC.setState(2);
/*      */           } 
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 2041:
/*      */         case 2052:
/*      */         case 2071:
/*      */         case 2101:
/*      */         case 2102:
/*      */         case 2130:
/*      */         case 2133:
/*      */         case 2182:
/*      */         case 2183:
/*      */         case 2192:
/*      */         case 2193:
/*      */         case 2208:
/*      */         case 2232:
/*      */         case 2342:
/*      */         case 2345:
/*      */         case 2346:
/*      */         case 2347:
/*      */         case 2348:
/*      */         case 2349:
/*      */         case 2373:
/* 3717 */           synchronized (this.statusSync) {
/* 3718 */             if (Trace.isOn) {
/* 3719 */               Trace.data(this, "callConsumer(int,int,int)", "Setting state suspended in callConsumer() - 2");
/*      */             }
/* 3721 */             this.status |= 0x40000;
/* 3722 */             if (Trace.isOn) {
/* 3723 */               Trace.data(this, "callConsumer(int,int,int)", "RPQ status changed to", decodeStatusNoTrace());
/*      */             }
/* 3725 */             this.callbackCBC.setState(3);
/*      */           } 
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 2009:
/*      */         case 2162:
/*      */         case 2195:
/*      */         case 2203:
/*      */         case 2204:
/* 3746 */           synchronized (this.statusSync) {
/* 3747 */             if (Trace.isOn) {
/* 3748 */               Trace.data(this, "callConsumer(int,int,int)", "Setting state suspended in callConsumer() - 3");
/*      */             }
/* 3750 */             this.status |= 0x40000;
/* 3751 */             if (Trace.isOn) {
/* 3752 */               Trace.data(this, "callConsumer(int,int,int)", "RPQ status changed to", decodeStatusNoTrace());
/*      */             }
/* 3754 */             this.callbackCBC.setState(4);
/*      */           } 
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         default:
/* 3764 */           if (compCode == 2) {
/* 3765 */             synchronized (this.statusSync) {
/* 3766 */               if (Trace.isOn) {
/* 3767 */                 Trace.data(this, "callConsumer(int,int,int)", "Setting state suspended in callConsumer() - 4");
/*      */               }
/* 3769 */               this.status |= 0x40000;
/* 3770 */               if (Trace.isOn) {
/* 3771 */                 Trace.data(this, "callConsumer(int,int,int)", "RPQ status changed to", decodeStatusNoTrace());
/*      */               }
/* 3773 */               this.callbackCBC.setState(3);
/*      */             } 
/*      */           }
/*      */           break;
/*      */       } 
/*      */ 
/*      */     
/*      */     }
/* 3781 */     MQConsumer consumer = this.mqcbCBD.getCallbackFunction();
/*      */     
/*      */     try {
/* 3784 */       consumer.consumer((Hconn)this.hconn, null, null, null, this.callbackCBC);
/*      */     }
/* 3786 */     catch (Throwable throwable) {
/* 3787 */       if (Trace.isOn) {
/* 3788 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "callConsumer(int,int,int)", throwable);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3798 */       if (throwable instanceof Error) {
/* 3799 */         Error traceRet1 = (Error)throwable;
/* 3800 */         if (Trace.isOn) {
/* 3801 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "callConsumer(int,int,int)", traceRet1, 1);
/*      */         }
/*      */         
/* 3804 */         throw traceRet1;
/*      */       } 
/* 3806 */       if (throwable instanceof RuntimeException) {
/* 3807 */         RuntimeException traceRet2 = (RuntimeException)throwable;
/* 3808 */         if (Trace.isOn) {
/* 3809 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "callConsumer(int,int,int)", traceRet2, 2);
/*      */         }
/*      */         
/* 3812 */         throw traceRet2;
/*      */       } 
/*      */     } 
/* 3815 */     if (Trace.isOn) {
/* 3816 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "callConsumer(int,int,int)", 3);
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
/*      */   void receiveNotification(RemoteTls tls, RfpTSH tsh, RfpNOTIFICATION notification, RemoteSession remoteSession) throws JmqiException {
/* 3831 */     if (Trace.isOn) {
/* 3832 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "receiveNotification(RemoteTls,RfpTSH,RfpNOTIFICATION,RemoteSession)", new Object[] { tls, tsh, notification, remoteSession });
/*      */     }
/*      */ 
/*      */     
/* 3836 */     RemoteSession session = this.hconn.getSession(this);
/*      */     
/* 3838 */     if (session == remoteSession) {
/* 3839 */       boolean swap = session.isSwap();
/*      */       
/* 3841 */       boolean locked = false;
/* 3842 */       boolean wakeGetter = false;
/* 3843 */       boolean addToQueue = false;
/* 3844 */       requestMutex(tls);
/* 3845 */       locked = true; try {
/*      */         boolean isReconnecting; StringBuffer dumpBuffer;
/*      */         HashMap<String, Object> info;
/* 3848 */         switch (notification.getNotificationCode(swap)) {
/*      */           case 12:
/* 3850 */             synchronized (this.statusSync) {
/* 3851 */               this.status &= 0xFFFFEFFF;
/*      */             } 
/* 3853 */             if (Trace.isOn) {
/* 3854 */               Trace.data(this, "receiveNotification(RemoteTls,RfpTSH,RfpNOTIFICATION,RemoteSession)", "RPQ status changed to", decodeStatusNoTrace());
/*      */             }
/*      */             break;
/*      */           case 1:
/* 3858 */             synchronized (this.statusSync) {
/* 3859 */               this.status |= 0x8;
/*      */             } 
/* 3861 */             if (Trace.isOn) {
/* 3862 */               Trace.data(this, "receiveNotification(RemoteTls,RfpTSH,RfpNOTIFICATION,RemoteSession)", "RPQ status changed to", decodeStatusNoTrace());
/*      */             }
/* 3864 */             wakeGetter = true;
/* 3865 */             addToQueue = true;
/*      */             break;
/*      */           case 2:
/* 3868 */             synchronized (this.statusSync) {
/* 3869 */               this.status &= 0xFFFFFFF7;
/*      */             } 
/* 3871 */             if (Trace.isOn) {
/* 3872 */               Trace.data(this, "receiveNotification(RemoteTls,RfpTSH,RfpNOTIFICATION,RemoteSession)", "RPQ status changed to", decodeStatusNoTrace());
/*      */             }
/* 3874 */             wakeGetter = true;
/* 3875 */             addToQueue = true;
/*      */             break;
/*      */           case 7:
/* 3878 */             synchronized (this.statusSync) {
/* 3879 */               this.status |= 0x100;
/*      */             } 
/* 3881 */             if (Trace.isOn) {
/* 3882 */               Trace.data(this, "receiveNotification(RemoteTls,RfpTSH,RfpNOTIFICATION,RemoteSession)", "RPQ status changed to", decodeStatusNoTrace());
/*      */             }
/* 3884 */             this.hconn.setQuiescing();
/* 3885 */             wakeGetter = true;
/*      */             break;
/*      */           case 14:
/* 3888 */             synchronized (this.statusSync) {
/* 3889 */               this.status |= 0x2000;
/*      */             } 
/* 3891 */             if (Trace.isOn) {
/* 3892 */               Trace.data(this, "receiveNotification(RemoteTls,RfpTSH,RfpNOTIFICATION,RemoteSession)", "RPQ status changed to", decodeStatusNoTrace());
/*      */             }
/* 3894 */             this.lastCompCode = 2;
/* 3895 */             this.lastReason = notification.getValue(swap);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 3902 */             isReconnecting = (this.hconn.getRebalancingListener() == null && makeReconnectable(session));
/* 3903 */             if (!isReconnecting) {
/* 3904 */               RemoteConnection connection; switch (this.lastReason) {
/*      */                 
/*      */                 case 2009:
/* 3907 */                   this.hconn.raiseEventForReason(this.lastReason);
/*      */                   
/* 3909 */                   connection = this.hconn.getParentConnection(this);
/* 3910 */                   connection.asyncConnectionBroken(tls, null, null, null);
/*      */                   break;
/*      */ 
/*      */ 
/*      */                 
/*      */                 case 2373:
/* 3916 */                   addToQueue = true;
/*      */                   break;
/*      */               } 
/*      */ 
/*      */ 
/*      */             
/*      */             } 
/* 3923 */             wakeGetter = true;
/*      */             break;
/*      */           case 16:
/* 3926 */             synchronized (this.statusSync) {
/* 3927 */               this.status |= 0x40000000;
/*      */             } 
/* 3929 */             if (Trace.isOn) {
/* 3930 */               Trace.data(this, "receiveNotification(RemoteTls,RfpTSH,RfpNOTIFICATION,RemoteSession)", "RPQ status changed to", decodeStatusNoTrace());
/*      */             }
/* 3932 */             wakeGetter = true;
/*      */             break;
/*      */ 
/*      */           
/*      */           default:
/* 3937 */             dumpBuffer = new StringBuffer();
/* 3938 */             JmqiTools.hexDump(tsh.getRfpBuffer(), null, tsh.getRfpOffset(), tsh.getTransLength(), dumpBuffer);
/* 3939 */             info = new HashMap<>();
/* 3940 */             info.put("NotificationCode", Integer.valueOf(notification.getNotificationCode(swap)));
/* 3941 */             info.put("rfpBuffer", dumpBuffer);
/* 3942 */             info.put("Description", "Unexpected notification code");
/* 3943 */             Trace.ffst(this, "receiveNotification(RemoteTls,RfpTSH,RfpNOTIFICATION,RemoteSession)", "01", info, null); break;
/*      */         } 
/* 3945 */         if (addToQueue)
/*      */         {
/* 3947 */           RemoteProxyMessage message = allocMessage(tls, 24);
/* 3948 */           message.setType(0);
/* 3949 */           message.setCompCode(2);
/* 3950 */           message.setReason(notification.getValue(swap));
/* 3951 */           addPhysicalMessage(this.hconn.getParentConnection(this).getRemoteFap().getTls(), message);
/*      */         
/*      */         }
/* 3954 */         else if (wakeGetter)
/*      */         {
/* 3956 */           if ((this.status & 0x2) != 0 && (this.status & 0x4) == 0)
/*      */           {
/* 3958 */             synchronized (this.getterEventMonitor)
/*      */             {
/* 3960 */               synchronized (this.statusSync) {
/* 3961 */                 this.status |= 0x4;
/*      */               } 
/* 3963 */               if (Trace.isOn) {
/* 3964 */                 Trace.data(this, "receiveNotification(RemoteTls,RfpTSH,RfpNOTIFICATION,RemoteSession)", "RPQ status changed to", decodeStatusNoTrace());
/*      */               }
/* 3966 */               this.getterEventMonitor.notifyAll();
/*      */             }
/*      */           
/*      */           }
/*      */         }
/*      */       
/*      */       } finally {
/*      */         
/* 3974 */         if (Trace.isOn) {
/* 3975 */           Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "receiveNotification(RemoteTls,RfpTSH,RfpNOTIFICATION,RemoteSession)");
/*      */         }
/*      */         
/* 3978 */         if (locked) {
/* 3979 */           releaseMutex();
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 3984 */     if (Trace.isOn) {
/* 3985 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "receiveNotification(RemoteTls,RfpTSH,RfpNOTIFICATION,RemoteSession)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean makeReconnectable(RemoteSession session) throws JmqiException {
/* 3992 */     if (Trace.isOn) {
/* 3993 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "makeReconnectable(RemoteSession)", new Object[] { session });
/*      */     }
/*      */ 
/*      */     
/* 3997 */     boolean isReconnecting = false;
/*      */     
/* 3999 */     if (this.hconn.isReconnectable()) {
/* 4000 */       RemoteParentHconn remoteParentHconn; RemoteHconn controllingHconn = this.hconn;
/* 4001 */       if (RemoteHconn.isReconnectableReasonCode(this.lastReason)) {
/* 4002 */         if (this.hconn.getParent() != null) {
/* 4003 */           remoteParentHconn = this.hconn.getParent();
/*      */         }
/* 4005 */         remoteParentHconn.eligibleForReconnect(true);
/*      */       } 
/* 4007 */       isReconnecting = remoteParentHconn.isReconnecting();
/* 4008 */       if (remoteParentHconn.hasFailed()) {
/* 4009 */         this.lastCompCode = remoteParentHconn.getReconnectionFailureCompCode();
/* 4010 */         this.lastReason = remoteParentHconn.getReconnectionFailureReason();
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 4015 */     if (Trace.isOn) {
/* 4016 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "makeReconnectable(RemoteSession)", 
/* 4017 */           Boolean.valueOf(isReconnecting));
/*      */     }
/*      */     
/* 4020 */     return isReconnecting;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   RemoteHobj getHobj() {
/* 4030 */     if (Trace.isOn) {
/* 4031 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "getHobj()", "getter", this.hobj);
/*      */     }
/* 4033 */     return this.hobj;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQCBD getMqcbCBD() {
/* 4042 */     if (Trace.isOn) {
/* 4043 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "getMqcbCBD()", "getter", this.mqcbCBD);
/*      */     }
/*      */     
/* 4046 */     return this.mqcbCBD;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   MQGMO getMqcbGmo() {
/* 4055 */     if (Trace.isOn) {
/* 4056 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "getMqcbGmo()", "getter", this.mqcbGmo);
/*      */     }
/*      */     
/* 4059 */     return this.mqcbGmo;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getStatus() {
/* 4070 */     if (Trace.isOn) {
/* 4071 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "getStatus()", "getter", 
/* 4072 */           Integer.valueOf(this.status));
/*      */     }
/* 4074 */     return this.status;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setHconn(RemoteHconn hconn) {
/* 4083 */     if (Trace.isOn) {
/* 4084 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "setHconn(RemoteHconn)", "setter", hconn);
/*      */     }
/*      */     
/* 4087 */     this.hconn = hconn;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void sendConsumerState(RemoteTls tls, boolean reply) throws JmqiException {
/*      */     int clientAstate;
/* 4098 */     if (Trace.isOn) {
/* 4099 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "sendConsumerState(RemoteTls,boolean)", new Object[] { tls, 
/* 4100 */             Boolean.valueOf(reply) });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4106 */     if ((this.status & 0x20000000) != 0) {
/*      */       
/* 4108 */       if (Trace.isOn) {
/* 4109 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "sendConsumerState(RemoteTls,boolean)", 1);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */ 
/*      */     
/* 4118 */     if (!this.cbAlreadyRegistered || (this.status & 0x200000) != 0) {
/*      */       
/* 4120 */       clientAstate = 0;
/*      */     }
/* 4122 */     else if ((this.status & 0x40000) != 0) {
/*      */       
/* 4124 */       clientAstate = 17;
/*      */     } else {
/*      */       
/* 4127 */       clientAstate = 16;
/*      */     } 
/*      */     
/* 4130 */     this.hconn.sendNotification(getHobj().getIntegerHandle(), 5, clientAstate, reply);
/*      */     
/* 4132 */     if (Trace.isOn) {
/* 4133 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "sendConsumerState(RemoteTls,boolean)", 2);
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
/*      */   private void setAsyncSelection(RemoteTls tls) throws JmqiException {
/* 4146 */     if (Trace.isOn) {
/* 4147 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "setAsyncSelection(RemoteTls)", "setter", tls);
/*      */     }
/*      */ 
/*      */     
/* 4151 */     checkSelectionCriteria(tls, this.mqcbMD, this.mqcbGmo, this.mqcbCBD.getMaxMsgLength());
/* 4152 */     this.asyncSelectionIndex = this.selectionIndex;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EnumSet<RemoteConstants.Event> getEventsRaised() {
/* 4161 */     if (Trace.isOn) {
/* 4162 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "getEventsRaised()", "getter", this.eventsRaised);
/*      */     }
/*      */     
/* 4165 */     return this.eventsRaised;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void checkPurge(RemoteTls tls, long timeNow) throws JmqiException {
/* 4176 */     if (Trace.isOn) {
/* 4177 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "checkPurge(RemoteTls,long)", new Object[] { tls, 
/* 4178 */             Long.valueOf(timeNow) });
/*      */     }
/* 4180 */     RemoteProxyMessage message = this.queueOldest;
/*      */     
/* 4182 */     long lastPurged = timeNow - this.purgeTime;
/* 4183 */     this.lastCheckPurge = timeNow;
/* 4184 */     while (message != null) {
/*      */       
/* 4186 */       if (message == this.queueTop) {
/*      */         break;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 4192 */       if (message.getAddedTime() > lastPurged) {
/*      */         break;
/*      */       }
/*      */       
/* 4196 */       RemoteProxyMessage nextMessage = message.getNewer();
/* 4197 */       this.backlog -= (message.getMsgLength() + message.getMsgDescByteSize());
/* 4198 */       removeMessage(tls, false, false, message);
/* 4199 */       message = nextMessage;
/*      */     } 
/*      */     
/* 4202 */     if (Trace.isOn) {
/* 4203 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "checkPurge(RemoteTls,long)");
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
/*      */   private long getTimeInSeconds() {
/* 4215 */     long traceRet1 = System.currentTimeMillis() / 1000L;
/*      */     
/* 4217 */     if (Trace.isOn) {
/* 4218 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "getTimeInSeconds()", "getter", 
/* 4219 */           Long.valueOf(traceRet1));
/*      */     }
/* 4221 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   long getNoMsgTime() {
/* 4230 */     if (Trace.isOn) {
/* 4231 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "getNoMsgTime()", "getter", 
/* 4232 */           Long.valueOf(this.noMsgTime));
/*      */     }
/* 4234 */     return this.noMsgTime;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setNoMsgTime(long noMsgTime) {
/* 4243 */     if (Trace.isOn) {
/* 4244 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "setNoMsgTime(long)", "setter", 
/* 4245 */           Long.valueOf(noMsgTime));
/*      */     }
/* 4247 */     this.noMsgTime = noMsgTime;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isCbAlreadyRegistered() {
/* 4256 */     if (Trace.isOn) {
/* 4257 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "isCbAlreadyRegistered()", "getter", 
/* 4258 */           Boolean.valueOf(this.cbAlreadyRegistered));
/*      */     }
/* 4260 */     return this.cbAlreadyRegistered;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isEmpty() {
/* 4267 */     boolean retVal = (this.queueTop == null);
/* 4268 */     if (Trace.isOn) {
/* 4269 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "isEmpty()", "getter", 
/* 4270 */           Boolean.valueOf(retVal));
/*      */     }
/* 4272 */     return retVal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasPersistent() {
/* 4279 */     boolean retVal = false;
/* 4280 */     if (this.queueTop != null && this.queueTop.isPersistent()) {
/* 4281 */       retVal = true;
/*      */     }
/* 4283 */     if (Trace.isOn) {
/* 4284 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "hasPersistent()", "getter", 
/* 4285 */           Boolean.valueOf(retVal));
/*      */     }
/* 4287 */     return retVal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void copyMDToUserMD(MQMD userMd, MQMD rcvdMd) {
/* 4298 */     this.sysenv.conditionalCopyMQMD(rcvdMd, userMd);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static class GetterEventMonitor
/*      */   {
/*      */     GetterEventMonitor() {
/* 4310 */       if (Trace.isOn) {
/* 4311 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.GetterEventMonitor", "<init>()");
/*      */       }
/* 4313 */       if (Trace.isOn) {
/* 4314 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.GetterEventMonitor", "<init>()");
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
/*      */   private static class StatusSync
/*      */   {
/*      */     StatusSync() {
/* 4329 */       if (Trace.isOn) {
/* 4330 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.StatusSync", "<init>()");
/*      */       }
/* 4332 */       if (Trace.isOn) {
/* 4333 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.StatusSync", "<init>()");
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
/*      */   public void notifyConnectionFailure(Throwable e) {
/* 4347 */     if (Trace.isOn) {
/* 4348 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "notifyConnectionFailure(Throwable)", new Object[] { e });
/*      */     }
/*      */     
/* 4351 */     synchronized (this.getterEventMonitor) {
/* 4352 */       this.connectionFailure = e;
/* 4353 */       this.getterEventMonitor.notifyAll();
/*      */     } 
/*      */     
/* 4356 */     if (Trace.isOn) {
/* 4357 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "notifyConnectionFailure(Throwable)");
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
/*      */   public void resetForReconnect(RemoteTls tls) throws JmqiException {
/* 4371 */     if (Trace.isOn) {
/* 4372 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "resetForReconnect(RemoteTls)", new Object[] { tls });
/*      */     }
/*      */     
/* 4375 */     requestMutex(tls);
/* 4376 */     this.connectionFailure = null;
/*      */     try {
/* 4378 */       if (this.progressMsg != null) {
/* 4379 */         if (Trace.isOn) {
/* 4380 */           Trace.data(this, "resetForReconnect(RemoteTls)", "Clearing an in-progress message");
/*      */         }
/* 4382 */         this.progressMsg = null;
/*      */       } 
/* 4384 */       this.progressMsgIndex = 0;
/* 4385 */       this.progressMsgOffset = 0;
/* 4386 */       this.lastReceivedIndex = 0;
/* 4387 */       synchronized (this.statusSync) {
/* 4388 */         this.status |= 0x10000;
/* 4389 */         this.status |= 0x800;
/* 4390 */         this.status &= 0xFFFFFEFF;
/*      */       } 
/* 4392 */       if (Trace.isOn) {
/* 4393 */         Trace.data(this, "resetForReconnect(RemoteTls)", "RPQ status changed to", decodeStatusNoTrace());
/*      */       }
/* 4395 */       this.receivedBytes = 0;
/* 4396 */       this.processedBytes = 0;
/* 4397 */       this.requestedBytes = 0;
/* 4398 */       this.cbAlreadyRegistered = false;
/*      */       
/* 4400 */       System.arraycopy(CMQC.MQMI_NONE, 0, this.currentMsgId, 0, 24);
/* 4401 */       this.currentMsgIdSet = false;
/*      */       
/* 4403 */       System.arraycopy(CMQC.MQCI_NONE, 0, this.currentCorrelId, 0, 24);
/* 4404 */       this.currentCorrelIdSet = false;
/*      */ 
/*      */ 
/*      */       
/* 4408 */       clearTxnMessage(tls);
/*      */     } finally {
/*      */       
/* 4411 */       if (Trace.isOn) {
/* 4412 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "resetForReconnect(RemoteTls)");
/*      */       }
/*      */       
/* 4415 */       releaseMutex();
/*      */     } 
/*      */     
/* 4418 */     if (Trace.isOn) {
/* 4419 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "resetForReconnect(RemoteTls)");
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
/*      */   public void clearAllTxnMessages(RemoteTls tls) throws JmqiException {
/* 4433 */     if (Trace.isOn) {
/* 4434 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "clearAllTxnMessages(RemoteTls)", new Object[] { tls });
/*      */     }
/*      */     
/* 4437 */     requestMutex(tls);
/*      */ 
/*      */     
/*      */     try {
/* 4441 */       clearTxnMessage(tls);
/*      */     } finally {
/*      */       
/* 4444 */       if (Trace.isOn) {
/* 4445 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "clearAllTxnMessages(RemoteTls)");
/*      */       }
/*      */       
/* 4448 */       releaseMutex();
/*      */     } 
/*      */     
/* 4451 */     if (Trace.isOn) {
/* 4452 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "clearAllTxnMessages(RemoteTls)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void releaseWaitingGetters() {
/* 4463 */     if (Trace.isOn) {
/* 4464 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "releaseWaitingGetters()");
/*      */     }
/* 4466 */     synchronized (this.getterEventMonitor) {
/*      */       
/* 4468 */       this.getterEventMonitor.notifyAll();
/*      */     } 
/*      */     
/* 4471 */     if (Trace.isOn) {
/* 4472 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "releaseWaitingGetters()");
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
/*      */   void raiseEvent(RemoteConstants.Event ed) throws JmqiException {
/* 4484 */     if (Trace.isOn) {
/* 4485 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "raiseEvent(EventDescriptor)", new Object[] { ed });
/*      */     }
/*      */ 
/*      */     
/* 4489 */     if (!isCbAlreadyRegistered()) {
/*      */       
/* 4491 */       if (Trace.isOn) {
/* 4492 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "raiseEvent(int)", 1);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 4498 */     if (this.eventsRaised.contains(ed)) {
/* 4499 */       if (Trace.isOn) {
/* 4500 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "raiseEvent(int)", 2);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/* 4505 */     RemoteTls tls = this.hconn.getRemoteFap().getTls();
/* 4506 */     requestMutex(tls);
/*      */     
/*      */     try {
/* 4509 */       this.eventsRaised.add(ed);
/*      */       
/* 4511 */       RemoteProxyMessage message = allocMessage(tls, 24);
/*      */       
/* 4513 */       message.setType(0);
/* 4514 */       message.setCompCode(2);
/* 4515 */       message.setReason(ed.getReason());
/*      */       
/* 4517 */       addPhysicalMessage(tls, message);
/*      */     }
/* 4519 */     catch (JmqiException e) {
/* 4520 */       if (Trace.isOn) {
/* 4521 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "raiseEvent(int)", (Throwable)e);
/*      */       
/*      */       }
/*      */     
/*      */     }
/*      */     finally {
/*      */       
/* 4528 */       if (Trace.isOn) {
/* 4529 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "raiseEvent(int)");
/*      */       }
/* 4531 */       releaseMutex();
/*      */     } 
/* 4533 */     if (Trace.isOn) {
/* 4534 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "raiseEvent(int)", 3);
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
/*      */   void checkTxnMessage(RemoteTls tls) throws JmqiException {
/* 4546 */     if (Trace.isOn) {
/* 4547 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "checkTxnMessage(RemoteTls)", new Object[] { tls });
/*      */     }
/*      */ 
/*      */     
/* 4551 */     if (this.mqcbCBD != null && 
/* 4552 */       this.mqcbCBD.getCallbackFunction() != null && (this.status & 0x40000) == 0)
/*      */     {
/*      */ 
/*      */ 
/*      */       
/* 4557 */       if (!isEmpty()) {
/*      */ 
/*      */ 
/*      */         
/* 4561 */         requestMutex(tls);
/*      */         
/* 4563 */         driveConsumer(this.env.newPint(0));
/*      */         
/* 4565 */         releaseMutex();
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 4571 */     if (Trace.isOn) {
/* 4572 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "checkTxnMessage(RemoteTls)");
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
/*      */   private void clearTxnMessage(RemoteTls tls) throws JmqiException {
/* 4584 */     if (Trace.isOn) {
/* 4585 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "clearTxnMessage(RemoteTls)", new Object[] { tls });
/*      */     }
/*      */ 
/*      */     
/* 4589 */     assert haveMutex();
/*      */     
/* 4591 */     if ((this.status & 0x10000000) == 268435456) {
/* 4592 */       for (RemoteProxyMessage pMessage = this.queueTop; pMessage != null; pMessage = pMessage.getNewer()) {
/*      */         
/* 4594 */         if ((pMessage.getType() & 0x1) == 1)
/*      */         {
/* 4596 */           if (pMessage.isTransactional()) {
/* 4597 */             if (Trace.isOn) {
/* 4598 */               Trace.data(this, "clearTxnMessage(RemoteTls)", "removing a transactional message");
/*      */             }
/* 4600 */             removeMessage(tls, false, false, pMessage);
/*      */             
/* 4602 */             if (isEmpty() && (this.status & 0x800) == 0 && (this.status & 0x40000) == 0 && (this.status & 0x80) == 0)
/*      */             {
/*      */ 
/*      */               
/* 4606 */               synchronized (this.statusSync) {
/* 4607 */                 this.status |= 0x800;
/*      */               } 
/*      */             }
/*      */             
/*      */             break;
/*      */           } 
/*      */         }
/*      */       } 
/*      */     }
/* 4616 */     if (Trace.isOn) {
/* 4617 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "clearTxnMessage(RemoteTls)");
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
/*      */   private void driveConsumer(Pint processed) throws JmqiException {
/* 4629 */     if (Trace.isOn) {
/* 4630 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "driveConsumer(Pint)", new Object[] { processed });
/*      */     }
/*      */     
/* 4633 */     RemoteProxyMessage pMessage = null;
/* 4634 */     boolean keepBrowse = ((getStatus() & 0x20) != 0);
/* 4635 */     RemoteTls tls = this.hconn.getParentConnection(this).getRemoteFap().getTls();
/*      */ 
/*      */     
/*      */     do {
/* 4639 */       pMessage = findNextMessage(tls, getMqcbCBD().getMaxMsgLength(), false);
/*      */       
/* 4641 */       if (pMessage == null) {
/*      */         break;
/*      */       }
/*      */       
/* 4645 */       removeMessage(tls, true, keepBrowse, pMessage);
/*      */       
/* 4647 */       releaseMutex();
/*      */       
/*      */       try {
/* 4650 */         if ((pMessage.getType() & 0x1) != 0) {
/*      */           
/* 4652 */           this.callbackCBC.setVersion(1);
/* 4653 */           this.callbackCBC.setHobj((Hobj)getHobj());
/* 4654 */           this.callbackCBC.setCallbackArea(this.mqcbCBD.getCallbackArea());
/* 4655 */           this.callbackCBC.setConnectionArea(this.hconn.getConnectionArea());
/* 4656 */           if (keepBrowse) {
/* 4657 */             this.callbackCBC.setCallType(7);
/*      */           } else {
/*      */             
/* 4660 */             this.callbackCBC.setCallType(6);
/* 4661 */             if (pMessage.getReason() != 0)
/*      */             {
/*      */ 
/*      */ 
/*      */               
/* 4666 */               switch (pMessage.getReason()) {
/*      */                 case 2080:
/* 4668 */                   this.callbackCBC.setCallType(7);
/*      */                   break;
/*      */               } 
/*      */ 
/*      */             
/*      */             }
/*      */           } 
/* 4675 */           this.callbackCBC.setCompCode(pMessage.getCompCode());
/* 4676 */           this.callbackCBC.setReason(pMessage.getReason());
/* 4677 */           this.callbackCBC.setDataLength(pMessage.getActualMsgLength());
/* 4678 */           this.callbackCBC.setBufferLength(pMessage.getMsgLength());
/* 4679 */           this.callbackCBC.setFlags(0);
/*      */ 
/*      */ 
/*      */           
/* 4683 */           if ((getStatus() & 0x80) != 0)
/*      */           {
/*      */ 
/*      */             
/* 4687 */             if (isEmpty()) {
/* 4688 */               this.callbackCBC.setFlags(this.callbackCBC.getFlags() | 0x1);
/*      */             }
/*      */           }
/*      */           
/* 4692 */           if ((getStatus() & 0x10000000) != 0 && !this.hconn.inTransaction()) {
/* 4693 */             switch (getMqcbGmo().getOptions() & 0x1006) {
/*      */               case 2:
/* 4695 */                 this.hconn.setInTransaction();
/*      */                 break;
/*      */               case 4096:
/* 4698 */                 if (this.mqcbMD.getPersistence() == 1) {
/* 4699 */                   this.hconn.setInTransaction();
/*      */                 }
/*      */                 break;
/*      */             } 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           }
/* 4708 */           getMqcbGmo().setMsgToken(pMessage.getMsgToken());
/* 4709 */           getMqcbGmo().setVersion(4);
/* 4710 */           getMqcbGmo().setReturnedLength(pMessage.getMsgLength());
/*      */           
/* 4712 */           MQConsumer consumer = getMqcbCBD().getCallbackFunction();
/* 4713 */           ByteBuffer byteBuf = ByteBuffer.allocate((pMessage.getMsgData()).length);
/* 4714 */           byteBuf = byteBuf.put(pMessage.getMsgData());
/*      */           try {
/* 4716 */             consumer.consumer((Hconn)this.hconn, pMessage.getMsgDesc(), getMqcbGmo(), byteBuf, this.callbackCBC);
/*      */           }
/* 4718 */           catch (Throwable throwable) {
/* 4719 */             if (Trace.isOn) {
/* 4720 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "driveConsumer(Pint)", throwable);
/*      */             }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 4731 */             if (throwable instanceof Error) {
/* 4732 */               Error traceRet1 = (Error)throwable;
/* 4733 */               if (Trace.isOn) {
/* 4734 */                 Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "driveConsumer(Pint)", traceRet1, 1);
/*      */               }
/*      */               
/* 4737 */               throw traceRet1;
/*      */             } 
/* 4739 */             if (throwable instanceof RuntimeException) {
/* 4740 */               RuntimeException traceRet2 = (RuntimeException)throwable;
/* 4741 */               if (Trace.isOn) {
/* 4742 */                 Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "driveConsumer(Pint)", traceRet2, 2);
/*      */               }
/*      */               
/* 4745 */               throw traceRet2;
/*      */             }
/*      */           
/*      */           } 
/*      */         } else {
/*      */           
/* 4751 */           processEvent(pMessage);
/*      */         }
/*      */       
/*      */       } finally {
/*      */         
/* 4756 */         if (Trace.isOn) {
/* 4757 */           Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "driveConsumer(Pint)");
/*      */         }
/*      */ 
/*      */         
/* 4761 */         requestMutex(tls);
/*      */       } 
/*      */       
/* 4764 */       processed.x++;
/*      */ 
/*      */       
/* 4767 */       if (this.hconn.consumersChanged()) {
/* 4768 */         this.hconn.driveOutstanding();
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4774 */       if (!isCbAlreadyRegistered()) {
/*      */ 
/*      */ 
/*      */         
/* 4778 */         this.hconn.checkTxnAllowed();
/* 4779 */         if (Trace.isOn) {
/* 4780 */           Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "driveConsumer(Pint)", 2);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         return;
/*      */       } 
/*      */ 
/*      */       
/* 4789 */       if (!this.hconn.isStarted() || this.hconn.isSuspended() || this.hconn.isSuspending()) {
/*      */ 
/*      */ 
/*      */         
/* 4793 */         synchronized (this.statusSync) {
/* 4794 */           this.status |= 0x800;
/*      */         } 
/* 4796 */         if (Trace.isOn) {
/* 4797 */           Trace.data(this, "driveConsumer(Pint)", "RPQ status changed to", decodeStatusNoTrace());
/*      */         }
/*      */         
/* 4800 */         if (Trace.isOn) {
/* 4801 */           Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "driveConsumer(Pint)", 3);
/*      */         }
/*      */ 
/*      */         
/*      */         return;
/*      */       } 
/*      */ 
/*      */       
/* 4809 */       if (this.asyncSelectionIndex != this.selectionIndex) {
/* 4810 */         setAsyncSelection(tls);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4816 */       if ((getStatus() & 0x40000) != 0) {
/*      */ 
/*      */ 
/*      */         
/* 4820 */         this.hconn.checkTxnAllowed();
/* 4821 */         if (Trace.isOn) {
/* 4822 */           Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "driveConsumer(Pint)", 4);
/*      */         }
/*      */ 
/*      */         
/*      */         return;
/*      */       } 
/*      */ 
/*      */       
/* 4830 */       if ((getStatus() & 0x80) != 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 4835 */         this.hconn.checkTxnAllowed();
/*      */       } else {
/*      */         boolean makeRequest;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 4853 */         if ((getStatus() & 0x400) != 0) {
/* 4854 */           makeRequest = ((getStatus() & 0x12000) != 0);
/* 4855 */           if (isEmpty())
/*      */           {
/* 4857 */             if (!makeRequest) {
/* 4858 */               makeRequest = ((getStatus() & 0x8000) != 0);
/*      */             }
/*      */             
/* 4861 */             if (!makeRequest)
/*      */             {
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 4867 */               releaseMutex();
/* 4868 */               Thread.yield();
/* 4869 */               requestMutex(tls);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 4875 */               if (isEmpty())
/*      */               {
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/* 4881 */                 if ((getStatus() & 0x8) == 0) {
/* 4882 */                   makeRequest = this.hconn.checkClientEmpty();
/*      */                 
/*      */                 }
/*      */               
/*      */               }
/*      */             }
/*      */           
/*      */           }
/*      */         
/*      */         }
/*      */         else {
/*      */           
/* 4894 */           makeRequest = ((getStatus() & 0x8) == 0);
/*      */         } 
/*      */         
/* 4897 */         if (makeRequest) {
/* 4898 */           requestMessages(tls, false, 0, false, true);
/*      */         
/*      */         }
/*      */       }
/*      */     
/*      */     }
/* 4904 */     while (processed.x < 5);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4909 */     if (Trace.isOn) {
/* 4910 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "driveConsumer(Pint)", 5);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void processEvent(RemoteProxyMessage pMessage) throws JmqiException {
/* 4916 */     if (Trace.isOn) {
/* 4917 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "processEvent(RemoteProxyMessage)", new Object[] { pMessage });
/*      */     }
/* 4919 */     int compCode = pMessage.getCompCode();
/* 4920 */     int reason = pMessage.getReason();
/* 4921 */     if (reason == 2009 && 
/* 4922 */       this.hconn.isReconnectable()) {
/* 4923 */       JmqiException traceRet = new JmqiException(this.env, -1, null, 2, 2009, null);
/* 4924 */       if (Trace.isOn) {
/* 4925 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "processEvent(RemoteProxyMessage)", (Throwable)traceRet);
/*      */       }
/* 4927 */       throw traceRet;
/*      */     } 
/*      */     
/* 4930 */     callConsumer(5, compCode, reason);
/* 4931 */     if (Trace.isOn) {
/* 4932 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "processEvent(RemoteProxyMessage)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void mqcbSuspendMC() throws JmqiException {
/* 4942 */     if (Trace.isOn) {
/* 4943 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "mqcbSuspendMC()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4949 */     if (getMqcbCBD().getCallbackFunction() == null) {
/* 4950 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2448, null);
/*      */       
/* 4952 */       if (Trace.isOn) {
/* 4953 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "mqcbSuspendMC()", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 4956 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 4961 */     if ((getStatus() & 0x40000) == 0) {
/* 4962 */       synchronized (this.statusSync) {
/* 4963 */         if (Trace.isOn) {
/* 4964 */           Trace.data(this, "mqcbSuspendMC()", "Setting state suspended in mqcbSuspendMC()", null);
/*      */         }
/* 4966 */         this.status |= 0x40000;
/*      */       } 
/* 4968 */       if (Trace.isOn) {
/* 4969 */         Trace.data(this, "mqcbSuspendMC()", "RPQ status changed to", decodeStatusNoTrace());
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 4974 */       this.hconn.setConsumersChanged();
/*      */ 
/*      */ 
/*      */       
/* 4978 */       sendConsumerState(this.hconn.getParentConnection(this).getRemoteFap().getTls(), false);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 4983 */     if ((getMqcbCBD().getOptions() & 0x4) == 0) {
/* 4984 */       this.hconn.removeFromDispatchList(this);
/*      */     }
/*      */     
/* 4987 */     if (Trace.isOn) {
/* 4988 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "mqcbSuspendMC()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void mqcbResumeMC() throws JmqiException {
/* 4999 */     if (Trace.isOn) {
/* 5000 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "mqcbResumeMC()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5006 */     if (this.mqcbCBD.getCallbackFunction() == null) {
/* 5007 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2448, null);
/*      */       
/* 5009 */       if (Trace.isOn) {
/* 5010 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "mqcbResumeMC()", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 5013 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 5018 */     if ((this.status & 0x40000) != 0) {
/* 5019 */       synchronized (this.statusSync) {
/* 5020 */         this.status &= 0xFFFBFFFF;
/*      */       } 
/* 5022 */       if (Trace.isOn) {
/* 5023 */         Trace.data(this, "mqcbResumeMC()", "RPQ status changed to", decodeStatusNoTrace());
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 5028 */       sendConsumerState(this.hconn.getParentConnection(this).getRemoteFap().getTls(), false);
/*      */     } 
/*      */ 
/*      */     
/* 5032 */     this.hconn.addToDispatchList(this);
/*      */ 
/*      */     
/* 5035 */     this.hconn.checkDispatchable(this);
/*      */     
/* 5037 */     if (Trace.isOn) {
/* 5038 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "mqcbResumeMC()");
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
/*      */   public void mqcbRegisterMC(MQCBD callbackDesc, MQMD msgDesc, MQGMO gmo) throws JmqiException {
/* 5052 */     if (Trace.isOn) {
/* 5053 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "mqcbRegisterMC(MQCBD,MQMD,MQGMO)", new Object[] { callbackDesc, msgDesc, gmo });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 5058 */     validateMQMD(msgDesc);
/* 5059 */     validateMQGMO(gmo);
/* 5060 */     if (callbackDesc.getCallbackFunction() == null) {
/* 5061 */       callbackDesc.setCallbackFunction((MQConsumer)JmqiUtils.loadAndInstantiateClass(this.env, callbackDesc.getCallbackName()));
/*      */     }
/* 5063 */     RemoteTls tls = this.hconn.getParentConnection(this).getRemoteFap().getTls();
/* 5064 */     requestMutex(tls);
/*      */     
/*      */     try {
/* 5067 */       checkGetMsgOptions(tls, true, msgDesc, gmo);
/*      */       
/* 5069 */       this.mqcbGmo = gmo;
/* 5070 */       this.mqcbMD = msgDesc;
/*      */       
/* 5072 */       if (!isCbAlreadyRegistered()) {
/* 5073 */         this.callbackCBC = this.env.newMQCBC();
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         try {
/* 5079 */           this.mqcbCBD = (MQCBD)callbackDesc.clone();
/*      */         }
/* 5081 */         catch (CloneNotSupportedException e) {
/* 5082 */           if (Trace.isOn) {
/* 5083 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "mqcbRegisterMC(MQCBD,MQMD,MQGMO)", e);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 5088 */           HashMap<String, Object> info = new HashMap<>();
/* 5089 */           info.put("Exception Message", e.getMessage());
/* 5090 */           info.put("Description", "Unable to clone MQCBD");
/* 5091 */           Trace.ffst(this, "mqcbRegisterMC(MQCBD,MQMD,MQGMO)", "01", info, null);
/*      */         } 
/*      */ 
/*      */         
/* 5095 */         if ((getMqcbCBD().getOptions() & 0x100) != 0) {
/* 5096 */           callConsumer(3, 0, 0);
/*      */         }
/*      */         
/* 5099 */         this.cbAlreadyRegistered = true;
/*      */ 
/*      */ 
/*      */         
/* 5103 */         sendConsumerState(this.hconn.getParentConnection(this).getRemoteFap().getTls(), false);
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 5108 */         synchronized (this.statusSync) {
/* 5109 */           this.status = getStatus() & 0xFFDFFFFF;
/*      */         } 
/* 5111 */         if (Trace.isOn) {
/* 5112 */           Trace.data(this, "mqcbRegisterMC(MQCBD,MQMD,MQGMO)", "RPQ status changed to", decodeStatusNoTrace());
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 5119 */       setNoMsgTime(0L);
/*      */ 
/*      */       
/* 5122 */       this.hconn.addToDispatchList(this);
/*      */ 
/*      */ 
/*      */       
/* 5126 */       setAsyncSelection(tls);
/*      */     } finally {
/*      */       
/* 5129 */       if (Trace.isOn) {
/* 5130 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "mqcbRegisterMC(MQCBD,MQMD,MQGMO)");
/*      */       }
/*      */       
/* 5133 */       releaseMutex();
/*      */     } 
/*      */     
/* 5136 */     if (Trace.isOn) {
/* 5137 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "mqcbRegisterMC(MQCBD,MQMD,MQGMO)");
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
/*      */   public void mqcbDeRegisterMC(boolean immediate) throws JmqiException {
/* 5150 */     if (Trace.isOn) {
/* 5151 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "mqcbDeRegisterMC(boolean)", new Object[] {
/* 5152 */             Boolean.valueOf(immediate)
/*      */           });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 5158 */     if (getMqcbCBD().getCallbackFunction() == null) {
/* 5159 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 1, 2448, null);
/*      */       
/* 5161 */       if (Trace.isOn) {
/* 5162 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "mqcbDeRegisterMC(boolean)", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 5165 */       throw traceRet1;
/*      */     } 
/*      */     
/* 5168 */     this.cbAlreadyRegistered = false;
/*      */ 
/*      */     
/* 5171 */     if (immediate) {
/*      */       
/* 5173 */       this.hconn.removeFromDispatchList(this);
/*      */       
/* 5175 */       if ((getMqcbCBD().getOptions() & 0x200) != 0) {
/* 5176 */         callConsumer(4, 0, 0);
/*      */       }
/* 5178 */       getMqcbCBD().setCallbackFunction(null);
/*      */ 
/*      */ 
/*      */       
/* 5182 */       synchronized (this.statusSync) {
/* 5183 */         this.status = getStatus() & 0xFFDFFFFF;
/*      */       } 
/* 5185 */       if (Trace.isOn) {
/* 5186 */         Trace.data(this, "mqcbDeRegisterMC(boolean)", "RPQ status changed to", decodeStatusNoTrace());
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 5192 */       sendConsumerState(this.hconn.getParentConnection(this).getRemoteFap().getTls(), false);
/*      */     }
/*      */     else {
/*      */       
/* 5196 */       this.hconn.setConsumersChanged();
/* 5197 */       synchronized (this.statusSync) {
/* 5198 */         this.status = getStatus() | 0x200000;
/*      */       } 
/* 5200 */       if (Trace.isOn) {
/* 5201 */         Trace.data(this, "mqcbDeRegisterMC(boolean)", "RPQ status changed to", decodeStatusNoTrace());
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 5206 */       sendConsumerState(this.hconn.getParentConnection(this).getRemoteFap().getTls(), false);
/*      */     } 
/*      */     
/* 5209 */     if (Trace.isOn) {
/* 5210 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "mqcbDeRegisterMC(boolean)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void driveEventsMC() throws JmqiException {
/* 5220 */     if (Trace.isOn) {
/* 5221 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "driveEventsMC()");
/*      */     }
/*      */ 
/*      */     
/* 5225 */     for (RemoteConstants.Event ed : this.hconn.getEventsHad()) {
/* 5226 */       if (!this.eventsRaised.contains(ed)) {
/* 5227 */         this.eventsRaised.add(ed);
/* 5228 */         callConsumer(5, 2, ed.getReason());
/*      */       } 
/*      */     } 
/*      */     
/* 5232 */     if (Trace.isOn) {
/* 5233 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "driveEventsMC()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   boolean failIfQuiescing() {
/* 5240 */     boolean traceRet = ((this.appGmo.getOptions() & 0x2000) != 0);
/* 5241 */     if (Trace.isOn) {
/* 5242 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "failIfQuiescing()", "getter", 
/* 5243 */           Boolean.valueOf(traceRet));
/*      */     }
/*      */     
/* 5246 */     return traceRet;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isDispatchable() throws JmqiException {
/* 5254 */     if (Trace.isOn) {
/* 5255 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "isDispatchable()");
/*      */     }
/* 5257 */     RemoteTls tls = this.hconn.getParentConnection(this).getRemoteFap().getTls();
/* 5258 */     if ((this.status & 0x40000) == 0) {
/* 5259 */       if (Trace.isOn) {
/* 5260 */         Trace.data(this, "isDispatchable()", "NOT SUSPENDED", null);
/*      */       }
/*      */ 
/*      */       
/* 5264 */       if (this.asyncSelectionIndex != this.selectionIndex) {
/* 5265 */         setAsyncSelection(tls);
/*      */       }
/*      */       
/* 5268 */       if (isEmpty() && (getStatus() & 0x800) != 0 && (getStatus() & 0x80) == 0) {
/* 5269 */         requestMutex(tls);
/*      */         try {
/* 5271 */           requestMessages(tls, false, 0, false, true);
/*      */         } finally {
/*      */           
/* 5274 */           if (Trace.isOn) {
/* 5275 */             Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "isDispatchable()");
/*      */           }
/*      */           
/* 5278 */           releaseMutex();
/*      */         } 
/*      */       } 
/*      */       
/* 5282 */       if (!this.eventsRaised.equals(this.hconn.getEventsHad())) {
/* 5283 */         if (Trace.isOn) {
/* 5284 */           Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "isDispatchable()", 
/* 5285 */               Boolean.valueOf(true), 1);
/*      */         }
/* 5287 */         return true;
/*      */       } 
/*      */       
/* 5290 */       if ((getMqcbGmo().getOptions() & 0x1) != 0 && getMqcbGmo().getWaitInterval() >= 0) {
/* 5291 */         if (getMqcbGmo().getWaitInterval() == 0) {
/* 5292 */           synchronized (this.statusSync) {
/* 5293 */             this.status = getStatus() | 0x1000000;
/*      */           } 
/* 5295 */           if (Trace.isOn) {
/* 5296 */             Trace.data(this, "isDispatchable()", "RPQ status changed to", decodeStatusNoTrace());
/*      */           }
/*      */         } 
/* 5299 */         if (Trace.isOn) {
/* 5300 */           Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "isDispatchable()", 
/* 5301 */               Boolean.valueOf(true), 2);
/*      */         }
/* 5303 */         return true;
/*      */       } 
/*      */ 
/*      */       
/* 5307 */       if (!isEmpty()) {
/* 5308 */         if (Trace.isOn) {
/* 5309 */           Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "isDispatchable()", 
/* 5310 */               Boolean.valueOf(true), 3);
/*      */         }
/* 5312 */         return true;
/*      */       } 
/*      */     } 
/*      */     
/* 5316 */     if (Trace.isOn) {
/* 5317 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "isDispatchable()", 
/* 5318 */           Boolean.valueOf(false), 4);
/*      */     }
/* 5320 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void deliverMsgs(Pint processed) throws JmqiException {
/* 5328 */     if (Trace.isOn) {
/* 5329 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "deliverMsgs(Pint)", new Object[] { processed });
/*      */     }
/*      */     
/* 5332 */     requestMutex(this.hconn.getParentConnection(this).getRemoteFap().getTls());
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 5338 */       if (getMqcbGmo().getWaitInterval() == 0 && (getMqcbGmo().getOptions() & 0x1) != 0) {
/* 5339 */         synchronized (this.statusSync) {
/* 5340 */           this.status = getStatus() | 0x1000000;
/*      */         } 
/* 5342 */         if (Trace.isOn) {
/* 5343 */           Trace.data(this, "deliverMsgs(Pint)", "RPQ status changed to", decodeStatusNoTrace());
/*      */         }
/*      */       } 
/* 5346 */       driveConsumer(processed);
/*      */     } finally {
/*      */       
/* 5349 */       if (Trace.isOn) {
/* 5350 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "deliverMsgs(Pint)");
/*      */       }
/*      */       
/* 5353 */       releaseMutex();
/*      */     } 
/* 5355 */     if (Trace.isOn) {
/* 5356 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "deliverMsgs(Pint)");
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
/*      */   private void validateMQGMO(MQGMO gmo) throws JmqiException {
/* 5368 */     if (Trace.isOn) {
/* 5369 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "validateMQGMO(MQGMO)", new Object[] { gmo });
/*      */     }
/*      */ 
/*      */     
/* 5373 */     if (gmo.getVersion() < 1 || gmo.getVersion() > 4) {
/* 5374 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2256, null);
/*      */       
/* 5376 */       if (Trace.isOn) {
/* 5377 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "validateMQGMO(MQGMO)", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 5380 */       throw traceRet1;
/*      */     } 
/* 5382 */     if (Trace.isOn) {
/* 5383 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "validateMQGMO(MQGMO)");
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
/*      */   private void validateMQMD(MQMD md) throws JmqiException {
/* 5395 */     if (Trace.isOn) {
/* 5396 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "validateMQMD(MQMD)", new Object[] { md });
/*      */     }
/*      */ 
/*      */     
/* 5400 */     if (md.getVersion() < 1 || md.getVersion() > 2) {
/* 5401 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2257, null);
/*      */       
/* 5403 */       if (Trace.isOn) {
/* 5404 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "validateMQMD(MQMD)", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 5407 */       throw traceRet1;
/*      */     } 
/* 5409 */     if (Trace.isOn) {
/* 5410 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "validateMQMD(MQMD)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void driveStop() {
/* 5419 */     if (Trace.isOn) {
/* 5420 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "driveStop()");
/*      */     }
/* 5422 */     synchronized (this.statusSync) {
/* 5423 */       this.status &= 0xFFBFFFFF;
/*      */     } 
/* 5425 */     if (Trace.isOn) {
/* 5426 */       Trace.data(this, "driveStop()", "RPQ status changed to", decodeStatusNoTrace());
/*      */     }
/*      */     
/* 5429 */     if (Trace.isOn) {
/* 5430 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "driveStop()");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   void unsetCallbackOnEmpty() {
/* 5436 */     if (Trace.isOn) {
/* 5437 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "unsetCallbackOnEmpty()");
/*      */     }
/* 5439 */     synchronized (this.statusSync) {
/* 5440 */       this.status &= 0xFEFFFFFF;
/*      */     } 
/* 5442 */     if (Trace.isOn) {
/* 5443 */       Trace.data(this, "unsetCallbackOnEmpty()", "RPQ status changed to", decodeStatusNoTrace());
/*      */     }
/*      */     
/* 5446 */     if (Trace.isOn) {
/* 5447 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "unsetCallbackOnEmpty()");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   boolean callbackOnEmpty() {
/* 5453 */     if (Trace.isOn) {
/* 5454 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "callbackOnEmpty()");
/*      */     }
/* 5456 */     boolean traceRet1 = ((this.status & 0x1000000) != 0);
/*      */     
/* 5458 */     if (Trace.isOn) {
/* 5459 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "callbackOnEmpty()", 
/* 5460 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 5462 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void dump(PrintWriter pw, int level) {
/* 5470 */     if (Trace.isOn) {
/* 5471 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "dump(PrintWriter,int)", new Object[] { pw, 
/* 5472 */             Integer.valueOf(level) });
/*      */     }
/* 5474 */     String prefix = Trace.buildPrefix(level);
/* 5475 */     pw.format("%s%s%n", new Object[] { prefix, toString() });
/* 5476 */     pw.format("%s  status: 0x%x (%s)%n", new Object[] { prefix, Integer.valueOf(this.status), decodeStatus() });
/* 5477 */     pw.format("%s  queueStatus: 0x%x (%s)%n", new Object[] { prefix, Integer.valueOf(this.queueStatus), decodeQueueStatus() });
/* 5478 */     pw.format("%s  last physical message added @ %s%n", new Object[] { prefix, Trace.formatTimeStamp(this.lastPhysicalAddTime) });
/* 5479 */     pw.format("%s  last REQUEST_MSGS flow @ %s%n", new Object[] { prefix, Trace.formatTimeStamp(this.lastRequestTime) });
/* 5480 */     pw.format("%s  is logically removed: %b%n", new Object[] { prefix, Boolean.valueOf(this.logicallyRemoved) });
/* 5481 */     pw.format("%s  proxyQueueLock %s%n", new Object[] { prefix, this.proxyQueueLock.toString() });
/* 5482 */     if (Trace.isOn) {
/* 5483 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "dump(PrintWriter,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private String decodeStatus() {
/* 5489 */     if (Trace.isOn) {
/* 5490 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "decodeStatus()");
/*      */     }
/* 5492 */     String traceRet1 = decodeStatusNoTrace();
/* 5493 */     if (Trace.isOn) {
/* 5494 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "decodeStatus()", traceRet1);
/*      */     }
/* 5496 */     return traceRet1;
/*      */   }
/*      */   
/*      */   private String decodeStatusNoTrace() {
/* 5500 */     StringBuilder result = new StringBuilder();
/* 5501 */     if ((this.status & 0x1) != 0) {
/* 5502 */       result.append("rpqST_UNHEALTHY");
/*      */     }
/* 5504 */     if ((this.status & 0x2) != 0) {
/* 5505 */       if (result.length() > 0) {
/* 5506 */         result.append('|');
/*      */       }
/* 5508 */       result.append("rpqST_GETTER");
/*      */     } 
/* 5510 */     if ((this.status & 0x4) != 0) {
/* 5511 */       if (result.length() > 0) {
/* 5512 */         result.append('|');
/*      */       }
/* 5514 */       result.append("rpqST_GETTER_SIGNALLED");
/*      */     } 
/* 5516 */     if ((this.status & 0x8) != 0) {
/* 5517 */       if (result.length() > 0) {
/* 5518 */         result.append('|');
/*      */       }
/* 5520 */       result.append("rpqST_GET_INHIBITED");
/*      */     } 
/* 5522 */     if ((this.status & 0x20) != 0) {
/* 5523 */       if (result.length() > 0) {
/* 5524 */         result.append('|');
/*      */       }
/* 5526 */       result.append("rpqST_BROWSE");
/*      */     } 
/* 5528 */     if ((this.status & 0x40) != 0) {
/* 5529 */       if (result.length() > 0) {
/* 5530 */         result.append('|');
/*      */       }
/* 5532 */       result.append("rpqST_MULTICAST");
/*      */     } 
/* 5534 */     if ((this.status & 0x80) != 0) {
/* 5535 */       if (result.length() > 0) {
/* 5536 */         result.append('|');
/*      */       }
/* 5538 */       result.append("rpqST_QUIESCED");
/*      */     } 
/* 5540 */     if ((this.status & 0x100) != 0) {
/* 5541 */       if (result.length() > 0) {
/* 5542 */         result.append('|');
/*      */       }
/* 5544 */       result.append("rpqST_QM_QUIESCING");
/*      */     } 
/* 5546 */     if ((this.status & 0x200) != 0) {
/* 5547 */       if (result.length() > 0) {
/* 5548 */         result.append('|');
/*      */       }
/* 5550 */       result.append("rpqST_SELECTION_SET");
/*      */     } 
/* 5552 */     if ((this.status & 0x400) != 0) {
/* 5553 */       if (result.length() > 0) {
/* 5554 */         result.append('|');
/*      */       }
/* 5556 */       result.append("rpqST_STREAMING");
/*      */     } 
/* 5558 */     if ((this.status & 0x800) != 0) {
/* 5559 */       if (result.length() > 0) {
/* 5560 */         result.append('|');
/*      */       }
/* 5562 */       result.append("rpqST_REQUEST_MSG_NEEDED");
/*      */     } 
/* 5564 */     if ((this.status & 0x1000) != 0) {
/* 5565 */       if (result.length() > 0) {
/* 5566 */         result.append('|');
/*      */       }
/* 5568 */       result.append("rpqST_BROWSE_FIRST_WAIT");
/*      */     } 
/* 5570 */     if ((this.status & 0x2000) != 0) {
/* 5571 */       if (result.length() > 0) {
/* 5572 */         result.append('|');
/*      */       }
/* 5574 */       result.append("rpqST_STREAMING_PAUSED");
/*      */     } 
/* 5576 */     if ((this.status & 0x4000) != 0) {
/* 5577 */       if (result.length() > 0) {
/* 5578 */         result.append('|');
/*      */       }
/* 5580 */       result.append("rpqST_STREAMING_REQUESTED");
/*      */     } 
/* 5582 */     if ((this.status & 0x8000) != 0) {
/* 5583 */       if (result.length() > 0) {
/* 5584 */         result.append('|');
/*      */       }
/* 5586 */       result.append("rpqST_STREAMING_INHIBITED");
/*      */     } 
/* 5588 */     if ((this.status & 0x10000) != 0) {
/* 5589 */       if (result.length() > 0) {
/* 5590 */         result.append('|');
/*      */       }
/* 5592 */       result.append("rpqST_SELECTION_CHANGED");
/*      */     } 
/* 5594 */     if ((this.status & 0x20000) != 0) {
/* 5595 */       if (result.length() > 0) {
/* 5596 */         result.append('|');
/*      */       }
/* 5598 */       result.append("rpqST_INPUT");
/*      */     } 
/* 5600 */     if ((this.status & 0x40000) != 0) {
/* 5601 */       if (result.length() > 0) {
/* 5602 */         result.append('|');
/*      */       }
/* 5604 */       result.append("rpqST_SUSPENDED");
/*      */     } 
/* 5606 */     if ((this.status & 0x100000) != 0) {
/* 5607 */       if (result.length() > 0) {
/* 5608 */         result.append('|');
/*      */       }
/* 5610 */       result.append("rpqST_FUNCTION_LOADED");
/*      */     } 
/* 5612 */     if ((this.status & 0x200000) != 0) {
/* 5613 */       if (result.length() > 0) {
/* 5614 */         result.append('|');
/*      */       }
/* 5616 */       result.append("rpqST_DEREGISTER_OUTSTANDING");
/*      */     } 
/* 5618 */     if ((this.status & 0x400000) != 0) {
/* 5619 */       if (result.length() > 0) {
/* 5620 */         result.append('|');
/*      */       }
/* 5622 */       result.append("rpqST_START_CALLED");
/*      */     } 
/* 5624 */     if ((this.status & 0x800000) != 0) {
/* 5625 */       if (result.length() > 0) {
/* 5626 */         result.append('|');
/*      */       }
/* 5628 */       result.append("rpqST_STOP_CALLED");
/*      */     } 
/* 5630 */     if ((this.status & 0x1000000) != 0) {
/* 5631 */       if (result.length() > 0) {
/* 5632 */         result.append('|');
/*      */       }
/* 5634 */       result.append("rpqST_CALLBACK_ON_EMPTY");
/*      */     } 
/* 5636 */     if ((this.status & 0x2000000) != 0) {
/* 5637 */       if (result.length() > 0) {
/* 5638 */         result.append('|');
/*      */       }
/* 5640 */       result.append("rpqST_MQGET_CALLED");
/*      */     } 
/* 5642 */     if ((this.status & 0x4000000) != 0) {
/* 5643 */       if (result.length() > 0) {
/* 5644 */         result.append('|');
/*      */       }
/* 5646 */       result.append("rpqST_MQGET_WAITING");
/*      */     } 
/* 5648 */     if ((this.status & 0x8000000) != 0) {
/* 5649 */       if (result.length() > 0) {
/* 5650 */         result.append('|');
/*      */       }
/* 5652 */       result.append("rpqST_MSG_COPIED");
/*      */     } 
/* 5654 */     if ((this.status & 0x10000000) != 0) {
/* 5655 */       if (result.length() > 0) {
/* 5656 */         result.append('|');
/*      */       }
/* 5658 */       result.append("rpqST_TRANSACTIONAL");
/*      */     } 
/* 5660 */     if ((this.status & 0x20000000) != 0) {
/* 5661 */       if (result.length() > 0) {
/* 5662 */         result.append('|');
/*      */       }
/* 5664 */       result.append("rpqST_CLOSED");
/*      */     } 
/* 5666 */     if ((this.status & 0x40000000) != 0) {
/* 5667 */       if (result.length() > 0) {
/* 5668 */         result.append('|');
/*      */       }
/* 5670 */       result.append("rpqST_STREAMING_TXN_PAUSED");
/*      */     } 
/* 5672 */     return result.toString();
/*      */   }
/*      */   
/*      */   private String decodeQueueStatus() {
/* 5676 */     if (Trace.isOn) {
/* 5677 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "decodeQueueStatus()");
/*      */     }
/* 5679 */     String traceRet1 = decodeQueueStatusNoTrace();
/* 5680 */     if (Trace.isOn) {
/* 5681 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "decodeQueueStatus()", traceRet1);
/*      */     }
/*      */     
/* 5684 */     return traceRet1;
/*      */   }
/*      */   
/*      */   protected String decodeQueueStatusNoTrace() {
/* 5688 */     StringBuilder result = new StringBuilder();
/* 5689 */     if ((this.queueStatus & 0x1) != 0) {
/* 5690 */       result.append("rfpQS_STREAMING");
/*      */     }
/* 5692 */     if ((this.queueStatus & 0x2) != 0) {
/* 5693 */       if (result.length() > 0) {
/* 5694 */         result.append('|');
/*      */       }
/* 5696 */       result.append("rfpQS_STREAMING_BACKLOG");
/*      */     } 
/* 5698 */     if ((this.queueStatus & 0x4) != 0) {
/* 5699 */       if (result.length() > 0) {
/* 5700 */         result.append('|');
/*      */       }
/* 5702 */       result.append("rfpQS_STREAMING_INHIBITED");
/*      */     } 
/* 5704 */     return result.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isLogicallyRemoved() {
/* 5711 */     if (Trace.isOn) {
/* 5712 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "isLogicallyRemoved()", "getter", 
/* 5713 */           Boolean.valueOf(this.logicallyRemoved));
/*      */     }
/* 5715 */     return this.logicallyRemoved;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLogicallyRemoved(boolean logicallyRemoved) {
/* 5722 */     if (Trace.isOn) {
/* 5723 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue", "setLogicallyRemoved(boolean)", "setter", 
/* 5724 */           Boolean.valueOf(logicallyRemoved));
/*      */     }
/* 5726 */     this.logicallyRemoved = logicallyRemoved;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\impl\RemoteProxyQueue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */