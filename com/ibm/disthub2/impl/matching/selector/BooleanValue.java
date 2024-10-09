/*     */ package com.ibm.disthub2.impl.matching.selector;
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
/*     */ public final class BooleanValue
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   private static final int indexTRUE = 0;
/*     */   private static final int indexFALSE = 1;
/*     */   private static final int indexNULL = 2;
/*     */   private int lookupValue;
/*     */   
/*     */   private BooleanValue(int lookupValue) {
/*  41 */     this.lookupValue = lookupValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  49 */   public static final BooleanValue TRUE = new BooleanValue(0);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   public static final BooleanValue FALSE = new BooleanValue(1);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  59 */   public static final BooleanValue NULL = new BooleanValue(2);
/*     */ 
/*     */ 
/*     */   
/*  63 */   private static final BooleanValue[][] andTable = new BooleanValue[][] { { TRUE, FALSE, NULL }, { FALSE, FALSE, FALSE }, { NULL, FALSE, NULL } };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  68 */   private static final BooleanValue[][] orTable = new BooleanValue[][] { { TRUE, TRUE, TRUE }, { TRUE, FALSE, NULL }, { TRUE, NULL, NULL } };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  73 */   private static final BooleanValue[] notTable = new BooleanValue[] { FALSE, TRUE, NULL };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BooleanValue and(BooleanValue b) {
/*  83 */     return andTable[this.lookupValue][b.lookupValue];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BooleanValue or(BooleanValue b) {
/*  90 */     return orTable[this.lookupValue][b.lookupValue];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BooleanValue not() {
/*  97 */     return notTable[this.lookupValue];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BooleanValue valueOf(boolean val) {
/* 104 */     return val ? TRUE : FALSE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean booleanValue() {
/* 113 */     return (this == TRUE);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 119 */     return String.valueOf(booleanValue());
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\selector\BooleanValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */