/*     */ package com.ibm.mq;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.Hashtable;
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
/*     */ class QueueManagerFactoryProperties
/*     */   extends JmqiObject
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/QueueManagerFactoryProperties.java";
/*     */   private String name;
/*     */   private Hashtable properties;
/*     */   private MQConnectionManager conMgr;
/*     */   private MQQueueManager mgr;
/*     */   
/*     */   static {
/*  43 */     if (Trace.isOn) {
/*  44 */       Trace.data("com.ibm.mq.QueueManagerFactoryProperties", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/QueueManagerFactoryProperties.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public QueueManagerFactoryProperties() {
/*  55 */     super(MQSESSION.getJmqiEnv());
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
/*  71 */     this.name = null;
/*  72 */     this.properties = null;
/*     */     
/*  74 */     this.conMgr = null;
/*  75 */     this.mgr = null;
/*     */     if (Trace.isOn) {
/*     */       Trace.entry(this, "com.ibm.mq.QueueManagerFactoryProperties", "<init>()");
/*     */     }
/*     */     if (Trace.isOn) {
/*     */       Trace.exit(this, "com.ibm.mq.QueueManagerFactoryProperties", "<init>()");
/*     */     }
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  85 */     if (Trace.isOn) {
/*  86 */       Trace.data(this, "com.ibm.mq.QueueManagerFactoryProperties", "setName(String)", "setter", name);
/*     */     }
/*     */     
/*  89 */     this.name = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  99 */     if (Trace.isOn) {
/* 100 */       Trace.data(this, "com.ibm.mq.QueueManagerFactoryProperties", "getName()", "getter", this.name);
/*     */     }
/* 102 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setProperties(Hashtable properties) {
/* 113 */     if (Trace.isOn) {
/* 114 */       Trace.data(this, "com.ibm.mq.QueueManagerFactoryProperties", "setProperties(Hashtable)", "setter", 
/* 115 */           Trace.sanitizeMap(properties, "password", "XXXXXXXXXXXX"));
/*     */     }
/* 117 */     this.properties = properties;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Hashtable getProperties() {
/* 128 */     if (Trace.isOn) {
/* 129 */       Trace.data(this, "com.ibm.mq.QueueManagerFactoryProperties", "getProperties()", "getter", 
/* 130 */           Trace.sanitizeMap(this.properties, "password", "XXXXXXXXXXXX"));
/*     */     }
/* 132 */     return this.properties;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConMgr(MQConnectionManager conMgr) {
/* 143 */     if (Trace.isOn) {
/* 144 */       Trace.data(this, "com.ibm.mq.QueueManagerFactoryProperties", "setConMgr(MQConnectionManager)", "setter", conMgr);
/*     */     }
/*     */     
/* 147 */     this.conMgr = conMgr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQConnectionManager getConMgr() {
/* 157 */     if (Trace.isOn) {
/* 158 */       Trace.data(this, "com.ibm.mq.QueueManagerFactoryProperties", "getConMgr()", "getter", this.conMgr);
/*     */     }
/*     */     
/* 161 */     return this.conMgr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isValid() {
/* 171 */     if (Trace.isOn) {
/* 172 */       Trace.data(this, "com.ibm.mq.QueueManagerFactoryProperties", "isValid()", "getter", 
/* 173 */           Boolean.valueOf(true));
/*     */     }
/* 175 */     return true;
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
/*     */   public String toString() {
/* 187 */     if (Trace.isOn) {
/* 188 */       Trace.entry(this, "com.ibm.mq.QueueManagerFactoryProperties", "toString()");
/*     */     }
/* 190 */     StringBuffer strbf = new StringBuffer();
/* 191 */     strbf.append("qmName:     " + this.name + "\n");
/* 192 */     strbf.append("properties: " + Trace.sanitizeMap(this.properties, "password", "XXXXXXXXXXXX") + "\n");
/* 193 */     strbf.append("conMgr    : " + this.conMgr);
/* 194 */     String traceRet1 = strbf.toString();
/*     */     
/* 196 */     if (Trace.isOn) {
/* 197 */       Trace.exit(this, "com.ibm.mq.QueueManagerFactoryProperties", "toString()", traceRet1);
/*     */     }
/* 199 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setMgr(MQQueueManager mgr) {
/* 209 */     if (Trace.isOn) {
/* 210 */       Trace.data(this, "com.ibm.mq.QueueManagerFactoryProperties", "setMgr(MQQueueManager)", "setter", mgr);
/*     */     }
/*     */     
/* 213 */     this.mgr = mgr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   MQQueueManager getMgr() {
/* 221 */     if (Trace.isOn) {
/* 222 */       Trace.data(this, "com.ibm.mq.QueueManagerFactoryProperties", "getMgr()", "getter", this.mgr);
/*     */     }
/* 224 */     return this.mgr;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\QueueManagerFactoryProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */