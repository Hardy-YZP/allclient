package com.ibm.mq.ese.intercept;

import com.ibm.mq.jmqi.MQMD;
import com.ibm.mq.jmqi.MQOD;
import com.ibm.mq.jmqi.MQPMO;
import com.ibm.mq.jmqi.handles.Hconn;
import com.ibm.mq.jmqi.handles.Hobj;
import com.ibm.mq.jmqi.handles.Pint;
import com.ibm.mq.jmqi.system.SpiPutOptions;
import com.ibm.mq.jmqi.system.zrfp.Triplet;
import java.nio.ByteBuffer;

public interface JmqiPutInterceptor extends JmqiInterceptor {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/intercept/JmqiPutInterceptor.java";
  
  MQPutContext beforeMQPUT(Hconn paramHconn, Hobj paramHobj, MQMD paramMQMD, MQPMO paramMQPMO, int paramInt, ByteBuffer paramByteBuffer, Pint paramPint1, Pint paramPint2);
  
  void afterMQPUT(MQPutContext paramMQPutContext, Hconn paramHconn, Hobj paramHobj, MQMD paramMQMD, MQPMO paramMQPMO, int paramInt, ByteBuffer paramByteBuffer, Pint paramPint1, Pint paramPint2);
  
  MQPutContext beforeJmqiPut(Hconn paramHconn, Hobj paramHobj, MQMD paramMQMD, MQPMO paramMQPMO, ByteBuffer[] paramArrayOfByteBuffer, Pint paramPint1, Pint paramPint2);
  
  void afterJmqiPut(MQPutContext paramMQPutContext, Hconn paramHconn, Hobj paramHobj, MQMD paramMQMD, MQPMO paramMQPMO, ByteBuffer[] paramArrayOfByteBuffer, Pint paramPint1, Pint paramPint2);
  
  MQPutContext beforeSpiPut(Hconn paramHconn, Hobj paramHobj, MQMD paramMQMD, MQPMO paramMQPMO, SpiPutOptions paramSpiPutOptions, int paramInt, ByteBuffer paramByteBuffer, Pint paramPint1, Pint paramPint2);
  
  void afterSpiPut(MQPutContext paramMQPutContext, Hconn paramHconn, Hobj paramHobj, MQMD paramMQMD, MQPMO paramMQPMO, SpiPutOptions paramSpiPutOptions, int paramInt, ByteBuffer paramByteBuffer, Pint paramPint1, Pint paramPint2);
  
  MQPutContext beforeSpiPut(Hconn paramHconn, Hobj paramHobj, MQMD paramMQMD, MQPMO paramMQPMO, SpiPutOptions paramSpiPutOptions, ByteBuffer[] paramArrayOfByteBuffer, Pint paramPint1, Pint paramPint2);
  
  void afterSpiPut(MQPutContext paramMQPutContext, Hconn paramHconn, Hobj paramHobj, MQMD paramMQMD, MQPMO paramMQPMO, SpiPutOptions paramSpiPutOptions, ByteBuffer[] paramArrayOfByteBuffer, Pint paramPint1, Pint paramPint2);
  
  void beforeJmqiPutWithTriplets(Hconn paramHconn, Hobj paramHobj, MQMD paramMQMD, MQPMO paramMQPMO, ByteBuffer[] paramArrayOfByteBuffer, Triplet[] paramArrayOfTriplet, Pint paramPint1, Pint paramPint2);
  
  void afterJmqiPutWithTriplets(Hconn paramHconn, Hobj paramHobj, MQMD paramMQMD, MQPMO paramMQPMO, ByteBuffer[] paramArrayOfByteBuffer, Triplet[] paramArrayOfTriplet, Pint paramPint1, Pint paramPint2);
  
  void beforeJmqiPut1WithTriplets(Hconn paramHconn, MQOD paramMQOD, MQMD paramMQMD, MQPMO paramMQPMO, ByteBuffer[] paramArrayOfByteBuffer, Triplet[] paramArrayOfTriplet, Pint paramPint1, Pint paramPint2);
  
  void afterJmqiPut1WithTriplets(Hconn paramHconn, MQOD paramMQOD, MQMD paramMQMD, MQPMO paramMQPMO, ByteBuffer[] paramArrayOfByteBuffer, Triplet[] paramArrayOfTriplet, Pint paramPint1, Pint paramPint2);
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\intercept\JmqiPutInterceptor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */