/*     */ package com.ibm.mq;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.componentmanager.Component;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
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
/*     */ public class MQJavaComponent
/*     */   extends Component
/*     */ {
/*     */   static final String copyright_notice = "Licensed Materials - Property of IBM 5724-H72,5655-R36,5655-L82,5724-L26 (c) Copyright IBM Corp. 2008, 2016 All Rights Reserved. US Government Users Restricted Rights - Use, duplication or disclosure restricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQJavaComponent.java";
/*     */   
/*     */   static {
/*  46 */     if (Trace.isOn) {
/*  47 */       Trace.data("com.ibm.mq.MQJavaComponent", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQJavaComponent.java");
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
/*     */   public MQJavaComponent() {
/*  66 */     if (Trace.isOn) {
/*  67 */       Trace.entry(this, "com.ibm.mq.MQJavaComponent", "<init>()");
/*     */     }
/*     */     
/*  70 */     this.name = "com.ibm.mq";
/*  71 */     this.title = "IBM MQ classes for Java";
/*  72 */     this.type = "INFRA";
/*  73 */     this.inPreferenceTo = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  84 */     Object o = AccessController.doPrivileged(new PrivilegedAction()
/*     */         {
/*     */           public Object run()
/*     */           {
/*  88 */             if (Trace.isOn) {
/*  89 */               Trace.entry(this, "com.ibm.mq.MQJavaComponent", "run()");
/*     */             }
/*     */             
/*  92 */             int[] newversion = null;
/*     */             try {
/*  94 */               URL resource = getClass().getResource("version.properties");
/*  95 */               if (resource == null) {
/*  96 */                 resource = ClassLoader.getSystemResource("META-INF/version.properties");
/*     */               }
/*     */               
/*  99 */               if (resource != null) {
/* 100 */                 Properties props = new Properties();
/* 101 */                 InputStream is = resource.openStream();
/* 102 */                 props.load(is);
/*     */ 
/*     */                 
/* 105 */                 String stringVersion = props.getProperty(MQJavaComponent.this.name);
/* 106 */                 if (stringVersion != null) {
/* 107 */                   newversion = new int[4];
/* 108 */                   StringTokenizer token = new StringTokenizer(stringVersion);
/* 109 */                   for (int i = 0; token.hasMoreElements(); i++) {
/* 110 */                     String t = token.nextToken(".");
/* 111 */                     int value = Integer.parseInt(t);
/* 112 */                     newversion[i] = value;
/*     */                   } 
/*     */                 } 
/*     */ 
/*     */                 
/* 117 */                 String CMVCLevel = props.getProperty("CMVC");
/* 118 */                 if (CMVCLevel != null) {
/* 119 */                   MQJavaComponent.this.implementationInfo = new HashMap<>();
/* 120 */                   MQJavaComponent.this.implementationInfo.put("CMVC", CMVCLevel);
/*     */                 } 
/*     */                 
/* 123 */                 is.close();
/*     */               } 
/*     */ 
/*     */               
/* 127 */               resource = getClass().getResource("BuildInfo.properties");
/* 128 */               if (resource == null) {
/* 129 */                 resource = ClassLoader.getSystemResource("META-INF/BuildInfo.properties");
/*     */               }
/*     */               
/* 132 */               if (resource != null) {
/* 133 */                 Properties props = new Properties();
/* 134 */                 InputStream is = resource.openStream();
/* 135 */                 props.load(is);
/*     */ 
/*     */                 
/* 138 */                 String basedOnLevel = props.getProperty("BasedOn");
/* 139 */                 String tracks = props.getProperty("APARs");
/*     */                 
/* 141 */                 String title = props.getProperty("title");
/* 142 */                 if (basedOnLevel != null && tracks != null) {
/* 143 */                   MQJavaComponent.this.implementationInfo.put("BasedOn", basedOnLevel);
/* 144 */                   MQJavaComponent.this.implementationInfo.put("APARs", tracks);
/*     */                 } 
/* 146 */                 if (title != null) {
/* 147 */                   MQJavaComponent.this.implementationInfo.put("title", title);
/*     */                 }
/* 149 */                 is.close();
/*     */               }
/*     */             
/*     */             }
/* 153 */             catch (RuntimeException re) {
/* 154 */               throw re;
/*     */             }
/* 156 */             catch (Exception e) {
/* 157 */               if (Trace.isOn) {
/* 158 */                 Trace.catchBlock(this, "com.ibm.mq.null", "run()", e);
/*     */               }
/*     */ 
/*     */               
/* 162 */               HashMap<String, Exception> data = new HashMap<>();
/* 163 */               data.put("exception", e);
/* 164 */               Trace.ffst("com.ibm.mq.MQJavaComponent", "MQJavaComponent()", "XB001001", data, null);
/*     */             } 
/*     */             
/* 167 */             if (Trace.isOn) {
/* 168 */               Trace.exit(this, "com.ibm.mq.null", "run()", newversion);
/*     */             }
/* 170 */             return newversion;
/*     */           }
/*     */         });
/* 173 */     if (o != null) {
/* 174 */       this.version = (int[])o;
/*     */     }
/*     */     
/* 177 */     if (Trace.isOn) {
/* 178 */       Trace.exit(this, "com.ibm.mq.MQJavaComponent", "<init>()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Class<?> getFactoryClass() {
/* 189 */     if (Trace.isOn) {
/* 190 */       Trace.data(this, "com.ibm.mq.MQJavaComponent", "getFactoryClass()", "getter", null);
/*     */     }
/* 192 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getFactoryInstance() {
/* 200 */     if (Trace.isOn) {
/* 201 */       Trace.data(this, "com.ibm.mq.MQJavaComponent", "getFactoryInstance()", "getter", null);
/*     */     }
/* 203 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSuitable(HashMap<Object, Object> filters) {
/* 211 */     if (Trace.isOn) {
/* 212 */       Trace.entry(this, "com.ibm.mq.MQJavaComponent", "isSuitable(HashMap<Object , Object>)", new Object[] { filters });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 217 */     if (filters != null && filters.containsKey("name")) {
/* 218 */       String filterName = filters.get("name").toString();
/* 219 */       if (!this.name.equals(filterName)) {
/*     */         
/* 221 */         if (Trace.isOn) {
/* 222 */           Trace.exit(this, "com.ibm.mq.MQJavaComponent", "isSuitable(HashMap<Object , Object>)", 
/* 223 */               Boolean.valueOf(false), 1);
/*     */         }
/* 225 */         return false;
/*     */       } 
/*     */     } 
/* 228 */     if (Trace.isOn) {
/* 229 */       Trace.exit(this, "com.ibm.mq.MQJavaComponent", "isSuitable(HashMap<Object , Object>)", 
/* 230 */           Boolean.valueOf(true), 2);
/*     */     }
/* 232 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQJavaComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */