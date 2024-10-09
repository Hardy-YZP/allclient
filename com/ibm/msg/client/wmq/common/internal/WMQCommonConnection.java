package com.ibm.msg.client.wmq.common.internal;

import com.ibm.msg.client.jms.JmsPropertyContext;
import com.ibm.msg.client.provider.ProviderConnection;
import javax.jms.JMSException;

public interface WMQCommonConnection extends ProviderConnection {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/WMQCommonConnection.java";
  
  WMQDestination createTemporaryDestination(int paramInt, JmsPropertyContext paramJmsPropertyContext) throws JMSException;
  
  void deleteTemporaryDestination(WMQDestination paramWMQDestination) throws JMSException;
  
  boolean getPersistenceFromMD();
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\common\internal\WMQCommonConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */