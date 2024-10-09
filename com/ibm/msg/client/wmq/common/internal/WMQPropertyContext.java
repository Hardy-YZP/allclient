/*     */ package com.ibm.msg.client.wmq.common.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsPropertyCacheInterface;
/*     */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*     */ import com.ibm.msg.client.jms.JmsValidationInterface;
/*     */ import com.ibm.msg.client.jms.internal.JmsPropertyContextImpl;
/*     */ import com.ibm.msg.client.provider.ProviderPropertyContextCallback;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.util.Collection;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.Vector;
/*     */ import javax.jms.JMSException;
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
/*     */ public class WMQPropertyContext
/*     */   implements JmsPropertyContext, JmsValidationInterface, JmsPropertyCacheInterface
/*     */ {
/*     */   private static final long serialVersionUID = -2339897896507411816L;
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/WMQPropertyContext.java";
/*     */   protected JmsPropertyContext jmsPropertyContext;
/*     */   protected transient Thread creator;
/*     */   protected transient long createTime;
/*     */   protected transient StackTraceElement[] createStack;
/*     */   
/*     */   static {
/*  60 */     if (Trace.isOn) {
/*  61 */       Trace.data("com.ibm.msg.client.wmq.common.internal.WMQPropertyContext", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/WMQPropertyContext.java");
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
/*     */   protected WMQPropertyContext(JmsPropertyContext jmsProps) {
/*  94 */     if (Trace.isOn) {
/*  95 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQPropertyContext", "<init>(JmsPropertyContext)", null);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 113 */     if (jmsProps == null) {
/* 114 */       this.jmsPropertyContext = (JmsPropertyContext)new JmsPropertyContextImpl();
/*     */     } else {
/*     */       
/* 117 */       this.jmsPropertyContext = jmsProps;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 123 */       if (jmsProps instanceof ProviderPropertyContextCallback) {
/* 124 */         ((ProviderPropertyContextCallback)jmsProps).setProviderPropertyContext(this);
/*     */       }
/*     */     } 
/*     */     
/* 128 */     this.creator = Thread.currentThread();
/* 129 */     this.createTime = System.currentTimeMillis();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 135 */     if (Trace.isOn) {
/* 136 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQPropertyContext", "<init>(JmsPropertyContext)");
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
/*     */   public void clearCachedValue(String key) {}
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
/*     */   public boolean equals(Object o) {
/* 161 */     if (this == o) {
/* 162 */       return true;
/*     */     }
/* 164 */     if (null == o || !(o instanceof WMQPropertyContext)) {
/* 165 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 170 */     WMQPropertyContext other = (WMQPropertyContext)o;
/*     */     
/* 172 */     JmsPropertyContext thisParentContext = this.jmsPropertyContext;
/* 173 */     JmsPropertyContext thatParentContext = other.jmsPropertyContext;
/*     */     
/* 175 */     return thisParentContext.equals(thatParentContext);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 184 */     return this.jmsPropertyContext.hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getBooleanProperty(String name) throws JMSException {
/* 193 */     assert this.jmsPropertyContext != null : "jmsPropertyContext null in getBooleanProperty";
/* 194 */     boolean result = this.jmsPropertyContext.getBooleanProperty(name);
/* 195 */     if (Trace.isOn) {
/* 196 */       Trace.data(this, "getBooleanProperty(String)", "via WMQPropertyContext", Boolean.valueOf(result));
/*     */     }
/* 198 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte getByteProperty(String name) throws JMSException {
/* 207 */     assert this.jmsPropertyContext != null : "jmsPropertyContext null in getByteProperty";
/* 208 */     byte result = this.jmsPropertyContext.getByteProperty(name);
/* 209 */     if (Trace.isOn) {
/* 210 */       Trace.data(this, "getByteProperty(String)", "via WMQPropertyContext", Byte.valueOf(result));
/*     */     }
/* 212 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getBytesProperty(String name) throws JMSException {
/* 221 */     assert this.jmsPropertyContext != null : "jmsPropertyContext null in getBytesProperty";
/* 222 */     byte[] result = this.jmsPropertyContext.getBytesProperty(name);
/* 223 */     if (Trace.isOn) {
/* 224 */       Trace.data(this, "getBytesProperty(String)", "via WMQPropertyContext", result);
/*     */     }
/* 226 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getCachedValue(String key) {
/* 236 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getCachedValueAll(Map<String, Object> props) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCanonicalKey(String keyIn) {
/* 255 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char getCharProperty(String name) throws JMSException {
/* 264 */     assert this.jmsPropertyContext != null : "jmsPropertyContext null in getCharProperty";
/* 265 */     char result = this.jmsPropertyContext.getCharProperty(name);
/* 266 */     if (Trace.isOn) {
/* 267 */       Trace.data(this, "getCharProperty(String)", "via WMQPropertyContext", Character.valueOf(result));
/*     */     }
/* 269 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getDoubleProperty(String name) throws JMSException {
/* 278 */     assert this.jmsPropertyContext != null : "jmsPropertyContext null in getDoubleProperty";
/* 279 */     double result = this.jmsPropertyContext.getDoubleProperty(name);
/* 280 */     if (Trace.isOn) {
/* 281 */       Trace.data(this, "getDoubleProperty(String)", "via WMQPropertyContext", Double.valueOf(result));
/*     */     }
/* 283 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getFloatProperty(String name) throws JMSException {
/* 292 */     assert this.jmsPropertyContext != null : "jmsPropertyContext null in getFloatProperty";
/* 293 */     float result = this.jmsPropertyContext.getFloatProperty(name);
/* 294 */     if (Trace.isOn) {
/* 295 */       Trace.data(this, "getFloatProperty(String)", "via WMQPropertyContext", Float.valueOf(result));
/*     */     }
/* 297 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getIntProperty(String name) throws JMSException {
/* 306 */     assert this.jmsPropertyContext != null : "jmsPropertyContext null in getIntProperty";
/* 307 */     int result = this.jmsPropertyContext.getIntProperty(name);
/* 308 */     if (Trace.isOn) {
/* 309 */       Trace.data(this, "getIntProperty(String)", "via WMQPropertyContext", Integer.valueOf(result));
/*     */     }
/* 311 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getLongProperty(String name) throws JMSException {
/* 320 */     assert this.jmsPropertyContext != null : "jmsPropertyContext null in getLongProperty";
/* 321 */     long result = this.jmsPropertyContext.getLongProperty(name);
/* 322 */     if (Trace.isOn) {
/* 323 */       Trace.data(this, "getLongProperty(String)", "via WMQPropertyContext", Long.valueOf(result));
/*     */     }
/* 325 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getObjectProperty(String name) throws JMSException {
/* 335 */     assert this.jmsPropertyContext != null : "jmsPropertyContext null in getObjectProperty";
/* 336 */     Object result = this.jmsPropertyContext.getObjectProperty(name);
/*     */     
/* 338 */     if (Trace.isOn) {
/* 339 */       if ("XMSC_PASSWORD".equals(name)) {
/* 340 */         Trace.data(this, "getObjectProperty(String)", "via WMQPropertyContext", "********");
/*     */       } else {
/*     */         
/* 343 */         Trace.data(this, "getObjectProperty(String)", "via WMQPropertyContext", result);
/*     */       } 
/*     */     }
/* 346 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Enumeration<String> getPropertyNames() throws JMSException {
/* 355 */     assert this.jmsPropertyContext != null : "jmsPropertyContext null in getPropertyNames";
/* 356 */     Enumeration<String> result = this.jmsPropertyContext.getPropertyNames();
/* 357 */     if (Trace.isOn) {
/* 358 */       Trace.data(this, "getPropertyNames()", "via WMQPropertyContext", result);
/*     */     }
/* 360 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getShortProperty(String name) throws JMSException {
/* 369 */     assert this.jmsPropertyContext != null : "jmsPropertyContext null in getShortProperty";
/* 370 */     short result = this.jmsPropertyContext.getShortProperty(name);
/* 371 */     if (Trace.isOn) {
/* 372 */       Trace.data(this, "getShortProperty(String)", "via WMQPropertyContext", Short.valueOf(result));
/*     */     }
/* 374 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStringProperty(String name) throws JMSException {
/* 384 */     assert this.jmsPropertyContext != null : "jmsPropertyContext null in getStringProperty";
/* 385 */     String result = this.jmsPropertyContext.getStringProperty(name);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 390 */     assert !name.equalsIgnoreCase("XMSC_WMQ_HOST_NAME") && !name.equalsIgnoreCase("XMSC_WMQ_PORT");
/*     */     
/* 392 */     if (Trace.isOn) {
/* 393 */       if ("XMSC_PASSWORD".equals(name)) {
/* 394 */         Trace.data(this, "getStringProperty(String)", "via WMQPropertyContext", "********");
/*     */       } else {
/*     */         
/* 397 */         Trace.data(this, "getStringProperty(String)", "via WMQPropertyContext", result);
/*     */       } 
/*     */     }
/* 400 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object mapFromCanonical(String keyIn, Object valueIn) {
/* 411 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector<Object> mapToCanonical(String keyIn, Object valueIn) {
/* 422 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean propertyExists(String name) throws JMSException {
/* 431 */     assert this.jmsPropertyContext != null : "jmsPropertyContext null in propertyExists";
/* 432 */     boolean result = this.jmsPropertyContext.propertyExists(name);
/* 433 */     if (Trace.isOn) {
/* 434 */       Trace.data(this, "propertyExists(String)", "via WMQPropertyContext", Boolean.valueOf(result));
/*     */     }
/* 436 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBatchProperties(Map<String, Object> properties) throws JMSException {
/* 446 */     this.jmsPropertyContext.setBatchProperties(properties);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBooleanProperty(String name, boolean value) throws JMSException {
/* 455 */     if (Trace.isOn) {
/* 456 */       Trace.data(this, "setBooleanProperty(String,boolean)", "via WMQPropertyContext", new Object[] { name, Boolean.valueOf(value) });
/*     */     }
/* 458 */     setObjectProperty(name, Boolean.valueOf(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setByteProperty(String name, byte value) throws JMSException {
/* 467 */     if (Trace.isOn) {
/* 468 */       Trace.data(this, "setByteProperty(String,byte)", "via WMQPropertyContext", new Object[] { name, Byte.valueOf(value) });
/*     */     }
/* 470 */     setObjectProperty(name, Byte.valueOf(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBytesProperty(String name, byte[] value) throws JMSException {
/* 479 */     if (Trace.isOn) {
/* 480 */       Trace.data(this, "setBytesProperty(String,byte [ ])", "via WMQPropertyContext", new Object[] { name, value });
/*     */     }
/* 482 */     setObjectProperty(name, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setCachedValue(String key, Object value) {
/* 493 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCharProperty(String name, char value) throws JMSException {
/* 502 */     if (Trace.isOn) {
/* 503 */       Trace.data(this, "setCharProperty(String,char)", "via WMQPropertyContext", new Object[] { name, Character.valueOf(value) });
/*     */     }
/* 505 */     setObjectProperty(name, Character.valueOf(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDoubleProperty(String name, double value) throws JMSException {
/* 514 */     if (Trace.isOn) {
/* 515 */       Trace.data(this, "setDoubleProperty(String,double)", "via WMQPropertyContext", new Object[] { name, Double.valueOf(value) });
/*     */     }
/* 517 */     setObjectProperty(name, Double.valueOf(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFloatProperty(String name, float value) throws JMSException {
/* 526 */     if (Trace.isOn) {
/* 527 */       Trace.data(this, "setFloatProperty(String,float)", "via WMQPropertyContext", new Object[] { name, Float.valueOf(value) });
/*     */     }
/* 529 */     setObjectProperty(name, Float.valueOf(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIntProperty(String name, int value) throws JMSException {
/* 538 */     if (Trace.isOn) {
/* 539 */       Trace.data(this, "setIntProperty(String,int)", "via WMQPropertyContext", new Object[] { name, Integer.valueOf(value) });
/*     */     }
/* 541 */     setObjectProperty(name, Integer.valueOf(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLongProperty(String name, long value) throws JMSException {
/* 550 */     if (Trace.isOn) {
/* 551 */       Trace.data(this, "setLongProperty(String,long)", "via WMQPropertyContext", new Object[] { name, Long.valueOf(value) });
/*     */     }
/* 553 */     setObjectProperty(name, Long.valueOf(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setObjectProperty(String name, Object value) throws JMSException {
/* 563 */     if (Trace.isOn) {
/* 564 */       Trace.data(this, "setObjectProperty(String,Object)", "via WMQPropertyContext", new Object[] { name, value });
/*     */     }
/* 566 */     this.jmsPropertyContext.setObjectProperty(name, value);
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
/*     */   public void setPropertyContext(JmsPropertyContext jmsPropertyContext) {
/* 580 */     if (Trace.isOn) {
/* 581 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.WMQPropertyContext", "setPropertyContext(JmsPropertyContext)", "setter", jmsPropertyContext);
/*     */     }
/*     */     
/* 584 */     this.jmsPropertyContext = jmsPropertyContext;
/*     */ 
/*     */ 
/*     */     
/* 588 */     if (jmsPropertyContext instanceof ProviderPropertyContextCallback) {
/* 589 */       ((ProviderPropertyContextCallback)jmsPropertyContext).setProviderPropertyContext(this);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setShortProperty(String name, short value) throws JMSException {
/* 599 */     if (Trace.isOn) {
/* 600 */       Trace.data(this, "setShortProperty(String,short)", "via WMQPropertyContext", new Object[] { name, Short.valueOf(value) });
/*     */     }
/* 602 */     setObjectProperty(name, Short.valueOf(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStringProperty(String name, String value) throws JMSException {
/* 613 */     if (Trace.isOn) {
/* 614 */       if ("XMSC_PASSWORD".equals(name)) {
/* 615 */         Trace.data(this, "setStringProperty(String,String)", "via WMQPropertyContext", new Object[] { name, "********" });
/*     */       } else {
/*     */         
/* 618 */         Trace.data(this, "setStringProperty(String,String)", "via WMQPropertyContext", new Object[] { name, value });
/*     */       } 
/*     */     }
/* 621 */     setObjectProperty(name, value);
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
/*     */   protected void throwValidationException(String name, Object value, Exception linkedException) throws JMSException {
/*     */     JMSException jMSException1;
/* 635 */     if (Trace.isOn) {
/* 636 */       if ("XMSC_PASSWORD".equals(name)) {
/* 637 */         Trace.data(this, "throwValidationException(String,Object,Exception)", "via WMQPropertyContext", new Object[] { name, "********", linkedException });
/*     */       }
/*     */       else {
/*     */         
/* 641 */         Trace.data(this, "throwValidationException(String,Object,Exception)", "via WMQPropertyContext", new Object[] { name, value, linkedException });
/*     */       } 
/*     */     }
/*     */     
/* 645 */     Exception validateException = linkedException;
/*     */     
/* 647 */     if (!(linkedException instanceof JMSException)) {
/*     */       
/* 649 */       HashMap<String, Object> inserts = new HashMap<>();
/* 650 */       inserts.put("XMSC_INSERT_PROPERTY", name);
/* 651 */       inserts.put("XMSC_INSERT_VALUE", value);
/* 652 */       JMSException e = (JMSException)NLSServices.createException("JMSCMQ0004", inserts);
/* 653 */       e.setLinkedException(linkedException);
/* 654 */       jMSException1 = e;
/*     */     } 
/* 656 */     JMSException jmsex = jMSException1;
/* 657 */     if (Trace.isOn) {
/* 658 */       Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.WMQPropertyContext", "throwValidationException(String,Object,Exception)", (Throwable)jmsex);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 663 */     throw jmsex;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean validate(Object name, Object value) throws JMSException {
/* 673 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String crossPropertyValidate() {
/* 683 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearCachedValueAll() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsCachedValue(String key) {
/* 703 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 712 */     this.jmsPropertyContext.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 722 */     boolean result = this.jmsPropertyContext.containsKey(key);
/* 723 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsValue(Object value) {
/* 732 */     boolean result = this.jmsPropertyContext.containsValue(value);
/* 733 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<Map.Entry<String, Object>> entrySet() {
/* 742 */     Set<Map.Entry<String, Object>> result = this.jmsPropertyContext.entrySet();
/* 743 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object get(Object key) {
/* 752 */     Object result = this.jmsPropertyContext.get(key);
/* 753 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 762 */     boolean result = this.jmsPropertyContext.isEmpty();
/* 763 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<String> keySet() {
/* 772 */     Set<String> result = this.jmsPropertyContext.keySet();
/* 773 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object put(String arg0, Object arg1) {
/* 782 */     Object result = this.jmsPropertyContext.put(arg0, arg1);
/* 783 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void putAll(Map<? extends String, ? extends Object> arg0) {
/* 792 */     this.jmsPropertyContext.putAll(arg0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object remove(Object key) {
/* 801 */     Object result = this.jmsPropertyContext.remove(key);
/* 802 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 811 */     int size = this.jmsPropertyContext.size();
/* 812 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<Object> values() {
/* 821 */     Collection<Object> result = this.jmsPropertyContext.values();
/* 822 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object mapFromCanonical(JmsPropertyContext context, String keyIn, Object valueIn) {
/* 831 */     if (Trace.isOn) {
/* 832 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQPropertyContext", "mapFromCanonical(JmsPropertyContext,String,Object)", new Object[] { context, keyIn, valueIn });
/*     */     }
/*     */ 
/*     */     
/* 836 */     Object traceRet1 = mapFromCanonical(keyIn, valueIn);
/* 837 */     if (Trace.isOn) {
/* 838 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQPropertyContext", "mapFromCanonical(JmsPropertyContext,String,Object)", traceRet1);
/*     */     }
/*     */     
/* 841 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector<Object> mapToCanonical(JmsPropertyContext context, String keyIn, Object valueIn) {
/* 850 */     if (Trace.isOn) {
/* 851 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQPropertyContext", "mapToCanonical(JmsPropertyContext,String,Object)", new Object[] { context, keyIn, valueIn });
/*     */     }
/*     */ 
/*     */     
/* 855 */     Vector<Object> traceRet1 = mapToCanonical(keyIn, valueIn);
/* 856 */     if (Trace.isOn) {
/* 857 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQPropertyContext", "mapToCanonical(JmsPropertyContext,String,Object)", traceRet1);
/*     */     }
/*     */     
/* 860 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/*     */     WMQPropertyContext newContext;
/* 872 */     if (Trace.isOn) {
/* 873 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQPropertyContext", "clone()");
/*     */     }
/*     */     
/* 876 */     if (Trace.isOn) {
/* 877 */       Trace.data(this, "Cloning WMQPropertyContext", null);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 883 */       ByteArrayOutputStream baos = new ByteArrayOutputStream();
/* 884 */       ObjectOutputStream oos = new ObjectOutputStream(baos);
/* 885 */       oos.writeObject(this);
/* 886 */       oos.flush();
/* 887 */       oos.close();
/*     */ 
/*     */       
/* 890 */       ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
/* 891 */       ObjectInputStream ois = new ObjectInputStream(bais);
/* 892 */       Object o = ois.readObject();
/*     */       
/* 894 */       assert o instanceof WMQPropertyContext;
/* 895 */       newContext = (WMQPropertyContext)o;
/*     */     
/*     */     }
/* 898 */     catch (IOException e) {
/* 899 */       if (Trace.isOn) {
/* 900 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.common.internal.WMQPropertyContext", "clone()", e, 1);
/*     */       }
/*     */       
/* 903 */       CloneNotSupportedException cnse = new CloneNotSupportedException();
/* 904 */       cnse.initCause(e);
/* 905 */       if (Trace.isOn) {
/* 906 */         Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.WMQPropertyContext", "clone()", cnse, 1);
/*     */       }
/*     */       
/* 909 */       throw cnse;
/*     */     
/*     */     }
/* 912 */     catch (ClassNotFoundException e) {
/* 913 */       if (Trace.isOn) {
/* 914 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.common.internal.WMQPropertyContext", "clone()", e, 2);
/*     */       }
/*     */       
/* 917 */       CloneNotSupportedException cnse = new CloneNotSupportedException();
/* 918 */       cnse.initCause(e);
/* 919 */       if (Trace.isOn) {
/* 920 */         Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.WMQPropertyContext", "clone()", cnse, 2);
/*     */       }
/*     */       
/* 923 */       throw cnse;
/*     */     } 
/*     */ 
/*     */     
/* 927 */     if (Trace.isOn) {
/* 928 */       Trace.data(this, "Created clone", newContext);
/*     */     }
/*     */     
/* 931 */     if (Trace.isOn) {
/* 932 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQPropertyContext", "clone()", newContext);
/*     */     }
/*     */     
/* 935 */     return newContext;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\common\internal\WMQPropertyContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */