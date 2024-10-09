package com.ibm.mq.jmqi.handles;

import com.ibm.mq.constants.QmgrAdvancedCapability;
import com.ibm.mq.constants.QmgrSplCapability;
import com.ibm.mq.jmqi.JmqiException;
import com.ibm.mq.jmqi.system.JmqiCodepage;

public interface Hconn {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/handles/Hconn.java";
  
  public static final int NUMBER_OF_SHARING_CONVERSTIONS_UNDEFINED = -1;
  
  public static final int FAP_LEVEL_UNDEFINED = -1;
  
  int getCmdLevel() throws JmqiException;
  
  String getUid() throws JmqiException;
  
  String getName() throws JmqiException;
  
  String getOriginalQueueManagerName() throws JmqiException;
  
  int getPlatform() throws JmqiException;
  
  int getCcsid() throws JmqiException;
  
  int getNumberOfSharingConversations() throws JmqiException;
  
  int getFapLevel() throws JmqiException;
  
  String getRemoteProductId() throws JmqiException;
  
  boolean isXASupported();
  
  boolean isXAPrepared();
  
  void setXAPrepared(boolean paramBoolean);
  
  byte[] getConnectionId() throws JmqiException;
  
  String getConnectionIdAsString() throws JmqiException;
  
  Object getConnectionArea();
  
  void setConnectionArea(Object paramObject);
  
  int getPointerSize();
  
  boolean isByteSwap() throws JmqiException;
  
  JmqiCodepage getCodePage() throws JmqiException;
  
  String getQsgName() throws JmqiException;
  
  QmgrSplCapability getQmgrSplCapability();
  
  boolean isConnToZos() throws JmqiException;
  
  QmgrAdvancedCapability getQmgrAdvancedCapability() throws JmqiException;
  
  void setConnTag(byte[] paramArrayOfbyte);
  
  byte[] getConnTag();
  
  void invalidate();
  
  boolean onMessagePermitted();
  
  void leaveOnMessage();
  
  void initiateConnectionStop();
  
  void finishConnectionStop();
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\handles\Hconn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */