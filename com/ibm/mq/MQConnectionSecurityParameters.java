/*     */ package com.ibm.mq;
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
/*     */ public class MQConnectionSecurityParameters
/*     */   extends JmqiObject
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/MQConnectionSecurityParameters.java";
/*     */   
/*     */   static {
/*  69 */     if (Trace.isOn) {
/*  70 */       Trace.data("com.ibm.mq.MQConnectionSecurityParameters", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/MQConnectionSecurityParameters.java");
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
/*  85 */   private MQCSP jmqiStructure = JmqiSESSION.getJmqiEnv().newMQCSP();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String MQCSP_STRUCT_ID = "CSP ";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQConnectionSecurityParameters() {
/*  98 */     super(JmqiSESSION.getJmqiEnv());
/*  99 */     if (Trace.isOn) {
/* 100 */       Trace.entry(this, "com.ibm.mq.MQConnectionSecurityParameters", "<init>()");
/*     */     }
/*     */ 
/*     */     
/* 104 */     if (Trace.isOn) {
/* 105 */       Trace.exit(this, "com.ibm.mq.MQConnectionSecurityParameters", "<init>()");
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
/*     */   public void setVersion(int i) {
/* 122 */     if (Trace.isOn) {
/* 123 */       Trace.data(this, "com.ibm.mq.MQConnectionSecurityParameters", "setVersion(int)", "setter", 
/* 124 */           Integer.valueOf(i));
/*     */     }
/* 126 */     this.jmqiStructure.setVersion(i);
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
/*     */   public int getVersion() {
/* 139 */     int traceRet1 = this.jmqiStructure.getVersion();
/* 140 */     if (Trace.isOn) {
/* 141 */       Trace.data(this, "com.ibm.mq.MQConnectionSecurityParameters", "getVersion()", "getter", 
/* 142 */           Integer.valueOf(traceRet1));
/*     */     }
/* 144 */     return traceRet1;
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
/*     */   public void setAuthenticationType(int i) {
/* 162 */     if (Trace.isOn) {
/* 163 */       Trace.data(this, "com.ibm.mq.MQConnectionSecurityParameters", "setAuthenticationType(int)", "setter", 
/* 164 */           Integer.valueOf(i));
/*     */     }
/* 166 */     if (i == 0 || i == 1) {
/* 167 */       this.jmqiStructure.setAuthenticationType(i);
/*     */     } else {
/* 169 */       this.jmqiStructure.setAuthenticationType(0);
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
/*     */   public int getAuthenticationType() {
/* 186 */     int traceRet1 = this.jmqiStructure.getAuthenticationType();
/* 187 */     if (Trace.isOn) {
/* 188 */       Trace.data(this, "com.ibm.mq.MQConnectionSecurityParameters", "getAuthenticationType()", "getter", 
/* 189 */           Integer.valueOf(traceRet1));
/*     */     }
/* 191 */     return traceRet1;
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
/*     */   public void setCSPUserId(String id) {
/* 207 */     if (Trace.isOn) {
/* 208 */       Trace.data(this, "com.ibm.mq.MQConnectionSecurityParameters", "setCSPUserId(String)", "setter", id);
/*     */     }
/*     */     
/* 211 */     this.jmqiStructure.setCspUserId(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCSPUserId() {
/* 221 */     String traceRet1 = this.jmqiStructure.getCspUserId();
/* 222 */     if (Trace.isOn) {
/* 223 */       Trace.data(this, "com.ibm.mq.MQConnectionSecurityParameters", "getCSPUserId()", "getter", traceRet1);
/*     */     }
/*     */     
/* 226 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCSPUserIdLength() {
/* 236 */     String cspUserId = this.jmqiStructure.getCspUserId();
/* 237 */     int traceRet1 = (cspUserId != null) ? cspUserId.length() : 0;
/* 238 */     if (Trace.isOn) {
/* 239 */       Trace.data(this, "com.ibm.mq.MQConnectionSecurityParameters", "getCSPUserIdLength()", "getter", 
/* 240 */           Integer.valueOf(traceRet1));
/*     */     }
/* 242 */     return traceRet1;
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
/*     */   public void setCSPPassword(String password) {
/* 259 */     if (Trace.isOn) {
/* 260 */       Trace.data(this, "com.ibm.mq.MQConnectionSecurityParameters", "setCSPPassword(String)", "setter", (password == null) ? null : "********");
/*     */     }
/*     */     
/* 263 */     this.jmqiStructure.setCspPassword(password);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCSPPassword() {
/* 274 */     String traceRet1 = this.jmqiStructure.getCspPassword();
/* 275 */     if (Trace.isOn) {
/* 276 */       Trace.data(this, "getCSPPassword()", (traceRet1 == null) ? null : "********");
/*     */     }
/* 278 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCSPPasswordLength() {
/* 289 */     String cspPassword = this.jmqiStructure.getCspPassword();
/* 290 */     int traceRet1 = (cspPassword != null) ? cspPassword.length() : 0;
/* 291 */     if (Trace.isOn) {
/* 292 */       Trace.data(this, "getCSPPasswordLength()", Integer.valueOf(-1));
/*     */     }
/* 294 */     return traceRet1;
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
/*     */   public void setInitialKey(String initialKey) {
/* 324 */     throw new UnsupportedOperationException();
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
/*     */   public String getInitialKey() {
/* 344 */     throw new UnsupportedOperationException();
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
/* 366 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected MQCSP getJMQIStructure() {
/* 374 */     if (Trace.isOn) {
/* 375 */       Trace.data(this, "com.ibm.mq.MQConnectionSecurityParameters", "getJMQIStructure()", "getter", this.jmqiStructure);
/*     */     }
/*     */     
/* 378 */     return this.jmqiStructure;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQConnectionSecurityParameters.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */