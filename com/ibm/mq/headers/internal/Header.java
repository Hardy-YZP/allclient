/*      */ package com.ibm.mq.headers.internal;
/*      */ 
/*      */ import com.ibm.mq.headers.MQDataException;
/*      */ import com.ibm.mq.headers.MQHeader;
/*      */ import com.ibm.mq.headers.internal.store.ByteStore;
/*      */ import com.ibm.mq.headers.internal.store.Store;
/*      */ import com.ibm.mq.headers.internal.validator.MQHeaderValidationException;
/*      */ import com.ibm.mq.internal.MQCommonServices;
/*      */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*      */ import com.ibm.mq.jmqi.JmqiObject;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.io.DataInput;
/*      */ import java.io.DataOutput;
/*      */ import java.io.IOException;
/*      */ import java.util.AbstractList;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.NoSuchElementException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class Header
/*      */   extends JmqiObject
/*      */   implements MQHeader
/*      */ {
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/Header.java";
/*      */   public static final int DEFAULT_ENCODING = 273;
/*      */   public static final int DEFAULT_CCSID = 1208;
/*      */   protected HeaderType type;
/*      */   private Store store;
/*      */   public Header delegate;
/*      */   ThreadLocal<Boolean> toStringInFlight;
/*      */   
/*      */   static {
/*  123 */     if (Trace.isOn) {
/*  124 */       Trace.data("com.ibm.mq.headers.internal.Header", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/Header.java");
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
/*      */ 
/*      */ 
/*      */   
/*      */   public static JmqiEnvironment getJmqiEnv() {
/*  151 */     if (Trace.isOn) {
/*  152 */       Trace.data("com.ibm.mq.headers.internal.Header", "getJmqiEnv()", "getter", MQCommonServices.jmqiEnv);
/*      */     }
/*      */     
/*  155 */     return MQCommonServices.jmqiEnv;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Header(HeaderType type) {
/*  164 */     this(type, DefaultStore.INSTANCE);
/*  165 */     if (Trace.isOn) {
/*  166 */       Trace.entry(this, "com.ibm.mq.headers.internal.Header", "<init>(HeaderType)", new Object[] { type });
/*      */     }
/*      */     
/*  169 */     if (Trace.isOn) {
/*  170 */       Trace.exit(this, "com.ibm.mq.headers.internal.Header", "<init>(HeaderType)");
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
/*      */   protected Header(HeaderType type, Store store) {
/*  182 */     super(getJmqiEnv());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1182 */     this.toStringInFlight = new ThreadLocal<Boolean>()
/*      */       {
/*      */         protected Boolean initialValue()
/*      */         {
/* 1186 */           if (Trace.isOn) {
/* 1187 */             Trace.exit(this, "com.ibm.mq.headers.internal.Header", "ThreadLocal<Boolean>()", Boolean.FALSE);
/*      */           }
/*      */           
/* 1190 */           return Boolean.FALSE;
/*      */         }
/*      */       }; if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "<init>(HeaderType,Store)", new Object[] { type, store });  this.type = type.apply(this); store(store); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "<init>(HeaderType,Store)"); 
/*      */   }
/*      */   public final Store store() { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "store()");  Store traceRet1 = (this.delegate != null) ? this.delegate.store() : this.store; if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "store()", traceRet1);  return traceRet1; }
/*      */   @Deprecated public final void store(Store newStore) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "store(Store)", new Object[] { newStore });  this.store = newStore; if (this.delegate != null) this.delegate.store(this.store);  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "store(Store)");  }
/*      */   protected final Store store(int newSize) throws IOException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "store(int)", new Object[] { Integer.valueOf(newSize) });  store(store().allocate(this, newSize)); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "store(int)", this.store);  return this.store; }
/*      */   protected final Store store(int offset, int oldSize, int newSize) throws IOException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "store(int,int,int)", new Object[] { Integer.valueOf(offset), Integer.valueOf(oldSize), Integer.valueOf(newSize) });  store(store().allocate(this, offset, oldSize, newSize)); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "store(int,int,int)", this.store);  return this.store; }
/*      */   private HeaderField getField(String name) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "getField(String)", new Object[] { name });  int index = name.indexOf('.'); if (index >= 0) { UnsupportedOperationException traceRet1 = new UnsupportedOperationException(HeaderMessages.getMessage("MQHDR0005")); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.headers.internal.Header", "getField(String)", traceRet1);  throw traceRet1; }  HeaderField traceRet2 = this.type.getField(name); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "getField(String)", traceRet2);  return traceRet2; }
/* 1199 */   final Header newInstance(Class<?> headerClass, HeaderType newType, Store newStore) throws InstantiationException, IllegalAccessException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "newInstance(Class<?>,HeaderType,Store)", new Object[] { headerClass, newType, newStore });  Header header = (Header)headerClass.newInstance(); header.type = newType; header.store(newStore); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "newInstance(Class<?>,HeaderType,Store)", header);  return header; } protected final Object getValue(HeaderField field) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "getValue(HeaderField)", new Object[] { field });  if (!field.isPresent(this)) { if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "getValue(HeaderField)", null, 1);  return null; }  Object traceRet1 = field.getValue(this); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "getValue(HeaderField)", traceRet1, 2);  return traceRet1; } protected final void setValue(HeaderField field, Object value) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "setValue(HeaderField,Object)", new Object[] { field, value });  checkForSet(field); field.setValue(this, value); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "setValue(HeaderField,Object)");  } public char getCharValue(HeaderField field) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "getCharValue(HeaderField)", new Object[] { field });  String value = getStringValue(field); char traceRet1 = (value == null || value.length() == 0) ? Character.MIN_VALUE : value.charAt(0); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "getCharValue(HeaderField)", Character.valueOf(traceRet1));  return traceRet1; } public void setCharValue(HeaderField field, char value) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "setCharValue(HeaderField,char)", new Object[] { field, Character.valueOf(value) });  setStringValue(field, new String(new char[] { value })); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "setCharValue(HeaderField,char)");  } protected final int getIntValue(HeaderField field) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "getIntValue(HeaderField)", new Object[] { field });  checkForGet(field); int traceRet1 = ((MQLongField)this.type.getField(field.getIndex())).getIntValue(this); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "getIntValue(HeaderField)", Integer.valueOf(traceRet1));  return traceRet1; } public String toString() { if (Trace.isOn) {
/* 1200 */       Trace.entry(this, "com.ibm.mq.headers.internal.Header", "toString()");
/*      */     }
/* 1202 */     boolean inFlight = ((Boolean)this.toStringInFlight.get()).booleanValue();
/* 1203 */     if (!inFlight) {
/* 1204 */       this.toStringInFlight.set(Boolean.TRUE);
/* 1205 */       StringBuffer sb = new StringBuffer(type() + " (" + store() + ")");
/* 1206 */       if (fields() != null) {
/* 1207 */         Iterator<?> it = fields().iterator();
/*      */         
/* 1209 */         while (it.hasNext()) {
/* 1210 */           sb.append("\n\t");
/* 1211 */           sb.append(toString((MQHeader.Field)it.next()));
/*      */         } 
/*      */       } 
/* 1214 */       this.toStringInFlight.set(Boolean.FALSE);
/* 1215 */       String traceRet1 = new String(sb);
/* 1216 */       if (Trace.isOn) {
/* 1217 */         Trace.exit(this, "com.ibm.mq.headers.internal.Header", "toString()", traceRet1, 1);
/*      */       }
/* 1219 */       return traceRet1;
/*      */     } 
/* 1221 */     String traceRet2 = "(this Header)";
/* 1222 */     if (Trace.isOn) {
/* 1223 */       Trace.exit(this, "com.ibm.mq.headers.internal.Header", "toString()", traceRet2, 2);
/*      */     }
/* 1225 */     return traceRet2; }
/*      */   protected final void setIntValue(HeaderField field, int value) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "setIntValue(HeaderField,int)", new Object[] { field, Integer.valueOf(value) });  checkForSet(field); ((MQLongField)field).setIntValue(this, value); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "setIntValue(HeaderField,int)");  }
/*      */   protected final long getInt64Value(HeaderField field) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "getInt64Value(HeaderField)", new Object[] { field });  checkForGet(field); long traceRet1 = ((MQInt64Field)field).getLongValue(this); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "getInt64Value(HeaderField)", Long.valueOf(traceRet1));  return traceRet1; }
/*      */   protected final void setInt64Value(HeaderField field, long value) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "setInt64Value(HeaderField,long)", new Object[] { field, Long.valueOf(value) });  checkForSet(field); ((MQInt64Field)field).setLongValue(this, value); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "setInt64Value(HeaderField,long)");  }
/*      */   protected final String getStringValue(HeaderField field) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "getStringValue(HeaderField)", new Object[] { field });  checkForGet(field); String traceRet1 = trimNulls((String)field.getValue(this)); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "getStringValue(HeaderField)", traceRet1);  return traceRet1; }
/*      */   protected final void setStringValue(HeaderField field, String value) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "setStringValue(HeaderField,String)", new Object[] { field, value });  checkForSet(field); ((MQCharField)field).setValue(this, value); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "setStringValue(HeaderField,String)");  }
/*      */   protected final byte[] getBytesValue(HeaderField field) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "getBytesValue(HeaderField)", new Object[] { field });  checkForGet(field); byte[] traceRet1 = (byte[])field.getValue(this); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "getBytesValue(HeaderField)", traceRet1);  return traceRet1; }
/*      */   protected final void setBytesValue(HeaderField field, byte[] value) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "setBytesValue(HeaderField,byte [ ])", new Object[] { field, value });  checkForSet(field); ((MQByteField)field).setValue(this, value); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "setBytesValue(HeaderField,byte [ ])");  }
/*      */   protected final int[] getIntListValue(HeaderField field) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "getIntListValue(HeaderField)", new Object[] { field });  checkForGet(field); int[] traceRet1 = (int[])field.getValue(this); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "getIntListValue(HeaderField)", traceRet1);  return traceRet1; }
/* 1234 */   protected final void setIntListValue(HeaderField field, int[] value) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "setIntListValue(HeaderField,int [ ])", new Object[] { field, value });  checkForSet(field); ((MQLongArrayField)field).setValue(this, value); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "setIntListValue(HeaderField,int [ ])");  } protected final long[] getInt64ListValue(HeaderField field) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "getInt64ListValue(HeaderField)", new Object[] { field });  checkForGet(field); long[] traceRet1 = (long[])field.getValue(this); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "getInt64ListValue(HeaderField)", traceRet1);  return traceRet1; } protected final void setInt64ListValue(HeaderField field, long[] value) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "setInt64ListValue(HeaderField,long [ ])", new Object[] { field, value });  checkForSet(field); ((MQInt64ArrayField)field).setValue(this, value); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "setInt64ListValue(HeaderField,long [ ])");  } private final void checkForGet(HeaderField field) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "checkForGet(HeaderField)", new Object[] { field });  if (!field.isPresent(this)) { NoSuchElementException traceRet1 = new NoSuchElementException(HeaderMessages.getMessage("MQHDR0006", new Object[] { field.name() })); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.headers.internal.Header", "checkForGet(HeaderField)", traceRet1);  throw traceRet1; }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "checkForGet(HeaderField)");  } private final void checkForSet(HeaderField field) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "checkForSet(HeaderField)", new Object[] { field });  if (!field.isPresent(this)) field.setPresent(this);  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "checkForSet(HeaderField)");  } public final void validate() throws MQDataException, IOException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "validate()");  try { this.type.validate(this); } catch (MQHeaderValidationException e) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.mq.headers.internal.Header", "validate()", (Throwable)e, 1);  MQDataException traceRet1 = new MQDataException(1, 2142, e); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.headers.internal.Header", "validate()", (Throwable)traceRet1, 1);  throw traceRet1; } catch (Exception e) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.mq.headers.internal.Header", "validate()", e, 2);  MQDataException traceRet2 = MQDataException.getMQDataException(e); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.headers.internal.Header", "validate()", (Throwable)traceRet2, 2);  throw traceRet2; }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "validate()");  } public final Object getValue(String name) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "getValue(String)", new Object[] { name });  Object traceRet1 = getValue(getField(name)); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "getValue(String)", traceRet1);  return traceRet1; } public final void setValue(String name, Object value) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "setValue(String,Object)", new Object[] { name, value });  setValue(getField(name), value); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "setValue(String,Object)");  } public final char getCharValue(String name) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "getCharValue(String)", new Object[] { name });  char traceRet1 = getCharValue(getField(name)); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "getCharValue(String)", Character.valueOf(traceRet1));  return traceRet1; } public final void setCharValue(String name, char value) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "setCharValue(String,char)", new Object[] { name, Character.valueOf(value) });  setCharValue(getField(name), value); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "setCharValue(String,char)");  } public final int getIntValue(String name) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "getIntValue(String)", new Object[] { name });  int traceRet1 = getIntValue(getField(name)); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "getIntValue(String)", Integer.valueOf(traceRet1));  return traceRet1; } public final void setIntValue(String name, int value) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "setIntValue(String,int)", new Object[] { name, Integer.valueOf(value) });  setIntValue(getField(name), value); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "setIntValue(String,int)");  } public final String getStringValue(String name) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "getStringValue(String)", new Object[] { name });  String traceRet1 = getStringValue(getField(name)); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "getStringValue(String)", traceRet1);  return traceRet1; } public final void setStringValue(String name, String value) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "setStringValue(String,String)", new Object[] { name, value });  setStringValue(getField(name), value); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "setStringValue(String,String)");  } public final byte[] getBytesValue(String name) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "getBytesValue(String)", new Object[] { name });  byte[] traceRet1 = getBytesValue(getField(name)); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "getBytesValue(String)", traceRet1);  return traceRet1; } protected String toString(MQHeader.Field field) { if (Trace.isOn) {
/* 1235 */       Trace.entry(this, "com.ibm.mq.headers.internal.Header", "toString(Field)", new Object[] { field });
/*      */     }
/*      */     
/* 1238 */     Object value = field.getValue();
/*      */     
/* 1240 */     if (value != null) {
/* 1241 */       if (value instanceof Integer) {
/* 1242 */         value = value.toString() + " (0x" + HexString.hexString(((Integer)value).intValue()) + ")";
/*      */       }
/* 1244 */       else if (value instanceof Long) {
/* 1245 */         value = value.toString() + " (0x" + HexString.hexString(((Long)value).longValue()) + ")";
/*      */       }
/* 1247 */       else if (value instanceof String) {
/* 1248 */         value = '"' + value.toString() + '"';
/*      */       }
/* 1250 */       else if (value instanceof byte[]) {
/* 1251 */         value = "0x" + HexString.hexString((byte[])value);
/*      */       }
/* 1253 */       else if (value instanceof int[]) {
/* 1254 */         StringBuffer inner = new StringBuffer();
/*      */         
/* 1256 */         inner.append('{');
/*      */         
/* 1258 */         int[] array = (int[])value;
/*      */         
/* 1260 */         for (int i = 0; i < array.length; i++) {
/* 1261 */           inner.append(Integer.toString(array[i]));
/* 1262 */           inner.append(", ");
/*      */         } 
/*      */         
/* 1265 */         if (array.length > 0) {
/* 1266 */           inner.setLength(inner.length() - ", ".length());
/*      */         }
/*      */         
/* 1269 */         inner.append('}');
/* 1270 */         value = new String(inner);
/*      */       }
/* 1272 */       else if (value instanceof long[]) {
/* 1273 */         StringBuffer inner = new StringBuffer();
/*      */         
/* 1275 */         inner.append('{');
/*      */         
/* 1277 */         long[] array = (long[])value;
/*      */         
/* 1279 */         for (int i = 0; i < array.length; i++) {
/* 1280 */           inner.append(Long.toString(array[i]));
/* 1281 */           inner.append(", ");
/*      */         } 
/*      */         
/* 1284 */         if (array.length > 0) {
/* 1285 */           inner.setLength(inner.length() - ", ".length());
/*      */         }
/*      */         
/* 1288 */         inner.append('}');
/* 1289 */         value = new String(inner);
/*      */       }
/* 1291 */       else if (value instanceof String[]) {
/* 1292 */         StringBuffer inner = new StringBuffer();
/*      */         
/* 1294 */         inner.append('{');
/*      */         
/* 1296 */         String[] array = (String[])value;
/*      */         
/* 1298 */         for (int i = 0; i < array.length; i++) {
/* 1299 */           inner.append('"');
/* 1300 */           inner.append(array[i]);
/* 1301 */           inner.append('"');
/* 1302 */           inner.append(", ");
/*      */         } 
/*      */         
/* 1305 */         if (array.length > 0) {
/* 1306 */           inner.setLength(inner.length() - ", ".length());
/*      */         }
/*      */         
/* 1309 */         inner.append('}');
/* 1310 */         value = new String(inner);
/*      */       } 
/*      */     }
/*      */     
/* 1314 */     String traceRet1 = field.getType() + " " + field.getName() + ": " + value;
/* 1315 */     if (Trace.isOn) {
/* 1316 */       Trace.exit(this, "com.ibm.mq.headers.internal.Header", "toString(Field)", traceRet1);
/*      */     }
/* 1318 */     return traceRet1; } public final void setBytesValue(String name, byte[] value) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "setBytesValue(String,byte [ ])", new Object[] { name, value });  setBytesValue(getField(name), value); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "setBytesValue(String,byte [ ])");  } public final HeaderType headerType() { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "headerType()");  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "headerType()", this.type);  return this.type; } public String type() { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "type()");  HeaderType type = headerType(); String traceRet1 = (type == null) ? "*Undefined*" : type.name; if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "type()", traceRet1);  return traceRet1; }
/*      */   public int read(DataInput message) throws MQDataException, IOException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "read(DataInput)", new Object[] { message });  try { int traceRet1 = read(message, true); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "read(DataInput)", Integer.valueOf(traceRet1));  return traceRet1; } catch (Exception e) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.mq.headers.internal.Header", "read(DataInput)", e);  MQDataException traceRet2 = MQDataException.getMQDataException(e); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.headers.internal.Header", "read(DataInput)", (Throwable)traceRet2);  throw traceRet2; }  }
/*      */   public int read(DataInput message, boolean copy) throws Exception, MQDataException, IOException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "read(DataInput,boolean)", new Object[] { message, Boolean.valueOf(copy) });  MessageWrapper wrappedMessage = MessageWrapper.wrap(message); int size = read(message, wrappedMessage.getEncoding(), wrappedMessage.getCharacterSet()); if (copy) { byte[] bytes = new byte[size]; Store localStore = store(); localStore.copyTo(bytes, 0, size); store((Store)new ByteStore(bytes, localStore.encoding(), localStore.characterSet())); }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "read(DataInput,boolean)", Integer.valueOf(size));  return size; }
/*      */   public int read(DataInput message, int encoding, int characterSet) throws Exception, MQDataException, IOException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "read(DataInput,int,int)", new Object[] { message, Integer.valueOf(encoding), Integer.valueOf(characterSet) });  int size = ((this.delegate == null) ? this.type : this.delegate.type).getFixedSize(); MessageWrapper wrappedMessage = MessageWrapper.wrap(message); store(wrappedMessage.getStore(encoding, characterSet, size)); if (this.type.isVariableSize()) { store().readFrom(wrappedMessage, size, size() - size); size = size(); }  validate(); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "read(DataInput,int,int)", Integer.valueOf(size));  return size; }
/*      */   public int write(DataOutput message) throws IOException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "write(DataOutput)", new Object[] { message });  MessageWrapper wrappedMessage = MessageWrapper.wrap(message); int traceRet1 = write(message, wrappedMessage.getEncoding(), wrappedMessage.getCharacterSet()); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "write(DataOutput)", Integer.valueOf(traceRet1));  return traceRet1; }
/*      */   public int write(DataOutput messageP, int encoding, int characterSet) throws IOException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "write(DataOutput,int,int)", new Object[] { messageP, Integer.valueOf(encoding), Integer.valueOf(characterSet) });  DataOutput message = messageP; Store localStore = store(); if (localStore.hasData() && localStore.matchesEncoding(encoding) && localStore.characterSet() == characterSet) { int i = size(); localStore.writeTo(message, 0, i); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "write(DataOutput,int,int)", Integer.valueOf(i), 1);  return i; }  MessageWrapper wrappedMessage = MessageWrapper.wrap(message); if (!store().matchesEncoding(encoding)) message = wrappedMessage.getReversed();  Iterator<?> it = this.type.getFields().iterator(); int size = 0; while (it.hasNext()) { HeaderField field = (HeaderField)it.next(); if (field.isPresent(this)) size += field.write(this, message, encoding, characterSet);  }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "write(DataOutput,int,int)", Integer.valueOf(size), 2);  return size; }
/*      */   public int size() { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "size()");  int traceRet1 = this.type.size(this); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "size()", Integer.valueOf(traceRet1));  return traceRet1; }
/*      */   public int encoding() { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "encoding()");  int traceRet1 = store().encoding(); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "encoding()", Integer.valueOf(traceRet1));  return traceRet1; }
/*      */   public int characterSet() { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "characterSet()");  int traceRet1 = store().characterSet(); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "characterSet()", Integer.valueOf(traceRet1));  return traceRet1; }
/*      */   public List<?> fields() { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.internal.Header", "fields()");  List<?> traceRet1 = (this.type != null) ? new FieldList(this.type.getFields()) : null; if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.internal.Header", "fields()", traceRet1);  return traceRet1; }
/* 1328 */   protected String trimNulls(String s) { if (Trace.isOn) {
/* 1329 */       Trace.entry(this, "com.ibm.mq.headers.internal.Header", "trimNulls(String)", new Object[] { s });
/*      */     }
/*      */ 
/*      */     
/* 1333 */     if (s == null) {
/* 1334 */       if (Trace.isOn) {
/* 1335 */         Trace.exit(this, "com.ibm.mq.headers.internal.Header", "trimNulls(String)", null, 0);
/*      */       }
/* 1337 */       return null;
/*      */     } 
/*      */     
/* 1340 */     int nonNullLength = s.length();
/*      */     
/* 1342 */     while (nonNullLength > 0 && s.charAt(nonNullLength - 1) == '\000') {
/* 1343 */       nonNullLength--;
/*      */     }
/*      */     
/* 1346 */     String result = (nonNullLength != s.length()) ? s.substring(0, nonNullLength) : s;
/*      */     
/* 1348 */     if (Trace.isOn) {
/* 1349 */       Trace.exit(this, "com.ibm.mq.headers.internal.Header", "trimNulls(String)", result, 1);
/*      */     }
/* 1351 */     return result; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String[] trimNulls(String[] s) {
/* 1360 */     if (Trace.isOn) {
/* 1361 */       Trace.entry(this, "com.ibm.mq.headers.internal.Header", "trimNulls(String[])", new Object[] { s });
/*      */     }
/*      */ 
/*      */     
/* 1365 */     if (s == null) {
/* 1366 */       if (Trace.isOn) {
/* 1367 */         Trace.exit(this, "com.ibm.mq.headers.internal.Header", "trimNulls(String[])", null, 0);
/*      */       }
/* 1369 */       return null;
/*      */     } 
/*      */     
/* 1372 */     for (int i = 0; i < s.length; i++) {
/* 1373 */       s[i] = trimNulls(s[i]);
/*      */     }
/*      */     
/* 1376 */     if (Trace.isOn) {
/* 1377 */       Trace.exit(this, "com.ibm.mq.headers.internal.Header", "trimNulls(String)", s);
/*      */     }
/*      */     
/* 1380 */     return s;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final class FieldList
/*      */     extends AbstractList<Object>
/*      */   {
/*      */     private final List<?> fields;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private FieldList(List<?> fields) {
/* 1395 */       if (Trace.isOn) {
/* 1396 */         Trace.entry(this, "com.ibm.mq.headers.internal.FieldList", "<init>(List<?>)", new Object[] { fields });
/*      */       }
/*      */       
/* 1399 */       this.fields = fields;
/* 1400 */       if (Trace.isOn) {
/* 1401 */         Trace.exit(this, "com.ibm.mq.headers.internal.FieldList", "<init>(List<?>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int size() {
/* 1411 */       if (Trace.isOn) {
/* 1412 */         Trace.entry(this, "com.ibm.mq.headers.internal.FieldList", "size()");
/*      */       }
/* 1414 */       int traceRet1 = this.fields.size();
/* 1415 */       if (Trace.isOn) {
/* 1416 */         Trace.exit(this, "com.ibm.mq.headers.internal.FieldList", "size()", 
/* 1417 */             Integer.valueOf(traceRet1));
/*      */       }
/* 1419 */       return traceRet1;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Object get(int index) {
/* 1427 */       if (Trace.isOn)
/* 1428 */         Trace.entry(this, "com.ibm.mq.headers.internal.FieldList", "get(int)", new Object[] {
/* 1429 */               Integer.valueOf(index)
/*      */             }); 
/* 1431 */       final HeaderField field = (HeaderField)this.fields.get(index);
/*      */       
/* 1433 */       Object traceRet1 = new MQHeader.Field()
/*      */         {
/*      */           public String getName()
/*      */           {
/* 1437 */             if (Trace.isOn) {
/* 1438 */               Trace.entry(this, "com.ibm.mq.headers.internal.FieldList", "getName()");
/*      */             }
/*      */             
/* 1441 */             String traceRet1 = field.name();
/* 1442 */             if (Trace.isOn) {
/* 1443 */               Trace.exit(this, "com.ibm.mq.headers.internal.null", "getName()", traceRet1);
/*      */             }
/* 1445 */             return traceRet1;
/*      */           }
/*      */ 
/*      */           
/*      */           public String getType() {
/* 1450 */             if (Trace.isOn) {
/* 1451 */               Trace.entry(this, "com.ibm.mq.headers.internal.FieldList", "getType()");
/*      */             }
/*      */             
/* 1454 */             String traceRet1 = field.type();
/* 1455 */             if (Trace.isOn) {
/* 1456 */               Trace.exit(this, "com.ibm.mq.headers.internal.null", "getType()", traceRet1);
/*      */             }
/* 1458 */             return traceRet1;
/*      */           }
/*      */ 
/*      */           
/*      */           public Object getValue() {
/* 1463 */             if (Trace.isOn) {
/* 1464 */               Trace.entry(this, "com.ibm.mq.headers.internal.FieldList", "getValue()");
/*      */             }
/*      */             
/* 1467 */             Object traceRet1 = Header.this.getValue(field);
/* 1468 */             if (Trace.isOn) {
/* 1469 */               Trace.exit(this, "com.ibm.mq.headers.internal.null", "getValue()", traceRet1);
/*      */             }
/* 1471 */             return traceRet1;
/*      */           }
/*      */ 
/*      */           
/*      */           public void setValue(Object value) {
/* 1476 */             if (Trace.isOn) {
/* 1477 */               Trace.entry(this, "com.ibm.mq.headers.internal.FieldList", "setValue(Object)", new Object[] { value });
/*      */             }
/*      */             
/* 1480 */             field.setValue(Header.this, value);
/* 1481 */             if (Trace.isOn) {
/* 1482 */               Trace.exit(this, "com.ibm.mq.headers.internal.null", "setValue(Object)");
/*      */             }
/*      */           }
/*      */         };
/*      */ 
/*      */       
/* 1488 */       if (Trace.isOn) {
/* 1489 */         Trace.exit(this, "com.ibm.mq.headers.internal.FieldList", "get(int)", traceRet1);
/*      */       }
/* 1491 */       return traceRet1;
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\internal\Header.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */