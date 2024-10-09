package com.ibm.mq.jmqi;

import com.ibm.mq.jmqi.handles.Hconn;
import com.ibm.mq.jmqi.handles.Hmsg;
import com.ibm.mq.jmqi.handles.Hobj;
import com.ibm.mq.jmqi.handles.Phconn;
import com.ibm.mq.jmqi.handles.Phmsg;
import com.ibm.mq.jmqi.handles.Phobj;
import com.ibm.mq.jmqi.handles.Pint;
import java.nio.ByteBuffer;

public interface JmqiMQ {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/JmqiMQ.java";
  
  void MQBACK(Hconn paramHconn, Pint paramPint1, Pint paramPint2);
  
  void MQBEGIN(Hconn paramHconn, MQBO paramMQBO, Pint paramPint1, Pint paramPint2);
  
  void MQCB(Hconn paramHconn, int paramInt, MQCBD paramMQCBD, Hobj paramHobj, MQMD paramMQMD, MQGMO paramMQGMO, Pint paramPint1, Pint paramPint2);
  
  void MQCLOSE(Hconn paramHconn, Phobj paramPhobj, int paramInt, Pint paramPint1, Pint paramPint2);
  
  void MQCMIT(Hconn paramHconn, Pint paramPint1, Pint paramPint2);
  
  void MQCONN(String paramString, Phconn paramPhconn, Pint paramPint1, Pint paramPint2);
  
  void MQCONNX(String paramString, MQCNO paramMQCNO, Phconn paramPhconn, Pint paramPint1, Pint paramPint2);
  
  void MQCTL(Hconn paramHconn, int paramInt, MQCTLO paramMQCTLO, Pint paramPint1, Pint paramPint2);
  
  void MQDISC(Phconn paramPhconn, Pint paramPint1, Pint paramPint2);
  
  void MQGET(Hconn paramHconn, Hobj paramHobj, MQMD paramMQMD, MQGMO paramMQGMO, int paramInt, ByteBuffer paramByteBuffer, Pint paramPint1, Pint paramPint2, Pint paramPint3);
  
  void MQINQ(Hconn paramHconn, Hobj paramHobj, int paramInt1, int[] paramArrayOfint1, int paramInt2, int[] paramArrayOfint2, int paramInt3, byte[] paramArrayOfbyte, Pint paramPint1, Pint paramPint2);
  
  void MQOPEN(Hconn paramHconn, MQOD paramMQOD, int paramInt, Phobj paramPhobj, Pint paramPint1, Pint paramPint2);
  
  void MQPUT(Hconn paramHconn, Hobj paramHobj, MQMD paramMQMD, MQPMO paramMQPMO, int paramInt, ByteBuffer paramByteBuffer, Pint paramPint1, Pint paramPint2);
  
  void MQPUT1(Hconn paramHconn, MQOD paramMQOD, MQMD paramMQMD, MQPMO paramMQPMO, int paramInt, ByteBuffer paramByteBuffer, Pint paramPint1, Pint paramPint2);
  
  void MQSET(Hconn paramHconn, Hobj paramHobj, int paramInt1, int[] paramArrayOfint1, int paramInt2, int[] paramArrayOfint2, int paramInt3, byte[] paramArrayOfbyte, Pint paramPint1, Pint paramPint2);
  
  void MQSTAT(Hconn paramHconn, int paramInt, MQSTS paramMQSTS, Pint paramPint1, Pint paramPint2);
  
  void MQSUB(Hconn paramHconn, MQSD paramMQSD, Phobj paramPhobj1, Phobj paramPhobj2, Pint paramPint1, Pint paramPint2);
  
  @Deprecated
  void MQSUBRQ(Hconn paramHconn, Phobj paramPhobj, int paramInt, MQSRO paramMQSRO, Pint paramPint1, Pint paramPint2);
  
  void MQSUBRQ(Hconn paramHconn, Hobj paramHobj, int paramInt, MQSRO paramMQSRO, Pint paramPint1, Pint paramPint2);
  
  void authenticate(Hconn paramHconn, String paramString1, String paramString2, Pint paramPint1, Pint paramPint2);
  
  boolean isSharedHandlesSupported() throws JmqiException;
  
  int getTlsComponentId();
  
  void MQCRTMH(Hconn paramHconn, MQCMHO paramMQCMHO, Phmsg paramPhmsg, Pint paramPint1, Pint paramPint2);
  
  void MQDLTMH(Hconn paramHconn, Phmsg paramPhmsg, MQDMHO paramMQDMHO, Pint paramPint1, Pint paramPint2);
  
  void MQSETMP(Hconn paramHconn, Hmsg paramHmsg, MQSMPO paramMQSMPO, MQCHARV paramMQCHARV, MQPD paramMQPD, int paramInt1, int paramInt2, ByteBuffer paramByteBuffer, Pint paramPint1, Pint paramPint2);
  
  void MQINQMP(Hconn paramHconn, Hmsg paramHmsg, MQIMPO paramMQIMPO, MQCHARV paramMQCHARV, MQPD paramMQPD, Pint paramPint1, int paramInt, ByteBuffer paramByteBuffer, Pint paramPint2, Pint paramPint3, Pint paramPint4);
  
  void MQDLTMP(Hconn paramHconn, Hmsg paramHmsg, MQDMPO paramMQDMPO, MQCHARV paramMQCHARV, Pint paramPint1, Pint paramPint2);
  
  void MQMHBUF(Hconn paramHconn, Hmsg paramHmsg, MQMHBO paramMQMHBO, MQCHARV paramMQCHARV, MQMD paramMQMD, int paramInt, ByteBuffer paramByteBuffer, Pint paramPint1, Pint paramPint2, Pint paramPint3);
  
  void MQBUFMH(Hconn paramHconn, Hmsg paramHmsg, MQBMHO paramMQBMHO, MQMD paramMQMD, int paramInt, ByteBuffer paramByteBuffer, Pint paramPint1, Pint paramPint2, Pint paramPint3);
  
  boolean isAsyncConsumeThread(Hconn paramHconn);
  
  boolean isLocal();
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\JmqiMQ.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */