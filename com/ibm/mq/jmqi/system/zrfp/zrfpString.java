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
/*     */ class zrfpString
/*     */   extends zrfPROP
/*     */ {
/*     */   static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/zrfp/zrfpString.java";
/*     */   private static final int SIZEOF_SUPPORT = 4;
/*     */   private static final int SIZEOF_VALUELENGTH = 4;
/*     */   private static final int SIZEOF_NAMELENGTH = 2;
/*     */   private static final int SIZEOF_COPYOPTIONS = 1;
/*     */   private String name;
/*     */   private String value;
/*     */   
/*     */   static {
/*  60 */     if (Trace.isOn) {
/*  61 */       Trace.data("com.ibm.mq.jmqi.system.zrfp.zrfpString", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/zrfp/zrfpString.java");
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
/*     */   zrfpString(JmqiEnvironment env, JmqiDC dc) {
/*  88 */     super(env, dc, 0, 15);
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
/* 106 */       Trace.entry(this, "com.ibm.mq.jmqi.system.zrfp.zrfpString", "write(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
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
/* 118 */     byte[] valueBytes = this.dc.getStrBytes(this.value, writeCp);
/* 119 */     int valueLength = valueBytes.length;
/* 120 */     this.dc.writeI32(valueLength, buffer, pos, writeSwap);
/* 121 */     pos += 4;
/*     */     
/* 123 */     byte[] nameBytes = this.dc.getStrBytes(this.name, writeCp);
/* 124 */     int nameLength = nameBytes.length;
/* 125 */     this.dc.writeU16(nameLength, buffer, pos, writeSwap);
/* 126 */     pos += 2;
/*     */     
/* 128 */     Utils.writeU8(22, buffer, pos, writeSwap);
/* 129 */     pos++;
/*     */     
/* 131 */     System.arraycopy(nameBytes, 0, buffer, pos, nameLength);
/* 132 */     pos += nameLength;
/*     */     
/* 134 */     System.arraycopy(valueBytes, 0, buffer, pos, valueLength);
/* 135 */     pos += valueLength;
/*     */     
/* 137 */     buffer[pos] = 0;
/* 138 */     pos++;
/*     */     
/* 140 */     int lengthStruct = this.FIXED_LENGTH + nameLength + valueLength + 1;
/* 141 */     int paddingStruct = this.dc.getPaddingLength(lengthStruct);
/* 142 */     for (int i = 0; i < paddingStruct; i++) {
/* 143 */       buffer[pos + i] = 0;
/*     */     }
/* 145 */     pos += paddingStruct;
/*     */     
/* 147 */     this.propLen = nameLength + valueLength;
/*     */     
/* 149 */     if (Trace.isOn) {
/* 150 */       Trace.exit(this, "com.ibm.mq.jmqi.system.zrfp.zrfpString", "write(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", 
/* 151 */           Integer.valueOf(pos));
/*     */     }
/* 153 */     return pos;
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
/* 170 */     if (Trace.isOn) {
/* 171 */       Trace.entry(this, "com.ibm.mq.jmqi.system.zrfp.zrfpString", "read(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 173 */             Integer.valueOf(startPos), Boolean.valueOf(readSwap), readCp, tls });
/*     */     }
/* 175 */     int pos = startPos;
/* 176 */     int[] tmp = Utils.readU8U24(buffer, pos, readSwap);
/*     */     
/* 178 */     int type = tmp[0];
/* 179 */     this.flags = tmp[1];
/* 180 */     if (type != this.TYPE)
/*     */     {
/*     */       
/* 183 */       if (!isAsStringFlagEnabled()) {
/* 184 */         JmqiException jmqiEx = new JmqiException(this.env, -1, null, 2, 2473, null);
/*     */ 
/*     */         
/* 187 */         if (Trace.isOn) {
/* 188 */           Trace.throwing(this, "com.ibm.mq.jmqi.system.zrfp.zrfpString", "read(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", (Throwable)jmqiEx);
/*     */         }
/*     */         
/* 191 */         throw jmqiEx;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 196 */     pos += 4;
/*     */ 
/*     */     
/* 199 */     pos += 4;
/*     */ 
/*     */     
/* 202 */     int valueLength = this.dc.readI32(buffer, pos, readSwap);
/* 203 */     pos += 4;
/*     */ 
/*     */     
/* 206 */     int nameLength = this.dc.readU16(buffer, pos, readSwap);
/* 207 */     pos += 2;
/*     */ 
/*     */     
/* 210 */     pos++;
/*     */ 
/*     */     
/* 213 */     this.name = this.dc.readField(buffer, pos, nameLength, readCp, tls);
/* 214 */     pos += nameLength;
/*     */ 
/*     */     
/* 217 */     this.value = this.dc.readField(buffer, pos, valueLength, readCp, tls);
/* 218 */     pos += valueLength;
/*     */ 
/*     */     
/* 221 */     pos++;
/*     */ 
/*     */     
/* 224 */     int lengthStruct = this.FIXED_LENGTH + nameLength + valueLength + 1;
/* 225 */     int paddingStruct = this.dc.getPaddingLength(lengthStruct);
/* 226 */     pos += paddingStruct;
/*     */     
/* 228 */     if (Trace.isOn) {
/* 229 */       Trace.exit(this, "com.ibm.mq.jmqi.system.zrfp.zrfpString", "read(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", 
/* 230 */           Integer.valueOf(pos));
/*     */     }
/* 232 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setNameValue(String name, String value) {
/* 241 */     this.name = name;
/* 242 */     this.value = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   String getName() {
/* 250 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   String getValue() {
/* 258 */     return this.value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isAsStringFlagEnabled() {
/* 269 */     boolean asString = ((this.flags & 0x1) == 1);
/* 270 */     return asString;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\zrfp\zrfpString.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */