/*     */ package com.ibm.disthub2.impl.matching;
/*     */ 
/*     */ import com.ibm.disthub2.impl.matching.selector.Conjunction;
/*     */ import com.ibm.disthub2.impl.matching.selector.EvalContext;
/*     */ import com.ibm.disthub2.impl.matching.selector.Identifier;
/*     */ import com.ibm.disthub2.impl.matching.selector.SimpleTest;
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
/*     */ public final class StringMatcher
/*     */   extends SimpleMatcher
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   private static final int INITIAL_CHILDREN_CAPACITY = 10;
/*     */   private Hashtable children;
/*     */   
/*     */   public StringMatcher(Identifier id) {
/*  65 */     super(id);
/*  66 */     this.children = new Hashtable<>(10);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void handlePut(SimpleTest test, String topic, Conjunction selector, MatchTarget object, InternTable subExpr, MatchTarget[] targets) throws MatchingException {
/*  75 */     ContentMatcher next = (ContentMatcher)this.children.get(test.stringVal);
/*  76 */     ContentMatcher newNext = Factory.createMatcher(this.id.ordinalPosition, selector, next);
/*  77 */     if (newNext != next) {
/*  78 */       this.children.put(test.stringVal, newNext);
/*     */     }
/*  80 */     newNext.put(topic, selector, object, subExpr, targets);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void handleGet(Object value, String topic, EvalContext msg, SearchResults result) throws MatchingException, BadMessageFormatMatchingException {
/*  89 */     if (!(value instanceof String)) {
/*     */       return;
/*     */     }
/*  92 */     ContentMatcher next = (ContentMatcher)this.children.get(value);
/*  93 */     if (next != null) {
/*  94 */       next.get(topic, msg, result);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void handleRemove(SimpleTest test, String topic, Conjunction selector, MatchTarget object, InternTable subExpr, int parentId) throws MatchingException {
/* 104 */     ContentMatcher next = (ContentMatcher)this.children.get(test.stringVal);
/* 105 */     ContentMatcher newNext = (ContentMatcher)next.remove(topic, selector, object, subExpr, this.ordinalPosition);
/* 106 */     if (newNext == null) {
/* 107 */       this.children.remove(test.stringVal);
/* 108 */     } else if (newNext != next) {
/* 109 */       this.children.put(test.stringVal, newNext);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isEmpty() {
/* 118 */     return (super.isEmpty() && this.children.isEmpty());
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\StringMatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */