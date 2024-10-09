/*     */ package com.ibm.disthub2.impl.formats;
/*     */ 
/*     */ import com.ibm.disthub2.client.UninitializedAccessException;
/*     */ import com.ibm.disthub2.spi.ExceptionBuilder;
/*     */ import com.ibm.disthub2.spi.ExceptionConstants;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class MessageMap
/*     */   implements ExceptionConstants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   public long multiChoice;
/*     */   public int offsetsNeeded;
/*  47 */   int lastH = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] choiceCodes;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Remap[] fields;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MessageMap(FlatSchema flatSchema, long multiChoice) {
/*  64 */     this.multiChoice = multiChoice;
/*  65 */     this.choiceCodes = new int[flatSchema.variants.length];
/*  66 */     for (int i = 0; i < this.choiceCodes.length; i++) {
/*  67 */       this.choiceCodes[i] = -1;
/*     */     }
/*  69 */     this.fields = new Remap[flatSchema.fields.length];
/*     */ 
/*     */     
/*  72 */     setChoices(multiChoice, flatSchema.variants, 0);
/*     */ 
/*     */     
/*  75 */     int moi = -1;
/*  76 */     int length = 0;
/*  77 */     int incr = 0;
/*     */ 
/*     */     
/*  80 */     for (int j = 0; j < this.fields.length; j++) {
/*  81 */       if (isPresent(j, flatSchema.fields[j])) {
/*  82 */         byte typeCode = (flatSchema.fields[j]).field.getTypeCode();
/*     */         
/*  84 */         if ((flatSchema.fields[j]).field.getAccess() == -100)
/*  85 */           this.lastH = j; 
/*  86 */         if (typeCode >= 1) {
/*  87 */           length = SchemaCodes.typeSizes[typeCode - 1];
/*  88 */         } else if (typeCode == -1) {
/*  89 */           length = -2;
/*     */         } else {
/*  91 */           length = -1;
/*  92 */         }  this.fields[j] = new Remap(moi, incr, length);
/*  93 */         if (length != -1) {
/*  94 */           incr += length;
/*     */         } else {
/*  96 */           moi++;
/*  97 */           incr = 0;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 103 */     this.offsetsNeeded = (length == -1) ? moi : (moi + 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setChoices(long multiChoice, FlatSchema.Variant[] variants, int index) {
/* 113 */     long[] counts = (variants[index]).caseMultiCounts;
/* 114 */     for (int i = 0; i < counts.length; i++) {
/* 115 */       if (multiChoice >= counts[i]) {
/* 116 */         multiChoice -= counts[i];
/*     */       }
/*     */       else {
/*     */         
/* 120 */         this.choiceCodes[index] = i;
/* 121 */         if ((variants[index]).subVariants == null) {
/*     */           return;
/*     */         }
/* 124 */         FlatSchema.Variant[] subVars = (variants[index]).subVariants[i];
/* 125 */         if (subVars == null) {
/*     */           return;
/*     */         }
/* 128 */         long radix = counts[i];
/* 129 */         for (int j = 0; j < subVars.length; j++) {
/* 130 */           radix /= (subVars[j]).multiCount;
/* 131 */           long contrib = multiChoice / radix;
/* 132 */           multiChoice %= radix;
/* 133 */           setChoices(contrib, variants, (subVars[j]).index);
/*     */         } 
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/* 139 */     throw new RuntimeException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isPresent(int index, FlatSchema.Field field) {
/* 149 */     FlatSchema.Variant toTest = field.dominator;
/* 150 */     int testCase = field.domCase;
/* 151 */     while (toTest != null) {
/* 152 */       if (testCase != this.choiceCodes[toTest.index])
/* 153 */         return false; 
/* 154 */       testCase = toTest.domCase;
/* 155 */       toTest = toTest.dominator;
/*     */     } 
/* 157 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long getMultiChoice(int[] choices, FlatSchema.Variant[] variants, int index) {
/* 166 */     int choice = choices[index];
/* 167 */     if (choice == -1) {
/* 168 */       String name = variants[index].getFullName(true);
/* 169 */       if (name == null || name.length() == 0)
/* 170 */         name = String.valueOf(index); 
/* 171 */       throw new UninitializedAccessException(ExceptionBuilder.buildReasonString(-1987922836, new Object[] { name }));
/*     */     } 
/* 173 */     long ans = 0L;
/*     */     
/* 175 */     for (int i = 0; i < choice; i++) {
/* 176 */       ans += (variants[index]).caseMultiCounts[i];
/*     */     }
/*     */     
/* 179 */     FlatSchema.Variant[] subVars = null;
/* 180 */     if ((variants[index]).subVariants != null)
/* 181 */       subVars = (variants[index]).subVariants[choice]; 
/* 182 */     if (subVars == null)
/*     */     {
/* 184 */       return ans;
/*     */     }
/* 186 */     long base = 0L;
/* 187 */     for (int j = 0; j < subVars.length; j++) {
/* 188 */       base = base * (subVars[j]).multiCount + getMultiChoice(choices, variants, (subVars[j]).index);
/*     */     }
/* 190 */     return ans + base;
/*     */   }
/*     */ 
/*     */   
/*     */   public static class Remap
/*     */   {
/*     */     public int offsetIndex;
/*     */     public int fixedIncr;
/*     */     public int length;
/*     */     
/*     */     Remap(int offsetIndex, int fixedIncr, int length) {
/* 201 */       this.offsetIndex = offsetIndex;
/* 202 */       this.fixedIncr = fixedIncr;
/* 203 */       this.length = length;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\MessageMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */