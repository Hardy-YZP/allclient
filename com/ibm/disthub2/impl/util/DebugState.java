/*     */ package com.ibm.disthub2.impl.util;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.BaseConfig;
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
/*     */ import com.ibm.disthub2.spi.DebugHandle;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import java.util.StringTokenizer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DebugState
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  54 */   private static Hashtable debug_tab = new Hashtable<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   private static Hashtable debugRegistry = new Hashtable<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int register(DebugHandle R) {
/*  73 */     return registerPrivate(R);
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
/*     */   public static int register(DebugObject R) {
/*  86 */     return registerPrivate(R);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int registerPrivate(Object R) {
/*  97 */     synchronized (debugRegistry) {
/*  98 */       String debugName = getFirstToken(R.toString());
/*     */ 
/*     */       
/* 101 */       if (!debugRegistry.containsKey(debugName))
/* 102 */         debugRegistry.put(debugName, new Hashtable<>()); 
/* 103 */       ((Hashtable<Object, Object>)debugRegistry.get(debugName)).put(R, R);
/*     */ 
/*     */       
/* 106 */       if (debug_tab.containsKey(debugName) || debug_tab.containsKey("*")) {
/* 107 */         return (BaseConfig.getBaseConfig()).DEBUG_LEVEL;
/*     */       }
/* 109 */       return 0;
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
/*     */   public static void deregister(DebugHandle R) {
/* 122 */     deregisterPrivate(R);
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
/*     */   public static void deregister(DebugObject R) {
/* 134 */     deregisterPrivate(R);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void deregisterPrivate(Object R) {
/* 145 */     synchronized (debugRegistry) {
/* 146 */       String debugName = getFirstToken(R.toString());
/*     */       
/* 148 */       if (debugRegistry.containsKey(debugName)) {
/* 149 */         Hashtable table = (Hashtable)debugRegistry.get(debugName);
/* 150 */         table.remove(R);
/* 151 */         if (table.size() == 0) {
/* 152 */           debugRegistry.remove(debugName);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized void update() {
/* 162 */     synchronized (debugRegistry) {
/*     */       
/* 164 */       setAllMask(0);
/*     */ 
/*     */       
/* 167 */       String names = (BaseConfig.getBaseConfig()).DEBUG_NAME;
/* 168 */       Assert.condition((names != null));
/*     */       
/* 170 */       debug_tab = new Hashtable<>();
/* 171 */       if (names.length() > 0) {
/* 172 */         StringTokenizer stz = new StringTokenizer(names, ";");
/* 173 */         while (stz.hasMoreTokens()) {
/* 174 */           String entry = stz.nextToken();
/* 175 */           debug_tab.put(entry, entry);
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 181 */       setAllMask((BaseConfig.getBaseConfig()).DEBUG_LEVEL);
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
/*     */   private static void setAllMask(int mask) {
/* 193 */     if (debug_tab.containsKey("*")) {
/* 194 */       for (Enumeration e = debugRegistry.keys(); e.hasMoreElements();) {
/* 195 */         setElementsMask((Hashtable)debugRegistry.get(e.nextElement()), mask);
/*     */       }
/*     */     } else {
/*     */       
/* 199 */       for (Enumeration e = debug_tab.keys(); e.hasMoreElements(); ) {
/* 200 */         Object nextKey = e.nextElement();
/* 201 */         if (debugRegistry.containsKey(nextKey)) {
/* 202 */           setElementsMask((Hashtable)debugRegistry.get(nextKey), mask);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void setElementsMask(Hashtable h, int mask) {
/* 212 */     for (Enumeration e = h.elements(); e.hasMoreElements(); ) {
/* 213 */       Object next = e.nextElement();
/* 214 */       if (next instanceof DebugHandle) {
/* 215 */         ((DebugHandle)next).setMask(mask); continue;
/* 216 */       }  if (next instanceof DebugObject) {
/* 217 */         ((DebugObject)next).setMask(mask); continue;
/*     */       } 
/* 219 */       Assert.condition(false);
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
/*     */   private static String getFirstToken(String str) {
/*     */     int cutoff;
/* 234 */     if ((cutoff = str.indexOf(' ')) < 0)
/*     */     {
/* 236 */       return str;
/*     */     }
/* 238 */     return str.substring(0, cutoff);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\imp\\util\DebugState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */