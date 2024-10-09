/*      */ package com.ibm.jms;
/*      */ 
/*      */ import com.ibm.mq.jms.MQDestination;
/*      */ import com.ibm.mq.jms.MQTemporaryQueue;
/*      */ import com.ibm.mq.jms.MQTemporaryTopic;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.jms.JmsMessage;
/*      */ import com.ibm.msg.client.jms.internal.JmsMessageImpl;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.ObjectStreamField;
/*      */ import java.io.Serializable;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import javax.jms.Destination;
/*      */ import javax.jms.JMSException;
/*      */ import javax.jms.Message;
/*      */ import javax.jms.MessageFormatException;
/*      */ import javax.jms.TemporaryQueue;
/*      */ import javax.jms.TemporaryTopic;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class JMSMessage
/*      */   implements Message, Serializable, JmsMessage
/*      */ {
/*      */   static final long serialVersionUID = -4436266789041169376L;
/*      */   
/*      */   static {
/*  363 */     if (Trace.isOn) {
/*  364 */       Trace.data("com.ibm.jms.JMSMessage", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/jms/JMSMessage.java");
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
/*  380 */   private static final ObjectStreamField[] serialPersistentFields = new ObjectStreamField[] { new ObjectStreamField("properties", Hashtable.class), new ObjectStreamField("correlationId", String.class), new ObjectStreamField("nativeCorrelId", byte[].class), new ObjectStreamField("deliveryMode", int.class), new ObjectStreamField("expiration", long.class), new ObjectStreamField("timeToLive", long.class), new ObjectStreamField("priority", int.class), new ObjectStreamField("redelivered", boolean.class), new ObjectStreamField("timestamp", long.class), new ObjectStreamField("type", String.class), new ObjectStreamField("messageId", String.class), new ObjectStreamField("destination", Destination.class), new ObjectStreamField("replyTo", Destination.class) };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected transient Message delegateMsg;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final String ENCODING_PROPERTY = "JMS_IBM_Encoding";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final String CHARSET_PROPERTY = "JMS_IBM_Character_Set";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final String NULL_MARKER = "__JMSMessage$$NULL_MARKER";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JMSMessage() {
/*  446 */     if (Trace.isOn) {
/*  447 */       Trace.entry(this, "com.ibm.jms.JMSMessage", "<init>()");
/*      */     }
/*  449 */     assert false : "Do not call messages default constructor, use javax.jms.Session methods instead";
/*  450 */     if (Trace.isOn) {
/*  451 */       Trace.exit(this, "com.ibm.jms.JMSMessage", "<init>()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected JMSMessage(Message delegateMsg) {
/*  458 */     if (Trace.isOn) {
/*  459 */       Trace.entry(this, "com.ibm.jms.JMSMessage", "<init>(Message)", new Object[] { delegateMsg });
/*      */     }
/*  461 */     setDelegate(delegateMsg);
/*  462 */     if (Trace.isOn) {
/*  463 */       Trace.exit(this, "com.ibm.jms.JMSMessage", "<init>(Message)");
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
/*      */   public void acknowledge() throws JMSException {
/*  475 */     if (Trace.isOn) {
/*  476 */       Trace.entry(this, "com.ibm.jms.JMSMessage", "acknowledge()");
/*      */     }
/*  478 */     this.delegateMsg.acknowledge();
/*  479 */     if (Trace.isOn) {
/*  480 */       Trace.exit(this, "com.ibm.jms.JMSMessage", "acknowledge()");
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
/*      */   public void clearBody() throws JMSException {
/*  492 */     if (Trace.isOn) {
/*  493 */       Trace.entry(this, "com.ibm.jms.JMSMessage", "clearBody()");
/*      */     }
/*  495 */     this.delegateMsg.clearBody();
/*  496 */     if (Trace.isOn) {
/*  497 */       Trace.exit(this, "com.ibm.jms.JMSMessage", "clearBody()");
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
/*      */   public void clearProperties() throws JMSException {
/*  509 */     if (Trace.isOn) {
/*  510 */       Trace.entry(this, "com.ibm.jms.JMSMessage", "clearProperties()");
/*      */     }
/*  512 */     this.delegateMsg.clearProperties();
/*  513 */     if (Trace.isOn) {
/*  514 */       Trace.exit(this, "com.ibm.jms.JMSMessage", "clearProperties()");
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
/*      */   public boolean getBooleanProperty(String name) throws JMSException {
/*  530 */     if (Trace.isOn) {
/*  531 */       Trace.entry(this, "com.ibm.jms.JMSMessage", "getBooleanProperty(String)", new Object[] { name });
/*      */     }
/*      */     
/*  534 */     boolean traceRet1 = this.delegateMsg.getBooleanProperty(name);
/*  535 */     if (Trace.isOn) {
/*  536 */       Trace.exit(this, "com.ibm.jms.JMSMessage", "getBooleanProperty(String)", 
/*  537 */           Boolean.valueOf(traceRet1));
/*      */     }
/*  539 */     return traceRet1;
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
/*      */   public byte getByteProperty(String name) throws JMSException {
/*  553 */     if (Trace.isOn) {
/*  554 */       Trace.entry(this, "com.ibm.jms.JMSMessage", "getByteProperty(String)", new Object[] { name });
/*      */     }
/*  556 */     byte traceRet1 = this.delegateMsg.getByteProperty(name);
/*  557 */     if (Trace.isOn) {
/*  558 */       Trace.exit(this, "com.ibm.jms.JMSMessage", "getByteProperty(String)", 
/*  559 */           Byte.valueOf(traceRet1));
/*      */     }
/*  561 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Message getDelegate() {
/*  570 */     if (Trace.isOn) {
/*  571 */       Trace.data(this, "com.ibm.jms.JMSMessage", "getDelegate()", "getter", this.delegateMsg);
/*      */     }
/*  573 */     return this.delegateMsg;
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
/*      */   public double getDoubleProperty(String name) throws JMSException {
/*  587 */     if (Trace.isOn) {
/*  588 */       Trace.entry(this, "com.ibm.jms.JMSMessage", "getDoubleProperty(String)", new Object[] { name });
/*      */     }
/*  590 */     double traceRet1 = this.delegateMsg.getDoubleProperty(name);
/*  591 */     if (Trace.isOn) {
/*  592 */       Trace.exit(this, "com.ibm.jms.JMSMessage", "getDoubleProperty(String)", 
/*  593 */           Double.valueOf(traceRet1));
/*      */     }
/*  595 */     return traceRet1;
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
/*      */   public float getFloatProperty(String name) throws JMSException {
/*  609 */     if (Trace.isOn) {
/*  610 */       Trace.entry(this, "com.ibm.jms.JMSMessage", "getFloatProperty(String)", new Object[] { name });
/*      */     }
/*  612 */     float traceRet1 = this.delegateMsg.getFloatProperty(name);
/*  613 */     if (Trace.isOn) {
/*  614 */       Trace.exit(this, "com.ibm.jms.JMSMessage", "getFloatProperty(String)", 
/*  615 */           Float.valueOf(traceRet1));
/*      */     }
/*  617 */     return traceRet1;
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
/*      */   public int getIntProperty(String name) throws JMSException {
/*  631 */     if (Trace.isOn) {
/*  632 */       Trace.entry(this, "com.ibm.jms.JMSMessage", "getIntProperty(String)", new Object[] { name });
/*      */     }
/*  634 */     int traceRet1 = this.delegateMsg.getIntProperty(name);
/*  635 */     if (Trace.isOn) {
/*  636 */       Trace.exit(this, "com.ibm.jms.JMSMessage", "getIntProperty(String)", 
/*  637 */           Integer.valueOf(traceRet1));
/*      */     }
/*  639 */     return traceRet1;
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
/*      */   public String getJMSCorrelationID() throws JMSException {
/*  655 */     String traceRet1 = this.delegateMsg.getJMSCorrelationID();
/*  656 */     if (Trace.isOn) {
/*  657 */       Trace.data(this, "com.ibm.jms.JMSMessage", "getJMSCorrelationID()", "getter", traceRet1);
/*      */     }
/*  659 */     return traceRet1;
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
/*      */   public byte[] getJMSCorrelationIDAsBytes() throws JMSException {
/*  673 */     byte[] traceRet1 = this.delegateMsg.getJMSCorrelationIDAsBytes();
/*  674 */     if (Trace.isOn) {
/*  675 */       Trace.data(this, "com.ibm.jms.JMSMessage", "getJMSCorrelationIDAsBytes()", "getter", traceRet1);
/*      */     }
/*      */     
/*  678 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getJMSDeliveryMode() throws JMSException {
/*  689 */     int traceRet1 = this.delegateMsg.getJMSDeliveryMode();
/*  690 */     if (Trace.isOn) {
/*  691 */       Trace.data(this, "com.ibm.jms.JMSMessage", "getJMSDeliveryMode()", "getter", 
/*  692 */           Integer.valueOf(traceRet1));
/*      */     }
/*  694 */     return traceRet1;
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
/*      */   public Destination getJMSDestination() throws JMSException {
/*      */     MQTemporaryTopic mQTemporaryTopic;
/*      */     MQTemporaryQueue mQTemporaryQueue;
/*  717 */     Destination destination = null;
/*  718 */     destination = this.delegateMsg.getJMSDestination();
/*      */     
/*  720 */     if (destination instanceof TemporaryTopic) {
/*  721 */       mQTemporaryTopic = MQDestinationProxy.createTemporaryTopic((TemporaryTopic)destination);
/*      */     }
/*  723 */     else if (mQTemporaryTopic instanceof TemporaryQueue) {
/*  724 */       mQTemporaryQueue = MQDestinationProxy.createTemporaryQueue((TemporaryQueue)mQTemporaryTopic);
/*      */     } 
/*      */     
/*  727 */     if (Trace.isOn) {
/*  728 */       Trace.data(this, "com.ibm.jms.JMSMessage", "getJMSDestination()", "getter", mQTemporaryQueue);
/*      */     }
/*  730 */     return (Destination)mQTemporaryQueue;
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
/*      */   public long getJMSExpiration() throws JMSException {
/*  758 */     long traceRet1 = this.delegateMsg.getJMSExpiration();
/*  759 */     if (Trace.isOn) {
/*  760 */       Trace.data(this, "com.ibm.jms.JMSMessage", "getJMSExpiration()", "getter", 
/*  761 */           Long.valueOf(traceRet1));
/*      */     }
/*  763 */     return traceRet1;
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
/*      */   public String getJMSMessageID() throws JMSException {
/*  792 */     String traceRet1 = this.delegateMsg.getJMSMessageID();
/*  793 */     if (Trace.isOn) {
/*  794 */       Trace.data(this, "com.ibm.jms.JMSMessage", "getJMSMessageID()", "getter", traceRet1);
/*      */     }
/*  796 */     return traceRet1;
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
/*      */   public int getJMSPriority() throws JMSException {
/*  812 */     int traceRet1 = this.delegateMsg.getJMSPriority();
/*  813 */     if (Trace.isOn) {
/*  814 */       Trace.data(this, "com.ibm.jms.JMSMessage", "getJMSPriority()", "getter", 
/*  815 */           Integer.valueOf(traceRet1));
/*      */     }
/*  817 */     return traceRet1;
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
/*      */   public boolean getJMSRedelivered() throws JMSException {
/*  833 */     boolean traceRet1 = this.delegateMsg.getJMSRedelivered();
/*  834 */     if (Trace.isOn) {
/*  835 */       Trace.data(this, "com.ibm.jms.JMSMessage", "getJMSRedelivered()", "getter", 
/*  836 */           Boolean.valueOf(traceRet1));
/*      */     }
/*  838 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Destination getJMSReplyTo() throws JMSException {
/*      */     MQTemporaryTopic mQTemporaryTopic;
/*      */     MQTemporaryQueue mQTemporaryQueue;
/*  849 */     Destination traceRet1 = this.delegateMsg.getJMSReplyTo();
/*  850 */     if (traceRet1 instanceof TemporaryTopic) {
/*  851 */       mQTemporaryTopic = MQDestinationProxy.createTemporaryTopic((TemporaryTopic)traceRet1);
/*      */     }
/*  853 */     else if (mQTemporaryTopic instanceof TemporaryQueue) {
/*  854 */       mQTemporaryQueue = MQDestinationProxy.createTemporaryQueue((TemporaryQueue)mQTemporaryTopic);
/*      */     } 
/*      */     
/*  857 */     if (Trace.isOn) {
/*  858 */       Trace.data(this, "com.ibm.jms.JMSMessage", "getJMSReplyTo()", "getter", mQTemporaryQueue);
/*      */     }
/*  860 */     return (Destination)mQTemporaryQueue;
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
/*      */   public long getJMSTimestamp() throws JMSException {
/*  880 */     long traceRet1 = this.delegateMsg.getJMSTimestamp();
/*  881 */     if (Trace.isOn) {
/*  882 */       Trace.data(this, "com.ibm.jms.JMSMessage", "getJMSTimestamp()", "getter", 
/*  883 */           Long.valueOf(traceRet1));
/*      */     }
/*  885 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getJMSType() throws JMSException {
/*  896 */     String traceRet1 = this.delegateMsg.getJMSType();
/*  897 */     if (Trace.isOn) {
/*  898 */       Trace.data(this, "com.ibm.jms.JMSMessage", "getJMSType()", "getter", traceRet1);
/*      */     }
/*  900 */     return traceRet1;
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
/*      */   public long getLongProperty(String name) throws JMSException {
/*  914 */     if (Trace.isOn) {
/*  915 */       Trace.entry(this, "com.ibm.jms.JMSMessage", "getLongProperty(String)", new Object[] { name });
/*      */     }
/*  917 */     long traceRet1 = this.delegateMsg.getLongProperty(name);
/*  918 */     if (Trace.isOn) {
/*  919 */       Trace.exit(this, "com.ibm.jms.JMSMessage", "getLongProperty(String)", 
/*  920 */           Long.valueOf(traceRet1));
/*      */     }
/*  922 */     return traceRet1;
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
/*      */   public Object getObjectProperty(String name) throws JMSException {
/*  942 */     if (Trace.isOn) {
/*  943 */       Trace.entry(this, "com.ibm.jms.JMSMessage", "getObjectProperty(String)", new Object[] { name });
/*      */     }
/*  945 */     Object traceRet1 = this.delegateMsg.getObjectProperty(name);
/*  946 */     if (Trace.isOn) {
/*  947 */       Trace.exit(this, "com.ibm.jms.JMSMessage", "getObjectProperty(String)", traceRet1);
/*      */     }
/*  949 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Enumeration<?> getPropertyNames() throws JMSException {
/*  960 */     Enumeration<?> traceRet1 = this.delegateMsg.getPropertyNames();
/*  961 */     if (Trace.isOn) {
/*  962 */       Trace.data(this, "com.ibm.jms.JMSMessage", "getPropertyNames()", "getter", traceRet1);
/*      */     }
/*  964 */     return traceRet1;
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
/*      */   public short getShortProperty(String name) throws JMSException {
/*  977 */     if (Trace.isOn) {
/*  978 */       Trace.entry(this, "com.ibm.jms.JMSMessage", "getShortProperty(String)", new Object[] { name });
/*      */     }
/*  980 */     short traceRet1 = this.delegateMsg.getShortProperty(name);
/*  981 */     if (Trace.isOn) {
/*  982 */       Trace.exit(this, "com.ibm.jms.JMSMessage", "getShortProperty(String)", 
/*  983 */           Short.valueOf(traceRet1));
/*      */     }
/*  985 */     return traceRet1;
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
/*  998 */     if (Trace.isOn) {
/*  999 */       Trace.entry(this, "com.ibm.jms.JMSMessage", "getStringProperty(String)", new Object[] { name });
/*      */     }
/* 1001 */     String traceRet1 = this.delegateMsg.getStringProperty(name);
/* 1002 */     if (Trace.isOn) {
/* 1003 */       Trace.exit(this, "com.ibm.jms.JMSMessage", "getStringProperty(String)", traceRet1);
/*      */     }
/* 1005 */     return traceRet1;
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
/*      */   public boolean propertyExists(String name) throws JMSException {
/* 1017 */     if (Trace.isOn) {
/* 1018 */       Trace.entry(this, "com.ibm.jms.JMSMessage", "propertyExists(String)", new Object[] { name });
/*      */     }
/* 1020 */     boolean traceRet1 = this.delegateMsg.propertyExists(name);
/* 1021 */     if (Trace.isOn) {
/* 1022 */       Trace.exit(this, "com.ibm.jms.JMSMessage", "propertyExists(String)", 
/* 1023 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 1025 */     return traceRet1;
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
/*      */   public void setBooleanProperty(String name, boolean value) throws JMSException {
/* 1038 */     if (Trace.isOn) {
/* 1039 */       Trace.entry(this, "com.ibm.jms.JMSMessage", "setBooleanProperty(String,boolean)", new Object[] { name, 
/* 1040 */             Boolean.valueOf(value) });
/*      */     }
/* 1042 */     this.delegateMsg.setBooleanProperty(name, value);
/* 1043 */     if (Trace.isOn) {
/* 1044 */       Trace.exit(this, "com.ibm.jms.JMSMessage", "setBooleanProperty(String,boolean)");
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
/*      */   public void setByteProperty(String name, byte value) throws JMSException {
/* 1059 */     if (Trace.isOn) {
/* 1060 */       Trace.entry(this, "com.ibm.jms.JMSMessage", "setByteProperty(String,byte)", new Object[] { name, 
/* 1061 */             Byte.valueOf(value) });
/*      */     }
/* 1063 */     this.delegateMsg.setByteProperty(name, value);
/* 1064 */     if (Trace.isOn) {
/* 1065 */       Trace.exit(this, "com.ibm.jms.JMSMessage", "setByteProperty(String,byte)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDelegate(Message delegateMsg) {
/* 1076 */     if (Trace.isOn) {
/* 1077 */       Trace.data(this, "com.ibm.jms.JMSMessage", "setDelegate(Message)", "setter", delegateMsg);
/*      */     }
/* 1079 */     this.delegateMsg = delegateMsg;
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
/*      */   public void setDoubleProperty(String name, double value) throws JMSException {
/* 1092 */     if (Trace.isOn) {
/* 1093 */       Trace.entry(this, "com.ibm.jms.JMSMessage", "setDoubleProperty(String,double)", new Object[] { name, 
/* 1094 */             Double.valueOf(value) });
/*      */     }
/* 1096 */     this.delegateMsg.setDoubleProperty(name, value);
/* 1097 */     if (Trace.isOn) {
/* 1098 */       Trace.exit(this, "com.ibm.jms.JMSMessage", "setDoubleProperty(String,double)");
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
/*      */   public void setFloatProperty(String name, float value) throws JMSException {
/* 1113 */     if (Trace.isOn) {
/* 1114 */       Trace.entry(this, "com.ibm.jms.JMSMessage", "setFloatProperty(String,float)", new Object[] { name, 
/* 1115 */             Float.valueOf(value) });
/*      */     }
/* 1117 */     this.delegateMsg.setFloatProperty(name, value);
/* 1118 */     if (Trace.isOn) {
/* 1119 */       Trace.exit(this, "com.ibm.jms.JMSMessage", "setFloatProperty(String,float)");
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
/*      */   public void setIntProperty(String name, int value) throws JMSException {
/* 1134 */     if (Trace.isOn) {
/* 1135 */       Trace.entry(this, "com.ibm.jms.JMSMessage", "setIntProperty(String,int)", new Object[] { name, Integer.valueOf(value) });
/*      */     }
/* 1137 */     this.delegateMsg.setIntProperty(name, value);
/* 1138 */     if (Trace.isOn) {
/* 1139 */       Trace.exit(this, "com.ibm.jms.JMSMessage", "setIntProperty(String,int)");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setJMSCorrelationID(String correlationID) throws JMSException {
/* 1179 */     if (Trace.isOn) {
/* 1180 */       Trace.data(this, "com.ibm.jms.JMSMessage", "setJMSCorrelationID(String)", "setter", correlationID);
/*      */     }
/*      */     
/* 1183 */     this.delegateMsg.setJMSCorrelationID(correlationID);
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
/*      */   public void setJMSCorrelationIDAsBytes(byte[] correlID) throws JMSException {
/* 1210 */     if (Trace.isOn) {
/* 1211 */       Trace.data(this, "com.ibm.jms.JMSMessage", "setJMSCorrelationIDAsBytes(byte [ ])", "setter", correlID);
/*      */     }
/*      */     
/* 1214 */     this.delegateMsg.setJMSCorrelationIDAsBytes(correlID);
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
/*      */   public void setJMSDeliveryMode(int deliveryMode) throws JMSException {
/* 1234 */     if (Trace.isOn) {
/* 1235 */       Trace.data(this, "com.ibm.jms.JMSMessage", "setJMSDeliveryMode(int)", "setter", 
/* 1236 */           Integer.valueOf(deliveryMode));
/*      */     }
/* 1238 */     this.delegateMsg.setJMSDeliveryMode(deliveryMode);
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
/*      */   public void setJMSDestination(Destination destination) throws JMSException {
/* 1253 */     if (Trace.isOn) {
/* 1254 */       Trace.data(this, "com.ibm.jms.JMSMessage", "setJMSDestination(Destination)", "setter", destination);
/*      */     }
/*      */ 
/*      */     
/* 1258 */     Destination dest = MQDestinationProxy.validateDestination(destination);
/* 1259 */     this.delegateMsg.setJMSDestination(dest);
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
/*      */   public void setJMSExpiration(long expiration) throws JMSException {
/* 1275 */     if (Trace.isOn) {
/* 1276 */       Trace.data(this, "com.ibm.jms.JMSMessage", "setJMSExpiration(long)", "setter", 
/* 1277 */           Long.valueOf(expiration));
/*      */     }
/* 1279 */     this.delegateMsg.setJMSExpiration(expiration);
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
/*      */   public void setJMSMessageID(String id) throws JMSException {
/* 1301 */     if (Trace.isOn) {
/* 1302 */       Trace.data(this, "com.ibm.jms.JMSMessage", "setJMSMessageID(String)", "setter", id);
/*      */     }
/* 1304 */     this.delegateMsg.setJMSMessageID(id);
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
/*      */   public void setJMSPriority(int priority) throws JMSException {
/* 1324 */     if (Trace.isOn) {
/* 1325 */       Trace.data(this, "com.ibm.jms.JMSMessage", "setJMSPriority(int)", "setter", 
/* 1326 */           Integer.valueOf(priority));
/*      */     }
/* 1328 */     this.delegateMsg.setJMSPriority(priority);
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
/*      */   public void setJMSRedelivered(boolean redelivered) throws JMSException {
/* 1343 */     if (Trace.isOn) {
/* 1344 */       Trace.data(this, "com.ibm.jms.JMSMessage", "setJMSRedelivered(boolean)", "setter", 
/* 1345 */           Boolean.valueOf(redelivered));
/*      */     }
/* 1347 */     this.delegateMsg.setJMSRedelivered(redelivered);
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
/*      */   public void setJMSReplyTo(Destination replyTo) throws JMSException {
/* 1376 */     if (Trace.isOn) {
/* 1377 */       Trace.data(this, "com.ibm.jms.JMSMessage", "setJMSReplyTo(Destination)", "setter", replyTo);
/*      */     }
/*      */     
/* 1380 */     Destination dest = MQDestinationProxy.validateDestination(replyTo);
/*      */     
/* 1382 */     this.delegateMsg.setJMSReplyTo(dest);
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
/*      */   public void setJMSTimestamp(long timestamp) throws JMSException {
/* 1397 */     if (Trace.isOn) {
/* 1398 */       Trace.data(this, "com.ibm.jms.JMSMessage", "setJMSTimestamp(long)", "setter", 
/* 1399 */           Long.valueOf(timestamp));
/*      */     }
/* 1401 */     this.delegateMsg.setJMSTimestamp(timestamp);
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
/*      */   public void setJMSType(String type) throws JMSException {
/* 1421 */     if (Trace.isOn) {
/* 1422 */       Trace.data(this, "com.ibm.jms.JMSMessage", "setJMSType(String)", "setter", type);
/*      */     }
/* 1424 */     this.delegateMsg.setJMSType(type);
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
/*      */   public void setLongProperty(String name, long value) throws JMSException {
/* 1437 */     if (Trace.isOn) {
/* 1438 */       Trace.entry(this, "com.ibm.jms.JMSMessage", "setLongProperty(String,long)", new Object[] { name, 
/* 1439 */             Long.valueOf(value) });
/*      */     }
/* 1441 */     this.delegateMsg.setLongProperty(name, value);
/* 1442 */     if (Trace.isOn) {
/* 1443 */       Trace.exit(this, "com.ibm.jms.JMSMessage", "setLongProperty(String,long)");
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
/*      */   public void setObjectProperty(String name, Object value) throws JMSException {
/* 1463 */     if (Trace.isOn) {
/* 1464 */       Trace.entry(this, "com.ibm.jms.JMSMessage", "setObjectProperty(String,Object)", new Object[] { name, value });
/*      */     }
/*      */     
/* 1467 */     this.delegateMsg.setObjectProperty(name, value);
/* 1468 */     if (Trace.isOn) {
/* 1469 */       Trace.exit(this, "com.ibm.jms.JMSMessage", "setObjectProperty(String,Object)");
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
/*      */   public void setShortProperty(String name, short value) throws JMSException {
/* 1484 */     if (Trace.isOn) {
/* 1485 */       Trace.entry(this, "com.ibm.jms.JMSMessage", "setShortProperty(String,short)", new Object[] { name, 
/* 1486 */             Short.valueOf(value) });
/*      */     }
/* 1488 */     this.delegateMsg.setShortProperty(name, value);
/* 1489 */     if (Trace.isOn) {
/* 1490 */       Trace.exit(this, "com.ibm.jms.JMSMessage", "setShortProperty(String,short)");
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
/*      */   public void setStringProperty(String name, String value) throws JMSException {
/* 1505 */     if (Trace.isOn) {
/* 1506 */       Trace.entry(this, "com.ibm.jms.JMSMessage", "setStringProperty(String,String)", new Object[] { name, value });
/*      */     }
/*      */     
/* 1509 */     this.delegateMsg.setStringProperty(name, value);
/* 1510 */     if (Trace.isOn) {
/* 1511 */       Trace.exit(this, "com.ibm.jms.JMSMessage", "setStringProperty(String,String)");
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
/*      */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 1524 */     if (Trace.isOn) {
/* 1525 */       Trace.entry(this, "com.ibm.jms.JMSMessage", "readObject(java.io.ObjectInputStream)", new Object[] { in });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1535 */       String connectionType = "com.ibm.msg.client.wmq";
/* 1536 */       Message superClassMsg = null;
/* 1537 */       if (this.delegateMsg != null) {
/* 1538 */         superClassMsg = this.delegateMsg;
/*      */       }
/* 1540 */       this.delegateMsg = (Message)new JmsMessageImpl(connectionType, superClassMsg);
/*      */     
/*      */     }
/* 1543 */     catch (JMSException e) {
/* 1544 */       if (Trace.isOn) {
/* 1545 */         Trace.catchBlock(this, "com.ibm.jms.JMSMessage", "readObject(java.io.ObjectInputStream)", (Throwable)e, 1);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1557 */     ObjectInputStream.GetField fields = in.readFields();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1572 */       boolean messageCorrelSet = false;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1577 */       if (!fields.defaulted("properties")) {
/* 1578 */         Hashtable<String, Object> tempProperties = (Hashtable<String, Object>)fields.get("properties", (Object)null);
/*      */         
/* 1580 */         Iterator<Map.Entry<String, Object>> it = tempProperties.entrySet().iterator();
/* 1581 */         while (it.hasNext()) {
/* 1582 */           Map.Entry<String, Object> entry = it.next();
/* 1583 */           String key = entry.getKey();
/* 1584 */           Object value = entry.getValue();
/*      */           
/* 1586 */           setObjectProperty(key, value.equals("__JMSMessage$$NULL_MARKER") ? null : value);
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1593 */       if (!fields.defaulted("correlationId")) {
/* 1594 */         setJMSCorrelationID((String)fields.get("correlationId", (Object)null));
/* 1595 */         messageCorrelSet = true;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1600 */       if (!messageCorrelSet && 
/* 1601 */         !fields.defaulted("nativeCorrelId")) {
/* 1602 */         setJMSCorrelationIDAsBytes((byte[])fields.get("nativeCorrelId", (Object)null));
/* 1603 */         messageCorrelSet = true;
/*      */       } 
/*      */ 
/*      */       
/* 1607 */       if (!fields.defaulted("deliveryMode")) {
/* 1608 */         setJMSDeliveryMode(fields.get("deliveryMode", 0));
/*      */       }
/*      */       
/* 1611 */       if (!fields.defaulted("expiration")) {
/* 1612 */         setJMSExpiration(fields.get("expiration", 0L));
/*      */       }
/*      */       
/* 1615 */       if (!fields.defaulted("timeToLive")) {
/* 1616 */         setLongProperty("timeToLive", fields.get("timeToLive", 0L));
/*      */       }
/*      */       
/* 1619 */       if (!fields.defaulted("priority")) {
/* 1620 */         setJMSPriority(fields.get("priority", 0));
/*      */       }
/*      */       
/* 1623 */       if (!fields.defaulted("redelivered")) {
/* 1624 */         setJMSRedelivered(fields.get("redelivered", false));
/*      */       }
/*      */       
/* 1627 */       if (!fields.defaulted("timestamp")) {
/* 1628 */         setJMSTimestamp(fields.get("timestamp", 0L));
/*      */       }
/*      */       
/* 1631 */       if (!fields.defaulted("type")) {
/* 1632 */         setJMSType((String)fields.get("type", (Object)null));
/*      */       }
/*      */       
/* 1635 */       if (!fields.defaulted("messageId")) {
/* 1636 */         setJMSMessageID((String)fields.get("messageId", (Object)null));
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1642 */       if (!fields.defaulted("destination")) {
/* 1643 */         setJMSDestination((Destination)fields.get("destination", (Object)null));
/*      */       }
/*      */       
/* 1646 */       if (!fields.defaulted("replyTo")) {
/* 1647 */         setJMSReplyTo((Destination)fields.get("replyTo", (Object)null));
/*      */       
/*      */       }
/*      */     }
/* 1651 */     catch (JMSException je) {
/* 1652 */       if (Trace.isOn) {
/* 1653 */         Trace.catchBlock(this, "com.ibm.jms.JMSMessage", "readObject(java.io.ObjectInputStream)", (Throwable)je, 2);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1660 */     if (Trace.isOn) {
/* 1661 */       Trace.exit(this, "com.ibm.jms.JMSMessage", "readObject(java.io.ObjectInputStream)");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 1667 */     if (Trace.isOn) {
/* 1668 */       Trace.entry(this, "com.ibm.jms.JMSMessage", "writeObject(java.io.ObjectOutputStream)", new Object[] { out });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1676 */     ObjectOutputStream.PutField fields = out.putFields();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1683 */       Hashtable<String, Object> tempTable = new Hashtable<>();
/* 1684 */       Enumeration<?> e = getPropertyNames();
/* 1685 */       while (e.hasMoreElements()) {
/* 1686 */         String key = (String)e.nextElement();
/* 1687 */         Object propertyValue = getObjectProperty(key);
/* 1688 */         tempTable.put(key, (propertyValue == null) ? "__JMSMessage$$NULL_MARKER" : propertyValue);
/*      */       } 
/*      */       
/* 1691 */       fields.put("properties", tempTable);
/*      */       
/* 1693 */       fields.put("correlationId", getJMSCorrelationID());
/* 1694 */       fields.put("nativeCorrelId", getJMSCorrelationIDAsBytes());
/* 1695 */       fields.put("deliveryMode", getJMSDeliveryMode());
/* 1696 */       fields.put("expiration", getJMSExpiration());
/*      */       
/* 1698 */       if (getObjectProperty("timeToLive") != null) {
/* 1699 */         fields.put("timeToLive", getLongProperty("timeToLive"));
/*      */       }
/* 1701 */       fields.put("priority", getJMSPriority());
/* 1702 */       fields.put("redelivered", getJMSRedelivered());
/* 1703 */       fields.put("timestamp", getJMSTimestamp());
/* 1704 */       fields.put("type", getJMSType());
/* 1705 */       fields.put("messageId", getJMSMessageID());
/*      */       
/* 1707 */       fields.put("destination", getJMSDestination());
/* 1708 */       fields.put("replyTo", getJMSReplyTo());
/*      */     }
/* 1710 */     catch (JMSException je) {
/* 1711 */       if (Trace.isOn) {
/* 1712 */         Trace.catchBlock(this, "com.ibm.jms.JMSMessage", "writeObject(java.io.ObjectOutputStream)", (Throwable)je);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1721 */     out.writeFields();
/*      */     
/* 1723 */     if (Trace.isOn) {
/* 1724 */       Trace.exit(this, "com.ibm.jms.JMSMessage", "writeObject(java.io.ObjectOutputStream)");
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
/*      */   public String toString() {
/* 1737 */     String traceRet1 = ((this.delegateMsg == null) ? "<null>" : this.delegateMsg).toString();
/* 1738 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class MQDestinationProxy
/*      */     extends MQDestination
/*      */   {
/*      */     private static final long serialVersionUID = 1627181143081591416L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private MQDestinationProxy() throws JMSException {
/* 1758 */       super("");
/* 1759 */       if (Trace.isOn) {
/* 1760 */         Trace.entry(this, "com.ibm.jms.MQDestinationProxy", "<init>()");
/*      */       }
/* 1762 */       if (Trace.isOn) {
/* 1763 */         Trace.exit(this, "com.ibm.jms.MQDestinationProxy", "<init>()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected static Destination validateDestination(Destination dest) throws JMSException {
/* 1772 */       if (Trace.isOn) {
/* 1773 */         Trace.entry("com.ibm.jms.MQDestinationProxy", "validateDestination(Destination)", new Object[] { dest });
/*      */       }
/*      */       
/* 1776 */       Destination traceRet1 = proxyValidateDestination(dest);
/* 1777 */       if (Trace.isOn) {
/* 1778 */         Trace.exit("com.ibm.jms.MQDestinationProxy", "validateDestination(Destination)", traceRet1);
/*      */       }
/* 1780 */       return traceRet1;
/*      */     }
/*      */     
/*      */     protected static MQTemporaryTopic createTemporaryTopic(TemporaryTopic tempTopic) throws JMSException {
/* 1784 */       if (Trace.isOn) {
/* 1785 */         Trace.entry("com.ibm.jms.MQDestinationProxy", "createTemporaryTopic(TemporaryTopic)", new Object[] { tempTopic });
/*      */       }
/*      */       
/* 1788 */       MQTemporaryTopic traceRet1 = proxyCreateTemporaryTopic(tempTopic);
/* 1789 */       if (Trace.isOn) {
/* 1790 */         Trace.exit("com.ibm.jms.MQDestinationProxy", "createTemporaryTopic(TemporaryTopic)", traceRet1);
/*      */       }
/*      */       
/* 1793 */       return traceRet1;
/*      */     }
/*      */     
/*      */     protected static MQTemporaryQueue createTemporaryQueue(TemporaryQueue tempQueue) throws JMSException {
/* 1797 */       if (Trace.isOn) {
/* 1798 */         Trace.entry("com.ibm.jms.MQDestinationProxy", "createTemporaryQueue(TemporaryQueue)", new Object[] { tempQueue });
/*      */       }
/*      */       
/* 1801 */       MQTemporaryQueue traceRet1 = proxyCreateTemporaryQueue(tempQueue);
/* 1802 */       if (Trace.isOn) {
/* 1803 */         Trace.exit("com.ibm.jms.MQDestinationProxy", "createTemporaryQueue(TemporaryQueue)", traceRet1);
/*      */       }
/*      */       
/* 1806 */       return traceRet1;
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
/*      */   protected static JMSMapMessage createMapMessage(Message delegateMsg) {
/* 1818 */     if (Trace.isOn) {
/* 1819 */       Trace.entry("com.ibm.jms.JMSMessage", "createMapMessage(Message)", new Object[] { delegateMsg });
/*      */     }
/*      */     
/* 1822 */     JMSMapMessage traceRet1 = new JMSMapMessage(delegateMsg);
/* 1823 */     if (Trace.isOn) {
/* 1824 */       Trace.exit("com.ibm.jms.JMSMessage", "createMapMessage(Message)", traceRet1);
/*      */     }
/* 1826 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static JMSBytesMessage createBytesMessage(Message delegateMsg) {
/* 1836 */     if (Trace.isOn) {
/* 1837 */       Trace.entry("com.ibm.jms.JMSMessage", "createBytesMessage(Message)", new Object[] { delegateMsg });
/*      */     }
/*      */     
/* 1840 */     JMSBytesMessage traceRet1 = new JMSBytesMessage(delegateMsg);
/* 1841 */     if (Trace.isOn) {
/* 1842 */       Trace.exit("com.ibm.jms.JMSMessage", "createBytesMessage(Message)", traceRet1);
/*      */     }
/* 1844 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static JMSStreamMessage createStreamMessage(Message delegateMsg) {
/* 1854 */     if (Trace.isOn) {
/* 1855 */       Trace.entry("com.ibm.jms.JMSMessage", "createStreamMessage(Message)", new Object[] { delegateMsg });
/*      */     }
/*      */     
/* 1858 */     JMSStreamMessage traceRet1 = new JMSStreamMessage(delegateMsg);
/* 1859 */     if (Trace.isOn) {
/* 1860 */       Trace.exit("com.ibm.jms.JMSMessage", "createStreamMessage(Message)", traceRet1);
/*      */     }
/* 1862 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static JMSTextMessage createTextMessage(Message delegateMsg) {
/* 1872 */     if (Trace.isOn) {
/* 1873 */       Trace.entry("com.ibm.jms.JMSMessage", "createTextMessage(Message)", new Object[] { delegateMsg });
/*      */     }
/*      */     
/* 1876 */     JMSTextMessage traceRet1 = new JMSTextMessage(delegateMsg);
/* 1877 */     if (Trace.isOn) {
/* 1878 */       Trace.exit("com.ibm.jms.JMSMessage", "createTextMessage(Message)", traceRet1);
/*      */     }
/* 1880 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static JMSObjectMessage createObjectMessage(Message delegateMsg) {
/* 1890 */     if (Trace.isOn) {
/* 1891 */       Trace.entry("com.ibm.jms.JMSMessage", "createObjectMessage(Message)", new Object[] { delegateMsg });
/*      */     }
/*      */     
/* 1894 */     JMSObjectMessage traceRet1 = new JMSObjectMessage(delegateMsg);
/* 1895 */     if (Trace.isOn) {
/* 1896 */       Trace.exit("com.ibm.jms.JMSMessage", "createObjectMessage(Message)", traceRet1);
/*      */     }
/* 1898 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static JMSMessage createMessage(Message delegateMsg) {
/* 1908 */     if (Trace.isOn) {
/* 1909 */       Trace.entry("com.ibm.jms.JMSMessage", "createMessage(Message)", new Object[] { delegateMsg });
/*      */     }
/* 1911 */     JMSMessage traceRet1 = new JMSMessage(delegateMsg);
/* 1912 */     if (Trace.isOn) {
/* 1913 */       Trace.exit("com.ibm.jms.JMSMessage", "createMessage(Message)", traceRet1);
/*      */     }
/* 1915 */     return traceRet1;
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
/*      */   public <T> T getBody(Class<T> c) throws JMSException, MessageFormatException {
/* 1928 */     if (Trace.isOn) {
/* 1929 */       Trace.entry(this, "com.ibm.jms.JMSMessage", "getBody(Class<T>)", new Object[] { c });
/*      */     }
/* 1931 */     T traceRet1 = (T)this.delegateMsg.getBody(c);
/* 1932 */     if (Trace.isOn) {
/* 1933 */       Trace.exit(this, "com.ibm.jms.JMSMessage", "getBody(Class<T>)", traceRet1);
/*      */     }
/* 1935 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getJMSDeliveryTime() throws JMSException {
/* 1946 */     long traceRet1 = this.delegateMsg.getJMSDeliveryTime();
/* 1947 */     if (Trace.isOn) {
/* 1948 */       Trace.data(this, "com.ibm.jms.JMSMessage", "getJMSDeliveryTime()", "getter", 
/* 1949 */           Long.valueOf(traceRet1));
/*      */     }
/* 1951 */     return traceRet1;
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
/*      */   public boolean isBodyAssignableTo(Class c) throws JMSException, MessageFormatException {
/* 1963 */     if (Trace.isOn) {
/* 1964 */       Trace.entry(this, "com.ibm.jms.JMSMessage", "isBodyAssignableTo(Class)", new Object[] { c });
/*      */     }
/* 1966 */     boolean traceRet1 = this.delegateMsg.isBodyAssignableTo(c);
/* 1967 */     if (Trace.isOn) {
/* 1968 */       Trace.exit(this, "com.ibm.jms.JMSMessage", "isBodyAssignableTo(Class)", 
/* 1969 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 1971 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setJMSDeliveryTime(long deliveryTime) throws JMSException {
/* 1982 */     if (Trace.isOn) {
/* 1983 */       Trace.data(this, "com.ibm.jms.JMSMessage", "setJMSDeliveryTime(long)", "setter", 
/* 1984 */           Long.valueOf(deliveryTime));
/*      */     }
/* 1986 */     this.delegateMsg.getJMSDeliveryTime();
/*      */   }
/*      */   
/*      */   public synchronized void updateFromMessage(Message message) throws JMSException {
/* 1990 */     if (Trace.isOn) {
/* 1991 */       Trace.entry(this, "com.ibm.jms.JMSMessage", "updateFromMessage(Message)", new Object[] { message });
/*      */     }
/*      */     
/* 1994 */     if (this.delegateMsg instanceof JmsMessage) {
/* 1995 */       ((JmsMessage)this.delegateMsg).updateFromMessage(message);
/*      */     
/*      */     }
/* 1998 */     else if (Trace.isOn) {
/* 1999 */       Trace.data(this, "updateFromMessage(JmsMessage)", "delegate message not updatable", this.delegateMsg.getClass().getName());
/*      */     } 
/*      */     
/* 2002 */     if (Trace.isOn)
/* 2003 */       Trace.exit(this, "com.ibm.jms.JMSMessage", "updateFromMessage(Message)"); 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\jms\JMSMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */