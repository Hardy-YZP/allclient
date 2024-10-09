/*      */ package com.ibm.msg.client.jms.internal;
/*      */ 
/*      */ import com.ibm.msg.client.commonservices.Utils;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.jms.JmsFactoryFactory;
/*      */ import com.ibm.msg.client.jms.JmsMessage;
/*      */ import com.ibm.msg.client.jms.admin.JmsDestinationImpl;
/*      */ import com.ibm.msg.client.provider.ProviderBytesMessage;
/*      */ import com.ibm.msg.client.provider.ProviderDestination;
/*      */ import com.ibm.msg.client.provider.ProviderJmsFactory;
/*      */ import com.ibm.msg.client.provider.ProviderMapMessage;
/*      */ import com.ibm.msg.client.provider.ProviderMessage;
/*      */ import com.ibm.msg.client.provider.ProviderMessageFactory;
/*      */ import com.ibm.msg.client.provider.ProviderObjectMessage;
/*      */ import com.ibm.msg.client.provider.ProviderStreamMessage;
/*      */ import com.ibm.msg.client.provider.ProviderTextMessage;
/*      */ import java.io.ObjectStreamException;
/*      */ import java.io.Serializable;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.Enumeration;
/*      */ import java.util.HashMap;
/*      */ import java.util.Hashtable;
/*      */ import java.util.ListIterator;
/*      */ import java.util.Map;
/*      */ import javax.jms.BytesMessage;
/*      */ import javax.jms.Destination;
/*      */ import javax.jms.JMSException;
/*      */ import javax.jms.MapMessage;
/*      */ import javax.jms.Message;
/*      */ import javax.jms.MessageFormatException;
/*      */ import javax.jms.MessageNotReadableException;
/*      */ import javax.jms.ObjectMessage;
/*      */ import javax.jms.Queue;
/*      */ import javax.jms.StreamMessage;
/*      */ import javax.jms.TextMessage;
/*      */ import javax.jms.Topic;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class JmsMessageImpl
/*      */   extends JmsPropertyContextImpl
/*      */   implements Message, Serializable, JmsMessage
/*      */ {
/*      */   private static final long serialVersionUID = 5268072332264758613L;
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsMessageImpl.java";
/*      */   protected static final String MESSAGETYPE_NONE = "jms_none";
/*      */   protected static final String MESSAGETYPE_TEXT = "jms_text";
/*      */   protected static final String MESSAGETYPE_OBJECT = "jms_object";
/*      */   protected static final String MESSAGETYPE_MAP = "jms_map";
/*      */   protected static final String MESSAGETYPE_STREAM = "jms_stream";
/*      */   protected static final String MESSAGETYPE_BYTES = "jms_bytes";
/*      */   
/*      */   static {
/*   77 */     if (Trace.isOn) {
/*   78 */       Trace.data("com.ibm.msg.client.jms.internal.JmsMessageImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsMessageImpl.java");
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
/*  105 */   private transient JmsSessionImpl theSession = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  111 */   protected transient ProviderMessageFactory providerMessageFactory = null;
/*  112 */   protected transient ProviderJmsFactory providerJmsFactory = null;
/*      */   
/*      */   protected void getFactories() {
/*  115 */     if (Trace.isOn) {
/*  116 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getFactories()");
/*      */     }
/*  118 */     getFactories(null);
/*  119 */     if (Trace.isOn) {
/*  120 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getFactories()");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void getFactories(String newConnectionTypeName) {
/*  126 */     if (Trace.isOn) {
/*  127 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getFactories(String)", new Object[] { newConnectionTypeName });
/*      */     }
/*      */ 
/*      */     
/*  131 */     if (this.providerMessageFactory == null || this.providerJmsFactory == null) {
/*      */       
/*      */       try {
/*  134 */         JmsFactoryFactoryImpl jmsFactory = null;
/*  135 */         if (newConnectionTypeName == null) {
/*  136 */           jmsFactory = (JmsFactoryFactoryImpl)JmsFactoryFactory.getInstance(this.connectionTypeName);
/*      */         } else {
/*      */           
/*  139 */           jmsFactory = (JmsFactoryFactoryImpl)JmsFactoryFactory.getInstance(newConnectionTypeName);
/*      */         } 
/*      */         
/*  142 */         if (this.providerMessageFactory == null) {
/*  143 */           this.providerMessageFactory = jmsFactory.getProviderMessageFactory();
/*      */         }
/*  145 */         if (this.providerJmsFactory == null) {
/*  146 */           this.providerJmsFactory = jmsFactory.getProviderFactoryFactory().getJmsFactory();
/*      */         }
/*      */       }
/*  149 */       catch (Exception e) {
/*  150 */         if (Trace.isOn) {
/*  151 */           Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getFactories(String)", e);
/*      */         }
/*      */ 
/*      */         
/*  155 */         HashMap<String, Object> info = new HashMap<>();
/*  156 */         info.put("exception", e);
/*  157 */         Trace.ffst(this, "obtainFactories(String)", "XJ001001", info, JMSException.class);
/*      */       } 
/*      */     }
/*      */     
/*  161 */     if (Trace.isOn) {
/*  162 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getFactories(String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected ProviderMessageFactory getProviderMessageFactory() {
/*  169 */     getFactories();
/*  170 */     if (Trace.isOn) {
/*  171 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getProviderMessageFactory()", "getter", this.providerMessageFactory);
/*      */     }
/*      */     
/*  174 */     return this.providerMessageFactory;
/*      */   }
/*      */ 
/*      */   
/*      */   protected ProviderJmsFactory getProviderJmsFactory() {
/*  179 */     getFactories();
/*  180 */     if (Trace.isOn) {
/*  181 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getProviderJmsFactory()", "getter", this.providerJmsFactory);
/*      */     }
/*      */     
/*  184 */     return this.providerJmsFactory;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean bodyReadOnly = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean propertiesReadOnly = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  202 */   private static Hashtable<String, Class<?>> JMS_IBM_props = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  209 */   private transient String cachedToString = "<init> in progress";
/*  210 */   private transient Object cachedToStringLock = new Object();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  216 */   protected String messageType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private ProviderMessage providerMessage;
/*      */ 
/*      */ 
/*      */   
/*  225 */   private Destination jmsDestination = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  232 */   private Destination jmsReplyTo = null;
/*      */ 
/*      */   
/*  235 */   private String connectionTypeName = null;
/*      */   private transient Enumeration<String> jmsxPropertyNames;
/*      */   private static final Map<Class<?>, Map<Class<?>, JMSMessagePropertyValueConverter>> jmsPropertyValidators;
/*      */   
/*      */   static {
/*  240 */     if (Trace.isOn) {
/*  241 */       Trace.entry("com.ibm.msg.client.jms.internal.JmsMessageImpl", "static()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  249 */     JMS_IBM_props = new Hashtable<>(54, 1.0F);
/*  250 */     JMS_IBM_props.put("JMS_IBM_Format", String.class);
/*  251 */     JMS_IBM_props.put("JMS_IBM_MsgType", Integer.class);
/*  252 */     JMS_IBM_props.put("JMS_IBM_Feedback", Integer.class);
/*  253 */     JMS_IBM_props.put("JMS_IBM_PutApplType", Integer.class);
/*  254 */     JMS_IBM_props.put("JMS_IBM_Report_Exception", Integer.class);
/*  255 */     JMS_IBM_props.put("JMS_IBM_Report_Expiration", Integer.class);
/*  256 */     JMS_IBM_props.put("JMS_IBM_Report_COA", Integer.class);
/*  257 */     JMS_IBM_props.put("JMS_IBM_Report_COD", Integer.class);
/*  258 */     JMS_IBM_props.put("JMS_IBM_Report_PAN", Integer.class);
/*  259 */     JMS_IBM_props.put("JMS_IBM_Report_NAN", Integer.class);
/*  260 */     JMS_IBM_props.put("JMS_IBM_Report_Pass_Msg_ID", Integer.class);
/*  261 */     JMS_IBM_props.put("JMS_IBM_Report_Pass_Correl_ID", Integer.class);
/*  262 */     JMS_IBM_props.put("JMS_IBM_Report_Discard_Msg", Integer.class);
/*  263 */     JMS_IBM_props.put("JMS_IBM_Encoding", Integer.class);
/*  264 */     JMS_IBM_props.put("JMS_IBM_Character_Set", String.class);
/*  265 */     JMS_IBM_props.put("JMS_IBM_Unmappable_Action", String.class);
/*  266 */     JMS_IBM_props.put("JMS_IBM_Unmappable_Replacement", Byte.class);
/*  267 */     JMS_IBM_props.put("JMS_IBM_Last_Msg_In_Group", Boolean.class);
/*  268 */     JMS_IBM_props.put("JMS_IBM_PutDate", String.class);
/*  269 */     JMS_IBM_props.put("JMS_IBM_PutTime", String.class);
/*  270 */     JMS_IBM_props.put("JMS_IBM_ConnectionID", String.class);
/*  271 */     JMS_IBM_props.put("JMS_IBM_ArmCorrelator", String.class);
/*  272 */     JMS_IBM_props.put("JMS_IBM_RMCorrelator", String.class);
/*  273 */     JMS_IBM_props.put("JMS_TOG_ARM_Correlator", String.class);
/*  274 */     JMS_IBM_props.put("JMS_IBM_Retain", Integer.class);
/*  275 */     JMS_IBM_props.put("JMS_IBM_SubscriptionUserData", String.class);
/*      */ 
/*      */     
/*  278 */     JMS_IBM_props.put("JMS_IBM_MQMD_Report", Integer.class);
/*  279 */     JMS_IBM_props.put("JMS_IBM_MQMD_MsgType", Integer.class);
/*  280 */     JMS_IBM_props.put("JMS_IBM_MQMD_Expiry", Integer.class);
/*  281 */     JMS_IBM_props.put("JMS_IBM_MQMD_Feedback", Integer.class);
/*  282 */     JMS_IBM_props.put("JMS_IBM_MQMD_Encoding", Integer.class);
/*  283 */     JMS_IBM_props.put("JMS_IBM_MQMD_CodedCharSetId", Integer.class);
/*  284 */     JMS_IBM_props.put("JMS_IBM_MQMD_Format", String.class);
/*  285 */     JMS_IBM_props.put("JMS_IBM_MQMD_Priority", Integer.class);
/*  286 */     JMS_IBM_props.put("JMS_IBM_MQMD_Persistence", Integer.class);
/*  287 */     JMS_IBM_props.put("JMS_IBM_MQMD_MsgId", byte[].class);
/*  288 */     JMS_IBM_props.put("JMS_IBM_MQMD_CorrelId", byte[].class);
/*  289 */     JMS_IBM_props.put("JMS_IBM_MQMD_BackoutCount", Integer.class);
/*  290 */     JMS_IBM_props.put("JMS_IBM_MQMD_ReplyToQ", String.class);
/*  291 */     JMS_IBM_props.put("JMS_IBM_MQMD_ReplyToQMgr", String.class);
/*  292 */     JMS_IBM_props.put("JMS_IBM_MQMD_UserIdentifier", String.class);
/*  293 */     JMS_IBM_props.put("JMS_IBM_MQMD_AccountingToken", byte[].class);
/*  294 */     JMS_IBM_props.put("JMS_IBM_MQMD_ApplIdentityData", String.class);
/*  295 */     JMS_IBM_props.put("JMS_IBM_MQMD_PutApplType", Integer.class);
/*  296 */     JMS_IBM_props.put("JMS_IBM_MQMD_PutApplName", String.class);
/*  297 */     JMS_IBM_props.put("JMS_IBM_MQMD_PutDate", String.class);
/*  298 */     JMS_IBM_props.put("JMS_IBM_MQMD_PutTime", String.class);
/*  299 */     JMS_IBM_props.put("JMS_IBM_MQMD_ApplOriginData", String.class);
/*  300 */     JMS_IBM_props.put("JMS_IBM_MQMD_GroupId", byte[].class);
/*  301 */     JMS_IBM_props.put("JMS_IBM_MQMD_MsgSeqNumber", Integer.class);
/*  302 */     JMS_IBM_props.put("JMS_IBM_MQMD_Offset", Integer.class);
/*  303 */     JMS_IBM_props.put("JMS_IBM_MQMD_MsgFlags", Integer.class);
/*  304 */     JMS_IBM_props.put("JMS_IBM_MQMD_OriginalLength", Integer.class);
/*      */     
/*  306 */     if (Trace.isOn) {
/*  307 */       Trace.exit("com.ibm.msg.client.jms.internal.JmsMessageImpl", "static()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  312 */     jmsPropertyValidators = new HashMap<>();
/*      */ 
/*      */     
/*  315 */     if (Trace.isOn) {
/*  316 */       Trace.entry("com.ibm.msg.client.jms.internal.JmsMessageImpl", "static()");
/*      */     }
/*  318 */     if (Trace.isOn) {
/*  319 */       Trace.entry("com.ibm.msg.client.jms.internal.JmsMessageImpl", "static()");
/*      */     }
/*      */ 
/*      */     
/*  323 */     Map<Class<?>, JMSMessagePropertyValueConverter> booleanPropertyValidators = new HashMap<>();
/*  324 */     booleanPropertyValidators.put(Boolean.class, new JmsBooleanPropertyValidator());
/*  325 */     booleanPropertyValidators.put(String.class, new JmsBooleanPropertyValidator());
/*      */ 
/*      */     
/*  328 */     Map<Class<?>, JMSMessagePropertyValueConverter> bytePropertyValidators = new HashMap<>();
/*  329 */     bytePropertyValidators.put(Byte.class, new JmsBytePropertyValidator());
/*  330 */     bytePropertyValidators.put(String.class, new JmsBytePropertyValidator());
/*      */ 
/*      */     
/*  333 */     Map<Class<?>, JMSMessagePropertyValueConverter> shortPropertyValidators = new HashMap<>();
/*  334 */     shortPropertyValidators.put(Byte.class, new JmsShortPropertyValidator());
/*  335 */     shortPropertyValidators.put(Short.class, new JmsShortPropertyValidator());
/*  336 */     shortPropertyValidators.put(String.class, new JmsShortPropertyValidator());
/*      */ 
/*      */     
/*  339 */     Map<Class<?>, JMSMessagePropertyValueConverter> integerPropertyValidators = new HashMap<>();
/*  340 */     integerPropertyValidators.put(Byte.class, new JmsIntegerPropertyValidator());
/*  341 */     integerPropertyValidators.put(Short.class, new JmsIntegerPropertyValidator());
/*  342 */     integerPropertyValidators.put(Integer.class, new JmsIntegerPropertyValidator());
/*  343 */     integerPropertyValidators.put(String.class, new JmsIntegerPropertyValidator());
/*      */ 
/*      */     
/*  346 */     Map<Class<?>, JMSMessagePropertyValueConverter> longPropertyValidators = new HashMap<>();
/*  347 */     longPropertyValidators.put(Byte.class, new JmsLongPropertyValidator());
/*  348 */     longPropertyValidators.put(Short.class, new JmsLongPropertyValidator());
/*  349 */     longPropertyValidators.put(Integer.class, new JmsLongPropertyValidator());
/*  350 */     longPropertyValidators.put(Long.class, new JmsLongPropertyValidator());
/*  351 */     longPropertyValidators.put(String.class, new JmsLongPropertyValidator());
/*      */ 
/*      */     
/*  354 */     Map<Class<?>, JMSMessagePropertyValueConverter> floatPropertyValidators = new HashMap<>();
/*  355 */     floatPropertyValidators.put(Float.class, new JmsFloatPropertyValidator());
/*  356 */     floatPropertyValidators.put(String.class, new JmsFloatPropertyValidator());
/*      */ 
/*      */     
/*  359 */     Map<Class<?>, JMSMessagePropertyValueConverter> doublePropertyValidators = new HashMap<>();
/*  360 */     doublePropertyValidators.put(Float.class, new JmsDoublePropertyValidator());
/*  361 */     doublePropertyValidators.put(Double.class, new JmsDoublePropertyValidator());
/*  362 */     doublePropertyValidators.put(String.class, new JmsDoublePropertyValidator());
/*      */ 
/*      */     
/*  365 */     Map<Class<?>, JMSMessagePropertyValueConverter> stringPropertyValidators = new HashMap<>();
/*  366 */     stringPropertyValidators.put(Boolean.class, new JmsBooleanPropertyValidator());
/*  367 */     stringPropertyValidators.put(Byte.class, new JmsStringPropertyValidator());
/*  368 */     stringPropertyValidators.put(Short.class, new JmsStringPropertyValidator());
/*  369 */     stringPropertyValidators.put(Integer.class, new JmsStringPropertyValidator());
/*  370 */     stringPropertyValidators.put(Long.class, new JmsStringPropertyValidator());
/*  371 */     stringPropertyValidators.put(Float.class, new JmsStringPropertyValidator());
/*  372 */     stringPropertyValidators.put(Double.class, new JmsStringPropertyValidator());
/*  373 */     stringPropertyValidators.put(String.class, new JmsStringPropertyValidator());
/*      */ 
/*      */     
/*  376 */     jmsPropertyValidators.put(Boolean.class, booleanPropertyValidators);
/*  377 */     jmsPropertyValidators.put(Byte.class, bytePropertyValidators);
/*  378 */     jmsPropertyValidators.put(Short.class, shortPropertyValidators);
/*  379 */     jmsPropertyValidators.put(Integer.class, integerPropertyValidators);
/*  380 */     jmsPropertyValidators.put(Long.class, longPropertyValidators);
/*  381 */     jmsPropertyValidators.put(Float.class, floatPropertyValidators);
/*  382 */     jmsPropertyValidators.put(Double.class, doublePropertyValidators);
/*  383 */     jmsPropertyValidators.put(String.class, stringPropertyValidators);
/*      */     
/*  385 */     if (Trace.isOn) {
/*  386 */       Trace.exit("com.ibm.msg.client.jms.internal.JmsMessageImpl", "static()");
/*      */     }
/*  388 */     if (Trace.isOn) {
/*  389 */       Trace.exit("com.ibm.msg.client.jms.internal.JmsMessageImpl", "static()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private ProviderMessageFactory obtainFactories(String connectionTypeName) throws JMSException {
/*  399 */     if (Trace.isOn) {
/*  400 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "obtainFactories(String)", new Object[] { connectionTypeName });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  407 */     getFactories(connectionTypeName);
/*      */     
/*  409 */     if (Trace.isOn) {
/*  410 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "obtainFactories(String)", this.providerMessageFactory);
/*      */     }
/*      */     
/*  413 */     return this.providerMessageFactory;
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
/*      */   public JmsMessageImpl(JmsSessionImpl session) throws JMSException {
/*  425 */     if (Trace.isOn) {
/*  426 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "<init>(JmsSessionImpl)", new Object[] { session });
/*      */     }
/*      */ 
/*      */     
/*  430 */     assert session != null : Trace.ffstAssertion(this, "<init>(JmsSessionImpl)", "XJ001004", new Object[] { "connectionTypeName=>" + this.connectionTypeName });
/*      */ 
/*      */     
/*  433 */     this.providerMessageFactory = session.getMessageFactory();
/*  434 */     this.providerJmsFactory = session.getJmsFactory();
/*      */     
/*  436 */     this.theSession = session;
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  441 */       this.providerMessage = createProviderMessage(session);
/*      */     
/*      */     }
/*  444 */     catch (Exception e) {
/*  445 */       if (Trace.isOn) {
/*  446 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "<init>(JmsSessionImpl)", e);
/*      */       }
/*      */ 
/*      */       
/*  450 */       HashMap<String, Object> info = new HashMap<>();
/*  451 */       info.put("exception", e);
/*  452 */       Trace.ffst(this, "<init>(JmsSessionImpl)", "XJ001002", info, JMSException.class);
/*      */     } 
/*      */     
/*  455 */     this.connectionTypeName = session.getConnectionTypeName();
/*      */     
/*  457 */     this.cachedToString = null;
/*      */     
/*  459 */     if (Trace.isOn) {
/*  460 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "<init>(JmsSessionImpl)");
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
/*      */   public JmsMessageImpl(String connectionType, Message message) throws JMSException {
/*  475 */     if (Trace.isOn) {
/*  476 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "<init>(String,Message)", new Object[] { connectionType, message });
/*      */     }
/*      */ 
/*      */     
/*  480 */     obtainFactories(connectionType);
/*  481 */     this.theSession = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  487 */       this.providerMessage = createProviderMessage(null);
/*      */     
/*      */     }
/*  490 */     catch (Exception e) {
/*  491 */       if (Trace.isOn) {
/*  492 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "<init>(String,Message)", e);
/*      */       }
/*      */ 
/*      */       
/*  496 */       HashMap<String, Object> info = new HashMap<>();
/*  497 */       info.put("exception", e);
/*  498 */       Trace.ffst(this, "<init>(JmsSessionImpl)", "XJ001002", info, JMSException.class);
/*      */     } 
/*      */     
/*  501 */     this.connectionTypeName = connectionType;
/*      */ 
/*      */     
/*  504 */     copyMessageState(message);
/*      */     
/*  506 */     this.cachedToString = null;
/*      */     
/*  508 */     if (Trace.isOn) {
/*  509 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "<init>(String,Message)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   JmsMessageImpl(ProviderMessage providerMessage, JmsSessionImpl newSess, String connectionTypeName) throws JMSException {
/*  519 */     if (Trace.isOn) {
/*  520 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "<init>(ProviderMessage,JmsSessionImpl,String)", new Object[] { providerMessage, newSess, connectionTypeName });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  525 */     this.connectionTypeName = connectionTypeName;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  532 */     if (newSess != null) {
/*  533 */       this.providerMessageFactory = newSess.getMessageFactory();
/*  534 */       this.providerJmsFactory = newSess.getJmsFactory();
/*      */     } else {
/*      */       
/*  537 */       obtainFactories(connectionTypeName);
/*      */     } 
/*      */     
/*  540 */     this.providerMessage = providerMessage;
/*  541 */     this.theSession = newSess;
/*  542 */     this.messageType = "jms_none";
/*      */     
/*  544 */     this.cachedToString = null;
/*      */     
/*  546 */     if (Trace.isOn) {
/*  547 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "<init>(ProviderMessage,JmsSessionImpl,String)");
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
/*      */   JmsMessageImpl(JmsSessionImpl session, Message message) throws JMSException {
/*  563 */     this(session);
/*  564 */     if (Trace.isOn) {
/*  565 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "<init>(JmsSessionImpl,Message)", new Object[] { session, message });
/*      */     }
/*      */ 
/*      */     
/*  569 */     this.theSession = session;
/*      */ 
/*      */     
/*  572 */     copyMessageState(message);
/*      */     
/*  574 */     this.cachedToString = null;
/*      */     
/*  576 */     if (Trace.isOn) {
/*  577 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "<init>(JmsSessionImpl,Message)");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void copyMessageState(Message message) throws JMSException {
/*      */     JmsDestinationImpl jmsDestinationImpl;
/*  584 */     if (Trace.isOn) {
/*  585 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "copyMessageState(Message)", new Object[] { message });
/*      */     }
/*      */ 
/*      */     
/*  589 */     if (message == null) {
/*  590 */       if (Trace.isOn) {
/*  591 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "copyMessageState(Message)", 1);
/*      */       }
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  598 */     setJMSCorrelationID(message.getJMSCorrelationID());
/*  599 */     setJMSType(message.getJMSType());
/*      */     
/*  601 */     setJMSDeliveryMode(message.getJMSDeliveryMode());
/*  602 */     setJMSExpiration(message.getJMSExpiration());
/*  603 */     setJMSPriority(message.getJMSPriority());
/*  604 */     setJMSRedelivered(message.getJMSRedelivered());
/*  605 */     setJMSTimestamp(message.getJMSTimestamp());
/*  606 */     setJMSMessageID(message.getJMSMessageID());
/*  607 */     setJMSDestination(message.getJMSDestination());
/*      */ 
/*      */ 
/*      */     
/*  611 */     Destination replyTo = message.getJMSReplyTo();
/*  612 */     if (replyTo instanceof JmsDestinationImpl) {
/*  613 */       jmsDestinationImpl = createDestination(this.connectionTypeName, (JmsDestinationImpl)replyTo);
/*      */     }
/*  615 */     setJMSReplyTo((Destination)jmsDestinationImpl);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  621 */     Enumeration<?> propertyNames = message.getPropertyNames();
/*  622 */     while (propertyNames != null && propertyNames.hasMoreElements()) {
/*  623 */       String name = (String)propertyNames.nextElement();
/*  624 */       Object value = message.getObjectProperty(name);
/*      */ 
/*      */       
/*      */       try {
/*  628 */         setObjectProperty(name, value);
/*      */       }
/*  630 */       catch (Exception e) {
/*  631 */         if (Trace.isOn) {
/*  632 */           Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "copyMessageState(Message)", e);
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  638 */     if (Trace.isOn) {
/*  639 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "copyMessageState(Message)", 2);
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
/*      */   public String getJMSMessageID() throws JMSException {
/*  651 */     String result = this.providerMessage.getJMSMessageID();
/*  652 */     if (Trace.isOn) {
/*  653 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getJMSMessageID()", "getter", result);
/*      */     }
/*      */     
/*  656 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setJMSMessageID(String newMsgId) throws JMSException {
/*  666 */     if (Trace.isOn) {
/*  667 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "setJMSMessageID(String)", "setter", newMsgId);
/*      */     }
/*      */ 
/*      */     
/*  671 */     this.providerMessage.setJMSMessageID(newMsgId);
/*      */     
/*  673 */     invalidateToStringCache();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getJMSTimestamp() throws JMSException {
/*  682 */     Long ts = this.providerMessage.getJMSTimestamp();
/*      */     
/*  684 */     long val = 0L;
/*  685 */     if (ts != null) {
/*  686 */       val = ts.longValue();
/*      */     } else {
/*      */       
/*  689 */       val = 0L;
/*      */     } 
/*      */     
/*  692 */     if (Trace.isOn) {
/*  693 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getJMSTimestamp()", "getter", 
/*  694 */           Long.valueOf(val));
/*      */     }
/*  696 */     return val;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setJMSTimestamp(long time) throws JMSException {
/*  704 */     if (Trace.isOn) {
/*  705 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "setJMSTimestamp(long)", "setter", 
/*  706 */           Long.valueOf(time));
/*      */     }
/*      */     
/*  709 */     this.providerMessage.setJMSTimestamp(time);
/*      */     
/*  711 */     invalidateToStringCache();
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
/*      */   public byte[] getJMSCorrelationIDAsBytes() throws JMSException {
/*  723 */     byte[] result = this.providerMessage.getJMSCorrelationIDAsBytes();
/*  724 */     if (Trace.isOn) {
/*  725 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getJMSCorrelationIDAsBytes()", "getter", result);
/*      */     }
/*      */     
/*  728 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setJMSCorrelationIDAsBytes(byte[] correlIdBytes) throws JMSException {
/*  738 */     if (Trace.isOn) {
/*  739 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "setJMSCorrelationIDAsBytes(byte [ ])", "setter", correlIdBytes);
/*      */     }
/*      */ 
/*      */     
/*  743 */     this.providerMessage.setJMSCorrelationIDAsBytes(correlIdBytes);
/*      */     
/*  745 */     invalidateToStringCache();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setJMSCorrelationID(String correl) throws JMSException {
/*  753 */     if (Trace.isOn) {
/*  754 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "setJMSCorrelationID(String)", "setter", correl);
/*      */     }
/*      */ 
/*      */     
/*  758 */     this.providerMessage.setJMSCorrelationID(correl);
/*      */     
/*  760 */     invalidateToStringCache();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getJMSCorrelationID() throws JMSException {
/*  769 */     String correl = this.providerMessage.getJMSCorrelationID();
/*      */     
/*  771 */     if (Trace.isOn) {
/*  772 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getJMSCorrelationID()", "getter", correl);
/*      */     }
/*      */     
/*  775 */     return correl;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Destination getJMSReplyTo() throws JMSException {
/*  784 */     if (Trace.isOn) {
/*  785 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getJMSReplyTo()");
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  790 */       if (this.jmsReplyTo != null)
/*  791 */       { if (Trace.isOn) {
/*  792 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getJMSReplyTo()", this.jmsReplyTo, 1);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  812 */         return this.jmsReplyTo; }  String providerReplyTo = this.providerMessage.getJMSReplyToAsString(); return this.jmsReplyTo;
/*      */     } finally {
/*      */       Exception exception = null;
/*      */       if (Trace.isOn)
/*      */         Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getJMSReplyTo()"); 
/*      */       if (Trace.isOn)
/*      */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getJMSReplyTo()", this.jmsReplyTo, 2); 
/*      */     } 
/*      */   } public void setJMSReplyTo(Destination destination) throws JMSException {
/*  821 */     if (Trace.isOn) {
/*  822 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "setJMSReplyTo(Destination)", "setter", destination);
/*      */     }
/*      */ 
/*      */     
/*  826 */     invalidateToStringCache();
/*      */ 
/*      */     
/*  829 */     this.jmsReplyTo = destination;
/*      */     
/*  831 */     if (destination == null) {
/*  832 */       this.providerMessage.setJMSReplyToAsString(null);
/*      */     }
/*  834 */     else if (destination instanceof JmsDestinationImpl) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  839 */       JmsDestinationImpl jmsReplyTo = (JmsDestinationImpl)destination;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  846 */       if (!jmsReplyTo.getStringProperty("XMSC_CONNECTION_TYPE_NAME").equals(this.connectionTypeName)) {
/*  847 */         HashMap<String, Object> inserts = new HashMap<>();
/*  848 */         inserts.put("XMSC_INSERT_VALUE", jmsReplyTo.getStringProperty("XMSC_CONNECTION_TYPE_NAME"));
/*  849 */         inserts.put("XMSC_INSERT_NAME", "JMSReplyTo");
/*  850 */         JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0003", inserts);
/*  851 */         if (Trace.isOn) {
/*  852 */           Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "setJMSReplyTo(Destination)", (Throwable)je);
/*      */         }
/*      */         
/*  855 */         throw je;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  860 */       ProviderDestination providerDestination = JmsDestinationImplProxy.getProviderDestination(jmsReplyTo);
/*      */       
/*  862 */       if (Trace.isOn) {
/*  863 */         Trace.data(this, "Setting ProviderDestination in providerMessage", providerDestination);
/*      */       }
/*      */       
/*  866 */       this.providerMessage.setJMSReplyToAsString(providerDestination.toURI());
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  876 */       if (Trace.isOn) {
/*  877 */         Trace.data(this, "object is a foreign Destination. Attempting to create a ProviderDestination", destination.getClass().getName());
/*      */       }
/*  879 */       String name = null;
/*  880 */       if (destination instanceof Queue) {
/*  881 */         name = ((Queue)destination).getQueueName();
/*      */       }
/*  883 */       else if (destination instanceof Topic) {
/*  884 */         name = ((Topic)destination).getTopicName();
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  890 */         JmsDestinationImpl jmsReplyTo = new JmsDestinationImpl(this.connectionTypeName, name);
/*      */         
/*  892 */         ProviderDestination providerDestination = JmsDestinationImplProxy.getProviderDestination(jmsReplyTo);
/*  893 */         this.providerMessage.setJMSReplyToAsString(providerDestination.toURI());
/*      */       }
/*  895 */       catch (Exception e) {
/*  896 */         if (Trace.isOn) {
/*  897 */           Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "setJMSReplyTo(Destination)", e);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  903 */         if (Trace.isOn) {
/*  904 */           Trace.data(this, "Failed to create JMSReplyTo ProviderDestination from foreign Destination object.  The message will not contian a JMSReplyTo field", e);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  909 */         this.providerMessage.setJMSReplyToAsString(null);
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
/*      */   private String getJMSDestinationAsString() throws JMSException {
/*  924 */     if (Trace.isOn) {
/*  925 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getJMSDestinationAsString()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  931 */     String jmsDestinationAsString = null;
/*      */     try {
/*  933 */       if (this.jmsDestination != null) {
/*  934 */         jmsDestinationAsString = this.jmsDestination.toString();
/*      */       } else {
/*      */         
/*  937 */         jmsDestinationAsString = this.providerMessage.getJMSDestinationAsString();
/*      */       }
/*      */     
/*  940 */     } catch (JMSException je) {
/*  941 */       if (Trace.isOn) {
/*  942 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getJMSDestinationAsString()", (Throwable)je);
/*      */         
/*  944 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getJMSDestinationAsString()", (Throwable)je);
/*      */       } 
/*      */       
/*  947 */       throw je;
/*      */     } 
/*  949 */     if (Trace.isOn) {
/*  950 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getJMSDestinationAsString()", jmsDestinationAsString);
/*      */     }
/*      */     
/*  953 */     return jmsDestinationAsString;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Destination getJMSDestination() throws JMSException {
/*  961 */     if (Trace.isOn) {
/*  962 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getJMSDestination()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  969 */       if (this.jmsDestination != null) {
/*  970 */         if (Trace.isOn) {
/*  971 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getJMSDestination()", this.jmsDestination, 1);
/*      */         }
/*      */         
/*  974 */         return this.jmsDestination;
/*      */       } 
/*  976 */       String providerDest = this.providerMessage.getJMSDestinationAsString();
/*  977 */       JmsDestinationImpl newDestination = providerDestToJmsDest(providerDest);
/*      */       
/*  979 */       if (Trace.isOn) {
/*  980 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getJMSDestination()", newDestination, 2);
/*      */       }
/*      */       
/*  983 */       return (Destination)newDestination;
/*      */     } finally {
/*      */       
/*  986 */       if (Trace.isOn) {
/*  987 */         Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getJMSDestination()");
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setJMSDestination(Destination destination) throws JMSException {
/*  998 */     if (Trace.isOn) {
/*  999 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "setJMSDestination(Destination)", "setter", destination);
/*      */     }
/*      */ 
/*      */     
/* 1003 */     invalidateToStringCache();
/*      */     
/* 1005 */     if (destination == null) {
/* 1006 */       this.providerMessage.setJMSDestinationAsString(null);
/* 1007 */       this.jmsDestination = null;
/*      */     
/*      */     }
/* 1010 */     else if (destination instanceof JmsDestinationImpl) {
/*      */       
/* 1012 */       JmsDestinationImpl jmsDestination = (JmsDestinationImpl)destination;
/*      */       
/* 1014 */       if (jmsDestination.getStringProperty("XMSC_CONNECTION_TYPE_NAME").equals(this.connectionTypeName))
/*      */       {
/* 1016 */         if (Trace.isOn) {
/* 1017 */           Trace.data(this, "destination is a common client Destination from the same provider as this Message.Storing the ProviderDestination in the ProviderMessage", null);
/*      */         }
/* 1019 */         ProviderDestination providerDestination = JmsDestinationImplProxy.getProviderDestination(jmsDestination);
/* 1020 */         this.providerMessage.setJMSDestinationAsString(providerDestination.toURI());
/*      */ 
/*      */         
/* 1023 */         jmsDestination = null;
/*      */       }
/*      */       else
/*      */       {
/* 1027 */         if (Trace.isOn) {
/* 1028 */           Trace.data(this, "destination is a common client Destination, but is from a different provider to this Message.Storing the Destination in the common client Message", null);
/*      */         }
/*      */         
/* 1031 */         this.jmsDestination = destination;
/* 1032 */         this.providerMessage.setJMSDestinationAsString(null);
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 1038 */       if (Trace.isOn) {
/* 1039 */         Trace.data(this, "destination is from a non-common client JMS implementation. Storing the Destination in the common client Message", null);
/*      */       }
/*      */       
/* 1042 */       this.jmsDestination = destination;
/* 1043 */       this.providerMessage.setJMSDestinationAsString(null);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getJMSDeliveryMode() throws JMSException {
/* 1054 */     Integer dm = this.providerMessage.getJMSDeliveryMode();
/*      */     
/* 1056 */     int val = 0;
/* 1057 */     if (dm != null) {
/* 1058 */       val = dm.intValue();
/*      */     } else {
/*      */       
/* 1061 */       val = 2;
/*      */     } 
/*      */     
/* 1064 */     if (Trace.isOn) {
/* 1065 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getJMSDeliveryMode()", "getter", 
/* 1066 */           Integer.valueOf(val));
/*      */     }
/* 1068 */     return val;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setJMSDeliveryMode(int dm) throws JMSException {
/*      */     HashMap<String, Object> inserts;
/*      */     JMSException je;
/* 1076 */     if (Trace.isOn) {
/* 1077 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "setJMSDeliveryMode(int)", "setter", 
/* 1078 */           Integer.valueOf(dm));
/*      */     }
/*      */     
/* 1081 */     switch (dm) {
/*      */       case 1:
/*      */       case 2:
/* 1084 */         this.providerMessage.setJMSDeliveryMode(dm);
/*      */         break;
/*      */ 
/*      */       
/*      */       default:
/* 1089 */         inserts = new HashMap<>();
/* 1090 */         inserts.put("XMSC_INSERT_VALUE", String.valueOf(dm));
/* 1091 */         inserts.put("XMSC_INSERT_NAME", "JMSDeliveryMode");
/* 1092 */         je = (JMSException)JmsErrorUtils.createException("JMSCC0005", inserts);
/* 1093 */         if (Trace.isOn) {
/* 1094 */           Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "setJMSDeliveryMode(int)", (Throwable)je);
/*      */         }
/*      */         
/* 1097 */         throw je;
/*      */     } 
/*      */     
/* 1100 */     invalidateToStringCache();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getJMSRedelivered() throws JMSException {
/* 1110 */     Boolean redeliv = this.providerMessage.getJMSRedelivered();
/*      */     
/* 1112 */     boolean val = false;
/* 1113 */     if (redeliv != null) {
/* 1114 */       val = redeliv.booleanValue();
/*      */     } else {
/*      */       
/* 1117 */       val = false;
/*      */     } 
/*      */     
/* 1120 */     if (Trace.isOn) {
/* 1121 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getJMSRedelivered()", "getter", 
/* 1122 */           Boolean.valueOf(val));
/*      */     }
/* 1124 */     return val;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setJMSRedelivered(boolean redelivered) throws JMSException {
/* 1133 */     if (Trace.isOn) {
/* 1134 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "setJMSRedelivered(boolean)", "setter", 
/* 1135 */           Boolean.valueOf(redelivered));
/*      */     }
/*      */     
/* 1138 */     this.providerMessage.setJMSRedelivered(redelivered);
/*      */     
/* 1140 */     invalidateToStringCache();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getJMSType() throws JMSException {
/* 1149 */     String type = this.providerMessage.getJMSType();
/*      */     
/* 1151 */     if (Trace.isOn) {
/* 1152 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getJMSType()", "getter", type);
/*      */     }
/*      */     
/* 1155 */     return type;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setJMSType(String jmsType) throws JMSException {
/* 1163 */     if (Trace.isOn) {
/* 1164 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "setJMSType(String)", "setter", jmsType);
/*      */     }
/*      */ 
/*      */     
/* 1168 */     this.providerMessage.setJMSType(jmsType);
/*      */     
/* 1170 */     invalidateToStringCache();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getJMSExpiration() throws JMSException {
/* 1179 */     Long ex = this.providerMessage.getJMSExpiration();
/* 1180 */     long exl = 0L;
/* 1181 */     if (ex != null) {
/* 1182 */       exl = ex.longValue();
/*      */     }
/*      */     else {
/*      */       
/* 1186 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1187 */       inserts.put("XMSC_INSERT_PROPERTY", "JMSExpiration");
/* 1188 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0084", inserts);
/* 1189 */       if (Trace.isOn) {
/* 1190 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getJMSExpiration()", (Throwable)je);
/*      */       }
/*      */       
/* 1193 */       throw je;
/*      */     } 
/*      */     
/* 1196 */     if (Trace.isOn) {
/* 1197 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getJMSExpiration()", "getter", 
/* 1198 */           Long.valueOf(exl));
/*      */     }
/* 1200 */     return exl;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setJMSExpiration(long exp) throws JMSException {
/* 1208 */     if (Trace.isOn) {
/* 1209 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "setJMSExpiration(long)", "setter", 
/* 1210 */           Long.valueOf(exp));
/*      */     }
/*      */     
/* 1213 */     this.providerMessage.setJMSExpiration(exp);
/*      */     
/* 1215 */     invalidateToStringCache();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getJMSPriority() throws JMSException {
/* 1223 */     if (Trace.isOn) {
/* 1224 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getJMSPriority()");
/*      */     }
/*      */     
/* 1227 */     Integer tempPri = this.providerMessage.getJMSPriority();
/* 1228 */     int p = 0;
/* 1229 */     if (tempPri != null) {
/* 1230 */       p = tempPri.intValue();
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 1236 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1237 */       inserts.put("XMSC_INSERT_PROPERTY", "JMSPriority");
/* 1238 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0084", inserts);
/* 1239 */       if (Trace.isOn) {
/* 1240 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getJMSPriority()", (Throwable)je);
/*      */       }
/*      */       
/* 1243 */       throw je;
/*      */     } 
/*      */     
/* 1246 */     if (Trace.isOn) {
/* 1247 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getJMSPriority()", 
/* 1248 */           Integer.valueOf(p));
/*      */     }
/* 1250 */     return p;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setJMSPriority(int newPriority) throws JMSException {
/* 1259 */     if (Trace.isOn) {
/* 1260 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "setJMSPriority(int)", "setter", 
/* 1261 */           Integer.valueOf(newPriority));
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 1266 */       this.providerMessage.setJMSPriority(newPriority);
/*      */       
/* 1268 */       invalidateToStringCache();
/*      */     
/*      */     }
/* 1271 */     catch (IllegalArgumentException iae) {
/* 1272 */       if (Trace.isOn) {
/* 1273 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "setJMSPriority(int)", iae);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1279 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1280 */       inserts.put("XMSC_INSERT_VALUE", "" + newPriority);
/* 1281 */       inserts.put("XMSC_INSERT_NAME", "JMSPriority");
/* 1282 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0005", inserts);
/* 1283 */       je.setLinkedException(iae);
/* 1284 */       if (Trace.isOn) {
/* 1285 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "setJMSPriority(int)", (Throwable)je);
/*      */       }
/*      */       
/* 1288 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clearProperties() throws JMSException {
/* 1298 */     if (Trace.isOn) {
/* 1299 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "clearProperties()");
/*      */     }
/*      */ 
/*      */     
/* 1303 */     this.providerMessage.clearProperties();
/*      */ 
/*      */     
/* 1306 */     this.propertiesReadOnly = false;
/*      */     
/* 1308 */     invalidateToStringCache();
/* 1309 */     if (Trace.isOn) {
/* 1310 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "clearProperties()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean propertyExists(String name) throws JMSException {
/* 1320 */     if (Trace.isOn) {
/* 1321 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "propertyExists(String)", new Object[] { name });
/*      */     }
/*      */     
/* 1324 */     boolean result = this.providerMessage.propertyExists(name);
/* 1325 */     if (Trace.isOn) {
/* 1326 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "propertyExists(String)", 
/* 1327 */           Boolean.valueOf(result));
/*      */     }
/* 1329 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getBooleanProperty(String name) throws JMSException {
/*      */     boolean result;
/* 1337 */     if (Trace.isOn) {
/* 1338 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getBooleanProperty(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1345 */       Object obj = getObjectProperty(name);
/*      */       
/* 1347 */       result = JmsPropertyContextImpl.parseBoolean(obj, name, (Class)MessageFormatException.class);
/*      */     }
/* 1349 */     catch (JMSException jmse) {
/* 1350 */       if (Trace.isOn) {
/* 1351 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getBooleanProperty(String)", (Throwable)jmse);
/*      */       }
/*      */       
/* 1354 */       if (Trace.isOn) {
/* 1355 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getBooleanProperty(String)", (Throwable)jmse);
/*      */       }
/* 1357 */       if (Trace.isOn) {
/* 1358 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getBooleanProperty(String)", (Throwable)jmse);
/*      */       }
/*      */       
/* 1361 */       throw jmse;
/*      */     } 
/*      */     
/* 1364 */     if (Trace.isOn) {
/* 1365 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getBooleanProperty(String)", 
/* 1366 */           Boolean.valueOf(result));
/*      */     }
/* 1368 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte getByteProperty(String name) throws JMSException {
/*      */     byte result;
/* 1376 */     if (Trace.isOn) {
/* 1377 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getByteProperty(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1384 */       Object obj = getObjectProperty(name);
/*      */       
/* 1386 */       result = JmsPropertyContextImpl.parseByte(obj, name, (Class)MessageFormatException.class);
/*      */     }
/* 1388 */     catch (JMSException jmse) {
/* 1389 */       if (Trace.isOn) {
/* 1390 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getByteProperty(String)", (Throwable)jmse, 1);
/*      */       }
/*      */       
/* 1393 */       if (Trace.isOn) {
/* 1394 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getByteProperty(String)", (Throwable)jmse);
/*      */       }
/* 1396 */       if (Trace.isOn) {
/* 1397 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getByteProperty(String)", (Throwable)jmse, 1);
/*      */       }
/*      */       
/* 1400 */       throw jmse;
/*      */     }
/* 1402 */     catch (NumberFormatException nfe) {
/* 1403 */       if (Trace.isOn) {
/* 1404 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getByteProperty(String)", nfe, 2);
/*      */       }
/*      */       
/* 1407 */       if (Trace.isOn) {
/* 1408 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getByteProperty(String)", nfe);
/*      */       }
/* 1410 */       if (Trace.isOn) {
/* 1411 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getByteProperty(String)", nfe, 2);
/*      */       }
/*      */       
/* 1414 */       throw nfe;
/*      */     } 
/*      */     
/* 1417 */     if (Trace.isOn) {
/* 1418 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getByteProperty(String)", 
/* 1419 */           Byte.valueOf(result));
/*      */     }
/* 1421 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public short getShortProperty(String name) throws JMSException {
/*      */     short result;
/* 1429 */     if (Trace.isOn) {
/* 1430 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getShortProperty(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1437 */       Object obj = getObjectProperty(name);
/*      */       
/* 1439 */       result = JmsPropertyContextImpl.parseShort(obj, name, (Class)MessageFormatException.class);
/*      */     }
/* 1441 */     catch (JMSException jmse) {
/* 1442 */       if (Trace.isOn) {
/* 1443 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getShortProperty(String)", (Throwable)jmse, 1);
/*      */       }
/*      */       
/* 1446 */       if (Trace.isOn) {
/* 1447 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getShortProperty(String)", (Throwable)jmse);
/*      */       }
/* 1449 */       if (Trace.isOn) {
/* 1450 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getShortProperty(String)", (Throwable)jmse, 1);
/*      */       }
/*      */       
/* 1453 */       throw jmse;
/*      */     }
/* 1455 */     catch (NumberFormatException nfe) {
/* 1456 */       if (Trace.isOn) {
/* 1457 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getShortProperty(String)", nfe, 2);
/*      */       }
/*      */       
/* 1460 */       if (Trace.isOn) {
/* 1461 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getShortProperty(String)", nfe);
/*      */       }
/* 1463 */       if (Trace.isOn) {
/* 1464 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getShortProperty(String)", nfe, 2);
/*      */       }
/*      */       
/* 1467 */       throw nfe;
/*      */     } 
/*      */     
/* 1470 */     if (Trace.isOn) {
/* 1471 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getShortProperty(String)", 
/* 1472 */           Short.valueOf(result));
/*      */     }
/* 1474 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getIntProperty(String name) throws JMSException {
/*      */     int result;
/* 1482 */     if (Trace.isOn) {
/* 1483 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getIntProperty(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1489 */       Object obj = getObjectProperty(name);
/*      */       
/* 1491 */       result = JmsPropertyContextImpl.parseInt(obj, name, (Class)MessageFormatException.class);
/*      */     }
/* 1493 */     catch (JMSException jmse) {
/* 1494 */       if (Trace.isOn) {
/* 1495 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getIntProperty(String)", (Throwable)jmse, 1);
/*      */       }
/*      */       
/* 1498 */       if (Trace.isOn) {
/* 1499 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getIntProperty(String)", (Throwable)jmse);
/*      */       }
/* 1501 */       if (Trace.isOn) {
/* 1502 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getIntProperty(String)", (Throwable)jmse, 1);
/*      */       }
/*      */       
/* 1505 */       throw jmse;
/*      */     }
/* 1507 */     catch (NumberFormatException nfe) {
/* 1508 */       if (Trace.isOn) {
/* 1509 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getIntProperty(String)", nfe, 2);
/*      */       }
/*      */       
/* 1512 */       if (Trace.isOn) {
/* 1513 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getIntProperty(String)", nfe);
/*      */       }
/* 1515 */       if (Trace.isOn) {
/* 1516 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getIntProperty(String)", nfe, 2);
/*      */       }
/*      */       
/* 1519 */       throw nfe;
/*      */     } 
/*      */     
/* 1522 */     if (Trace.isOn) {
/* 1523 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getIntProperty(String)", 
/* 1524 */           Integer.valueOf(result));
/*      */     }
/* 1526 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getLongProperty(String name) throws JMSException {
/*      */     long result;
/* 1534 */     if (Trace.isOn) {
/* 1535 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getLongProperty(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1542 */       Object obj = getObjectProperty(name);
/*      */       
/* 1544 */       result = JmsPropertyContextImpl.parseLong(obj, name, (Class)MessageFormatException.class);
/*      */     }
/* 1546 */     catch (JMSException jmse) {
/* 1547 */       if (Trace.isOn) {
/* 1548 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getLongProperty(String)", (Throwable)jmse, 1);
/*      */       }
/*      */       
/* 1551 */       if (Trace.isOn) {
/* 1552 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getLongProperty(String)", (Throwable)jmse);
/*      */       }
/* 1554 */       if (Trace.isOn) {
/* 1555 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getLongProperty(String)", (Throwable)jmse, 1);
/*      */       }
/*      */       
/* 1558 */       throw jmse;
/*      */     }
/* 1560 */     catch (NumberFormatException nfe) {
/* 1561 */       if (Trace.isOn) {
/* 1562 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getLongProperty(String)", nfe, 2);
/*      */       }
/*      */       
/* 1565 */       if (Trace.isOn) {
/* 1566 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getLongProperty(String)", nfe);
/*      */       }
/* 1568 */       if (Trace.isOn) {
/* 1569 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getLongProperty(String)", nfe, 2);
/*      */       }
/*      */       
/* 1572 */       throw nfe;
/*      */     } 
/*      */     
/* 1575 */     if (Trace.isOn) {
/* 1576 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getLongProperty(String)", 
/* 1577 */           Long.valueOf(result));
/*      */     }
/* 1579 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getFloatProperty(String name) throws JMSException {
/*      */     float result;
/* 1587 */     if (Trace.isOn) {
/* 1588 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getFloatProperty(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1595 */       Object obj = getObjectProperty(name);
/*      */       
/* 1597 */       result = JmsPropertyContextImpl.parseFloat(obj, name, (Class)MessageFormatException.class);
/*      */     }
/* 1599 */     catch (JMSException jmse) {
/* 1600 */       if (Trace.isOn) {
/* 1601 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getFloatProperty(String)", (Throwable)jmse, 1);
/*      */       }
/*      */       
/* 1604 */       if (Trace.isOn) {
/* 1605 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getFloatProperty(String)", (Throwable)jmse);
/*      */       }
/* 1607 */       if (Trace.isOn) {
/* 1608 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getFloatProperty(String)", (Throwable)jmse, 1);
/*      */       }
/*      */       
/* 1611 */       throw jmse;
/*      */     }
/* 1613 */     catch (NumberFormatException nfe) {
/* 1614 */       if (Trace.isOn) {
/* 1615 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getFloatProperty(String)", nfe, 2);
/*      */       }
/*      */       
/* 1618 */       if (Trace.isOn) {
/* 1619 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getFloatProperty(String)", nfe);
/*      */       }
/* 1621 */       if (Trace.isOn) {
/* 1622 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getFloatProperty(String)", nfe, 2);
/*      */       }
/*      */       
/* 1625 */       throw nfe;
/*      */     } 
/*      */     
/* 1628 */     if (Trace.isOn) {
/* 1629 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getFloatProperty(String)", 
/* 1630 */           Float.valueOf(result));
/*      */     }
/* 1632 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getDoubleProperty(String name) throws JMSException {
/*      */     double result;
/* 1640 */     if (Trace.isOn) {
/* 1641 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getDoubleProperty(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1648 */       Object obj = getObjectProperty(name);
/*      */       
/* 1650 */       result = JmsPropertyContextImpl.parseDouble(obj, name, (Class)MessageFormatException.class);
/*      */     }
/* 1652 */     catch (JMSException jmse) {
/* 1653 */       if (Trace.isOn) {
/* 1654 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getDoubleProperty(String)", (Throwable)jmse, 1);
/*      */       }
/*      */       
/* 1657 */       if (Trace.isOn) {
/* 1658 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getDoubleProperty(String)", (Throwable)jmse);
/*      */       }
/* 1660 */       if (Trace.isOn) {
/* 1661 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getDoubleProperty(String)", (Throwable)jmse, 1);
/*      */       }
/*      */       
/* 1664 */       throw jmse;
/*      */     }
/* 1666 */     catch (NumberFormatException nfe) {
/* 1667 */       if (Trace.isOn) {
/* 1668 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getDoubleProperty(String)", nfe, 2);
/*      */       }
/*      */       
/* 1671 */       if (Trace.isOn) {
/* 1672 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getDoubleProperty(String)", nfe);
/*      */       }
/* 1674 */       if (Trace.isOn) {
/* 1675 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getDoubleProperty(String)", nfe, 2);
/*      */       }
/*      */       
/* 1678 */       throw nfe;
/*      */     } 
/*      */     
/* 1681 */     if (Trace.isOn) {
/* 1682 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getDoubleProperty(String)", 
/* 1683 */           Double.valueOf(result));
/*      */     }
/* 1685 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getStringProperty(String name) throws JMSException {
/* 1693 */     if (Trace.isOn) {
/* 1694 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getStringProperty(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1699 */     Object obj = null;
/*      */     
/*      */     try {
/* 1702 */       if (name != null && name.equals("JMS_TOG_ARM_Correlator")) {
/* 1703 */         obj = getObjectProperty("JMS_IBM_ArmCorrelator");
/*      */       }
/*      */       else {
/*      */         
/* 1707 */         obj = getObjectProperty(name);
/*      */       }
/*      */     
/*      */     }
/* 1711 */     catch (JMSException jmse) {
/* 1712 */       if (Trace.isOn) {
/* 1713 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getStringProperty(String)", (Throwable)jmse);
/*      */       }
/*      */       
/* 1716 */       if (Trace.isOn) {
/* 1717 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getStringProperty(String)", (Throwable)jmse);
/*      */       }
/* 1719 */       if (Trace.isOn) {
/* 1720 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getStringProperty(String)", (Throwable)jmse);
/*      */       }
/*      */       
/* 1723 */       throw jmse;
/*      */     } 
/*      */     
/* 1726 */     String value = null;
/*      */     
/* 1728 */     if (obj instanceof String || obj == null) {
/* 1729 */       value = (String)obj;
/*      */     }
/*      */     else {
/*      */       
/* 1733 */       value = obj.toString();
/*      */     } 
/*      */     
/* 1736 */     if (Trace.isOn) {
/* 1737 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getStringProperty(String)", value);
/*      */     }
/*      */     
/* 1740 */     return value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getObjectProperty(String name) throws JMSException {
/* 1748 */     if (Trace.isOn) {
/* 1749 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getObjectProperty(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/* 1753 */     if (name == null || "".equals(name)) {
/* 1754 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1755 */       inserts.put("XMSC_INSERT_PROPERTY", name);
/* 1756 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0049", inserts);
/* 1757 */       if (Trace.isOn) {
/* 1758 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getObjectProperty(String)", (Throwable)je);
/*      */       }
/*      */       
/* 1761 */       throw je;
/*      */     } 
/*      */     
/* 1764 */     Object result = this.providerMessage.getObjectProperty(name);
/* 1765 */     if (Trace.isOn) {
/* 1766 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getObjectProperty(String)", result);
/*      */     }
/*      */     
/* 1769 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Enumeration<String> getPropertyNames() throws JMSException {
/* 1778 */     Enumeration<String> result = this.providerMessage.getPropertyNames();
/* 1779 */     if (Trace.isOn) {
/* 1780 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getPropertyNames()", "getter", result);
/*      */     }
/*      */     
/* 1783 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBooleanProperty(String name, boolean val) throws JMSException {
/* 1791 */     if (Trace.isOn) {
/* 1792 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "setBooleanProperty(String,boolean)", new Object[] { name, 
/* 1793 */             Boolean.valueOf(val) });
/*      */     }
/*      */     
/* 1796 */     checkPropertiesWriteable("setBooleanProperty");
/* 1797 */     checkPropName(name, "setBooleanProperty");
/* 1798 */     checkSettablePropertyName(name, Boolean.class);
/*      */     
/* 1800 */     this.providerMessage.setObjectProperty(name, Boolean.valueOf(val));
/*      */     
/* 1802 */     invalidateToStringCache();
/* 1803 */     if (Trace.isOn) {
/* 1804 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "setBooleanProperty(String,boolean)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setByteProperty(String name, byte val) throws JMSException {
/* 1815 */     if (Trace.isOn) {
/* 1816 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "setByteProperty(String,byte)", new Object[] { name, 
/* 1817 */             Byte.valueOf(val) });
/*      */     }
/*      */     
/* 1820 */     checkPropertiesWriteable("setByteProperty");
/* 1821 */     checkPropName(name, "setByteProperty");
/* 1822 */     checkSettablePropertyName(name, Byte.class);
/*      */     
/* 1824 */     this.providerMessage.setObjectProperty(name, Byte.valueOf(val));
/*      */     
/* 1826 */     invalidateToStringCache();
/* 1827 */     if (Trace.isOn) {
/* 1828 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "setByteProperty(String,byte)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setShortProperty(String name, short val) throws JMSException {
/* 1839 */     if (Trace.isOn) {
/* 1840 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "setShortProperty(String,short)", new Object[] { name, 
/* 1841 */             Short.valueOf(val) });
/*      */     }
/*      */     
/* 1844 */     checkPropertiesWriteable("setShortProperty");
/* 1845 */     checkPropName(name, "setShortProperty");
/* 1846 */     checkSettablePropertyName(name, Short.class);
/*      */     
/* 1848 */     this.providerMessage.setObjectProperty(name, Short.valueOf(val));
/*      */     
/* 1850 */     invalidateToStringCache();
/* 1851 */     if (Trace.isOn) {
/* 1852 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "setShortProperty(String,short)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setIntProperty(String name, int val) throws JMSException {
/* 1863 */     if (Trace.isOn) {
/* 1864 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "setIntProperty(String,int)", new Object[] { name, 
/* 1865 */             Integer.valueOf(val) });
/*      */     }
/*      */     
/* 1868 */     checkPropertiesWriteable("setIntProperty");
/* 1869 */     checkPropName(name, "setIntProperty");
/*      */ 
/*      */ 
/*      */     
/* 1873 */     if (name.equals("JMS_IBM_Character_Set")) {
/* 1874 */       setStringProperty(name, String.valueOf(val));
/* 1875 */       if (Trace.isOn) {
/* 1876 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "setIntProperty(String,int)", 1);
/*      */       }
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1883 */     checkSettablePropertyName(name, Integer.class);
/*      */     
/* 1885 */     this.providerMessage.setObjectProperty(name, Integer.valueOf(val));
/*      */     
/* 1887 */     invalidateToStringCache();
/*      */     
/* 1889 */     if (Trace.isOn) {
/* 1890 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "setIntProperty(String,int)", 2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLongProperty(String name, long val) throws JMSException {
/* 1901 */     if (Trace.isOn) {
/* 1902 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "setLongProperty(String,long)", new Object[] { name, 
/* 1903 */             Long.valueOf(val) });
/*      */     }
/*      */     
/* 1906 */     checkPropertiesWriteable("setLongProperty");
/* 1907 */     checkPropName(name, "setLongProperty");
/* 1908 */     checkSettablePropertyName(name, Long.class);
/*      */     
/* 1910 */     this.providerMessage.setObjectProperty(name, Long.valueOf(val));
/*      */     
/* 1912 */     invalidateToStringCache();
/*      */     
/* 1914 */     if (Trace.isOn) {
/* 1915 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "setLongProperty(String,long)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFloatProperty(String name, float val) throws JMSException {
/* 1926 */     if (Trace.isOn) {
/* 1927 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "setFloatProperty(String,float)", new Object[] { name, 
/* 1928 */             Float.valueOf(val) });
/*      */     }
/*      */     
/* 1931 */     checkPropertiesWriteable("setFloatProperty");
/* 1932 */     checkPropName(name, "setFloatProperty");
/* 1933 */     checkSettablePropertyName(name, Float.class);
/*      */     
/* 1935 */     this.providerMessage.setObjectProperty(name, Float.valueOf(val));
/*      */     
/* 1937 */     invalidateToStringCache();
/* 1938 */     if (Trace.isOn) {
/* 1939 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "setFloatProperty(String,float)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDoubleProperty(String name, double val) throws JMSException {
/* 1950 */     if (Trace.isOn) {
/* 1951 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "setDoubleProperty(String,double)", new Object[] { name, 
/* 1952 */             Double.valueOf(val) });
/*      */     }
/*      */     
/* 1955 */     checkPropertiesWriteable("setDoubleProperty");
/* 1956 */     checkPropName(name, "setDoubleProperty");
/* 1957 */     checkSettablePropertyName(name, Double.class);
/*      */     
/* 1959 */     this.providerMessage.setObjectProperty(name, Double.valueOf(val));
/*      */     
/* 1961 */     invalidateToStringCache();
/* 1962 */     if (Trace.isOn) {
/* 1963 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "setDoubleProperty(String,double)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setStringProperty(String name, String val) throws JMSException {
/* 1974 */     if (Trace.isOn) {
/* 1975 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "setStringProperty(String,String)", new Object[] { name, val });
/*      */     }
/*      */ 
/*      */     
/* 1979 */     checkPropertiesWriteable("setStringProperty");
/* 1980 */     checkPropName(name, "setStringProperty");
/* 1981 */     checkSettablePropertyName(name, String.class);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1986 */     if (name.equals("JMS_TOG_ARM_Correlator")) {
/* 1987 */       this.providerMessage.setObjectProperty("JMS_IBM_ArmCorrelator", val);
/*      */     } else {
/*      */       
/* 1990 */       this.providerMessage.setObjectProperty(name, val);
/*      */     } 
/*      */     
/* 1993 */     invalidateToStringCache();
/*      */     
/* 1995 */     if (Trace.isOn) {
/* 1996 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "setStringProperty(String,String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setObjectProperty(String name, Object obj) throws JMSException {
/* 2007 */     if (Trace.isOn) {
/* 2008 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "setObjectProperty(String,Object)", new Object[] { name, obj });
/*      */     }
/*      */ 
/*      */     
/* 2012 */     checkPropertiesWriteable("setObjectProperty");
/* 2013 */     checkPropName(name, "setObjectProperty");
/*      */     
/* 2015 */     Class<?> objType = (obj == null) ? Object.class : obj.getClass();
/*      */ 
/*      */     
/* 2018 */     boolean propertyValueValidated = false;
/*      */     try {
/* 2020 */       checkSettablePropertyName(name, objType);
/*      */     }
/* 2022 */     catch (JMSException je) {
/* 2023 */       if (Trace.isOn) {
/* 2024 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "setObjectProperty(String,Object)", (Throwable)je);
/*      */       }
/*      */       
/* 2027 */       String errorCode = je.getErrorCode();
/* 2028 */       if ("JMSCC0051".equalsIgnoreCase(errorCode)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2042 */         Class<?> expectedPropType = JMS_IBM_props.get(name);
/* 2043 */         if (expectedPropType == byte[].class) {
/*      */ 
/*      */           
/* 2046 */           if (Trace.isOn) {
/* 2047 */             Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "setObjectProperty(String,Object)", (Throwable)je, 1);
/*      */           }
/*      */           
/* 2050 */           if (Trace.isOn) {
/* 2051 */             Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "setObjectProperty(String,Object)", (Throwable)je, 1);
/*      */           }
/*      */           
/* 2054 */           throw je;
/*      */         } 
/* 2056 */         Map<Class<?>, JMSMessagePropertyValueConverter> propertyValidators = jmsPropertyValidators.get(expectedPropType);
/* 2057 */         if (propertyValidators == null) {
/*      */ 
/*      */           
/* 2060 */           HashMap<String, Object> inserts = new HashMap<>();
/* 2061 */           inserts.put("XMSC_INSERT_PROPERTY", name);
/* 2062 */           inserts.put("XMSC_INSERT_TYPE", expectedPropType);
/* 2063 */           inserts.put("XMSC_INSERT_OTHER_TYPE", objType);
/* 2064 */           JMSException newJe = (JMSException)JmsErrorUtils.createException("JMSCC0070", inserts);
/*      */           
/* 2066 */           if (Trace.isOn) {
/* 2067 */             Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "setObjectProperty(String,Object)", (Throwable)newJe, 2);
/*      */           }
/*      */           
/* 2070 */           throw newJe;
/*      */         } 
/* 2072 */         JMSMessagePropertyValueConverter validator = propertyValidators.get(objType);
/* 2073 */         if (validator == null) {
/*      */ 
/*      */           
/* 2076 */           HashMap<String, Object> inserts = new HashMap<>();
/* 2077 */           inserts.put("XMSC_INSERT_PROPERTY", name);
/* 2078 */           inserts.put("XMSC_INSERT_TYPE", expectedPropType);
/* 2079 */           inserts.put("XMSC_INSERT_OTHER_TYPE", objType);
/* 2080 */           JMSException newJe = (JMSException)JmsErrorUtils.createException("JMSCC0068", inserts);
/*      */           
/* 2082 */           if (Trace.isOn) {
/* 2083 */             Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "setObjectProperty(String,Object)", (Throwable)newJe, 3);
/*      */           }
/*      */           
/* 2086 */           if (Trace.isOn) {
/* 2087 */             Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "setObjectProperty(String,Object)", (Throwable)newJe, 3);
/*      */           }
/*      */           
/* 2090 */           throw newJe;
/*      */         } 
/*      */         
/* 2093 */         obj = validator.convert(name, obj);
/* 2094 */         propertyValueValidated = true;
/*      */       }
/*      */       else {
/*      */         
/* 2098 */         if (Trace.isOn) {
/* 2099 */           Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "setObjectProperty(String,Object)", (Throwable)je, 4);
/*      */         }
/*      */         
/* 2102 */         if (Trace.isOn) {
/* 2103 */           Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "setObjectProperty(String,Object)", (Throwable)je, 4);
/*      */         }
/*      */         
/* 2106 */         throw je;
/*      */       } 
/*      */     } 
/*      */     
/* 2110 */     if (propertyValueValidated) {
/*      */ 
/*      */       
/* 2113 */       this.providerMessage.setObjectProperty(name, obj);
/* 2114 */       invalidateToStringCache();
/*      */ 
/*      */     
/*      */     }
/* 2118 */     else if (obj == null || obj instanceof Boolean || obj instanceof Number || obj instanceof String) {
/* 2119 */       this.providerMessage.setObjectProperty(name, obj);
/* 2120 */       invalidateToStringCache();
/*      */     
/*      */     }
/* 2123 */     else if (obj instanceof byte[] && (name.equals("JMS_IBM_MQMD_MsgId") || name.equals("JMS_IBM_MQMD_CorrelId") || name
/* 2124 */       .equals("JMS_IBM_MQMD_AccountingToken") || name.equals("JMS_IBM_MQMD_GroupId"))) {
/* 2125 */       this.providerMessage.setObjectProperty(name, obj);
/* 2126 */       invalidateToStringCache();
/*      */     } else {
/*      */       
/* 2129 */       HashMap<String, Object> inserts = new HashMap<>();
/* 2130 */       inserts.put("XMSC_INSERT_PROPERTY", name);
/* 2131 */       inserts.put("XMSC_INSERT_TYPE", objType);
/* 2132 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0039", inserts);
/* 2133 */       if (Trace.isOn) {
/* 2134 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "setObjectProperty(String,Object)", (Throwable)je, 5);
/*      */       }
/*      */       
/* 2137 */       throw je;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2142 */     if (Trace.isOn) {
/* 2143 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "setObjectProperty(String,Object)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void acknowledge() throws JMSException {
/* 2154 */     if (Trace.isOn) {
/* 2155 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "acknowledge()");
/*      */     }
/*      */ 
/*      */     
/* 2159 */     if (this.theSession == null) {
/* 2160 */       if (Trace.isOn) {
/* 2161 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "acknowledge()", 1);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 2167 */     this.theSession.checkNotClosed();
/*      */ 
/*      */     
/* 2170 */     this.theSession.checkSynchronousUsage("acknowledge");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2177 */     if (this.theSession.getTransacted()) {
/* 2178 */       if (Trace.isOn) {
/* 2179 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "acknowledge()", 2);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 2185 */     int sessAck = this.theSession.getAcknowledgeMode();
/* 2186 */     if (sessAck == 2) {
/* 2187 */       JmsSessionImpl.ReentrantDoubleLock sessionSyncLock = this.theSession.getSessionSyncLock();
/* 2188 */       sessionSyncLock.getExclusiveLock();
/*      */       
/*      */       try {
/* 2191 */         this.theSession.commitTransaction();
/*      */       } finally {
/*      */         
/* 2194 */         if (Trace.isOn) {
/* 2195 */           Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "acknowledge()");
/*      */         }
/*      */         
/* 2198 */         sessionSyncLock.unlockExclusiveLock();
/*      */       } 
/*      */     } 
/*      */     
/* 2202 */     if (Trace.isOn) {
/* 2203 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "acknowledge()", 3);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clearBody() throws JMSException {
/* 2213 */     if (Trace.isOn) {
/* 2214 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "clearBody()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2219 */     this.providerMessage.clearBody();
/*      */ 
/*      */     
/* 2222 */     this.bodyReadOnly = false;
/*      */     
/* 2224 */     invalidateToStringCache();
/*      */     
/* 2226 */     if (Trace.isOn) {
/* 2227 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "clearBody()");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private synchronized Object getCachedToStringLock() {
/* 2233 */     if (this.cachedToStringLock == null) {
/* 2234 */       this.cachedToStringLock = new Object();
/*      */     }
/* 2236 */     if (Trace.isOn) {
/* 2237 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getCachedToStringLock()", "getter", this.cachedToStringLock);
/*      */     }
/*      */     
/* 2240 */     return this.cachedToStringLock;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/* 2250 */     synchronized (getCachedToStringLock()) {
/*      */       
/* 2252 */       if (this.cachedToString == null) {
/*      */         
/*      */         try {
/*      */ 
/*      */ 
/*      */           
/* 2258 */           this.cachedToString = "TO_STRING_IN_PROGRESS";
/*      */           
/* 2260 */           StringBuffer sb = new StringBuffer(512);
/*      */           
/* 2262 */           sb.append("\n  JMSMessage class: " + this.messageType);
/* 2263 */           sb.append("\n  JMSType:          " + getJMSType());
/* 2264 */           sb.append("\n  JMSDeliveryMode:  " + getJMSDeliveryMode());
/* 2265 */           sb.append("\n  JMSDeliveryDelay: " + getJMSDeliveryDelay());
/* 2266 */           sb.append("\n  JMSDeliveryTime:  " + getJMSDeliveryTime());
/* 2267 */           sb.append("\n  JMSExpiration:    " + getJMSExpiration());
/* 2268 */           sb.append("\n  JMSPriority:      " + getJMSPriority());
/* 2269 */           sb.append("\n  JMSMessageID:     " + getJMSMessageID());
/* 2270 */           sb.append("\n  JMSTimestamp:     " + getJMSTimestamp());
/* 2271 */           sb.append("\n  JMSCorrelationID: " + getJMSCorrelationID());
/*      */ 
/*      */ 
/*      */           
/* 2275 */           sb.append("\n  JMSDestination:   ");
/*      */           
/*      */           try {
/* 2278 */             sb.append(getJMSDestinationAsString());
/*      */           }
/* 2280 */           catch (Exception e) {
/*      */             
/* 2282 */             sb.append("<ERROR>");
/*      */           } 
/*      */           
/* 2285 */           sb.append("\n  JMSReplyTo:       ");
/*      */           try {
/* 2287 */             sb.append(getJMSReplyTo());
/*      */           }
/* 2289 */           catch (Exception e) {
/*      */             
/* 2291 */             sb.append("<ERROR>");
/*      */           } 
/*      */           
/* 2294 */           sb.append("\n  JMSRedelivered:   " + getJMSRedelivered());
/*      */ 
/*      */           
/* 2297 */           Enumeration<String> enumeration = getPropertyNames();
/*      */ 
/*      */           
/* 2300 */           ArrayList<String> keys = new ArrayList<>();
/* 2301 */           while (enumeration.hasMoreElements()) {
/* 2302 */             keys.add(enumeration.nextElement());
/*      */           }
/* 2304 */           Collections.sort(keys);
/* 2305 */           ListIterator<String> propNames = keys.listIterator();
/*      */           
/* 2307 */           while (propNames.hasNext()) {
/* 2308 */             String name = propNames.next();
/* 2309 */             Object value = getObjectProperty(name);
/*      */ 
/*      */ 
/*      */             
/* 2313 */             if (value instanceof byte[]) {
/* 2314 */               StringBuffer s = Utils.bytesToHex((byte[])value);
/* 2315 */               if (s.length() > 100) {
/* 2316 */                 s.setLength(100);
/* 2317 */                 s.append(" ...");
/*      */               } 
/* 2319 */               value = s.toString();
/*      */             } 
/*      */             
/* 2322 */             sb.append("\n    " + name + ": " + value);
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/* 2327 */           this.cachedToString = sb.toString();
/*      */         }
/* 2329 */         catch (JMSException e) {
/*      */ 
/*      */           
/* 2332 */           this.cachedToString = "";
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/* 2337 */     return this.cachedToString;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected ProviderMessage createProviderMessage(JmsSessionImpl session) throws Exception {
/*      */     ProviderMessage result;
/* 2345 */     if (Trace.isOn) {
/* 2346 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "createProviderMessage(JmsSessionImpl)", new Object[] { session });
/*      */     }
/*      */ 
/*      */     
/* 2350 */     this.messageType = "jms_none";
/*      */ 
/*      */ 
/*      */     
/* 2354 */     if (session != null) {
/* 2355 */       result = getProviderMessageFactory().createMessage(session.getProviderSession());
/*      */     } else {
/*      */       
/* 2358 */       result = getProviderMessageFactory().createMessage(null);
/*      */     } 
/* 2360 */     if (Trace.isOn) {
/* 2361 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "createProviderMessage(JmsSessionImpl)", result);
/*      */     }
/*      */     
/* 2364 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected ProviderMessage getProviderMessage() throws JMSException {
/* 2374 */     if (Trace.isOn) {
/* 2375 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getProviderMessage()", "getter", this.providerMessage);
/*      */     }
/*      */     
/* 2378 */     return this.providerMessage;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void checkBodyWriteable(String callingMethodName) throws JMSException {
/* 2386 */     if (this.bodyReadOnly) {
/* 2387 */       HashMap<String, Object> inserts = new HashMap<>();
/* 2388 */       inserts.put("XMSC_INSERT_METHOD", callingMethodName);
/* 2389 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0044", inserts);
/* 2390 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void checkBodyReadable(String callingMethodName) throws MessageNotReadableException {
/* 2399 */     if (!this.bodyReadOnly) {
/* 2400 */       HashMap<String, Object> inserts = new HashMap<>();
/* 2401 */       inserts.put("XMSC_INSERT_METHOD", callingMethodName);
/*      */       
/* 2403 */       MessageNotReadableException je = (MessageNotReadableException)JmsErrorUtils.createException("JMSCC0046", inserts);
/* 2404 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void checkPropertiesWriteable(String callingMethodName) throws JMSException {
/* 2413 */     if (this.propertiesReadOnly) {
/*      */       JMSException je;
/* 2415 */       if (callingMethodName != null) {
/* 2416 */         HashMap<String, Object> inserts = new HashMap<>();
/* 2417 */         inserts.put("XMSC_INSERT_METHOD", callingMethodName);
/* 2418 */         je = (JMSException)JmsErrorUtils.createException("JMSCC0045", inserts);
/*      */       } else {
/*      */         
/* 2421 */         je = (JMSException)JmsErrorUtils.createException("JMSCC5005", null);
/*      */       } 
/* 2423 */       throw je;
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
/*      */   static Message inboundJmsInstance(ProviderMessage providerMessage, JmsSessionImpl newSess, String connectionTypeName) throws JMSException {
/* 2435 */     if (Trace.isOn) {
/* 2436 */       Trace.entry("com.ibm.msg.client.jms.internal.JmsMessageImpl", "inboundJmsInstance(ProviderMessage,JmsSessionImpl,String)", new Object[] { providerMessage, newSess, connectionTypeName });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2441 */     JmsMessageImpl jmsMessage = null;
/*      */     
/* 2443 */     if (providerMessage instanceof ProviderBytesMessage) {
/* 2444 */       jmsMessage = new JmsBytesMessageImpl((ProviderBytesMessage)providerMessage, newSess, connectionTypeName);
/*      */     }
/* 2446 */     else if (providerMessage instanceof ProviderMapMessage) {
/* 2447 */       jmsMessage = new JmsMapMessageImpl((ProviderMapMessage)providerMessage, newSess, connectionTypeName);
/*      */     }
/* 2449 */     else if (providerMessage instanceof ProviderStreamMessage) {
/* 2450 */       jmsMessage = new JmsStreamMessageImpl((ProviderStreamMessage)providerMessage, newSess, connectionTypeName);
/*      */     }
/* 2452 */     else if (providerMessage instanceof ProviderObjectMessage) {
/* 2453 */       jmsMessage = new JmsObjectMessageImpl((ProviderObjectMessage)providerMessage, newSess, connectionTypeName);
/*      */     }
/* 2455 */     else if (providerMessage instanceof ProviderTextMessage) {
/* 2456 */       jmsMessage = new JmsTextMessageImpl((ProviderTextMessage)providerMessage, newSess, connectionTypeName);
/*      */     } else {
/*      */       
/* 2459 */       jmsMessage = new JmsMessageImpl(providerMessage, newSess, connectionTypeName);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2464 */     jmsMessage.bodyReadOnly = true;
/* 2465 */     jmsMessage.propertiesReadOnly = true;
/*      */     
/* 2467 */     if (Trace.isOn) {
/* 2468 */       Trace.exit("com.ibm.msg.client.jms.internal.JmsMessageImpl", "inboundJmsInstance(ProviderMessage,JmsSessionImpl,String)", jmsMessage);
/*      */     }
/*      */     
/* 2471 */     return jmsMessage;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static JmsMessageImpl messageToJmsMessageImpl(JmsSessionImpl session, Message message) throws JMSException {
/* 2480 */     if (Trace.isOn) {
/* 2481 */       Trace.entry("com.ibm.msg.client.jms.internal.JmsMessageImpl", "messageToJmsMessageImpl(JmsSessionImpl,Message)", new Object[] { session, message });
/*      */     }
/*      */ 
/*      */     
/* 2485 */     JmsMessageImpl jmsMessage = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2491 */     if (message instanceof JmsMessage) {
/* 2492 */       Message delegate = ((JmsMessage)message).getDelegate();
/* 2493 */       if (delegate != null && delegate instanceof JmsMessageImpl) {
/*      */         
/* 2495 */         jmsMessage = (JmsMessageImpl)delegate;
/*      */       } else {
/*      */         
/* 2498 */         jmsMessage = (JmsMessageImpl)message;
/*      */       }
/*      */     
/* 2501 */     } else if (message instanceof BytesMessage) {
/* 2502 */       jmsMessage = new JmsBytesMessageImpl(session, (BytesMessage)message);
/*      */     }
/* 2504 */     else if (message instanceof MapMessage) {
/* 2505 */       jmsMessage = new JmsMapMessageImpl(session, (MapMessage)message);
/*      */     }
/* 2507 */     else if (message instanceof ObjectMessage) {
/* 2508 */       jmsMessage = new JmsObjectMessageImpl(session, (ObjectMessage)message);
/*      */     }
/* 2510 */     else if (message instanceof StreamMessage) {
/* 2511 */       jmsMessage = new JmsStreamMessageImpl(session, (StreamMessage)message);
/*      */     }
/* 2513 */     else if (message instanceof TextMessage) {
/* 2514 */       jmsMessage = new JmsTextMessageImpl(session, (TextMessage)message);
/*      */     } else {
/*      */       
/* 2517 */       jmsMessage = new JmsMessageImpl(session, message);
/*      */     } 
/*      */     
/* 2520 */     if (Trace.isOn) {
/* 2521 */       Trace.exit("com.ibm.msg.client.jms.internal.JmsMessageImpl", "messageToJmsMessageImpl(JmsSessionImpl,Message)", jmsMessage);
/*      */     }
/*      */     
/* 2524 */     return jmsMessage;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isBodyReadOnly() {
/* 2534 */     return this.bodyReadOnly;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setBodyReadOnly() {
/* 2544 */     this.bodyReadOnly = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void checkPropName(String name, String callingMethodName) throws IllegalArgumentException, MessageFormatException {
/* 2554 */     if (name == null || "".equals(name)) {
/* 2555 */       IllegalArgumentException je = (IllegalArgumentException)JmsErrorUtils.createException("JMSCC0043", null);
/* 2556 */       throw je;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2561 */     if (JMS_IBM_props.containsKey(name)) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2567 */     if (!Character.isJavaIdentifierStart(name.charAt(0))) {
/* 2568 */       HashMap<String, Object> inserts = new HashMap<>();
/* 2569 */       inserts.put("XMSC_INSERT_PROPERTY", name);
/* 2570 */       MessageFormatException je2 = (MessageFormatException)JmsErrorUtils.createException("JMSCC0049", inserts);
/* 2571 */       throw je2;
/*      */     } 
/*      */     
/* 2574 */     for (int i = 1; i < name.length(); i++) {
/* 2575 */       if (!Character.isJavaIdentifierPart(name.charAt(i))) {
/* 2576 */         HashMap<String, Object> inserts = new HashMap<>();
/* 2577 */         inserts.put("XMSC_INSERT_PROPERTY", name);
/* 2578 */         MessageFormatException je3 = (MessageFormatException)JmsErrorUtils.createException("JMSCC0049", inserts);
/* 2579 */         throw je3;
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void checkSettablePropertyName(String propertyName, Class<?> propType) throws JMSException {
/* 2598 */     if (propertyName.startsWith("JMS")) {
/* 2599 */       if (JMS_IBM_props.containsKey(propertyName)) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2604 */         Class<?> expectedPropType = JMS_IBM_props.get(propertyName);
/* 2605 */         if (expectedPropType != propType) {
/* 2606 */           HashMap<String, Object> inserts = new HashMap<>();
/* 2607 */           inserts.put("XMSC_INSERT_PROPERTY", propertyName);
/* 2608 */           inserts.put("XMSC_INSERT_TYPE", expectedPropType.getName());
/* 2609 */           inserts.put("XMSC_INSERT_OTHER_TYPE", propType.getName());
/* 2610 */           JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0051", inserts);
/* 2611 */           throw je;
/*      */         }
/*      */       
/* 2614 */       } else if (propertyName.startsWith("JMSX")) {
/*      */ 
/*      */         
/* 2617 */         if (!propertyName.equals("JMSXGroupID") && !propertyName.equals("JMSXGroupSeq") && !propertyName.equals("JMSXUserID") && 
/* 2618 */           !propertyName.equals("JMSXAppID") && !propertyName.equals("JMSXDeliveryCount") && 
/*      */ 
/*      */           
/* 2621 */           !propertyName.equals("JMSXState") && !propertyName.equals("JMSXProducerTXID") && !propertyName.equals("JMSXConsumerTXID") && 
/* 2622 */           !propertyName.equals("JMSXRcvTimestamp")) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2631 */           if (this.jmsxPropertyNames == null)
/*      */           {
/* 2633 */             this
/* 2634 */               .jmsxPropertyNames = ((JmsFactoryFactoryImpl)JmsFactoryFactory.getInstance(this.connectionTypeName)).getProviderMessageFactory().getJMSXPropertyNames();
/*      */           }
/*      */ 
/*      */           
/* 2638 */           boolean found = false;
/*      */ 
/*      */           
/* 2641 */           while (this.jmsxPropertyNames.hasMoreElements()) {
/* 2642 */             String nextProp = this.jmsxPropertyNames.nextElement();
/* 2643 */             if (nextProp.equals(propertyName)) {
/* 2644 */               found = true;
/*      */               
/*      */               break;
/*      */             } 
/*      */           } 
/* 2649 */           if (!found) {
/*      */             
/* 2651 */             HashMap<String, Object> inserts = new HashMap<>();
/* 2652 */             inserts.put("XMSC_INSERT_PROPERTY", propertyName);
/* 2653 */             JMSException je2 = (JMSException)JmsErrorUtils.createException("JMSCC0050", inserts);
/* 2654 */             throw je2;
/*      */           } 
/*      */         } 
/*      */       } else {
/*      */         
/* 2659 */         HashMap<String, Object> inserts = new HashMap<>();
/* 2660 */         inserts.put("XMSC_INSERT_PROPERTY", propertyName);
/* 2661 */         JMSException je3 = (JMSException)JmsErrorUtils.createException("JMSCC0050", inserts);
/* 2662 */         throw je3;
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   JmsDestinationImpl createDestination(String connectionTypeName, JmsDestinationImpl dest) throws JMSException {
/* 2670 */     if (Trace.isOn) {
/* 2671 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "createDestination(String,JmsDestinationImpl)", new Object[] { connectionTypeName, dest });
/*      */     }
/*      */ 
/*      */     
/* 2675 */     JmsDestinationImpl newDest = dest;
/* 2676 */     JmsFactoryFactory factory = JmsFactoryFactory.getInstance(connectionTypeName);
/*      */     
/* 2678 */     ProviderDestination providerDestination = JmsDestinationImplProxy.getProviderDestination(dest);
/* 2679 */     String destName = providerDestination.getName();
/* 2680 */     if (dest instanceof Queue) {
/* 2681 */       newDest = (JmsDestinationImpl)factory.createQueue(destName);
/*      */     }
/* 2683 */     else if (dest instanceof Topic) {
/* 2684 */       newDest = (JmsDestinationImpl)factory.createTopic(destName);
/*      */     } 
/*      */     
/* 2687 */     Enumeration<String> propNames = dest.getPropertyNames();
/* 2688 */     while (propNames.hasMoreElements()) {
/*      */       
/* 2690 */       String propName = propNames.nextElement();
/*      */       
/*      */       try {
/* 2693 */         newDest.setObjectProperty(propName, dest.getObjectProperty(propName));
/*      */       }
/* 2695 */       catch (JMSException e) {
/* 2696 */         if (Trace.isOn) {
/* 2697 */           Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "createDestination(String,JmsDestinationImpl)", (Throwable)e);
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2705 */     if (Trace.isOn) {
/* 2706 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "createDestination(String,JmsDestinationImpl)", newDest);
/*      */     }
/*      */     
/* 2709 */     return newDest;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void invalidateToStringCache() {
/* 2719 */     synchronized (getCachedToStringLock()) {
/* 2720 */       this.cachedToString = null;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected String getConnectionTypeName() {
/* 2725 */     if (Trace.isOn) {
/* 2726 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getConnectionTypeName()", "getter", this.connectionTypeName);
/*      */     }
/*      */     
/* 2729 */     return this.connectionTypeName;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private JmsDestinationImpl providerDestToJmsDest(String providerDestString) throws JMSException {
/* 2735 */     if (Trace.isOn) {
/* 2736 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "providerDestToJmsDest(String)", new Object[] { providerDestString });
/*      */     }
/*      */ 
/*      */     
/* 2740 */     JmsDestinationImpl jmsDest = null;
/*      */     
/* 2742 */     if (providerDestString != null && providerDestString.trim().length() > 0) {
/* 2743 */       jmsDest = (JmsDestinationImpl)getProviderJmsFactory().createDestination(providerDestString);
/*      */     }
/*      */     
/* 2746 */     if (Trace.isOn) {
/* 2747 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "providerDestToJmsDest(String)", jmsDest);
/*      */     }
/*      */     
/* 2750 */     return jmsDest;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void readObjectNoData() throws ObjectStreamException {
/* 2760 */     if (Trace.isOn) {
/* 2761 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "readObjectNoData()");
/*      */     }
/*      */     
/* 2764 */     getFactories();
/*      */     
/* 2766 */     if (Trace.isOn) {
/* 2767 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "readObjectNoData()");
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
/*      */   public <T> T getBody(Class<T> c) throws JMSException, MessageFormatException {
/* 2780 */     if (Trace.isOn) {
/* 2781 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getBody(Class<T>)", new Object[] { c });
/*      */     }
/*      */ 
/*      */     
/* 2785 */     Object object = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2791 */     boolean hasBody = hasBody();
/*      */ 
/*      */     
/* 2794 */     if (isBodyAssignableTo(c)) {
/*      */       byte[] arrayOfByte; long length; JMSException je2;
/*      */       HashMap<String, Object> data;
/* 2797 */       switch (this.messageType) {
/*      */         case "jms_bytes":
/* 2799 */           if (!hasBody) {
/*      */             break;
/*      */           }
/* 2802 */           length = ((JmsBytesMessageImpl)this).getBodyLength();
/* 2803 */           arrayOfByte = new byte[(int)length];
/*      */           
/* 2805 */           ((JmsBytesMessageImpl)this).readBytes(arrayOfByte);
/*      */           break;
/*      */         
/*      */         case "jms_map":
/* 2809 */           if (!hasBody) {
/*      */             break;
/*      */           }
/* 2812 */           object = ((JmsMapMessageImpl)this).getMap(false);
/*      */           break;
/*      */         
/*      */         case "jms_none":
/* 2816 */           object = null;
/*      */           break;
/*      */         
/*      */         case "jms_object":
/* 2820 */           if (!hasBody) {
/*      */             break;
/*      */           }
/*      */           try {
/* 2824 */             object = ((JmsObjectMessageImpl)this).getObject();
/*      */           }
/* 2826 */           catch (Exception e) {
/* 2827 */             if (Trace.isOn) {
/* 2828 */               Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getBody(Class<T>)", e);
/*      */             }
/*      */             
/* 2831 */             HashMap<String, Object> inserts = new HashMap<>();
/* 2832 */             inserts.put("XMSC_INSERT_EXCEPTION", e);
/* 2833 */             JMSException je3 = (JMSException)JmsErrorUtils.createException("JMSCC5003", inserts);
/* 2834 */             if (Trace.isOn) {
/* 2835 */               Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getBody(Class<T>)", (Throwable)je3, 1);
/*      */             }
/*      */             
/* 2838 */             throw je3;
/*      */           } 
/*      */           break;
/*      */         
/*      */         case "jms_stream":
/* 2843 */           je2 = (JMSException)JmsErrorUtils.createException("JMSCC5001", null);
/* 2844 */           if (Trace.isOn) {
/* 2845 */             Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getBody(Class<T>)", (Throwable)je2, 2);
/*      */           }
/*      */           
/* 2848 */           throw je2;
/*      */         
/*      */         case "jms_text":
/* 2851 */           if (!hasBody) {
/*      */             break;
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2860 */           object = ((JmsTextMessageImpl)this).getText();
/*      */           break;
/*      */         
/*      */         default:
/* 2864 */           data = new HashMap<>();
/* 2865 */           data.put("Class", c);
/* 2866 */           Trace.ffst(this, "isBodyAssignableTo(Class C)", "XJ001003", data, null);
/*      */           break;
/*      */       } 
/*      */     
/*      */     } else {
/* 2871 */       HashMap<String, Object> inserts = new HashMap<>();
/* 2872 */       inserts.put("XMSC_INSERT_MESSAGE_TYPE", this.messageType);
/* 2873 */       inserts.put("XMSC_INSERT_BODY_TYPE", c.getName());
/*      */       
/* 2875 */       JMSException je3 = (JMSException)JmsErrorUtils.createException("JMSCC5002", inserts);
/* 2876 */       if (Trace.isOn) {
/* 2877 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getBody(Class<T>)", (Throwable)je3, 3);
/*      */       }
/*      */       
/* 2880 */       throw je3;
/*      */     } 
/*      */     
/* 2883 */     if (Trace.isOn) {
/* 2884 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getBody(Class<T>)", object);
/*      */     }
/*      */     
/* 2887 */     return (T)object;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getJMSDeliveryDelay() throws JMSException {
/* 2896 */     long traceRet1 = this.providerMessage.getJMSDeliveryDelay();
/* 2897 */     if (Trace.isOn) {
/* 2898 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getJMSDeliveryDelay()", "getter", 
/* 2899 */           Long.valueOf(traceRet1));
/*      */     }
/* 2901 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getJMSDeliveryTime() throws JMSException {
/* 2910 */     long dt = this.providerMessage.getJMSDeliveryTime();
/*      */     
/* 2912 */     if (Trace.isOn) {
/* 2913 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getJMSDeliveryTime()", "getter", 
/* 2914 */           Long.valueOf(dt));
/*      */     }
/* 2916 */     return dt;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isBodyAssignableTo(Class c) throws JMSException {
/*      */     HashMap<String, Object> data;
/* 2928 */     if (Trace.isOn) {
/* 2929 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "isBodyAssignableTo(Class)", new Object[] { c });
/*      */     }
/*      */ 
/*      */     
/* 2933 */     boolean result = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2940 */     boolean hasBody = hasBody();
/*      */ 
/*      */     
/* 2943 */     switch (this.messageType) {
/*      */       case "jms_bytes":
/* 2945 */         result = (!hasBody || c.equals(Object.class) || c.equals(byte[].class));
/*      */         break;
/*      */       
/*      */       case "jms_map":
/* 2949 */         result = (!hasBody || c.equals(Object.class) || c.equals(Map.class));
/*      */         break;
/*      */       
/*      */       case "jms_none":
/* 2953 */         result = true;
/*      */         break;
/*      */ 
/*      */       
/*      */       case "jms_object":
/* 2958 */         result = (!hasBody || c.equals(Serializable.class));
/* 2959 */         if (!result) {
/* 2960 */           Object o = ((JmsObjectMessageImpl)this).getObject();
/*      */ 
/*      */           
/* 2963 */           result = (c != null && c.isAssignableFrom(o.getClass()));
/*      */         } 
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case "jms_stream":
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case "jms_text":
/* 2979 */         result = (!hasBody || c.equals(String.class) || c.equals(Object.class) || c.equals(CharSequence.class) || c.equals(Serializable.class) || c.equals(Comparable.class));
/*      */         break;
/*      */ 
/*      */       
/*      */       default:
/* 2984 */         data = new HashMap<>();
/* 2985 */         data.put("Param", c);
/* 2986 */         Trace.ffst(this, "isBodyAssignableTo(Class C)", "XJ001004", data, null);
/*      */         break;
/*      */     } 
/* 2989 */     if (Trace.isOn) {
/* 2990 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "isBodyAssignableTo(Class)", 
/* 2991 */           Boolean.valueOf(result));
/*      */     }
/* 2993 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setJMSDeliveryTime(long timestamp) throws JMSException {
/* 3001 */     if (Trace.isOn) {
/* 3002 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "setJMSDeliveryTime(long)", "setter", 
/* 3003 */           Long.valueOf(timestamp));
/*      */     }
/*      */     
/* 3006 */     this.providerMessage.setJMSDeliveryTime(timestamp);
/*      */     
/* 3008 */     invalidateToStringCache();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean hasBody() throws JMSException {
/* 3017 */     if (Trace.isOn) {
/* 3018 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "hasBody()");
/*      */     }
/* 3020 */     boolean traceRet1 = this.providerMessage.hasBody();
/* 3021 */     if (Trace.isOn) {
/* 3022 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "hasBody()", 
/* 3023 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 3025 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized void updateFromMessage(Message message) throws JMSException {
/* 3033 */     if (Trace.isOn) {
/* 3034 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "updateFromMessage(Message)", new Object[] { message });
/*      */     }
/*      */ 
/*      */     
/* 3038 */     genericMessageUpdate(message, this);
/*      */     
/* 3040 */     if (Trace.isOn) {
/* 3041 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "updateFromMessage(Message)");
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
/*      */   public Message getDelegate() {
/* 3053 */     if (Trace.isOn) {
/* 3054 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageImpl", "getDelegate()", "getter", null);
/*      */     }
/*      */     
/* 3057 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void genericMessageUpdate(Message sourceMessage, Message destinationMessage) {
/* 3067 */     if (Trace.isOn) {
/* 3068 */       Trace.entry("com.ibm.msg.client.jms.internal.JmsMessageImpl", "genericMessageUpdate(Message,Message)", new Object[] { sourceMessage, destinationMessage });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 3074 */       destinationMessage.setJMSPriority(sourceMessage.getJMSPriority());
/* 3075 */       destinationMessage.setJMSDeliveryMode(sourceMessage.getJMSDeliveryMode());
/* 3076 */       destinationMessage.setJMSMessageID(sourceMessage.getJMSMessageID());
/* 3077 */       destinationMessage.setJMSExpiration(sourceMessage.getJMSExpiration());
/* 3078 */       destinationMessage.setJMSTimestamp(sourceMessage.getJMSTimestamp());
/* 3079 */       Destination dest = sourceMessage.getJMSDestination();
/* 3080 */       destinationMessage.setJMSDestination(dest);
/*      */       
/* 3082 */       destinationMessage.setJMSCorrelationID(sourceMessage.getJMSCorrelationID());
/* 3083 */       destinationMessage.setJMSDeliveryTime(sourceMessage.getJMSDeliveryTime());
/* 3084 */       destinationMessage.setJMSMessageID(sourceMessage.getJMSMessageID());
/* 3085 */       destinationMessage.setJMSRedelivered(sourceMessage.getJMSRedelivered());
/* 3086 */       destinationMessage.setJMSReplyTo(sourceMessage.getJMSReplyTo());
/* 3087 */       destinationMessage.setJMSType(sourceMessage.getJMSType());
/*      */     
/*      */     }
/* 3090 */     catch (Throwable t) {
/* 3091 */       if (Trace.isOn) {
/* 3092 */         Trace.catchBlock("com.ibm.msg.client.jms.internal.JmsMessageImpl", "genericMessageUpdate(Message,Message)", t, 1);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 3102 */       destinationMessage.setStringProperty("JMSXAppID", sourceMessage
/* 3103 */           .getStringProperty("JMSXAppID"));
/* 3104 */       destinationMessage.setStringProperty("JMSXUserID", sourceMessage
/* 3105 */           .getStringProperty("JMSXUserID"));
/* 3106 */       destinationMessage.setStringProperty("JMSXGroupID", sourceMessage
/*      */           
/* 3108 */           .getStringProperty("JMSXGroupID"));
/* 3109 */       destinationMessage.setIntProperty("JMSXGroupSeq", sourceMessage
/* 3110 */           .getIntProperty("JMSXGroupSeq"));
/*      */     }
/* 3112 */     catch (Exception e) {
/* 3113 */       if (Trace.isOn) {
/* 3114 */         Trace.catchBlock("com.ibm.msg.client.jms.internal.JmsMessageImpl", "genericMessageUpdate(Message,Message)", e, 2);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 3123 */       destinationMessage.setStringProperty("JMS_IBM_PutDate", sourceMessage
/*      */           
/* 3125 */           .getStringProperty("JMS_IBM_PutDate"));
/* 3126 */       destinationMessage.setStringProperty("JMS_IBM_PutTime", sourceMessage
/*      */           
/* 3128 */           .getStringProperty("JMS_IBM_PutTime"));
/* 3129 */       destinationMessage.setIntProperty("JMS_IBM_PutApplType", sourceMessage
/*      */           
/* 3131 */           .getIntProperty("JMS_IBM_PutApplType"));
/*      */     }
/* 3133 */     catch (Exception e) {
/* 3134 */       if (Trace.isOn) {
/* 3135 */         Trace.catchBlock("com.ibm.msg.client.jms.internal.JmsMessageImpl", "genericMessageUpdate(Message,Message)", e, 3);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 3140 */     if (Trace.isOn) {
/* 3141 */       Trace.exit("com.ibm.msg.client.jms.internal.JmsMessageImpl", "genericMessageUpdate(Message,Message)");
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
/*      */   static class JmsBooleanPropertyValidator
/*      */     implements JMSMessagePropertyValueConverter
/*      */   {
/*      */     public boolean isConvertible(Object value) {
/* 3156 */       if (Trace.isOn) {
/* 3157 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsBooleanPropertyValidator", "isConvertible(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 3163 */         convert("", value);
/*      */       }
/* 3165 */       catch (JMSException je) {
/* 3166 */         if (Trace.isOn) {
/* 3167 */           Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsBooleanPropertyValidator", "isConvertible(Object)", (Throwable)je);
/*      */         }
/*      */         
/* 3170 */         if (Trace.isOn) {
/* 3171 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBooleanPropertyValidator", "isConvertible(Object)", 
/* 3172 */               Boolean.valueOf(false), 1);
/*      */         }
/* 3174 */         return false;
/*      */       } 
/* 3176 */       if (Trace.isOn) {
/* 3177 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBooleanPropertyValidator", "isConvertible(Object)", 
/* 3178 */             Boolean.valueOf(true), 2);
/*      */       }
/* 3180 */       return true;
/*      */     }
/*      */     
/*      */     public Boolean convert(String propertyName, Object value) throws JMSException {
/* 3184 */       if (Trace.isOn) {
/* 3185 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsBooleanPropertyValidator", "convert(String,Object)", new Object[] { propertyName, value });
/*      */       }
/*      */ 
/*      */       
/* 3189 */       if (value instanceof Boolean) {
/* 3190 */         Boolean traceRet1 = (Boolean)value;
/* 3191 */         if (Trace.isOn) {
/* 3192 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBooleanPropertyValidator", "convert(String,Object)", traceRet1, 1);
/*      */         }
/*      */         
/* 3195 */         return traceRet1;
/*      */       } 
/* 3197 */       if (value instanceof String) {
/* 3198 */         Boolean traceRet2 = Boolean.valueOf((String)value);
/* 3199 */         if (Trace.isOn) {
/* 3200 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBooleanPropertyValidator", "convert(String,Object)", traceRet2, 2);
/*      */         }
/*      */         
/* 3203 */         return traceRet2;
/*      */       } 
/*      */ 
/*      */       
/* 3207 */       HashMap<String, Object> inserts = new HashMap<>();
/* 3208 */       inserts.put("XMSC_INSERT_PROPERTY", propertyName);
/* 3209 */       inserts.put("XMSC_INSERT_TYPE", "java.lang.Boolean");
/* 3210 */       inserts.put("XMSC_INSERT_OTHER_TYPE", (value != null) ? value.getClass() : null);
/* 3211 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0068", inserts);
/*      */       
/* 3213 */       if (Trace.isOn) {
/* 3214 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsBooleanPropertyValidator", "convert(String,Object)", (Throwable)je);
/*      */       }
/*      */       
/* 3217 */       throw je;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class JmsBytePropertyValidator
/*      */     implements JMSMessagePropertyValueConverter
/*      */   {
/*      */     public boolean isConvertible(Object value) {
/* 3229 */       if (Trace.isOn) {
/* 3230 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsBytePropertyValidator", "isConvertible(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 3236 */         convert("", value);
/*      */       }
/* 3238 */       catch (JMSException je) {
/* 3239 */         if (Trace.isOn) {
/* 3240 */           Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsBytePropertyValidator", "isConvertible(Object)", (Throwable)je);
/*      */         }
/*      */         
/* 3243 */         if (Trace.isOn) {
/* 3244 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytePropertyValidator", "isConvertible(Object)", 
/* 3245 */               Boolean.valueOf(false), 1);
/*      */         }
/* 3247 */         return false;
/*      */       } 
/* 3249 */       if (Trace.isOn) {
/* 3250 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytePropertyValidator", "isConvertible(Object)", 
/* 3251 */             Boolean.valueOf(true), 2);
/*      */       }
/* 3253 */       return true;
/*      */     }
/*      */     
/*      */     public Byte convert(String propertyName, Object value) throws JMSException {
/* 3257 */       if (Trace.isOn) {
/* 3258 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsBytePropertyValidator", "convert(String,Object)", new Object[] { propertyName, value });
/*      */       }
/*      */ 
/*      */       
/* 3262 */       if (value instanceof Byte) {
/* 3263 */         Byte traceRet1 = (Byte)value;
/* 3264 */         if (Trace.isOn) {
/* 3265 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytePropertyValidator", "convert(String,Object)", traceRet1, 1);
/*      */         }
/*      */         
/* 3268 */         return traceRet1;
/*      */       } 
/* 3270 */       if (value instanceof String) {
/*      */         try {
/* 3272 */           Byte traceRet2 = Byte.valueOf((String)value);
/* 3273 */           if (Trace.isOn) {
/* 3274 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsBytePropertyValidator", "convert(String,Object)", traceRet2, 2);
/*      */           }
/*      */           
/* 3277 */           return traceRet2;
/*      */         }
/* 3279 */         catch (NumberFormatException nfe) {
/* 3280 */           if (Trace.isOn) {
/* 3281 */             Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsBytePropertyValidator", "convert(String,Object)", nfe);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 3286 */           HashMap<String, Object> hashMap = new HashMap<>();
/* 3287 */           hashMap.put("XMSC_INSERT_PROPERTY", propertyName);
/* 3288 */           hashMap.put("XMSC_INSERT_TYPE", "java.lang.Byte");
/* 3289 */           hashMap.put("XMSC_INSERT_OTHER_TYPE", value.getClass());
/* 3290 */           hashMap.put("XMSC_INSERT_VALUE", value);
/* 3291 */           JMSException jMSException = (JMSException)JmsErrorUtils.createException("JMSCC0069", hashMap);
/*      */           
/* 3293 */           if (Trace.isOn) {
/* 3294 */             Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsBytePropertyValidator", "convert(String,Object)", (Throwable)jMSException, 1);
/*      */           }
/*      */           
/* 3297 */           throw jMSException;
/*      */         } 
/*      */       }
/*      */ 
/*      */       
/* 3302 */       HashMap<String, Object> inserts = new HashMap<>();
/* 3303 */       inserts.put("XMSC_INSERT_PROPERTY", propertyName);
/* 3304 */       inserts.put("XMSC_INSERT_TYPE", "java.lang.Byte");
/* 3305 */       inserts.put("XMSC_INSERT_OTHER_TYPE", (value != null) ? value.getClass() : null);
/* 3306 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0068", inserts);
/*      */       
/* 3308 */       if (Trace.isOn) {
/* 3309 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsBytePropertyValidator", "convert(String,Object)", (Throwable)je, 2);
/*      */       }
/*      */       
/* 3312 */       throw je;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class JmsShortPropertyValidator
/*      */     implements JMSMessagePropertyValueConverter
/*      */   {
/*      */     public boolean isConvertible(Object value) {
/* 3325 */       if (Trace.isOn) {
/* 3326 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsShortPropertyValidator", "isConvertible(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 3332 */         convert("", value);
/*      */       }
/* 3334 */       catch (JMSException je) {
/* 3335 */         if (Trace.isOn) {
/* 3336 */           Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsShortPropertyValidator", "isConvertible(Object)", (Throwable)je);
/*      */         }
/*      */         
/* 3339 */         if (Trace.isOn) {
/* 3340 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsShortPropertyValidator", "isConvertible(Object)", 
/* 3341 */               Boolean.valueOf(false), 1);
/*      */         }
/* 3343 */         return false;
/*      */       } 
/* 3345 */       if (Trace.isOn) {
/* 3346 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsShortPropertyValidator", "isConvertible(Object)", 
/* 3347 */             Boolean.valueOf(true), 2);
/*      */       }
/* 3349 */       return true;
/*      */     }
/*      */     
/*      */     public Short convert(String propertyName, Object value) throws JMSException {
/* 3353 */       if (Trace.isOn) {
/* 3354 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsShortPropertyValidator", "convert(String,Object)", new Object[] { propertyName, value });
/*      */       }
/*      */ 
/*      */       
/* 3358 */       if (value instanceof Short) {
/* 3359 */         Short traceRet1 = (Short)value;
/* 3360 */         if (Trace.isOn) {
/* 3361 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsShortPropertyValidator", "convert(String,Object)", traceRet1, 1);
/*      */         }
/*      */         
/* 3364 */         return traceRet1;
/*      */       } 
/* 3366 */       if (value instanceof Byte) {
/* 3367 */         Byte byteValue = (Byte)value;
/* 3368 */         Short traceRet2 = Short.valueOf(byteValue.shortValue());
/* 3369 */         if (Trace.isOn) {
/* 3370 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsShortPropertyValidator", "convert(String,Object)", traceRet2, 2);
/*      */         }
/*      */         
/* 3373 */         return traceRet2;
/*      */       } 
/* 3375 */       if (value instanceof String) {
/*      */         try {
/* 3377 */           Short traceRet3 = Short.valueOf((String)value);
/* 3378 */           if (Trace.isOn) {
/* 3379 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsShortPropertyValidator", "convert(String,Object)", traceRet3, 3);
/*      */           }
/*      */           
/* 3382 */           return traceRet3;
/*      */         }
/* 3384 */         catch (NumberFormatException nfe) {
/* 3385 */           if (Trace.isOn) {
/* 3386 */             Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsShortPropertyValidator", "convert(String,Object)", nfe);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 3391 */           HashMap<String, Object> hashMap = new HashMap<>();
/* 3392 */           hashMap.put("XMSC_INSERT_PROPERTY", propertyName);
/* 3393 */           hashMap.put("XMSC_INSERT_TYPE", "java.lang.Short");
/* 3394 */           hashMap.put("XMSC_INSERT_OTHER_TYPE", value.getClass());
/* 3395 */           hashMap.put("XMSC_INSERT_VALUE", value);
/* 3396 */           JMSException jMSException = (JMSException)JmsErrorUtils.createException("JMSCC0069", hashMap);
/*      */           
/* 3398 */           if (Trace.isOn) {
/* 3399 */             Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsShortPropertyValidator", "convert(String,Object)", (Throwable)jMSException, 1);
/*      */           }
/*      */           
/* 3402 */           throw jMSException;
/*      */         } 
/*      */       }
/*      */ 
/*      */       
/* 3407 */       HashMap<String, Object> inserts = new HashMap<>();
/* 3408 */       inserts.put("XMSC_INSERT_PROPERTY", propertyName);
/* 3409 */       inserts.put("XMSC_INSERT_TYPE", "java.lang.Short");
/* 3410 */       inserts.put("XMSC_INSERT_OTHER_TYPE", (value != null) ? value.getClass() : null);
/* 3411 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0068", inserts);
/*      */       
/* 3413 */       if (Trace.isOn) {
/* 3414 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsShortPropertyValidator", "convert(String,Object)", (Throwable)je, 2);
/*      */       }
/*      */       
/* 3417 */       throw je;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class JmsIntegerPropertyValidator
/*      */     implements JMSMessagePropertyValueConverter
/*      */   {
/*      */     public boolean isConvertible(Object value) {
/* 3431 */       if (Trace.isOn) {
/* 3432 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsIntegerPropertyValidator", "isConvertible(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 3438 */         convert("", value);
/*      */       }
/* 3440 */       catch (JMSException je) {
/* 3441 */         if (Trace.isOn) {
/* 3442 */           Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsIntegerPropertyValidator", "isConvertible(Object)", (Throwable)je);
/*      */         }
/*      */         
/* 3445 */         if (Trace.isOn) {
/* 3446 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsIntegerPropertyValidator", "isConvertible(Object)", 
/* 3447 */               Boolean.valueOf(false), 1);
/*      */         }
/* 3449 */         return false;
/*      */       } 
/* 3451 */       if (Trace.isOn) {
/* 3452 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsIntegerPropertyValidator", "isConvertible(Object)", 
/* 3453 */             Boolean.valueOf(true), 2);
/*      */       }
/* 3455 */       return true;
/*      */     }
/*      */     
/*      */     public Integer convert(String propertyName, Object value) throws JMSException {
/* 3459 */       if (Trace.isOn) {
/* 3460 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsIntegerPropertyValidator", "convert(String,Object)", new Object[] { propertyName, value });
/*      */       }
/*      */ 
/*      */       
/* 3464 */       if (value instanceof Integer) {
/* 3465 */         Integer traceRet1 = (Integer)value;
/* 3466 */         if (Trace.isOn) {
/* 3467 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsIntegerPropertyValidator", "convert(String,Object)", traceRet1, 1);
/*      */         }
/*      */         
/* 3470 */         return traceRet1;
/*      */       } 
/* 3472 */       if (value instanceof Byte) {
/* 3473 */         Byte byteValue = (Byte)value;
/* 3474 */         Integer traceRet2 = Integer.valueOf(byteValue.intValue());
/* 3475 */         if (Trace.isOn) {
/* 3476 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsIntegerPropertyValidator", "convert(String,Object)", traceRet2, 2);
/*      */         }
/*      */         
/* 3479 */         return traceRet2;
/*      */       } 
/* 3481 */       if (value instanceof Short) {
/* 3482 */         Short shortValue = (Short)value;
/* 3483 */         Integer traceRet3 = Integer.valueOf(shortValue.intValue());
/* 3484 */         if (Trace.isOn) {
/* 3485 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsIntegerPropertyValidator", "convert(String,Object)", traceRet3, 3);
/*      */         }
/*      */         
/* 3488 */         return traceRet3;
/*      */       } 
/* 3490 */       if (value instanceof String) {
/*      */         try {
/* 3492 */           Integer traceRet4 = Integer.valueOf((String)value);
/* 3493 */           if (Trace.isOn) {
/* 3494 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsIntegerPropertyValidator", "convert(String,Object)", traceRet4, 4);
/*      */           }
/*      */           
/* 3497 */           return traceRet4;
/*      */         }
/* 3499 */         catch (NumberFormatException nfe) {
/* 3500 */           if (Trace.isOn) {
/* 3501 */             Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsIntegerPropertyValidator", "convert(String,Object)", nfe);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 3506 */           HashMap<String, Object> hashMap = new HashMap<>();
/* 3507 */           hashMap.put("XMSC_INSERT_PROPERTY", propertyName);
/* 3508 */           hashMap.put("XMSC_INSERT_TYPE", "java.lang.Integer");
/* 3509 */           hashMap.put("XMSC_INSERT_OTHER_TYPE", value.getClass());
/* 3510 */           hashMap.put("XMSC_INSERT_VALUE", value);
/* 3511 */           JMSException jMSException = (JMSException)JmsErrorUtils.createException("JMSCC0069", hashMap);
/*      */           
/* 3513 */           if (Trace.isOn) {
/* 3514 */             Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsIntegerPropertyValidator", "convert(String,Object)", (Throwable)jMSException, 1);
/*      */           }
/*      */ 
/*      */           
/* 3518 */           throw jMSException;
/*      */         } 
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 3524 */       HashMap<String, Object> inserts = new HashMap<>();
/* 3525 */       inserts.put("XMSC_INSERT_PROPERTY", propertyName);
/* 3526 */       inserts.put("XMSC_INSERT_TYPE", "java.lang.Integer");
/* 3527 */       inserts.put("XMSC_INSERT_OTHER_TYPE", (value != null) ? value.getClass() : null);
/* 3528 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0068", inserts);
/*      */       
/* 3530 */       if (Trace.isOn) {
/* 3531 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsIntegerPropertyValidator", "convert(String,Object)", (Throwable)je, 2);
/*      */       }
/*      */       
/* 3534 */       throw je;
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
/*      */   static class JmsLongPropertyValidator
/*      */     implements JMSMessagePropertyValueConverter
/*      */   {
/*      */     public boolean isConvertible(Object value) {
/* 3549 */       if (Trace.isOn) {
/* 3550 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsLongPropertyValidator", "isConvertible(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 3556 */         convert("", value);
/*      */       }
/* 3558 */       catch (JMSException je) {
/* 3559 */         if (Trace.isOn) {
/* 3560 */           Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsLongPropertyValidator", "isConvertible(Object)", (Throwable)je);
/*      */         }
/*      */         
/* 3563 */         if (Trace.isOn) {
/* 3564 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsLongPropertyValidator", "isConvertible(Object)", 
/* 3565 */               Boolean.valueOf(false), 1);
/*      */         }
/* 3567 */         return false;
/*      */       } 
/* 3569 */       if (Trace.isOn) {
/* 3570 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsLongPropertyValidator", "isConvertible(Object)", 
/* 3571 */             Boolean.valueOf(true), 2);
/*      */       }
/* 3573 */       return true;
/*      */     }
/*      */     
/*      */     public Long convert(String propertyName, Object value) throws JMSException {
/* 3577 */       if (Trace.isOn) {
/* 3578 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsLongPropertyValidator", "convert(String,Object)", new Object[] { propertyName, value });
/*      */       }
/*      */ 
/*      */       
/* 3582 */       if (value instanceof Long) {
/* 3583 */         Long traceRet1 = (Long)value;
/* 3584 */         if (Trace.isOn) {
/* 3585 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsLongPropertyValidator", "convert(String,Object)", traceRet1, 1);
/*      */         }
/*      */         
/* 3588 */         return traceRet1;
/*      */       } 
/* 3590 */       if (value instanceof Byte) {
/* 3591 */         Byte byteValue = (Byte)value;
/* 3592 */         Long traceRet2 = Long.valueOf(byteValue.longValue());
/* 3593 */         if (Trace.isOn) {
/* 3594 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsLongPropertyValidator", "convert(String,Object)", traceRet2, 2);
/*      */         }
/*      */         
/* 3597 */         return traceRet2;
/*      */       } 
/* 3599 */       if (value instanceof Short) {
/* 3600 */         Short shortValue = (Short)value;
/* 3601 */         Long traceRet3 = Long.valueOf(shortValue.longValue());
/* 3602 */         if (Trace.isOn) {
/* 3603 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsLongPropertyValidator", "convert(String,Object)", traceRet3, 3);
/*      */         }
/*      */         
/* 3606 */         return traceRet3;
/*      */       } 
/* 3608 */       if (value instanceof Integer) {
/* 3609 */         Integer integerValue = (Integer)value;
/* 3610 */         Long traceRet4 = Long.valueOf(integerValue.longValue());
/* 3611 */         if (Trace.isOn) {
/* 3612 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsLongPropertyValidator", "convert(String,Object)", traceRet4, 4);
/*      */         }
/*      */         
/* 3615 */         return traceRet4;
/*      */       } 
/* 3617 */       if (value instanceof String) {
/*      */         try {
/* 3619 */           Long traceRet5 = Long.valueOf((String)value);
/* 3620 */           if (Trace.isOn) {
/* 3621 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsLongPropertyValidator", "convert(String,Object)", traceRet5, 5);
/*      */           }
/*      */           
/* 3624 */           return traceRet5;
/*      */         }
/* 3626 */         catch (NumberFormatException nfe) {
/* 3627 */           if (Trace.isOn) {
/* 3628 */             Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsLongPropertyValidator", "convert(String,Object)", nfe);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 3633 */           HashMap<String, Object> hashMap = new HashMap<>();
/* 3634 */           hashMap.put("XMSC_INSERT_PROPERTY", propertyName);
/* 3635 */           hashMap.put("XMSC_INSERT_TYPE", "java.lang.Long");
/* 3636 */           hashMap.put("XMSC_INSERT_OTHER_TYPE", value.getClass());
/* 3637 */           hashMap.put("XMSC_INSERT_VALUE", value);
/* 3638 */           JMSException jMSException = (JMSException)JmsErrorUtils.createException("JMSCC0069", hashMap);
/*      */           
/* 3640 */           if (Trace.isOn) {
/* 3641 */             Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsLongPropertyValidator", "convert(String,Object)", (Throwable)jMSException, 1);
/*      */           }
/*      */           
/* 3644 */           throw jMSException;
/*      */         } 
/*      */       }
/*      */ 
/*      */       
/* 3649 */       HashMap<String, Object> inserts = new HashMap<>();
/* 3650 */       inserts.put("XMSC_INSERT_PROPERTY", propertyName);
/* 3651 */       inserts.put("XMSC_INSERT_TYPE", "java.lang.Long");
/* 3652 */       inserts.put("XMSC_INSERT_OTHER_TYPE", (value != null) ? value.getClass() : null);
/* 3653 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0068", inserts);
/*      */       
/* 3655 */       if (Trace.isOn) {
/* 3656 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsLongPropertyValidator", "convert(String,Object)", (Throwable)je, 2);
/*      */       }
/*      */       
/* 3659 */       throw je;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class JmsFloatPropertyValidator
/*      */     implements JMSMessagePropertyValueConverter
/*      */   {
/*      */     public boolean isConvertible(Object value) {
/* 3671 */       if (Trace.isOn) {
/* 3672 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsFloatPropertyValidator", "isConvertible(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 3678 */         convert("", value);
/*      */       }
/* 3680 */       catch (JMSException je) {
/* 3681 */         if (Trace.isOn) {
/* 3682 */           Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsFloatPropertyValidator", "isConvertible(Object)", (Throwable)je);
/*      */         }
/*      */         
/* 3685 */         if (Trace.isOn) {
/* 3686 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsFloatPropertyValidator", "isConvertible(Object)", 
/* 3687 */               Boolean.valueOf(false), 1);
/*      */         }
/* 3689 */         return false;
/*      */       } 
/* 3691 */       if (Trace.isOn) {
/* 3692 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsFloatPropertyValidator", "isConvertible(Object)", 
/* 3693 */             Boolean.valueOf(true), 2);
/*      */       }
/* 3695 */       return true;
/*      */     }
/*      */     
/*      */     public Float convert(String propertyName, Object value) throws JMSException {
/* 3699 */       if (Trace.isOn) {
/* 3700 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsFloatPropertyValidator", "convert(String,Object)", new Object[] { propertyName, value });
/*      */       }
/*      */ 
/*      */       
/* 3704 */       if (value instanceof Float) {
/* 3705 */         Float traceRet1 = (Float)value;
/* 3706 */         if (Trace.isOn) {
/* 3707 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsFloatPropertyValidator", "convert(String,Object)", traceRet1, 1);
/*      */         }
/*      */         
/* 3710 */         return traceRet1;
/*      */       } 
/* 3712 */       if (value instanceof String) {
/*      */         try {
/* 3714 */           Float traceRet2 = Float.valueOf((String)value);
/* 3715 */           if (Trace.isOn) {
/* 3716 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsFloatPropertyValidator", "convert(String,Object)", traceRet2, 2);
/*      */           }
/*      */           
/* 3719 */           return traceRet2;
/*      */         }
/* 3721 */         catch (NumberFormatException nfe) {
/* 3722 */           if (Trace.isOn) {
/* 3723 */             Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsFloatPropertyValidator", "convert(String,Object)", nfe);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 3728 */           HashMap<String, Object> hashMap = new HashMap<>();
/* 3729 */           hashMap.put("XMSC_INSERT_PROPERTY", propertyName);
/* 3730 */           hashMap.put("XMSC_INSERT_TYPE", "java.lang.Float");
/* 3731 */           hashMap.put("XMSC_INSERT_OTHER_TYPE", value.getClass());
/* 3732 */           hashMap.put("XMSC_INSERT_VALUE", value);
/* 3733 */           JMSException jMSException = (JMSException)JmsErrorUtils.createException("JMSCC0069", hashMap);
/*      */           
/* 3735 */           if (Trace.isOn) {
/* 3736 */             Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsFloatPropertyValidator", "convert(String,Object)", (Throwable)jMSException, 1);
/*      */           }
/*      */           
/* 3739 */           throw jMSException;
/*      */         } 
/*      */       }
/*      */ 
/*      */       
/* 3744 */       HashMap<String, Object> inserts = new HashMap<>();
/* 3745 */       inserts.put("XMSC_INSERT_PROPERTY", propertyName);
/* 3746 */       inserts.put("XMSC_INSERT_TYPE", "java.lang.Float");
/* 3747 */       inserts.put("XMSC_INSERT_OTHER_TYPE", (value != null) ? value.getClass() : null);
/* 3748 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0068", inserts);
/*      */       
/* 3750 */       if (Trace.isOn) {
/* 3751 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsFloatPropertyValidator", "convert(String,Object)", (Throwable)je, 2);
/*      */       }
/*      */       
/* 3754 */       throw je;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class JmsDoublePropertyValidator
/*      */     implements JMSMessagePropertyValueConverter
/*      */   {
/*      */     public boolean isConvertible(Object value) {
/* 3767 */       if (Trace.isOn) {
/* 3768 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsDoublePropertyValidator", "isConvertible(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 3774 */         convert("", value);
/*      */       }
/* 3776 */       catch (JMSException je) {
/* 3777 */         if (Trace.isOn) {
/* 3778 */           Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsDoublePropertyValidator", "isConvertible(Object)", (Throwable)je);
/*      */         }
/*      */         
/* 3781 */         if (Trace.isOn) {
/* 3782 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsDoublePropertyValidator", "isConvertible(Object)", 
/* 3783 */               Boolean.valueOf(false), 1);
/*      */         }
/* 3785 */         return false;
/*      */       } 
/* 3787 */       if (Trace.isOn) {
/* 3788 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsDoublePropertyValidator", "isConvertible(Object)", 
/* 3789 */             Boolean.valueOf(true), 2);
/*      */       }
/* 3791 */       return true;
/*      */     }
/*      */     
/*      */     public Double convert(String propertyName, Object value) throws JMSException {
/* 3795 */       if (Trace.isOn) {
/* 3796 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsDoublePropertyValidator", "convert(String,Object)", new Object[] { propertyName, value });
/*      */       }
/*      */ 
/*      */       
/* 3800 */       if (value instanceof Double) {
/* 3801 */         Double traceRet1 = (Double)value;
/* 3802 */         if (Trace.isOn) {
/* 3803 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsDoublePropertyValidator", "convert(String,Object)", traceRet1, 1);
/*      */         }
/*      */         
/* 3806 */         return traceRet1;
/*      */       } 
/* 3808 */       if (value instanceof Float) {
/* 3809 */         Float floatValue = (Float)value;
/* 3810 */         Double traceRet2 = Double.valueOf(floatValue.doubleValue());
/* 3811 */         if (Trace.isOn) {
/* 3812 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsDoublePropertyValidator", "convert(String,Object)", traceRet2, 2);
/*      */         }
/*      */         
/* 3815 */         return traceRet2;
/*      */       } 
/* 3817 */       if (value instanceof String) {
/*      */         try {
/* 3819 */           Double traceRet3 = Double.valueOf((String)value);
/* 3820 */           if (Trace.isOn) {
/* 3821 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsDoublePropertyValidator", "convert(String,Object)", traceRet3, 3);
/*      */           }
/*      */           
/* 3824 */           return traceRet3;
/*      */         }
/* 3826 */         catch (NumberFormatException nfe) {
/* 3827 */           if (Trace.isOn) {
/* 3828 */             Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsDoublePropertyValidator", "convert(String,Object)", nfe);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 3833 */           HashMap<String, Object> hashMap = new HashMap<>();
/* 3834 */           hashMap.put("XMSC_INSERT_PROPERTY", propertyName);
/* 3835 */           hashMap.put("XMSC_INSERT_TYPE", "java.lang.Double");
/* 3836 */           hashMap.put("XMSC_INSERT_OTHER_TYPE", value.getClass());
/* 3837 */           hashMap.put("XMSC_INSERT_VALUE", value);
/* 3838 */           JMSException jMSException = (JMSException)JmsErrorUtils.createException("JMSCC0069", hashMap);
/*      */           
/* 3840 */           if (Trace.isOn) {
/* 3841 */             Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsDoublePropertyValidator", "convert(String,Object)", (Throwable)jMSException, 1);
/*      */           }
/*      */           
/* 3844 */           throw jMSException;
/*      */         } 
/*      */       }
/*      */ 
/*      */       
/* 3849 */       HashMap<String, Object> inserts = new HashMap<>();
/* 3850 */       inserts.put("XMSC_INSERT_PROPERTY", propertyName);
/* 3851 */       inserts.put("XMSC_INSERT_TYPE", "java.lang.Double");
/* 3852 */       inserts.put("XMSC_INSERT_OTHER_TYPE", (value != null) ? value.getClass() : null);
/* 3853 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0068", inserts);
/*      */       
/* 3855 */       if (Trace.isOn) {
/* 3856 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsDoublePropertyValidator", "convert(String,Object)", (Throwable)je, 2);
/*      */       }
/*      */       
/* 3859 */       throw je;
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
/*      */   static class JmsStringPropertyValidator
/*      */     implements JMSMessagePropertyValueConverter
/*      */   {
/*      */     public boolean isConvertible(Object value) {
/* 3877 */       if (Trace.isOn) {
/* 3878 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsStringPropertyValidator", "isConvertible(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 3884 */         convert("", value);
/*      */       }
/* 3886 */       catch (JMSException je) {
/* 3887 */         if (Trace.isOn) {
/* 3888 */           Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsStringPropertyValidator", "isConvertible(Object)", (Throwable)je);
/*      */         }
/*      */         
/* 3891 */         if (Trace.isOn) {
/* 3892 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsStringPropertyValidator", "isConvertible(Object)", 
/* 3893 */               Boolean.valueOf(false), 1);
/*      */         }
/* 3895 */         return false;
/*      */       } 
/* 3897 */       if (Trace.isOn) {
/* 3898 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsStringPropertyValidator", "isConvertible(Object)", 
/* 3899 */             Boolean.valueOf(true), 2);
/*      */       }
/* 3901 */       return true;
/*      */     }
/*      */     
/*      */     public String convert(String propertyName, Object value) throws JMSException {
/* 3905 */       if (Trace.isOn) {
/* 3906 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsStringPropertyValidator", "convert(String,Object)", new Object[] { propertyName, value });
/*      */       }
/*      */       
/* 3909 */       String traceRet1 = String.valueOf(value);
/* 3910 */       if (Trace.isOn) {
/* 3911 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsStringPropertyValidator", "convert(String,Object)", traceRet1);
/*      */       }
/*      */       
/* 3914 */       return traceRet1;
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsMessageImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */