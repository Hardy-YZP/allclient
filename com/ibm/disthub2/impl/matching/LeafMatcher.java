/*     */ package com.ibm.disthub2.impl.matching;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
/*     */ import com.ibm.disthub2.impl.matching.selector.Conjunction;
/*     */ import com.ibm.disthub2.impl.matching.selector.EvalContext;
/*     */ import com.ibm.disthub2.impl.util.FastVector;
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
/*     */ public class LeafMatcher
/*     */   extends Matcher
/*     */   implements ClientLogConstants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  55 */   private static final DebugObject debug = new DebugObject("LeafMatcher");
/*     */ 
/*     */ 
/*     */   
/*     */   FastVector[] lists;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   LeafMatcher() {
/*  65 */     if (debug.debugIt(32)) {
/*  66 */       debug.debug(-165922073994779L, "LeafMatcher");
/*     */     }
/*     */     
/*  69 */     this.lists = new FastVector[12];
/*     */ 
/*     */     
/*  72 */     if (debug.debugIt(64)) {
/*  73 */       debug.debug(-142394261359015L, "LeafMatcher");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void put(String topic, Conjunction selector, MatchTarget object, InternTable subExpr, MatchTarget[] targets) throws MatchingException {
/*  83 */     if (debug.debugIt(32)) {
/*  84 */       debug.debug(-165922073994779L, "put", topic, selector, object, subExpr, targets);
/*     */     }
/*     */     
/*  87 */     int type = object.type();
/*  88 */     if (this.lists[type] == null)
/*  89 */       this.lists[type] = new FastVector(); 
/*  90 */     object.setIndex((this.lists[type]).m_count);
/*  91 */     this.lists[type].addElement(object);
/*     */ 
/*     */     
/*  94 */     if (debug.debugIt(64)) {
/*  95 */       debug.debug(-142394261359015L, "put");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Matcher remove(String topic, Conjunction selector, MatchTarget object, InternTable subExpr, int parentId) throws MatchingException {
/* 104 */     int type = object.type();
/* 105 */     Matcher result = this;
/* 106 */     if (type < this.lists.length) {
/* 107 */       FastVector set = this.lists[type];
/* 108 */       if (set != null) {
/* 109 */         int index = object.getIndex();
/* 110 */         if (index < set.m_count && object == set.m_data[index]) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 115 */           if (set.m_count == 1) {
/* 116 */             set.m_count = 0;
/*     */           } else {
/* 118 */             MatchTarget toMove = (MatchTarget)set.m_data[set.m_count - 1];
/* 119 */             toMove.setIndex(index);
/* 120 */             set.m_data[index] = toMove;
/* 121 */             set.m_count--;
/*     */           } 
/*     */ 
/*     */           
/* 125 */           set.m_data[set.m_count] = null;
/* 126 */           object.invalidate();
/*     */ 
/*     */ 
/*     */           
/* 130 */           result = null;
/* 131 */           for (int i = 0; i < 12; i++) {
/* 132 */             FastVector theSet = this.lists[i];
/* 133 */             if (theSet != null && theSet.m_count > 0) {
/* 134 */               result = this;
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 143 */     if (debug.debugIt(64)) {
/* 144 */       debug.debug(-142394261359015L, "remove", result);
/*     */     }
/*     */     
/* 147 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void get(String topic, EvalContext msg, SearchResults result) throws MatchingException, BadMessageFormatMatchingException {
/* 156 */     if (debug.debugIt(32)) {
/* 157 */       debug.debug(-165922073994779L, "get", topic, msg, result);
/*     */     }
/*     */     
/* 160 */     result.addObjects(this.lists);
/*     */ 
/*     */     
/* 163 */     if (debug.debugIt(64)) {
/* 164 */       debug.debug(-142394261359015L, "get");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void merge(LeafMatcher cousin) {
/* 172 */     if (debug.debugIt(32)) {
/* 173 */       debug.debug(-165922073994779L, "merge", cousin);
/*     */     }
/*     */     
/* 176 */     for (int i = 0; i < this.lists.length; i++) {
/* 177 */       if (cousin.lists[i] != null) {
/* 178 */         if (this.lists[i] == null) {
/* 179 */           this.lists[i] = (FastVector)cousin.lists[i].clone();
/*     */         } else {
/* 181 */           this.lists[i].append(cousin.lists[i]);
/*     */         } 
/*     */       }
/*     */     } 
/* 185 */     if (debug.debugIt(64))
/* 186 */       debug.debug(-142394261359015L, "merge"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\LeafMatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */