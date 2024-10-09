package com.ibm.mq.jmqi.internal;

import com.ibm.mq.jmqi.JmqiException;
import com.ibm.mq.jmqi.JmqiMQ;
import com.ibm.mq.jmqi.system.JmqiCodepage;
import com.ibm.mq.jmqi.system.JmqiTls;

public interface MqiStructure {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/internal/MqiStructure.java";
  
  int getRequiredBufferSize(int paramInt, JmqiCodepage paramJmqiCodepage) throws JmqiException;
  
  int getRequiredInputBufferSize(int paramInt, JmqiCodepage paramJmqiCodepage) throws JmqiException;
  
  int getMaximumRequiredBufferSize(int paramInt, JmqiCodepage paramJmqiCodepage) throws JmqiException;
  
  int writeToBuffer(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, boolean paramBoolean, JmqiCodepage paramJmqiCodepage, JmqiTls paramJmqiTls) throws JmqiException;
  
  int writeToTraceBuffer(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, boolean paramBoolean, JmqiCodepage paramJmqiCodepage, JmqiTls paramJmqiTls) throws JmqiException;
  
  int readFromBuffer(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, boolean paramBoolean, JmqiCodepage paramJmqiCodepage, JmqiTls paramJmqiTls) throws JmqiException;
  
  int writeToBuffer(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, boolean paramBoolean, JmqiCodepage paramJmqiCodepage, JmqiMQ paramJmqiMQ) throws JmqiException;
  
  int readFromBuffer(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, boolean paramBoolean, JmqiCodepage paramJmqiCodepage, JmqiMQ paramJmqiMQ) throws JmqiException;
  
  String toStringMultiLine();
  
  void addFieldsToFormatter(JmqiStructureFormatter paramJmqiStructureFormatter);
  
  void setVersion(int paramInt);
  
  int getVersion();
  
  int getFirstVersion();
  
  int getCurrentVersion();
  
  int getSize(int paramInt) throws JmqiException;
  
  void setupForTest(int paramInt);
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\internal\MqiStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */