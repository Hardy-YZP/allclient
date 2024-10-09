/*     */ package com.ibm.disthub2.impl.util;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
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
/*     */ public class FeatureSet
/*     */   implements Cloneable
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  53 */   private static final DebugObject debug = new DebugObject("FeatureSet");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   Hashtable features = new Hashtable<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Enumeration features() {
/*  74 */     return this.features.keys();
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
/*     */   public Enumeration parameters(String feature) {
/*  87 */     if (this.features.containsKey(feature)) {
/*  88 */       return ((Hashtable)this.features.get(feature)).keys();
/*     */     }
/*  90 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void put(String feature) {
/* 100 */     if (!this.features.containsKey(feature)) {
/* 101 */       this.features.put(feature, new Hashtable<>());
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
/*     */   public synchronized void put(String feature, String param, String value) {
/* 117 */     put(feature);
/* 118 */     ((Hashtable<String, String>)this.features.get(feature)).put(param, value);
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
/*     */   public synchronized Hashtable get(String feature) {
/* 135 */     if (this.features.containsKey(feature)) {
/* 136 */       return (Hashtable)((Hashtable)this.features.get(feature)).clone();
/*     */     }
/* 138 */     return null;
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
/*     */   public synchronized String get(String feature, String param) {
/*     */     try {
/* 155 */       return (String)((Hashtable)this.features.get(feature)).get(param);
/* 156 */     } catch (NullPointerException e) {
/*     */       
/* 158 */       return null;
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
/*     */   public synchronized boolean equals(Object other) {
/* 175 */     synchronized (other) {
/*     */       
/* 177 */       FeatureSet O = (FeatureSet)other;
/*     */       
/* 179 */       if (O.features.size() != this.features.size()) {
/* 180 */         return false;
/*     */       }
/* 182 */       for (Enumeration<String> e1 = O.features.keys(); e1.hasMoreElements(); ) {
/* 183 */         String nextKey = e1.nextElement();
/*     */         
/* 185 */         if (!this.features.containsKey(nextKey)) {
/* 186 */           return false;
/*     */         }
/* 188 */         Hashtable lParams = (Hashtable)this.features.get(nextKey);
/* 189 */         Hashtable oParams = (Hashtable)O.features.get(nextKey);
/*     */         
/* 191 */         if (oParams.size() != lParams.size()) {
/* 192 */           return false;
/*     */         }
/* 194 */         for (Enumeration<String> e2 = oParams.keys(); e2.hasMoreElements(); ) {
/* 195 */           String nextParamKey = e2.nextElement();
/*     */           
/* 197 */           if (!oParams.get(nextParamKey).equals(lParams.get(nextParamKey))) {
/* 198 */             return false;
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 203 */       return true;
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
/*     */   public synchronized int hashCode() {
/* 220 */     int value = 0;
/*     */     
/* 222 */     for (Enumeration<E> e = this.features.keys(); e.hasMoreElements();) {
/* 223 */       value += e.nextElement().hashCode();
/*     */     }
/* 225 */     return value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized String toString() {
/* 234 */     StringBuffer result = new StringBuffer();
/*     */     
/* 236 */     for (Enumeration<String> e1 = this.features.keys(); e1.hasMoreElements(); ) {
/* 237 */       String nextKey = e1.nextElement();
/* 238 */       Hashtable params = (Hashtable)this.features.get(nextKey);
/* 239 */       result.append(nextKey).append("={");
/*     */       
/* 241 */       for (Enumeration<String> e2 = params.keys(); e2.hasMoreElements(); ) {
/* 242 */         String nextParamKey = e2.nextElement();
/*     */         
/* 244 */         result.append(nextParamKey).append("=").append(params.get(nextParamKey));
/* 245 */         if (e2.hasMoreElements()) {
/* 246 */           result.append(",");
/*     */         }
/*     */       } 
/* 249 */       result.append("}");
/*     */     } 
/*     */     
/* 252 */     return result.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized Object clone() {
/* 262 */     FeatureSet newF = new FeatureSet();
/* 263 */     for (Enumeration<String> e = features(); e.hasMoreElements(); ) {
/* 264 */       String next = e.nextElement();
/*     */       
/* 266 */       newF.features.put(next, get(next).clone());
/*     */     } 
/*     */     
/* 269 */     return newF;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\imp\\util\FeatureSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */