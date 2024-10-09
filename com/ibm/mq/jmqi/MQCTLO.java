/*     */ package com.ibm.mq.jmqi;
/*     */ 
/*     */ import com.ibm.mq.constants.MQConstants;
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
/*     */ public class MQCTLO
/*     */   extends AbstractMqiStructure
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQCTLO.java";
/*     */   private static final int SIZEOF_STRUCID = 4;
/*     */   private static final int SIZEOF_VERSION = 4;
/*     */   private static final int SIZEOF_OPTIONS = 4;
/*     */   private static final int SIZEOF_WAIT_INTERVAL = 4;
/*     */   
/*     */   static {
/*  41 */     if (Trace.isOn) {
/*  42 */       Trace.data("com.ibm.mq.jmqi.MQCTLO", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQCTLO.java");
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
/*     */   
/*  65 */   private int version = 1;
/*  66 */   private int options = 0;
/*  67 */   private int waitInterval = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV1(int ptrSize) {
/*  77 */     int sizeOfStructureV1 = 16 + ptrSize;
/*  78 */     return sizeOfStructureV1;
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
/*  90 */     int size = 0;
/*  91 */     switch (version) {
/*     */       case 1:
/*  93 */         size = getSizeV1(ptrSize);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 101 */         return size;
/*     */     } 
/*     */     JmqiException e = new JmqiException(env, -1, null, 2, 2445, null);
/*     */     throw e;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected MQCTLO(JmqiEnvironment env) {
/* 110 */     super(env);
/* 111 */     if (Trace.isOn) {
/* 112 */       Trace.entry(this, "com.ibm.mq.jmqi.MQCTLO", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/* 114 */     if (Trace.isOn) {
/* 115 */       Trace.exit(this, "com.ibm.mq.jmqi.MQCTLO", "<init>(JmqiEnvironment)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOptions() {
/* 124 */     if (Trace.isOn) {
/* 125 */       Trace.data(this, "com.ibm.mq.jmqi.MQCTLO", "getOptions()", "getter", 
/* 126 */           Integer.valueOf(this.options));
/*     */     }
/* 128 */     return this.options;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOptions(int options) {
/* 138 */     if (Trace.isOn) {
/* 139 */       Trace.data(this, "com.ibm.mq.jmqi.MQCTLO", "setOptions(int)", "setter", 
/* 140 */           Integer.valueOf(options));
/*     */     }
/* 142 */     this.options = options;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion() {
/* 150 */     if (Trace.isOn) {
/* 151 */       Trace.data(this, "com.ibm.mq.jmqi.MQCTLO", "getVersion()", "getter", 
/* 152 */           Integer.valueOf(this.version));
/*     */     }
/* 154 */     return this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(int version) {
/* 165 */     if (Trace.isOn) {
/* 166 */       Trace.data(this, "com.ibm.mq.jmqi.MQCTLO", "setVersion(int)", "setter", 
/* 167 */           Integer.valueOf(version));
/*     */     }
/* 169 */     this.version = version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/* 178 */     int size = getSize(this.env, this.version, ptrSize);
/* 179 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int posP, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 188 */     if (Trace.isOn) {
/* 189 */       Trace.entry(this, "com.ibm.mq.jmqi.MQCTLO", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 191 */             Integer.valueOf(posP), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 193 */     int pos = posP;
/* 194 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 196 */     dc.writeMQField("CTLO", buffer, pos, 4, cp, tls);
/* 197 */     pos += 4;
/*     */     
/* 199 */     dc.writeI32(this.version, buffer, pos, swap);
/* 200 */     pos += 4;
/*     */     
/* 202 */     dc.writeI32(this.options, buffer, pos, swap);
/* 203 */     pos += 4;
/*     */     
/* 205 */     dc.writeI32(this.waitInterval, buffer, pos, swap);
/* 206 */     pos += 4;
/*     */     
/* 208 */     dc.clear(buffer, pos, ptrSize);
/* 209 */     pos += ptrSize;
/*     */     
/* 211 */     if (Trace.isOn) {
/* 212 */       Trace.exit(this, "com.ibm.mq.jmqi.MQCTLO", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 213 */           Integer.valueOf(pos));
/*     */     }
/* 215 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readFromBuffer(byte[] buffer, int posP, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 224 */     if (Trace.isOn) {
/* 225 */       Trace.entry(this, "com.ibm.mq.jmqi.MQCTLO", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 227 */             Integer.valueOf(posP), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 229 */     int pos = posP;
/* 230 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 232 */     String strucId = dc.readMQField(buffer, pos, 4, cp, tls);
/* 233 */     if (!strucId.equals("CTLO")) {
/*     */       
/* 235 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2445, null);
/*     */       
/* 237 */       if (Trace.isOn) {
/* 238 */         Trace.throwing(this, "com.ibm.mq.jmqi.MQCTLO", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", traceRet1);
/*     */       }
/*     */       
/* 241 */       throw traceRet1;
/*     */     } 
/* 243 */     pos += 4;
/*     */ 
/*     */     
/* 246 */     pos += 12 + ptrSize;
/*     */     
/* 248 */     if (Trace.isOn) {
/* 249 */       Trace.exit(this, "com.ibm.mq.jmqi.MQCTLO", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 250 */           Integer.valueOf(pos));
/*     */     }
/* 252 */     return pos;
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
/* 264 */     fmt.add("version", this.version);
/* 265 */     fmt.add("Options", this.options);
/* 266 */     String optionDescription = MQConstants.decodeOptionsForTrace(this.options, "MQCTLO_.*");
/* 267 */     fmt.add("option flags", optionDescription);
/* 268 */     fmt.add("WaitInterval", this.waitInterval);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\MQCTLO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */