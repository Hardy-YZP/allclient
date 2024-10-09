/*     */ package com.ibm.disthub2.impl.util;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
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
/*     */ public final class TypeSafeVector
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  53 */   private static final DebugObject debug = new DebugObject("TypeSafeVector");
/*     */   
/*     */   Class m_class;
/*     */   
/*     */   Vector m_impl;
/*     */   
/*     */   public TypeSafeVector(String className, int initialCapacity, int capacityIncrement) {
/*     */     try {
/*  61 */       this.m_class = Class.forName(className);
/*     */     }
/*  63 */     catch (Exception e) {
/*  64 */       Assert.failure(e);
/*     */     } 
/*  66 */     this.m_impl = new Vector(initialCapacity, capacityIncrement);
/*     */   }
/*     */   
/*     */   public TypeSafeVector(String className, int initialCapacity) {
/*     */     try {
/*  71 */       this.m_class = Class.forName(className);
/*     */     }
/*  73 */     catch (Exception e) {
/*  74 */       Assert.failure(e);
/*     */     } 
/*  76 */     this.m_impl = new Vector(initialCapacity);
/*     */   }
/*     */   
/*     */   public TypeSafeVector(String className) {
/*     */     try {
/*  81 */       this.m_class = Class.forName(className);
/*     */     }
/*  83 */     catch (Exception e) {
/*  84 */       Assert.failure(e);
/*     */     } 
/*  86 */     this.m_impl = new Vector();
/*     */   }
/*     */   
/*     */   public void copyInto(Object[] anArray) {
/*  90 */     this.m_impl.copyInto(anArray);
/*     */   }
/*     */   
/*     */   public void trimToSize() {
/*  94 */     this.m_impl.trimToSize();
/*     */   }
/*     */   
/*     */   public void ensureCapacity(int minCapacity) {
/*  98 */     this.m_impl.ensureCapacity(minCapacity);
/*     */   }
/*     */   
/*     */   public void setSize(int newSize) {
/* 102 */     this.m_impl.setSize(newSize);
/*     */   }
/*     */   
/*     */   public int capacity() {
/* 106 */     return this.m_impl.capacity();
/*     */   }
/*     */   
/*     */   public int size() {
/* 110 */     return this.m_impl.size();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 114 */     return this.m_impl.isEmpty();
/*     */   }
/*     */   
/*     */   public Enumeration elements() {
/* 118 */     return this.m_impl.elements();
/*     */   }
/*     */   
/*     */   public boolean contains(Object elem) {
/* 122 */     Assert.condition(this.m_class.isInstance(elem));
/* 123 */     return this.m_impl.contains(elem);
/*     */   }
/*     */   
/*     */   public int indexOf(Object elem) {
/* 127 */     Assert.condition(this.m_class.isInstance(elem));
/* 128 */     return this.m_impl.indexOf(elem);
/*     */   }
/*     */   
/*     */   public int indexOf(Object elem, int index) {
/* 132 */     Assert.condition(this.m_class.isInstance(elem));
/* 133 */     return this.m_impl.indexOf(elem, index);
/*     */   }
/*     */   
/*     */   public int lastIndexOf(Object elem) {
/* 137 */     Assert.condition(this.m_class.isInstance(elem));
/* 138 */     return this.m_impl.lastIndexOf(elem);
/*     */   }
/*     */   
/*     */   public int lastIndexOf(Object elem, int index) {
/* 142 */     Assert.condition(this.m_class.isInstance(elem));
/* 143 */     return this.m_impl.lastIndexOf(elem, index);
/*     */   }
/*     */   
/*     */   public Object elementAt(int index) {
/* 147 */     return this.m_impl.elementAt(index);
/*     */   }
/*     */   
/*     */   public Object firstElement() {
/* 151 */     return this.m_impl.firstElement();
/*     */   }
/*     */   
/*     */   public Object lastElement() {
/* 155 */     return this.m_impl.lastElement();
/*     */   }
/*     */   
/*     */   public void setElementAt(Object elem, int index) {
/* 159 */     Assert.condition(this.m_class.isInstance(elem));
/* 160 */     this.m_impl.setElementAt(elem, index);
/*     */   }
/*     */   
/*     */   public void removeElementAt(int index) {
/* 164 */     this.m_impl.removeElementAt(index);
/*     */   }
/*     */   
/*     */   public void insertElementAt(Object elem, int index) {
/* 168 */     Assert.condition(this.m_class.isInstance(elem));
/* 169 */     this.m_impl.insertElementAt(elem, index);
/*     */   }
/*     */   
/*     */   public void addElement(Object elem) {
/* 173 */     Assert.condition(this.m_class.isInstance(elem));
/* 174 */     this.m_impl.addElement(elem);
/*     */   }
/*     */   
/*     */   public boolean removeElement(Object elem) {
/* 178 */     Assert.condition(this.m_class.isInstance(elem));
/* 179 */     return this.m_impl.removeElement(elem);
/*     */   }
/*     */   
/*     */   public void removeAllElements() {
/* 183 */     this.m_impl.removeAllElements();
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 187 */     return this.m_impl.clone();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 191 */     return this.m_impl.toString();
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\imp\\util\TypeSafeVector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */