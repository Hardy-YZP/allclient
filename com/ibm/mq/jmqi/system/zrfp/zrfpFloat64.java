/*     */ package com.ibm.mq.jmqi.system.zrfp;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.internal.JmqiDC;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class zrfpFloat64
/*     */   extends zrfPROP
/*     */ {
/*     */   static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/zrfp/zrfpFloat64.java";
/*     */   private static final int SIZEOF_SUPPORT = 4;
/*     */   private static final int SIZEOF_VALUE = 8;
/*     */   private static final int SIZEOF_NAMELENGTH = 2;
/*     */   private static final int SIZEOF_COPYOPTIONS = 1;
/*     */   private String name;
/*     */   private double value;
/*     */   
/*     */   static {
/*  64 */     if (Trace.isOn) {
/*  65 */       Trace.data("com.ibm.mq.jmqi.system.zrfp.zrfpFloat64", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/zrfp/zrfpFloat64.java");
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
/*     */ 
/*     */ 
/*     */   
/*     */   zrfpFloat64(JmqiEnvironment env, JmqiDC dc) {
/*  92 */     super(env, dc, 8, 19);
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
/*     */   int write(byte[] buffer, int startPos, boolean writeSwap, JmqiCodepage writeCp, JmqiTls tls) throws JmqiException {
/* 109 */     if (Trace.isOn) {
/* 110 */       Trace.entry(this, "com.ibm.mq.jmqi.system.zrfp.zrfpFloat64", "write(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 112 */             Integer.valueOf(startPos), Boolean.valueOf(writeSwap), writeCp, tls });
/*     */     }
/* 114 */     int pos = startPos;
/*     */     
/* 116 */     Utils.writeU8U24(this.TYPE, this.flags, buffer, pos, writeSwap);
/* 117 */     pos += 4;
/*     */     
/* 119 */     this.dc.writeI32(1, buffer, pos, writeSwap);
/* 120 */     pos += 4;
/*     */     
/* 122 */     long valueInt64 = Double.doubleToLongBits(this.value);
/* 123 */     this.dc.writeI64(valueInt64, buffer, pos, writeSwap);
/* 124 */     pos += 8;
/*     */     
/* 126 */     byte[] nameBytes = this.dc.getStrBytes(this.name, writeCp);
/* 127 */     int nameLength = nameBytes.length;
/* 128 */     this.dc.writeU16(nameLength, buffer, pos, writeSwap);
/* 129 */     pos += 2;
/*     */     
/* 131 */     Utils.writeU8(22, buffer, pos, writeSwap);
/* 132 */     pos++;
/*     */     
/* 134 */     System.arraycopy(nameBytes, 0, buffer, pos, nameLength);
/* 135 */     pos += nameLength;
/*     */     
/* 137 */     int lengthStruct = this.FIXED_LENGTH + nameLength;
/* 138 */     int paddingStruct = this.dc.getPaddingLength(lengthStruct);
/* 139 */     for (int i = 0; i < paddingStruct; i++) {
/* 140 */       buffer[pos + i] = 0;
/*     */     }
/* 142 */     pos += paddingStruct;
/*     */     
/* 144 */     this.propLen = nameLength + 8;
/*     */     
/* 146 */     if (Trace.isOn) {
/* 147 */       Trace.exit(this, "com.ibm.mq.jmqi.system.zrfp.zrfpFloat64", "write(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", 
/* 148 */           Integer.valueOf(pos));
/*     */     }
/* 150 */     return pos;
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
/*     */   int read(byte[] buffer, int startPos, boolean readSwap, JmqiCodepage readCp, JmqiTls tls) throws JmqiException {
/* 167 */     if (Trace.isOn) {
/* 168 */       Trace.entry(this, "com.ibm.mq.jmqi.system.zrfp.zrfpFloat64", "read(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 170 */             Integer.valueOf(startPos), Boolean.valueOf(readSwap), readCp, tls });
/*     */     }
/* 172 */     int pos = startPos;
/* 173 */     int[] tmp = Utils.readU8U24(buffer, pos, readSwap);
/*     */     
/* 175 */     int type = tmp[0];
/* 176 */     if (type != this.TYPE) {
/* 177 */       JmqiException jmqiEx = new JmqiException(this.env, -1, null, 2, 2473, null);
/*     */ 
/*     */       
/* 180 */       if (Trace.isOn) {
/* 181 */         Trace.throwing(this, "com.ibm.mq.jmqi.system.zrfp.zrfpFloat64", "read(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", (Throwable)jmqiEx);
/*     */       }
/*     */       
/* 184 */       throw jmqiEx;
/*     */     } 
/*     */ 
/*     */     
/* 188 */     this.flags = tmp[1];
/* 189 */     pos += 4;
/*     */ 
/*     */     
/* 192 */     pos += 4;
/*     */ 
/*     */     
/* 195 */     long valueInt64 = this.dc.readI64(buffer, pos, readSwap);
/* 196 */     this.value = Double.longBitsToDouble(valueInt64);
/* 197 */     pos += 8;
/*     */ 
/*     */     
/* 200 */     int nameLength = this.dc.readU16(buffer, pos, readSwap);
/* 201 */     pos += 2;
/*     */ 
/*     */     
/* 204 */     pos++;
/*     */ 
/*     */     
/* 207 */     this.name = this.dc.readField(buffer, pos, nameLength, readCp, tls);
/* 208 */     pos += nameLength;
/*     */ 
/*     */     
/* 211 */     int lengthStruct = this.FIXED_LENGTH + nameLength;
/* 212 */     int paddingStruct = this.dc.getPaddingLength(lengthStruct);
/* 213 */     pos += paddingStruct;
/*     */     
/* 215 */     if (Trace.isOn) {
/* 216 */       Trace.exit(this, "com.ibm.mq.jmqi.system.zrfp.zrfpFloat64", "read(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", 
/* 217 */           Integer.valueOf(pos));
/*     */     }
/* 219 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setNameValue(String name, double value) {
/* 228 */     this.name = name;
/* 229 */     this.value = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   String getName() {
/* 237 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   double getValue() {
/* 245 */     return this.value;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\zrfp\zrfpFloat64.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */