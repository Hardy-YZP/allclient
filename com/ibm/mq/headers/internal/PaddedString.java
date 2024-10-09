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
/*     */ public class PaddedString
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/PaddedString.java";
/*     */   
/*     */   static {
/*  34 */     if (Trace.isOn) {
/*  35 */       Trace.data("com.ibm.mq.headers.internal.PaddedString", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/PaddedString.java");
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
/*     */   public static String pad(String string, int length) {
/*  49 */     if (Trace.isOn) {
/*  50 */       Trace.entry("com.ibm.mq.headers.internal.PaddedString", "pad(String,int)", new Object[] { string, 
/*  51 */             Integer.valueOf(length) });
/*     */     }
/*  53 */     String traceRet1 = pad(string, ' ', length, false);
/*  54 */     if (Trace.isOn) {
/*  55 */       Trace.exit("com.ibm.mq.headers.internal.PaddedString", "pad(String,int)", traceRet1);
/*     */     }
/*     */     
/*  58 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String pad(String string, char pad, int length) {
/*  68 */     if (Trace.isOn) {
/*  69 */       Trace.entry("com.ibm.mq.headers.internal.PaddedString", "pad(String,char,int)", new Object[] { string, 
/*  70 */             Character.valueOf(pad), Integer.valueOf(length) });
/*     */     }
/*  72 */     String traceRet1 = pad(string, pad, length, false);
/*  73 */     if (Trace.isOn) {
/*  74 */       Trace.exit("com.ibm.mq.headers.internal.PaddedString", "pad(String,char,int)", traceRet1);
/*     */     }
/*     */     
/*  77 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String pad(String string, char pad, int lengthP, boolean truncate) {
/*  88 */     if (Trace.isOn) {
/*  89 */       Trace.entry("com.ibm.mq.headers.internal.PaddedString", "pad(String,char,int,boolean)", new Object[] { string, 
/*  90 */             Character.valueOf(pad), Integer.valueOf(lengthP), 
/*  91 */             Boolean.valueOf(truncate) });
/*     */     }
/*  93 */     int length = lengthP;
/*  94 */     StringBuilder sb = new StringBuilder(length);
/*     */     
/*  96 */     sb.append(string);
/*  97 */     length -= string.length();
/*     */     
/*  99 */     if (truncate && length < 0) {
/* 100 */       sb.setLength(sb.length() + length);
/*     */     }
/*     */     
/* 103 */     while (length-- > 0) {
/* 104 */       sb.append(pad);
/*     */     }
/*     */     
/* 107 */     String traceRet1 = new String(sb);
/* 108 */     if (Trace.isOn) {
/* 109 */       Trace.exit("com.ibm.mq.headers.internal.PaddedString", "pad(String,char,int,boolean)", traceRet1);
/*     */     }
/*     */     
/* 112 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String lead(int value, int length) {
/* 121 */     if (Trace.isOn)
/* 122 */       Trace.entry("com.ibm.mq.headers.internal.PaddedString", "lead(int,int)", new Object[] {
/* 123 */             Integer.valueOf(value), Integer.valueOf(length)
/*     */           }); 
/* 125 */     String traceRet1 = lead(value, ' ', length);
/* 126 */     if (Trace.isOn) {
/* 127 */       Trace.exit("com.ibm.mq.headers.internal.PaddedString", "lead(int,int)", traceRet1);
/*     */     }
/* 129 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String lead(int value, char pad, int lengthP) {
/* 139 */     if (Trace.isOn)
/* 140 */       Trace.entry("com.ibm.mq.headers.internal.PaddedString", "lead(int,char,int)", new Object[] {
/* 141 */             Integer.valueOf(value), Character.valueOf(pad), Integer.valueOf(lengthP)
/*     */           }); 
/* 143 */     int length = lengthP;
/* 144 */     StringBuilder sb = new StringBuilder(length);
/* 145 */     String string = Integer.toString(value);
/*     */     
/* 147 */     length -= string.length();
/*     */     
/* 149 */     while (length-- > 0) {
/* 150 */       sb.append(pad);
/*     */     }
/*     */     
/* 153 */     sb.append(string);
/*     */     
/* 155 */     String traceRet1 = new String(sb);
/* 156 */     if (Trace.isOn) {
/* 157 */       Trace.exit("com.ibm.mq.headers.internal.PaddedString", "lead(int,char,int)", traceRet1);
/*     */     }
/*     */     
/* 160 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\internal\PaddedString.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */