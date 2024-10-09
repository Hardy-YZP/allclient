package com.ibm.msg.client.provider;

import javax.jms.JMSException;

public interface ProviderObjectMessage extends ProviderMessage {
  void setSerializedObject(byte[] paramArrayOfbyte) throws JMSException;
  
  byte[] getSerializedObject() throws JMSException;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\provider\ProviderObjectMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */