/*      */ package com.ibm.msg.client.wmq.internal;
/*      */ 
/*      */ import com.ibm.mq.MQException;
/*      */ import com.ibm.mq.constants.QmgrAdvancedCapability;
/*      */ import com.ibm.mq.exits.MQCD;
/*      */ import com.ibm.mq.exits.MQCSP;
/*      */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*      */ import com.ibm.mq.jmqi.JmqiException;
/*      */ import com.ibm.mq.jmqi.JmqiMQ;
/*      */ import com.ibm.mq.jmqi.JmqiReconnectionListener;
/*      */ import com.ibm.mq.jmqi.JmqiUtils;
/*      */ import com.ibm.mq.jmqi.MQBNO;
/*      */ import com.ibm.mq.jmqi.MQCBC;
/*      */ import com.ibm.mq.jmqi.MQCBD;
/*      */ import com.ibm.mq.jmqi.MQCNO;
/*      */ import com.ibm.mq.jmqi.MQConsumer;
/*      */ import com.ibm.mq.jmqi.MQGMO;
/*      */ import com.ibm.mq.jmqi.MQMD;
/*      */ import com.ibm.mq.jmqi.MQOD;
/*      */ import com.ibm.mq.jmqi.MQSCO;
/*      */ import com.ibm.mq.jmqi.RebalancingListener;
/*      */ import com.ibm.mq.jmqi.RebalancingRequest;
/*      */ import com.ibm.mq.jmqi.handles.Hconn;
/*      */ import com.ibm.mq.jmqi.handles.Phconn;
/*      */ import com.ibm.mq.jmqi.handles.Phobj;
/*      */ import com.ibm.mq.jmqi.handles.Pint;
/*      */ import com.ibm.mq.jmqi.internal.Configuration;
/*      */ import com.ibm.mq.jmqi.remote.api.RemoteHconn;
/*      */ import com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection;
/*      */ import com.ibm.mq.jmqi.system.JmqiConnectOptions;
/*      */ import com.ibm.mq.jmqi.system.JmqiSP;
/*      */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*      */ import com.ibm.msg.client.commonservices.Log.Log;
/*      */ import com.ibm.msg.client.commonservices.cssystem.CSSystem;
/*      */ import com.ibm.msg.client.commonservices.cssystem.WASSupport;
/*      */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*      */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*      */ import com.ibm.msg.client.commonservices.trace.TableBuilder;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*      */ import com.ibm.msg.client.jms.JmsReadablePropertyContext;
/*      */ import com.ibm.msg.client.jms.internal.JmsConnectionImpl;
/*      */ import com.ibm.msg.client.jms.internal.JmsTls;
/*      */ import com.ibm.msg.client.provider.ProviderAsyncConsumeThreadDetector;
/*      */ import com.ibm.msg.client.provider.ProviderConnection;
/*      */ import com.ibm.msg.client.provider.ProviderConnectionBrowser;
/*      */ import com.ibm.msg.client.provider.ProviderDestination;
/*      */ import com.ibm.msg.client.provider.ProviderExceptionListener;
/*      */ import com.ibm.msg.client.provider.ProviderMessageReferenceHandler;
/*      */ import com.ibm.msg.client.provider.ProviderMetaData;
/*      */ import com.ibm.msg.client.provider.ProviderOnMessageController;
/*      */ import com.ibm.msg.client.provider.ProviderSession;
/*      */ import com.ibm.msg.client.wmq.common.WMQThreadLocalStorage;
/*      */ import com.ibm.msg.client.wmq.common.internal.Reason;
/*      */ import com.ibm.msg.client.wmq.common.internal.StringableProperty;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQCommonConnection;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQConsumerOwner;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQDestination;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQHobjCache;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQMetaData;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQPropertyContext;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQTemporaryQueue;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQTemporaryTopic;
/*      */ import java.io.PrintWriter;
/*      */ import java.lang.reflect.Method;
/*      */ import java.net.MalformedURLException;
/*      */ import java.net.URL;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.util.AbstractQueue;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.Enumeration;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.IdentityHashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.UUID;
/*      */ import java.util.Vector;
/*      */ import javax.jms.IllegalStateException;
/*      */ import javax.jms.JMSException;
/*      */ import javax.net.ssl.SSLSocketFactory;
/*      */ import org.json.JSONObject;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class WMQConnection
/*      */   extends WMQPropertyContext
/*      */   implements ProviderConnection, ProviderOnMessageController, WMQCommonConnection, WMQConsumerOwner, MQConsumer, JmqiReconnectionListener, ProviderAsyncConsumeThreadDetector
/*      */ {
/*      */   private static final long serialVersionUID = -2703683054810854326L;
/*      */   
/*      */   static {
/*  139 */     if (Trace.isOn) {
/*  140 */       Trace.data("com.ibm.msg.client.wmq.internal.WMQConnection", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq/src/com/ibm/msg/client/wmq/internal/WMQConnection.java");
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
/*  151 */   private static String persistenceFromMDProperty = "com.ibm.mq.jms.tuning.usePersistenceFromMD";
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq/src/com/ibm/msg/client/wmq/internal/WMQConnection.java";
/*      */ 
/*      */   
/*  158 */   static String[] connectionModeMappings = new String[] { "Bindings", "Client", "DirectTCPIP", "", "DirectHTTP", "ClientUnmanged", "", "", "ClientThenBindings" }; public static final int SHARING_CONVERSATION_UNDEFINED = -1; public static final int FAP_LEVEL_UNDEFINED = -1; private transient MQCNO connectOptions; private transient ProviderExceptionListener exceptionListener; private transient WMQConsumerOwnerShadow helper; private transient JmqiConnectOptions jmqiConnectOptions;
/*      */   private transient JmqiEnvironment jmqiEnvironment;
/*      */   private transient JmqiMQ jmqiMq;
/*      */   private transient JmqiSP jmqiSp;
/*      */   private int jmqiCompId;
/*      */   private transient WMQMetaData metaData;
/*      */   private boolean persistenceFromMD;
/*      */   private transient Phconn phconn;
/*      */   private String queueManagerName;
/*      */   
/*      */   static {
/*  169 */     if (Trace.isOn) {
/*  170 */       Trace.entry("com.ibm.msg.client.wmq.internal.WMQConnection", "static()");
/*      */     }
/*      */     
/*  173 */     PropertyStore.register(persistenceFromMDProperty, null);
/*      */     
/*  175 */     if (Trace.isOn) {
/*  176 */       Trace.exit("com.ibm.msg.client.wmq.internal.WMQConnection", "static()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Enumeration<String> tokenise(String string) {
/*  187 */     if (Trace.isOn) {
/*  188 */       Trace.entry("com.ibm.msg.client.wmq.internal.WMQConnection", "tokenise(String)", new Object[] { string });
/*      */     }
/*  190 */     Vector<String> vector = new Vector<>();
/*  191 */     boolean inQuotes = false;
/*  192 */     boolean firstQuote = false;
/*  193 */     char quote = '\'';
/*  194 */     char comma = ',';
/*      */     
/*  196 */     StringBuffer buffer = new StringBuffer();
/*      */     
/*  198 */     for (int i = 0; i < string.length(); i++) {
/*  199 */       char thisChar = string.charAt(i);
/*  200 */       if (inQuotes) {
/*  201 */         if (firstQuote) {
/*  202 */           if (thisChar == '\'') {
/*  203 */             buffer.append(thisChar);
/*      */           }
/*  205 */           else if (thisChar == ',') {
/*  206 */             vector.add(new String(buffer));
/*  207 */             buffer = new StringBuffer();
/*  208 */             inQuotes = false;
/*      */           } else {
/*      */             
/*  211 */             buffer.append(thisChar);
/*  212 */             inQuotes = false;
/*      */           } 
/*  214 */           firstQuote = false;
/*      */         
/*      */         }
/*  217 */         else if (thisChar == '\'') {
/*  218 */           firstQuote = true;
/*      */         } else {
/*      */           
/*  221 */           buffer.append(thisChar);
/*      */         
/*      */         }
/*      */ 
/*      */       
/*      */       }
/*  227 */       else if (thisChar == ',') {
/*  228 */         vector.add(new String(buffer));
/*  229 */         buffer = new StringBuffer();
/*      */       }
/*  231 */       else if (thisChar == '\'') {
/*  232 */         inQuotes = true;
/*      */       } else {
/*      */         
/*  235 */         buffer.append(thisChar);
/*      */       } 
/*      */     } 
/*      */     
/*  239 */     vector.add(new String(buffer));
/*      */     
/*  241 */     Enumeration<String> traceRet1 = vector.elements();
/*  242 */     if (Trace.isOn) {
/*  243 */       Trace.exit("com.ibm.msg.client.wmq.internal.WMQConnection", "tokenise(String)", traceRet1);
/*      */     }
/*  245 */     return traceRet1;
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
/*  276 */   private long temporaryDestinationNumber = 1L;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String temporaryModelQ;
/*      */ 
/*      */ 
/*      */   
/*  285 */   protected Map<WMQDestination, Phobj> temporaryQueuePhobjs = Collections.synchronizedMap(new IdentityHashMap<>());
/*      */   protected String tempQPrefix;
/*      */   private boolean propertyRefreshNeeded;
/*      */   private Object UOWManagerObject;
/*      */   private transient Method getLocalUOWIdMethod;
/*      */   private boolean setupReflectionAttempted;
/*      */   private void updateQmNameEtc() throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "updateQmNameEtc()");  try { Hconn myHconn = getHconn(); String qmName = myHconn.getName(); if (qmName != null) qmName = qmName.trim();  setStringProperty("XMSC_WMQ_RESOLVED_QUEUE_MANAGER", qmName); setStringProperty("XMSC_WMQ_RESOLVED_QUEUE_MANAGER_ID", myHconn.getUid()); setStringProperty("XMSC_WMQ_CONNECTION_ID", myHconn.getConnectionIdAsString()); String qsgName = myHconn.getQsgName(); if (qsgName != null && qsgName.equals(this.queueManagerName)) setStringProperty("XMSC_WMQ_RESOLVED_QUEUE_SHARING_GROUP_NAME", qsgName);  byte[] connTag = myHconn.getConnTag(); if (connTag != null) setStringProperty("XMSC_WMQ_RESOLVED_CONNECTION_TAG", new String(connTag));  this.propertyRefreshNeeded = false; if (myHconn instanceof RemoteHconn) try { RemoteHconn rHconn = (RemoteHconn)myHconn; RemoteTCPConnection connection = (RemoteTCPConnection)rHconn.getParentConnection(); setStringProperty("XMSC_WMQ_HOST_NAME", connection.getSocketIpAddress()); setIntProperty("XMSC_WMQ_PORT", connection.getSocketIpPort()); } catch (Exception e) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "updateQmNameEtc()", e, 0);  }   } catch (JmqiException e) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "updateQmNameEtc()", (Throwable)e, 1);  HashMap<String, String> inserts = new HashMap<>(); inserts.put("XMSC_INSERT_COMP_CODE", Integer.toString(e.getCompCode())); inserts.put("XMSC_INSERT_REASON", Integer.toString(e.getReason())); inserts.put("XMSC_INSERT_METHOD", "updateQmNameEtc()"); String exceptionMessage = "JMSWMQ2019"; JMSException je = Reason.createException(exceptionMessage, inserts, e.getReason(), e.getCompCode(), this.jmqiEnvironment); je.setLinkedException((Exception)e); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "updateQmNameEtc()", (Throwable)je, 3);  throw je; }  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "updateQmNameEtc()");  }
/*      */   private String accessTemporaryQueue(Phobj phobj) throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "accessTemporaryQueue(Phobj)", new Object[] { phobj });  if (this.tempQPrefix == null) this.tempQPrefix = getStringProperty("XMSC_WMQ_TEMP_Q_PREFIX");  if (this.temporaryModelQ == null) this.temporaryModelQ = getStringProperty("XMSC_WMQ_TEMPORARY_MODEL");  String queueName = null; MQOD mqod = this.jmqiEnvironment.newMQOD(); int openOptions = 48; if (this.temporaryModelQ != null && this.temporaryModelQ.length() > 0) mqod.setObjectName(this.temporaryModelQ);  if (this.tempQPrefix != null && this.tempQPrefix.length() > 0) mqod.setDynamicQName(this.tempQPrefix);  int fiq = getIntProperty("failIfQuiesce"); if (fiq == 1) { if (Trace.isOn) Trace.data(this, "Open fail-if-quiesce = yes", null);  openOptions |= 0x2000; } else { if (Trace.isOn) Trace.data(this, "Open fail-if-quiesce = no", null);  openOptions &= 0xFFFFDFFF; }  if (Trace.isOn) Trace.traceData(this, "accessTemporaryQueue", "queue = " + mqod.getObjectName() + "\ndynamic queue name = " + mqod.getDynamicQName() + "\nalternate user id = " + mqod.getAlternateUserId() + "\noptions = " + openOptions, null);  Pint pCompCode = new Pint(this.jmqiEnvironment); Pint pReason = new Pint(this.jmqiEnvironment); boolean didSuspend = suspendAsyncService(); this.jmqiMq.MQOPEN(this.phconn.getHconn(), mqod, openOptions, phobj, pCompCode, pReason); if (didSuspend) resumeAsyncService();  if (pReason.x != 0 || pCompCode.x != 0) { if (Reason.isImpossibleReason(pReason.x, pCompCode.x, this.jmqiSp)) { HashMap<String, Object> info = new HashMap<>(); info.put("pHConn", this.phconn); info.put("MQOD", mqod); info.put("openOptions", Integer.valueOf(openOptions)); info.put("reason", pReason); info.put("compcode", pCompCode); info.put("hconn", this.phconn.getHconn()); Trace.ffst(this, "accessTemporaryQueue", "XN001003", info, JMSException.class); }  if (Reason.isConnectionBroken(pReason.x)) { JMSException e = Reason.createException("JMSWMQ1107", null, pReason.x, pCompCode.x, this.jmqiEnvironment); driveExceptionListener(e, true); }  HashMap<String, String> inserts = new HashMap<>(); inserts.put("XMSC_DESTINATION_NAME", this.temporaryModelQ); JMSException je = Reason.createException("JMSWMQ2008", inserts, pReason.x, pCompCode.x, this.jmqiEnvironment); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "accessTemporaryQueue(Phobj)", (Throwable)je);  throw je; }  queueName = mqod.getObjectName().trim(); if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "accessTemporaryQueue(Phobj)", queueName);  return queueName; }
/*      */   public void addAsyncConsumer() throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "addAsyncConsumer()");  this.helper.addAsyncConsumer(); if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "addAsyncConsumer()");  }
/*      */   public void close() throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "close()");  Map<WMQDestination, Phobj> temporaryQueuesClone = new HashMap<>(this.temporaryQueuePhobjs); for (Map.Entry<WMQDestination, Phobj> temporaryQueueEntry : temporaryQueuesClone.entrySet()) { WMQDestination temporaryQueue = temporaryQueueEntry.getKey(); Phobj temporaryQueuePhobj = temporaryQueueEntry.getValue(); if (!isPermanentDynamic(temporaryQueuePhobj, temporaryQueue)) { try { deleteTemporaryDestination(temporaryQueue); if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "close()", "deleted", temporaryQueue.toString());  } catch (JMSException je) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "close()", (Throwable)je);  }  continue; }  if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "close()", "queue was permanent dynamic, so not deleted", temporaryQueue.toString());  }  try { Pint cc = this.jmqiEnvironment.newPint(); Pint rc = this.jmqiEnvironment.newPint(); this.jmqiMq.MQDISC(this.phconn, cc, rc); if (rc.x != 0) { if (Reason.isImpossibleReason(rc.x, cc.x, this.jmqiSp)) { HashMap<String, Object> info = new HashMap<>(); info.put("rc", rc); info.put("cc", cc); info.put("queuemanager", this.queueManagerName); info.put("hconn", this.phconn.getHconn()); Trace.ffst(this, "close()", "XN001002", info, JMSException.class); }  if (!Reason.isConnectionBroken(rc.x)) { HashMap<String, String> inserts = new HashMap<>(); inserts.put("XMSC_INSERT_QUEUE_MANAGER", this.queueManagerName); inserts.put("XMSC_WMQ_CONNECTION_MODE", getStringProperty("XMSC_WMQ_CONNECTION_MODE")); inserts.put("XMSC_WMQ_HOST_NAME", getStringProperty("XMSC_WMQ_CONNECTION_NAME_LIST_INT")); JMSException je = Reason.createException("JMSWMQ0019", inserts, rc.x, cc.x, this.jmqiEnvironment); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "close()", (Throwable)je);  throw je; }  }  } finally { if (Trace.isOn) Trace.finallyBlock(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "close()");  Trace.deRegisterFFSTObject(this); }  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "close()");  }
/*      */   private boolean isPermanentDynamic(Phobj temporaryQueuePhobj, WMQDestination temporaryQueue) throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "isPermanentDynamic(Phobj, WMQDestination)", new Object[] { temporaryQueuePhobj, temporaryQueue });  boolean isPermanentDynamic = false; if (temporaryQueuePhobj != null) { int[] pSelectors = { 7 }; int[] pIntAttrs = new int[1]; byte[] pCharAttrs = new byte[0]; Pint cc = this.jmqiEnvironment.newPint(); Pint rc = this.jmqiEnvironment.newPint(); this.jmqiMq.MQINQ(this.phconn.getHconn(), temporaryQueuePhobj.getHobj(), pSelectors.length, pSelectors, pIntAttrs.length, pIntAttrs, pCharAttrs.length, pCharAttrs, cc, rc); if (rc.x != 0) checkJmqiCallSuccess("MQINQ", "JMSWMQ0030", temporaryQueue.getName(), cc, rc, "XN00S009");  isPermanentDynamic = (pIntAttrs[0] == 2); }  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "isPermanentDynamic(WMQDestination)", Boolean.valueOf(isPermanentDynamic));  return isPermanentDynamic; }
/*      */   public void consumer(Hconn hconn, MQMD mqmd, MQGMO mqgmo, ByteBuffer pBuffer, MQCBC mqcbc) { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "consumer(Hconn,MQMD,MQGMO,ByteBuffer,MQCBC)", new Object[] { hconn, mqmd, mqgmo, pBuffer, mqcbc });  int callType = mqcbc.getCallType(); if (callType == 6) { HashMap<String, Object> data = new HashMap<>(); data.put("callType", Integer.valueOf(callType)); Trace.ffst(this, "consume(Hconn,MQMD,MQGMO,ByteNuffer,MQCBC)", "XN001004", data, null); } else if (callType == 7) { HashMap<String, Object> data = new HashMap<>(); data.put("callType", Integer.valueOf(callType)); Trace.ffst(this, "consume(Hconn,MQMD,MQGMO,ByteNuffer,MQCBC)", "XN001005", data, null); } else if (callType == 5) { int rc = mqcbc.getReason(); int cc = mqcbc.getCompCode(); if (rc == 2033) { if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "consumer(Hconn,MQMD,MQGMO,ByteBuffer,MQCBC)", 1);  return; }  if (Reason.isImpossibleReason(rc, cc, null)) { HashMap<String, Object> info = new HashMap<>(); info.put("connection", this); info.put("reason", Integer.valueOf(rc)); info.put("compcode", Integer.valueOf(cc)); info.put("hconn", hconn); Trace.ffst("WMQConnection", "consumer()", "XN001006", info, null); }  String wmqMsg = NLSServices.getMessage("JMSWMQ2014", new Object[] { Integer.toString(cc), Integer.toString(rc) }); MQException wmqex = new MQException(wmqMsg, "JMSWMQ2014", rc, cc); JMSException e = Reason.createException("JMSWMQ1107", null, rc, cc, this.jmqiEnvironment); e.setLinkedException((Exception)wmqex); try { driveExceptionListener(e, Reason.isConnectionBroken(rc)); } catch (JMSException e2) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "consumer(Hconn,MQMD,MQGMO,ByteBuffer,MQCBC)", (Throwable)e2);  HashMap<String, Object> info = new HashMap<>(); info.put("exception1", e); info.put("exception2", e2); Trace.ffst(this, "consume(Hconn,MQMD,MQGMO,ByteNuffer,MQCBC)", "XN001007", info, null); }  }  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "consumer(Hconn,MQMD,MQGMO,ByteBuffer,MQCBC)", 2);  }
/*      */   public ProviderConnectionBrowser createConnectionBrowser(ProviderDestination destination, String selector, ProviderMessageReferenceHandler msgRefHandler, int quantityHint) throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "createConnectionBrowser(ProviderDestination,String,ProviderMessageReferenceHandler,int)", new Object[] { destination, selector, msgRefHandler, Integer.valueOf(quantityHint) });  WMQConnectionBrowser browser = new WMQConnectionBrowser(this, destination, null, selector, false, false, msgRefHandler, quantityHint, false); if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "createConnectionBrowser(ProviderDestination,String,ProviderMessageReferenceHandler,int)", browser);  return browser; }
/*      */   public ProviderConnectionBrowser createDurableConnectionBrowser(ProviderDestination destination, String clientid, String subName, String selector, ProviderMessageReferenceHandler msgRefHandler, int quantityHint, boolean noLocal) throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "createDurableConnectionBrowser(ProviderDestination,String,String,String,ProviderMessageReferenceHandler,int,boolean)", new Object[] { destination, clientid, subName, selector, msgRefHandler, Integer.valueOf(quantityHint), Boolean.valueOf(noLocal) });  String resolvedQMName = getStringProperty("XMSC_WMQ_RESOLVED_QUEUE_MANAGER"); String streamName = getStringProperty("XMSC_WMQ_BROKER_PUBQ"); String fqSubName = WMQSession.createFullSubscriptionName(clientid, subName, resolvedQMName, streamName, true, false); WMQConnectionBrowser browser = new WMQConnectionBrowser(this, destination, fqSubName, selector, false, true, msgRefHandler, quantityHint, noLocal); if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "createDurableConnectionBrowser(ProviderDestination,String,String,String,ProviderMessageReferenceHandler,int,boolean)", browser);  return browser; }
/*      */   public ProviderSession createSession(JmsPropertyContext properties) throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "createSession(JmsPropertyContext)", new Object[] { properties });  throwIfClosed(); int ackMode = properties.getIntProperty("XMSC_ACKNOWLEDGE_MODE"); WMQSession session = new WMQSession(this, ackMode, properties); if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "createSession(JmsPropertyContext)", session);  return session; }
/*      */   public synchronized WMQDestination createTemporaryDestination(int destType, JmsPropertyContext propContext) throws JMSException { WMQTemporaryTopic wMQTemporaryTopic; if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "createTemporaryDestination(int,JmsPropertyContext)", new Object[] { Integer.valueOf(destType), propContext });  WMQDestination dest = null; if (destType == 1) { Phobj phobj = this.jmqiEnvironment.newPhobj(); String name = accessTemporaryQueue(phobj); String qmName = ""; try { qmName = getHconn().getName().trim(); } catch (JmqiException e) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "createTemporaryDestination(int,JmsPropertyContext)", (Throwable)e);  if (Trace.isOn) Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "createTemporaryDestination(int,JmsPropertyContext)", (Throwable)e);  HashMap<String, Object> info = new HashMap<>(); info.put("reason", Integer.valueOf(e.getReason())); info.put("compcode", Integer.valueOf(e.getCompCode())); info.put("hconn", this.phconn.getHconn()); info.put("XMSC_INSERT_QUEUE_MANAGER", this.queueManagerName); info.put("XMSC_WMQ_CONNECTION_MODE", connectionModeMappings[getIntProperty("XMSC_WMQ_CONNECTION_MODE")]); Trace.ffst(this, "<init>", "XN001011", info, null); HashMap<String, String> inserts = new HashMap<>(); inserts.put("XMSC_INSERT_COMP_CODE", Integer.toString(e.getCompCode())); inserts.put("XMSC_INSERT_REASON", Integer.toString(e.getReason())); inserts.put("XMSC_INSERT_METHOD", "createTemporaryDestination(int,JmsPropertyContext)"); String exceptionMessage = "JMSWMQ2019"; JMSException je = Reason.createException(exceptionMessage, inserts, e.getReason(), e.getCompCode(), this.jmqiEnvironment); je.setLinkedException((Exception)e); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "createTemporaryDestination(int,JmsPropertyContext)", (Throwable)je);  if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "createTemporaryDestination(int,JmsPropertyContext)", (Throwable)je);  throw je; }  WMQTemporaryQueue wMQTemporaryQueue = new WMQTemporaryQueue("queue://" + qmName + "/" + name, this, propContext); this.temporaryQueuePhobjs.put(wMQTemporaryQueue, phobj); } else if (destType == 2) { boolean addPrefixDelimiter = true; String tempTopicPrefix = getStringProperty("XMSC_WMQ_TEMP_TOPIC_PREFIX"); if (tempTopicPrefix == null || tempTopicPrefix.equals("")) { addPrefixDelimiter = false; if (tempTopicPrefix == null) tempTopicPrefix = "";  }  StringBuffer namesb = new StringBuffer("TEMP/"); namesb.append(tempTopicPrefix); if (addPrefixDelimiter) namesb.append('/');  namesb.append(UUID.randomUUID().toString().replace("-", "")); wMQTemporaryTopic = new WMQTemporaryTopic(namesb.toString(), this, propContext); }  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "createTemporaryDestination(int,JmsPropertyContext)", wMQTemporaryTopic);  return (WMQDestination)wMQTemporaryTopic; } public void decrementCloseCounter() { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "decrementCloseCounter()");  this.helper.decrementCloseCounter(); if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "decrementCloseCounter()");  } public void deleteTemporaryDestination(WMQDestination dest) throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "deleteTemporaryDestination(WMQDestination)", new Object[] { dest });  Phobj phobj = this.temporaryQueuePhobjs.get(dest); if (phobj == null) { if (Trace.isOn) Trace.data(this, "deleteTemporaryDestination(WMQDestination dest)", "Failed to find temporary queue in Hashtable", dest);  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "deleteTemporaryDestination(WMQDestination)", 1);  return; }  int options = 2; Pint pCompCode = this.jmqiEnvironment.newPint(); Pint pReason = this.jmqiEnvironment.newPint(); this.jmqiMq.MQCLOSE(this.phconn.getHconn(), phobj, options, pCompCode, pReason); if (pReason.x != 0 || pCompCode.x != 0) { if (Reason.isImpossibleReason(pReason.x, pCompCode.x, this.jmqiSp)) { HashMap<String, Object> info = new HashMap<>(); info.put("pHConn", this.phconn); info.put("pHobj", phobj); info.put("closeOptions", Integer.valueOf(options)); info.put("reason", pReason); info.put("compcode", pCompCode); Trace.ffst(this, "deleteTemporaryDestination", "XN001008", info, JMSException.class); }  if (Reason.isConnectionBroken(pReason.x)) { JMSException e = Reason.createException("JMSWMQ1107", null, pReason.x, pCompCode.x, this.jmqiEnvironment); driveExceptionListener(e, true); }  HashMap<String, String> inserts = new HashMap<>(); inserts.put("XMSC_DESTINATION_NAME", dest.getName()); JMSException je = Reason.createException("JMSWMQ2000", inserts, pReason.x, pCompCode.x, this.jmqiEnvironment); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "deleteTemporaryDestination(WMQDestination)", (Throwable)je);  throw je; }  this.temporaryQueuePhobjs.remove(dest); if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "deleteTemporaryDestination(WMQDestination)", 2);  } public void driveExceptionListener(JMSException e, boolean isConnectionBroken) throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "driveExceptionListener(JMSException,boolean)", new Object[] { e, Boolean.valueOf(isConnectionBroken) });  JmsTls tls = JmsTls.getInstance(); if (this.exceptionListener != null) { this.exceptionListener.onException(e, isConnectionBroken); } else if (isConnectionBroken) { if (tls.inFinalizer()) { if (Trace.isOn) Trace.traceData(this, "driveExceptionListener(JMSException, boolean)", "No listener  - Connection Broken but in finalizer, so not logging", e);  } else { if (Trace.isOn) Trace.traceData(this, "driveExceptionListener(JMSException, boolean)", "No listener  - Connection Broken, so logging Exception instead", e);  HashMap<String, JMSException> inserts = new HashMap<>(); inserts.put("XMSC_INSERT_EXCEPTION", e); Log.log(this, "driveExceptionListener(JMSException, boolean)", "JMSWMQ2018", inserts); }  } else if (Trace.isOn) { Trace.traceData(this, "driveExceptionListener(JMSException, boolean)", "No listener  - Not a Connection Broken error, so not logging", e); }  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "driveExceptionListener(JMSException,boolean)");  } public int getAckMode() { if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getAckMode()", "getter", Integer.valueOf(1));  return 1; } public int getCmdLevel() throws JmqiException { Hconn hconn = this.phconn.getHconn(); int traceRet1 = hconn.getCmdLevel(); if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getCmdLevel()", "getter", Integer.valueOf(traceRet1));  return traceRet1; } public int getSharingConversations() throws JmqiException { Hconn hconn = this.phconn.getHconn(); int traceRet1 = hconn.getNumberOfSharingConversations(); if (traceRet1 == -1) traceRet1 = -1;  if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getSharingConversations()", "getter", Integer.valueOf(traceRet1));  return traceRet1; } public int getFapLevel() throws JmqiException { Hconn hconn = this.phconn.getHconn(); int traceRet1 = hconn.getFapLevel(); if (traceRet1 == -1) traceRet1 = -1;  if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getFapLevel()", "getter", Integer.valueOf(traceRet1));  return traceRet1; } public WMQCommonConnection getConnection() { if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getConnection()", "getter", this);  return this; } public MQCNO getConnectOptions() throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getConnectOptions()");  MQCNO connOptions = this.jmqiEnvironment.newMQCNO(); connOptions.setVersion(5); MQSCO sslConfig = this.jmqiEnvironment.newMQSCO(); connOptions.setSslConfig(sslConfig); boolean sslFipsRequired = getBooleanProperty("XMSC_WMQ_SSL_FIPS_REQUIRED"); int value = sslFipsRequired ? 1 : 0; sslConfig.setFipsRequired(value); String sslCryptoHardware = getStringProperty("XMSC_WMQ_SSL_CRYPTO_HW"); sslConfig.setCryptoHardware(sslCryptoHardware); String sslKeyRepository = getStringProperty("XMSC_WMQ_SSL_KEY_REPOSITORY"); sslConfig.setKeyRepository(sslKeyRepository); int sslKeyResetCount = getIntProperty("XMSC_WMQ_SSL_KEY_RESETCOUNT"); sslConfig.setKeyResetCount(sslKeyResetCount); int options = 0; try { if (this.jmqiMq.isSharedHandlesSupported()) options |= 0x40;  } catch (JmqiException e) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getConnectOptions()", (Throwable)e);  HashMap<String, Object> info = new HashMap<>(); info.put("exception", e); Trace.ffst(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "XN001009", info, JMSException.class); }  switch (getIntProperty("XMSC_WMQ_SHARE_CONV_ALLOWED")) { case 0: options |= 0x10000; break; }  int transport = getIntProperty("XMSC_WMQ_CONNECTION_MODE"); if (transport == 1) { switch (getIntProperty("XMSC_WMQ_CLIENT_RECONNECT_OPTIONS")) { case 67108864: options |= 0x4000000; break;case 16777216: options |= 0x1000000; break;case 33554432: options |= 0x2000000; break;case 0: options |= 0x0; break; }  this.jmqiConnectOptions.setReconnectionTimeout(getIntProperty("XMSC_WMQ_CLIENT_RECONNECT_TIMEOUT")); if ((options & 0x2000000) == 0); }  int mask = -327905; options |= getIntProperty("XMSC_WMQ_CONNECT_OPTIONS") & mask; connOptions.setOptions(options); String url = getStringProperty("XMSC_WMQ_CCDTURL"); if (url == null && transport != 0) { MQCD clientConn = this.jmqiEnvironment.newMQCD(); connOptions.setClientConn(clientConn); String qMgrName = getStringProperty("XMSC_WMQ_QUEUE_MANAGER"); if (qMgrName != null && !qMgrName.equals("")) clientConn.setQMgrName(qMgrName);  String localAddress = getStringProperty("XMSC_WMQ_LOCAL_ADDRESS"); if (localAddress != null && !localAddress.equals("")) { clientConn.setLocalAddress(localAddress); clientConn.setVersion(7); }  String userId = getStringProperty("XMSC_USERID"); String str1 = getStringProperty("XMSC_PASSWORD"); clientConn.setRemoteUserIdentifier(userId); clientConn.setRemotePassword(str1); String connname = getStringProperty("XMSC_WMQ_CONNECTION_NAME_LIST_INT"); if (connname != null && !connname.equals("")) { clientConn.setConnectionName(getStringProperty("XMSC_WMQ_CONNECTION_NAME_LIST_INT").trim()); } else { HashMap<String, Object> data = new HashMap<>((Map<? extends String, ?>)this); Trace.ffst(this, "getConnectOptions()", "XN00100H", data, null); }  clientConn.setChannelName(getStringProperty("XMSC_WMQ_CHANNEL")); clientConn.setMaxMsgLength(104857600); String string = getStringProperty("XMSC_WMQ_RECEIVE_EXIT"); if (string != null && string.trim().length() == 0) { Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getConnectOptions()", "Receive Exit", "Set exit string to null"); string = null; }  String fixedLengthString = makeFixedLengthStrings(string, JmqiEnvironment.getMqExitNameLength()); int count = getNumberOfExitsDefined(fixedLengthString, JmqiEnvironment.getMqExitNameLength()); clientConn.setReceiveExitPtr(fixedLengthString); clientConn.setReceiveExitsDefined(count); string = getStringProperty("XMSC_WMQ_RECEIVE_EXIT_INIT"); fixedLengthString = makeFixedLengthStrings(string, 32); if (this.jmqiConnectOptions != null) this.jmqiConnectOptions.setReceiveExitsUserData(fixedLengthString);  string = getStringProperty("XMSC_WMQ_SEND_EXIT"); if (string != null && string.trim().length() == 0) { Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getConnectOptions()", "Send Exit", "Set exit string to null"); string = null; }  fixedLengthString = makeFixedLengthStrings(string, JmqiEnvironment.getMqExitNameLength()); count = getNumberOfExitsDefined(fixedLengthString, JmqiEnvironment.getMqExitNameLength()); clientConn.setSendExitPtr(fixedLengthString); clientConn.setSendExitsDefined(count); string = getStringProperty("XMSC_WMQ_SEND_EXIT_INIT"); fixedLengthString = makeFixedLengthStrings(string, 32); if (this.jmqiConnectOptions != null) this.jmqiConnectOptions.setSendExitsUserData(fixedLengthString);  string = getStringProperty("XMSC_WMQ_SECURITY_EXIT"); clientConn.setSecurityExit(string); string = getStringProperty("XMSC_WMQ_SECURITY_EXIT_INIT"); clientConn.setSecurityUserData(string); if (this.jmqiConnectOptions != null) this.jmqiConnectOptions.setSecurityExitUserData(string);  String sslCipherSpec = getStringProperty("XMSC_WMQ_SSL_CIPHER_SPEC"); String sslCipherSuite = getStringProperty("XMSC_WMQ_SSL_CIPHER_SUITE"); if (sslCipherSuite != null && sslCipherSuite.length() == 0) sslCipherSuite = null;  if (sslCipherSpec == null) { if (sslCipherSuite != null) { sslCipherSpec = JmqiUtils.toCipherSpec(sslCipherSuite, sslFipsRequired); if (sslCipherSpec != null) { clientConn.setSslCipherSpec(sslCipherSpec); } else { HashMap<String, String> inserts = new HashMap<>(); inserts.put("XMSC_INSERT_QUEUE_MANAGER", this.queueManagerName); inserts.put("XMSC_WMQ_CONNECTION_MODE", connectionModeMappings[getIntProperty("XMSC_WMQ_CONNECTION_MODE")]); inserts.put("XMSC_WMQ_HOST_NAME", getStringProperty("XMSC_WMQ_HOST_NAME")); JMSException e = Reason.createException("JMSWMQ0018", inserts, 2400, 2, this.jmqiEnvironment); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getConnectOptions()", (Throwable)e);  throw e; }  }  } else { clientConn.setSslCipherSpec(sslCipherSpec); }  String sslPeerName = getStringProperty("XMSC_WMQ_SSL_PEER_NAME"); clientConn.setSslPeerName(sslPeerName); Object hdrComp = getObjectProperty("XMSC_WMQ_HEADER_COMP"); if (hdrComp instanceof String) { clientConn.setHdrCompList(getHdrCompArray((String)hdrComp)); } else if (hdrComp instanceof Collection) { clientConn.setHdrCompList(getHdrCompArray((Collection)hdrComp)); } else { HashMap<String, Object> data = new HashMap<>(); data.put("hdrComp", (hdrComp != null) ? hdrComp.toString() : "<null>"); if (hdrComp != null) data.put("class", hdrComp.getClass().getName());  Trace.ffst(this, "getConnectOptions()", "XN00100D", data, null); }  Object msgComp = getObjectProperty("XMSC_WMQ_MSG_COMP"); if (msgComp instanceof String) { clientConn.setMsgCompList(getMsgCompArray((String)msgComp)); } else if (msgComp instanceof Collection) { clientConn.setMsgCompList(getMsgCompArray((Collection)msgComp)); } else { HashMap<String, Object> data = new HashMap<>(); data.put("msgComp", (msgComp != null) ? msgComp.toString() : "<null>"); if (msgComp != null) data.put("class", msgComp.getClass().getName());  Trace.ffst(this, "getConnectOptions()", "XN00100E", data, null); }  }  byte[] connTag = getBytesProperty("XMSC_WMQ_CONNECTION_TAG"); connOptions.setConnTag(connTag); String username = getStringProperty("XMSC_USERID"); String password = getStringProperty("XMSC_PASSWORD"); boolean createDefaultMQCSP = (this.jmqiConnectOptions != null) ? this.jmqiConnectOptions.isSet(128) : true; MQCSP csp = null; if ((createDefaultMQCSP || transport == 0) && username != null && username.trim().length() > 0) { csp = this.jmqiEnvironment.newMQCSP(); csp.setCspUserId(username); csp.setCspPassword(password); csp.setAuthenticationType(1); }  connOptions.setSecurityParms(csp); if (transport == 1) { Object rebalancingApplicationTypeObject = getObjectProperty("XMSC_WMQ_REBALANCING_APPLICATION_TYPE"); if (rebalancingApplicationTypeObject != null) { MQBNO mqbno = this.jmqiEnvironment.newMQBNO(); mqbno.setType(getIntProperty("XMSC_WMQ_REBALANCING_APPLICATION_TYPE")); connOptions.setMqbno(mqbno); } else { connOptions.setMqbno(null); }  } else { connOptions.setMqbno(null); }  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getConnectOptions()", connOptions);  return connOptions; } public Hconn getHconn() { Hconn traceRet1 = this.phconn.getHconn(); if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getHconn()", "getter", traceRet1);  return traceRet1; } public JmqiConnectOptions getJmqiConnectOptions() throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getJmqiConnectOptions()");  if (this.jmqiConnectOptions == null) { JmqiSystemEnvironment jmqiSystemEnvironment = (JmqiSystemEnvironment)this.jmqiEnvironment; this.jmqiConnectOptions = jmqiSystemEnvironment.newJmqiConnectOptions(); this.jmqiConnectOptions.setJms(true); String userid = getStringProperty("XMSC_USERID"); this.jmqiConnectOptions.setUserIdentifier(userid); String password = getStringProperty("XMSC_PASSWORD"); this.jmqiConnectOptions.setPassword(password); Configuration config = this.jmqiEnvironment.getConfiguration(); boolean nullPassword = getBooleanProperty("XMSC_PASSWORD_WAS_NULL"); boolean existsJmsMQCSP = propertyExists("XMSC_USER_AUTHENTICATION_MQCSP"); boolean useJmsMQCSP = getBooleanProperty("XMSC_USER_AUTHENTICATION_MQCSP"); boolean useConfigMQCSP = config.getBoolValue(Configuration.USE_MQCSP_AUTHENTICATION); boolean defaultConfigMQCSP = config.wasDefaulted((Configuration.CfgProperty)Configuration.USE_MQCSP_AUTHENTICATION); int transport = getIntProperty("XMSC_WMQ_CONNECTION_MODE"); boolean setBitMQCSP = true; if (Trace.isOn) { Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getJmqiConnectOptions()", "nullPassword: " + nullPassword); Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getJmqiConnectOptions()", "existsJmsMQCSP: " + existsJmsMQCSP); Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getJmqiConnectOptions()", "useJmsMQCSP: " + useJmsMQCSP); Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getJmqiConnectOptions()", "useConfigMQCSP: " + useConfigMQCSP); Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getJmqiConnectOptions()", "defaultConfigMQCSP: " + defaultConfigMQCSP); }  if (transport != 0) { if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getJmqiConnectOptions()", "Application is not using the BINDINGS transport");  if (!nullPassword && password.trim().length() >= 0) { if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getJmqiConnectOptions()", "Application has specified a password");  if ((existsJmsMQCSP && !useJmsMQCSP) || (!existsJmsMQCSP && !useConfigMQCSP)) { if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getJmqiConnectOptions()", "The current configuration indicates that MQCSP Authentication should not be used");  if (password.trim().length() > 12) { if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getJmqiConnectOptions()", "Password is longer than 12 characters. Long passwords are only supported when MQCSP Authentication is enabled - throwing exception containing MQRC_ENVIRONMENT_ERROR");  HashMap<String, String> inserts = new HashMap<>(); JMSException cspException = null; inserts.put("XMSC_INSERT_QUEUE_MANAGER", this.queueManagerName); inserts.put("XMSC_WMQ_CONNECTION_MODE", connectionModeMappings[getIntProperty("XMSC_WMQ_CONNECTION_MODE")]); inserts.put("XMSC_WMQ_HOST_NAME", getStringProperty("XMSC_WMQ_HOST_NAME")); cspException = Reason.createException("JMSWMQ0018", inserts, 2012, 2, this.jmqiEnvironment); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getConnectOptions()", (Throwable)cspException);  throw cspException; }  if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getJmqiConnectOptions()", "Password has a length of 12 characters or less. Setting flag indicating MQCSP Authentication should be disabled");  setBitMQCSP = false; }  } else { if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getJmqiConnectOptions()", "Application has not specified a password");  if ((!existsJmsMQCSP || !useJmsMQCSP) && (existsJmsMQCSP || !useConfigMQCSP || defaultConfigMQCSP)) { if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getJmqiConnectOptions()", "The current configuration indicates that MQCSP Authentication should not be used. Setting flag indicating MQCSP Authentication should be disabled");  setBitMQCSP = false; }  }  if (setBitMQCSP && Trace.isOn) Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getJmqiConnectOptions()", "The current configuration indicates that MQCSP Authentication should be used");  }  if ((setBitMQCSP || transport == 0) && userid != null && userid.trim().length() > 0) { this.jmqiConnectOptions.setFlag(128); } else { this.jmqiConnectOptions.unsetFlag(128); }  String appName = getStringProperty("XMSC_WMQ_APPNAME"); this.jmqiConnectOptions.setApplicationName(appName); if (appName != null && appName.equals("com.ibm.mq.webui.backend")) this.jmqiConnectOptions.setWebAdminConnection(true);  Object rebalancingObject = getObjectProperty("XMSC_WMQ_REBALANCING_LISTENER"); RebalancingListener rebalancingListener = null; if (rebalancingObject instanceof RebalancingListener) { rebalancingListener = (RebalancingListener)rebalancingObject; if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getJmqiConnectOptions()", "RebalancingListener<init>", rebalancingListener);  }  this.jmqiConnectOptions.setRebalancingListener(rebalancingListener); try { URL url; String value = getStringProperty("XMSC_WMQ_CCDTURL"); if (value != null) { url = new URL(value); } else { url = null; }  this.jmqiConnectOptions.setCcdtUrl(url); } catch (MalformedURLException e) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getJmqiConnectOptions()", e, 1);  }  Collection<?> sslCertStoresCol = (Collection)getObjectProperty("XMSC_WMQ_SSL_CERT_STORES_COL"); if (sslCertStoresCol == null) { String sslCertStoresStr = getStringProperty("XMSC_WMQ_SSL_CERT_STORES_STR"); if (sslCertStoresStr != null && !sslCertStoresStr.trim().equals("")) try { sslCertStoresCol = JmqiUtils.getCertStoreCollectionFromSpaceSeperatedString(sslCertStoresStr); } catch (Exception e) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getJmqiConnectOptions()", e, 2);  HashMap<String, String> info = new HashMap<>(); info.put("XMSC_INSERT_VALUE", sslCertStoresStr); info.put("XMSC_INSERT_PROPERTY", "XMSC_WMQ_SSL_CERT_STORES_STR"); JMSException je = (JMSException)NLSServices.createException("JMSWMQ1006", info); je.setLinkedException(e); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getJmqiConnectOptions()", (Throwable)je);  throw je; }   }  this.jmqiConnectOptions.setCrlCertStores(sslCertStoresCol); SSLSocketFactory sslSocketFactory = (SSLSocketFactory)getObjectProperty("XMSC_WMQ_SSL_SOCKET_FACTORY"); this.jmqiConnectOptions.setSslSocketFactory(sslSocketFactory); int ccsid = getIntProperty("XMSC_WMQ_QMGR_CCSID"); this.jmqiConnectOptions.setQueueManagerCCSID(ccsid); String requiredQmid = getStringProperty("XMSC_WMQ_REQUIRED_QUEUE_MANAGER_ID"); if (requiredQmid != null) { this.jmqiConnectOptions.setWasReconnectQmgr(true); this.jmqiConnectOptions.setReconnectionQmId(requiredQmid); }  try { boolean optimize = PropertyStore.getBooleanPropertyObject("com.ibm.msg.client.wmq.overrideInheritRRSContext").booleanValue(); if (optimize) { final WASSupport.WASRuntimeHelper helper1 = (WASSupport.WASRuntimeHelper)PropertyStore.getObjectProperty("com.ibm.mq.connector.JCARuntimeHelper"); if (helper1 != null && WASSupport.getWASSupport().isWASCommonServicesPresent()) if (helper1.getEnvironment() == 32) this.jmqiConnectOptions.setWASLocalUOWID(new JmqiConnectOptions.JmqiOptionAdapter() {
/*      */                     public long getLongOption() { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getLongOption()");  long rrsTranID = -1L; try { rrsTranID = helper1.getLocalRRSTranId(); if (rrsTranID == -1L) rrsTranID = WMQConnection.this.fakeGetLocalRRSTrandId();  } catch (IllegalStateException e) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.null", "getLongOption()", e);  if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.wmq.internal.null", "IllegalStateException from WAS", e);  }  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.wmq.internal.null", "getLongOption()", Long.valueOf(rrsTranID));  return rrsTranID; }
/*  302 */                   });   }  } catch (Exception e) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getJmqiConnectOptions()", e, 3);  if (Trace.isOn) Trace.traceInfo(this, "WMQConnection", "getJmqiConnectOptions()", "Failed to setup the 2PC optimization " + e);  }  }  String requiredQmId = getStringProperty("XMSC_WMQ_REQUIRED_QUEUE_MANAGER_ID"); String requiredQsgId = getStringProperty("XMSC_WMQ_REQUIRED_QUEUE_SHARING_GROUP_NAME"); if (requiredQmId != null || requiredQsgId != null) { this.jmqiConnectOptions.setWasReconnectQmgr(true); this.jmqiConnectOptions.setReconnectionQmId(requiredQmId); this.jmqiConnectOptions.setReconnectionQsgName(requiredQsgId); } else { this.jmqiConnectOptions.setWasReconnectQmgr(false); this.jmqiConnectOptions.setReconnectionQmId(null); this.jmqiConnectOptions.setReconnectionQsgName(null); }  this.jmqiConnectOptions.setReconnectionListener(this); if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getJmqiConnectOptions()", this.jmqiConnectOptions);  return this.jmqiConnectOptions; } public JmqiEnvironment getJmqiEnvironment() { if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getJmqiEnvironment()", "getter", this.jmqiEnvironment);  return this.jmqiEnvironment; } public WMQConnection(JmqiEnvironment jmqiEnvironment, JmqiMQ jmqiMq, int jmqiCompId, JmsPropertyContext connectionProps) throws JMSException { super(connectionProps);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2951 */     this.UOWManagerObject = null;
/* 2952 */     this.getLocalUOWIdMethod = null;
/* 2953 */     this.setupReflectionAttempted = false; if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "<init>(JmqiEnvironment,JmqiMQ,int,JmsPropertyContext)", new Object[] { jmqiEnvironment, jmqiMq, Integer.valueOf(jmqiCompId), connectionProps });  ThreadLocal<String> qmidForRecovery = (ThreadLocal<String>)getObjectProperty("XMSC_WMQ_REQUIRED_QUEUE_MANAGER_THREADLOCAL"); if (qmidForRecovery != null) try { String possibleQmId = qmidForRecovery.get(); if (possibleQmId != null) { if (possibleQmId.startsWith("QSGNAME:")) { setStringProperty("XMSC_WMQ_REQUIRED_QUEUE_SHARING_GROUP_NAME", possibleQmId.substring("QSGNAME:".length())); } else if (possibleQmId.startsWith("QMID:")) { setStringProperty("XMSC_WMQ_REQUIRED_QUEUE_MANAGER_ID", possibleQmId.substring("QMID:".length())); }  } else { setStringProperty("XMSC_WMQ_REQUIRED_QUEUE_SHARING_GROUP_NAME", null); setStringProperty("XMSC_WMQ_REQUIRED_QUEUE_MANAGER_ID", null); }  } catch (JMSException jMSException) {}  this.jmqiEnvironment = jmqiEnvironment; this.jmqiMq = jmqiMq; this.jmqiSp = (JmqiSP)jmqiMq; this.jmqiCompId = jmqiCompId; this.phconn = jmqiEnvironment.newPhconn(); Pint cc = jmqiEnvironment.newPint(); Pint rc = jmqiEnvironment.newPint(); Pint cc2 = jmqiEnvironment.newPint(); Pint rc2 = jmqiEnvironment.newPint(); setQueueManagerNameAllowingForOverride(); String ccdt = getStringProperty("XMSC_WMQ_CCDTURL"); String channel = getStringProperty("XMSC_WMQ_CHANNEL"); if (ccdt != null && channel != null && channel.length() > 0) if (!channel.equals("SYSTEM.DEF.SVRCONN")) { HashMap<String, String> inserts = new HashMap<>(); inserts.put("XMSC_INSERT_QUEUE_MANAGER", this.queueManagerName); inserts.put("XMSC_WMQ_CONNECTION_MODE", connectionModeMappings[getIntProperty("XMSC_WMQ_CONNECTION_MODE")]); inserts.put("XMSC_WMQ_CCDTURL", ccdt); inserts.put("XMSC_WMQ_CHANNEL", channel); JMSException je = Reason.createException("JMSWMQ2020", inserts, 2423, 2, jmqiEnvironment); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "<init>(JmqiEnvironment,JmqiMQ,int,JmsPropertyContext)", (Throwable)je, 1);  throw je; }   getJmqiConnectOptions(); this.connectOptions = getConnectOptions(); this.jmqiSp.jmqiConnect(this.queueManagerName, this.jmqiConnectOptions, this.connectOptions, null, this.phconn, cc, rc); if (cc.x == 1 && rc.x == 2267) { cc.x = 0; rc.x = 0; }  if (cc.x == 1) { jmqiMq.MQDISC(this.phconn, cc2, rc2); } else if (cc.x == 0) { String userid = this.jmqiConnectOptions.getUserIdentifier(); if (userid != null && !"".equals(userid.trim())) { Hconn hconn = getHconn(); String password = this.jmqiConnectOptions.getPassword(); jmqiMq.authenticate(hconn, userid, password, cc, rc); if (cc.x != 0) jmqiMq.MQDISC(this.phconn, cc2, rc2);  }  }  if (cc.x != 0) { String exceptionMessage; if (connectionProps instanceof JmsConnectionImpl) ((JmsConnectionImpl)connectionProps).dropClientId();  if (Reason.isImpossibleReason(rc.x, cc.x, this.jmqiSp)) { HashMap<String, Object> info = new HashMap<>(); info.put("rc", rc); info.put("cc", cc); info.put("queuemanager", this.queueManagerName); info.put("jmqiConnOpts", this.jmqiConnectOptions); info.put("connOpts", this.connectOptions); Trace.ffst(this, "<init>(WMQConnection,int,JmsPropertyContext)", "XN001001", info, JMSException.class); }  HashMap<String, String> inserts = new HashMap<>(); inserts.put("XMSC_INSERT_QUEUE_MANAGER", this.queueManagerName); inserts.put("XMSC_WMQ_CONNECTION_MODE", connectionModeMappings[getIntProperty("XMSC_WMQ_CONNECTION_MODE")]); if (rc.x == 2035) { if (ccdt == null) { inserts.put("XMSC_WMQ_HOST_NAME", getStringProperty("XMSC_WMQ_CONNECTION_NAME_LIST_INT")); exceptionMessage = "JMSWMQ2013"; } else { inserts.put("XMSC_WMQ_CCDTURL", ccdt); exceptionMessage = "JMSWMQ2021"; }  } else if (ccdt == null) { inserts.put("XMSC_WMQ_HOST_NAME", connectionModeMappings[getIntProperty("XMSC_WMQ_CONNECTION_MODE")]); exceptionMessage = "JMSWMQ0018"; } else { inserts.put("XMSC_WMQ_CCDTURL", ccdt); exceptionMessage = "JMSWMQ2020"; }  JMSException je = Reason.createException(exceptionMessage, inserts, rc.x, cc.x, jmqiEnvironment); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "<init>(JmqiEnvironment,JmqiMQ,int,JmsPropertyContext)", (Throwable)je, 2);  throw je; }  Trace.registerFFSTObject(this); updateQmNameEtc(); try { setIntProperty("XMSC_WMQ_COMMAND_LEVEL", getCmdLevel()); if (this.jmqiSp.isCICS()) { setBooleanProperty("XMSC_CAPABILITY_NATIVE_CICS_UNMANAGED", true); } else if (this.jmqiSp.isIMS()) { setBooleanProperty("XMSC_CAPABILITY_NATIVE_IMS", true); }  if (getHconn().getQmgrAdvancedCapability() == QmgrAdvancedCapability.SUPPORTED) setBooleanProperty("XMSC_WMQ_CAPABILITY_ADVANCED", true);  } catch (JmqiException e) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "<init>(JmqiEnvironment,JmqiMQ,int,JmsPropertyContext)", (Throwable)e, 2);  HashMap<String, String> inserts = new HashMap<>(); inserts.put("XMSC_INSERT_COMP_CODE", Integer.toString(e.getCompCode())); inserts.put("XMSC_INSERT_REASON", Integer.toString(e.getReason())); inserts.put("XMSC_INSERT_METHOD", "WMQConnection.<init>(JmqiEnvironment,JmqiMQ,int,JmsPropertyContext)"); String exceptionMessage = "JMSWMQ2019"; JMSException je = Reason.createException(exceptionMessage, inserts, e.getReason(), e.getCompCode(), jmqiEnvironment); je.setLinkedException((Exception)e); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "<init>(JmqiEnvironment,JmqiMQ,int,JmsPropertyContext)", (Throwable)je, 4);  throw je; }  try { setStringProperty("XMSC_WMQ_REMOTE_QMGR_QSGNAME", getHconn().getQsgName()); } catch (JmqiException e) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "<init>(JmqiEnvironment,JmqiMQ,int,JmsPropertyContext)", (Throwable)e, 3);  if (Trace.isOn) { Trace.data(this, "Ignoring JmqiException reported during getQSGName check", ""); Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "<init>(JmqiEnvironment,JmqiMQ,int,JmsPropertyContext)", (Throwable)e); }  setStringProperty("XMSC_WMQ_REMOTE_QMGR_QSGNAME", "UNKNOWN"); }  this.helper = new WMQConsumerOwnerShadow(this, this.queueManagerName); String persistenceFromMDAsString = PropertyStore.getStringProperty(persistenceFromMDProperty); if (persistenceFromMDAsString != null && persistenceFromMDAsString.equalsIgnoreCase("ON")) { this.persistenceFromMD = true; } else { this.persistenceFromMD = false; }  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "<init>(JmqiEnvironment,JmqiMQ,int,JmsPropertyContext)");  }
/*      */   public JmqiMQ getJmqiMQ() { if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getJmqiMQ()", "getter", this.jmqiMq);  return this.jmqiMq; }
/*      */   public ProviderMetaData getMetaData(JmsPropertyContext parentMetadata) throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getMetaData(JmsPropertyContext)", new Object[] { parentMetadata });  if (this.metaData == null) this.metaData = new WMQMetaData(parentMetadata, this);  try { this.metaData.setBooleanProperty("XMSC_IS_Z_SERIES", (getHconn().getPlatform() == 1)); } catch (JmqiException e) { if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getMetaData(JmsPropertyContext)", this.metaData);  JMSException je = Reason.createException("com.ibm.msg.client.wmq.internal.WMQConnection.getMetaData(JmsPropertyContext)", e.getCompCode(), e.getReason(), this.jmqiEnvironment); throw je; }  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getMetaData(JmsPropertyContext)", this.metaData);  return (ProviderMetaData)this.metaData; }
/*      */   public String getName() throws JmqiException { Hconn hconn = this.phconn.getHconn(); String traceRet1 = hconn.getName(); if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getName()", "getter", traceRet1);  return traceRet1; }
/* 2957 */   private int getNumberOfExitsDefined(String fixedLengthString, int length) { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getNumberOfExitsDefined(String,int)", new Object[] { fixedLengthString, Integer.valueOf(length) });  int result = 0; if (fixedLengthString != null) result = fixedLengthString.length() / length;  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getNumberOfExitsDefined(String,int)", Integer.valueOf(result));  return result; } public boolean getPersistenceFromMD() { if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getPersistenceFromMD()", "getter", Boolean.valueOf(this.persistenceFromMD));  return this.persistenceFromMD; } public int getPlatform() throws JmqiException { Hconn hconn = this.phconn.getHconn(); int traceRet1 = hconn.getPlatform(); if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getPlatform()", "getter", Integer.valueOf(traceRet1));  return traceRet1; } private void setQueueManagerNameAllowingForOverride() throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "setQueueManagerNameAllowingForOverride()");  AbstractQueue<RebalancingRequest> queueManagerOverrideQueue = (AbstractQueue<RebalancingRequest>)getObjectProperty("XMSC_WMQ_QUEUE_MANAGER_OVERRIDE_QUEUE"); if (queueManagerOverrideQueue != null) { RebalancingRequest request = queueManagerOverrideQueue.poll(); if (request != null) this.queueManagerName = request.getQmName();  if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "setQueueManagerNameAllowingForOverride()", "setter", this.queueManagerName);  }  getQueueManagerName(); if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "setQueueManagerNameAllowingForOverride()");  } private long fakeGetLocalRRSTrandId() { if (Trace.isOn) {
/* 2958 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "fakeGetLocalRRSTrandId()");
/*      */     }
/*      */     
/* 2961 */     long uow = -1L;
/*      */ 
/*      */     
/*      */     try {
/* 2965 */       if (!this.setupReflectionAttempted) {
/* 2966 */         setupUOWRelfection();
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2971 */       if (this.UOWManagerObject != null && this.getLocalUOWIdMethod != null) {
/*      */         
/* 2973 */         Object longObj = this.getLocalUOWIdMethod.invoke(this.UOWManagerObject, (Object[])null);
/*      */ 
/*      */         
/* 2976 */         uow = ((Long)longObj).longValue();
/*      */         
/* 2978 */         if (Trace.isOn) {
/* 2979 */           Trace.traceInfo(this, "WMQConnection", "fakeGetLocalRRSTrandId()", "UOW id=" + uow);
/*      */ 
/*      */         
/*      */         }
/*      */       
/*      */       }
/* 2985 */       else if (Trace.isOn) {
/* 2986 */         Trace.traceInfo(this, "WMQConnection", "fakeGetLocalRRSTrandId()", "Can't proceed method setupUOWReflection failed");
/*      */       
/*      */       }
/*      */     
/*      */     }
/* 2991 */     catch (Exception e) {
/* 2992 */       if (Trace.isOn) {
/* 2993 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "fakeGetLocalRRSTrandId()", e);
/*      */       }
/*      */ 
/*      */       
/* 2997 */       if (Trace.isOn) {
/* 2998 */         Trace.catchBlock(this, "WMQConnection", "fakeGetLocalRRSTrandId()", e);
/*      */       }
/*      */     } 
/*      */     
/* 3002 */     if (Trace.isOn) {
/* 3003 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "fakeGetLocalRRSTrandId()", Long.valueOf(uow));
/*      */     }
/* 3005 */     return uow; } public String getQueueManagerName() throws JMSException { if (this.queueManagerName == null) this.queueManagerName = getStringProperty("XMSC_WMQ_QUEUE_MANAGER");  if (this.queueManagerName != null) this.queueManagerName = this.queueManagerName.trim();  if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getQueueManagerName()", "getter", this.queueManagerName);  return this.queueManagerName; } public boolean getTransacted() { if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getTransacted()", "getter", Boolean.valueOf(false));  return false; } public String getUid() throws JmqiException { Hconn hconn = this.phconn.getHconn(); String traceRet1 = hconn.getUid(); if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getUid()", "getter", traceRet1);  return traceRet1; } public void incrementCloseCounter() { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "incrementCloseCounter()");  this.helper.incrementCloseCounter(); if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "incrementCloseCounter()");  } public boolean isAsyncRunning() { boolean traceRet1 = this.helper.isAsyncRunning(); if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "isAsyncRunning()", "getter", Boolean.valueOf(traceRet1));  return traceRet1; } public boolean isAsyncConsumeThread() { boolean traceRet1 = this.jmqiMq.isAsyncConsumeThread(getHconn()); if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "isAsyncConsumeThread()", "getter", Boolean.valueOf(traceRet1));  return traceRet1; } public boolean isSubscriptionInUse(String subscriptionName) { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "isSubscriptionInUse(String)", new Object[] { subscriptionName });  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "isSubscriptionInUse(String)", Boolean.valueOf(false));  return false; } private String makeFixedLengthStrings(String input, int length) { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "makeFixedLengthStrings(String,int)", new Object[] { input, Integer.valueOf(length) });  String result = null; if (input != null) { Enumeration<String> eTokens = tokenise(input); StringBuffer buffer = new StringBuffer(); while (eTokens.hasMoreElements()) { String exit = eTokens.nextElement(); if (exit.length() > length) exit = exit.substring(0, length);  buffer.append(exit); for (int j = exit.length(); j < length; j++) buffer.append(' ');  }  result = buffer.toString(); }  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "makeFixedLengthStrings(String,int)", result);  return result; } public void removeAsyncConsumer() throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "removeAsyncConsumer()");  lockHconn(); try { this.helper.removeAsyncConsumer(); } finally { unlockHconn(); }  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "removeAsyncConsumer()");  } public WMQHobjCache getHobjCache() { if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getHobjCache()", "getter", null);  return null; } public void removeSubscription(String subscriptionName) { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "removeSubscription(String)", new Object[] { subscriptionName });  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "removeSubscription(String)");  } public void resumeAsyncService() throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "resumeAsyncService()");  this.helper.resumeAsyncService(); if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "resumeAsyncService()");  } public void setExceptionListener(ProviderExceptionListener listener) throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "setExceptionListener(ProviderExceptionListener)", new Object[] { listener });  int transport = getIntProperty("XMSC_WMQ_CONNECTION_MODE"); if (this.exceptionListener != null && listener != null) { this.exceptionListener = listener; } else if (this.exceptionListener != null || listener != null) { if (listener != null && transport != 0) { MQCBD cbd = this.jmqiEnvironment.newMQCBD(); cbd.setCallbackFunction(this); cbd.setCallbackType(2); lockHconn(); try { boolean didSuspend = suspendAsyncService(); Phobj pHobj = this.jmqiEnvironment.newPhobj(); Pint cc = this.jmqiEnvironment.newPint(); Pint rc = this.jmqiEnvironment.newPint(); this.jmqiMq.MQCB(this.phconn.getHconn(), 256, cbd, pHobj.getHobj(), null, null, cc, rc); if (rc.x != 0 || cc.x != 0) { if (Reason.isImpossibleReason(rc.x, cc.x, null)) { HashMap<String, Object> info = new HashMap<>(); info.put("messageid", "JMSWMQ2016"); info.put("connection", this); info.put("reason", rc); info.put("compcode", cc); info.put("hconn", this.phconn.getHconn()); Trace.ffst("WMQConnection", "setExceptionListener", "XN00100A", info, JMSException.class); }  JMSException je = Reason.createException("JMSWMQ2016", null, rc.x, cc.x, this.jmqiEnvironment); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "setExceptionListener(ProviderExceptionListener)", (Throwable)je, 1);  throw je; }  if (didSuspend) resumeAsyncService();  } finally { unlockHconn(); }  } else if (transport != 0) { lockHconn(); try { suspendAsyncService(); MQCBD cbd = this.jmqiEnvironment.newMQCBD(); cbd.setCallbackType(2); Phobj pHobj = this.jmqiEnvironment.newPhobj(); Pint cc = this.jmqiEnvironment.newPint(); Pint rc = this.jmqiEnvironment.newPint(); this.jmqiMq.MQCB(this.phconn.getHconn(), 512, cbd, pHobj.getHobj(), null, null, cc, rc); if (rc.x != 0 || cc.x != 0) { if (Reason.isImpossibleReason(rc.x, cc.x, null)) { HashMap<String, Object> info = new HashMap<>(); info.put("messageid", "JMSWMQ2017"); info.put("connection", this); info.put("reason", rc); info.put("compcode", cc); info.put("hconn", this.phconn.getHconn()); Trace.ffst("WMQConnection", "setExceptionListener", "XN00100B", info, JMSException.class); }  JMSException je = Reason.createException("JMSWMQ2017", null, rc.x, cc.x, this.jmqiEnvironment); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "setExceptionListener(ProviderExceptionListener)", (Throwable)je, 2);  throw je; }  resumeAsyncService(); } finally { unlockHconn(); }  }  this.exceptionListener = listener; }  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "setExceptionListener(ProviderExceptionListener)");  } public void start() throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "start()");  lockHconn(); try { this.helper.start(); } finally { unlockHconn(); }  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "start()");  } public void stop() throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "stop()");  lockHconn(); try { this.helper.stop(); } finally { unlockHconn(); }  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "stop()");  } public boolean suspendAsyncService() throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "suspendAsyncService()");  boolean didSuspend = this.helper.suspendAsyncService(); if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "suspendAsyncService()", Boolean.valueOf(didSuspend));  return didSuspend; } public void syncpoint(boolean commit, boolean forceTransactedCommit, WMQDestination destination) throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "syncpoint(boolean,boolean,WMQDestination)", new Object[] { Boolean.valueOf(commit), Boolean.valueOf(forceTransactedCommit), destination });  Pint cc = this.jmqiEnvironment.newPint(); Pint rc = this.jmqiEnvironment.newPint(); if (commit) { this.jmqiMq.MQCMIT(this.phconn.getHconn(), cc, rc); if (cc.x == 1 && rc.x == 2408) { cc.x = 0; rc.x = 0; }  } else { this.jmqiMq.MQBACK(this.phconn.getHconn(), cc, rc); }  checkJmqiCallSuccess(commit ? "MQCMIT" : "MQBACK", "JMSWMQ0030", destination.getName(), cc, rc, "XN00S006"); if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "syncpoint(boolean,boolean,WMQDestination)");  } private void checkJmqiCallSuccess(String method, String messageid, String targetDestination, Pint completionCode, Pint reason, String probeid) throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "checkJmqiCallSuccess(String,String,String,Pint,Pint,String)", new Object[] { method, messageid, targetDestination, completionCode, reason, probeid });  if (reason.x != 0 || completionCode.x != 0) { if (Reason.isImpossibleReason(reason.x, completionCode.x, this.jmqiSp)) { HashMap<String, Object> info = new HashMap<>(); info.put("method", method); info.put("reason", reason); info.put("compcode", completionCode); info.put("target destination", targetDestination); info.put("hconn", this.phconn.getHconn()); Trace.ffst("WMQConnection", "checkJmqiCallSuccess", probeid, info, JMSException.class); }  if (Reason.isConnectionBroken(reason.x)) { JMSException e = Reason.createException("JMSWMQ1107", null, reason.x, completionCode.x, this.jmqiEnvironment); driveExceptionListener(e, true); }  HashMap<String, Object> inserts = new HashMap<>(); inserts.put("XMSC_INSERT_METHOD", method); if (targetDestination != null) inserts.put("XMSC_DESTINATION_NAME", targetDestination);  JMSException je = Reason.createException(messageid, inserts, reason.x, completionCode.x, this.jmqiEnvironment); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "checkJmqiCallSuccess(String,String,String,Pint,Pint,String)", (Throwable)je);  throw je; }  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "checkJmqiCallSuccess(String,String,String,Pint,Pint,String)");  } private void throwIfClosed() throws IllegalStateException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "throwIfClosed()");  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "throwIfClosed()");  } public void validateProperty(String name, Object value) throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "validateProperty(String,Object)", new Object[] { name, value });  boolean isValid = true; if (!isValid) throwValidationException(name, value, null);  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "validateProperty(String,Object)");  } public int getJmqiCompId() { if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getJmqiCompId()", "getter", Integer.valueOf(this.jmqiCompId));  return this.jmqiCompId; } public int getCloseCounter() { int traceRet1 = this.helper.getCloseCounter(); if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getCloseCounter()", "getter", Integer.valueOf(traceRet1));  return traceRet1; } public String toString() { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "toString()");  TableBuilder builder = new TableBuilder(); builder.append("Instance", super.toString()); builder.append("connectOptions", this.connectOptions); builder.append("exceptionListener", this.exceptionListener); builder.append("helper", this.helper); builder.append("queueManagerName", this.queueManagerName); builder.append("temporaryDestinationNumber", Long.valueOf(this.temporaryDestinationNumber)); builder.append("temporaryModelQ", this.temporaryModelQ); builder.append("tempQPrefix", this.tempQPrefix); String traceRet1 = builder.toString(); if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "toString()", traceRet1);  return traceRet1; } public JSONObject toJson() { return StringableProperty.jsonIfy((JmsReadablePropertyContext)this); }
/*      */   public void dump(PrintWriter pw, int level) { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "dump(PrintWriter,int)", new Object[] { pw, Integer.valueOf(level) });  String prefix = Trace.buildPrefix(level); pw.format("%s%s\n", new Object[] { prefix, super.toString() }); if (this.creator != null) { pw.format("%s  created by \"%s\" (id: %d) @ %s - stack :\n", new Object[] { prefix, this.creator.getName(), Long.valueOf(this.creator.getId()), Trace.formatTimeStamp(this.createTime) }); Trace.dumpStack(pw, level + 1, this.createStack); }  pw.format("%s  connectOptions %s\n", new Object[] { prefix, String.valueOf(this.connectOptions) }); pw.format("%s  exceptionListener %s\n", new Object[] { prefix, String.valueOf(this.exceptionListener) }); pw.format("%s  helper %s\n", new Object[] { prefix, String.valueOf(this.helper) }); pw.format("%s  queueManagerName %s\n", new Object[] { prefix, String.valueOf(this.queueManagerName) }); pw.format("%s  temporaryDestinationNumber %d\n", new Object[] { prefix, Long.valueOf(this.temporaryDestinationNumber) }); pw.format("%s  temporaryModelQ %s\n", new Object[] { prefix, String.valueOf(this.temporaryModelQ) }); pw.format("%s  tempQPrefix %s\n", new Object[] { prefix, String.valueOf(this.tempQPrefix) }); if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "dump(PrintWriter,int)");  }
/*      */   public void operationPerformed(WMQConsumerOwner.Operation operation, boolean syncpoint) { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "operationPerformed(Operation,boolean)", new Object[] { operation, Boolean.valueOf(syncpoint) });  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "operationPerformed(Operation,boolean)");  }
/*      */   private int[] getHdrCompArray(String value) { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getHdrCompArray(String)", new Object[] { value });  StringTokenizer st = new StringTokenizer(value); int[] result = new int[st.countTokens()]; int pos = 0; while (st.hasMoreTokens()) { String tk = st.nextToken(); if (tk.equals("NONE")) { result[pos++] = 0; continue; }  if (tk.equals("SYSTEM")) { result[pos++] = 8; continue; }  try { result[pos] = Integer.parseInt(tk); pos++; } catch (NumberFormatException nfe) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getHdrCompArray(String)", nfe);  HashMap<String, Object> data = new HashMap<>(); data.put("value", value); Trace.ffst(this, "getHdrCompArray(String)", "XN00100F", data, null); }  }  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getHdrCompArray(String)", result);  return result; }
/*      */   private int[] getHdrCompArray(Collection<?> value) { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getHdrCompArray(Collection<?>)", new Object[] { value });  StringBuffer buff = new StringBuffer(); Iterator<?> i = value.iterator(); while (i.hasNext()) { buff.append(i.next().toString()); if (i.hasNext()) buff.append(" ");  }  int[] traceRet1 = getHdrCompArray(buff.toString()); if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getHdrCompArray(Collection<?>)", traceRet1);  return traceRet1; }
/*      */   private int[] getMsgCompArray(String value) { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getMsgCompArray(String)", new Object[] { value });  StringTokenizer st = new StringTokenizer(value); int[] result = new int[st.countTokens()]; int pos = 0; while (st.hasMoreTokens()) { String tk = st.nextToken(); if (tk.equals("NONE")) { result[pos++] = 0; continue; }  if (tk.equals("RLE")) { result[pos++] = 1; continue; }  if (tk.equals("ZLIBFAST")) { result[pos++] = 2; continue; }  if (tk.equals("ZLIBHIGH")) { result[pos++] = 4; continue; }  try { result[pos] = Integer.parseInt(tk); pos++; } catch (NumberFormatException nfe) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getMsgCompArray(String)", nfe);  HashMap<String, Object> data = new HashMap<>(); data.put("value", value); Trace.ffst(this, "getMsgCompArray(String)", "XN00100G", data, null); }  }  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getMsgCompArray(String)", result);  return result; }
/*      */   private int[] getMsgCompArray(Collection<?> value) { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getMsgCompArray(Collection<?>)", new Object[] { value });  StringBuffer buff = new StringBuffer(); Iterator<?> i = value.iterator(); while (i.hasNext()) { buff.append(i.next().toString()); if (i.hasNext()) buff.append(" ");  }  int[] traceRet1 = getMsgCompArray(buff.toString()); if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getMsgCompArray(Collection<?>)", traceRet1);  return traceRet1; }
/* 3012 */   private void setupUOWRelfection() { if (Trace.isOn) {
/* 3013 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "setupUOWRelfection()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 3021 */       Class<?> registryClass = CSSystem.dynamicLoadClass("com.ibm.ws.uow.UOWManagerFactory", getClass());
/*      */ 
/*      */       
/* 3024 */       Method getUOWManager = registryClass.getDeclaredMethod("getUOWManager", (Class[])null);
/* 3025 */       this.UOWManagerObject = getUOWManager.invoke(registryClass, (Object[])null);
/*      */ 
/*      */       
/* 3028 */       this.getLocalUOWIdMethod = this.UOWManagerObject.getClass().getDeclaredMethod("getLocalUOWId", (Class[])null);
/*      */ 
/*      */       
/* 3031 */       this.setupReflectionAttempted = true;
/*      */     
/*      */     }
/* 3034 */     catch (Exception e) {
/* 3035 */       if (Trace.isOn) {
/* 3036 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "setupUOWRelfection()", e);
/*      */       }
/* 3038 */       if (Trace.isOn) {
/* 3039 */         Trace.catchBlock(this, "WMQConnection", "setupUOWRelfection()", e);
/*      */       }
/*      */     } 
/*      */     
/* 3043 */     if (Trace.isOn) {
/* 3044 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "setupUOWRelfection()");
/*      */     } }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public WMQThreadLocalStorage getThreadLocalStorage() {
/* 3054 */     JmqiSystemEnvironment sysenv = (JmqiSystemEnvironment)this.jmqiEnvironment;
/* 3055 */     WMQThreadLocalStorage traceRet1 = (WMQThreadLocalStorage)sysenv.getComponentTls(this.jmqiCompId);
/* 3056 */     if (Trace.isOn) {
/* 3057 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "getThreadLocalStorage()", "getter", traceRet1);
/*      */     }
/* 3059 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ProviderConnectionBrowser createSharedConnectionBrowser(ProviderDestination destination, String clientid, String subName, String selector, ProviderMessageReferenceHandler msgRefHandler, int quantityHint, boolean noLocal) throws JMSException {
/* 3070 */     if (Trace.isOn) {
/* 3071 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "createSharedConnectionBrowser(ProviderDestination,String,String,String,ProviderMessageReferenceHandler,int,boolean)", new Object[] { destination, clientid, subName, selector, msgRefHandler, 
/*      */             
/* 3073 */             Integer.valueOf(quantityHint), Boolean.valueOf(noLocal) });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 3078 */     String resolvedQMName = getStringProperty("XMSC_WMQ_RESOLVED_QUEUE_MANAGER");
/* 3079 */     String streamName = getStringProperty("XMSC_WMQ_BROKER_PUBQ");
/*      */     
/* 3081 */     String fqSubName = WMQSession.createFullSubscriptionName(clientid, subName, resolvedQMName, streamName, false, true);
/* 3082 */     WMQConnectionBrowser browser = new WMQConnectionBrowser(this, destination, fqSubName, selector, true, false, msgRefHandler, quantityHint, noLocal);
/* 3083 */     if (Trace.isOn) {
/* 3084 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "createSharedConnectionBrowser(ProviderDestination,String,String,String,ProviderMessageReferenceHandler,int,boolean)", browser);
/*      */     }
/*      */     
/* 3087 */     return browser;
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
/*      */   public ProviderConnectionBrowser createSharedDurableConnectionBrowser(ProviderDestination destination, String clientid, String subName, String selector, ProviderMessageReferenceHandler msgRefHandler, int quantityHint, boolean noLocal) throws JMSException {
/* 3099 */     if (Trace.isOn) {
/* 3100 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "createSharedDurableConnectionBrowser(ProviderDestination,String,String,String,ProviderMessageReferenceHandler,int,boolean)", new Object[] { destination, clientid, subName, selector, msgRefHandler, 
/*      */             
/* 3102 */             Integer.valueOf(quantityHint), Boolean.valueOf(noLocal) });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 3107 */     String resolvedQMName = getStringProperty("XMSC_WMQ_RESOLVED_QUEUE_MANAGER");
/* 3108 */     String streamName = getStringProperty("XMSC_WMQ_BROKER_PUBQ");
/*      */     
/* 3110 */     String fqSubName = WMQSession.createFullSubscriptionName(clientid, subName, resolvedQMName, streamName, true, true);
/* 3111 */     WMQConnectionBrowser browser = new WMQConnectionBrowser(this, destination, fqSubName, selector, true, true, msgRefHandler, quantityHint, noLocal);
/* 3112 */     if (Trace.isOn) {
/* 3113 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "createSharedDurableConnectionBrowser(ProviderDestination,String,String,String,ProviderMessageReferenceHandler,int,boolean)", browser);
/*      */     }
/*      */     
/* 3116 */     return browser;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void reconnected() {
/* 3125 */     if (Trace.isOn) {
/* 3126 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "reconnected()");
/*      */     }
/*      */     
/* 3129 */     this.propertyRefreshNeeded = true;
/*      */     
/* 3131 */     if (Trace.isOn) {
/* 3132 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "reconnected()");
/*      */     }
/*      */   }
/*      */   
/* 3136 */   private static final Set<String> specialProperties = new HashSet<>();
/*      */   static {
/* 3138 */     specialProperties.add("XMSC_WMQ_RESOLVED_QUEUE_MANAGER");
/* 3139 */     specialProperties.add("XMSC_WMQ_RESOLVED_QUEUE_MANAGER_ID");
/*      */     
/* 3141 */     specialProperties.add("XMSC_WMQ_RESOLVED_QUEUE_SHARING_GROUP_NAME");
/* 3142 */     specialProperties.add("XMSC_WMQ_RESOLVED_CONNECTION_TAG");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getObjectProperty(String name) throws JMSException {
/* 3150 */     if (specialProperties.contains(name) && this.propertyRefreshNeeded) {
/* 3151 */       updateQmNameEtc();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3158 */     return super.getObjectProperty(name);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void lockHconn() {
/* 3167 */     if (Trace.isOn) {
/* 3168 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "lockHconn()", null);
/*      */     }
/* 3170 */     this.helper.lockHconn();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unlockHconn() {
/* 3178 */     if (Trace.isOn) {
/* 3179 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "unlockHconn()", null);
/*      */     }
/* 3181 */     this.helper.unlockHconn();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void awaitHconn() throws InterruptedException {
/* 3190 */     if (Trace.isOn) {
/* 3191 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "awaitHconn()", null);
/*      */     }
/* 3193 */     this.helper.awaitHconn();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void signalHconn() {
/* 3201 */     if (Trace.isOn) {
/* 3202 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "signalHconn()", null);
/*      */     }
/* 3204 */     this.helper.signalHconn();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean haveHconnLock() {
/* 3212 */     boolean result = this.helper.haveHconnLock();
/* 3213 */     if (Trace.isOn) {
/* 3214 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "haveHconnLock()", null);
/*      */     }
/* 3216 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void initiateConnectionStop() {
/* 3224 */     if (Trace.isOn) {
/* 3225 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "initiateConnectionStop()");
/*      */     }
/*      */     
/* 3228 */     this.helper.initiateConnectionStop();
/* 3229 */     if (Trace.isOn) {
/* 3230 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "initiateConnectionStop()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void finishConnectionStop() {
/* 3240 */     if (Trace.isOn) {
/* 3241 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "finishConnectionStop()");
/*      */     }
/*      */     
/* 3244 */     this.helper.finishConnectionStop();
/* 3245 */     if (Trace.isOn)
/* 3246 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConnection", "finishConnectionStop()"); 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\internal\WMQConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */