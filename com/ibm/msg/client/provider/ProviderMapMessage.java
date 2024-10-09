package com.ibm.msg.client.provider;

import java.util.Enumeration;
import java.util.Map;
import javax.jms.JMSException;

public interface ProviderMapMessage extends ProviderMessage {
  Object getObject(String paramString) throws JMSException;
  
  Enumeration<String> getMapNames() throws JMSException;
  
  Map<?, ?> getMap(boolean paramBoolean) throws JMSException;
  
  void setBoolean(String paramString, boolean paramBoolean) throws JMSException;
  
  void setByte(String paramString, byte paramByte) throws JMSException;
  
  void setShort(String paramString, short paramShort) throws JMSException;
  
  void setChar(String paramString, char paramChar) throws JMSException;
  
  void setInt(String paramString, int paramInt) throws JMSException;
  
  void setLong(String paramString, long paramLong) throws JMSException;
  
  void setFloat(String paramString, float paramFloat) throws JMSException;
  
  void setDouble(String paramString, double paramDouble) throws JMSException;
  
  void setString(String paramString1, String paramString2) throws JMSException;
  
  void setBytes(String paramString, byte[] paramArrayOfbyte) throws JMSException;
  
  void setBytes(String paramString, byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws JMSException;
  
  void setObject(String paramString, Object paramObject) throws JMSException;
  
  boolean itemExists(String paramString) throws JMSException;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\provider\ProviderMapMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */