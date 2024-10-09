package com.ibm.msg.client.jms;

import java.util.Map;

public interface JmsPropertyCacheInterface {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms/src/com/ibm/msg/client/jms/JmsPropertyCacheInterface.java";
  
  Object getCachedValue(String paramString);
  
  void getCachedValueAll(Map<String, Object> paramMap);
  
  boolean setCachedValue(String paramString, Object paramObject);
  
  void clearCachedValue(String paramString);
  
  void clearCachedValueAll();
  
  boolean containsCachedValue(String paramString);
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\JmsPropertyCacheInterface.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */