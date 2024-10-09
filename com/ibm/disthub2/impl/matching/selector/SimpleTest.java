/*     */ package com.ibm.disthub2.impl.matching.selector;
/*     */ 
/*     */ import com.ibm.disthub2.impl.matching.BadMessageFormatMatchingException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class SimpleTest
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   public Identifier identifier;
/*     */   public static final int ID = 0;
/*     */   public static final int NOTID = 1;
/*     */   public static final int STRINGEQ = 2;
/*     */   public static final int STRINGOTH = 3;
/*     */   public static final int NULL = 4;
/*     */   public static final int NOTNULL = 5;
/*     */   public static final int NUMERIC = 6;
/*     */   public int kind;
/*     */   public String stringVal;
/*     */   public NumericValue lower;
/*     */   public boolean lowIncl;
/*     */   public NumericValue upper;
/*     */   public boolean upIncl;
/*     */   public Selector[] tests;
/*     */   
/*     */   public static boolean isSimple(Selector sel) {
/* 158 */     if (sel.numIds != 1)
/* 159 */       return false; 
/* 160 */     if (!(sel instanceof Operator))
/* 161 */       return true; 
/* 162 */     Operator oper = (Operator)sel;
/* 163 */     if (oper.op == 5)
/* 164 */       return false; 
/* 165 */     for (int i = 0; i < oper.operands.length; i++) {
/* 166 */       if ((oper.operands[i]).numIds == 1 && !isSimple(oper.operands[i]))
/* 167 */         return false; 
/* 168 */     }  return true;
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
/*     */   SimpleTest(Selector sel) {
/*     */     Operator subOp;
/*     */     Object value;
/* 183 */     if (sel instanceof Identifier) {
/* 184 */       this.identifier = (Identifier)sel;
/* 185 */       this.kind = 0;
/*     */       return;
/*     */     } 
/* 188 */     if (!(sel instanceof Operator))
/* 189 */       throw new IllegalArgumentException(); 
/* 190 */     Operator oper = (Operator)sel;
/* 191 */     switch (oper.op) {
/*     */       case 4:
/* 193 */         this.identifier = (Identifier)oper.operands[0];
/* 194 */         this.kind = 3;
/* 195 */         this.tests = new Selector[] { sel };
/*     */         return;
/*     */       case 1:
/* 198 */         if (oper.operands[0] instanceof Identifier) {
/* 199 */           this.identifier = (Identifier)oper.operands[0];
/* 200 */           this.kind = 1;
/*     */           return;
/*     */         } 
/* 203 */         subOp = (Operator)oper.operands[0];
/* 204 */         this.identifier = (Identifier)subOp.operands[0];
/* 205 */         if (subOp.op == 4) {
/* 206 */           this.kind = 3;
/* 207 */           this.tests = new Selector[] { sel };
/*     */           return;
/*     */         } 
/* 210 */         if (subOp.op == 3) {
/* 211 */           this.kind = 5;
/*     */           return;
/*     */         } 
/* 214 */         throw new IllegalArgumentException();
/*     */       case 3:
/* 216 */         this.identifier = (Identifier)oper.operands[0];
/* 217 */         this.kind = 4;
/*     */         return;
/*     */       case 40:
/*     */       case 41:
/*     */       case 42:
/*     */       case 43:
/*     */       case 44:
/*     */       case 45:
/* 225 */         oper = simpleComparison(oper);
/* 226 */         this.identifier = (Identifier)oper.operands[0];
/* 227 */         value = Evaluator.eval(oper.operands[1]);
/* 228 */         if (value instanceof String) {
/* 229 */           if (oper.op == 45) {
/* 230 */             this.kind = 2;
/* 231 */             this.stringVal = (String)value;
/*     */           } else {
/*     */             
/* 234 */             this.kind = 3;
/* 235 */             this.tests = new Selector[] { oper };
/*     */           } 
/*     */         } else {
/* 238 */           recordNumericComparison(oper.op, (NumericValue)value);
/*     */         } 
/*     */         return;
/*     */     } 
/* 242 */     throw new IllegalArgumentException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void recordNumericComparison(int op, NumericValue value) {
/* 250 */     this.kind = 6;
/* 251 */     switch (op) {
/*     */       case 45:
/* 253 */         this.upper = this.lower = value;
/* 254 */         this.upIncl = this.lowIncl = true;
/*     */         return;
/*     */       case 42:
/* 257 */         this.upper = value;
/*     */         return;
/*     */       case 41:
/* 260 */         this.lower = value;
/*     */         return;
/*     */       case 44:
/* 263 */         this.upper = value;
/* 264 */         this.upIncl = true;
/*     */         return;
/*     */       case 43:
/* 267 */         this.lower = value;
/* 268 */         this.lowIncl = true;
/*     */         return;
/*     */     } 
/* 271 */     throw new IllegalArgumentException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Identifier findIdentifier(Selector tree) {
/* 279 */     if (tree instanceof Identifier)
/* 280 */       return (Identifier)tree; 
/* 281 */     if (tree instanceof Operator) {
/* 282 */       Operator oper = (Operator)tree;
/* 283 */       for (int i = 0; i < oper.operands.length; i++) {
/* 284 */         if ((oper.operands[i]).numIds > 0)
/* 285 */           return findIdentifier(oper.operands[i]); 
/*     */       } 
/* 287 */     }  throw new IllegalArgumentException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Operator simpleComparison(Operator expr) {
/* 296 */     int op = expr.op;
/* 297 */     Selector idExpr = expr.operands[0];
/* 298 */     Selector constantExpr = expr.operands[1];
/* 299 */     if (idExpr.numIds == 0) {
/*     */       
/* 301 */       Selector temp = constantExpr;
/* 302 */       constantExpr = idExpr;
/* 303 */       idExpr = temp;
/* 304 */       op = invertComparison(op);
/*     */     } 
/* 306 */     while (!(idExpr instanceof Identifier)) {
/*     */       Selector residue;
/*     */       
/*     */       int newOp;
/* 310 */       Operator currOp = (Operator)idExpr;
/* 311 */       if (currOp.op == 2) {
/* 312 */         op = invertComparison(op);
/* 313 */         idExpr = currOp.operands[0];
/* 314 */         constantExpr = new Operator(2, constantExpr);
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/* 319 */       if ((currOp.operands[0]).numIds == 0) {
/*     */         
/* 321 */         switch (currOp.op) {
/*     */           case 48:
/*     */           case 50:
/*     */             break;
/*     */           case 49:
/* 326 */             op = invertComparison(op);
/* 327 */             constantExpr = new Operator(2, constantExpr);
/*     */             break;
/*     */           case 51:
/* 330 */             op = invertComparison(op);
/* 331 */             constantExpr = new Operator(51, new Literal(Integer.valueOf(1)), constantExpr);
/*     */             break;
/*     */           
/*     */           default:
/* 335 */             throw new IllegalArgumentException();
/*     */         } 
/* 337 */         residue = currOp.operands[0];
/* 338 */         idExpr = currOp.operands[1];
/*     */       }
/*     */       else {
/*     */         
/* 342 */         residue = currOp.operands[1];
/* 343 */         idExpr = currOp.operands[0];
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 348 */       switch (currOp.op) {
/*     */         case 48:
/* 350 */           newOp = 49;
/*     */           break;
/*     */         case 50:
/* 353 */           newOp = 51;
/*     */           break;
/*     */         case 49:
/* 356 */           newOp = 48;
/*     */           break;
/*     */         case 51:
/* 359 */           newOp = 50;
/*     */           break;
/*     */         default:
/* 362 */           throw new IllegalArgumentException();
/*     */       } 
/* 364 */       constantExpr = new Operator(newOp, constantExpr, residue);
/*     */     } 
/*     */     
/* 367 */     return new Operator(op, idExpr, constantExpr);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int invertComparison(int op) {
/* 375 */     switch (op) {
/*     */       case 40:
/*     */       case 45:
/* 378 */         return op;
/*     */       case 42:
/* 380 */         return 41;
/*     */       case 41:
/* 382 */         return 42;
/*     */       case 44:
/* 384 */         return 43;
/*     */       case 43:
/* 386 */         return 44;
/*     */     } 
/* 388 */     throw new IllegalArgumentException();
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
/*     */   public Object getValue() {
/* 402 */     switch (this.kind) {
/*     */       case 2:
/* 404 */         return this.stringVal;
/*     */       case 6:
/* 406 */         if (this.upper != null && this.lower != null && this.upIncl && this.lowIncl && this.lower.compareTo(this.upper) == 0) {
/* 407 */           return this.lower;
/*     */         }
/* 409 */         return null;
/*     */       case 0:
/* 411 */         return BooleanValue.TRUE;
/*     */       case 1:
/* 413 */         return BooleanValue.FALSE;
/*     */     } 
/* 415 */     return null;
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
/*     */   public boolean combine(SimpleTest other) {
/* 431 */     switch (this.kind) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 4:
/* 435 */         return (this.kind == other.kind);
/*     */       case 5:
/* 437 */         absorb(other);
/* 438 */         return true;
/*     */       case 2:
/* 440 */         if (other.kind == 2)
/* 441 */           return this.stringVal.equals(other.stringVal); 
/* 442 */         if (other.kind == 3) {
/* 443 */           return selects(this.stringVal, other.tests);
/*     */         }
/* 445 */         return (other.kind == 5);
/*     */       case 3:
/* 447 */         if (other.kind == 3) {
/* 448 */           this.tests = append(this.tests, other.tests);
/* 449 */           return true;
/*     */         } 
/* 451 */         if (other.kind == 2) {
/* 452 */           if (selects(other.stringVal, this.tests)) {
/* 453 */             absorb(other);
/* 454 */             return true;
/*     */           } 
/*     */           
/* 457 */           return false;
/*     */         } 
/* 459 */         return (other.kind == 5);
/*     */       case 6:
/* 461 */         if (other.kind == 6) {
/* 462 */           return combineNumeric(other);
/*     */         }
/* 464 */         return (other.kind == 5);
/*     */     } 
/* 466 */     throw new IllegalArgumentException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void absorb(SimpleTest other) {
/* 473 */     this.kind = other.kind;
/* 474 */     this.stringVal = other.stringVal;
/* 475 */     this.tests = other.tests;
/* 476 */     this.upper = other.upper;
/* 477 */     this.lower = other.lower;
/* 478 */     this.upIncl = other.upIncl;
/* 479 */     this.lowIncl = other.lowIncl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Selector[] append(Selector[] one, Selector[] two) {
/* 486 */     Selector[] ans = new Selector[one.length + two.length];
/* 487 */     System.arraycopy(one, 0, ans, 0, one.length);
/* 488 */     System.arraycopy(two, 0, ans, one.length, two.length);
/* 489 */     return ans;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean selects(String value, Selector[] tests) {
/* 497 */     EvalContext ec = new OneIdentifierContext(value);
/*     */     try {
/* 499 */       for (int i = 0; i < tests.length; i++) {
/* 500 */         if (!((BooleanValue)Evaluator.eval(tests[i], ec, false)).booleanValue())
/* 501 */           return false; 
/* 502 */       }  return true;
/* 503 */     } catch (BadMessageFormatMatchingException e) {
/*     */       
/* 505 */       throw new IllegalStateException();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean combineNumeric(SimpleTest other) {
/*     */     NumericValue newUpper, newLower;
/*     */     boolean newLowIncl, newUpIncl;
/* 516 */     if (this.upper == null) {
/* 517 */       newUpper = other.upper;
/* 518 */       newUpIncl = other.upIncl;
/*     */     }
/* 520 */     else if (other.upper == null) {
/* 521 */       newUpper = this.upper;
/* 522 */       newUpIncl = this.upIncl;
/*     */     } else {
/*     */       
/* 525 */       int upComp = this.upper.compareTo(other.upper);
/* 526 */       if (upComp < 0) {
/* 527 */         newUpper = this.upper;
/* 528 */         newUpIncl = this.upIncl;
/*     */       }
/* 530 */       else if (upComp == 0) {
/* 531 */         newUpper = this.upper;
/* 532 */         int i = this.upIncl & other.upIncl;
/*     */       } else {
/*     */         
/* 535 */         newUpper = other.upper;
/* 536 */         newUpIncl = other.upIncl;
/*     */       } 
/*     */     } 
/* 539 */     if (this.lower == null) {
/* 540 */       newLower = other.lower;
/* 541 */       newLowIncl = other.lowIncl;
/*     */     }
/* 543 */     else if (other.lower == null) {
/* 544 */       newLower = this.lower;
/* 545 */       newLowIncl = this.lowIncl;
/*     */     } else {
/*     */       
/* 548 */       int lowComp = this.lower.compareTo(other.lower);
/* 549 */       if (lowComp > 0) {
/* 550 */         newLower = this.lower;
/* 551 */         newLowIncl = this.lowIncl;
/*     */       }
/* 553 */       else if (lowComp == 0) {
/* 554 */         newLower = this.lower;
/* 555 */         int i = this.lowIncl & other.lowIncl;
/*     */       } else {
/*     */         
/* 558 */         newLower = other.lower;
/* 559 */         newLowIncl = other.lowIncl;
/*     */       } 
/*     */     } 
/* 562 */     if (newLower != null && newUpper != null) {
/* 563 */       int vTest = newLower.compareTo(newUpper);
/* 564 */       if (vTest > 0)
/* 565 */         return false; 
/* 566 */       if (vTest == 0 && (
/* 567 */         !newLowIncl || !newUpIncl)) {
/* 568 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 572 */     this.upper = newUpper;
/* 573 */     this.lower = newLower;
/* 574 */     this.upIncl = newUpIncl;
/* 575 */     this.lowIncl = newLowIncl;
/* 576 */     return true;
/*     */   }
/*     */   
/*     */   private static final class OneIdentifierContext
/*     */     implements EvalContext
/*     */   {
/*     */     String value;
/*     */     
/*     */     OneIdentifierContext(String value) {
/* 585 */       this.value = value;
/*     */     }
/* 587 */     public Object getIdentifierValue(Identifier id, boolean ignoreType) { return this.value; } public Object getExprValue(int key) {
/* 588 */       return null;
/*     */     }
/*     */     
/*     */     public void saveExprValue(int key, Object value) {}
/*     */     
/*     */     public void prepareCache(int size) {}
/*     */   }
/*     */   
/*     */   public String toString() {
/* 597 */     return toSelector().toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Selector toSelector() {
/*     */     Selector ans;
/*     */     int i;
/* 605 */     switch (this.kind) {
/*     */       case 0:
/* 607 */         return this.identifier;
/*     */       case 1:
/* 609 */         return new Operator(1, this.identifier);
/*     */       case 4:
/* 611 */         return new Operator(3, this.identifier);
/*     */       case 5:
/* 613 */         return new Operator(1, new Operator(3, this.identifier));
/*     */       case 2:
/* 615 */         return new Operator(45, this.identifier, new Literal(this.stringVal));
/*     */       case 3:
/* 617 */         ans = null;
/* 618 */         for (i = 0; i < this.tests.length; i++) {
/* 619 */           if (ans == null) {
/* 620 */             ans = this.tests[i];
/*     */           } else {
/* 622 */             ans = new Operator(46, ans, this.tests[i]);
/*     */           } 
/* 624 */         }  return ans;
/*     */       case 6:
/* 626 */         if (this.upper == null) {
/* 627 */           if (this.lowIncl) {
/* 628 */             return new Operator(43, this.identifier, new Literal(this.lower));
/*     */           }
/* 630 */           return new Operator(41, this.identifier, new Literal(this.lower));
/* 631 */         }  if (this.lower == null) {
/* 632 */           if (this.upIncl) {
/* 633 */             return new Operator(44, this.identifier, new Literal(this.upper));
/*     */           }
/* 635 */           return new Operator(42, this.identifier, new Literal(this.upper));
/* 636 */         }  if (this.lower.compareTo(this.upper) == 0)
/* 637 */           return new Operator(45, this.identifier, new Literal(this.lower)); 
/* 638 */         return new Operator(46, new Operator(this.lowIncl ? 43 : 41, this.identifier, new Literal(this.lower)), new Operator(this.upIncl ? 44 : 42, this.identifier, new Literal(this.upper)));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 644 */     throw new IllegalArgumentException();
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\selector\SimpleTest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */