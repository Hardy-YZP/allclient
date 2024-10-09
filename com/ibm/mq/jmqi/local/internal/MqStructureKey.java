/*     */ package com.ibm.mq.jmqi.local.internal;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
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
/*     */ public class MqStructureKey
/*     */   extends JmqiObject
/*     */ {
/*     */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/internal/MqStructureKey.java";
/*     */   private int index;
/*     */   private int version;
/*     */   
/*     */   static {
/*  34 */     if (Trace.isOn) {
/*  35 */       Trace.data("com.ibm.mq.jmqi.local.internal.MqStructureKey", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/internal/MqStructureKey.java");
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
/*     */   public MqStructureKey(JmqiEnvironment env, int index, int version) {
/*  55 */     super(env);
/*  56 */     this.index = index;
/*  57 */     this.version = version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  67 */     if (Trace.isOn) {
/*  68 */       Trace.entry(this, "com.ibm.mq.jmqi.local.internal.MqStructureKey", "hashCode()");
/*     */     }
/*  70 */     int hash = 0;
/*  71 */     hash += 31 * this.index;
/*  72 */     hash += 37 * this.version;
/*  73 */     if (Trace.isOn) {
/*  74 */       Trace.exit(this, "com.ibm.mq.jmqi.local.internal.MqStructureKey", "hashCode()", 
/*  75 */           Integer.valueOf(hash));
/*     */     }
/*  77 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/*  85 */     if (Trace.isOn) {
/*  86 */       Trace.entry(this, "com.ibm.mq.jmqi.local.internal.MqStructureKey", "equals(Object)", new Object[] { obj });
/*     */     }
/*     */     
/*  89 */     boolean result = false;
/*  90 */     if (obj != null && obj instanceof MqStructureKey) {
/*  91 */       MqStructureKey other = (MqStructureKey)obj;
/*  92 */       if (other.index == this.index && other.version == this.version) {
/*  93 */         result = true;
/*     */       }
/*     */     } 
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.exit(this, "com.ibm.mq.jmqi.local.internal.MqStructureKey", "equals(Object)", 
/*  98 */           Boolean.valueOf(result));
/*     */     }
/* 100 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\local\internal\MqStructureKey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */