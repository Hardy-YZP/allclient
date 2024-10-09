/*      */ package com.ibm.msg.client.commonservices.componentmanager;
/*      */ 
/*      */ import com.ibm.msg.client.commonservices.CSIException;
/*      */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*      */ import com.ibm.msg.client.commonservices.trace.StartupTracer;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.io.InputStream;
/*      */ import java.net.URL;
/*      */ import java.security.AccessControlException;
/*      */ import java.security.AccessController;
/*      */ import java.security.PrivilegedAction;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Enumeration;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.Properties;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.Vector;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ComponentManager
/*      */ {
/*   75 */   private static HashMap<String, Vector<ComponentListener>> listenerRegistry = new HashMap<>();
/*      */ 
/*      */ 
/*      */   
/*   79 */   private static HashMap<String, Vector<Component>> componentRegistry = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean useStartupTrace = true;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static StartupTracer startupTrace;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String COMPONENT_VRMF = "COMPONENT_VRMF";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String PROVIDER_NAME = "XMSC_PROVIDER_NAME";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String WMQ_PROVIDER = "com.ibm.msg.client.wmq";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String WMQ_PROVIDER_NAME = "IBM MQ JMS Provider";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String JAKARTA_WMQ_PROVIDER = "com.ibm.msg.client.jakarta.wmq";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String JAKARTA_WMQ_PROVIDER_NAME = "IBM MQ Jakarta Messaging Provider";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQTT_PROVIDER = "com.ibm.msg.client.mqtt";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String RTT_PROVIDER = "com.ibm.msg.client.rtt";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String WPM_PROVIDER = "com.ibm.msg.client.wpm";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String TRT_PROVIDER = "com.ibm.msg.client.tempore";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String J2SE_COMMONSERVICE = "com.ibm.msg.client.commonservices.j2se";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String J2ME_COMMONSERVICE = "com.ibm.msg.client.commonservices.j2me";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String WMQ_JMS_CLASSES = "com.ibm.mq.jms";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String WMQ_JMS_CLASSES_TITLE = "IBM MQ classes for Java Message Service";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String WMQ_JAKARTA_MESSAGING_CLASSES = "com.ibm.mq.jakarta.jms";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String WMQ_JAKARTA_MESSAGING_CLASSES_TITLE = "IBM MQ Classes for Jakarta Messaging Service";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String MQ_JAVA_CLASSES = "com.ibm.mq";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String JAVA_MSG_SERVICE_CLIENT = "com.ibm.msg.client.jms";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String JAVA_MSG_SERVICE_CLIENT_TITLE = "Java Message Service Client";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String JAKARTA_MSG_SERVICE_CLIENT = "com.ibm.msg.client.jakarta.jms";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String JAKARTA_MSG_SERVICE_CLIENT_TITLE = "Jakarta Messaging Client";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int COMPONENT_VERSION_INDEX = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int COMPONENT_RELEASE_INDEX = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int COMPONENT_MODIFICATION_INDEX = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int COMPONENT_FIX_INDEX = 3;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final String COMP_CLASS = "CompClass";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final String COMP_INFO_FILE = "META-INF/compinfo.properties";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final String COMP_LIST = "CompList";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  234 */   private int waitTimeout = 30000;
/*      */ 
/*      */   
/*  237 */   private static ComponentManager compMgr = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/componentmanager/ComponentManager.java";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addComponentListener(String componentType, ComponentListener listener) {
/*  249 */     _traceEntry(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "addComponentListener(String,ComponentListener)", new Object[] { componentType, listener });
/*      */ 
/*      */     
/*  252 */     assert listenerRegistry != null : "listenerRegistry not initialized - check sequence of static initilization";
/*  253 */     Vector<ComponentListener> entry = listenerRegistry.get(componentType);
/*  254 */     if (entry != null) {
/*      */       
/*  256 */       entry.add(listener);
/*      */     }
/*      */     else {
/*      */       
/*  260 */       entry = new Vector<>();
/*  261 */       entry.add(listener);
/*  262 */       listenerRegistry.put(componentType, entry);
/*      */     } 
/*  264 */     _traceExit(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "addComponentListener(String,ComponentListener)", null, -1);
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
/*      */   private Component[] findComponent(ArrayList<Component> preLoadedComponents, String type, HashMap<Object, Object> filters) throws Exception {
/*  276 */     _traceEntry(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "findComponent(String,HashMap)", new Object[] { type, filters });
/*      */ 
/*      */     
/*  279 */     ArrayList<Component> results = preLoadedComponents;
/*      */     
/*  281 */     Enumeration<URL> resources = ClassLoader.getSystemResources("META-INF/compinfo.properties");
/*  282 */     _traceData(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "findComponent(String,HashMap)", "Using main system classloader", ClassLoader.class);
/*      */ 
/*      */ 
/*      */     
/*  286 */     locateComponents(results, resources);
/*      */ 
/*      */     
/*  289 */     ClassLoader thisClassesLoader = getClass().getClassLoader();
/*  290 */     resources = thisClassesLoader.getResources("META-INF/compinfo.properties");
/*      */     
/*  292 */     _traceData(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "findComponent(String,HashMap)", "Using class's classloader", thisClassesLoader);
/*      */ 
/*      */ 
/*      */     
/*  296 */     locateComponents(results, resources);
/*      */ 
/*      */     
/*  299 */     ClassLoader threadContextClassloader = Thread.currentThread().getContextClassLoader();
/*  300 */     resources = threadContextClassloader.getResources("META-INF/compinfo.properties");
/*      */     
/*  302 */     _traceData(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "findComponent(String,HashMap)", "Using thread context classloader", threadContextClassloader);
/*      */ 
/*      */ 
/*      */     
/*  306 */     locateComponents(results, resources);
/*      */ 
/*      */     
/*  309 */     if (results.size() == 0) {
/*  310 */       if (type != null && type.equals("CSI")) {
/*      */         
/*  312 */         CSIException traceRet1 = new CSIException(NLSServices.getMessage("JMSCS0003"));
/*  313 */         _traceThrowing(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "findComponent(String,HashMap)", (Throwable)traceRet1, 1);
/*      */ 
/*      */         
/*  316 */         throw traceRet1;
/*      */       } 
/*  318 */       if (type != null && type.equals("MPI")) {
/*      */         
/*  320 */         CSIException traceRet2 = new CSIException(NLSServices.getMessage("JMSCS0004"));
/*  321 */         _traceThrowing(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "findComponent(String,HashMap)", (Throwable)traceRet2, 2);
/*      */ 
/*      */         
/*  324 */         throw traceRet2;
/*      */       } 
/*      */ 
/*      */       
/*  328 */       CSIException traceRet3 = new CSIException(NLSServices.getMessage("JMSCS0004"));
/*  329 */       _traceThrowing(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "findComponent(String,HashMap)", (Throwable)traceRet3, 3);
/*      */       
/*  331 */       throw traceRet3;
/*      */     } 
/*      */ 
/*      */     
/*  335 */     Object[] temp = results.toArray();
/*      */     
/*  337 */     _traceData(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "findComponent(String,HashMap)", "Copying array contents", temp);
/*      */ 
/*      */     
/*  340 */     Component[] components = new Component[temp.length];
/*  341 */     System.arraycopy(temp, 0, components, 0, temp.length);
/*      */     
/*  343 */     _traceExit(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "findComponent(String,HashMap)", components, -1);
/*      */     
/*  345 */     return components;
/*      */   }
/*      */   
/*      */   private void locateComponents(ArrayList<Component> results, Enumeration<URL> resources) {
/*  349 */     _traceData(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "locateComponents(ArrayList results, Enumeration resources)", "Enumeration of compinfo.properties", resources);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  354 */     while (resources != null && resources.hasMoreElements()) {
/*  355 */       URL url = resources.nextElement();
/*      */       
/*  357 */       _traceData(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "findComponent(String,HashMap)", "Got URI", url);
/*      */ 
/*      */       
/*  360 */       try (InputStream is = url.openStream()) {
/*  361 */         Properties props = new Properties();
/*  362 */         props.load(is);
/*      */ 
/*      */         
/*  365 */         String compList = props.getProperty("CompList");
/*  366 */         StringTokenizer components = new StringTokenizer(compList, ",");
/*  367 */         while (components.hasMoreElements()) {
/*  368 */           String prefix = components.nextToken();
/*  369 */           prefix = prefix + "_";
/*      */           
/*  371 */           String componentClass = props.getProperty(prefix + "CompClass");
/*      */           
/*  373 */           _traceData(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "findComponent(String,HashMap)", "Getting componentClass", componentClass);
/*      */ 
/*      */ 
/*      */           
/*      */           try {
/*  378 */             Component comp = (Component)dynamicLoadClass(componentClass, getClass()).newInstance();
/*  379 */             results.add(comp);
/*      */           }
/*  381 */           catch (ClassNotFoundException e) {
/*  382 */             _traceCatchBlock(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "findComponent(String,HashMap)", e, -1);
/*      */           }
/*      */         
/*      */         }
/*      */       
/*  387 */       } catch (Exception e) {
/*  388 */         _traceCatchBlock(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "findComponent(String,HashMap)", e, -1);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  393 */     _traceData(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "locateComponents(ArrayList results, Enumeration resources)", "Enumeration done", null);
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
/*      */   public Component getComponent(String type, HashMap<Object, Object> filters) throws CSIException {
/*  409 */     _traceEntry("com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "getComponent(String,HashMap)", new Object[] { type, filters });
/*      */ 
/*      */ 
/*      */     
/*  413 */     Component bestSoFar = null;
/*      */ 
/*      */     
/*      */     try {
/*  417 */       Vector<Component> entry = componentRegistry.get(type);
/*      */       
/*  419 */       if (entry != null) {
/*  420 */         Iterator<Component> it = entry.iterator();
/*      */         
/*  422 */         while (it.hasNext()) {
/*  423 */           Component comp = it.next();
/*      */ 
/*      */           
/*  426 */           if (checkComponent(comp, filters))
/*      */           {
/*  428 */             if (bestSoFar == null) {
/*  429 */               bestSoFar = comp;
/*      */ 
/*      */               
/*      */               continue;
/*      */             } 
/*      */             
/*  435 */             if (comp.getName().equals(bestSoFar.getName())) {
/*      */               
/*  437 */               int[] compVersion = comp.getVersion();
/*  438 */               int[] bestSoFarVersion = bestSoFar.getVersion();
/*  439 */               if (compVersion != null && bestSoFarVersion != null && compVersion.length == 4 && bestSoFarVersion.length == 4) {
/*  440 */                 boolean bool = false;
/*  441 */                 for (int j = 0; j < compVersion.length && !bool; j++) {
/*  442 */                   if (compVersion[j] < bestSoFarVersion[j]) {
/*  443 */                     _traceData(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "getComponent(String,HashMap)", "Best component so far updated due to version rule to", comp
/*      */                         
/*  445 */                         .getName());
/*  446 */                     bestSoFar = comp;
/*  447 */                     bool = true;
/*      */                   } 
/*      */                 } 
/*      */               } 
/*      */               
/*      */               continue;
/*      */             } 
/*      */             
/*  455 */             String[] inPreferenceTo = comp.getInPreferenceTo();
/*  456 */             boolean setBestSoFar = false;
/*  457 */             for (int i = 0; inPreferenceTo != null && i < inPreferenceTo.length && !setBestSoFar; i++) {
/*  458 */               if (inPreferenceTo[i].equals(bestSoFar.getName()))
/*      */               {
/*  460 */                 _traceData(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "getComponent(String,HashMap)", "Best component so far updated due to inPreferenceTo rule to", comp
/*      */ 
/*      */                     
/*  463 */                     .getName());
/*  464 */                 bestSoFar = comp;
/*      */               }
/*      */             
/*      */             }
/*      */           
/*      */           }
/*      */         
/*      */         } 
/*      */       } 
/*  473 */     } catch (Exception e) {
/*  474 */       _traceCatchBlock(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "getComponent(String,HashMap)", e, -1);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  479 */     if (bestSoFar == null) {
/*  480 */       if (type.equals("CSI")) {
/*      */         
/*  482 */         CSIException traceRet1 = new CSIException(NLSServices.getMessage("JMSCS0003"));
/*      */         
/*  484 */         _traceThrowing(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "getComponent(String,HashMap)", (Throwable)traceRet1, 1);
/*      */ 
/*      */         
/*  487 */         throw traceRet1;
/*      */       } 
/*  489 */       if (type.equals("MPI")) {
/*      */         
/*  491 */         CSIException traceRet2 = new CSIException(NLSServices.getMessage("JMSCS0004"));
/*      */         
/*  493 */         _traceThrowing(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "getComponent(String,HashMap)", (Throwable)traceRet2, 2);
/*      */ 
/*      */         
/*  496 */         throw traceRet2;
/*      */       } 
/*      */ 
/*      */       
/*  500 */       CSIException traceRet3 = new CSIException(NLSServices.getMessage("JMSCS0005") + " " + type);
/*      */       
/*  502 */       _traceThrowing("com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "getComponent(String,HashMap)", (Throwable)traceRet3, 3);
/*      */ 
/*      */ 
/*      */       
/*  506 */       throw traceRet3;
/*      */     } 
/*      */ 
/*      */     
/*  510 */     _traceExit("com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "getComponent(String,HashMap)", bestSoFar, -1);
/*      */ 
/*      */ 
/*      */     
/*  514 */     return bestSoFar;
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
/*      */   public Component[] getComponents(String type, HashMap<Object, Object> filters) throws CSIException {
/*  528 */     _traceEntry(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "getComponents(String,HashMap)", new Object[] { type, filters });
/*      */ 
/*      */     
/*  531 */     Component[] components = new Component[0];
/*  532 */     ArrayList<Component> results = new ArrayList<>();
/*      */ 
/*      */     
/*  535 */     Vector<Component> entry = componentRegistry.get(type);
/*      */     
/*  537 */     if (entry != null) {
/*  538 */       Iterator<Component> it = entry.iterator();
/*  539 */       while (it.hasNext()) {
/*  540 */         Component comp = it.next();
/*      */         
/*  542 */         if (checkComponent(comp, filters)) {
/*  543 */           results.add(comp);
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*  548 */       if (results.size() == 0) {
/*  549 */         if (type.equals("CSI")) {
/*      */           
/*  551 */           CSIException traceRet1 = new CSIException(NLSServices.getMessage("JMSCS0003"));
/*      */           
/*  553 */           _traceThrowing(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "getComponents(String,HashMap)", (Throwable)traceRet1, 1);
/*      */ 
/*      */           
/*  556 */           throw traceRet1;
/*      */         } 
/*  558 */         if (type.equals("MPI")) {
/*      */           
/*  560 */           CSIException traceRet2 = new CSIException(NLSServices.getMessage("JMSCS0004"));
/*      */           
/*  562 */           _traceThrowing(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "getComponents(String,HashMap)", (Throwable)traceRet2, 2);
/*      */ 
/*      */           
/*  565 */           throw traceRet2;
/*      */         } 
/*      */ 
/*      */         
/*  569 */         CSIException traceRet3 = new CSIException(NLSServices.getMessage("JMSCS0005") + " " + type);
/*      */         
/*  571 */         _traceThrowing(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "getComponents(String,HashMap)", (Throwable)traceRet3, 3);
/*      */ 
/*      */         
/*  574 */         throw traceRet3;
/*      */       } 
/*      */ 
/*      */       
/*  578 */       Object[] temp = results.toArray();
/*  579 */       components = new Component[temp.length];
/*  580 */       System.arraycopy(temp, 0, components, 0, temp.length);
/*      */     } 
/*      */     
/*  583 */     _traceExit(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "getComponents(String,HashMap)", components, -1);
/*      */ 
/*      */     
/*  586 */     return components;
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
/*      */   public Component[] getComponents(HashMap<Object, Object> filters) {
/*  598 */     _traceEntry(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "getComponents(HashMap)", new Object[] { filters });
/*      */ 
/*      */     
/*  601 */     Component[] components = null;
/*  602 */     ArrayList<Component> results = new ArrayList<>();
/*      */     
/*  604 */     Iterator<Vector<Component>> compRegIt = componentRegistry.values().iterator();
/*      */ 
/*      */     
/*  607 */     while (compRegIt.hasNext()) {
/*  608 */       Vector<Component> entry = compRegIt.next();
/*      */       
/*  610 */       Iterator<Component> it = entry.iterator();
/*  611 */       while (it.hasNext()) {
/*  612 */         Component comp = it.next();
/*      */         
/*  614 */         if (checkComponent(comp, filters)) {
/*  615 */           results.add(comp);
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  620 */     if (results.size() > 0) {
/*  621 */       Object[] temp = results.toArray();
/*  622 */       components = new Component[temp.length];
/*  623 */       System.arraycopy(temp, 0, components, 0, temp.length);
/*      */     } 
/*      */     
/*  626 */     _traceExit(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "getComponents(HashMap)", components, -1);
/*      */ 
/*      */     
/*  629 */     return components;
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
/*      */   public Component[] getComponentsWithWait(String type, HashMap<Object, Object> filters) throws CSIException {
/*  648 */     _traceEntry(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "getComponentsWithWait(String,HashMap)", new Object[] { type, filters });
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  653 */     Component[] comps = null;
/*      */     try {
/*  655 */       comps = getComponents(type, filters);
/*      */     }
/*  657 */     catch (CSIException e1) {
/*  658 */       _traceCatchBlock(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "getComponentsWithWait(String,HashMap)", (Throwable)e1, 1);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  663 */     while (comps == null || comps.length == 0) {
/*      */       try {
/*  665 */         synchronized (componentRegistry) {
/*  666 */           long t1 = System.currentTimeMillis();
/*  667 */           componentRegistry.wait(this.waitTimeout);
/*      */ 
/*      */           
/*  670 */           if (System.currentTimeMillis() - t1 > this.waitTimeout) {
/*  671 */             if (type.equals("CSI")) {
/*      */               
/*  673 */               CSIException traceRet1 = new CSIException(NLSServices.getMessage("JMSCS0003"));
/*      */               
/*  675 */               _traceThrowing(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "getComponentsWithWait(String,HashMap)", (Throwable)traceRet1, 1);
/*      */ 
/*      */               
/*  678 */               throw traceRet1;
/*      */             } 
/*  680 */             if (type.equals("MPI")) {
/*      */               
/*  682 */               CSIException traceRet2 = new CSIException(NLSServices.getMessage("JMSCS0004"));
/*      */               
/*  684 */               _traceThrowing(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "getComponentsWithWait(String,HashMap)", (Throwable)traceRet2, 2);
/*      */ 
/*      */               
/*  687 */               throw traceRet2;
/*      */             } 
/*      */ 
/*      */             
/*  691 */             CSIException traceRet3 = new CSIException(NLSServices.getMessage("JMSCS0005") + " " + type);
/*      */             
/*  693 */             _traceThrowing(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "getComponentsWithWait(String,HashMap)", (Throwable)traceRet3, 3);
/*      */ 
/*      */             
/*  696 */             throw traceRet3;
/*      */           }
/*      */         
/*      */         }
/*      */       
/*  701 */       } catch (InterruptedException e) {
/*  702 */         _traceCatchBlock(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "getComponentsWithWait(String,HashMap)", e, 2);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  710 */         comps = getComponents(type, filters);
/*      */       }
/*  712 */       catch (CSIException e2) {
/*  713 */         _traceCatchBlock(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "getComponentsWithWait(String,HashMap)", (Throwable)e2, 3);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  718 */     _traceExit(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "getComponentsWithWait(String,HashMap)", comps, -1);
/*      */ 
/*      */     
/*  721 */     return comps;
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
/*      */   public Component getComponentWithWait(String type, HashMap<Object, Object> filters) throws CSIException {
/*  741 */     _traceEntry(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "getComponentWithWait(String,HashMap)", new Object[] { type, filters });
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  746 */     Component comp = null;
/*      */     try {
/*  748 */       comp = getComponent(type, filters);
/*      */     }
/*  750 */     catch (CSIException e1) {
/*  751 */       _traceCatchBlock(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "getComponentWithWait(String,HashMap)", (Throwable)e1, 1);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  756 */     while (comp == null) {
/*      */       try {
/*  758 */         synchronized (componentRegistry) {
/*  759 */           long t1 = System.currentTimeMillis();
/*  760 */           componentRegistry.wait(this.waitTimeout);
/*      */ 
/*      */           
/*  763 */           if (System.currentTimeMillis() - t1 > this.waitTimeout) {
/*  764 */             if (type.equals("CSI")) {
/*      */               
/*  766 */               CSIException traceRet1 = new CSIException(NLSServices.getMessage("JMSCS0003"));
/*      */               
/*  768 */               _traceThrowing(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "getComponentWithWait(String,HashMap)", (Throwable)traceRet1, 1);
/*      */ 
/*      */               
/*  771 */               throw traceRet1;
/*      */             } 
/*  773 */             if (type.equals("MPI")) {
/*      */               
/*  775 */               CSIException traceRet2 = new CSIException(NLSServices.getMessage("JMSCS0004"));
/*      */               
/*  777 */               _traceThrowing(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "getComponentWithWait(String,HashMap)", (Throwable)traceRet2, 2);
/*      */ 
/*      */               
/*  780 */               throw traceRet2;
/*      */             } 
/*      */ 
/*      */             
/*  784 */             CSIException traceRet3 = new CSIException(NLSServices.getMessage("JMSCS0005") + " " + type);
/*      */             
/*  786 */             _traceThrowing(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "getComponentWithWait(String,HashMap)", (Throwable)traceRet3, 3);
/*      */ 
/*      */             
/*  789 */             throw traceRet3;
/*      */           }
/*      */         
/*      */         }
/*      */       
/*  794 */       } catch (InterruptedException e) {
/*  795 */         _traceCatchBlock(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "getComponentWithWait(String,HashMap)", e, 2);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  802 */         comp = getComponent(type, filters);
/*      */       }
/*  804 */       catch (CSIException e1) {
/*  805 */         _traceCatchBlock(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "getComponentWithWait(String,HashMap)", (Throwable)e1, 3);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  810 */     _traceExit(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "getComponentWithWait(String,HashMap)", comp, -1);
/*      */ 
/*      */     
/*  813 */     return comp;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void init() {
/*  822 */     init(new ArrayList<>());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected synchronized void init(final ArrayList<Component> preLoadedComponents) {
/*  832 */     String s = AccessController.<String>doPrivileged(new PrivilegedAction<String>()
/*      */         {
/*      */           public String run()
/*      */           {
/*      */             try {
/*  837 */               return System.getProperty("com.ibm.msg.client.commonservices.trace.startup");
/*      */             }
/*  839 */             catch (AccessControlException ace) {
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  844 */               System.err.println("Failed to query system property " + ace);
/*  845 */               return null;
/*      */             } 
/*      */           }
/*      */         });
/*  849 */     if (s != null && s.equalsIgnoreCase("TRUE"))
/*      */     {
/*      */       
/*  852 */       if (startupTrace == null) {
/*  853 */         startupTrace = StartupTracer.getInstance();
/*      */       }
/*      */     }
/*      */     
/*  857 */     _traceEntry(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "init()", null);
/*      */     
/*  859 */     Object o = null;
/*      */ 
/*      */     
/*  862 */     if (componentRegistry == null) {
/*  863 */       componentRegistry = new HashMap<>();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  870 */         o = AccessController.doPrivileged(new PrivilegedAction()
/*      */             {
/*      */               public Object run()
/*      */               {
/*      */                 try {
/*  875 */                   return ComponentManager.this.findComponent(preLoadedComponents, null, null);
/*      */                 }
/*  877 */                 catch (Exception e) {
/*  878 */                   return e;
/*      */                 } 
/*      */               }
/*      */             });
/*      */         
/*  883 */         if (o instanceof Exception) {
/*  884 */           throw (Exception)o;
/*      */         }
/*      */         
/*  887 */         Component[] components = (Component[])o;
/*      */         
/*  889 */         for (int i = 0; i < components.length; i++) {
/*  890 */           registerComponent(components[i]);
/*      */         }
/*      */       }
/*  893 */       catch (Exception e) {
/*  894 */         _traceData(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "init()", "Exception when finding/registering components", e);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  901 */     _traceExit(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "init()", null, -1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void notifyListeners(Component component, boolean registering) {
/*  912 */     _traceEntry(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "notifyListeners(Component,boolean)", new Object[] { component, 
/*  913 */           Boolean.valueOf(registering) });
/*      */ 
/*      */     
/*  916 */     Vector<ComponentListener> entry = listenerRegistry.get(component.getType());
/*  917 */     if (entry != null) {
/*      */       
/*  919 */       for (int i = 0; i < entry.size(); i++) {
/*  920 */         ComponentListener listener = entry.get(i);
/*      */         
/*  922 */         _traceData(this, "c.i.m.c.commonservices.componentmanager.ComponentManager", "notifyListeners(Component, boolean)", "Notifying listener", listener
/*  923 */             .getClass());
/*      */         
/*  925 */         if (registering)
/*      */         {
/*  927 */           listener.componentRegistered(component);
/*      */         }
/*      */         else
/*      */         {
/*  931 */           listener.componentUnregistered(component);
/*      */         }
/*      */       
/*      */       } 
/*      */     } else {
/*      */       
/*  937 */       _traceData(this, "c.i.m.c.commonservices.componentmanager.ComponentManager", "notifyListeners(Component, boolean)", "No listeners to notify for this component", component
/*  938 */           .getName());
/*      */     } 
/*      */     
/*  941 */     _traceExit(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "notifyListeners(Component,boolean)", null, -1);
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
/*      */   public void registerComponent(Component comp) {
/*  960 */     _traceEntry(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "registerComponent(Component)", new Object[] { comp });
/*      */ 
/*      */     
/*  963 */     String componentType = comp.getType();
/*      */ 
/*      */     
/*  966 */     Vector<Component> entry = componentRegistry.get(componentType);
/*      */ 
/*      */     
/*  969 */     if (entry == null) {
/*  970 */       Vector<Component> newEntry = new Vector<>();
/*  971 */       newEntry.add(comp);
/*      */ 
/*      */ 
/*      */       
/*  975 */       synchronized (componentRegistry) {
/*  976 */         componentRegistry.put(componentType, newEntry);
/*      */         
/*  978 */         notifyListeners(comp, true);
/*      */         
/*  980 */         componentRegistry.notify();
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  985 */       boolean alreadyRegistered = false;
/*  986 */       Iterator<Component> it = entry.iterator();
/*  987 */       while (it.hasNext() && !alreadyRegistered) {
/*  988 */         Component temp = it.next();
/*  989 */         if (temp.getClass().equals(comp.getClass())) {
/*  990 */           alreadyRegistered = true;
/*      */           
/*  992 */           _traceData(this, null, "notifyListeners(Component, boolean)", "The component has already been register, no further work needed", temp
/*  993 */               .getName());
/*      */         } 
/*      */       } 
/*  996 */       if (!alreadyRegistered)
/*      */       {
/*      */         
/*  999 */         synchronized (componentRegistry) {
/* 1000 */           entry.add(comp);
/*      */           
/* 1002 */           notifyListeners(comp, true);
/*      */           
/* 1004 */           componentRegistry.notify();
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/* 1009 */     _traceExit(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "registerComponent(Component)", null, -1);
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
/*      */   public void removeComponentListener(String componentType, ComponentListener listener) {
/* 1023 */     _traceEntry(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "removeComponentListener(String,ComponentListener)", new Object[] { componentType, listener });
/*      */ 
/*      */ 
/*      */     
/* 1027 */     Vector<ComponentListener> entry = listenerRegistry.get(componentType);
/* 1028 */     if (entry != null) {
/* 1029 */       entry.remove(listener);
/*      */     }
/*      */     
/* 1032 */     _traceExit(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "removeComponentListener(String,ComponentListener)", null, -1);
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
/*      */   public void unregisterComponent(Component comp) {
/* 1045 */     _traceEntry(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "unregisterComponent(Component)", new Object[] { comp });
/*      */ 
/*      */ 
/*      */     
/* 1049 */     Vector<Component> entry = componentRegistry.get(comp.getType());
/* 1050 */     if (entry != null) {
/* 1051 */       entry.remove(comp);
/* 1052 */       notifyListeners(comp, false);
/*      */     } 
/*      */     
/* 1055 */     _traceExit(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "unregisterComponent(Component)", null, -1);
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
/*      */   public void setWaitTimeout(int timeout) {
/* 1068 */     _traceData(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "setWaitTimeout(int)", "setter", 
/* 1069 */         Integer.valueOf(timeout));
/*      */     
/* 1071 */     this.waitTimeout = timeout;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static synchronized void initSingleton(ArrayList<Component> preLoadedComponents) {
/* 1078 */     if (compMgr == null) {
/* 1079 */       compMgr = new ComponentManager();
/* 1080 */       compMgr.init(preLoadedComponents);
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
/*      */   public static synchronized ComponentManager getInstance() {
/* 1094 */     if (compMgr == null) {
/* 1095 */       compMgr = new ComponentManager();
/* 1096 */       compMgr.init();
/*      */     } 
/*      */     
/* 1099 */     _traceData(null, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "getter", "getInstance()", compMgr);
/*      */ 
/*      */     
/* 1102 */     return compMgr;
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
/*      */   private boolean checkComponent(Component comp, HashMap<Object, Object> filters) {
/* 1114 */     _traceEntry(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "checkComponent(Component,HashMap)", new Object[] { comp, filters });
/*      */ 
/*      */     
/* 1117 */     if (!comp.isSuitable(filters)) {
/* 1118 */       _traceExit(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "checkComponent(Component,HashMap)", 
/* 1119 */           Boolean.valueOf(false), 1);
/*      */       
/* 1121 */       return false;
/*      */     } 
/* 1123 */     if (filters != null) {
/*      */       
/* 1125 */       String provider = (String)filters.get("XMSC_PROVIDER_NAME");
/* 1126 */       boolean providerMatched = true;
/* 1127 */       if (provider != null && !provider.equals(comp.getName())) {
/* 1128 */         providerMatched = false;
/*      */       }
/*      */ 
/*      */       
/* 1132 */       boolean versionMatched = true;
/* 1133 */       int[] compVersion = comp.getVersion();
/* 1134 */       int[] versionFilter = (int[])filters.get("COMPONENT_VRMF");
/* 1135 */       if (versionFilter != null && compVersion != null)
/*      */       {
/*      */         
/* 1138 */         if (compVersion.length == 4 && versionFilter.length == 4) {
/* 1139 */           for (int i = 0; i < compVersion.length; i++) {
/*      */             
/* 1141 */             if (compVersion[i] != versionFilter[i] && versionFilter[i] != -1) {
/* 1142 */               versionMatched = false;
/*      */             }
/*      */           } 
/*      */         }
/*      */       }
/*      */ 
/*      */       
/* 1149 */       if (!providerMatched || !versionMatched) {
/* 1150 */         _traceExit(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "checkComponent(Component,HashMap)", 
/* 1151 */             Boolean.valueOf(false), 2);
/*      */         
/* 1153 */         return false;
/*      */       } 
/*      */     } 
/*      */     
/* 1157 */     _traceExit(this, "com.ibm.msg.client.commonservices.componentmanager.ComponentManager", "checkComponent(Component,HashMap)", 
/* 1158 */         Boolean.valueOf(true), 3);
/*      */     
/* 1160 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void setUseStartupTrace(boolean value) {
/* 1167 */     useStartupTrace = value;
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
/*      */   public static void _traceExit(Object parentClass, String parentClassName, String methodSignature, Object returnValue, int exitIndex) {
/* 1179 */     if (useStartupTrace) {
/* 1180 */       if (startupTrace != null) {
/* 1181 */         startupTrace.methodExit(8, parentClass, parentClassName, methodSignature, returnValue, exitIndex);
/*      */       
/*      */       }
/*      */     
/*      */     }
/* 1186 */     else if (Trace.isOn) {
/* 1187 */       Trace.exit(parentClass, parentClassName, methodSignature, returnValue, exitIndex);
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
/*      */   public static void _traceEntry(Object parentClass, String parentClassName, String methodSignature, Object[] parameters) {
/* 1200 */     if (useStartupTrace) {
/* 1201 */       if (startupTrace != null) {
/* 1202 */         startupTrace.methodEntry(8, parentClass, parentClassName, methodSignature, parameters);
/*      */       
/*      */       }
/*      */     
/*      */     }
/* 1207 */     else if (Trace.isOn) {
/* 1208 */       Trace.entry(parentClass, parentClassName, methodSignature, parameters);
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
/*      */   public static void _traceData(Object parentClass, String parentClassName, String methodSignature, String uniqueDescription, Object data) {
/* 1222 */     if (useStartupTrace) {
/* 1223 */       if (startupTrace != null) {
/* 1224 */         startupTrace.traceData(9, parentClass, parentClassName, methodSignature, uniqueDescription, data);
/*      */       
/*      */       }
/*      */     
/*      */     }
/* 1229 */     else if (Trace.isOn) {
/* 1230 */       Trace.data(null, parentClassName, methodSignature, uniqueDescription, data);
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
/*      */   public static void _traceThrowing(Object parentClass, String parentClassName, String methodSignature, Throwable thrown, int exitIndex) {
/* 1244 */     if (useStartupTrace) {
/* 1245 */       if (startupTrace != null) {
/* 1246 */         startupTrace.throwing(1, parentClass, parentClassName, methodSignature, thrown, exitIndex);
/*      */       
/*      */       }
/*      */     
/*      */     }
/* 1251 */     else if (Trace.isOn) {
/* 1252 */       Trace.throwing(parentClass, parentClassName, methodSignature, thrown, exitIndex);
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
/*      */   public static void _traceCatchBlock(Object parentClass, String parentClassName, String methodSignature, Throwable thrown, int exitIndex) {
/* 1266 */     if (useStartupTrace) {
/* 1267 */       if (startupTrace != null) {
/* 1268 */         startupTrace.catchBlock(1, parentClass, parentClassName, methodSignature, thrown, exitIndex);
/*      */       
/*      */       }
/*      */     
/*      */     }
/* 1273 */     else if (Trace.isOn) {
/* 1274 */       Trace.catchBlock(parentClass, parentClassName, methodSignature, thrown, exitIndex);
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
/*      */   public Class<?> dynamicLoadClass(final String name, final Class<?> loadingClass) throws ClassNotFoundException {
/* 1312 */     Object[] results = AccessController.<Object[]>doPrivileged(new PrivilegedAction<Object[]>()
/*      */         {
/*      */ 
/*      */           
/*      */           public Object[] run()
/*      */           {
/* 1318 */             Class<?> cls = null;
/*      */ 
/*      */             
/* 1321 */             Exception exception = null;
/*      */ 
/*      */             
/* 1324 */             ClassLoader threadClassloader = Thread.currentThread().getContextClassLoader();
/* 1325 */             ClassLoader classClassloader = loadingClass.getClassLoader();
/*      */ 
/*      */             
/* 1328 */             if (threadClassloader != null) {
/*      */               
/*      */               try {
/* 1331 */                 ComponentManager._traceData(this, "CommonClassLoading", "dynamicLoadClass()", "Classload Step 1: Threadcontext Classloader", threadClassloader);
/*      */ 
/*      */                 
/* 1334 */                 cls = Class.forName(name, true, threadClassloader);
/*      */                 
/* 1336 */                 ComponentManager._traceData(this, "CommonClassLoading", "dynamicLoadClass()", "Step 1 After load:", cls);
/*      */ 
/*      */               
/*      */               }
/* 1340 */               catch (ClassNotFoundException e) {
/* 1341 */                 ComponentManager._traceData(this, "CommonClassLoading", "dynamicLoadClass()", "Expected Excetion", e
/* 1342 */                     .getClass() + "::" + e.getMessage());
/*      */               } 
/*      */             }
/*      */ 
/*      */ 
/*      */             
/* 1348 */             if (cls == null && classClassloader != null) {
/*      */               
/*      */               try {
/* 1351 */                 ComponentManager._traceData(this, "CommonClassLoading", "dynamicLoadClass()", "Classload Step 2: Loading Class Classlodaer ", classClassloader);
/*      */ 
/*      */                 
/* 1354 */                 cls = Class.forName(name, true, classClassloader);
/*      */                 
/* 1356 */                 ComponentManager._traceData(this, "CommonClassLoading", "dynamicLoadClass()", "Step 2 After load:", cls);
/*      */ 
/*      */               
/*      */               }
/* 1360 */               catch (ClassNotFoundException e) {
/*      */                 
/* 1362 */                 ComponentManager._traceData(this, "CommonClassLoading", "dynamicLoadClass()", "Expected Excetion", e
/* 1363 */                     .getClass() + "::" + e.getMessage());
/*      */               } 
/*      */             }
/*      */ 
/*      */ 
/*      */             
/* 1369 */             if (cls == null) {
/*      */               
/*      */               try {
/* 1372 */                 ComponentManager._traceData(this, "CommonClassLoading", "dynamicLoadClass()", "Classload Step 3: class.forName()", "not using specific classloader");
/*      */ 
/*      */                 
/* 1375 */                 cls = Class.forName(name);
/*      */                 
/* 1377 */                 ComponentManager._traceData(this, "CommonClassLoading", "dynamicLoadClass()", "Step 3 After class:", cls);
/*      */                 
/* 1379 */                 ComponentManager._traceData(this, "CommonClassLoading", "dynamicLoadClass()", "Step 3 Classloader", cls
/* 1380 */                     .getClassLoader().toString());
/*      */               
/*      */               }
/* 1383 */               catch (ClassNotFoundException e) {
/*      */                 
/* 1385 */                 ComponentManager._traceData(this, "CommonClassLoading", "dynamicLoadClass()", "Expected Excetion", e
/* 1386 */                     .getClass() + "::" + e.getMessage());
/*      */                 
/* 1388 */                 exception = e;
/*      */               } 
/*      */             }
/*      */             
/* 1392 */             Object[] traceRet1 = { cls, exception };
/* 1393 */             return traceRet1;
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */     
/* 1399 */     if (results[1] != null && results[1] instanceof ClassNotFoundException) {
/* 1400 */       throw (ClassNotFoundException)results[1];
/*      */     }
/* 1402 */     if (results[0] instanceof Class) {
/* 1403 */       return (Class)results[0];
/*      */     }
/* 1405 */     return null;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\componentmanager\ComponentManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */