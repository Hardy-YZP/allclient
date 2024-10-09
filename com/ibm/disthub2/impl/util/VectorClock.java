/*     */ package com.ibm.disthub2.impl.util;
/*     */ 
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class VectorClock
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  81 */   Hashtable vc = new Hashtable<>(4, 0.75F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StampPair insert(Long p) {
/*  89 */     StampPair sp = new StampPair(p.longValue(), 0L);
/*  90 */     this.vc.put(p, sp);
/*  91 */     return sp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StampPair insert(Long p, long t) {
/*  99 */     StampPair sp = new StampPair(p.longValue(), t);
/* 100 */     this.vc.put(p, sp);
/* 101 */     return sp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long get(Long p) {
/* 108 */     StampPair sp = (StampPair)this.vc.get(p);
/* 109 */     if (sp == null) {
/* 110 */       return 0L;
/*     */     }
/* 112 */     return sp.stamp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StampPair getStampPair(Long p) {
/* 121 */     StampPair sp = (StampPair)this.vc.get(p);
/* 122 */     return sp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StampPair ensureStampPair(Long p) {
/* 130 */     StampPair sp = (StampPair)this.vc.get(p);
/* 131 */     if (sp == null) {
/* 132 */       return insert(p);
/*     */     }
/* 134 */     return sp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long set(Long p, long t) {
/* 142 */     StampPair sp = (StampPair)this.vc.get(p);
/* 143 */     if (sp == null) {
/* 144 */       sp = new StampPair(p.longValue(), t);
/* 145 */       this.vc.put(p, sp);
/* 146 */       return 0L;
/*     */     } 
/* 148 */     long temp = sp.stamp;
/* 149 */     sp.stamp = t;
/* 150 */     return temp;
/*     */   }
/*     */ 
/*     */   
/*     */   public StampPair setAndReturnIfInserted(Long p, long t) {
/* 155 */     StampPair sp = (StampPair)this.vc.get(p);
/* 156 */     if (sp == null) {
/* 157 */       sp = new StampPair(p.longValue(), t);
/* 158 */       this.vc.put(p, sp);
/* 159 */       return sp;
/*     */     } 
/* 161 */     sp.stamp = t;
/* 162 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Enumeration allElements() {
/* 171 */     return this.vc.elements();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 178 */     return this.vc.size();
/*     */   }
/*     */   
/*     */   public VectorClock clonep() {
/* 182 */     VectorClock copy = new VectorClock();
/* 183 */     Enumeration<StampPair> e = allElements();
/* 184 */     while (e.hasMoreElements()) {
/* 185 */       StampPair sp = e.nextElement();
/* 186 */       copy.insert(Long.valueOf(sp.pid), sp.stamp);
/*     */     } 
/* 188 */     return copy;
/*     */   }
/*     */   
/*     */   public VectorClock getIncrementFromOld(VectorClock oldvc) {
/* 192 */     Enumeration<StampPair> e = allElements();
/* 193 */     VectorClock inc = new VectorClock();
/* 194 */     while (e.hasMoreElements()) {
/* 195 */       StampPair sp = e.nextElement();
/* 196 */       Long pid = Long.valueOf(sp.pid);
/* 197 */       if (sp.stamp > oldvc.get(pid))
/*     */       {
/* 199 */         inc.set(pid, sp.stamp);
/*     */       }
/*     */     } 
/* 202 */     return inc;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 206 */     Enumeration<StampPair> e = allElements();
/* 207 */     String s = "VC=[";
/* 208 */     while (e.hasMoreElements()) {
/* 209 */       StampPair sp = e.nextElement();
/* 210 */       s = s + "(" + sp.pid + "," + sp.stamp + "),";
/*     */     } 
/* 212 */     s = s + "]";
/* 213 */     return s;
/*     */   }
/*     */   
/*     */   public void write(DataOutputStream dos) throws IOException {
/* 217 */     dos.writeInt(size());
/* 218 */     Enumeration<StampPair> stamps = allElements();
/* 219 */     while (stamps.hasMoreElements()) {
/* 220 */       StampPair sp = stamps.nextElement();
/* 221 */       dos.writeLong(sp.pid);
/* 222 */       dos.writeLong(sp.stamp);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void read(DataInputStream dis) throws IOException {
/* 227 */     int numPairs = dis.readInt();
/*     */     
/* 229 */     for (int i = 0; i < numPairs; i++) {
/* 230 */       long p = dis.readLong();
/* 231 */       long t = dis.readLong();
/* 232 */       set(Long.valueOf(p), t);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\imp\\util\VectorClock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */