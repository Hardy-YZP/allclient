package com.ibm.mq;

import java.util.EventListener;

interface MQPoolServicesEventListener extends EventListener {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQPoolServicesEventListener.java";
  
  void tokenAdded(MQPoolServicesEvent paramMQPoolServicesEvent);
  
  void tokenRemoved(MQPoolServicesEvent paramMQPoolServicesEvent);
  
  void defaultConnectionManagerChanged(MQPoolServicesEvent paramMQPoolServicesEvent);
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQPoolServicesEventListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */