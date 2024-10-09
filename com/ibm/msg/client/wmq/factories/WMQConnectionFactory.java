/*      */ package com.ibm.msg.client.wmq.factories;
/*      */ 
/*      */ import com.ibm.mq.MQException;
/*      */ import com.ibm.mq.constants.CMQC;
/*      */ import com.ibm.mq.constants.QmgrAdvancedCapability;
/*      */ import com.ibm.mq.jmqi.JmqiDefaultPropertyHandler;
/*      */ import com.ibm.mq.jmqi.JmqiDefaultThreadPoolFactory;
/*      */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*      */ import com.ibm.mq.jmqi.JmqiException;
/*      */ import com.ibm.mq.jmqi.JmqiFactory;
/*      */ import com.ibm.mq.jmqi.JmqiMQ;
/*      */ import com.ibm.mq.jmqi.JmqiPropertyHandler;
/*      */ import com.ibm.mq.jmqi.JmqiThreadPoolFactory;
/*      */ import com.ibm.mq.jmqi.MQOD;
/*      */ import com.ibm.mq.jmqi.handles.Hconn;
/*      */ import com.ibm.mq.jmqi.handles.Hobj;
/*      */ import com.ibm.mq.jmqi.handles.Phobj;
/*      */ import com.ibm.mq.jmqi.handles.Pint;
/*      */ import com.ibm.mq.jmqi.system.JmqiComponent;
/*      */ import com.ibm.mq.jmqi.system.JmqiComponentTls;
/*      */ import com.ibm.mq.jmqi.system.JmqiSP;
/*      */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*      */ import com.ibm.mq.jms.MQConnectionFactory;
/*      */ import com.ibm.mq.jms.MQQueueConnectionFactory;
/*      */ import com.ibm.mq.jms.MQXAConnectionFactory;
/*      */ import com.ibm.mq.jms.MQXAQueueConnectionFactory;
/*      */ import com.ibm.mq.jms.MQXATopicConnectionFactory;
/*      */ import com.ibm.msg.client.commonservices.Log.Log;
/*      */ import com.ibm.msg.client.commonservices.cssystem.CSSystem;
/*      */ import com.ibm.msg.client.commonservices.cssystem.WASSupport;
/*      */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*      */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*      */ import com.ibm.msg.client.jms.internal.JmsErrorUtils;
/*      */ import com.ibm.msg.client.provider.ProviderConnection;
/*      */ import com.ibm.msg.client.provider.ProviderConnectionFactory;
/*      */ import com.ibm.msg.client.wmq.common.WMQConnectionName;
/*      */ import com.ibm.msg.client.wmq.common.WMQConnectionNameList;
/*      */ import com.ibm.msg.client.wmq.common.WMQThreadLocalStorage;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQPropertyContext;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQStandardValidators;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQValidationInterface;
/*      */ import com.ibm.msg.client.wmq.compat.jms.internal.MQConnection;
/*      */ import com.ibm.msg.client.wmq.internal.RebalancingApplicationType;
/*      */ import com.ibm.msg.client.wmq.internal.WMQConnection;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.lang.reflect.Field;
/*      */ import java.net.MalformedURLException;
/*      */ import java.net.URL;
/*      */ import java.security.AccessControlException;
/*      */ import java.security.AccessController;
/*      */ import java.security.PrivilegedAction;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.Vector;
/*      */ import java.util.regex.Pattern;
/*      */ import javax.jms.JMSException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class WMQConnectionFactory
/*      */   extends WMQPropertyContext
/*      */   implements ProviderConnectionFactory, JmqiComponent
/*      */ {
/*      */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.factories/src/com/ibm/msg/client/wmq/factories/WMQConnectionFactory.java";
/*      */   private static final long serialVersionUID = 4815162342L;
/*      */   protected static final String forceDontUseJmqiWorkerThreadProperty = "com.ibm.msg.client.wmq.internal.forceDontUseJmqiWorkerThread";
/*      */   
/*      */   static {
/*  123 */     if (Trace.isOn) {
/*  124 */       Trace.data("com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.factories/src/com/ibm/msg/client/wmq/factories/WMQConnectionFactory.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static void checkNotQueueCF(Object parent, String calledFrom) throws JMSException {
/*  135 */     if (Trace.isOn) {
/*  136 */       Trace.entry("com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "checkNotQueueCF(Object,String)", new Object[] { parent, calledFrom });
/*      */     }
/*      */     
/*  139 */     if (parent instanceof MQQueueConnectionFactory) {
/*  140 */       HashMap<String, String> inserts = new HashMap<>();
/*  141 */       inserts.put("XMSC_INSERT_METHOD", calledFrom);
/*  142 */       inserts.put("XMSC_INSERT_TYPE", parent.getClass().getName());
/*  143 */       JMSException je = (JMSException)NLSServices.createException("JMSMQ1112", inserts);
/*  144 */       if (Trace.isOn) {
/*  145 */         Trace.throwing("com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "checkNotQueueCF(Object,String)", (Throwable)je);
/*      */       }
/*      */       
/*  148 */       throw je;
/*      */     } 
/*  150 */     if (Trace.isOn) {
/*  151 */       Trace.exit("com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "checkNotQueueCF(Object,String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static void checkNotTopicCF(Object parent, String calledFrom) throws JMSException {
/*  158 */     if (Trace.isOn) {
/*  159 */       Trace.entry("com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "checkNotTopicCF(Object,String)", new Object[] { parent, calledFrom });
/*      */     }
/*      */     
/*  162 */     if (parent instanceof com.ibm.mq.jms.MQTopicConnectionFactory) {
/*  163 */       HashMap<String, String> inserts = new HashMap<>();
/*  164 */       inserts.put("XMSC_INSERT_METHOD", calledFrom);
/*  165 */       inserts.put("XMSC_INSERT_TYPE", parent.getClass().getName());
/*  166 */       JMSException je = (JMSException)NLSServices.createException("JMSMQ1112", inserts);
/*  167 */       if (Trace.isOn) {
/*  168 */         Trace.throwing("com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "checkNotTopicCF(Object,String)", (Throwable)je);
/*      */       }
/*      */       
/*  171 */       throw je;
/*      */     } 
/*  173 */     if (Trace.isOn) {
/*  174 */       Trace.exit("com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "checkNotTopicCF(Object,String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQAdminObjectPropertyValidator
/*      */     extends WMQStandardValidators.WMQTrivialPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 7919748242803675773L;
/*      */ 
/*      */     
/*  187 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(1, 1.0F);
/*      */     static {
/*  189 */       if (Trace.isOn) {
/*  190 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQAdminObjectPropertyValidator", "static()");
/*      */       }
/*  192 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_ADMIN_OBJECT_TYPE");
/*  193 */       if (Trace.isOn) {
/*  194 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQAdminObjectPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/*  199 */       if (Trace.isOn) {
/*  200 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQAdminObjectPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/*  203 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/*  204 */         propertyValidators.put(entry.getValue(), new WMQAdminObjectPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/*  206 */       if (Trace.isOn) {
/*  207 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQAdminObjectPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQAdminObjectPropertyValidator(int domain) {
/*  218 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/*  223 */       if (Trace.isOn) {
/*  224 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQAdminObjectPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  229 */       if (Trace.isOn) {
/*  230 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQAdminObjectPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static class WMQAppNamePropertyValidator
/*      */     extends WMQStandardValidators.WMQApplicationNamePropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -6875808309996286570L;
/*      */     
/*  241 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/*  243 */       if (Trace.isOn) {
/*  244 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQAppNamePropertyValidator", "static()");
/*      */       }
/*  246 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_APPNAME");
/*  247 */       domainsToPropertyNames.put(Integer.valueOf(1), "APPLICATIONNAME");
/*  248 */       domainsToPropertyNames.put(Integer.valueOf(2), "APPNAME");
/*  249 */       if (Trace.isOn) {
/*  250 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQAppNamePropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/*  255 */       if (Trace.isOn) {
/*  256 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQAppNamePropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/*  259 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/*  260 */         propertyValidators.put(entry.getValue(), new WMQAppNamePropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/*  262 */       if (Trace.isOn) {
/*  263 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQAppNamePropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQAppNamePropertyValidator(int domain) {
/*  271 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/*  277 */       if (Trace.isOn) {
/*  278 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQAppNamePropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */       
/*  282 */       if (Trace.isOn) {
/*  283 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQAppNamePropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQBrokerCCSubQPropertyValidator
/*      */     extends WMQStandardValidators.WMQBrokerQueueNamePropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -6998439326748223037L;
/*      */ 
/*      */ 
/*      */     
/*  298 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/*  300 */       if (Trace.isOn) {
/*  301 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQBrokerCCSubQPropertyValidator", "static()");
/*      */       }
/*      */       
/*  304 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_BROKER_CC_SUBQ");
/*  305 */       domainsToPropertyNames.put(Integer.valueOf(1), "BROKERCCSUBQ");
/*  306 */       domainsToPropertyNames.put(Integer.valueOf(2), "CCSUB");
/*  307 */       if (Trace.isOn) {
/*  308 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQBrokerCCSubQPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/*  313 */       if (Trace.isOn) {
/*  314 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQBrokerCCSubQPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/*  317 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/*  318 */         propertyValidators.put(entry.getValue(), new WMQBrokerCCSubQPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/*  320 */       if (Trace.isOn) {
/*  321 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQBrokerCCSubQPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQBrokerCCSubQPropertyValidator(int domain) {
/*  332 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */     
/*      */     public void topicCFvalidate(Object parent) throws JMSException {
/*  337 */       if (Trace.isOn) {
/*  338 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQBrokerCCSubQPropertyValidator", "topicCFvalidate(Object)", new Object[] { parent });
/*      */       }
/*      */       
/*  341 */       WMQConnectionFactory.checkNotQueueCF(parent, "WMQBrokerCCSubQPropertyValidator");
/*  342 */       if (Trace.isOn) {
/*  343 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQBrokerCCSubQPropertyValidator", "topicCFvalidate(Object)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/*  351 */       if (Trace.isOn) {
/*  352 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQBrokerCCSubQPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  357 */       if (Trace.isOn) {
/*  358 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQBrokerCCSubQPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQBrokerControlQPropertyValidator
/*      */     extends WMQStandardValidators.WMQQueueNamePropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 7226072931501351962L;
/*      */ 
/*      */ 
/*      */     
/*  373 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/*  375 */       if (Trace.isOn) {
/*  376 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQBrokerControlQPropertyValidator", "static()");
/*      */       }
/*      */       
/*  379 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_BROKER_CONTROLQ");
/*  380 */       domainsToPropertyNames.put(Integer.valueOf(1), "BROKERCONQ");
/*  381 */       domainsToPropertyNames.put(Integer.valueOf(2), "BCON");
/*  382 */       if (Trace.isOn) {
/*  383 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQBrokerControlQPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/*  389 */       if (Trace.isOn) {
/*  390 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQBrokerControlQPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/*  393 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/*  394 */         propertyValidators.put(entry.getValue(), new WMQBrokerControlQPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/*  396 */       if (Trace.isOn) {
/*  397 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQBrokerControlQPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQBrokerControlQPropertyValidator(int domain) {
/*  408 */       super(domain, domainsToPropertyNames);
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
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/*  421 */       WMQConnectionFactory.checkNotQueueCF(parent, "setBrokerControlQ()");
/*      */       
/*  423 */       boolean traceRet1 = super.validate(value, parent);
/*  424 */       return traceRet1;
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/*  429 */       if (Trace.isOn) {
/*  430 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQBrokerControlQPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  435 */       if (Trace.isOn) {
/*  436 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQBrokerControlQPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQBrokerPubQPropertyValidator
/*      */     extends WMQStandardValidators.WMQQueueNamePropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 595921449503109875L;
/*      */ 
/*      */     
/*  450 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/*  452 */       if (Trace.isOn) {
/*  453 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQBrokerPubQPropertyValidator", "static()");
/*      */       }
/*  455 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_BROKER_PUBQ");
/*  456 */       domainsToPropertyNames.put(Integer.valueOf(1), "BROKERPUBQ");
/*  457 */       domainsToPropertyNames.put(Integer.valueOf(2), "BPUB");
/*  458 */       if (Trace.isOn) {
/*  459 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQBrokerPubQPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/*  464 */       if (Trace.isOn) {
/*  465 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQBrokerPubQPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/*  468 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/*  469 */         propertyValidators.put(entry.getValue(), new WMQBrokerPubQPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/*  471 */       if (Trace.isOn) {
/*  472 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQBrokerPubQPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQBrokerPubQPropertyValidator(int domain) {
/*  483 */       super(domain, domainsToPropertyNames);
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
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/*  496 */       WMQConnectionFactory.checkNotQueueCF(parent, "setBrokerPubQ()");
/*      */       
/*  498 */       boolean traceRet1 = super.validate(value, parent);
/*  499 */       return traceRet1;
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/*  504 */       if (Trace.isOn) {
/*  505 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQBrokerPubQPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  510 */       if (Trace.isOn) {
/*  511 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQBrokerPubQPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQBrokerQMgrPropertyValidator
/*      */     extends WMQStandardValidators.WMQQueueNamePropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -412165032155030940L;
/*      */ 
/*      */     
/*  525 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/*  527 */       if (Trace.isOn) {
/*  528 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQBrokerQMgrPropertyValidator", "static()");
/*      */       }
/*  530 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_BROKER_QMGR");
/*  531 */       domainsToPropertyNames.put(Integer.valueOf(1), "BROKERQMGR");
/*  532 */       domainsToPropertyNames.put(Integer.valueOf(2), "BQM");
/*  533 */       if (Trace.isOn) {
/*  534 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQBrokerQMgrPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/*  539 */       if (Trace.isOn) {
/*  540 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQBrokerQMgrPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/*  543 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/*  544 */         propertyValidators.put(entry.getValue(), new WMQBrokerQMgrPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/*  546 */       if (Trace.isOn) {
/*  547 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQBrokerQMgrPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQBrokerQMgrPropertyValidator(int domain) {
/*  558 */       super(domain, domainsToPropertyNames);
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
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/*  571 */       WMQConnectionFactory.checkNotQueueCF(parent, "setBrokerQMgr()");
/*      */       
/*  573 */       boolean traceRet1 = super.validate(value, parent);
/*  574 */       return traceRet1;
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/*  579 */       if (Trace.isOn) {
/*  580 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQBrokerQMgrPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  585 */       if (Trace.isOn) {
/*  586 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQBrokerQMgrPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQBrokerSubQPropertyValidator
/*      */     extends WMQStandardValidators.WMQBrokerQueueNamePropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 5366967103691046747L;
/*      */ 
/*      */     
/*  600 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/*  602 */       if (Trace.isOn) {
/*  603 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQBrokerSubQPropertyValidator", "static()");
/*      */       }
/*  605 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_BROKER_SUBQ");
/*  606 */       domainsToPropertyNames.put(Integer.valueOf(1), "BROKERSUBQ");
/*  607 */       domainsToPropertyNames.put(Integer.valueOf(2), "BSUB");
/*  608 */       if (Trace.isOn) {
/*  609 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQBrokerSubQPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/*  614 */       if (Trace.isOn) {
/*  615 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQBrokerSubQPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/*  618 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/*  619 */         propertyValidators.put(entry.getValue(), new WMQBrokerSubQPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/*  621 */       if (Trace.isOn) {
/*  622 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQBrokerSubQPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQBrokerSubQPropertyValidator(int domain) {
/*  633 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */     
/*      */     public void topicCFvalidate(Object parent) throws JMSException {
/*  638 */       if (Trace.isOn) {
/*  639 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQBrokerSubQPropertyValidator", "topicCFvalidate(Object)", new Object[] { parent });
/*      */       }
/*      */       
/*  642 */       WMQConnectionFactory.checkNotQueueCF(parent, "WMQBrokerCCSubQPropertyValidator");
/*  643 */       if (Trace.isOn) {
/*  644 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQBrokerSubQPropertyValidator", "topicCFvalidate(Object)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/*  652 */       if (Trace.isOn) {
/*  653 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQBrokerSubQPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  658 */       if (Trace.isOn) {
/*  659 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQBrokerSubQPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQBrokerVersionPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 2993529085855398328L;
/*      */ 
/*      */ 
/*      */     
/*  674 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/*  676 */       if (Trace.isOn) {
/*  677 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQBrokerVersionPropertyValidator", "static()");
/*      */       }
/*      */       
/*  680 */       domainsToPropertyNames.put(Integer.valueOf(4), "brokerVersion");
/*  681 */       domainsToPropertyNames.put(Integer.valueOf(1), "BROKERVER");
/*  682 */       domainsToPropertyNames.put(Integer.valueOf(2), "BVER");
/*  683 */       if (Trace.isOn) {
/*  684 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQBrokerVersionPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/*  690 */       if (Trace.isOn) {
/*  691 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQBrokerVersionPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/*  694 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/*  695 */         propertyValidators.put(entry.getValue(), new WMQBrokerVersionPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/*  697 */       if (Trace.isOn) {
/*  698 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQBrokerVersionPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  704 */     private static HashMap<Object, Object> valuesToCanonical = new HashMap<>(3, 1.0F);
/*  705 */     private static Set<Object> validValues = new HashSet(3, 1.0F);
/*      */     static {
/*  707 */       if (Trace.isOn) {
/*  708 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQBrokerVersionPropertyValidator", "static()");
/*      */       }
/*      */       
/*  711 */       valuesToCanonical.put("V1", Integer.valueOf(0));
/*  712 */       valuesToCanonical.put("V2", Integer.valueOf(1));
/*  713 */       valuesToCanonical.put("UNSPECIFIED", Integer.valueOf(-1));
/*  714 */       validValues.addAll(valuesToCanonical.values());
/*  715 */       if (Trace.isOn) {
/*  716 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQBrokerVersionPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQBrokerVersionPropertyValidator(int domain) {
/*  726 */       super(domain, domainsToPropertyNames, valuesToCanonical);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object valueP, Object parent) throws JMSException {
/*  733 */       Object value = valueP;
/*      */       
/*  735 */       WMQConnectionFactory.checkNotQueueCF(parent, "setBrokerVersion()");
/*      */       
/*  737 */       if (validateIntBySet(value, validValues)) {
/*  738 */         updateBrokerVersionFlag(parent);
/*  739 */         return true;
/*      */       } 
/*      */       
/*  742 */       return false;
/*      */     }
/*      */     
/*      */     private void updateBrokerVersionFlag(final Object parent) {
/*  746 */       if (Trace.isOn) {
/*  747 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQBrokerVersionPropertyValidator", "updateBrokerVersionFlag(final Object)", new Object[] { parent });
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  756 */       if (parent instanceof MQConnectionFactory) {
/*  757 */         AccessController.doPrivileged(new PrivilegedAction()
/*      */             {
/*      */               public Object run()
/*      */               {
/*  761 */                 if (Trace.isOn) {
/*  762 */                   Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQBrokerVersionPropertyValidator", "run()");
/*      */                 }
/*      */                 
/*      */                 try {
/*  766 */                   Field bverSetField = MQConnectionFactory.class.getDeclaredField("bverSet");
/*  767 */                   bverSetField.setAccessible(true);
/*  768 */                   bverSetField.setBoolean(parent, true);
/*      */                 }
/*  770 */                 catch (NoSuchFieldException nsfe) {
/*  771 */                   if (Trace.isOn) {
/*  772 */                     Trace.catchBlock(this, "com.ibm.msg.client.wmq.factories.null", "run()", nsfe, 1);
/*      */                   }
/*      */                 }
/*  775 */                 catch (IllegalAccessException iae) {
/*  776 */                   if (Trace.isOn) {
/*  777 */                     Trace.catchBlock(this, "com.ibm.msg.client.wmq.factories.null", "run()", iae, 2);
/*      */                   }
/*      */                 } finally {
/*      */                   
/*  781 */                   if (Trace.isOn) {
/*  782 */                     Trace.finallyBlock(this, "com.ibm.msg.client.wmq.factories.null", "run()");
/*      */                   }
/*      */                 } 
/*      */                 
/*  786 */                 if (Trace.isOn) {
/*  787 */                   Trace.exit(this, "com.ibm.msg.client.wmq.factories.null", "run()", null);
/*      */                 }
/*  789 */                 return null;
/*      */               }
/*      */             });
/*      */       }
/*      */       
/*  794 */       if (Trace.isOn) {
/*  795 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQBrokerVersionPropertyValidator", "updateBrokerVersionFlag(final Object)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object version) throws JMSException {
/*  803 */       if (Trace.isOn) {
/*  804 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQBrokerVersionPropertyValidator", "throwBadValueException(Object)", new Object[] { version });
/*      */       }
/*      */       
/*  807 */       HashMap<String, String> info = new HashMap<>();
/*  808 */       info.put("XMSC_INSERT_VALUE", String.valueOf(version));
/*  809 */       info.put("XMSC_INSERT_NAME", "brokerVersion");
/*  810 */       JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/*      */       
/*  812 */       if (Trace.isOn) {
/*  813 */         Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQBrokerVersionPropertyValidator", "throwBadValueException(Object)", (Throwable)je);
/*      */       }
/*      */       
/*  816 */       throw je;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQQMgrCCSIDPropertyValidator
/*      */     extends WMQStandardValidators.WMQCcsidPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -4143839812665823110L;
/*      */     
/*  827 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/*  829 */       if (Trace.isOn) {
/*  830 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQQMgrCCSIDPropertyValidator", "static()");
/*      */       }
/*  832 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_QMGR_CCSID");
/*  833 */       domainsToPropertyNames.put(Integer.valueOf(1), "CCSID");
/*  834 */       domainsToPropertyNames.put(Integer.valueOf(2), "CCS");
/*  835 */       if (Trace.isOn) {
/*  836 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQQMgrCCSIDPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/*  841 */       if (Trace.isOn) {
/*  842 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQQMgrCCSIDPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/*  845 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/*  846 */         propertyValidators.put(entry.getValue(), new WMQQMgrCCSIDPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/*  848 */       if (Trace.isOn) {
/*  849 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQQMgrCCSIDPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQQMgrCCSIDPropertyValidator(int domain) {
/*  860 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean crossPropertyValidate(Object parent) throws JMSException {
/*  865 */       if (Trace.isOn) {
/*  866 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQQMgrCCSIDPropertyValidator", "crossPropertyValidate(Object)", new Object[] { parent });
/*      */       }
/*      */       
/*  869 */       if (((JmsPropertyContext)parent).getIntProperty("XMSC_WMQ_QMGR_CCSID") != 819 && (
/*  870 */         (JmsPropertyContext)parent).getIntProperty("XMSC_WMQ_CONNECTION_MODE") == 0) {
/*  871 */         if (Trace.isOn) {
/*  872 */           Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQQMgrCCSIDPropertyValidator", "crossPropertyValidate(Object)", 
/*  873 */               Boolean.valueOf(false), 1);
/*      */         }
/*  875 */         return false;
/*      */       } 
/*      */       
/*  878 */       if (Trace.isOn) {
/*  879 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQQMgrCCSIDPropertyValidator", "crossPropertyValidate(Object)", 
/*  880 */             Boolean.valueOf(true), 2);
/*      */       }
/*  882 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object ccsid) throws JMSException {
/*  887 */       if (Trace.isOn) {
/*  888 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQQMgrCCSIDPropertyValidator", "throwBadValueException(Object)", new Object[] { ccsid });
/*      */       }
/*      */       
/*  891 */       HashMap<String, String> info = new HashMap<>();
/*  892 */       info.put("XMSC_INSERT_VALUE", String.valueOf(ccsid));
/*  893 */       info.put("XMSC_INSERT_NAME", "CCSID");
/*  894 */       JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/*  895 */       if (Trace.isOn) {
/*  896 */         Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQQMgrCCSIDPropertyValidator", "throwBadValueException(Object)", (Throwable)je);
/*      */       }
/*      */       
/*  899 */       throw je;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQCCDTURLPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -4370671351805889291L;
/*      */     
/*  910 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/*  912 */       if (Trace.isOn) {
/*  913 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQCCDTURLPropertyValidator", "static()");
/*      */       }
/*  915 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_CCDTURL");
/*  916 */       domainsToPropertyNames.put(Integer.valueOf(1), "CCDTURL");
/*  917 */       domainsToPropertyNames.put(Integer.valueOf(2), "CCDT");
/*  918 */       if (Trace.isOn) {
/*  919 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQCCDTURLPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/*  924 */       if (Trace.isOn) {
/*  925 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQCCDTURLPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/*  928 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/*  929 */         propertyValidators.put(entry.getValue(), new WMQCCDTURLPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/*  931 */       if (Trace.isOn) {
/*  932 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQCCDTURLPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQCCDTURLPropertyValidator(int domain) {
/*  943 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object valueP, Object parent) throws JMSException {
/*  950 */       Object value = valueP;
/*      */ 
/*      */       
/*  953 */       if (value == null) {
/*  954 */         return true;
/*      */       }
/*  956 */       value = convertToString(value);
/*      */       
/*      */       try {
/*  959 */         new URL((String)value);
/*      */         
/*  961 */         return true;
/*      */       }
/*  963 */       catch (MalformedURLException e) {
/*      */         
/*  965 */         return false;
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean crossPropertyValidate(Object parent) throws JMSException {
/*  971 */       if (Trace.isOn) {
/*  972 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQCCDTURLPropertyValidator", "crossPropertyValidate(Object)", new Object[] { parent });
/*      */       }
/*      */       
/*  975 */       if (((JmsPropertyContext)parent).getStringProperty("XMSC_WMQ_CCDTURL") != null) {
/*  976 */         if (((JmsPropertyContext)parent).getIntProperty("XMSC_WMQ_CONNECTION_MODE") != 0 && "SYSTEM.DEF.SVRCONN"
/*  977 */           .equals(((JmsPropertyContext)parent).getStringProperty("XMSC_WMQ_CHANNEL"))) {
/*  978 */           if (Trace.isOn) {
/*  979 */             Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQCCDTURLPropertyValidator", "crossPropertyValidate(Object)", 
/*  980 */                 Boolean.valueOf(true), 1);
/*      */           }
/*  982 */           return true;
/*      */         } 
/*  984 */         if (Trace.isOn) {
/*  985 */           Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQCCDTURLPropertyValidator", "crossPropertyValidate(Object)", 
/*  986 */               Boolean.valueOf(false), 2);
/*      */         }
/*  988 */         return false;
/*      */       } 
/*  990 */       if (Trace.isOn) {
/*  991 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQCCDTURLPropertyValidator", "crossPropertyValidate(Object)", 
/*  992 */             Boolean.valueOf(true), 3);
/*      */       }
/*  994 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/*  999 */       if (Trace.isOn) {
/* 1000 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQCCDTURLPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1005 */       if (Trace.isOn) {
/* 1006 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQCCDTURLPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQChannelPropertyValidator
/*      */     extends WMQStandardValidators.WMQChannelNamePropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -7961979588050326660L;
/*      */ 
/*      */     
/* 1020 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 1022 */       if (Trace.isOn) {
/* 1023 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQChannelPropertyValidator", "static()");
/*      */       }
/* 1025 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_CHANNEL");
/* 1026 */       domainsToPropertyNames.put(Integer.valueOf(1), "CHANNEL");
/* 1027 */       domainsToPropertyNames.put(Integer.valueOf(2), "CHAN");
/* 1028 */       if (Trace.isOn) {
/* 1029 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQChannelPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 1034 */       if (Trace.isOn) {
/* 1035 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQChannelPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 1038 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 1039 */         propertyValidators.put(entry.getValue(), new WMQChannelPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 1041 */       if (Trace.isOn) {
/* 1042 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQChannelPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQChannelPropertyValidator(int domain) {
/* 1053 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean crossPropertyValidate(Object parent) throws JMSException {
/* 1058 */       if (Trace.isOn) {
/* 1059 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQChannelPropertyValidator", "crossPropertyValidate(Object)", new Object[] { parent });
/*      */       }
/*      */       
/* 1062 */       if (!((JmsPropertyContext)parent).getStringProperty("XMSC_WMQ_CHANNEL").equals("SYSTEM.DEF.SVRCONN")) {
/* 1063 */         if (((JmsPropertyContext)parent).getIntProperty("XMSC_WMQ_CONNECTION_MODE") != 0 && ((JmsPropertyContext)parent)
/* 1064 */           .getStringProperty("XMSC_WMQ_CCDTURL") == null) {
/* 1065 */           if (Trace.isOn) {
/* 1066 */             Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQChannelPropertyValidator", "crossPropertyValidate(Object)", 
/* 1067 */                 Boolean.valueOf(true), 1);
/*      */           }
/* 1069 */           return true;
/*      */         } 
/* 1071 */         if (Trace.isOn) {
/* 1072 */           Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQChannelPropertyValidator", "crossPropertyValidate(Object)", 
/* 1073 */               Boolean.valueOf(false), 2);
/*      */         }
/* 1075 */         return false;
/*      */       } 
/* 1077 */       if (Trace.isOn) {
/* 1078 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQChannelPropertyValidator", "crossPropertyValidate(Object)", 
/* 1079 */             Boolean.valueOf(true), 3);
/*      */       }
/* 1081 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 1086 */       if (Trace.isOn) {
/* 1087 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQChannelPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1092 */       if (Trace.isOn) {
/* 1093 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQChannelPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class WMQCleanupIntervalPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 2300294526348491914L;
/*      */ 
/*      */ 
/*      */     
/* 1108 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 1110 */       if (Trace.isOn) {
/* 1111 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQCleanupIntervalPropertyValidator", "static()");
/*      */       }
/*      */       
/* 1114 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_CLEANUP_INTERVAL");
/* 1115 */       domainsToPropertyNames.put(Integer.valueOf(1), "CLEANUPINT");
/* 1116 */       domainsToPropertyNames.put(Integer.valueOf(2), "CLINT");
/* 1117 */       if (Trace.isOn) {
/* 1118 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQCleanupIntervalPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 1124 */       if (Trace.isOn) {
/* 1125 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQCleanupIntervalPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 1128 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 1129 */         propertyValidators.put(entry.getValue(), new WMQCleanupIntervalPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 1131 */       if (Trace.isOn) {
/* 1132 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQCleanupIntervalPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQCleanupIntervalPropertyValidator(int domain) {
/* 1143 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object valueP, Object parent) throws JMSException {
/* 1153 */       Object value = valueP;
/*      */ 
/*      */       
/* 1156 */       WMQConnectionFactory.checkNotQueueCF(parent, "setCleanupInterval()");
/*      */       
/* 1158 */       return validateLongForRange(value, 0L, Long.MAX_VALUE);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object longValue) throws JMSException {
/* 1167 */       if (Trace.isOn) {
/* 1168 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQCleanupIntervalPropertyValidator", "throwBadValueException(Object)", new Object[] { longValue });
/*      */       }
/*      */       
/* 1171 */       HashMap<String, String> info = new HashMap<>();
/* 1172 */       info.put("XMSC_INSERT_VALUE", String.valueOf(longValue));
/* 1173 */       info.put("XMSC_INSERT_NAME", "cleanupInterval");
/* 1174 */       JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/*      */       
/* 1176 */       if (Trace.isOn) {
/* 1177 */         Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQCleanupIntervalPropertyValidator", "throwBadValueException(Object)", (Throwable)je);
/*      */       }
/*      */ 
/*      */       
/* 1181 */       throw je;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean crossPropertyValidate(Object parent) throws JMSException {
/* 1189 */       if (Trace.isOn) {
/* 1190 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQCleanupIntervalPropertyValidator", "crossPropertyValidate(Object)", new Object[] { parent });
/*      */       }
/*      */       
/* 1193 */       if (((JmsPropertyContext)parent).getIntProperty("XMSC_WMQ_CLEANUP_INTERVAL") != 3600000 && (
/* 1194 */         (JmsPropertyContext)parent).getIntProperty("XMSC_WMQ_CLEANUP_LEVEL") == 0) {
/* 1195 */         if (Trace.isOn) {
/* 1196 */           Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQCleanupIntervalPropertyValidator", "crossPropertyValidate(Object)", 
/*      */               
/* 1198 */               Boolean.valueOf(false), 1);
/*      */         }
/* 1200 */         return false;
/*      */       } 
/*      */       
/* 1203 */       if (Trace.isOn) {
/* 1204 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQCleanupIntervalPropertyValidator", "crossPropertyValidate(Object)", 
/* 1205 */             Boolean.valueOf(true), 2);
/*      */       }
/* 1207 */       return true;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQCleanupLevelPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 6482851624338428343L;
/*      */     
/* 1218 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 1220 */       if (Trace.isOn) {
/* 1221 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQCleanupLevelPropertyValidator", "static()");
/*      */       }
/*      */       
/* 1224 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_CLEANUP_LEVEL");
/* 1225 */       domainsToPropertyNames.put(Integer.valueOf(1), "CLEANUP");
/* 1226 */       domainsToPropertyNames.put(Integer.valueOf(2), "CL");
/* 1227 */       if (Trace.isOn) {
/* 1228 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQCleanupLevelPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 1233 */       if (Trace.isOn) {
/* 1234 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQCleanupLevelPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 1237 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 1238 */         propertyValidators.put(entry.getValue(), new WMQCleanupLevelPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 1240 */       if (Trace.isOn) {
/* 1241 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQCleanupLevelPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1247 */     private static HashMap<Object, Object> valuesToCanonical = new HashMap<>(4, 1.0F);
/* 1248 */     private static Set<Object> validValues = new HashSet(6, 1.0F);
/*      */     static {
/* 1250 */       if (Trace.isOn) {
/* 1251 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQCleanupLevelPropertyValidator", "static()");
/*      */       }
/*      */       
/* 1254 */       valuesToCanonical.put("SAFE", Integer.valueOf(1));
/* 1255 */       valuesToCanonical.put("ASPROP", Integer.valueOf(-1));
/* 1256 */       valuesToCanonical.put("NONE", Integer.valueOf(0));
/* 1257 */       valuesToCanonical.put("STRONG", Integer.valueOf(2));
/* 1258 */       validValues.addAll(valuesToCanonical.values());
/*      */       
/* 1260 */       validValues.add(Integer.valueOf(4));
/* 1261 */       validValues.add(Integer.valueOf(3));
/* 1262 */       if (Trace.isOn) {
/* 1263 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQCleanupLevelPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQCleanupLevelPropertyValidator(int domain) {
/* 1272 */       super(domain, domainsToPropertyNames, valuesToCanonical);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object valueP, Object parent) throws JMSException {
/* 1279 */       Object value = valueP;
/*      */       
/* 1281 */       WMQConnectionFactory.checkNotQueueCF(parent, "setCleanupLevel()");
/*      */       
/* 1283 */       return validateIntBySet(value, validValues);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object level) throws JMSException {
/* 1288 */       if (Trace.isOn) {
/* 1289 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQCleanupLevelPropertyValidator", "throwBadValueException(Object)", new Object[] { level });
/*      */       }
/*      */       
/* 1292 */       HashMap<String, String> info = new HashMap<>();
/* 1293 */       info.put("XMSC_INSERT_VALUE", String.valueOf(level));
/* 1294 */       info.put("XMSC_INSERT_NAME", "cleanupLevel");
/* 1295 */       JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/*      */       
/* 1297 */       if (Trace.isOn) {
/* 1298 */         Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQCleanupLevelPropertyValidator", "throwBadValueException(Object)", (Throwable)je);
/*      */       }
/*      */       
/* 1301 */       throw je;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQRebalancingApplicationTypeValidator
/*      */     extends WMQStandardValidators.WMQIntPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 5354132738982432357L;
/*      */ 
/*      */     
/* 1313 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 1315 */       if (Trace.isOn) {
/* 1316 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQRebalancingApplicationTypeValidator", "static()");
/*      */       }
/*      */       
/* 1319 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_REBALANCING_APPLICATION_TYPE");
/* 1320 */       if (Trace.isOn) {
/* 1321 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQRebalancingApplicationTypeValidator", "static()");
/*      */       }
/*      */     }
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 1326 */       if (Trace.isOn) {
/* 1327 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQRebalancingApplicationTypeValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 1330 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 1331 */         propertyValidators.put(entry.getValue(), new WMQRebalancingApplicationTypeValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 1333 */       if (Trace.isOn) {
/* 1334 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQRebalancingApplicationTypeValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1340 */     private static HashMap<Object, Object> valuesToCanonical = new HashMap<>(10, 1.0F);
/* 1341 */     private static Set<Object> validValues = new HashSet(10, 1.0F);
/*      */     static {
/* 1343 */       if (Trace.isOn) {
/* 1344 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQRebalancingApplicationTypeValidator", "static()");
/*      */       }
/*      */ 
/*      */       
/* 1348 */       for (RebalancingApplicationType rebalancingApplicationType : RebalancingApplicationType.values()) {
/* 1349 */         valuesToCanonical.put(rebalancingApplicationType.getTag(), Integer.valueOf(rebalancingApplicationType.getPropertyValue()));
/* 1350 */         validValues.add(Integer.valueOf(rebalancingApplicationType.getPropertyValue()));
/*      */       } 
/*      */       
/* 1353 */       if (Trace.isOn) {
/* 1354 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQRebalancingApplicationTypeValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQRebalancingApplicationTypeValidator(int domain) {
/* 1363 */       super(domain, domainsToPropertyNames, valuesToCanonical);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/* 1369 */       if (((JmsPropertyContext)parent).getObjectProperty("XMSC_IS_JEE") == null) {
/* 1370 */         return false;
/*      */       }
/* 1372 */       return validateIntBySet(value, validValues);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object type) throws JMSException {
/* 1377 */       if (Trace.isOn) {
/* 1378 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQRebalancingApplicationTypeValidator", "throwBadValueException(Object)", new Object[] { type });
/*      */       }
/*      */       
/* 1381 */       HashMap<String, String> info = new HashMap<>();
/* 1382 */       info.put("XMSC_INSERT_VALUE", String.valueOf(type));
/* 1383 */       info.put("XMSC_INSERT_NAME", "rebalancingApplicationType");
/* 1384 */       JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/*      */       
/* 1386 */       if (Trace.isOn) {
/* 1387 */         Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQRebalancingApplicationTypeValidator", "throwBadValueException(Object)", (Throwable)je);
/*      */       }
/*      */       
/* 1390 */       throw je;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQRebalancingListenerPropertyValidator
/*      */     extends WMQStandardValidators.WMQTrivialPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 7341399549358104484L;
/*      */ 
/*      */     
/* 1402 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 1404 */       if (Trace.isOn) {
/* 1405 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQRebalancingListenerPropertyValidator", "static()");
/*      */       }
/*      */       
/* 1408 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_REBALANCING_LISTENER");
/* 1409 */       if (Trace.isOn) {
/* 1410 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQRebalancingListenerPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 1416 */       if (Trace.isOn) {
/* 1417 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQRebalancingListenerPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 1420 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 1421 */         propertyValidators.put(entry.getValue(), new WMQRebalancingListenerPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 1423 */       if (Trace.isOn) {
/* 1424 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQRebalancingListenerPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQRebalancingListenerPropertyValidator(int domain) {
/* 1435 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/* 1446 */       return (value == null || value instanceof com.ibm.mq.jmqi.RebalancingListener);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 1451 */       if (Trace.isOn) {
/* 1452 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQRebalancingListenerPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */       
/* 1456 */       HashMap<String, String> info = new HashMap<>();
/* 1457 */       info.put("XMSC_INSERT_VALUE", String.valueOf(value));
/* 1458 */       info.put("XMSC_INSERT_NAME", "rebalancingListener");
/* 1459 */       JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/*      */       
/* 1461 */       if (Trace.isOn) {
/* 1462 */         Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQRebalancingListenerPropertyValidator", "throwBadValueException(Object)", (Throwable)je);
/*      */       }
/*      */       
/* 1465 */       throw je;
/*      */     }
/*      */   }
/*      */   
/*      */   static class WMQQueueManagerOverrideQueueValidator
/*      */     extends WMQStandardValidators.WMQTrivialPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 7341399549358104484L;
/* 1473 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 1475 */       if (Trace.isOn) {
/* 1476 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQQueueManagerOverrideQueueValidator", "static()");
/*      */       }
/*      */       
/* 1479 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_QUEUE_MANAGER_OVERRIDE_QUEUE");
/* 1480 */       if (Trace.isOn) {
/* 1481 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQQueueManagerOverrideQueueValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 1487 */       if (Trace.isOn) {
/* 1488 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQQueueManagerOverrideQueueValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 1491 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 1492 */         propertyValidators.put(entry.getValue(), new WMQQueueManagerOverrideQueueValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 1494 */       if (Trace.isOn) {
/* 1495 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQQueueManagerOverrideQueueValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQQueueManagerOverrideQueueValidator(int domain) {
/* 1506 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/* 1517 */       return (value == null || value instanceof java.util.AbstractQueue);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 1522 */       if (Trace.isOn) {
/* 1523 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQQueueManagerOverrideQueueValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */       
/* 1527 */       HashMap<String, String> info = new HashMap<>();
/* 1528 */       info.put("XMSC_INSERT_VALUE", String.valueOf(value));
/* 1529 */       info.put("XMSC_INSERT_NAME", "queueManagerOverrideQueue");
/* 1530 */       JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/*      */       
/* 1532 */       if (Trace.isOn) {
/* 1533 */         Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQQueueManagerOverrideQueueValidator", "throwBadValueException(Object)", (Throwable)je);
/*      */       }
/*      */       
/* 1536 */       throw je;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQClientIDPropertyValidator
/*      */     extends WMQStandardValidators.WMQStringPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -8932880273620654596L;
/*      */     
/* 1547 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 1549 */       if (Trace.isOn) {
/* 1550 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQClientIDPropertyValidator", "static()");
/*      */       }
/* 1552 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_CLIENT_ID");
/* 1553 */       domainsToPropertyNames.put(Integer.valueOf(1), "CLIENTID");
/* 1554 */       domainsToPropertyNames.put(Integer.valueOf(2), "CID");
/* 1555 */       if (Trace.isOn) {
/* 1556 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQClientIDPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 1561 */       if (Trace.isOn) {
/* 1562 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQClientIDPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 1565 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 1566 */         propertyValidators.put(entry.getValue(), new WMQClientIDPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 1568 */       if (Trace.isOn) {
/* 1569 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQClientIDPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQClientIDPropertyValidator(int domain) {
/* 1580 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean crossPropertyValidate(Object parent) throws JMSException {
/* 1585 */       if (Trace.isOn) {
/* 1586 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQClientIDPropertyValidator", "crossPropertyValidate(Object)", new Object[] { parent });
/*      */       }
/*      */       
/* 1589 */       if (((JmsPropertyContext)parent).getObjectProperty("XMSC_CLIENT_ID") != null && (
/* 1590 */         (JmsPropertyContext)parent).getIntProperty("XMSC_WMQ_CONNECTION_MODE") != 1) {
/* 1591 */         if (Trace.isOn) {
/* 1592 */           Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQClientIDPropertyValidator", "crossPropertyValidate(Object)", 
/* 1593 */               Boolean.valueOf(false), 1);
/*      */         }
/* 1595 */         return false;
/*      */       } 
/*      */       
/* 1598 */       if (Trace.isOn) {
/* 1599 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQClientIDPropertyValidator", "crossPropertyValidate(Object)", 
/* 1600 */             Boolean.valueOf(true), 2);
/*      */       }
/* 1602 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 1607 */       if (Trace.isOn) {
/* 1608 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQClientIDPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1613 */       if (Trace.isOn) {
/* 1614 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQClientIDPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQCloneSupportPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -5026438323249820121L;
/*      */ 
/*      */     
/* 1628 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 1630 */       if (Trace.isOn) {
/* 1631 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQCloneSupportPropertyValidator", "static()");
/*      */       }
/*      */       
/* 1634 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_CLONE_SUPPORT");
/* 1635 */       domainsToPropertyNames.put(Integer.valueOf(1), "CLONESUPP");
/* 1636 */       domainsToPropertyNames.put(Integer.valueOf(2), "CLS");
/* 1637 */       if (Trace.isOn) {
/* 1638 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQCloneSupportPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 1643 */       if (Trace.isOn) {
/* 1644 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQCloneSupportPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 1647 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 1648 */         propertyValidators.put(entry.getValue(), new WMQCloneSupportPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 1650 */       if (Trace.isOn) {
/* 1651 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQCloneSupportPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1657 */     private static HashMap<Object, Object> valuesToCanonical = new HashMap<>(2, 1.0F);
/* 1658 */     private static Set<Object> validValues = new HashSet(2, 1.0F);
/*      */     static {
/* 1660 */       if (Trace.isOn) {
/* 1661 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQCloneSupportPropertyValidator", "static()");
/*      */       }
/*      */       
/* 1664 */       valuesToCanonical.put("DISABLED", Integer.valueOf(0));
/* 1665 */       valuesToCanonical.put("ENABLED", Integer.valueOf(1));
/* 1666 */       validValues.addAll(valuesToCanonical.values());
/* 1667 */       if (Trace.isOn) {
/* 1668 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQCloneSupportPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQCloneSupportPropertyValidator(int domain) {
/* 1677 */       super(domain, domainsToPropertyNames, valuesToCanonical);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/* 1683 */       WMQConnectionFactory.checkNotQueueCF(parent, "setCloneSupport()");
/* 1684 */       return validateIntBySet(value, validValues);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object type) throws JMSException {
/* 1689 */       if (Trace.isOn) {
/* 1690 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQCloneSupportPropertyValidator", "throwBadValueException(Object)", new Object[] { type });
/*      */       }
/*      */       
/* 1693 */       HashMap<String, String> info = new HashMap<>();
/* 1694 */       info.put("XMSC_INSERT_VALUE", String.valueOf(type));
/* 1695 */       info.put("XMSC_INSERT_NAME", "cloneSupport");
/* 1696 */       JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/* 1697 */       if (Trace.isOn) {
/* 1698 */         Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQCloneSupportPropertyValidator", "throwBadValueException(Object)", (Throwable)je);
/*      */       }
/*      */       
/* 1701 */       throw je;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQConnTagPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -593486815816249250L;
/*      */ 
/*      */     
/* 1713 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 1715 */       if (Trace.isOn) {
/* 1716 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQConnTagPropertyValidator", "static()");
/*      */       }
/* 1718 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_CONNECTION_TAG");
/* 1719 */       domainsToPropertyNames.put(Integer.valueOf(1), "CONNTAG");
/* 1720 */       domainsToPropertyNames.put(Integer.valueOf(2), "CNTAG");
/* 1721 */       if (Trace.isOn) {
/* 1722 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQConnTagPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 1727 */       if (Trace.isOn) {
/* 1728 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQConnTagPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 1731 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 1732 */         propertyValidators.put(entry.getValue(), new WMQConnTagPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 1734 */       if (Trace.isOn) {
/* 1735 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQConnTagPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQConnTagPropertyValidator(int domain) {
/* 1746 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/* 1752 */       if (value instanceof byte[]) {
/* 1753 */         return true;
/*      */       }
/* 1755 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQValidationInterface.WMQPropertyValidatorDatatype toCanonical(String keyIn, Object valueIn) {
/*      */       byte[] valueOut;
/* 1763 */       if (Trace.isOn) {
/* 1764 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQConnTagPropertyValidator", "toCanonical(String,Object)", new Object[] { keyIn, valueIn });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1769 */       String keyOut = domainsToPropertyNames.get(Integer.valueOf(4));
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1774 */       byte[] val = (byte[])valueIn;
/* 1775 */       int valLen = val.length;
/*      */ 
/*      */       
/* 1778 */       if (valLen == 128) {
/*      */         
/* 1780 */         valueOut = (byte[])valueIn;
/*      */       }
/* 1782 */       else if (valLen < 128) {
/*      */         
/* 1784 */         valueOut = new byte[128];
/* 1785 */         System.arraycopy(val, 0, valueOut, 0, valLen);
/* 1786 */         System.arraycopy(CMQC.MQCT_NONE, 0, valueOut, valLen, 128 - valLen);
/*      */       }
/*      */       else {
/*      */         
/* 1790 */         valueOut = new byte[128];
/* 1791 */         System.arraycopy(val, 0, valueOut, 0, 128);
/*      */       } 
/*      */       
/* 1794 */       WMQValidationInterface.WMQPropertyValidatorDatatype returnVal = new WMQValidationInterface.WMQPropertyValidatorDatatype(keyOut, valueOut);
/*      */       
/* 1796 */       if (Trace.isOn) {
/* 1797 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQConnTagPropertyValidator", "toCanonical(String,Object)", returnVal);
/*      */       }
/*      */       
/* 1800 */       return returnVal;
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 1805 */       if (Trace.isOn) {
/* 1806 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQConnTagPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1811 */       if (Trace.isOn) {
/* 1812 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQConnTagPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQCFDescriptionPropertyValidator
/*      */     extends WMQStandardValidators.WMQStringPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 6986879728345123980L;
/*      */ 
/*      */     
/* 1826 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 1828 */       if (Trace.isOn) {
/* 1829 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQCFDescriptionPropertyValidator", "static()");
/*      */       }
/*      */       
/* 1832 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_CF_DESCRIPTION");
/* 1833 */       domainsToPropertyNames.put(Integer.valueOf(1), "DESCRIPTION");
/* 1834 */       domainsToPropertyNames.put(Integer.valueOf(2), "DESC");
/* 1835 */       if (Trace.isOn) {
/* 1836 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQCFDescriptionPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 1842 */       if (Trace.isOn) {
/* 1843 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQCFDescriptionPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 1846 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 1847 */         propertyValidators.put(entry.getValue(), new WMQCFDescriptionPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 1849 */       if (Trace.isOn) {
/* 1850 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQCFDescriptionPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQCFDescriptionPropertyValidator(int domain) {
/* 1861 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 1866 */       if (Trace.isOn) {
/* 1867 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQCFDescriptionPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1872 */       if (Trace.isOn) {
/* 1873 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQCFDescriptionPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQFailIfQuiescePropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 3434462552598026495L;
/*      */ 
/*      */     
/* 1887 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 1889 */       if (Trace.isOn) {
/* 1890 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQFailIfQuiescePropertyValidator", "static()");
/*      */       }
/*      */       
/* 1893 */       domainsToPropertyNames.put(Integer.valueOf(4), "failIfQuiesce");
/* 1894 */       domainsToPropertyNames.put(Integer.valueOf(1), "FAILIFQUIESCE");
/* 1895 */       domainsToPropertyNames.put(Integer.valueOf(2), "FIQ");
/* 1896 */       if (Trace.isOn) {
/* 1897 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQFailIfQuiescePropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 1903 */       if (Trace.isOn) {
/* 1904 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQFailIfQuiescePropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 1907 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 1908 */         propertyValidators.put(entry.getValue(), new WMQFailIfQuiescePropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 1910 */       if (Trace.isOn) {
/* 1911 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQFailIfQuiescePropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1917 */     private static HashMap<Object, Object> valuesToCanonical = new HashMap<>(2, 1.0F);
/* 1918 */     private static Set<Object> validValues = new HashSet(2, 1.0F);
/*      */     static {
/* 1920 */       if (Trace.isOn) {
/* 1921 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQFailIfQuiescePropertyValidator", "static()");
/*      */       }
/*      */       
/* 1924 */       valuesToCanonical.put("YES", Integer.valueOf(1));
/* 1925 */       valuesToCanonical.put("NO", Integer.valueOf(0));
/* 1926 */       validValues.addAll(valuesToCanonical.values());
/* 1927 */       if (Trace.isOn) {
/* 1928 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQFailIfQuiescePropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQFailIfQuiescePropertyValidator(int domain) {
/* 1938 */       super(domain, domainsToPropertyNames, valuesToCanonical);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/* 1944 */       return validateIntBySet(value, validValues);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object fiq) throws JMSException {
/* 1949 */       if (Trace.isOn) {
/* 1950 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQFailIfQuiescePropertyValidator", "throwBadValueException(Object)", new Object[] { fiq });
/*      */       }
/*      */       
/* 1953 */       HashMap<String, String> info = new HashMap<>();
/* 1954 */       info.put("XMSC_INSERT_VALUE", String.valueOf(fiq));
/* 1955 */       info.put("XMSC_INSERT_NAME", "failIfQuiesce");
/* 1956 */       JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/*      */       
/* 1958 */       if (Trace.isOn) {
/* 1959 */         Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQFailIfQuiescePropertyValidator", "throwBadValueException(Object)", (Throwable)je);
/*      */       }
/*      */       
/* 1962 */       throw je;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQHeaderCompPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -7472872025334039252L;
/*      */     
/* 1973 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 1975 */       if (Trace.isOn) {
/* 1976 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQHeaderCompPropertyValidator", "static()");
/*      */       }
/* 1978 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_HEADER_COMP");
/* 1979 */       domainsToPropertyNames.put(Integer.valueOf(1), "COMPHDR");
/* 1980 */       domainsToPropertyNames.put(Integer.valueOf(2), "HC");
/* 1981 */       if (Trace.isOn) {
/* 1982 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQHeaderCompPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 1987 */       if (Trace.isOn) {
/* 1988 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQHeaderCompPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 1991 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 1992 */         propertyValidators.put(entry.getValue(), new WMQHeaderCompPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 1994 */       if (Trace.isOn) {
/* 1995 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQHeaderCompPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQHeaderCompPropertyValidator(int domain) {
/* 2006 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object<Object> valueP, Object parent) throws JMSException {
/* 2013 */       Object<Object> value = valueP;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2018 */       if (value instanceof String) {
/*      */         
/* 2020 */         StringTokenizer st = new StringTokenizer((String)value);
/* 2021 */         Vector<Object> c = new Vector();
/* 2022 */         while (st.hasMoreTokens()) {
/* 2023 */           String tk = st.nextToken();
/* 2024 */           if (tk.equals("NONE")) {
/* 2025 */             c.add(Integer.valueOf(0)); continue;
/*      */           } 
/* 2027 */           if (tk.equals("SYSTEM")) {
/* 2028 */             c.add(Integer.valueOf(8));
/*      */             
/*      */             continue;
/*      */           } 
/* 2032 */           c.add(tk);
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2038 */         value = (Object<Object>)c;
/*      */       } 
/*      */       
/* 2041 */       if (value instanceof Collection) {
/* 2042 */         if (((Collection)value).size() == 0) {
/* 2043 */           return true;
/*      */         }
/* 2045 */         Integer compressor = Integer.valueOf(0);
/* 2046 */         Iterator<?> it = ((Collection)value).iterator();
/* 2047 */         while (it.hasNext()) {
/*      */           try {
/* 2049 */             compressor = (Integer)it.next();
/*      */           }
/* 2051 */           catch (ClassCastException cce) {
/* 2052 */             if (Trace.isOn) {
/* 2053 */               Trace.traceData(this, "Compression technique of unacceptable type", null);
/*      */             }
/* 2055 */             HashMap<String, String> info = new HashMap<>();
/* 2056 */             info.put("XMSC_INSERT_VALUE", cce.getMessage());
/* 2057 */             info.put("XMSC_INSERT_NAME", "Compression technique of unacceptable type");
/* 2058 */             JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/* 2059 */             je.setLinkedException(cce);
/*      */             
/* 2061 */             throw je;
/*      */           } 
/* 2063 */           if (compressor == null) {
/* 2064 */             if (Trace.isOn) {
/* 2065 */               Trace.traceData(this, "null compressor", null);
/*      */             }
/* 2067 */             HashMap<String, String> info = new HashMap<>();
/* 2068 */             info.put("XMSC_INSERT_VALUE", "null");
/* 2069 */             info.put("XMSC_INSERT_NAME", "Compression technique");
/* 2070 */             JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/*      */             
/* 2072 */             throw je;
/*      */           } 
/* 2074 */           int comp = compressor.intValue();
/* 2075 */           if (comp != 0 && comp != 8) {
/* 2076 */             HashMap<String, String> info = new HashMap<>();
/* 2077 */             info.put("XMSC_INSERT_VALUE", compressor.toString());
/* 2078 */             info.put("XMSC_INSERT_NAME", "Compression technique");
/* 2079 */             JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/*      */             
/* 2081 */             throw je;
/*      */           } 
/*      */         } 
/*      */         
/* 2085 */         return true;
/*      */       } 
/* 2087 */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean crossPropertyValidate(Object parent) throws JMSException {
/* 2092 */       if (Trace.isOn) {
/* 2093 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQHeaderCompPropertyValidator", "crossPropertyValidate(Object)", new Object[] { parent });
/*      */       }
/*      */       
/* 2096 */       if (((JmsPropertyContext)parent).getObjectProperty("XMSC_WMQ_HEADER_COMP") != null && (
/* 2097 */         (JmsPropertyContext)parent).getIntProperty("XMSC_WMQ_CONNECTION_MODE") != 1) {
/* 2098 */         if (Trace.isOn) {
/* 2099 */           Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQHeaderCompPropertyValidator", "crossPropertyValidate(Object)", 
/* 2100 */               Boolean.valueOf(false), 1);
/*      */         }
/* 2102 */         return false;
/*      */       } 
/*      */       
/* 2105 */       if (Trace.isOn) {
/* 2106 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQHeaderCompPropertyValidator", "crossPropertyValidate(Object)", 
/* 2107 */             Boolean.valueOf(true), 2);
/*      */       }
/* 2109 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 2114 */       if (Trace.isOn) {
/* 2115 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQHeaderCompPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2120 */       if (Trace.isOn) {
/* 2121 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQHeaderCompPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   static class WMQHostnamePropertyValidator
/*      */     extends WMQStandardValidators.WMQStringPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -4078396467130249926L;
/*      */     
/*      */     public WMQValidationInterface.WMQPropertyValidatorDatatype fromCanonical(String keyIn, Object valueIn) {
/* 2132 */       if (Trace.isOn) {
/* 2133 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQHostnamePropertyValidator", "fromCanonical(String,Object)", new Object[] { keyIn, valueIn });
/*      */       }
/*      */       
/* 2136 */       WMQValidationInterface.WMQPropertyValidatorDatatype traceRet1 = super.fromCanonical(keyIn, valueIn);
/* 2137 */       if (Trace.isOn) {
/* 2138 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQHostnamePropertyValidator", "fromCanonical(String,Object)", traceRet1);
/*      */       }
/*      */       
/* 2141 */       return traceRet1;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2149 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 2151 */       if (Trace.isOn) {
/* 2152 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQHostnamePropertyValidator", "static()");
/*      */       }
/* 2154 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_HOST_NAME");
/* 2155 */       domainsToPropertyNames.put(Integer.valueOf(1), "HOSTNAME");
/* 2156 */       domainsToPropertyNames.put(Integer.valueOf(2), "HOST");
/* 2157 */       if (Trace.isOn) {
/* 2158 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQHostnamePropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 2163 */       if (Trace.isOn) {
/* 2164 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQHostnamePropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 2167 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 2168 */         propertyValidators.put(entry.getValue(), new WMQHostnamePropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 2170 */       if (Trace.isOn) {
/* 2171 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQHostnamePropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQHostnamePropertyValidator(int domain) {
/* 2182 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */     
/*      */     public WMQValidationInterface.WMQPropertyValidatorDatatype fromCanonical(JmsPropertyContext context, String keyIn, Object valueIn) {
/* 2187 */       if (Trace.isOn) {
/* 2188 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQHostnamePropertyValidator", "fromCanonical(JmsPropertyContext,String,Object)", new Object[] { context, keyIn, valueIn });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2193 */       String connectionNameList = "";
/*      */       try {
/* 2195 */         connectionNameList = context.getStringProperty("XMSC_WMQ_CONNECTION_NAME_LIST");
/*      */       }
/* 2197 */       catch (JMSException e) {
/* 2198 */         if (Trace.isOn) {
/* 2199 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.factories.WMQHostnamePropertyValidator", "fromCanonical(JmsPropertyContext,String,Object)", (Throwable)e);
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2206 */       int endHost = connectionNameList.indexOf('(');
/* 2207 */       String hostName = connectionNameList.substring(0, endHost);
/*      */       
/* 2209 */       WMQValidationInterface.WMQPropertyValidatorDatatype traceRet1 = super.fromCanonical(context, keyIn, hostName);
/* 2210 */       if (Trace.isOn) {
/* 2211 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQHostnamePropertyValidator", "fromCanonical(JmsPropertyContext,String,Object)", traceRet1);
/*      */       }
/*      */       
/* 2214 */       return traceRet1;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQValidationInterface.WMQPropertyValidatorDatatype toCanonical(JmsPropertyContext context, String keyIn, Object valueInP) {
/*      */       WMQConnectionNameList<WMQConnectionName> wMQConnectionNameList;
/* 2221 */       if (Trace.isOn) {
/* 2222 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQHostnamePropertyValidator", "toCanonical(JmsPropertyContext,String,Object)", new Object[] { context, keyIn, valueInP });
/*      */       }
/*      */ 
/*      */       
/* 2226 */       Object valueIn = valueInP;
/*      */       
/* 2228 */       if (valueIn instanceof String) {
/* 2229 */         valueIn = ((String)valueIn).trim();
/*      */       }
/*      */ 
/*      */       
/* 2233 */       ArrayList<WMQConnectionName> connectionNameList = null;
/*      */       
/*      */       try {
/* 2236 */         connectionNameList = (ArrayList<WMQConnectionName>)context.getObjectProperty("XMSC_WMQ_CONNECTION_NAME_LIST_INT");
/* 2237 */         if (connectionNameList == null) {
/* 2238 */           wMQConnectionNameList = new WMQConnectionNameList();
/* 2239 */           wMQConnectionNameList.add(new WMQConnectionName("localhost", 1414));
/*      */         } 
/* 2241 */         WMQConnectionName wcn = wMQConnectionNameList.get(0);
/* 2242 */         wcn.setHostname((String)valueIn);
/*      */       }
/* 2244 */       catch (JMSException e) {
/* 2245 */         if (Trace.isOn) {
/* 2246 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.factories.WMQHostnamePropertyValidator", "toCanonical(JmsPropertyContext,String,Object)", (Throwable)e);
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2252 */       WMQValidationInterface.WMQPropertyValidatorDatatype returnVal = new WMQValidationInterface.WMQPropertyValidatorDatatype("XMSC_WMQ_CONNECTION_NAME_LIST_INT", wMQConnectionNameList);
/*      */ 
/*      */       
/* 2255 */       if (Trace.isOn) {
/* 2256 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQHostnamePropertyValidator", "toCanonical(JmsPropertyContext,String,Object)", returnVal);
/*      */       }
/*      */       
/* 2259 */       return returnVal;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQValidationInterface.WMQPropertyValidatorDatatype toCanonical(String keyIn, Object valueInP) {
/* 2269 */       if (Trace.isOn) {
/* 2270 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQHostnamePropertyValidator", "toCanonical(String,Object)", new Object[] { keyIn, valueInP });
/*      */       }
/*      */ 
/*      */       
/* 2274 */       Object valueIn = valueInP;
/*      */ 
/*      */ 
/*      */       
/* 2278 */       if (valueIn instanceof String) {
/* 2279 */         valueIn = ((String)valueIn).trim();
/*      */       }
/* 2281 */       WMQValidationInterface.WMQPropertyValidatorDatatype returnVal = super.toCanonical(keyIn, valueIn);
/* 2282 */       if (Trace.isOn) {
/* 2283 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQHostnamePropertyValidator", "toCanonical(String keyIn, Object valueIn)");
/*      */       }
/* 2285 */       if (Trace.isOn) {
/* 2286 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQHostnamePropertyValidator", "toCanonical(String,Object)", returnVal);
/*      */       }
/*      */       
/* 2289 */       return returnVal;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean crossPropertyValidate(Object parent) throws JMSException {
/* 2294 */       if (Trace.isOn) {
/* 2295 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQHostnamePropertyValidator", "crossPropertyValidate(Object)", new Object[] { parent });
/*      */       }
/*      */       
/* 2298 */       if (!((JmsPropertyContext)parent).getStringProperty("XMSC_WMQ_HOST_NAME").equals("localhost") && (
/* 2299 */         (JmsPropertyContext)parent).getIntProperty("XMSC_WMQ_CONNECTION_MODE") == 0) {
/* 2300 */         if (Trace.isOn) {
/* 2301 */           Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQHostnamePropertyValidator", "crossPropertyValidate(Object)", 
/* 2302 */               Boolean.valueOf(false), 1);
/*      */         }
/* 2304 */         return false;
/*      */       } 
/*      */       
/* 2307 */       if (Trace.isOn) {
/* 2308 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQHostnamePropertyValidator", "crossPropertyValidate(Object)", 
/* 2309 */             Boolean.valueOf(true), 2);
/*      */       }
/* 2311 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 2316 */       if (Trace.isOn) {
/* 2317 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQHostnamePropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2322 */       if (Trace.isOn) {
/* 2323 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQHostnamePropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQLocalAddressPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 4607207777088043690L;
/*      */ 
/*      */     
/* 2337 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 2339 */       if (Trace.isOn) {
/* 2340 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQLocalAddressPropertyValidator", "static()");
/*      */       }
/*      */       
/* 2343 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_LOCAL_ADDRESS");
/* 2344 */       domainsToPropertyNames.put(Integer.valueOf(1), "LOCALADDRESS");
/* 2345 */       domainsToPropertyNames.put(Integer.valueOf(2), "LA");
/* 2346 */       if (Trace.isOn) {
/* 2347 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQLocalAddressPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 2352 */       if (Trace.isOn) {
/* 2353 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQLocalAddressPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 2356 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 2357 */         propertyValidators.put(entry.getValue(), new WMQLocalAddressPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 2359 */       if (Trace.isOn) {
/* 2360 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQLocalAddressPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQLocalAddressPropertyValidator(int domain) {
/* 2371 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object valueP, Object parent) throws JMSException {
/* 2378 */       Object value = valueP;
/* 2379 */       if (value == null) {
/* 2380 */         value = "";
/*      */       }
/*      */       
/* 2383 */       String address = convertToString(value);
/* 2384 */       address = address.trim();
/*      */ 
/*      */ 
/*      */       
/* 2388 */       if (address.length() > 48) {
/* 2389 */         return false;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2395 */       if (address.equals(""))
/*      */       {
/* 2397 */         return true;
/*      */       }
/*      */       
/* 2400 */       String lowPort = null;
/* 2401 */       String highPort = null;
/*      */       
/* 2403 */       int openBracketIndex = address.indexOf("(");
/* 2404 */       int closeBracketIndex = address.indexOf(")");
/* 2405 */       int commaIndex = address.indexOf(",");
/*      */ 
/*      */ 
/*      */       
/* 2409 */       if (openBracketIndex != -1 && closeBracketIndex == -1)
/*      */       {
/* 2411 */         return false;
/*      */       }
/* 2413 */       if (openBracketIndex == -1 && closeBracketIndex != -1)
/*      */       {
/* 2415 */         return false;
/*      */       }
/*      */ 
/*      */       
/* 2419 */       if (commaIndex != -1 && openBracketIndex == -1)
/*      */       {
/* 2421 */         return false;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2431 */       if (openBracketIndex == -1) {
/* 2432 */         return true;
/*      */       }
/*      */ 
/*      */       
/* 2436 */       if (commaIndex == -1) {
/*      */         
/* 2438 */         lowPort = address.substring(openBracketIndex + 1, closeBracketIndex);
/*      */       }
/*      */       else {
/*      */         
/* 2442 */         lowPort = address.substring(openBracketIndex + 1, commaIndex);
/* 2443 */         highPort = address.substring(commaIndex + 1, closeBracketIndex);
/*      */       } 
/*      */ 
/*      */       
/* 2447 */       int lowPortInt = 0;
/* 2448 */       int highPortInt = 0;
/*      */       
/* 2450 */       if (lowPort != null) {
/*      */         try {
/* 2452 */           lowPortInt = Integer.parseInt(lowPort);
/*      */         }
/* 2454 */         catch (NumberFormatException e) {
/*      */           
/* 2456 */           return false;
/*      */         } 
/*      */       }
/* 2459 */       if (highPort != null) {
/*      */         try {
/* 2461 */           highPortInt = Integer.parseInt(highPort);
/*      */         }
/* 2463 */         catch (NumberFormatException e) {
/*      */           
/* 2465 */           return false;
/*      */         } 
/*      */       }
/*      */ 
/*      */       
/* 2470 */       if (lowPortInt < 0 || highPortInt < 0)
/*      */       {
/* 2472 */         return false;
/*      */       }
/*      */       
/* 2475 */       if (lowPortInt > 65535 || highPortInt > 65535)
/*      */       {
/* 2477 */         return false;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2482 */       if (highPort != null && 
/* 2483 */         lowPortInt > highPortInt)
/*      */       {
/* 2485 */         return false;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2490 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean crossPropertyValidate(Object parent) throws JMSException {
/* 2495 */       if (Trace.isOn) {
/* 2496 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQLocalAddressPropertyValidator", "crossPropertyValidate(Object)", new Object[] { parent });
/*      */       }
/*      */       
/* 2499 */       if (!((JmsPropertyContext)parent).getStringProperty("XMSC_WMQ_LOCAL_ADDRESS").equals("") && (
/* 2500 */         (JmsPropertyContext)parent).getIntProperty("XMSC_WMQ_CONNECTION_MODE") == 0) {
/* 2501 */         if (Trace.isOn) {
/* 2502 */           Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQLocalAddressPropertyValidator", "crossPropertyValidate(Object)", 
/* 2503 */               Boolean.valueOf(false), 1);
/*      */         }
/* 2505 */         return false;
/*      */       } 
/*      */       
/* 2508 */       if (Trace.isOn) {
/* 2509 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQLocalAddressPropertyValidator", "crossPropertyValidate(Object)", 
/* 2510 */             Boolean.valueOf(true), 2);
/*      */       }
/* 2512 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 2517 */       if (Trace.isOn) {
/* 2518 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQLocalAddressPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2523 */       if (Trace.isOn) {
/* 2524 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQLocalAddressPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQMapmsgNameStylePropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 8800568865487888303L;
/*      */ 
/*      */     
/* 2538 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 2540 */       if (Trace.isOn) {
/* 2541 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQMapmsgNameStylePropertyValidator", "static()");
/*      */       }
/*      */       
/* 2544 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_MAP_NAME_STYLE");
/* 2545 */       domainsToPropertyNames.put(Integer.valueOf(1), "MAPNAMESTYLE");
/* 2546 */       domainsToPropertyNames.put(Integer.valueOf(2), "MNST");
/* 2547 */       if (Trace.isOn) {
/* 2548 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQMapmsgNameStylePropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 2554 */       if (Trace.isOn) {
/* 2555 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQMapmsgNameStylePropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 2558 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 2559 */         propertyValidators.put(entry.getValue(), new WMQMapmsgNameStylePropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 2561 */       if (Trace.isOn) {
/* 2562 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQMapmsgNameStylePropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2568 */     private static HashMap<Object, Object> valuesToCanonical = new HashMap<>(4, 1.0F);
/*      */     static {
/* 2570 */       if (Trace.isOn) {
/* 2571 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQMapmsgNameStylePropertyValidator", "static()");
/*      */       }
/*      */       
/* 2574 */       valuesToCanonical.put("STANDARD", Boolean.TRUE);
/* 2575 */       valuesToCanonical.put("COMPATIBLE", Boolean.FALSE);
/* 2576 */       valuesToCanonical.put("TRUE", Boolean.TRUE);
/* 2577 */       valuesToCanonical.put("FALSE", Boolean.FALSE);
/* 2578 */       if (Trace.isOn) {
/* 2579 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQMapmsgNameStylePropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQMapmsgNameStylePropertyValidator(int domain) {
/* 2589 */       super(domain, domainsToPropertyNames, valuesToCanonical);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/* 2596 */       if (value instanceof Boolean) {
/* 2597 */         return true;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2603 */       String stringValue = convertToString(value);
/* 2604 */       if (stringValue == null) {
/* 2605 */         return false;
/*      */       }
/*      */       
/* 2608 */       return valuesToCanonical.containsKey(stringValue.toUpperCase());
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 2613 */       if (Trace.isOn) {
/* 2614 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQMapmsgNameStylePropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2619 */       if (Trace.isOn) {
/* 2620 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQMapmsgNameStylePropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQMaxBufferSizePropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -491142338544404051L;
/*      */ 
/*      */     
/* 2634 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 2636 */       if (Trace.isOn) {
/* 2637 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQMaxBufferSizePropertyValidator", "static()");
/*      */       }
/*      */       
/* 2640 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_MAX_BUFFER_SIZE");
/* 2641 */       domainsToPropertyNames.put(Integer.valueOf(1), "MAXBUFFSIZE");
/* 2642 */       domainsToPropertyNames.put(Integer.valueOf(2), "MBSZ");
/* 2643 */       if (Trace.isOn) {
/* 2644 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQMaxBufferSizePropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 2650 */       if (Trace.isOn) {
/* 2651 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQMaxBufferSizePropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 2654 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 2655 */         propertyValidators.put(entry.getValue(), new WMQMaxBufferSizePropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 2657 */       if (Trace.isOn) {
/* 2658 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQMaxBufferSizePropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQMaxBufferSizePropertyValidator(int domain) {
/* 2669 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object valueP, Object parent) throws JMSException {
/* 2676 */       Object value = valueP;
/*      */ 
/*      */       
/* 2679 */       WMQConnectionFactory.checkNotQueueCF(parent, "setMaxBufferSize()");
/*      */       
/* 2681 */       return validateIntForRange(value, 0, 2147483647);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object size) throws JMSException {
/* 2686 */       if (Trace.isOn) {
/* 2687 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQMaxBufferSizePropertyValidator", "throwBadValueException(Object)", new Object[] { size });
/*      */       }
/*      */       
/* 2690 */       HashMap<String, String> info = new HashMap<>();
/* 2691 */       info.put("XMSC_INSERT_VALUE", String.valueOf(size));
/* 2692 */       info.put("XMSC_INSERT_NAME", "maxBufferSize");
/* 2693 */       JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/*      */       
/* 2695 */       if (Trace.isOn) {
/* 2696 */         Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQMaxBufferSizePropertyValidator", "throwBadValueException(Object)", (Throwable)je);
/*      */       }
/*      */       
/* 2699 */       throw je;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQMessageRetentionPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 6760842274009487000L;
/*      */     
/* 2710 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 2712 */       if (Trace.isOn) {
/* 2713 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQMessageRetentionPropertyValidator", "static()");
/*      */       }
/*      */       
/* 2716 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_MESSAGE_RETENTION");
/* 2717 */       domainsToPropertyNames.put(Integer.valueOf(1), "MSGRETENTION");
/* 2718 */       domainsToPropertyNames.put(Integer.valueOf(2), "MRET");
/* 2719 */       if (Trace.isOn) {
/* 2720 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQMessageRetentionPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 2726 */       if (Trace.isOn) {
/* 2727 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQMessageRetentionPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 2730 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 2731 */         propertyValidators.put(entry.getValue(), new WMQMessageRetentionPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 2733 */       if (Trace.isOn) {
/* 2734 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQMessageRetentionPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2740 */     private static HashMap<Object, Object> valuesToCanonical = new HashMap<>(2, 1.0F);
/* 2741 */     private static Set<Object> validValues = new HashSet(2, 1.0F);
/*      */     static {
/* 2743 */       if (Trace.isOn) {
/* 2744 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQMessageRetentionPropertyValidator", "static()");
/*      */       }
/*      */       
/* 2747 */       valuesToCanonical.put("YES", Integer.valueOf(1));
/* 2748 */       valuesToCanonical.put("NO", Integer.valueOf(0));
/* 2749 */       validValues.addAll(valuesToCanonical.values());
/* 2750 */       if (Trace.isOn) {
/* 2751 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQMessageRetentionPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQMessageRetentionPropertyValidator(int domain) {
/* 2761 */       super(domain, domainsToPropertyNames, valuesToCanonical);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/* 2767 */       WMQConnectionFactory.checkNotTopicCF(parent, "WMQMessageRetentionPropertyValidator");
/*      */       
/* 2769 */       return validateIntBySet(value, validValues);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object mRet) throws JMSException {
/* 2774 */       if (Trace.isOn) {
/* 2775 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQMessageRetentionPropertyValidator", "throwBadValueException(Object)", new Object[] { mRet });
/*      */       }
/*      */       
/* 2778 */       HashMap<String, String> info = new HashMap<>();
/* 2779 */       info.put("XMSC_INSERT_VALUE", String.valueOf(mRet));
/* 2780 */       info.put("XMSC_INSERT_NAME", "messageRetention");
/* 2781 */       JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/* 2782 */       if (Trace.isOn) {
/* 2783 */         Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQMessageRetentionPropertyValidator", "throwBadValueException(Object)", (Throwable)je);
/*      */       }
/*      */ 
/*      */       
/* 2787 */       throw je;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQMessageSelectionPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -3081005187089113998L;
/*      */     
/* 2798 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 2800 */       if (Trace.isOn) {
/* 2801 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQMessageSelectionPropertyValidator", "static()");
/*      */       }
/*      */       
/* 2804 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_MESSAGE_SELECTION");
/* 2805 */       domainsToPropertyNames.put(Integer.valueOf(1), "MSGSELECTION");
/* 2806 */       domainsToPropertyNames.put(Integer.valueOf(2), "MSEL");
/* 2807 */       if (Trace.isOn) {
/* 2808 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQMessageSelectionPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 2814 */       if (Trace.isOn) {
/* 2815 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQMessageSelectionPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 2818 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 2819 */         propertyValidators.put(entry.getValue(), new WMQMessageSelectionPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 2821 */       if (Trace.isOn) {
/* 2822 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQMessageSelectionPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2828 */     private static HashMap<Object, Object> valuesToCanonical = new HashMap<>(2, 1.0F);
/* 2829 */     private static Set<Object> validValues = new HashSet(2, 1.0F);
/*      */     static {
/* 2831 */       if (Trace.isOn) {
/* 2832 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQMessageSelectionPropertyValidator", "static()");
/*      */       }
/*      */       
/* 2835 */       valuesToCanonical.put("CLIENT", Integer.valueOf(0));
/* 2836 */       valuesToCanonical.put("BROKER", Integer.valueOf(1));
/* 2837 */       validValues.addAll(valuesToCanonical.values());
/* 2838 */       if (Trace.isOn) {
/* 2839 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQMessageSelectionPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQMessageSelectionPropertyValidator(int domain) {
/* 2849 */       super(domain, domainsToPropertyNames, valuesToCanonical);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object valueP, Object parent) throws JMSException {
/* 2856 */       Object value = valueP;
/*      */       
/* 2858 */       WMQConnectionFactory.checkNotQueueCF(parent, "setMessageSelection()");
/*      */       
/* 2860 */       if (validateIntBySet(value, validValues)) {
/* 2861 */         updateMessageSelectionFlag(parent);
/* 2862 */         return true;
/*      */       } 
/* 2864 */       return false;
/*      */     }
/*      */     
/*      */     private void updateMessageSelectionFlag(final Object parent) {
/* 2868 */       if (Trace.isOn) {
/* 2869 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQMessageSelectionPropertyValidator", "updateMessageSelectionFlag(final Object)", new Object[] { parent });
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2878 */       if (parent instanceof MQConnectionFactory) {
/* 2879 */         AccessController.doPrivileged(new PrivilegedAction()
/*      */             {
/*      */               public Object run()
/*      */               {
/* 2883 */                 if (Trace.isOn) {
/* 2884 */                   Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQMessageSelectionPropertyValidator", "run()");
/*      */                 }
/*      */                 
/*      */                 try {
/* 2888 */                   Field mselSetField = MQConnectionFactory.class.getDeclaredField("mselSet");
/* 2889 */                   mselSetField.setAccessible(true);
/* 2890 */                   mselSetField.setBoolean(parent, true);
/*      */                 }
/* 2892 */                 catch (NoSuchFieldException nsfe) {
/* 2893 */                   if (Trace.isOn) {
/* 2894 */                     Trace.catchBlock(this, "com.ibm.msg.client.wmq.factories.null", "run()", nsfe, 1);
/*      */                   }
/*      */                 }
/* 2897 */                 catch (IllegalAccessException iae) {
/* 2898 */                   if (Trace.isOn) {
/* 2899 */                     Trace.catchBlock(this, "com.ibm.msg.client.wmq.factories.null", "run()", iae, 2);
/*      */                   }
/*      */                 } finally {
/*      */                   
/* 2903 */                   if (Trace.isOn) {
/* 2904 */                     Trace.finallyBlock(this, "com.ibm.msg.client.wmq.factories.null", "run()");
/*      */                   }
/*      */                 } 
/*      */                 
/* 2908 */                 if (Trace.isOn) {
/* 2909 */                   Trace.exit(this, "com.ibm.msg.client.wmq.factories.null", "run()", null);
/*      */                 }
/* 2911 */                 return null;
/*      */               }
/*      */             });
/*      */       }
/*      */       
/* 2916 */       if (Trace.isOn) {
/* 2917 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQMessageSelectionPropertyValidator", "updateMessageSelectionFlag(final Object)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object selection) throws JMSException {
/* 2925 */       if (Trace.isOn) {
/* 2926 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQMessageSelectionPropertyValidator", "throwBadValueException(Object)", new Object[] { selection });
/*      */       }
/*      */       
/* 2929 */       HashMap<String, String> info = new HashMap<>();
/* 2930 */       info.put("XMSC_INSERT_VALUE", String.valueOf(selection));
/* 2931 */       info.put("XMSC_INSERT_NAME", "messageSelection");
/* 2932 */       JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/*      */       
/* 2934 */       if (Trace.isOn) {
/* 2935 */         Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQMessageSelectionPropertyValidator", "throwBadValueException(Object)", (Throwable)je);
/*      */       }
/*      */ 
/*      */       
/* 2939 */       throw je;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQConnectionOptionsPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 397751746931416967L;
/*      */     
/* 2950 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 2952 */       if (Trace.isOn) {
/* 2953 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQConnectionOptionsPropertyValidator", "static()");
/*      */       }
/*      */       
/* 2956 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_CONNECT_OPTIONS");
/* 2957 */       domainsToPropertyNames.put(Integer.valueOf(1), "CONNOPT");
/* 2958 */       domainsToPropertyNames.put(Integer.valueOf(2), "CNOPT");
/* 2959 */       if (Trace.isOn) {
/* 2960 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQConnectionOptionsPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 2966 */       if (Trace.isOn) {
/* 2967 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQConnectionOptionsPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 2970 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 2971 */         propertyValidators.put(entry.getValue(), new WMQConnectionOptionsPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 2973 */       if (Trace.isOn) {
/* 2974 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQConnectionOptionsPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2980 */     private static HashMap<Object, Object> valuesToCanonical = new HashMap<>(8, 1.0F);
/*      */     static {
/* 2982 */       if (Trace.isOn) {
/* 2983 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQConnectionOptionsPropertyValidator", "static()");
/*      */       }
/*      */       
/* 2986 */       valuesToCanonical.put("STANDARD", Integer.valueOf(0));
/* 2987 */       valuesToCanonical.put("SHARED", Integer.valueOf(256));
/* 2988 */       valuesToCanonical.put("ISOLATED", Integer.valueOf(512));
/* 2989 */       valuesToCanonical.put("FASTPATH", Integer.valueOf(1));
/*      */ 
/*      */       
/* 2992 */       valuesToCanonical.put("SERIALQM", Integer.valueOf(2));
/*      */ 
/*      */ 
/*      */       
/* 2996 */       valuesToCanonical.put("SERIALQSG", Integer.valueOf(4));
/*      */       
/* 2998 */       valuesToCanonical.put("RESTRICTQM", Integer.valueOf(8));
/* 2999 */       valuesToCanonical.put("RESTRICTQSG", Integer.valueOf(16));
/* 3000 */       if (Trace.isOn) {
/* 3001 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQConnectionOptionsPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQConnectionOptionsPropertyValidator(int domain) {
/* 3011 */       super(domain, domainsToPropertyNames, valuesToCanonical);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object valueP, Object parent) throws JMSException {
/* 3017 */       Object value = valueP;
/* 3018 */       if (!(value instanceof Integer)) {
/* 3019 */         value = convertToString(value);
/*      */       }
/*      */       
/* 3022 */       if (value instanceof Integer || value instanceof String) {
/*      */         int cTagOpt;
/* 3024 */         if (value instanceof Integer) {
/* 3025 */           cTagOpt = ((Integer)value).intValue();
/*      */         
/*      */         }
/* 3028 */         else if (this.mapperDomain == 1 || this.mapperDomain == 2) {
/* 3029 */           if (valuesToCanonical.containsKey(value)) {
/* 3030 */             cTagOpt = ((Integer)valuesToCanonical.get(value)).intValue();
/*      */           } else {
/*      */             
/* 3033 */             return false;
/*      */           } 
/*      */         } else {
/*      */           
/*      */           try {
/* 3038 */             cTagOpt = Integer.parseInt((String)value);
/*      */           }
/* 3040 */           catch (NumberFormatException e) {
/* 3041 */             return false;
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/* 3046 */         int count = 0;
/* 3047 */         if ((cTagOpt & 0x8) != 0) {
/* 3048 */           count++;
/*      */         }
/* 3050 */         if ((cTagOpt & 0x10) != 0) {
/* 3051 */           count++;
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 3056 */         if ((cTagOpt & 0x2) != 0) {
/* 3057 */           count++;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3063 */         if ((cTagOpt & 0x4) != 0) {
/* 3064 */           count++;
/*      */         }
/* 3066 */         if (count > 1) {
/*      */           
/* 3068 */           HashMap<String, Object> info = new HashMap<>();
/* 3069 */           info.put("XMSC_INSERT_VALUE", String.valueOf(cTagOpt));
/* 3070 */           info.put("XMSC_INSERT_NAME", "ProviderConnection options");
/* 3071 */           JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/*      */           
/* 3073 */           throw je;
/*      */         } 
/* 3075 */         count = 0;
/* 3076 */         if ((cTagOpt & 0x1) != 0) {
/* 3077 */           count++;
/*      */         }
/* 3079 */         if ((cTagOpt & 0x200) != 0) {
/* 3080 */           count++;
/*      */         }
/* 3082 */         if ((cTagOpt & 0x100) != 0) {
/* 3083 */           count++;
/*      */         }
/* 3085 */         if (count > 1) {
/*      */           
/* 3087 */           HashMap<String, Object> info = new HashMap<>();
/* 3088 */           info.put("XMSC_INSERT_VALUE", String.valueOf(cTagOpt));
/* 3089 */           info.put("XMSC_INSERT_NAME", "ProviderConnection options");
/* 3090 */           JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/*      */           
/* 3092 */           throw je;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3098 */         if (((MQConnectionFactory)parent).getTransportType() == 1 && ((cTagOpt & 0x8) != 0 || (cTagOpt & 0x10) != 0)) {
/*      */ 
/*      */           
/* 3101 */           HashMap<String, Object> info = new HashMap<>();
/* 3102 */           info.put("XMSC_INSERT_VALUE", String.valueOf(cTagOpt));
/* 3103 */           info.put("XMSC_INSERT_NAME", "XMSC_WMQ_CONNECT_OPTIONS");
/* 3104 */           JMSException je = (JMSException)NLSServices.createException("JMSFMQ1008", info);
/*      */           
/* 3106 */           throw je;
/*      */         } 
/*      */         
/* 3109 */         count = cTagOpt;
/* 3110 */         count &= 0xFFFFFCE0;
/*      */ 
/*      */ 
/*      */         
/* 3114 */         if (count > 0) {
/* 3115 */           HashMap<String, Object> info = new HashMap<>();
/* 3116 */           info.put("XMSC_INSERT_VALUE", String.valueOf(cTagOpt));
/* 3117 */           info.put("XMSC_INSERT_NAME", "ProviderConnection options");
/* 3118 */           JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/*      */           
/* 3120 */           throw je;
/*      */         } 
/* 3122 */         return true;
/*      */       } 
/*      */       
/* 3125 */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 3130 */       if (Trace.isOn) {
/* 3131 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQConnectionOptionsPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3137 */       if (Trace.isOn) {
/* 3138 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQConnectionOptionsPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQMsgBatchSizePropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 3698963451138408718L;
/*      */ 
/*      */     
/* 3152 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 3154 */       if (Trace.isOn) {
/* 3155 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQMsgBatchSizePropertyValidator", "static()");
/*      */       }
/*      */       
/* 3158 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_MSG_BATCH_SIZE");
/* 3159 */       domainsToPropertyNames.put(Integer.valueOf(1), "MSGBATCHSZ");
/* 3160 */       domainsToPropertyNames.put(Integer.valueOf(2), "MBS");
/* 3161 */       if (Trace.isOn) {
/* 3162 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQMsgBatchSizePropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 3167 */       if (Trace.isOn) {
/* 3168 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQMsgBatchSizePropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 3171 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 3172 */         propertyValidators.put(entry.getValue(), new WMQMsgBatchSizePropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 3174 */       if (Trace.isOn) {
/* 3175 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQMsgBatchSizePropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQMsgBatchSizePropertyValidator(int domain) {
/* 3186 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/* 3192 */       return validateIntForRange(value, 0, 2147483647);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object size) throws JMSException {
/* 3197 */       if (Trace.isOn) {
/* 3198 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQMsgBatchSizePropertyValidator", "throwBadValueException(Object)", new Object[] { size });
/*      */       }
/*      */       
/* 3201 */       HashMap<String, Object> info = new HashMap<>();
/* 3202 */       info.put("XMSC_INSERT_VALUE", String.valueOf(size));
/* 3203 */       info.put("XMSC_INSERT_NAME", "MsgBatchSize");
/* 3204 */       JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/*      */       
/* 3206 */       if (Trace.isOn) {
/* 3207 */         Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQMsgBatchSizePropertyValidator", "throwBadValueException(Object)", (Throwable)je);
/*      */       }
/*      */       
/* 3210 */       throw je;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQMsgCompPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 2828456160225827315L;
/*      */ 
/*      */     
/* 3222 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 3224 */       if (Trace.isOn) {
/* 3225 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQMsgCompPropertyValidator", "static()");
/*      */       }
/* 3227 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_MSG_COMP");
/* 3228 */       domainsToPropertyNames.put(Integer.valueOf(1), "COMPMSG");
/* 3229 */       domainsToPropertyNames.put(Integer.valueOf(2), "MC");
/* 3230 */       if (Trace.isOn) {
/* 3231 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQMsgCompPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 3236 */       if (Trace.isOn) {
/* 3237 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQMsgCompPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 3240 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 3241 */         propertyValidators.put(entry.getValue(), new WMQMsgCompPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 3243 */       if (Trace.isOn) {
/* 3244 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQMsgCompPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQMsgCompPropertyValidator(int domain) {
/* 3255 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object<Integer> valueP, Object parent) throws JMSException {
/* 3262 */       Object<Integer> value = valueP;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3267 */       if (value instanceof String) {
/*      */         
/* 3269 */         StringTokenizer st = new StringTokenizer((String)value);
/* 3270 */         Vector<Integer> c = new Vector<>();
/* 3271 */         while (st.hasMoreTokens()) {
/* 3272 */           String tk = st.nextToken();
/* 3273 */           if (tk.equals("NONE")) {
/* 3274 */             c.add(Integer.valueOf(0));
/*      */           }
/* 3276 */           else if (tk.equals("RLE")) {
/* 3277 */             c.add(Integer.valueOf(1));
/*      */           }
/* 3279 */           else if (tk.equals("ZLIBFAST")) {
/* 3280 */             c.add(Integer.valueOf(2));
/*      */           }
/* 3282 */           else if (tk.equals("ZLIBHIGH")) {
/* 3283 */             c.add(Integer.valueOf(4));
/*      */           } else {
/*      */             
/* 3286 */             if (Trace.isOn) {
/* 3287 */               Trace.traceData(this, "Compressor value is not supported", null);
/*      */             }
/* 3289 */             HashMap<String, Object> info = new HashMap<>();
/* 3290 */             info.put("XMSC_INSERT_VALUE", String.valueOf(tk));
/* 3291 */             info.put("XMSC_INSERT_NAME", "Compressor");
/* 3292 */             JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/*      */             
/* 3294 */             throw je;
/*      */           } 
/*      */           
/* 3297 */           value = (Object<Integer>)c;
/*      */         } 
/*      */       } 
/*      */       
/* 3301 */       if (value instanceof Collection) {
/* 3302 */         Collection<Object> compList = (Collection)value;
/* 3303 */         if (compList.size() == 0) {
/* 3304 */           return true;
/*      */         }
/* 3306 */         Integer compressor = Integer.valueOf(0);
/* 3307 */         Iterator<Object> it = compList.iterator();
/* 3308 */         while (it.hasNext()) {
/*      */           try {
/* 3310 */             compressor = (Integer)it.next();
/*      */           }
/* 3312 */           catch (ClassCastException cce) {
/* 3313 */             if (Trace.isOn) {
/* 3314 */               Trace.traceData(this, "Compressor of unacceptable type", null);
/*      */             }
/* 3316 */             HashMap<String, Object> info = new HashMap<>();
/* 3317 */             info.put("XMSC_INSERT_VALUE", cce.getMessage());
/* 3318 */             info.put("XMSC_INSERT_NAME", "Compressor value");
/* 3319 */             JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/* 3320 */             je.setLinkedException(cce);
/*      */             
/* 3322 */             throw je;
/*      */           } 
/* 3324 */           if (compressor == null) {
/* 3325 */             if (Trace.isOn) {
/* 3326 */               Trace.traceData(this, "null compressor", null);
/*      */             }
/* 3328 */             HashMap<String, Object> info = new HashMap<>();
/* 3329 */             info.put("XMSC_INSERT_VALUE", "null");
/* 3330 */             info.put("XMSC_INSERT_NAME", "Compressor");
/* 3331 */             JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/*      */             
/* 3333 */             throw je;
/*      */           } 
/* 3335 */           int comp = compressor.intValue();
/* 3336 */           if (comp != 0 && comp != 1 && comp != 2 && comp != 4) {
/*      */             
/* 3338 */             HashMap<String, Object> info = new HashMap<>();
/* 3339 */             info.put("XMSC_INSERT_VALUE", compressor.toString());
/* 3340 */             info.put("XMSC_INSERT_NAME", "Compressor");
/* 3341 */             JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/*      */             
/* 3343 */             throw je;
/*      */           } 
/*      */         } 
/*      */         
/* 3347 */         return true;
/*      */       } 
/* 3349 */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 3354 */       if (Trace.isOn) {
/* 3355 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQMsgCompPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 3360 */       if (Trace.isOn) {
/* 3361 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQMsgCompPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQPollingIntervalPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -1218143777857680293L;
/*      */ 
/*      */     
/* 3375 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 3377 */       if (Trace.isOn) {
/* 3378 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQPollingIntervalPropertyValidator", "static()");
/*      */       }
/*      */       
/* 3381 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_POLLING_INTERVAL");
/* 3382 */       domainsToPropertyNames.put(Integer.valueOf(1), "POLLINGINT");
/* 3383 */       domainsToPropertyNames.put(Integer.valueOf(2), "PINT");
/* 3384 */       if (Trace.isOn) {
/* 3385 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQPollingIntervalPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 3391 */       if (Trace.isOn) {
/* 3392 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQPollingIntervalPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 3395 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 3396 */         propertyValidators.put(entry.getValue(), new WMQPollingIntervalPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 3398 */       if (Trace.isOn) {
/* 3399 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQPollingIntervalPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQPollingIntervalPropertyValidator(int domain) {
/* 3410 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/* 3415 */       if (Trace.isOn) {
/* 3416 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQPollingIntervalPropertyValidator", "validate(Object,Object)", new Object[] { value, parent });
/*      */       }
/*      */       
/* 3419 */       boolean traceRet1 = validateIntForRange(value, 0, 2147483647);
/* 3420 */       if (Trace.isOn) {
/* 3421 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQPollingIntervalPropertyValidator", "validate(Object,Object)", 
/* 3422 */             Boolean.valueOf(traceRet1));
/*      */       }
/* 3424 */       return traceRet1;
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object interval) throws JMSException {
/* 3429 */       if (Trace.isOn) {
/* 3430 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQPollingIntervalPropertyValidator", "throwBadValueException(Object)", new Object[] { interval });
/*      */       }
/*      */       
/* 3433 */       HashMap<String, Object> info = new HashMap<>();
/* 3434 */       info.put("XMSC_INSERT_VALUE", String.valueOf(interval));
/* 3435 */       info.put("XMSC_INSERT_NAME", "pollingInterval");
/* 3436 */       JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/*      */       
/* 3438 */       if (Trace.isOn) {
/* 3439 */         Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQPollingIntervalPropertyValidator", "throwBadValueException(Object)", (Throwable)je);
/*      */       }
/*      */ 
/*      */       
/* 3443 */       throw je;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQOptPubPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 7690158133313647691L;
/*      */     
/* 3454 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 3456 */       if (Trace.isOn) {
/* 3457 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQOptPubPropertyValidator", "static()");
/*      */       }
/* 3459 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_OPT_PUB");
/* 3460 */       domainsToPropertyNames.put(Integer.valueOf(1), "OPTIMISTICPUBLICATION");
/* 3461 */       domainsToPropertyNames.put(Integer.valueOf(2), "OPTPUB");
/* 3462 */       if (Trace.isOn) {
/* 3463 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQOptPubPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 3468 */       if (Trace.isOn) {
/* 3469 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQOptPubPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 3472 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 3473 */         propertyValidators.put(entry.getValue(), new WMQOptPubPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 3475 */       if (Trace.isOn) {
/* 3476 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQOptPubPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQOptPubPropertyValidator(int domain) {
/* 3487 */       super(domain, domainsToPropertyNames, yesNoToBoolean);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/* 3494 */       return validateBooleanTFYN(value);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 3499 */       if (Trace.isOn) {
/* 3500 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQOptPubPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 3505 */       if (Trace.isOn) {
/* 3506 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQOptPubPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQOutNotifyPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 6973164587041708965L;
/*      */ 
/*      */     
/* 3520 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 3522 */       if (Trace.isOn) {
/* 3523 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQOutNotifyPropertyValidator", "static()");
/*      */       }
/* 3525 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_OUTCOME_NOTIFICATION");
/* 3526 */       domainsToPropertyNames.put(Integer.valueOf(1), "OUTCOMENOTIFICATION");
/* 3527 */       domainsToPropertyNames.put(Integer.valueOf(2), "NOTIFY");
/* 3528 */       if (Trace.isOn) {
/* 3529 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQOutNotifyPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 3534 */       if (Trace.isOn) {
/* 3535 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQOutNotifyPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 3538 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 3539 */         propertyValidators.put(entry.getValue(), new WMQOutNotifyPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 3541 */       if (Trace.isOn) {
/* 3542 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQOutNotifyPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQOutNotifyPropertyValidator(int domain) {
/* 3553 */       super(domain, domainsToPropertyNames, yesNoToBoolean);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/* 3560 */       return validateBooleanTFYN(value);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 3565 */       if (Trace.isOn) {
/* 3566 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQOutNotifyPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 3571 */       if (Trace.isOn) {
/* 3572 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQOutNotifyPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   static class WMQPortPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 2960491545923009779L;
/*      */     
/*      */     public WMQValidationInterface.WMQPropertyValidatorDatatype fromCanonical(JmsPropertyContext context, String keyIn, Object valueIn) {
/* 3583 */       if (Trace.isOn) {
/* 3584 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQPortPropertyValidator", "fromCanonical(JmsPropertyContext,String,Object)", new Object[] { context, keyIn, valueIn });
/*      */       }
/*      */ 
/*      */       
/* 3588 */       String connectionNameList = "";
/*      */       try {
/* 3590 */         connectionNameList = context.getStringProperty("XMSC_WMQ_CONNECTION_NAME_LIST");
/*      */       }
/* 3592 */       catch (JMSException e) {
/* 3593 */         if (Trace.isOn) {
/* 3594 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.factories.WMQPortPropertyValidator", "fromCanonical(JmsPropertyContext,String,Object)", (Throwable)e);
/*      */         }
/*      */         
/* 3597 */         if (Trace.isOn) {
/* 3598 */           Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQPortPropertyValidator", "fromCanonical(JmsPropertyContext,String,Object)", null, 1);
/*      */         }
/*      */         
/* 3601 */         return null;
/*      */       } 
/*      */ 
/*      */       
/* 3605 */       int startPort = connectionNameList.indexOf('(');
/* 3606 */       int endPort = connectionNameList.indexOf(')');
/*      */       
/* 3608 */       String port = connectionNameList.substring(startPort + 1, endPort);
/*      */       
/* 3610 */       WMQValidationInterface.WMQPropertyValidatorDatatype traceRet1 = super.fromCanonical(context, keyIn, port);
/* 3611 */       if (Trace.isOn) {
/* 3612 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQPortPropertyValidator", "fromCanonical(JmsPropertyContext,String,Object)", traceRet1, 2);
/*      */       }
/*      */       
/* 3615 */       return traceRet1;
/*      */     }
/*      */ 
/*      */     
/*      */     public WMQValidationInterface.WMQPropertyValidatorDatatype toCanonical(JmsPropertyContext context, String keyIn, Object valueIn) {
/*      */       WMQConnectionNameList<WMQConnectionName> wMQConnectionNameList;
/* 3621 */       if (Trace.isOn) {
/* 3622 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQPortPropertyValidator", "toCanonical(JmsPropertyContext,String,Object)", new Object[] { context, keyIn, valueIn });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 3627 */       ArrayList<WMQConnectionName> connectionNameList = null;
/*      */       try {
/* 3629 */         connectionNameList = (ArrayList<WMQConnectionName>)context.getObjectProperty("XMSC_WMQ_CONNECTION_NAME_LIST_INT");
/* 3630 */         if (connectionNameList == null) {
/* 3631 */           wMQConnectionNameList = new WMQConnectionNameList();
/* 3632 */           wMQConnectionNameList.add(new WMQConnectionName("localhost", 1414));
/*      */         } 
/* 3634 */         WMQConnectionName wcn = wMQConnectionNameList.get(0);
/* 3635 */         if (valueIn instanceof String) {
/* 3636 */           wcn.setPort(Integer.parseInt((String)valueIn));
/*      */         }
/* 3638 */         else if (valueIn instanceof Integer) {
/* 3639 */           wcn.setPort(((Integer)valueIn).intValue());
/*      */         }
/*      */       
/*      */       }
/* 3643 */       catch (JMSException e) {
/* 3644 */         if (Trace.isOn) {
/* 3645 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.factories.WMQPortPropertyValidator", "toCanonical(JmsPropertyContext,String,Object)", (Throwable)e);
/*      */         }
/*      */         
/* 3648 */         if (Trace.isOn) {
/* 3649 */           Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQPortPropertyValidator", "toCanonical(JmsPropertyContext,String,Object)", null, 1);
/*      */         }
/*      */         
/* 3652 */         return null;
/*      */       } 
/*      */       
/* 3655 */       WMQValidationInterface.WMQPropertyValidatorDatatype returnVal = new WMQValidationInterface.WMQPropertyValidatorDatatype("XMSC_WMQ_CONNECTION_NAME_LIST_INT", wMQConnectionNameList);
/*      */ 
/*      */       
/* 3658 */       if (Trace.isOn) {
/* 3659 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQPortPropertyValidator", "toCanonical(JmsPropertyContext,String,Object)", returnVal, 2);
/*      */       }
/*      */       
/* 3662 */       return returnVal;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3670 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 3672 */       if (Trace.isOn) {
/* 3673 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQPortPropertyValidator", "static()");
/*      */       }
/* 3675 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_PORT");
/* 3676 */       domainsToPropertyNames.put(Integer.valueOf(1), "PORT");
/* 3677 */       domainsToPropertyNames.put(Integer.valueOf(2), "PORT");
/* 3678 */       if (Trace.isOn) {
/* 3679 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQPortPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 3684 */       if (Trace.isOn) {
/* 3685 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQPortPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 3688 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 3689 */         propertyValidators.put(entry.getValue(), new WMQPortPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 3691 */       if (Trace.isOn) {
/* 3692 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQPortPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQPortPropertyValidator(int domain) {
/* 3703 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/* 3709 */       if (validateIntForRange(value, 0, 2147483647)) {
/* 3710 */         updatePortFlag(parent);
/* 3711 */         return true;
/*      */       } 
/* 3713 */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean crossPropertyValidate(Object parent) throws JMSException {
/* 3718 */       if (Trace.isOn) {
/* 3719 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQPortPropertyValidator", "crossPropertyValidate(Object)", new Object[] { parent });
/*      */       }
/*      */       
/* 3722 */       if (((JmsPropertyContext)parent).getIntProperty("XMSC_WMQ_PORT") != 1414 && ((JmsPropertyContext)parent).getIntProperty("XMSC_WMQ_PORT") != 1506 && (
/* 3723 */         (JmsPropertyContext)parent).getIntProperty("XMSC_WMQ_CONNECTION_MODE") == 0) {
/* 3724 */         if (Trace.isOn) {
/* 3725 */           Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQPortPropertyValidator", "crossPropertyValidate(Object)", 
/* 3726 */               Boolean.valueOf(false), 1);
/*      */         }
/* 3728 */         return false;
/*      */       } 
/*      */       
/* 3731 */       if (Trace.isOn) {
/* 3732 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQPortPropertyValidator", "crossPropertyValidate(Object)", 
/* 3733 */             Boolean.valueOf(true), 2);
/*      */       }
/* 3735 */       return true;
/*      */     }
/*      */     
/*      */     private void updatePortFlag(final Object parent) {
/* 3739 */       if (Trace.isOn) {
/* 3740 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQPortPropertyValidator", "updatePortFlag(final Object)", new Object[] { parent });
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3749 */       if (parent instanceof MQConnectionFactory) {
/* 3750 */         AccessController.doPrivileged(new PrivilegedAction()
/*      */             {
/*      */               public Object run()
/*      */               {
/* 3754 */                 if (Trace.isOn) {
/* 3755 */                   Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQPortPropertyValidator", "run()");
/*      */                 }
/*      */                 
/*      */                 try {
/* 3759 */                   Field portSetField = MQConnectionFactory.class.getDeclaredField("portSet");
/* 3760 */                   portSetField.setAccessible(true);
/* 3761 */                   portSetField.setBoolean(parent, true);
/*      */                 }
/* 3763 */                 catch (NoSuchFieldException nsfe) {
/* 3764 */                   if (Trace.isOn) {
/* 3765 */                     Trace.catchBlock(this, "com.ibm.msg.client.wmq.factories.null", "run()", nsfe, 1);
/*      */                   }
/*      */                 }
/* 3768 */                 catch (IllegalAccessException iae) {
/* 3769 */                   if (Trace.isOn) {
/* 3770 */                     Trace.catchBlock(this, "com.ibm.msg.client.wmq.factories.null", "run()", iae, 2);
/*      */                   }
/*      */                 } finally {
/*      */                   
/* 3774 */                   if (Trace.isOn) {
/* 3775 */                     Trace.finallyBlock(this, "com.ibm.msg.client.wmq.factories.null", "run()");
/*      */                   }
/*      */                 } 
/* 3778 */                 if (Trace.isOn) {
/* 3779 */                   Trace.exit(this, "com.ibm.msg.client.wmq.factories.null", "run()", null);
/*      */                 }
/* 3781 */                 return null;
/*      */               }
/*      */             });
/*      */       }
/*      */       
/* 3786 */       if (Trace.isOn) {
/* 3787 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQPortPropertyValidator", "updatePortFlag(final Object)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object port) throws JMSException {
/* 3795 */       if (Trace.isOn) {
/* 3796 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQPortPropertyValidator", "throwBadValueException(Object)", new Object[] { port });
/*      */       }
/*      */       
/* 3799 */       HashMap<String, Object> info = new HashMap<>();
/* 3800 */       info.put("XMSC_INSERT_VALUE", String.valueOf(port));
/* 3801 */       info.put("XMSC_INSERT_NAME", "port");
/* 3802 */       JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/*      */       
/* 3804 */       if (Trace.isOn) {
/* 3805 */         Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQPortPropertyValidator", "throwBadValueException(Object)", (Throwable)je);
/*      */       }
/*      */       
/* 3808 */       throw je;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQProcessDurationPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -4268937941756073078L;
/*      */     
/* 3819 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 3821 */       if (Trace.isOn) {
/* 3822 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQProcessDurationPropertyValidator", "static()");
/*      */       }
/*      */       
/* 3825 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_PROCESS_DURATION");
/* 3826 */       domainsToPropertyNames.put(Integer.valueOf(1), "PROCESSDURATION");
/* 3827 */       domainsToPropertyNames.put(Integer.valueOf(2), "PROCDUR");
/* 3828 */       if (Trace.isOn) {
/* 3829 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQProcessDurationPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 3835 */       if (Trace.isOn) {
/* 3836 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQProcessDurationPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 3839 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 3840 */         propertyValidators.put(entry.getValue(), new WMQProcessDurationPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 3842 */       if (Trace.isOn) {
/* 3843 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQProcessDurationPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 3849 */     private static HashMap<Object, Object> valuesToCanonical = new HashMap<>(3, 1.0F);
/* 3850 */     private static HashSet<Object> validValues = new HashSet(3, 1.0F);
/*      */     static {
/* 3852 */       if (Trace.isOn) {
/* 3853 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQProcessDurationPropertyValidator", "static()");
/*      */       }
/*      */       
/* 3856 */       valuesToCanonical.put("UNKNOWN", Integer.valueOf(0));
/* 3857 */       valuesToCanonical.put("SHORT", Integer.valueOf(1));
/* 3858 */       valuesToCanonical.put("DEFAULT", Integer.valueOf(0));
/* 3859 */       validValues.addAll(valuesToCanonical.values());
/* 3860 */       if (Trace.isOn) {
/* 3861 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQProcessDurationPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQProcessDurationPropertyValidator(int domain) {
/* 3871 */       super(domain, domainsToPropertyNames, valuesToCanonical);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/* 3877 */       return validateIntBySet(value, validValues);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object newVal) throws JMSException {
/* 3882 */       if (Trace.isOn) {
/* 3883 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQProcessDurationPropertyValidator", "throwBadValueException(Object)", new Object[] { newVal });
/*      */       }
/*      */       
/* 3886 */       HashMap<String, Object> info = new HashMap<>();
/* 3887 */       info.put("XMSC_INSERT_VALUE", String.valueOf(newVal));
/* 3888 */       info.put("XMSC_INSERT_NAME", "processDuration");
/* 3889 */       JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/*      */       
/* 3891 */       if (Trace.isOn) {
/* 3892 */         Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQProcessDurationPropertyValidator", "throwBadValueException(Object)", (Throwable)je);
/*      */       }
/*      */ 
/*      */       
/* 3896 */       throw je;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQProviderVersionPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 3796689812129943870L;
/*      */     
/* 3907 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 3909 */       if (Trace.isOn) {
/* 3910 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQProviderVersionPropertyValidator", "static()");
/*      */       }
/*      */       
/* 3913 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_PROVIDER_VERSION");
/* 3914 */       domainsToPropertyNames.put(Integer.valueOf(1), "PROVIDERVERSION");
/* 3915 */       domainsToPropertyNames.put(Integer.valueOf(2), "PVER");
/* 3916 */       if (Trace.isOn) {
/* 3917 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQProviderVersionPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 3923 */       if (Trace.isOn) {
/* 3924 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQProviderVersionPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 3927 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 3928 */         propertyValidators.put(entry.getValue(), new WMQProviderVersionPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 3930 */       if (Trace.isOn) {
/* 3931 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQProviderVersionPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3940 */     private static final Pattern providerVersionPattern = Pattern.compile("\\d+(\\.\\d+){0,3}|unspecified", 2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQProviderVersionPropertyValidator(int domain) {
/* 3947 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object valueP, Object parent) throws JMSException {
/* 3953 */       Object value = valueP;
/* 3954 */       if (value == null)
/*      */       {
/* 3956 */         return false;
/*      */       }
/*      */       
/* 3959 */       value = convertToString(value);
/*      */       
/* 3961 */       if (!providerVersionPattern.matcher((String)value).matches()) {
/* 3962 */         HashMap<String, Object> info = new HashMap<>();
/* 3963 */         info.put("XMSC_INSERT_VALUE", value);
/* 3964 */         info.put("XMSC_INSERT_NAME", "ProviderVersion");
/* 3965 */         JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/*      */         
/* 3967 */         throw je;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3973 */       if (!((String)value).equalsIgnoreCase("unspecified")) {
/*      */ 
/*      */         
/* 3976 */         String[] providerVerElements = ((String)value).split("\\.");
/* 3977 */         if (providerVerElements.length == 0) {
/*      */ 
/*      */           
/* 3980 */           HashMap<String, Object> info = new HashMap<>();
/* 3981 */           info.put("XMSC_INSERT_VALUE", value);
/* 3982 */           info.put("XMSC_INSERT_NAME", "ProviderVersion");
/* 3983 */           JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/*      */           
/* 3985 */           throw je;
/*      */         } 
/*      */         
/* 3988 */         int[] elemValue = new int[4];
/* 3989 */         Arrays.fill(elemValue, 0);
/* 3990 */         for (int i = 0; i < providerVerElements.length; i++) {
/*      */           
/*      */           try {
/* 3993 */             elemValue[i] = Integer.parseInt(providerVerElements[i]);
/*      */           }
/* 3995 */           catch (NumberFormatException nfe) {
/*      */ 
/*      */ 
/*      */             
/* 3999 */             HashMap<String, Object> info = new HashMap<>();
/* 4000 */             info.put("XMSC_INSERT_VALUE", value);
/* 4001 */             info.put("XMSC_INSERT_NAME", "ProviderVersion");
/* 4002 */             JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/*      */             
/* 4004 */             throw je;
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 4010 */         long pverBig = 0L;
/* 4011 */         for (int j = 0; j < 4; j++) {
/* 4012 */           pverBig *= 100L;
/* 4013 */           pverBig += elemValue[j];
/*      */         } 
/*      */         
/* 4016 */         if (pverBig > 9000000L || pverBig < 6000000L) {
/* 4017 */           HashMap<String, Object> info = new HashMap<>();
/* 4018 */           info.put("XMSC_INSERT_VALUE", value);
/* 4019 */           info.put("XMSC_INSERT_NAME", "ProviderVersion");
/* 4020 */           JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/*      */           
/* 4022 */           throw je;
/*      */         } 
/*      */       } 
/*      */       
/* 4026 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean crossPropertyValidate(Object parent) throws JMSException {
/* 4031 */       if (Trace.isOn) {
/* 4032 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQProviderVersionPropertyValidator", "crossPropertyValidate(Object)", new Object[] { parent });
/*      */       }
/*      */ 
/*      */       
/* 4036 */       if (!((JmsPropertyContext)parent).getStringProperty("XMSC_WMQ_PROVIDER_VERSION").equals("unspecified") && (
/* 4037 */         (JmsPropertyContext)parent).getIntProperty("XMSC_WMQ_CONNECTION_MODE") == 0) {
/* 4038 */         if (Trace.isOn) {
/* 4039 */           Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQProviderVersionPropertyValidator", "crossPropertyValidate(Object)", 
/*      */               
/* 4041 */               Boolean.valueOf(false), 1);
/*      */         }
/* 4043 */         return false;
/*      */       } 
/*      */       
/* 4046 */       if (Trace.isOn) {
/* 4047 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQProviderVersionPropertyValidator", "crossPropertyValidate(Object)", 
/* 4048 */             Boolean.valueOf(true), 2);
/*      */       }
/* 4050 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 4055 */       if (Trace.isOn) {
/* 4056 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQProviderVersionPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 4061 */       if (Trace.isOn) {
/* 4062 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQProviderVersionPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQPubAckIntervalPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -7759281454969029483L;
/*      */ 
/*      */     
/* 4076 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 4078 */       if (Trace.isOn) {
/* 4079 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQPubAckIntervalPropertyValidator", "static()");
/*      */       }
/*      */       
/* 4082 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_PUB_ACK_INTERVAL");
/* 4083 */       domainsToPropertyNames.put(Integer.valueOf(1), "PUBACKINT");
/* 4084 */       domainsToPropertyNames.put(Integer.valueOf(2), "PAI");
/* 4085 */       if (Trace.isOn) {
/* 4086 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQPubAckIntervalPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 4092 */       if (Trace.isOn) {
/* 4093 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQPubAckIntervalPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 4096 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 4097 */         propertyValidators.put(entry.getValue(), new WMQPubAckIntervalPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 4099 */       if (Trace.isOn) {
/* 4100 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQPubAckIntervalPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQPubAckIntervalPropertyValidator(int domain) {
/* 4111 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/* 4117 */       WMQConnectionFactory.checkNotQueueCF(parent, "setPubAckInterval()");
/* 4118 */       return validateIntForRange(value, -1, 2147483647);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object interval) throws JMSException {
/* 4123 */       if (Trace.isOn) {
/* 4124 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQPubAckIntervalPropertyValidator", "throwBadValueException(Object)", new Object[] { interval });
/*      */       }
/*      */       
/* 4127 */       HashMap<String, Object> info = new HashMap<>();
/* 4128 */       info.put("XMSC_INSERT_VALUE", String.valueOf(interval));
/* 4129 */       info.put("XMSC_INSERT_NAME", "PubAckInterval");
/* 4130 */       JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/*      */       
/* 4132 */       if (Trace.isOn) {
/* 4133 */         Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQPubAckIntervalPropertyValidator", "throwBadValueException(Object)", (Throwable)je);
/*      */       }
/*      */ 
/*      */       
/* 4137 */       throw je;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQQueueManagerPropertyValidator
/*      */     extends WMQStandardValidators.WMQQueueNamePropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 6046121161697048153L;
/*      */     
/* 4148 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 4150 */       if (Trace.isOn) {
/* 4151 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQQueueManagerPropertyValidator", "static()");
/*      */       }
/*      */       
/* 4154 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_QUEUE_MANAGER");
/* 4155 */       domainsToPropertyNames.put(Integer.valueOf(1), "QMANAGER");
/* 4156 */       domainsToPropertyNames.put(Integer.valueOf(2), "QMGR");
/* 4157 */       if (Trace.isOn) {
/* 4158 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQQueueManagerPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 4163 */       if (Trace.isOn) {
/* 4164 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQQueueManagerPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 4167 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 4168 */         propertyValidators.put(entry.getValue(), new WMQQueueManagerPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 4170 */       if (Trace.isOn) {
/* 4171 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQQueueManagerPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQQueueManagerPropertyValidator(int domain) {
/* 4182 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 4187 */       if (Trace.isOn) {
/* 4188 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQQueueManagerPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 4193 */       if (Trace.isOn) {
/* 4194 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQQueueManagerPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQReceiveExitPropertyValidator
/*      */     extends WMQStandardValidators.WMQStringPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -4603255629202181269L;
/*      */ 
/*      */     
/* 4208 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 4210 */       if (Trace.isOn) {
/* 4211 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQReceiveExitPropertyValidator", "static()");
/*      */       }
/* 4213 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_RECEIVE_EXIT");
/* 4214 */       domainsToPropertyNames.put(Integer.valueOf(1), "RECEXIT");
/* 4215 */       domainsToPropertyNames.put(Integer.valueOf(2), "RCX");
/* 4216 */       if (Trace.isOn) {
/* 4217 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQReceiveExitPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 4222 */       if (Trace.isOn) {
/* 4223 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQReceiveExitPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 4226 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 4227 */         propertyValidators.put(entry.getValue(), new WMQReceiveExitPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 4229 */       if (Trace.isOn) {
/* 4230 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQReceiveExitPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQReceiveExitPropertyValidator(int domain) {
/* 4241 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 4246 */       if (Trace.isOn) {
/* 4247 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQReceiveExitPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 4252 */       if (Trace.isOn) {
/* 4253 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQReceiveExitPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQReceiveExitInitPropertyValidator
/*      */     extends WMQStandardValidators.WMQStringPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 523959514006874514L;
/*      */ 
/*      */     
/* 4267 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 4269 */       if (Trace.isOn) {
/* 4270 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQReceiveExitInitPropertyValidator", "static()");
/*      */       }
/*      */       
/* 4273 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_RECEIVE_EXIT_INIT");
/* 4274 */       domainsToPropertyNames.put(Integer.valueOf(1), "RECEXITINIT");
/* 4275 */       domainsToPropertyNames.put(Integer.valueOf(2), "RCXI");
/* 4276 */       if (Trace.isOn) {
/* 4277 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQReceiveExitInitPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 4283 */       if (Trace.isOn) {
/* 4284 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQReceiveExitInitPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 4287 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 4288 */         propertyValidators.put(entry.getValue(), new WMQReceiveExitInitPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 4290 */       if (Trace.isOn) {
/* 4291 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQReceiveExitInitPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQReceiveExitInitPropertyValidator(int domain) {
/* 4302 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 4307 */       if (Trace.isOn) {
/* 4308 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQReceiveExitInitPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 4313 */       if (Trace.isOn) {
/* 4314 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQReceiveExitInitPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQRcvIsolPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -5326028447026745256L;
/*      */ 
/*      */     
/* 4328 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 4330 */       if (Trace.isOn) {
/* 4331 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQRcvIsolPropertyValidator", "static()");
/*      */       }
/* 4333 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_RECEIVE_ISOLATION");
/* 4334 */       domainsToPropertyNames.put(Integer.valueOf(1), "RECEIVEISOLATION");
/* 4335 */       domainsToPropertyNames.put(Integer.valueOf(2), "RCVISOL");
/* 4336 */       if (Trace.isOn) {
/* 4337 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQRcvIsolPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 4342 */       if (Trace.isOn) {
/* 4343 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQRcvIsolPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 4346 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 4347 */         propertyValidators.put(entry.getValue(), new WMQRcvIsolPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 4349 */       if (Trace.isOn) {
/* 4350 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQRcvIsolPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 4356 */     private static HashMap<Object, Object> valuesToCanonical = new HashMap<>(3, 1.0F);
/* 4357 */     private static Set<Object> validValues = new HashSet(3, 1.0F);
/*      */     static {
/* 4359 */       if (Trace.isOn) {
/* 4360 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQRcvIsolPropertyValidator", "static()");
/*      */       }
/* 4362 */       valuesToCanonical.put("COMMITTED", Integer.valueOf(0));
/* 4363 */       valuesToCanonical.put("UNCOMMITTED", Integer.valueOf(1));
/* 4364 */       valuesToCanonical.put("DEFAULT", Integer.valueOf(0));
/* 4365 */       validValues.addAll(valuesToCanonical.values());
/* 4366 */       if (Trace.isOn) {
/* 4367 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQRcvIsolPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQRcvIsolPropertyValidator(int domain) {
/* 4376 */       super(domain, domainsToPropertyNames, valuesToCanonical);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/* 4382 */       return validateIntBySet(value, validValues);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object newVal) throws JMSException {
/* 4387 */       if (Trace.isOn) {
/* 4388 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQRcvIsolPropertyValidator", "throwBadValueException(Object)", new Object[] { newVal });
/*      */       }
/*      */ 
/*      */       
/* 4392 */       HashMap<String, Object> info = new HashMap<>();
/* 4393 */       info.put("XMSC_INSERT_VALUE", String.valueOf(newVal));
/* 4394 */       info.put("XMSC_INSERT_NAME", "receiveIsolation");
/* 4395 */       JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/*      */       
/* 4397 */       if (Trace.isOn) {
/* 4398 */         Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQRcvIsolPropertyValidator", "throwBadValueException(Object)", (Throwable)je);
/*      */       }
/*      */       
/* 4401 */       throw je;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQRescanIntervalPropertyValidator
/*      */     extends WMQStandardValidators.WMQQueueNamePropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 6182528870830046926L;
/*      */ 
/*      */     
/* 4413 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 4415 */       if (Trace.isOn) {
/* 4416 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQRescanIntervalPropertyValidator", "static()");
/*      */       }
/*      */       
/* 4419 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_RESCAN_INTERVAL");
/* 4420 */       domainsToPropertyNames.put(Integer.valueOf(1), "RESCANINT");
/* 4421 */       domainsToPropertyNames.put(Integer.valueOf(2), "RINT");
/* 4422 */       if (Trace.isOn) {
/* 4423 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQRescanIntervalPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 4429 */       if (Trace.isOn) {
/* 4430 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQRescanIntervalPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 4433 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 4434 */         propertyValidators.put(entry.getValue(), new WMQRescanIntervalPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 4436 */       if (Trace.isOn) {
/* 4437 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQRescanIntervalPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQRescanIntervalPropertyValidator(int domain) {
/* 4448 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object valueP, Object parent) throws JMSException {
/* 4454 */       return validateIntForRange(valueP, 0, 2147483647);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object interval) throws JMSException {
/* 4459 */       if (Trace.isOn) {
/* 4460 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQRescanIntervalPropertyValidator", "throwBadValueException(Object)", new Object[] { interval });
/*      */       }
/*      */ 
/*      */       
/* 4464 */       HashMap<String, Object> info = new HashMap<>();
/* 4465 */       info.put("XMSC_INSERT_VALUE", String.valueOf(interval));
/* 4466 */       info.put("XMSC_INSERT_NAME", "rescanInterval");
/* 4467 */       JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/*      */       
/* 4469 */       if (Trace.isOn) {
/* 4470 */         Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQRescanIntervalPropertyValidator", "throwBadValueException(Object)", (Throwable)je);
/*      */       }
/*      */ 
/*      */       
/* 4474 */       throw je;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQSecurityExitPropertyValidator
/*      */     extends WMQStandardValidators.WMQStringPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 2590959585421048601L;
/*      */     
/* 4485 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 4487 */       if (Trace.isOn) {
/* 4488 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQSecurityExitPropertyValidator", "static()");
/*      */       }
/*      */       
/* 4491 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_SECURITY_EXIT");
/* 4492 */       domainsToPropertyNames.put(Integer.valueOf(1), "SECEXIT");
/* 4493 */       domainsToPropertyNames.put(Integer.valueOf(2), "SCX");
/* 4494 */       if (Trace.isOn) {
/* 4495 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQSecurityExitPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 4500 */       if (Trace.isOn) {
/* 4501 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQSecurityExitPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 4504 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 4505 */         propertyValidators.put(entry.getValue(), new WMQSecurityExitPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 4507 */       if (Trace.isOn) {
/* 4508 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQSecurityExitPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQSecurityExitPropertyValidator(int domain) {
/* 4519 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 4524 */       if (Trace.isOn) {
/* 4525 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQSecurityExitPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 4530 */       if (Trace.isOn) {
/* 4531 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQSecurityExitPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQSecurityExitInitPropertyValidator
/*      */     extends WMQStandardValidators.WMQStringPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 5282141183069892571L;
/*      */ 
/*      */     
/* 4545 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 4547 */       if (Trace.isOn) {
/* 4548 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQSecurityExitInitPropertyValidator", "static()");
/*      */       }
/*      */       
/* 4551 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_SECURITY_EXIT_INIT");
/* 4552 */       domainsToPropertyNames.put(Integer.valueOf(1), "SECEXITINIT");
/* 4553 */       domainsToPropertyNames.put(Integer.valueOf(2), "SCXI");
/* 4554 */       if (Trace.isOn) {
/* 4555 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQSecurityExitInitPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 4561 */       if (Trace.isOn) {
/* 4562 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQSecurityExitInitPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 4565 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 4566 */         propertyValidators.put(entry.getValue(), new WMQSecurityExitInitPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 4568 */       if (Trace.isOn) {
/* 4569 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQSecurityExitInitPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQSecurityExitInitPropertyValidator(int domain) {
/* 4580 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 4585 */       if (Trace.isOn) {
/* 4586 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQSecurityExitInitPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 4591 */       if (Trace.isOn) {
/* 4592 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQSecurityExitInitPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQSendCheckCountPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 1458473553538049949L;
/*      */ 
/*      */     
/* 4606 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 4608 */       if (Trace.isOn) {
/* 4609 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQSendCheckCountPropertyValidator", "static()");
/*      */       }
/*      */       
/* 4612 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_SEND_CHECK_COUNT");
/* 4613 */       domainsToPropertyNames.put(Integer.valueOf(1), "SENDCHECKCOUNT");
/* 4614 */       domainsToPropertyNames.put(Integer.valueOf(2), "SCC");
/* 4615 */       if (Trace.isOn) {
/* 4616 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQSendCheckCountPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 4622 */       if (Trace.isOn) {
/* 4623 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQSendCheckCountPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 4626 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 4627 */         propertyValidators.put(entry.getValue(), new WMQSendCheckCountPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 4629 */       if (Trace.isOn) {
/* 4630 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQSendCheckCountPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQSendCheckCountPropertyValidator(int domain) {
/* 4641 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object valueP, Object parent) throws JMSException {
/* 4647 */       return validateIntForRange(valueP, 0, 2147483647);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object intval) throws JMSException {
/* 4652 */       if (Trace.isOn) {
/* 4653 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQSendCheckCountPropertyValidator", "throwBadValueException(Object)", new Object[] { intval });
/*      */       }
/*      */       
/* 4656 */       HashMap<String, Object> info = new HashMap<>();
/* 4657 */       info.put("XMSC_INSERT_VALUE", String.valueOf(intval));
/* 4658 */       info.put("XMSC_INSERT_NAME", "sendCheckCount");
/* 4659 */       JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/*      */       
/* 4661 */       if (Trace.isOn) {
/* 4662 */         Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQSendCheckCountPropertyValidator", "throwBadValueException(Object)", (Throwable)je);
/*      */       }
/*      */ 
/*      */       
/* 4666 */       throw je;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQSendExitPropertyValidator
/*      */     extends WMQStandardValidators.WMQStringPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 1886796269033400462L;
/*      */     
/* 4677 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 4679 */       if (Trace.isOn) {
/* 4680 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQSendExitPropertyValidator", "static()");
/*      */       }
/* 4682 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_SEND_EXIT");
/* 4683 */       domainsToPropertyNames.put(Integer.valueOf(1), "SENDEXIT");
/* 4684 */       domainsToPropertyNames.put(Integer.valueOf(2), "SDX");
/* 4685 */       if (Trace.isOn) {
/* 4686 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQSendExitPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 4691 */       if (Trace.isOn) {
/* 4692 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQSendExitPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 4695 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 4696 */         propertyValidators.put(entry.getValue(), new WMQSendExitPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 4698 */       if (Trace.isOn) {
/* 4699 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQSendExitPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQSendExitPropertyValidator(int domain) {
/* 4710 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 4715 */       if (Trace.isOn) {
/* 4716 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQSendExitPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 4721 */       if (Trace.isOn) {
/* 4722 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQSendExitPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQSendExitInitPropertyValidator
/*      */     extends WMQStandardValidators.WMQStringPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 5905014472636439912L;
/*      */ 
/*      */     
/* 4736 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 4738 */       if (Trace.isOn) {
/* 4739 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQSendExitInitPropertyValidator", "static()");
/*      */       }
/*      */       
/* 4742 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_SEND_EXIT_INIT");
/* 4743 */       domainsToPropertyNames.put(Integer.valueOf(1), "SENDEXITINIT");
/* 4744 */       domainsToPropertyNames.put(Integer.valueOf(2), "SDXI");
/* 4745 */       if (Trace.isOn) {
/* 4746 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQSendExitInitPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 4751 */       if (Trace.isOn) {
/* 4752 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQSendExitInitPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 4755 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 4756 */         propertyValidators.put(entry.getValue(), new WMQSendExitInitPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 4758 */       if (Trace.isOn) {
/* 4759 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQSendExitInitPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQSendExitInitPropertyValidator(int domain) {
/* 4770 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 4775 */       if (Trace.isOn) {
/* 4776 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQSendExitInitPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 4781 */       if (Trace.isOn) {
/* 4782 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQSendExitInitPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQShareConvAllowedPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -2057880582374102891L;
/*      */ 
/*      */     
/* 4796 */     private static HashMap<Object, Object> valuesToCanonical = new HashMap<>(2, 1.0F);
/* 4797 */     private static Set<Object> validValues = new HashSet(2, 1.0F);
/*      */     static {
/* 4799 */       if (Trace.isOn) {
/* 4800 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQShareConvAllowedPropertyValidator", "static()");
/*      */       }
/*      */       
/* 4803 */       valuesToCanonical.put("YES", Integer.valueOf(1));
/* 4804 */       valuesToCanonical.put("NO", Integer.valueOf(0));
/* 4805 */       validValues.addAll(valuesToCanonical.values());
/* 4806 */       if (Trace.isOn) {
/* 4807 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQShareConvAllowedPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/* 4812 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 4814 */       if (Trace.isOn) {
/* 4815 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQShareConvAllowedPropertyValidator", "static()");
/*      */       }
/*      */       
/* 4818 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_SHARE_CONV_ALLOWED");
/* 4819 */       domainsToPropertyNames.put(Integer.valueOf(1), "SHARECONVALLOWED");
/* 4820 */       domainsToPropertyNames.put(Integer.valueOf(2), "SCALD");
/* 4821 */       if (Trace.isOn) {
/* 4822 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQShareConvAllowedPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 4828 */       if (Trace.isOn) {
/* 4829 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQShareConvAllowedPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 4832 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 4833 */         propertyValidators.put(entry.getValue(), new WMQShareConvAllowedPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 4835 */       if (Trace.isOn) {
/* 4836 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQShareConvAllowedPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQShareConvAllowedPropertyValidator(int domain) {
/* 4847 */       super(domain, domainsToPropertyNames, valuesToCanonical);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/* 4853 */       return validateIntBySet(value, validValues);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean crossPropertyValidate(Object parent) throws JMSException {
/* 4858 */       if (Trace.isOn) {
/* 4859 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQShareConvAllowedPropertyValidator", "crossPropertyValidate(Object)", new Object[] { parent });
/*      */       }
/*      */       
/* 4862 */       if (((JmsPropertyContext)parent).getIntProperty("XMSC_WMQ_SHARE_CONV_ALLOWED") != 1 && (
/* 4863 */         (JmsPropertyContext)parent).getIntProperty("XMSC_WMQ_CONNECTION_MODE") != 1) {
/* 4864 */         if (Trace.isOn) {
/* 4865 */           Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQShareConvAllowedPropertyValidator", "crossPropertyValidate(Object)", 
/*      */               
/* 4867 */               Boolean.valueOf(false), 1);
/*      */         }
/* 4869 */         return false;
/*      */       } 
/*      */       
/* 4872 */       if (Trace.isOn) {
/* 4873 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQShareConvAllowedPropertyValidator", "crossPropertyValidate(Object)", 
/* 4874 */             Boolean.valueOf(true), 2);
/*      */       }
/* 4876 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object intval) throws JMSException {
/* 4881 */       if (Trace.isOn) {
/* 4882 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQShareConvAllowedPropertyValidator", "throwBadValueException(Object)", new Object[] { intval });
/*      */       }
/*      */       
/* 4885 */       HashMap<String, Object> info = new HashMap<>();
/* 4886 */       info.put("XMSC_INSERT_VALUE", String.valueOf(intval));
/* 4887 */       info.put("XMSC_INSERT_NAME", "shareConvAllowed");
/* 4888 */       JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/*      */       
/* 4890 */       if (Trace.isOn) {
/* 4891 */         Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQShareConvAllowedPropertyValidator", "throwBadValueException(Object)", (Throwable)je);
/*      */       }
/*      */ 
/*      */       
/* 4895 */       throw je;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQSparseSubscriptionsPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 2863993679302251180L;
/*      */     
/* 4906 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 4908 */       if (Trace.isOn) {
/* 4909 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQSparseSubscriptionsPropertyValidator", "static()");
/*      */       }
/*      */       
/* 4912 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_SPARSE_SUBSCRIPTIONS");
/* 4913 */       domainsToPropertyNames.put(Integer.valueOf(1), "SPARSESUBS");
/* 4914 */       domainsToPropertyNames.put(Integer.valueOf(2), "SSUBS");
/* 4915 */       if (Trace.isOn) {
/* 4916 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQSparseSubscriptionsPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 4922 */       if (Trace.isOn) {
/* 4923 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQSparseSubscriptionsPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 4926 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 4927 */         propertyValidators.put(entry.getValue(), new WMQSparseSubscriptionsPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 4929 */       if (Trace.isOn) {
/* 4930 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQSparseSubscriptionsPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQSparseSubscriptionsPropertyValidator(int domain) {
/* 4941 */       super(domain, domainsToPropertyNames, yesNoToBoolean);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/* 4948 */       WMQConnectionFactory.checkNotQueueCF(parent, "setSparseSubscriptions()");
/*      */       
/* 4950 */       return validateBooleanTFYN(value);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 4955 */       if (Trace.isOn) {
/* 4956 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQSparseSubscriptionsPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4962 */       if (Trace.isOn) {
/* 4963 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQSparseSubscriptionsPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQSSLCertStoresColPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -8934571746754688885L;
/*      */ 
/*      */ 
/*      */     
/* 4978 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 4980 */       if (Trace.isOn) {
/* 4981 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQSSLCertStoresColPropertyValidator", "static()");
/*      */       }
/*      */       
/* 4984 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_SSL_CERT_STORES_COL");
/* 4985 */       if (Trace.isOn) {
/* 4986 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQSSLCertStoresColPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 4992 */       if (Trace.isOn) {
/* 4993 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQSSLCertStoresColPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 4996 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 4997 */         propertyValidators.put(entry.getValue(), new WMQSSLCertStoresColPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 4999 */       if (Trace.isOn) {
/* 5000 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQSSLCertStoresColPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQSSLCertStoresColPropertyValidator(int domain) {
/* 5011 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/* 5017 */       if (value instanceof Collection) {
/* 5018 */         return true;
/*      */       }
/* 5020 */       if (value == null)
/*      */       {
/*      */         
/* 5023 */         return true;
/*      */       }
/* 5025 */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean crossPropertyValidate(Object parent) throws JMSException {
/* 5030 */       if (Trace.isOn) {
/* 5031 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQSSLCertStoresColPropertyValidator", "crossPropertyValidate(Object)", new Object[] { parent });
/*      */       }
/*      */       
/* 5034 */       if (((JmsPropertyContext)parent).getObjectProperty("XMSC_WMQ_SSL_CERT_STORES_COL") != null && (
/* 5035 */         (JmsPropertyContext)parent).getObjectProperty("XMSC_WMQ_SSL_CIPHER_SUITE") == null) {
/* 5036 */         if (Trace.isOn) {
/* 5037 */           Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQSSLCertStoresColPropertyValidator", "crossPropertyValidate(Object)", 
/*      */               
/* 5039 */               Boolean.valueOf(false), 1);
/*      */         }
/* 5041 */         return false;
/*      */       } 
/*      */       
/* 5044 */       if (Trace.isOn) {
/* 5045 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQSSLCertStoresColPropertyValidator", "crossPropertyValidate(Object)", 
/* 5046 */             Boolean.valueOf(true), 2);
/*      */       }
/* 5048 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 5053 */       if (Trace.isOn) {
/* 5054 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQSSLCertStoresColPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 5059 */       if (Trace.isOn) {
/* 5060 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQSSLCertStoresColPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQSSLCertStoresStrPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -1183933341131104792L;
/*      */ 
/*      */     
/* 5074 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 5076 */       if (Trace.isOn) {
/* 5077 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQSSLCertStoresStrPropertyValidator", "static()");
/*      */       }
/*      */       
/* 5080 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_SSL_CERT_STORES_STR");
/* 5081 */       domainsToPropertyNames.put(Integer.valueOf(1), "SSLCRL");
/* 5082 */       domainsToPropertyNames.put(Integer.valueOf(2), "SCRL");
/* 5083 */       if (Trace.isOn) {
/* 5084 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQSSLCertStoresStrPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 5090 */       if (Trace.isOn) {
/* 5091 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQSSLCertStoresStrPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 5094 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 5095 */         propertyValidators.put(entry.getValue(), new WMQSSLCertStoresStrPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 5097 */       if (Trace.isOn) {
/* 5098 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQSSLCertStoresStrPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQSSLCertStoresStrPropertyValidator(int domain) {
/* 5109 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/* 5115 */       String stores = convertToString(value);
/* 5116 */       if (stores != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 5132 */         StringTokenizer st = new StringTokenizer(stores);
/*      */ 
/*      */ 
/*      */         
/* 5136 */         while (st.hasMoreTokens()) {
/* 5137 */           String currentHost = "";
/* 5138 */           String URI = st.nextToken();
/*      */ 
/*      */ 
/*      */           
/* 5142 */           if (!URI.toUpperCase().startsWith("LDAP://")) {
/*      */             
/* 5144 */             HashMap<String, Object> info = new HashMap<>();
/* 5145 */             info.put("XMSC_INSERT_VALUE", URI);
/* 5146 */             info.put("XMSC_INSERT_NAME", "CertStore URI protocol");
/* 5147 */             JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/*      */             
/* 5149 */             throw je;
/*      */           } 
/*      */           
/*      */           int portpos;
/* 5153 */           if ((portpos = URI.indexOf(':', 7)) != -1) {
/*      */             
/*      */             try
/*      */             {
/*      */ 
/*      */               
/* 5159 */               Integer.parseInt(URI.substring(portpos + 1));
/* 5160 */               currentHost = URI.substring(7, portpos);
/*      */             }
/* 5162 */             catch (NumberFormatException nfe)
/*      */             {
/* 5164 */               HashMap<String, Object> info = new HashMap<>();
/* 5165 */               info.put("XMSC_INSERT_VALUE", URI);
/* 5166 */               info.put("XMSC_INSERT_NAME", "CertStore URI port");
/* 5167 */               JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/*      */               
/* 5169 */               throw je;
/*      */             }
/*      */           
/*      */           } else {
/*      */             
/* 5174 */             currentHost = URI.substring(7);
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/* 5179 */           if (currentHost.length() <= 0) {
/*      */             
/* 5181 */             HashMap<String, Object> info = new HashMap<>();
/* 5182 */             info.put("XMSC_INSERT_VALUE", URI);
/* 5183 */             info.put("XMSC_INSERT_NAME", "CertStore URI port");
/* 5184 */             JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/*      */             
/* 5186 */             throw je;
/*      */           } 
/*      */         } 
/*      */       } 
/* 5190 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean crossPropertyValidate(Object parent) throws JMSException {
/* 5196 */       if (Trace.isOn) {
/* 5197 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQSSLCertStoresStrPropertyValidator", "crossPropertyValidate(Object)", new Object[] { parent });
/*      */       }
/*      */       
/* 5200 */       if (((JmsPropertyContext)parent).getObjectProperty("XMSC_WMQ_SSL_CERT_STORES_STR") != null && (
/* 5201 */         (JmsPropertyContext)parent).getObjectProperty("XMSC_WMQ_SSL_CIPHER_SUITE") == null) {
/* 5202 */         if (Trace.isOn) {
/* 5203 */           Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQSSLCertStoresStrPropertyValidator", "crossPropertyValidate(Object)", 
/*      */               
/* 5205 */               Boolean.valueOf(false), 1);
/*      */         }
/* 5207 */         return false;
/*      */       } 
/*      */       
/* 5210 */       if (Trace.isOn) {
/* 5211 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQSSLCertStoresStrPropertyValidator", "crossPropertyValidate(Object)", 
/* 5212 */             Boolean.valueOf(true), 2);
/*      */       }
/* 5214 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 5219 */       if (Trace.isOn) {
/* 5220 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQSSLCertStoresStrPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 5225 */       if (Trace.isOn) {
/* 5226 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQSSLCertStoresStrPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQSSLCipherSuitePropertyValidator
/*      */     extends WMQStandardValidators.WMQStringPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 8258565901457400654L;
/*      */ 
/*      */     
/* 5240 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 5242 */       if (Trace.isOn) {
/* 5243 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQSSLCipherSuitePropertyValidator", "static()");
/*      */       }
/*      */       
/* 5246 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_SSL_CIPHER_SUITE");
/* 5247 */       domainsToPropertyNames.put(Integer.valueOf(1), "SSLCIPHERSUITE");
/* 5248 */       domainsToPropertyNames.put(Integer.valueOf(2), "SCPHS");
/* 5249 */       if (Trace.isOn) {
/* 5250 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQSSLCipherSuitePropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 5256 */       if (Trace.isOn) {
/* 5257 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQSSLCipherSuitePropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 5260 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 5261 */         propertyValidators.put(entry.getValue(), new WMQSSLCipherSuitePropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 5263 */       if (Trace.isOn) {
/* 5264 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQSSLCipherSuitePropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQSSLCipherSuitePropertyValidator(int domain) {
/* 5275 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean crossPropertyValidate(Object parent) throws JMSException {
/* 5280 */       if (Trace.isOn) {
/* 5281 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQSSLCipherSuitePropertyValidator", "crossPropertyValidate(Object)", new Object[] { parent });
/*      */       }
/*      */       
/* 5284 */       if (((JmsPropertyContext)parent).getObjectProperty("XMSC_WMQ_SSL_CIPHER_SUITE") != null && (
/* 5285 */         (JmsPropertyContext)parent).getIntProperty("XMSC_WMQ_CONNECTION_MODE") != 1) {
/* 5286 */         if (Trace.isOn) {
/* 5287 */           Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQSSLCipherSuitePropertyValidator", "crossPropertyValidate(Object)", 
/*      */               
/* 5289 */               Boolean.valueOf(false), 1);
/*      */         }
/* 5291 */         return false;
/*      */       } 
/*      */       
/* 5294 */       if (Trace.isOn) {
/* 5295 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQSSLCipherSuitePropertyValidator", "crossPropertyValidate(Object)", 
/* 5296 */             Boolean.valueOf(true), 2);
/*      */       }
/* 5298 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 5303 */       if (Trace.isOn) {
/* 5304 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQSSLCipherSuitePropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 5309 */       if (Trace.isOn) {
/* 5310 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQSSLCipherSuitePropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQSSLFipsRequiredPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -3024451464357897333L;
/*      */ 
/*      */     
/* 5324 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 5326 */       if (Trace.isOn) {
/* 5327 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQSSLFipsRequiredPropertyValidator", "static()");
/*      */       }
/*      */       
/* 5330 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_SSL_FIPS_REQUIRED");
/* 5331 */       domainsToPropertyNames.put(Integer.valueOf(1), "SSLFIPSREQUIRED");
/* 5332 */       domainsToPropertyNames.put(Integer.valueOf(2), "SFIPS");
/* 5333 */       if (Trace.isOn) {
/* 5334 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQSSLFipsRequiredPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 5340 */       if (Trace.isOn) {
/* 5341 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQSSLFipsRequiredPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 5344 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 5345 */         propertyValidators.put(entry.getValue(), new WMQSSLFipsRequiredPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 5347 */       if (Trace.isOn) {
/* 5348 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQSSLFipsRequiredPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQSSLFipsRequiredPropertyValidator(int domain) {
/* 5359 */       super(domain, domainsToPropertyNames, yesNoToBoolean);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/* 5365 */       if (value == null)
/*      */       {
/*      */         
/* 5368 */         return true;
/*      */       }
/* 5370 */       return validateBooleanTFYN(value);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean crossPropertyValidate(Object parent) throws JMSException {
/* 5375 */       if (Trace.isOn) {
/* 5376 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQSSLFipsRequiredPropertyValidator", "crossPropertyValidate(Object)", new Object[] { parent });
/*      */       }
/*      */       
/* 5379 */       if (((JmsPropertyContext)parent).getBooleanProperty("XMSC_WMQ_SSL_FIPS_REQUIRED") && (
/* 5380 */         (JmsPropertyContext)parent).getObjectProperty("XMSC_WMQ_SSL_CIPHER_SUITE") == null) {
/* 5381 */         if (Trace.isOn) {
/* 5382 */           Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQSSLFipsRequiredPropertyValidator", "crossPropertyValidate(Object)", 
/*      */               
/* 5384 */               Boolean.valueOf(false), 1);
/*      */         }
/* 5386 */         return false;
/*      */       } 
/*      */       
/* 5389 */       if (Trace.isOn) {
/* 5390 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQSSLFipsRequiredPropertyValidator", "crossPropertyValidate(Object)", 
/* 5391 */             Boolean.valueOf(true), 2);
/*      */       }
/* 5393 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 5398 */       if (Trace.isOn) {
/* 5399 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQSSLFipsRequiredPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 5404 */       if (Trace.isOn) {
/* 5405 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQSSLFipsRequiredPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQSSLPeerNamePropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 4934320916933908241L;
/*      */ 
/*      */     
/* 5419 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 5421 */       if (Trace.isOn) {
/* 5422 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQSSLPeerNamePropertyValidator", "static()");
/*      */       }
/* 5424 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_SSL_PEER_NAME");
/* 5425 */       domainsToPropertyNames.put(Integer.valueOf(1), "SSLPEERNAME");
/* 5426 */       domainsToPropertyNames.put(Integer.valueOf(2), "SPEER");
/* 5427 */       if (Trace.isOn) {
/* 5428 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQSSLPeerNamePropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 5433 */       if (Trace.isOn) {
/* 5434 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQSSLPeerNamePropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 5437 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 5438 */         propertyValidators.put(entry.getValue(), new WMQSSLPeerNamePropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 5440 */       if (Trace.isOn) {
/* 5441 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQSSLPeerNamePropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQSSLPeerNamePropertyValidator(int domain) {
/* 5452 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean crossPropertyValidate(Object parent) throws JMSException {
/* 5457 */       if (Trace.isOn) {
/* 5458 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQSSLPeerNamePropertyValidator", "crossPropertyValidate(Object)", new Object[] { parent });
/*      */       }
/*      */       
/* 5461 */       if (((JmsPropertyContext)parent).getObjectProperty("XMSC_WMQ_SSL_PEER_NAME") != null && (
/* 5462 */         (JmsPropertyContext)parent).getObjectProperty("XMSC_WMQ_SSL_CIPHER_SUITE") == null) {
/* 5463 */         if (Trace.isOn) {
/* 5464 */           Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQSSLPeerNamePropertyValidator", "crossPropertyValidate(Object)", 
/* 5465 */               Boolean.valueOf(false), 1);
/*      */         }
/* 5467 */         return false;
/*      */       } 
/*      */       
/* 5470 */       if (Trace.isOn) {
/* 5471 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQSSLPeerNamePropertyValidator", "crossPropertyValidate(Object)", 
/* 5472 */             Boolean.valueOf(true), 2);
/*      */       }
/* 5474 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object valueP, Object propContext) throws JMSException {
/* 5480 */       Object value = valueP;
/*      */       
/* 5482 */       if (value == null) {
/* 5483 */         return true;
/*      */       }
/* 5485 */       value = convertToString(value);
/*      */ 
/*      */       
/* 5488 */       boolean traceRet1 = checkPeerName((String)value);
/* 5489 */       return traceRet1;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private boolean checkPeerName(String DN) {
/* 5499 */       if (Trace.isOn) {
/* 5500 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQSSLPeerNamePropertyValidator", "checkPeerName(String)", new Object[] { DN });
/*      */       }
/*      */       
/* 5503 */       int DNCLEAR = 1;
/* 5504 */       int DNSYMBOL = 2;
/* 5505 */       int DNVALUE = 3;
/* 5506 */       int DNQVALUE = 4;
/* 5507 */       int DNFINISHED = 5;
/*      */       
/* 5509 */       int state = 1;
/*      */       
/* 5511 */       String symbol = "";
/* 5512 */       String value = "";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 5522 */       if (DN == null || DN.equals("")) {
/* 5523 */         state = 5;
/*      */       }
/*      */       try {
/* 5526 */         for (int i = 0; i < DN.length(); i++) {
/* 5527 */           char ch = DN.charAt(i);
/* 5528 */           if (state == 1) {
/* 5529 */             if (ch == '"' || ch == ',' || ch == ';' || ch == '=') {
/* 5530 */               MQException traceRet1 = new MQException(2, 2399, this);
/* 5531 */               if (Trace.isOn) {
/* 5532 */                 Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQSSLPeerNamePropertyValidator", "checkPeerName(String)", (Throwable)traceRet1, 1);
/*      */               }
/*      */ 
/*      */               
/* 5536 */               throw traceRet1;
/*      */             } 
/* 5538 */             if (ch != ' ' && ch != '\t') {
/* 5539 */               symbol = symbol + ch;
/* 5540 */               state = 2;
/*      */             }
/*      */           
/*      */           }
/* 5544 */           else if (state == 2) {
/* 5545 */             if (ch == ' ' || ch == '"') {
/* 5546 */               MQException traceRet2 = new MQException(2, 2399, this);
/* 5547 */               if (Trace.isOn) {
/* 5548 */                 Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQSSLPeerNamePropertyValidator", "checkPeerName(String)", (Throwable)traceRet2, 2);
/*      */               }
/*      */ 
/*      */               
/* 5552 */               throw traceRet2;
/*      */             } 
/* 5554 */             if (ch == '=') {
/* 5555 */               value = "";
/* 5556 */               if (i + 1 >= DN.length()) {
/* 5557 */                 state = 3;
/*      */               
/*      */               }
/* 5560 */               else if (DN.charAt(i + 1) == '"') {
/* 5561 */                 i++;
/* 5562 */                 state = 4;
/*      */               } else {
/*      */                 
/* 5565 */                 state = 3;
/*      */               }
/*      */             
/*      */             } else {
/*      */               
/* 5570 */               symbol = symbol + ch;
/*      */             }
/*      */           
/*      */           }
/* 5574 */           else if (state == 3) {
/*      */ 
/*      */             
/* 5577 */             if ((ch == ',' || ch == ';') && (i == 0 || DN.charAt(i - 1) != '\\')) {
/* 5578 */               state = 1;
/* 5579 */               symbol = "";
/*      */             } else {
/*      */               
/* 5582 */               value = value + ch;
/*      */             }
/*      */           
/*      */           }
/* 5586 */           else if (state == 4) {
/* 5587 */             if (ch == '"' && (i == 0 || DN.charAt(i - 1) != '\\')) {
/* 5588 */               state = 5;
/* 5589 */               symbol = "";
/*      */             } else {
/*      */               
/* 5592 */               value = value + ch;
/*      */             }
/*      */           
/*      */           }
/* 5596 */           else if (state == 5) {
/* 5597 */             if (ch == ',' || ch == ';') {
/* 5598 */               state = 1;
/*      */             }
/* 5600 */             else if (ch != ' ' && ch != '\t') {
/* 5601 */               MQException traceRet3 = new MQException(2, 2399, this);
/* 5602 */               if (Trace.isOn) {
/* 5603 */                 Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQSSLPeerNamePropertyValidator", "checkPeerName(String)", (Throwable)traceRet3, 3);
/*      */               }
/*      */ 
/*      */               
/* 5607 */               throw traceRet3;
/*      */             } 
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
/*      */ 
/*      */         
/* 5621 */         if (state == 2 || state == 1) {
/* 5622 */           MQException traceRet4 = new MQException(2, 2399, this);
/* 5623 */           if (Trace.isOn) {
/* 5624 */             Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQSSLPeerNamePropertyValidator", "checkPeerName(String)", (Throwable)traceRet4, 4);
/*      */           }
/*      */ 
/*      */           
/* 5628 */           throw traceRet4;
/*      */         }
/*      */       
/* 5631 */       } catch (MQException e) {
/* 5632 */         if (Trace.isOn) {
/* 5633 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.factories.WMQSSLPeerNamePropertyValidator", "checkPeerName(String)", (Throwable)e);
/*      */         }
/*      */ 
/*      */         
/* 5637 */         if (Trace.isOn) {
/* 5638 */           Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQSSLPeerNamePropertyValidator", "checkPeerName(String)", 
/* 5639 */               Boolean.valueOf(false), 1);
/*      */         }
/* 5641 */         return false;
/*      */       } 
/* 5643 */       if (Trace.isOn) {
/* 5644 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQSSLPeerNamePropertyValidator", "checkPeerName(String)", 
/* 5645 */             Boolean.valueOf(true), 2);
/*      */       }
/* 5647 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 5652 */       if (Trace.isOn) {
/* 5653 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQSSLPeerNamePropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 5658 */       if (Trace.isOn) {
/* 5659 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQSSLPeerNamePropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQSSLResetCountPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -6355667917902267498L;
/*      */ 
/*      */     
/* 5673 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 5675 */       if (Trace.isOn) {
/* 5676 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQSSLResetCountPropertyValidator", "static()");
/*      */       }
/*      */       
/* 5679 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_SSL_KEY_RESETCOUNT");
/* 5680 */       domainsToPropertyNames.put(Integer.valueOf(1), "SSLRESETCOUNT");
/* 5681 */       domainsToPropertyNames.put(Integer.valueOf(2), "SRC");
/* 5682 */       if (Trace.isOn) {
/* 5683 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQSSLResetCountPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 5689 */       if (Trace.isOn) {
/* 5690 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQSSLResetCountPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 5693 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 5694 */         propertyValidators.put(entry.getValue(), new WMQSSLResetCountPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 5696 */       if (Trace.isOn) {
/* 5697 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQSSLResetCountPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQSSLResetCountPropertyValidator(int domain) {
/* 5708 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/* 5714 */       if (value == null)
/*      */       {
/*      */         
/* 5717 */         return true;
/*      */       }
/* 5719 */       return validateIntForRange(value, 0, 999999999);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean crossPropertyValidate(Object parent) throws JMSException {
/* 5724 */       if (Trace.isOn) {
/* 5725 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQSSLResetCountPropertyValidator", "crossPropertyValidate(Object)", new Object[] { parent });
/*      */       }
/*      */       
/* 5728 */       if (((JmsPropertyContext)parent).getIntProperty("XMSC_WMQ_SSL_KEY_RESETCOUNT") != 0 && (
/* 5729 */         (JmsPropertyContext)parent).getObjectProperty("XMSC_WMQ_SSL_CIPHER_SUITE") == null) {
/* 5730 */         if (Trace.isOn) {
/* 5731 */           Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQSSLResetCountPropertyValidator", "crossPropertyValidate(Object)", 
/* 5732 */               Boolean.valueOf(false), 1);
/*      */         }
/* 5734 */         return false;
/*      */       } 
/*      */       
/* 5737 */       if (Trace.isOn) {
/* 5738 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQSSLResetCountPropertyValidator", "crossPropertyValidate(Object)", 
/* 5739 */             Boolean.valueOf(true), 2);
/*      */       }
/* 5741 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object bytes) throws JMSException {
/* 5746 */       if (Trace.isOn) {
/* 5747 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQSSLResetCountPropertyValidator", "throwBadValueException(Object)", new Object[] { bytes });
/*      */       }
/*      */       
/* 5750 */       HashMap<String, Object> info = new HashMap<>();
/* 5751 */       info.put("XMSC_INSERT_VALUE", String.valueOf(bytes));
/* 5752 */       info.put("XMSC_INSERT_NAME", "sslResetCount");
/* 5753 */       JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/*      */       
/* 5755 */       if (Trace.isOn) {
/* 5756 */         Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQSSLResetCountPropertyValidator", "throwBadValueException(Object)", (Throwable)je);
/*      */       }
/*      */       
/* 5759 */       throw je;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQSSLSocketFactoryPropertyValidator
/*      */     extends WMQStandardValidators.WMQTrivialPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 1770397102389658575L;
/*      */     
/* 5770 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 5772 */       if (Trace.isOn) {
/* 5773 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQSSLSocketFactoryPropertyValidator", "static()");
/*      */       }
/*      */       
/* 5776 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_SSL_SOCKET_FACTORY");
/* 5777 */       if (Trace.isOn) {
/* 5778 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQSSLSocketFactoryPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 5784 */       if (Trace.isOn) {
/* 5785 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQSSLSocketFactoryPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 5788 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 5789 */         propertyValidators.put(entry.getValue(), new WMQSSLSocketFactoryPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 5791 */       if (Trace.isOn) {
/* 5792 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQSSLSocketFactoryPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQSSLSocketFactoryPropertyValidator(int domain) {
/* 5803 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean crossPropertyValidate(Object parent) throws JMSException {
/* 5808 */       if (Trace.isOn) {
/* 5809 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQSSLSocketFactoryPropertyValidator", "crossPropertyValidate(Object)", new Object[] { parent });
/*      */       }
/*      */       
/* 5812 */       if (((JmsPropertyContext)parent).getObjectProperty("XMSC_WMQ_SSL_SOCKET_FACTORY") != null && (
/* 5813 */         (JmsPropertyContext)parent).getObjectProperty("XMSC_WMQ_SSL_CIPHER_SUITE") == null) {
/* 5814 */         if (Trace.isOn) {
/* 5815 */           Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQSSLSocketFactoryPropertyValidator", "crossPropertyValidate(Object)", 
/*      */               
/* 5817 */               Boolean.valueOf(false), 1);
/*      */         }
/* 5819 */         return false;
/*      */       } 
/*      */       
/* 5822 */       if (Trace.isOn) {
/* 5823 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQSSLSocketFactoryPropertyValidator", "crossPropertyValidate(Object)", 
/* 5824 */             Boolean.valueOf(true), 2);
/*      */       }
/* 5826 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 5831 */       if (Trace.isOn) {
/* 5832 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQSSLSocketFactoryPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 5837 */       if (Trace.isOn) {
/* 5838 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQSSLSocketFactoryPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQStatusRefreshIntervalPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -7976792455381322958L;
/*      */ 
/*      */     
/* 5852 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 5854 */       if (Trace.isOn) {
/* 5855 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQStatusRefreshIntervalPropertyValidator", "static()");
/*      */       }
/*      */       
/* 5858 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_STATUS_REFRESH_INTERVAL");
/* 5859 */       domainsToPropertyNames.put(Integer.valueOf(1), "STATREFRESHINT");
/* 5860 */       domainsToPropertyNames.put(Integer.valueOf(2), "SRI");
/* 5861 */       if (Trace.isOn) {
/* 5862 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQStatusRefreshIntervalPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 5868 */       if (Trace.isOn) {
/* 5869 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQStatusRefreshIntervalPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 5872 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 5873 */         propertyValidators.put(entry.getValue(), new WMQStatusRefreshIntervalPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 5875 */       if (Trace.isOn) {
/* 5876 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQStatusRefreshIntervalPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQStatusRefreshIntervalPropertyValidator(int domain) {
/* 5887 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/* 5894 */       WMQConnectionFactory.checkNotQueueCF(parent, "setStatusRefreshInterval()");
/*      */       
/* 5896 */       return validateIntForRange(value, -1, 2147483647);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object interval) throws JMSException {
/* 5901 */       if (Trace.isOn) {
/* 5902 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQStatusRefreshIntervalPropertyValidator", "throwBadValueException(Object)", new Object[] { interval });
/*      */       }
/*      */ 
/*      */       
/* 5906 */       HashMap<String, Object> info = new HashMap<>();
/* 5907 */       info.put("XMSC_INSERT_VALUE", String.valueOf(interval));
/* 5908 */       info.put("XMSC_INSERT_NAME", "StatusRefreshInterval");
/* 5909 */       JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/*      */       
/* 5911 */       if (Trace.isOn) {
/* 5912 */         Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQStatusRefreshIntervalPropertyValidator", "throwBadValueException(Object)", (Throwable)je);
/*      */       }
/*      */ 
/*      */       
/* 5916 */       throw je;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQSubscriptionStorePropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -3149709136733446051L;
/*      */     
/* 5927 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 5929 */       if (Trace.isOn) {
/* 5930 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQSubscriptionStorePropertyValidator", "static()");
/*      */       }
/*      */       
/* 5933 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_SUBSCRIPTION_STORE");
/* 5934 */       domainsToPropertyNames.put(Integer.valueOf(1), "SUBSTORE");
/* 5935 */       domainsToPropertyNames.put(Integer.valueOf(2), "SS");
/* 5936 */       if (Trace.isOn) {
/* 5937 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQSubscriptionStorePropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 5943 */       if (Trace.isOn) {
/* 5944 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQSubscriptionStorePropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 5947 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 5948 */         propertyValidators.put(entry.getValue(), new WMQSubscriptionStorePropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 5950 */       if (Trace.isOn) {
/* 5951 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQSubscriptionStorePropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 5957 */     private static HashMap<Object, Object> valuesToCanonical = new HashMap<>(3, 1.0F);
/* 5958 */     private static Set<Object> validValues = new HashSet(3, 1.0F);
/*      */     static {
/* 5960 */       if (Trace.isOn) {
/* 5961 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQSubscriptionStorePropertyValidator", "static()");
/*      */       }
/*      */       
/* 5964 */       valuesToCanonical.put("MIGRATE", Integer.valueOf(2));
/* 5965 */       valuesToCanonical.put("QUEUE", Integer.valueOf(0));
/* 5966 */       valuesToCanonical.put("BROKER", Integer.valueOf(1));
/* 5967 */       validValues.addAll(valuesToCanonical.values());
/* 5968 */       if (Trace.isOn) {
/* 5969 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQSubscriptionStorePropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQSubscriptionStorePropertyValidator(int domain) {
/* 5979 */       super(domain, domainsToPropertyNames, valuesToCanonical);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/* 5986 */       WMQConnectionFactory.checkNotQueueCF(parent, "setSubscriptionStore()");
/*      */       
/* 5988 */       return validateIntBySet(value, validValues);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object flag) throws JMSException {
/* 5993 */       if (Trace.isOn) {
/* 5994 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQSubscriptionStorePropertyValidator", "throwBadValueException(Object)", new Object[] { flag });
/*      */       }
/*      */ 
/*      */       
/* 5998 */       HashMap<String, Object> info = new HashMap<>();
/* 5999 */       info.put("XMSC_INSERT_VALUE", String.valueOf(flag));
/* 6000 */       info.put("XMSC_INSERT_NAME", "subscriptionStore");
/* 6001 */       JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/*      */       
/* 6003 */       if (Trace.isOn) {
/* 6004 */         Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQSubscriptionStorePropertyValidator", "throwBadValueException(Object)", (Throwable)je);
/*      */       }
/*      */ 
/*      */       
/* 6008 */       throw je;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQSyncpointAllGetsPropertyValidator
/*      */     extends WMQStandardValidators.WMQBooleanPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 6573805368499716518L;
/*      */     
/* 6019 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 6021 */       if (Trace.isOn) {
/* 6022 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQSyncpointAllGetsPropertyValidator", "static()");
/*      */       }
/*      */       
/* 6025 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_SYNCPOINT_ALL_GETS");
/* 6026 */       domainsToPropertyNames.put(Integer.valueOf(1), "SYNCPOINTALLGETS");
/* 6027 */       domainsToPropertyNames.put(Integer.valueOf(2), "SPAG");
/* 6028 */       if (Trace.isOn) {
/* 6029 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQSyncpointAllGetsPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 6035 */       if (Trace.isOn) {
/* 6036 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQSyncpointAllGetsPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 6039 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 6040 */         propertyValidators.put(entry.getValue(), new WMQSyncpointAllGetsPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 6042 */       if (Trace.isOn) {
/* 6043 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQSyncpointAllGetsPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQSyncpointAllGetsPropertyValidator(int domain) {
/* 6054 */       super(domain, domainsToPropertyNames, yesNoToBoolean);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 6059 */       if (Trace.isOn) {
/* 6060 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQSyncpointAllGetsPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 6065 */       if (Trace.isOn) {
/* 6066 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQSyncpointAllGetsPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQTargetClientMatchingPropertyValidator
/*      */     extends WMQStandardValidators.WMQBooleanPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 5614110823978901569L;
/*      */ 
/*      */     
/* 6080 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 6082 */       if (Trace.isOn) {
/* 6083 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQTargetClientMatchingPropertyValidator", "static()");
/*      */       }
/*      */       
/* 6086 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_TARGET_CLIENT_MATCHING");
/* 6087 */       domainsToPropertyNames.put(Integer.valueOf(1), "TARGCLIENTMATCHING");
/* 6088 */       domainsToPropertyNames.put(Integer.valueOf(2), "TCM");
/* 6089 */       if (Trace.isOn) {
/* 6090 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQTargetClientMatchingPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 6096 */       if (Trace.isOn) {
/* 6097 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQTargetClientMatchingPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 6100 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 6101 */         propertyValidators.put(entry.getValue(), new WMQTargetClientMatchingPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 6103 */       if (Trace.isOn) {
/* 6104 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQTargetClientMatchingPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQTargetClientMatchingPropertyValidator(int domain) {
/* 6115 */       super(domain, domainsToPropertyNames, yesNoToBoolean);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 6120 */       if (Trace.isOn) {
/* 6121 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQTargetClientMatchingPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 6127 */       if (Trace.isOn) {
/* 6128 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQTargetClientMatchingPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQTemporaryModelPropertyValidator
/*      */     extends WMQStandardValidators.WMQQueueNamePropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 5032845991021006372L;
/*      */ 
/*      */ 
/*      */     
/* 6143 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 6145 */       if (Trace.isOn) {
/* 6146 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQTemporaryModelPropertyValidator", "static()");
/*      */       }
/*      */       
/* 6149 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_TEMPORARY_MODEL");
/* 6150 */       domainsToPropertyNames.put(Integer.valueOf(1), "TEMPMODEL");
/* 6151 */       domainsToPropertyNames.put(Integer.valueOf(2), "TM");
/* 6152 */       if (Trace.isOn) {
/* 6153 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQTemporaryModelPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 6159 */       if (Trace.isOn) {
/* 6160 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQTemporaryModelPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 6163 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 6164 */         propertyValidators.put(entry.getValue(), new WMQTemporaryModelPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 6166 */       if (Trace.isOn) {
/* 6167 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQTemporaryModelPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQTemporaryModelPropertyValidator(int domain) {
/* 6178 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/* 6185 */       WMQConnectionFactory.checkNotTopicCF(parent, "setTemporaryModel()");
/*      */       
/* 6187 */       boolean traceRet1 = super.validate(value, parent);
/* 6188 */       return traceRet1;
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 6193 */       if (Trace.isOn) {
/* 6194 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQTemporaryModelPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 6199 */       if (Trace.isOn) {
/* 6200 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQTemporaryModelPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQTempTopicPrefixPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 5325130931042923132L;
/*      */ 
/*      */     
/* 6214 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 6216 */       if (Trace.isOn) {
/* 6217 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQTempTopicPrefixPropertyValidator", "static()");
/*      */       }
/*      */       
/* 6220 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_TEMP_TOPIC_PREFIX");
/* 6221 */       domainsToPropertyNames.put(Integer.valueOf(1), "TEMPTOPICPREFIX");
/* 6222 */       domainsToPropertyNames.put(Integer.valueOf(2), "TTP");
/* 6223 */       if (Trace.isOn) {
/* 6224 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQTempTopicPrefixPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 6230 */       if (Trace.isOn) {
/* 6231 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQTempTopicPrefixPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 6234 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 6235 */         propertyValidators.put(entry.getValue(), new WMQTempTopicPrefixPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 6237 */       if (Trace.isOn) {
/* 6238 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQTempTopicPrefixPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQTempTopicPrefixPropertyValidator(int domain) {
/* 6249 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */     
/* 6253 */     private static final Pattern badPattern = Pattern.compile(".*((#+)|(\\++)|(/+)|(\\*+)|(\\?+)).*");
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object valueP, Object parent) throws JMSException {
/* 6258 */       Object value = valueP;
/*      */       
/* 6260 */       WMQConnectionFactory.checkNotQueueCF(parent, "setTempTopicPrefix()");
/*      */       
/* 6262 */       if (value == null)
/*      */       {
/* 6264 */         return true;
/*      */       }
/*      */       
/* 6267 */       value = convertToString(value);
/*      */       
/* 6269 */       if (badPattern.matcher((String)value).matches()) {
/* 6270 */         HashMap<String, Object> info = new HashMap<>();
/* 6271 */         info.put("XMSC_INSERT_VALUE", String.valueOf(value));
/* 6272 */         info.put("XMSC_INSERT_NAME", "tempTopicPrefix");
/* 6273 */         JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/*      */         
/* 6275 */         throw je;
/*      */       } 
/* 6277 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 6283 */       if (Trace.isOn) {
/* 6284 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQTempTopicPrefixPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 6289 */       if (Trace.isOn) {
/* 6290 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQTempTopicPrefixPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQTempQPrefixPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 2717973061599556214L;
/*      */ 
/*      */     
/* 6304 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 6306 */       if (Trace.isOn) {
/* 6307 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQTempQPrefixPropertyValidator", "static()");
/*      */       }
/* 6309 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_TEMP_Q_PREFIX");
/* 6310 */       domainsToPropertyNames.put(Integer.valueOf(1), "TEMPQPREFIX");
/* 6311 */       domainsToPropertyNames.put(Integer.valueOf(2), "TQP");
/* 6312 */       if (Trace.isOn) {
/* 6313 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQTempQPrefixPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 6318 */       if (Trace.isOn) {
/* 6319 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQTempQPrefixPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 6322 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 6323 */         propertyValidators.put(entry.getValue(), new WMQTempQPrefixPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 6325 */       if (Trace.isOn) {
/* 6326 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQTempQPrefixPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQTempQPrefixPropertyValidator(int domain) {
/* 6337 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object valueP, Object parent) throws JMSException {
/* 6343 */       Object value = valueP;
/*      */       
/* 6345 */       if (parent instanceof com.ibm.mq.jms.MQTopicConnectionFactory) {
/* 6346 */         HashMap<String, Object> inserts = new HashMap<>();
/* 6347 */         inserts.put("XMSC_INSERT_METHOD", "setTempQPrefix()");
/* 6348 */         inserts.put("XMSC_INSERT_TYPE", parent.getClass().getName());
/* 6349 */         JMSException je = (JMSException)NLSServices.createException("JMSMQ1112", inserts);
/* 6350 */         throw je;
/*      */       } 
/*      */       
/* 6353 */       if (value == null) {
/* 6354 */         value = "";
/*      */       }
/*      */       
/* 6357 */       String newTempQPrefix = convertToString(value);
/*      */       
/*      */       try {
/* 6360 */         if (newTempQPrefix == null || newTempQPrefix.equals(""))
/*      */         {
/*      */ 
/*      */ 
/*      */           
/* 6365 */           return true;
/*      */         }
/*      */         
/* 6368 */         int length = 0;
/* 6369 */         length = newTempQPrefix.length();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 6375 */         if (length < 2 || length > 33 || !newTempQPrefix.substring(newTempQPrefix.length() - 1, newTempQPrefix.length()).equals("*")) {
/*      */ 
/*      */           
/* 6378 */           String vstr = newTempQPrefix;
/*      */           
/* 6380 */           HashMap<String, Object> info = new HashMap<>();
/* 6381 */           info.put("XMSC_INSERT_VALUE", vstr);
/* 6382 */           info.put("XMSC_INSERT_NAME", "tempQPrefix");
/* 6383 */           JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/*      */           
/* 6385 */           throw je;
/*      */         } 
/* 6387 */         return true;
/*      */       }
/* 6389 */       catch (JMSException je) {
/* 6390 */         throw je;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 6397 */       if (Trace.isOn) {
/* 6398 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQTempQPrefixPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 6403 */       if (Trace.isOn) {
/* 6404 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQTempQPrefixPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQConnectionModePropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 6461105230365657215L;
/*      */ 
/*      */     
/* 6418 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 6420 */       if (Trace.isOn) {
/* 6421 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQConnectionModePropertyValidator", "static()");
/*      */       }
/*      */       
/* 6424 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_CONNECTION_MODE");
/* 6425 */       domainsToPropertyNames.put(Integer.valueOf(1), "TRANSPORT");
/* 6426 */       domainsToPropertyNames.put(Integer.valueOf(2), "TRAN");
/* 6427 */       if (Trace.isOn) {
/* 6428 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQConnectionModePropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 6434 */       if (Trace.isOn) {
/* 6435 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQConnectionModePropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 6438 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 6439 */         propertyValidators.put(entry.getValue(), new WMQConnectionModePropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 6441 */       if (Trace.isOn) {
/* 6442 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQConnectionModePropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 6448 */     private static HashMap<Object, Object> valuesToCanonical = new HashMap<>(5, 1.0F);
/*      */     static {
/* 6450 */       if (Trace.isOn) {
/* 6451 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQConnectionModePropertyValidator", "static()");
/*      */       }
/*      */       
/* 6454 */       valuesToCanonical.put("CLIENT", Integer.valueOf(1));
/* 6455 */       valuesToCanonical.put("BIND", Integer.valueOf(0));
/* 6456 */       valuesToCanonical.put("BINDINGS_THEN_CLIENT", Integer.valueOf(8));
/* 6457 */       valuesToCanonical.put("DIRECT", Integer.valueOf(2));
/* 6458 */       valuesToCanonical.put("DIRECTHTTP", Integer.valueOf(4));
/* 6459 */       if (Trace.isOn) {
/* 6460 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQConnectionModePropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQConnectionModePropertyValidator(int domain) {
/* 6470 */       super(domain, domainsToPropertyNames, valuesToCanonical);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object valueP, Object parent) throws JMSException {
/* 6476 */       Object value = valueP;
/*      */       
/* 6478 */       JmsPropertyContext parentPC = (JmsPropertyContext)parent;
/* 6479 */       if (!(value instanceof Integer)) {
/* 6480 */         value = convertToString(value);
/*      */       }
/*      */       
/* 6483 */       if (value instanceof Integer || value instanceof String) {
/*      */         int type;
/*      */         
/* 6486 */         if (value instanceof Integer) {
/* 6487 */           type = ((Integer)value).intValue();
/*      */         
/*      */         }
/* 6490 */         else if (this.mapperDomain == 1 || this.mapperDomain == 2) {
/*      */           
/* 6492 */           if (valuesToCanonical.containsKey(value)) {
/* 6493 */             type = ((Integer)valuesToCanonical.get(value)).intValue();
/*      */           } else {
/*      */             
/* 6496 */             return false;
/*      */           } 
/*      */         } else {
/*      */ 
/*      */           
/*      */           try {
/* 6502 */             type = Integer.parseInt((String)value);
/*      */           }
/* 6504 */           catch (NumberFormatException e) {
/* 6505 */             return false;
/*      */           } 
/*      */         } 
/*      */         
/*      */         try {
/*      */           Class<? extends Object> parentClass;
/* 6511 */           switch (type) {
/*      */             case 8:
/* 6513 */               return true;
/*      */ 
/*      */ 
/*      */             
/*      */             case 0:
/* 6518 */               if (CSSystem.currentPlatform().equals(CSSystem.Platform.OS_NSS)) {
/* 6519 */                 HashMap<String, String> inserts = new HashMap<>();
/* 6520 */                 inserts.put("XMSC_INSERT_VALUE", "bindings");
/* 6521 */                 inserts.put("XMSC_INSERT_PROPERTY", "Transport");
/* 6522 */                 JMSException jMSException = (JMSException)NLSServices.createException("JMSMQ1006", inserts);
/* 6523 */                 throw jMSException;
/*      */               } 
/*      */ 
/*      */             
/*      */             case 1:
/* 6528 */               if (type == 1) {
/* 6529 */                 int options = parentPC.getIntProperty("XMSC_WMQ_CONNECT_OPTIONS");
/* 6530 */                 if ((options & 0x8) != 0 || (options & 0x10) != 0) {
/*      */ 
/*      */                   
/* 6533 */                   HashMap<String, Object> hashMap = new HashMap<>();
/* 6534 */                   hashMap.put("XMSC_INSERT_VALUE", String.valueOf(options));
/* 6535 */                   hashMap.put("XMSC_INSERT_NAME", "XMSC_WMQ_CONNECT_OPTIONS");
/* 6536 */                   JMSException jMSException = (JMSException)NLSServices.createException("JMSFMQ1008", hashMap);
/*      */                   
/* 6538 */                   throw jMSException;
/*      */                 } 
/*      */               } 
/*      */               
/* 6542 */               return true;
/*      */ 
/*      */ 
/*      */             
/*      */             case 2:
/*      */             case 4:
/* 6548 */               if (CSSystem.currentPlatform().equals(CSSystem.Platform.OS_NSS)) {
/* 6549 */                 HashMap<String, String> inserts = new HashMap<>();
/* 6550 */                 inserts.put("XMSC_INSERT_VALUE", "WMQConstants.RTT_CM_TCP");
/* 6551 */                 inserts.put("XMSC_INSERT_PROPERTY", "XMSC_WMQ_CONNECTION_MODE");
/* 6552 */                 JMSException jMSException = (JMSException)NLSServices.createException("JMSMQ1006", inserts);
/* 6553 */                 throw jMSException;
/*      */               } 
/*      */ 
/*      */               
/* 6557 */               parentClass = (Class)parent.getClass();
/*      */               
/* 6559 */               if (parentClass.equals(MQQueueConnectionFactory.class) || parentClass.equals(MQXAConnectionFactory.class) || parentClass
/* 6560 */                 .equals(MQXAQueueConnectionFactory.class) || parentClass.equals(MQXATopicConnectionFactory.class)) {
/*      */ 
/*      */ 
/*      */                 
/* 6564 */                 HashMap<String, String> hashMap = new HashMap<>();
/* 6565 */                 hashMap.put("XMSC_INSERT_VALUE", String.valueOf(type));
/* 6566 */                 hashMap.put("XMSC_INSERT_NAME", "transportType");
/* 6567 */                 JMSException jMSException = (JMSException)NLSServices.createException("JMSFMQ1006", hashMap);
/*      */                 
/* 6569 */                 throw jMSException;
/*      */               } 
/*      */               
/* 6572 */               return true;
/*      */           } 
/*      */           
/* 6575 */           HashMap<String, Object> info = new HashMap<>();
/* 6576 */           info.put("XMSC_INSERT_VALUE", String.valueOf(type));
/* 6577 */           info.put("XMSC_INSERT_NAME", "transportType");
/* 6578 */           JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/*      */           
/* 6580 */           throw je;
/*      */         
/*      */         }
/* 6583 */         catch (JMSException je) {
/* 6584 */           throw je;
/*      */         } 
/*      */       } 
/* 6587 */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 6592 */       if (Trace.isOn) {
/* 6593 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQConnectionModePropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 6598 */       if (Trace.isOn) {
/* 6599 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQConnectionModePropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQConnectionPoolingPropertyValidator
/*      */     extends WMQStandardValidators.WMQBooleanPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 277824117667162657L;
/*      */ 
/*      */     
/* 6613 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 6615 */       if (Trace.isOn) {
/* 6616 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQConnectionPoolingPropertyValidator", "static()");
/*      */       }
/*      */       
/* 6619 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_USE_CONNECTION_POOLING");
/* 6620 */       domainsToPropertyNames.put(Integer.valueOf(1), "USECONNPOOLING");
/* 6621 */       domainsToPropertyNames.put(Integer.valueOf(2), "UCP");
/* 6622 */       if (Trace.isOn) {
/* 6623 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQConnectionPoolingPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 6629 */       if (Trace.isOn) {
/* 6630 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQConnectionPoolingPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 6633 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 6634 */         propertyValidators.put(entry.getValue(), new WMQConnectionPoolingPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 6636 */       if (Trace.isOn) {
/* 6637 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQConnectionPoolingPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQConnectionPoolingPropertyValidator(int domain) {
/* 6648 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 6653 */       if (Trace.isOn) {
/* 6654 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQConnectionPoolingPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 6660 */       if (Trace.isOn) {
/* 6661 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQConnectionPoolingPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQWildcardFormatPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 6686620470291229589L;
/*      */ 
/*      */     
/* 6675 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 6677 */       if (Trace.isOn) {
/* 6678 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQWildcardFormatPropertyValidator", "static()");
/*      */       }
/*      */       
/* 6681 */       domainsToPropertyNames.put(Integer.valueOf(4), "wildcardFormat");
/* 6682 */       domainsToPropertyNames.put(Integer.valueOf(1), "WILDCARDFORMAT");
/* 6683 */       domainsToPropertyNames.put(Integer.valueOf(2), "WCFMT");
/* 6684 */       if (Trace.isOn) {
/* 6685 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQWildcardFormatPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 6691 */       if (Trace.isOn) {
/* 6692 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQWildcardFormatPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 6695 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 6696 */         propertyValidators.put(entry.getValue(), new WMQWildcardFormatPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 6698 */       if (Trace.isOn) {
/* 6699 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQWildcardFormatPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 6705 */     private static HashMap<Object, Object> valuesToCanonical = new HashMap<>(2, 1.0F);
/*      */     static {
/* 6707 */       if (Trace.isOn) {
/* 6708 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQWildcardFormatPropertyValidator", "static()");
/*      */       }
/*      */       
/* 6711 */       valuesToCanonical.put("CHAR_ONLY", Integer.valueOf(1));
/* 6712 */       valuesToCanonical.put("TOPIC_ONLY", Integer.valueOf(0));
/* 6713 */       if (Trace.isOn) {
/* 6714 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQWildcardFormatPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQWildcardFormatPropertyValidator(int domain) {
/* 6724 */       super(domain, domainsToPropertyNames, valuesToCanonical);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/* 6730 */       return validateIntForRange(value, 0, 2);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object intval) throws JMSException {
/* 6735 */       if (Trace.isOn) {
/* 6736 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQWildcardFormatPropertyValidator", "throwBadValueException(Object)", new Object[] { intval });
/*      */       }
/*      */       
/* 6739 */       HashMap<String, Object> info = new HashMap<>();
/* 6740 */       info.put("XMSC_INSERT_VALUE", String.valueOf(intval));
/* 6741 */       info.put("XMSC_INSERT_NAME", "wildcardFormat");
/* 6742 */       JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/*      */       
/* 6744 */       if (Trace.isOn) {
/* 6745 */         Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQWildcardFormatPropertyValidator", "throwBadValueException(Object)", (Throwable)je);
/*      */       }
/*      */ 
/*      */       
/* 6749 */       throw je;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQConnectionTypeNamePropertyValidator
/*      */     extends WMQStandardValidators.WMQStringPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -6292739949362401746L;
/*      */     
/* 6760 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 6762 */       if (Trace.isOn) {
/* 6763 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQConnectionTypeNamePropertyValidator", "static()");
/*      */       }
/*      */       
/* 6766 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_CONNECTION_TYPE_NAME");
/* 6767 */       if (Trace.isOn) {
/* 6768 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQConnectionTypeNamePropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 6774 */       if (Trace.isOn) {
/* 6775 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQConnectionTypeNamePropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 6778 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 6779 */         propertyValidators.put(entry.getValue(), new WMQConnectionTypeNamePropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 6781 */       if (Trace.isOn) {
/* 6782 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQConnectionTypeNamePropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQConnectionTypeNamePropertyValidator(int domain) {
/* 6793 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 6798 */       if (Trace.isOn) {
/* 6799 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQConnectionTypeNamePropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 6805 */       if (Trace.isOn) {
/* 6806 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQConnectionTypeNamePropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQConnectionTypePropertyValidator
/*      */     extends WMQStandardValidators.WMQIntPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 6735356794678569616L;
/*      */ 
/*      */ 
/*      */     
/* 6821 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 6823 */       if (Trace.isOn) {
/* 6824 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQConnectionTypePropertyValidator", "static()");
/*      */       }
/*      */       
/* 6827 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_CONNECTION_TYPE");
/* 6828 */       if (Trace.isOn) {
/* 6829 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQConnectionTypePropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 6835 */       if (Trace.isOn) {
/* 6836 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQConnectionTypePropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 6839 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 6840 */         propertyValidators.put(entry.getValue(), new WMQConnectionTypePropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 6842 */       if (Trace.isOn) {
/* 6843 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQConnectionTypePropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQConnectionTypePropertyValidator(int domain) {
/* 6854 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 6859 */       if (Trace.isOn) {
/* 6860 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQConnectionTypePropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 6865 */       if (Trace.isOn) {
/* 6866 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQConnectionTypePropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQPasswordPropertyValidator
/*      */     extends WMQStandardValidators.WMQStringPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 7715077688644289226L;
/*      */ 
/*      */     
/* 6880 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 6882 */       if (Trace.isOn) {
/* 6883 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQPasswordPropertyValidator", "static()");
/*      */       }
/* 6885 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_PASSWORD");
/* 6886 */       if (Trace.isOn) {
/* 6887 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQPasswordPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 6892 */       if (Trace.isOn) {
/* 6893 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQPasswordPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 6896 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 6897 */         propertyValidators.put(entry.getValue(), new WMQPasswordPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 6899 */       if (Trace.isOn) {
/* 6900 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQPasswordPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQPasswordPropertyValidator(int domain) {
/* 6911 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 6916 */       if (Trace.isOn) {
/* 6917 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQPasswordPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 6922 */       if (Trace.isOn) {
/* 6923 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQPasswordPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQUserIDPropertyValidator
/*      */     extends WMQStandardValidators.WMQStringPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -6338751396776206443L;
/*      */ 
/*      */     
/* 6937 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 6939 */       if (Trace.isOn) {
/* 6940 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQUserIDPropertyValidator", "static()");
/*      */       }
/* 6942 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_USERID");
/* 6943 */       if (Trace.isOn) {
/* 6944 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQUserIDPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 6949 */       if (Trace.isOn) {
/* 6950 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQUserIDPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 6953 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 6954 */         propertyValidators.put(entry.getValue(), new WMQUserIDPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 6956 */       if (Trace.isOn) {
/* 6957 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQUserIDPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQUserIDPropertyValidator(int domain) {
/* 6968 */       super(domain, domainsToPropertyNames);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 6973 */       if (Trace.isOn) {
/* 6974 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQUserIDPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 6979 */       if (Trace.isOn) {
/* 6980 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQUserIDPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQAsyncExceptionsPropertyValidator
/*      */     extends WMQStandardValidators.WMQPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = 5712381868077510247L;
/*      */ 
/*      */     
/* 6994 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 6996 */       if (Trace.isOn) {
/* 6997 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQAsyncExceptionsPropertyValidator", "static()");
/*      */       }
/*      */       
/* 7000 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_ASYNC_EXCEPTIONS");
/* 7001 */       domainsToPropertyNames.put(Integer.valueOf(1), "ASYNCEXCEPTION");
/* 7002 */       domainsToPropertyNames.put(Integer.valueOf(2), "AEX");
/* 7003 */       if (Trace.isOn) {
/* 7004 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQAsyncExceptionsPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 7010 */       if (Trace.isOn) {
/* 7011 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQAsyncExceptionsPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 7014 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 7015 */         propertyValidators.put(entry.getValue(), new WMQAsyncExceptionsPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 7017 */       if (Trace.isOn) {
/* 7018 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQAsyncExceptionsPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 7024 */     private static HashMap<Object, Object> valuesToCanonical = new HashMap<>(2, 1.0F);
/* 7025 */     private static Set<Object> validValues = new HashSet(2, 1.0F);
/*      */     static {
/* 7027 */       if (Trace.isOn) {
/* 7028 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQAsyncExceptionsPropertyValidator", "static()");
/*      */       }
/*      */       
/* 7031 */       valuesToCanonical.put("ALL", Integer.valueOf(-1));
/* 7032 */       valuesToCanonical.put("CONNECTIONBROKEN", Integer.valueOf(1));
/* 7033 */       validValues.addAll(valuesToCanonical.values());
/* 7034 */       if (Trace.isOn) {
/* 7035 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQAsyncExceptionsPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQAsyncExceptionsPropertyValidator(int domain) {
/* 7045 */       super(domain, domainsToPropertyNames, valuesToCanonical);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/* 7051 */       return validateIntBySet(value, validValues);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object asyncflags) throws JMSException {
/* 7056 */       if (Trace.isOn) {
/* 7057 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQAsyncExceptionsPropertyValidator", "throwBadValueException(Object)", new Object[] { asyncflags });
/*      */       }
/*      */       
/* 7060 */       HashMap<String, Object> info = new HashMap<>();
/* 7061 */       info.put("XMSC_INSERT_VALUE", String.valueOf(asyncflags));
/* 7062 */       info.put("XMSC_INSERT_NAME", "AsyncExceptions");
/* 7063 */       JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/*      */       
/* 7065 */       if (Trace.isOn) {
/* 7066 */         Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQAsyncExceptionsPropertyValidator", "throwBadValueException(Object)", (Throwable)je);
/*      */       }
/*      */ 
/*      */       
/* 7070 */       throw je;
/*      */     }
/*      */   }
/*      */   
/*      */   static class WMQClientReconnectionOptionsPropertyValidator
/*      */     extends WMQStandardValidators.WMQIntPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -542695826704636159L;
/* 7078 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 7080 */       if (Trace.isOn) {
/* 7081 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQClientReconnectionOptionsPropertyValidator", "static()");
/*      */       }
/*      */ 
/*      */       
/* 7085 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_CLIENT_RECONNECT_OPTIONS");
/* 7086 */       domainsToPropertyNames.put(Integer.valueOf(1), "CLIENTRECONNECTOPTIONS");
/* 7087 */       domainsToPropertyNames.put(Integer.valueOf(2), "CROPT");
/* 7088 */       if (Trace.isOn) {
/* 7089 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQClientReconnectionOptionsPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 7096 */       if (Trace.isOn) {
/* 7097 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQClientReconnectionOptionsPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */ 
/*      */       
/* 7101 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 7102 */         propertyValidators.put(entry.getValue(), new WMQClientReconnectionOptionsPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 7104 */       if (Trace.isOn) {
/* 7105 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQClientReconnectionOptionsPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 7112 */     private static HashMap<Object, Object> valuesToCanonical = new HashMap<>(4, 1.0F);
/* 7113 */     private static Set<Object> validValues = new HashSet(4, 1.0F);
/*      */     static {
/* 7115 */       if (Trace.isOn) {
/* 7116 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQClientReconnectionOptionsPropertyValidator", "static()");
/*      */       }
/*      */ 
/*      */       
/* 7120 */       valuesToCanonical.put("ANY", Integer.valueOf(16777216));
/* 7121 */       valuesToCanonical.put("QMGR", Integer.valueOf(67108864));
/* 7122 */       valuesToCanonical.put("ASDEF", Integer.valueOf(0));
/* 7123 */       valuesToCanonical.put("DISABLED", Integer.valueOf(33554432));
/* 7124 */       validValues.addAll(valuesToCanonical.values());
/* 7125 */       if (Trace.isOn) {
/* 7126 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQClientReconnectionOptionsPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQClientReconnectionOptionsPropertyValidator(int domain) {
/* 7134 */       super(domain, domainsToPropertyNames, valuesToCanonical);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/* 7141 */       return validateIntBySet(value, validValues);
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object cropt) throws JMSException {
/* 7146 */       if (Trace.isOn) {
/* 7147 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQClientReconnectionOptionsPropertyValidator", "throwBadValueException(Object)", new Object[] { cropt });
/*      */       }
/*      */ 
/*      */       
/* 7151 */       HashMap<String, Object> info = new HashMap<>();
/* 7152 */       info.put("XMSC_INSERT_VALUE", String.valueOf(cropt));
/* 7153 */       info.put("XMSC_INSERT_NAME", "XMSC_WMQ_CLIENT_RECONNECT_OPTIONS");
/* 7154 */       JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/*      */       
/* 7156 */       if (Trace.isOn) {
/* 7157 */         Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQClientReconnectionOptionsPropertyValidator", "throwBadValueException(Object)", (Throwable)je);
/*      */       }
/*      */ 
/*      */       
/* 7161 */       throw je;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static class WMQClientReconnectionTimeoutPropertyValidator
/*      */     extends WMQStandardValidators.WMQIntPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -542695826704636159L;
/*      */     
/* 7171 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 7173 */       if (Trace.isOn) {
/* 7174 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQClientReconnectionTimeoutPropertyValidator", "static()");
/*      */       }
/*      */ 
/*      */       
/* 7178 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_CLIENT_RECONNECT_TIMEOUT");
/* 7179 */       if (Trace.isOn) {
/* 7180 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQClientReconnectionTimeoutPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 7187 */       if (Trace.isOn) {
/* 7188 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQClientReconnectionTimeoutPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */ 
/*      */       
/* 7192 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 7193 */         propertyValidators.put(entry.getValue(), new WMQClientReconnectionTimeoutPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 7195 */       if (Trace.isOn) {
/* 7196 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQClientReconnectionTimeoutPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQClientReconnectionTimeoutPropertyValidator(int domain) {
/* 7204 */       super(domain, domainsToPropertyNames);
/* 7205 */       if (Trace.isOn)
/* 7206 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQClientReconnectionTimeoutPropertyValidator", "<init>(int)", new Object[] {
/*      */               
/* 7208 */               Integer.valueOf(domain)
/*      */             }); 
/* 7210 */       if (Trace.isOn) {
/* 7211 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQClientReconnectionTimeoutPropertyValidator", "<init>(int)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/* 7220 */       if (Trace.isOn) {
/* 7221 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQClientReconnectionTimeoutPropertyValidator", "validate(Object,Object)", new Object[] { value, parent });
/*      */       }
/*      */ 
/*      */       
/* 7225 */       if (!super.validate(value, parent)) {
/* 7226 */         if (Trace.isOn) {
/* 7227 */           Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQClientReconnectionTimeoutPropertyValidator", "validate(Object,Object)", 
/*      */               
/* 7229 */               Boolean.valueOf(false), 1);
/*      */         }
/* 7231 */         return false;
/*      */       } 
/* 7233 */       if (((Integer)value).intValue() < 0) {
/* 7234 */         if (Trace.isOn) {
/* 7235 */           Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQClientReconnectionTimeoutPropertyValidator", "validate(Object,Object)", 
/*      */               
/* 7237 */               Boolean.valueOf(false), 2);
/*      */         }
/* 7239 */         return false;
/*      */       } 
/* 7241 */       if (Trace.isOn) {
/* 7242 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQClientReconnectionTimeoutPropertyValidator", "validate(Object,Object)", 
/*      */             
/* 7244 */             Boolean.valueOf(true), 3);
/*      */       }
/* 7246 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 7251 */       if (Trace.isOn) {
/* 7252 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQClientReconnectionTimeoutPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 7258 */       if (Trace.isOn) {
/* 7259 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQClientReconnectionTimeoutPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQConnectionNameListPropertyValidator
/*      */     extends WMQStandardValidators.WMQStringPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -542695826704636159L;
/*      */ 
/*      */     
/* 7272 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 7274 */       if (Trace.isOn) {
/* 7275 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQConnectionNameListPropertyValidator", "static()");
/*      */       }
/*      */       
/* 7278 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_WMQ_CONNECTION_NAME_LIST_INT");
/* 7279 */       domainsToPropertyNames.put(Integer.valueOf(1), "CONNECTIONNAMELIST");
/* 7280 */       domainsToPropertyNames.put(Integer.valueOf(2), "CNLIST");
/* 7281 */       domainsToPropertyNames.put(Integer.valueOf(5), "XMSC_WMQ_CONNECTION_NAME_LIST");
/* 7282 */       if (Trace.isOn) {
/* 7283 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQConnectionNameListPropertyValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 7289 */       if (Trace.isOn) {
/* 7290 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQConnectionNameListPropertyValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 7293 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 7294 */         propertyValidators.put(entry.getValue(), new WMQConnectionNameListPropertyValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 7296 */       if (Trace.isOn) {
/* 7297 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQConnectionNameListPropertyValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQConnectionNameListPropertyValidator(int domain) {
/* 7305 */       super(domain, domainsToPropertyNames);
/* 7306 */       if (Trace.isOn)
/* 7307 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQConnectionNameListPropertyValidator", "<init>(int)", new Object[] {
/*      */               
/* 7309 */               Integer.valueOf(domain)
/*      */             }); 
/* 7311 */       if (Trace.isOn) {
/* 7312 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQConnectionNameListPropertyValidator", "<init>(int)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQValidationInterface.WMQPropertyValidatorDatatype fromCanonical(JmsPropertyContext context, String keyIn, Object valueIn) {
/* 7320 */       if (Trace.isOn) {
/* 7321 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQConnectionNameListPropertyValidator", "fromCanonical(JmsPropertyContext,String,Object)", new Object[] { context, keyIn, valueIn });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 7326 */       WMQValidationInterface.WMQPropertyValidatorDatatype traceRet1 = fromCanonical(keyIn, valueIn);
/* 7327 */       if (Trace.isOn) {
/* 7328 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQConnectionNameListPropertyValidator", "fromCanonical(JmsPropertyContext,String,Object)", traceRet1);
/*      */       }
/*      */ 
/*      */       
/* 7332 */       return traceRet1;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQValidationInterface.WMQPropertyValidatorDatatype fromCanonical(String keyIn, Object valueIn) {
/* 7338 */       if (Trace.isOn) {
/* 7339 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQConnectionNameListPropertyValidator", "fromCanonical(String,Object)", new Object[] { keyIn, valueIn });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 7344 */       if (this.mapperDomain == 4 && 
/* 7345 */         keyIn.equalsIgnoreCase("XMSC_WMQ_CONNECTION_NAME_LIST_INT")) {
/*      */         
/* 7347 */         WMQValidationInterface.WMQPropertyValidatorDatatype traceRet1 = new WMQValidationInterface.WMQPropertyValidatorDatatype(keyIn, valueIn);
/* 7348 */         if (Trace.isOn) {
/* 7349 */           Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQConnectionNameListPropertyValidator", "fromCanonical(String,Object)", traceRet1, 1);
/*      */         }
/*      */ 
/*      */         
/* 7353 */         return traceRet1;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 7358 */       StringBuffer buffer = new StringBuffer();
/*      */       
/* 7360 */       ArrayList<WMQConnectionName> list = (ArrayList<WMQConnectionName>)valueIn;
/* 7361 */       Iterator<WMQConnectionName> iterator = list.iterator();
/* 7362 */       while (iterator.hasNext()) {
/* 7363 */         WMQConnectionName wcn = iterator.next();
/* 7364 */         buffer.append(",");
/* 7365 */         buffer.append(wcn);
/*      */       } 
/*      */ 
/*      */       
/* 7369 */       buffer.deleteCharAt(0);
/* 7370 */       WMQValidationInterface.WMQPropertyValidatorDatatype datatype = new WMQValidationInterface.WMQPropertyValidatorDatatype(keyIn, buffer.toString());
/*      */       
/* 7372 */       if (Trace.isOn) {
/* 7373 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQConnectionNameListPropertyValidator", "fromCanonical(String,Object)", datatype, 2);
/*      */       }
/*      */ 
/*      */       
/* 7377 */       return datatype;
/*      */     }
/*      */ 
/*      */     
/*      */     public WMQValidationInterface.WMQPropertyValidatorDatatype toCanonical(JmsPropertyContext context, String keyIn, Object valueIn) {
/* 7382 */       if (Trace.isOn) {
/* 7383 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQConnectionNameListPropertyValidator", "toCanonical(JmsPropertyContext,String,Object)", new Object[] { context, keyIn, valueIn });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 7388 */       WMQValidationInterface.WMQPropertyValidatorDatatype traceRet1 = toCanonical(keyIn, valueIn);
/* 7389 */       if (Trace.isOn) {
/* 7390 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQConnectionNameListPropertyValidator", "toCanonical(JmsPropertyContext,String,Object)", traceRet1);
/*      */       }
/*      */ 
/*      */       
/* 7394 */       return traceRet1;
/*      */     }
/*      */     
/*      */     public WMQValidationInterface.WMQPropertyValidatorDatatype toCanonical(String keyIn, Object valueInP) {
/*      */       WMQConnectionNameList list;
/* 7399 */       if (Trace.isOn) {
/* 7400 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQConnectionNameListPropertyValidator", "toCanonical(String,Object)", new Object[] { keyIn, valueInP });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 7405 */       Object valueIn = valueInP;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 7415 */       if (valueIn == null || (valueIn instanceof String && ((String)valueIn).trim().equals(""))) {
/* 7416 */         valueIn = "localhost(1414)";
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 7421 */       if (valueIn instanceof WMQConnectionNameList) {
/* 7422 */         list = (WMQConnectionNameList)valueIn;
/*      */       } else {
/*      */         
/* 7425 */         list = new WMQConnectionNameList();
/*      */         
/* 7427 */         String[] entries = ((String)valueIn).trim().split(",");
/* 7428 */         for (String entry : entries) {
/*      */           
/* 7430 */           String[] singleEntry = entry.split("\\(|\\)");
/*      */           
/* 7432 */           assert singleEntry.length == 1 || singleEntry.length == 2;
/*      */ 
/*      */           
/* 7435 */           if (singleEntry.length == 2) {
/* 7436 */             int port = Integer.parseInt(singleEntry[1].trim());
/* 7437 */             WMQConnectionName wcn = new WMQConnectionName(singleEntry[0], port);
/* 7438 */             list.add(wcn);
/*      */           }
/* 7440 */           else if (singleEntry.length == 1) {
/* 7441 */             WMQConnectionName wcn = new WMQConnectionName(singleEntry[0], 1414);
/* 7442 */             list.add(wcn);
/*      */           } 
/*      */         } 
/*      */       } 
/* 7446 */       WMQValidationInterface.WMQPropertyValidatorDatatype datatype = new WMQValidationInterface.WMQPropertyValidatorDatatype("XMSC_WMQ_CONNECTION_NAME_LIST_INT", list);
/*      */       
/* 7448 */       if (Trace.isOn) {
/* 7449 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQConnectionNameListPropertyValidator", "toCanonical(String,Object)", datatype);
/*      */       }
/*      */ 
/*      */       
/* 7453 */       return datatype;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean validate(Object value, Object parent) throws JMSException {
/* 7458 */       if (Trace.isOn) {
/* 7459 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQConnectionNameListPropertyValidator", "validate(Object,Object)", new Object[] { value, parent });
/*      */       }
/*      */ 
/*      */       
/* 7463 */       if (!super.validate(value, parent)) {
/* 7464 */         if (Trace.isOn) {
/* 7465 */           Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQConnectionNameListPropertyValidator", "validate(Object,Object)", 
/*      */               
/* 7467 */               Boolean.valueOf(false), 1);
/*      */         }
/* 7469 */         return false;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 7475 */       if (value == null || !(value instanceof String) || value.equals("")) {
/* 7476 */         if (Trace.isOn) {
/* 7477 */           Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQConnectionNameListPropertyValidator", "validate(Object,Object)", 
/*      */               
/* 7479 */               Boolean.valueOf(true), 2);
/*      */         }
/* 7481 */         return true;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 7489 */       String[] hostPortPairs = ((String)value).split(",");
/*      */       
/* 7491 */       for (String hostPort : hostPortPairs) {
/* 7492 */         hostPort = hostPort.trim();
/* 7493 */         int firstParen = hostPort.indexOf('(');
/* 7494 */         if (firstParen != -1) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 7499 */           int hostPortLen = hostPort.length();
/* 7500 */           if (hostPort.charAt(hostPortLen - 1) != ')') {
/*      */             
/* 7502 */             if (Trace.isOn) {
/* 7503 */               Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQConnectionNameListPropertyValidator", "validate(Object,Object)", 
/*      */                   
/* 7505 */                   Boolean.valueOf(false), 3);
/*      */             }
/* 7507 */             return false;
/*      */           } 
/* 7509 */           String portString = hostPort.substring(firstParen + 1, hostPortLen - 1);
/*      */           try {
/* 7511 */             int port = Integer.parseInt(portString);
/* 7512 */             if (port <= 0)
/*      */             {
/* 7514 */               if (Trace.isOn) {
/* 7515 */                 Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQConnectionNameListPropertyValidator", "validate(Object,Object)", 
/*      */                     
/* 7517 */                     Boolean.valueOf(false), 4);
/*      */               }
/* 7519 */               return false;
/*      */             }
/*      */           
/* 7522 */           } catch (NumberFormatException nfe) {
/* 7523 */             if (Trace.isOn) {
/* 7524 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.factories.WMQConnectionNameListPropertyValidator", "validate(Object,Object)", nfe);
/*      */             }
/*      */ 
/*      */ 
/*      */             
/* 7529 */             if (Trace.isOn) {
/* 7530 */               Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQConnectionNameListPropertyValidator", "validate(Object,Object)", 
/*      */                   
/* 7532 */                   Boolean.valueOf(false), 5);
/*      */             }
/* 7534 */             return false;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 7539 */       if (Trace.isOn) {
/* 7540 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQConnectionNameListPropertyValidator", "validate(Object,Object)", 
/*      */             
/* 7542 */             Boolean.valueOf(true), 6);
/*      */       }
/* 7544 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 7549 */       if (Trace.isOn) {
/* 7550 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQConnectionNameListPropertyValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 7556 */       if (Trace.isOn) {
/* 7557 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQConnectionNameListPropertyValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static class WMQUserAuthenticationMqcspValidator
/*      */     extends WMQStandardValidators.WMQBooleanPropertyValidator
/*      */   {
/*      */     private static final long serialVersionUID = -6165806597073912258L;
/*      */ 
/*      */     
/* 7570 */     private static HashMap<Integer, String> domainsToPropertyNames = new HashMap<>(3, 1.0F);
/*      */     static {
/* 7572 */       if (Trace.isOn) {
/* 7573 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQUserAuthenticationMqcspValidator", "static()");
/*      */       }
/*      */       
/* 7576 */       domainsToPropertyNames.put(Integer.valueOf(4), "XMSC_USER_AUTHENTICATION_MQCSP");
/* 7577 */       domainsToPropertyNames.put(Integer.valueOf(1), "USERAUTHMQCSP");
/* 7578 */       domainsToPropertyNames.put(Integer.valueOf(2), "UAMQ");
/* 7579 */       if (Trace.isOn) {
/* 7580 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQUserAuthenticationMqcspValidator", "static()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     static void register(Map<String, WMQStandardValidators.WMQPropertyValidator> propertyValidators) {
/* 7586 */       if (Trace.isOn) {
/* 7587 */         Trace.entry("com.ibm.msg.client.wmq.factories.WMQUserAuthenticationMqcspValidator", "register(Map<String , WMQPropertyValidator>)", new Object[] { propertyValidators });
/*      */       }
/*      */       
/* 7590 */       for (Map.Entry<Integer, String> entry : domainsToPropertyNames.entrySet()) {
/* 7591 */         propertyValidators.put(entry.getValue(), new WMQUserAuthenticationMqcspValidator(((Integer)entry.getKey()).intValue()));
/*      */       }
/* 7593 */       if (Trace.isOn) {
/* 7594 */         Trace.exit("com.ibm.msg.client.wmq.factories.WMQUserAuthenticationMqcspValidator", "register(Map<String , WMQPropertyValidator>)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WMQUserAuthenticationMqcspValidator(int domain) {
/* 7602 */       super(domain, domainsToPropertyNames, yesNoToBoolean);
/* 7603 */       if (Trace.isOn)
/* 7604 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQUserAuthenticationMqcspValidator", "<init>(int)", new Object[] {
/* 7605 */               Integer.valueOf(domain)
/*      */             }); 
/* 7607 */       if (Trace.isOn) {
/* 7608 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQUserAuthenticationMqcspValidator", "<init>(int)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void throwBadValueException(Object value) throws JMSException {
/* 7616 */       if (Trace.isOn) {
/* 7617 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQUserAuthenticationMqcspValidator", "throwBadValueException(Object)", new Object[] { value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 7622 */       if (Trace.isOn) {
/* 7623 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQUserAuthenticationMqcspValidator", "throwBadValueException(Object)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static boolean forceDontUseJmqiWorkerThread = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final String forceDontUseSharedHconnProperty = "com.ibm.msg.client.wmq.internal.forceDontUseSharedHconn";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static boolean forceDontUseSharedHconn = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final String forceUseJmqiWorkerThreadProperty = "com.ibm.msg.client.wmq.internal.forceUseJmqiWorkerThread";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static boolean forceUseJmqiWorkerThread = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final String inheritRRSContextProperty = "com.ibm.msg.client.wmq.overrideInheritRRSContext";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static boolean inheritRRSContext = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 7667 */   private static final Map<String, WMQStandardValidators.WMQPropertyValidator> allPropertyValidators = new HashMap<>(); private transient JmqiEnvironment jmqiEnvironment;
/*      */   
/*      */   static {
/* 7670 */     if (Trace.isOn) {
/* 7671 */       Trace.entry("com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "static()");
/*      */     }
/*      */ 
/*      */     
/* 7675 */     PropertyStore.register("com.ibm.msg.client.wmq.internal.forceUseJmqiWorkerThread", false);
/* 7676 */     PropertyStore.register("com.ibm.msg.client.wmq.internal.forceDontUseJmqiWorkerThread", false);
/* 7677 */     PropertyStore.register("com.ibm.msg.client.wmq.internal.forceDontUseSharedHconn", false);
/* 7678 */     PropertyStore.register("com.ibm.msg.client.wmq.overrideInheritRRSContext", false);
/*      */ 
/*      */     
/* 7681 */     PropertyStore.register("com.ibm.mq.connector.JCARuntimeHelper", null);
/*      */ 
/*      */     
/* 7684 */     forceUseJmqiWorkerThread = PropertyStore.getBooleanProperty("com.ibm.msg.client.wmq.internal.forceUseJmqiWorkerThread");
/*      */     
/* 7686 */     forceDontUseJmqiWorkerThread = PropertyStore.getBooleanProperty("com.ibm.msg.client.wmq.internal.forceDontUseJmqiWorkerThread");
/* 7687 */     forceDontUseSharedHconn = PropertyStore.getBooleanProperty("com.ibm.msg.client.wmq.internal.forceDontUseSharedHconn");
/* 7688 */     inheritRRSContext = PropertyStore.getBooleanProperty("com.ibm.msg.client.wmq.overrideInheritRRSContext");
/*      */ 
/*      */ 
/*      */     
/* 7692 */     WMQAdminObjectPropertyValidator.register(allPropertyValidators);
/* 7693 */     WMQBrokerCCSubQPropertyValidator.register(allPropertyValidators);
/* 7694 */     WMQBrokerControlQPropertyValidator.register(allPropertyValidators);
/* 7695 */     WMQBrokerPubQPropertyValidator.register(allPropertyValidators);
/* 7696 */     WMQBrokerQMgrPropertyValidator.register(allPropertyValidators);
/* 7697 */     WMQBrokerSubQPropertyValidator.register(allPropertyValidators);
/* 7698 */     WMQBrokerVersionPropertyValidator.register(allPropertyValidators);
/* 7699 */     WMQBrokerVersionPropertyValidator.register(allPropertyValidators);
/* 7700 */     WMQQMgrCCSIDPropertyValidator.register(allPropertyValidators);
/* 7701 */     WMQCCDTURLPropertyValidator.register(allPropertyValidators);
/* 7702 */     WMQChannelPropertyValidator.register(allPropertyValidators);
/* 7703 */     WMQCleanupIntervalPropertyValidator.register(allPropertyValidators);
/* 7704 */     WMQCleanupLevelPropertyValidator.register(allPropertyValidators);
/* 7705 */     WMQClientIDPropertyValidator.register(allPropertyValidators);
/* 7706 */     WMQCloneSupportPropertyValidator.register(allPropertyValidators);
/* 7707 */     WMQConnTagPropertyValidator.register(allPropertyValidators);
/* 7708 */     WMQCFDescriptionPropertyValidator.register(allPropertyValidators);
/* 7709 */     WMQFailIfQuiescePropertyValidator.register(allPropertyValidators);
/* 7710 */     WMQHeaderCompPropertyValidator.register(allPropertyValidators);
/* 7711 */     WMQHostnamePropertyValidator.register(allPropertyValidators);
/* 7712 */     WMQLocalAddressPropertyValidator.register(allPropertyValidators);
/* 7713 */     WMQMapmsgNameStylePropertyValidator.register(allPropertyValidators);
/* 7714 */     WMQMaxBufferSizePropertyValidator.register(allPropertyValidators);
/* 7715 */     WMQMessageRetentionPropertyValidator.register(allPropertyValidators);
/* 7716 */     WMQMessageSelectionPropertyValidator.register(allPropertyValidators);
/* 7717 */     WMQConnectionOptionsPropertyValidator.register(allPropertyValidators);
/* 7718 */     WMQMsgBatchSizePropertyValidator.register(allPropertyValidators);
/* 7719 */     WMQMsgCompPropertyValidator.register(allPropertyValidators);
/* 7720 */     WMQPollingIntervalPropertyValidator.register(allPropertyValidators);
/* 7721 */     WMQOptPubPropertyValidator.register(allPropertyValidators);
/* 7722 */     WMQOutNotifyPropertyValidator.register(allPropertyValidators);
/* 7723 */     WMQPortPropertyValidator.register(allPropertyValidators);
/* 7724 */     WMQProcessDurationPropertyValidator.register(allPropertyValidators);
/* 7725 */     WMQProviderVersionPropertyValidator.register(allPropertyValidators);
/* 7726 */     WMQPubAckIntervalPropertyValidator.register(allPropertyValidators);
/* 7727 */     WMQQueueManagerPropertyValidator.register(allPropertyValidators);
/* 7728 */     WMQQueueManagerOverrideQueueValidator.register(allPropertyValidators);
/* 7729 */     WMQRebalancingApplicationTypeValidator.register(allPropertyValidators);
/* 7730 */     WMQRebalancingListenerPropertyValidator.register(allPropertyValidators);
/* 7731 */     WMQReceiveExitPropertyValidator.register(allPropertyValidators);
/* 7732 */     WMQReceiveExitInitPropertyValidator.register(allPropertyValidators);
/* 7733 */     WMQRcvIsolPropertyValidator.register(allPropertyValidators);
/* 7734 */     WMQRescanIntervalPropertyValidator.register(allPropertyValidators);
/* 7735 */     WMQSecurityExitPropertyValidator.register(allPropertyValidators);
/* 7736 */     WMQSecurityExitInitPropertyValidator.register(allPropertyValidators);
/* 7737 */     WMQSendCheckCountPropertyValidator.register(allPropertyValidators);
/* 7738 */     WMQSendExitPropertyValidator.register(allPropertyValidators);
/* 7739 */     WMQSendExitInitPropertyValidator.register(allPropertyValidators);
/* 7740 */     WMQShareConvAllowedPropertyValidator.register(allPropertyValidators);
/* 7741 */     WMQSparseSubscriptionsPropertyValidator.register(allPropertyValidators);
/* 7742 */     WMQSSLCertStoresColPropertyValidator.register(allPropertyValidators);
/* 7743 */     WMQSSLCertStoresStrPropertyValidator.register(allPropertyValidators);
/* 7744 */     WMQSSLCipherSuitePropertyValidator.register(allPropertyValidators);
/* 7745 */     WMQSSLFipsRequiredPropertyValidator.register(allPropertyValidators);
/* 7746 */     WMQSSLPeerNamePropertyValidator.register(allPropertyValidators);
/* 7747 */     WMQSSLResetCountPropertyValidator.register(allPropertyValidators);
/* 7748 */     WMQSSLSocketFactoryPropertyValidator.register(allPropertyValidators);
/* 7749 */     WMQStatusRefreshIntervalPropertyValidator.register(allPropertyValidators);
/* 7750 */     WMQSubscriptionStorePropertyValidator.register(allPropertyValidators);
/* 7751 */     WMQSyncpointAllGetsPropertyValidator.register(allPropertyValidators);
/* 7752 */     WMQTargetClientMatchingPropertyValidator.register(allPropertyValidators);
/* 7753 */     WMQTemporaryModelPropertyValidator.register(allPropertyValidators);
/* 7754 */     WMQTempTopicPrefixPropertyValidator.register(allPropertyValidators);
/* 7755 */     WMQTempQPrefixPropertyValidator.register(allPropertyValidators);
/* 7756 */     WMQConnectionModePropertyValidator.register(allPropertyValidators);
/* 7757 */     WMQConnectionPoolingPropertyValidator.register(allPropertyValidators);
/* 7758 */     WMQWildcardFormatPropertyValidator.register(allPropertyValidators);
/* 7759 */     WMQConnectionTypeNamePropertyValidator.register(allPropertyValidators);
/* 7760 */     WMQConnectionTypePropertyValidator.register(allPropertyValidators);
/* 7761 */     WMQPasswordPropertyValidator.register(allPropertyValidators);
/* 7762 */     WMQUserIDPropertyValidator.register(allPropertyValidators);
/* 7763 */     WMQAsyncExceptionsPropertyValidator.register(allPropertyValidators);
/* 7764 */     WMQClientReconnectionOptionsPropertyValidator.register(allPropertyValidators);
/* 7765 */     WMQClientReconnectionTimeoutPropertyValidator.register(allPropertyValidators);
/* 7766 */     WMQConnectionNameListPropertyValidator.register(allPropertyValidators);
/* 7767 */     WMQConnectionNameListPropertyValidator.register(allPropertyValidators);
/* 7768 */     WMQAppNamePropertyValidator.register(allPropertyValidators);
/* 7769 */     WMQUserAuthenticationMqcspValidator.register(allPropertyValidators);
/*      */     
/* 7771 */     if (Trace.isOn) {
/* 7772 */       Trace.exit("com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "static()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 7777 */     if (Trace.isOn) {
/* 7778 */       Trace.entry("com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "static()");
/*      */     }
/*      */     
/* 7781 */     if (Trace.isOn) {
/* 7782 */       Trace.exit("com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "static()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int jmqiCompId;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public WMQConnectionFactory(JmsPropertyContext props) throws JMSException {
/* 7795 */     super(props);
/* 7796 */     if (Trace.isOn) {
/* 7797 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "<init>(JmsPropertyContext)", new Object[] { props });
/*      */     }
/*      */ 
/*      */     
/* 7801 */     initialiseWMQConnectionFactory();
/*      */     
/* 7803 */     if (Trace.isOn) {
/* 7804 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "<init>(JmsPropertyContext)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void initialiseWMQConnectionFactory() {
/* 7811 */     if (Trace.isOn) {
/* 7812 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "initialiseWMQConnectionFactory()");
/*      */     }
/*      */ 
/*      */     
/* 7816 */     JmqiDefaultThreadPoolFactory threadPool = new JmqiDefaultThreadPoolFactory();
/* 7817 */     JmqiDefaultPropertyHandler jmqiDefaultPropertyHandler = new JmqiDefaultPropertyHandler();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 7823 */       this.jmqiEnvironment = JmqiFactory.getInstance((JmqiThreadPoolFactory)threadPool, (JmqiPropertyHandler)jmqiDefaultPropertyHandler);
/*      */       
/* 7825 */       JmqiSystemEnvironment sysenv = (JmqiSystemEnvironment)this.jmqiEnvironment;
/* 7826 */       this.jmqiCompId = sysenv.registerComponent(this);
/*      */     
/*      */     }
/* 7829 */     catch (JmqiException e) {
/* 7830 */       if (Trace.isOn) {
/* 7831 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "initialiseWMQConnectionFactory()", (Throwable)e);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 7836 */       HashMap<String, Object> info = new HashMap<>();
/* 7837 */       info.put("exception", e);
/* 7838 */       Trace.ffst(this, "<init>(WMQConnectionFactory)", "XT001001", info, null);
/*      */     } 
/*      */     
/* 7841 */     this.jmqiEnvironment.setCaller('M');
/* 7842 */     threadPool.setEnv(this.jmqiEnvironment);
/*      */     
/* 7844 */     if (Trace.isOn) {
/* 7845 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "initialiseWMQConnectionFactory()");
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
/*      */   public ProviderConnection createProviderConnection(JmsPropertyContext connectionProps) throws JMSException {
/* 7863 */     if (Trace.isOn) {
/* 7864 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "createProviderConnection(JmsPropertyContext)", new Object[] { connectionProps });
/*      */     }
/*      */ 
/*      */     
/* 7868 */     ProviderConnection connection = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 7874 */     String providerVer = connectionProps.getStringProperty("XMSC_WMQ_PROVIDER_VERSION");
/* 7875 */     int transportType = connectionProps.getIntProperty("XMSC_WMQ_CONNECTION_MODE");
/* 7876 */     int brokerVersion = connectionProps.getIntProperty("brokerVersion");
/* 7877 */     String brokerQMgr = connectionProps.getStringProperty("XMSC_WMQ_BROKER_QMGR");
/*      */     
/* 7879 */     if (transportType == 4 || transportType == 2) {
/* 7880 */       HashMap<String, Object> info = new HashMap<>();
/* 7881 */       info.put("XMSC_INSERT_VALUE", "DirectIP");
/* 7882 */       info.put("XMSC_INSERT_NAME", "Transport Type");
/* 7883 */       JMSException je = (JMSException)NLSServices.createException("JMSFMQ1006", info);
/* 7884 */       if (Trace.isOn) {
/* 7885 */         Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "createProviderConnection(JmsPropertyContext)", (Throwable)je, 1);
/*      */       }
/*      */       
/* 7888 */       throw je;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 7895 */     if (transportType == 1) {
/* 7896 */       setConnectionCredentials(connectionProps);
/*      */     }
/*      */ 
/*      */     
/* 7900 */     ProviderVersion pVer = new ProviderVersion(providerVer);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 7905 */     String sysPropProviderVer = PropertyStore.getStringProperty("com.ibm.msg.client.wmq.overrideProviderVersion");
/* 7906 */     if (sysPropProviderVer != null) {
/* 7907 */       boolean valid = false;
/*      */       try {
/* 7909 */         valid = validate("XMSC_WMQ_PROVIDER_VERSION", sysPropProviderVer);
/*      */       }
/* 7911 */       catch (JMSException e) {
/* 7912 */         if (Trace.isOn) {
/* 7913 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "createProviderConnection(JmsPropertyContext)", (Throwable)e, 1);
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 7920 */       if (valid)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 7935 */         pVer = new ProviderVersion(sysPropProviderVer);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 7954 */     String ccdturl = connectionProps.getStringProperty("XMSC_WMQ_CCDTURL");
/* 7955 */     if (ccdturl != null && transportType == 0) {
/* 7956 */       connectionProps.setIntProperty("XMSC_WMQ_CONNECTION_MODE", 1);
/*      */ 
/*      */       
/* 7959 */       setConnectionCredentials(connectionProps);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 7967 */     if (pVer.equals("unspecified") && brokerVersion == 1 && brokerQMgr != null && !brokerQMgr.trim().equals(""))
/*      */     {
/*      */       
/* 7970 */       pVer = new ProviderVersion("6.0.0.0");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 7989 */     String cachedCipherSuite = null;
/*      */ 
/*      */     
/* 7992 */     boolean bindingsThenClient = false;
/* 7993 */     int transport = connectionProps.getIntProperty("XMSC_WMQ_CONNECTION_MODE");
/* 7994 */     if (transport == 8) {
/* 7995 */       bindingsThenClient = true;
/*      */ 
/*      */ 
/*      */       
/* 7999 */       connectionProps.setIntProperty("XMSC_WMQ_CONNECTION_MODE", 0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 8007 */       cachedCipherSuite = (String)connectionProps.remove("XMSC_WMQ_SSL_CIPHER_SUITE");
/*      */     } 
/*      */     
/* 8010 */     if (pVer.equals("unspecified")) {
/*      */ 
/*      */       
/* 8013 */       if (Trace.isOn) {
/* 8014 */         Trace.traceData(this, "createProviderConnection(JmsPropertyContext)", "ProviderVersion is unset or set to 'unspecified' - auto-detecting QM level.");
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 8019 */       connection = null;
/*      */       
/*      */       try {
/* 8022 */         connectionProps.setStringProperty("XMSC_WMQ_PROVIDER_VERSION", "8.0.0.0");
/* 8023 */         connection = createV7ProviderConnection(connectionProps);
/*      */       }
/* 8025 */       catch (JMSException jmsE) {
/* 8026 */         if (Trace.isOn) {
/* 8027 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "createProviderConnection(JmsPropertyContext)", (Throwable)jmsE, 2);
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
/* 8038 */         if (!bindingsThenClient) {
/* 8039 */           if (Trace.isOn) {
/* 8040 */             Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "createProviderConnection(JmsPropertyContext)", (Throwable)jmsE, 2);
/*      */           }
/*      */           
/* 8043 */           throw jmsE;
/*      */         } 
/*      */         
/* 8046 */         int reason = 0;
/* 8047 */         Exception cause = jmsE.getLinkedException();
/* 8048 */         if (cause instanceof JmqiException) {
/* 8049 */           reason = ((JmqiException)cause).getReason();
/*      */         }
/* 8051 */         else if (cause instanceof MQException) {
/* 8052 */           reason = ((MQException)cause).getReason();
/*      */         } 
/*      */         
/* 8055 */         switch (reason) {
/*      */           
/*      */           case 2058:
/*      */           case 2059:
/*      */           case 2495:
/* 8060 */             connectionProps.setIntProperty("XMSC_WMQ_CONNECTION_MODE", 1);
/*      */ 
/*      */             
/* 8063 */             setConnectionCredentials(connectionProps);
/*      */ 
/*      */             
/* 8066 */             if (cachedCipherSuite != null) {
/* 8067 */               connectionProps.setStringProperty("XMSC_WMQ_SSL_CIPHER_SUITE", cachedCipherSuite);
/*      */             }
/*      */             
/* 8070 */             connection = createV7ProviderConnection(connectionProps);
/*      */             break;
/*      */           default:
/* 8073 */             if (Trace.isOn) {
/* 8074 */               Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "createProviderConnection(JmsPropertyContext)", (Throwable)jmsE, 3);
/*      */             }
/*      */             
/* 8077 */             throw jmsE;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       } 
/* 8084 */       if (checkConnectionPV7Capable((WMQConnection)connection, pVer, false) && checkBrokerStatus((WMQConnection)connection, brokerVersion))
/*      */       {
/* 8086 */         if (Trace.isOn) {
/* 8087 */           Trace.traceData(this, "createProviderConnection(JmsPropertyContext)", "Retaining connection for V7 QM.");
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/* 8092 */         if (Trace.isOn) {
/* 8093 */           Trace.traceData(this, "createProviderConnection(JmsPropertyContext)", "creating V6 QM.");
/*      */         }
/* 8095 */         connection.close();
/* 8096 */         connectionProps.setStringProperty("XMSC_WMQ_PROVIDER_VERSION", "6");
/* 8097 */         connection = createV6ProviderConnection(connectionProps);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 8115 */     else if (pVer.version >= 7) {
/*      */ 
/*      */       
/* 8118 */       boolean tryClient = false;
/*      */       
/*      */       try {
/* 8121 */         connection = createV7ProviderConnection(connectionProps);
/*      */       }
/* 8123 */       catch (JMSException jmsE) {
/* 8124 */         if (Trace.isOn) {
/* 8125 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "createProviderConnection(JmsPropertyContext)", (Throwable)jmsE, 3);
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
/* 8136 */         if (!bindingsThenClient) {
/* 8137 */           if (Trace.isOn) {
/* 8138 */             Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "createProviderConnection(JmsPropertyContext)", (Throwable)jmsE, 4);
/*      */           }
/*      */           
/* 8141 */           throw jmsE;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 8150 */         Exception cause = jmsE.getLinkedException();
/* 8151 */         if (cause instanceof MQException) {
/*      */ 
/*      */           
/* 8154 */           int reason = ((MQException)cause).getReason();
/* 8155 */           if (reason == 2058 || reason == 2059) {
/* 8156 */             tryClient = true;
/*      */           }
/*      */         }
/* 8159 */         else if (cause instanceof JmqiException) {
/* 8160 */           int reason = ((JmqiException)cause).getReason();
/* 8161 */           if (reason == 2495) {
/* 8162 */             tryClient = true;
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/* 8167 */         if (tryClient) {
/* 8168 */           connectionProps.setIntProperty("XMSC_WMQ_CONNECTION_MODE", 1);
/*      */ 
/*      */           
/* 8171 */           setConnectionCredentials(connectionProps);
/*      */ 
/*      */           
/* 8174 */           if (cachedCipherSuite != null) {
/* 8175 */             connectionProps.setStringProperty("XMSC_WMQ_SSL_CIPHER_SUITE", cachedCipherSuite);
/*      */           }
/*      */           
/* 8178 */           connection = createV7ProviderConnection(connectionProps);
/*      */         
/*      */         }
/*      */         else {
/*      */           
/* 8183 */           if (Trace.isOn) {
/* 8184 */             Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "createProviderConnection(JmsPropertyContext)", (Throwable)jmsE, 5);
/*      */           }
/*      */           
/* 8187 */           throw jmsE;
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 8195 */       if (checkConnectionPV7Capable((WMQConnection)connection, pVer, true))
/*      */       {
/* 8197 */         if (Trace.isOn) {
/* 8198 */           Trace.traceData(this, "createProviderConnection(JmsPropertyContext)", "ProviderVersion set to request V7 CF; returning new V7 CF");
/*      */         
/*      */         }
/*      */       
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 8207 */       if (Trace.isOn) {
/* 8208 */         Trace.traceData(this, "createProviderConnectionFactory(JmsPropertyContext)", "ProviderVersion set to request non-v7 CF; returning new V6 CF");
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 8214 */         connection = createV6ProviderConnection(connectionProps);
/*      */       }
/* 8216 */       catch (JMSException jmsE) {
/* 8217 */         if (Trace.isOn) {
/* 8218 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "createProviderConnection(JmsPropertyContext)", (Throwable)jmsE, 4);
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
/* 8229 */         if (!bindingsThenClient) {
/* 8230 */           if (Trace.isOn) {
/* 8231 */             Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "createProviderConnection(JmsPropertyContext)", (Throwable)jmsE, 6);
/*      */           }
/*      */           
/* 8234 */           throw jmsE;
/*      */         } 
/*      */         
/* 8237 */         boolean tryClient = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 8247 */         Throwable cause = jmsE.getLinkedException();
/* 8248 */         if (cause instanceof MQException) {
/*      */ 
/*      */           
/* 8251 */           int reason = ((MQException)cause).getReason();
/* 8252 */           if (reason == 2058 || reason == 2059) {
/* 8253 */             tryClient = true;
/*      */           }
/* 8255 */           else if (reason == 2298) {
/* 8256 */             cause = cause.getCause();
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 8264 */         if (cause instanceof JmqiException) {
/* 8265 */           int reason = ((JmqiException)cause).getReason();
/* 8266 */           if (reason == 2495) {
/* 8267 */             tryClient = true;
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/* 8272 */         if (tryClient) {
/* 8273 */           connectionProps.setIntProperty("XMSC_WMQ_CONNECTION_MODE", 1);
/*      */ 
/*      */           
/* 8276 */           setConnectionCredentials(connectionProps);
/*      */ 
/*      */           
/* 8279 */           if (cachedCipherSuite != null) {
/* 8280 */             connectionProps.setStringProperty("XMSC_WMQ_SSL_CIPHER_SUITE", cachedCipherSuite);
/*      */           }
/*      */           
/* 8283 */           connection = createV6ProviderConnection(connectionProps);
/*      */         
/*      */         }
/*      */         else {
/*      */           
/* 8288 */           if (Trace.isOn) {
/* 8289 */             Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "createProviderConnection(JmsPropertyContext)", (Throwable)jmsE, 7);
/*      */           }
/*      */           
/* 8292 */           throw jmsE;
/*      */         } 
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 8322 */     if (Trace.isOn) {
/* 8323 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "createProviderConnection(JmsPropertyContext)", connection);
/*      */     }
/*      */     
/* 8326 */     return connection;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void setConnectionCredentials(JmsPropertyContext connectionProps) throws JMSException {
/* 8334 */     if (connectionProps.getStringProperty("XMSC_USERID") == null) {
/* 8335 */       connectionProps.setStringProperty("XMSC_USERID", "");
/*      */     }
/*      */     
/* 8338 */     if (connectionProps.getStringProperty("XMSC_PASSWORD") == null) {
/* 8339 */       connectionProps.setStringProperty("XMSC_PASSWORD", "");
/* 8340 */       connectionProps.setBooleanProperty("XMSC_PASSWORD_WAS_NULL", true);
/*      */     } 
/*      */     
/* 8343 */     if (Trace.isOn) {
/* 8344 */       Trace.traceData(this, "connecting as user: " + connectionProps.getStringProperty("XMSC_USERID"), null);
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
/*      */   private boolean checkBrokerStatus(WMQConnection connection, int brokerVersion) throws JMSException {
/* 8361 */     if (Trace.isOn) {
/* 8362 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "checkBrokerStatus(WMQConnection,int)", new Object[] { connection, 
/*      */             
/* 8364 */             Integer.valueOf(brokerVersion) });
/*      */     }
/* 8366 */     boolean suitableForV7PubSub = true;
/*      */     
/* 8368 */     if (brokerVersion == 0 || brokerVersion == -1) {
/* 8369 */       suitableForV7PubSub = true;
/*      */     }
/*      */     else {
/*      */       
/* 8373 */       String controlQ = null;
/*      */       
/*      */       try {
/* 8376 */         controlQ = connection.getStringProperty("XMSC_WMQ_BROKER_CONTROLQ");
/*      */       }
/* 8378 */       catch (JMSException e) {
/* 8379 */         if (Trace.isOn) {
/* 8380 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "checkBrokerStatus(WMQConnection,int)", (Throwable)e);
/*      */         }
/*      */         
/* 8383 */         HashMap<String, Object> data = new HashMap<>();
/* 8384 */         data.put("BrokerControlQ", null);
/* 8385 */         data.put("WMQConnection", connection);
/* 8386 */         Trace.ffst(this, "checkBrokerStatus", "XT001006", data, JMSException.class);
/*      */         
/* 8388 */         if (Trace.isOn) {
/* 8389 */           Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "checkBrokerStatus(WMQConnection,int)", 
/* 8390 */               Boolean.valueOf(false), 1);
/*      */         }
/* 8392 */         return false;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 8399 */       JmqiMQ mq = connection.getJmqiMQ();
/* 8400 */       JmqiEnvironment env = connection.getJmqiEnvironment();
/* 8401 */       Hconn hConn = connection.getHconn();
/*      */ 
/*      */       
/* 8404 */       Pint cc = env.newPint(0);
/* 8405 */       Pint rc = env.newPint(0);
/*      */ 
/*      */       
/* 8408 */       MQOD mqod = env.newMQOD();
/* 8409 */       mqod.setObjectName(controlQ);
/* 8410 */       int options = 8208;
/* 8411 */       Phobj phobj = env.newPhobj();
/*      */ 
/*      */       
/* 8414 */       mq.MQOPEN(hConn, mqod, options, phobj, cc, rc);
/* 8415 */       if (cc.x == 0 || cc.x == 1) {
/*      */         
/* 8417 */         MQOD mqod_qm = env.newMQOD();
/* 8418 */         mqod_qm.setObjectType(5);
/* 8419 */         int options_qm = 32;
/* 8420 */         Phobj phobj_qm = env.newPhobj();
/*      */ 
/*      */         
/* 8423 */         mq.MQOPEN(hConn, mqod_qm, options_qm, phobj_qm, cc, rc);
/* 8424 */         if (cc.x == 0 || cc.x == 1) {
/* 8425 */           Hobj hobj = phobj_qm.getHobj();
/*      */ 
/*      */           
/* 8428 */           int[] selectors = { 187 };
/* 8429 */           int[] intAttrs = new int[1];
/* 8430 */           mq.MQINQ(hConn, hobj, selectors.length, selectors, intAttrs.length, intAttrs, 0, new byte[0], cc, rc);
/* 8431 */           if (rc.x == 0) {
/*      */ 
/*      */             
/* 8434 */             int psMode = intAttrs[0];
/* 8435 */             if (psMode == 1 || psMode == 0) {
/*      */               
/* 8437 */               suitableForV7PubSub = false;
/*      */             } else {
/*      */               
/* 8440 */               suitableForV7PubSub = true;
/*      */             } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 8463 */             if (Trace.isOn) {
/* 8464 */               HashMap<String, Object> data = new HashMap<>();
/* 8465 */               data.put("psMode", Integer.valueOf(psMode));
/* 8466 */               data.put("suitableForV7PubSub", Boolean.valueOf(suitableForV7PubSub));
/* 8467 */               Trace.data(this, "checkBrokerStatus()", "Final BrokerVer results", data);
/*      */             }
/*      */           
/*      */           } else {
/*      */             
/* 8472 */             HashMap<String, Object> data = new HashMap<>();
/* 8473 */             data.put("WMQConnection", connection);
/* 8474 */             data.put("cc", Integer.valueOf(cc.x));
/* 8475 */             data.put("rc", Integer.valueOf(rc.x));
/* 8476 */             Trace.ffst(this, "checkBrokerStatus", "XT001007", data, JMSException.class);
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/* 8481 */           mq.MQCLOSE(hConn, phobj_qm, 0, cc, rc);
/*      */         }
/*      */         else {
/*      */           
/* 8485 */           HashMap<String, Object> data = new HashMap<>();
/* 8486 */           data.put("WMQConnection", connection);
/* 8487 */           data.put("cc", Integer.valueOf(cc.x));
/* 8488 */           data.put("rc", Integer.valueOf(rc.x));
/* 8489 */           Trace.ffst(this, "checkBrokerStatus", "XT001008", data, JMSException.class);
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 8494 */         options = 0;
/* 8495 */         mq.MQCLOSE(hConn, phobj, options, cc, rc);
/*      */       }
/*      */       else {
/*      */         
/* 8499 */         if (Trace.isOn) {
/*      */           
/* 8501 */           HashMap<String, Object> data = new HashMap<>();
/* 8502 */           data.put("ControlQ", controlQ);
/* 8503 */           data.put("cc", Integer.valueOf(cc.x));
/* 8504 */           data.put("rc", Integer.valueOf(rc.x));
/* 8505 */           Trace.data(this, "checkBrokerStatus()", "Unable to open the broker control queue", null);
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 8511 */         suitableForV7PubSub = true;
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
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 8527 */     if (Trace.isOn) {
/* 8528 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "checkBrokerStatus(WMQConnection,int)", 
/* 8529 */           Boolean.valueOf(suitableForV7PubSub), 2);
/*      */     }
/* 8531 */     return suitableForV7PubSub;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JmqiEnvironment getJmqiEnvironment() {
/* 8538 */     if (Trace.isOn) {
/* 8539 */       Trace.data(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "getJmqiEnvironment()", "getter", this.jmqiEnvironment);
/*      */     }
/*      */     
/* 8542 */     return this.jmqiEnvironment;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getJmqiCompId() {
/* 8549 */     if (Trace.isOn) {
/* 8550 */       Trace.data(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "getJmqiCompId()", "getter", 
/* 8551 */           Integer.valueOf(this.jmqiCompId));
/*      */     }
/* 8553 */     return this.jmqiCompId;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected ProviderConnection createV7ProviderConnection(JmsPropertyContext connectionProps) throws JMSException {
/* 8563 */     if (Trace.isOn) {
/* 8564 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "createV7ProviderConnection(JmsPropertyContext)", new Object[] { connectionProps });
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
/*      */ 
/*      */ 
/*      */     
/* 8578 */     int jmqiType = -1;
/* 8579 */     int transport = connectionProps.getIntProperty("XMSC_WMQ_CONNECTION_MODE");
/* 8580 */     if (transport == 0) {
/* 8581 */       if (connectionProps.getStringProperty("XMSC_WMQ_SSL_CIPHER_SUITE") != null) {
/* 8582 */         MQException mqE = new MQException(2, 2396, null);
/* 8583 */         JMSException je = (JMSException)NLSServices.createException("JMSFMQ6312", null);
/* 8584 */         je.setLinkedException((Exception)mqE);
/*      */         
/* 8586 */         if (Trace.isOn) {
/* 8587 */           Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "createV7ProviderConnection(JmsPropertyContext)", (Throwable)je, 1);
/*      */         }
/*      */         
/* 8590 */         throw je;
/*      */       } 
/* 8592 */       jmqiType = 0;
/*      */     }
/* 8594 */     else if (transport == 1) {
/* 8595 */       checkClientConnectionAllowed(false);
/* 8596 */       jmqiType = 2;
/*      */     } 
/*      */ 
/*      */     
/* 8600 */     int jmqiOptions = 0;
/*      */ 
/*      */     
/* 8603 */     WASSupport.WASRuntimeHelper helper = (WASSupport.WASRuntimeHelper)PropertyStore.getObjectProperty("com.ibm.mq.connector.JCARuntimeHelper");
/* 8604 */     if (Trace.isOn) {
/* 8605 */       Trace.data(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "createV7ProviderConnection(JmsPropertyContext)", "Got runtime helper object:" + helper);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 8610 */     if (helper != null) {
/* 8611 */       if (Trace.isOn) {
/* 8612 */         Trace.data(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "createV7ProviderConnection(JmsPropertyContext)", "Environment settings are " + helper
/*      */ 
/*      */             
/* 8615 */             .getEnvironment());
/*      */       }
/* 8617 */       if (helper.getEnvironment() == 8 || helper.getEnvironment() == 16 || helper.getEnvironment() == 32 || helper
/* 8618 */         .getEnvironment() == 184) {
/* 8619 */         jmqiOptions |= 0x20;
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 8628 */     if (forceUseJmqiWorkerThread) {
/* 8629 */       jmqiOptions |= 0x4;
/*      */     }
/* 8631 */     if (forceDontUseJmqiWorkerThread) {
/* 8632 */       jmqiOptions |= 0x8;
/*      */     }
/* 8634 */     if (forceDontUseSharedHconn) {
/* 8635 */       jmqiOptions |= 0x10;
/*      */     }
/* 8637 */     if (inheritRRSContext) {
/* 8638 */       jmqiOptions |= 0x40;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 8643 */     JmqiMQ jmqiMq = null;
/*      */     try {
/* 8645 */       jmqiMq = this.jmqiEnvironment.getMQI(jmqiType, jmqiOptions);
/*      */     }
/* 8647 */     catch (JmqiException e) {
/* 8648 */       if (Trace.isOn) {
/* 8649 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "createV7ProviderConnection(JmsPropertyContext)", (Throwable)e);
/*      */       }
/*      */ 
/*      */       
/* 8653 */       JMSException je = (JMSException)NLSServices.createException("JMSFMQ6312", null);
/* 8654 */       je.setLinkedException((Exception)e);
/*      */       
/* 8656 */       if (Trace.isOn) {
/* 8657 */         Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "createV7ProviderConnection(JmsPropertyContext)", (Throwable)je, 2);
/*      */       }
/*      */       
/* 8660 */       throw je;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 8665 */     JmqiSP spi = (JmqiSP)jmqiMq;
/* 8666 */     if ((spi.isCICS() || spi.isIMS()) && (connectionProps
/* 8667 */       .getStringProperty("XMSC_USERID") != null || connectionProps
/* 8668 */       .getStringProperty("XMSC_PASSWORD") != null)) {
/*      */       
/* 8670 */       String messageID = spi.isCICS() ? "JMSCC6004" : "JMSCC6014";
/* 8671 */       JMSException je = (JMSException)JmsErrorUtils.createException(messageID, null);
/*      */       
/* 8673 */       if (Trace.isOn) {
/* 8674 */         Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "createV7ProviderConnection(JmsPropertyContext)", (Throwable)je, 3);
/*      */       }
/*      */       
/* 8677 */       throw je;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 8683 */     WMQConnection connection = new WMQConnection(this.jmqiEnvironment, jmqiMq, this.jmqiCompId, connectionProps);
/* 8684 */     connection.setStringProperty("XMSC_WMQ_PROVIDER_VERSION", "8.0.0.0");
/*      */ 
/*      */ 
/*      */     
/* 8688 */     if (!jmqiMq.isLocal() && !checkClientConnAllowedAnyServer(false)) {
/* 8689 */       JmqiException jmqiException; Exception nestedException = null;
/* 8690 */       boolean zosClientAllowed = false;
/*      */       try {
/* 8692 */         Hconn hconn = connection.getHconn();
/* 8693 */         if (hconn.isConnToZos() && hconn.getQmgrAdvancedCapability() == QmgrAdvancedCapability.SUPPORTED) {
/* 8694 */           zosClientAllowed = true;
/*      */         } else {
/*      */           
/* 8697 */           zosClientAllowed = false;
/*      */         }
/*      */       
/* 8700 */       } catch (JmqiException e) {
/* 8701 */         if (Trace.isOn) {
/* 8702 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "createV7ProviderConnection(JmsPropertyContext)", (Throwable)e);
/*      */         }
/*      */ 
/*      */         
/* 8706 */         jmqiException = e;
/*      */       } 
/* 8708 */       if (!zosClientAllowed) {
/*      */         
/* 8710 */         connection.close();
/*      */         
/* 8712 */         Log.log(this, "createV7ProviderConnection(JmsPropertyContext)", "JMSFMQ0005", null);
/*      */         
/* 8714 */         JMSException je = (JMSException)NLSServices.createException("JMSFMQ0005", null);
/* 8715 */         if (jmqiException != null) {
/* 8716 */           je.setLinkedException((Exception)jmqiException);
/*      */         }
/*      */         
/* 8719 */         if (Trace.isOn) {
/* 8720 */           Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "createV7ProviderConnection(JmsPropertyContext)", (Throwable)je);
/*      */         }
/*      */         
/* 8723 */         throw je;
/*      */       } 
/*      */     } 
/*      */     
/* 8727 */     if (Trace.isOn) {
/* 8728 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "createV7ProviderConnection(JmsPropertyContext)", connection);
/*      */     }
/*      */     
/* 8731 */     return (ProviderConnection)connection;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected ProviderConnection createV6ProviderConnection(JmsPropertyContext connectionProps) throws JMSException {
/*      */     MQConnection mQConnection;
/* 8741 */     if (Trace.isOn) {
/* 8742 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "createV6ProviderConnection(JmsPropertyContext)", new Object[] { connectionProps });
/*      */     }
/*      */ 
/*      */     
/* 8746 */     ProviderConnection connection = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*      */       HashMap<String, Object> info;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 8760 */       int brokerVersion = connectionProps.getIntProperty("brokerVersion");
/* 8761 */       int messageSelection = connectionProps.getIntProperty("XMSC_WMQ_MESSAGE_SELECTION");
/* 8762 */       if (brokerVersion != 1 && messageSelection == 1) {
/* 8763 */         JMSException je = (JMSException)NLSServices.createException("JMSFMQ3036", null);
/* 8764 */         if (Trace.isOn) {
/* 8765 */           Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "createV6ProviderConnection(JmsPropertyContext)", (Throwable)je, 1);
/*      */         }
/*      */         
/* 8768 */         throw je;
/*      */       } 
/*      */ 
/*      */       
/* 8772 */       String username = connectionProps.getStringProperty("XMSC_USERID");
/* 8773 */       String password = connectionProps.getStringProperty("XMSC_PASSWORD");
/*      */ 
/*      */       
/* 8776 */       int transportType = connectionProps.getIntProperty("XMSC_WMQ_CONNECTION_MODE");
/*      */       
/* 8778 */       switch (transportType) {
/*      */         case 0:
/* 8780 */           mQConnection = new MQConnection(username, password, connectionProps);
/*      */           break;
/*      */         case 1:
/* 8783 */           checkClientConnectionAllowed(false);
/* 8784 */           mQConnection = new MQConnection(username, password, connectionProps);
/*      */           break;
/*      */         default:
/* 8787 */           info = new HashMap<>();
/* 8788 */           info.put("connectionMode", Integer.toString(transportType));
/* 8789 */           Trace.ffst(this, "(createV6ProviderConnection)", "XT001004", info, JMSException.class);
/*      */           break;
/*      */       } 
/* 8792 */       if (mQConnection != null) {
/* 8793 */         mQConnection.setStringProperty("XMSC_WMQ_PROVIDER_VERSION", "6.0.0.0");
/*      */       }
/*      */     }
/* 8796 */     catch (JMSException je) {
/* 8797 */       if (Trace.isOn) {
/* 8798 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "createV6ProviderConnection(JmsPropertyContext)", (Throwable)je);
/*      */       }
/*      */       
/* 8801 */       if (Trace.isOn) {
/* 8802 */         Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "createV6ProviderConnection(JmsPropertyContext)", (Throwable)je, 2);
/*      */       }
/*      */       
/* 8805 */       throw je;
/*      */     } 
/*      */     
/* 8808 */     if (Trace.isOn) {
/* 8809 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "createV6ProviderConnection(JmsPropertyContext)", mQConnection);
/*      */     }
/*      */     
/* 8812 */     return (ProviderConnection)mQConnection;
/*      */   }
/*      */ 
/*      */   
/*      */   protected String getProcessUserId() {
/* 8817 */     if (Trace.isOn) {
/* 8818 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "getProcessUserId()");
/*      */     }
/*      */     
/* 8821 */     String username = AccessController.<String>doPrivileged(new PrivilegedAction<String>()
/*      */         {
/*      */           public String run()
/*      */           {
/* 8825 */             if (Trace.isOn) {
/* 8826 */               Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "run()");
/*      */             }
/* 8828 */             String traceRet1 = null;
/*      */             try {
/* 8830 */               traceRet1 = PropertyStore.user_name;
/*      */             }
/* 8832 */             catch (AccessControlException ace) {
/* 8833 */               if (Trace.isOn) {
/* 8834 */                 Trace.catchBlock(this, "com.ibm.msg.client.wmq.factories.null", "run()", ace);
/*      */               }
/*      */             } 
/* 8837 */             if (traceRet1 == null) {
/* 8838 */               traceRet1 = "anonymous";
/*      */             }
/*      */             
/* 8841 */             if (Trace.isOn) {
/* 8842 */               Trace.exit(this, "com.ibm.msg.client.wmq.factories.null", "run()", traceRet1);
/*      */             }
/* 8844 */             return traceRet1;
/*      */           }
/*      */         });
/*      */     
/* 8848 */     if (Trace.isOn) {
/* 8849 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "getProcessUserId()", username);
/*      */     }
/*      */     
/* 8852 */     return username;
/*      */   }
/*      */   
/*      */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 8856 */     if (Trace.isOn) {
/* 8857 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "readObject(java.io.ObjectInputStream)", new Object[] { in });
/*      */     }
/*      */     
/* 8860 */     in.defaultReadObject();
/*      */ 
/*      */     
/* 8863 */     initialiseWMQConnectionFactory();
/* 8864 */     if (Trace.isOn) {
/* 8865 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "readObject(java.io.ObjectInputStream)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JmqiComponentTls newTlsObject() {
/* 8876 */     if (Trace.isOn) {
/* 8877 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "newTlsObject()");
/*      */     }
/* 8879 */     WMQThreadLocalStorage wMQThreadLocalStorage = new WMQThreadLocalStorage();
/* 8880 */     if (Trace.isOn) {
/* 8881 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "newTlsObject()", wMQThreadLocalStorage);
/*      */     }
/*      */     
/* 8884 */     return (JmqiComponentTls)wMQThreadLocalStorage;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getJmqiComponentName() {
/* 8892 */     if (Trace.isOn) {
/* 8893 */       Trace.data(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "getJmqiComponentName()", "getter", "com.ibm.msg.client.wmq");
/*      */     }
/*      */     
/* 8896 */     return "com.ibm.msg.client.wmq";
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
/*      */   public Object mapFromCanonical(String keyIn, Object valueIn) {
/* 8911 */     if (Trace.isOn) {
/* 8912 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "mapFromCanonical(String,Object)", new Object[] { keyIn, valueIn });
/*      */     }
/*      */     
/* 8915 */     if (allPropertyValidators.size() > 0) {
/*      */       
/* 8917 */       WMQStandardValidators.WMQPropertyValidator mapper = allPropertyValidators.get(keyIn);
/* 8918 */       if (mapper == null)
/*      */       {
/*      */ 
/*      */         
/* 8922 */         mapper = allPropertyValidators.get(keyIn.toUpperCase());
/*      */       }
/*      */       
/* 8925 */       if (mapper != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 8931 */         WMQValidationInterface.WMQPropertyValidatorDatatype mappedPair = mapper.fromCanonical((JmsPropertyContext)this, keyIn, valueIn);
/*      */ 
/*      */         
/* 8934 */         Object traceRet1 = mappedPair.getValue();
/* 8935 */         if (Trace.isOn) {
/* 8936 */           Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "mapFromCanonical(String,Object)", traceRet1, 1);
/*      */         }
/*      */         
/* 8939 */         return traceRet1;
/*      */       } 
/*      */ 
/*      */       
/* 8943 */       if (Trace.isOn) {
/* 8944 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "mapFromCanonical(String,Object)", valueIn, 2);
/*      */       }
/*      */       
/* 8947 */       return valueIn;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 8952 */     if (Trace.isOn) {
/* 8953 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "mapFromCanonical(String,Object)", valueIn, 3);
/*      */     }
/*      */     
/* 8956 */     return valueIn;
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
/*      */   public String getCanonicalKey(String keyIn) {
/* 8969 */     if (Trace.isOn) {
/* 8970 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "getCanonicalKey(String)", new Object[] { keyIn });
/*      */     }
/*      */     
/* 8973 */     if (allPropertyValidators.size() > 0) {
/* 8974 */       WMQStandardValidators.WMQPropertyValidator mapper = allPropertyValidators.get(keyIn);
/* 8975 */       if (mapper == null)
/*      */       {
/*      */ 
/*      */         
/* 8979 */         mapper = allPropertyValidators.get(keyIn.toUpperCase());
/*      */       }
/*      */       
/* 8982 */       if (mapper != null) {
/*      */ 
/*      */ 
/*      */         
/* 8986 */         String traceRet1 = mapper.getDomainName(4);
/* 8987 */         if (Trace.isOn) {
/* 8988 */           Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "getCanonicalKey(String)", traceRet1, 1);
/*      */         }
/*      */         
/* 8991 */         return traceRet1;
/*      */       } 
/*      */       
/* 8994 */       if (Trace.isOn) {
/* 8995 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "getCanonicalKey(String)", keyIn, 2);
/*      */       }
/*      */       
/* 8998 */       return keyIn;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 9003 */     if (Trace.isOn) {
/* 9004 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "getCanonicalKey(String)", keyIn, 3);
/*      */     }
/*      */     
/* 9007 */     return keyIn;
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
/*      */   public Vector<Object> mapToCanonical(String keyIn, Object valueIn) {
/* 9024 */     Vector<Object> result = new Vector();
/*      */ 
/*      */     
/* 9027 */     if (allPropertyValidators.size() > 0) {
/*      */       
/* 9029 */       WMQStandardValidators.WMQPropertyValidator mapper = allPropertyValidators.get(keyIn);
/* 9030 */       if (mapper == null)
/*      */       {
/*      */ 
/*      */         
/* 9034 */         mapper = allPropertyValidators.get(keyIn.toUpperCase());
/*      */       }
/*      */       
/* 9037 */       if (mapper != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 9043 */         WMQValidationInterface.WMQPropertyValidatorDatatype pair = mapper.toCanonical((JmsPropertyContext)this, keyIn, valueIn);
/*      */         
/* 9045 */         result.add(pair.getName());
/* 9046 */         result.add(pair.getValue());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 9054 */         return result;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 9062 */       result.add(keyIn);
/* 9063 */       result.add(valueIn);
/* 9064 */       return result;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 9069 */     result.add(keyIn);
/* 9070 */     result.add(valueIn);
/* 9071 */     return result;
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
/*      */   public boolean validate(Object name, Object value) throws JMSException {
/* 9088 */     if (allPropertyValidators.size() > 0) {
/* 9089 */       Object mqValidator = allPropertyValidators.get(name);
/* 9090 */       if (mqValidator == null && name instanceof String)
/*      */       {
/*      */         
/* 9093 */         mqValidator = allPropertyValidators.get(((String)name).toUpperCase());
/*      */       }
/*      */       
/* 9096 */       if (mqValidator != null) {
/*      */ 
/*      */ 
/*      */         
/* 9100 */         boolean traceRet1 = ((WMQStandardValidators.WMQPropertyValidator)mqValidator).validate(value, this.jmsPropertyContext);
/* 9101 */         return traceRet1;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 9108 */       Trace.traceData(this, "Unable to find validator for property " + name + " - skipping validation", null);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 9121 */       return true;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 9128 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String crossPropertyValidate() {
/* 9136 */     if (Trace.isOn) {
/* 9137 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "crossPropertyValidate()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 9143 */     boolean overallValidation = true;
/* 9144 */     StringBuilder validationFailures = new StringBuilder("");
/*      */     
/* 9146 */     for (Map.Entry<String, WMQStandardValidators.WMQPropertyValidator> entry : allPropertyValidators.entrySet()) {
/* 9147 */       String key = entry.getKey();
/* 9148 */       WMQStandardValidators.WMQPropertyValidator mqValidator = entry.getValue();
/* 9149 */       if (mqValidator != null) {
/* 9150 */         boolean validated = true;
/*      */         try {
/* 9152 */           validated = mqValidator.crossPropertyValidate(this.jmsPropertyContext);
/*      */         }
/* 9154 */         catch (JMSException j) {
/* 9155 */           if (Trace.isOn) {
/* 9156 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "crossPropertyValidate()", (Throwable)j);
/*      */           }
/*      */           
/* 9159 */           if (Trace.isOn) {
/* 9160 */             Trace.traceWarning(this, "c.i.m.c.w.common.internal.WMQDestination", "crossPropertyValidate()", j);
/*      */           }
/*      */         } 
/* 9163 */         if (!validated) {
/* 9164 */           overallValidation = false;
/* 9165 */           validationFailures.append(key).append(", ");
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 9170 */     if (overallValidation == true) {
/* 9171 */       if (Trace.isOn) {
/* 9172 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "crossPropertyValidate()", null, 1);
/*      */       }
/*      */       
/* 9175 */       return null;
/*      */     } 
/*      */     
/* 9178 */     String traceRet1 = validationFailures.substring(0, validationFailures.length() - 2);
/* 9179 */     if (Trace.isOn) {
/* 9180 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "crossPropertyValidate()", traceRet1, 2);
/*      */     }
/*      */     
/* 9183 */     return traceRet1;
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
/*      */   private boolean checkConnectionPV7Capable(WMQConnection connection, ProviderVersion requestedProviderVersion, boolean throwException) throws JMSException {
/*      */     int cmdLevel, sharingConversations;
/* 9196 */     if (Trace.isOn) {
/* 9197 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "checkConnectionPV7Capable(WMQConnection,ProviderVersion,boolean)", new Object[] { connection, requestedProviderVersion, 
/*      */             
/* 9199 */             Boolean.valueOf(throwException) });
/*      */     }
/* 9201 */     boolean traceRet1 = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 9209 */       cmdLevel = connection.getCmdLevel();
/* 9210 */       sharingConversations = connection.getSharingConversations();
/*      */     }
/* 9212 */     catch (JmqiException j) {
/* 9213 */       if (Trace.isOn) {
/* 9214 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "checkConnectionPV7Capable(WMQConnection,ProviderVersion,boolean)", (Throwable)j);
/*      */       }
/*      */       
/* 9217 */       JMSException jmsE = new JMSException(j.getMessage());
/* 9218 */       if (Trace.isOn) {
/* 9219 */         Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "checkConnectionPV7Capable(WMQConnection,ProviderVersion,boolean)", (Throwable)jmsE, 1);
/*      */       }
/*      */       
/* 9222 */       throw jmsE;
/*      */     } 
/*      */     
/* 9225 */     if (cmdLevel < 700) {
/*      */       
/* 9227 */       if (Trace.isOn) {
/* 9228 */         Trace.traceData(this, "checkConnectionPV7Capable(WMQConnection)", "cmdLevel < 700; not a v7 capable QM");
/*      */       }
/*      */       
/* 9231 */       if (throwException) {
/*      */ 
/*      */         
/* 9234 */         HashMap<String, Object> inserts = new HashMap<>();
/* 9235 */         inserts.put("XMSC_WMQ_PROVIDER_VERSION", requestedProviderVersion);
/* 9236 */         inserts.put("XMSC_INSERT_COMMAND_LEVEL", Integer.toString(cmdLevel));
/* 9237 */         Log.log(this, "checkConnectionPV7Capable()", "JMSFMQ0003", inserts);
/*      */         
/* 9239 */         JMSException je = (JMSException)NLSServices.createException("JMSFMQ0003", inserts);
/* 9240 */         if (Trace.isOn) {
/* 9241 */           Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "checkConnectionPV7Capable(WMQConnection,ProviderVersion,boolean)", (Throwable)je, 2);
/*      */         }
/*      */         
/* 9244 */         throw je;
/*      */       } 
/* 9246 */       traceRet1 = false;
/*      */     }
/* 9248 */     else if (sharingConversations != -1 && sharingConversations == 0) {
/*      */ 
/*      */ 
/*      */       
/* 9252 */       if (Trace.isOn) {
/* 9253 */         Trace.traceData(this, "checkConnectionPV7Capable(WMQConnection)", "sharingConversations == 0; not a v7 capable client connection");
/*      */       }
/*      */       
/* 9256 */       if (throwException) {
/*      */         
/* 9258 */         HashMap<String, Object> inserts = new HashMap<>();
/* 9259 */         inserts.put("XMSC_INSERT_VALUE", Integer.toString(sharingConversations));
/* 9260 */         inserts.put("XMSC_WMQ_PROVIDER_VERSION", requestedProviderVersion);
/* 9261 */         Log.log(this, "checkConnectionPV7Capable()", "JMSFMQ0004", inserts);
/*      */         
/* 9263 */         JMSException je = (JMSException)NLSServices.createException("JMSFMQ0004", inserts);
/* 9264 */         if (Trace.isOn) {
/* 9265 */           Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "checkConnectionPV7Capable(WMQConnection,ProviderVersion,boolean)", (Throwable)je, 3);
/*      */         }
/*      */         
/* 9268 */         throw je;
/*      */       } 
/* 9270 */       traceRet1 = false;
/*      */     } else {
/* 9272 */       if (requestedProviderVersion.version >= 8 && cmdLevel < 800) {
/*      */         
/* 9274 */         HashMap<String, Object> inserts = new HashMap<>();
/* 9275 */         inserts.put("XMSC_WMQ_PROVIDER_VERSION", requestedProviderVersion);
/* 9276 */         inserts.put("XMSC_INSERT_COMMAND_LEVEL", Integer.toString(cmdLevel));
/* 9277 */         Log.log(this, "checkConnectionPV7Capable()", "JMSFMQ0003", inserts);
/*      */         
/* 9279 */         JMSException je = (JMSException)NLSServices.createException("JMSFMQ0003", inserts);
/* 9280 */         if (Trace.isOn) {
/* 9281 */           Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "checkConnectionPV7Capable(WMQConnection,ProviderVersion,boolean)", (Throwable)je, 4);
/*      */         }
/*      */         
/* 9284 */         throw je;
/*      */       } 
/*      */       
/* 9287 */       if (Trace.isOn) {
/* 9288 */         Trace.traceData(this, "checkConnectionPV7Capable(WMQConnection)", "v7 capable QM and/or connection");
/*      */       }
/*      */ 
/*      */       
/* 9292 */       if (requestedProviderVersion.equals("unspecified")) {
/*      */         
/* 9294 */         if (cmdLevel >= 800) {
/* 9295 */           connection.setStringProperty("XMSC_WMQ_PROVIDER_VERSION", "8.0.0.0");
/*      */         } else {
/*      */           
/* 9298 */           connection.setStringProperty("XMSC_WMQ_PROVIDER_VERSION", "7.0.0.0");
/*      */         } 
/*      */       } else {
/*      */         
/* 9302 */         connection.setStringProperty("XMSC_WMQ_PROVIDER_VERSION", requestedProviderVersion.toString());
/*      */       } 
/*      */       
/* 9305 */       traceRet1 = true;
/*      */     } 
/*      */     
/* 9308 */     if (Trace.isOn) {
/* 9309 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "checkConnectionPV7Capable(WMQConnection,ProviderVersion,boolean)", 
/*      */           
/* 9311 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 9313 */     return traceRet1;
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean checkClientConnAllowedAnyServer(boolean isXA) {
/* 9318 */     if (Trace.isOn) {
/* 9319 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "checkClientConnAllowedAnyServer(boolean)", new Object[] {
/* 9320 */             Boolean.valueOf(isXA)
/*      */           });
/*      */     }
/* 9323 */     boolean forceAllowClientConnection = false;
/* 9324 */     boolean retval = false;
/*      */ 
/*      */     
/* 9327 */     PropertyStore.register("com.ibm.msg.client.wmq.forceAllowClientConnection", forceAllowClientConnection, true);
/* 9328 */     forceAllowClientConnection = PropertyStore.getBooleanProperty("com.ibm.msg.client.wmq.forceAllowClientConnection");
/*      */     
/* 9330 */     boolean isWAS = WASSupport.getWASSupport().isWASCommonServicesPresent();
/* 9331 */     boolean zSeries = ((WMQFactoryFactory)WMQFactoryFactory.getInstance()).iszSeries();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 9345 */     if (forceAllowClientConnection || !zSeries || isWAS) {
/* 9346 */       retval = true;
/*      */     } else {
/*      */       
/* 9349 */       retval = false;
/*      */     } 
/*      */     
/* 9352 */     if (Trace.isOn) {
/* 9353 */       Trace.traceData(this, "zSeries = " + zSeries, null);
/* 9354 */       Trace.traceData(this, "forceAllowClientConnection = " + forceAllowClientConnection, null);
/* 9355 */       Trace.traceData(this, "isWAS = " + isWAS, null);
/* 9356 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "checkClientConnAllowedAnyServer(boolean)", 
/* 9357 */           Boolean.valueOf(retval));
/*      */     } 
/* 9359 */     return retval;
/*      */   }
/*      */   
/*      */   protected void checkClientConnectionAllowed(boolean isXA) throws JMSException {
/* 9363 */     if (Trace.isOn) {
/* 9364 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "checkClientConnectionAllowed(boolean)", new Object[] {
/* 9365 */             Boolean.valueOf(isXA)
/*      */           });
/*      */     }
/* 9368 */     boolean clientAllowedAnyServer = checkClientConnAllowedAnyServer(isXA);
/*      */     
/* 9370 */     if (clientAllowedAnyServer || (!isXA && 
/* 9371 */       !this.jmqiEnvironment.isRunningUnderIMS() && !this.jmqiEnvironment.isRunningUnderCICS() && !this.jmqiEnvironment.isMaybeUnderCICS())) {
/* 9372 */       if (Trace.isOn) {
/* 9373 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "checkClientConnectionAllowed(boolean)");
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */ 
/*      */     
/* 9382 */     Log.log(this, "checkClientConnectionAllowed(boolean)", "JMSFMQ0005", null);
/*      */     
/* 9384 */     JMSException je = (JMSException)NLSServices.createException("JMSFMQ0005", null);
/* 9385 */     if (Trace.isOn) {
/* 9386 */       Trace.throwing(this, "com.ibm.msg.client.wmq.factories.WMQConnectionFactory", "checkClientConnectionAllowed(boolean)", (Throwable)je);
/*      */     }
/*      */     
/* 9389 */     throw je;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean equals(Object o) {
/* 9400 */     if (this == o) {
/* 9401 */       boolean bool = true;
/*      */     }
/*      */     
/* 9404 */     if (null == o || !(o instanceof WMQConnectionFactory)) {
/* 9405 */       boolean bool = false;
/*      */     }
/*      */     
/* 9408 */     boolean traceRet1 = super.equals(o);
/* 9409 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int hashCode() {
/* 9418 */     return super.hashCode();
/*      */   }
/*      */ 
/*      */   
/*      */   static class ProviderVersion
/*      */   {
/*      */     public boolean defaultPVer = true;
/* 9425 */     public int version = 0;
/* 9426 */     public int release = 0;
/* 9427 */     public int modification = 0;
/* 9428 */     public int fix = 0;
/*      */     
/*      */     public ProviderVersion(String providerVerP) {
/* 9431 */       if (Trace.isOn) {
/* 9432 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.ProviderVersion", "<init>(String)", new Object[] { providerVerP });
/*      */       }
/*      */       
/* 9435 */       String providerVer = providerVerP;
/*      */ 
/*      */ 
/*      */       
/* 9439 */       if (providerVer == null) {
/* 9440 */         providerVer = "unspecified";
/*      */       }
/*      */       
/* 9443 */       if (providerVer.equalsIgnoreCase("unspecified")) {
/* 9444 */         this.defaultPVer = true;
/* 9445 */         if (Trace.isOn) {
/* 9446 */           Trace.exit(this, "com.ibm.msg.client.wmq.factories.ProviderVersion", "<init>(String)", 1);
/*      */         }
/*      */         
/*      */         return;
/*      */       } 
/* 9451 */       this.defaultPVer = false;
/*      */ 
/*      */       
/* 9454 */       String[] providerVerElements = providerVer.split("\\.");
/* 9455 */       if (providerVerElements.length == 0) {
/* 9456 */         Trace.ffst(this, "createProviderConnection", "XT001002", null, JMSException.class);
/*      */       }
/*      */       
/* 9459 */       this.version = Integer.parseInt(providerVerElements[0]);
/* 9460 */       int len = providerVerElements.length;
/* 9461 */       if (len >= 2) {
/* 9462 */         this.release = Integer.parseInt(providerVerElements[1]);
/* 9463 */         if (len >= 3) {
/* 9464 */           this.modification = Integer.parseInt(providerVerElements[2]);
/* 9465 */           if (len == 4) {
/* 9466 */             this.fix = Integer.parseInt(providerVerElements[3]);
/*      */           }
/*      */         } 
/*      */       } 
/*      */       
/* 9471 */       if (Trace.isOn) {
/* 9472 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.ProviderVersion", "<init>(String)", 2);
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public String toString() {
/* 9479 */       if (Trace.isOn) {
/* 9480 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.ProviderVersion", "toString()");
/*      */       }
/* 9482 */       StringBuilder sb = new StringBuilder();
/* 9483 */       if (this.defaultPVer) {
/* 9484 */         if (Trace.isOn) {
/* 9485 */           Trace.exit(this, "com.ibm.msg.client.wmq.factories.ProviderVersion", "toString()", "unspecified", 1);
/*      */         }
/*      */         
/* 9488 */         return "unspecified";
/*      */       } 
/* 9490 */       sb.append(this.version).append(".");
/* 9491 */       sb.append(this.release).append(".");
/* 9492 */       sb.append(this.modification).append(".");
/* 9493 */       sb.append(this.fix);
/*      */       
/* 9495 */       String traceRet1 = sb.toString();
/* 9496 */       if (Trace.isOn) {
/* 9497 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.ProviderVersion", "toString()", traceRet1, 2);
/*      */       }
/*      */       
/* 9500 */       return traceRet1;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean equals(Object o) {
/* 9505 */       if (Trace.isOn) {
/* 9506 */         Trace.entry(this, "com.ibm.msg.client.wmq.factories.ProviderVersion", "equals(Object)", new Object[] { o });
/*      */       }
/*      */       
/* 9509 */       if (o instanceof String) {
/* 9510 */         boolean traceRet1 = toString().equalsIgnoreCase((String)o);
/* 9511 */         if (Trace.isOn) {
/* 9512 */           Trace.exit(this, "com.ibm.msg.client.wmq.factories.ProviderVersion", "equals(Object)", 
/* 9513 */               Boolean.valueOf(traceRet1), 1);
/*      */         }
/* 9515 */         return traceRet1;
/*      */       } 
/*      */       
/* 9518 */       if (!(o instanceof ProviderVersion)) {
/* 9519 */         boolean traceRet1 = false;
/* 9520 */         if (Trace.isOn) {
/* 9521 */           Trace.exit(this, "com.ibm.msg.client.wmq.factories.ProviderVersion", "equals(Object)", 
/* 9522 */               Boolean.valueOf(traceRet1), 3);
/*      */         }
/* 9524 */         return traceRet1;
/*      */       } 
/*      */       
/* 9527 */       ProviderVersion pv = (ProviderVersion)o;
/*      */       
/* 9529 */       boolean traceRet2 = (this.version == pv.version && this.release == pv.release && this.modification == pv.modification && this.fix == pv.fix);
/* 9530 */       if (Trace.isOn) {
/* 9531 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.ProviderVersion", "equals(Object)", 
/* 9532 */             Boolean.valueOf(traceRet2), 2);
/*      */       }
/* 9534 */       return traceRet2;
/*      */     }
/*      */ 
/*      */     
/*      */     public int HashCode() {
/* 9539 */       return hashCode();
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\factories\WMQConnectionFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */