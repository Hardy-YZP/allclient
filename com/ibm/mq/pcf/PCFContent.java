package com.ibm.mq.pcf;

import java.util.Enumeration;

@Deprecated
public interface PCFContent {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/pcf/PCFContent.java";
  
  void addParameter(PCFParameter paramPCFParameter);
  
  void addParameter(int paramInt1, int paramInt2);
  
  void addParameter(int paramInt, int[] paramArrayOfint);
  
  void addParameter(int paramInt, long paramLong);
  
  void addParameter(int paramInt, long[] paramArrayOflong);
  
  void addParameter(int paramInt, String paramString);
  
  void addParameter(int paramInt, String[] paramArrayOfString);
  
  void addParameter(int paramInt, byte[] paramArrayOfbyte);
  
  void addFilterParameter(int paramInt1, int paramInt2, int paramInt3);
  
  void addFilterParameter(int paramInt1, int paramInt2, String paramString);
  
  void addFilterParameter(int paramInt1, int paramInt2, byte[] paramArrayOfbyte);
  
  int getParameterCount();
  
  Enumeration getParameters();
  
  PCFParameter getParameter(int paramInt);
  
  Object getParameterValue(int paramInt);
  
  int getIntParameterValue(int paramInt) throws PCFException;
  
  int[] getIntListParameterValue(int paramInt) throws PCFException;
  
  long getInt64ParameterValue(int paramInt) throws PCFException;
  
  long[] getIntList64ParameterValue(int paramInt) throws PCFException;
  
  String getStringParameterValue(int paramInt) throws PCFException;
  
  String[] getStringListParameterValue(int paramInt) throws PCFException;
  
  byte[] getBytesParameterValue(int paramInt) throws PCFException;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\pcf\PCFContent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */