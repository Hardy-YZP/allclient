/*     */ package com.ibm.mq.headers.internal;
/*     */ 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class StringOptionRule
/*     */   extends OptionRule
/*     */ {
/*     */   final MQCharField field;
/*     */   final String value;
/*     */   
/*     */   static {
/* 205 */     if (Trace.isOn) {
/* 206 */       Trace.data("com.ibm.mq.headers.internal.StringOptionRule", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/OptionRule.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   StringOptionRule(MQCharField field, String value) {
/* 216 */     if (Trace.isOn) {
/* 217 */       Trace.entry(this, "com.ibm.mq.headers.internal.StringOptionRule", "<init>(MQCharField,String)", new Object[] { field, value });
/*     */     }
/*     */     
/* 220 */     if (field == null) {
/*     */       
/* 222 */       IllegalArgumentException traceRet1 = new IllegalArgumentException(HeaderMessages.getMessage("MQHDR0010"));
/*     */       
/* 224 */       if (Trace.isOn) {
/* 225 */         Trace.throwing(this, "com.ibm.mq.headers.internal.StringOptionRule", "<init>(MQCharField,String)", traceRet1, 1);
/*     */       }
/*     */       
/* 228 */       throw traceRet1;
/*     */     } 
/*     */ 
/*     */     
/* 232 */     if (value == null) {
/*     */       
/* 234 */       IllegalArgumentException traceRet2 = new IllegalArgumentException(HeaderMessages.getMessage("MQHDR0029"));
/* 235 */       if (Trace.isOn) {
/* 236 */         Trace.throwing(this, "com.ibm.mq.headers.internal.StringOptionRule", "<init>(MQCharField,String)", traceRet2, 2);
/*     */       }
/*     */       
/* 239 */       throw traceRet2;
/*     */     } 
/*     */ 
/*     */     
/* 243 */     this.field = field;
/* 244 */     this.value = value;
/* 245 */     if (Trace.isOn) {
/* 246 */       Trace.exit(this, "com.ibm.mq.headers.internal.StringOptionRule", "<init>(MQCharField,String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPresent(Header h) {
/* 257 */     if (Trace.isOn) {
/* 258 */       Trace.entry(this, "com.ibm.mq.headers.internal.StringOptionRule", "isPresent(Header)", new Object[] { h });
/*     */     }
/*     */     
/* 261 */     boolean traceRet1 = this.value.equals(this.field.getValue(h));
/*     */     
/* 263 */     if (Trace.isOn) {
/* 264 */       Trace.exit(this, "com.ibm.mq.headers.internal.StringOptionRule", "isPresent(Header)", 
/* 265 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 267 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPresent(Header h) {
/* 275 */     if (Trace.isOn) {
/* 276 */       Trace.data(this, "com.ibm.mq.headers.internal.StringOptionRule", "setPresent(Header)", "setter", h);
/*     */     }
/*     */     
/* 279 */     this.field.setValue(h, this.value);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\internal\StringOptionRule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */