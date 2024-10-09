/*     */ package com.ibm.msg.client.commonservices.util;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import javax.lang.model.SourceVersion;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AllowList
/*     */ {
/*  54 */   final GeneralTree<String> allowList = new GeneralTree<>("*");
/*  55 */   final Map<String, Boolean> cachedLookups = new ConcurrentHashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AllowList() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public AllowList(BufferedReader r) throws IOException, IllegalArgumentException {
/*  65 */     this();
/*  66 */     load(r);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void load(BufferedReader r) throws IOException, IllegalArgumentException {
/*  76 */     String name = null;
/*  77 */     while ((name = r.readLine()) != null) {
/*  78 */       String[] splitName = name.split("#");
/*  79 */       if (splitName.length > 0) {
/*  80 */         name = splitName[0].trim();
/*  81 */         if (!name.isEmpty()) {
/*  82 */           addName(name);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AllowList(String[] classNames) throws IllegalArgumentException {
/*  94 */     this();
/*  95 */     load(classNames);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void load(String[] classNames) throws IllegalArgumentException {
/* 104 */     for (String className : classNames) {
/* 105 */       addName(className);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addName(String classNameP) throws IllegalArgumentException {
/* 115 */     String className = classNameP.trim();
/* 116 */     if (isGoodName(className)) {
/* 117 */       this.allowList.addSubElements(elementList(className));
/*     */     } else {
/*     */       
/* 120 */       throw new IllegalArgumentException(className);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isGoodName(String className) {
/* 131 */     if (SourceVersion.isName(className)) {
/* 132 */       return true;
/*     */     }
/*     */     
/* 135 */     if (className.endsWith(".*")) {
/* 136 */       String partName = className.substring(0, className.length() - 2);
/* 137 */       return SourceVersion.isName(partName);
/*     */     } 
/*     */     
/* 140 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean contains(String className) {
/* 149 */     Boolean cachedValue = this.cachedLookups.get(className);
/* 150 */     if (cachedValue == null) {
/* 151 */       cachedValue = Boolean.valueOf(this.allowList.contains(elementList(className)));
/* 152 */       this.cachedLookups.put(className, cachedValue);
/*     */     } 
/* 154 */     return cachedValue.booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<String> elementList(String className) {
/* 163 */     return Arrays.asList(className.trim().split("\\."));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dump(PrintStream out) {
/* 171 */     this.allowList.dump(out);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservice\\util\AllowList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */