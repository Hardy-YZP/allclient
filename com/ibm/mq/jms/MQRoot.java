/*     */ package com.ibm.mq.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*     */ import java.util.Collection;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
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
/*     */ public abstract class MQRoot
/*     */   implements JmsPropertyContext
/*     */ {
/*     */   private static final long serialVersionUID = 7385857931340705898L;
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQRoot.java";
/*     */   protected JmsPropertyContext delegate;
/*     */   
/*     */   static {
/*  45 */     if (Trace.isOn) {
/*  46 */       Trace.data("com.ibm.mq.jms.MQRoot", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQRoot.java");
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
/*     */   protected MQRoot() {
/*  59 */     if (Trace.isOn) {
/*  60 */       Trace.entry(this, "com.ibm.mq.jms.MQRoot", "<init>()");
/*     */     }
/*     */     
/*  63 */     if (Trace.isOn) {
/*  64 */       Trace.exit(this, "com.ibm.mq.jms.MQRoot", "<init>()");
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
/*     */   public void setBatchProperties(Map<String, Object> properties) throws JMSException {
/*  78 */     if (Trace.isOn) {
/*  79 */       Trace.data(this, "com.ibm.mq.jms.MQRoot", "setBatchProperties(Map<String , Object>)", "setter", properties);
/*     */     }
/*     */     
/*  82 */     this.delegate.setBatchProperties(properties);
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
/*     */   public void setBooleanProperty(String name, boolean value) throws JMSException {
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.entry(this, "com.ibm.mq.jms.MQRoot", "setBooleanProperty(String,boolean)", new Object[] { name, 
/*  98 */             Boolean.valueOf(value) });
/*     */     }
/* 100 */     this.delegate.setBooleanProperty(name, value);
/*     */     
/* 102 */     if (Trace.isOn) {
/* 103 */       Trace.exit(this, "com.ibm.mq.jms.MQRoot", "setBooleanProperty(String,boolean)");
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
/*     */   public void setByteProperty(String name, byte value) throws JMSException {
/* 119 */     if (Trace.isOn) {
/* 120 */       Trace.entry(this, "com.ibm.mq.jms.MQRoot", "setByteProperty(String,byte)", new Object[] { name, 
/* 121 */             Byte.valueOf(value) });
/*     */     }
/*     */     
/* 124 */     this.delegate.setByteProperty(name, value);
/* 125 */     if (Trace.isOn) {
/* 126 */       Trace.exit(this, "com.ibm.mq.jms.MQRoot", "setByteProperty(String,byte)");
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
/*     */   public void setBytesProperty(String name, byte[] value) throws JMSException {
/* 142 */     if (Trace.isOn) {
/* 143 */       Trace.entry(this, "com.ibm.mq.jms.MQRoot", "setBytesProperty(String,byte [ ])", new Object[] { name, value });
/*     */     }
/*     */     
/* 146 */     this.delegate.setBytesProperty(name, value);
/*     */     
/* 148 */     if (Trace.isOn) {
/* 149 */       Trace.exit(this, "com.ibm.mq.jms.MQRoot", "setBytesProperty(String,byte [ ])");
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
/*     */   public void setCharProperty(String name, char value) throws JMSException {
/* 165 */     if (Trace.isOn) {
/* 166 */       Trace.entry(this, "com.ibm.mq.jms.MQRoot", "setCharProperty(String,char)", new Object[] { name, 
/* 167 */             Character.valueOf(value) });
/*     */     }
/* 169 */     this.delegate.setCharProperty(name, value);
/*     */     
/* 171 */     if (Trace.isOn) {
/* 172 */       Trace.exit(this, "com.ibm.mq.jms.MQRoot", "setCharProperty(String,char)");
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
/*     */   public void setDoubleProperty(String name, double value) throws JMSException {
/* 188 */     if (Trace.isOn) {
/* 189 */       Trace.entry(this, "com.ibm.mq.jms.MQRoot", "setDoubleProperty(String,double)", new Object[] { name, 
/* 190 */             Double.valueOf(value) });
/*     */     }
/* 192 */     this.delegate.setDoubleProperty(name, value);
/*     */     
/* 194 */     if (Trace.isOn) {
/* 195 */       Trace.exit(this, "com.ibm.mq.jms.MQRoot", "setDoubleProperty(String,double)");
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
/*     */   public void setFloatProperty(String name, float value) throws JMSException {
/* 211 */     if (Trace.isOn) {
/* 212 */       Trace.entry(this, "com.ibm.mq.jms.MQRoot", "setFloatProperty(String,float)", new Object[] { name, 
/* 213 */             Float.valueOf(value) });
/*     */     }
/* 215 */     this.delegate.setFloatProperty(name, value);
/*     */     
/* 217 */     if (Trace.isOn) {
/* 218 */       Trace.exit(this, "com.ibm.mq.jms.MQRoot", "setFloatProperty(String,float)");
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
/*     */   public void setIntProperty(String name, int value) throws JMSException {
/* 234 */     if (Trace.isOn) {
/* 235 */       Trace.entry(this, "com.ibm.mq.jms.MQRoot", "setIntProperty(String,int)", new Object[] { name, 
/* 236 */             Integer.valueOf(value) });
/*     */     }
/* 238 */     this.delegate.setIntProperty(name, value);
/*     */     
/* 240 */     if (Trace.isOn) {
/* 241 */       Trace.exit(this, "com.ibm.mq.jms.MQRoot", "setIntProperty(String,int)");
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
/*     */   public void setLongProperty(String name, long value) throws JMSException {
/* 257 */     if (Trace.isOn) {
/* 258 */       Trace.entry(this, "com.ibm.mq.jms.MQRoot", "setLongProperty(String,long)", new Object[] { name, 
/* 259 */             Long.valueOf(value) });
/*     */     }
/* 261 */     this.delegate.setLongProperty(name, value);
/*     */     
/* 263 */     if (Trace.isOn) {
/* 264 */       Trace.exit(this, "com.ibm.mq.jms.MQRoot", "setLongProperty(String,long)");
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
/*     */   public void setObjectProperty(String name, Object value) throws JMSException {
/* 280 */     if (Trace.isOn) {
/* 281 */       Trace.entry(this, "com.ibm.mq.jms.MQRoot", "setObjectProperty(String,Object)", new Object[] { name, value });
/*     */     }
/*     */     
/* 284 */     this.delegate.setObjectProperty(name, value);
/*     */     
/* 286 */     if (Trace.isOn) {
/* 287 */       Trace.exit(this, "com.ibm.mq.jms.MQRoot", "setObjectProperty(String,Object)");
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
/*     */   public void setShortProperty(String name, short value) throws JMSException {
/* 303 */     if (Trace.isOn) {
/* 304 */       Trace.entry(this, "com.ibm.mq.jms.MQRoot", "setShortProperty(String,short)", new Object[] { name, 
/* 305 */             Short.valueOf(value) });
/*     */     }
/* 307 */     this.delegate.setShortProperty(name, value);
/*     */     
/* 309 */     if (Trace.isOn) {
/* 310 */       Trace.exit(this, "com.ibm.mq.jms.MQRoot", "setShortProperty(String,short)");
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
/*     */   public void setStringProperty(String name, String value) throws JMSException {
/* 326 */     if (Trace.isOn) {
/* 327 */       Trace.entry(this, "com.ibm.mq.jms.MQRoot", "setStringProperty(String,String)", new Object[] { name, value });
/*     */     }
/*     */     
/* 330 */     this.delegate.setStringProperty(name, value);
/* 331 */     if (Trace.isOn) {
/* 332 */       Trace.exit(this, "com.ibm.mq.jms.MQRoot", "setStringProperty(String,String)");
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
/*     */   public boolean getBooleanProperty(String name) throws JMSException {
/* 346 */     if (Trace.isOn) {
/* 347 */       Trace.entry(this, "com.ibm.mq.jms.MQRoot", "getBooleanProperty(String)", new Object[] { name });
/*     */     }
/* 349 */     boolean traceRet1 = this.delegate.getBooleanProperty(name);
/* 350 */     if (Trace.isOn) {
/* 351 */       Trace.exit(this, "com.ibm.mq.jms.MQRoot", "getBooleanProperty(String)", 
/* 352 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 354 */     return traceRet1;
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
/*     */   public byte getByteProperty(String name) throws JMSException {
/* 366 */     if (Trace.isOn) {
/* 367 */       Trace.entry(this, "com.ibm.mq.jms.MQRoot", "getByteProperty(String)", new Object[] { name });
/*     */     }
/* 369 */     byte traceRet1 = this.delegate.getByteProperty(name);
/* 370 */     if (Trace.isOn) {
/* 371 */       Trace.exit(this, "com.ibm.mq.jms.MQRoot", "getByteProperty(String)", Byte.valueOf(traceRet1));
/*     */     }
/* 373 */     return traceRet1;
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
/*     */   public byte[] getBytesProperty(String name) throws JMSException {
/* 385 */     if (Trace.isOn) {
/* 386 */       Trace.entry(this, "com.ibm.mq.jms.MQRoot", "getBytesProperty(String)", new Object[] { name });
/*     */     }
/* 388 */     byte[] traceRet1 = this.delegate.getBytesProperty(name);
/* 389 */     if (Trace.isOn) {
/* 390 */       Trace.exit(this, "com.ibm.mq.jms.MQRoot", "getBytesProperty(String)", traceRet1);
/*     */     }
/* 392 */     return traceRet1;
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
/*     */   public char getCharProperty(String name) throws JMSException {
/* 404 */     if (Trace.isOn) {
/* 405 */       Trace.entry(this, "com.ibm.mq.jms.MQRoot", "getCharProperty(String)", new Object[] { name });
/*     */     }
/* 407 */     char traceRet1 = this.delegate.getCharProperty(name);
/* 408 */     if (Trace.isOn) {
/* 409 */       Trace.exit(this, "com.ibm.mq.jms.MQRoot", "getCharProperty(String)", 
/* 410 */           Character.valueOf(traceRet1));
/*     */     }
/* 412 */     return traceRet1;
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
/*     */   public double getDoubleProperty(String name) throws JMSException {
/* 424 */     if (Trace.isOn) {
/* 425 */       Trace.entry(this, "com.ibm.mq.jms.MQRoot", "getDoubleProperty(String)", new Object[] { name });
/*     */     }
/* 427 */     double traceRet1 = this.delegate.getDoubleProperty(name);
/* 428 */     if (Trace.isOn) {
/* 429 */       Trace.exit(this, "com.ibm.mq.jms.MQRoot", "getDoubleProperty(String)", 
/* 430 */           Double.valueOf(traceRet1));
/*     */     }
/* 432 */     return traceRet1;
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
/*     */   public float getFloatProperty(String name) throws JMSException {
/* 444 */     if (Trace.isOn) {
/* 445 */       Trace.entry(this, "com.ibm.mq.jms.MQRoot", "getFloatProperty(String)", new Object[] { name });
/*     */     }
/* 447 */     float traceRet1 = this.delegate.getFloatProperty(name);
/* 448 */     if (Trace.isOn) {
/* 449 */       Trace.exit(this, "com.ibm.mq.jms.MQRoot", "getFloatProperty(String)", 
/* 450 */           Float.valueOf(traceRet1));
/*     */     }
/* 452 */     return traceRet1;
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
/*     */   public int getIntProperty(String name) throws JMSException {
/* 464 */     if (Trace.isOn) {
/* 465 */       Trace.entry(this, "com.ibm.mq.jms.MQRoot", "getIntProperty(String)", new Object[] { name });
/*     */     }
/* 467 */     int traceRet1 = this.delegate.getIntProperty(name);
/* 468 */     if (Trace.isOn) {
/* 469 */       Trace.exit(this, "com.ibm.mq.jms.MQRoot", "getIntProperty(String)", 
/* 470 */           Integer.valueOf(traceRet1));
/*     */     }
/* 472 */     return traceRet1;
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
/*     */   public long getLongProperty(String name) throws JMSException {
/* 484 */     if (Trace.isOn) {
/* 485 */       Trace.entry(this, "com.ibm.mq.jms.MQRoot", "getLongProperty(String)", new Object[] { name });
/*     */     }
/* 487 */     long traceRet1 = this.delegate.getLongProperty(name);
/* 488 */     if (Trace.isOn) {
/* 489 */       Trace.exit(this, "com.ibm.mq.jms.MQRoot", "getLongProperty(String)", Long.valueOf(traceRet1));
/*     */     }
/* 491 */     return traceRet1;
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
/*     */   public Object getObjectProperty(String name) throws JMSException {
/* 503 */     if (Trace.isOn) {
/* 504 */       Trace.entry(this, "com.ibm.mq.jms.MQRoot", "getObjectProperty(String)", new Object[] { name });
/*     */     }
/* 506 */     Object traceRet1 = this.delegate.getObjectProperty(name);
/* 507 */     if (Trace.isOn) {
/* 508 */       Trace.exit(this, "com.ibm.mq.jms.MQRoot", "getObjectProperty(String)", traceRet1);
/*     */     }
/* 510 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Enumeration<String> getPropertyNames() throws JMSException {
/* 521 */     Enumeration<String> traceRet1 = this.delegate.getPropertyNames();
/* 522 */     if (Trace.isOn) {
/* 523 */       Trace.data(this, "com.ibm.mq.jms.MQRoot", "getPropertyNames()", "getter", traceRet1);
/*     */     }
/* 525 */     return traceRet1;
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
/*     */   public short getShortProperty(String name) throws JMSException {
/* 537 */     if (Trace.isOn) {
/* 538 */       Trace.entry(this, "com.ibm.mq.jms.MQRoot", "getShortProperty(String)", new Object[] { name });
/*     */     }
/* 540 */     short traceRet1 = this.delegate.getShortProperty(name);
/* 541 */     if (Trace.isOn) {
/* 542 */       Trace.exit(this, "com.ibm.mq.jms.MQRoot", "getShortProperty(String)", 
/* 543 */           Short.valueOf(traceRet1));
/*     */     }
/* 545 */     return traceRet1;
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
/*     */   public String getStringProperty(String name) throws JMSException {
/* 558 */     if (Trace.isOn) {
/* 559 */       Trace.entry(this, "com.ibm.mq.jms.MQRoot", "getStringProperty(String)", new Object[] { name });
/*     */     }
/* 561 */     String traceRet1 = this.delegate.getStringProperty(name);
/* 562 */     if (Trace.isOn) {
/* 563 */       Trace.exit(this, "com.ibm.mq.jms.MQRoot", "getStringProperty(String)", traceRet1);
/*     */     }
/* 565 */     return traceRet1;
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
/*     */   public boolean propertyExists(String name) throws JMSException {
/* 577 */     if (Trace.isOn) {
/* 578 */       Trace.entry(this, "com.ibm.mq.jms.MQRoot", "propertyExists(String)", new Object[] { name });
/*     */     }
/* 580 */     boolean traceRet1 = this.delegate.propertyExists(name);
/* 581 */     if (Trace.isOn) {
/* 582 */       Trace.exit(this, "com.ibm.mq.jms.MQRoot", "propertyExists(String)", 
/* 583 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 585 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 595 */     if (Trace.isOn) {
/* 596 */       Trace.entry(this, "com.ibm.mq.jms.MQRoot", "clear()");
/*     */     }
/* 598 */     this.delegate.clear();
/* 599 */     if (Trace.isOn) {
/* 600 */       Trace.exit(this, "com.ibm.mq.jms.MQRoot", "clear()");
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
/*     */   public boolean containsKey(Object key) {
/* 615 */     if (Trace.isOn) {
/* 616 */       Trace.entry(this, "com.ibm.mq.jms.MQRoot", "containsKey(Object)", new Object[] { key });
/*     */     }
/* 618 */     boolean traceRet1 = this.delegate.containsKey(key);
/* 619 */     if (Trace.isOn) {
/* 620 */       Trace.exit(this, "com.ibm.mq.jms.MQRoot", "containsKey(Object)", Boolean.valueOf(traceRet1));
/*     */     }
/* 622 */     return traceRet1;
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
/*     */   public boolean containsValue(Object value) {
/* 635 */     if (Trace.isOn) {
/* 636 */       Trace.entry(this, "com.ibm.mq.jms.MQRoot", "containsValue(Object)", new Object[] { value });
/*     */     }
/* 638 */     boolean traceRet1 = this.delegate.containsValue(value);
/* 639 */     if (Trace.isOn) {
/* 640 */       Trace.exit(this, "com.ibm.mq.jms.MQRoot", "containsValue(Object)", 
/* 641 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 643 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<Map.Entry<String, Object>> entrySet() {
/* 654 */     if (Trace.isOn) {
/* 655 */       Trace.entry(this, "com.ibm.mq.jms.MQRoot", "entrySet()");
/*     */     }
/* 657 */     Set<Map.Entry<String, Object>> traceRet1 = this.delegate.entrySet();
/* 658 */     if (Trace.isOn) {
/* 659 */       Trace.exit(this, "com.ibm.mq.jms.MQRoot", "entrySet()", traceRet1);
/*     */     }
/* 661 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object get(Object key) {
/* 672 */     if (Trace.isOn) {
/* 673 */       Trace.entry(this, "com.ibm.mq.jms.MQRoot", "get(Object)", new Object[] { key });
/*     */     }
/* 675 */     Object traceRet1 = this.delegate.get(key);
/* 676 */     if (Trace.isOn) {
/* 677 */       Trace.exit(this, "com.ibm.mq.jms.MQRoot", "get(Object)", traceRet1);
/*     */     }
/* 679 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 690 */     boolean traceRet1 = this.delegate.isEmpty();
/* 691 */     if (Trace.isOn) {
/* 692 */       Trace.data(this, "com.ibm.mq.jms.MQRoot", "isEmpty()", "getter", Boolean.valueOf(traceRet1));
/*     */     }
/* 694 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<String> keySet() {
/* 705 */     if (Trace.isOn) {
/* 706 */       Trace.entry(this, "com.ibm.mq.jms.MQRoot", "keySet()");
/*     */     }
/* 708 */     Set<String> traceRet1 = this.delegate.keySet();
/* 709 */     if (Trace.isOn) {
/* 710 */       Trace.exit(this, "com.ibm.mq.jms.MQRoot", "keySet()", traceRet1);
/*     */     }
/* 712 */     return traceRet1;
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
/*     */   public Object put(String key, Object value) {
/* 724 */     if (Trace.isOn) {
/* 725 */       Trace.entry(this, "com.ibm.mq.jms.MQRoot", "put(String,Object)", new Object[] { key, value });
/*     */     }
/* 727 */     Object traceRet1 = this.delegate.put(key, value);
/* 728 */     if (Trace.isOn) {
/* 729 */       Trace.exit(this, "com.ibm.mq.jms.MQRoot", "put(String,Object)", traceRet1);
/*     */     }
/* 731 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void putAll(Map<? extends String, ? extends Object> properties) {
/* 742 */     if (Trace.isOn) {
/* 743 */       Trace.entry(this, "com.ibm.mq.jms.MQRoot", "putAll(Map<? extends String , ? extends Object>)", new Object[] { properties });
/*     */     }
/*     */     
/* 746 */     this.delegate.putAll(properties);
/* 747 */     if (Trace.isOn) {
/* 748 */       Trace.exit(this, "com.ibm.mq.jms.MQRoot", "putAll(Map<? extends String , ? extends Object>)");
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
/*     */   public Object remove(Object key) {
/* 761 */     if (Trace.isOn) {
/* 762 */       Trace.entry(this, "com.ibm.mq.jms.MQRoot", "remove(Object)", new Object[] { key });
/*     */     }
/* 764 */     Object traceRet1 = this.delegate.remove(key);
/* 765 */     if (Trace.isOn) {
/* 766 */       Trace.exit(this, "com.ibm.mq.jms.MQRoot", "remove(Object)", traceRet1);
/*     */     }
/* 768 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 779 */     if (Trace.isOn) {
/* 780 */       Trace.entry(this, "com.ibm.mq.jms.MQRoot", "size()");
/*     */     }
/* 782 */     int traceRet1 = this.delegate.size();
/* 783 */     if (Trace.isOn) {
/* 784 */       Trace.exit(this, "com.ibm.mq.jms.MQRoot", "size()", Integer.valueOf(traceRet1));
/*     */     }
/* 786 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<Object> values() {
/* 797 */     if (Trace.isOn) {
/* 798 */       Trace.entry(this, "com.ibm.mq.jms.MQRoot", "values()");
/*     */     }
/* 800 */     Collection<Object> traceRet1 = this.delegate.values();
/* 801 */     if (Trace.isOn) {
/* 802 */       Trace.exit(this, "com.ibm.mq.jms.MQRoot", "values()", traceRet1);
/*     */     }
/* 804 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 813 */     if (Trace.isOn) {
/* 814 */       Trace.entry(this, "com.ibm.mq.jms.MQRoot", "equals(Object)", new Object[] { o });
/*     */     }
/* 816 */     if (o instanceof MQRoot) {
/* 817 */       boolean traceRet1 = this.delegate.equals(((MQRoot)o).delegate);
/* 818 */       if (Trace.isOn) {
/* 819 */         Trace.exit(this, "com.ibm.mq.jms.MQRoot", "equals(Object)", Boolean.valueOf(traceRet1), 1);
/*     */       }
/* 821 */       return traceRet1;
/*     */     } 
/*     */     
/* 824 */     if (Trace.isOn) {
/* 825 */       Trace.exit(this, "com.ibm.mq.jms.MQRoot", "equals(Object)", Boolean.valueOf(false), 2);
/*     */     }
/* 827 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 836 */     if (Trace.isOn) {
/* 837 */       Trace.entry(this, "com.ibm.mq.jms.MQRoot", "hashCode()");
/*     */     }
/* 839 */     if (this.delegate == null) {
/* 840 */       int traceRet1 = System.identityHashCode(this);
/* 841 */       if (Trace.isOn) {
/* 842 */         Trace.exit(this, "com.ibm.mq.jms.MQRoot", "hashCode()", Integer.valueOf(traceRet1), 1);
/*     */       }
/* 844 */       return traceRet1;
/*     */     } 
/* 846 */     int traceRet2 = this.delegate.hashCode();
/* 847 */     if (Trace.isOn) {
/* 848 */       Trace.exit(this, "com.ibm.mq.jms.MQRoot", "hashCode()", Integer.valueOf(traceRet2), 2);
/*     */     }
/* 850 */     return traceRet2;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQRoot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */