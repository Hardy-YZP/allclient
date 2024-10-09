package com.ibm.msg.client.provider;

import java.util.Enumeration;
import javax.jms.JMSException;

public interface ProviderMetaData {
  int getJMSMajorVersion() throws JMSException;
  
  int getJMSMinorVersion() throws JMSException;
  
  String getJMSVersion() throws JMSException;
  
  int getProviderMajorVersion() throws JMSException;
  
  int getProviderMinorVersion() throws JMSException;
  
  String getProviderVersion() throws JMSException;
  
  String getProviderName() throws JMSException;
  
  Enumeration<String> getJMSXPropertyNames() throws JMSException;
  
  String getConnectionTypeName() throws JMSException;
  
  boolean doesConnectionSupport(String paramString);
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\provider\ProviderMetaData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */