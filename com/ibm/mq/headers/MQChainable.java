package com.ibm.mq.headers;

public interface MQChainable {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQChainable.java";
  
  int nextEncoding();
  
  void nextEncoding(int paramInt);
  
  int nextCharacterSet();
  
  void nextCharacterSet(int paramInt);
  
  String nextFormat();
  
  void nextFormat(String paramString);
  
  String format();
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\MQChainable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */