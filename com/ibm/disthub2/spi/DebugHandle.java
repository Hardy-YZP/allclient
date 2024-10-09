/*     */ package com.ibm.disthub2.spi;
/*     */ 
/*     */ import java.io.PrintWriter;
/*     */ import java.io.StringWriter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class DebugHandle
/*     */   implements LogConstants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  45 */   private static final DebugHandle fake = new DebugHandle();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   protected int debugMask = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String debug_name;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Embeddable link;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private DebugHandle() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DebugHandle(String debugName, Embeddable E) {
/*  82 */     this.link = E;
/*  83 */     this.debug_name = debugName;
/*  84 */     this.debugMask = this.link.debugRegister(this);
/*     */ 
/*     */     
/*  87 */     if (debugIt(1)) {
/*  88 */       debug(-157801339138874L, this, "DebugHandle.<ctor>", Integer.toHexString(this.debugMask));
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
/*     */   public boolean debugIt(int mask) {
/* 100 */     return ((this.debugMask & mask) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 109 */     return this.debug_name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMask(int mask) {
/* 120 */     this.debugMask = mask;
/*     */   }
/*     */ 
/*     */   
/*     */   public void debug(long type, Object caller, String module, Object[] args) {
/* 125 */     this.link.debug(type, caller, module, args);
/*     */   }
/*     */   public void debug(long type, Object caller, String module) {
/* 128 */     debug(type, caller, module, new Object[0]);
/*     */   }
/*     */   public void debug(long type, Object caller, String module, Object o1) {
/* 131 */     debug(type, caller, module, new Object[] { o1 });
/*     */   }
/*     */   public void debug(long type, Object caller, String module, Object o1, Object o2) {
/* 134 */     debug(type, caller, module, new Object[] { o1, o2 });
/*     */   }
/*     */   public void debug(long type, Object caller, String module, Object o1, Object o2, Object o3) {
/* 137 */     debug(type, caller, module, new Object[] { o1, o2, o3 });
/*     */   }
/*     */   public void debug(long type, Object caller, String module, Object o1, Object o2, Object o3, Object o4) {
/* 140 */     debug(type, caller, module, new Object[] { o1, o2, o3, o4 });
/*     */   }
/*     */   public void debug(long type, Object caller, String module, Object o1, Object o2, Object o3, Object o4, Object o5) {
/* 143 */     debug(type, caller, module, new Object[] { o1, o2, o3, o4, o5 });
/*     */   }
/*     */   public void debug(long type, Object caller, String module, Object o1, Object o2, Object o3, Object o4, Object o5, Object o6) {
/* 146 */     debug(type, caller, module, new Object[] { o1, o2, o3, o4, o5, o6 });
/*     */   }
/*     */   public void debug(long type, Object caller, String module, Object o1, Object o2, Object o3, Object o4, Object o5, Object o6, Object o7) {
/* 149 */     debug(type, caller, module, new Object[] { o1, o2, o3, o4, o5, o6, o7 });
/*     */   }
/*     */   public void debug(long type, Object caller, String module, Object o1, Object o2, Object o3, Object o4, Object o5, Object o6, Object o7, Object o8) {
/* 152 */     debug(type, caller, module, new Object[] { o1, o2, o3, o4, o5, o6, o7, o8 });
/*     */   }
/*     */   public void debug(long type, Object caller, String module, Object[] oa, int n) {
/* 155 */     if (n < oa.length) {
/* 156 */       if (n < 0)
/* 157 */         n = 0; 
/* 158 */       Object[] copy = new Object[n];
/* 159 */       for (int i = 0; i < n; i++)
/* 160 */         copy[i] = oa[i]; 
/* 161 */       debug(type, caller, module, copy);
/*     */     } else {
/* 163 */       debug(type, caller, module, oa);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Throwable wrapException(Throwable x) {
/* 174 */     fake.getClass(); return new ThrowableWrapper(x);
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
/*     */   public class ThrowableWrapper
/*     */     extends Throwable
/*     */   {
/* 199 */     String rep = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     Throwable x;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     ThrowableWrapper(Throwable x) {
/* 212 */       this.x = x;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 222 */       if (this.rep == null) {
/* 223 */         StringWriter sw = new StringWriter();
/* 224 */         this.x.printStackTrace(new PrintWriter(sw));
/* 225 */         this.rep = sw.toString();
/*     */       } 
/*     */       
/* 228 */       return this.rep;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\spi\DebugHandle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */