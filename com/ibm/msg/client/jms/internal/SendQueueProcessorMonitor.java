package com.ibm.msg.client.jms.internal;

public interface SendQueueProcessorMonitor {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/SendQueueProcessorMonitor.java";
  
  String getProcessorState();
  
  String viewQueueContents();
  
  int getMaxSendQueueDepth();
  
  void setMaxSendQueueDepth(int paramInt);
  
  int getCurrentSendQueueDepth();
  
  long getProcessorTimeout();
  
  void setProcessorTimeout(long paramLong);
  
  void requestClose();
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\SendQueueProcessorMonitor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */