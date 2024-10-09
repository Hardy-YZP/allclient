/*    */ package com.ibm.mq.jms;
/*    */ 
/*    */ import com.ibm.msg.client.commonservices.trace.Trace;
/*    */ import java.util.Hashtable;
/*    */ import javax.jms.JMSException;
/*    */ import javax.naming.Context;
/*    */ import javax.naming.Name;
/*    */ import javax.naming.Reference;
/*    */ import javax.naming.spi.ObjectFactory;
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
/*    */ public class MQXATopicConnectionFactoryFactory
/*    */   implements ObjectFactory
/*    */ {
/*    */   static {
/* 40 */     if (Trace.isOn) {
/* 41 */       Trace.data("com.ibm.mq.jms.MQXATopicConnectionFactoryFactory", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQXATopicConnectionFactoryFactory.java");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception {
/* 54 */     if (Trace.isOn) {
/* 55 */       Trace.entry(this, "com.ibm.mq.jms.MQXATopicConnectionFactoryFactory", "getObjectInstance(Object,Name,Context,Hashtable<? , ?>)", new Object[] { obj, name, nameCtx, environment });
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 60 */     MQXATopicConnectionFactory xatcf = null;
/*    */     
/* 62 */     if (obj instanceof Reference) {
/* 63 */       Reference ref = (Reference)obj;
/* 64 */       String className = ref.getClassName();
/* 65 */       if (className.equals(MQXATopicConnectionFactory.class.getName())) {
/* 66 */         xatcf = new MQXATopicConnectionFactory();
/* 67 */         populateXATCF(xatcf, ref);
/*    */       } 
/*    */     } 
/* 70 */     if (Trace.isOn) {
/* 71 */       Trace.exit(this, "com.ibm.mq.jms.MQXATopicConnectionFactoryFactory", "getObjectInstance(Object,Name,Context,Hashtable<? , ?>)", xatcf);
/*    */     }
/*    */     
/* 74 */     return xatcf;
/*    */   }
/*    */   
/*    */   protected void populateXATCF(MQXATopicConnectionFactory xatcf, Reference ref) throws JMSException {
/* 78 */     if (Trace.isOn) {
/* 79 */       Trace.entry(this, "com.ibm.mq.jms.MQXATopicConnectionFactoryFactory", "populateXATCF(MQXATopicConnectionFactory,Reference)", new Object[] { xatcf, ref });
/*    */     }
/*    */     
/* 82 */     xatcf.setFromReference(ref);
/* 83 */     if (Trace.isOn)
/* 84 */       Trace.exit(this, "com.ibm.mq.jms.MQXATopicConnectionFactoryFactory", "populateXATCF(MQXATopicConnectionFactory,Reference)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQXATopicConnectionFactoryFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */