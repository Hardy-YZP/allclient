package com.ibm.msg.client.commonservices.provider.workqueue;

import com.ibm.msg.client.commonservices.CSIException;
import com.ibm.msg.client.commonservices.workqueue.WorkQueueItem;

public interface CSPWorkQueueManager {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/provider/workqueue/CSPWorkQueueManager.java";
  
  void enqueueItem(WorkQueueItem paramWorkQueueItem, int paramInt) throws CSIException;
  
  int fillThreadPool() throws CSIException;
  
  int emptyThreadPool() throws CSIException;
  
  int getCurrentThreadPoolSize() throws CSIException;
  
  void start() throws CSIException;
  
  void pause() throws CSIException;
  
  void close() throws CSIException;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\provider\workqueue\CSPWorkQueueManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */