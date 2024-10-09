/*      */ package com.ibm.msg.client.jms.internal;
/*      */ 
/*      */ import com.ibm.msg.client.commonservices.trace.TableBuilder;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.jms.JmsReadablePropertyContext;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.ObjectStreamException;
/*      */ import java.io.PrintWriter;
/*      */ import java.util.Collections;
/*      */ import java.util.ConcurrentModificationException;
/*      */ import java.util.Enumeration;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.UUID;
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
/*      */ public abstract class JmsReadablePropertyContextImpl
/*      */   implements JmsReadablePropertyContext
/*      */ {
/*      */   private static final long serialVersionUID = 1485713687468941354L;
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsReadablePropertyContextImpl.java";
/*      */   private Map<String, Object> properties;
/*      */   private UUID orderingId;
/*      */   
/*      */   static {
/*   53 */     if (Trace.isOn) {
/*   54 */       Trace.data("com.ibm.msg.client.jms.internal.JmsReadablePropertyContextImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsReadablePropertyContextImpl.java");
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
/*      */   public JmsReadablePropertyContextImpl() {
/*   77 */     this.properties = Collections.synchronizedMap(new HashMap<>());
/*   78 */     setOrderingId();
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
/*      */   public JmsReadablePropertyContextImpl(Map<String, Object> propsTable, boolean doCopy) {
/*   92 */     if (Trace.isOn) {
/*   93 */       if (propsTable.containsKey("XMSC_PASSWORD")) {
/*   94 */         HashMap<String, Object> propsNotPasswd = new HashMap<>(propsTable);
/*   95 */         propsNotPasswd.put("XMSC_PASSWORD", "********");
/*   96 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsReadablePropertyContextImpl", "<init>(Map,boolean)", new Object[] { propsNotPasswd, 
/*   97 */               Boolean.valueOf(doCopy) });
/*      */       } else {
/*      */         
/*  100 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsReadablePropertyContextImpl", "<init>(Map,boolean)", new Object[] { propsTable, 
/*  101 */               Boolean.valueOf(doCopy) });
/*      */       } 
/*      */     }
/*  104 */     if (doCopy) {
/*  105 */       HashMap<String, Object> newPropsTable = new HashMap<>(propsTable);
/*  106 */       this.properties = Collections.synchronizedMap(newPropsTable);
/*      */     } else {
/*      */       
/*  109 */       this.properties = Collections.synchronizedMap(propsTable);
/*      */     } 
/*  111 */     if (Trace.isOn) {
/*  112 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsReadablePropertyContextImpl", "<init>(Map,boolean)");
/*      */     }
/*      */     
/*  115 */     setOrderingId();
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
/*      */   public JmsReadablePropertyContextImpl(JmsReadablePropertyContextImpl other, boolean doCopy) {
/*  127 */     this(other.properties, doCopy);
/*  128 */     if (Trace.isOn) {
/*  129 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsReadablePropertyContextImpl", "<init>(JmsReadablePropertyContextImpl,boolean)", new Object[] { other, 
/*      */             
/*  131 */             Boolean.valueOf(doCopy) });
/*      */     }
/*  133 */     if (Trace.isOn) {
/*  134 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsReadablePropertyContextImpl", "<init>(JmsReadablePropertyContextImpl,boolean)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void setOrderingId() {
/*  141 */     this.orderingId = new UUID(Thread.currentThread().getId(), System.currentTimeMillis());
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
/*      */   public boolean getBooleanProperty(String name) throws JMSException {
/*  153 */     Object value = getObjectProperty(name);
/*  154 */     boolean result = parseBoolean(value, name, (Class)JMSException.class);
/*  155 */     return result;
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
/*      */   public byte getByteProperty(String name) throws JMSException {
/*  167 */     Object value = getObjectProperty(name);
/*  168 */     byte result = parseByte(value, name, (Class)JMSException.class);
/*  169 */     return result;
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
/*      */   public byte[] getBytesProperty(String name) throws JMSException {
/*  181 */     Object objVal = getObjectProperty(name);
/*      */     
/*  183 */     byte[] copyOfValue = null;
/*  184 */     if (objVal instanceof byte[]) {
/*      */       
/*  186 */       byte[] value = (byte[])objVal;
/*  187 */       copyOfValue = new byte[value.length];
/*  188 */       System.arraycopy(value, 0, copyOfValue, 0, value.length);
/*      */     
/*      */     }
/*  191 */     else if (objVal == null) {
/*  192 */       copyOfValue = null;
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  197 */       throwBadConvertException(objVal, name, "Byte[]", (Class)JMSException.class);
/*      */     } 
/*      */     
/*  200 */     return copyOfValue;
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
/*      */   public char getCharProperty(String name) throws JMSException {
/*  213 */     Object objVal = getObjectProperty(name);
/*      */     
/*  215 */     char value = Character.MIN_VALUE;
/*  216 */     if (objVal instanceof Character) {
/*  217 */       value = ((Character)objVal).charValue();
/*      */     } else {
/*  219 */       if (objVal == null) {
/*  220 */         HashMap<String, Object> inserts = new HashMap<>();
/*  221 */         inserts.put("XMSC_INSERT_FIELD", name);
/*  222 */         JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0042", inserts);
/*      */         
/*  224 */         if (Trace.isOn) {
/*  225 */           Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsReadablePropertyContextImpl", "getCharProperty(String)", (Throwable)je);
/*      */         }
/*      */         
/*  228 */         throw je;
/*      */       } 
/*      */       
/*  231 */       throwBadConvertException(objVal, name, "Character", (Class)JMSException.class);
/*      */     } 
/*      */     
/*  234 */     return value;
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
/*      */   public double getDoubleProperty(String name) throws JMSException {
/*  247 */     Object value = getObjectProperty(name);
/*  248 */     double result = parseDouble(value, name, (Class)JMSException.class);
/*  249 */     return result;
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
/*      */   public float getFloatProperty(String name) throws JMSException {
/*  261 */     Object value = getObjectProperty(name);
/*  262 */     float result = parseFloat(value, name, (Class)JMSException.class);
/*  263 */     return result;
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
/*      */   public int getIntProperty(String name) throws JMSException {
/*  275 */     Object value = getObjectProperty(name);
/*  276 */     int result = parseInt(value, name, (Class)JMSException.class);
/*  277 */     return result;
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
/*      */   public long getLongProperty(String name) throws JMSException {
/*  289 */     Object value = getObjectProperty(name);
/*  290 */     long result = parseLong(value, name, (Class)JMSException.class);
/*  291 */     return result;
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
/*      */   public Object getObjectProperty(String name) throws JMSException {
/*  303 */     Object result = this.properties.get(name);
/*  304 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Enumeration<String> getPropertyNames() throws JMSException {
/*  315 */     final Iterator<String> iterator = this.properties.keySet().iterator();
/*      */     
/*  317 */     Enumeration<String> e = new Enumeration<String>()
/*      */       {
/*      */         public boolean hasMoreElements()
/*      */         {
/*  321 */           boolean b = iterator.hasNext();
/*  322 */           return b;
/*      */         }
/*      */ 
/*      */         
/*      */         public String nextElement() {
/*  327 */           String o = iterator.next();
/*  328 */           return o;
/*      */         }
/*      */       };
/*  331 */     return e;
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
/*      */   public short getShortProperty(String name) throws JMSException {
/*  343 */     Object value = getObjectProperty(name);
/*  344 */     short result = parseShort(value, name, (Class)JMSException.class);
/*  345 */     return result;
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
/*      */   public String getStringProperty(String name) throws JMSException {
/*  358 */     Object obj = getObjectProperty(name);
/*      */     
/*  360 */     String value = null;
/*      */     
/*  362 */     if (obj instanceof String || obj == null) {
/*      */       
/*  364 */       value = (String)obj;
/*      */     
/*      */     }
/*  367 */     else if (obj instanceof byte[]) {
/*  368 */       value = arrayToHexString((byte[])obj);
/*      */     }
/*      */     else {
/*      */       
/*  372 */       value = obj.toString();
/*      */     } 
/*      */     
/*  375 */     return value;
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
/*      */   public boolean propertyExists(String name) throws JMSException {
/*  388 */     boolean result = this.properties.containsKey(name);
/*  389 */     if (Trace.isOn) {
/*  390 */       Trace.data(this, "propertyExists(String)", "via JmsReadablePropertyContextImpl", 
/*  391 */           Boolean.valueOf(result));
/*      */     }
/*  393 */     return result;
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
/*      */   static void throwBadConvertException(Object obj, String propName, String dType, Class<? extends Exception> exceptionClass) throws JMSException {
/*  407 */     if (Trace.isOn) {
/*  408 */       Trace.entry("com.ibm.msg.client.jms.internal.JmsReadablePropertyContextImpl", "throwBadConvertException(Object,String,String,Class<? extends Exception>)", new Object[] { obj, propName, dType, exceptionClass });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  414 */     String clsName = null;
/*      */     
/*  416 */     if (!(obj instanceof byte[])) {
/*      */       
/*  418 */       clsName = obj.getClass().getName();
/*      */       
/*  420 */       int index = 0;
/*      */ 
/*      */ 
/*      */       
/*  424 */       if ((index = clsName.lastIndexOf('.')) != 0) {
/*  425 */         clsName = clsName.substring(index + 1);
/*      */       }
/*      */     } else {
/*      */       
/*  429 */       clsName = "Byte[]";
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  436 */     String msgId = "JMSCC0041";
/*  437 */     if (JMSException.class == exceptionClass) {
/*  438 */       msgId = "JMSCC0041";
/*      */     }
/*  440 */     else if (MessageFormatException.class == exceptionClass) {
/*  441 */       msgId = "JMSCC0105";
/*      */     } else {
/*      */       
/*  444 */       HashMap<String, Class<? extends Exception>> info = new HashMap<>();
/*  445 */       info.put("class", exceptionClass);
/*  446 */       Trace.ffst("JmsReadablePropertyContextImpl", "throwBadConvertException", "XJ002001", info, JMSException.class);
/*      */     } 
/*      */ 
/*      */     
/*  450 */     HashMap<String, Object> inserts = new HashMap<>();
/*  451 */     inserts.put("XMSC_INSERT_PROPERTY", propName);
/*  452 */     inserts.put("XMSC_INSERT_TYPE", clsName);
/*  453 */     inserts.put("XMSC_INSERT_OTHER_TYPE", dType);
/*  454 */     JMSException ex = (JMSException)JmsErrorUtils.createException(msgId, inserts);
/*      */     
/*  456 */     if (Trace.isOn) {
/*  457 */       Trace.throwing("com.ibm.msg.client.jms.internal.JmsReadablePropertyContextImpl", "throwBadConvertException(Object,String,String,Class<? extends Exception>)", (Throwable)ex);
/*      */     }
/*      */     
/*  460 */     throw ex;
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
/*      */   protected static boolean parseBoolean(Object obj, String name, Class<? extends Exception> exceptionClass) throws JMSException {
/*  479 */     boolean value = false;
/*      */     
/*  481 */     if (obj instanceof Boolean) {
/*      */       
/*  483 */       value = ((Boolean)obj).booleanValue();
/*      */     
/*      */     }
/*  486 */     else if (obj instanceof String) {
/*  487 */       value = Boolean.valueOf((String)obj).booleanValue();
/*      */     
/*      */     }
/*  490 */     else if (obj == null) {
/*      */       
/*  492 */       value = Boolean.valueOf((String)null).booleanValue();
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/*  498 */       throwBadConvertException(obj, name, "Boolean", exceptionClass);
/*      */     } 
/*      */ 
/*      */     
/*  502 */     return value;
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
/*      */   protected static byte parseByte(Object obj, String name, Class<? extends Exception> exceptionClass) throws JMSException {
/*  519 */     byte value = 0;
/*      */     
/*  521 */     if (obj instanceof Byte) {
/*      */ 
/*      */       
/*  524 */       value = ((Byte)obj).byteValue();
/*      */     
/*      */     }
/*  527 */     else if (obj instanceof String) {
/*      */       
/*  529 */       value = Byte.parseByte((String)obj);
/*      */     } else {
/*      */       
/*  532 */       if (obj == null)
/*      */       {
/*      */ 
/*      */         
/*  536 */         throw new NumberFormatException("null value");
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  541 */       throwBadConvertException(obj, name, "Byte", exceptionClass);
/*      */     } 
/*      */     
/*  544 */     return value;
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
/*      */   protected static double parseDouble(Object obj, String name, Class<? extends Exception> exceptionClass) throws JMSException {
/*  561 */     double value = 0.0D;
/*      */     
/*  563 */     if (obj instanceof Double) {
/*      */ 
/*      */       
/*  566 */       value = ((Double)obj).doubleValue();
/*      */     
/*      */     }
/*  569 */     else if (obj instanceof String) {
/*      */       
/*  571 */       value = Double.parseDouble((String)obj);
/*      */     
/*      */     }
/*  574 */     else if (obj instanceof Float) {
/*      */       
/*  576 */       value = ((Float)obj).doubleValue();
/*      */     } else {
/*      */       
/*  579 */       if (obj == null)
/*      */       {
/*      */ 
/*      */         
/*  583 */         throw new NullPointerException("null value");
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  588 */       throwBadConvertException(obj, name, "Double", exceptionClass);
/*      */     } 
/*      */     
/*  591 */     return value;
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
/*      */   protected static float parseFloat(Object obj, String name, Class<? extends Exception> exceptionClass) throws JMSException {
/*  608 */     float value = 0.0F;
/*      */     
/*  610 */     if (obj instanceof Float) {
/*      */       
/*  612 */       value = ((Float)obj).floatValue();
/*      */     
/*      */     }
/*  615 */     else if (obj instanceof String) {
/*      */       
/*  617 */       value = Float.parseFloat((String)obj);
/*      */     } else {
/*      */       
/*  620 */       if (obj == null)
/*      */       {
/*      */ 
/*      */         
/*  624 */         throw new NullPointerException("null value");
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  629 */       throwBadConvertException(obj, name, "Float", exceptionClass);
/*      */     } 
/*      */ 
/*      */     
/*  633 */     return value;
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
/*      */   protected static int parseInt(Object obj, String name, Class<? extends Exception> exceptionClass) throws JMSException {
/*  649 */     int value = 0;
/*      */     
/*  651 */     if (obj instanceof Integer) {
/*      */       
/*  653 */       value = ((Integer)obj).intValue();
/*      */     
/*      */     }
/*  656 */     else if (obj instanceof String) {
/*      */       
/*  658 */       value = Integer.parseInt((String)obj);
/*      */     
/*      */     }
/*  661 */     else if (obj instanceof Byte) {
/*      */       
/*  663 */       value = ((Byte)obj).intValue();
/*      */     
/*      */     }
/*  666 */     else if (obj instanceof Short) {
/*      */       
/*  668 */       value = ((Short)obj).intValue();
/*      */     } else {
/*      */       
/*  671 */       if (obj == null)
/*      */       {
/*      */ 
/*      */         
/*  675 */         throw new NumberFormatException("null value");
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  680 */       throwBadConvertException(obj, name, "Integer", exceptionClass);
/*      */     } 
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
/*      */   
/*      */   protected static long parseLong(Object obj, String name, Class<? extends Exception> exceptionClass) throws JMSException {
/*  700 */     long value = 0L;
/*      */     
/*  702 */     if (obj instanceof Long) {
/*      */ 
/*      */       
/*  705 */       value = ((Long)obj).longValue();
/*      */     
/*      */     }
/*  708 */     else if (obj instanceof String) {
/*      */       
/*  710 */       value = Long.parseLong((String)obj);
/*      */     
/*      */     }
/*  713 */     else if (obj instanceof Byte) {
/*      */       
/*  715 */       value = ((Byte)obj).longValue();
/*      */     
/*      */     }
/*  718 */     else if (obj instanceof Short) {
/*      */       
/*  720 */       value = ((Short)obj).longValue();
/*      */     
/*      */     }
/*  723 */     else if (obj instanceof Integer) {
/*      */       
/*  725 */       value = ((Integer)obj).longValue();
/*      */     } else {
/*      */       
/*  728 */       if (obj == null)
/*      */       {
/*      */ 
/*      */         
/*  732 */         throw new NumberFormatException("null value");
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  737 */       throwBadConvertException(obj, name, "Long", exceptionClass);
/*      */     } 
/*      */ 
/*      */     
/*  741 */     return value;
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
/*      */   protected static short parseShort(Object obj, String name, Class<? extends Exception> exceptionClass) throws JMSException {
/*  758 */     short value = 0;
/*      */     
/*  760 */     if (obj instanceof Short) {
/*      */ 
/*      */       
/*  763 */       value = ((Short)obj).shortValue();
/*      */     
/*      */     }
/*  766 */     else if (obj instanceof String) {
/*      */       
/*  768 */       value = Short.parseShort((String)obj);
/*      */     
/*      */     }
/*  771 */     else if (obj instanceof Byte) {
/*      */       
/*  773 */       value = ((Byte)obj).shortValue();
/*      */     } else {
/*      */       
/*  776 */       if (obj == null)
/*      */       {
/*      */ 
/*      */         
/*  780 */         throw new NumberFormatException("null value");
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  785 */       throwBadConvertException(obj, name, "Short", exceptionClass);
/*      */     } 
/*      */     
/*  788 */     return value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Map<String, Object> getProperties() {
/*  797 */     return this.properties;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int hashCode() {
/*  807 */     if (Trace.isOn) {
/*  808 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsReadablePropertyContextImpl", "hashCode()");
/*      */     }
/*      */     
/*  811 */     int traceRet1 = super.hashCode();
/*  812 */     if (Trace.isOn) {
/*  813 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsReadablePropertyContextImpl", "hashCode()", 
/*  814 */           Integer.valueOf(traceRet1));
/*      */     }
/*  816 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean equals(Object obj) {
/*  826 */     if (Trace.isOn) {
/*  827 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsReadablePropertyContextImpl", "equals(Object)", new Object[] { obj });
/*      */     }
/*      */     
/*  830 */     boolean traceRet1 = super.equals(obj);
/*  831 */     if (Trace.isOn) {
/*  832 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsReadablePropertyContextImpl", "equals(Object)", 
/*  833 */           Boolean.valueOf(traceRet1));
/*      */     }
/*  835 */     return traceRet1;
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
/*      */   protected boolean compareContexts(JmsReadablePropertyContextImpl ap, JmsReadablePropertyContextImpl bp) throws JMSException {
/*  853 */     if (Trace.isOn) {
/*  854 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsReadablePropertyContextImpl", "compareContexts(JmsReadablePropertyContextImpl,JmsReadablePropertyContextImpl)", new Object[] { ap, bp });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*      */       JmsReadablePropertyContextImpl a, b;
/*      */       
/*  861 */       if (bp == ap) {
/*  862 */         if (Trace.isOn) {
/*  863 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsReadablePropertyContextImpl", "compareContexts(JmsReadablePropertyContextImpl,JmsReadablePropertyContextImpl)", 
/*      */               
/*  865 */               Boolean.valueOf(true), 1);
/*      */         }
/*  867 */         return true;
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
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  882 */       switch (ap.orderingId.compareTo(bp.orderingId)) {
/*      */         case 1:
/*  884 */           a = bp;
/*  885 */           b = ap;
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         default:
/*  893 */           a = ap;
/*  894 */           b = bp;
/*      */           break;
/*      */       } 
/*  897 */       Map<String, Object> mapA = a.getProperties();
/*  898 */       Map<String, Object> mapB = b.getProperties();
/*      */ 
/*      */       
/*  901 */       if (mapA.size() != mapB.size()) {
/*  902 */         if (Trace.isOn) {
/*  903 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsReadablePropertyContextImpl", "compareContexts(JmsReadablePropertyContextImpl,JmsReadablePropertyContextImpl)", 
/*      */               
/*  905 */               Boolean.valueOf(false), 2);
/*      */         }
/*  907 */         return false;
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/*  912 */         synchronized (mapA) {
/*  913 */           Iterator<Map.Entry<String, Object>> i = mapA.entrySet().iterator();
/*  914 */           while (i.hasNext()) {
/*  915 */             Map.Entry<String, Object> e = i.next();
/*  916 */             Object key = e.getKey();
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  921 */             String value = a.getStringProperty((String)key);
/*      */             
/*  923 */             if (value == null) {
/*  924 */               if (b.getStringProperty((String)key) != null || !mapB.containsKey(key)) {
/*  925 */                 if (Trace.isOn) {
/*  926 */                   Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsReadablePropertyContextImpl", "compareContexts(JmsReadablePropertyContextImpl,JmsReadablePropertyContextImpl)", 
/*      */                       
/*  928 */                       Boolean.valueOf(false), 3);
/*      */                 }
/*  930 */                 return false;
/*      */               } 
/*      */               continue;
/*      */             } 
/*  934 */             if (!value.equals(b.getStringProperty((String)key))) {
/*  935 */               if (Trace.isOn) {
/*  936 */                 Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsReadablePropertyContextImpl", "compareContexts(JmsReadablePropertyContextImpl,JmsReadablePropertyContextImpl)", 
/*      */                     
/*  938 */                     Boolean.valueOf(false), 4);
/*      */               }
/*  940 */               return false;
/*      */             }
/*      */           
/*      */           }
/*      */         
/*      */         } 
/*  946 */       } catch (NullPointerException unused) {
/*  947 */         if (Trace.isOn) {
/*  948 */           Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsReadablePropertyContextImpl", "compareContexts(JmsReadablePropertyContextImpl,JmsReadablePropertyContextImpl)", unused);
/*      */         }
/*      */ 
/*      */         
/*  952 */         if (Trace.isOn) {
/*  953 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsReadablePropertyContextImpl", "compareContexts(JmsReadablePropertyContextImpl,JmsReadablePropertyContextImpl)", 
/*      */               
/*  955 */               Boolean.valueOf(false), 5);
/*      */         }
/*  957 */         return false;
/*      */       } 
/*      */       
/*  960 */       if (Trace.isOn) {
/*  961 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsReadablePropertyContextImpl", "compareContexts(JmsReadablePropertyContextImpl,JmsReadablePropertyContextImpl)", 
/*      */             
/*  963 */             Boolean.valueOf(true), 6);
/*      */       }
/*  965 */       return true;
/*      */     } finally {
/*      */       
/*  968 */       if (Trace.isOn) {
/*  969 */         Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsReadablePropertyContextImpl", "compareContexts(JmsReadablePropertyContextImpl,JmsReadablePropertyContextImpl)");
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
/*      */   private void readObjectNoData() throws ObjectStreamException {
/*  984 */     if (Trace.isOn) {
/*  985 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsReadablePropertyContextImpl", "readObjectNoData()");
/*      */     }
/*      */ 
/*      */     
/*  989 */     this.properties = Collections.synchronizedMap(new HashMap<>());
/*  990 */     if (Trace.isOn) {
/*  991 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsReadablePropertyContextImpl", "readObjectNoData()");
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
/*      */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 1005 */     if (Trace.isOn) {
/* 1006 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsReadablePropertyContextImpl", "writeObject(ObjectOutputStream)", new Object[] { out });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1011 */     flushPendingPropertyUpdates();
/*      */ 
/*      */     
/* 1014 */     out.defaultWriteObject();
/* 1015 */     if (Trace.isOn) {
/* 1016 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsReadablePropertyContextImpl", "writeObject(ObjectOutputStream)");
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
/*      */   protected void flushPendingPropertyUpdates() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/* 1044 */     TableBuilder builder = new TableBuilder();
/* 1045 */     builder.append(stringifyMe(), sanitize());
/*      */     
/* 1047 */     String result = builder.toString();
/* 1048 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Map<String, Object> sanitize() {
/* 1056 */     if (Trace.isOn) {
/* 1057 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsReadablePropertyContextImpl", "sanitize()");
/*      */     }
/*      */ 
/*      */     
/* 1061 */     Map<String, Object> propsNotPasswd = new HashMap<>();
/*      */     try {
/* 1063 */       propsNotPasswd.putAll(this.properties);
/*      */       
/* 1065 */       if (this.properties.containsKey("XMSC_PASSWORD")) {
/* 1066 */         propsNotPasswd.put("XMSC_PASSWORD", "********");
/*      */       }
/*      */     }
/* 1069 */     catch (ConcurrentModificationException cme) {
/* 1070 */       propsNotPasswd.clear();
/* 1071 */       propsNotPasswd.put("PROPERTIES_BEING_MODIFIED", Boolean.TRUE);
/*      */     } 
/*      */     
/* 1074 */     if (Trace.isOn) {
/* 1075 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsReadablePropertyContextImpl", "sanitize()", propsNotPasswd);
/*      */     }
/*      */     
/* 1078 */     return propsNotPasswd;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void dump(PrintWriter pw, int level) {
/* 1087 */     if (Trace.isOn) {
/* 1088 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsReadablePropertyContextImpl", "dump(PrintWriter,int)", new Object[] { pw, 
/* 1089 */             Integer.valueOf(level) });
/*      */     }
/* 1091 */     TableBuilder builder = new TableBuilder(level * 2, false, false);
/* 1092 */     builder.populate(sanitize());
/* 1093 */     pw.println(builder.toString());
/* 1094 */     if (Trace.isOn) {
/* 1095 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsReadablePropertyContextImpl", "dump(PrintWriter,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private String arrayToHexString(byte[] array) {
/*      */     String retVal;
/* 1103 */     if (Trace.isOn) {
/* 1104 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsReadablePropertyContextImpl", "arrayToHexString(byte [ ])", new Object[] { array });
/*      */     }
/*      */ 
/*      */     
/* 1108 */     if (array != null) {
/* 1109 */       StringBuffer hexString = new StringBuffer(array.length);
/*      */ 
/*      */       
/* 1112 */       for (int i = 0; i < array.length; i++) {
/* 1113 */         int hexVal = (array[i] & 0xF0) >> 4;
/* 1114 */         char hexChar = (char)((hexVal > 9) ? (65 + hexVal - 10) : (48 + hexVal));
/* 1115 */         hexString.append(hexChar);
/* 1116 */         hexVal = array[i] & 0xF;
/* 1117 */         hexChar = (char)((hexVal > 9) ? (65 + hexVal - 10) : (48 + hexVal));
/* 1118 */         hexString.append(hexChar);
/*      */       } 
/* 1120 */       retVal = hexString.toString();
/*      */     } else {
/*      */       
/* 1123 */       retVal = "<null>";
/*      */     } 
/* 1125 */     if (Trace.isOn) {
/* 1126 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsReadablePropertyContextImpl", "arrayToHexString(byte [ ])", retVal);
/*      */     }
/*      */     
/* 1129 */     return retVal;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsReadablePropertyContextImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */