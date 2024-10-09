package com.ibm.mq.ese.service;

import com.ibm.mq.jmqi.MQGMO;
import com.ibm.mq.jmqi.MQMD;
import com.ibm.mq.jmqi.handles.Hconn;
import com.ibm.mq.jmqi.handles.Hobj;
import com.ibm.mq.jmqi.handles.Pint;
import java.nio.ByteBuffer;

public interface EseMQService {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/service/EseMQService.java";
  
  void checkQueueExists(Hconn paramHconn, String paramString, Pint paramPint1, Pint paramPint2) throws EseMQException;
  
  long inqQmgrCcsid(Hconn paramHconn) throws EseMQException;
  
  String inqQmgrDlq(Hconn paramHconn) throws EseMQException;
  
  int inqQueueType(Hconn paramHconn, String paramString) throws EseMQException;
  
  String[] inqResolveQueue(Hconn paramHconn, int paramInt1, String paramString1, String paramString2, int paramInt2, boolean paramBoolean) throws EseMQException;
  
  String[] inqResolveQueue2(Hconn paramHconn, String paramString1, String paramString2, int paramInt) throws EseMQException;
  
  MQGMO copyGetMsgOpts(MQGMO paramMQGMO1, MQGMO paramMQGMO2);
  
  ByteBuffer getMessage(Hconn paramHconn, Hobj paramHobj, MQMD paramMQMD, MQGMO paramMQGMO, int paramInt1, int paramInt2, ByteBuffer paramByteBuffer, Pint paramPint1, Pint paramPint2, Pint paramPint3);
  
  void putInErrorq(Hconn paramHconn, String paramString1, String paramString2, String paramString3, String paramString4, MQMD paramMQMD, boolean paramBoolean, ByteBuffer paramByteBuffer, int paramInt) throws EseMQException;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\service\EseMQService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */