package com.ibm.msg.client.provider;

import javax.jms.JMSException;
import javax.transaction.xa.XAResource;

public interface ProviderXASession extends ProviderSession {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.provider/src/com/ibm/msg/client/provider/ProviderXASession.java";
  
  XAResource getXAResource() throws JMSException;
  
  boolean isXASessionActive();
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\provider\ProviderXASession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */