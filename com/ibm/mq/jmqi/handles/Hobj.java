package com.ibm.mq.jmqi.handles;

public interface Hobj {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/handles/Hobj.java";
  
  int getIntegerHandle();
  
  byte[] getAMSPolicy();
  
  String getAMSErrorQueue();
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\handles\Hobj.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */