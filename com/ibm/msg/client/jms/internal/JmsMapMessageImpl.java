/*      */ package com.ibm.msg.client.jms.internal;
/*      */ 
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.provider.ProviderMapMessage;
/*      */ import com.ibm.msg.client.provider.ProviderMessage;
/*      */ import java.util.Enumeration;
/*      */ import java.util.HashMap;
/*      */ import java.util.Map;
/*      */ import javax.jms.JMSException;
/*      */ import javax.jms.MapMessage;
/*      */ import javax.jms.Message;
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
/*      */ public class JmsMapMessageImpl
/*      */   extends JmsMessageImpl
/*      */   implements MapMessage
/*      */ {
/*      */   private static final long serialVersionUID = -8315480708014156786L;
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsMapMessageImpl.java";
/*      */   private ProviderMapMessage providerMapMessage;
/*      */   
/*      */   static {
/*   49 */     if (Trace.isOn) {
/*   50 */       Trace.data("com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsMapMessageImpl.java");
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
/*      */   
/*   73 */   private transient String cachedMapToString = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JmsMapMessageImpl(JmsSessionImpl session) throws JMSException {
/*   83 */     super(session);
/*   84 */     if (Trace.isOn) {
/*   85 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "<init>(JmsSessionImpl)", new Object[] { session });
/*      */     }
/*      */ 
/*      */     
/*   89 */     this.providerMapMessage = (ProviderMapMessage)getProviderMessage();
/*      */     
/*   91 */     this.messageType = "jms_map";
/*      */     
/*   93 */     if (Trace.isOn) {
/*   94 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "<init>(JmsSessionImpl)");
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
/*      */   public JmsMapMessageImpl(String connectionType, Message message) throws JMSException {
/*  110 */     super(connectionType, message);
/*  111 */     if (Trace.isOn) {
/*  112 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "<init>(String,Message)", new Object[] { connectionType, message });
/*      */     }
/*      */ 
/*      */     
/*  116 */     this.messageType = "jms_map";
/*      */     
/*  118 */     if (Trace.isOn) {
/*  119 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "<init>(String,Message)");
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
/*      */   protected JmsMapMessageImpl(ProviderMapMessage newMsg, JmsSessionImpl newSess, String connectionTypeName) throws JMSException {
/*  132 */     super((ProviderMessage)newMsg, newSess, connectionTypeName);
/*  133 */     if (Trace.isOn) {
/*  134 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "<init>(ProviderMapMessage,JmsSessionImpl,String)", new Object[] { newMsg, newSess, connectionTypeName });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  140 */     this.providerMapMessage = newMsg;
/*  141 */     this.messageType = "jms_map";
/*      */ 
/*      */ 
/*      */     
/*  145 */     if (Trace.isOn) {
/*  146 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "<init>(ProviderMapMessage,JmsSessionImpl,String)");
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
/*      */   JmsMapMessageImpl(JmsSessionImpl session, MapMessage mapMessage) throws JMSException {
/*  159 */     super(session, (Message)mapMessage);
/*  160 */     if (Trace.isOn) {
/*  161 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "<init>(JmsSessionImpl,MapMessage)", new Object[] { session, mapMessage });
/*      */     }
/*      */ 
/*      */     
/*  165 */     this.providerMapMessage = (ProviderMapMessage)getProviderMessage();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  171 */     Enumeration<?> mapNames = mapMessage.getMapNames();
/*  172 */     if (mapNames != null) {
/*  173 */       while (mapNames.hasMoreElements()) {
/*  174 */         String name = (String)mapNames.nextElement();
/*  175 */         Object value = mapMessage.getObject(name);
/*  176 */         setObject(name, value);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*  181 */     if (Trace.isOn) {
/*  182 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "<init>(JmsSessionImpl,MapMessage)");
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
/*      */   JmsMapMessageImpl(JmsSessionImpl session, Message message, Map<String, Object> map) throws JMSException {
/*  194 */     super(session, message);
/*  195 */     if (Trace.isOn) {
/*  196 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "<init>(JmsSessionImpl,Message,Map<String , Object>)", new Object[] { session, message, map });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  201 */     this.providerMapMessage = (ProviderMapMessage)getProviderMessage();
/*      */ 
/*      */ 
/*      */     
/*  205 */     for (Map.Entry<String, Object> entry : map.entrySet()) {
/*  206 */       setObject(entry.getKey(), entry.getValue());
/*      */     }
/*      */     
/*  209 */     if (Trace.isOn) {
/*  210 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "<init>(JmsSessionImpl,Message,Map<String , Object>)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getBoolean(String name) throws JMSException {
/*  220 */     if (Trace.isOn) {
/*  221 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "getBoolean(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/*  225 */     Object obj = null;
/*      */     
/*  227 */     obj = this.providerMapMessage.getObject(name);
/*      */     
/*  229 */     boolean value = false;
/*      */     
/*  231 */     value = JmsPropertyContextImpl.parseBoolean(obj, name, (Class)MessageFormatException.class);
/*      */     
/*  233 */     if (Trace.isOn) {
/*  234 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "getBoolean(String)", 
/*  235 */           Boolean.valueOf(value));
/*      */     }
/*  237 */     return value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte getByte(String name) throws JMSException {
/*  245 */     if (Trace.isOn) {
/*  246 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "getByte(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/*  250 */     Object obj = null;
/*      */     
/*  252 */     obj = this.providerMapMessage.getObject(name);
/*      */     
/*  254 */     byte value = 0;
/*      */     
/*  256 */     value = JmsPropertyContextImpl.parseByte(obj, name, (Class)MessageFormatException.class);
/*  257 */     if (Trace.isOn) {
/*  258 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "getByte(String)", 
/*  259 */           Byte.valueOf(value));
/*      */     }
/*  261 */     return value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public short getShort(String name) throws JMSException {
/*  269 */     if (Trace.isOn) {
/*  270 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "getShort(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/*  274 */     Object obj = null;
/*      */     
/*  276 */     obj = this.providerMapMessage.getObject(name);
/*      */     
/*  278 */     short value = 0;
/*      */     
/*  280 */     value = JmsPropertyContextImpl.parseShort(obj, name, (Class)MessageFormatException.class);
/*  281 */     if (Trace.isOn) {
/*  282 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "getShort(String)", 
/*  283 */           Short.valueOf(value));
/*      */     }
/*  285 */     return value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public char getChar(String name) throws JMSException {
/*  293 */     if (Trace.isOn) {
/*  294 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "getChar(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/*  298 */     Object obj = null;
/*      */     
/*  300 */     obj = this.providerMapMessage.getObject(name);
/*      */     
/*  302 */     char value = Character.MIN_VALUE;
/*      */     
/*  304 */     if (obj instanceof Character) {
/*  305 */       value = ((Character)obj).charValue();
/*      */     } else {
/*  307 */       if (obj == null) {
/*      */         
/*  309 */         NullPointerException npe = new NullPointerException();
/*  310 */         if (Trace.isOn) {
/*  311 */           Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "getChar(String)", npe);
/*      */         }
/*  313 */         if (Trace.isOn) {
/*  314 */           Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "getChar(String)", npe);
/*      */         }
/*      */         
/*  317 */         throw npe;
/*      */       } 
/*      */       
/*  320 */       throwBadConvertException(obj, name, "Character", (Class)MessageFormatException.class);
/*      */     } 
/*      */     
/*  323 */     if (Trace.isOn) {
/*  324 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "getChar(String)", 
/*  325 */           Character.valueOf(value));
/*      */     }
/*  327 */     return value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getInt(String name) throws JMSException {
/*  335 */     if (Trace.isOn) {
/*  336 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "getInt(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/*  340 */     Object obj = null;
/*      */     
/*  342 */     obj = this.providerMapMessage.getObject(name);
/*      */     
/*  344 */     int value = 0;
/*      */     
/*  346 */     value = JmsPropertyContextImpl.parseInt(obj, name, (Class)MessageFormatException.class);
/*      */     
/*  348 */     if (Trace.isOn) {
/*  349 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "getInt(String)", 
/*  350 */           Integer.valueOf(value));
/*      */     }
/*  352 */     return value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getLong(String name) throws JMSException {
/*  360 */     if (Trace.isOn) {
/*  361 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "getLong(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/*  365 */     Object obj = null;
/*      */     
/*  367 */     obj = this.providerMapMessage.getObject(name);
/*      */     
/*  369 */     long value = 0L;
/*      */     
/*  371 */     value = JmsPropertyContextImpl.parseLong(obj, name, (Class)MessageFormatException.class);
/*      */     
/*  373 */     if (Trace.isOn) {
/*  374 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "getLong(String)", 
/*  375 */           Long.valueOf(value));
/*      */     }
/*  377 */     return value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getFloat(String name) throws JMSException {
/*  385 */     if (Trace.isOn) {
/*  386 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "getFloat(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/*  390 */     Object obj = null;
/*  391 */     obj = this.providerMapMessage.getObject(name);
/*      */     
/*  393 */     float value = 0.0F;
/*      */     
/*  395 */     value = JmsPropertyContextImpl.parseFloat(obj, name, (Class)MessageFormatException.class);
/*      */     
/*  397 */     if (Trace.isOn) {
/*  398 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "getFloat(String)", 
/*  399 */           Float.valueOf(value));
/*      */     }
/*  401 */     return value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getDouble(String name) throws JMSException {
/*  409 */     if (Trace.isOn) {
/*  410 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "getDouble(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/*  414 */     Object obj = null;
/*      */     
/*  416 */     obj = this.providerMapMessage.getObject(name);
/*      */     
/*  418 */     double value = 0.0D;
/*      */     
/*  420 */     value = JmsPropertyContextImpl.parseDouble(obj, name, (Class)MessageFormatException.class);
/*      */     
/*  422 */     if (Trace.isOn) {
/*  423 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "getDouble(String)", 
/*  424 */           Double.valueOf(value));
/*      */     }
/*  426 */     return value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getString(String name) throws JMSException {
/*  434 */     if (Trace.isOn) {
/*  435 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "getString(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/*  439 */     Object obj = null;
/*      */     
/*  441 */     obj = this.providerMapMessage.getObject(name);
/*      */     
/*  443 */     String value = null;
/*      */     
/*  445 */     if (obj instanceof String || obj == null) {
/*  446 */       value = (String)obj;
/*      */     
/*      */     }
/*  449 */     else if (obj instanceof byte[]) {
/*  450 */       throwBadConvertException(obj, name, "String", (Class)MessageFormatException.class);
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  455 */       value = obj.toString();
/*      */     } 
/*      */     
/*  458 */     if (Trace.isOn) {
/*  459 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "getString(String)", value);
/*      */     }
/*      */     
/*  462 */     return value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getBytes(String name) throws JMSException {
/*  470 */     if (Trace.isOn) {
/*  471 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "getBytes(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/*  475 */     Object obj = null;
/*      */     
/*  477 */     obj = this.providerMapMessage.getObject(name);
/*      */     
/*  479 */     byte[] value = null;
/*      */     
/*  481 */     if (obj instanceof byte[]) {
/*      */       
/*  483 */       byte[] objB = (byte[])obj;
/*  484 */       value = new byte[objB.length];
/*  485 */       System.arraycopy(objB, 0, value, 0, objB.length);
/*      */     
/*      */     }
/*  488 */     else if (obj == null) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  493 */       value = null;
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  498 */       throwBadConvertException(obj, name, "Byte[]", (Class)MessageFormatException.class);
/*      */     } 
/*      */ 
/*      */     
/*  502 */     if (Trace.isOn) {
/*  503 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "getBytes(String)", value);
/*      */     }
/*      */     
/*  506 */     return value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getObject(String name) throws JMSException {
/*  514 */     if (Trace.isOn) {
/*  515 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "getObject(String)", new Object[] { name });
/*      */     }
/*      */     
/*  518 */     Object result = null;
/*      */     
/*  520 */     Object obj = null;
/*      */     
/*  522 */     obj = this.providerMapMessage.getObject(name);
/*      */     
/*  524 */     if (obj instanceof byte[]) {
/*      */       
/*  526 */       byte[] objB = (byte[])obj;
/*  527 */       byte[] value = new byte[objB.length];
/*  528 */       System.arraycopy(objB, 0, value, 0, objB.length);
/*      */       
/*  530 */       result = value;
/*      */     }
/*      */     else {
/*      */       
/*  534 */       result = obj;
/*      */     } 
/*      */     
/*  537 */     if (Trace.isOn) {
/*  538 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "getObject(String)", result);
/*      */     }
/*      */     
/*  541 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Enumeration<String> getMapNames() throws JMSException {
/*  550 */     Enumeration<String> map = this.providerMapMessage.getMapNames();
/*      */     
/*  552 */     if (Trace.isOn) {
/*  553 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "getMapNames()", "getter", map);
/*      */     }
/*      */     
/*  556 */     return map;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBoolean(String name, boolean value) throws JMSException {
/*  564 */     if (Trace.isOn) {
/*  565 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "setBoolean(String,boolean)", new Object[] { name, 
/*  566 */             Boolean.valueOf(value) });
/*      */     }
/*      */     
/*  569 */     checkBodyWriteable("setBoolean");
/*  570 */     checkMapName(name, "setBoolean");
/*      */     
/*  572 */     this.providerMapMessage.setBoolean(name, value);
/*      */ 
/*      */     
/*  575 */     this.cachedMapToString = null;
/*      */     
/*  577 */     if (Trace.isOn) {
/*  578 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "setBoolean(String,boolean)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setByte(String name, byte value) throws JMSException {
/*  589 */     if (Trace.isOn) {
/*  590 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "setByte(String,byte)", new Object[] { name, 
/*  591 */             Byte.valueOf(value) });
/*      */     }
/*      */     
/*  594 */     checkBodyWriteable("setByte");
/*  595 */     checkMapName(name, "setByte");
/*      */     
/*  597 */     this.providerMapMessage.setByte(name, value);
/*      */ 
/*      */     
/*  600 */     this.cachedMapToString = null;
/*      */     
/*  602 */     if (Trace.isOn) {
/*  603 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "setByte(String,byte)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBytes(String name, byte[] value) throws JMSException {
/*  614 */     if (Trace.isOn) {
/*  615 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "setBytes(String,byte [ ])", new Object[] { name, value });
/*      */     }
/*      */ 
/*      */     
/*  619 */     checkBodyWriteable("setBytes");
/*  620 */     checkMapName(name, "setBytes(String, byte[])");
/*      */     
/*  622 */     int length = 0;
/*  623 */     if (value != null) {
/*  624 */       length = value.length;
/*      */     }
/*      */     
/*  627 */     setBytes(name, value, 0, length);
/*      */ 
/*      */     
/*  630 */     this.cachedMapToString = null;
/*      */     
/*  632 */     if (Trace.isOn) {
/*  633 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "setBytes(String,byte [ ])");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBytes(String name, byte[] value, int start, int length) throws JMSException {
/*  644 */     if (Trace.isOn) {
/*  645 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "setBytes(String,byte [ ],int,int)", new Object[] { name, value, 
/*  646 */             Integer.valueOf(start), 
/*  647 */             Integer.valueOf(length) });
/*      */     }
/*      */     
/*  650 */     checkBodyWriteable("setBytes");
/*  651 */     checkMapName(name, "setBytes(String, byte[], int, int)");
/*      */ 
/*      */     
/*  654 */     byte[] deepCopy = null;
/*      */ 
/*      */ 
/*      */     
/*  658 */     if (value != null) {
/*  659 */       deepCopy = new byte[length];
/*  660 */       System.arraycopy(value, start, deepCopy, 0, length);
/*      */     } 
/*      */ 
/*      */     
/*  664 */     this.providerMapMessage.setBytes(name, deepCopy);
/*      */ 
/*      */     
/*  667 */     this.cachedMapToString = null;
/*      */     
/*  669 */     if (Trace.isOn) {
/*  670 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "setBytes(String,byte [ ],int,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setChar(String name, char value) throws JMSException {
/*  681 */     if (Trace.isOn) {
/*  682 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "setChar(String,char)", new Object[] { name, 
/*  683 */             Character.valueOf(value) });
/*      */     }
/*      */     
/*  686 */     checkBodyWriteable("setChar");
/*  687 */     checkMapName(name, "setChar");
/*      */     
/*  689 */     this.providerMapMessage.setChar(name, value);
/*      */ 
/*      */     
/*  692 */     this.cachedMapToString = null;
/*      */     
/*  694 */     if (Trace.isOn) {
/*  695 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "setChar(String,char)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDouble(String name, double value) throws JMSException {
/*  706 */     if (Trace.isOn) {
/*  707 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "setDouble(String,double)", new Object[] { name, 
/*  708 */             Double.valueOf(value) });
/*      */     }
/*      */     
/*  711 */     checkBodyWriteable("setDouble");
/*  712 */     checkMapName(name, "setDouble");
/*      */     
/*  714 */     this.providerMapMessage.setDouble(name, value);
/*      */ 
/*      */     
/*  717 */     this.cachedMapToString = null;
/*      */     
/*  719 */     if (Trace.isOn) {
/*  720 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "setDouble(String,double)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFloat(String name, float value) throws JMSException {
/*  731 */     if (Trace.isOn) {
/*  732 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "setFloat(String,float)", new Object[] { name, 
/*  733 */             Float.valueOf(value) });
/*      */     }
/*      */     
/*  736 */     checkBodyWriteable("setFloat");
/*  737 */     checkMapName(name, "setFloat");
/*      */     
/*  739 */     this.providerMapMessage.setFloat(name, value);
/*      */ 
/*      */     
/*  742 */     this.cachedMapToString = null;
/*      */     
/*  744 */     if (Trace.isOn) {
/*  745 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "setFloat(String,float)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setInt(String name, int value) throws JMSException {
/*  756 */     if (Trace.isOn) {
/*  757 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "setInt(String,int)", new Object[] { name, 
/*  758 */             Integer.valueOf(value) });
/*      */     }
/*      */     
/*  761 */     checkBodyWriteable("setInt");
/*  762 */     checkMapName(name, "setInt");
/*      */     
/*  764 */     this.providerMapMessage.setInt(name, value);
/*      */ 
/*      */     
/*  767 */     this.cachedMapToString = null;
/*      */     
/*  769 */     if (Trace.isOn) {
/*  770 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "setInt(String,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLong(String name, long value) throws JMSException {
/*  780 */     if (Trace.isOn) {
/*  781 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "setLong(String,long)", new Object[] { name, 
/*  782 */             Long.valueOf(value) });
/*      */     }
/*      */     
/*  785 */     checkBodyWriteable("setLong");
/*  786 */     checkMapName(name, "setLong");
/*      */     
/*  788 */     this.providerMapMessage.setLong(name, value);
/*      */ 
/*      */     
/*  791 */     this.cachedMapToString = null;
/*      */     
/*  793 */     if (Trace.isOn) {
/*  794 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "setLong(String,long)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setObject(String name, Object valueP) throws JMSException {
/*  805 */     if (Trace.isOn) {
/*  806 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "setObject(String,Object)", new Object[] { name, valueP });
/*      */     }
/*      */ 
/*      */     
/*  810 */     Object value = valueP;
/*      */     
/*  812 */     checkBodyWriteable("setObject");
/*  813 */     checkMapName(name, "setObject");
/*      */ 
/*      */     
/*  816 */     if (value != null && !(value instanceof String) && !(value instanceof Integer) && !(value instanceof Short) && !(value instanceof Byte) && !(value instanceof Long) && !(value instanceof Float) && !(value instanceof Double) && !(value instanceof Boolean) && !(value instanceof Character) && !(value instanceof byte[])) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  829 */       HashMap<String, Object> inserts = new HashMap<>();
/*  830 */       inserts.put("XMSC_INSERT_OBJECT", value.getClass().getName());
/*  831 */       JMSException traceRet1 = (JMSException)JmsErrorUtils.createException("JMSCC0083", inserts);
/*  832 */       if (Trace.isOn) {
/*  833 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "setObject(String,Object)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  836 */       throw traceRet1;
/*      */     } 
/*      */     
/*  839 */     if (value instanceof byte[]) {
/*      */ 
/*      */       
/*  842 */       byte[] v = (byte[])value;
/*  843 */       byte[] tmp = new byte[v.length];
/*  844 */       System.arraycopy(v, 0, tmp, 0, v.length);
/*  845 */       value = tmp;
/*      */     } 
/*      */     
/*  848 */     this.providerMapMessage.setObject(name, value);
/*      */ 
/*      */     
/*  851 */     this.cachedMapToString = null;
/*      */     
/*  853 */     if (Trace.isOn) {
/*  854 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "setObject(String,Object)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setShort(String name, short value) throws JMSException {
/*  865 */     if (Trace.isOn) {
/*  866 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "setShort(String,short)", new Object[] { name, 
/*  867 */             Short.valueOf(value) });
/*      */     }
/*      */     
/*  870 */     checkBodyWriteable("setShort");
/*  871 */     checkMapName(name, "setShort");
/*      */     
/*  873 */     this.providerMapMessage.setShort(name, value);
/*      */ 
/*      */     
/*  876 */     this.cachedMapToString = null;
/*      */     
/*  878 */     if (Trace.isOn) {
/*  879 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "setShort(String,short)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setString(String name, String value) throws JMSException {
/*  890 */     if (Trace.isOn) {
/*  891 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "setString(String,String)", new Object[] { name, value });
/*      */     }
/*      */     
/*  894 */     checkBodyWriteable("setString");
/*  895 */     checkMapName(name, "setString");
/*      */     
/*  897 */     this.providerMapMessage.setString(name, value);
/*      */ 
/*      */     
/*  900 */     this.cachedMapToString = null;
/*      */     
/*  902 */     if (Trace.isOn) {
/*  903 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "setString(String,String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean itemExists(String name) throws JMSException {
/*  914 */     if (Trace.isOn) {
/*  915 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "itemExists(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/*  919 */     boolean exists = false;
/*      */     
/*  921 */     exists = this.providerMapMessage.itemExists(name);
/*      */     
/*  923 */     if (Trace.isOn) {
/*  924 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "itemExists(String)", 
/*  925 */           Boolean.valueOf(exists));
/*      */     }
/*  927 */     return exists;
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
/*      */   public String toString() {
/*  939 */     if (Trace.isOn) {
/*  940 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "toString()");
/*      */     }
/*  942 */     String val = super.toString();
/*      */     
/*  944 */     if (this.cachedMapToString == null) {
/*      */       
/*  946 */       StringBuffer sb = new StringBuffer();
/*      */       try {
/*  948 */         Enumeration<String> props = getMapNames();
/*      */         
/*  950 */         while (props.hasMoreElements()) {
/*  951 */           String nextKey = props.nextElement();
/*  952 */           Object propVal = getObject(nextKey);
/*      */           
/*  954 */           if (propVal == null) {
/*  955 */             propVal = "<null>";
/*      */           }
/*  957 */           sb.append("\n" + nextKey + " = " + propVal);
/*      */         
/*      */         }
/*      */       
/*      */       }
/*  962 */       catch (JMSException e) {
/*  963 */         if (Trace.isOn) {
/*  964 */           Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "toString()", (Throwable)e);
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  971 */       this.cachedMapToString = sb.toString();
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  978 */     val = val + this.cachedMapToString;
/*      */     
/*  980 */     if (Trace.isOn) {
/*  981 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "toString()", val);
/*      */     }
/*  983 */     return val;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected ProviderMessage createProviderMessage(JmsSessionImpl session) throws Exception {
/*  992 */     if (Trace.isOn) {
/*  993 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "createProviderMessage(JmsSessionImpl)", new Object[] { session });
/*      */     }
/*      */ 
/*      */     
/*  997 */     if (session != null) {
/*  998 */       this.providerMapMessage = getProviderMessageFactory().createMapMessage(session.getProviderSession());
/*      */     } else {
/*      */       
/* 1001 */       this.providerMapMessage = getProviderMessageFactory().createMapMessage(null);
/*      */     } 
/*      */     
/* 1004 */     if (Trace.isOn) {
/* 1005 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "createProviderMessage(JmsSessionImpl)", this.providerMapMessage);
/*      */     }
/*      */     
/* 1008 */     return (ProviderMessage)this.providerMapMessage;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void checkMapName(String name, String callingMethodName) throws IllegalArgumentException {
/* 1017 */     if (Trace.isOn) {
/* 1018 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "checkMapName(String,String)", new Object[] { name, callingMethodName });
/*      */     }
/*      */ 
/*      */     
/* 1022 */     if (name == null || "".equals(name)) {
/* 1023 */       IllegalArgumentException je = (IllegalArgumentException)JmsErrorUtils.createException("JMSCC0043", null);
/* 1024 */       if (Trace.isOn) {
/* 1025 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "checkMapName(String,String)", je);
/*      */       }
/*      */       
/* 1028 */       throw je;
/*      */     } 
/*      */     
/* 1031 */     if (Trace.isOn) {
/* 1032 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "checkMapName(String,String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected Object getMap(boolean deepClone) throws JMSException {
/* 1039 */     if (Trace.isOn) {
/* 1040 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "getMap(boolean)", new Object[] {
/* 1041 */             Boolean.valueOf(deepClone)
/*      */           });
/*      */     }
/* 1044 */     Object traceRet1 = this.providerMapMessage.getMap(deepClone);
/* 1045 */     if (Trace.isOn) {
/* 1046 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMapMessageImpl", "getMap(boolean)", traceRet1);
/*      */     }
/*      */     
/* 1049 */     return traceRet1;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsMapMessageImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */