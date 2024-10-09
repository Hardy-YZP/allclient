package com.ibm.msg.client.provider;

import javax.jms.JMSException;

public interface ProviderMatchSpace {
  boolean doesMessageMatch(Object paramObject, String paramString, ProviderMessage paramProviderMessage) throws JMSException;
  
  Object createConsumerContext(String paramString1, String paramString2) throws JMSException;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\provider\ProviderMatchSpace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */