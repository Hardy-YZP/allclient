/*      */ package com.ibm.msg.client.wmq.common.internal;
/*      */ 
/*      */ import com.ibm.mq.jmqi.JmqiDefaultPropertyHandler;
/*      */ import com.ibm.mq.jmqi.JmqiDefaultThreadPoolFactory;
/*      */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*      */ import com.ibm.mq.jmqi.JmqiException;
/*      */ import com.ibm.mq.jmqi.JmqiFactory;
/*      */ import com.ibm.mq.jmqi.JmqiPropertyHandler;
/*      */ import com.ibm.mq.jmqi.JmqiThreadPoolFactory;
/*      */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*      */ import com.ibm.msg.client.commonservices.Log.Log;
/*      */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*      */ import com.ibm.msg.client.commonservices.trace.TableBuilder;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*      */ import com.ibm.msg.client.provider.ProviderConnection;
/*      */ import com.ibm.msg.client.provider.ProviderDestination;
/*      */ import java.io.IOException;
/*      */ import java.io.NotSerializableException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.PrintWriter;
/*      */ import java.nio.charset.CodingErrorAction;
/*      */ import java.util.Arrays;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.Vector;
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
/*      */ public class WMQDestination
/*      */   extends WMQPropertyContext
/*      */   implements ProviderDestination
/*      */ {
/*      */   private static final long serialVersionUID = -3208663426915897132L;
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/WMQDestination.java";
/*      */   
/*      */   static {
/*   77 */     if (Trace.isOn) {
/*   78 */       Trace.data("com.ibm.msg.client.wmq.common.internal.WMQDestination", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/WMQDestination.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static final class CacheProperty
/*      */   {
/*      */     String name;
/*      */ 
/*      */     
/*      */     String uriName;
/*      */ 
/*      */     
/*      */     Object value;
/*      */ 
/*      */     
/*      */     Object defaultValue;
/*      */     
/*      */     boolean baseProperty;
/*      */     
/*      */     boolean exists;
/*      */ 
/*      */     
/*      */     CacheProperty(String name, Object defaultValue, boolean baseProperty) {
/*  103 */       this(name, name, defaultValue, baseProperty);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     CacheProperty(String name, String uriName, Object defaultValue, boolean baseProperty) {
/*  114 */       this.name = name;
/*  115 */       this.uriName = (uriName == null) ? name : uriName;
/*  116 */       this.value = defaultValue;
/*  117 */       this.defaultValue = defaultValue;
/*  118 */       this.baseProperty = baseProperty;
/*  119 */       this.exists = true;
/*      */     }
/*      */     
/*      */     void clear() {
/*  123 */       if (Trace.isOn) {
/*  124 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.CacheProperty", "clear()");
/*      */       }
/*  126 */       this.value = null;
/*  127 */       this.exists = false;
/*  128 */       if (Trace.isOn) {
/*  129 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.CacheProperty", "clear()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     void setValue(Object value) {
/*  135 */       if (Trace.isOn) {
/*  136 */         Trace.data(this, "com.ibm.msg.client.wmq.common.internal.CacheProperty", "setValue(Object)", "setter", value);
/*      */       }
/*      */       
/*  139 */       this.value = value;
/*  140 */       this.exists = true;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  150 */   private static final Pattern validNamePattern = Pattern.compile("[A-Za-z0-9/._%]*");
/*      */ 
/*      */   
/*  153 */   private static final Pattern validQmgrNamePattern = Pattern.compile("[A-Za-z0-9/._%*]*");
/*      */ 
/*      */   
/*  156 */   private static final Pattern durPubSubQPattern = Pattern.compile("SYSTEM\\.(JMS|BROKER)\\.[A-Za-z0-9/._%]*\\.\\*");
/*      */   
/*      */   private boolean hasBeenDeleted;
/*      */   private boolean isTemporary;
/*      */   protected WMQCommonConnection connection;
/*      */   private int destType;
/*      */   
/*      */   static class WMQAdminObjectPropertyValidator
/*      */     extends WMQStandardValidators.WMQTrivialPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 64038312559603860L;
/*  167 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(1, 1.0F);
/*      */     static {
/*  169 */       if (Trace.isOn) {
/*  170 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQAdminObjectPropertyValidator", "static()");
/*      */       }
/*      */       
/*  173 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_ADMIN_OBJECT_TYPE");
/*  174 */       if (Trace.isOn) {
/*  175 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQAdminObjectPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/*  181 */       if (Trace.isOn) {
/*  182 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQAdminObjectPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/*  185 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/*  186 */         propertyValidators.put(entry.getValue(), new WMQAdminObjectPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/*  188 */       if (Trace.isOn) {
/*  189 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQAdminObjectPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQAdminObjectPropertyValidator(int domain) {
/*  197 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/*  202 */       if (Trace.isOn) {
/*  203 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQAdminObjectPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  209 */       if (Trace.isOn) {
/*  210 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQAdminObjectPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQAlternateUserIdPropertyValidator
/*      */     extends WMQStandardValidators.WMQStringPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 1L;
/*      */ 
/*      */ 
/*      */     
/*  226 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/*  228 */       if (Trace.isOn) {
/*  229 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQAlternateUserIdPropertyValidator", "static()");
/*      */       }
/*      */       
/*  232 */       domainsToPropertyNames.put(Integer.valueOf(4), "alternateUserId");
/*  233 */       domainsToPropertyNames.put(Integer.valueOf(1), "ALTERNATEUSERID");
/*  234 */       domainsToPropertyNames.put(Integer.valueOf(2), "ALTU");
/*  235 */       if (Trace.isOn) {
/*  236 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQAlternateUserIdPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/*  242 */       if (Trace.isOn) {
/*  243 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQAlternateUserIdPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/*  246 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/*  247 */         propertyValidators.put(entry.getValue(), new WMQAlternateUserIdPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/*  249 */       if (Trace.isOn) {
/*  250 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQAlternateUserIdPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQAlternateUserIdPropertyValidator(int domain) {
/*  258 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/*  263 */       if (Trace.isOn) {
/*  264 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQAlternateUserIdPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  270 */       if (Trace.isOn) {
/*  271 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQAlternateUserIdPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQCCSIDPropertyValidator
/*      */     extends WMQStandardValidators.WMQCcsidPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -8899138887128414268L;
/*      */ 
/*      */ 
/*      */     
/*  286 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/*  288 */       if (Trace.isOn) {
/*  289 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQCCSIDPropertyValidator", "static()");
/*      */       }
/*  291 */       domainsToPropertyNames.put(Integer.valueOf(4), "CCSID");
/*  292 */       domainsToPropertyNames.put(Integer.valueOf(1), "CCSID");
/*  293 */       domainsToPropertyNames.put(Integer.valueOf(2), "CCS");
/*  294 */       if (Trace.isOn) {
/*  295 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQCCSIDPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/*  300 */       if (Trace.isOn) {
/*  301 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQCCSIDPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/*  304 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/*  305 */         propertyValidators.put(entry.getValue(), new WMQCCSIDPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/*  307 */       if (Trace.isOn) {
/*  308 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQCCSIDPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQCCSIDPropertyValidator(int domain) {
/*  316 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean crossPropertyValidate(Object parent) throws JMSException {
/*  321 */       if (Trace.isOn) {
/*  322 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQCCSIDPropertyValidator", "crossPropertyValidate(Object)", new Object[] { parent });
/*      */       }
/*      */       
/*  325 */       if (((JmsPropertyContext)parent)
/*  326 */         .getIntProperty("CCSID") != 1208 && (
/*  327 */         (JmsPropertyContext)parent)
/*  328 */         .getIntProperty("XMSC_WMQ_CONNECTION_MODE") == 0) {
/*  329 */         if (Trace.isOn) {
/*  330 */           Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQCCSIDPropertyValidator", "crossPropertyValidate(Object)", 
/*      */               
/*  332 */               Boolean.valueOf(false), 1);
/*      */         }
/*  334 */         return false;
/*      */       } 
/*      */       
/*  337 */       if (Trace.isOn) {
/*  338 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQCCSIDPropertyValidator", "crossPropertyValidate(Object)", 
/*  339 */             Boolean.valueOf(true), 2);
/*      */       }
/*  341 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object ccsid) throws JMSException {
/*  346 */       if (Trace.isOn) {
/*  347 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQCCSIDPropertyValidator", "throwBadValueException(Object)", new Object[] { ccsid });
/*      */       }
/*      */       
/*  350 */       HashMap<String, Object> inserts = new HashMap<>();
/*  351 */       inserts.put("XMSC_INSERT_PROPERTY", this.applicablePropertyName);
/*      */       
/*  353 */       inserts.put("XMSC_INSERT_VALUE", 
/*  354 */           String.valueOf(ccsid));
/*      */       
/*  356 */       JMSException je = (JMSException)NLSServices.createException("JMSCMQ0004", inserts);
/*      */ 
/*      */       
/*  359 */       if (Trace.isOn) {
/*  360 */         Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.WMQCCSIDPropertyValidator", "throwBadValueException(Object)", (Throwable)je);
/*      */       }
/*      */ 
/*      */       
/*  364 */       throw je;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQUnmappableActionPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -4259307989747389596L;
/*      */     
/*  375 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/*  377 */       if (Trace.isOn) {
/*  378 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQUnmappableActionPropertyValidator", "static()");
/*      */       }
/*      */       
/*  381 */       domainsToPropertyNames.put(Integer.valueOf(4), "JMS_IBM_Unmappable_Action");
/*  382 */       domainsToPropertyNames.put(Integer.valueOf(1), "UNMAPPABLEACTION");
/*  383 */       domainsToPropertyNames.put(Integer.valueOf(2), "UMA");
/*  384 */       if (Trace.isOn) {
/*  385 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQUnmappableActionPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/*  391 */       if (Trace.isOn) {
/*  392 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQUnmappableActionPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/*  395 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/*  396 */         propertyValidators.put(entry.getValue(), new WMQUnmappableActionPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/*  398 */       if (Trace.isOn) {
/*  399 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQUnmappableActionPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  405 */     private static HashMap<Object, Object> valuesToCanonical = new WMQStandardValidators.CaseInsentiveHashmap();
/*  406 */     private static Set<Object> validValues = new HashSet(2, 1.0F);
/*      */     static {
/*  408 */       if (Trace.isOn) {
/*  409 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQUnmappableActionPropertyValidator", "static()");
/*      */       }
/*      */       
/*  412 */       valuesToCanonical.put("REPLACE", CodingErrorAction.REPLACE.toString());
/*  413 */       valuesToCanonical.put("REPORT", CodingErrorAction.REPORT.toString());
/*  414 */       valuesToCanonical.put("IGNORE", CodingErrorAction.IGNORE.toString());
/*  415 */       validValues.addAll(valuesToCanonical.values());
/*  416 */       if (Trace.isOn) {
/*  417 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQUnmappableActionPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQUnmappableActionPropertyValidator(int domain) {
/*  424 */       super(domain, domainsToPropertyNames, valuesToCanonical);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/*  430 */       if (Trace.isOn) {
/*  431 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQUnmappableActionPropertyValidator", "validate(Object,Object)", new Object[] { value, parent });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  436 */       if (!validValues.contains(value)) {
/*  437 */         throwBadValueException(value);
/*      */       }
/*  439 */       if (Trace.isOn) {
/*  440 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQUnmappableActionPropertyValidator", "validate(Object,Object)", 
/*      */             
/*  442 */             Boolean.valueOf(true));
/*      */       }
/*  444 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object unmappableAction) throws JMSException {
/*  449 */       if (Trace.isOn) {
/*  450 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQUnmappableActionPropertyValidator", "throwBadValueException(Object)", new Object[] { unmappableAction });
/*      */       }
/*      */ 
/*      */       
/*  454 */       HashMap<String, Object> inserts = new HashMap<>();
/*  455 */       inserts.put("XMSC_INSERT_PROPERTY", this.applicablePropertyName);
/*      */       
/*  457 */       inserts.put("XMSC_INSERT_VALUE", 
/*  458 */           String.valueOf(unmappableAction));
/*      */       
/*  460 */       JMSException je = (JMSException)NLSServices.createException("JMSCMQ0004", inserts);
/*      */ 
/*      */       
/*  463 */       if (Trace.isOn) {
/*  464 */         Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.WMQUnmappableActionPropertyValidator", "throwBadValueException(Object)", (Throwable)je);
/*      */       }
/*      */ 
/*      */       
/*  468 */       throw je;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static class WMQUnmappableReplacementPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -4259307989747389596L;
/*      */     
/*  478 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/*  480 */       if (Trace.isOn) {
/*  481 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQUnmappableReplacementPropertyValidator", "static()");
/*      */       }
/*      */ 
/*      */       
/*  485 */       domainsToPropertyNames.put(Integer.valueOf(4), "JMS_IBM_Unmappable_Replacement");
/*  486 */       domainsToPropertyNames.put(Integer.valueOf(1), "UNMAPPABLEREPLACEMENT");
/*  487 */       domainsToPropertyNames.put(Integer.valueOf(2), "UMR");
/*  488 */       if (Trace.isOn) {
/*  489 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQUnmappableReplacementPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*  494 */     private static HashMap<Object, Object> valuesToCanonical = new WMQStandardValidators.CaseInsentiveHashmap();
/*  495 */     private static Set<Object> validValues = new HashSet(256, 1.0F);
/*      */     static {
/*  497 */       if (Trace.isOn) {
/*  498 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQUnmappableReplacementPropertyValidator", "static()");
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  504 */       for (short b = 0; b <= 255; b = (short)(b + 1)) {
/*  505 */         valuesToCanonical.put(Byte.valueOf((byte)b), Byte.valueOf((byte)b));
/*      */       }
/*  507 */       validValues.addAll(valuesToCanonical.values());
/*  508 */       if (Trace.isOn) {
/*  509 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQUnmappableReplacementPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/*  516 */       if (Trace.isOn) {
/*  517 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQUnmappableReplacementPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */ 
/*      */       
/*  521 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/*  522 */         propertyValidators.put(entry.getValue(), new WMQUnmappableReplacementPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/*  524 */       if (Trace.isOn) {
/*  525 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQUnmappableReplacementPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQUnmappableReplacementPropertyValidator(int domain) {
/*  534 */       super(domain, domainsToPropertyNames, valuesToCanonical);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/*  540 */       if (Trace.isOn) {
/*  541 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQUnmappableReplacementPropertyValidator", "validate(Object,Object)", new Object[] { value, parent });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  546 */       if (!(value instanceof Byte) || !validValues.contains(value)) {
/*  547 */         throwBadValueException(value);
/*      */       }
/*  549 */       if (Trace.isOn) {
/*  550 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQUnmappableReplacementPropertyValidator", "validate(Object,Object)", 
/*      */             
/*  552 */             Boolean.valueOf(true));
/*      */       }
/*  554 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object unmappableReplacement) throws JMSException {
/*  559 */       if (Trace.isOn) {
/*  560 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQUnmappableReplacementPropertyValidator", "throwBadValueException(Object)", new Object[] { unmappableReplacement });
/*      */       }
/*      */ 
/*      */       
/*  564 */       HashMap<String, Object> inserts = new HashMap<>();
/*  565 */       inserts.put("XMSC_INSERT_PROPERTY", this.applicablePropertyName);
/*      */       
/*  567 */       inserts.put("XMSC_INSERT_VALUE", 
/*  568 */           String.valueOf(unmappableReplacement));
/*      */       
/*  570 */       JMSException je = (JMSException)NLSServices.createException("JMSCMQ0004", inserts);
/*      */ 
/*      */       
/*  573 */       if (Trace.isOn) {
/*  574 */         Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.WMQUnmappableReplacementPropertyValidator", "throwBadValueException(Object)", (Throwable)je);
/*      */       }
/*      */ 
/*      */       
/*  578 */       throw je;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQConnectionTypeNamePropertyValidator
/*      */     extends WMQStandardValidators.WMQStringPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 4057776201748947687L;
/*      */ 
/*      */     
/*  591 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(1, 1.0F);
/*      */     static {
/*  593 */       if (Trace.isOn) {
/*  594 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQConnectionTypeNamePropertyValidator", "static()");
/*      */       }
/*      */ 
/*      */       
/*  598 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_CONNECTION_TYPE_NAME");
/*  599 */       if (Trace.isOn) {
/*  600 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQConnectionTypeNamePropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/*  607 */       if (Trace.isOn) {
/*  608 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQConnectionTypeNamePropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */ 
/*      */       
/*  612 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/*  613 */         propertyValidators.put(entry.getValue(), new WMQConnectionTypeNamePropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/*  615 */       if (Trace.isOn) {
/*  616 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQConnectionTypeNamePropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQConnectionTypeNamePropertyValidator(int domain) {
/*  625 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/*  630 */       if (Trace.isOn) {
/*  631 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQConnectionTypeNamePropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  637 */       if (Trace.isOn) {
/*  638 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQConnectionTypeNamePropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQConnectionTypePropertyValidator
/*      */     extends WMQStandardValidators.WMQIntPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -4767085469361870248L;
/*      */ 
/*      */ 
/*      */     
/*  654 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(1, 1.0F);
/*      */     static {
/*  656 */       if (Trace.isOn) {
/*  657 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQConnectionTypePropertyValidator", "static()");
/*      */       }
/*      */       
/*  660 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_CONNECTION_TYPE");
/*  661 */       if (Trace.isOn) {
/*  662 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQConnectionTypePropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/*  668 */       if (Trace.isOn) {
/*  669 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQConnectionTypePropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/*  672 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/*  673 */         propertyValidators.put(entry.getValue(), new WMQConnectionTypePropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/*  675 */       if (Trace.isOn) {
/*  676 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQConnectionTypePropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQConnectionTypePropertyValidator(int domain) {
/*  684 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/*  689 */       if (Trace.isOn) {
/*  690 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQConnectionTypePropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  696 */       if (Trace.isOn) {
/*  697 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQConnectionTypePropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQDestDescriptionPropertyValidator
/*      */     extends WMQStandardValidators.WMQStringPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -9060363910500947331L;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  714 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/*  716 */       if (Trace.isOn) {
/*  717 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQDestDescriptionPropertyValidator", "static()");
/*      */       }
/*      */       
/*  720 */       domainsToPropertyNames.put(Integer.valueOf(4), "destDescription");
/*  721 */       domainsToPropertyNames.put(Integer.valueOf(1), "DESCRIPTION");
/*  722 */       domainsToPropertyNames.put(Integer.valueOf(2), "DESC");
/*  723 */       if (Trace.isOn) {
/*  724 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQDestDescriptionPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/*  730 */       if (Trace.isOn) {
/*  731 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQDestDescriptionPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/*  734 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/*  735 */         propertyValidators.put(entry.getValue(), new WMQDestDescriptionPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/*  737 */       if (Trace.isOn) {
/*  738 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQDestDescriptionPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQDestDescriptionPropertyValidator(int domain) {
/*  746 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/*  751 */       if (Trace.isOn) {
/*  752 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQDestDescriptionPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  758 */       if (Trace.isOn) {
/*  759 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestDescriptionPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQQueueManagerNamePropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -6200113082501515211L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  781 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/*  783 */       if (Trace.isOn) {
/*  784 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQQueueManagerNamePropertyValidator", "static()");
/*      */       }
/*      */       
/*  787 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_QUEUE_MANAGER");
/*  788 */       domainsToPropertyNames.put(Integer.valueOf(1), "QMANAGER");
/*  789 */       domainsToPropertyNames.put(Integer.valueOf(2), "QMGR");
/*  790 */       if (Trace.isOn) {
/*  791 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQQueueManagerNamePropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/*  797 */       if (Trace.isOn) {
/*  798 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQQueueManagerNamePropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/*  801 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/*  802 */         propertyValidators.put(entry.getValue(), new WMQQueueManagerNamePropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/*  804 */       if (Trace.isOn) {
/*  805 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQQueueManagerNamePropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQQueueManagerNamePropertyValidator(int domain) {
/*  813 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/*  821 */       return validateString(value, (Pattern)null, WMQDestination.validNamePattern, 48, false);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/*  826 */       if (Trace.isOn) {
/*  827 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQQueueManagerNamePropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  833 */       if (Trace.isOn) {
/*  834 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQQueueManagerNamePropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQDestinationNamePropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 6012851027361876199L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  855 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/*  857 */       if (Trace.isOn) {
/*  858 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQDestinationNamePropertyValidator", "static()");
/*      */       }
/*      */       
/*  861 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_DESTINATION_NAME");
/*  862 */       domainsToPropertyNames.put(Integer.valueOf(1), "DESTNAME");
/*  863 */       domainsToPropertyNames.put(Integer.valueOf(2), "NAME");
/*  864 */       if (Trace.isOn) {
/*  865 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQDestinationNamePropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/*  871 */       if (Trace.isOn) {
/*  872 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQDestinationNamePropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/*  875 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/*  876 */         propertyValidators.put(entry.getValue(), new WMQDestinationNamePropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/*  878 */       if (Trace.isOn) {
/*  879 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQDestinationNamePropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  885 */     static final Pattern queueNamePattern = Pattern.compile("queue://.+|topic://.+", 2);
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQDestinationNamePropertyValidator(int domain) {
/*  890 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/*  898 */       if (value == null) {
/*  899 */         return false;
/*      */       }
/*      */ 
/*      */       
/*  903 */       if (parent instanceof com.ibm.msg.client.jms.JmsTopic) {
/*  904 */         return true;
/*      */       }
/*      */       
/*  907 */       return validateString(value, queueNamePattern, WMQDestination.validNamePattern, 48, false);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/*  912 */       if (Trace.isOn) {
/*  913 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationNamePropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  919 */       if (Trace.isOn) {
/*  920 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestinationNamePropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQEncodingPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 2941571030984413932L;
/*      */ 
/*      */ 
/*      */     
/*  935 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/*  937 */       if (Trace.isOn) {
/*  938 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQEncodingPropertyValidator", "static()");
/*      */       }
/*      */       
/*  941 */       domainsToPropertyNames.put(Integer.valueOf(4), "encoding");
/*  942 */       domainsToPropertyNames.put(Integer.valueOf(1), "ENCODING");
/*  943 */       domainsToPropertyNames.put(Integer.valueOf(2), "ENC");
/*  944 */       if (Trace.isOn) {
/*  945 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQEncodingPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/*  951 */       if (Trace.isOn) {
/*  952 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQEncodingPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/*  955 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/*  956 */         propertyValidators.put(entry.getValue(), new WMQEncodingPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/*  958 */       if (Trace.isOn) {
/*  959 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQEncodingPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  965 */     private static HashMap<Object, Object> valuesToCanonical = new WMQStandardValidators.CaseInsentiveHashmap();
/*      */     static {
/*  967 */       if (Trace.isOn) {
/*  968 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQEncodingPropertyValidator", "static()");
/*      */       }
/*      */       
/*  971 */       valuesToCanonical.put("NATIVE", 
/*  972 */           Integer.valueOf(273));
/*  973 */       valuesToCanonical.put("N", 
/*  974 */           Integer.valueOf(1));
/*  975 */       valuesToCanonical.put("R", 
/*  976 */           Integer.valueOf(2));
/*  977 */       valuesToCanonical.put("3", 
/*  978 */           Integer.valueOf(768));
/*  979 */       if (Trace.isOn) {
/*  980 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQEncodingPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQEncodingPropertyValidator(int domain) {
/*  987 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object valueP, Object parent) throws JMSException {
/*  993 */       if (Trace.isOn) {
/*  994 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQEncodingPropertyValidator", "validate(Object,Object)", new Object[] { valueP, parent });
/*      */       }
/*      */ 
/*      */       
/*  998 */       Object value = valueP;
/*      */       
/* 1000 */       if (!((value instanceof Integer || value instanceof Long) ? 1 : 0)) {
/* 1001 */         value = convertToString(value);
/*      */       }
/*      */       
/* 1004 */       if (value instanceof String) {
/* 1005 */         if (this.mapperDomain == 1 || this.mapperDomain == 2) {
/* 1006 */           if (valuesToCanonical.containsKey(value)) {
/* 1007 */             value = valuesToCanonical.get(value);
/*      */           } else {
/*      */             
/* 1010 */             if (Trace.isOn) {
/* 1011 */               Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQEncodingPropertyValidator", "validate(Object,Object)", 
/*      */                   
/* 1013 */                   Boolean.valueOf(false), 1);
/*      */             }
/* 1015 */             return false;
/*      */           } 
/*      */         } else {
/*      */           
/*      */           try {
/* 1020 */             value = Integer.valueOf((String)value);
/*      */           }
/* 1022 */           catch (NumberFormatException e) {
/* 1023 */             if (Trace.isOn) {
/* 1024 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.common.internal.WMQEncodingPropertyValidator", "validate(Object,Object)", e);
/*      */             }
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1030 */             if (Trace.isOn) {
/* 1031 */               Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQEncodingPropertyValidator", "validate(Object,Object)", 
/*      */                   
/* 1033 */                   Boolean.valueOf(false), 2);
/*      */             }
/* 1035 */             return false;
/*      */           } 
/*      */         } 
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1043 */       if (value instanceof Integer || value instanceof Long) {
/*      */         int encoding;
/* 1045 */         if (value instanceof Long) {
/* 1046 */           encoding = ((Long)value).intValue();
/*      */         } else {
/*      */           
/* 1049 */           encoding = ((Integer)value).intValue();
/*      */         } 
/*      */         
/* 1052 */         int mask = 819;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1059 */         if ((encoding & (mask ^ 0xFFFFFFFF)) != 0) {
/* 1060 */           HashMap<String, Object> inserts = new HashMap<>();
/* 1061 */           inserts.put("XMSC_INSERT_PROPERTY", this.applicablePropertyName);
/*      */           
/* 1063 */           inserts.put("XMSC_INSERT_VALUE", 
/* 1064 */               Integer.toHexString(encoding));
/*      */           
/* 1066 */           JMSException je = (JMSException)NLSServices.createException("JMSCMQ0004", inserts);
/*      */ 
/*      */           
/* 1069 */           if (Trace.isOn) {
/* 1070 */             Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.WMQEncodingPropertyValidator", "validate(Object,Object)", (Throwable)je);
/*      */           }
/*      */ 
/*      */           
/* 1074 */           throw je;
/*      */         } 
/* 1076 */         if (Trace.isOn) {
/* 1077 */           Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQEncodingPropertyValidator", "validate(Object,Object)", 
/* 1078 */               Boolean.valueOf(true), 3);
/*      */         }
/* 1080 */         return true;
/*      */       } 
/*      */       
/* 1083 */       if (Trace.isOn) {
/* 1084 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQEncodingPropertyValidator", "validate(Object,Object)", 
/* 1085 */             Boolean.valueOf(false), 4);
/*      */       }
/* 1087 */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 1092 */       if (Trace.isOn) {
/* 1093 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQEncodingPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1098 */       if (Trace.isOn) {
/* 1099 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQEncodingPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQFailIfQuiescePropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 671057721715729251L;
/*      */ 
/*      */     
/* 1113 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 1115 */       if (Trace.isOn) {
/* 1116 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQFailIfQuiescePropertyValidator", "static()");
/*      */       }
/*      */       
/* 1119 */       domainsToPropertyNames.put(Integer.valueOf(4), "failIfQuiesce");
/* 1120 */       domainsToPropertyNames.put(Integer.valueOf(1), "FAILIFQUIESCE");
/* 1121 */       domainsToPropertyNames.put(Integer.valueOf(2), "FIQ");
/* 1122 */       if (Trace.isOn) {
/* 1123 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQFailIfQuiescePropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 1129 */       if (Trace.isOn) {
/* 1130 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQFailIfQuiescePropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 1133 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 1134 */         propertyValidators.put(entry.getValue(), new WMQFailIfQuiescePropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 1136 */       if (Trace.isOn) {
/* 1137 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQFailIfQuiescePropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1143 */     private static HashMap<Object, Object> valuesToCanonical = new WMQStandardValidators.CaseInsentiveHashmap();
/* 1144 */     private static Set<Object> validValues = new HashSet(2, 1.0F);
/*      */     static {
/* 1146 */       if (Trace.isOn) {
/* 1147 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQFailIfQuiescePropertyValidator", "static()");
/*      */       }
/*      */       
/* 1150 */       valuesToCanonical.put("YES", Integer.valueOf(1));
/* 1151 */       valuesToCanonical.put("NO", Integer.valueOf(0));
/* 1152 */       validValues.addAll(valuesToCanonical.values());
/* 1153 */       if (Trace.isOn) {
/* 1154 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQFailIfQuiescePropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQFailIfQuiescePropertyValidator(int domain) {
/* 1161 */       super(domain, domainsToPropertyNames, valuesToCanonical);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/* 1168 */       return validateIntBySet(value, validValues);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object fiq) throws JMSException {
/* 1173 */       if (Trace.isOn) {
/* 1174 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQFailIfQuiescePropertyValidator", "throwBadValueException(Object)", new Object[] { fiq });
/*      */       }
/*      */ 
/*      */       
/* 1178 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1179 */       inserts.put("XMSC_INSERT_PROPERTY", this.applicablePropertyName);
/*      */       
/* 1181 */       inserts.put("XMSC_INSERT_VALUE", 
/* 1182 */           String.valueOf(fiq));
/*      */       
/* 1184 */       JMSException je = (JMSException)NLSServices.createException("JMSCMQ0004", inserts);
/*      */ 
/*      */       
/* 1187 */       if (Trace.isOn) {
/* 1188 */         Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.WMQFailIfQuiescePropertyValidator", "throwBadValueException(Object)", (Throwable)je);
/*      */       }
/*      */ 
/*      */       
/* 1192 */       throw je;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQMessageContextPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 8485830106901109460L;
/*      */ 
/*      */     
/* 1205 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 1207 */       if (Trace.isOn) {
/* 1208 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQMessageContextPropertyValidator", "static()");
/*      */       }
/*      */       
/* 1211 */       domainsToPropertyNames.put(Integer.valueOf(4), "mdMessageContext");
/* 1212 */       domainsToPropertyNames.put(Integer.valueOf(1), "MDMSGCTX");
/* 1213 */       domainsToPropertyNames.put(Integer.valueOf(2), "MDCTX");
/* 1214 */       if (Trace.isOn) {
/* 1215 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQMessageContextPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/* 1220 */     private static HashMap<Object, Object> valuesToCanonical = new WMQStandardValidators.CaseInsentiveHashmap();
/* 1221 */     private static Set<Object> validValues = new HashSet(3, 1.0F);
/*      */     static {
/* 1223 */       if (Trace.isOn) {
/* 1224 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQMessageContextPropertyValidator", "static()");
/*      */       }
/*      */ 
/*      */       
/* 1228 */       valuesToCanonical.put("DEFAULT", Integer.valueOf(0));
/* 1229 */       valuesToCanonical.put("SET_IDENTITY_CONTEXT", Integer.valueOf(1));
/* 1230 */       valuesToCanonical.put("SET_ALL_CONTEXT", Integer.valueOf(2));
/*      */       
/* 1232 */       validValues.addAll(valuesToCanonical.values());
/* 1233 */       if (Trace.isOn) {
/* 1234 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQMessageContextPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 1241 */       if (Trace.isOn) {
/* 1242 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQMessageContextPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 1245 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 1246 */         propertyValidators.put(entry.getValue(), new WMQMessageContextPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 1248 */       if (Trace.isOn) {
/* 1249 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQMessageContextPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQMessageContextPropertyValidator(int domain) {
/* 1257 */       super(domain, domainsToPropertyNames, valuesToCanonical);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/* 1264 */       return validateIntBySet(value, validValues);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object messageContext) throws JMSException {
/* 1270 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1271 */       inserts.put("XMSC_INSERT_PROPERTY", this.applicablePropertyName);
/*      */       
/* 1273 */       inserts.put("XMSC_INSERT_VALUE", 
/* 1274 */           String.valueOf(messageContext));
/*      */       
/* 1276 */       JMSException je = (JMSException)NLSServices.createException("JMSCMQ0004", inserts);
/*      */ 
/*      */       
/* 1279 */       throw je;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQmdWriteEnabledPropertyValidator
/*      */     extends WMQStandardValidators.WMQBooleanPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 5423523221173433204L;
/*      */ 
/*      */ 
/*      */     
/* 1293 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 1295 */       if (Trace.isOn) {
/* 1296 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQmdWriteEnabledPropertyValidator", "static()");
/*      */       }
/*      */       
/* 1299 */       domainsToPropertyNames.put(Integer.valueOf(4), "mdWriteEnabled");
/* 1300 */       domainsToPropertyNames.put(Integer.valueOf(1), "MDWRITE");
/* 1301 */       domainsToPropertyNames.put(Integer.valueOf(2), "MDW");
/* 1302 */       if (Trace.isOn) {
/* 1303 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQmdWriteEnabledPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 1309 */       if (Trace.isOn) {
/* 1310 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQmdWriteEnabledPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 1313 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 1314 */         propertyValidators.put(entry.getValue(), new WMQmdWriteEnabledPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 1316 */       if (Trace.isOn) {
/* 1317 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQmdWriteEnabledPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQmdWriteEnabledPropertyValidator(int domain) {
/* 1325 */       super(domain, domainsToPropertyNames, yesNoToBoolean);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 1330 */       if (Trace.isOn) {
/* 1331 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQmdWriteEnabledPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1337 */       if (Trace.isOn) {
/* 1338 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQmdWriteEnabledPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQmdReadEnabledPropertyValidator
/*      */     extends WMQStandardValidators.WMQBooleanPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 858442200305589834L;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1355 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 1357 */       if (Trace.isOn) {
/* 1358 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQmdReadEnabledPropertyValidator", "static()");
/*      */       }
/*      */       
/* 1361 */       domainsToPropertyNames.put(Integer.valueOf(4), "mdReadEnabled");
/* 1362 */       domainsToPropertyNames.put(Integer.valueOf(1), "MDREAD");
/* 1363 */       domainsToPropertyNames.put(Integer.valueOf(2), "MDR");
/* 1364 */       if (Trace.isOn) {
/* 1365 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQmdReadEnabledPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 1371 */       if (Trace.isOn) {
/* 1372 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQmdReadEnabledPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 1375 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 1376 */         propertyValidators.put(entry.getValue(), new WMQmdReadEnabledPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 1378 */       if (Trace.isOn) {
/* 1379 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQmdReadEnabledPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQmdReadEnabledPropertyValidator(int domain) {
/* 1387 */       super(domain, domainsToPropertyNames, yesNoToBoolean);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 1392 */       if (Trace.isOn) {
/* 1393 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQmdReadEnabledPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1399 */       if (Trace.isOn) {
/* 1400 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQmdReadEnabledPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQMessageBodyStylePropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 1053052193028384899L;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1417 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 1419 */       if (Trace.isOn) {
/* 1420 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQMessageBodyStylePropertyValidator", "static()");
/*      */       }
/*      */       
/* 1423 */       domainsToPropertyNames.put(Integer.valueOf(4), "messageBody");
/* 1424 */       domainsToPropertyNames.put(Integer.valueOf(1), "MSGBODY");
/* 1425 */       domainsToPropertyNames.put(Integer.valueOf(2), "MBODY");
/* 1426 */       if (Trace.isOn) {
/* 1427 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQMessageBodyStylePropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 1433 */       if (Trace.isOn) {
/* 1434 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQMessageBodyStylePropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 1437 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 1438 */         propertyValidators.put(entry.getValue(), new WMQMessageBodyStylePropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 1440 */       if (Trace.isOn) {
/* 1441 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQMessageBodyStylePropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1447 */     private static HashMap<Object, Object> valuesToCanonical = new WMQStandardValidators.CaseInsentiveHashmap();
/* 1448 */     private static Set<Object> validValues = new HashSet(3, 1.0F);
/*      */     static {
/* 1450 */       if (Trace.isOn) {
/* 1451 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQMessageBodyStylePropertyValidator", "static()");
/*      */       }
/*      */       
/* 1454 */       valuesToCanonical.put("JMS", 
/* 1455 */           Integer.valueOf(0));
/* 1456 */       valuesToCanonical.put("MQ", 
/* 1457 */           Integer.valueOf(1));
/* 1458 */       valuesToCanonical.put("UNSPECIFIED", 
/* 1459 */           Integer.valueOf(2));
/* 1460 */       validValues.addAll(valuesToCanonical.values());
/* 1461 */       if (Trace.isOn) {
/* 1462 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQMessageBodyStylePropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQMessageBodyStylePropertyValidator(int domain) {
/* 1469 */       super(domain, domainsToPropertyNames, valuesToCanonical);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/* 1476 */       return validateIntBySet(value, validValues);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object messageBodyStyle) throws JMSException {
/* 1481 */       if (Trace.isOn) {
/* 1482 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQMessageBodyStylePropertyValidator", "throwBadValueException(Object)", new Object[] { messageBodyStyle });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1487 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1488 */       inserts.put("XMSC_INSERT_PROPERTY", this.applicablePropertyName);
/*      */       
/* 1490 */       inserts.put("XMSC_INSERT_VALUE", 
/* 1491 */           String.valueOf(messageBodyStyle));
/*      */       
/* 1493 */       JMSException je = (JMSException)NLSServices.createException("JMSCMQ0004", inserts);
/*      */ 
/*      */       
/* 1496 */       if (Trace.isOn) {
/* 1497 */         Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.WMQMessageBodyStylePropertyValidator", "throwBadValueException(Object)", (Throwable)je);
/*      */       }
/*      */ 
/*      */       
/* 1501 */       throw je;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQDeliveryModePropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 916546588885471908L;
/*      */     
/* 1512 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(4, 1.0F);
/*      */     static {
/* 1514 */       if (Trace.isOn) {
/* 1515 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQDeliveryModePropertyValidator", "static()");
/*      */       }
/*      */       
/* 1518 */       domainsToPropertyNames.put(Integer.valueOf(4), "deliveryMode");
/* 1519 */       domainsToPropertyNames.put(Integer.valueOf(1), "PERSISTENCE");
/* 1520 */       domainsToPropertyNames.put(Integer.valueOf(2), "PER");
/* 1521 */       domainsToPropertyNames.put(Integer.valueOf(3), "persistence");
/* 1522 */       if (Trace.isOn) {
/* 1523 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQDeliveryModePropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 1529 */       if (Trace.isOn) {
/* 1530 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQDeliveryModePropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 1533 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 1534 */         propertyValidators.put(entry.getValue(), new WMQDeliveryModePropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 1536 */       if (Trace.isOn) {
/* 1537 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQDeliveryModePropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1543 */     private static HashMap<Object, Object> valuesToCanonical = new WMQStandardValidators.CaseInsentiveHashmap();
/* 1544 */     private static Set<Object> validValues = new HashSet(5, 1.0F);
/*      */     static {
/* 1546 */       if (Trace.isOn) {
/* 1547 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQDeliveryModePropertyValidator", "static()");
/*      */       }
/*      */       
/* 1550 */       valuesToCanonical.put("APP", Integer.valueOf(-2));
/* 1551 */       valuesToCanonical.put("QDEF", 
/* 1552 */           Integer.valueOf(-1));
/* 1553 */       valuesToCanonical
/* 1554 */         .put("PERS", Integer.valueOf(2));
/* 1555 */       valuesToCanonical.put("NON", Integer.valueOf(1));
/* 1556 */       valuesToCanonical.put("HIGH", 
/* 1557 */           Integer.valueOf(3));
/* 1558 */       validValues.addAll(valuesToCanonical.values());
/* 1559 */       if (Trace.isOn) {
/* 1560 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQDeliveryModePropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQDeliveryModePropertyValidator(int domain) {
/* 1567 */       super(domain, domainsToPropertyNames, valuesToCanonical);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/* 1573 */       if (Trace.isOn) {
/* 1574 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQDeliveryModePropertyValidator", "validate(Object,Object)", new Object[] { value, parent });
/*      */       }
/*      */ 
/*      */       
/* 1578 */       boolean traceRet1 = validateIntBySet(value, validValues);
/* 1579 */       if (Trace.isOn) {
/* 1580 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDeliveryModePropertyValidator", "validate(Object,Object)", 
/*      */             
/* 1582 */             Boolean.valueOf(traceRet1));
/*      */       }
/* 1584 */       return traceRet1;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object deliveryMode) throws JMSException {
/* 1590 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1591 */       inserts.put("XMSC_INSERT_PROPERTY", this.applicablePropertyName);
/*      */       
/* 1593 */       inserts.put("XMSC_INSERT_VALUE", 
/* 1594 */           String.valueOf(deliveryMode));
/*      */       
/* 1596 */       JMSException je = (JMSException)NLSServices.createException("JMSCMQ0004", inserts);
/*      */ 
/*      */       
/* 1599 */       throw je;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQPriorityPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 3172310166239305786L;
/*      */     
/* 1610 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 1612 */       if (Trace.isOn) {
/* 1613 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQPriorityPropertyValidator", "static()");
/*      */       }
/*      */       
/* 1616 */       domainsToPropertyNames.put(Integer.valueOf(4), "priority");
/* 1617 */       domainsToPropertyNames.put(Integer.valueOf(1), "PRIORITY");
/* 1618 */       domainsToPropertyNames.put(Integer.valueOf(2), "PRI");
/* 1619 */       if (Trace.isOn) {
/* 1620 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQPriorityPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 1626 */       if (Trace.isOn) {
/* 1627 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQPriorityPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 1630 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 1631 */         propertyValidators.put(entry.getValue(), new WMQPriorityPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 1633 */       if (Trace.isOn) {
/* 1634 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQPriorityPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1640 */     private static HashMap<Object, Object> valuesToCanonical = new WMQStandardValidators.CaseInsentiveHashmap();
/* 1641 */     private static Set<Object> validValues = new HashSet(12, 1.0F);
/*      */     static {
/* 1643 */       if (Trace.isOn) {
/* 1644 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQPriorityPropertyValidator", "static()");
/*      */       }
/*      */       
/* 1647 */       valuesToCanonical.put("APP", Integer.valueOf(-2));
/* 1648 */       valuesToCanonical.put("QDEF", 
/* 1649 */           Integer.valueOf(-1));
/*      */ 
/*      */       
/* 1652 */       valuesToCanonical.put("0", Integer.valueOf(0));
/* 1653 */       valuesToCanonical.put("1", Integer.valueOf(1));
/* 1654 */       valuesToCanonical.put("2", Integer.valueOf(2));
/* 1655 */       valuesToCanonical.put("3", Integer.valueOf(3));
/* 1656 */       valuesToCanonical.put("4", Integer.valueOf(4));
/* 1657 */       valuesToCanonical.put("5", Integer.valueOf(5));
/* 1658 */       valuesToCanonical.put("6", Integer.valueOf(6));
/* 1659 */       valuesToCanonical.put("7", Integer.valueOf(7));
/* 1660 */       valuesToCanonical.put("8", Integer.valueOf(8));
/* 1661 */       valuesToCanonical.put("9", Integer.valueOf(9));
/* 1662 */       validValues.addAll(valuesToCanonical.values());
/* 1663 */       if (Trace.isOn) {
/* 1664 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQPriorityPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQPriorityPropertyValidator(int domain) {
/* 1671 */       super(domain, domainsToPropertyNames, valuesToCanonical);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/* 1678 */       return validateIntBySet(value, validValues);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object priority) throws JMSException {
/* 1684 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1685 */       inserts.put("XMSC_INSERT_PROPERTY", this.applicablePropertyName);
/*      */       
/* 1687 */       inserts.put("XMSC_INSERT_VALUE", 
/* 1688 */           String.valueOf(priority));
/*      */       
/* 1690 */       JMSException je = (JMSException)NLSServices.createException("JMSCMQ0004", inserts);
/*      */ 
/*      */ 
/*      */       
/* 1694 */       throw je;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQPutAsyncAllowedPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -4617294089836628163L;
/*      */ 
/*      */     
/* 1707 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 1709 */       if (Trace.isOn) {
/* 1710 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQPutAsyncAllowedPropertyValidator", "static()");
/*      */       }
/*      */       
/* 1713 */       domainsToPropertyNames.put(Integer.valueOf(4), "putAsyncAllowed");
/* 1714 */       domainsToPropertyNames.put(Integer.valueOf(1), "PUTASYNCALLOWED");
/* 1715 */       domainsToPropertyNames.put(Integer.valueOf(2), "PAALD");
/* 1716 */       if (Trace.isOn) {
/* 1717 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQPutAsyncAllowedPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 1723 */       if (Trace.isOn) {
/* 1724 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQPutAsyncAllowedPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 1727 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 1728 */         propertyValidators.put(entry.getValue(), new WMQPutAsyncAllowedPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 1730 */       if (Trace.isOn) {
/* 1731 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQPutAsyncAllowedPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1737 */     private static HashMap<Object, Object> valuesToCanonical = new WMQStandardValidators.CaseInsentiveHashmap();
/* 1738 */     private static HashSet<Object> validValues = new HashSet(5, 1.0F);
/*      */     static {
/* 1740 */       if (Trace.isOn) {
/* 1741 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQPutAsyncAllowedPropertyValidator", "static()");
/*      */       }
/*      */       
/* 1744 */       valuesToCanonical.put("YES", 
/* 1745 */           Integer.valueOf(1));
/* 1746 */       valuesToCanonical.put("NO", 
/* 1747 */           Integer.valueOf(0));
/* 1748 */       valuesToCanonical.put("AS_DEST", 
/* 1749 */           Integer.valueOf(-1));
/* 1750 */       valuesToCanonical.put("AS_Q_DEF", 
/* 1751 */           Integer.valueOf(-1));
/* 1752 */       valuesToCanonical
/* 1753 */         .put("AS_TOPIC_DEF", 
/* 1754 */           Integer.valueOf(-1));
/* 1755 */       validValues.addAll(valuesToCanonical.values());
/* 1756 */       if (Trace.isOn) {
/* 1757 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQPutAsyncAllowedPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQPutAsyncAllowedPropertyValidator(int domain) {
/* 1764 */       super(domain, domainsToPropertyNames, valuesToCanonical);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/* 1771 */       return validateIntBySet(value, validValues);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object putAsyncValue) throws JMSException {
/* 1777 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1778 */       inserts.put("XMSC_INSERT_PROPERTY", this.applicablePropertyName);
/*      */       
/* 1780 */       inserts.put("XMSC_INSERT_VALUE", 
/* 1781 */           String.valueOf(putAsyncValue));
/*      */       
/* 1783 */       JMSException je = (JMSException)NLSServices.createException("JMSCMQ0004", inserts);
/*      */ 
/*      */       
/* 1786 */       throw je;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQReadAheadAllowedPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 2594030826556403515L;
/*      */ 
/*      */     
/* 1799 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 1801 */       if (Trace.isOn) {
/* 1802 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQReadAheadAllowedPropertyValidator", "static()");
/*      */       }
/*      */       
/* 1805 */       domainsToPropertyNames.put(Integer.valueOf(4), "readAheadAllowed");
/* 1806 */       domainsToPropertyNames.put(Integer.valueOf(1), "READAHEADALLOWED");
/* 1807 */       domainsToPropertyNames.put(Integer.valueOf(2), "RAALD");
/* 1808 */       if (Trace.isOn) {
/* 1809 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQReadAheadAllowedPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 1815 */       if (Trace.isOn) {
/* 1816 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQReadAheadAllowedPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 1819 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 1820 */         propertyValidators.put(entry.getValue(), new WMQReadAheadAllowedPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 1822 */       if (Trace.isOn) {
/* 1823 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQReadAheadAllowedPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1829 */     private static HashMap<Object, Object> valuesToCanonical = new WMQStandardValidators.CaseInsentiveHashmap();
/* 1830 */     private static HashSet<Object> validValues = new HashSet(5, 1.0F);
/*      */     static {
/* 1832 */       if (Trace.isOn) {
/* 1833 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQReadAheadAllowedPropertyValidator", "static()");
/*      */       }
/*      */       
/* 1836 */       valuesToCanonical.put("YES", 
/* 1837 */           Integer.valueOf(1));
/* 1838 */       valuesToCanonical.put("NO", 
/* 1839 */           Integer.valueOf(0));
/* 1840 */       valuesToCanonical.put("AS_DEST", 
/* 1841 */           Integer.valueOf(-1));
/* 1842 */       valuesToCanonical.put("AS_Q_DEF", 
/* 1843 */           Integer.valueOf(-1));
/* 1844 */       valuesToCanonical
/* 1845 */         .put("AS_TOPIC_DEF", 
/* 1846 */           Integer.valueOf(-1));
/* 1847 */       validValues.addAll(valuesToCanonical.values());
/* 1848 */       if (Trace.isOn) {
/* 1849 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQReadAheadAllowedPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQReadAheadAllowedPropertyValidator(int domain) {
/* 1856 */       super(domain, domainsToPropertyNames, valuesToCanonical);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/* 1863 */       return validateIntBySet(value, validValues);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean crossPropertyValidate(Object parent) throws JMSException {
/* 1868 */       if (Trace.isOn) {
/* 1869 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQReadAheadAllowedPropertyValidator", "crossPropertyValidate(Object)", new Object[] { parent });
/*      */       }
/*      */ 
/*      */       
/* 1873 */       if (((JmsPropertyContext)parent)
/* 1874 */         .getIntProperty("readAheadAllowed") != -1 && ((JmsPropertyContext)parent)
/* 1875 */         .getIntProperty("readAheadAllowed") != 0 && (
/* 1876 */         (JmsPropertyContext)parent)
/* 1877 */         .getIntProperty("XMSC_WMQ_CONNECTION_MODE") == 0) {
/* 1878 */         if (Trace.isOn) {
/* 1879 */           Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQReadAheadAllowedPropertyValidator", "crossPropertyValidate(Object)", 
/*      */               
/* 1881 */               Boolean.valueOf(false), 1);
/*      */         }
/* 1883 */         return false;
/*      */       } 
/*      */       
/* 1886 */       if (Trace.isOn) {
/* 1887 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQReadAheadAllowedPropertyValidator", "crossPropertyValidate(Object)", 
/*      */             
/* 1889 */             Boolean.valueOf(true), 2);
/*      */       }
/* 1891 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object readAheadValue) throws JMSException {
/* 1897 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1898 */       inserts.put("XMSC_INSERT_PROPERTY", this.applicablePropertyName);
/*      */       
/* 1900 */       inserts.put("XMSC_INSERT_VALUE", 
/* 1901 */           String.valueOf(readAheadValue));
/*      */       
/* 1903 */       JMSException je = (JMSException)NLSServices.createException("JMSCMQ0004", inserts);
/*      */ 
/*      */       
/* 1906 */       throw je;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQReadAheadClosePolicyPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 8703407824128956301L;
/*      */ 
/*      */     
/* 1919 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 1921 */       if (Trace.isOn) {
/* 1922 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQReadAheadClosePolicyPropertyValidator", "static()");
/*      */       }
/*      */ 
/*      */       
/* 1926 */       domainsToPropertyNames.put(Integer.valueOf(4), "readAheadClosePolicy");
/* 1927 */       domainsToPropertyNames.put(Integer.valueOf(1), "READAHEADCLOSEPOLICY");
/* 1928 */       domainsToPropertyNames.put(Integer.valueOf(2), "RACP");
/* 1929 */       if (Trace.isOn) {
/* 1930 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQReadAheadClosePolicyPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 1937 */       if (Trace.isOn) {
/* 1938 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQReadAheadClosePolicyPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */ 
/*      */       
/* 1942 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 1943 */         propertyValidators.put(entry.getValue(), new WMQReadAheadClosePolicyPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 1945 */       if (Trace.isOn) {
/* 1946 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQReadAheadClosePolicyPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1953 */     private static HashMap<Object, Object> valuesToCanonical = new WMQStandardValidators.CaseInsentiveHashmap();
/* 1954 */     private static Set<Object> validValues = new HashSet(2, 1.0F);
/*      */     static {
/* 1956 */       if (Trace.isOn) {
/* 1957 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQReadAheadClosePolicyPropertyValidator", "static()");
/*      */       }
/*      */ 
/*      */       
/* 1961 */       valuesToCanonical.put("DELIVER_ALL", 
/* 1962 */           Integer.valueOf(2));
/* 1963 */       valuesToCanonical.put("DELIVER_CURRENT", 
/* 1964 */           Integer.valueOf(1));
/* 1965 */       validValues.addAll(valuesToCanonical.values());
/* 1966 */       if (Trace.isOn) {
/* 1967 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQReadAheadClosePolicyPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQReadAheadClosePolicyPropertyValidator(int domain) {
/* 1975 */       super(domain, domainsToPropertyNames, valuesToCanonical);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/* 1981 */       if (Trace.isOn) {
/* 1982 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQReadAheadClosePolicyPropertyValidator", "validate(Object,Object)", new Object[] { value, parent });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1987 */       boolean traceRet1 = validateIntBySet(value, validValues);
/* 1988 */       if (Trace.isOn) {
/* 1989 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQReadAheadClosePolicyPropertyValidator", "validate(Object,Object)", 
/*      */             
/* 1991 */             Boolean.valueOf(traceRet1));
/*      */       }
/* 1993 */       return traceRet1;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean crossPropertyValidate(Object parent) throws JMSException {
/* 1998 */       if (Trace.isOn) {
/* 1999 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQReadAheadClosePolicyPropertyValidator", "crossPropertyValidate(Object)", new Object[] { parent });
/*      */       }
/*      */ 
/*      */       
/* 2003 */       if (((JmsPropertyContext)parent)
/* 2004 */         .getIntProperty("readAheadClosePolicy") != 2 && (
/* 2005 */         (JmsPropertyContext)parent)
/* 2006 */         .getIntProperty("XMSC_WMQ_CONNECTION_MODE") == 0) {
/* 2007 */         if (Trace.isOn) {
/* 2008 */           Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQReadAheadClosePolicyPropertyValidator", "crossPropertyValidate(Object)", 
/*      */               
/* 2010 */               Boolean.valueOf(false), 1);
/*      */         }
/* 2012 */         return false;
/*      */       } 
/*      */       
/* 2015 */       if (Trace.isOn) {
/* 2016 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQReadAheadClosePolicyPropertyValidator", "crossPropertyValidate(Object)", 
/*      */             
/* 2018 */             Boolean.valueOf(true), 2);
/*      */       }
/* 2020 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object closePolicyValue) throws JMSException {
/* 2026 */       HashMap<String, Object> inserts = new HashMap<>();
/* 2027 */       inserts.put("XMSC_INSERT_PROPERTY", this.applicablePropertyName);
/*      */       
/* 2029 */       inserts.put("XMSC_INSERT_VALUE", 
/* 2030 */           String.valueOf(closePolicyValue));
/*      */       
/* 2032 */       JMSException je = (JMSException)NLSServices.createException("JMSCMQ0004", inserts);
/*      */ 
/*      */       
/* 2035 */       throw je;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQTargetClientPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -8327884191290941918L;
/*      */     
/* 2046 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 2048 */       if (Trace.isOn) {
/* 2049 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQTargetClientPropertyValidator", "static()");
/*      */       }
/*      */       
/* 2052 */       domainsToPropertyNames.put(Integer.valueOf(4), "targetClient");
/* 2053 */       domainsToPropertyNames.put(Integer.valueOf(1), "TARGCLIENT");
/* 2054 */       domainsToPropertyNames.put(Integer.valueOf(2), "TC");
/* 2055 */       if (Trace.isOn) {
/* 2056 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQTargetClientPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 2062 */       if (Trace.isOn) {
/* 2063 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQTargetClientPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 2066 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 2067 */         propertyValidators.put(entry.getValue(), new WMQTargetClientPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 2069 */       if (Trace.isOn) {
/* 2070 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQTargetClientPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2076 */     private static HashMap<Object, Object> valuesToCanonical = new WMQStandardValidators.CaseInsentiveHashmap();
/* 2077 */     private static Set<Object> validValues = new HashSet(2, 1.0F);
/*      */     static {
/* 2079 */       if (Trace.isOn) {
/* 2080 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQTargetClientPropertyValidator", "static()");
/*      */       }
/*      */       
/* 2083 */       valuesToCanonical.put("JMS", 
/* 2084 */           Integer.valueOf(0));
/* 2085 */       valuesToCanonical.put("MQ", 
/* 2086 */           Integer.valueOf(1));
/* 2087 */       validValues.addAll(valuesToCanonical.values());
/* 2088 */       if (Trace.isOn) {
/* 2089 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQTargetClientPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQTargetClientPropertyValidator(int domain) {
/* 2096 */       super(domain, domainsToPropertyNames, valuesToCanonical);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/* 2103 */       return validateIntBySet(value, validValues);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object targetClient) throws JMSException {
/* 2109 */       HashMap<String, Object> inserts = new HashMap<>();
/* 2110 */       inserts.put("XMSC_INSERT_PROPERTY", this.applicablePropertyName);
/*      */       
/* 2112 */       inserts.put("XMSC_INSERT_VALUE", 
/* 2113 */           String.valueOf(targetClient));
/*      */       
/* 2115 */       JMSException je = (JMSException)NLSServices.createException("JMSCMQ0004", inserts);
/*      */ 
/*      */       
/* 2118 */       throw je;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQTimeToLivePropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -447532760337374562L;
/*      */     
/* 2129 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 2131 */       if (Trace.isOn) {
/* 2132 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQTimeToLivePropertyValidator", "static()");
/*      */       }
/*      */       
/* 2135 */       domainsToPropertyNames.put(Integer.valueOf(4), "timeToLive");
/* 2136 */       domainsToPropertyNames.put(Integer.valueOf(1), "EXPIRY");
/* 2137 */       domainsToPropertyNames.put(Integer.valueOf(2), "EXP");
/* 2138 */       if (Trace.isOn) {
/* 2139 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQTimeToLivePropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 2145 */       if (Trace.isOn) {
/* 2146 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQTimeToLivePropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 2149 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 2150 */         propertyValidators.put(entry.getValue(), new WMQTimeToLivePropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 2152 */       if (Trace.isOn) {
/* 2153 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQTimeToLivePropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2159 */     private static HashMap<Object, Object> valuesToCanonical = new WMQStandardValidators.CaseInsentiveHashmap();
/*      */     static {
/* 2161 */       if (Trace.isOn) {
/* 2162 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQTimeToLivePropertyValidator", "static()");
/*      */       }
/*      */       
/* 2165 */       valuesToCanonical.put("APP", Integer.valueOf(-2));
/* 2166 */       valuesToCanonical.put("UNLIM", 
/* 2167 */           Integer.valueOf(0));
/* 2168 */       if (Trace.isOn) {
/* 2169 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQTimeToLivePropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQTimeToLivePropertyValidator(int domain) {
/* 2176 */       super(domain, domainsToPropertyNames, valuesToCanonical);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object valueP, Object parent) throws JMSException {
/* 2182 */       if (Trace.isOn) {
/* 2183 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQTimeToLivePropertyValidator", "validate(Object,Object)", new Object[] { valueP, parent });
/*      */       }
/*      */ 
/*      */       
/* 2187 */       Object value = valueP;
/*      */       
/* 2189 */       if (!((value instanceof Integer || value instanceof Long) ? 1 : 0)) {
/* 2190 */         value = convertToString(value);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2195 */       if (value instanceof String) {
/* 2196 */         if ((this.mapperDomain == 1 || this.mapperDomain == 2) && 
/* 2197 */           valuesToCanonical.containsKey(value)) {
/* 2198 */           value = valuesToCanonical.get(value);
/*      */         }
/*      */ 
/*      */         
/*      */         try {
/* 2203 */           value = Long.valueOf((String)value);
/*      */         }
/* 2205 */         catch (NumberFormatException e) {
/* 2206 */           if (Trace.isOn) {
/* 2207 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.common.internal.WMQTimeToLivePropertyValidator", "validate(Object,Object)", e);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2213 */           if (Trace.isOn) {
/* 2214 */             Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQTimeToLivePropertyValidator", "validate(Object,Object)", 
/*      */                 
/* 2216 */                 Boolean.valueOf(false), 1);
/*      */           }
/* 2218 */           return false;
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2230 */       if (value instanceof Integer || value instanceof Long) {
/*      */         long intval;
/* 2232 */         if (value instanceof Long) {
/* 2233 */           intval = ((Long)value).longValue();
/*      */         } else {
/*      */           
/* 2236 */           intval = ((Integer)value).intValue();
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 2241 */         if (intval < 0L && intval != -2L) {
/* 2242 */           HashMap<String, Object> inserts = new HashMap<>();
/* 2243 */           inserts.put("XMSC_INSERT_PROPERTY", this.applicablePropertyName);
/*      */           
/* 2245 */           inserts.put("XMSC_INSERT_VALUE", 
/* 2246 */               String.valueOf(intval));
/*      */           
/* 2248 */           JMSException je = (JMSException)NLSServices.createException("JMSCMQ0004", inserts);
/*      */ 
/*      */ 
/*      */           
/* 2252 */           if (Trace.isOn) {
/* 2253 */             Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.WMQTimeToLivePropertyValidator", "validate(Object,Object)", (Throwable)je);
/*      */           }
/*      */ 
/*      */           
/* 2257 */           throw je;
/*      */         } 
/* 2259 */         if (Trace.isOn) {
/* 2260 */           Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQTimeToLivePropertyValidator", "validate(Object,Object)", 
/*      */               
/* 2262 */               Boolean.valueOf(true), 2);
/*      */         }
/* 2264 */         return true;
/*      */       } 
/* 2266 */       if (Trace.isOn) {
/* 2267 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQTimeToLivePropertyValidator", "validate(Object,Object)", 
/* 2268 */             Boolean.valueOf(false), 3);
/*      */       }
/* 2270 */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 2275 */       if (Trace.isOn) {
/* 2276 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQTimeToLivePropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2281 */       if (Trace.isOn) {
/* 2282 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQTimeToLivePropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQObjectIdentityPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     static final long serialVersionUID = -8471859292465301393L;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2299 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(1, 1.0F);
/*      */     static {
/* 2301 */       if (Trace.isOn) {
/* 2302 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQObjectIdentityPropertyValidator", "static()");
/*      */       }
/*      */       
/* 2305 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_OBJECT_IDENTITY");
/* 2306 */       if (Trace.isOn) {
/* 2307 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQObjectIdentityPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 2313 */       if (Trace.isOn) {
/* 2314 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQObjectIdentityPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 2317 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 2318 */         propertyValidators.put(entry.getValue(), new WMQObjectIdentityPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 2320 */       if (Trace.isOn) {
/* 2321 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQObjectIdentityPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQObjectIdentityPropertyValidator(int domain) {
/* 2329 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/* 2337 */       return validateLongForRange(value, Long.MIN_VALUE, Long.MAX_VALUE);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 2342 */       if (Trace.isOn) {
/* 2343 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQObjectIdentityPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2349 */       if (Trace.isOn) {
/* 2350 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQObjectIdentityPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQUserPropsPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -4548728485512352415L;
/*      */ 
/*      */ 
/*      */     
/* 2365 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(1, 1.0F);
/*      */     static {
/* 2367 */       if (Trace.isOn) {
/* 2368 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQUserPropsPropertyValidator", "static()");
/*      */       }
/*      */       
/* 2371 */       domainsToPropertyNames.put(Integer.valueOf(4), "userProperties");
/* 2372 */       if (Trace.isOn) {
/* 2373 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQUserPropsPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 2379 */       if (Trace.isOn) {
/* 2380 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQUserPropsPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 2383 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 2384 */         propertyValidators.put(entry.getValue(), new WMQUserPropsPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 2386 */       if (Trace.isOn) {
/* 2387 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQUserPropsPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQUserPropsPropertyValidator(int domain) {
/* 2395 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object valueP, Object parent) throws JMSException {
/* 2401 */       if (Trace.isOn) {
/* 2402 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQUserPropsPropertyValidator", "validate(Object,Object)", new Object[] { valueP, parent });
/*      */       }
/*      */ 
/*      */       
/* 2406 */       Object value = valueP;
/*      */ 
/*      */       
/* 2409 */       if (value instanceof java.util.Properties) {
/* 2410 */         if (Trace.isOn) {
/* 2411 */           Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQUserPropsPropertyValidator", "validate(Object,Object)", 
/* 2412 */               Boolean.valueOf(true), 1);
/*      */         }
/* 2414 */         return true;
/*      */       } 
/* 2416 */       if (Trace.isOn) {
/* 2417 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQUserPropsPropertyValidator", "validate(Object,Object)", 
/* 2418 */             Boolean.valueOf(false), 2);
/*      */       }
/* 2420 */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 2425 */       if (Trace.isOn) {
/* 2426 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQUserPropsPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2431 */       if (Trace.isOn) {
/* 2432 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQUserPropsPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQWildcardFormatPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 3919470995696146576L;
/*      */ 
/*      */ 
/*      */     
/* 2448 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 2450 */       if (Trace.isOn) {
/* 2451 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQWildcardFormatPropertyValidator", "static()");
/*      */       }
/*      */       
/* 2454 */       domainsToPropertyNames.put(Integer.valueOf(4), "wildcardFormat");
/* 2455 */       domainsToPropertyNames.put(Integer.valueOf(1), "WILDCARDFORMAT");
/* 2456 */       domainsToPropertyNames.put(Integer.valueOf(2), "WCFMT");
/* 2457 */       if (Trace.isOn) {
/* 2458 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQWildcardFormatPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 2464 */       if (Trace.isOn) {
/* 2465 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQWildcardFormatPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 2468 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 2469 */         propertyValidators.put(entry.getValue(), new WMQWildcardFormatPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 2471 */       if (Trace.isOn) {
/* 2472 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQWildcardFormatPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2478 */     private static HashMap<Object, Object> valuesToCanonical = new WMQStandardValidators.CaseInsentiveHashmap();
/* 2479 */     private static Set<Object> validValues = new HashSet(2, 1.0F);
/*      */     static {
/* 2481 */       if (Trace.isOn) {
/* 2482 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQWildcardFormatPropertyValidator", "static()");
/*      */       }
/*      */       
/* 2485 */       valuesToCanonical.put("CHAR_ONLY", 
/* 2486 */           Integer.valueOf(1));
/* 2487 */       valuesToCanonical.put("TOPIC_ONLY", 
/* 2488 */           Integer.valueOf(0));
/* 2489 */       validValues.addAll(valuesToCanonical.values());
/* 2490 */       if (Trace.isOn) {
/* 2491 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQWildcardFormatPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQWildcardFormatPropertyValidator(int domain) {
/* 2498 */       super(domain, domainsToPropertyNames, valuesToCanonical);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/* 2505 */       return validateIntBySet(value, validValues);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object intval) throws JMSException {
/* 2511 */       HashMap<String, Object> inserts = new HashMap<>();
/* 2512 */       inserts.put("XMSC_INSERT_PROPERTY", this.applicablePropertyName);
/*      */       
/* 2514 */       inserts.put("XMSC_INSERT_VALUE", 
/* 2515 */           String.valueOf(intval));
/*      */       
/* 2517 */       JMSException je = (JMSException)NLSServices.createException("JMSCMQ0004", inserts);
/*      */ 
/*      */       
/* 2520 */       throw je;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQBrokerCCDurSubQPropertyValidator
/*      */     extends WMQStandardValidators.WMQQueueNamePropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -1968710937199422796L;
/*      */ 
/*      */ 
/*      */     
/* 2534 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 2536 */       if (Trace.isOn) {
/* 2537 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQBrokerCCDurSubQPropertyValidator", "static()");
/*      */       }
/*      */       
/* 2540 */       domainsToPropertyNames.put(Integer.valueOf(4), "brokerCCDurSubQueue");
/* 2541 */       domainsToPropertyNames.put(Integer.valueOf(1), "BROKERCCDURSUBQ");
/* 2542 */       domainsToPropertyNames.put(Integer.valueOf(2), "CCDSUB");
/* 2543 */       if (Trace.isOn) {
/* 2544 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQBrokerCCDurSubQPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 2550 */       if (Trace.isOn) {
/* 2551 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQBrokerCCDurSubQPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 2554 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 2555 */         propertyValidators.put(entry.getValue(), new WMQBrokerCCDurSubQPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 2557 */       if (Trace.isOn) {
/* 2558 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQBrokerCCDurSubQPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQBrokerCCDurSubQPropertyValidator(int domain) {
/* 2566 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object valueP, Object parent) throws JMSException {
/* 2572 */       if (Trace.isOn) {
/* 2573 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQBrokerCCDurSubQPropertyValidator", "validate(Object,Object)", new Object[] { valueP, parent });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2578 */       Object value = valueP;
/*      */       
/* 2580 */       value = convertToString(value);
/*      */ 
/*      */       
/* 2583 */       if (!WMQDestination.validNamePattern.matcher((String)value).matches() && 
/* 2584 */         !WMQDestination.durPubSubQPattern.matcher((String)value).matches()) {
/* 2585 */         if (Trace.isOn) {
/* 2586 */           Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQBrokerCCDurSubQPropertyValidator", "validate(Object,Object)", 
/*      */               
/* 2588 */               Boolean.valueOf(false), 1);
/*      */         }
/* 2590 */         return false;
/*      */       } 
/*      */       
/* 2593 */       String queueName = (String)value;
/* 2594 */       if (queueName.length() <= 48 && queueName.startsWith("SYSTEM.JMS.")) {
/* 2595 */         if (Trace.isOn) {
/* 2596 */           Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQBrokerCCDurSubQPropertyValidator", "validate(Object,Object)", 
/*      */               
/* 2598 */               Boolean.valueOf(true), 2);
/*      */         }
/* 2600 */         return true;
/*      */       } 
/* 2602 */       if (Trace.isOn) {
/* 2603 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQBrokerCCDurSubQPropertyValidator", "validate(Object,Object)", 
/*      */             
/* 2605 */             Boolean.valueOf(false), 3);
/*      */       }
/* 2607 */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 2612 */       if (Trace.isOn) {
/* 2613 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQBrokerCCDurSubQPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2619 */       if (Trace.isOn) {
/* 2620 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQBrokerCCDurSubQPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQBrokerDurSubQPropertyValidator
/*      */     extends WMQStandardValidators.WMQQueueNamePropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 4010546703751946760L;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2637 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 2639 */       if (Trace.isOn) {
/* 2640 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQBrokerDurSubQPropertyValidator", "static()");
/*      */       }
/*      */       
/* 2643 */       domainsToPropertyNames.put(Integer.valueOf(4), "brokerDurSubQueue");
/* 2644 */       domainsToPropertyNames.put(Integer.valueOf(1), "BROKERDURSUBQ");
/* 2645 */       domainsToPropertyNames.put(Integer.valueOf(2), "BDSUB");
/* 2646 */       if (Trace.isOn) {
/* 2647 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQBrokerDurSubQPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 2653 */       if (Trace.isOn) {
/* 2654 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQBrokerDurSubQPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 2657 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 2658 */         propertyValidators.put(entry.getValue(), new WMQBrokerDurSubQPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 2660 */       if (Trace.isOn) {
/* 2661 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQBrokerDurSubQPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQBrokerDurSubQPropertyValidator(int domain) {
/* 2669 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object valueP, Object parent) throws JMSException {
/* 2675 */       if (Trace.isOn) {
/* 2676 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQBrokerDurSubQPropertyValidator", "validate(Object,Object)", new Object[] { valueP, parent });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2681 */       Object value = valueP;
/*      */       
/* 2683 */       if (value == null) {
/* 2684 */         if (Trace.isOn) {
/* 2685 */           Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQBrokerDurSubQPropertyValidator", "validate(Object,Object)", 
/*      */               
/* 2687 */               Boolean.valueOf(false), 1);
/*      */         }
/* 2689 */         return false;
/*      */       } 
/*      */       
/* 2692 */       value = convertToString(value);
/*      */ 
/*      */       
/* 2695 */       if (!WMQDestination.validNamePattern.matcher((String)value).matches() && 
/* 2696 */         !WMQDestination.durPubSubQPattern.matcher((String)value).matches()) {
/* 2697 */         if (Trace.isOn) {
/* 2698 */           Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQBrokerDurSubQPropertyValidator", "validate(Object,Object)", 
/*      */               
/* 2700 */               Boolean.valueOf(false), 2);
/*      */         }
/* 2702 */         return false;
/*      */       } 
/*      */       
/* 2705 */       String queueName = (String)value;
/* 2706 */       if (queueName.length() <= 48 && (queueName.startsWith("SYSTEM.JMS.") || queueName
/* 2707 */         .startsWith("SYSTEM.BROKER"))) {
/* 2708 */         if (Trace.isOn) {
/* 2709 */           Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQBrokerDurSubQPropertyValidator", "validate(Object,Object)", 
/*      */               
/* 2711 */               Boolean.valueOf(true), 3);
/*      */         }
/* 2713 */         return true;
/*      */       } 
/* 2715 */       if (Trace.isOn) {
/* 2716 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQBrokerDurSubQPropertyValidator", "validate(Object,Object)", 
/*      */             
/* 2718 */             Boolean.valueOf(false), 4);
/*      */       }
/* 2720 */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 2725 */       if (Trace.isOn) {
/* 2726 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQBrokerDurSubQPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2732 */       if (Trace.isOn) {
/* 2733 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQBrokerDurSubQPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQBrokerPubQPropertyValidator
/*      */     extends WMQStandardValidators.WMQQueueNamePropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 4467837469835453836L;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2750 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 2752 */       if (Trace.isOn) {
/* 2753 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQBrokerPubQPropertyValidator", "static()");
/*      */       }
/*      */       
/* 2756 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_BROKER_PUBQ");
/* 2757 */       domainsToPropertyNames.put(Integer.valueOf(1), "BROKERPUBQ");
/* 2758 */       domainsToPropertyNames.put(Integer.valueOf(2), "BPUB");
/* 2759 */       if (Trace.isOn) {
/* 2760 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQBrokerPubQPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 2766 */       if (Trace.isOn) {
/* 2767 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQBrokerPubQPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 2770 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 2771 */         propertyValidators.put(entry.getValue(), new WMQBrokerPubQPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 2773 */       if (Trace.isOn) {
/* 2774 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQBrokerPubQPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQBrokerPubQPropertyValidator(int domain) {
/* 2782 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object valueP, Object parent) throws JMSException {
/* 2788 */       if (Trace.isOn) {
/* 2789 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQBrokerPubQPropertyValidator", "validate(Object,Object)", new Object[] { valueP, parent });
/*      */       }
/*      */ 
/*      */       
/* 2793 */       Object value = valueP;
/*      */       
/* 2795 */       if (value == null) {
/* 2796 */         if (Trace.isOn) {
/* 2797 */           Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQBrokerPubQPropertyValidator", "validate(Object,Object)", 
/*      */               
/* 2799 */               Boolean.valueOf(false), 1);
/*      */         }
/* 2801 */         return false;
/*      */       } 
/*      */       
/* 2804 */       value = convertToString(value);
/*      */ 
/*      */       
/* 2807 */       if (!WMQDestination.validNamePattern.matcher((String)value).matches()) {
/* 2808 */         if (Trace.isOn) {
/* 2809 */           Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQBrokerPubQPropertyValidator", "validate(Object,Object)", 
/*      */               
/* 2811 */               Boolean.valueOf(false), 2);
/*      */         }
/* 2813 */         return false;
/*      */       } 
/*      */       
/* 2816 */       String queueName = (String)value;
/* 2817 */       if (queueName == null || queueName.length() > 48) {
/* 2818 */         if (Trace.isOn) {
/* 2819 */           Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQBrokerPubQPropertyValidator", "validate(Object,Object)", 
/*      */               
/* 2821 */               Boolean.valueOf(false), 3);
/*      */         }
/* 2823 */         return false;
/*      */       } 
/*      */       
/* 2826 */       if (Trace.isOn) {
/* 2827 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQBrokerPubQPropertyValidator", "validate(Object,Object)", 
/* 2828 */             Boolean.valueOf(true), 4);
/*      */       }
/* 2830 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 2835 */       if (Trace.isOn) {
/* 2836 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQBrokerPubQPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2841 */       if (Trace.isOn) {
/* 2842 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQBrokerPubQPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQBrokerPubQQMgrPropertyValidator
/*      */     extends WMQStandardValidators.WMQQueueNamePropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -6450884337394891771L;
/*      */ 
/*      */ 
/*      */     
/* 2858 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 2860 */       if (Trace.isOn) {
/* 2861 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQBrokerPubQQMgrPropertyValidator", "static()");
/*      */       }
/*      */       
/* 2864 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_BROKER_PUBQ_QMGR");
/* 2865 */       domainsToPropertyNames.put(Integer.valueOf(1), "BROKERPUBQMGR");
/* 2866 */       domainsToPropertyNames.put(Integer.valueOf(2), "BPQM");
/* 2867 */       if (Trace.isOn) {
/* 2868 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQBrokerPubQQMgrPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 2874 */       if (Trace.isOn) {
/* 2875 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQBrokerPubQQMgrPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 2878 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 2879 */         propertyValidators.put(entry.getValue(), new WMQBrokerPubQQMgrPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 2881 */       if (Trace.isOn) {
/* 2882 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQBrokerPubQQMgrPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQBrokerPubQQMgrPropertyValidator(int domain) {
/* 2890 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/* 2897 */       return validateString(value, (Pattern)null, WMQDestination.validQmgrNamePattern, 48, false);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 2902 */       if (Trace.isOn) {
/* 2903 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQBrokerPubQQMgrPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2909 */       if (Trace.isOn) {
/* 2910 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQBrokerPubQQMgrPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQBrokerVersionPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 7965859765992626834L;
/*      */ 
/*      */ 
/*      */     
/* 2925 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 2927 */       if (Trace.isOn) {
/* 2928 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQBrokerVersionPropertyValidator", "static()");
/*      */       }
/*      */       
/* 2931 */       domainsToPropertyNames.put(Integer.valueOf(4), "brokerVersion");
/* 2932 */       domainsToPropertyNames.put(Integer.valueOf(1), "BROKERVER");
/* 2933 */       domainsToPropertyNames.put(Integer.valueOf(2), "BVER");
/* 2934 */       if (Trace.isOn) {
/* 2935 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQBrokerVersionPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/* 2940 */     static Set<Object> validValues = new HashSet(2, 1.0F);
/*      */     static {
/* 2942 */       if (Trace.isOn) {
/* 2943 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQBrokerVersionPropertyValidator", "static()");
/*      */       }
/*      */       
/* 2946 */       validValues.addAll(Arrays.asList((Object[])new Integer[] { Integer.valueOf(0), Integer.valueOf(1) }));
/* 2947 */       if (Trace.isOn) {
/* 2948 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQBrokerVersionPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 2954 */       if (Trace.isOn) {
/* 2955 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQBrokerVersionPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 2958 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 2959 */         propertyValidators.put(entry.getValue(), new WMQBrokerVersionPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 2961 */       if (Trace.isOn) {
/* 2962 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQBrokerVersionPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQBrokerVersionPropertyValidator(int domain) {
/* 2970 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/* 2977 */       return validateIntBySet(value, validValues);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean crossPropertyValidate(Object parent) throws JMSException {
/* 2982 */       if (Trace.isOn) {
/* 2983 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQBrokerVersionPropertyValidator", "crossPropertyValidate(Object)", new Object[] { parent });
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2989 */       JmsPropertyContext parentPC = (JmsPropertyContext)parent;
/* 2990 */       if (parentPC.getIntProperty("brokerVersion") == 0) {
/* 2991 */         if (parentPC
/* 2992 */           .getIntProperty("XMSC_WMQ_CONNECTION_MODE") == 1 || parentPC
/* 2993 */           .getIntProperty("XMSC_WMQ_CONNECTION_MODE") == 0) {
/* 2994 */           if (Trace.isOn) {
/* 2995 */             Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQBrokerVersionPropertyValidator", "crossPropertyValidate(Object)", 
/*      */                 
/* 2997 */                 Boolean.valueOf(true), 1);
/*      */           }
/* 2999 */           return true;
/*      */         } 
/*      */         
/* 3002 */         if (Trace.isOn) {
/* 3003 */           Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQBrokerVersionPropertyValidator", "crossPropertyValidate(Object)", 
/*      */               
/* 3005 */               Boolean.valueOf(false), 2);
/*      */         }
/* 3007 */         return false;
/*      */       } 
/*      */       
/* 3010 */       if (parentPC
/* 3011 */         .getIntProperty("brokerVersion") == 1) {
/*      */         
/* 3013 */         if (Trace.isOn) {
/* 3014 */           Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQBrokerVersionPropertyValidator", "crossPropertyValidate(Object)", 
/*      */               
/* 3016 */               Boolean.valueOf(false), 3);
/*      */         }
/* 3018 */         return false;
/*      */       } 
/*      */       
/* 3021 */       if (Trace.isOn) {
/* 3022 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQBrokerVersionPropertyValidator", "crossPropertyValidate(Object)", 
/*      */             
/* 3024 */             Boolean.valueOf(true), 4);
/*      */       }
/* 3026 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object version) throws JMSException {
/* 3033 */       HashMap<String, Object> inserts = new HashMap<>();
/* 3034 */       inserts.put("XMSC_INSERT_PROPERTY", this.applicablePropertyName);
/*      */       
/* 3036 */       inserts.put("XMSC_INSERT_VALUE", 
/* 3037 */           String.valueOf(version));
/*      */       
/* 3039 */       JMSException je = (JMSException)NLSServices.createException("JMSCMQ0004", inserts);
/*      */       
/* 3041 */       throw je;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQVersionPropertyValidator
/*      */     extends WMQStandardValidators.WMQTrivialPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -6200113082501515212L;
/*      */ 
/*      */ 
/*      */     
/* 3055 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 3057 */       if (Trace.isOn) {
/* 3058 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQVersionPropertyValidator", "static()");
/*      */       }
/*      */       
/* 3061 */       domainsToPropertyNames.put(Integer.valueOf(4), "version");
/* 3062 */       domainsToPropertyNames.put(Integer.valueOf(1), "VERSION");
/* 3063 */       domainsToPropertyNames.put(Integer.valueOf(2), "VER");
/* 3064 */       if (Trace.isOn) {
/* 3065 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQVersionPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 3071 */       if (Trace.isOn) {
/* 3072 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQVersionPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 3075 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 3076 */         propertyValidators.put(entry.getValue(), new WMQVersionPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 3078 */       if (Trace.isOn) {
/* 3079 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQVersionPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQVersionPropertyValidator(int domain) {
/* 3087 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 3092 */       if (Trace.isOn) {
/* 3093 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQVersionPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 3098 */       if (Trace.isOn) {
/* 3099 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQVersionPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQReplyToStylePropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -6200145672501515211L;
/*      */ 
/*      */ 
/*      */     
/* 3114 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(4, 1.0F);
/*      */     static {
/* 3116 */       if (Trace.isOn) {
/* 3117 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQReplyToStylePropertyValidator", "static()");
/*      */       }
/*      */       
/* 3120 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_REPLYTO_STYLE");
/* 3121 */       domainsToPropertyNames.put(Integer.valueOf(1), "REPLYTOSTYLE");
/* 3122 */       domainsToPropertyNames.put(Integer.valueOf(2), "RTOST");
/* 3123 */       domainsToPropertyNames.put(Integer.valueOf(3), "replyToStyle");
/* 3124 */       if (Trace.isOn) {
/* 3125 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQReplyToStylePropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 3131 */       if (Trace.isOn) {
/* 3132 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQReplyToStylePropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 3135 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 3136 */         propertyValidators.put(entry.getValue(), new WMQReplyToStylePropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 3138 */       if (Trace.isOn) {
/* 3139 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQReplyToStylePropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 3145 */     private static HashMap<Object, Object> valuesToCanonical = new WMQStandardValidators.CaseInsentiveHashmap();
/* 3146 */     private static Set<Object> validValues = new HashSet(3, 1.0F);
/*      */     static {
/* 3148 */       if (Trace.isOn) {
/* 3149 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQReplyToStylePropertyValidator", "static()");
/*      */       }
/*      */       
/* 3152 */       valuesToCanonical.put("DEFAULT", 
/* 3153 */           Integer.valueOf(0));
/* 3154 */       valuesToCanonical.put("MQMD", 
/* 3155 */           Integer.valueOf(1));
/* 3156 */       valuesToCanonical.put("RFH2", 
/* 3157 */           Integer.valueOf(2));
/* 3158 */       validValues.addAll(valuesToCanonical.values());
/* 3159 */       if (Trace.isOn) {
/* 3160 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQReplyToStylePropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQReplyToStylePropertyValidator(int domain) {
/* 3167 */       super(domain, domainsToPropertyNames, valuesToCanonical);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/* 3174 */       return validateIntBySet(value, validValues);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object replyToStyle) throws JMSException {
/* 3180 */       HashMap<String, Object> inserts = new HashMap<>();
/* 3181 */       inserts.put("XMSC_INSERT_PROPERTY", this.applicablePropertyName);
/*      */       
/* 3183 */       inserts.put("XMSC_INSERT_VALUE", 
/* 3184 */           String.valueOf(replyToStyle));
/*      */       
/* 3186 */       JMSException je = (JMSException)NLSServices.createException("JMSCMQ0004", inserts);
/*      */ 
/*      */       
/* 3189 */       throw je;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQReceiveCCSIDPropertyValidator
/*      */     extends WMQStandardValidators.WMQCcsidPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -889913888348414268L;
/*      */     
/* 3200 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 3202 */       if (Trace.isOn) {
/* 3203 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQReceiveCCSIDPropertyValidator", "static()");
/*      */       }
/*      */       
/* 3206 */       domainsToPropertyNames.put(Integer.valueOf(4), "receiveCCSID");
/* 3207 */       domainsToPropertyNames.put(Integer.valueOf(1), "RECEIVECCSID");
/* 3208 */       domainsToPropertyNames.put(Integer.valueOf(2), "RCCS");
/* 3209 */       if (Trace.isOn) {
/* 3210 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQReceiveCCSIDPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 3216 */       if (Trace.isOn) {
/* 3217 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQReceiveCCSIDPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 3220 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 3221 */         propertyValidators.put(entry.getValue(), new WMQReceiveCCSIDPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 3223 */       if (Trace.isOn) {
/* 3224 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQReceiveCCSIDPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQReceiveCCSIDPropertyValidator(int domain) {
/* 3232 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object ccsid) throws JMSException {
/* 3237 */       if (Trace.isOn) {
/* 3238 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQReceiveCCSIDPropertyValidator", "throwBadValueException(Object)", new Object[] { ccsid });
/*      */       }
/*      */ 
/*      */       
/* 3242 */       HashMap<String, Object> inserts = new HashMap<>();
/* 3243 */       inserts.put("XMSC_INSERT_PROPERTY", this.applicablePropertyName);
/*      */       
/* 3245 */       inserts.put("XMSC_INSERT_VALUE", 
/* 3246 */           String.valueOf(ccsid));
/*      */       
/* 3248 */       JMSException je = (JMSException)NLSServices.createException("JMSCMQ0004", inserts);
/*      */ 
/*      */       
/* 3251 */       if (Trace.isOn) {
/* 3252 */         Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.WMQReceiveCCSIDPropertyValidator", "throwBadValueException(Object)", (Throwable)je);
/*      */       }
/*      */ 
/*      */       
/* 3256 */       throw je;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQReceiveConversionPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -84234231548414268L;
/*      */ 
/*      */ 
/*      */     
/* 3271 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 3273 */       if (Trace.isOn) {
/* 3274 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQReceiveConversionPropertyValidator", "static()");
/*      */       }
/*      */ 
/*      */       
/* 3278 */       domainsToPropertyNames.put(Integer.valueOf(4), "receiveConversion");
/* 3279 */       domainsToPropertyNames.put(Integer.valueOf(1), "RECEIVECONVERSION");
/* 3280 */       domainsToPropertyNames.put(Integer.valueOf(2), "RCNV");
/* 3281 */       domainsToPropertyNames.put(Integer.valueOf(3), "receiveConversion");
/* 3282 */       if (Trace.isOn) {
/* 3283 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQReceiveConversionPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 3289 */       if (Trace.isOn) {
/* 3290 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQReceiveConversionPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */ 
/*      */       
/* 3294 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 3295 */         propertyValidators.put(entry.getValue(), new WMQReceiveConversionPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 3297 */       if (Trace.isOn) {
/* 3298 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQReceiveConversionPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 3304 */     private static HashMap<Object, Object> valuesToCanonical = new WMQStandardValidators.CaseInsentiveHashmap();
/* 3305 */     private static Set<Object> validValues = new HashSet(2, 1.0F);
/*      */     static {
/* 3307 */       if (Trace.isOn) {
/* 3308 */         Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQReceiveConversionPropertyValidator", "static()");
/*      */       }
/*      */ 
/*      */       
/* 3312 */       valuesToCanonical.put("QMGR", 
/* 3313 */           Integer.valueOf(2));
/* 3314 */       valuesToCanonical
/* 3315 */         .put("CLIENT_MSG", 
/* 3316 */           Integer.valueOf(1));
/* 3317 */       validValues.addAll(valuesToCanonical.values());
/* 3318 */       if (Trace.isOn) {
/* 3319 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQReceiveConversionPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQReceiveConversionPropertyValidator(int domain) {
/* 3326 */       super(domain, domainsToPropertyNames, valuesToCanonical);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/* 3333 */       return validateIntBySet(value, validValues);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object rcnv) throws JMSException {
/* 3339 */       HashMap<String, Object> inserts = new HashMap<>();
/* 3340 */       inserts.put("XMSC_INSERT_PROPERTY", this.applicablePropertyName);
/*      */       
/* 3342 */       inserts.put("XMSC_INSERT_VALUE", 
/* 3343 */           String.valueOf(rcnv));
/*      */       
/* 3345 */       JMSException je = (JMSException)NLSServices.createException("JMSCMQ0004", inserts);
/*      */ 
/*      */       
/* 3348 */       throw je;
/*      */     }
/*      */   }
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
/*      */   private boolean validationEnabled = true;
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
/* 3374 */   private static final Map<String, WMQStandardValidators.WMQPropertyValidator> allPropertyValidators = new HashMap<>();
/*      */ 
/*      */   
/* 3377 */   private WMQDestinationURIParser uriParser = new WMQDestinationURIParser();
/*      */ 
/*      */   
/* 3380 */   private String destinationAsURI = null;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean destBaseChanged;
/*      */ 
/*      */   
/*      */   private boolean destPropsChanged;
/*      */ 
/*      */   
/*      */   private HashMap<String, Object> destProps;
/*      */ 
/*      */   
/*      */   private boolean cacheInitialised;
/*      */ 
/*      */   
/*      */   private CacheProperty cachedAlternateUserId;
/*      */ 
/*      */   
/*      */   private CacheProperty cachedDestinationName;
/*      */ 
/*      */   
/*      */   private CacheProperty cachedDeliveryMode;
/*      */ 
/*      */   
/*      */   private CacheProperty cachedEncoding;
/*      */ 
/*      */   
/*      */   private CacheProperty cachedCCSID;
/*      */ 
/*      */   
/*      */   private CacheProperty cachedTargetClient;
/*      */ 
/*      */   
/*      */   private CacheProperty cachedPriority;
/*      */ 
/*      */   
/*      */   private CacheProperty cachedDestDescription;
/*      */ 
/*      */   
/*      */   private CacheProperty cachedFailIfQuiesce;
/*      */ 
/*      */   
/*      */   private CacheProperty cachedPutAsyncAllowed;
/*      */ 
/*      */   
/*      */   private CacheProperty cachedReadAheadAllowed;
/*      */ 
/*      */   
/*      */   private CacheProperty cachedReadAheadClosePolicy;
/*      */ 
/*      */   
/*      */   private CacheProperty cachedWildcardFormat;
/*      */ 
/*      */   
/*      */   private CacheProperty cachedTimeToLive;
/*      */ 
/*      */ 
/*      */   
/*      */   static {
/* 3440 */     if (Trace.isOn) {
/* 3441 */       Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQDestination", "static()");
/*      */     }
/*      */     
/* 3444 */     WMQAlternateUserIdPropertyValidator.register(allPropertyValidators);
/* 3445 */     WMQEncodingPropertyValidator.register(allPropertyValidators);
/* 3446 */     WMQCCSIDPropertyValidator.register(allPropertyValidators);
/* 3447 */     WMQTargetClientPropertyValidator.register(allPropertyValidators);
/* 3448 */     WMQDeliveryModePropertyValidator.register(allPropertyValidators);
/* 3449 */     WMQPriorityPropertyValidator.register(allPropertyValidators);
/* 3450 */     WMQDestDescriptionPropertyValidator.register(allPropertyValidators);
/* 3451 */     WMQFailIfQuiescePropertyValidator.register(allPropertyValidators);
/* 3452 */     WMQMessageContextPropertyValidator.register(allPropertyValidators);
/* 3453 */     WMQmdWriteEnabledPropertyValidator.register(allPropertyValidators);
/* 3454 */     WMQmdReadEnabledPropertyValidator.register(allPropertyValidators);
/* 3455 */     WMQMessageBodyStylePropertyValidator.register(allPropertyValidators);
/* 3456 */     WMQPutAsyncAllowedPropertyValidator.register(allPropertyValidators);
/* 3457 */     WMQReadAheadAllowedPropertyValidator.register(allPropertyValidators);
/* 3458 */     WMQReadAheadClosePolicyPropertyValidator.register(allPropertyValidators);
/* 3459 */     WMQWildcardFormatPropertyValidator.register(allPropertyValidators);
/* 3460 */     WMQTimeToLivePropertyValidator.register(allPropertyValidators);
/* 3461 */     WMQBrokerPubQQMgrPropertyValidator.register(allPropertyValidators);
/* 3462 */     WMQBrokerPubQPropertyValidator.register(allPropertyValidators);
/* 3463 */     WMQBrokerDurSubQPropertyValidator.register(allPropertyValidators);
/* 3464 */     WMQBrokerCCDurSubQPropertyValidator.register(allPropertyValidators);
/* 3465 */     WMQBrokerVersionPropertyValidator.register(allPropertyValidators);
/* 3466 */     WMQDestinationNamePropertyValidator.register(allPropertyValidators);
/* 3467 */     WMQQueueManagerNamePropertyValidator.register(allPropertyValidators);
/* 3468 */     WMQVersionPropertyValidator.register(allPropertyValidators);
/* 3469 */     WMQReplyToStylePropertyValidator.register(allPropertyValidators);
/* 3470 */     WMQReceiveCCSIDPropertyValidator.register(allPropertyValidators);
/* 3471 */     WMQReceiveConversionPropertyValidator.register(allPropertyValidators);
/* 3472 */     WMQConnectionTypeNamePropertyValidator.register(allPropertyValidators);
/* 3473 */     WMQConnectionTypePropertyValidator.register(allPropertyValidators);
/* 3474 */     WMQAdminObjectPropertyValidator.register(allPropertyValidators);
/* 3475 */     WMQUserPropsPropertyValidator.register(allPropertyValidators);
/* 3476 */     WMQObjectIdentityPropertyValidator.register(allPropertyValidators);
/* 3477 */     WMQUnmappableActionPropertyValidator.register(allPropertyValidators);
/* 3478 */     WMQUnmappableReplacementPropertyValidator.register(allPropertyValidators);
/*      */     
/* 3480 */     if (Trace.isOn)
/* 3481 */       Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQDestination", "static()"); 
/*      */   } private CacheProperty cachedMessageContext; private CacheProperty cachedMdWriteEnabled; private CacheProperty cachedMdReadEnabled; private CacheProperty cachedMessageBodyStyle; private CacheProperty cachedRemoteQueueManager; private CacheProperty cachedBrokerCCDurSubQ; private CacheProperty cachedBrokerDurSubQ; private CacheProperty cachedBrokerPubQ; private CacheProperty cachedBrokerPubQQMgr; private CacheProperty cachedBrokerVersion; private CacheProperty cachedVersion; private CacheProperty cachedReplyToStyle; private CacheProperty cachedReceiveCCSID; private CacheProperty cachedReceiveConversion; private CacheProperty cachedUnmappableReplacement; private CacheProperty cachedUnmappableAction; private Boolean supportsNPHigh; private void initialiseDefaultCacheProperties() { this.cachedAlternateUserId = new CacheProperty("alternateUserId", null, false); this.cachedDestinationName = new CacheProperty("XMSC_DESTINATION_NAME", null, true); this.cachedPriority = new CacheProperty("priority", Integer.valueOf(-2), false); this.cachedDeliveryMode = new CacheProperty("deliveryMode", "persistence", Integer.valueOf(-2), false); this.cachedTimeToLive = new CacheProperty("timeToLive", "expiry", Integer.valueOf(-2), false); this.cachedCCSID = new CacheProperty("CCSID", Integer.valueOf(1208), false); this.cachedReceiveCCSID = new CacheProperty("receiveCCSID", Integer.valueOf(1208), false); this.cachedReceiveConversion = new CacheProperty("receiveConversion", Integer.valueOf(1), false); this.cachedDestDescription = new CacheProperty("destDescription", null, false); this.cachedEncoding = new CacheProperty("encoding", Integer.valueOf(273), false); this.cachedFailIfQuiesce = new CacheProperty("failIfQuiesce", Integer.valueOf(1), false); this.cachedMessageContext = new CacheProperty("mdMessageContext", Integer.valueOf(0), false); this.cachedMdWriteEnabled = new CacheProperty("mdWriteEnabled", Boolean.valueOf(false), false); this.cachedMdReadEnabled = new CacheProperty("mdReadEnabled", Boolean.valueOf(false), false); this.cachedMessageBodyStyle = new CacheProperty("messageBody", Integer.valueOf(2), false); this.cachedPutAsyncAllowed = new CacheProperty("putAsyncAllowed", Integer.valueOf(-1), false); this.cachedReadAheadAllowed = new CacheProperty("readAheadAllowed", Integer.valueOf(-1), false); this.cachedReadAheadClosePolicy = new CacheProperty("readAheadClosePolicy", Integer.valueOf(2), false); this.cachedTargetClient = new CacheProperty("targetClient", Integer.valueOf(0), false); this.cachedWildcardFormat = new CacheProperty("wildcardFormat", Integer.valueOf(0), false); this.cachedVersion = new CacheProperty("version", Integer.valueOf(7), false); try {
/*      */       JmqiDefaultThreadPoolFactory threadPool = new JmqiDefaultThreadPoolFactory(); JmqiDefaultPropertyHandler jmqiDefaultPropertyHandler = new JmqiDefaultPropertyHandler(); JmqiEnvironment env = JmqiFactory.getInstance((JmqiThreadPoolFactory)threadPool, (JmqiPropertyHandler)jmqiDefaultPropertyHandler);
/*      */       CodingErrorAction cea = JmqiCodepage.getUnmappableCharacterDefaultAction(env);
/*      */       byte[] unmappableReplacement = JmqiCodepage.getUnmappableCharacterDefaultReplacement(env);
/*      */       this.cachedUnmappableAction = new CacheProperty("JMS_IBM_Unmappable_Action", cea.toString(), false);
/*      */       this.cachedUnmappableReplacement = new CacheProperty("JMS_IBM_Unmappable_Replacement", Byte.valueOf(unmappableReplacement[0]), false);
/*      */     } catch (JmqiException je) {
/*      */       this.cachedUnmappableAction = new CacheProperty("JMS_IBM_Unmappable_Action", CodingErrorAction.REPORT.toString(), false);
/*      */       this.cachedUnmappableReplacement = new CacheProperty("JMS_IBM_Unmappable_Replacement", Byte.valueOf((byte)63), false);
/*      */     } 
/*      */     this.cachedRemoteQueueManager = new CacheProperty("XMSC_WMQ_QUEUE_MANAGER", "", true);
/*      */     this.cachedBrokerCCDurSubQ = new CacheProperty("brokerCCDurSubQueue", "SYSTEM.JMS.D.CC.SUBSCRIBER.QUEUE", false);
/*      */     this.cachedBrokerDurSubQ = new CacheProperty("brokerDurSubQueue", "SYSTEM.JMS.D.SUBSCRIBER.QUEUE", false);
/*      */     this.cachedBrokerPubQ = new CacheProperty("XMSC_WMQ_BROKER_PUBQ", "", false);
/*      */     this.cachedBrokerPubQQMgr = new CacheProperty("XMSC_WMQ_BROKER_PUBQ_QMGR", "", false);
/*      */     this.cachedBrokerVersion = new CacheProperty("brokerVersion", Integer.valueOf(0), false);
/* 3498 */     this.cachedReplyToStyle = new CacheProperty("XMSC_WMQ_REPLYTO_STYLE", Integer.valueOf(0), false); } public WMQDestination(int destType, String name, JmsPropertyContext props) throws JMSException { this(false, destType, name, (ProviderConnection)null, props); } public void delete() throws JMSException { if (Trace.isOn)
/*      */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "delete()");  if (!this.isTemporary) {
/*      */       Trace.ffst(this, "delete()", "XM002001", null, null); JMSException jmsex = (JMSException)NLSServices.createException("JMSCMQ0003", null); if (Trace.isOn)
/*      */         Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "delete()", (Throwable)jmsex); 
/*      */       throw jmsex;
/*      */     } 
/*      */     if (this.connection != null)
/*      */       this.connection.deleteTemporaryDestination(this); 
/*      */     this.hasBeenDeleted = true;
/*      */     if (Trace.isOn)
/*      */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "delete()");  }
/*      */   public String getName() { if (Trace.isOn)
/*      */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "getName()"); 
/*      */     try {
/*      */       String traceRet1 = getStringProperty("XMSC_DESTINATION_NAME");
/*      */       if (Trace.isOn)
/*      */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "getName()", traceRet1, 1); 
/*      */       return traceRet1;
/*      */     } catch (JMSException j) {
/*      */       if (Trace.isOn)
/*      */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "getName()", (Throwable)j); 
/*      */       if (Trace.isOn)
/*      */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "getName()", null, 2); 
/*      */       return null;
/*      */     }  }
/* 3523 */   WMQDestination(boolean isTemporary, int destType, String name, ProviderConnection connection, JmsPropertyContext props) throws JMSException { super(props);
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
/* 3880 */     this.supportsNPHigh = null; if (Trace.isOn)
/*      */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "<init>(boolean,int,String,ProviderConnection,JmsPropertyContext)", new Object[] { Boolean.valueOf(isTemporary), Integer.valueOf(destType), name, connection, props });  this.destType = destType; this.isTemporary = isTemporary; this.connection = (WMQCommonConnection)connection; this.hasBeenDeleted = false; this.uriParser.setDomain(destType); if (name != null) {
/*      */       this.uriParser.setUri(name); setStringProperty("XMSC_DESTINATION_NAME", this.uriParser.getDestinationName()); setStringProperty("XMSC_WMQ_QUEUE_MANAGER", this.uriParser.getQmName()); this.uriParser.getPropsIntoPropertyContext(this); this.destBaseChanged = false;
/*      */       this.destPropsChanged = false;
/*      */     } 
/*      */     int domain = this.uriParser.getDomain();
/*      */     if (domain != this.destType)
/*      */       this.destType = domain; 
/*      */     if (Trace.isOn)
/*      */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "<init>(boolean,int,String,ProviderConnection,JmsPropertyContext)");  } public boolean isQueue() { boolean result = (this.destType == 1 || this.destType == 3);
/*      */     if (Trace.isOn)
/*      */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "isQueue()", "getter", Boolean.valueOf(result)); 
/*      */     return result; }
/* 3893 */   public boolean isNPHighCheckDone() { boolean traceRet1 = (this.supportsNPHigh != null);
/* 3894 */     if (Trace.isOn) {
/* 3895 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "isNPHighCheckDone()", "getter", 
/* 3896 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 3898 */     return traceRet1; }
/*      */    public boolean isTemporary() {
/*      */     if (Trace.isOn)
/*      */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "isTemporary()", "getter", Boolean.valueOf(this.isTemporary)); 
/*      */     return this.isTemporary;
/*      */   }
/*      */   public boolean isTopic() {
/*      */     boolean result = (this.destType == 2);
/*      */     if (Trace.isOn)
/*      */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "isTopic()", "getter", Boolean.valueOf(result)); 
/*      */     return result;
/*      */   }
/* 3910 */   public boolean getNPHighSupported() { boolean traceRet1 = (this.supportsNPHigh != null && this.supportsNPHigh.booleanValue());
/* 3911 */     if (Trace.isOn) {
/* 3912 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "getNPHighSupported()", "getter", 
/* 3913 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 3915 */     return traceRet1; }
/*      */   
/*      */   public boolean isManagedQueue() {
/*      */     boolean result = (this.destType == 3);
/*      */     if (Trace.isOn)
/*      */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "isManagedQueue()", "getter", Boolean.valueOf(result)); 
/*      */     if (Trace.isOn)
/*      */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "isManagedQueue()", "getter", Boolean.valueOf(result)); 
/*      */     return result;
/*      */   }
/*      */   
/* 3926 */   public void setNPHighSupported(boolean value) { if (Trace.isOn) {
/* 3927 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "setNPHighSupported(boolean)", "setter", 
/* 3928 */           Boolean.valueOf(value));
/*      */     }
/* 3930 */     this.supportsNPHigh = Boolean.valueOf(value); } public void setFailIfQuiesce(int fiq) throws JMSException { if (Trace.isOn)
/*      */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "setFailIfQuiesce(int)", "setter", Integer.valueOf(fiq));  setIntProperty("failIfQuiesce", fiq); }
/*      */   public int getFailIfQuiesce() { int fiq = 0; try { fiq = getIntProperty("failIfQuiesce"); }
/*      */     catch (JMSException je)
/*      */     { if (Trace.isOn)
/*      */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "getFailIfQuiesce()", (Throwable)je);  if (Trace.isOn)
/*      */         Trace.data(this, "public int getFailIfQuiesce()", "Exception thrown querying property. Assuming default", je);  }
/*      */      if (Trace.isOn)
/*      */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "getFailIfQuiesce()", "getter", Integer.valueOf(fiq));  return fiq; }
/* 3939 */   public String toURI() throws JMSException { if (Trace.isOn) {
/* 3940 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "toURI()");
/*      */     }
/*      */ 
/*      */     
/* 3944 */     if (this.destBaseChanged) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3950 */       if (isQueue()) {
/*      */ 
/*      */         
/* 3953 */         String qmName = (this.cachedRemoteQueueManager.value == null) ? "" : this.cachedRemoteQueueManager.value.toString();
/* 3954 */         this.uriParser.setQmName(qmName);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3960 */       String name = (this.cachedDestinationName.value == null) ? "" : this.cachedDestinationName.value.toString();
/* 3961 */       this.uriParser.setDestinationName(name);
/*      */       
/* 3963 */       this.destBaseChanged = false;
/*      */     } 
/*      */ 
/*      */     
/* 3967 */     if (this.destPropsChanged) {
/* 3968 */       this.uriParser.setProps(this.destProps);
/*      */       
/* 3970 */       this.destPropsChanged = false;
/*      */     } 
/*      */     
/* 3973 */     this.destinationAsURI = this.uriParser.getURI();
/*      */     
/* 3975 */     if (Trace.isOn) {
/* 3976 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "toURI()", this.destinationAsURI);
/*      */     }
/* 3978 */     return this.destinationAsURI; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean containsAnyWildcard() {
/* 3989 */     if (Trace.isOn) {
/* 3990 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "containsAnyWildcard()");
/*      */     }
/*      */ 
/*      */     
/* 3994 */     boolean result = false;
/* 3995 */     int brokerVersion = 0;
/*      */     
/*      */     try {
/* 3998 */       brokerVersion = getIntProperty("brokerVersion");
/*      */     }
/* 4000 */     catch (JMSException je) {
/* 4001 */       if (Trace.isOn) {
/* 4002 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "containsAnyWildcard()", (Throwable)je);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 4008 */     result = this.uriParser.containsAnyWildcard(brokerVersion);
/*      */     
/* 4010 */     if (Trace.isOn) {
/* 4011 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "containsAnyWildcard()", 
/* 4012 */           Boolean.valueOf(result));
/*      */     }
/* 4014 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean containsWildcard() {
/* 4025 */     if (Trace.isOn) {
/* 4026 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "containsWildcard()");
/*      */     }
/*      */ 
/*      */     
/* 4030 */     boolean result = false;
/*      */     
/* 4032 */     result = this.uriParser.containsWildcard();
/*      */     
/* 4034 */     if (Trace.isOn) {
/* 4035 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "containsWildcard()", 
/* 4036 */           Boolean.valueOf(result));
/*      */     }
/* 4038 */     return result;
/*      */   }
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
/*      */   public Object getCachedValue(String key) {
/* 4052 */     if (!this.cacheInitialised) {
/* 4053 */       initialiseDefaultCacheProperties();
/* 4054 */       this.cacheInitialised = true;
/*      */     } 
/*      */     
/* 4057 */     CacheProperty prop = findCacheProperty(key);
/* 4058 */     if (prop != null && prop.exists) {
/* 4059 */       return prop.value;
/*      */     }
/*      */     
/* 4062 */     return null;
/*      */   }
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
/*      */   public boolean setCachedValue(String key, Object value) {
/* 4078 */     if (!this.cacheInitialised) {
/* 4079 */       initialiseDefaultCacheProperties();
/* 4080 */       this.cacheInitialised = true;
/*      */     } 
/*      */     
/* 4083 */     CacheProperty prop = findCacheProperty(key);
/* 4084 */     if (prop != null) {
/* 4085 */       prop.setValue(value);
/* 4086 */       if (prop.baseProperty) {
/* 4087 */         this.destBaseChanged = true;
/*      */       } else {
/*      */         
/* 4090 */         Object defaultVal = prop.defaultValue;
/* 4091 */         checkForURIChange(prop.uriName, value, (defaultVal == null) ? null : defaultVal
/* 4092 */             .toString());
/* 4093 */         this.destPropsChanged = true;
/*      */       } 
/* 4095 */       return true;
/*      */     } 
/* 4097 */     if (key.equals("XMSC_CONNECTION_TYPE_NAME") || key.equals("XMSC_CONNECTION_TYPE") || key.equals("XMSC_ADMIN_OBJECT_TYPE"))
/*      */     {
/* 4099 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 4103 */     if (this.destProps == null) {
/* 4104 */       this.destProps = new HashMap<>();
/*      */     }
/* 4106 */     this.destProps.put(key, (value == null) ? null : value.toString());
/* 4107 */     this.destPropsChanged = true;
/* 4108 */     return false;
/*      */   }
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
/*      */   public void clearCachedValue(String key) {
/* 4123 */     if (!this.cacheInitialised) {
/* 4124 */       initialiseDefaultCacheProperties();
/* 4125 */       this.cacheInitialised = true;
/*      */     } 
/*      */     
/* 4128 */     CacheProperty prop = findCacheProperty(key);
/* 4129 */     if (prop != null) {
/* 4130 */       prop.clear();
/* 4131 */       if (prop.baseProperty) {
/* 4132 */         this.destBaseChanged = true;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 4137 */     this.destPropsChanged = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void getCachedValueAll(Map<String, Object> props) {
/* 4147 */     if (!this.cacheInitialised) {
/* 4148 */       initialiseDefaultCacheProperties();
/* 4149 */       this.cacheInitialised = true;
/*      */     } 
/*      */     
/* 4152 */     if (this.cachedAlternateUserId.exists) {
/* 4153 */       props.put(this.cachedAlternateUserId.name, this.cachedAlternateUserId.value);
/*      */     }
/* 4155 */     if (this.cachedDestinationName.exists) {
/* 4156 */       props.put(this.cachedDestinationName.name, this.cachedDestinationName.value);
/*      */     }
/* 4158 */     if (this.cachedPriority.exists) {
/* 4159 */       props.put(this.cachedPriority.name, this.cachedPriority.value);
/*      */     }
/* 4161 */     if (this.cachedDeliveryMode.exists) {
/* 4162 */       props.put(this.cachedDeliveryMode.name, this.cachedDeliveryMode.value);
/*      */     }
/* 4164 */     if (this.cachedTimeToLive.exists) {
/* 4165 */       props.put(this.cachedTimeToLive.name, this.cachedTimeToLive.value);
/*      */     }
/* 4167 */     if (this.cachedCCSID.exists) {
/* 4168 */       props.put(this.cachedCCSID.name, this.cachedCCSID.value);
/*      */     }
/* 4170 */     if (this.cachedDestDescription.exists) {
/* 4171 */       props.put(this.cachedDestDescription.name, this.cachedDestDescription.value);
/*      */     }
/* 4173 */     if (this.cachedEncoding.exists) {
/* 4174 */       props.put(this.cachedEncoding.name, this.cachedEncoding.value);
/*      */     }
/* 4176 */     if (this.cachedFailIfQuiesce.exists) {
/* 4177 */       props.put(this.cachedFailIfQuiesce.name, this.cachedFailIfQuiesce.value);
/*      */     }
/* 4179 */     if (this.cachedMessageContext.exists) {
/* 4180 */       props.put(this.cachedMessageContext.name, this.cachedMessageContext.value);
/*      */     }
/* 4182 */     if (this.cachedMdWriteEnabled.exists) {
/* 4183 */       props.put(this.cachedMdWriteEnabled.name, this.cachedMdWriteEnabled.value);
/*      */     }
/* 4185 */     if (this.cachedMdReadEnabled.exists) {
/* 4186 */       props.put(this.cachedMdReadEnabled.name, this.cachedMdReadEnabled.value);
/*      */     }
/* 4188 */     if (this.cachedMessageBodyStyle.exists) {
/* 4189 */       props.put(this.cachedMessageBodyStyle.name, this.cachedMessageBodyStyle.value);
/*      */     }
/* 4191 */     if (this.cachedPutAsyncAllowed.exists) {
/* 4192 */       props.put(this.cachedPutAsyncAllowed.name, this.cachedPutAsyncAllowed.value);
/*      */     }
/* 4194 */     if (this.cachedReadAheadAllowed.exists) {
/* 4195 */       props.put(this.cachedReadAheadAllowed.name, this.cachedReadAheadAllowed.value);
/*      */     }
/* 4197 */     if (this.cachedReadAheadClosePolicy.exists) {
/* 4198 */       props.put(this.cachedReadAheadClosePolicy.name, this.cachedReadAheadClosePolicy.value);
/*      */     }
/*      */     
/* 4201 */     if (this.cachedTargetClient.exists) {
/* 4202 */       props.put(this.cachedTargetClient.name, this.cachedTargetClient.value);
/*      */     }
/* 4204 */     if (this.cachedWildcardFormat.exists) {
/* 4205 */       props.put(this.cachedWildcardFormat.name, this.cachedWildcardFormat.value);
/*      */     }
/* 4207 */     if (this.cachedVersion.exists) {
/* 4208 */       props.put(this.cachedVersion.name, this.cachedVersion.value);
/*      */     }
/* 4210 */     if (this.cachedUnmappableAction.exists) {
/* 4211 */       props.put(this.cachedUnmappableAction.name, this.cachedUnmappableAction.value);
/*      */     }
/* 4213 */     if (this.cachedUnmappableReplacement.exists) {
/* 4214 */       props.put(this.cachedUnmappableReplacement.name, this.cachedUnmappableReplacement.value);
/*      */     }
/*      */ 
/*      */     
/* 4218 */     if (this.cachedRemoteQueueManager.exists) {
/* 4219 */       props.put(this.cachedRemoteQueueManager.name, this.cachedRemoteQueueManager.value);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 4224 */     if (this.cachedBrokerCCDurSubQ.exists) {
/* 4225 */       props.put(this.cachedBrokerCCDurSubQ.name, this.cachedBrokerCCDurSubQ.value);
/*      */     }
/* 4227 */     if (this.cachedBrokerDurSubQ.exists) {
/* 4228 */       props.put(this.cachedBrokerDurSubQ.name, this.cachedBrokerDurSubQ.value);
/*      */     }
/* 4230 */     if (this.cachedBrokerPubQ.exists) {
/* 4231 */       props.put(this.cachedBrokerPubQ.name, this.cachedBrokerPubQ.value);
/*      */     }
/* 4233 */     if (this.cachedBrokerPubQQMgr.exists) {
/* 4234 */       props.put(this.cachedBrokerPubQQMgr.name, this.cachedBrokerPubQQMgr.value);
/*      */     }
/* 4236 */     if (this.cachedBrokerVersion.exists) {
/* 4237 */       props.put(this.cachedBrokerVersion.name, this.cachedBrokerVersion.value);
/*      */     }
/*      */     
/* 4240 */     if (this.cachedReplyToStyle.exists) {
/* 4241 */       props.put(this.cachedReplyToStyle.name, this.cachedReplyToStyle.value);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clearCachedValueAll() {
/* 4252 */     if (!this.cacheInitialised) {
/* 4253 */       initialiseDefaultCacheProperties();
/* 4254 */       this.cacheInitialised = true;
/*      */     } 
/*      */     
/* 4257 */     this.cachedAlternateUserId.clear();
/* 4258 */     this.cachedDestinationName.clear();
/* 4259 */     this.cachedDeliveryMode.clear();
/* 4260 */     this.cachedEncoding.clear();
/* 4261 */     this.cachedCCSID.clear();
/* 4262 */     this.cachedTargetClient.clear();
/* 4263 */     this.cachedPriority.clear();
/* 4264 */     this.cachedDestDescription.clear();
/* 4265 */     this.cachedFailIfQuiesce.clear();
/* 4266 */     this.cachedPutAsyncAllowed.clear();
/* 4267 */     this.cachedReadAheadAllowed.clear();
/* 4268 */     this.cachedReadAheadClosePolicy.clear();
/* 4269 */     this.cachedWildcardFormat.clear();
/* 4270 */     this.cachedTimeToLive.clear();
/* 4271 */     this.cachedMessageContext.clear();
/* 4272 */     this.cachedMdWriteEnabled.clear();
/* 4273 */     this.cachedMdReadEnabled.clear();
/* 4274 */     this.cachedMessageBodyStyle.clear();
/* 4275 */     this.cachedRemoteQueueManager.clear();
/* 4276 */     this.cachedBrokerCCDurSubQ.clear();
/* 4277 */     this.cachedBrokerDurSubQ.clear();
/* 4278 */     this.cachedBrokerPubQ.clear();
/* 4279 */     this.cachedBrokerPubQQMgr.clear();
/* 4280 */     this.cachedBrokerVersion.clear();
/* 4281 */     this.cachedVersion.clear();
/* 4282 */     this.cachedReplyToStyle.clear();
/* 4283 */     this.cachedUnmappableAction.clear();
/* 4284 */     this.cachedUnmappableReplacement.clear();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean containsCachedValue(String key) {
/* 4295 */     if (!this.cacheInitialised) {
/* 4296 */       initialiseDefaultCacheProperties();
/* 4297 */       this.cacheInitialised = true;
/*      */     } 
/*      */     
/* 4300 */     boolean found = false;
/*      */     
/* 4302 */     CacheProperty prop = findCacheProperty(key);
/* 4303 */     if (prop != null && prop.exists) {
/* 4304 */       found = true;
/*      */     }
/*      */     
/* 4307 */     return found;
/*      */   }
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
/*      */   private CacheProperty findCacheProperty(String key) {
/* 4329 */     CacheProperty prop = null;
/*      */     
/* 4331 */     if (key.equals(this.cachedAlternateUserId.name)) {
/* 4332 */       prop = this.cachedAlternateUserId;
/*      */     }
/* 4334 */     else if (key.equals(this.cachedDestinationName.name)) {
/* 4335 */       prop = this.cachedDestinationName;
/*      */     }
/* 4337 */     else if (key.equals(this.cachedPriority.name)) {
/* 4338 */       prop = this.cachedPriority;
/*      */     }
/* 4340 */     else if (key.equals(this.cachedDeliveryMode.name)) {
/* 4341 */       prop = this.cachedDeliveryMode;
/*      */     }
/* 4343 */     else if (key.equals(this.cachedTimeToLive.name)) {
/* 4344 */       prop = this.cachedTimeToLive;
/*      */     }
/* 4346 */     else if (key.equals(this.cachedTargetClient.name)) {
/* 4347 */       prop = this.cachedTargetClient;
/*      */     }
/* 4349 */     else if (key.equals(this.cachedEncoding.name)) {
/* 4350 */       prop = this.cachedEncoding;
/*      */     }
/* 4352 */     else if (key.equals(this.cachedCCSID.name)) {
/* 4353 */       prop = this.cachedCCSID;
/*      */     }
/* 4355 */     else if (key.equals(this.cachedReceiveCCSID.name)) {
/* 4356 */       prop = this.cachedReceiveCCSID;
/*      */     }
/* 4358 */     else if (key.equals(this.cachedReceiveConversion.name)) {
/* 4359 */       prop = this.cachedReceiveConversion;
/*      */     }
/* 4361 */     else if (key.equals(this.cachedDestDescription.name)) {
/* 4362 */       prop = this.cachedDestDescription;
/*      */     }
/* 4364 */     else if (key.equals(this.cachedFailIfQuiesce.name)) {
/* 4365 */       prop = this.cachedFailIfQuiesce;
/*      */     }
/* 4367 */     else if (key.equals(this.cachedPutAsyncAllowed.name)) {
/* 4368 */       prop = this.cachedPutAsyncAllowed;
/*      */     }
/* 4370 */     else if (key.equals(this.cachedReadAheadAllowed.name)) {
/* 4371 */       prop = this.cachedReadAheadAllowed;
/*      */     }
/* 4373 */     else if (key.equals(this.cachedReadAheadClosePolicy.name)) {
/* 4374 */       prop = this.cachedReadAheadClosePolicy;
/*      */     }
/* 4376 */     else if (key.equals(this.cachedWildcardFormat.name)) {
/* 4377 */       prop = this.cachedWildcardFormat;
/*      */     }
/* 4379 */     else if (key.equals(this.cachedMessageContext.name)) {
/* 4380 */       prop = this.cachedMessageContext;
/*      */     }
/* 4382 */     else if (key.equals(this.cachedMdWriteEnabled.name)) {
/* 4383 */       prop = this.cachedMdWriteEnabled;
/*      */     }
/* 4385 */     else if (key.equals(this.cachedMdReadEnabled.name)) {
/* 4386 */       prop = this.cachedMdReadEnabled;
/*      */     }
/* 4388 */     else if (key.equals(this.cachedMessageBodyStyle.name)) {
/* 4389 */       prop = this.cachedMessageBodyStyle;
/*      */     }
/* 4391 */     else if (key.equals(this.cachedRemoteQueueManager.name)) {
/* 4392 */       prop = this.cachedRemoteQueueManager;
/*      */     }
/* 4394 */     else if (key.equals(this.cachedBrokerCCDurSubQ.name)) {
/* 4395 */       prop = this.cachedBrokerCCDurSubQ;
/*      */     }
/* 4397 */     else if (key.equals(this.cachedBrokerDurSubQ.name)) {
/* 4398 */       prop = this.cachedBrokerDurSubQ;
/*      */     }
/* 4400 */     else if (key.equals(this.cachedBrokerPubQ.name)) {
/* 4401 */       prop = this.cachedBrokerPubQ;
/*      */     }
/* 4403 */     else if (key.equals(this.cachedBrokerPubQQMgr.name)) {
/* 4404 */       prop = this.cachedBrokerPubQQMgr;
/*      */     }
/* 4406 */     else if (key.equals(this.cachedBrokerVersion.name)) {
/* 4407 */       prop = this.cachedBrokerVersion;
/*      */     }
/* 4409 */     else if (key.equals(this.cachedVersion.name)) {
/* 4410 */       prop = this.cachedVersion;
/*      */     }
/* 4412 */     else if (key.equals(this.cachedReplyToStyle.name)) {
/* 4413 */       prop = this.cachedReplyToStyle;
/*      */     }
/* 4415 */     else if (key.equals(this.cachedUnmappableAction.name)) {
/* 4416 */       prop = this.cachedUnmappableAction;
/*      */     }
/* 4418 */     else if (key.equals(this.cachedUnmappableReplacement.name)) {
/* 4419 */       prop = this.cachedUnmappableReplacement;
/*      */     } 
/*      */     
/* 4422 */     return prop;
/*      */   }
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
/*      */   private void checkForURIChange(String propName, Object val, String defaultVal) {
/* 4435 */     if (Trace.isOn) {
/* 4436 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "checkForURIChange(String,Object,String)", new Object[] { propName, val, defaultVal });
/*      */     }
/*      */ 
/*      */     
/* 4440 */     if (this.destProps == null) {
/* 4441 */       this.destProps = new HashMap<>();
/*      */     }
/*      */     
/* 4444 */     if (val != defaultVal && (
/* 4445 */       val == null || !val.toString().equals(defaultVal))) {
/* 4446 */       this.destProps.put(propName, val);
/* 4447 */       if (Trace.isOn) {
/* 4448 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "checkForURIChange(String,Object,String)", 1);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 4458 */     this.destProps.remove(propName);
/* 4459 */     if (Trace.isOn) {
/* 4460 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "checkForURIChange(String,Object,String)", 2);
/*      */     }
/*      */   }
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
/*      */   public Object mapFromCanonical(String keyIn, Object valueIn) {
/* 4482 */     if (Trace.isOn) {
/* 4483 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "mapFromCanonical(String,Object)", new Object[] { keyIn, valueIn });
/*      */     }
/*      */     
/* 4486 */     if (allPropertyValidators.size() > 0) {
/*      */ 
/*      */       
/* 4489 */       WMQStandardValidators.WMQPropertyValidator mapper = allPropertyValidators.get(keyIn);
/* 4490 */       if (mapper != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 4496 */         WMQValidationInterface.WMQPropertyValidatorDatatype mappedPair = mapper.fromCanonical(keyIn, valueIn);
/*      */ 
/*      */ 
/*      */         
/* 4500 */         Object traceRet1 = mappedPair.getValue();
/* 4501 */         if (Trace.isOn) {
/* 4502 */           Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "mapFromCanonical(String,Object)", traceRet1, 1);
/*      */         }
/*      */         
/* 4505 */         return traceRet1;
/*      */       } 
/*      */       
/* 4508 */       if (Trace.isOn) {
/* 4509 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "mapFromCanonical(String,Object)", valueIn, 2);
/*      */       }
/*      */       
/* 4512 */       return valueIn;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 4517 */     if (Trace.isOn) {
/* 4518 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "mapFromCanonical(String,Object)", valueIn, 3);
/*      */     }
/*      */     
/* 4521 */     return valueIn;
/*      */   }
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
/*      */   public String getCanonicalKey(String keyIn) {
/* 4536 */     if (Trace.isOn) {
/* 4537 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "getCanonicalKey(String)", new Object[] { keyIn });
/*      */     }
/*      */     
/* 4540 */     if (allPropertyValidators.size() > 0) {
/*      */       
/* 4542 */       WMQStandardValidators.WMQPropertyValidator mapper = allPropertyValidators.get(keyIn);
/* 4543 */       if (mapper == null)
/*      */       {
/*      */ 
/*      */         
/* 4547 */         mapper = allPropertyValidators.get(keyIn
/* 4548 */             .toUpperCase());
/*      */       }
/*      */       
/* 4551 */       if (mapper != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 4556 */         String traceRet1 = mapper.getDomainName(4);
/* 4557 */         if (Trace.isOn) {
/* 4558 */           Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "getCanonicalKey(String)", traceRet1, 1);
/*      */         }
/*      */         
/* 4561 */         return traceRet1;
/*      */       } 
/*      */       
/* 4564 */       if (Trace.isOn) {
/* 4565 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "getCanonicalKey(String)", keyIn, 2);
/*      */       }
/*      */       
/* 4568 */       return keyIn;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 4573 */     if (Trace.isOn) {
/* 4574 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "getCanonicalKey(String)", keyIn, 3);
/*      */     }
/*      */     
/* 4577 */     return keyIn;
/*      */   }
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
/*      */   public Vector mapToCanonical(String keyIn, Object valueIn) {
/* 4595 */     if (Trace.isOn) {
/* 4596 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "mapToCanonical(String,Object)", new Object[] { keyIn, valueIn });
/*      */     }
/*      */     
/* 4599 */     Vector<Object> result = new Vector();
/*      */     
/* 4601 */     if (allPropertyValidators.size() > 0) {
/*      */ 
/*      */       
/* 4604 */       WMQStandardValidators.WMQPropertyValidator mapper = allPropertyValidators.get(keyIn);
/* 4605 */       if (mapper == null)
/*      */       {
/*      */ 
/*      */         
/* 4609 */         mapper = allPropertyValidators.get(keyIn
/* 4610 */             .toUpperCase());
/*      */       }
/*      */       
/* 4613 */       if (mapper != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 4619 */         WMQValidationInterface.WMQPropertyValidatorDatatype mappedPair = mapper.toCanonical(keyIn, valueIn);
/*      */ 
/*      */         
/* 4622 */         result.add(mappedPair.getName());
/* 4623 */         result.add(mappedPair.getValue());
/* 4624 */         if (Trace.isOn) {
/* 4625 */           Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "mapToCanonical(String,Object)", result, 1);
/*      */         }
/*      */         
/* 4628 */         return result;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4634 */       result.add(keyIn);
/* 4635 */       result.add(valueIn);
/* 4636 */       if (Trace.isOn) {
/* 4637 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "mapToCanonical(String,Object)", result, 2);
/*      */       }
/*      */       
/* 4640 */       return result;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 4645 */     result.add(keyIn);
/* 4646 */     result.add(valueIn);
/* 4647 */     if (Trace.isOn) {
/* 4648 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "mapToCanonical(String,Object)", result, 3);
/*      */     }
/*      */     
/* 4651 */     return result;
/*      */   }
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
/*      */   public boolean validate(Object name, Object value) throws JMSException {
/* 4666 */     if (Trace.isOn) {
/* 4667 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "validate(Object,Object)", new Object[] { name, value });
/*      */     }
/*      */ 
/*      */     
/* 4671 */     if (this.validationEnabled)
/*      */     {
/*      */ 
/*      */ 
/*      */       
/* 4676 */       if (allPropertyValidators.size() > 0) {
/* 4677 */         Object mqValidator = allPropertyValidators.get(name);
/* 4678 */         if (mqValidator == null && name instanceof String)
/*      */         {
/*      */           
/* 4681 */           mqValidator = allPropertyValidators.get(((String)name)
/* 4682 */               .toUpperCase());
/*      */         }
/*      */         
/* 4685 */         if (mqValidator != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 4690 */           boolean traceRet1 = ((WMQStandardValidators.WMQPropertyValidator)mqValidator).validate(value, this.jmsPropertyContext);
/* 4691 */           if (Trace.isOn) {
/* 4692 */             Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "validate(Object,Object)", 
/* 4693 */                 Boolean.valueOf(traceRet1), 1);
/*      */           }
/* 4695 */           return traceRet1;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 4703 */         if (Trace.isOn) {
/* 4704 */           Trace.traceData(this, "Unable to find validator for property " + name + " - skipping validation", null);
/*      */         }
/*      */         
/* 4707 */         HashMap<String, Object> inserts = new HashMap<>();
/* 4708 */         inserts.put("XMSC_INSERT_KEY", name);
/* 4709 */         inserts.put("XMSC_INSERT_VALUE", value);
/* 4710 */         Log.log(this, "validate(Object name, Object value)", "JMSCMQ0007", inserts);
/*      */ 
/*      */         
/* 4713 */         if (Trace.isOn) {
/* 4714 */           Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "validate(Object,Object)", 
/* 4715 */               Boolean.valueOf(true), 2);
/*      */         }
/* 4717 */         return true;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4726 */     if (Trace.isOn) {
/* 4727 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "validate(Object,Object)", 
/* 4728 */           Boolean.valueOf(true), 3);
/*      */     }
/* 4730 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String crossPropertyValidate() {
/* 4738 */     if (Trace.isOn) {
/* 4739 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "crossPropertyValidate()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4745 */     boolean overallValidation = true;
/* 4746 */     StringBuilder validationFailures = new StringBuilder();
/*      */     
/* 4748 */     if (allPropertyValidators.size() > 0) {
/* 4749 */       for (Map.Entry<String, WMQStandardValidators.WMQPropertyValidator> validator : allPropertyValidators.entrySet()) {
/* 4750 */         String key = validator.getKey();
/* 4751 */         WMQStandardValidators.WMQPropertyValidator mqValidator = validator.getValue();
/* 4752 */         if (mqValidator != null) {
/* 4753 */           boolean validated = true;
/*      */           
/*      */           try {
/* 4756 */             validated = mqValidator.crossPropertyValidate(this.jmsPropertyContext);
/*      */           }
/* 4758 */           catch (JMSException j) {
/* 4759 */             if (Trace.isOn) {
/* 4760 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "crossPropertyValidate()", (Throwable)j);
/*      */             }
/*      */             
/* 4763 */             if (Trace.isOn) {
/* 4764 */               Trace.traceWarning(this, "c.i.m.c.w.common.internal.WMQDestination", "crossPropertyValidate()", j);
/*      */             }
/*      */           } 
/*      */ 
/*      */           
/* 4769 */           if (!validated) {
/* 4770 */             overallValidation = false;
/* 4771 */             if (validationFailures.length() > 0) {
/* 4772 */               validationFailures.append(", ");
/*      */             }
/* 4774 */             validationFailures.append(key.toString());
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/* 4780 */     if (overallValidation == true) {
/* 4781 */       if (Trace.isOn) {
/* 4782 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "crossPropertyValidate()", null, 1);
/*      */       }
/*      */       
/* 4785 */       return null;
/*      */     } 
/* 4787 */     String traceRet1 = validationFailures.toString();
/* 4788 */     if (Trace.isOn) {
/* 4789 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "crossPropertyValidate()", traceRet1, 2);
/*      */     }
/*      */     
/* 4792 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isDeleted() {
/* 4799 */     if (Trace.isOn) {
/* 4800 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "isDeleted()");
/*      */     }
/* 4802 */     if (this.isTemporary) {
/* 4803 */       if (Trace.isOn) {
/* 4804 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "isDeleted()", 
/* 4805 */             Boolean.valueOf(this.hasBeenDeleted), 1);
/*      */       }
/* 4807 */       return this.hasBeenDeleted;
/*      */     } 
/* 4809 */     if (Trace.isOn) {
/* 4810 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "isDeleted()", 
/* 4811 */           Boolean.valueOf(false), 2);
/*      */     }
/* 4813 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isScopedBy(WMQCommonConnection conn) {
/* 4821 */     if (Trace.isOn) {
/* 4822 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "isScopedBy(WMQCommonConnection)", new Object[] { conn });
/*      */     }
/*      */     
/* 4825 */     boolean traceRet1 = conn.equals(this.connection);
/* 4826 */     if (Trace.isOn) {
/* 4827 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "isScopedBy(WMQCommonConnection)", 
/* 4828 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 4830 */     return traceRet1;
/*      */   }
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
/*      */   public boolean equals(Object o) {
/*      */     boolean traceRet1;
/* 4847 */     if (this == o) {
/* 4848 */       traceRet1 = true;
/*      */     }
/* 4850 */     else if (null == o || !(o instanceof WMQDestination)) {
/* 4851 */       traceRet1 = false;
/*      */     } else {
/*      */       
/* 4854 */       traceRet1 = super.equals(o);
/*      */     } 
/* 4856 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int hashCode() {
/* 4865 */     return super.hashCode();
/*      */   }
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
/*      */   public void checkAccess(WMQCommonConnection connectionP) throws JMSException {}
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
/*      */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 4890 */     if (Trace.isOn) {
/* 4891 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "readObject(ObjectInputStream)", new Object[] { in });
/*      */     }
/*      */     
/* 4894 */     NotSerializableException traceRet1 = new NotSerializableException();
/* 4895 */     if (Trace.isOn) {
/* 4896 */       Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "readObject(ObjectInputStream)", traceRet1);
/*      */     }
/*      */     
/* 4899 */     throw traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 4909 */     if (Trace.isOn) {
/* 4910 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "writeObject(ObjectOutputStream)", new Object[] { out });
/*      */     }
/*      */     
/* 4913 */     NotSerializableException traceRet1 = new NotSerializableException();
/* 4914 */     if (Trace.isOn) {
/* 4915 */       Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "writeObject(ObjectOutputStream)", traceRet1);
/*      */     }
/*      */     
/* 4918 */     throw traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDestType(int destType) {
/* 4927 */     if (Trace.isOn) {
/* 4928 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "setDestType(int)", "setter", 
/* 4929 */           Integer.valueOf(destType));
/*      */     }
/* 4931 */     if (Trace.isOn)
/* 4932 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "setDestType(int)", new Object[] {
/*      */             
/* 4934 */             Integer.valueOf(destType)
/*      */           }); 
/* 4936 */     this.destType = destType;
/* 4937 */     if (Trace.isOn) {
/* 4938 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "setDestType(int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/* 4949 */     if (Trace.isOn) {
/* 4950 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "toString()");
/*      */     }
/*      */     
/* 4953 */     String traceRet1 = null;
/* 4954 */     if (this.destinationAsURI != null) {
/* 4955 */       traceRet1 = this.destinationAsURI;
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 4961 */       String className = getClass().getCanonicalName();
/* 4962 */       traceRet1 = className + "@" + Integer.toHexString(System.identityHashCode(this));
/*      */     } 
/*      */     
/* 4965 */     if (Trace.isOn) {
/* 4966 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "toString()", traceRet1);
/*      */     }
/* 4968 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void dump(PrintWriter pw, int level) {
/* 4976 */     if (Trace.isOn) {
/* 4977 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "dump(PrintWriter,int)", new Object[] { pw, 
/* 4978 */             Integer.valueOf(level) });
/*      */     }
/* 4980 */     String prefix = Trace.buildPrefix(level);
/* 4981 */     pw.format("%s%s%n", new Object[] { prefix, super.toString() });
/* 4982 */     pw.format("%s[Properties]", new Object[] { prefix });
/* 4983 */     TableBuilder builder = new TableBuilder((level + 1) * 2, true, false);
/* 4984 */     builder.populate((Map)this);
/* 4985 */     pw.println(builder.toString());
/* 4986 */     if (Trace.isOn)
/* 4987 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQDestination", "dump(PrintWriter,int)"); 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\common\internal\WMQDestination.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */