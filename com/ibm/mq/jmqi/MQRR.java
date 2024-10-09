/*     */ package com.ibm.mq.jmqi;
/*     */ 
/*     */ import com.ibm.mq.jmqi.internal.AbstractMqiStructure;
/*     */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*     */ import com.ibm.mq.jmqi.internal.JmqiStructureFormatter;
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*     */ import com.ibm.mq.jmqi.system.JmqiTls;
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
/*     */ public class MQRR
/*     */   extends AbstractMqiStructure
/*     */ {
/*     */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQRR.java";
/*     */   private static final int SIZEOF_COMP_CODE = 4;
/*     */   private static final int SIZEOF_REASON = 4;
/*     */   
/*     */   static {
/*  40 */     if (Trace.isOn) {
/*  41 */       Trace.data("com.ibm.mq.jmqi.MQRR", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQRR.java");
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
/*  60 */   private int compCode = 0;
/*  61 */   private int reason = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSize(JmqiEnvironment env, int sizeofNativePointer) {
/*  71 */     int structureSize = 8;
/*  72 */     return structureSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected MQRR(JmqiEnvironment env) {
/*  81 */     super(env);
/*  82 */     if (Trace.isOn) {
/*  83 */       Trace.entry(this, "com.ibm.mq.jmqi.MQRR", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/*  85 */     if (Trace.isOn) {
/*  86 */       Trace.exit(this, "com.ibm.mq.jmqi.MQRR", "<init>(JmqiEnvironment)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCompCode() {
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.data(this, "com.ibm.mq.jmqi.MQRR", "getCompCode()", "getter", 
/*  98 */           Integer.valueOf(this.compCode));
/*     */     }
/* 100 */     return this.compCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCompCode(int compCode) {
/* 110 */     if (Trace.isOn) {
/* 111 */       Trace.data(this, "com.ibm.mq.jmqi.MQRR", "setCompCode(int)", "setter", 
/* 112 */           Integer.valueOf(compCode));
/*     */     }
/* 114 */     this.compCode = compCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getReason() {
/* 122 */     if (Trace.isOn) {
/* 123 */       Trace.data(this, "com.ibm.mq.jmqi.MQRR", "getReason()", "getter", Integer.valueOf(this.reason));
/*     */     }
/* 125 */     return this.reason;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReason(int reason) {
/* 135 */     if (Trace.isOn) {
/* 136 */       Trace.data(this, "com.ibm.mq.jmqi.MQRR", "setReason(int)", "setter", Integer.valueOf(reason));
/*     */     }
/*     */     
/* 139 */     this.reason = reason;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/* 150 */     return getSize(this.env, ptrSize);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 159 */     if (Trace.isOn) {
/* 160 */       Trace.entry(this, "com.ibm.mq.jmqi.MQRR", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 162 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 164 */     int pos = offset;
/* 165 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 167 */     dc.writeI32(this.compCode, buffer, pos, swap);
/* 168 */     pos += 4;
/*     */     
/* 170 */     dc.writeI32(this.reason, buffer, pos, swap);
/* 171 */     pos += 4;
/*     */     
/* 173 */     if (Trace.isOn) {
/* 174 */       Trace.exit(this, "com.ibm.mq.jmqi.MQRR", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 175 */           Integer.valueOf(pos));
/*     */     }
/* 177 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 186 */     if (Trace.isOn) {
/* 187 */       Trace.entry(this, "com.ibm.mq.jmqi.MQRR", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 189 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 191 */     int pos = offset;
/* 192 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 194 */     this.compCode = dc.readI32(buffer, pos, swap);
/* 195 */     pos += 4;
/*     */     
/* 197 */     this.reason = dc.readI32(buffer, pos, swap);
/* 198 */     pos += 4;
/*     */     
/* 200 */     if (Trace.isOn) {
/* 201 */       Trace.exit(this, "com.ibm.mq.jmqi.MQRR", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 202 */           Integer.valueOf(pos));
/*     */     }
/* 204 */     return pos;
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
/*     */   public void addFieldsToFormatter(JmqiStructureFormatter fmt) {
/* 216 */     fmt.add("compCode", this.compCode);
/* 217 */     fmt.add("reason", this.reason);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\MQRR.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */