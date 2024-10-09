/*      */ package com.ibm.disthub2.impl.matching;
/*      */ 
/*      */ import com.ibm.disthub2.impl.client.DebugObject;
/*      */ import com.ibm.disthub2.impl.matching.selector.Conjunction;
/*      */ import com.ibm.disthub2.impl.matching.selector.EvalContext;
/*      */ import com.ibm.disthub2.impl.matching.selector.Resolver;
/*      */ import com.ibm.disthub2.impl.util.FastVector;
/*      */ import com.ibm.disthub2.impl.util.TopicHandler;
/*      */ import com.ibm.disthub2.spi.ClientExceptionConstants;
/*      */ import com.ibm.disthub2.spi.ClientLogConstants;
/*      */ import com.ibm.disthub2.spi.ExceptionBuilder;
/*      */ import java.io.PrintWriter;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Hashtable;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class MatchSpace
/*      */   implements ClientExceptionConstants, ClientLogConstants
/*      */ {
/*      */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*   98 */   private static final DebugObject debug = new DebugObject("MatchSpace");
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static class Subscription
/*      */   {
/*      */     String topic;
/*      */ 
/*      */     
/*      */     Conjunction[] expr;
/*      */ 
/*      */     
/*      */     boolean hasWilds;
/*      */ 
/*      */     
/*      */     MatchTarget origTarget;
/*      */ 
/*      */     
/*      */     FastVector cloneTargets;
/*      */ 
/*      */ 
/*      */     
/*      */     Subscription(String topic, Conjunction[] expr, boolean hasWilds, MatchTarget origTarget, FastVector cloneTargets) {
/*  122 */       this.topic = topic;
/*  123 */       this.expr = expr;
/*  124 */       this.hasWilds = hasWilds;
/*  125 */       this.origTarget = origTarget;
/*  126 */       this.cloneTargets = cloneTargets;
/*      */     }
/*      */ 
/*      */     
/*      */     public String toString() {
/*  131 */       String str = "Subscription[subject = " + this.topic + " (";
/*  132 */       if (this.expr == null) {
/*  133 */         str = str + ") ";
/*      */       } else {
/*      */         
/*  136 */         for (int i = 0; i < this.expr.length; i++) {
/*  137 */           str = str + this.expr[i] + " ";
/*      */         }
/*      */       } 
/*  140 */       str = str + ") hasWilds = " + this.hasWilds + " target = " + this.origTarget + "]";
/*  141 */       return str;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*  147 */   AdminSpace admin = new AdminSpace();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  153 */   Hashtable adminSubs = new Hashtable<>();
/*      */ 
/*      */ 
/*      */   
/*      */   private static class SubscriptionPoint
/*      */   {
/*      */     Hashtable subscriptions;
/*      */ 
/*      */     
/*      */     SubscriptionSpace subSpace;
/*      */ 
/*      */ 
/*      */     
/*      */     SubscriptionPoint(MatchSpace matchSpace, AdminSpace admin) {
/*  167 */       this.subscriptions = new Hashtable<>();
/*  168 */       this.subSpace = new SubscriptionSpace(matchSpace, admin);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  175 */   SubscriptionPoint defaultSubPoint = new SubscriptionPoint(this, this.admin);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  180 */   Hashtable subPoints = new Hashtable<>();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  187 */   InternTable subExpr = new InternTable();
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean permissive;
/*      */ 
/*      */ 
/*      */   
/*      */   public static void setPermissive(boolean p) {
/*  196 */     permissive = p;
/*      */   }
/*      */   
/*      */   public static boolean getPermissive() {
/*  200 */     return permissive;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MatchSpace() {
/*  208 */     if (debug.debugIt(32)) {
/*  209 */       debug.debug(-165922073994779L, "MatchSpace");
/*      */     }
/*      */     
/*  212 */     this.subPoints.put("", this.defaultSubPoint);
/*      */     
/*  214 */     if (debug.debugIt(64)) {
/*  215 */       debug.debug(-142394261359015L, "MatchSpace");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized void put(String subPoint, String topic, String selector, MatchTarget object, MatchTarget[] targets, Resolver resolver) throws MatchingException, InvalidTopicSyntaxException, QuerySyntaxException {
/*      */     SubscriptionPoint sbpt;
/*  260 */     if (debug.debugIt(32)) {
/*  261 */       debug.debug(-165922073994779L, "put", subPoint, topic, selector, object, targets, resolver);
/*      */     }
/*      */ 
/*      */     
/*  265 */     if (subPoint == null || subPoint.length() == 0) {
/*  266 */       sbpt = this.defaultSubPoint;
/*      */     } else {
/*      */       
/*  269 */       sbpt = (SubscriptionPoint)this.subPoints.get(subPoint);
/*  270 */       if (sbpt == null) {
/*  271 */         sbpt = new SubscriptionPoint(this, this.admin);
/*  272 */         this.subPoints.put(subPoint, sbpt);
/*      */       } 
/*      */     } 
/*  275 */     putInternal(sbpt, topic, selector, object, targets, resolver);
/*      */     
/*  277 */     if (debug.debugIt(64)) {
/*  278 */       debug.debug(-142394261359015L, "put");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized void put(String topic, String selector, MatchTarget object, MatchTarget[] targets, Resolver resolver) throws MatchingException, InvalidTopicSyntaxException, QuerySyntaxException {
/*  292 */     if (debug.debugIt(32)) {
/*  293 */       debug.debug(-165922073994779L, "put", topic, selector, object, targets, resolver);
/*      */     }
/*      */     
/*  296 */     putInternal(this.defaultSubPoint, topic, selector, object, targets, resolver);
/*      */     
/*  298 */     if (debug.debugIt(64)) {
/*  299 */       debug.debug(-142394261359015L, "put");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void put(String subPoint, String topic, String selector, MatchTarget object, MatchTarget[] targets) throws MatchingException, InvalidTopicSyntaxException, QuerySyntaxException {
/*  313 */     if (debug.debugIt(32)) {
/*  314 */       debug.debug(-165922073994779L, "put", subPoint, topic, selector, object, targets);
/*      */     }
/*      */     
/*  317 */     put(subPoint, topic, selector, object, targets, null);
/*      */     
/*  319 */     if (debug.debugIt(64)) {
/*  320 */       debug.debug(-142394261359015L, "put");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void put(String topic, String selector, MatchTarget object, MatchTarget[] targets) throws MatchingException, InvalidTopicSyntaxException, QuerySyntaxException {
/*  334 */     if (debug.debugIt(32)) {
/*  335 */       debug.debug(-165922073994779L, "put", topic, selector, object, targets);
/*      */     }
/*      */     
/*  338 */     put(topic, selector, object, targets, (Resolver)null);
/*      */     
/*  340 */     if (debug.debugIt(64)) {
/*  341 */       debug.debug(-142394261359015L, "put");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void putInternal(SubscriptionPoint sbpt, String topic, String selector, MatchTarget object, MatchTarget[] targets, Resolver resolver) throws QuerySyntaxException, MatchingException, InvalidTopicSyntaxException {
/*  357 */     if (debug.debugIt(32)) {
/*  358 */       debug.debug(-165922073994779L, "putInternal", sbpt, topic, selector, object, targets, resolver);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  363 */     boolean hasWilds = TopicHandler.checkTopicSyntax(topic, '/', '+', '#');
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  369 */     FastVector clones = new FastVector();
/*  370 */     Conjunction[] query = sbpt.subSpace.put(topic, hasWilds, selector, object, targets, resolver, this.subExpr, clones);
/*      */     
/*  372 */     if (clones.m_count == 0) {
/*  373 */       clones = null;
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  378 */       storeSubscription(topic, query, hasWilds, object, clones, sbpt.subscriptions);
/*      */     }
/*  380 */     catch (MatchingException e) {
/*      */ 
/*      */       
/*      */       try {
/*      */         
/*  385 */         sbpt.subSpace.remove(topic, hasWilds, query, object, this.subExpr, clones);
/*      */       }
/*  387 */       catch (Exception exception) {}
/*  388 */       throw e;
/*      */     } 
/*      */     
/*  391 */     if (debug.debugIt(64)) {
/*  392 */       debug.debug(-142394261359015L, "putInternal");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void storeSubscription(String topic, Conjunction[] query, boolean hasWilds, MatchTarget object, FastVector clones, Hashtable<String, Hashtable<Object, Object>> subscriptions) throws MatchingException {
/*  405 */     if (debug.debugIt(32)) {
/*  406 */       debug.debug(-165922073994779L, "storeSubscription", topic, query, Boolean.valueOf(hasWilds), object, subscriptions);
/*      */     }
/*      */ 
/*      */     
/*  410 */     Subscription sub = new Subscription(topic, query, hasWilds, object, clones);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  415 */     String subKey = object.getTargetName();
/*  416 */     if (subKey == null) {
/*  417 */       subKey = topic;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  422 */     Hashtable<Object, Object> mTargets = (Hashtable)subscriptions.get(subKey);
/*  423 */     if (mTargets == null) {
/*      */       
/*  425 */       mTargets = new Hashtable<>();
/*  426 */       subscriptions.put(subKey, mTargets);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  431 */     if (mTargets.containsKey(object)) {
/*  432 */       throw new MatchingException(ExceptionBuilder.buildReasonString(973908005, null));
/*      */     }
/*      */ 
/*      */     
/*  436 */     mTargets.put(object, sub);
/*      */     
/*  438 */     if (debug.debugIt(64)) {
/*  439 */       debug.debug(-142394261359015L, "storeSubscription");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized void putAdmin(String topic, MatchTarget object) throws MatchingException, InvalidTopicSyntaxException {
/*  461 */     if (debug.debugIt(32)) {
/*  462 */       debug.debug(-165922073994779L, "putAdmin", topic, object);
/*      */     }
/*      */     
/*  465 */     boolean hasWilds = TopicHandler.checkTopicSyntax(topic, '/', '+', '#');
/*      */ 
/*      */ 
/*      */     
/*  469 */     storeSubscription(topic, null, hasWilds, object, null, this.adminSubs);
/*  470 */     this.admin.put(topic, hasWilds, object);
/*      */     
/*  472 */     if (debug.debugIt(64)) {
/*  473 */       debug.debug(-142394261359015L, "putAdmin");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void get(String subPoint, String topic, EvalContext msg, SearchResults result) throws MatchingException, BadMessageFormatMatchingException {
/*  494 */     if (debug.debugIt(32)) {
/*  495 */       debug.debug(-165922073994779L, "get", subPoint, topic, msg, result);
/*      */     }
/*      */     
/*  498 */     SubscriptionPoint sbpt = getSubPoint(subPoint);
/*  499 */     if (sbpt != null)
/*      */     {
/*      */       
/*  502 */       sbpt.subSpace.get(topic, msg, result);
/*      */     }
/*      */     
/*  505 */     if (debug.debugIt(64)) {
/*  506 */       debug.debug(-142394261359015L, "get");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private SubscriptionPoint getSubPoint(String subPoint) {
/*  517 */     if (debug.debugIt(32)) {
/*  518 */       debug.debug(-165922073994779L, "getSubPoint", subPoint);
/*      */     }
/*      */     
/*  521 */     SubscriptionPoint result = this.defaultSubPoint;
/*  522 */     if (subPoint != null && subPoint.length() > 0) {
/*  523 */       result = (SubscriptionPoint)this.subPoints.get(subPoint);
/*      */     }
/*      */     
/*  526 */     if (debug.debugIt(64)) {
/*  527 */       debug.debug(-142394261359015L, "getSubPoint", result);
/*      */     }
/*      */     
/*  530 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void get(String topic, EvalContext msg, SearchResults result) throws MatchingException, BadMessageFormatMatchingException {
/*  542 */     if (debug.debugIt(32)) {
/*  543 */       debug.debug(-165922073994779L, "get", topic, msg, result);
/*      */     }
/*      */     
/*  546 */     this.defaultSubPoint.subSpace.get(topic, msg, result);
/*      */     
/*  548 */     if (debug.debugIt(64)) {
/*  549 */       debug.debug(-142394261359015L, "get");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void getAdmin(String topic, SearchResults result) throws MatchingException {
/*  565 */     if (debug.debugIt(32)) {
/*  566 */       debug.debug(-165922073994779L, "getAdmin", topic, result);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  572 */     AdminSpace.CacheEntry e = this.admin.get(topic);
/*  573 */     if (e == null) {
/*  574 */       pessimisticGetAdmin(topic, result);
/*      */       
/*  576 */       if (debug.debugIt(64)) {
/*  577 */         debug.debug(-142394261359015L, "getAdmin");
/*      */       }
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */ 
/*      */     
/*  585 */     long wildG = this.admin.wildGeneration;
/*  586 */     long exactG = e.exactGeneration;
/*      */ 
/*      */     
/*  589 */     if ((wildG & 0x1L) == 1L || (exactG & 0x1L) == 1L) {
/*  590 */       pessimisticGetAdmin(topic, result);
/*      */       
/*  592 */       if (debug.debugIt(64)) {
/*  593 */         debug.debug(-142394261359015L, "getAdmin");
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  603 */     if (wildG != e.wildGeneration || e.consolidated == null) {
/*  604 */       pessimisticGetAdmin(topic, result);
/*      */       
/*  606 */       if (debug.debugIt(64)) {
/*  607 */         debug.debug(-142394261359015L, "getAdmin");
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  622 */     synchronized (result) {
/*      */     
/*      */     } 
/*      */     
/*  626 */     Throwable oops = null;
/*      */     try {
/*  628 */       e.consolidated.get(topic, null, result);
/*      */     }
/*  630 */     catch (BadMessageFormatMatchingException bexc) {
/*      */       
/*  632 */       oops = bexc;
/*      */     }
/*  634 */     catch (RuntimeException exc) {
/*  635 */       oops = exc;
/*      */     }
/*  637 */     catch (Error exc) {
/*  638 */       oops = exc;
/*      */     } 
/*      */ 
/*      */     
/*  642 */     if (wildG != this.admin.wildGeneration || exactG != e.exactGeneration) {
/*  643 */       result.reset();
/*  644 */       pessimisticGetAdmin(topic, result);
/*      */     } 
/*      */ 
/*      */     
/*  648 */     if (oops != null) {
/*  649 */       throw new MatchingException(ExceptionBuilder.buildReasonString(-1200843245, new Object[] { oops }));
/*      */     }
/*      */ 
/*      */     
/*  653 */     if (debug.debugIt(64)) {
/*  654 */       debug.debug(-142394261359015L, "getAdmin");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private synchronized void pessimisticGetAdmin(String topic, SearchResults result) throws MatchingException {
/*  668 */     if (debug.debugIt(32)) {
/*  669 */       debug.debug(-165922073994779L, "pessimisticGetAdmin", topic, result);
/*      */     }
/*      */     
/*      */     try {
/*  673 */       AdminSpace.CacheEntry e = this.admin.getLocked(topic);
/*  674 */       e.consolidated.get(topic, null, result);
/*      */     }
/*  676 */     catch (BadMessageFormatMatchingException bexc) {
/*      */       
/*  678 */       throw new MatchingException(ExceptionBuilder.buildReasonString(-1200843245, new Object[] { bexc }));
/*      */     } 
/*      */     
/*  681 */     if (debug.debugIt(64)) {
/*  682 */       debug.debug(-142394261359015L, "pessimisticGetAdmin");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized void remove(MatchTarget object) throws MatchingException {
/*  696 */     if (debug.debugIt(32)) {
/*  697 */       debug.debug(-165922073994779L, "remove", object);
/*      */     }
/*      */     
/*  700 */     String topic = object.getTargetName();
/*  701 */     if (topic == null) {
/*  702 */       throw new MatchingException(ExceptionBuilder.buildReasonString(-1954331796, new Object[] { object }));
/*      */     }
/*  704 */     removeInternal(this.defaultSubPoint, topic, object);
/*      */     
/*  706 */     if (debug.debugIt(64)) {
/*  707 */       debug.debug(-142394261359015L, "remove");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized void remove(String topic, MatchTarget object) throws MatchingException {
/*  720 */     if (debug.debugIt(32)) {
/*  721 */       debug.debug(-165922073994779L, "remove", topic, object);
/*      */     }
/*      */     
/*  724 */     removeInternal(this.defaultSubPoint, topic, object);
/*      */     
/*  726 */     if (debug.debugIt(64)) {
/*  727 */       debug.debug(-142394261359015L, "remove");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized void remove(String subPoint, String topic, MatchTarget object) throws MatchingException {
/*  755 */     if (debug.debugIt(32)) {
/*  756 */       debug.debug(-165922073994779L, "remove", subPoint, topic, object);
/*      */     }
/*      */     
/*  759 */     SubscriptionPoint sbpt = getSubPoint(subPoint);
/*  760 */     if (sbpt == null) {
/*  761 */       throw new MatchingException(ExceptionBuilder.buildReasonString(61872650, new Object[] { topic, object }));
/*      */     }
/*  763 */     removeInternal(sbpt, topic, object);
/*      */     
/*  765 */     if (debug.debugIt(64)) {
/*  766 */       debug.debug(-142394261359015L, "remove");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void removeInternal(SubscriptionPoint sbpt, String topic, MatchTarget object) throws MatchingException {
/*  777 */     if (debug.debugIt(32)) {
/*  778 */       debug.debug(-165922073994779L, "removeInternal", sbpt, topic, object);
/*      */     }
/*      */     
/*  781 */     Subscription sub = removeSubscription(sbpt.subscriptions, topic, object);
/*      */     
/*  783 */     sbpt.subSpace.remove(sub.topic, sub.hasWilds, sub.expr, sub.origTarget, this.subExpr, sub.cloneTargets);
/*      */     
/*  785 */     if (debug.debugIt(64)) {
/*  786 */       debug.debug(-142394261359015L, "removeInternal");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Subscription removeSubscription(Hashtable subscriptions, String topic, MatchTarget object) throws MatchingException {
/*  799 */     if (debug.debugIt(32)) {
/*  800 */       debug.debug(-165922073994779L, "removeSubscription", subscriptions, topic, object);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  806 */     String subKey = object.getTargetName();
/*  807 */     if (subKey == null) {
/*  808 */       subKey = topic;
/*      */     }
/*      */     
/*  811 */     Subscription sub = null;
/*  812 */     Hashtable mTargets = (Hashtable)subscriptions.get(subKey);
/*  813 */     if (mTargets != null) {
/*  814 */       sub = (Subscription)mTargets.remove(object);
/*      */     }
/*  816 */     if (sub == null) {
/*  817 */       throw new MatchingException(ExceptionBuilder.buildReasonString(61872650, new Object[] { topic, object }));
/*      */     }
/*      */     
/*  820 */     if (mTargets.size() == 0) {
/*  821 */       subscriptions.remove(subKey);
/*      */     }
/*      */     
/*  824 */     if (debug.debugIt(64)) {
/*  825 */       debug.debug(-142394261359015L, "removeSubscription", sub);
/*      */     }
/*      */     
/*  828 */     return sub;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized void removeAdmin(MatchTarget object) throws MatchingException {
/*  839 */     if (debug.debugIt(32)) {
/*  840 */       debug.debug(-165922073994779L, "removeAdmin", object);
/*      */     }
/*      */     
/*  843 */     String topic = object.getTargetName();
/*  844 */     if (topic == null) {
/*  845 */       throw new MatchingException(ExceptionBuilder.buildReasonString(-1954331796, new Object[] { object }));
/*      */     }
/*  847 */     removeAdmin(topic, object);
/*      */     
/*  849 */     if (debug.debugIt(64)) {
/*  850 */       debug.debug(-142394261359015L, "removeAdmin");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized void removeAdmin(String topic, MatchTarget object) throws MatchingException {
/*  865 */     if (debug.debugIt(32)) {
/*  866 */       debug.debug(-165922073994779L, "removeAdmin", topic, object);
/*      */     }
/*      */     
/*  869 */     Subscription sub = removeSubscription(this.adminSubs, topic, object);
/*  870 */     this.admin.remove(sub.topic, sub.hasWilds, sub.origTarget);
/*      */     
/*  872 */     if (debug.debugIt(64)) {
/*  873 */       debug.debug(-142394261359015L, "removeAdmin");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized Enumeration getAllMatchTargets(String subPoint) {
/*  891 */     if (debug.debugIt(32)) {
/*  892 */       debug.debug(-165922073994779L, "getAllMatchTargets", subPoint);
/*      */     }
/*      */     
/*  895 */     FastVector objectVector = new FastVector();
/*  896 */     SubscriptionPoint sbpt = getSubPoint(subPoint);
/*  897 */     if (sbpt != null) {
/*  898 */       getAllMatchTargetsInternal(sbpt.subscriptions, objectVector);
/*      */     }
/*      */     
/*  901 */     Enumeration result = objectVector.elements();
/*      */     
/*  903 */     if (debug.debugIt(64)) {
/*  904 */       debug.debug(-142394261359015L, "getAllMatchTargets", result);
/*      */     }
/*      */     
/*  907 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized Enumeration getAllMatchTargets() {
/*  922 */     if (debug.debugIt(32)) {
/*  923 */       debug.debug(-165922073994779L, "getAllMatchTargets");
/*      */     }
/*      */     
/*  926 */     FastVector objectVector = new FastVector();
/*      */ 
/*      */     
/*  929 */     Enumeration subPointEnumeration = this.subPoints.elements();
/*      */ 
/*      */     
/*  932 */     while (subPointEnumeration.hasMoreElements()) {
/*  933 */       getAllMatchTargetsInternal(((SubscriptionPoint)subPointEnumeration.nextElement()).subscriptions, objectVector);
/*      */     }
/*      */     
/*  936 */     Enumeration result = objectVector.elements();
/*      */     
/*  938 */     if (debug.debugIt(64)) {
/*  939 */       debug.debug(-142394261359015L, "getAllMatchTargets", result);
/*      */     }
/*      */     
/*  942 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized Enumeration getAllSubscriptionPoints() {
/*  954 */     if (debug.debugIt(32)) {
/*  955 */       debug.debug(-165922073994779L, "getAllSubscriptionPoints");
/*      */     }
/*      */     
/*  958 */     Enumeration result = this.subPoints.keys();
/*      */     
/*  960 */     if (debug.debugIt(64)) {
/*  961 */       debug.debug(-142394261359015L, "getAllSubscriptionPoints", result);
/*      */     }
/*      */     
/*  964 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized Enumeration getAllAdminMatchTargets() {
/*  979 */     if (debug.debugIt(32)) {
/*  980 */       debug.debug(-165922073994779L, "getAllAdminMatchTargets");
/*      */     }
/*      */     
/*  983 */     FastVector objectVector = new FastVector();
/*  984 */     getAllMatchTargetsInternal(this.adminSubs, objectVector);
/*  985 */     Enumeration result = objectVector.elements();
/*      */     
/*  987 */     if (debug.debugIt(64)) {
/*  988 */       debug.debug(-142394261359015L, "getAllAdminMatchTargets", result);
/*      */     }
/*      */     
/*  991 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void getAllMatchTargetsInternal(Hashtable subscriptions, FastVector answer) {
/*  999 */     if (debug.debugIt(32)) {
/* 1000 */       debug.debug(-165922073994779L, "getAllMatchTargetsInternal", subscriptions, answer);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1006 */     Enumeration<Hashtable> theTables = subscriptions.elements();
/* 1007 */     while (theTables.hasMoreElements()) {
/* 1008 */       Hashtable mTargets = theTables.nextElement();
/* 1009 */       Enumeration<MatchTarget> theObjs = mTargets.keys();
/* 1010 */       while (theObjs.hasMoreElements()) {
/* 1011 */         MatchTarget nextObj = theObjs.nextElement();
/* 1012 */         answer.addElement(nextObj);
/*      */       } 
/*      */     } 
/*      */     
/* 1016 */     if (debug.debugIt(64)) {
/* 1017 */       debug.debug(-142394261359015L, "getAllMatchTargetsInternal");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized int getNumberOfMatchTargets(String subPoint) {
/* 1033 */     if (debug.debugIt(32)) {
/* 1034 */       debug.debug(-165922073994779L, "getNumberOfMatchTargets", subPoint);
/*      */     }
/*      */     
/* 1037 */     SubscriptionPoint sbpt = getSubPoint(subPoint);
/* 1038 */     int result = 0;
/* 1039 */     if (sbpt != null)
/*      */     {
/*      */       
/* 1042 */       result = getNumberOfMatchTargetsInternal(sbpt.subscriptions);
/*      */     }
/*      */     
/* 1045 */     if (debug.debugIt(64)) {
/* 1046 */       debug.debug(-142394261359015L, "getNumberOfMatchTargets", Integer.valueOf(result));
/*      */     }
/*      */     
/* 1049 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized int getNumberOfAdminMatchTargets() {
/* 1061 */     if (debug.debugIt(32)) {
/* 1062 */       debug.debug(-165922073994779L, "getNumberOfAdminMatchTargets");
/*      */     }
/*      */     
/* 1065 */     int result = getNumberOfMatchTargetsInternal(this.adminSubs);
/*      */     
/* 1067 */     if (debug.debugIt(64)) {
/* 1068 */       debug.debug(-142394261359015L, "getNumberOfAdminMatchTargets", Integer.valueOf(result));
/*      */     }
/*      */     
/* 1071 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int getNumberOfMatchTargetsInternal(Hashtable subscriptions) {
/* 1079 */     if (debug.debugIt(32)) {
/* 1080 */       debug.debug(-165922073994779L, "getNumberOfMatchTargetsInternal", subscriptions);
/*      */     }
/*      */ 
/*      */     
/* 1084 */     int total = 0;
/*      */     
/* 1086 */     Enumeration<Hashtable> theTables = subscriptions.elements();
/* 1087 */     while (theTables.hasMoreElements()) {
/* 1088 */       Hashtable mTargets = theTables.nextElement();
/* 1089 */       total += mTargets.size();
/*      */     } 
/*      */     
/* 1092 */     if (debug.debugIt(64)) {
/* 1093 */       debug.debug(-142394261359015L, "getNumberOfMatchTargetsInternal", Integer.valueOf(total));
/*      */     }
/*      */     
/* 1096 */     return total;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized FastVector getAllMatchTargetsForTopic(String topic) {
/* 1119 */     if (debug.debugIt(32)) {
/* 1120 */       debug.debug(-165922073994779L, "getAllMatchTargetsForTopic", topic);
/*      */     }
/* 1122 */     FastVector allSubscriptions = new FastVector();
/*      */     
/* 1124 */     Enumeration subPointEnumeration = this.subPoints.elements();
/*      */ 
/*      */     
/* 1127 */     while (subPointEnumeration.hasMoreElements()) {
/* 1128 */       getAllMatchTargetsForTopicInternal(((SubscriptionPoint)subPointEnumeration.nextElement()).subscriptions, topic, allSubscriptions);
/*      */     }
/*      */     
/* 1131 */     if (debug.debugIt(64)) {
/* 1132 */       debug.debug(-142394261359015L, "getAllMatchTargetsForTopic", allSubscriptions);
/*      */     }
/* 1134 */     return allSubscriptions;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized FastVector getAllMatchTargetsForTopic(String subPoint, String topic) {
/* 1157 */     if (debug.debugIt(32)) {
/* 1158 */       debug.debug(-165922073994779L, "getAllMatchTargetsForTopic", subPoint, topic);
/*      */     }
/*      */     
/* 1161 */     FastVector allSubscriptions = new FastVector();
/* 1162 */     SubscriptionPoint sbpt = getSubPoint(subPoint);
/* 1163 */     if (sbpt != null) {
/* 1164 */       getAllMatchTargetsForTopicInternal(sbpt.subscriptions, topic, allSubscriptions);
/*      */     }
/*      */     
/* 1167 */     if (debug.debugIt(64)) {
/* 1168 */       debug.debug(-142394261359015L, "getAllMatchTargetsForTopic", allSubscriptions);
/*      */     }
/*      */     
/* 1171 */     return allSubscriptions;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized FastVector getAllAdminMatchTargetsForTopic(String topic) {
/* 1192 */     if (debug.debugIt(32)) {
/* 1193 */       debug.debug(-165922073994779L, "getAllAdminMatchTargetsForTopic", topic);
/*      */     }
/*      */     
/* 1196 */     FastVector allSubscriptions = new FastVector();
/* 1197 */     getAllMatchTargetsForTopicInternal(this.adminSubs, topic, allSubscriptions);
/*      */     
/* 1199 */     if (debug.debugIt(64)) {
/* 1200 */       debug.debug(-142394261359015L, "getAllAdminMatchTargetsForTopic", allSubscriptions);
/*      */     }
/*      */     
/* 1203 */     return allSubscriptions;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void getAllMatchTargetsForTopicInternal(Hashtable subs, String topic, FastVector ans) {
/* 1211 */     if (debug.debugIt(32)) {
/* 1212 */       debug.debug(-165922073994779L, "getAllMatchTargetsForTopicInternal", subs, topic, ans);
/*      */     }
/*      */     
/* 1215 */     Hashtable mTargets = (Hashtable)subs.get(topic);
/* 1216 */     if (mTargets != null) {
/* 1217 */       Enumeration<MatchTarget> theObjs = mTargets.keys();
/*      */       
/* 1219 */       while (theObjs.hasMoreElements()) {
/* 1220 */         MatchTarget nextObj = theObjs.nextElement();
/* 1221 */         ans.addElement(nextObj);
/*      */       } 
/*      */     } 
/*      */     
/* 1225 */     if (debug.debugIt(64)) {
/* 1226 */       debug.debug(-142394261359015L, "getAllMatchTargetsForTopicInternal");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized void clear(String subPoint) throws MatchingException {
/* 1247 */     if (debug.debugIt(32)) {
/* 1248 */       debug.debug(-165922073994779L, "clear", subPoint);
/*      */     }
/*      */     
/* 1251 */     SubscriptionPoint sbpt = getSubPoint(subPoint);
/* 1252 */     if (sbpt != null) {
/* 1253 */       sbpt.subSpace.clear();
/* 1254 */       sbpt.subscriptions.clear();
/*      */     } 
/*      */     
/* 1257 */     if (debug.debugIt(64)) {
/* 1258 */       debug.debug(-142394261359015L, "clear");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized void clearAdmin() throws MatchingException {
/* 1277 */     if (debug.debugIt(32)) {
/* 1278 */       debug.debug(-165922073994779L, "clearAdmin");
/*      */     }
/*      */     
/* 1281 */     this.admin.clear();
/*      */     
/* 1283 */     if (debug.debugIt(64)) {
/* 1284 */       debug.debug(-142394261359015L, "clearAdmin");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void statistics(PrintWriter wtr) {
/* 1295 */     this.defaultSubPoint.subSpace.statistics(wtr);
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\MatchSpace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */