package com.ibm.msg.client.jms;

import javax.jms.Connection;
import javax.jms.JMSException;

public interface JmsConnection extends JmsPropertyContext, Connection {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms/src/com/ibm/msg/client/jms/JmsConnection.java";
  
  JmsConnectionBrowser createConnectionBrowser(JmsDestination paramJmsDestination, String paramString, JmsMessageReferenceHandler paramJmsMessageReferenceHandler, int paramInt) throws JMSException;
  
  JmsConnectionBrowser createDurableConnectionBrowser(JmsTopic paramJmsTopic, String paramString1, String paramString2, JmsMessageReferenceHandler paramJmsMessageReferenceHandler, int paramInt, boolean paramBoolean) throws JMSException;
  
  JmsConnectionBrowser createSharedConnectionBrowser(JmsTopic paramJmsTopic, String paramString1, String paramString2, JmsMessageReferenceHandler paramJmsMessageReferenceHandler, int paramInt, boolean paramBoolean) throws JMSException;
  
  JmsConnectionBrowser createSharedDurableConnectionBrowser(JmsTopic paramJmsTopic, String paramString1, String paramString2, JmsMessageReferenceHandler paramJmsMessageReferenceHandler, int paramInt, boolean paramBoolean) throws JMSException;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\JmsConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */