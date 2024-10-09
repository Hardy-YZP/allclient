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
/*     */ class zrfpUnknown
/*     */   extends zrfPROP
/*     */ {
/*     */   static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/zrfp/zrfpUnknown.java";
/*     */   private static final int SIZEOF_SUPPORT = 4;
/*     */   private static final int SIZEOF_TYPESTRING = 8;
/*     */   private static final int SIZEOF_VALUELENGTH = 4;
/*     */   private static final int SIZEOF_NAMELENGTH = 2;
/*     */   private static final int SIZEOF_COPYOPTIONS = 1;
/*     */   
/*     */   static {
/*  66 */     if (Trace.isOn) {
/*  67 */       Trace.data("com.ibm.mq.jmqi.system.zrfp.zrfpUnknown", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/zrfp/zrfpUnknown.java");
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
/*     */   zrfpUnknown(JmqiEnvironment env, JmqiDC dc) {
/*  93 */     super(env, dc, 11, 23);
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
/* 111 */       Trace.entry(this, "com.ibm.mq.jmqi.system.zrfp.zrfpUnknown", "write(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 113 */             Integer.valueOf(startPos), Boolean.valueOf(writeSwap), writeCp, tls });
/*     */     }
/*     */     
/* 116 */     JmqiException jmqiEx = new JmqiException(this.env, -1, null, 2, 2298, null);
/*     */ 
/*     */ 
/*     */     
/* 120 */     if (Trace.isOn) {
/* 121 */       Trace.throwing(this, "com.ibm.mq.jmqi.system.zrfp.zrfpUnknown", "write(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", (Throwable)jmqiEx);
/*     */     }
/*     */     
/* 124 */     throw jmqiEx;
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
/* 141 */     if (Trace.isOn) {
/* 142 */       Trace.entry(this, "com.ibm.mq.jmqi.system.zrfp.zrfpUnknown", "read(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 144 */             Integer.valueOf(startPos), Boolean.valueOf(readSwap), readCp, tls });
/*     */     }
/* 146 */     int pos = startPos;
/* 147 */     int[] tmp = Utils.readU8U24(buffer, pos, readSwap);
/*     */     
/* 149 */     int type = tmp[0];
/* 150 */     if (type != 11) {
/* 151 */       JmqiException jmqiEx = new JmqiException(this.env, -1, null, 2, 2473, null);
/*     */ 
/*     */ 
/*     */       
/* 155 */       if (Trace.isOn) {
/* 156 */         Trace.throwing(this, "com.ibm.mq.jmqi.system.zrfp.zrfpUnknown", "read(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", (Throwable)jmqiEx);
/*     */       }
/*     */       
/* 159 */       throw jmqiEx;
/*     */     } 
/*     */ 
/*     */     
/* 163 */     pos += 4;
/*     */ 
/*     */     
/* 166 */     pos += 4;
/*     */ 
/*     */     
/* 169 */     pos += 8;
/*     */ 
/*     */     
/* 172 */     int valueLength = this.dc.readI32(buffer, pos, readSwap);
/* 173 */     pos += 4;
/*     */ 
/*     */     
/* 176 */     int nameLength = this.dc.readU16(buffer, pos, readSwap);
/* 177 */     pos += 2;
/*     */ 
/*     */     
/* 180 */     pos++;
/*     */ 
/*     */     
/* 183 */     pos += nameLength;
/*     */ 
/*     */     
/* 186 */     pos += valueLength;
/*     */ 
/*     */     
/* 189 */     int lengthStruct = 23 + nameLength + valueLength;
/* 190 */     int paddingStruct = this.dc.getPaddingLength(lengthStruct);
/* 191 */     pos += paddingStruct;
/*     */     
/* 193 */     if (Trace.isOn) {
/* 194 */       Trace.exit(this, "com.ibm.mq.jmqi.system.zrfp.zrfpUnknown", "read(byte [ ],int,boolean,JmqiCodepage,JmqiTls)", 
/* 195 */           Integer.valueOf(pos));
/*     */     }
/* 197 */     return pos;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\zrfp\zrfpUnknown.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */