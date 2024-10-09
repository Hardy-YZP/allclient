/*     */ package com.ibm.disthub2.impl.matching;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
/*     */ import com.ibm.disthub2.impl.matching.selector.Conjunction;
/*     */ import com.ibm.disthub2.impl.matching.selector.EvalContext;
/*     */ import com.ibm.disthub2.impl.util.Assert;
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
/*     */ abstract class ContentMatcher
/*     */   extends Matcher
/*     */   implements ClientLogConstants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  38 */   private static final DebugObject debug = new DebugObject("ContentMatcher");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int ordinalPosition;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   ContentMatcher vacantChild;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   ContentMatcher(int ordinalPosition) {
/*  55 */     this.ordinalPosition = ordinalPosition;
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
/*     */   void put(String topic, Conjunction selector, MatchTarget object, InternTable subExpr, MatchTarget[] targets) throws MatchingException {
/*  68 */     this.vacantChild = Factory.createMatcher(this.ordinalPosition, selector, this.vacantChild);
/*  69 */     if (debug.debugIt(16)) {
/*  70 */       debug.debug(-153415734321212L, "put", this + "vacantChild is: " + this.vacantChild);
/*     */     }
/*  72 */     this.vacantChild.put(topic, selector, object, subExpr, targets);
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
/*     */   void get(String topic, EvalContext msg, SearchResults result) throws MatchingException, BadMessageFormatMatchingException {
/*  84 */     if (this.vacantChild != null) {
/*     */       
/*  86 */       if (debug.debugIt(16)) {
/*  87 */         debug.debug(-153415734321212L, "get", this + "nonnull vacantChild is: " + this.vacantChild);
/*     */       }
/*  89 */       this.vacantChild.get(topic, msg, result);
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
/*     */ 
/*     */   
/*     */   Matcher remove(String topic, Conjunction selector, MatchTarget object, InternTable subExpr, int parentId) throws MatchingException {
/* 103 */     Assert.condition((this.vacantChild != null));
/* 104 */     if (debug.debugIt(16)) {
/* 105 */       debug.debug(-153415734321212L, "remove", this + "vacantChild is: " + this.vacantChild);
/*     */     }
/* 107 */     this.vacantChild = (ContentMatcher)this.vacantChild.remove(topic, selector, object, subExpr, this.ordinalPosition);
/* 108 */     if (debug.debugIt(16)) {
/* 109 */       debug.debug(-153415734321212L, "remove", this + "return vacantChild: " + this.vacantChild);
/*     */     }
/* 111 */     return this.vacantChild;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\ContentMatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */