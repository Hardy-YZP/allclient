package com.ibm.msg.client.provider;

import java.util.ArrayList;
import javax.jms.JMSException;

public interface ProviderStreamMessage extends ProviderMessage {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.provider/src/com/ibm/msg/client/provider/ProviderStreamMessage.java";
  
  ArrayList<Object> getStreamData() throws JMSException;
  
  Object readObject() throws JMSException;
  
  void writeBoolean(boolean paramBoolean) throws JMSException;
  
  void writeByte(byte paramByte) throws JMSException;
  
  void writeShort(short paramShort) throws JMSException;
  
  void writeChar(char paramChar) throws JMSException;
  
  void writeInt(int paramInt) throws JMSException;
  
  void writeLong(long paramLong) throws JMSException;
  
  void writeFloat(float paramFloat) throws JMSException;
  
  void writeDouble(double paramDouble) throws JMSException;
  
  void writeString(String paramString) throws JMSException;
  
  void writeBytes(byte[] paramArrayOfbyte) throws JMSException;
  
  void writeBytes(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws JMSException;
  
  void writeObject(Object paramObject) throws JMSException;
  
  void reset() throws JMSException;
  
  void stepBack() throws JMSException;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\provider\ProviderStreamMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */