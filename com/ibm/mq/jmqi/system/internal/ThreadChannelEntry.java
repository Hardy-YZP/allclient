/*     */ package com.ibm.mq.jmqi.system.internal;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ThreadChannelEntry
/*     */   extends JmqiObject
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/internal/ThreadChannelEntry.java";
/*     */   private ChannelEntry thisAlphaEntry;
/*     */   private ChannelEntry firstWeightedEntry;
/*     */   private ChannelEntry thisWeightedEntry;
/*     */   
/*     */   static {
/*  38 */     if (Trace.isOn) {
/*  39 */       Trace.data("com.ibm.mq.jmqi.system.internal.ThreadChannelEntry", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/internal/ThreadChannelEntry.java");
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
/*  51 */   private Set<ChannelEntry> seenWeightedEntries = new HashSet<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ThreadChannelEntry(JmqiEnvironment env) {
/*  57 */     super(env);
/*  58 */     if (Trace.isOn) {
/*  59 */       Trace.entry(this, "com.ibm.mq.jmqi.system.internal.ThreadChannelEntry", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/*     */     
/*  62 */     this.thisAlphaEntry = null;
/*  63 */     this.firstWeightedEntry = null;
/*  64 */     this.thisWeightedEntry = null;
/*     */     
/*  66 */     if (Trace.isOn) {
/*  67 */       Trace.exit(this, "com.ibm.mq.jmqi.system.internal.ThreadChannelEntry", "<init>(JmqiEnvironment)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChannelEntry getThisAlphaEntry() {
/*  77 */     if (Trace.isOn) {
/*  78 */       Trace.data(this, "com.ibm.mq.jmqi.system.internal.ThreadChannelEntry", "getThisAlphaEntry()", "getter", this.thisAlphaEntry);
/*     */     }
/*     */     
/*  81 */     return this.thisAlphaEntry;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChannelEntry getFirstWeightedEntry() {
/*  88 */     if (Trace.isOn) {
/*  89 */       Trace.data(this, "com.ibm.mq.jmqi.system.internal.ThreadChannelEntry", "getFirstWeightedEntry()", "getter", this.firstWeightedEntry);
/*     */     }
/*     */     
/*  92 */     return this.firstWeightedEntry;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChannelEntry getThisWeightedEntry() {
/*  99 */     if (Trace.isOn) {
/* 100 */       Trace.data(this, "com.ibm.mq.jmqi.system.internal.ThreadChannelEntry", "getThisWeightedEntry()", "getter", this.thisWeightedEntry);
/*     */     }
/*     */     
/* 103 */     return this.thisWeightedEntry;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setThisAlphaEntry(ChannelEntry aEntry) {
/* 110 */     if (Trace.isOn) {
/* 111 */       Trace.data(this, "com.ibm.mq.jmqi.system.internal.ThreadChannelEntry", "setThisAlphaEntry(ChannelEntry)", "setter", aEntry);
/*     */     }
/*     */     
/* 114 */     this.thisAlphaEntry = aEntry;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFirstWeightedEntry(ChannelEntry wEntry) {
/* 122 */     if (Trace.isOn) {
/* 123 */       Trace.data(this, "com.ibm.mq.jmqi.system.internal.ThreadChannelEntry", "setFirstWeightedEntry(ChannelEntry)", "setter", wEntry);
/*     */     }
/*     */     
/* 126 */     this.firstWeightedEntry = wEntry;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setThisWeightedEntry(ChannelEntry wEntry) {
/* 134 */     if (Trace.isOn) {
/* 135 */       Trace.data(this, "com.ibm.mq.jmqi.system.internal.ThreadChannelEntry", "setThisWeightedEntry(ChannelEntry)", "setter", wEntry);
/*     */     }
/*     */     
/* 138 */     this.thisWeightedEntry = wEntry;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean seenThisWeightedEntry(ChannelEntry wEntry) {
/* 147 */     boolean seenThis = this.seenWeightedEntries.contains(wEntry);
/* 148 */     if (Trace.isOn) {
/* 149 */       Trace.data(this, "com.ibm.mq.jmqi.system.internal.ThreadChannelEntry", "seenThisWeightedEntry(ChannelEntry)", "getter", wEntry);
/*     */     }
/*     */     
/* 152 */     return seenThis;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void rememberThisWeightedEntry(ChannelEntry wEntry) {
/* 159 */     if (Trace.isOn) {
/* 160 */       Trace.data(this, "com.ibm.mq.jmqi.system.internal.ThreadChannelEntry", "rememberThisWeightedEntry(ChannelEntry)", "setter", wEntry);
/*     */     }
/*     */ 
/*     */     
/* 164 */     this.seenWeightedEntries.add(wEntry);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int seenWeightedEntryCount() {
/* 171 */     int seencount = this.seenWeightedEntries.size();
/* 172 */     if (Trace.isOn) {
/* 173 */       Trace.data(this, "com.ibm.mq.jmqi.system.internal.ThreadChannelEntry", "seenThisWeightedEntry(ChannelEntry)", "getter", 
/* 174 */           Integer.valueOf(seencount));
/*     */     }
/* 176 */     return seencount;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\internal\ThreadChannelEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */