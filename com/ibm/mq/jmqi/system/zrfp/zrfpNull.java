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
/*     */ class zrfpNull
/*     */   extends zrfPROP
/*     */ {
/*     */   static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/zrfp/zrfpNull.java";
/*     */   private static final int SIZEOF_SUPPORT = 4;
/*     */   private static final int SIZEOF_NAMELENGTH = 2;
/*     */   private static final int SIZEOF_COPYOPTIONS = 1;
/*     */   private String name;
/*     */   
/*     */   static {
/*  57 */     if (Trace.isOn) {
/*  58 */       Trace.data("com.ibm.mq.jmqi.system.zrfp.zrfpNull", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/zrfp/zrfpNull.java");
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
/*     */   zrfpNull(JmqiEnvironment env, JmqiDC dc) {
/*  83 */     super(env, dc, 9, 11);
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
/* 100 */     if (Trace.isOn) {
/* 101 */       Trace.entry(this, "com.ibm.mq.jmqi.system.zrfp.zrfpNull", "write(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 103 */             Integer.valueOf(startPos), Boolean.valueOf(writeSwap), writeCp, tls });
/*     */     }
/* 105 */     int pos = startPos;
/*     */     
/* 107 */     Utils.writeU8U24(this.TYPE, this.flags, buffer, pos, writeSwap);
/* 108 */     pos += 4;
/*     */     
/* 110 */     this.dc.writeI32(1, buffer, pos, writeSwap);
/* 111 */     pos += 4;
/*     */     
/* 113 */     byte[] nameBytes = this.dc.getStrBytes(this.name, writeCp);
/* 114 */     int nameLength = nameBytes.length;
/* 115 */     this.dc.writeU16(nameLength, buffer, pos, writeSwap);
/* 116 */     pos += 2;
/*     */     
/* 118 */     Utils.writeU8(22, buffer, pos, writeSwap);
/* 119 */     pos++;
/*     */     
/* 121 */     System.arraycopy(nameBytes, 0, buffer, pos, nameLength);
/* 122 */     pos += nameLength;
/*     */     
/* 124 */     int lengthStruct = this.FIXED_LENGTH + nameLength;
/* 125 */     int paddingStruct = this.dc.getPaddingLength(lengthStruct);
/* 126 */     for (int i = 0; i < paddingStruct; i++) {
/* 127 */       buffer[pos + i] = 0;
/*     */     }
/* 129 */     pos += paddingStruct;
/*     */     
/* 131 */     this.propLen = nameLength;
/*     */     
/* 133 */     if (Trace.isOn) {
/* 134 */       Trace.exit(this, "com.ibm.mq.jmqi.system.zrfp.zrfpNull", "write(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", 
/* 135 */           Integer.valueOf(pos));
/*     */     }
/* 137 */     return pos;
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
/* 154 */     if (Trace.isOn) {
/* 155 */       Trace.entry(this, "com.ibm.mq.jmqi.system.zrfp.zrfpNull", "read(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 157 */             Integer.valueOf(startPos), Boolean.valueOf(readSwap), readCp, tls });
/*     */     }
/* 159 */     int pos = startPos;
/* 160 */     int[] tmp = Utils.readU8U24(buffer, pos, readSwap);
/*     */     
/* 162 */     int type = tmp[0];
/* 163 */     if (type != this.TYPE) {
/* 164 */       JmqiException jmqiEx = new JmqiException(this.env, -1, null, 2, 2473, null);
/*     */ 
/*     */       
/* 167 */       if (Trace.isOn) {
/* 168 */         Trace.throwing(this, "com.ibm.mq.jmqi.system.zrfp.zrfpNull", "read(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", (Throwable)jmqiEx);
/*     */       }
/*     */       
/* 171 */       throw jmqiEx;
/*     */     } 
/*     */ 
/*     */     
/* 175 */     this.flags = tmp[1];
/* 176 */     pos += 4;
/*     */ 
/*     */     
/* 179 */     pos += 4;
/*     */ 
/*     */     
/* 182 */     int nameLength = this.dc.readU16(buffer, pos, readSwap);
/* 183 */     pos += 2;
/*     */ 
/*     */     
/* 186 */     pos++;
/*     */ 
/*     */     
/* 189 */     this.name = this.dc.readField(buffer, pos, nameLength, readCp, tls);
/* 190 */     pos += nameLength;
/*     */ 
/*     */     
/* 193 */     int lengthStruct = this.FIXED_LENGTH + nameLength;
/* 194 */     int paddingStruct = this.dc.getPaddingLength(lengthStruct);
/* 195 */     pos += paddingStruct;
/*     */     
/* 197 */     if (Trace.isOn) {
/* 198 */       Trace.exit(this, "com.ibm.mq.jmqi.system.zrfp.zrfpNull", "read(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", 
/* 199 */           Integer.valueOf(pos));
/*     */     }
/* 201 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setName(String name) {
/* 209 */     this.name = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   String getName() {
/* 217 */     return this.name;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\zrfp\zrfpNull.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */