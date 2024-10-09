/*     */ package com.ibm.msg.client.services;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Trace
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms/src/com/ibm/msg/client/services/Trace.java";
/*     */   
/*     */   public static boolean isOn() {
/*  73 */     return com.ibm.msg.client.commonservices.trace.Trace.isOn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setStatus(boolean traceOn) {
/*  82 */     com.ibm.msg.client.commonservices.trace.Trace.setOn(traceOn);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setOn() {
/*  89 */     com.ibm.msg.client.commonservices.trace.Trace.setOn(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setOff() {
/*  96 */     com.ibm.msg.client.commonservices.trace.Trace.setOn(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setTraceLevel(int newTraceLevel) {
/* 106 */     com.ibm.msg.client.commonservices.trace.Trace.setTraceLevel(newTraceLevel);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getTraceLevel() {
/* 115 */     return com.ibm.msg.client.commonservices.trace.Trace.getTraceLevel();
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
/*     */   public static void setTraceFileCount(int count) {
/* 128 */     if (!com.ibm.msg.client.commonservices.trace.Trace.isOn) {
/* 129 */       PropertyStore.set("com.ibm.msg.client.commonservices.trace.count", Integer.toString(count));
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
/*     */   public static void setTraceFilename(String filename) {
/* 142 */     if (!com.ibm.msg.client.commonservices.trace.Trace.isOn) {
/* 143 */       PropertyStore.set("com.ibm.msg.client.commonservices.trace.outputName", filename);
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
/*     */   public static void setTraceLimit(int count) {
/* 158 */     if (!com.ibm.msg.client.commonservices.trace.Trace.isOn) {
/* 159 */       PropertyStore.set("com.ibm.msg.client.commonservices.trace.limit", Integer.toString(count));
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
/*     */   public static void setTraceFileAppend(boolean append) {
/* 173 */     if (!com.ibm.msg.client.commonservices.trace.Trace.isOn) {
/* 174 */       PropertyStore.set("com.ibm.msg.client.commonservices.trace.append", Boolean.toString(append));
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
/*     */   public static void inject(String className, String description, Object data) {
/* 188 */     com.ibm.msg.client.commonservices.trace.Trace.traceData(className, description, data);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\services\Trace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */