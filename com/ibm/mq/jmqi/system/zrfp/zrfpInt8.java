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
/*     */ class zrfpInt8
/*     */   extends zrfPROP
/*     */ {
/*     */   static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/zrfp/zrfpInt8.java";
/*     */   private static final int SIZEOF_SUPPORT = 4;
/*     */   private static final int SIZEOF_NAMELENGTH = 2;
/*     */   private static final int SIZEOF_VALUE = 1;
/*     */   private static final int SIZEOF_COPYOPTIONS = 1;
/*     */   private String name;
/*     */   private byte value;
/*     */   
/*     */   static {
/*  62 */     if (Trace.isOn) {
/*  63 */       Trace.data("com.ibm.mq.jmqi.system.zrfp.zrfpInt8", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/zrfp/zrfpInt8.java");
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
/*     */   zrfpInt8(JmqiEnvironment env, JmqiDC dc) {
/*  90 */     super(env, dc, 3, 12);
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
/* 107 */     if (Trace.isOn) {
/* 108 */       Trace.entry(this, "com.ibm.mq.jmqi.system.zrfp.zrfpInt8", "write(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 110 */             Integer.valueOf(startPos), Boolean.valueOf(writeSwap), writeCp, tls });
/*     */     }
/* 112 */     int pos = startPos;
/*     */     
/* 114 */     Utils.writeU8U24(this.TYPE, this.flags, buffer, pos, writeSwap);
/* 115 */     pos += 4;
/*     */     
/* 117 */     this.dc.writeI32(1, buffer, pos, writeSwap);
/* 118 */     pos += 4;
/*     */     
/* 120 */     byte[] nameBytes = this.dc.getStrBytes(this.name, writeCp);
/* 121 */     int nameLength = nameBytes.length;
/* 122 */     this.dc.writeU16(nameLength, buffer, pos, writeSwap);
/* 123 */     pos += 2;
/*     */     
/* 125 */     buffer[pos] = this.value;
/* 126 */     pos++;
/*     */     
/* 128 */     Utils.writeU8(22, buffer, pos, writeSwap);
/* 129 */     pos++;
/*     */     
/* 131 */     System.arraycopy(nameBytes, 0, buffer, pos, nameLength);
/* 132 */     pos += nameLength;
/*     */     
/* 134 */     int lengthStruct = this.FIXED_LENGTH + nameLength;
/* 135 */     int paddingStruct = this.dc.getPaddingLength(lengthStruct);
/* 136 */     for (int i = 0; i < paddingStruct; i++) {
/* 137 */       buffer[pos + i] = 0;
/*     */     }
/* 139 */     pos += paddingStruct;
/*     */     
/* 141 */     this.propLen = nameLength + 1;
/*     */     
/* 143 */     if (Trace.isOn) {
/* 144 */       Trace.exit(this, "com.ibm.mq.jmqi.system.zrfp.zrfpInt8", "write(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", 
/* 145 */           Integer.valueOf(pos));
/*     */     }
/* 147 */     return pos;
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
/* 164 */     if (Trace.isOn) {
/* 165 */       Trace.entry(this, "com.ibm.mq.jmqi.system.zrfp.zrfpInt8", "read(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 167 */             Integer.valueOf(startPos), Boolean.valueOf(readSwap), readCp, tls });
/*     */     }
/* 169 */     int pos = startPos;
/* 170 */     int[] tmp = Utils.readU8U24(buffer, pos, readSwap);
/*     */     
/* 172 */     int type = tmp[0];
/* 173 */     if (type != this.TYPE) {
/* 174 */       JmqiException jmqiEx = new JmqiException(this.env, -1, null, 2, 2473, null);
/*     */ 
/*     */       
/* 177 */       if (Trace.isOn) {
/* 178 */         Trace.throwing(this, "com.ibm.mq.jmqi.system.zrfp.zrfpInt8", "read(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", (Throwable)jmqiEx);
/*     */       }
/*     */       
/* 181 */       throw jmqiEx;
/*     */     } 
/*     */ 
/*     */     
/* 185 */     this.flags = tmp[1];
/* 186 */     pos += 4;
/*     */ 
/*     */     
/* 189 */     pos += 4;
/*     */ 
/*     */     
/* 192 */     int nameLength = this.dc.readU16(buffer, pos, readSwap);
/* 193 */     pos += 2;
/*     */ 
/*     */     
/* 196 */     this.value = buffer[pos];
/* 197 */     pos++;
/*     */ 
/*     */     
/* 200 */     pos++;
/*     */ 
/*     */     
/* 203 */     this.name = this.dc.readField(buffer, pos, nameLength, readCp, tls);
/* 204 */     pos += nameLength;
/*     */ 
/*     */     
/* 207 */     int lengthStruct = this.FIXED_LENGTH + nameLength;
/* 208 */     int paddingStruct = this.dc.getPaddingLength(lengthStruct);
/* 209 */     pos += paddingStruct;
/*     */     
/* 211 */     if (Trace.isOn) {
/* 212 */       Trace.exit(this, "com.ibm.mq.jmqi.system.zrfp.zrfpInt8", "read(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", 
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
/*     */   void setNameValue(String name, byte value) {
/* 224 */     this.name = name;
/* 225 */     this.value = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   String getName() {
/* 233 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   byte getValue() {
/* 241 */     return this.value;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\zrfp\zrfpInt8.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */