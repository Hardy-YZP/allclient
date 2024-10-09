/*      */ package com.ibm.msg.client.wmq.messages;
/*      */ 
/*      */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQUtils;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.Enumeration;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
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
/*      */ abstract class TransientMessageProps
/*      */   implements JmsPropertyContext
/*      */ {
/*      */   private static final long serialVersionUID = -8817434776141727485L;
/*      */   
/*      */   static {
/*   61 */     if (Trace.isOn) {
/*   62 */       Trace.data("com.ibm.msg.client.wmq.messages.TransientMessageProps", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.factories/src/com/ibm/msg/client/wmq/messages/TransientMessageProps.java");
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
/*   81 */   protected Map<String, Object> properties = Collections.synchronizedMap(new HashMap<>(15));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBatchProperties(Map<String, Object> batchedProperties) throws JMSException {
/*   89 */     if (Trace.isOn) {
/*   90 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "setBatchProperties(Map<String , Object>)", "setter", batchedProperties);
/*      */     }
/*      */ 
/*      */     
/*   94 */     Iterator<Map.Entry<String, Object>> entries = batchedProperties.entrySet().iterator();
/*      */     
/*   96 */     while (entries.hasNext()) {
/*      */ 
/*      */       
/*   99 */       Map.Entry<String, Object> entry = entries.next();
/*      */ 
/*      */       
/*  102 */       setObjectProperty(entry.getKey(), entry.getValue());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBooleanProperty(String name, boolean value) throws JMSException {
/*  110 */     if (Trace.isOn) {
/*  111 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "setBooleanProperty(String,boolean)", "setter", name);
/*      */     }
/*      */ 
/*      */     
/*  115 */     this.properties.put(name, Boolean.valueOf(value));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setByteProperty(String name, byte value) throws JMSException {
/*  122 */     if (Trace.isOn) {
/*  123 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "setByteProperty(String,byte)", "setter", name);
/*      */     }
/*      */ 
/*      */     
/*  127 */     this.properties.put(name, Byte.valueOf(value));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBytesProperty(String name, byte[] value) throws JMSException {
/*  134 */     if (Trace.isOn) {
/*  135 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "setBytesProperty(String,byte [ ])", new Object[] { name, value });
/*      */     }
/*      */ 
/*      */     
/*  139 */     HashMap<String, Object> inserts = new HashMap<>();
/*  140 */     inserts.put("XMSC_INSERT_PROPERTY", name);
/*  141 */     inserts.put("XMSC_INSERT_VALUE", value);
/*  142 */     inserts.put("XMSC_INSERT_TYPE", (new byte[0]).getClass());
/*  143 */     JMSException ex = (JMSException)NLSServices.createException("JMSWMQ0006", inserts);
/*  144 */     if (Trace.isOn) {
/*  145 */       Trace.throwing(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "setBytesProperty(String,byte [ ])", (Throwable)ex);
/*      */     }
/*      */     
/*  148 */     throw ex;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCharProperty(String name, char value) throws JMSException {
/*  155 */     if (Trace.isOn) {
/*  156 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "setCharProperty(String,char)", new Object[] { name, 
/*  157 */             Character.valueOf(value) });
/*      */     }
/*      */     
/*  160 */     HashMap<String, Object> inserts = new HashMap<>();
/*  161 */     inserts.put("XMSC_INSERT_PROPERTY", name);
/*  162 */     inserts.put("XMSC_INSERT_VALUE", Character.valueOf(value));
/*  163 */     inserts.put("XMSC_INSERT_TYPE", Character.class);
/*  164 */     JMSException ex = (JMSException)NLSServices.createException("JMSWMQ0006", inserts);
/*  165 */     if (Trace.isOn) {
/*  166 */       Trace.throwing(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "setCharProperty(String,char)", (Throwable)ex);
/*      */     }
/*      */     
/*  169 */     throw ex;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDoubleProperty(String name, double value) throws JMSException {
/*  176 */     if (Trace.isOn) {
/*  177 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "setDoubleProperty(String,double)", "setter", name);
/*      */     }
/*      */ 
/*      */     
/*  181 */     this.properties.put(name, Double.valueOf(value));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFloatProperty(String name, float value) throws JMSException {
/*  188 */     if (Trace.isOn) {
/*  189 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "setFloatProperty(String,float)", "setter", name);
/*      */     }
/*      */ 
/*      */     
/*  193 */     this.properties.put(name, Float.valueOf(value));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setIntProperty(String name, int value) throws JMSException {
/*  200 */     if (Trace.isOn) {
/*  201 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "setIntProperty(String,int)", "setter", name);
/*      */     }
/*      */ 
/*      */     
/*  205 */     this.properties.put(name, Integer.valueOf(value));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLongProperty(String name, long value) throws JMSException {
/*  212 */     if (Trace.isOn) {
/*  213 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "setLongProperty(String,long)", "setter", name);
/*      */     }
/*      */ 
/*      */     
/*  217 */     this.properties.put(name, Long.valueOf(value));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setObjectProperty(String name, Object value) throws JMSException {
/*  225 */     if (Trace.isOn) {
/*  226 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "setObjectProperty(String,Object)", "setter", name);
/*      */     }
/*      */ 
/*      */     
/*  230 */     this.properties.put(name, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setShortProperty(String name, short value) throws JMSException {
/*  237 */     if (Trace.isOn) {
/*  238 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "setShortProperty(String,short)", "setter", name);
/*      */     }
/*      */ 
/*      */     
/*  242 */     this.properties.put(name, Short.valueOf(value));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setStringProperty(String name, String value) throws JMSException {
/*  250 */     if (Trace.isOn) {
/*  251 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "setStringProperty(String,String)", "setter", name);
/*      */     }
/*      */ 
/*      */     
/*  255 */     this.properties.put(name, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getBooleanProperty(String name) throws JMSException {
/*  263 */     Object value = getObjectProperty(name);
/*  264 */     boolean result = parseBoolean(value, name, JMSException.class);
/*  265 */     if (Trace.isOn) {
/*  266 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "getBooleanProperty(String)", "getter", 
/*  267 */           Boolean.valueOf(result));
/*      */     }
/*  269 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte getByteProperty(String name) throws JMSException {
/*  277 */     Object value = getObjectProperty(name);
/*  278 */     byte result = parseByte(value, name, JMSException.class);
/*  279 */     if (Trace.isOn) {
/*  280 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "getByteProperty(String)", "getter", 
/*  281 */           Byte.valueOf(result));
/*      */     }
/*  283 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getBytesProperty(String name) throws JMSException {
/*  290 */     if (Trace.isOn) {
/*  291 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "getBytesProperty(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/*  295 */     HashMap<String, Object> inserts = new HashMap<>();
/*  296 */     inserts.put("XMSC_INSERT_PROPERTY", name);
/*  297 */     inserts.put("XMSC_INSERT_TYPE", (new byte[0]).getClass());
/*  298 */     JMSException ex = (JMSException)NLSServices.createException("JMSWMQ0006", inserts);
/*  299 */     if (Trace.isOn) {
/*  300 */       Trace.throwing(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "getBytesProperty(String)", (Throwable)ex);
/*      */     }
/*      */     
/*  303 */     throw ex;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public char getCharProperty(String name) throws JMSException {
/*  310 */     if (Trace.isOn) {
/*  311 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "getCharProperty(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/*  315 */     HashMap<String, Object> inserts = new HashMap<>();
/*  316 */     inserts.put("XMSC_INSERT_PROPERTY", name);
/*  317 */     inserts.put("XMSC_INSERT_TYPE", Character.class);
/*  318 */     JMSException ex = (JMSException)NLSServices.createException("JMSWMQ0006", inserts);
/*  319 */     if (Trace.isOn) {
/*  320 */       Trace.throwing(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "getCharProperty(String)", (Throwable)ex);
/*      */     }
/*      */     
/*  323 */     throw ex;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getDoubleProperty(String name) throws JMSException {
/*  331 */     Object value = getObjectProperty(name);
/*  332 */     double result = parseDouble(value, name, JMSException.class);
/*  333 */     if (Trace.isOn) {
/*  334 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "getDoubleProperty(String)", "getter", 
/*  335 */           Double.valueOf(result));
/*      */     }
/*  337 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getFloatProperty(String name) throws JMSException {
/*  345 */     Object value = getObjectProperty(name);
/*  346 */     float result = parseFloat(value, name, JMSException.class);
/*  347 */     if (Trace.isOn) {
/*  348 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "getFloatProperty(String)", "getter", 
/*  349 */           Float.valueOf(result));
/*      */     }
/*  351 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getIntProperty(String name) throws JMSException {
/*  359 */     Object value = getObjectProperty(name);
/*  360 */     int result = parseInt(value, name, JMSException.class);
/*  361 */     if (Trace.isOn) {
/*  362 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "getIntProperty(String)", "getter", 
/*  363 */           Integer.valueOf(result));
/*      */     }
/*  365 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getLongProperty(String name) throws JMSException {
/*  373 */     Object value = getObjectProperty(name);
/*  374 */     long result = parseLong(value, name, JMSException.class);
/*  375 */     if (Trace.isOn) {
/*  376 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "getLongProperty(String)", "getter", 
/*  377 */           Long.valueOf(result));
/*      */     }
/*  379 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getObjectProperty(String name) throws JMSException {
/*  387 */     Object result = this.properties.get(name);
/*  388 */     if (Trace.isOn) {
/*  389 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "getObjectProperty(String)", "getter", result);
/*      */     }
/*      */     
/*  392 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Enumeration<String> getPropertyNames() throws JMSException {
/*  400 */     Enumeration<String> traceRet1 = WMQUtils.enumerationFromIterable(this.properties.keySet());
/*  401 */     if (Trace.isOn) {
/*  402 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "getPropertyNames()", "getter", traceRet1);
/*      */     }
/*      */     
/*  405 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public short getShortProperty(String name) throws JMSException {
/*  413 */     Object value = getObjectProperty(name);
/*  414 */     short result = parseShort(value, name, JMSException.class);
/*  415 */     if (Trace.isOn) {
/*  416 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "getShortProperty(String)", "getter", 
/*  417 */           Short.valueOf(result));
/*      */     }
/*  419 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getStringProperty(String name) throws JMSException {
/*  427 */     Object obj = getObjectProperty(name);
/*      */     
/*  429 */     String value = null;
/*      */     
/*  431 */     if (obj instanceof String || obj == null) {
/*      */       
/*  433 */       value = (String)obj;
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  438 */       value = obj.toString();
/*      */     } 
/*  440 */     if (Trace.isOn) {
/*  441 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "getStringProperty(String)", "getter", value);
/*      */     }
/*      */     
/*  444 */     return value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean propertyExists(String name) throws JMSException {
/*  452 */     boolean result = this.properties.containsKey(name);
/*  453 */     if (Trace.isOn) {
/*  454 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "propertyExists(String)", "getter", 
/*  455 */           Boolean.valueOf(result));
/*      */     }
/*  457 */     return result;
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
/*  470 */     if (Trace.isOn) {
/*  471 */       Trace.entry("com.ibm.msg.client.wmq.messages.TransientMessageProps", "throwBadConvertException(Object,String,String,Class<?>)", new Object[] { obj, propName, dType, exceptionClass });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  476 */     String clsName = null;
/*      */     
/*  478 */     if (!(obj instanceof byte[])) {
/*      */       
/*  480 */       clsName = obj.getClass().getName();
/*      */       
/*  482 */       int index = 0;
/*      */ 
/*      */       
/*  485 */       if ((index = clsName.lastIndexOf('.')) != 0) {
/*  486 */         clsName = clsName.substring(index + 1);
/*      */       }
/*      */     } else {
/*      */       
/*  490 */       clsName = "Byte[]";
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  497 */     String msgId = "JMSWMQ1055";
/*  498 */     if (JMSException.class == exceptionClass) {
/*  499 */       msgId = "JMSWMQ1055";
/*      */     }
/*  501 */     else if (MessageFormatException.class == exceptionClass) {
/*  502 */       msgId = "JMSWMQ0006";
/*      */     } else {
/*      */       
/*  505 */       HashMap<String, Object> info = new HashMap<>();
/*  506 */       info.put("class", exceptionClass);
/*  507 */       Trace.ffst("WMQMessageProps", "throwBadConvertException", "XN00J001", info, JMSException.class);
/*      */     } 
/*      */     
/*  510 */     HashMap<String, Object> inserts = new HashMap<>();
/*  511 */     inserts.put("XMSC_INSERT_PROPERTY", propName);
/*  512 */     inserts.put("XMSC_INSERT_TYPE", clsName);
/*  513 */     inserts.put("XMSC_INSERT_OTHER_TYPE", dType);
/*  514 */     JMSException ex = (JMSException)NLSServices.createException(msgId, inserts);
/*  515 */     if (Trace.isOn) {
/*  516 */       Trace.throwing("com.ibm.msg.client.wmq.messages.TransientMessageProps", "throwBadConvertException(Object,String,String,Class<?>)", (Throwable)ex);
/*      */     }
/*      */     
/*  519 */     throw ex;
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
/*      */   protected static boolean parseBoolean(Object obj, String name, Class<?> exceptionClass) throws JMSException {
/*  535 */     boolean value = false;
/*      */     
/*  537 */     if (obj instanceof Boolean) {
/*      */       
/*  539 */       value = ((Boolean)obj).booleanValue();
/*      */     
/*      */     }
/*  542 */     else if (obj instanceof String) {
/*  543 */       value = Boolean.valueOf((String)obj).booleanValue();
/*      */     
/*      */     }
/*  546 */     else if (obj == null) {
/*      */       
/*  548 */       value = Boolean.valueOf((String)null).booleanValue();
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/*  554 */       throwBadConvertException(obj, name, "Boolean", exceptionClass);
/*      */     } 
/*      */ 
/*      */     
/*  558 */     return value;
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
/*  574 */     byte value = 0;
/*      */     
/*  576 */     if (obj instanceof Byte) {
/*      */ 
/*      */       
/*  579 */       value = ((Byte)obj).byteValue();
/*      */     
/*      */     }
/*  582 */     else if (obj instanceof String) {
/*      */       
/*  584 */       value = Byte.parseByte((String)obj);
/*      */     } else {
/*      */       
/*  587 */       if (obj == null)
/*      */       {
/*      */         
/*  590 */         throw new NullPointerException();
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  595 */       throwBadConvertException(obj, name, "Byte", exceptionClass);
/*      */     } 
/*      */     
/*  598 */     return value;
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
/*  614 */     double value = 0.0D;
/*      */     
/*  616 */     if (obj instanceof Double) {
/*      */ 
/*      */       
/*  619 */       value = ((Double)obj).doubleValue();
/*      */     
/*      */     }
/*  622 */     else if (obj instanceof String) {
/*      */       
/*  624 */       value = Double.parseDouble((String)obj);
/*      */     
/*      */     }
/*  627 */     else if (obj instanceof Float) {
/*      */       
/*  629 */       value = ((Float)obj).doubleValue();
/*      */     
/*      */     }
/*  632 */     else if (obj == null) {
/*      */ 
/*      */       
/*  635 */       value = Double.valueOf((String)null).doubleValue();
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  640 */       throwBadConvertException(obj, name, "Double", exceptionClass);
/*      */     } 
/*      */     
/*  643 */     return value;
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
/*  659 */     float value = 0.0F;
/*      */     
/*  661 */     if (obj instanceof Float) {
/*      */       
/*  663 */       value = ((Float)obj).floatValue();
/*      */     
/*      */     }
/*  666 */     else if (obj instanceof String) {
/*      */       
/*  668 */       value = Float.parseFloat((String)obj);
/*      */     
/*      */     }
/*  671 */     else if (obj == null) {
/*      */ 
/*      */       
/*  674 */       value = Float.valueOf((String)null).floatValue();
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  679 */       throwBadConvertException(obj, name, "Float", exceptionClass);
/*      */     } 
/*      */ 
/*      */     
/*  683 */     return value;
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
/*  699 */     int value = 0;
/*      */     
/*  701 */     if (obj instanceof Integer) {
/*      */       
/*  703 */       value = ((Integer)obj).intValue();
/*      */     
/*      */     }
/*  706 */     else if (obj instanceof String) {
/*      */       
/*  708 */       value = Integer.parseInt((String)obj);
/*      */     
/*      */     }
/*  711 */     else if (obj instanceof Byte) {
/*      */       
/*  713 */       value = ((Byte)obj).intValue();
/*      */     
/*      */     }
/*  716 */     else if (obj instanceof Short) {
/*      */       
/*  718 */       value = ((Short)obj).intValue();
/*      */     } else {
/*      */       
/*  721 */       if (obj == null)
/*      */       {
/*      */         
/*  724 */         throw new NullPointerException();
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  729 */       throwBadConvertException(obj, name, "Integer", exceptionClass);
/*      */     } 
/*      */     
/*  732 */     return value;
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
/*  748 */     long value = 0L;
/*      */     
/*  750 */     if (obj instanceof Long) {
/*      */ 
/*      */       
/*  753 */       value = ((Long)obj).longValue();
/*      */     
/*      */     }
/*  756 */     else if (obj instanceof String) {
/*      */       
/*  758 */       value = Long.parseLong((String)obj);
/*      */     
/*      */     }
/*  761 */     else if (obj instanceof Byte) {
/*      */       
/*  763 */       value = ((Byte)obj).longValue();
/*      */     
/*      */     }
/*  766 */     else if (obj instanceof Short) {
/*      */       
/*  768 */       value = ((Short)obj).longValue();
/*      */     
/*      */     }
/*  771 */     else if (obj instanceof Integer) {
/*      */       
/*  773 */       value = ((Integer)obj).longValue();
/*      */     } else {
/*      */       
/*  776 */       if (obj == null)
/*      */       {
/*      */         
/*  779 */         throw new NullPointerException();
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  784 */       throwBadConvertException(obj, name, "Long", exceptionClass);
/*      */     } 
/*      */ 
/*      */     
/*  788 */     return value;
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
/*  804 */     short value = 0;
/*      */     
/*  806 */     if (obj instanceof Short) {
/*      */ 
/*      */       
/*  809 */       value = ((Short)obj).shortValue();
/*      */     
/*      */     }
/*  812 */     else if (obj instanceof String) {
/*      */       
/*  814 */       value = Short.parseShort((String)obj);
/*      */     
/*      */     }
/*  817 */     else if (obj instanceof Byte) {
/*      */       
/*  819 */       value = ((Byte)obj).shortValue();
/*      */     
/*      */     }
/*  822 */     else if (obj == null) {
/*      */ 
/*      */       
/*  825 */       value = Short.valueOf((String)null).shortValue();
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  830 */       throwBadConvertException(obj, name, "Short", exceptionClass);
/*      */     } 
/*      */     
/*  833 */     return value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clearProperties() throws JMSException {
/*  843 */     if (Trace.isOn) {
/*  844 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "clearProperties()");
/*      */     }
/*      */ 
/*      */     
/*  848 */     this.properties.clear();
/*  849 */     if (Trace.isOn) {
/*  850 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "clearProperties()");
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
/*      */   public void removeProperty(String name) throws JMSException {
/*  867 */     if (Trace.isOn) {
/*  868 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "removeProperty(String)", new Object[] { name });
/*      */     }
/*      */     
/*  871 */     this.properties.remove(name);
/*  872 */     if (Trace.isOn) {
/*  873 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "removeProperty(String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clear() {
/*  883 */     if (Trace.isOn) {
/*  884 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "clear()");
/*      */     }
/*  886 */     this.properties.clear();
/*  887 */     if (Trace.isOn) {
/*  888 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "clear()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean containsKey(Object key) {
/*  897 */     if (Trace.isOn) {
/*  898 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "containsKey(Object)", new Object[] { key });
/*      */     }
/*      */     
/*  901 */     boolean traceRet1 = this.properties.containsKey(key);
/*  902 */     if (Trace.isOn) {
/*  903 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "containsKey(Object)", 
/*  904 */           Boolean.valueOf(traceRet1));
/*      */     }
/*  906 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean containsValue(Object value) {
/*  913 */     if (Trace.isOn) {
/*  914 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "containsValue(Object)", new Object[] { value });
/*      */     }
/*      */     
/*  917 */     boolean traceRet1 = this.properties.containsValue(value);
/*  918 */     if (Trace.isOn) {
/*  919 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "containsValue(Object)", 
/*  920 */           Boolean.valueOf(traceRet1));
/*      */     }
/*  922 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Set<Map.Entry<String, Object>> entrySet() {
/*  929 */     if (Trace.isOn) {
/*  930 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "entrySet()");
/*      */     }
/*  932 */     Set<Map.Entry<String, Object>> traceRet1 = this.properties.entrySet();
/*  933 */     if (Trace.isOn) {
/*  934 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "entrySet()", traceRet1);
/*      */     }
/*      */     
/*  937 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object get(Object key) {
/*  944 */     if (Trace.isOn) {
/*  945 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "get(Object)", new Object[] { key });
/*      */     }
/*      */     
/*  948 */     Object traceRet1 = this.properties.get(key);
/*  949 */     if (Trace.isOn) {
/*  950 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "get(Object)", traceRet1);
/*      */     }
/*      */     
/*  953 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isEmpty() {
/*  960 */     boolean traceRet1 = this.properties.isEmpty();
/*  961 */     if (Trace.isOn) {
/*  962 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "isEmpty()", "getter", 
/*  963 */           Boolean.valueOf(traceRet1));
/*      */     }
/*  965 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Set<String> keySet() {
/*  972 */     if (Trace.isOn) {
/*  973 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "keySet()");
/*      */     }
/*  975 */     Set<String> traceRet1 = this.properties.keySet();
/*  976 */     if (Trace.isOn) {
/*  977 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "keySet()", traceRet1);
/*      */     }
/*      */     
/*  980 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object put(String arg0, Object arg1) {
/*  987 */     if (Trace.isOn) {
/*  988 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "put(String,Object)", new Object[] { arg0, arg1 });
/*      */     }
/*      */     
/*  991 */     Object traceRet1 = this.properties.put(arg0, arg1);
/*  992 */     if (Trace.isOn) {
/*  993 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "put(String,Object)", traceRet1);
/*      */     }
/*      */     
/*  996 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void putAll(Map<? extends String, ? extends Object> arg0) {
/* 1003 */     if (Trace.isOn) {
/* 1004 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "putAll(Map<? extends String , ? extends Object>)", new Object[] { arg0 });
/*      */     }
/*      */     
/* 1007 */     this.properties.putAll(arg0);
/* 1008 */     if (Trace.isOn) {
/* 1009 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "putAll(Map<? extends String , ? extends Object>)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object remove(Object key) {
/* 1019 */     if (Trace.isOn) {
/* 1020 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "remove(Object)", new Object[] { key });
/*      */     }
/*      */     
/* 1023 */     Object traceRet1 = this.properties.remove(key);
/* 1024 */     if (Trace.isOn) {
/* 1025 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "remove(Object)", traceRet1);
/*      */     }
/*      */     
/* 1028 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int size() {
/* 1035 */     if (Trace.isOn) {
/* 1036 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "size()");
/*      */     }
/* 1038 */     int traceRet1 = this.properties.size();
/* 1039 */     if (Trace.isOn) {
/* 1040 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "size()", 
/* 1041 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1043 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<Object> values() {
/* 1050 */     if (Trace.isOn) {
/* 1051 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "values()");
/*      */     }
/* 1053 */     Collection<Object> traceRet1 = this.properties.values();
/* 1054 */     if (Trace.isOn) {
/* 1055 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientMessageProps", "values()", traceRet1);
/*      */     }
/*      */     
/* 1058 */     return traceRet1;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\messages\TransientMessageProps.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */