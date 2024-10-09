package com.ibm.msg.client.provider;

import com.ibm.msg.client.jms.JmsPropertyContext;
import javax.jms.JMSException;

public interface ProviderMessage extends JmsPropertyContext {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.provider/src/com/ibm/msg/client/provider/ProviderMessage.java";
  
  String getJMSMessageID() throws JMSException;
  
  void setJMSMessageID(String paramString) throws JMSException;
  
  Long getJMSTimestamp() throws JMSException;
  
  void setJMSTimestamp(long paramLong) throws JMSException;
  
  byte[] getJMSCorrelationIDAsBytes() throws JMSException;
  
  void setJMSCorrelationIDAsBytes(byte[] paramArrayOfbyte) throws JMSException;
  
  void setJMSCorrelationID(String paramString) throws JMSException;
  
  String getJMSCorrelationID() throws JMSException;
  
  String getJMSReplyToAsString() throws JMSException;
  
  void setJMSReplyToAsString(String paramString) throws JMSException;
  
  String getJMSDestinationAsString() throws JMSException;
  
  void setJMSDestinationAsString(String paramString) throws JMSException;
  
  Integer getJMSDeliveryMode() throws JMSException;
  
  void setJMSDeliveryMode(int paramInt) throws JMSException;
  
  Boolean getJMSRedelivered() throws JMSException;
  
  void setJMSRedelivered(boolean paramBoolean) throws JMSException;
  
  String getJMSType() throws JMSException;
  
  void setJMSType(String paramString) throws JMSException;
  
  Long getJMSExpiration() throws JMSException;
  
  void setJMSExpiration(long paramLong) throws JMSException;
  
  long getJMSDeliveryDelay() throws JMSException;
  
  void setJMSDeliveryDelay(long paramLong) throws JMSException;
  
  long getJMSDeliveryTime() throws JMSException;
  
  void setJMSDeliveryTime(long paramLong) throws JMSException;
  
  Integer getJMSPriority() throws JMSException;
  
  void setJMSPriority(int paramInt) throws JMSException;
  
  void removeProperty(String paramString) throws JMSException;
  
  void clearProperties() throws JMSException;
  
  void clearBody() throws JMSException;
  
  boolean hasBody();
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\provider\ProviderMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */