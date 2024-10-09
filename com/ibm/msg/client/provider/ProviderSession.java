package com.ibm.msg.client.provider;

import com.ibm.msg.client.jms.JmsPropertyContext;
import java.io.PrintWriter;
import javax.jms.JMSException;

public interface ProviderSession extends JmsPropertyContext {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.provider/src/com/ibm/msg/client/provider/ProviderSession.java";
  
  void rollback() throws JMSException;
  
  ProviderMessageProducer createProducer(ProviderDestination paramProviderDestination, JmsPropertyContext paramJmsPropertyContext) throws JMSException;
  
  ProviderMessageConsumer createConsumer(ProviderDestination paramProviderDestination, String paramString, boolean paramBoolean, JmsPropertyContext paramJmsPropertyContext) throws JMSException;
  
  ProviderMessageConsumer createDurableSubscriber(ProviderDestination paramProviderDestination, String paramString1, String paramString2, boolean paramBoolean, JmsPropertyContext paramJmsPropertyContext) throws JMSException;
  
  ProviderMessageConsumer createSharedConsumer(ProviderDestination paramProviderDestination, String paramString1, String paramString2, JmsPropertyContext paramJmsPropertyContext) throws JMSException;
  
  ProviderMessageConsumer createSharedDurableConsumer(ProviderDestination paramProviderDestination, String paramString1, String paramString2, JmsPropertyContext paramJmsPropertyContext) throws JMSException;
  
  ProviderQueueBrowser createBrowser(ProviderDestination paramProviderDestination, String paramString, JmsPropertyContext paramJmsPropertyContext) throws JMSException;
  
  ProviderDestination createTemporaryDestination(int paramInt, JmsPropertyContext paramJmsPropertyContext) throws JMSException;
  
  void commit() throws JMSException;
  
  void close(boolean paramBoolean) throws JMSException;
  
  void deleteDurableSubscriber(String paramString) throws JMSException;
  
  void enableMessageReferenceProcessing();
  
  void loadMessageReference(ProviderMessageReference paramProviderMessageReference) throws JMSException;
  
  String createMessageID();
  
  void start() throws JMSException;
  
  void stop() throws JMSException;
  
  ProviderMessageReference recreateMessageReference(byte[] paramArrayOfbyte, ProviderDestination paramProviderDestination) throws JMSException;
  
  String getDestinationURI(byte[] paramArrayOfbyte) throws JMSException;
  
  boolean isMessageAlien(ProviderMessage paramProviderMessage) throws JMSException;
  
  boolean isInGlobalTransaction();
  
  void dump(PrintWriter paramPrintWriter, int paramInt);
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\provider\ProviderSession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */