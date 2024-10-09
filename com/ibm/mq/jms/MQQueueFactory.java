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
/*     */ 
/*     */ 
/*     */ public class MQQueueFactory
/*     */   implements ObjectFactory
/*     */ {
/*     */   static {
/*  42 */     if (Trace.isOn) {
/*  43 */       Trace.data("com.ibm.mq.jms.MQQueueFactory", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQQueueFactory.java");
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
/*  62 */     if (Trace.isOn) {
/*  63 */       Trace.entry(this, "com.ibm.mq.jms.MQQueueFactory", "getObjectInstance(Object,Name,Context,Hashtable<? , ?>)", new Object[] { obj, name, nameCtx, environment });
/*     */     }
/*     */ 
/*     */     
/*  67 */     if (obj instanceof Reference) {
/*  68 */       Reference ref = (Reference)obj;
/*  69 */       if (ref.getClassName().equals(MQQueue.class.getName())) {
/*  70 */         MQQueue q = new MQQueue();
/*     */         
/*  72 */         populateQueue(q, ref);
/*     */         
/*  74 */         if (Trace.isOn) {
/*  75 */           Trace.exit(this, "com.ibm.mq.jms.MQQueueFactory", "getObjectInstance(Object,Name,Context,Hashtable<? , ?>)", q, 1);
/*     */         }
/*     */         
/*  78 */         return q;
/*     */       } 
/*     */     } 
/*  81 */     if (Trace.isOn) {
/*  82 */       Trace.exit(this, "com.ibm.mq.jms.MQQueueFactory", "getObjectInstance(Object,Name,Context,Hashtable<? , ?>)", null, 2);
/*     */     }
/*     */     
/*  85 */     return null;
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
/*     */   protected void populateQueue(MQQueue queue, Reference ref) throws JMSException {
/*  97 */     if (Trace.isOn) {
/*  98 */       Trace.entry(this, "com.ibm.mq.jms.MQQueueFactory", "populateQueue(MQQueue,Reference)", new Object[] { queue, ref });
/*     */     }
/*     */     
/* 101 */     queue.setFromReference(ref);
/* 102 */     if (Trace.isOn)
/* 103 */       Trace.exit(this, "com.ibm.mq.jms.MQQueueFactory", "populateQueue(MQQueue,Reference)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQQueueFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */