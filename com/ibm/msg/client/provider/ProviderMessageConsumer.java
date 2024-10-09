package com.ibm.msg.client.provider;

import com.ibm.msg.client.jms.JmsPropertyContext;
import java.io.PrintWriter;
import java.util.concurrent.locks.ReentrantLock;
import javax.jms.JMSException;

public interface ProviderMessageConsumer extends JmsPropertyContext {
  public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.provider/src/com/ibm/msg/client/provider/ProviderMessageConsumer.java";
  
  public static final int USE_DEFAULT = -1;
  
  void setMessageListener(ProviderMessageListener paramProviderMessageListener) throws JMSException;
  
  ProviderMessage receive(long paramLong) throws JMSException;
  
  void close(boolean paramBoolean, ReentrantLock paramReentrantLock) throws JMSException;
  
  void start(boolean paramBoolean) throws JMSException;
  
  void stop() throws JMSException;
  
  void handlePoisonMessage(ProviderMessage paramProviderMessage) throws JMSException;
  
  boolean shouldMessageBeRequeued(int paramInt, ProviderMessage paramProviderMessage) throws JMSException;
  
  ProviderMessage lockMessage(long paramLong) throws JMSException;
  
  void removeLockedMessage(ProviderMessage paramProviderMessage) throws JMSException;
  
  void unlockMessage() throws JMSException;
  
  void dump(PrintWriter paramPrintWriter, int paramInt);
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\provider\ProviderMessageConsumer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */