/*     */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.Hashtable;
/*     */ import javax.jms.JMSException;
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
/*     */ public class MQBrokerSubscriptionList
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQBrokerSubscriptionList.java";
/*     */   
/*     */   static {
/*  56 */     if (Trace.isOn) {
/*  57 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionList", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQBrokerSubscriptionList.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   private static String CLSNAME = "MQBrokerSubscriptionList";
/*     */   
/*     */   private static Hashtable openSubscriptions;
/*     */ 
/*     */   
/*     */   public MQBrokerSubscriptionList() {
/*  72 */     if (Trace.isOn) {
/*  73 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionList", "<init>()");
/*     */     }
/*     */     
/*  76 */     synchronized (MQBrokerSubscriptionList.class) {
/*  77 */       if (openSubscriptions == null) {
/*  78 */         if (Trace.isOn) {
/*  79 */           Trace.traceData(CLSNAME, "creating new Hashtable", null);
/*     */         }
/*  81 */         openSubscriptions = new Hashtable<>();
/*     */       } 
/*     */     } 
/*  84 */     if (Trace.isOn) {
/*  85 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionList", "<init>()");
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
/*     */   public synchronized boolean getSubscription(String fullName, MQSession mqs) throws JMSException {
/*  99 */     if (Trace.isOn) {
/* 100 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionList", "getSubscription(String,MQSession)", new Object[] { fullName, mqs });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 107 */       if (openSubscriptions.containsKey(fullName)) {
/* 108 */         JMSException je = ConfigEnvironment.newException("MQJMS3023");
/* 109 */         if (Trace.isOn) {
/* 110 */           Trace.traceData(CLSNAME, "subscription " + fullName + " already in use", null);
/*     */         }
/*     */         
/* 113 */         if (Trace.isOn) {
/* 114 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionList", "getSubscription(String,MQSession)", (Throwable)je, 1);
/*     */         }
/*     */ 
/*     */         
/* 118 */         throw je;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 123 */       openSubscriptions.put(fullName, mqs);
/*     */       
/* 125 */       if (Trace.isOn) {
/* 126 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionList", "getSubscription(String,MQSession)", 
/* 127 */             Boolean.valueOf(true));
/*     */       }
/* 129 */       return true;
/*     */     }
/* 131 */     catch (Exception e) {
/* 132 */       if (Trace.isOn) {
/* 133 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionList", "getSubscription(String,MQSession)", e);
/*     */       }
/*     */ 
/*     */       
/* 137 */       JMSException traceRet1 = (JMSException)e;
/* 138 */       if (Trace.isOn) {
/* 139 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionList", "getSubscription(String,MQSession)", (Throwable)traceRet1, 2);
/*     */       }
/*     */ 
/*     */       
/* 143 */       throw traceRet1;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized boolean removeSubscription(String fullName) throws JMSException {
/* 154 */     if (Trace.isOn) {
/* 155 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionList", "removeSubscription(String)", new Object[] { fullName });
/*     */     }
/*     */ 
/*     */     
/* 159 */     if (openSubscriptions.containsKey(fullName)) {
/* 160 */       openSubscriptions.remove(fullName);
/*     */     } else {
/* 162 */       JMSException je = new JMSException("MQJMS3051");
/*     */       
/* 164 */       if (Trace.isOn) {
/* 165 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionList", "removeSubscription(String)", (Throwable)je);
/*     */       }
/*     */ 
/*     */       
/* 169 */       throw je;
/*     */     } 
/*     */     
/* 172 */     if (Trace.isOn) {
/* 173 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionList", "removeSubscription(String)", 
/* 174 */           Boolean.valueOf(true));
/*     */     }
/* 176 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\MQBrokerSubscriptionList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */