/*     */ package com.ibm.disthub2.impl.matching;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
/*     */ import com.ibm.disthub2.impl.matching.selector.Conjunction;
/*     */ import com.ibm.disthub2.impl.matching.selector.EvalContext;
/*     */ import com.ibm.disthub2.impl.matching.selector.Identifier;
/*     */ import com.ibm.disthub2.impl.matching.selector.SimpleTest;
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
/*     */ abstract class SimpleMatcher
/*     */   extends ContentMatcher
/*     */   implements ClientLogConstants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  38 */   private static final DebugObject debug = new DebugObject("SimpleMatcher");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Identifier id;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ContentMatcher nullChild;
/*     */ 
/*     */ 
/*     */   
/*     */   private ContentMatcher notNullChild;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   SimpleMatcher(Identifier id) {
/*  58 */     super(id.ordinalPosition);
/*  59 */     this.id = id;
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
/*  72 */     if (debug.debugIt(16)) {
/*  73 */       debug.debug(-153415734321212L, "put", this + ", topic=" + topic + ", ordinalpos=" + this.ordinalPosition);
/*     */     }
/*  75 */     SimpleTest test = Factory.findTest(this.ordinalPosition, selector);
/*  76 */     if (test == null) {
/*     */       
/*  78 */       if (debug.debugIt(16)) {
/*  79 */         debug.debug(-153415734321212L, "put", this + ", test is null");
/*     */       }
/*  81 */       super.put(topic, selector, object, subExpr, targets);
/*     */     } else {
/*     */       ContentMatcher next;
/*     */       
/*  85 */       if (test.kind == 4) {
/*  86 */         next = this.nullChild = Factory.createMatcher(this.ordinalPosition, selector, this.nullChild);
/*  87 */       } else if (test.kind == 5) {
/*  88 */         next = this.notNullChild = Factory.createMatcher(this.ordinalPosition, selector, this.notNullChild);
/*     */       } else {
/*  90 */         handlePut(test, topic, selector, object, subExpr, targets);
/*     */         return;
/*     */       } 
/*  93 */       if (debug.debugIt(16)) {
/*  94 */         debug.debug(-153415734321212L, "put", this + ", drive put against next matcher: " + next);
/*     */       }
/*  96 */       next.put(topic, selector, object, subExpr, targets);
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
/*     */   void get(String topic, EvalContext msg, SearchResults result) throws MatchingException, BadMessageFormatMatchingException {
/* 110 */     if (debug.debugIt(16)) {
/* 111 */       debug.debug(-153415734321212L, "get", this + ", is about to call getIdentifierValue on topic=" + topic);
/*     */     }
/* 113 */     Object value = msg.getIdentifierValue(this.id, false);
/*     */     
/* 115 */     if (value == null) {
/*     */       
/* 117 */       if (this.nullChild != null)
/*     */       {
/* 119 */         this.nullChild.get(topic, msg, result);
/*     */       }
/*     */     } else {
/*     */       
/* 123 */       if (debug.debugIt(16)) {
/* 124 */         debug.debug(-153415734321212L, "get", this + ", getIdentifierValue(" + this.id + ", false) has returned: " + value.toString());
/*     */       }
/* 126 */       handleGet(value, topic, msg, result);
/*     */       
/* 128 */       if (this.notNullChild != null)
/*     */       {
/* 130 */         this.notNullChild.get(topic, msg, result);
/*     */       }
/*     */     } 
/*     */     
/* 134 */     super.get(topic, msg, result);
/*     */     
/* 136 */     if (debug.debugIt(16)) {
/* 137 */       debug.debug(-153415734321212L, "get", this + ", About to exit with the following search results: " + result.toString());
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
/*     */   Matcher remove(String topic, Conjunction selector, MatchTarget object, InternTable subExpr, int parentId) throws MatchingException {
/* 150 */     if (debug.debugIt(16)) {
/* 151 */       debug.debug(-153415734321212L, "remove", this);
/*     */     }
/* 153 */     SimpleTest test = Factory.findTest(this.ordinalPosition, selector);
/* 154 */     if (test == null) {
/* 155 */       super.remove(topic, selector, object, subExpr, this.ordinalPosition);
/* 156 */     } else if (test.kind == 4) {
/* 157 */       this.nullChild = (ContentMatcher)this.nullChild.remove(topic, selector, object, subExpr, this.ordinalPosition);
/* 158 */     } else if (test.kind == 5) {
/* 159 */       this.notNullChild = (ContentMatcher)this.notNullChild.remove(topic, selector, object, subExpr, this.ordinalPosition);
/*     */     } else {
/* 161 */       handleRemove(test, topic, selector, object, subExpr, this.ordinalPosition);
/* 162 */     }  if (isEmpty()) {
/*     */       
/* 164 */       if (this.vacantChild instanceof DifficultMatcher) {
/*     */         
/* 166 */         if (debug.debugIt(16)) {
/* 167 */           debug.debug(-153415734321212L, "remove", "vacantChild is a DM, reset ord position: " + this.vacantChild.ordinalPosition);
/*     */         }
/*     */ 
/*     */         
/* 171 */         this.vacantChild.ordinalPosition = parentId;
/*     */       } 
/*     */       
/* 174 */       if (debug.debugIt(16)) {
/*     */         
/* 176 */         StringBuffer buf = new StringBuffer();
/* 177 */         buf.append("return vacantChild: ");
/* 178 */         buf.append(this.vacantChild);
/*     */         
/* 180 */         if (this.vacantChild != null) {
/*     */           
/* 182 */           buf.append(", with ord position: ");
/* 183 */           buf.append(this.vacantChild.ordinalPosition);
/*     */         } 
/* 185 */         debug.debug(-153415734321212L, "remove", buf.toString());
/*     */       } 
/* 187 */       return this.vacantChild;
/*     */     } 
/*     */ 
/*     */     
/* 191 */     if (debug.debugIt(16)) {
/* 192 */       debug.debug(-153415734321212L, "remove", "return this" + this);
/*     */     }
/* 194 */     return this;
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
/*     */   boolean isEmpty() {
/* 207 */     return (this.nullChild == null && this.notNullChild == null);
/*     */   }
/*     */   
/*     */   abstract void handlePut(SimpleTest paramSimpleTest, String paramString, Conjunction paramConjunction, MatchTarget paramMatchTarget, InternTable paramInternTable, MatchTarget[] paramArrayOfMatchTarget) throws MatchingException;
/*     */   
/*     */   abstract void handleGet(Object paramObject, String paramString, EvalContext paramEvalContext, SearchResults paramSearchResults) throws MatchingException, BadMessageFormatMatchingException;
/*     */   
/*     */   abstract void handleRemove(SimpleTest paramSimpleTest, String paramString, Conjunction paramConjunction, MatchTarget paramMatchTarget, InternTable paramInternTable, int paramInt) throws MatchingException;
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\SimpleMatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */