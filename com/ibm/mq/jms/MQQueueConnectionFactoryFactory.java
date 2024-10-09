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
/*    */ public class MQQueueConnectionFactoryFactory
/*    */   implements ObjectFactory
/*    */ {
/*    */   static {
/* 40 */     if (Trace.isOn) {
/* 41 */       Trace.data("com.ibm.mq.jms.MQQueueConnectionFactoryFactory", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQQueueConnectionFactoryFactory.java");
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
/* 62 */       Trace.entry(this, "com.ibm.mq.jms.MQQueueConnectionFactoryFactory", "getObjectInstance(Object,Name,Context,Hashtable<? , ?>)", new Object[] { obj, name, nameCtx, environment });
/*    */     }
/*    */ 
/*    */     
/* 66 */     if (obj instanceof Reference) {
/* 67 */       Reference ref = (Reference)obj;
/* 68 */       if (ref.getClassName().equals(MQQueueConnectionFactory.class.getName())) {
/* 69 */         MQQueueConnectionFactory qcf = new MQQueueConnectionFactory();
/*    */         
/* 71 */         populateQCF(qcf, ref);
/*    */         
/* 73 */         if (Trace.isOn) {
/* 74 */           Trace.exit(this, "com.ibm.mq.jms.MQQueueConnectionFactoryFactory", "getObjectInstance(Object,Name,Context,Hashtable<? , ?>)", qcf, 1);
/*    */         }
/*    */         
/* 77 */         return qcf;
/*    */       } 
/*    */     } 
/* 80 */     if (Trace.isOn) {
/* 81 */       Trace.exit(this, "com.ibm.mq.jms.MQQueueConnectionFactoryFactory", "getObjectInstance(Object,Name,Context,Hashtable<? , ?>)", null, 2);
/*    */     }
/*    */     
/* 84 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void populateQCF(MQQueueConnectionFactory qcf, Reference ref) throws JMSException {
/* 89 */     if (Trace.isOn) {
/* 90 */       Trace.entry(this, "com.ibm.mq.jms.MQQueueConnectionFactoryFactory", "populateQCF(MQQueueConnectionFactory,Reference)", new Object[] { qcf, ref });
/*    */     }
/*    */     
/* 93 */     qcf.setFromReference(ref);
/* 94 */     if (Trace.isOn)
/* 95 */       Trace.exit(this, "com.ibm.mq.jms.MQQueueConnectionFactoryFactory", "populateQCF(MQQueueConnectionFactory,Reference)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQQueueConnectionFactoryFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */