/*     */ package com.ibm.disthub2.impl.matching;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
/*     */ import com.ibm.disthub2.impl.matching.selector.Conjunction;
/*     */ import com.ibm.disthub2.impl.matching.selector.EvalContext;
/*     */ import com.ibm.disthub2.impl.util.Assert;
/*     */ import com.ibm.disthub2.spi.ClientLogConstants;
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
/*     */ final class SubjectMatcher
/*     */   extends Matcher
/*     */   implements ClientLogConstants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2004 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  90 */   private static final DebugObject debug = new DebugObject("AdminSpace");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Hashtable exactMatches;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private PartialMatch partMatches;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean noContent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   SubjectMatcher(boolean root, boolean noContent) {
/* 114 */     if (debug.debugIt(32)) {
/* 115 */       debug.debug(-165922073994779L, "SubjectMatcher", Boolean.valueOf(root), Boolean.valueOf(noContent));
/*     */     }
/*     */ 
/*     */     
/* 119 */     if (!root) {
/* 120 */       this.exactMatches = new Hashtable<>();
/*     */     }
/* 122 */     this.noContent = noContent;
/* 123 */     this.partMatches = new PartialMatch();
/*     */ 
/*     */     
/* 126 */     if (debug.debugIt(64)) {
/* 127 */       debug.debug(-142394261359015L, "SubjectMatcher");
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
/*     */   public void put(String topic, Conjunction selector, MatchTarget object, InternTable subExpr, MatchTarget[] targets) throws MatchingException {
/* 149 */     if (debug.debugIt(32)) {
/* 150 */       debug.debug(-165922073994779L, "put", topic, selector, object, subExpr, targets);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 155 */     int starpos = findFirstMatchOneWildcard(topic);
/* 156 */     int hashpos = findFirstMatchManyWildcard(topic);
/* 157 */     Matcher next = null;
/*     */     
/* 159 */     if (starpos == -1 && hashpos == -1) {
/*     */ 
/*     */       
/* 162 */       next = (Matcher)this.exactMatches.get(topic);
/* 163 */       if (next == null) {
/* 164 */         next = new CacheingMatcher(this.noContent ? new LeafMatcher() : null);
/* 165 */         this.exactMatches.put(topic, next);
/*     */       } 
/*     */       
/* 168 */       topic = "";
/*     */     }
/* 170 */     else if (starpos == -1 || (hashpos != -1 && hashpos < starpos)) {
/*     */ 
/*     */       
/* 173 */       PartialMatch pm = this.partMatches.get(topic.substring(0, (hashpos > 0) ? (hashpos - 1) : 0));
/*     */ 
/*     */       
/* 176 */       if (topic.length() - hashpos > 2) {
/* 177 */         topic = topic.substring(hashpos + 2);
/*     */       } else {
/*     */         
/* 180 */         topic = "";
/*     */       } 
/* 182 */       next = pm.hashChild;
/* 183 */       if (next == null) {
/* 184 */         pm.hashChild = next = new InverseSubjectMatcher(this.noContent);
/*     */       
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 190 */       PartialMatch pm = this.partMatches.get(topic.substring(0, (starpos > 0) ? (starpos - 1) : 0));
/*     */ 
/*     */       
/* 193 */       if (topic.length() - starpos > 2) {
/* 194 */         topic = topic.substring(starpos + 2);
/*     */       } else {
/*     */         
/* 197 */         topic = "";
/*     */       } 
/* 199 */       next = pm.starChild;
/* 200 */       if (next == null) {
/* 201 */         pm.starChild = next = new SubjectMatcher(false, this.noContent);
/*     */       }
/*     */     } 
/*     */     
/* 205 */     next.put(topic, selector, object, subExpr, targets);
/*     */ 
/*     */     
/* 208 */     if (debug.debugIt(64)) {
/* 209 */       debug.debug(-142394261359015L, "put");
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
/*     */   public void get(String topic, EvalContext msg, SearchResults result) throws MatchingException, BadMessageFormatMatchingException {
/* 244 */     if (debug.debugIt(32)) {
/* 245 */       debug.debug(-165922073994779L, "get", topic, msg, result);
/*     */     }
/*     */ 
/*     */     
/* 249 */     int len = topic.length();
/*     */     
/* 251 */     for (PartialMatch p = this.partMatches; p != null && p.keylen <= len; p = p.next) {
/* 252 */       if ((p.keylen != 0 && !topic.startsWith(p.key)) || (
/* 253 */         p.starChild == null && p.hashChild == null)) {
/*     */         continue;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 259 */       int keylen = p.keylen;
/* 260 */       if (keylen < len && keylen > 0) {
/* 261 */         if (topic.charAt(keylen) == '/') {
/* 262 */           keylen++;
/*     */         } else {
/*     */           continue;
/*     */         } 
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 270 */       String subSubj = topic.substring(keylen);
/*     */ 
/*     */       
/* 273 */       if (subSubj.length() <= 0 || subSubj.charAt(0) != '\001') {
/*     */ 
/*     */ 
/*     */         
/* 277 */         if (p.hashChild != null) {
/* 278 */           p.hashChild.get(subSubj, msg, result);
/*     */         }
/*     */         
/* 281 */         if (p.starChild != null)
/*     */         {
/* 283 */           if (len != keylen) {
/*     */ 
/*     */ 
/*     */             
/* 287 */             int from = subSubj.indexOf("/");
/* 288 */             if (from != -1) {
/* 289 */               subSubj = subSubj.substring(from + 1);
/*     */             } else {
/*     */               
/* 292 */               subSubj = "";
/*     */             } 
/* 294 */             p.starChild.get(subSubj, msg, result);
/*     */           }  } 
/*     */       } 
/*     */       continue;
/*     */     } 
/* 299 */     if (this.exactMatches != null) {
/* 300 */       Matcher exact = (Matcher)this.exactMatches.get(topic);
/* 301 */       if (exact != null) {
/* 302 */         exact.get(topic, msg, result);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 307 */     if (debug.debugIt(64)) {
/* 308 */       debug.debug(-142394261359015L, "get");
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
/*     */   public Matcher remove(String topic, Conjunction selector, MatchTarget object, InternTable subExpr, int parentId) throws MatchingException {
/* 327 */     if (debug.debugIt(32)) {
/* 328 */       debug.debug(-165922073994779L, "remove", topic, selector, object, subExpr);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 333 */     int starpos = findFirstMatchOneWildcard(topic);
/* 334 */     int hashpos = findFirstMatchManyWildcard(topic);
/* 335 */     PartialMatch pm = null;
/* 336 */     if (starpos == -1 && hashpos == -1) {
/*     */       
/* 338 */       Matcher next = (Matcher)this.exactMatches.get(topic);
/* 339 */       Assert.condition((next != null));
/* 340 */       if (next.remove(null, selector, object, subExpr, -1) == null) {
/* 341 */         this.exactMatches.remove(topic);
/*     */       }
/*     */     }
/* 344 */     else if (starpos == -1 || (hashpos != -1 && hashpos < starpos)) {
/*     */       
/* 346 */       pm = this.partMatches.get(topic.substring(0, (hashpos > 0) ? (hashpos - 1) : 0));
/* 347 */       Assert.condition((pm != null));
/*     */ 
/*     */       
/* 350 */       if (topic.length() - hashpos > 2) {
/* 351 */         topic = topic.substring(hashpos + 2);
/*     */       } else {
/*     */         
/* 354 */         topic = "";
/*     */       } 
/* 356 */       Assert.condition((pm.hashChild != null));
/* 357 */       pm.hashChild = pm.hashChild.remove(topic, selector, object, subExpr, -1);
/*     */     }
/*     */     else {
/*     */       
/* 361 */       pm = this.partMatches.get(topic.substring(0, (starpos > 0) ? (starpos - 1) : 0));
/*     */ 
/*     */       
/* 364 */       if (topic.length() - starpos > 2) {
/* 365 */         topic = topic.substring(starpos + 2);
/*     */       } else {
/*     */         
/* 368 */         topic = "";
/*     */       } 
/* 370 */       Assert.condition((pm.starChild != null));
/* 371 */       pm.starChild = pm.starChild.remove(topic, selector, object, subExpr, -1);
/*     */     } 
/*     */     
/* 374 */     if (pm != null && pm.isEmpty()) {
/* 375 */       this.partMatches.remove(pm);
/*     */     }
/* 377 */     Matcher result = ((this.exactMatches == null || this.exactMatches.isEmpty()) && this.partMatches.isEmptyChain()) ? null : this;
/*     */ 
/*     */     
/* 380 */     if (debug.debugIt(64)) {
/* 381 */       debug.debug(-142394261359015L, "remove", result);
/*     */     }
/*     */ 
/*     */     
/* 385 */     return result;
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
/*     */   private final int findFirstMatchManyWildcard(String topic) {
/* 401 */     int firstOccurrence = -1;
/*     */     
/* 403 */     int topiclength = topic.length();
/*     */ 
/*     */     
/* 406 */     if (topic == null || topiclength == 0) {
/* 407 */       return -1;
/*     */     }
/*     */ 
/*     */     
/* 411 */     if (topiclength == 1) {
/* 412 */       if (topic.charAt(0) == '#') {
/* 413 */         return 0;
/*     */       }
/*     */ 
/*     */       
/* 417 */       return -1;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 422 */     if (topic.charAt(0) == '#' && topic.charAt(1) == '/') {
/* 423 */       return 0;
/*     */     }
/*     */ 
/*     */     
/* 427 */     firstOccurrence = topic.indexOf("/#/");
/* 428 */     if (firstOccurrence != -1) {
/* 429 */       return firstOccurrence + 1;
/*     */     }
/*     */ 
/*     */     
/* 433 */     if (topic.charAt(topiclength - 2) == '/' && topic.charAt(topiclength - 1) == '#') {
/* 434 */       return topiclength - 1;
/*     */     }
/*     */ 
/*     */     
/* 438 */     return firstOccurrence;
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
/*     */   private final int findFirstMatchOneWildcard(String topic) {
/* 454 */     int firstOccurrence = -1;
/*     */     
/* 456 */     int topiclength = topic.length();
/*     */ 
/*     */     
/* 459 */     if (topic == null || topiclength == 0) {
/* 460 */       return -1;
/*     */     }
/*     */ 
/*     */     
/* 464 */     if (topiclength == 1) {
/* 465 */       if (topic.charAt(0) == '+') {
/* 466 */         return 0;
/*     */       }
/*     */ 
/*     */       
/* 470 */       return -1;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 475 */     if (topic.charAt(0) == '+' && topic.charAt(1) == '/') {
/* 476 */       return 0;
/*     */     }
/*     */ 
/*     */     
/* 480 */     firstOccurrence = topic.indexOf("/+/");
/* 481 */     if (firstOccurrence != -1) {
/* 482 */       return firstOccurrence + 1;
/*     */     }
/*     */ 
/*     */     
/* 486 */     if (topic.charAt(topiclength - 2) == '/' && topic.charAt(topiclength - 1) == '+') {
/* 487 */       return topiclength - 1;
/*     */     }
/*     */ 
/*     */     
/* 491 */     return firstOccurrence;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\SubjectMatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */