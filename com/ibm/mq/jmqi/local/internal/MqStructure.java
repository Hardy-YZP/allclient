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
/*     */ public class MqStructure
/*     */   extends JmqiObject
/*     */ {
/*     */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/internal/MqStructure.java";
/*     */   private int index;
/*     */   private String name;
/*     */   private int version;
/*     */   private int size;
/*     */   
/*     */   static {
/*  34 */     if (Trace.isOn) {
/*  35 */       Trace.data("com.ibm.mq.jmqi.local.internal.MqStructure", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/internal/MqStructure.java");
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
/*     */   public MqStructure(JmqiEnvironment env, int index, String name, int version, int size) {
/*  59 */     super(env);
/*  60 */     this.index = index;
/*  61 */     this.name = name;
/*  62 */     this.version = version;
/*  63 */     this.size = size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getIndex() {
/*  72 */     if (Trace.isOn) {
/*  73 */       Trace.data(this, "com.ibm.mq.jmqi.local.internal.MqStructure", "getIndex()", "getter", 
/*  74 */           Integer.valueOf(this.index));
/*     */     }
/*  76 */     return this.index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  83 */     if (Trace.isOn) {
/*  84 */       Trace.data(this, "com.ibm.mq.jmqi.local.internal.MqStructure", "getName()", "getter", this.name);
/*     */     }
/*  86 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion() {
/*  93 */     if (Trace.isOn) {
/*  94 */       Trace.data(this, "com.ibm.mq.jmqi.local.internal.MqStructure", "getVersion()", "getter", 
/*  95 */           Integer.valueOf(this.version));
/*     */     }
/*  97 */     return this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSize() {
/* 104 */     if (Trace.isOn) {
/* 105 */       Trace.data(this, "com.ibm.mq.jmqi.local.internal.MqStructure", "getSize()", "getter", 
/* 106 */           Integer.valueOf(this.size));
/*     */     }
/* 108 */     return this.size;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\local\internal\MqStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */