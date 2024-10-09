/*     */ package com.ibm.disthub2.impl.matching;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
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
/*     */ class CacheingSearchResults
/*     */   implements SearchResults, ClientLogConstants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  39 */   private static final DebugObject debug = new DebugObject("CacheingSearchResults");
/*     */   
/*     */   private SearchResults delegate;
/*     */   
/*     */   private FastVector wildMatchers;
/*     */   
/*     */   boolean hasContent;
/*     */ 
/*     */   
/*     */   CacheingSearchResults(SearchResults delegate) {
/*  49 */     if (debug.debugIt(32)) {
/*  50 */       debug.debug(-165922073994779L, "CacheingSearchResults", delegate);
/*     */     }
/*     */     
/*  53 */     this.delegate = delegate;
/*  54 */     this.wildMatchers = new FastVector();
/*     */ 
/*     */     
/*  57 */     if (debug.debugIt(64)) {
/*  58 */       debug.debug(-142394261359015L, "CacheingSearchResults");
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
/*     */   public void addObjects(FastVector[] objects) {
/*  70 */     if (debug.debugIt(32)) {
/*  71 */       debug.debug(-165922073994779L, "addObjects", objects);
/*     */     }
/*     */     
/*  74 */     if (this.delegate != null) {
/*  75 */       this.delegate.addObjects(objects);
/*     */     }
/*     */     
/*  78 */     if (debug.debugIt(64)) {
/*  79 */       debug.debug(-142394261359015L, "addObjects");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Object provideCacheable(String topic) {
/*  85 */     return null; } public boolean acceptCacheable(Object cached) {
/*  86 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void setMatcher(Matcher m, boolean hasContent) {
/*  92 */     if (debug.debugIt(32)) {
/*  93 */       debug.debug(-165922073994779L, "setMatcher", m, Boolean.valueOf(hasContent));
/*     */     }
/*     */     
/*  96 */     this.wildMatchers.addElement(m);
/*  97 */     this.hasContent |= hasContent;
/*     */ 
/*     */     
/* 100 */     if (debug.debugIt(64)) {
/* 101 */       debug.debug(-142394261359015L, "setMatcher");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reset() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Matcher[] getMatchers() {
/* 114 */     if (debug.debugIt(32)) {
/* 115 */       debug.debug(-165922073994779L, "getMatchers");
/*     */     }
/*     */     
/* 118 */     Matcher[] ans = new Matcher[this.wildMatchers.m_count];
/* 119 */     System.arraycopy(this.wildMatchers.m_data, 0, ans, 0, ans.length);
/*     */ 
/*     */     
/* 122 */     if (debug.debugIt(64)) {
/* 123 */       debug.debug(-142394261359015L, "getMatchers", ans);
/*     */     }
/*     */     
/* 126 */     return ans;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\CacheingSearchResults.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */