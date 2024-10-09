package com.ibm.msg.client.provider;

import java.util.Enumeration;
import javax.jms.JMSException;

public interface ProviderMessageFactory {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.provider/src/com/ibm/msg/client/provider/ProviderMessageFactory.java";
  
  ProviderBytesMessage createBytesMessage(ProviderSession paramProviderSession) throws JMSException;
  
  ProviderMapMessage createMapMessage(ProviderSession paramProviderSession) throws JMSException;
  
  ProviderMessage createMessage(ProviderSession paramProviderSession) throws JMSException;
  
  ProviderObjectMessage createObjectMessage(ProviderSession paramProviderSession) throws JMSException;
  
  ProviderStreamMessage createStreamMessage(ProviderSession paramProviderSession) throws JMSException;
  
  ProviderTextMessage createTextMessage(ProviderSession paramProviderSession) throws JMSException;
  
  ProviderMessage convertMessageUsingSession(ProviderMessage paramProviderMessage, ProviderSession paramProviderSession) throws JMSException;
  
  Enumeration<String> getJMSXPropertyNames() throws JMSException;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\provider\ProviderMessageFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */