/*     */ package com.ibm.mq.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*     */ import com.ibm.msg.client.jms.JmsQueueBrowser;
/*     */ import java.util.Enumeration;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.Queue;
/*     */ import javax.jms.QueueBrowser;
/*     */ import javax.jms.TemporaryQueue;
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
/*     */ public class MQQueueBrowser
/*     */   extends MQRoot
/*     */   implements QueueBrowser, JmsQueueBrowser
/*     */ {
/*     */   private static final long serialVersionUID = -4384927316550942103L;
/*     */   private JmsQueueBrowser commonQBrowser;
/*     */   
/*     */   static {
/*  48 */     if (Trace.isOn) {
/*  49 */       Trace.data("com.ibm.mq.jms.MQQueueBrowser", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQQueueBrowser.java");
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
/*     */   MQQueueBrowser() {
/*  63 */     if (Trace.isOn) {
/*  64 */       Trace.entry(this, "com.ibm.mq.jms.MQQueueBrowser", "<init>()");
/*     */     }
/*  66 */     if (Trace.isOn) {
/*  67 */       Trace.exit(this, "com.ibm.mq.jms.MQQueueBrowser", "<init>()");
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
/*     */   public void close() throws JMSException {
/*  82 */     if (Trace.isOn) {
/*  83 */       Trace.entry(this, "com.ibm.mq.jms.MQQueueBrowser", "close()");
/*     */     }
/*  85 */     this.commonQBrowser.close();
/*  86 */     if (Trace.isOn) {
/*  87 */       Trace.exit(this, "com.ibm.mq.jms.MQQueueBrowser", "close()");
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
/*     */   public Enumeration<?> getEnumeration() throws JMSException {
/* 124 */     MQQueueEnumeration wrapper = new MQQueueEnumeration();
/* 125 */     wrapper.setDelegate(this.commonQBrowser.getEnumeration());
/* 126 */     if (Trace.isOn) {
/* 127 */       Trace.data(this, "com.ibm.mq.jms.MQQueueBrowser", "getEnumeration()", "getter", wrapper);
/*     */     }
/* 129 */     return wrapper;
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
/*     */   public String getMessageSelector() throws JMSException {
/* 142 */     String traceRet1 = this.commonQBrowser.getMessageSelector();
/* 143 */     if (Trace.isOn) {
/* 144 */       Trace.data(this, "com.ibm.mq.jms.MQQueueBrowser", "getMessageSelector()", "getter", traceRet1);
/*     */     }
/*     */     
/* 147 */     return traceRet1;
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
/*     */   public Queue getQueue() throws JMSException {
/* 164 */     Queue traceRet1 = null;
/* 165 */     Queue dest = this.commonQBrowser.getQueue();
/*     */ 
/*     */     
/* 168 */     if (dest instanceof TemporaryQueue) {
/* 169 */       traceRet1 = new MQTemporaryQueue((TemporaryQueue)dest);
/*     */     } else {
/* 171 */       traceRet1 = dest;
/*     */     } 
/*     */     
/* 174 */     if (Trace.isOn) {
/* 175 */       Trace.data(this, "com.ibm.mq.jms.MQQueueBrowser", "getQueue()", "getter", traceRet1);
/*     */     }
/* 177 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   void setDelegate(JmsQueueBrowser commonQBrowser) {
/* 182 */     if (Trace.isOn) {
/* 183 */       Trace.data(this, "com.ibm.mq.jms.MQQueueBrowser", "setDelegate(JmsQueueBrowser)", "setter", commonQBrowser);
/*     */     }
/*     */     
/* 186 */     this.commonQBrowser = commonQBrowser;
/* 187 */     this.delegate = (JmsPropertyContext)commonQBrowser;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQQueueBrowser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */