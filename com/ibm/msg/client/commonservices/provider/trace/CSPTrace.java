package com.ibm.msg.client.commonservices.provider.trace;

import com.ibm.msg.client.commonservices.trace.TraceFormatter;
import com.ibm.msg.client.commonservices.trace.TraceHandler;
import java.util.HashMap;

public interface CSPTrace {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/provider/trace/CSPTrace.java";
  
  void catchBlock(int paramInt1, Object paramObject, String paramString1, String paramString2, Throwable paramThrowable, int paramInt2);
  
  void close();
  
  void finallyBlock(int paramInt1, Object paramObject, String paramString1, String paramString2, int paramInt2);
  
  void initialize();
  
  void methodEntry(int paramInt, Object paramObject, String paramString1, String paramString2, Object[] paramArrayOfObject);
  
  void methodExit(int paramInt1, Object paramObject1, String paramString1, String paramString2, Object paramObject2, int paramInt2);
  
  void setTraceFormatter(TraceFormatter paramTraceFormatter);
  
  void setTraceHandler(TraceHandler paramTraceHandler);
  
  void throwing(int paramInt1, Object paramObject, String paramString1, String paramString2, Throwable paramThrowable, int paramInt2);
  
  void traceData(int paramInt, Object paramObject1, String paramString1, String paramString2, String paramString3, Object paramObject2);
  
  String ffst(Object paramObject, String paramString1, String paramString2, HashMap<String, ? extends Object> paramHashMap, String paramString3);
  
  void setTraceLevel(int paramInt);
  
  int getTraceLevel();
  
  String getOutputFileName();
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\provider\trace\CSPTrace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */