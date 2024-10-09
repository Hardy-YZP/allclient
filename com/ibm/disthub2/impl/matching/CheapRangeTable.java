/*     */ package com.ibm.disthub2.impl.matching;
/*     */ 
/*     */ import com.ibm.disthub2.impl.matching.selector.NumericValue;
/*     */ import com.ibm.disthub2.impl.matching.selector.SimpleTest;
/*     */ import com.ibm.disthub2.impl.util.Assert;
/*     */ import com.ibm.disthub2.impl.util.FastVector;
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
/*     */ public class CheapRangeTable
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  47 */   public static int numTables = 0;
/*  48 */   public static int numEntries = 0;
/*     */   
/*  50 */   int size = 0;
/*     */ 
/*     */   
/*     */   RangeEntry[] ranges;
/*     */ 
/*     */ 
/*     */   
/*     */   public CheapRangeTable() {
/*  58 */     this.ranges = new RangeEntry[3];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void insert(SimpleTest test, Object target) {
/*  69 */     if (this.size == this.ranges.length) {
/*  70 */       RangeEntry[] tmp = new RangeEntry[2 * this.size];
/*  71 */       System.arraycopy(this.ranges, 0, tmp, 0, this.size);
/*  72 */       this.ranges = tmp;
/*     */     } 
/*     */     
/*  75 */     this.ranges[this.size] = new RangeEntry(test, target);
/*  76 */     this.size++;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getExact(SimpleTest test) {
/*  82 */     for (int i = 0; i < this.size; i++) {
/*  83 */       if (this.ranges[i].correspondsTo(test))
/*  84 */         return (this.ranges[i]).target; 
/*     */     } 
/*  86 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void replace(SimpleTest test, Object target) {
/*  92 */     for (int i = 0; i < this.size; i++) {
/*  93 */       if (this.ranges[i].correspondsTo(test)) {
/*  94 */         (this.ranges[i]).target = target; return;
/*     */       } 
/*     */     } 
/*  97 */     Assert.failure();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FastVector find(NumericValue value) {
/* 107 */     FastVector targets = new FastVector(1);
/*     */     
/* 109 */     for (int i = 0; i < this.size; i++) {
/* 110 */       if (this.ranges[i].contains(value)) {
/* 111 */         targets.addElement((this.ranges[i]).target);
/*     */       }
/*     */     } 
/* 114 */     return targets;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 119 */     return (this.size == 0);
/*     */   }
/*     */   
/*     */   public void remove(SimpleTest test) {
/* 123 */     Assert.condition((this.size != 0), "Removal from empty range table.");
/*     */     
/* 125 */     int toGo = -1;
/* 126 */     for (int i = 0; toGo < 0 && i < this.size; i++) {
/* 127 */       if (this.ranges[i].correspondsTo(test)) {
/* 128 */         toGo = i;
/*     */       }
/*     */     } 
/* 131 */     Assert.condition((toGo >= 0), "Unable to remove entry from CheapRangeTable.");
/*     */     
/* 133 */     System.arraycopy(this.ranges, toGo + 1, this.ranges, toGo, this.size - toGo - 1);
/* 134 */     this.size--;
/*     */   }
/*     */   
/*     */   class RangeEntry { NumericValue lower;
/*     */     boolean lowIncl;
/*     */     NumericValue upper;
/*     */     boolean upIncl;
/*     */     Object target;
/*     */     
/*     */     RangeEntry(SimpleTest test, Object t) {
/* 144 */       this.lower = test.lower;
/* 145 */       this.lowIncl = test.lowIncl;
/* 146 */       this.upper = test.upper;
/* 147 */       this.upIncl = test.upIncl;
/* 148 */       this.target = t;
/*     */     }
/*     */     boolean correspondsTo(SimpleTest t) {
/* 151 */       if (this.lowIncl != t.lowIncl || this.upIncl != t.upIncl)
/* 152 */         return false; 
/* 153 */       if (this.lower == null) {
/* 154 */         if (t.lower != null)
/* 155 */           return false; 
/*     */       } else {
/* 157 */         if (t.lower == null)
/* 158 */           return false; 
/* 159 */         if (!this.lower.equals(t.lower))
/* 160 */           return false; 
/* 161 */       }  if (this.upper == null) {
/* 162 */         if (t.upper != null)
/* 163 */           return false; 
/*     */       } else {
/* 165 */         if (t.upper == null)
/* 166 */           return false; 
/* 167 */         if (!this.upper.equals(t.upper))
/* 168 */           return false; 
/* 169 */       }  return true;
/*     */     }
/*     */     boolean contains(NumericValue v) {
/* 172 */       if (this.lower != null) {
/* 173 */         int comp = this.lower.compareTo(v);
/* 174 */         if (comp > 0 || (!this.lowIncl && comp == 0))
/* 175 */           return false; 
/*     */       } 
/* 177 */       if (this.upper != null) {
/* 178 */         int comp = this.upper.compareTo(v);
/* 179 */         if (comp < 0 || (!this.upIncl && comp == 0))
/* 180 */           return false; 
/*     */       } 
/* 182 */       return true;
/*     */     } }
/*     */ 
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\CheapRangeTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */