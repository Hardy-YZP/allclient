/*     */ package com.ibm.disthub2.impl.matching;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
/*     */ import com.ibm.disthub2.impl.matching.selector.Conjunction;
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
/*     */ final class Factory
/*     */   implements ClientExceptionConstants, ClientLogConstants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  82 */   private static final DebugObject debug = new DebugObject("Factory");
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
/*     */   static ContentMatcher createMatcher(int lastOrdinalPosition, Conjunction selector, ContentMatcher oldMatcher) {
/* 110 */     if (oldMatcher instanceof DifficultMatcher) {
/* 111 */       if (debug.debugIt(16)) {
/* 112 */         debug.debug(-153415734321212L, "createMatcher", "Reusing old DifficultMatcher with position " + oldMatcher.ordinalPosition + " for position " + lastOrdinalPosition);
/*     */       }
/*     */ 
/*     */       
/* 116 */       return oldMatcher;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 124 */     if (oldMatcher != null) {
/* 125 */       Assert.condition((oldMatcher.ordinalPosition >= lastOrdinalPosition));
/*     */     }
/* 127 */     if (selector != null) {
/* 128 */       for (int i = 0; i < selector.simpleTests.length; i++) {
/* 129 */         int newPos = (selector.simpleTests[i]).identifier.ordinalPosition;
/*     */         
/* 131 */         if (oldMatcher != null && newPos >= oldMatcher.ordinalPosition) {
/*     */ 
/*     */ 
/*     */           
/* 135 */           if (debug.debugIt(16)) {
/* 136 */             debug.debug(-153415734321212L, "createMatcher", "Reusing " + oldMatcher
/* 137 */                 .getClass().getName() + " for position " + lastOrdinalPosition + "; next test is at " + newPos);
/*     */           }
/*     */           
/* 140 */           return oldMatcher;
/*     */         } 
/*     */         
/* 143 */         if (newPos > lastOrdinalPosition) {
/*     */ 
/*     */           
/* 146 */           if (debug.debugIt(16)) {
/* 147 */             debug.debug(-153415734321212L, "createMatcher", "Creating new matcher at position " + newPos);
/*     */           }
/*     */           
/* 150 */           return createMatcher(selector.simpleTests[i], oldMatcher);
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 160 */     ContentMatcher ans = new DifficultMatcher(lastOrdinalPosition);
/* 161 */     if (debug.debugIt(16)) {
/* 162 */       if (oldMatcher != null) {
/* 163 */         debug.debug(-153415734321212L, "createMatcher", "New DifficultMatcher at position " + lastOrdinalPosition + " with successor " + oldMatcher
/*     */             
/* 165 */             .getClass().getName() + " at position " + oldMatcher.ordinalPosition);
/*     */       
/*     */       }
/* 168 */       else if (debug.debugIt(16)) {
/* 169 */         debug.debug(-153415734321212L, "createMatcher", "New DifficultMatcher at position " + lastOrdinalPosition + " with null successor.");
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 174 */     ans.vacantChild = oldMatcher;
/* 175 */     return ans;
/*     */   }
/*     */ 
/*     */   
/*     */   private static ContentMatcher createMatcher(SimpleTest test, ContentMatcher oldMatcher) {
/*     */     ContentMatcher ans;
/* 181 */     Identifier id = test.identifier;
/*     */     
/* 183 */     switch (test.kind) {
/*     */       case 4:
/*     */       case 5:
/* 186 */         switch (id.type)
/*     */         { case -6:
/* 188 */             if (debug.debugIt(16)) {
/* 189 */               debug.debug(-153415734321212L, "createMatcher", "New BooleanMatcher NULL/NOTNULL for id " + id.name);
/*     */             }
/*     */             
/* 192 */             ans = new BooleanMatcher(id);
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
/* 241 */             ans.vacantChild = oldMatcher;
/* 242 */             return ans;case 0: if (debug.debugIt(16)) debug.debug(-153415734321212L, "createMatcher", "New UnknownMatcher for id " + id.name);  ans = new UnknownMatcher(id); ans.vacantChild = oldMatcher; return ans;case -5: if (debug.debugIt(16)) debug.debug(-153415734321212L, "createMatcher", "New StringMatcher NULL/NOTNULL for id " + id.name);  ans = new StringMatcher(id); ans.vacantChild = oldMatcher; return ans; }  if (debug.debugIt(16)) debug.debug(-153415734321212L, "createMatcher", "New NumericMatcher NULL/NOTNULL for id " + id.name);  ans = new NumericMatcher(id); ans.vacantChild = oldMatcher; return ans;case 0: case 1: if (debug.debugIt(16)) debug.debug(-153415734321212L, "createMatcher", "New BooleanMatcher for id " + id.name);  ans = new BooleanMatcher(id); ans.vacantChild = oldMatcher; return ans;case 2: if (debug.debugIt(16)) debug.debug(-153415734321212L, "createMatcher", "New StringMatcher for id " + id.name);  ans = new StringMatcher(id); ans.vacantChild = oldMatcher; return ans;case 6: if (debug.debugIt(16)) debug.debug(-153415734321212L, "createMatcher", "New NumericMatcher for id " + id.name);  ans = new NumericMatcher(id); ans.vacantChild = oldMatcher; return ans;
/*     */     } 
/*     */     throw new IllegalStateException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static SimpleTest findTest(int ordinalPosition, Conjunction selector) {
/* 255 */     if (selector == null) {
/* 256 */       return null;
/*     */     }
/* 258 */     for (int i = 0; i < selector.simpleTests.length; i++) {
/* 259 */       SimpleTest cand = selector.simpleTests[i];
/* 260 */       if (cand.identifier.ordinalPosition == ordinalPosition) {
/* 261 */         return cand;
/*     */       }
/*     */     } 
/* 264 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\Factory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */