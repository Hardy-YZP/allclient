/*     */ package com.ibm.disthub2.impl.matching;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
/*     */ import com.ibm.disthub2.impl.matching.selector.BooleanValue;
/*     */ import com.ibm.disthub2.impl.matching.selector.Conjunction;
/*     */ import com.ibm.disthub2.impl.matching.selector.EvalContext;
/*     */ import com.ibm.disthub2.impl.matching.selector.Identifier;
/*     */ import com.ibm.disthub2.impl.matching.selector.SimpleTest;
/*     */ import com.ibm.disthub2.impl.util.Assert;
/*     */ import com.ibm.disthub2.spi.ClientExceptionConstants;
/*     */ import com.ibm.disthub2.spi.ClientLogConstants;
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
/*     */ public final class BooleanMatcher
/*     */   extends SimpleMatcher
/*     */   implements ClientExceptionConstants, ClientLogConstants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  57 */   private static final DebugObject debug = new DebugObject("BooleanMatcher");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ContentMatcher trueChild;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ContentMatcher falseChild;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BooleanMatcher(Identifier id) {
/*  75 */     super(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void handlePut(SimpleTest test, String topic, Conjunction selector, MatchTarget object, InternTable subExpr, MatchTarget[] targets) throws MatchingException {
/*     */     ContentMatcher next;
/*  87 */     if (test.kind == 0) {
/*  88 */       next = this.trueChild = Factory.createMatcher(this.ordinalPosition, selector, this.trueChild);
/*  89 */     } else if (test.kind == 1) {
/*  90 */       next = this.falseChild = Factory.createMatcher(this.ordinalPosition, selector, this.falseChild);
/*     */     } else {
/*  92 */       throw Assert.failureError();
/*  93 */     }  next.put(topic, selector, object, subExpr, targets);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void handleGet(Object value, String topic, EvalContext msg, SearchResults result) throws MatchingException, BadMessageFormatMatchingException {
/* 104 */     if (!(value instanceof BooleanValue))
/*     */       return; 
/* 106 */     if (((BooleanValue)value).booleanValue()) {
/* 107 */       if (this.trueChild != null) {
/* 108 */         this.trueChild.get(topic, msg, result);
/*     */       }
/* 110 */     } else if (this.falseChild != null) {
/* 111 */       this.falseChild.get(topic, msg, result);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void handleRemove(SimpleTest test, String topic, Conjunction selector, MatchTarget object, InternTable subExpr, int parentId) throws MatchingException {
/* 122 */     if (test.kind == 0) {
/* 123 */       this.trueChild = (ContentMatcher)this.trueChild.remove(topic, selector, object, subExpr, this.ordinalPosition);
/* 124 */     } else if (test.kind == 1) {
/* 125 */       this.falseChild = (ContentMatcher)this.falseChild.remove(topic, selector, object, subExpr, this.ordinalPosition);
/*     */     } else {
/* 127 */       throw Assert.failureError();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isEmpty() {
/* 136 */     return (super.isEmpty() && this.trueChild == null && this.falseChild == null);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\BooleanMatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */