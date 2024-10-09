/*     */ package com.ibm.msg.client.commonservices;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.componentmanager.Component;
/*     */ import com.ibm.msg.client.commonservices.componentmanager.ComponentListener;
/*     */ import com.ibm.msg.client.commonservices.componentmanager.ComponentManager;
/*     */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*     */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*     */ import com.ibm.msg.client.commonservices.provider.CSPCommonServices;
/*     */ import com.ibm.msg.client.commonservices.provider.commandmanager.CSPCommandManager;
/*     */ import com.ibm.msg.client.commonservices.provider.log.CSPLog;
/*     */ import com.ibm.msg.client.commonservices.provider.nls.CSPNLSServices;
/*     */ import com.ibm.msg.client.commonservices.provider.propertystore.CSPPropertyStore;
/*     */ import com.ibm.msg.client.commonservices.provider.trace.CSPTrace;
/*     */ import com.ibm.msg.client.commonservices.provider.workqueue.CSPWorkQueueManager;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URL;
/*     */ import java.security.AccessControlException;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Iterator;
/*     */ import java.util.Properties;
/*     */ import java.util.Vector;
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
/*     */ public final class CommonServices
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/CommonServices.java";
/*     */   
/*     */   static {
/*  54 */     if (Trace.isOn) {
/*  55 */       Trace.data("com.ibm.msg.client.commonservices.CommonServices", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/CommonServices.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   private static CSPCommonServices implementation = null;
/*     */ 
/*     */   
/*     */   private static Vector<CSIListener> listeners;
/*     */ 
/*     */   
/*     */   public static final String TRACE_FILENAME = "TraceFilename";
/*     */ 
/*     */   
/*     */   public static final String LOG_FILENAME = "LogFilename";
/*     */ 
/*     */   
/*     */   public static final String FDC_FILENAME = "FDCFilename";
/*     */   
/*     */   public static final String FDC_TITLE = "FDCTitle";
/*     */   
/*     */   public static final String PRODUCT_NAME = "ProductName";
/*     */   
/*     */   public static final String PRODUCT_KEY = "ProductKey";
/*     */   
/*     */   public static final String JMS_PRODUCTISATION_KEY = "MQJMS";
/*     */   
/*     */   public static final String XR_PRODUCTISATION_KEY = "MQXR";
/*     */   
/*     */   private static CSPCommandManager commandManagerImplementation;
/*     */   
/*     */   private static CSPLog logImplementation;
/*     */   
/*     */   private static CSPNLSServices nlsServicesImplementation;
/*     */   
/*     */   private static CSPPropertyStore propertyStoreImplementation;
/*     */   
/*     */   private static CSPTrace traceImplementation;
/*     */   
/*     */   private static CSPWorkQueueManager workQueueManagerImplementation;
/*     */   
/*     */   public static final int commandManagerModule = 1;
/*     */   
/*     */   public static final int logModule = 2;
/*     */   
/*     */   public static final int nlsServicesModule = 3;
/*     */   
/*     */   public static final int propertyStoreModule = 4;
/*     */   
/*     */   public static final int traceModule = 5;
/*     */   
/*     */   public static final int workQueueManagerModule = 6;
/*     */   
/*     */   private static final String PRODUCTIZATION_PROPS = "META-INF/product.properties";
/*     */ 
/*     */   
/*     */   static {
/* 115 */     if (Trace.isOn) {
/* 116 */       Trace.entry("com.ibm.msg.client.commonservices.CommonServices", "static()");
/*     */     }
/*     */     
/* 119 */     ComponentManager compMgr = ComponentManager.getInstance();
/*     */     try {
/* 121 */       Component component = compMgr.getComponent("CSI", null);
/* 122 */       implementation = (CSPCommonServices)component.getFactoryInstance();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 127 */       PropertyStore.initialize();
/* 128 */       Trace.initialize();
/*     */ 
/*     */ 
/*     */       
/* 132 */       notifyListeners();
/*     */     
/*     */     }
/* 135 */     catch (AccessControlException ace) {
/* 136 */       if (Trace.isOn) {
/* 137 */         Trace.catchBlock("com.ibm.msg.client.commonservices.CommonServices", "static()", "Caught expected exception", ace);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 143 */       ComponentListener listener = new CSIInitializeListener();
/* 144 */       compMgr.addComponentListener("CSI", listener);
/*     */     
/*     */     }
/* 147 */     catch (RuntimeException e1) {
/* 148 */       if (Trace.isOn) {
/* 149 */         Trace.catchBlock("com.ibm.msg.client.commonservices.CommonServices", "static()", e1);
/*     */       }
/*     */       
/* 152 */       throw e1;
/*     */     }
/* 154 */     catch (Exception e1) {
/* 155 */       if (Trace.isOn) {
/* 156 */         Trace.catchBlock("com.ibm.msg.client.commonservices.CommonServices", "static()", e1);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 162 */       ComponentListener listener = new CSIInitializeListener();
/* 163 */       compMgr.addComponentListener("CSI", listener);
/*     */     } 
/*     */ 
/*     */     
/* 167 */     if (Trace.isOn) {
/* 168 */       Trace.exit("com.ibm.msg.client.commonservices.CommonServices", "static()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private CommonServices() {
/* 176 */     if (Trace.isOn) {
/* 177 */       Trace.entry(this, "com.ibm.msg.client.commonservices.CommonServices", "<init>()");
/*     */     }
/*     */     
/* 180 */     if (Trace.isOn) {
/* 181 */       Trace.exit(this, "com.ibm.msg.client.commonservices.CommonServices", "<init>()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isInitialized() {
/* 190 */     boolean traceRet1 = (implementation != null);
/* 191 */     if (Trace.isOn) {
/* 192 */       Trace.data("com.ibm.msg.client.commonservices.CommonServices", "isInitialized()", "getter", 
/* 193 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 195 */     return traceRet1;
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
/*     */ 
/*     */   
/*     */   public static boolean isModuleInitialized(int module) throws CSIException {
/*     */     boolean traceRet1, traceRet2, traceRet3, traceRet4, traceRet5, traceRet6;
/* 219 */     if (Trace.isOn) {
/* 220 */       Trace.entry("com.ibm.msg.client.commonservices.CommonServices", "isModuleInitialized(int)", new Object[] {
/* 221 */             Integer.valueOf(module)
/*     */           });
/*     */     }
/* 224 */     switch (module) {
/*     */       case 1:
/* 226 */         traceRet1 = (commandManagerImplementation != null);
/* 227 */         if (Trace.isOn) {
/* 228 */           Trace.exit("com.ibm.msg.client.commonservices.CommonServices", "isModuleInitialized(int)", 
/* 229 */               Boolean.valueOf(traceRet1), 1);
/*     */         }
/* 231 */         return traceRet1;
/*     */       
/*     */       case 2:
/* 234 */         traceRet2 = (logImplementation != null);
/* 235 */         if (Trace.isOn) {
/* 236 */           Trace.exit("com.ibm.msg.client.commonservices.CommonServices", "isModuleInitialized(int)", 
/* 237 */               Boolean.valueOf(traceRet2), 2);
/*     */         }
/* 239 */         return traceRet2;
/*     */       
/*     */       case 3:
/* 242 */         traceRet3 = (nlsServicesImplementation != null);
/* 243 */         if (Trace.isOn) {
/* 244 */           Trace.exit("com.ibm.msg.client.commonservices.CommonServices", "isModuleInitialized(int)", 
/* 245 */               Boolean.valueOf(traceRet3), 3);
/*     */         }
/* 247 */         return traceRet3;
/*     */       
/*     */       case 4:
/* 250 */         traceRet4 = (propertyStoreImplementation != null);
/* 251 */         if (Trace.isOn) {
/* 252 */           Trace.exit("com.ibm.msg.client.commonservices.CommonServices", "isModuleInitialized(int)", 
/* 253 */               Boolean.valueOf(traceRet4), 4);
/*     */         }
/* 255 */         return traceRet4;
/*     */       
/*     */       case 5:
/* 258 */         traceRet5 = (traceImplementation != null);
/* 259 */         if (Trace.isOn) {
/* 260 */           Trace.exit("com.ibm.msg.client.commonservices.CommonServices", "isModuleInitialized(int)", 
/* 261 */               Boolean.valueOf(traceRet5), 5);
/*     */         }
/* 263 */         return traceRet5;
/*     */       
/*     */       case 6:
/* 266 */         traceRet6 = (workQueueManagerImplementation != null);
/* 267 */         if (Trace.isOn) {
/* 268 */           Trace.exit("com.ibm.msg.client.commonservices.CommonServices", "isModuleInitialized(int)", 
/* 269 */               Boolean.valueOf(traceRet6), 6);
/*     */         }
/* 271 */         return traceRet6;
/*     */     } 
/*     */     
/* 274 */     String msg = NLSServices.getMessage("JMSCS0002");
/* 275 */     CSIException thrown = new CSIException(msg);
/* 276 */     if (Trace.isOn) {
/* 277 */       Trace.throwing("com.ibm.msg.client.commonservices.CommonServices", "isModuleInitialized(int)", thrown);
/*     */     }
/*     */     
/* 280 */     throw thrown;
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
/*     */   public static synchronized CSPTrace getTrace() throws CSIException {
/* 292 */     if (implementation == null) {
/* 293 */       String msg = NLSServices.getMessage("JMSCS0002");
/* 294 */       CSIException thrown = new CSIException(msg);
/* 295 */       if (Trace.isOn) {
/* 296 */         Trace.throwing("com.ibm.msg.client.commonservices.CommonServices", "getTrace()", thrown);
/*     */       }
/* 298 */       throw thrown;
/*     */     } 
/*     */     
/* 301 */     if (traceImplementation == null) {
/* 302 */       traceImplementation = implementation.getTrace();
/*     */     }
/*     */     
/* 305 */     if (Trace.isOn) {
/* 306 */       Trace.data("com.ibm.msg.client.commonservices.CommonServices", "getTrace()", "getter", traceImplementation);
/*     */     }
/*     */     
/* 309 */     return traceImplementation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized CSPLog getLog() throws CSIException {
/* 319 */     if (implementation == null) {
/* 320 */       String msg = NLSServices.getMessage("JMSCS0002");
/* 321 */       CSIException thrown = new CSIException(msg);
/* 322 */       if (Trace.isOn) {
/* 323 */         Trace.throwing("com.ibm.msg.client.commonservices.CommonServices", "getLog()", thrown);
/*     */       }
/* 325 */       throw thrown;
/*     */     } 
/*     */ 
/*     */     
/* 329 */     if (logImplementation == null) {
/* 330 */       logImplementation = implementation.getLog();
/*     */     }
/*     */     
/* 333 */     if (Trace.isOn) {
/* 334 */       Trace.data("com.ibm.msg.client.commonservices.CommonServices", "getLog()", "getter", logImplementation);
/*     */     }
/*     */     
/* 337 */     return logImplementation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized CSPWorkQueueManager getWorkQueueManager() throws CSIException {
/* 346 */     if (implementation == null) {
/* 347 */       String msg = NLSServices.getMessage("JMSCS0002");
/* 348 */       CSIException thrown = new CSIException(msg);
/* 349 */       if (Trace.isOn) {
/* 350 */         Trace.throwing("com.ibm.msg.client.commonservices.CommonServices", "getWorkQueueManager()", thrown);
/*     */       }
/*     */       
/* 353 */       throw thrown;
/*     */     } 
/*     */     
/* 356 */     if (workQueueManagerImplementation == null) {
/* 357 */       workQueueManagerImplementation = implementation.getWorkQueueManager();
/*     */     }
/* 359 */     if (Trace.isOn) {
/* 360 */       Trace.data("com.ibm.msg.client.commonservices.CommonServices", "getWorkQueueManager()", "getter", workQueueManagerImplementation);
/*     */     }
/*     */     
/* 363 */     return workQueueManagerImplementation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized CSPNLSServices getNLSServices() throws CSIException {
/* 372 */     if (implementation == null) {
/*     */ 
/*     */ 
/*     */       
/* 376 */       String msg = "Unable to locate NLS services at this stage of initialization; PI commonservices will now be used";
/*     */       
/* 378 */       CSIException thrown = new CSIException(msg);
/* 379 */       if (Trace.isOn) {
/* 380 */         Trace.throwing("com.ibm.msg.client.commonservices.CommonServices", "getNLSServices()", thrown);
/*     */       }
/*     */       
/* 383 */       throw thrown;
/*     */     } 
/*     */     
/* 386 */     if (nlsServicesImplementation == null) {
/* 387 */       nlsServicesImplementation = implementation.getNLSServices();
/*     */     }
/* 389 */     if (Trace.isOn) {
/* 390 */       Trace.data("com.ibm.msg.client.commonservices.CommonServices", "getNLSServices()", "getter", nlsServicesImplementation);
/*     */     }
/*     */     
/* 393 */     return nlsServicesImplementation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized CSPCommandManager getCommandManager() throws CSIException {
/* 402 */     if (implementation == null) {
/* 403 */       String msg = NLSServices.getMessage("JMSCS0002");
/* 404 */       CSIException thrown = new CSIException(msg);
/* 405 */       if (Trace.isOn) {
/* 406 */         Trace.throwing("com.ibm.msg.client.commonservices.CommonServices", "getCommandManager()", thrown);
/*     */       }
/*     */       
/* 409 */       throw thrown;
/*     */     } 
/*     */     
/* 412 */     if (commandManagerImplementation == null) {
/* 413 */       commandManagerImplementation = implementation.getCommandManager();
/*     */     }
/* 415 */     if (Trace.isOn) {
/* 416 */       Trace.data("com.ibm.msg.client.commonservices.CommonServices", "getCommandManager()", "getter", commandManagerImplementation);
/*     */     }
/*     */     
/* 419 */     return commandManagerImplementation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CSPPropertyStore getPropertyStore() throws CSIException {
/* 428 */     if (implementation == null) {
/* 429 */       String msg = NLSServices.getMessage("JMSCS0002");
/* 430 */       CSIException thrown = new CSIException(msg);
/* 431 */       if (Trace.isOn) {
/* 432 */         Trace.throwing("com.ibm.msg.client.commonservices.CommonServices", "getPropertyStore()", thrown);
/*     */       }
/*     */       
/* 435 */       throw thrown;
/*     */     } 
/*     */     
/* 438 */     synchronized (CommonServices.class) {
/* 439 */       if (propertyStoreImplementation == null) {
/* 440 */         propertyStoreImplementation = implementation.getPropertyStore();
/*     */       }
/*     */     } 
/* 443 */     if (Trace.isOn) {
/* 444 */       Trace.data("com.ibm.msg.client.commonservices.CommonServices", "getPropertyStore()", "getter", propertyStoreImplementation);
/*     */     }
/*     */     
/* 447 */     return propertyStoreImplementation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addCSIListener(CSIListener newListener) {
/* 454 */     if (Trace.isOn) {
/* 455 */       Trace.entry("com.ibm.msg.client.commonservices.CommonServices", "addCSIListener(CSIListener)", new Object[] { newListener });
/*     */     }
/*     */     
/* 458 */     getListeners().add(newListener);
/* 459 */     if (Trace.isOn) {
/* 460 */       Trace.exit("com.ibm.msg.client.commonservices.CommonServices", "addCSIListener(CSIListener)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void removeCSIListener(CSIListener listener) {
/* 469 */     if (Trace.isOn) {
/* 470 */       Trace.entry("com.ibm.msg.client.commonservices.CommonServices", "removeCSIListener(CSIListener)", new Object[] { listener });
/*     */     }
/*     */     
/* 473 */     getListeners().remove(listener);
/* 474 */     if (Trace.isOn) {
/* 475 */       Trace.exit("com.ibm.msg.client.commonservices.CommonServices", "removeCSIListener(CSIListener)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void notifyListeners() {
/* 485 */     if (Trace.isOn) {
/* 486 */       Trace.entry("com.ibm.msg.client.commonservices.CommonServices", "notifyListeners()");
/*     */     }
/*     */     
/* 489 */     if (getListeners() != null) {
/*     */       
/* 491 */       Vector<CSIListener> clonedListeners = (Vector<CSIListener>)listeners.clone();
/* 492 */       Iterator<CSIListener> listenersIterator = clonedListeners.iterator();
/* 493 */       while (listenersIterator.hasNext()) {
/* 494 */         CSIListener listener = listenersIterator.next();
/*     */         try {
/* 496 */           if (Trace.isOn) {
/* 497 */             Trace.traceData("CommonServices", "notifyListeners", "notifying listener", listener);
/*     */           }
/* 499 */           listener.onCSIInitialize();
/*     */         }
/* 501 */         catch (Throwable t) {
/* 502 */           if (Trace.isOn) {
/* 503 */             Trace.catchBlock("com.ibm.msg.client.commonservices.CommonServices", "notifyListeners()", t);
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 508 */           if (Trace.isOn) {
/* 509 */             Trace.traceData("CommonServices", "notifyListeners", "Throwable thrown from CSIListener. Caught and swallowed", t);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 514 */     if (Trace.isOn) {
/* 515 */       Trace.exit("com.ibm.msg.client.commonservices.CommonServices", "notifyListeners()");
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
/*     */   private static synchronized Vector<CSIListener> getListeners() {
/* 529 */     if (listeners == null) {
/* 530 */       listeners = new Vector<>();
/*     */     }
/* 532 */     if (Trace.isOn) {
/* 533 */       Trace.data("com.ibm.msg.client.commonservices.CommonServices", "getListeners()", "getter", listeners);
/*     */     }
/*     */     
/* 536 */     return listeners;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class CSIInitializeListener
/*     */     implements ComponentListener
/*     */   {
/*     */     public void componentRegistered(Component component) {
/* 550 */       if (Trace.isOn) {
/* 551 */         Trace.entry(this, "com.ibm.msg.client.commonservices.CSIInitializeListener", "componentRegistered(Component)", new Object[] { component });
/*     */       }
/*     */       
/* 554 */       if (component.getType().equals("CSI")) {
/* 555 */         CommonServices.implementation = (CSPCommonServices)component.getFactoryInstance();
/* 556 */         CommonServices.notifyListeners();
/*     */         
/* 558 */         ComponentManager compMgr = ComponentManager.getInstance();
/* 559 */         compMgr.removeComponentListener("CSI", this);
/*     */       } 
/* 561 */       if (Trace.isOn) {
/* 562 */         Trace.exit(this, "com.ibm.msg.client.commonservices.CSIInitializeListener", "componentRegistered(Component)");
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void componentUnregistered(Component component) {
/* 575 */       if (Trace.isOn) {
/* 576 */         Trace.entry(this, "com.ibm.msg.client.commonservices.CSIInitializeListener", "componentUnregistered(Component)", new Object[] { component });
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 581 */       if (Trace.isOn) {
/* 582 */         Trace.exit(this, "com.ibm.msg.client.commonservices.CSIInitializeListener", "componentUnregistered(Component)");
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
/*     */   
/* 599 */   private static Properties productization = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized Properties getProductization() {
/* 608 */     if (productization == null) {
/*     */ 
/*     */       
/* 611 */       productization = new Properties();
/* 612 */       productization.put("FDCFilename", "JMSCC%COUNT%.FDC");
/* 613 */       productization.put("FDCTitle", "IBM MQ Classes for Java and classes for JMS First Failure Symptom Report");
/* 614 */       productization.put("ProductName", "IBM MQ classes for Java and classes for JMS");
/* 615 */       productization.put("TraceFilename", "mqjava_%PID%.cl%u.trc");
/* 616 */       productization.put("LogFilename", "mqjava_%PID%.log");
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 622 */         Enumeration<URL> resources = ClassLoader.getSystemResources("META-INF/product.properties");
/*     */         
/* 624 */         if (!loadProperties(resources))
/*     */         {
/* 626 */           ClassLoader thisClassesLoader = CommonServices.class.getClassLoader();
/* 627 */           resources = thisClassesLoader.getResources("META-INF/product.properties");
/*     */           
/* 629 */           if (!loadProperties(resources))
/*     */           {
/* 631 */             ClassLoader threadContextClassloader = Thread.currentThread().getContextClassLoader();
/* 632 */             if (threadContextClassloader != null) {
/* 633 */               resources = threadContextClassloader.getResources("META-INF/product.properties");
/*     */               
/* 635 */               loadProperties(resources);
/*     */             }
/*     */           
/*     */           }
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 643 */       catch (IOException e) {
/* 644 */         if (Trace.isOn) {
/* 645 */           Trace.catchBlock("com.ibm.msg.client.commonservices.CommonServices", "getProductization()", e);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 651 */         if (Trace.isOn) {
/* 652 */           Trace.data("com.ibm.msg.client.commonservices.CommonServices", "getProductization()", "Unable to load properties ", e
/*     */ 
/*     */               
/* 655 */               .getMessage());
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 660 */     if (Trace.isOn) {
/* 661 */       Trace.data("com.ibm.msg.client.commonservices.CommonServices", "getProductization()", "getter", productization);
/*     */     }
/*     */     
/* 664 */     return productization;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean loadProperties(Enumeration<URL> resources) {
/* 670 */     if (Trace.isOn) {
/* 671 */       Trace.entry("com.ibm.msg.client.commonservices.CommonServices", "loadProperties(Enumeration<URL>)", new Object[] { resources });
/*     */     }
/*     */ 
/*     */     
/* 675 */     boolean success = false;
/* 676 */     while (resources.hasMoreElements() && !success) {
/* 677 */       URL url = resources.nextElement();
/*     */       
/*     */       try {
/* 680 */         InputStream urlStream = url.openStream();
/* 681 */         productization.load(urlStream);
/*     */         try {
/* 683 */           urlStream.close();
/*     */         }
/* 685 */         catch (IOException ioe) {
/* 686 */           if (Trace.isOn) {
/* 687 */             Trace.catchBlock("com.ibm.msg.client.commonservices.CommonServices", "loadProperties()", ioe);
/*     */           }
/*     */         } 
/*     */ 
/*     */         
/* 692 */         if (Trace.isOn) {
/* 693 */           Trace.data("com.ibm.msg.client.commonservices.CommonServices", "loadProperties()", "Productization Properties loaded from ", url);
/*     */         }
/* 695 */         success = true;
/*     */       }
/* 697 */       catch (IOException e) {
/* 698 */         if (Trace.isOn) {
/* 699 */           Trace.catchBlock("com.ibm.msg.client.commonservices.CommonServices", "loadProperties(Enumeration<URL>)", e);
/*     */         }
/*     */ 
/*     */         
/* 703 */         if (Trace.isOn) {
/* 704 */           Trace.data("com.ibm.msg.client.commonservices.CommonServices", "loadProperties()", "Unable to load properties ", e.getMessage());
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 709 */     if (Trace.isOn) {
/* 710 */       Trace.exit("com.ibm.msg.client.commonservices.CommonServices", "loadProperties(Enumeration<URL>)", 
/* 711 */           Boolean.valueOf(success));
/*     */     }
/* 713 */     return success;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\CommonServices.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */