/*     */ package com.ibm.msg.client.wmq.factories;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiDefaultPropertyHandler;
/*     */ import com.ibm.mq.jmqi.JmqiDefaultThreadPoolFactory;
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.JmqiFactory;
/*     */ import com.ibm.mq.jmqi.JmqiMQ;
/*     */ import com.ibm.mq.jmqi.JmqiPropertyHandler;
/*     */ import com.ibm.mq.jmqi.JmqiThreadPoolFactory;
/*     */ import com.ibm.mq.jmqi.handles.Pint;
/*     */ import com.ibm.mq.jmqi.system.JmqiMetaData;
/*     */ import com.ibm.mq.jmqi.system.JmqiSP;
/*     */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*     */ import com.ibm.msg.client.commonservices.componentmanager.Component;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URL;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import java.util.StringTokenizer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WMQComponent
/*     */   extends Component
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.factories/src/com/ibm/msg/client/wmq/factories/WMQComponent.java";
/*     */   
/*     */   static {
/*  62 */     if (Trace.isOn) {
/*  63 */       Trace.data("com.ibm.msg.client.wmq.factories.WMQComponent", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.factories/src/com/ibm/msg/client/wmq/factories/WMQComponent.java");
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
/*     */   public WMQComponent() {
/*  75 */     if (Trace.isOn) {
/*  76 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQComponent", "<init>()");
/*     */     }
/*  78 */     this.name = "com.ibm.msg.client.wmq";
/*  79 */     this.title = "IBM MQ JMS Provider";
/*     */ 
/*     */     
/*  82 */     this.type = "MPI";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  89 */     URL resource = getClass().getResource("version.properties");
/*  90 */     if (resource == null) {
/*     */       try {
/*  92 */         Enumeration<URL> enumeration = ClassLoader.getSystemResources("META-INF/version.properties");
/*     */         
/*  94 */         while (enumeration.hasMoreElements()) {
/*  95 */           URL url = enumeration.nextElement();
/*  96 */           if (url.toString().indexOf("com.ibm.mq.axis2.jar") < 0)
/*     */           {
/*  98 */             resource = url;
/*     */           }
/*     */         }
/*     */       
/* 102 */       } catch (IOException e) {
/* 103 */         if (Trace.isOn) {
/* 104 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.factories.WMQComponent", "<init>()", e, 1);
/*     */         }
/*     */         
/* 107 */         Trace.catchBlock("com.ibm.msg.client.wmq.factories.WMQComponent", "<init>", e);
/*     */       } 
/*     */     }
/*     */     
/* 111 */     if (resource != null) {
/* 112 */       Properties props = new Properties();
/* 113 */       try (InputStream is = resource.openStream()) {
/*     */         
/* 115 */         props.load(is);
/*     */ 
/*     */         
/* 118 */         String stringVersion = props.getProperty(this.name);
/* 119 */         if (stringVersion != null) {
/* 120 */           this.version = new int[4];
/* 121 */           StringTokenizer token = new StringTokenizer(stringVersion);
/*     */           
/* 123 */           for (int i = 0; token.hasMoreElements(); i++) {
/* 124 */             String t = token.nextToken(".");
/* 125 */             int value = Integer.parseInt(t);
/* 126 */             this.version[i] = value;
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 131 */         String CMVCLevel = props.getProperty("CMVC");
/* 132 */         if (CMVCLevel != null) {
/* 133 */           this.implementationInfo = new HashMap<>();
/* 134 */           this.implementationInfo.put("CMVC", CMVCLevel);
/*     */         }
/*     */       
/* 137 */       } catch (Exception e) {
/* 138 */         if (Trace.isOn) {
/* 139 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.factories.WMQComponent", "<init>()", e, 2);
/*     */         }
/*     */ 
/*     */         
/* 143 */         System.err.println("WMQComponent failed to initialize " + e.getMessage());
/*     */       } 
/*     */     } 
/* 146 */     if (Trace.isOn) {
/* 147 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQComponent", "<init>()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Class<?> getFactoryClass() {
/* 157 */     if (Trace.isOn) {
/* 158 */       Trace.data(this, "com.ibm.msg.client.wmq.factories.WMQComponent", "getFactoryClass()", "getter", WMQFactoryFactory.class);
/*     */     }
/*     */     
/* 161 */     return WMQFactoryFactory.class;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getFactoryInstance() {
/* 169 */     Object traceRet1 = WMQFactoryFactory.getInstance();
/* 170 */     if (Trace.isOn) {
/* 171 */       Trace.data(this, "com.ibm.msg.client.wmq.factories.WMQComponent", "getFactoryInstance()", "getter", traceRet1);
/*     */     }
/*     */     
/* 174 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSuitable(HashMap<Object, Object> filters) {
/* 182 */     if (Trace.isOn) {
/* 183 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQComponent", "isSuitable(HashMap<Object , Object>)", new Object[] { filters });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 189 */     if (Trace.isOn) {
/* 190 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQComponent", "isSuitable(HashMap<Object , Object>)", 
/* 191 */           Boolean.valueOf(true));
/*     */     }
/* 193 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, String> getImplementationInfo() {
/* 204 */     Map<String, String> traceRet1 = getImplementationInfo(false, false);
/* 205 */     if (Trace.isOn) {
/* 206 */       Trace.data(this, "com.ibm.msg.client.wmq.factories.WMQComponent", "getImplementationInfo()", "getter", traceRet1);
/*     */     }
/*     */     
/* 209 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, String> getImplementationInfo(boolean quiet) {
/* 217 */     if (Trace.isOn)
/* 218 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQComponent", "getImplementationInfo(boolean)", new Object[] {
/* 219 */             Boolean.valueOf(quiet)
/*     */           }); 
/* 221 */     Map<String, String> traceRet1 = getImplementationInfo(quiet, false);
/* 222 */     if (Trace.isOn) {
/* 223 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQComponent", "getImplementationInfo(boolean)", traceRet1);
/*     */     }
/*     */     
/* 226 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, String> getImplementationInfo(boolean quiet, boolean restoreException) {
/* 235 */     if (Trace.isOn) {
/* 236 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQComponent", "getImplementationInfo(boolean,boolean)", new Object[] {
/* 237 */             Boolean.valueOf(quiet), Boolean.valueOf(restoreException)
/*     */           });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 246 */     JmqiDefaultThreadPoolFactory threadPool = new JmqiDefaultThreadPoolFactory();
/* 247 */     JmqiDefaultPropertyHandler jmqiDefaultPropertyHandler = new JmqiDefaultPropertyHandler();
/*     */     
/* 249 */     String MQJBND_LVL = "mqjbnd level";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 261 */     JmqiEnvironment jmqiEnvironment = null;
/* 262 */     JmqiException currentJmqiException = null;
/*     */     try {
/* 264 */       jmqiEnvironment = JmqiFactory.getInstance((JmqiThreadPoolFactory)threadPool, (JmqiPropertyHandler)jmqiDefaultPropertyHandler);
/*     */       
/* 266 */       JmqiSystemEnvironment sysenv = (JmqiSystemEnvironment)jmqiEnvironment;
/*     */ 
/*     */       
/* 269 */       currentJmqiException = sysenv.getLastException();
/*     */ 
/*     */       
/* 272 */       JmqiMetaData jmqiMetaData = sysenv.newJmqiMetaData();
/* 273 */       JmqiMQ jmqiMQ = jmqiEnvironment.getMQI(0, 0);
/*     */       
/* 275 */       Pint cc = jmqiEnvironment.newPint();
/* 276 */       Pint rc = jmqiEnvironment.newPint();
/* 277 */       ((JmqiSP)jmqiMQ).getMetaData(jmqiMetaData, cc, rc);
/* 278 */       String bindingsLevel = jmqiMetaData.getCmvcLevel();
/*     */       
/* 280 */       if (this.implementationInfo == null) {
/* 281 */         this.implementationInfo = new HashMap<>();
/*     */       }
/*     */       
/* 284 */       this.implementationInfo.put(MQJBND_LVL, bindingsLevel);
/*     */     }
/* 286 */     catch (JmqiException e) {
/* 287 */       if (Trace.isOn) {
/* 288 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.factories.WMQComponent", "getImplementationInfo(boolean,boolean)", (Throwable)e);
/*     */       }
/*     */ 
/*     */       
/* 292 */       if (jmqiEnvironment != null) {
/* 293 */         JmqiException storedException = jmqiEnvironment.getLastException();
/* 294 */         jmqiEnvironment.setLastException(storedException);
/*     */       } 
/*     */       
/* 297 */       if (!quiet) {
/* 298 */         int rc = e.getReason();
/* 299 */         if (rc == 2495) {
/* 300 */           Throwable cause = e.getCause();
/* 301 */           String causeMsg = (cause != null) ? cause.getMessage() : "";
/* 302 */           String msg = e.getLocalizedMessage() + "::" + causeMsg;
/* 303 */           this.implementationInfo.put(MQJBND_LVL, msg);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 309 */     if (jmqiEnvironment != null && restoreException) {
/* 310 */       jmqiEnvironment.setLastException(currentJmqiException);
/*     */     }
/*     */     
/* 313 */     Map<String, String> traceRet1 = super.getImplementationInfo();
/* 314 */     if (Trace.isOn) {
/* 315 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQComponent", "getImplementationInfo(boolean,boolean)", traceRet1);
/*     */     }
/*     */     
/* 318 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\factories\WMQComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */