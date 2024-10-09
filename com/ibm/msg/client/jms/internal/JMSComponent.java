/*     */ package com.ibm.msg.client.jms.internal;
/*     */ 
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
/*     */ public class JMSComponent
/*     */   extends Component
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JMSComponent.java";
/*     */   
/*     */   static {
/*  43 */     if (Trace.isOn) {
/*  44 */       Trace.data("com.ibm.msg.client.jms.internal.JMSComponent", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JMSComponent.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JMSComponent() {
/*  54 */     if (Trace.isOn) {
/*  55 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JMSComponent", "<init>()");
/*     */     }
/*     */     
/*  58 */     this.name = "com.ibm.msg.client.jms";
/*  59 */     this.title = "Java Message Service Client";
/*  60 */     this.type = "INFRA";
/*  61 */     this.inPreferenceTo = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  73 */     int[] v = AccessController.<int[]>doPrivileged((PrivilegedAction)new PrivilegedAction<int[]>()
/*     */         {
/*     */           public int[] run() {
/*  76 */             if (Trace.isOn) {
/*  77 */               Trace.entry(this, "com.ibm.msg.client.jms.internal.JMSComponent", "run()");
/*     */             }
/*     */             
/*  80 */             int[] newversion = null;
/*     */             try {
/*  82 */               URL resource = getClass().getResource("version.properties");
/*  83 */               if (resource == null) {
/*  84 */                 resource = ClassLoader.getSystemResource("META-INF/version.properties");
/*     */               }
/*     */               
/*  87 */               if (resource != null) {
/*  88 */                 InputStream propertiesStream = resource.openStream();
/*  89 */                 Properties props = new Properties();
/*  90 */                 props.load(propertiesStream);
/*     */                 try {
/*  92 */                   propertiesStream.close();
/*  93 */                 } catch (IOException ioe) {
/*  94 */                   if (Trace.isOn) {
/*  95 */                     Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JMSComponent", "run()", ioe);
/*     */                   }
/*     */                 } 
/*     */                 
/*  99 */                 String stringVersion = props.getProperty(JMSComponent.this.name);
/* 100 */                 if (stringVersion != null) {
/* 101 */                   newversion = new int[4];
/* 102 */                   StringTokenizer token = new StringTokenizer(stringVersion);
/* 103 */                   for (int i = 0; token.hasMoreElements(); i++) {
/* 104 */                     String t = token.nextToken(".");
/* 105 */                     int value = Integer.parseInt(t);
/* 106 */                     newversion[i] = value;
/*     */                   } 
/*     */                 } 
/*     */ 
/*     */                 
/* 111 */                 String CMVCLevel = props.getProperty("CMVC");
/* 112 */                 if (CMVCLevel != null) {
/* 113 */                   JMSComponent.this.implementationInfo = new HashMap<>();
/* 114 */                   JMSComponent.this.implementationInfo.put("CMVC", CMVCLevel);
/*     */                 }
/*     */               
/*     */               } 
/* 118 */             } catch (Exception e) {
/* 119 */               if (Trace.isOn) {
/* 120 */                 Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JMSComponent", "run()", e);
/*     */               }
/*     */               
/* 123 */               System.err.println("JMSComponent failed to initialize " + e.getMessage());
/*     */             } 
/* 125 */             if (Trace.isOn) {
/* 126 */               Trace.exit(this, "com.ibm.msg.client.jms.internal.JMSComponent", "run()", newversion);
/*     */             }
/* 128 */             return newversion;
/*     */           }
/*     */         });
/*     */     
/* 132 */     if (v != null) {
/* 133 */       this.version = v;
/*     */     }
/*     */     
/* 136 */     if (Trace.isOn) {
/* 137 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JMSComponent", "<init>()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Class<?> getFactoryClass() {
/* 147 */     if (Trace.isOn) {
/* 148 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JMSComponent", "getFactoryClass()", "getter", null);
/*     */     }
/*     */     
/* 151 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getFactoryInstance() {
/* 159 */     if (Trace.isOn) {
/* 160 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JMSComponent", "getFactoryInstance()", "getter", null);
/*     */     }
/*     */     
/* 163 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSuitable(HashMap<Object, Object> filters) {
/* 171 */     if (Trace.isOn) {
/* 172 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JMSComponent", "isSuitable(HashMap<Object , Object>)", new Object[] { filters });
/*     */     }
/*     */     
/* 175 */     if (Trace.isOn) {
/* 176 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JMSComponent", "isSuitable(HashMap<Object , Object>)", 
/* 177 */           Boolean.valueOf(true));
/*     */     }
/* 179 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JMSComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */