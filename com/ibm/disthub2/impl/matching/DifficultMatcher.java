/*     */ package com.ibm.disthub2.impl.matching;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
/*     */ import com.ibm.disthub2.impl.matching.selector.BooleanValue;
/*     */ import com.ibm.disthub2.impl.matching.selector.Conjunction;
/*     */ import com.ibm.disthub2.impl.matching.selector.EvalContext;
/*     */ import com.ibm.disthub2.impl.matching.selector.Evaluator;
/*     */ import com.ibm.disthub2.impl.matching.selector.Selector;
/*     */ import com.ibm.disthub2.impl.util.Assert;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class DifficultMatcher
/*     */   extends ContentMatcher
/*     */   implements ClientLogConstants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/* 106 */   private static final DebugObject debug = new DebugObject("DifficultMatcher");
/*     */ 
/*     */   
/*     */   private static final int INIT_MTTL_SIZE = 0;
/*     */ 
/*     */   
/*     */   FastVector roots;
/*     */ 
/*     */   
/*     */   FastVector objs;
/*     */ 
/*     */   
/*     */   MatchTargetTypeList alwaysMatch;
/*     */ 
/*     */   
/*     */   private class MatchTargetTypeList
/*     */   {
/* 123 */     FastVector[] lists = new FastVector[0];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void addTarget(MatchTarget t) {
/* 135 */       if (DifficultMatcher.debug.debugIt(32)) {
/* 136 */         DifficultMatcher.debug.debug(-165922073994779L, "MatchTargetTypeList.addTarget", t);
/*     */       }
/*     */       
/* 139 */       int type = t.type();
/*     */       
/* 141 */       resize(type);
/*     */       
/* 143 */       if (this.lists[type] == null) {
/* 144 */         this.lists[type] = new FastVector();
/*     */       }
/*     */       
/* 147 */       t.setIndex((this.lists[type]).m_count);
/* 148 */       this.lists[type].addElement(t);
/*     */       
/* 150 */       if (DifficultMatcher.debug.debugIt(64)) {
/* 151 */         DifficultMatcher.debug.debug(-142394261359015L, "MatchTargetTypeList.addTarget");
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean deleteTarget(MatchTarget t) {
/* 165 */       if (DifficultMatcher.debug.debugIt(32)) {
/* 166 */         DifficultMatcher.debug.debug(-165922073994779L, "MatchTargetTypeList.deleteTarget", t);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 174 */       int type = t.type();
/* 175 */       boolean result = false;
/* 176 */       if (type < this.lists.length) {
/* 177 */         FastVector set = this.lists[type];
/* 178 */         if (set != null) {
/* 179 */           int index = t.getIndex();
/* 180 */           if (index < set.m_count && t == set.m_data[index]) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 186 */             if (set.m_count == 1) {
/* 187 */               set.m_count = 0;
/*     */             } else {
/*     */               
/* 190 */               MatchTarget toMove = (MatchTarget)set.m_data[set.m_count - 1];
/* 191 */               toMove.setIndex(index);
/* 192 */               set.m_data[index] = toMove;
/* 193 */               set.m_count--;
/*     */             } 
/*     */ 
/*     */             
/* 197 */             if (set.m_count == 0) {
/* 198 */               this.lists[type] = null;
/*     */ 
/*     */               
/* 201 */               if (type == this.lists.length - 1) {
/* 202 */                 int newlen = this.lists.length;
/*     */ 
/*     */ 
/*     */                 
/* 206 */                 for (; newlen > 0 && this.lists[newlen - 1] == null; newlen--);
/*     */ 
/*     */ 
/*     */                 
/* 210 */                 if (newlen > 0) {
/* 211 */                   FastVector[] list2 = new FastVector[newlen];
/* 212 */                   System.arraycopy(this.lists, 0, list2, 0, newlen);
/* 213 */                   this.lists = list2;
/*     */                 } else {
/*     */                   
/* 216 */                   this.lists = new FastVector[0];
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */             
/* 221 */             result = true;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 226 */       if (DifficultMatcher.debug.debugIt(64)) {
/* 227 */         DifficultMatcher.debug.debug(-142394261359015L, "MatchTargetTypeList.deleteTarget", Boolean.valueOf(result));
/*     */       }
/*     */       
/* 230 */       return result;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public FastVector getTypeList(int type) {
/* 243 */       if (DifficultMatcher.debug.debugIt(32)) {
/* 244 */         DifficultMatcher.debug.debug(-165922073994779L, "MatchTargetTypeList.getTypeList", Integer.valueOf(type));
/*     */       }
/*     */       
/* 247 */       FastVector result = null;
/* 248 */       if (type < this.lists.length) {
/* 249 */         result = this.lists[type];
/*     */       }
/*     */       
/* 252 */       if (DifficultMatcher.debug.debugIt(64)) {
/* 253 */         DifficultMatcher.debug.debug(-142394261359015L, "MatchTargetTypeList.getTypeList", result);
/*     */       }
/*     */       
/* 256 */       return result;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private void resize(int maxType) {
/* 267 */       if (DifficultMatcher.debug.debugIt(32)) {
/* 268 */         DifficultMatcher.debug.debug(-165922073994779L, "MatchTargetTypeList.resize", Integer.valueOf(maxType));
/*     */       }
/*     */       
/* 271 */       int size = this.lists.length;
/* 272 */       if (size <= maxType) {
/* 273 */         FastVector[] list2 = new FastVector[maxType + 1];
/* 274 */         System.arraycopy(this.lists, 0, list2, 0, size);
/* 275 */         this.lists = list2;
/*     */       } 
/*     */       
/* 278 */       if (DifficultMatcher.debug.debugIt(64)) {
/* 279 */         DifficultMatcher.debug.debug(-142394261359015L, "MatchTargetTypeList.resize");
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int size() {
/* 291 */       return this.lists.length;
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
/* 310 */   public static int totalDifficultEntries = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DifficultMatcher(int pid) {
/* 321 */     super(pid);
/* 322 */     if (debug.debugIt(32)) {
/* 323 */       debug.debug(-165922073994779L, "DifficultMatcher", Integer.valueOf(pid), this);
/*     */     }
/*     */     
/* 326 */     this.roots = new FastVector();
/* 327 */     this.objs = new FastVector();
/* 328 */     this.alwaysMatch = new MatchTargetTypeList();
/*     */     
/* 330 */     if (debug.debugIt(64)) {
/* 331 */       debug.debug(-142394261359015L, "DifficultMatcher");
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
/*     */   public void put(String topic, Conjunction selector, MatchTarget object, InternTable subExpr, MatchTarget[] targets) throws MatchingException {
/* 351 */     if (debug.debugIt(32)) {
/* 352 */       debug.debug(-165922073994779L, "put", topic, selector, object, subExpr, targets, this);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 359 */     if (selector == null || noEligibleTests(selector)) {
/* 360 */       MatchTargetTypeList mttl; Selector expr = (selector == null) ? null : selector.residual;
/*     */ 
/*     */       
/* 363 */       if (expr == null) {
/* 364 */         mttl = this.alwaysMatch;
/*     */       } else {
/*     */         
/* 367 */         expr = expr.intern(subExpr);
/* 368 */         totalDifficultEntries = subExpr.size();
/*     */         
/* 370 */         int pos = this.roots.indexOf(expr);
/*     */ 
/*     */ 
/*     */         
/* 374 */         if (pos == -1) {
/* 375 */           MatchTargetTypeList tlist = new MatchTargetTypeList();
/* 376 */           mttl = tlist;
/*     */           
/* 378 */           this.objs.addElement(tlist);
/* 379 */           this.roots.addElement(expr);
/*     */         }
/*     */         else {
/*     */           
/* 383 */           mttl = (MatchTargetTypeList)this.objs.elementAt(pos);
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 388 */       mttl.addTarget(object);
/*     */       
/* 390 */       if (object.type() == 4) {
/* 391 */         FastVector h = mttl.getTypeList(3);
/* 392 */         if (h != null) {
/* 393 */           Assert.condition((h.m_count < 2));
/* 394 */           if (h.m_count == 1) {
/* 395 */             targets[0] = (MatchTarget)h.m_data[0];
/*     */           }
/*     */           
/* 398 */           object.setISP(targets[0]);
/*     */         }
/*     */       
/* 401 */       } else if (object.type() == 3) {
/* 402 */         FastVector h = mttl.getTypeList(4);
/* 403 */         if (h != null)
/*     */         {
/* 405 */           for (int i = 0; i < h.m_count; i++) {
/* 406 */             MatchTarget bs = (MatchTarget)h.m_data[i];
/* 407 */             bs.setISP(object);
/*     */           }
/*     */         
/*     */         }
/*     */       } 
/*     */     } else {
/*     */       
/* 414 */       super.put(topic, selector, object, subExpr, targets);
/*     */     } 
/*     */     
/* 417 */     if (debug.debugIt(64)) {
/* 418 */       debug.debug(-142394261359015L, "put");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean noEligibleTests(Conjunction selector) {
/* 427 */     for (int i = 0; i < selector.simpleTests.length; i++) {
/* 428 */       if (debug.debugIt(16)) {
/* 429 */         debug.debug(-153415734321212L, "noEligibleTests", "DM ord: " + this.ordinalPosition + ", id: " + (selector.simpleTests[i]).identifier + ", test ord: " + (selector.simpleTests[i]).identifier.ordinalPosition);
/*     */       }
/*     */ 
/*     */       
/* 433 */       if ((selector.simpleTests[i]).identifier.ordinalPosition > this.ordinalPosition) {
/* 434 */         return false;
/*     */       }
/*     */     } 
/* 437 */     return true;
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
/*     */   public void get(String topic, EvalContext msg, SearchResults result) throws MatchingException, BadMessageFormatMatchingException {
/* 452 */     if (debug.debugIt(32)) {
/* 453 */       debug.debug(-165922073994779L, "get", topic, msg, result, this);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 458 */     FastVector[] v = this.alwaysMatch.lists;
/* 459 */     if (v.length > 0) {
/* 460 */       result.addObjects(v);
/*     */     }
/*     */     
/* 463 */     if (msg != null) {
/* 464 */       int numExpr = this.roots.size();
/*     */       
/* 466 */       for (int current = 0; current < numExpr; current++) {
/* 467 */         BooleanValue res = (BooleanValue)Evaluator.eval((Selector)this.roots.m_data[current], msg, 
/* 468 */             MatchSpace.getPermissive());
/* 469 */         if (res.booleanValue()) {
/* 470 */           MatchTargetTypeList tlist = (MatchTargetTypeList)this.objs.m_data[current];
/* 471 */           v = tlist.lists;
/* 472 */           if (v.length > 0) {
/* 473 */             result.addObjects(v);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 480 */     if (debug.debugIt(16)) {
/* 481 */       debug.debug(-153415734321212L, "get", "continue branch");
/*     */     }
/*     */     
/* 484 */     super.get(topic, msg, result);
/*     */     
/* 486 */     if (debug.debugIt(64)) {
/* 487 */       debug.debug(-142394261359015L, "get");
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
/* 506 */     if (debug.debugIt(32)) {
/* 507 */       debug.debug(-165922073994779L, "remove", topic, selector, object, subExpr, this);
/*     */     }
/*     */     
/* 510 */     Assert.condition((this.objs != null));
/*     */     
/* 512 */     int type = object.type();
/*     */     
/* 514 */     if (selector == null || noEligibleTests(selector)) {
/* 515 */       int len = this.objs.size();
/*     */       
/* 517 */       if (debug.debugIt(16)) {
/* 518 */         debug.debug(-153415734321212L, "remove", "remove sub from this Matcher Node");
/*     */       }
/*     */       
/* 521 */       boolean removed = false;
/*     */       
/* 523 */       for (int i = 0; !removed && i < len; i++) {
/* 524 */         MatchTargetTypeList tlist = (MatchTargetTypeList)this.objs.elementAt(i);
/*     */         
/* 526 */         removed = tlist.deleteTarget(object);
/*     */         
/* 528 */         if (removed) {
/* 529 */           Selector node = (Selector)this.roots.m_data[i];
/*     */           
/* 531 */           Assert.condition((node != null));
/*     */ 
/*     */           
/* 534 */           node.unintern(subExpr);
/*     */           
/* 536 */           if (tlist.size() == 0) {
/* 537 */             this.objs.dropElementAt(i);
/* 538 */             this.roots.dropElementAt(i);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 543 */       if (!removed) {
/*     */ 
/*     */         
/* 546 */         if (debug.debugIt(16)) {
/* 547 */           debug.debug(-153415734321212L, "remove", "remove sub from alwaysMatchlist");
/*     */         }
/*     */         
/* 550 */         removed = this.alwaysMatch.deleteTarget(object);
/* 551 */         Assert.condition(removed);
/*     */       } 
/* 553 */       if (removed) {
/* 554 */         object.invalidate();
/*     */       
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 561 */       if (debug.debugIt(16)) {
/* 562 */         debug.debug(-153415734321212L, "remove", "pass thru to remove from super");
/*     */       }
/*     */       
/* 565 */       super.remove(topic, selector, object, subExpr, this.ordinalPosition);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 570 */     Matcher result = this;
/* 571 */     if (this.roots.size() == 0 && this.alwaysMatch.size() == 0) {
/* 572 */       if (debug.debugIt(16)) {
/* 573 */         debug.debug(-153415734321212L, "remove", "set result to vacantChild: " + this.vacantChild);
/*     */       }
/*     */       
/* 576 */       result = this.vacantChild;
/*     */     } 
/*     */     
/* 579 */     if (debug.debugIt(64)) {
/* 580 */       debug.debug(-142394261359015L, "remove", result);
/*     */     }
/*     */     
/* 583 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\DifficultMatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */