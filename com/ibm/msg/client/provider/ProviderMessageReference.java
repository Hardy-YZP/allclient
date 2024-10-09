package com.ibm.msg.client.provider;

import javax.jms.JMSException;

public interface ProviderMessageReference extends Cloneable {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.provider/src/com/ibm/msg/client/provider/ProviderMessageReference.java";
  
  public static final int NO_DATA = 0;
  
  public static final int HEADER_DATA = 1;
  
  public static final int FULL_DATA = 2;
  
  ProviderMessage getMessage() throws JMSException;
  
  int getDataQuantity() throws JMSException;
  
  byte[] flatten() throws JMSException;
  
  Object clone();
  
  String getDestinationAsString();
  
  void setDestination(ProviderDestination paramProviderDestination);
  
  boolean isManagedQueue();
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\provider\ProviderMessageReference.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */