/*     */ package com.ibm.mq.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsDestination;
/*     */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*     */ import com.ibm.msg.client.jms.JmsTemporaryTopic;
/*     */ import com.ibm.msg.client.jms.admin.JmsDestinationImpl;
/*     */ import com.ibm.msg.client.jms.internal.JmsTemporaryTopicImpl;
/*     */ import com.ibm.msg.client.provider.ProviderDestination;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Map;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.TemporaryTopic;
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
/*     */ public class MQTemporaryTopic
/*     */   extends MQTopic
/*     */   implements TemporaryTopic, JmsTemporaryTopic
/*     */ {
/*     */   private static final long serialVersionUID = -1478617109196523368L;
/*     */   
/*     */   static {
/*  42 */     if (Trace.isOn) {
/*  43 */       Trace.data("com.ibm.mq.jms.MQTemporaryTopic", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQTemporaryTopic.java");
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
/*  62 */   private TemporaryTopic commonTemporaryTopic = null;
/*     */ 
/*     */   
/*     */   MQTemporaryTopic(TemporaryTopic commonTopic) {
/*  66 */     super((JmsDestination)commonTopic);
/*  67 */     if (Trace.isOn) {
/*  68 */       Trace.entry(this, "com.ibm.mq.jms.MQTemporaryTopic", "<init>(TemporaryTopic)", new Object[] { commonTopic });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*  73 */       JmsTemporaryTopicImpl tti = (JmsTemporaryTopicImpl)commonTopic;
/*  74 */       ProviderDestination pDest = _getProviderDestination((JmsDestinationImpl)tti);
/*  75 */       setProviderDestination(pDest);
/*     */     }
/*  77 */     catch (JMSException e) {
/*  78 */       if (Trace.isOn) {
/*  79 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQTemporaryTopic", "<init>(TemporaryTopic)", (Throwable)e);
/*     */       }
/*     */     } 
/*     */     
/*  83 */     this.commonTemporaryTopic = commonTopic;
/*  84 */     if (commonTopic instanceof JmsDestination) {
/*  85 */       this.propertyDelegate = (JmsDestination)commonTopic;
/*     */     }
/*     */     
/*  88 */     if (Trace.isOn) {
/*  89 */       Trace.exit(this, "com.ibm.mq.jms.MQTemporaryTopic", "<init>(TemporaryTopic)");
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
/*     */   public void delete() throws JMSException {
/* 103 */     if (Trace.isOn) {
/* 104 */       Trace.entry(this, "com.ibm.mq.jms.MQTemporaryTopic", "delete()");
/*     */     }
/* 106 */     this.commonTemporaryTopic.delete();
/* 107 */     if (Trace.isOn) {
/* 108 */       Trace.exit(this, "com.ibm.mq.jms.MQTemporaryTopic", "delete()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   TemporaryTopic getCommonTemporaryTopic() {
/* 115 */     if (Trace.isOn) {
/* 116 */       Trace.data(this, "com.ibm.mq.jms.MQTemporaryTopic", "getCommonTemporaryTopic()", "getter", this.commonTemporaryTopic);
/*     */     }
/*     */     
/* 119 */     return this.commonTemporaryTopic;
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
/*     */   public void setObjectProperty(String name, Object value) throws JMSException {
/* 132 */     if (Trace.isOn) {
/* 133 */       Trace.entry(this, "com.ibm.mq.jms.MQTemporaryTopic", "setObjectProperty(String,Object)", new Object[] { name, value });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 139 */     if (this.propertyDelegate != null) {
/* 140 */       this.propertyDelegate.setObjectProperty(name, value);
/*     */     }
/*     */     
/* 143 */     if (Trace.isOn) {
/* 144 */       Trace.exit(this, "com.ibm.mq.jms.MQTemporaryTopic", "setObjectProperty(String,Object)");
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
/*     */   public Object getObjectProperty(String name) throws JMSException {
/* 158 */     if (Trace.isOn) {
/* 159 */       Trace.entry(this, "com.ibm.mq.jms.MQTemporaryTopic", "getObjectProperty(String)", new Object[] { name });
/*     */     }
/*     */ 
/*     */     
/* 163 */     Object traceRet1 = null;
/*     */ 
/*     */ 
/*     */     
/* 167 */     if (this.propertyDelegate != null) {
/* 168 */       traceRet1 = this.propertyDelegate.getObjectProperty(name);
/*     */     }
/*     */     
/* 171 */     if (Trace.isOn) {
/* 172 */       Trace.exit(this, "com.ibm.mq.jms.MQTemporaryTopic", "getObjectProperty(String)", traceRet1);
/*     */     }
/* 174 */     return traceRet1;
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
/* 188 */     if (Trace.isOn) {
/* 189 */       Trace.data(this, "com.ibm.mq.jms.MQTemporaryTopic", "setProviderPropertyContext(com.ibm.msg.client.jms.JmsPropertyContext)", "setter", context);
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
/*     */   public void setBatchProperties(Map<String, Object> properties) throws JMSException {
/* 204 */     if (Trace.isOn) {
/* 205 */       Trace.data(this, "com.ibm.mq.jms.MQTemporaryTopic", "setBatchProperties(Map<String , Object>)", "setter", properties);
/*     */     }
/*     */     
/* 208 */     this.propertyDelegate.setBatchProperties(properties);
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
/* 219 */     Enumeration<String> traceRet1 = this.propertyDelegate.getPropertyNames();
/* 220 */     if (Trace.isOn) {
/* 221 */       Trace.data(this, "com.ibm.mq.jms.MQTemporaryTopic", "getPropertyNames()", "getter", traceRet1);
/*     */     }
/*     */     
/* 224 */     return traceRet1;
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
/* 236 */     if (Trace.isOn) {
/* 237 */       Trace.entry(this, "com.ibm.mq.jms.MQTemporaryTopic", "propertyExists(String)", new Object[] { name });
/*     */     }
/*     */     
/* 240 */     boolean traceRet1 = this.propertyDelegate.propertyExists(name);
/* 241 */     if (Trace.isOn) {
/* 242 */       Trace.exit(this, "com.ibm.mq.jms.MQTemporaryTopic", "propertyExists(String)", 
/* 243 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 245 */     return traceRet1;
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
/* 256 */     if (Trace.isOn) {
/* 257 */       Trace.entry(this, "com.ibm.mq.jms.MQTemporaryTopic", "equals(Object)", new Object[] { obj });
/*     */     }
/*     */     
/* 260 */     if (obj instanceof TemporaryTopic) {
/* 261 */       boolean traceRet1 = this.propertyDelegate.equals(((MQDestination)obj).propertyDelegate);
/* 262 */       if (Trace.isOn) {
/* 263 */         Trace.exit(this, "com.ibm.mq.jms.MQTemporaryTopic", "equals(Object)", 
/* 264 */             Boolean.valueOf(traceRet1), 1);
/*     */       }
/* 266 */       return traceRet1;
/*     */     } 
/*     */     
/* 269 */     if (Trace.isOn) {
/* 270 */       Trace.exit(this, "com.ibm.mq.jms.MQTemporaryTopic", "equals(Object)", Boolean.valueOf(false), 2);
/*     */     }
/*     */     
/* 273 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 281 */     if (Trace.isOn) {
/* 282 */       Trace.entry(this, "com.ibm.mq.jms.MQTemporaryTopic", "hashCode()");
/*     */     }
/* 284 */     int traceRet1 = this.propertyDelegate.hashCode();
/* 285 */     if (Trace.isOn) {
/* 286 */       Trace.exit(this, "com.ibm.mq.jms.MQTemporaryTopic", "hashCode()", Integer.valueOf(traceRet1));
/*     */     }
/*     */     
/* 289 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 299 */     if (Trace.isOn) {
/* 300 */       Trace.entry(this, "com.ibm.mq.jms.MQTemporaryTopic", "toString()");
/*     */     }
/* 302 */     String traceRet1 = this.propertyDelegate.toString();
/* 303 */     if (Trace.isOn) {
/* 304 */       Trace.exit(this, "com.ibm.mq.jms.MQTemporaryTopic", "toString()", traceRet1);
/*     */     }
/* 306 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQTemporaryTopic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */