package com.ibm.msg.client.provider;

import com.ibm.msg.client.jms.JmsConnectionFactory;
import com.ibm.msg.client.jms.JmsDestination;
import com.ibm.msg.client.jms.JmsPropertyContext;
import com.ibm.msg.client.jms.JmsQueue;
import com.ibm.msg.client.jms.JmsQueueConnectionFactory;
import com.ibm.msg.client.jms.JmsSession;
import com.ibm.msg.client.jms.JmsTopic;
import com.ibm.msg.client.jms.JmsTopicConnectionFactory;
import com.ibm.msg.client.jms.JmsXAConnectionFactory;
import com.ibm.msg.client.jms.JmsXAQueueConnectionFactory;
import com.ibm.msg.client.jms.JmsXATopicConnectionFactory;
import javax.jms.JMSException;

public interface ProviderJmsFactory {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.provider/src/com/ibm/msg/client/provider/ProviderJmsFactory.java";
  
  JmsQueue createQueue(String paramString) throws JMSException;
  
  JmsQueue createQueue(String paramString, JmsSession paramJmsSession) throws JMSException;
  
  JmsTopic createTopic(String paramString) throws JMSException;
  
  JmsTopic createTopic(String paramString, JmsSession paramJmsSession) throws JMSException;
  
  JmsConnectionFactory createConnectionFactory() throws JMSException;
  
  JmsConnectionFactory createConnectionFactory(String paramString) throws JMSException;
  
  JmsQueueConnectionFactory createQueueConnectionFactory() throws JMSException;
  
  JmsQueueConnectionFactory createQueueConnectionFactory(String paramString) throws JMSException;
  
  JmsTopicConnectionFactory createTopicConnectionFactory() throws JMSException;
  
  JmsTopicConnectionFactory createTopicConnectionFactory(String paramString) throws JMSException;
  
  JmsXAConnectionFactory createXAConnectionFactory() throws JMSException;
  
  JmsXAConnectionFactory createXAConnectionFactory(String paramString) throws JMSException;
  
  JmsXAQueueConnectionFactory createXAQueueConnectionFactory() throws JMSException;
  
  JmsXAQueueConnectionFactory createXAQueueConnectionFactory(String paramString) throws JMSException;
  
  JmsXATopicConnectionFactory createXATopicConnectionFactory() throws JMSException;
  
  JmsXATopicConnectionFactory createXATopicConnectionFactory(String paramString) throws JMSException;
  
  JmsDestination createDestination(String paramString) throws JMSException;
  
  JmsDestination createDestination(String paramString, JmsSession paramJmsSession) throws JMSException;
  
  JmsPropertyContext createJmsObject(short paramShort, Object paramObject) throws JMSException;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\provider\ProviderJmsFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */