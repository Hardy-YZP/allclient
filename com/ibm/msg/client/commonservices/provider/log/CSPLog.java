package com.ibm.msg.client.commonservices.provider.log;

import java.util.HashMap;

public interface CSPLog {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/provider/log/CSPLog.java";
  
  void initialize();
  
  void log(Object paramObject, String paramString1, String paramString2, String paramString3, HashMap<String, ? extends Object> paramHashMap);
  
  void logNLS(Object paramObject, String paramString1, String paramString2, String paramString3);
  
  void close();
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\provider\log\CSPLog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */