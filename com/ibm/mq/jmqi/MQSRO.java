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
/*     */ public class MQSRO
/*     */   extends AbstractMqiStructure
/*     */ {
/*     */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQSRO.java";
/*     */   private static final int SIZEOF_STRUCID = 4;
/*     */   private static final int SIZEOF_VERSION = 4;
/*     */   private static final int SIZEOF_OPTIONS = 4;
/*     */   private static final int SIZEOF_NUMPUBS = 4;
/*     */   
/*     */   static {
/*  41 */     if (Trace.isOn) {
/*  42 */       Trace.data("com.ibm.mq.jmqi.MQSRO", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQSRO.java");
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
/*  63 */   private int version = 1;
/*  64 */   private int options = 0;
/*  65 */   private int numPubs = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV1(int ptrSize) {
/*  74 */     if (Trace.isOn)
/*  75 */       Trace.entry("com.ibm.mq.jmqi.MQSRO", "getSizeV1(int)", new Object[] {
/*  76 */             Integer.valueOf(ptrSize)
/*     */           }); 
/*  78 */     int size = 16;
/*  79 */     if (Trace.isOn) {
/*  80 */       Trace.exit("com.ibm.mq.jmqi.MQSRO", "getSizeV1(int)", Integer.valueOf(size));
/*     */     }
/*  82 */     return size;
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
/*     */   private static int getSize(JmqiEnvironment env, int version, int sizeofNativePointer) throws JmqiException {
/*     */     int size;
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.entry("com.ibm.mq.jmqi.MQSRO", "getSize(JmqiEnvironment,int,int)", new Object[] { env, 
/*  98 */             Integer.valueOf(version), Integer.valueOf(sizeofNativePointer) });
/*     */     }
/*     */     
/* 101 */     if (version == 1) {
/* 102 */       size = getSizeV1(sizeofNativePointer);
/*     */     }
/*     */     else {
/*     */       
/* 106 */       JmqiException e = new JmqiException(env, -1, null, 2, 2438, null);
/*     */ 
/*     */       
/* 109 */       if (Trace.isOn) {
/* 110 */         Trace.throwing("com.ibm.mq.jmqi.MQSRO", "getSize(JmqiEnvironment,int,int)", e);
/*     */       }
/* 112 */       throw e;
/*     */     } 
/* 114 */     if (Trace.isOn) {
/* 115 */       Trace.exit("com.ibm.mq.jmqi.MQSRO", "getSize(JmqiEnvironment,int,int)", 
/* 116 */           Integer.valueOf(size));
/*     */     }
/* 118 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected MQSRO(JmqiEnvironment env) {
/* 127 */     super(env);
/* 128 */     if (Trace.isOn) {
/* 129 */       Trace.entry(this, "com.ibm.mq.jmqi.MQSRO", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/* 131 */     if (Trace.isOn) {
/* 132 */       Trace.exit(this, "com.ibm.mq.jmqi.MQSRO", "<init>(JmqiEnvironment)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion() {
/* 142 */     if (Trace.isOn) {
/* 143 */       Trace.data(this, "com.ibm.mq.jmqi.MQSRO", "getVersion()", "getter", Integer.valueOf(this.version));
/*     */     }
/* 145 */     return this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(int version) {
/* 153 */     if (Trace.isOn) {
/* 154 */       Trace.data(this, "com.ibm.mq.jmqi.MQSRO", "setVersion(int)", "setter", 
/* 155 */           Integer.valueOf(version));
/*     */     }
/* 157 */     this.version = version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOptions() {
/* 164 */     if (Trace.isOn) {
/* 165 */       Trace.data(this, "com.ibm.mq.jmqi.MQSRO", "getOptions()", "getter", Integer.valueOf(this.options));
/*     */     }
/* 167 */     return this.options;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOptions(int options) {
/* 174 */     if (Trace.isOn) {
/* 175 */       Trace.data(this, "com.ibm.mq.jmqi.MQSRO", "setOptions(int)", "setter", 
/* 176 */           Integer.valueOf(options));
/*     */     }
/* 178 */     this.options = options;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumPublications() {
/* 185 */     if (Trace.isOn) {
/* 186 */       Trace.data(this, "com.ibm.mq.jmqi.MQSRO", "getNumPublications()", "getter", 
/* 187 */           Integer.valueOf(this.numPubs));
/*     */     }
/* 189 */     return this.numPubs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNumPublications(int numPubs) {
/* 196 */     if (Trace.isOn) {
/* 197 */       Trace.data(this, "com.ibm.mq.jmqi.MQSRO", "setNumPublications(int)", "setter", 
/* 198 */           Integer.valueOf(numPubs));
/*     */     }
/* 200 */     this.numPubs = numPubs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/* 208 */     if (Trace.isOn)
/* 209 */       Trace.entry(this, "com.ibm.mq.jmqi.MQSRO", "getRequiredBufferSize(int,JmqiCodepage)", new Object[] {
/* 210 */             Integer.valueOf(ptrSize), cp
/*     */           }); 
/* 212 */     int size = getSize(this.env, this.version, ptrSize);
/*     */     
/* 214 */     if (Trace.isOn) {
/* 215 */       Trace.exit(this, "com.ibm.mq.jmqi.MQSRO", "getRequiredBufferSize(int,JmqiCodepage)", 
/* 216 */           Integer.valueOf(size));
/*     */     }
/* 218 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 227 */     if (Trace.isOn) {
/* 228 */       Trace.entry(this, "com.ibm.mq.jmqi.MQSRO", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 230 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 232 */     int pos = offset;
/* 233 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 235 */     dc.writeMQField("SRO ", buffer, pos, 4, cp, tls);
/* 236 */     pos += 4;
/*     */     
/* 238 */     dc.writeI32(this.version, buffer, pos, swap);
/* 239 */     pos += 4;
/*     */     
/* 241 */     dc.writeI32(this.options, buffer, pos, swap);
/* 242 */     pos += 4;
/*     */     
/* 244 */     dc.writeI32(this.numPubs, buffer, pos, swap);
/* 245 */     pos += 4;
/*     */     
/* 247 */     if (Trace.isOn) {
/* 248 */       Trace.exit(this, "com.ibm.mq.jmqi.MQSRO", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 249 */           Integer.valueOf(pos));
/*     */     }
/* 251 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 260 */     if (Trace.isOn) {
/* 261 */       Trace.entry(this, "com.ibm.mq.jmqi.MQSRO", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 263 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 265 */     int pos = offset;
/* 266 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 268 */     String strucId = dc.readMQField(buffer, pos, 4, cp, tls);
/*     */ 
/*     */     
/* 271 */     if (Trace.isOn) {
/* 272 */       Trace.data(this, "com.ibm.mq.jmqi.MQSRO", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", "strucId", strucId);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 277 */     if (!strucId.equals("SRO ")) {
/*     */       
/* 279 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2438, null);
/*     */ 
/*     */       
/* 282 */       if (Trace.isOn) {
/* 283 */         Trace.throwing(this, "com.ibm.mq.jmqi.MQSRO", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", traceRet1);
/*     */       }
/*     */       
/* 286 */       throw traceRet1;
/*     */     } 
/* 288 */     pos += 4;
/*     */ 
/*     */     
/* 291 */     pos += 8;
/*     */ 
/*     */     
/* 294 */     this.numPubs = dc.readI32(buffer, pos, swap);
/* 295 */     pos += 4;
/*     */     
/* 297 */     if (Trace.isOn) {
/* 298 */       Trace.exit(this, "com.ibm.mq.jmqi.MQSRO", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 299 */           Integer.valueOf(pos));
/*     */     }
/* 301 */     return pos;
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
/* 313 */     fmt.add("version", this.version);
/* 314 */     fmt.add("options", this.options);
/* 315 */     String optionDescription = MQConstants.decodeOptionsForTrace(this.options, "MQSRO_.*");
/* 316 */     fmt.add("option flags", optionDescription);
/* 317 */     fmt.add("numPubs", this.numPubs);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\MQSRO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */