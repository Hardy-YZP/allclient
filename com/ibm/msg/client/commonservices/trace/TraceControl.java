package com.ibm.msg.client.commonservices.trace;

public interface TraceControl {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/trace/TraceControl.java";
  
  void setOn();
  
  void setOff();
  
  void includePackage(String paramString);
  
  void excludePackage(String paramString);
  
  boolean isOn();
  
  PackageNode rootNode();
  
  String getTraceFileName();
  
  String getUserDir();
  
  String dumpState(String paramString, boolean paramBoolean);
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\trace\TraceControl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */