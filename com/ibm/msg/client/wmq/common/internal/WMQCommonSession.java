package com.ibm.msg.client.wmq.common.internal;

import com.ibm.mq.jmqi.JmqiEnvironment;
import com.ibm.mq.jmqi.JmqiMQ;
import com.ibm.mq.jmqi.handles.Hconn;
import com.ibm.msg.client.provider.ProviderSession;
import com.ibm.msg.client.wmq.common.WMQThreadLocalStorage;

public interface WMQCommonSession extends ProviderSession {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/WMQCommonSession.java";
  
  WMQCommonConnection getConnection();
  
  WMQThreadLocalStorage getThreadLocalStorage();
  
  Hconn getHconn();
  
  JmqiEnvironment getJmqiEnvironment();
  
  JmqiMQ getJmqiMQ();
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\common\internal\WMQCommonSession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */