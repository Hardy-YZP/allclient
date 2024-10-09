/*     */ package com.ibm.msg.client.jms;
/*     */ 
/*     */ import java.util.Map;
/*     */ import javax.jms.JMSException;
/*     */ import org.json.JSONObject;
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
/*     */ public interface JmsPropertyContext
/*     */   extends JmsReadablePropertyContext, Map<String, Object>
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms/src/com/ibm/msg/client/jms/JmsPropertyContext.java";
/*     */   
/*     */   void setCharProperty(String paramString, char paramChar) throws JMSException;
/*     */   
/*     */   void setBooleanProperty(String paramString, boolean paramBoolean) throws JMSException;
/*     */   
/*     */   void setByteProperty(String paramString, byte paramByte) throws JMSException;
/*     */   
/*     */   void setShortProperty(String paramString, short paramShort) throws JMSException;
/*     */   
/*     */   void setIntProperty(String paramString, int paramInt) throws JMSException;
/*     */   
/*     */   void setLongProperty(String paramString, long paramLong) throws JMSException;
/*     */   
/*     */   void setFloatProperty(String paramString, float paramFloat) throws JMSException;
/*     */   
/*     */   void setDoubleProperty(String paramString, double paramDouble) throws JMSException;
/*     */   
/*     */   void setStringProperty(String paramString1, String paramString2) throws JMSException;
/*     */   
/*     */   void setObjectProperty(String paramString, Object paramObject) throws JMSException;
/*     */   
/*     */   void setBytesProperty(String paramString, byte[] paramArrayOfbyte) throws JMSException;
/*     */   
/*     */   void setBatchProperties(Map<String, Object> paramMap) throws JMSException;
/*     */   
/*     */   default JSONObject toJson() {
/* 193 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\JmsPropertyContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */