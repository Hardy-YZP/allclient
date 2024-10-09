package com.ibm.mq;

interface MQConnectionEventListener {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQConnectionEventListener.java";
  
  void connectionClosed(MQManagedConnectionJ11 paramMQManagedConnectionJ11, MQQueueManager paramMQQueueManager, boolean paramBoolean);
  
  void connectionErrorOccurred(MQManagedConnectionJ11 paramMQManagedConnectionJ11, MQQueueManager paramMQQueueManager, Exception paramException);
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQConnectionEventListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */