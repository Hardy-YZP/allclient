package com.ibm.msg.client.jms;

import java.util.Iterator;

public interface JmsExceptionDetail {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms/src/com/ibm/msg/client/jms/JmsExceptionDetail.java";
  
  String getExplanation();
  
  String getUserAction();
  
  String getValue(String paramString);
  
  Iterator<String> getKeys();
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\JmsExceptionDetail.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */