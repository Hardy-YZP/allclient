/*      */ package com.ibm.msg.client.jms.internal;
/*      */ 
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.jms.JmsPropertyCacheInterface;
/*      */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*      */ import com.ibm.msg.client.jms.JmsValidationInterface;
/*      */ import com.ibm.msg.client.provider.ProviderPropertyContextCallback;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.Vector;
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
/*      */ public class JmsPropertyContextImpl
/*      */   extends JmsReadablePropertyContextImpl
/*      */   implements JmsPropertyContext, ProviderPropertyContextCallback
/*      */ {
/*      */   static {
/*   54 */     if (Trace.isOn) {
/*   55 */       Trace.data("com.ibm.msg.client.jms.internal.JmsPropertyContextImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsPropertyContextImpl.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean settingDefaults = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final long serialVersionUID = 5430741298380246575L;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsPropertyContextImpl.java";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private transient JmsPropertyContext providerPropertyContext;
/*      */ 
/*      */ 
/*      */   
/*      */   private transient boolean providerCacheSynched;
/*      */ 
/*      */ 
/*      */   
/*      */   protected transient Thread creator;
/*      */ 
/*      */ 
/*      */   
/*      */   protected transient long createTime;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JmsPropertyContextImpl() {
/*   96 */     this.creator = Thread.currentThread();
/*   97 */     this.createTime = System.currentTimeMillis();
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
/*      */   public JmsPropertyContextImpl(Map<String, Object> propsTable, boolean doCopy) {
/*  114 */     super(propsTable, doCopy);
/*  115 */     if (Trace.isOn) {
/*  116 */       if (propsTable.containsKey("XMSC_PASSWORD")) {
/*  117 */         HashMap<String, Object> propsNotPasswd = new HashMap<>(propsTable);
/*  118 */         propsNotPasswd.put("XMSC_PASSWORD", "********");
/*  119 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsPropertyContextImpl", "<init>(Map,boolean)", new Object[] { propsNotPasswd, Boolean.valueOf(doCopy) });
/*      */       } else {
/*  121 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsPropertyContextImpl", "<init>(Map,boolean)", new Object[] { propsTable, Boolean.valueOf(doCopy) });
/*      */       } 
/*      */     }
/*      */     
/*  125 */     this.creator = Thread.currentThread();
/*  126 */     this.createTime = System.currentTimeMillis();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  131 */     if (Trace.isOn) {
/*  132 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsPropertyContextImpl", "<init>(Map,boolean)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setProviderPropertyContext(JmsPropertyContext providerPropertyContext) {
/*  142 */     if (Trace.isOn) {
/*  143 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsPropertyContextImpl", "setProviderPropertyContext(JmsPropertyContext)", "setter", providerPropertyContext);
/*      */     }
/*      */ 
/*      */     
/*  147 */     this.providerPropertyContext = providerPropertyContext;
/*      */ 
/*      */ 
/*      */     
/*  151 */     if (isProviderCachingEnabled()) {
/*  152 */       initCache();
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
/*      */   public void setBatchProperties(Map<String, Object> properties) throws JMSException {
/*  164 */     if (Trace.isOn) {
/*  165 */       if (properties.containsKey("XMSC_PASSWORD")) {
/*  166 */         HashMap<String, Object> propsNotPasswd = new HashMap<>(properties);
/*  167 */         propsNotPasswd.put("XMSC_PASSWORD", "********");
/*  168 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsPropertyContextImpl", "setBatchProperties(Map)", new Object[] { propsNotPasswd });
/*      */       } else {
/*  170 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsPropertyContextImpl", "setBatchProperties(Map)", new Object[] { properties });
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  177 */     synchronized (super.getProperties()) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  182 */       Map<String, Object> oldProperties = Collections.synchronizedMap(new HashMap<>());
/*      */ 
/*      */       
/*  185 */       for (Map.Entry<String, Object> property : properties.entrySet()) {
/*      */         
/*  187 */         String key = property.getKey();
/*      */ 
/*      */ 
/*      */         
/*  191 */         Object oldObject = getObjectProperty(key);
/*      */         
/*  193 */         oldProperties.put(key, oldObject);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  199 */         setObjectPropertyInternal(key, properties.get(key), false);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  206 */       boolean validationSuccessful = true;
/*  207 */       Vector<String> failedValidations = new Vector<>();
/*      */ 
/*      */ 
/*      */       
/*  211 */       for (Map.Entry<String, Object> property : properties.entrySet()) {
/*      */         
/*  213 */         String key = property.getKey();
/*      */ 
/*      */         
/*  216 */         if (this.providerPropertyContext instanceof com.ibm.msg.client.provider.ProviderDestination || this.providerPropertyContext instanceof com.ibm.msg.client.provider.ProviderConnectionFactory) {
/*      */           
/*  218 */           boolean newStyleVal = true;
/*      */           try {
/*  220 */             newStyleVal = ((JmsValidationInterface)this.providerPropertyContext).validate(key, property.getValue());
/*      */           }
/*  222 */           catch (JMSException j) {
/*  223 */             if (Trace.isOn) {
/*  224 */               Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsPropertyContextImpl", "setBatchProperties(Map)", (Throwable)j, 1);
/*      */             }
/*      */             
/*  227 */             newStyleVal = false;
/*      */           } 
/*  229 */           if (!newStyleVal) {
/*  230 */             validationSuccessful = false;
/*      */ 
/*      */             
/*  233 */             failedValidations.add(key + " (" + property.getValue() + ")");
/*      */           }  continue;
/*      */         } 
/*      */         try {
/*  237 */           validateProperty(key, property.getValue());
/*      */         }
/*  239 */         catch (JMSException je) {
/*  240 */           if (Trace.isOn) {
/*  241 */             Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsPropertyContextImpl", "setBatchProperties(Map)", (Throwable)je, 2);
/*      */           }
/*      */           
/*  244 */           validationSuccessful = false;
/*      */ 
/*      */ 
/*      */           
/*  248 */           failedValidations.add(key + " (" + property.getValue() + ")");
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  255 */       String crossPropertyValidationSuccessful = null;
/*  256 */       if (this.providerPropertyContext instanceof com.ibm.msg.client.provider.ProviderDestination || this.providerPropertyContext instanceof com.ibm.msg.client.provider.ProviderConnectionFactory) {
/*  257 */         crossPropertyValidationSuccessful = ((JmsValidationInterface)this.providerPropertyContext).crossPropertyValidate();
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  263 */       if (!validationSuccessful) {
/*      */         
/*  265 */         for (Map.Entry<String, Object> property : oldProperties.entrySet()) {
/*      */           
/*  267 */           String key = property.getKey();
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  272 */           Object oldObject = property.getValue();
/*      */           
/*  274 */           setObjectPropertyInternal(key, oldObject, false);
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  280 */         if (failedValidations.size() > 0) {
/*      */           
/*  282 */           StringBuilder error = new StringBuilder();
/*      */           
/*  284 */           for (int i = 0; i < failedValidations.size(); i++) {
/*  285 */             error = error.append(failedValidations.get(i));
/*  286 */             if (i + 1 < failedValidations.size()) {
/*  287 */               error = error.append(", ");
/*      */             }
/*      */           } 
/*      */           
/*  291 */           HashMap<String, Object> inserts = new HashMap<>();
/*  292 */           inserts.put("XMSC_INSERT_PROPERTY", error);
/*  293 */           JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC3041", inserts);
/*      */           
/*  295 */           if (Trace.isOn) {
/*  296 */             Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsPropertyContextImpl", "setBatchProperties(Map)", (Throwable)je, 1);
/*      */           }
/*  298 */           throw je;
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  304 */       if (crossPropertyValidationSuccessful != null) {
/*      */ 
/*      */         
/*  307 */         Iterator<Map.Entry<String, Object>> entries = oldProperties.entrySet().iterator();
/*  308 */         while (entries.hasNext()) {
/*  309 */           Map.Entry<String, Object> entry = entries.next();
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  314 */           setObjectPropertyInternal(entry.getKey(), entry.getValue(), false);
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  319 */         String error = "Cross-property validation failed for " + crossPropertyValidationSuccessful;
/*      */         
/*  321 */         JMSException je = new JMSException(error);
/*  322 */         if (Trace.isOn) {
/*  323 */           Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsPropertyContextImpl", "setBatchProperties(Map)", (Throwable)je, 2);
/*      */         }
/*  325 */         throw je;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  330 */     if (Trace.isOn) {
/*  331 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsPropertyContextImpl", "setBatchProperties(Map)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBooleanProperty(String name, boolean value) throws JMSException {
/*  342 */     setObjectProperty(name, Boolean.valueOf(value));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setByteProperty(String name, byte value) throws JMSException {
/*  351 */     setObjectProperty(name, Byte.valueOf(value));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBytesProperty(String name, byte[] value) throws JMSException {
/*  360 */     setObjectProperty(name, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCharProperty(String name, char value) throws JMSException {
/*  369 */     setObjectProperty(name, Character.valueOf(value));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDoubleProperty(String name, double value) throws JMSException {
/*  378 */     setObjectProperty(name, Double.valueOf(value));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFloatProperty(String name, float value) throws JMSException {
/*  387 */     setObjectProperty(name, Float.valueOf(value));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setIntProperty(String name, int value) throws JMSException {
/*  396 */     setObjectProperty(name, Integer.valueOf(value));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLongProperty(String name, long value) throws JMSException {
/*  405 */     setObjectProperty(name, Long.valueOf(value));
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
/*      */   private void setObjectPropertyInternal(String name, Object value, boolean validationEnabledP) throws JMSException {
/*  423 */     boolean validationEnabled = validationEnabledP;
/*  424 */     boolean useCommonValidation = false;
/*  425 */     boolean useProviderValidation = false;
/*  426 */     boolean useProviderNameMapping = false;
/*  427 */     boolean useProviderCaching = false;
/*      */ 
/*      */ 
/*      */     
/*  431 */     if (this.providerPropertyContext instanceof com.ibm.msg.client.provider.ProviderDestination || this.providerPropertyContext instanceof com.ibm.msg.client.provider.ProviderConnectionFactory) {
/*  432 */       useProviderValidation = true;
/*  433 */       useProviderNameMapping = true;
/*  434 */       useProviderCaching = true;
/*  435 */     } else if (this.providerPropertyContext instanceof com.ibm.msg.client.provider.ProviderMessageProducer) {
/*  436 */       useCommonValidation = true;
/*  437 */       useProviderValidation = true;
/*      */     } else {
/*  439 */       useCommonValidation = true;
/*      */     } 
/*      */     
/*  442 */     if (this.settingDefaults) {
/*  443 */       validationEnabled = false;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  448 */     if (useCommonValidation && 
/*  449 */       name != null && validationEnabled) {
/*  450 */       validateProperty(name, value);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  455 */     if (useProviderValidation && 
/*  456 */       name != null && validationEnabled) {
/*  457 */       boolean validationSuccessful = ((JmsValidationInterface)this.providerPropertyContext).validate(name, value);
/*  458 */       if (!validationSuccessful) {
/*      */         
/*  460 */         HashMap<String, Object> inserts = new HashMap<>();
/*  461 */         inserts.put("XMSC_INSERT_NAME", name);
/*  462 */         inserts.put("XMSC_INSERT_VALUE", (value == null) ? "<null>" : value.toString());
/*  463 */         JMSException validationException = (JMSException)JmsErrorUtils.createException("JMSCC0005", inserts);
/*  464 */         if (Trace.isOn) {
/*  465 */           Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsPropertyContextImpl", "setObjectPropertyInternal(String,Object,boolean)", (Throwable)validationException, 1);
/*      */         }
/*  467 */         throw validationException;
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
/*  482 */     String canonicalName = name;
/*  483 */     Object canonicalValue = value;
/*  484 */     Vector<Object> mapResult = null;
/*      */     
/*  486 */     if (useProviderNameMapping) {
/*  487 */       mapResult = ((JmsValidationInterface)this.providerPropertyContext).mapToCanonical(name, value);
/*  488 */       if (mapResult != null) {
/*  489 */         canonicalName = (String)mapResult.get(0);
/*  490 */         canonicalValue = mapResult.get(1);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  497 */     boolean cacheSetOk = false;
/*  498 */     if (useProviderCaching) {
/*  499 */       cacheSetOk = ((JmsPropertyCacheInterface)this.providerPropertyContext).setCachedValue(canonicalName, canonicalValue);
/*      */     }
/*      */     
/*  502 */     if (cacheSetOk) {
/*  503 */       this.providerCacheSynched = false;
/*      */     } else {
/*  505 */       super.getProperties().put(canonicalName, canonicalValue);
/*      */ 
/*      */ 
/*      */       
/*  509 */       if (mapResult != null && mapResult.size() > 2) {
/*  510 */         int index = 2;
/*  511 */         while (index < mapResult.size()) {
/*  512 */           super.getProperties().put((String)mapResult.get(index), mapResult.get(index + 1));
/*  513 */           index += 2;
/*      */         } 
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getObjectProperty(String name) throws JMSException {
/*  534 */     if (Trace.isOn) {
/*  535 */       Trace.data(this, "getObjectProperty(String)", "via JmsPropertyContextImpl", name);
/*      */     }
/*      */     
/*  538 */     if (!(this.providerPropertyContext instanceof com.ibm.msg.client.provider.ProviderDestination) && !(this.providerPropertyContext instanceof com.ibm.msg.client.provider.ProviderConnectionFactory)) {
/*  539 */       Object result = super.getObjectProperty(name);
/*  540 */       return result;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  561 */     Object quickValue = ((JmsPropertyCacheInterface)this.providerPropertyContext).getCachedValue(name);
/*  562 */     if (quickValue != null) {
/*  563 */       if (Trace.isOn) {
/*  564 */         Trace.traceData(this, "getObjectProperty(String)", "Returning cached value.", quickValue);
/*      */       }
/*  566 */       return quickValue;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  571 */     if (Trace.isOn) {
/*  572 */       Trace.traceData(this, "getObjectProperty(String)", "Performing property mapping.");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  577 */     String canonicalKey = ((JmsValidationInterface)this.providerPropertyContext).getCanonicalKey(name);
/*      */ 
/*      */     
/*  580 */     Object canonicalValue = ((JmsPropertyCacheInterface)this.providerPropertyContext).getCachedValue(canonicalKey);
/*  581 */     if (canonicalValue == null) {
/*  582 */       canonicalValue = super.getProperties().get(canonicalKey);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  587 */     Object returnValue = ((JmsValidationInterface)this.providerPropertyContext).mapFromCanonical(name, canonicalValue);
/*      */ 
/*      */ 
/*      */     
/*  591 */     return returnValue;
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
/*      */   public void setObjectProperty(String name, Object value) throws JMSException {
/*  603 */     if (Trace.isOn) {
/*  604 */       if ("XMSC_PASSWORD".equals(name)) {
/*  605 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsPropertyContextImpl", "setObjectProperty(String,Object)", new Object[] { name, "********" });
/*      */       } else {
/*  607 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsPropertyContextImpl", "setObjectProperty(String,Object)", new Object[] { name, value });
/*      */       } 
/*      */     }
/*      */     
/*      */     try {
/*  612 */       setObjectPropertyInternal(name, value, true);
/*      */     }
/*  614 */     catch (JMSException e) {
/*  615 */       if (Trace.isOn) {
/*  616 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsPropertyContextImpl", "setObjectProperty(String,Object)", (Throwable)e);
/*      */       }
/*  618 */       if (Trace.isOn) {
/*  619 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsPropertyContextImpl", "setObjectProperty(String,Object)", (Throwable)e);
/*      */       }
/*  621 */       throw e;
/*      */     } 
/*  623 */     if (Trace.isOn) {
/*  624 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsPropertyContextImpl", "setObjectProperty(String,Object)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setShortProperty(String name, short value) throws JMSException {
/*  635 */     if (Trace.isOn) {
/*  636 */       Trace.data(this, "setShortProperty(String,short)", "via JmsPropertyContextImpl", name);
/*      */     }
/*      */     
/*  639 */     setObjectProperty(name, Short.valueOf(value));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setStringProperty(String name, String value) throws JMSException {
/*  649 */     setObjectProperty(name, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean propertyExists(String name) throws JMSException {
/*  660 */     if (Trace.isOn) {
/*  661 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsPropertyContextImpl", "propertyExists(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/*  665 */     boolean traceRet1 = false;
/*      */     
/*  667 */     if (this.providerPropertyContext instanceof JmsPropertyCacheInterface) {
/*  668 */       traceRet1 = ((JmsPropertyCacheInterface)this.providerPropertyContext).containsCachedValue(name);
/*      */     }
/*      */     
/*  671 */     if (!traceRet1) {
/*  672 */       traceRet1 = super.propertyExists(name);
/*      */     }
/*  674 */     if (Trace.isOn) {
/*  675 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsPropertyContextImpl", "propertyExists(String)", 
/*  676 */           Boolean.valueOf(traceRet1));
/*      */     }
/*  678 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void flushPendingPropertyUpdates() {
/*  689 */     if (Trace.isOn) {
/*  690 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsPropertyContextImpl", "flushPendingPropertyUpdates()");
/*      */     }
/*      */     
/*  693 */     if (isProviderCachingEnabled()) {
/*  694 */       flushCache();
/*      */     }
/*  696 */     if (Trace.isOn) {
/*  697 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsPropertyContextImpl", "flushPendingPropertyUpdates()");
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
/*      */   protected void validateProperty(String name, Object value) throws JMSException {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Map<String, Object> getProperties() {
/*  721 */     if (isProviderCachingEnabled()) {
/*  722 */       flushCache();
/*      */     }
/*  724 */     return super.getProperties();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void initCache() {
/*  731 */     if (Trace.isOn) {
/*  732 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsPropertyContextImpl", "initCache()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  740 */     Map<String, Object> map = super.getProperties();
/*  741 */     Map<String, Object> propertyMapCopy = null;
/*  742 */     synchronized (map) {
/*  743 */       propertyMapCopy = new HashMap<>(map);
/*      */     } 
/*      */     
/*  746 */     Iterator<Map.Entry<String, Object>> entryIterator = propertyMapCopy.entrySet().iterator();
/*  747 */     while (entryIterator.hasNext()) {
/*  748 */       Map.Entry<String, Object> entry = entryIterator.next();
/*  749 */       ((JmsPropertyCacheInterface)this.providerPropertyContext).setCachedValue(entry.getKey(), entry.getValue());
/*      */     } 
/*      */     
/*  752 */     if (Trace.isOn) {
/*  753 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsPropertyContextImpl", "initCache()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void flushCache() {
/*  763 */     boolean isProviderCachingEnabled = isProviderCachingEnabled();
/*      */     
/*  765 */     if (isProviderCachingEnabled && !this.providerCacheSynched) {
/*  766 */       if (Trace.isOn) {
/*  767 */         Trace.data(this, "flushCache()", "Synchronising the current provider cache into the propertyContext", null);
/*      */       }
/*      */       
/*  770 */       Map<String, Object> pc = super.getProperties();
/*  771 */       ((JmsPropertyCacheInterface)this.providerPropertyContext).getCachedValueAll(pc);
/*  772 */       this.providerCacheSynched = true;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clear() {
/*  781 */     if (Trace.isOn) {
/*  782 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsPropertyContextImpl", "clear()");
/*      */     }
/*  784 */     if (this.providerPropertyContext instanceof JmsPropertyCacheInterface) {
/*  785 */       ((JmsPropertyCacheInterface)this.providerPropertyContext).clearCachedValueAll();
/*      */     }
/*  787 */     getProperties().clear();
/*  788 */     if (Trace.isOn) {
/*  789 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsPropertyContextImpl", "clear()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean containsKey(Object key) {
/*  800 */     boolean result = getProperties().containsKey(key);
/*  801 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean containsValue(Object value) {
/*  810 */     boolean result = getProperties().containsValue(value);
/*  811 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Set<Map.Entry<String, Object>> entrySet() {
/*  822 */     if (Trace.isOn) {
/*  823 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsPropertyContextImpl", "entrySet()");
/*      */     }
/*      */     
/*  826 */     Set<Map.Entry<String, Object>> traceRet1 = getProperties().entrySet();
/*      */     
/*  828 */     if (Trace.isOn) {
/*  829 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsPropertyContextImpl", "entrySet()", "@" + Integer.toHexString(System.identityHashCode(traceRet1)));
/*      */     }
/*  831 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object get(Object key) {
/*  841 */     Object traceRet1 = getProperties().get(key);
/*      */     
/*  843 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isEmpty() {
/*  851 */     boolean traceRet1 = getProperties().isEmpty();
/*  852 */     if (Trace.isOn) {
/*  853 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsPropertyContextImpl", "isEmpty()", "getter", 
/*  854 */           Boolean.valueOf(traceRet1));
/*      */     }
/*  856 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Set<String> keySet() {
/*  865 */     Set<String> result = getProperties().keySet();
/*  866 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object put(String arg0, Object arg1) {
/*  876 */     if (Trace.isOn) {
/*  877 */       if ("XMSC_PASSWORD".equals(arg0)) {
/*  878 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsPropertyContextImpl", "put(Object,Object)", new Object[] { arg0, "********" });
/*      */       } else {
/*  880 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsPropertyContextImpl", "put(Object,Object)", new Object[] { arg0, arg1 });
/*      */       } 
/*      */     }
/*  883 */     Object traceRet1 = null;
/*      */     try {
/*  885 */       traceRet1 = getObjectProperty(arg0.toString());
/*  886 */       setObjectProperty(arg0.toString(), arg1);
/*      */     }
/*  888 */     catch (JMSException e) {
/*  889 */       HashMap<String, Object> map = new HashMap<>();
/*  890 */       map.put("arg0", arg0);
/*  891 */       map.put("arg1", arg1);
/*  892 */       map.put("exception", e);
/*  893 */       Trace.ffst(this, "put(Object arg0, Object arg1)", "XJ00A001", map, null);
/*      */     } 
/*      */     
/*  896 */     if (Trace.isOn) {
/*  897 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsPropertyContextImpl", "put(Object,Object)", traceRet1);
/*      */     }
/*  899 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void putAll(Map<? extends String, ? extends Object> arg0) {
/*  909 */     HashMap<String, Object> propsCopy = new HashMap<>(arg0);
/*  910 */     if (Trace.isOn) {
/*  911 */       if (arg0.containsKey("XMSC_PASSWORD")) {
/*  912 */         propsCopy.put("XMSC_PASSWORD", "********");
/*      */       }
/*  914 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsPropertyContextImpl", "putAll(Map)", new Object[] { propsCopy });
/*      */     } 
/*      */     
/*  917 */     Set<Map.Entry<String, Object>> entrySet = propsCopy.entrySet();
/*  918 */     Iterator<Map.Entry<String, Object>> iterator = entrySet.iterator();
/*  919 */     while (iterator.hasNext()) {
/*  920 */       Map.Entry<String, Object> entry = iterator.next();
/*      */       try {
/*  922 */         setObjectProperty(entry.getKey(), entry.getValue());
/*      */       }
/*  924 */       catch (JMSException e) {
/*  925 */         HashMap<String, Object> map = new HashMap<>();
/*  926 */         map.put("arg0", arg0);
/*  927 */         map.put("exception", e);
/*  928 */         Trace.ffst(this, "putAll(Map arg0)", "XJ00A002", map, null);
/*      */       } 
/*      */     } 
/*      */     
/*  932 */     if (Trace.isOn) {
/*  933 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsPropertyContextImpl", "putAll(Map)");
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
/*      */   public Object remove(Object key) {
/*  945 */     if (Trace.isOn) {
/*  946 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsPropertyContextImpl", "remove(Object)", new Object[] { key });
/*      */     }
/*      */     
/*  949 */     if (this.providerPropertyContext instanceof JmsPropertyCacheInterface) {
/*  950 */       ((JmsPropertyCacheInterface)this.providerPropertyContext).clearCachedValue((String)key);
/*      */     }
/*  952 */     Object traceRet1 = getProperties().remove(key);
/*      */     
/*  954 */     if (Trace.isOn) {
/*  955 */       if ("XMSC_PASSWORD".equals(key)) {
/*  956 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsPropertyContextImpl", "remove(Object)", "********");
/*      */       } else {
/*  958 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsPropertyContextImpl", "remove(Object)", traceRet1);
/*      */       } 
/*      */     }
/*  961 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int size() {
/*  969 */     if (Trace.isOn) {
/*  970 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsPropertyContextImpl", "size()");
/*      */     }
/*  972 */     int traceRet1 = getProperties().size();
/*  973 */     if (Trace.isOn) {
/*  974 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsPropertyContextImpl", "size()", 
/*  975 */           Integer.valueOf(traceRet1));
/*      */     }
/*  977 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<Object> values() {
/*  987 */     if (Trace.isOn) {
/*  988 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsPropertyContextImpl", "values()");
/*      */     }
/*      */     
/*  991 */     Map<String, Object> props = getProperties();
/*  992 */     Collection<Object> traceRet1 = props.values();
/*      */     
/*  994 */     if (Trace.isOn) {
/*  995 */       if (props.containsKey("XMSC_PASSWORD")) {
/*  996 */         HashMap<String, Object> propsNotPasswd = new HashMap<>(props);
/*  997 */         propsNotPasswd.put("XMSC_PASSWORD", "********");
/*  998 */         Collection<Object> clln = propsNotPasswd.values();
/*  999 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsPropertyContextImpl", "values()", clln);
/*      */       } else {
/* 1001 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsPropertyContextImpl", "values()", traceRet1);
/*      */       } 
/*      */     }
/* 1004 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isProviderCachingEnabled() {
/* 1015 */     if ((this.providerPropertyContext instanceof com.ibm.msg.client.provider.ProviderDestination == true || this.providerPropertyContext instanceof com.ibm.msg.client.provider.ProviderConnectionFactory == true) && this.providerPropertyContext instanceof JmsPropertyCacheInterface)
/*      */     {
/* 1017 */       return true;
/*      */     }
/* 1019 */     return false;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsPropertyContextImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */