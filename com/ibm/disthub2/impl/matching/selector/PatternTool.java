/*     */ package com.ibm.disthub2.impl.matching.selector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class PatternTool
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   private static final char MATCHMANY = '%';
/*     */   private static final char MATCHONE = '_';
/*     */   
/*     */   private static final boolean checkPos(String pattern, int ip, String str, int is, char esc, int[] scratch) {
/*  71 */     if (ip == pattern.length()) {
/*  72 */       if (is == str.length()) {
/*  73 */         return true;
/*     */       }
/*  75 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  82 */     while (ip < pattern.length() && pattern.charAt(ip) != '%') {
/*     */       
/*  84 */       if (pattern.charAt(ip) == esc) {
/*     */ 
/*     */         
/*  87 */         if (++ip == pattern.length()) {
/*  88 */           return false;
/*     */         }
/*  90 */         if (is == str.length() || pattern.charAt(ip) != str.charAt(is)) {
/*  91 */           return false;
/*     */         
/*     */         }
/*     */       }
/*  95 */       else if (is == str.length() || (pattern
/*  96 */         .charAt(ip) != str.charAt(is) && pattern
/*  97 */         .charAt(ip) != '_')) {
/*  98 */         return false;
/*  99 */       }  ip++;
/* 100 */       is++;
/*     */     } 
/* 102 */     scratch[0] = ip;
/* 103 */     scratch[1] = is;
/* 104 */     return true;
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
/*     */   private static boolean findFirst(String pattern, int ip, String str, int is, char esc, int[] scratch) {
/* 128 */     while (is < str.length()) {
/*     */       
/* 130 */       if (checkPos(pattern, ip, str, is, esc, scratch)) {
/* 131 */         return true;
/*     */       }
/* 133 */       is++;
/*     */     } 
/* 135 */     return false;
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
/*     */   public static boolean match(String pattern, String str, char esc, int[] scratch) {
/* 158 */     int ip = 0;
/* 159 */     int is = 0;
/* 160 */     int old_ip = 0;
/* 161 */     int old_is = 0;
/*     */     
/* 163 */     if (!checkPos(pattern, 0, str, 0, esc, scratch)) {
/* 164 */       return false;
/*     */     }
/* 166 */     ip = scratch[0];
/* 167 */     is = scratch[1];
/*     */     
/* 169 */     if (ip == pattern.length()) {
/*     */ 
/*     */       
/* 172 */       if (is == str.length()) {
/* 173 */         return true;
/*     */       }
/* 175 */       return false;
/*     */     } 
/*     */     
/* 178 */     while (ip < pattern.length()) {
/* 179 */       ip++;
/* 180 */       if (ip == pattern.length())
/* 181 */         return true; 
/* 182 */       if (!findFirst(pattern, ip, str, is, esc, scratch)) {
/* 183 */         return false;
/*     */       }
/* 185 */       old_ip = ip;
/* 186 */       old_is = is;
/* 187 */       ip = scratch[0];
/* 188 */       is = scratch[1];
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 194 */     if (is == str.length()) {
/* 195 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 203 */     int nleft = 0;
/* 204 */     for (ip = old_ip; ip < pattern.length(); ip++) {
/*     */       
/* 206 */       if (pattern.charAt(ip) == esc) {
/*     */         
/* 208 */         ip++;
/* 209 */         if (ip == pattern.length())
/*     */         {
/*     */ 
/*     */           
/* 213 */           return false; } 
/*     */       } 
/* 215 */       nleft++;
/*     */     } 
/*     */     
/* 218 */     if (str.length() - nleft < old_is)
/*     */     {
/*     */ 
/*     */ 
/*     */       
/* 223 */       return false;
/*     */     }
/*     */     
/* 226 */     if (checkPos(pattern, old_ip, str, str.length() - nleft, esc, scratch)) {
/* 227 */       return true;
/*     */     }
/* 229 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\selector\PatternTool.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */