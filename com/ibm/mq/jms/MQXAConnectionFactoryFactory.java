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
/*    */ public class MQXAConnectionFactoryFactory
/*    */   implements ObjectFactory
/*    */ {
/*    */   static {
/* 40 */     if (Trace.isOn) {
/* 41 */       Trace.data("com.ibm.mq.jms.MQXAConnectionFactoryFactory", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQXAConnectionFactoryFactory.java");
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
/* 62 */       Trace.entry(this, "com.ibm.mq.jms.MQXAConnectionFactoryFactory", "getObjectInstance(Object,Name,Context,Hashtable<? , ?>)", new Object[] { obj, name, nameCtx, environment });
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 67 */     MQXAConnectionFactory xacf = null;
/*    */     
/* 69 */     if (obj instanceof Reference) {
/* 70 */       Reference ref = (Reference)obj;
/* 71 */       String className = ref.getClassName();
/* 72 */       if (className.equals(MQXAConnectionFactory.class.getName())) {
/* 73 */         xacf = new MQXAConnectionFactory();
/* 74 */         populateXACF(xacf, ref);
/*    */       } 
/*    */     } 
/* 77 */     if (Trace.isOn) {
/* 78 */       Trace.exit(this, "com.ibm.mq.jms.MQXAConnectionFactoryFactory", "getObjectInstance(Object,Name,Context,Hashtable<? , ?>)", xacf);
/*    */     }
/*    */     
/* 81 */     return xacf;
/*    */   }
/*    */   
/*    */   protected void populateXACF(MQXAConnectionFactory xacf, Reference ref) throws JMSException {
/* 85 */     if (Trace.isOn) {
/* 86 */       Trace.entry(this, "com.ibm.mq.jms.MQXAConnectionFactoryFactory", "populateXACF(MQXAConnectionFactory,Reference)", new Object[] { xacf, ref });
/*    */     }
/*    */     
/* 89 */     xacf.setFromReference(ref);
/* 90 */     if (Trace.isOn)
/* 91 */       Trace.exit(this, "com.ibm.mq.jms.MQXAConnectionFactoryFactory", "populateXACF(MQXAConnectionFactory,Reference)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQXAConnectionFactoryFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */