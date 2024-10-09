/*     */ package com.ibm.disthub2.impl.matching.selector;
/*     */ 
/*     */ import com.ibm.disthub2.impl.matching.MatchSpace;
/*     */ import java.io.UTFDataFormatException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Operator
/*     */   extends Selector
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   public int op;
/*     */   public Selector[] operands;
/*     */   private int hashcode;
/*     */   private boolean hashCodeCached;
/*     */   
/*     */   public Operator(int op, Selector op1) {
/*  61 */     this.op = op;
/*  62 */     this.operands = new Selector[] { op1 };
/*  63 */     assignType();
/*  64 */     if (this.type == 2)
/*     */       return; 
/*  66 */     this.numIds = op1.numIds;
/*  67 */     if (this.numIds == 0 && op1 instanceof Operator) {
/*  68 */       this.operands[0] = new Literal(Evaluator.eval(op1));
/*     */     }
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
/*     */   public Operator(int op, Selector op1, Selector op2) {
/*  83 */     this.op = op;
/*  84 */     this.operands = new Selector[] { op1, op2 };
/*  85 */     assignType();
/*  86 */     if (this.type == 2)
/*     */       return; 
/*  88 */     if (op1.numIds == 0 && op1 instanceof Operator)
/*  89 */       this.operands[0] = new Literal(Evaluator.eval(op1)); 
/*  90 */     if (op2.numIds == 0 && op2 instanceof Operator)
/*  91 */       this.operands[1] = new Literal(Evaluator.eval(op2)); 
/*  92 */     this.numIds = op1.numIds + op2.numIds;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Operator(byte[] buf, int offset) throws UTFDataFormatException {
/* 100 */     this.op = buf[offset++];
/* 101 */     this.operands = new Selector[(this.op >= 40) ? 2 : 1]; int i;
/* 102 */     for (i = 0; i < this.operands.length; i++) {
/* 103 */       this.operands[i] = Selector.decodeSubtree(buf, offset);
/* 104 */       offset += this.operands[i].length();
/*     */     } 
/* 106 */     assignType();
/* 107 */     if (this.type == 2)
/*     */       return; 
/* 109 */     for (i = 0; i < this.operands.length; i++) {
/* 110 */       Selector operand = this.operands[i];
/* 111 */       if (operand.numIds == 0 && operand instanceof Operator)
/* 112 */         this.operands[i] = new Literal(Evaluator.eval(operand)); 
/* 113 */       this.numIds += operand.numIds;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void assignType() {
/*     */     int type0, type1;
/* 125 */     switch (this.op) {
/*     */       case 1:
/* 127 */         this.type = this.operands[0].mayBeBoolean() ? -6 : 2;
/*     */         return;
/*     */       case 2:
/* 130 */         this.type = this.operands[0].mayBeNumeric() ? (this.operands[0]).type : 2;
/*     */         return;
/*     */       case 3:
/* 133 */         if (this.operands[0] instanceof Identifier) {
/* 134 */           this.type = -6;
/* 135 */         } else if (this.operands[0] instanceof Operator) {
/* 136 */           this.type = (((Operator)this.operands[0]).op == 54) ? -6 : 2;
/*     */         } else {
/* 138 */           this.type = 2;
/*     */         }  return;
/*     */       case 4:
/* 141 */         this.type = this.operands[0].mayBeString() ? -6 : 2;
/*     */         return;
/*     */       case 5:
/* 144 */         this.type = this.operands[0].mayBeList() ? -6 : 2;
/*     */         return;
/*     */       case 40:
/*     */       case 45:
/* 148 */         type0 = (this.operands[0]).type;
/* 149 */         type1 = (this.operands[1]).type;
/* 150 */         if (type0 >= -4 && type0 <= -1)
/* 151 */           type0 = 1; 
/* 152 */         if (type1 >= -4 && type1 <= -1)
/* 153 */           type1 = 1; 
/* 154 */         if (type0 == 0) {
/* 155 */           (this.operands[0]).type = type0 = type1;
/* 156 */         } else if (type1 == 0) {
/* 157 */           (this.operands[1]).type = type1 = type0;
/* 158 */         }  this.type = (type0 == type1) ? -6 : 2;
/*     */         return;
/*     */       case 41:
/*     */       case 42:
/*     */       case 43:
/*     */       case 44:
/* 164 */         this.type = (this.operands[0].mayBeNumeric() && this.operands[1].mayBeNumeric()) ? -6 : 2;
/* 165 */         if (this.type == 2 && ((this.operands[0]).type == -5 || (this.operands[1]).type == -5) && 
/* 166 */           MatchSpace.getPermissive() == true)
/*     */         {
/* 168 */           this.type = -6;
/*     */         }
/*     */         return;
/*     */       case 48:
/*     */       case 49:
/*     */       case 50:
/*     */       case 51:
/* 175 */         if (!this.operands[0].mayBeNumeric() || !this.operands[1].mayBeNumeric()) {
/* 176 */           this.type = 2;
/*     */ 
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */           
/* 183 */           this.type = ((this.operands[0]).type > (this.operands[1]).type) ? (this.operands[0]).type : (this.operands[1]).type;
/*     */         }  return;
/*     */       case 46:
/*     */       case 47:
/* 187 */         this.type = (this.operands[0].mayBeBoolean() && this.operands[1].mayBeBoolean()) ? -6 : 2;
/*     */         return;
/*     */       case 54:
/* 190 */         this.type = ((this.operands[0]).type == 3 && this.operands[1] instanceof Identifier) ? 0 : 2;
/*     */         return;
/*     */       
/*     */       case 52:
/* 194 */         this.type = (this.operands[0].mayBeList() && (this.operands[1]).type == -4) ? 3 : 2;
/*     */         return;
/*     */       case 53:
/* 197 */         this.type = (this.operands[0].mayBeList() && (this.operands[1]).type == -6) ? 4 : 2;
/*     */         return;
/*     */     } 
/* 200 */     this.type = 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int length() {
/* 211 */     int ans = 1;
/* 212 */     for (int i = 0; i < this.operands.length; i++)
/* 213 */       ans += this.operands[i].length(); 
/* 214 */     return ans;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int encode(byte[] buf, int offset) {
/* 221 */     buf[offset] = (byte)this.op;
/* 222 */     int len = 1;
/* 223 */     for (int i = 0; i < this.operands.length; i++)
/* 224 */       len += this.operands[i].encode(buf, offset + len); 
/* 225 */     return len;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Selector intern(Selector.InternTable table) {
/* 232 */     for (int i = 0; i < this.operands.length; i++)
/* 233 */       this.operands[i] = this.operands[i].intern(table); 
/* 234 */     return super.intern(table);
/*     */   }
/*     */   
/*     */   public void unintern(Selector.InternTable table) {
/* 238 */     for (int i = 0; i < this.operands.length; i++)
/* 239 */       this.operands[i].unintern(table); 
/* 240 */     super.unintern(table);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 247 */     if (o instanceof Operator && super.equals(o)) {
/* 248 */       Operator other = (Operator)o;
/* 249 */       if (this.op != other.op)
/* 250 */         return false; 
/* 251 */       for (int i = 0; i < this.operands.length; i++) {
/* 252 */         if (!this.operands[i].equals(other.operands[i]))
/* 253 */           return false; 
/* 254 */       }  return true;
/*     */     } 
/*     */     
/* 257 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 262 */     if (this.hashCodeCached)
/* 263 */       return this.hashcode; 
/* 264 */     int ans = this.op;
/* 265 */     for (int i = 0; i < this.operands.length; i++)
/* 266 */       ans = (ans << 9) + this.operands[i].hashCode(); 
/* 267 */     this.hashCodeCached = true;
/* 268 */     this.hashcode = ans;
/* 269 */     return ans;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() {
/* 276 */     Operator result = (Operator)super.clone();
/* 277 */     for (int i = 0; i < this.operands.length; i++)
/* 278 */       result.operands[i] = (Selector)this.operands[i].clone(); 
/* 279 */     return result;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 283 */     String stringOp = null;
/* 284 */     int prec = precedence(this);
/* 285 */     String op1 = this.operands[0].toString();
/* 286 */     if (precedence(this.operands[0]) > prec)
/* 287 */       op1 = "(" + op1 + ")"; 
/* 288 */     String op2 = null;
/* 289 */     if (this.operands.length > 1) {
/* 290 */       op2 = this.operands[1].toString();
/* 291 */       if (precedence(this.operands[1]) > prec)
/* 292 */         op2 = "(" + op2 + ")"; 
/*     */     } 
/* 294 */     switch (this.op) {
/*     */       case 1:
/* 296 */         return "NOT " + op1;
/*     */       case 2:
/* 298 */         return "-" + op1;
/*     */       case 3:
/* 300 */         return op1 + " IS NULL";
/*     */       case 5:
/* 302 */         return op1 + " IS EMPTY";
/*     */       case 40:
/* 304 */         stringOp = "<>";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 348 */         return op1 + stringOp + op2;case 41: stringOp = ">"; return op1 + stringOp + op2;case 42: stringOp = "<"; return op1 + stringOp + op2;case 43: stringOp = ">="; return op1 + stringOp + op2;case 44: stringOp = "<="; return op1 + stringOp + op2;case 45: stringOp = "="; return op1 + stringOp + op2;case 46: stringOp = " AND "; return op1 + stringOp + op2;case 47: stringOp = " OR "; return op1 + stringOp + op2;case 48: stringOp = "+"; return op1 + stringOp + op2;case 49: stringOp = "-"; return op1 + stringOp + op2;case 50: stringOp = "*"; return op1 + stringOp + op2;case 51: stringOp = "/"; return op1 + stringOp + op2;case 54: stringOp = "."; return op1 + stringOp + op2;
/*     */       case 52:
/*     */       case 53:
/*     */         return op1 + "[" + op2 + "]";
/*     */     } 
/*     */     throw new IllegalStateException();
/*     */   } private int precedence(Selector sel) {
/* 355 */     if (!(sel instanceof Operator))
/* 356 */       return 0; 
/* 357 */     Operator oper = (Operator)sel;
/* 358 */     if (oper.op < 40)
/* 359 */       return 0; 
/* 360 */     switch (oper.op) {
/*     */       case 52:
/*     */       case 53:
/*     */       case 54:
/* 364 */         return 0;
/*     */       case 50:
/*     */       case 51:
/* 367 */         return 1;
/*     */       case 48:
/*     */       case 49:
/* 370 */         return 2;
/*     */       case 40:
/*     */       case 41:
/*     */       case 42:
/*     */       case 43:
/*     */       case 44:
/*     */       case 45:
/* 377 */         return 3;
/*     */       case 46:
/* 379 */         return 4;
/*     */     } 
/* 381 */     return 5;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\selector\Operator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */