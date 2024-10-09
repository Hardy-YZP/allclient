/*     */ package com.ibm.mq.jmqi.remote.util;
/*     */ 
/*     */ import com.ibm.mq.jmqi.ConstantDecoder;
/*     */ import com.ibm.mq.jmqi.remote.impl.RemoteConstants;
/*     */ import com.ibm.mq.jmqi.remote.rfp.RfpConst;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.lang.ref.Reference;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.lang.reflect.Field;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RemoteConstantDecoder
/*     */   extends ConstantDecoder
/*     */   implements RfpConst, RemoteConstants
/*     */ {
/*     */   private static Reference<Field[]> fieldsRef;
/*     */   
/*     */   static {
/*  43 */     if (Trace.isOn) {
/*  44 */       Trace.data("com.ibm.mq.jmqi.remote.util.RemoteConstantDecoder", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/util/RemoteConstantDecoder.java");
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
/*     */   public static String decodeOptions(int options, String prefix) {
/*  60 */     String traceRet1 = decodeOptions(options, getFields(), prefix);
/*  61 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String formatOptions(int options, String prefix) {
/*  70 */     String traceRet1 = formatOptions(options, getFields(), prefix);
/*  71 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String decodeSingleOption(int value, String prefix) {
/*  80 */     String traceRet1 = decodeSingleOption(value, getFields(), prefix);
/*  81 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String formatSingleOption(int value, String prefix) {
/*  90 */     String traceRet1 = formatSingleOption(value, getFields(), prefix);
/*  91 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String formatRequestId(int value) {
/*     */     String traceRet1, traceRet2;
/*  99 */     switch (value) {
/*     */       case 0:
/* 101 */         traceRet1 = String.format("%d - syncRequestId", new Object[] { Integer.valueOf(value) });
/* 102 */         return traceRet1;
/*     */       
/*     */       case 1:
/* 105 */         traceRet2 = String.format("%d - asyncRequestId", new Object[] { Integer.valueOf(value) });
/* 106 */         return traceRet2;
/*     */     } 
/*     */     
/* 109 */     String traceRet3 = String.format("%d - requestId", new Object[] { Integer.valueOf(value) });
/* 110 */     return traceRet3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static synchronized Field[] getFields() {
/* 120 */     Reference<Field[]> ref = fieldsRef;
/*     */     
/*     */     Field[] fields;
/* 123 */     if (ref == null || (fields = ref.get()) == null) {
/* 124 */       fieldsRef = (Reference)new SoftReference<>(fields = RemoteConstantDecoder.class.getFields());
/*     */     }
/*     */     
/* 127 */     return fields;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remot\\util\RemoteConstantDecoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */