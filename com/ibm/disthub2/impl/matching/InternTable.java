/*    */ package com.ibm.disthub2.impl.matching;
/*    */ 
/*    */ import com.ibm.disthub2.impl.matching.selector.Selector;
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
/*    */ public final class InternTable
/*    */   extends Hashtable
/*    */   implements Selector.InternTable
/*    */ {
/*    */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*    */   private static final int COUNTER_LIMIT = 10000;
/*    */   private static final int MIN_REDUCE = 2000;
/* 54 */   private int counter = 1;
/*    */ 
/*    */ 
/*    */   
/*    */   public int getNextUniqueId() {
/* 59 */     if (this.counter > 10000 && this.counter - size() >= 2000) {
/* 60 */       compress();
/*    */     }
/* 62 */     return this.counter++;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int evalCacheSize() {
/* 70 */     return this.counter;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private void compress() {
/* 76 */     this.counter = 1;
/* 77 */     for (Enumeration<V> e = elements(); e.hasMoreElements(); ) {
/* 78 */       Selector s = (Selector)e.nextElement();
/* 79 */       s.uniqueId = this.counter++;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\InternTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */