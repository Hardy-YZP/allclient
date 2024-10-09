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
/*    */ public class MQRRSQueueConnectionFactoryFactory
/*    */   implements ObjectFactory
/*    */ {
/*    */   static {
/* 40 */     if (Trace.isOn) {
/* 41 */       Trace.data("com.ibm.mq.jms.MQRRSQueueConnectionFactoryFactory", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQRRSQueueConnectionFactoryFactory.java");
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception {
/* 61 */     if (Trace.isOn) {
/* 62 */       Trace.entry(this, "com.ibm.mq.jms.MQRRSQueueConnectionFactoryFactory", "getObjectInstance(Object,Name,Context,Hashtable<? , ?>)", new Object[] { obj, name, nameCtx, environment });
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 67 */     MQRRSQueueConnectionFactory rrsqcf = null;
/*    */     
/* 69 */     if (obj instanceof Reference) {
/* 70 */       Reference ref = (Reference)obj;
/* 71 */       String className = ref.getClassName();
/* 72 */       if (className.equals(MQRRSQueueConnectionFactory.class.getName())) {
/* 73 */         rrsqcf = new MQRRSQueueConnectionFactory();
/* 74 */         populateRRSQCF(rrsqcf, ref);
/*    */       } 
/*    */     } 
/* 77 */     if (Trace.isOn) {
/* 78 */       Trace.exit(this, "com.ibm.mq.jms.MQRRSQueueConnectionFactoryFactory", "getObjectInstance(Object,Name,Context,Hashtable<? , ?>)", rrsqcf);
/*    */     }
/*    */     
/* 81 */     return rrsqcf;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void populateRRSQCF(MQRRSQueueConnectionFactory rrsqcf, Reference ref) throws JMSException {
/* 86 */     if (Trace.isOn) {
/* 87 */       Trace.entry(this, "com.ibm.mq.jms.MQRRSQueueConnectionFactoryFactory", "populateRRSQCF(MQRRSQueueConnectionFactory,Reference)", new Object[] { rrsqcf, ref });
/*    */     }
/*    */     
/* 90 */     rrsqcf.setFromReference(ref);
/* 91 */     if (Trace.isOn)
/* 92 */       Trace.exit(this, "com.ibm.mq.jms.MQRRSQueueConnectionFactoryFactory", "populateRRSQCF(MQRRSQueueConnectionFactory,Reference)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQRRSQueueConnectionFactoryFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */