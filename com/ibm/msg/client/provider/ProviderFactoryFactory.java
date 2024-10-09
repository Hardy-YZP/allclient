/*    */ package com.ibm.msg.client.provider;
/*    */ 
/*    */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*    */ import java.util.Map;
/*    */ import javax.jms.JMSException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface ProviderFactoryFactory
/*    */ {
/*    */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.provider/src/com/ibm/msg/client/provider/ProviderFactoryFactory.java";
/* 53 */   public static final ProviderMessageFactory providerMessageFactory = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 59 */   public static final ProviderMatchSpace providerMatchspace = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 65 */   public static final ProviderJmsFactory providerJmsFactory = null;
/*    */   
/*    */   ProviderMessageFactory getMessageFactory() throws JMSException;
/*    */   
/*    */   ProviderMatchSpace getMatchSpace() throws JMSException;
/*    */   
/*    */   ProviderJmsFactory getJmsFactory() throws JMSException;
/*    */   
/*    */   Map<String, Object> getCapabilities() throws JMSException;
/*    */   
/*    */   @Deprecated
/*    */   ProviderMetaData getMetaData() throws JMSException;
/*    */   
/*    */   ProviderConnectionFactory createProviderConnectionFactory(JmsPropertyContext paramJmsPropertyContext) throws JMSException;
/*    */   
/*    */   ProviderDestination createProviderDestination(int paramInt, String paramString, JmsPropertyContext paramJmsPropertyContext) throws JMSException;
/*    */   
/*    */   ProviderXAConnectionFactory createProviderXAConnectionFactory(JmsPropertyContext paramJmsPropertyContext) throws JMSException;
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\provider\ProviderFactoryFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */