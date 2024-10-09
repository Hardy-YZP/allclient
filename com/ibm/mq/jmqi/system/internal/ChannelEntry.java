/*     */ package com.ibm.mq.jmqi.system.internal;
/*     */ 
/*     */ import com.ibm.mq.exits.MQCD;
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
/*     */ public class ChannelEntry
/*     */   extends JmqiObject
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/internal/ChannelEntry.java";
/*     */   private MQCD channel;
/*     */   private ChannelEntry next;
/*     */   
/*     */   static {
/*  33 */     if (Trace.isOn) {
/*  34 */       Trace.data("com.ibm.mq.jmqi.system.internal.ChannelEntry", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/internal/ChannelEntry.java");
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
/*     */   public ChannelEntry(JmqiEnvironment env, MQCD cd, ChannelEntry chlEntry) {
/*  53 */     super(env);
/*  54 */     if (Trace.isOn) {
/*  55 */       Trace.entry(this, "com.ibm.mq.jmqi.system.internal.ChannelEntry", "<init>(JmqiEnvironment,MQCD,ChannelEntry)", new Object[] { env, cd, chlEntry });
/*     */     }
/*     */     
/*  58 */     this.channel = cd;
/*  59 */     this.next = chlEntry;
/*     */     
/*  61 */     if (Trace.isOn) {
/*  62 */       Trace.exit(this, "com.ibm.mq.jmqi.system.internal.ChannelEntry", "<init>(JmqiEnvironment,MQCD,ChannelEntry)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQCD getChannel() {
/*  72 */     if (Trace.isOn) {
/*  73 */       Trace.data(this, "com.ibm.mq.jmqi.system.internal.ChannelEntry", "getChannel()", "getter", this.channel);
/*     */     }
/*     */     
/*  76 */     return this.channel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChannelEntry getNextChannel() {
/*  83 */     if (Trace.isOn) {
/*  84 */       Trace.data(this, "com.ibm.mq.jmqi.system.internal.ChannelEntry", "getNextChannel()", "getter", this.next);
/*     */     }
/*     */     
/*  87 */     return this.next;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setChannel(MQCD chl) {
/*  94 */     if (Trace.isOn) {
/*  95 */       Trace.data(this, "com.ibm.mq.jmqi.system.internal.ChannelEntry", "setChannel(MQCD)", "setter", chl);
/*     */     }
/*     */     
/*  98 */     this.channel = chl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNextChannel(ChannelEntry nextChl) {
/* 105 */     if (Trace.isOn) {
/* 106 */       Trace.data(this, "com.ibm.mq.jmqi.system.internal.ChannelEntry", "setNextChannel(ChannelEntry)", "setter", nextChl);
/*     */     }
/*     */     
/* 109 */     this.next = nextChl;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\internal\ChannelEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */