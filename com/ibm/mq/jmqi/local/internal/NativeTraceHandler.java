/*     */ package com.ibm.mq.jmqi.local.internal;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.nio.charset.CharacterCodingException;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NativeTraceHandler
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/internal/NativeTraceHandler.java";
/*     */   private JmqiCodepage nativeCp;
/*     */   
/*     */   static {
/*  40 */     if (Trace.isOn) {
/*  41 */       Trace.data("com.ibm.mq.jmqi.local.internal.NativeTraceHandler", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/internal/NativeTraceHandler.java");
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
/*     */   public NativeTraceHandler(JmqiEnvironment env) {
/*  57 */     this.nativeCp = env.getNativeCharSet();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void data(int compID, int funcID, byte[] description, Object data) {
/*  68 */     String jdescription = null;
/*     */     try {
/*  70 */       if (description != null) {
/*  71 */         jdescription = this.nativeCp.bytesToString(description);
/*     */       }
/*     */     }
/*  74 */     catch (CharacterCodingException e) {
/*  75 */       jdescription = e.getClass().getName() + ": " + e.getMessage() + ", nativeCp:" + this.nativeCp;
/*     */     } 
/*  77 */     Trace.data("jmqi.local", TraceNames.getName(funcID), jdescription, data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void entry(int compID, int funcID) {
/*  86 */     Trace.entry("jmqi.local", TraceNames.getName(funcID));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void exit(int compID, int funcID, int value) {
/*  96 */     Trace.exit("jmqi.local", TraceNames.getName(funcID), value);
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
/*     */   public void ffst(int compID, int funcID, int probe, int rc, int insert1, int insert2, byte[] insert3, byte[] insert4, byte[] insert5) {
/* 112 */     String jinsert3 = null;
/* 113 */     String jinsert4 = null;
/* 114 */     String jinsert5 = null;
/*     */     try {
/* 116 */       if (insert3 != null) {
/* 117 */         jinsert3 = this.nativeCp.bytesToString(insert3);
/*     */       }
/* 119 */       if (insert4 != null) {
/* 120 */         jinsert4 = this.nativeCp.bytesToString(insert3);
/*     */       }
/* 122 */       if (insert5 != null) {
/* 123 */         jinsert5 = this.nativeCp.bytesToString(insert3);
/*     */       }
/*     */     }
/* 126 */     catch (CharacterCodingException e) {
/* 127 */       jinsert3 = e.getClass().getName() + ": " + e.getMessage() + ", nativeCp:" + this.nativeCp;
/*     */     } 
/*     */     
/* 130 */     HashMap<String, Object> ffstInfo = new HashMap<>();
/* 131 */     ffstInfo.put("Insert1", Integer.valueOf(insert1));
/* 132 */     ffstInfo.put("Insert2", Integer.valueOf(insert2));
/* 133 */     ffstInfo.put("Insert3", jinsert3);
/* 134 */     ffstInfo.put("Insert4", jinsert4);
/* 135 */     ffstInfo.put("Insert5", jinsert5);
/* 136 */     Trace.ffst("jmqi.local", TraceNames.getName(funcID), Integer.toString(probe), ffstInfo, null);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\local\internal\NativeTraceHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */