package com.ibm.mq.jmqi.system;

import com.ibm.mq.exits.MQCSP;
import com.ibm.mq.jmqi.JmqiException;
import com.ibm.mq.jmqi.MQCNO;
import com.ibm.mq.jmqi.MQGMO;
import com.ibm.mq.jmqi.MQMD;
import com.ibm.mq.jmqi.MQOD;
import com.ibm.mq.jmqi.MQPMO;
import com.ibm.mq.jmqi.MQSD;
import com.ibm.mq.jmqi.MQSRO;
import com.ibm.mq.jmqi.handles.Hconn;
import com.ibm.mq.jmqi.handles.Hobj;
import com.ibm.mq.jmqi.handles.PbyteBuffer;
import com.ibm.mq.jmqi.handles.Phconn;
import com.ibm.mq.jmqi.handles.Phobj;
import com.ibm.mq.jmqi.handles.Pint;
import com.ibm.mq.jmqi.system.zrfp.Triplet;
import java.nio.ByteBuffer;
import java.util.List;

public interface JmqiSP {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/JmqiSP.java";
  
  void spiConnect(String paramString, LpiPrivConnStruct paramLpiPrivConnStruct, MQCNO paramMQCNO, Phconn paramPhconn, Pint paramPint1, Pint paramPint2);
  
  void spiSubscribe(Hconn paramHconn, LpiSD paramLpiSD, MQSD paramMQSD, Phobj paramPhobj1, Phobj paramPhobj2, Pint paramPint1, Pint paramPint2);
  
  void spiUnsubscribe(Hconn paramHconn, LpiUSD paramLpiUSD, Pint paramPint1, Pint paramPint2);
  
  void jmqiCancelWaitingGets(Hconn paramHconn1, Hconn paramHconn2, Hconn paramHconn3, Pint paramPint1, Pint paramPint2);
  
  void jmqiConnect(String paramString, JmqiConnectOptions paramJmqiConnectOptions, MQCNO paramMQCNO, Hconn paramHconn, Phconn paramPhconn, Pint paramPint1, Pint paramPint2);
  
  void jmqiPut(Hconn paramHconn, Hobj paramHobj, MQMD paramMQMD, MQPMO paramMQPMO, ByteBuffer[] paramArrayOfByteBuffer, Pint paramPint1, Pint paramPint2);
  
  void jmqiPutWithTriplets(Hconn paramHconn, Hobj paramHobj, MQMD paramMQMD, MQPMO paramMQPMO, ByteBuffer[] paramArrayOfByteBuffer, Triplet[] paramArrayOfTriplet, Pint paramPint1, Pint paramPint2);
  
  void jmqiPut1(Hconn paramHconn, MQOD paramMQOD, MQMD paramMQMD, MQPMO paramMQPMO, ByteBuffer[] paramArrayOfByteBuffer, Pint paramPint1, Pint paramPint2);
  
  void jmqiPut1WithTriplets(Hconn paramHconn, MQOD paramMQOD, MQMD paramMQMD, MQPMO paramMQPMO, ByteBuffer[] paramArrayOfByteBuffer, Triplet[] paramArrayOfTriplet, Pint paramPint1, Pint paramPint2);
  
  void jmqiNotify(Hconn paramHconn1, Hconn paramHconn2, int paramInt, LpiNotifyDetails paramLpiNotifyDetails, Pint paramPint1, Pint paramPint2);
  
  void spiActivateMessage(Hconn paramHconn, SpiActivate paramSpiActivate, Pint paramPint1, Pint paramPint2);
  
  void spiGet(Hconn paramHconn, Hobj paramHobj, MQMD paramMQMD, MQGMO paramMQGMO, SpiGetOptions paramSpiGetOptions, int paramInt, ByteBuffer paramByteBuffer, Pint paramPint1, Pint paramPint2, Pint paramPint3);
  
  void spiOpen(Hconn paramHconn, MQOD paramMQOD, SpiOpenOptions paramSpiOpenOptions, Phobj paramPhobj, Pint paramPint1, Pint paramPint2);
  
  ByteBuffer jmqiGet(Hconn paramHconn, Hobj paramHobj, MQMD paramMQMD, MQGMO paramMQGMO, int paramInt1, int paramInt2, PbyteBuffer paramPbyteBuffer, Pint paramPint1, Pint paramPint2, Pint paramPint3, Pint paramPint4);
  
  boolean jmqiConvertMessage(Hconn paramHconn, Hobj paramHobj, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, MQMD paramMQMD, ByteBuffer paramByteBuffer, Pint paramPint1, int paramInt4, int paramInt5, Pint paramPint2, Pint paramPint3, Pint paramPint4);
  
  void spiPut(Hconn paramHconn, Hobj paramHobj, MQMD paramMQMD, MQPMO paramMQPMO, SpiPutOptions paramSpiPutOptions, int paramInt, ByteBuffer paramByteBuffer, Pint paramPint1, Pint paramPint2);
  
  void spiPut(Hconn paramHconn, Hobj paramHobj, MQMD paramMQMD, MQPMO paramMQPMO, SpiPutOptions paramSpiPutOptions, ByteBuffer[] paramArrayOfByteBuffer, Pint paramPint1, Pint paramPint2);
  
  void spiDefine(Hconn paramHconn, boolean paramBoolean, LexObjectSelector paramLexObjectSelector, String paramString, int paramInt1, int[] paramArrayOfint1, int paramInt2, int[] paramArrayOfint2, int paramInt3, byte[] paramArrayOfbyte, LexFilterElement paramLexFilterElement, int paramInt4, int[] paramArrayOfint3, Pint paramPint1, Pint paramPint2);
  
  void spiInquire(Hconn paramHconn, LexObjectSelector paramLexObjectSelector, int paramInt1, int[] paramArrayOfint1, int paramInt2, int[] paramArrayOfint2, int paramInt3, byte[] paramArrayOfbyte, LexFilterElement paramLexFilterElement, Pint paramPint1, Pint paramPint2);
  
  void spiDelete(Hconn paramHconn, LexObjectSelector paramLexObjectSelector, boolean paramBoolean, Pint paramPint1, Pint paramPint2);
  
  void spiSyncPoint(Hconn paramHconn, SpiSyncPointOptions paramSpiSyncPointOptions, Pint paramPint1, Pint paramPint2);
  
  void getMetaData(JmqiMetaData paramJmqiMetaData, Pint paramPint1, Pint paramPint2);
  
  void executeRunnable(Hconn paramHconn, JmqiRunnable paramJmqiRunnable) throws JmqiException, Exception;
  
  void checkCmdLevel(Hconn paramHconn) throws JmqiException;
  
  void honourRRS(Hconn paramHconn, Pint paramPint1, Pint paramPint2);
  
  void lpiSPISubscriptionRequest(Hconn paramHconn, Hobj paramHobj, int paramInt, LpiSRD paramLpiSRD, MQSRO paramMQSRO, Pint paramPint1, Pint paramPint2);
  
  List<byte[]> jmqiInquireNamedSubscribers(Hconn paramHconn, LpiCALLOPT paramLpiCALLOPT, String paramString, Pint paramPint1, Pint paramPint2);
  
  void lpiSPIInquireNamedSubscribers(Hconn paramHconn, byte[] paramArrayOfbyte1, int paramInt1, byte[] paramArrayOfbyte2, int paramInt2, byte[] paramArrayOfbyte3, Pint paramPint1, Pint paramPint2, Pint paramPint3, Pint paramPint4);
  
  void lpiSPIAdoptUser(Hconn paramHconn, int paramInt1, LexContext paramLexContext, String paramString, int paramInt2, byte[] paramArrayOfbyte1, int paramInt3, LpiAdoptUserOptions paramLpiAdoptUserOptions, MQCSP paramMQCSP, int paramInt4, byte[] paramArrayOfbyte2, Pint paramPint1, Pint paramPint2);
  
  void lpiSPICHLAUTH(Hconn paramHconn, LpiCHLAUTHQuery paramLpiCHLAUTHQuery, Pint paramPint1, Pint paramPint2);
  
  void lpiSPIMapCredentials(Hconn paramHconn, LexContext paramLexContext, String paramString1, MQCSP paramMQCSP, byte[] paramArrayOfbyte, String paramString2, String paramString3, String paramString4, Pint paramPint1, Pint paramPint2);
  
  void lpiSPICheckPrivileged(Hconn paramHconn, String paramString1, String paramString2, int paramInt, Pint paramPint1, Pint paramPint2);
  
  int getMqiId();
  
  int getReconnectCycle();
  
  boolean isCICS();
  
  boolean isIMS();
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\JmqiSP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */