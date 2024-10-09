/*     */ package com.ibm.mq.ese.intercept;
/*     */ 
/*     */ import com.ibm.mq.ese.core.SecurityPolicy;
/*     */ import com.ibm.mq.jmqi.MQGMO;
/*     */ import com.ibm.mq.jmqi.MQMD;
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
/*     */ public final class MQGetContext
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/intercept/MQGetContext.java";
/*     */   private MQGMO origGmo;
/*     */   private SmqiObject smqiObject;
/*     */   private final MQMD origMQMD;
/*     */   private byte[] msgToken;
/*     */   private boolean formatErrorNeglected;
/*     */   private boolean asyncConsume;
/*     */   
/*     */   static {
/*  38 */     if (Trace.isOn) {
/*  39 */       Trace.data("com.ibm.mq.ese.intercept.MQGetContext", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/intercept/MQGetContext.java");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQGetContext(MQMD mqmd, MQGMO mqgmo) {
/*  90 */     if (Trace.isOn) {
/*  91 */       Trace.entry(this, "com.ibm.mq.ese.intercept.MQGetContext", "<init>(MQMD,MQGMO)", new Object[] { mqmd, mqgmo });
/*     */     }
/*     */     
/*  94 */     this.origMQMD = mqmd;
/*  95 */     this.origGmo = mqgmo;
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.exit(this, "com.ibm.mq.ese.intercept.MQGetContext", "<init>(MQMD,MQGMO)");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public MQGetContext(MQMD mqmd, MQGMO mqgmo, SmqiObject smqiObject) {
/* 103 */     this(mqmd, mqgmo);
/* 104 */     if (Trace.isOn) {
/* 105 */       Trace.entry(this, "com.ibm.mq.ese.intercept.MQGetContext", "<init>(MQMD,MQGMO,SmqiObject)", new Object[] { mqmd, mqgmo, smqiObject });
/*     */     }
/*     */     
/* 108 */     this.smqiObject = smqiObject;
/* 109 */     if (Trace.isOn) {
/* 110 */       Trace.exit(this, "com.ibm.mq.ese.intercept.MQGetContext", "<init>(MQMD,MQGMO,SmqiObject)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQGMO getOrigGmo() {
/* 119 */     if (Trace.isOn) {
/* 120 */       Trace.data(this, "com.ibm.mq.ese.intercept.MQGetContext", "getOrigGmo()", "getter", this.origGmo);
/*     */     }
/* 122 */     return this.origGmo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SmqiObject getSmqiObject() {
/* 130 */     if (Trace.isOn) {
/* 131 */       Trace.data(this, "com.ibm.mq.ese.intercept.MQGetContext", "getSmqiObject()", "getter", this.smqiObject);
/*     */     }
/*     */     
/* 134 */     return this.smqiObject;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQMD getOrigMQMD() {
/* 142 */     if (Trace.isOn) {
/* 143 */       Trace.data(this, "com.ibm.mq.ese.intercept.MQGetContext", "getOrigMQMD()", "getter", this.origMQMD);
/*     */     }
/*     */     
/* 146 */     return this.origMQMD;
/*     */   }
/*     */   
/*     */   public void setMsgToken(byte[] msgToken) {
/* 150 */     if (Trace.isOn) {
/* 151 */       Trace.data(this, "com.ibm.mq.ese.intercept.MQGetContext", "setMsgToken(byte [ ])", "setter", msgToken);
/*     */     }
/*     */     
/* 154 */     this.msgToken = msgToken;
/*     */   }
/*     */   
/*     */   public byte[] getMsgToken() {
/* 158 */     if (Trace.isOn) {
/* 159 */       Trace.data(this, "com.ibm.mq.ese.intercept.MQGetContext", "getMsgToken()", "getter", this.msgToken);
/*     */     }
/*     */     
/* 162 */     return this.msgToken;
/*     */   }
/*     */   
/*     */   public void setOrigGmo(MQGMO origGmo) {
/* 166 */     if (Trace.isOn) {
/* 167 */       Trace.data(this, "com.ibm.mq.ese.intercept.MQGetContext", "setOrigGmo(MQGMO)", "setter", origGmo);
/*     */     }
/*     */     
/* 170 */     this.origGmo = origGmo;
/*     */   }
/*     */   
/*     */   public void setSmqiObject(SmqiObject smqiObject) {
/* 174 */     if (Trace.isOn) {
/* 175 */       Trace.data(this, "com.ibm.mq.ese.intercept.MQGetContext", "setSmqiObject(SmqiObject)", "setter", smqiObject);
/*     */     }
/*     */     
/* 178 */     this.smqiObject = smqiObject;
/*     */   }
/*     */   
/*     */   public SecurityPolicy getSecPolicy() {
/* 182 */     if (Trace.isOn) {
/* 183 */       Trace.entry(this, "com.ibm.mq.ese.intercept.MQGetContext", "getSecPolicy()");
/*     */     }
/* 185 */     if (this.smqiObject == null) {
/* 186 */       if (Trace.isOn) {
/* 187 */         Trace.exit(this, "com.ibm.mq.ese.intercept.MQGetContext", "getSecPolicy()", null, 1);
/*     */       }
/* 189 */       return null;
/*     */     } 
/* 191 */     SecurityPolicy traceRet1 = this.smqiObject.getSecPolicy();
/* 192 */     if (Trace.isOn) {
/* 193 */       Trace.exit(this, "com.ibm.mq.ese.intercept.MQGetContext", "getSecPolicy()", traceRet1, 2);
/*     */     }
/* 195 */     return traceRet1;
/*     */   }
/*     */   
/*     */   public void setFormatErrorNeglected(boolean b) {
/* 199 */     if (Trace.isOn) {
/* 200 */       Trace.data(this, "com.ibm.mq.ese.intercept.MQGetContext", "setFormatErrorNeglected(boolean)", "setter", 
/* 201 */           Boolean.valueOf(b));
/*     */     }
/* 203 */     this.formatErrorNeglected = b;
/*     */   }
/*     */   
/*     */   public boolean isFormatErrorNeglected() {
/* 207 */     if (Trace.isOn) {
/* 208 */       Trace.data(this, "com.ibm.mq.ese.intercept.MQGetContext", "isFormatErrorNeglected()", "getter", 
/* 209 */           Boolean.valueOf(this.formatErrorNeglected));
/*     */     }
/* 211 */     return this.formatErrorNeglected;
/*     */   }
/*     */   
/*     */   public void setAsyncConsume(boolean b) {
/* 215 */     if (Trace.isOn) {
/* 216 */       Trace.data(this, "com.ibm.mq.ese.intercept.MQGetContext", "setAsyncConsume(boolean)", "setter", 
/* 217 */           Boolean.valueOf(b));
/*     */     }
/* 219 */     this.asyncConsume = b;
/*     */   }
/*     */   
/*     */   public boolean isAsyncConsume() {
/* 223 */     if (Trace.isOn) {
/* 224 */       Trace.data(this, "com.ibm.mq.ese.intercept.MQGetContext", "isAsyncConsume()", "getter", 
/* 225 */           Boolean.valueOf(this.asyncConsume));
/*     */     }
/* 227 */     return this.asyncConsume;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\intercept\MQGetContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */