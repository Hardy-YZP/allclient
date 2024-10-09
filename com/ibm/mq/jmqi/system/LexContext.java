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
/*     */ public class LexContext
/*     */   extends AbstractMqiStructure
/*     */ {
/*     */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/LexContext.java";
/*     */   private static final int SIZEOF_USERID = 12;
/*     */   private static final int SIZEOF_SECURITY_ID = 40;
/*     */   private static final int SIZEOF_EFFECTIVE_USER_ID = 12;
/*     */   private String userId;
/*  56 */   private byte[] securityId = new byte[40];
/*     */ 
/*     */ 
/*     */   
/*     */   private String effectiveUserId;
/*     */ 
/*     */ 
/*     */   
/*     */   public LexContext(JmqiEnvironment env) {
/*  65 */     super(env);
/*  66 */     if (Trace.isOn) {
/*  67 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LexContext", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/*     */     
/*  70 */     if (Trace.isOn) {
/*  71 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LexContext", "<init>(JmqiEnvironment)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getFieldSizeV1(int ptrSize) {
/*  81 */     if (Trace.isOn)
/*  82 */       Trace.entry("com.ibm.mq.jmqi.system.LexContext", "getFieldSizeV1(int)", new Object[] {
/*  83 */             Integer.valueOf(ptrSize)
/*     */           }); 
/*  85 */     int size = 0;
/*  86 */     size += 12;
/*  87 */     size += 40;
/*  88 */     size += 12;
/*     */     
/*  90 */     if (Trace.isOn) {
/*  91 */       Trace.exit("com.ibm.mq.jmqi.system.LexContext", "getFieldSizeV1(int)", Integer.valueOf(size));
/*     */     }
/*  93 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV1(int ptrSize) {
/* 101 */     if (Trace.isOn)
/* 102 */       Trace.entry("com.ibm.mq.jmqi.system.LexContext", "getSizeV1(int)", new Object[] {
/* 103 */             Integer.valueOf(ptrSize)
/*     */           }); 
/* 105 */     int size = getFieldSizeV1(ptrSize);
/*     */     
/* 107 */     if (Trace.isOn) {
/* 108 */       Trace.exit("com.ibm.mq.jmqi.system.LexContext", "getSizeV1(int)", Integer.valueOf(size));
/*     */     }
/* 110 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSize(JmqiEnvironment env, int version, int ptrSize) throws JmqiException {
/* 121 */     if (Trace.isOn) {
/* 122 */       Trace.entry("com.ibm.mq.jmqi.system.LexContext", "getSize(JmqiEnvironment,int,int)", new Object[] { env, 
/* 123 */             Integer.valueOf(version), Integer.valueOf(ptrSize) });
/*     */     }
/* 125 */     int traceRet1 = getSizeV1(ptrSize);
/*     */     
/* 127 */     if (Trace.isOn) {
/* 128 */       Trace.exit("com.ibm.mq.jmqi.system.LexContext", "getSize(JmqiEnvironment,int,int)", 
/* 129 */           Integer.valueOf(traceRet1));
/*     */     }
/* 131 */     return traceRet1;
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
/* 142 */     if (Trace.isOn)
/* 143 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LexContext", "getRequiredBufferSize(int,JmqiCodepage)", new Object[] {
/* 144 */             Integer.valueOf(ptrSize), cp
/*     */           }); 
/* 146 */     int version = 1;
/* 147 */     int size = getSize(this.env, version, ptrSize);
/*     */     
/* 149 */     if (Trace.isOn) {
/* 150 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LexContext", "getRequiredBufferSize(int,JmqiCodepage)", 
/* 151 */           Integer.valueOf(size));
/*     */     }
/* 153 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUserId() {
/* 160 */     if (Trace.isOn) {
/* 161 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexContext", "getUserId()", "getter", this.userId);
/*     */     }
/* 163 */     return this.userId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUserId(String userId) {
/* 170 */     if (Trace.isOn) {
/* 171 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexContext", "setUserId(String)", "setter", userId);
/*     */     }
/* 173 */     this.userId = userId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getSecurityId() {
/* 180 */     if (Trace.isOn) {
/* 181 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexContext", "getSecurityId()", "getter", this.securityId);
/*     */     }
/*     */     
/* 184 */     return this.securityId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSecurityId(byte[] securityId) {
/* 191 */     if (Trace.isOn) {
/* 192 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexContext", "setSecurityId(byte [ ])", "setter", securityId);
/*     */     }
/*     */     
/* 195 */     this.securityId = securityId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEffectiveUserId() {
/* 202 */     if (Trace.isOn) {
/* 203 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexContext", "getEffectiveUserId()", "getter", this.effectiveUserId);
/*     */     }
/*     */     
/* 206 */     return this.effectiveUserId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEffectiveUserId(String effectiveUserId) {
/* 213 */     if (Trace.isOn) {
/* 214 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexContext", "setEffectiveUserId(String)", "setter", effectiveUserId);
/*     */     }
/*     */     
/* 217 */     this.effectiveUserId = effectiveUserId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 226 */     if (Trace.isOn) {
/* 227 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LexContext", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 229 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 231 */     int pos = offset;
/* 232 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 234 */     dc.writeField(this.userId, buffer, pos, 12, cp, tls);
/* 235 */     pos += 12;
/*     */     
/* 237 */     System.arraycopy(this.securityId, 0, buffer, pos, 40);
/* 238 */     pos += 40;
/*     */     
/* 240 */     dc.writeField(this.effectiveUserId, buffer, pos, 12, cp, tls);
/* 241 */     pos += 12;
/* 242 */     int traceRet1 = pos - offset;
/*     */ 
/*     */ 
/*     */     
/* 246 */     if (Trace.isOn) {
/* 247 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LexContext", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 248 */           Integer.valueOf(traceRet1));
/*     */     }
/* 250 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 259 */     if (Trace.isOn) {
/* 260 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LexContext", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 262 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 264 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 265 */     int pos = offset;
/*     */     
/* 267 */     this.userId = dc.readField(buffer, pos, 12, cp, tls);
/* 268 */     pos += 12;
/*     */     
/* 270 */     System.arraycopy(buffer, pos, this.securityId, 0, 40);
/* 271 */     pos += 40;
/*     */     
/* 273 */     this.effectiveUserId = dc.readField(buffer, pos, 12, cp, tls);
/* 274 */     pos += 12;
/* 275 */     int traceRet1 = pos - offset;
/*     */ 
/*     */ 
/*     */     
/* 279 */     if (Trace.isOn) {
/* 280 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LexContext", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 281 */           Integer.valueOf(traceRet1));
/*     */     }
/* 283 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addFieldsToFormatter(JmqiStructureFormatter fmt) {
/* 294 */     fmt.add("userId", this.userId);
/* 295 */     fmt.add("securityId", this.securityId);
/* 296 */     fmt.add("effectiveUserId", this.effectiveUserId);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\LexContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */