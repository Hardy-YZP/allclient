/*     */ package com.ibm.disthub2.impl.matching.selector;
/*     */ 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Selector
/*     */   implements Cloneable
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   public static final int VERSION = 1;
/*     */   public static final int DOUBLE = -1;
/*     */   public static final int FLOAT = -2;
/*     */   public static final int LONG = -3;
/*     */   public static final int INT = -4;
/*     */   public static final int STRING = -5;
/*     */   public static final int BOOLEAN = -6;
/*     */   public static final int IDENTIFIER = 0;
/*     */   public static final int NOT = 1;
/*     */   public static final int NEG = 2;
/*     */   public static final int ISNULL = 3;
/*     */   public static final int LIKE = 4;
/*     */   public static final int ISEMPTY = 5;
/*     */   static final int FIRST_BINARY = 40;
/*     */   public static final int NE = 40;
/*     */   public static final int GT = 41;
/*     */   public static final int LT = 42;
/*     */   public static final int GE = 43;
/*     */   public static final int LE = 44;
/*     */   public static final int EQ = 45;
/*     */   public static final int AND = 46;
/*     */   public static final int OR = 47;
/*     */   public static final int PLUS = 48;
/*     */   public static final int MINUS = 49;
/*     */   public static final int TIMES = 50;
/*     */   public static final int DIV = 51;
/*     */   public static final int INDEX = 52;
/*     */   public static final int SELECT = 53;
/*     */   public static final int FIELD = 54;
/*     */   public static final int UNKNOWN = 0;
/*     */   public static final int NUMERIC = 1;
/*     */   public static final int INVALID = 2;
/*     */   public static final int TUPLE = 3;
/*     */   public static final int LIST = 4;
/*     */   public int type;
/*     */   public int uniqueId;
/*     */   private int refCount;
/*     */   public int numIds;
/*     */   
/*     */   public boolean mayBeBoolean() {
/* 257 */     if (this.type == 0) this.type = -6; 
/* 258 */     return (this.type == -6);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean mayBeString() {
/* 267 */     if (this.type == 0) this.type = -5; 
/* 268 */     return (this.type == -5);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean mayBeNumeric() {
/* 277 */     if (this.type == 0) this.type = 1; 
/* 278 */     return (this.type == 1 || (this.type >= -4 && this.type <= -1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean mayBeList() {
/* 287 */     if (this.type == 0) this.type = 4; 
/* 288 */     return (this.type == 4);
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
/*     */   public Selector intern(InternTable table) {
/* 307 */     Selector me = (Selector)table.get(this);
/* 308 */     if (me != null) {
/* 309 */       me.refCount++;
/* 310 */       return me;
/*     */     } 
/* 312 */     this.uniqueId = table.getNextUniqueId();
/* 313 */     this.refCount++;
/* 314 */     table.put(this, this);
/* 315 */     return this;
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
/*     */   public void unintern(InternTable table) {
/* 328 */     this.refCount--;
/* 329 */     if (this.refCount < 0) throw new IllegalStateException(); 
/* 330 */     if (this.refCount == 0) {
/* 331 */       Object res = table.remove(this);
/* 332 */       if (res == null) throw new IllegalStateException();
/*     */     
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Selector decode(byte[] buf, int offset) {
/* 360 */     if (buf[offset++] != 1)
/* 361 */       throw new IllegalArgumentException(); 
/*     */     try {
/* 363 */       return decodeSubtree(buf, offset);
/* 364 */     } catch (Exception e) {
/* 365 */       throw new IllegalArgumentException();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Selector decodeSubtree(byte[] buf, int offset) throws UTFDataFormatException {
/* 374 */     int type = buf[offset];
/* 375 */     if (type < 0)
/* 376 */       return new Literal(buf, offset); 
/* 377 */     if (type == 0)
/* 378 */       return new Identifier(buf, offset); 
/* 379 */     if (type == 4) {
/* 380 */       return new LikeOperator(buf, offset);
/*     */     }
/* 382 */     return new Operator(buf, offset);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] encode() {
/* 389 */     byte[] buf = new byte[length() + 1];
/* 390 */     buf[0] = 1;
/* 391 */     encode(buf, 1);
/* 392 */     return buf;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   abstract int length();
/*     */ 
/*     */ 
/*     */   
/*     */   abstract int encode(byte[] paramArrayOfbyte, int paramInt);
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 405 */     if (o instanceof Selector) {
/* 406 */       Selector s = (Selector)o;
/* 407 */       return (this.type == s.type && this.numIds == s.numIds);
/*     */     } 
/* 409 */     return false;
/*     */   }
/*     */   
/*     */   public Object clone() {
/*     */     try {
/* 414 */       return super.clone();
/* 415 */     } catch (CloneNotSupportedException e) {
/*     */       
/* 417 */       throw new IllegalStateException();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static interface InternTable {
/*     */     Object get(Object param1Object);
/*     */     
/*     */     Object put(Object param1Object1, Object param1Object2);
/*     */     
/*     */     Object remove(Object param1Object);
/*     */     
/*     */     int size();
/*     */     
/*     */     int getNextUniqueId();
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\selector\Selector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */