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
/*     */ class IntOptionRule
/*     */   extends OptionRule
/*     */ {
/*     */   final MQLongField field;
/*     */   final int value;
/*     */   
/*     */   static {
/* 137 */     if (Trace.isOn) {
/* 138 */       Trace.data("com.ibm.mq.headers.internal.IntOptionRule", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/OptionRule.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   IntOptionRule(MQLongField field, int value) {
/* 148 */     if (Trace.isOn) {
/* 149 */       Trace.entry(this, "com.ibm.mq.headers.internal.IntOptionRule", "<init>(MQLongField,int)", new Object[] { field, 
/* 150 */             Integer.valueOf(value) });
/*     */     }
/* 152 */     if (field == null) {
/*     */       
/* 154 */       IllegalArgumentException traceRet1 = new IllegalArgumentException(HeaderMessages.getMessage("MQHDR0010"));
/*     */       
/* 156 */       if (Trace.isOn) {
/* 157 */         Trace.throwing(this, "com.ibm.mq.headers.internal.IntOptionRule", "<init>(MQLongField,int)", traceRet1);
/*     */       }
/*     */       
/* 160 */       throw traceRet1;
/*     */     } 
/*     */ 
/*     */     
/* 164 */     this.field = field;
/* 165 */     this.value = value;
/* 166 */     if (Trace.isOn) {
/* 167 */       Trace.exit(this, "com.ibm.mq.headers.internal.IntOptionRule", "<init>(MQLongField,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPresent(Header h) {
/* 177 */     if (Trace.isOn) {
/* 178 */       Trace.entry(this, "com.ibm.mq.headers.internal.IntOptionRule", "isPresent(Header)", new Object[] { h });
/*     */     }
/*     */     
/* 181 */     boolean traceRet1 = (this.field.getIntValue(h) >= this.value);
/*     */     
/* 183 */     if (Trace.isOn) {
/* 184 */       Trace.exit(this, "com.ibm.mq.headers.internal.IntOptionRule", "isPresent(Header)", 
/* 185 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 187 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPresent(Header h) {
/* 195 */     if (Trace.isOn) {
/* 196 */       Trace.data(this, "com.ibm.mq.headers.internal.IntOptionRule", "setPresent(Header)", "setter", h);
/*     */     }
/*     */     
/* 199 */     this.field.setIntValue(h, this.value);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\internal\IntOptionRule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */