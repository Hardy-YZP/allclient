/*      */ package com.ibm.msg.client.jms.internal;
/*      */ 
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.jms.JmsMessageReference;
/*      */ import com.ibm.msg.client.jms.JmsQueueSession;
/*      */ import com.ibm.msg.client.jms.JmsXAQueueSession;
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
/*      */ import javax.jms.QueueReceiver;
/*      */ import javax.jms.QueueSender;
/*      */ import javax.jms.QueueSession;
/*      */ import javax.jms.StreamMessage;
/*      */ import javax.jms.TemporaryQueue;
/*      */ import javax.jms.TemporaryTopic;
/*      */ import javax.jms.TextMessage;
/*      */ import javax.jms.Topic;
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
/*      */ public class JmsXAQueueSessionImpl
/*      */   extends JmsXASessionImpl
/*      */   implements JmsXAQueueSession
/*      */ {
/*      */   private static final long serialVersionUID = 3599902885610897632L;
/*      */   static final String sccsid2 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsXAQueueSessionImpl.java";
/*      */   
/*      */   static {
/*   73 */     if (Trace.isOn) {
/*   74 */       Trace.data("com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsXAQueueSessionImpl.java");
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
/*      */   protected JmsXAQueueSessionImpl(JmsXAQueueConnectionImpl connection) throws JMSException {
/*   90 */     super(connection);
/*   91 */     if (Trace.isOn) {
/*   92 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "<init>(JmsXAQueueConnectionImpl)", new Object[] { connection });
/*      */     }
/*      */     
/*   95 */     if (Trace.isOn) {
/*   96 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "<init>(JmsXAQueueConnectionImpl)");
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
/*      */   
/*      */   public QueueSession getQueueSession() throws JMSException {
/*  130 */     if (Trace.isOn) {
/*  131 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "getQueueSession()");
/*      */     }
/*      */     
/*  134 */     if (Trace.isOn) {
/*  135 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "getQueueSession()", "creating new JmsQueueSession() for use with this XA session");
/*      */     }
/*      */     
/*  138 */     JmsQueueSession jmsQueueSession = new JmsQueueSession()
/*      */       {
/*      */         private static final long serialVersionUID = 211174869885230826L;
/*      */         
/*      */         public Message consume(byte[] flatMR) throws JMSException {
/*  143 */           if (Trace.isOn) {
/*  144 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "consume(byte [ ])", new Object[] { flatMR });
/*      */           }
/*      */           
/*  147 */           Message traceRet1 = JmsXAQueueSessionImpl.this.consume(flatMR);
/*  148 */           if (Trace.isOn) {
/*  149 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "consume(byte [ ])", traceRet1);
/*      */           }
/*      */           
/*  152 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public void deliver(List<JmsMessageReference> arg0) throws JMSException {
/*  156 */           if (Trace.isOn) {
/*  157 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "deliver(List<JmsMessageReference>)", new Object[] { arg0 });
/*      */           }
/*      */           
/*  160 */           JmsXAQueueSessionImpl.this.deliver(arg0);
/*  161 */           if (Trace.isOn) {
/*  162 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "deliver(List<JmsMessageReference>)");
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public JmsMessageReference recreateMessageReference(byte[] arg0) throws JMSException {
/*  169 */           if (Trace.isOn) {
/*  170 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "recreateMessageReference(byte [ ])", new Object[] { arg0 });
/*      */           }
/*      */           
/*  173 */           JmsMessageReference traceRet1 = JmsXAQueueSessionImpl.this.recreateMessageReference(arg0);
/*  174 */           if (Trace.isOn) {
/*  175 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "recreateMessageReference(byte [ ])", traceRet1);
/*      */           }
/*      */           
/*  178 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public void setBatchProperties(Map<String, Object> arg0) throws JMSException {
/*  182 */           if (Trace.isOn) {
/*  183 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "setBatchProperties(Map<String , Object>)", new Object[] { arg0 });
/*      */           }
/*      */           
/*  186 */           JmsXAQueueSessionImpl.this.setBatchProperties(arg0);
/*  187 */           if (Trace.isOn) {
/*  188 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "setBatchProperties(Map<String , Object>)");
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public void setBooleanProperty(String name, boolean value) throws JMSException {
/*  195 */           if (Trace.isOn) {
/*  196 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "setBooleanProperty(String,boolean)", new Object[] { name, 
/*  197 */                   Boolean.valueOf(value) });
/*      */           }
/*  199 */           JmsXAQueueSessionImpl.this.setBooleanProperty(name, value);
/*  200 */           if (Trace.isOn) {
/*  201 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "setBooleanProperty(String,boolean)");
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public void setByteProperty(String name, byte value) throws JMSException {
/*  208 */           if (Trace.isOn) {
/*  209 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "setByteProperty(String,byte)", new Object[] { name, 
/*  210 */                   Byte.valueOf(value) });
/*      */           }
/*  212 */           JmsXAQueueSessionImpl.this.setByteProperty(name, value);
/*  213 */           if (Trace.isOn) {
/*  214 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "setByteProperty(String,byte)");
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public void setBytesProperty(String name, byte[] value) throws JMSException {
/*  221 */           if (Trace.isOn) {
/*  222 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "setBytesProperty(String,byte [ ])", new Object[] { name, value });
/*      */           }
/*      */           
/*  225 */           JmsXAQueueSessionImpl.this.setBytesProperty(name, value);
/*  226 */           if (Trace.isOn) {
/*  227 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "setBytesProperty(String,byte [ ])");
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public void setCharProperty(String name, char value) throws JMSException {
/*  234 */           if (Trace.isOn) {
/*  235 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "setCharProperty(String,char)", new Object[] { name, 
/*  236 */                   Character.valueOf(value) });
/*      */           }
/*  238 */           JmsXAQueueSessionImpl.this.setCharProperty(name, value);
/*  239 */           if (Trace.isOn) {
/*  240 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "setCharProperty(String,char)");
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public void setDoubleProperty(String name, double value) throws JMSException {
/*  247 */           if (Trace.isOn) {
/*  248 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "setDoubleProperty(String,double)", new Object[] { name, 
/*  249 */                   Double.valueOf(value) });
/*      */           }
/*  251 */           JmsXAQueueSessionImpl.this.setDoubleProperty(name, value);
/*  252 */           if (Trace.isOn) {
/*  253 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "setDoubleProperty(String,double)");
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public void setFloatProperty(String name, float value) throws JMSException {
/*  260 */           if (Trace.isOn) {
/*  261 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "setFloatProperty(String,float)", new Object[] { name, 
/*  262 */                   Float.valueOf(value) });
/*      */           }
/*  264 */           JmsXAQueueSessionImpl.this.setFloatProperty(name, value);
/*  265 */           if (Trace.isOn) {
/*  266 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "setFloatProperty(String,float)");
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public void setIntProperty(String name, int value) throws JMSException {
/*  273 */           if (Trace.isOn) {
/*  274 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "setIntProperty(String,int)", new Object[] { name, 
/*  275 */                   Integer.valueOf(value) });
/*      */           }
/*  277 */           JmsXAQueueSessionImpl.this.setIntProperty(name, value);
/*  278 */           if (Trace.isOn) {
/*  279 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "setIntProperty(String,int)");
/*      */           }
/*      */         }
/*      */ 
/*      */         
/*      */         public void setLongProperty(String name, long value) throws JMSException {
/*  285 */           if (Trace.isOn) {
/*  286 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "setLongProperty(String,long)", new Object[] { name, 
/*  287 */                   Long.valueOf(value) });
/*      */           }
/*  289 */           JmsXAQueueSessionImpl.this.setLongProperty(name, value);
/*  290 */           if (Trace.isOn) {
/*  291 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "setLongProperty(String,long)");
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public void setObjectProperty(String arg0, Object arg1) throws JMSException {
/*  298 */           if (Trace.isOn) {
/*  299 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "setObjectProperty(String,Object)", new Object[] { arg0, arg1 });
/*      */           }
/*      */           
/*  302 */           JmsXAQueueSessionImpl.this.setObjectProperty(arg0, arg1);
/*  303 */           if (Trace.isOn) {
/*  304 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "setObjectProperty(String,Object)");
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public void setShortProperty(String name, short value) throws JMSException {
/*  311 */           if (Trace.isOn) {
/*  312 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "setShortProperty(String,short)", new Object[] { name, 
/*  313 */                   Short.valueOf(value) });
/*      */           }
/*  315 */           JmsXAQueueSessionImpl.this.setShortProperty(name, value);
/*  316 */           if (Trace.isOn) {
/*  317 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "setShortProperty(String,short)");
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public void setStringProperty(String name, String value) throws JMSException {
/*  324 */           if (Trace.isOn) {
/*  325 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "setStringProperty(String,String)", new Object[] { name, value });
/*      */           }
/*      */           
/*  328 */           JmsXAQueueSessionImpl.this.setStringProperty(name, value);
/*  329 */           if (Trace.isOn) {
/*  330 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "setStringProperty(String,String)");
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public boolean getBooleanProperty(String name) throws JMSException {
/*  337 */           if (Trace.isOn) {
/*  338 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "getBooleanProperty(String)", new Object[] { name });
/*      */           }
/*      */           
/*  341 */           boolean traceRet1 = JmsXAQueueSessionImpl.this.getBooleanProperty(name);
/*  342 */           if (Trace.isOn) {
/*  343 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getBooleanProperty(String)", 
/*  344 */                 Boolean.valueOf(traceRet1));
/*      */           }
/*  346 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public byte getByteProperty(String name) throws JMSException {
/*  350 */           if (Trace.isOn) {
/*  351 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "getByteProperty(String)", new Object[] { name });
/*      */           }
/*      */           
/*  354 */           byte traceRet1 = JmsXAQueueSessionImpl.this.getByteProperty(name);
/*  355 */           if (Trace.isOn) {
/*  356 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getByteProperty(String)", 
/*  357 */                 Byte.valueOf(traceRet1));
/*      */           }
/*  359 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public byte[] getBytesProperty(String name) throws JMSException {
/*  363 */           if (Trace.isOn) {
/*  364 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "getBytesProperty(String)", new Object[] { name });
/*      */           }
/*      */           
/*  367 */           byte[] traceRet1 = JmsXAQueueSessionImpl.this.getBytesProperty(name);
/*  368 */           if (Trace.isOn) {
/*  369 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getBytesProperty(String)", traceRet1);
/*      */           }
/*      */           
/*  372 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public char getCharProperty(String arg0) throws JMSException {
/*  376 */           if (Trace.isOn) {
/*  377 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "getCharProperty(String)", new Object[] { arg0 });
/*      */           }
/*      */           
/*  380 */           char traceRet1 = JmsXAQueueSessionImpl.this.getCharProperty(arg0);
/*  381 */           if (Trace.isOn) {
/*  382 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getCharProperty(String)", 
/*  383 */                 Character.valueOf(traceRet1));
/*      */           }
/*  385 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public double getDoubleProperty(String name) throws JMSException {
/*  389 */           if (Trace.isOn) {
/*  390 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "getDoubleProperty(String)", new Object[] { name });
/*      */           }
/*      */           
/*  393 */           double traceRet1 = JmsXAQueueSessionImpl.this.getDoubleProperty(name);
/*  394 */           if (Trace.isOn) {
/*  395 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getDoubleProperty(String)", 
/*  396 */                 Double.valueOf(traceRet1));
/*      */           }
/*  398 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public float getFloatProperty(String name) throws JMSException {
/*  402 */           if (Trace.isOn) {
/*  403 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "getFloatProperty(String)", new Object[] { name });
/*      */           }
/*      */           
/*  406 */           float traceRet1 = JmsXAQueueSessionImpl.this.getFloatProperty(name);
/*  407 */           if (Trace.isOn) {
/*  408 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getFloatProperty(String)", 
/*  409 */                 Float.valueOf(traceRet1));
/*      */           }
/*  411 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public int getIntProperty(String name) throws JMSException {
/*  415 */           if (Trace.isOn) {
/*  416 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "getIntProperty(String)", new Object[] { name });
/*      */           }
/*      */           
/*  419 */           int traceRet1 = JmsXAQueueSessionImpl.this.getIntProperty(name);
/*  420 */           if (Trace.isOn) {
/*  421 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getIntProperty(String)", 
/*  422 */                 Integer.valueOf(traceRet1));
/*      */           }
/*  424 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public long getLongProperty(String name) throws JMSException {
/*  428 */           if (Trace.isOn) {
/*  429 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "getLongProperty(String)", new Object[] { name });
/*      */           }
/*      */           
/*  432 */           long traceRet1 = JmsXAQueueSessionImpl.this.getLongProperty(name);
/*  433 */           if (Trace.isOn) {
/*  434 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getLongProperty(String)", 
/*  435 */                 Long.valueOf(traceRet1));
/*      */           }
/*  437 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public Object getObjectProperty(String arg0) throws JMSException {
/*  441 */           if (Trace.isOn) {
/*  442 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "getObjectProperty(String)", new Object[] { arg0 });
/*      */           }
/*      */           
/*  445 */           Object traceRet1 = JmsXAQueueSessionImpl.this.getObjectProperty(arg0);
/*  446 */           if (Trace.isOn) {
/*  447 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getObjectProperty(String)", traceRet1);
/*      */           }
/*      */           
/*  450 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public Enumeration<String> getPropertyNames() throws JMSException {
/*  454 */           if (Trace.isOn) {
/*  455 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "getPropertyNames()");
/*      */           }
/*      */           
/*  458 */           Enumeration<String> traceRet1 = JmsXAQueueSessionImpl.this.getPropertyNames();
/*  459 */           if (Trace.isOn) {
/*  460 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getPropertyNames()", traceRet1);
/*      */           }
/*      */           
/*  463 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public short getShortProperty(String name) throws JMSException {
/*  467 */           if (Trace.isOn) {
/*  468 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "getShortProperty(String)", new Object[] { name });
/*      */           }
/*      */           
/*  471 */           short traceRet1 = JmsXAQueueSessionImpl.this.getShortProperty(name);
/*  472 */           if (Trace.isOn) {
/*  473 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getShortProperty(String)", 
/*  474 */                 Short.valueOf(traceRet1));
/*      */           }
/*  476 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public String getStringProperty(String name) throws JMSException {
/*  480 */           if (Trace.isOn) {
/*  481 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "getStringProperty(String)", new Object[] { name });
/*      */           }
/*      */           
/*  484 */           String traceRet1 = JmsXAQueueSessionImpl.this.getStringProperty(name);
/*  485 */           if (Trace.isOn) {
/*  486 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getStringProperty(String)", traceRet1);
/*      */           }
/*      */           
/*  489 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public boolean propertyExists(String name) throws JMSException {
/*  493 */           if (Trace.isOn) {
/*  494 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "propertyExists(String)", new Object[] { name });
/*      */           }
/*      */           
/*  497 */           boolean traceRet1 = JmsXAQueueSessionImpl.this.propertyExists(name);
/*  498 */           if (Trace.isOn) {
/*  499 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "propertyExists(String)", 
/*  500 */                 Boolean.valueOf(traceRet1));
/*      */           }
/*  502 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public void clear() {
/*  506 */           if (Trace.isOn) {
/*  507 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "clear()");
/*      */           }
/*  509 */           JmsXAQueueSessionImpl.this.clear();
/*  510 */           if (Trace.isOn) {
/*  511 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "clear()");
/*      */           }
/*      */         }
/*      */ 
/*      */         
/*      */         public boolean containsKey(Object key) {
/*  517 */           if (Trace.isOn) {
/*  518 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "containsKey(Object)", new Object[] { key });
/*      */           }
/*      */           
/*  521 */           boolean traceRet1 = JmsXAQueueSessionImpl.this.containsKey(key);
/*  522 */           if (Trace.isOn) {
/*  523 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "containsKey(Object)", 
/*  524 */                 Boolean.valueOf(traceRet1));
/*      */           }
/*  526 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public boolean containsValue(Object value) {
/*  530 */           if (Trace.isOn) {
/*  531 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "containsValue(Object)", new Object[] { value });
/*      */           }
/*      */           
/*  534 */           boolean traceRet1 = JmsXAQueueSessionImpl.this.containsValue(value);
/*  535 */           if (Trace.isOn) {
/*  536 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "containsValue(Object)", 
/*  537 */                 Boolean.valueOf(traceRet1));
/*      */           }
/*  539 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public Set<Map.Entry<String, Object>> entrySet() {
/*  543 */           if (Trace.isOn) {
/*  544 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "entrySet()");
/*      */           }
/*      */           
/*  547 */           Set<Map.Entry<String, Object>> traceRet1 = JmsXAQueueSessionImpl.this.entrySet();
/*  548 */           if (Trace.isOn) {
/*  549 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "entrySet()", traceRet1);
/*      */           }
/*  551 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public Object get(Object key) {
/*  555 */           if (Trace.isOn) {
/*  556 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "get(Object)", new Object[] { key });
/*      */           }
/*      */           
/*  559 */           Object traceRet1 = JmsXAQueueSessionImpl.this.get(key);
/*  560 */           if (Trace.isOn) {
/*  561 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "get(Object)", traceRet1);
/*      */           }
/*  563 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public boolean isEmpty() {
/*  567 */           if (Trace.isOn) {
/*  568 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "isEmpty()");
/*      */           }
/*  570 */           boolean traceRet1 = JmsXAQueueSessionImpl.this.isEmpty();
/*  571 */           if (Trace.isOn) {
/*  572 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "isEmpty()", 
/*  573 */                 Boolean.valueOf(traceRet1));
/*      */           }
/*  575 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public Set<String> keySet() {
/*  579 */           if (Trace.isOn) {
/*  580 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "keySet()");
/*      */           }
/*  582 */           Set<String> traceRet1 = JmsXAQueueSessionImpl.this.keySet();
/*  583 */           if (Trace.isOn) {
/*  584 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "keySet()", traceRet1);
/*      */           }
/*  586 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public Object put(String key, Object value) {
/*  590 */           if (Trace.isOn) {
/*  591 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "put(String,Object)", new Object[] { key, value });
/*      */           }
/*      */           
/*  594 */           Object traceRet1 = JmsXAQueueSessionImpl.this.put(key, value);
/*  595 */           if (Trace.isOn) {
/*  596 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "put(String,Object)", traceRet1);
/*      */           }
/*      */           
/*  599 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public void putAll(Map<? extends String, ? extends Object> t) {
/*  603 */           if (Trace.isOn) {
/*  604 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "putAll(Map<? extends String , ? extends Object>)", new Object[] { t });
/*      */           }
/*      */           
/*  607 */           JmsXAQueueSessionImpl.this.putAll(t);
/*  608 */           if (Trace.isOn) {
/*  609 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "putAll(Map<? extends String , ? extends Object>)");
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public Object remove(Object key) {
/*  616 */           if (Trace.isOn) {
/*  617 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "remove(Object)", new Object[] { key });
/*      */           }
/*      */           
/*  620 */           Object traceRet1 = JmsXAQueueSessionImpl.this.remove(key);
/*  621 */           if (Trace.isOn) {
/*  622 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "remove(Object)", traceRet1);
/*      */           }
/*  624 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public int size() {
/*  628 */           if (Trace.isOn) {
/*  629 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "size()");
/*      */           }
/*  631 */           int traceRet1 = JmsXAQueueSessionImpl.this.size();
/*  632 */           if (Trace.isOn) {
/*  633 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "size()", 
/*  634 */                 Integer.valueOf(traceRet1));
/*      */           }
/*  636 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public Collection<Object> values() {
/*  640 */           if (Trace.isOn) {
/*  641 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "values()");
/*      */           }
/*  643 */           Collection<Object> traceRet1 = JmsXAQueueSessionImpl.this.values();
/*  644 */           if (Trace.isOn) {
/*  645 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "values()", traceRet1);
/*      */           }
/*  647 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public void close() throws JMSException {
/*  651 */           if (Trace.isOn) {
/*  652 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "close()");
/*      */           }
/*  654 */           JmsXAQueueSessionImpl.this.close();
/*  655 */           if (Trace.isOn) {
/*  656 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "close()");
/*      */           }
/*      */         }
/*      */ 
/*      */         
/*      */         public void commit() throws JMSException {
/*  662 */           if (Trace.isOn) {
/*  663 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "commit()");
/*      */           }
/*  665 */           JmsXAQueueSessionImpl.this.commit();
/*  666 */           if (Trace.isOn) {
/*  667 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "commit()");
/*      */           }
/*      */         }
/*      */ 
/*      */         
/*      */         public QueueBrowser createBrowser(Queue queue) throws JMSException {
/*  673 */           if (Trace.isOn) {
/*  674 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "createBrowser(Queue)", new Object[] { queue });
/*      */           }
/*      */           
/*  677 */           QueueBrowser traceRet1 = JmsXAQueueSessionImpl.this.createBrowser(queue);
/*  678 */           if (Trace.isOn) {
/*  679 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createBrowser(Queue)", traceRet1);
/*      */           }
/*      */           
/*  682 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public QueueBrowser createBrowser(Queue arg0, String arg1) throws JMSException {
/*  686 */           if (Trace.isOn) {
/*  687 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "createBrowser(Queue,String)", new Object[] { arg0, arg1 });
/*      */           }
/*      */           
/*  690 */           QueueBrowser traceRet1 = JmsXAQueueSessionImpl.this.createBrowser(arg0, arg1);
/*  691 */           if (Trace.isOn) {
/*  692 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createBrowser(Queue,String)", traceRet1);
/*      */           }
/*      */           
/*  695 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public BytesMessage createBytesMessage() throws JMSException {
/*  699 */           if (Trace.isOn) {
/*  700 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "createBytesMessage()");
/*      */           }
/*      */           
/*  703 */           BytesMessage traceRet1 = JmsXAQueueSessionImpl.this.createBytesMessage();
/*  704 */           if (Trace.isOn) {
/*  705 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createBytesMessage()", traceRet1);
/*      */           }
/*      */           
/*  708 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public MessageConsumer createConsumer(Destination dest) throws JMSException {
/*  712 */           if (Trace.isOn) {
/*  713 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "createConsumer(Destination)", new Object[] { dest });
/*      */           }
/*      */           
/*  716 */           MessageConsumer traceRet1 = JmsXAQueueSessionImpl.this.createConsumer(dest);
/*  717 */           if (Trace.isOn) {
/*  718 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createConsumer(Destination)", traceRet1);
/*      */           }
/*      */           
/*  721 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public MessageConsumer createConsumer(Destination dest, String selector) throws JMSException {
/*  725 */           if (Trace.isOn) {
/*  726 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "createConsumer(Destination,String)", new Object[] { dest, selector });
/*      */           }
/*      */           
/*  729 */           MessageConsumer traceRet1 = JmsXAQueueSessionImpl.this.createConsumer(dest, selector);
/*  730 */           if (Trace.isOn) {
/*  731 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createConsumer(Destination,String)", traceRet1);
/*      */           }
/*      */           
/*  734 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public MessageConsumer createConsumer(Destination arg0, String arg1, boolean arg2) throws JMSException {
/*  738 */           if (Trace.isOn) {
/*  739 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "createConsumer(Destination,String,boolean)", new Object[] { arg0, arg1, 
/*      */                   
/*  741 */                   Boolean.valueOf(arg2) });
/*      */           }
/*  743 */           MessageConsumer traceRet1 = JmsXAQueueSessionImpl.this.createConsumer(arg0, arg1, arg2);
/*  744 */           if (Trace.isOn) {
/*  745 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createConsumer(Destination,String,boolean)", traceRet1);
/*      */           }
/*      */           
/*  748 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public TopicSubscriber createDurableSubscriber(Topic topic, String subscriptionName) throws JMSException {
/*  752 */           if (Trace.isOn) {
/*  753 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "createDurableSubscriber(Topic,String)", new Object[] { topic, subscriptionName });
/*      */           }
/*      */ 
/*      */           
/*  757 */           TopicSubscriber traceRet1 = JmsXAQueueSessionImpl.this.createDurableSubscriber(topic, subscriptionName);
/*  758 */           if (Trace.isOn) {
/*  759 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createDurableSubscriber(Topic,String)", traceRet1);
/*      */           }
/*      */           
/*  762 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public TopicSubscriber createDurableSubscriber(Topic arg0, String arg1, String arg2, boolean arg3) throws JMSException {
/*  766 */           if (Trace.isOn) {
/*  767 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "createDurableSubscriber(Topic,String,String,boolean)", new Object[] { arg0, arg1, arg2, 
/*      */                   
/*  769 */                   Boolean.valueOf(arg3) });
/*      */           }
/*      */           
/*  772 */           TopicSubscriber traceRet1 = JmsXAQueueSessionImpl.this.createDurableSubscriber(arg0, arg1, arg2, arg3);
/*  773 */           if (Trace.isOn) {
/*  774 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createDurableSubscriber(Topic,String,String,boolean)", traceRet1);
/*      */           }
/*      */           
/*  777 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public MapMessage createMapMessage() throws JMSException {
/*  781 */           if (Trace.isOn) {
/*  782 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "createMapMessage()");
/*      */           }
/*      */           
/*  785 */           MapMessage traceRet1 = JmsXAQueueSessionImpl.this.createMapMessage();
/*  786 */           if (Trace.isOn) {
/*  787 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createMapMessage()", traceRet1);
/*      */           }
/*      */           
/*  790 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public Message createMessage() throws JMSException {
/*  794 */           if (Trace.isOn) {
/*  795 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "createMessage()");
/*      */           }
/*      */           
/*  798 */           Message traceRet1 = JmsXAQueueSessionImpl.this.createMessage();
/*  799 */           if (Trace.isOn) {
/*  800 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createMessage()", traceRet1);
/*      */           }
/*  802 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public ObjectMessage createObjectMessage() throws JMSException {
/*  806 */           if (Trace.isOn) {
/*  807 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "createObjectMessage()");
/*      */           }
/*      */           
/*  810 */           ObjectMessage traceRet1 = JmsXAQueueSessionImpl.this.createObjectMessage();
/*  811 */           if (Trace.isOn) {
/*  812 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createObjectMessage()", traceRet1);
/*      */           }
/*      */           
/*  815 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public ObjectMessage createObjectMessage(Serializable obj) throws JMSException {
/*  819 */           if (Trace.isOn) {
/*  820 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "createObjectMessage(Serializable)", new Object[] { obj });
/*      */           }
/*      */           
/*  823 */           ObjectMessage traceRet1 = JmsXAQueueSessionImpl.this.createObjectMessage(obj);
/*  824 */           if (Trace.isOn) {
/*  825 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createObjectMessage(Serializable)", traceRet1);
/*      */           }
/*      */           
/*  828 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public MessageProducer createProducer(Destination arg0) throws JMSException {
/*  832 */           if (Trace.isOn) {
/*  833 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "createProducer(Destination)", new Object[] { arg0 });
/*      */           }
/*      */           
/*  836 */           MessageProducer traceRet1 = JmsXAQueueSessionImpl.this.createProducer(arg0);
/*  837 */           if (Trace.isOn) {
/*  838 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createProducer(Destination)", traceRet1);
/*      */           }
/*      */           
/*  841 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public Queue createQueue(String arg0) throws JMSException {
/*  845 */           if (Trace.isOn) {
/*  846 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "createQueue(String)", new Object[] { arg0 });
/*      */           }
/*      */           
/*  849 */           Queue traceRet1 = JmsXAQueueSessionImpl.this.createQueue(arg0);
/*  850 */           if (Trace.isOn) {
/*  851 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createQueue(String)", traceRet1);
/*      */           }
/*      */           
/*  854 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public StreamMessage createStreamMessage() throws JMSException {
/*  858 */           if (Trace.isOn) {
/*  859 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "createStreamMessage()");
/*      */           }
/*      */           
/*  862 */           StreamMessage traceRet1 = JmsXAQueueSessionImpl.this.createStreamMessage();
/*  863 */           if (Trace.isOn) {
/*  864 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createStreamMessage()", traceRet1);
/*      */           }
/*      */           
/*  867 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public TemporaryQueue createTemporaryQueue() throws JMSException {
/*  871 */           if (Trace.isOn) {
/*  872 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "createTemporaryQueue()");
/*      */           }
/*      */           
/*  875 */           TemporaryQueue traceRet1 = JmsXAQueueSessionImpl.this.createTemporaryQueue();
/*  876 */           if (Trace.isOn) {
/*  877 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createTemporaryQueue()", traceRet1);
/*      */           }
/*      */           
/*  880 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public TemporaryTopic createTemporaryTopic() throws JMSException {
/*  884 */           if (Trace.isOn) {
/*  885 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "createTemporaryTopic()");
/*      */           }
/*      */           
/*  888 */           TemporaryTopic traceRet1 = JmsXAQueueSessionImpl.this.createTemporaryTopic();
/*  889 */           if (Trace.isOn) {
/*  890 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createTemporaryTopic()", traceRet1);
/*      */           }
/*      */           
/*  893 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public TextMessage createTextMessage() throws JMSException {
/*  897 */           if (Trace.isOn) {
/*  898 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "createTextMessage()");
/*      */           }
/*      */           
/*  901 */           TextMessage traceRet1 = JmsXAQueueSessionImpl.this.createTextMessage();
/*  902 */           if (Trace.isOn) {
/*  903 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createTextMessage()", traceRet1);
/*      */           }
/*      */           
/*  906 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public TextMessage createTextMessage(String arg0) throws JMSException {
/*  910 */           if (Trace.isOn) {
/*  911 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "createTextMessage(String)", new Object[] { arg0 });
/*      */           }
/*      */           
/*  914 */           TextMessage traceRet1 = JmsXAQueueSessionImpl.this.createTextMessage(arg0);
/*  915 */           if (Trace.isOn) {
/*  916 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createTextMessage(String)", traceRet1);
/*      */           }
/*      */           
/*  919 */           return traceRet1;
/*      */         }
/*      */ 
/*      */         
/*      */         public Topic createTopic(String arg0) throws JMSException {
/*  924 */           if (Trace.isOn) {
/*  925 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "createTopic(String)", new Object[] { arg0 });
/*      */           }
/*      */           
/*  928 */           Topic traceRet1 = JmsXAQueueSessionImpl.this.createTopic(arg0);
/*  929 */           if (Trace.isOn) {
/*  930 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createTopic(String)", traceRet1);
/*      */           }
/*      */           
/*  933 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public int getAcknowledgeMode() throws JMSException {
/*  937 */           if (Trace.isOn) {
/*  938 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "getAcknowledgeMode()");
/*      */           }
/*      */           
/*  941 */           int traceRet1 = JmsXAQueueSessionImpl.this.getAcknowledgeMode();
/*  942 */           if (Trace.isOn) {
/*  943 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getAcknowledgeMode()", 
/*  944 */                 Integer.valueOf(traceRet1));
/*      */           }
/*  946 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public MessageListener getMessageListener() throws JMSException {
/*  950 */           if (Trace.isOn) {
/*  951 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "getMessageListener()");
/*      */           }
/*      */           
/*  954 */           MessageListener traceRet1 = JmsXAQueueSessionImpl.this.getMessageListener();
/*  955 */           if (Trace.isOn) {
/*  956 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getMessageListener()", traceRet1);
/*      */           }
/*      */           
/*  959 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public boolean getTransacted() throws JMSException {
/*  963 */           if (Trace.isOn) {
/*  964 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "getTransacted()");
/*      */           }
/*      */           
/*  967 */           boolean traceRet1 = JmsXAQueueSessionImpl.this.getTransacted();
/*  968 */           if (Trace.isOn) {
/*  969 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getTransacted()", 
/*  970 */                 Boolean.valueOf(traceRet1));
/*      */           }
/*  972 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public void recover() throws JMSException {
/*  976 */           if (Trace.isOn) {
/*  977 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "recover()");
/*      */           }
/*  979 */           JmsXAQueueSessionImpl.this.recover();
/*  980 */           if (Trace.isOn) {
/*  981 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "recover()");
/*      */           }
/*      */         }
/*      */ 
/*      */         
/*      */         public void rollback() throws JMSException {
/*  987 */           if (Trace.isOn) {
/*  988 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "rollback()");
/*      */           }
/*      */           
/*  991 */           JmsXAQueueSessionImpl.this.rollback();
/*  992 */           if (Trace.isOn) {
/*  993 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "rollback()");
/*      */           }
/*      */         }
/*      */ 
/*      */         
/*      */         public void run() {
/*  999 */           if (Trace.isOn) {
/* 1000 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "run()");
/*      */           }
/* 1002 */           JmsXAQueueSessionImpl.this.run();
/* 1003 */           if (Trace.isOn) {
/* 1004 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "run()");
/*      */           }
/*      */         }
/*      */ 
/*      */         
/*      */         public void setMessageListener(MessageListener listener) throws JMSException {
/* 1010 */           if (Trace.isOn) {
/* 1011 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "setMessageListener(MessageListener)", new Object[] { listener });
/*      */           }
/*      */           
/* 1014 */           JmsXAQueueSessionImpl.this.setMessageListener(listener);
/* 1015 */           if (Trace.isOn) {
/* 1016 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "setMessageListener(MessageListener)");
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public void unsubscribe(String arg0) throws JMSException {
/* 1023 */           if (Trace.isOn) {
/* 1024 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "unsubscribe(String)", new Object[] { arg0 });
/*      */           }
/*      */           
/* 1027 */           JmsXAQueueSessionImpl.this.unsubscribe(arg0);
/* 1028 */           if (Trace.isOn) {
/* 1029 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "unsubscribe(String)");
/*      */           }
/*      */         }
/*      */ 
/*      */         
/*      */         public QueueReceiver createReceiver(Queue queue) throws JMSException {
/* 1035 */           if (Trace.isOn) {
/* 1036 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "createReceiver(Queue)", new Object[] { queue });
/*      */           }
/*      */ 
/*      */           
/* 1040 */           QueueReceiver traceRet1 = (QueueReceiver)JmsXAQueueSessionImpl.this.createConsumer((Destination)queue);
/* 1041 */           if (Trace.isOn) {
/* 1042 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createReceiver(Queue)", traceRet1);
/*      */           }
/*      */           
/* 1045 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public QueueReceiver createReceiver(Queue dest, String selector) throws JMSException {
/* 1049 */           if (Trace.isOn) {
/* 1050 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "createReceiver(Queue,String)", new Object[] { dest, selector });
/*      */           }
/*      */ 
/*      */           
/* 1054 */           QueueReceiver traceRet1 = (QueueReceiver)JmsXAQueueSessionImpl.this.createConsumer((Destination)dest, selector);
/* 1055 */           if (Trace.isOn) {
/* 1056 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createReceiver(Queue,String)", traceRet1);
/*      */           }
/*      */           
/* 1059 */           return traceRet1;
/*      */         }
/*      */         
/*      */         public QueueSender createSender(Queue arg0) throws JMSException {
/* 1063 */           if (Trace.isOn) {
/* 1064 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "createSender(Queue)", new Object[] { arg0 });
/*      */           }
/*      */           
/* 1067 */           QueueSender traceRet1 = (QueueSender)JmsXAQueueSessionImpl.this.createProducer((Destination)arg0);
/* 1068 */           if (Trace.isOn) {
/* 1069 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createSender(Queue)", traceRet1);
/*      */           }
/*      */           
/* 1072 */           return traceRet1;
/*      */         }
/*      */ 
/*      */         
/*      */         public void clearMessageReferences() {
/* 1077 */           if (Trace.isOn) {
/* 1078 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "clearMessageReferences()");
/*      */           }
/*      */           
/* 1081 */           JmsXAQueueSessionImpl.this.clearMessageReferences();
/* 1082 */           if (Trace.isOn) {
/* 1083 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "clearMessageReferences()");
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
/* 1095 */           if (Trace.isOn) {
/* 1096 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "createDurableConsumer(Topic,String)", new Object[] { topic, name });
/*      */           }
/*      */ 
/*      */           
/* 1100 */           MessageConsumer traceRet1 = JmsXAQueueSessionImpl.this.createDurableConsumer(topic, name);
/* 1101 */           if (Trace.isOn) {
/* 1102 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createDurableConsumer(Topic,String)", traceRet1);
/*      */           }
/*      */           
/* 1105 */           return traceRet1;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public MessageConsumer createDurableConsumer(Topic topic, String name, String selector, boolean noLocal) throws InvalidDestinationException, InvalidSelectorException, IllegalStateException, JMSException {
/* 1112 */           if (Trace.isOn) {
/* 1113 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "createDurableConsumer(Topic,String,String,boolean)", new Object[] { topic, name, selector, 
/*      */                   
/* 1115 */                   Boolean.valueOf(noLocal) });
/*      */           }
/*      */           
/* 1118 */           MessageConsumer traceRet1 = JmsXAQueueSessionImpl.this.createDurableConsumer(topic, name, selector, noLocal);
/* 1119 */           if (Trace.isOn) {
/* 1120 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createDurableConsumer(Topic,String,String,boolean)", traceRet1);
/*      */           }
/*      */           
/* 1123 */           return traceRet1;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public MessageConsumer createSharedConsumer(Topic topic, String name) throws JMSException, InvalidDestinationException, InvalidSelectorException {
/* 1130 */           if (Trace.isOn) {
/* 1131 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "createSharedConsumer(Topic,String)", new Object[] { topic, name });
/*      */           }
/*      */ 
/*      */           
/* 1135 */           MessageConsumer traceRet1 = JmsXAQueueSessionImpl.this.createSharedConsumer(topic, name);
/* 1136 */           if (Trace.isOn) {
/* 1137 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createSharedConsumer(Topic,String)", traceRet1);
/*      */           }
/*      */           
/* 1140 */           return traceRet1;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public MessageConsumer createSharedConsumer(Topic topic, String name, String selector) throws JMSException, InvalidDestinationException, InvalidSelectorException {
/* 1147 */           if (Trace.isOn) {
/* 1148 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "createSharedConsumer(Topic,String,String)", new Object[] { topic, name, selector });
/*      */           }
/*      */ 
/*      */           
/* 1152 */           MessageConsumer traceRet1 = JmsXAQueueSessionImpl.this.createSharedConsumer(topic, name, selector);
/* 1153 */           if (Trace.isOn) {
/* 1154 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createSharedConsumer(Topic,String,String)", traceRet1);
/*      */           }
/*      */           
/* 1157 */           return traceRet1;
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public MessageConsumer createSharedDurableConsumer(Topic topic, String name) throws JMSException, InvalidDestinationException {
/* 1163 */           if (Trace.isOn) {
/* 1164 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "createSharedDurableConsumer(Topic,String)", new Object[] { topic, name });
/*      */           }
/*      */ 
/*      */           
/* 1168 */           MessageConsumer traceRet1 = JmsXAQueueSessionImpl.this.createSharedDurableConsumer(topic, name);
/* 1169 */           if (Trace.isOn) {
/* 1170 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createSharedDurableConsumer(Topic,String)", traceRet1);
/*      */           }
/*      */           
/* 1173 */           return traceRet1;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public MessageConsumer createSharedDurableConsumer(Topic topic, String name, String selector) throws InvalidDestinationException, IllegalStateException, JMSException {
/* 1180 */           if (Trace.isOn) {
/* 1181 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "createSharedDurableConsumer(Topic,String,String)", new Object[] { topic, name, selector });
/*      */           }
/*      */ 
/*      */           
/* 1185 */           MessageConsumer traceRet1 = JmsXAQueueSessionImpl.this.createSharedDurableConsumer(topic, name, selector);
/* 1186 */           if (Trace.isOn) {
/* 1187 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createSharedDurableConsumer(Topic,String,String)", traceRet1);
/*      */           }
/*      */           
/* 1190 */           return traceRet1;
/*      */         }
/*      */       };
/*      */ 
/*      */     
/* 1195 */     if (Trace.isOn) {
/* 1196 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "getQueueSession()", jmsQueueSession);
/*      */     }
/*      */     
/* 1199 */     QueueSession traceRet2 = (QueueSession)jmsQueueSession;
/* 1200 */     if (Trace.isOn) {
/* 1201 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl", "getQueueSession()", traceRet2);
/*      */     }
/*      */     
/* 1204 */     return traceRet2;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsXAQueueSessionImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */