package com.ibm.msg.client.commonservices.trace;

import java.io.PrintWriter;

public interface TraceFFSTInfo {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/trace/TraceFFSTInfo.java";
  
  String providerInformation(Object paramObject);
  
  void registerObject(Object paramObject);
  
  void dump(PrintWriter paramPrintWriter, int paramInt);
  
  void deRegisterObject(Object paramObject);
  
  void registerDumpable(DumpableObject paramDumpableObject);
  
  void deRegisterDumpable(DumpableObject paramDumpableObject);
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\trace\TraceFFSTInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */