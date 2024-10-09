package com.ibm.msg.client.commonservices.Log;

public interface LogHandler {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/Log/LogHandler.java";
  
  void close() throws SecurityException;
  
  void flush();
  
  void publish(LogRecord paramLogRecord);
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\Log\LogHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */