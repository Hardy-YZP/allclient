/*     */ package com.ibm.msg.client.commonservices.propertystore;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.CSIException;
/*     */ import com.ibm.msg.client.commonservices.CSIListener;
/*     */ import com.ibm.msg.client.commonservices.CommonServices;
/*     */ import com.ibm.msg.client.commonservices.Log.Log;
/*     */ import com.ibm.msg.client.commonservices.monitor.MonitorAgent;
/*     */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*     */ import com.ibm.msg.client.commonservices.provider.propertystore.CSPPropertyStore;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.security.AccessControlException;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import java.util.Vector;
/*     */ import javax.management.NotCompliantMBeanException;
/*     */ import javax.management.StandardMBean;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class PropertyStore
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/propertystore/PropertyStore.java";
/*  88 */   private static CSPPropertyStore functionalPropertyStore = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static boolean initialized = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static boolean listening = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String java_version;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String java_vendor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String java_vendor_url;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String java_home;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String java_vm_specification_version;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String java_vm_specification_vendor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String java_vm_specification_name;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String java_vm_version;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String java_vm_vendor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String java_vm_name;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String java_specification_version;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String java_specification_vendor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String java_specification_name;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String java_class_version;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String java_class_path;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String java_library_path;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String java_io_tmpdir;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String java_compiler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String java_ext_dirs;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String os_name;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String os_arch;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String os_version;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String file_separator;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String path_separator;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 244 */   public static String line_separator = "\n";
/*     */   
/*     */   public static String user_name;
/*     */   
/*     */   public static String user_home;
/*     */   
/*     */   public static String user_dir;
/*     */   
/* 252 */   private static HashMap<String, List<PropertyListener>> listeners = null;
/*     */   
/*     */   static {
/*     */     try {
/* 256 */       setStandardSystemProperties();
/* 257 */       initialize();
/*     */     }
/* 259 */     catch (CSIException cSIException) {}
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
/*     */   public static String getStringProperty(String name) {
/* 274 */     return functionalPropertyStore.getStringProperty(name);
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
/*     */   @Deprecated
/*     */   public static long getLongProperty(String name) {
/* 290 */     return functionalPropertyStore.getLongProperty(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Long getLongPropertyObject(String name) {
/* 298 */     Long retVal = null;
/*     */     try {
/* 300 */       long value = functionalPropertyStore.getLongProperty(name);
/* 301 */       retVal = Long.valueOf(value);
/*     */     }
/* 303 */     catch (Throwable t) {
/*     */ 
/*     */       
/* 306 */       retVal = null;
/*     */     } 
/* 308 */     return retVal;
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
/*     */   @Deprecated
/*     */   public static boolean getBooleanProperty(String name) {
/* 323 */     return functionalPropertyStore.getBooleanProperty(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Boolean getBooleanPropertyObject(String name) {
/* 331 */     Boolean retVal = null;
/*     */     try {
/* 333 */       boolean value = functionalPropertyStore.getBooleanProperty(name);
/* 334 */       retVal = Boolean.valueOf(value);
/*     */     }
/* 336 */     catch (Throwable t) {
/*     */ 
/*     */       
/* 339 */       retVal = null;
/*     */     } 
/* 341 */     return retVal;
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
/*     */   public static Object getObjectProperty(String name) {
/* 354 */     return functionalPropertyStore.getObjectProperty(name);
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
/*     */   @Deprecated
/*     */   public static boolean wasOverridden(String name, StringBuffer unparsableUserValue) {
/* 371 */     return functionalPropertyStore.wasOverridden(name, unparsableUserValue);
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
/*     */   public static void addPropertiesFile(String filename, String namespace, boolean optional) throws PropertiesFileException {
/* 386 */     functionalPropertyStore.addPropertiesFile(filename, namespace, optional);
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
/*     */   public static void set(String name, String value) {
/* 400 */     functionalPropertyStore.set(name, value);
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
/*     */   public static void set(String name, Object value) {
/* 414 */     functionalPropertyStore.set(name, value.toString());
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
/*     */   public static void register(String name, String defaultValue, boolean controlled) {
/* 427 */     functionalPropertyStore.register(name, defaultValue, controlled);
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
/*     */   public static void register(String name, boolean defaultValue, boolean controlled) {
/* 440 */     functionalPropertyStore.register(name, defaultValue, controlled);
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
/*     */   public static void register(String name, Object defaultValue, boolean controlled) {
/* 453 */     functionalPropertyStore.register(name, defaultValue, controlled);
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
/*     */   public static void register(String name, long defaultValue, Long minValid, Long maxValid, boolean controlled) {
/* 468 */     functionalPropertyStore.register(name, defaultValue, minValid, maxValid, controlled);
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
/*     */   public static void register(String name, String defaultValue) {
/* 480 */     functionalPropertyStore.register(name, defaultValue, false);
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
/*     */   public static void register(String name, boolean defaultValue) {
/* 492 */     functionalPropertyStore.register(name, defaultValue, false);
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
/*     */   public static void register(String name, Object defaultValue) {
/* 505 */     assert !(defaultValue instanceof Integer) && !(defaultValue instanceof Long) && !(defaultValue instanceof String) && !(defaultValue instanceof Boolean);
/*     */     
/* 507 */     functionalPropertyStore.register(name, defaultValue, false);
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
/*     */   public static void register(String name, long defaultValue, Long minValid, Long maxValid) {
/* 521 */     functionalPropertyStore.register(name, defaultValue, minValid, maxValid, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static HashMap<String, Object> getAll() {
/* 532 */     return functionalPropertyStore.getAll();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void initialize() throws CSIException {
/* 541 */     if (!initialized) {
/*     */       
/*     */       try {
/* 544 */         synchronized (PropertyStore.class) {
/* 545 */           if (listeners == null) {
/* 546 */             listeners = new HashMap<>();
/*     */           }
/*     */         } 
/*     */ 
/*     */         
/* 551 */         functionalPropertyStore = CommonServices.getPropertyStore();
/*     */ 
/*     */         
/* 554 */         intializePropertyStoreControl();
/*     */         
/* 556 */         initialized = true;
/*     */         
/* 558 */         CommonServices.notifyListeners();
/*     */       }
/* 560 */       catch (CSIException csie) {
/*     */         
/* 562 */         if (!listening) {
/*     */ 
/*     */           
/* 565 */           functionalPropertyStore = new PIPropertyStore();
/*     */ 
/*     */ 
/*     */           
/* 569 */           CSIListener listener = new CSIListener()
/*     */             {
/*     */               public void onCSIInitialize()
/*     */               {
/*     */                 try {
/* 574 */                   PropertyStore.initialize();
/* 575 */                   PropertyStore.listening = false;
/* 576 */                   CommonServices.removeCSIListener(this);
/*     */                 }
/* 578 */                 catch (CSIException csie2) {
/*     */                   
/* 580 */                   HashMap<String, CSIException> hash = new HashMap<>();
/* 581 */                   hash.put("Exception", csie2);
/* 582 */                   Trace.ffst(this, "onCSIInitialize", "XC004001", hash, CSIException.class);
/*     */                 } 
/*     */               }
/*     */             };
/*     */           
/* 587 */           CommonServices.addCSIListener(listener);
/* 588 */           listening = true;
/*     */         } 
/* 590 */         throw csie;
/*     */       } 
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
/*     */   private static synchronized void setStandardSystemProperties() {
/* 604 */     Object sysPropsObj = AccessController.doPrivileged(new PrivilegedAction()
/*     */         {
/*     */           public Object run()
/*     */           {
/*     */             try {
/* 609 */               Object traceRet1 = System.getProperties();
/* 610 */               return traceRet1;
/*     */             }
/* 612 */             catch (AccessControlException ace) {
/*     */               
/*     */               try {
/* 615 */                 PropertyStore.os_name = System.getProperty("os.name");
/*     */               }
/* 617 */               catch (AccessControlException accessControlException) {}
/*     */ 
/*     */               
/*     */               try {
/* 621 */                 PropertyStore.user_name = System.getProperty("user.name");
/*     */               }
/* 623 */               catch (AccessControlException accessControlException) {}
/*     */ 
/*     */               
/*     */               try {
/* 627 */                 PropertyStore.line_separator = System.getProperty("line.separator");
/*     */               }
/* 629 */               catch (AccessControlException accessControlException) {}
/*     */ 
/*     */               
/*     */               try {
/* 633 */                 PropertyStore.path_separator = System.getProperty("path.separator");
/*     */               }
/* 635 */               catch (AccessControlException accessControlException) {}
/*     */ 
/*     */ 
/*     */               
/* 639 */               return ace;
/*     */             } 
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 645 */     if (sysPropsObj instanceof Properties) {
/* 646 */       Properties sysProps = (Properties)sysPropsObj;
/* 647 */       java_version = sysProps.getProperty("java.version");
/* 648 */       java_vendor = sysProps.getProperty("java.vendor");
/* 649 */       java_vendor_url = sysProps.getProperty("java.vendor.url");
/* 650 */       java_home = sysProps.getProperty("java.home");
/* 651 */       java_vm_specification_version = sysProps.getProperty("java.vm.specification.version");
/* 652 */       java_vm_specification_vendor = sysProps.getProperty("java.vm.specification.vendor");
/* 653 */       java_vm_specification_name = sysProps.getProperty("java.vm.specification.name");
/* 654 */       java_vm_version = sysProps.getProperty("java.vm.version");
/* 655 */       java_vm_vendor = sysProps.getProperty("java.vm.vendor");
/* 656 */       java_vm_name = sysProps.getProperty("java.vm.name");
/* 657 */       java_specification_version = sysProps.getProperty("java.specification.version");
/* 658 */       java_specification_vendor = sysProps.getProperty("java.specification.vendor");
/* 659 */       java_specification_name = sysProps.getProperty("java.specification.name");
/* 660 */       java_class_version = sysProps.getProperty("java.class.version");
/* 661 */       java_class_path = sysProps.getProperty("java.class.path");
/* 662 */       java_library_path = sysProps.getProperty("java.library.path");
/* 663 */       java_io_tmpdir = sysProps.getProperty("java.io.tmpdir");
/* 664 */       java_compiler = sysProps.getProperty("java.compiler");
/* 665 */       java_ext_dirs = sysProps.getProperty("java.ext.dirs");
/* 666 */       os_name = sysProps.getProperty("os.name");
/* 667 */       os_arch = sysProps.getProperty("os.arch");
/* 668 */       os_version = sysProps.getProperty("os.version");
/* 669 */       file_separator = sysProps.getProperty("file.separator");
/* 670 */       path_separator = sysProps.getProperty("path.separator");
/* 671 */       line_separator = sysProps.getProperty("line.separator");
/* 672 */       user_name = sysProps.getProperty("user.name");
/* 673 */       user_home = sysProps.getProperty("user.home");
/* 674 */       user_dir = sysProps.getProperty("user.dir");
/*     */     
/*     */     }
/* 677 */     else if (!(sysPropsObj instanceof AccessControlException)) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 684 */       HashMap<String, Object> data = new HashMap<>();
/* 685 */       data.put("property", sysPropsObj);
/* 686 */       Trace.ffst("PropertyStore", "setStandardSystemProperties()", "XC004002", data, null);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Object updateIfAllowed(final String propertyName, final Object newValue) throws PropertiesException {
/*     */     String msg;
/*     */     PropertiesException traceRet1;
/* 710 */     final CSPPropertyStore.PropertySource propSource = functionalPropertyStore.howHasPropertyBeenSet(propertyName);
/* 711 */     switch (propSource) {
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
/*     */       case SYS_PROP:
/*     */       case CONFIG_FILE:
/*     */       case CONFIG_URL:
/*     */       case API_ADMIN:
/* 761 */         return functionalPropertyStore.getObjectProperty(propertyName);
/*     */       case UNKNOWN_PROP:
/*     */         msg = NLSServices.getMessage("JMSCS0018", new HashMap<String, Object>() {  }); traceRet1 = new PropertiesException(msg);
/*     */         throw traceRet1;
/*     */       case API:
/*     */       case REGISTERED_ONLY:
/*     */         functionalPropertyStore.set(propertyName, newValue.toString(), CSPPropertyStore.PropertySource.API);
/*     */         informListeners(propertyName, newValue);
/*     */     } 
/* 770 */     Trace.ffst("PropertyStore", "update(String,Object)", "XC004003", new HashMap<String, Object>() {  }, null); } public static void setPropertyListener(PropertyListener listener, String propertyName) { if (listeners.containsKey(propertyName)) {
/* 771 */       List<PropertyListener> list = listeners.get(propertyName);
/* 772 */       if (list.contains(listener)) {
/*     */         return;
/*     */       }
/* 775 */       list.add(listener);
/*     */     } else {
/* 777 */       List<PropertyListener> list = new Vector<>();
/* 778 */       list.add(listener);
/* 779 */       listeners.put(propertyName, list);
/*     */     }  }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setPropertyListener(PropertyListener listener, Collection<String> propertyNames) {
/* 791 */     for (String propertyName : propertyNames) {
/* 792 */       setPropertyListener(listener, propertyName);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void removePropertyListener(PropertyListener listener) {
/* 802 */     for (String key : listeners.keySet()) {
/* 803 */       removePropertyListener(listener, key);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void removePropertyListener(PropertyListener listener, String propertyName) {
/* 812 */     List<PropertyListener> list = listeners.get(propertyName);
/* 813 */     if (list.contains(listener)) {
/* 814 */       list.remove(listener);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static void informListeners(String propertyName, Object newValue) {
/* 820 */     List<PropertyListener> list = listeners.get(propertyName);
/*     */     
/* 822 */     if (list != null) {
/* 823 */       for (PropertyListener listener : list) {
/* 824 */         listener.onUpdate(propertyName, newValue);
/*     */       }
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
/* 840 */   private static StandardMBean dynamicPropertyStoreControl = null;
/* 841 */   private static String dynamicPropertyStoreControlName = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void intializePropertyStoreControl() {
/* 851 */     PropertyStoreControlImpl propertyStoreController = new PropertyStoreControlImpl();
/*     */     
/*     */     try {
/* 854 */       dynamicPropertyStoreControl = new StandardMBean((T)propertyStoreController, (Class)PropertyStoreControl.class);
/*     */     }
/* 856 */     catch (NotCompliantMBeanException e) {
/* 857 */       HashMap<String, Object> inserts = new HashMap<>();
/* 858 */       inserts.put("PropertyStoreControlImpl", propertyStoreController);
/* 859 */       inserts.put("PropertyStoreControl", PropertyStoreControl.class);
/* 860 */       inserts.put("NotCompliantMBeanException", e);
/*     */       
/* 862 */       StringBuffer message = new StringBuffer();
/* 863 */       message.append("Failed to create StandardMBean for dynamic property store control\n");
/* 864 */       for (Map.Entry<String, Object> insert : inserts.entrySet()) {
/* 865 */         String key = insert.getKey();
/* 866 */         Object value = insert.getValue();
/* 867 */         message.append(key);
/* 868 */         message.append(": ");
/* 869 */         message.append(value);
/* 870 */         message.append("\n");
/*     */       } 
/*     */       
/* 873 */       Log.logNLS("com.ibm.msg.client.commonservices.propertystore.PropertyStore", "registerMBean(Object, String)", message
/* 874 */           .toString());
/*     */       
/*     */       return;
/*     */     } 
/* 878 */     dynamicPropertyStoreControlName = "PropertyStoreControl";
/* 879 */     MonitorAgent.registerMBean(dynamicPropertyStoreControl, "CommonServices", dynamicPropertyStoreControlName);
/*     */   }
/*     */   
/*     */   public static interface PropertyListener {
/*     */     void onUpdate(String param1String, Object param1Object);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\propertystore\PropertyStore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */