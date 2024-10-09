package com.ibm.msg.client.wmq.compat.base.internal;

import com.ibm.mq.MQException;

public interface MQQueueManagerEventListener {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQQueueManagerEventListener.java";
  
  void onConnectionBrokenException(MQQueueManager paramMQQueueManager, MQException paramMQException);
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\base\internal\MQQueueManagerEventListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */