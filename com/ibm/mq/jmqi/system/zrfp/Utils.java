/*     */ package com.ibm.mq.jmqi.system.zrfp;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
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
/*     */ public abstract class Utils
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/zrfp/Utils.java";
/*     */   
/*     */   static {
/*  35 */     if (Trace.isOn) {
/*  36 */       Trace.data("com.ibm.mq.jmqi.system.zrfp.Utils", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/zrfp/Utils.java");
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
/*     */   public static void writeU8(int u8, byte[] buffer, int pos, boolean swap) {
/*  56 */     buffer[pos] = (byte)(u8 & 0xFF);
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
/*     */   public static int readU8(byte[] buffer, int pos, boolean swap) {
/*  69 */     return buffer[pos] & 0xFF;
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
/*     */   public static void writeI16(short i16, byte[] buffer, int pos, boolean swap) {
/*  82 */     if (swap) {
/*  83 */       buffer[pos] = (byte)(i16 & 0xFF);
/*  84 */       buffer[pos + 1] = (byte)(i16 >> 8 & 0xFF);
/*     */     } else {
/*     */       
/*  87 */       buffer[pos + 1] = (byte)(i16 & 0xFF);
/*  88 */       buffer[pos] = (byte)(i16 >> 8 & 0xFF);
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
/*     */   public static short readI16(byte[] buffer, int pos, boolean swap) {
/* 102 */     int i16 = swap ? ((buffer[pos + 1] & 0xFF) << 8 | buffer[pos] & 0xFF) : ((buffer[pos] & 0xFF) << 8 | buffer[pos + 1] & 0xFF);
/*     */ 
/*     */ 
/*     */     
/* 106 */     return (short)i16;
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
/*     */   public static void writeU8U24(int u8, int u24, byte[] buffer, int pos, boolean swap) {
/* 122 */     buffer[pos] = (byte)(u8 & 0xFF);
/* 123 */     if (swap) {
/* 124 */       buffer[pos + 1] = (byte)(u24 & 0xFF);
/* 125 */       buffer[pos + 2] = (byte)(u24 >> 8 & 0xFF);
/* 126 */       buffer[pos + 3] = (byte)(u24 >> 16 & 0xFF);
/*     */     } else {
/*     */       
/* 129 */       buffer[pos + 3] = (byte)(u24 & 0xFF);
/* 130 */       buffer[pos + 2] = (byte)(u24 >> 8 & 0xFF);
/* 131 */       buffer[pos + 1] = (byte)(u24 >> 16 & 0xFF);
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
/*     */   public static int[] readU8U24(byte[] buffer, int pos, boolean swap) {
/* 146 */     int[] result = new int[2];
/* 147 */     int u8 = buffer[pos] & 0xFF;
/* 148 */     int u24 = -1;
/* 149 */     if (swap) {
/* 150 */       u24 = (buffer[pos + 3] & 0xFF) << 16 | (buffer[pos + 2] & 0xFF) << 8 | buffer[pos + 1] & 0xFF;
/*     */     } else {
/*     */       
/* 153 */       u24 = (buffer[pos + 1] & 0xFF) << 16 | (buffer[pos + 2] & 0xFF) << 8 | buffer[pos + 3] & 0xFF;
/*     */     } 
/* 155 */     result[0] = u8;
/* 156 */     result[1] = u24;
/* 157 */     return result;
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
/*     */   public static void writeU32(long u32, byte[] buffer, int pos, boolean swap) {
/* 171 */     if (swap) {
/* 172 */       buffer[pos] = (byte)(int)(u32 & 0xFFL);
/* 173 */       buffer[pos + 1] = (byte)(int)(u32 >> 8L & 0xFFL);
/* 174 */       buffer[pos + 2] = (byte)(int)(u32 >> 16L & 0xFFL);
/* 175 */       buffer[pos + 3] = (byte)(int)(u32 >> 24L & 0xFFL);
/*     */     } else {
/*     */       
/* 178 */       buffer[pos + 3] = (byte)(int)(u32 & 0xFFL);
/* 179 */       buffer[pos + 2] = (byte)(int)(u32 >> 8L & 0xFFL);
/* 180 */       buffer[pos + 1] = (byte)(int)(u32 >> 16L & 0xFFL);
/* 181 */       buffer[pos] = (byte)(int)(u32 >> 24L & 0xFFL);
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
/*     */   public static long readU32(byte[] buffer, int pos, boolean swap) {
/* 196 */     long u32 = swap ? ((buffer[pos + 3] & 0xFF) << 24 | (buffer[pos + 2] & 0xFF) << 16 | (buffer[pos + 1] & 0xFF) << 8 | buffer[pos] & 0xFF) : ((buffer[pos] & 0xFF) << 24 | (buffer[pos + 1] & 0xFF) << 16 | (buffer[pos + 2] & 0xFF) << 8 | buffer[pos + 3] & 0xFF);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 202 */     return u32;
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
/*     */   public static int computeParentStructLength(String name, JmqiCodepage writeCp, JmqiDC dc) throws JmqiException {
/* 219 */     if (writeCp.getCCSID() == 1208) {
/* 220 */       int i = name.length();
/*     */       
/* 222 */       if (i == 3 || i == 4 || i == 5)
/*     */       {
/*     */ 
/*     */         
/* 226 */         return 16;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 231 */     byte[] bytes = dc.getStrBytes(name, writeCp);
/* 232 */     int length = bytes.length;
/* 233 */     int lengthStruct = 10 + length;
/* 234 */     int paddingStruct = dc.getPaddingLength(lengthStruct);
/* 235 */     return lengthStruct + paddingStruct;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Integer getTypeObject(int type) {
/* 246 */     if (Trace.isOn)
/* 247 */       Trace.entry("com.ibm.mq.jmqi.system.zrfp.Utils", "getTypeObject(int)", new Object[] {
/* 248 */             Integer.valueOf(type)
/*     */           }); 
/* 250 */     Integer result = null;
/* 251 */     switch (type) {
/*     */       case 0:
/* 253 */         result = Constants.TYPE_STRING;
/*     */         break;
/*     */       case 1:
/* 256 */         result = Constants.TYPE_BOOLEAN;
/*     */         break;
/*     */       case 2:
/* 259 */         result = Constants.TYPE_BYTE_STRING;
/*     */         break;
/*     */       case 3:
/* 262 */         result = Constants.TYPE_INT8;
/*     */         break;
/*     */       case 4:
/* 265 */         result = Constants.TYPE_INT16;
/*     */         break;
/*     */       case 5:
/* 268 */         result = Constants.TYPE_INT32;
/*     */         break;
/*     */       case 6:
/* 271 */         result = Constants.TYPE_INT64;
/*     */         break;
/*     */       case 7:
/* 274 */         result = Constants.TYPE_FLOAT32;
/*     */         break;
/*     */       case 8:
/* 277 */         result = Constants.TYPE_FLOAT64;
/*     */         break;
/*     */       case 9:
/* 280 */         result = Constants.TYPE_NULL;
/*     */         break;
/*     */       case 10:
/* 283 */         result = Constants.TYPE_PARENT;
/*     */         break;
/*     */       case 11:
/* 286 */         result = Constants.TYPE_UNKNOWN;
/*     */         break;
/*     */       case 12:
/* 289 */         result = Constants.TYPE_LAST;
/*     */         break;
/*     */       case 255:
/* 292 */         result = Constants.TYPE_MAX;
/*     */         break;
/*     */       default:
/* 295 */         result = null; break;
/*     */     } 
/* 297 */     if (Trace.isOn) {
/* 298 */       Trace.exit("com.ibm.mq.jmqi.system.zrfp.Utils", "getTypeObject(int)", result);
/*     */     }
/* 300 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\zrfp\Utils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */