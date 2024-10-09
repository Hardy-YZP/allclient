/*     */ package com.ibm.mq.jmqi.system.zrfp;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*     */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ZRFP
/*     */   extends JmqiObject
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/zrfp/ZRFP.java";
/*     */   protected final JmqiTls tls;
/*     */   protected final JmqiDC dc;
/*     */   protected final zrfpFixed fixedPart;
/*     */   protected final zrfpBoolean booleanNode;
/*     */   protected final zrfpByteArray byteArrayNode;
/*     */   protected final zrfpFloat32 float32Node;
/*     */   protected final zrfpFloat64 float64Node;
/*     */   protected final zrfpInt8 int8Node;
/*     */   protected final zrfpInt16 int16Node;
/*     */   protected final zrfpInt32 int32Node;
/*     */   protected final zrfpInt64 int64Node;
/*     */   protected final zrfpNull nullNode;
/*     */   protected final zrfpParent parentNode;
/*     */   protected final zrfpString stringNode;
/*     */   
/*     */   static {
/* 237 */     if (Trace.isOn) {
/* 238 */       Trace.data("com.ibm.mq.jmqi.system.zrfp.ZRFP", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/zrfp/ZRFP.java");
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
/*     */ 
/*     */   
/*     */   public ZRFP(JmqiEnvironment env, JmqiTls tls) {
/* 277 */     super(env);
/* 278 */     if (Trace.isOn) {
/* 279 */       Trace.entry(this, "com.ibm.mq.jmqi.system.zrfp.ZRFP", "<init>(JmqiEnvironment,JmqiTls)", new Object[] { env, tls });
/*     */     }
/*     */ 
/*     */     
/* 283 */     JmqiSystemEnvironment sysenv = (JmqiSystemEnvironment)env;
/*     */     
/* 285 */     this.dc = sysenv.getDC();
/*     */     
/* 287 */     this.tls = tls;
/*     */ 
/*     */     
/* 290 */     this.fixedPart = new zrfpFixed(env, this.dc);
/* 291 */     this.booleanNode = new zrfpBoolean(env, this.dc);
/* 292 */     this.byteArrayNode = new zrfpByteArray(env, this.dc);
/* 293 */     this.float32Node = new zrfpFloat32(env, this.dc);
/* 294 */     this.float64Node = new zrfpFloat64(env, this.dc);
/* 295 */     this.int8Node = new zrfpInt8(env, this.dc);
/* 296 */     this.int16Node = new zrfpInt16(env, this.dc);
/* 297 */     this.int32Node = new zrfpInt32(env, this.dc);
/* 298 */     this.int64Node = new zrfpInt64(env, this.dc);
/* 299 */     this.nullNode = new zrfpNull(env, this.dc);
/* 300 */     this.parentNode = new zrfpParent(env, this.dc);
/* 301 */     this.stringNode = new zrfpString(env, this.dc);
/*     */ 
/*     */ 
/*     */     
/* 305 */     if (Trace.isOn)
/* 306 */       Trace.exit(this, "com.ibm.mq.jmqi.system.zrfp.ZRFP", "<init>(JmqiEnvironment,JmqiTls)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\zrfp\ZRFP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */