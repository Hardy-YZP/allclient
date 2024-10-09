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
/*    */ public class MQRRSTopicConnectionFactoryFactory
/*    */   implements ObjectFactory
/*    */ {
/*    */   static {
/* 40 */     if (Trace.isOn) {
/* 41 */       Trace.data("com.ibm.mq.jms.MQRRSTopicConnectionFactoryFactory", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQRRSTopicConnectionFactoryFactory.java");
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
/* 62 */       Trace.entry(this, "com.ibm.mq.jms.MQRRSTopicConnectionFactoryFactory", "getObjectInstance(Object,Name,Context,Hashtable<? , ?>)", new Object[] { obj, name, nameCtx, environment });
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 67 */     MQRRSTopicConnectionFactory rrstcf = null;
/*    */     
/* 69 */     if (obj instanceof Reference) {
/* 70 */       Reference ref = (Reference)obj;
/* 71 */       String className = ref.getClassName();
/* 72 */       if (className.equals(MQRRSTopicConnectionFactory.class.getName())) {
/* 73 */         rrstcf = new MQRRSTopicConnectionFactory();
/* 74 */         populateRRSTCF(rrstcf, ref);
/*    */       } 
/*    */     } 
/* 77 */     if (Trace.isOn) {
/* 78 */       Trace.exit(this, "com.ibm.mq.jms.MQRRSTopicConnectionFactoryFactory", "getObjectInstance(Object,Name,Context,Hashtable<? , ?>)", rrstcf);
/*    */     }
/*    */     
/* 81 */     return rrstcf;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void populateRRSTCF(MQRRSTopicConnectionFactory rrstcf, Reference ref) throws JMSException {
/* 86 */     if (Trace.isOn) {
/* 87 */       Trace.entry(this, "com.ibm.mq.jms.MQRRSTopicConnectionFactoryFactory", "populateRRSTCF(MQRRSTopicConnectionFactory,Reference)", new Object[] { rrstcf, ref });
/*    */     }
/*    */     
/* 90 */     rrstcf.setFromReference(ref);
/* 91 */     if (Trace.isOn)
/* 92 */       Trace.exit(this, "com.ibm.mq.jms.MQRRSTopicConnectionFactoryFactory", "populateRRSTCF(MQRRSTopicConnectionFactory,Reference)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQRRSTopicConnectionFactoryFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */