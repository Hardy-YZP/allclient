/*      */ package com.ibm.msg.client.wmq.common.internal.messages;
/*      */ 
/*      */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQUtils;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.Enumeration;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import javax.jms.JMSException;
/*      */ import javax.jms.MessageFormatException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ abstract class WMQMessageProps
/*      */   implements JmsPropertyContext
/*      */ {
/*      */   private static final long serialVersionUID = -5436164779134941791L;
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/messages/WMQMessageProps.java";
/*      */   
/*      */   static {
/*   70 */     if (Trace.isOn) {
/*   71 */       Trace.data("com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/messages/WMQMessageProps.java");
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
/*      */   
/*   93 */   protected Map<String, Object> properties = Collections.synchronizedMap(new HashMap<>(15));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBatchProperties(Map batchedProperties) throws JMSException {
/*  104 */     if (Trace.isOn) {
/*  105 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "setBatchProperties(Map)", "setter", batchedProperties);
/*      */     }
/*      */ 
/*      */     
/*  109 */     for (Map.Entry<Object, Object> batchedProperty : (Iterable<Map.Entry<Object, Object>>)batchedProperties.entrySet())
/*      */     {
/*      */       
/*  112 */       setObjectProperty((String)batchedProperty.getKey(), batchedProperty.getValue());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBooleanProperty(String name, boolean value) throws JMSException {
/*  122 */     if (Trace.isOn) {
/*  123 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "setBooleanProperty(String,boolean)", "setter", name);
/*      */     }
/*      */ 
/*      */     
/*  127 */     this.properties.put(name, Boolean.valueOf(value));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setByteProperty(String name, byte value) throws JMSException {
/*  136 */     if (Trace.isOn) {
/*  137 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "setByteProperty(String,byte)", "setter", name);
/*      */     }
/*      */ 
/*      */     
/*  141 */     this.properties.put(name, Byte.valueOf(value));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBytesProperty(String name, byte[] value) throws JMSException {
/*  150 */     if (Trace.isOn) {
/*  151 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "setBytesProperty(String,byte [ ])", new Object[] { name, value });
/*      */     }
/*      */ 
/*      */     
/*  155 */     HashMap<String, Object> inserts = new HashMap<>();
/*  156 */     inserts.put("XMSC_INSERT_PROPERTY", name);
/*  157 */     inserts.put("XMSC_INSERT_VALUE", value);
/*  158 */     inserts.put("XMSC_INSERT_TYPE", (new byte[0]).getClass());
/*  159 */     JMSException ex = (JMSException)NLSServices.createException("JMSCMQ0008", inserts);
/*  160 */     if (Trace.isOn) {
/*  161 */       Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "setBytesProperty(String,byte [ ])", (Throwable)ex);
/*      */     }
/*      */     
/*  164 */     throw ex;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCharProperty(String name, char value) throws JMSException {
/*  173 */     if (Trace.isOn) {
/*  174 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "setCharProperty(String,char)", new Object[] { name, 
/*  175 */             Character.valueOf(value) });
/*      */     }
/*      */     
/*  178 */     HashMap<String, Object> inserts = new HashMap<>();
/*  179 */     inserts.put("XMSC_INSERT_PROPERTY", name);
/*  180 */     inserts.put("XMSC_INSERT_VALUE", Character.valueOf(value));
/*  181 */     inserts.put("XMSC_INSERT_TYPE", Character.class);
/*  182 */     JMSException ex = (JMSException)NLSServices.createException("JMSCMQ0008", inserts);
/*  183 */     if (Trace.isOn) {
/*  184 */       Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "setCharProperty(String,char)", (Throwable)ex);
/*      */     }
/*      */     
/*  187 */     throw ex;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDoubleProperty(String name, double value) throws JMSException {
/*  196 */     if (Trace.isOn) {
/*  197 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "setDoubleProperty(String,double)", "setter", name);
/*      */     }
/*      */ 
/*      */     
/*  201 */     this.properties.put(name, Double.valueOf(value));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFloatProperty(String name, float value) throws JMSException {
/*  210 */     if (Trace.isOn) {
/*  211 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "setFloatProperty(String,float)", "setter", name);
/*      */     }
/*      */ 
/*      */     
/*  215 */     this.properties.put(name, Float.valueOf(value));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setIntProperty(String name, int value) throws JMSException {
/*  224 */     if (Trace.isOn) {
/*  225 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "setIntProperty(String,int)", "setter", name);
/*      */     }
/*      */ 
/*      */     
/*  229 */     this.properties.put(name, Integer.valueOf(value));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLongProperty(String name, long value) throws JMSException {
/*  238 */     if (Trace.isOn) {
/*  239 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "setLongProperty(String,long)", "setter", name);
/*      */     }
/*      */ 
/*      */     
/*  243 */     this.properties.put(name, Long.valueOf(value));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setObjectProperty(String name, Object value) throws JMSException {
/*  253 */     if (Trace.isOn) {
/*  254 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "setObjectProperty(String,Object)", "setter", name);
/*      */     }
/*      */ 
/*      */     
/*  258 */     this.properties.put(name, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setShortProperty(String name, short value) throws JMSException {
/*  267 */     if (Trace.isOn) {
/*  268 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "setShortProperty(String,short)", "setter", name);
/*      */     }
/*      */ 
/*      */     
/*  272 */     this.properties.put(name, Short.valueOf(value));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setStringProperty(String name, String value) throws JMSException {
/*  282 */     if (Trace.isOn) {
/*  283 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "setStringProperty(String,String)", "setter", name);
/*      */     }
/*      */ 
/*      */     
/*  287 */     this.properties.put(name, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getBooleanProperty(String name) throws JMSException {
/*  297 */     Object value = getObjectProperty(name);
/*  298 */     boolean result = parseBoolean(value, name, JMSException.class);
/*  299 */     if (Trace.isOn) {
/*  300 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "getBooleanProperty(String)", "getter", 
/*  301 */           Boolean.valueOf(result));
/*      */     }
/*  303 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte getByteProperty(String name) throws JMSException {
/*  313 */     Object value = getObjectProperty(name);
/*  314 */     byte result = parseByte(value, name, JMSException.class);
/*  315 */     if (Trace.isOn) {
/*  316 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "getByteProperty(String)", "getter", 
/*  317 */           Byte.valueOf(result));
/*      */     }
/*  319 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getBytesProperty(String name) throws JMSException {
/*  328 */     if (Trace.isOn) {
/*  329 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "getBytesProperty(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/*  333 */     HashMap<String, Object> inserts = new HashMap<>();
/*  334 */     inserts.put("XMSC_INSERT_PROPERTY", name);
/*  335 */     inserts.put("XMSC_INSERT_TYPE", (new byte[0]).getClass());
/*  336 */     JMSException ex = (JMSException)NLSServices.createException("JMSCMQ0008", inserts);
/*  337 */     if (Trace.isOn) {
/*  338 */       Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "getBytesProperty(String)", (Throwable)ex);
/*      */     }
/*      */     
/*  341 */     throw ex;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public char getCharProperty(String name) throws JMSException {
/*  350 */     if (Trace.isOn) {
/*  351 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "getCharProperty(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/*  355 */     HashMap<String, Object> inserts = new HashMap<>();
/*  356 */     inserts.put("XMSC_INSERT_PROPERTY", name);
/*  357 */     inserts.put("XMSC_INSERT_TYPE", Character.class);
/*  358 */     JMSException ex = (JMSException)NLSServices.createException("JMSCMQ0008", inserts);
/*  359 */     if (Trace.isOn) {
/*  360 */       Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "getCharProperty(String)", (Throwable)ex);
/*      */     }
/*      */     
/*  363 */     throw ex;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getDoubleProperty(String name) throws JMSException {
/*  373 */     Object value = getObjectProperty(name);
/*  374 */     double result = parseDouble(value, name, JMSException.class);
/*  375 */     if (Trace.isOn) {
/*  376 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "getDoubleProperty(String)", "getter", 
/*  377 */           Double.valueOf(result));
/*      */     }
/*  379 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getFloatProperty(String name) throws JMSException {
/*  389 */     Object value = getObjectProperty(name);
/*  390 */     float result = parseFloat(value, name, JMSException.class);
/*  391 */     if (Trace.isOn) {
/*  392 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "getFloatProperty(String)", "getter", 
/*  393 */           Float.valueOf(result));
/*      */     }
/*  395 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getIntProperty(String name) throws JMSException {
/*  405 */     Object value = getObjectProperty(name);
/*  406 */     int result = parseInt(value, name, JMSException.class);
/*  407 */     if (Trace.isOn) {
/*  408 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "getIntProperty(String)", "getter", 
/*  409 */           Integer.valueOf(result));
/*      */     }
/*  411 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getLongProperty(String name) throws JMSException {
/*  421 */     Object value = getObjectProperty(name);
/*  422 */     long result = parseLong(value, name, JMSException.class);
/*  423 */     if (Trace.isOn) {
/*  424 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "getLongProperty(String)", "getter", 
/*  425 */           Long.valueOf(result));
/*      */     }
/*  427 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getObjectProperty(String name) throws JMSException {
/*  437 */     Object result = this.properties.get(name);
/*  438 */     if (Trace.isOn) {
/*  439 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "getObjectProperty(String)", "getter", result);
/*      */     }
/*      */     
/*  442 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Enumeration<String> getPropertyNames() throws JMSException {
/*  452 */     Set<String> sanitizedPropertyNames = null;
/*  453 */     if (this.properties.containsKey("XMSC_CLIENT_ID")) {
/*  454 */       sanitizedPropertyNames = new HashSet<>(this.properties.keySet());
/*  455 */       sanitizedPropertyNames.remove("XMSC_CLIENT_ID");
/*      */     } else {
/*  457 */       sanitizedPropertyNames = this.properties.keySet();
/*      */     } 
/*  459 */     Enumeration<String> traceRet1 = WMQUtils.enumerationFromIterable(sanitizedPropertyNames);
/*  460 */     if (Trace.isOn) {
/*  461 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "getPropertyNames()", "getter", traceRet1);
/*      */     }
/*      */     
/*  464 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public short getShortProperty(String name) throws JMSException {
/*  474 */     Object value = getObjectProperty(name);
/*  475 */     short result = parseShort(value, name, JMSException.class);
/*  476 */     if (Trace.isOn) {
/*  477 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "getShortProperty(String)", "getter", 
/*  478 */           Short.valueOf(result));
/*      */     }
/*  480 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getStringProperty(String name) throws JMSException {
/*  490 */     Object obj = getObjectProperty(name);
/*      */     
/*  492 */     String value = null;
/*      */     
/*  494 */     if (obj instanceof String || obj == null) {
/*      */       
/*  496 */       value = (String)obj;
/*      */     }
/*      */     else {
/*      */       
/*  500 */       value = obj.toString();
/*      */     } 
/*      */     
/*  503 */     if (Trace.isOn) {
/*  504 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "getStringProperty(String)", "getter", value);
/*      */     }
/*      */     
/*  507 */     return value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean propertyExists(String name) throws JMSException {
/*  517 */     boolean result = this.properties.containsKey(name);
/*  518 */     if (Trace.isOn) {
/*  519 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "propertyExists(String)", "getter", 
/*  520 */           Boolean.valueOf(result));
/*      */     }
/*  522 */     return result;
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
/*      */   static void throwBadConvertException(Object obj, String propName, String dType, Class<?> exceptionClass) throws JMSException {
/*  535 */     if (Trace.isOn) {
/*  536 */       Trace.entry("com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "throwBadConvertException(Object,String,String,Class<?>)", new Object[] { obj, propName, dType, exceptionClass });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  541 */     String clsName = null;
/*      */     
/*  543 */     if (!(obj instanceof byte[])) {
/*      */       
/*  545 */       clsName = obj.getClass().getName();
/*      */       
/*  547 */       int index = 0;
/*      */ 
/*      */       
/*  550 */       if ((index = clsName.lastIndexOf('.')) != 0) {
/*  551 */         clsName = clsName.substring(index + 1);
/*      */       }
/*      */     } else {
/*  554 */       clsName = "Byte[]";
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  561 */     String msgId = "JMSCMQ1055";
/*  562 */     if (JMSException.class == exceptionClass) {
/*  563 */       msgId = "JMSCMQ1055";
/*  564 */     } else if (MessageFormatException.class == exceptionClass) {
/*  565 */       msgId = "JMSCMQ0008";
/*      */     } else {
/*  567 */       HashMap<String, Object> info = new HashMap<>();
/*  568 */       info.put("class", exceptionClass);
/*  569 */       Trace.ffst("WMQMessageProps", "throwBadConvertException", "XN00J001", info, JMSException.class);
/*      */     } 
/*      */     
/*  572 */     HashMap<String, Object> inserts = new HashMap<>();
/*  573 */     inserts.put("XMSC_INSERT_PROPERTY", propName);
/*  574 */     inserts.put("XMSC_INSERT_TYPE", clsName);
/*  575 */     inserts.put("XMSC_INSERT_OTHER_TYPE", dType);
/*  576 */     JMSException ex = (JMSException)NLSServices.createException(msgId, inserts);
/*      */     
/*  578 */     if (Trace.isOn) {
/*  579 */       Trace.throwing("com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "throwBadConvertException(Object,String,String,Class<?>)", (Throwable)ex);
/*      */     }
/*      */     
/*  582 */     throw ex;
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
/*      */   protected static boolean parseBoolean(Object obj, String name, Class<?> exceptionClass) throws JMSException {
/*  599 */     boolean value = false;
/*      */     
/*  601 */     if (obj instanceof Boolean) {
/*      */       
/*  603 */       value = ((Boolean)obj).booleanValue();
/*      */     }
/*  605 */     else if (obj instanceof String) {
/*  606 */       value = Boolean.valueOf((String)obj).booleanValue();
/*      */     } else {
/*  608 */       if (obj == null)
/*      */       {
/*      */         
/*  611 */         throw new NullPointerException();
/*      */       }
/*      */ 
/*      */       
/*  615 */       throwBadConvertException(obj, name, "Boolean", exceptionClass);
/*      */     } 
/*      */ 
/*      */     
/*  619 */     return value;
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
/*      */   protected static byte parseByte(Object obj, String name, Class<?> exceptionClass) throws JMSException {
/*  635 */     byte value = 0;
/*      */     
/*  637 */     if (obj instanceof Byte) {
/*      */ 
/*      */       
/*  640 */       value = ((Byte)obj).byteValue();
/*      */     }
/*  642 */     else if (obj instanceof String) {
/*      */       
/*  644 */       value = Byte.parseByte((String)obj);
/*      */     } else {
/*  646 */       if (obj == null)
/*      */       {
/*      */         
/*  649 */         throw new NullPointerException();
/*      */       }
/*      */ 
/*      */       
/*  653 */       throwBadConvertException(obj, name, "Byte", exceptionClass);
/*      */     } 
/*      */     
/*  656 */     return value;
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
/*      */   protected static double parseDouble(Object obj, String name, Class<?> exceptionClass) throws JMSException {
/*  672 */     double value = 0.0D;
/*      */     
/*  674 */     if (obj instanceof Double) {
/*      */ 
/*      */       
/*  677 */       value = ((Double)obj).doubleValue();
/*      */     }
/*  679 */     else if (obj instanceof String) {
/*      */       
/*  681 */       value = Double.parseDouble((String)obj);
/*      */     }
/*  683 */     else if (obj instanceof Float) {
/*      */       
/*  685 */       value = ((Float)obj).doubleValue();
/*      */     } else {
/*  687 */       if (obj == null)
/*      */       {
/*      */         
/*  690 */         throw new NullPointerException();
/*      */       }
/*      */ 
/*      */       
/*  694 */       throwBadConvertException(obj, name, "Double", exceptionClass);
/*      */     } 
/*      */     
/*  697 */     return value;
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
/*      */   protected static float parseFloat(Object obj, String name, Class<?> exceptionClass) throws JMSException {
/*  713 */     float value = 0.0F;
/*      */     
/*  715 */     if (obj instanceof Float) {
/*      */       
/*  717 */       value = ((Float)obj).floatValue();
/*      */     }
/*  719 */     else if (obj instanceof String) {
/*      */       
/*  721 */       value = Float.parseFloat((String)obj);
/*      */     } else {
/*  723 */       if (obj == null)
/*      */       {
/*      */ 
/*      */         
/*  727 */         throw new NullPointerException();
/*      */       }
/*      */ 
/*      */       
/*  731 */       throwBadConvertException(obj, name, "Float", exceptionClass);
/*      */     } 
/*      */ 
/*      */     
/*  735 */     return value;
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
/*      */   protected static int parseInt(Object obj, String name, Class<?> exceptionClass) throws JMSException {
/*  751 */     int value = 0;
/*      */     
/*  753 */     if (obj instanceof Integer) {
/*      */       
/*  755 */       value = ((Integer)obj).intValue();
/*      */     }
/*  757 */     else if (obj instanceof String) {
/*      */       
/*  759 */       value = Integer.parseInt((String)obj);
/*      */     }
/*  761 */     else if (obj instanceof Byte) {
/*      */       
/*  763 */       value = ((Byte)obj).intValue();
/*      */     }
/*  765 */     else if (obj instanceof Short) {
/*      */       
/*  767 */       value = ((Short)obj).intValue();
/*      */     } else {
/*  769 */       if (obj == null)
/*      */       {
/*      */ 
/*      */         
/*  773 */         throw new NullPointerException();
/*      */       }
/*      */ 
/*      */       
/*  777 */       throwBadConvertException(obj, name, "Integer", exceptionClass);
/*      */     } 
/*      */     
/*  780 */     return value;
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
/*      */   protected static long parseLong(Object obj, String name, Class<?> exceptionClass) throws JMSException {
/*  796 */     long value = 0L;
/*      */     
/*  798 */     if (obj instanceof Long) {
/*      */ 
/*      */       
/*  801 */       value = ((Long)obj).longValue();
/*      */     }
/*  803 */     else if (obj instanceof String) {
/*      */       
/*  805 */       value = Long.parseLong((String)obj);
/*      */     }
/*  807 */     else if (obj instanceof Byte) {
/*      */       
/*  809 */       value = ((Byte)obj).longValue();
/*      */     }
/*  811 */     else if (obj instanceof Short) {
/*      */       
/*  813 */       value = ((Short)obj).longValue();
/*      */     }
/*  815 */     else if (obj instanceof Integer) {
/*      */       
/*  817 */       value = ((Integer)obj).longValue();
/*      */     } else {
/*  819 */       if (obj == null)
/*      */       {
/*      */ 
/*      */         
/*  823 */         throw new NullPointerException();
/*      */       }
/*      */ 
/*      */       
/*  827 */       throwBadConvertException(obj, name, "Long", exceptionClass);
/*      */     } 
/*      */ 
/*      */     
/*  831 */     return value;
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
/*      */   protected static short parseShort(Object obj, String name, Class<?> exceptionClass) throws JMSException {
/*  847 */     short value = 0;
/*      */     
/*  849 */     if (obj instanceof Short) {
/*      */ 
/*      */       
/*  852 */       value = ((Short)obj).shortValue();
/*      */     }
/*  854 */     else if (obj instanceof String) {
/*      */       
/*  856 */       value = Short.parseShort((String)obj);
/*      */     }
/*  858 */     else if (obj instanceof Byte) {
/*      */       
/*  860 */       value = ((Byte)obj).shortValue();
/*      */     } else {
/*  862 */       if (obj == null)
/*      */       {
/*      */ 
/*      */         
/*  866 */         throw new NullPointerException();
/*      */       }
/*      */ 
/*      */       
/*  870 */       throwBadConvertException(obj, name, "Short", exceptionClass);
/*      */     } 
/*      */     
/*  873 */     return value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clearProperties() throws JMSException {
/*  884 */     if (Trace.isOn) {
/*  885 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "clearProperties()");
/*      */     }
/*      */ 
/*      */     
/*  889 */     this.properties.clear();
/*  890 */     if (Trace.isOn) {
/*  891 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "clearProperties()");
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
/*      */   public void removeProperty(String name) throws JMSException {
/*  910 */     if (Trace.isOn) {
/*  911 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "removeProperty(String)", new Object[] { name });
/*      */     }
/*      */     
/*  914 */     this.properties.remove(name);
/*  915 */     if (Trace.isOn) {
/*  916 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "removeProperty(String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clear() {
/*  926 */     if (Trace.isOn) {
/*  927 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "clear()");
/*      */     }
/*      */     
/*  930 */     this.properties.clear();
/*  931 */     if (Trace.isOn) {
/*  932 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "clear()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean containsKey(Object key) {
/*  942 */     if (Trace.isOn) {
/*  943 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "containsKey(Object)", new Object[] { key });
/*      */     }
/*      */     
/*  946 */     boolean traceRet1 = this.properties.containsKey(key);
/*  947 */     if (Trace.isOn) {
/*  948 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "containsKey(Object)", 
/*  949 */           Boolean.valueOf(traceRet1));
/*      */     }
/*  951 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean containsValue(Object value) {
/*  958 */     if (Trace.isOn) {
/*  959 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "containsValue(Object)", new Object[] { value });
/*      */     }
/*      */     
/*  962 */     boolean traceRet1 = this.properties.containsValue(value);
/*  963 */     if (Trace.isOn) {
/*  964 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "containsValue(Object)", 
/*  965 */           Boolean.valueOf(traceRet1));
/*      */     }
/*  967 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Set<Map.Entry<String, Object>> entrySet() {
/*  974 */     if (Trace.isOn) {
/*  975 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "entrySet()");
/*      */     }
/*      */     
/*  978 */     Set<Map.Entry<String, Object>> traceRet1 = this.properties.entrySet();
/*  979 */     if (Trace.isOn) {
/*  980 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "entrySet()", traceRet1);
/*      */     }
/*      */     
/*  983 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object get(Object key) {
/*  990 */     if (Trace.isOn) {
/*  991 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "get(Object)", new Object[] { key });
/*      */     }
/*      */     
/*  994 */     Object traceRet1 = this.properties.get(key);
/*  995 */     if (Trace.isOn) {
/*  996 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "get(Object)", traceRet1);
/*      */     }
/*      */     
/*  999 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isEmpty() {
/* 1006 */     boolean traceRet1 = this.properties.isEmpty();
/* 1007 */     if (Trace.isOn) {
/* 1008 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "isEmpty()", "getter", 
/* 1009 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 1011 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Set<String> keySet() {
/* 1018 */     if (Trace.isOn) {
/* 1019 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "keySet()");
/*      */     }
/*      */     
/* 1022 */     Set<String> traceRet1 = this.properties.keySet();
/* 1023 */     if (Trace.isOn) {
/* 1024 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "keySet()", traceRet1);
/*      */     }
/*      */     
/* 1027 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object put(String arg0, Object arg1) {
/* 1034 */     if (Trace.isOn) {
/* 1035 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "put(String,Object)", new Object[] { arg0, arg1 });
/*      */     }
/*      */     
/* 1038 */     Object traceRet1 = this.properties.put(arg0, arg1);
/* 1039 */     if (Trace.isOn) {
/* 1040 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "put(String,Object)", traceRet1);
/*      */     }
/*      */     
/* 1043 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void putAll(Map<? extends String, ?> arg0) {
/* 1051 */     if (Trace.isOn) {
/* 1052 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "putAll(Map)", new Object[] { arg0 });
/*      */     }
/*      */     
/* 1055 */     this.properties.putAll(arg0);
/* 1056 */     if (Trace.isOn) {
/* 1057 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "putAll(Map)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object remove(Object key) {
/* 1067 */     if (Trace.isOn) {
/* 1068 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "remove(Object)", new Object[] { key });
/*      */     }
/*      */     
/* 1071 */     Object traceRet1 = this.properties.remove(key);
/* 1072 */     if (Trace.isOn) {
/* 1073 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "remove(Object)", traceRet1);
/*      */     }
/*      */     
/* 1076 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int size() {
/* 1083 */     if (Trace.isOn) {
/* 1084 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "size()");
/*      */     }
/*      */     
/* 1087 */     int traceRet1 = this.properties.size();
/* 1088 */     if (Trace.isOn) {
/* 1089 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "size()", 
/* 1090 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1092 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<Object> values() {
/* 1099 */     if (Trace.isOn) {
/* 1100 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "values()");
/*      */     }
/*      */     
/* 1103 */     Collection<Object> traceRet1 = this.properties.values();
/* 1104 */     if (Trace.isOn) {
/* 1105 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageProps", "values()", traceRet1);
/*      */     }
/*      */     
/* 1108 */     return traceRet1;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\common\internal\messages\WMQMessageProps.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */