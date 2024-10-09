/*     */ package com.ibm.disthub2.impl.matching;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
/*     */ import com.ibm.disthub2.impl.matching.selector.Conjunction;
/*     */ import com.ibm.disthub2.impl.matching.selector.EvalContext;
/*     */ import com.ibm.disthub2.spi.ClientLogConstants;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class CacheingMatcher
/*     */   extends Matcher
/*     */   implements ClientLogConstants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  19 */   private static final DebugObject debug = new DebugObject("CacheingMatcher");
/*     */ 
/*     */   
/*     */   boolean content;
/*     */   
/*     */   Matcher child;
/*     */ 
/*     */   
/*     */   CacheingMatcher(Matcher child) {
/*  28 */     if (debug.debugIt(32)) {
/*  29 */       debug.debug(-165922073994779L, "CacheingMatcher", child);
/*     */     }
/*     */     
/*  32 */     this.child = child;
/*     */ 
/*     */     
/*  35 */     if (debug.debugIt(64)) {
/*  36 */       debug.debug(-142394261359015L, "CacheingMatcher");
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
/*     */   public void put(String topic, Conjunction selector, MatchTarget object, InternTable subExpr, MatchTarget[] isps) throws MatchingException {
/*  48 */     if (debug.debugIt(32)) {
/*  49 */       debug.debug(-165922073994779L, "put", topic, selector, object, subExpr, isps);
/*     */     }
/*     */     
/*  52 */     this.content |= (selector != null) ? 1 : 0;
/*  53 */     if (!(this.child instanceof LeafMatcher))
/*  54 */       this.child = Factory.createMatcher(-1, selector, (ContentMatcher)this.child); 
/*  55 */     this.child.put(topic, selector, object, subExpr, isps);
/*     */ 
/*     */     
/*  58 */     if (debug.debugIt(64)) {
/*  59 */       debug.debug(-142394261359015L, "put");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void get(String topic, EvalContext msg, SearchResults result) throws MatchingException, BadMessageFormatMatchingException {
/*  69 */     if (debug.debugIt(32)) {
/*  70 */       debug.debug(-165922073994779L, "get", topic, msg, result);
/*     */     }
/*     */     
/*  73 */     if (result instanceof CacheingSearchResults)
/*  74 */       ((CacheingSearchResults)result).setMatcher(this.child, this.content); 
/*  75 */     this.child.get(topic, msg, result);
/*     */ 
/*     */     
/*  78 */     if (debug.debugIt(64)) {
/*  79 */       debug.debug(-142394261359015L, "get");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Matcher remove(String topic, Conjunction selector, MatchTarget object, InternTable subExpr, int parentId) throws MatchingException {
/*  88 */     if (debug.debugIt(32)) {
/*  89 */       debug.debug(-165922073994779L, "remove", topic, selector, object, subExpr);
/*     */     }
/*     */     
/*  92 */     this.child = this.child.remove(topic, selector, object, subExpr, -1);
/*  93 */     Matcher result = this;
/*  94 */     if (this.child == null) {
/*  95 */       result = null;
/*     */     }
/*     */     
/*  98 */     if (debug.debugIt(64)) {
/*  99 */       debug.debug(-142394261359015L, "remove", result);
/*     */     }
/*     */     
/* 102 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\CacheingMatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */