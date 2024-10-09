/*    */ package com.ibm.disthub2.impl.util;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Enumeration;
/*    */ import java.util.Hashtable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StampPair
/*    */   implements Serializable, Cloneable
/*    */ {
/*    */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*    */   public static final long UNDEFINED_TIMESTAMP = -1L;
/*    */   public long pid;
/*    */   public long stamp;
/*    */   public boolean dirty;
/*    */   
/*    */   public StampPair(long pid, long stamp) {
/* 66 */     this(pid, stamp, false);
/*    */   }
/*    */   
/*    */   public StampPair(long pid, long stamp, boolean dirty) {
/* 70 */     this.pid = pid;
/* 71 */     this.stamp = stamp;
/* 72 */     this.dirty = dirty;
/*    */   }
/*    */   
/*    */   public final Object clone() {
/* 76 */     return new StampPair(this.pid, this.stamp, this.dirty);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Hashtable enum2Hashtable(Enumeration<StampPair> enum1) {
/* 83 */     if (enum1 == null) {
/* 84 */       return null;
/*    */     }
/* 86 */     Hashtable<Object, Object> rslt = new Hashtable<>();
/* 87 */     while (enum1.hasMoreElements()) {
/* 88 */       long pid, stamp; StampPair p = enum1.nextElement();
/*    */       
/* 90 */       synchronized (p) {
/* 91 */         pid = p.pid;
/* 92 */         stamp = p.stamp;
/*    */       } 
/* 94 */       rslt.put(Long.valueOf(pid), Long.valueOf(stamp));
/*    */     } 
/* 96 */     return rslt;
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\imp\\util\StampPair.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */