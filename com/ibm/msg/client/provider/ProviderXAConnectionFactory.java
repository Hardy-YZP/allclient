package com.ibm.msg.client.provider;

import com.ibm.msg.client.jms.JmsPropertyContext;
import javax.jms.JMSException;

public interface ProviderXAConnectionFactory extends ProviderConnectionFactory {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.provider/src/com/ibm/msg/client/provider/ProviderXAConnectionFactory.java";
  
  ProviderXAConnection createProviderXAConnection(JmsPropertyContext paramJmsPropertyContext) throws JMSException;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\provider\ProviderXAConnectionFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */