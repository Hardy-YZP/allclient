package com.ibm.msg.client.wmq.compat.jms.internal;

import com.ibm.msg.client.provider.ProviderMessage;
import com.ibm.msg.client.provider.ProviderMessageReference;
import javax.jms.JMSException;

interface MessageReference extends Cloneable, ProviderMessageReference {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MessageReference.java";
  
  public static final int NO_DATA = 0;
  
  public static final int HEADER_DATA = 1;
  
  public static final int FULL_DATA = 2;
  
  int getDataQuantity() throws JMSException;
  
  ProviderMessage getMessage() throws JMSException;
  
  Object clone();
  
  byte[] flatten() throws JMSException;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\MessageReference.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */