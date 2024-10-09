/*     */ package com.ibm.disthub2.impl.util;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
/*     */ import com.ibm.disthub2.spi.ExceptionBuilder;
/*     */ import com.ibm.disthub2.spi.ExceptionConstants;
/*     */ import java.io.Serializable;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FastVector
/*     */   implements Cloneable, Serializable, ExceptionConstants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  77 */   private static final DebugObject debug = new DebugObject("FastVector");
/*     */   
/*     */   public Object[] m_data;
/*     */   
/*     */   public int m_count;
/*     */   
/*     */   public FastVector(int initSize) {
/*  84 */     this.m_data = new Object[initSize];
/*     */   }
/*     */   
/*     */   public FastVector() {
/*  88 */     this(8);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FastVector(Vector v) {
/*     */     try {
/*  96 */       this.m_data = new Object[v.size()];
/*  97 */       v.copyInto(this.m_data);
/*     */     }
/*  99 */     catch (IndexOutOfBoundsException indexOutOfBoundsException) {}
/*     */ 
/*     */ 
/*     */     
/* 103 */     this.m_count = v.size();
/*     */   }
/*     */   
/*     */   public void addElement(Object obj) {
/* 107 */     if (this.m_data.length == this.m_count) {
/* 108 */       Object[] newdata = new Object[this.m_data.length << 1];
/* 109 */       System.arraycopy(this.m_data, 0, newdata, 0, this.m_count);
/* 110 */       this.m_data = newdata;
/*     */     } 
/*     */     
/* 113 */     this.m_data[this.m_count++] = obj;
/*     */   }
/*     */ 
/*     */   
/*     */   public int addElementI(Object obj) {
/* 118 */     if (this.m_data.length == this.m_count) {
/* 119 */       Object[] newdata = new Object[this.m_data.length << 1];
/* 120 */       System.arraycopy(this.m_data, 0, newdata, 0, this.m_count);
/* 121 */       this.m_data = newdata;
/*     */     } 
/*     */     
/* 124 */     this.m_data[this.m_count] = obj;
/* 125 */     return this.m_count++;
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/* 129 */     Object[] a = new Object[this.m_count];
/* 130 */     System.arraycopy(this.m_data, 0, a, 0, this.m_count);
/* 131 */     return a;
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 135 */     FastVector v = null;
/*     */     try {
/* 137 */       v = (FastVector)super.clone();
/*     */     }
/* 139 */     catch (CloneNotSupportedException cloneNotSupportedException) {}
/* 140 */     v.m_data = new Object[this.m_count];
/* 141 */     System.arraycopy(this.m_data, 0, v.m_data, 0, this.m_count);
/* 142 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector makeVector() {
/* 149 */     Vector<Object> v = new Vector(this.m_count);
/*     */     try {
/* 151 */       for (int i = 0; i < this.m_count; i++) {
/* 152 */         v.addElement(this.m_data[i]);
/*     */       
/*     */       }
/*     */     }
/* 156 */     catch (IndexOutOfBoundsException e) {
/*     */       
/* 158 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 161 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TypeSafeVector makeTypeSafeVector(String className) {
/* 168 */     TypeSafeVector v = new TypeSafeVector(className, this.m_count);
/*     */     try {
/* 170 */       for (int i = 0; i < this.m_count; i++) {
/* 171 */         v.setElementAt(this.m_data[i], i);
/*     */       }
/*     */     }
/* 174 */     catch (IndexOutOfBoundsException indexOutOfBoundsException) {}
/*     */ 
/*     */     
/* 177 */     return v;
/*     */   }
/*     */   
/*     */   public void append(FastVector v) {
/* 181 */     if (v.m_count == 0) {
/*     */       return;
/*     */     }
/*     */     
/* 185 */     int newlength = (this.m_data.length > 0) ? this.m_data.length : 1;
/*     */     
/* 187 */     while (v.m_count + this.m_count >= newlength)
/*     */     {
/* 189 */       newlength <<= 1;
/*     */     }
/* 191 */     if (newlength != this.m_data.length) {
/* 192 */       Object[] newdata = new Object[newlength];
/* 193 */       System.arraycopy(this.m_data, 0, newdata, 0, this.m_count);
/* 194 */       this.m_data = newdata;
/*     */     } 
/* 196 */     System.arraycopy(v.m_data, 0, this.m_data, this.m_count, v.m_count);
/* 197 */     this.m_count += v.m_count;
/*     */   }
/*     */   
/*     */   public void removeElementAt(int i) {
/* 201 */     System.arraycopy(this.m_data, i + 1, this.m_data, i, this.m_count - i - 1);
/* 202 */     this.m_count--;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object dropElementAt(int i) {
/*     */     int im;
/* 211 */     this.m_data[i] = this.m_data[im = this.m_count - 1];
/* 212 */     this.m_data[im] = null;
/*     */     
/* 214 */     this.m_count = im;
/* 215 */     return this.m_data[i];
/*     */   }
/*     */   
/*     */   public void reset() {
/* 219 */     this.m_count = 0;
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
/*     */   public final int indexOf(Object o) {
/* 238 */     int result = -1;
/* 239 */     for (int i = 0; i < this.m_count; i++) {
/* 240 */       if (this.m_data[i].equals(o)) {
/* 241 */         result = i;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 246 */     return result;
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
/*     */   public final Object elementAt(int i) throws ArrayIndexOutOfBoundsException {
/* 267 */     if (i >= this.m_count) {
/* 268 */       throw new ArrayIndexOutOfBoundsException(ExceptionBuilder.buildReasonString(-851325932, new Object[] { Integer.valueOf(i) }));
/*     */     }
/*     */     
/* 271 */     return this.m_data[i];
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
/*     */   public final int size() {
/* 289 */     return this.m_count;
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
/*     */   public final boolean contains(Object o) {
/* 308 */     boolean result = (indexOf(o) != -1);
/*     */     
/* 310 */     return result;
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
/*     */   public final Enumeration elements() {
/* 325 */     return new FastVectorEnumeration(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public class FastVectorEnumeration
/*     */     implements Enumeration
/*     */   {
/*     */     FastVector v;
/*     */ 
/*     */ 
/*     */     
/* 338 */     int index = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public FastVectorEnumeration(FastVector vector) {
/* 352 */       this.v = (FastVector)vector.clone();
/*     */     }
/*     */     
/*     */     public boolean hasMoreElements() {
/* 356 */       return (this.index < this.v.m_count);
/*     */     }
/*     */     
/*     */     public Object nextElement() {
/* 360 */       Object o = this.v.elementAt(this.index);
/* 361 */       this.index++;
/* 362 */       return o;
/*     */     }
/*     */   }
/*     */   
/*     */   public String toString() {
/* 367 */     StringBuffer buf = new StringBuffer("[ ");
/* 368 */     if (this.m_count >= 1) {
/* 369 */       buf.append(this.m_data[0].toString());
/*     */     }
/* 371 */     for (int i = 1; i < this.m_count; i++) {
/* 372 */       buf.append(", " + this.m_data[i].toString());
/*     */     }
/* 374 */     buf.append(" ]");
/* 375 */     return buf.toString();
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\imp\\util\FastVector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */