package com.ibm.msg.client.commonservices.trace;

public interface TraceHandler {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/trace/TraceHandler.java";
  
  void publish(TraceRecord paramTraceRecord);
  
  void flush();
  
  void close() throws SecurityException;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\trace\TraceHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */