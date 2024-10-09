/*     */ package com.ibm.msg.client.commonservices.util;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PTimer
/*     */ {
/*  55 */   private static ThreadLocal<PTimer> threadsTimer = new ThreadLocal<PTimer>()
/*     */     {
/*     */       protected PTimer initialValue()
/*     */       {
/*  59 */         return new PTimer();
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */   
/*     */   private static class TimingEvent
/*     */   {
/*     */     long startTime;
/*     */ 
/*     */     
/*     */     long endTime;
/*     */ 
/*     */     
/*     */     String comment;
/*     */ 
/*     */     
/*  76 */     List<TimingEvent> children = new ArrayList<>();
/*  77 */     TimingEvent parent = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     TimingEvent(String comment) {
/*  84 */       this.startTime = System.nanoTime();
/*  85 */       this.comment = comment;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     void dump(int level) {
/*  93 */       StringBuilder s = new StringBuilder(60);
/*  94 */       for (int i = 0; i < level; i++) {
/*  95 */         s.append(" ");
/*     */       }
/*  97 */       System.out.format("%s%-60.60s - %5d millis\n", new Object[] { s, this.comment, Long.valueOf((this.endTime - this.startTime) / 1000000L) });
/*  98 */       for (TimingEvent sp : this.children) {
/*  99 */         sp.dump(level + 1);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/* 105 */   private TimingEvent root = null;
/* 106 */   private TimingEvent current = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void start(String comment) {
/* 113 */     TimingEvent sp = new TimingEvent(comment);
/* 114 */     if (this.current == null) {
/* 115 */       this.root = sp;
/*     */     } else {
/* 117 */       sp.parent = this.current;
/* 118 */       this.current.children.add(sp);
/*     */     } 
/* 120 */     this.current = sp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void end() {
/* 127 */     this.current.endTime = System.nanoTime();
/* 128 */     this.current = this.current.parent;
/* 129 */     if (this.current == null) {
/* 130 */       System.out.println("Thread: " + Thread.currentThread().getName());
/* 131 */       this.root.dump(0);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void startTiming(String comment) {
/* 140 */     ((PTimer)threadsTimer.get()).start(comment);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void endTiming() {
/* 147 */     ((PTimer)threadsTimer.get()).end();
/*     */   }
/*     */   
/*     */   private PTimer() {}
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservice\\util\PTimer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */