package com.ibm.mq.ese.intercept;

import com.ibm.mq.jmqi.MQGMO;
import com.ibm.mq.jmqi.MQMD;
import com.ibm.mq.jmqi.handles.Hconn;
import com.ibm.mq.jmqi.handles.Hobj;
import com.ibm.mq.jmqi.handles.PbyteBuffer;
import com.ibm.mq.jmqi.handles.Pint;
import com.ibm.mq.jmqi.system.SpiGetOptions;
import java.nio.ByteBuffer;

public interface JmqiGetInterceptor extends JmqiInterceptor {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/intercept/JmqiGetInterceptor.java";
  
  MQGetContext beforeMQGET(Hconn paramHconn, Hobj paramHobj, MQMD paramMQMD, MQGMO paramMQGMO, int paramInt, ByteBuffer paramByteBuffer, Pint paramPint1, Pint paramPint2, Pint paramPint3);
  
  void afterMQGET(MQGetContext paramMQGetContext, Hconn paramHconn, Hobj paramHobj, MQMD paramMQMD, MQGMO paramMQGMO, int paramInt, ByteBuffer paramByteBuffer, Pint paramPint1, Pint paramPint2, Pint paramPint3);
  
  MQGetContext beforeJmqiGet(Hconn paramHconn, Hobj paramHobj, MQMD paramMQMD, MQGMO paramMQGMO, int paramInt1, int paramInt2, PbyteBuffer paramPbyteBuffer, Pint paramPint1, Pint paramPint2, Pint paramPint3, Pint paramPint4);
  
  void afterJmqiGet(MQGetContext paramMQGetContext, Hconn paramHconn, Hobj paramHobj, MQMD paramMQMD, MQGMO paramMQGMO, int paramInt1, int paramInt2, PbyteBuffer paramPbyteBuffer, Pint paramPint1, Pint paramPint2, Pint paramPint3, Pint paramPint4);
  
  MQGetContext beforeJmqiGetMessage(Hconn paramHconn, Hobj paramHobj, MQMD paramMQMD, MQGMO paramMQGMO, int paramInt, ByteBuffer paramByteBuffer, Pint paramPint1, Pint paramPint2, Pint paramPint3);
  
  void afterJmqiGetMessage(MQGetContext paramMQGetContext, Hconn paramHconn, Hobj paramHobj, MQMD paramMQMD, MQGMO paramMQGMO, int paramInt, ByteBuffer paramByteBuffer, Pint paramPint1, Pint paramPint2, Pint paramPint3);
  
  MQGetContext beforeSpiGet(Hconn paramHconn, Hobj paramHobj, MQMD paramMQMD, MQGMO paramMQGMO, SpiGetOptions paramSpiGetOptions, int paramInt, ByteBuffer paramByteBuffer, Pint paramPint1, Pint paramPint2, Pint paramPint3);
  
  void afterSpiGet(MQGetContext paramMQGetContext, Hconn paramHconn, Hobj paramHobj, MQMD paramMQMD, MQGMO paramMQGMO, SpiGetOptions paramSpiGetOptions, int paramInt, ByteBuffer paramByteBuffer, Pint paramPint1, Pint paramPint2, Pint paramPint3);
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\intercept\JmqiGetInterceptor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */