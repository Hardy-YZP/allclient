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
/*     */ public class LpiSDSubProps
/*     */   extends AbstractMqiStructure
/*     */ {
/*     */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/LpiSDSubProps.java";
/*     */   private static final int SIZEOF_DESTINATIONQMGR = 48;
/*     */   private static final int SIZEOF_DESTINATIONQNAME = 48;
/*     */   private static final int SIZEOF_SUBCORRELID = 24;
/*     */   private static final int SIZEOF_PSPROPERTYSTYLE = 4;
/*     */   private static final int SIZEOF_SUBSCOPE = 4;
/*     */   private static final int SIZEOF_SUBTYPE = 4;
/*     */   private String destinationQMgr;
/*     */   private String destinationQName;
/*     */   
/*     */   public static int getSizeV1(int ptrSize) {
/*  70 */     int size = 132;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  75 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  80 */   private byte[] subCorrelId = new byte[24];
/*  81 */   private int pSPropertyStyle = 3;
/*     */   private int subScope;
/*  83 */   private int subType = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected LpiSDSubProps(JmqiEnvironment env) {
/*  91 */     super(env);
/*  92 */     if (Trace.isOn) {
/*  93 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiSDSubProps", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/*     */     
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiSDSubProps", "<init>(JmqiEnvironment)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDestinationQMgr() {
/* 106 */     if (Trace.isOn) {
/* 107 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiSDSubProps", "getDestinationQMgr()", "getter", this.destinationQMgr);
/*     */     }
/*     */     
/* 110 */     return this.destinationQMgr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDestinationQMgr(String destinationQMgr) {
/* 117 */     if (Trace.isOn) {
/* 118 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiSDSubProps", "setDestinationQMgr(String)", "setter", destinationQMgr);
/*     */     }
/*     */     
/* 121 */     this.destinationQMgr = destinationQMgr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDestinationQName() {
/* 128 */     if (Trace.isOn) {
/* 129 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiSDSubProps", "getDestinationQName()", "getter", this.destinationQName);
/*     */     }
/*     */     
/* 132 */     return this.destinationQName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDestinationQName(String destinationQName) {
/* 139 */     if (Trace.isOn) {
/* 140 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiSDSubProps", "setDestinationQName(String)", "setter", destinationQName);
/*     */     }
/*     */     
/* 143 */     this.destinationQName = destinationQName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getSubCorrelId() {
/* 150 */     if (Trace.isOn) {
/* 151 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiSDSubProps", "getSubCorrelId()", "getter", this.subCorrelId);
/*     */     }
/*     */     
/* 154 */     return this.subCorrelId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSubCorrelId(byte[] subCorrelId) {
/* 161 */     if (Trace.isOn) {
/* 162 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiSDSubProps", "setSubCorrelId(byte [ ])", "setter", subCorrelId);
/*     */     }
/*     */     
/* 165 */     this.subCorrelId = subCorrelId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPSPropertyStyle() {
/* 172 */     if (Trace.isOn) {
/* 173 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiSDSubProps", "getPSPropertyStyle()", "getter", 
/* 174 */           Integer.valueOf(this.pSPropertyStyle));
/*     */     }
/* 176 */     return this.pSPropertyStyle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPSPropertyStyle(int propertyStyle) {
/* 183 */     if (Trace.isOn) {
/* 184 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiSDSubProps", "setPSPropertyStyle(int)", "setter", 
/* 185 */           Integer.valueOf(propertyStyle));
/*     */     }
/* 187 */     this.pSPropertyStyle = propertyStyle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSubScope() {
/* 194 */     if (Trace.isOn) {
/* 195 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiSDSubProps", "getSubScope()", "getter", 
/* 196 */           Integer.valueOf(this.subScope));
/*     */     }
/* 198 */     return this.subScope;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSubScope(int subScope) {
/* 205 */     if (Trace.isOn) {
/* 206 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiSDSubProps", "setSubScope(int)", "setter", 
/* 207 */           Integer.valueOf(subScope));
/*     */     }
/* 209 */     this.subScope = subScope;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSubType() {
/* 216 */     if (Trace.isOn) {
/* 217 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiSDSubProps", "getSubType()", "getter", 
/* 218 */           Integer.valueOf(this.subType));
/*     */     }
/* 220 */     return this.subType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSubType(int subType) {
/* 227 */     if (Trace.isOn) {
/* 228 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiSDSubProps", "setSubType(int)", "setter", 
/* 229 */           Integer.valueOf(subType));
/*     */     }
/* 231 */     this.subType = subType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/* 240 */     return getSizeV1(ptrSize);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 249 */     if (Trace.isOn) {
/* 250 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiSDSubProps", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 252 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 254 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 255 */     int pos = offset;
/*     */     
/* 257 */     dc.writeField(this.destinationQMgr, buffer, pos, 48, cp, tls);
/* 258 */     pos += 48;
/*     */     
/* 260 */     dc.writeField(this.destinationQName, buffer, pos, 48, cp, tls);
/* 261 */     pos += 48;
/*     */     
/* 263 */     System.arraycopy(this.subCorrelId, 0, buffer, pos, 24);
/* 264 */     pos += 24;
/*     */     
/* 266 */     dc.writeI32(this.pSPropertyStyle, buffer, pos, swap);
/* 267 */     pos += 4;
/*     */     
/* 269 */     dc.writeI32(this.subScope, buffer, pos, swap);
/* 270 */     pos += 4;
/*     */     
/* 272 */     dc.writeI32(this.subType, buffer, pos, swap);
/* 273 */     pos += 4;
/*     */ 
/*     */     
/* 276 */     if (Trace.isOn) {
/* 277 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiSDSubProps", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 278 */           Integer.valueOf(pos));
/*     */     }
/* 280 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 289 */     if (Trace.isOn) {
/* 290 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiSDSubProps", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 292 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 294 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 295 */     int pos = offset;
/*     */     
/* 297 */     this.destinationQMgr = dc.readMQField(buffer, pos, 48, cp, tls);
/* 298 */     pos += 48;
/*     */     
/* 300 */     this.destinationQName = dc.readMQField(buffer, pos, 48, cp, tls);
/* 301 */     pos += 48;
/*     */     
/* 303 */     System.arraycopy(buffer, pos, this.subCorrelId, 0, 24);
/* 304 */     pos += 24;
/*     */     
/* 306 */     this.pSPropertyStyle = dc.readI32(buffer, pos, swap);
/* 307 */     pos += 4;
/*     */     
/* 309 */     this.subScope = dc.readI32(buffer, pos, swap);
/* 310 */     pos += 4;
/*     */     
/* 312 */     this.subType = dc.readI32(buffer, pos, swap);
/* 313 */     pos += 4;
/*     */ 
/*     */ 
/*     */     
/* 317 */     if (Trace.isOn) {
/* 318 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiSDSubProps", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 319 */           Integer.valueOf(pos));
/*     */     }
/* 321 */     return pos;
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
/* 333 */     fmt.add("destinationQMgr", this.destinationQMgr);
/* 334 */     fmt.add("destinationQName", this.destinationQName);
/* 335 */     fmt.add("subCorrelId", this.subCorrelId);
/* 336 */     fmt.add("pSPropertyStyle", this.pSPropertyStyle);
/* 337 */     fmt.add("subScope", this.subScope);
/* 338 */     fmt.add("subType", this.subType);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\LpiSDSubProps.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */