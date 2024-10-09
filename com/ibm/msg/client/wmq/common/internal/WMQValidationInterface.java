/*    */ package com.ibm.msg.client.wmq.common.internal;
/*    */ 
/*    */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*    */ import java.io.Serializable;
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
/*    */ public interface WMQValidationInterface
/*    */   extends Serializable
/*    */ {
/*    */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/WMQValidationInterface.java";
/*    */   public static final int MQJMS_PROPERTY_DOMAIN_ADMIN = 1;
/*    */   public static final int MQJMS_PROPERTY_DOMAIN_CANONICAL = 4;
/*    */   public static final int MQJMS_PROPERTY_DOMAIN_SHORTADMIN = 2;
/*    */   public static final int MQJMS_PROPERTY_DOMAIN_URI = 3;
/*    */   public static final int MQJMS_PROPERTY_DOMAIN_CAN_EXT = 5;
/*    */   
/*    */   WMQPropertyValidatorDatatype fromCanonical(JmsPropertyContext paramJmsPropertyContext, String paramString, Object paramObject);
/*    */   
/*    */   WMQPropertyValidatorDatatype fromCanonical(String paramString, Object paramObject);
/*    */   
/*    */   String getDomainName(int paramInt);
/*    */   
/*    */   WMQPropertyValidatorDatatype toCanonical(JmsPropertyContext paramJmsPropertyContext, String paramString, Object paramObject);
/*    */   
/*    */   WMQPropertyValidatorDatatype toCanonical(String paramString, Object paramObject);
/*    */   
/*    */   boolean validate(Object paramObject1, Object paramObject2) throws JMSException;
/*    */   
/*    */   boolean crossPropertyValidate(Object paramObject) throws JMSException;
/*    */   
/*    */   public static class WMQPropertyValidatorDatatype
/*    */   {
/*    */     String name;
/*    */     Object value;
/*    */     
/*    */     public WMQPropertyValidatorDatatype(String name, Object value) {
/* 54 */       this.name = name;
/* 55 */       this.value = value;
/*    */     }
/*    */     
/*    */     public String getName() {
/* 59 */       return this.name;
/*    */     }
/*    */     
/*    */     public Object getValue() {
/* 63 */       return this.value;
/*    */     }
/*    */     
/*    */     public void setName(String name) {
/* 67 */       this.name = name;
/*    */     }
/*    */     
/*    */     public void setValue(Object value) {
/* 71 */       this.value = value;
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\common\internal\WMQValidationInterface.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */