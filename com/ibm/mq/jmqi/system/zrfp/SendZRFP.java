/*     */ package com.ibm.mq.jmqi.system.zrfp;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.mq.jmqi.system.JmqiTls;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.nio.ByteOrder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SendZRFP
/*     */   extends ZRFP
/*     */ {
/*     */   static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/zrfp/SendZRFP.java";
/*     */   private final boolean writeSwap;
/*     */   private final JmqiCodepage writeCp;
/*     */   private long writeStrucLength;
/*     */   private long writePropLength;
/*     */   private int writeCompatibles;
/*     */   
/*     */   static {
/*  48 */     if (Trace.isOn) {
/*  49 */       Trace.data("com.ibm.mq.jmqi.system.zrfp.SendZRFP", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/zrfp/SendZRFP.java");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SendZRFP(JmqiEnvironment env, JmqiTls tls) throws JmqiException {
/*  86 */     this(env, tls, 1208);
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
/*     */   public SendZRFP(JmqiEnvironment env, JmqiTls tls, int writeCcsid) throws JmqiException {
/*  98 */     super(env, tls);
/*  99 */     if (Trace.isOn) {
/* 100 */       Trace.entry(this, "com.ibm.mq.jmqi.system.zrfp.SendZRFP", "<init>(JmqiEnvironment,JmqiTls,int)", new Object[] { env, tls, 
/* 101 */             Integer.valueOf(writeCcsid) });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 106 */       this.writeCp = JmqiCodepage.getJmqiCodepage(env, writeCcsid);
/*     */       
/* 108 */       if (this.writeCp == null)
/*     */       {
/* 110 */         UnsupportedEncodingException traceRet2 = new UnsupportedEncodingException(Integer.toString(writeCcsid));
/* 111 */         if (Trace.isOn) {
/* 112 */           Trace.throwing(this, "com.ibm.mq.jmqi.system.zrfp.SendZRFP", "<init>(JmqiEnvironment,JmqiTls,int)", traceRet2, 1);
/*     */         }
/*     */         
/* 115 */         throw traceRet2;
/*     */       }
/*     */     
/*     */     }
/* 119 */     catch (UnsupportedEncodingException e) {
/* 120 */       if (Trace.isOn) {
/* 121 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.system.zrfp.SendZRFP", "<init>(JmqiEnvironment,JmqiTls,int)", e);
/*     */       }
/*     */       
/* 124 */       JmqiException traceRet1 = new JmqiException(env, 6047, new String[] { String.valueOf(writeCcsid), null, null, null, "???" }, 2, 2195, e);
/*     */       
/* 126 */       if (Trace.isOn) {
/* 127 */         Trace.throwing(this, "com.ibm.mq.jmqi.system.zrfp.SendZRFP", "<init>(JmqiEnvironment,JmqiTls,int)", (Throwable)traceRet1, 2);
/*     */       }
/*     */       
/* 130 */       throw traceRet1;
/*     */     } 
/* 132 */     assert this.writeCp != null : "writeCp is unexpectedly null";
/*     */ 
/*     */     
/* 135 */     if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
/* 136 */       this.writeSwap = true;
/*     */     } else {
/*     */       
/* 139 */       this.writeSwap = false;
/*     */     } 
/*     */     
/* 142 */     if (Trace.isOn) {
/* 143 */       Trace.exit(this, "com.ibm.mq.jmqi.system.zrfp.SendZRFP", "<init>(JmqiEnvironment,JmqiTls,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void resetWriteCounters() {
/* 154 */     this.writeStrucLength = 0L;
/* 155 */     this.writePropLength = 0L;
/* 156 */     this.writeCompatibles = 0;
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
/*     */   public int writeToBuffer(byte[] buffer, int startOffset, Triplet[] triplets) throws JmqiException {
/* 169 */     if (Trace.isOn) {
/* 170 */       Trace.entry(this, "com.ibm.mq.jmqi.system.zrfp.SendZRFP", "writeToBuffer(byte [ ],int,Triplet [ ])", new Object[] { buffer, 
/*     */             
/* 172 */             Integer.valueOf(startOffset), triplets });
/*     */     }
/* 174 */     assert buffer != null : "buffer is unexpectedly null";
/* 175 */     assert startOffset >= 0 : "startOffset is negative";
/*     */     
/* 177 */     resetWriteCounters();
/* 178 */     int pos = startOffset;
/*     */     
/* 180 */     pos += 24;
/*     */     
/* 182 */     int length = triplets.length;
/*     */     
/* 184 */     for (int i = 0; i < length; i++) {
/*     */       
/* 186 */       Triplet thisTriplet = triplets[i];
/* 187 */       if (thisTriplet != null && thisTriplet.size() > 0) {
/* 188 */         pos = writeTriplet(buffer, pos, thisTriplet);
/*     */       }
/*     */     } 
/*     */     
/* 192 */     this.writeStrucLength += 24L;
/*     */     
/* 194 */     this.fixedPart.setCompatibles(this.writeCompatibles);
/* 195 */     this.fixedPart.setStrucLength(this.writeStrucLength);
/* 196 */     this.fixedPart.setPropsLength(this.writePropLength);
/* 197 */     this.fixedPart.write(buffer, startOffset, this.writeSwap);
/*     */     
/* 199 */     if (Trace.isOn) {
/* 200 */       Trace.exit(this, "com.ibm.mq.jmqi.system.zrfp.SendZRFP", "writeToBuffer(byte [ ],int,Triplet [ ])", 
/* 201 */           Integer.valueOf(pos));
/*     */     }
/* 203 */     return pos;
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
/*     */   public int getStructLength() {
/* 217 */     int traceRet1 = (int)this.writeStrucLength;
/*     */     
/* 219 */     if (Trace.isOn) {
/* 220 */       Trace.data(this, "com.ibm.mq.jmqi.system.zrfp.SendZRFP", "getStructLength()", "getter", 
/* 221 */           Integer.valueOf(traceRet1));
/*     */     }
/* 223 */     return traceRet1;
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
/*     */   private int writeTriplet(byte[] buffer, int offset, Triplet triplet) throws JmqiException {
/* 242 */     if (Trace.isOn) {
/* 243 */       Trace.entry(this, "com.ibm.mq.jmqi.system.zrfp.SendZRFP", "writeTriplet(byte [ ],int,Triplet)", new Object[] { buffer, 
/* 244 */             Integer.valueOf(offset), triplet });
/*     */     }
/*     */     
/* 247 */     String folderName = triplet.getLabel();
/*     */     
/* 249 */     int folderStart = offset;
/* 250 */     int folderLength = Utils.computeParentStructLength(folderName, this.writeCp, this.dc);
/* 251 */     int folderEnd = folderStart + folderLength;
/* 252 */     int lastPos = folderEnd;
/* 253 */     int groupLength = folderLength;
/*     */ 
/*     */ 
/*     */     
/* 257 */     zrfPROP node = null;
/* 258 */     int size = triplet.size();
/* 259 */     for (int i = 0; i < size; i++) {
/* 260 */       String stringValue; int int32Value; boolean boolValue; byte[] byteArrayValue; float float32Value; double float64Value; byte int8Value; short int16Value; long int64Value; JmqiException jmqiEx; String propName = triplet.getKey(i);
/* 261 */       int type = triplet.getType(i);
/* 262 */       int start = lastPos;
/* 263 */       int end = start;
/* 264 */       switch (type) {
/*     */         
/*     */         case 0:
/* 267 */           node = this.stringNode;
/* 268 */           stringValue = (String)triplet.getValue(i);
/* 269 */           this.stringNode.setNameValue(propName, stringValue);
/*     */           break;
/*     */         case 5:
/* 272 */           node = this.int32Node;
/* 273 */           int32Value = ((Integer)triplet.getValue(i)).intValue();
/* 274 */           this.int32Node.setNameValue(propName, int32Value);
/*     */           break;
/*     */         case 1:
/* 277 */           node = this.booleanNode;
/* 278 */           boolValue = ((Boolean)triplet.getValue(i)).booleanValue();
/* 279 */           this.booleanNode.setNameValue(propName, boolValue);
/*     */           break;
/*     */         case 2:
/* 282 */           node = this.byteArrayNode;
/* 283 */           byteArrayValue = (byte[])triplet.getValue(i);
/* 284 */           this.byteArrayNode.setNameValue(propName, byteArrayValue);
/*     */           break;
/*     */         case 7:
/* 287 */           node = this.float32Node;
/* 288 */           float32Value = ((Float)triplet.getValue(i)).floatValue();
/* 289 */           this.float32Node.setNameValue(propName, float32Value);
/*     */           break;
/*     */         case 8:
/* 292 */           node = this.float64Node;
/* 293 */           float64Value = ((Double)triplet.getValue(i)).doubleValue();
/* 294 */           this.float64Node.setNameValue(propName, float64Value);
/*     */           break;
/*     */         case 3:
/* 297 */           node = this.int8Node;
/* 298 */           int8Value = ((Byte)triplet.getValue(i)).byteValue();
/* 299 */           this.int8Node.setNameValue(propName, int8Value);
/*     */           break;
/*     */         case 4:
/* 302 */           node = this.int16Node;
/* 303 */           int16Value = ((Short)triplet.getValue(i)).shortValue();
/* 304 */           this.int16Node.setNameValue(propName, int16Value);
/*     */           break;
/*     */         case 6:
/* 307 */           node = this.int64Node;
/* 308 */           int64Value = ((Long)triplet.getValue(i)).longValue();
/* 309 */           this.int64Node.setNameValue(propName, int64Value);
/*     */           break;
/*     */         case 9:
/* 312 */           node = this.nullNode;
/* 313 */           this.nullNode.setName(propName);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 11:
/*     */           break;
/*     */         
/*     */         default:
/* 321 */           jmqiEx = new JmqiException(this.env, -1, null, 2, 2473, null);
/*     */           
/* 323 */           if (Trace.isOn) {
/* 324 */             Trace.throwing(this, "com.ibm.mq.jmqi.system.zrfp.SendZRFP", "writeTriplet(byte [ ],int,Triplet)", (Throwable)jmqiEx);
/*     */           }
/*     */           
/* 327 */           throw jmqiEx;
/*     */       } 
/*     */       
/* 330 */       assert node != null : "zrfPROP node is unexpectedly null";
/* 331 */       end = node.write(buffer, start, this.writeSwap, this.writeCp, this.tls);
/* 332 */       assert end > start : "Invalid end";
/* 333 */       lastPos = end;
/* 334 */       int length = end - start;
/* 335 */       groupLength += length;
/* 336 */       this.writePropLength += node.getPropLen();
/*     */     } 
/*     */ 
/*     */     
/* 340 */     this.writeStrucLength += groupLength;
/* 341 */     this.writeCompatibles++;
/*     */ 
/*     */     
/* 344 */     this.parentNode.setName(folderName);
/* 345 */     this.parentNode.setGroupLength(groupLength);
/* 346 */     this.parentNode.setFlags(544);
/* 347 */     this.parentNode.write(buffer, folderStart, this.writeSwap, this.writeCp, this.tls);
/*     */     
/* 349 */     if (Trace.isOn) {
/* 350 */       Trace.exit(this, "com.ibm.mq.jmqi.system.zrfp.SendZRFP", "writeTriplet(byte [ ],int,Triplet)", 
/* 351 */           Integer.valueOf(lastPos));
/*     */     }
/* 353 */     return lastPos;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\zrfp\SendZRFP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */