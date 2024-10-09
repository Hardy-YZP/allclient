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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class zrfpFloat32
/*     */   extends zrfPROP
/*     */ {
/*     */   static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/zrfp/zrfpFloat32.java";
/*     */   private static final int SIZEOF_SUPPORT = 4;
/*     */   private static final int SIZEOF_VALUE = 4;
/*     */   private static final int SIZEOF_NAMELENGTH = 2;
/*     */   private static final int SIZEOF_COPYOPTIONS = 1;
/*     */   private String name;
/*     */   private float value;
/*     */   
/*     */   static {
/*  73 */     if (Trace.isOn) {
/*  74 */       Trace.data("com.ibm.mq.jmqi.system.zrfp.zrfpFloat32", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/zrfp/zrfpFloat32.java");
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
/*     */   zrfpFloat32(JmqiEnvironment env, JmqiDC dc) {
/* 101 */     super(env, dc, 7, 15);
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
/* 118 */     if (Trace.isOn) {
/* 119 */       Trace.entry(this, "com.ibm.mq.jmqi.system.zrfp.zrfpFloat32", "write(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 121 */             Integer.valueOf(startPos), Boolean.valueOf(writeSwap), writeCp, tls });
/*     */     }
/* 123 */     int pos = startPos;
/*     */     
/* 125 */     Utils.writeU8U24(this.TYPE, this.flags, buffer, pos, writeSwap);
/* 126 */     pos += 4;
/*     */     
/* 128 */     this.dc.writeI32(1, buffer, pos, writeSwap);
/* 129 */     pos += 4;
/*     */     
/* 131 */     int valueInt32 = Float.floatToIntBits(this.value);
/* 132 */     this.dc.writeI32(valueInt32, buffer, pos, writeSwap);
/* 133 */     pos += 4;
/*     */     
/* 135 */     byte[] nameBytes = this.dc.getStrBytes(this.name, writeCp);
/* 136 */     int nameLength = nameBytes.length;
/* 137 */     this.dc.writeU16(nameLength, buffer, pos, writeSwap);
/* 138 */     pos += 2;
/*     */     
/* 140 */     Utils.writeU8(22, buffer, pos, writeSwap);
/* 141 */     pos++;
/*     */     
/* 143 */     System.arraycopy(nameBytes, 0, buffer, pos, nameLength);
/* 144 */     pos += nameLength;
/*     */     
/* 146 */     int lengthStruct = this.FIXED_LENGTH + nameLength;
/* 147 */     int paddingStruct = this.dc.getPaddingLength(lengthStruct);
/* 148 */     for (int i = 0; i < paddingStruct; i++) {
/* 149 */       buffer[pos + i] = 0;
/*     */     }
/* 151 */     pos += paddingStruct;
/*     */     
/* 153 */     this.propLen = nameLength + 4;
/*     */     
/* 155 */     if (Trace.isOn) {
/* 156 */       Trace.exit(this, "com.ibm.mq.jmqi.system.zrfp.zrfpFloat32", "write(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", 
/* 157 */           Integer.valueOf(pos));
/*     */     }
/* 159 */     return pos;
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
/* 176 */     if (Trace.isOn) {
/* 177 */       Trace.entry(this, "com.ibm.mq.jmqi.system.zrfp.zrfpFloat32", "read(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 179 */             Integer.valueOf(startPos), Boolean.valueOf(readSwap), readCp, tls });
/*     */     }
/* 181 */     int pos = startPos;
/* 182 */     int[] tmp = Utils.readU8U24(buffer, pos, readSwap);
/*     */     
/* 184 */     int type = tmp[0];
/* 185 */     if (type != this.TYPE) {
/* 186 */       JmqiException jmqiEx = new JmqiException(this.env, -1, null, 2, 2473, null);
/*     */ 
/*     */       
/* 189 */       if (Trace.isOn) {
/* 190 */         Trace.throwing(this, "com.ibm.mq.jmqi.system.zrfp.zrfpFloat32", "read(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", (Throwable)jmqiEx);
/*     */       }
/*     */       
/* 193 */       throw jmqiEx;
/*     */     } 
/*     */ 
/*     */     
/* 197 */     this.flags = tmp[1];
/* 198 */     pos += 4;
/*     */ 
/*     */     
/* 201 */     pos += 4;
/*     */ 
/*     */     
/* 204 */     int valueInt32 = this.dc.readI32(buffer, pos, readSwap);
/* 205 */     this.value = Float.intBitsToFloat(valueInt32);
/* 206 */     pos += 4;
/*     */ 
/*     */     
/* 209 */     int nameLength = this.dc.readU16(buffer, pos, readSwap);
/* 210 */     pos += 2;
/*     */ 
/*     */     
/* 213 */     pos++;
/*     */ 
/*     */     
/* 216 */     this.name = this.dc.readField(buffer, pos, nameLength, readCp, tls);
/* 217 */     pos += nameLength;
/*     */ 
/*     */     
/* 220 */     int lengthStruct = this.FIXED_LENGTH + nameLength;
/* 221 */     int paddingStruct = this.dc.getPaddingLength(lengthStruct);
/* 222 */     pos += paddingStruct;
/*     */     
/* 224 */     if (Trace.isOn) {
/* 225 */       Trace.exit(this, "com.ibm.mq.jmqi.system.zrfp.zrfpFloat32", "read(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", 
/* 226 */           Integer.valueOf(pos));
/*     */     }
/* 228 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setNameValue(String name, float value) {
/* 237 */     this.name = name;
/* 238 */     this.value = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   String getName() {
/* 246 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   float getValue() {
/* 254 */     return this.value;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\zrfp\zrfpFloat32.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */