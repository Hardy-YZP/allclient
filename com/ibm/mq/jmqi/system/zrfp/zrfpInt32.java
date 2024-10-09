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
/*     */ class zrfpInt32
/*     */   extends zrfPROP
/*     */ {
/*     */   static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/zrfp/zrfpInt32.java";
/*     */   private static final int SIZEOF_SUPPORT = 4;
/*     */   private static final int SIZEOF_VALUE = 4;
/*     */   private static final int SIZEOF_NAMELENGTH = 2;
/*     */   private static final int SIZEOF_COPYOPTIONS = 1;
/*     */   private String name;
/*     */   private int value;
/*     */   
/*     */   static {
/*  65 */     if (Trace.isOn) {
/*  66 */       Trace.data("com.ibm.mq.jmqi.system.zrfp.zrfpInt32", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/zrfp/zrfpInt32.java");
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
/*     */   zrfpInt32(JmqiEnvironment env, JmqiDC dc) {
/*  93 */     super(env, dc, 5, 15);
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
/* 110 */     if (Trace.isOn) {
/* 111 */       Trace.entry(this, "com.ibm.mq.jmqi.system.zrfp.zrfpInt32", "write(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 113 */             Integer.valueOf(startPos), Boolean.valueOf(writeSwap), writeCp, tls });
/*     */     }
/* 115 */     int pos = startPos;
/*     */     
/* 117 */     Utils.writeU8U24(this.TYPE, this.flags, buffer, pos, writeSwap);
/* 118 */     pos += 4;
/*     */     
/* 120 */     this.dc.writeI32(1, buffer, pos, writeSwap);
/* 121 */     pos += 4;
/*     */     
/* 123 */     this.dc.writeI32(this.value, buffer, pos, writeSwap);
/* 124 */     pos += 4;
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
/* 144 */     this.propLen = nameLength + 4;
/*     */     
/* 146 */     if (Trace.isOn) {
/* 147 */       Trace.exit(this, "com.ibm.mq.jmqi.system.zrfp.zrfpInt32", "write(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", 
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
/* 168 */       Trace.entry(this, "com.ibm.mq.jmqi.system.zrfp.zrfpInt32", "read(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
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
/* 181 */         Trace.throwing(this, "com.ibm.mq.jmqi.system.zrfp.zrfpInt32", "read(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", (Throwable)jmqiEx);
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
/* 195 */     this.value = this.dc.readI32(buffer, pos, readSwap);
/* 196 */     pos += 4;
/*     */ 
/*     */     
/* 199 */     int nameLength = this.dc.readU16(buffer, pos, readSwap);
/* 200 */     pos += 2;
/*     */ 
/*     */     
/* 203 */     pos++;
/*     */ 
/*     */     
/* 206 */     this.name = this.dc.readField(buffer, pos, nameLength, readCp, tls);
/* 207 */     pos += nameLength;
/*     */ 
/*     */     
/* 210 */     int lengthStruct = this.FIXED_LENGTH + nameLength;
/* 211 */     int paddingStruct = this.dc.getPaddingLength(lengthStruct);
/* 212 */     pos += paddingStruct;
/*     */     
/* 214 */     if (Trace.isOn) {
/* 215 */       Trace.exit(this, "com.ibm.mq.jmqi.system.zrfp.zrfpInt32", "read(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", 
/* 216 */           Integer.valueOf(pos));
/*     */     }
/* 218 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setNameValue(String name, int value) {
/* 227 */     this.name = name;
/* 228 */     this.value = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   String getName() {
/* 236 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int getValue() {
/* 244 */     return this.value;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\zrfp\zrfpInt32.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */