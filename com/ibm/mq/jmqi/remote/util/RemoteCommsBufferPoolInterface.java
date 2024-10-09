package com.ibm.mq.jmqi.remote.util;

import com.ibm.mq.jmqi.JmqiException;

public interface RemoteCommsBufferPoolInterface {
  RemoteCommsBuffer allocBuffer(int paramInt) throws JmqiException;
  
  void freeBuffer(RemoteCommsBuffer paramRemoteCommsBuffer) throws JmqiException;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remot\\util\RemoteCommsBufferPoolInterface.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */