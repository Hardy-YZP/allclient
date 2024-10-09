package com.ibm.msg.client.provider;

import javax.jms.JMSException;

public interface ProviderBytesMessage extends ProviderMessage {
  byte[] getBytes() throws JMSException;
  
  void setBytes(byte[] paramArrayOfbyte) throws JMSException;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\provider\ProviderBytesMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */