/*     */ package com.ibm.msg.client.commonservices.trace;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.text.MessageFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Properties;
/*     */ import javax.management.JMX;
/*     */ import javax.management.MBeanServerConnection;
/*     */ import javax.management.MalformedObjectNameException;
/*     */ import javax.management.ObjectName;
/*     */ import javax.management.remote.JMXConnector;
/*     */ import javax.management.remote.JMXConnectorFactory;
/*     */ import javax.management.remote.JMXServiceURL;
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
/*     */ public class TraceController
/*     */ {
/*     */   private static final String USAGE = "TraceController.0";
/*     */   private static final String MISSING_HOST = "TraceController.1";
/*     */   private static final String INCOMPATIBLE_OPTIONS = "TraceController.9";
/*     */   private static final String TOO_MANY_HOSTS = "TraceController.2";
/*     */   private static final String MISSING_PORT = "TraceController.3";
/*     */   private static final String TOO_MANY_PORTS = "TraceController.4";
/*     */   private static final String INVALID_PORT = "TraceController.5";
/*     */   private static final String MISSING_INCLUDE_PACKAGE = "TraceController.6";
/*     */   private static final String MISSING_EXCLUDE_PACKAGE = "TraceController.7";
/*     */   private static final String INVALID_OPTION = "TraceController.8";
/*     */   private static final String TRACE_STATUS = "TraceController.10";
/*     */   private static final String ENABLING_TRACE = "TraceController.11";
/*     */   private static final String DISABLING_TRACE = "TraceController.12";
/*     */   private static final String INCLUDING_PACKAGE = "TraceController.13";
/*     */   private static final String EXCLUDING_PACKAGE = "TraceController.14";
/*     */   private static final String MISSING_ID = "TraceController.15";
/*     */   private static final String TOO_MANY_IDS = "TraceController.16";
/*     */   private static final String INCOMPATIBLE_SPECIFIERS = "TraceController.17";
/*     */   private static final String USER_DIRECTORY = "TraceController.18";
/*     */   private static final String TRACE_FILE_NAME = "TraceController.19";
/*     */   private static final String NO_PACKAGES = "TraceController.20";
/*     */   private static final String INCLUDE_EXCLUDE = "TraceController.21";
/*     */   private static final String INCLUDED = "TraceController.22";
/*     */   private static final String EXCLUDED = "TraceController.23";
/*     */   private static final String CANNOT_ATTACH = "TraceController.24";
/*     */   private static final String CANNOT_GET_PROPERTIES = "TraceController.25";
/*     */   private static final String CANNOT_LOAD_AGENT = "TraceController.26";
/*     */   private static final String CANNOT_INITIALIZE_AGENT = "TraceController.27";
/*     */   private static final String CANNOT_ACCESS_TRACE_CONTROL = "TraceController.28";
/*     */   private static final String MISSING_SPECIFIERS = "TraceController.29";
/*     */   private static final String NO_VM_SUPPORT = "TraceController.30";
/*     */   private static final String USAGE_ALLCLIENT = "TraceController.31";
/*     */   private static final String DUMP_FILE_NAME = "TraceController.32";
/*     */   private static final String NOT_A_DIRECTORY = "TraceController.33";
/*     */   private static final String MISSING_DUMP_LOC = "TraceController.34";
/*     */   private static final String USAGE_JAKARTACLIENT = "TraceController.35";
/*  84 */   private JMXConnector agentConnector = null;
/*  85 */   private MBeanServerConnection beanConnection = null;
/*     */ 
/*     */   
/*     */   private TraceControl traceControlProxy;
/*     */ 
/*     */   
/*     */   private static boolean calledFromAllClient = false;
/*     */ 
/*     */   
/*     */   private static boolean calledFromJakartaClient = false;
/*     */   
/*     */   private static boolean doDump = false;
/*     */ 
/*     */   
/*     */   public static void main(String[] arg) {
/* 100 */     String myAgentHost = null;
/* 101 */     int myAgentPort = -1;
/* 102 */     String myId = null;
/* 103 */     String dumpLocation = null;
/*     */ 
/*     */     
/* 106 */     boolean showStatus = false;
/* 107 */     boolean setOn = false;
/* 108 */     boolean setOff = false;
/* 109 */     boolean compress = true;
/* 110 */     int mainOptionCount = 0;
/* 111 */     System.setProperty("com.ibm.tools.attach.timeout", "1000");
/*     */ 
/*     */ 
/*     */     
/* 115 */     System.setProperty("sun.tools.attach.timeout", "1000");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 120 */     ArrayList<String> includedPackages = new ArrayList<>();
/* 121 */     ArrayList<String> excludedPackages = new ArrayList<>();
/*     */ 
/*     */     
/* 124 */     for (int i = 0; i < arg.length; i++) {
/* 125 */       if (arg[i].equals("-h")) {
/*     */         
/* 127 */         i++;
/* 128 */         if (i >= arg.length) {
/* 129 */           abort(getMessageString("TraceController.1"));
/*     */         }
/* 131 */         if (myAgentHost != null) {
/* 132 */           abort(getMessageString("TraceController.2"));
/*     */         }
/* 134 */         myAgentHost = arg[i];
/*     */       
/*     */       }
/* 137 */       else if (arg[i].equals("-p")) {
/*     */         
/* 139 */         i++;
/* 140 */         if (i >= arg.length) {
/* 141 */           abort(getMessageString("TraceController.3"));
/*     */         }
/* 143 */         if (myAgentPort != -1) {
/* 144 */           abort(getMessageString("TraceController.4"));
/*     */         }
/*     */         try {
/* 147 */           myAgentPort = Integer.parseInt(arg[i]);
/*     */         }
/* 149 */         catch (NumberFormatException nfe) {
/* 150 */           abort(getMessageString("TraceController.5", new Object[] { arg[i] }));
/*     */         }
/*     */       
/*     */       }
/* 154 */       else if (arg[i].equals("-i")) {
/*     */ 
/*     */         
/* 157 */         i++;
/* 158 */         if (i >= arg.length) {
/* 159 */           abort(getMessageString("TraceController.15"));
/*     */         }
/* 161 */         if (myId != null) {
/* 162 */           abort(getMessageString("TraceController.16"));
/*     */         }
/* 164 */         myId = arg[i];
/*     */       
/*     */       }
/* 167 */       else if (arg[i].equals("-status")) {
/*     */         
/* 169 */         showStatus = true;
/* 170 */         mainOptionCount++;
/*     */       
/*     */       }
/* 173 */       else if (arg[i].equals("-enable")) {
/*     */         
/* 175 */         setOn = true;
/* 176 */         mainOptionCount++;
/*     */       
/*     */       }
/* 179 */       else if (arg[i].equals("-disable")) {
/*     */         
/* 181 */         setOff = true;
/* 182 */         mainOptionCount++;
/*     */       
/*     */       }
/* 185 */       else if (arg[i].equals("-ip")) {
/*     */         
/* 187 */         i++;
/* 188 */         if (i >= arg.length) {
/* 189 */           abort(getMessageString("TraceController.6"));
/*     */         }
/* 191 */         includedPackages.add(arg[i]);
/*     */       
/*     */       }
/* 194 */       else if (arg[i].equals("-ep")) {
/*     */ 
/*     */         
/* 197 */         i++;
/* 198 */         if (i >= arg.length) {
/* 199 */           abort(getMessageString("TraceController.7"));
/*     */         }
/* 201 */         excludedPackages.add(arg[i]);
/*     */       
/*     */       }
/* 204 */       else if (arg[i].equals("-list")) {
/*     */         
/* 206 */         listVM();
/* 207 */         System.exit(0);
/*     */       }
/* 209 */       else if (arg[i].equals("-allclient")) {
/* 210 */         calledFromAllClient = true;
/*     */       }
/* 212 */       else if (arg[i].equals("-jakartaclient")) {
/* 213 */         calledFromJakartaClient = true;
/*     */       }
/* 215 */       else if (arg[i].equals("-dump")) {
/* 216 */         doDump = true;
/*     */       }
/* 218 */       else if (arg[i].equals("-dumploc")) {
/* 219 */         i++;
/* 220 */         if (i >= arg.length) {
/* 221 */           abort(getMessageString("TraceController.34"));
/*     */         }
/* 223 */         dumpLocation = arg[i];
/* 224 */         File dumpLocFile = new File(dumpLocation);
/* 225 */         if (!dumpLocFile.exists() || !dumpLocFile.isDirectory() || !dumpLocFile.isAbsolute()) {
/* 226 */           abort(getMessageString("TraceController.33", new Object[] { dumpLocation }));
/*     */         }
/*     */       }
/* 229 */       else if (arg[i].equals("-nocompress")) {
/* 230 */         compress = false;
/*     */       } else {
/*     */         
/* 233 */         abort(getMessageString("TraceController.8", new Object[] { arg[i] }));
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 239 */     if (myId != null) {
/* 240 */       if (myAgentPort != -1 || myAgentHost != null) {
/* 241 */         abort(getMessageString("TraceController.17"));
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 246 */     else if (myAgentHost == null || myAgentPort == -1) {
/* 247 */       abort(getMessageString("TraceController.29"));
/*     */     } 
/*     */ 
/*     */     
/* 251 */     if (mainOptionCount > 1) {
/* 252 */       abort(getMessageString("TraceController.9"));
/*     */     }
/*     */     
/* 255 */     if (mainOptionCount == 0 && includedPackages.isEmpty() && excludedPackages.isEmpty()) {
/* 256 */       showStatus = true;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 261 */     TraceController agentController = null;
/* 262 */     if (myId != null) {
/* 263 */       agentController = new TraceController(myId);
/*     */     } else {
/*     */       
/* 266 */       agentController = new TraceController(myAgentHost, myAgentPort);
/*     */     } 
/*     */     
/* 269 */     for (String includedPackage : includedPackages) {
/* 270 */       agentController.includePackage(includedPackage);
/*     */     }
/*     */     
/* 273 */     for (String excludedPackage : excludedPackages) {
/* 274 */       agentController.excludePackage(excludedPackage);
/*     */     }
/*     */     
/* 277 */     if (showStatus) {
/* 278 */       agentController.fullStatus();
/*     */     }
/* 280 */     else if (setOn) {
/* 281 */       agentController.setOn();
/*     */     }
/* 283 */     else if (setOff) {
/* 284 */       agentController.setOff();
/*     */     } 
/*     */     
/* 287 */     if (doDump) {
/* 288 */       agentController.dumpState(dumpLocation, compress);
/*     */     }
/*     */     
/* 291 */     agentController.close();
/*     */   }
/*     */ 
/*     */   
/*     */   private static void listVM() {
/* 296 */     List<?> vmds = VirtualMachineProxy.list();
/* 297 */     for (Object vmd : vmds) {
/*     */       
/* 299 */       VirtualMachineDescriptorProxy tempVMD = new VirtualMachineDescriptorProxy(vmd);
/*     */       try {
/* 301 */         VirtualMachineProxy vm = VirtualMachineProxy.attach(vmd, true);
/* 302 */         System.out.format("%6s : '%s' %n", new Object[] { tempVMD.id(), tempVMD.displayName() });
/* 303 */         vm.detach();
/*     */       }
/* 305 */       catch (Exception exception) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class VirtualMachineDescriptorProxy
/*     */   {
/*     */     static Class<?> vmdescriptor;
/*     */ 
/*     */     
/*     */     Object vmd;
/*     */ 
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 323 */         vmdescriptor = Class.forName("com.ibm.tools.attach.VirtualMachineDescriptor");
/*     */       }
/* 325 */       catch (ClassNotFoundException ex) {
/*     */         
/*     */         try {
/* 328 */           vmdescriptor = Class.forName("com.sun.tools.attach.VirtualMachineDescriptor");
/*     */         }
/* 330 */         catch (ClassNotFoundException e) {
/*     */           
/* 332 */           TraceController.abort(TraceController.getMessageString("TraceController.30"), e);
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected VirtualMachineDescriptorProxy(Object vmd) {
/* 342 */       this.vmd = vmd;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     String id() {
/*     */       try {
/* 353 */         Method idMethod = vmdescriptor.getDeclaredMethod("id", (Class[])null);
/*     */         
/* 355 */         return (String)idMethod.invoke(this.vmd, (Object[])null);
/*     */       }
/* 357 */       catch (NoSuchMethodException|SecurityException|IllegalAccessException|IllegalArgumentException|InvocationTargetException e) {
/*     */         
/* 359 */         TraceController.abort(TraceController.getMessageString("TraceController.30"), e);
/*     */         
/* 361 */         return null;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     String displayName() {
/*     */       try {
/* 373 */         Method idMethod = vmdescriptor.getDeclaredMethod("displayName", (Class[])null);
/*     */         
/* 375 */         return (String)idMethod.invoke(this.vmd, (Object[])null);
/*     */       }
/* 377 */       catch (NoSuchMethodException|SecurityException|IllegalAccessException|IllegalArgumentException|InvocationTargetException e) {
/*     */         
/* 379 */         TraceController.abort(TraceController.getMessageString("TraceController.30"), e);
/*     */         
/* 381 */         return null;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static class VirtualMachineProxy
/*     */   {
/*     */     static Class<?> vm;
/*     */     
/*     */     Object proxyVM;
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 397 */         vm = Class.forName("com.ibm.tools.attach.VirtualMachine");
/*     */       
/*     */       }
/* 400 */       catch (ClassNotFoundException ex) {
/*     */         try {
/* 402 */           vm = Class.forName("com.sun.tools.attach.VirtualMachine");
/*     */         
/*     */         }
/* 405 */         catch (ClassNotFoundException e) {
/*     */           
/* 407 */           TraceController.abort(TraceController.getMessageString("TraceController.30"), e);
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     static List<?> list() {
/*     */       try {
/* 420 */         Method listMethod = vm.getDeclaredMethod("list", (Class[])null);
/* 421 */         Object list = listMethod.invoke(vm, (Object[])null);
/* 422 */         return (List)list;
/*     */       }
/* 424 */       catch (InvocationTargetException|IllegalArgumentException|IllegalAccessException|NoSuchMethodException|SecurityException e) {
/*     */         
/* 426 */         TraceController.abort(TraceController.getMessageString("TraceController.30"), e);
/*     */         
/* 428 */         return null;
/*     */       } 
/*     */     }
/*     */     
/*     */     static VirtualMachineProxy attach(Object vmd, boolean ignoreFail) {
/*     */       try {
/* 434 */         Method attachMethod = vm.getDeclaredMethod("attach", new Class[] { TraceController.VirtualMachineDescriptorProxy.vmdescriptor });
/*     */         
/* 436 */         Object vmObj = attachMethod.invoke(vm, new Object[] { vmd });
/*     */         
/* 438 */         return new VirtualMachineProxy(vmObj);
/*     */       }
/* 440 */       catch (NoSuchMethodException|SecurityException|IllegalAccessException|IllegalArgumentException|InvocationTargetException e) {
/*     */         
/* 442 */         if (!ignoreFail) {
/* 443 */           TraceController.abort(TraceController.getMessageString("TraceController.30"), e);
/*     */         }
/*     */         
/* 446 */         return null;
/*     */       } 
/*     */     }
/*     */     
/*     */     static VirtualMachineProxy attach(String id, boolean ignoreFail) {
/*     */       try {
/* 452 */         Method attachMethod = vm.getDeclaredMethod("attach", new Class[] { String.class });
/* 453 */         Object vmObj = attachMethod.invoke(vm, new Object[] { id });
/*     */         
/* 455 */         return new VirtualMachineProxy(vmObj);
/*     */       }
/* 457 */       catch (NoSuchMethodException|SecurityException|IllegalAccessException|IllegalArgumentException|InvocationTargetException e) {
/*     */         
/* 459 */         if (!ignoreFail) {
/* 460 */           TraceController.abort(TraceController.getMessageString("TraceController.30"), e);
/*     */         }
/*     */         
/* 463 */         return null;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     VirtualMachineProxy(Object vm) {
/* 469 */       this.proxyVM = vm;
/*     */     }
/*     */ 
/*     */     
/*     */     void detach() {
/*     */       try {
/* 475 */         Method attachMethod = vm.getDeclaredMethod("detach", (Class[])null);
/* 476 */         attachMethod.invoke(this.proxyVM, (Object[])null);
/*     */       
/*     */       }
/* 479 */       catch (NoSuchMethodException|SecurityException|IllegalAccessException|IllegalArgumentException|InvocationTargetException e) {
/*     */         
/* 481 */         TraceController.abort(TraceController.getMessageString("TraceController.30"), e);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Properties getSystemProperties() {
/*     */       try {
/* 489 */         Method getSystemPropertiesMethod = vm.getDeclaredMethod("getSystemProperties", (Class[])null);
/* 490 */         Object props = getSystemPropertiesMethod.invoke(this.proxyVM, (Object[])null);
/* 491 */         return (Properties)props;
/*     */       }
/* 493 */       catch (NoSuchMethodException|SecurityException|IllegalAccessException|IllegalArgumentException|InvocationTargetException e) {
/*     */         
/* 495 */         TraceController.abort(TraceController.getMessageString("TraceController.30"), e);
/*     */         
/* 497 */         return null;
/*     */       } 
/*     */     }
/*     */     
/*     */     public Properties getAgentProperties() {
/*     */       try {
/* 503 */         Method getAgentPropertiesMethod = vm.getDeclaredMethod("getAgentProperties", (Class[])null);
/* 504 */         Object props = getAgentPropertiesMethod.invoke(this.proxyVM, (Object[])null);
/* 505 */         return (Properties)props;
/*     */       }
/* 507 */       catch (NoSuchMethodException|SecurityException|IllegalAccessException|IllegalArgumentException|InvocationTargetException e) {
/*     */         
/* 509 */         TraceController.abort(TraceController.getMessageString("TraceController.30"), e);
/*     */         
/* 511 */         return null;
/*     */       } 
/*     */     }
/*     */     
/*     */     public void loadAgent(String agentJarPath) {
/*     */       try {
/* 517 */         Method loadAgentMethod = vm.getDeclaredMethod("loadAgent", new Class[] { String.class });
/* 518 */         loadAgentMethod.invoke(this.proxyVM, new Object[] { agentJarPath });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/* 525 */       catch (NoSuchMethodException|SecurityException|IllegalAccessException|IllegalArgumentException|InvocationTargetException e) {
/*     */         
/* 527 */         TraceController.abort(TraceController.getMessageString("TraceController.30"), e);
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
/*     */   private static void abort(String errorMessage) {
/* 540 */     abort(errorMessage, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void abort(String errorMessage, Throwable e) {
/* 550 */     System.err.println(errorMessage);
/* 551 */     for (Throwable t = e; t != null; t = t.getCause()) {
/* 552 */       t.printStackTrace();
/*     */     }
/* 554 */     if (calledFromAllClient) {
/* 555 */       System.err.println(getMessageString("TraceController.31"));
/*     */     }
/* 557 */     else if (calledFromJakartaClient) {
/* 558 */       System.err.println(getMessageString("TraceController.35"));
/*     */     } else {
/*     */       
/* 561 */       System.err.println(getMessageString("TraceController.0"));
/*     */     } 
/* 563 */     System.exit(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String getMessageString(String tag) {
/* 571 */     return TraceControllerMessages.getString(tag);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String getMessageString(String tag, Object... inserts) {
/* 581 */     return MessageFormat.format(getMessageString(tag), inserts);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private TraceController(String id) {
/* 590 */     VirtualMachineProxy vm = null;
/*     */     try {
/* 592 */       vm = VirtualMachineProxy.attach(id, false);
/*     */     }
/* 594 */     catch (Exception e) {
/*     */       
/* 596 */       abort(getMessageString("TraceController.24", new Object[] { id }), e);
/*     */     } 
/*     */ 
/*     */     
/* 600 */     String connectorAddress = null;
/*     */     
/*     */     try {
/* 603 */       connectorAddress = vm.getSystemProperties().getProperty("com.sun.management.jmxremote.localConnectorAddress");
/*     */       
/* 605 */       if (connectorAddress == null)
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 611 */         connectorAddress = vm.getAgentProperties().getProperty("com.sun.management.jmxremote.localConnectorAddress");
/*     */       
/*     */       }
/*     */     }
/* 615 */     catch (Exception e) {
/* 616 */       abort(getMessageString("TraceController.25", new Object[] { id }), e);
/*     */     } 
/*     */     
/* 619 */     if (connectorAddress == null) {
/*     */       
/* 621 */       String agentJarPath = null;
/*     */       try {
/* 623 */         String javaHome = vm.getSystemProperties().getProperty("java.home");
/* 624 */         agentJarPath = javaHome + File.separator + "lib" + File.separator + "management-agent.jar";
/* 625 */         vm.loadAgent(agentJarPath);
/*     */         
/* 627 */         connectorAddress = vm.getSystemProperties().getProperty("com.sun.management.jmxremote.localConnectorAddress");
/*     */         
/* 629 */         if (connectorAddress == null)
/*     */         {
/*     */ 
/*     */ 
/*     */           
/* 634 */           connectorAddress = vm.getAgentProperties().getProperty("com.sun.management.jmxremote.localConnectorAddress");
/*     */         
/*     */         }
/*     */       }
/* 638 */       catch (Exception e) {
/* 639 */         abort(getMessageString("TraceController.27", new Object[] { agentJarPath, id }), e);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*     */     try {
/* 645 */       JMXServiceURL agentUrl = new JMXServiceURL(connectorAddress);
/*     */       
/* 647 */       initializeTraceController(agentUrl);
/*     */     }
/* 649 */     catch (MalformedObjectNameException|IOException e) {
/* 650 */       abort(getMessageString("TraceController.28", new Object[] { id }), e);
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
/*     */   private TraceController(String myAgentHost, int myAgentPort) {
/*     */     try {
/* 665 */       JMXServiceURL agentUrl = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://" + myAgentHost + ":" + myAgentPort + "/jmxrmi");
/*     */       
/* 667 */       initializeTraceController(agentUrl);
/*     */     }
/* 669 */     catch (MalformedObjectNameException|IOException e) {
/* 670 */       abort(getMessageString("TraceController.28", new Object[] { myAgentHost + ":" + myAgentPort }), e);
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
/*     */   private void initializeTraceController(JMXServiceURL agentUrl) throws IOException, MalformedObjectNameException {
/* 684 */     this.agentConnector = JMXConnectorFactory.connect(agentUrl);
/* 685 */     this.beanConnection = this.agentConnector.getMBeanServerConnection();
/* 686 */     ObjectName traceBeanName = new ObjectName("IBM MQ:type=CommonServices,name=TraceControl");
/*     */     
/* 688 */     this.traceControlProxy = JMX.<TraceControl>newMBeanProxy(this.beanConnection, traceBeanName, TraceControl.class);
/*     */     
/*     */     try {
/* 691 */       this.traceControlProxy.isOn();
/*     */     
/*     */     }
/* 694 */     catch (Exception e) {
/* 695 */       abort(getMessageString("TraceController.28", new Object[] { agentUrl }), e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void fullStatus() {
/* 703 */     showStatus();
/*     */     
/* 705 */     System.out.println(getMessageString("TraceController.18", new Object[] { this.traceControlProxy.getUserDir() }));
/*     */     
/* 707 */     System.out.println(getMessageString("TraceController.19", new Object[] { this.traceControlProxy.getTraceFileName() }));
/*     */     
/* 709 */     PackageNode rootNode = this.traceControlProxy.rootNode();
/*     */     
/* 711 */     if (rootNode == null) {
/* 712 */       System.out.println(getMessageString("TraceController.20"));
/*     */     } else {
/*     */       
/* 715 */       System.out.println(getMessageString("TraceController.21"));
/* 716 */       dumpNode(rootNode, 0);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void dumpNode(PackageNode node, int level) {
/* 727 */     for (int i = 0; i < level; i++) {
/* 728 */       System.out.print("  ");
/*     */     }
/*     */     
/* 731 */     String name = node.getName();
/* 732 */     System.out.format("%s - %s%n", new Object[] { (name == null) ? "root" : name, 
/* 733 */           node.isIncluded() ? getMessageString("TraceController.22") : getMessageString("TraceController.23") });
/* 734 */     for (PackageNode child : node.getChildren()) {
/* 735 */       dumpNode(child, level + 1);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void showStatus() {
/* 744 */     System.out.println(getMessageString("TraceController.10", new Object[] { Boolean.valueOf(this.traceControlProxy.isOn()) }));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setOn() {
/* 751 */     System.out.println(getMessageString("TraceController.11"));
/* 752 */     this.traceControlProxy.setOn();
/* 753 */     showStatus();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setOff() {
/* 760 */     System.out.println(getMessageString("TraceController.12"));
/* 761 */     this.traceControlProxy.setOff();
/* 762 */     showStatus();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void includePackage(String includedPackage) {
/* 771 */     System.out.println(getMessageString("TraceController.13", new Object[] { includedPackage }));
/* 772 */     this.traceControlProxy.includePackage(includedPackage);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void excludePackage(String excludedPackage) {
/* 781 */     System.out.println(getMessageString("TraceController.14", new Object[] { excludedPackage }));
/* 782 */     this.traceControlProxy.excludePackage(excludedPackage);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void dumpState(String dumpLocation, boolean compress) {
/* 789 */     String dumpFileName = this.traceControlProxy.dumpState(dumpLocation, compress);
/* 790 */     System.out.println(getMessageString("TraceController.32", new Object[] { dumpFileName }));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void close() {
/*     */     try {
/* 800 */       this.agentConnector.close();
/*     */     }
/* 802 */     catch (IOException iOException) {}
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\trace\TraceController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */