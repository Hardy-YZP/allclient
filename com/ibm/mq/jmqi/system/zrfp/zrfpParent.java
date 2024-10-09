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
/*     */ class zrfpParent
/*     */   extends zrfPROP
/*     */ {
/*     */   static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/zrfp/zrfpParent.java";
/*     */   private static final int SIZEOF_GROUPLENGTH = 4;
/*     */   private static final int SIZEOF_NAMELENGTH = 2;
/*     */   private long groupLength;
/*     */   private String name;
/*     */   
/*     */   static {
/*  58 */     if (Trace.isOn) {
/*  59 */       Trace.data("com.ibm.mq.jmqi.system.zrfp.zrfpParent", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/zrfp/zrfpParent.java");
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
/*     */   zrfpParent(JmqiEnvironment env, JmqiDC dc) {
/*  84 */     super(env, dc, 10, 10);
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
/* 101 */     if (Trace.isOn) {
/* 102 */       Trace.entry(this, "com.ibm.mq.jmqi.system.zrfp.zrfpParent", "write(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 104 */             Integer.valueOf(startPos), Boolean.valueOf(writeSwap), writeCp, tls });
/*     */     }
/* 106 */     int pos = startPos;
/*     */     
/* 108 */     Utils.writeU8U24(this.TYPE, this.flags, buffer, pos, writeSwap);
/* 109 */     pos += 4;
/*     */     
/* 111 */     Utils.writeU32(this.groupLength, buffer, pos, writeSwap);
/* 112 */     pos += 4;
/*     */     
/* 114 */     byte[] nameBytes = this.dc.getStrBytes(this.name, writeCp);
/* 115 */     int nameLength = nameBytes.length;
/* 116 */     this.dc.writeU16(nameLength, buffer, pos, writeSwap);
/* 117 */     pos += 2;
/*     */     
/* 119 */     System.arraycopy(nameBytes, 0, buffer, pos, nameLength);
/* 120 */     pos += nameLength;
/*     */     
/* 122 */     int lengthStruct = this.FIXED_LENGTH + nameLength;
/* 123 */     int paddingStruct = this.dc.getPaddingLength(lengthStruct);
/* 124 */     for (int i = 0; i < paddingStruct; i++) {
/* 125 */       buffer[pos + i] = 0;
/*     */     }
/* 127 */     pos += paddingStruct;
/*     */     
/* 129 */     if (Trace.isOn) {
/* 130 */       Trace.exit(this, "com.ibm.mq.jmqi.system.zrfp.zrfpParent", "write(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", 
/* 131 */           Integer.valueOf(pos));
/*     */     }
/* 133 */     return pos;
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
/* 150 */     if (Trace.isOn) {
/* 151 */       Trace.entry(this, "com.ibm.mq.jmqi.system.zrfp.zrfpParent", "read(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 153 */             Integer.valueOf(startPos), Boolean.valueOf(readSwap), readCp, tls });
/*     */     }
/* 155 */     int pos = startPos;
/* 156 */     int[] tmp = Utils.readU8U24(buffer, pos, readSwap);
/*     */     
/* 158 */     int type = tmp[0];
/* 159 */     if (type != this.TYPE) {
/* 160 */       JmqiException jmqiEx = new JmqiException(this.env, -1, null, 2, 2473, null);
/*     */ 
/*     */       
/* 163 */       if (Trace.isOn) {
/* 164 */         Trace.throwing(this, "com.ibm.mq.jmqi.system.zrfp.zrfpParent", "read(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", (Throwable)jmqiEx);
/*     */       }
/*     */       
/* 167 */       throw jmqiEx;
/*     */     } 
/*     */ 
/*     */     
/* 171 */     this.flags = tmp[1];
/* 172 */     pos += 4;
/*     */ 
/*     */     
/* 175 */     this.groupLength = Utils.readU32(buffer, pos, readSwap);
/* 176 */     pos += 4;
/*     */ 
/*     */     
/* 179 */     int nameLength = this.dc.readU16(buffer, pos, readSwap);
/* 180 */     pos += 2;
/*     */ 
/*     */     
/* 183 */     this.name = this.dc.readField(buffer, pos, nameLength, readCp, tls);
/* 184 */     pos += nameLength;
/*     */ 
/*     */     
/* 187 */     int lengthStruct = this.FIXED_LENGTH + nameLength;
/* 188 */     int paddingStruct = this.dc.getPaddingLength(lengthStruct);
/* 189 */     pos += paddingStruct;
/*     */     
/* 191 */     if (Trace.isOn) {
/* 192 */       Trace.exit(this, "com.ibm.mq.jmqi.system.zrfp.zrfpParent", "read(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", 
/* 193 */           Integer.valueOf(pos));
/*     */     }
/* 195 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   long getGroupLength() {
/* 203 */     return this.groupLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setGroupLength(long groupLength) {
/* 211 */     this.groupLength = groupLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   String getName() {
/* 219 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setName(String name) {
/* 227 */     this.name = name;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\zrfp\zrfpParent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */