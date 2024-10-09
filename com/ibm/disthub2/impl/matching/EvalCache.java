/*     */ package com.ibm.disthub2.impl.matching;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EvalCache
/*     */   implements ClientExceptionConstants, ClientLogConstants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  83 */   private static final DebugObject debug = new DebugObject("EvalCache");
/*     */   
/*  85 */   int generation = 1;
/*     */ 
/*     */ 
/*     */   
/*     */   int[] cacheTag;
/*     */ 
/*     */ 
/*     */   
/*     */   Object[] cacheValue;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void prepareCache(int size) {
/*  99 */     if (debug.debugIt(32)) {
/* 100 */       debug.debug(-165922073994779L, "prepareCache");
/*     */     }
/*     */     
/* 103 */     int oldSize = (this.cacheTag == null) ? 0 : this.cacheTag.length;
/*     */     
/* 105 */     if (size <= oldSize) {
/*     */       
/* 107 */       if (this.generation == Integer.MAX_VALUE) {
/* 108 */         this.generation = 1;
/* 109 */         for (int i = 0; i < size; i++) {
/* 110 */           this.cacheTag[i] = 0;
/*     */         }
/*     */       } else {
/*     */         
/* 114 */         this.generation++;
/*     */       } 
/*     */       
/* 117 */       if (debug.debugIt(64)) {
/* 118 */         debug.debug(-142394261359015L, "prepareCache");
/*     */       }
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */     
/* 126 */     this.cacheTag = new int[2 * size];
/* 127 */     this.cacheValue = new Object[2 * size];
/*     */ 
/*     */     
/* 130 */     this.generation = 1;
/*     */     
/* 132 */     if (debug.debugIt(64)) {
/* 133 */       debug.debug(-142394261359015L, "prepareCache");
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
/*     */   public Object getExprValue(int id) {
/* 146 */     if (debug.debugIt(32)) {
/* 147 */       debug.debug(-165922073994779L, "getExprValue", Integer.valueOf(id));
/*     */     }
/*     */     
/* 150 */     Object result = null;
/* 151 */     if (this.cacheTag[id] == this.generation) {
/* 152 */       result = this.cacheValue[id];
/*     */     }
/*     */     
/* 155 */     if (debug.debugIt(64)) {
/* 156 */       debug.debug(-142394261359015L, "getExprValue", result);
/*     */     }
/*     */     
/* 159 */     return result;
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
/*     */   public void saveExprValue(int id, Object value) {
/* 171 */     if (debug.debugIt(32)) {
/* 172 */       debug.debug(-165922073994779L, "saveExprValue", Integer.valueOf(id), value);
/*     */     }
/*     */     
/* 175 */     this.cacheTag[id] = this.generation;
/* 176 */     this.cacheValue[id] = value;
/* 177 */     if (debug.debugIt(64))
/* 178 */       debug.debug(-142394261359015L, "saveExprValue"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\EvalCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */