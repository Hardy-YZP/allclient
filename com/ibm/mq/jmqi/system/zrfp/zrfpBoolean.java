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
/*     */ class zrfpBoolean
/*     */   extends zrfPROP
/*     */ {
/*     */   static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/zrfp/zrfpBoolean.java";
/*     */   private static final int SIZEOF_SUPPORT = 4;
/*     */   private static final int SIZEOF_VALUE = 4;
/*     */   private static final int SIZEOF_NAMELENGTH = 2;
/*     */   private static final int SIZEOF_COPYOPTIONS = 1;
/*     */   private String name;
/*     */   private boolean value;
/*     */   
/*     */   static {
/*  65 */     if (Trace.isOn) {
/*  66 */       Trace.data("com.ibm.mq.jmqi.system.zrfp.zrfpBoolean", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/zrfp/zrfpBoolean.java");
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
/*     */   zrfpBoolean(JmqiEnvironment env, JmqiDC dc) {
/*  93 */     super(env, dc, 1, 15);
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
/* 111 */       Trace.entry(this, "com.ibm.mq.jmqi.system.zrfp.zrfpBoolean", "write(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
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
/* 123 */     int valueInt32 = this.value ? 1 : 0;
/* 124 */     this.dc.writeI32(valueInt32, buffer, pos, writeSwap);
/* 125 */     pos += 4;
/*     */     
/* 127 */     byte[] nameBytes = this.dc.getStrBytes(this.name, writeCp);
/* 128 */     int nameLength = nameBytes.length;
/* 129 */     this.dc.writeU16(nameLength, buffer, pos, writeSwap);
/* 130 */     pos += 2;
/*     */     
/* 132 */     Utils.writeU8(22, buffer, pos, writeSwap);
/* 133 */     pos++;
/*     */     
/* 135 */     System.arraycopy(nameBytes, 0, buffer, pos, nameLength);
/* 136 */     pos += nameLength;
/*     */     
/* 138 */     int lengthStruct = this.FIXED_LENGTH + nameLength;
/* 139 */     int paddingStruct = this.dc.getPaddingLength(lengthStruct);
/* 140 */     for (int i = 0; i < paddingStruct; i++) {
/* 141 */       buffer[pos + i] = 0;
/*     */     }
/* 143 */     pos += paddingStruct;
/*     */     
/* 145 */     this.propLen = nameLength + 4;
/*     */     
/* 147 */     if (Trace.isOn) {
/* 148 */       Trace.exit(this, "com.ibm.mq.jmqi.system.zrfp.zrfpBoolean", "write(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", 
/* 149 */           Integer.valueOf(pos));
/*     */     }
/* 151 */     return pos;
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
/* 168 */     if (Trace.isOn) {
/* 169 */       Trace.entry(this, "com.ibm.mq.jmqi.system.zrfp.zrfpBoolean", "read(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 171 */             Integer.valueOf(startPos), Boolean.valueOf(readSwap), readCp, tls });
/*     */     }
/* 173 */     int pos = startPos;
/* 174 */     int[] tmp = Utils.readU8U24(buffer, pos, readSwap);
/*     */     
/* 176 */     int type = tmp[0];
/* 177 */     if (type != this.TYPE) {
/* 178 */       JmqiException jmqiEx = new JmqiException(this.env, -1, null, 2, 2473, null);
/*     */ 
/*     */       
/* 181 */       if (Trace.isOn) {
/* 182 */         Trace.throwing(this, "com.ibm.mq.jmqi.system.zrfp.zrfpBoolean", "read(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", (Throwable)jmqiEx);
/*     */       }
/*     */       
/* 185 */       throw jmqiEx;
/*     */     } 
/*     */ 
/*     */     
/* 189 */     this.flags = tmp[1];
/* 190 */     pos += 4;
/*     */ 
/*     */     
/* 193 */     pos += 4;
/*     */ 
/*     */     
/* 196 */     int valueInt32 = this.dc.readI32(buffer, pos, readSwap);
/* 197 */     this.value = !(valueInt32 == 0);
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
/* 212 */     int lengthStruct = this.FIXED_LENGTH + nameLength;
/* 213 */     int paddingStruct = this.dc.getPaddingLength(lengthStruct);
/* 214 */     pos += paddingStruct;
/*     */     
/* 216 */     if (Trace.isOn) {
/* 217 */       Trace.exit(this, "com.ibm.mq.jmqi.system.zrfp.zrfpBoolean", "read(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", 
/* 218 */           Integer.valueOf(pos));
/*     */     }
/* 220 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setNameValue(String name, boolean value) {
/* 229 */     this.name = name;
/* 230 */     this.value = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   String getName() {
/* 238 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean getValue() {
/* 246 */     return this.value;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\zrfp\zrfpBoolean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */