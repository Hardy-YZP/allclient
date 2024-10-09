/*     */ package com.ibm.mq.jmqi.internal;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JmqiStructureFormatter
/*     */   extends JmqiObject
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/internal/JmqiStructureFormatter.java";
/*     */   private StringBuffer buffer;
/*     */   private int width1;
/*     */   private int width2;
/*     */   private String separator;
/*     */   
/*     */   public JmqiStructureFormatter(JmqiEnvironment env, int width1, int width2, String separator) {
/*  51 */     super(env);
/*  52 */     this.buffer = new StringBuffer();
/*  53 */     this.width1 = width1;
/*  54 */     this.width2 = width2;
/*  55 */     this.separator = separator;
/*     */   }
/*     */ 
/*     */   
/*     */   private void addField(String lquote, String rquote, String name, String value) {
/*  60 */     this.buffer.append(JmqiTools.left(name, this.width1));
/*  61 */     this.buffer.append(JmqiTools.left(":", this.width2));
/*  62 */     this.buffer.append(lquote);
/*  63 */     this.buffer.append(value);
/*  64 */     this.buffer.append(rquote);
/*  65 */     this.buffer.append(this.separator);
/*     */   }
/*     */ 
/*     */   
/*     */   private void addField(String lquote, String rquote, int name, String value) {
/*  70 */     this.buffer.append(JmqiTools.right(Integer.toString(name), this.width1));
/*  71 */     this.buffer.append(JmqiTools.left(":", this.width2));
/*  72 */     this.buffer.append(lquote);
/*  73 */     this.buffer.append(value);
/*  74 */     this.buffer.append(rquote);
/*  75 */     this.buffer.append(this.separator);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(String name, int value) {
/*  84 */     addField("", "", name, formatInt(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(String name, long value) {
/*  93 */     addField("", "", name, formatLong(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(String name, boolean value) {
/* 102 */     addField("", "", name, Boolean.toString(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(String name, String value) {
/* 111 */     addField("'", "'", name, JmqiTools.safeString(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(String name, String[] value) {
/* 120 */     if (value != null) {
/* 121 */       this.buffer.append(JmqiTools.left(name, this.width1));
/* 122 */       this.buffer.append(":");
/* 123 */       this.buffer.append(this.separator);
/* 124 */       for (int i = 0; i < value.length; i++) {
/* 125 */         addField("(", ")", i, JmqiTools.safeString(value[i]));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(String name, int[] value) {
/* 136 */     this.buffer.append(JmqiTools.left(name, this.width1));
/* 137 */     this.buffer.append(JmqiTools.left(":", this.width2));
/* 138 */     this.buffer.append("(");
/* 139 */     for (int i = 0; i < value.length; i++) {
/* 140 */       if (i > 0) {
/* 141 */         this.buffer.append(',');
/*     */       }
/* 143 */       this.buffer.append(formatInt(value[i]));
/*     */     } 
/* 145 */     this.buffer.append(")");
/*     */   }
/*     */ 
/*     */   
/*     */   private String formatInt(int i) {
/* 150 */     String traceRet1 = String.format("%d(0x%x)", new Object[] { Integer.valueOf(i), Integer.valueOf(i) });
/* 151 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   private String formatLong(long l) {
/* 156 */     String traceRet1 = String.format("%d(0x%x)", new Object[] { Long.valueOf(l), Long.valueOf(l) });
/* 157 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(String name, byte[] value) {
/* 166 */     addField("", "", name, JmqiTools.arrayToHexString(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(String name, Object value) {
/* 175 */     addField("[", "]", name, JmqiTools.safeString(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmqiStructureFormatter append(String value) {
/* 184 */     this.buffer.append(value);
/* 185 */     this.buffer.append(this.separator);
/* 186 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 195 */     return this.buffer.toString();
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\internal\JmqiStructureFormatter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */