/*     */ package com.ibm.disthub2.impl.client;
/*     */ 
/*     */ import com.ibm.disthub2.impl.util.DebugState;
/*     */ import com.ibm.disthub2.impl.util.ExceptionWrapper;
/*     */ import com.ibm.disthub2.spi.LogConstants;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class DebugObject
/*     */   implements Serializable, LogConstants
/*     */ {
/*  51 */   private int debugMask = 0;
/*     */   
/*     */   protected String debug_name;
/*     */ 
/*     */   
/*     */   public boolean debugIt(int mask) {
/*  57 */     return ((this.debugMask & mask) != 0);
/*     */   }
/*     */   
/*     */   public void setMask(int mask) {
/*  61 */     this.debugMask = mask;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public DebugObject(String name) {
/*  67 */     this.debug_name = name;
/*  68 */     this.debugMask = DebugState.register(this);
/*     */ 
/*     */     
/*  71 */     if (debugIt(1)) {
/*  72 */       debug(-157801339138874L, "DebugObject", Integer.toHexString(this.debugMask));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeName(String name) {
/*  81 */     DebugState.deregister(this);
/*  82 */     this.debug_name = name;
/*  83 */     this.debugMask = DebugState.register(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  88 */     return this.debug_name;
/*     */   }
/*     */ 
/*     */   
/*     */   public void debug(long type, Object[] args) {
/*  93 */     Logger.debug(type, this, this.debug_name, args);
/*     */   }
/*     */   public void debug(long type) {
/*  96 */     debug(type, new Object[0]);
/*     */   }
/*     */   public void debug(long type, Object o1) {
/*  99 */     debug(type, new Object[] { o1 });
/*     */   }
/*     */   public void debug(long type, Object o1, Object o2) {
/* 102 */     debug(type, new Object[] { o1, o2 });
/*     */   }
/*     */   public void debug(long type, Object o1, Object o2, Object o3) {
/* 105 */     debug(type, new Object[] { o1, o2, o3 });
/*     */   }
/*     */   public void debug(long type, Object o1, Object o2, Object o3, Object o4) {
/* 108 */     debug(type, new Object[] { o1, o2, o3, o4 });
/*     */   }
/*     */   public void debug(long type, Object o1, Object o2, Object o3, Object o4, Object o5) {
/* 111 */     debug(type, new Object[] { o1, o2, o3, o4, o5 });
/*     */   }
/*     */   public void debug(long type, Object o1, Object o2, Object o3, Object o4, Object o5, Object o6) {
/* 114 */     debug(type, new Object[] { o1, o2, o3, o4, o5, o6 });
/*     */   }
/*     */   public void debug(long type, Object o1, Object o2, Object o3, Object o4, Object o5, Object o6, Object o7) {
/* 117 */     debug(type, new Object[] { o1, o2, o3, o4, o5, o6, o7 });
/*     */   }
/*     */   public void debug(long type, Object o1, Object o2, Object o3, Object o4, Object o5, Object o6, Object o7, Object o8) {
/* 120 */     debug(type, new Object[] { o1, o2, o3, o4, o5, o6, o7, o8 });
/*     */   }
/*     */   public void debug(long type, Object[] oa, int n) {
/* 123 */     if (n < oa.length) {
/* 124 */       if (n < 0)
/* 125 */         n = 0; 
/* 126 */       Object[] copy = new Object[n];
/* 127 */       for (int i = 0; i < n; i++)
/* 128 */         copy[i] = oa[i]; 
/* 129 */       debug(type, copy);
/*     */     } else {
/* 131 */       debug(type, oa);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object debugX(Throwable x) {
/* 136 */     return new ExceptionWrapper(x);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\client\DebugObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */