/*     */ package com.ibm.mq.constants;
/*     */ 
/*     */ import com.ibm.mq.jmqi.StringJoiner;
/*     */ import com.ibm.mq.jmqi.internal.JmqiTools;
/*     */ import java.lang.ref.Reference;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.TreeMap;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class MQConstants
/*     */   implements CMQBC, CMQC, CMQCFC, CMQPSC, CMQXC, CMQZC, MQPropertyIdentifiers, TempConstants
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/constants/MQConstants.java";
/*     */   protected static final String UNFILTERED_DELIMITER = " ";
/*     */   protected static final String FILTERED_DELIMITER = "/";
/*     */   protected static final String OPTION_DELIMITER = " | ";
/*     */   protected static final String OPTIONVALUES_DELIMITER = "/";
/*     */   protected static final String OPTIONVALUES_DELIMITER_FOR_TRACE = " or ";
/*     */   protected static final String OPTIONVALUES_PREFIX = "";
/*     */   protected static final String OPTIONVALUES_PREFIX_FOR_TRACE = "{";
/*     */   protected static final String OPTIONVALUES_SUFFIX = "";
/*     */   protected static final String OPTIONVALUES_SUFFIX_FOR_TRACE = "}";
/*     */   static final String EXCEPTION_PATTERN = "MQRC_.*|MQRCCF_.*";
/* 118 */   protected static final Class<?> thisClass = MQConstants.class;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Reference<Field[]> fieldsRef;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 130 */   private static Map<Object, List<String>> cache = new TreeMap<>(new ValueValueComparator(MQConstants.class));
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Pattern cachedPattern;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 140 */   private static final Map<Integer, String> compCodeCache = new HashMap<>();
/* 141 */   private static final Map<Integer, String> reasonCache = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/* 147 */     compCodeCache.put(Integer.valueOf(0), "MQCC_OK");
/* 148 */     compCodeCache.put(Integer.valueOf(1), "MQCC_WARNING");
/* 149 */     compCodeCache.put(Integer.valueOf(2), "MQCC_FAILED");
/* 150 */     compCodeCache.put(Integer.valueOf(-1), "MQCC_UNKNOWN");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 155 */     reasonCache.put(Integer.valueOf(0), "MQRC_NONE");
/* 156 */     reasonCache.put(Integer.valueOf(2033), "MQRC_NO_MSG_AVAILABLE");
/* 157 */     reasonCache.put(Integer.valueOf(2428), "MQRC_NO_SUBSCRIPTION");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Object getValue(String name) {
/*     */     try {
/* 169 */       Field field = thisClass.getField(name);
/* 170 */       Object value = field.get(thisClass);
/* 171 */       return value;
/*     */     }
/* 173 */     catch (NoSuchFieldException|IllegalAccessException ex) {
/* 174 */       return null;
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
/*     */   public static int getIntValue(String name) throws NoSuchElementException {
/*     */     int intValue;
/* 189 */     Object value = getValue(name);
/*     */     
/* 191 */     if (value != null && value instanceof Number) {
/* 192 */       intValue = ((Number)value).intValue();
/*     */     } else {
/*     */       
/* 195 */       NoSuchElementException traceRet1 = new NoSuchElementException();
/* 196 */       throw traceRet1;
/*     */     } 
/*     */     
/* 199 */     return intValue;
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
/*     */   public static String lookupCompCode(int reason) {
/* 215 */     String result = compCodeCache.get(Integer.valueOf(reason));
/* 216 */     if (result == null) {
/* 217 */       result = lookup(reason, "MQCC_.*");
/*     */     }
/* 219 */     return result;
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
/*     */   public static String lookupReasonCode(int reason) {
/* 235 */     String result = reasonCache.get(Integer.valueOf(reason));
/* 236 */     if (result == null) {
/* 237 */       result = lookup(reason, "MQRC_.*|MQRCCF_.*");
/*     */     }
/* 239 */     return result;
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
/*     */   public static String lookup(Object value, String filter) {
/* 255 */     List<String> decodedNames = lookup(value, false);
/* 256 */     if (filter != null) {
/* 257 */       decodedNames = filter(decodedNames, getPattern(filter));
/* 258 */       return join(decodedNames, "/", "", "");
/*     */     } 
/*     */     
/* 261 */     return join(decodedNames, " ", "", "");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String join(List<String> decodedNames, String delimiter, String optionsPrefix, String optionsSuffix) {
/* 271 */     StringJoiner joiner = new StringJoiner(delimiter, optionsPrefix, optionsSuffix);
/* 272 */     for (String decodedName : decodedNames) {
/* 273 */       joiner.add(decodedName);
/*     */     }
/* 275 */     return joiner.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<String> lookup(Object valueP, boolean parse) {
/* 285 */     Object value = valueP;
/* 286 */     if (parse && value instanceof String) {
/*     */       
/*     */       try {
/*     */         
/* 290 */         value = Integer.valueOf((String)value);
/*     */       }
/* 292 */       catch (NumberFormatException numberFormatException) {}
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 299 */     List<String> decodedNames = cache.get(value);
/*     */     
/* 301 */     if (decodedNames == null) {
/* 302 */       decodedNames = fullLookup(value);
/*     */     }
/*     */     
/* 305 */     return decodedNames;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static List<String> fullLookup(Object value) {
/* 314 */     List<String> decodedNames = new ArrayList<>();
/* 315 */     Field[] fields = getFields();
/* 316 */     Comparator<Object> comparator = new FieldValueComparator(thisClass);
/* 317 */     int index = Arrays.binarySearch((Object[])fields, value, comparator);
/*     */     
/* 319 */     if (index >= 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 325 */       while (index > 0 && comparator.compare(fields[index - 1], value) == 0) {
/* 326 */         index--;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       do {
/* 334 */         String fieldName = fields[index].getName();
/* 335 */         decodedNames.add(fieldName);
/* 336 */         ++index;
/*     */       }
/* 338 */       while (index < fields.length && comparator.compare(fields[index], value) == 0);
/*     */       
/* 340 */       if (decodedNames.size() > 0) {
/* 341 */         synchronized (cache) {
/* 342 */           cache.put(value, decodedNames);
/*     */         } 
/*     */       }
/*     */     } 
/* 346 */     return decodedNames;
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
/*     */   public static String lookup(int value, String filter) {
/* 360 */     String traceRet1 = lookup(Integer.valueOf(value), filter);
/* 361 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static synchronized Field[] getFields() {
/* 368 */     Field[] fields = null;
/*     */     
/* 370 */     if (fieldsRef != null) {
/* 371 */       fields = fieldsRef.get();
/*     */     }
/*     */     
/* 374 */     if (fields == null) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 379 */       fields = MQConstants.class.getFields();
/*     */       
/* 381 */       Arrays.sort(fields, new FieldFieldComparator(thisClass));
/*     */       
/* 383 */       fieldsRef = (Reference)new SoftReference<>(fields);
/*     */     } 
/*     */     
/* 386 */     return fields;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static final Pattern getPattern(String filter) {
/* 395 */     if (filter == null) {
/* 396 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 403 */     if (cachedPattern == null || !cachedPattern.pattern().equals(filter)) {
/* 404 */       cachedPattern = Pattern.compile(filter);
/*     */     }
/*     */     
/* 407 */     return cachedPattern;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final List<String> filter(List<String> constantNames, Pattern filterPattern) {
/* 418 */     List<String> result = new ArrayList<>();
/*     */     
/* 420 */     for (String constantName : constantNames) {
/* 421 */       if (filterPattern.matcher(constantName).matches()) {
/* 422 */         result.add(constantName);
/*     */       }
/*     */     } 
/*     */     
/* 426 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class ValueValueComparator
/*     */     implements Comparator<Object>
/*     */   {
/*     */     final Object classInstance;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public ValueValueComparator(Object instance) {
/* 441 */       this.classInstance = instance;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int compare(Object aP, Object bP) {
/* 450 */       Object a = aP;
/* 451 */       Object b = bP;
/*     */ 
/*     */       
/* 454 */       if (a == null) {
/* 455 */         a = this;
/*     */       }
/* 457 */       if (b == null) {
/* 458 */         b = this;
/*     */       }
/*     */       
/* 461 */       int compared = getString(a).compareTo(getString(b));
/*     */       
/* 463 */       return compared;
/*     */     }
/*     */     
/*     */     private String getString(Object o) {
/* 467 */       Class<? extends Object> type = (Class)o.getClass();
/*     */       
/* 469 */       if (byte[].class == type)
/*     */       {
/* 471 */         return JmqiTools.arrayToHexString((byte[])o);
/*     */       }
/*     */ 
/*     */       
/* 475 */       return String.valueOf(o);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final class FieldValueComparator
/*     */     extends ValueValueComparator
/*     */   {
/*     */     public FieldValueComparator(Object instance) {
/* 488 */       super(instance);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int compare(Object a, Object b) {
/*     */       try {
/* 501 */         int traceRet1 = super.compare(((Field)a).get(MQConstants.thisClass), b);
/* 502 */         return traceRet1;
/*     */       }
/* 504 */       catch (IllegalAccessException e) {
/* 505 */         int traceRet2 = super.compare(a, b);
/* 506 */         return traceRet2;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static final class FieldFieldComparator
/*     */     extends ValueValueComparator
/*     */   {
/*     */     FieldFieldComparator(Object instance) {
/* 517 */       super(instance);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int compare(Object a, Object b) {
/*     */       try {
/* 530 */         int traceRet1 = super.compare(((Field)a).get(MQConstants.thisClass), ((Field)b).get(MQConstants.thisClass));
/* 531 */         return traceRet1;
/*     */       }
/* 533 */       catch (IllegalAccessException e) {
/* 534 */         int traceRet2 = super.compare(a, b);
/* 535 */         return traceRet2;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 544 */     if (args.length == 0) {
/* 545 */       String USAGE = "Usage: MQConstants nnn nnn ...\n\twhere nnn represents an options field to be decoded\n\tInteger.decode() is used to parse the arguments, which may be in decimal, hexidecimal or even octal form";
/*     */ 
/*     */       
/* 548 */       System.out.println(USAGE);
/* 549 */       System.exit(0);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 555 */     Map<String, TreeMap<Integer, String>> optionTypes = new TreeMap<>();
/* 556 */     Pattern optionPattern = Pattern.compile("MQ[A-Z]+O_.*");
/* 557 */     for (Field field : getFields()) {
/* 558 */       String fieldName = field.getName();
/* 559 */       Matcher optionMatcher = optionPattern.matcher(fieldName);
/* 560 */       if (field.getType() == int.class && optionMatcher.matches()) {
/*     */         try {
/* 562 */           int value = field.getInt(null);
/* 563 */           String optionPrefix = fieldName.split("_")[0];
/* 564 */           TreeMap<Integer, String> flags = optionTypes.get(optionPrefix);
/* 565 */           if (flags == null) {
/* 566 */             flags = new TreeMap<>();
/* 567 */             optionTypes.put(optionPrefix, flags);
/*     */           } 
/* 569 */           flags.put(Integer.valueOf(value), fieldName);
/*     */         }
/* 571 */         catch (IllegalArgumentException|IllegalAccessException illegalArgumentException) {}
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 581 */     StringBuilder result = new StringBuilder();
/* 582 */     for (String arg : args) {
/* 583 */       int optionFlags = Integer.decode(arg).intValue();
/* 584 */       System.out.format("Checking value %d (0x%x)%n%n", new Object[] { Integer.valueOf(optionFlags), Integer.valueOf(optionFlags) });
/*     */       
/* 586 */       for (String optionType : optionTypes.keySet()) {
/* 587 */         result.delete(0, result.length());
/* 588 */         int of = optionFlags;
/* 589 */         for (Map.Entry<Integer, String> entry : (Iterable<Map.Entry<Integer, String>>)((TreeMap)optionTypes.get(optionType)).entrySet()) {
/* 590 */           if ((of & ((Integer)entry.getKey()).intValue()) != 0) {
/* 591 */             if (result.length() > 0) {
/* 592 */               result.append(" | ");
/*     */             }
/* 594 */             result.append(entry.getValue());
/* 595 */             of &= ((Integer)entry.getKey()).intValue() ^ 0xFFFFFFFF;
/*     */           } 
/*     */         } 
/* 598 */         if (result.length() > 0 && of == 0) {
/* 599 */           System.out.format("\tOptionType: %s\t%n\t\t%s%n%n", new Object[] { optionType, result.toString() });
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String decodeOptions(int options, String optionPattern) {
/* 618 */     return decodeOptions(options, optionPattern, " | ", "/", "", "");
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
/*     */   public static String decodeOptionsForTrace(int options, String optionPattern) {
/* 633 */     return decodeOptions(options, optionPattern, " | ", " or ", "{", "}");
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
/*     */   private static String decodeOptions(int options, String optionPattern, String optionDelimiter, String valueDelimiter, String optionsPrefix, String optionsSuffix) {
/* 646 */     StringJoiner result = new StringJoiner(optionDelimiter);
/* 647 */     for (List<String> decodedNames : decodeOptionsAsList(options, optionPattern)) {
/* 648 */       result.add(join(decodedNames, valueDelimiter, optionsPrefix, optionsSuffix));
/*     */     }
/* 650 */     return result.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<List<String>> decodeOptionsAsList(int optionsP, String optionPattern) {
/* 661 */     int options = optionsP;
/*     */     
/* 663 */     List<List<String>> result = new ArrayList<>();
/*     */     
/* 665 */     if (options == 0) {
/* 666 */       result.add(decodeOption(optionPattern, 0));
/*     */     } else {
/*     */       int mask;
/*     */       
/* 670 */       for (mask = 1; mask < 1073741824 && options != 0; mask <<= 1) {
/* 671 */         if ((options & mask) != 0) {
/* 672 */           result.add(decodeOption(optionPattern, mask));
/*     */         }
/* 674 */         options &= mask ^ 0xFFFFFFFF;
/*     */       } 
/*     */     } 
/*     */     
/* 678 */     return result;
/*     */   }
/*     */   
/*     */   private static List<String> decodeOption(String optionPattern, int option) {
/* 682 */     List<String> decodedNames = lookup(Integer.valueOf(option), false);
/* 683 */     Pattern filterPattern = getPattern(optionPattern);
/* 684 */     if (filterPattern != null) {
/* 685 */       decodedNames = filter(decodedNames, filterPattern);
/*     */     }
/* 687 */     decodedNames = stripVersion(decodedNames);
/* 688 */     return decodedNames;
/*     */   }
/*     */   
/*     */   private static List<String> stripVersion(List<String> decodedNames) {
/* 692 */     List<String> result = new ArrayList<>();
/* 693 */     for (String decodedName : decodedNames) {
/* 694 */       if (!decodedName.contains("_VERSION_") && 
/* 695 */         !decodedName.endsWith("_VERSION")) {
/* 696 */         result.add(decodedName);
/*     */       }
/*     */     } 
/*     */     
/* 700 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\constants\MQConstants.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */