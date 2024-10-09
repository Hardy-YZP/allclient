/*     */ package com.ibm.msg.client.commonservices.j2se;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.Log.Log;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class J2SEComponent
/*     */   extends Component
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices.j2se/src/com/ibm/msg/client/commonservices/j2se/J2SEComponent.java";
/*     */   
/*     */   public J2SEComponent() {
/*  64 */     Object o = AccessController.doPrivileged(new PrivilegedAction()
/*     */         {
/*     */           public Object run()
/*     */           {
/*  68 */             int[] newversion = null;
/*     */             try {
/*  70 */               URL resource = getClass().getResource("version.properties");
/*  71 */               if (resource == null) {
/*  72 */                 resource = ClassLoader.getSystemResource("META-INF/version.properties");
/*     */               }
/*     */               
/*  75 */               if (resource != null) {
/*  76 */                 InputStream versionPropertiesStream = resource.openStream();
/*  77 */                 Properties props = new Properties();
/*  78 */                 props.load(versionPropertiesStream);
/*     */                 try {
/*  80 */                   versionPropertiesStream.close();
/*  81 */                 } catch (IOException ioe) {
/*  82 */                   if (Trace.isOn) {
/*  83 */                     Trace.catchBlock(this, "com.ibm.msg.client.commonservices.j2se.J2SEComponent", "<init>", ioe);
/*     */                   }
/*     */                 } 
/*     */                 
/*  87 */                 String stringVersion = props.getProperty(J2SEComponent.this.name);
/*  88 */                 if (stringVersion != null) {
/*  89 */                   newversion = new int[4];
/*  90 */                   StringTokenizer token = new StringTokenizer(stringVersion);
/*  91 */                   for (int i = 0; token.hasMoreElements(); i++) {
/*  92 */                     String t = token.nextToken(".");
/*  93 */                     int value = Integer.parseInt(t);
/*  94 */                     newversion[i] = value;
/*     */                   } 
/*     */                 } 
/*     */ 
/*     */                 
/*  99 */                 String CMVCLevel = props.getProperty("CMVC");
/* 100 */                 if (CMVCLevel != null) {
/* 101 */                   J2SEComponent.this.implementationInfo = new HashMap<>();
/* 102 */                   J2SEComponent.this.implementationInfo.put("CMVC", CMVCLevel);
/*     */                 }
/*     */               
/*     */               } 
/* 106 */             } catch (Exception e) {
/*     */               
/* 108 */               HashMap<String, Object> inserts = new HashMap<>();
/*     */ 
/*     */               
/* 111 */               inserts.put("XMSC_INSERT_EXCEPTION", e);
/* 112 */               Log.log(this, "<init>", "JMSCS0010", inserts);
/*     */             } 
/* 114 */             return newversion;
/*     */           }
/*     */         });
/* 117 */     if (o != null) {
/* 118 */       this.version = (int[])o;
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
/* 129 */     return CommonServicesImplementation.class;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getFactoryInstance() {
/* 138 */     Object traceRet1 = new CommonServicesImplementation();
/* 139 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSuitable(HashMap<Object, Object> filters) {
/* 150 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\j2se\J2SEComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */