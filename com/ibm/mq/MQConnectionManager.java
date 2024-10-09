package com.ibm.mq;

interface MQConnectionManager {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQConnectionManager.java";
  
  Object allocateConnection(MQManagedConnectionFactory paramMQManagedConnectionFactory, MQConnectionRequestInfo paramMQConnectionRequestInfo) throws MQResourceException;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQConnectionManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */