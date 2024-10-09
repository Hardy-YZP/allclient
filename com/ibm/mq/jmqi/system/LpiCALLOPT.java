/*     */ package com.ibm.mq.jmqi.system;
/*     */ 
/*     */ import com.ibm.mq.constants.CMQC;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LpiCALLOPT
/*     */   extends AbstractMqiStructure
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/LpiCALLOPT.java";
/*     */   public static final int lpiSPICALL_NONE = 0;
/*     */   public static final int lpiSPICALL_ALTERNATE_USER = 1;
/*     */   public static final int lpiSPICALL_FAIL_IF_QUIESCING = 2;
/*     */   private static final int SIZEOF_LENGTH = 4;
/*     */   private static final int SIZEOF_COMMAND_INFO = 4;
/*     */   private static final int SIZEOF_ALTERNATE_USER_ID = 20;
/*     */   private static final int SIZEOF_ALTERNATE_SECURITY_ID = 40;
/*  91 */   private int length = 0;
/*  92 */   private int commandInfo = 0;
/*  93 */   private String alternateUserId = "";
/*  94 */   private byte[] alternateSecurityId = CMQC.MQSID_NONE;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected LpiCALLOPT(JmqiEnvironment env) {
/* 102 */     super(env);
/* 103 */     if (Trace.isOn) {
/* 104 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiCALLOPT", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/*     */     
/* 107 */     if (Trace.isOn) {
/* 108 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiCALLOPT", "<init>(JmqiEnvironment)");
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
/*     */   public int getSize(int ptrSize) throws JmqiException {
/* 121 */     if (Trace.isOn)
/* 122 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiCALLOPT", "getSize(int)", new Object[] {
/* 123 */             Integer.valueOf(ptrSize)
/*     */           }); 
/* 125 */     int traceRet1 = getSize(this.env, ptrSize);
/*     */     
/* 127 */     if (Trace.isOn) {
/* 128 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiCALLOPT", "getSize(int)", 
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
/*     */   public static int getSize(JmqiEnvironment env, int ptrSize) throws JmqiException {
/* 142 */     if (Trace.isOn) {
/* 143 */       Trace.entry("com.ibm.mq.jmqi.system.LpiCALLOPT", "getSize(JmqiEnvironment,int)", new Object[] { env, 
/* 144 */             Integer.valueOf(ptrSize) });
/*     */     }
/* 146 */     int size = 0;
/* 147 */     size += 4;
/* 148 */     size += 4;
/* 149 */     size += 20;
/* 150 */     size += 40;
/*     */     
/* 152 */     if (Trace.isOn) {
/* 153 */       Trace.exit("com.ibm.mq.jmqi.system.LpiCALLOPT", "getSize(JmqiEnvironment,int)", 
/* 154 */           Integer.valueOf(size));
/*     */     }
/* 156 */     return size;
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
/* 167 */     if (Trace.isOn)
/* 168 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiCALLOPT", "getRequiredBufferSize(int,JmqiCodepage)", new Object[] {
/* 169 */             Integer.valueOf(ptrSize), cp
/*     */           }); 
/* 171 */     int traceRet1 = getSize(this.env, ptrSize);
/*     */     
/* 173 */     if (Trace.isOn) {
/* 174 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiCALLOPT", "getRequiredBufferSize(int,JmqiCodepage)", 
/* 175 */           Integer.valueOf(traceRet1));
/*     */     }
/* 177 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLength() {
/* 184 */     if (Trace.isOn) {
/* 185 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCALLOPT", "getLength()", "getter", 
/* 186 */           Integer.valueOf(this.length));
/*     */     }
/* 188 */     return this.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLength(int length) {
/* 195 */     if (Trace.isOn) {
/* 196 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCALLOPT", "setLength(int)", "setter", 
/* 197 */           Integer.valueOf(length));
/*     */     }
/* 199 */     this.length = length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCommandInfo() {
/* 206 */     if (Trace.isOn) {
/* 207 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCALLOPT", "getCommandInfo()", "getter", 
/* 208 */           Integer.valueOf(this.commandInfo));
/*     */     }
/* 210 */     return this.commandInfo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCommandInfo(int commandInfo) {
/* 217 */     if (Trace.isOn) {
/* 218 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCALLOPT", "setCommandInfo(int)", "setter", 
/* 219 */           Integer.valueOf(commandInfo));
/*     */     }
/* 221 */     this.commandInfo = commandInfo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAlternateUserId() {
/* 228 */     if (Trace.isOn) {
/* 229 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCALLOPT", "getAlternateUserId()", "getter", this.alternateUserId);
/*     */     }
/*     */     
/* 232 */     return this.alternateUserId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAlternateUserId(String alternateUserId) {
/* 239 */     if (Trace.isOn) {
/* 240 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCALLOPT", "setAlternateUserId(String)", "setter", alternateUserId);
/*     */     }
/*     */     
/* 243 */     this.alternateUserId = alternateUserId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getAlternateSecurityId() {
/* 250 */     if (Trace.isOn) {
/* 251 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCALLOPT", "getAlternateSecurityId()", "getter", this.alternateSecurityId);
/*     */     }
/*     */     
/* 254 */     return this.alternateSecurityId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAlternateSecurityId(byte[] alternateSecurityId) {
/* 261 */     if (Trace.isOn) {
/* 262 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCALLOPT", "setAlternateSecurityId(byte [ ])", "setter", alternateSecurityId);
/*     */     }
/*     */     
/* 265 */     this.alternateSecurityId = alternateSecurityId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 273 */     if (Trace.isOn) {
/* 274 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiCALLOPT", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 276 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 278 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 279 */     int pos = offset;
/*     */     
/* 281 */     dc.writeI32(this.length, buffer, pos, swap);
/* 282 */     pos += 4;
/*     */     
/* 284 */     dc.writeI32(this.commandInfo, buffer, pos, swap);
/* 285 */     pos += 4;
/*     */     
/* 287 */     dc.writeField(this.alternateUserId, buffer, pos, 20, cp, tls);
/* 288 */     pos += 20;
/*     */     
/* 290 */     System.arraycopy(this.alternateSecurityId, 0, buffer, pos, 40);
/* 291 */     pos += 40;
/*     */     
/* 293 */     if (Trace.isOn) {
/* 294 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiCALLOPT", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 295 */           Integer.valueOf(pos));
/*     */     }
/* 297 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 305 */     if (Trace.isOn) {
/* 306 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiCALLOPT", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 308 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 310 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 311 */     int pos = offset;
/*     */     
/* 313 */     this.length = dc.readI32(buffer, pos, swap);
/* 314 */     pos += 4;
/*     */     
/* 316 */     this.commandInfo = dc.readI32(buffer, pos, swap);
/* 317 */     pos += 4;
/*     */     
/* 319 */     this.alternateUserId = dc.readField(buffer, pos, 20, cp, tls);
/* 320 */     pos += 20;
/*     */     
/* 322 */     System.arraycopy(buffer, pos, this.alternateSecurityId, 0, 40);
/* 323 */     pos += 40;
/*     */     
/* 325 */     if (Trace.isOn) {
/* 326 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiCALLOPT", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 327 */           Integer.valueOf(pos));
/*     */     }
/* 329 */     return pos;
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
/* 341 */     fmt.add("length", this.length);
/* 342 */     fmt.add("commandInfo", this.commandInfo);
/* 343 */     fmt.add("alternateUserId", this.alternateUserId);
/* 344 */     fmt.add("alternateSecurityId", this.alternateSecurityId);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\LpiCALLOPT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */