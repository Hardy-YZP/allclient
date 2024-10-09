/*     */ package com.ibm.disthub2.impl.util;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class TopicHandler
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2004 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  42 */   public static final Object MATCHONE = new Object();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean checkTopicSyntax(String topic, char separator, char matchOne, char matchMany) throws IllegalArgumentException {
/*  66 */     boolean hasWild = false;
/*     */     
/*  68 */     if (topic == null)
/*     */     {
/*  70 */       throw new IllegalArgumentException();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  77 */     StringBuffer twoSeparators = new StringBuffer();
/*  78 */     twoSeparators.append(separator);
/*  79 */     twoSeparators.append(separator);
/*     */     
/*  81 */     if (topic.indexOf(twoSeparators.toString()) > -1)
/*     */     {
/*  83 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/*  86 */     if (findFirstMatchOneWildcard(topic, matchOne, separator) > -1) {
/*  87 */       hasWild = true;
/*     */     }
/*     */     
/*  90 */     int pos1 = findFirstMatchManyWildcard(topic, matchMany, separator);
/*  91 */     if (pos1 > -1) {
/*     */       
/*  93 */       if (findLastMatchManyWildcard(topic, matchMany, separator) > pos1)
/*     */       {
/*  95 */         throw new IllegalArgumentException();
/*     */       }
/*     */       
/*  98 */       hasWild = true;
/*     */     } 
/*     */     
/* 101 */     return hasWild;
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
/*     */   private static final int findFirstMatchManyWildcard(String topic, char matchMany, char separator) {
/* 119 */     return findFirstMatchManyWildcard(topic, matchMany, separator, 0);
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
/*     */   private static final int findFirstMatchManyWildcard(String topic, char matchMany, char separator, int startpos) {
/* 138 */     int firstOccurrence = -1;
/*     */     
/* 140 */     int topiclength = topic.length();
/*     */ 
/*     */     
/* 143 */     if (topic == null || topiclength == 0 || startpos >= topiclength) {
/* 144 */       return -1;
/*     */     }
/*     */     
/* 147 */     if (topiclength == 1) {
/*     */       
/* 149 */       if (topic.charAt(0) == matchMany) {
/* 150 */         return 0;
/*     */       }
/*     */       
/* 153 */       return -1;
/*     */     } 
/*     */ 
/*     */     
/* 157 */     if (topic.charAt(startpos) == matchMany && topic
/* 158 */       .charAt(startpos + 1) == separator) {
/* 159 */       return 0;
/*     */     }
/*     */     
/* 162 */     firstOccurrence = topic.indexOf("" + separator + matchMany + separator, startpos);
/* 163 */     if (firstOccurrence != -1) {
/* 164 */       return firstOccurrence + 1;
/*     */     }
/*     */     
/* 167 */     if (topic.charAt(topiclength - 2) == separator && topic
/* 168 */       .charAt(topiclength - 1) == matchMany) {
/* 169 */       return topiclength - 1;
/*     */     }
/*     */     
/* 172 */     return firstOccurrence;
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
/*     */   private static final int findFirstMatchOneWildcard(String topic, char matchOne, char separator, int startpos, int endpos) {
/* 190 */     int i = findFirstMatchOneWildcard(topic.substring(startpos, endpos), matchOne, separator);
/*     */ 
/*     */     
/* 193 */     if (i == -1) {
/* 194 */       return -1;
/*     */     }
/*     */     
/* 197 */     return i + startpos;
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
/*     */   private static final int findFirstMatchOneWildcard(String topic, char matchOne, char separator) {
/* 215 */     int firstOccurrence = -1;
/*     */     
/* 217 */     int topiclength = topic.length();
/*     */ 
/*     */     
/* 220 */     if (topic == null || topiclength == 0) {
/* 221 */       return -1;
/*     */     }
/*     */     
/* 224 */     if (topiclength == 1) {
/*     */       
/* 226 */       if (topic.charAt(0) == matchOne) {
/* 227 */         return 0;
/*     */       }
/*     */       
/* 230 */       return -1;
/*     */     } 
/*     */ 
/*     */     
/* 234 */     if (topic.charAt(0) == matchOne && topic
/* 235 */       .charAt(1) == separator) {
/* 236 */       return 0;
/*     */     }
/*     */     
/* 239 */     firstOccurrence = topic.indexOf("" + separator + matchOne + separator);
/* 240 */     if (firstOccurrence != -1) {
/* 241 */       return firstOccurrence + 1;
/*     */     }
/*     */     
/* 244 */     if (topic.charAt(topiclength - 2) == separator && topic
/* 245 */       .charAt(topiclength - 1) == matchOne) {
/* 246 */       return topiclength - 1;
/*     */     }
/*     */     
/* 249 */     return firstOccurrence;
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
/*     */   private static final int findLastMatchManyWildcard(String topic, char matchMany, char separator) {
/* 267 */     int lastOccurrence = -1;
/*     */     
/* 269 */     int topiclength = topic.length();
/*     */ 
/*     */     
/* 272 */     if (topic == null || topiclength == 0) {
/* 273 */       return -1;
/*     */     }
/*     */     
/* 276 */     if (topiclength == 1) {
/*     */       
/* 278 */       if (topic.charAt(0) == matchMany) {
/* 279 */         return 0;
/*     */       }
/*     */       
/* 282 */       return -1;
/*     */     } 
/*     */ 
/*     */     
/* 286 */     if (topic.charAt(topiclength - 2) == separator && topic
/* 287 */       .charAt(topiclength - 1) == matchMany) {
/* 288 */       return topiclength - 1;
/*     */     }
/*     */     
/* 291 */     lastOccurrence = topic.lastIndexOf("" + separator + matchMany + separator);
/* 292 */     if (lastOccurrence != -1) {
/* 293 */       return lastOccurrence + 1;
/*     */     }
/*     */     
/* 296 */     if (topic.charAt(0) == matchMany && topic
/* 297 */       .charAt(1) == separator) {
/* 298 */       return 0;
/*     */     }
/*     */     
/* 301 */     return lastOccurrence;
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
/*     */   public static boolean topicMatch(Object[][] pattern, String topic, char separator) {
/* 321 */     if (pattern.length < 1 || pattern.length > 2) {
/* 322 */       return false;
/*     */     }
/*     */     
/* 325 */     String residue = topicMatchForward(pattern[0], topic, separator);
/*     */     
/* 327 */     if (residue == null)
/*     */     {
/* 329 */       return false;
/*     */     }
/* 331 */     if (pattern.length == 1)
/*     */     {
/* 333 */       return (residue.length() == 0);
/*     */     }
/*     */ 
/*     */     
/* 337 */     return topicMatchBackward(pattern[1], residue, separator);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String topicMatchForward(Object[] pattern, String topic, char separator) {
/* 347 */     for (int i = 0; i < pattern.length; i++) {
/*     */       
/* 349 */       if (pattern[i] == MATCHONE) {
/*     */         
/* 351 */         if (topic.length() == 0) {
/* 352 */           return null;
/*     */         }
/* 354 */         topic = stripLeft(topic, separator);
/*     */       }
/*     */       else {
/*     */         
/* 358 */         String pat = (String)pattern[i];
/*     */         
/* 360 */         if (!topic.startsWith(pat)) {
/* 361 */           return null;
/*     */         }
/* 363 */         if (topic.length() > pat.length() && topic.charAt(pat.length()) != separator) {
/* 364 */           return null;
/*     */         }
/* 366 */         if (topic.length() > pat.length() + 1) {
/* 367 */           topic = topic.substring(pat.length() + 1);
/*     */         } else {
/* 369 */           topic = "";
/*     */         } 
/*     */       } 
/* 372 */     }  return topic;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean topicMatchBackward(Object[] pattern, String residue, char separator) {
/* 381 */     for (int i = pattern.length - 1; i >= 0; i--) {
/*     */       
/* 383 */       if (pattern[i] == MATCHONE) {
/*     */         
/* 385 */         if (residue.length() == 0) {
/* 386 */           return false;
/*     */         }
/* 388 */         residue = stripRight(residue, separator);
/*     */       }
/*     */       else {
/*     */         
/* 392 */         String pat = (String)pattern[i];
/*     */         
/* 394 */         if (!residue.endsWith(pat)) {
/* 395 */           return false;
/*     */         }
/* 397 */         if (residue.length() > pat.length() && residue
/* 398 */           .charAt(residue.length() - pat.length() - 1) != separator) {
/* 399 */           return false;
/*     */         }
/* 401 */         if (residue.length() > pat.length() + 1) {
/* 402 */           residue = residue.substring(0, residue.length() - pat.length() - 1);
/*     */         } else {
/* 404 */           residue = "";
/*     */         } 
/*     */       } 
/* 407 */     }  return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String stripLeft(String topic, char separator) {
/* 414 */     int ind = topic.indexOf(separator);
/* 415 */     if (ind == -1)
/* 416 */       return ""; 
/* 417 */     return topic.substring(ind + 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String stripRight(String topic, char separator) {
/* 424 */     int ind = topic.lastIndexOf(separator);
/* 425 */     if (ind == -1)
/* 426 */       return ""; 
/* 427 */     return topic.substring(0, ind);
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
/*     */   public static Object[][] parsePattern(String pattern, char matchOne, char matchMany) {
/* 457 */     if (pattern.charAt(0) == matchMany && (pattern
/* 458 */       .length() == 1 || (pattern.length() == 2 && pattern.charAt(1) == '/'))) {
/* 459 */       return new Object[][] { {}, {} };
/*     */     }
/* 461 */     int i = 0;
/* 462 */     int topicLen = pattern.length();
/* 463 */     FastVector rows = new FastVector();
/* 464 */     FastVector row = new FastVector();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 470 */     while (i < topicLen) {
/*     */ 
/*     */       
/* 473 */       int p = findFirstMatchManyWildcard(pattern, matchMany, '/', i);
/*     */       
/* 475 */       if (p == -1) {
/*     */ 
/*     */ 
/*     */         
/* 479 */         makeRow(row, pattern, matchOne, i, topicLen);
/*     */ 
/*     */         
/* 482 */         i = topicLen;
/*     */       }
/*     */       else {
/*     */         
/* 486 */         if (p == i) {
/*     */ 
/*     */           
/* 489 */           row.reset();
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 494 */           makeRow(row, pattern, matchOne, i, p - 1);
/*     */ 
/*     */ 
/*     */           
/* 498 */           if (p == topicLen - 1 || p == topicLen - 2) {
/*     */             
/* 500 */             Object[] arrayOfObject = new Object[row.m_count];
/* 501 */             System.arraycopy(row.m_data, 0, arrayOfObject, 0, arrayOfObject.length);
/* 502 */             rows.addElement(arrayOfObject);
/* 503 */             row.reset();
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 508 */         i = p + 2;
/*     */       } 
/*     */       
/* 511 */       Object[] part = new Object[row.m_count];
/* 512 */       System.arraycopy(row.m_data, 0, part, 0, part.length);
/* 513 */       rows.addElement(part);
/*     */     } 
/*     */     
/* 516 */     Object[][] topicPattern = new Object[rows.m_count][];
/* 517 */     System.arraycopy(rows.m_data, 0, topicPattern, 0, topicPattern.length);
/*     */     
/* 519 */     return topicPattern;
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
/*     */   private static final void makeRow(FastVector row, String topic, char matchOne, int startpos, int endpos) {
/* 539 */     int j = startpos;
/* 540 */     row.reset();
/*     */ 
/*     */     
/* 543 */     while (j < endpos) {
/*     */ 
/*     */       
/* 546 */       int p = findFirstMatchOneWildcard(topic, matchOne, '/', j, endpos);
/*     */       
/* 548 */       if (p == -1) {
/*     */ 
/*     */         
/* 551 */         row.addElement(topic.substring(j, endpos));
/*     */ 
/*     */         
/* 554 */         j = endpos;
/*     */         
/*     */         continue;
/*     */       } 
/* 558 */       if (p == j) {
/*     */ 
/*     */         
/* 561 */         row.addElement(MATCHONE);
/*     */       }
/*     */       else {
/*     */         
/* 565 */         row.addElement(topic.substring(j, p - 1));
/*     */ 
/*     */         
/* 568 */         row.addElement(MATCHONE);
/*     */       } 
/*     */ 
/*     */       
/* 572 */       j = p + 2;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\imp\\util\TopicHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */