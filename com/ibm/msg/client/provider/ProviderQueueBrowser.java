package com.ibm.msg.client.provider;

import com.ibm.msg.client.jms.JmsPropertyContext;
import java.util.Enumeration;
import javax.jms.JMSException;

public interface ProviderQueueBrowser extends JmsPropertyContext {
  public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.provider/src/com/ibm/msg/client/provider/ProviderQueueBrowser.java";
  
  Enumeration<Object> getEnumeration() throws JMSException;
  
  void close(boolean paramBoolean) throws JMSException;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\provider\ProviderQueueBrowser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */