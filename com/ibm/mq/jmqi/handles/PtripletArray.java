/*     */ package com.ibm.mq.jmqi.handles;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.mq.jmqi.system.zrfp.Triplet;
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
/*     */ public class PtripletArray
/*     */   extends JmqiObject
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/handles/PtripletArray.java";
/*     */   public Triplet[] triplets;
/*     */   
/*     */   static {
/*  36 */     if (Trace.isOn) {
/*  37 */       Trace.data("com.ibm.mq.jmqi.handles.PtripletArray", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/handles/PtripletArray.java");
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
/*     */   public PtripletArray(JmqiEnvironment env, Triplet[] triplets) {
/*  59 */     super(env);
/*  60 */     if (Trace.isOn) {
/*  61 */       Trace.entry(this, "com.ibm.mq.jmqi.handles.PtripletArray", "<init>(JmqiEnvironment,Triplet [ ])", new Object[] { env, triplets });
/*     */     }
/*     */     
/*  64 */     this.triplets = triplets;
/*     */ 
/*     */     
/*  67 */     if (Trace.isOn) {
/*  68 */       Trace.exit(this, "com.ibm.mq.jmqi.handles.PtripletArray", "<init>(JmqiEnvironment,Triplet [ ])");
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
/*     */   public PtripletArray(JmqiEnvironment env) {
/*  80 */     super(env);
/*  81 */     if (Trace.isOn) {
/*  82 */       Trace.entry(this, "com.ibm.mq.jmqi.handles.PtripletArray", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/*     */     
/*  85 */     this.triplets = null;
/*     */ 
/*     */     
/*  88 */     if (Trace.isOn) {
/*  89 */       Trace.exit(this, "com.ibm.mq.jmqi.handles.PtripletArray", "<init>(JmqiEnvironment)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Triplet[] getTriplets() {
/*  98 */     if (Trace.isOn) {
/*  99 */       Trace.data(this, "com.ibm.mq.jmqi.handles.PtripletArray", "getTriplets()", "getter", this.triplets);
/*     */     }
/*     */     
/* 102 */     return this.triplets;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTriplets(Triplet[] triplets) {
/* 109 */     if (Trace.isOn) {
/* 110 */       Trace.data(this, "com.ibm.mq.jmqi.handles.PtripletArray", "setTriplets(Triplet [ ])", "setter", triplets);
/*     */     }
/*     */     
/* 113 */     this.triplets = triplets;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\handles\PtripletArray.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */