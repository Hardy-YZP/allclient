package com.ibm.msg.client.provider;

import javax.jms.JMSException;

public interface ProviderMessageReferenceHandler {
  void endDeliver() throws JMSException;
  
  void handleMessageReference(ProviderMessageReference paramProviderMessageReference) throws JMSException;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\provider\ProviderMessageReferenceHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */