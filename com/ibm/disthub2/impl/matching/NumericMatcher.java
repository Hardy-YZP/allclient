/*     */ package com.ibm.disthub2.impl.matching;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
/*     */ import com.ibm.disthub2.impl.matching.selector.Conjunction;
/*     */ import com.ibm.disthub2.impl.matching.selector.EvalContext;
/*     */ import com.ibm.disthub2.impl.matching.selector.Identifier;
/*     */ import com.ibm.disthub2.impl.matching.selector.NumericValue;
/*     */ import com.ibm.disthub2.impl.matching.selector.SimpleTest;
/*     */ import com.ibm.disthub2.impl.util.FastVector;
/*     */ import com.ibm.disthub2.spi.ClientLogConstants;
/*     */ import java.util.Hashtable;
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
/*     */ public final class NumericMatcher
/*     */   extends SimpleMatcher
/*     */   implements ClientLogConstants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  48 */   private static final DebugObject debug = new DebugObject("NumericMatcher");
/*     */ 
/*     */ 
/*     */   
/*  52 */   private Hashtable children = new Hashtable<>();
/*  53 */   private CheapRangeTable ranges = new CheapRangeTable();
/*     */ 
/*     */ 
/*     */   
/*     */   public NumericMatcher(Identifier id) {
/*  58 */     super(id);
/*  59 */     if (debug.debugIt(32)) {
/*  60 */       debug.debug(-165922073994779L, "NumericMatcher", id, this);
/*     */     }
/*     */     
/*  63 */     if (debug.debugIt(64)) {
/*  64 */       debug.debug(-142394261359015L, "NumericMatcher");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void handlePut(SimpleTest test, String topic, Conjunction selector, MatchTarget object, InternTable subExpr, MatchTarget[] targets) throws MatchingException {
/*     */     ContentMatcher next;
/*  76 */     Object value = test.getValue();
/*     */     
/*  78 */     if (value != null) {
/*     */       
/*  80 */       next = (ContentMatcher)this.children.get(value);
/*     */     } else {
/*     */       
/*  83 */       next = (ContentMatcher)this.ranges.getExact(test);
/*     */     } 
/*     */     
/*  86 */     ContentMatcher newNext = Factory.createMatcher(this.id.ordinalPosition, selector, next);
/*     */     
/*  88 */     newNext.put(topic, selector, object, subExpr, targets);
/*     */     
/*  90 */     if (newNext != next) {
/*  91 */       if (value != null) {
/*  92 */         this.children.put(value, newNext);
/*  93 */       } else if (next == null) {
/*  94 */         this.ranges.insert(test, newNext);
/*     */       } else {
/*  96 */         this.ranges.replace(test, newNext);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void handleGet(Object value, String topic, EvalContext msg, SearchResults result) throws MatchingException, BadMessageFormatMatchingException {
/* 107 */     if (debug.debugIt(16)) {
/* 108 */       debug.debug(-153415734321212L, "handleGet", this.id.name + "=" + value);
/*     */     }
/* 110 */     if (!(value instanceof NumericValue)) {
/* 111 */       if (debug.debugIt(16)) {
/* 112 */         debug.debug(-153415734321212L, "not numeric: " + value);
/*     */       }
/*     */       return;
/*     */     } 
/* 116 */     ContentMatcher exact = (ContentMatcher)this.children.get(value);
/* 117 */     if (exact != null) {
/* 118 */       if (debug.debugIt(16)) {
/* 119 */         debug.debug(-153415734321212L, "handleGet", this.id.name + "=" + value + " found exact match");
/*     */       }
/* 121 */       exact.get(topic, msg, result);
/*     */     } 
/* 123 */     FastVector inexact = this.ranges.find((NumericValue)value);
/* 124 */     if (debug.debugIt(16)) {
/* 125 */       debug.debug(-153415734321212L, "handleGet", this.id.name + "=" + value + " found " + inexact.m_count + " inexact matches");
/*     */     }
/* 127 */     for (int i = 0; i < inexact.m_count; i++) {
/* 128 */       ((ContentMatcher)inexact.m_data[i]).get(topic, msg, result);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void handleRemove(SimpleTest test, String topic, Conjunction selector, MatchTarget object, InternTable subExpr, int parentId) throws MatchingException {
/*     */     ContentMatcher next;
/* 139 */     Object value = test.getValue();
/*     */     
/* 141 */     if (value != null) {
/*     */       
/* 143 */       next = (ContentMatcher)this.children.get(value);
/*     */     } else {
/*     */       
/* 146 */       next = (ContentMatcher)this.ranges.getExact(test);
/*     */     } 
/*     */ 
/*     */     
/* 150 */     ContentMatcher newNext = (ContentMatcher)next.remove(topic, selector, object, subExpr, this.ordinalPosition);
/*     */     
/* 152 */     if (newNext != next) {
/* 153 */       if (value != null) {
/* 154 */         if (newNext != null) {
/* 155 */           this.children.put(value, newNext);
/*     */         } else {
/* 157 */           this.children.remove(value);
/*     */         } 
/* 159 */       } else if (newNext != null) {
/* 160 */         this.ranges.replace(test, newNext);
/*     */       } else {
/* 162 */         this.ranges.remove(test);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isEmpty() {
/* 172 */     return (super.isEmpty() && this.children.isEmpty() && this.ranges.isEmpty());
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\NumericMatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */