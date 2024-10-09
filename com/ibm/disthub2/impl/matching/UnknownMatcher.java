/*    */ package com.ibm.disthub2.impl.matching;
/*    */ 
/*    */ import com.ibm.disthub2.impl.matching.selector.Conjunction;
/*    */ import com.ibm.disthub2.impl.matching.selector.EvalContext;
/*    */ import com.ibm.disthub2.impl.matching.selector.Identifier;
/*    */ import com.ibm.disthub2.impl.matching.selector.SimpleTest;
/*    */ import com.ibm.disthub2.impl.util.Assert;
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
/*    */ public final class UnknownMatcher
/*    */   extends SimpleMatcher
/*    */ {
/*    */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*    */   
/*    */   public UnknownMatcher(Identifier id) {
/* 40 */     super(id);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   void handlePut(SimpleTest test, String topic, Conjunction selector, MatchTarget object, InternTable subExpr, MatchTarget[] targets) throws MatchingException {
/* 46 */     Assert.failure();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void handleGet(Object value, String topic, EvalContext msg, SearchResults result) throws MatchingException, BadMessageFormatMatchingException {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void handleRemove(SimpleTest test, String topic, Conjunction selector, MatchTarget object, InternTable subExpr, int parentId) throws MatchingException {
/* 59 */     Assert.failure();
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\UnknownMatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */