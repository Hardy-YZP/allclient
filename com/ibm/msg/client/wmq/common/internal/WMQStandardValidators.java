/*      */ package com.ibm.msg.client.wmq.common.internal;
/*      */ 
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.regex.Pattern;
/*      */ import javax.jms.JMSException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class WMQStandardValidators
/*      */ {
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/WMQStandardValidators.java";
/*      */   
/*      */   static {
/*   42 */     if (Trace.isOn) {
/*   43 */       Trace.data("com.ibm.msg.client.wmq.common.internal.WMQStandardValidators", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/WMQStandardValidators.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class CaseInsentiveHashmap
/*      */     extends HashMap<Object, Object>
/*      */   {
/*      */     private static final long serialVersionUID = -3235307258816920577L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean containsKey(Object key) {
/*   65 */       if (Trace.isOn) {
/*   66 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.CaseInsentiveHashmap", "containsKey(Object)", new Object[] { key });
/*      */       }
/*      */       
/*   69 */       Object myKey = (key instanceof String) ? ((String)key).toUpperCase() : key;
/*   70 */       boolean traceRet1 = super.containsKey(myKey);
/*   71 */       if (Trace.isOn) {
/*   72 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.CaseInsentiveHashmap", "containsKey(Object)", 
/*   73 */             Boolean.valueOf(traceRet1));
/*      */       }
/*   75 */       return traceRet1;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Object get(Object key) {
/*   83 */       if (Trace.isOn) {
/*   84 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.CaseInsentiveHashmap", "get(Object)", new Object[] { key });
/*      */       }
/*      */       
/*   87 */       Object myKey = (key instanceof String) ? ((String)key).toUpperCase() : key;
/*   88 */       Object traceRet1 = super.get(myKey);
/*   89 */       if (Trace.isOn) {
/*   90 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.CaseInsentiveHashmap", "get(Object)", traceRet1);
/*      */       }
/*      */       
/*   93 */       return traceRet1;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Object put(Object key, Object arg1) {
/*  101 */       if (Trace.isOn) {
/*  102 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.CaseInsentiveHashmap", "put(Object,Object)", new Object[] { key, arg1 });
/*      */       }
/*      */       
/*  105 */       Object myKey = (key instanceof String) ? ((String)key).toUpperCase() : key;
/*  106 */       Object traceRet1 = super.put(myKey, arg1);
/*  107 */       if (Trace.isOn) {
/*  108 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.CaseInsentiveHashmap", "put(Object,Object)", traceRet1);
/*      */       }
/*      */       
/*  111 */       return traceRet1;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Object remove(Object key) {
/*  119 */       if (Trace.isOn) {
/*  120 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.CaseInsentiveHashmap", "remove(Object)", new Object[] { key });
/*      */       }
/*      */       
/*  123 */       Object myKey = (key instanceof String) ? ((String)key).toUpperCase() : key;
/*  124 */       Object traceRet1 = super.remove(myKey);
/*  125 */       if (Trace.isOn) {
/*  126 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.CaseInsentiveHashmap", "remove(Object)", traceRet1);
/*      */       }
/*      */       
/*  129 */       return traceRet1;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static abstract class WMQApplicationNamePropertyValidator
/*      */     extends WMQStringPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -4723428365012933293L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQApplicationNamePropertyValidator(int domain, HashMap<Integer, String> domainNames) {
/*  148 */       super(domain, domainNames);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/*  159 */       String appName = convertToString(value);
/*  160 */       if (appName == null || appName.length() <= 0 || appName.length() > 28) {
/*  161 */         return false;
/*      */       }
/*  163 */       return true;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static abstract class WMQBooleanPropertyValidator
/*      */     extends WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 6967310440464503110L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQBooleanPropertyValidator(int domain, HashMap<Integer, String> domainNames) {
/*  181 */       super(domain, domainNames);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQBooleanPropertyValidator(int domain, HashMap<Integer, String> domainNames, HashMap<Object, Object> valuesToCanonical) {
/*  191 */       super(domain, domainNames, valuesToCanonical);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/*  202 */       if (value instanceof Boolean) {
/*  203 */         return true;
/*      */       }
/*      */ 
/*      */       
/*  207 */       String myValue = convertToString(value);
/*      */       
/*  209 */       if (myValue == null) {
/*  210 */         return false;
/*      */       }
/*      */ 
/*      */       
/*  214 */       if (myValue.equalsIgnoreCase("true") || myValue.equalsIgnoreCase("yes") || myValue.equalsIgnoreCase("false") || myValue.equalsIgnoreCase("no")) {
/*  215 */         return true;
/*      */       }
/*      */       
/*  218 */       return false;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static abstract class WMQChannelNamePropertyValidator
/*      */     extends WMQStringPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 8072163458710729837L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQChannelNamePropertyValidator(int domain, HashMap<Integer, String> domainNames) {
/*  236 */       super(domain, domainNames);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/*  247 */       String queueName = convertToString(value);
/*  248 */       if (queueName == null || queueName.length() > 20) {
/*  249 */         return false;
/*      */       }
/*  251 */       return true;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static abstract class WMQIntPropertyValidator
/*      */     extends WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 2013417708304942541L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQIntPropertyValidator(int domain, HashMap<Integer, String> domainNames) {
/*  269 */       super(domain, domainNames);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQIntPropertyValidator(int domain, HashMap<Integer, String> domainNames, HashMap<Object, Object> valuesToCanonical) {
/*  279 */       super(domain, domainNames, valuesToCanonical);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/*  290 */       if (value instanceof Integer) {
/*  291 */         return true;
/*      */       }
/*      */ 
/*      */       
/*  295 */       String myValue = convertToString(value);
/*      */       
/*  297 */       if (myValue == null) {
/*  298 */         return false;
/*      */       }
/*      */ 
/*      */       
/*      */       try {
/*  303 */         Integer.parseInt(myValue);
/*      */       }
/*  305 */       catch (NumberFormatException e) {
/*      */         
/*  307 */         return false;
/*      */       } 
/*      */       
/*  310 */       return true;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static abstract class WMQPropertyValidator
/*      */     implements WMQValidationInterface
/*      */   {
/*      */     private static final long serialVersionUID = -4471899697874758148L;
/*      */ 
/*      */ 
/*      */     
/*  325 */     protected static final HashMap<Object, Object> yesNoToBoolean = new HashMap<>(2, 1.0F);
/*      */     static {
/*  327 */       if (Trace.isOn) {
/*  328 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQPropertyValidator", "static()");
/*      */       }
/*  330 */       yesNoToBoolean.put("YES", Boolean.TRUE);
/*  331 */       yesNoToBoolean.put("NO", Boolean.FALSE);
/*  332 */       if (Trace.isOn) {
/*  333 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */     
/*  337 */     protected static final Set<Object> trueFalseYesNo = new HashSet(4, 1.0F);
/*      */     static {
/*  339 */       if (Trace.isOn) {
/*  340 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQPropertyValidator", "static()");
/*      */       }
/*      */ 
/*      */       
/*  344 */       trueFalseYesNo.add("YES");
/*  345 */       trueFalseYesNo.add("NO");
/*  346 */       trueFalseYesNo.add("TRUE");
/*  347 */       trueFalseYesNo.add("FALSE");
/*      */       
/*  349 */       if (Trace.isOn) {
/*  350 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */     
/*  354 */     protected String applicablePropertyName = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQValidationInterface.WMQPropertyValidatorDatatype fromCanonical(JmsPropertyContext context, String keyIn, Object valueIn) {
/*  366 */       if (Trace.isOn) {
/*  367 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQPropertyValidator", "fromCanonical(JmsPropertyContext,String,Object)", new Object[] { context, keyIn, valueIn });
/*      */       }
/*      */ 
/*      */       
/*  371 */       WMQValidationInterface.WMQPropertyValidatorDatatype traceRet1 = fromCanonical(keyIn, valueIn);
/*  372 */       if (Trace.isOn) {
/*  373 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQPropertyValidator", "fromCanonical(JmsPropertyContext,String,Object)", traceRet1);
/*      */       }
/*      */       
/*  376 */       return traceRet1;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQValidationInterface.WMQPropertyValidatorDatatype toCanonical(JmsPropertyContext context, String keyIn, Object valueIn) {
/*  389 */       String propertyName = this.domainNames.get(
/*  390 */           Integer.valueOf(4));
/*      */ 
/*      */ 
/*      */       
/*  394 */       WMQValidationInterface.WMQPropertyValidatorDatatype traceRet1 = toCanonical(keyIn, valueIn);
/*      */       
/*  396 */       return traceRet1;
/*      */     }
/*      */     
/*  399 */     protected HashMap<Integer, String> domainNames = null;
/*  400 */     protected HashMap<Integer, HashMap<Object, Object>> valueToCanMappings = new HashMap<>();
/*  401 */     protected HashMap<Object, Object> valueFromCanMappings = new HashMap<>();
/*      */ 
/*      */ 
/*      */     
/*      */     protected int mapperDomain;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQPropertyValidator(int domain, HashMap<Integer, String> domainNames) {
/*  411 */       this.mapperDomain = domain;
/*  412 */       this.domainNames = domainNames;
/*  413 */       this.applicablePropertyName = domainNames.get(
/*  414 */           Integer.valueOf(domain));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQPropertyValidator(int domain, HashMap<Integer, String> domainNames, HashMap<Object, Object> valuesToCanonical) {
/*  424 */       this(domain, domainNames);
/*      */       
/*  426 */       this.valueToCanMappings.put(Integer.valueOf(1), valuesToCanonical);
/*  427 */       this.valueToCanMappings.put(Integer.valueOf(2), valuesToCanonical);
/*      */ 
/*      */       
/*  430 */       reverseValueMappings();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void reverseValueMappings() {
/*  437 */       for (Map.Entry<Integer, HashMap<Object, Object>> mapping : this.valueToCanMappings.entrySet()) {
/*  438 */         Integer domain = mapping.getKey();
/*  439 */         HashMap<Object, Object> domainValueMappings = mapping.getValue();
/*      */         
/*  441 */         HashMap<Object, Object> reverseValueMappings = new HashMap<>();
/*  442 */         for (Map.Entry<Object, Object> dvMapping : domainValueMappings.entrySet()) {
/*  443 */           Object canValue = dvMapping.getKey();
/*  444 */           Object domainValue = dvMapping.getValue();
/*  445 */           reverseValueMappings.put(domainValue, canValue);
/*      */         } 
/*  447 */         this.valueFromCanMappings.put(domain, reverseValueMappings);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQValidationInterface.WMQPropertyValidatorDatatype fromCanonical(String keyIn, Object valueIn) {
/*  464 */       Object valueOut, myValueIn = (valueIn instanceof String) ? ((String)valueIn).trim() : valueIn;
/*  465 */       String keyOut = this.domainNames.get(Integer.valueOf(this.mapperDomain));
/*      */ 
/*      */       
/*  468 */       if (this.mapperDomain == 4) {
/*  469 */         valueOut = valueIn;
/*      */       }
/*      */       else {
/*      */         
/*  473 */         HashMap mappings = (HashMap)this.valueFromCanMappings.get(Integer.valueOf(this.mapperDomain));
/*      */         
/*  475 */         if (mappings == null) {
/*      */           
/*  477 */           valueOut = myValueIn;
/*      */         } else {
/*  479 */           valueOut = mappings.get(myValueIn);
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  484 */       WMQValidationInterface.WMQPropertyValidatorDatatype returnVal = new WMQValidationInterface.WMQPropertyValidatorDatatype(keyOut, valueOut);
/*  485 */       return returnVal;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getDomainName(int domain) {
/*      */       try {
/*  495 */         String name = this.domainNames.get(Integer.valueOf(domain));
/*  496 */         return name;
/*      */       }
/*  498 */       catch (NullPointerException npe) {
/*  499 */         return null;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQValidationInterface.WMQPropertyValidatorDatatype toCanonical(String keyIn, Object valueIn) {
/*      */       Object valueOut;
/*  514 */       String keyOut = this.domainNames.get(Integer.valueOf(4));
/*      */ 
/*      */       
/*  517 */       if (this.mapperDomain == 4) {
/*  518 */         valueOut = valueIn;
/*      */       } else {
/*      */         
/*  521 */         HashMap mappings = this.valueToCanMappings.get(Integer.valueOf(this.mapperDomain));
/*  522 */         if (mappings == null) {
/*      */           
/*  524 */           valueOut = valueIn;
/*      */         } else {
/*  526 */           valueOut = mappings.get(valueIn);
/*  527 */           if (valueOut == null) {
/*  528 */             valueOut = valueIn;
/*      */           }
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  534 */       WMQValidationInterface.WMQPropertyValidatorDatatype returnVal = new WMQValidationInterface.WMQPropertyValidatorDatatype(keyOut, valueOut);
/*  535 */       return returnVal;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean crossPropertyValidate(Object parent) throws JMSException {
/*  549 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String convertToString(Object value) {
/*  561 */       if (Trace.isOn) {
/*  562 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQPropertyValidator", "convertToString(Object)", new Object[] { value });
/*      */       }
/*      */       
/*  565 */       if (value == null) {
/*  566 */         if (Trace.isOn) {
/*  567 */           Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQPropertyValidator", "convertToString(Object)", null, 1);
/*      */         }
/*      */         
/*  570 */         return null;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  575 */       String traceRet = String.valueOf(value);
/*  576 */       if (Trace.isOn) {
/*  577 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQPropertyValidator", "convertToString(Object)", traceRet, 2);
/*      */       }
/*      */       
/*  580 */       return traceRet;
/*      */     }
/*      */     
/*      */     protected boolean validateLongForRange(Object value, long minAllowable, long maxAllowable) throws JMSException {
/*  584 */       if (Trace.isOn) {
/*  585 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQPropertyValidator", "validateLongForRange(Object,long,long)", new Object[] { value, 
/*  586 */               Long.valueOf(minAllowable), 
/*  587 */               Long.valueOf(maxAllowable) });
/*      */       }
/*      */       
/*  590 */       long longValue = 0L;
/*  591 */       if (value instanceof Long) {
/*  592 */         longValue = ((Long)value).longValue();
/*  593 */       } else if (value instanceof Integer) {
/*  594 */         longValue = ((Integer)value).longValue();
/*      */       }
/*      */       else {
/*      */         
/*  598 */         String stringValue = convertToString(value);
/*  599 */         if (stringValue == null) {
/*  600 */           if (Trace.isOn) {
/*  601 */             Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQPropertyValidator", "validateLongForRange(Object,long,long)", 
/*  602 */                 Boolean.valueOf(false), 1);
/*      */           }
/*  604 */           return false;
/*      */         } 
/*      */         try {
/*  607 */           longValue = Long.parseLong(stringValue);
/*      */         }
/*  609 */         catch (NumberFormatException e) {
/*  610 */           if (Trace.isOn) {
/*  611 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.common.internal.WMQPropertyValidator", "validateLongForRange(Object,long,long)", e);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  616 */           if (Trace.isOn) {
/*  617 */             Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQPropertyValidator", "validateLongForRange(Object,long,long)", 
/*  618 */                 Boolean.valueOf(false), 2);
/*      */           }
/*  620 */           return false;
/*      */         } 
/*      */       } 
/*      */       
/*  624 */       if (longValue < minAllowable || longValue > maxAllowable) {
/*  625 */         throwBadValueException(Long.valueOf(longValue));
/*      */       }
/*  627 */       if (Trace.isOn) {
/*  628 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQPropertyValidator", "validateLongForRange(Object,long,long)", 
/*  629 */             Boolean.valueOf(true), 3);
/*      */       }
/*  631 */       return true;
/*      */     }
/*      */     
/*      */     protected boolean validateIntForRange(Object value, int minAllowable, int maxAllowable) throws JMSException {
/*  635 */       if (Trace.isOn) {
/*  636 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQPropertyValidator", "validateIntForRange(Object,int,int)", new Object[] { value, 
/*  637 */               Integer.valueOf(minAllowable), 
/*  638 */               Integer.valueOf(maxAllowable) });
/*      */       }
/*      */       
/*  641 */       int intValue = 0;
/*  642 */       if (value instanceof Long) {
/*  643 */         intValue = ((Long)value).intValue();
/*      */       }
/*  645 */       else if (value instanceof Integer) {
/*  646 */         intValue = ((Integer)value).intValue();
/*      */       }
/*      */       else {
/*      */         
/*  650 */         String stringValue = convertToString(value);
/*  651 */         if (stringValue == null) {
/*  652 */           if (Trace.isOn) {
/*  653 */             Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQPropertyValidator", "validateIntForRange(Object,int,int)", 
/*  654 */                 Boolean.valueOf(false), 1);
/*      */           }
/*  656 */           return false;
/*      */         } 
/*      */         try {
/*  659 */           intValue = Integer.parseInt(stringValue);
/*      */         }
/*  661 */         catch (NumberFormatException e) {
/*  662 */           if (Trace.isOn) {
/*  663 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.common.internal.WMQPropertyValidator", "validateIntForRange(Object,int,int)", e);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  668 */           if (Trace.isOn) {
/*  669 */             Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQPropertyValidator", "validateIntForRange(Object,int,int)", 
/*  670 */                 Boolean.valueOf(false), 2);
/*      */           }
/*  672 */           return false;
/*      */         } 
/*      */       } 
/*      */       
/*  676 */       if (intValue < minAllowable || intValue > maxAllowable) {
/*  677 */         throwBadValueException(Integer.valueOf(intValue));
/*      */       }
/*  679 */       if (Trace.isOn) {
/*  680 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQPropertyValidator", "validateIntForRange(Object,int,int)", 
/*  681 */             Boolean.valueOf(true), 3);
/*      */       }
/*  683 */       return true;
/*      */     }
/*      */     
/*      */     protected boolean validateIntBySet(Object value, Set<Object> validValues) throws JMSException {
/*  687 */       if (Trace.isOn) {
/*  688 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQPropertyValidator", "validateIntBySet(Object,Set<Object>)", new Object[] { value, validValues });
/*      */       }
/*      */       
/*  691 */       Integer intValue = null;
/*      */       
/*  693 */       if (value instanceof Long) {
/*  694 */         intValue = Integer.valueOf(((Long)value).intValue());
/*      */       }
/*  696 */       else if (value instanceof Integer) {
/*  697 */         intValue = (Integer)value;
/*      */       } else {
/*  699 */         String stringValue = convertToString(value);
/*  700 */         if (stringValue == null) {
/*  701 */           if (Trace.isOn) {
/*  702 */             Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQPropertyValidator", "validateIntBySet(Object,Set<Object>)", 
/*  703 */                 Boolean.valueOf(false), 1);
/*      */           }
/*  705 */           return false;
/*      */         } 
/*      */         
/*  708 */         if (this.mapperDomain == 1 || this.mapperDomain == 2) {
/*      */           
/*  710 */           HashMap<Object, Object> valueToCan = this.valueToCanMappings.get(Integer.valueOf(this.mapperDomain));
/*  711 */           if (valueToCan != null && valueToCan.containsKey(value)) {
/*  712 */             intValue = (Integer)valueToCan.get(value);
/*      */           } else {
/*      */             
/*  715 */             if (Trace.isOn) {
/*  716 */               Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQPropertyValidator", "validateIntBySet(Object,Set<Object>)", 
/*  717 */                   Boolean.valueOf(false), 2);
/*      */             }
/*  719 */             return false;
/*      */           } 
/*      */         } else {
/*      */           
/*      */           try {
/*  724 */             intValue = Integer.valueOf(stringValue);
/*      */           }
/*  726 */           catch (NumberFormatException e) {
/*  727 */             if (Trace.isOn) {
/*  728 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.common.internal.WMQPropertyValidator", "validateIntBySet(Object,Set<Object>)", e);
/*      */             }
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  734 */             if (Trace.isOn) {
/*  735 */               Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQPropertyValidator", "validateIntBySet(Object,Set<Object>)", 
/*  736 */                   Boolean.valueOf(false), 3);
/*      */             }
/*  738 */             return false;
/*      */           } 
/*      */         } 
/*      */       } 
/*  742 */       if (!validValues.contains(intValue)) {
/*  743 */         throwBadValueException(intValue);
/*      */       }
/*  745 */       if (Trace.isOn) {
/*  746 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQPropertyValidator", "validateIntBySet(Object,Set<Object>)", 
/*  747 */             Boolean.valueOf(true), 4);
/*      */       }
/*  749 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean validateBooleanTFYN(Object value) {
/*  759 */       if (Trace.isOn) {
/*  760 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQPropertyValidator", "validateBooleanTFYN(Object)", new Object[] { value });
/*      */       }
/*      */       
/*  763 */       if (value instanceof Boolean) {
/*  764 */         if (Trace.isOn) {
/*  765 */           Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQPropertyValidator", "validateBooleanTFYN(Object)", 
/*  766 */               Boolean.valueOf(true), 1);
/*      */         }
/*  768 */         return true;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  773 */       String stringValue = convertToString(value);
/*  774 */       if (stringValue == null) {
/*  775 */         if (Trace.isOn) {
/*  776 */           Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQPropertyValidator", "validateBooleanTFYN(Object)", 
/*  777 */               Boolean.valueOf(false), 2);
/*      */         }
/*  779 */         return false;
/*      */       } 
/*      */       
/*  782 */       boolean traceRet1 = trueFalseYesNo.contains(stringValue.toUpperCase());
/*  783 */       if (Trace.isOn) {
/*  784 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQPropertyValidator", "validateBooleanTFYN(Object)", 
/*  785 */             Boolean.valueOf(traceRet1), 3);
/*      */       }
/*  787 */       return traceRet1;
/*      */     }
/*      */ 
/*      */     
/*      */     protected boolean validateString(Object value, Pattern uriPattern, Pattern validChars, int maxLength, boolean resultIfNull) {
/*  792 */       if (value == null) {
/*  793 */         return resultIfNull;
/*      */       }
/*      */       
/*  796 */       String name = convertToString(value);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  804 */       if (uriPattern != null && uriPattern.matcher(name).matches())
/*      */       {
/*  806 */         return true;
/*      */       }
/*      */       
/*  809 */       if (name.length() > maxLength) {
/*  810 */         return false;
/*      */       }
/*      */ 
/*      */       
/*  814 */       if (validChars.matcher(name).matches()) {
/*  815 */         return true;
/*      */       }
/*      */       
/*  818 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public abstract void throwBadValueException(Object param1Object) throws JMSException;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static abstract class WMQBrokerQueueNamePropertyValidator
/*      */     extends WMQStringPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 8063134122139699815L;
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQBrokerQueueNamePropertyValidator(int domain, HashMap<Integer, String> domainNames) {
/*  838 */       super(domain, domainNames);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/*  850 */       topicCFvalidate(parent);
/*      */       
/*  852 */       String queueName = convertToString(value);
/*      */       
/*  854 */       if (queueName != null && queueName.length() <= 48)
/*      */       {
/*      */         
/*  857 */         if (queueName.startsWith("SYSTEM.JMS.ND.") || queueName.equals("")) {
/*  858 */           return true;
/*      */         }
/*      */       }
/*  861 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public abstract void topicCFvalidate(Object param1Object) throws JMSException;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static abstract class WMQQueueNamePropertyValidator
/*      */     extends WMQStringPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 8200512289994318081L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQQueueNamePropertyValidator(int domain, HashMap<Integer, String> domainNames) {
/*  888 */       super(domain, domainNames);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/*  899 */       String queueName = convertToString(value);
/*  900 */       if (queueName == null || queueName.length() > 48) {
/*  901 */         return false;
/*      */       }
/*  903 */       return true;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static abstract class WMQStringPropertyValidator
/*      */     extends WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 8095053398196219654L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQStringPropertyValidator(int domain, HashMap<Integer, String> domainNames) {
/*  924 */       super(domain, domainNames);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/*  937 */       return true;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static abstract class WMQTrivialPropertyValidator
/*      */     extends WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -3920430316343950980L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQTrivialPropertyValidator(int domain, HashMap<Integer, String> domainNames) {
/*  955 */       super(domain, domainNames);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/*  967 */       return true;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static abstract class WMQCcsidPropertyValidator
/*      */     extends WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 5413229385800692188L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQCcsidPropertyValidator(int domain, HashMap<Integer, String> domainNames) {
/*  987 */       super(domain, domainNames);
/*  988 */       if (Trace.isOn) {
/*  989 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQCcsidPropertyValidator", "<init>(int,HashMap<Integer , String>)", new Object[] {
/*  990 */               Integer.valueOf(domain), domainNames
/*      */             });
/*      */       }
/*  993 */       if (Trace.isOn) {
/*  994 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQCcsidPropertyValidator", "<init>(int,HashMap<Integer , String>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object valueP, Object parent) throws JMSException {
/* 1007 */       Object value = valueP;
/*      */ 
/*      */       
/* 1010 */       if (value instanceof String) {
/*      */         try {
/* 1012 */           value = Integer.valueOf((String)value);
/*      */         }
/* 1014 */         catch (NumberFormatException e) {
/*      */ 
/*      */           
/* 1017 */           return false;
/*      */         } 
/*      */       }
/* 1020 */       if (value instanceof Integer) {
/* 1021 */         int ccsid = ((Integer)value).intValue();
/*      */ 
/*      */ 
/*      */         
/*      */         try {
/* 1026 */           if (ccsid < 0) {
/* 1027 */             throwBadValueException(Integer.valueOf(ccsid));
/*      */           }
/* 1029 */           return true;
/*      */         }
/* 1031 */         catch (JMSException je) {
/* 1032 */           throw je;
/*      */         } 
/*      */       } 
/* 1035 */       return false;
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\common\internal\WMQStandardValidators.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */