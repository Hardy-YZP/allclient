package com.ibm.msg.client.commonservices.Log;

public interface LogRecord {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/Log/LogRecord.java";
  
  String getMessage();
  
  long getMillis();
  
  Object[] getParameters();
  
  long getSequenceNumber();
  
  String getSourceClassName();
  
  String getSourceMethodName();
  
  Throwable getThrown();
  
  void setMessage(String paramString);
  
  void setMillis(long paramLong);
  
  void setParameters(Object[] paramArrayOfObject);
  
  void setSequenceNumber(long paramLong);
  
  void setSourceClassName(String paramString);
  
  void setSourceMethodName(String paramString);
  
  void setThrown(Throwable paramThrowable);
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\Log\LogRecord.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */