/*     */ package com.ibm.disthub2.impl.matching;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
/*     */ import com.ibm.disthub2.spi.ClientExceptionConstants;
/*     */ import com.ibm.disthub2.spi.ClientLogConstants;
/*     */ import com.ibm.disthub2.spi.ExceptionBuilder;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class AdminSpace
/*     */   implements ClientExceptionConstants, ClientLogConstants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  91 */   private static final DebugObject debug = new DebugObject("AdminSpace");
/*     */ 
/*     */ 
/*     */   
/*  95 */   Hashtable matchCache = new Hashtable<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 101 */   volatile long wildGeneration = 0L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   SubjectMatcher root;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class CacheEntry
/*     */   {
/*     */     LeafMatcher exactMatcher;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     volatile long exactGeneration;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     Matcher[] wildMatchers;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     long wildGeneration;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     LeafMatcher consolidated;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     int refCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void put(String topic, boolean hasWilds, MatchTarget object) throws MatchingException {
/* 149 */     if (debug.debugIt(32)) {
/* 150 */       debug.debug(-165922073994779L, "put", topic, Boolean.valueOf(hasWilds), object);
/*     */     }
/*     */ 
/*     */     
/* 154 */     if (!hasWilds) {
/* 155 */       CacheEntry e = createCacheEntry(topic);
/* 156 */       e.exactGeneration++;
/* 157 */       LeafMatcher exact = e.exactMatcher;
/* 158 */       if (exact == null) {
/* 159 */         e.exactMatcher = exact = new LeafMatcher();
/*     */       }
/* 161 */       e.consolidated = null;
/*     */       try {
/* 163 */         exact.put(topic, null, object, null, null);
/*     */       }
/* 165 */       catch (RuntimeException exc) {
/* 166 */         throw new MatchingException(ExceptionBuilder.buildReasonString(-1200843245, new Object[] { exc }));
/*     */       } finally {
/*     */         
/* 169 */         e.exactGeneration++;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 174 */       this.wildGeneration++;
/* 175 */       if (this.root == null) {
/* 176 */         this.root = new SubjectMatcher(true, true);
/*     */       }
/*     */       
/*     */       try {
/* 180 */         this.root.put(topic, null, object, null, null);
/*     */       }
/* 182 */       catch (RuntimeException e) {
/* 183 */         throw new MatchingException(ExceptionBuilder.buildReasonString(-1200843245, new Object[] { e }));
/*     */       } finally {
/*     */         
/* 186 */         this.wildGeneration++;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 191 */     if (debug.debugIt(64)) {
/* 192 */       debug.debug(-142394261359015L, "put");
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
/*     */   CacheEntry get(String topic) {
/* 204 */     if (debug.debugIt(32)) {
/* 205 */       debug.debug(-165922073994779L, "get", topic);
/*     */     }
/*     */     
/* 208 */     CacheEntry result = (CacheEntry)this.matchCache.get(topic);
/*     */     
/* 210 */     if (debug.debugIt(64)) {
/* 211 */       debug.debug(-142394261359015L, "get", result);
/*     */     }
/*     */     
/* 214 */     return result;
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
/*     */   private CacheEntry createCacheEntry(String topic) {
/* 227 */     if (debug.debugIt(32)) {
/* 228 */       debug.debug(-165922073994779L, "createCachEntry", topic);
/*     */     }
/*     */     
/* 231 */     CacheEntry e = (CacheEntry)this.matchCache.get(topic);
/* 232 */     if (e == null) {
/* 233 */       e = new CacheEntry();
/* 234 */       this.matchCache.put(topic, e);
/*     */     } 
/*     */     
/* 237 */     if (debug.debugIt(64)) {
/* 238 */       debug.debug(-142394261359015L, "createCacheEntry", e);
/*     */     }
/*     */     
/* 241 */     return e;
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
/*     */ 
/*     */   
/*     */   void unCache(String topic, CacheEntry entry) {
/* 256 */     if (entry.refCount == 0 && entry.exactMatcher == null) {
/* 257 */       this.matchCache.remove(topic);
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
/*     */ 
/*     */   
/*     */   CacheEntry getLocked(String topic) throws MatchingException {
/* 273 */     if (debug.debugIt(32)) {
/* 274 */       debug.debug(-165922073994779L, "getLocked", topic);
/*     */     }
/*     */     
/* 277 */     CacheEntry e = createCacheEntry(topic);
/* 278 */     if (e.wildGeneration != this.wildGeneration) {
/* 279 */       e.consolidated = null;
/* 280 */       e.wildMatchers = null;
/*     */     } 
/*     */ 
/*     */     
/* 284 */     if (e.consolidated != null) {
/* 285 */       if (debug.debugIt(64)) {
/* 286 */         debug.debug(-142394261359015L, "getLocked", e);
/*     */       }
/*     */       
/* 289 */       return e;
/*     */     } 
/*     */     
/* 292 */     if (e.wildMatchers == null && this.root != null) {
/* 293 */       CacheingSearchResults csr = new CacheingSearchResults(null);
/*     */       try {
/* 295 */         this.root.get(topic, null, csr);
/*     */       }
/* 297 */       catch (RuntimeException exc) {
/* 298 */         throw new MatchingException(ExceptionBuilder.buildReasonString(-1200843245, new Object[] { exc }));
/*     */       }
/* 300 */       catch (BadMessageFormatMatchingException bexc) {
/*     */         
/* 302 */         throw new MatchingException(ExceptionBuilder.buildReasonString(-1200843245, new Object[] { bexc }));
/*     */       } 
/* 304 */       e.wildMatchers = csr.getMatchers();
/* 305 */       e.wildGeneration = this.wildGeneration;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 310 */     LeafMatcher consol = new LeafMatcher();
/* 311 */     if (e.wildMatchers != null) {
/* 312 */       for (int i = 0; i < e.wildMatchers.length; i++) {
/* 313 */         consol.merge((LeafMatcher)e.wildMatchers[i]);
/*     */       }
/*     */     }
/* 316 */     if (e.exactMatcher != null) {
/* 317 */       consol.merge(e.exactMatcher);
/*     */     }
/* 319 */     e.consolidated = consol;
/*     */     
/* 321 */     if (debug.debugIt(64)) {
/* 322 */       debug.debug(-142394261359015L, "getLocked", e);
/*     */     }
/*     */     
/* 325 */     return e;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void remove(String topic, boolean hasWilds, MatchTarget object) throws MatchingException {
/* 349 */     if (debug.debugIt(32)) {
/* 350 */       debug.debug(-165922073994779L, "remove", topic, Boolean.valueOf(hasWilds), object);
/*     */     }
/*     */     
/* 353 */     if (hasWilds) {
/*     */       
/* 355 */       if (this.root == null) {
/* 356 */         throw new MatchingException(ExceptionBuilder.buildReasonString(-1617888306, new Object[] { topic, object }));
/*     */       }
/* 358 */       this.wildGeneration++;
/*     */       try {
/* 360 */         this.root = (SubjectMatcher)this.root.remove(topic, null, object, null, -1);
/*     */       }
/* 362 */       catch (RuntimeException e) {
/* 363 */         throw new MatchingException(ExceptionBuilder.buildReasonString(-1200843245, new Object[] { e }));
/*     */       } finally {
/*     */         
/* 366 */         this.wildGeneration++;
/*     */       } 
/*     */     } else {
/*     */       
/* 370 */       CacheEntry e = (CacheEntry)this.matchCache.get(topic);
/*     */       
/* 372 */       if (e == null || e.exactMatcher == null) {
/* 373 */         throw new MatchingException(ExceptionBuilder.buildReasonString(-980208654, new Object[] { topic, object }));
/*     */       }
/*     */       
/* 376 */       e.exactGeneration++;
/*     */       
/*     */       try {
/* 379 */         e.exactMatcher = (LeafMatcher)e.exactMatcher.remove(topic, null, object, null, -1);
/*     */       }
/* 381 */       catch (RuntimeException exc) {
/* 382 */         e.exactGeneration++;
/* 383 */         throw new MatchingException(ExceptionBuilder.buildReasonString(-1200843245, new Object[] { exc }));
/*     */       } 
/*     */       
/* 386 */       if (e.exactMatcher == null && 
/* 387 */         e.refCount == 0 && (e.wildMatchers == null || e.wildGeneration != this.wildGeneration || e.wildMatchers.length == 0))
/*     */       {
/*     */ 
/*     */         
/* 391 */         this.matchCache.remove(topic);
/*     */       }
/*     */       
/* 394 */       e.consolidated = null;
/*     */       
/* 396 */       e.exactGeneration++;
/*     */     } 
/*     */     
/* 399 */     if (debug.debugIt(64)) {
/* 400 */       debug.debug(-142394261359015L, "remove");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void clear() {
/* 410 */     if (debug.debugIt(32)) {
/* 411 */       debug.debug(-165922073994779L, "clear");
/*     */     }
/*     */     
/* 414 */     this.root = null;
/* 415 */     this.wildGeneration = 0L;
/* 416 */     this.matchCache = new Hashtable<>();
/*     */     
/* 418 */     if (debug.debugIt(64))
/* 419 */       debug.debug(-142394261359015L, "clear"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\AdminSpace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */