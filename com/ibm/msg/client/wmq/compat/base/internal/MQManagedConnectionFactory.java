package com.ibm.msg.client.wmq.compat.base.internal;

import com.ibm.mq.MQException;
import java.io.Serializable;

interface MQManagedConnectionFactory extends Serializable, Cloneable {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQManagedConnectionFactory.java";
  
  Object clone();
  
  MQManagedConnectionJ11 createManagedConnection(MQConnectionRequestInfo paramMQConnectionRequestInfo) throws MQResourceException, MQException;
  
  boolean equals(Object paramObject);
  
  int hashCode();
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\base\internal\MQManagedConnectionFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */