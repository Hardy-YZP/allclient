package com.ibm.mq.jms;

import javax.jms.JMSException;
import javax.jms.Message;

public interface MessageReference extends Cloneable {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MessageReference.java";
  
  public static final int FULL_DATA = 2;
  
  public static final int HEADER_DATA = 1;
  
  public static final int NO_DATA = 0;
  
  Object clone();
  
  byte[] flatten() throws JMSException;
  
  int getDataQuantity() throws JMSException;
  
  Message getMessage() throws JMSException;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MessageReference.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */