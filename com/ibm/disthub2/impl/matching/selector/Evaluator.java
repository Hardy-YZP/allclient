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
/*     */ public final class Evaluator
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   
/*     */   public static Object eval(Selector sel, EvalContext bind, boolean permissive) throws BadMessageFormatMatchingException {
/*  65 */     if (sel.type == 2) {
/*  66 */       throw new IllegalArgumentException(sel.toString());
/*     */     }
/*     */ 
/*     */     
/*  70 */     if (sel.uniqueId != 0) {
/*  71 */       Object object = bind.getExprValue(sel.uniqueId);
/*  72 */       if (object != null) {
/*  73 */         return object;
/*     */       }
/*     */     } 
/*  76 */     Object ans = evalInternal(sel, bind, permissive);
/*  77 */     if (sel.uniqueId != 0)
/*  78 */       bind.saveExprValue(sel.uniqueId, ans); 
/*  79 */     return ans;
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
/*     */   public static Object eval(Selector sel) {
/*     */     try {
/*  95 */       return eval(sel, EvalContext.DUMMY, false);
/*  96 */     } catch (BadMessageFormatMatchingException e) {
/*     */       
/*  98 */       throw new IllegalStateException();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Object evalInternal(Selector sel, EvalContext bind, boolean permissive) throws BadMessageFormatMatchingException {
/*     */     Selector op1;
/* 109 */     if (sel instanceof Literal) {
/* 110 */       return ((Literal)sel).value;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 116 */     if (sel instanceof Identifier) {
/* 117 */       Identifier id = (Identifier)sel;
/* 118 */       if (permissive && id.type != 0) {
/*     */ 
/*     */         
/* 121 */         Object idVal = bind.getIdentifierValue(id, true);
/* 122 */         if (idVal == null) {
/* 123 */           return null;
/*     */         }
/* 125 */         switch (id.type) {
/*     */           case 1:
/* 127 */             if (idVal instanceof NumericValue) {
/* 128 */               return idVal;
/*     */             }
/* 130 */             return castToNumber(idVal);
/*     */           case -5:
/* 132 */             return idVal.toString();
/*     */           case -6:
/* 134 */             if (idVal instanceof BooleanValue) {
/* 135 */               return idVal;
/*     */             }
/* 137 */             return castToBoolean(idVal);
/*     */         } 
/*     */         
/* 140 */         return null;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 145 */       return bind.getIdentifierValue(id, false);
/*     */     } 
/*     */ 
/*     */     
/* 149 */     Operator op = (Operator)sel;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 154 */     switch (op.op) {
/*     */       case 52:
/* 156 */         return evalIndex(op, bind, permissive);
/*     */ 
/*     */       
/*     */       case 53:
/* 160 */         return null;
/*     */       case 5:
/* 162 */         return evalIsEmpty(op.operands[0], bind, permissive);
/*     */       case 54:
/* 164 */         val0 = eval(op.operands[0], bind, false);
/* 165 */         op1 = op.operands[1];
/* 166 */         if (val0 instanceof EvalContext && op1 instanceof Identifier) {
/* 167 */           return ((EvalContext)val0).getIdentifierValue((Identifier)op1, permissive);
/*     */         }
/* 169 */         return null;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 174 */     Object val0 = eval(op.operands[0], bind, false);
/* 175 */     Object val1 = (op.operands.length == 1) ? null : eval(op.operands[1], bind, false);
/*     */ 
/*     */     
/* 178 */     switch (op.op) {
/*     */       case 1:
/* 180 */         if (val0 instanceof BooleanValue)
/* 181 */           return ((BooleanValue)val0).not(); 
/* 182 */         if (permissive && val0 != null) {
/* 183 */           return castToBoolean(val0).not();
/*     */         }
/* 185 */         return BooleanValue.NULL;
/*     */       case 2:
/* 187 */         if (!(val0 instanceof NumericValue))
/* 188 */           if (permissive && val0 != null) {
/* 189 */             val0 = castToNumber(val0);
/* 190 */             if (val0 == null) {
/* 191 */               return null;
/*     */             }
/*     */           } else {
/* 194 */             return null;
/*     */           }  
/* 196 */         return ((NumericValue)val0).neg();
/*     */       case 3:
/* 198 */         if (val0 instanceof BooleanValue) {
/* 199 */           return BooleanValue.valueOf((val0 == BooleanValue.NULL));
/*     */         }
/* 201 */         return BooleanValue.valueOf((val0 == null));
/*     */       case 4:
/* 203 */         if (!(val0 instanceof String))
/* 204 */           if (permissive && val0 != null) {
/* 205 */             val0 = val0.toString();
/*     */           } else {
/* 207 */             return BooleanValue.NULL;
/*     */           }  
/* 209 */         return evalPattern((LikeOperator)sel, (String)val0);
/*     */       case 40:
/* 211 */         return compare(val0, val1, false, permissive).not();
/*     */       case 45:
/* 213 */         return compare(val0, val1, false, permissive);
/*     */       case 41:
/* 215 */         return compare(val1, val0, true, permissive);
/*     */       case 42:
/* 217 */         return compare(val0, val1, true, permissive);
/*     */       case 43:
/* 219 */         return compare(val0, val1, true, permissive).not();
/*     */       case 44:
/* 221 */         return compare(val1, val0, true, permissive).not();
/*     */       case 48:
/*     */       case 49:
/*     */       case 50:
/*     */       case 51:
/* 226 */         return promoteAndEvaluate(op.op, val0, val1, permissive);
/*     */       case 46:
/*     */       case 47:
/* 229 */         if (!(val0 instanceof BooleanValue))
/*     */         {
/* 231 */           if (permissive && val0 != null)
/* 232 */             val0 = castToBoolean(val0); 
/*     */         }
/* 234 */         if (!(val1 instanceof BooleanValue))
/*     */         {
/* 236 */           if (permissive && val1 != null) {
/* 237 */             val1 = castToBoolean(val1);
/*     */           }
/*     */         }
/* 240 */         if (val0 == null)
/* 241 */           val0 = BooleanValue.NULL; 
/* 242 */         if (val1 == null) {
/* 243 */           val1 = BooleanValue.NULL;
/*     */         }
/* 245 */         if (op.op == 46) {
/* 246 */           return ((BooleanValue)val0).and((BooleanValue)val1);
/*     */         }
/* 248 */         return ((BooleanValue)val0).or((BooleanValue)val1);
/*     */     } 
/*     */     
/* 251 */     return BooleanValue.NULL;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static NumericValue castToNumber(Object val) {
/* 261 */     if (!(val instanceof String))
/* 262 */       return null; 
/* 263 */     String stringVal = (String)val;
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 268 */       switch (stringVal.charAt(stringVal.length() - 1)) {
/*     */         case 'L':
/*     */         case 'l':
/* 271 */           stringVal = stringVal.substring(0, stringVal.length() - 1);
/* 272 */           return new NumericValue(Long.decode(stringVal).longValue(), true);
/*     */         case 'F':
/*     */         case 'f':
/* 275 */           return new NumericValue(Float.parseFloat(stringVal));
/*     */         case 'D':
/*     */         case 'd':
/* 278 */           return new NumericValue(Double.parseDouble(stringVal));
/*     */       } 
/*     */       try {
/* 281 */         return new NumericValue(Long.parseLong(stringVal), false);
/* 282 */       } catch (NumberFormatException e) {
/*     */         try {
/* 284 */           return new NumericValue(Long.decode(stringVal).longValue(), false);
/* 285 */         } catch (NumberFormatException e2) {
/* 286 */           return new NumericValue(Double.parseDouble(stringVal));
/*     */         }
/*     */       
/*     */       } 
/* 290 */     } catch (NumberFormatException e) {
/* 291 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BooleanValue castToBoolean(Object val) {
/* 301 */     if (val instanceof BooleanValue)
/* 302 */       return (BooleanValue)val; 
/* 303 */     if (!(val instanceof String))
/* 304 */       return BooleanValue.NULL; 
/* 305 */     String stringVal = (String)val;
/* 306 */     if (stringVal.equalsIgnoreCase("true"))
/* 307 */       return BooleanValue.TRUE; 
/* 308 */     if (stringVal.equalsIgnoreCase("false")) {
/* 309 */       return BooleanValue.FALSE;
/*     */     }
/* 311 */     return BooleanValue.NULL;
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
/*     */   private static BooleanValue compare(Object val0, Object val1, boolean lessThan, boolean permissive) {
/*     */     int comp;
/* 324 */     if (val0 == BooleanValue.NULL || val1 == BooleanValue.NULL) {
/* 325 */       return BooleanValue.NULL;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 330 */     int ans0 = (val0 instanceof NumericValue) ? 0 : ((val0 instanceof BooleanValue) ? 1 : ((val0 instanceof String) ? 2 : 3));
/*     */     
/* 332 */     int ans1 = (val1 instanceof NumericValue) ? 0 : ((val1 instanceof BooleanValue) ? 1 : ((val1 instanceof String) ? 2 : 3));
/*     */     
/* 334 */     if (ans0 > 2 || ans1 > 2)
/* 335 */       return BooleanValue.NULL; 
/* 336 */     int category = ans0 * 3 + ans1;
/*     */ 
/*     */     
/* 339 */     if (permissive) {
/* 340 */       switch (category) {
/*     */         case 0:
/*     */         case 4:
/*     */         case 8:
/*     */           break;
/*     */         case 2:
/* 346 */           val1 = castToNumber(val1);
/* 347 */           if (val1 == null)
/* 348 */             return BooleanValue.NULL; 
/* 349 */           category = 0;
/*     */           break;
/*     */         case 5:
/* 352 */           val1 = castToBoolean(val1);
/* 353 */           if (val1 == null)
/* 354 */             return BooleanValue.NULL; 
/* 355 */           category = 4;
/*     */           break;
/*     */         case 6:
/* 358 */           val0 = castToNumber(val0);
/* 359 */           if (val0 == null)
/* 360 */             return BooleanValue.NULL; 
/* 361 */           category = 0;
/*     */           break;
/*     */         case 7:
/* 364 */           val0 = castToBoolean(val0);
/* 365 */           if (val0 == null)
/* 366 */             return BooleanValue.NULL; 
/* 367 */           category = 4;
/*     */           break;
/*     */         default:
/* 370 */           return BooleanValue.NULL;
/*     */       } 
/*     */     }
/* 373 */     switch (category) {
/*     */       
/*     */       case 0:
/* 376 */         comp = ((NumericValue)val0).compareTo(val1);
/* 377 */         if (lessThan) {
/* 378 */           return BooleanValue.valueOf((comp < 0));
/*     */         }
/* 380 */         return BooleanValue.valueOf((comp == 0));
/*     */       case 4:
/* 382 */         if (lessThan) {
/* 383 */           return BooleanValue.NULL;
/*     */         }
/*     */         
/* 386 */         return BooleanValue.valueOf((val0 == val1));
/*     */       case 8:
/* 388 */         if (lessThan) {
/* 389 */           return BooleanValue.NULL;
/*     */         }
/* 391 */         return BooleanValue.valueOf(val0.equals(val1));
/*     */     } 
/* 393 */     return BooleanValue.NULL;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Object promoteAndEvaluate(int op, Object val0, Object val1, boolean permissive) {
/* 403 */     if (!(val0 instanceof NumericValue))
/* 404 */       if (permissive && val0 != null) {
/* 405 */         val0 = castToNumber(val0);
/*     */       } else {
/* 407 */         return null;
/* 408 */       }   if (!(val1 instanceof NumericValue))
/* 409 */       if (permissive && val1 != null) {
/* 410 */         val1 = castToNumber(val1);
/*     */       } else {
/* 412 */         return null;
/* 413 */       }   if (val0 == null || val1 == null)
/*     */     {
/* 415 */       return null;
/*     */     }
/*     */     
/* 418 */     switch (op) {
/*     */       case 48:
/* 420 */         return ((NumericValue)val0).plus((NumericValue)val1);
/*     */       case 49:
/* 422 */         return ((NumericValue)val0).minus((NumericValue)val1);
/*     */       case 50:
/* 424 */         return ((NumericValue)val0).times((NumericValue)val1);
/*     */       case 51:
/* 426 */         return ((NumericValue)val0).div((NumericValue)val1);
/*     */     } 
/*     */     
/* 429 */     throw new IllegalStateException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static EvalContext evalIndex(Operator op, EvalContext bind, boolean permissive) throws BadMessageFormatMatchingException {
/*     */     int index;
/* 440 */     Object inxObj = eval(op.operands[1], bind, permissive);
/* 441 */     if (inxObj instanceof NumericValue) {
/* 442 */       index = ((NumericValue)inxObj).intValue();
/*     */     } else {
/* 444 */       return null;
/* 445 */     }  Selector list = op.operands[0];
/*     */ 
/*     */ 
/*     */     
/* 449 */     if (list instanceof Identifier || (list instanceof Operator && ((Operator)list).op == 54)) {
/*     */       
/* 451 */       Object elist = eval(list, bind, permissive);
/* 452 */       if (elist instanceof EvalContextList) {
/* 453 */         return ((EvalContextList)elist).index(index);
/*     */       }
/* 455 */       return null;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 461 */     if (list instanceof Operator && ((Operator)list).op == 53) {
/* 462 */       return evalSelect((Operator)list, bind, permissive, index);
/*     */     }
/* 464 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static BooleanValue evalIsEmpty(Selector list, EvalContext bind, boolean permissive) throws BadMessageFormatMatchingException {
/* 474 */     if (list instanceof Identifier || (list instanceof Operator && ((Operator)list).op == 54)) {
/*     */       
/* 476 */       Object elist = eval(list, bind, permissive);
/* 477 */       if (elist instanceof EvalContextList) {
/* 478 */         return BooleanValue.valueOf(((EvalContextList)elist).isEmpty());
/*     */       }
/* 480 */       return null;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 486 */     if (list instanceof Operator && ((Operator)list).op == 53) {
/* 487 */       return BooleanValue.valueOf((evalSelect((Operator)list, bind, permissive, 0) == null));
/*     */     }
/* 489 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static EvalContext evalSelect(Operator selOper, EvalContext bind, boolean permissive, int index) throws BadMessageFormatMatchingException {
/* 497 */     Object list = eval(selOper.operands[0], bind, permissive);
/* 498 */     if (!(list instanceof EvalContextList))
/* 499 */       return null; 
/* 500 */     EvalContextList elist = (EvalContextList)list;
/* 501 */     int counter = 0;
/* 502 */     for (EvalContext ans = elist.successor(null); ans != null; ans = elist.successor(ans)) {
/* 503 */       if (((BooleanValue)eval(selOper.operands[1], ans, permissive)).booleanValue())
/* 504 */       { if (counter == index) {
/* 505 */           return ans;
/*     */         }
/* 507 */         counter++; } 
/* 508 */     }  return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Object evalPattern(LikeOperator pattern, String string) {
/*     */     try {
/* 519 */       return 
/* 520 */         BooleanValue.valueOf(PatternTool.match(pattern.pattern, string, pattern.escaped ? pattern.escape : Character.MIN_VALUE, new int[2]));
/*     */ 
/*     */     
/*     */     }
/* 524 */     catch (Exception e) {
/* 525 */       return BooleanValue.NULL;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\selector\Evaluator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */