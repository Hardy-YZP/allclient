/*     */ package com.ibm.mq.jmqi.system.zrfp;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
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
/*     */ class zrfPROP
/*     */   extends JmqiObject
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/zrfp/zrfPROP.java";
/*     */   protected final int TYPE;
/*     */   protected final int FIXED_LENGTH;
/*     */   protected static final int SIZEOF_TYPE = 1;
/*     */   protected static final int SIZEOF_FLAGS = 3;
/*     */   
/*     */   static {
/*  45 */     if (Trace.isOn) {
/*  46 */       Trace.data("com.ibm.mq.jmqi.system.zrfp.zrfPROP", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/zrfp/zrfPROP.java");
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
/*  64 */   protected int flags = 0;
/*  65 */   protected int propLen = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final JmqiDC dc;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   zrfPROP(JmqiEnvironment env, JmqiDC dc, int type, int fixedLength) {
/*  78 */     super(env);
/*     */     
/*  80 */     this.TYPE = type;
/*  81 */     this.FIXED_LENGTH = fixedLength;
/*     */     
/*  83 */     this.dc = dc;
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
/*     */   int write(byte[] buffer, int startPos, boolean writeSwap, JmqiCodepage writeCp, JmqiTls tls) throws JmqiException {
/* 101 */     return 0;
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
/*     */   int read(byte[] buffer, int startPos, boolean readSwap, JmqiCodepage readCp, JmqiTls tls) throws JmqiException {
/* 119 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int getPropLen() {
/* 127 */     return this.propLen;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int getFlags() {
/* 135 */     return this.flags;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setFlags(int flags) {
/* 143 */     this.flags = flags;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\zrfp\zrfPROP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */