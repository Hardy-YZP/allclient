/*     */ package com.ibm.disthub2.impl.matching;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
/*     */ import com.ibm.disthub2.impl.client.SessionConfig;
/*     */ import com.ibm.disthub2.impl.matching.selector.Conjunction;
/*     */ import com.ibm.disthub2.impl.matching.selector.EvalContext;
/*     */ import com.ibm.disthub2.impl.matching.selector.MatchParser;
/*     */ import com.ibm.disthub2.impl.matching.selector.PositionAssigner;
/*     */ import com.ibm.disthub2.impl.matching.selector.Resolver;
/*     */ import com.ibm.disthub2.impl.matching.selector.Selector;
/*     */ import com.ibm.disthub2.impl.matching.selector.Transformer;
/*     */ import com.ibm.disthub2.impl.util.FastVector;
/*     */ import com.ibm.disthub2.spi.ClientExceptionConstants;
/*     */ import com.ibm.disthub2.spi.ClientLogConstants;
/*     */ import com.ibm.disthub2.spi.ExceptionBuilder;
/*     */ import java.io.PrintWriter;
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
/*     */ 
/*     */ final class SubscriptionSpace
/*     */   implements ClientExceptionConstants, ClientLogConstants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/* 102 */   private static final DebugObject debug = new DebugObject("SubscriptionSpace");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Hashtable matchCache;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 112 */   volatile long wildGeneration = 0L;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Matcher root;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   AdminSpace admin;
/*     */ 
/*     */ 
/*     */   
/*     */   MatchSpace matchSpace;
/*     */ 
/*     */ 
/*     */   
/*     */   MatchParser parser;
/*     */ 
/*     */ 
/*     */   
/* 134 */   DefaultResolver defaultResolver = new DefaultResolver();
/*     */ 
/*     */   
/* 137 */   PositionAssigner positionAssigner = new PositionAssigner();
/*     */ 
/*     */ 
/*     */   
/* 141 */   SchemaTarget.Results schemaResults = new SchemaTarget.Results();
/*     */ 
/*     */   
/*     */   private int exactPuts;
/*     */ 
/*     */   
/*     */   private int wildPuts;
/*     */   
/*     */   private int resultCacheHitGets;
/*     */   
/*     */   private int wildCacheHitGets;
/*     */   
/*     */   private int wildCacheMissGets;
/*     */   
/*     */   private int exactMatches;
/*     */   
/*     */   private int resultsCached;
/*     */   
/*     */   private int removals;
/*     */   
/*     */   private int cacheCreates;
/*     */   
/*     */   private int cacheRemoves;
/*     */   
/*     */   private int optimisticGets;
/*     */   
/*     */   private int pessimisticGets;
/*     */   
/*     */   private int puntsDueToCache;
/*     */ 
/*     */   
/*     */   private class CacheEntry
/*     */   {
/*     */     Matcher exactMatcher;
/*     */     
/*     */     volatile long exactGeneration;
/*     */     
/*     */     Matcher[] wildMatchers;
/*     */     
/*     */     long wildGeneration;
/*     */     
/*     */     Object cachedResults;
/*     */     
/*     */     boolean noResultCache;
/*     */     
/*     */     AdminSpace.CacheEntry admins;
/*     */ 
/*     */     
/*     */     private CacheEntry() {}
/*     */   }
/*     */ 
/*     */   
/*     */   SubscriptionSpace(MatchSpace matchSpace, AdminSpace admin) {
/* 194 */     if (debug.debugIt(32)) {
/* 195 */       debug.debug(-165922073994779L, "SubscriptionSpace", matchSpace, admin);
/*     */     }
/*     */     
/* 198 */     this.matchSpace = matchSpace;
/* 199 */     this.admin = admin;
/* 200 */     this.matchCache = new Hashtable<>((SessionConfig.getSessionConfig()).MATCH_CACHE_INITIAL_CAPACITY);
/*     */     
/* 202 */     if (debug.debugIt(64)) {
/* 203 */       debug.debug(-142394261359015L, "SubscriptionSpace");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Conjunction[] put(String topic, boolean hasWilds, String selector, MatchTarget object, MatchTarget[] targets, Resolver resolver, InternTable subExpr, FastVector clones) throws QuerySyntaxException, MatchingException {
/* 256 */     if (debug.debugIt(32)) {
/* 257 */       debug.debug(-165922073994779L, "put", new Object[] { topic, 
/* 258 */             Boolean.valueOf(hasWilds), selector, object, targets, resolver, subExpr, clones });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 264 */     Conjunction[] expr = null;
/* 265 */     if (selector != null && selector.trim().length() != 0) {
/*     */       try {
/* 267 */         this.parser = MatchParser.prime(this.parser, selector, false);
/* 268 */         Selector parsed = this.parser.getSelector();
/* 269 */         if (parsed.type == 2) {
/* 270 */           throw new QuerySyntaxException(selector);
/*     */         }
/* 272 */         if (resolver == null) {
/* 273 */           resolver = chooseResolver(topic, hasWilds);
/*     */         }
/*     */         
/* 276 */         parsed = Transformer.resolve(parsed, resolver, this.positionAssigner);
/*     */         
/* 278 */         if (parsed.type == 2) {
/* 279 */           throw new QuerySyntaxException(selector);
/*     */         }
/* 281 */         expr = Transformer.organizeTests(Transformer.DNF(parsed));
/* 282 */         if (debug.debugIt(16)) {
/* 283 */           debug.debug(-153415734321212L, "put", parsed, "Back from organizeTests with " + expr);
/*     */         }
/*     */         
/* 286 */         if (debug.debugIt(16)) {
/* 287 */           if (expr == null) {
/* 288 */             debug.debug(-153415734321212L, "put", parsed, "transformed to true");
/* 289 */           } else if (expr.length == 0) {
/* 290 */             debug.debug(-153415734321212L, "put", parsed, "transformed to false");
/*     */           } else {
/* 292 */             for (int i = 0; i < expr.length; i++) {
/* 293 */               debug.debug(-153415734321212L, "put", parsed, "disjunct " + i, expr[i]);
/*     */             }
/*     */           }
/*     */         
/*     */         }
/* 298 */       } catch (RuntimeException e) {
/* 299 */         e.printStackTrace();
/* 300 */         throw new QuerySyntaxException(ExceptionBuilder.buildReasonString(-1200843245, new Object[] { e }));
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 310 */     if (expr != null && expr.length == 0) {
/* 311 */       return expr;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 319 */     if (expr != null && expr.length > 1) {
/* 320 */       for (int i = 0; i < expr.length; i++) {
/* 321 */         clones.addElement(object.duplicate());
/*     */       }
/*     */     } else {
/* 324 */       clones = null;
/*     */     } 
/*     */ 
/*     */     
/* 328 */     if (!hasWilds) {
/* 329 */       CacheEntry e = getCacheEntry(topic, true);
/* 330 */       e.exactGeneration++;
/* 331 */       Matcher exact = e.exactMatcher;
/*     */       
/* 333 */       ContentMatcher contentMatcher = null;
/* 334 */       if (exact == null || exact instanceof ContentMatcher) {
/* 335 */         contentMatcher = (ContentMatcher)exact;
/* 336 */         e.exactMatcher = exact = Factory.createMatcher(-1, (expr == null) ? null : expr[0], contentMatcher);
/*     */       } 
/*     */       
/* 339 */       e.noResultCache |= (expr != null) ? 1 : 0;
/* 340 */       e.cachedResults = null;
/*     */       try {
/* 342 */         if (expr == null) {
/* 343 */           exact.put(topic, null, object, subExpr, targets);
/* 344 */         } else if (expr.length == 1) {
/* 345 */           exact.put(topic, expr[0], object, subExpr, targets);
/*     */         } else {
/* 347 */           for (int i = 0; i < expr.length; i++) {
/* 348 */             if (i > 0 && 
/* 349 */               exact instanceof ContentMatcher) {
/* 350 */               contentMatcher = (ContentMatcher)exact;
/* 351 */               e.exactMatcher = exact = Factory.createMatcher(-1, expr[i], contentMatcher);
/*     */             } 
/*     */             
/* 354 */             exact.put(topic, expr[i], (MatchTarget)clones.m_data[i], subExpr, targets);
/*     */           }
/*     */         
/*     */         } 
/* 358 */       } catch (RuntimeException exc) {
/* 359 */         throw new MatchingException(ExceptionBuilder.buildReasonString(-1200843245, new Object[] { exc }));
/*     */       } finally {
/*     */         
/* 362 */         e.exactGeneration++;
/*     */       } 
/*     */       
/* 365 */       this.exactPuts++;
/*     */     }
/*     */     else {
/*     */       
/* 369 */       this.wildGeneration++;
/* 370 */       if (this.root == null) {
/* 371 */         this.root = new SubjectMatcher(true, false);
/*     */       }
/*     */       
/*     */       try {
/* 375 */         if (expr == null) {
/* 376 */           this.root.put(topic, null, object, subExpr, targets);
/* 377 */         } else if (expr.length == 1) {
/* 378 */           this.root.put(topic, expr[0], object, subExpr, targets);
/*     */         } else {
/* 380 */           for (int i = 0; i < expr.length; i++) {
/* 381 */             this.root.put(topic, expr[i], (MatchTarget)clones.m_data[i], subExpr, targets);
/*     */           }
/*     */         }
/*     */       
/* 385 */       } catch (RuntimeException e) {
/* 386 */         throw new MatchingException(ExceptionBuilder.buildReasonString(-1200843245, new Object[] { e }));
/*     */       } finally {
/*     */         
/* 389 */         this.wildGeneration++;
/*     */       } 
/*     */ 
/*     */       
/* 393 */       this.wildPuts++;
/*     */     } 
/*     */     
/* 396 */     if (debug.debugIt(64)) {
/* 397 */       debug.debug(-142394261359015L, "put", expr);
/*     */     }
/*     */     
/* 400 */     return expr;
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
/*     */   private Resolver chooseResolver(String topic, boolean hasWilds) throws MatchingException {
/* 415 */     SchemaTarget target = SchemaTarget.getSchemaTarget(topic, hasWilds, this.matchSpace, this.schemaResults);
/*     */     
/* 417 */     if (target == null || target.schema == null) {
/* 418 */       return this.defaultResolver;
/*     */     }
/* 420 */     return this.schemaResults.target.schema;
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
/*     */   CacheEntry getCacheEntry(String topic, boolean create) throws MatchingException {
/* 436 */     if (debug.debugIt(32)) {
/* 437 */       debug.debug(-165922073994779L, "getCacheEntry", topic, Boolean.valueOf(create));
/*     */     }
/*     */     
/* 440 */     CacheEntry e = (CacheEntry)this.matchCache.get(topic);
/* 441 */     if (e == null && 
/* 442 */       create) {
/* 443 */       e = new CacheEntry();
/* 444 */       e.admins = this.admin.getLocked(topic);
/* 445 */       e.admins.refCount++;
/*     */ 
/*     */       
/* 448 */       this.matchCache.put(topic, e);
/* 449 */       this.cacheCreates++;
/*     */     } 
/*     */ 
/*     */     
/* 453 */     if (debug.debugIt(64)) {
/* 454 */       debug.debug(-142394261359015L, "getCacheEntry", e);
/*     */     }
/*     */     
/* 457 */     return e;
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
/*     */   void get(String topic, EvalContext msg, SearchResults result) throws MatchingException, BadMessageFormatMatchingException {
/* 475 */     if (debug.debugIt(32)) {
/* 476 */       debug.debug(-165922073994779L, "get", topic, msg, result);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 481 */     CacheEntry e = getCacheEntry(topic, false);
/* 482 */     if (e == null) {
/* 483 */       this.puntsDueToCache++;
/* 484 */       pessimisticGet(topic, msg, result);
/*     */       
/* 486 */       if (debug.debugIt(64)) {
/* 487 */         debug.debug(-142394261359015L, "get");
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 499 */     long wildG = this.wildGeneration;
/* 500 */     long exactG = e.exactGeneration;
/* 501 */     long adminWildG = this.admin.wildGeneration;
/* 502 */     long adminExactG = e.admins.exactGeneration;
/*     */ 
/*     */ 
/*     */     
/* 506 */     if ((wildG & 0x1L) == 1L || (exactG & 0x1L) == 1L || (adminWildG & 0x1L) == 1L || (adminExactG & 0x1L) == 1L) {
/*     */       
/* 508 */       pessimisticGet(topic, msg, result);
/*     */       
/* 510 */       if (debug.debugIt(64)) {
/* 511 */         debug.debug(-142394261359015L, "get");
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 523 */     if (wildG != e.wildGeneration || (!e.noResultCache && e.cachedResults == null) || adminWildG != e.admins.wildGeneration || e.admins.consolidated == null) {
/*     */       
/* 525 */       this.puntsDueToCache++;
/* 526 */       pessimisticGet(topic, msg, result);
/*     */       
/* 528 */       if (debug.debugIt(64)) {
/* 529 */         debug.debug(-142394261359015L, "get");
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 542 */     synchronized (result) {
/*     */     
/*     */     } 
/*     */ 
/*     */     
/* 547 */     Throwable oops = null;
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 552 */       if (e.cachedResults != null && result.acceptCacheable(e.cachedResults)) {
/*     */ 
/*     */         
/* 555 */         this.resultCacheHitGets++;
/*     */       } else {
/*     */         
/* 558 */         msg.prepareCache(this.matchSpace.subExpr.evalCacheSize());
/*     */ 
/*     */         
/* 561 */         if (e.wildMatchers != null) {
/*     */           
/* 563 */           for (int i = 0; i < e.wildMatchers.length; i++) {
/* 564 */             if (debug.debugIt(16)) {
/* 565 */               debug.debug(-153415734321212L, "get", "Do wild match against" + e.wildMatchers[i]);
/*     */             }
/*     */             
/* 568 */             e.wildMatchers[i].get(null, msg, result);
/*     */           } 
/* 570 */           this.wildCacheHitGets++;
/*     */         } 
/*     */         
/* 573 */         if (e.exactMatcher != null) {
/* 574 */           if (debug.debugIt(16)) {
/* 575 */             debug.debug(-153415734321212L, "get", "Do exact match against matcher: " + e.exactMatcher);
/*     */           }
/* 577 */           e.exactMatcher.get(null, msg, result);
/* 578 */           this.exactMatches++;
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 583 */         e.admins.consolidated.get(null, null, result);
/*     */       }
/*     */     
/* 586 */     } catch (RuntimeException exc) {
/*     */       
/* 588 */       oops = exc;
/*     */     }
/* 590 */     catch (Error exc) {
/*     */       
/* 592 */       oops = exc;
/*     */     } 
/*     */ 
/*     */     
/* 596 */     if (wildG != this.wildGeneration || exactG != e.exactGeneration || adminWildG != this.admin.wildGeneration || adminExactG != e.admins.exactGeneration) {
/*     */       
/* 598 */       result.reset();
/* 599 */       pessimisticGet(topic, msg, result);
/*     */       
/* 601 */       if (debug.debugIt(64)) {
/* 602 */         debug.debug(-142394261359015L, "get");
/*     */       }
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */     
/* 610 */     if (oops != null) {
/* 611 */       throw new MatchingException(ExceptionBuilder.buildReasonString(-1200843245, new Object[] { oops }));
/*     */     }
/*     */ 
/*     */     
/* 615 */     this.optimisticGets++;
/*     */     
/* 617 */     if (debug.debugIt(64)) {
/* 618 */       debug.debug(-142394261359015L, "get");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void pessimisticGet(String topic, EvalContext msg, SearchResults result) throws MatchingException, BadMessageFormatMatchingException {
/* 628 */     if (debug.debugIt(32)) {
/* 629 */       debug.debug(-165922073994779L, "pessimisticGet", topic, msg, result);
/*     */     }
/*     */     
/* 632 */     synchronized (this.matchSpace) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 637 */       CacheEntry e = getCacheEntry(topic, true);
/* 638 */       if (e.wildGeneration != this.wildGeneration || e.admins.wildGeneration != this.admin.wildGeneration) {
/* 639 */         e.cachedResults = null;
/* 640 */         e.wildMatchers = null;
/*     */       } 
/*     */ 
/*     */       
/* 644 */       if (e.cachedResults != null && result.acceptCacheable(e.cachedResults)) {
/* 645 */         this.resultCacheHitGets++;
/* 646 */         this.pessimisticGets++;
/*     */         
/* 648 */         if (debug.debugIt(64)) {
/* 649 */           debug.debug(-142394261359015L, "pessimisticGet");
/*     */         }
/*     */ 
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 656 */       msg.prepareCache(this.matchSpace.subExpr.evalCacheSize());
/*     */ 
/*     */       
/* 659 */       if (e.wildMatchers != null) {
/*     */         
/* 661 */         for (int i = 0; i < e.wildMatchers.length; i++) {
/*     */           try {
/* 663 */             e.wildMatchers[i].get(null, msg, result);
/*     */           }
/* 665 */           catch (RuntimeException exc) {
/* 666 */             throw new MatchingException(ExceptionBuilder.buildReasonString(-1200843245, new Object[] { exc }));
/*     */           } 
/*     */         } 
/* 669 */         this.wildCacheHitGets++;
/*     */       
/*     */       }
/* 672 */       else if (this.root != null) {
/* 673 */         CacheingSearchResults csr = new CacheingSearchResults(result);
/*     */         try {
/* 675 */           this.root.get(topic, msg, csr);
/*     */         }
/* 677 */         catch (RuntimeException exc) {
/* 678 */           throw new MatchingException(ExceptionBuilder.buildReasonString(-1200843245, new Object[] { exc }));
/*     */         } 
/* 680 */         e.wildMatchers = csr.getMatchers();
/* 681 */         e.wildGeneration = this.wildGeneration;
/* 682 */         e.noResultCache |= csr.hasContent;
/* 683 */         this.wildCacheMissGets++;
/*     */       } 
/*     */ 
/*     */       
/* 687 */       if (e.exactMatcher != null) {
/*     */         try {
/* 689 */           e.exactMatcher.get(null, msg, result);
/*     */         }
/* 691 */         catch (RuntimeException exc) {
/* 692 */           throw new MatchingException(ExceptionBuilder.buildReasonString(-1200843245, new Object[] { exc }));
/*     */         } 
/* 694 */         this.exactMatches++;
/*     */       } 
/*     */       
/* 697 */       e.admins = this.admin.getLocked(topic);
/* 698 */       e.admins.consolidated.get(null, null, result);
/*     */ 
/*     */       
/* 701 */       if (!e.noResultCache) {
/* 702 */         e.cachedResults = result.provideCacheable(topic);
/* 703 */         if (e.cachedResults != null) {
/* 704 */           this.resultsCached++;
/*     */         }
/*     */       } 
/*     */       
/* 708 */       this.pessimisticGets++;
/*     */       
/* 710 */       if (debug.debugIt(64)) {
/* 711 */         debug.debug(-142394261359015L, "pessimisticGet");
/*     */       }
/*     */       return;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void remove(String topic, boolean hasWilds, Conjunction[] query, MatchTarget object, InternTable subExpr, FastVector clones) throws MatchingException {
/* 753 */     if (debug.debugIt(32)) {
/* 754 */       debug.debug(-165922073994779L, "remove", topic, Boolean.valueOf(hasWilds), query, object, subExpr);
/*     */     }
/*     */     
/* 757 */     if (hasWilds) {
/*     */       
/* 759 */       if (this.root == null) {
/* 760 */         throw new MatchingException(ExceptionBuilder.buildReasonString(-1617888306, new Object[] { topic, object }));
/*     */       }
/* 762 */       this.wildGeneration++;
/*     */       try {
/* 764 */         if (query == null) {
/* 765 */           this.root = this.root.remove(topic, null, object, subExpr, -1);
/* 766 */         } else if (query.length == 1) {
/* 767 */           this.root = this.root.remove(topic, query[0], object, subExpr, -1);
/*     */         } else {
/* 769 */           for (int i = 0; i < query.length; i++) {
/* 770 */             this.root = this.root.remove(topic, query[i], (MatchTarget)clones.m_data[i], subExpr, -1);
/*     */           }
/*     */         }
/*     */       
/* 774 */       } catch (RuntimeException e) {
/* 775 */         throw new MatchingException(ExceptionBuilder.buildReasonString(-1200843245, new Object[] { e }));
/*     */       } finally {
/*     */         
/* 778 */         this.wildGeneration++;
/*     */       } 
/*     */     } else {
/*     */       
/* 782 */       CacheEntry e = (CacheEntry)this.matchCache.get(topic);
/*     */       
/* 784 */       if (debug.debugIt(16)) {
/* 785 */         debug.debug(-153415734321212L, "remove", "About to process removal of exact matcher:" + e.exactMatcher);
/*     */       }
/*     */       
/* 788 */       if (e == null || e.exactMatcher == null) {
/* 789 */         throw new MatchingException(ExceptionBuilder.buildReasonString(-980208654, new Object[] { topic, object }));
/*     */       }
/* 791 */       e.exactGeneration++;
/*     */       
/*     */       try {
/* 794 */         if (query == null) {
/* 795 */           if (debug.debugIt(16)) {
/* 796 */             debug.debug(-153415734321212L, "remove", "Null query remove");
/*     */           }
/*     */           
/* 799 */           e.exactMatcher = e.exactMatcher.remove(topic, null, object, subExpr, -1);
/* 800 */         } else if (query.length == 1) {
/* 801 */           if (debug.debugIt(16)) {
/* 802 */             debug.debug(-153415734321212L, "remove", "Process matcher remove query for: " + query[0]);
/*     */           }
/*     */           
/* 805 */           e.exactMatcher = e.exactMatcher.remove(topic, query[0], object, subExpr, -1);
/*     */         } else {
/* 807 */           for (int i = 0; i < query.length; i++) {
/* 808 */             if (debug.debugIt(16)) {
/* 809 */               debug.debug(-153415734321212L, "remove", "Process matcher remove query for: " + query[i]);
/*     */             }
/*     */             
/* 812 */             e.exactMatcher = e.exactMatcher.remove(topic, query[i], (MatchTarget)clones.m_data[i], subExpr, -1);
/*     */           }
/*     */         
/*     */         }
/*     */       
/* 817 */       } catch (RuntimeException exc) {
/* 818 */         e.exactGeneration++;
/*     */         
/* 820 */         debug.debugX(exc);
/* 821 */         throw new MatchingException(ExceptionBuilder.buildReasonString(-1200843245, new Object[] { exc }));
/*     */       } 
/*     */       
/* 824 */       if (debug.debugIt(16)) {
/* 825 */         debug.debug(-153415734321212L, "remove", "Exact matcher is now: " + e.exactMatcher);
/*     */       }
/*     */       
/* 828 */       if (e.exactMatcher == null && (
/* 829 */         e.wildMatchers == null || e.wildGeneration != this.wildGeneration || e.wildMatchers.length == 0)) {
/*     */ 
/*     */         
/* 832 */         this.matchCache.remove(topic);
/* 833 */         e.admins.refCount--;
/* 834 */         if (e.admins.refCount == 0) {
/* 835 */           this.admin.unCache(topic, e.admins);
/*     */         }
/* 837 */         this.cacheRemoves++;
/*     */       } 
/*     */       
/* 840 */       e.cachedResults = null;
/* 841 */       e.exactGeneration++;
/*     */     } 
/* 843 */     this.removals++;
/*     */     
/* 845 */     if (debug.debugIt(64)) {
/* 846 */       debug.debug(-142394261359015L, "remove");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void statistics(PrintWriter wtr) {
/* 854 */     int truePessimisticGets = this.pessimisticGets - this.puntsDueToCache;
/* 855 */     wtr.println("Exact puts: " + this.exactPuts + ", Wildcard generation: " + this.wildGeneration + ", Wildcard puts: " + this.wildPuts + ", Wildcard-Cache-hit gets: " + this.wildCacheHitGets + ", Wildcard-Cache-miss gets: " + this.wildCacheMissGets + ", Result-Cache-hit gets: " + this.resultCacheHitGets + ", Exact matches: " + this.exactMatches + ", Results cached: " + this.resultsCached + ", Removals:" + this.removals + ", Cache entries created:" + this.cacheCreates + ", Cache entries removed:" + this.cacheRemoves + ", Optimistic gets:" + this.optimisticGets + ", True Pessimistic gets:" + truePessimisticGets + ", Mutating gets:" + this.puntsDueToCache);
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
/*     */   void clear() {
/* 879 */     if (debug.debugIt(32)) {
/* 880 */       debug.debug(-165922073994779L, "clear");
/*     */     }
/*     */     
/* 883 */     this.root = null;
/* 884 */     this.wildGeneration = 0L;
/* 885 */     this.matchCache = new Hashtable<>((SessionConfig.getSessionConfig()).MATCH_CACHE_INITIAL_CAPACITY);
/*     */     
/* 887 */     if (debug.debugIt(64))
/* 888 */       debug.debug(-142394261359015L, "clear"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\SubscriptionSpace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */