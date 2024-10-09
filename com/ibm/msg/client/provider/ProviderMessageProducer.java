package com.ibm.msg.client.provider;

import com.ibm.msg.client.jms.JmsPropertyContext;
import java.io.PrintWriter;
import javax.jms.JMSException;

public interface ProviderMessageProducer extends JmsPropertyContext {
  public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.provider/src/com/ibm/msg/client/provider/ProviderMessageProducer.java";
  
  void close(boolean paramBoolean) throws JMSException;
  
  void send(ProviderDestination paramProviderDestination, ProviderMessage paramProviderMessage) throws JMSException;
  
  boolean providerPriorityValidate(int paramInt);
  
  boolean providerTimeToLiveValidate(long paramLong);
  
  void dump(PrintWriter paramPrintWriter, int paramInt);
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\provider\ProviderMessageProducer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */