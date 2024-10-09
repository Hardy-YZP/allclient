/*      */ package com.ibm.mq.jmqi.remote.impl;
/*      */ 
/*      */ import com.ibm.mq.constants.QmgrAdvancedCapability;
/*      */ import com.ibm.mq.exits.MQCD;
/*      */ import com.ibm.mq.exits.MQCSP;
/*      */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*      */ import com.ibm.mq.jmqi.JmqiException;
/*      */ import com.ibm.mq.jmqi.JmqiObject;
/*      */ import com.ibm.mq.jmqi.JmqiUtils;
/*      */ import com.ibm.mq.jmqi.MQSCO;
/*      */ import com.ibm.mq.jmqi.handles.Hconn;
/*      */ import com.ibm.mq.jmqi.handles.Pint;
/*      */ import com.ibm.mq.jmqi.internal.Configuration;
/*      */ import com.ibm.mq.jmqi.internal.JmqiTools;
/*      */ import com.ibm.mq.jmqi.remote.api.RemoteFAP;
/*      */ import com.ibm.mq.jmqi.remote.api.RemoteHconn;
/*      */ import com.ibm.mq.jmqi.remote.api.RemoteParentHconn;
/*      */ import com.ibm.mq.jmqi.remote.exit.RemoteExitChain;
/*      */ import com.ibm.mq.jmqi.remote.rfp.RfpMQAPI;
/*      */ import com.ibm.mq.jmqi.remote.rfp.RfpSOCKACT;
/*      */ import com.ibm.mq.jmqi.remote.rfp.RfpStructure;
/*      */ import com.ibm.mq.jmqi.remote.rfp.RfpTSH;
/*      */ import com.ibm.mq.jmqi.remote.rfp.spi.RfpINSPI;
/*      */ import com.ibm.mq.jmqi.remote.rfp.spi.RfpVERBSTRUCT;
/*      */ import com.ibm.mq.jmqi.remote.rfp.spi.RfpVerbArray;
/*      */ import com.ibm.mq.jmqi.remote.util.RemoteCommsBuffer;
/*      */ import com.ibm.mq.jmqi.remote.util.RemoteCompressor;
/*      */ import com.ibm.mq.jmqi.remote.util.RemoteConstantDecoder;
/*      */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*      */ import com.ibm.msg.client.commonservices.Log.Log;
/*      */ import com.ibm.msg.client.commonservices.Utils;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.commonservices.util.Cruise;
/*      */ import java.io.PrintWriter;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.util.HashMap;
/*      */ import java.util.LinkedList;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class RemoteSession
/*      */   extends JmqiObject
/*      */ {
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/impl/RemoteSession.java";
/*      */   
/*      */   static {
/*   74 */     if (Trace.isOn) {
/*   75 */       Trace.data("com.ibm.mq.jmqi.remote.impl.RemoteSession", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/impl/RemoteSession.java");
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
/*   91 */   private RemoteConnection parentConnection = null;
/*      */ 
/*      */   
/*   94 */   private Object rcvExitLock = new RcvExitLock();
/*   95 */   private RfpTSH rcvExitLockTSH = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  100 */   private byte[] connectionId = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  106 */   private String connectionIdString = null;
/*      */ 
/*      */   
/*  109 */   private RemoteExitChain connSendExits = null;
/*      */ 
/*      */   
/*  112 */   private RemoteExitChain connRcvExits = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  117 */   private String userIdentifier = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  122 */   private byte[] accountingToken = null;
/*      */ 
/*      */   
/*      */   private volatile Throwable asyncFailure;
/*      */   
/*  127 */   private Object asyncTshLock = new AsyncTshLock();
/*      */ 
/*      */   
/*  130 */   private LinkedList<RfpTSH> asyncTshQueue = new LinkedList<>();
/*      */ 
/*      */ 
/*      */   
/*      */   private volatile boolean disconnected = false;
/*      */ 
/*      */ 
/*      */   
/*      */   private final int reconnectCycle;
/*      */ 
/*      */   
/*      */   private volatile boolean reconnectDisabled = false;
/*      */ 
/*      */   
/*      */   private boolean connectionBroken = false;
/*      */ 
/*      */   
/*      */   private boolean securityFlowsComplete = false;
/*      */ 
/*      */   
/*  150 */   private int xaState = 0;
/*      */ 
/*      */   
/*  153 */   private int rmid = -1;
/*      */ 
/*      */   
/*  156 */   private RequestEntryMutex requestEntryMutex = new RequestEntryMutex();
/*      */ 
/*      */   
/*  159 */   private RequestMessagesMutex requestMessagesMutex = new RequestMessagesMutex();
/*      */ 
/*      */   
/*  162 */   private RemoteRequestEntry exchangeRequests = null;
/*      */ 
/*      */   
/*  165 */   private int globalMessageIndex = 0;
/*      */ 
/*      */   
/*  168 */   private RemoteRequestEntry cachedExchangeRequest = null;
/*      */ 
/*      */   
/*  171 */   private int conversationId = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  178 */   private RfpStructure[] rfpStructCache = new RfpStructure[24];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  185 */   private RfpVERBSTRUCT[] spiStructCache = new RfpVERBSTRUCT[36];
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  190 */   private RfpINSPI inSpi = null;
/*      */ 
/*      */   
/*  193 */   private RemoteCommsBuffer commsBuffer = null;
/*      */ 
/*      */   
/*  196 */   private RemoteCompressor compressor = null;
/*      */ 
/*      */   
/*  199 */   private RemoteHconn hconn = null;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean reconnectionBegun = false;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean terminatedByExit = false;
/*      */ 
/*      */ 
/*      */   
/*      */   private volatile boolean endRequested = false;
/*      */ 
/*      */   
/*  214 */   private MQSCO mqsco = null;
/*      */ 
/*      */   
/*  217 */   private int traceIdentifier = -1;
/*      */   
/*  219 */   private int remoteTraceIdentifier = -1; private RfpVerbArray[] verbArrays; private int rmtReqEntMaxPollTime; void setExits(RemoteExitChainPair exitChainPair) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "setExits(RemoteExitChainPair)", new Object[] { exitChainPair });  this.connSendExits = exitChainPair.getConnSendExits(); this.connRcvExits = exitChainPair.getConnRcvExits(); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "setExits(RemoteExitChainPair)");  } public void checkIfDisconnected() throws JmqiException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "checkIfDisconnected()");  RemoteHconn rcnParent = this.hconn; if (this.disconnected && (rcnParent == null || rcnParent.hasFailed())) { JmqiException e = new JmqiException(this.env, -1, null, 2, 2009, this.asyncFailure); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "checkIfDisconnected()", (Throwable)e);  throw e; }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "checkIfDisconnected()");  } public RfpVERBSTRUCT getSpiStruct(int spiVerb, int structType) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "getSpiStruct(int,int)", new Object[] { RemoteConstantDecoder.formatSingleOption(spiVerb, "rfpVB_"), RfpVERBSTRUCT.decodeVerbStructType(structType) });  RfpVERBSTRUCT traceRet1 = this.spiStructCache[(spiVerb - 1) * 3 + structType]; if (Trace.isOn) Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "getSpiStruct(int,int)", traceRet1);  return traceRet1; } void processReceivedTsh(RemoteTls tls, RfpTSH rTSHP) throws JmqiException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "processReceivedTsh(RemoteTls,RfpTSH)", new Object[] { tls, rTSHP });  RfpTSH rTSH = rTSHP; if (this.connRcvExits != null && this.securityFlowsComplete) { byte[] rcvdBuffer = rTSH.getRfpBuffer(); lockRcvExitForTSH(rTSH); try { rTSH = this.connRcvExits.callRcvExit(tls, this.parentConnection, this, rTSH); } catch (JmqiException jmqe) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "processReceivedTsh(RemoteTls,RfpTSH)", (Throwable)jmqe);  switch (jmqe.getReason()) { case 2059: case 2063: case 2537: disconnect(tls); break; }  } finally { if (Trace.isOn) Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "processReceivedTsh(RemoteTls,RfpTSH)");  if (rcvdBuffer == rTSH.getRfpBuffer()) unlockRcvExitForTSH(rTSH);  }  }  int curMsgCompression = this.parentConnection.getCurMsgCompression(); if ((rTSH.getControlFlags2() & 0x1) != 0 || (rTSH.getControlFlags2() & 0x2) != 0) this.compressor.decompressMsgSegment(rTSH, curMsgCompression, this.parentConnection.getMaxTransmissionSize(), isSwap());  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "processReceivedTsh(RemoteTls,RfpTSH)");  } private void lockRcvExitForTSH(RfpTSH rTSH) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "lockRcvExitForTSH(RfpTSH)", new Object[] { rTSH });  synchronized (this.rcvExitLock) { while (this.rcvExitLockTSH != null) { try { this.rcvExitLock.wait(); } catch (InterruptedException e) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "lockRcvExitForTSH(RfpTSH)", e);  }  }  this.rcvExitLockTSH = rTSH; }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "lockRcvExitForTSH(RfpTSH)");  } private void unlockRcvExitForTSH(RfpTSH rTSH) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "unlockRcvExitForTSH(RfpTSH)", new Object[] { rTSH });  synchronized (this.rcvExitLock) { if (this.rcvExitLockTSH == rTSH) { this.rcvExitLockTSH = null; this.rcvExitLock.notify(); }  }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "unlockRcvExitForTSH(RfpTSH)");  } public void putSpiStruct(int spiVerb, int structType, RfpVERBSTRUCT struct) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "putSpiStruct(int,int,RfpVERBSTRUCT)", new Object[] { RemoteConstantDecoder.formatSingleOption(spiVerb, "rfpVB_"), RfpVERBSTRUCT.decodeVerbStructType(structType), struct });  this.spiStructCache[(spiVerb - 1) * 3 + structType] = struct; if (Trace.isOn) Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "putSpiStruct(int,int,RfpVERBSTRUCT)");  } public RfpStructure getRfp(int rfpStructIndex, RfpStructure parentRfp, int offset) throws JmqiException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "getRfp(int,RfpStructure,int)", new Object[] { Integer.valueOf(rfpStructIndex), parentRfp, Integer.valueOf(offset) });  byte[] parentBuffer = null; if (parentRfp != null) parentBuffer = parentRfp.getRfpBuffer();  if (rfpStructIndex < 0 || rfpStructIndex > 23) { JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2195, null); HashMap<String, Object> ffstInfo = new HashMap<>(); ffstInfo.put("rfpStructIndex", Integer.valueOf(rfpStructIndex)); ffstInfo.put("Description", "Invalid RFP structure index"); Trace.ffst(this, "getRfp(int,RfpStructure,int)", "01", ffstInfo, null); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "getRfp(int,RfpStructure,int)", (Throwable)traceRet1);  throw traceRet1; }  if (this.rfpStructCache[rfpStructIndex] == null) { this.rfpStructCache[rfpStructIndex] = RfpStructure.newRfp(this.env, rfpStructIndex, parentBuffer, offset); } else { this.rfpStructCache[rfpStructIndex].setRfpBuffer(parentBuffer); this.rfpStructCache[rfpStructIndex].setRfpOffset(offset); }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "getRfp(int,RfpStructure,int)", this.rfpStructCache[rfpStructIndex]);  return this.rfpStructCache[rfpStructIndex]; } public RfpTSH allocateTSH(int segmentType, int requestId, RfpTSH tshP) throws JmqiException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "allocateTSH(int,int,RfpTSH)", new Object[] { RemoteConstantDecoder.decodeSingleOption(segmentType, "rfpTST_"), RemoteConstantDecoder.formatRequestId(requestId), tshP });  RfpTSH tsh = tshP; RemoteConnection connection = this.parentConnection; if (this.parentConnection.isMultiplexingEnabled()) { tsh = connection.allocateTSH(2, segmentType, tsh); tsh.setConvId(this.conversationId); tsh.setRequestId(requestId); } else { tsh = connection.allocateTSH(0, segmentType, tsh); }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "allocateTSH(int,int,RfpTSH)", tsh);  return tsh; } void deliverTSH(RfpTSH rTSH) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "deliverTSH(RfpTSH)", new Object[] { rTSH });  synchronized (this.asyncTshLock) { this.asyncTshQueue.addLast(rTSH); this.asyncTshLock.notify(); }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "deliverTSH(RfpTSH)");  }
/*      */   private RfpTSH receiveAsyncTsh() throws JmqiException { RfpTSH rTSH; if (Trace.isOn) Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "receiveAsyncTsh()");  synchronized (this.asyncTshLock) { while (!this.connectionBroken && this.asyncTshQueue.isEmpty() && this.asyncFailure == null && this.parentConnection.getAsyncFailure() == null) { try { long startTime = System.currentTimeMillis(); long waitTime = 5000L; this.asyncTshLock.wait(waitTime); if (System.currentTimeMillis() >= startTime + waitTime && !getParentConnection().isConnected()) { this.asyncTshQueue.clear(); JmqiException traceRet2 = new JmqiException(this.env, 9213, null, 2, 2009, null); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "receiveAsyncTsh()", (Throwable)traceRet2, 1);  throw traceRet2; }  } catch (InterruptedException e) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "receiveAsyncTsh()", e);  }  }  if (this.connectionBroken || this.asyncTshQueue.isEmpty()) { this.connectionBroken = true; RemoteHconn hconnCopy = this.hconn; if (hconnCopy != null) hconnCopy.raiseEventForReason(2009);  JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2009, this.asyncFailure); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "receiveAsyncTsh()", (Throwable)traceRet1, 2);  throw traceRet1; }  rTSH = this.asyncTshQueue.removeFirst(); }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "receiveAsyncTsh()", rTSH);  return rTSH; }
/*      */   public void asyncFailureNotify(Throwable e) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "asyncFailureNotify(Throwable)", new Object[] { e });  synchronized (this.asyncTshLock) { setAsyncFailure(e); this.asyncTshLock.notifyAll(); }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "asyncFailureNotify(Throwable)");  }
/*      */   public void setAsyncFailure(Throwable e) { if (Trace.isOn) Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "setAsyncFailure(Throwable)", "setter", e);  synchronized (this.asyncTshLock) { this.asyncFailure = e; this.asyncTshQueue.clear(); }  synchronized (this.requestEntryMutex) { RemoteRequestEntry entry = this.exchangeRequests; while (entry != null) { synchronized (entry) { entry.notify(); }  entry = entry.getNext(); }  }  }
/*      */   public void sendTSH(RemoteTls tls, RfpTSH tsh) throws JmqiException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "sendTSH(RemoteTls,RfpTSH)", new Object[] { tls, tsh });  sendTSH(tls, tsh, -1); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "sendTSH(RemoteTls,RfpTSH)");  }
/*      */   public void sendTSH(RemoteTls tls, RfpTSH tsh, int passwordOffset) throws JmqiException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "sendTSH(RemoteTls,RfpTSH,int)", new Object[] { tls, tsh, Integer.valueOf(passwordOffset) });  if (isEndRequested()) { JmqiException e = new JmqiException(this.env, -1, null, 2, 2009, this.asyncFailure); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "sendTSH(RemoteTls,RfpTSH,int)", (Throwable)e);  throw e; }  RemoteConnection connection = this.parentConnection; int usrDataBuffLen = calculateUserDataBufferLength(tsh, isMultiplexingEnabled()); if (usrDataBuffLen > 0) { int buffLen = (tsh.getRfpBuffer()).length; int msgLen = tsh.getTransLength(); if (isMultiplexingEnabled()) msgLen = msgLen + 3 & 0xFFFFFFFC;  if (usrDataBuffLen + msgLen > buffLen) { byte[] newTshBuff = new byte[usrDataBuffLen + msgLen]; System.arraycopy(tsh.getRfpBuffer(), 0, newTshBuff, 0, msgLen); tsh.setRfpBuffer(newTshBuff); }  byte[] tshBuffer = tsh.getRfpBuffer(); int tshOffset = tsh.getRfpOffset(); int bytesInTshBuffer = tsh.getTransLength(); if (isMultiplexingEnabled()) bytesInTshBuffer = bytesInTshBuffer + 3 & 0xFFFFFFFC;  if (tsh.getUserDataBuffer() != null) { System.arraycopy(tsh.getUserDataBuffer(), 0, tshBuffer, tshOffset + bytesInTshBuffer, tsh.getUserDataLength()); bytesInTshBuffer += tsh.getUserDataLength(); if (isMultiplexingEnabled()) bytesInTshBuffer = bytesInTshBuffer + 3 & 0xFFFFFFFC;  tsh.setTransLength(bytesInTshBuffer); tsh.setControlFlags1(tsh.getControlFlags1() | 0x10 | 0x20); } else if (tsh.getUserDataBuffers() != null) { ByteBuffer[] mBuffs = tsh.getUserDataBuffers(); for (int buffIndex = 0; buffIndex < tsh.getNumUserDataBuffers(); buffIndex++) { byte[] buffData = mBuffs[buffIndex].array(); int buffDataLen = mBuffs[buffIndex].remaining(); int buffDataOff = mBuffs[buffIndex].position(); System.arraycopy(buffData, buffDataOff, tshBuffer, tshOffset + bytesInTshBuffer, buffDataLen - buffDataOff); bytesInTshBuffer += buffDataLen - buffDataOff; }  if (isMultiplexingEnabled()) bytesInTshBuffer = bytesInTshBuffer + 3 & 0xFFFFFFFC;  tsh.setTransLength(bytesInTshBuffer); tsh.setControlFlags1(tsh.getControlFlags1() | 0x10 | 0x20); }  }  tsh.setUserDataSingle(null, 0); tsh.setUserDataMulti(null, 0); connection.sendTSH(tls, tsh, this, passwordOffset); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "sendTSH(RemoteTls,RfpTSH,int)");  }
/*      */   public RfpTSH receiveTSH(RemoteTls tls, RfpTSH partialTSH) throws JmqiException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "receiveTSH(RemoteTls,RfpTSH)", new Object[] { tls, partialTSH });  RemoteConnection connection = this.parentConnection; RfpTSH rTSH = null; if (connection.isMultiplexingEnabled()) { rTSH = receiveAsyncTsh(); } else { rTSH = connection.receiveTSH(this, tls, partialTSH); }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "receiveTSH(RemoteTls,RfpTSH)", rTSH);  return rTSH; }
/*      */   public boolean reconnectDisabled() { if (Trace.isOn) Trace.data(this, "reconnectDisabled()", "getter", Boolean.valueOf(this.reconnectDisabled));  return this.reconnectDisabled; }
/*      */   private RfpTSH receiveOneTSH(RemoteTls tls) throws JmqiException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "receiveOneTSH(RemoteTls)", new Object[] { tls });  int dataAvailable = 0; int size = 0; RfpTSH rTSH = new RfpTSH(this.env, null, 0); while (rTSH != null) { if (this.commsBuffer != null && (dataAvailable = this.commsBuffer.getDataAvailable()) >= 8) { int dataUsed = this.commsBuffer.getDataUsed(); rTSH.setRfpBuffer(this.commsBuffer.getBuffer()); rTSH.setRfpOffset(dataUsed); rTSH.setParentBuffer(this.commsBuffer); rTSH.checkEyecatcher(); size = rTSH.getTransLength(); if (size <= dataAvailable) { this.commsBuffer.addUseCount(1); this.commsBuffer.setDataAvailable(dataAvailable - size); this.commsBuffer.setDataUsed(dataUsed + size); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "receiveOneTSH(RemoteTls)", rTSH, 1);  return rTSH; }  if (size > (this.commsBuffer.getBuffer()).length) { HashMap<String, Object> ffstInfo = new HashMap<>(); ffstInfo.put("TSH transmission length", Integer.valueOf(size)); ffstInfo.put("Buffer capacity", Integer.valueOf((this.commsBuffer.getBuffer()).length)); ffstInfo.put("Maximum transmission length", Integer.valueOf(this.parentConnection.getMaxTransmissionSize())); ffstInfo.put("Description", "TSH segment size exceeds buffer capacity"); Trace.ffst(this, "receiveOneTSH(RemoteTls)", "01", ffstInfo, null); JmqiException traceRet1 = new JmqiException(this.env, 9523, new String[] { null, null, this.parentConnection.getChannelName() }, 2, 2195, null); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "receiveOneTSH(RemoteTls)", (Throwable)traceRet1, 1);  throw traceRet1; }  }  RemoteCommsBuffer newBuffer = (getRemoteFap().getTls()).receiveBufferPool.allocBuffer(this.parentConnection.getMaxTransmissionSize() + 8); if (dataAvailable != 0) { System.arraycopy(this.commsBuffer.getBuffer(), this.commsBuffer.getDataUsed(), newBuffer.getBuffer(), 0, dataAvailable); newBuffer.setDataAvailable(dataAvailable); }  if (this.commsBuffer != null) this.commsBuffer.free();  this.commsBuffer = newBuffer; int bytesRead = receiveBuffer(); if (bytesRead < 0) rTSH = null;  }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "receiveOneTSH(RemoteTls)", null, 2);  return null; }
/*  228 */   public RemoteSession(JmqiEnvironment env, RemoteConnection connection) { super(env);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2403 */     this.verbArrays = null; if (Trace.isOn) Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "<init>(JmqiEnvironment,RemoteConnection)", new Object[] { env, connection });  Configuration reqConfig = env.getConfiguration(); this.rmtReqEntMaxPollTime = reqConfig.getIntValue(Configuration.ENV_MQRMTREQ_POLLTIME); this.parentConnection = connection; this.reconnectCycle = connection.getReconnectCycle(); this.traceIdentifier = connection.getTraceIdentifier(); this.remoteTraceIdentifier = connection.getRemoteTraceIdentifier(); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "<init>(JmqiEnvironment,RemoteConnection)");  }
/*      */   private int receiveBuffer() throws JmqiException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "receiveBuffer()");  int availableBeforeRecv = this.commsBuffer.getDataAvailable(); int bytesInBuffer = availableBeforeRecv + this.commsBuffer.getDataUsed(); int bytesRead = 0; byte[] commsArray = this.commsBuffer.getBuffer(); try { bytesRead = this.parentConnection.receive(commsArray, bytesInBuffer, commsArray.length - bytesInBuffer); } catch (JmqiException e) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "receiveBuffer()", (Throwable)e);  JmqiException traceRet1 = new JmqiException(this.env, 9208, new String[] { Integer.toString(bytesRead), Integer.toHexString(bytesRead), this.parentConnection.getRemoteHostDescr(), this.parentConnection.getTrpType() }, 2, 2009, null); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "receiveBuffer()", (Throwable)traceRet1, 1);  throw traceRet1; }  if (bytesRead < 0) { JmqiException traceRet2 = new JmqiException(this.env, 9208, new String[] { Integer.toString(bytesRead), Integer.toHexString(bytesRead), this.parentConnection.getRemoteHostDescr(), this.parentConnection.getTrpType() }, 2, 2009, null); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "receiveBuffer()", (Throwable)traceRet2, 2);  throw traceRet2; }  if (bytesRead > 0) { this.commsBuffer.setDataAvailable(availableBeforeRecv + bytesRead); if (this.parentConnection.getSecureKeyResetCount() > 0 && this.parentConnection.isSecure()) this.parentConnection.setBytesSinceKeyReset(this.parentConnection.getBytesSinceKeyReset() + bytesRead);  if (this.parentConnection.getFapLevel() >= 8 && this.parentConnection.getBytesSinceKeyReset() > this.parentConnection.getSecureKeyResetCount() && this.parentConnection.isSecure()) this.parentConnection.setHeartbeatKeyResetRequired(true);  }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "receiveBuffer()", Integer.valueOf(bytesRead));  return bytesRead; }
/*      */   void deliverExchangeReply(RemoteTls tls, int requestId, RfpTSH tsh) throws JmqiException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "deliverExchangeReply(RemoteTls,int,RfpTSH)", new Object[] { tls, Integer.valueOf(requestId), tsh });  processReceivedTsh(tls, tsh); synchronized (this.requestEntryMutex) { RemoteRequestEntry entry = this.exchangeRequests; while (entry != null && entry.getRequestId() != requestId) entry = entry.getNext();  if (entry == null) { HashMap<String, Object> ffstInfo = new HashMap<>(); ffstInfo.put("requestId", Integer.valueOf(requestId)); ffstInfo.put("Description", "No request with this id found"); Trace.ffst(this, "deliverExchangeReply(RemoteTls,int,RfpTSH)", "01", ffstInfo, null); JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2195, null); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "deliverExchangeReply(RemoteTls,int,RfpTSH)", (Throwable)traceRet1);  throw traceRet1; }  synchronized (entry) { entry.setReply(tsh); entry.notify(); }  }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "deliverExchangeReply(RemoteTls,int,RfpTSH)");  }
/*      */   private RemoteRequestEntry allocateRequestEntry() { RemoteRequestEntry newEntry; if (Trace.isOn) Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "allocateRequestEntry()");  synchronized (this.requestEntryMutex) { if (this.cachedExchangeRequest != null) { newEntry = this.cachedExchangeRequest; this.cachedExchangeRequest = null; newEntry.setReply(null); } else { newEntry = new RemoteRequestEntry(this.env); }  int newRequestId = 3; for (RemoteRequestEntry currentRequest = this.exchangeRequests; currentRequest != null; currentRequest = currentRequest.getNext()) { if (currentRequest.getRequestId() >= newRequestId) newRequestId = currentRequest.getRequestId() + 2;  }  newEntry.setRequestId(newRequestId); if (this.exchangeRequests != null) this.exchangeRequests.setPrev(newEntry);  newEntry.setNext(this.exchangeRequests); newEntry.setPrev(null); this.exchangeRequests = newEntry; }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "allocateRequestEntry()", newEntry);  return newEntry; }
/*      */   private void releaseRequestEntry(RemoteRequestEntry entry) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "releaseRequestEntry(RemoteRequestEntry)", new Object[] { entry });  synchronized (this.requestEntryMutex) { RemoteRequestEntry prev = entry.getPrev(); RemoteRequestEntry next = entry.getNext(); if (prev == null) { this.exchangeRequests = next; } else { prev.setNext(next); }  if (next != null) next.setPrev(prev);  if (this.cachedExchangeRequest == null) this.cachedExchangeRequest = entry;  }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "releaseRequestEntry(RemoteRequestEntry)");  }
/*      */   void startConversation(RemoteTls tls) throws JmqiException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "startConversation(RemoteTls)", new Object[] { tls });  boolean swap = this.parentConnection.isSwap(); this.traceIdentifier = RemoteFAP.getUniqueTraceIdentifier(); RfpTSH sTSH = this.parentConnection.allocateTSH(1, 12, null); sTSH.setTransLength(sTSH.hdrSize() + 20); RfpSOCKACT sockAct = new RfpSOCKACT(this.env, sTSH.getRfpBuffer(), sTSH.getRfpOffset() + sTSH.hdrSize()); sockAct.setConversationId(this.conversationId, swap); sockAct.setRequestId(0, swap); sockAct.setType(1, swap); sockAct.setParm1(Utils.getThreadId(), swap); sockAct.setParm2(this.traceIdentifier, swap); this.parentConnection.sendTSH(tls, sTSH, null); RfpTSH rTSH = receiveTSH(tls, null); try { HashMap<String, Object> ffstInfo; JmqiException traceRet3; switch (rTSH.getSegmentType()) { case 5: if ((rTSH.getControlFlags1() & 0x2) != 0) { JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2273, null); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "startConversation(RemoteTls)", (Throwable)traceRet1, 1);  throw traceRet1; }  break;case 12: sockAct = new RfpSOCKACT(this.env, rTSH.getRfpBuffer(), rTSH.getRfpOffset() + rTSH.hdrSize()); if (sockAct.getType(swap) != 8) { HashMap<String, Object> hashMap = new HashMap<>(); hashMap.put("SegmentType", Integer.valueOf(rTSH.getSegmentType())); hashMap.put("getType", Integer.valueOf(sockAct.getType(swap))); hashMap.put("Description", "Unknown SockACT from server for START_CONV"); Trace.ffst(this, "startConversation(RemoteTls)", "01", hashMap, null); JmqiException traceRet2 = new JmqiException(this.env, -1, null, 2, 2273, null); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "startConversation(RemoteTls)", (Throwable)traceRet2, 2);  throw traceRet2; }  this.remoteTraceIdentifier = sockAct.getParm2(swap); break;default: ffstInfo = new HashMap<>(); ffstInfo.put("SegmentType", Integer.valueOf(rTSH.getSegmentType())); ffstInfo.put("getType", Integer.valueOf(sockAct.getType(swap))); ffstInfo.put("Description", "Unknown SegmentType from server for START_CONV"); Trace.ffst(this, "startConversation(RemoteTls)", "02", ffstInfo, null); traceRet3 = new JmqiException(this.env, -1, null, 2, 2273, null); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "startConversation(RemoteTls)", (Throwable)traceRet3, 3);  throw traceRet3; }  } finally { if (Trace.isOn) Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "startConversation(RemoteTls)");  releaseReceivedTSH(rTSH); }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "startConversation(RemoteTls)");  }
/*      */   public RfpTSH exchangeTSH(RemoteTls tls, RfpTSH requestTsh) throws JmqiException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "exchangeTSH(RemoteTls,RfpTSH)", new Object[] { tls, requestTsh });  if (this.disconnected) { JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2009, this.asyncFailure); this.connectionBroken = true; RemoteHconn hconnCopy = this.hconn; if (hconnCopy != null) hconnCopy.raiseEventForReason(2009);  if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "exchangeTSH(RemoteTls,RfpTSH)", (Throwable)traceRet1, 1);  throw traceRet1; }  RemoteRequestEntry request = allocateRequestEntry(); requestTsh.setRequestId(request.getRequestId()); requestTsh.setControlFlags1(requestTsh.getControlFlags1() | 0x1); sendTSH(tls, requestTsh); if (Trace.isOn) Trace.data(this.env, "exchangeTSH(RemoteTls,RfpTSH)", "Polling RemoteRequest Value = ", String.valueOf(this.rmtReqEntMaxPollTime));  synchronized (request) { while (true) { try { if (request.getReply() == null) { if (this.asyncFailure != null) { if (this.asyncFailure instanceof JmqiException) { if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "exchangeTSH(RemoteTls,RfpTSH)", this.asyncFailure, 3);  throw (JmqiException)this.asyncFailure; }  JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2009, this.asyncFailure); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "exchangeTSH(RemoteTls,RfpTSH)", (Throwable)traceRet1, 2);  throw traceRet1; }  if (this.disconnected) { JmqiException e = new JmqiException(this.env, -1, null, 2, 2009, null); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "exchangeTSH(RemoteTls,RfpTSH)", (Throwable)e, 4);  throw e; }  request.wait(this.rmtReqEntMaxPollTime); continue; }  } catch (InterruptedException e) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "exchangeTSH(RemoteTls,RfpTSH)", e);  HashMap<String, Object> ffstInfo = new HashMap<>(); ffstInfo.put("requestId", Integer.valueOf(request.getRequestId())); ffstInfo.put("request", request.toString()); ffstInfo.put("Description", "Interrupted while waiting for exchange reply"); Trace.ffst(this, "exchangeTSH(RemoteTls,RfpTSH)", "01", ffstInfo, null); JmqiException traceRet2 = new JmqiException(this.env, -1, null, 2, 2195, null); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "exchangeTSH(RemoteTls,RfpTSH)", (Throwable)traceRet2, 3);  throw traceRet2; }  break; }  }  RfpTSH replyTsh = request.getReply(); releaseRequestEntry(request); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "exchangeTSH(RemoteTls,RfpTSH)", replyTsh);  return replyTsh; }
/*      */   public void releaseReceivedTSH(RfpTSH rTSH) throws JmqiException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "releaseReceivedTSH(RfpTSH)", new Object[] { rTSH });  RemoteConnection connection = null; try { connection = this.parentConnection; } finally { if (Trace.isOn) Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "releaseReceivedTSH(RfpTSH)");  unlockRcvExitForTSH(rTSH); }  if (connection != null) connection.releaseReceivedTSH(rTSH);  if (Trace.isOn)
/*      */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "releaseReceivedTSH(RfpTSH)");  }
/*      */   public RfpMQAPI allocateMQAPI(int segmentType) throws JmqiException { if (Trace.isOn)
/*      */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "allocateMQAPI(int)", new Object[] { RemoteConstantDecoder.decodeSingleOption(segmentType, "rfpTST_") });  RfpMQAPI mqAPI = allocateMQAPI(segmentType, false); if (Trace.isOn)
/* 2414 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "allocateMQAPI(int)", mqAPI);  return mqAPI; } public RfpVerbArray getVerbArray(int verbId, Pint compCode, Pint reason, boolean enterCall) { if (Trace.isOn)
/* 2415 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "getVerbArray(int,com.ibm.mq.jmqi.handles.Pint,com.ibm.mq.jmqi.handles.Pint,boolean)", new Object[] {
/*      */             
/* 2417 */             RemoteConstantDecoder.decodeSingleOption(verbId, "RfpVB_"), compCode, reason, Boolean.valueOf(enterCall)
/*      */           }); 
/* 2419 */     if (this.verbArrays == null) {
/* 2420 */       if (Trace.isOn) {
/* 2421 */         Trace.data(this, "getVerbArray(int,com.ibm.mq.jmqi.handles.Pint,com.ibm.mq.jmqi.handles.Pint,boolean)", "arrays was null", null);
/*      */       }
/* 2423 */       this.verbArrays = getRemoteFap().spiQuerySpi(this, compCode, reason, enterCall);
/* 2424 */       if (Trace.isOn) {
/* 2425 */         Trace.data(this, "getVerbArray(int,com.ibm.mq.jmqi.handles.Pint,com.ibm.mq.jmqi.handles.Pint,boolean)", "spiQuerySpi CC = ", compCode);
/* 2426 */         Trace.data(this, "getVerbArray(int,com.ibm.mq.jmqi.handles.Pint,com.ibm.mq.jmqi.handles.Pint,boolean)", "spiQuerySpi RC = ", reason);
/*      */       } 
/*      */     } 
/* 2429 */     if (this.verbArrays != null) {
/* 2430 */       compCode.x = 0;
/* 2431 */       reason.x = 0;
/* 2432 */       for (int i = 0; i < this.verbArrays.length; i++) {
/* 2433 */         if (this.verbArrays[i].getVerbId() == verbId) {
/*      */           
/* 2435 */           if (Trace.isOn) {
/* 2436 */             Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "getVerbArray(int,com.ibm.mq.jmqi.handles.Pint,com.ibm.mq.jmqi.handles.Pint,boolean)", this.verbArrays[i], 1);
/*      */           }
/*      */ 
/*      */           
/* 2440 */           return this.verbArrays[i];
/*      */         } 
/*      */       } 
/*      */     } 
/* 2444 */     RfpVerbArray traceRet1 = RfpVerbArray.getDummy(this.env);
/* 2445 */     if (Trace.isOn) {
/* 2446 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "getVerbArray(int,com.ibm.mq.jmqi.handles.Pint,com.ibm.mq.jmqi.handles.Pint,boolean)", traceRet1, 2);
/*      */     }
/*      */ 
/*      */     
/* 2450 */     return traceRet1; }
/*      */   public RfpMQAPI allocateMQAPI(int segmentType, boolean isSpiNotifyCall) throws JmqiException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "allocateMQAPI(int,boolean)", new Object[] { RemoteConstantDecoder.decodeSingleOption(segmentType, "rfpTST_"), Boolean.valueOf(isSpiNotifyCall) });  RemoteConnection connection = this.parentConnection; RfpMQAPI mqAPI = null; if (isSpiNotifyCall) { mqAPI = (RfpMQAPI)getRfp(23, null, 0); } else { mqAPI = (RfpMQAPI)getRfp(1, null, 0); }  if (connection.isMultiplexingEnabled()) { connection.allocateTSH(2, segmentType, (RfpTSH)mqAPI); mqAPI.setConvId(this.conversationId); mqAPI.setRequestId(0); } else { connection.allocateTSH(0, segmentType, (RfpTSH)mqAPI); }  mqAPI.setReasonCode(0, connection.isSwap()); mqAPI.setCompCode(0, connection.isSwap()); mqAPI.setHandle(0, connection.isSwap()); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "allocateMQAPI(int,boolean)", mqAPI);  return mqAPI; }
/*      */   public RfpMQAPI receiveMQIFlow(RemoteTls tls) throws JmqiException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "receiveMQIFlow(RemoteTls)", new Object[] { tls });  RfpTSH receivedTSH = null; do { receivedTSH = receiveTSH(tls, null); } while (receivedTSH.getSegmentType() == 5); RfpMQAPI mqAPI = (RfpMQAPI)getRfp(1, (RfpStructure)receivedTSH, 0); mqAPI.setParentBuffer(receivedTSH.getParentBuffer()); mqAPI.setTransLength(receivedTSH.getTransLength()); mqAPI.setTshType(receivedTSH.getTshType()); mqAPI.setRfpBuffer(receivedTSH.getRfpBuffer()); mqAPI.setRfpOffset(receivedTSH.getRfpOffset()); if (mqAPI.getTransLength() < mqAPI.hdrSize()) { HashMap<String, Object> ffstInfo = new HashMap<>(); ffstInfo.put("transLength", Integer.valueOf(mqAPI.getTransLength())); ffstInfo.put("hdrSize", Integer.valueOf(mqAPI.hdrSize())); ffstInfo.put("tshType", Integer.valueOf(mqAPI.getTshType())); ffstInfo.put("segmentType", Integer.valueOf(mqAPI.getSegmentType())); StringBuffer dumpBuffer = new StringBuffer(mqAPI.getTransLength() * 10); JmqiUtils.hexDump(mqAPI.getRfpBuffer(), null, mqAPI.getRfpOffset(), mqAPI.getTransLength(), dumpBuffer); ffstInfo.put("contents", dumpBuffer.toString()); ffstInfo.put("Description", "Received MQI structure not valid"); Trace.ffst(this, "receiveMQIFlow(RemoteTls)", "01", ffstInfo, null); JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2195, null); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "receiveMQIFlow(RemoteTls)", (Throwable)traceRet1);  throw traceRet1; }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "receiveMQIFlow(RemoteTls)", mqAPI);  return mqAPI; }
/*      */   public RfpMQAPI receive1stGetReplyTSH(RemoteTls tls) throws JmqiException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "receive1stGetReplyTSH(RemoteTls)", new Object[] { tls });  RfpTSH rTSH = null; rTSH = receiveOneTSH(tls); processReceivedTsh(tls, rTSH); RfpMQAPI mqAPI = (RfpMQAPI)getRfp(1, (RfpStructure)rTSH, 0); mqAPI.setParentBuffer(rTSH.getParentBuffer()); mqAPI.setTransLength(rTSH.getTransLength()); mqAPI.setTshType(rTSH.getTshType()); mqAPI.setRfpBuffer(rTSH.getRfpBuffer()); mqAPI.setRfpOffset(rTSH.getRfpOffset()); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "receive1stGetReplyTSH(RemoteTls)", mqAPI);  return mqAPI; }
/*      */   public RfpTSH receiveNextGetReplyTSH(RemoteTls tls) throws JmqiException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "receiveNextGetReplyTSH(RemoteTls)", new Object[] { tls });  RfpTSH rTSH = receiveOneTSH(tls); processReceivedTsh(tls, rTSH); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "receiveNextGetReplyTSH(RemoteTls)", rTSH);  return rTSH; }
/*      */   public int receiveSpannedMQIData(RemoteTls tls, RfpMQAPI firstMQIFlow, int headerLengthP, ByteBuffer dataBuffer, int dataLength) throws JmqiException { RfpTSH rfpTSH; if (Trace.isOn) Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "receiveSpannedMQIData(RemoteTls,RfpMQAPI,int,ByteBuffer,int)", new Object[] { tls, firstMQIFlow, Integer.valueOf(headerLengthP), dataBuffer, Integer.valueOf(dataLength) });  RfpMQAPI rfpMQAPI = firstMQIFlow; int segmentType = firstMQIFlow.getSegmentType(); int msgBytesRead = 0; int headerLength = headerLengthP; int msgBytesAvailable = rfpMQAPI.getTransLength() - headerLength; byte[] data = dataBuffer.array(); boolean receivedTSH = false; try { while (msgBytesAvailable > 0) { if (msgBytesRead + msgBytesAvailable > dataLength) { if (Trace.isOn) { Trace.data(this.env, "receiveSpannedMQIData(RemoteTls,RfpMQAPI,int,ByteBuffer,int)", "Available data", Integer.toString(msgBytesRead + msgBytesAvailable)); Trace.data(this.env, "receiveSpannedMQIData(RemoteTls,RfpMQAPI,int,ByteBuffer,int)", "Data length", Integer.toString(dataLength)); }  msgBytesAvailable = dataLength - msgBytesRead; }  byte[] commsBuffer = rfpMQAPI.getRfpBuffer(); int startPos = rfpMQAPI.getRfpOffset() + headerLength; System.arraycopy(commsBuffer, startPos, data, msgBytesRead, msgBytesAvailable); msgBytesRead += msgBytesAvailable; if ((rfpMQAPI.getControlFlags1() & 0x20) == 0) { if (receivedTSH) { releaseReceivedTSH((RfpTSH)rfpMQAPI); rfpMQAPI = null; }  rfpTSH = receiveTSH(tls, null); receivedTSH = true; headerLength = rfpTSH.hdrSize(); if (rfpTSH.getSegmentType() != segmentType || (rfpTSH.getControlFlags1() & 0x10) != 0) { HashMap<String, Object> ffstInfo = new HashMap<>(); ffstInfo.put("SegmentType", Integer.valueOf(rfpTSH.getSegmentType())); ffstInfo.put("ControlFlags1", Integer.valueOf(rfpTSH.getControlFlags1())); ffstInfo.put("Description", "Invalid follow on TSH received"); Trace.ffst(this, "receiveSpannedMQIData(RemoteTls,RfpMQAPI,int,ByteBuffer,int)", "01", ffstInfo, null); JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2195, null); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "receiveSpannedMQIData(RemoteTls,RfpMQAPI,int,ByteBuffer,int)", (Throwable)traceRet1);  throw traceRet1; }  msgBytesAvailable = rfpTSH.getTransLength() - headerLength; continue; }  msgBytesAvailable = 0; }  } finally { if (Trace.isOn) Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "receiveSpannedMQIData(RemoteTls,RfpMQAPI,int,ByteBuffer,int)");  if (receivedTSH && rfpTSH != null) releaseReceivedTSH(rfpTSH);  }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "receiveSpannedMQIData(RemoteTls,RfpMQAPI,int,ByteBuffer,int)", Integer.valueOf(msgBytesRead));  return msgBytesRead; }
/*      */   public boolean isDisconnected() { if (Trace.isOn) Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "isDisconnected()", "getter", Boolean.valueOf(this.disconnected));  return this.disconnected; }
/* 2457 */   public void disconnect(RemoteTls tls) throws JmqiException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "disconnect(RemoteTls)", new Object[] { tls });  synchronized (this.requestMessagesMutex) { if (!this.disconnected && this.parentConnection != null) { MQCD mqcd = this.parentConnection.getNegotiatedChannel(); int fapLevel = this.parentConnection.getFapLevel(); String remProductId = this.parentConnection.getRemoteProductId(); try { if (this.connRcvExits != null) this.connRcvExits.termExits(mqcd, fapLevel, remProductId);  if (this.connSendExits != null) this.connSendExits.termExits(mqcd, fapLevel, remProductId);  } catch (JmqiException je) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "disconnect(RemoteTls)", (Throwable)je);  if (je.getReason() != 2009) { if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "disconnect(RemoteTls)", (Throwable)je);  throw je; }  }  this.parentConnection.removeSession(tls, this, true); setDisconnected(); }  }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "disconnect(RemoteTls)");  } public void terminate() throws JmqiException { RemoteConnection connection = this.parentConnection; if (connection != null) connection.terminate();  } void setDisconnected() { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "setDisconnected()");  this.disconnected = true; if (this.asyncFailure != null) this.connectionBroken = true;  RemoteHconn hconnCopy = this.hconn; if (hconnCopy != null) hconnCopy.setDisconnected(this.asyncFailure);  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "setDisconnected()");  } public void setConnectionId(byte[] connectionId) { if (Trace.isOn) Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "setConnectionId(byte [ ])", "setter", connectionId);  this.connectionId = connectionId; this.connectionIdString = JmqiTools.arrayToHexString(connectionId); } public String toString() { StringBuffer retString = new StringBuffer(); retString.append(getClass().getName()); retString.append(String.format("[:/%x]", new Object[] { Integer.valueOf(System.identityHashCode(this)) })); retString.append("[connectionId="); retString.append(JmqiTools.arrayToHexString(this.connectionId)); retString.append(",traceIdentifier="); retString.append(this.traceIdentifier); retString.append(",remoteTraceIdentifier="); retString.append(this.remoteTraceIdentifier); retString.append("]"); String traceRet1 = retString.toString(); return traceRet1; } public String getUserIdentifier() { if (Trace.isOn) Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "getUserIdentifier()", "getter", this.userIdentifier);  return this.userIdentifier; } public void setUserIdentifier(String userIdentifier) { if (Trace.isOn) Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "setUserIdentifier(String)", "setter", userIdentifier);  this.userIdentifier = userIdentifier; } public byte[] getAccountingToken() { if (Trace.isOn) Trace.data(this, "RemoteHconn.getAccountingToken()", this.accountingToken);  return this.accountingToken; } public void setAccountingToken(byte[] accountingToken) { if (Trace.isOn) Trace.data(this, "RemoteHconn.setAccountingToken(byte [ ])", accountingToken);  this.accountingToken = accountingToken; } public int getGlobalMessageIndex() { if (Trace.isOn) Trace.data(this, "RemoteHconn.getGlobalMessageIndex()", Integer.valueOf(this.globalMessageIndex));  return this.globalMessageIndex; } public void setGlobalMessageIndex(int globalMessageIndex) { if (Trace.isOn) Trace.data(this, "RemoteHconn.setGlobalMessageIndex(int)", Integer.valueOf(globalMessageIndex));  this.globalMessageIndex = globalMessageIndex; } public long getMaximumMessageLength() throws JmqiException { RemoteConnection connection = this.parentConnection; long traceRet1 = connection.getMaximumMessageLength(); if (Trace.isOn) Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "getMaximumMessageLength()", "getter", Long.valueOf(traceRet1));  return traceRet1; } public int getFapLevel() throws JmqiException { int traceRet1 = this.parentConnection.getFapLevel(); if (Trace.isOn) Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "getFapLevel()", "getter", Integer.valueOf(traceRet1));  return traceRet1; } public boolean isDistListCapable() throws JmqiException { RemoteConnection connection = this.parentConnection; boolean traceRet1 = connection.isDistListCapable(); if (Trace.isOn) Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "isDistListCapable()", "getter", Boolean.valueOf(traceRet1));  return traceRet1; } public RemoteTagPool getIdTagPool() throws JmqiException { RemoteConnection connection = this.parentConnection; RemoteTagPool traceRet1 = connection.getIdTagPool(); if (Trace.isOn) Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "getIdTagPool()", "getter", traceRet1);  return traceRet1; } public String getRemoteQmgrName() throws JmqiException { RemoteConnection connection = this.parentConnection; String traceRet1 = connection.getRemoteQmgrName(); if (Trace.isOn) Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "getRemoteQmgrName()", "getter", traceRet1);  return traceRet1; } public int getXaState() { if (Trace.isOn) Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "getXaState()", "getter", Integer.valueOf(this.xaState));  return this.xaState; } public void setXaState(int xaState) { if (Trace.isOn) Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "setXaState(int)", "setter", Integer.valueOf(xaState));  this.xaState = xaState; } public int getRmid() { if (Trace.isOn) Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "getRmid()", "getter", Integer.valueOf(this.rmid));  return this.rmid; } public void setRmid(int rmid) { if (Trace.isOn) Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "setRmid(int)", "setter", Integer.valueOf(rmid));  this.rmid = rmid; } public int getPlatform() throws JmqiException { int platform = this.parentConnection.getQMgrInfo((Hconn)this.hconn).getPlatform(); if (Trace.isOn) Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "getPlatform()", "getter", Integer.valueOf(platform));  return platform; } public int getCcsid() throws JmqiException { int traceRet1 = this.parentConnection.getCp().getCCSID(); if (Trace.isOn) Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "getCcsid()", "getter", Integer.valueOf(traceRet1));  return traceRet1; } public int getCmdLevel() throws JmqiException { int commandLevel = this.parentConnection.getQMgrInfo((Hconn)this.hconn).getCommandLevel(); if (Trace.isOn) Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "getCmdLevel()", "getter", Integer.valueOf(commandLevel));  return commandLevel; } public QmgrAdvancedCapability getQmgrAdvancedCapability() throws JmqiException { QmgrAdvancedCapability traceRet1; int advCap = this.parentConnection.getQMgrInfo((Hconn)this.hconn).getAdvCap(); if (advCap == 1) { traceRet1 = QmgrAdvancedCapability.SUPPORTED; } else if (advCap == -1) { traceRet1 = QmgrAdvancedCapability.UNKNOWN; } else { traceRet1 = QmgrAdvancedCapability.NOT_SUPPORTED; }  if (Trace.isOn) Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteSession", "getQmgrAdvancedCapability()", "getter", traceRet1);  return traceRet1; } public int getMQEncoding() throws JmqiException { RemoteConnection connection = this.parentConnection; int traceRet1 = connection.getRemoteMQEncoding(); if (Trace.isOn) Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "getMQEncoding()", "getter", Integer.valueOf(traceRet1));  return traceRet1; } public String getName() throws JmqiException { String name = this.parentConnection.getQMgrInfo((Hconn)this.hconn).getName(); if (Trace.isOn) Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "getName()", "getter", name);  return name; } public String getUid() throws JmqiException { String uid = ""; if (getFapLevel() < 10) { uid = this.parentConnection.getQMgrInfo((Hconn)this.hconn).getUid(); } else { uid = this.parentConnection.getRemoteQMID(); }  if (Trace.isOn) Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "getUid()", "getter", uid);  return uid; } public String getOldUid() { String uid = this.parentConnection.getRemoteQMID(); if (Trace.isOn) Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "getOldUid()", "getter", uid);  return uid; } public int getConversationId() { if (Trace.isOn) Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "getConversationId()", "getter", Integer.valueOf(this.conversationId));  return this.conversationId; } public void setConversationId(int conversationId) { if (Trace.isOn) Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "setConversationId(int)", "setter", Integer.valueOf(conversationId));  this.conversationId = conversationId; } public RemoteFAP getRemoteFap() { RemoteFAP traceRet1 = this.parentConnection.getRemoteFap(); if (Trace.isOn) Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "getRemoteFap()", "getter", traceRet1);  return traceRet1; } public boolean isMultiplexingEnabled() throws JmqiException { RemoteConnection connection = this.parentConnection; boolean traceRet1 = connection.isMultiplexingEnabled(); if (Trace.isOn) Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "isMultiplexingEnabled()", "getter", Boolean.valueOf(traceRet1));  return traceRet1; } public JmqiCodepage getCp() { return this.parentConnection.getCp(); } public boolean isSwap() { return this.parentConnection.isSwap(); } public RemoteConnection getParentConnection() { if (Trace.isOn) Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "getParentConnection()", "getter", this.parentConnection);  return this.parentConnection; } public void setParentConnection(RemoteConnection parentConnection) { if (Trace.isOn) Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "setParentConnection(RemoteConnection)", "setter", parentConnection);  this.parentConnection = parentConnection; } private int calculateUserDataBufferLength(RfpTSH tsh, boolean multiplexingEnabled) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "calculateUserDataBufferLength(RfpTSH,boolean)", new Object[] { tsh, Boolean.valueOf(multiplexingEnabled) });  int usrDataBuffLen = 0; if (tsh.getUserDataBuffer() != null) { if (multiplexingEnabled) { usrDataBuffLen = tsh.getUserDataLength() + 3 & 0xFFFFFFFC; } else { usrDataBuffLen = tsh.getUserDataLength(); }  } else if (tsh.getUserDataBuffers() != null) { ByteBuffer[] mBuffs = tsh.getUserDataBuffers(); for (int buffIndex = 0; buffIndex < tsh.getNumUserDataBuffers(); buffIndex++) { int buffDataLen = mBuffs[buffIndex].remaining(); int buffDataOff = mBuffs[buffIndex].position(); usrDataBuffLen += buffDataLen - buffDataOff; }  if (multiplexingEnabled) usrDataBuffLen = usrDataBuffLen + 3 & 0xFFFFFFFC;  }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "calculateUserDataBufferLength(RfpTSH,boolean)", Integer.valueOf(usrDataBuffLen));  return usrDataBuffLen; } public int getNumberOfSharingConversations() { int result = -1; if (this.parentConnection != null) { MQCD cd = this.parentConnection.getNegotiatedChannel(); if (cd != null) result = cd.getSharingConversations();  }  if (Trace.isOn) Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "getNumberOfSharingConversations()", "getter", Integer.valueOf(result));  return result; } public void setInSpi(RfpINSPI inSpi) { if (Trace.isOn) {
/* 2458 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "setInSpi(RfpINSPI)", "setter", inSpi);
/*      */     }
/*      */     
/* 2461 */     this.inSpi = inSpi; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RfpINSPI getInSpi() {
/* 2468 */     if (Trace.isOn) {
/* 2469 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "getInSpi()", "getter", this.inSpi);
/*      */     }
/* 2471 */     return this.inSpi;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setHconn(RemoteHconn hconn) {
/* 2478 */     if (Trace.isOn) {
/* 2479 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "setHconn(RemoteHconn)", "setter", hconn);
/*      */     }
/*      */     
/* 2482 */     this.hconn = hconn;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isReconnectionBegun() {
/* 2489 */     if (Trace.isOn) {
/* 2490 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "isReconnectionBegun()", "getter", 
/* 2491 */           Boolean.valueOf(this.reconnectionBegun));
/*      */     }
/* 2493 */     return this.reconnectionBegun;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReconnectionBegun() {
/* 2500 */     if (Trace.isOn) {
/* 2501 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "setReconnectionBegun()");
/*      */     }
/* 2503 */     this.reconnectionBegun = true;
/* 2504 */     if (Trace.isOn) {
/* 2505 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "setReconnectionBegun()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getConnectionId() {
/* 2515 */     if (Trace.isOn) {
/* 2516 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "getConnectionId()", "getter", this.connectionId);
/*      */     }
/*      */     
/* 2519 */     return this.connectionId;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getConnectionIdAsString() {
/* 2527 */     if (Trace.isOn) {
/* 2528 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "getConnectionIdAsString()", "getter", this.connectionIdString);
/*      */     }
/*      */     
/* 2531 */     return this.connectionIdString;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMqsco(MQSCO mqsco) {
/* 2538 */     if (Trace.isOn) {
/* 2539 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "setMqsco(MQSCO)", "setter", mqsco);
/*      */     }
/*      */     
/* 2542 */     this.mqsco = mqsco;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQSCO getMqsco() {
/* 2549 */     if (Trace.isOn) {
/* 2550 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "getMqsco()", "getter", this.mqsco);
/*      */     }
/* 2552 */     return this.mqsco;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RemoteHconn getHconn() {
/* 2559 */     RemoteHconn hconnCopy = this.hconn;
/* 2560 */     if (Trace.isOn) {
/* 2561 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "getHconn()", "getter", hconnCopy);
/*      */     }
/*      */     
/* 2564 */     return hconnCopy;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static class RequestEntryMutex
/*      */   {
/*      */     RequestEntryMutex() {
/* 2576 */       if (Trace.isOn) {
/* 2577 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RequestEntryMutex", "<init>()");
/*      */       }
/* 2579 */       if (Trace.isOn) {
/* 2580 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RequestEntryMutex", "<init>()");
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
/*      */   private static class RequestMessagesMutex
/*      */   {
/*      */     RequestMessagesMutex() {
/* 2595 */       if (Trace.isOn) {
/* 2596 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RequestMessagesMutex", "<init>()");
/*      */       }
/* 2598 */       if (Trace.isOn) {
/* 2599 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RequestMessagesMutex", "<init>()");
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
/*      */   private static class AsyncTshLock
/*      */   {
/*      */     AsyncTshLock() {
/* 2614 */       if (Trace.isOn) {
/* 2615 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.AsyncTshLock", "<init>()");
/*      */       }
/* 2617 */       if (Trace.isOn) {
/* 2618 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.AsyncTshLock", "<init>()");
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
/*      */   private static class RcvExitLock
/*      */   {
/*      */     RcvExitLock() {
/* 2633 */       if (Trace.isOn) {
/* 2634 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RcvExitLock", "<init>()");
/*      */       }
/* 2636 */       if (Trace.isOn) {
/* 2637 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RcvExitLock", "<init>()");
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
/*      */   public void configureCompression(int compLevel) throws JmqiException {
/* 2650 */     if (Trace.isOn)
/* 2651 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "configureCompression(int)", new Object[] {
/* 2652 */             Integer.valueOf(compLevel)
/*      */           }); 
/* 2654 */     this.compressor = new RemoteCompressor(this.env, compLevel);
/*      */     
/* 2656 */     if (Trace.isOn) {
/* 2657 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "configureCompression(int)");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   byte[] compress(RfpTSH sendTSH) throws JmqiException {
/* 2663 */     if (Trace.isOn) {
/* 2664 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "compress(RfpTSH)", new Object[] { sendTSH });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2669 */     byte[] compressedSegment = this.compressor.compressMsgSegment(sendTSH, this.parentConnection.getCurHdrCompression(), this.parentConnection.getCurMsgCompression(), this.parentConnection
/* 2670 */         .getMaxTransmissionSize(), isSwap());
/* 2671 */     if (Trace.isOn) {
/* 2672 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "compress(RfpTSH)", compressedSegment);
/*      */     }
/*      */     
/* 2675 */     return compressedSegment;
/*      */   }
/*      */   
/*      */   boolean hasSendExits() {
/* 2679 */     if (Trace.isOn) {
/* 2680 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "hasSendExits()");
/*      */     }
/* 2682 */     boolean retval = (this.connSendExits != null);
/* 2683 */     if (Trace.isOn) {
/* 2684 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "hasSendExits()", 
/* 2685 */           Boolean.valueOf(retval));
/*      */     }
/* 2687 */     return retval;
/*      */   }
/*      */   
/*      */   RfpTSH callSendExits(RfpTSH sendTSH) throws JmqiException {
/* 2691 */     if (Trace.isOn) {
/* 2692 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "callSendExits(RfpTSH)", new Object[] { sendTSH });
/*      */     }
/*      */ 
/*      */     
/* 2696 */     RfpTSH retval = null;
/* 2697 */     if (this.securityFlowsComplete) {
/* 2698 */       retval = this.connSendExits.callSendExit(getRemoteFap().getTls(), getParentConnection(), this, sendTSH);
/*      */     } else {
/*      */       
/* 2701 */       retval = sendTSH;
/*      */     } 
/* 2703 */     if (Trace.isOn) {
/* 2704 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "callSendExits(RfpTSH)", retval);
/*      */     }
/*      */     
/* 2707 */     return retval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void deliverConnectionBroken(Throwable e, String qmName, String qmId) throws JmqiException {
/* 2717 */     if (Trace.isOn) {
/* 2718 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "deliverConnectionBroken(Throwable, String, String)", new Object[] { e, qmName, qmId });
/*      */     }
/*      */     
/* 2721 */     RemoteHconn targetHconn = this.hconn;
/*      */     
/* 2723 */     if (targetHconn != null) {
/*      */ 
/*      */       
/* 2726 */       RemoteParentHconn remoteParentHconn1, remoteParentHconn2 = targetHconn.getParent();
/* 2727 */       if (remoteParentHconn2 != null) {
/* 2728 */         remoteParentHconn1 = remoteParentHconn2;
/*      */       }
/*      */       
/* 2731 */       if (qmName == null) {
/* 2732 */         remoteParentHconn1.deliverConnectionBroken(e, null, null);
/*      */ 
/*      */       
/*      */       }
/* 2736 */       else if (remoteParentHconn1.isReconnectable()) {
/*      */         
/* 2738 */         remoteParentHconn1.deliverConnectionBroken(e, qmName, qmId);
/*      */       }
/*      */       else {
/*      */         
/* 2742 */         HashMap<String, Object> logInserts = new HashMap<>();
/* 2743 */         logInserts.put("XMSC_HCONN", remoteParentHconn1.toString());
/* 2744 */         Log.log(this, "deliverConnectionBroken(Throwable, String, String)", "JMSCS0054", logInserts);
/*      */       
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 2751 */       asyncFailureNotify(e);
/*      */     } 
/* 2753 */     if (Trace.isOn) {
/* 2754 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "deliverConnectionBroken(Throwable, String, String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void qmQuiescing() throws JmqiException {
/* 2765 */     if (Trace.isOn) {
/* 2766 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "qmQuiescing()");
/*      */     }
/* 2768 */     RemoteHconn hconnCopy = this.hconn;
/* 2769 */     if (hconnCopy != null) {
/* 2770 */       hconnCopy.qmQuiescing();
/*      */     }
/* 2772 */     if (Trace.isOn) {
/* 2773 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "qmQuiescing()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void chlQuiescing() throws JmqiException {
/* 2783 */     if (Trace.isOn) {
/* 2784 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "chlQuiescing()");
/*      */     }
/* 2786 */     RemoteHconn hconnCopy = this.hconn;
/* 2787 */     if (hconnCopy != null) {
/* 2788 */       hconnCopy.chlQuiescing();
/*      */     }
/* 2790 */     if (Trace.isOn) {
/* 2791 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "chlQuiescing()");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   void disableReconnect() {
/* 2797 */     if (Trace.isOn) {
/* 2798 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "disableReconnect()");
/*      */     }
/*      */     
/* 2801 */     this.reconnectDisabled = true;
/*      */     
/* 2803 */     RemoteHconn hconnCopy = this.hconn;
/* 2804 */     if (hconnCopy != null) {
/*      */       try {
/* 2806 */         hconnCopy.eligibleForReconnect(false);
/*      */       }
/* 2808 */       catch (JmqiException e2) {
/* 2809 */         if (Trace.isOn) {
/* 2810 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "disableReconnect()", (Throwable)e2);
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 2816 */     if (Trace.isOn) {
/* 2817 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "disableReconnect()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTerminatedByExit(boolean b) {
/* 2826 */     if (Trace.isOn) {
/* 2827 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "setTerminatedByExit(boolean)", "setter", 
/* 2828 */           Boolean.valueOf(b));
/*      */     }
/* 2830 */     this.terminatedByExit = b;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isTerminatedByExit() {
/* 2837 */     if (Trace.isOn) {
/* 2838 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "isTerminatedByExit()", "getter", 
/* 2839 */           Boolean.valueOf(this.terminatedByExit));
/*      */     }
/* 2841 */     return this.terminatedByExit;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isEndRequested() {
/* 2848 */     if (Trace.isOn) {
/* 2849 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "isEndRequested()", "getter", 
/* 2850 */           Boolean.valueOf(this.endRequested));
/*      */     }
/* 2852 */     return this.endRequested;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEndRequested(boolean b) {
/* 2859 */     if (Trace.isOn) {
/* 2860 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "setEndRequested(boolean)", "setter", 
/* 2861 */           Boolean.valueOf(b));
/*      */     }
/* 2863 */     this.endRequested = b;
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
/*      */   @Cruise("This method used to have the signature: setQuiescing()")
/*      */   public void notifyHconnOfQuieceEvent() {
/* 2881 */     if (Trace.isOn) {
/* 2882 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "notifyHconnOfQuieceEvent()");
/*      */     }
/* 2884 */     RemoteHconn hconnCopy = this.hconn;
/* 2885 */     if (hconnCopy != null) {
/* 2886 */       hconnCopy.setQuiescing();
/* 2887 */       hconnCopy.wakeGetters();
/* 2888 */       hconnCopy.wakeDispatchThread();
/*      */     } 
/*      */     
/* 2891 */     if (Trace.isOn) {
/* 2892 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "notifyHconnOfQuieceEvent()");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   boolean connectionBroken() {
/* 2898 */     if (Trace.isOn) {
/* 2899 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "connectionBroken()");
/*      */     }
/* 2901 */     if (Trace.isOn) {
/* 2902 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "connectionBroken()", 
/* 2903 */           Boolean.valueOf(this.connectionBroken));
/*      */     }
/* 2905 */     return this.connectionBroken;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class RemoteRequestEntry
/*      */     extends JmqiObject
/*      */   {
/*      */     private RemoteRequestEntry next;
/*      */ 
/*      */ 
/*      */     
/*      */     private RemoteRequestEntry prev;
/*      */ 
/*      */ 
/*      */     
/*      */     private int requestId;
/*      */ 
/*      */     
/* 2925 */     private RfpTSH reply = null;
/*      */     
/*      */     RemoteRequestEntry(JmqiEnvironment env) {
/* 2928 */       super(env);
/* 2929 */       if (Trace.isOn) {
/* 2930 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteRequestEntry", "<init>(JmqiEnvironment)", new Object[] { env });
/*      */       }
/*      */       
/* 2933 */       if (Trace.isOn) {
/* 2934 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteRequestEntry", "<init>(JmqiEnvironment)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     RemoteRequestEntry getNext() {
/* 2941 */       if (Trace.isOn) {
/* 2942 */         Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteRequestEntry", "getNext()", "getter", this.next);
/*      */       }
/*      */       
/* 2945 */       return this.next;
/*      */     }
/*      */     
/*      */     void setNext(RemoteRequestEntry next) {
/* 2949 */       if (Trace.isOn) {
/* 2950 */         Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteRequestEntry", "setNext(RemoteRequestEntry)", "setter", next);
/*      */       }
/*      */       
/* 2953 */       this.next = next;
/*      */     }
/*      */     
/*      */     RemoteRequestEntry getPrev() {
/* 2957 */       if (Trace.isOn) {
/* 2958 */         Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteRequestEntry", "getPrev()", "getter", this.prev);
/*      */       }
/*      */       
/* 2961 */       return this.prev;
/*      */     }
/*      */     
/*      */     void setPrev(RemoteRequestEntry prev) {
/* 2965 */       if (Trace.isOn) {
/* 2966 */         Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteRequestEntry", "setPrev(RemoteRequestEntry)", "setter", prev);
/*      */       }
/*      */       
/* 2969 */       this.prev = prev;
/*      */     }
/*      */     
/*      */     void setRequestId(int requestId) {
/* 2973 */       if (Trace.isOn) {
/* 2974 */         Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteRequestEntry", "setRequestId(int)", "setter", 
/* 2975 */             Integer.valueOf(requestId));
/*      */       }
/* 2977 */       this.requestId = requestId;
/*      */     }
/*      */     
/*      */     int getRequestId() {
/* 2981 */       if (Trace.isOn) {
/* 2982 */         Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteRequestEntry", "getRequestId()", "getter", 
/* 2983 */             Integer.valueOf(this.requestId));
/*      */       }
/* 2985 */       return this.requestId;
/*      */     }
/*      */     
/*      */     RfpTSH getReply() {
/* 2989 */       if (Trace.isOn) {
/* 2990 */         Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteRequestEntry", "getReply()", "getter", this.reply);
/*      */       }
/*      */       
/* 2993 */       return this.reply;
/*      */     }
/*      */     
/*      */     void setReply(RfpTSH reply) {
/* 2997 */       if (Trace.isOn) {
/* 2998 */         Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteRequestEntry", "setReply(RfpTSH)", "setter", reply);
/*      */       }
/*      */       
/* 3001 */       this.reply = reply;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Hconn getParentHconn() {
/*      */     RemoteParentHconn remoteParentHconn;
/* 3010 */     Hconn result = null;
/* 3011 */     RemoteHconn hconnCopy = this.hconn;
/* 3012 */     if (hconnCopy != null) {
/* 3013 */       remoteParentHconn = hconnCopy.getParent();
/*      */     }
/* 3015 */     if (Trace.isOn) {
/* 3016 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "getParentHconn()", "getter", remoteParentHconn);
/*      */     }
/*      */     
/* 3019 */     return (Hconn)remoteParentHconn;
/*      */   }
/*      */ 
/*      */   
/*      */   public void initOAMUserAuth(RemoteTls tls, MQCSP mqcsp) throws JmqiException {
/* 3024 */     if (Trace.isOn) {
/* 3025 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "initOAMUserAuth(RemoteTls, MQCSP)", new Object[] { tls, mqcsp });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 3030 */       this.parentConnection.initOAMUserAuth(tls, mqcsp, this);
/*      */     
/*      */     }
/* 3033 */     catch (JmqiException jmqie) {
/* 3034 */       if (Trace.isOn) {
/* 3035 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "initOAMUserAuth(RemoteTls, MQCSP)", (Throwable)jmqie, 1);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 3041 */         disconnect(tls);
/*      */       }
/* 3043 */       catch (JmqiException discJmqie) {
/*      */ 
/*      */         
/* 3046 */         if (Trace.isOn) {
/* 3047 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "initOAMUserAuth(RemoteTls, MQCSP)", (Throwable)discJmqie, 2);
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 3053 */       if (Trace.isOn) {
/* 3054 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "initOAMUserAuth(RemoteTls, MQCSP)", (Throwable)jmqie, 1);
/*      */       }
/*      */       
/* 3057 */       throw jmqie;
/*      */     } 
/*      */     
/* 3060 */     this.securityFlowsComplete = true;
/* 3061 */     if (Trace.isOn) {
/* 3062 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "initOAMUserAuth(RemoteTls, MQCSP)");
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
/*      */   public boolean isSPISupported() throws JmqiException {
/* 3076 */     boolean traceRet1 = this.parentConnection.isSPISupported();
/*      */     
/* 3078 */     if (Trace.isOn) {
/* 3079 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "isSPISupported()", "getter", 
/* 3080 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 3082 */     return traceRet1;
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
/*      */   public String getQsgName() throws JmqiException {
/* 3095 */     String qsgName = null;
/*      */ 
/*      */     
/* 3098 */     if (getPlatform() == 1) {
/* 3099 */       qsgName = this.parentConnection.getQMgrInfo((Hconn)this.hconn).getQsgName();
/*      */     
/*      */     }
/* 3102 */     else if (Trace.isOn) {
/* 3103 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "getQsgName()", "Remote platform reported as non-z/OS, so skipping qsgName inquire");
/*      */     } 
/*      */     
/* 3106 */     if (Trace.isOn) {
/* 3107 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "getQsgName()", "getter", qsgName);
/*      */     }
/*      */     
/* 3110 */     return qsgName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void dump(PrintWriter pw, int level) {
/* 3118 */     if (Trace.isOn) {
/* 3119 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "dump(PrintWriter,int)", new Object[] { pw, 
/* 3120 */             Integer.valueOf(level) });
/*      */     }
/* 3122 */     String prefix = Trace.buildPrefix(level);
/* 3123 */     pw.format("%s%s%n", new Object[] { prefix, toString() });
/* 3124 */     RemoteHconn hconnCopy = this.hconn;
/* 3125 */     if (hconnCopy != null) {
/* 3126 */       hconnCopy.dump(pw, level + 1);
/* 3127 */       RemoteProxyQueueManager rpqm = hconnCopy.getProxyQueueManager();
/* 3128 */       if (rpqm != null) {
/* 3129 */         rpqm.dump(pw, level + 1);
/*      */       }
/*      */     } 
/* 3132 */     if (Trace.isOn) {
/* 3133 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "dump(PrintWriter,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Long generateIdentifier(int hobj) {
/* 3144 */     Long identifier = Long.valueOf(this.parentConnection.hashCode() << 32L | hobj & 0xFFFFFFFFL);
/*      */     
/* 3146 */     if (Trace.isOn) {
/* 3147 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "generateIdentifier(int)", "getter", identifier);
/*      */     }
/*      */ 
/*      */     
/* 3151 */     return identifier;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getReconnectCycle() {
/* 3156 */     if (Trace.isOn) {
/* 3157 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "getReconnectCycle()", "getter", 
/* 3158 */           Integer.valueOf(this.reconnectCycle));
/*      */     }
/* 3160 */     return this.reconnectCycle;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RequestMessagesMutex getRequestMessagesMutex() {
/* 3167 */     if (Trace.isOn) {
/* 3168 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteSession", "getRequestMessagesMutex()", "getter", this.requestMessagesMutex);
/*      */     }
/*      */     
/* 3171 */     return this.requestMessagesMutex;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\impl\RemoteSession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */