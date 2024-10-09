/*     */ package com.ibm.mq.jmqi.internal;
/*     */ 
/*     */ import com.ibm.mq.jmqi.ConstantDecoder;
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.JmqiMQ;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.mq.jmqi.JmqiUtils;
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.mq.jmqi.system.JmqiComponentTls;
/*     */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*     */ import com.ibm.mq.jmqi.system.JmqiTls;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractMqiStructure
/*     */   extends JmqiObject
/*     */   implements MqiStructure
/*     */ {
/*     */   static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/internal/AbstractMqiStructure.java";
/*     */   
/*     */   public AbstractMqiStructure(JmqiEnvironment env) {
/*  54 */     super(env);
/*  55 */     if (Trace.isOn) {
/*  56 */       Trace.entry(this, "com.ibm.mq.jmqi.internal.AbstractMqiStructure", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/*     */     
/*  59 */     if (Trace.isOn) {
/*  60 */       Trace.exit(this, "com.ibm.mq.jmqi.internal.AbstractMqiStructure", "<init>(JmqiEnvironment)");
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
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int pos, int ptrSize, boolean swap, JmqiCodepage cp, JmqiMQ mq) throws JmqiException {
/*  83 */     JmqiSystemEnvironment sysenv = (JmqiSystemEnvironment)this.env;
/*  84 */     int compId = mq.getTlsComponentId();
/*  85 */     JmqiComponentTls tls = sysenv.getComponentTls(compId);
/*  86 */     JmqiTls jTls = sysenv.getJmqiTls(tls);
/*     */     
/*  88 */     return writeToBuffer(buffer, pos, ptrSize, swap, cp, jTls);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToTraceBuffer(byte[] buffer, int pos, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 111 */     return writeToBuffer(buffer, pos, ptrSize, swap, cp, tls);
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
/*     */   public int readFromBuffer(byte[] buffer, int pos, int ptrSize, boolean swap, JmqiCodepage cp, JmqiMQ mq) throws JmqiException {
/* 129 */     JmqiSystemEnvironment sysenv = (JmqiSystemEnvironment)this.env;
/* 130 */     int compId = mq.getTlsComponentId();
/* 131 */     JmqiComponentTls tls = sysenv.getComponentTls(compId);
/* 132 */     JmqiTls jTls = sysenv.getJmqiTls(tls);
/*     */     
/* 134 */     return readFromBuffer(buffer, pos, ptrSize, swap, cp, jTls);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 143 */     return toString(0, 0, " ");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toStringMultiLine() {
/* 152 */     return toString(15, 2, JmqiUtils.NL);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public String toTraceString() {
/* 163 */     return toStringMultiLine();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String toString(int width1, int width2, String sep) {
/* 174 */     JmqiStructureFormatter fmt = new JmqiStructureFormatter(this.env, width1, width2, sep);
/* 175 */     addFieldsToFormatter(fmt);
/* 176 */     return fmt.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(int version) {
/* 184 */     if (Trace.isOn) {
/* 185 */       Trace.data(this, "com.ibm.mq.jmqi.internal.AbstractMqiStructure", "setVersion(int)", "setter", 
/* 186 */           Integer.valueOf(version));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion() {
/* 196 */     if (Trace.isOn) {
/* 197 */       Trace.data(this, "com.ibm.mq.jmqi.internal.AbstractMqiStructure", "getVersion()", "getter", 
/* 198 */           Integer.valueOf(0));
/*     */     }
/* 200 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFirstVersion() {
/* 208 */     if (Trace.isOn) {
/* 209 */       Trace.data(this, "com.ibm.mq.jmqi.internal.AbstractMqiStructure", "getFirstVersion()", "getter", 
/* 210 */           Integer.valueOf(1));
/*     */     }
/* 212 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCurrentVersion() {
/* 220 */     if (Trace.isOn) {
/* 221 */       Trace.data(this, "com.ibm.mq.jmqi.internal.AbstractMqiStructure", "getCurrentVersion()", "getter", 
/* 222 */           Integer.valueOf(1));
/*     */     }
/* 224 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSize(int ptrSize) throws JmqiException {
/* 234 */     if (Trace.isOn)
/* 235 */       Trace.entry(this, "com.ibm.mq.jmqi.internal.AbstractMqiStructure", "getSize(int)", new Object[] {
/* 236 */             Integer.valueOf(ptrSize)
/*     */           }); 
/* 238 */     if (Trace.isOn) {
/* 239 */       Trace.exit(this, "com.ibm.mq.jmqi.internal.AbstractMqiStructure", "getSize(int)", 
/* 240 */           Integer.valueOf(0));
/*     */     }
/* 242 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setupForTest(int ptrSize) {
/* 250 */     if (Trace.isOn) {
/* 251 */       Trace.data(this, "com.ibm.mq.jmqi.internal.AbstractMqiStructure", "setupForTest(int)", "setter", 
/* 252 */           Integer.valueOf(ptrSize));
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
/*     */   public int getMaximumRequiredBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/* 265 */     if (Trace.isOn) {
/* 266 */       Trace.entry(this, "com.ibm.mq.jmqi.internal.AbstractMqiStructure", "getMaximumRequiredBufferSize(int,JmqiCodepage)", new Object[] {
/* 267 */             Integer.valueOf(ptrSize), cp
/*     */           });
/*     */     }
/* 270 */     int traceRet1 = getRequiredBufferSize(ptrSize, cp);
/*     */     
/* 272 */     if (Trace.isOn) {
/* 273 */       Trace.exit(this, "com.ibm.mq.jmqi.internal.AbstractMqiStructure", "getMaximumRequiredBufferSize(int,JmqiCodepage)", 
/* 274 */           Integer.valueOf(traceRet1));
/*     */     }
/* 276 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredInputBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/* 287 */     if (Trace.isOn) {
/* 288 */       Trace.entry(this, "com.ibm.mq.jmqi.internal.AbstractMqiStructure", "getRequiredInputBufferSize(int,JmqiCodepage)", new Object[] {
/* 289 */             Integer.valueOf(ptrSize), cp
/*     */           });
/*     */     }
/* 292 */     int traceRet1 = getRequiredBufferSize(ptrSize, cp);
/*     */     
/* 294 */     if (Trace.isOn) {
/* 295 */       Trace.exit(this, "com.ibm.mq.jmqi.internal.AbstractMqiStructure", "getRequiredInputBufferSize(int,JmqiCodepage)", 
/* 296 */           Integer.valueOf(traceRet1));
/*     */     }
/* 298 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String formatOptions(int options, Field[] fields, String prefix) {
/* 303 */     return ConstantDecoder.formatOptions(options, fields, prefix);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\internal\AbstractMqiStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */