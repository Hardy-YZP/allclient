package com.ibm.msg.client.jms.internal;

import javax.jms.JMSException;

public interface JMSMessagePropertyValueConverter {
  boolean isConvertible(Object paramObject);
  
  Object convert(String paramString, Object paramObject) throws JMSException;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JMSMessagePropertyValueConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */