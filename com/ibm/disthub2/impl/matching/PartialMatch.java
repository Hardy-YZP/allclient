/*     */ package com.ibm.disthub2.impl.matching;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class PartialMatch
/*     */   implements ClientLogConstants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  39 */   private static final DebugObject debug = new DebugObject("PartialMatch");
/*     */   
/*     */   PartialMatch next;
/*     */   
/*     */   String key;
/*     */   
/*     */   int keylen;
/*     */   
/*     */   Matcher hashChild;
/*     */   
/*     */   Matcher starChild;
/*     */ 
/*     */   
/*     */   public PartialMatch() {
/*  53 */     this.key = "";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private PartialMatch(String key, PartialMatch next) {
/*  59 */     if (debug.debugIt(32)) {
/*  60 */       debug.debug(-165922073994779L, "PartialMatch", key, next);
/*     */     }
/*     */ 
/*     */     
/*  64 */     this.key = key;
/*  65 */     this.keylen = key.length();
/*  66 */     this.next = next;
/*     */ 
/*     */     
/*  69 */     if (debug.debugIt(64)) {
/*  70 */       debug.debug(-142394261359015L, "PartialMatch");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   PartialMatch get(String key) {
/*  79 */     if (debug.debugIt(32)) {
/*  80 */       debug.debug(-165922073994779L, "get", key);
/*     */     }
/*     */ 
/*     */     
/*  84 */     int matchlen = key.length();
/*  85 */     for (PartialMatch pm = this;; pm = pm.next) {
/*  86 */       if (pm.key.equals(key)) {
/*     */         
/*  88 */         if (debug.debugIt(64)) {
/*  89 */           debug.debug(-142394261359015L, "get", pm);
/*     */         }
/*     */ 
/*     */         
/*  93 */         return pm;
/*     */       } 
/*  95 */       if (pm.next == null || pm.next.keylen > matchlen) {
/*     */ 
/*     */         
/*  98 */         pm.next = new PartialMatch(key, pm.next);
/*     */ 
/*     */         
/* 101 */         if (debug.debugIt(64)) {
/* 102 */           debug.debug(-142394261359015L, "get", pm.next);
/*     */         }
/*     */ 
/*     */         
/* 106 */         return pm.next;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void remove(PartialMatch pm) {
/* 114 */     if (debug.debugIt(32)) {
/* 115 */       debug.debug(-165922073994779L, "remove", pm);
/*     */     }
/*     */ 
/*     */     
/* 119 */     if (pm == this) {
/*     */       return;
/*     */     }
/* 122 */     for (PartialMatch p = this; p.next != null; p = p.next) {
/* 123 */       if (p.next == pm) {
/* 124 */         p.next = pm.next;
/*     */ 
/*     */         
/* 127 */         if (debug.debugIt(64)) {
/* 128 */           debug.debug(-142394261359015L, "remove");
/*     */         }
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*     */     
/* 135 */     Assert.failure("Partial Match chain damage detected");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isEmpty() {
/* 141 */     if (debug.debugIt(32)) {
/* 142 */       debug.debug(-165922073994779L, "isEmpty");
/*     */     }
/*     */ 
/*     */     
/* 146 */     boolean result = (this.keylen > 0 && this.hashChild == null && this.starChild == null);
/*     */ 
/*     */     
/* 149 */     if (debug.debugIt(64)) {
/* 150 */       debug.debug(-142394261359015L, "isEmpty", Boolean.valueOf(result));
/*     */     }
/*     */ 
/*     */     
/* 154 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isEmptyChain() {
/* 160 */     if (debug.debugIt(32)) {
/* 161 */       debug.debug(-165922073994779L, "isEmptyChain");
/*     */     }
/*     */ 
/*     */     
/* 165 */     boolean result = (this.next == null && this.hashChild == null && this.starChild == null);
/*     */ 
/*     */     
/* 168 */     if (debug.debugIt(64)) {
/* 169 */       debug.debug(-142394261359015L, "isEmptyChain", Boolean.valueOf(result));
/*     */     }
/*     */ 
/*     */     
/* 173 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\PartialMatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */