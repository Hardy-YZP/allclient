package com.ibm.msg.client.provider;

import com.ibm.msg.client.jms.JmsPropertyContext;
import javax.jms.JMSException;

public interface ProviderConnectionFactory extends JmsPropertyContext {
  ProviderConnection createProviderConnection(JmsPropertyContext paramJmsPropertyContext) throws JMSException;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\provider\ProviderConnectionFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */