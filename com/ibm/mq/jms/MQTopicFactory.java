/*     */ package com.ibm.mq.jms;
/*     */ 
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
/*     */ 
/*     */ 
/*     */ public class MQTopicFactory
/*     */   implements ObjectFactory
/*     */ {
/*     */   static {
/*  40 */     if (Trace.isOn) {
/*  41 */       Trace.data("com.ibm.mq.jms.MQTopicFactory", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQTopicFactory.java");
/*     */     }
/*     */   }
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
/*     */   public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception {
/*  60 */     if (Trace.isOn) {
/*  61 */       Trace.entry(this, "com.ibm.mq.jms.MQTopicFactory", "getObjectInstance(Object,Name,Context,Hashtable<? , ?>)", new Object[] { obj, name, nameCtx, environment });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  66 */     if (obj instanceof Reference) {
/*  67 */       Reference ref = (Reference)obj;
/*  68 */       if (ref.getClassName().equals(MQTopic.class.getName())) {
/*  69 */         MQTopic t = new MQTopic();
/*     */         
/*  71 */         populateTopic(t, ref);
/*     */         
/*  73 */         if (Trace.isOn) {
/*  74 */           Trace.exit(this, "com.ibm.mq.jms.MQTopicFactory", "getObjectInstance(Object,Name,Context,Hashtable<? , ?>)", t, 1);
/*     */         }
/*     */         
/*  77 */         return t;
/*     */       } 
/*     */     } 
/*  80 */     if (Trace.isOn) {
/*  81 */       Trace.exit(this, "com.ibm.mq.jms.MQTopicFactory", "getObjectInstance(Object,Name,Context,Hashtable<? , ?>)", null, 2);
/*     */     }
/*     */     
/*  84 */     return null;
/*     */   }
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
/*     */   protected void populateTopic(MQTopic top, Reference ref) throws JMSException {
/*  97 */     if (Trace.isOn) {
/*  98 */       Trace.entry(this, "com.ibm.mq.jms.MQTopicFactory", "populateTopic(MQTopic,Reference)", new Object[] { top, ref });
/*     */     }
/*     */     
/* 101 */     top.setFromReference(ref);
/* 102 */     if (Trace.isOn)
/* 103 */       Trace.exit(this, "com.ibm.mq.jms.MQTopicFactory", "populateTopic(MQTopic,Reference)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQTopicFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */