/*     */ package com.ibm.disthub2.impl.matching.selector;
/*     */ 
/*     */ import com.ibm.disthub2.impl.util.FastVector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ParseUtil
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   
/*     */   static Selector parseStringLiteral(String image) {
/*  47 */     image = image.substring(1, image.length() - 1);
/*     */     
/*  49 */     for (int i = 0; i < image.length(); i++) {
/*  50 */       if (image.charAt(i) == '\'')
/*     */       {
/*  52 */         image = image.substring(0, i + 1) + image.substring(i + 2); } 
/*     */     } 
/*  54 */     return new Literal(image);
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
/*     */   static Selector parseIntegerLiteral(String val) {
/*  67 */     char tag = val.charAt(val.length() - 1);
/*  68 */     boolean mustBeLong = false;
/*  69 */     if (tag == 'l' || tag == 'L') {
/*  70 */       val = val.substring(0, val.length() - 1);
/*  71 */       mustBeLong = true;
/*     */     } 
/*     */     try {
/*  74 */       return new Literal(new NumericValue(Long.parseLong(val), mustBeLong));
/*  75 */     } catch (NumberFormatException e) {
/*  76 */       return new Literal(new NumericValue(Long.decode(val).longValue(), mustBeLong));
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
/*     */   static Selector parseFloatingLiteral(String val) {
/*     */     NumericValue value;
/*  91 */     char tag = val.charAt(val.length() - 1);
/*  92 */     if (tag == 'f' || tag == 'F') {
/*  93 */       value = new NumericValue(Float.parseFloat(val));
/*     */     } else {
/*  95 */       value = new NumericValue(Double.parseDouble(val));
/*  96 */     }  return new Literal(value);
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
/*     */   static Selector convertSet(Selector expr, FastVector set) {
/* 112 */     Selector ans = null;
/* 113 */     for (int i = 0; i < set.m_count; i++) {
/* 114 */       Selector comparand = (Selector)set.m_data[i];
/* 115 */       Selector comparison = new Operator(45, (Selector)expr.clone(), comparand);
/* 116 */       if (ans == null) {
/* 117 */         ans = comparison;
/*     */       } else {
/* 119 */         ans = new Operator(47, ans, comparison);
/*     */       } 
/* 121 */     }  return ans;
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
/*     */   static Selector convertRange(Selector expr, Selector bound1, Selector bound2) {
/* 138 */     return new Operator(46, new Operator(43, (Selector)expr
/* 139 */           .clone(), bound1), new Operator(44, (Selector)expr
/* 140 */           .clone(), bound2));
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\selector\ParseUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */