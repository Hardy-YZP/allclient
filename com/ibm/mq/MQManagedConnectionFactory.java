package com.ibm.mq;

import java.io.Serializable;

interface MQManagedConnectionFactory extends Serializable, Cloneable {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQManagedConnectionFactory.java";
  
  MQManagedConnectionJ11 createManagedConnection(MQConnectionRequestInfo paramMQConnectionRequestInfo) throws MQResourceException;
  
  int hashCode();
  
  boolean equals(Object paramObject);
  
  Object clone();
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQManagedConnectionFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */