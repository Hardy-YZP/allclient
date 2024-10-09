package com.ibm.mq.jmqi.remote.util;

import com.ibm.mq.jmqi.JmqiException;

public interface RemoteCommsBuffer {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/util/RemoteCommsBuffer.java";
  
  void addUseCount(int paramInt) throws JmqiException;
  
  void free() throws JmqiException;
  
  byte[] getBuffer() throws JmqiException;
  
  int getDataAvailable() throws JmqiException;
  
  void setDataAvailable(int paramInt) throws JmqiException;
  
  int getDataPosition() throws JmqiException;
  
  void setDataPosition(int paramInt) throws JmqiException;
  
  int getDataUsed() throws JmqiException;
  
  void setDataUsed(int paramInt) throws JmqiException;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remot\\util\RemoteCommsBuffer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */