/*      */ package com.ibm.msg.client.commonservices.j2se.propertystore;
/*      */ 
/*      */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*      */ import com.ibm.msg.client.commonservices.propertystore.PropertiesException;
/*      */ import com.ibm.msg.client.commonservices.propertystore.PropertiesFileException;
/*      */ import com.ibm.msg.client.commonservices.provider.propertystore.CSPPropertyStore;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.net.MalformedURLException;
/*      */ import java.net.URL;
/*      */ import java.security.AccessControlException;
/*      */ import java.security.AccessController;
/*      */ import java.security.PrivilegedAction;
/*      */ import java.util.Enumeration;
/*      */ import java.util.HashMap;
/*      */ import java.util.Map;
/*      */ import java.util.Properties;
/*      */ import java.util.concurrent.ConcurrentHashMap;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class PropertyStoreImpl
/*      */   implements CSPPropertyStore
/*      */ {
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices.j2se/src/com/ibm/msg/client/commonservices/j2se/propertystore/PropertyStoreImpl.java";
/*      */   
/*      */   static {
/*   51 */     if (Trace.isOn) {
/*   52 */       Trace.data("com.ibm.msg.client.commonservices.j2se.propertystore.PropertyStoreImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices.j2se/src/com/ibm/msg/client/commonservices/j2se/propertystore/PropertyStoreImpl.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   61 */   private HashMap<String, Object> currentprops = new HashMap<>();
/*   62 */   private Map<String, PropertyDefinition> propDefns = new ConcurrentHashMap<>();
/*      */ 
/*      */   
/*      */   private boolean successfullyReadAllSystemProperties = false;
/*      */ 
/*      */   
/*      */   public PropertyStoreImpl() {
/*   69 */     if (Trace.isOn) {
/*   70 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.propertystore.PropertyStoreImpl", "<init>()");
/*      */     }
/*      */ 
/*      */     
/*   74 */     Properties sysprops = AccessController.<Properties>doPrivileged(new PrivilegedAction<Properties>()
/*      */         {
/*      */           public Properties run()
/*      */           {
/*   78 */             if (Trace.isOn) {
/*   79 */               Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.propertystore.PropertyStoreImpl", "run()");
/*      */             }
/*      */             
/*      */             try {
/*   83 */               Properties traceRet1 = System.getProperties();
/*   84 */               PropertyStoreImpl.this.successfullyReadAllSystemProperties = true;
/*   85 */               if (Trace.isOn) {
/*   86 */                 Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.propertystore.null", "run()", traceRet1, 1);
/*      */               }
/*      */               
/*   89 */               return traceRet1;
/*      */             }
/*   91 */             catch (AccessControlException ace) {
/*   92 */               if (Trace.isOn) {
/*   93 */                 Trace.catchBlock(this, "com.ibm.msg.client.commonservices.j2se.propertystore.null", "run()", ace);
/*      */               }
/*      */               
/*   96 */               if (Trace.isOn) {
/*   97 */                 Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.propertystore.null", "run()", null, 2);
/*      */               }
/*      */               
/*  100 */               return null;
/*      */             } 
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  106 */     if (null != sysprops) {
/*  107 */       load(sysprops, null, CSPPropertyStore.PropertySource.SYS_PROP);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  115 */     String configFileURL = null;
/*  116 */     URL url = null;
/*  117 */     configFileURL = (String)this.currentprops.get("com.ibm.msg.client.config.location");
/*  118 */     if (configFileURL == null) {
/*      */       
/*  120 */       url = ClassLoader.getSystemResource("META-INF/jms.config");
/*      */     } else {
/*      */       
/*      */       try {
/*  124 */         HashMap<String, Object> inserts = new HashMap<>();
/*  125 */         inserts.put("XMSC_CONFIG_FILE_LOCATION", configFileURL);
/*      */ 
/*      */         
/*  128 */         if (Trace.isOn) {
/*  129 */           Trace.traceData(this, "<init()", "Reading PropertyStore Properties from config file", inserts);
/*      */         }
/*      */ 
/*      */         
/*  133 */         url = new URL(configFileURL);
/*      */       }
/*  135 */       catch (MalformedURLException ex) {
/*  136 */         if (Trace.isOn) {
/*  137 */           Trace.catchBlock(this, "com.ibm.msg.client.commonservices.j2se.propertystore.PropertyStoreImpl", "<init>()", ex, 1);
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  144 */     if (url != null) {
/*      */       try {
/*  146 */         InputStream stream = null;
/*  147 */         stream = url.openStream();
/*  148 */         if (stream != null) {
/*  149 */           Properties configprops = new Properties();
/*  150 */           configprops.load(stream);
/*  151 */           load(configprops, null, CSPPropertyStore.PropertySource.CONFIG_URL);
/*      */         }
/*      */       
/*  154 */       } catch (Exception ex) {
/*  155 */         if (Trace.isOn) {
/*  156 */           Trace.catchBlock(this, "com.ibm.msg.client.commonservices.j2se.propertystore.PropertyStoreImpl", "<init>()", ex, 2);
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  164 */     if (Trace.isOn) {
/*  165 */       Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.propertystore.PropertyStoreImpl", "<init>()");
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
/*      */   public HashMap<String, Object> getAll() {
/*  182 */     HashMap<String, Object> traceRet1 = (HashMap<String, Object>)this.currentprops.clone();
/*  183 */     if (Trace.isOn) {
/*  184 */       Trace.data(this, "com.ibm.msg.client.commonservices.j2se.propertystore.PropertyStoreImpl", "getAll()", "getter", traceRet1);
/*      */     }
/*      */     
/*  187 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getStringProperty(String name) {
/*  197 */     Object o1 = this.propDefns.get(name);
/*  198 */     PropertyDefinition def = null;
/*  199 */     if (o1 == null) {
/*  200 */       HashMap<String, Object> info = new HashMap<>();
/*  201 */       info.put("name", name);
/*  202 */       Trace.ffst(this, "getStringProperty(String)", "XS002001", info, null);
/*      */     } else {
/*      */       
/*  205 */       def = (PropertyDefinition)o1;
/*  206 */       if (PropertyDefinition.Value.TYPE_STRING != def.type && def.source != CSPPropertyStore.PropertySource.CONFIG_FILE && def.source != CSPPropertyStore.PropertySource.CONFIG_URL && def.source != CSPPropertyStore.PropertySource.SYS_PROP) {
/*  207 */         HashMap<String, Object> info = new HashMap<>();
/*  208 */         info.put("type", def.type.name());
/*  209 */         Trace.ffst(this, "getStringProperty(String)", "XS002002", info, null);
/*      */       } 
/*      */ 
/*      */       
/*  213 */       if (true == def.controlled) {
/*  214 */         return def.defaultString;
/*      */       }
/*      */     } 
/*      */     
/*  218 */     Object o2 = getProperty(name);
/*  219 */     if (null != o2) {
/*  220 */       String traceRet1 = (String)o2;
/*  221 */       return traceRet1;
/*      */     } 
/*      */     
/*  224 */     return (def == null) ? null : def.defaultString;
/*      */   }
/*      */   
/*      */   protected Object getProperty(String name) {
/*  228 */     if (Trace.isOn) {
/*  229 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.propertystore.PropertyStoreImpl", "getProperty(String)", new Object[] { name });
/*      */     }
/*      */     
/*  232 */     Object o = this.currentprops.get(name);
/*  233 */     if (o != null) {
/*  234 */       if (Trace.isOn) {
/*  235 */         Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.propertystore.PropertyStoreImpl", "getProperty(String)", o, 1);
/*      */       }
/*      */       
/*  238 */       return o;
/*      */     } 
/*      */     
/*  241 */     if (!this.successfullyReadAllSystemProperties) {
/*      */ 
/*      */ 
/*      */       
/*  245 */       Object sysPropValue = readSystemProperty(name);
/*  246 */       if (sysPropValue != null) {
/*      */ 
/*      */ 
/*      */         
/*  250 */         if (Trace.isOn) {
/*  251 */           Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.propertystore.PropertyStoreImpl", "getProperty(String)", sysPropValue, 2);
/*      */         }
/*      */ 
/*      */         
/*  255 */         return sysPropValue;
/*      */       } 
/*      */     } 
/*  258 */     if (Trace.isOn) {
/*  259 */       Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.propertystore.PropertyStoreImpl", "getProperty(String)", null, 3);
/*      */     }
/*      */     
/*  262 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private String readSystemProperty(final String name) {
/*  268 */     return AccessController.<String>doPrivileged(new PrivilegedAction<String>()
/*      */         {
/*      */           public String run()
/*      */           {
/*      */             try {
/*  273 */               return System.getProperty(name);
/*      */             }
/*  275 */             catch (AccessControlException ace) {
/*      */               
/*  277 */               return null;
/*      */             } 
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getLongProperty(String name) {
/*  290 */     Object o1 = this.propDefns.get(name);
/*  291 */     PropertyDefinition def = null;
/*      */     
/*  293 */     if (o1 == null) {
/*  294 */       HashMap<String, Object> info = new HashMap<>();
/*  295 */       info.put("name", name);
/*  296 */       Trace.ffst(this, "getLongProperty(String)", "XS002003", info, null);
/*      */     } else {
/*      */       
/*  299 */       def = (PropertyDefinition)o1;
/*  300 */       if (PropertyDefinition.Value.TYPE_LONG != def.type && def.source != CSPPropertyStore.PropertySource.CONFIG_FILE && def.source != CSPPropertyStore.PropertySource.CONFIG_URL && def.source != CSPPropertyStore.PropertySource.SYS_PROP) {
/*  301 */         HashMap<String, Object> info = new HashMap<>();
/*  302 */         info.put("type", def.type.name());
/*  303 */         Trace.ffst(this, "getLongProperty(String)", "XS002004", info, null);
/*      */       } 
/*      */ 
/*      */       
/*  307 */       if (true == def.controlled) {
/*  308 */         return def.defaultLong;
/*      */       }
/*      */     } 
/*      */     
/*  312 */     Object o2 = getProperty(name);
/*  313 */     if (null == o2) {
/*  314 */       return ((def == null) ? null : Long.valueOf(def.defaultLong)).longValue();
/*      */     }
/*      */     
/*  317 */     long l = 0L;
/*      */     try {
/*  319 */       l = Long.parseLong(((String)o2).trim());
/*      */     }
/*  321 */     catch (NumberFormatException nfe) {
/*  322 */       return ((def == null) ? null : Long.valueOf(def.defaultLong)).longValue();
/*      */     } 
/*      */     
/*  325 */     if (def != null) {
/*  326 */       if (null != def.minValid && l < def.minValid.longValue()) {
/*  327 */         return def.defaultLong;
/*      */       }
/*      */       
/*  330 */       if (null != def.maxValid && l > def.maxValid.longValue()) {
/*  331 */         return def.defaultLong;
/*      */       }
/*      */     } 
/*      */     
/*  335 */     return l;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getBooleanProperty(String name) {
/*  345 */     Object o1 = this.propDefns.get(name);
/*  346 */     PropertyDefinition def = null;
/*      */     
/*  348 */     if (o1 == null) {
/*  349 */       HashMap<String, Object> info = new HashMap<>();
/*  350 */       info.put("name", name);
/*  351 */       Trace.ffst(this, "getBooleanProperty(String)", "XS002005", info, null);
/*      */     } else {
/*      */       
/*  354 */       def = (PropertyDefinition)o1;
/*  355 */       if (PropertyDefinition.Value.TYPE_BOOLEAN != def.type && def.source != CSPPropertyStore.PropertySource.CONFIG_FILE && def.source != CSPPropertyStore.PropertySource.CONFIG_URL && def.source != CSPPropertyStore.PropertySource.SYS_PROP) {
/*  356 */         HashMap<String, Object> info = new HashMap<>();
/*  357 */         info.put("type", def.type.name());
/*  358 */         Trace.ffst(this, "getBooleanProperty(String)", "XS002006", info, null);
/*      */       } 
/*      */ 
/*      */       
/*  362 */       if (true == def.controlled) {
/*  363 */         return def.defaultBoolean;
/*      */       }
/*      */     } 
/*      */     
/*  367 */     Object o2 = getProperty(name);
/*  368 */     if (null != o2) {
/*  369 */       String s = ((String)o2).trim();
/*  370 */       if (s.equalsIgnoreCase("true")) {
/*  371 */         return true;
/*      */       }
/*  373 */       if (s.equalsIgnoreCase("false")) {
/*  374 */         return false;
/*      */       }
/*      */     } 
/*  377 */     return (def == null) ? false : def.defaultBoolean;
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
/*      */   public Object getObjectProperty(String name) {
/*  393 */     Object o1 = this.propDefns.get(name);
/*      */ 
/*      */     
/*  396 */     if (o1 == null) {
/*  397 */       HashMap<String, Object> info = new HashMap<>();
/*  398 */       info.put("name", name);
/*  399 */       Trace.ffst(this, "getObjectProperty(String)", "XS00200A", info, null);
/*      */       
/*  401 */       Object object = null;
/*  402 */       if (this.currentprops.containsKey(name)) {
/*  403 */         object = getProperty(name);
/*      */       }
/*      */       
/*  406 */       return object;
/*      */     } 
/*      */     
/*  409 */     PropertyDefinition def = (PropertyDefinition)o1;
/*  410 */     if (PropertyDefinition.Value.TYPE_OBJECT == PropertyDefinition.Value.YET_TO_DEFN) {
/*  411 */       HashMap<String, Object> info = new HashMap<>();
/*  412 */       info.put("type", def.type.name());
/*  413 */       info.put("source", def.source.name());
/*  414 */       info.put("Property name", name);
/*      */       
/*  416 */       Trace.ffst(this, "getObjectProperty(String)", "XS00200B", info, null);
/*      */     } 
/*      */ 
/*      */     
/*  420 */     if (true == def.controlled)
/*      */     {
/*  422 */       return def.getDefault();
/*      */     }
/*      */ 
/*      */     
/*  426 */     Object retVal = def.getDefault();
/*      */     
/*  428 */     if (this.currentprops.containsKey(name)) {
/*  429 */       retVal = getProperty(name);
/*      */     }
/*      */     
/*  432 */     return retVal;
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
/*      */   public void set(String name, String value) {
/*  444 */     this.currentprops.put(name, value);
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
/*      */   @Deprecated
/*      */   public void set(String name, Object value) {
/*  462 */     this.currentprops.put(name, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addPropertiesFile(String filename, String namespace, boolean optional) throws PropertiesFileException {
/*  473 */     if (Trace.isOn) {
/*  474 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.propertystore.PropertyStoreImpl", "addPropertiesFile(String,String,boolean)", new Object[] { filename, namespace, 
/*      */             
/*  476 */             Boolean.valueOf(optional) });
/*      */     }
/*      */     
/*  479 */     Properties props = new Properties();
/*  480 */     FileInputStream fis = null;
/*      */     try {
/*  482 */       fis = new FileInputStream(filename);
/*  483 */       props.load(fis);
/*      */     }
/*  485 */     catch (IOException ioe) {
/*  486 */       if (Trace.isOn) {
/*  487 */         Trace.catchBlock(this, "com.ibm.msg.client.commonservices.j2se.propertystore.PropertyStoreImpl", "addPropertiesFile(String,String,boolean)", ioe, 1);
/*      */       }
/*      */ 
/*      */       
/*  491 */       if (!optional) {
/*  492 */         HashMap<String, Object> info = new HashMap<>();
/*  493 */         info.put("file", filename);
/*  494 */         info.put("namespace", namespace);
/*  495 */         Trace.ffst(this, "addPropertiesFile(String,String,boolean)", "XS002007", info, null);
/*      */       } 
/*      */     } finally {
/*      */       
/*  499 */       if (Trace.isOn) {
/*  500 */         Trace.finallyBlock(this, "com.ibm.msg.client.commonservices.j2se.propertystore.PropertyStoreImpl", "addPropertiesFile(String,String,boolean)");
/*      */       }
/*      */ 
/*      */       
/*  504 */       if (fis != null) {
/*      */         try {
/*  506 */           fis.close();
/*      */         }
/*  508 */         catch (IOException e) {
/*  509 */           if (Trace.isOn) {
/*  510 */             Trace.catchBlock(this, "com.ibm.msg.client.commonservices.j2se.propertystore.PropertyStoreImpl", "addPropertiesFile(String,String,boolean)", e, 2);
/*      */           }
/*      */         } 
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  519 */     load(props, namespace, CSPPropertyStore.PropertySource.CONFIG_FILE);
/*  520 */     if (Trace.isOn) {
/*  521 */       Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.propertystore.PropertyStoreImpl", "addPropertiesFile(String,String,boolean)");
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
/*      */   protected void load(Properties newprops, String namespaceArg, CSPPropertyStore.PropertySource source) {
/*  538 */     if (Trace.isOn) {
/*  539 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.propertystore.PropertyStoreImpl", "load(Properties,String)", new Object[] { newprops, namespaceArg });
/*      */     }
/*      */ 
/*      */     
/*  543 */     String namespace = namespaceArg;
/*      */ 
/*      */     
/*  546 */     if (null != namespace && !namespace.endsWith(".")) {
/*  547 */       namespace = namespace + '.';
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  554 */     Enumeration<?> keys = newprops.propertyNames();
/*  555 */     while (keys.hasMoreElements()) {
/*  556 */       String oldkey = (String)keys.nextElement();
/*  557 */       String newkey = oldkey;
/*      */ 
/*      */       
/*  560 */       if (-1 == newkey.indexOf('.') && null != namespace) {
/*  561 */         newkey = namespace + newkey;
/*      */       }
/*      */ 
/*      */       
/*  565 */       if (isControlledProperty(newkey) != true)
/*      */       {
/*  567 */         if (null == this.currentprops.get(newkey)) {
/*  568 */           String value = newprops.getProperty(oldkey);
/*  569 */           this.currentprops.put(newkey, value);
/*      */ 
/*      */           
/*  572 */           PropertyDefinition pd = this.propDefns.get(newkey);
/*  573 */           if (pd != null) {
/*      */             
/*  575 */             if (pd.getSource() == CSPPropertyStore.PropertySource.REGISTERED_ONLY) {
/*  576 */               pd.setSource(source); continue;
/*      */             } 
/*  578 */             HashMap<String, Object> info = new HashMap<>();
/*  579 */             info.put("PropertyDefinition", pd);
/*  580 */             info.put("newkey", newkey);
/*  581 */             Trace.ffst(this, "load(Properties,String)", "XS002010", info, null);
/*      */             continue;
/*      */           } 
/*  584 */           pd = new PropertyDefinition(source);
/*  585 */           this.propDefns.put(newkey, pd);
/*      */         } 
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  591 */     if (Trace.isOn) {
/*  592 */       Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.propertystore.PropertyStoreImpl", "load(Properties,String)");
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
/*      */   public void register(String name, String defaultValue, boolean controlled) {
/*  611 */     if (this.propDefns.containsKey(name)) {
/*      */ 
/*      */       
/*  614 */       if (Trace.isOn) {
/*  615 */         Trace.data(this, "com.ibm.msg.client.commonservices.j2se.propertystore.PropertyStoreImpl", "register(String,String,boolean)", new Object[] { name, defaultValue, 
/*      */               
/*  617 */               Boolean.valueOf(controlled) });
/*      */       }
/*  619 */       PropertyDefinition prop = this.propDefns.get(name);
/*  620 */       if (prop.source != CSPPropertyStore.PropertySource.REGISTERED_ONLY) {
/*  621 */         prop.controlled = controlled;
/*  622 */         prop.defaultString = defaultValue;
/*  623 */         prop.type = PropertyDefinition.Value.TYPE_STRING;
/*      */       } 
/*      */       
/*  626 */       if (Trace.isOn) {
/*  627 */         Trace.data(this, "com.ibm.msg.client.commonservices.j2se.propertystore.PropertyStoreImpl", "register(String,String,boolean)", "Failed. Property already registered", (prop == null) ? null : prop
/*      */             
/*  629 */             .defaultString);
/*      */       }
/*      */     } else {
/*      */       
/*  633 */       PropertyDefinition def = new PropertyDefinition(defaultValue, controlled);
/*  634 */       this.propDefns.put(name, def);
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
/*      */   public void register(String name, boolean defaultValue, boolean controlled) {
/*  651 */     if (this.propDefns.containsKey(name)) {
/*      */ 
/*      */       
/*  654 */       if (Trace.isOn) {
/*  655 */         Trace.data(this, "com.ibm.msg.client.commonservices.j2se.propertystore.PropertyStoreImpl", "register(String,boolean,boolean)", new Object[] { name, 
/*      */               
/*  657 */               Boolean.valueOf(defaultValue), Boolean.valueOf(controlled) });
/*      */       }
/*      */       
/*  660 */       PropertyDefinition prop = this.propDefns.get(name);
/*  661 */       if (prop.source != CSPPropertyStore.PropertySource.REGISTERED_ONLY) {
/*  662 */         prop.controlled = controlled;
/*  663 */         prop.defaultBoolean = defaultValue;
/*  664 */         prop.type = PropertyDefinition.Value.TYPE_BOOLEAN;
/*      */       } 
/*  666 */       if (Trace.isOn) {
/*  667 */         Trace.data(this, "com.ibm.msg.client.commonservices.j2se.propertystore.PropertyStoreImpl", "register(String,boolean,boolean)", "Failed. Property already registered", (prop == null) ? null : 
/*      */             
/*  669 */             Boolean.valueOf(prop.defaultBoolean));
/*      */       }
/*      */     } else {
/*      */       
/*  673 */       PropertyDefinition def = new PropertyDefinition(defaultValue, controlled);
/*  674 */       this.propDefns.put(name, def);
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
/*      */   public void register(String name, Object defaultValue, boolean controlled) {
/*  690 */     if (this.propDefns.containsKey(name)) {
/*      */ 
/*      */       
/*  693 */       if (Trace.isOn) {
/*  694 */         Trace.data(this, "com.ibm.msg.client.commonservices.j2se.propertystore.PropertyStoreImpl", "register(String,Object,boolean)", new Object[] { name, defaultValue, 
/*      */               
/*  696 */               Boolean.valueOf(controlled) });
/*  697 */         PropertyDefinition prop = this.propDefns.get(name);
/*  698 */         if (prop.source != CSPPropertyStore.PropertySource.REGISTERED_ONLY) {
/*  699 */           prop.controlled = controlled;
/*  700 */           prop.defaultObject = defaultValue;
/*      */         } 
/*      */         
/*  703 */         Trace.data(this, "com.ibm.msg.client.commonservices.j2se.propertystore.PropertyStoreImpl", "register(String,Object,boolean)", "Failed. Property already registered", (prop == null) ? null : prop
/*      */             
/*  705 */             .defaultObject);
/*      */       } 
/*      */     } else {
/*      */       
/*  709 */       PropertyDefinition def = new PropertyDefinition(defaultValue, controlled);
/*  710 */       this.propDefns.put(name, def);
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
/*      */   public void register(String name, long defaultValue, Long minValid, Long maxValid, boolean controlled) {
/*  730 */     if (this.propDefns.containsKey(name)) {
/*      */ 
/*      */       
/*  733 */       if (Trace.isOn) {
/*  734 */         Trace.data(this, "com.ibm.msg.client.commonservices.j2se.propertystore.PropertyStoreImpl", "register(String,long,Long,Long,boolean)", new Object[] { name, 
/*      */               
/*  736 */               Long.valueOf(defaultValue), Boolean.valueOf(controlled) });
/*      */       }
/*  738 */       PropertyDefinition prop = this.propDefns.get(name);
/*  739 */       if (prop.source != CSPPropertyStore.PropertySource.REGISTERED_ONLY) {
/*  740 */         prop.controlled = controlled;
/*  741 */         prop.defaultLong = defaultValue;
/*  742 */         prop.minValid = minValid;
/*  743 */         prop.maxValid = maxValid;
/*  744 */         prop.type = PropertyDefinition.Value.TYPE_LONG;
/*      */       } 
/*      */ 
/*      */       
/*  748 */       if (Trace.isOn) {
/*  749 */         Trace.data(this, "com.ibm.msg.client.commonservices.j2se.propertystore.PropertyStoreImpl", "register(String,long,Long,Long,boolean)", "Failed. Property already registered", (prop == null) ? null : 
/*      */             
/*  751 */             Long.valueOf(prop.defaultLong));
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/*  756 */       PropertyDefinition def = new PropertyDefinition(defaultValue, minValid, maxValid, controlled);
/*  757 */       this.propDefns.put(name, def);
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
/*      */   public boolean isControlledProperty(String name) {
/*  772 */     boolean controlledProperty = false;
/*  773 */     PropertyDefinition def = this.propDefns.get(name);
/*  774 */     if (def != null) {
/*  775 */       controlledProperty = def.controlled;
/*      */     }
/*  777 */     return controlledProperty;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean wasOverridden(String name, StringBuffer unparsable) {
/*      */     long l;
/*      */     HashMap<String, Object> info;
/*  790 */     if (Trace.isOn) {
/*  791 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.propertystore.PropertyStoreImpl", "wasOverridden(String,StringBuffer)", new Object[] { name, unparsable });
/*      */     }
/*      */     
/*  794 */     Object o = getProperty(name);
/*  795 */     if (null == o) {
/*  796 */       if (Trace.isOn) {
/*  797 */         Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.propertystore.PropertyStoreImpl", "wasOverridden(String,StringBuffer)", 
/*  798 */             Boolean.valueOf(false), 1);
/*      */       }
/*  800 */       return false;
/*      */     } 
/*  802 */     String value = (String)o;
/*      */     
/*  804 */     if (null == unparsable) {
/*  805 */       if (Trace.isOn) {
/*  806 */         Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.propertystore.PropertyStoreImpl", "wasOverridden(String,StringBuffer)", 
/*  807 */             Boolean.valueOf(true), 2);
/*      */       }
/*  809 */       return true;
/*      */     } 
/*      */     
/*  812 */     if (unparsable.length() > 0) {
/*  813 */       unparsable.delete(0, unparsable.length());
/*      */     }
/*      */     
/*  816 */     o = this.propDefns.get(name);
/*  817 */     if (o == null) {
/*  818 */       HashMap<String, Object> hashMap = new HashMap<>();
/*  819 */       hashMap.put("name", name);
/*  820 */       Trace.ffst(this, "wasOverridden(String,StringBuffer)", "XS002008", hashMap, null);
/*      */       
/*  822 */       if (Trace.isOn) {
/*  823 */         Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.propertystore.PropertyStoreImpl", "wasOverridden(String,StringBuffer)", 
/*  824 */             Boolean.valueOf(false), 3);
/*      */       }
/*  826 */       return false;
/*      */     } 
/*      */     
/*  829 */     PropertyDefinition def = (PropertyDefinition)o;
/*  830 */     switch (def.type) {
/*      */       case TYPE_STRING:
/*      */         break;
/*      */ 
/*      */       
/*      */       case TYPE_BOOLEAN:
/*  836 */         if (!value.equalsIgnoreCase("true") && !value.equalsIgnoreCase("false")) {
/*  837 */           unparsable.append(value);
/*      */         }
/*      */         break;
/*      */       
/*      */       case TYPE_LONG:
/*  842 */         l = 0L;
/*      */         try {
/*  844 */           l = Long.parseLong(value);
/*      */         }
/*  846 */         catch (NumberFormatException nfe) {
/*  847 */           if (Trace.isOn) {
/*  848 */             Trace.catchBlock(this, "com.ibm.msg.client.commonservices.j2se.propertystore.PropertyStoreImpl", "wasOverridden(String,StringBuffer)", nfe);
/*      */           }
/*      */ 
/*      */           
/*  852 */           unparsable.append(value);
/*  853 */           if (Trace.isOn) {
/*  854 */             Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.propertystore.PropertyStoreImpl", "wasOverridden(String,StringBuffer)", 
/*      */                 
/*  856 */                 Boolean.valueOf(true), 4);
/*      */           }
/*  858 */           return true;
/*      */         } 
/*      */         
/*  861 */         if (null != def.minValid && l < def.minValid.longValue()) {
/*  862 */           unparsable.append(value);
/*  863 */           if (Trace.isOn) {
/*  864 */             Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.propertystore.PropertyStoreImpl", "wasOverridden(String,StringBuffer)", 
/*      */                 
/*  866 */                 Boolean.valueOf(true), 5);
/*      */           }
/*  868 */           return true;
/*      */         } 
/*      */         
/*  871 */         if (null != def.maxValid && l > def.maxValid.longValue()) {
/*  872 */           unparsable.append(value);
/*  873 */           if (Trace.isOn) {
/*  874 */             Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.propertystore.PropertyStoreImpl", "wasOverridden(String,StringBuffer)", 
/*      */                 
/*  876 */                 Boolean.valueOf(true), 6);
/*      */           }
/*  878 */           return true;
/*      */         } 
/*      */         break;
/*      */       
/*      */       default:
/*  883 */         info = new HashMap<>();
/*  884 */         info.put("name", name);
/*  885 */         info.put("type", def.type.name());
/*  886 */         Trace.ffst(this, "wasOverridden(String,StringBuffer)", "XS002009", info, null); break;
/*      */     } 
/*  888 */     if (Trace.isOn) {
/*  889 */       Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.propertystore.PropertyStoreImpl", "wasOverridden(String,StringBuffer)", 
/*  890 */           Boolean.valueOf(true), 7);
/*      */     }
/*  892 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CSPPropertyStore.PropertySource howHasPropertyBeenSet(final String propertyName) throws PropertiesException {
/*  900 */     PropertyDefinition pd = this.propDefns.get(propertyName);
/*  901 */     if (pd == null) {
/*      */       
/*  903 */       String msg = NLSServices.getMessage("JMSCS0018", new HashMap<String, String>()
/*      */           {
/*      */           
/*      */           });
/*      */ 
/*      */ 
/*      */       
/*  910 */       PropertiesException traceRet1 = new PropertiesException(msg);
/*  911 */       throw traceRet1;
/*      */     } 
/*  913 */     return pd.getSource();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static class PropertyDefinition
/*      */   {
/*  924 */     private Value type = Value.YET_TO_DEFN;
/*      */     
/*      */     private String defaultString;
/*      */     private long defaultLong;
/*      */     private boolean defaultBoolean;
/*  929 */     private Object defaultObject = null;
/*      */     
/*      */     private Long minValid;
/*      */     private Long maxValid;
/*      */     private boolean controlled;
/*  934 */     private CSPPropertyStore.PropertySource source = null;
/*      */     
/*      */     public Object getDefault() {
/*  937 */       switch (this.type) {
/*      */         case TYPE_BOOLEAN:
/*  939 */           return Boolean.valueOf(this.defaultBoolean);
/*      */ 
/*      */         
/*      */         case TYPE_LONG:
/*  943 */           return Long.valueOf(this.defaultLong);
/*      */ 
/*      */         
/*      */         case TYPE_OBJECT:
/*  947 */           return this.defaultObject;
/*      */ 
/*      */         
/*      */         case TYPE_STRING:
/*  951 */           return this.defaultString;
/*      */       } 
/*      */       
/*  954 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public String toString() {
/*  960 */       StringBuilder builder = new StringBuilder();
/*  961 */       builder.append(this.type.name()).append(":Default String-");
/*  962 */       builder.append(this.defaultString).append(":");
/*  963 */       builder.append(this.defaultLong).append(":");
/*  964 */       builder.append(this.defaultBoolean).append(":");
/*  965 */       builder.append(this.defaultObject).append(":");
/*  966 */       builder.append(this.minValid).append("-").append(this.maxValid).append(":");
/*  967 */       builder.append(this.controlled).append(":");
/*  968 */       builder.append((this.source == null) ? "no source" : this.source.name());
/*  969 */       return builder.toString();
/*      */     }
/*      */ 
/*      */     
/*      */     protected CSPPropertyStore.PropertySource getSource() {
/*  974 */       return this.source;
/*      */     }
/*      */     
/*      */     protected void setSource(CSPPropertyStore.PropertySource source) {
/*  978 */       this.source = source;
/*      */     }
/*      */     
/*      */     public enum Value {
/*  982 */       TYPE_OBJECT, TYPE_LONG, TYPE_STRING, TYPE_BOOLEAN, YET_TO_DEFN;
/*      */     }
/*      */     
/*      */     private PropertyDefinition(CSPPropertyStore.PropertySource source) {
/*  986 */       this.source = source;
/*      */     }
/*      */ 
/*      */     
/*      */     private PropertyDefinition(String defaultValue, boolean controlled) {
/*  991 */       this.type = Value.TYPE_STRING;
/*  992 */       this.defaultString = defaultValue;
/*  993 */       this.controlled = controlled;
/*  994 */       this.source = CSPPropertyStore.PropertySource.REGISTERED_ONLY;
/*      */     }
/*      */ 
/*      */     
/*      */     private PropertyDefinition(boolean defaultValue, boolean controlled) {
/*  999 */       this.type = Value.TYPE_BOOLEAN;
/* 1000 */       this.defaultBoolean = defaultValue;
/* 1001 */       this.controlled = controlled;
/* 1002 */       this.source = CSPPropertyStore.PropertySource.REGISTERED_ONLY;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private PropertyDefinition(Object defaultValue, boolean controlled) {
/* 1010 */       this.type = Value.TYPE_OBJECT;
/* 1011 */       this.defaultObject = defaultValue;
/* 1012 */       this.controlled = controlled;
/* 1013 */       this.source = CSPPropertyStore.PropertySource.REGISTERED_ONLY;
/*      */     }
/*      */ 
/*      */     
/*      */     private PropertyDefinition(long defaultValue, Long minValid, Long maxValid, boolean controlled) {
/* 1018 */       this.type = Value.TYPE_LONG;
/* 1019 */       this.defaultLong = defaultValue;
/* 1020 */       this.minValid = minValid;
/* 1021 */       this.maxValid = maxValid;
/* 1022 */       this.controlled = controlled;
/* 1023 */       this.source = CSPPropertyStore.PropertySource.REGISTERED_ONLY;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void set(String name, Object value, CSPPropertyStore.PropertySource source) {
/* 1030 */     this.currentprops.put(name, value);
/* 1031 */     ((PropertyDefinition)this.propDefns.get(name)).setSource(source);
/*      */   }
/*      */   
/*      */   public enum Value {
/*      */     TYPE_OBJECT, TYPE_LONG, TYPE_STRING, TYPE_BOOLEAN, YET_TO_DEFN;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\j2se\propertystore\PropertyStoreImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */