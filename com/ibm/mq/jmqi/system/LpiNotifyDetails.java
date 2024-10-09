/*     */ package com.ibm.mq.jmqi.system;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.internal.AbstractMqiStructure;
/*     */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*     */ import com.ibm.mq.jmqi.internal.JmqiStructureFormatter;
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
/*     */ public class LpiNotifyDetails
/*     */   extends AbstractMqiStructure
/*     */ {
/*     */   public static final String sccsid2 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/LpiNotifyDetails.java";
/*     */   public static final int LPINOTIFYDETAILS_VERSION_1 = 1;
/*     */   public static final int LPINOTIFYDETAILS_CURRENT_VERSION = 1;
/*     */   public static final int lpiSPINotify_CANCELWAITER = 1;
/*     */   public static final int lpiSPINotify_BREAKCONN = 2;
/*     */   private static final int SIZEOF_VERSION = 4;
/*     */   private static final int SIZEOF_REASON = 4;
/*     */   private static final int SIZEOF_CONNECTIONID = 24;
/*  67 */   private int version = 1;
/*  68 */   private int reason = 2033;
/*  69 */   private byte[] connectionId = new byte[24];
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LpiNotifyDetails(JmqiEnvironment env) {
/*  75 */     super(env);
/*  76 */     if (Trace.isOn) {
/*  77 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiNotifyDetails", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/*     */     
/*  80 */     if (Trace.isOn) {
/*  81 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiNotifyDetails", "<init>(JmqiEnvironment)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getCurrentSize(int ptrSize) {
/*  91 */     if (Trace.isOn)
/*  92 */       Trace.entry("com.ibm.mq.jmqi.system.LpiNotifyDetails", "getCurrentSize(int)", new Object[] {
/*  93 */             Integer.valueOf(ptrSize)
/*     */           }); 
/*  95 */     int traceRet1 = getSizeV1(ptrSize);
/*     */     
/*  97 */     if (Trace.isOn) {
/*  98 */       Trace.exit("com.ibm.mq.jmqi.system.LpiNotifyDetails", "getCurrentSize(int)", 
/*  99 */           Integer.valueOf(traceRet1));
/*     */     }
/* 101 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV1(int ptrSize) {
/* 110 */     int size = 32;
/*     */     
/* 112 */     return size;
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
/*     */   public static int getSize(JmqiEnvironment env, int version, int ptrSize) throws JmqiException {
/* 124 */     int size = 0;
/* 125 */     switch (version) {
/*     */       case 1:
/* 127 */         size = getSizeV1(ptrSize);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 134 */         return size;
/*     */     } 
/*     */     JmqiException e = new JmqiException(env, -1, null, 2, 2195, null);
/*     */     throw e;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/* 145 */     if (Trace.isOn)
/* 146 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiNotifyDetails", "getRequiredBufferSize(int,JmqiCodepage)", new Object[] {
/* 147 */             Integer.valueOf(ptrSize), cp
/*     */           }); 
/* 149 */     int size = getSize(this.env, this.version, ptrSize);
/*     */     
/* 151 */     if (Trace.isOn) {
/* 152 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiNotifyDetails", "getRequiredBufferSize(int,JmqiCodepage)", 
/* 153 */           Integer.valueOf(size));
/*     */     }
/* 155 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion() {
/* 163 */     if (Trace.isOn) {
/* 164 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiNotifyDetails", "getVersion()", "getter", 
/* 165 */           Integer.valueOf(this.version));
/*     */     }
/* 167 */     return this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(int version) {
/* 175 */     if (Trace.isOn) {
/* 176 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiNotifyDetails", "setVersion(int)", "setter", 
/* 177 */           Integer.valueOf(version));
/*     */     }
/* 179 */     this.version = version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getReason() {
/* 186 */     if (Trace.isOn) {
/* 187 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiNotifyDetails", "getReason()", "getter", 
/* 188 */           Integer.valueOf(this.reason));
/*     */     }
/* 190 */     return this.reason;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReason(int reason) {
/* 197 */     if (Trace.isOn) {
/* 198 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiNotifyDetails", "setReason(int)", "setter", 
/* 199 */           Integer.valueOf(reason));
/*     */     }
/* 201 */     this.reason = reason;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getConnectionId() {
/* 208 */     if (Trace.isOn) {
/* 209 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiNotifyDetails", "getConnectionId()", "getter", this.connectionId);
/*     */     }
/*     */     
/* 212 */     return this.connectionId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConnectionId(byte[] connectionId) {
/* 219 */     if (Trace.isOn) {
/* 220 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiNotifyDetails", "setConnectionId(byte [ ])", "setter", connectionId);
/*     */     }
/*     */     
/* 223 */     this.connectionId = connectionId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 232 */     if (Trace.isOn) {
/* 233 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiNotifyDetails", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 235 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 237 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 238 */     int pos = offset;
/*     */     
/* 240 */     this.version = dc.readI32(buffer, pos, swap);
/* 241 */     pos += 4;
/*     */     
/* 243 */     this.reason = dc.readI32(buffer, pos, swap);
/* 244 */     pos += 4;
/*     */     
/* 246 */     System.arraycopy(buffer, pos, this.connectionId, 0, 24);
/* 247 */     pos += 24;
/*     */     
/* 249 */     if (Trace.isOn) {
/* 250 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiNotifyDetails", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 251 */           Integer.valueOf(pos));
/*     */     }
/* 253 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 262 */     if (Trace.isOn) {
/* 263 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiNotifyDetails", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 265 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 267 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 268 */     int pos = offset;
/*     */     
/* 270 */     dc.writeI32(this.version, buffer, pos, swap);
/* 271 */     pos += 4;
/*     */     
/* 273 */     dc.writeI32(this.reason, buffer, pos, swap);
/* 274 */     pos += 4;
/*     */     
/* 276 */     System.arraycopy(this.connectionId, 0, buffer, pos, 24);
/* 277 */     pos += 24;
/*     */     
/* 279 */     if (Trace.isOn) {
/* 280 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiNotifyDetails", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 281 */           Integer.valueOf(pos));
/*     */     }
/* 283 */     return pos;
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
/* 295 */     fmt.add("version", this.version);
/* 296 */     fmt.add("reason", this.reason);
/* 297 */     fmt.add("connectionId", this.connectionId);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\LpiNotifyDetails.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */