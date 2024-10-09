package com.ibm.msg.client.jms;

import java.io.Serializable;
import javax.jms.JMSException;
import javax.jms.Message;

public interface JmsMessage extends Message, Serializable {
  void updateFromMessage(Message paramMessage) throws JMSException;
  
  Message getDelegate();
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\JmsMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */