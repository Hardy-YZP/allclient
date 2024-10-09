/*      */ package com.ibm.msg.client.jms.internal;
/*      */ 
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.jms.JmsMessageReference;
/*      */ import com.ibm.msg.client.jms.JmsTopicSession;
/*      */ import com.ibm.msg.client.jms.JmsXATopicSession;
/*      */ import java.io.Serializable;
/*      */ import java.util.Collection;
/*      */ import java.util.Enumeration;
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
/*      */ import javax.jms.StreamMessage;
/*      */ import javax.jms.TemporaryQueue;
/*      */ import javax.jms.TemporaryTopic;
/*      */ import javax.jms.TextMessage;
/*      */ import javax.jms.Topic;
/*      */ import javax.jms.TopicPublisher;
/*      */ import javax.jms.TopicSession;
/*      */ import javax.jms.TopicSubscriber;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class JmsXATopicSessionImpl
/*      */   extends JmsXASessionImpl
/*      */   implements JmsXATopicSession
/*      */ {
/*      */   private static final long serialVersionUID = -918832292474539111L;
/*      */   static final String sccsid2 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsXATopicSessionImpl.java";
/*      */   
/*      */   static {
/*   72 */     if (Trace.isOn) {
/*   73 */       Trace.data("com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsXATopicSessionImpl.java");
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
/*      */   protected JmsXATopicSessionImpl(JmsXATopicConnectionImpl connection) throws JMSException {
/*   89 */     super(connection);
/*   90 */     if (Trace.isOn) {
/*   91 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "<init>(JmsXATopicConnectionImpl)", new Object[] { connection });
/*      */     }
/*      */     
/*   94 */     if (Trace.isOn) {
/*   95 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "<init>(JmsXATopicConnectionImpl)");
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
/*      */   public TopicSession getTopicSession() throws JMSException {
/*  127 */     if (Trace.isOn) {
/*  128 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "getTopicSession()");
/*      */     }
/*      */     
/*  131 */     if (Trace.isOn) {
/*  132 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "getTopicSession()");
/*  133 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "getTopicSession()", "creating new JmsTopicSession() for use with this XA session");
/*      */     } 
/*  135 */     JmsTopicSession jmsTopicSession = new JmsTopicSession()
/*      */       {
/*      */         private static final long serialVersionUID = 7591389817411237208L;
/*      */         
/*      */         public Message consume(byte[] flatMR) throws JMSException {
/*  140 */           if (Trace.isOn) {
/*  141 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "consume(byte [ ])", new Object[] { flatMR });
/*      */           }
/*      */           
/*  144 */           Message traceRet1 = JmsXATopicSessionImpl.this.consume(flatMR);
/*  145 */           if (Trace.isOn) {
/*  146 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "consume(byte [ ])", traceRet1);
/*      */           }
/*      */           
/*  149 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public void deliver(List<JmsMessageReference> arg0) throws JMSException {
/*  153 */           if (Trace.isOn) {
/*  154 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "deliver(List<JmsMessageReference>)", new Object[] { arg0 });
/*      */           }
/*      */           
/*  157 */           JmsXATopicSessionImpl.this.deliver(arg0);
/*  158 */           if (Trace.isOn) {
/*  159 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "deliver(List<JmsMessageReference>)");
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public JmsMessageReference recreateMessageReference(byte[] arg0) throws JMSException {
/*  166 */           if (Trace.isOn) {
/*  167 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "recreateMessageReference(byte [ ])", new Object[] { arg0 });
/*      */           }
/*      */           
/*  170 */           JmsMessageReference traceRet1 = JmsXATopicSessionImpl.this.recreateMessageReference(arg0);
/*  171 */           if (Trace.isOn) {
/*  172 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "recreateMessageReference(byte [ ])", traceRet1);
/*      */           }
/*      */           
/*  175 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public void setBatchProperties(Map<String, Object> arg0) throws JMSException {
/*  179 */           if (Trace.isOn) {
/*  180 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "setBatchProperties(Map<String , Object>)", new Object[] { arg0 });
/*      */           }
/*      */           
/*  183 */           JmsXATopicSessionImpl.this.setBatchProperties(arg0);
/*  184 */           if (Trace.isOn) {
/*  185 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "setBatchProperties(Map<String , Object>)");
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public void setBooleanProperty(String name, boolean value) throws JMSException {
/*  192 */           if (Trace.isOn) {
/*  193 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "setBooleanProperty(String,boolean)", new Object[] { name, 
/*  194 */                   Boolean.valueOf(value) });
/*      */           }
/*  196 */           JmsXATopicSessionImpl.this.setBooleanProperty(name, value);
/*  197 */           if (Trace.isOn) {
/*  198 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "setBooleanProperty(String,boolean)");
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public void setByteProperty(String name, byte value) throws JMSException {
/*  205 */           if (Trace.isOn) {
/*  206 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "setByteProperty(String,byte)", new Object[] { name, 
/*  207 */                   Byte.valueOf(value) });
/*      */           }
/*  209 */           JmsXATopicSessionImpl.this.setByteProperty(name, value);
/*  210 */           if (Trace.isOn) {
/*  211 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "setByteProperty(String,byte)");
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public void setBytesProperty(String name, byte[] value) throws JMSException {
/*  218 */           if (Trace.isOn) {
/*  219 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "setBytesProperty(String,byte [ ])", new Object[] { name, value });
/*      */           }
/*      */           
/*  222 */           JmsXATopicSessionImpl.this.setBytesProperty(name, value);
/*  223 */           if (Trace.isOn) {
/*  224 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "setBytesProperty(String,byte [ ])");
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public void setCharProperty(String name, char value) throws JMSException {
/*  231 */           if (Trace.isOn) {
/*  232 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "setCharProperty(String,char)", new Object[] { name, 
/*  233 */                   Character.valueOf(value) });
/*      */           }
/*  235 */           JmsXATopicSessionImpl.this.setCharProperty(name, value);
/*  236 */           if (Trace.isOn) {
/*  237 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "setCharProperty(String,char)");
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public void setDoubleProperty(String name, double value) throws JMSException {
/*  244 */           if (Trace.isOn) {
/*  245 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "setDoubleProperty(String,double)", new Object[] { name, 
/*  246 */                   Double.valueOf(value) });
/*      */           }
/*  248 */           JmsXATopicSessionImpl.this.setDoubleProperty(name, value);
/*  249 */           if (Trace.isOn) {
/*  250 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "setDoubleProperty(String,double)");
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public void setFloatProperty(String name, float value) throws JMSException {
/*  257 */           if (Trace.isOn) {
/*  258 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "setFloatProperty(String,float)", new Object[] { name, 
/*  259 */                   Float.valueOf(value) });
/*      */           }
/*  261 */           JmsXATopicSessionImpl.this.setFloatProperty(name, value);
/*  262 */           if (Trace.isOn) {
/*  263 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "setFloatProperty(String,float)");
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public void setIntProperty(String name, int value) throws JMSException {
/*  270 */           if (Trace.isOn) {
/*  271 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "setIntProperty(String,int)", new Object[] { name, 
/*  272 */                   Integer.valueOf(value) });
/*      */           }
/*  274 */           JmsXATopicSessionImpl.this.setIntProperty(name, value);
/*  275 */           if (Trace.isOn) {
/*  276 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "setIntProperty(String,int)");
/*      */           }
/*      */         }
/*      */ 
/*      */         
/*      */         public void setLongProperty(String name, long value) throws JMSException {
/*  282 */           if (Trace.isOn) {
/*  283 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "setLongProperty(String,long)", new Object[] { name, 
/*  284 */                   Long.valueOf(value) });
/*      */           }
/*  286 */           JmsXATopicSessionImpl.this.setLongProperty(name, value);
/*  287 */           if (Trace.isOn) {
/*  288 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "setLongProperty(String,long)");
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public void setObjectProperty(String arg0, Object arg1) throws JMSException {
/*  295 */           if (Trace.isOn) {
/*  296 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "setObjectProperty(String,Object)", new Object[] { arg0, arg1 });
/*      */           }
/*      */           
/*  299 */           JmsXATopicSessionImpl.this.setObjectProperty(arg0, arg1);
/*  300 */           if (Trace.isOn) {
/*  301 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "setObjectProperty(String,Object)");
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public void setShortProperty(String name, short value) throws JMSException {
/*  308 */           if (Trace.isOn) {
/*  309 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "setShortProperty(String,short)", new Object[] { name, 
/*  310 */                   Short.valueOf(value) });
/*      */           }
/*  312 */           JmsXATopicSessionImpl.this.setShortProperty(name, value);
/*  313 */           if (Trace.isOn) {
/*  314 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "setShortProperty(String,short)");
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public void setStringProperty(String name, String value) throws JMSException {
/*  321 */           if (Trace.isOn) {
/*  322 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "setStringProperty(String,String)", new Object[] { name, value });
/*      */           }
/*      */           
/*  325 */           JmsXATopicSessionImpl.this.setStringProperty(name, value);
/*  326 */           if (Trace.isOn) {
/*  327 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "setStringProperty(String,String)");
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public boolean getBooleanProperty(String name) throws JMSException {
/*  334 */           if (Trace.isOn) {
/*  335 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "getBooleanProperty(String)", new Object[] { name });
/*      */           }
/*      */           
/*  338 */           boolean traceRet1 = JmsXATopicSessionImpl.this.getBooleanProperty(name);
/*  339 */           if (Trace.isOn) {
/*  340 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getBooleanProperty(String)", 
/*  341 */                 Boolean.valueOf(traceRet1));
/*      */           }
/*  343 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public byte getByteProperty(String name) throws JMSException {
/*  347 */           if (Trace.isOn) {
/*  348 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "getByteProperty(String)", new Object[] { name });
/*      */           }
/*      */           
/*  351 */           byte traceRet1 = JmsXATopicSessionImpl.this.getByteProperty(name);
/*  352 */           if (Trace.isOn) {
/*  353 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getByteProperty(String)", 
/*  354 */                 Byte.valueOf(traceRet1));
/*      */           }
/*  356 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public byte[] getBytesProperty(String name) throws JMSException {
/*  360 */           if (Trace.isOn) {
/*  361 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "getBytesProperty(String)", new Object[] { name });
/*      */           }
/*      */           
/*  364 */           byte[] traceRet1 = JmsXATopicSessionImpl.this.getBytesProperty(name);
/*  365 */           if (Trace.isOn) {
/*  366 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getBytesProperty(String)", traceRet1);
/*      */           }
/*      */           
/*  369 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public char getCharProperty(String arg0) throws JMSException {
/*  373 */           if (Trace.isOn) {
/*  374 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "getCharProperty(String)", new Object[] { arg0 });
/*      */           }
/*      */           
/*  377 */           char traceRet1 = JmsXATopicSessionImpl.this.getCharProperty(arg0);
/*  378 */           if (Trace.isOn) {
/*  379 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getCharProperty(String)", 
/*  380 */                 Character.valueOf(traceRet1));
/*      */           }
/*  382 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public double getDoubleProperty(String name) throws JMSException {
/*  386 */           if (Trace.isOn) {
/*  387 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "getDoubleProperty(String)", new Object[] { name });
/*      */           }
/*      */           
/*  390 */           double traceRet1 = JmsXATopicSessionImpl.this.getDoubleProperty(name);
/*  391 */           if (Trace.isOn) {
/*  392 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getDoubleProperty(String)", 
/*  393 */                 Double.valueOf(traceRet1));
/*      */           }
/*  395 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public float getFloatProperty(String name) throws JMSException {
/*  399 */           if (Trace.isOn) {
/*  400 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "getFloatProperty(String)", new Object[] { name });
/*      */           }
/*      */           
/*  403 */           float traceRet1 = JmsXATopicSessionImpl.this.getFloatProperty(name);
/*  404 */           if (Trace.isOn) {
/*  405 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getFloatProperty(String)", 
/*  406 */                 Float.valueOf(traceRet1));
/*      */           }
/*  408 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public int getIntProperty(String name) throws JMSException {
/*  412 */           if (Trace.isOn) {
/*  413 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "getIntProperty(String)", new Object[] { name });
/*      */           }
/*      */           
/*  416 */           int traceRet1 = JmsXATopicSessionImpl.this.getIntProperty(name);
/*  417 */           if (Trace.isOn) {
/*  418 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getIntProperty(String)", 
/*  419 */                 Integer.valueOf(traceRet1));
/*      */           }
/*  421 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public long getLongProperty(String name) throws JMSException {
/*  425 */           if (Trace.isOn) {
/*  426 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "getLongProperty(String)", new Object[] { name });
/*      */           }
/*      */           
/*  429 */           long traceRet1 = JmsXATopicSessionImpl.this.getLongProperty(name);
/*  430 */           if (Trace.isOn) {
/*  431 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getLongProperty(String)", 
/*  432 */                 Long.valueOf(traceRet1));
/*      */           }
/*  434 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public Object getObjectProperty(String arg0) throws JMSException {
/*  438 */           if (Trace.isOn) {
/*  439 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "getObjectProperty(String)", new Object[] { arg0 });
/*      */           }
/*      */           
/*  442 */           Object traceRet1 = JmsXATopicSessionImpl.this.getObjectProperty(arg0);
/*  443 */           if (Trace.isOn) {
/*  444 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getObjectProperty(String)", traceRet1);
/*      */           }
/*      */           
/*  447 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public Enumeration<String> getPropertyNames() throws JMSException {
/*  451 */           if (Trace.isOn) {
/*  452 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "getPropertyNames()");
/*      */           }
/*      */           
/*  455 */           Enumeration<String> traceRet1 = JmsXATopicSessionImpl.this.getPropertyNames();
/*  456 */           if (Trace.isOn) {
/*  457 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getPropertyNames()", traceRet1);
/*      */           }
/*      */           
/*  460 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public short getShortProperty(String name) throws JMSException {
/*  464 */           if (Trace.isOn) {
/*  465 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "getShortProperty(String)", new Object[] { name });
/*      */           }
/*      */           
/*  468 */           short traceRet1 = JmsXATopicSessionImpl.this.getShortProperty(name);
/*  469 */           if (Trace.isOn) {
/*  470 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getShortProperty(String)", 
/*  471 */                 Short.valueOf(traceRet1));
/*      */           }
/*  473 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public String getStringProperty(String name) throws JMSException {
/*  477 */           if (Trace.isOn) {
/*  478 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "getStringProperty(String)", new Object[] { name });
/*      */           }
/*      */           
/*  481 */           String traceRet1 = JmsXATopicSessionImpl.this.getStringProperty(name);
/*  482 */           if (Trace.isOn) {
/*  483 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getStringProperty(String)", traceRet1);
/*      */           }
/*      */           
/*  486 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public boolean propertyExists(String name) throws JMSException {
/*  490 */           if (Trace.isOn) {
/*  491 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "propertyExists(String)", new Object[] { name });
/*      */           }
/*      */           
/*  494 */           boolean traceRet1 = JmsXATopicSessionImpl.this.propertyExists(name);
/*  495 */           if (Trace.isOn) {
/*  496 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "propertyExists(String)", 
/*  497 */                 Boolean.valueOf(traceRet1));
/*      */           }
/*  499 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public void clear() {
/*  503 */           if (Trace.isOn) {
/*  504 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "clear()");
/*      */           }
/*  506 */           JmsXATopicSessionImpl.this.clear();
/*  507 */           if (Trace.isOn) {
/*  508 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "clear()");
/*      */           }
/*      */         }
/*      */ 
/*      */         
/*      */         public boolean containsKey(Object key) {
/*  514 */           if (Trace.isOn) {
/*  515 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "containsKey(Object)", new Object[] { key });
/*      */           }
/*      */           
/*  518 */           boolean traceRet1 = JmsXATopicSessionImpl.this.containsKey(key);
/*  519 */           if (Trace.isOn) {
/*  520 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "containsKey(Object)", 
/*  521 */                 Boolean.valueOf(traceRet1));
/*      */           }
/*  523 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public boolean containsValue(Object value) {
/*  527 */           if (Trace.isOn) {
/*  528 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "containsValue(Object)", new Object[] { value });
/*      */           }
/*      */           
/*  531 */           boolean traceRet1 = JmsXATopicSessionImpl.this.containsValue(value);
/*  532 */           if (Trace.isOn) {
/*  533 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "containsValue(Object)", 
/*  534 */                 Boolean.valueOf(traceRet1));
/*      */           }
/*  536 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public Set<Map.Entry<String, Object>> entrySet() {
/*  540 */           if (Trace.isOn) {
/*  541 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "entrySet()");
/*      */           }
/*      */           
/*  544 */           Set<Map.Entry<String, Object>> traceRet1 = JmsXATopicSessionImpl.this.entrySet();
/*  545 */           if (Trace.isOn) {
/*  546 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "entrySet()", traceRet1);
/*      */           }
/*  548 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public Object get(Object key) {
/*  552 */           if (Trace.isOn) {
/*  553 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "get(Object)", new Object[] { key });
/*      */           }
/*      */           
/*  556 */           Object traceRet1 = JmsXATopicSessionImpl.this.get(key);
/*  557 */           if (Trace.isOn) {
/*  558 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "get(Object)", traceRet1);
/*      */           }
/*  560 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public boolean isEmpty() {
/*  564 */           if (Trace.isOn) {
/*  565 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "isEmpty()");
/*      */           }
/*  567 */           boolean traceRet1 = JmsXATopicSessionImpl.this.isEmpty();
/*  568 */           if (Trace.isOn) {
/*  569 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "isEmpty()", 
/*  570 */                 Boolean.valueOf(traceRet1));
/*      */           }
/*  572 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public Set<String> keySet() {
/*  576 */           if (Trace.isOn) {
/*  577 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "keySet()");
/*      */           }
/*  579 */           Set<String> traceRet1 = JmsXATopicSessionImpl.this.keySet();
/*  580 */           if (Trace.isOn) {
/*  581 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "keySet()", traceRet1);
/*      */           }
/*  583 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public Object put(String key, Object value) {
/*  587 */           if (Trace.isOn) {
/*  588 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "put(String,Object)", new Object[] { key, value });
/*      */           }
/*      */           
/*  591 */           Object traceRet1 = JmsXATopicSessionImpl.this.put(key, value);
/*  592 */           if (Trace.isOn) {
/*  593 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "put(String,Object)", traceRet1);
/*      */           }
/*      */           
/*  596 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public void putAll(Map<? extends String, ? extends Object> t) {
/*  600 */           if (Trace.isOn) {
/*  601 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "putAll(Map<? extends String , ? extends Object>)", new Object[] { t });
/*      */           }
/*      */           
/*  604 */           JmsXATopicSessionImpl.this.putAll(t);
/*      */           
/*  606 */           if (Trace.isOn) {
/*  607 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "putAll(Map<? extends String , ? extends Object>)");
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public Object remove(Object key) {
/*  614 */           if (Trace.isOn) {
/*  615 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "remove(Object)", new Object[] { key });
/*      */           }
/*      */           
/*  618 */           Object traceRet1 = JmsXATopicSessionImpl.this.remove(key);
/*  619 */           if (Trace.isOn) {
/*  620 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "remove(Object)", traceRet1);
/*      */           }
/*  622 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public int size() {
/*  626 */           if (Trace.isOn) {
/*  627 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "size()");
/*      */           }
/*  629 */           int traceRet1 = JmsXATopicSessionImpl.this.size();
/*  630 */           if (Trace.isOn) {
/*  631 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "size()", 
/*  632 */                 Integer.valueOf(traceRet1));
/*      */           }
/*  634 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public Collection<Object> values() {
/*  638 */           if (Trace.isOn) {
/*  639 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "values()");
/*      */           }
/*  641 */           Collection<Object> traceRet1 = JmsXATopicSessionImpl.this.values();
/*  642 */           if (Trace.isOn) {
/*  643 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "values()", traceRet1);
/*      */           }
/*  645 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public void close() throws JMSException {
/*  649 */           if (Trace.isOn) {
/*  650 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "close()");
/*      */           }
/*  652 */           JmsXATopicSessionImpl.this.close();
/*  653 */           if (Trace.isOn) {
/*  654 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "close()");
/*      */           }
/*      */         }
/*      */ 
/*      */         
/*      */         public void commit() throws JMSException {
/*  660 */           if (Trace.isOn) {
/*  661 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "commit()");
/*      */           }
/*  663 */           JmsXATopicSessionImpl.this.commit();
/*  664 */           if (Trace.isOn) {
/*  665 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "commit()");
/*      */           }
/*      */         }
/*      */ 
/*      */         
/*      */         public QueueBrowser createBrowser(Queue queue) throws JMSException {
/*  671 */           if (Trace.isOn) {
/*  672 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "createBrowser(Queue)", new Object[] { queue });
/*      */           }
/*      */           
/*  675 */           QueueBrowser traceRet1 = JmsXATopicSessionImpl.this.createBrowser(queue);
/*  676 */           if (Trace.isOn) {
/*  677 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createBrowser(Queue)", traceRet1);
/*      */           }
/*      */           
/*  680 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public QueueBrowser createBrowser(Queue arg0, String arg1) throws JMSException {
/*  684 */           if (Trace.isOn) {
/*  685 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "createBrowser(Queue,String)", new Object[] { arg0, arg1 });
/*      */           }
/*      */           
/*  688 */           QueueBrowser traceRet1 = JmsXATopicSessionImpl.this.createBrowser(arg0, arg1);
/*  689 */           if (Trace.isOn) {
/*  690 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createBrowser(Queue,String)", traceRet1);
/*      */           }
/*      */           
/*  693 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public BytesMessage createBytesMessage() throws JMSException {
/*  697 */           if (Trace.isOn) {
/*  698 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "createBytesMessage()");
/*      */           }
/*      */           
/*  701 */           BytesMessage traceRet1 = JmsXATopicSessionImpl.this.createBytesMessage();
/*  702 */           if (Trace.isOn) {
/*  703 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createBytesMessage()", traceRet1);
/*      */           }
/*      */           
/*  706 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public MessageConsumer createConsumer(Destination dest) throws JMSException {
/*  710 */           if (Trace.isOn) {
/*  711 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "createConsumer(Destination)", new Object[] { dest });
/*      */           }
/*      */           
/*  714 */           MessageConsumer traceRet1 = JmsXATopicSessionImpl.this.createConsumer(dest);
/*  715 */           if (Trace.isOn) {
/*  716 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createConsumer(Destination)", traceRet1);
/*      */           }
/*      */           
/*  719 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public MessageConsumer createConsumer(Destination dest, String selector) throws JMSException {
/*  723 */           if (Trace.isOn) {
/*  724 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "createConsumer(Destination,String)", new Object[] { dest, selector });
/*      */           }
/*      */           
/*  727 */           MessageConsumer traceRet1 = JmsXATopicSessionImpl.this.createConsumer(dest, selector);
/*  728 */           if (Trace.isOn) {
/*  729 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createConsumer(Destination,String)", traceRet1);
/*      */           }
/*      */           
/*  732 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public MessageConsumer createConsumer(Destination arg0, String arg1, boolean arg2) throws JMSException {
/*  736 */           if (Trace.isOn) {
/*  737 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "createConsumer(Destination,String,boolean)", new Object[] { arg0, arg1, 
/*      */                   
/*  739 */                   Boolean.valueOf(arg2) });
/*      */           }
/*  741 */           MessageConsumer traceRet1 = JmsXATopicSessionImpl.this.createConsumer(arg0, arg1, arg2);
/*  742 */           if (Trace.isOn) {
/*  743 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createConsumer(Destination,String,boolean)", traceRet1);
/*      */           }
/*      */           
/*  746 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public TopicSubscriber createDurableSubscriber(Topic topic, String subscriptionName) throws JMSException {
/*  750 */           if (Trace.isOn) {
/*  751 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "createDurableSubscriber(Topic,String)", new Object[] { topic, subscriptionName });
/*      */           }
/*      */ 
/*      */           
/*  755 */           TopicSubscriber traceRet1 = JmsXATopicSessionImpl.this.createDurableSubscriber(topic, subscriptionName);
/*  756 */           if (Trace.isOn) {
/*  757 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createDurableSubscriber(Topic,String)", traceRet1);
/*      */           }
/*      */           
/*  760 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public TopicSubscriber createDurableSubscriber(Topic arg0, String arg1, String arg2, boolean arg3) throws JMSException {
/*  764 */           if (Trace.isOn) {
/*  765 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "createDurableSubscriber(Topic,String,String,boolean)", new Object[] { arg0, arg1, arg2, 
/*      */                   
/*  767 */                   Boolean.valueOf(arg3) });
/*      */           }
/*      */           
/*  770 */           TopicSubscriber traceRet1 = JmsXATopicSessionImpl.this.createDurableSubscriber(arg0, arg1, arg2, arg3);
/*  771 */           if (Trace.isOn) {
/*  772 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createDurableSubscriber(Topic,String,String,boolean)", traceRet1);
/*      */           }
/*      */           
/*  775 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public MapMessage createMapMessage() throws JMSException {
/*  779 */           if (Trace.isOn) {
/*  780 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "createMapMessage()");
/*      */           }
/*      */           
/*  783 */           MapMessage traceRet1 = JmsXATopicSessionImpl.this.createMapMessage();
/*  784 */           if (Trace.isOn) {
/*  785 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createMapMessage()", traceRet1);
/*      */           }
/*      */           
/*  788 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public Message createMessage() throws JMSException {
/*  792 */           if (Trace.isOn) {
/*  793 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "createMessage()");
/*      */           }
/*      */           
/*  796 */           Message traceRet1 = JmsXATopicSessionImpl.this.createMessage();
/*  797 */           if (Trace.isOn) {
/*  798 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createMessage()", traceRet1);
/*      */           }
/*  800 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public ObjectMessage createObjectMessage() throws JMSException {
/*  804 */           if (Trace.isOn) {
/*  805 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "createObjectMessage()");
/*      */           }
/*      */           
/*  808 */           ObjectMessage traceRet1 = JmsXATopicSessionImpl.this.createObjectMessage();
/*  809 */           if (Trace.isOn) {
/*  810 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createObjectMessage()", traceRet1);
/*      */           }
/*      */           
/*  813 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public ObjectMessage createObjectMessage(Serializable obj) throws JMSException {
/*  817 */           if (Trace.isOn) {
/*  818 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "createObjectMessage(Serializable)", new Object[] { obj });
/*      */           }
/*      */           
/*  821 */           ObjectMessage traceRet1 = JmsXATopicSessionImpl.this.createObjectMessage(obj);
/*  822 */           if (Trace.isOn) {
/*  823 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createObjectMessage(Serializable)", traceRet1);
/*      */           }
/*      */           
/*  826 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public MessageProducer createProducer(Destination arg0) throws JMSException {
/*  830 */           if (Trace.isOn) {
/*  831 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "createProducer(Destination)", new Object[] { arg0 });
/*      */           }
/*      */           
/*  834 */           MessageProducer traceRet1 = JmsXATopicSessionImpl.this.createProducer(arg0);
/*  835 */           if (Trace.isOn) {
/*  836 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createProducer(Destination)", traceRet1);
/*      */           }
/*      */           
/*  839 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public Queue createQueue(String arg0) throws JMSException {
/*  843 */           if (Trace.isOn) {
/*  844 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "createQueue(String)", new Object[] { arg0 });
/*      */           }
/*      */           
/*  847 */           Queue traceRet1 = JmsXATopicSessionImpl.this.createQueue(arg0);
/*  848 */           if (Trace.isOn) {
/*  849 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createQueue(String)", traceRet1);
/*      */           }
/*      */           
/*  852 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public StreamMessage createStreamMessage() throws JMSException {
/*  856 */           if (Trace.isOn) {
/*  857 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "createStreamMessage()");
/*      */           }
/*      */           
/*  860 */           StreamMessage traceRet1 = JmsXATopicSessionImpl.this.createStreamMessage();
/*  861 */           if (Trace.isOn) {
/*  862 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createStreamMessage()", traceRet1);
/*      */           }
/*      */           
/*  865 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public TemporaryQueue createTemporaryQueue() throws JMSException {
/*  869 */           if (Trace.isOn) {
/*  870 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "createTemporaryQueue()");
/*      */           }
/*      */           
/*  873 */           TemporaryQueue traceRet1 = JmsXATopicSessionImpl.this.createTemporaryQueue();
/*  874 */           if (Trace.isOn) {
/*  875 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createTemporaryQueue()", traceRet1);
/*      */           }
/*      */           
/*  878 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public TemporaryTopic createTemporaryTopic() throws JMSException {
/*  882 */           if (Trace.isOn) {
/*  883 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "createTemporaryTopic()");
/*      */           }
/*      */           
/*  886 */           TemporaryTopic traceRet1 = JmsXATopicSessionImpl.this.createTemporaryTopic();
/*  887 */           if (Trace.isOn) {
/*  888 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createTemporaryTopic()", traceRet1);
/*      */           }
/*      */           
/*  891 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public TextMessage createTextMessage() throws JMSException {
/*  895 */           if (Trace.isOn) {
/*  896 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "createTextMessage()");
/*      */           }
/*      */           
/*  899 */           TextMessage traceRet1 = JmsXATopicSessionImpl.this.createTextMessage();
/*  900 */           if (Trace.isOn) {
/*  901 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createTextMessage()", traceRet1);
/*      */           }
/*      */           
/*  904 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public TextMessage createTextMessage(String arg0) throws JMSException {
/*  908 */           if (Trace.isOn) {
/*  909 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "createTextMessage(String)", new Object[] { arg0 });
/*      */           }
/*      */           
/*  912 */           TextMessage traceRet1 = JmsXATopicSessionImpl.this.createTextMessage(arg0);
/*  913 */           if (Trace.isOn) {
/*  914 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createTextMessage(String)", traceRet1);
/*      */           }
/*      */           
/*  917 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public Topic createTopic(String arg0) throws JMSException {
/*  921 */           if (Trace.isOn) {
/*  922 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "createTopic(String)", new Object[] { arg0 });
/*      */           }
/*      */           
/*  925 */           Topic traceRet1 = JmsXATopicSessionImpl.this.createTopic(arg0);
/*  926 */           if (Trace.isOn) {
/*  927 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createTopic(String)", traceRet1);
/*      */           }
/*      */           
/*  930 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public int getAcknowledgeMode() throws JMSException {
/*  934 */           if (Trace.isOn) {
/*  935 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "getAcknowledgeMode()");
/*      */           }
/*      */           
/*  938 */           int traceRet1 = JmsXATopicSessionImpl.this.getAcknowledgeMode();
/*  939 */           if (Trace.isOn) {
/*  940 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getAcknowledgeMode()", 
/*  941 */                 Integer.valueOf(traceRet1));
/*      */           }
/*  943 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public MessageListener getMessageListener() throws JMSException {
/*  947 */           if (Trace.isOn) {
/*  948 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "getMessageListener()");
/*      */           }
/*      */           
/*  951 */           MessageListener traceRet1 = JmsXATopicSessionImpl.this.getMessageListener();
/*  952 */           if (Trace.isOn) {
/*  953 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getMessageListener()", traceRet1);
/*      */           }
/*      */           
/*  956 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public boolean getTransacted() throws JMSException {
/*  960 */           if (Trace.isOn) {
/*  961 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "getTransacted()");
/*      */           }
/*      */           
/*  964 */           boolean traceRet1 = JmsXATopicSessionImpl.this.getTransacted();
/*  965 */           if (Trace.isOn) {
/*  966 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getTransacted()", 
/*  967 */                 Boolean.valueOf(traceRet1));
/*      */           }
/*  969 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public void recover() throws JMSException {
/*  973 */           if (Trace.isOn) {
/*  974 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "recover()");
/*      */           }
/*  976 */           JmsXATopicSessionImpl.this.recover();
/*  977 */           if (Trace.isOn) {
/*  978 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "recover()");
/*      */           }
/*      */         }
/*      */ 
/*      */         
/*      */         public void rollback() throws JMSException {
/*  984 */           if (Trace.isOn) {
/*  985 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "rollback()");
/*      */           }
/*      */           
/*  988 */           JmsXATopicSessionImpl.this.rollback();
/*  989 */           if (Trace.isOn) {
/*  990 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "rollback()");
/*      */           }
/*      */         }
/*      */ 
/*      */         
/*      */         public void run() {
/*  996 */           if (Trace.isOn) {
/*  997 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "run()");
/*      */           }
/*  999 */           JmsXATopicSessionImpl.this.run();
/* 1000 */           if (Trace.isOn) {
/* 1001 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "run()");
/*      */           }
/*      */         }
/*      */ 
/*      */         
/*      */         public void setMessageListener(MessageListener listener) throws JMSException {
/* 1007 */           if (Trace.isOn) {
/* 1008 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "setMessageListener(MessageListener)", new Object[] { listener });
/*      */           }
/*      */           
/* 1011 */           JmsXATopicSessionImpl.this.setMessageListener(listener);
/* 1012 */           if (Trace.isOn) {
/* 1013 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "setMessageListener(MessageListener)");
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public void unsubscribe(String arg0) throws JMSException {
/* 1020 */           if (Trace.isOn) {
/* 1021 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "unsubscribe(String)", new Object[] { arg0 });
/*      */           }
/*      */           
/* 1024 */           JmsXATopicSessionImpl.this.unsubscribe(arg0);
/* 1025 */           if (Trace.isOn) {
/* 1026 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "unsubscribe(String)");
/*      */           }
/*      */         }
/*      */ 
/*      */         
/*      */         public TopicPublisher createPublisher(Topic arg0) throws JMSException {
/* 1032 */           if (Trace.isOn) {
/* 1033 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "createPublisher(Topic)", new Object[] { arg0 });
/*      */           }
/*      */ 
/*      */           
/* 1037 */           TopicPublisher traceRet1 = (TopicPublisher)JmsXATopicSessionImpl.this.createProducer((Destination)arg0);
/* 1038 */           if (Trace.isOn) {
/* 1039 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createPublisher(Topic)", traceRet1);
/*      */           }
/*      */           
/* 1042 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public TopicSubscriber createSubscriber(Topic topic) throws JMSException {
/* 1046 */           if (Trace.isOn) {
/* 1047 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "createSubscriber(Topic)", new Object[] { topic });
/*      */           }
/*      */ 
/*      */           
/* 1051 */           TopicSubscriber traceRet1 = (TopicSubscriber)JmsXATopicSessionImpl.this.createConsumer((Destination)topic);
/* 1052 */           if (Trace.isOn) {
/* 1053 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createSubscriber(Topic)", traceRet1);
/*      */           }
/*      */           
/* 1056 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public TopicSubscriber createSubscriber(Topic arg0, String arg1, boolean arg2) throws JMSException {
/* 1060 */           if (Trace.isOn) {
/* 1061 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "createSubscriber(Topic,String,boolean)", new Object[] { arg0, arg1, 
/*      */                   
/* 1063 */                   Boolean.valueOf(arg2) });
/*      */           }
/*      */           
/* 1066 */           TopicSubscriber traceRet1 = (TopicSubscriber)JmsXATopicSessionImpl.this.createConsumer((Destination)arg0, arg1, arg2);
/* 1067 */           if (Trace.isOn) {
/* 1068 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createSubscriber(Topic,String,boolean)", traceRet1);
/*      */           }
/*      */           
/* 1071 */           return traceRet1;
/*      */         }
/*      */ 
/*      */         
/*      */         public void clearMessageReferences() {
/* 1076 */           if (Trace.isOn) {
/* 1077 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "clearMessageReferences()");
/*      */           }
/*      */           
/* 1080 */           if (Trace.isOn) {
/* 1081 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "clearMessageReferences()");
/*      */           }
/*      */           
/* 1084 */           JmsXATopicSessionImpl.this.clearMessageReferences();
/* 1085 */           if (Trace.isOn) {
/* 1086 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "clearMessageReferences()");
/*      */           }
/*      */           
/* 1089 */           if (Trace.isOn) {
/* 1090 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "clearMessageReferences()");
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
/* 1102 */           if (Trace.isOn) {
/* 1103 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "createDurableConsumer(Topic,String)", new Object[] { topic, name });
/*      */           }
/*      */ 
/*      */           
/* 1107 */           MessageConsumer traceRet1 = JmsXATopicSessionImpl.this.createDurableConsumer(topic, name);
/* 1108 */           if (Trace.isOn) {
/* 1109 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createDurableConsumer(Topic,String)", traceRet1);
/*      */           }
/*      */           
/* 1112 */           return traceRet1;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public MessageConsumer createDurableConsumer(Topic topic, String name, String selector, boolean noLocal) throws InvalidDestinationException, InvalidSelectorException, IllegalStateException, JMSException {
/* 1119 */           if (Trace.isOn) {
/* 1120 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "createDurableConsumer(Topic,String,String,boolean)", new Object[] { topic, name, selector, 
/*      */                   
/* 1122 */                   Boolean.valueOf(noLocal) });
/*      */           }
/*      */           
/* 1125 */           MessageConsumer traceRet1 = JmsXATopicSessionImpl.this.createDurableConsumer(topic, name, selector, noLocal);
/* 1126 */           if (Trace.isOn) {
/* 1127 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createDurableConsumer(Topic,String,String,boolean)", traceRet1);
/*      */           }
/*      */           
/* 1130 */           return traceRet1;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public MessageConsumer createSharedConsumer(Topic topic, String name) throws JMSException, InvalidDestinationException, InvalidSelectorException {
/* 1137 */           if (Trace.isOn) {
/* 1138 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "createSharedConsumer(Topic,String)", new Object[] { topic, name });
/*      */           }
/*      */ 
/*      */           
/* 1142 */           MessageConsumer traceRet1 = JmsXATopicSessionImpl.this.createSharedConsumer(topic, name);
/* 1143 */           if (Trace.isOn) {
/* 1144 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createSharedConsumer(Topic,String)", traceRet1);
/*      */           }
/*      */           
/* 1147 */           return traceRet1;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public MessageConsumer createSharedConsumer(Topic topic, String name, String selector) throws JMSException, InvalidDestinationException, InvalidSelectorException {
/* 1154 */           if (Trace.isOn) {
/* 1155 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "createSharedConsumer(Topic,String,String)", new Object[] { topic, name, selector });
/*      */           }
/*      */ 
/*      */           
/* 1159 */           MessageConsumer traceRet1 = JmsXATopicSessionImpl.this.createSharedConsumer(topic, name, selector);
/* 1160 */           if (Trace.isOn) {
/* 1161 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createSharedConsumer(Topic,String,String)", traceRet1);
/*      */           }
/*      */           
/* 1164 */           return traceRet1;
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public MessageConsumer createSharedDurableConsumer(Topic topic, String name) throws JMSException, InvalidDestinationException {
/* 1170 */           if (Trace.isOn) {
/* 1171 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "createSharedDurableConsumer(Topic,String)", new Object[] { topic, name });
/*      */           }
/*      */ 
/*      */           
/* 1175 */           MessageConsumer traceRet1 = JmsXATopicSessionImpl.this.createSharedDurableConsumer(topic, name);
/* 1176 */           if (Trace.isOn) {
/* 1177 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createSharedDurableConsumer(Topic,String)", traceRet1);
/*      */           }
/*      */           
/* 1180 */           return traceRet1;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public MessageConsumer createSharedDurableConsumer(Topic topic, String name, String selector) throws InvalidDestinationException, IllegalStateException, JMSException {
/* 1187 */           if (Trace.isOn) {
/* 1188 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "createSharedDurableConsumer(Topic,String,String)", new Object[] { topic, name, selector });
/*      */           }
/*      */ 
/*      */           
/* 1192 */           MessageConsumer traceRet1 = JmsXATopicSessionImpl.this.createSharedDurableConsumer(topic, name, selector);
/* 1193 */           if (Trace.isOn) {
/* 1194 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createSharedDurableConsumer(Topic,String,String)", traceRet1);
/*      */           }
/*      */           
/* 1197 */           return traceRet1;
/*      */         }
/*      */       };
/*      */ 
/*      */ 
/*      */     
/* 1203 */     TopicSession traceRet2 = (TopicSession)jmsTopicSession;
/* 1204 */     if (Trace.isOn) {
/* 1205 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl", "getTopicSession()", traceRet2);
/*      */     }
/*      */     
/* 1208 */     return traceRet2;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsXATopicSessionImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */