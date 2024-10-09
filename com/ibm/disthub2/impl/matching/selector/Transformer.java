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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Transformer
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   
/*     */   public static Selector resolve(Selector tree, Resolver resolver, PositionAssigner positionAssigner) {
/*  51 */     return resolve(tree, resolver, positionAssigner, new Object[1]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Selector resolve(Selector tree, Resolver resolver, PositionAssigner positionAssigner, Object[] context) {
/*  58 */     if (tree instanceof Identifier) {
/*  59 */       Identifier id = (Identifier)tree;
/*     */ 
/*     */       
/*  62 */       Selector res = resolver.resolve(id, positionAssigner, context[0]);
/*     */ 
/*     */       
/*  65 */       if (res.type == 4 && res instanceof Identifier)
/*  66 */         context[0] = resolver.pushContext(context[0], (Identifier)res); 
/*  67 */       return res;
/*     */     } 
/*  69 */     if (tree.numIds > 0) {
/*  70 */       Operator oper = (Operator)tree;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  76 */       Object oldContext = context[0];
/*  77 */       for (int i = 0; i < oper.operands.length; i++) {
/*  78 */         oper.operands[i] = resolve(oper.operands[i], resolver, positionAssigner, context);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*  83 */       if (oper.op == 5 || (oper.op == 54 && oper.type != 4)) {
/*     */         
/*  85 */         resolver.restoreContext(oldContext);
/*  86 */         context[0] = oldContext;
/*     */       }
/*  88 */       else if (oper.op == 54) {
/*  89 */         context[0] = resolver.pushContext(context[0], (Identifier)oper.operands[1]);
/*  90 */       }  oper.assignType();
/*     */     } 
/*  92 */     return tree;
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
/*     */   public static Selector DNF(Selector tree) {
/* 110 */     tree = simplifyTree(tree);
/*     */ 
/*     */     
/* 113 */     return DNF0(tree);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Selector DNF0(Selector tree) {
/* 121 */     if (!(tree instanceof Operator))
/* 122 */       return tree; 
/* 123 */     Operator oper = (Operator)tree;
/* 124 */     switch (oper.op) {
/*     */       case 46:
/* 126 */         return processAND(oper.operands[0], oper.operands[1]);
/*     */       case 47:
/* 128 */         return makeOR(DNF0(oper.operands[0]), DNF0(oper.operands[1]));
/*     */     } 
/* 130 */     return tree;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Selector processAND(Selector sel0, Selector sel1) {
/* 138 */     sel0 = DNF0(sel0);
/* 139 */     sel1 = DNF0(sel1);
/* 140 */     if (sel0 instanceof Operator) {
/* 141 */       Operator oper = (Operator)sel0;
/* 142 */       if (oper.op == 47) {
/* 143 */         return makeOR(processAND(oper.operands[0], sel1), 
/* 144 */             processAND(oper.operands[1], sel1));
/*     */       }
/*     */     } 
/* 147 */     return processANDRight(sel0, sel1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Selector processANDRight(Selector sel0, Selector sel1) {
/* 154 */     if (sel1 instanceof Operator) {
/* 155 */       Operator oper = (Operator)sel1;
/* 156 */       if (oper.op == 47) {
/* 157 */         return makeOR(processANDRight(sel0, oper.operands[0]), 
/* 158 */             processANDRight(sel0, oper.operands[1]));
/*     */       }
/*     */     } 
/* 161 */     return makeAND(sel0, sel1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Selector makeOR(Selector sel0, Selector sel1) {
/* 170 */     if (sel0.numIds == 0) {
/*     */       
/* 172 */       BooleanValue staticResult = (BooleanValue)Evaluator.eval(sel0);
/* 173 */       if (staticResult.booleanValue()) {
/* 174 */         return new Literal(BooleanValue.TRUE);
/*     */       }
/* 176 */       return evalOf(sel1);
/*     */     } 
/* 178 */     if (sel1.numIds == 0) {
/*     */       
/* 180 */       BooleanValue staticResult = (BooleanValue)Evaluator.eval(sel1);
/* 181 */       if (staticResult.booleanValue()) {
/* 182 */         return new Literal(BooleanValue.TRUE);
/*     */       }
/* 184 */       return evalOf(sel0);
/*     */     } 
/* 186 */     return new Operator(47, sel0, sel1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Selector makeAND(Selector sel0, Selector sel1) {
/* 195 */     if (sel0.numIds == 0) {
/*     */       
/* 197 */       BooleanValue staticResult = (BooleanValue)Evaluator.eval(sel0);
/* 198 */       if (!staticResult.booleanValue()) {
/* 199 */         return new Literal(BooleanValue.FALSE);
/*     */       }
/* 201 */       return evalOf(sel1);
/*     */     } 
/* 203 */     if (sel1.numIds == 0) {
/*     */       
/* 205 */       BooleanValue staticResult = (BooleanValue)Evaluator.eval(sel1);
/* 206 */       if (!staticResult.booleanValue()) {
/* 207 */         return new Literal(BooleanValue.FALSE);
/*     */       }
/* 209 */       return evalOf(sel0);
/*     */     } 
/* 211 */     return new Operator(46, sel0, sel1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Selector evalOf(Selector arg) {
/* 219 */     if (arg.numIds > 0)
/* 220 */       return arg; 
/* 221 */     return new Literal(Evaluator.eval(arg));
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
/*     */   private static Selector simplifyTree(Selector tree) {
/*     */     LikeOperator loper;
/* 237 */     if (!(tree instanceof Operator))
/* 238 */       return tree; 
/* 239 */     Operator oper = (Operator)tree;
/* 240 */     switch (oper.op) {
/*     */       case 1:
/* 242 */         return simplifyNOT(oper.operands[0]);
/*     */       case 45:
/* 244 */         return simplifyEQ(oper.operands[0], oper.operands[1]);
/*     */       case 40:
/* 246 */         return simplifyNE(oper.operands[0], oper.operands[1]);
/*     */       case 2:
/*     */       case 3:
/*     */       case 5:
/* 250 */         return new Operator(oper.op, simplifyTree(oper.operands[0]));
/*     */       case 4:
/* 252 */         loper = (LikeOperator)oper;
/* 253 */         return new LikeOperator(simplifyTree(loper.operands[0]), loper.pattern, loper.escaped, loper.escape);
/*     */     } 
/*     */     
/* 256 */     return new Operator(oper.op, simplifyTree(oper.operands[0]), 
/* 257 */         simplifyTree(oper.operands[1]));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Selector simplifyNOT(Selector tree) {
/* 265 */     if (tree instanceof Literal)
/* 266 */       return new Literal(((BooleanValue)((Literal)tree).value).not()); 
/* 267 */     if (!(tree instanceof Operator))
/* 268 */       return new Operator(1, tree); 
/* 269 */     Operator oper = (Operator)tree;
/* 270 */     switch (oper.op) {
/*     */       case 1:
/* 272 */         return simplifyTree(oper.operands[0]);
/*     */       case 46:
/* 274 */         return makeOR(simplifyNOT(oper.operands[0]), 
/* 275 */             simplifyNOT(oper.operands[1]));
/*     */       case 47:
/* 277 */         return makeAND(simplifyNOT(oper.operands[0]), 
/* 278 */             simplifyNOT(oper.operands[1]));
/*     */       case 45:
/* 280 */         return simplifyNE(oper.operands[0], oper.operands[1]);
/*     */       case 40:
/* 282 */         return simplifyEQ(oper.operands[0], oper.operands[1]);
/*     */       case 41:
/* 284 */         return new Operator(44, oper.operands[0], oper.operands[1]);
/*     */       case 42:
/* 286 */         return new Operator(43, oper.operands[0], oper.operands[1]);
/*     */       case 43:
/* 288 */         return new Operator(42, oper.operands[0], oper.operands[1]);
/*     */       case 44:
/* 290 */         return new Operator(41, oper.operands[0], oper.operands[1]);
/*     */     } 
/* 292 */     return new Operator(1, tree);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Selector simplifyEQ(Selector sel0, Selector sel1) {
/* 300 */     if (sel0.type != -6) {
/* 301 */       return new Operator(45, sel0, sel1);
/*     */     }
/* 303 */     return makeOR(makeAND(simplifyTree(sel0), simplifyTree(sel1)), 
/* 304 */         makeAND(simplifyNOT(sel0), simplifyNOT(sel1)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Selector simplifyNE(Selector sel0, Selector sel1) {
/* 311 */     if (sel0.type == -6)
/* 312 */       return makeOR(makeAND(simplifyTree(sel0), simplifyNOT(sel1)), 
/* 313 */           makeAND(simplifyNOT(sel0), simplifyTree(sel1))); 
/* 314 */     if (sel0.type == -5 || sel0.type == 0) {
/* 315 */       return new Operator(40, sel0, sel1);
/*     */     }
/*     */     
/* 318 */     return makeOR(new Operator(42, sel0, sel1), new Operator(41, sel0, sel1));
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
/*     */   public static Conjunction[] organizeTests(Selector tree) {
/* 341 */     FastVector tests = new FastVector();
/* 342 */     if (organizeTests(tests, tree))
/* 343 */       return null; 
/* 344 */     Conjunction[] ans = new Conjunction[tests.m_count];
/* 345 */     System.arraycopy(tests.m_data, 0, ans, 0, tests.m_count);
/* 346 */     return ans;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean organizeTests(FastVector tests, Selector tree) {
/*     */     Conjunction conjunction;
/* 355 */     if (tree instanceof Operator) {
/* 356 */       Operator oper = (Operator)tree;
/* 357 */       if (oper.op == 47) {
/* 358 */         if (organizeTests(tests, oper.operands[0]) || 
/* 359 */           organizeTests(tests, oper.operands[1]))
/* 360 */           return true; 
/* 361 */         return false;
/*     */       } 
/* 363 */       if (oper.op == 46) {
/* 364 */         conjunction = organizeConjunction(oper);
/*     */       } else {
/* 366 */         conjunction = simpleConjunct(tree);
/*     */       } 
/*     */     } else {
/* 369 */       conjunction = simpleConjunct(tree);
/* 370 */     }  if (conjunction == null)
/*     */     {
/*     */       
/* 373 */       return false; } 
/* 374 */     if (conjunction.alwaysTrue)
/*     */     {
/*     */       
/* 377 */       return true;
/*     */     }
/*     */     
/* 380 */     if (conjunction.organize())
/* 381 */       tests.addElement(conjunction); 
/* 382 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Conjunction simpleConjunct(Selector tree) {
/* 392 */     if (tree.numIds == 0) {
/* 393 */       if (((BooleanValue)Evaluator.eval(tree)).booleanValue()) {
/* 394 */         return new Conjunction();
/*     */       }
/* 396 */       return null;
/* 397 */     }  if (tree.numIds == 1 && SimpleTest.isSimple(tree)) {
/* 398 */       return new Conjunction(new SimpleTest(tree));
/*     */     }
/* 400 */     return new Conjunction(tree);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Conjunction organizeConjunction(Operator andOper) {
/*     */     Conjunction conjunction;
/* 412 */     Selector left = andOper.operands[0];
/*     */     
/* 414 */     if (left instanceof Operator) {
/* 415 */       Operator oper = (Operator)left;
/* 416 */       if (oper.op == 46) {
/* 417 */         conjunction = organizeConjunction(oper);
/*     */       } else {
/* 419 */         conjunction = simpleConjunct(left);
/*     */       } 
/*     */     } else {
/* 422 */       conjunction = simpleConjunct(left);
/* 423 */     }  if (conjunction == null)
/* 424 */       return null; 
/* 425 */     return augmentConjunction(conjunction, andOper.operands[1]) ? conjunction : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean augmentConjunction(Conjunction target, Selector toAdd) {
/* 434 */     if (toAdd instanceof Operator) {
/* 435 */       Operator oper = (Operator)toAdd;
/* 436 */       if (oper.op == 46) {
/* 437 */         return (augmentConjunction(target, oper.operands[0]) && 
/* 438 */           augmentConjunction(target, oper.operands[1]));
/*     */       }
/*     */     } 
/* 441 */     if (toAdd.numIds == 0)
/*     */     {
/*     */       
/* 444 */       return ((BooleanValue)Evaluator.eval(toAdd)).booleanValue(); } 
/* 445 */     if (toAdd.numIds == 1 && SimpleTest.isSimple(toAdd)) {
/* 446 */       return target.and(new SimpleTest(toAdd));
/*     */     }
/* 448 */     target.and(toAdd);
/* 449 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\selector\Transformer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */