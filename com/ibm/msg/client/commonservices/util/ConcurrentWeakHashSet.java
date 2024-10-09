/*     */ package com.ibm.msg.client.commonservices.util;
/*     */ 
/*     */ import java.lang.ref.Reference;
/*     */ import java.lang.ref.ReferenceQueue;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ConcurrentWeakHashSet<T>
/*     */   implements Set<T>
/*     */ {
/*  27 */   Map<WeakReference<T>, Object> underlying = new ConcurrentHashMap<>();
/*     */   
/*  29 */   Object dummyEntry = new Object();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  35 */   ReferenceQueue<? super T> refQueue = new ReferenceQueue<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean add(T key) {
/*  43 */     boolean result = (this.underlying.put(new WeakReference<>(key, this.refQueue), this.dummyEntry) == null);
/*  44 */     cleanup();
/*  45 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean remove(Object key) {
/*  55 */     boolean result = (this.underlying.remove(new WeakReference(key)) != null);
/*  56 */     cleanup();
/*  57 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/*  65 */     cleanup();
/*  66 */     return this.underlying.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator<T> iterator() {
/*  74 */     cleanup();
/*  75 */     return new Iterator<T>()
/*     */       {
/*  77 */         Iterator<WeakReference<T>> i = ConcurrentWeakHashSet.this.underlying.keySet().iterator();
/*     */ 
/*     */         
/*     */         public boolean hasNext() {
/*  81 */           return this.i.hasNext();
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         public T next() {
/*  89 */           return ((WeakReference<T>)this.i.next()).get();
/*     */         }
/*     */ 
/*     */         
/*     */         public void remove() {
/*  94 */           this.i.remove();
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void cleanup() {
/*     */     Reference<?> ref;
/* 104 */     while ((ref = this.refQueue.poll()) != null) {
/* 105 */       this.underlying.remove(ref);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 114 */     cleanup();
/* 115 */     return this.underlying.isEmpty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean contains(Object o) {
/* 124 */     boolean result = this.underlying.containsKey(new WeakReference(o));
/* 125 */     cleanup();
/* 126 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object[] toArray() {
/* 135 */     return toArray((Object[])null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T2> T2[] toArray(T2[] a) {
/* 145 */     cleanup();
/* 146 */     ArrayList<T> result = new ArrayList<>(this.underlying.size());
/* 147 */     for (WeakReference<T> entry : this.underlying.keySet()) {
/* 148 */       T value = entry.get();
/* 149 */       if (value != null) {
/* 150 */         result.add(value);
/*     */       }
/*     */     } 
/* 153 */     return (T2[])result.toArray();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsAll(Collection<?> c) {
/* 161 */     for (Object o : c) {
/* 162 */       if (!contains(o)) {
/* 163 */         return false;
/*     */       }
/*     */     } 
/* 166 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addAll(Collection<? extends T> c) {
/* 175 */     boolean result = false;
/* 176 */     for (T o : c) {
/* 177 */       if (add(o)) {
/* 178 */         result = true;
/*     */       }
/*     */     } 
/* 181 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean retainAll(Collection<?> c) {
/* 189 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removeAll(Collection<?> c) {
/* 197 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 205 */     this.underlying.clear();
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservice\\util\ConcurrentWeakHashSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */