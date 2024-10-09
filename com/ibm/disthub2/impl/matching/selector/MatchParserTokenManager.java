/*      */ package com.ibm.disthub2.impl.matching.selector;
/*      */ 
/*      */ import java.io.IOException;
/*      */ 
/*      */ public class MatchParserTokenManager
/*      */   implements MatchParserConstants {
/*      */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2012 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*      */   
/*      */   private final int jjStopAtPos(int pos, int kind) {
/*   10 */     this.jjmatchedKind = kind;
/*   11 */     this.jjmatchedPos = pos;
/*   12 */     return pos + 1;
/*      */   }
/*      */   
/*      */   private final int jjMoveStringLiteralDfa0_0() {
/*   16 */     switch (this.curChar) {
/*      */       
/*      */       case '\t':
/*   19 */         this.jjmatchedKind = 18;
/*   20 */         return jjMoveNfa_0(0, 0);
/*      */       case '\n':
/*   22 */         this.jjmatchedKind = 19;
/*   23 */         return jjMoveNfa_0(0, 0);
/*      */       case '\f':
/*   25 */         this.jjmatchedKind = 21;
/*   26 */         return jjMoveNfa_0(0, 0);
/*      */       case '\r':
/*   28 */         this.jjmatchedKind = 20;
/*   29 */         return jjMoveNfa_0(0, 0);
/*      */       case ' ':
/*   31 */         this.jjmatchedKind = 17;
/*   32 */         return jjMoveNfa_0(0, 0);
/*      */       case '(':
/*   34 */         this.jjmatchedKind = 11;
/*   35 */         return jjMoveNfa_0(0, 0);
/*      */       case ')':
/*   37 */         this.jjmatchedKind = 12;
/*   38 */         return jjMoveNfa_0(0, 0);
/*      */       case '*':
/*   40 */         this.jjmatchedKind = 9;
/*   41 */         return jjMoveNfa_0(0, 0);
/*      */       case '+':
/*   43 */         this.jjmatchedKind = 7;
/*   44 */         return jjMoveNfa_0(0, 0);
/*      */       case ',':
/*   46 */         this.jjmatchedKind = 13;
/*   47 */         return jjMoveNfa_0(0, 0);
/*      */       case '-':
/*   49 */         this.jjmatchedKind = 8;
/*   50 */         return jjMoveNfa_0(0, 0);
/*      */       case '.':
/*   52 */         this.jjmatchedKind = 14;
/*   53 */         return jjMoveNfa_0(0, 0);
/*      */       case '/':
/*   55 */         this.jjmatchedKind = 10;
/*   56 */         return jjMoveNfa_0(0, 0);
/*      */       case '<':
/*   58 */         this.jjmatchedKind = 1;
/*   59 */         return jjMoveStringLiteralDfa1_0(40L);
/*      */       case '=':
/*   61 */         this.jjmatchedKind = 6;
/*   62 */         return jjMoveNfa_0(0, 0);
/*      */       case '>':
/*   64 */         this.jjmatchedKind = 2;
/*   65 */         return jjMoveStringLiteralDfa1_0(16L);
/*      */       case 'A':
/*   67 */         return jjMoveStringLiteralDfa1_0(268435456L);
/*      */       case 'B':
/*   69 */         return jjMoveStringLiteralDfa1_0(16777216L);
/*      */       case 'E':
/*   71 */         return jjMoveStringLiteralDfa1_0(8657043456L);
/*      */       case 'F':
/*   73 */         return jjMoveStringLiteralDfa1_0(4294967296L);
/*      */       case 'I':
/*   75 */         return jjMoveStringLiteralDfa1_0(17192452096L);
/*      */       case 'L':
/*   77 */         return jjMoveStringLiteralDfa1_0(33554432L);
/*      */       case 'N':
/*   79 */         return jjMoveStringLiteralDfa1_0(1610612736L);
/*      */       case 'O':
/*   81 */         return jjMoveStringLiteralDfa1_0(134217728L);
/*      */       case 'T':
/*   83 */         return jjMoveStringLiteralDfa1_0(2147483648L);
/*      */       case '[':
/*   85 */         this.jjmatchedKind = 15;
/*   86 */         return jjMoveNfa_0(0, 0);
/*      */       case ']':
/*   88 */         this.jjmatchedKind = 16;
/*   89 */         return jjMoveNfa_0(0, 0);
/*      */       case 'a':
/*   91 */         return jjMoveStringLiteralDfa1_0(268435456L);
/*      */       case 'b':
/*   93 */         return jjMoveStringLiteralDfa1_0(16777216L);
/*      */       case 'e':
/*   95 */         return jjMoveStringLiteralDfa1_0(8657043456L);
/*      */       case 'f':
/*   97 */         return jjMoveStringLiteralDfa1_0(4294967296L);
/*      */       case 'i':
/*   99 */         return jjMoveStringLiteralDfa1_0(17192452096L);
/*      */       case 'l':
/*  101 */         return jjMoveStringLiteralDfa1_0(33554432L);
/*      */       case 'n':
/*  103 */         return jjMoveStringLiteralDfa1_0(1610612736L);
/*      */       case 'o':
/*  105 */         return jjMoveStringLiteralDfa1_0(134217728L);
/*      */       case 't':
/*  107 */         return jjMoveStringLiteralDfa1_0(2147483648L);
/*      */     } 
/*  109 */     return jjMoveNfa_0(0, 0);
/*      */   }
/*      */   
/*      */   private final int jjMoveStringLiteralDfa1_0(long active0) {
/*      */     try {
/*  114 */       this.curChar = this.input_stream.readChar();
/*  115 */     } catch (IOException e) {
/*  116 */       return jjMoveNfa_0(0, 0);
/*      */     } 
/*  118 */     switch (this.curChar) {
/*      */       
/*      */       case '=':
/*  121 */         if ((active0 & 0x8L) != 0L) {
/*      */           
/*  123 */           this.jjmatchedKind = 3;
/*  124 */           this.jjmatchedPos = 1; break;
/*      */         } 
/*  126 */         if ((active0 & 0x10L) != 0L) {
/*      */           
/*  128 */           this.jjmatchedKind = 4;
/*  129 */           this.jjmatchedPos = 1;
/*      */         } 
/*      */         break;
/*      */       case '>':
/*  133 */         if ((active0 & 0x20L) != 0L) {
/*      */           
/*  135 */           this.jjmatchedKind = 5;
/*  136 */           this.jjmatchedPos = 1;
/*      */         } 
/*      */         break;
/*      */       case 'A':
/*  140 */         return jjMoveStringLiteralDfa2_0(active0, 4294967296L);
/*      */       case 'E':
/*  142 */         return jjMoveStringLiteralDfa2_0(active0, 16777216L);
/*      */       case 'I':
/*  144 */         return jjMoveStringLiteralDfa2_0(active0, 33554432L);
/*      */       case 'M':
/*  146 */         return jjMoveStringLiteralDfa2_0(active0, 8589934592L);
/*      */       case 'N':
/*  148 */         if ((active0 & 0x800000L) != 0L) {
/*      */           
/*  150 */           this.jjmatchedKind = 23;
/*  151 */           this.jjmatchedPos = 1;
/*      */         } 
/*  153 */         return jjMoveStringLiteralDfa2_0(active0, 268435456L);
/*      */       case 'O':
/*  155 */         return jjMoveStringLiteralDfa2_0(active0, 536870912L);
/*      */       case 'R':
/*  157 */         if ((active0 & 0x8000000L) != 0L) {
/*      */           
/*  159 */           this.jjmatchedKind = 27;
/*  160 */           this.jjmatchedPos = 1;
/*      */         } 
/*  162 */         return jjMoveStringLiteralDfa2_0(active0, 2147483648L);
/*      */       case 'S':
/*  164 */         if ((active0 & 0x400000L) != 0L) {
/*      */           
/*  166 */           this.jjmatchedKind = 22;
/*  167 */           this.jjmatchedPos = 1;
/*      */         } 
/*  169 */         return jjMoveStringLiteralDfa2_0(active0, 17246978048L);
/*      */       case 'U':
/*  171 */         return jjMoveStringLiteralDfa2_0(active0, 1073741824L);
/*      */       case 'a':
/*  173 */         return jjMoveStringLiteralDfa2_0(active0, 4294967296L);
/*      */       case 'e':
/*  175 */         return jjMoveStringLiteralDfa2_0(active0, 16777216L);
/*      */       case 'i':
/*  177 */         return jjMoveStringLiteralDfa2_0(active0, 33554432L);
/*      */       case 'm':
/*  179 */         return jjMoveStringLiteralDfa2_0(active0, 8589934592L);
/*      */       case 'n':
/*  181 */         if ((active0 & 0x800000L) != 0L) {
/*      */           
/*  183 */           this.jjmatchedKind = 23;
/*  184 */           this.jjmatchedPos = 1;
/*      */         } 
/*  186 */         return jjMoveStringLiteralDfa2_0(active0, 268435456L);
/*      */       case 'o':
/*  188 */         return jjMoveStringLiteralDfa2_0(active0, 536870912L);
/*      */       case 'r':
/*  190 */         if ((active0 & 0x8000000L) != 0L) {
/*      */           
/*  192 */           this.jjmatchedKind = 27;
/*  193 */           this.jjmatchedPos = 1;
/*      */         } 
/*  195 */         return jjMoveStringLiteralDfa2_0(active0, 2147483648L);
/*      */       case 's':
/*  197 */         if ((active0 & 0x400000L) != 0L) {
/*      */           
/*  199 */           this.jjmatchedKind = 22;
/*  200 */           this.jjmatchedPos = 1;
/*      */         } 
/*  202 */         return jjMoveStringLiteralDfa2_0(active0, 17246978048L);
/*      */       case 'u':
/*  204 */         return jjMoveStringLiteralDfa2_0(active0, 1073741824L);
/*      */     } 
/*      */ 
/*      */     
/*  208 */     return jjMoveNfa_0(0, 1);
/*      */   }
/*      */   
/*      */   private final int jjMoveStringLiteralDfa2_0(long old0, long active0) {
/*  212 */     if ((active0 &= old0) == 0L)
/*  213 */       return jjMoveNfa_0(0, 1);  try {
/*  214 */       this.curChar = this.input_stream.readChar();
/*  215 */     } catch (IOException e) {
/*  216 */       return jjMoveNfa_0(0, 1);
/*      */     } 
/*  218 */     switch (this.curChar) {
/*      */       
/*      */       case 'A':
/*  221 */         if ((active0 & 0x400000000L) != 0L) {
/*      */           
/*  223 */           this.jjmatchedKind = 34;
/*  224 */           this.jjmatchedPos = 2;
/*      */         } 
/*      */         break;
/*      */       case 'C':
/*  228 */         return jjMoveStringLiteralDfa3_0(active0, 67108864L);
/*      */       case 'D':
/*  230 */         if ((active0 & 0x10000000L) != 0L) {
/*      */           
/*  232 */           this.jjmatchedKind = 28;
/*  233 */           this.jjmatchedPos = 2;
/*      */         } 
/*      */         break;
/*      */       case 'K':
/*  237 */         return jjMoveStringLiteralDfa3_0(active0, 33554432L);
/*      */       case 'L':
/*  239 */         return jjMoveStringLiteralDfa3_0(active0, 5368709120L);
/*      */       case 'P':
/*  241 */         return jjMoveStringLiteralDfa3_0(active0, 8589934592L);
/*      */       case 'T':
/*  243 */         if ((active0 & 0x20000000L) != 0L) {
/*      */           
/*  245 */           this.jjmatchedKind = 29;
/*  246 */           this.jjmatchedPos = 2;
/*      */         } 
/*  248 */         return jjMoveStringLiteralDfa3_0(active0, 16777216L);
/*      */       case 'U':
/*  250 */         return jjMoveStringLiteralDfa3_0(active0, 2147483648L);
/*      */       case 'a':
/*  252 */         if ((active0 & 0x400000000L) != 0L) {
/*      */           
/*  254 */           this.jjmatchedKind = 34;
/*  255 */           this.jjmatchedPos = 2;
/*      */         } 
/*      */         break;
/*      */       case 'c':
/*  259 */         return jjMoveStringLiteralDfa3_0(active0, 67108864L);
/*      */       case 'd':
/*  261 */         if ((active0 & 0x10000000L) != 0L) {
/*      */           
/*  263 */           this.jjmatchedKind = 28;
/*  264 */           this.jjmatchedPos = 2;
/*      */         } 
/*      */         break;
/*      */       case 'k':
/*  268 */         return jjMoveStringLiteralDfa3_0(active0, 33554432L);
/*      */       case 'l':
/*  270 */         return jjMoveStringLiteralDfa3_0(active0, 5368709120L);
/*      */       case 'p':
/*  272 */         return jjMoveStringLiteralDfa3_0(active0, 8589934592L);
/*      */       case 't':
/*  274 */         if ((active0 & 0x20000000L) != 0L) {
/*      */           
/*  276 */           this.jjmatchedKind = 29;
/*  277 */           this.jjmatchedPos = 2;
/*      */         } 
/*  279 */         return jjMoveStringLiteralDfa3_0(active0, 16777216L);
/*      */       case 'u':
/*  281 */         return jjMoveStringLiteralDfa3_0(active0, 2147483648L);
/*      */     } 
/*      */ 
/*      */     
/*  285 */     return jjMoveNfa_0(0, 2);
/*      */   }
/*      */   
/*      */   private final int jjMoveStringLiteralDfa3_0(long old0, long active0) {
/*  289 */     if ((active0 &= old0) == 0L)
/*  290 */       return jjMoveNfa_0(0, 2);  try {
/*  291 */       this.curChar = this.input_stream.readChar();
/*  292 */     } catch (IOException e) {
/*  293 */       return jjMoveNfa_0(0, 2);
/*      */     } 
/*  295 */     switch (this.curChar) {
/*      */       
/*      */       case 'A':
/*  298 */         return jjMoveStringLiteralDfa4_0(active0, 67108864L);
/*      */       case 'E':
/*  300 */         if ((active0 & 0x2000000L) != 0L) {
/*      */           
/*  302 */           this.jjmatchedKind = 25;
/*  303 */           this.jjmatchedPos = 3; break;
/*      */         } 
/*  305 */         if ((active0 & 0x80000000L) != 0L) {
/*      */           
/*  307 */           this.jjmatchedKind = 31;
/*  308 */           this.jjmatchedPos = 3;
/*      */         } 
/*      */         break;
/*      */       case 'L':
/*  312 */         if ((active0 & 0x40000000L) != 0L) {
/*      */           
/*  314 */           this.jjmatchedKind = 30;
/*  315 */           this.jjmatchedPos = 3;
/*      */         } 
/*      */         break;
/*      */       case 'S':
/*  319 */         return jjMoveStringLiteralDfa4_0(active0, 4294967296L);
/*      */       case 'T':
/*  321 */         return jjMoveStringLiteralDfa4_0(active0, 8589934592L);
/*      */       case 'W':
/*  323 */         return jjMoveStringLiteralDfa4_0(active0, 16777216L);
/*      */       case 'a':
/*  325 */         return jjMoveStringLiteralDfa4_0(active0, 67108864L);
/*      */       case 'e':
/*  327 */         if ((active0 & 0x2000000L) != 0L) {
/*      */           
/*  329 */           this.jjmatchedKind = 25;
/*  330 */           this.jjmatchedPos = 3; break;
/*      */         } 
/*  332 */         if ((active0 & 0x80000000L) != 0L) {
/*      */           
/*  334 */           this.jjmatchedKind = 31;
/*  335 */           this.jjmatchedPos = 3;
/*      */         } 
/*      */         break;
/*      */       case 'l':
/*  339 */         if ((active0 & 0x40000000L) != 0L) {
/*      */           
/*  341 */           this.jjmatchedKind = 30;
/*  342 */           this.jjmatchedPos = 3;
/*      */         } 
/*      */         break;
/*      */       case 's':
/*  346 */         return jjMoveStringLiteralDfa4_0(active0, 4294967296L);
/*      */       case 't':
/*  348 */         return jjMoveStringLiteralDfa4_0(active0, 8589934592L);
/*      */       case 'w':
/*  350 */         return jjMoveStringLiteralDfa4_0(active0, 16777216L);
/*      */     } 
/*      */ 
/*      */     
/*  354 */     return jjMoveNfa_0(0, 3);
/*      */   }
/*      */   
/*      */   private final int jjMoveStringLiteralDfa4_0(long old0, long active0) {
/*  358 */     if ((active0 &= old0) == 0L)
/*  359 */       return jjMoveNfa_0(0, 3);  try {
/*  360 */       this.curChar = this.input_stream.readChar();
/*  361 */     } catch (IOException e) {
/*  362 */       return jjMoveNfa_0(0, 3);
/*      */     } 
/*  364 */     switch (this.curChar) {
/*      */       
/*      */       case 'E':
/*  367 */         if ((active0 & 0x100000000L) != 0L) {
/*      */           
/*  369 */           this.jjmatchedKind = 32;
/*  370 */           this.jjmatchedPos = 4;
/*      */         } 
/*  372 */         return jjMoveStringLiteralDfa5_0(active0, 16777216L);
/*      */       case 'P':
/*  374 */         return jjMoveStringLiteralDfa5_0(active0, 67108864L);
/*      */       case 'Y':
/*  376 */         if ((active0 & 0x200000000L) != 0L) {
/*      */           
/*  378 */           this.jjmatchedKind = 33;
/*  379 */           this.jjmatchedPos = 4;
/*      */         } 
/*      */         break;
/*      */       case 'e':
/*  383 */         if ((active0 & 0x100000000L) != 0L) {
/*      */           
/*  385 */           this.jjmatchedKind = 32;
/*  386 */           this.jjmatchedPos = 4;
/*      */         } 
/*  388 */         return jjMoveStringLiteralDfa5_0(active0, 16777216L);
/*      */       case 'p':
/*  390 */         return jjMoveStringLiteralDfa5_0(active0, 67108864L);
/*      */       case 'y':
/*  392 */         if ((active0 & 0x200000000L) != 0L) {
/*      */           
/*  394 */           this.jjmatchedKind = 33;
/*  395 */           this.jjmatchedPos = 4;
/*      */         } 
/*      */         break;
/*      */     } 
/*      */ 
/*      */     
/*  401 */     return jjMoveNfa_0(0, 4);
/*      */   }
/*      */   
/*      */   private final int jjMoveStringLiteralDfa5_0(long old0, long active0) {
/*  405 */     if ((active0 &= old0) == 0L)
/*  406 */       return jjMoveNfa_0(0, 4);  try {
/*  407 */       this.curChar = this.input_stream.readChar();
/*  408 */     } catch (IOException e) {
/*  409 */       return jjMoveNfa_0(0, 4);
/*      */     } 
/*  411 */     switch (this.curChar) {
/*      */       
/*      */       case 'E':
/*  414 */         if ((active0 & 0x4000000L) != 0L) {
/*      */           
/*  416 */           this.jjmatchedKind = 26;
/*  417 */           this.jjmatchedPos = 5;
/*      */         } 
/*  419 */         return jjMoveStringLiteralDfa6_0(active0, 16777216L);
/*      */       case 'e':
/*  421 */         if ((active0 & 0x4000000L) != 0L) {
/*      */           
/*  423 */           this.jjmatchedKind = 26;
/*  424 */           this.jjmatchedPos = 5;
/*      */         } 
/*  426 */         return jjMoveStringLiteralDfa6_0(active0, 16777216L);
/*      */     } 
/*      */ 
/*      */     
/*  430 */     return jjMoveNfa_0(0, 5);
/*      */   }
/*      */   
/*      */   private final int jjMoveStringLiteralDfa6_0(long old0, long active0) {
/*  434 */     if ((active0 &= old0) == 0L)
/*  435 */       return jjMoveNfa_0(0, 5);  try {
/*  436 */       this.curChar = this.input_stream.readChar();
/*  437 */     } catch (IOException e) {
/*  438 */       return jjMoveNfa_0(0, 5);
/*      */     } 
/*  440 */     switch (this.curChar) {
/*      */       
/*      */       case 'N':
/*  443 */         if ((active0 & 0x1000000L) != 0L) {
/*      */           
/*  445 */           this.jjmatchedKind = 24;
/*  446 */           this.jjmatchedPos = 6;
/*      */         } 
/*      */         break;
/*      */       case 'n':
/*  450 */         if ((active0 & 0x1000000L) != 0L) {
/*      */           
/*  452 */           this.jjmatchedKind = 24;
/*  453 */           this.jjmatchedPos = 6;
/*      */         } 
/*      */         break;
/*      */     } 
/*      */ 
/*      */     
/*  459 */     return jjMoveNfa_0(0, 6);
/*      */   }
/*      */   
/*      */   private final void jjCheckNAdd(int state) {
/*  463 */     if (this.jjrounds[state] != this.jjround) {
/*      */       
/*  465 */       this.jjstateSet[this.jjnewStateCnt++] = state;
/*  466 */       this.jjrounds[state] = this.jjround;
/*      */     } 
/*      */   }
/*      */   
/*      */   private final void jjAddStates(int start, int end) {
/*      */     do {
/*  472 */       this.jjstateSet[this.jjnewStateCnt++] = jjnextStates[start];
/*  473 */     } while (start++ != end);
/*      */   }
/*      */   
/*      */   private final void jjCheckNAddTwoStates(int state1, int state2) {
/*  477 */     jjCheckNAdd(state1);
/*  478 */     jjCheckNAdd(state2);
/*      */   }
/*      */   
/*      */   private final void jjCheckNAddStates(int start, int end) {
/*      */     do {
/*  483 */       jjCheckNAdd(jjnextStates[start]);
/*  484 */     } while (start++ != end);
/*      */   }
/*      */   
/*      */   private final void jjCheckNAddStates(int start) {
/*  488 */     jjCheckNAdd(jjnextStates[start]);
/*  489 */     jjCheckNAdd(jjnextStates[start + 1]);
/*      */   }
/*  491 */   static final long[] jjbitVec0 = new long[] { 2301339413881290750L, -16384L, 4294967295L, 432345564227567616L };
/*      */ 
/*      */   
/*  494 */   static final long[] jjbitVec2 = new long[] { 0L, 0L, 0L, -36028797027352577L };
/*      */ 
/*      */   
/*  497 */   static final long[] jjbitVec3 = new long[] { 0L, -1L, -1L, -1L };
/*      */ 
/*      */   
/*  500 */   static final long[] jjbitVec4 = new long[] { -1L, -1L, 65535L, 0L };
/*      */ 
/*      */   
/*  503 */   static final long[] jjbitVec5 = new long[] { -1L, -1L, 0L, 0L };
/*      */ 
/*      */   
/*  506 */   static final long[] jjbitVec6 = new long[] { 70368744177663L, 0L, 0L, 0L };
/*      */ 
/*      */   
/*  509 */   static final long[] jjbitVec7 = new long[] { -2L, -1L, -1L, -1L };
/*      */ 
/*      */   
/*  512 */   static final long[] jjbitVec8 = new long[] { 0L, 0L, -1L, -1L };
/*      */ 
/*      */ 
/*      */   
/*      */   private final int jjMoveNfa_0(int startState, int curPos) {
/*  517 */     int strKind = this.jjmatchedKind;
/*  518 */     int strPos = this.jjmatchedPos;
/*      */     int seenUpto;
/*  520 */     this.input_stream.backup(seenUpto = curPos + 1); 
/*  521 */     try { this.curChar = this.input_stream.readChar(); }
/*  522 */     catch (IOException e) { throw new Error("Internal Error"); }
/*  523 */      curPos = 0;
/*      */     
/*  525 */     int startsAt = 0;
/*  526 */     this.jjnewStateCnt = 47;
/*  527 */     int i = 1;
/*  528 */     this.jjstateSet[0] = startState;
/*  529 */     int kind = Integer.MAX_VALUE;
/*      */     
/*      */     while (true) {
/*  532 */       if (++this.jjround == Integer.MAX_VALUE)
/*  533 */         ReInitRounds(); 
/*  534 */       if (this.curChar < '@') {
/*      */         
/*  536 */         long l = 1L << this.curChar;
/*      */         
/*      */         do {
/*  539 */           switch (this.jjstateSet[--i]) {
/*      */             
/*      */             case 0:
/*  542 */               if ((0x3FF000000000000L & l) != 0L) {
/*      */                 
/*  544 */                 if (kind > 41)
/*  545 */                   kind = 41; 
/*  546 */                 jjCheckNAddStates(0, 6);
/*      */               }
/*  548 */               else if (this.curChar == '\'') {
/*  549 */                 jjCheckNAddStates(7, 9);
/*  550 */               } else if (this.curChar == '.') {
/*  551 */                 jjCheckNAdd(18);
/*  552 */               } else if (this.curChar == '-') {
/*  553 */                 this.jjstateSet[this.jjnewStateCnt++] = 12;
/*  554 */               } else if (this.curChar == '"') {
/*  555 */                 jjCheckNAddStates(10, 12);
/*  556 */               } else if (this.curChar == '$') {
/*      */                 
/*  558 */                 if (kind > 35)
/*  559 */                   kind = 35; 
/*  560 */                 jjCheckNAdd(1);
/*      */               } 
/*  562 */               if ((0x3FE000000000000L & l) != 0L) {
/*      */                 
/*  564 */                 if (kind > 40)
/*  565 */                   kind = 40; 
/*  566 */                 jjCheckNAddTwoStates(16, 14);
/*      */               }
/*  568 */               else if (this.curChar == '0') {
/*  569 */                 jjAddStates(13, 14);
/*  570 */               }  if (this.curChar == '0') {
/*      */                 
/*  572 */                 if (kind > 40)
/*  573 */                   kind = 40; 
/*  574 */                 jjCheckNAddTwoStates(13, 14);
/*      */               } 
/*      */               break;
/*      */             case 1:
/*  578 */               if ((0x3FF401000000000L & l) == 0L)
/*      */                 break; 
/*  580 */               if (kind > 35)
/*  581 */                 kind = 35; 
/*  582 */               jjCheckNAdd(1);
/*      */               break;
/*      */             case 2:
/*      */             case 5:
/*  586 */               if (this.curChar == '"')
/*  587 */                 jjCheckNAddStates(10, 12); 
/*      */               break;
/*      */             case 3:
/*  590 */               if ((0xFFFFFFFBFFFFDBFFL & l) != 0L)
/*  591 */                 jjCheckNAddStates(10, 12); 
/*      */               break;
/*      */             case 6:
/*  594 */               if (this.curChar == '"' && kind > 36)
/*  595 */                 kind = 36; 
/*      */               break;
/*      */             case 7:
/*  598 */               if ((0xFF000000000000L & l) != 0L)
/*  599 */                 jjCheckNAddStates(15, 18); 
/*      */               break;
/*      */             case 8:
/*  602 */               if ((0xFF000000000000L & l) != 0L)
/*  603 */                 jjCheckNAddStates(10, 12); 
/*      */               break;
/*      */             case 9:
/*  606 */               if ((0xF000000000000L & l) != 0L)
/*  607 */                 this.jjstateSet[this.jjnewStateCnt++] = 10; 
/*      */               break;
/*      */             case 10:
/*  610 */               if ((0xFF000000000000L & l) != 0L)
/*  611 */                 jjCheckNAdd(8); 
/*      */               break;
/*      */             case 11:
/*  614 */               if (this.curChar == '-')
/*  615 */                 this.jjstateSet[this.jjnewStateCnt++] = 12; 
/*      */               break;
/*      */             case 12:
/*  618 */               if (this.curChar != '0')
/*      */                 break; 
/*  620 */               if (kind > 40)
/*  621 */                 kind = 40; 
/*  622 */               jjCheckNAddTwoStates(13, 14);
/*      */               break;
/*      */             case 13:
/*  625 */               if ((0xFF000000000000L & l) == 0L)
/*      */                 break; 
/*  627 */               if (kind > 40)
/*  628 */                 kind = 40; 
/*  629 */               jjCheckNAddTwoStates(13, 14);
/*      */               break;
/*      */             case 15:
/*  632 */               if ((0x3FE000000000000L & l) == 0L)
/*      */                 break; 
/*  634 */               if (kind > 40)
/*  635 */                 kind = 40; 
/*  636 */               jjCheckNAddTwoStates(16, 14);
/*      */               break;
/*      */             case 16:
/*  639 */               if ((0x3FF000000000000L & l) == 0L)
/*      */                 break; 
/*  641 */               if (kind > 40)
/*  642 */                 kind = 40; 
/*  643 */               jjCheckNAddTwoStates(16, 14);
/*      */               break;
/*      */             case 17:
/*  646 */               if (this.curChar == '.')
/*  647 */                 jjCheckNAdd(18); 
/*      */               break;
/*      */             case 18:
/*  650 */               if ((0x3FF000000000000L & l) == 0L)
/*      */                 break; 
/*  652 */               if (kind > 41)
/*  653 */                 kind = 41; 
/*  654 */               jjCheckNAddStates(19, 21);
/*      */               break;
/*      */             case 20:
/*  657 */               if ((0x280000000000L & l) != 0L)
/*  658 */                 jjCheckNAdd(21); 
/*      */               break;
/*      */             case 21:
/*  661 */               if ((0x3FF000000000000L & l) == 0L)
/*      */                 break; 
/*  663 */               if (kind > 41)
/*  664 */                 kind = 41; 
/*  665 */               jjCheckNAddTwoStates(21, 22);
/*      */               break;
/*      */             case 23:
/*      */             case 25:
/*  669 */               if (this.curChar == '\'')
/*  670 */                 jjCheckNAddStates(7, 9); 
/*      */               break;
/*      */             case 24:
/*  673 */               if ((0xFFFFFF7FFFFFFFFFL & l) != 0L)
/*  674 */                 jjCheckNAddStates(7, 9); 
/*      */               break;
/*      */             case 26:
/*  677 */               if (this.curChar == '\'')
/*  678 */                 this.jjstateSet[this.jjnewStateCnt++] = 25; 
/*      */               break;
/*      */             case 27:
/*  681 */               if (this.curChar == '\'' && kind > 43)
/*  682 */                 kind = 43; 
/*      */               break;
/*      */             case 28:
/*  685 */               if ((0x3FF000000000000L & l) == 0L)
/*      */                 break; 
/*  687 */               if (kind > 41)
/*  688 */                 kind = 41; 
/*  689 */               jjCheckNAddStates(0, 6);
/*      */               break;
/*      */             case 29:
/*  692 */               if ((0x3FF000000000000L & l) != 0L)
/*  693 */                 jjCheckNAddTwoStates(29, 30); 
/*      */               break;
/*      */             case 30:
/*  696 */               if (this.curChar != '.')
/*      */                 break; 
/*  698 */               if (kind > 41)
/*  699 */                 kind = 41; 
/*  700 */               jjCheckNAddStates(22, 24);
/*      */               break;
/*      */             case 31:
/*  703 */               if ((0x3FF000000000000L & l) == 0L)
/*      */                 break; 
/*  705 */               if (kind > 41)
/*  706 */                 kind = 41; 
/*  707 */               jjCheckNAddStates(22, 24);
/*      */               break;
/*      */             case 33:
/*  710 */               if ((0x280000000000L & l) != 0L)
/*  711 */                 jjCheckNAdd(34); 
/*      */               break;
/*      */             case 34:
/*  714 */               if ((0x3FF000000000000L & l) == 0L)
/*      */                 break; 
/*  716 */               if (kind > 41)
/*  717 */                 kind = 41; 
/*  718 */               jjCheckNAddTwoStates(34, 22);
/*      */               break;
/*      */             case 35:
/*  721 */               if ((0x3FF000000000000L & l) != 0L)
/*  722 */                 jjCheckNAddTwoStates(35, 36); 
/*      */               break;
/*      */             case 37:
/*  725 */               if ((0x280000000000L & l) != 0L)
/*  726 */                 jjCheckNAdd(38); 
/*      */               break;
/*      */             case 38:
/*  729 */               if ((0x3FF000000000000L & l) == 0L)
/*      */                 break; 
/*  731 */               if (kind > 41)
/*  732 */                 kind = 41; 
/*  733 */               jjCheckNAddTwoStates(38, 22);
/*      */               break;
/*      */             case 39:
/*  736 */               if ((0x3FF000000000000L & l) == 0L)
/*      */                 break; 
/*  738 */               if (kind > 41)
/*  739 */                 kind = 41; 
/*  740 */               jjCheckNAddStates(25, 27);
/*      */               break;
/*      */             case 41:
/*  743 */               if ((0x280000000000L & l) != 0L)
/*  744 */                 jjCheckNAdd(42); 
/*      */               break;
/*      */             case 42:
/*  747 */               if ((0x3FF000000000000L & l) == 0L)
/*      */                 break; 
/*  749 */               if (kind > 41)
/*  750 */                 kind = 41; 
/*  751 */               jjCheckNAddTwoStates(42, 22);
/*      */               break;
/*      */             case 43:
/*  754 */               if (this.curChar == '0')
/*  755 */                 jjAddStates(13, 14); 
/*      */               break;
/*      */             case 45:
/*  758 */               if ((0x3FF000000000000L & l) == 0L)
/*      */                 break; 
/*  760 */               if (kind > 40)
/*  761 */                 kind = 40; 
/*  762 */               jjCheckNAddTwoStates(45, 14);
/*      */               break;
/*      */           } 
/*      */         
/*  766 */         } while (i != startsAt);
/*      */       }
/*  768 */       else if (this.curChar < '') {
/*      */         
/*  770 */         long l = 1L << (this.curChar & 0x3F);
/*      */         
/*      */         do {
/*  773 */           switch (this.jjstateSet[--i]) {
/*      */             
/*      */             case 0:
/*      */             case 1:
/*  777 */               if ((0x7FFFFFE87FFFFFEL & l) == 0L)
/*      */                 break; 
/*  779 */               if (kind > 35)
/*  780 */                 kind = 35; 
/*  781 */               jjCheckNAdd(1);
/*      */               break;
/*      */             case 3:
/*  784 */               if ((0xFFFFFFFFEFFFFFFFL & l) != 0L)
/*  785 */                 jjCheckNAddStates(10, 12); 
/*      */               break;
/*      */             case 4:
/*  788 */               if (this.curChar == '\\')
/*  789 */                 jjAddStates(28, 30); 
/*      */               break;
/*      */             case 5:
/*  792 */               if ((0x14404410000000L & l) != 0L)
/*  793 */                 jjCheckNAddStates(10, 12); 
/*      */               break;
/*      */             case 14:
/*  796 */               if ((0x100000001000L & l) != 0L && kind > 40)
/*  797 */                 kind = 40; 
/*      */               break;
/*      */             case 19:
/*  800 */               if ((0x2000000020L & l) != 0L)
/*  801 */                 jjAddStates(31, 32); 
/*      */               break;
/*      */             case 22:
/*  804 */               if ((0x5000000050L & l) != 0L && kind > 41)
/*  805 */                 kind = 41; 
/*      */               break;
/*      */             case 24:
/*  808 */               jjAddStates(7, 9);
/*      */               break;
/*      */             case 32:
/*  811 */               if ((0x2000000020L & l) != 0L)
/*  812 */                 jjAddStates(33, 34); 
/*      */               break;
/*      */             case 36:
/*  815 */               if ((0x2000000020L & l) != 0L)
/*  816 */                 jjAddStates(35, 36); 
/*      */               break;
/*      */             case 40:
/*  819 */               if ((0x2000000020L & l) != 0L)
/*  820 */                 jjAddStates(37, 38); 
/*      */               break;
/*      */             case 44:
/*  823 */               if (this.curChar == 'x')
/*  824 */                 jjCheckNAdd(45); 
/*      */               break;
/*      */             case 45:
/*  827 */               if ((0x7E0000007EL & l) == 0L)
/*      */                 break; 
/*  829 */               if (kind > 40)
/*  830 */                 kind = 40; 
/*  831 */               jjCheckNAddTwoStates(45, 14);
/*      */               break;
/*      */             case 46:
/*  834 */               if (this.curChar == 'X') {
/*  835 */                 jjCheckNAdd(45);
/*      */               }
/*      */               break;
/*      */           } 
/*  839 */         } while (i != startsAt);
/*      */       }
/*      */       else {
/*      */         
/*  843 */         int hiByte = this.curChar >> 8;
/*  844 */         int i1 = hiByte >> 6;
/*  845 */         long l1 = 1L << (hiByte & 0x3F);
/*  846 */         int i2 = (this.curChar & 0xFF) >> 6;
/*  847 */         long l2 = 1L << (this.curChar & 0x3F);
/*      */         
/*      */         do {
/*  850 */           switch (this.jjstateSet[--i]) {
/*      */             
/*      */             case 0:
/*      */             case 1:
/*  854 */               if (!jjCanMove_0(hiByte, i1, i2, l1, l2))
/*      */                 break; 
/*  856 */               if (kind > 35)
/*  857 */                 kind = 35; 
/*  858 */               jjCheckNAdd(1);
/*      */               break;
/*      */             case 3:
/*  861 */               if (jjCanMove_1(hiByte, i1, i2, l1, l2))
/*  862 */                 jjAddStates(10, 12); 
/*      */               break;
/*      */             case 24:
/*  865 */               if (jjCanMove_1(hiByte, i1, i2, l1, l2)) {
/*  866 */                 jjAddStates(7, 9);
/*      */               }
/*      */               break;
/*      */           } 
/*  870 */         } while (i != startsAt);
/*      */       } 
/*  872 */       if (kind != Integer.MAX_VALUE) {
/*      */         
/*  874 */         this.jjmatchedKind = kind;
/*  875 */         this.jjmatchedPos = curPos;
/*  876 */         kind = Integer.MAX_VALUE;
/*      */       } 
/*  878 */       curPos++;
/*  879 */       if ((i = this.jjnewStateCnt) == (startsAt = 47 - (this.jjnewStateCnt = startsAt)))
/*      */         break;  
/*  881 */       try { this.curChar = this.input_stream.readChar(); }
/*  882 */       catch (IOException e) { break; }
/*      */     
/*  884 */     }  if (this.jjmatchedPos > strPos) {
/*  885 */       return curPos;
/*      */     }
/*  887 */     int toRet = Math.max(curPos, seenUpto);
/*      */     
/*  889 */     if (curPos < toRet)
/*  890 */       for (i = toRet - Math.min(curPos, seenUpto); i-- > 0;) { 
/*  891 */         try { this.curChar = this.input_stream.readChar(); }
/*  892 */         catch (IOException e) { throw new Error("Internal Error : Please send a bug report."); }
/*      */          }
/*  894 */         if (this.jjmatchedPos < strPos) {
/*      */       
/*  896 */       this.jjmatchedKind = strKind;
/*  897 */       this.jjmatchedPos = strPos;
/*      */     }
/*  899 */     else if (this.jjmatchedPos == strPos && this.jjmatchedKind > strKind) {
/*  900 */       this.jjmatchedKind = strKind;
/*      */     } 
/*  902 */     return toRet;
/*      */   }
/*  904 */   static final int[] jjnextStates = new int[] { 29, 30, 35, 36, 39, 40, 22, 24, 26, 27, 3, 4, 6, 44, 46, 3, 4, 8, 6, 18, 19, 22, 31, 32, 22, 39, 40, 22, 5, 7, 9, 20, 21, 33, 34, 37, 38, 41, 42 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final boolean jjCanMove_0(int hiByte, int i1, int i2, long l1, long l2) {
/*  911 */     switch (hiByte) {
/*      */       
/*      */       case 0:
/*  914 */         return ((jjbitVec2[i2] & l2) != 0L);
/*      */       case 48:
/*  916 */         return ((jjbitVec3[i2] & l2) != 0L);
/*      */       case 49:
/*  918 */         return ((jjbitVec4[i2] & l2) != 0L);
/*      */       case 51:
/*  920 */         return ((jjbitVec5[i2] & l2) != 0L);
/*      */       case 61:
/*  922 */         return ((jjbitVec6[i2] & l2) != 0L);
/*      */     } 
/*  924 */     if ((jjbitVec0[i1] & l1) != 0L)
/*  925 */       return true; 
/*  926 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   private static final boolean jjCanMove_1(int hiByte, int i1, int i2, long l1, long l2) {
/*  931 */     switch (hiByte) {
/*      */       
/*      */       case 0:
/*  934 */         return ((jjbitVec8[i2] & l2) != 0L);
/*      */     } 
/*  936 */     if ((jjbitVec7[i1] & l1) != 0L)
/*  937 */       return true; 
/*  938 */     return false;
/*      */   }
/*      */   
/*  941 */   public static final String[] jjstrLiteralImages = new String[] { "", "<", ">", "<=", ">=", "<>", "=", "+", "-", "*", "/", "(", ")", ",", ".", "[", "]", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null };
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  946 */   public static final String[] lexStateNames = new String[] { "DEFAULT" };
/*      */ 
/*      */   
/*  949 */   static final long[] jjtoToken = new long[] { 12232062795775L };
/*      */ 
/*      */   
/*  952 */   static final long[] jjtoSkip = new long[] { 4063232L };
/*      */   
/*      */   private CharStream input_stream;
/*      */   
/*  956 */   private final int[] jjrounds = new int[47];
/*  957 */   private final int[] jjstateSet = new int[94]; protected char curChar; int curLexState;
/*      */   int defaultLexState;
/*      */   int jjnewStateCnt;
/*      */   int jjround;
/*      */   int jjmatchedPos;
/*      */   int jjmatchedKind;
/*      */   
/*      */   public MatchParserTokenManager(CharStream stream, int lexState) {
/*  965 */     this(stream);
/*  966 */     SwitchTo(lexState);
/*      */   }
/*      */   
/*      */   public void ReInit(CharStream stream) {
/*  970 */     this.jjmatchedPos = this.jjnewStateCnt = 0;
/*  971 */     this.curLexState = this.defaultLexState;
/*  972 */     this.input_stream = stream;
/*  973 */     ReInitRounds();
/*      */   }
/*      */ 
/*      */   
/*      */   private final void ReInitRounds() {
/*  978 */     this.jjround = -2147483647;
/*  979 */     for (int i = 47; i-- > 0;)
/*  980 */       this.jjrounds[i] = Integer.MIN_VALUE; 
/*      */   }
/*      */   
/*      */   public void ReInit(CharStream stream, int lexState) {
/*  984 */     ReInit(stream);
/*  985 */     SwitchTo(lexState);
/*      */   }
/*      */   
/*      */   public void SwitchTo(int lexState) {
/*  989 */     if (lexState >= 1 || lexState < 0) {
/*  990 */       throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", 2);
/*      */     }
/*  992 */     this.curLexState = lexState;
/*      */   }
/*      */ 
/*      */   
/*      */   private final Token jjFillToken() {
/*  997 */     Token t = Token.newToken(this.jjmatchedKind);
/*  998 */     t.kind = this.jjmatchedKind;
/*  999 */     String im = jjstrLiteralImages[this.jjmatchedKind];
/* 1000 */     t.image = (im == null) ? this.input_stream.GetImage() : im;
/* 1001 */     t.beginLine = this.input_stream.getBeginLine();
/* 1002 */     t.beginColumn = this.input_stream.getBeginColumn();
/* 1003 */     t.endLine = this.input_stream.getEndLine();
/* 1004 */     t.endColumn = this.input_stream.getEndColumn();
/* 1005 */     return t;
/*      */   }
/*      */   public MatchParserTokenManager(CharStream stream) {
/* 1008 */     this.curLexState = 0;
/* 1009 */     this.defaultLexState = 0;
/*      */     this.input_stream = stream;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final Token getNextToken() {
/* 1018 */     Token specialToken = null;
/*      */     
/* 1020 */     int curPos = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     while (true) {
/*      */       try {
/* 1027 */         this.curChar = this.input_stream.BeginToken();
/*      */       }
/* 1029 */       catch (IOException e) {
/*      */         
/* 1031 */         this.jjmatchedKind = 0;
/* 1032 */         Token matchedToken = jjFillToken();
/* 1033 */         return matchedToken;
/*      */       } 
/*      */       
/* 1036 */       this.jjmatchedKind = Integer.MAX_VALUE;
/* 1037 */       this.jjmatchedPos = 0;
/* 1038 */       curPos = jjMoveStringLiteralDfa0_0();
/* 1039 */       if (this.jjmatchedKind != Integer.MAX_VALUE) {
/*      */         
/* 1041 */         if (this.jjmatchedPos + 1 < curPos)
/* 1042 */           this.input_stream.backup(curPos - this.jjmatchedPos - 1); 
/* 1043 */         if ((jjtoToken[this.jjmatchedKind >> 6] & 1L << (this.jjmatchedKind & 0x3F)) != 0L) {
/*      */           
/* 1045 */           Token matchedToken = jjFillToken();
/* 1046 */           return matchedToken;
/*      */         } 
/*      */         
/*      */         continue;
/*      */       } 
/*      */       break;
/*      */     } 
/* 1053 */     int error_line = this.input_stream.getEndLine();
/* 1054 */     int error_column = this.input_stream.getEndColumn();
/* 1055 */     String error_after = null;
/* 1056 */     boolean EOFSeen = false; try {
/* 1057 */       this.input_stream.readChar(); this.input_stream.backup(1);
/* 1058 */     } catch (IOException e1) {
/* 1059 */       EOFSeen = true;
/* 1060 */       error_after = (curPos <= 1) ? "" : this.input_stream.GetImage();
/* 1061 */       if (this.curChar == '\n' || this.curChar == '\r') {
/* 1062 */         error_line++;
/* 1063 */         error_column = 0;
/*      */       } else {
/*      */         
/* 1066 */         error_column++;
/*      */       } 
/* 1068 */     }  if (!EOFSeen) {
/* 1069 */       this.input_stream.backup(1);
/* 1070 */       error_after = (curPos <= 1) ? "" : this.input_stream.GetImage();
/*      */     } 
/* 1072 */     throw new TokenMgrError(EOFSeen, this.curLexState, error_line, error_column, error_after, this.curChar, 0);
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\selector\MatchParserTokenManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */