/*     */ package com.ibm.disthub2.impl.matching;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
/*     */ import com.ibm.disthub2.impl.matching.selector.Conjunction;
/*     */ import com.ibm.disthub2.impl.matching.selector.EvalContext;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class InverseSubjectMatcher
/*     */   extends Matcher
/*     */   implements ClientLogConstants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2004 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  86 */   private static final DebugObject debug = new DebugObject("InverseSubjectMatcher");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean noContent;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private PartialMatch partMatches;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   InverseSubjectMatcher(boolean noContent) {
/* 104 */     if (debug.debugIt(32)) {
/* 105 */       debug.debug(-165922073994779L, "InversSubjectMatcher", Boolean.valueOf(noContent));
/*     */     }
/*     */ 
/*     */     
/* 109 */     this.noContent = noContent;
/* 110 */     this.partMatches = new PartialMatch();
/*     */ 
/*     */     
/* 113 */     if (debug.debugIt(64)) {
/* 114 */       debug.debug(-142394261359015L, "InverseSubjectMatcher");
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
/*     */   public synchronized void put(String topic, Conjunction selector, MatchTarget object, InternTable subExpr, MatchTarget[] targets) throws MatchingException {
/* 134 */     if (debug.debugIt(32)) {
/* 135 */       debug.debug(-165922073994779L, "put", topic, selector, object, subExpr, targets);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 140 */     int starpos = findLastMatchOneWildcard(topic);
/* 141 */     Matcher next = null;
/* 142 */     if (starpos == -1) {
/*     */       
/* 144 */       PartialMatch pm = this.partMatches.get(topic);
/* 145 */       next = pm.hashChild;
/* 146 */       if (next == null) {
/* 147 */         next = pm.hashChild = new CacheingMatcher(this.noContent ? new LeafMatcher() : null);
/*     */       }
/* 149 */       topic = "";
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/* 158 */       String after = "";
/* 159 */       if (topic.length() - starpos > 2) {
/* 160 */         after = topic.substring(starpos + 2);
/*     */       }
/* 162 */       if (starpos > 1) {
/* 163 */         topic = topic.substring(0, starpos - 1);
/*     */       } else {
/*     */         
/* 166 */         topic = "";
/*     */       } 
/* 168 */       PartialMatch pm = this.partMatches.get(after);
/* 169 */       next = pm.starChild;
/* 170 */       if (next == null) {
/* 171 */         next = pm.starChild = new InverseSubjectMatcher(this.noContent);
/*     */       }
/*     */     } 
/*     */     
/* 175 */     next.put(topic, selector, object, subExpr, targets);
/*     */ 
/*     */     
/* 178 */     if (debug.debugIt(64)) {
/* 179 */       debug.debug(-142394261359015L, "put");
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
/*     */   public synchronized void get(String topic, EvalContext msg, SearchResults result) throws MatchingException, BadMessageFormatMatchingException {
/* 208 */     if (debug.debugIt(32)) {
/* 209 */       debug.debug(-165922073994779L, "get", topic, msg, result);
/*     */     }
/*     */ 
/*     */     
/* 213 */     int len = topic.length();
/* 214 */     for (PartialMatch p = this.partMatches; p != null && p.keylen <= len; p = p.next) {
/* 215 */       String subSubj; if ((p.keylen != 0 && !topic.endsWith(p.key)) || (
/* 216 */         p.starChild == null && p.hashChild == null)) {
/*     */         continue;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 222 */       int keylen = p.keylen;
/*     */       
/* 224 */       if (keylen == len) {
/* 225 */         subSubj = "";
/*     */       }
/* 227 */       else if (keylen == 0) {
/* 228 */         subSubj = topic;
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 233 */         int divide = topic.length() - 1 - keylen;
/* 234 */         if (topic.charAt(divide) != '/') {
/*     */           continue;
/*     */         }
/*     */         
/* 238 */         if (divide == 0) {
/* 239 */           subSubj = "";
/*     */         } else {
/*     */           
/* 242 */           subSubj = topic.substring(0, divide - 1);
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 247 */       if (subSubj.length() <= 0 || subSubj.charAt(0) != '\001') {
/*     */ 
/*     */ 
/*     */         
/* 251 */         if (p.hashChild != null) {
/* 252 */           p.hashChild.get(subSubj, msg, result);
/*     */         }
/*     */         
/* 255 */         if (p.starChild != null)
/*     */         {
/* 257 */           if (len != keylen) {
/*     */ 
/*     */ 
/*     */             
/* 261 */             int from = subSubj.lastIndexOf("/");
/* 262 */             if (from != -1) {
/* 263 */               subSubj = subSubj.substring(0, from);
/*     */             } else {
/*     */               
/* 266 */               subSubj = "";
/*     */             } 
/* 268 */             p.starChild.get(subSubj, msg, result);
/*     */           } 
/*     */         }
/*     */       } 
/*     */       continue;
/*     */     } 
/* 274 */     if (debug.debugIt(64)) {
/* 275 */       debug.debug(-142394261359015L, "get");
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
/*     */     PartialMatch pm;
/* 295 */     if (debug.debugIt(32)) {
/* 296 */       debug.debug(-165922073994779L, "remove", topic, selector, object, subExpr);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 301 */     int starpos = findLastMatchOneWildcard(topic);
/*     */     
/* 303 */     if (starpos == -1) {
/*     */       
/* 305 */       pm = this.partMatches.get(topic);
/* 306 */       Assert.condition((pm.hashChild != null));
/* 307 */       pm.hashChild = pm.hashChild.remove(null, selector, object, subExpr, -1);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 312 */       String after = "";
/* 313 */       if (topic.length() - starpos > 2) {
/* 314 */         after = topic.substring(starpos + 2);
/*     */       }
/* 316 */       if (starpos > 1) {
/* 317 */         topic = topic.substring(0, starpos - 1);
/*     */       } else {
/*     */         
/* 320 */         topic = "";
/*     */       } 
/* 322 */       pm = this.partMatches.get(after);
/* 323 */       Assert.condition((pm.starChild != null));
/* 324 */       pm.starChild = pm.starChild.remove(topic, selector, object, subExpr, -1);
/*     */     } 
/*     */     
/* 327 */     if (pm.isEmpty()) {
/* 328 */       this.partMatches.remove(pm);
/*     */     }
/*     */     
/* 331 */     Matcher result = this.partMatches.isEmptyChain() ? null : this;
/*     */ 
/*     */     
/* 334 */     if (debug.debugIt(64)) {
/* 335 */       debug.debug(-142394261359015L, "remove", result);
/*     */     }
/*     */ 
/*     */     
/* 339 */     return result;
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
/*     */   private final int findLastMatchManyWildcard(String topic) {
/* 354 */     int lastOccurrence = -1;
/*     */     
/* 356 */     int topiclength = topic.length();
/*     */ 
/*     */     
/* 359 */     if (topic == null || topiclength == 0) {
/* 360 */       return -1;
/*     */     }
/*     */ 
/*     */     
/* 364 */     if (topiclength == 1) {
/* 365 */       if (topic.charAt(0) == '#') {
/* 366 */         return 0;
/*     */       }
/*     */ 
/*     */       
/* 370 */       return -1;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 375 */     if (topic.charAt(topiclength - 2) == '/' && topic.charAt(topiclength - 1) == '#') {
/* 376 */       return topiclength - 1;
/*     */     }
/*     */ 
/*     */     
/* 380 */     lastOccurrence = topic.lastIndexOf("/#/");
/* 381 */     if (lastOccurrence != -1) {
/* 382 */       return lastOccurrence + 1;
/*     */     }
/*     */ 
/*     */     
/* 386 */     if (topic.charAt(0) == '#' && topic.charAt(1) == '/') {
/* 387 */       return 0;
/*     */     }
/*     */ 
/*     */     
/* 391 */     return lastOccurrence;
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
/*     */   private final int findLastMatchOneWildcard(String topic) {
/* 406 */     int lastOccurrence = -1;
/*     */     
/* 408 */     int topiclength = topic.length();
/*     */ 
/*     */     
/* 411 */     if (topic == null || topiclength == 0) {
/* 412 */       return -1;
/*     */     }
/*     */ 
/*     */     
/* 416 */     if (topiclength == 1) {
/* 417 */       if (topic.charAt(0) == '+') {
/* 418 */         return 0;
/*     */       }
/*     */ 
/*     */       
/* 422 */       return -1;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 427 */     if (topic.charAt(topiclength - 2) == '/' && topic.charAt(topiclength - 1) == '+') {
/* 428 */       return topiclength - 1;
/*     */     }
/*     */ 
/*     */     
/* 432 */     lastOccurrence = topic.lastIndexOf("/+/");
/* 433 */     if (lastOccurrence != -1) {
/* 434 */       return lastOccurrence + 1;
/*     */     }
/*     */ 
/*     */     
/* 438 */     if (topic.charAt(0) == '+' && topic.charAt(1) == '/') {
/* 439 */       return 0;
/*     */     }
/*     */ 
/*     */     
/* 443 */     return lastOccurrence;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\InverseSubjectMatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */