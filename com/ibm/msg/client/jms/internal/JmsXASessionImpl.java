/*      */ package com.ibm.msg.client.jms.internal;
/*      */ 
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.jms.JmsMessageReference;
/*      */ import com.ibm.msg.client.jms.JmsSession;
/*      */ import com.ibm.msg.client.jms.JmsXASession;
/*      */ import com.ibm.msg.client.provider.ProviderXASession;
/*      */ import java.io.IOException;
/*      */ import java.io.NotSerializableException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.Collection;
/*      */ import java.util.Enumeration;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import javax.jms.BytesMessage;
/*      */ import javax.jms.Destination;
/*      */ import javax.jms.IllegalStateException;
/*      */ import javax.jms.InvalidDestinationException;
/*      */ import javax.jms.InvalidSelectorException;
/*      */ import javax.jms.JMSException;
/*      */ import javax.jms.MapMessage;
/*      */ import javax.jms.Message;
/*      */ import javax.jms.MessageConsumer;
/*      */ import javax.jms.MessageListener;
/*      */ import javax.jms.MessageProducer;
/*      */ import javax.jms.ObjectMessage;
/*      */ import javax.jms.Queue;
/*      */ import javax.jms.QueueBrowser;
/*      */ import javax.jms.Session;
/*      */ import javax.jms.StreamMessage;
/*      */ import javax.jms.TemporaryQueue;
/*      */ import javax.jms.TemporaryTopic;
/*      */ import javax.jms.TextMessage;
/*      */ import javax.jms.Topic;
/*      */ import javax.jms.TopicSubscriber;
/*      */ import javax.transaction.xa.XAResource;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class JmsXASessionImpl
/*      */   extends JmsSessionImpl
/*      */   implements JmsXASession
/*      */ {
/*      */   private static final long serialVersionUID = 1373634542979416953L;
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsXASessionImpl.java";
/*      */   
/*      */   static {
/*   76 */     if (Trace.isOn) {
/*   77 */       Trace.data("com.ibm.msg.client.jms.internal.JmsXASessionImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsXASessionImpl.java");
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
/*      */   protected JmsXASessionImpl(JmsXAConnectionImpl connection) throws JMSException {
/*   94 */     super(true, 0, connection);
/*   95 */     if (Trace.isOn) {
/*   96 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "<init>(JmsXAConnectionImpl)", new Object[] { connection });
/*      */     }
/*      */ 
/*      */     
/*  100 */     if (Trace.isOn) {
/*  101 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "<init>(JmsXAConnectionImpl)");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Session getSession() throws JMSException {
/*  134 */     if (Trace.isOn) {
/*  135 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "getSession()");
/*      */     }
/*      */     
/*  138 */     JmsSession jmsSession = new JmsSession()
/*      */       {
/*      */         private static final long serialVersionUID = 1L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         private void writeObject(ObjectOutputStream out) throws IOException {
/*  147 */           if (Trace.isOn) {
/*  148 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "writeObject(ObjectOutputStream)", new Object[] { out });
/*      */           }
/*      */           
/*  151 */           NotSerializableException traceRet1 = new NotSerializableException("com.ibm.msg.client.jms.JmsXASession$JmsSession");
/*      */           
/*  153 */           if (Trace.isOn) {
/*  154 */             Trace.throwing(this, "com.ibm.msg.client.jms.internal.null", "writeObject(ObjectOutputStream)", traceRet1);
/*      */           }
/*      */           
/*  157 */           throw traceRet1;
/*      */         }
/*      */         
/*      */         private void readObject(ObjectInputStream in) throws IOException {
/*  161 */           if (Trace.isOn) {
/*  162 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "readObject(ObjectInputStream)", new Object[] { in });
/*      */           }
/*      */           
/*  165 */           NotSerializableException traceRet1 = new NotSerializableException("com.ibm.msg.client.jms.JmsXASession$JmsSession");
/*      */           
/*  167 */           if (Trace.isOn) {
/*  168 */             Trace.throwing(this, "com.ibm.msg.client.jms.internal.null", "readObject(ObjectInputStream)", traceRet1);
/*      */           }
/*      */           
/*  171 */           throw traceRet1;
/*      */         }
/*      */         
/*      */         public Message consume(byte[] flatMR) throws JMSException {
/*  175 */           if (Trace.isOn) {
/*  176 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "consume(byte [ ])", new Object[] { flatMR });
/*      */           }
/*      */           
/*  179 */           Message traceRet1 = JmsXASessionImpl.this.consume(flatMR);
/*  180 */           if (Trace.isOn) {
/*  181 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "consume(byte [ ])", traceRet1);
/*      */           }
/*      */           
/*  184 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public void deliver(List<JmsMessageReference> mrf) throws JMSException {
/*  188 */           if (Trace.isOn) {
/*  189 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "deliver(List<JmsMessageReference>)", new Object[] { mrf });
/*      */           }
/*      */           
/*  192 */           JmsXASessionImpl.this.deliver(mrf);
/*  193 */           if (Trace.isOn) {
/*  194 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "deliver(List<JmsMessageReference>)");
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public JmsMessageReference recreateMessageReference(byte[] flatMR) throws JMSException {
/*  201 */           if (Trace.isOn) {
/*  202 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "recreateMessageReference(byte [ ])", new Object[] { flatMR });
/*      */           }
/*      */           
/*  205 */           JmsMessageReference traceRet1 = JmsXASessionImpl.this.recreateMessageReference(flatMR);
/*  206 */           if (Trace.isOn) {
/*  207 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "recreateMessageReference(byte [ ])", traceRet1);
/*      */           }
/*      */           
/*  210 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public void setBatchProperties(Map<String, Object> properties) throws JMSException {
/*  214 */           if (Trace.isOn) {
/*  215 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "setBatchProperties(Map<String , Object>)", new Object[] { properties });
/*      */           }
/*      */           
/*  218 */           JmsXASessionImpl.this.setBatchProperties(properties);
/*      */           
/*  220 */           if (Trace.isOn) {
/*  221 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "setBatchProperties(Map<String , Object>)");
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public void setBooleanProperty(String name, boolean value) throws JMSException {
/*  228 */           if (Trace.isOn) {
/*  229 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "setBooleanProperty(String,boolean)", new Object[] { name, 
/*  230 */                   Boolean.valueOf(value) });
/*      */           }
/*  232 */           JmsXASessionImpl.this.setBooleanProperty(name, value);
/*      */           
/*  234 */           if (Trace.isOn) {
/*  235 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "setBooleanProperty(String,boolean)");
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public void setByteProperty(String name, byte value) throws JMSException {
/*  242 */           if (Trace.isOn) {
/*  243 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "setByteProperty(String,byte)", new Object[] { name, 
/*  244 */                   Byte.valueOf(value) });
/*      */           }
/*  246 */           JmsXASessionImpl.this.setByteProperty(name, value);
/*      */           
/*  248 */           if (Trace.isOn) {
/*  249 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "setByteProperty(String,byte)");
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public void setBytesProperty(String name, byte[] value) throws JMSException {
/*  256 */           if (Trace.isOn) {
/*  257 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "setBytesProperty(String,byte [ ])", new Object[] { name, value });
/*      */           }
/*      */           
/*  260 */           JmsXASessionImpl.this.setBytesProperty(name, value);
/*      */           
/*  262 */           if (Trace.isOn) {
/*  263 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "setBytesProperty(String,byte [ ])");
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public void setCharProperty(String name, char value) throws JMSException {
/*  270 */           if (Trace.isOn) {
/*  271 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "setCharProperty(String,char)", new Object[] { name, 
/*  272 */                   Character.valueOf(value) });
/*      */           }
/*  274 */           JmsXASessionImpl.this.setCharProperty(name, value);
/*      */           
/*  276 */           if (Trace.isOn) {
/*  277 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "setCharProperty(String,char)");
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public void setDoubleProperty(String name, double value) throws JMSException {
/*  284 */           if (Trace.isOn) {
/*  285 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "setDoubleProperty(String,double)", new Object[] { name, 
/*  286 */                   Double.valueOf(value) });
/*      */           }
/*  288 */           JmsXASessionImpl.this.setDoubleProperty(name, value);
/*      */           
/*  290 */           if (Trace.isOn) {
/*  291 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "setDoubleProperty(String,double)");
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public void setFloatProperty(String name, float value) throws JMSException {
/*  298 */           if (Trace.isOn) {
/*  299 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "setFloatProperty(String,float)", new Object[] { name, 
/*  300 */                   Float.valueOf(value) });
/*      */           }
/*  302 */           JmsXASessionImpl.this.setFloatProperty(name, value);
/*      */           
/*  304 */           if (Trace.isOn) {
/*  305 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "setFloatProperty(String,float)");
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public void setIntProperty(String name, int value) throws JMSException {
/*  312 */           if (Trace.isOn) {
/*  313 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "setIntProperty(String,int)", new Object[] { name, 
/*  314 */                   Integer.valueOf(value) });
/*      */           }
/*  316 */           JmsXASessionImpl.this.setIntProperty(name, value);
/*      */           
/*  318 */           if (Trace.isOn) {
/*  319 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "setIntProperty(String,int)");
/*      */           }
/*      */         }
/*      */ 
/*      */         
/*      */         public void setLongProperty(String name, long value) throws JMSException {
/*  325 */           if (Trace.isOn) {
/*  326 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "setLongProperty(String,long)", new Object[] { name, 
/*  327 */                   Long.valueOf(value) });
/*      */           }
/*  329 */           JmsXASessionImpl.this.setLongProperty(name, value);
/*      */           
/*  331 */           if (Trace.isOn) {
/*  332 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "setLongProperty(String,long)");
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public void setObjectProperty(String name, Object value) throws JMSException {
/*  339 */           if (Trace.isOn) {
/*  340 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "setObjectProperty(String,Object)", new Object[] { name, value });
/*      */           }
/*      */           
/*  343 */           JmsXASessionImpl.this.setObjectProperty(name, value);
/*      */           
/*  345 */           if (Trace.isOn) {
/*  346 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "setObjectProperty(String,Object)");
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public void setShortProperty(String name, short value) throws JMSException {
/*  353 */           if (Trace.isOn) {
/*  354 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "setShortProperty(String,short)", new Object[] { name, 
/*  355 */                   Short.valueOf(value) });
/*      */           }
/*  357 */           JmsXASessionImpl.this.setShortProperty(name, value);
/*      */           
/*  359 */           if (Trace.isOn) {
/*  360 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "setShortProperty(String,short)");
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public void setStringProperty(String name, String value) throws JMSException {
/*  367 */           if (Trace.isOn) {
/*  368 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "setStringProperty(String,String)", new Object[] { name, value });
/*      */           }
/*      */           
/*  371 */           JmsXASessionImpl.this.setStringProperty(name, value);
/*      */           
/*  373 */           if (Trace.isOn) {
/*  374 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "setStringProperty(String,String)");
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public boolean getBooleanProperty(String name) throws JMSException {
/*  381 */           if (Trace.isOn) {
/*  382 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "getBooleanProperty(String)", new Object[] { name });
/*      */           }
/*      */           
/*  385 */           boolean traceRet1 = JmsXASessionImpl.this.getBooleanProperty(name);
/*  386 */           if (Trace.isOn) {
/*  387 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getBooleanProperty(String)", 
/*  388 */                 Boolean.valueOf(traceRet1));
/*      */           }
/*  390 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public byte getByteProperty(String name) throws JMSException {
/*  394 */           if (Trace.isOn) {
/*  395 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "getByteProperty(String)", new Object[] { name });
/*      */           }
/*      */           
/*  398 */           byte traceRet1 = JmsXASessionImpl.this.getByteProperty(name);
/*  399 */           if (Trace.isOn) {
/*  400 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getByteProperty(String)", 
/*  401 */                 Byte.valueOf(traceRet1));
/*      */           }
/*  403 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public byte[] getBytesProperty(String name) throws JMSException {
/*  407 */           if (Trace.isOn) {
/*  408 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "getBytesProperty(String)", new Object[] { name });
/*      */           }
/*      */           
/*  411 */           byte[] traceRet1 = JmsXASessionImpl.this.getBytesProperty(name);
/*  412 */           if (Trace.isOn) {
/*  413 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getBytesProperty(String)", traceRet1);
/*      */           }
/*      */           
/*  416 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public char getCharProperty(String name) throws JMSException {
/*  420 */           if (Trace.isOn) {
/*  421 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "getCharProperty(String)", new Object[] { name });
/*      */           }
/*      */           
/*  424 */           char traceRet1 = JmsXASessionImpl.this.getCharProperty(name);
/*  425 */           if (Trace.isOn) {
/*  426 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getCharProperty(String)", 
/*  427 */                 Character.valueOf(traceRet1));
/*      */           }
/*  429 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public double getDoubleProperty(String name) throws JMSException {
/*  433 */           if (Trace.isOn) {
/*  434 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "getDoubleProperty(String)", new Object[] { name });
/*      */           }
/*      */           
/*  437 */           double traceRet1 = JmsXASessionImpl.this.getDoubleProperty(name);
/*  438 */           if (Trace.isOn) {
/*  439 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getDoubleProperty(String)", 
/*  440 */                 Double.valueOf(traceRet1));
/*      */           }
/*  442 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public float getFloatProperty(String arg0) throws JMSException {
/*  446 */           if (Trace.isOn) {
/*  447 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "getFloatProperty(String)", new Object[] { arg0 });
/*      */           }
/*      */           
/*  450 */           float traceRet1 = JmsXASessionImpl.this.getFloatProperty(arg0);
/*  451 */           if (Trace.isOn) {
/*  452 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getFloatProperty(String)", 
/*  453 */                 Float.valueOf(traceRet1));
/*      */           }
/*  455 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public int getIntProperty(String name) throws JMSException {
/*  459 */           if (Trace.isOn) {
/*  460 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "getIntProperty(String)", new Object[] { name });
/*      */           }
/*      */           
/*  463 */           int traceRet1 = JmsXASessionImpl.this.getIntProperty(name);
/*  464 */           if (Trace.isOn) {
/*  465 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getIntProperty(String)", 
/*  466 */                 Integer.valueOf(traceRet1));
/*      */           }
/*  468 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public long getLongProperty(String name) throws JMSException {
/*  472 */           if (Trace.isOn) {
/*  473 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "getLongProperty(String)", new Object[] { name });
/*      */           }
/*      */           
/*  476 */           long traceRet1 = JmsXASessionImpl.this.getLongProperty(name);
/*  477 */           if (Trace.isOn) {
/*  478 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getLongProperty(String)", 
/*  479 */                 Long.valueOf(traceRet1));
/*      */           }
/*  481 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public Object getObjectProperty(String name) throws JMSException {
/*  485 */           if (Trace.isOn) {
/*  486 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "getObjectProperty(String)", new Object[] { name });
/*      */           }
/*      */           
/*  489 */           Object traceRet1 = JmsXASessionImpl.this.getObjectProperty(name);
/*  490 */           if (Trace.isOn) {
/*  491 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getObjectProperty(String)", traceRet1);
/*      */           }
/*      */           
/*  494 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public Enumeration<String> getPropertyNames() throws JMSException {
/*  498 */           if (Trace.isOn) {
/*  499 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "getPropertyNames()");
/*      */           }
/*      */           
/*  502 */           Enumeration<String> traceRet1 = JmsXASessionImpl.this.getPropertyNames();
/*  503 */           if (Trace.isOn) {
/*  504 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getPropertyNames()", traceRet1);
/*      */           }
/*      */           
/*  507 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public short getShortProperty(String name) throws JMSException {
/*  511 */           if (Trace.isOn) {
/*  512 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "getShortProperty(String)", new Object[] { name });
/*      */           }
/*      */           
/*  515 */           short traceRet1 = JmsXASessionImpl.this.getShortProperty(name);
/*  516 */           if (Trace.isOn) {
/*  517 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getShortProperty(String)", 
/*  518 */                 Short.valueOf(traceRet1));
/*      */           }
/*  520 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public String getStringProperty(String name) throws JMSException {
/*  524 */           if (Trace.isOn) {
/*  525 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "getStringProperty(String)", new Object[] { name });
/*      */           }
/*      */           
/*  528 */           String traceRet1 = JmsXASessionImpl.this.getStringProperty(name);
/*  529 */           if (Trace.isOn) {
/*  530 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getStringProperty(String)", traceRet1);
/*      */           }
/*      */           
/*  533 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public boolean propertyExists(String name) throws JMSException {
/*  537 */           if (Trace.isOn) {
/*  538 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "propertyExists(String)", new Object[] { name });
/*      */           }
/*      */           
/*  541 */           boolean traceRet1 = JmsXASessionImpl.this.propertyExists(name);
/*  542 */           if (Trace.isOn) {
/*  543 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "propertyExists(String)", 
/*  544 */                 Boolean.valueOf(traceRet1));
/*      */           }
/*  546 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public void clear() {
/*  550 */           if (Trace.isOn) {
/*  551 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "clear()");
/*      */           }
/*  553 */           JmsXASessionImpl.this.clear();
/*      */           
/*  555 */           if (Trace.isOn) {
/*  556 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "clear()");
/*      */           }
/*      */         }
/*      */ 
/*      */         
/*      */         public boolean containsKey(Object key) {
/*  562 */           if (Trace.isOn) {
/*  563 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "containsKey(Object)", new Object[] { key });
/*      */           }
/*      */           
/*  566 */           boolean traceRet1 = JmsXASessionImpl.this.containsKey(key);
/*  567 */           if (Trace.isOn) {
/*  568 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "containsKey(Object)", 
/*  569 */                 Boolean.valueOf(traceRet1));
/*      */           }
/*  571 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public boolean containsValue(Object value) {
/*  575 */           if (Trace.isOn) {
/*  576 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "containsValue(Object)", new Object[] { value });
/*      */           }
/*      */           
/*  579 */           boolean traceRet1 = JmsXASessionImpl.this.containsValue(value);
/*  580 */           if (Trace.isOn) {
/*  581 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "containsValue(Object)", 
/*  582 */                 Boolean.valueOf(traceRet1));
/*      */           }
/*  584 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public Set<Map.Entry<String, Object>> entrySet() {
/*  588 */           if (Trace.isOn) {
/*  589 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "entrySet()");
/*      */           }
/*  591 */           Set<Map.Entry<String, Object>> traceRet1 = JmsXASessionImpl.this.entrySet();
/*  592 */           if (Trace.isOn) {
/*  593 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "entrySet()", traceRet1);
/*      */           }
/*  595 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public Object get(Object key) {
/*  599 */           if (Trace.isOn) {
/*  600 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "get(Object)", new Object[] { key });
/*      */           }
/*      */           
/*  603 */           Object traceRet1 = JmsXASessionImpl.this.get(key);
/*  604 */           if (Trace.isOn) {
/*  605 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "get(Object)", traceRet1);
/*      */           }
/*  607 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public boolean isEmpty() {
/*  611 */           if (Trace.isOn) {
/*  612 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "isEmpty()");
/*      */           }
/*  614 */           boolean traceRet1 = JmsXASessionImpl.this.isEmpty();
/*  615 */           if (Trace.isOn) {
/*  616 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "isEmpty()", 
/*  617 */                 Boolean.valueOf(traceRet1));
/*      */           }
/*  619 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public Set<String> keySet() {
/*  623 */           if (Trace.isOn) {
/*  624 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "keySet()");
/*      */           }
/*  626 */           Set<String> traceRet1 = JmsXASessionImpl.this.keySet();
/*  627 */           if (Trace.isOn) {
/*  628 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "keySet()", traceRet1);
/*      */           }
/*  630 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public Object put(String key, Object value) {
/*  634 */           if (Trace.isOn) {
/*  635 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "put(String,Object)", new Object[] { key, value });
/*      */           }
/*      */           
/*  638 */           Object traceRet1 = JmsXASessionImpl.this.put(key, value);
/*  639 */           if (Trace.isOn) {
/*  640 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "put(String,Object)", traceRet1);
/*      */           }
/*      */           
/*  643 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public void putAll(Map<? extends String, ? extends Object> t) {
/*  647 */           if (Trace.isOn) {
/*  648 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "putAll(Map<? extends String , ? extends Object>)", new Object[] { t });
/*      */           }
/*      */           
/*  651 */           JmsXASessionImpl.this.putAll(t);
/*      */           
/*  653 */           if (Trace.isOn) {
/*  654 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "putAll(Map<? extends String , ? extends Object>)");
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public Object remove(Object key) {
/*  661 */           if (Trace.isOn) {
/*  662 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "remove(Object)", new Object[] { key });
/*      */           }
/*      */           
/*  665 */           Object traceRet1 = JmsXASessionImpl.this.remove(key);
/*  666 */           if (Trace.isOn) {
/*  667 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "remove(Object)", traceRet1);
/*      */           }
/*  669 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public int size() {
/*  673 */           if (Trace.isOn) {
/*  674 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "size()");
/*      */           }
/*  676 */           int traceRet1 = JmsXASessionImpl.this.size();
/*  677 */           if (Trace.isOn) {
/*  678 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "size()", 
/*  679 */                 Integer.valueOf(traceRet1));
/*      */           }
/*  681 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public Collection<Object> values() {
/*  685 */           if (Trace.isOn) {
/*  686 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "values()");
/*      */           }
/*  688 */           Collection<Object> traceRet1 = JmsXASessionImpl.this.values();
/*  689 */           if (Trace.isOn) {
/*  690 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "values()", traceRet1);
/*      */           }
/*  692 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public void close() throws JMSException {
/*  696 */           if (Trace.isOn) {
/*  697 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "close()");
/*      */           }
/*  699 */           JmsXASessionImpl.this.close();
/*  700 */           if (Trace.isOn) {
/*  701 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "close()");
/*      */           }
/*      */         }
/*      */ 
/*      */         
/*      */         public void commit() throws JMSException {
/*  707 */           if (Trace.isOn) {
/*  708 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "commit()");
/*      */           }
/*  710 */           JmsXASessionImpl.this.commit();
/*  711 */           if (Trace.isOn) {
/*  712 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "commit()");
/*      */           }
/*      */         }
/*      */ 
/*      */         
/*      */         public QueueBrowser createBrowser(Queue queue) throws JMSException {
/*  718 */           if (Trace.isOn) {
/*  719 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "createBrowser(Queue)", new Object[] { queue });
/*      */           }
/*      */           
/*  722 */           QueueBrowser traceRet1 = JmsXASessionImpl.this.createBrowser(queue);
/*  723 */           if (Trace.isOn) {
/*  724 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createBrowser(Queue)", traceRet1);
/*      */           }
/*      */           
/*  727 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public QueueBrowser createBrowser(Queue queue, String selector) throws JMSException {
/*  731 */           if (Trace.isOn) {
/*  732 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "createBrowser(Queue,String)", new Object[] { queue, selector });
/*      */           }
/*      */           
/*  735 */           QueueBrowser traceRet1 = JmsXASessionImpl.this.createBrowser(queue, selector);
/*  736 */           if (Trace.isOn) {
/*  737 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createBrowser(Queue,String)", traceRet1);
/*      */           }
/*      */           
/*  740 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public BytesMessage createBytesMessage() throws JMSException {
/*  744 */           if (Trace.isOn) {
/*  745 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "createBytesMessage()");
/*      */           }
/*      */           
/*  748 */           BytesMessage traceRet1 = JmsXASessionImpl.this.createBytesMessage();
/*  749 */           if (Trace.isOn) {
/*  750 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createBytesMessage()", traceRet1);
/*      */           }
/*      */           
/*  753 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public MessageConsumer createConsumer(Destination dest) throws JMSException {
/*  757 */           if (Trace.isOn) {
/*  758 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "createConsumer(Destination)", new Object[] { dest });
/*      */           }
/*      */           
/*  761 */           MessageConsumer traceRet1 = JmsXASessionImpl.this.createConsumer(dest);
/*  762 */           if (Trace.isOn) {
/*  763 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createConsumer(Destination)", traceRet1);
/*      */           }
/*      */           
/*  766 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public MessageConsumer createConsumer(Destination dest, String selector) throws JMSException {
/*  770 */           if (Trace.isOn) {
/*  771 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "createConsumer(Destination,String)", new Object[] { dest, selector });
/*      */           }
/*      */           
/*  774 */           MessageConsumer traceRet1 = JmsXASessionImpl.this.createConsumer(dest, selector);
/*  775 */           if (Trace.isOn) {
/*  776 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createConsumer(Destination,String)", traceRet1);
/*      */           }
/*      */           
/*  779 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public MessageConsumer createConsumer(Destination dest, String selector, boolean noLocal) throws JMSException {
/*  783 */           if (Trace.isOn) {
/*  784 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "createConsumer(Destination,String,boolean)", new Object[] { dest, selector, 
/*      */                   
/*  786 */                   Boolean.valueOf(noLocal) });
/*      */           }
/*  788 */           MessageConsumer traceRet1 = JmsXASessionImpl.this.createConsumer(dest, selector, noLocal);
/*  789 */           if (Trace.isOn) {
/*  790 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createConsumer(Destination,String,boolean)", traceRet1);
/*      */           }
/*      */           
/*  793 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public TopicSubscriber createDurableSubscriber(Topic topic, String subscriptionName) throws JMSException {
/*  797 */           if (Trace.isOn) {
/*  798 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "createDurableSubscriber(Topic,String)", new Object[] { topic, subscriptionName });
/*      */           }
/*      */ 
/*      */           
/*  802 */           TopicSubscriber traceRet1 = JmsXASessionImpl.this.createDurableSubscriber(topic, subscriptionName);
/*  803 */           if (Trace.isOn) {
/*  804 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createDurableSubscriber(Topic,String)", traceRet1);
/*      */           }
/*      */           
/*  807 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public TopicSubscriber createDurableSubscriber(Topic topic, String subscriptionName, String selector, boolean noLocal) throws JMSException {
/*  811 */           if (Trace.isOn) {
/*  812 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "createDurableSubscriber(Topic,String,String,boolean)", new Object[] { topic, subscriptionName, selector, 
/*      */                   
/*  814 */                   Boolean.valueOf(noLocal) });
/*      */           }
/*      */           
/*  817 */           TopicSubscriber traceRet1 = JmsXASessionImpl.this.createDurableSubscriber(topic, subscriptionName, selector, noLocal);
/*  818 */           if (Trace.isOn) {
/*  819 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createDurableSubscriber(Topic,String,String,boolean)", traceRet1);
/*      */           }
/*      */           
/*  822 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public MapMessage createMapMessage() throws JMSException {
/*  826 */           if (Trace.isOn) {
/*  827 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "createMapMessage()");
/*      */           }
/*      */           
/*  830 */           MapMessage traceRet1 = JmsXASessionImpl.this.createMapMessage();
/*  831 */           if (Trace.isOn) {
/*  832 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createMapMessage()", traceRet1);
/*      */           }
/*      */           
/*  835 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public Message createMessage() throws JMSException {
/*  839 */           if (Trace.isOn) {
/*  840 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "createMessage()");
/*      */           }
/*      */           
/*  843 */           Message traceRet1 = JmsXASessionImpl.this.createMessage();
/*  844 */           if (Trace.isOn) {
/*  845 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createMessage()", traceRet1);
/*      */           }
/*  847 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public ObjectMessage createObjectMessage() throws JMSException {
/*  851 */           if (Trace.isOn) {
/*  852 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "createObjectMessage()");
/*      */           }
/*      */           
/*  855 */           ObjectMessage traceRet1 = JmsXASessionImpl.this.createObjectMessage();
/*  856 */           if (Trace.isOn) {
/*  857 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createObjectMessage()", traceRet1);
/*      */           }
/*      */           
/*  860 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public ObjectMessage createObjectMessage(Serializable arg0) throws JMSException {
/*  864 */           if (Trace.isOn) {
/*  865 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "createObjectMessage(Serializable)", new Object[] { arg0 });
/*      */           }
/*      */           
/*  868 */           ObjectMessage traceRet1 = JmsXASessionImpl.this.createObjectMessage(arg0);
/*  869 */           if (Trace.isOn) {
/*  870 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createObjectMessage(Serializable)", traceRet1);
/*      */           }
/*      */           
/*  873 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public MessageProducer createProducer(Destination dest) throws JMSException {
/*  877 */           if (Trace.isOn) {
/*  878 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "createProducer(Destination)", new Object[] { dest });
/*      */           }
/*      */           
/*  881 */           MessageProducer traceRet1 = JmsXASessionImpl.this.createProducer(dest);
/*  882 */           if (Trace.isOn) {
/*  883 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createProducer(Destination)", traceRet1);
/*      */           }
/*      */           
/*  886 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public Queue createQueue(String name) throws JMSException {
/*  890 */           if (Trace.isOn) {
/*  891 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "createQueue(String)", new Object[] { name });
/*      */           }
/*      */           
/*  894 */           Queue traceRet1 = JmsXASessionImpl.this.createQueue(name);
/*  895 */           if (Trace.isOn) {
/*  896 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createQueue(String)", traceRet1);
/*      */           }
/*      */           
/*  899 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public StreamMessage createStreamMessage() throws JMSException {
/*  903 */           if (Trace.isOn) {
/*  904 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "createStreamMessage()");
/*      */           }
/*      */           
/*  907 */           StreamMessage traceRet1 = JmsXASessionImpl.this.createStreamMessage();
/*  908 */           if (Trace.isOn) {
/*  909 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createStreamMessage()", traceRet1);
/*      */           }
/*      */           
/*  912 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public TemporaryQueue createTemporaryQueue() throws JMSException {
/*  916 */           if (Trace.isOn) {
/*  917 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "createTemporaryQueue()");
/*      */           }
/*      */           
/*  920 */           TemporaryQueue traceRet1 = JmsXASessionImpl.this.createTemporaryQueue();
/*  921 */           if (Trace.isOn) {
/*  922 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createTemporaryQueue()", traceRet1);
/*      */           }
/*      */           
/*  925 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public TemporaryTopic createTemporaryTopic() throws JMSException {
/*  929 */           if (Trace.isOn) {
/*  930 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "createTemporaryTopic()");
/*      */           }
/*      */           
/*  933 */           TemporaryTopic traceRet1 = JmsXASessionImpl.this.createTemporaryTopic();
/*  934 */           if (Trace.isOn) {
/*  935 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createTemporaryTopic()", traceRet1);
/*      */           }
/*      */           
/*  938 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public TextMessage createTextMessage() throws JMSException {
/*  942 */           if (Trace.isOn) {
/*  943 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "createTextMessage()");
/*      */           }
/*      */           
/*  946 */           TextMessage traceRet1 = JmsXASessionImpl.this.createTextMessage();
/*  947 */           if (Trace.isOn) {
/*  948 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createTextMessage()", traceRet1);
/*      */           }
/*      */           
/*  951 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public TextMessage createTextMessage(String text) throws JMSException {
/*  955 */           if (Trace.isOn) {
/*  956 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "createTextMessage(String)", new Object[] { text });
/*      */           }
/*      */           
/*  959 */           TextMessage traceRet1 = JmsXASessionImpl.this.createTextMessage(text);
/*  960 */           if (Trace.isOn) {
/*  961 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createTextMessage(String)", traceRet1);
/*      */           }
/*      */           
/*  964 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public Topic createTopic(String name) throws JMSException {
/*  968 */           if (Trace.isOn) {
/*  969 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "createTopic(String)", new Object[] { name });
/*      */           }
/*      */           
/*  972 */           Topic traceRet1 = JmsXASessionImpl.this.createTopic(name);
/*  973 */           if (Trace.isOn) {
/*  974 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createTopic(String)", traceRet1);
/*      */           }
/*      */           
/*  977 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public int getAcknowledgeMode() throws JMSException {
/*  981 */           if (Trace.isOn) {
/*  982 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "getAcknowledgeMode()");
/*      */           }
/*      */           
/*  985 */           int traceRet1 = JmsXASessionImpl.this.getAcknowledgeMode();
/*  986 */           if (Trace.isOn) {
/*  987 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getAcknowledgeMode()", 
/*  988 */                 Integer.valueOf(traceRet1));
/*      */           }
/*  990 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public MessageListener getMessageListener() throws JMSException {
/*  994 */           if (Trace.isOn) {
/*  995 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "getMessageListener()");
/*      */           }
/*      */           
/*  998 */           MessageListener traceRet1 = JmsXASessionImpl.this.getMessageListener();
/*  999 */           if (Trace.isOn) {
/* 1000 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getMessageListener()", traceRet1);
/*      */           }
/*      */           
/* 1003 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public boolean getTransacted() throws JMSException {
/* 1007 */           if (Trace.isOn) {
/* 1008 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "getTransacted()");
/*      */           }
/*      */           
/* 1011 */           boolean traceRet1 = JmsXASessionImpl.this.getTransacted();
/* 1012 */           if (Trace.isOn) {
/* 1013 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getTransacted()", 
/* 1014 */                 Boolean.valueOf(traceRet1));
/*      */           }
/* 1016 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public void recover() throws JMSException {
/* 1020 */           if (Trace.isOn) {
/* 1021 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "recover()");
/*      */           }
/* 1023 */           JmsXASessionImpl.this.recover();
/* 1024 */           if (Trace.isOn) {
/* 1025 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "recover()");
/*      */           }
/*      */         }
/*      */ 
/*      */         
/*      */         public void rollback() throws JMSException {
/* 1031 */           if (Trace.isOn) {
/* 1032 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "rollback()");
/*      */           }
/* 1034 */           JmsXASessionImpl.this.rollback();
/*      */           
/* 1036 */           if (Trace.isOn) {
/* 1037 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "rollback()");
/*      */           }
/*      */         }
/*      */ 
/*      */         
/*      */         public void run() {
/* 1043 */           if (Trace.isOn) {
/* 1044 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "run()");
/*      */           }
/* 1046 */           JmsXASessionImpl.this.run();
/*      */           
/* 1048 */           if (Trace.isOn) {
/* 1049 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "run()");
/*      */           }
/*      */         }
/*      */ 
/*      */         
/*      */         public void setMessageListener(MessageListener listener) throws JMSException {
/* 1055 */           if (Trace.isOn) {
/* 1056 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "setMessageListener(MessageListener)", new Object[] { listener });
/*      */           }
/*      */           
/* 1059 */           JmsXASessionImpl.this.setMessageListener(listener);
/* 1060 */           if (Trace.isOn) {
/* 1061 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "setMessageListener(MessageListener)");
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public void unsubscribe(String subscriptionName) throws JMSException {
/* 1068 */           if (Trace.isOn) {
/* 1069 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "unsubscribe(String)", new Object[] { subscriptionName });
/*      */           }
/*      */           
/* 1072 */           JmsXASessionImpl.this.unsubscribe(subscriptionName);
/* 1073 */           if (Trace.isOn) {
/* 1074 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "unsubscribe(String)");
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public void clearMessageReferences() {
/* 1081 */           if (Trace.isOn) {
/* 1082 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "clearMessageReferences()");
/*      */           }
/*      */           
/* 1085 */           JmsXASessionImpl.this.clearMessageReferences();
/* 1086 */           if (Trace.isOn) {
/* 1087 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "clearMessageReferences()");
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public MessageConsumer createDurableConsumer(Topic topic, String name) throws InvalidDestinationException, IllegalStateException, JMSException {
/* 1099 */           if (Trace.isOn) {
/* 1100 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "createDurableConsumer(Topic,String)", new Object[] { topic, name });
/*      */           }
/*      */ 
/*      */           
/* 1104 */           MessageConsumer traceRet1 = JmsXASessionImpl.this.createDurableConsumer(topic, name);
/* 1105 */           if (Trace.isOn) {
/* 1106 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createDurableConsumer(Topic,String)", traceRet1);
/*      */           }
/*      */           
/* 1109 */           return traceRet1;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public MessageConsumer createDurableConsumer(Topic topic, String name, String selector, boolean noLocal) throws InvalidDestinationException, InvalidSelectorException, IllegalStateException, JMSException {
/* 1116 */           if (Trace.isOn) {
/* 1117 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "createDurableConsumer(Topic,String,String,boolean)", new Object[] { topic, name, selector, 
/*      */                   
/* 1119 */                   Boolean.valueOf(noLocal) });
/*      */           }
/*      */           
/* 1122 */           MessageConsumer traceRet1 = JmsXASessionImpl.this.createDurableConsumer(topic, name, selector, noLocal);
/* 1123 */           if (Trace.isOn) {
/* 1124 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createDurableConsumer(Topic,String,String,boolean)", traceRet1);
/*      */           }
/*      */           
/* 1127 */           return traceRet1;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public MessageConsumer createSharedConsumer(Topic topic, String name) throws JMSException, InvalidDestinationException, InvalidSelectorException {
/* 1134 */           if (Trace.isOn) {
/* 1135 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "createSharedConsumer(Topic,String)", new Object[] { topic, name });
/*      */           }
/*      */ 
/*      */           
/* 1139 */           MessageConsumer traceRet1 = JmsXASessionImpl.this.createSharedConsumer(topic, name);
/* 1140 */           if (Trace.isOn) {
/* 1141 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createSharedConsumer(Topic,String)", traceRet1);
/*      */           }
/*      */           
/* 1144 */           return traceRet1;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public MessageConsumer createSharedConsumer(Topic topic, String name, String selector) throws JMSException, InvalidDestinationException, InvalidSelectorException {
/* 1151 */           if (Trace.isOn) {
/* 1152 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "createSharedConsumer(Topic,String,String)", new Object[] { topic, name, selector });
/*      */           }
/*      */ 
/*      */           
/* 1156 */           MessageConsumer traceRet1 = JmsXASessionImpl.this.createSharedConsumer(topic, name, selector);
/* 1157 */           if (Trace.isOn) {
/* 1158 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createSharedConsumer(Topic,String,String)", traceRet1);
/*      */           }
/*      */           
/* 1161 */           return traceRet1;
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public MessageConsumer createSharedDurableConsumer(Topic topic, String name) throws JMSException, InvalidDestinationException {
/* 1167 */           if (Trace.isOn) {
/* 1168 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "createSharedDurableConsumer(Topic,String)", new Object[] { topic, name });
/*      */           }
/*      */ 
/*      */           
/* 1172 */           MessageConsumer traceRet1 = JmsXASessionImpl.this.createSharedDurableConsumer(topic, name);
/* 1173 */           if (Trace.isOn) {
/* 1174 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createSharedDurableConsumer(Topic,String)", traceRet1);
/*      */           }
/*      */           
/* 1177 */           return traceRet1;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public MessageConsumer createSharedDurableConsumer(Topic topic, String name, String selector) throws InvalidDestinationException, IllegalStateException, JMSException {
/* 1184 */           if (Trace.isOn) {
/* 1185 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "createSharedDurableConsumer(Topic,String,String)", new Object[] { topic, name, selector });
/*      */           }
/*      */ 
/*      */           
/* 1189 */           MessageConsumer traceRet1 = JmsXASessionImpl.this.createSharedDurableConsumer(topic, name, selector);
/* 1190 */           if (Trace.isOn) {
/* 1191 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createSharedDurableConsumer(Topic,String,String)", traceRet1);
/*      */           }
/*      */           
/* 1194 */           return traceRet1;
/*      */         }
/*      */       };
/*      */     
/* 1198 */     if (Trace.isOn) {
/* 1199 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "getSession()", jmsSession);
/*      */     }
/* 1201 */     if (Trace.isOn) {
/* 1202 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "getSession()", jmsSession);
/*      */     }
/*      */     
/* 1205 */     return (Session)jmsSession;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public XAResource getXAResource() {
/* 1214 */     if (Trace.isOn) {
/* 1215 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "getXAResource()");
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 1220 */       checkNotClosed();
/*      */       
/* 1222 */       XAResource xar = getProviderXASession().getXAResource();
/* 1223 */       if (Trace.isOn) {
/* 1224 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "getXAResource()", xar);
/*      */       }
/*      */       
/* 1227 */       return xar;
/*      */     }
/* 1229 */     catch (JMSException e) {
/* 1230 */       if (Trace.isOn) {
/* 1231 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "getXAResource()", (Throwable)e);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1238 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1239 */       inserts.put("XMSC_INSERT_EXCEPTION", e);
/* 1240 */       RuntimeException re = (RuntimeException)JmsErrorUtils.createException("JMSCC0106", inserts);
/* 1241 */       re.initCause((Throwable)e);
/* 1242 */       if (Trace.isOn) {
/* 1243 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "getXAResource()", re);
/*      */       }
/*      */       
/* 1246 */       throw re;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected ProviderXASession getProviderXASession() {
/* 1254 */     ProviderXASession pxs = (ProviderXASession)getProviderSession();
/* 1255 */     if (Trace.isOn) {
/* 1256 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "getProviderXASession()", "getter", pxs);
/*      */     }
/*      */     
/* 1259 */     return pxs;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void commit() throws JMSException {
/* 1268 */     if (Trace.isOn) {
/* 1269 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "commit()");
/*      */     }
/* 1271 */     JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC1069", null);
/* 1272 */     if (Trace.isOn) {
/* 1273 */       Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "commit()", (Throwable)je);
/*      */     }
/* 1275 */     throw je;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void rollback() throws JMSException {
/* 1284 */     if (Trace.isOn) {
/* 1285 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "rollback()");
/*      */     }
/* 1287 */     JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC1069", null);
/* 1288 */     if (Trace.isOn) {
/* 1289 */       Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsXASessionImpl", "rollback()", (Throwable)je);
/*      */     }
/* 1291 */     throw je;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsXASessionImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */