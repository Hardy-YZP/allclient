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
/*     */ public class ParseException
/*     */   extends Exception
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2012 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   protected boolean specialConstructor;
/*     */   public Token currentToken;
/*     */   public int[][] expectedTokenSequences;
/*     */   public String[] tokenImage;
/*     */   protected String eol;
/*     */   
/*     */   public ParseException(Token currentTokenVal, int[][] expectedTokenSequencesVal, String[] tokenImageVal) {
/*  34 */     super("");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 141 */     this.eol = System.getProperty("line.separator", "\n"); this.specialConstructor = true; this.currentToken = currentTokenVal; this.expectedTokenSequences = expectedTokenSequencesVal; this.tokenImage = tokenImageVal; } public ParseException() { this.eol = System.getProperty("line.separator", "\n"); this.specialConstructor = false; } public ParseException(String message) { super(message); this.eol = System.getProperty("line.separator", "\n");
/*     */     this.specialConstructor = false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String add_escapes(String str) {
/* 149 */     StringBuffer retval = new StringBuffer();
/*     */     
/* 151 */     for (int i = 0; i < str.length(); i++) {
/* 152 */       char ch; switch (str.charAt(i)) {
/*     */         case '\000':
/*     */           break;
/*     */         
/*     */         case '\b':
/* 157 */           retval.append("\\b");
/*     */           break;
/*     */         case '\t':
/* 160 */           retval.append("\\t");
/*     */           break;
/*     */         case '\n':
/* 163 */           retval.append("\\n");
/*     */           break;
/*     */         case '\f':
/* 166 */           retval.append("\\f");
/*     */           break;
/*     */         case '\r':
/* 169 */           retval.append("\\r");
/*     */           break;
/*     */         case '"':
/* 172 */           retval.append("\\\"");
/*     */           break;
/*     */         case '\'':
/* 175 */           retval.append("\\'");
/*     */           break;
/*     */         case '\\':
/* 178 */           retval.append("\\\\");
/*     */           break;
/*     */         default:
/* 181 */           if ((ch = str.charAt(i)) < ' ' || ch > '~') {
/* 182 */             String s = "0000" + Integer.toString(ch, 16);
/* 183 */             retval.append("\\u" + s.substring(s.length() - 4, s.length())); break;
/*     */           } 
/* 185 */           retval.append(ch);
/*     */           break;
/*     */       } 
/*     */     
/*     */     } 
/* 190 */     return retval.toString();
/*     */   }
/*     */   
/*     */   public String getMessage() {
/*     */     if (!this.specialConstructor)
/*     */       return super.getMessage(); 
/*     */     String expected = "";
/*     */     int maxSize = 0;
/*     */     for (int i = 0; i < this.expectedTokenSequences.length; i++) {
/*     */       if (maxSize < (this.expectedTokenSequences[i]).length)
/*     */         maxSize = (this.expectedTokenSequences[i]).length; 
/*     */       for (int k = 0; k < (this.expectedTokenSequences[i]).length; k++)
/*     */         expected = expected + this.tokenImage[this.expectedTokenSequences[i][k]] + " "; 
/*     */       if (this.expectedTokenSequences[i][(this.expectedTokenSequences[i]).length - 1] != 0)
/*     */         expected = expected + "..."; 
/*     */       expected = expected + this.eol + "    ";
/*     */     } 
/*     */     String retval = "Encountered \"";
/*     */     Token tok = this.currentToken.next;
/*     */     for (int j = 0; j < maxSize; j++) {
/*     */       if (j != 0)
/*     */         retval = retval + " "; 
/*     */       if (tok.kind == 0) {
/*     */         retval = retval + this.tokenImage[0];
/*     */         break;
/*     */       } 
/*     */       retval = retval + add_escapes(tok.image);
/*     */       tok = tok.next;
/*     */     } 
/*     */     retval = retval + "\" at line " + this.currentToken.next.beginLine + ", column " + this.currentToken.next.beginColumn + "." + this.eol;
/*     */     if (this.expectedTokenSequences.length == 1) {
/*     */       retval = retval + "Was expecting:" + this.eol + "    ";
/*     */     } else {
/*     */       retval = retval + "Was expecting one of:" + this.eol + "    ";
/*     */     } 
/*     */     retval = retval + expected;
/*     */     return retval;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\selector\ParseException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */