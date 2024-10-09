/*     */ package com.ibm.mq.jmqi;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ConstantDecoder
/*     */ {
/*     */   protected static final String OPTION_DELIMITER = " | ";
/*     */   protected static final String OPTIONVALUES_DELIMITER_FOR_TRACE = " or ";
/*     */   protected static final String OPTIONVALUES_PREFIX_FOR_TRACE = "{";
/*     */   protected static final String OPTIONVALUES_SUFFIX_FOR_TRACE = "}";
/*     */   
/*     */   public static String decodeOptions(int optionsP, Field[] fields, String prefix) {
/*  47 */     int options = optionsP;
/*     */     
/*  49 */     List<List<String>> optionList = new ArrayList<>();
/*  50 */     if (options == 0) {
/*  51 */       decodeOption(0, optionList, fields, prefix);
/*     */     } else {
/*     */       int mask;
/*     */       
/*  55 */       for (mask = 1; mask < 1073741824 && options != 0; mask <<= 1) {
/*  56 */         if ((options & mask) != 0) {
/*  57 */           decodeOption(mask, optionList, fields, prefix);
/*     */         }
/*  59 */         options &= mask ^ 0xFFFFFFFF;
/*     */       } 
/*     */     } 
/*     */     
/*  63 */     StringJoiner result = new StringJoiner(" | ");
/*  64 */     for (List<String> optionValueList : optionList) {
/*  65 */       result.add(join(optionValueList, " or ", "{", "}"));
/*     */     }
/*     */     
/*  68 */     return result.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String formatOptions(int options, Field[] fields, String prefix) {
/*  78 */     String decoded = decodeOptions(options, fields, prefix);
/*  79 */     if (decoded == null || decoded.length() == 0) {
/*  80 */       return String.format("%d (0X%x)", new Object[] { Integer.valueOf(options), Integer.valueOf(options) });
/*     */     }
/*     */     
/*  83 */     return String.format("%d (0X%x) - %s", new Object[] { Integer.valueOf(options), Integer.valueOf(options), decoded });
/*     */   }
/*     */ 
/*     */   
/*     */   private static void decodeOption(int option, List<List<String>> result, Field[] fields, String prefix) {
/*  88 */     List<String> decode = lookup(option, fields, prefix);
/*  89 */     if (decode.size() != 0) {
/*  90 */       result.add(decode);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected static List<String> lookup(int option, Field[] fields, String prefix) {
/*  96 */     List<String> result = new ArrayList<>();
/*  97 */     for (Field field : fields) {
/*  98 */       if (field.getName().startsWith(prefix) && 
/*  99 */         !field.getName().contains("_VERSION") && field
/* 100 */         .getType() == int.class) {
/*     */         try {
/* 102 */           int value = field.getInt(null);
/* 103 */           if (value == option) {
/* 104 */             result.add(field.getName());
/*     */           }
/*     */         }
/* 107 */         catch (IllegalArgumentException|IllegalAccessException illegalArgumentException) {}
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 112 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String decodeSingleOption(int value, Field[] fields, String prefix) {
/* 122 */     return join(lookup(value, fields, prefix), " or ", "{", "}");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String formatSingleOption(int value, Field[] fields, String prefix) {
/* 132 */     String decoded = decodeSingleOption(value, fields, prefix);
/* 133 */     if (decoded == null || decoded.length() == 0) {
/* 134 */       return String.format("%d (0X%x)", new Object[] { Integer.valueOf(value), Integer.valueOf(value) });
/*     */     }
/*     */     
/* 137 */     return String.format("%d (0X%x) - %s", new Object[] { Integer.valueOf(value), Integer.valueOf(value), decoded });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String join(List<String> decodedNames, String delimiter, String optionsPrefix, String optionsSuffix) {
/* 147 */     StringJoiner joiner = new StringJoiner(delimiter, optionsPrefix, optionsSuffix);
/* 148 */     for (String decodedName : decodedNames) {
/* 149 */       joiner.add(decodedName);
/*     */     }
/* 151 */     return joiner.toString();
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\ConstantDecoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */