package com.ibm.jms;

import javax.jms.Destination;

@Deprecated
public interface JMSDestination extends Destination {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/jms/JMSDestination.java";
  
  String getStringFromDestination();
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\jms\JMSDestination.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */