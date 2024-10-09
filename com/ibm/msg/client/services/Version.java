/*     */ package com.ibm.msg.client.services;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.CSIException;
/*     */ import com.ibm.msg.client.commonservices.componentmanager.ComponentManager;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
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
/*     */ public class Version
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms/src/com/ibm/msg/client/services/Version.java";
/*     */   public static final String COMPONENT_TYPE_COMMON_SERVICES = "CSI";
/*     */   public static final String COMPONENT_TYPE_PROVIDER = "MPI";
/*     */   public static final String COMPONENT_TYPE_COMMAND_HANDLER = "CMDH";
/*     */   public static final String COMPONENT_TYPE_LICENSE = "LICENSE";
/*     */   public static final String WMQ_PROVIDER = "com.ibm.msg.client.wmq";
/*     */   public static final String MQTT_PROVIDER = "com.ibm.msg.client.mqtt";
/*     */   public static final String RTT_PROVIDER = "com.ibm.msg.client.rtt";
/*     */   public static final String WPM_PROVIDER = "com.ibm.msg.client.wpm";
/*     */   public static final String JAVA_MSG_SERVICE_CLIENT = "com.ibm.msg.client.jms";
/*     */   public static final String JSE_COMMON_SERVICES = "com.ibm.msg.client.commonservices.j2me";
/*     */   public static final String WMQ_JMS_CLASSES = "com.ibm.mq.jms";
/*     */   public static final int COMPONENT_VERSION_INDEX = 0;
/*     */   public static final int COMPONENT_RELEASE_INDEX = 1;
/*     */   public static final int COMPONENT_MODIFICATION_INDEX = 2;
/*     */   public static final int COMPONENT_FIX_INDEX = 3;
/*     */   
/*     */   static {
/*  38 */     if (Trace.isOn) {
/*  39 */       Trace.data("com.ibm.msg.client.services.Version", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms/src/com/ibm/msg/client/services/Version.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Version() {
/*  48 */     if (Trace.isOn) {
/*  49 */       Trace.entry(this, "com.ibm.msg.client.services.Version", "<init>()");
/*     */     }
/*     */     
/*  52 */     if (Trace.isOn) {
/*  53 */       Trace.exit(this, "com.ibm.msg.client.services.Version", "<init>()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Component
/*     */   {
/*     */     private com.ibm.msg.client.commonservices.componentmanager.Component delegate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected Component() {
/*  72 */       if (Trace.isOn) {
/*  73 */         Trace.entry(this, "com.ibm.msg.client.services.Component", "<init>()");
/*     */       }
/*     */       
/*  76 */       if (Trace.isOn) {
/*  77 */         Trace.exit(this, "com.ibm.msg.client.services.Component", "<init>()");
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
/*     */     public String getName() {
/*  89 */       String traceRet1 = this.delegate.getName();
/*  90 */       if (Trace.isOn) {
/*  91 */         Trace.data(this, "com.ibm.msg.client.services.Component", "getName()", "getter", traceRet1);
/*     */       }
/*     */       
/*  94 */       return traceRet1;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getTitle() {
/* 103 */       String traceRet1 = this.delegate.getTitle();
/* 104 */       if (Trace.isOn) {
/* 105 */         Trace.data(this, "com.ibm.msg.client.services.Component", "getTitle()", "getter", traceRet1);
/*     */       }
/*     */       
/* 108 */       return traceRet1;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int[] getVersion() {
/* 117 */       int[] traceRet1 = this.delegate.getVersion();
/* 118 */       if (Trace.isOn) {
/* 119 */         Trace.data(this, "com.ibm.msg.client.services.Component", "getVersion()", "getter", traceRet1);
/*     */       }
/*     */       
/* 122 */       return traceRet1;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getVersionString() {
/* 131 */       String traceRet1 = this.delegate.getVersionString();
/* 132 */       if (Trace.isOn) {
/* 133 */         Trace.data(this, "com.ibm.msg.client.services.Component", "getVersionString()", "getter", traceRet1);
/*     */       }
/*     */       
/* 136 */       return traceRet1;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Map<String, String> getImplementationInfo() {
/* 146 */       Map<String, String> traceRet1 = this.delegate.getImplementationInfo();
/* 147 */       if (Trace.isOn) {
/* 148 */         Trace.data(this, "com.ibm.msg.client.services.Component", "getImplementationInfo()", "getter", traceRet1);
/*     */       }
/*     */       
/* 151 */       return traceRet1;
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
/*     */     public Map<String, String> getImplementationInfo(boolean quiet) {
/* 163 */       if (Trace.isOn)
/* 164 */         Trace.entry(this, "com.ibm.msg.client.services.Component", "getImplementationInfo(boolean)", new Object[] {
/* 165 */               Boolean.valueOf(quiet)
/*     */             }); 
/* 167 */       Map<String, String> traceRet1 = this.delegate.getImplementationInfo(quiet);
/* 168 */       if (Trace.isOn) {
/* 169 */         Trace.exit(this, "com.ibm.msg.client.services.Component", "getImplementationInfo(boolean)", traceRet1);
/*     */       }
/*     */       
/* 172 */       return traceRet1;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getType() {
/* 181 */       String traceRet1 = this.delegate.getType();
/* 182 */       if (Trace.isOn) {
/* 183 */         Trace.data(this, "com.ibm.msg.client.services.Component", "getType()", "getter", traceRet1);
/*     */       }
/*     */       
/* 186 */       return traceRet1;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getLocation() {
/* 195 */       String traceRet1 = this.delegate.getJarLocation();
/* 196 */       if (Trace.isOn) {
/* 197 */         Trace.data(this, "com.ibm.msg.client.services.Component", "getLocation()", "getter", traceRet1);
/*     */       }
/*     */       
/* 200 */       return traceRet1;
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
/*     */   public static Component[] getComponents() {
/* 285 */     ComponentManager compMgr = ComponentManager.getInstance();
/*     */     
/* 287 */     com.ibm.msg.client.commonservices.componentmanager.Component[] components = compMgr.getComponents(null);
/*     */     
/* 289 */     Component[] traceRet1 = convertComponents(components);
/* 290 */     if (Trace.isOn) {
/* 291 */       Trace.data("com.ibm.msg.client.services.Version", "getComponents()", "getter", traceRet1);
/*     */     }
/* 293 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Component[] getComponents(String type) {
/*     */     com.ibm.msg.client.commonservices.componentmanager.Component[] components;
/* 303 */     if (Trace.isOn) {
/* 304 */       Trace.entry("com.ibm.msg.client.services.Version", "getComponents(String)", new Object[] { type });
/*     */     }
/*     */     
/* 307 */     ComponentManager compMgr = ComponentManager.getInstance();
/*     */     
/*     */     try {
/* 310 */       components = compMgr.getComponents(type, null);
/*     */     }
/* 312 */     catch (CSIException e) {
/* 313 */       if (Trace.isOn) {
/* 314 */         Trace.catchBlock("com.ibm.msg.client.services.Version", "getComponents(String)", (Throwable)e);
/*     */       }
/*     */ 
/*     */       
/* 318 */       if (Trace.isOn) {
/* 319 */         Trace.exit("com.ibm.msg.client.services.Version", "getComponents(String)", null, 1);
/*     */       }
/*     */       
/* 322 */       return null;
/*     */     } 
/*     */     
/* 325 */     Component[] traceRet1 = convertComponents(components);
/* 326 */     if (Trace.isOn) {
/* 327 */       Trace.exit("com.ibm.msg.client.services.Version", "getComponents(String)", traceRet1, 2);
/*     */     }
/* 329 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Component[] getComponents(String type, String name) {
/*     */     com.ibm.msg.client.commonservices.componentmanager.Component[] components;
/* 341 */     if (Trace.isOn) {
/* 342 */       Trace.entry("com.ibm.msg.client.services.Version", "getComponents(String,String)", new Object[] { type, name });
/*     */     }
/*     */     
/* 345 */     ComponentManager compMgr = ComponentManager.getInstance();
/*     */ 
/*     */     
/* 348 */     HashMap<Object, Object> filter = new HashMap<>();
/* 349 */     filter.put("XMSC_PROVIDER_NAME", name);
/*     */     try {
/* 351 */       components = compMgr.getComponents(type, filter);
/*     */     }
/* 353 */     catch (CSIException e) {
/* 354 */       if (Trace.isOn) {
/* 355 */         Trace.catchBlock("com.ibm.msg.client.services.Version", "getComponents(String,String)", (Throwable)e);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 360 */       if (Trace.isOn) {
/* 361 */         Trace.exit("com.ibm.msg.client.services.Version", "getComponents(String,String)", null, 1);
/*     */       }
/*     */       
/* 364 */       return null;
/*     */     } 
/*     */     
/* 367 */     Component[] traceRet1 = convertComponents(components);
/* 368 */     if (Trace.isOn) {
/* 369 */       Trace.exit("com.ibm.msg.client.services.Version", "getComponents(String,String)", traceRet1, 2);
/*     */     }
/*     */     
/* 372 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Component[] convertComponents(com.ibm.msg.client.commonservices.componentmanager.Component[] components) {
/* 383 */     if (Trace.isOn) {
/* 384 */       Trace.entry("com.ibm.msg.client.services.Version", "convertComponents(com.ibm.msg.client.commonservices.componentmanager.Component [ ])", new Object[] { components });
/*     */     }
/*     */ 
/*     */     
/* 388 */     Component[] result = null;
/* 389 */     if (components != null) {
/* 390 */       result = new Component[components.length];
/* 391 */       for (int i = 0; i < components.length; i++) {
/* 392 */         Component comp = new Component();
/* 393 */         comp.delegate = components[i];
/* 394 */         result[i] = comp;
/*     */       } 
/*     */     } 
/*     */     
/* 398 */     if (Trace.isOn) {
/* 399 */       Trace.exit("com.ibm.msg.client.services.Version", "convertComponents(com.ibm.msg.client.commonservices.componentmanager.Component [ ])", result);
/*     */     }
/*     */ 
/*     */     
/* 403 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\services\Version.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */