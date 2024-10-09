/*     */ package com.ibm.msg.client.wmq.compat.base.internal;
/*     */ 
/*     */ import com.ibm.mq.exits.MQCSP;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQConnectionSecurityParameters
/*     */   extends JmqiObject
/*     */ {
/*     */   public static final String MQCSP_STRUCT_ID = "CSP ";
/*     */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQConnectionSecurityParameters.java";
/*     */   
/*     */   static {
/*  71 */     if (Trace.isOn) {
/*  72 */       Trace.data("com.ibm.msg.client.wmq.compat.base.internal.MQConnectionSecurityParameters", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQConnectionSecurityParameters.java");
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
/*  92 */   private MQCSP jmqiStructure = MQSESSION.getJmqiEnv().newMQCSP();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQConnectionSecurityParameters() {
/* 100 */     super(MQSESSION.getJmqiEnv());
/* 101 */     if (Trace.isOn) {
/* 102 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionSecurityParameters", "<init>()");
/*     */     }
/*     */     
/* 105 */     if (Trace.isOn) {
/* 106 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionSecurityParameters", "<init>()");
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
/*     */   public int getAuthenticationType() {
/* 126 */     int traceRet1 = this.jmqiStructure.getAuthenticationType();
/* 127 */     if (Trace.isOn) {
/* 128 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionSecurityParameters", "getAuthenticationType()", "getter", 
/*     */           
/* 130 */           Integer.valueOf(traceRet1));
/*     */     }
/* 132 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCSPPassword() {
/* 142 */     String traceRet1 = this.jmqiStructure.getCspPassword();
/*     */     
/* 144 */     if (Trace.isOn) {
/* 145 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionSecurityParameters", "getCSPPassword()", "getter", (traceRet1 == null) ? traceRet1 : 
/*     */ 
/*     */ 
/*     */           
/* 149 */           Integer.valueOf(traceRet1.length()));
/*     */     }
/* 151 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCSPPasswordLength() {
/* 160 */     String cspPassword = this.jmqiStructure.getCspPassword();
/* 161 */     int traceRet1 = (cspPassword != null) ? cspPassword.length() : 0;
/* 162 */     if (Trace.isOn) {
/* 163 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionSecurityParameters", "getCSPPasswordLength()", "getter", 
/*     */           
/* 165 */           Integer.valueOf(traceRet1));
/*     */     }
/* 167 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCSPUserId() {
/* 176 */     String traceRet1 = this.jmqiStructure.getCspUserId();
/* 177 */     if (Trace.isOn) {
/* 178 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionSecurityParameters", "getCSPUserId()", "getter", traceRet1);
/*     */     }
/*     */ 
/*     */     
/* 182 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCSPUserIdLength() {
/* 191 */     String cspUserId = this.jmqiStructure.getCspUserId();
/* 192 */     int traceRet1 = (cspUserId != null) ? cspUserId.length() : 0;
/* 193 */     if (Trace.isOn) {
/* 194 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionSecurityParameters", "getCSPUserIdLength()", "getter", 
/*     */           
/* 196 */           Integer.valueOf(traceRet1));
/*     */     }
/* 198 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected MQCSP getJMQIStructure() {
/* 206 */     if (Trace.isOn) {
/* 207 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionSecurityParameters", "getJMQIStructure()", "getter", this.jmqiStructure);
/*     */     }
/*     */ 
/*     */     
/* 211 */     return this.jmqiStructure;
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
/*     */   public int getVersion() {
/* 223 */     int traceRet1 = this.jmqiStructure.getVersion();
/* 224 */     if (Trace.isOn) {
/* 225 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionSecurityParameters", "getVersion()", "getter", 
/*     */           
/* 227 */           Integer.valueOf(traceRet1));
/*     */     }
/* 229 */     return traceRet1;
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
/*     */   public void setAuthenticationType(int i) {
/* 245 */     if (Trace.isOn) {
/* 246 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionSecurityParameters", "setAuthenticationType(int)", "setter", 
/*     */           
/* 248 */           Integer.valueOf(i));
/*     */     }
/* 250 */     if (i == 0 || i == 1) {
/* 251 */       this.jmqiStructure.setAuthenticationType(i);
/*     */     } else {
/* 253 */       this.jmqiStructure.setAuthenticationType(0);
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
/*     */   public void setCSPPassword(String password) {
/* 270 */     if (Trace.isOn) {
/* 271 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionSecurityParameters", "setCSPPassword(String)", "setter", (password == null) ? password : 
/*     */ 
/*     */ 
/*     */           
/* 275 */           Integer.valueOf(password.length()));
/*     */     }
/* 277 */     this.jmqiStructure.setCspPassword(password);
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
/*     */   public void setCSPUserId(String id) {
/* 292 */     if (Trace.isOn) {
/* 293 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionSecurityParameters", "setCSPUserId(String)", "setter", id);
/*     */     }
/*     */ 
/*     */     
/* 297 */     this.jmqiStructure.setCspUserId(id);
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
/*     */   public void setInitialKey(String initialKey) {
/* 326 */     throw new UnsupportedOperationException();
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
/*     */   public String getInitialKey() {
/* 347 */     throw new UnsupportedOperationException();
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
/*     */   public int getInitialKeyLength() {
/* 369 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(int i) {
/* 380 */     if (Trace.isOn) {
/* 381 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionSecurityParameters", "setVersion(int)", "setter", 
/*     */           
/* 383 */           Integer.valueOf(i));
/*     */     }
/* 385 */     this.jmqiStructure.setVersion(i);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\base\internal\MQConnectionSecurityParameters.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */