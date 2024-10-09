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
/*    */ public class MQTopicConnectionFactoryFactory
/*    */   implements ObjectFactory
/*    */ {
/*    */   static {
/* 40 */     if (Trace.isOn) {
/* 41 */       Trace.data("com.ibm.mq.jms.MQTopicConnectionFactoryFactory", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQTopicConnectionFactoryFactory.java");
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
/* 62 */       Trace.entry(this, "com.ibm.mq.jms.MQTopicConnectionFactoryFactory", "getObjectInstance(Object,Name,Context,Hashtable<? , ?>)", new Object[] { obj, name, nameCtx, environment });
/*    */     }
/*    */ 
/*    */     
/* 66 */     MQTopicConnectionFactory tcf = null;
/*    */     
/* 68 */     if (obj instanceof Reference) {
/* 69 */       Reference ref = (Reference)obj;
/* 70 */       String className = ref.getClassName();
/* 71 */       if (className.equals(MQTopicConnectionFactory.class.getName())) {
/* 72 */         tcf = new MQTopicConnectionFactory();
/* 73 */         populateTCF(tcf, ref);
/*    */       } 
/*    */     } 
/*    */     
/* 77 */     if (Trace.isOn) {
/* 78 */       Trace.exit(this, "com.ibm.mq.jms.MQTopicConnectionFactoryFactory", "getObjectInstance(Object,Name,Context,Hashtable<? , ?>)", tcf);
/*    */     }
/*    */     
/* 81 */     return tcf;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void populateTCF(MQTopicConnectionFactory tcf, Reference ref) throws JMSException {
/* 86 */     if (Trace.isOn) {
/* 87 */       Trace.entry(this, "com.ibm.mq.jms.MQTopicConnectionFactoryFactory", "populateTCF(MQTopicConnectionFactory,Reference)", new Object[] { tcf, ref });
/*    */     }
/*    */     
/* 90 */     tcf.setFromReference(ref);
/* 91 */     if (Trace.isOn)
/* 92 */       Trace.exit(this, "com.ibm.mq.jms.MQTopicConnectionFactoryFactory", "populateTCF(MQTopicConnectionFactory,Reference)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQTopicConnectionFactoryFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */