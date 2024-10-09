package com.ibm.msg.client.jms;

import java.util.List;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

public interface JmsSession extends JmsPropertyContext, Session {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms/src/com/ibm/msg/client/jms/JmsSession.java";
  
  JmsMessageReference recreateMessageReference(byte[] paramArrayOfbyte) throws JMSException;
  
  Message consume(byte[] paramArrayOfbyte) throws JMSException;
  
  void deliver(List<JmsMessageReference> paramList) throws JMSException;
  
  void clearMessageReferences();
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\JmsSession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */