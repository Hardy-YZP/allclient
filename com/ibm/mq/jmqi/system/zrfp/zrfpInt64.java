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
/*     */ class zrfpInt64
/*     */   extends zrfPROP
/*     */ {
/*     */   static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/zrfp/zrfpInt64.java";
/*     */   private static final int SIZEOF_SUPPORT = 4;
/*     */   private static final int SIZEOF_VALUE = 8;
/*     */   private static final int SIZEOF_NAMELENGTH = 2;
/*     */   private static final int SIZEOF_COPYOPTIONS = 1;
/*     */   private String name;
/*     */   private long value;
/*     */   
/*     */   static {
/*  59 */     if (Trace.isOn) {
/*  60 */       Trace.data("com.ibm.mq.jmqi.system.zrfp.zrfpInt64", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/zrfp/zrfpInt64.java");
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
/*     */   zrfpInt64(JmqiEnvironment env, JmqiDC dc) {
/*  87 */     super(env, dc, 6, 19);
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
/* 104 */     if (Trace.isOn) {
/* 105 */       Trace.entry(this, "com.ibm.mq.jmqi.system.zrfp.zrfpInt64", "write(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 107 */             Integer.valueOf(startPos), Boolean.valueOf(writeSwap), writeCp, tls });
/*     */     }
/* 109 */     int pos = startPos;
/*     */     
/* 111 */     Utils.writeU8U24(this.TYPE, this.flags, buffer, pos, writeSwap);
/* 112 */     pos += 4;
/*     */     
/* 114 */     this.dc.writeI32(1, buffer, pos, writeSwap);
/* 115 */     pos += 4;
/*     */     
/* 117 */     this.dc.writeI64(this.value, buffer, pos, writeSwap);
/* 118 */     pos += 8;
/*     */     
/* 120 */     byte[] nameBytes = this.dc.getStrBytes(this.name, writeCp);
/* 121 */     int nameLength = nameBytes.length;
/* 122 */     this.dc.writeU16(nameLength, buffer, pos, writeSwap);
/* 123 */     pos += 2;
/*     */     
/* 125 */     Utils.writeU8(22, buffer, pos, writeSwap);
/* 126 */     pos++;
/*     */     
/* 128 */     System.arraycopy(nameBytes, 0, buffer, pos, nameLength);
/* 129 */     pos += nameLength;
/*     */     
/* 131 */     int lengthStruct = this.FIXED_LENGTH + nameLength;
/* 132 */     int paddingStruct = this.dc.getPaddingLength(lengthStruct);
/* 133 */     for (int i = 0; i < paddingStruct; i++) {
/* 134 */       buffer[pos + i] = 0;
/*     */     }
/* 136 */     pos += paddingStruct;
/*     */     
/* 138 */     this.propLen = nameLength + 8;
/*     */     
/* 140 */     if (Trace.isOn) {
/* 141 */       Trace.exit(this, "com.ibm.mq.jmqi.system.zrfp.zrfpInt64", "write(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", 
/* 142 */           Integer.valueOf(pos));
/*     */     }
/* 144 */     return pos;
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
/* 161 */     if (Trace.isOn) {
/* 162 */       Trace.entry(this, "com.ibm.mq.jmqi.system.zrfp.zrfpInt64", "read(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 164 */             Integer.valueOf(startPos), Boolean.valueOf(readSwap), readCp, tls });
/*     */     }
/* 166 */     int pos = startPos;
/* 167 */     int[] tmp = Utils.readU8U24(buffer, pos, readSwap);
/*     */     
/* 169 */     int type = tmp[0];
/* 170 */     if (type != this.TYPE) {
/* 171 */       JmqiException jmqiEx = new JmqiException(this.env, -1, null, 2, 2473, null);
/*     */ 
/*     */       
/* 174 */       if (Trace.isOn) {
/* 175 */         Trace.throwing(this, "com.ibm.mq.jmqi.system.zrfp.zrfpInt64", "read(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", (Throwable)jmqiEx);
/*     */       }
/*     */       
/* 178 */       throw jmqiEx;
/*     */     } 
/*     */ 
/*     */     
/* 182 */     this.flags = tmp[1];
/* 183 */     pos += 4;
/*     */ 
/*     */     
/* 186 */     pos += 4;
/*     */ 
/*     */     
/* 189 */     this.value = this.dc.readI64(buffer, pos, readSwap);
/* 190 */     pos += 8;
/*     */ 
/*     */     
/* 193 */     int nameLength = this.dc.readU16(buffer, pos, readSwap);
/* 194 */     pos += 2;
/*     */ 
/*     */     
/* 197 */     pos++;
/*     */ 
/*     */     
/* 200 */     this.name = this.dc.readField(buffer, pos, nameLength, readCp, tls);
/* 201 */     pos += nameLength;
/*     */ 
/*     */     
/* 204 */     int lengthStruct = this.FIXED_LENGTH + nameLength;
/* 205 */     int paddingStruct = this.dc.getPaddingLength(lengthStruct);
/* 206 */     pos += paddingStruct;
/*     */     
/* 208 */     if (Trace.isOn) {
/* 209 */       Trace.exit(this, "com.ibm.mq.jmqi.system.zrfp.zrfpInt64", "read(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", 
/* 210 */           Integer.valueOf(pos));
/*     */     }
/* 212 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setNameValue(String name, long value) {
/* 221 */     this.name = name;
/* 222 */     this.value = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   String getName() {
/* 230 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   long getValue() {
/* 238 */     return this.value;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\zrfp\zrfpInt64.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */