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
/*     */ class zrfpInt16
/*     */   extends zrfPROP
/*     */ {
/*     */   static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/zrfp/zrfpInt16.java";
/*     */   private static final int SIZEOF_SUPPORT = 4;
/*     */   private static final int SIZEOF_VALUE = 2;
/*     */   private static final int SIZEOF_NAMELENGTH = 2;
/*     */   private static final int SIZEOF_COPYOPTIONS = 1;
/*     */   private String name;
/*     */   private short value;
/*     */   
/*     */   static {
/*  63 */     if (Trace.isOn) {
/*  64 */       Trace.data("com.ibm.mq.jmqi.system.zrfp.zrfpInt16", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/zrfp/zrfpInt16.java");
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
/*     */   zrfpInt16(JmqiEnvironment env, JmqiDC dc) {
/*  91 */     super(env, dc, 4, 13);
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
/* 108 */     if (Trace.isOn) {
/* 109 */       Trace.entry(this, "com.ibm.mq.jmqi.system.zrfp.zrfpInt16", "write(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 111 */             Integer.valueOf(startPos), Boolean.valueOf(writeSwap), writeCp, tls });
/*     */     }
/* 113 */     int pos = startPos;
/*     */     
/* 115 */     Utils.writeU8U24(this.TYPE, this.flags, buffer, pos, writeSwap);
/* 116 */     pos += 4;
/*     */     
/* 118 */     this.dc.writeI32(1, buffer, pos, writeSwap);
/* 119 */     pos += 4;
/*     */     
/* 121 */     Utils.writeI16(this.value, buffer, pos, writeSwap);
/* 122 */     pos += 2;
/*     */     
/* 124 */     byte[] nameBytes = this.dc.getStrBytes(this.name, writeCp);
/* 125 */     int nameLength = nameBytes.length;
/* 126 */     this.dc.writeU16(nameLength, buffer, pos, writeSwap);
/* 127 */     pos += 2;
/*     */     
/* 129 */     Utils.writeU8(22, buffer, pos, writeSwap);
/* 130 */     pos++;
/*     */     
/* 132 */     System.arraycopy(nameBytes, 0, buffer, pos, nameLength);
/* 133 */     pos += nameLength;
/*     */     
/* 135 */     int lengthStruct = this.FIXED_LENGTH + nameLength;
/* 136 */     int paddingStruct = this.dc.getPaddingLength(lengthStruct);
/* 137 */     for (int i = 0; i < paddingStruct; i++) {
/* 138 */       buffer[pos + i] = 0;
/*     */     }
/* 140 */     pos += paddingStruct;
/*     */     
/* 142 */     this.propLen = nameLength + 2;
/*     */     
/* 144 */     if (Trace.isOn) {
/* 145 */       Trace.exit(this, "com.ibm.mq.jmqi.system.zrfp.zrfpInt16", "write(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", 
/* 146 */           Integer.valueOf(pos));
/*     */     }
/* 148 */     return pos;
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
/* 165 */     if (Trace.isOn) {
/* 166 */       Trace.entry(this, "com.ibm.mq.jmqi.system.zrfp.zrfpInt16", "read(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 168 */             Integer.valueOf(startPos), Boolean.valueOf(readSwap), readCp, tls });
/*     */     }
/* 170 */     int pos = startPos;
/* 171 */     int[] tmp = Utils.readU8U24(buffer, pos, readSwap);
/*     */     
/* 173 */     int type = tmp[0];
/* 174 */     if (type != this.TYPE) {
/* 175 */       JmqiException jmqiEx = new JmqiException(this.env, -1, null, 2, 2473, null);
/*     */ 
/*     */       
/* 178 */       if (Trace.isOn) {
/* 179 */         Trace.throwing(this, "com.ibm.mq.jmqi.system.zrfp.zrfpInt16", "read(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", (Throwable)jmqiEx);
/*     */       }
/*     */       
/* 182 */       throw jmqiEx;
/*     */     } 
/*     */ 
/*     */     
/* 186 */     this.flags = tmp[1];
/* 187 */     pos += 4;
/*     */ 
/*     */     
/* 190 */     pos += 4;
/*     */ 
/*     */     
/* 193 */     this.value = Utils.readI16(buffer, pos, readSwap);
/* 194 */     pos += 2;
/*     */ 
/*     */     
/* 197 */     int nameLength = this.dc.readU16(buffer, pos, readSwap);
/* 198 */     pos += 2;
/*     */ 
/*     */     
/* 201 */     pos++;
/*     */ 
/*     */     
/* 204 */     this.name = this.dc.readField(buffer, pos, nameLength, readCp, tls);
/* 205 */     pos += nameLength;
/*     */ 
/*     */     
/* 208 */     int lengthStruct = this.FIXED_LENGTH + nameLength;
/* 209 */     int paddingStruct = this.dc.getPaddingLength(lengthStruct);
/* 210 */     pos += paddingStruct;
/*     */     
/* 212 */     if (Trace.isOn) {
/* 213 */       Trace.exit(this, "com.ibm.mq.jmqi.system.zrfp.zrfpInt16", "read(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", 
/* 214 */           Integer.valueOf(pos));
/*     */     }
/* 216 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setNameValue(String name, short value) {
/* 225 */     this.name = name;
/* 226 */     this.value = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   String getName() {
/* 234 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   short getValue() {
/* 242 */     return this.value;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\zrfp\zrfpInt16.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */