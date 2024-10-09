/*     */ package com.ibm.mq.jmqi;
/*     */ 
/*     */ import com.ibm.mq.constants.MQConstants;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.lang.ref.Reference;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class JmqiXADecoder
/*     */   extends ConstantDecoder
/*     */   implements JmqiXA
/*     */ {
/*     */   private static Reference<Field[]> fieldsRef;
/*     */   
/*     */   static {
/*  42 */     if (Trace.isOn) {
/*  43 */       Trace.data("com.ibm.mq.jmqi.JmqiXADecoder", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/JmqiXADecoder.java");
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
/*     */   public static String decodeOptions(int options, String prefix) {
/*  56 */     if (Trace.isOn)
/*  57 */       Trace.entry("com.ibm.mq.jmqi.JmqiXADecoder", "decodeOptions(int,String)", new Object[] {
/*  58 */             Integer.valueOf(options), prefix
/*     */           }); 
/*  60 */     String traceRet1 = decodeOptions(options, getFields(), prefix);
/*  61 */     if (Trace.isOn) {
/*  62 */       Trace.exit("com.ibm.mq.jmqi.JmqiXADecoder", "decodeOptions(int,String)", traceRet1);
/*     */     }
/*  64 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String formatOptions(int options, String prefix) {
/*  73 */     if (Trace.isOn)
/*  74 */       Trace.entry("com.ibm.mq.jmqi.JmqiXADecoder", "formatOptions(int,String)", new Object[] {
/*  75 */             Integer.valueOf(options), prefix
/*     */           }); 
/*  77 */     String traceRet1 = formatOptions(options, getFields(), prefix);
/*  78 */     if (Trace.isOn) {
/*  79 */       Trace.exit("com.ibm.mq.jmqi.JmqiXADecoder", "formatOptions(int,String)", traceRet1);
/*     */     }
/*  81 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String decodeSingleOption(int value, String prefix) {
/*  90 */     if (Trace.isOn) {
/*  91 */       Trace.entry("com.ibm.mq.jmqi.JmqiXADecoder", "decodeSingleOption(int,String)", new Object[] {
/*  92 */             Integer.valueOf(value), prefix
/*     */           });
/*     */     }
/*  95 */     String traceRet1 = decodeSingleOption(value, getFields(), prefix);
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.exit("com.ibm.mq.jmqi.JmqiXADecoder", "decodeSingleOption(int,String)", traceRet1);
/*     */     }
/*  99 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String formatSingleOption(int value, String prefix) {
/* 109 */     if (Trace.isOn) {
/* 110 */       Trace.entry("com.ibm.mq.jmqi.JmqiXADecoder", "formatSingleOption(int,String)", new Object[] {
/* 111 */             Integer.valueOf(value), prefix
/*     */           });
/*     */     }
/* 114 */     String traceRet1 = formatSingleOption(value, getFields(), prefix);
/* 115 */     if (Trace.isOn) {
/* 116 */       Trace.exit("com.ibm.mq.jmqi.JmqiXADecoder", "formatSingleOption(int,String)", traceRet1);
/*     */     }
/* 118 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String formatRc(int rc) {
/* 127 */     if (Trace.isOn)
/* 128 */       Trace.entry("com.ibm.mq.jmqi.JmqiXADecoder", "formatRc(int)", new Object[] {
/* 129 */             Integer.valueOf(rc)
/*     */           }); 
/* 131 */     if (rc == 0) {
/* 132 */       String traceRet1 = String.format("%d (0X%x) - XA_OK", new Object[] { Integer.valueOf(rc), Integer.valueOf(rc) });
/* 133 */       if (Trace.isOn) {
/* 134 */         Trace.exit("com.ibm.mq.jmqi.JmqiXADecoder", "formatRc(int)", traceRet1, 1);
/*     */       }
/* 136 */       return traceRet1;
/*     */     } 
/*     */     
/* 139 */     Field[] fields = getFields();
/* 140 */     String decoded = null;
/* 141 */     List<String> rcList = lookup(rc, fields, "XA");
/* 142 */     if (rcList.size() > 0) {
/* 143 */       decoded = rcList.get(0);
/*     */     } else {
/*     */       
/* 146 */       decoded = MQConstants.lookup(rc, "MQRC_");
/*     */     } 
/* 148 */     if (decoded.length() == 0) {
/* 149 */       String traceRet2 = String.format("%d (0X%x)", new Object[] { Integer.valueOf(rc), Integer.valueOf(rc) });
/* 150 */       if (Trace.isOn) {
/* 151 */         Trace.exit("com.ibm.mq.jmqi.JmqiXADecoder", "formatRc(int)", traceRet2, 2);
/*     */       }
/* 153 */       return traceRet2;
/*     */     } 
/*     */     
/* 156 */     String traceRet3 = String.format("%d (0X%x) - %s", new Object[] { Integer.valueOf(rc), Integer.valueOf(rc), decoded });
/* 157 */     if (Trace.isOn) {
/* 158 */       Trace.exit("com.ibm.mq.jmqi.JmqiXADecoder", "formatRc(int)", traceRet3, 3);
/*     */     }
/* 160 */     return traceRet3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static synchronized Field[] getFields() {
/* 168 */     Reference<Field[]> ref = fieldsRef;
/*     */     
/*     */     Field[] fields;
/* 171 */     if (ref == null || (fields = ref.get()) == null) {
/* 172 */       fieldsRef = (Reference)new SoftReference<>(fields = JmqiXADecoder.class.getFields());
/*     */     }
/*     */     
/* 175 */     if (Trace.isOn) {
/* 176 */       Trace.data("com.ibm.mq.jmqi.JmqiXADecoder", "getFields()", "getter", fields);
/*     */     }
/* 178 */     return fields;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\JmqiXADecoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */