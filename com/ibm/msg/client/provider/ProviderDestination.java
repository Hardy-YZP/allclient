package com.ibm.msg.client.provider;

import com.ibm.msg.client.jms.JmsPropertyContext;
import javax.jms.JMSException;

public interface ProviderDestination extends JmsPropertyContext {
  String getName();
  
  boolean isQueue();
  
  boolean isTopic();
  
  boolean isTemporary();
  
  String toURI() throws JMSException;
  
  void delete() throws JMSException;
  
  void setDestType(int paramInt);
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\provider\ProviderDestination.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */