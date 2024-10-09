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
/*     */ class zrfpByteArray
/*     */   extends zrfPROP
/*     */ {
/*     */   static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/zrfp/zrfpByteArray.java";
/*     */   private static final int SIZEOF_SUPPORT = 4;
/*     */   private static final int SIZEOF_VALUELENGTH = 4;
/*     */   private static final int SIZEOF_NAMELENGTH = 2;
/*     */   private static final int SIZEOF_COPYOPTIONS = 1;
/*     */   private String name;
/*     */   private byte[] value;
/*     */   
/*     */   static {
/*  60 */     if (Trace.isOn) {
/*  61 */       Trace.data("com.ibm.mq.jmqi.system.zrfp.zrfpByteArray", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/zrfp/zrfpByteArray.java");
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
/*     */   zrfpByteArray(JmqiEnvironment env, JmqiDC dc) {
/*  88 */     super(env, dc, 2, 15);
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
/* 105 */     if (Trace.isOn) {
/* 106 */       Trace.entry(this, "com.ibm.mq.jmqi.system.zrfp.zrfpByteArray", "write(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 108 */             Integer.valueOf(startPos), Boolean.valueOf(writeSwap), writeCp, tls });
/*     */     }
/* 110 */     int pos = startPos;
/*     */     
/* 112 */     Utils.writeU8U24(this.TYPE, this.flags, buffer, pos, writeSwap);
/* 113 */     pos += 4;
/*     */     
/* 115 */     this.dc.writeI32(1, buffer, pos, writeSwap);
/* 116 */     pos += 4;
/*     */     
/* 118 */     int valueLength = this.value.length;
/* 119 */     this.dc.writeI32(valueLength, buffer, pos, writeSwap);
/* 120 */     pos += 4;
/*     */     
/* 122 */     byte[] nameBytes = this.dc.getStrBytes(this.name, writeCp);
/* 123 */     int nameLength = nameBytes.length;
/* 124 */     this.dc.writeU16(nameLength, buffer, pos, writeSwap);
/* 125 */     pos += 2;
/*     */     
/* 127 */     Utils.writeU8(22, buffer, pos, writeSwap);
/* 128 */     pos++;
/*     */     
/* 130 */     System.arraycopy(nameBytes, 0, buffer, pos, nameLength);
/* 131 */     pos += nameLength;
/*     */     
/* 133 */     System.arraycopy(this.value, 0, buffer, pos, valueLength);
/* 134 */     pos += valueLength;
/*     */     
/* 136 */     buffer[pos] = 0;
/* 137 */     pos++;
/*     */     
/* 139 */     int lengthStruct = this.FIXED_LENGTH + nameLength + valueLength + 1;
/* 140 */     int paddingStruct = this.dc.getPaddingLength(lengthStruct);
/* 141 */     for (int i = 0; i < paddingStruct; i++) {
/* 142 */       buffer[pos + i] = 0;
/*     */     }
/* 144 */     pos += paddingStruct;
/*     */     
/* 146 */     this.propLen = nameLength + valueLength;
/*     */     
/* 148 */     if (Trace.isOn) {
/* 149 */       Trace.exit(this, "com.ibm.mq.jmqi.system.zrfp.zrfpByteArray", "write(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", 
/* 150 */           Integer.valueOf(pos));
/*     */     }
/* 152 */     return pos;
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
/* 169 */     if (Trace.isOn) {
/* 170 */       Trace.entry(this, "com.ibm.mq.jmqi.system.zrfp.zrfpByteArray", "read(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 172 */             Integer.valueOf(startPos), Boolean.valueOf(readSwap), readCp, tls });
/*     */     }
/* 174 */     int pos = startPos;
/* 175 */     int[] tmp = Utils.readU8U24(buffer, pos, readSwap);
/*     */     
/* 177 */     int type = tmp[0];
/* 178 */     if (type != this.TYPE) {
/* 179 */       JmqiException jmqiEx = new JmqiException(this.env, -1, null, 2, 2473, null);
/*     */ 
/*     */       
/* 182 */       if (Trace.isOn) {
/* 183 */         Trace.throwing(this, "com.ibm.mq.jmqi.system.zrfp.zrfpByteArray", "read(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", (Throwable)jmqiEx);
/*     */       }
/*     */       
/* 186 */       throw jmqiEx;
/*     */     } 
/*     */ 
/*     */     
/* 190 */     this.flags = tmp[1];
/* 191 */     pos += 4;
/*     */ 
/*     */     
/* 194 */     pos += 4;
/*     */ 
/*     */     
/* 197 */     int valueLength = this.dc.readI32(buffer, pos, readSwap);
/* 198 */     pos += 4;
/*     */ 
/*     */     
/* 201 */     int nameLength = this.dc.readU16(buffer, pos, readSwap);
/* 202 */     pos += 2;
/*     */ 
/*     */     
/* 205 */     pos++;
/*     */ 
/*     */     
/* 208 */     this.name = this.dc.readField(buffer, pos, nameLength, readCp, tls);
/* 209 */     pos += nameLength;
/*     */ 
/*     */     
/* 212 */     this.value = new byte[valueLength];
/* 213 */     System.arraycopy(buffer, pos, this.value, 0, valueLength);
/* 214 */     pos += valueLength;
/*     */ 
/*     */     
/* 217 */     pos++;
/*     */ 
/*     */     
/* 220 */     int lengthStruct = this.FIXED_LENGTH + nameLength + valueLength + 1;
/* 221 */     int paddingStruct = this.dc.getPaddingLength(lengthStruct);
/* 222 */     pos += paddingStruct;
/*     */     
/* 224 */     if (Trace.isOn) {
/* 225 */       Trace.exit(this, "com.ibm.mq.jmqi.system.zrfp.zrfpByteArray", "read(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", 
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
/*     */   void setNameValue(String name, byte[] value) {
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
/*     */   byte[] getValue() {
/* 254 */     return this.value;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\zrfp\zrfpByteArray.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */