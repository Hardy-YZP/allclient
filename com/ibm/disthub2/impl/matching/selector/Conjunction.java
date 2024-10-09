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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Conjunction
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   public SimpleTest[] simpleTests;
/*     */   public Selector residual;
/*     */   public boolean alwaysTrue;
/*  70 */   private FastVector tmpSimpleTests = new FastVector();
/*  71 */   private FastVector tmpResidual = new FastVector();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Conjunction(SimpleTest simple) {
/*  80 */     this.tmpSimpleTests.addElement(simple);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Conjunction(Selector resid) {
/*  90 */     this.tmpResidual.addElement(resid);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Conjunction() {
/*  99 */     this.alwaysTrue = true;
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
/*     */   boolean and(SimpleTest newTest) {
/* 112 */     for (int i = 0; i < this.tmpSimpleTests.m_count; i++) {
/* 113 */       SimpleTest cand = (SimpleTest)this.tmpSimpleTests.m_data[i];
/* 114 */       if (cand.identifier.name.equals(newTest.identifier.name))
/* 115 */         return cand.combine(newTest); 
/*     */     } 
/* 117 */     this.tmpSimpleTests.addElement(newTest);
/* 118 */     this.alwaysTrue = false;
/* 119 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void and(Selector newResid) {
/* 129 */     this.tmpResidual.addElement(newResid);
/* 130 */     this.alwaysTrue = false;
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
/*     */   public boolean organize() {
/* 147 */     if (this.tmpResidual.m_count > 0) {
/* 148 */       FastVector[] equatedIds = findEquatedIdentifiers();
/* 149 */       while (equatedIds != null && this.tmpResidual.m_count > 0) {
/* 150 */         equatedIds = reduceResidual(equatedIds);
/* 151 */         if (equatedIds != null && equatedIds.length == 0)
/*     */         {
/* 153 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*     */     int i;
/*     */     
/* 161 */     for (i = 0; i < this.tmpSimpleTests.m_count; ) {
/* 162 */       SimpleTest cand = (SimpleTest)this.tmpSimpleTests.m_data[i];
/* 163 */       if (cand.kind == 3) {
/* 164 */         for (int j = 0; j < cand.tests.length; j++)
/* 165 */           this.tmpResidual.addElement(cand.tests[j]); 
/* 166 */         this.tmpSimpleTests.dropElementAt(i);
/*     */         continue;
/*     */       } 
/* 169 */       i++;
/*     */     } 
/*     */ 
/*     */     
/* 173 */     for (i = 0; i < this.tmpSimpleTests.m_count - 1; i++) {
/* 174 */       for (int j = i + 1; j < this.tmpSimpleTests.m_count; j++) {
/* 175 */         SimpleTest iTest = (SimpleTest)this.tmpSimpleTests.m_data[i];
/* 176 */         SimpleTest jTest = (SimpleTest)this.tmpSimpleTests.m_data[j];
/* 177 */         if (jTest.identifier.ordinalPosition < iTest.identifier.ordinalPosition) {
/*     */           
/* 179 */           this.tmpSimpleTests.m_data[j] = iTest;
/* 180 */           this.tmpSimpleTests.m_data[i] = jTest;
/*     */         }
/* 182 */         else if (jTest.identifier.ordinalPosition == iTest.identifier.ordinalPosition) {
/*     */           
/* 184 */           throw new IllegalStateException();
/*     */         } 
/*     */       } 
/* 187 */     }  this.simpleTests = new SimpleTest[this.tmpSimpleTests.m_count];
/* 188 */     System.arraycopy(this.tmpSimpleTests.m_data, 0, this.simpleTests, 0, this.tmpSimpleTests.m_count);
/* 189 */     this.tmpSimpleTests = null;
/* 190 */     for (i = 0; i < this.tmpResidual.m_count; i++) {
/* 191 */       if (this.residual == null)
/* 192 */       { this.residual = (Selector)this.tmpResidual.m_data[i]; }
/*     */       else
/* 194 */       { this.residual = new Operator(46, this.residual, (Selector)this.tmpResidual.m_data[i]); } 
/* 195 */     }  this.tmpResidual = null;
/* 196 */     this.alwaysTrue = (this.simpleTests.length == 0 && this.residual == null);
/* 197 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private FastVector[] findEquatedIdentifiers() {
/* 205 */     FastVector[] ans = null;
/* 206 */     for (int i = 0; i < this.tmpSimpleTests.m_count; i++) {
/* 207 */       SimpleTest cand = (SimpleTest)this.tmpSimpleTests.m_data[i];
/* 208 */       if (cand.kind == 4) {
/* 209 */         if (ans == null)
/* 210 */           ans = new FastVector[] { new FastVector(), new FastVector() }; 
/* 211 */         ans[0].addElement(cand.identifier.name);
/* 212 */         ans[1].addElement(null);
/*     */       } else {
/*     */         
/* 215 */         Object candValue = cand.getValue();
/* 216 */         if (candValue != null) {
/* 217 */           if (ans == null)
/* 218 */             ans = new FastVector[] { new FastVector(), new FastVector() }; 
/* 219 */           ans[0].addElement(cand.identifier.name);
/* 220 */           ans[1].addElement(candValue);
/*     */         } 
/*     */       } 
/*     */     } 
/* 224 */     return ans;
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
/*     */   private FastVector[] reduceResidual(FastVector[] equatedIds) {
/* 236 */     FastVector[] ans = null;
/* 237 */     for (int i = 0; i < this.tmpResidual.m_count; ) {
/* 238 */       Operator oper = substitute((Operator)this.tmpResidual.m_data[i], equatedIds);
/* 239 */       if (oper.numIds > 0 && !SimpleTest.isSimple(oper)) {
/*     */         
/* 241 */         this.tmpResidual.m_data[i++] = oper; continue;
/* 242 */       }  if (oper.numIds == 1) {
/*     */ 
/*     */         
/* 245 */         Selector trans = Transformer.DNF(oper);
/* 246 */         if (trans instanceof Operator && ((Operator)trans).op == 47) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 255 */           this.tmpResidual.m_data[i++] = oper;
/*     */           
/*     */           continue;
/*     */         } 
/*     */         
/* 260 */         SimpleTest newTest = new SimpleTest(trans);
/* 261 */         if (!and(newTest))
/* 262 */           return new FastVector[0]; 
/* 263 */         this.tmpResidual.dropElementAt(i);
/*     */         
/* 265 */         if (newTest.kind == 4) {
/* 266 */           if (ans == null)
/* 267 */             ans = new FastVector[] { new FastVector(), new FastVector() }; 
/* 268 */           ans[0].addElement(newTest.identifier.name);
/* 269 */           ans[1].addElement(null);
/*     */           continue;
/*     */         } 
/* 272 */         Object newTestValue = newTest.getValue();
/* 273 */         if (newTestValue != null) {
/* 274 */           if (ans == null)
/* 275 */             ans = new FastVector[] { new FastVector(), new FastVector() }; 
/* 276 */           ans[0].addElement(newTest.identifier.name);
/* 277 */           ans[1].addElement(newTestValue);
/*     */         } 
/*     */ 
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/* 284 */       if (!((BooleanValue)Evaluator.eval(oper)).booleanValue()) {
/* 285 */         return new FastVector[0];
/*     */       }
/* 287 */       this.tmpResidual.dropElementAt(i);
/*     */     } 
/*     */     
/* 290 */     return ans;
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
/*     */   private static Operator substitute(Operator oper, FastVector[] equatedIds) {
/* 303 */     Selector op1 = oper.operands[0];
/* 304 */     Selector op2 = (oper.operands.length == 1) ? null : oper.operands[1];
/* 305 */     if (op1 instanceof Identifier) {
/* 306 */       op1 = substitute((Identifier)op1, equatedIds);
/* 307 */     } else if (op1 instanceof Operator) {
/* 308 */       op1 = substitute((Operator)op1, equatedIds);
/* 309 */     }  if (op1 == null)
/* 310 */       return null; 
/* 311 */     if (op2 != null) {
/* 312 */       if (op2 instanceof Identifier) {
/* 313 */         op2 = substitute((Identifier)op2, equatedIds);
/* 314 */       } else if (op2 instanceof Operator) {
/* 315 */         op2 = substitute((Operator)op2, equatedIds);
/* 316 */       }  if (op2 == null)
/* 317 */         return null; 
/*     */     } 
/* 319 */     if (op1 == oper.operands[0] && (op2 == null || op2 == oper.operands[1]))
/* 320 */       return oper; 
/* 321 */     if (oper instanceof LikeOperator) {
/* 322 */       LikeOperator loper = (LikeOperator)oper;
/* 323 */       return new LikeOperator(op1, loper.pattern, loper.escaped, loper.escape);
/*     */     } 
/*     */     
/* 326 */     return (op2 == null) ? new Operator(oper.op, op1) : new Operator(oper.op, op1, op2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Selector substitute(Identifier id, FastVector[] equatedIds) {
/* 334 */     for (int i = 0; i < (equatedIds[0]).m_count; i++) {
/* 335 */       if (id.name.equals((equatedIds[0]).m_data[i]))
/* 336 */         return new Literal((equatedIds[1]).m_data[i]); 
/* 337 */     }  return id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 344 */     if (this.simpleTests == null)
/* 345 */       return "unknown"; 
/* 346 */     if (this.alwaysTrue)
/* 347 */       return "true"; 
/* 348 */     StringBuffer ans = new StringBuffer();
/* 349 */     String delim = "";
/* 350 */     for (int i = 0; i < this.simpleTests.length; i++) {
/* 351 */       ans.append(delim).append(this.simpleTests[i]);
/* 352 */       delim = " AND ";
/*     */     } 
/* 354 */     if (this.residual != null)
/* 355 */       ans.append(delim).append(this.residual); 
/* 356 */     return ans.toString();
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\selector\Conjunction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */