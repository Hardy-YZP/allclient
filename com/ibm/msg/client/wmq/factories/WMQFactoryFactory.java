/*     */ package com.ibm.msg.client.wmq.factories;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.cssystem.CSSystem;
/*     */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*     */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.commonservices.trace.TraceFFSTInfo;
/*     */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*     */ import com.ibm.msg.client.provider.ProviderConnectionFactory;
/*     */ import com.ibm.msg.client.provider.ProviderDestination;
/*     */ import com.ibm.msg.client.provider.ProviderFactoryFactory;
/*     */ import com.ibm.msg.client.provider.ProviderJmsFactory;
/*     */ import com.ibm.msg.client.provider.ProviderMatchSpace;
/*     */ import com.ibm.msg.client.provider.ProviderMessageFactory;
/*     */ import com.ibm.msg.client.provider.ProviderMetaData;
/*     */ import com.ibm.msg.client.provider.ProviderXAConnectionFactory;
/*     */ import com.ibm.msg.client.wmq.common.internal.WMQDestination;
/*     */ import com.ibm.msg.client.wmq.common.internal.WMQFFSTInfo;
/*     */ import com.ibm.msg.client.wmq.factories.admin.WMQJmsFactory;
/*     */ import java.lang.reflect.Method;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
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
/*     */ public class WMQFactoryFactory
/*     */   implements ProviderFactoryFactory
/*     */ {
/*     */   static {
/*  57 */     if (Trace.isOn) {
/*  58 */       Trace.data("com.ibm.msg.client.wmq.factories.WMQFactoryFactory", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.factories/src/com/ibm/msg/client/wmq/factories/WMQFactoryFactory.java");
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
/*  69 */   private static Map<String, Object> capabilities = null;
/*     */   
/*     */   private static ProviderFactoryFactory factoryFactory;
/*     */   
/*  73 */   private static WMQJmsFactory providerJmsFactory = new WMQJmsFactory();
/*     */   
/*  75 */   private static ProviderMatchSpace providerMatchSpace = null;
/*     */   
/*  77 */   private static ProviderMessageFactory providerMessageFactory = new WMQMessageFactory();
/*     */   
/*     */   protected static final String osNameProperty = "os.name";
/*     */   
/*     */   protected static final String bitModeProperty = "com.ibm.vm.bitmode";
/*     */   
/*     */   protected static final String forceClientProperty = "com.ibm.msg.client.wmq.forceAllowClientConnection";
/*     */   
/*     */   protected static final String overrideProviderVersion = "com.ibm.msg.client.wmq.overrideProviderVersion";
/*     */   
/*     */   protected static final String standaloneTraceProperty = "com.ibm.msg.client.commonservices.trace.standalone";
/*     */   
/*     */   protected static final String overrideReplyToStyleDefault = "com.ibm.mq.jms.replyToStyle";
/*     */   private boolean iSeries = false;
/*     */   private boolean zSeries = false;
/*     */   private boolean zSeries64 = false;
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.factories/src/com/ibm/msg/client/wmq/factories/WMQFactoryFactory.java";
/*     */   
/*     */   static {
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.entry("com.ibm.msg.client.wmq.factories.WMQFactoryFactory", "static()");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 104 */       NLSServices.addCatalogue("com.ibm.msg.client.wmq.internal.resources.JMSWMQ_MessageResourceBundle", "JMSWMQ", WMQFactoryFactory.class);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 109 */       NLSServices.addCatalogue("com.ibm.msg.client.wmq.common.internal.resources.JMSCMQ_MessageResourceBundle", "JMSCMQ", WMQFactoryFactory.class);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 114 */       NLSServices.addCatalogue("com.ibm.msg.client.wmq.factories.resources.JMSFMQ_MessageResourceBundle", "JMSFMQ", WMQFactoryFactory.class);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 119 */       NLSServices.addCatalogue("com.ibm.msg.client.wmq.compat.jms.internal.services.resources.MQJMS_MessageResourceBundle", "MQJMS", WMQFactoryFactory.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 127 */       Trace.registerFFSTInfo((TraceFFSTInfo)new WMQFFSTInfo());
/*     */     
/*     */     }
/* 130 */     catch (Throwable e) {
/* 131 */       if (Trace.isOn) {
/* 132 */         Trace.data("com.ibm.msg.client.wmq.factories.WMQFactoryFactory", "static()", "Caught expected exception", e);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 137 */       System.err.println("Serious problem with class initialization:");
/* 138 */       e.printStackTrace();
/*     */ 
/*     */       
/* 141 */       System.err.println("End of information");
/* 142 */       System.err.flush();
/*     */     } 
/*     */     
/* 145 */     if (Trace.isOn) {
/* 146 */       Trace.exit("com.ibm.msg.client.wmq.factories.WMQFactoryFactory", "static()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ProviderFactoryFactory getInstance() {
/* 157 */     synchronized (WMQFactoryFactory.class) {
/* 158 */       if (factoryFactory == null) {
/* 159 */         factoryFactory = new WMQFactoryFactory();
/*     */       }
/*     */     } 
/*     */     
/* 163 */     if (Trace.isOn) {
/* 164 */       Trace.data("com.ibm.msg.client.wmq.factories.WMQFactoryFactory", "getInstance()", "getter", factoryFactory);
/*     */     }
/*     */     
/* 167 */     return factoryFactory;
/*     */   }
/*     */   
/*     */   protected WMQFactoryFactory() {
/* 171 */     if (Trace.isOn) {
/* 172 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQFactoryFactory", "<init>()");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 180 */     boolean forceAllowClientConnection = readBooleanMQEnvironmentSetting("forceAllowClientConnection");
/*     */ 
/*     */     
/* 183 */     PropertyStore.register("com.ibm.msg.client.wmq.forceAllowClientConnection", forceAllowClientConnection, true);
/*     */ 
/*     */     
/* 186 */     PropertyStore.register("com.ibm.msg.client.wmq.overrideProviderVersion", null);
/*     */ 
/*     */     
/* 189 */     PropertyStore.register("com.ibm.mq.jms.replyToStyle", null);
/*     */     
/* 191 */     PropertyStore.register("os.name", null, false);
/* 192 */     String os = PropertyStore.getStringProperty("os.name");
/* 193 */     if (os == null) {
/* 194 */       os = "";
/*     */     }
/*     */     
/* 197 */     if (os.equals("OS/390") || os.equals("z/OS")) {
/* 198 */       this.zSeries = true;
/* 199 */       PropertyStore.register("com.ibm.vm.bitmode", null, false);
/* 200 */       this.zSeries64 = "64".equals(PropertyStore.getStringProperty("com.ibm.vm.bitmode"));
/*     */     }
/* 202 */     else if (os.equals("OS/400") || os.equals("OS400")) {
/* 203 */       this.iSeries = true;
/*     */     } 
/*     */     
/* 206 */     if (Trace.isOn) {
/* 207 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQFactoryFactory", "<init>()");
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
/*     */   public ProviderConnectionFactory createProviderConnectionFactory(JmsPropertyContext properties) throws JMSException {
/* 219 */     if (Trace.isOn) {
/* 220 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQFactoryFactory", "createProviderConnectionFactory(JmsPropertyContext)", new Object[] { properties });
/*     */     }
/*     */     
/* 223 */     ProviderConnectionFactory pcf = new WMQConnectionFactory(properties);
/* 224 */     if (Trace.isOn) {
/* 225 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQFactoryFactory", "createProviderConnectionFactory(JmsPropertyContext)", pcf);
/*     */     }
/*     */     
/* 228 */     return pcf;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProviderXAConnectionFactory createProviderXAConnectionFactory(JmsPropertyContext properties) throws JMSException {
/* 238 */     if (Trace.isOn) {
/* 239 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQFactoryFactory", "createProviderXAConnectionFactory(JmsPropertyContext)", new Object[] { properties });
/*     */     }
/*     */     
/* 242 */     ProviderXAConnectionFactory pxcf = new WMQXAConnectionFactory(properties);
/* 243 */     if (Trace.isOn) {
/* 244 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQFactoryFactory", "createProviderXAConnectionFactory(JmsPropertyContext)", pxcf);
/*     */     }
/*     */     
/* 247 */     return pxcf;
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
/*     */   public ProviderDestination createProviderDestination(int destType, String name, JmsPropertyContext properties) throws JMSException {
/* 264 */     if (Trace.isOn) {
/* 265 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQFactoryFactory", "createProviderDestination(int,String,JmsPropertyContext)", new Object[] {
/*     */             
/* 267 */             Integer.valueOf(destType), name, properties
/*     */           });
/*     */     }
/* 270 */     WMQDestination destination = null;
/* 271 */     destination = new WMQDestination(destType, name, properties);
/*     */     
/* 273 */     if (Trace.isOn) {
/* 274 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQFactoryFactory", "createProviderDestination(int,String,JmsPropertyContext)", destination);
/*     */     }
/*     */     
/* 277 */     return (ProviderDestination)destination;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized Map<String, Object> getCapabilities() {
/* 284 */     if (null == capabilities) {
/* 285 */       capabilities = Collections.synchronizedMap(new HashMap<>());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 291 */       if (CSSystem.currentPlatform().equals(CSSystem.Platform.OS_NSS)) {
/* 292 */         capabilities.put("XMSC_CAPABILITY_TRANSACTIONS_XA", Boolean.valueOf(false));
/*     */       } else {
/*     */         
/* 295 */         capabilities.put("XMSC_CAPABILITY_TRANSACTIONS_XA", Boolean.valueOf(true));
/*     */       } 
/*     */       
/* 298 */       capabilities.put("XMSC_CAPABILITY_TRANSACTIONS_LOCAL", Boolean.valueOf(true));
/* 299 */       capabilities.put("XMSC_CAPABILITY_ASF", Boolean.valueOf(true));
/* 300 */       capabilities.put("XMSC_CAPABILITY_POINT_TO_POINT", Boolean.valueOf(true));
/* 301 */       capabilities.put("XMSC_CAPABILITY_PUBLISH_SUBSCRIBE", Boolean.valueOf(true));
/* 302 */       capabilities.put("XMSC_CAPABILITY_USERNAME_PASSWORD", Boolean.valueOf(true));
/*     */     } 
/* 304 */     if (Trace.isOn) {
/* 305 */       Trace.data(this, "com.ibm.msg.client.wmq.factories.WMQFactoryFactory", "getCapabilities()", "getter", capabilities);
/*     */     }
/*     */     
/* 308 */     return capabilities;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProviderJmsFactory getJmsFactory() {
/* 316 */     if (Trace.isOn) {
/* 317 */       Trace.data(this, "com.ibm.msg.client.wmq.factories.WMQFactoryFactory", "getJmsFactory()", "getter", providerJmsFactory);
/*     */     }
/*     */     
/* 320 */     return (ProviderJmsFactory)providerJmsFactory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProviderMatchSpace getMatchSpace() throws JMSException {
/* 330 */     if (Trace.isOn) {
/* 331 */       Trace.data(this, "com.ibm.msg.client.wmq.factories.WMQFactoryFactory", "getMatchSpace()", "getter", providerMatchSpace);
/*     */     }
/*     */     
/* 334 */     return providerMatchSpace;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProviderMessageFactory getMessageFactory() throws JMSException {
/* 343 */     if (Trace.isOn) {
/* 344 */       Trace.data(this, "com.ibm.msg.client.wmq.factories.WMQFactoryFactory", "getMessageFactory()", "getter", providerMessageFactory);
/*     */     }
/*     */     
/* 347 */     return providerMessageFactory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public ProviderMetaData getMetaData() throws JMSException {
/* 356 */     if (Trace.isOn) {
/* 357 */       Trace.data(this, "com.ibm.msg.client.wmq.factories.WMQFactoryFactory", "getMetaData()", "getter", null);
/*     */     }
/*     */     
/* 360 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean readBooleanMQEnvironmentSetting(final String methodName) {
/* 368 */     if (Trace.isOn) {
/* 369 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQFactoryFactory", "readBooleanMQEnvironmentSetting(final String)", new Object[] { methodName });
/*     */     }
/*     */ 
/*     */     
/* 373 */     Boolean isEnabled = AccessController.<Boolean>doPrivileged(new PrivilegedAction<Boolean>()
/*     */         {
/*     */           public Boolean run() {
/* 376 */             if (Trace.isOn) {
/* 377 */               Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQFactoryFactory", "run()");
/*     */             }
/* 379 */             Boolean enabled = null;
/*     */             try {
/* 381 */               Class<?> mqenv = CSSystem.dynamicLoadClass("com.ibm.mq.MQEnvironment", getClass());
/*     */               
/* 383 */               if (mqenv == null) {
/*     */                 
/* 385 */                 enabled = Boolean.FALSE;
/*     */               }
/*     */               else {
/*     */                 
/* 389 */                 Class<?> c = mqenv;
/* 390 */                 if (Trace.isOn) {
/* 391 */                   Trace.data(this, "<init>", "class", c);
/*     */                 }
/*     */                 
/* 394 */                 Method m = c.getMethod(methodName, (Class[])null);
/* 395 */                 Object returnVal = m.invoke(null, (Object[])null);
/* 396 */                 if (Trace.isOn) {
/* 397 */                   Trace.data(this, "<init>", "Found method - it returned", returnVal);
/*     */                 }
/*     */                 
/* 400 */                 if (returnVal instanceof Boolean) {
/* 401 */                   enabled = (Boolean)returnVal;
/*     */                 } else {
/*     */                   
/* 404 */                   enabled = Boolean.FALSE;
/*     */                 }
/*     */               
/*     */               } 
/* 408 */             } catch (ClassNotFoundException e) {
/* 409 */               if (Trace.isOn) {
/* 410 */                 Trace.data(this, "com.ibm.msg.client.wmq.factories.null", "run()", "Caught expected exception at catch index 1", e);
/*     */               }
/*     */ 
/*     */ 
/*     */               
/* 415 */               enabled = Boolean.FALSE;
/*     */             }
/* 417 */             catch (Exception ex) {
/* 418 */               if (Trace.isOn) {
/* 419 */                 Trace.catchBlock(this, "com.ibm.msg.client.wmq.factories.null", "run()", ex, 2);
/*     */               }
/*     */               
/* 422 */               enabled = Boolean.FALSE;
/*     */             }
/* 424 */             catch (NoClassDefFoundError error) {
/* 425 */               if (Trace.isOn) {
/* 426 */                 Trace.catchBlock(this, "com.ibm.msg.client.wmq.factories.null", "run()", error, 3);
/*     */               }
/*     */               
/* 429 */               enabled = Boolean.FALSE;
/*     */             } 
/*     */             
/* 432 */             if (Trace.isOn) {
/* 433 */               Trace.exit(this, "com.ibm.msg.client.wmq.factories.null", "run()", enabled);
/*     */             }
/* 435 */             return enabled;
/*     */           }
/*     */         });
/*     */     
/* 439 */     boolean traceRet1 = isEnabled.booleanValue();
/* 440 */     if (Trace.isOn) {
/* 441 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQFactoryFactory", "readBooleanMQEnvironmentSetting(final String)", 
/* 442 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 444 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isiSeries() {
/* 451 */     return this.iSeries;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean iszSeries() {
/* 458 */     return this.zSeries;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean iszSeries64() {
/* 465 */     return this.zSeries64;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\factories\WMQFactoryFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */