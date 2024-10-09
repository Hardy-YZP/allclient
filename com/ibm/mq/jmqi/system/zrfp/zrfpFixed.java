/*     */ package com.ibm.mq.jmqi.system.zrfp;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.mq.jmqi.internal.JmqiDC;
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
/*     */ class zrfpFixed
/*     */   extends JmqiObject
/*     */ {
/*     */   static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/zrfp/zrfpFixed.java";
/*     */   private static final int SIZEOF_STRUCID = 4;
/*     */   private static final int SIZEOF_VERSION = 1;
/*     */   private static final int SIZEOF_FLAGS = 3;
/*     */   private static final int SIZEOF_STRUCLENGTH = 4;
/*     */   private static final int SIZEOF_PROPSLENGTH = 4;
/*     */   private static final int SIZEOF_CCSID = 4;
/*     */   private static final int SIZEOF_CCSIDTYPE = 1;
/*     */   private static final int SIZEOF_COMPATIBLES = 1;
/*     */   private static final int SIZEOF_SPARE = 2;
/*     */   
/*     */   static {
/*  60 */     if (Trace.isOn) {
/*  61 */       Trace.data("com.ibm.mq.jmqi.system.zrfp.zrfpFixed", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/zrfp/zrfpFixed.java");
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
/*  83 */   private byte[] cachedBytes = null;
/*     */   
/*     */   private long cachedStrucLength;
/*     */   
/*     */   private long cachedPropsLength;
/*     */   
/*     */   private int cachedCCSID;
/*     */   private int cachedCompatibles;
/*     */   private boolean cachedWriteSwap;
/*     */   private int flags;
/*     */   private long strucLength;
/*     */   private long propsLength;
/*  95 */   private int ccsid = 1208;
/*  96 */   private int ccsidType = 3;
/*     */ 
/*     */ 
/*     */   
/*     */   private int compatibles;
/*     */ 
/*     */   
/*     */   private final JmqiDC dc;
/*     */ 
/*     */ 
/*     */   
/*     */   zrfpFixed(JmqiEnvironment env, JmqiDC dc) {
/* 108 */     super(env);
/* 109 */     this.dc = dc;
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
/*     */   int write(byte[] buffer, int offset, boolean writeSwap) throws JmqiException {
/* 123 */     if (Trace.isOn) {
/* 124 */       Trace.entry(this, "com.ibm.mq.jmqi.system.zrfp.zrfpFixed", "write(byte [ ],int,boolean)", new Object[] { buffer, 
/* 125 */             Integer.valueOf(offset), Boolean.valueOf(writeSwap) });
/*     */     }
/*     */     
/* 128 */     if (this.cachedBytes != null && this.cachedWriteSwap == writeSwap)
/*     */     {
/* 130 */       if (this.cachedStrucLength == this.strucLength && this.cachedPropsLength == this.propsLength && this.cachedCCSID == this.ccsid && this.cachedCompatibles == this.compatibles) {
/*     */ 
/*     */         
/* 133 */         System.arraycopy(this.cachedBytes, 0, buffer, offset, 24);
/*     */ 
/*     */         
/* 136 */         if (Trace.isOn) {
/* 137 */           Trace.exit(this, "com.ibm.mq.jmqi.system.zrfp.zrfpFixed", "write(byte [ ],int,boolean)", 
/* 138 */               Integer.valueOf(24), 1);
/*     */         }
/* 140 */         return 24;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 145 */     this.cachedStrucLength = this.strucLength;
/* 146 */     this.cachedPropsLength = this.propsLength;
/* 147 */     this.cachedCCSID = this.ccsid;
/* 148 */     this.cachedCompatibles = this.compatibles;
/* 149 */     this.cachedWriteSwap = writeSwap;
/*     */ 
/*     */     
/* 152 */     this.cachedBytes = new byte[24];
/*     */     
/* 154 */     int pos = 0;
/*     */ 
/*     */     
/* 157 */     Utils.writeU32(1515341392L, this.cachedBytes, pos, false);
/* 158 */     pos += 4;
/*     */ 
/*     */     
/* 161 */     int u8 = 1;
/* 162 */     int u24 = writeSwap ? 1 : 0;
/* 163 */     Utils.writeU8U24(u8, u24, this.cachedBytes, pos, false);
/* 164 */     pos += 4;
/*     */ 
/*     */     
/* 167 */     Utils.writeU32(this.strucLength, this.cachedBytes, pos, writeSwap);
/* 168 */     pos += 4;
/*     */ 
/*     */     
/* 171 */     Utils.writeU32(this.propsLength, this.cachedBytes, pos, writeSwap);
/* 172 */     pos += 4;
/*     */ 
/*     */     
/* 175 */     this.dc.writeI32(this.ccsid, this.cachedBytes, pos, writeSwap);
/* 176 */     pos += 4;
/*     */ 
/*     */     
/* 179 */     Utils.writeU8(this.ccsidType, this.cachedBytes, pos, writeSwap);
/* 180 */     pos++;
/*     */ 
/*     */     
/* 183 */     Utils.writeU8(this.compatibles, this.cachedBytes, pos, writeSwap);
/* 184 */     pos++;
/*     */ 
/*     */     
/* 187 */     this.cachedBytes[pos++] = 0;
/* 188 */     this.cachedBytes[pos++] = 0;
/*     */ 
/*     */     
/* 191 */     System.arraycopy(this.cachedBytes, 0, buffer, offset, 24);
/*     */     
/* 193 */     if (Trace.isOn) {
/* 194 */       Trace.exit(this, "com.ibm.mq.jmqi.system.zrfp.zrfpFixed", "write(byte [ ],int,boolean)", 
/* 195 */           Integer.valueOf(pos), 2);
/*     */     }
/* 197 */     return pos;
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
/*     */   int read(byte[] buffer, int offset) throws JmqiException {
/* 209 */     if (Trace.isOn) {
/* 210 */       Trace.entry(this, "com.ibm.mq.jmqi.system.zrfp.zrfpFixed", "read(byte [ ],int)", new Object[] { buffer, 
/* 211 */             Integer.valueOf(offset) });
/*     */     }
/* 213 */     int pos = offset;
/*     */     
/* 215 */     long strucID = Utils.readU32(buffer, pos, false);
/* 216 */     pos += 4;
/* 217 */     if (strucID != 1515341392L) {
/* 218 */       JmqiException jmqiEx = new JmqiException(this.env, -1, null, 2, 6107, null);
/*     */ 
/*     */ 
/*     */       
/* 222 */       if (Trace.isOn) {
/* 223 */         Trace.throwing(this, "com.ibm.mq.jmqi.system.zrfp.zrfpFixed", "read(byte [ ],int)", (Throwable)jmqiEx, 1);
/*     */       }
/*     */       
/* 226 */       throw jmqiEx;
/*     */     } 
/*     */ 
/*     */     
/* 230 */     int[] tmp = Utils.readU8U24(buffer, pos, false);
/* 231 */     int version = tmp[0];
/*     */     
/* 233 */     if (version != 1) {
/* 234 */       JmqiException jmqiEx = new JmqiException(this.env, -1, null, 2, 6107, null);
/*     */       
/* 236 */       if (Trace.isOn) {
/* 237 */         Trace.throwing(this, "com.ibm.mq.jmqi.system.zrfp.zrfpFixed", "read(byte [ ],int)", (Throwable)jmqiEx, 2);
/*     */       }
/*     */       
/* 240 */       throw jmqiEx;
/*     */     } 
/* 242 */     this.flags = tmp[1];
/* 243 */     pos += 4;
/*     */     
/* 245 */     boolean readSwap = getReadSwap();
/*     */ 
/*     */     
/* 248 */     this.strucLength = Utils.readU32(buffer, pos, readSwap);
/* 249 */     pos += 4;
/*     */ 
/*     */     
/* 252 */     this.propsLength = Utils.readU32(buffer, pos, readSwap);
/* 253 */     pos += 4;
/*     */ 
/*     */     
/* 256 */     this.ccsid = this.dc.readI32(buffer, pos, readSwap);
/* 257 */     pos += 4;
/*     */ 
/*     */     
/* 260 */     this.ccsidType = Utils.readU8(buffer, pos, readSwap);
/* 261 */     pos++;
/*     */ 
/*     */     
/* 264 */     this.compatibles = Utils.readU8(buffer, pos, readSwap);
/* 265 */     pos++;
/*     */ 
/*     */     
/* 268 */     pos += 2;
/*     */     
/* 270 */     if (Trace.isOn) {
/* 271 */       Trace.exit(this, "com.ibm.mq.jmqi.system.zrfp.zrfpFixed", "read(byte [ ],int)", 
/* 272 */           Integer.valueOf(pos));
/*     */     }
/* 274 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean getReadSwap() {
/* 282 */     boolean result = ((this.flags & 0x1) == 1);
/*     */ 
/*     */     
/* 285 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int getFlags() {
/* 293 */     return this.flags;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setFlags(int flags) {
/* 301 */     this.flags = flags;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   long getStrucLength() {
/* 309 */     return this.strucLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setStrucLength(long strucLength) {
/* 317 */     this.strucLength = strucLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   long getPropsLength() {
/* 325 */     return this.propsLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setPropsLength(long propsLength) {
/* 333 */     this.propsLength = propsLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int getCcsid() {
/* 341 */     return this.ccsid;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setCcsid(int ccsid) {
/* 349 */     this.ccsid = ccsid;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int getCcsidType() {
/* 357 */     return this.ccsidType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setCcsidType(int ccsidType) {
/* 365 */     this.ccsidType = ccsidType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int getCompatibles() {
/* 373 */     return this.compatibles;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setCompatibles(int compatibles) {
/* 381 */     this.compatibles = compatibles;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\zrfp\zrfpFixed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */