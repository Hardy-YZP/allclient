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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TokenMgrError
/*     */   extends Error
/*     */ {
/*     */   static final String COPYRIGHT = "IBM Confidential OCO Source Materials 5724-H72 (C) Copyright IBM Corp. 2016 The source code for the program is not published or otherwise divested of its trade secrets, irrespective of what has been deposited with the U.S. Copyright Office. ";
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2012 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   static final int LEXICAL_ERROR = 0;
/*     */   static final int STATIC_LEXER_ERROR = 1;
/*     */   static final int INVALID_LEXICAL_STATE = 2;
/*     */   static final int LOOP_DETECTED = 3;
/*     */   int errorCode;
/*     */   
/*     */   protected static final String addEscapes(String str) {
/*  87 */     StringBuffer retval = new StringBuffer();
/*     */     
/*  89 */     for (int i = 0; i < str.length(); i++) {
/*  90 */       char ch; switch (str.charAt(i)) {
/*     */         case '\000':
/*     */           break;
/*     */         
/*     */         case '\b':
/*  95 */           retval.append("\\b");
/*     */           break;
/*     */         case '\t':
/*  98 */           retval.append("\\t");
/*     */           break;
/*     */         case '\n':
/* 101 */           retval.append("\\n");
/*     */           break;
/*     */         case '\f':
/* 104 */           retval.append("\\f");
/*     */           break;
/*     */         case '\r':
/* 107 */           retval.append("\\r");
/*     */           break;
/*     */         case '"':
/* 110 */           retval.append("\\\"");
/*     */           break;
/*     */         case '\'':
/* 113 */           retval.append("\\'");
/*     */           break;
/*     */         case '\\':
/* 116 */           retval.append("\\\\");
/*     */           break;
/*     */         default:
/* 119 */           if ((ch = str.charAt(i)) < ' ' || ch > '~') {
/* 120 */             String s = "0000" + Integer.toString(ch, 16);
/* 121 */             retval.append("\\u" + s.substring(s.length() - 4, s.length())); break;
/*     */           } 
/* 123 */           retval.append(ch);
/*     */           break;
/*     */       } 
/*     */     
/*     */     } 
/* 128 */     return retval.toString();
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
/*     */   private static final String LexicalError(boolean EOFSeen, int lexState, int errorLine, int errorColumn, String errorAfter, char curChar) {
/* 144 */     return "Lexical error at line " + errorLine + ", column " + errorColumn + ".  Encountered: " + (EOFSeen ? "<EOF> " : ("\"" + 
/*     */ 
/*     */       
/* 147 */       addEscapes(String.valueOf(curChar)) + "\"" + " (" + curChar + "), ")) + "after : \"" + 
/* 148 */       addEscapes(errorAfter) + "\"";
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
/*     */   public String getMessage() {
/* 161 */     return super.getMessage();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TokenMgrError() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public TokenMgrError(String message, int reason) {
/* 172 */     super(message);
/* 173 */     this.errorCode = reason;
/*     */   }
/*     */   
/*     */   public TokenMgrError(boolean EOFSeen, int lexState, int errorLine, int errorColumn, String errorAfter, char curChar, int reason) {
/* 177 */     this(LexicalError(EOFSeen, lexState, errorLine, errorColumn, errorAfter, curChar), reason);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\selector\TokenMgrError.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */