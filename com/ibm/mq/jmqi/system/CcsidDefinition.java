/*     */ package com.ibm.mq.jmqi.system;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CcsidDefinition
/*     */ {
/*     */   private static final String VALUE_DELIMITER = ",";
/*     */   private static final String FIELD_DELIMITER = "|";
/*     */   private String javaName;
/*  23 */   private Set<Integer> ccsids = new TreeSet<>();
/*  24 */   private Set<String> aliasNames = new TreeSet<>();
/*     */ 
/*     */   
/*     */   private boolean isAscii;
/*     */ 
/*     */   
/*     */   private boolean is16Bit;
/*     */ 
/*     */   
/*     */   public CcsidDefinition(String javaName, boolean isAscii) {
/*  34 */     this.javaName = javaName;
/*  35 */     this.isAscii = isAscii;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addCcsid(int ccsid) {
/*  42 */     this.ccsids.add(Integer.valueOf(ccsid));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAlias(String aliasName) {
/*  49 */     this.aliasNames.add(aliasName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] aliases() {
/*  56 */     return this.aliasNames.<String>toArray(new String[this.aliasNames.size()]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] ccsids() {
/*  63 */     int[] result = new int[this.ccsids.size()];
/*  64 */     int i = 0;
/*  65 */     for (Iterator<Integer> iterator = this.ccsids.iterator(); iterator.hasNext(); ) { int ccsid = ((Integer)iterator.next()).intValue();
/*  66 */       result[i++] = ccsid; }
/*     */     
/*  68 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAscii() {
/*  75 */     return this.isAscii;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  83 */     StringBuilder sb = new StringBuilder();
/*  84 */     sb.append(this.javaName);
/*  85 */     sb.append("|");
/*  86 */     sb.append(this.isAscii);
/*  87 */     sb.append("|");
/*  88 */     boolean first = true;
/*  89 */     for (null = this.ccsids.iterator(); null.hasNext(); ) { int ccsid = ((Integer)null.next()).intValue();
/*  90 */       if (!first) {
/*  91 */         sb.append(",");
/*     */       }
/*  93 */       sb.append(ccsid);
/*  94 */       first = false; }
/*     */     
/*  96 */     sb.append("|");
/*  97 */     first = true;
/*  98 */     for (String aliasName : this.aliasNames) {
/*  99 */       if (!first) {
/* 100 */         sb.append(",");
/*     */       }
/* 102 */       sb.append(aliasName);
/* 103 */       first = false;
/*     */     } 
/* 105 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CcsidDefinition fromString(String in) {
/* 113 */     String[] inFields = in.split("\\|");
/* 114 */     String javaName = inFields[0];
/* 115 */     boolean isAscii = Boolean.parseBoolean(inFields[1]);
/* 116 */     CcsidDefinition result = new CcsidDefinition(javaName, isAscii);
/* 117 */     String[] ccsidStrings = inFields[2].split("\\,");
/* 118 */     for (String ccsidString : ccsidStrings) {
/* 119 */       result.addCcsid(Integer.parseInt(ccsidString));
/*     */     }
/* 121 */     if (inFields.length > 3) {
/* 122 */       String[] aliasNames = inFields[3].split("\\,");
/* 123 */       for (String aliasName : aliasNames) {
/* 124 */         if (aliasName.contains("-16") || aliasName.contains("_16") || aliasName.startsWith("Unicode")) {
/* 125 */           result.is16Bit = true;
/*     */         }
/* 127 */         result.addAlias(aliasName);
/*     */       } 
/*     */     } 
/* 130 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean is16Bit() {
/* 137 */     return this.is16Bit;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\CcsidDefinition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */