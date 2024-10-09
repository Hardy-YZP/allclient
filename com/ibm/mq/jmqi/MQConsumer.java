package com.ibm.mq.jmqi;

import com.ibm.mq.jmqi.handles.Hconn;
import java.nio.ByteBuffer;

public interface MQConsumer {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQConsumer.java";
  
  void consumer(Hconn paramHconn, MQMD paramMQMD, MQGMO paramMQGMO, ByteBuffer paramByteBuffer, MQCBC paramMQCBC);
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\MQConsumer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */