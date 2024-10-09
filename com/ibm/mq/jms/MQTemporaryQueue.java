/*     */ package com.ibm.mq.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsDestination;
/*     */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*     */ import com.ibm.msg.client.jms.JmsTemporaryQueue;
/*     */ import com.ibm.msg.client.jms.admin.JmsDestinationImpl;
/*     */ import com.ibm.msg.client.jms.internal.JmsTemporaryQueueImpl;
/*     */ import com.ibm.msg.client.provider.ProviderDestination;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Map;
/*     */ import javax.jms.JMSException;
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
/*     */ public class MQTemporaryQueue
/*     */   extends MQQueue
/*     */   implements TemporaryQueue, JmsTemporaryQueue
/*     */ {
/*     */   private static final long serialVersionUID = 9113157886341016039L;
/*     */   
/*     */   static {
/*  44 */     if (Trace.isOn) {
/*  45 */       Trace.data("com.ibm.mq.jms.MQTemporaryQueue", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQTemporaryQueue.java");
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
/*  64 */   private TemporaryQueue commonTemporaryQueue = null;
/*     */ 
/*     */ 
/*     */   
/*     */   MQTemporaryQueue(TemporaryQueue commonQueue) {
/*  69 */     super((JmsDestination)commonQueue);
/*  70 */     if (Trace.isOn) {
/*  71 */       Trace.entry(this, "com.ibm.mq.jms.MQTemporaryQueue", "<init>(TemporaryQueue)", new Object[] { commonQueue });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*  76 */       JmsTemporaryQueueImpl tqi = (JmsTemporaryQueueImpl)commonQueue;
/*  77 */       ProviderDestination pDest = _getProviderDestination((JmsDestinationImpl)tqi);
/*  78 */       setProviderDestination(pDest);
/*     */     }
/*  80 */     catch (JMSException e) {
/*  81 */       if (Trace.isOn) {
/*  82 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQTemporaryQueue", "<init>(TemporaryQueue)", (Throwable)e, 1);
/*     */       }
/*     */     } 
/*     */     
/*  86 */     this.commonTemporaryQueue = commonQueue;
/*  87 */     if (commonQueue instanceof JmsDestination) {
/*  88 */       this.propertyDelegate = (JmsDestination)commonQueue;
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
/*     */     
/*     */     try {
/* 101 */       setPersistence(1);
/*     */     }
/* 103 */     catch (JMSException e) {
/* 104 */       if (Trace.isOn) {
/* 105 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQTemporaryQueue", "<init>(TemporaryQueue)", (Throwable)e, 2);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 112 */     if (Trace.isOn) {
/* 113 */       Trace.exit(this, "com.ibm.mq.jms.MQTemporaryQueue", "<init>(TemporaryQueue)");
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
/*     */   public void delete() throws JMSException {
/* 126 */     if (Trace.isOn) {
/* 127 */       Trace.entry(this, "com.ibm.mq.jms.MQTemporaryQueue", "delete()");
/*     */     }
/* 129 */     this.commonTemporaryQueue.delete();
/* 130 */     if (Trace.isOn) {
/* 131 */       Trace.exit(this, "com.ibm.mq.jms.MQTemporaryQueue", "delete()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   TemporaryQueue getCommonTemporaryQueue() {
/* 138 */     if (Trace.isOn) {
/* 139 */       Trace.data(this, "com.ibm.mq.jms.MQTemporaryQueue", "getCommonTemporaryQueue()", "getter", this.commonTemporaryQueue);
/*     */     }
/*     */     
/* 142 */     return this.commonTemporaryQueue;
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
/*     */   public void setObjectProperty(String name, Object value) throws JMSException {
/* 157 */     if (Trace.isOn) {
/* 158 */       Trace.entry(this, "com.ibm.mq.jms.MQTemporaryQueue", "setObjectProperty(String,Object)", new Object[] { name, value });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 164 */     if (this.propertyDelegate != null) {
/* 165 */       this.propertyDelegate.setObjectProperty(name, value);
/*     */     }
/*     */     
/* 168 */     if (Trace.isOn) {
/* 169 */       Trace.exit(this, "com.ibm.mq.jms.MQTemporaryQueue", "setObjectProperty(String,Object)");
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
/*     */   public Object getObjectProperty(String name) throws JMSException {
/* 185 */     if (Trace.isOn) {
/* 186 */       Trace.entry(this, "com.ibm.mq.jms.MQTemporaryQueue", "getObjectProperty(String)", new Object[] { name });
/*     */     }
/*     */ 
/*     */     
/* 190 */     Object traceRet1 = null;
/*     */ 
/*     */ 
/*     */     
/* 194 */     if (this.propertyDelegate != null) {
/* 195 */       traceRet1 = this.propertyDelegate.getObjectProperty(name);
/*     */     }
/*     */     
/* 198 */     if (Trace.isOn) {
/* 199 */       Trace.exit(this, "com.ibm.mq.jms.MQTemporaryQueue", "getObjectProperty(String)", traceRet1);
/*     */     }
/* 201 */     return traceRet1;
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
/*     */   public void setProviderPropertyContext(JmsPropertyContext context) {
/* 215 */     if (Trace.isOn) {
/* 216 */       Trace.data(this, "com.ibm.mq.jms.MQTemporaryQueue", "setProviderPropertyContext(JmsPropertyContext)", "setter", context);
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
/*     */   public void setBatchProperties(Map<String, Object> properties) throws JMSException {
/* 230 */     if (Trace.isOn) {
/* 231 */       Trace.data(this, "com.ibm.mq.jms.MQTemporaryQueue", "setBatchProperties(Map<String , Object>)", "setter", properties);
/*     */     }
/*     */     
/* 234 */     this.propertyDelegate.setBatchProperties(properties);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Enumeration<String> getPropertyNames() throws JMSException {
/* 245 */     Enumeration<String> traceRet1 = this.propertyDelegate.getPropertyNames();
/* 246 */     if (Trace.isOn) {
/* 247 */       Trace.data(this, "com.ibm.mq.jms.MQTemporaryQueue", "getPropertyNames()", "getter", traceRet1);
/*     */     }
/*     */     
/* 250 */     return traceRet1;
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
/*     */   public boolean propertyExists(String name) throws JMSException {
/* 262 */     if (Trace.isOn) {
/* 263 */       Trace.entry(this, "com.ibm.mq.jms.MQTemporaryQueue", "propertyExists(String)", new Object[] { name });
/*     */     }
/*     */     
/* 266 */     boolean traceRet1 = this.propertyDelegate.propertyExists(name);
/* 267 */     if (Trace.isOn) {
/* 268 */       Trace.exit(this, "com.ibm.mq.jms.MQTemporaryQueue", "propertyExists(String)", 
/* 269 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 271 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 282 */     if (Trace.isOn) {
/* 283 */       Trace.entry(this, "com.ibm.mq.jms.MQTemporaryQueue", "equals(java.lang.Object)", new Object[] { obj });
/*     */     }
/*     */ 
/*     */     
/* 287 */     if (obj instanceof TemporaryQueue) {
/* 288 */       boolean traceRet1 = this.propertyDelegate.equals(((MQDestination)obj).propertyDelegate);
/* 289 */       if (Trace.isOn) {
/* 290 */         Trace.exit(this, "com.ibm.mq.jms.MQTemporaryQueue", "equals(java.lang.Object)", 
/* 291 */             Boolean.valueOf(traceRet1), 1);
/*     */       }
/* 293 */       return traceRet1;
/*     */     } 
/*     */     
/* 296 */     if (Trace.isOn) {
/* 297 */       Trace.exit(this, "com.ibm.mq.jms.MQTemporaryQueue", "equals(java.lang.Object)", 
/* 298 */           Boolean.valueOf(false), 2);
/*     */     }
/* 300 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 308 */     if (Trace.isOn) {
/* 309 */       Trace.entry(this, "com.ibm.mq.jms.MQTemporaryQueue", "hashCode()");
/*     */     }
/* 311 */     int traceRet1 = this.propertyDelegate.hashCode();
/* 312 */     if (Trace.isOn) {
/* 313 */       Trace.exit(this, "com.ibm.mq.jms.MQTemporaryQueue", "hashCode()", Integer.valueOf(traceRet1));
/*     */     }
/*     */     
/* 316 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 326 */     if (Trace.isOn) {
/* 327 */       Trace.entry(this, "com.ibm.mq.jms.MQTemporaryQueue", "toString()");
/*     */     }
/* 329 */     String traceRet1 = (this.propertyDelegate == null) ? "<null>" : this.propertyDelegate.toString();
/* 330 */     if (Trace.isOn) {
/* 331 */       Trace.exit(this, "com.ibm.mq.jms.MQTemporaryQueue", "toString()", traceRet1);
/*     */     }
/* 333 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQTemporaryQueue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */