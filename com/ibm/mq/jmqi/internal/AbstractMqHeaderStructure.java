/*     */ package com.ibm.mq.jmqi.internal;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.MQHeader;
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractMqHeaderStructure
/*     */   extends AbstractMqiStructure
/*     */ {
/*     */   public static final String sccsid2 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/internal/AbstractMqHeaderStructure.java";
/*     */   private MQHeader mqHeader;
/*     */   
/*     */   protected AbstractMqHeaderStructure(JmqiEnvironment env) {
/*  51 */     super(env);
/*  52 */     if (Trace.isOn) {
/*  53 */       Trace.entry(this, "com.ibm.mq.jmqi.internal.AbstractMqHeaderStructure", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/*     */     
/*  56 */     this.mqHeader = env.newMQHeader();
/*     */     
/*  58 */     if (Trace.isOn) {
/*  59 */       Trace.exit(this, "com.ibm.mq.jmqi.internal.AbstractMqHeaderStructure", "<init>(JmqiEnvironment)");
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
/*     */   protected AbstractMqHeaderStructure(JmqiEnvironment env, MQHeader mqHeader) {
/*  71 */     super(env);
/*  72 */     if (Trace.isOn) {
/*  73 */       Trace.entry(this, "com.ibm.mq.jmqi.internal.AbstractMqHeaderStructure", "<init>(JmqiEnvironment,MQHeader)", new Object[] { env, mqHeader });
/*     */     }
/*     */     
/*  76 */     this.mqHeader = mqHeader;
/*     */     
/*  78 */     if (Trace.isOn) {
/*  79 */       Trace.exit(this, "com.ibm.mq.jmqi.internal.AbstractMqHeaderStructure", "<init>(JmqiEnvironment,MQHeader)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQHeader getMqHeader() {
/*  89 */     if (Trace.isOn) {
/*  90 */       Trace.data(this, "com.ibm.mq.jmqi.internal.AbstractMqHeaderStructure", "getMqHeader()", "getter", this.mqHeader);
/*     */     }
/*     */     
/*  93 */     return this.mqHeader;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMqHeader(MQHeader mqHeader) {
/* 100 */     if (Trace.isOn) {
/* 101 */       Trace.data(this, "com.ibm.mq.jmqi.internal.AbstractMqHeaderStructure", "setMqHeader(MQHeader)", "setter", mqHeader);
/*     */     }
/*     */     
/* 104 */     this.mqHeader = mqHeader;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion() {
/* 112 */     int traceRet1 = this.mqHeader.getVersion();
/* 113 */     if (Trace.isOn) {
/* 114 */       Trace.data(this, "com.ibm.mq.jmqi.internal.AbstractMqHeaderStructure", "getVersion()", "getter", 
/* 115 */           Integer.valueOf(traceRet1));
/*     */     }
/* 117 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(int version) {
/* 125 */     if (Trace.isOn) {
/* 126 */       Trace.data(this, "com.ibm.mq.jmqi.internal.AbstractMqHeaderStructure", "setVersion(int)", "setter", 
/* 127 */           Integer.valueOf(version));
/*     */     }
/* 129 */     this.mqHeader.setVersion(version);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStrucLength() {
/* 136 */     int traceRet1 = this.mqHeader.getStrucLength();
/* 137 */     if (Trace.isOn) {
/* 138 */       Trace.data(this, "com.ibm.mq.jmqi.internal.AbstractMqHeaderStructure", "getStrucLength()", "getter", 
/* 139 */           Integer.valueOf(traceRet1));
/*     */     }
/* 141 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStrucLength(int strucLength) {
/* 148 */     if (Trace.isOn) {
/* 149 */       Trace.data(this, "com.ibm.mq.jmqi.internal.AbstractMqHeaderStructure", "setStrucLength(int)", "setter", 
/* 150 */           Integer.valueOf(strucLength));
/*     */     }
/* 152 */     this.mqHeader.setStrucLength(strucLength);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEncoding() {
/* 159 */     int traceRet1 = this.mqHeader.getEncoding();
/* 160 */     if (Trace.isOn) {
/* 161 */       Trace.data(this, "com.ibm.mq.jmqi.internal.AbstractMqHeaderStructure", "getEncoding()", "getter", 
/* 162 */           Integer.valueOf(traceRet1));
/*     */     }
/* 164 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEncoding(int encoding) {
/* 171 */     if (Trace.isOn) {
/* 172 */       Trace.data(this, "com.ibm.mq.jmqi.internal.AbstractMqHeaderStructure", "setEncoding(int)", "setter", 
/* 173 */           Integer.valueOf(encoding));
/*     */     }
/* 175 */     this.mqHeader.setEncoding(encoding);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCodedCharSetId() {
/* 182 */     int traceRet1 = this.mqHeader.getCodedCharSetId();
/* 183 */     if (Trace.isOn) {
/* 184 */       Trace.data(this, "com.ibm.mq.jmqi.internal.AbstractMqHeaderStructure", "getCodedCharSetId()", "getter", 
/* 185 */           Integer.valueOf(traceRet1));
/*     */     }
/* 187 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCodedCharSetId(int codedCharSetId) {
/* 194 */     if (Trace.isOn) {
/* 195 */       Trace.data(this, "com.ibm.mq.jmqi.internal.AbstractMqHeaderStructure", "setCodedCharSetId(int)", "setter", 
/* 196 */           Integer.valueOf(codedCharSetId));
/*     */     }
/* 198 */     this.mqHeader.setCodedCharSetId(codedCharSetId);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFormat() {
/* 205 */     String traceRet1 = this.mqHeader.getFormat();
/* 206 */     if (Trace.isOn) {
/* 207 */       Trace.data(this, "com.ibm.mq.jmqi.internal.AbstractMqHeaderStructure", "getFormat()", "getter", traceRet1);
/*     */     }
/*     */     
/* 210 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFormat(String format) {
/* 217 */     if (Trace.isOn) {
/* 218 */       Trace.data(this, "com.ibm.mq.jmqi.internal.AbstractMqHeaderStructure", "setFormat(String)", "setter", format);
/*     */     }
/*     */     
/* 221 */     this.mqHeader.setFormat(format);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFlags() {
/* 229 */     int traceRet1 = this.mqHeader.getFlags();
/* 230 */     if (Trace.isOn) {
/* 231 */       Trace.data(this, "com.ibm.mq.jmqi.internal.AbstractMqHeaderStructure", "getFlags()", "getter", 
/* 232 */           Integer.valueOf(traceRet1));
/*     */     }
/* 234 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFlags(int flags) {
/* 241 */     if (Trace.isOn) {
/* 242 */       Trace.data(this, "com.ibm.mq.jmqi.internal.AbstractMqHeaderStructure", "setFlags(int)", "setter", 
/* 243 */           Integer.valueOf(flags));
/*     */     }
/* 245 */     this.mqHeader.setFlags(flags);
/*     */   }
/*     */   
/*     */   public abstract int readBodyFromBuffer(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, boolean paramBoolean, JmqiCodepage paramJmqiCodepage, JmqiTls paramJmqiTls) throws JmqiException;
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\internal\AbstractMqHeaderStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */