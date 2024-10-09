/*     */ package com.ibm.mq.ese.core;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AlgorithmHelper
/*     */   implements MessageProtectionConstants
/*     */ {
/*  40 */   static Map<String, String> nameToOid = new HashMap<>();
/*  41 */   static Map<String, String> oidToName = new HashMap<>();
/*     */   
/*     */   static {
/*  44 */     Class<MessageProtectionConstants> mpcClass = MessageProtectionConstants.class;
/*  45 */     Field[] allFields = mpcClass.getFields();
/*  46 */     for (Field f : allFields) {
/*  47 */       String fName = f.getName();
/*  48 */       if (fName.endsWith("_CBC_OID")) {
/*  49 */         Field oidField = f;
/*     */         try {
/*  51 */           Field nameField = mpcClass.getField(fName.substring(0, fName.length() - 8));
/*  52 */           registerMapping(nameField, oidField);
/*     */         }
/*  54 */         catch (NoSuchFieldException|SecurityException noSuchFieldException) {}
/*     */ 
/*     */       
/*     */       }
/*  58 */       else if (fName.endsWith("_OID")) {
/*  59 */         Field oidField = f;
/*     */         try {
/*  61 */           Field nameField = mpcClass.getField(fName.substring(0, fName.length() - 4));
/*  62 */           registerMapping(nameField, oidField);
/*     */         }
/*  64 */         catch (NoSuchFieldException|SecurityException noSuchFieldException) {}
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void registerMapping(Field nameField, Field oidField) {
/*     */     try {
/*  75 */       String name = (String)nameField.get(null);
/*  76 */       String oid = (String)oidField.get(null);
/*  77 */       nameToOid.put(name, oid);
/*  78 */       oidToName.put(oid, name);
/*     */     }
/*  80 */     catch (IllegalAccessException|IllegalArgumentException illegalAccessException) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getAlgorithmName(String oid) {
/*  91 */     String name = oidToName.get(oid);
/*  92 */     if (name == null)
/*     */     {
/*  94 */       if (nameToOid.containsKey(oid)) {
/*  95 */         name = oid;
/*     */       }
/*     */     }
/*  98 */     return name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getAlgorithmOID(String name) {
/* 106 */     String oid = nameToOid.get(name);
/* 107 */     if (oid == null)
/*     */     {
/* 109 */       if (oidToName.containsKey(name)) {
/* 110 */         oid = name;
/*     */       }
/*     */     }
/* 113 */     return oid;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\core\AlgorithmHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */