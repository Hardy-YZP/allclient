/*       */ package com.ibm.mq.jmqi.remote.api;
/*       */ 
/*       */ import com.ibm.mq.constants.MQConstants;
/*       */ import com.ibm.mq.constants.QmgrSplCapability;
/*       */ import com.ibm.mq.exits.MQCD;
/*       */ import com.ibm.mq.exits.MQCSP;
/*       */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*       */ import com.ibm.mq.jmqi.JmqiException;
/*       */ import com.ibm.mq.jmqi.JmqiImplementation;
/*       */ import com.ibm.mq.jmqi.JmqiMQ;
/*       */ import com.ibm.mq.jmqi.JmqiXA;
/*       */ import com.ibm.mq.jmqi.MQAIR;
/*       */ import com.ibm.mq.jmqi.MQBMHO;
/*       */ import com.ibm.mq.jmqi.MQBNO;
/*       */ import com.ibm.mq.jmqi.MQBO;
/*       */ import com.ibm.mq.jmqi.MQCBD;
/*       */ import com.ibm.mq.jmqi.MQCHARV;
/*       */ import com.ibm.mq.jmqi.MQCMHO;
/*       */ import com.ibm.mq.jmqi.MQCNO;
/*       */ import com.ibm.mq.jmqi.MQCTLO;
/*       */ import com.ibm.mq.jmqi.MQDMHO;
/*       */ import com.ibm.mq.jmqi.MQDMPO;
/*       */ import com.ibm.mq.jmqi.MQGMO;
/*       */ import com.ibm.mq.jmqi.MQIMPO;
/*       */ import com.ibm.mq.jmqi.MQMD;
/*       */ import com.ibm.mq.jmqi.MQMHBO;
/*       */ import com.ibm.mq.jmqi.MQOD;
/*       */ import com.ibm.mq.jmqi.MQPD;
/*       */ import com.ibm.mq.jmqi.MQPMO;
/*       */ import com.ibm.mq.jmqi.MQSCO;
/*       */ import com.ibm.mq.jmqi.MQSD;
/*       */ import com.ibm.mq.jmqi.MQSMPO;
/*       */ import com.ibm.mq.jmqi.MQSRO;
/*       */ import com.ibm.mq.jmqi.MQSTS;
/*       */ import com.ibm.mq.jmqi.handles.Hconn;
/*       */ import com.ibm.mq.jmqi.handles.Hmsg;
/*       */ import com.ibm.mq.jmqi.handles.Hobj;
/*       */ import com.ibm.mq.jmqi.handles.PbyteBuffer;
/*       */ import com.ibm.mq.jmqi.handles.Phconn;
/*       */ import com.ibm.mq.jmqi.handles.Phmsg;
/*       */ import com.ibm.mq.jmqi.handles.Phobj;
/*       */ import com.ibm.mq.jmqi.handles.Pint;
/*       */ import com.ibm.mq.jmqi.handles.PtripletArray;
/*       */ import com.ibm.mq.jmqi.internal.Configuration;
/*       */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*       */ import com.ibm.mq.jmqi.internal.JmqiTools;
/*       */ import com.ibm.mq.jmqi.remote.impl.RemoteCCDT;
/*       */ import com.ibm.mq.jmqi.remote.impl.RemoteConnection;
/*       */ import com.ibm.mq.jmqi.remote.impl.RemoteConnectionPool;
/*       */ import com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue;
/*       */ import com.ibm.mq.jmqi.remote.impl.RemoteReconnectThread;
/*       */ import com.ibm.mq.jmqi.remote.impl.RemoteSession;
/*       */ import com.ibm.mq.jmqi.remote.impl.RemoteTagPool;
/*       */ import com.ibm.mq.jmqi.remote.impl.RemoteTls;
/*       */ import com.ibm.mq.jmqi.remote.rfp.RfpFAPMQCNO;
/*       */ import com.ibm.mq.jmqi.remote.rfp.RfpMPH;
/*       */ import com.ibm.mq.jmqi.remote.rfp.RfpMQAPI;
/*       */ import com.ibm.mq.jmqi.remote.rfp.RfpMQCLOSE;
/*       */ import com.ibm.mq.jmqi.remote.rfp.RfpMQCONN;
/*       */ import com.ibm.mq.jmqi.remote.rfp.RfpMQINQ;
/*       */ import com.ibm.mq.jmqi.remote.rfp.RfpMQOPEN_PRIV;
/*       */ import com.ibm.mq.jmqi.remote.rfp.RfpMQSET;
/*       */ import com.ibm.mq.jmqi.remote.rfp.RfpMQSTAT;
/*       */ import com.ibm.mq.jmqi.remote.rfp.RfpStructure;
/*       */ import com.ibm.mq.jmqi.remote.rfp.RfpTSH;
/*       */ import com.ibm.mq.jmqi.remote.rfp.RfpXAID;
/*       */ import com.ibm.mq.jmqi.remote.rfp.RfpXAIDS;
/*       */ import com.ibm.mq.jmqi.remote.rfp.RfpXA_COMPLETE;
/*       */ import com.ibm.mq.jmqi.remote.rfp.RfpXA_INFO;
/*       */ import com.ibm.mq.jmqi.remote.rfp.spi.RfpACTIVATESPIIN;
/*       */ import com.ibm.mq.jmqi.remote.rfp.spi.RfpACTIVATESPIINOUT;
/*       */ import com.ibm.mq.jmqi.remote.rfp.spi.RfpACTIVATESPIOUT;
/*       */ import com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIIN;
/*       */ import com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIINOUT;
/*       */ import com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIOUT;
/*       */ import com.ibm.mq.jmqi.remote.rfp.spi.RfpINSPI;
/*       */ import com.ibm.mq.jmqi.remote.rfp.spi.RfpNOTIFYSPIIN;
/*       */ import com.ibm.mq.jmqi.remote.rfp.spi.RfpNOTIFYSPIINOUT;
/*       */ import com.ibm.mq.jmqi.remote.rfp.spi.RfpNOTIFYSPIOUT;
/*       */ import com.ibm.mq.jmqi.remote.rfp.spi.RfpOPENSPIIN;
/*       */ import com.ibm.mq.jmqi.remote.rfp.spi.RfpOPENSPIINOUT;
/*       */ import com.ibm.mq.jmqi.remote.rfp.spi.RfpOPENSPIOUT;
/*       */ import com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIIN;
/*       */ import com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIINOUT;
/*       */ import com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIOUT;
/*       */ import com.ibm.mq.jmqi.remote.rfp.spi.RfpQUERYSPIIN;
/*       */ import com.ibm.mq.jmqi.remote.rfp.spi.RfpQUERYSPIINOUT;
/*       */ import com.ibm.mq.jmqi.remote.rfp.spi.RfpQUERYSPIOUT;
/*       */ import com.ibm.mq.jmqi.remote.rfp.spi.RfpSUBSCRIBESPIIN;
/*       */ import com.ibm.mq.jmqi.remote.rfp.spi.RfpSUBSCRIBESPIINOUT;
/*       */ import com.ibm.mq.jmqi.remote.rfp.spi.RfpSUBSCRIBESPIOUT;
/*       */ import com.ibm.mq.jmqi.remote.rfp.spi.RfpSYNCPOINTSPIIN;
/*       */ import com.ibm.mq.jmqi.remote.rfp.spi.RfpSYNCPOINTSPIINOUT;
/*       */ import com.ibm.mq.jmqi.remote.rfp.spi.RfpSYNCPOINTSPIOUT;
/*       */ import com.ibm.mq.jmqi.remote.rfp.spi.RfpUNSUBSCRIBESPIIN;
/*       */ import com.ibm.mq.jmqi.remote.rfp.spi.RfpUNSUBSCRIBESPIINOUT;
/*       */ import com.ibm.mq.jmqi.remote.rfp.spi.RfpUNSUBSCRIBESPIOUT;
/*       */ import com.ibm.mq.jmqi.remote.rfp.spi.RfpVerbArray;
/*       */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*       */ import com.ibm.mq.jmqi.system.JmqiComponent;
/*       */ import com.ibm.mq.jmqi.system.JmqiComponentTls;
/*       */ import com.ibm.mq.jmqi.system.JmqiConnectOptions;
/*       */ import com.ibm.mq.jmqi.system.JmqiMetaData;
/*       */ import com.ibm.mq.jmqi.system.JmqiRunnable;
/*       */ import com.ibm.mq.jmqi.system.JmqiSP;
/*       */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*       */ import com.ibm.mq.jmqi.system.JmqiTls;
/*       */ import com.ibm.mq.jmqi.system.LexContext;
/*       */ import com.ibm.mq.jmqi.system.LexFilterElement;
/*       */ import com.ibm.mq.jmqi.system.LexObjectSelector;
/*       */ import com.ibm.mq.jmqi.system.LpiAdoptUserOptions;
/*       */ import com.ibm.mq.jmqi.system.LpiCALLOPT;
/*       */ import com.ibm.mq.jmqi.system.LpiCHLAUTHQuery;
/*       */ import com.ibm.mq.jmqi.system.LpiNotifyDetails;
/*       */ import com.ibm.mq.jmqi.system.LpiPrivConnStruct;
/*       */ import com.ibm.mq.jmqi.system.LpiSD;
/*       */ import com.ibm.mq.jmqi.system.LpiSRD;
/*       */ import com.ibm.mq.jmqi.system.LpiUSD;
/*       */ import com.ibm.mq.jmqi.system.SpiActivate;
/*       */ import com.ibm.mq.jmqi.system.SpiGetOptions;
/*       */ import com.ibm.mq.jmqi.system.SpiOpenOptions;
/*       */ import com.ibm.mq.jmqi.system.SpiPutOptions;
/*       */ import com.ibm.mq.jmqi.system.SpiSyncPointOptions;
/*       */ import com.ibm.mq.jmqi.system.internal.ChannelEntry;
/*       */ import com.ibm.mq.jmqi.system.internal.ChannelListEntry;
/*       */ import com.ibm.mq.jmqi.system.internal.ThreadChannelEntry;
/*       */ import com.ibm.mq.jmqi.system.zrfp.Triplet;
/*       */ import com.ibm.msg.client.commonservices.cssystem.CSSystem;
/*       */ import com.ibm.msg.client.commonservices.trace.DumpableComponent;
/*       */ import com.ibm.msg.client.commonservices.trace.Trace;
/*       */ import java.io.PrintWriter;
/*       */ import java.io.UnsupportedEncodingException;
/*       */ import java.net.URI;
/*       */ import java.net.URISyntaxException;
/*       */ import java.net.URL;
/*       */ import java.net.URLEncoder;
/*       */ import java.nio.ByteBuffer;
/*       */ import java.security.AccessController;
/*       */ import java.security.PrivilegedAction;
/*       */ import java.util.ArrayList;
/*       */ import java.util.Arrays;
/*       */ import java.util.HashMap;
/*       */ import java.util.Iterator;
/*       */ import java.util.List;
/*       */ import java.util.Map;
/*       */ import java.util.Objects;
/*       */ import java.util.concurrent.atomic.AtomicInteger;
/*       */ import javax.transaction.xa.Xid;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ public class RemoteFAP
/*       */   extends JmqiImplementation
/*       */   implements JmqiComponent, JmqiMQ, JmqiSP, JmqiXA, DumpableComponent
/*       */ {
/*       */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/api/RemoteFAP.java";
/*       */   JmqiSystemEnvironment sysenv;
/*       */   private RemoteConnectionPool connectionFactory;
/*       */   private Configuration clientCfg;
/*       */   
/*       */   static {
/*   193 */     if (Trace.isOn) {
/*   194 */       Trace.data("com.ibm.mq.jmqi.remote.api.RemoteFAP", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/api/RemoteFAP.java");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*   221 */   private Map<URI, RemoteCCDT> ccdtCache = new HashMap<>();
/*       */   
/*       */   private static class NameListDefinition
/*       */   {
/*       */     private RemoteCCDT ccdt;
/*       */     private String qMgrName;
/*       */     private int transportType;
/*       */     private int hashCode;
/*       */     private boolean hashCodeComputed = false;
/*       */     
/*       */     NameListDefinition(RemoteCCDT ccdt, String qMgrName, int transportType) {
/*   232 */       if (Trace.isOn) {
/*   233 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.NameListDefinition", "<init>(RemoteCCDT,String,int)", new Object[] { ccdt, qMgrName, 
/*   234 */               Integer.valueOf(transportType) });
/*       */       }
/*   236 */       this.ccdt = ccdt;
/*   237 */       this.qMgrName = qMgrName;
/*   238 */       this.transportType = transportType;
/*   239 */       if (Trace.isOn) {
/*   240 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.NameListDefinition", "<init>(RemoteCCDT,String,int)");
/*       */       }
/*       */     }
/*       */ 
/*       */ 
/*       */     
/*       */     public int hashCode() {
/*   247 */       if (Trace.isOn) {
/*   248 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.NameListDefinition", "hashCode()");
/*       */       }
/*   250 */       if (!this.hashCodeComputed) {
/*   251 */         this.hashCode = this.ccdt.getCCDTFile().hashCode() * 3;
/*   252 */         this.hashCode += this.qMgrName.hashCode() * 5;
/*   253 */         this.hashCode += this.transportType;
/*   254 */         this.hashCodeComputed = true;
/*       */       } 
/*   256 */       if (Trace.isOn) {
/*   257 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.NameListDefinition", "hashCode()", 
/*   258 */             Integer.valueOf(this.hashCode));
/*       */       }
/*   260 */       return this.hashCode;
/*       */     }
/*       */ 
/*       */     
/*       */     public boolean equals(Object other) {
/*   265 */       if (Trace.isOn) {
/*   266 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.NameListDefinition", "equals(Object)", new Object[] { other });
/*       */       }
/*       */       
/*   269 */       if (other instanceof NameListDefinition) {
/*   270 */         NameListDefinition otherNde = (NameListDefinition)other;
/*       */         
/*   272 */         boolean traceRet1 = (this.ccdt.getCCDTFile().equals(otherNde.ccdt.getCCDTFile()) && this.qMgrName.equals(otherNde.qMgrName) && this.transportType == otherNde.transportType);
/*       */         
/*   274 */         if (Trace.isOn) {
/*   275 */           Trace.exit(this, "com.ibm.mq.jmqi.remote.api.NameListDefinition", "equals(Object)", 
/*   276 */               Boolean.valueOf(traceRet1), 1);
/*       */         }
/*   278 */         return traceRet1;
/*       */       } 
/*       */       
/*   281 */       if (Trace.isOn) {
/*   282 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.NameListDefinition", "equals(Object)", 
/*   283 */             Boolean.valueOf(false), 2);
/*       */       }
/*   285 */       return false;
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*   293 */   private HashMap<NameListDefinition, ChannelListEntry> nameLists = new HashMap<>();
/*       */ 
/*       */   
/*   296 */   private Object ccdtCacheLock = new CcdtCacheLock();
/*       */ 
/*       */ 
/*       */   
/*       */   private boolean loadingCcdt = false;
/*       */ 
/*       */ 
/*       */   
/*       */   private int jmqiCompId;
/*       */ 
/*       */   
/*   307 */   protected Object nameListLock = new NameListLock();
/*       */ 
/*       */   
/*       */   private static final int FAP_PTR_SIZE = 4;
/*       */ 
/*       */   
/*   313 */   private static AtomicInteger traceIdentifier = new AtomicInteger(1);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*   321 */   private int SSL_MIN_RESET_COUNT = 32768;
/*       */   
/*       */   private RemoteReconnectThread reconnectThread;
/*   324 */   private Object reconnectThreadLock = new ReconnectThreadLock();
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   private static final int DEFAULT_CCSID_DIST = 819;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   private static final int DEFAULT_CCSID_ZOS = 500;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   private static final int DEFAULT_CCSID_ISERIES = 37;
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public RemoteFAP(JmqiEnvironment jmqiEnv, int options) {
/*   345 */     super(jmqiEnv);
/*   346 */     if (Trace.isOn) {
/*   347 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "<init>(JmqiEnvironment,int)", new Object[] { jmqiEnv, 
/*   348 */             Integer.valueOf(options) });
/*       */     }
/*   350 */     if (!(this.env instanceof JmqiSystemEnvironment)) {
/*       */       
/*   352 */       RuntimeException traceRet1 = new RuntimeException();
/*   353 */       if (Trace.isOn) {
/*   354 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "<init>(JmqiEnvironment,int)", traceRet1);
/*       */       }
/*   356 */       throw traceRet1;
/*       */     } 
/*   358 */     this.sysenv = (JmqiSystemEnvironment)this.env;
/*   359 */     this.connectionFactory = new RemoteConnectionPool(jmqiEnv, this);
/*   360 */     this.clientCfg = this.env.getConfiguration();
/*       */     
/*   362 */     Trace.registerDumpableComponent(this);
/*       */     
/*   364 */     this.jmqiCompId = this.sysenv.registerComponent(this);
/*       */     
/*   366 */     if (Trace.isOn) {
/*   367 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "<init>(JmqiEnvironment,int)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public String getJmqiComponentName() {
/*   379 */     if (Trace.isOn) {
/*   380 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "getJmqiComponentName()", "getter", "com.ibm.mq.jmqi.remote");
/*       */     }
/*       */     
/*   383 */     return "com.ibm.mq.jmqi.remote";
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public JmqiComponentTls newTlsObject() {
/*   393 */     if (Trace.isOn) {
/*   394 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "newTlsObject()");
/*       */     }
/*   396 */     RemoteTls remoteTls = new RemoteTls(this.env);
/*       */     
/*   398 */     if (Trace.isOn) {
/*   399 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "newTlsObject()", remoteTls);
/*       */     }
/*   401 */     return (JmqiComponentTls)remoteTls;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   private RemoteCCDT getCcdt(URL ccdtUrl) throws JmqiException {
/*   415 */     if (Trace.isOn) {
/*   416 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "getCcdt(URL)", new Object[] { ccdtUrl });
/*       */     }
/*   418 */     RemoteCCDT ccdt = null;
/*   419 */     URI ccdtUri = null;
/*       */     try {
/*   421 */       ccdtUri = new URI(URLEncoder.encode(ccdtUrl.toExternalForm(), "utf-8"));
/*       */     }
/*   423 */     catch (URISyntaxException e1) {
/*   424 */       if (Trace.isOn) {
/*   425 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "getCcdt(URL)", e1, 1);
/*       */       }
/*       */       
/*   428 */       JmqiException traceRet1 = new JmqiException(this.env, 9231, new String[] { ccdtUrl.toString() }, 2, 2539, null);
/*       */       
/*   430 */       if (Trace.isOn) {
/*   431 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "getCcdt(URL)", (Throwable)traceRet1, 1);
/*       */       }
/*   433 */       throw traceRet1;
/*       */     }
/*   435 */     catch (UnsupportedEncodingException e) {
/*   436 */       if (Trace.isOn) {
/*   437 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "getCcdt(URL)", e, 2);
/*       */       }
/*   439 */       JmqiException traceRet2 = new JmqiException(this.env, 9231, new String[] { ccdtUrl.toString() }, 2, 2539, null);
/*       */       
/*   441 */       if (Trace.isOn) {
/*   442 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "getCcdt(URL)", (Throwable)traceRet2, 2);
/*       */       }
/*   444 */       throw traceRet2;
/*       */     } 
/*       */ 
/*       */     
/*   448 */     synchronized (this.ccdtCacheLock) {
/*   449 */       ccdt = this.ccdtCache.get(ccdtUri);
/*   450 */       if (ccdt != null) {
/*       */ 
/*       */         
/*   453 */         boolean ccdtModified = ccdt.hasCcdtBeenModifiedSincePreviouslyParsed();
/*   454 */         if (ccdtModified) {
/*   455 */           clearNameListOfEntriesWithGivenCCDT(ccdt);
/*   456 */           ccdt = null;
/*   457 */           this.ccdtCache.remove(ccdtUri);
/*       */         } 
/*       */       } 
/*       */       
/*   461 */       if (ccdt == null) {
/*       */ 
/*       */ 
/*       */         
/*   465 */         while (this.loadingCcdt) {
/*       */           try {
/*   467 */             this.ccdtCacheLock.wait();
/*       */           }
/*   469 */           catch (InterruptedException e) {
/*   470 */             if (Trace.isOn) {
/*   471 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "getCcdt(URL)", e, 3);
/*       */             }
/*       */           } 
/*       */         } 
/*       */ 
/*       */         
/*   477 */         ccdt = this.ccdtCache.get(ccdtUri);
/*       */         
/*   479 */         if (ccdt == null)
/*       */         {
/*   481 */           this.loadingCcdt = true;
/*       */         }
/*       */       } 
/*       */     } 
/*       */ 
/*       */     
/*   487 */     if (ccdt == null) {
/*       */       try {
/*   489 */         ccdt = new RemoteCCDT(this.env, ccdtUrl);
/*       */ 
/*       */         
/*   492 */         synchronized (this.ccdtCacheLock) {
/*   493 */           this.ccdtCache.put(ccdtUri, ccdt);
/*       */         } 
/*       */       } finally {
/*       */         
/*   497 */         if (Trace.isOn) {
/*   498 */           Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "getCcdt(URL)");
/*       */         }
/*       */ 
/*       */         
/*   502 */         synchronized (this.ccdtCacheLock) {
/*   503 */           this.loadingCcdt = false;
/*   504 */           this.ccdtCacheLock.notifyAll();
/*       */         } 
/*       */       } 
/*       */     }
/*       */     
/*   509 */     if (Trace.isOn) {
/*   510 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "getCcdt(URL)", ccdt);
/*       */     }
/*   512 */     return ccdt;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   private void clearNameListOfEntriesWithGivenCCDT(RemoteCCDT ccdt) {
/*   521 */     synchronized (this.nameListLock) {
/*   522 */       Iterator<Map.Entry<NameListDefinition, ChannelListEntry>> it = this.nameLists.entrySet().iterator();
/*   523 */       while (it.hasNext()) {
/*   524 */         Map.Entry<NameListDefinition, ChannelListEntry> entry = it.next();
/*   525 */         if (Objects.equals(ccdt, (entry.getKey()).ccdt)) {
/*   526 */           if (Trace.isOn) {
/*   527 */             Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "clearNameListOfEntriesWithGivenCCDT(RemoteCCDT)", "Removing entry from nameList", entry
/*       */                 
/*   529 */                 .getKey());
/*       */           }
/*   531 */           it.remove();
/*       */         } 
/*       */       } 
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public ChannelListEntry getNameList(RemoteCCDT ccdt, String qMgrName, int transportType) throws JmqiException {
/*   552 */     if (Trace.isOn) {
/*   553 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "getNameList(RemoteCCDT,String,int)", new Object[] { ccdt, qMgrName, 
/*   554 */             Integer.valueOf(transportType) });
/*       */     }
/*       */     
/*   557 */     ChannelListEntry nameEntry = null;
/*       */     
/*   559 */     synchronized (this.nameListLock) {
/*   560 */       NameListDefinition def = new NameListDefinition(ccdt, qMgrName, transportType);
/*       */ 
/*       */       
/*   563 */       if ((nameEntry = this.nameLists.get(def)) == null) {
/*       */         
/*   565 */         String matchString = null;
/*   566 */         int asteriskPos = qMgrName.indexOf('*');
/*   567 */         if (asteriskPos != -1) {
/*       */           
/*   569 */           if (asteriskPos != 0) {
/*       */             
/*   571 */             JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2058, null);
/*       */ 
/*       */             
/*   574 */             if (Trace.isOn) {
/*   575 */               Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "getNameList(RemoteCCDT,String,int)", (Throwable)traceRet1);
/*       */             }
/*       */             
/*   578 */             throw traceRet1;
/*       */           } 
/*       */ 
/*       */           
/*   582 */           if (qMgrName.length() != 1) {
/*   583 */             matchString = qMgrName.substring(1);
/*       */           } else {
/*       */             
/*   586 */             matchString = "";
/*       */           }
/*       */         
/*       */         }
/*       */         else {
/*       */           
/*   592 */           matchString = qMgrName;
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*   598 */         if (Trace.isOn) {
/*   599 */           Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "getNameList(RemoteCCDT,String,int)", "Looking in CCDT for entry that matches ", matchString);
/*       */         }
/*       */         
/*   602 */         nameEntry = createNameListEntryFromCCDT(ccdt, matchString, transportType);
/*   603 */         this.nameLists.put(def, nameEntry);
/*       */       } 
/*       */     } 
/*       */     
/*   607 */     if (Trace.isOn) {
/*   608 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "getNameList(RemoteCCDT,String,int)", nameEntry);
/*       */     }
/*   610 */     return nameEntry;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public ChannelListEntry getNameListFromMQCD(MQCD mqcd, int transport) throws JmqiException {
/*   626 */     if (Trace.isOn) {
/*   627 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "getNameListFromMQCD(MQCD,int)", new Object[] { mqcd, 
/*   628 */             Integer.valueOf(transport) });
/*       */     }
/*   630 */     ChannelListEntry nameEntry = null;
/*   631 */     nameEntry = new ChannelListEntry(this.env, transport);
/*   632 */     nameEntry.setName(mqcd.getQMgrName());
/*   633 */     nameEntry.setUseCount(1);
/*   634 */     nameEntry.setUpdateRequired(false);
/*   635 */     nameEntry.setTotalWeight(0);
/*   636 */     nameEntry.setModTime(System.currentTimeMillis());
/*       */ 
/*       */ 
/*       */     
/*   640 */     List<MQCD> channelDefinitions = new ArrayList<>();
/*   641 */     String[] connectionNames = mqcd.getConnectionName().split(",");
/*   642 */     for (int i = 0; i < connectionNames.length; i++) {
/*       */       try {
/*   644 */         MQCD subDefinition = (MQCD)mqcd.clone();
/*   645 */         subDefinition.setConnectionName(connectionNames[i]);
/*   646 */         channelDefinitions.add(subDefinition);
/*       */       }
/*   648 */       catch (CloneNotSupportedException cloneNotSupportedException) {}
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*   654 */     nameEntry.createChannelEntryLists(channelDefinitions.iterator());
/*       */     
/*   656 */     if (Trace.isOn) {
/*   657 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "getNameListFromMQCD(MQCD,int)", nameEntry);
/*       */     }
/*   659 */     return nameEntry;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   private ChannelListEntry createNameListEntryFromCCDT(final RemoteCCDT ccdt, String name, int transport) throws JmqiException {
/*   678 */     if (Trace.isOn) {
/*   679 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "createNameListEntryFromCCDT(final RemoteCCDT,String,int)", new Object[] { ccdt, name, 
/*       */             
/*   681 */             Integer.valueOf(transport) });
/*       */     }
/*   683 */     final ChannelListEntry nameEntry = new ChannelListEntry(this.env, transport);
/*   684 */     nameEntry.setName(name);
/*   685 */     nameEntry.setUseCount(1);
/*   686 */     nameEntry.setUpdateRequired(false);
/*   687 */     nameEntry.setTotalWeight(0);
/*   688 */     AccessController.doPrivileged(new PrivilegedAction()
/*       */         {
/*       */           public Object run()
/*       */           {
/*   692 */             if (Trace.isOn) {
/*   693 */               Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "run()");
/*       */             }
/*       */             
/*   696 */             if (ccdt.getCCDTFile() != null) {
/*   697 */               nameEntry.setChannelFile(ccdt.getCCDTFile().getAbsolutePath());
/*       */             } else {
/*       */               
/*   700 */               nameEntry.setChannelFile(null);
/*       */             } 
/*   702 */             if (Trace.isOn) {
/*   703 */               Trace.exit(this, "com.ibm.mq.jmqi.remote.api.null", "run()", null);
/*       */             }
/*   705 */             return null;
/*       */           }
/*       */         });
/*   708 */     nameEntry.setModTime(ccdt.getCachedLastChangeTime());
/*       */ 
/*       */     
/*   711 */     nameEntry.createChannelEntryLists(ccdt.getChannelDefinitions());
/*       */     
/*   713 */     if (Trace.isOn) {
/*   714 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "createNameListEntryFromCCDT(final RemoteCCDT,String,int)", nameEntry);
/*       */     }
/*       */     
/*   717 */     return nameEntry;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   private boolean querySyncDelivery(RemoteTls tls, RemoteHconn remoteHconn, RemoteHobj objectHandle, MQPMO putMessageOpts, MQMD messageDescriptor) throws JmqiException {
/*   747 */     if (Trace.isOn) {
/*   748 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "querySyncDelivery(RemoteTls,RemoteHconn,RemoteHobj,MQPMO,MQMD)", new Object[] { tls, remoteHconn, objectHandle, putMessageOpts, messageDescriptor });
/*       */     }
/*       */ 
/*       */ 
/*       */     
/*   753 */     int requestedOptions = putMessageOpts.getOptions();
/*   754 */     if ((requestedOptions & 0x10000) != 0 && (requestedOptions & 0x20000) != 0) {
/*   755 */       JmqiException jmqiException = new JmqiException(this.env, -1, null, 2, 2046, null);
/*       */ 
/*       */       
/*   758 */       if (Trace.isOn) {
/*   759 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "querySyncDelivery(RemoteTls,RemoteHconn,RemoteHobj,MQPMO,MQMD)", (Throwable)jmqiException);
/*       */       }
/*       */       
/*   762 */       throw jmqiException;
/*       */     } 
/*       */ 
/*       */     
/*   766 */     int requestedAsyncOpts = requestedOptions & 0x30000;
/*       */ 
/*       */     
/*   769 */     int requestedTagOpts = requestedOptions & 0xC0;
/*       */ 
/*       */ 
/*       */     
/*   773 */     int chosenAsyncOpt = 0;
/*   774 */     int chosenTagOpts = requestedTagOpts;
/*       */ 
/*       */ 
/*       */     
/*   778 */     if ((requestedAsyncOpts & 0x20000) != 0 || (requestedAsyncOpts == 0 && objectHandle != null && objectHandle
/*   779 */       .getDefaultPutResponseType() == 1)) {
/*       */       
/*   781 */       chosenAsyncOpt = 131072;
/*       */ 
/*       */     
/*       */     }
/*   785 */     else if (requestedAsyncOpts == 0 && this.clientCfg.getBoolValue(Configuration.ENV_MQPUT1DEFSYNC) && objectHandle == null) {
/*       */       
/*   787 */       chosenAsyncOpt = 131072;
/*       */     }
/*   789 */     else if (requestedAsyncOpts == 0 && objectHandle == null && (requestedOptions & 0x2) == 0) {
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*   794 */       chosenAsyncOpt = 131072;
/*       */     }
/*   796 */     else if ((requestedOptions & 0x8000) != 0) {
/*       */       
/*   798 */       chosenAsyncOpt = 131072;
/*       */     
/*       */     }
/*       */     else {
/*       */ 
/*       */       
/*   804 */       boolean persistent = (messageDescriptor.getPersistence() == 1 || (messageDescriptor.getPersistence() == 2 && objectHandle != null && objectHandle.getDefaultPersistence() == 1));
/*   805 */       if (persistent && (requestedOptions & 0x2) == 0) {
/*       */         
/*   807 */         chosenAsyncOpt = 131072;
/*       */       }
/*       */       else {
/*       */         
/*   811 */         chosenAsyncOpt = 65536;
/*       */ 
/*       */         
/*   814 */         if ((requestedOptions & 0x800) == 0) {
/*       */           
/*   816 */           if ((requestedOptions & 0x400) == 0)
/*       */           {
/*       */             
/*   819 */             messageDescriptor.setApplIdentityData("");
/*       */           }
/*       */ 
/*       */           
/*   823 */           messageDescriptor.setPutApplType(28);
/*   824 */           messageDescriptor.setPutApplName(remoteHconn.getApplicationName());
/*       */ 
/*       */           
/*   827 */           messageDescriptor.setApplOriginData("");
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*   837 */         int idsRequired = 0;
/*   838 */         boolean msgIdRequired = false;
/*       */         
/*   840 */         if ((requestedTagOpts & 0x40) != 0 || Arrays.equals(MQConstants.MQMI_NONE, messageDescriptor.getMsgId())) {
/*   841 */           chosenTagOpts &= 0xFFFFFFBF;
/*   842 */           msgIdRequired = true;
/*   843 */           idsRequired++;
/*       */         } 
/*       */         
/*   846 */         boolean correlIdRequired = false;
/*       */         
/*   848 */         if ((requestedTagOpts & 0x80) != 0) {
/*   849 */           chosenTagOpts &= 0xFFFFFF7F;
/*   850 */           correlIdRequired = true;
/*   851 */           idsRequired++;
/*       */         } 
/*       */         
/*   854 */         if (idsRequired > 0) {
/*   855 */           int i = 0;
/*   856 */           RemoteTagPool tagPool = remoteHconn.getIdTagPool();
/*   857 */           byte[][] ids = tagPool.getTags(tls, idsRequired, remoteHconn.getSession());
/*       */           
/*   859 */           if (msgIdRequired) {
/*   860 */             messageDescriptor.setMsgId(ids[i++]);
/*       */           }
/*   862 */           if (correlIdRequired) {
/*   863 */             messageDescriptor.setCorrelId(ids[i++]);
/*       */           }
/*       */         } 
/*       */       } 
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*   872 */     putMessageOpts.setOptions(requestedOptions - requestedAsyncOpts + chosenAsyncOpt - requestedTagOpts + chosenTagOpts);
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*   877 */     boolean traceRet1 = (chosenAsyncOpt == 131072);
/*       */     
/*   879 */     if (Trace.isOn) {
/*   880 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "querySyncDelivery(RemoteTls,RemoteHconn,RemoteHobj,MQPMO,MQMD)", 
/*   881 */           Boolean.valueOf(traceRet1));
/*       */     }
/*   883 */     return traceRet1;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   private RemoteHconn getRemoteHconn(RemoteTls tls, Hconn hConn) throws JmqiException {
/*   899 */     if (Trace.isOn) {
/*   900 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "getRemoteHconn(RemoteTls,Hconn)", new Object[] { tls, hConn });
/*       */     }
/*       */ 
/*       */     
/*   904 */     if (tls.inExit) {
/*   905 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2219, null);
/*       */ 
/*       */       
/*   908 */       if (Trace.isOn) {
/*   909 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "getRemoteHconn(RemoteTls,Hconn)", (Throwable)traceRet1, 1);
/*       */       }
/*       */       
/*   912 */       throw traceRet1;
/*       */     } 
/*       */     
/*   915 */     if (hConn == null || !(hConn instanceof RemoteHconn)) {
/*   916 */       JmqiException traceRet2 = new JmqiException(this.env, -1, null, 2, 2018, null);
/*       */       
/*   918 */       if (Trace.isOn) {
/*   919 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "getRemoteHconn(RemoteTls,Hconn)", (Throwable)traceRet2, 2);
/*       */       }
/*       */       
/*   922 */       throw traceRet2;
/*       */     } 
/*   924 */     RemoteHconn traceRet3 = (RemoteHconn)hConn;
/*   925 */     if (Trace.isOn) {
/*   926 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "getRemoteHconn(RemoteTls,Hconn)", traceRet3);
/*       */     }
/*   928 */     return traceRet3;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQCB(Hconn hConn, int operationP, MQCBD pCallbackDescP, Hobj hobjP, MQMD pMsgDescP, MQGMO getMsgOptsP, Pint pCompCode, Pint pReason) {
/*   955 */     if (Trace.isOn) {
/*   956 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQCB(Hconn,int,MQCBD,Hobj,MQMD,MQGMO,final Pint,final Pint)", new Object[] { hConn, 
/*       */             
/*   958 */             Integer.valueOf(operationP), pCallbackDescP, hobjP, pMsgDescP, getMsgOptsP, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */     
/*   962 */     int operation = operationP;
/*   963 */     MQMD pMsgDesc = pMsgDescP;
/*   964 */     MQGMO getMsgOpts = getMsgOptsP;
/*   965 */     Hobj hobj = hobjP;
/*   966 */     RemoteHobj remoteHobj = null;
/*   967 */     MQCBD pCallbackDesc = pCallbackDescP;
/*   968 */     RemoteProxyQueue pq = null;
/*   969 */     RemoteHconn remoteHconn = null;
/*   970 */     RemoteSession remoteSession = null;
/*       */ 
/*       */     
/*   973 */     if (hobj instanceof RemoteHobj) {
/*   974 */       remoteHobj = (RemoteHobj)hobj;
/*   975 */       remoteHobj.setupCallback(pCallbackDesc, operation, pMsgDesc, getMsgOpts);
/*       */     } 
/*   977 */     RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*   978 */     JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/*   979 */     jmqiTls.lastException = null;
/*   980 */     tls.inMQCTLorMQCB = true;
/*       */     
/*       */     try {
/*   983 */       remoteHconn = getRemoteHconn(tls, hConn);
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*   988 */       remoteHconn.enterCall(tls.isDispatchThread, true);
/*       */       
/*       */       try {
/*   991 */         remoteSession = remoteHconn.getSession();
/*       */         
/*   993 */         if (remoteSession.getParentConnection().getFapLevel() < 9) {
/*   994 */           JmqiException traceRet2 = new JmqiException(this.env, -1, null, 2, 2298, null);
/*       */ 
/*       */           
/*   997 */           if (Trace.isOn) {
/*   998 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQCB(Hconn,int,MQCBD,Hobj,MQMD,MQGMO,final Pint,final Pint)", (Throwable)traceRet2, 1);
/*       */           }
/*       */           
/*  1001 */           throw traceRet2;
/*       */         } 
/*       */ 
/*       */ 
/*       */         
/*  1006 */         if (!remoteSession.getParentConnection().isMultiplexingEnabled()) {
/*  1007 */           JmqiException traceRet3 = new JmqiException(this.env, -1, null, 2, 2012, null);
/*       */           
/*  1009 */           if (Trace.isOn) {
/*  1010 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQCB(Hconn,int,MQCBD,Hobj,MQMD,MQGMO,final Pint,final Pint)", (Throwable)traceRet3, 2);
/*       */           }
/*       */           
/*  1013 */           throw traceRet3;
/*       */         } 
/*       */ 
/*       */         
/*  1017 */         remoteSession.checkIfDisconnected();
/*       */         
/*  1019 */         pCompCode.x = 0;
/*  1020 */         pReason.x = 0;
/*       */         
/*  1022 */         switch (operation) {
/*       */           case 256:
/*       */           case 512:
/*       */           case 65536:
/*       */           case 65792:
/*       */           case 131072:
/*       */           case 131328:
/*       */             break;
/*       */ 
/*       */ 
/*       */           
/*       */           case 66048:
/*  1034 */             operation &= 0xFFFEFFFF;
/*       */             break;
/*       */           default:
/*  1037 */             pCompCode.x = 2;
/*  1038 */             pReason.x = 2488;
/*       */             
/*  1040 */             if (Trace.isOn) {
/*  1041 */               Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQCB(Hconn,int,MQCBD,Hobj,MQMD,MQGMO,final Pint,final Pint)", 1);
/*       */             }
/*       */             return;
/*       */         } 
/*       */ 
/*       */ 
/*       */         
/*  1048 */         if (pCallbackDesc != null) {
/*       */           
/*  1050 */           validateMQCBD(pCallbackDesc);
/*       */         }
/*       */         else {
/*       */           
/*  1054 */           pCallbackDesc = this.env.newMQCBD();
/*       */         } 
/*       */ 
/*       */         
/*  1058 */         if ((pCallbackDesc.getOptions() & 0x2000) != 0 && remoteHconn.isQuiescing()) {
/*  1059 */           pCompCode.x = 2;
/*  1060 */           pReason.x = 2202;
/*       */           
/*  1062 */           if (Trace.isOn) {
/*  1063 */             Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQCB(Hconn,int,MQCBD,Hobj,MQMD,MQGMO,final Pint,final Pint)", 2);
/*       */           }
/*       */ 
/*       */           
/*       */           return;
/*       */         } 
/*       */ 
/*       */         
/*  1071 */         if (pCallbackDesc.getCallbackType() == 2) {
/*  1072 */           hobj = null;
/*       */           
/*  1074 */           if (remoteHconn.isReconnectable()) {
/*  1075 */             remoteHconn.setupEventHandler(pCallbackDesc, operation);
/*       */           }
/*       */         } 
/*       */ 
/*       */         
/*  1080 */         if (pMsgDesc == null) {
/*  1081 */           pMsgDesc = this.env.newMQMD();
/*       */         }
/*       */         
/*  1084 */         if (getMsgOpts == null) {
/*  1085 */           getMsgOpts = this.env.newMQGMO();
/*       */         }
/*       */ 
/*       */         
/*  1089 */         if (hobj != null) {
/*  1090 */           pq = remoteHobj.getProxyQueue();
/*  1091 */           if (pq == null) {
/*  1092 */             pCompCode.x = 2;
/*  1093 */             pReason.x = 2019;
/*       */             
/*  1095 */             if (Trace.isOn) {
/*  1096 */               Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQCB(Hconn,int,MQCBD,Hobj,MQMD,MQGMO,final Pint,final Pint)", 3);
/*       */             }
/*       */ 
/*       */             
/*       */             return;
/*       */           } 
/*       */           
/*  1103 */           if ((operation & 0x100) != 0) {
/*  1104 */             pq.mqcbRegisterMC(pCallbackDesc, pMsgDesc, getMsgOpts);
/*       */           }
/*       */           
/*  1107 */           if ((operation & 0x20000) != 0) {
/*  1108 */             pq.mqcbResumeMC();
/*       */           }
/*       */           
/*  1111 */           if ((operation & 0x10000) != 0) {
/*  1112 */             pq.mqcbSuspendMC();
/*       */           }
/*       */           
/*  1115 */           if ((operation & 0x200) != 0) {
/*  1116 */             pq.mqcbDeRegisterMC(!tls.isDispatchThread);
/*       */           }
/*       */         }
/*       */         else {
/*       */           
/*  1121 */           if ((operation & 0x100) != 0) {
/*  1122 */             remoteHconn.mqcbRegisterEH(pCallbackDesc);
/*       */           }
/*       */           
/*  1125 */           if ((operation & 0x20000) != 0) {
/*  1126 */             remoteHconn.mqcbResumeEH();
/*       */           }
/*       */           
/*  1129 */           if ((operation & 0x10000) != 0) {
/*  1130 */             remoteHconn.mqcbSuspendEH();
/*       */           }
/*       */           
/*  1133 */           if ((operation & 0x200) != 0) {
/*  1134 */             remoteHconn.mqcbDeregisterEH();
/*       */           }
/*       */         } 
/*       */       } finally {
/*       */         
/*  1139 */         if (Trace.isOn) {
/*  1140 */           Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQCB(Hconn,int,MQCBD,Hobj,MQMD,MQGMO,final Pint,final Pint)");
/*       */         }
/*       */         
/*  1143 */         if (remoteHconn != null) {
/*       */           
/*       */           try {
/*       */             
/*  1147 */             remoteHconn.leaveCall(pReason.x);
/*       */           }
/*  1149 */           catch (JmqiException e) {
/*  1150 */             if (Trace.isOn) {
/*  1151 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQCB(Hconn,int,MQCBD,Hobj,MQMD,MQGMO,final Pint,final Pint)", (Throwable)e, 1);
/*       */             }
/*       */           }
/*       */         
/*       */         }
/*       */       }
/*       */     
/*  1158 */     } catch (JmqiException e) {
/*  1159 */       if (Trace.isOn) {
/*  1160 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQCB(Hconn,int,MQCBD,Hobj,MQMD,MQGMO,final Pint,final Pint)", (Throwable)e, 2);
/*       */       }
/*       */       
/*  1163 */       pCompCode.x = e.getCompCode();
/*  1164 */       pReason.x = e.getReason();
/*       */     } 
/*       */ 
/*       */ 
/*       */     
/*  1169 */     if (0 != pReason.x && 
/*  1170 */       remoteHconn.isReconnectable() && 
/*  1171 */       RemoteHconn.isReconnectableReasonCode(pReason.x) && 
/*  1172 */       !remoteHconn.hasFailed()) {
/*       */ 
/*       */ 
/*       */       
/*  1176 */       pCompCode.x = 0;
/*  1177 */       pReason.x = 0;
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  1183 */     if (remoteHconn.hasFailed()) {
/*  1184 */       pCompCode.x = remoteHconn.getReconnectionFailureCompCode();
/*  1185 */       pReason.x = remoteHconn.getReconnectionFailureReason();
/*  1186 */       jmqiTls.lastException = remoteHconn.getReconnectionFailureException();
/*       */     } 
/*       */     
/*  1189 */     tls.inMQCTLorMQCB = false;
/*       */     
/*  1191 */     if (Trace.isOn) {
/*  1192 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQCB(Hconn,int,MQCBD,Hobj,MQMD,MQGMO,final Pint,final Pint)", 4);
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQCONN(String name, Phconn pHconn, Pint pCompCode, Pint pReason) {
/*  1212 */     if (Trace.isOn) {
/*  1213 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQCONN(String,Phconn,Pint,Pint)", new Object[] { name, pHconn, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */ 
/*       */     
/*  1218 */     jmqiConnect(name, null, null, null, pHconn, pCompCode, pReason);
/*       */     
/*  1220 */     if (Trace.isOn) {
/*  1221 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQCONN(String,Phconn,Pint,Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQCONNX(String name, MQCNO connectionOptions, Phconn pHconn, Pint pCompCode, Pint pReason) {
/*  1242 */     if (Trace.isOn) {
/*  1243 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQCONNX(String,MQCNO,Phconn,Pint,Pint)", new Object[] { name, connectionOptions, pHconn, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */     
/*  1247 */     if (connectionOptions == null) {
/*  1248 */       pCompCode.x = 2;
/*  1249 */       pReason.x = 2139;
/*       */       
/*  1251 */       if (Trace.isOn) {
/*  1252 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQCONNX(String,MQCNO,Phconn,Pint,Pint)", 1);
/*       */       }
/*       */       
/*       */       return;
/*       */     } 
/*  1257 */     jmqiConnect(name, null, connectionOptions, null, pHconn, pCompCode, pReason);
/*       */     
/*  1259 */     if (Trace.isOn) {
/*  1260 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQCONNX(String,MQCNO,Phconn,Pint,Pint)", 2);
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void jmqiCancelWaitingGets(Hconn notifyHconn, Hconn getterHconn, Hconn helperHconn, Pint pCompCode, Pint pReason) {
/*  1274 */     if (Trace.isOn) {
/*  1275 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiCancelWaitingGets(Hconn, Hconn, Hconn, Pint, Pint)", new Object[] { notifyHconn, getterHconn, helperHconn, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */ 
/*       */     
/*  1280 */     RemoteHconn hconnForAction = null;
/*       */ 
/*       */     
/*  1283 */     RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  1284 */     JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/*  1285 */     jmqiTls.lastException = null;
/*       */     try {
/*       */       try {
/*  1288 */         RemoteHconn getterRemoteHconn = getRemoteHconn(tls, getterHconn);
/*  1289 */         RemoteHconn notifyRemoteHconn = getRemoteHconn(tls, notifyHconn);
/*  1290 */         RemoteSession getterRemoteSession = getterRemoteHconn.getSession();
/*       */         
/*  1292 */         RemoteConnection parentConn = getterRemoteSession.getParentConnection();
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  1301 */         if (parentConn.isMultiplexSyncGetCapable()) {
/*       */ 
/*       */           
/*  1304 */           hconnForAction = notifyRemoteHconn;
/*       */         }
/*       */         else {
/*       */           
/*  1308 */           hconnForAction = getterRemoteHconn;
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  1316 */         hconnForAction.enterNotifyCall(tls.isDispatchThread, true);
/*  1317 */         JmqiTools.cancelWaitingGets((JmqiEnvironment)this.sysenv, this, notifyHconn, getterHconn, helperHconn, pCompCode, pReason);
/*       */       } finally {
/*       */         
/*  1320 */         hconnForAction.leaveNotifyCall(pReason.x, true);
/*       */       }
/*       */     
/*  1323 */     } catch (JmqiException e) {
/*  1324 */       if (Trace.isOn) {
/*  1325 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiCancelWaitingGets(Hconn, Hconn, Hconn, Pint, Pint)", (Throwable)e);
/*       */       }
/*       */       
/*  1328 */       if (e.getCompCode() != 2 || e.getReason() != 2009) {
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  1333 */         jmqiTls.lastException = e;
/*  1334 */         pCompCode.x = e.getCompCode();
/*  1335 */         pReason.x = e.getReason();
/*       */       } 
/*       */     } 
/*       */     
/*  1339 */     if (Trace.isOn) {
/*  1340 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiCancelWaitingGets(Hconn, Hconn, Hconn, Pint, Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void jmqiConnect(String name, JmqiConnectOptions pJmqiConnectOpts, MQCNO connectionOptions, Hconn parentHconn, Phconn pHconn, Pint pCompCode, Pint pReason) {
/*  1366 */     if (Trace.isOn) {
/*  1367 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,Pint,Pint)", new Object[] { name, pJmqiConnectOpts, connectionOptions, parentHconn, pHconn, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  1373 */     if (Trace.isOn) {
/*  1374 */       Trace.data(this, "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,Pint,Pint)", "__________");
/*  1375 */       Trace.data(this, "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,Pint,Pint)", "jmqiConnect >>");
/*  1376 */       Trace.traceData(this, "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,Pint,Pint)", "JMQI ConnectOpts", pJmqiConnectOpts);
/*       */       
/*  1378 */       Trace.traceData(this, "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,Pint,Pint)", "ConnectOpts", connectionOptions);
/*       */       
/*  1380 */       Trace.traceData(this, "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,Pint,Pint)", "parentHconn", parentHconn);
/*       */       
/*  1382 */       Trace.traceData(this, "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,Pint,Pint)", "Hconn", pHconn);
/*       */       
/*  1384 */       Trace.traceData(this, "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,Pint,Pint)", "CompCode", pCompCode);
/*       */       
/*  1386 */       Trace.traceData(this, "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,Pint,Pint)", "Reason", pReason);
/*       */       
/*  1388 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,Pint,Pint)", "__________");
/*       */     } 
/*       */ 
/*       */     
/*  1392 */     jmqiConnect(name, pJmqiConnectOpts, connectionOptions, parentHconn, pHconn, pCompCode, pReason, null);
/*       */ 
/*       */     
/*  1395 */     if (Trace.isOn) {
/*  1396 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,Pint,Pint)", "__________");
/*       */       
/*  1398 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,Pint,Pint)", "jmqiConnect <<");
/*       */       
/*  1400 */       Trace.traceData(this, "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,Pint,Pint)", "JMQI ConnectOpts", pJmqiConnectOpts);
/*       */       
/*  1402 */       Trace.traceData(this, "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,Pint,Pint)", "ConnectOpts", connectionOptions);
/*       */       
/*  1404 */       Trace.traceData(this, "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,Pint,Pint)", "parentHconn", parentHconn);
/*       */       
/*  1406 */       Trace.traceData(this, "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,Pint,Pint)", "Hconn", pHconn);
/*       */       
/*  1408 */       Trace.traceData(this, "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,Pint,Pint)", "CompCode", pCompCode);
/*       */       
/*  1410 */       Trace.traceData(this, "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,Pint,Pint)", "Reason", pReason);
/*       */       
/*  1412 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,Pint,Pint)", "__________");
/*       */     } 
/*       */ 
/*       */     
/*  1416 */     if (Trace.isOn) {
/*  1417 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,Pint,Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void jmqiConnect(String name, JmqiConnectOptions pJmqiConnectOpts, MQCNO connectionOptions, Hconn parentHconn, Phconn pHconn, Pint pCompCode, Pint pReason, RemoteHconn reconnectHconn) {
/*  1444 */     if (Trace.isOn) {
/*  1445 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,Pint,Pint, RemoteHconn)", new Object[] { name, pJmqiConnectOpts, connectionOptions, parentHconn, pHconn, pCompCode, pReason, reconnectHconn });
/*       */     }
/*       */ 
/*       */ 
/*       */     
/*  1450 */     Connector connector = new Connector(pJmqiConnectOpts, connectionOptions, reconnectHconn);
/*  1451 */     connector.jmqiConnect(name, pHconn, parentHconn, pCompCode, pReason);
/*       */     
/*  1453 */     RemoteParentHconn rpHconn = (RemoteParentHconn)parentHconn;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  1459 */     if (0 != pReason.x && rpHconn != null && rpHconn
/*       */       
/*  1461 */       .isReconnectable() && 
/*  1462 */       !threadIsReconnectThread() && 
/*  1463 */       RemoteHconn.isReconnectableReasonCode(pReason.x) && 
/*  1464 */       !rpHconn.hasFailed()) {
/*       */       try {
/*  1466 */         rpHconn.initiateReconnection(rpHconn.getSessionNoWait());
/*  1467 */         jmqiConnect(name, pJmqiConnectOpts, connectionOptions, parentHconn, pHconn, pCompCode, pReason, reconnectHconn);
/*       */       }
/*  1469 */       catch (JmqiException je) {
/*  1470 */         if (Trace.isOn) {
/*  1471 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,Pint,Pint, RemoteHconn)", (Throwable)je);
/*       */         }
/*       */       } 
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  1481 */     if (Trace.isOn) {
/*  1482 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,Pint,Pint, RemoteHconn)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   private JmqiException getMostInterestingException(JmqiException exchain) {
/*  1489 */     JmqiException bestSoFar = exchain;
/*  1490 */     JmqiException jmqiException1 = exchain;
/*       */     
/*  1492 */     if (Trace.isOn) {
/*  1493 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "getMostInterestingException(JmqiException)", new Object[] { exchain });
/*       */     }
/*       */ 
/*       */     
/*  1497 */     while (jmqiException1 != null) {
/*  1498 */       if (jmqiException1 instanceof JmqiException) {
/*  1499 */         JmqiException thisException = jmqiException1;
/*       */         
/*  1501 */         switch (thisException.getReason()) {
/*       */           case 2059:
/*       */           case 2538:
/*       */           case 2543:
/*  1505 */             throwable = thisException.getCause();
/*       */             continue;
/*       */         } 
/*       */         
/*  1509 */         bestSoFar = thisException;
/*  1510 */         Throwable throwable = null;
/*       */         
/*       */         continue;
/*       */       } 
/*       */       
/*  1515 */       jmqiException1 = null;
/*       */     } 
/*       */ 
/*       */     
/*  1519 */     if (Trace.isOn) {
/*  1520 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "getMostInterestingException(JmqiException)", new Object[] { bestSoFar });
/*       */     }
/*       */ 
/*       */     
/*  1524 */     return bestSoFar;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQCTL(Hconn hConn, int operation, MQCTLO pControlOpts, Pint pCompCode, Pint pReason) {
/*  1543 */     if (Trace.isOn) {
/*  1544 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQCTL(Hconn,int,MQCTLO,Pint,Pint)", new Object[] { hConn, 
/*  1545 */             Integer.valueOf(operation), pControlOpts, pCompCode, pReason });
/*       */     }
/*  1547 */     RemoteHconn remoteHconn = null;
/*       */     
/*  1549 */     RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  1550 */     JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/*  1551 */     jmqiTls.lastException = null;
/*  1552 */     tls.inMQCTLorMQCB = true;
/*       */     try {
/*  1554 */       remoteHconn = getRemoteHconn(tls, hConn);
/*  1555 */       remoteHconn.doMQCTL(this.jmqiCompId, operation, pControlOpts, pCompCode, pReason);
/*       */     }
/*  1557 */     catch (JmqiException e) {
/*  1558 */       if (Trace.isOn) {
/*  1559 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQCTL(Hconn,int,MQCTLO,Pint,Pint)", (Throwable)e, 1);
/*       */       }
/*       */ 
/*       */ 
/*       */       
/*  1564 */       jmqiTls.lastException = e;
/*  1565 */       pCompCode.x = e.getCompCode();
/*  1566 */       pReason.x = e.getReason();
/*       */     } 
/*       */ 
/*       */     
/*  1570 */     if (0 != pReason.x && 
/*  1571 */       remoteHconn.isReconnectable() && 
/*  1572 */       RemoteHconn.isReconnectableReasonCode(pReason.x) && 
/*  1573 */       !remoteHconn.hasFailed()) {
/*       */ 
/*       */       
/*  1576 */       pCompCode.x = 0;
/*  1577 */       pReason.x = 0;
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  1583 */     if (remoteHconn.hasFailed()) {
/*  1584 */       pCompCode.x = remoteHconn.getReconnectionFailureCompCode();
/*  1585 */       pReason.x = remoteHconn.getReconnectionFailureReason();
/*  1586 */       jmqiTls.lastException = remoteHconn.getReconnectionFailureException();
/*       */     } 
/*       */     
/*  1589 */     tls.inMQCTLorMQCB = false;
/*       */     
/*  1591 */     if (Trace.isOn) {
/*  1592 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQCTL(Hconn,int,MQCTLO,Pint,Pint)", 2);
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQDISC(Phconn pHconn, Pint pCompCode, Pint pReason) {
/*  1609 */     if (Trace.isOn) {
/*  1610 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQDISC(Phconn,Pint,Pint)", new Object[] { pHconn, pCompCode, pReason });
/*       */     }
/*       */     
/*  1613 */     MQDISC(pHconn, pCompCode, pReason, false);
/*  1614 */     if (Trace.isOn) {
/*  1615 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQDISC(Phconn,Pint,Pint)");
/*       */     }
/*       */   }
/*       */   
/*       */   void MQDISC(Phconn pHconn, Pint pCompCode, Pint pReason, boolean calledFromReconnect) {
/*  1620 */     if (Trace.isOn) {
/*  1621 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQDISC(Phconn,Pint,Pint,boolean)", new Object[] { pHconn, pCompCode, pReason, 
/*  1622 */             Boolean.valueOf(calledFromReconnect) });
/*       */     }
/*       */ 
/*       */     
/*  1626 */     if (Trace.isOn) {
/*  1627 */       Trace.data(this, "MQDISC(Phconn,Pint,Pint,boolean)", "__________");
/*  1628 */       Trace.data(this, "MQDISC(Phconn,Pint,Pint,boolean)", "MQDISC >>");
/*  1629 */       Trace.data(this, "MQDISC(Phconn,Pint,Pint,boolean)", "Hconn", pHconn);
/*  1630 */       Trace.data(this, "MQDISC(Phconn,Pint,Pint,boolean)", "CompCode", pCompCode);
/*  1631 */       Trace.data(this, "MQDISC(Phconn,Pint,Pint,boolean)", "Reason", pReason);
/*  1632 */       Trace.data(this, "MQDISC(Phconn,Pint,Pint,boolean)", "__________");
/*       */     } 
/*  1634 */     RemoteHconn remoteHconn = null;
/*  1635 */     RemoteSession remoteSession = null;
/*  1636 */     RfpMQAPI rMQAPI = null;
/*       */     
/*  1638 */     RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  1639 */     JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/*  1640 */     jmqiTls.lastException = null;
/*  1641 */     Hconn hconn = pHconn.getHconn();
/*  1642 */     boolean wasReconnectable = false;
/*       */     
/*       */     try {
/*  1645 */       remoteHconn = getRemoteHconn(tls, hconn);
/*  1646 */       if (remoteHconn.isReconnectable()) {
/*       */ 
/*       */         
/*  1649 */         remoteHconn.eligibleForReconnect(false);
/*  1650 */         wasReconnectable = true;
/*       */       } 
/*       */       
/*  1653 */       RemoteConnection parentConn = remoteHconn.getParentConnection();
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  1659 */       if (!calledFromReconnect) {
/*       */         
/*  1661 */         remoteHconn.enterCall(tls.isDispatchThread, true);
/*       */ 
/*       */ 
/*       */         
/*  1665 */         remoteHconn.enterNotifyCall(tls.isDispatchThread, false);
/*       */       } 
/*       */       try {
/*  1668 */         remoteSession = remoteHconn.getSession();
/*  1669 */         remoteSession.checkIfDisconnected();
/*       */         
/*  1671 */         if (remoteHconn.getFapLevel() >= 4) {
/*       */           
/*  1673 */           RfpMQAPI sMQAPI = remoteSession.allocateMQAPI(130);
/*  1674 */           sMQAPI.setControlFlags1(48);
/*  1675 */           sMQAPI.setCallLength(sMQAPI.hdrSize());
/*  1676 */           sMQAPI.setTransLength(sMQAPI.hdrSize());
/*       */           
/*  1678 */           remoteSession.sendTSH(tls, (RfpTSH)sMQAPI);
/*       */           
/*  1680 */           rMQAPI = remoteSession.receiveMQIFlow(tls);
/*       */           
/*       */           try {
/*  1683 */             if (rMQAPI.getSegmentType() != 146) {
/*  1684 */               HashMap<String, Object> info = new HashMap<>();
/*  1685 */               info.put("SegmentType", Integer.valueOf(rMQAPI.getSegmentType()));
/*  1686 */               info.put("Description", "MQDISC reply expected");
/*  1687 */               Trace.ffst(this, "MQDISC(Phconn,Pint,Pint)", "01", info, null);
/*  1688 */               JmqiException traceRet3 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*       */ 
/*       */               
/*  1691 */               if (Trace.isOn) {
/*  1692 */                 Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQDISC(Phconn,Pint,Pint,boolean)", (Throwable)traceRet3);
/*       */               }
/*       */               
/*  1695 */               throw traceRet3;
/*       */             } 
/*       */             
/*  1698 */             pCompCode.x = rMQAPI.getCompCode(remoteSession.isSwap());
/*  1699 */             pReason.x = rMQAPI.getReasonCode(remoteSession.isSwap());
/*       */             
/*  1701 */             if (wasReconnectable && RemoteHconn.isReconnectableReasonCode(pReason.x))
/*       */             {
/*  1703 */               pCompCode.x = 0;
/*  1704 */               pReason.x = 0;
/*       */             }
/*       */           
/*       */           }
/*       */           finally {
/*       */             
/*  1710 */             if (Trace.isOn) {
/*  1711 */               Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQDISC(Phconn,Pint,Pint,boolean)", 1);
/*       */             }
/*       */             
/*  1714 */             if (rMQAPI != null) {
/*  1715 */               remoteSession.releaseReceivedTSH((RfpTSH)rMQAPI);
/*       */             }
/*       */           }
/*       */         
/*       */         } 
/*       */       } finally {
/*       */         
/*  1722 */         if (Trace.isOn) {
/*  1723 */           Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQDISC(Phconn,Pint,Pint,boolean)", 2);
/*       */         }
/*       */ 
/*       */         
/*  1727 */         if (pCompCode.x != 2 || pReason.x == 2009) {
/*  1728 */           parentConn.completeClose(tls, remoteSession);
/*  1729 */           if (remoteHconn != null) {
/*  1730 */             remoteHconn.setInactive();
/*       */           }
/*       */           
/*  1733 */           pHconn.setHconn(MQConstants.jmqi_MQHC_UNUSABLE_HCONN);
/*       */         } 
/*       */         
/*  1736 */         if (!calledFromReconnect) {
/*  1737 */           remoteHconn.removeSelfFromParentIfAny();
/*       */           
/*       */           try {
/*  1740 */             if (remoteHconn != null) {
/*  1741 */               remoteHconn.leaveNotifyCall(pReason.x, false);
/*       */             }
/*       */           }
/*  1744 */           catch (JmqiException e) {
/*  1745 */             if (Trace.isOn) {
/*  1746 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQDISC(Phconn,Pint,Pint,boolean)", (Throwable)e, 1);
/*       */             }
/*       */             
/*  1749 */             jmqiTls.lastException = e;
/*  1750 */             pCompCode.x = e.getCompCode();
/*  1751 */             pReason.x = e.getReason();
/*       */           } 
/*       */           
/*       */           try {
/*  1755 */             remoteHconn.leaveCall(pReason.x);
/*       */           }
/*  1757 */           catch (JmqiException e) {
/*  1758 */             if (Trace.isOn) {
/*  1759 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQDISC(Phconn,Pint,Pint,boolean)", (Throwable)e, 2);
/*       */             }
/*       */             
/*  1762 */             jmqiTls.lastException = e;
/*  1763 */             pCompCode.x = e.getCompCode();
/*  1764 */             pReason.x = e.getReason();
/*       */           }
/*       */         
/*       */         } 
/*       */       } 
/*  1769 */     } catch (JmqiException e) {
/*  1770 */       if (Trace.isOn) {
/*  1771 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQDISC(Phconn,Pint,Pint,boolean)", (Throwable)e, 3);
/*       */       }
/*       */       
/*  1774 */       jmqiTls.lastException = e;
/*  1775 */       pCompCode.x = e.getCompCode();
/*  1776 */       pReason.x = e.getReason();
/*       */     } 
/*       */ 
/*       */     
/*  1780 */     if (0 != pReason.x && 
/*  1781 */       hconn instanceof RemoteHconn && remoteHconn.isReconnectable() && 
/*  1782 */       RemoteHconn.isReconnectableReasonCode(pReason.x) && 
/*  1783 */       !remoteHconn.hasFailed()) {
/*       */ 
/*       */       
/*  1786 */       pCompCode.x = 0;
/*  1787 */       pReason.x = 0;
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  1793 */     if (remoteHconn.hasFailed()) {
/*  1794 */       pCompCode.x = remoteHconn.getReconnectionFailureCompCode();
/*  1795 */       pReason.x = remoteHconn.getReconnectionFailureReason();
/*  1796 */       jmqiTls.lastException = remoteHconn.getReconnectionFailureException();
/*       */     } 
/*       */ 
/*       */     
/*  1800 */     if (Trace.isOn) {
/*  1801 */       Trace.data(this, "MQDISC(Phconn,Pint,Pint,boolean)", "__________");
/*  1802 */       Trace.data(this, "MQDISC(Phconn,Pint,Pint,boolean)", "MQDISC <<");
/*       */       
/*  1804 */       Trace.data(this, "MQDISC(Phconn,Pint,Pint,boolean)", "Hconn", pHconn);
/*  1805 */       Trace.data(this, "MQDISC(Phconn,Pint,Pint,boolean)", "CompCode", pCompCode);
/*  1806 */       Trace.data(this, "MQDISC(Phconn,Pint,Pint,boolean)", "Reason", pReason);
/*       */       
/*  1808 */       Trace.data(this, "MQDISC(Phconn,Pint,Pint,boolean)", "__________");
/*       */     } 
/*       */     
/*  1811 */     if (Trace.isOn) {
/*  1812 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQDISC(Phconn,Pint,Pint,boolean)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQOPEN(Hconn hConn, MQOD pOD, int options, Phobj pHObj, Pint pCompCode, Pint pReason) {
/*  1835 */     if (Trace.isOn) {
/*  1836 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint)", new Object[] { hConn, pOD, 
/*  1837 */             Integer.valueOf(options), pHObj, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */     
/*  1841 */     if (Trace.isOn) {
/*  1842 */       Trace.data(this, "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint)", "__________");
/*  1843 */       Trace.data(this, "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint)", "MQOPEN >>");
/*  1844 */       Trace.data(this, "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint)", "Hconn", hConn);
/*  1845 */       Trace.data(this, "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint)", "Objdesc", pOD);
/*  1846 */       Trace.data(this, "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint)", "Options", "0x" + Integer.toHexString(options));
/*  1847 */       Trace.data(this, "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint)", "Hobj", pHObj);
/*  1848 */       Trace.data(this, "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint)", "CompCode", pCompCode);
/*  1849 */       Trace.data(this, "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint)", "Reason", pReason);
/*  1850 */       Trace.data(this, "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint)", "__________");
/*       */     } 
/*  1852 */     RemoteHconn remoteHconn = (RemoteHconn)hConn;
/*  1853 */     RemoteSession session = MQOPEN(hConn, pOD, options, pHObj, pCompCode, pReason, null);
/*  1854 */     if (0 != pReason.x && remoteHconn.isReconnectable() && 
/*  1855 */       !threadIsReconnectThread()) {
/*  1856 */       if (RemoteHconn.isReconnectableReasonCode(pReason.x)) {
/*  1857 */         if (!remoteHconn.hasFailed()) {
/*       */           try {
/*  1859 */             remoteHconn.initiateReconnection(session);
/*  1860 */             if (pOD.getObjectQMgrName() != null && pOD.getObjectQMgrName().trim().length() != 0) {
/*  1861 */               pOD.setObjectQMgrName(remoteHconn.getQmName());
/*       */             }
/*  1863 */             MQOPEN(hConn, pOD, options, pHObj, pCompCode, pReason);
/*       */           }
/*  1865 */           catch (JmqiException je) {
/*  1866 */             if (Trace.isOn) {
/*  1867 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint)", (Throwable)je);
/*       */             
/*       */             }
/*       */           
/*       */           }
/*       */         
/*       */         }
/*       */       }
/*  1875 */       else if (pReason.x == 2545) {
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  1880 */         MQOPEN(hConn, pOD, options, pHObj, pCompCode, pReason);
/*       */       } 
/*       */     }
/*       */ 
/*       */     
/*  1885 */     if (remoteHconn.hasFailed()) {
/*  1886 */       pCompCode.x = remoteHconn.getReconnectionFailureCompCode();
/*  1887 */       pReason.x = remoteHconn.getReconnectionFailureReason();
/*  1888 */       RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  1889 */       JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/*  1890 */       jmqiTls.lastException = remoteHconn.getReconnectionFailureException();
/*       */     } 
/*       */ 
/*       */     
/*  1894 */     if (Trace.isOn) {
/*  1895 */       Trace.data(this, "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint)", "__________");
/*  1896 */       Trace.data(this, "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint)", "MQOPEN <<");
/*       */       
/*  1898 */       Trace.data(this, "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint)", "Hconn", hConn);
/*  1899 */       Trace.data(this, "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint)", "Objdesc", pOD);
/*  1900 */       Trace.data(this, "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint)", "Options", "0x" + Integer.toHexString(options));
/*  1901 */       Trace.data(this, "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint)", "Hobj", pHObj);
/*  1902 */       Trace.data(this, "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint)", "CompCode", pCompCode);
/*  1903 */       Trace.data(this, "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint)", "Reason", pReason);
/*       */       
/*  1905 */       Trace.data(this, "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint)", "__________");
/*       */     } 
/*       */     
/*  1908 */     if (Trace.isOn) {
/*  1909 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */   
/*       */   private boolean threadIsReconnectThread() {
/*  1915 */     if (Trace.isOn) {
/*  1916 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "threadIsReconnectThread()");
/*       */     }
/*  1918 */     RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  1919 */     if (Trace.isOn) {
/*  1920 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "threadIsReconnectThread()", 
/*  1921 */           Boolean.valueOf(tls.isReconnectThread));
/*       */     }
/*  1923 */     return tls.isReconnectThread;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public RemoteSession MQOPEN(Hconn hConn, MQOD pOD, int options, Phobj pHObj, Pint pCompCode, Pint pReason, RemoteHobj reconnectHobjP) {
/*  1947 */     if (Trace.isOn) {
/*  1948 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint,RemoteHobj)", new Object[] { hConn, pOD, 
/*       */             
/*  1950 */             Integer.valueOf(options), pHObj, pCompCode, pReason, reconnectHobjP });
/*       */     }
/*       */     
/*  1953 */     int initialReconnectCycle = ((RemoteHconn)hConn).getApplicableReconnectCycle();
/*       */     
/*  1955 */     boolean reconnecting = (reconnectHobjP != null);
/*  1956 */     RemoteHobj remoteHobj = reconnectHobjP;
/*  1957 */     RemoteHconn remoteHconn = null;
/*  1958 */     RemoteSession remoteSession = null;
/*  1959 */     RfpMQAPI rMQAPI = null;
/*  1960 */     RemoteProxyQueue proxyQueue = null;
/*       */     
/*  1962 */     RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  1963 */     JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/*  1964 */     jmqiTls.lastException = null;
/*  1965 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*       */ 
/*       */     
/*  1968 */     pOD.getObjectString().setRemote(true);
/*  1969 */     pOD.getSelectionString().setRemote(true);
/*  1970 */     pOD.getResObjectString().setRemote(true);
/*       */     
/*       */     try {
/*  1973 */       if ((options & 0x100000) != 0 && (options & 0x80000) != 0) {
/*  1974 */         JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2046, null);
/*       */ 
/*       */         
/*  1977 */         if (Trace.isOn) {
/*  1978 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint,RemoteHobj)", (Throwable)traceRet1, 1);
/*       */         }
/*       */         
/*  1981 */         throw traceRet1;
/*       */       } 
/*       */ 
/*       */       
/*  1985 */       remoteHconn = getRemoteHconn(tls, hConn);
/*       */ 
/*       */       
/*  1988 */       pHObj.setHobj(MQConstants.jmqi_MQHO_UNUSABLE_HOBJ);
/*       */ 
/*       */ 
/*       */       
/*  1992 */       String originalObjectName = pOD.getObjectName();
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  1997 */       if (pOD.getVersion() < 2 && remoteHconn.getSession().isDistListCapable()) {
/*       */ 
/*       */ 
/*       */         
/*  2001 */         pOD.setVersion(2);
/*  2002 */         pOD.setRecsPresent(0);
/*  2003 */         pOD.setResponseRecords(null);
/*  2004 */         pOD.setKnownDestCount(0);
/*  2005 */         pOD.setUnknownDestCount(0);
/*  2006 */         pOD.setInvalidDestCount(0);
/*       */       } 
/*       */ 
/*       */       
/*  2010 */       remoteHconn.enterCall(tls.isDispatchThread, false, false);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*       */       try {
/*  2018 */         remoteSession = remoteHconn.getSession();
/*       */         
/*  2020 */         JmqiCodepage cp = remoteSession.getCp();
/*  2021 */         boolean swap = remoteSession.isSwap();
/*       */         
/*  2023 */         remoteSession.checkIfDisconnected();
/*       */         
/*  2025 */         proxyQueue = getProxyQueue(remoteSession, options, reconnecting, remoteHobj, remoteHconn, tls);
/*       */ 
/*       */         
/*  2028 */         RfpMQAPI sMQAPI = remoteSession.allocateMQAPI(131);
/*  2029 */         sMQAPI.setControlFlags1(48);
/*       */ 
/*       */         
/*  2032 */         byte[] commsBuffer = sMQAPI.getRfpBuffer();
/*  2033 */         int writePos = sMQAPI.getRfpOffset() + sMQAPI.hdrSize();
/*       */         
/*  2035 */         writePos = pOD.writeToBuffer(commsBuffer, writePos, 4, swap, cp, jmqiTls);
/*       */ 
/*       */         
/*  2038 */         dc.writeI32(options, commsBuffer, writePos, swap);
/*       */         
/*  2040 */         writePos += 4;
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  2045 */         int requestedPolicyDataMaxLength = 0;
/*       */ 
/*       */         
/*  2048 */         if (remoteHconn.getFapLevel() >= 9) {
/*  2049 */           int mqopenPrivStructVersion, mqopenPrivStructSize; RfpMQOPEN_PRIV openPrivArea = (RfpMQOPEN_PRIV)remoteSession.getRfp(9, (RfpStructure)sMQAPI, writePos);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  2058 */           if (remoteHconn.getFapLevel() >= 12 && remoteHconn.getQmgrSplCapability() == QmgrSplCapability.SUPPORTED) {
/*  2059 */             mqopenPrivStructVersion = 2;
/*  2060 */             mqopenPrivStructSize = 84;
/*       */           } else {
/*       */             
/*  2063 */             mqopenPrivStructVersion = 1;
/*  2064 */             mqopenPrivStructSize = 28;
/*       */           } 
/*       */ 
/*       */           
/*  2068 */           openPrivArea.initEyecatcher();
/*  2069 */           openPrivArea.setVersion(mqopenPrivStructVersion, swap);
/*  2070 */           openPrivArea.setLength(mqopenPrivStructSize, swap);
/*       */ 
/*       */           
/*  2073 */           openPrivArea.setDefPersistence(-1, swap);
/*  2074 */           openPrivArea.setDefPutResponseType(-1, swap);
/*       */ 
/*       */           
/*  2077 */           if (remoteHconn.getFapLevel() >= 12 && remoteHconn.getQmgrSplCapability() == QmgrSplCapability.SUPPORTED) {
/*  2078 */             openPrivArea.setPolicyErrorQueue("", cp, jmqiTls);
/*  2079 */             openPrivArea.setPolicyDataOffset(0, swap);
/*       */ 
/*       */             
/*  2082 */             int MAX_REQUESTED_POLICY_LENGTH = 10240;
/*  2083 */             openPrivArea.setPolicyDataLength(MAX_REQUESTED_POLICY_LENGTH, swap);
/*  2084 */             requestedPolicyDataMaxLength = MAX_REQUESTED_POLICY_LENGTH;
/*       */           } 
/*  2086 */           writePos += mqopenPrivStructSize;
/*       */         } 
/*       */ 
/*       */         
/*  2090 */         int transLength = writePos - sMQAPI.getRfpOffset();
/*       */         
/*  2092 */         sMQAPI.setTransLength(transLength);
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  2097 */         sMQAPI.setCallLength(transLength + pOD.getResObjectString().getVsBufSize() + pOD.getSelectionString().getVsBufSize() + requestedPolicyDataMaxLength);
/*       */ 
/*       */         
/*  2100 */         remoteSession.sendTSH(tls, (RfpTSH)sMQAPI);
/*       */ 
/*       */         
/*  2103 */         rMQAPI = remoteSession.receiveMQIFlow(tls);
/*       */ 
/*       */         
/*  2106 */         int replyLength = rMQAPI.getTransLength();
/*       */         
/*  2108 */         if (rMQAPI.getSegmentType() != 147 || replyLength < rMQAPI.hdrSize() + MQOD.getSizeV1(4)) {
/*  2109 */           HashMap<String, Object> info = new HashMap<>();
/*  2110 */           info.put("SegmentType", Integer.valueOf(rMQAPI.getSegmentType()));
/*  2111 */           info.put("Description", "MQOPEN reply expected");
/*  2112 */           info.put("Buffer", rMQAPI.getRfpBuffer());
/*  2113 */           Trace.ffst(this, "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint,RemoteHobj)", "01", info, null);
/*  2114 */           JmqiException traceRet3 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*       */ 
/*       */           
/*  2117 */           if (Trace.isOn) {
/*  2118 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint,RemoteHobj)", (Throwable)traceRet3, 2);
/*       */           }
/*       */           
/*  2121 */           throw traceRet3;
/*       */         } 
/*       */ 
/*       */ 
/*       */         
/*  2126 */         swap = remoteSession.isSwap();
/*       */ 
/*       */         
/*  2129 */         pCompCode.x = rMQAPI.getCompCode(swap);
/*  2130 */         pReason.x = rMQAPI.getReasonCode(swap);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  2136 */         if (pCompCode.x != 2) {
/*       */           
/*  2138 */           int defPersistence = -1;
/*  2139 */           int defPutResponseType = -1;
/*  2140 */           int defReadAhead = -1;
/*  2141 */           String errorQueue = null;
/*  2142 */           int policyOffset = -1;
/*  2143 */           int policyLength = -1;
/*  2144 */           int policyReadPos = -1;
/*  2145 */           int readPos = rMQAPI.getRfpOffset() + rMQAPI.hdrSize();
/*  2146 */           Pint optionPos = this.env.newPint(0);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  2152 */           readPos = pOD.readFromBuffer(rMQAPI.getRfpBuffer(), readPos, 4, swap, cp, jmqiTls, optionPos);
/*       */ 
/*       */           
/*  2155 */           if (remoteHconn.getFapLevel() >= 4) {
/*  2156 */             int openPrivPos = (optionPos.x > 0) ? optionPos.x : readPos;
/*       */             
/*  2158 */             openPrivPos += 4;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */             
/*  2165 */             if (remoteHconn.getFapLevel() >= 9) {
/*       */               
/*  2167 */               RfpMQOPEN_PRIV openPrivArea = (RfpMQOPEN_PRIV)remoteSession.getRfp(9, (RfpStructure)rMQAPI, openPrivPos);
/*       */               
/*  2169 */               if (openPrivArea.getVersion(swap) >= 2 && 
/*  2170 */                 remoteHconn.getFapLevel() >= 12 && remoteHconn.getQmgrSplCapability() == QmgrSplCapability.SUPPORTED) {
/*  2171 */                 errorQueue = openPrivArea.getPolicyErrorQueue(cp, jmqiTls);
/*  2172 */                 policyOffset = openPrivArea.getPolicyDataOffset(swap);
/*  2173 */                 policyLength = openPrivArea.getPolicyDataLength(swap);
/*  2174 */                 policyReadPos = openPrivPos;
/*       */               } 
/*       */ 
/*       */               
/*  2178 */               if (openPrivArea.getVersion(swap) >= 1) {
/*       */                 
/*  2180 */                 defPersistence = openPrivArea.getDefPersistence(swap);
/*  2181 */                 defPutResponseType = openPrivArea.getDefPutResponseType(swap);
/*  2182 */                 defReadAhead = openPrivArea.getDefReadAhead(swap);
/*       */               } 
/*       */               
/*  2185 */               if (optionPos.x == 0)
/*       */               {
/*       */                 
/*  2188 */                 readPos += openPrivArea.getLength(swap);
/*       */               }
/*       */             } 
/*       */           } 
/*       */ 
/*       */           
/*  2194 */           int hobjHandle = rMQAPI.getHandle(swap);
/*  2195 */           String objectName = pOD.getObjectName();
/*  2196 */           int objectType = pOD.getObjectType();
/*  2197 */           if (reconnecting) {
/*  2198 */             remoteHobj.updateHandle(hobjHandle, remoteSession);
/*       */           
/*       */           }
/*       */           else {
/*       */             
/*  2203 */             remoteHobj = new RemoteHobj(this.env, hobjHandle, proxyQueue, objectName, objectType, options, defPersistence, defPutResponseType, defReadAhead, pOD, remoteSession);
/*       */           } 
/*       */ 
/*       */           
/*  2207 */           if (((RemoteHconn)hConn).getApplicableReconnectCycle() != initialReconnectCycle) {
/*       */ 
/*       */ 
/*       */ 
/*       */             
/*  2212 */             JmqiException traceRet = new JmqiException(this.env, -1, null, 2, 2545, null);
/*       */ 
/*       */             
/*  2215 */             if (Trace.isOn) {
/*  2216 */               Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint,RemoteHobj)", (Throwable)traceRet, 3);
/*       */             }
/*       */             
/*  2219 */             throw traceRet;
/*       */           } 
/*       */           
/*  2222 */           pHObj.setHobj(remoteHobj);
/*  2223 */           remoteHconn.addHobj(remoteHobj);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  2231 */           if (remoteHconn.isReconnectable() && !reconnecting) {
/*  2232 */             remoteHobj.setOriginalObjectName(originalObjectName);
/*       */           }
/*       */ 
/*       */ 
/*       */           
/*  2237 */           if (proxyQueue != null) {
/*  2238 */             remoteHconn.getProxyQueueManager().setIdentifier(tls, proxyQueue, remoteHobj);
/*  2239 */             proxyQueue.setHconn(remoteHconn);
/*       */           } 
/*       */ 
/*       */ 
/*       */           
/*  2244 */           if (remoteHconn.getFapLevel() >= 12 && remoteHconn.getQmgrSplCapability() == QmgrSplCapability.SUPPORTED && 
/*  2245 */             policyLength > 0 && errorQueue != null && policyReadPos > -1 && rMQAPI.getRfpOffset() + policyOffset + policyLength <= (rMQAPI
/*  2246 */             .getRfpBuffer()).length) {
/*  2247 */             byte[] policyBytes = new byte[policyLength];
/*       */             try {
/*  2249 */               System.arraycopy(rMQAPI.getRfpBuffer(), policyReadPos + policyOffset, policyBytes, 0, policyLength);
/*       */             
/*       */             }
/*  2252 */             catch (ArrayIndexOutOfBoundsException e) {
/*  2253 */               if (Trace.isOn) {
/*  2254 */                 Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint,RemoteHobj)", e, 1);
/*       */               }
/*       */               
/*  2257 */               HashMap<String, Object> info = new HashMap<>();
/*  2258 */               info.put("policyReadPos", Integer.valueOf(policyReadPos));
/*  2259 */               info.put("policyOffset", Integer.valueOf(policyOffset));
/*  2260 */               info.put("policyLength", Integer.valueOf(policyLength));
/*  2261 */               info.put("Description", "MQOPEN - ArrayIndexOutOfBoundsException");
/*  2262 */               info.put("rMQAPI.getRfpOffset()", Integer.valueOf(rMQAPI.getRfpOffset()));
/*  2263 */               info.put("rMQAPI.getRfpBuffer().length", Integer.valueOf((rMQAPI.getRfpBuffer()).length));
/*  2264 */               StringBuffer bufferDump = new StringBuffer();
/*  2265 */               JmqiTools.hexDump(rMQAPI.getRfpBuffer(), null, rMQAPI.getRfpOffset(), (rMQAPI
/*  2266 */                   .getRfpBuffer()).length - rMQAPI.getRfpOffset(), bufferDump);
/*  2267 */               info.put("buffer contents", bufferDump);
/*  2268 */               Trace.ffst(this, "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint,RemoteHobj)", "99", info, null);
/*  2269 */               if (Trace.isOn) {
/*  2270 */                 Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint,RemoteHobj)", e, 3);
/*       */               }
/*       */               
/*  2273 */               throw e;
/*       */             } 
/*  2275 */             remoteHobj.setAMSPolicy(policyBytes);
/*  2276 */             remoteHobj.setAMSErrorQueue(errorQueue);
/*       */           }
/*       */         
/*       */         } 
/*       */       } finally {
/*       */         
/*  2282 */         if (Trace.isOn) {
/*  2283 */           Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint,RemoteHobj)");
/*       */         }
/*       */         
/*  2286 */         pOD.getObjectString().setRemote(false);
/*  2287 */         pOD.getSelectionString().setRemote(false);
/*  2288 */         pOD.getResObjectString().setRemote(false);
/*       */ 
/*       */         
/*       */         try {
/*  2292 */           if (remoteHconn != null) {
/*  2293 */             if (rMQAPI != null) {
/*       */               try {
/*  2295 */                 remoteSession.releaseReceivedTSH((RfpTSH)rMQAPI);
/*       */               }
/*  2297 */               catch (JmqiException e) {
/*  2298 */                 if (Trace.isOn) {
/*  2299 */                   Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint,RemoteHobj)", (Throwable)e, 2);
/*       */                 }
/*       */                 
/*  2302 */                 pCompCode.x = e.getCompCode();
/*  2303 */                 pReason.x = e.getReason();
/*       */               } 
/*       */             }
/*       */             
/*  2307 */             remoteHconn.leaveCall(pReason.x, false);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */             
/*  2315 */             if (pCompCode.x == 2 && proxyQueue != null && !reconnecting) {
/*  2316 */               remoteHconn.getProxyQueueManager().deleteProxyQueue(tls, proxyQueue);
/*  2317 */               if (remoteHobj != null) {
/*  2318 */                 remoteHobj.setProxyQueue(null);
/*       */               }
/*       */             }
/*       */           
/*       */           } 
/*  2323 */         } catch (JmqiException e) {
/*  2324 */           if (Trace.isOn) {
/*  2325 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint,RemoteHobj)", (Throwable)e, 3);
/*       */           }
/*       */         }
/*       */       
/*       */       }
/*       */     
/*  2331 */     } catch (JmqiException e) {
/*  2332 */       if (Trace.isOn) {
/*  2333 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint,RemoteHobj)", (Throwable)e, 4);
/*       */       }
/*       */ 
/*       */ 
/*       */       
/*  2338 */       jmqiTls.lastException = e;
/*  2339 */       pCompCode.x = e.getCompCode();
/*  2340 */       pReason.x = e.getReason();
/*       */     } 
/*       */     
/*  2343 */     if (Trace.isOn) {
/*  2344 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint,RemoteHobj)", remoteSession);
/*       */     }
/*       */     
/*  2347 */     return remoteSession;
/*       */   }
/*       */ 
/*       */   
/*       */   private RemoteProxyQueue getProxyQueue(RemoteSession remoteSession, int options, boolean reconnecting, RemoteHobj remoteHobj, RemoteHconn remoteHconn, RemoteTls tls) throws JmqiException {
/*  2352 */     RemoteProxyQueue proxyQueue = null;
/*       */ 
/*       */     
/*  2355 */     if (remoteSession.isMultiplexingEnabled() && ((options & 0x1) != 0 || (options & 0x2) != 0 || (options & 0x4) != 0 || (options & 0x8) != 0))
/*       */     {
/*       */       
/*  2358 */       if (reconnecting) {
/*  2359 */         proxyQueue = remoteHobj.getProxyQueue();
/*  2360 */         proxyQueue.resetForReconnect(tls);
/*       */       }
/*       */       else {
/*       */         
/*  2364 */         proxyQueue = remoteHconn.getProxyQueueManager().createProxyQueue(tls);
/*       */       } 
/*       */     }
/*  2367 */     return proxyQueue;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQSUB(Hconn hconn, MQSD pSubDesc, Phobj pHobj, Phobj pHsub, Pint pCompCode, Pint pReason) {
/*  2388 */     if (Trace.isOn) {
/*  2389 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQSUB(Hconn,MQSD,Phobj,Phobj,Pint,Pint)", new Object[] { hconn, pSubDesc, pHobj, pHsub, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */ 
/*       */     
/*  2394 */     if (Trace.isOn) {
/*  2395 */       Trace.data(this, "MQSUB(Hconn,MQSD,Phobj,Phobj,Pint,Pint)", "__________");
/*  2396 */       Trace.data(this, "MQSUB(Hconn,MQSD,Phobj,Phobj,Pint,Pint)", "MQSUB >>");
/*  2397 */       Trace.data(this, "MQSUB(Hconn,MQSD,Phobj,Phobj,Pint,Pint)", "Hconn", hconn);
/*  2398 */       Trace.data(this, "MQSUB(Hconn,MQSD,Phobj,Phobj,Pint,Pint)", "Subdesc", pSubDesc);
/*  2399 */       Trace.data(this, "MQSUB(Hconn,MQSD,Phobj,Phobj,Pint,Pint)", "Hobj", pHobj);
/*  2400 */       Trace.data(this, "MQSUB(Hconn,MQSD,Phobj,Phobj,Pint,Pint)", "Hsub", pHsub);
/*  2401 */       Trace.data(this, "MQSUB(Hconn,MQSD,Phobj,Phobj,Pint,Pint)", "CompCode", pCompCode);
/*  2402 */       Trace.data(this, "MQSUB(Hconn,MQSD,Phobj,Phobj,Pint,Pint)", "Reason", pReason);
/*  2403 */       Trace.data(this, "MQSUB(Hconn,MQSD,Phobj,Phobj,Pint,Pint)", "__________");
/*       */     } 
/*       */ 
/*       */     
/*  2407 */     RemoteSession session = jmqiSubscribe(hconn, pSubDesc, pHobj, pHsub, pCompCode, pReason, null, false, null);
/*  2408 */     RemoteHconn remoteHconn = (RemoteHconn)hconn;
/*  2409 */     if (0 != pReason.x && remoteHconn.isReconnectable() && 
/*  2410 */       !threadIsReconnectThread() && 
/*  2411 */       RemoteHconn.isReconnectableReasonCode(pReason.x) && 
/*  2412 */       !remoteHconn.hasFailed()) {
/*       */       try {
/*  2414 */         remoteHconn.initiateReconnection(session);
/*  2415 */         MQSUB(hconn, pSubDesc, pHobj, pHsub, pCompCode, pReason);
/*       */       }
/*  2417 */       catch (JmqiException je) {
/*  2418 */         if (Trace.isOn) {
/*  2419 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQSUB(Hconn,MQSD,Phobj,Phobj,Pint,Pint)", (Throwable)je);
/*       */         }
/*       */       } 
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  2430 */     if (remoteHconn.hasFailed()) {
/*  2431 */       pCompCode.x = remoteHconn.getReconnectionFailureCompCode();
/*  2432 */       pReason.x = remoteHconn.getReconnectionFailureReason();
/*  2433 */       RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  2434 */       JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/*  2435 */       jmqiTls.lastException = remoteHconn.getReconnectionFailureException();
/*       */     } 
/*       */ 
/*       */     
/*  2439 */     if (Trace.isOn) {
/*  2440 */       Trace.data(this, "MQSUB(Hconn,MQSD,Phobj,Phobj,Pint,Pint)", "__________");
/*  2441 */       Trace.data(this, "MQSUB(Hconn,MQSD,Phobj,Phobj,Pint,Pint)", "MQSUB <<");
/*  2442 */       Trace.data(this, "MQSUB(Hconn,MQSD,Phobj,Phobj,Pint,Pint)", "Hconn", hconn);
/*  2443 */       Trace.data(this, "MQSUB(Hconn,MQSD,Phobj,Phobj,Pint,Pint)", "Subdesc", pSubDesc);
/*  2444 */       Trace.data(this, "MQSUB(Hconn,MQSD,Phobj,Phobj,Pint,Pint)", "Hobj", pHobj);
/*  2445 */       Trace.data(this, "MQSUB(Hconn,MQSD,Phobj,Phobj,Pint,Pint)", "Hsub", pHsub);
/*  2446 */       Trace.data(this, "MQSUB(Hconn,MQSD,Phobj,Phobj,Pint,Pint)", "CompCode", pCompCode);
/*  2447 */       Trace.data(this, "MQSUB(Hconn,MQSD,Phobj,Phobj,Pint,Pint)", "Reason", pReason);
/*  2448 */       Trace.data(this, "MQSUB(Hconn,MQSD,Phobj,Phobj,Pint,Pint)", "__________");
/*       */     } 
/*       */     
/*  2451 */     if (Trace.isOn) {
/*  2452 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQSUB(Hconn,MQSD,Phobj,Phobj,Pint,Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   @Deprecated
/*       */   public void MQSUBRQ(Hconn hconn, Phobj pHsub, int action, MQSRO subRqOpts, Pint pCompCode, Pint pReason) {
/*  2479 */     if (Trace.isOn) {
/*  2480 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQSUBRQ(Hconn,Phobj,final int,MQSRO,final Pint,final Pint)", new Object[] { hconn, pHsub, 
/*       */             
/*  2482 */             Integer.valueOf(action), subRqOpts, pCompCode, pReason });
/*       */     }
/*  2484 */     Hobj hsub = pHsub.getHobj();
/*  2485 */     MQSUBRQ(hconn, hsub, action, subRqOpts, pCompCode, pReason);
/*       */     
/*  2487 */     if (Trace.isOn) {
/*  2488 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQSUBRQ(Hconn,Phobj,final int,MQSRO,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQSUBRQ(Hconn hconn, Hobj hsub, int action, MQSRO subRqOpts, Pint pCompCode, Pint pReason) {
/*  2512 */     if (Trace.isOn) {
/*  2513 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQSUBRQ(Hconn,Hobj,int,MQSRO,final Pint,final Pint)", new Object[] { hconn, hsub, 
/*       */             
/*  2515 */             Integer.valueOf(action), subRqOpts, pCompCode, pReason });
/*       */     }
/*       */     
/*  2518 */     RemoteHconn remoteHconn = null;
/*  2519 */     RemoteSession remoteSession = null;
/*  2520 */     RfpMQAPI rMQAPI = null;
/*       */     
/*  2522 */     RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  2523 */     JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/*  2524 */     jmqiTls.lastException = null;
/*  2525 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*       */     try {
/*  2527 */       remoteHconn = getRemoteHconn(tls, hconn);
/*       */       
/*  2529 */       if (hsub == null) {
/*  2530 */         JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2019, null);
/*       */ 
/*       */         
/*  2533 */         if (Trace.isOn) {
/*  2534 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQSUBRQ(Hconn,Hobj,int,MQSRO,final Pint,final Pint)", (Throwable)traceRet1, 1);
/*       */         }
/*       */         
/*  2537 */         throw traceRet1;
/*       */       } 
/*       */ 
/*       */       
/*  2541 */       if (subRqOpts == null) {
/*  2542 */         JmqiException traceRet2 = new JmqiException(this.env, -1, null, 2, 2438, null);
/*       */         
/*  2544 */         if (Trace.isOn) {
/*  2545 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQSUBRQ(Hconn,Hobj,int,MQSRO,final Pint,final Pint)", (Throwable)traceRet2, 2);
/*       */         }
/*       */         
/*  2548 */         throw traceRet2;
/*       */       } 
/*       */ 
/*       */       
/*  2552 */       remoteHconn.enterCall(tls.isDispatchThread, false);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*       */       try {
/*  2560 */         remoteSession = remoteHconn.getSession();
/*       */         
/*  2562 */         remoteSession.checkIfDisconnected();
/*       */         
/*  2564 */         JmqiCodepage cp = remoteSession.getCp();
/*  2565 */         boolean swap = remoteSession.isSwap();
/*       */ 
/*       */         
/*  2568 */         if (remoteHconn.getFapLevel() < 9) {
/*  2569 */           JmqiException traceRet3 = new JmqiException(this.env, -1, null, 2, 2298, null);
/*       */           
/*  2571 */           if (Trace.isOn) {
/*  2572 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQSUBRQ(Hconn,Hobj,int,MQSRO,final Pint,final Pint)", (Throwable)traceRet3, 3);
/*       */           }
/*       */           
/*  2575 */           throw traceRet3;
/*       */         } 
/*       */ 
/*       */         
/*  2579 */         RfpMQAPI sMQAPI = remoteSession.allocateMQAPI(143);
/*  2580 */         sMQAPI.setHandle(hsub.getIntegerHandle(), swap);
/*       */         
/*  2582 */         int writePos = sMQAPI.getRfpOffset() + sMQAPI.hdrSize();
/*  2583 */         byte[] commsBuffer = sMQAPI.getRfpBuffer();
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  2597 */         dc.writeI32(action, commsBuffer, writePos, swap);
/*  2598 */         writePos += 4;
/*       */         
/*  2600 */         writePos = subRqOpts.writeToBuffer(commsBuffer, writePos, 4, swap, cp, jmqiTls);
/*       */         
/*  2602 */         int requestLength = writePos - sMQAPI.getRfpOffset();
/*       */         
/*  2604 */         sMQAPI.setTransLength(requestLength);
/*  2605 */         sMQAPI.setCallLength(requestLength);
/*  2606 */         sMQAPI.setControlFlags1(48);
/*       */ 
/*       */         
/*  2609 */         remoteSession.sendTSH(tls, (RfpTSH)sMQAPI);
/*       */ 
/*       */         
/*  2612 */         rMQAPI = remoteSession.receiveMQIFlow(tls);
/*       */         
/*  2614 */         if (rMQAPI.getSegmentType() != 159 || (rMQAPI.getControlFlags1() & 0x20) == 0) {
/*  2615 */           JmqiException traceRet4 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*       */           
/*  2617 */           if (Trace.isOn) {
/*  2618 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQSUBRQ(Hconn,Hobj,int,MQSRO,final Pint,final Pint)", (Throwable)traceRet4, 4);
/*       */           }
/*       */           
/*  2621 */           throw traceRet4;
/*       */         } 
/*       */ 
/*       */ 
/*       */         
/*  2626 */         swap = remoteSession.isSwap();
/*       */ 
/*       */         
/*  2629 */         pCompCode.x = rMQAPI.getCompCode(swap);
/*  2630 */         pReason.x = rMQAPI.getReasonCode(swap);
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  2635 */         if (pCompCode.x != 2) {
/*       */           
/*  2637 */           int readPos = rMQAPI.getRfpOffset() + rMQAPI.hdrSize();
/*  2638 */           commsBuffer = rMQAPI.getRfpBuffer();
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  2653 */           readPos += 4;
/*       */ 
/*       */           
/*  2656 */           readPos = subRqOpts.readFromBuffer(commsBuffer, readPos, 4, swap, cp, jmqiTls);
/*       */         } 
/*       */       } finally {
/*       */         
/*  2660 */         if (Trace.isOn) {
/*  2661 */           Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQSUBRQ(Hconn,Hobj,int,MQSRO,final Pint,final Pint)");
/*       */         }
/*       */ 
/*       */ 
/*       */         
/*       */         try {
/*  2667 */           if (remoteHconn != null) {
/*  2668 */             if (rMQAPI != null) {
/*       */               try {
/*  2670 */                 remoteSession.releaseReceivedTSH((RfpTSH)rMQAPI);
/*       */               }
/*  2672 */               catch (JmqiException e) {
/*  2673 */                 if (Trace.isOn) {
/*  2674 */                   Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQSUBRQ(Hconn,Hobj,int,MQSRO,final Pint,final Pint)", (Throwable)e, 1);
/*       */                 }
/*       */               } 
/*       */             }
/*       */ 
/*       */             
/*  2680 */             remoteHconn.leaveCall(pReason.x);
/*       */           }
/*       */         
/*  2683 */         } catch (JmqiException e) {
/*  2684 */           if (Trace.isOn) {
/*  2685 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQSUBRQ(Hconn,Hobj,int,MQSRO,final Pint,final Pint)", (Throwable)e, 2);
/*       */           }
/*       */         }
/*       */       
/*       */       }
/*       */     
/*       */     }
/*  2692 */     catch (JmqiException e) {
/*  2693 */       if (Trace.isOn) {
/*  2694 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQSUBRQ(Hconn,Hobj,int,MQSRO,final Pint,final Pint)", (Throwable)e, 3);
/*       */       }
/*       */ 
/*       */       
/*  2698 */       jmqiTls.lastException = e;
/*  2699 */       pCompCode.x = e.getCompCode();
/*  2700 */       pReason.x = e.getReason();
/*       */     } 
/*       */     
/*  2703 */     if (Trace.isOn) {
/*  2704 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQSUBRQ(Hconn,Hobj,int,MQSRO,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQCRTMH(Hconn hConn, MQCMHO pCrtMsgHOpts, Phmsg pHmsg, Pint pCompCode, Pint pReason) {
/*  2726 */     if (Trace.isOn) {
/*  2727 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQCRTMH(Hconn,MQCMHO,Phmsg,final Pint,final Pint)", new Object[] { hConn, pCrtMsgHOpts, pHmsg, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */ 
/*       */     
/*  2732 */     pCompCode.x = 2;
/*  2733 */     pReason.x = 2298;
/*       */     
/*  2735 */     if (Trace.isOn) {
/*  2736 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQCRTMH(Hconn,MQCMHO,Phmsg,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQDLTMH(Hconn hConn, Phmsg pHmsg, MQDMHO pDltMsgHOpts, Pint pCompCode, Pint pReason) {
/*  2759 */     if (Trace.isOn) {
/*  2760 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQDLTMH(Hconn,Phmsg,MQDMHO,final Pint,final Pint)", new Object[] { hConn, pHmsg, pDltMsgHOpts, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */ 
/*       */     
/*  2765 */     pCompCode.x = 2;
/*  2766 */     pReason.x = 2298;
/*       */     
/*  2768 */     if (Trace.isOn) {
/*  2769 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQDLTMH(Hconn,Phmsg,MQDMHO,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQSETMP(Hconn hConn, Hmsg hmsg, MQSMPO pSetPropOpts, MQCHARV pName, MQPD pPropDesc, int Type, int ValueLength, ByteBuffer pValue, Pint pCompCode, Pint pReason) {
/*  2807 */     if (Trace.isOn) {
/*  2808 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQSETMP(Hconn,Hmsg,MQSMPO,MQCHARV,MQPD,int,int,ByteBuffer,final Pint,final Pint)", new Object[] { hConn, hmsg, pSetPropOpts, pName, pPropDesc, 
/*       */             
/*  2810 */             Integer.valueOf(Type), 
/*  2811 */             Integer.valueOf(ValueLength), pValue, pCompCode, pReason });
/*       */     }
/*       */     
/*  2814 */     pCompCode.x = 2;
/*  2815 */     pReason.x = 2298;
/*       */     
/*  2817 */     if (Trace.isOn) {
/*  2818 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQSETMP(Hconn,Hmsg,MQSMPO,MQCHARV,MQPD,int,int,ByteBuffer,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQINQMP(Hconn hConn, Hmsg hmsg, MQIMPO pInqPropOpts, MQCHARV pName, MQPD pPropDesc, Pint pType, int ValueLength, ByteBuffer pValue, Pint pDataLength, Pint pCompCode, Pint pReason) {
/*  2854 */     if (Trace.isOn) {
/*  2855 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQINQMP(Hconn,Hmsg,MQIMPO,MQCHARV,MQPD,Pint,int,ByteBuffer,Pint,final Pint,final Pint)", new Object[] { hConn, hmsg, pInqPropOpts, pName, pPropDesc, pType, 
/*       */             
/*  2857 */             Integer.valueOf(ValueLength), pValue, pDataLength, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  2868 */     pCompCode.x = 2;
/*  2869 */     pReason.x = 2298;
/*       */     
/*  2871 */     if (Trace.isOn) {
/*  2872 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQINQMP(Hconn,Hmsg,MQIMPO,MQCHARV,MQPD,Pint,int,ByteBuffer,Pint,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQDLTMP(Hconn hConn, Hmsg hmsg, MQDMPO pDltPropOpts, MQCHARV pName, Pint pCompCode, Pint pReason) {
/*  2897 */     if (Trace.isOn) {
/*  2898 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQDLTMP(Hconn,Hmsg,MQDMPO,MQCHARV,final Pint,final Pint)", new Object[] { hConn, hmsg, pDltPropOpts, pName, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */ 
/*       */     
/*  2903 */     pCompCode.x = 2;
/*  2904 */     pReason.x = 2298;
/*       */     
/*  2906 */     if (Trace.isOn) {
/*  2907 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQDLTMP(Hconn,Hmsg,MQDMPO,MQCHARV,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQMHBUF(Hconn hConn, Hmsg hmsg, MQMHBO pMsgHBufOpts, MQCHARV pName, MQMD pMsgDesc, int BufferLength, ByteBuffer pBuffer, Pint pDataLength, Pint pCompCode, Pint pReason) {
/*  2946 */     if (Trace.isOn) {
/*  2947 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQMHBUF(Hconn,Hmsg,MQMHBO,MQCHARV,MQMD,int,ByteBuffer,Pint,final Pint,final Pint)", new Object[] { hConn, hmsg, pMsgHBufOpts, pName, pMsgDesc, 
/*       */             
/*  2949 */             Integer.valueOf(BufferLength), pBuffer, pDataLength, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */     
/*  2953 */     pCompCode.x = 2;
/*  2954 */     pReason.x = 2298;
/*       */     
/*  2956 */     if (Trace.isOn) {
/*  2957 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQMHBUF(Hconn,Hmsg,MQMHBO,MQCHARV,MQMD,int,ByteBuffer,Pint,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQBUFMH(Hconn hConn, Hmsg hmsg, MQBMHO pBufMsgHOpts, MQMD pMsgDesc, int BufferLength, ByteBuffer pBuffer, Pint pDataLength, Pint pCompCode, Pint pReason) {
/*  2993 */     if (Trace.isOn) {
/*  2994 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQBUFMH(Hconn,Hmsg,MQBMHO,MQMD,int,ByteBuffer,Pint,final Pint,final Pint)", new Object[] { hConn, hmsg, pBufMsgHOpts, pMsgDesc, 
/*       */             
/*  2996 */             Integer.valueOf(BufferLength), pBuffer, pDataLength, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */     
/*  3000 */     pCompCode.x = 2;
/*  3001 */     pReason.x = 2298;
/*       */     
/*  3003 */     if (Trace.isOn) {
/*  3004 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQBUFMH(Hconn,Hmsg,MQBMHO,MQMD,int,ByteBuffer,Pint,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void spiSubscribe(Hconn hconn, LpiSD plpiSD, MQSD pSubDesc, Phobj pHobj, Phobj pHsub, Pint pCompCode, Pint pReason) {
/*  3031 */     if (Trace.isOn) {
/*  3032 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,Pint,Pint)", new Object[] { hconn, plpiSD, pSubDesc, pHobj, pHsub, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  3038 */     if (Trace.isOn) {
/*  3039 */       Trace.data(this, "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,Pint,Pint)", "__________");
/*  3040 */       Trace.data(this, "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,Pint,Pint)", "MQSUB >>");
/*  3041 */       Trace.data(this, "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,Pint,Pint)", "Hconn", hconn);
/*  3042 */       Trace.data(this, "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,Pint,Pint)", "SpiSD", plpiSD);
/*  3043 */       Trace.data(this, "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,Pint,Pint)", "Subdesc", pSubDesc);
/*  3044 */       Trace.data(this, "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,Pint,Pint)", "Hobj", pHobj);
/*  3045 */       Trace.data(this, "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,Pint,Pint)", "Hsub", pHsub);
/*  3046 */       Trace.data(this, "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,Pint,Pint)", "CompCode", pCompCode);
/*  3047 */       Trace.data(this, "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,Pint,Pint)", "Reason", pReason);
/*  3048 */       Trace.data(this, "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,Pint,Pint)", "__________");
/*       */     } 
/*       */     
/*  3051 */     RemoteSession session = jmqiSubscribe(hconn, pSubDesc, pHobj, pHsub, pCompCode, pReason, plpiSD, true, null);
/*  3052 */     RemoteHconn remoteHconn = (RemoteHconn)hconn;
/*  3053 */     if (0 != pReason.x && remoteHconn.isReconnectable() && 
/*  3054 */       !threadIsReconnectThread() && 
/*  3055 */       RemoteHconn.isReconnectableReasonCode(pReason.x) && 
/*  3056 */       !remoteHconn.hasFailed()) {
/*       */       try {
/*  3058 */         remoteHconn.initiateReconnection(session);
/*  3059 */         spiSubscribe(hconn, plpiSD, pSubDesc, pHobj, pHsub, pCompCode, pReason);
/*       */       }
/*  3061 */       catch (JmqiException je) {
/*  3062 */         if (Trace.isOn) {
/*  3063 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,Pint,Pint)", (Throwable)je);
/*       */         }
/*       */       } 
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  3074 */     if (remoteHconn.hasFailed()) {
/*  3075 */       pCompCode.x = remoteHconn.getReconnectionFailureCompCode();
/*  3076 */       pReason.x = remoteHconn.getReconnectionFailureReason();
/*  3077 */       RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  3078 */       JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/*  3079 */       jmqiTls.lastException = remoteHconn.getReconnectionFailureException();
/*       */     } 
/*       */ 
/*       */     
/*  3083 */     if (Trace.isOn) {
/*  3084 */       Trace.data(this, "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,Pint,Pint)", "__________");
/*  3085 */       Trace.data(this, "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,Pint,Pint)", "MQSUB <<");
/*  3086 */       Trace.data(this, "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,Pint,Pint)", "Hconn", hconn);
/*  3087 */       Trace.data(this, "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,Pint,Pint)", "SpiSD", plpiSD);
/*  3088 */       Trace.data(this, "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,Pint,Pint)", "Subdesc", pSubDesc);
/*  3089 */       Trace.data(this, "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,Pint,Pint)", "Hobj", pHobj);
/*  3090 */       Trace.data(this, "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,Pint,Pint)", "Hsub", pHsub);
/*  3091 */       Trace.data(this, "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,Pint,Pint)", "CompCode", pCompCode);
/*  3092 */       Trace.data(this, "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,Pint,Pint)", "Reason", pReason);
/*  3093 */       Trace.data(this, "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,Pint,Pint)", "__________");
/*       */     } 
/*       */     
/*  3096 */     if (Trace.isOn) {
/*  3097 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,Pint,Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void jmqiSubscribe(Hconn hconn, MQSD pSubDesc, Phobj pHobj, Phobj pHsub, Pint pCompCode, Pint pReason, LpiSD spiSD, boolean spiCall) {
/*  3125 */     if (Trace.isOn) {
/*  3126 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiSubscribe(Hconn,MQSD,Phobj,Phobj,Pint,Pint,LpiSD,boolean)", new Object[] { hconn, pSubDesc, pHobj, pHsub, pCompCode, pReason, spiSD, 
/*       */             
/*  3128 */             Boolean.valueOf(spiCall) });
/*       */     }
/*       */     
/*  3131 */     RemoteSession session = jmqiSubscribe(hconn, pSubDesc, pHobj, pHsub, pCompCode, pReason, spiSD, spiCall, null);
/*  3132 */     RemoteHconn remoteHconn = (RemoteHconn)hconn;
/*  3133 */     if (0 != pReason.x && remoteHconn.isReconnectable() && 
/*  3134 */       !threadIsReconnectThread() && 
/*  3135 */       RemoteHconn.isReconnectableReasonCode(pReason.x) && 
/*  3136 */       !remoteHconn.hasFailed()) {
/*       */       try {
/*  3138 */         remoteHconn.initiateReconnection(session);
/*  3139 */         jmqiSubscribe(hconn, pSubDesc, pHobj, pHsub, pCompCode, pReason, spiSD, spiCall);
/*       */       }
/*  3141 */       catch (JmqiException je) {
/*  3142 */         if (Trace.isOn) {
/*  3143 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiSubscribe(Hconn,MQSD,Phobj,Phobj,Pint,Pint,LpiSD,boolean)", (Throwable)je);
/*       */         }
/*       */       } 
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  3154 */     if (remoteHconn.hasFailed()) {
/*  3155 */       pCompCode.x = remoteHconn.getReconnectionFailureCompCode();
/*  3156 */       pReason.x = remoteHconn.getReconnectionFailureReason();
/*  3157 */       RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  3158 */       JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/*  3159 */       jmqiTls.lastException = remoteHconn.getReconnectionFailureException();
/*       */     } 
/*       */     
/*  3162 */     if (Trace.isOn) {
/*  3163 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiSubscribe(Hconn,MQSD,Phobj,Phobj,Pint,Pint,LpiSD,boolean)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public RemoteSession jmqiSubscribe(Hconn hconn, MQSD pSubDesc, Phobj pHobj, Phobj pHsub, Pint pCompCode, Pint pReason, LpiSD spiSD, boolean spiCall, RemoteHsub reconnectHsubP) {
/*  3195 */     if (Trace.isOn) {
/*  3196 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiSubscribe(Hconn,MQSD,Phobj,Phobj,Pint,Pint,LpiSD,boolean,RemoteHsub)", new Object[] { hconn, pSubDesc, pHobj, pHsub, pCompCode, pReason, spiSD, 
/*       */             
/*  3198 */             Boolean.valueOf(spiCall), reconnectHsubP });
/*       */     }
/*       */ 
/*       */     
/*  3202 */     boolean reconnecting = (reconnectHsubP != null);
/*  3203 */     RemoteHsub remoteHsub = reconnectHsubP;
/*  3204 */     RemoteHobj remoteHobj = reconnecting ? remoteHsub.getHobj() : null;
/*  3205 */     RemoteHconn remoteHconn = null;
/*  3206 */     RemoteSession remoteSession = null;
/*  3207 */     RfpMQAPI rMQAPI = null;
/*  3208 */     RemoteProxyQueue proxyQueue = null;
/*  3209 */     RfpSUBSCRIBESPIOUT spiOut = null;
/*  3210 */     int spiOutRelativeOffset = 0;
/*       */     
/*  3212 */     RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  3213 */     JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/*  3214 */     jmqiTls.lastException = null;
/*  3215 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*       */ 
/*       */     
/*  3218 */     if (pSubDesc != null) {
/*  3219 */       pSubDesc.getObjectString().setRemote(true);
/*  3220 */       pSubDesc.getSelectionString().setRemote(true);
/*  3221 */       pSubDesc.getResObjectString().setRemote(true);
/*  3222 */       pSubDesc.getSubName().setRemote(true);
/*  3223 */       pSubDesc.getSubUserData().setRemote(true);
/*       */     } 
/*  3225 */     if (spiSD != null) {
/*  3226 */       spiSD.getSubIdentity().setRemote(true);
/*       */     }
/*       */     try {
/*  3229 */       remoteHconn = getRemoteHconn(tls, hconn);
/*       */       
/*  3231 */       if (pSubDesc == null) {
/*  3232 */         JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2424, null);
/*       */ 
/*       */         
/*  3235 */         if (Trace.isOn) {
/*  3236 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiSubscribe(Hconn,MQSD,Phobj,Phobj,Pint,Pint,LpiSD,boolean,RemoteHsub)", (Throwable)traceRet1, 1);
/*       */         }
/*       */         
/*  3239 */         throw traceRet1;
/*       */       } 
/*       */       
/*  3242 */       if (pSubDesc.getOptions() == 0) {
/*  3243 */         JmqiException traceRet = new JmqiException(this.env, -1, null, 2, 2046, null);
/*       */         
/*  3245 */         if (Trace.isOn) {
/*  3246 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiSubscribe(Hconn,MQSD,Phobj,Phobj,Pint,Pint,LpiSD,boolean,RemoteHsub)", (Throwable)traceRet, 2);
/*       */         }
/*       */         
/*  3249 */         throw traceRet;
/*       */       } 
/*       */ 
/*       */       
/*  3253 */       if (pHobj == null) {
/*  3254 */         JmqiException traceRet2 = new JmqiException(this.env, -1, null, 2, 2019, null);
/*       */         
/*  3256 */         if (Trace.isOn) {
/*  3257 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiSubscribe(Hconn,MQSD,Phobj,Phobj,Pint,Pint,LpiSD,boolean,RemoteHsub)", (Throwable)traceRet2, 3);
/*       */         }
/*       */         
/*  3260 */         throw traceRet2;
/*       */       } 
/*       */ 
/*       */       
/*  3264 */       if (pHsub == null) {
/*  3265 */         JmqiException traceRet3 = new JmqiException(this.env, -1, null, 2, 2019, null);
/*       */         
/*  3267 */         if (Trace.isOn) {
/*  3268 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiSubscribe(Hconn,MQSD,Phobj,Phobj,Pint,Pint,LpiSD,boolean,RemoteHsub)", (Throwable)traceRet3, 4);
/*       */         }
/*       */         
/*  3271 */         throw traceRet3;
/*       */       } 
/*       */       
/*  3274 */       pHsub.setHobj(MQConstants.jmqi_MQHO_UNUSABLE_HOBJ);
/*       */       
/*  3276 */       boolean managedQueue = ((pSubDesc.getOptions() & 0x20) != 0);
/*       */       
/*  3278 */       if (managedQueue) {
/*  3279 */         if (pHobj.getHobj() != null && pHobj.getHobj() != MQConstants.jmqi_MQHO_NONE) {
/*  3280 */           JmqiException traceRet4 = new JmqiException(this.env, -1, null, 2, 2019, null);
/*       */           
/*  3282 */           if (Trace.isOn) {
/*  3283 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiSubscribe(Hconn,MQSD,Phobj,Phobj,Pint,Pint,LpiSD,boolean,RemoteHsub)", (Throwable)traceRet4, 5);
/*       */           }
/*       */ 
/*       */           
/*  3287 */           throw traceRet4;
/*       */         } 
/*       */         
/*  3290 */         if (remoteHconn.getSession().isMultiplexingEnabled())
/*       */         {
/*       */           
/*  3293 */           if (reconnecting && remoteHsub.getHobj() != null) {
/*  3294 */             proxyQueue = remoteHsub.getHobj().getProxyQueue();
/*  3295 */             proxyQueue.resetForReconnect(tls);
/*       */           }
/*       */           else {
/*       */             
/*  3299 */             proxyQueue = remoteHconn.getProxyQueueManager().createProxyQueue(tls);
/*       */           } 
/*       */         }
/*  3302 */         pHobj.setHobj(MQConstants.jmqi_MQHO_NONE);
/*       */       
/*       */       }
/*  3305 */       else if (!spiCall) {
/*       */         
/*  3307 */         if (!(pHobj.getHobj() instanceof RemoteHobj)) {
/*  3308 */           JmqiException traceRet5 = new JmqiException(this.env, -1, null, 2, 2019, null);
/*       */           
/*  3310 */           if (Trace.isOn) {
/*  3311 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiSubscribe(Hconn,MQSD,Phobj,Phobj,Pint,Pint,LpiSD,boolean,RemoteHsub)", (Throwable)traceRet5, 6);
/*       */           }
/*       */ 
/*       */           
/*  3315 */           throw traceRet5;
/*       */         } 
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/*  3321 */       remoteHconn.enterCall(tls.isDispatchThread, false);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*       */       try {
/*  3329 */         remoteSession = remoteHconn.getSession();
/*  3330 */         JmqiCodepage cp = remoteSession.getCp();
/*  3331 */         boolean swap = remoteSession.isSwap();
/*       */         
/*  3333 */         remoteSession.checkIfDisconnected();
/*       */ 
/*       */         
/*  3336 */         if (remoteHconn.getFapLevel() < 9) {
/*  3337 */           JmqiException traceRet6 = new JmqiException(this.env, -1, null, 2, 2298, null);
/*       */           
/*  3339 */           if (Trace.isOn) {
/*  3340 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiSubscribe(Hconn,MQSD,Phobj,Phobj,Pint,Pint,LpiSD,boolean,RemoteHsub)", (Throwable)traceRet6, 7);
/*       */           }
/*       */ 
/*       */           
/*  3344 */           throw traceRet6;
/*       */         } 
/*       */         
/*  3347 */         RfpVerbArray verbArray = null;
/*  3348 */         if (spiCall) {
/*  3349 */           verbArray = remoteSession.getVerbArray(7, pCompCode, pReason, false);
/*       */           
/*  3351 */           if (pCompCode.x != 0) {
/*       */             
/*  3353 */             if (Trace.isOn) {
/*  3354 */               Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiSubscribe(Hconn,MQSD,Phobj,Phobj,Pint,Pint,LpiSD,boolean,RemoteHsub)", remoteSession, 1);
/*       */             }
/*       */ 
/*       */             
/*  3358 */             return remoteSession;
/*       */           } 
/*       */         } 
/*       */ 
/*       */         
/*  3363 */         RfpMQAPI sMQAPI = null;
/*  3364 */         if (spiCall) {
/*  3365 */           sMQAPI = remoteSession.allocateMQAPI(140);
/*       */         } else {
/*       */           
/*  3368 */           sMQAPI = remoteSession.allocateMQAPI(142);
/*       */         } 
/*       */         
/*  3371 */         sMQAPI.setHandle(pHobj.getHobj().getIntegerHandle(), swap);
/*       */         
/*  3373 */         int writePos = sMQAPI.getRfpOffset() + sMQAPI.hdrSize();
/*  3374 */         byte[] commsBuffer = sMQAPI.getRfpBuffer();
/*       */ 
/*       */         
/*  3377 */         int replySegType = 158;
/*  3378 */         int transLength = 0;
/*       */         
/*  3380 */         if (spiCall) {
/*  3381 */           replySegType = 156;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  3389 */           int inSpiOffset = writePos;
/*       */ 
/*       */           
/*  3392 */           RfpINSPI inSpi = RfpINSPI.getInstance((JmqiEnvironment)this.sysenv, remoteSession, commsBuffer, inSpiOffset);
/*  3393 */           inSpi.setVerbId(7, swap);
/*       */ 
/*       */           
/*  3396 */           int spiInOutOffset = inSpiOffset + inSpi.getStructSize();
/*  3397 */           RfpSUBSCRIBESPIINOUT spiInOut = RfpSUBSCRIBESPIINOUT.getInstance(this.env, remoteSession, commsBuffer, spiInOutOffset, verbArray
/*  3398 */               .getMaxInoutVersion());
/*  3399 */           spiInOut.initEyecatcher();
/*  3400 */           spiInOut.setDefaultVersion(swap);
/*  3401 */           spiInOut.setLength(spiInOut.getStructSize(), swap);
/*       */ 
/*       */           
/*  3404 */           int spiInOffset = spiInOutOffset + spiInOut.getStructSize();
/*  3405 */           RfpSUBSCRIBESPIIN spiIn = RfpSUBSCRIBESPIIN.getInstance(this.env, remoteSession, commsBuffer, spiInOffset, verbArray
/*  3406 */               .getMaxInVersion());
/*  3407 */           spiIn.initEyecatcher();
/*  3408 */           spiIn.setDefaultVersion(swap);
/*  3409 */           spiIn.setHSub(0, swap);
/*  3410 */           int endOfSD = spiSD.writeToBuffer(spiIn.getRfpBuffer(), spiIn.getSpiSdOffset(), 4, swap, cp, jmqiTls);
/*       */           
/*  3412 */           spiIn.setlpiSdSize(endOfSD - spiIn.getSpiSdOffset());
/*  3413 */           int endOfMqsd = pSubDesc.writeToBuffer(spiIn.getRfpBuffer(), spiIn.getMqsdOffset(), 4, swap, cp, jmqiTls);
/*       */           
/*  3415 */           spiIn.setmqSdSize(endOfMqsd - spiIn.getMqsdOffset());
/*  3416 */           spiIn.setLength(spiIn.getStructSize(), swap);
/*       */ 
/*       */           
/*  3419 */           spiOutRelativeOffset = spiInOffset - sMQAPI.getRfpOffset();
/*  3420 */           spiOut = RfpSUBSCRIBESPIOUT.getInstance(this.env, remoteSession, commsBuffer, 0, verbArray
/*  3421 */               .getMaxOutVersion(), LpiSD.getCurrentSize(4), pSubDesc
/*  3422 */               .getRequiredInputBufferSize(4, cp));
/*  3423 */           spiOut.setDefaultVersion(swap);
/*       */ 
/*       */ 
/*       */           
/*  3427 */           inSpi.setOutStructVersion(spiOut.getVersion(swap), swap);
/*  3428 */           inSpi.setOutStructLength(spiOut.getStructSize(), swap);
/*       */           
/*  3430 */           transLength = sMQAPI.hdrSize() + inSpi.getStructSize() + spiInOut.getStructSize() + spiIn.getStructSize();
/*       */         } else {
/*       */           
/*  3433 */           dc.writeI32(pHsub.getHobj().getIntegerHandle(), commsBuffer, writePos, swap);
/*  3434 */           writePos += 4;
/*       */ 
/*       */           
/*  3437 */           dc.writeI32(0, commsBuffer, writePos, swap);
/*  3438 */           writePos += 4;
/*  3439 */           writePos = pSubDesc.writeToBuffer(commsBuffer, writePos, 4, swap, cp, jmqiTls);
/*       */           
/*  3441 */           transLength = writePos - sMQAPI.getRfpOffset();
/*       */         } 
/*       */         
/*  3444 */         sMQAPI.setControlFlags1(48);
/*       */ 
/*       */ 
/*       */         
/*  3448 */         sMQAPI.setCallLength(transLength + pSubDesc.getResObjectString().getVsBufSize() + pSubDesc.getSelectionString().getVsBufSize() + pSubDesc.getSubUserData().getVsBufSize());
/*       */ 
/*       */         
/*  3451 */         sMQAPI.setTransLength(transLength);
/*       */ 
/*       */         
/*  3454 */         remoteSession.sendTSH(tls, (RfpTSH)sMQAPI);
/*       */ 
/*       */         
/*  3457 */         rMQAPI = remoteSession.receiveMQIFlow(tls);
/*       */         
/*  3459 */         if (rMQAPI.getSegmentType() != replySegType || (rMQAPI.getControlFlags1() & 0x20) == 0) {
/*  3460 */           HashMap<String, Object> info = new HashMap<>();
/*  3461 */           info.put("SegmentType", Integer.valueOf(rMQAPI.getSegmentType()));
/*  3462 */           info.put("ControlFlags1", Integer.valueOf(rMQAPI.getControlFlags1()));
/*  3463 */           info.put("Description", "Unexpected flow received from server");
/*  3464 */           Trace.ffst(this, "jmqiSubscribe(Hconn,MQSD,Phobj,Phobj,Pint,Pint,LpiSD,boolean,RemoteHsub)", "02", info, null);
/*       */           
/*  3466 */           JmqiException e = new JmqiException(this.env, -1, null, 2, 2195, null);
/*       */           
/*  3468 */           if (Trace.isOn) {
/*  3469 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiSubscribe(Hconn,MQSD,Phobj,Phobj,Pint,Pint,LpiSD,boolean,RemoteHsub)", (Throwable)e, 8);
/*       */           }
/*       */           
/*  3472 */           throw e;
/*       */         } 
/*       */ 
/*       */ 
/*       */         
/*  3477 */         swap = remoteSession.isSwap();
/*       */ 
/*       */         
/*  3480 */         pCompCode.x = rMQAPI.getCompCode(swap);
/*  3481 */         pReason.x = rMQAPI.getReasonCode(swap);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  3487 */         if (pCompCode.x != 2) {
/*  3488 */           int defReadAhead, readPos = rMQAPI.getRfpOffset() + rMQAPI.hdrSize();
/*  3489 */           commsBuffer = rMQAPI.getRfpBuffer();
/*       */           
/*  3491 */           int returnedHsub = 0;
/*  3492 */           if (spiCall) {
/*       */             
/*  3494 */             spiOut.setRfpBuffer(commsBuffer);
/*  3495 */             spiOut.setRfpOffset(rMQAPI.getRfpOffset() + spiOutRelativeOffset);
/*       */             
/*  3497 */             if (!spiOut.checkID()) {
/*  3498 */               HashMap<String, Object> info = new HashMap<>();
/*  3499 */               info.put("SegmentType", Integer.valueOf(rMQAPI.getSegmentType()));
/*  3500 */               info.put("ControlFlags1", Integer.valueOf(rMQAPI.getControlFlags1()));
/*  3501 */               info.put("Description", "Unexpected flow received from server");
/*  3502 */               Trace.ffst(this, "jmqiSubscribe(Hconn,MQSD,Phobj,Phobj,Pint,Pint,LpiSD,boolean,RemoteHsub)", "01", info, null);
/*       */               
/*  3504 */               JmqiException e = new JmqiException(this.env, -1, null, 2, 2195, null);
/*       */               
/*  3506 */               if (Trace.isOn) {
/*  3507 */                 Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiSubscribe(Hconn,MQSD,Phobj,Phobj,Pint,Pint,LpiSD,boolean,RemoteHsub)", (Throwable)e, 9);
/*       */               }
/*       */ 
/*       */               
/*  3511 */               throw e;
/*       */             } 
/*       */             
/*  3514 */             returnedHsub = spiOut.getHSub(swap);
/*       */             
/*  3516 */             spiSD.readFromBuffer(spiOut.getRfpBuffer(), spiOut.getSpiSdOffset(), 4, swap, cp, jmqiTls);
/*       */             
/*  3518 */             defReadAhead = spiSD.getDestReadAhead();
/*       */             
/*  3520 */             pSubDesc.readFromBuffer(spiOut.getRfpBuffer(), spiOut.getMqsdOffset(), 4, swap, cp, jmqiTls);
/*       */           
/*       */           }
/*       */           else {
/*       */ 
/*       */             
/*  3526 */             returnedHsub = dc.readI32(commsBuffer, readPos, swap);
/*       */             
/*  3528 */             readPos += 4;
/*       */ 
/*       */             
/*  3531 */             defReadAhead = dc.readI32(commsBuffer, readPos, swap);
/*  3532 */             readPos += 4;
/*       */ 
/*       */             
/*  3535 */             readPos = pSubDesc.readFromBuffer(commsBuffer, readPos, 4, swap, cp, jmqiTls);
/*       */           } 
/*       */ 
/*       */           
/*  3539 */           RemoteHobj resolvedHobj = null;
/*       */           
/*  3541 */           if (managedQueue) {
/*       */ 
/*       */ 
/*       */             
/*  3545 */             resolvedHobj = new RemoteHobj(this.env, rMQAPI.getHandle(remoteSession.isSwap()), proxyQueue, "", 8, 0, 0, 2, defReadAhead, null, remoteSession);
/*       */ 
/*       */ 
/*       */             
/*  3549 */             pHobj.setHobj(resolvedHobj);
/*       */             
/*  3551 */             if (reconnecting) {
/*  3552 */               RemoteHobj oldHobj = remoteHsub.getHobj();
/*  3553 */               oldHobj.copyCallbackTo(resolvedHobj);
/*       */             } 
/*       */ 
/*       */ 
/*       */             
/*  3558 */             if (proxyQueue != null) {
/*  3559 */               int openOptions = 10;
/*  3560 */               if ((pSubDesc.getOptions() & 0x10000000) != 0) {
/*  3561 */                 openOptions |= 0x100000;
/*       */               }
/*  3563 */               else if ((pSubDesc.getOptions() & 0x8000000) != 0) {
/*  3564 */                 openOptions |= 0x80000;
/*       */               } 
/*  3566 */               resolvedHobj.setOpenOptions(openOptions);
/*  3567 */               remoteHconn.getProxyQueueManager().setIdentifier(tls, proxyQueue, resolvedHobj);
/*  3568 */               proxyQueue.setHconn(remoteHconn);
/*       */             } else {
/*       */               
/*  3571 */               resolvedHobj.setOpenOptions(524298);
/*       */             
/*       */             }
/*       */ 
/*       */           
/*       */           }
/*  3577 */           else if (!(pHobj.getHobj() instanceof RemoteHobj)) {
/*  3578 */             resolvedHobj = new RemoteHobj(this.env, rMQAPI.getHandle(remoteSession.isSwap()), proxyQueue, "", 8, 0, 0, 2, defReadAhead, null, remoteSession);
/*       */ 
/*       */ 
/*       */             
/*  3582 */             pHobj.setHobj(resolvedHobj);
/*       */           } 
/*       */ 
/*       */           
/*  3586 */           if (reconnecting) {
/*  3587 */             remoteHsub.setHandle(returnedHsub);
/*  3588 */             remoteHsub.setHobj(resolvedHobj);
/*  3589 */             remoteHobj.updateHandle(resolvedHobj.getIntegerHandle(), remoteSession);
/*       */           }
/*       */           else {
/*       */             
/*  3593 */             remoteHsub = new RemoteHsub(this.env, returnedHsub, resolvedHobj, pSubDesc, spiSD, spiCall, remoteHconn.getCodePage(), tls);
/*       */           } 
/*  3595 */           if (resolvedHobj != null) {
/*  3596 */             resolvedHobj.setParentHsub(remoteHsub);
/*       */           }
/*  3598 */           pHsub.setHobj(remoteHsub);
/*  3599 */           remoteHconn.addHsub(remoteHsub);
/*       */         }
/*       */       
/*       */       }
/*       */       finally {
/*       */         
/*  3605 */         if (Trace.isOn) {
/*  3606 */           Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiSubscribe(Hconn,MQSD,Phobj,Phobj,Pint,Pint,LpiSD,boolean,RemoteHsub)");
/*       */         }
/*       */ 
/*       */         
/*  3610 */         pSubDesc.getObjectString().setRemote(false);
/*  3611 */         pSubDesc.getSelectionString().setRemote(false);
/*  3612 */         pSubDesc.getResObjectString().setRemote(false);
/*  3613 */         pSubDesc.getSubName().setRemote(false);
/*  3614 */         pSubDesc.getSubUserData().setRemote(false);
/*       */ 
/*       */ 
/*       */         
/*       */         try {
/*  3619 */           if (remoteHconn != null) {
/*  3620 */             if (rMQAPI != null) {
/*       */               try {
/*  3622 */                 remoteSession.releaseReceivedTSH((RfpTSH)rMQAPI);
/*       */               }
/*  3624 */               catch (JmqiException e) {
/*  3625 */                 if (Trace.isOn) {
/*  3626 */                   Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiSubscribe(Hconn,MQSD,Phobj,Phobj,Pint,Pint,LpiSD,boolean,RemoteHsub)", (Throwable)e, 1);
/*       */                 }
/*       */               } 
/*       */             }
/*       */ 
/*       */ 
/*       */ 
/*       */             
/*  3634 */             remoteHconn.leaveCall(pReason.x);
/*       */           }
/*       */         
/*  3637 */         } catch (JmqiException e) {
/*  3638 */           if (Trace.isOn) {
/*  3639 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiSubscribe(Hconn,MQSD,Phobj,Phobj,Pint,Pint,LpiSD,boolean,RemoteHsub)", (Throwable)e, 2);
/*       */           }
/*       */         }
/*       */       
/*       */       }
/*       */     
/*  3645 */     } catch (JmqiException e) {
/*  3646 */       if (Trace.isOn) {
/*  3647 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiSubscribe(Hconn,MQSD,Phobj,Phobj,Pint,Pint,LpiSD,boolean,RemoteHsub)", (Throwable)e, 3);
/*       */       }
/*       */ 
/*       */       
/*  3651 */       jmqiTls.lastException = e;
/*  3652 */       pCompCode.x = e.getCompCode();
/*  3653 */       pReason.x = e.getReason();
/*       */     } 
/*       */     
/*  3656 */     if (Trace.isOn) {
/*  3657 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiSubscribe(Hconn,MQSD,Phobj,Phobj,Pint,Pint,LpiSD,boolean,RemoteHsub)", remoteSession, 2);
/*       */     }
/*       */     
/*  3660 */     return remoteSession;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void jmqiNotify(Hconn notifyHconn, Hconn getterHconn, int options, LpiNotifyDetails notifyDetails, Pint pCompCode, Pint pReason) {
/*  3684 */     if (Trace.isOn) {
/*  3685 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiNotify(Hconn,Hconn,final int,LpiNotifyDetails,final Pint,final Pint)", new Object[] { notifyHconn, getterHconn, 
/*       */             
/*  3687 */             Integer.valueOf(options), notifyDetails, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  3694 */     boolean callSpiNotify = true;
/*  3695 */     RemoteHconn getterRemoteHconn = null;
/*       */     
/*  3697 */     if (notifyDetails.getReason() != 2107) {
/*  3698 */       if (Trace.isOn) {
/*  3699 */         Trace.data(this, "jmqiNotify", "invalid reason code " + notifyDetails.getReason());
/*       */       }
/*       */ 
/*       */       
/*  3703 */       pCompCode.x = 2;
/*  3704 */       pReason.x = 2298;
/*  3705 */       callSpiNotify = false;
/*       */     }
/*  3707 */     else if (getterHconn instanceof RemoteHconn) {
/*  3708 */       getterRemoteHconn = (RemoteHconn)getterHconn;
/*  3709 */       if (!getterRemoteHconn.isInUse()) {
/*  3710 */         if (Trace.isOn) {
/*  3711 */           Trace.data(this, "jmqiNotify", "Skipping actual spiNotify call, as no calls are in progress");
/*       */         }
/*       */ 
/*       */         
/*  3715 */         pCompCode.x = 0;
/*  3716 */         pReason.x = 0;
/*  3717 */         callSpiNotify = false;
/*       */       } 
/*       */     } 
/*  3720 */     if (callSpiNotify) {
/*  3721 */       RemoteSession session = spiNotify(notifyHconn, getterHconn, options, notifyDetails, pCompCode, pReason);
/*  3722 */       RemoteHconn remoteHconn = session.getHconn();
/*       */ 
/*       */       
/*       */       try {
/*  3726 */         if (0 != pReason.x && 2107 != pReason.x && 2009 == pReason.x && (remoteHconn == null || 
/*       */ 
/*       */           
/*  3729 */           !remoteHconn.isReconnectable()) && getterRemoteHconn != null && 
/*       */ 
/*       */           
/*  3732 */           !getterRemoteHconn.getSession().isDisconnected() && 
/*       */           
/*  3734 */           !remoteHconn.equals(getterRemoteHconn))
/*       */         {
/*       */ 
/*       */ 
/*       */           
/*  3739 */           Pint pCompCodeTmp = new Pint((JmqiEnvironment)this.sysenv, 0);
/*  3740 */           Pint pReasonTmp = new Pint((JmqiEnvironment)this.sysenv, 0);
/*  3741 */           Phconn phconnTmp = new Phconn((JmqiEnvironment)this.sysenv);
/*       */           
/*  3743 */           jmqiConnect(getterRemoteHconn
/*  3744 */               .getQmName(), getterRemoteHconn
/*  3745 */               .getJmqiConnectionOptions(), getterRemoteHconn
/*  3746 */               .getConnectionOptions(), null, phconnTmp, pCompCodeTmp, pReasonTmp);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  3752 */           if (pCompCodeTmp.x == 0 && pReasonTmp.x == 0) {
/*  3753 */             jmqiNotify(phconnTmp.getHconn(), getterHconn, options, notifyDetails, pCompCode, pReason);
/*  3754 */             MQDISC(phconnTmp, pCompCodeTmp, pReasonTmp);
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*       */           }
/*  3760 */           else if (getterRemoteHconn.isInUse()) {
/*       */             try {
/*  3762 */               getterRemoteHconn.getSession().terminate();
/*       */             }
/*  3764 */             catch (JmqiException je) {
/*  3765 */               if (Trace.isOn) {
/*  3766 */                 Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiNotify(Hconn,Hconn,final int,LpiNotifyDetails,final Pint,final Pint)", (Throwable)je, 1);
/*       */               
/*       */               }
/*       */             }
/*       */           
/*       */           }
/*       */         
/*       */         }
/*       */       
/*       */       }
/*  3776 */       catch (JmqiException je) {
/*  3777 */         pCompCode.x = je.getCompCode();
/*  3778 */         pReason.x = je.getReason();
/*       */       } 
/*       */ 
/*       */       
/*  3782 */       if (0 != pReason.x && remoteHconn.isReconnectable() && 
/*  3783 */         !threadIsReconnectThread() && 
/*  3784 */         RemoteHconn.isReconnectableReasonCode(pReason.x) && 
/*  3785 */         !remoteHconn.hasFailed()) {
/*       */         try {
/*  3787 */           remoteHconn.initiateReconnection(session);
/*  3788 */           jmqiNotify(notifyHconn, getterHconn, options, notifyDetails, pCompCode, pReason);
/*       */         }
/*  3790 */         catch (JmqiException je) {
/*  3791 */           if (Trace.isOn) {
/*  3792 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiNotify(Hconn,Hconn,final int,LpiNotifyDetails,final Pint,final Pint)", (Throwable)je);
/*       */           }
/*       */         } 
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  3803 */       if (remoteHconn.hasFailed()) {
/*  3804 */         pCompCode.x = remoteHconn.getReconnectionFailureCompCode();
/*  3805 */         pReason.x = remoteHconn.getReconnectionFailureReason();
/*  3806 */         RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  3807 */         JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/*  3808 */         jmqiTls.lastException = remoteHconn.getReconnectionFailureException();
/*       */       } 
/*       */     } 
/*       */     
/*  3812 */     if (Trace.isOn) {
/*  3813 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiNotify(Hconn,Hconn,final int,LpiNotifyDetails,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public RemoteSession spiNotify(Hconn notifyHconn, Hconn getterHconn, int options, LpiNotifyDetails notifyDetails, Pint pCompCode, Pint pReason) {
/*  3841 */     if (Trace.isOn) {
/*  3842 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiNotify(Hconn, Hconn,int,LpiNotifyDetails,Pint,Pint)", new Object[] { notifyHconn, getterHconn, 
/*       */             
/*  3844 */             Integer.valueOf(options), notifyDetails, pCompCode, pReason });
/*       */     }
/*       */     
/*  3847 */     RemoteHconn hconnForAction = null;
/*  3848 */     RemoteSession sessionForAction = null;
/*  3849 */     RfpMQAPI rMQAPI = null;
/*       */     
/*  3851 */     RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  3852 */     JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/*  3853 */     jmqiTls.lastException = null;
/*       */     try {
/*  3855 */       RemoteHconn getterRemoteHconn = getRemoteHconn(tls, getterHconn);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  3862 */       RemoteHconn notifyRemoteHconn = getRemoteHconn(tls, notifyHconn);
/*       */       
/*  3864 */       RemoteSession notifyRemoteSession = notifyRemoteHconn.getSession();
/*  3865 */       RemoteSession getterRemoteSession = getterRemoteHconn.getSession();
/*       */       
/*  3867 */       RemoteConnection parentConn = getterRemoteSession.getParentConnection();
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  3876 */       if (parentConn.isMultiplexSyncGetCapable()) {
/*       */ 
/*       */         
/*  3879 */         hconnForAction = notifyRemoteHconn;
/*       */       }
/*       */       else {
/*       */         
/*  3883 */         hconnForAction = getterRemoteHconn;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  3891 */       hconnForAction.enterNotifyCall(tls.isDispatchThread, true);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*       */       try {
/*  3900 */         notifyRemoteSession = notifyRemoteHconn.getSession();
/*  3901 */         getterRemoteSession = getterRemoteHconn.getSession();
/*       */         
/*  3903 */         if (parentConn.isMultiplexSyncGetCapable()) {
/*       */ 
/*       */           
/*  3906 */           sessionForAction = notifyRemoteSession;
/*       */         }
/*       */         else {
/*       */           
/*  3910 */           sessionForAction = getterRemoteSession;
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  3918 */         RfpVerbArray verbArray = sessionForAction.getVerbArray(11, pCompCode, pReason, true);
/*       */ 
/*       */         
/*  3921 */         if (pCompCode.x != 0) {
/*  3922 */           if (Trace.isOn) {
/*  3923 */             Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiNotify(Hconn,Hconn,int,LpiNotifyDetails,Pint,Pint)", sessionForAction, 1);
/*       */           }
/*       */           
/*  3926 */           return sessionForAction;
/*       */         } 
/*       */         
/*  3929 */         sessionForAction.checkIfDisconnected();
/*       */ 
/*       */         
/*  3932 */         if (hconnForAction.getFapLevel() < 9) {
/*  3933 */           JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2298, null);
/*       */ 
/*       */           
/*  3936 */           if (Trace.isOn) {
/*  3937 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiNotify(Hconn,Hconn,int,LpiNotifyDetails,Pint,Pint)", (Throwable)traceRet1, 1);
/*       */           }
/*       */           
/*  3940 */           throw traceRet1;
/*       */         } 
/*       */         
/*  3943 */         boolean swap = sessionForAction.isSwap();
/*       */ 
/*       */         
/*  3946 */         RfpMQAPI sMQAPI = null;
/*  3947 */         sMQAPI = sessionForAction.allocateMQAPI(140, true);
/*       */         
/*  3949 */         sMQAPI.setHandle(0, swap);
/*       */         
/*  3951 */         int inSpiOffset = sMQAPI.getRfpOffset() + sMQAPI.hdrSize();
/*  3952 */         byte[] commsBuffer = sMQAPI.getRfpBuffer();
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  3962 */         RfpINSPI inSpi = RfpINSPI.getInstance((JmqiEnvironment)this.sysenv, sessionForAction, commsBuffer, inSpiOffset);
/*       */         
/*  3964 */         int spiInOutOffset = inSpiOffset + inSpi.getStructSize();
/*  3965 */         RfpNOTIFYSPIINOUT spiInOut = RfpNOTIFYSPIINOUT.getInstance(this.env, sessionForAction, commsBuffer, spiInOutOffset, verbArray
/*  3966 */             .getMaxInoutVersion());
/*       */         
/*  3968 */         int spiInOffset = spiInOutOffset + spiInOut.getStructSize();
/*  3969 */         RfpNOTIFYSPIIN spiIn = RfpNOTIFYSPIIN.getInstance(this.env, sessionForAction, commsBuffer, spiInOffset, verbArray
/*  3970 */             .getMaxInVersion());
/*       */         
/*  3972 */         RfpNOTIFYSPIOUT spiOut = RfpNOTIFYSPIOUT.getInstance(this.env, sessionForAction, commsBuffer, 0, verbArray
/*  3973 */             .getMaxOutVersion());
/*  3974 */         spiOut.setDefaultVersion(swap);
/*       */ 
/*       */         
/*  3977 */         inSpi.setVerbId(11, swap);
/*  3978 */         inSpi.setOutStructVersion(spiOut.getVersion(swap), swap);
/*  3979 */         inSpi.setOutStructLength(spiOut.getStructSize(), swap);
/*       */ 
/*       */         
/*  3982 */         spiInOut.initEyecatcher();
/*  3983 */         spiInOut.setDefaultVersion(swap);
/*  3984 */         spiInOut.setLength(spiInOut.getStructSize(), swap);
/*       */ 
/*       */         
/*  3987 */         spiIn.initEyecatcher();
/*  3988 */         spiIn.setVersion(1, swap);
/*  3989 */         spiIn.setLength(spiIn.getStructSize(), swap);
/*  3990 */         spiIn.setOptions(options, swap);
/*  3991 */         spiIn.setReason(notifyDetails.getReason(), swap);
/*  3992 */         spiIn.setConnectionId(notifyDetails.getConnectionId());
/*       */ 
/*       */         
/*  3995 */         sMQAPI.setControlFlags1(48);
/*       */         
/*  3997 */         int transLength = sMQAPI.hdrSize() + inSpi.getStructSize() + spiInOut.getStructSize() + spiIn.getStructSize();
/*       */ 
/*       */         
/*  4000 */         sMQAPI.setCallLength(transLength);
/*       */ 
/*       */         
/*  4003 */         sMQAPI.setTransLength(transLength);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  4013 */         RfpTSH rTSH = sessionForAction.exchangeTSH(tls, (RfpTSH)sMQAPI);
/*       */         
/*  4015 */         rMQAPI = new RfpMQAPI((JmqiEnvironment)this.sysenv, rTSH.getRfpBuffer(), rTSH.getRfpOffset());
/*       */ 
/*       */         
/*  4018 */         rMQAPI.setParentBuffer(rTSH.getParentBuffer());
/*  4019 */         rMQAPI.setTransLength(rTSH.getTransLength());
/*  4020 */         rMQAPI.setTshType(rTSH.getTshType());
/*  4021 */         rMQAPI.setRfpBuffer(rTSH.getRfpBuffer());
/*  4022 */         rMQAPI.setRfpOffset(rTSH.getRfpOffset());
/*       */ 
/*       */         
/*  4025 */         if (rMQAPI.getTransLength() < rMQAPI.hdrSize()) {
/*  4026 */           HashMap<String, Object> info = new HashMap<>();
/*  4027 */           info.put("TransLength", Integer.valueOf(rMQAPI.getTransLength()));
/*  4028 */           info.put("hdrSize", Integer.valueOf(rMQAPI.hdrSize()));
/*  4029 */           info.put("Description", "Invalid MQI structure received");
/*  4030 */           Trace.ffst(this, "spiNotify(Hconn,Hconn,int,LpiNotifyDetails,Pint,Pint)", "01", info, null);
/*  4031 */           JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*       */ 
/*       */           
/*  4034 */           if (Trace.isOn) {
/*  4035 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiNotify(Hconn,Hconn,int,LpiNotifyDetails,Pint,Pint)", (Throwable)traceRet1, 2);
/*       */           }
/*       */           
/*  4038 */           throw traceRet1;
/*       */         } 
/*       */         
/*  4041 */         if (rMQAPI.getSegmentType() != 156 || (rMQAPI.getControlFlags1() & 0x20) == 0) {
/*  4042 */           HashMap<String, Object> info = new HashMap<>();
/*  4043 */           info.put("SegmentType", Integer.valueOf(rTSH.getSegmentType()));
/*  4044 */           info.put("ControlFlags1", Integer.valueOf(rTSH.getControlFlags1()));
/*  4045 */           info.put("Description", "Unexpected flow received from server");
/*  4046 */           Trace.ffst(this, "spiNotify(Hconn,Hconn,int,LpiNotifyDetails,Pint,Pint)", "01", info, null);
/*  4047 */           JmqiException traceRet2 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*       */           
/*  4049 */           if (Trace.isOn) {
/*  4050 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiNotify(Hconn,Hconn,int,LpiNotifyDetails,Pint,Pint)", (Throwable)traceRet2, 3);
/*       */           }
/*       */           
/*  4053 */           throw traceRet2;
/*       */         } 
/*       */ 
/*       */         
/*  4057 */         pCompCode.x = rMQAPI.getCompCode(sessionForAction.isSwap());
/*  4058 */         pReason.x = rMQAPI.getReasonCode(sessionForAction.isSwap());
/*       */       }
/*       */       finally {
/*       */         
/*  4062 */         if (Trace.isOn) {
/*  4063 */           Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiNotify(Hconn,Hconn,int,LpiNotifyDetails,Pint,Pint)", 1);
/*       */         }
/*       */ 
/*       */         
/*       */         try {
/*  4068 */           if (sessionForAction != null) {
/*       */             try {
/*  4070 */               if (rMQAPI != null) {
/*  4071 */                 sessionForAction.releaseReceivedTSH((RfpTSH)rMQAPI);
/*       */               }
/*       */             } finally {
/*       */               
/*  4075 */               if (Trace.isOn) {
/*  4076 */                 Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiNotify(Hconn,Hconn,int,LpiNotifyDetails,Pint,Pint)", 2);
/*       */               }
/*       */               
/*  4079 */               hconnForAction.leaveNotifyCall(pReason.x, true);
/*       */             }
/*       */           
/*       */           }
/*  4083 */         } catch (JmqiException e) {
/*  4084 */           if (Trace.isOn) {
/*  4085 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiNotify(Hconn,Hconn,int,LpiNotifyDetails,Pint,Pint)", (Throwable)e, 1);
/*       */           }
/*       */         }
/*       */       
/*       */       }
/*       */     
/*  4091 */     } catch (JmqiException e) {
/*  4092 */       if (Trace.isOn) {
/*  4093 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiNotify(Hconn,Hconn,int,LpiNotifyDetails,Pint,Pint)", (Throwable)e, 2);
/*       */       }
/*       */       
/*  4096 */       if (e.getCompCode() != 2 || e.getReason() != 2009) {
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  4101 */         jmqiTls.lastException = e;
/*  4102 */         pCompCode.x = e.getCompCode();
/*  4103 */         pReason.x = e.getReason();
/*       */       } 
/*       */     } 
/*  4106 */     if (Trace.isOn) {
/*  4107 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiNotify(Hconn,Hconn,int,LpiNotifyDetails,Pint,Pint)", sessionForAction, 2);
/*       */     }
/*       */     
/*  4110 */     return sessionForAction;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void spiUnsubscribe(Hconn hconn, LpiUSD lpiUSD, Pint pCompCode, Pint pReason) {
/*  4127 */     if (Trace.isOn) {
/*  4128 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiUnsubscribe(Hconn,LpiUSD,Pint,Pint)", new Object[] { hconn, lpiUSD, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */ 
/*       */     
/*  4133 */     if (Trace.isOn) {
/*  4134 */       Trace.data(this, "spiUnsubscribe(Hconn,LpiUSD,Pint,Pint)", "__________");
/*  4135 */       Trace.data(this, "spiUnsubscribe(Hconn,LpiUSD,Pint,Pint)", "spiUnsubscribe >>");
/*  4136 */       Trace.data(this, "spiUnsubscribe(Hconn,LpiUSD,Pint,Pint)", "Hconn", hconn);
/*  4137 */       Trace.data(this, "spiUnsubscribe(Hconn,LpiUSD,Pint,Pint)", "LpiUSD", lpiUSD);
/*  4138 */       Trace.data(this, "spiUnsubscribe(Hconn,LpiUSD,Pint,Pint)", "CompCode", pCompCode);
/*  4139 */       Trace.data(this, "spiUnsubscribe(Hconn,LpiUSD,Pint,Pint)", "Reason", pReason);
/*  4140 */       Trace.data(this, "spiUnsubscribe(Hconn,LpiUSD,Pint,Pint)", "__________");
/*       */     } 
/*       */ 
/*       */     
/*  4144 */     RemoteHconn remoteHconn = null;
/*  4145 */     RemoteSession remoteSession = null;
/*  4146 */     RfpMQAPI rMQAPI = null;
/*       */     
/*  4148 */     RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  4149 */     JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/*  4150 */     jmqiTls.lastException = null;
/*       */ 
/*       */     
/*  4153 */     if (lpiUSD != null) {
/*  4154 */       lpiUSD.getSubName().setRemote(true);
/*  4155 */       lpiUSD.getSubIdentity().setRemote(true);
/*       */     } 
/*       */     try {
/*  4158 */       remoteHconn = getRemoteHconn(tls, hconn);
/*       */ 
/*       */       
/*  4161 */       remoteHconn.enterCall(tls.isDispatchThread, false);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*       */       try {
/*  4169 */         remoteSession = remoteHconn.getSession();
/*  4170 */         RfpVerbArray verbArray = remoteSession.getVerbArray(8, pCompCode, pReason, true);
/*  4171 */         if (pCompCode.x != 0) {
/*       */           
/*  4173 */           if (Trace.isOn) {
/*  4174 */             Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiUnsubscribe(Hconn,LpiUSD,Pint,Pint)", 1);
/*       */           }
/*       */           
/*       */           return;
/*       */         } 
/*       */         
/*  4180 */         remoteSession.checkIfDisconnected();
/*       */         
/*  4182 */         JmqiCodepage cp = remoteSession.getCp();
/*  4183 */         boolean swap = remoteSession.isSwap();
/*       */ 
/*       */         
/*  4186 */         if (remoteHconn.getFapLevel() < 9) {
/*  4187 */           JmqiException traceRet2 = new JmqiException(this.env, -1, null, 2, 2298, null);
/*       */ 
/*       */           
/*  4190 */           if (Trace.isOn) {
/*  4191 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiUnsubscribe(Hconn,LpiUSD,Pint,Pint)", (Throwable)traceRet2, 1);
/*       */           }
/*       */           
/*  4194 */           throw traceRet2;
/*       */         } 
/*       */ 
/*       */         
/*  4198 */         RfpMQAPI sMQAPI = null;
/*  4199 */         sMQAPI = remoteSession.allocateMQAPI(140);
/*       */         
/*  4201 */         int inSpiOffset = sMQAPI.getRfpOffset() + sMQAPI.hdrSize();
/*  4202 */         byte[] commsBuffer = sMQAPI.getRfpBuffer();
/*       */         
/*  4204 */         int spiUsdSize = LpiUSD.getCurrentSize(4);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  4212 */         RfpINSPI inSpi = RfpINSPI.getInstance((JmqiEnvironment)this.sysenv, remoteSession, commsBuffer, inSpiOffset);
/*       */         
/*  4214 */         int spiInOutOffset = inSpiOffset + inSpi.getStructSize();
/*  4215 */         RfpUNSUBSCRIBESPIINOUT spiInOut = RfpUNSUBSCRIBESPIINOUT.getInstance(this.env, remoteSession, commsBuffer, spiInOutOffset, verbArray
/*  4216 */             .getMaxInoutVersion());
/*       */         
/*  4218 */         int spiInOffset = spiInOutOffset + spiInOut.getStructSize();
/*  4219 */         RfpUNSUBSCRIBESPIIN spiIn = RfpUNSUBSCRIBESPIIN.getInstance(this.env, remoteSession, commsBuffer, spiInOffset, verbArray
/*  4220 */             .getMaxInVersion(), spiUsdSize);
/*       */         
/*  4222 */         int spiOutRelativeOffset = spiInOffset - sMQAPI.getRfpOffset();
/*  4223 */         RfpUNSUBSCRIBESPIOUT spiOut = RfpUNSUBSCRIBESPIOUT.getInstance(this.env, remoteSession, commsBuffer, 0, verbArray
/*  4224 */             .getMaxOutVersion(), spiUsdSize);
/*  4225 */         spiOut.setDefaultVersion(swap);
/*       */ 
/*       */         
/*  4228 */         inSpi.setVerbId(8, swap);
/*  4229 */         inSpi.setOutStructVersion(spiOut.getVersion(swap), swap);
/*  4230 */         inSpi.setOutStructLength(spiOut.getStructSize(), swap);
/*       */ 
/*       */         
/*  4233 */         spiInOut.initEyecatcher();
/*  4234 */         spiInOut.setDefaultVersion(swap);
/*  4235 */         spiInOut.setLength(spiInOut.getStructSize(), swap);
/*       */ 
/*       */         
/*  4238 */         spiIn.initEyecatcher();
/*  4239 */         spiIn.setDefaultVersion(swap);
/*  4240 */         spiIn.setLength(spiIn.getStructSize(), swap);
/*       */ 
/*       */         
/*  4243 */         int tailPos = lpiUSD.writeToBuffer(spiIn.getRfpBuffer(), spiIn.getSpiUsdOffset(), 4, swap, cp, jmqiTls);
/*       */ 
/*       */ 
/*       */         
/*  4247 */         sMQAPI.setControlFlags1(48);
/*       */         
/*  4249 */         int transLength = tailPos - sMQAPI.getRfpOffset();
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  4257 */         sMQAPI.setCallLength(transLength);
/*       */ 
/*       */         
/*  4260 */         sMQAPI.setTransLength(transLength);
/*       */ 
/*       */         
/*  4263 */         remoteSession.sendTSH(tls, (RfpTSH)sMQAPI);
/*       */ 
/*       */         
/*  4266 */         rMQAPI = remoteSession.receiveMQIFlow(tls);
/*       */         
/*  4268 */         if (rMQAPI.getSegmentType() != 156 || (rMQAPI.getControlFlags1() & 0x20) == 0) {
/*  4269 */           HashMap<String, Object> info = new HashMap<>();
/*  4270 */           info.put("SegmentType", Integer.valueOf(rMQAPI.getSegmentType()));
/*  4271 */           info.put("ControlFlags1", Integer.valueOf(rMQAPI.getControlFlags1()));
/*  4272 */           info.put("Description", "Unexpected flow received from server");
/*  4273 */           Trace.ffst(this, "spiUnsubscribe(Hconn,LpiUSD,Pint,Pint)", "01", info, null);
/*  4274 */           JmqiException traceRet3 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*       */           
/*  4276 */           if (Trace.isOn) {
/*  4277 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiUnsubscribe(Hconn,LpiUSD,Pint,Pint)", (Throwable)traceRet3, 2);
/*       */           }
/*       */           
/*  4280 */           throw traceRet3;
/*       */         } 
/*       */ 
/*       */ 
/*       */         
/*  4285 */         swap = remoteSession.isSwap();
/*       */ 
/*       */         
/*  4288 */         pCompCode.x = rMQAPI.getCompCode(swap);
/*  4289 */         pReason.x = rMQAPI.getReasonCode(swap);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  4295 */         if (pCompCode.x != 2) {
/*  4296 */           commsBuffer = rMQAPI.getRfpBuffer();
/*       */ 
/*       */           
/*  4299 */           spiOut.setRfpBuffer(commsBuffer);
/*  4300 */           spiOut.setRfpOffset(rMQAPI.getRfpOffset() + spiOutRelativeOffset);
/*       */           
/*  4302 */           if (!spiOut.checkID()) {
/*  4303 */             HashMap<String, Object> info = new HashMap<>();
/*  4304 */             info.put("SegmentType", Integer.valueOf(rMQAPI.getSegmentType()));
/*  4305 */             info.put("ControlFlags1", Integer.valueOf(rMQAPI.getControlFlags1()));
/*  4306 */             info.put("Description", "Unexpected flow received from server");
/*  4307 */             Trace.ffst(this, "spiUnsubscribe(Hconn,LpiUSD,Pint,Pint)", "01", info, null);
/*  4308 */             JmqiException e = new JmqiException(this.env, -1, null, 2, 2195, null);
/*       */             
/*  4310 */             if (Trace.isOn) {
/*  4311 */               Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiUnsubscribe(Hconn,LpiUSD,Pint,Pint)", (Throwable)e, 3);
/*       */             }
/*       */             
/*  4314 */             throw e;
/*       */           } 
/*  4316 */           lpiUSD.readFromBuffer(spiOut.getRfpBuffer(), spiOut.getSpiUsdOffset(), 4, swap, cp, jmqiTls);
/*       */         }
/*       */       
/*       */       } finally {
/*       */         
/*  4321 */         if (Trace.isOn) {
/*  4322 */           Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiUnsubscribe(Hconn,LpiUSD,Pint,Pint)");
/*       */         }
/*       */         
/*  4325 */         if (lpiUSD != null) {
/*  4326 */           lpiUSD.getSubName().setRemote(false);
/*  4327 */           lpiUSD.getSubIdentity().setRemote(false);
/*       */         } 
/*       */ 
/*       */ 
/*       */         
/*       */         try {
/*  4333 */           if (remoteHconn != null) {
/*  4334 */             if (rMQAPI != null) {
/*       */               try {
/*  4336 */                 remoteSession.releaseReceivedTSH((RfpTSH)rMQAPI);
/*       */               }
/*  4338 */               catch (JmqiException e) {
/*  4339 */                 if (Trace.isOn) {
/*  4340 */                   Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiUnsubscribe(Hconn,LpiUSD,Pint,Pint)", (Throwable)e, 1);
/*       */                 }
/*       */               } 
/*       */             }
/*       */ 
/*       */ 
/*       */             
/*  4347 */             remoteHconn.leaveCall(pReason.x);
/*       */           }
/*       */         
/*  4350 */         } catch (JmqiException e) {
/*  4351 */           if (Trace.isOn) {
/*  4352 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiUnsubscribe(Hconn,LpiUSD,Pint,Pint)", (Throwable)e, 2);
/*       */           }
/*       */         }
/*       */       
/*       */       }
/*       */     
/*  4358 */     } catch (JmqiException e) {
/*  4359 */       if (Trace.isOn) {
/*  4360 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiUnsubscribe(Hconn,LpiUSD,Pint,Pint)", (Throwable)e, 3);
/*       */       }
/*       */ 
/*       */       
/*  4364 */       jmqiTls.lastException = e;
/*  4365 */       pCompCode.x = e.getCompCode();
/*  4366 */       pReason.x = e.getReason();
/*       */     } 
/*       */ 
/*       */     
/*  4370 */     if (Trace.isOn) {
/*  4371 */       Trace.data(this, "spiUnsubscribe(Hconn,LpiUSD,Pint,Pint)", "__________");
/*  4372 */       Trace.data(this, "spiUnsubscribe(Hconn,LpiUSD,Pint,Pint)", "MQSUB <<");
/*       */       
/*  4374 */       Trace.data(this, "spiUnsubscribe(Hconn,LpiUSD,Pint,Pint)", "Hconn", hconn);
/*  4375 */       Trace.data(this, "spiUnsubscribe(Hconn,LpiUSD,Pint,Pint)", "SpiUSD", lpiUSD);
/*  4376 */       Trace.data(this, "spiUnsubscribe(Hconn,LpiUSD,Pint,Pint)", "CompCode", pCompCode);
/*  4377 */       Trace.data(this, "spiUnsubscribe(Hconn,LpiUSD,Pint,Pint)", "Reason", pReason);
/*       */       
/*  4379 */       Trace.data(this, "spiUnsubscribe(Hconn,LpiUSD,Pint,Pint)", "__________");
/*       */     } 
/*       */     
/*  4382 */     if (Trace.isOn) {
/*  4383 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiUnsubscribe(Hconn,LpiUSD,Pint,Pint)", 2);
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void spiSyncPoint(Hconn hconn, SpiSyncPointOptions pSpo, Pint pCompCode, Pint pReason) {
/*  4409 */     if (Trace.isOn) {
/*  4410 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiSyncPoint(Hconn,SpiSyncPointOptions,Pint,Pint)", new Object[] { hconn, pSpo, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */ 
/*       */     
/*  4415 */     RemoteHconn remoteHconn = null;
/*  4416 */     RfpMQAPI rMQAPI = null;
/*       */     
/*  4418 */     RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  4419 */     JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/*  4420 */     jmqiTls.lastException = null;
/*       */     try {
/*  4422 */       remoteHconn = getRemoteHconn(tls, hconn);
/*  4423 */       RemoteSession remoteSession = null;
/*       */ 
/*       */       
/*  4426 */       remoteHconn.enterCall(tls.isDispatchThread, false);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*       */       try {
/*  4435 */         remoteSession = remoteHconn.getSession();
/*  4436 */         RfpVerbArray verbArray = remoteSession.getVerbArray(5, pCompCode, pReason, true);
/*  4437 */         if (pCompCode.x != 0) {
/*       */           
/*  4439 */           if (Trace.isOn) {
/*  4440 */             Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiSyncPoint(Hconn,SpiSyncPointOptions,Pint,Pint)", 1);
/*       */           }
/*       */           
/*       */           return;
/*       */         } 
/*       */         
/*  4446 */         remoteSession.checkIfDisconnected();
/*       */         
/*  4448 */         boolean swap = remoteSession.isSwap();
/*       */ 
/*       */         
/*  4451 */         RfpMQAPI sMQAPI = null;
/*  4452 */         sMQAPI = remoteSession.allocateMQAPI(140);
/*       */         
/*  4454 */         int inSpiOffset = sMQAPI.getRfpOffset() + sMQAPI.hdrSize();
/*  4455 */         byte[] commsBuffer = sMQAPI.getRfpBuffer();
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  4463 */         RfpINSPI inSpi = RfpINSPI.getInstance((JmqiEnvironment)this.sysenv, remoteSession, commsBuffer, inSpiOffset);
/*       */         
/*  4465 */         int spiInOutOffset = inSpiOffset + inSpi.getStructSize();
/*  4466 */         RfpSYNCPOINTSPIINOUT spiInOut = RfpSYNCPOINTSPIINOUT.getInstance(this.env, remoteSession, commsBuffer, spiInOutOffset, verbArray
/*  4467 */             .getMaxInoutVersion());
/*       */         
/*  4469 */         int spiInOffset = spiInOutOffset + spiInOut.getStructSize();
/*  4470 */         RfpSYNCPOINTSPIIN spiIn = RfpSYNCPOINTSPIIN.getInstance(this.env, remoteSession, commsBuffer, spiInOffset, verbArray
/*  4471 */             .getMaxInVersion());
/*       */         
/*  4473 */         RfpSYNCPOINTSPIOUT spiOut = RfpSYNCPOINTSPIOUT.getInstance(this.env, remoteSession, commsBuffer, 0, verbArray
/*  4474 */             .getMaxOutVersion());
/*  4475 */         spiOut.setDefaultVersion(swap);
/*       */ 
/*       */         
/*  4478 */         inSpi.setVerbId(5, swap);
/*  4479 */         inSpi.setOutStructVersion(spiOut.getVersion(swap), swap);
/*  4480 */         inSpi.setOutStructLength(spiOut.getStructSize(), swap);
/*       */ 
/*       */         
/*  4483 */         spiInOut.initEyecatcher();
/*  4484 */         spiInOut.setDefaultVersion(swap);
/*  4485 */         spiInOut.setLength(spiInOut.getStructSize(), swap);
/*       */ 
/*       */         
/*  4488 */         spiIn.initEyecatcher();
/*  4489 */         spiIn.setDefaultVersion(swap);
/*  4490 */         spiIn.setLength(spiIn.getStructSize(), swap);
/*  4491 */         spiIn.setOptions(0, swap);
/*  4492 */         spiIn.setAction(6, swap);
/*       */         
/*  4494 */         int transLength = sMQAPI.hdrSize() + inSpi.getStructSize() + spiInOut.getStructSize() + spiIn.getStructSize();
/*       */ 
/*       */ 
/*       */         
/*  4498 */         sMQAPI.setCallLength(transLength);
/*       */ 
/*       */         
/*  4501 */         sMQAPI.setTransLength(transLength);
/*       */         
/*  4503 */         sMQAPI.setControlFlags1(48);
/*       */ 
/*       */         
/*  4506 */         remoteSession.sendTSH(tls, (RfpTSH)sMQAPI);
/*       */ 
/*       */         
/*  4509 */         rMQAPI = remoteSession.receiveMQIFlow(tls);
/*       */         
/*  4511 */         if (rMQAPI.getSegmentType() != 156 || (rMQAPI.getControlFlags1() & 0x20) == 0) {
/*  4512 */           HashMap<String, Object> info = new HashMap<>();
/*  4513 */           info.put("SegmentType", Integer.valueOf(rMQAPI.getSegmentType()));
/*  4514 */           info.put("ControlFlags1", Integer.valueOf(rMQAPI.getControlFlags1()));
/*  4515 */           info.put("Description", "Unexpected flow received from server");
/*  4516 */           Trace.ffst(this, "spiSyncPoint(Hconn,SpiSyncPointOptions,Pint,Pint)", "01", info, null);
/*  4517 */           JmqiException e = new JmqiException(this.env, -1, null, 2, 2195, null);
/*       */ 
/*       */           
/*  4520 */           if (Trace.isOn) {
/*  4521 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiSyncPoint(Hconn,SpiSyncPointOptions,Pint,Pint)", (Throwable)e);
/*       */           }
/*       */           
/*  4524 */           throw e;
/*       */         } 
/*       */ 
/*       */         
/*  4528 */         pCompCode.x = rMQAPI.getCompCode(remoteSession.isSwap());
/*  4529 */         pReason.x = rMQAPI.getReasonCode(remoteSession.isSwap());
/*       */       }
/*       */       finally {
/*       */         
/*  4533 */         if (Trace.isOn) {
/*  4534 */           Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiSyncPoint(Hconn,SpiSyncPointOptions,Pint,Pint)");
/*       */         }
/*       */ 
/*       */ 
/*       */         
/*       */         try {
/*  4540 */           if (remoteHconn != null) {
/*  4541 */             if (rMQAPI != null) {
/*       */               try {
/*  4543 */                 remoteSession.releaseReceivedTSH((RfpTSH)rMQAPI);
/*       */               }
/*  4545 */               catch (JmqiException e) {
/*  4546 */                 if (Trace.isOn) {
/*  4547 */                   Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiSyncPoint(Hconn,SpiSyncPointOptions,Pint,Pint)", (Throwable)e, 1);
/*       */                 }
/*       */               } 
/*       */             }
/*       */ 
/*       */ 
/*       */             
/*  4554 */             remoteHconn.leaveCall(pReason.x);
/*       */           }
/*       */         
/*  4557 */         } catch (JmqiException e) {
/*  4558 */           if (Trace.isOn) {
/*  4559 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiSyncPoint(Hconn,SpiSyncPointOptions,Pint,Pint)", (Throwable)e, 2);
/*       */           }
/*       */         }
/*       */       
/*       */       }
/*       */     
/*  4565 */     } catch (JmqiException e) {
/*  4566 */       if (Trace.isOn) {
/*  4567 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiSyncPoint(Hconn,SpiSyncPointOptions,Pint,Pint)", (Throwable)e, 3);
/*       */       }
/*       */ 
/*       */ 
/*       */       
/*  4572 */       jmqiTls.lastException = e;
/*  4573 */       pCompCode.x = e.getCompCode();
/*  4574 */       pReason.x = e.getReason();
/*       */     } 
/*       */     
/*  4577 */     if (Trace.isOn) {
/*  4578 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiSyncPoint(Hconn,SpiSyncPointOptions,Pint,Pint)", 2);
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void spiActivateMessage(Hconn hconn, SpiActivate pActivateOpts, Pint pCompCode, Pint pReason) {
/*  4598 */     if (Trace.isOn) {
/*  4599 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiActivateMessage(Hconn,SpiActivate,Pint,Pint)", new Object[] { hconn, pActivateOpts, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */     
/*  4603 */     RemoteHconn remoteHconn = null;
/*  4604 */     RemoteSession remoteSession = null;
/*  4605 */     RfpMQAPI rMQAPI = null;
/*       */     
/*  4607 */     RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  4608 */     JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/*  4609 */     jmqiTls.lastException = null;
/*       */     try {
/*  4611 */       remoteHconn = getRemoteHconn(tls, hconn);
/*       */       
/*  4613 */       if (pActivateOpts == null) {
/*  4614 */         JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*       */ 
/*       */         
/*  4617 */         if (Trace.isOn) {
/*  4618 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiActivateMessage(Hconn,SpiActivate,Pint,Pint)", (Throwable)traceRet1, 1);
/*       */         }
/*       */         
/*  4621 */         throw traceRet1;
/*       */       } 
/*       */       
/*  4624 */       byte[] msgId = pActivateOpts.getMsgId();
/*  4625 */       int options = pActivateOpts.getOptions();
/*       */ 
/*       */       
/*  4628 */       if (msgId == null || msgId.length != 24) {
/*  4629 */         JmqiException traceRet2 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*       */         
/*  4631 */         if (Trace.isOn) {
/*  4632 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiActivateMessage(Hconn,SpiActivate,Pint,Pint)", (Throwable)traceRet2, 2);
/*       */         }
/*       */         
/*  4635 */         throw traceRet2;
/*       */       } 
/*       */ 
/*       */       
/*  4639 */       remoteHconn.enterCall(tls.isDispatchThread, false);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*       */       try {
/*  4647 */         remoteSession = remoteHconn.getSession();
/*       */         
/*  4649 */         remoteSession.checkIfDisconnected();
/*       */         
/*  4651 */         RfpVerbArray verbArray = remoteSession.getVerbArray(4, pCompCode, pReason, false);
/*  4652 */         if (pCompCode.x != 0) {
/*       */           
/*  4654 */           if (Trace.isOn) {
/*  4655 */             Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiActivateMessage(Hconn,SpiActivate,Pint,Pint)", 1);
/*       */           }
/*       */           
/*       */           return;
/*       */         } 
/*       */         
/*  4661 */         JmqiCodepage cp = remoteSession.getCp();
/*  4662 */         boolean swap = remoteSession.isSwap();
/*       */ 
/*       */         
/*  4665 */         RfpMQAPI sMQAPI = null;
/*  4666 */         sMQAPI = remoteSession.allocateMQAPI(140);
/*       */         
/*  4668 */         int inSpiOffset = sMQAPI.getRfpOffset() + sMQAPI.hdrSize();
/*  4669 */         byte[] commsBuffer = sMQAPI.getRfpBuffer();
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  4677 */         RfpINSPI inSpi = RfpINSPI.getInstance((JmqiEnvironment)this.sysenv, remoteSession, commsBuffer, inSpiOffset);
/*       */         
/*  4679 */         int spiInOutOffset = inSpiOffset + inSpi.getStructSize();
/*  4680 */         RfpACTIVATESPIINOUT spiInOut = RfpACTIVATESPIINOUT.getInstance(this.env, remoteSession, commsBuffer, spiInOutOffset, verbArray
/*  4681 */             .getMaxInoutVersion());
/*       */         
/*  4683 */         int spiInOffset = spiInOutOffset + spiInOut.getStructSize();
/*  4684 */         RfpACTIVATESPIIN spiIn = RfpACTIVATESPIIN.getInstance(this.env, remoteSession, commsBuffer, spiInOffset, verbArray
/*  4685 */             .getMaxInVersion());
/*       */         
/*  4687 */         RfpACTIVATESPIOUT spiOut = RfpACTIVATESPIOUT.getInstance(this.env, remoteSession, commsBuffer, 0, verbArray
/*  4688 */             .getMaxOutVersion());
/*  4689 */         spiOut.setDefaultVersion(swap);
/*       */ 
/*       */         
/*  4692 */         inSpi.setVerbId(4, swap);
/*  4693 */         inSpi.setOutStructVersion(spiOut.getVersion(swap), swap);
/*  4694 */         inSpi.setOutStructLength(spiOut.getStructSize(), swap);
/*       */ 
/*       */         
/*  4697 */         spiInOut.initEyecatcher();
/*  4698 */         spiInOut.setDefaultVersion(swap);
/*  4699 */         spiInOut.setLength(spiInOut.getStructSize(), swap);
/*       */ 
/*       */         
/*  4702 */         spiIn.initEyecatcher();
/*  4703 */         spiIn.setDefaultVersion(swap);
/*  4704 */         spiIn.setLength(spiIn.getStructSize(), swap);
/*  4705 */         spiIn.setOptions(options, swap);
/*       */ 
/*       */         
/*  4708 */         String emptyCHAR48 = "                                                ";
/*       */         
/*  4710 */         spiIn.setQName(emptyCHAR48, cp, jmqiTls);
/*  4711 */         spiIn.setQMgrName(emptyCHAR48, cp, jmqiTls);
/*       */ 
/*       */         
/*  4714 */         spiIn.setMsgId(msgId);
/*       */         
/*  4716 */         int transLength = sMQAPI.hdrSize() + inSpi.getStructSize() + spiInOut.getStructSize() + spiIn.getStructSize();
/*       */ 
/*       */ 
/*       */         
/*  4720 */         sMQAPI.setCallLength(transLength);
/*       */ 
/*       */         
/*  4723 */         sMQAPI.setTransLength(transLength);
/*       */         
/*  4725 */         sMQAPI.setControlFlags1(48);
/*       */ 
/*       */         
/*  4728 */         remoteSession.sendTSH(tls, (RfpTSH)sMQAPI);
/*       */ 
/*       */         
/*  4731 */         rMQAPI = remoteSession.receiveMQIFlow(tls);
/*       */         
/*  4733 */         if (rMQAPI.getSegmentType() != 156 || (rMQAPI.getControlFlags1() & 0x20) == 0) {
/*  4734 */           HashMap<String, Object> info = new HashMap<>();
/*  4735 */           info.put("SegmentType", Integer.valueOf(rMQAPI.getSegmentType()));
/*  4736 */           info.put("ControlFlags1", Integer.valueOf(rMQAPI.getControlFlags1()));
/*  4737 */           info.put("Description", "Unexpected flow received from server");
/*  4738 */           Trace.ffst(this, "spiActivateMessage(Hconn,SpiActivate,Pint,Pint)", "01", info, null);
/*  4739 */           JmqiException e = new JmqiException(this.env, -1, null, 2, 2195, null);
/*       */           
/*  4741 */           if (Trace.isOn) {
/*  4742 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiActivateMessage(Hconn,SpiActivate,Pint,Pint)", (Throwable)e, 3);
/*       */           }
/*       */           
/*  4745 */           throw e;
/*       */         } 
/*       */ 
/*       */         
/*  4749 */         pCompCode.x = rMQAPI.getCompCode(remoteSession.isSwap());
/*  4750 */         pReason.x = rMQAPI.getReasonCode(remoteSession.isSwap());
/*       */       }
/*       */       finally {
/*       */         
/*  4754 */         if (Trace.isOn) {
/*  4755 */           Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiActivateMessage(Hconn,SpiActivate,Pint,Pint)");
/*       */         }
/*       */ 
/*       */ 
/*       */         
/*       */         try {
/*  4761 */           if (remoteHconn != null) {
/*  4762 */             if (rMQAPI != null) {
/*       */               try {
/*  4764 */                 remoteSession.releaseReceivedTSH((RfpTSH)rMQAPI);
/*       */               }
/*  4766 */               catch (JmqiException e) {
/*  4767 */                 if (Trace.isOn) {
/*  4768 */                   Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiActivateMessage(Hconn,SpiActivate,Pint,Pint)", (Throwable)e, 1);
/*       */                 }
/*       */               } 
/*       */             }
/*       */ 
/*       */ 
/*       */             
/*  4775 */             remoteHconn.leaveCall(pReason.x);
/*       */           }
/*       */         
/*  4778 */         } catch (JmqiException e) {
/*  4779 */           if (Trace.isOn) {
/*  4780 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiActivateMessage(Hconn,SpiActivate,Pint,Pint)", (Throwable)e, 2);
/*       */           }
/*       */         }
/*       */       
/*       */       }
/*       */     
/*  4786 */     } catch (JmqiException e) {
/*  4787 */       if (Trace.isOn) {
/*  4788 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiActivateMessage(Hconn,SpiActivate,Pint,Pint)", (Throwable)e, 3);
/*       */       }
/*       */ 
/*       */ 
/*       */       
/*  4793 */       jmqiTls.lastException = e;
/*  4794 */       pCompCode.x = e.getCompCode();
/*  4795 */       pReason.x = e.getReason();
/*       */     } 
/*       */     
/*  4798 */     if (Trace.isOn) {
/*  4799 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiActivateMessage(Hconn,SpiActivate,Pint,Pint)", 2);
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void spiOpen(Hconn hconn, MQOD od, SpiOpenOptions options, Phobj phobj, Pint pCompCode, Pint pReason) {
/*  4818 */     if (Trace.isOn) {
/*  4819 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint)", new Object[] { hconn, od, options, phobj, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  4825 */     if (Trace.isOn) {
/*  4826 */       Trace.data(this, "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint)", "__________");
/*  4827 */       Trace.data(this, "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint)", "spiOpen >>");
/*  4828 */       Trace.data(this, "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint)", "Hconn", hconn);
/*  4829 */       Trace.data(this, "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint)", "MQOD", od);
/*  4830 */       Trace.data(this, "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint)", "SpiOpenOptions", options);
/*       */       
/*  4832 */       Trace.data(this, "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint)", "Hobj", phobj);
/*  4833 */       Trace.data(this, "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint)", "CompCode", pCompCode);
/*       */       
/*  4835 */       Trace.data(this, "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint)", "Reason", pReason);
/*  4836 */       Trace.data(this, "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint)", "__________");
/*       */     } 
/*  4838 */     RemoteSession session = spiOpen(hconn, od, options, phobj, pCompCode, pReason, null);
/*  4839 */     RemoteHconn remoteHconn = (RemoteHconn)hconn;
/*  4840 */     if (0 != pReason.x && remoteHconn.isReconnectable() && 
/*  4841 */       !threadIsReconnectThread() && 
/*  4842 */       RemoteHconn.isReconnectableReasonCode(pReason.x) && 
/*  4843 */       !remoteHconn.hasFailed()) {
/*       */       try {
/*  4845 */         remoteHconn.initiateReconnection(session);
/*  4846 */         if (od.getObjectQMgrName() != null && od.getObjectQMgrName().trim().length() != 0) {
/*  4847 */           od.setObjectQMgrName(remoteHconn.getQmName());
/*       */         }
/*  4849 */         spiOpen(hconn, od, options, phobj, pCompCode, pReason);
/*       */       }
/*  4851 */       catch (JmqiException je) {
/*  4852 */         if (Trace.isOn) {
/*  4853 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint)", (Throwable)je);
/*       */         }
/*       */       } 
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  4864 */     if (remoteHconn.hasFailed()) {
/*  4865 */       pCompCode.x = remoteHconn.getReconnectionFailureCompCode();
/*  4866 */       pReason.x = remoteHconn.getReconnectionFailureReason();
/*  4867 */       RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  4868 */       JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/*  4869 */       jmqiTls.lastException = remoteHconn.getReconnectionFailureException();
/*       */     } 
/*       */     
/*  4872 */     if (Trace.isOn) {
/*  4873 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public RemoteSession spiOpen(Hconn hconn, MQOD od, SpiOpenOptions spiOpenOptions, Phobj phobj, Pint pCompCode, Pint pReason, RemoteHobj reconnectHobj) {
/*  4893 */     if (Trace.isOn) {
/*  4894 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint,RemoteHobj)", new Object[] { hconn, od, spiOpenOptions, phobj, pCompCode, pReason, reconnectHobj });
/*       */     }
/*       */ 
/*       */ 
/*       */     
/*  4899 */     boolean reconnecting = (reconnectHobj != null);
/*  4900 */     RemoteHobj remoteHobj = reconnectHobj;
/*  4901 */     RemoteHconn remoteHconn = null;
/*  4902 */     RemoteSession remoteSession = null;
/*  4903 */     RfpMQAPI rMQAPI = null;
/*  4904 */     RemoteProxyQueue proxyQueue = null;
/*       */     
/*  4906 */     RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  4907 */     JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/*  4908 */     jmqiTls.lastException = null;
/*       */ 
/*       */     
/*  4911 */     if (od != null) {
/*  4912 */       od.getObjectString().setRemote(true);
/*  4913 */       od.getSelectionString().setRemote(true);
/*  4914 */       od.getResObjectString().setRemote(true);
/*       */     } 
/*       */     try {
/*  4917 */       remoteHconn = getRemoteHconn(tls, hconn);
/*       */       
/*  4919 */       if (od == null) {
/*  4920 */         JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2044, null);
/*       */ 
/*       */         
/*  4923 */         if (Trace.isOn) {
/*  4924 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint,RemoteHobj)", (Throwable)traceRet1, 1);
/*       */         }
/*       */ 
/*       */         
/*  4928 */         throw traceRet1;
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/*  4933 */       String originalObjectName = od.getObjectName();
/*       */ 
/*       */       
/*  4936 */       if (spiOpenOptions == null) {
/*  4937 */         JmqiException traceRet2 = new JmqiException(this.env, -1, null, 2, 2046, null);
/*       */         
/*  4939 */         if (Trace.isOn) {
/*  4940 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint,RemoteHobj)", (Throwable)traceRet2, 2);
/*       */         }
/*       */ 
/*       */         
/*  4944 */         throw traceRet2;
/*       */       } 
/*       */ 
/*       */       
/*  4948 */       if (phobj == null) {
/*  4949 */         JmqiException traceRet3 = new JmqiException(this.env, -1, null, 2, 2019, null);
/*       */         
/*  4951 */         if (Trace.isOn) {
/*  4952 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint,RemoteHobj)", (Throwable)traceRet3, 3);
/*       */         }
/*       */ 
/*       */         
/*  4956 */         throw traceRet3;
/*       */       } 
/*       */ 
/*       */       
/*  4960 */       remoteHconn.enterCall(tls.isDispatchThread, false);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*       */       try {
/*  4968 */         remoteSession = remoteHconn.getSession();
/*  4969 */         JmqiCodepage cp = remoteSession.getCp();
/*  4970 */         boolean swap = remoteSession.isSwap();
/*       */         
/*  4972 */         remoteSession.checkIfDisconnected();
/*       */         
/*  4974 */         proxyQueue = getProxyQueue(remoteSession, spiOpenOptions.getOptions(), reconnecting, remoteHobj, remoteHconn, tls);
/*       */ 
/*       */         
/*  4977 */         phobj.setHobj(MQConstants.jmqi_MQHO_UNUSABLE_HOBJ);
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  4982 */         if (od.getVersion() < 2 && remoteSession.isDistListCapable()) {
/*       */ 
/*       */ 
/*       */           
/*  4986 */           od.setVersion(2);
/*  4987 */           od.setRecsPresent(0);
/*  4988 */           od.setResponseRecords(null);
/*  4989 */           od.setKnownDestCount(0);
/*  4990 */           od.setUnknownDestCount(0);
/*  4991 */           od.setInvalidDestCount(0);
/*       */         } 
/*       */         
/*  4994 */         RfpVerbArray verbArray = remoteSession.getVerbArray(12, pCompCode, pReason, false);
/*  4995 */         if (pCompCode.x != 0) {
/*  4996 */           if (Trace.isOn) {
/*  4997 */             Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint,RemoteHobj)", remoteSession, 1);
/*       */           }
/*       */ 
/*       */           
/*  5001 */           return remoteSession;
/*       */         } 
/*       */ 
/*       */         
/*  5005 */         if (remoteHconn.getFapLevel() < 9) {
/*  5006 */           JmqiException traceRet4 = new JmqiException(this.env, -1, null, 2, 2298, null);
/*       */           
/*  5008 */           if (Trace.isOn) {
/*  5009 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint,RemoteHobj)", (Throwable)traceRet4, 4);
/*       */           }
/*       */ 
/*       */           
/*  5013 */           throw traceRet4;
/*       */         } 
/*       */ 
/*       */         
/*  5017 */         RfpMQAPI sMQAPI = null;
/*  5018 */         sMQAPI = remoteSession.allocateMQAPI(140);
/*       */         
/*  5020 */         sMQAPI.setHandle(phobj.getHobj().getIntegerHandle(), swap);
/*       */         
/*  5022 */         int inSpiOffset = sMQAPI.getRfpOffset() + sMQAPI.hdrSize();
/*  5023 */         byte[] commsBuffer = sMQAPI.getRfpBuffer();
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  5034 */         RfpINSPI inSpi = RfpINSPI.getInstance((JmqiEnvironment)this.sysenv, remoteSession, commsBuffer, inSpiOffset);
/*  5035 */         inSpi.setVerbId(12, swap);
/*       */ 
/*       */         
/*  5038 */         int spiInOutOffset = inSpiOffset + inSpi.getStructSize();
/*  5039 */         RfpOPENSPIINOUT spiInOut = RfpOPENSPIINOUT.getInstance(this.env, remoteSession, commsBuffer, spiInOutOffset, verbArray
/*  5040 */             .getMaxInoutVersion());
/*  5041 */         spiInOut.initEyecatcher();
/*  5042 */         spiInOut.setDefaultVersion(swap);
/*  5043 */         spiInOut.setLength(spiInOut.getStructSize(), swap);
/*       */ 
/*       */         
/*  5046 */         int maxInVersion = verbArray.getMaxInVersion();
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  5053 */         if (maxInVersion < 2 || remoteHconn.getQmgrSplCapability() != QmgrSplCapability.SUPPORTED)
/*       */         {
/*       */ 
/*       */           
/*  5057 */           if (maxInVersion >= 2) {
/*  5058 */             maxInVersion = 1;
/*       */           }
/*       */         }
/*       */ 
/*       */ 
/*       */         
/*  5064 */         int spiInOffset = spiInOutOffset + spiInOut.getStructSize();
/*  5065 */         RfpOPENSPIIN spiIn = RfpOPENSPIIN.getInstance(this.env, remoteSession, commsBuffer, spiInOffset, maxInVersion);
/*       */         
/*  5067 */         spiIn.initEyecatcher();
/*  5068 */         spiIn.setVersion(maxInVersion, swap);
/*       */         
/*  5070 */         int pos = spiIn.getOptionsOffset();
/*  5071 */         JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*  5072 */         pos += dc.writeString("LPOO", spiIn.getRfpBuffer(), pos, 4, cp, jmqiTls);
/*  5073 */         dc.writeI32(spiOpenOptions.getVersion(), spiIn.getRfpBuffer(), pos, swap);
/*  5074 */         pos += 4;
/*  5075 */         dc.writeI32(spiOpenOptions.getOptions(), spiIn.getRfpBuffer(), pos, swap);
/*  5076 */         pos += 4;
/*  5077 */         dc.writeI32(spiOpenOptions.getLpiOptions(), spiIn.getRfpBuffer(), pos, swap);
/*  5078 */         pos += 4;
/*  5079 */         dc.writeI32(spiOpenOptions.getDefPersistence(), spiIn.getRfpBuffer(), pos, swap);
/*  5080 */         pos += 4;
/*  5081 */         dc.writeI32(spiOpenOptions.getDefPutResponseType(), spiIn.getRfpBuffer(), pos, swap);
/*  5082 */         pos += 4;
/*  5083 */         dc.writeI32(spiOpenOptions.getDefReadAhead(), spiIn.getRfpBuffer(), pos, swap);
/*  5084 */         pos += 4;
/*  5085 */         dc.writeI32(spiOpenOptions.getPropertyControl(), spiIn.getRfpBuffer(), pos, swap);
/*  5086 */         pos += 4;
/*  5087 */         spiIn.setOptionsSize(32);
/*       */         
/*  5089 */         if (maxInVersion >= 2) {
/*       */ 
/*       */ 
/*       */           
/*  5093 */           ((JmqiSystemEnvironment)this.env).getDC().writeMQField("", spiIn.getRfpBuffer(), spiIn
/*  5094 */               .getPolicyErrorQueueOffset(), 48, cp, jmqiTls);
/*  5095 */           ((JmqiSystemEnvironment)this.env).getDC().writeI32(0, spiIn.getRfpBuffer(), spiIn
/*  5096 */               .getPolicyDataOffsetOffset(), swap);
/*       */ 
/*       */ 
/*       */           
/*  5100 */           if (remoteHconn.getFapLevel() >= 12 && remoteHconn.getQmgrSplCapability() == QmgrSplCapability.SUPPORTED) {
/*  5101 */             ((JmqiSystemEnvironment)this.env).getDC().writeI32(10240, spiIn.getRfpBuffer(), spiIn
/*  5102 */                 .getPolicyDataLengthOffset(), swap);
/*       */           } else {
/*       */             
/*  5105 */             ((JmqiSystemEnvironment)this.env).getDC().writeI32(0, spiIn.getRfpBuffer(), spiIn
/*  5106 */                 .getPolicyDataLengthOffset(), swap);
/*       */           } 
/*       */         } 
/*  5109 */         int endOfOd = od.writeToBuffer(spiIn.getRfpBuffer(), spiIn.getOdOffset(), 4, swap, cp, jmqiTls);
/*       */         
/*  5111 */         spiIn.setOdSize(endOfOd - spiIn.getOdOffset());
/*  5112 */         spiIn.setLength(spiIn.getStructSize(), swap);
/*       */ 
/*       */         
/*  5115 */         int spiOutRelativeOffset = spiInOffset - sMQAPI.getRfpOffset();
/*  5116 */         int maxOutVersion = verbArray.getMaxOutVersion();
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  5124 */         if (maxOutVersion < 2 || remoteHconn.getQmgrSplCapability() != QmgrSplCapability.SUPPORTED)
/*       */         {
/*       */ 
/*       */           
/*  5128 */           if (maxOutVersion >= 2) {
/*  5129 */             maxOutVersion = 1;
/*       */           }
/*       */         }
/*       */ 
/*       */ 
/*       */         
/*  5135 */         RfpOPENSPIOUT spiOut = RfpOPENSPIOUT.getInstance(this.env, remoteSession, commsBuffer, 0, maxOutVersion, 
/*  5136 */             SpiOpenOptions.getSizeV1(4), od.getRequiredInputBufferSize(4, cp));
/*  5137 */         spiOut.setDefaultVersion(swap);
/*       */ 
/*       */         
/*  5140 */         inSpi.setOutStructVersion(spiOut.getVersion(swap), swap);
/*  5141 */         inSpi.setOutStructLength(spiOut.getStructSize(), swap);
/*       */ 
/*       */         
/*  5144 */         sMQAPI.setControlFlags1(48);
/*       */         
/*  5146 */         int transLength = sMQAPI.hdrSize() + inSpi.getStructSize() + spiInOut.getStructSize() + spiIn.getStructSize();
/*       */ 
/*       */         
/*  5149 */         sMQAPI.setCallLength(transLength + od
/*  5150 */             .getResObjectString().getVsBufSize() + od.getSelectionString().getVsBufSize());
/*       */ 
/*       */         
/*  5153 */         sMQAPI.setTransLength(transLength);
/*       */ 
/*       */         
/*  5156 */         remoteSession.sendTSH(tls, (RfpTSH)sMQAPI);
/*       */ 
/*       */         
/*  5159 */         rMQAPI = remoteSession.receiveMQIFlow(tls);
/*       */         
/*  5161 */         if (rMQAPI.getSegmentType() != 156 || (rMQAPI.getControlFlags1() & 0x20) == 0) {
/*  5162 */           HashMap<String, Object> info = new HashMap<>();
/*  5163 */           info.put("SegmentType", Integer.valueOf(rMQAPI.getSegmentType()));
/*  5164 */           info.put("ControlFlags1", Integer.valueOf(rMQAPI.getControlFlags1()));
/*  5165 */           info.put("Description", "Unexpected flow received from server");
/*  5166 */           Trace.ffst(this, "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint,RemoteHobj)", "01", info, null);
/*       */           
/*  5168 */           JmqiException traceRet5 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*       */           
/*  5170 */           if (Trace.isOn) {
/*  5171 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint,RemoteHobj)", (Throwable)traceRet5, 5);
/*       */           }
/*       */ 
/*       */           
/*  5175 */           throw traceRet5;
/*       */         } 
/*       */ 
/*       */ 
/*       */         
/*  5180 */         swap = remoteSession.isSwap();
/*       */ 
/*       */         
/*  5183 */         int ccsid = rMQAPI.getCcsid(swap);
/*       */         try {
/*  5185 */           cp = JmqiCodepage.getJmqiCodepage((JmqiEnvironment)this.sysenv, ccsid);
/*       */           
/*  5187 */           if (cp == null)
/*       */           {
/*  5189 */             UnsupportedEncodingException traceRet7 = new UnsupportedEncodingException(Integer.toString(ccsid));
/*  5190 */             if (Trace.isOn) {
/*  5191 */               Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint,RemoteHobj)", traceRet7, 6);
/*       */             }
/*       */ 
/*       */             
/*  5195 */             throw traceRet7;
/*       */           }
/*       */         
/*       */         }
/*  5199 */         catch (UnsupportedEncodingException e) {
/*  5200 */           if (Trace.isOn) {
/*  5201 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint,RemoteHobj)", e, 1);
/*       */           }
/*       */ 
/*       */ 
/*       */           
/*  5206 */           JmqiException traceRet1 = new JmqiException(this.env, 6047, new String[] { String.valueOf(ccsid), null, null, null, "???" }, 2, 2195, e);
/*       */           
/*  5208 */           if (Trace.isOn) {
/*  5209 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint,RemoteHobj)", (Throwable)traceRet1, 7);
/*       */           }
/*       */ 
/*       */           
/*  5213 */           throw traceRet1;
/*       */         } 
/*       */ 
/*       */         
/*  5217 */         pCompCode.x = rMQAPI.getCompCode(swap);
/*  5218 */         pReason.x = rMQAPI.getReasonCode(swap);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  5224 */         if (pCompCode.x != 2) {
/*  5225 */           commsBuffer = rMQAPI.getRfpBuffer();
/*       */ 
/*       */           
/*  5228 */           spiOut.setRfpBuffer(commsBuffer);
/*  5229 */           spiOut.setRfpOffset(rMQAPI.getRfpOffset() + spiOutRelativeOffset);
/*  5230 */           if (!spiOut.checkID()) {
/*  5231 */             HashMap<String, Object> info = new HashMap<>();
/*  5232 */             info.put("SegmentType", Integer.valueOf(rMQAPI.getSegmentType()));
/*  5233 */             info.put("ControlFlags1", Integer.valueOf(rMQAPI.getControlFlags1()));
/*  5234 */             info.put("Description", "Unexpected flow received from server");
/*  5235 */             Trace.ffst(this, "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint,RemoteHobj)", "02", info, null);
/*       */ 
/*       */             
/*  5238 */             JmqiException traceRet6 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*       */             
/*  5240 */             if (Trace.isOn) {
/*  5241 */               Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint,RemoteHobj)", (Throwable)traceRet6, 8);
/*       */             }
/*       */ 
/*       */             
/*  5245 */             throw traceRet6;
/*       */           } 
/*       */           
/*  5248 */           pos = spiOut.getOptionsOffset();
/*  5249 */           pos += 4;
/*  5250 */           spiOpenOptions.setVersion(dc.readI32(spiOut.getRfpBuffer(), pos, swap));
/*  5251 */           pos += 4;
/*  5252 */           spiOpenOptions.setOptions(dc.readI32(spiOut.getRfpBuffer(), pos, swap));
/*  5253 */           pos += 4;
/*  5254 */           spiOpenOptions.setLpiOptions(dc.readI32(spiOut.getRfpBuffer(), pos, swap));
/*  5255 */           pos += 4;
/*  5256 */           spiOpenOptions.setDefPersistence(dc.readI32(spiOut.getRfpBuffer(), pos, swap));
/*  5257 */           pos += 4;
/*  5258 */           spiOpenOptions.setDefPutResponseType(dc.readI32(spiOut.getRfpBuffer(), pos, swap));
/*  5259 */           pos += 4;
/*  5260 */           spiOpenOptions.setDefReadAhead(dc.readI32(spiOut.getRfpBuffer(), pos, swap));
/*  5261 */           pos += 4;
/*  5262 */           spiOpenOptions.setPropertyControl(dc.readI32(spiOut.getRfpBuffer(), pos, swap));
/*  5263 */           pos += 4;
/*       */           
/*  5265 */           int policyErrorQueueOffset = -1;
/*  5266 */           int policyOffsetOffset = -1;
/*  5267 */           int policyOffset = -1;
/*  5268 */           int policyLengthOffset = -1;
/*  5269 */           int policyLength = -1;
/*  5270 */           String readAmsErrorQueue = null;
/*       */ 
/*       */           
/*  5273 */           if (maxOutVersion >= 2 && remoteSession.getFapLevel() >= 12 && remoteHconn.getQmgrSplCapability() == QmgrSplCapability.SUPPORTED) {
/*       */ 
/*       */             
/*  5276 */             policyErrorQueueOffset = spiOut.getPolicyErrorQueueOffset();
/*  5277 */             policyOffsetOffset = spiOut.getPolicyDataOffsetOffset();
/*  5278 */             policyOffset = dc.readI32(spiOut.getRfpBuffer(), policyOffsetOffset, swap);
/*  5279 */             policyLengthOffset = spiOut.getPolicyDataLengthOffset();
/*  5280 */             policyLength = dc.readI32(spiOut.getRfpBuffer(), policyLengthOffset, swap);
/*  5281 */             readAmsErrorQueue = dc.readMQField(spiOut.getRfpBuffer(), policyErrorQueueOffset, 48, cp, jmqiTls);
/*       */           } 
/*       */ 
/*       */           
/*  5285 */           od.readFromBuffer(spiOut.getRfpBuffer(), spiOut.getOdOffset(), 4, swap, cp, jmqiTls);
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  5290 */           int defPersistence = -1;
/*  5291 */           int defPutResponseType = -1;
/*  5292 */           int defReadAhead = -1;
/*       */ 
/*       */           
/*  5295 */           defPersistence = spiOpenOptions.getDefPersistence();
/*  5296 */           defPutResponseType = spiOpenOptions.getDefPutResponseType();
/*  5297 */           defReadAhead = spiOpenOptions.getDefReadAhead();
/*       */ 
/*       */           
/*  5300 */           int hobjHandle = rMQAPI.getHandle(swap);
/*  5301 */           String objectName = od.getObjectName();
/*  5302 */           int objectType = od.getObjectType();
/*  5303 */           if (reconnecting) {
/*  5304 */             remoteHobj.updateHandle(hobjHandle, remoteSession);
/*       */           
/*       */           }
/*       */           else {
/*       */ 
/*       */             
/*  5310 */             remoteHobj = new RemoteHobj(this.env, hobjHandle, proxyQueue, objectName, objectType, spiOpenOptions.getOptions(), spiOpenOptions, defPersistence, defPutResponseType, defReadAhead, od, remoteSession);
/*       */           } 
/*       */ 
/*       */           
/*  5314 */           phobj.setHobj(remoteHobj);
/*  5315 */           remoteHconn.addHobj(remoteHobj);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  5323 */           if (remoteHconn.isReconnectable() && !reconnecting) {
/*  5324 */             remoteHobj.setOriginalObjectName(originalObjectName);
/*       */           }
/*       */ 
/*       */ 
/*       */           
/*  5329 */           if (proxyQueue != null) {
/*  5330 */             remoteHconn.getProxyQueueManager().setIdentifier(tls, proxyQueue, remoteHobj);
/*  5331 */             proxyQueue.setHconn(remoteHconn);
/*       */           } 
/*       */ 
/*       */ 
/*       */           
/*  5336 */           if (policyLength > 0 && readAmsErrorQueue != null && spiOut.getRfpOffset() + policyOffset + policyLength <= (spiOut.getRfpBuffer()).length) {
/*  5337 */             byte[] policyBytes = new byte[policyLength];
/*  5338 */             System.arraycopy(spiOut.getRfpBuffer(), spiOut.getRfpOffset() + policyOffset, policyBytes, 0, policyLength);
/*       */ 
/*       */             
/*  5341 */             remoteHobj.setAMSPolicy(policyBytes);
/*  5342 */             remoteHobj.setAMSErrorQueue(readAmsErrorQueue);
/*       */           } 
/*       */         } 
/*       */       } finally {
/*       */         
/*  5347 */         if (Trace.isOn) {
/*  5348 */           Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint,RemoteHobj)");
/*       */         }
/*       */         
/*  5351 */         if (od != null) {
/*  5352 */           od.getObjectString().setRemote(false);
/*  5353 */           od.getSelectionString().setRemote(false);
/*  5354 */           od.getResObjectString().setRemote(false);
/*       */         } 
/*       */ 
/*       */ 
/*       */         
/*       */         try {
/*  5360 */           if (remoteHconn != null) {
/*  5361 */             if (rMQAPI != null) {
/*       */               try {
/*  5363 */                 remoteSession.releaseReceivedTSH((RfpTSH)rMQAPI);
/*       */               }
/*  5365 */               catch (JmqiException e) {
/*  5366 */                 if (Trace.isOn) {
/*  5367 */                   Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint,RemoteHobj)", (Throwable)e, 2);
/*       */                 }
/*       */               } 
/*       */             }
/*       */ 
/*       */ 
/*       */ 
/*       */             
/*  5375 */             remoteHconn.leaveCall(pReason.x);
/*       */           }
/*       */         
/*  5378 */         } catch (JmqiException e) {
/*  5379 */           if (Trace.isOn) {
/*  5380 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint,RemoteHobj)", (Throwable)e, 3);
/*       */           }
/*       */         }
/*       */       
/*       */       }
/*       */     
/*       */     }
/*  5387 */     catch (JmqiException e) {
/*  5388 */       if (Trace.isOn) {
/*  5389 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint,RemoteHobj)", (Throwable)e, 4);
/*       */       }
/*       */ 
/*       */       
/*  5393 */       jmqiTls.lastException = e;
/*  5394 */       pCompCode.x = e.getCompCode();
/*  5395 */       pReason.x = e.getReason();
/*       */     } 
/*       */     
/*  5398 */     if (Trace.isOn) {
/*  5399 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint,RemoteHobj)", remoteSession, 2);
/*       */     }
/*       */ 
/*       */     
/*  5403 */     return remoteSession;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void spiPut(Hconn hconn, Hobj hobj, MQMD pMqmd, MQPMO pMqpmo, SpiPutOptions spiPutOptions, int bufferLength, ByteBuffer pByteBuffer, Pint pCompCode, Pint pReason) {
/*  5431 */     if (Trace.isOn) {
/*  5432 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,int,ByteBuffer,Pint,Pint)", new Object[] { hconn, hobj, pMqmd, pMqpmo, spiPutOptions, 
/*       */             
/*  5434 */             Integer.valueOf(bufferLength), pByteBuffer, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */     
/*  5438 */     if (pByteBuffer == null) {
/*  5439 */       NullPointerException npe = new NullPointerException();
/*  5440 */       if (Trace.isOn) {
/*  5441 */         Trace.throwing("com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,int,ByteBuffer,Pint,Pint)", npe);
/*       */       }
/*       */       
/*  5444 */       if (Trace.isOn) {
/*  5445 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,int,ByteBuffer,Pint,Pint)", npe, 1);
/*       */       }
/*       */       
/*  5448 */       throw npe;
/*       */     } 
/*       */ 
/*       */     
/*  5452 */     RfpMQAPI rMQAPI = null;
/*  5453 */     RemoteHconn remoteHconn = null;
/*       */     
/*  5455 */     RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  5456 */     JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/*  5457 */     jmqiTls.lastException = null;
/*       */     try {
/*  5459 */       remoteHconn = getRemoteHconn(tls, hconn);
/*  5460 */       RemoteSession remoteSession = null;
/*       */ 
/*       */ 
/*       */       
/*  5464 */       if (remoteHconn.isReconnectable() && (
/*  5465 */         pMqpmo.getOptions() & 0x8000) != 0) {
/*  5466 */         JmqiException je = new JmqiException(this.env, -1, null, 2, 2547, null);
/*       */ 
/*       */         
/*  5469 */         if (Trace.isOn) {
/*  5470 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,int,ByteBuffer,Pint,Pint)", (Throwable)je, 2);
/*       */         }
/*       */         
/*  5473 */         throw je;
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/*  5478 */       if (hobj == null || !(hobj instanceof RemoteHobj)) {
/*  5479 */         JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2019, null);
/*       */ 
/*       */         
/*  5482 */         if (Trace.isOn) {
/*  5483 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,int,ByteBuffer,Pint,Pint)", (Throwable)traceRet1, 3);
/*       */         }
/*       */         
/*  5486 */         throw traceRet1;
/*       */       } 
/*       */       
/*  5489 */       RemoteHobj remoteHobj = (RemoteHobj)hobj;
/*       */ 
/*       */       
/*  5492 */       if (pMqmd == null) {
/*  5493 */         JmqiException traceRet2 = new JmqiException(this.env, -1, null, 2, 2026, null);
/*       */         
/*  5495 */         if (Trace.isOn) {
/*  5496 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,int,ByteBuffer,Pint,Pint)", (Throwable)traceRet2, 4);
/*       */         }
/*       */         
/*  5499 */         throw traceRet2;
/*       */       } 
/*       */       
/*  5502 */       if (pMqpmo == null) {
/*  5503 */         JmqiException traceRet3 = new JmqiException(this.env, -1, null, 2, 2173, null);
/*       */         
/*  5505 */         if (Trace.isOn) {
/*  5506 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,int,ByteBuffer,Pint,Pint)", (Throwable)traceRet3, 5);
/*       */         }
/*       */         
/*  5509 */         throw traceRet3;
/*       */       } 
/*       */       
/*  5512 */       if ((pMqpmo.getOptions() & 0x4) == 0 && (pMqpmo.getOptions() & 0x2) == 0) {
/*  5513 */         pMqpmo.setOptions(pMqpmo.getOptions() | 0x4);
/*       */       }
/*       */       
/*  5516 */       byte[] pBuffer = pByteBuffer.array();
/*  5517 */       int spiOptions = spiPutOptions.getOptions();
/*       */       
/*  5519 */       if (bufferLength > pBuffer.length) {
/*  5520 */         JmqiException traceRet6 = new JmqiException(this.env, -1, null, 2, 2005, null);
/*       */         
/*  5522 */         if (Trace.isOn) {
/*  5523 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,int,ByteBuffer,Pint,Pint)", (Throwable)traceRet6, 7);
/*       */         }
/*       */         
/*  5526 */         throw traceRet6;
/*       */       } 
/*       */ 
/*       */       
/*  5530 */       int originalPutOpts = pMqpmo.getOptions();
/*       */ 
/*       */       
/*  5533 */       remoteHconn.enterCall(tls.isDispatchThread, false);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*       */       try {
/*  5541 */         remoteSession = remoteHconn.getSession();
/*       */         
/*  5543 */         remoteSession.checkIfDisconnected();
/*       */         
/*  5545 */         if (bufferLength > remoteSession.getMaximumMessageLength()) {
/*  5546 */           JmqiException traceRet5 = new JmqiException(this.env, -1, null, 2, 2218, null);
/*       */           
/*  5548 */           if (Trace.isOn) {
/*  5549 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,int,ByteBuffer,Pint,Pint)", (Throwable)traceRet5, 6);
/*       */           }
/*       */           
/*  5552 */           throw traceRet5;
/*       */         } 
/*       */         
/*  5555 */         RfpVerbArray verbArray = remoteSession.getVerbArray(2, pCompCode, pReason, false);
/*  5556 */         if (pCompCode.x != 0) {
/*       */           
/*  5558 */           if (Trace.isOn) {
/*  5559 */             Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,int,ByteBuffer,Pint,Pint)", 1);
/*       */           }
/*       */           
/*       */           return;
/*       */         } 
/*       */         
/*  5565 */         JmqiCodepage cp = remoteSession.getCp();
/*  5566 */         boolean swap = remoteSession.isSwap();
/*       */ 
/*       */         
/*  5569 */         RfpMQAPI sMQAPI = null;
/*  5570 */         sMQAPI = remoteSession.allocateMQAPI(140);
/*  5571 */         sMQAPI.setHandle(remoteHobj.getIntegerHandle(), swap);
/*       */         
/*  5573 */         int inSpiOffset = sMQAPI.getRfpOffset() + sMQAPI.hdrSize();
/*  5574 */         byte[] commsBuffer = sMQAPI.getRfpBuffer();
/*       */         
/*  5576 */         int pMqmdBufferSize = pMqmd.getRequiredBufferSize(4, cp);
/*  5577 */         int pMqpmoBufferSize = pMqpmo.getRequiredBufferSize(4, cp);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  5585 */         RfpINSPI inSpi = RfpINSPI.getInstance((JmqiEnvironment)this.sysenv, remoteSession, commsBuffer, inSpiOffset);
/*       */         
/*  5587 */         int spiInOutOffset = inSpiOffset + inSpi.getStructSize();
/*  5588 */         int spiInOutRelativeOffset = spiInOutOffset - sMQAPI.getRfpOffset();
/*  5589 */         RfpPUTSPIINOUT spiInOut = RfpPUTSPIINOUT.getInstance((JmqiEnvironment)this.sysenv, remoteSession, commsBuffer, spiInOutOffset, verbArray
/*  5590 */             .getMaxInoutVersion(), pMqmdBufferSize, pMqpmoBufferSize);
/*       */         
/*  5592 */         int spiInOffset = spiInOutOffset + spiInOut.getStructSize();
/*  5593 */         RfpPUTSPIIN spiIn = RfpPUTSPIIN.getInstance((JmqiEnvironment)this.sysenv, remoteSession, commsBuffer, spiInOffset, verbArray
/*  5594 */             .getMaxInVersion());
/*       */         
/*  5596 */         RfpPUTSPIOUT spiOut = RfpPUTSPIOUT.getInstance((JmqiEnvironment)this.sysenv, remoteSession, commsBuffer, 0, verbArray
/*  5597 */             .getMaxOutVersion());
/*  5598 */         spiOut.setDefaultVersion(swap);
/*       */ 
/*       */         
/*  5601 */         inSpi.setVerbId(2, swap);
/*  5602 */         inSpi.setOutStructVersion(spiOut.getVersion(swap), swap);
/*  5603 */         inSpi.setOutStructLength(spiOut.getStructSize(), swap);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  5610 */         spiInOut.initEyecatcher();
/*  5611 */         spiInOut.setDefaultVersion(swap);
/*  5612 */         spiInOut.setLength(spiInOut.getStructSize(), swap);
/*       */ 
/*       */         
/*  5615 */         boolean setId = ((originalPutOpts & 0xC00) != 0);
/*  5616 */         boolean setOrig = ((originalPutOpts & 0xA00) != 0);
/*       */         
/*  5618 */         pMqmd.writeToBuffer(spiInOut.getRfpBuffer(), spiInOut.getMsgDescOffset(), false, setId, setOrig, 4, swap, cp, jmqiTls);
/*       */ 
/*       */         
/*  5621 */         pMqpmo.writeToBuffer(spiInOut.getRfpBuffer(), spiInOut.getPutMsgOptsOffset(), 4, swap, cp, jmqiTls);
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  5626 */         spiIn.initEyecatcher();
/*  5627 */         spiIn.setDefaultVersion(swap);
/*  5628 */         spiIn.setLength(spiIn.getStructSize() + bufferLength, swap);
/*       */         
/*  5630 */         spiIn.setOptions(spiOptions, swap);
/*  5631 */         spiIn.setMessageLength(bufferLength, swap);
/*  5632 */         spiIn.setMsgIdReservation(0, swap);
/*  5633 */         spiIn.setDeliveryDelay(spiPutOptions.getDeliveryDelay(), swap);
/*       */         
/*  5635 */         int transLength = sMQAPI.hdrSize() + inSpi.getStructSize() + spiInOut.getStructSize() + spiIn.getStructSize();
/*       */ 
/*       */ 
/*       */         
/*  5639 */         sMQAPI.setCallLength(transLength + pBuffer.length);
/*       */ 
/*       */         
/*  5642 */         sMQAPI.setTransLength(transLength);
/*       */         
/*  5644 */         sMQAPI.setUserDataSingle(pBuffer, bufferLength);
/*       */         
/*  5646 */         remoteSession.sendTSH(tls, (RfpTSH)sMQAPI);
/*       */ 
/*       */         
/*  5649 */         rMQAPI = remoteSession.receiveMQIFlow(tls);
/*       */         
/*  5651 */         if (rMQAPI.getSegmentType() != 156 || (rMQAPI.getControlFlags1() & 0x20) == 0) {
/*  5652 */           HashMap<String, Object> info = new HashMap<>();
/*  5653 */           info.put("SegmentType", Integer.valueOf(rMQAPI.getSegmentType()));
/*  5654 */           info.put("ControlFlags1", Integer.valueOf(rMQAPI.getControlFlags1()));
/*  5655 */           info.put("Description", "Unexpected flow received from server");
/*  5656 */           Trace.ffst(this, "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,int,ByteBuffer,Pint,Pint)", "01", info, null);
/*       */           
/*  5658 */           JmqiException e = new JmqiException(this.env, -1, null, 2, 2195, null);
/*       */           
/*  5660 */           if (Trace.isOn) {
/*  5661 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,int,ByteBuffer,Pint,Pint)", (Throwable)e, 8);
/*       */           }
/*       */           
/*  5664 */           throw e;
/*       */         } 
/*       */ 
/*       */ 
/*       */         
/*  5669 */         swap = remoteSession.isSwap();
/*       */ 
/*       */         
/*  5672 */         pCompCode.x = rMQAPI.getCompCode(swap);
/*  5673 */         pReason.x = rMQAPI.getReasonCode(swap);
/*       */         
/*  5675 */         if (pCompCode.x != 2)
/*       */         {
/*       */           
/*  5678 */           spiInOut.setRfpBuffer(rMQAPI.getRfpBuffer());
/*  5679 */           spiInOut.setRfpOffset(rMQAPI.getRfpOffset() + spiInOutRelativeOffset);
/*  5680 */           if (!spiInOut.checkID()) {
/*       */ 
/*       */ 
/*       */ 
/*       */             
/*  5685 */             JmqiException traceRet7 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*       */             
/*  5687 */             if (Trace.isOn) {
/*  5688 */               Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,int,ByteBuffer,Pint,Pint)", (Throwable)traceRet7, 9);
/*       */             }
/*       */ 
/*       */             
/*  5692 */             throw traceRet7;
/*       */           } 
/*       */ 
/*       */           
/*  5696 */           pMqmd.readFromBuffer(spiInOut.getRfpBuffer(), spiInOut.getMsgDescOffset(), 4, swap, cp, jmqiTls);
/*       */ 
/*       */ 
/*       */           
/*  5700 */           pMqpmo.readFromBuffer(spiInOut.getRfpBuffer(), spiInOut.getPutMsgOptsOffset(), 4, swap, cp, jmqiTls);
/*       */         }
/*       */       
/*       */       }
/*       */       finally {
/*       */         
/*  5706 */         if (Trace.isOn) {
/*  5707 */           Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,int,ByteBuffer,Pint,Pint)");
/*       */         }
/*       */ 
/*       */ 
/*       */         
/*       */         try {
/*  5713 */           if (remoteHconn != null) {
/*  5714 */             if (rMQAPI != null) {
/*       */               try {
/*  5716 */                 remoteSession.releaseReceivedTSH((RfpTSH)rMQAPI);
/*       */               }
/*  5718 */               catch (JmqiException e) {
/*  5719 */                 if (Trace.isOn) {
/*  5720 */                   Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,int,ByteBuffer,Pint,Pint)", (Throwable)e, 1);
/*       */                 }
/*       */               } 
/*       */             }
/*       */ 
/*       */ 
/*       */ 
/*       */             
/*  5728 */             remoteHconn.leaveCall(pReason.x);
/*       */           }
/*       */         
/*  5731 */         } catch (JmqiException e) {
/*  5732 */           if (Trace.isOn) {
/*  5733 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,int,ByteBuffer,Pint,Pint)", (Throwable)e, 2);
/*       */           }
/*       */         }
/*       */       
/*       */       }
/*       */     
/*  5739 */     } catch (JmqiException e) {
/*  5740 */       if (Trace.isOn) {
/*  5741 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,int,ByteBuffer,Pint,Pint)", (Throwable)e, 3);
/*       */       }
/*       */ 
/*       */ 
/*       */       
/*  5746 */       jmqiTls.lastException = e;
/*  5747 */       pCompCode.x = e.getCompCode();
/*  5748 */       pReason.x = e.getReason();
/*       */     } 
/*       */     
/*  5751 */     if (Trace.isOn) {
/*  5752 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,int,ByteBuffer,Pint,Pint)", 2);
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void spiPut(Hconn hconn, Hobj hobj, MQMD pMsgDesc, MQPMO pPutMsgOpts, SpiPutOptions pSpiPutOpts, ByteBuffer[] pBuffers, Pint pCompCode, Pint pReason) {
/*  5768 */     if (Trace.isOn) {
/*  5769 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,ByteBuffer [ ],final Pint,final Pint)", new Object[] { hconn, hobj, pMsgDesc, pPutMsgOpts, pSpiPutOpts, pBuffers, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */ 
/*       */     
/*  5774 */     ByteBuffer mergedByteBuffer = JmqiTools.mergeByteBuffers(pBuffers);
/*  5775 */     spiPut(hconn, hobj, pMsgDesc, pPutMsgOpts, pSpiPutOpts, mergedByteBuffer.limit(), mergedByteBuffer, pCompCode, pReason);
/*       */ 
/*       */     
/*  5778 */     if (Trace.isOn) {
/*  5779 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,ByteBuffer [ ],final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public RfpVerbArray[] spiQuerySpi(RemoteSession remoteSession, Pint pCompCode, Pint pReason, boolean enterCall) {
/*  5797 */     if (Trace.isOn) {
/*  5798 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiQuerySpi(RemoteSession,Pint,Pint,boolean)", new Object[] { remoteSession, pCompCode, pReason, 
/*  5799 */             Boolean.valueOf(enterCall) });
/*       */     }
/*  5801 */     RemoteHconn remoteHconn = remoteSession.getHconn();
/*  5802 */     RfpMQAPI rMQAPI = null;
/*       */     
/*  5804 */     RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  5805 */     JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/*  5806 */     jmqiTls.lastException = null;
/*  5807 */     int bufferLength = 12 * RfpVerbArray.getRequiredBufferSize();
/*  5808 */     RfpVerbArray[] verbArrayArray = null;
/*       */     try {
/*  5810 */       if (enterCall) {
/*       */         
/*  5812 */         remoteHconn.enterCall(tls.isDispatchThread, false);
/*       */ 
/*       */ 
/*       */         
/*  5816 */         remoteSession.checkIfDisconnected();
/*       */       } 
/*  5818 */       boolean swap = remoteSession.isSwap();
/*       */       
/*  5820 */       RfpMQAPI sMQAPI = null;
/*  5821 */       sMQAPI = remoteSession.allocateMQAPI(140);
/*  5822 */       sMQAPI.setHandle(0, swap);
/*  5823 */       int inSpiOffset = sMQAPI.getRfpOffset() + sMQAPI.hdrSize();
/*  5824 */       byte[] commsBuffer = sMQAPI.getRfpBuffer();
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  5830 */       RfpINSPI inSpi = RfpINSPI.getInstance(this.env, remoteSession, commsBuffer, inSpiOffset);
/*  5831 */       RfpVerbArray verbArray = RfpVerbArray.getQuerySpi((JmqiEnvironment)this.sysenv);
/*  5832 */       int spiInOutOffset = inSpiOffset + inSpi.getStructSize();
/*  5833 */       RfpQUERYSPIINOUT spiInOut = RfpQUERYSPIINOUT.getInstance((JmqiEnvironment)this.sysenv, remoteSession, commsBuffer, spiInOutOffset, verbArray
/*  5834 */           .getMaxInoutVersion());
/*  5835 */       int spiInOffset = spiInOutOffset + spiInOut.getStructSize();
/*  5836 */       RfpQUERYSPIIN spiIn = RfpQUERYSPIIN.getInstance((JmqiEnvironment)this.sysenv, remoteSession, commsBuffer, spiInOffset, verbArray
/*  5837 */           .getMaxInVersion());
/*  5838 */       int spiOutRelativeOffset = spiInOffset - sMQAPI.getRfpOffset();
/*  5839 */       RfpQUERYSPIOUT spiOut = RfpQUERYSPIOUT.getInstance((JmqiEnvironment)this.sysenv, remoteSession, commsBuffer, 0, verbArray
/*  5840 */           .getMaxOutVersion());
/*  5841 */       spiOut.setDefaultVersion(swap);
/*       */       
/*  5843 */       inSpi.setVerbId(1, swap);
/*  5844 */       inSpi.setOutStructVersion(spiOut.getVersion(swap), swap);
/*  5845 */       inSpi.setOutStructLength(spiOut.getStructSize() + bufferLength, swap);
/*  5846 */       spiInOut.initEyecatcher();
/*  5847 */       spiInOut.setDefaultVersion(swap);
/*  5848 */       spiInOut.setLength(spiInOut.getStructSize(), swap);
/*  5849 */       spiIn.initEyecatcher();
/*  5850 */       spiIn.setDefaultVersion(swap);
/*  5851 */       spiIn.setLength(spiIn.getStructSize(), swap);
/*  5852 */       int transLength = sMQAPI.hdrSize() + inSpi.getStructSize() + spiInOut.getStructSize() + spiIn.getStructSize();
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  5857 */       sMQAPI.setCallLength(transLength + bufferLength);
/*       */       
/*  5859 */       sMQAPI.setTransLength(transLength);
/*  5860 */       sMQAPI.setControlFlags1(48);
/*       */       
/*  5862 */       remoteSession.sendTSH(tls, (RfpTSH)sMQAPI);
/*       */       
/*  5864 */       rMQAPI = remoteSession.receiveMQIFlow(tls);
/*  5865 */       commsBuffer = rMQAPI.getRfpBuffer();
/*  5866 */       if (rMQAPI.getSegmentType() != 156 || (rMQAPI.getControlFlags1() & 0x20) == 0) {
/*       */ 
/*       */ 
/*       */         
/*  5870 */         if (Trace.isOn) {
/*  5871 */           Trace.data(this, "spiQuerySpi(RemoteSession,Pint,Pint,boolean)", 
/*  5872 */               Integer.valueOf(rMQAPI.getSegmentType()));
/*  5873 */           Trace.data(this, "spiQuerySpi(RemoteSession,Pint,Pint,boolean)", 
/*  5874 */               Integer.valueOf(rMQAPI.getControlFlags1()));
/*       */         } 
/*  5876 */         JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*       */ 
/*       */         
/*  5879 */         if (Trace.isOn) {
/*  5880 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiQuerySpi(RemoteSession,Pint,Pint,boolean)", (Throwable)traceRet1, 1);
/*       */         }
/*       */         
/*  5883 */         throw traceRet1;
/*       */       } 
/*       */ 
/*       */       
/*  5887 */       swap = remoteSession.isSwap();
/*       */ 
/*       */       
/*  5890 */       pCompCode.x = rMQAPI.getCompCode(swap);
/*  5891 */       pReason.x = rMQAPI.getReasonCode(swap);
/*       */       
/*  5893 */       if (pCompCode.x != 2) {
/*  5894 */         spiOut.setRfpBuffer(commsBuffer);
/*  5895 */         spiOut.setRfpOffset(rMQAPI.getRfpOffset() + spiOutRelativeOffset);
/*  5896 */         if (!spiOut.checkID()) {
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  5901 */           JmqiException traceRet2 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*       */           
/*  5903 */           if (Trace.isOn) {
/*  5904 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiQuerySpi(RemoteSession,Pint,Pint,boolean)", (Throwable)traceRet2, 2);
/*       */           }
/*       */           
/*  5907 */           throw traceRet2;
/*       */         } 
/*  5909 */         verbArrayArray = spiOut.getArray(swap);
/*       */       }
/*       */     
/*  5912 */     } catch (JmqiException e) {
/*  5913 */       if (Trace.isOn) {
/*  5914 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiQuerySpi(RemoteSession,Pint,Pint,boolean)", (Throwable)e, 1);
/*       */       }
/*       */ 
/*       */       
/*  5918 */       jmqiTls.lastException = e;
/*  5919 */       pCompCode.x = e.getCompCode();
/*  5920 */       pReason.x = e.getReason();
/*       */     } finally {
/*       */       
/*  5923 */       if (Trace.isOn) {
/*  5924 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiQuerySpi(RemoteSession,Pint,Pint,boolean)");
/*       */       }
/*       */ 
/*       */       
/*       */       try {
/*  5929 */         if (rMQAPI != null) {
/*       */           try {
/*  5931 */             remoteSession.releaseReceivedTSH((RfpTSH)rMQAPI);
/*       */           }
/*  5933 */           catch (JmqiException e) {
/*  5934 */             if (Trace.isOn) {
/*  5935 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiQuerySpi(RemoteSession,Pint,Pint,boolean)", (Throwable)e, 2);
/*       */             }
/*       */           } 
/*       */         }
/*       */ 
/*       */         
/*  5941 */         if (enterCall)
/*       */         {
/*  5943 */           remoteHconn.leaveCall(pReason.x);
/*       */         
/*       */         }
/*       */       }
/*  5947 */       catch (JmqiException e) {
/*  5948 */         if (Trace.isOn) {
/*  5949 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiQuerySpi(RemoteSession,Pint,Pint,boolean)", (Throwable)e, 3);
/*       */         }
/*       */       } 
/*       */     } 
/*       */ 
/*       */     
/*  5955 */     if (Trace.isOn) {
/*  5956 */       Trace.data(this, "spiQuerySpi(RemoteSession,Pint,Pint,boolean)", "spiQuerySpi exit CC = ", pCompCode);
/*  5957 */       Trace.data(this, "spiQuerySpi(RemoteSession,Pint,Pint,boolean)", "spiQuerySpi query RC = ", pReason);
/*       */     } 
/*       */     
/*  5960 */     if (Trace.isOn) {
/*  5961 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiQuerySpi(RemoteSession,Pint,Pint,boolean)", verbArrayArray);
/*       */     }
/*       */     
/*  5964 */     return verbArrayArray;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public ByteBuffer jmqiGet(Hconn hconn, Hobj hobj, MQMD md, MQGMO gmo, int expectedMsgLength, int maxMsgLength, PbyteBuffer pByteBuffer, Pint pMsgTooSmallForBufferCount, Pint pDataLength, Pint pCompCode, Pint pReason) {
/*  5988 */     if (Trace.isOn) {
/*  5989 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiGet(Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", new Object[] { hconn, hobj, md, gmo, 
/*       */             
/*  5991 */             Integer.valueOf(expectedMsgLength), 
/*  5992 */             Integer.valueOf(maxMsgLength), pByteBuffer, pMsgTooSmallForBufferCount, pDataLength, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */ 
/*       */     
/*  5997 */     int originalOptions = gmo.getOptions();
/*  5998 */     int originalMatchOptions = gmo.getMatchOptions();
/*  5999 */     if ((originalOptions & 0x1006) == 0) {
/*  6000 */       gmo.setOptions(originalOptions | 0x4);
/*       */     }
/*  6002 */     ByteBuffer traceRet1 = JmqiTools.getMessage(this.env, this, hconn, hobj, md, gmo, expectedMsgLength, maxMsgLength, pByteBuffer, pMsgTooSmallForBufferCount, pDataLength, pCompCode, pReason);
/*       */     
/*  6004 */     gmo.setOptions(originalOptions);
/*  6005 */     gmo.setMatchOptions(originalMatchOptions);
/*       */ 
/*       */     
/*  6008 */     if (Trace.isOn) {
/*  6009 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiGet(Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", traceRet1);
/*       */     }
/*       */     
/*  6012 */     return traceRet1;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void jmqiGetInternal(Hconn hconn, Hobj hobj, MQMD md, MQGMO gmo, int bufferLength, ByteBuffer buffer, Pint pDataLength, Pint pCompCode, Pint pReason) {
/*  6031 */     if (Trace.isOn) {
/*  6032 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiGetInternal(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,Pint,Pint)", new Object[] { hconn, hobj, md, gmo, 
/*       */             
/*  6034 */             Integer.valueOf(bufferLength), buffer, pDataLength, pCompCode, pReason });
/*       */     }
/*       */     
/*  6037 */     byte[] savedMsgId = Arrays.copyOf(md.getMsgId(), (md.getMsgId()).length);
/*  6038 */     byte[] savedCorrelId = Arrays.copyOf(md.getCorrelId(), (md.getCorrelId()).length);
/*  6039 */     RemoteSession session = jmqiGetInternalWithRecon(hconn, hobj, md, gmo, bufferLength, buffer, pDataLength, pCompCode, pReason);
/*       */     
/*  6041 */     RemoteHconn remoteHconn = (RemoteHconn)hconn;
/*  6042 */     if (0 != pReason.x && remoteHconn.isReconnectable() && 
/*  6043 */       !threadIsReconnectThread() && 
/*  6044 */       RemoteHconn.isReconnectableReasonCode(pReason.x) && 
/*  6045 */       !remoteHconn.hasFailed()) {
/*       */       try {
/*  6047 */         remoteHconn.initiateReconnection(session);
/*  6048 */         md.setMsgId(savedMsgId);
/*  6049 */         md.setCorrelId(savedCorrelId);
/*  6050 */         jmqiGetInternal(hconn, hobj, md, gmo, bufferLength, buffer, pDataLength, pCompCode, pReason);
/*       */       
/*       */       }
/*  6053 */       catch (JmqiException je) {
/*  6054 */         if (Trace.isOn) {
/*  6055 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiGetInternal(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,Pint,Pint)", (Throwable)je);
/*       */         }
/*       */       } 
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  6066 */     if (remoteHconn.hasFailed()) {
/*  6067 */       pCompCode.x = remoteHconn.getReconnectionFailureCompCode();
/*  6068 */       pReason.x = remoteHconn.getReconnectionFailureReason();
/*  6069 */       RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  6070 */       JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/*  6071 */       jmqiTls.lastException = remoteHconn.getReconnectionFailureException();
/*       */     } 
/*       */     
/*  6074 */     if (Trace.isOn) {
/*  6075 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiGetInternal(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,Pint,Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public RemoteSession jmqiGetInternalWithRecon(Hconn hconn, Hobj hobj, MQMD md, MQGMO gmo, int bufferLength, ByteBuffer buffer, Pint pDataLength, Pint pCompCode, Pint pReason) {
/*  6097 */     if (Trace.isOn) {
/*  6098 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiGetInternalWithRecon(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,Pint,Pint)", new Object[] { hconn, hobj, md, gmo, 
/*       */             
/*  6100 */             Integer.valueOf(bufferLength), buffer, pDataLength, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */     
/*  6104 */     RemoteSession remoteSession = null;
/*       */     try {
/*  6106 */       RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  6107 */       RemoteHconn remoteHconn = getRemoteHconn(tls, hconn);
/*  6108 */       remoteSession = remoteHconn.getSession();
/*       */ 
/*       */       
/*  6111 */       if (remoteHconn.isReconnectable() && (
/*  6112 */         gmo.getOptions() & 0x8000) != 0) {
/*  6113 */         JmqiException je = new JmqiException(this.env, -1, null, 2, 2547, null);
/*       */ 
/*       */         
/*  6116 */         if (Trace.isOn) {
/*  6117 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiGetInternalWithRecon(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,Pint,Pint)", (Throwable)je);
/*       */         }
/*       */         
/*  6120 */         throw je;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  6126 */       SpiGetOptions spiGetOptions = this.sysenv.newSpiGetOptions();
/*  6127 */       if ((gmo.getOptions() & 0x4000) != 0) {
/*  6128 */         spiGetOptions.setOptions(128);
/*       */       }
/*       */       
/*  6131 */       handleUndefinedCcsid(md);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  6137 */       RemoteProxyQueue proxyQueue = null;
/*  6138 */       if (hobj instanceof RemoteHobj) {
/*  6139 */         proxyQueue = ((RemoteHobj)hobj).getProxyQueue();
/*       */       }
/*  6141 */       if (proxyQueue != null && (
/*  6142 */         !remoteHconn.getParentConnection().isMultiplexSyncGetCapable() || (proxyQueue.getStatus() & 0x4000) != 0)) {
/*       */         
/*  6144 */         remoteHconn.enterCall(tls.isDispatchThread, false);
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         try {
/*  6150 */           remoteSession.checkIfDisconnected();
/*       */           
/*  6152 */           proxyQueue.proxyMQGET(tls, md, gmo, bufferLength, buffer.array(), pDataLength, spiGetOptions, pCompCode, pReason);
/*       */         }
/*       */         finally {
/*       */           
/*  6156 */           if (Trace.isOn) {
/*  6157 */             Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiGetInternalWithRecon(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,Pint,Pint)");
/*       */           }
/*       */ 
/*       */ 
/*       */           
/*  6162 */           remoteHconn.leaveCall(pReason.x);
/*       */         }
/*       */       
/*       */       } else {
/*       */         
/*  6167 */         jmqiGetMessageWithProps(hconn, hobj, md, gmo, bufferLength, buffer, pDataLength, null, pCompCode, pReason);
/*       */       }
/*       */     
/*       */     }
/*  6171 */     catch (JmqiException e) {
/*  6172 */       if (Trace.isOn) {
/*  6173 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiGetInternalWithRecon(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,Pint,Pint)", (Throwable)e);
/*       */       }
/*       */ 
/*       */       
/*  6177 */       pCompCode.x = e.getCompCode();
/*  6178 */       pReason.x = e.getReason();
/*       */     } 
/*       */     
/*  6181 */     if (Trace.isOn) {
/*  6182 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiGetInternalWithRecon(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,Pint,Pint)", remoteSession);
/*       */     }
/*       */     
/*  6185 */     return remoteSession;
/*       */   }
/*       */   
/*       */   private void handleUndefinedCcsid(MQMD md) {
/*  6189 */     if (Trace.isOn) {
/*  6190 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "handleUndefinedCcsid(MQMD)", new Object[] { md });
/*       */     }
/*       */     
/*  6193 */     if (md.getCodedCharSetId() == 0) {
/*  6194 */       switch (CSSystem.currentPlatform()) {
/*       */         case OS_ZSERIES:
/*  6196 */           md.setCodedCharSetId(500);
/*       */           break;
/*       */         case OS_ISERIES:
/*  6199 */           md.setCodedCharSetId(37);
/*       */           break;
/*       */         default:
/*  6202 */           md.setCodedCharSetId(819);
/*       */           break;
/*       */       } 
/*       */     }
/*  6206 */     if (Trace.isOn) {
/*  6207 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "handleUndefinedCcsid(MQMD)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean jmqiConvertMessage(Hconn hconn, Hobj hobj, int encoding, int ccsid, int options, boolean callExitOnLenErr, MQMD md, ByteBuffer buffer, Pint pDataLength, int availableLength, int bufferLength, Pint pCompCode, Pint pReason, Pint pReturnedLength) {
/*  6236 */     if (Trace.isOn) {
/*  6237 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiConvertMessage(Hconn,Hobj,int,int,int,boolean,MQMD,ByteBuffer,Pint,int,int,Pint,Pint,Pint)", new Object[] { hconn, hobj, 
/*       */             
/*  6239 */             Integer.valueOf(encoding), Integer.valueOf(ccsid), 
/*  6240 */             Integer.valueOf(options), Boolean.valueOf(callExitOnLenErr), md, buffer, pDataLength, 
/*  6241 */             Integer.valueOf(availableLength), Integer.valueOf(bufferLength), pCompCode, pReason, pReturnedLength });
/*       */     }
/*       */ 
/*       */     
/*  6245 */     RemoteHobj remoteHobj = (RemoteHobj)hobj;
/*  6246 */     if (remoteHobj.getProxyQueue() != null && remoteHobj.getAMSPolicy() != null) {
/*  6247 */       HashMap<String, Object> info = new HashMap<>();
/*  6248 */       info.put("Description", "Unexpected call to jmqiConvertMessage");
/*  6249 */       Trace.ffst(this, "jmqiConvertMessage(Hconn,Hobj,int,int,int,boolean,MQMD,ByteBuffer,Pint,int,int,Pint,Pint,Pint)", "01", info, null);
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  6255 */     if (Trace.isOn) {
/*  6256 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiConvertMessage(Hconn,Hobj,int,int,int,boolean,MQMD,ByteBuffer,Pint,int,int,Pint,Pint,Pint)", 
/*       */           
/*  6258 */           Boolean.valueOf(true));
/*       */     }
/*  6260 */     return true;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void spiGet(Hconn hconn, Hobj hobj, MQMD pMqmd, MQGMO pMqgmo, SpiGetOptions spiGetOptions, int bufferLength, ByteBuffer pByteBuffer, Pint dataLength, Pint pCompCode, Pint pReason) {
/*  6290 */     if (Trace.isOn) {
/*  6291 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,int,ByteBuffer,Pint,Pint,Pint)", new Object[] { hconn, hobj, pMqmd, pMqgmo, spiGetOptions, 
/*       */             
/*  6293 */             Integer.valueOf(bufferLength), pByteBuffer, dataLength, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */ 
/*       */     
/*  6298 */     if (pByteBuffer == null) {
/*  6299 */       NullPointerException npe = new NullPointerException();
/*  6300 */       if (Trace.isOn) {
/*  6301 */         Trace.throwing("com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,int,ByteBuffer,Pint,Pint,Pint)", npe);
/*       */       }
/*       */       
/*  6304 */       if (Trace.isOn) {
/*  6305 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,int,ByteBuffer,Pint,Pint,Pint)", npe, 1);
/*       */       }
/*       */       
/*  6308 */       throw npe;
/*       */     } 
/*       */     
/*  6311 */     RfpMQAPI rMQAPI = null;
/*       */     
/*  6313 */     RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  6314 */     JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/*  6315 */     jmqiTls.lastException = null;
/*       */     try {
/*  6317 */       RemoteHconn remoteHconn = getRemoteHconn(tls, hconn);
/*  6318 */       RemoteSession remoteSession = null;
/*       */ 
/*       */ 
/*       */       
/*  6322 */       if (remoteHconn.isReconnectable() && (
/*  6323 */         pMqgmo.getOptions() & 0x8000) != 0) {
/*  6324 */         JmqiException je = new JmqiException(this.env, -1, null, 2, 2547, null);
/*       */ 
/*       */         
/*  6327 */         if (Trace.isOn) {
/*  6328 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,int,ByteBuffer,Pint,Pint,Pint)", (Throwable)je, 2);
/*       */         }
/*       */         
/*  6331 */         throw je;
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/*  6336 */       if (hobj == null || !(hobj instanceof RemoteHobj)) {
/*  6337 */         JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2019, null);
/*       */ 
/*       */         
/*  6340 */         if (Trace.isOn) {
/*  6341 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,int,ByteBuffer,Pint,Pint,Pint)", (Throwable)traceRet1, 3);
/*       */         }
/*       */         
/*  6344 */         throw traceRet1;
/*       */       } 
/*       */       
/*  6347 */       RemoteHobj remoteHobj = (RemoteHobj)hobj;
/*       */ 
/*       */       
/*  6350 */       if (pMqmd == null) {
/*  6351 */         JmqiException traceRet2 = new JmqiException(this.env, -1, null, 2, 2026, null);
/*       */         
/*  6353 */         if (Trace.isOn) {
/*  6354 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,int,ByteBuffer,Pint,Pint,Pint)", (Throwable)traceRet2, 4);
/*       */         }
/*       */         
/*  6357 */         throw traceRet2;
/*       */       } 
/*       */       
/*  6360 */       if (pMqgmo == null) {
/*  6361 */         JmqiException traceRet3 = new JmqiException(this.env, -1, null, 2, 2186, null);
/*       */         
/*  6363 */         if (Trace.isOn) {
/*  6364 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,int,ByteBuffer,Pint,Pint,Pint)", (Throwable)traceRet3, 5);
/*       */         }
/*       */         
/*  6367 */         throw traceRet3;
/*       */       } 
/*       */       
/*  6370 */       byte[] pBuffer = pByteBuffer.array();
/*  6371 */       int spiOptions = spiGetOptions.getOptions();
/*       */ 
/*       */       
/*  6374 */       remoteHconn.enterCall(tls.isDispatchThread, false);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*       */       try {
/*  6382 */         remoteSession = remoteHconn.getSession();
/*       */         
/*  6384 */         remoteSession.checkIfDisconnected();
/*       */         
/*  6386 */         RfpVerbArray verbArray = remoteSession.getVerbArray(3, pCompCode, pReason, false);
/*  6387 */         if (pCompCode.x != 0) {
/*       */           
/*  6389 */           if (Trace.isOn) {
/*  6390 */             Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,int,ByteBuffer,Pint,Pint,Pint)", 1);
/*       */           }
/*       */           
/*       */           return;
/*       */         } 
/*       */         
/*  6396 */         JmqiCodepage cp = remoteSession.getCp();
/*  6397 */         boolean swap = remoteSession.isSwap();
/*       */ 
/*       */         
/*  6400 */         RfpMQAPI sMQAPI = null;
/*  6401 */         sMQAPI = remoteSession.allocateMQAPI(140);
/*  6402 */         sMQAPI.setHandle(remoteHobj.getIntegerHandle(), swap);
/*       */         
/*  6404 */         int inSpiOffset = sMQAPI.getRfpOffset() + sMQAPI.hdrSize();
/*  6405 */         byte[] commsBuffer = sMQAPI.getRfpBuffer();
/*       */         
/*  6407 */         int pMqmdBufferSize = pMqmd.getRequiredBufferSize(4, cp);
/*  6408 */         int pMqgmoBufferSize = pMqgmo.getRequiredBufferSize(4, cp);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  6416 */         RfpINSPI inSpi = RfpINSPI.getInstance((JmqiEnvironment)this.sysenv, remoteSession, commsBuffer, inSpiOffset);
/*       */         
/*  6418 */         int spiInOutOffset = inSpiOffset + inSpi.getStructSize();
/*  6419 */         RfpGETSPIINOUT spiInOut = RfpGETSPIINOUT.getInstance((JmqiEnvironment)this.sysenv, remoteSession, commsBuffer, spiInOutOffset, verbArray
/*  6420 */             .getMaxInoutVersion(), pMqmdBufferSize, pMqgmoBufferSize);
/*       */         
/*  6422 */         int spiInOffset = spiInOutOffset + spiInOut.getStructSize();
/*  6423 */         RfpGETSPIIN spiIn = RfpGETSPIIN.getInstance((JmqiEnvironment)this.sysenv, remoteSession, commsBuffer, spiInOffset, verbArray
/*  6424 */             .getMaxInVersion());
/*       */         
/*  6426 */         int spiOutRelativeOffset = spiInOffset - sMQAPI.getRfpOffset();
/*  6427 */         RfpGETSPIOUT spiOut = RfpGETSPIOUT.getInstance((JmqiEnvironment)this.sysenv, remoteSession, commsBuffer, 0, verbArray
/*  6428 */             .getMaxOutVersion());
/*  6429 */         spiOut.setDefaultVersion(swap);
/*       */ 
/*       */         
/*  6432 */         inSpi.setVerbId(3, swap);
/*  6433 */         inSpi.setOutStructVersion(spiOut.getVersion(swap), swap);
/*  6434 */         inSpi.setOutStructLength(spiOut.getStructSize() + bufferLength, swap);
/*       */ 
/*       */         
/*  6437 */         spiInOut.initEyecatcher();
/*  6438 */         spiInOut.setDefaultVersion(swap);
/*  6439 */         spiInOut.setLength(spiInOut.getStructSize(), swap);
/*  6440 */         pMqmd.writeToBuffer(spiInOut.getRfpBuffer(), spiInOut.getMsgDescOffset(), true, false, false, 4, swap, cp, jmqiTls);
/*       */         
/*  6442 */         pMqgmo.writeToBuffer(spiInOut.getRfpBuffer(), spiInOut.getGetMsgOptsOffset(), 4, swap, cp, jmqiTls);
/*       */ 
/*       */ 
/*       */         
/*  6446 */         spiIn.initEyecatcher();
/*  6447 */         spiIn.setDefaultVersion(swap);
/*  6448 */         spiIn.setLength(spiIn.getStructSize(), swap);
/*  6449 */         spiIn.setBatchSize(1, swap);
/*  6450 */         spiIn.setBatchInterval(1, swap);
/*  6451 */         spiIn.setMaxMsgLength(bufferLength, swap);
/*  6452 */         spiIn.setOptions(spiOptions, swap);
/*       */         
/*  6454 */         int transLength = sMQAPI.hdrSize() + inSpi.getStructSize() + spiInOut.getStructSize() + spiIn.getStructSize();
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  6462 */         sMQAPI.setCallLength(transLength + bufferLength);
/*       */ 
/*       */         
/*  6465 */         sMQAPI.setTransLength(transLength);
/*       */         
/*  6467 */         sMQAPI.setControlFlags1(48);
/*       */ 
/*       */         
/*  6470 */         remoteSession.sendTSH(tls, (RfpTSH)sMQAPI);
/*       */ 
/*       */         
/*  6473 */         rMQAPI = remoteSession.receiveMQIFlow(tls);
/*       */         
/*  6475 */         commsBuffer = rMQAPI.getRfpBuffer();
/*       */         
/*  6477 */         if (rMQAPI.getSegmentType() != 156) {
/*  6478 */           HashMap<String, Object> info = new HashMap<>();
/*  6479 */           info.put("SegmentType", Integer.valueOf(rMQAPI.getSegmentType()));
/*  6480 */           info.put("ControlFlags1", Integer.valueOf(rMQAPI.getControlFlags1()));
/*  6481 */           info.put("Description", "Unexpected flow received from server");
/*  6482 */           Trace.ffst(this, "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,int,ByteBuffer,Pint,Pint,Pint)", "01", info, null);
/*       */           
/*  6484 */           JmqiException e = new JmqiException(this.env, -1, null, 2, 2195, null);
/*       */           
/*  6486 */           if (Trace.isOn) {
/*  6487 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,int,ByteBuffer,Pint,Pint,Pint)", (Throwable)e, 6);
/*       */           }
/*       */           
/*  6490 */           throw e;
/*       */         } 
/*       */ 
/*       */ 
/*       */         
/*  6495 */         swap = remoteSession.isSwap();
/*       */ 
/*       */         
/*  6498 */         pCompCode.x = rMQAPI.getCompCode(swap);
/*  6499 */         pReason.x = rMQAPI.getReasonCode(swap);
/*       */         
/*  6501 */         if (pCompCode.x != 2) {
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  6506 */           spiInOut.setRfpBuffer(commsBuffer);
/*  6507 */           spiInOut.setRfpOffset(spiInOutOffset);
/*       */           
/*  6509 */           if (!spiInOut.checkID()) {
/*       */ 
/*       */ 
/*       */ 
/*       */             
/*  6514 */             JmqiException traceRet4 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*       */             
/*  6516 */             if (Trace.isOn) {
/*  6517 */               Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,int,ByteBuffer,Pint,Pint,Pint)", (Throwable)traceRet4, 7);
/*       */             }
/*       */ 
/*       */             
/*  6521 */             throw traceRet4;
/*       */           } 
/*       */           
/*  6524 */           pMqmd.readFromBuffer(spiInOut.getRfpBuffer(), spiInOut.getMsgDescOffset(), 4, swap, cp, jmqiTls);
/*       */           
/*  6526 */           pMqgmo.readFromBuffer(spiInOut.getRfpBuffer(), spiInOut.getGetMsgOptsOffset(), 4, swap, cp, jmqiTls);
/*       */ 
/*       */ 
/*       */           
/*  6530 */           spiOut.setRfpBuffer(commsBuffer);
/*  6531 */           spiOut.setRfpOffset(rMQAPI.getRfpOffset() + spiOutRelativeOffset);
/*       */           
/*  6533 */           if (!spiOut.checkID()) {
/*       */ 
/*       */ 
/*       */ 
/*       */             
/*  6538 */             JmqiException traceRet5 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*       */             
/*  6540 */             if (Trace.isOn) {
/*  6541 */               Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,int,ByteBuffer,Pint,Pint,Pint)", (Throwable)traceRet5, 8);
/*       */             }
/*       */ 
/*       */             
/*  6545 */             throw traceRet5;
/*       */           } 
/*       */ 
/*       */           
/*  6549 */           dataLength.x = spiOut.getMsgLength(swap);
/*       */ 
/*       */           
/*  6552 */           byte[] rfpBuffer = spiOut.getRfpBuffer();
/*  6553 */           int msgOffset = spiOut.getMsgOffset();
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  6583 */           int msgDataReceived = rMQAPI.getTransLength() - msgOffset;
/*  6584 */           System.arraycopy(rfpBuffer, msgOffset, pBuffer, 0, msgDataReceived);
/*       */           
/*  6586 */           if ((rMQAPI.getControlFlags1() & 0x20) == 0) {
/*       */ 
/*       */ 
/*       */ 
/*       */             
/*  6591 */             boolean isLastSegment = false;
/*       */             
/*  6593 */             if (Trace.isOn) {
/*  6594 */               Trace.data(this, "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,int,ByteBuffer,Pint,Pint,Pint)", "Message is segmented. Received " + msgDataReceived + " of " + pBuffer.length + " expected bytes", null);
/*       */             }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */             
/*  6601 */             while (!isLastSegment) {
/*       */               
/*  6603 */               RfpTSH rTSH = remoteSession.receiveTSH(tls, null);
/*       */ 
/*       */               
/*  6606 */               int receivedDataLength = rTSH.getTransLength() - rTSH.hdrSize();
/*  6607 */               System.arraycopy(rTSH.getRfpBuffer(), rTSH.getRfpOffset() + rTSH.hdrSize(), pBuffer, msgDataReceived, receivedDataLength);
/*       */ 
/*       */ 
/*       */               
/*  6611 */               msgDataReceived += receivedDataLength;
/*       */               
/*  6613 */               if (Trace.isOn) {
/*  6614 */                 Trace.data(this, "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,int,ByteBuffer,Pint,Pint,Pint)", "Received " + msgDataReceived + " of " + pBuffer.length + " expected bytes", null);
/*       */               }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */               
/*  6621 */               if ((rMQAPI.getControlFlags1() & 0x20) != 0) {
/*  6622 */                 isLastSegment = true;
/*  6623 */                 if (Trace.isOn) {
/*  6624 */                   Trace.data(this, "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,int,ByteBuffer,Pint,Pint,Pint)", "This is the last segment", null);
/*       */                 }
/*       */               } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */               
/*  6638 */               if (!isLastSegment) {
/*  6639 */                 remoteSession.releaseReceivedTSH(rTSH);
/*       */               }
/*       */             } 
/*       */           } 
/*       */         } 
/*       */       } finally {
/*       */         
/*  6646 */         if (Trace.isOn) {
/*  6647 */           Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,int,ByteBuffer,Pint,Pint,Pint)");
/*       */         }
/*       */ 
/*       */ 
/*       */         
/*       */         try {
/*  6653 */           if (remoteHconn != null) {
/*  6654 */             if (rMQAPI != null) {
/*       */               try {
/*  6656 */                 remoteSession.releaseReceivedTSH((RfpTSH)rMQAPI);
/*       */               }
/*  6658 */               catch (JmqiException e) {
/*  6659 */                 if (Trace.isOn) {
/*  6660 */                   Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,int,ByteBuffer,Pint,Pint,Pint)", (Throwable)e, 1);
/*       */                 }
/*       */               } 
/*       */             }
/*       */ 
/*       */ 
/*       */ 
/*       */             
/*  6668 */             remoteHconn.leaveCall(pReason.x);
/*       */           }
/*       */         
/*  6671 */         } catch (JmqiException e) {
/*  6672 */           if (Trace.isOn) {
/*  6673 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,int,ByteBuffer,Pint,Pint,Pint)", (Throwable)e, 2);
/*       */           }
/*       */         }
/*       */       
/*       */       }
/*       */     
/*  6679 */     } catch (JmqiException e) {
/*  6680 */       if (Trace.isOn) {
/*  6681 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,int,ByteBuffer,Pint,Pint,Pint)", (Throwable)e, 3);
/*       */       }
/*       */ 
/*       */ 
/*       */       
/*  6686 */       jmqiTls.lastException = e;
/*  6687 */       pCompCode.x = e.getCompCode();
/*  6688 */       pReason.x = e.getReason();
/*       */     } 
/*       */     
/*  6691 */     if (Trace.isOn) {
/*  6692 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,int,ByteBuffer,Pint,Pint,Pint)", 2);
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQCLOSE(Hconn hconn, Phobj pHobj, int options, Pint pCompCode, Pint pReason) {
/*  6714 */     if (Trace.isOn) {
/*  6715 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQCLOSE(Hconn,Phobj,int,Pint,Pint)", new Object[] { hconn, pHobj, 
/*  6716 */             Integer.valueOf(options), pCompCode, pReason });
/*       */     }
/*       */ 
/*       */     
/*  6720 */     if (Trace.isOn) {
/*  6721 */       Trace.data(this, "MQCLOSE(Hconn,Phobj,int,Pint,Pint)", "__________");
/*  6722 */       Trace.data(this, "MQCLOSE(Hconn,Phobj,int,Pint,Pint)", "MQCLOSE >>");
/*  6723 */       Trace.data(this, "MQCLOSE(Hconn,Phobj,int,Pint,Pint)", "Hconn", hconn);
/*  6724 */       Trace.data(this, "MQCLOSE(Hconn,Phobj,int,Pint,Pint)", "Options", "0x" + Integer.toHexString(options));
/*  6725 */       Trace.data(this, "MQCLOSE(Hconn,Phobj,int,Pint,Pint)", "Hobj", pHobj);
/*  6726 */       Trace.data(this, "MQCLOSE(Hconn,Phobj,int,Pint,Pint)", "CompCode", pCompCode);
/*  6727 */       Trace.data(this, "MQCLOSE(Hconn,Phobj,int,Pint,Pint)", "Reason", pReason);
/*  6728 */       Trace.data(this, "MQCLOSE(Hconn,Phobj,int,Pint,Pint)", "__________");
/*       */     } 
/*  6730 */     RemoteHconn remoteHconn = null;
/*  6731 */     RemoteSession remoteSession = null;
/*  6732 */     RfpMQAPI rMQAPI = null;
/*       */     
/*  6734 */     RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  6735 */     JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/*  6736 */     jmqiTls.lastException = null;
/*       */     try {
/*  6738 */       remoteHconn = getRemoteHconn(tls, hconn);
/*       */       
/*  6740 */       Hobj hobj = pHobj.getHobj();
/*  6741 */       if (hobj == null || (!(hobj instanceof RemoteHobj) && !(hobj instanceof RemoteHsub))) {
/*  6742 */         Exception nested = new Exception("hobj was " + String.valueOf(hobj));
/*  6743 */         JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2019, nested);
/*       */ 
/*       */         
/*  6746 */         if (Trace.isOn) {
/*  6747 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQCLOSE(Hconn,Phobj,int,Pint,Pint)", (Throwable)traceRet1, 1);
/*       */         }
/*       */         
/*  6750 */         throw traceRet1;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  6759 */       remoteHconn.enterCall(tls.isDispatchThread, false);
/*       */ 
/*       */ 
/*       */       
/*  6763 */       remoteHconn.enterNotifyCall(tls.isDispatchThread, false);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  6771 */       remoteHconn.removeHobj(pHobj.getHobj());
/*       */       
/*       */       try {
/*  6774 */         remoteSession = remoteHconn.getSession();
/*       */         
/*  6776 */         remoteSession.checkIfDisconnected();
/*       */         
/*  6778 */         boolean swap = remoteSession.isSwap();
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  6783 */         RemoteProxyQueue proxyQueue = null;
/*  6784 */         if (hobj instanceof RemoteHobj) {
/*  6785 */           proxyQueue = ((RemoteHobj)hobj).getProxyQueue();
/*       */         }
/*       */         
/*  6788 */         if (proxyQueue != null) {
/*  6789 */           proxyQueue.proxyMQCLOSE(tls, options, pCompCode, pReason);
/*       */         
/*       */         }
/*       */         else {
/*       */           
/*  6794 */           RfpMQAPI sMQAPI = remoteSession.allocateMQAPI(132);
/*       */           
/*  6796 */           sMQAPI.setControlFlags1(48);
/*  6797 */           sMQAPI.setHandle(hobj.getIntegerHandle(), swap);
/*       */           
/*  6799 */           int writePos = sMQAPI.getRfpOffset() + sMQAPI.hdrSize();
/*       */           
/*  6801 */           RfpMQCLOSE mqCloseDetails = (RfpMQCLOSE)remoteSession.getRfp(5, (RfpStructure)sMQAPI, writePos);
/*       */ 
/*       */           
/*  6804 */           writePos += 4;
/*       */           
/*  6806 */           mqCloseDetails.setOptions(options, swap);
/*       */           
/*  6808 */           int transLength = writePos - sMQAPI.getRfpOffset();
/*       */           
/*  6810 */           sMQAPI.setTransLength(transLength);
/*  6811 */           sMQAPI.setCallLength(transLength);
/*       */ 
/*       */           
/*  6814 */           remoteSession.sendTSH(tls, (RfpTSH)sMQAPI);
/*       */ 
/*       */           
/*  6817 */           rMQAPI = remoteSession.receiveMQIFlow(tls);
/*       */ 
/*       */           
/*  6820 */           if (rMQAPI.getSegmentType() != 148) {
/*  6821 */             HashMap<String, Object> info = new HashMap<>();
/*  6822 */             info.put("SegmentType", Integer.valueOf(rMQAPI.getSegmentType()));
/*  6823 */             info.put("ControlFlags1", Integer.valueOf(rMQAPI.getControlFlags1()));
/*  6824 */             info.put("Description", "Unexpected flow received from server");
/*  6825 */             Trace.ffst(this, "MQCLOSE(Hconn,Phobj,int,Pint,Pint)", "01", info, null);
/*  6826 */             JmqiException traceRet4 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*       */             
/*  6828 */             if (Trace.isOn) {
/*  6829 */               Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQCLOSE(Hconn,Phobj,int,Pint,Pint)", (Throwable)traceRet4, 2);
/*       */             }
/*       */             
/*  6832 */             throw traceRet4;
/*       */           } 
/*       */ 
/*       */ 
/*       */           
/*  6837 */           swap = remoteSession.isSwap();
/*       */ 
/*       */           
/*  6840 */           pCompCode.x = rMQAPI.getCompCode(swap);
/*  6841 */           pReason.x = rMQAPI.getReasonCode(swap);
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  6849 */         switch (pCompCode.x) {
/*       */           case 2:
/*  6851 */             switch (pReason.x) {
/*       */               case 2045:
/*       */               case 2055:
/*       */                 break;
/*       */             } 
/*       */ 
/*       */             
/*  6858 */             pHobj.setHobj(MQConstants.jmqi_MQHO_UNUSABLE_HOBJ);
/*       */             break;
/*       */ 
/*       */           
/*       */           case 1:
/*       */             break;
/*       */ 
/*       */           
/*       */           default:
/*  6867 */             pHobj.setHobj(MQConstants.jmqi_MQHO_UNUSABLE_HOBJ);
/*       */             break;
/*       */         } 
/*       */ 
/*       */       
/*       */       } finally {
/*  6873 */         if (Trace.isOn) {
/*  6874 */           Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQCLOSE(Hconn,Phobj,int,Pint,Pint)");
/*       */         }
/*       */ 
/*       */ 
/*       */         
/*  6879 */         if (remoteHconn != null) {
/*  6880 */           if (rMQAPI != null) {
/*       */             try {
/*  6882 */               remoteSession.releaseReceivedTSH((RfpTSH)rMQAPI);
/*       */             }
/*  6884 */             catch (JmqiException e) {
/*  6885 */               if (Trace.isOn) {
/*  6886 */                 Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQCLOSE(Hconn,Phobj,int,Pint,Pint)", (Throwable)e, 1);
/*       */               }
/*       */             } 
/*       */           }
/*       */ 
/*       */ 
/*       */           
/*       */           try {
/*  6894 */             remoteHconn.leaveNotifyCall(pReason.x, false);
/*       */           }
/*  6896 */           catch (JmqiException e) {
/*  6897 */             if (Trace.isOn) {
/*  6898 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQCLOSE(Hconn,Phobj,int,Pint,Pint)", (Throwable)e, 2);
/*       */             }
/*       */             
/*  6901 */             if (Trace.isOn) {
/*  6902 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQCLOSE(Hconn,Phobj,int,Pint,Pint)", (Throwable)e, 4);
/*       */             }
/*       */           } 
/*       */ 
/*       */           
/*       */           try {
/*  6908 */             remoteHconn.leaveCall(pReason.x);
/*       */           }
/*  6910 */           catch (JmqiException e) {
/*  6911 */             if (Trace.isOn) {
/*  6912 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQCLOSE(Hconn,Phobj,int,Pint,Pint)", (Throwable)e, 3);
/*       */             }
/*       */           }
/*       */         
/*       */         }
/*       */       
/*       */       } 
/*  6919 */     } catch (JmqiException e) {
/*  6920 */       if (Trace.isOn) {
/*  6921 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQCLOSE(Hconn,Phobj,int,Pint,Pint)", (Throwable)e, 4);
/*       */       }
/*       */ 
/*       */ 
/*       */       
/*  6926 */       jmqiTls.lastException = e;
/*  6927 */       pCompCode.x = e.getCompCode();
/*  6928 */       pReason.x = e.getReason();
/*       */     } 
/*       */ 
/*       */     
/*  6932 */     if (0 != pReason.x && 
/*  6933 */       remoteHconn.isReconnectable()) {
/*  6934 */       RemoteHconn rcnHconn = (RemoteHconn)hconn;
/*  6935 */       if (RemoteHconn.isReconnectableReasonCode(pReason.x) && 
/*  6936 */         !rcnHconn.hasFailed()) {
/*       */         
/*  6938 */         pCompCode.x = 0;
/*  6939 */         pReason.x = 0;
/*       */       } 
/*       */     } 
/*       */ 
/*       */ 
/*       */     
/*  6945 */     if (remoteHconn.hasFailed()) {
/*  6946 */       pCompCode.x = remoteHconn.getReconnectionFailureCompCode();
/*  6947 */       pReason.x = remoteHconn.getReconnectionFailureReason();
/*  6948 */       jmqiTls.lastException = remoteHconn.getReconnectionFailureException();
/*       */     } 
/*       */ 
/*       */     
/*  6952 */     if (Trace.isOn) {
/*  6953 */       Trace.data(this, "MQCLOSE(Hconn,Phobj,int,Pint,Pint)", "__________");
/*  6954 */       Trace.data(this, "MQCLOSE(Hconn,Phobj,int,Pint,Pint)", "MQCLOSE <<");
/*       */       
/*  6956 */       Trace.data(this, "MQCLOSE(Hconn,Phobj,int,Pint,Pint)", "Hconn", hconn);
/*  6957 */       Trace.data(this, "MQCLOSE(Hconn,Phobj,int,Pint,Pint)", "Options", "0x" + Integer.toHexString(options));
/*  6958 */       Trace.data(this, "MQCLOSE(Hconn,Phobj,int,Pint,Pint)", "Hobj", pHobj);
/*  6959 */       Trace.data(this, "MQCLOSE(Hconn,Phobj,int,Pint,Pint)", "CompCode", pCompCode);
/*  6960 */       Trace.data(this, "MQCLOSE(Hconn,Phobj,int,Pint,Pint)", "Reason", pReason);
/*       */       
/*  6962 */       Trace.data(this, "MQCLOSE(Hconn,Phobj,int,Pint,Pint)", "__________");
/*       */     } 
/*       */     
/*  6965 */     if (Trace.isOn) {
/*  6966 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQCLOSE(Hconn,Phobj,int,Pint,Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQPUT(Hconn hconn, Hobj hobj, MQMD pMQMD, MQPMO pPMO, int bufferLength, ByteBuffer buffer, Pint pCompCode, Pint pReason) {
/*  6994 */     if (Trace.isOn) {
/*  6995 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQPUT(Hconn,Hobj,MQMD,MQPMO,int,ByteBuffer,Pint,Pint)", new Object[] { hconn, hobj, pMQMD, pPMO, 
/*       */             
/*  6997 */             Integer.valueOf(bufferLength), buffer, pCompCode, pReason });
/*       */     }
/*       */     
/*  7000 */     if (Trace.isOn) {
/*  7001 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQPUT(Hconn,Hobj,MQMD,MQPMO,int,ByteBuffer,Pint,Pint)", new Object[] { hconn, hobj, pMQMD, pPMO, 
/*       */             
/*  7003 */             Integer.valueOf(bufferLength), buffer, pCompCode, pReason });
/*       */     }
/*       */     
/*  7006 */     RemoteSession session = jmqiPutMessageWithProps(hconn, hobj, null, pMQMD, pPMO, bufferLength, buffer, null, 0, null, 134, pCompCode, pReason);
/*       */ 
/*       */     
/*  7009 */     RemoteHconn remoteHconn = (RemoteHconn)hconn;
/*       */     
/*  7011 */     if (0 != pReason.x && remoteHconn.isReconnectable() && 
/*  7012 */       !threadIsReconnectThread() && 
/*  7013 */       RemoteHconn.isReconnectableReasonCode(pReason.x) && 
/*  7014 */       !remoteHconn.hasFailed()) {
/*       */       try {
/*  7016 */         remoteHconn.initiateReconnection(session);
/*       */ 
/*       */ 
/*       */         
/*  7020 */         setCompletionCodeAndReasonCodeForInterruptedMQPUT(pMQMD, pPMO, hobj, pCompCode, pReason);
/*       */       }
/*  7022 */       catch (JmqiException je) {
/*  7023 */         if (Trace.isOn) {
/*  7024 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQPUT(Hconn,Hobj,MQMD,MQPMO,int,ByteBuffer,Pint,Pint)", (Throwable)je);
/*       */         }
/*       */       } 
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  7034 */     if (remoteHconn.hasFailed()) {
/*  7035 */       pCompCode.x = remoteHconn.getReconnectionFailureCompCode();
/*  7036 */       pReason.x = remoteHconn.getReconnectionFailureReason();
/*  7037 */       RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  7038 */       JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/*  7039 */       jmqiTls.lastException = remoteHconn.getReconnectionFailureException();
/*       */     } 
/*       */     
/*  7042 */     if (Trace.isOn) {
/*  7043 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQPUT(Hconn,Hobj,MQMD,MQPMO,int,ByteBuffer,Pint,Pint)");
/*       */     }
/*       */     
/*  7046 */     if (Trace.isOn) {
/*  7047 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQPUT(Hconn,Hobj,MQMD,MQPMO,int,ByteBuffer,Pint,Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void jmqiPut(Hconn hconn, Hobj hobj, MQMD pMsgDesc, MQPMO pPutMsgOpts, ByteBuffer[] buffers, Pint pCompCode, Pint pReason) {
/*  7074 */     if (Trace.isOn) {
/*  7075 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],Pint,Pint)", new Object[] { hconn, hobj, pMsgDesc, pPutMsgOpts, buffers, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */     
/*  7079 */     if (Trace.isOn) {
/*  7080 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer[],Pint,Pint)", new Object[] { hconn, hobj, pMsgDesc, pPutMsgOpts, buffers, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */ 
/*       */     
/*  7085 */     int numBuffers = (buffers == null) ? 0 : buffers.length;
/*       */     
/*  7087 */     RemoteSession session = jmqiPutMessageWithProps(hconn, hobj, null, pMsgDesc, pPutMsgOpts, 0, null, buffers, numBuffers, null, 134, pCompCode, pReason);
/*       */ 
/*       */     
/*  7090 */     RemoteHconn remoteHconn = (RemoteHconn)hconn;
/*       */     
/*  7092 */     if (0 != pReason.x && remoteHconn.isReconnectable() && 
/*  7093 */       !threadIsReconnectThread() && 
/*  7094 */       RemoteHconn.isReconnectableReasonCode(pReason.x) && 
/*  7095 */       !remoteHconn.hasFailed()) {
/*       */       try {
/*  7097 */         remoteHconn.initiateReconnection(session);
/*       */ 
/*       */ 
/*       */         
/*  7101 */         setCompletionCodeAndReasonCodeForInterruptedMQPUT(pMsgDesc, pPutMsgOpts, hobj, pCompCode, pReason);
/*       */       
/*       */       }
/*  7104 */       catch (JmqiException je) {
/*  7105 */         if (Trace.isOn) {
/*  7106 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],Pint,Pint)", (Throwable)je);
/*       */         }
/*       */       } 
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  7116 */     if (remoteHconn.hasFailed()) {
/*  7117 */       pCompCode.x = remoteHconn.getReconnectionFailureCompCode();
/*  7118 */       pReason.x = remoteHconn.getReconnectionFailureReason();
/*  7119 */       RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  7120 */       JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/*  7121 */       jmqiTls.lastException = remoteHconn.getReconnectionFailureException();
/*       */     } 
/*       */     
/*  7124 */     if (Trace.isOn) {
/*  7125 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer[],Pint,Pint)");
/*       */     }
/*       */     
/*  7128 */     if (Trace.isOn) {
/*  7129 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],Pint,Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQPUT1(Hconn hconn, MQOD pObjDesc, MQMD pMsgDesc, MQPMO pPutMsgOpts, int BufferLength, ByteBuffer pBuffer, Pint pCompCode, Pint pReason) {
/*  7158 */     if (Trace.isOn) {
/*  7159 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQPUT1(Hconn,MQOD,MQMD,MQPMO,int,ByteBuffer,Pint,Pint)", new Object[] { hconn, pObjDesc, pMsgDesc, pPutMsgOpts, 
/*       */             
/*  7161 */             Integer.valueOf(BufferLength), pBuffer, pCompCode, pReason });
/*       */     }
/*  7163 */     if (Trace.isOn) {
/*  7164 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQPUT1(Hconn,MQOD,MQMD,MQPMO,int, ByteBuffer,Pint,Pint)", new Object[] { hconn, pObjDesc, pMsgDesc, pPutMsgOpts, 
/*       */             
/*  7166 */             Integer.valueOf(BufferLength), pBuffer, pCompCode, pReason });
/*       */     }
/*       */     
/*  7169 */     jmqiPutMessageWithProps(hconn, null, pObjDesc, pMsgDesc, pPutMsgOpts, BufferLength, pBuffer, null, 0, null, 135, pCompCode, pReason);
/*       */     
/*  7171 */     if (Trace.isOn) {
/*  7172 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQPUT1(Hconn,MQOD,MQMD,MQPMO,int, ByteBuffer,Pint,Pint)");
/*       */     }
/*       */     
/*  7175 */     if (Trace.isOn) {
/*  7176 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQPUT1(Hconn,MQOD,MQMD,MQPMO,int,ByteBuffer,Pint,Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void jmqiPut1(Hconn hconn, MQOD pObjDesc, MQMD pMsgDesc, MQPMO pPutMsgOpts, ByteBuffer[] buffers, Pint pCompCode, Pint pReason) {
/*  7203 */     if (Trace.isOn) {
/*  7204 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiPut1(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],Pint,Pint)", new Object[] { hconn, pObjDesc, pMsgDesc, pPutMsgOpts, buffers, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */ 
/*       */     
/*  7209 */     int numBuffers = (buffers == null) ? 0 : buffers.length;
/*       */     
/*  7211 */     RemoteSession session = jmqiPutMessageWithProps(hconn, null, pObjDesc, pMsgDesc, pPutMsgOpts, 0, null, buffers, numBuffers, null, 135, pCompCode, pReason);
/*       */     
/*  7213 */     RemoteHconn remoteHconn = (RemoteHconn)hconn;
/*       */     
/*  7215 */     if (0 != pReason.x && remoteHconn.isReconnectable() && 
/*  7216 */       !threadIsReconnectThread() && 
/*  7217 */       RemoteHconn.isReconnectableReasonCode(pReason.x) && 
/*  7218 */       !remoteHconn.hasFailed()) {
/*       */       try {
/*  7220 */         if (pObjDesc.getObjectQMgrName() != null && pObjDesc.getObjectQMgrName().trim().length() != 0) {
/*  7221 */           pObjDesc.setObjectQMgrName(remoteHconn.getQmName());
/*       */         }
/*  7223 */         remoteHconn.initiateReconnection(session);
/*       */ 
/*       */ 
/*       */         
/*  7227 */         setCompletionCodeAndReasonCodeForInterruptedMQPUT(pMsgDesc, pPutMsgOpts, null, pCompCode, pReason);
/*       */       
/*       */       }
/*  7230 */       catch (JmqiException je) {
/*  7231 */         if (Trace.isOn) {
/*  7232 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiPut1(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],Pint,Pint)", (Throwable)je);
/*       */         }
/*       */       } 
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  7242 */     if (remoteHconn.hasFailed()) {
/*  7243 */       pCompCode.x = remoteHconn.getReconnectionFailureCompCode();
/*  7244 */       pReason.x = remoteHconn.getReconnectionFailureReason();
/*  7245 */       RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  7246 */       JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/*  7247 */       jmqiTls.lastException = remoteHconn.getReconnectionFailureException();
/*       */     } 
/*       */     
/*  7250 */     if (Trace.isOn) {
/*  7251 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiPut1(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],Pint,Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void jmqiPutWithTriplets(Hconn hconn, Hobj hobj, MQMD pMsgDesc, MQPMO pPutMsgOpts, ByteBuffer[] buffers, Triplet[] triplets, Pint pCompCode, Pint pReason) {
/*  7283 */     if (Trace.isOn) {
/*  7284 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiPutWithTriplets(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],Triplet [ ],Pint,Pint)", new Object[] { hconn, hobj, pMsgDesc, pPutMsgOpts, buffers, triplets, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */ 
/*       */     
/*  7289 */     int numBuffers = (buffers == null) ? 0 : buffers.length;
/*       */     
/*  7291 */     RemoteSession session = jmqiPutMessageWithProps(hconn, hobj, null, pMsgDesc, pPutMsgOpts, 0, null, buffers, numBuffers, triplets, 134, pCompCode, pReason);
/*       */ 
/*       */     
/*  7294 */     RemoteHconn remoteHconn = (RemoteHconn)hconn;
/*       */     
/*  7296 */     if (0 != pReason.x && remoteHconn.isReconnectable() && 
/*  7297 */       !threadIsReconnectThread()) {
/*  7298 */       if (RemoteHconn.isReconnectableReasonCode(pReason.x) && 
/*  7299 */         !remoteHconn.hasFailed()) {
/*       */         try {
/*  7301 */           remoteHconn.initiateReconnection(session);
/*       */ 
/*       */ 
/*       */           
/*  7305 */           setCompletionCodeAndReasonCodeForInterruptedMQPUT(pMsgDesc, pPutMsgOpts, hobj, pCompCode, pReason);
/*       */         
/*       */         }
/*  7308 */         catch (JmqiException je) {
/*  7309 */           if (Trace.isOn) {
/*  7310 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiPutWithTriplets(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],Triplet [ ],Pint,Pint)", (Throwable)je);
/*       */           }
/*       */         } 
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  7318 */       if (remoteHconn.hasFailed()) {
/*  7319 */         pCompCode.x = remoteHconn.getReconnectionFailureCompCode();
/*  7320 */         pReason.x = remoteHconn.getReconnectionFailureReason();
/*  7321 */         RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  7322 */         JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/*  7323 */         jmqiTls.lastException = remoteHconn.getReconnectionFailureException();
/*       */       } 
/*       */     } 
/*       */ 
/*       */     
/*  7328 */     if (Trace.isOn) {
/*  7329 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiPutWithTriplets(Hconn,Hobj,MQMD,MQPMO,ByteBuffer[],Triplet[],Pint,Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void jmqiPut1WithTriplets(Hconn hconn, MQOD pObjDesc, MQMD pMsgDesc, MQPMO pPutMsgOpts, ByteBuffer[] buffers, Triplet[] triplets, Pint pCompCode, Pint pReason) {
/*  7361 */     if (Trace.isOn) {
/*  7362 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiPut1WithTriplets(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],Triplet [ ],Pint,Pint)", new Object[] { hconn, pObjDesc, pMsgDesc, pPutMsgOpts, buffers, triplets, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */     
/*  7366 */     if (Trace.isOn) {
/*  7367 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiPut1WithTriplets(Hconn,MQOD,MQMD,MQPMO,ByteBuffer[],Triplet[],Pint,Pint)", new Object[] { hconn, pObjDesc, pMsgDesc, pPutMsgOpts, buffers, triplets, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */ 
/*       */     
/*  7372 */     int numBuffers = (buffers == null) ? 0 : buffers.length;
/*       */     
/*  7374 */     RemoteSession session = jmqiPutMessageWithProps(hconn, null, pObjDesc, pMsgDesc, pPutMsgOpts, 0, null, buffers, numBuffers, triplets, 135, pCompCode, pReason);
/*       */ 
/*       */     
/*  7377 */     RemoteHconn remoteHconn = (RemoteHconn)hconn;
/*       */     
/*  7379 */     if (0 != pReason.x && remoteHconn.isReconnectable() && 
/*  7380 */       !threadIsReconnectThread() && 
/*  7381 */       RemoteHconn.isReconnectableReasonCode(pReason.x) && 
/*  7382 */       !remoteHconn.hasFailed()) {
/*       */       try {
/*  7384 */         remoteHconn.initiateReconnection(session);
/*  7385 */         if (pObjDesc.getObjectQMgrName() != null && pObjDesc.getObjectQMgrName().trim().length() != 0) {
/*  7386 */           pObjDesc.setObjectQMgrName(remoteHconn.getQmName());
/*       */         }
/*       */ 
/*       */ 
/*       */         
/*  7391 */         setCompletionCodeAndReasonCodeForInterruptedMQPUT(pMsgDesc, pPutMsgOpts, null, pCompCode, pReason);
/*       */       
/*       */       }
/*  7394 */       catch (JmqiException je) {
/*  7395 */         if (Trace.isOn) {
/*  7396 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiPut1WithTriplets(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],Triplet [ ],Pint,Pint)", (Throwable)je);
/*       */         }
/*       */       } 
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  7407 */     if (remoteHconn.hasFailed()) {
/*  7408 */       pCompCode.x = remoteHconn.getReconnectionFailureCompCode();
/*  7409 */       pReason.x = remoteHconn.getReconnectionFailureReason();
/*  7410 */       RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  7411 */       JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/*  7412 */       jmqiTls.lastException = remoteHconn.getReconnectionFailureException();
/*       */     } 
/*       */     
/*  7415 */     if (Trace.isOn) {
/*  7416 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiPut1WithTriplets(Hconn,MQOD,MQMD,MQPMO,ByteBuffer[],Triplet[],Pint,Pint)");
/*       */     }
/*       */ 
/*       */     
/*  7420 */     if (Trace.isOn) {
/*  7421 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiPut1WithTriplets(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],Triplet [ ],Pint,Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   private void setCompletionCodeAndReasonCodeForInterruptedMQPUT(MQMD pMQMD, MQPMO pPMO, Hobj hobj, Pint pCompCode, Pint pReason) {
/*  7481 */     if (Trace.isOn) {
/*  7482 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "setCompletionCodeAndReasonCodeForInterruptedMQPUT(MQMD,MQPMO,Hobj,Pint,Pint)", new Object[] { pMQMD, pPMO, hobj, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  7488 */     int syncpointOptions = pPMO.getOptions() & 0x6;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  7493 */     if ((syncpointOptions & 0x2) != 0) {
/*  7494 */       if (Trace.isOn) {
/*  7495 */         Trace.data(this, "setCompletionCodeAndReasonCodeForInterruptedMQPUT(MQMD,MQPMO,Hobj,Pint,Pint)", "Message was being put under syncpoint. Setting completion code to MQCC_FAILED and reason code to MQRC_BACKED_OUT");
/*       */       }
/*       */       
/*  7498 */       pCompCode.x = 2;
/*  7499 */       pReason.x = 2003;
/*       */     
/*       */     }
/*       */     else {
/*       */       
/*  7504 */       boolean failMQPUT = false;
/*       */ 
/*       */       
/*  7507 */       int persistence = pMQMD.getPersistence();
/*       */       
/*  7509 */       if (persistence == 1) {
/*       */ 
/*       */ 
/*       */         
/*  7513 */         if (Trace.isOn) {
/*  7514 */           Trace.data(this, "setCompletionCodeAndReasonCodeForInterruptedMQPUT(MQMD,MQPMO,Hobj,Pint,Pint)", "Persistent message was being put outside of a syncpoint.");
/*       */         }
/*       */         
/*  7517 */         failMQPUT = true;
/*       */       }
/*  7519 */       else if (persistence == 2 || persistence == 2) {
/*       */ 
/*       */ 
/*       */         
/*  7523 */         if (hobj == null) {
/*  7524 */           if (Trace.isOn) {
/*  7525 */             Trace.data(this, "setCompletionCodeAndReasonCodeForInterruptedMQPUT(MQMD,MQPMO,Hobj,Pint,Pint)", "Message with a default persistence was being put outside of syncpoint as part of an MQPUT1 call.");
/*       */           }
/*       */           
/*  7528 */           failMQPUT = true;
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         }
/*  7534 */         else if (hobj instanceof RemoteHobj) {
/*  7535 */           RemoteHobj rhobj = (RemoteHobj)hobj;
/*  7536 */           if (rhobj.getDefaultPersistence() == 1) {
/*       */ 
/*       */ 
/*       */ 
/*       */             
/*  7541 */             if (Trace.isOn) {
/*  7542 */               Trace.data(this, "setCompletionCodeAndReasonCodeForInterruptedMQPUT(MQMD,MQPMO,Hobj,Pint,Pint)", "Message with a default persistence was being put outside of syncpoint to a destination which had a default persistence of Persistent");
/*       */             }
/*       */ 
/*       */             
/*  7546 */             failMQPUT = true;
/*       */           
/*       */           }
/*  7549 */           else if (Trace.isOn) {
/*  7550 */             Trace.data(this, "setCompletionCodeAndReasonCodeForInterruptedMQPUT(MQMD,MQPMO,Hobj,Pint,Pint)", "Message with a default persistence was being put outside of syncpoint to a destination which had a default persistence of Not Persistent");
/*       */           
/*       */           }
/*       */ 
/*       */         
/*       */         }
/*       */         else {
/*       */ 
/*       */           
/*  7559 */           if (Trace.isOn) {
/*  7560 */             Trace.data(this, "setCompletionCodeAndReasonCodeForInterruptedMQPUT(MQMD,MQPMO,Hobj,Pint,Pint)", "Message with a default persistence was being put outside of syncpoint. It was not possible to determine the default persistence of the destination");
/*       */           }
/*       */ 
/*       */           
/*  7564 */           failMQPUT = true;
/*       */ 
/*       */         
/*       */         }
/*       */ 
/*       */       
/*       */       }
/*  7571 */       else if (Trace.isOn) {
/*  7572 */         Trace.data(this, "setCompletionCodeAndReasonCodeForInterruptedMQPUT(MQMD,MQPMO,Hobj,Pint,Pint)", "Non persistent message was being put outside of syncpoint. ");
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  7578 */       if (failMQPUT) {
/*  7579 */         if (Trace.isOn) {
/*  7580 */           Trace.data(this, "setCompletionCodeAndReasonCodeForInterruptedMQPUT(MQMD,MQPMO,Hobj,Pint,Pint)", "Setting completion code to MQCC_FAILED and reason code to MQRC_CALL_INTERRUPTED");
/*       */         }
/*       */         
/*  7583 */         pCompCode.x = 2;
/*  7584 */         pReason.x = 2549;
/*       */       } else {
/*       */         
/*  7587 */         if (Trace.isOn) {
/*  7588 */           Trace.data(this, "setCompletionCodeAndReasonCodeForInterruptedMQPUT(MQMD,MQPMO,Hobj,Pint,Pint)", "Setting completion code to MQCC_NONE and reason code to MQRC_OK");
/*       */         }
/*       */         
/*  7591 */         pCompCode.x = 0;
/*  7592 */         pReason.x = 0;
/*       */       } 
/*       */     } 
/*       */     
/*  7596 */     if (Trace.isOn) {
/*  7597 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "setCompletionCodeAndReasonCodeForInterruptedMQPUT(MQMD,MQPMO,Hobj,Pint,Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   private int writeMPH(Triplet[] triplets, RfpMQAPI sMQAPI, int offset, boolean swap, JmqiCodepage cp, JmqiTls jmqiTls) throws JmqiException {
/*  7617 */     if (Trace.isOn) {
/*  7618 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "writeMPH(Triplet [ ],RfpMQAPI,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { triplets, sMQAPI, 
/*       */             
/*  7620 */             Integer.valueOf(offset), Boolean.valueOf(swap), cp, jmqiTls });
/*       */     }
/*  7622 */     byte[] commsBuff = sMQAPI.getRfpBuffer();
/*  7623 */     int pos = offset;
/*       */ 
/*       */     
/*       */     try {
/*  7627 */       RfpMPH mph = (RfpMPH)RfpStructure.newRfp(this.env, 22, commsBuff, offset);
/*       */       
/*  7629 */       mph.setTriplets(triplets);
/*       */       
/*  7631 */       pos = mph.writeToBuffer(4, swap, cp, jmqiTls);
/*       */     }
/*  7633 */     catch (ArrayIndexOutOfBoundsException aiooe) {
/*  7634 */       if (Trace.isOn) {
/*  7635 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "writeMPH(Triplet [ ],RfpMQAPI,int,boolean,JmqiCodepage,JmqiTls)", aiooe);
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  7651 */       int newSize = 2 * commsBuff.length;
/*  7652 */       byte[] newBuffer = new byte[newSize];
/*  7653 */       System.arraycopy(commsBuff, 0, newBuffer, 0, offset);
/*  7654 */       sMQAPI.setRfpBuffer(newBuffer);
/*       */ 
/*       */       
/*  7657 */       pos = writeMPH(triplets, sMQAPI, offset, swap, cp, jmqiTls);
/*       */     } 
/*       */     
/*  7660 */     if (Trace.isOn) {
/*  7661 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "writeMPH(Triplet [ ],RfpMQAPI,int,boolean,JmqiCodepage,JmqiTls)", 
/*  7662 */           Integer.valueOf(pos));
/*       */     }
/*  7664 */     return pos;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   private int readMPH(PtripletArray pTriplets, RfpMQAPI rMQAPI, int readPos, boolean swap, JmqiCodepage cp, JmqiTls jmqiTls, RemoteSession remoteSession, RemoteTls tls) throws JmqiException {
/*  7683 */     if (Trace.isOn) {
/*  7684 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "readMPH(PtripletArray,RfpMQAPI,int,boolean,JmqiCodepage,JmqiTls,RemoteSession,RemoteTls)", new Object[] { pTriplets, rMQAPI, 
/*       */             
/*  7686 */             Integer.valueOf(readPos), Boolean.valueOf(swap), cp, jmqiTls, remoteSession, tls });
/*       */     }
/*       */     
/*  7689 */     byte[] commsBuff = rMQAPI.getRfpBuffer();
/*  7690 */     int pos = readPos;
/*       */     
/*  7692 */     int remainingLength = rMQAPI.getTransLength() - readPos;
/*       */     
/*  7694 */     RfpMPH mph = (RfpMPH)RfpStructure.newRfp(this.env, 22, commsBuff, readPos);
/*       */     
/*  7696 */     int mphLength = mph.getMPHLength(4, swap, cp, jmqiTls);
/*  7697 */     if (mphLength <= remainingLength) {
/*       */ 
/*       */       
/*  7700 */       pos = mph.readFromBuffer(4, swap, cp, jmqiTls);
/*       */ 
/*       */ 
/*       */     
/*       */     }
/*       */     else {
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  7710 */       byte[] msgPropsBuffer = new byte[mphLength];
/*  7711 */       int msgDataToReceive = mphLength;
/*       */       
/*  7713 */       System.arraycopy(commsBuff, readPos, msgPropsBuffer, 0, remainingLength);
/*  7714 */       int msgDataReceived = remainingLength;
/*       */       
/*  7716 */       boolean moreToReceive = true;
/*  7717 */       while (moreToReceive) {
/*  7718 */         RfpTSH rTSH = remoteSession.receiveNextGetReplyTSH(tls);
/*  7719 */         byte[] tshBuffer = rTSH.getRfpBuffer();
/*  7720 */         int startOffset = rTSH.getRfpOffset() + rTSH.hdrSize();
/*  7721 */         int dataLen = rTSH.getTransLength() - rTSH.hdrSize();
/*  7722 */         int dueLen = msgDataToReceive - msgDataReceived;
/*       */         
/*  7724 */         int readLen = (dueLen <= dataLen) ? dueLen : dataLen;
/*  7725 */         System.arraycopy(tshBuffer, startOffset, msgPropsBuffer, msgDataReceived, readLen);
/*  7726 */         msgDataReceived += readLen;
/*  7727 */         moreToReceive = (msgDataReceived < msgDataToReceive);
/*  7728 */         if (moreToReceive) {
/*       */           
/*  7730 */           remoteSession.releaseReceivedTSH(rTSH);
/*       */ 
/*       */ 
/*       */           
/*       */           continue;
/*       */         } 
/*       */ 
/*       */         
/*  7738 */         pos = startOffset + readLen;
/*  7739 */         rMQAPI.setRfpBuffer(rTSH.getRfpBuffer());
/*  7740 */         rMQAPI.setRfpOffset(rTSH.getRfpOffset());
/*  7741 */         rMQAPI.setTransLength(rTSH.getTransLength());
/*       */       } 
/*       */ 
/*       */       
/*  7745 */       if (msgDataReceived != msgDataToReceive) {
/*  7746 */         HashMap<String, Object> info = new HashMap<>();
/*  7747 */         info.put("DataReceived", Integer.valueOf(msgDataReceived));
/*  7748 */         info.put("DataToReceive", Integer.valueOf(msgDataToReceive));
/*  7749 */         info.put("Description", "Incorrect length of data received");
/*  7750 */         Trace.ffst(this, "spiUnsubscribe(Hconn,LpiUSD,Pint,Pint)", "01", info, null);
/*  7751 */         JmqiException e = new JmqiException(this.env, -1, null, 2, 2195, null);
/*       */ 
/*       */         
/*  7754 */         if (Trace.isOn) {
/*  7755 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "readMPH(PtripletArray,RfpMQAPI,int,boolean,JmqiCodepage,JmqiTls,RemoteSession,RemoteTls)", (Throwable)e);
/*       */         }
/*       */ 
/*       */         
/*  7759 */         throw e;
/*       */       } 
/*       */ 
/*       */       
/*  7763 */       mph = (RfpMPH)RfpStructure.newRfp(this.env, 22, msgPropsBuffer, 0);
/*       */ 
/*       */       
/*  7766 */       mph.readFromBuffer(4, swap, cp, jmqiTls);
/*       */     } 
/*       */ 
/*       */     
/*  7770 */     Triplet[] triplets = mph.getTriplets();
/*  7771 */     pTriplets.setTriplets(triplets);
/*       */     
/*  7773 */     if (Trace.isOn) {
/*  7774 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "readMPH(PtripletArray,RfpMQAPI,int,boolean,JmqiCodepage,JmqiTls,RemoteSession,RemoteTls)", 
/*       */           
/*  7776 */           Integer.valueOf(pos));
/*       */     }
/*  7778 */     return pos;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   private RemoteSession jmqiPutMessageWithProps(Hconn hconn, Hobj hobjP, MQOD pODP, MQMD pMQMD, MQPMO pPMO_orig, int sbLength, ByteBuffer sBuff, ByteBuffer[] mBuffs, int numBuffs, Triplet[] triplets, int flowType, Pint pCompCode, Pint pReason) {
/*  7817 */     if (Trace.isOn) {
/*  7818 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)", new Object[] { hconn, hobjP, pODP, pMQMD, pPMO_orig, 
/*       */             
/*  7820 */             Integer.valueOf(sbLength), sBuff, mBuffs, 
/*  7821 */             Integer.valueOf(numBuffs), triplets, Integer.valueOf(flowType), pCompCode, pReason });
/*       */     }
/*       */     
/*  7824 */     if (Trace.isOn) {
/*  7825 */       Trace.data(this, "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)", "__________");
/*       */ 
/*       */       
/*  7828 */       Trace.data(this, "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)", "jmqiPutMessageWithProps >>");
/*       */ 
/*       */       
/*  7831 */       Trace.data(this, "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)", "Hconn", hconn);
/*       */ 
/*       */       
/*  7834 */       Trace.data(this, "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)", "Hobj", hobjP);
/*       */ 
/*       */       
/*  7837 */       Trace.data(this, "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)", "MQOD", pODP);
/*       */ 
/*       */       
/*  7840 */       Trace.data(this, "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)", "MQMD", pMQMD);
/*       */ 
/*       */       
/*  7843 */       Trace.data(this, "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)", "MQPMO", pPMO_orig);
/*       */ 
/*       */       
/*  7846 */       if (sBuff != null) {
/*  7847 */         Trace.data(this, "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)", "Bufferlength", 
/*       */             
/*  7849 */             Integer.valueOf(sbLength));
/*  7850 */         JmqiTools.traceApiBuffer(this, "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)", sBuff, sbLength);
/*       */       
/*       */       }
/*       */       else {
/*       */         
/*  7855 */         Trace.data(this, "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)", "NumBuffers", 
/*       */             
/*  7857 */             Integer.valueOf(numBuffs));
/*  7858 */         if (mBuffs != null) {
/*  7859 */           for (int i = 0; i < mBuffs.length; i++) {
/*  7860 */             JmqiTools.traceApiBuffer(this, "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)", mBuffs[i], mBuffs[i]
/*       */                 
/*  7862 */                 .limit());
/*       */           }
/*       */         }
/*       */       } 
/*  7866 */       Trace.data(this, "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)", "flowType", 
/*       */           
/*  7868 */           Integer.valueOf(flowType));
/*  7869 */       Trace.data(this, "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)", "MessageProperties", triplets);
/*       */ 
/*       */       
/*  7872 */       Trace.data(this, "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)", "CompCode", pCompCode);
/*       */ 
/*       */       
/*  7875 */       Trace.data(this, "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)", "Reason", pReason);
/*       */ 
/*       */       
/*  7878 */       Trace.data(this, "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)", "__________");
/*       */     } 
/*       */ 
/*       */     
/*  7882 */     MQPMO pPMO = (MQPMO)pPMO_orig.clone();
/*  7883 */     MQOD pOD = pODP;
/*  7884 */     Hobj hobj = hobjP;
/*  7885 */     RemoteHconn remoteHconn = null;
/*  7886 */     RemoteSession remoteSession = null;
/*  7887 */     RfpMQAPI rMQAPI = null;
/*       */     
/*  7889 */     RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  7890 */     JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/*  7891 */     jmqiTls.lastException = null;
/*  7892 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*       */ 
/*       */     
/*  7895 */     if (pOD != null) {
/*  7896 */       pOD.getObjectString().setRemote(true);
/*  7897 */       pOD.getSelectionString().setRemote(true);
/*  7898 */       pOD.getResObjectString().setRemote(true);
/*       */     } 
/*       */     try {
/*  7901 */       remoteHconn = getRemoteHconn(tls, hconn);
/*       */ 
/*       */ 
/*       */       
/*  7905 */       if (remoteHconn.isReconnectable() && (
/*  7906 */         pPMO.getOptions() & 0x8000) != 0) {
/*  7907 */         JmqiException je = new JmqiException(this.env, -1, null, 2, 2547, null);
/*       */ 
/*       */         
/*  7910 */         if (Trace.isOn) {
/*  7911 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)", (Throwable)je, 1);
/*       */         }
/*       */ 
/*       */         
/*  7915 */         throw je;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  7921 */       if (flowType != 134 && flowType != 135) {
/*  7922 */         JmqiException ex = new JmqiException(this.env, -1, null, 2, 2195, null);
/*       */         
/*  7924 */         if (Trace.isOn) {
/*  7925 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)", (Throwable)ex, 2);
/*       */         }
/*       */ 
/*       */         
/*  7929 */         throw ex;
/*       */       } 
/*       */       
/*  7932 */       if (flowType == 134) {
/*       */ 
/*       */         
/*  7935 */         pOD = null;
/*       */ 
/*       */         
/*  7938 */         if (hobj == null || !(hobj instanceof RemoteHobj)) {
/*  7939 */           JmqiException ex = new JmqiException(this.env, -1, null, 2, 2019, null);
/*       */ 
/*       */           
/*  7942 */           if (Trace.isOn) {
/*  7943 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)", (Throwable)ex, 3);
/*       */           }
/*       */ 
/*       */           
/*  7947 */           throw ex;
/*       */         }
/*       */       
/*       */       }
/*       */       else {
/*       */         
/*  7953 */         hobj = null;
/*       */ 
/*       */         
/*  7956 */         if (pOD == null) {
/*  7957 */           JmqiException ex = new JmqiException(this.env, -1, null, 2, 2044, null);
/*       */           
/*  7959 */           if (Trace.isOn) {
/*  7960 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)", (Throwable)ex, 4);
/*       */           }
/*       */ 
/*       */           
/*  7964 */           throw ex;
/*       */         } 
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  7971 */       remoteHconn.enterCall(tls.isDispatchThread, false);
/*       */       
/*       */       try {
/*  7974 */         remoteSession = remoteHconn.getSession();
/*       */         
/*  7976 */         JmqiCodepage cp = remoteSession.getCp();
/*  7977 */         boolean swap = remoteSession.isSwap();
/*       */         
/*  7979 */         remoteSession.checkIfDisconnected();
/*       */ 
/*       */         
/*  7982 */         if ((pPMO.getOptions() & 0x2) != 0) {
/*  7983 */           remoteHconn.setInTransaction();
/*       */         }
/*       */ 
/*       */         
/*  7987 */         RemoteHobj remoteHobj = (RemoteHobj)hobj;
/*       */ 
/*       */         
/*  7990 */         int msgSize = 0;
/*       */         
/*  7992 */         if (sBuff != null) {
/*  7993 */           if (!sBuff.hasArray() || sBuff.capacity() < sbLength) {
/*  7994 */             JmqiException ex = new JmqiException(this.env, -1, null, 2, 2010, null);
/*       */             
/*  7996 */             if (Trace.isOn) {
/*  7997 */               Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)", (Throwable)ex, 5);
/*       */             }
/*       */ 
/*       */             
/*  8001 */             throw ex;
/*       */           } 
/*       */           
/*  8004 */           msgSize = sbLength;
/*       */         }
/*  8006 */         else if (mBuffs != null) {
/*       */ 
/*       */           
/*  8009 */           for (int i = 0; i < numBuffs; i++) {
/*  8010 */             ByteBuffer buffer = mBuffs[i];
/*       */             
/*  8012 */             if (buffer == null) {
/*  8013 */               JmqiException ex = new JmqiException(this.env, -1, null, 2, 2004, null);
/*       */               
/*  8015 */               if (Trace.isOn) {
/*  8016 */                 Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)", (Throwable)ex, 6);
/*       */               }
/*       */ 
/*       */               
/*  8020 */               throw ex;
/*       */             } 
/*       */             
/*  8023 */             msgSize += mBuffs[i].remaining();
/*       */           } 
/*       */         } 
/*       */ 
/*       */         
/*  8028 */         if (pMQMD == null) {
/*  8029 */           JmqiException ex = new JmqiException(this.env, -1, null, 2, 2026, null);
/*       */           
/*  8031 */           if (Trace.isOn) {
/*  8032 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)", (Throwable)ex, 7);
/*       */           }
/*       */ 
/*       */           
/*  8036 */           throw ex;
/*       */         } 
/*       */ 
/*       */         
/*  8040 */         if (msgSize > remoteSession.getMaximumMessageLength()) {
/*  8041 */           JmqiException ex = new JmqiException(this.env, -1, null, 2, 2010, null);
/*       */           
/*  8043 */           if (Trace.isOn) {
/*  8044 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)", (Throwable)ex, 8);
/*       */           }
/*       */ 
/*       */           
/*  8048 */           throw ex;
/*       */         } 
/*  8050 */         if (msgSize < 0) {
/*  8051 */           JmqiException ex = new JmqiException(this.env, -1, null, 2, 2005, null);
/*       */           
/*  8053 */           if (Trace.isOn) {
/*  8054 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)", (Throwable)ex, 9);
/*       */           }
/*       */ 
/*       */           
/*  8058 */           throw ex;
/*       */         } 
/*       */         
/*  8061 */         handleUndefinedCcsid(pMQMD);
/*       */ 
/*       */ 
/*       */         
/*  8065 */         if (pPMO.getVersion() < 2 && remoteSession.isDistListCapable()) {
/*       */ 
/*       */           
/*  8068 */           pPMO.setVersion(2);
/*  8069 */           pPMO.setPutMsgRecFields(0);
/*  8070 */           pPMO.setRecsPresent(0);
/*  8071 */           pPMO.setPutMsgRecords(null);
/*  8072 */           pPMO.setResponseRecords(null);
/*       */         }
/*  8074 */         else if (pPMO.getVersion() == 2 && !remoteSession.isDistListCapable()) {
/*       */ 
/*       */           
/*  8077 */           pPMO.setVersion(1);
/*       */         } 
/*       */ 
/*       */         
/*  8081 */         int originalPutOpts = pPMO.getOptions();
/*       */ 
/*       */         
/*  8084 */         boolean synchronousCall = true;
/*       */         
/*  8086 */         int fapLevel = remoteHconn.getFapLevel();
/*  8087 */         if (fapLevel >= 9 && remoteHconn.isSPISupported())
/*       */         {
/*       */           
/*  8090 */           synchronousCall = querySyncDelivery(tls, remoteHconn, remoteHobj, pPMO, pMQMD);
/*       */         }
/*       */ 
/*       */         
/*  8094 */         RfpMQAPI sMQAPI = remoteSession.allocateMQAPI(flowType);
/*       */         
/*  8096 */         if (flowType == 134) {
/*  8097 */           sMQAPI.setHandle(remoteHobj.getIntegerHandle(), swap);
/*       */         } else {
/*       */           
/*  8100 */           sMQAPI.setHandle(-1, swap);
/*       */         } 
/*       */ 
/*       */         
/*  8104 */         boolean setId = ((originalPutOpts & 0xC00) != 0);
/*  8105 */         boolean setOrig = ((originalPutOpts & 0x800) != 0);
/*       */         
/*  8107 */         byte[] commsBuff = sMQAPI.getRfpBuffer();
/*  8108 */         int writePos = sMQAPI.getRfpOffset() + sMQAPI.hdrSize();
/*       */ 
/*       */         
/*  8111 */         if (flowType == 135)
/*       */         {
/*  8113 */           writePos = pOD.writeToBuffer(commsBuff, writePos, 4, swap, cp, jmqiTls);
/*       */         }
/*       */ 
/*       */         
/*  8117 */         writePos = pMQMD.writeToBuffer(commsBuff, writePos, false, setId, setOrig, 4, swap, cp, jmqiTls);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  8139 */         boolean writeMPH = false;
/*  8140 */         if (triplets != null && fapLevel >= 9) {
/*  8141 */           int pmoVersion = pPMO.getVersion();
/*  8142 */           if (pmoVersion >= 3) {
/*  8143 */             writeMPH = true;
/*  8144 */             pPMO.setOriginalMsgHandle(1L);
/*  8145 */             pPMO.setNewMsgHandle(0L);
/*       */           } 
/*       */         } 
/*       */ 
/*       */         
/*  8150 */         writePos = pPMO.writeToBuffer(commsBuff, writePos, 4, swap, cp, jmqiTls);
/*       */ 
/*       */         
/*  8153 */         if (writeMPH) {
/*       */           
/*  8155 */           writePos = writeMPH(triplets, sMQAPI, writePos, swap, cp, jmqiTls);
/*       */ 
/*       */           
/*  8158 */           commsBuff = sMQAPI.getRfpBuffer();
/*       */         } 
/*       */ 
/*       */         
/*  8162 */         dc.writeI32(msgSize, commsBuff, writePos, swap);
/*  8163 */         writePos += 4;
/*       */ 
/*       */         
/*  8166 */         int headerLength = writePos - sMQAPI.getRfpOffset();
/*  8167 */         sMQAPI.setTransLength(headerLength);
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  8172 */         int callLength = (flowType == 134) ? (headerLength + msgSize) : (headerLength + msgSize + pOD.getResObjectString().getVsBufSize() + pOD.getSelectionString().getVsBufSize());
/*  8173 */         sMQAPI.setCallLength(callLength);
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  8178 */         if (sBuff != null) {
/*  8179 */           sMQAPI.setUserDataSingle(sBuff.array(), sbLength);
/*       */         } else {
/*       */           
/*  8182 */           sMQAPI.setUserDataSingle(null, 0);
/*       */         } 
/*       */         
/*  8185 */         if (mBuffs != null) {
/*  8186 */           sMQAPI.setUserDataMulti(mBuffs, numBuffs);
/*       */         } else {
/*       */           
/*  8189 */           sMQAPI.setUserDataMulti(null, 0);
/*       */         } 
/*       */ 
/*       */         
/*  8193 */         remoteSession.sendTSH(tls, (RfpTSH)sMQAPI);
/*       */ 
/*       */         
/*  8196 */         if (synchronousCall) {
/*       */           
/*  8198 */           rMQAPI = remoteSession.receiveMQIFlow(tls);
/*       */           
/*  8200 */           commsBuff = rMQAPI.getRfpBuffer();
/*       */           
/*  8202 */           int readPos = rMQAPI.getRfpOffset() + rMQAPI.hdrSize();
/*       */ 
/*       */           
/*  8205 */           int expectedReply = (flowType == 134) ? 150 : 151;
/*       */ 
/*       */ 
/*       */           
/*  8209 */           if (rMQAPI.getSegmentType() != expectedReply) {
/*  8210 */             HashMap<String, Object> info = new HashMap<>();
/*  8211 */             info.put("SegmentType", Integer.valueOf(rMQAPI.getSegmentType()));
/*  8212 */             info.put("ControlFlags1", Integer.valueOf(rMQAPI.getControlFlags1()));
/*  8213 */             info.put("Description", "Unexpected flow received from server");
/*  8214 */             Trace.ffst(this, "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)", "01", info, null);
/*       */ 
/*       */             
/*  8217 */             JmqiException ex = new JmqiException(this.env, -1, null, 2, 2195, null);
/*       */             
/*  8219 */             if (Trace.isOn) {
/*  8220 */               Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)", (Throwable)ex, 10);
/*       */             }
/*       */ 
/*       */             
/*  8224 */             throw ex;
/*       */           } 
/*       */ 
/*       */ 
/*       */           
/*  8229 */           swap = remoteSession.isSwap();
/*       */ 
/*       */           
/*  8232 */           pCompCode.x = rMQAPI.getCompCode(swap);
/*  8233 */           pReason.x = rMQAPI.getReasonCode(swap);
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  8238 */           if (pCompCode.x != 2)
/*       */           {
/*  8240 */             if (flowType == 135)
/*       */             {
/*  8242 */               readPos = pOD.readFromBuffer(commsBuff, readPos, 4, swap, cp, jmqiTls);
/*       */             }
/*       */ 
/*       */             
/*  8246 */             readPos = pMQMD.readFromBuffer(commsBuff, readPos, true, setId, setOrig, 4, swap, cp, jmqiTls);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */             
/*  8252 */             readPos = pPMO.readFromBuffer(commsBuff, readPos, 4, swap, cp, jmqiTls);
/*       */           
/*       */           }
/*       */         
/*       */         }
/*       */         else {
/*       */           
/*  8259 */           pCompCode.x = 0;
/*  8260 */           pReason.x = 0;
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  8266 */         if (pPMO != null) {
/*  8267 */           pPMO.setOptions(originalPutOpts);
/*       */           
/*  8269 */           pPMO_orig.populateFields(pPMO);
/*       */         }
/*       */       
/*       */       }
/*       */       finally {
/*       */         
/*  8275 */         if (Trace.isOn) {
/*  8276 */           Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)");
/*       */         }
/*       */         
/*  8279 */         if (pOD != null) {
/*  8280 */           pOD.getObjectString().setRemote(false);
/*  8281 */           pOD.getSelectionString().setRemote(false);
/*  8282 */           pOD.getResObjectString().setRemote(false);
/*       */         } 
/*       */ 
/*       */ 
/*       */         
/*       */         try {
/*  8288 */           if (remoteHconn != null) {
/*  8289 */             if (rMQAPI != null) {
/*       */               try {
/*  8291 */                 remoteSession.releaseReceivedTSH((RfpTSH)rMQAPI);
/*       */               }
/*  8293 */               catch (JmqiException e) {
/*  8294 */                 if (Trace.isOn) {
/*  8295 */                   Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)", (Throwable)e, 1);
/*       */                 }
/*       */               } 
/*       */             }
/*       */ 
/*       */ 
/*       */ 
/*       */             
/*  8303 */             remoteHconn.leaveCall(pReason.x);
/*       */           }
/*       */         
/*  8306 */         } catch (JmqiException e) {
/*  8307 */           if (Trace.isOn) {
/*  8308 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)", (Throwable)e, 2);
/*       */           }
/*       */         }
/*       */       
/*       */       }
/*       */     
/*       */     }
/*  8315 */     catch (JmqiException e) {
/*  8316 */       if (Trace.isOn) {
/*  8317 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)", (Throwable)e, 3);
/*       */       }
/*       */ 
/*       */ 
/*       */       
/*  8322 */       jmqiTls.lastException = e;
/*  8323 */       pCompCode.x = e.getCompCode();
/*  8324 */       pReason.x = e.getReason();
/*       */     } 
/*       */ 
/*       */     
/*  8328 */     if (Trace.isOn) {
/*  8329 */       Trace.data(this, "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)", "__________");
/*       */ 
/*       */       
/*  8332 */       Trace.data(this, "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)", "jmqiPutMessageWithProps <<");
/*       */ 
/*       */ 
/*       */       
/*  8336 */       Trace.data(this, "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)", "Hconn", hconn);
/*       */ 
/*       */       
/*  8339 */       Trace.data(this, "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)", "Hobj", hobj);
/*       */ 
/*       */       
/*  8342 */       Trace.data(this, "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)", "MQOD", pOD);
/*       */ 
/*       */       
/*  8345 */       Trace.data(this, "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)", "Msgdesc", pMQMD);
/*       */ 
/*       */       
/*  8348 */       Trace.data(this, "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)", "Putmsgopts", pPMO);
/*       */ 
/*       */       
/*  8351 */       Trace.data(this, "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)", "Bufferlength", "input");
/*       */ 
/*       */       
/*  8354 */       Trace.data(this, "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)", "flowType", 
/*       */           
/*  8356 */           Integer.valueOf(flowType));
/*  8357 */       Trace.data(this, "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)", "MessageProperties", triplets);
/*       */ 
/*       */       
/*  8360 */       Trace.data(this, "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)", "CompCode", pCompCode);
/*       */ 
/*       */       
/*  8363 */       Trace.data(this, "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)", "Reason", pReason);
/*       */ 
/*       */ 
/*       */       
/*  8367 */       Trace.data(this, "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)", "__________");
/*       */     } 
/*       */ 
/*       */ 
/*       */     
/*  8372 */     if (Trace.isOn) {
/*  8373 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiPutMessageWithProps(Hconn,Hobj,MQOD,MQMD,MQPMO,int,ByteBuffer,ByteBuffer [ ],int,Triplet [ ],int,Pint,Pint)", remoteSession);
/*       */     }
/*       */ 
/*       */     
/*  8377 */     return remoteSession;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQGET(Hconn hconn, Hobj hobj, MQMD msgDesc, MQGMO getMsgOpts, int bufferLength, ByteBuffer buffer, Pint pDataLength, Pint pCompCode, Pint pReason) {
/*  8406 */     if (Trace.isOn) {
/*  8407 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQGET(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,Pint,Pint)", new Object[] { hconn, hobj, msgDesc, getMsgOpts, 
/*       */             
/*  8409 */             Integer.valueOf(bufferLength), buffer, pDataLength, pCompCode, pReason });
/*       */     }
/*  8411 */     if (Trace.isOn) {
/*  8412 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQGET(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,Pint)", new Object[] { hconn, hobj, msgDesc, getMsgOpts, 
/*       */             
/*  8414 */             Integer.valueOf(bufferLength), buffer, pCompCode, pReason });
/*       */     }
/*       */     
/*  8417 */     RemoteSession session = jmqiGetMessageWithProps(hconn, hobj, msgDesc, getMsgOpts, bufferLength, buffer, pDataLength, null, pCompCode, pReason);
/*       */ 
/*       */     
/*  8420 */     RemoteHconn remoteHconn = (RemoteHconn)hconn;
/*       */     
/*  8422 */     if (0 != pReason.x && remoteHconn.isReconnectable() && 
/*  8423 */       !threadIsReconnectThread() && 
/*  8424 */       RemoteHconn.isReconnectableReasonCode(pReason.x) && 
/*  8425 */       !remoteHconn.hasFailed()) {
/*       */       try {
/*  8427 */         remoteHconn.initiateReconnection(session);
/*  8428 */         MQGET(hconn, hobj, msgDesc, getMsgOpts, bufferLength, buffer, pDataLength, pCompCode, pReason);
/*       */       
/*       */       }
/*  8431 */       catch (JmqiException je) {
/*  8432 */         if (Trace.isOn) {
/*  8433 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQGET(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,Pint,Pint)", (Throwable)je);
/*       */         }
/*       */       } 
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  8443 */     if (remoteHconn.hasFailed()) {
/*  8444 */       pCompCode.x = remoteHconn.getReconnectionFailureCompCode();
/*  8445 */       pReason.x = remoteHconn.getReconnectionFailureReason();
/*  8446 */       RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  8447 */       JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/*  8448 */       jmqiTls.lastException = remoteHconn.getReconnectionFailureException();
/*       */     } 
/*       */     
/*  8451 */     if (Trace.isOn) {
/*  8452 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQGET(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,Pint)");
/*       */     }
/*       */ 
/*       */     
/*  8456 */     if (Trace.isOn) {
/*  8457 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQGET(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,Pint,Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   private RemoteSession jmqiGetMessageWithProps(Hconn hconn, Hobj hobj, MQMD msgDesc, MQGMO getMsgOpts, int bufferLength, ByteBuffer buffer, Pint pDataLength, PtripletArray pTriplets, Pint pCompCode, Pint pReason) {
/*  8494 */     if (Trace.isOn) {
/*  8495 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiGetMessageWithProps(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,PtripletArray,Pint,Pint)", new Object[] { hconn, hobj, msgDesc, getMsgOpts, 
/*       */             
/*  8497 */             Integer.valueOf(bufferLength), buffer, pDataLength, pTriplets, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */     
/*  8501 */     if (Trace.isOn) {
/*  8502 */       Trace.data(this, "jmqiGetMessageWithProps(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,PtripletArray,Pint,Pint)", "__________");
/*       */ 
/*       */       
/*  8505 */       Trace.data(this, "jmqiGetMessageWithProps(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,PtripletArray,Pint,Pint)", "jmqiGetMessageWithProps >>");
/*       */ 
/*       */       
/*  8508 */       Trace.data(this, "jmqiGetMessageWithProps(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,PtripletArray,Pint,Pint)", "Hconn", hconn);
/*       */ 
/*       */       
/*  8511 */       Trace.data(this, "jmqiGetMessageWithProps(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,PtripletArray,Pint,Pint)", "Hobj", hobj);
/*       */ 
/*       */       
/*  8514 */       Trace.data(this, "jmqiGetMessageWithProps(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,PtripletArray,Pint,Pint)", "Msgdesc", msgDesc);
/*       */ 
/*       */       
/*  8517 */       Trace.data(this, "jmqiGetMessageWithProps(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,PtripletArray,Pint,Pint)", "Getmsgops", getMsgOpts);
/*       */ 
/*       */       
/*  8520 */       Trace.data(this, "jmqiGetMessageWithProps(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,PtripletArray,Pint,Pint)", "Bufferlength", 
/*       */           
/*  8522 */           Integer.toString(bufferLength));
/*  8523 */       Trace.data(this, "jmqiGetMessageWithProps(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,PtripletArray,Pint,Pint)", "Datalength", pDataLength);
/*       */ 
/*       */       
/*  8526 */       Trace.data(this, "jmqiGetMessageWithProps(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,PtripletArray,Pint,Pint)", "MessageProperties", (pTriplets == null) ? null : pTriplets
/*       */           
/*  8528 */           .getTriplets());
/*  8529 */       Trace.data(this, "jmqiGetMessageWithProps(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,PtripletArray,Pint,Pint)", "CompCode", pCompCode);
/*       */ 
/*       */       
/*  8532 */       Trace.data(this, "jmqiGetMessageWithProps(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,PtripletArray,Pint,Pint)", "Reason", pReason);
/*       */ 
/*       */       
/*  8535 */       Trace.data(this, "jmqiGetMessageWithProps(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,PtripletArray,Pint,Pint)", "__________");
/*       */     } 
/*       */ 
/*       */     
/*  8539 */     RemoteHconn remoteHconn = null;
/*  8540 */     RemoteSession remoteSession = null;
/*  8541 */     RfpMQAPI rMQAPI = null;
/*       */     
/*  8543 */     RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  8544 */     JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/*  8545 */     jmqiTls.lastException = null;
/*  8546 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*       */     try {
/*  8548 */       remoteHconn = getRemoteHconn(tls, hconn);
/*  8549 */       RemoteConnection parentConn = remoteHconn.getParentConnection();
/*       */ 
/*       */ 
/*       */       
/*  8553 */       if (remoteHconn.isReconnectable() && (
/*  8554 */         getMsgOpts.getOptions() & 0x8000) != 0) {
/*  8555 */         JmqiException je = new JmqiException(this.env, -1, null, 2, 2547, null);
/*       */ 
/*       */         
/*  8558 */         if (Trace.isOn) {
/*  8559 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiGetMessageWithProps(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,PtripletArray,Pint,Pint)", (Throwable)je, 1);
/*       */         }
/*       */ 
/*       */         
/*  8563 */         throw je;
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/*  8568 */       if (hobj == null || !(hobj instanceof RemoteHobj)) {
/*       */         
/*  8570 */         JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2019, null);
/*       */ 
/*       */         
/*  8573 */         if (Trace.isOn) {
/*  8574 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiGetMessageWithProps(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,PtripletArray,Pint,Pint)", (Throwable)traceRet1, 2);
/*       */         }
/*       */ 
/*       */         
/*  8578 */         throw traceRet1;
/*       */       } 
/*       */       
/*  8581 */       RemoteHobj remoteHobj = (RemoteHobj)hobj;
/*       */ 
/*       */       
/*  8584 */       if (bufferLength < 0) {
/*  8585 */         JmqiException traceRet2 = new JmqiException(this.env, -1, null, 2, 2005, null);
/*       */         
/*  8587 */         if (Trace.isOn) {
/*  8588 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiGetMessageWithProps(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,PtripletArray,Pint,Pint)", (Throwable)traceRet2, 3);
/*       */         }
/*       */ 
/*       */         
/*  8592 */         throw traceRet2;
/*       */       } 
/*       */ 
/*       */       
/*  8596 */       if (bufferLength > buffer.capacity()) {
/*  8597 */         JmqiException traceRet3 = new JmqiException(this.env, -1, null, 2, 2005, null);
/*       */         
/*  8599 */         if (Trace.isOn) {
/*  8600 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiGetMessageWithProps(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,PtripletArray,Pint,Pint)", (Throwable)traceRet3, 4);
/*       */         }
/*       */ 
/*       */         
/*  8604 */         throw traceRet3;
/*       */       } 
/*       */ 
/*       */       
/*  8608 */       long maxMsgLength = remoteHconn.getMaximumMessageLength();
/*  8609 */       if (bufferLength > maxMsgLength) {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  8616 */         pDataLength.x = (int)maxMsgLength;
/*  8617 */         if (Trace.isOn) {
/*  8618 */           Trace.data(this, "jmqiGetMessageWithProps(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,PtripletArray,Pint,Pint)", "bufferLength passed in is greater than the maximum message length on the channel being used");
/*       */ 
/*       */           
/*  8621 */           Trace.data(this, "jmqiGetMessageWithProps(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,PtripletArray,Pint,Pint)", "pDataLength set to " + pDataLength);
/*       */         } 
/*       */ 
/*       */         
/*  8625 */         JmqiException traceRet4 = new JmqiException(this.env, -1, null, 2, 2010, null);
/*       */         
/*  8627 */         if (Trace.isOn) {
/*  8628 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiGetMessageWithProps(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,PtripletArray,Pint,Pint)", (Throwable)traceRet4, 5);
/*       */         }
/*       */ 
/*       */         
/*  8632 */         throw traceRet4;
/*       */       } 
/*       */       
/*  8635 */       checkGetOptions(getMsgOpts, remoteHobj);
/*       */       
/*  8637 */       handleUndefinedCcsid(msgDesc);
/*       */ 
/*       */       
/*  8640 */       remoteHconn.enterCall(tls.isDispatchThread, false);
/*       */ 
/*       */ 
/*       */       
/*  8644 */       boolean parentConnSendLockTaken = false;
/*       */       
/*       */       try {
/*  8647 */         remoteSession = remoteHconn.getSession();
/*       */         
/*  8649 */         JmqiCodepage cp = remoteSession.getCp();
/*  8650 */         boolean swap = remoteSession.isSwap();
/*       */         
/*  8652 */         remoteSession.checkIfDisconnected();
/*       */         
/*  8654 */         int fapLevel = remoteHconn.getFapLevel();
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  8660 */         if (remoteHobj.getProxyQueue() != null && (!remoteHconn.getParentConnection().isMultiplexSyncGetCapable() || (remoteHobj
/*  8661 */           .getProxyQueue().getStatus() & 0x4000) != 0)) {
/*  8662 */           remoteHobj.getProxyQueue().proxyMQGET(tls, msgDesc, getMsgOpts, bufferLength, buffer.array(), pDataLength, null, pCompCode, pReason);
/*       */         
/*       */         }
/*       */         else {
/*       */ 
/*       */           
/*  8668 */           RfpMQAPI sMQAPI = remoteSession.allocateMQAPI(133);
/*       */           
/*  8670 */           sMQAPI.setHandle(remoteHobj.getIntegerHandle(), swap);
/*       */           
/*  8672 */           int writePos = sMQAPI.getRfpOffset() + sMQAPI.hdrSize();
/*  8673 */           byte[] commsBuff = sMQAPI.getRfpBuffer();
/*       */ 
/*       */           
/*  8676 */           writePos = msgDesc.writeToBuffer(commsBuff, writePos, true, false, false, 4, swap, cp, jmqiTls);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  8692 */           boolean readMPH = false;
/*  8693 */           if (pTriplets != null && fapLevel >= 9) {
/*  8694 */             int gmoVersion = getMsgOpts.getVersion();
/*  8695 */             if (gmoVersion >= 4) {
/*  8696 */               readMPH = true;
/*  8697 */               getMsgOpts.setMessageHandle(2L);
/*       */             } 
/*       */           } 
/*       */ 
/*       */           
/*  8702 */           writePos = getMsgOpts.writeToBuffer(commsBuff, writePos, 4, swap, cp, jmqiTls);
/*       */ 
/*       */           
/*  8705 */           dc.writeI32(bufferLength, commsBuff, writePos, swap);
/*  8706 */           writePos += 4;
/*       */ 
/*       */           
/*  8709 */           int requestSize = writePos - sMQAPI.getRfpOffset();
/*       */           
/*  8711 */           sMQAPI.setTransLength(requestSize);
/*       */ 
/*       */           
/*  8714 */           sMQAPI.setCallLength(requestSize + bufferLength);
/*       */ 
/*       */           
/*  8717 */           sMQAPI.setControlFlags1(48);
/*       */           
/*  8719 */           remoteSession.sendTSH(tls, (RfpTSH)sMQAPI);
/*       */           
/*  8721 */           boolean gotReply = false;
/*  8722 */           while (!gotReply) {
/*       */             boolean unexpectedTshType;
/*       */ 
/*       */             
/*  8726 */             if (remoteSession.isMultiplexingEnabled()) {
/*  8727 */               rMQAPI = remoteSession.receiveMQIFlow(tls);
/*  8728 */               unexpectedTshType = (rMQAPI.getTshType() != 2);
/*       */             } else {
/*       */               
/*  8731 */               rMQAPI = remoteSession.receive1stGetReplyTSH(tls);
/*  8732 */               unexpectedTshType = (rMQAPI.getTshType() != 0);
/*       */             } 
/*       */             
/*  8735 */             if (unexpectedTshType) {
/*       */ 
/*       */ 
/*       */ 
/*       */               
/*  8740 */               HashMap<String, Object> info = new HashMap<>();
/*  8741 */               if (remoteSession.isMultiplexingEnabled()) {
/*  8742 */                 info.put("Description", "Invalid TSH received on multiplexed connection");
/*       */               } else {
/*       */                 
/*  8745 */                 info.put("Description", "Invalid TSH received on non-multiplexed connection");
/*       */               } 
/*  8747 */               Trace.ffst(this, "jmqiGetMessageWithProps(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,PtripletArray,Pint,Pint)", "01", info, null);
/*       */ 
/*       */               
/*  8750 */               JmqiException e = new JmqiException(this.env, -1, null, 2, 2009, null);
/*       */               
/*  8752 */               if (Trace.isOn) {
/*  8753 */                 Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiGetMessageWithProps(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,PtripletArray,Pint,Pint)", (Throwable)e, 6);
/*       */               }
/*       */ 
/*       */               
/*  8757 */               throw e;
/*       */             } 
/*       */ 
/*       */ 
/*       */             
/*  8762 */             swap = remoteSession.isSwap();
/*       */ 
/*       */ 
/*       */             
/*  8766 */             if (rMQAPI.getSegmentType() == 149) {
/*       */               
/*  8768 */               if ((rMQAPI.getControlFlags1() & 0x10) == 0) {
/*  8769 */                 HashMap<String, Object> info = new HashMap<>();
/*  8770 */                 info.put("SegmentType", Integer.valueOf(rMQAPI.getSegmentType()));
/*  8771 */                 info.put("ControlFlags1", Integer.valueOf(rMQAPI.getControlFlags1()));
/*  8772 */                 info.put("Description", "Unexpected flow received from server");
/*  8773 */                 Trace.ffst(this, "jmqiGetMessageWithProps(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,PtripletArray,Pint,Pint)", "01", info, null);
/*       */ 
/*       */                 
/*  8776 */                 JmqiException traceRet7 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*       */                 
/*  8778 */                 if (Trace.isOn) {
/*  8779 */                   Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiGetMessageWithProps(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,PtripletArray,Pint,Pint)", (Throwable)traceRet7, 7);
/*       */                 }
/*       */ 
/*       */                 
/*  8783 */                 throw traceRet7;
/*       */               } 
/*       */ 
/*       */               
/*  8787 */               pCompCode.x = rMQAPI.getCompCode(swap);
/*  8788 */               pReason.x = rMQAPI.getReasonCode(swap);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */               
/*  8794 */               if (pCompCode.x != 2) {
/*  8795 */                 int msgDataReceived; commsBuff = rMQAPI.getRfpBuffer();
/*       */                 
/*  8797 */                 int readPos = rMQAPI.getRfpOffset() + rMQAPI.hdrSize();
/*       */ 
/*       */                 
/*  8800 */                 readPos = msgDesc.readFromBuffer(commsBuff, readPos, 4, swap, cp, jmqiTls);
/*       */ 
/*       */                 
/*  8803 */                 readPos = getMsgOpts.readFromBuffer(commsBuff, readPos, true, 4, swap, cp, jmqiTls);
/*       */ 
/*       */ 
/*       */                 
/*  8807 */                 if (readMPH) {
/*  8808 */                   long mh = getMsgOpts.getMessageHandle();
/*  8809 */                   if (mh != 0L) {
/*       */                     
/*  8811 */                     readPos = readMPH(pTriplets, rMQAPI, readPos, swap, cp, jmqiTls, remoteSession, tls);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */                     
/*  8817 */                     commsBuff = rMQAPI.getRfpBuffer();
/*  8818 */                     getMsgOpts.setMessageHandle(1L);
/*       */                   } 
/*       */                 } 
/*       */ 
/*       */                 
/*  8823 */                 pDataLength.x = dc.readI32(commsBuff, readPos, swap);
/*  8824 */                 readPos += 4;
/*       */ 
/*       */ 
/*       */ 
/*       */                 
/*  8829 */                 int msgDataToReceive = pDataLength.x;
/*       */                 
/*  8831 */                 if (msgDataToReceive > bufferLength) {
/*  8832 */                   msgDataToReceive = bufferLength;
/*       */                 }
/*       */ 
/*       */ 
/*       */                 
/*  8837 */                 if (remoteSession.isMultiplexingEnabled()) {
/*  8838 */                   msgDataReceived = remoteSession.receiveSpannedMQIData(tls, rMQAPI, readPos - rMQAPI
/*  8839 */                       .getRfpOffset(), buffer, msgDataToReceive);
/*       */                 
/*       */                 }
/*       */                 else {
/*       */                   
/*  8844 */                   byte[] data = buffer.array();
/*  8845 */                   msgDataReceived = rMQAPI.getTransLength() - readPos;
/*  8846 */                   System.arraycopy(rMQAPI.getRfpBuffer(), readPos, data, 0, msgDataReceived);
/*       */                   
/*  8848 */                   while (msgDataReceived < msgDataToReceive) {
/*  8849 */                     RfpTSH rTSH = remoteSession.receiveNextGetReplyTSH(tls);
/*  8850 */                     System.arraycopy(rTSH.getRfpBuffer(), rTSH.getRfpOffset() + rTSH.hdrSize(), data, msgDataReceived, rTSH
/*  8851 */                         .getTransLength() - rTSH.hdrSize());
/*  8852 */                     msgDataReceived += rTSH.getTransLength() - rTSH.hdrSize();
/*  8853 */                     remoteSession.releaseReceivedTSH(rTSH);
/*       */                   } 
/*       */                 } 
/*       */ 
/*       */ 
/*       */                 
/*  8859 */                 if (msgDataReceived != msgDataToReceive) {
/*  8860 */                   HashMap<String, Object> info = new HashMap<>();
/*  8861 */                   info.put("msgDataReceived", Integer.valueOf(msgDataReceived));
/*  8862 */                   info.put("msgDataToReceive", Integer.valueOf(msgDataToReceive));
/*  8863 */                   info.put("Description", "Incorrect length of data received");
/*  8864 */                   Trace.ffst(this, "jmqiGetMessageWithProps(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,PtripletArray,Pint,Pint)", "01", info, null);
/*       */ 
/*       */                   
/*  8867 */                   JmqiException traceRet8 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*       */                   
/*  8869 */                   if (Trace.isOn) {
/*  8870 */                     Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiGetMessageWithProps(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,PtripletArray,Pint,Pint)", (Throwable)traceRet8, 8);
/*       */                   }
/*       */ 
/*       */                   
/*  8874 */                   throw traceRet8;
/*       */                 } 
/*  8876 */                 buffer.limit(msgDataReceived);
/*  8877 */                 buffer.rewind();
/*       */               } 
/*  8879 */               gotReply = true;
/*       */               break;
/*       */             } 
/*  8882 */             if ((rMQAPI.getControlFlags1() & 0x8) != 0) {
/*       */ 
/*       */               
/*  8885 */               parentConn.analyseErrorSegment((RfpTSH)rMQAPI); continue;
/*       */             } 
/*  8887 */             if (rMQAPI.getSegmentType() == 9) {
/*  8888 */               if ((rMQAPI.getControlFlags1() & 0x1) == 0) {
/*       */                 break;
/*       */               }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */               
/*  8896 */               if (parentConn.getFapLevel() < 8 && parentConn.getSecureKeyResetCount() > 0 && parentConn.isSecure()) {
/*  8897 */                 parentConn.performSecureKeyReset();
/*       */               }
/*       */ 
/*       */               
/*  8901 */               parentConn.sendHeartbeat(tls, 2); continue;
/*       */             } 
/*  8903 */             if (rMQAPI.getSegmentType() == 11) {
/*       */ 
/*       */ 
/*       */               
/*  8907 */               parentConn.performSecureKeyReset();
/*       */ 
/*       */               
/*  8910 */               RfpTSH keyResetTSH = parentConn.allocateTSH(0, 5, null);
/*  8911 */               keyResetTSH.setTransLength(keyResetTSH.hdrSize());
/*  8912 */               keyResetTSH.setControlFlags1(64);
/*  8913 */               parentConn.sendTSH(tls, keyResetTSH, null);
/*       */             } 
/*       */           } 
/*       */ 
/*       */           
/*  8918 */           if (pCompCode.x != 2) {
/*  8919 */             switch (getMsgOpts.getOptions() & 0x1006) {
/*       */               case 2:
/*  8921 */                 remoteHconn.setInTransaction();
/*       */                 break;
/*       */               case 4096:
/*  8924 */                 if (msgDesc.getPersistence() == 1) {
/*  8925 */                   remoteHconn.setInTransaction();
/*       */                 }
/*       */                 break;
/*       */             } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*       */           }
/*       */         } 
/*       */       } finally {
/*  8938 */         if (Trace.isOn) {
/*  8939 */           Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiGetMessageWithProps(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,PtripletArray,Pint,Pint)", 1);
/*       */         }
/*       */ 
/*       */         
/*  8943 */         if (parentConnSendLockTaken) {
/*  8944 */           parentConn.releaseSendLock();
/*  8945 */           parentConnSendLockTaken = false;
/*       */         } 
/*       */ 
/*       */         
/*       */         try {
/*  8950 */           if (remoteHconn != null) {
/*  8951 */             if (rMQAPI != null) {
/*       */               try {
/*  8953 */                 remoteSession.releaseReceivedTSH((RfpTSH)rMQAPI);
/*       */               }
/*  8955 */               catch (JmqiException e) {
/*  8956 */                 if (Trace.isOn) {
/*  8957 */                   Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiGetMessageWithProps(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,PtripletArray,Pint,Pint)", (Throwable)e, 1);
/*       */                 }
/*       */               } 
/*       */             }
/*       */ 
/*       */ 
/*       */ 
/*       */             
/*  8965 */             remoteHconn.leaveCall(pReason.x);
/*       */           }
/*       */         
/*  8968 */         } catch (JmqiException e) {
/*  8969 */           if (Trace.isOn) {
/*  8970 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiGetMessageWithProps(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,PtripletArray,Pint,Pint)", (Throwable)e, 2);
/*       */           }
/*       */         }
/*       */       
/*       */       }
/*       */     
/*       */     }
/*  8977 */     catch (JmqiException e) {
/*  8978 */       if (Trace.isOn) {
/*  8979 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiGetMessageWithProps(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,PtripletArray,Pint,Pint)", (Throwable)e, 3);
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  8985 */       jmqiTls.lastException = e;
/*  8986 */       pCompCode.x = e.getCompCode();
/*  8987 */       pReason.x = e.getReason();
/*       */     } finally {
/*       */       
/*  8990 */       Exception exception = null; if (Trace.isOn) {
/*  8991 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiGetMessageWithProps(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,PtripletArray,Pint,Pint)", 2);
/*       */       }
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  9013 */     if (Trace.isOn) {
/*  9014 */       Trace.data(this, "jmqiGetMessageWithProps(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,PtripletArray,Pint,Pint)", "__________");
/*       */ 
/*       */       
/*  9017 */       Trace.data(this, "jmqiGetMessageWithProps(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,PtripletArray,Pint,Pint)", "jmqiGetMessageWithProps <<");
/*       */ 
/*       */ 
/*       */       
/*  9021 */       Trace.data(this, "jmqiGetMessageWithProps(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,PtripletArray,Pint,Pint)", "Hconn", hconn);
/*       */ 
/*       */       
/*  9024 */       Trace.data(this, "jmqiGetMessageWithProps(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,PtripletArray,Pint,Pint)", "Hobj", hobj);
/*       */ 
/*       */       
/*  9027 */       Trace.data(this, "jmqiGetMessageWithProps(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,PtripletArray,Pint,Pint)", "Msgdesc", msgDesc);
/*       */ 
/*       */       
/*  9030 */       Trace.data(this, "jmqiGetMessageWithProps(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,PtripletArray,Pint,Pint)", "Getmsgops", getMsgOpts);
/*       */ 
/*       */       
/*  9033 */       Trace.data(this, "jmqiGetMessageWithProps(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,PtripletArray,Pint,Pint)", "Bufferlength", 
/*       */           
/*  9035 */           Integer.toString(bufferLength));
/*       */       
/*  9037 */       JmqiTools.traceApiBuffer(this, "jmqiGetMessageWithProps(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,PtripletArray,Pint,Pint)", buffer, bufferLength);
/*       */ 
/*       */ 
/*       */       
/*  9041 */       Trace.data(this, "jmqiGetMessageWithProps(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,PtripletArray,Pint,Pint)", "Datalength", pDataLength);
/*       */ 
/*       */       
/*  9044 */       Trace.data(this, "jmqiGetMessageWithProps(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,PtripletArray,Pint,Pint)", "MessageProperties", (pTriplets == null) ? null : pTriplets
/*       */           
/*  9046 */           .getTriplets());
/*  9047 */       Trace.data(this, "jmqiGetMessageWithProps(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,PtripletArray,Pint,Pint)", "CompCode", pCompCode);
/*       */ 
/*       */       
/*  9050 */       Trace.data(this, "jmqiGetMessageWithProps(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,PtripletArray,Pint,Pint)", "Reason", pReason);
/*       */ 
/*       */ 
/*       */       
/*  9054 */       Trace.data(this, "jmqiGetMessageWithProps(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,PtripletArray,Pint,Pint)", "__________");
/*       */     } 
/*       */ 
/*       */ 
/*       */     
/*  9059 */     if (Trace.isOn) {
/*  9060 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiGetMessageWithProps(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,PtripletArray,Pint,Pint)", remoteSession);
/*       */     }
/*       */ 
/*       */     
/*  9064 */     return remoteSession;
/*       */   }
/*       */   
/*       */   private void checkGetOptions(MQGMO getMsgOpts, RemoteHobj remoteHobj) throws JmqiException {
/*  9068 */     if (Trace.isOn) {
/*  9069 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "checkGetOptions(MQGMO,RemoteHobj)", new Object[] { getMsgOpts, remoteHobj });
/*       */     }
/*       */ 
/*       */     
/*  9073 */     if ((getMsgOpts.getOptions() & 0x830) != 0) {
/*  9074 */       if ((remoteHobj.getOpenOptions() & 0x8) == 0) {
/*  9075 */         JmqiException traceRet5 = new JmqiException(this.env, -1, null, 2, 2036, null);
/*       */ 
/*       */         
/*  9078 */         if (Trace.isOn) {
/*  9079 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "checkGetOptions(MQGMO,RemoteHobj)", (Throwable)traceRet5, 1);
/*       */         }
/*       */         
/*  9082 */         throw traceRet5;
/*       */       }
/*       */     
/*  9085 */     } else if ((remoteHobj.getOpenOptions() & 0x7) == 0) {
/*  9086 */       JmqiException traceRet6 = new JmqiException(this.env, -1, null, 2, 2037, null);
/*       */ 
/*       */       
/*  9089 */       if (Trace.isOn) {
/*  9090 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "checkGetOptions(MQGMO,RemoteHobj)", (Throwable)traceRet6, 2);
/*       */       }
/*       */       
/*  9093 */       throw traceRet6;
/*       */     } 
/*  9095 */     if (Trace.isOn) {
/*  9096 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "checkGetOptions(MQGMO,RemoteHobj)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQINQ(Hconn hconn, Hobj hobj, int selectorCount, int[] selectors, int intAttrsCount, int[] intAttrs, int charAttrsLength, byte[] charAttrs, Pint pCompCode, Pint pReason) {
/*  9128 */     if (Trace.isOn) {
/*  9129 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", new Object[] { hconn, hobj, 
/*       */             
/*  9131 */             Integer.valueOf(selectorCount), selectors, 
/*  9132 */             Integer.valueOf(intAttrsCount), intAttrs, Integer.valueOf(charAttrsLength), charAttrs, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */ 
/*       */     
/*  9137 */     if (Trace.isOn) {
/*  9138 */       Trace.data(this, "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "__________");
/*  9139 */       Trace.data(this, "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "MQINQ >>");
/*  9140 */       Trace.data(this, "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "Hconn", hconn);
/*  9141 */       Trace.data(this, "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "Hobj", hobj);
/*  9142 */       Trace.data(this, "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "Selectorcount", 
/*  9143 */           Integer.toString(selectorCount));
/*  9144 */       Trace.data(this, "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "Selectors", selectors);
/*       */       
/*  9146 */       Trace.data(this, "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "Intattrcount", 
/*  9147 */           Integer.toString(intAttrsCount));
/*  9148 */       Trace.data(this, "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "Intattrs", intAttrs);
/*  9149 */       Trace.data(this, "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "Charattrlength", 
/*  9150 */           Integer.toString(charAttrsLength));
/*  9151 */       Trace.data(this, "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "Charattrs", charAttrs);
/*       */       
/*  9153 */       Trace.data(this, "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "CompCode", pCompCode);
/*  9154 */       Trace.data(this, "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "Reason", pReason);
/*  9155 */       Trace.data(this, "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "__________");
/*       */     } 
/*  9157 */     RemoteHconn remoteHconn = null;
/*  9158 */     RfpMQAPI rMQAPI = null;
/*       */     
/*  9160 */     RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  9161 */     JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/*  9162 */     jmqiTls.lastException = null;
/*  9163 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*  9164 */     RemoteSession remoteSession = null;
/*       */     try {
/*  9166 */       remoteHconn = getRemoteHconn(tls, hconn);
/*       */       
/*  9168 */       if (hobj == null || !(hobj instanceof RemoteHobj)) {
/*  9169 */         JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2019, null);
/*       */ 
/*       */         
/*  9172 */         if (Trace.isOn) {
/*  9173 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", (Throwable)traceRet1, 1);
/*       */         }
/*       */         
/*  9176 */         throw traceRet1;
/*       */       } 
/*       */       
/*  9179 */       RemoteHobj remoteHobj = (RemoteHobj)hobj;
/*       */ 
/*       */       
/*  9182 */       if (selectors == null || selectors.length < selectorCount) {
/*  9183 */         JmqiException traceRet2 = new JmqiException(this.env, -1, null, 2, 2065, null);
/*       */         
/*  9185 */         if (Trace.isOn) {
/*  9186 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", (Throwable)traceRet2, 2);
/*       */         }
/*       */         
/*  9189 */         throw traceRet2;
/*       */       } 
/*       */ 
/*       */       
/*  9193 */       int actualIntAttrCount = 0;
/*       */       
/*  9195 */       for (int selector = 0; selector < selectorCount; selector++) {
/*  9196 */         if (selectors[selector] <= 2000) {
/*  9197 */           actualIntAttrCount++;
/*       */         }
/*       */       } 
/*       */ 
/*       */       
/*  9202 */       if (actualIntAttrCount > 0 && (intAttrs == null || intAttrsCount > intAttrs.length)) {
/*  9203 */         JmqiException traceRet3 = new JmqiException(this.env, -1, null, 2, 2021, null);
/*       */         
/*  9205 */         if (Trace.isOn) {
/*  9206 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", (Throwable)traceRet3, 3);
/*       */         }
/*       */         
/*  9209 */         throw traceRet3;
/*       */       } 
/*       */ 
/*       */       
/*  9213 */       int charAttrCount = 0;
/*       */       
/*  9215 */       for (int i = 0; i < selectorCount; i++) {
/*  9216 */         if (selectors[i] >= 2001 && selectors[i] <= 4000) {
/*  9217 */           charAttrCount++;
/*       */         }
/*       */       } 
/*       */ 
/*       */       
/*  9222 */       if (charAttrCount > 0 && (charAttrs == null || charAttrsLength > charAttrs.length)) {
/*  9223 */         JmqiException traceRet4 = new JmqiException(this.env, -1, null, 2, 2007, null);
/*       */         
/*  9225 */         if (Trace.isOn) {
/*  9226 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", (Throwable)traceRet4, 4);
/*       */         }
/*       */         
/*  9229 */         throw traceRet4;
/*       */       } 
/*       */ 
/*       */       
/*  9233 */       if (actualIntAttrCount + charAttrCount != selectorCount) {
/*  9234 */         JmqiException traceRet5 = new JmqiException(this.env, -1, null, 2, 2067, null);
/*       */         
/*  9236 */         if (Trace.isOn) {
/*  9237 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", (Throwable)traceRet5, 5);
/*       */         }
/*       */         
/*  9240 */         throw traceRet5;
/*       */       } 
/*       */ 
/*       */       
/*  9244 */       remoteHconn.enterCall(tls.isDispatchThread, false);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*       */       try {
/*  9252 */         remoteSession = remoteHconn.getSession();
/*       */         
/*  9254 */         remoteSession.checkIfDisconnected();
/*       */ 
/*       */         
/*  9257 */         boolean swap = remoteSession.isSwap();
/*       */ 
/*       */         
/*  9260 */         RfpMQAPI sMQAPI = remoteSession.allocateMQAPI(137);
/*       */         
/*  9262 */         sMQAPI.setHandle(remoteHobj.getIntegerHandle(), swap);
/*       */         
/*  9264 */         int writePos = sMQAPI.getRfpOffset() + sMQAPI.hdrSize();
/*  9265 */         byte[] commsBuff = sMQAPI.getRfpBuffer();
/*       */ 
/*       */         
/*  9268 */         RfpMQINQ mqInqDetails = (RfpMQINQ)remoteSession.getRfp(6, (RfpStructure)sMQAPI, writePos);
/*  9269 */         writePos += 12;
/*       */         
/*  9271 */         mqInqDetails.setSelectorCount(selectorCount, swap);
/*  9272 */         mqInqDetails.setIntAttrCount(intAttrsCount, swap);
/*  9273 */         mqInqDetails.setCharAttrLength(charAttrsLength, swap);
/*       */ 
/*       */         
/*  9276 */         for (int j = 0; j < selectorCount; j++) {
/*  9277 */           dc.writeI32(selectors[j], commsBuff, writePos, swap);
/*  9278 */           writePos += 4;
/*       */         } 
/*       */ 
/*       */         
/*  9282 */         int requestLength = writePos - sMQAPI.getRfpOffset();
/*       */         
/*  9284 */         sMQAPI.setControlFlags1(48);
/*  9285 */         sMQAPI.setTransLength(requestLength);
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  9290 */         sMQAPI.setCallLength(requestLength + intAttrsCount * 4 + charAttrsLength + 1);
/*       */ 
/*       */         
/*  9293 */         remoteSession.sendTSH(tls, (RfpTSH)sMQAPI);
/*       */ 
/*       */         
/*  9296 */         rMQAPI = remoteSession.receiveMQIFlow(tls);
/*       */ 
/*       */         
/*  9299 */         if (rMQAPI.getSegmentType() != 153 || (rMQAPI.getControlFlags1() & 0x99) == 0) {
/*  9300 */           HashMap<String, Object> info = new HashMap<>();
/*  9301 */           info.put("SegmentType", Integer.valueOf(rMQAPI.getSegmentType()));
/*  9302 */           info.put("ControlFlags1", Integer.valueOf(rMQAPI.getControlFlags1()));
/*  9303 */           info.put("Description", "Unexpected flow received from server");
/*  9304 */           Trace.ffst(this, "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "01", info, null);
/*       */           
/*  9306 */           JmqiException traceRet8 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*       */           
/*  9308 */           if (Trace.isOn) {
/*  9309 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", (Throwable)traceRet8, 6);
/*       */           }
/*       */           
/*  9312 */           throw traceRet8;
/*       */         } 
/*       */ 
/*       */ 
/*       */         
/*  9317 */         swap = remoteSession.isSwap();
/*       */ 
/*       */         
/*  9320 */         pCompCode.x = rMQAPI.getCompCode(swap);
/*  9321 */         pReason.x = rMQAPI.getReasonCode(swap);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  9327 */         if (pCompCode.x != 2) {
/*  9328 */           commsBuff = rMQAPI.getRfpBuffer();
/*  9329 */           int readPos = rMQAPI.getRfpOffset() + rMQAPI.hdrSize() + 12 + 4 * selectorCount;
/*       */ 
/*       */           
/*  9332 */           for (int k = 0; k < intAttrsCount; k++) {
/*  9333 */             intAttrs[k] = dc.readI32(commsBuff, readPos, swap);
/*  9334 */             readPos += 4;
/*       */           } 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  9340 */           if (charAttrsLength > 0)
/*       */           {
/*  9342 */             remoteSession.receiveSpannedMQIData(tls, rMQAPI, readPos - rMQAPI.getRfpOffset(), 
/*  9343 */                 ByteBuffer.wrap(charAttrs), charAttrsLength);
/*       */           }
/*       */         } 
/*       */       } finally {
/*       */         
/*  9348 */         if (Trace.isOn) {
/*  9349 */           Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)");
/*       */         }
/*       */ 
/*       */ 
/*       */         
/*       */         try {
/*  9355 */           if (remoteHconn != null) {
/*  9356 */             if (rMQAPI != null) {
/*       */               try {
/*  9358 */                 remoteSession.releaseReceivedTSH((RfpTSH)rMQAPI);
/*       */               }
/*  9360 */               catch (JmqiException e) {
/*  9361 */                 if (Trace.isOn) {
/*  9362 */                   Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", (Throwable)e, 1);
/*       */                 }
/*       */               } 
/*       */             }
/*       */ 
/*       */ 
/*       */             
/*  9369 */             remoteHconn.leaveCall(pReason.x);
/*       */           }
/*       */         
/*  9372 */         } catch (JmqiException e) {
/*  9373 */           if (Trace.isOn) {
/*  9374 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", (Throwable)e, 2);
/*       */           }
/*       */         }
/*       */       
/*       */       }
/*       */     
/*  9380 */     } catch (JmqiException e) {
/*  9381 */       if (Trace.isOn) {
/*  9382 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", (Throwable)e, 3);
/*       */       }
/*       */ 
/*       */ 
/*       */       
/*  9387 */       jmqiTls.lastException = e;
/*  9388 */       pCompCode.x = e.getCompCode();
/*  9389 */       pReason.x = e.getReason();
/*       */     } 
/*       */ 
/*       */     
/*  9393 */     if (0 != pReason.x && 
/*  9394 */       remoteHconn.isReconnectable() && 
/*  9395 */       RemoteHconn.isReconnectableReasonCode(pReason.x) && 
/*  9396 */       !remoteHconn.hasFailed()) {
/*       */       try {
/*  9398 */         remoteHconn.initiateReconnection(remoteSession);
/*  9399 */         MQINQ(hconn, hobj, selectorCount, selectors, intAttrsCount, intAttrs, charAttrsLength, charAttrs, pCompCode, pReason);
/*       */       
/*       */       }
/*  9402 */       catch (JmqiException je) {
/*  9403 */         if (Trace.isOn) {
/*  9404 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", (Throwable)je);
/*       */         }
/*       */       } 
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  9414 */     if (remoteHconn.hasFailed()) {
/*  9415 */       pCompCode.x = remoteHconn.getReconnectionFailureCompCode();
/*  9416 */       pReason.x = remoteHconn.getReconnectionFailureReason();
/*  9417 */       jmqiTls.lastException = remoteHconn.getReconnectionFailureException();
/*       */     } 
/*       */ 
/*       */     
/*  9421 */     if (Trace.isOn) {
/*  9422 */       Trace.data(this, "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "__________");
/*  9423 */       Trace.data(this, "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "MQINQ <<");
/*       */       
/*  9425 */       Trace.data(this, "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "Hconn", hconn);
/*  9426 */       Trace.data(this, "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "Hobj", hobj);
/*  9427 */       Trace.data(this, "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "Selectorcount", 
/*  9428 */           Integer.toString(selectorCount));
/*  9429 */       Trace.data(this, "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "Selectors", selectors);
/*       */       
/*  9431 */       Trace.data(this, "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "Intattrcount", 
/*  9432 */           Integer.toString(intAttrsCount));
/*  9433 */       Trace.data(this, "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "Intattrs", intAttrs);
/*  9434 */       Trace.data(this, "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "Charattrlength", 
/*  9435 */           Integer.toString(charAttrsLength));
/*  9436 */       Trace.data(this, "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "Charattrs", charAttrs);
/*       */       
/*  9438 */       Trace.data(this, "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "CompCode", pCompCode);
/*  9439 */       Trace.data(this, "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "Reason", pReason);
/*       */       
/*  9441 */       Trace.data(this, "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "__________");
/*       */     } 
/*       */     
/*  9444 */     if (Trace.isOn) {
/*  9445 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQSET(Hconn hconn, Hobj hobj, int selectorCount, int[] selectors, int intAttrsCount, int[] intAttrs, int charAttrLength, byte[] charAttrs, Pint pCompCode, Pint pReason) {
/*  9478 */     if (Trace.isOn) {
/*  9479 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", new Object[] { hconn, hobj, 
/*       */             
/*  9481 */             Integer.valueOf(selectorCount), selectors, 
/*  9482 */             Integer.valueOf(intAttrsCount), intAttrs, Integer.valueOf(charAttrLength), charAttrs, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */ 
/*       */     
/*  9487 */     if (Trace.isOn) {
/*  9488 */       Trace.data(this, "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "__________");
/*  9489 */       Trace.data(this, "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "MQSET >>");
/*  9490 */       Trace.data(this, "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "__________");
/*  9491 */       Trace.data(this, "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "Hconn", hconn);
/*  9492 */       Trace.data(this, "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "Hobj", hobj);
/*  9493 */       Trace.data(this, "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "Selectorcount", 
/*  9494 */           Integer.toString(selectorCount));
/*  9495 */       Trace.data(this, "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "Selectors", selectors);
/*       */       
/*  9497 */       Trace.data(this, "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "Intattrcount", 
/*  9498 */           Integer.toString(intAttrsCount));
/*  9499 */       Trace.data(this, "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "Intattrs", intAttrs);
/*  9500 */       Trace.data(this, "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "Charattrlength", 
/*  9501 */           Integer.toString(charAttrLength));
/*  9502 */       Trace.data(this, "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "Charattrs", charAttrs);
/*       */       
/*  9504 */       Trace.data(this, "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "CompCode", pCompCode);
/*  9505 */       Trace.data(this, "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "Reason", pReason);
/*  9506 */       Trace.data(this, "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "__________");
/*       */     } 
/*  9508 */     RemoteHconn remoteHconn = null;
/*  9509 */     RemoteSession remoteSession = null;
/*  9510 */     RfpMQAPI rMQAPI = null;
/*       */     
/*  9512 */     RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  9513 */     JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/*  9514 */     jmqiTls.lastException = null;
/*  9515 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*       */     try {
/*  9517 */       remoteHconn = getRemoteHconn(tls, hconn);
/*       */       
/*  9519 */       if (hobj == null || !(hobj instanceof RemoteHobj)) {
/*  9520 */         JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2019, null);
/*       */ 
/*       */         
/*  9523 */         if (Trace.isOn) {
/*  9524 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", (Throwable)traceRet1, 1);
/*       */         }
/*       */         
/*  9527 */         throw traceRet1;
/*       */       } 
/*       */       
/*  9530 */       RemoteHobj remoteHobj = (RemoteHobj)hobj;
/*       */ 
/*       */       
/*  9533 */       if (selectors == null || selectors.length < selectorCount) {
/*  9534 */         JmqiException traceRet2 = new JmqiException(this.env, -1, null, 2, 2065, null);
/*       */         
/*  9536 */         if (Trace.isOn) {
/*  9537 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", (Throwable)traceRet2, 2);
/*       */         }
/*       */         
/*  9540 */         throw traceRet2;
/*       */       } 
/*       */ 
/*       */       
/*  9544 */       int actualIntAttrCount = 0;
/*       */       
/*  9546 */       for (int selector = 0; selector < selectorCount; selector++) {
/*  9547 */         if (selectors[selector] <= 2000) {
/*  9548 */           actualIntAttrCount++;
/*       */         }
/*       */       } 
/*       */ 
/*       */       
/*  9553 */       if (actualIntAttrCount > 0 && (intAttrs == null || intAttrsCount > intAttrs.length)) {
/*  9554 */         JmqiException traceRet3 = new JmqiException(this.env, -1, null, 2, 2021, null);
/*       */         
/*  9556 */         if (Trace.isOn) {
/*  9557 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", (Throwable)traceRet3, 3);
/*       */         }
/*       */         
/*  9560 */         throw traceRet3;
/*       */       } 
/*       */ 
/*       */       
/*  9564 */       int charAttrCount = 0;
/*       */       
/*  9566 */       for (int i = 0; i < selectorCount; i++) {
/*  9567 */         if (selectors[i] >= 2001 && selectors[i] <= 4000) {
/*  9568 */           charAttrCount++;
/*       */         }
/*       */       } 
/*       */ 
/*       */       
/*  9573 */       if (charAttrCount > 0 && (charAttrs == null || charAttrs.length < charAttrCount)) {
/*  9574 */         JmqiException traceRet4 = new JmqiException(this.env, -1, null, 2, 2007, null);
/*       */         
/*  9576 */         if (Trace.isOn) {
/*  9577 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", (Throwable)traceRet4, 4);
/*       */         }
/*       */         
/*  9580 */         throw traceRet4;
/*       */       } 
/*       */ 
/*       */       
/*  9584 */       if (actualIntAttrCount + charAttrCount != selectorCount) {
/*  9585 */         JmqiException traceRet5 = new JmqiException(this.env, -1, null, 2, 2067, null);
/*       */         
/*  9587 */         if (Trace.isOn) {
/*  9588 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", (Throwable)traceRet5, 5);
/*       */         }
/*       */         
/*  9591 */         throw traceRet5;
/*       */       } 
/*       */ 
/*       */       
/*  9595 */       remoteHconn.enterCall(tls.isDispatchThread, false);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*       */       try {
/*  9603 */         remoteSession = remoteHconn.getSession();
/*       */         
/*  9605 */         remoteSession.checkIfDisconnected();
/*       */ 
/*       */         
/*  9608 */         boolean swap = remoteSession.isSwap();
/*       */ 
/*       */         
/*  9611 */         RfpMQAPI sMQAPI = remoteSession.allocateMQAPI(136);
/*       */         
/*  9613 */         sMQAPI.setHandle(remoteHobj.getIntegerHandle(), swap);
/*       */         
/*  9615 */         byte[] commsBuff = sMQAPI.getRfpBuffer();
/*  9616 */         int writePos = sMQAPI.getRfpOffset() + sMQAPI.hdrSize();
/*       */ 
/*       */         
/*  9619 */         RfpMQSET mqSetDetails = (RfpMQSET)remoteSession.getRfp(7, (RfpStructure)sMQAPI, writePos);
/*  9620 */         writePos += 12;
/*       */         
/*  9622 */         mqSetDetails.setSelectorCount(selectorCount, swap);
/*  9623 */         mqSetDetails.setIntAttrCount(intAttrsCount, swap);
/*  9624 */         mqSetDetails.setCharAttrLength(charAttrLength, swap);
/*       */         
/*       */         int j;
/*  9627 */         for (j = 0; j < selectorCount; j++) {
/*  9628 */           dc.writeI32(selectors[j], commsBuff, writePos, swap);
/*  9629 */           writePos += 4;
/*       */         } 
/*       */ 
/*       */         
/*  9633 */         for (j = 0; j < intAttrsCount; j++) {
/*  9634 */           dc.writeI32(intAttrs[j], commsBuff, writePos, swap);
/*  9635 */           writePos += 4;
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  9641 */         if (charAttrLength > 0) {
/*       */           
/*  9643 */           sMQAPI.setUserDataSingle(charAttrs, charAttrLength);
/*       */         } else {
/*       */           
/*  9646 */           sMQAPI.setControlFlags1(48);
/*       */         } 
/*       */ 
/*       */ 
/*       */         
/*  9651 */         int headerLength = writePos - sMQAPI.getRfpOffset();
/*       */         
/*  9653 */         sMQAPI.setTransLength(headerLength);
/*  9654 */         sMQAPI.setCallLength(headerLength + charAttrLength);
/*       */ 
/*       */         
/*  9657 */         remoteSession.sendTSH(tls, (RfpTSH)sMQAPI);
/*       */ 
/*       */         
/*  9660 */         rMQAPI = remoteSession.receiveMQIFlow(tls);
/*       */ 
/*       */         
/*  9663 */         if (rMQAPI.getSegmentType() != 152 || (rMQAPI.getControlFlags1() & 0x20) == 0) {
/*  9664 */           HashMap<String, Object> info = new HashMap<>();
/*  9665 */           info.put("SegmentType", Integer.valueOf(rMQAPI.getSegmentType()));
/*  9666 */           info.put("ControlFlags1", Integer.valueOf(rMQAPI.getControlFlags1()));
/*  9667 */           info.put("Description", "Unexpected flow received from server");
/*  9668 */           Trace.ffst(this, "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "01", info, null);
/*       */           
/*  9670 */           JmqiException traceRet8 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*       */           
/*  9672 */           if (Trace.isOn) {
/*  9673 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", (Throwable)traceRet8, 6);
/*       */           }
/*       */           
/*  9676 */           throw traceRet8;
/*       */         } 
/*       */ 
/*       */ 
/*       */         
/*  9681 */         swap = remoteSession.isSwap();
/*       */ 
/*       */         
/*  9684 */         pCompCode.x = rMQAPI.getCompCode(swap);
/*  9685 */         pReason.x = rMQAPI.getReasonCode(swap);
/*       */       } finally {
/*       */         
/*  9688 */         if (Trace.isOn) {
/*  9689 */           Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)");
/*       */         }
/*       */ 
/*       */ 
/*       */         
/*       */         try {
/*  9695 */           if (remoteHconn != null) {
/*  9696 */             if (rMQAPI != null) {
/*       */               try {
/*  9698 */                 remoteSession.releaseReceivedTSH((RfpTSH)rMQAPI);
/*       */               }
/*  9700 */               catch (JmqiException e) {
/*  9701 */                 if (Trace.isOn) {
/*  9702 */                   Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", (Throwable)e, 1);
/*       */                 }
/*       */               } 
/*       */             }
/*       */ 
/*       */ 
/*       */             
/*  9709 */             remoteHconn.leaveCall(pReason.x);
/*       */           }
/*       */         
/*  9712 */         } catch (JmqiException e) {
/*  9713 */           if (Trace.isOn) {
/*  9714 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", (Throwable)e, 2);
/*       */           }
/*       */         }
/*       */       
/*       */       }
/*       */     
/*       */     }
/*  9721 */     catch (JmqiException e) {
/*  9722 */       if (Trace.isOn) {
/*  9723 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", (Throwable)e, 3);
/*       */       }
/*       */ 
/*       */ 
/*       */       
/*  9728 */       jmqiTls.lastException = e;
/*  9729 */       pCompCode.x = e.getCompCode();
/*  9730 */       pReason.x = e.getReason();
/*       */     } 
/*       */ 
/*       */     
/*  9734 */     if (Trace.isOn) {
/*  9735 */       Trace.data(this, "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "__________");
/*  9736 */       Trace.data(this, "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "MQSET <<");
/*       */       
/*  9738 */       Trace.data(this, "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "Hconn", hconn);
/*  9739 */       Trace.data(this, "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "Hobj", hobj);
/*  9740 */       Trace.data(this, "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "Selectorcount", 
/*  9741 */           Integer.toString(selectorCount));
/*  9742 */       Trace.data(this, "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "Selectors", selectors);
/*       */       
/*  9744 */       Trace.data(this, "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "Intattrcount", 
/*  9745 */           Integer.toString(intAttrsCount));
/*  9746 */       Trace.data(this, "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "Intattrs", intAttrs);
/*  9747 */       Trace.data(this, "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "Charattrlength", 
/*  9748 */           Integer.toString(charAttrLength));
/*  9749 */       Trace.data(this, "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "Charattrs", charAttrs);
/*       */       
/*  9751 */       Trace.data(this, "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "CompCode", pCompCode);
/*  9752 */       Trace.data(this, "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "Reason", pReason);
/*       */       
/*  9754 */       Trace.data(this, "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", "__________");
/*       */     } 
/*       */     
/*  9757 */     if (Trace.isOn) {
/*  9758 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQCMIT(Hconn hconn, Pint pCompCode, Pint pReason) {
/*  9776 */     if (Trace.isOn) {
/*  9777 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQCMIT(Hconn,Pint,Pint)", new Object[] { hconn, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */ 
/*       */     
/*  9782 */     if (Trace.isOn) {
/*  9783 */       Trace.data(this, "MQCMIT(Hconn,Pint,Pint)", "__________");
/*  9784 */       Trace.data(this, "MQCMIT(Hconn,Pint,Pint)", "MQCMIT >>");
/*  9785 */       Trace.data(this, "MQCMIT(Hconn,Pint,Pint)", "Hconn", hconn);
/*  9786 */       Trace.data(this, "MQCMIT(Hconn,Pint,Pint)", "CompCode", pCompCode);
/*  9787 */       Trace.data(this, "MQCMIT(Hconn,Pint,Pint)", "Reason", pReason);
/*  9788 */       Trace.data(this, "MQCMIT(Hconn,Pint,Pint)", "__________");
/*       */     } 
/*  9790 */     RemoteHconn remoteHconn = null;
/*  9791 */     RemoteSession remoteSession = null;
/*  9792 */     RfpMQAPI rMQAPI = null;
/*       */     
/*  9794 */     RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  9795 */     JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/*  9796 */     jmqiTls.lastException = null;
/*       */     try {
/*  9798 */       remoteHconn = getRemoteHconn(tls, hconn);
/*       */ 
/*       */       
/*  9801 */       remoteHconn.enterCall(tls.isDispatchThread, false);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*       */       try {
/*  9808 */         remoteSession = remoteHconn.getSession();
/*  9809 */         remoteSession.checkIfDisconnected();
/*       */         
/*  9811 */         if (remoteHconn.isTransactionDoomed()) {
/*  9812 */           handleDoomedTransaction(remoteHconn);
/*       */         }
/*  9814 */         boolean swap = remoteSession.isSwap();
/*       */         
/*  9816 */         RfpMQAPI sMQAPI = remoteSession.allocateMQAPI(138);
/*       */         
/*  9818 */         sMQAPI.setCallLength(sMQAPI.hdrSize());
/*  9819 */         sMQAPI.setTransLength(sMQAPI.hdrSize());
/*  9820 */         sMQAPI.setControlFlags1(48);
/*       */         
/*  9822 */         remoteSession.sendTSH(tls, (RfpTSH)sMQAPI);
/*       */         
/*  9824 */         rMQAPI = remoteSession.receiveMQIFlow(tls);
/*       */         
/*  9826 */         if (rMQAPI.getSegmentType() != 154 || (rMQAPI.getControlFlags1() & 0x20) == 0) {
/*  9827 */           HashMap<String, Object> info = new HashMap<>();
/*  9828 */           info.put("SegmentType", Integer.valueOf(rMQAPI.getSegmentType()));
/*  9829 */           info.put("ControlFlags1", Integer.valueOf(rMQAPI.getControlFlags1()));
/*  9830 */           info.put("Description", "Unexpected flow received from server");
/*  9831 */           Trace.ffst(this, "MQCMIT(Hconn,Pint,Pint)", "01", info, null);
/*  9832 */           JmqiException traceRet3 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*       */ 
/*       */           
/*  9835 */           if (Trace.isOn) {
/*  9836 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQCMIT(Hconn,Pint,Pint)", (Throwable)traceRet3);
/*       */           }
/*       */           
/*  9839 */           throw traceRet3;
/*       */         } 
/*       */ 
/*       */ 
/*       */         
/*  9844 */         swap = remoteSession.isSwap();
/*       */ 
/*       */         
/*  9847 */         pCompCode.x = rMQAPI.getCompCode(swap);
/*  9848 */         pReason.x = rMQAPI.getReasonCode(swap);
/*       */         
/*  9850 */         remoteHconn.unsetInTransaction();
/*  9851 */         remoteHconn.unsetTransactionDoomed();
/*       */       } finally {
/*       */         
/*  9854 */         if (Trace.isOn) {
/*  9855 */           Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQCMIT(Hconn,Pint,Pint)");
/*       */         }
/*       */ 
/*       */         
/*       */         try {
/*  9860 */           if (remoteHconn != null) {
/*  9861 */             if (rMQAPI != null) {
/*       */               try {
/*  9863 */                 remoteSession.releaseReceivedTSH((RfpTSH)rMQAPI);
/*       */               }
/*  9865 */               catch (JmqiException e) {
/*  9866 */                 if (Trace.isOn) {
/*  9867 */                   Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQCMIT(Hconn,Pint,Pint)", (Throwable)e, 1);
/*       */                 }
/*       */               } 
/*       */             }
/*       */ 
/*       */ 
/*       */             
/*  9874 */             remoteHconn.leaveCall(pReason.x);
/*       */           }
/*       */         
/*  9877 */         } catch (JmqiException e) {
/*  9878 */           if (Trace.isOn) {
/*  9879 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQCMIT(Hconn,Pint,Pint)", (Throwable)e, 2);
/*       */           }
/*       */         }
/*       */       
/*       */       } 
/*  9884 */     } catch (JmqiException e) {
/*  9885 */       if (Trace.isOn) {
/*  9886 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQCMIT(Hconn,Pint,Pint)", (Throwable)e, 3);
/*       */       }
/*       */ 
/*       */       
/*  9890 */       jmqiTls.lastException = e;
/*  9891 */       pCompCode.x = e.getCompCode();
/*  9892 */       pReason.x = e.getReason();
/*       */     } 
/*       */ 
/*       */     
/*  9896 */     if (0 != pReason.x && 
/*  9897 */       remoteHconn.isReconnectable() && 
/*  9898 */       RemoteHconn.isReconnectableReasonCode(pReason.x)) {
/*       */       
/*  9900 */       pCompCode.x = 2;
/*  9901 */       pReason.x = 2549;
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  9907 */     if (Trace.isOn) {
/*  9908 */       Trace.data(this, "MQCMIT(Hconn,Pint,Pint)", "__________");
/*  9909 */       Trace.data(this, "MQCMIT(Hconn,Pint,Pint)", "MQCMIT <<");
/*       */       
/*  9911 */       Trace.data(this, "MQCMIT(Hconn,Pint,Pint)", "Hconn", hconn);
/*  9912 */       Trace.data(this, "MQCMIT(Hconn,Pint,Pint)", "CompCode", pCompCode);
/*  9913 */       Trace.data(this, "MQCMIT(Hconn,Pint,Pint)", "Reason", pReason);
/*       */       
/*  9915 */       Trace.data(this, "MQCMIT(Hconn,Pint,Pint)", "__________");
/*       */     } 
/*       */     
/*  9918 */     if (Trace.isOn) {
/*  9919 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQCMIT(Hconn,Pint,Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQBACK(Hconn hconn, Pint pCompCode, Pint pReason) {
/*  9936 */     if (Trace.isOn) {
/*  9937 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQBACK(Hconn,Pint,Pint)", new Object[] { hconn, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */ 
/*       */     
/*  9942 */     if (Trace.isOn) {
/*  9943 */       Trace.data(this, "MQBACK(Hconn,Pint,Pint)", "__________");
/*  9944 */       Trace.data(this, "MQBACK(Hconn,Pint,Pint)", "MQBACK >>");
/*  9945 */       Trace.data(this, "MQBACK(Hconn,Pint,Pint)", "Hconn", hconn);
/*  9946 */       Trace.data(this, "MQBACK(Hconn,Pint,Pint)", "CompCode", pCompCode);
/*  9947 */       Trace.data(this, "MQBACK(Hconn,Pint,Pint)", "Reason", pReason);
/*  9948 */       Trace.data(this, "MQBACK(Hconn,Pint,Pint)", "__________");
/*       */     } 
/*  9950 */     RemoteHconn remoteHconn = null;
/*  9951 */     RemoteSession remoteSession = null;
/*  9952 */     RfpMQAPI rMQAPI = null;
/*       */     
/*  9954 */     RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  9955 */     JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/*  9956 */     jmqiTls.lastException = null;
/*       */     try {
/*  9958 */       remoteHconn = getRemoteHconn(tls, hconn);
/*       */       
/*  9960 */       remoteHconn.enterCall(tls.isDispatchThread, false);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*       */       try {
/*  9967 */         remoteSession = remoteHconn.getSession();
/*  9968 */         remoteSession.checkIfDisconnected();
/*       */         
/*  9970 */         boolean swap = remoteSession.isSwap();
/*       */         
/*  9972 */         RfpMQAPI sMQAPI = remoteSession.allocateMQAPI(139);
/*       */         
/*  9974 */         sMQAPI.setCallLength(sMQAPI.hdrSize());
/*  9975 */         sMQAPI.setTransLength(sMQAPI.hdrSize());
/*  9976 */         sMQAPI.setControlFlags1(48);
/*       */         
/*  9978 */         remoteSession.sendTSH(tls, (RfpTSH)sMQAPI);
/*       */         
/*  9980 */         rMQAPI = remoteSession.receiveMQIFlow(tls);
/*       */         
/*  9982 */         if (rMQAPI.getSegmentType() != 155 || (rMQAPI.getControlFlags1() & 0x20) == 0) {
/*  9983 */           HashMap<String, Object> info = new HashMap<>();
/*  9984 */           info.put("SegmentType", Integer.valueOf(rMQAPI.getSegmentType()));
/*  9985 */           info.put("ControlFlags1", Integer.valueOf(rMQAPI.getControlFlags1()));
/*  9986 */           info.put("Description", "Unexpected flow received from server");
/*  9987 */           Trace.ffst(this, "MQBACK(Hconn,Pint,Pint)", "01", info, null);
/*  9988 */           JmqiException traceRet3 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*       */ 
/*       */           
/*  9991 */           if (Trace.isOn) {
/*  9992 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQBACK(Hconn,Pint,Pint)", (Throwable)traceRet3);
/*       */           }
/*       */           
/*  9995 */           throw traceRet3;
/*       */         } 
/*       */ 
/*       */ 
/*       */         
/* 10000 */         swap = remoteSession.isSwap();
/*       */ 
/*       */         
/* 10003 */         pCompCode.x = rMQAPI.getCompCode(swap);
/* 10004 */         pReason.x = rMQAPI.getReasonCode(swap);
/*       */ 
/*       */         
/* 10007 */         if (remoteSession.isMultiplexingEnabled()) {
/* 10008 */           remoteHconn.clearAllTxnMessages();
/*       */         }
/* 10010 */         remoteHconn.unsetInTransaction();
/* 10011 */         remoteHconn.unsetTransactionDoomed();
/*       */       } finally {
/*       */         
/* 10014 */         if (Trace.isOn) {
/* 10015 */           Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQBACK(Hconn,Pint,Pint)");
/*       */         }
/*       */ 
/*       */         
/*       */         try {
/* 10020 */           if (remoteHconn != null) {
/* 10021 */             if (rMQAPI != null) {
/*       */               try {
/* 10023 */                 remoteSession.releaseReceivedTSH((RfpTSH)rMQAPI);
/*       */               }
/* 10025 */               catch (JmqiException e) {
/* 10026 */                 if (Trace.isOn) {
/* 10027 */                   Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQBACK(Hconn,Pint,Pint)", (Throwable)e, 1);
/*       */                 }
/*       */               } 
/*       */             }
/*       */ 
/*       */             
/* 10033 */             remoteHconn.leaveCall(pReason.x);
/*       */           }
/*       */         
/* 10036 */         } catch (JmqiException e) {
/* 10037 */           if (Trace.isOn) {
/* 10038 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQBACK(Hconn,Pint,Pint)", (Throwable)e, 2);
/*       */           }
/*       */         }
/*       */       
/*       */       } 
/* 10043 */     } catch (JmqiException e) {
/* 10044 */       if (Trace.isOn) {
/* 10045 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQBACK(Hconn,Pint,Pint)", (Throwable)e, 3);
/*       */       }
/*       */ 
/*       */       
/* 10049 */       jmqiTls.lastException = e;
/* 10050 */       pCompCode.x = e.getCompCode();
/* 10051 */       pReason.x = e.getReason();
/*       */     } 
/*       */ 
/*       */     
/* 10055 */     if (0 != pReason.x && 
/* 10056 */       remoteHconn.isReconnectable() && 
/* 10057 */       RemoteHconn.isReconnectableReasonCode(pReason.x) && 
/* 10058 */       !remoteHconn.hasFailed()) {
/*       */ 
/*       */       
/* 10061 */       pCompCode.x = 0;
/* 10062 */       pReason.x = 0;
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/* 10068 */     if (remoteHconn.hasFailed()) {
/* 10069 */       pCompCode.x = remoteHconn.getReconnectionFailureCompCode();
/* 10070 */       pReason.x = remoteHconn.getReconnectionFailureReason();
/* 10071 */       jmqiTls.lastException = remoteHconn.getReconnectionFailureException();
/*       */     } 
/*       */ 
/*       */     
/* 10075 */     if (Trace.isOn) {
/* 10076 */       Trace.data(this, "MQBACK(Hconn,Pint,Pint)", "__________");
/* 10077 */       Trace.data(this, "MQBACK(Hconn,Pint,Pint)", "MQBACK <<");
/* 10078 */       Trace.data(this, "MQBACK(Hconn,Pint,Pint)", "Hconn", hconn);
/* 10079 */       Trace.data(this, "MQBACK(Hconn,Pint,Pint)", "CompCode", pCompCode);
/* 10080 */       Trace.data(this, "MQBACK(Hconn,Pint,Pint)", "Reason", pReason);
/* 10081 */       Trace.data(this, "MQBACK(Hconn,Pint,Pint)", "__________");
/*       */     } 
/*       */     
/* 10084 */     if (Trace.isOn) {
/* 10085 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQBACK(Hconn,Pint,Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQSTAT(Hconn hconn, int type, MQSTS stat, Pint pCompCode, Pint pReason) {
/* 10106 */     if (Trace.isOn) {
/* 10107 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQSTAT(Hconn,int,MQSTS,Pint,Pint)", new Object[] { hconn, 
/* 10108 */             Integer.valueOf(type), stat, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */     
/* 10112 */     if (Trace.isOn) {
/* 10113 */       Trace.data(this, "MQSTAT(Hconn,int,MQSTS,Pint,Pint)", "__________");
/* 10114 */       Trace.data(this, "MQSTAT(Hconn,int,MQSTS,Pint,Pint)", "MQSTAT >>");
/* 10115 */       Trace.data(this, "MQSTAT(Hconn,int,MQSTS,Pint,Pint)", "Hconn", hconn);
/* 10116 */       Trace.data(this, "MQSTAT(Hconn,int,MQSTS,Pint,Pint)", "Type", "0x" + Integer.toHexString(type));
/* 10117 */       Trace.data(this, "MQSTAT(Hconn,int,MQSTS,Pint,Pint)", "Stat", stat);
/* 10118 */       Trace.data(this, "MQSTAT(Hconn,int,MQSTS,Pint,Pint)", "CompCode", pCompCode);
/* 10119 */       Trace.data(this, "MQSTAT(Hconn,int,MQSTS,Pint,Pint)", "Reason", pReason);
/* 10120 */       Trace.data(this, "MQSTAT(Hconn,int,MQSTS,Pint,Pint)", "__________");
/*       */     } 
/* 10122 */     RemoteHconn remoteHconn = null;
/* 10123 */     RfpMQAPI rMQAPI = null;
/*       */ 
/*       */     
/* 10126 */     if (stat != null) {
/* 10127 */       stat.getObjectString().setRemote(true);
/* 10128 */       stat.getSubName().setRemote(true);
/*       */     } 
/*       */     
/* 10131 */     RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 10132 */     JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/* 10133 */     jmqiTls.lastException = null;
/* 10134 */     RemoteSession remoteSession = null;
/*       */     try {
/* 10136 */       remoteHconn = getRemoteHconn(tls, hconn);
/*       */       
/* 10138 */       if (stat == null) {
/* 10139 */         JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2426, null);
/*       */ 
/*       */         
/* 10142 */         if (Trace.isOn) {
/* 10143 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQSTAT(Hconn,int,MQSTS,Pint,Pint)", (Throwable)traceRet1, 1);
/*       */         }
/*       */         
/* 10146 */         throw traceRet1;
/*       */       } 
/*       */ 
/*       */       
/* 10150 */       remoteHconn.enterCall(tls.isDispatchThread, false);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*       */       try {
/* 10159 */         remoteSession = remoteHconn.getSession();
/* 10160 */         remoteSession.checkIfDisconnected();
/*       */         
/* 10162 */         JmqiCodepage cp = remoteSession.getCp();
/* 10163 */         boolean swap = remoteSession.isSwap();
/*       */ 
/*       */         
/* 10166 */         if (remoteHconn.getFapLevel() < 9) {
/* 10167 */           JmqiException traceRet2 = new JmqiException(this.env, -1, null, 2, 2298, null);
/*       */           
/* 10169 */           if (Trace.isOn) {
/* 10170 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQSTAT(Hconn,int,MQSTS,Pint,Pint)", (Throwable)traceRet2, 2);
/*       */           }
/*       */           
/* 10173 */           throw traceRet2;
/*       */         } 
/*       */ 
/*       */         
/* 10177 */         RfpMQAPI sMQAPI = remoteSession.allocateMQAPI(141);
/* 10178 */         int writePos = sMQAPI.getRfpOffset() + sMQAPI.hdrSize();
/* 10179 */         byte[] commsBuff = sMQAPI.getRfpBuffer();
/* 10180 */         RfpMQSTAT mqstatDetails = (RfpMQSTAT)remoteSession.getRfp(15, (RfpStructure)sMQAPI, writePos);
/*       */         
/* 10182 */         writePos += 4;
/*       */         
/* 10184 */         mqstatDetails.setType(type, swap);
/*       */         
/* 10186 */         writePos = stat.writeToBuffer(commsBuff, writePos, 4, swap, cp, jmqiTls);
/*       */         
/* 10188 */         int requestLength = writePos - sMQAPI.getRfpOffset();
/*       */         
/* 10190 */         sMQAPI.setTransLength(requestLength);
/* 10191 */         sMQAPI.setCallLength(requestLength);
/* 10192 */         sMQAPI.setControlFlags1(48);
/*       */ 
/*       */         
/* 10195 */         remoteSession.sendTSH(tls, (RfpTSH)sMQAPI);
/*       */ 
/*       */         
/* 10198 */         rMQAPI = remoteSession.receiveMQIFlow(tls);
/*       */         
/* 10200 */         if (rMQAPI.getSegmentType() != 157 || (rMQAPI.getControlFlags1() & 0x20) == 0) {
/* 10201 */           HashMap<String, Object> info = new HashMap<>();
/* 10202 */           info.put("SegmentType", Integer.valueOf(rMQAPI.getSegmentType()));
/* 10203 */           info.put("ControlFlags1", Integer.valueOf(rMQAPI.getControlFlags1()));
/* 10204 */           info.put("Description", "Unexpected flow received from server");
/* 10205 */           Trace.ffst(this, "MQSTAT(Hconn,int,MQSTS,Pint,Pint)", "01", info, null);
/* 10206 */           JmqiException traceRet5 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*       */           
/* 10208 */           if (Trace.isOn) {
/* 10209 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQSTAT(Hconn,int,MQSTS,Pint,Pint)", (Throwable)traceRet5, 3);
/*       */           }
/*       */           
/* 10212 */           throw traceRet5;
/*       */         } 
/*       */ 
/*       */ 
/*       */         
/* 10217 */         swap = remoteSession.isSwap();
/*       */ 
/*       */         
/* 10220 */         pCompCode.x = rMQAPI.getCompCode(swap);
/* 10221 */         pReason.x = rMQAPI.getReasonCode(swap);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/* 10227 */         if (pCompCode.x != 2) {
/*       */           
/* 10229 */           int readPos = rMQAPI.getRfpOffset() + rMQAPI.hdrSize() + 4;
/*       */           
/* 10231 */           commsBuff = rMQAPI.getRfpBuffer();
/*       */           
/* 10233 */           stat.readFromBuffer(commsBuff, readPos, 4, swap, cp, jmqiTls);
/*       */         } 
/*       */       } finally {
/*       */         
/* 10237 */         if (Trace.isOn) {
/* 10238 */           Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQSTAT(Hconn,int,MQSTS,Pint,Pint)");
/*       */         }
/*       */ 
/*       */ 
/*       */         
/* 10243 */         stat.getObjectString().setRemote(false);
/* 10244 */         stat.getSubName().setRemote(false);
/*       */ 
/*       */         
/*       */         try {
/* 10248 */           if (remoteHconn != null) {
/* 10249 */             if (rMQAPI != null) {
/*       */               try {
/* 10251 */                 remoteSession.releaseReceivedTSH((RfpTSH)rMQAPI);
/*       */               }
/* 10253 */               catch (JmqiException e) {
/* 10254 */                 if (Trace.isOn) {
/* 10255 */                   Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQSTAT(Hconn,int,MQSTS,Pint,Pint)", (Throwable)e, 1);
/*       */                 }
/*       */               } 
/*       */             }
/*       */ 
/*       */             
/* 10261 */             remoteHconn.leaveCall(pReason.x);
/*       */           }
/*       */         
/* 10264 */         } catch (JmqiException e) {
/* 10265 */           if (Trace.isOn) {
/* 10266 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQSTAT(Hconn,int,MQSTS,Pint,Pint)", (Throwable)e, 2);
/*       */           }
/*       */         }
/*       */       
/*       */       }
/*       */     
/* 10272 */     } catch (JmqiException e) {
/* 10273 */       if (Trace.isOn) {
/* 10274 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQSTAT(Hconn,int,MQSTS,Pint,Pint)", (Throwable)e, 3);
/*       */       }
/*       */ 
/*       */ 
/*       */       
/* 10279 */       jmqiTls.lastException = e;
/* 10280 */       pCompCode.x = e.getCompCode();
/* 10281 */       pReason.x = e.getReason();
/*       */     } 
/*       */ 
/*       */     
/* 10285 */     if (0 != pReason.x && 
/* 10286 */       remoteHconn.isReconnectable() && 
/* 10287 */       RemoteHconn.isReconnectableReasonCode(pReason.x) && 
/* 10288 */       !remoteHconn.hasFailed()) {
/*       */       try {
/* 10290 */         remoteHconn.initiateReconnection(remoteSession);
/* 10291 */         MQSTAT(hconn, type, stat, pCompCode, pReason);
/*       */       }
/* 10293 */       catch (JmqiException je) {
/* 10294 */         if (Trace.isOn) {
/* 10295 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQSTAT(Hconn,int,MQSTS,Pint,Pint)", (Throwable)je);
/*       */         }
/*       */       } 
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/* 10305 */     if (remoteHconn.hasFailed()) {
/* 10306 */       pCompCode.x = remoteHconn.getReconnectionFailureCompCode();
/* 10307 */       pReason.x = remoteHconn.getReconnectionFailureReason();
/* 10308 */       jmqiTls.lastException = remoteHconn.getReconnectionFailureException();
/*       */     } 
/*       */ 
/*       */     
/* 10312 */     if (Trace.isOn) {
/* 10313 */       Trace.data(this, "MQSTAT(Hconn,int,MQSTS,Pint,Pint)", "__________");
/* 10314 */       Trace.data(this, "MQSTAT(Hconn,int,MQSTS,Pint,Pint)", "MQSTAT <<");
/*       */       
/* 10316 */       Trace.data(this, "MQSTAT(Hconn,int,MQSTS,Pint,Pint)", "Hconn", hconn);
/* 10317 */       Trace.data(this, "MQSTAT(Hconn,int,MQSTS,Pint,Pint)", "Type", "0x" + Integer.toHexString(type));
/* 10318 */       Trace.data(this, "MQSTAT(Hconn,int,MQSTS,Pint,Pint)", "Stat", stat);
/* 10319 */       Trace.data(this, "MQSTAT(Hconn,int,MQSTS,Pint,Pint)", "CompCode", pCompCode);
/* 10320 */       Trace.data(this, "MQSTAT(Hconn,int,MQSTS,Pint,Pint)", "Reason", pReason);
/*       */       
/* 10322 */       Trace.data(this, "MQSTAT(Hconn,int,MQSTS,Pint,Pint)", "__________");
/*       */     } 
/*       */     
/* 10325 */     if (Trace.isOn) {
/* 10326 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQSTAT(Hconn,int,MQSTS,Pint,Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQBEGIN(Hconn hconn, MQBO beginOptions, Pint pCompCode, Pint pReason) {
/* 10345 */     if (Trace.isOn) {
/* 10346 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQBEGIN(Hconn,MQBO,Pint,Pint)", new Object[] { hconn, beginOptions, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */     
/* 10352 */     pCompCode.x = 2;
/* 10353 */     pReason.x = 2012;
/*       */     
/* 10355 */     if (hconn instanceof RemoteHconn) {
/* 10356 */       RemoteHconn remoteHconn = (RemoteHconn)hconn;
/* 10357 */       if (remoteHconn.getShareOption() != 32) {
/* 10358 */         pCompCode.x = 2;
/* 10359 */         pReason.x = 2018;
/*       */       } 
/*       */     } 
/*       */     
/* 10363 */     if (Trace.isOn) {
/* 10364 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "MQBEGIN(Hconn,MQBO,Pint,Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void spiConnect(String pQMgrName, LpiPrivConnStruct pSpiConnectOpts, MQCNO pConnectOpts, Phconn pHconn, Pint pCompCode, Pint pReason) {
/* 10389 */     if (Trace.isOn) {
/* 10390 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiConnect(String,LpiPrivConnStruct,MQCNO,Phconn,Pint,Pint)", new Object[] { pQMgrName, pSpiConnectOpts, pConnectOpts, pHconn, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */ 
/*       */     
/* 10395 */     MQCONNX(pQMgrName, pConnectOpts, pHconn, pCompCode, pReason);
/*       */     
/* 10397 */     if (Trace.isOn) {
/* 10398 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiConnect(String,LpiPrivConnStruct,MQCNO,Phconn,Pint,Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   private String getXAOpenStrParam(String xaOpenStr, String xaOpenStrLC, String param) {
/* 10416 */     if (Trace.isOn) {
/* 10417 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "getXAOpenStrParam(String,String,String)", new Object[] { xaOpenStr, xaOpenStrLC, param });
/*       */     }
/*       */     
/* 10420 */     String value = null;
/*       */     
/* 10422 */     int xaOpenStrLen = xaOpenStrLC.length();
/* 10423 */     int paramIndex = xaOpenStrLC.indexOf(param);
/* 10424 */     if (paramIndex >= 0) {
/*       */       
/* 10426 */       paramIndex += param.length();
/* 10427 */       if (xaOpenStrLen > paramIndex) {
/*       */         
/* 10429 */         int valBeginIndex = xaOpenStr.indexOf('=', paramIndex);
/* 10430 */         if (valBeginIndex > 0) {
/*       */           
/*       */           do {
/* 10433 */             valBeginIndex++;
/*       */           }
/* 10435 */           while (xaOpenStrLen > valBeginIndex && xaOpenStr.charAt(valBeginIndex) == ' ');
/*       */ 
/*       */           
/* 10438 */           if (xaOpenStrLen > valBeginIndex) {
/*       */ 
/*       */             
/* 10441 */             int valueEndIndex = xaOpenStr.indexOf(',', valBeginIndex);
/* 10442 */             if (valueEndIndex < 0) {
/* 10443 */               valueEndIndex = xaOpenStrLen;
/*       */             }
/*       */             
/*       */             do {
/* 10447 */               valueEndIndex--;
/*       */             }
/* 10449 */             while (valueEndIndex > 0 && xaOpenStr.charAt(valueEndIndex) == ' ');
/*       */             
/* 10451 */             if (valueEndIndex - valBeginIndex > 0)
/*       */             {
/* 10453 */               value = xaOpenStr.substring(valBeginIndex, valueEndIndex + 1);
/*       */             }
/*       */           } 
/*       */         } 
/*       */       } 
/*       */     } 
/*       */     
/* 10460 */     if (Trace.isOn) {
/* 10461 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "getXAOpenStrParam(String,String,String)", value);
/*       */     }
/* 10463 */     return value;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int xa_open(Hconn hconn, String xaOpenStr, int rmid, int flags) {
/* 10490 */     if (Trace.isOn) {
/* 10491 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_open(Hconn,String,int,int)", new Object[] { hconn, xaOpenStr, 
/* 10492 */             Integer.valueOf(rmid), Integer.valueOf(flags) });
/*       */     }
/*       */     
/* 10495 */     int rc = 0;
/* 10496 */     RemoteHconn remoteHconn = null;
/* 10497 */     RemoteSession remoteSession = null;
/* 10498 */     RfpMQAPI rMQAPI = null;
/*       */     
/* 10500 */     RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 10501 */     JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/* 10502 */     jmqiTls.lastException = null;
/*       */     try {
/* 10504 */       remoteHconn = getRemoteHconn(tls, hconn);
/*       */       
/* 10506 */       if (rc == 0 && xaOpenStr == null) {
/* 10507 */         if (Trace.isOn) {
/* 10508 */           Trace.data(this, "setting rc=XAER_INVAL due to null xaOpenStr", null);
/*       */         }
/* 10510 */         rc = -5;
/*       */       } 
/*       */       
/* 10513 */       if (rc == 0 && (flags & Integer.MIN_VALUE) != 0) {
/* 10514 */         if (Trace.isOn) {
/* 10515 */           Trace.data(this, "setting rc=XAER_ASYNC ", null);
/*       */         }
/* 10517 */         rc = -2;
/*       */       } 
/* 10519 */       if (rc == 0 && flags != 0) {
/* 10520 */         if (Trace.isOn) {
/* 10521 */           Trace.data(this, "setting rc=XAER_INVAL due to flags != TMNOFLAGS ", Integer.valueOf(flags));
/*       */         }
/* 10523 */         rc = -5;
/*       */       } 
/* 10525 */       String xaOpenStrQMName = null;
/* 10526 */       if (rc == 0) {
/* 10527 */         xaOpenStrQMName = getXAOpenStrParam(xaOpenStr, xaOpenStr.toLowerCase(), "qmname");
/* 10528 */         if (xaOpenStrQMName == null) {
/*       */           
/* 10530 */           if (Trace.isOn) {
/* 10531 */             Trace.data(this, "setting rc=XAER_INVAL as xaOpenStrQMName is null", null);
/*       */           }
/* 10533 */           rc = -5;
/*       */         } 
/*       */       } 
/*       */ 
/*       */       
/* 10538 */       if (!this.clientCfg.getBoolValue(Configuration.disableXACCDTCheck))
/*       */       {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/* 10545 */         if (rc == 0 && (
/* 10546 */           xaOpenStrQMName.charAt(0) == '*' || (
/* 10547 */           !xaOpenStrQMName.equals(remoteHconn.getSession().getRemoteQmgrName()) && 
/* 10548 */           !xaOpenStrQMName.equals(remoteHconn.getOriginalQueueManagerName()) && 
/* 10549 */           !xaOpenStrQMName.equals(remoteHconn.getQsgName())))) {
/*       */ 
/*       */           
/* 10552 */           if (Trace.isOn) {
/* 10553 */             Trace.data(this, "setting rc=XAER_INVAL as xaOpenStrQMName is invalid: ", xaOpenStrQMName);
/*       */           }
/* 10555 */           rc = -5;
/*       */         } 
/*       */       }
/*       */ 
/*       */       
/* 10560 */       if (rc == 0) {
/*       */         
/* 10562 */         remoteHconn.enterCall(tls.isDispatchThread, false);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         try {
/* 10570 */           remoteSession = remoteHconn.getSession();
/* 10571 */           JmqiCodepage cp = remoteSession.getCp();
/*       */           
/* 10573 */           remoteSession.checkIfDisconnected();
/*       */           
/* 10575 */           boolean swap = remoteSession.isSwap();
/*       */ 
/*       */           
/* 10578 */           boolean callRequired = true;
/* 10579 */           if (rc == 0) {
/* 10580 */             if ((remoteSession.getXaState() & 0x2) != 0) {
/*       */ 
/*       */               
/* 10583 */               if (remoteSession.getRmid() != rmid) {
/* 10584 */                 if (Trace.isOn) {
/* 10585 */                   Trace.data(this, "setting rc=XAER_INVAL as remoteSession.getRmid() != rmid: ", 
/* 10586 */                       Integer.valueOf(rmid));
/*       */                 }
/* 10588 */                 rc = -5;
/*       */               } 
/* 10590 */               callRequired = false;
/*       */             }
/* 10592 */             else if ((remoteSession.getXaState() & 0x4) != 0) {
/*       */ 
/*       */ 
/*       */               
/* 10596 */               if (Trace.isOn) {
/* 10597 */                 Trace.data(this, "setting rc=XAER_PROTO as transction alread active", null);
/*       */               }
/* 10599 */               rc = -6;
/*       */             } 
/*       */           }
/*       */ 
/*       */ 
/*       */           
/* 10605 */           if (rc == 0 && callRequired) {
/*       */             
/* 10607 */             RfpMQAPI sMQAPI = remoteSession.allocateMQAPI(163);
/*       */             
/* 10609 */             sMQAPI.setRMID(rmid, swap);
/* 10610 */             sMQAPI.setFlags(flags, swap);
/*       */             
/* 10612 */             RfpXA_INFO xaInfo = (RfpXA_INFO)remoteSession.getRfp(12, (RfpStructure)sMQAPI, sMQAPI
/* 10613 */                 .hdrSize());
/*       */ 
/*       */ 
/*       */             
/* 10617 */             xaInfo.setXaInfo(xaOpenStrQMName, cp, jmqiTls);
/*       */             
/* 10619 */             sMQAPI.setControlFlags1(48);
/* 10620 */             sMQAPI.setTransLength(sMQAPI.hdrSize() + 257);
/* 10621 */             sMQAPI.setCallLength(sMQAPI.hdrSize() + 257);
/*       */             
/* 10623 */             remoteSession.sendTSH(tls, (RfpTSH)sMQAPI);
/*       */             
/* 10625 */             rMQAPI = remoteSession.receiveMQIFlow(tls);
/* 10626 */             if (rMQAPI.getSegmentType() != 179 || (rMQAPI.getControlFlags1() & 0x20) == 0) {
/* 10627 */               HashMap<String, Object> info = new HashMap<>();
/* 10628 */               info.put("SegmentType", Integer.valueOf(rMQAPI.getSegmentType()));
/* 10629 */               info.put("ControlFlags1", Integer.valueOf(rMQAPI.getControlFlags1()));
/* 10630 */               info.put("Description", "Unexpected flow received from server");
/* 10631 */               Trace.ffst(this, "xa_open(Hconn,String,int,int)", "01", info, null);
/* 10632 */               JmqiException traceRet3 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*       */ 
/*       */               
/* 10635 */               if (Trace.isOn) {
/* 10636 */                 Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_open(Hconn,String,int,int)", (Throwable)traceRet3);
/*       */               }
/*       */               
/* 10639 */               throw traceRet3;
/*       */             } 
/*       */ 
/*       */ 
/*       */             
/* 10644 */             swap = remoteSession.isSwap();
/*       */ 
/*       */ 
/*       */             
/* 10648 */             rc = rMQAPI.getReturnCode(swap);
/*       */             
/* 10650 */             if (Trace.isOn) {
/* 10651 */               Trace.data(this, "setting rc as returned by the QM: ", Integer.valueOf(rc));
/*       */             }
/*       */             
/* 10654 */             remoteSession.setXaState(remoteSession.getXaState() | 0x2);
/* 10655 */             remoteSession.setRmid(rmid);
/*       */           } 
/*       */         } finally {
/*       */           
/* 10659 */           if (Trace.isOn) {
/* 10660 */             Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_open(Hconn,String,int,int)");
/*       */           }
/*       */ 
/*       */ 
/*       */           
/*       */           try {
/* 10666 */             if (remoteHconn != null) {
/* 10667 */               if (rMQAPI != null) {
/*       */                 try {
/* 10669 */                   remoteSession.releaseReceivedTSH((RfpTSH)rMQAPI);
/*       */                 }
/* 10671 */                 catch (JmqiException e) {
/* 10672 */                   if (Trace.isOn) {
/* 10673 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_open(Hconn,String,int,int)", (Throwable)e, 1);
/*       */                   }
/*       */                 } 
/*       */               }
/*       */ 
/*       */               
/* 10679 */               remoteHconn.leaveCall(rc);
/*       */             }
/*       */           
/* 10682 */           } catch (JmqiException e) {
/* 10683 */             if (Trace.isOn) {
/* 10684 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_open(Hconn,String,int,int)", (Throwable)e, 2);
/*       */             }
/*       */           }
/*       */         
/*       */         }
/*       */       
/*       */       } 
/* 10691 */     } catch (JmqiException e) {
/* 10692 */       if (Trace.isOn) {
/* 10693 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_open(Hconn,String,int,int)", (Throwable)e, 3);
/*       */       }
/* 10695 */       jmqiTls.lastException = e;
/* 10696 */       rc = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */     } 
/*       */     
/* 10699 */     if (Trace.isOn) {
/* 10700 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_open(Hconn,String,int,int)", 
/* 10701 */           Integer.valueOf(rc));
/*       */     }
/* 10703 */     return rc;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int xa_close(Hconn hconn, String xaCloseStr, int rmid, int flags) {
/* 10721 */     if (Trace.isOn) {
/* 10722 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_close(Hconn,String,int,int)", new Object[] { hconn, xaCloseStr, 
/* 10723 */             Integer.valueOf(rmid), Integer.valueOf(flags) });
/*       */     }
/*       */     
/* 10726 */     int rc = 0;
/* 10727 */     RemoteHconn remoteHconn = null;
/* 10728 */     RemoteSession remoteSession = null;
/* 10729 */     RfpMQAPI rMQAPI = null;
/*       */     
/* 10731 */     RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 10732 */     JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/* 10733 */     jmqiTls.lastException = null;
/*       */     try {
/* 10735 */       remoteHconn = getRemoteHconn(tls, hconn);
/*       */       
/* 10737 */       if (rc == 0 && xaCloseStr == null) {
/* 10738 */         if (Trace.isOn) {
/* 10739 */           Trace.data(this, "setting rc to XAER_INVAL due to xaCloseStr being null", null);
/*       */         }
/* 10741 */         rc = -5;
/*       */       } 
/*       */       
/* 10744 */       if (rc == 0 && (flags & Integer.MIN_VALUE) != 0) {
/* 10745 */         if (Trace.isOn) {
/* 10746 */           Trace.data(this, "setting rc=XAER_ASYNC ", null);
/*       */         }
/* 10748 */         rc = -2;
/*       */       } 
/* 10750 */       if (rc == 0 && flags != 0) {
/* 10751 */         if (Trace.isOn) {
/* 10752 */           Trace.data(this, "setting rc=XAER_INVAL due to flags != TMNOFLAGS ", Integer.valueOf(flags));
/*       */         }
/* 10754 */         rc = -5;
/*       */       } 
/* 10756 */       if (rc == 0) {
/*       */         
/* 10758 */         remoteHconn.enterCall(tls.isDispatchThread, false);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         try {
/* 10766 */           remoteSession = remoteHconn.getSession();
/* 10767 */           remoteSession.checkIfDisconnected();
/* 10768 */           boolean swap = remoteSession.isSwap();
/* 10769 */           JmqiCodepage cp = remoteSession.getCp();
/*       */ 
/*       */           
/* 10772 */           if ((remoteSession.getXaState() & 0x2) == 0) {
/* 10773 */             if (Trace.isOn) {
/* 10774 */               Trace.data(this, "setting rc=XAER_RMFAIL XAState is not opened", null);
/*       */             }
/* 10776 */             rc = -7;
/*       */           } 
/*       */ 
/*       */           
/* 10780 */           if (rc == 0 && remoteSession.getRmid() != rmid) {
/* 10781 */             if (Trace.isOn) {
/* 10782 */               Trace.data(this, "setting rc=XAER_INVAL as remoteSession.getRmid() != rmid ", 
/* 10783 */                   Integer.valueOf(rmid));
/*       */             }
/* 10785 */             rc = -5;
/*       */           } 
/*       */ 
/*       */           
/* 10789 */           if (rc == 0) {
/*       */             
/* 10791 */             RfpMQAPI sMQAPI = remoteSession.allocateMQAPI(164);
/* 10792 */             sMQAPI.setRMID(rmid, swap);
/* 10793 */             sMQAPI.setFlags(flags, swap);
/*       */             
/* 10795 */             RfpXA_INFO xaInfo = (RfpXA_INFO)remoteSession.getRfp(12, (RfpStructure)sMQAPI, sMQAPI
/* 10796 */                 .hdrSize());
/*       */             
/* 10798 */             xaInfo.setXaInfo(xaCloseStr, cp, jmqiTls);
/*       */             
/* 10800 */             sMQAPI.setControlFlags1(48);
/* 10801 */             sMQAPI.setTransLength(sMQAPI.hdrSize() + 257);
/* 10802 */             sMQAPI.setCallLength(sMQAPI.hdrSize() + 257);
/*       */             
/* 10804 */             remoteSession.sendTSH(tls, (RfpTSH)sMQAPI);
/*       */             
/* 10806 */             rMQAPI = remoteSession.receiveMQIFlow(tls);
/* 10807 */             if (rMQAPI.getSegmentType() != 180 || (rMQAPI.getControlFlags1() & 0x20) == 0) {
/* 10808 */               HashMap<String, Object> info = new HashMap<>();
/* 10809 */               info.put("SegmentType", Integer.valueOf(rMQAPI.getSegmentType()));
/* 10810 */               info.put("ControlFlags1", Integer.valueOf(rMQAPI.getControlFlags1()));
/* 10811 */               info.put("Description", "Unexpected flow received from server");
/* 10812 */               Trace.ffst(this, "xa_close(Hconn,String,int,int)", "01", info, null);
/* 10813 */               JmqiException traceRet3 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*       */ 
/*       */               
/* 10816 */               if (Trace.isOn) {
/* 10817 */                 Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_close(Hconn,String,int,int)", (Throwable)traceRet3);
/*       */               }
/*       */               
/* 10820 */               throw traceRet3;
/*       */             } 
/*       */ 
/*       */ 
/*       */             
/* 10825 */             swap = remoteSession.isSwap();
/*       */ 
/*       */ 
/*       */ 
/*       */             
/* 10830 */             rc = rMQAPI.getReturnCode(swap);
/*       */             
/* 10832 */             if (Trace.isOn) {
/* 10833 */               Trace.data(this, "setting rc as returned by the QM: ", Integer.valueOf(rc));
/*       */             }
/*       */             
/* 10836 */             remoteSession.setXaState(remoteSession.getXaState() & 0xFFFFFFFD);
/* 10837 */             remoteSession.setRmid(-1);
/*       */           } 
/*       */         } finally {
/*       */           
/* 10841 */           if (Trace.isOn) {
/* 10842 */             Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_close(Hconn,String,int,int)");
/*       */           }
/*       */ 
/*       */ 
/*       */           
/*       */           try {
/* 10848 */             if (remoteHconn != null) {
/* 10849 */               if (rMQAPI != null) {
/*       */                 try {
/* 10851 */                   remoteSession.releaseReceivedTSH((RfpTSH)rMQAPI);
/*       */                 }
/* 10853 */                 catch (JmqiException e) {
/* 10854 */                   if (Trace.isOn) {
/* 10855 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_close(Hconn,String,int,int)", (Throwable)e, 1);
/*       */                   }
/*       */                 } 
/*       */               }
/*       */ 
/*       */               
/* 10861 */               remoteHconn.leaveCall(rc);
/*       */             }
/*       */           
/* 10864 */           } catch (JmqiException e) {
/* 10865 */             if (Trace.isOn) {
/* 10866 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_close(Hconn,String,int,int)", (Throwable)e, 2);
/*       */             }
/*       */           }
/*       */         
/*       */         }
/*       */       
/*       */       } 
/* 10873 */     } catch (JmqiException e) {
/* 10874 */       if (Trace.isOn) {
/* 10875 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_close(Hconn,String,int,int)", (Throwable)e, 3);
/*       */       }
/* 10877 */       jmqiTls.lastException = e;
/* 10878 */       rc = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */     } 
/*       */     
/* 10881 */     if (Trace.isOn) {
/* 10882 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_close(Hconn,String,int,int)", 
/* 10883 */           Integer.valueOf(rc));
/*       */     }
/* 10885 */     return rc;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int xa_start(Hconn hconn, Xid xid, int rmid, int flags) {
/* 10903 */     if (Trace.isOn) {
/* 10904 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_start(Hconn,Xid,int,int)", new Object[] { hconn, xid, 
/* 10905 */             Integer.valueOf(rmid), Integer.valueOf(flags) });
/*       */     }
/*       */     
/* 10908 */     int rc = 0;
/* 10909 */     RemoteHconn remoteHconn = null;
/* 10910 */     RemoteSession remoteSession = null;
/* 10911 */     RfpMQAPI rMQAPI = null;
/*       */     
/* 10913 */     RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 10914 */     JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/* 10915 */     jmqiTls.lastException = null;
/*       */     try {
/* 10917 */       remoteHconn = getRemoteHconn(tls, hconn);
/*       */       
/* 10919 */       if (rc == 0 && xid == null) {
/* 10920 */         if (Trace.isOn) {
/* 10921 */           Trace.data(this, "setting rc=XAER_INVAL as xid is null", null);
/*       */         }
/* 10923 */         rc = -5;
/*       */       } 
/*       */       
/* 10926 */       if (rc == 0 && (flags & Integer.MIN_VALUE) != 0) {
/* 10927 */         if (Trace.isOn) {
/* 10928 */           Trace.data(this, "setting rc=XAER_ASYNC", null);
/*       */         }
/* 10930 */         rc = -2;
/*       */       } 
/* 10932 */       if (rc == 0 && (flags & 0xE7DFFFFF) != 0) {
/* 10933 */         if (Trace.isOn) {
/* 10934 */           Trace.data(this, "setting rc=XAER_INVAL XAState as flags & ~(TMNOWAIT | TMRESUME | TMJOIN))", null);
/*       */         }
/*       */         
/* 10937 */         rc = -5;
/*       */       } 
/* 10939 */       if (rc == 0)
/*       */       {
/* 10941 */         remoteHconn.enterCall(tls.isDispatchThread, false);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         try {
/* 10949 */           remoteSession = remoteHconn.getSession();
/* 10950 */           remoteSession.checkIfDisconnected();
/* 10951 */           boolean swap = remoteSession.isSwap();
/*       */ 
/*       */           
/* 10954 */           if ((remoteSession.getXaState() & 0x2) == 0) {
/* 10955 */             if (Trace.isOn) {
/* 10956 */               Trace.data(this, "setting rc=XAER_RMFAIL XAState is not Opened", null);
/*       */             }
/* 10958 */             rc = -7;
/*       */           } 
/*       */ 
/*       */           
/* 10962 */           if (rc == 0 && remoteSession.getRmid() != rmid) {
/* 10963 */             if (Trace.isOn) {
/* 10964 */               Trace.data(this, "setting rc=XAER_INVAL as remoteSession.getRmid() != rmid ", 
/* 10965 */                   Integer.valueOf(rmid));
/*       */             }
/* 10967 */             rc = -5;
/*       */           } 
/*       */ 
/*       */           
/* 10971 */           if (rc == 0) {
/*       */             
/* 10973 */             RfpMQAPI sMQAPI = remoteSession.allocateMQAPI(161);
/* 10974 */             sMQAPI.setRMID(rmid, swap);
/* 10975 */             sMQAPI.setFlags(flags, swap);
/*       */             
/* 10977 */             RfpXAID xaid = (RfpXAID)remoteSession.getRfp(13, (RfpStructure)sMQAPI, sMQAPI.hdrSize());
/* 10978 */             xaid.setXid(xid, swap);
/*       */             
/* 10980 */             sMQAPI.setControlFlags1(48);
/* 10981 */             sMQAPI.setTransLength(sMQAPI.hdrSize() + xaid.getRoundedLength());
/* 10982 */             sMQAPI.setCallLength(sMQAPI.hdrSize() + xaid.getRoundedLength());
/*       */             
/* 10984 */             remoteSession.sendTSH(tls, (RfpTSH)sMQAPI);
/*       */             
/* 10986 */             rMQAPI = remoteSession.receiveMQIFlow(tls);
/* 10987 */             if (rMQAPI.getSegmentType() != 177 || (rMQAPI.getControlFlags1() & 0x20) == 0) {
/* 10988 */               HashMap<String, Object> info = new HashMap<>();
/* 10989 */               info.put("SegmentType", Integer.valueOf(rMQAPI.getSegmentType()));
/* 10990 */               info.put("ControlFlags1", Integer.valueOf(rMQAPI.getControlFlags1()));
/* 10991 */               info.put("Description", "Unexpected flow received from server");
/* 10992 */               Trace.ffst(this, "xa_start(Hconn,Xid,int,int)", "01", info, null);
/* 10993 */               JmqiException traceRet3 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*       */ 
/*       */               
/* 10996 */               if (Trace.isOn) {
/* 10997 */                 Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_start(Hconn,Xid,int,int)", (Throwable)traceRet3);
/*       */               }
/*       */               
/* 11000 */               throw traceRet3;
/*       */             } 
/*       */ 
/*       */ 
/*       */             
/* 11005 */             swap = remoteSession.isSwap();
/*       */ 
/*       */             
/* 11008 */             rc = rMQAPI.getReturnCode(swap);
/*       */             
/* 11010 */             if (Trace.isOn) {
/* 11011 */               Trace.data(this, "setting rc as returned by the QM: ", Integer.valueOf(rc));
/*       */             }
/*       */ 
/*       */             
/* 11015 */             if (rc == 0) {
/* 11016 */               remoteSession.setXaState(remoteSession.getXaState() | 0x4);
/*       */             }
/*       */           } 
/*       */         } finally {
/*       */           
/* 11021 */           if (Trace.isOn) {
/* 11022 */             Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_start(Hconn,Xid,int,int)");
/*       */           }
/*       */ 
/*       */           
/*       */           try {
/* 11027 */             if (remoteHconn != null) {
/* 11028 */               if (rMQAPI != null) {
/*       */                 try {
/* 11030 */                   remoteSession.releaseReceivedTSH((RfpTSH)rMQAPI);
/*       */                 }
/* 11032 */                 catch (JmqiException e) {
/* 11033 */                   if (Trace.isOn) {
/* 11034 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_start(Hconn,Xid,int,int)", (Throwable)e, 1);
/*       */                   }
/*       */                 } 
/*       */               }
/*       */ 
/*       */ 
/*       */               
/* 11041 */               remoteHconn.leaveCall(rc);
/*       */             }
/*       */           
/* 11044 */           } catch (JmqiException e) {
/* 11045 */             if (Trace.isOn) {
/* 11046 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_start(Hconn,Xid,int,int)", (Throwable)e, 2);
/*       */             }
/*       */           }
/*       */         
/*       */         }
/*       */       
/*       */       }
/*       */     
/* 11054 */     } catch (JmqiException e) {
/* 11055 */       if (Trace.isOn) {
/* 11056 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_start(Hconn,Xid,int,int)", (Throwable)e, 3);
/*       */       }
/* 11058 */       jmqiTls.lastException = e;
/* 11059 */       rc = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */     } 
/*       */     
/* 11062 */     if (Trace.isOn) {
/* 11063 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_start(Hconn,Xid,int,int)", 
/* 11064 */           Integer.valueOf(rc));
/*       */     }
/* 11066 */     return rc;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int xa_end(Hconn hconn, Xid xid, int rmid, int flags) {
/* 11084 */     if (Trace.isOn) {
/* 11085 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_end(Hconn,Xid,int,int)", new Object[] { hconn, xid, 
/* 11086 */             Integer.valueOf(rmid), Integer.valueOf(flags) });
/*       */     }
/*       */     
/* 11089 */     int rc = 0;
/* 11090 */     RemoteHconn remoteHconn = null;
/* 11091 */     RemoteSession remoteSession = null;
/* 11092 */     RfpMQAPI rMQAPI = null;
/*       */     
/* 11094 */     RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 11095 */     JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/* 11096 */     jmqiTls.lastException = null;
/*       */     try {
/* 11098 */       remoteHconn = getRemoteHconn(tls, hconn);
/*       */       
/* 11100 */       if (rc == 0 && xid == null) {
/* 11101 */         if (Trace.isOn) {
/* 11102 */           Trace.data(this, "setting rc=XAER_INVAL as xid is null", null);
/*       */         }
/* 11104 */         rc = -5;
/*       */       } 
/*       */       
/* 11107 */       if (rc == 0 && (flags & Integer.MIN_VALUE) != 0) {
/* 11108 */         if (Trace.isOn) {
/* 11109 */           Trace.data(this, "setting rc=XAER_INVAL as xid is null", null);
/*       */         }
/* 11111 */         rc = -2;
/*       */       } 
/* 11113 */       if (rc == 0 && (flags & 0xD9FFFFFF) != 0) {
/*       */         
/* 11115 */         if (Trace.isOn) {
/* 11116 */           Trace.data(this, "setting rc=XAER_INVAL as (flags & ~(TMSUCCESS | TMFAIL | TMSUSPEND)) != 0", null);
/*       */         }
/* 11118 */         rc = -5;
/*       */       } 
/* 11120 */       if (rc == 0) {
/*       */         
/* 11122 */         remoteHconn.enterCall(tls.isDispatchThread, false);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         try {
/* 11130 */           remoteSession = remoteHconn.getSession();
/* 11131 */           remoteSession.checkIfDisconnected();
/*       */           
/* 11133 */           boolean swap = remoteSession.isSwap();
/*       */           
/* 11135 */           if ((remoteSession.getXaState() & 0x2) == 0) {
/* 11136 */             if (Trace.isOn) {
/* 11137 */               Trace.data(this, "setting rc=XAER_RMFAIL as XAState is not Opened", null);
/*       */             }
/* 11139 */             rc = -7;
/*       */           } 
/*       */           
/* 11142 */           if (rc == 0 && remoteSession.getRmid() != rmid) {
/* 11143 */             if (Trace.isOn) {
/* 11144 */               Trace.data(this, "setting rc=XAER_RMFAIL as XAState is not Opened", null);
/*       */             }
/* 11146 */             rc = -5;
/*       */           } 
/*       */           
/* 11149 */           if (rc == 0) {
/*       */             
/* 11151 */             RfpMQAPI sMQAPI = remoteSession.allocateMQAPI(162);
/* 11152 */             sMQAPI.setRMID(rmid, swap);
/* 11153 */             sMQAPI.setFlags(flags, swap);
/*       */             
/* 11155 */             RfpXAID xaid = (RfpXAID)remoteSession.getRfp(13, (RfpStructure)sMQAPI, sMQAPI.hdrSize());
/* 11156 */             xaid.setXid(xid, swap);
/*       */             
/* 11158 */             sMQAPI.setControlFlags1(48);
/* 11159 */             sMQAPI.setTransLength(sMQAPI.hdrSize() + xaid.getRoundedLength());
/* 11160 */             sMQAPI.setCallLength(sMQAPI.hdrSize() + xaid.getRoundedLength());
/*       */             
/* 11162 */             remoteSession.sendTSH(tls, (RfpTSH)sMQAPI);
/*       */             
/* 11164 */             rMQAPI = remoteSession.receiveMQIFlow(tls);
/* 11165 */             if (rMQAPI.getSegmentType() != 178 || (rMQAPI.getControlFlags1() & 0x20) == 0) {
/* 11166 */               HashMap<String, Object> info = new HashMap<>();
/* 11167 */               info.put("SegmentType", Integer.valueOf(rMQAPI.getSegmentType()));
/* 11168 */               info.put("ControlFlags1", Integer.valueOf(rMQAPI.getControlFlags1()));
/* 11169 */               info.put("Description", "Unexpected flow received from server");
/* 11170 */               Trace.ffst(this, "xa_end(Hconn,Xid,int,int)", "01", info, null);
/* 11171 */               JmqiException traceRet3 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*       */ 
/*       */               
/* 11174 */               if (Trace.isOn) {
/* 11175 */                 Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_end(Hconn,Xid,int,int)", (Throwable)traceRet3);
/*       */               }
/*       */               
/* 11178 */               throw traceRet3;
/*       */             } 
/*       */ 
/*       */ 
/*       */             
/* 11183 */             swap = remoteSession.isSwap();
/*       */ 
/*       */             
/* 11186 */             rc = rMQAPI.getReturnCode(swap);
/*       */             
/* 11188 */             if (Trace.isOn) {
/* 11189 */               Trace.data(this, "setting rc as returned by the QM: ", Integer.valueOf(rc));
/*       */             }
/*       */ 
/*       */             
/* 11193 */             if (rc == 0 || rc == 106 || rc == 100 || rc == -4) {
/* 11194 */               remoteSession.setXaState(remoteSession.getXaState() & 0xFFFFFFFB);
/*       */             }
/*       */           } 
/*       */         } finally {
/*       */           
/* 11199 */           if (Trace.isOn) {
/* 11200 */             Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_end(Hconn,Xid,int,int)");
/*       */           }
/*       */ 
/*       */           
/*       */           try {
/* 11205 */             if (remoteHconn != null) {
/* 11206 */               if (rMQAPI != null) {
/*       */                 try {
/* 11208 */                   remoteSession.releaseReceivedTSH((RfpTSH)rMQAPI);
/*       */                 }
/* 11210 */                 catch (JmqiException e) {
/* 11211 */                   if (Trace.isOn) {
/* 11212 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_end(Hconn,Xid,int,int)", (Throwable)e, 1);
/*       */                   }
/*       */                 } 
/*       */               }
/*       */ 
/*       */ 
/*       */               
/* 11219 */               remoteHconn.leaveCall(rc);
/*       */             }
/*       */           
/* 11222 */           } catch (JmqiException e) {
/* 11223 */             if (Trace.isOn) {
/* 11224 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_end(Hconn,Xid,int,int)", (Throwable)e, 2);
/*       */             }
/*       */           }
/*       */         
/*       */         }
/*       */       
/*       */       } 
/* 11231 */     } catch (JmqiException e) {
/* 11232 */       if (Trace.isOn) {
/* 11233 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_end(Hconn,Xid,int,int)", (Throwable)e, 3);
/*       */       }
/* 11235 */       jmqiTls.lastException = e;
/* 11236 */       rc = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */     } 
/*       */     
/* 11239 */     if (Trace.isOn) {
/* 11240 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_end(Hconn,Xid,int,int)", Integer.valueOf(rc));
/*       */     }
/* 11242 */     return rc;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int xa_prepare(Hconn hconn, Xid xid, int rmid, int flags) {
/* 11260 */     if (Trace.isOn) {
/* 11261 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_prepare(Hconn,Xid,int,int)", new Object[] { hconn, xid, 
/* 11262 */             Integer.valueOf(rmid), Integer.valueOf(flags) });
/*       */     }
/*       */     
/* 11265 */     int rc = 0;
/* 11266 */     RemoteHconn remoteHconn = null;
/* 11267 */     RemoteSession remoteSession = null;
/* 11268 */     RfpMQAPI rMQAPI = null;
/*       */     
/* 11270 */     RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 11271 */     JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/* 11272 */     jmqiTls.lastException = null;
/*       */     try {
/* 11274 */       remoteHconn = getRemoteHconn(tls, hconn);
/*       */       
/* 11276 */       if (rc == 0 && xid == null) {
/* 11277 */         if (Trace.isOn) {
/* 11278 */           Trace.data(this, "setting rc=XAER_INVAL as xid is null", null);
/*       */         }
/* 11280 */         rc = -5;
/*       */       } 
/*       */       
/* 11283 */       if (rc == 0 && (flags & Integer.MIN_VALUE) != 0) {
/* 11284 */         if (Trace.isOn) {
/* 11285 */           Trace.data(this, "setting rc=XAER_ASYNC", null);
/*       */         }
/* 11287 */         rc = -2;
/*       */       } 
/* 11289 */       if (rc == 0 && flags != 0) {
/* 11290 */         if (Trace.isOn) {
/* 11291 */           Trace.data(this, "setting rc=XAER_INVAL as flags != TMNOFLAGS", null);
/*       */         }
/* 11293 */         rc = -5;
/*       */       } 
/* 11295 */       if (rc == 0) {
/*       */         
/* 11297 */         remoteHconn.enterCall(tls.isDispatchThread, false);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         try {
/* 11305 */           remoteSession = remoteHconn.getSession();
/* 11306 */           remoteSession.checkIfDisconnected();
/* 11307 */           boolean swap = remoteSession.isSwap();
/*       */ 
/*       */           
/* 11310 */           if ((remoteSession.getXaState() & 0x2) == 0) {
/* 11311 */             if (Trace.isOn) {
/* 11312 */               Trace.data(this, "setting rc=XAER_RMFAIL as XAState is not Opened", null);
/*       */             }
/* 11314 */             rc = -7;
/*       */           } 
/*       */ 
/*       */           
/* 11318 */           if (rc == 0 && remoteSession.getRmid() != rmid) {
/* 11319 */             if (Trace.isOn) {
/* 11320 */               Trace.data(this, "setting rc=XAER_INVAL as remoteSession.getRmid() != rmid)", 
/* 11321 */                   Integer.valueOf(rmid));
/*       */             }
/* 11323 */             rc = -5;
/*       */           } 
/*       */ 
/*       */           
/* 11327 */           if (rc == 0)
/*       */           {
/* 11329 */             RfpMQAPI sMQAPI = remoteSession.allocateMQAPI(165);
/* 11330 */             sMQAPI.setRMID(rmid, swap);
/* 11331 */             sMQAPI.setFlags(flags, swap);
/*       */             
/* 11333 */             RfpXAID xaid = (RfpXAID)remoteSession.getRfp(13, (RfpStructure)sMQAPI, sMQAPI.hdrSize());
/* 11334 */             xaid.setXid(xid, swap);
/*       */             
/* 11336 */             sMQAPI.setControlFlags1(48);
/* 11337 */             sMQAPI.setTransLength(sMQAPI.hdrSize() + xaid.getRoundedLength());
/* 11338 */             sMQAPI.setCallLength(sMQAPI.hdrSize() + xaid.getRoundedLength());
/*       */             
/* 11340 */             remoteSession.sendTSH(tls, (RfpTSH)sMQAPI);
/*       */             
/* 11342 */             rMQAPI = remoteSession.receiveMQIFlow(tls);
/* 11343 */             if (rMQAPI.getSegmentType() != 181 || (rMQAPI.getControlFlags1() & 0x20) == 0) {
/* 11344 */               HashMap<String, Object> info = new HashMap<>();
/* 11345 */               info.put("SegmentType", Integer.valueOf(rMQAPI.getSegmentType()));
/* 11346 */               info.put("ControlFlags1", Integer.valueOf(rMQAPI.getControlFlags1()));
/* 11347 */               info.put("Description", "Unexpected flow received from server");
/* 11348 */               Trace.ffst(this, "xa_prepare(Hconn,Xid,int,int)", "01", info, null);
/* 11349 */               JmqiException traceRet3 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*       */ 
/*       */               
/* 11352 */               if (Trace.isOn) {
/* 11353 */                 Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_prepare(Hconn,Xid,int,int)", (Throwable)traceRet3);
/*       */               }
/*       */               
/* 11356 */               throw traceRet3;
/*       */             } 
/*       */ 
/*       */ 
/*       */             
/* 11361 */             swap = remoteSession.isSwap();
/*       */ 
/*       */             
/* 11364 */             rc = rMQAPI.getReturnCode(swap);
/*       */             
/* 11366 */             if (Trace.isOn) {
/* 11367 */               Trace.data(this, "setting rc as returned by the QM: ", Integer.valueOf(rc));
/*       */             }
/*       */ 
/*       */             
/* 11371 */             if (rc == 0 || rc == 3) {
/* 11372 */               hconn.setXAPrepared(true);
/*       */             }
/*       */           }
/*       */         
/*       */         } finally {
/*       */           
/* 11378 */           if (Trace.isOn) {
/* 11379 */             Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_prepare(Hconn,Xid,int,int)");
/*       */           }
/*       */ 
/*       */ 
/*       */           
/*       */           try {
/* 11385 */             if (remoteHconn != null) {
/* 11386 */               if (rMQAPI != null) {
/*       */                 try {
/* 11388 */                   remoteSession.releaseReceivedTSH((RfpTSH)rMQAPI);
/*       */                 }
/* 11390 */                 catch (JmqiException e) {
/* 11391 */                   if (Trace.isOn) {
/* 11392 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_prepare(Hconn,Xid,int,int)", (Throwable)e, 1);
/*       */                   }
/*       */                 } 
/*       */               }
/*       */ 
/*       */ 
/*       */               
/* 11399 */               remoteHconn.leaveCall(rc);
/*       */             }
/*       */           
/* 11402 */           } catch (JmqiException e) {
/* 11403 */             if (Trace.isOn) {
/* 11404 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_prepare(Hconn,Xid,int,int)", (Throwable)e, 2);
/*       */             }
/*       */           }
/*       */         
/*       */         }
/*       */       
/*       */       } 
/* 11411 */     } catch (JmqiException e) {
/* 11412 */       if (Trace.isOn) {
/* 11413 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_prepare(Hconn,Xid,int,int)", (Throwable)e, 3);
/*       */       }
/* 11415 */       jmqiTls.lastException = e;
/* 11416 */       rc = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */     } 
/*       */     
/* 11419 */     if (Trace.isOn) {
/* 11420 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_prepare(Hconn,Xid,int,int)", 
/* 11421 */           Integer.valueOf(rc));
/*       */     }
/* 11423 */     return rc;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int xa_commit(Hconn hconn, Xid xid, int rmid, int flags) {
/* 11441 */     if (Trace.isOn) {
/* 11442 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_commit(Hconn,Xid,int,int)", new Object[] { hconn, xid, 
/* 11443 */             Integer.valueOf(rmid), Integer.valueOf(flags) });
/*       */     }
/*       */     
/* 11446 */     int rc = 0;
/* 11447 */     RemoteHconn remoteHconn = null;
/* 11448 */     RemoteSession remoteSession = null;
/* 11449 */     RfpMQAPI rMQAPI = null;
/*       */     
/* 11451 */     RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 11452 */     JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/* 11453 */     jmqiTls.lastException = null;
/*       */     try {
/* 11455 */       remoteHconn = getRemoteHconn(tls, hconn);
/*       */       
/* 11457 */       if (rc == 0 && xid == null) {
/* 11458 */         if (Trace.isOn) {
/* 11459 */           Trace.data(this, "setting rc=XAER_INVAL as xid is null", null);
/*       */         }
/* 11461 */         rc = -5;
/*       */       } 
/*       */       
/* 11464 */       if (rc == 0 && (flags & Integer.MIN_VALUE) != 0) {
/* 11465 */         if (Trace.isOn) {
/* 11466 */           Trace.data(this, "setting rc=XAER_ASYNC", null);
/*       */         }
/* 11468 */         rc = -2;
/*       */       } 
/* 11470 */       if (rc == 0 && (flags & 0xAFFFFFFF) != 0) {
/* 11471 */         if (Trace.isOn) {
/* 11472 */           Trace.data(this, "setting rc=XAER_INVAL as (flags & ~(TMNOWAIT | TMONEPHASE)) != 0)", null);
/*       */         }
/* 11474 */         rc = -5;
/*       */       } 
/* 11476 */       if (rc == 0) {
/*       */         
/* 11478 */         remoteHconn.enterCall(tls.isDispatchThread, false);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         try {
/* 11486 */           remoteSession = remoteHconn.getSession();
/* 11487 */           remoteSession.checkIfDisconnected();
/* 11488 */           boolean swap = remoteSession.isSwap();
/*       */           
/* 11490 */           if ((remoteSession.getXaState() & 0x2) == 0) {
/* 11491 */             if (Trace.isOn) {
/* 11492 */               Trace.data(this, "setting rc=XAER_RMFAIL as XAState is not Opened", null);
/*       */             }
/* 11494 */             rc = -7;
/*       */           } 
/*       */           
/* 11497 */           if (rc == 0 && remoteSession.getRmid() != rmid) {
/* 11498 */             if (Trace.isOn) {
/* 11499 */               Trace.data(this, "setting rc=XAER_INVAL as (remoteSession.getRmid() != rmid) ", 
/* 11500 */                   Integer.valueOf(rmid));
/*       */             }
/* 11502 */             rc = -5;
/*       */           } 
/*       */           
/* 11505 */           if (rc == 0)
/*       */           {
/* 11507 */             RfpMQAPI sMQAPI = remoteSession.allocateMQAPI(166);
/* 11508 */             sMQAPI.setRMID(rmid, swap);
/* 11509 */             sMQAPI.setFlags(flags, swap);
/*       */             
/* 11511 */             RfpXAID xaid = (RfpXAID)remoteSession.getRfp(13, (RfpStructure)sMQAPI, sMQAPI.hdrSize());
/* 11512 */             xaid.setXid(xid, swap);
/*       */             
/* 11514 */             sMQAPI.setControlFlags1(48);
/* 11515 */             sMQAPI.setTransLength(sMQAPI.hdrSize() + xaid.getRoundedLength());
/* 11516 */             sMQAPI.setCallLength(sMQAPI.hdrSize() + xaid.getRoundedLength());
/*       */             
/* 11518 */             remoteSession.sendTSH(tls, (RfpTSH)sMQAPI);
/*       */             
/* 11520 */             rMQAPI = remoteSession.receiveMQIFlow(tls);
/* 11521 */             if (rMQAPI.getSegmentType() != 182 || (rMQAPI.getControlFlags1() & 0x20) == 0) {
/* 11522 */               HashMap<String, Object> info = new HashMap<>();
/* 11523 */               info.put("SegmentType", Integer.valueOf(rMQAPI.getSegmentType()));
/* 11524 */               info.put("ControlFlags1", Integer.valueOf(rMQAPI.getControlFlags1()));
/* 11525 */               info.put("Description", "Unexpected flow received from server");
/* 11526 */               Trace.ffst(this, "xa_commit(Hconn,Xid,int,int)", "01", info, null);
/* 11527 */               JmqiException traceRet3 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*       */ 
/*       */               
/* 11530 */               if (Trace.isOn) {
/* 11531 */                 Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_commit(Hconn,Xid,int,int)", (Throwable)traceRet3);
/*       */               }
/*       */               
/* 11534 */               throw traceRet3;
/*       */             } 
/*       */ 
/*       */ 
/*       */             
/* 11539 */             swap = remoteSession.isSwap();
/*       */ 
/*       */             
/* 11542 */             rc = rMQAPI.getReturnCode(swap);
/*       */             
/* 11544 */             if (Trace.isOn) {
/* 11545 */               Trace.data(this, "setting rc as returned by the QM: ", Integer.valueOf(rc));
/*       */             }
/*       */           }
/*       */         
/*       */         } finally {
/*       */           
/* 11551 */           if (Trace.isOn) {
/* 11552 */             Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_commit(Hconn,Xid,int,int)");
/*       */           }
/*       */ 
/*       */ 
/*       */           
/*       */           try {
/* 11558 */             if (remoteHconn != null) {
/* 11559 */               if (rMQAPI != null) {
/*       */                 try {
/* 11561 */                   remoteSession.releaseReceivedTSH((RfpTSH)rMQAPI);
/*       */                 }
/* 11563 */                 catch (JmqiException e) {
/* 11564 */                   if (Trace.isOn) {
/* 11565 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_commit(Hconn,Xid,int,int)", (Throwable)e, 1);
/*       */                   }
/*       */                 } 
/*       */               }
/*       */ 
/*       */ 
/*       */               
/* 11572 */               remoteHconn.leaveCall(rc);
/*       */             }
/*       */           
/* 11575 */           } catch (JmqiException e) {
/* 11576 */             if (Trace.isOn) {
/* 11577 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_commit(Hconn,Xid,int,int)", (Throwable)e, 2);
/*       */             }
/*       */           }
/*       */         
/*       */         }
/*       */       
/*       */       } 
/* 11584 */     } catch (JmqiException e) {
/* 11585 */       if (Trace.isOn) {
/* 11586 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_commit(Hconn,Xid,int,int)", (Throwable)e, 3);
/*       */       }
/* 11588 */       jmqiTls.lastException = e;
/* 11589 */       rc = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */     } finally {
/*       */       
/* 11592 */       hconn.setXAPrepared(false);
/*       */     } 
/*       */     
/* 11595 */     if (Trace.isOn) {
/* 11596 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_commit(Hconn,Xid,int,int)", 
/* 11597 */           Integer.valueOf(rc));
/*       */     }
/* 11599 */     return rc;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int xa_rollback(Hconn hconn, Xid xid, int rmid, int flags) {
/* 11617 */     if (Trace.isOn) {
/* 11618 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_rollback(Hconn,Xid,int,int)", new Object[] { hconn, xid, 
/* 11619 */             Integer.valueOf(rmid), Integer.valueOf(flags) });
/*       */     }
/*       */     
/* 11622 */     int rc = 0;
/* 11623 */     RemoteHconn remoteHconn = null;
/* 11624 */     RemoteSession remoteSession = null;
/* 11625 */     RfpMQAPI rMQAPI = null;
/*       */     
/* 11627 */     RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 11628 */     JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/* 11629 */     jmqiTls.lastException = null;
/*       */     try {
/* 11631 */       remoteHconn = getRemoteHconn(tls, hconn);
/*       */       
/* 11633 */       if (rc == 0 && xid == null) {
/* 11634 */         if (Trace.isOn) {
/* 11635 */           Trace.data(this, "setting rc=XAER_INVAL as xid is null", null);
/*       */         }
/* 11637 */         rc = -5;
/*       */       } 
/*       */       
/* 11640 */       if (rc == 0 && (flags & Integer.MIN_VALUE) != 0) {
/* 11641 */         if (Trace.isOn) {
/* 11642 */           Trace.data(this, "setting rc=XAER_ASYNC", null);
/*       */         }
/* 11644 */         rc = -2;
/*       */       } 
/* 11646 */       if (rc == 0 && flags != 0) {
/* 11647 */         if (Trace.isOn) {
/* 11648 */           Trace.data(this, "setting rc=XAER_INVAL as flags != TMNOFLAGS", null);
/*       */         }
/* 11650 */         rc = -5;
/*       */       } 
/* 11652 */       if (rc == 0) {
/*       */         
/* 11654 */         remoteHconn.enterCall(tls.isDispatchThread, false);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         try {
/* 11662 */           remoteSession = remoteHconn.getSession();
/* 11663 */           remoteSession.checkIfDisconnected();
/* 11664 */           boolean swap = remoteSession.isSwap();
/*       */ 
/*       */           
/* 11667 */           if ((remoteSession.getXaState() & 0x2) == 0) {
/* 11668 */             if (Trace.isOn) {
/* 11669 */               Trace.data(this, "setting rc=XAER_RMFAIL as XAState is not Opened", null);
/*       */             }
/* 11671 */             rc = -7;
/*       */           } 
/*       */ 
/*       */           
/* 11675 */           if (rc == 0 && remoteSession.getRmid() != rmid) {
/* 11676 */             if (Trace.isOn) {
/* 11677 */               Trace.data(this, "setting rc=XAER_INVAL as remoteSession.getRmid() != rmid ", 
/* 11678 */                   Integer.valueOf(rmid));
/*       */             }
/* 11680 */             rc = -5;
/*       */           } 
/*       */ 
/*       */           
/* 11684 */           if (rc == 0)
/*       */           {
/* 11686 */             RfpMQAPI sMQAPI = remoteSession.allocateMQAPI(167);
/* 11687 */             sMQAPI.setRMID(rmid, swap);
/* 11688 */             sMQAPI.setFlags(flags, swap);
/*       */             
/* 11690 */             RfpXAID xaid = (RfpXAID)remoteSession.getRfp(13, (RfpStructure)sMQAPI, sMQAPI.hdrSize());
/* 11691 */             xaid.setXid(xid, swap);
/*       */             
/* 11693 */             sMQAPI.setControlFlags1(48);
/* 11694 */             sMQAPI.setTransLength(sMQAPI.hdrSize() + xaid.getRoundedLength());
/* 11695 */             sMQAPI.setCallLength(sMQAPI.hdrSize() + xaid.getRoundedLength());
/*       */             
/* 11697 */             remoteSession.sendTSH(tls, (RfpTSH)sMQAPI);
/*       */             
/* 11699 */             rMQAPI = remoteSession.receiveMQIFlow(tls);
/* 11700 */             if (rMQAPI.getSegmentType() != 183 || (rMQAPI.getControlFlags1() & 0x20) == 0) {
/* 11701 */               HashMap<String, Object> info = new HashMap<>();
/* 11702 */               info.put("SegmentType", Integer.valueOf(rMQAPI.getSegmentType()));
/* 11703 */               info.put("ControlFlags1", Integer.valueOf(rMQAPI.getControlFlags1()));
/* 11704 */               info.put("Description", "Unexpected flow received from server");
/* 11705 */               Trace.ffst(this, "xa_rollback(Hconn,Xid,int,int)", "01", info, null);
/* 11706 */               JmqiException traceRet3 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*       */ 
/*       */               
/* 11709 */               if (Trace.isOn) {
/* 11710 */                 Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_rollback(Hconn,Xid,int,int)", (Throwable)traceRet3);
/*       */               }
/*       */               
/* 11713 */               throw traceRet3;
/*       */             } 
/*       */ 
/*       */ 
/*       */             
/* 11718 */             swap = remoteSession.isSwap();
/*       */ 
/*       */             
/* 11721 */             rc = rMQAPI.getReturnCode(swap);
/*       */             
/* 11723 */             if (Trace.isOn) {
/* 11724 */               Trace.data(this, "setting rc as returned by the QM: ", Integer.valueOf(rc));
/*       */             }
/*       */           }
/*       */         
/*       */         }
/*       */         finally {
/*       */           
/* 11731 */           if (Trace.isOn) {
/* 11732 */             Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_rollback(Hconn,Xid,int,int)");
/*       */           }
/*       */ 
/*       */ 
/*       */           
/*       */           try {
/* 11738 */             if (remoteHconn != null) {
/* 11739 */               if (rMQAPI != null) {
/*       */                 try {
/* 11741 */                   remoteSession.releaseReceivedTSH((RfpTSH)rMQAPI);
/*       */                 }
/* 11743 */                 catch (JmqiException e) {
/* 11744 */                   if (Trace.isOn) {
/* 11745 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_rollback(Hconn,Xid,int,int)", (Throwable)e, 1);
/*       */                   }
/*       */                 } 
/*       */               }
/*       */ 
/*       */ 
/*       */               
/* 11752 */               remoteHconn.leaveCall(rc);
/*       */             }
/*       */           
/* 11755 */           } catch (JmqiException e) {
/* 11756 */             if (Trace.isOn) {
/* 11757 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_rollback(Hconn,Xid,int,int)", (Throwable)e, 2);
/*       */             }
/*       */           }
/*       */         
/*       */         }
/*       */       
/*       */       } 
/* 11764 */     } catch (JmqiException e) {
/* 11765 */       if (Trace.isOn) {
/* 11766 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_rollback(Hconn,Xid,int,int)", (Throwable)e, 3);
/*       */       }
/* 11768 */       jmqiTls.lastException = e;
/* 11769 */       rc = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */     } finally {
/*       */       
/* 11772 */       hconn.setXAPrepared(false);
/*       */     } 
/*       */     
/* 11775 */     if (Trace.isOn) {
/* 11776 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_rollback(Hconn,Xid,int,int)", 
/* 11777 */           Integer.valueOf(rc));
/*       */     }
/* 11779 */     return rc;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int xa_forget(Hconn hconn, Xid xid, int rmid, int flags) {
/* 11797 */     if (Trace.isOn) {
/* 11798 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_forget(Hconn,Xid,int,int)", new Object[] { hconn, xid, 
/* 11799 */             Integer.valueOf(rmid), Integer.valueOf(flags) });
/*       */     }
/*       */     
/* 11802 */     int rc = 0;
/* 11803 */     RemoteHconn remoteHconn = null;
/* 11804 */     RemoteSession remoteSession = null;
/* 11805 */     RfpMQAPI rMQAPI = null;
/*       */     
/* 11807 */     RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 11808 */     JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/* 11809 */     jmqiTls.lastException = null;
/*       */     try {
/* 11811 */       remoteHconn = getRemoteHconn(tls, hconn);
/*       */       
/* 11813 */       if (rc == 0 && xid == null) {
/* 11814 */         if (Trace.isOn) {
/* 11815 */           Trace.data(this, "setting rc=XAER_INVAL as xid is null", null);
/*       */         }
/* 11817 */         rc = -5;
/*       */       } 
/*       */       
/* 11820 */       if (rc == 0 && (flags & Integer.MIN_VALUE) != 0) {
/* 11821 */         if (Trace.isOn) {
/* 11822 */           Trace.data(this, "setting rc=XAER_ASYNC", null);
/*       */         }
/* 11824 */         rc = -2;
/*       */       } 
/* 11826 */       if (rc == 0 && flags != 0) {
/* 11827 */         if (Trace.isOn) {
/* 11828 */           Trace.data(this, "setting rc=XAER_INVAL as flags != TMNOFLAGS", null);
/*       */         }
/* 11830 */         rc = -5;
/*       */       } 
/* 11832 */       if (rc == 0) {
/*       */         
/* 11834 */         remoteHconn.enterCall(tls.isDispatchThread, false);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         try {
/* 11842 */           remoteSession = remoteHconn.getSession();
/* 11843 */           remoteSession.checkIfDisconnected();
/* 11844 */           boolean swap = remoteSession.isSwap();
/*       */           
/* 11846 */           if ((remoteSession.getXaState() & 0x2) == 0) {
/* 11847 */             if (Trace.isOn) {
/* 11848 */               Trace.data(this, "setting rc=XAER_RMFAIL as XAState is not Opened", null);
/*       */             }
/* 11850 */             rc = -7;
/*       */           } 
/*       */           
/* 11853 */           if (rc == 0 && remoteSession.getRmid() != rmid) {
/* 11854 */             if (Trace.isOn) {
/* 11855 */               Trace.data(this, "setting rc=XAER_INVAL as remoteSession.getRmid() != rmid ", 
/* 11856 */                   Integer.valueOf(rmid));
/*       */             }
/* 11858 */             rc = -5;
/*       */           } 
/*       */           
/* 11861 */           if (rc == 0)
/*       */           {
/* 11863 */             RfpMQAPI sMQAPI = remoteSession.allocateMQAPI(168);
/* 11864 */             sMQAPI.setRMID(rmid, swap);
/* 11865 */             sMQAPI.setFlags(flags, swap);
/*       */             
/* 11867 */             RfpXAID xaid = (RfpXAID)remoteSession.getRfp(13, (RfpStructure)sMQAPI, sMQAPI.hdrSize());
/* 11868 */             xaid.setXid(xid, swap);
/*       */             
/* 11870 */             sMQAPI.setControlFlags1(48);
/* 11871 */             sMQAPI.setTransLength(sMQAPI.hdrSize() + xaid.getRoundedLength());
/* 11872 */             sMQAPI.setCallLength(sMQAPI.hdrSize() + xaid.getRoundedLength());
/*       */             
/* 11874 */             remoteSession.sendTSH(tls, (RfpTSH)sMQAPI);
/*       */             
/* 11876 */             rMQAPI = remoteSession.receiveMQIFlow(tls);
/* 11877 */             if (rMQAPI.getSegmentType() != 184 || (rMQAPI.getControlFlags1() & 0x20) == 0) {
/* 11878 */               HashMap<String, Object> info = new HashMap<>();
/* 11879 */               info.put("SegmentType", Integer.valueOf(rMQAPI.getSegmentType()));
/* 11880 */               info.put("ControlFlags1", Integer.valueOf(rMQAPI.getControlFlags1()));
/* 11881 */               info.put("Description", "Unexpected flow received from server");
/* 11882 */               Trace.ffst(this, "xa_forget(Hconn,Xid,int,int)", "01", info, null);
/* 11883 */               JmqiException traceRet3 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*       */ 
/*       */               
/* 11886 */               if (Trace.isOn) {
/* 11887 */                 Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_forget(Hconn,Xid,int,int)", (Throwable)traceRet3);
/*       */               }
/*       */               
/* 11890 */               throw traceRet3;
/*       */             } 
/*       */ 
/*       */ 
/*       */             
/* 11895 */             swap = remoteSession.isSwap();
/*       */ 
/*       */             
/* 11898 */             rc = rMQAPI.getReturnCode(swap);
/*       */             
/* 11900 */             if (Trace.isOn) {
/* 11901 */               Trace.data(this, "setting rc as returned by the QM: ", Integer.valueOf(rc));
/*       */             }
/*       */           }
/*       */         
/*       */         } finally {
/*       */           
/* 11907 */           if (Trace.isOn) {
/* 11908 */             Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_forget(Hconn,Xid,int,int)");
/*       */           }
/*       */ 
/*       */ 
/*       */           
/*       */           try {
/* 11914 */             if (remoteHconn != null) {
/* 11915 */               if (rMQAPI != null) {
/*       */                 try {
/* 11917 */                   remoteSession.releaseReceivedTSH((RfpTSH)rMQAPI);
/*       */                 }
/* 11919 */                 catch (JmqiException e) {
/* 11920 */                   if (Trace.isOn) {
/* 11921 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_forget(Hconn,Xid,int,int)", (Throwable)e, 1);
/*       */                   }
/*       */                 } 
/*       */               }
/*       */ 
/*       */ 
/*       */               
/* 11928 */               remoteHconn.leaveCall(rc);
/*       */             }
/*       */           
/* 11931 */           } catch (JmqiException e) {
/* 11932 */             if (Trace.isOn) {
/* 11933 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_forget(Hconn,Xid,int,int)", (Throwable)e, 2);
/*       */             }
/*       */           }
/*       */         
/*       */         }
/*       */       
/*       */       } 
/* 11940 */     } catch (JmqiException e) {
/* 11941 */       if (Trace.isOn) {
/* 11942 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_forget(Hconn,Xid,int,int)", (Throwable)e, 3);
/*       */       }
/* 11944 */       jmqiTls.lastException = e;
/* 11945 */       rc = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */     } 
/*       */     
/* 11948 */     if (Trace.isOn) {
/* 11949 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_forget(Hconn,Xid,int,int)", 
/* 11950 */           Integer.valueOf(rc));
/*       */     }
/* 11952 */     return rc;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int xa_recover(Hconn hconn, Xid[] xids, int rmid, int flags) {
/* 11970 */     if (Trace.isOn) {
/* 11971 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_recover(Hconn,Xid [ ],int,int)", new Object[] { hconn, xids, 
/* 11972 */             Integer.valueOf(rmid), Integer.valueOf(flags) });
/*       */     }
/*       */     
/* 11975 */     int rc = 0;
/* 11976 */     RemoteHconn remoteHconn = null;
/* 11977 */     RemoteSession remoteSession = null;
/* 11978 */     RfpMQAPI rMQAPI = null;
/*       */     
/* 11980 */     RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 11981 */     JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/* 11982 */     jmqiTls.lastException = null;
/*       */     try {
/* 11984 */       remoteHconn = getRemoteHconn(tls, hconn);
/*       */       
/* 11986 */       if ((rc == 0 && xids == null) || xids.length == 0) {
/* 11987 */         if (Trace.isOn) {
/* 11988 */           Trace.data(this, "setting rc=XAER_INVAL as xid is null", null);
/*       */         }
/* 11990 */         rc = -5;
/*       */       } 
/*       */       
/* 11993 */       if (rc == 0 && (flags & Integer.MIN_VALUE) != 0) {
/* 11994 */         if (Trace.isOn) {
/* 11995 */           Trace.data(this, "setting rc=XAER_ASYNC", null);
/*       */         }
/* 11997 */         rc = -2;
/*       */       } 
/* 11999 */       if (rc == 0 && (flags & 0xFE7FFFFF) != 0) {
/* 12000 */         if (Trace.isOn) {
/* 12001 */           Trace.data(this, "setting rc=XAER_INVAL as (flags & ~(TMSTARTRSCAN | TMENDRSCAN)) != 0)", null);
/*       */         }
/* 12003 */         rc = -5;
/*       */       } 
/* 12005 */       if (rc == 0) {
/*       */         
/* 12007 */         remoteHconn.enterCall(tls.isDispatchThread, false);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         try {
/* 12015 */           remoteSession = remoteHconn.getSession();
/* 12016 */           remoteSession.checkIfDisconnected();
/* 12017 */           boolean swap = remoteSession.isSwap();
/*       */ 
/*       */           
/* 12020 */           if ((remoteSession.getXaState() & 0x2) == 0) {
/* 12021 */             if (Trace.isOn) {
/* 12022 */               Trace.data(this, "setting rc=XAER_RMFAIL as XAState is not Opened", null);
/*       */             }
/* 12024 */             rc = -7;
/*       */           } 
/*       */ 
/*       */           
/* 12028 */           if (rc == 0 && remoteSession.getRmid() != rmid) {
/* 12029 */             if (Trace.isOn) {
/* 12030 */               Trace.data(this, "setting rc=XAER_INVAL as remoteSession.getRmid() != rmid ", 
/* 12031 */                   Integer.valueOf(rmid));
/*       */             }
/* 12033 */             rc = -5;
/*       */           } 
/*       */ 
/*       */           
/* 12037 */           if (rc == 0) {
/*       */             
/* 12039 */             RfpMQAPI sMQAPI = remoteSession.allocateMQAPI(169);
/* 12040 */             sMQAPI.setRMID(rmid, swap);
/* 12041 */             sMQAPI.setFlags(flags, swap);
/*       */ 
/*       */ 
/*       */             
/* 12045 */             int headerLength = sMQAPI.hdrSize();
/* 12046 */             RfpXAIDS xaids = (RfpXAIDS)remoteSession.getRfp(14, (RfpStructure)sMQAPI, headerLength);
/* 12047 */             xaids.setCount(xids.length, swap);
/* 12048 */             headerLength += 4;
/*       */             
/* 12050 */             sMQAPI.setControlFlags1(48);
/* 12051 */             sMQAPI.setTransLength(headerLength);
/* 12052 */             sMQAPI.setCallLength(headerLength);
/*       */             
/* 12054 */             remoteSession.sendTSH(tls, (RfpTSH)sMQAPI);
/*       */             
/* 12056 */             rMQAPI = remoteSession.receiveMQIFlow(tls);
/* 12057 */             if (rMQAPI.getSegmentType() != 185 || (rMQAPI.getControlFlags1() & 0x20) == 0) {
/* 12058 */               HashMap<String, Object> info = new HashMap<>();
/* 12059 */               info.put("SegmentType", Integer.valueOf(rMQAPI.getSegmentType()));
/* 12060 */               info.put("ControlFlags1", Integer.valueOf(rMQAPI.getControlFlags1()));
/* 12061 */               info.put("Description", "Unexpected flow received from server");
/* 12062 */               Trace.ffst(this, "xa_recover(Hconn,Xid [ ],int,int)", "01", info, null);
/* 12063 */               JmqiException traceRet3 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*       */ 
/*       */               
/* 12066 */               if (Trace.isOn) {
/* 12067 */                 Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_recover(Hconn,Xid [ ],int,int)", (Throwable)traceRet3);
/*       */               }
/*       */               
/* 12070 */               throw traceRet3;
/*       */             } 
/*       */ 
/*       */ 
/*       */             
/* 12075 */             swap = remoteSession.isSwap();
/*       */ 
/*       */             
/* 12078 */             rc = rMQAPI.getReturnCode(swap);
/*       */             
/* 12080 */             if (Trace.isOn) {
/* 12081 */               Trace.data(this, "setting rc as returned by the QM: ", Integer.valueOf(rc));
/*       */             }
/*       */ 
/*       */ 
/*       */             
/* 12086 */             int readPosition = rMQAPI.getRfpOffset() + rMQAPI.hdrSize();
/*       */             
/* 12088 */             if (rc == 0) {
/*       */ 
/*       */ 
/*       */               
/* 12092 */               RfpXAIDS returnedXids = (RfpXAIDS)remoteSession.getRfp(14, (RfpStructure)rMQAPI, readPosition);
/*       */               
/* 12094 */               rc = returnedXids.getCount(swap);
/* 12095 */               if (Trace.isOn) {
/* 12096 */                 Trace.data(this, "setting rc as the count of the returned xids : ", 
/* 12097 */                     Integer.valueOf(rc));
/*       */               }
/*       */             } 
/*       */             
/* 12101 */             readPosition += 4;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */             
/* 12108 */             int i = 0;
/*       */             
/* 12110 */             if (rc > 0) {
/* 12111 */               if (rc > xids.length) {
/* 12112 */                 if (Trace.isOn) {
/* 12113 */                   Trace.data(this, "setting rc as size of the xids ", Integer.valueOf(rc));
/*       */                 }
/* 12115 */                 rc = xids.length;
/*       */               } 
/* 12117 */               ByteBuffer xidData = ByteBuffer.allocate(xids.length * RfpXAID.getMaxLength());
/*       */               
/* 12119 */               remoteSession.receiveSpannedMQIData(tls, rMQAPI, readPosition - rMQAPI.getRfpOffset(), xidData, xidData
/* 12120 */                   .capacity());
/*       */ 
/*       */               
/* 12123 */               readPosition = 0;
/*       */               
/* 12125 */               for (; i < rc; i++) {
/* 12126 */                 RfpXAID returnedXid = (RfpXAID)remoteSession.getRfp(13, null, readPosition);
/*       */ 
/*       */                 
/* 12129 */                 returnedXid.setRfpBuffer(xidData.array());
/*       */                 
/* 12131 */                 xids[i] = returnedXid.getXid(swap);
/*       */                 
/* 12133 */                 readPosition += returnedXid.getRoundedLength();
/*       */               } 
/*       */             } 
/*       */ 
/*       */             
/* 12138 */             for (; i < xids.length; i++) {
/* 12139 */               xids[i] = null;
/*       */             }
/*       */           } 
/*       */         } finally {
/*       */           
/* 12144 */           if (Trace.isOn) {
/* 12145 */             Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_recover(Hconn,Xid [ ],int,int)");
/*       */           }
/*       */ 
/*       */ 
/*       */           
/*       */           try {
/* 12151 */             if (remoteHconn != null) {
/* 12152 */               if (rMQAPI != null) {
/*       */                 try {
/* 12154 */                   remoteSession.releaseReceivedTSH((RfpTSH)rMQAPI);
/*       */                 }
/* 12156 */                 catch (JmqiException e) {
/* 12157 */                   if (Trace.isOn) {
/* 12158 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_recover(Hconn,Xid [ ],int,int)", (Throwable)e, 1);
/*       */                   }
/*       */                 } 
/*       */               }
/*       */ 
/*       */ 
/*       */               
/* 12165 */               remoteHconn.leaveCall(0);
/*       */             }
/*       */           
/* 12168 */           } catch (JmqiException e) {
/* 12169 */             if (Trace.isOn) {
/* 12170 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_recover(Hconn,Xid [ ],int,int)", (Throwable)e, 2);
/*       */             }
/*       */           }
/*       */         
/*       */         }
/*       */       
/*       */       } 
/* 12177 */     } catch (JmqiException e) {
/* 12178 */       if (Trace.isOn) {
/* 12179 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_recover(Hconn,Xid [ ],int,int)", (Throwable)e, 3);
/*       */       }
/*       */       
/* 12182 */       jmqiTls.lastException = e;
/* 12183 */       rc = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */     } 
/*       */     
/* 12186 */     if (Trace.isOn) {
/* 12187 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_recover(Hconn,Xid [ ],int,int)", 
/* 12188 */           Integer.valueOf(rc));
/*       */     }
/* 12190 */     return rc;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int xa_complete(Hconn hconn, Pint pHandle, Pint pRetval, int rmid, int flags) {
/* 12210 */     if (Trace.isOn) {
/* 12211 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_complete(Hconn,Pint,Pint,int,int)", new Object[] { hconn, pHandle, pRetval, 
/* 12212 */             Integer.valueOf(rmid), Integer.valueOf(flags) });
/*       */     }
/*       */     
/* 12215 */     int rc = 0;
/* 12216 */     RemoteHconn remoteHconn = null;
/* 12217 */     RemoteSession remoteSession = null;
/* 12218 */     RfpMQAPI rMQAPI = null;
/*       */     
/* 12220 */     RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 12221 */     JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/* 12222 */     jmqiTls.lastException = null;
/*       */     try {
/* 12224 */       remoteHconn = getRemoteHconn(tls, hconn);
/*       */       
/* 12226 */       if ((rc == 0 && pHandle == null) || pRetval == null) {
/* 12227 */         if (Trace.isOn) {
/* 12228 */           Trace.data(this, "setting rc=XAER_INVAL as (pHandle == null)) || (pRetval == null)", null);
/*       */         }
/* 12230 */         rc = -5;
/*       */       } 
/*       */       
/* 12233 */       if (rc == 0 && (flags & Integer.MIN_VALUE) != 0) {
/* 12234 */         if (Trace.isOn) {
/* 12235 */           Trace.data(this, "setting rc=XAER_INVAL as (pHandle == null)) || (pRetval == null)", null);
/*       */         }
/* 12237 */         rc = -2;
/*       */       } 
/* 12239 */       if (rc == 0 && (flags & 0xEFBFFFFF) != 0) {
/* 12240 */         if (Trace.isOn) {
/* 12241 */           Trace.data(this, "setting rc=XAER_INVAL as (pHandle == null)) || (pRetval == null)", null);
/*       */         }
/* 12243 */         rc = -5;
/*       */       } 
/* 12245 */       if (rc == 0) {
/*       */         
/* 12247 */         remoteHconn.enterCall(tls.isDispatchThread, false);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*       */         try {
/* 12255 */           remoteSession = remoteHconn.getSession();
/* 12256 */           remoteSession.checkIfDisconnected();
/* 12257 */           boolean swap = remoteSession.isSwap();
/*       */           
/* 12259 */           if ((remoteSession.getXaState() & 0x2) == 0) {
/* 12260 */             if (Trace.isOn) {
/* 12261 */               Trace.data(this, "setting rc=XAER_RMFAIL as XAState is not Opened", null);
/*       */             }
/* 12263 */             rc = -7;
/*       */           } 
/*       */           
/* 12266 */           if (rc == 0 && remoteSession.getRmid() != rmid) {
/* 12267 */             if (Trace.isOn) {
/* 12268 */               Trace.data(this, "setting rc=XAER_INVAL as remoteSession.getRmid() != rmid", 
/* 12269 */                   Integer.valueOf(rmid));
/*       */             }
/* 12271 */             rc = -5;
/*       */           } 
/*       */           
/* 12274 */           if (rc == 0) {
/*       */             
/* 12276 */             RfpMQAPI sMQAPI = remoteSession.allocateMQAPI(167);
/* 12277 */             sMQAPI.setRMID(rmid, swap);
/* 12278 */             sMQAPI.setFlags(flags, swap);
/*       */ 
/*       */             
/* 12281 */             RfpXA_COMPLETE xaCompleteDetails = (RfpXA_COMPLETE)remoteSession.getRfp(11, (RfpStructure)sMQAPI, sMQAPI.hdrSize());
/* 12282 */             xaCompleteDetails.setHandle(pHandle.x, swap);
/* 12283 */             xaCompleteDetails.setRetval(pRetval.x, swap);
/*       */             
/* 12285 */             sMQAPI.setControlFlags1(48);
/* 12286 */             sMQAPI.setTransLength(sMQAPI.hdrSize() + 8);
/* 12287 */             sMQAPI.setCallLength(sMQAPI.hdrSize() + 8);
/*       */             
/* 12289 */             remoteSession.sendTSH(tls, (RfpTSH)sMQAPI);
/*       */             
/* 12291 */             rMQAPI = remoteSession.receiveMQIFlow(tls);
/* 12292 */             if (rMQAPI.getSegmentType() != 183 || (rMQAPI.getControlFlags1() & 0x20) == 0) {
/* 12293 */               HashMap<String, Object> info = new HashMap<>();
/* 12294 */               info.put("SegmentType", Integer.valueOf(rMQAPI.getSegmentType()));
/* 12295 */               info.put("ControlFlags1", Integer.valueOf(rMQAPI.getControlFlags1()));
/* 12296 */               info.put("Description", "Unexpected flow received from server");
/* 12297 */               Trace.ffst(this, "xa_complete(Hconn,Pint,Pint,int,int)", "01", info, null);
/* 12298 */               JmqiException traceRet3 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*       */ 
/*       */               
/* 12301 */               if (Trace.isOn) {
/* 12302 */                 Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_complete(Hconn,Pint,Pint,int,int)", (Throwable)traceRet3);
/*       */               }
/*       */               
/* 12305 */               throw traceRet3;
/*       */             } 
/*       */ 
/*       */ 
/*       */             
/* 12310 */             swap = remoteSession.isSwap();
/*       */ 
/*       */             
/* 12313 */             rc = rMQAPI.getReturnCode(swap);
/*       */             
/* 12315 */             if (Trace.isOn) {
/* 12316 */               Trace.data(this, "setting rc as returned by the QM: ", Integer.valueOf(rc));
/*       */             }
/*       */             
/* 12319 */             xaCompleteDetails = (RfpXA_COMPLETE)remoteSession.getRfp(11, (RfpStructure)rMQAPI, rMQAPI
/* 12320 */                 .hdrSize());
/*       */             
/* 12322 */             pHandle.x = xaCompleteDetails.getHandle(swap);
/* 12323 */             pRetval.x = xaCompleteDetails.getRetval(swap);
/*       */           } 
/*       */         } finally {
/*       */           
/* 12327 */           if (Trace.isOn) {
/* 12328 */             Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_complete(Hconn,Pint,Pint,int,int)");
/*       */           }
/*       */ 
/*       */ 
/*       */           
/*       */           try {
/* 12334 */             if (remoteHconn != null) {
/* 12335 */               if (rMQAPI != null) {
/*       */                 try {
/* 12337 */                   remoteSession.releaseReceivedTSH((RfpTSH)rMQAPI);
/*       */                 }
/* 12339 */                 catch (JmqiException e) {
/* 12340 */                   if (Trace.isOn) {
/* 12341 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_complete(Hconn,Pint,Pint,int,int)", (Throwable)e, 1);
/*       */                   }
/*       */                 } 
/*       */               }
/*       */ 
/*       */ 
/*       */               
/* 12348 */               remoteHconn.leaveCall(rc);
/*       */             }
/*       */           
/* 12351 */           } catch (JmqiException e) {
/* 12352 */             if (Trace.isOn) {
/* 12353 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_complete(Hconn,Pint,Pint,int,int)", (Throwable)e, 2);
/*       */             }
/*       */           }
/*       */         
/*       */         }
/*       */       
/*       */       } 
/* 12360 */     } catch (JmqiException e) {
/* 12361 */       if (Trace.isOn) {
/* 12362 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_complete(Hconn,Pint,Pint,int,int)", (Throwable)e, 3);
/*       */       }
/*       */       
/* 12365 */       jmqiTls.lastException = e;
/* 12366 */       rc = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */     } 
/*       */     
/* 12369 */     if (Trace.isOn) {
/* 12370 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_complete(Hconn,Pint,Pint,int,int)", 
/* 12371 */           Integer.valueOf(rc));
/*       */     }
/* 12373 */     return rc;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int xa_open_tm(Hconn hconn, String name, int rmid, int flags) {
/* 12385 */     if (Trace.isOn) {
/* 12386 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_open_tm(Hconn,String,int,int)", new Object[] { hconn, name, 
/* 12387 */             Integer.valueOf(rmid), Integer.valueOf(flags) });
/*       */     }
/*       */ 
/*       */     
/* 12391 */     if (Trace.isOn) {
/* 12392 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "xa_open_tm(Hconn,String,int,int)", 
/* 12393 */           Integer.valueOf(0));
/*       */     }
/* 12395 */     return 0;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public RemoteConnectionPool getConnectionFactory() {
/* 12405 */     if (Trace.isOn) {
/* 12406 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "getConnectionFactory()", "getter", this.connectionFactory);
/*       */     }
/*       */     
/* 12409 */     return this.connectionFactory;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int getJmqiCompId() {
/* 12418 */     if (Trace.isOn) {
/* 12419 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "getJmqiCompId()", "getter", 
/* 12420 */           Integer.valueOf(this.jmqiCompId));
/*       */     }
/* 12422 */     return this.jmqiCompId;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public RemoteTls getTls() {
/* 12431 */     RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 12432 */     if (Trace.isOn) {
/* 12433 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "getTls()", "getter", tls);
/*       */     }
/* 12435 */     if (Trace.isOn) {
/* 12436 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "getTls()", "getter", tls);
/*       */     }
/* 12438 */     return tls;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void authenticate(Hconn hconn, String userId, String password, Pint pCompCode, Pint pReason) {
/* 12459 */     if (Trace.isOn) {
/* 12460 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "authenticate(Hconn,String,String,final Pint,final Pint)", new Object[] { hconn, userId, (password == null) ? password : 
/*       */ 
/*       */             
/* 12463 */             Integer.valueOf(password.length()), pCompCode, pReason });
/*       */     }
/*       */     
/* 12466 */     pCompCode.x = 0;
/* 12467 */     pReason.x = 0;
/*       */     
/* 12469 */     if (Trace.isOn) {
/* 12470 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "authenticate(Hconn,String,String,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void executeRunnable(Hconn hconn, JmqiRunnable job) throws Exception {
/* 12483 */     if (Trace.isOn) {
/* 12484 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "executeRunnable(Hconn,JmqiRunnable)", new Object[] { hconn, job });
/*       */     }
/*       */ 
/*       */ 
/*       */     
/* 12489 */     job.run();
/*       */     
/* 12491 */     if (Trace.isOn) {
/* 12492 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "executeRunnable(Hconn,JmqiRunnable)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void getMetaData(JmqiMetaData metadata, Pint pCompCode, Pint pReason) {
/* 12509 */     if (Trace.isOn) {
/* 12510 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "getMetaData(JmqiMetaData,Pint,Pint)", new Object[] { metadata, pCompCode, pReason });
/*       */     }
/*       */     
/* 12513 */     int flags = 2;
/* 12514 */     flags |= 0x4;
/* 12515 */     flags |= 0x8;
/* 12516 */     flags |= 0x10;
/* 12517 */     flags |= 0x20;
/* 12518 */     flags |= 0x40;
/* 12519 */     flags |= 0x80;
/* 12520 */     flags |= 0x100;
/* 12521 */     metadata.setVersion(2);
/* 12522 */     metadata.setFlags(flags);
/* 12523 */     metadata.setInstallFlags(0);
/* 12524 */     pCompCode.x = 0;
/* 12525 */     pReason.x = 0;
/*       */     
/* 12527 */     if (Trace.isOn) {
/* 12528 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "getMetaData(JmqiMetaData,Pint,Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean isSharedHandlesSupported() {
/* 12540 */     if (Trace.isOn) {
/* 12541 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "isSharedHandlesSupported()", "getter", 
/* 12542 */           Boolean.valueOf(true));
/*       */     }
/* 12544 */     return true;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int getTlsComponentId() {
/* 12554 */     if (Trace.isOn) {
/* 12555 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "getTlsComponentId()", "getter", 
/* 12556 */           Integer.valueOf(this.jmqiCompId));
/*       */     }
/* 12558 */     return this.jmqiCompId;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void checkCmdLevel(Hconn hconn) {
/* 12569 */     if (Trace.isOn) {
/* 12570 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "checkCmdLevel(Hconn)", new Object[] { hconn });
/*       */     }
/*       */ 
/*       */ 
/*       */     
/* 12575 */     if (Trace.isOn) {
/* 12576 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "checkCmdLevel(Hconn)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static int getUniqueTraceIdentifier() {
/* 12588 */     int retval = 0;
/*       */ 
/*       */ 
/*       */     
/* 12592 */     retval = traceIdentifier.getAndUpdate(i -> (i < Integer.MAX_VALUE) ? (i + 1) : 1);
/*       */     
/* 12594 */     if (Trace.isOn) {
/* 12595 */       Trace.data("com.ibm.mq.jmqi.remote.api.RemoteFAP", "getUniqueTraceIdentifier()", "getter", 
/* 12596 */           Integer.valueOf(retval));
/*       */     }
/*       */     
/* 12599 */     return retval;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void honourRRS(Hconn hconn, Pint pCompCode, Pint pReason) {
/* 12612 */     if (Trace.isOn) {
/* 12613 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "honourRRS(Hconn,Pint,Pint)", new Object[] { hconn, pCompCode, pReason });
/*       */     }
/*       */     
/* 12616 */     pCompCode.x = 2;
/* 12617 */     pReason.x = 2012;
/*       */     
/* 12619 */     if (Trace.isOn) {
/* 12620 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "honourRRS(Hconn,Pint,Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   private static class ReconnectThreadLock
/*       */   {
/*       */     ReconnectThreadLock() {
/* 12634 */       if (Trace.isOn) {
/* 12635 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.ReconnectThreadLock", "<init>()");
/*       */       }
/* 12637 */       if (Trace.isOn) {
/* 12638 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.ReconnectThreadLock", "<init>()");
/*       */       }
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   private static class CcdtCacheLock
/*       */   {
/*       */     CcdtCacheLock() {
/* 12653 */       if (Trace.isOn) {
/* 12654 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.CcdtCacheLock", "<init>()");
/*       */       }
/* 12656 */       if (Trace.isOn) {
/* 12657 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.CcdtCacheLock", "<init>()");
/*       */       }
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   private static class NameListLock
/*       */   {
/*       */     NameListLock() {
/* 12672 */       if (Trace.isOn) {
/* 12673 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.NameListLock", "<init>()");
/*       */       }
/* 12675 */       if (Trace.isOn) {
/* 12676 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.NameListLock", "<init>()");
/*       */       }
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean isAsyncConsumeThread(Hconn hconn) {
/* 12689 */     if (Trace.isOn) {
/* 12690 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "isAsyncConsumeThread(Hconn)", new Object[] { hconn });
/*       */     }
/*       */     
/* 12693 */     boolean traceRet1 = (getTls()).isDispatchThread;
/*       */     
/* 12695 */     if (Trace.isOn) {
/* 12696 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "isAsyncConsumeThread(Hconn)", 
/* 12697 */           Boolean.valueOf(traceRet1));
/*       */     }
/* 12699 */     return traceRet1;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   private void handleDoomedTransaction(RemoteHconn hconn) throws JmqiException {
/* 12709 */     if (Trace.isOn) {
/* 12710 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "handleDoomedTransaction(RemoteHconn)", new Object[] { hconn });
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */     
/* 12716 */     Pint pCompCode = this.env.newPint();
/* 12717 */     Pint pReason = this.env.newPint();
/* 12718 */     MQBACK(hconn, pCompCode, pReason);
/*       */     
/* 12720 */     JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2003, null);
/*       */ 
/*       */     
/* 12723 */     if (Trace.isOn) {
/* 12724 */       Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "handleDoomedTransaction(RemoteHconn)", (Throwable)traceRet1);
/*       */     }
/*       */     
/* 12727 */     throw traceRet1;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void spiDefine(Hconn hconn, boolean replace, LexObjectSelector objectSelector, String likeObjectName, int SelectorCount, int[] pSelectors, int IntAttrCount, int[] pIntAttrs, int CharAttrLength, byte[] pCharAttrs, LexFilterElement filterElement, int AttrErrorsCount, int[] AttrErrors, Pint pCompCode, Pint pReason) {
/* 12752 */     if (Trace.isOn) {
/* 12753 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiDefine(Hconn,boolean,LexObjectSelector,String,int,int [ ],final int,int [ ],int,byte [ ],LexFilterElement,int,int [ ],Pint,Pint)", new Object[] { hconn, 
/*       */             
/* 12755 */             Boolean.valueOf(replace), objectSelector, likeObjectName, 
/* 12756 */             Integer.valueOf(SelectorCount), pSelectors, Integer.valueOf(IntAttrCount), pIntAttrs, 
/* 12757 */             Integer.valueOf(CharAttrLength), pCharAttrs, filterElement, 
/* 12758 */             Integer.valueOf(AttrErrorsCount), AttrErrors, pCompCode, pReason });
/*       */     }
/* 12760 */     pCompCode.x = 2;
/* 12761 */     pReason.x = 2298;
/* 12762 */     if (Trace.isOn) {
/* 12763 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiDefine(Hconn,boolean,LexObjectSelector,String,int,int [ ],final int,int [ ],int,byte [ ],LexFilterElement,int,int [ ],Pint,Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void spiInquire(Hconn hconn, LexObjectSelector objectSelector, int SelectorCount, int[] pSelectors, int IntAttrCount, int[] pIntAttrs, int CharAttrLength, byte[] pCharAttrs, LexFilterElement filterElement, Pint pCompCode, Pint pReason) {
/* 12786 */     if (Trace.isOn) {
/* 12787 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiInquire(Hconn,LexObjectSelector,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final Pint,final Pint)", new Object[] { hconn, objectSelector, 
/*       */             
/* 12789 */             Integer.valueOf(SelectorCount), pSelectors, 
/* 12790 */             Integer.valueOf(IntAttrCount), pIntAttrs, Integer.valueOf(CharAttrLength), pCharAttrs, filterElement, pCompCode, pReason });
/*       */     }
/*       */     
/* 12793 */     pCompCode.x = 2;
/* 12794 */     pReason.x = 2298;
/* 12795 */     if (Trace.isOn) {
/* 12796 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiInquire(Hconn,LexObjectSelector,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void spiDelete(Hconn hconn, LexObjectSelector objectSelector, boolean force, Pint pCompCode, Pint pReason) {
/* 12811 */     if (Trace.isOn) {
/* 12812 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiDelete(Hconn,LexObjectSelector,boolean,Pint,Pint)", new Object[] { hconn, objectSelector, 
/*       */             
/* 12814 */             Boolean.valueOf(force), pCompCode, pReason });
/*       */     }
/* 12816 */     pCompCode.x = 2;
/* 12817 */     pReason.x = 2298;
/* 12818 */     if (Trace.isOn) {
/* 12819 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "spiDelete(Hconn,LexObjectSelector,boolean,Pint,Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   RemoteReconnectThread getReconnectThread() throws JmqiException {
/* 12826 */     if (Trace.isOn) {
/* 12827 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "getReconnectThread()");
/*       */     }
/* 12829 */     synchronized (this.reconnectThreadLock) {
/* 12830 */       if (this.reconnectThread == null) {
/* 12831 */         this.reconnectThread = new RemoteReconnectThread(this.env, this);
/* 12832 */         this.env.getThreadPoolFactory().getThreadPool().enqueue((Runnable)this.reconnectThread);
/* 12833 */         if (Trace.isOn) {
/* 12834 */           Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "getReconnectThread()", "created and launched", this.reconnectThread);
/*       */         }
/*       */       } 
/*       */     } 
/* 12838 */     if (Trace.isOn) {
/* 12839 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "getReconnectThread()", this.reconnectThread);
/*       */     }
/* 12841 */     return this.reconnectThread;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   private void validateMQCBD(MQCBD cbd) throws JmqiException {
/*       */     int validOptions;
/*       */     JmqiException traceRet3;
/* 12853 */     if (Trace.isOn) {
/* 12854 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "validateMQCBD(MQCBD)", new Object[] { cbd });
/*       */     }
/*       */     
/* 12857 */     if (cbd == null) {
/* 12858 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2444, null);
/*       */ 
/*       */       
/* 12861 */       if (Trace.isOn) {
/* 12862 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "validateMQCBD(MQCBD)", (Throwable)traceRet1, 1);
/*       */       }
/* 12864 */       throw traceRet1;
/*       */     } 
/*       */ 
/*       */     
/* 12868 */     if (cbd.getVersion() < 1 || cbd.getVersion() > 1) {
/* 12869 */       JmqiException traceRet2 = new JmqiException(this.env, -1, null, 2, 6128, null);
/*       */       
/* 12871 */       if (Trace.isOn) {
/* 12872 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "validateMQCBD(MQCBD)", (Throwable)traceRet2, 2);
/*       */       }
/* 12874 */       throw traceRet2;
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/* 12880 */     switch (cbd.getCallbackType()) {
/*       */       case 1:
/* 12882 */         validOptions = 8965;
/*       */         break;
/*       */       
/*       */       case 2:
/* 12886 */         validOptions = 8960;
/*       */         break;
/*       */       default:
/* 12889 */         traceRet3 = new JmqiException(this.env, -1, null, 2, 2483, null);
/*       */         
/* 12891 */         if (Trace.isOn) {
/* 12892 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "validateMQCBD(MQCBD)", (Throwable)traceRet3, 3);
/*       */         }
/* 12894 */         throw traceRet3;
/*       */     } 
/*       */ 
/*       */ 
/*       */     
/* 12899 */     if ((cbd.getOptions() & (validOptions ^ 0xFFFFFFFF)) != 0) {
/* 12900 */       JmqiException traceRet6 = new JmqiException(this.env, -1, null, 2, 2046, null);
/*       */       
/* 12902 */       if (Trace.isOn) {
/* 12903 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "validateMQCBD(MQCBD)", (Throwable)traceRet6, 4);
/*       */       }
/* 12905 */       throw traceRet6;
/*       */     } 
/* 12907 */     if (Trace.isOn) {
/* 12908 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "validateMQCBD(MQCBD)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void lpiSPISubscriptionRequest(Hconn hconn, Hobj hsub, int action, LpiSRD lpiSRD, MQSRO subRqOpts, Pint pCompCode, Pint pReason) {
/* 12925 */     if (Trace.isOn) {
/* 12926 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "lpiSPISubscriptionRequest(Hconn,Hobj,int,LpiSRD,MQSRO,final Pint,final Pint)", new Object[] { hconn, hsub, 
/*       */             
/* 12928 */             Integer.valueOf(action), lpiSRD, subRqOpts, pCompCode, pReason });
/*       */     }
/* 12930 */     pCompCode.x = 2;
/* 12931 */     pReason.x = 2298;
/* 12932 */     if (Trace.isOn) {
/* 12933 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "lpiSPISubscriptionRequest(Hconn,Hobj,int,LpiSRD,MQSRO,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public List<byte[]> jmqiInquireNamedSubscribers(Hconn hconn, LpiCALLOPT callOptions, String subName, Pint pCompCode, Pint pReason) {
/* 12950 */     if (Trace.isOn) {
/* 12951 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiInquireNamedSubscribers(Hconn,LpiCALLOPT,String,Pint,Pint)", new Object[] { hconn, callOptions, subName, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */ 
/*       */     
/* 12956 */     RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 12957 */     JmqiTls jTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/*       */     
/* 12959 */     List<byte[]> traceRet1 = JmqiTools.jmqiInquireNamedSubscribers(this.env, this, jTls, hconn, callOptions, subName, pCompCode, pReason);
/*       */     
/* 12961 */     if (Trace.isOn) {
/* 12962 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiInquireNamedSubscribers(Hconn,LpiCALLOPT,String,Pint,Pint)", traceRet1);
/*       */     }
/*       */     
/* 12965 */     return traceRet1;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void lpiSPIInquireNamedSubscribers(Hconn hconn, byte[] CallOptions, int CallOptionsLength, byte[] SubName, int SubNameLength, byte[] SubIdBuffer, Pint SubIdBufferLength, Pint SubscribersReturned, Pint pCompCode, Pint pReason) {
/* 12984 */     if (Trace.isOn) {
/* 12985 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "lpiSPIInquireNamedSubscribers(Hconn,byte [ ],int,byte [ ],int,byte [ ],Pint,Pint,Pint,Pint)", new Object[] { hconn, CallOptions, 
/*       */             
/* 12987 */             Integer.valueOf(CallOptionsLength), SubName, 
/* 12988 */             Integer.valueOf(SubNameLength), SubIdBuffer, SubIdBufferLength, SubscribersReturned, pCompCode, pReason });
/*       */     }
/*       */     
/* 12991 */     pCompCode.x = 2;
/* 12992 */     pReason.x = 2298;
/* 12993 */     if (Trace.isOn) {
/* 12994 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "lpiSPIInquireNamedSubscribers(Hconn,byte [ ],int,byte [ ],int,byte [ ],Pint,Pint,Pint,Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int getMqiId() {
/* 13005 */     if (Trace.isOn) {
/* 13006 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "getMqiId()", "getter", 
/* 13007 */           Integer.valueOf(2));
/*       */     }
/* 13009 */     return 2;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void dump(PrintWriter pw, int level) {
/* 13018 */     if (Trace.isOn) {
/* 13019 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "dump(PrintWriter,int)", new Object[] { pw, 
/* 13020 */             Integer.valueOf(level) });
/*       */     }
/* 13022 */     String prefix = Trace.buildPrefix(level);
/* 13023 */     pw.format("%s%s%n", new Object[] { prefix, toString() });
/* 13024 */     pw.format("Connection Pool : %s%n", new Object[] { this.connectionFactory.toString() });
/* 13025 */     pw.format("All Connections%n", new Object[0]);
/* 13026 */     for (RemoteConnection connection : RemoteConnection.getAllConnections()) {
/* 13027 */       if (connection != null) {
/* 13028 */         connection.dump(pw, level + 1);
/* 13029 */         pw.println();
/*       */       } 
/*       */     } 
/* 13032 */     if (Trace.isOn) {
/* 13033 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "dump(PrintWriter,int)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public String getComponentName() {
/* 13043 */     if (Trace.isOn) {
/* 13044 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "getComponentName()", "getter", "JMQI Remote");
/*       */     }
/* 13046 */     return "JMQI Remote";
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean isLocal() {
/* 13054 */     if (Trace.isOn) {
/* 13055 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "isLocal()", "getter", Boolean.valueOf(false));
/*       */     }
/* 13057 */     return false;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean isCICS() {
/* 13067 */     if (Trace.isOn) {
/* 13068 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "isCICS()", "getter", Boolean.valueOf(false));
/*       */     }
/* 13070 */     return false;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean isIMS() {
/* 13080 */     if (Trace.isOn) {
/* 13081 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "isIMS()", "getter", Boolean.valueOf(false));
/*       */     }
/* 13083 */     return false;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void lpiSPIAdoptUser(Hconn hconn, int flags, LexContext userContext, String applName, int applType, byte[] acctToken, int authToken, LpiAdoptUserOptions options, MQCSP securityParms, int connectOptions, byte[] connectionId, Pint pCompCode, Pint pReason) {
/* 13097 */     if (Trace.isOn) {
/* 13098 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "lpiSPIAdoptUser(Hconn,int,LexContext,String,int,byte [ ],int,LpiAdoptUserOptions,MQCSP,int,byte [ ],Pint,Pint)", new Object[] { hconn, 
/*       */             
/* 13100 */             Integer.valueOf(flags), userContext, applName, Integer.valueOf(applType), acctToken, 
/* 13101 */             Integer.valueOf(authToken), options, securityParms, 
/* 13102 */             Integer.valueOf(connectOptions), connectionId, pCompCode, pReason });
/*       */     }
/*       */     
/* 13105 */     HashMap<String, Object> info = new HashMap<>();
/* 13106 */     info.put("Description", "Unexpected call to lpiSPIAdoptUser");
/* 13107 */     Trace.ffst(this, "lpiSPIAdoptUser(Hconn, int, LexContext, String, int, byte[], int, LpiAdoptUserOptions, MQCSP, int, byte[], Pint, Pint)", "01", info, null);
/*       */ 
/*       */ 
/*       */     
/* 13111 */     pCompCode.x = 2;
/* 13112 */     pReason.x = 2298;
/* 13113 */     if (Trace.isOn) {
/* 13114 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "lpiSPIAdoptUser(Hconn,int,LexContext,String,int,byte [ ],int,LpiAdoptUserOptions,MQCSP,int,byte [ ],Pint,Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void lpiSPICHLAUTH(Hconn hConn, LpiCHLAUTHQuery pParms, Pint pCompCode, Pint pReason) {
/* 13127 */     if (Trace.isOn) {
/* 13128 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "lpiSPICHLAUTH(Hconn,LpiCHLAUTHQuery,Pint,Pint)", new Object[] { hConn, pParms, pCompCode, pReason });
/*       */     }
/*       */     
/* 13131 */     HashMap<String, Object> info = new HashMap<>();
/* 13132 */     info.put("Description", "Unexpected call to lpiSPICHLAUTH");
/* 13133 */     Trace.ffst(this, "lpiSPICHLAUTH(Hconn, LpiCHLAUTHQuery, int, Pint, Pint)", "01", info, null);
/*       */     
/* 13135 */     pCompCode.x = 2;
/* 13136 */     pReason.x = 2298;
/* 13137 */     if (Trace.isOn) {
/* 13138 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "lpiSPICHLAUTH(Hconn,LpiCHLAUTHQuery,Pint,Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void lpiSPIMapCredentials(Hconn hconn, LexContext userContext, String mcaUser, MQCSP securityParms, byte[] accountingToken, String applName, String channelName, String connectionName, Pint pCompCode, Pint pReason) {
/* 13152 */     if (Trace.isOn) {
/* 13153 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "lpiSPIMapCredentials(Hconn,LexContext,String,MQCSP,byte [ ],String,String,String,Pint,Pint)", new Object[] { hconn, userContext, mcaUser, securityParms, accountingToken, applName, channelName, connectionName, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */ 
/*       */     
/* 13158 */     HashMap<String, Object> info = new HashMap<>();
/* 13159 */     info.put("Description", "Unexpected call to lpiSPIMapCredentials");
/* 13160 */     Trace.ffst(this, "lpiSPIPreAdoptUser(Hconn, LexContext, String, MQCSP, byte[], String, String, String, Pint, Pint)", "01", info, null);
/*       */ 
/*       */ 
/*       */     
/* 13164 */     pCompCode.x = 2;
/* 13165 */     pReason.x = 2298;
/* 13166 */     if (Trace.isOn) {
/* 13167 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "lpiSPIMapCredentials(Hconn,LexContext,String,MQCSP,byte [ ],String,String,String,Pint,Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void lpiSPICheckPrivileged(Hconn hconn, String componentName, String entityName, int entityType, Pint pCompCode, Pint pReason) {
/* 13178 */     if (Trace.isOn) {
/* 13179 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "lpiSPICheckPrivileged(Hconn,String,String,int,Pint,Pint)", new Object[] { hconn, componentName, entityName, 
/*       */             
/* 13181 */             Integer.valueOf(entityType), pCompCode, pReason });
/*       */     }
/* 13183 */     HashMap<String, Object> info = new HashMap<>();
/* 13184 */     info.put("Description", "Unexpected call to lpiSPICheckPrivileged");
/* 13185 */     Trace.ffst(this, "lpiSPICheckPrivileged(Hconn,String,String,int,Pint,Pint)", "01", info, null);
/*       */ 
/*       */ 
/*       */     
/* 13189 */     pCompCode.x = 2;
/* 13190 */     pReason.x = 2298;
/* 13191 */     if (Trace.isOn) {
/* 13192 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "lpiSPICheckPrivileged(Hconn,String,String,int,Pint,Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */   
/*       */   class Connector
/*       */   {
/*       */     private static final int ALL_SHARE_OPTIONS = 224;
/*       */     
/*       */     private static final int RECONNECT_ON_OPTIONS = 83886080;
/*       */     
/*       */     private static final int ALL_RECONNECT_OPTIONS = 117440512;
/*       */     
/*       */     private boolean reconnecting;
/*       */     
/*       */     private RemoteHconn remoteHconn;
/*       */     
/*       */     private RemoteSession remoteSession;
/*       */     
/*       */     private MQCNO connectionOptions;
/*       */     
/*       */     private JmqiConnectOptions jmqiConnectOptions;
/* 13214 */     private MQCD mqcd = null;
/* 13215 */     private MQSCO mqsco = null;
/* 13216 */     private URL ccdtUrl = null;
/* 13217 */     private int connectOptsInteger = 0;
/*       */     private boolean reconnectAsDef = false;
/*       */     private RfpMQCONN mqConnDetails;
/*       */     private int transLength;
/* 13221 */     private int options = 0;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     private int xOptions;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     private int fapLevel;
/*       */ 
/*       */ 
/*       */     
/*       */     private boolean psuedoEnteredCall = false;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     Connector(JmqiConnectOptions jmqiConnectOptions, MQCNO connectionOptions, RemoteHconn reconnectHconn) {
/* 13241 */       if (Trace.isOn) {
/* 13242 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "<init>(JmqiConnectOptions,MQCNO,RemoteHconn)", new Object[] { jmqiConnectOptions, connectionOptions, reconnectHconn });
/*       */       }
/*       */ 
/*       */ 
/*       */       
/* 13247 */       this.reconnecting = (reconnectHconn != null);
/* 13248 */       this.remoteHconn = reconnectHconn;
/* 13249 */       this.remoteSession = null;
/* 13250 */       this.connectionOptions = connectionOptions;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 13256 */       this.jmqiConnectOptions = (jmqiConnectOptions != null) ? jmqiConnectOptions : ((JmqiSystemEnvironment)RemoteFAP.this.env).newJmqiConnectOptions();
/*       */       
/* 13258 */       if (Trace.isOn) {
/* 13259 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "<init>(JmqiConnectOptions,MQCNO,RemoteHconn)");
/*       */       }
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     private void jmqiConnect(String name, Phconn pHconn, Hconn parentHconnParam, Pint pCompCode, Pint pReason) {
/* 13275 */       if (Trace.isOn) {
/* 13276 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "jmqiConnect(String, pHconn, Hconn, Pint, Pint)", new Object[] { name, pHconn, parentHconnParam, pCompCode, pReason });
/*       */       }
/*       */ 
/*       */       
/* 13280 */       JmqiException runningException = null;
/*       */       
/* 13282 */       RemoteTls tls = (RemoteTls)RemoteFAP.this.sysenv.getComponentTls(RemoteFAP.this.jmqiCompId);
/* 13283 */       JmqiTls jmqiTls = RemoteFAP.this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/* 13284 */       jmqiTls.lastException = null;
/* 13285 */       ThreadChannelEntry threadChlEntry = new ThreadChannelEntry(RemoteFAP.this.env);
/* 13286 */       RemoteParentHconn parentHconn = (RemoteParentHconn)parentHconnParam;
/*       */       
/*       */       try {
/* 13289 */         if (parentHconn != null) {
/* 13290 */           psuedoEnterCall(parentHconn);
/*       */         }
/*       */         try {
/* 13293 */           MQCSP mqcsp = null;
/*       */           
/* 13295 */           errorIfInUserExit();
/*       */           
/* 13297 */           if (parentHconn != null) {
/* 13298 */             initialiseForJMSSession(parentHconn);
/*       */           }
/*       */           
/* 13301 */           if (this.connectionOptions != null) {
/*       */             
/* 13303 */             this.connectOptsInteger = this.connectionOptions.getOptions();
/*       */             
/* 13305 */             validateConnectOptions();
/*       */ 
/*       */             
/* 13308 */             if ((this.connectOptsInteger & 0x80000) == 0) {
/* 13309 */               this.mqcd = this.connectionOptions.getClientConn();
/*       */             }
/*       */             
/* 13312 */             validateMQCD();
/*       */             
/* 13314 */             setupReconnectOptions();
/*       */ 
/*       */             
/* 13317 */             mqcsp = this.connectionOptions.getSecurityParms();
/*       */ 
/*       */             
/* 13320 */             this.mqsco = this.connectionOptions.getSslConfig();
/*       */           } 
/*       */           
/* 13323 */           int shareOption = getShareOption();
/*       */           
/* 13325 */           if (this.mqsco == null) {
/* 13326 */             defaultMQSCO();
/*       */           }
/*       */           
/* 13329 */           validateSSLResetCount();
/*       */ 
/*       */           
/* 13332 */           this.ccdtUrl = this.jmqiConnectOptions.getCcdtUrl();
/*       */           
/* 13334 */           ChannelListEntry nameEntry = null;
/*       */ 
/*       */           
/* 13337 */           if (this.mqcd == null) {
/* 13338 */             nameEntry = initialiseFromCCDT(name);
/*       */           
/*       */           }
/* 13341 */           else if (this.mqcd.getConnectionName().indexOf(",") != -1) {
/*       */             
/* 13343 */             nameEntry = RemoteFAP.this.getNameListFromMQCD(this.mqcd, 2);
/*       */           } 
/*       */ 
/*       */ 
/*       */           
/* 13348 */           pHconn.setHconn(MQConstants.jmqi_MQHC_UNUSABLE_HCONN);
/*       */           
/* 13350 */           cloneMQCD();
/*       */           
/* 13352 */           if (nameEntry != null) {
/* 13353 */             this.mqcd = nameEntry.selectChannelEntry(((this.connectOptsInteger & 0x100000) == 1048576) ? this.mqcd : null, threadChlEntry);
/*       */           }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/* 13360 */           if (this.mqcd == null) {
/* 13361 */             JmqiException traceRet15 = new JmqiException(RemoteFAP.this.env, -1, null, 2, 2278, null);
/*       */             
/* 13363 */             if (Trace.isOn) {
/* 13364 */               Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "jmqiConnect()", (Throwable)traceRet15, 10);
/*       */             }
/*       */ 
/*       */             
/* 13368 */             throw traceRet15;
/*       */           } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/* 13376 */           if (this.mqcd.getLocalAddress() == null || this.mqcd.getLocalAddress().trim().length() == 0) {
/* 13377 */             this.mqcd.setLocalAddress(RemoteFAP.this.clientCfg.getStringValue(Configuration.ENV_MQ_LCLADDR));
/*       */           }
/*       */           
/* 13380 */           if (Trace.isOn) {
/* 13381 */             Trace.data(this, "jmqiConnect()", "MQCD:" + this.mqcd
/* 13382 */                 .toStringMultiLine(), this.mqcd);
/*       */           }
/*       */           
/* 13385 */           validateQmgrName(name);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*       */           while (true) {
/* 13399 */             overRideShareConv(this.jmqiConnectOptions, (nameEntry != null));
/*       */             
/*       */             try {
/* 13402 */               this.remoteSession = RemoteFAP.this.connectionFactory.getSession(tls, this.connectionOptions, mqcsp, this.mqcd, this.mqsco, name, this.jmqiConnectOptions, parentHconn);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */               
/* 13412 */               String resolvedName = (parentHconn != null) ? this.remoteSession.getRemoteQmgrName() : name;
/*       */               
/* 13414 */               JmqiCodepage cp = this.remoteSession.getCp();
/* 13415 */               boolean swap = this.remoteSession.isSwap();
/*       */ 
/*       */               
/* 13418 */               this.jmqiConnectOptions.setFapLevel(this.remoteSession.getFapLevel());
/*       */               
/* 13420 */               if (this.reconnecting) {
/*       */                 
/* 13422 */                 this.remoteHconn.setSession(this.remoteSession);
/* 13423 */                 this.remoteHconn.setQmName(this.remoteSession.getRemoteQmgrName());
/*       */               }
/*       */               else {
/*       */                 
/* 13427 */                 this.remoteHconn = RemoteHconn.newInstance(RemoteFAP.this.env, RemoteFAP.this, parentHconn, this.remoteSession, resolvedName, this.connectionOptions, this.jmqiConnectOptions);
/*       */                 
/* 13429 */                 if (this.remoteHconn == null) {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */                   
/* 13435 */                   if (parentHconn != null) {
/* 13436 */                     psuedoLeaveCall(parentHconn);
/*       */                   }
/*       */                   
/* 13439 */                   this.remoteSession.disconnect(tls);
/* 13440 */                   this.remoteSession = null;
/*       */                   
/* 13442 */                   pCompCode.x = 2;
/* 13443 */                   pReason.x = 2548;
/*       */                   
/* 13445 */                   String[] inserts = { null, null, this.mqcd.getConnectionName(), MQConstants.lookup(this.mqcd.getTransportType(), "MQXPT_"), null };
/* 13446 */                   runningException = new JmqiException(RemoteFAP.this.env, 9204, inserts, pCompCode.x, pReason.x, null);
/*       */                   
/*       */                   break;
/*       */                 } 
/*       */               } 
/* 13451 */               this.fapLevel = this.remoteHconn.getFapLevel();
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */               
/* 13458 */               if (this.fapLevel >= 14 && this.ccdtUrl != null) {
/* 13459 */                 this.remoteHconn.setCapabilityFlag(4);
/*       */               }
/*       */               
/* 13462 */               if (connectionAndOptionsAllowReconnect() || this.jmqiConnectOptions.jmsChannelSharingMode() == JmqiConnectOptions.ChannelSharingMode.CONNECTION) {
/* 13463 */                 if (this.remoteHconn.isaParentHconn()) {
/*       */                   
/* 13465 */                   ((RemoteParentHconn)this.remoteHconn).setWorkingConnection(this.remoteSession.getParentConnection());
/*       */                 } else {
/*       */                   
/* 13468 */                   ((RemoteChildHconn)this.remoteHconn).getParent().setWorkingConnection(this.remoteSession.getParentConnection());
/*       */                 } 
/*       */               }
/*       */               
/* 13472 */               this.remoteSession.setHconn(this.remoteHconn);
/*       */               
/* 13474 */               pHconn.setHconn(this.remoteHconn);
/*       */               
/* 13476 */               this.remoteHconn.setShareOption(shareOption);
/*       */               
/* 13478 */               this.remoteHconn.setOriginalQueueManagerNameOnceOnly(name);
/*       */               
/* 13480 */               String appName = JmqiTools.getAppName(this.connectionOptions, this.jmqiConnectOptions);
/*       */               
/* 13482 */               this.remoteHconn.setApplicationName(appName);
/*       */               
/* 13484 */               if (this.fapLevel >= 14) {
/* 13485 */                 setupRebalanceOptions(parentHconn);
/*       */               }
/*       */               
/* 13488 */               RfpMQAPI sMQAPI = setupConnectionFlow(resolvedName, cp, swap, appName);
/*       */               
/* 13490 */               this.remoteSession.sendTSH(tls, (RfpTSH)sMQAPI);
/*       */               
/* 13492 */               RfpMQAPI rMQAPI = this.remoteSession.receiveMQIFlow(tls);
/*       */               
/* 13494 */               validateSingleReply(rMQAPI);
/*       */               
/* 13496 */               validateReplyType(rMQAPI);
/*       */ 
/*       */               
/* 13499 */               pCompCode.x = rMQAPI.getCompCode(this.remoteSession.isSwap());
/* 13500 */               pReason.x = rMQAPI.getReasonCode(this.remoteSession.isSwap());
/*       */               
/* 13502 */               if (pCompCode.x == 2) {
/* 13503 */                 this.remoteSession.disconnect(tls);
/* 13504 */                 this.remoteSession = null;
/*       */               }
/*       */               else {
/*       */                 
/* 13508 */                 checkQmId();
/*       */                 
/* 13510 */                 swap = getReplySwapSetting(rMQAPI);
/*       */                 
/* 13512 */                 int returnedFAPCapabilityFlags = 0;
/* 13513 */                 int returnedFAPCNOVersion = 0;
/*       */                 
/* 13515 */                 if (this.fapLevel >= 8) {
/* 13516 */                   RfpFAPMQCNO returnedFapCNO = (RfpFAPMQCNO)this.remoteSession.getRfp(8, (RfpStructure)rMQAPI, rMQAPI
/*       */                       
/* 13518 */                       .hdrSize() + rMQAPI.getRfpOffset() + 120);
/*       */                   
/* 13520 */                   extractAndStoreConnectionId(returnedFapCNO);
/* 13521 */                   if (!this.reconnecting) {
/* 13522 */                     if (parentHconn == null) {
/* 13523 */                       extractAndStoreConntag(returnedFapCNO, swap);
/*       */                     } else {
/*       */                       
/* 13526 */                       this.remoteHconn.setConnTag(parentHconn.getConnTag());
/*       */                     } 
/*       */                   }
/*       */ 
/*       */ 
/*       */                   
/* 13532 */                   if (threadChlEntry.getThisWeightedEntry() != null) {
/* 13533 */                     nameEntry.setThisWeightedEntry(threadChlEntry.getThisWeightedEntry());
/*       */                   }
/*       */                   
/* 13536 */                   storeChannelName(threadChlEntry);
/*       */                   
/* 13538 */                   if (nameEntry != null) {
/* 13539 */                     nameEntry.setUseCount(nameEntry.getUseCount() - 1);
/*       */                   }
/*       */ 
/*       */                   
/* 13543 */                   returnedFAPCNOVersion = returnedFapCNO.getVersion(swap);
/* 13544 */                   if (this.fapLevel >= 12) {
/* 13545 */                     returnedFAPCapabilityFlags = returnedFapCNO.getCapabilityFlags(swap);
/*       */                   }
/*       */                 } 
/*       */                 
/* 13549 */                 this.remoteSession.setMqsco(this.mqsco);
/*       */                 
/* 13551 */                 if (parentHconn == null)
/*       */                 {
/* 13553 */                   this.remoteHconn.enterCall(tls.isDispatchThread, false);
/*       */                 }
/*       */                 
/*       */                 try {
/* 13557 */                   setupForReconnection();
/*       */                 } finally {
/*       */                   
/* 13560 */                   if (Trace.isOn) {
/* 13561 */                     Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "jmqiConnect()");
/*       */                   }
/*       */ 
/*       */                   
/*       */                   try {
/* 13566 */                     releaseTSH(rMQAPI);
/*       */                     
/* 13568 */                     recordSplCapability(returnedFAPCapabilityFlags, returnedFAPCNOVersion);
/*       */                     
/* 13570 */                     if (parentHconn == null)
/*       */                     {
/* 13572 */                       this.remoteHconn.leaveCall(pReason.x);
/*       */                     }
/*       */                   }
/* 13575 */                   catch (JmqiException e) {
/* 13576 */                     if (Trace.isOn) {
/* 13577 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "jmqiConnect()", (Throwable)e, 4);
/*       */                     }
/*       */                   } 
/*       */                 } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */                 
/*       */                 break;
/*       */               } 
/*       */ 
/*       */ 
/*       */ 
/*       */               
/* 13592 */               if ((this.connectOptsInteger & 0x100000) != 0) {
/*       */                 break;
/*       */               }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */               
/* 13603 */               if (nameEntry != null) {
/*       */ 
/*       */                 
/* 13606 */                 runningException = new JmqiException(RemoteFAP.this.env, 9204, new String[] { null, null, this.mqcd.getConnectionName(), MQConstants.lookup(this.mqcd.getTransportType(), "MQXPT_"), null }2, 2059, (Throwable)runningException);
/*       */ 
/*       */ 
/*       */                 
/* 13610 */                 this.mqcd = nameEntry.selectChannelEntry(null, threadChlEntry);
/*       */                 
/* 13612 */                 if (this.mqcd != null) {
/* 13613 */                   cloneMQCD();
/*       */                   
/*       */                   continue;
/*       */                 } 
/* 13617 */                 jmqiTls.lastException = runningException;
/*       */               } 
/*       */ 
/*       */               
/*       */               break;
/* 13622 */             } catch (JmqiException e) {
/* 13623 */               if (Trace.isOn) {
/* 13624 */                 Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "jmqiConnect()", (Throwable)e, 5);
/*       */               }
/*       */ 
/*       */ 
/*       */               
/* 13629 */               if (this.psuedoEnteredCall) {
/* 13630 */                 psuedoLeaveCall(parentHconn);
/*       */               }
/*       */ 
/*       */               
/* 13634 */               if (this.remoteSession != null) {
/*       */                 try {
/* 13636 */                   this.remoteSession.disconnect(tls);
/*       */                 }
/* 13638 */                 catch (JmqiException je) {
/* 13639 */                   if (Trace.isOn) {
/* 13640 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "jmqiConnect()", (Throwable)je, 6);
/*       */                   }
/*       */                 } 
/*       */ 
/*       */ 
/*       */ 
/*       */                 
/* 13647 */                 this.remoteSession = null;
/*       */               } 
/*       */ 
/*       */               
/* 13651 */               if (!this.reconnecting && this.remoteHconn != null) {
/* 13652 */                 RemoteParentHconn rph = this.remoteHconn.getParent();
/* 13653 */                 if (rph != null) {
/* 13654 */                   if (Trace.isOn) {
/* 13655 */                     Trace.data("com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "jmqiConnect()", "removing incomplete Hconn", this.remoteHconn);
/*       */                   }
/*       */ 
/*       */                   
/* 13659 */                   rph.removeChildHconn((RemoteChildHconn)this.remoteHconn);
/*       */                 } 
/* 13661 */                 this.remoteHconn = null;
/*       */               } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */               
/* 13670 */               JmqiException latestException = new JmqiException(RemoteFAP.this.env, 9204, new String[] { JmqiTools.getExSumm((Throwable)e), null, this.mqcd.getConnectionName(), MQConstants.lookup(this.mqcd.getTransportType(), "MQXPT_"), JmqiTools.getFailingCall((Throwable)e) }e.getCompCode(), e.getReason(), (runningException == null) ? (Throwable)e : (Throwable)runningException);
/* 13671 */               runningException = latestException;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */               
/* 13678 */               if (nameEntry != null) {
/*       */                 
/* 13680 */                 this.mqcd = nameEntry.selectChannelEntry(null, threadChlEntry);
/*       */                 
/* 13682 */                 if (this.mqcd != null) {
/* 13683 */                   cloneMQCD();
/* 13684 */                   if (parentHconn != null) {
/* 13685 */                     psuedoEnterCall(parentHconn);
/*       */                   }
/*       */                   
/*       */                   continue;
/*       */                 } 
/*       */               } 
/* 13691 */               throwMostInterestingException(runningException);
/*       */             }
/*       */           
/*       */           } 
/*       */         } finally {
/*       */           
/* 13697 */           if (Trace.isOn) {
/* 13698 */             Trace.finallyBlock("com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "jmqiConnect()");
/*       */           }
/*       */           
/* 13701 */           if ((((parentHconn != null) ? 1 : 0) & this.psuedoEnteredCall) != 0)
/*       */           {
/*       */             
/* 13704 */             psuedoLeaveCall(parentHconn);
/*       */           }
/*       */         }
/*       */       
/*       */       }
/* 13709 */       catch (JmqiException e) {
/* 13710 */         if (Trace.isOn) {
/* 13711 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "jmqiConnect()", (Throwable)e, 7);
/*       */         }
/*       */ 
/*       */ 
/*       */         
/* 13716 */         jmqiTls.lastException = e;
/* 13717 */         pCompCode.x = e.getCompCode();
/* 13718 */         pReason.x = e.getReason();
/*       */       } 
/*       */       
/* 13721 */       if (Trace.isOn) {
/* 13722 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "jmqiConnect()");
/*       */       }
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     private void psuedoEnterCall(RemoteParentHconn parent) throws JmqiException {
/* 13738 */       if (Trace.isOn) {
/* 13739 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "psuedoEnterCall(RemoteParentHconn)", new Object[] { parent });
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 13752 */       parent.acquireReconnectInitiationChildLock();
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 13758 */       parent.checkUsable(parent);
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 13763 */       parent.acquireReconnectExecutionChildLock();
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 13768 */       parent.releaseReconnectInitiationChildLock();
/*       */       
/* 13770 */       (RemoteFAP.this.getTls()).needToCheckForReconnect = false;
/*       */       
/* 13772 */       this.psuedoEnteredCall = true;
/*       */       
/* 13774 */       if (Trace.isOn) {
/* 13775 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "psuedoEnterCall(RemoteParentHconn)");
/*       */       }
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     private void psuedoLeaveCall(RemoteParentHconn parent) throws JmqiException {
/* 13790 */       if (Trace.isOn) {
/* 13791 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "psuedoLeaveCall(RemoteParentHconn)", new Object[] { parent });
/*       */       }
/* 13793 */       parent.releaseReconnectExecutionChildLock();
/* 13794 */       (RemoteFAP.this.getTls()).needToCheckForReconnect = true;
/*       */       
/* 13796 */       this.psuedoEnteredCall = false;
/*       */       
/* 13798 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "psuedoLeaveCall(RemoteParentHconn)");
/*       */     }
/*       */     
/*       */     private int getShareOption() throws JmqiException {
/* 13802 */       if (Trace.isOn) {
/* 13803 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", " getShareOption()");
/*       */       }
/*       */ 
/*       */       
/* 13807 */       int shareOption = this.connectOptsInteger & 0xE0;
/*       */       
/* 13809 */       if (shareOption == 0) {
/* 13810 */         shareOption = 32;
/*       */       }
/* 13812 */       if (shareOption != 32 && shareOption != 64 && shareOption != 128) {
/*       */         
/* 13814 */         JmqiException traceRet = new JmqiException(RemoteFAP.this.env, -1, null, 2, 2046, null);
/*       */         
/* 13816 */         if (Trace.isOn) {
/* 13817 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "getShareOption()", (Throwable)traceRet);
/*       */         }
/*       */ 
/*       */         
/* 13821 */         throw traceRet;
/*       */       } 
/* 13823 */       if (Trace.isOn) {
/* 13824 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", " getShareOption()", shareOption);
/*       */       }
/*       */       
/* 13827 */       return shareOption;
/*       */     }
/*       */ 
/*       */ 
/*       */     
/*       */     private void throwMostInterestingException(JmqiException runningException) throws JmqiException {
/* 13833 */       JmqiException mostInteresting = RemoteFAP.this.getMostInterestingException(runningException);
/*       */       
/* 13835 */       if (Trace.isOn) {
/* 13836 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "throwMostInterestingException(JmqiException)", (Throwable)mostInteresting);
/*       */       }
/*       */ 
/*       */       
/* 13840 */       throw mostInteresting;
/*       */     }
/*       */     
/*       */     private void errorIfInUserExit() throws JmqiException {
/* 13844 */       if (Trace.isOn) {
/* 13845 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "errorIfInUserExit()");
/*       */       }
/*       */ 
/*       */       
/* 13849 */       RemoteTls tls = (RemoteTls)RemoteFAP.this.sysenv.getComponentTls(RemoteFAP.this.jmqiCompId);
/* 13850 */       if (tls.inExit) {
/* 13851 */         JmqiException traceRet11 = new JmqiException(RemoteFAP.this.env, -1, null, 2, 2219, null);
/*       */ 
/*       */         
/* 13854 */         if (Trace.isOn) {
/* 13855 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "errorIfInUserExit()", (Throwable)traceRet11, 1);
/*       */         }
/*       */ 
/*       */         
/* 13859 */         throw traceRet11;
/*       */       } 
/* 13861 */       if (Trace.isOn) {
/* 13862 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "errorIfInUserExit()");
/*       */       }
/*       */     }
/*       */ 
/*       */     
/*       */     private void releaseTSH(RfpMQAPI rMQAPI) {
/* 13868 */       if (Trace.isOn) {
/* 13869 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "releaseTSH(RfpMQAPI)", new Object[] { rMQAPI });
/*       */       }
/*       */       
/* 13872 */       if (rMQAPI != null) {
/*       */         try {
/* 13874 */           this.remoteSession.releaseReceivedTSH((RfpTSH)rMQAPI);
/*       */         }
/* 13876 */         catch (JmqiException e) {
/* 13877 */           if (Trace.isOn) {
/* 13878 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "validateConnectOptions()", (Throwable)e, 3);
/*       */           }
/*       */         } 
/*       */       }
/*       */ 
/*       */ 
/*       */       
/* 13885 */       if (Trace.isOn) {
/* 13886 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "releaseTSH(RfpMQAPI)");
/*       */       }
/*       */     }
/*       */ 
/*       */     
/*       */     private void recordSplCapability(int returnedFAPCapabilityFlags, int returnedFAPCNOVersion) {
/* 13892 */       if (Trace.isOn) {
/* 13893 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "recordSplCapability(int, int)", new Object[] {
/* 13894 */               Integer.valueOf(returnedFAPCapabilityFlags), Integer.valueOf(returnedFAPCNOVersion)
/*       */             });
/*       */       }
/*       */       
/* 13898 */       QmgrSplCapability capability = QmgrSplCapability.UNKNOWN;
/*       */ 
/*       */ 
/*       */       
/* 13902 */       if (RemoteFAP.this.clientCfg.getBoolValue(Configuration.ENV_AMQ_DISABLE_CLIENT_AMS) == true) {
/* 13903 */         if (Trace.isOn) {
/* 13904 */           Trace.data(this, "recordSplCapability(int, int)", "Client AMS has been disabled as the AMQ_DISABLE_CLIENT_AMS environment property has been set");
/*       */         }
/*       */ 
/*       */ 
/*       */         
/* 13909 */         capability = QmgrSplCapability.NOT_SUPPORTED;
/*       */       }
/* 13911 */       else if (returnedFAPCNOVersion >= 2) {
/* 13912 */         if (returnedFAPCapabilityFlags == 0) {
/* 13913 */           capability = QmgrSplCapability.UNKNOWN;
/*       */         }
/* 13915 */         else if ((returnedFAPCapabilityFlags & 0x1) != 0) {
/* 13916 */           capability = QmgrSplCapability.SUPPORTED;
/*       */         } else {
/*       */           
/* 13919 */           capability = QmgrSplCapability.NOT_SUPPORTED;
/*       */         } 
/*       */       } 
/* 13922 */       this.remoteHconn.setQmgrSplCapability(capability);
/* 13923 */       if (Trace.isOn) {
/* 13924 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "recordSplCapability(int, int)");
/*       */       }
/*       */     }
/*       */ 
/*       */     
/*       */     private void setupForReconnection() throws JmqiException {
/* 13930 */       if (Trace.isOn) {
/* 13931 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "setupForReconnection()");
/*       */       }
/*       */       
/* 13934 */       this.remoteSession.checkIfDisconnected();
/*       */ 
/*       */       
/* 13937 */       if (this.reconnecting) {
/* 13938 */         makeHconnReconnectable();
/*       */       }
/* 13940 */       else if (connectionAndOptionsAllowReconnect()) {
/* 13941 */         if (Trace.isOn) {
/* 13942 */           Trace.data(this, "setupForReconnection()", "Queue manager supports reconnection. Checking if we've been asked to establish a ReconnectableHconn");
/*       */         }
/*       */         
/* 13945 */         makeHconnReconnectable();
/*       */       } 
/*       */       
/* 13948 */       if (Trace.isOn) {
/* 13949 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "setupForReconnection()");
/*       */       }
/*       */     }
/*       */ 
/*       */     
/*       */     private boolean connectionAndOptionsAllowReconnect() throws JmqiException {
/* 13955 */       if (this.remoteHconn.getParentConnection().supportsReconnection()) {
/* 13956 */         if ((this.connectionOptions.getOptions() & 0x5000000) != 0) {
/* 13957 */           return true;
/*       */         }
/* 13959 */         if (this.jmqiConnectOptions.getRebalancingListener() != null) {
/* 13960 */           return true;
/*       */         }
/*       */       } 
/* 13963 */       return false;
/*       */     }
/*       */     
/*       */     private void makeHconnReconnectable() throws JmqiException {
/* 13967 */       if (Trace.isOn) {
/* 13968 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "makeHconnReconnectable()");
/*       */       }
/*       */       
/* 13971 */       this.remoteHconn.setReconnectable(true);
/*       */ 
/*       */       
/* 13974 */       this.remoteHconn.getUid();
/* 13975 */       if (Trace.isOn) {
/* 13976 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "makeHconnReconnectable()");
/*       */       }
/*       */     }
/*       */ 
/*       */     
/*       */     private void checkQmId() throws JmqiException {
/* 13982 */       if (Trace.isOn) {
/* 13983 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "checkQmId()");
/*       */       }
/*       */ 
/*       */       
/* 13987 */       if (this.connectionOptions != null && (this.connectionOptions.getOptions() & 0x4000000) != 0 && this.jmqiConnectOptions
/* 13988 */         .getReconnectionQmId() != null && 
/* 13989 */         !this.remoteHconn.getUid().equals(this.jmqiConnectOptions.getReconnectionQmId())) {
/*       */         
/* 13991 */         JmqiException traceRet = new JmqiException(RemoteFAP.this.env, -1, null, 2, 2546, null);
/*       */         
/* 13993 */         if (Trace.isOn) {
/* 13994 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "checkQmId()", (Throwable)traceRet, 0);
/*       */         }
/*       */         
/* 13997 */         throw traceRet;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 14003 */       if (this.jmqiConnectOptions.isWasReconnectQmgr() && this.jmqiConnectOptions.getReconnectionQmId() != null && 
/* 14004 */         !this.remoteHconn.getUid().equals(this.jmqiConnectOptions.getReconnectionQmId())) {
/*       */         
/* 14006 */         JmqiException traceRet = new JmqiException(RemoteFAP.this.env, -1, null, 2, 2546, null);
/*       */         
/* 14008 */         if (Trace.isOn) {
/* 14009 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "checkQmId()", (Throwable)traceRet, 1);
/*       */         }
/*       */ 
/*       */         
/* 14013 */         throw traceRet;
/*       */       } 
/*       */       
/* 14016 */       if (Trace.isOn) {
/* 14017 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "checkQmId()");
/*       */       }
/*       */     }
/*       */ 
/*       */     
/*       */     private void storeChannelName(ThreadChannelEntry threadChlEntry) {
/* 14023 */       if (Trace.isOn) {
/* 14024 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "storeChannelName(ThreadChannelEntry)", new Object[] { threadChlEntry });
/*       */       }
/*       */ 
/*       */ 
/*       */       
/* 14029 */       if ((this.connectOptsInteger & 0x80000) != 0) {
/*       */         ChannelEntry chlEntry;
/*       */         
/* 14032 */         if (threadChlEntry.getThisWeightedEntry() != null) {
/* 14033 */           chlEntry = threadChlEntry.getThisWeightedEntry();
/*       */         } else {
/*       */           
/* 14036 */           chlEntry = threadChlEntry.getThisAlphaEntry();
/*       */         } 
/*       */         
/* 14039 */         this.mqcd.setChannelName(chlEntry.getChannel().getChannelName());
/*       */       } 
/*       */       
/* 14042 */       if (Trace.isOn) {
/* 14043 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "storeChannelName(ThreadChannelEntry)");
/*       */       }
/*       */     }
/*       */ 
/*       */     
/*       */     private void extractAndStoreConnectionId(RfpFAPMQCNO returnedFapCNO) {
/* 14049 */       if (Trace.isOn) {
/* 14050 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "extractAndStoreConnectionId(RfpFAPMQCNO)", new Object[] { returnedFapCNO });
/*       */       }
/*       */ 
/*       */       
/* 14054 */       byte[] connectionId = new byte[24];
/*       */       
/* 14056 */       returnedFapCNO.getConnectionId(connectionId);
/*       */       
/* 14058 */       this.remoteSession.setConnectionId(connectionId);
/* 14059 */       this.connectionOptions.setConnectionId(connectionId);
/*       */ 
/*       */       
/* 14062 */       if (this.connectionOptions != null && 
/* 14063 */         this.connectionOptions.getVersion() >= 5) {
/* 14064 */         this.connectionOptions.setConnectionId(connectionId);
/*       */       }
/*       */ 
/*       */       
/* 14068 */       if (Trace.isOn) {
/* 14069 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "extractAndStoreConnectionId(RfpFAPMQCNO)");
/*       */       }
/*       */     }
/*       */ 
/*       */     
/*       */     private void extractAndStoreConntag(RfpFAPMQCNO returnedFapCNO, boolean swap) {
/* 14075 */       if (Trace.isOn) {
/* 14076 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "extractAndStoreConntag(RfpFAPMQCNO)", new Object[] { returnedFapCNO });
/*       */       }
/*       */ 
/*       */       
/* 14080 */       if (returnedFapCNO.getVersion(swap) >= 3) {
/* 14081 */         byte[] connTag = new byte[128];
/* 14082 */         returnedFapCNO.getRetConnTag(connTag);
/* 14083 */         this.remoteHconn.setConnTag(connTag);
/*       */       } 
/*       */       
/* 14086 */       if (Trace.isOn)
/* 14087 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "extractAndStoreConntag(RfpFAPMQCNO)"); 
/*       */     }
/*       */     
/*       */     private boolean getReplySwapSetting(RfpMQAPI rMQAPI) throws JmqiException {
/*       */       HashMap<String, Object> info;
/*       */       JmqiException traceRet9;
/* 14093 */       if (Trace.isOn) {
/* 14094 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "getReplySwapSetting(RfpMQAPI)", new Object[] { rMQAPI });
/*       */       }
/*       */ 
/*       */       
/* 14098 */       boolean swap = false;
/* 14099 */       switch (rMQAPI.getEncoding()) {
/*       */         case 1:
/* 14101 */           swap = false;
/*       */           break;
/*       */         case 2:
/* 14104 */           swap = true;
/*       */           break;
/*       */         default:
/* 14107 */           info = new HashMap<>();
/* 14108 */           info.put("remoteEncoding", Integer.valueOf(rMQAPI.getEncoding()));
/* 14109 */           info.put("Description", "Unknown encoding received from queue manager");
/* 14110 */           Trace.ffst(this, "getReplySwapSetting(RfpMQAPI)", "00", info, null);
/*       */           
/* 14112 */           traceRet9 = new JmqiException(RemoteFAP.this.env, -1, null, 2, 6106, null);
/*       */ 
/*       */           
/* 14115 */           if (Trace.isOn) {
/* 14116 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "validateConnectOptions()", (Throwable)traceRet9, 17);
/*       */           }
/*       */ 
/*       */           
/* 14120 */           throw traceRet9;
/*       */       } 
/*       */       
/* 14123 */       if (Trace.isOn) {
/* 14124 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "getReplySwapSetting(RfpMQAPI)", 
/* 14125 */             Boolean.valueOf(swap));
/*       */       }
/*       */       
/* 14128 */       return swap;
/*       */     }
/*       */     
/*       */     private void validateReplyType(RfpMQAPI rMQAPI) throws JmqiException {
/* 14132 */       if (Trace.isOn) {
/* 14133 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "validateReplyType(RfpMQAPI)", new Object[] { rMQAPI });
/*       */       }
/*       */ 
/*       */ 
/*       */       
/* 14138 */       if (rMQAPI.getSegmentType() != 145) {
/* 14139 */         HashMap<String, Object> info = new HashMap<>();
/* 14140 */         info.put("SegmentType", Integer.valueOf(rMQAPI.getSegmentType()));
/* 14141 */         info.put("Description", "MQCONN reply expected");
/* 14142 */         Trace.ffst(this, "validateReplyType(RfpMQAPI)", "00", info, null);
/* 14143 */         JmqiException traceRet = new JmqiException(RemoteFAP.this.env, -1, null, 2, 2195, null);
/*       */         
/* 14145 */         if (Trace.isOn) {
/* 14146 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "validateReplyType(RfpMQAPI)", (Throwable)traceRet);
/*       */         }
/*       */         
/* 14149 */         throw traceRet;
/*       */       } 
/*       */       
/* 14152 */       if (Trace.isOn) {
/* 14153 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "validateReplyType(RfpMQAPI)");
/*       */       }
/*       */     }
/*       */ 
/*       */     
/*       */     private void validateSingleReply(RfpMQAPI rMQAPI) throws JmqiException {
/* 14159 */       if (Trace.isOn) {
/* 14160 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "validateSingleReply(RfpMQAPI)", new Object[] { rMQAPI });
/*       */       }
/*       */ 
/*       */ 
/*       */       
/* 14165 */       if ((rMQAPI.getControlFlags1() & 0x10) == 0 || (rMQAPI.getControlFlags1() & 0x20) == 0) {
/* 14166 */         HashMap<String, Object> info = new HashMap<>();
/* 14167 */         info.put("ControlFlags1", Integer.valueOf(rMQAPI.getControlFlags1()));
/* 14168 */         info.put("Description", "Exactly 1 flow expected in response to MQCONN");
/* 14169 */         Trace.ffst(this, "validateSingleReply(RfpMQAPI)", "01", info, null);
/* 14170 */         JmqiException traceRet = new JmqiException(RemoteFAP.this.env, -1, null, 2, 2195, null);
/*       */         
/* 14172 */         if (Trace.isOn) {
/* 14173 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "validateSingleReply(RfpMQAPI)", (Throwable)traceRet);
/*       */         }
/*       */         
/* 14176 */         throw traceRet;
/*       */       } 
/*       */       
/* 14179 */       if (Trace.isOn) {
/* 14180 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "validateSingleReply(RfpMQAPI)");
/*       */       }
/*       */     }
/*       */ 
/*       */     
/*       */     private RfpMQAPI setupConnectionFlow(String name, JmqiCodepage cp, boolean swap, String appName) throws JmqiException {
/* 14186 */       if (Trace.isOn) {
/* 14187 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "setupConnectionFlow(String, JmqiCodepage, boolean, String)", new Object[] { name, cp, 
/* 14188 */               Boolean.valueOf(swap), appName });
/*       */       }
/*       */       
/* 14191 */       RemoteTls tls = (RemoteTls)RemoteFAP.this.sysenv.getComponentTls(RemoteFAP.this.jmqiCompId);
/* 14192 */       JmqiTls jmqiTls = RemoteFAP.this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/*       */       
/* 14194 */       RfpMQAPI sMQAPI = this.remoteSession.allocateMQAPI(129);
/*       */       
/* 14196 */       this.mqConnDetails = (RfpMQCONN)this.remoteSession.getRfp(4, (RfpStructure)sMQAPI, sMQAPI.hdrSize());
/*       */       
/* 14198 */       this.transLength = sMQAPI.hdrSize();
/* 14199 */       this.options |= 0x1;
/* 14200 */       this.xOptions = 0;
/*       */ 
/*       */       
/* 14203 */       this.mqConnDetails.setQMgrName(name, cp, jmqiTls);
/* 14204 */       this.mqConnDetails.setApplType(28, swap);
/* 14205 */       this.mqConnDetails.setApplName(appName, cp, jmqiTls);
/* 14206 */       this.mqConnDetails.setAcctToken(MQConstants.MQACT_NONE);
/*       */       
/* 14208 */       if (this.fapLevel < 3) {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/* 14214 */         this.transLength += 112;
/*       */       } else {
/*       */         
/* 14217 */         this.transLength += 120;
/* 14218 */         this.options |= 0x2;
/* 14219 */         if (this.connectionOptions != null) {
/* 14220 */           this.xOptions = this.connectionOptions.getOptions();
/*       */         }
/* 14222 */         this.xOptions &= 0xFFEAFF1F;
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/* 14227 */       int fapCNOSize = 0;
/* 14228 */       if (this.fapLevel >= 8) {
/* 14229 */         fapCNOSize = setupFAPMQCNO(swap, sMQAPI);
/* 14230 */         this.transLength += fapCNOSize;
/*       */       } 
/*       */ 
/*       */       
/* 14234 */       if (this.fapLevel >= 10) {
/* 14235 */         configureReconnection(cp, this.transLength);
/* 14236 */         this.transLength += 72;
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*       */       }
/* 14242 */       else if (this.connectionOptions != null && (
/* 14243 */         this.connectionOptions.getOptions() & 0x5000000) != 0) {
/* 14244 */         handleCannotReconnect();
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 14251 */       sMQAPI.setControlFlags1(48);
/* 14252 */       sMQAPI.setTransLength(this.transLength);
/* 14253 */       sMQAPI.setCallLength(this.transLength);
/*       */       
/* 14255 */       this.mqConnDetails.setOptions(this.options, swap);
/* 14256 */       this.mqConnDetails.setXOptions(this.xOptions, swap);
/*       */       
/* 14258 */       if (Trace.isOn) {
/* 14259 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "setupConnectionFlow(String, JmqiCodepage, boolean, String)", sMQAPI);
/*       */       }
/*       */ 
/*       */       
/* 14263 */       return sMQAPI;
/*       */     }
/*       */     
/*       */     private void configureReconnection(JmqiCodepage cp, int reconnectionIdOffset) throws JmqiException {
/* 14267 */       if (Trace.isOn) {
/* 14268 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "configureReconnection(JmqiCodepage cp, int)", new Object[] { cp, 
/* 14269 */               Integer.valueOf(reconnectionIdOffset) });
/*       */       }
/*       */       
/* 14272 */       int reconnectionQmIdOffset = reconnectionIdOffset + 24;
/*       */       
/* 14274 */       RemoteTls tls = (RemoteTls)RemoteFAP.this.sysenv.getComponentTls(RemoteFAP.this.jmqiCompId);
/* 14275 */       JmqiTls jmqiTls = RemoteFAP.this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/* 14276 */       if (this.remoteHconn != null) {
/* 14277 */         this.options |= 0x4;
/* 14278 */         if (this.jmqiConnectOptions.getReconnectionId() != null && this.jmqiConnectOptions.getReconnectionQmId() != null) {
/* 14279 */           this.mqConnDetails.setReconnectionId(this.jmqiConnectOptions.getReconnectionId(), reconnectionIdOffset);
/* 14280 */           this.mqConnDetails.setReconnectionQmId(this.jmqiConnectOptions.getReconnectionQmId(), cp, jmqiTls, reconnectionQmIdOffset);
/*       */         } else {
/*       */           
/* 14283 */           this.mqConnDetails.clearReconnectionId(reconnectionIdOffset);
/* 14284 */           this.mqConnDetails.clearReconnectionQmId(reconnectionQmIdOffset);
/*       */         } 
/*       */       } 
/*       */       
/* 14288 */       if (Trace.isOn) {
/* 14289 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "configureReconnection(JmqiCodepage cp)");
/*       */       }
/*       */     }
/*       */ 
/*       */     
/*       */     private int setupFAPMQCNO(boolean swap, RfpMQAPI sMQAPI) throws JmqiException {
/* 14295 */       if (Trace.isOn) {
/* 14296 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "setupFAPMQCNO(boolean, RfpMQAPI)", new Object[] {
/* 14297 */               Boolean.valueOf(swap), sMQAPI
/*       */             });
/*       */       }
/* 14300 */       RfpFAPMQCNO fapMQCNO = (RfpFAPMQCNO)this.remoteSession.getRfp(8, (RfpStructure)sMQAPI, this.transLength);
/* 14301 */       fapMQCNO.initEyecatcher();
/* 14302 */       fapMQCNO.initVersion(this.fapLevel, swap);
/*       */       
/* 14304 */       if (this.connectionOptions != null) {
/* 14305 */         if (this.connectionOptions.getVersion() >= 3) {
/* 14306 */           byte[] connTag = this.connectionOptions.getConnTag();
/* 14307 */           fapMQCNO.setConnTag(connTag);
/* 14308 */           fapMQCNO.setRetConnTag(connTag);
/*       */         } 
/*       */         
/* 14311 */         MQBNO mqbno = null;
/* 14312 */         if (this.connectionOptions.getVersion() >= 8) {
/* 14313 */           mqbno = this.connectionOptions.getMqbno();
/*       */         }
/*       */         
/* 14316 */         if (fapMQCNO.supportsRebalancingOptions()) {
/* 14317 */           if (mqbno != null) {
/* 14318 */             fapMQCNO.setRebalancingApplicationType(mqbno.getType(), swap);
/* 14319 */             fapMQCNO.setRebalancingTimeout(mqbno.getTimeout(), swap);
/* 14320 */             fapMQCNO.setRebalancingOptions(mqbno.getBalanceOptions(), swap);
/*       */           } else {
/*       */             
/* 14323 */             fapMQCNO.setRebalancingApplicationType(0, swap);
/* 14324 */             fapMQCNO.setRebalancingTimeout(-1, swap);
/* 14325 */             fapMQCNO.setRebalancingOptions(0, swap);
/*       */           } 
/*       */         }
/*       */       } 
/*       */       
/* 14330 */       fapMQCNO.setCapabilityFlags(this.remoteHconn.getCapabilityFlag(), swap);
/*       */       
/* 14332 */       if (Trace.isOn) {
/* 14333 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "setupFAPMQCNO(boolean, RfpMQAPI)", fapMQCNO
/* 14334 */             .getSize(swap));
/*       */       }
/*       */       
/* 14337 */       return fapMQCNO.getSize(swap);
/*       */     }
/*       */     
/*       */     private void handleCannotReconnect() throws JmqiException {
/* 14341 */       if (Trace.isOn) {
/* 14342 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "handleCannotReconnect()");
/*       */       }
/*       */ 
/*       */       
/* 14346 */       if (this.reconnectAsDef) {
/*       */         
/* 14348 */         if (Trace.isOn) {
/* 14349 */           Trace.data(this, "handleCannotReconnect()", "RECONNECT_AS_DEF specified, and reconnect options set...but the queue manager doesn't support it");
/*       */           
/* 14351 */           Trace.data(this, "handleCannotReconnect()", "Turning reconnect options off");
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/* 14358 */         this.connectionOptions.setOptions(this.connectionOptions.getOptions() & 0xFAFFFFFF);
/* 14359 */         this.xOptions &= 0xFAFFFFFF;
/*       */       } else {
/*       */         
/* 14362 */         if (Trace.isOn) {
/* 14363 */           Trace.data(this, "handleCannotReconnect()", "Connection failed - reconnectable connection requires multiplexing and FAP 10");
/*       */         }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/* 14370 */         JmqiException traceRet = new JmqiException(RemoteFAP.this.env, -1, null, 2, 2012, null);
/*       */         
/* 14372 */         if (Trace.isOn) {
/* 14373 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "handleCannotReconnect()", (Throwable)traceRet);
/*       */         }
/*       */ 
/*       */         
/* 14377 */         throw traceRet;
/*       */       } 
/*       */       
/* 14380 */       if (Trace.isOn) {
/* 14381 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "handleCannotReconnect()");
/*       */       }
/*       */     }
/*       */ 
/*       */     
/*       */     private void validateQmgrName(String name) throws JmqiException {
/* 14387 */       if (Trace.isOn) {
/* 14388 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "validateQmgrName(String)", new Object[] { name });
/*       */       }
/*       */ 
/*       */ 
/*       */       
/* 14393 */       if (name == null) {
/* 14394 */         JmqiException traceRet = new JmqiException(RemoteFAP.this.env, -1, null, 2, 2058, null);
/*       */         
/* 14396 */         if (Trace.isOn) {
/* 14397 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "validateQmgrName()", (Throwable)traceRet, 1);
/*       */         }
/*       */ 
/*       */         
/* 14401 */         throw traceRet;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 14407 */       if (name.length() != 0 && name.charAt(0) == ' ' && name.trim().length() != 0) {
/* 14408 */         JmqiException traceRet = new JmqiException(RemoteFAP.this.env, -1, null, 2, 2058, null);
/*       */         
/* 14410 */         if (Trace.isOn) {
/* 14411 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "validateQmgrName()", (Throwable)traceRet, 2);
/*       */         }
/*       */ 
/*       */         
/* 14415 */         throw traceRet;
/*       */       } 
/*       */       
/* 14418 */       if (Trace.isOn) {
/* 14419 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "validateQmgrName(String)");
/*       */       }
/*       */     }
/*       */ 
/*       */     
/*       */     private ChannelListEntry initialiseFromCCDT(String name) throws JmqiException {
/* 14425 */       if (Trace.isOn) {
/* 14426 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "initialiseFromCCDT(String)", new Object[] { name });
/*       */       }
/*       */ 
/*       */       
/* 14430 */       if (this.ccdtUrl == null) {
/* 14431 */         defaultCCDTUrl();
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 14438 */       RemoteCCDT ccdt = RemoteFAP.this.getCcdt(this.ccdtUrl);
/* 14439 */       ChannelListEntry nameEntry = RemoteFAP.this.getNameList(ccdt, name, 2);
/*       */ 
/*       */ 
/*       */       
/* 14443 */       MQAIR[] authInfoRecords = ccdt.getAuthInfoRecords(1);
/* 14444 */       this.mqsco.setAuthInfoRecords(authInfoRecords);
/*       */       
/* 14446 */       if (Trace.isOn) {
/* 14447 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "initialiseFromCCDT(String)", nameEntry);
/*       */       }
/*       */ 
/*       */       
/* 14451 */       return nameEntry;
/*       */     }
/*       */     
/*       */     private void overRideShareConv(JmqiConnectOptions pJmqiConnectOpts, boolean ccdtInUse) {
/* 14455 */       if (Trace.isOn) {
/* 14456 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "overRideShareConv(JmqiConnectOptions, boolean)", new Object[] { pJmqiConnectOpts, 
/* 14457 */               Boolean.valueOf(ccdtInUse) });
/*       */       }
/*       */ 
/*       */ 
/*       */       
/* 14462 */       if (ccdtInUse && 
/* 14463 */         pJmqiConnectOpts != null && 
/* 14464 */         pJmqiConnectOpts.isSet(64)) {
/* 14465 */         this.mqcd.setSharingConversations(pJmqiConnectOpts.getSharingConversations());
/*       */       }
/*       */ 
/*       */ 
/*       */       
/* 14470 */       if (Trace.isOn) {
/* 14471 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "overRideShareConv(JmqiConnectOptions, boolean)");
/*       */       }
/*       */     }
/*       */ 
/*       */     
/*       */     private void cloneMQCD() throws JmqiException {
/* 14477 */       if (Trace.isOn) {
/* 14478 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "cloneMQCD()");
/*       */       }
/*       */       
/*       */       try {
/* 14482 */         this.mqcd = (this.mqcd == null) ? null : (MQCD)this.mqcd.clone();
/*       */       }
/* 14484 */       catch (CloneNotSupportedException e) {
/* 14485 */         if (Trace.isOn) {
/* 14486 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "cloneMQCD()", e, 2);
/*       */         }
/*       */         
/* 14489 */         HashMap<String, Object> info = new HashMap<>();
/* 14490 */         info.put("Description", "MQCD failed to be cloned");
/* 14491 */         Trace.ffst(this, "cloneMQCD()", "01", info, null);
/* 14492 */         JmqiException traceRet = new JmqiException(RemoteFAP.this.env, -1, null, 2, 2195, null);
/*       */ 
/*       */         
/* 14495 */         if (Trace.isOn) {
/* 14496 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "cloneMQCD()", (Throwable)traceRet, 9);
/*       */         }
/* 14498 */         throw traceRet;
/*       */       } 
/*       */       
/* 14501 */       if (Trace.isOn) {
/* 14502 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "cloneMQCD()");
/*       */       }
/*       */     }
/*       */     
/*       */     private void defaultCCDTUrl() throws JmqiException {
/* 14507 */       if (Trace.isOn) {
/* 14508 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "defaultCCDTUrl()");
/*       */       }
/*       */ 
/*       */       
/* 14512 */       String urlString = RemoteFAP.this.clientCfg.getStringValue(Configuration.ENV_MQCHLURL);
/*       */       
/* 14514 */       if (urlString == null) {
/* 14515 */         JmqiException traceRet = new JmqiException(RemoteFAP.this.env, -1, null, 2, 2058, null);
/*       */         
/* 14517 */         if (Trace.isOn) {
/* 14518 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "defaultCCDTUrl()", (Throwable)traceRet, 1);
/*       */         }
/*       */         
/* 14521 */         throw traceRet;
/*       */       } 
/*       */       
/*       */       try {
/* 14525 */         this.ccdtUrl = new URL(urlString);
/*       */       }
/* 14527 */       catch (Exception e) {
/* 14528 */         if (Trace.isOn) {
/* 14529 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "defaultCCDTUrl()", e);
/*       */         }
/*       */ 
/*       */         
/* 14533 */         JmqiException traceRet = new JmqiException(RemoteFAP.this.env, 9516, new String[] { JmqiTools.getExSumm(e), null, urlString }, 2, 2058, e);
/*       */         
/* 14535 */         if (Trace.isOn) {
/* 14536 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "defaultCCDTUrl()", (Throwable)traceRet, 2);
/*       */         }
/*       */         
/* 14539 */         throw traceRet;
/*       */       } 
/*       */       
/* 14542 */       if (Trace.isOn) {
/* 14543 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "defaultCCDTUrl()");
/*       */       }
/*       */     }
/*       */ 
/*       */ 
/*       */     
/*       */     private void validateSSLResetCount() {
/* 14550 */       if (this.mqsco.getKeyResetCount() > 0 && this.mqsco.getKeyResetCount() < RemoteFAP.this.SSL_MIN_RESET_COUNT) {
/* 14551 */         this.mqsco.setKeyResetCount(RemoteFAP.this.SSL_MIN_RESET_COUNT);
/* 14552 */         if (Trace.isOn) {
/* 14553 */           Trace.data(this, "validateConnectOptions()", "SSLResetCount overridden to ", 
/* 14554 */               Integer.valueOf(RemoteFAP.this.SSL_MIN_RESET_COUNT));
/*       */         }
/*       */       } 
/*       */     }
/*       */     
/*       */     private void defaultMQSCO() {
/* 14560 */       this.mqsco = RemoteFAP.this.env.newMQSCO();
/*       */       
/* 14562 */       if (RemoteFAP.this.clientCfg.getBoolValue(Configuration.ENV_MQSSLFIPS)) {
/* 14563 */         this.mqsco.setFipsRequired(1);
/*       */       }
/*       */ 
/*       */       
/* 14567 */       int mqSslReset = RemoteFAP.this.clientCfg.getIntValue(Configuration.ENV_MQSSLRESET);
/*       */       
/* 14569 */       if (mqSslReset > 0) {
/* 14570 */         this.mqsco.setKeyResetCount(mqSslReset);
/*       */       }
/*       */     }
/*       */     
/*       */     private void setupReconnectOptions() {
/* 14575 */       if (Trace.isOn) {
/* 14576 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "setupReconnectOptions()");
/*       */       }
/*       */       
/* 14579 */       if (this.jmqiConnectOptions.getRebalancingListener() != null) {
/* 14580 */         this.connectionOptions.setOptions(this.connectionOptions.getOptions() | 0x1000000);
/*       */       
/*       */       }
/* 14583 */       else if ((this.connectionOptions.getOptions() & 0x7000000) == 0) {
/* 14584 */         this.reconnectAsDef = true;
/*       */ 
/*       */         
/* 14587 */         String rcnConfig = RemoteFAP.this.env.getConfiguration().getStringValue(Configuration.CHANNEL_RECON);
/* 14588 */         int reconnectDefault = 0;
/* 14589 */         switch (rcnConfig.toUpperCase()) {
/*       */           case "YES":
/* 14591 */             reconnectDefault = 16777216;
/*       */             break;
/*       */           case "QMGR":
/* 14594 */             reconnectDefault = 67108864;
/*       */             break;
/*       */           case "DISABLED":
/* 14597 */             reconnectDefault = 33554432;
/*       */             break;
/*       */         } 
/*       */         
/* 14601 */         this.connectionOptions.setOptions(this.connectionOptions.getOptions() | reconnectDefault);
/*       */       }
/* 14603 */       else if ((this.connectionOptions.getOptions() & 0x2000000) != 0) {
/* 14604 */         this.connectionOptions.setOptions(this.connectionOptions.getOptions() & 0xF8FFFFFF);
/*       */       } 
/*       */       
/* 14607 */       if (Trace.isOn) {
/* 14608 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "setupReconnectOptions()");
/*       */       }
/*       */     }
/*       */     
/*       */     private void setupRebalanceOptions(Hconn parentHconn) {
/* 14613 */       if (Trace.isOn) {
/* 14614 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "setupRebalanceOptions(Hconn)", new Object[] { parentHconn });
/*       */       }
/*       */ 
/*       */       
/* 14618 */       if (this.fapLevel >= 14 && this.remoteSession.getParentConnection().isGenerateConntagCapable()) {
/* 14619 */         if (this.reconnecting) {
/* 14620 */           byte[] applicableConnTag = (parentHconn != null) ? parentHconn.getConnTag() : this.remoteHconn.getConnTag();
/* 14621 */           this.connectionOptions.setConnTag(applicableConnTag);
/* 14622 */           this.options |= 0x8;
/*       */         
/*       */         }
/* 14625 */         else if (connectionOptionsConntagUnset()) {
/* 14626 */           if (parentHconn == null) {
/* 14627 */             this.connectionOptions.setOptions(this.connectionOptions.getOptions() | 0x200000);
/*       */           } else {
/*       */             
/* 14630 */             this.connectionOptions.setConnTag(parentHconn.getConnTag());
/* 14631 */             this.options |= 0x8;
/*       */           } 
/*       */         } else {
/*       */           
/* 14635 */           this.options |= 0x8;
/*       */         } 
/*       */       }
/*       */ 
/*       */       
/* 14640 */       if (Trace.isOn) {
/* 14641 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "setupRebalanceOptions(Hconn)");
/*       */       }
/*       */     }
/*       */     
/*       */     private boolean connectionOptionsConntagUnset() {
/* 14646 */       if (Trace.isOn) {
/* 14647 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "connectionOptionsConntagUnset()");
/*       */       }
/* 14649 */       boolean result = true;
/* 14650 */       if (this.connectionOptions.getConnTag() != null) {
/* 14651 */         result = Arrays.equals(this.connectionOptions.getConnTag(), MQConstants.MQCT_NONE);
/*       */       }
/* 14653 */       if (Trace.isOn) {
/* 14654 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "connectionOptionsConntagUnset()", Boolean.valueOf(result));
/*       */       }
/* 14656 */       return result;
/*       */     }
/*       */     
/*       */     private void validateMQCD() throws JmqiException {
/* 14660 */       if (Trace.isOn) {
/* 14661 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "validateMQCD()");
/*       */       }
/*       */       
/* 14664 */       if (this.mqcd != null && this.mqcd.getChannelType() != 6) {
/* 14665 */         JmqiException traceRet = new JmqiException(RemoteFAP.this.env, -1, null, 2, 2277, null);
/*       */         
/* 14667 */         if (Trace.isOn) {
/* 14668 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "validateMQCD()", (Throwable)traceRet, 5);
/*       */         }
/*       */         
/* 14671 */         throw traceRet;
/*       */       } 
/*       */       
/* 14674 */       if (Trace.isOn) {
/* 14675 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "validateMQCD()");
/*       */       }
/*       */     }
/*       */     
/*       */     private void validateConnectOptions() throws JmqiException {
/* 14680 */       if (Trace.isOn) {
/* 14681 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "validateConnectOptions()");
/*       */       }
/*       */ 
/*       */       
/* 14685 */       if ((this.connectOptsInteger & 0x40000) != 0 && (this.connectOptsInteger & 0x10000) != 0) {
/* 14686 */         JmqiException traceRet = new JmqiException(RemoteFAP.this.env, -1, null, 2, 2046, null);
/*       */         
/* 14688 */         if (Trace.isOn) {
/* 14689 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "validateConnectOptions()", (Throwable)traceRet, 1);
/*       */         }
/*       */         
/* 14692 */         throw traceRet;
/*       */       } 
/*       */ 
/*       */       
/* 14696 */       if ((this.connectOptsInteger & 0x80000) != 0 && (this.connectOptsInteger & 0x100000) != 0) {
/* 14697 */         JmqiException traceRet = new JmqiException(RemoteFAP.this.env, -1, null, 2, 2046, null);
/*       */         
/* 14699 */         if (Trace.isOn) {
/* 14700 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "validateConnectOptions()", (Throwable)traceRet, 2);
/*       */         }
/*       */         
/* 14703 */         throw traceRet;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 14709 */       if (((this.connectOptsInteger & 0x80000) != 0 || (this.connectOptsInteger & 0x100000) != 0) && 
/* 14710 */         this.connectionOptions.getClientConn() == null) {
/* 14711 */         JmqiException traceRet = new JmqiException(RemoteFAP.this.env, -1, null, 2, 2277, null);
/*       */         
/* 14713 */         if (Trace.isOn) {
/* 14714 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "validateConnectOptions()", (Throwable)traceRet, 3);
/*       */         }
/*       */         
/* 14717 */         throw traceRet;
/*       */       } 
/*       */ 
/*       */       
/* 14721 */       if (Trace.isOn) {
/* 14722 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "validateConnectOptions()");
/*       */       }
/*       */     }
/*       */     
/*       */     private void initialiseForJMSSession(RemoteHconn parentHconn) throws JmqiException {
/* 14727 */       if (Trace.isOn) {
/* 14728 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "initialiseForJMSSession(RemoteHconn)", new Object[] { parentHconn });
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 14740 */       RemoteHconn WMQRemoteHconn = parentHconn;
/* 14741 */       RemoteConnection WMQRemoteConnection = WMQRemoteHconn.getParentConnection();
/* 14742 */       MQCD parentMqcd = WMQRemoteConnection.getClientConn();
/* 14743 */       if (parentMqcd != null) {
/* 14744 */         if (this.connectionOptions != null) {
/*       */           
/* 14746 */           this.connectOptsInteger = this.connectionOptions.getOptions();
/*       */         } else {
/*       */           
/* 14749 */           this.connectionOptions = RemoteFAP.this.env.newMQCNO();
/*       */         } 
/* 14751 */         this.connectOptsInteger |= 0x100000;
/* 14752 */         this.connectionOptions.setOptions(this.connectOptsInteger);
/* 14753 */         this.connectionOptions.setClientConn(parentMqcd);
/* 14754 */         this.mqsco = WMQRemoteHconn.getSession().getMqsco();
/*       */       } 
/*       */       
/* 14757 */       if (Trace.isOn) {
/* 14758 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector", "initialiseForJMSSession(RemoteHconn)");
/*       */       }
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int getReconnectCycle() {
/* 14768 */     int reconnectCycle = RemoteReconnectThread.getReconnectCycle();
/*       */     
/* 14770 */     if (Trace.isOn) {
/* 14771 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "getReconnectCycle()", "getter", 
/* 14772 */           Integer.valueOf(reconnectCycle));
/*       */     }
/*       */     
/* 14775 */     return reconnectCycle;
/*       */   }
/*       */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\api\RemoteFAP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */