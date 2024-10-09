/*     */ package com.ibm.msg.client.jms;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Enumeration;
/*     */ import javax.jms.JMSException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface JmsReadablePropertyContext
/*     */   extends Serializable
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms/src/com/ibm/msg/client/jms/JmsReadablePropertyContext.java";
/*     */   
/*     */   boolean propertyExists(String paramString) throws JMSException;
/*     */   
/*     */   char getCharProperty(String paramString) throws JMSException;
/*     */   
/*     */   boolean getBooleanProperty(String paramString) throws JMSException;
/*     */   
/*     */   byte getByteProperty(String paramString) throws JMSException;
/*     */   
/*     */   short getShortProperty(String paramString) throws JMSException;
/*     */   
/*     */   int getIntProperty(String paramString) throws JMSException;
/*     */   
/*     */   long getLongProperty(String paramString) throws JMSException;
/*     */   
/*     */   float getFloatProperty(String paramString) throws JMSException;
/*     */   
/*     */   double getDoubleProperty(String paramString) throws JMSException;
/*     */   
/*     */   String getStringProperty(String paramString) throws JMSException;
/*     */   
/*     */   Object getObjectProperty(String paramString) throws JMSException;
/*     */   
/*     */   byte[] getBytesProperty(String paramString) throws JMSException;
/*     */   
/*     */   Enumeration<String> getPropertyNames() throws JMSException;
/*     */   
/*     */   default String stringifyMe() {
/* 170 */     return String.format("%s@%x", new Object[] { getClass().getName(), Integer.valueOf(hashCode()) });
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\JmsReadablePropertyContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */