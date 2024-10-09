/*     */ package com.ibm.mq.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.Hashtable;
/*     */ import javax.jms.JMSException;
/*     */ import javax.naming.Context;
/*     */ import javax.naming.Name;
/*     */ import javax.naming.Reference;
/*     */ import javax.naming.spi.ObjectFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQConnectionFactoryFactory
/*     */   implements ObjectFactory
/*     */ {
/*     */   static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQConnectionFactoryFactory.java";
/*     */   
/*     */   static {
/*  41 */     if (Trace.isOn) {
/*  42 */       Trace.data("com.ibm.mq.jms.MQConnectionFactoryFactory", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQConnectionFactoryFactory.java");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  53 */     if (Trace.isOn) {
/*  54 */       Trace.entry("com.ibm.mq.jms.MQConnectionFactoryFactory", "static()");
/*     */     }
/*     */     
/*  57 */     NLSServices.addCatalogue("com.ibm.mq.jms.resources.JMSMQ_MessageResourceBundle", "JMSMQ", MQConnectionFactoryFactory.class);
/*     */     
/*  59 */     if (Trace.isOn) {
/*  60 */       Trace.exit("com.ibm.mq.jms.MQConnectionFactoryFactory", "static()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception {
/*  69 */     if (Trace.isOn) {
/*  70 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactoryFactory", "getObjectInstance(Object,Name,Context,Hashtable<? , ?>)", new Object[] { obj, name, nameCtx, environment });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  76 */     MQConnectionFactory cf = null;
/*     */     
/*  78 */     if (obj instanceof Reference) {
/*  79 */       Reference ref = (Reference)obj;
/*     */ 
/*     */       
/*  82 */       if (ref.getClassName().equals(MQConnectionFactory.class.getName())) {
/*  83 */         cf = new MQConnectionFactory();
/*     */       }
/*  85 */       else if (ref.getClassName().equals(MQQueueConnectionFactory.class.getName())) {
/*  86 */         cf = new MQQueueConnectionFactory();
/*     */       }
/*  88 */       else if (ref.getClassName().equals(MQTopicConnectionFactory.class.getName())) {
/*  89 */         cf = new MQTopicConnectionFactory();
/*     */       }
/*  91 */       else if (ref.getClassName().equals(MQXAConnectionFactory.class.getName())) {
/*  92 */         cf = new MQXAConnectionFactory();
/*     */       }
/*  94 */       else if (ref.getClassName().equals(MQXAQueueConnectionFactory.class.getName())) {
/*  95 */         cf = new MQXAQueueConnectionFactory();
/*     */       }
/*  97 */       else if (ref.getClassName().equals(MQXATopicConnectionFactory.class.getName())) {
/*  98 */         cf = new MQXATopicConnectionFactory();
/*     */       }
/* 100 */       else if (ref.getClassName().equals(MQRRSConnectionFactory.class.getName())) {
/* 101 */         cf = new MQRRSConnectionFactory();
/*     */       }
/* 103 */       else if (ref.getClassName().equals(MQRRSQueueConnectionFactory.class.getName())) {
/* 104 */         cf = new MQRRSQueueConnectionFactory();
/*     */       }
/* 106 */       else if (ref.getClassName().equals(MQRRSTopicConnectionFactory.class.getName())) {
/* 107 */         cf = new MQRRSTopicConnectionFactory();
/*     */       } 
/*     */       
/* 110 */       if (cf != null) {
/* 111 */         cf.setFromReference(ref);
/*     */ 
/*     */ 
/*     */       
/*     */       }
/* 116 */       else if (Trace.isOn) {
/* 117 */         Trace.data("com.ibm.mq.jms.MQConnectionFactoryFactory", "getObjectInstance(Object,Name,Context,Hashtable<? , ?>)", "the connection factory is null");
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 126 */     else if (Trace.isOn) {
/* 127 */       Trace.data("com.ibm.mq.jms.MQConnectionFactoryFactory", "getObjectInstance(Object,Name,Context,Hashtable<? , ?>)", "supplied object is not a Reference", null);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 133 */     if (Trace.isOn) {
/* 134 */       Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactoryFactory", "getObjectInstance(Object,Name,Context,Hashtable<? , ?>)", cf);
/*     */     }
/*     */     
/* 137 */     return cf;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void populateCF(MQConnectionFactory cf, Reference ref) throws JMSException {
/* 144 */     if (Trace.isOn) {
/* 145 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactoryFactory", "populateCF(MQConnectionFactory,Reference)", new Object[] { cf, ref });
/*     */     }
/*     */     
/* 148 */     cf.setFromReference(ref);
/* 149 */     if (Trace.isOn)
/* 150 */       Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactoryFactory", "populateCF(MQConnectionFactory,Reference)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQConnectionFactoryFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */