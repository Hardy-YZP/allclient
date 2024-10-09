package com.ibm.msg.client.jms;

import javax.jms.JMSException;
import javax.jms.Message;

public interface JmsMessageReference extends Cloneable {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms/src/com/ibm/msg/client/jms/JmsMessageReference.java";
  
  public static final int NO_DATA = 0;
  
  public static final int HEADER_DATA = 1;
  
  public static final int FULL_DATA = 2;
  
  int getDataQuantity() throws JMSException;
  
  Message getMessage() throws JMSException;
  
  byte[] flatten() throws JMSException;
  
  Object clone();
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\JmsMessageReference.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */