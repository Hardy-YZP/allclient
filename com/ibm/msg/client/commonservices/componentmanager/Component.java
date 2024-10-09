/*     */ package com.ibm.msg.client.commonservices.componentmanager;
/*     */ 
/*     */ import java.net.URL;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Component
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/componentmanager/Component.java";
/*     */   public static final String COMPONENT_TYPE_COMMON_SERVICES = "CSI";
/*     */   public static final String COMPONENT_TYPE_PROVIDER = "MPI";
/*     */   public static final String COMPONENT_TYPE_COMMAND_HANDLER = "CMDH";
/*     */   public static final String COMPONENT_TYPE_LICENSE = "LICENSE";
/*     */   public static final String COMPONENT_TYPE_INFRASTRUCTURE = "INFRA";
/*     */   protected String name;
/*     */   protected String type;
/*     */   protected String[] inPreferenceTo;
/*     */   protected int[] version;
/*     */   protected String title;
/*     */   protected Map<String, String> implementationInfo;
/*     */   
/*     */   static {
/*  40 */     ComponentManager._traceData(null, "com.ibm.msg.client.commonservices.componentmanager.Component", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/componentmanager/Component.java");
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
/*     */   public String getName() {
/*  96 */     ComponentManager._traceData(this, "com.ibm.msg.client.commonservices.componentmanager.Component", "getter", "getName()", this.name);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 101 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTitle() {
/* 108 */     ComponentManager._traceData(this, "com.ibm.msg.client.commonservices.componentmanager.Component", "getter", "getTitle()", this.title);
/*     */ 
/*     */ 
/*     */     
/* 112 */     return this.title;
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
/*     */   public String getType() {
/* 132 */     ComponentManager._traceData(this, "com.ibm.msg.client.commonservices.componentmanager.Component", "getter", "getType()", this.type);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 137 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, String> getImplementationInfo() {
/* 144 */     ComponentManager._traceData(this, "com.ibm.msg.client.commonservices.componentmanager.Component", "getter", "getImplementationInfo()", this.implementationInfo);
/*     */ 
/*     */ 
/*     */     
/* 148 */     return this.implementationInfo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, String> getImplementationInfo(boolean quiet) {
/* 156 */     return getImplementationInfo();
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
/*     */   public Map<String, String> getImplementationInfo(boolean quiet, boolean restoreException) {
/* 174 */     return getImplementationInfo();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getInPreferenceTo() {
/* 185 */     ComponentManager._traceData(this, "com.ibm.msg.client.commonservices.componentmanager.Component", "getter", "getInPreferenceTo()", this.inPreferenceTo);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 190 */     return this.inPreferenceTo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] getVersion() {
/* 199 */     ComponentManager._traceData(this, "com.ibm.msg.client.commonservices.componentmanager.Component", "getter", "getVersion()", this.version);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 204 */     return this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getVersionString() {
/* 213 */     ComponentManager._traceData(this, "com.ibm.msg.client.commonservices.componentmanager.Component", "getter", "getVersion()", this.version);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 218 */     StringBuilder versionStr = new StringBuilder();
/* 219 */     if (this.version != null && this.version.length > 0) {
/* 220 */       versionStr.append(this.version[0]);
/* 221 */       for (int i = 1; i < this.version.length; i++) {
/* 222 */         versionStr.append(".").append(this.version[i]);
/*     */       }
/*     */     } 
/*     */     
/* 226 */     return versionStr.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HashMap<String, String> getDetails() {
/* 235 */     HashMap<String, String> details = new HashMap<>();
/*     */     
/* 237 */     details.put("Class Name", getClass().toString());
/* 238 */     details.put("Component Name", this.name);
/* 239 */     details.put("Component Title", this.title);
/*     */     
/* 241 */     String versionStr = getVersionString();
/*     */     
/* 243 */     details.put("Version", versionStr);
/*     */     
/* 245 */     for (int i = 0; this.inPreferenceTo != null && i < this.inPreferenceTo.length; i++) {
/* 246 */       details.put("inPreferenceTo[" + i + "]", this.inPreferenceTo[i]);
/*     */     }
/*     */     
/* 249 */     if (getFactoryClass() != null) {
/* 250 */       details.put("Factory Class", getFactoryClass().toString());
/*     */     } else {
/*     */       
/* 253 */       details.put("Factory Class", "null");
/*     */     } 
/*     */     
/* 256 */     String jarLocation = getJarLocation();
/* 257 */     details.put("Jar location", jarLocation);
/*     */     
/* 259 */     for (int j = 0; this.inPreferenceTo != null && j < this.inPreferenceTo.length; j++) {
/* 260 */       details.put("inPreferenceTo[" + j + "]", this.inPreferenceTo[j]);
/*     */     }
/*     */     
/* 263 */     if (this.implementationInfo != null) {
/* 264 */       details.putAll(this.implementationInfo);
/*     */     }
/*     */     
/* 267 */     return details;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getJarLocation() {
/* 276 */     return AccessController.<String>doPrivileged(new PrivilegedAction<String>()
/*     */         {
/*     */ 
/*     */           
/*     */           public String run()
/*     */           {
/*     */             try {
/* 283 */               Class<?> c = Component.this.getClass();
/* 284 */               String resourceName = c.getName().replace('.', '/') + ".class";
/* 285 */               URL url = c.getClassLoader().getResource(resourceName);
/* 286 */               String protocol = url.getProtocol();
/* 287 */               if (protocol.equalsIgnoreCase("jar")) {
/* 288 */                 String path = url.getPath();
/* 289 */                 location = path.substring(0, path.indexOf("!"));
/*     */               } else {
/*     */                 
/* 292 */                 location = url.toString();
/*     */               } 
/*     */               
/* 295 */               String location = location.replace("%20", " ");
/*     */               
/* 297 */               return location;
/*     */             }
/* 299 */             catch (Throwable t) {
/* 300 */               return "Failed to get Jar location: " + t.getMessage();
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public abstract Class<?> getFactoryClass();
/*     */   
/*     */   public abstract Object getFactoryInstance();
/*     */   
/*     */   public abstract boolean isSuitable(HashMap<Object, Object> paramHashMap);
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\componentmanager\Component.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */