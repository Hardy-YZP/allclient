/*      */ package com.ibm.disthub2.impl.matching.selector;
/*      */ 
/*      */ import com.ibm.disthub2.impl.util.FastVector;
/*      */ import java.io.StringReader;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Vector;
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
/*      */ public class MatchParser
/*      */   implements MatchParserConstants
/*      */ {
/*      */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2005 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*      */   private boolean strict;
/*      */   public MatchParserTokenManager token_source;
/*      */   public Token token;
/*      */   public Token jj_nt;
/*      */   private int jj_ntk;
/*      */   private Token jj_scanpos;
/*      */   private Token jj_lastpos;
/*      */   private int jj_la;
/*      */   
/*      */   public static MatchParser prime(MatchParser parser, String selector, boolean strict) {
/*   51 */     CharStream inStream = new IBMUnicodeCharStream(new StringReader(selector), 1, 1);
/*   52 */     if (parser == null) {
/*   53 */       parser = new MatchParser(inStream);
/*      */     } else {
/*   55 */       parser.ReInit(inStream);
/*   56 */     }  parser.strict = strict;
/*   57 */     return parser;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Selector getSelector() {
/*      */     Selector ans;
/*      */     try {
/*   69 */       ans = QueryExpr();
/*   70 */       if (!ans.mayBeBoolean())
/*   71 */         ans.type = 2; 
/*   72 */     } catch (ParseException e) {
/*   73 */       ans = new Literal(BooleanValue.NULL);
/*   74 */       ans.type = 2;
/*      */     } 
/*   76 */     return ans;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final Selector QueryExpr() throws ParseException {
/*   82 */     Selector ans = BooleanExpr();
/*   83 */     jj_consume_token(0);
/*   84 */     return ans;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final Selector BooleanExpr() throws ParseException {
/*   91 */     Selector right = null;
/*   92 */     Selector left = BooleanTerm();
/*   93 */     switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*      */       case 27:
/*   95 */         jj_consume_token(27);
/*   96 */         right = BooleanExpr();
/*      */         break;
/*      */       default:
/*   99 */         this.jj_la1[0] = this.jj_gen;
/*      */         break;
/*      */     } 
/*  102 */     if (right == null) return left; 
/*  103 */     return new Operator(47, left, right);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final Selector BooleanTerm() throws ParseException {
/*  110 */     Selector right = null;
/*  111 */     Selector left = BooleanFactor();
/*  112 */     switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*      */       case 28:
/*  114 */         jj_consume_token(28);
/*  115 */         right = BooleanTerm();
/*      */         break;
/*      */       default:
/*  118 */         this.jj_la1[1] = this.jj_gen;
/*      */         break;
/*      */     } 
/*  121 */     if (right == null) return left; 
/*  122 */     return new Operator(46, left, right);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final Selector BooleanFactor() throws ParseException {
/*  128 */     boolean neg = false;
/*  129 */     switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*      */       case 29:
/*  131 */         jj_consume_token(29);
/*  132 */         neg = true;
/*      */         break;
/*      */       default:
/*  135 */         this.jj_la1[2] = this.jj_gen;
/*      */         break;
/*      */     } 
/*  138 */     Selector child = Predicate();
/*  139 */     if (neg) return new Operator(1, child); 
/*  140 */     return child;
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
/*      */   public final Selector Predicate() throws ParseException {
/*      */     Selector ans;
/*  156 */     if (jj_2_1(2147483647))
/*  157 */     { ans = ComparisonPredicate(); }
/*  158 */     else if (jj_2_2(2147483647))
/*  159 */     { ans = NullPredicate(); }
/*  160 */     else if (jj_2_3(2147483647))
/*  161 */     { ans = BetweenPredicate(); }
/*  162 */     else if (jj_2_4(2147483647))
/*  163 */     { ans = LikePredicate(); }
/*  164 */     else if (jj_2_5(2147483647))
/*  165 */     { ans = EmptyPredicate(); }
/*  166 */     else if (jj_2_6(2147483647))
/*  167 */     { ans = CasePredicate(); }
/*  168 */     else if (jj_2_7(2147483647))
/*  169 */     { ans = JMSSetPredicate(); }
/*  170 */     else if (jj_2_8(2147483647))
/*  171 */     { ans = GeneralSetPredicate(); }
/*      */     else
/*  173 */     { switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk)
/*      */       { case 7:
/*      */         case 8:
/*      */         case 11:
/*      */         case 31:
/*      */         case 32:
/*      */         case 35:
/*      */         case 36:
/*      */         case 40:
/*      */         case 41:
/*      */         case 43:
/*  184 */           ans = Expression();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  192 */           return ans; }  this.jj_la1[3] = this.jj_gen; jj_consume_token(-1); throw new ParseException(); }  return ans;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final Selector ComparisonPredicate() throws ParseException {
/*  200 */     Selector left = Expression();
/*  201 */     int op = ComparisonOperator();
/*  202 */     Selector right = Expression();
/*  203 */     return new Operator(op, left, right);
/*      */   }
/*      */ 
/*      */   
/*      */   public final int ComparisonOperator() throws ParseException {
/*  208 */     switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*      */       case 1:
/*  210 */         jj_consume_token(1);
/*  211 */         return 42;
/*      */       
/*      */       case 2:
/*  214 */         jj_consume_token(2);
/*  215 */         return 41;
/*      */       
/*      */       case 3:
/*  218 */         jj_consume_token(3);
/*  219 */         return 44;
/*      */       
/*      */       case 4:
/*  222 */         jj_consume_token(4);
/*  223 */         return 43;
/*      */       
/*      */       case 5:
/*  226 */         jj_consume_token(5);
/*  227 */         return 40;
/*      */       
/*      */       case 6:
/*  230 */         jj_consume_token(6);
/*  231 */         return 45;
/*      */     } 
/*      */     
/*  234 */     this.jj_la1[4] = this.jj_gen;
/*  235 */     jj_consume_token(-1);
/*  236 */     throw new ParseException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final Selector CasePredicate() throws ParseException {
/*  244 */     Selector left = Identifier();
/*  245 */     jj_consume_token(34);
/*  246 */     Selector right = Identifier();
/*  247 */     ((Identifier)right).caseOf = (Identifier)left;
/*  248 */     return new Operator(45, left, right);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final Selector Expression() throws ParseException {
/*  255 */     Selector right = null; int op = -1;
/*  256 */     Selector left = Term();
/*  257 */     switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*      */       case 7:
/*      */       case 8:
/*  260 */         op = PlusMinus();
/*  261 */         right = Expression();
/*      */         break;
/*      */       default:
/*  264 */         this.jj_la1[5] = this.jj_gen;
/*      */         break;
/*      */     } 
/*  267 */     if (right == null) return left; 
/*  268 */     return new Operator(op, left, right);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final int PlusMinus() throws ParseException {
/*  274 */     switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*      */       case 7:
/*  276 */         jj_consume_token(7);
/*  277 */         return 48;
/*      */       
/*      */       case 8:
/*  280 */         jj_consume_token(8);
/*  281 */         return 49;
/*      */     } 
/*      */     
/*  284 */     this.jj_la1[6] = this.jj_gen;
/*  285 */     jj_consume_token(-1);
/*  286 */     throw new ParseException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final Selector Term() throws ParseException {
/*  294 */     Selector right = null; int op = -1;
/*  295 */     Selector left = Primary(false);
/*  296 */     switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*      */       case 9:
/*      */       case 10:
/*  299 */         op = TimesDiv();
/*  300 */         right = Term();
/*      */         break;
/*      */       default:
/*  303 */         this.jj_la1[7] = this.jj_gen;
/*      */         break;
/*      */     } 
/*  306 */     if (right == null) return left; 
/*  307 */     return new Operator(op, left, right);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final int TimesDiv() throws ParseException {
/*  313 */     switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*      */       case 9:
/*  315 */         jj_consume_token(9);
/*  316 */         return 50;
/*      */       
/*      */       case 10:
/*  319 */         jj_consume_token(10);
/*  320 */         return 51;
/*      */     } 
/*      */     
/*  323 */     this.jj_la1[8] = this.jj_gen;
/*  324 */     jj_consume_token(-1);
/*  325 */     throw new ParseException();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final Selector Primary(boolean negated) throws ParseException {
/*      */     int i;
/*      */     Selector ans;
/*  333 */     int op = 48;
/*  334 */     switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*      */       case 11:
/*      */       case 31:
/*      */       case 32:
/*      */       case 35:
/*      */       case 36:
/*      */       case 40:
/*      */       case 41:
/*      */       case 43:
/*  343 */         ans = PrimaryNotPlusMinus(negated);
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
/*  356 */         return ans;case 7: case 8: op = PlusMinus(); i = negated ^ ((op == 49) ? 1 : 0); ans = Primary(i); return ans;
/*      */     } 
/*      */     this.jj_la1[9] = this.jj_gen;
/*      */     jj_consume_token(-1);
/*      */     throw new ParseException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final Selector PrimaryNotPlusMinus(boolean negated) throws ParseException {
/*      */     Selector ans;
/*      */     Token tok;
/*  369 */     switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*      */       case 31:
/*  371 */         jj_consume_token(31);
/*  372 */         ans = new Literal(BooleanValue.TRUE);
/*      */         break;
/*      */       case 32:
/*  375 */         jj_consume_token(32);
/*  376 */         ans = new Literal(BooleanValue.FALSE);
/*      */         break;
/*      */       case 11:
/*  379 */         jj_consume_token(11);
/*  380 */         ans = BooleanExpr();
/*  381 */         jj_consume_token(12);
/*      */         break;
/*      */       case 40:
/*  384 */         tok = jj_consume_token(40);
/*  385 */         ans = ParseUtil.parseIntegerLiteral((negated ? "-" : "") + tok.image);
/*  386 */         negated = false;
/*      */         break;
/*      */       case 41:
/*  389 */         tok = jj_consume_token(41);
/*  390 */         ans = ParseUtil.parseFloatingLiteral((negated ? "-" : "") + tok.image);
/*  391 */         negated = false;
/*      */         break;
/*      */       case 43:
/*  394 */         tok = jj_consume_token(43);
/*  395 */         ans = ParseUtil.parseStringLiteral(tok.image);
/*      */         break;
/*      */       case 35:
/*      */       case 36:
/*  399 */         ans = FieldRef();
/*      */         break;
/*      */       
/*      */       default:
/*  403 */         this.jj_la1[10] = this.jj_gen;
/*  404 */         jj_consume_token(-1);
/*  405 */         throw new ParseException();
/*      */     } 
/*  407 */     if (negated) return new Operator(2, ans); 
/*  408 */     return ans;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final Selector NullPredicate() throws ParseException {
/*  414 */     boolean not = false;
/*  415 */     Selector ans = FieldRef();
/*  416 */     jj_consume_token(22);
/*  417 */     switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*      */       case 29:
/*  419 */         jj_consume_token(29);
/*  420 */         not = true;
/*      */         break;
/*      */       default:
/*  423 */         this.jj_la1[11] = this.jj_gen;
/*      */         break;
/*      */     } 
/*  426 */     jj_consume_token(30);
/*  427 */     ans = new Operator(3, ans);
/*  428 */     if (not) return new Operator(1, ans); 
/*  429 */     return ans;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final Selector JMSSetPredicate() throws ParseException {
/*  435 */     boolean neg = false;
/*  436 */     Selector id = FieldRef();
/*  437 */     switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*      */       case 29:
/*  439 */         jj_consume_token(29);
/*  440 */         neg = true;
/*      */         break;
/*      */       default:
/*  443 */         this.jj_la1[12] = this.jj_gen;
/*      */         break;
/*      */     } 
/*  446 */     jj_consume_token(23);
/*  447 */     FastVector set = SetString();
/*  448 */     Selector ans = ParseUtil.convertSet(id, set);
/*  449 */     if (neg) return new Operator(1, ans); 
/*  450 */     return ans;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final Selector GeneralSetPredicate() throws ParseException {
/*  456 */     boolean neg = false;
/*  457 */     if (this.strict) throw generateParseException(); 
/*  458 */     Selector expr = Expression();
/*  459 */     switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*      */       case 29:
/*  461 */         jj_consume_token(29);
/*  462 */         neg = true;
/*      */         break;
/*      */       default:
/*  465 */         this.jj_la1[13] = this.jj_gen;
/*      */         break;
/*      */     } 
/*  468 */     jj_consume_token(23);
/*  469 */     FastVector set = SetExpr();
/*  470 */     Selector ans = ParseUtil.convertSet(expr, set);
/*  471 */     if (neg) ans = new Operator(1, ans); 
/*  472 */     return ans;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final FastVector SetExpr() throws ParseException {
/*  478 */     FastVector ans = new FastVector();
/*  479 */     jj_consume_token(11);
/*  480 */     Selector elem = Expression();
/*  481 */     ans.addElement(elem);
/*      */     
/*      */     while (true) {
/*  484 */       switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*      */         case 13:
/*      */           break;
/*      */         
/*      */         default:
/*  489 */           this.jj_la1[14] = this.jj_gen;
/*      */           break;
/*      */       } 
/*  492 */       jj_consume_token(13);
/*  493 */       elem = Expression();
/*  494 */       ans.addElement(elem);
/*      */     } 
/*  496 */     jj_consume_token(12);
/*  497 */     return ans;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final FastVector SetString() throws ParseException {
/*  503 */     FastVector ans = new FastVector();
/*  504 */     jj_consume_token(11);
/*  505 */     Token tok = jj_consume_token(43);
/*  506 */     ans.addElement(ParseUtil.parseStringLiteral(tok.image));
/*      */     
/*      */     while (true) {
/*  509 */       switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*      */         case 13:
/*      */           break;
/*      */         
/*      */         default:
/*  514 */           this.jj_la1[15] = this.jj_gen;
/*      */           break;
/*      */       } 
/*  517 */       jj_consume_token(13);
/*  518 */       tok = jj_consume_token(43);
/*  519 */       ans.addElement(ParseUtil.parseStringLiteral(tok.image));
/*      */     } 
/*  521 */     jj_consume_token(12);
/*  522 */     return ans;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final Selector BetweenPredicate() throws ParseException {
/*  528 */     boolean neg = false;
/*  529 */     Selector expr1 = Expression();
/*  530 */     switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*      */       case 29:
/*  532 */         jj_consume_token(29);
/*  533 */         neg = true;
/*      */         break;
/*      */       default:
/*  536 */         this.jj_la1[16] = this.jj_gen;
/*      */         break;
/*      */     } 
/*  539 */     jj_consume_token(24);
/*  540 */     Selector expr2 = Expression();
/*  541 */     jj_consume_token(28);
/*  542 */     Selector expr3 = Expression();
/*  543 */     Selector ans = ParseUtil.convertRange(expr1, expr2, expr3);
/*  544 */     if (neg) return new Operator(1, ans); 
/*  545 */     return ans;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final Selector LikePredicate() throws ParseException {
/*  552 */     Token esc = null; boolean neg = false;
/*  553 */     Selector id = FieldRef();
/*  554 */     switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*      */       case 29:
/*  556 */         jj_consume_token(29);
/*  557 */         neg = true;
/*      */         break;
/*      */       default:
/*  560 */         this.jj_la1[17] = this.jj_gen;
/*      */         break;
/*      */     } 
/*  563 */     jj_consume_token(25);
/*  564 */     Token pat = jj_consume_token(43);
/*  565 */     switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*      */       case 26:
/*  567 */         jj_consume_token(26);
/*  568 */         esc = jj_consume_token(43);
/*      */         break;
/*      */       default:
/*  571 */         this.jj_la1[18] = this.jj_gen;
/*      */         break;
/*      */     } 
/*  574 */     Selector ans = new LikeOperator(id, pat.image, (esc == null) ? null : esc.image);
/*  575 */     if (neg) return new Operator(1, ans); 
/*  576 */     return ans;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final Selector FieldRef() throws ParseException {
/*  583 */     Selector ans = Identifier();
/*      */ 
/*      */     
/*  586 */     while (jj_2_9(2147483647)) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  591 */       Selector[] sel = Selections();
/*  592 */       jj_consume_token(14);
/*  593 */       Selector id = Identifier();
/*  594 */       Selector select = sel[0];
/*  595 */       Selector index = sel[1];
/*  596 */       if (select != null)
/*  597 */         ans = new Operator(53, ans, select); 
/*  598 */       if (index == null)
/*  599 */         index = new Literal(new NumericValue(0)); 
/*  600 */       ans = new Operator(54, new Operator(52, ans, index), id);
/*      */     } 
/*  602 */     return ans;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final Selector[] Selections() throws ParseException {
/*  609 */     Selector select = null, index = null;
/*  610 */     if (this.strict) throw generateParseException(); 
/*  611 */     if (jj_2_10(2147483647))
/*  612 */     { index = Index(); }
/*      */     else
/*  614 */     { switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk)
/*      */       { case 15:
/*  616 */           select = SelectExpr();
/*  617 */           switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk)
/*      */           { case 15:
/*  619 */               index = Index();
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
/*  632 */               return new Selector[] { select, index }; }  this.jj_la1[19] = this.jj_gen; return new Selector[] { select, index }; }  this.jj_la1[20] = this.jj_gen; jj_consume_token(-1); throw new ParseException(); }  return new Selector[] { select, index };
/*      */   }
/*      */ 
/*      */   
/*      */   public final Selector Identifier() throws ParseException {
/*      */     Token tok;
/*      */     String id;
/*      */     Selector ans;
/*  640 */     switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*      */       case 35:
/*  642 */         tok = jj_consume_token(35);
/*  643 */         id = tok.image;
/*      */         
/*  645 */         if (this.strict && id.indexOf('.') != -1) {
/*  646 */           throw generateParseException();
/*      */         }
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
/*  658 */         ans = new Identifier(id);
/*  659 */         return ans;case 36: tok = jj_consume_token(36); if (this.strict) throw generateParseException();  id = tok.image.substring(1, tok.image.length() - 1); ans = new Identifier(id); return ans;
/*      */     } 
/*      */     this.jj_la1[21] = this.jj_gen;
/*      */     jj_consume_token(-1);
/*      */     throw new ParseException();
/*      */   }
/*      */   public final Selector SelectExpr() throws ParseException {
/*  666 */     jj_consume_token(15);
/*  667 */     Selector ans = BooleanExpr();
/*  668 */     jj_consume_token(16);
/*  669 */     return ans;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final Selector Index() throws ParseException {
/*  676 */     jj_consume_token(15);
/*  677 */     Token tok = jj_consume_token(40);
/*  678 */     jj_consume_token(16);
/*  679 */     return ParseUtil.parseIntegerLiteral(tok.image);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final Selector EmptyPredicate() throws ParseException {
/*  685 */     Selector select = null; boolean not = false;
/*  686 */     if (this.strict) throw generateParseException(); 
/*  687 */     Selector ans = FieldRef();
/*  688 */     switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*      */       case 15:
/*  690 */         select = SelectExpr();
/*      */         break;
/*      */       default:
/*  693 */         this.jj_la1[22] = this.jj_gen;
/*      */         break;
/*      */     } 
/*  696 */     jj_consume_token(22);
/*  697 */     switch ((this.jj_ntk == -1) ? jj_ntk() : this.jj_ntk) {
/*      */       case 29:
/*  699 */         jj_consume_token(29);
/*  700 */         not = true;
/*      */         break;
/*      */       default:
/*  703 */         this.jj_la1[23] = this.jj_gen;
/*      */         break;
/*      */     } 
/*  706 */     jj_consume_token(33);
/*  707 */     if (select != null)
/*  708 */       ans = new Operator(53, ans, select); 
/*  709 */     ans = new Operator(5, ans);
/*  710 */     if (not) return new Operator(1, ans); 
/*  711 */     return ans;
/*      */   }
/*      */ 
/*      */   
/*      */   private final boolean jj_2_1(int xla) {
/*  716 */     this.jj_la = xla; this.jj_lastpos = this.jj_scanpos = this.token;
/*  717 */     boolean retval = !jj_3_1();
/*  718 */     jj_save(0, xla);
/*  719 */     return retval;
/*      */   }
/*      */   
/*      */   private final boolean jj_2_2(int xla) {
/*  723 */     this.jj_la = xla; this.jj_lastpos = this.jj_scanpos = this.token;
/*  724 */     boolean retval = !jj_3_2();
/*  725 */     jj_save(1, xla);
/*  726 */     return retval;
/*      */   }
/*      */   
/*      */   private final boolean jj_2_3(int xla) {
/*  730 */     this.jj_la = xla; this.jj_lastpos = this.jj_scanpos = this.token;
/*  731 */     boolean retval = !jj_3_3();
/*  732 */     jj_save(2, xla);
/*  733 */     return retval;
/*      */   }
/*      */   
/*      */   private final boolean jj_2_4(int xla) {
/*  737 */     this.jj_la = xla; this.jj_lastpos = this.jj_scanpos = this.token;
/*  738 */     boolean retval = !jj_3_4();
/*  739 */     jj_save(3, xla);
/*  740 */     return retval;
/*      */   }
/*      */   
/*      */   private final boolean jj_2_5(int xla) {
/*  744 */     this.jj_la = xla; this.jj_lastpos = this.jj_scanpos = this.token;
/*  745 */     boolean retval = !jj_3_5();
/*  746 */     jj_save(4, xla);
/*  747 */     return retval;
/*      */   }
/*      */   
/*      */   private final boolean jj_2_6(int xla) {
/*  751 */     this.jj_la = xla; this.jj_lastpos = this.jj_scanpos = this.token;
/*  752 */     boolean retval = !jj_3_6();
/*  753 */     jj_save(5, xla);
/*  754 */     return retval;
/*      */   }
/*      */   
/*      */   private final boolean jj_2_7(int xla) {
/*  758 */     this.jj_la = xla; this.jj_lastpos = this.jj_scanpos = this.token;
/*  759 */     boolean retval = !jj_3_7();
/*  760 */     jj_save(6, xla);
/*  761 */     return retval;
/*      */   }
/*      */   
/*      */   private final boolean jj_2_8(int xla) {
/*  765 */     this.jj_la = xla; this.jj_lastpos = this.jj_scanpos = this.token;
/*  766 */     boolean retval = !jj_3_8();
/*  767 */     jj_save(7, xla);
/*  768 */     return retval;
/*      */   }
/*      */   
/*      */   private final boolean jj_2_9(int xla) {
/*  772 */     this.jj_la = xla; this.jj_lastpos = this.jj_scanpos = this.token;
/*  773 */     boolean retval = !jj_3_9();
/*  774 */     jj_save(8, xla);
/*  775 */     return retval;
/*      */   }
/*      */   
/*      */   private final boolean jj_2_10(int xla) {
/*  779 */     this.jj_la = xla; this.jj_lastpos = this.jj_scanpos = this.token;
/*  780 */     boolean retval = !jj_3_10();
/*  781 */     jj_save(9, xla);
/*  782 */     return retval;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_36() {
/*  786 */     if (jj_scan_token(5)) return true; 
/*  787 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/*  788 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3_10() {
/*  792 */     if (jj_3R_13()) return true; 
/*  793 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/*  794 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_40() {
/*  798 */     if (jj_scan_token(35)) return true; 
/*  799 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/*  800 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_35() {
/*  804 */     if (jj_scan_token(4)) return true; 
/*  805 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/*  806 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_34() {
/*  810 */     if (jj_scan_token(3)) return true; 
/*  811 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/*  812 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   private final boolean jj_3R_23() {
/*  817 */     Token xsp = this.jj_scanpos;
/*  818 */     if (jj_3R_40())
/*  819 */     { this.jj_scanpos = xsp;
/*  820 */       if (jj_3R_41()) return true; 
/*  821 */       if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false;  }
/*  822 */     else if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) { return false; }
/*  823 */      return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_33() {
/*  827 */     if (jj_scan_token(2)) return true; 
/*  828 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/*  829 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   private final boolean jj_3R_15() {
/*  834 */     Token xsp = this.jj_scanpos;
/*  835 */     if (jj_3R_32())
/*  836 */     { this.jj_scanpos = xsp;
/*  837 */       if (jj_3R_33())
/*  838 */       { this.jj_scanpos = xsp;
/*  839 */         if (jj_3R_34())
/*  840 */         { this.jj_scanpos = xsp;
/*  841 */           if (jj_3R_35())
/*  842 */           { this.jj_scanpos = xsp;
/*  843 */             if (jj_3R_36())
/*  844 */             { this.jj_scanpos = xsp;
/*  845 */               if (jj_3R_37()) return true; 
/*  846 */               if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false;  }
/*  847 */             else if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) { return false; }  }
/*  848 */           else if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) { return false; }  }
/*  849 */         else if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) { return false; }  }
/*  850 */       else if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) { return false; }  }
/*  851 */     else if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) { return false; }
/*  852 */      return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_32() {
/*  856 */     if (jj_scan_token(1)) return true; 
/*  857 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/*  858 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_29() {
/*  862 */     if (jj_3R_39()) return true; 
/*  863 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false;
/*      */     
/*  865 */     Token xsp = this.jj_scanpos;
/*  866 */     if (jj_3R_44()) { this.jj_scanpos = xsp; }
/*  867 */     else if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) { return false; }
/*  868 */      return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_20() {
/*  872 */     if (jj_scan_token(26)) return true; 
/*  873 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/*  874 */     if (jj_scan_token(43)) return true; 
/*  875 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/*  876 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3_8() {
/*  880 */     if (jj_3R_11()) return true; 
/*  881 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/*  882 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3_7() {
/*  886 */     if (jj_3R_10()) return true; 
/*  887 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/*  888 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_28() {
/*  892 */     if (jj_3R_13()) return true; 
/*  893 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/*  894 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3_6() {
/*  898 */     if (jj_3R_9()) return true; 
/*  899 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/*  900 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3_5() {
/*  904 */     if (jj_3R_8()) return true; 
/*  905 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/*  906 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_4() {
/*  910 */     if (jj_3R_14()) return true; 
/*  911 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/*  912 */     if (jj_3R_15()) return true; 
/*  913 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/*  914 */     if (jj_3R_14()) return true; 
/*  915 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/*  916 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3_4() {
/*  920 */     if (jj_3R_7()) return true; 
/*  921 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/*  922 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   private final boolean jj_3R_12() {
/*  927 */     Token xsp = this.jj_scanpos;
/*  928 */     if (jj_3R_28())
/*  929 */     { this.jj_scanpos = xsp;
/*  930 */       if (jj_3R_29()) return true; 
/*  931 */       if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false;  }
/*  932 */     else if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) { return false; }
/*  933 */      return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3_3() {
/*  937 */     if (jj_3R_6()) return true; 
/*  938 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/*  939 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3_2() {
/*  943 */     if (jj_3R_5()) return true; 
/*  944 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/*  945 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3_9() {
/*  949 */     if (jj_3R_12()) return true; 
/*  950 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/*  951 */     if (jj_scan_token(14)) return true; 
/*  952 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/*  953 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_78() {
/*  957 */     if (jj_3R_14()) return true; 
/*  958 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/*  959 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3_1() {
/*  963 */     if (jj_3R_4()) return true; 
/*  964 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/*  965 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_77() {
/*  969 */     if (jj_3R_11()) return true; 
/*  970 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/*  971 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_76() {
/*  975 */     if (jj_3R_10()) return true; 
/*  976 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/*  977 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_75() {
/*  981 */     if (jj_3R_9()) return true; 
/*  982 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/*  983 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_74() {
/*  987 */     if (jj_3R_8()) return true; 
/*  988 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/*  989 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_73() {
/*  993 */     if (jj_3R_7()) return true; 
/*  994 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/*  995 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_19() {
/*  999 */     if (jj_scan_token(29)) return true; 
/* 1000 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1001 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_72() {
/* 1005 */     if (jj_3R_6()) return true; 
/* 1006 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1007 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_71() {
/* 1011 */     if (jj_3R_5()) return true; 
/* 1012 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1013 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_38() {
/* 1017 */     if (jj_3R_12()) return true; 
/* 1018 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1019 */     if (jj_scan_token(14)) return true; 
/* 1020 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1021 */     if (jj_3R_23()) return true; 
/* 1022 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1023 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_70() {
/* 1027 */     if (jj_3R_4()) return true; 
/* 1028 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1029 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   private final boolean jj_3R_69() {
/* 1034 */     Token xsp = this.jj_scanpos;
/* 1035 */     if (jj_3R_70())
/* 1036 */     { this.jj_scanpos = xsp;
/* 1037 */       if (jj_3R_71())
/* 1038 */       { this.jj_scanpos = xsp;
/* 1039 */         if (jj_3R_72())
/* 1040 */         { this.jj_scanpos = xsp;
/* 1041 */           if (jj_3R_73())
/* 1042 */           { this.jj_scanpos = xsp;
/* 1043 */             if (jj_3R_74())
/* 1044 */             { this.jj_scanpos = xsp;
/* 1045 */               if (jj_3R_75())
/* 1046 */               { this.jj_scanpos = xsp;
/* 1047 */                 if (jj_3R_76())
/* 1048 */                 { this.jj_scanpos = xsp;
/* 1049 */                   if (jj_3R_77())
/* 1050 */                   { this.jj_scanpos = xsp;
/* 1051 */                     if (jj_3R_78()) return true; 
/* 1052 */                     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false;  }
/* 1053 */                   else if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) { return false; }  }
/* 1054 */                 else if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) { return false; }  }
/* 1055 */               else if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) { return false; }  }
/* 1056 */             else if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) { return false; }  }
/* 1057 */           else if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) { return false; }  }
/* 1058 */         else if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) { return false; }  }
/* 1059 */       else if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) { return false; }  }
/* 1060 */     else if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) { return false; }
/* 1061 */      return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_60() {
/* 1065 */     if (jj_scan_token(28)) return true; 
/* 1066 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1067 */     if (jj_3R_54()) return true; 
/* 1068 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1069 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_18() {
/* 1073 */     if (jj_scan_token(29)) return true; 
/* 1074 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1075 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_16() {
/* 1079 */     if (jj_3R_23()) return true; 
/* 1080 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false;
/*      */     
/*      */     while (true) {
/* 1083 */       Token xsp = this.jj_scanpos;
/* 1084 */       if (jj_3R_38()) { this.jj_scanpos = xsp; break; }
/* 1085 */        if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/*      */     } 
/* 1087 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_55() {
/* 1091 */     if (jj_scan_token(27)) return true; 
/* 1092 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1093 */     if (jj_3R_48()) return true; 
/* 1094 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1095 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_7() {
/* 1099 */     if (jj_3R_16()) return true; 
/* 1100 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false;
/*      */     
/* 1102 */     Token xsp = this.jj_scanpos;
/* 1103 */     if (jj_3R_19()) { this.jj_scanpos = xsp; }
/* 1104 */     else if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) { return false; }
/* 1105 */      if (jj_scan_token(25)) return true; 
/* 1106 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1107 */     if (jj_scan_token(43)) return true; 
/* 1108 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1109 */     xsp = this.jj_scanpos;
/* 1110 */     if (jj_3R_20()) { this.jj_scanpos = xsp; }
/* 1111 */     else if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) { return false; }
/* 1112 */      return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_68() {
/* 1116 */     if (jj_scan_token(29)) return true; 
/* 1117 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1118 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   private final boolean jj_3R_59() {
/* 1123 */     Token xsp = this.jj_scanpos;
/* 1124 */     if (jj_3R_68()) { this.jj_scanpos = xsp; }
/* 1125 */     else if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) { return false; }
/* 1126 */      if (jj_3R_69()) return true; 
/* 1127 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1128 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_6() {
/* 1132 */     if (jj_3R_14()) return true; 
/* 1133 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false;
/*      */     
/* 1135 */     Token xsp = this.jj_scanpos;
/* 1136 */     if (jj_3R_18()) { this.jj_scanpos = xsp; }
/* 1137 */     else if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) { return false; }
/* 1138 */      if (jj_scan_token(24)) return true; 
/* 1139 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1140 */     if (jj_3R_14()) return true; 
/* 1141 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1142 */     if (jj_scan_token(28)) return true; 
/* 1143 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1144 */     if (jj_3R_14()) return true; 
/* 1145 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1146 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_54() {
/* 1150 */     if (jj_3R_59()) return true; 
/* 1151 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false;
/*      */     
/* 1153 */     Token xsp = this.jj_scanpos;
/* 1154 */     if (jj_3R_60()) { this.jj_scanpos = xsp; }
/* 1155 */     else if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) { return false; }
/* 1156 */      return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_42() {
/* 1160 */     if (jj_scan_token(13)) return true; 
/* 1161 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1162 */     if (jj_scan_token(43)) return true; 
/* 1163 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1164 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_26() {
/* 1168 */     if (jj_scan_token(29)) return true; 
/* 1169 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1170 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_48() {
/* 1174 */     if (jj_3R_54()) return true; 
/* 1175 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false;
/*      */     
/* 1177 */     Token xsp = this.jj_scanpos;
/* 1178 */     if (jj_3R_55()) { this.jj_scanpos = xsp; }
/* 1179 */     else if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) { return false; }
/* 1180 */      return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_25() {
/* 1184 */     if (jj_scan_token(11)) return true; 
/* 1185 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1186 */     if (jj_scan_token(43)) return true; 
/* 1187 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false;
/*      */     
/*      */     while (true) {
/* 1190 */       Token xsp = this.jj_scanpos;
/* 1191 */       if (jj_3R_42()) { this.jj_scanpos = xsp; break; }
/* 1192 */        if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/*      */     } 
/* 1194 */     if (jj_scan_token(12)) return true; 
/* 1195 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1196 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_43() {
/* 1200 */     if (jj_scan_token(13)) return true; 
/* 1201 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1202 */     if (jj_3R_14()) return true; 
/* 1203 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1204 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_27() {
/* 1208 */     if (jj_scan_token(11)) return true; 
/* 1209 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1210 */     if (jj_3R_14()) return true; 
/* 1211 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false;
/*      */     
/*      */     while (true) {
/* 1214 */       Token xsp = this.jj_scanpos;
/* 1215 */       if (jj_3R_43()) { this.jj_scanpos = xsp; break; }
/* 1216 */        if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/*      */     } 
/* 1218 */     if (jj_scan_token(12)) return true; 
/* 1219 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1220 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_24() {
/* 1224 */     if (jj_scan_token(29)) return true; 
/* 1225 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1226 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_17() {
/* 1230 */     if (jj_scan_token(29)) return true; 
/* 1231 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1232 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_11() {
/* 1236 */     if (jj_3R_14()) return true; 
/* 1237 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false;
/*      */     
/* 1239 */     Token xsp = this.jj_scanpos;
/* 1240 */     if (jj_3R_26()) { this.jj_scanpos = xsp; }
/* 1241 */     else if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) { return false; }
/* 1242 */      if (jj_scan_token(23)) return true; 
/* 1243 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1244 */     if (jj_3R_27()) return true; 
/* 1245 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1246 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_10() {
/* 1250 */     if (jj_3R_16()) return true; 
/* 1251 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false;
/*      */     
/* 1253 */     Token xsp = this.jj_scanpos;
/* 1254 */     if (jj_3R_24()) { this.jj_scanpos = xsp; }
/* 1255 */     else if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) { return false; }
/* 1256 */      if (jj_scan_token(23)) return true; 
/* 1257 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1258 */     if (jj_3R_25()) return true; 
/* 1259 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1260 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_5() {
/* 1264 */     if (jj_3R_16()) return true; 
/* 1265 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1266 */     if (jj_scan_token(22)) return true; 
/* 1267 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false;
/*      */     
/* 1269 */     Token xsp = this.jj_scanpos;
/* 1270 */     if (jj_3R_17()) { this.jj_scanpos = xsp; }
/* 1271 */     else if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) { return false; }
/* 1272 */      if (jj_scan_token(30)) return true; 
/* 1273 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1274 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_22() {
/* 1278 */     if (jj_scan_token(29)) return true; 
/* 1279 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1280 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_67() {
/* 1284 */     if (jj_3R_16()) return true; 
/* 1285 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1286 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_66() {
/* 1290 */     if (jj_scan_token(43)) return true; 
/* 1291 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1292 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_65() {
/* 1296 */     if (jj_scan_token(41)) return true; 
/* 1297 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1298 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_64() {
/* 1302 */     if (jj_scan_token(40)) return true; 
/* 1303 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1304 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_63() {
/* 1308 */     if (jj_scan_token(11)) return true; 
/* 1309 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1310 */     if (jj_3R_48()) return true; 
/* 1311 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1312 */     if (jj_scan_token(12)) return true; 
/* 1313 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1314 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_62() {
/* 1318 */     if (jj_scan_token(32)) return true; 
/* 1319 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1320 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_61() {
/* 1324 */     if (jj_scan_token(31)) return true; 
/* 1325 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1326 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   private final boolean jj_3R_56() {
/* 1331 */     Token xsp = this.jj_scanpos;
/* 1332 */     if (jj_3R_61())
/* 1333 */     { this.jj_scanpos = xsp;
/* 1334 */       if (jj_3R_62())
/* 1335 */       { this.jj_scanpos = xsp;
/* 1336 */         if (jj_3R_63())
/* 1337 */         { this.jj_scanpos = xsp;
/* 1338 */           if (jj_3R_64())
/* 1339 */           { this.jj_scanpos = xsp;
/* 1340 */             if (jj_3R_65())
/* 1341 */             { this.jj_scanpos = xsp;
/* 1342 */               if (jj_3R_66())
/* 1343 */               { this.jj_scanpos = xsp;
/* 1344 */                 if (jj_3R_67()) return true; 
/* 1345 */                 if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false;  }
/* 1346 */               else if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) { return false; }  }
/* 1347 */             else if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) { return false; }  }
/* 1348 */           else if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) { return false; }  }
/* 1349 */         else if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) { return false; }  }
/* 1350 */       else if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) { return false; }  }
/* 1351 */     else if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) { return false; }
/* 1352 */      return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_46() {
/* 1356 */     if (jj_3R_51()) return true; 
/* 1357 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1358 */     if (jj_3R_30()) return true; 
/* 1359 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1360 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_50() {
/* 1364 */     if (jj_3R_47()) return true; 
/* 1365 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1366 */     if (jj_3R_45()) return true; 
/* 1367 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1368 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_49() {
/* 1372 */     if (jj_3R_56()) return true; 
/* 1373 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1374 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_21() {
/* 1378 */     if (jj_3R_39()) return true; 
/* 1379 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1380 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   private final boolean jj_3R_45() {
/* 1385 */     Token xsp = this.jj_scanpos;
/* 1386 */     if (jj_3R_49())
/* 1387 */     { this.jj_scanpos = xsp;
/* 1388 */       if (jj_3R_50()) return true; 
/* 1389 */       if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false;  }
/* 1390 */     else if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) { return false; }
/* 1391 */      return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_58() {
/* 1395 */     if (jj_scan_token(10)) return true; 
/* 1396 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1397 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   private final boolean jj_3R_51() {
/* 1402 */     Token xsp = this.jj_scanpos;
/* 1403 */     if (jj_3R_57())
/* 1404 */     { this.jj_scanpos = xsp;
/* 1405 */       if (jj_3R_58()) return true; 
/* 1406 */       if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false;  }
/* 1407 */     else if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) { return false; }
/* 1408 */      return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_57() {
/* 1412 */     if (jj_scan_token(9)) return true; 
/* 1413 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1414 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_31() {
/* 1418 */     if (jj_3R_47()) return true; 
/* 1419 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1420 */     if (jj_3R_14()) return true; 
/* 1421 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1422 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_30() {
/* 1426 */     if (jj_3R_45()) return true; 
/* 1427 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false;
/*      */     
/* 1429 */     Token xsp = this.jj_scanpos;
/* 1430 */     if (jj_3R_46()) { this.jj_scanpos = xsp; }
/* 1431 */     else if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) { return false; }
/* 1432 */      return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_8() {
/* 1436 */     if (jj_3R_16()) return true; 
/* 1437 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false;
/*      */     
/* 1439 */     Token xsp = this.jj_scanpos;
/* 1440 */     if (jj_3R_21()) { this.jj_scanpos = xsp; }
/* 1441 */     else if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) { return false; }
/* 1442 */      if (jj_scan_token(22)) return true; 
/* 1443 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1444 */     xsp = this.jj_scanpos;
/* 1445 */     if (jj_3R_22()) { this.jj_scanpos = xsp; }
/* 1446 */     else if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) { return false; }
/* 1447 */      if (jj_scan_token(33)) return true; 
/* 1448 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1449 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_53() {
/* 1453 */     if (jj_scan_token(8)) return true; 
/* 1454 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1455 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   private final boolean jj_3R_47() {
/* 1460 */     Token xsp = this.jj_scanpos;
/* 1461 */     if (jj_3R_52())
/* 1462 */     { this.jj_scanpos = xsp;
/* 1463 */       if (jj_3R_53()) return true; 
/* 1464 */       if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false;  }
/* 1465 */     else if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) { return false; }
/* 1466 */      return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_52() {
/* 1470 */     if (jj_scan_token(7)) return true; 
/* 1471 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1472 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_13() {
/* 1476 */     if (jj_scan_token(15)) return true; 
/* 1477 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1478 */     if (jj_scan_token(40)) return true; 
/* 1479 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1480 */     if (jj_scan_token(16)) return true; 
/* 1481 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1482 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_44() {
/* 1486 */     if (jj_3R_13()) return true; 
/* 1487 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1488 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_14() {
/* 1492 */     if (jj_3R_30()) return true; 
/* 1493 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false;
/*      */     
/* 1495 */     Token xsp = this.jj_scanpos;
/* 1496 */     if (jj_3R_31()) { this.jj_scanpos = xsp; }
/* 1497 */     else if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) { return false; }
/* 1498 */      return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_39() {
/* 1502 */     if (jj_scan_token(15)) return true; 
/* 1503 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1504 */     if (jj_3R_48()) return true; 
/* 1505 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1506 */     if (jj_scan_token(16)) return true; 
/* 1507 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1508 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_41() {
/* 1512 */     if (jj_scan_token(36)) return true; 
/* 1513 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1514 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_9() {
/* 1518 */     if (jj_3R_23()) return true; 
/* 1519 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1520 */     if (jj_scan_token(34)) return true; 
/* 1521 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1522 */     if (jj_3R_23()) return true; 
/* 1523 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1524 */     return false;
/*      */   }
/*      */   
/*      */   private final boolean jj_3R_37() {
/* 1528 */     if (jj_scan_token(6)) return true; 
/* 1529 */     if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) return false; 
/* 1530 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean lookingAhead = false;
/*      */   
/*      */   private boolean jj_semLA;
/*      */   
/*      */   private int jj_gen;
/*      */   
/* 1541 */   private final int[] jj_la1 = new int[24];
/* 1542 */   private final int[] jj_la1_0 = new int[] { 134217728, 268435456, 536870912, -2147481216, 126, 384, 384, 1536, 1536, -2147481216, -2147481600, 536870912, 536870912, 536870912, 8192, 8192, 536870912, 536870912, 67108864, 32768, 32768, 0, 32768, 536870912 };
/* 1543 */   private final int[] jj_la1_1 = new int[] { 0, 0, 0, 2841, 0, 0, 0, 0, 0, 2841, 2841, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 24, 0, 0 };
/* 1544 */   private final JJCalls[] jj_2_rtns = new JJCalls[10];
/*      */   private boolean jj_rescan = false;
/* 1546 */   private int jj_gc = 0;
/*      */   
/*      */   private Vector jj_expentries;
/*      */   
/*      */   private int[] jj_expentry;
/*      */   
/*      */   private int jj_kind;
/*      */   
/*      */   private int[] jj_lasttokens;
/*      */   private int jj_endpos;
/*      */   
/*      */   public void ReInit(CharStream stream) {
/* 1558 */     this.token_source.ReInit(stream);
/* 1559 */     this.token = new Token();
/* 1560 */     this.jj_ntk = -1;
/* 1561 */     this.jj_gen = 0; int i;
/* 1562 */     for (i = 0; i < 24; ) { this.jj_la1[i] = -1; i++; }
/* 1563 */      for (i = 0; i < this.jj_2_rtns.length; ) { this.jj_2_rtns[i] = new JJCalls(); i++; }
/*      */   
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
/*      */   public void ReInit(MatchParserTokenManager tm) {
/* 1576 */     this.token_source = tm;
/* 1577 */     this.token = new Token();
/* 1578 */     this.jj_ntk = -1;
/* 1579 */     this.jj_gen = 0; int i;
/* 1580 */     for (i = 0; i < 24; ) { this.jj_la1[i] = -1; i++; }
/* 1581 */      for (i = 0; i < this.jj_2_rtns.length; ) { this.jj_2_rtns[i] = new JJCalls(); i++; }
/*      */   
/*      */   }
/*      */   private final Token jj_consume_token(int kind) throws ParseException {
/*      */     Token oldToken;
/* 1586 */     if ((oldToken = this.token).next != null) { this.token = this.token.next; }
/* 1587 */     else { this.token = this.token.next = this.token_source.getNextToken(); }
/* 1588 */      this.jj_ntk = -1;
/* 1589 */     if (this.token.kind == kind) {
/* 1590 */       this.jj_gen++;
/* 1591 */       if (++this.jj_gc > 100) {
/* 1592 */         this.jj_gc = 0;
/* 1593 */         for (int i = 0; i < this.jj_2_rtns.length; i++) {
/* 1594 */           JJCalls c = this.jj_2_rtns[i];
/* 1595 */           while (c != null) {
/* 1596 */             if (c.gen < this.jj_gen) c.first = null; 
/* 1597 */             c = c.next;
/*      */           } 
/*      */         } 
/*      */       } 
/* 1601 */       return this.token;
/*      */     } 
/* 1603 */     this.token = oldToken;
/* 1604 */     this.jj_kind = kind;
/* 1605 */     throw generateParseException();
/*      */   }
/*      */   
/*      */   private final boolean jj_scan_token(int kind) {
/* 1609 */     if (this.jj_scanpos == this.jj_lastpos) {
/* 1610 */       this.jj_la--;
/* 1611 */       if (this.jj_scanpos.next == null) {
/* 1612 */         this.jj_lastpos = this.jj_scanpos = this.jj_scanpos.next = this.token_source.getNextToken();
/*      */       } else {
/* 1614 */         this.jj_lastpos = this.jj_scanpos = this.jj_scanpos.next;
/*      */       } 
/*      */     } else {
/* 1617 */       this.jj_scanpos = this.jj_scanpos.next;
/*      */     } 
/* 1619 */     if (this.jj_rescan) {
/* 1620 */       int i = 0; Token tok = this.token;
/* 1621 */       while (tok != null && tok != this.jj_scanpos) { i++; tok = tok.next; }
/* 1622 */        if (tok != null) jj_add_error_token(kind, i); 
/*      */     } 
/* 1624 */     return (this.jj_scanpos.kind != kind);
/*      */   }
/*      */   
/*      */   public final Token getNextToken() {
/* 1628 */     if (this.token.next != null) { this.token = this.token.next; }
/* 1629 */     else { this.token = this.token.next = this.token_source.getNextToken(); }
/* 1630 */      this.jj_ntk = -1;
/* 1631 */     this.jj_gen++;
/* 1632 */     return this.token;
/*      */   }
/*      */   
/*      */   public final Token getToken(int index) {
/* 1636 */     Token t = this.lookingAhead ? this.jj_scanpos : this.token;
/* 1637 */     for (int i = 0; i < index; i++) {
/* 1638 */       if (t.next != null) { t = t.next; }
/* 1639 */       else { t = t.next = this.token_source.getNextToken(); }
/*      */     
/* 1641 */     }  return t;
/*      */   }
/*      */   
/*      */   private final int jj_ntk() {
/* 1645 */     if ((this.jj_nt = this.token.next) == null) {
/* 1646 */       return this.jj_ntk = (this.token.next = this.token_source.getNextToken()).kind;
/*      */     }
/* 1648 */     return this.jj_ntk = this.jj_nt.kind;
/*      */   }
/*      */   
/* 1651 */   public MatchParser(CharStream stream) { this.jj_expentries = new Vector();
/*      */     
/* 1653 */     this.jj_kind = -1;
/* 1654 */     this.jj_lasttokens = new int[100]; this.token_source = new MatchParserTokenManager(stream); this.token = new Token(); this.jj_ntk = -1; this.jj_gen = 0; int i; for (i = 0; i < 24; ) { this.jj_la1[i] = -1; i++; }  for (i = 0; i < this.jj_2_rtns.length; ) { this.jj_2_rtns[i] = new JJCalls(); i++; }  } public MatchParser(MatchParserTokenManager tm) { this.jj_expentries = new Vector(); this.jj_kind = -1; this.jj_lasttokens = new int[100]; this.token_source = tm; this.token = new Token(); this.jj_ntk = -1; this.jj_gen = 0; int i; for (i = 0; i < 24; ) {
/*      */       this.jj_la1[i] = -1; i++;
/*      */     }  for (i = 0; i < this.jj_2_rtns.length; ) {
/*      */       this.jj_2_rtns[i] = new JJCalls(); i++;
/* 1658 */     }  } private void jj_add_error_token(int kind, int pos) { if (pos >= 100)
/* 1659 */       return;  if (pos == this.jj_endpos + 1) {
/* 1660 */       this.jj_lasttokens[this.jj_endpos++] = kind;
/* 1661 */     } else if (this.jj_endpos != 0) {
/* 1662 */       this.jj_expentry = new int[this.jj_endpos];
/* 1663 */       for (int i = 0; i < this.jj_endpos; i++) {
/* 1664 */         this.jj_expentry[i] = this.jj_lasttokens[i];
/*      */       }
/* 1666 */       boolean exists = false;
/* 1667 */       for (Enumeration<int[]> enum1 = this.jj_expentries.elements(); enum1.hasMoreElements(); ) {
/* 1668 */         int[] oldentry = enum1.nextElement();
/* 1669 */         if (oldentry.length == this.jj_expentry.length) {
/* 1670 */           exists = true;
/* 1671 */           for (int j = 0; j < this.jj_expentry.length; j++) {
/* 1672 */             if (oldentry[j] != this.jj_expentry[j]) {
/* 1673 */               exists = false;
/*      */               break;
/*      */             } 
/*      */           } 
/* 1677 */           if (exists)
/*      */             break; 
/*      */         } 
/* 1680 */       }  if (!exists) this.jj_expentries.addElement(this.jj_expentry); 
/* 1681 */       if (pos != 0) this.jj_lasttokens[(this.jj_endpos = pos) - 1] = kind; 
/*      */     }  }
/*      */ 
/*      */   
/*      */   public final ParseException generateParseException() {
/* 1686 */     this.jj_expentries.removeAllElements();
/* 1687 */     boolean[] la1tokens = new boolean[44]; int i;
/* 1688 */     for (i = 0; i < 44; i++) {
/* 1689 */       la1tokens[i] = false;
/*      */     }
/* 1691 */     if (this.jj_kind >= 0) {
/* 1692 */       la1tokens[this.jj_kind] = true;
/* 1693 */       this.jj_kind = -1;
/*      */     } 
/* 1695 */     for (i = 0; i < 24; i++) {
/* 1696 */       if (this.jj_la1[i] == this.jj_gen) {
/* 1697 */         for (int k = 0; k < 32; k++) {
/* 1698 */           if ((this.jj_la1_0[i] & 1 << k) != 0) {
/* 1699 */             la1tokens[k] = true;
/*      */           }
/* 1701 */           if ((this.jj_la1_1[i] & 1 << k) != 0) {
/* 1702 */             la1tokens[32 + k] = true;
/*      */           }
/*      */         } 
/*      */       }
/*      */     } 
/* 1707 */     for (i = 0; i < 44; i++) {
/* 1708 */       if (la1tokens[i]) {
/* 1709 */         this.jj_expentry = new int[1];
/* 1710 */         this.jj_expentry[0] = i;
/* 1711 */         this.jj_expentries.addElement(this.jj_expentry);
/*      */       } 
/*      */     } 
/* 1714 */     this.jj_endpos = 0;
/* 1715 */     jj_rescan_token();
/* 1716 */     jj_add_error_token(0, 0);
/* 1717 */     int[][] exptokseq = new int[this.jj_expentries.size()][];
/* 1718 */     for (int j = 0; j < this.jj_expentries.size(); j++) {
/* 1719 */       exptokseq[j] = this.jj_expentries.elementAt(j);
/*      */     }
/* 1721 */     return new ParseException(this.token, exptokseq, tokenImage);
/*      */   }
/*      */ 
/*      */   
/*      */   public final void enable_tracing() {}
/*      */ 
/*      */   
/*      */   public final void disable_tracing() {}
/*      */   
/*      */   private final void jj_rescan_token() {
/* 1731 */     this.jj_rescan = true;
/* 1732 */     for (int i = 0; i < 10; ) {
/* 1733 */       JJCalls p = this.jj_2_rtns[i];
/*      */       while (true)
/* 1735 */       { if (p.gen > this.jj_gen) {
/* 1736 */           this.jj_la = p.arg; this.jj_lastpos = this.jj_scanpos = p.first;
/* 1737 */           switch (i) { case 0:
/* 1738 */               jj_3_1(); break;
/* 1739 */             case 1: jj_3_2(); break;
/* 1740 */             case 2: jj_3_3(); break;
/* 1741 */             case 3: jj_3_4(); break;
/* 1742 */             case 4: jj_3_5(); break;
/* 1743 */             case 5: jj_3_6(); break;
/* 1744 */             case 6: jj_3_7(); break;
/* 1745 */             case 7: jj_3_8(); break;
/* 1746 */             case 8: jj_3_9(); break;
/* 1747 */             case 9: jj_3_10(); break; }
/*      */         
/*      */         } 
/* 1750 */         p = p.next;
/* 1751 */         if (p == null)
/*      */           i++;  } 
/* 1753 */     }  this.jj_rescan = false;
/*      */   }
/*      */   
/*      */   private final void jj_save(int index, int xla) {
/* 1757 */     JJCalls p = this.jj_2_rtns[index];
/* 1758 */     while (p.gen > this.jj_gen) {
/* 1759 */       if (p.next == null) { p = p.next = new JJCalls(); break; }
/* 1760 */        p = p.next;
/*      */     } 
/* 1762 */     p.gen = this.jj_gen + xla - this.jj_la; p.first = this.token; p.arg = xla;
/*      */   }
/*      */   
/*      */   static final class JJCalls {
/*      */     private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2005 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*      */     int gen;
/*      */     Token first;
/*      */     int arg;
/*      */     JJCalls next;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\selector\MatchParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */