/*     */ package com.ibm.msg.client.wmq.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*     */ import com.ibm.msg.client.provider.ProviderDestination;
/*     */ import com.ibm.msg.client.provider.ProviderQueueBrowser;
/*     */ import com.ibm.msg.client.wmq.common.internal.WMQDestination;
/*     */ import com.ibm.msg.client.wmq.common.internal.WMQPropertyContext;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.Vector;
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
/*     */ public class WMQQueueBrowser
/*     */   extends WMQPropertyContext
/*     */   implements ProviderQueueBrowser
/*     */ {
/*     */   private static final long serialVersionUID = -7029065419554489524L;
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq/src/com/ibm/msg/client/wmq/internal/WMQQueueBrowser.java";
/*     */   
/*     */   static {
/*  45 */     if (Trace.isOn) {
/*  46 */       Trace.data("com.ibm.msg.client.wmq.internal.WMQQueueBrowser", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq/src/com/ibm/msg/client/wmq/internal/WMQQueueBrowser.java");
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
/*  63 */   private transient Vector<WMQQueueEnumeration> enumerations = new Vector<>();
/*     */ 
/*     */   
/*     */   private WMQDestination queue;
/*     */   
/*     */   private String selector;
/*     */   
/*  70 */   private transient WMQQueueEnumeration initialEnumeration = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private WMQSession session;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WMQQueueBrowser(WMQSession session, ProviderDestination queue, String selector, JmsPropertyContext jmsProps) throws JMSException {
/*  87 */     super(jmsProps);
/*  88 */     if (Trace.isOn) {
/*  89 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQQueueBrowser", "<init>(WMQSession,ProviderDestination,String,JmsPropertyContext)", new Object[] { session, queue, selector, jmsProps });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  94 */     this.queue = (WMQDestination)queue;
/*  95 */     this.session = session;
/*  96 */     this.selector = selector;
/*     */ 
/*     */ 
/*     */     
/* 100 */     this.initialEnumeration = (WMQQueueEnumeration)getEnumeration();
/*     */     
/* 102 */     if (Trace.isOn) {
/* 103 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQQueueBrowser", "<init>(WMQSession,ProviderDestination,String,JmsPropertyContext)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close(boolean closingFromSession) throws JMSException {
/* 114 */     if (Trace.isOn) {
/* 115 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQQueueBrowser", "close(boolean)", new Object[] {
/* 116 */             Boolean.valueOf(closingFromSession)
/*     */           });
/*     */     }
/*     */     
/* 120 */     for (int i = 0; i < this.enumerations.size(); i++) {
/* 121 */       WMQQueueEnumeration enumVar = this.enumerations.elementAt(i);
/* 122 */       if (enumVar != null) {
/*     */         
/* 124 */         try { enumVar.close();
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
/* 137 */           if (Trace.isOn)
/* 138 */             Trace.finallyBlock(this, "com.ibm.msg.client.wmq.internal.WMQQueueBrowser", "close(boolean)");  } catch (JMSException e) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQQueueBrowser", "close(boolean)", (Throwable)e);  HashMap<String, Object> ffstData = new HashMap<>(); ffstData.put("Exception", e); ffstData.put("Message", "JMSWMQ2000"); Trace.ffst(this, "close()", "XN00G001", ffstData, JMSException.class); } finally { if (Trace.isOn) Trace.finallyBlock(this, "com.ibm.msg.client.wmq.internal.WMQQueueBrowser", "close(boolean)");
/*     */            }
/*     */       
/*     */       }
/*     */     } 
/*     */     
/* 144 */     if (Trace.isOn) {
/* 145 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQQueueBrowser", "close(boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Enumeration<Object> getEnumeration() throws JMSException {
/* 155 */     WMQQueueEnumeration result = null;
/*     */     
/* 157 */     if (this.initialEnumeration != null) {
/* 158 */       result = this.initialEnumeration;
/* 159 */       this.initialEnumeration = null;
/*     */     } else {
/*     */       
/* 162 */       result = new WMQQueueEnumeration((JmsPropertyContext)this, this.session, this.queue, this.selector);
/* 163 */       this.enumerations.addElement(result);
/*     */     } 
/*     */     
/* 166 */     if (Trace.isOn) {
/* 167 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQQueueBrowser", "getEnumeration()", "getter", result);
/*     */     }
/*     */     
/* 170 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\internal\WMQQueueBrowser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */