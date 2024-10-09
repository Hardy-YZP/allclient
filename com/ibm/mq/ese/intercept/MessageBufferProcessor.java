package com.ibm.mq.ese.intercept;

import com.ibm.mq.jmqi.JmqiException;
import com.ibm.mq.jmqi.MQMD;
import java.nio.ByteBuffer;

public interface MessageBufferProcessor {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/intercept/MessageBufferProcessor.java";
  
  public static final long MQHEADER_ASCII_RF_HEADER_2 = 5571313732236222496L;
  
  public static final long MQHEADER_EBCDIC_RF_HEADER_2 = -3109514705028104128L;
  
  public static final long MQHEADER_ASCII_RF_HEADER = 5571313732235042848L;
  
  public static final long MQHEADER_EBCDIC_RF_HEADER = -3109514705039769536L;
  
  public static final long MQHEADER_ASCII_MD_EXTENSION = 5571313710729076768L;
  
  public static final long MQHEADER_EBCDIC_MD_EXTENSION = -3109514726539444160L;
  
  public static final long MQHEADER_ASCII_WORK_INFO_HEADER = 5571313753762832416L;
  
  public static final long MQHEADER_EBCDIC_WORK_INFO_HEADER = -3109514649145950144L;
  
  public static final long MQHEADER_ASCII_REF_MSG_HEADER = 5571313732220756000L;
  
  public static final long MQHEADER_EBCDIC_REF_MSG_HEADER = -3109514705047764928L;
  
  public static final long MQHEADER_ASCII_IMS = 5571314810489937952L;
  
  public static final long MQHEADER_EBCDIC_IMS = -3109513626533216192L;
  
  public static final long MQHEADER_ASCII_CICS = 5571308195975208992L;
  
  public static final long MQHEADER_EBCDIC_CICS = -3109520271357099968L;
  
  public static final long MQHEADER_ASCII_DIST_HEADER = 5571313672159188000L;
  
  public static final long MQHEADER_EBCDIC_DIST_HEADER = -3109514795173092544L;
  
  public static final long MQHEADER_ASCII_DEAD_LETTER_HEADER = 5571309278272430112L;
  
  public static final long MQHEADER_EBCDIC_DEAD_LETTER_HEADER = -3109519189060861888L;
  
  public static final long MQHEADER_ASCII_EMBEDDED_PCF = 5571313676570543648L;
  
  public static final long MQHEADER_EBCDIC_EMBEDDED_PCF = -3109514790645283264L;
  
  void wrap(MQMD paramMQMD, ByteBuffer[] paramArrayOfByteBuffer);
  
  void wrap(MQMD paramMQMD, ByteBuffer paramByteBuffer);
  
  void process(int paramInt) throws JmqiException;
  
  boolean containsHeaders();
  
  void setPayloadEncodingCcsid(int paramInt1, int paramInt2) throws JmqiException;
  
  void setPayloadFormat(String paramString, int paramInt) throws JmqiException;
  
  int getPayloadEncoding();
  
  int getPayloadCcsid();
  
  String getPayloadFormat();
  
  byte[] getPayloadOnly();
  
  int getPayloadIndex();
  
  int getPayloadPos();
  
  int getPayloadLength();
  
  ByteBuffer getEntireBuffer();
  
  ByteBuffer[] getAllBuffers();
  
  ByteBuffer[] setProtectedPayload(byte[] paramArrayOfbyte);
  
  int getMqHeadersLength();
  
  void convertHeaders(int paramInt1, int paramInt2) throws JmqiException;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\intercept\MessageBufferProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */