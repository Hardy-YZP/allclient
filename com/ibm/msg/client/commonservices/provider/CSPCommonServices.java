package com.ibm.msg.client.commonservices.provider;

import com.ibm.msg.client.commonservices.provider.commandmanager.CSPCommandManager;
import com.ibm.msg.client.commonservices.provider.log.CSPLog;
import com.ibm.msg.client.commonservices.provider.nls.CSPNLSServices;
import com.ibm.msg.client.commonservices.provider.propertystore.CSPPropertyStore;
import com.ibm.msg.client.commonservices.provider.trace.CSPTrace;
import com.ibm.msg.client.commonservices.provider.workqueue.CSPWorkQueueManager;

public interface CSPCommonServices {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/provider/CSPCommonServices.java";
  
  CSPTrace getTrace();
  
  CSPLog getLog();
  
  CSPWorkQueueManager getWorkQueueManager();
  
  CSPNLSServices getNLSServices();
  
  CSPCommandManager getCommandManager();
  
  CSPPropertyStore getPropertyStore();
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\provider\CSPCommonServices.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */