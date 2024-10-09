/*     */ package com.ibm.mq.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.CommonServices;
/*     */ import com.ibm.msg.client.commonservices.componentmanager.Component;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URL;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
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
/*     */ public class MQJMSComponent
/*     */   extends Component
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQJMSComponent.java";
/*     */   
/*     */   static {
/*  47 */     if (Trace.isOn) {
/*  48 */       Trace.data("com.ibm.mq.jms.MQJMSComponent", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQJMSComponent.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQJMSComponent() {
/*  57 */     if (Trace.isOn) {
/*  58 */       Trace.entry(this, "com.ibm.mq.jms.MQJMSComponent", "<init>()");
/*     */     }
/*     */     
/*  61 */     this.name = "com.ibm.mq.jms";
/*  62 */     this.title = "IBM MQ classes for Java Message Service";
/*  63 */     this.type = "INFRA";
/*  64 */     this.inPreferenceTo = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  76 */     Object o = AccessController.doPrivileged(new PrivilegedAction()
/*     */         {
/*     */           public Object run()
/*     */           {
/*  80 */             if (Trace.isOn) {
/*  81 */               Trace.entry(this, "com.ibm.mq.jms.MQJMSComponent", "run()");
/*     */             }
/*     */             
/*  84 */             int[] newversion = null;
/*     */             
/*     */             try {
/*  87 */               URL resource = getClass().getResource("version.properties");
/*  88 */               if (resource == null) {
/*  89 */                 resource = ClassLoader.getSystemResource("META-INF/version.properties");
/*     */               }
/*     */               
/*  92 */               if (resource != null) {
/*  93 */                 InputStream versionPropertiesStream = resource.openStream();
/*  94 */                 Properties props = new Properties();
/*  95 */                 props.load(versionPropertiesStream);
/*     */                 try {
/*  97 */                   versionPropertiesStream.close();
/*  98 */                 } catch (IOException ioe) {
/*  99 */                   if (Trace.isOn) {
/* 100 */                     Trace.catchBlock(this, "com.ibm.mq.jms.MQJMSComponent", "run()", ioe, 1);
/*     */                   }
/*     */                 } 
/*     */                 
/* 104 */                 String stringVersion = props.getProperty(MQJMSComponent.this.name);
/* 105 */                 if (stringVersion != null) {
/* 106 */                   newversion = new int[4];
/* 107 */                   StringTokenizer token = new StringTokenizer(stringVersion);
/* 108 */                   for (int i = 0; token.hasMoreElements(); i++) {
/* 109 */                     String t = token.nextToken(".");
/* 110 */                     int value = Integer.parseInt(t);
/* 111 */                     newversion[i] = value;
/*     */                   } 
/*     */                 } 
/*     */ 
/*     */                 
/* 116 */                 String CMVCLevel = props.getProperty("CMVC");
/* 117 */                 if (CMVCLevel != null) {
/* 118 */                   MQJMSComponent.this.implementationInfo = new HashMap<>();
/* 119 */                   MQJMSComponent.this.implementationInfo.put("CMVC", CMVCLevel);
/*     */                 } 
/*     */               } 
/*     */ 
/*     */               
/* 124 */               resource = getClass().getResource("BuildInfo.properties");
/* 125 */               if (resource == null) {
/* 126 */                 resource = ClassLoader.getSystemResource("META-INF/BuildInfo.properties");
/*     */               }
/*     */               
/* 129 */               if (resource != null) {
/* 130 */                 InputStream buildPropertiesStream = resource.openStream();
/* 131 */                 Properties props = new Properties();
/* 132 */                 props.load(buildPropertiesStream);
/*     */                 try {
/* 134 */                   buildPropertiesStream.close();
/* 135 */                 } catch (IOException ioe) {
/* 136 */                   if (Trace.isOn) {
/* 137 */                     Trace.catchBlock(this, "com.ibm.mq.jms.MQJMSComponent", "run()", ioe, 2);
/*     */                   }
/*     */                 } 
/*     */                 
/* 141 */                 String basedOnLevel = props.getProperty("BasedOn");
/* 142 */                 String tracks = props.getProperty("APARs");
/* 143 */                 String title = props.getProperty("title");
/* 144 */                 if (basedOnLevel != null && tracks != null && !basedOnLevel.trim().equals("") && !tracks.trim().equals("")) {
/* 145 */                   MQJMSComponent.this.implementationInfo.put("BasedOn", basedOnLevel);
/* 146 */                   MQJMSComponent.this.implementationInfo.put("APARs", tracks);
/*     */                 } 
/* 148 */                 if (title != null && !title.trim().equals("")) {
/* 149 */                   MQJMSComponent.this.implementationInfo.put("title", title);
/*     */                 }
/*     */                 
/* 152 */                 String builtWith = props.getProperty("BuiltWith");
/* 153 */                 if (builtWith != null) {
/* 154 */                   MQJMSComponent.this.implementationInfo.put("BuiltWith", builtWith);
/*     */                 }
/*     */               } 
/*     */               
/* 158 */               String formFactor = CommonServices.getProductization().getProperty("FormFactor");
/* 159 */               if (formFactor != null && !formFactor.trim().equals("")) {
/* 160 */                 MQJMSComponent.this.implementationInfo.put("FormFactor", formFactor);
/*     */               
/*     */               }
/*     */             }
/* 164 */             catch (RuntimeException re) {
/* 165 */               throw re;
/*     */             }
/* 167 */             catch (Exception e) {
/* 168 */               if (Trace.isOn) {
/* 169 */                 Trace.catchBlock(this, "com.ibm.mq.jms.MQJMSComponent", "run()", e);
/*     */               }
/*     */ 
/*     */               
/* 173 */               HashMap<String, Exception> data = new HashMap<>();
/* 174 */               data.put("exception", e);
/* 175 */               Trace.ffst("com.ibm.mq.jms.MQJMSComponent", "MQJMSComponent()", "XF010001", data, null);
/*     */             } 
/*     */             
/* 178 */             if (Trace.isOn) {
/* 179 */               Trace.exit(this, "com.ibm.mq.jms.MQJMSComponent", "run()", newversion);
/*     */             }
/* 181 */             return newversion;
/*     */           }
/*     */         });
/* 184 */     if (o != null) {
/* 185 */       this.version = (int[])o;
/*     */     }
/*     */     
/* 188 */     if (Trace.isOn) {
/* 189 */       Trace.exit(this, "com.ibm.mq.jms.MQJMSComponent", "<init>()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Class<?> getFactoryClass() {
/* 199 */     if (Trace.isOn) {
/* 200 */       Trace.data(this, "com.ibm.mq.jms.MQJMSComponent", "getFactoryClass()", "getter", null);
/*     */     }
/* 202 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getFactoryInstance() {
/* 210 */     if (Trace.isOn) {
/* 211 */       Trace.data(this, "com.ibm.mq.jms.MQJMSComponent", "getFactoryInstance()", "getter", null);
/*     */     }
/* 213 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSuitable(HashMap<Object, Object> filters) {
/* 221 */     if (Trace.isOn) {
/* 222 */       Trace.entry(this, "com.ibm.mq.jms.MQJMSComponent", "isSuitable(HashMap<Object , Object>)", new Object[] { filters });
/*     */     }
/*     */     
/* 225 */     if (Trace.isOn) {
/* 226 */       Trace.exit(this, "com.ibm.mq.jms.MQJMSComponent", "isSuitable(HashMap<Object , Object>)", 
/* 227 */           Boolean.valueOf(true));
/*     */     }
/* 229 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQJMSComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */