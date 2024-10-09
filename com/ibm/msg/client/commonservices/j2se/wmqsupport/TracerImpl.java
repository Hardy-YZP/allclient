/*     */ package com.ibm.msg.client.commonservices.j2se.wmqsupport;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.j2se.trace.DefaultTracer;
/*     */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.HashMap;
/*     */ import java.util.logging.ConsoleHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TracerImpl
/*     */   extends DefaultTracer
/*     */ {
/*     */   private static final String DiagnosticsMQ = "Diagnostics.MQ";
/*     */   private static final String DiagnosticsJava = "Diagnostics.Java";
/*     */   private static final String DiagnosticsJavaTraceDetail = "Diagnostics.Java.Trace.Detail";
/*     */   private static final String DiagnosticsJavaTraceDestinationFile = "Diagnostics.Java.Trace.Destination.File";
/*     */   private static final String DiagnosticsJavaTraceDestinationConsole = "Diagnostics.Java.Trace.Destination.Console";
/*     */   private static final String DiagnosticsJavaTraceDestinationPathname = "Diagnostics.Java.Trace.Destination.Pathname";
/*     */   private static final String DiagnosticsJavaFFDCDestinationPathname = "Diagnostics.Java.FFDC.Destination.Pathname";
/*     */   private static final String enabled = "enabled";
/*     */   private static final String disabled = "disabled";
/*     */   private static final String detail_high = "high";
/*     */   private static final String detail_medium = "medium";
/*     */   private static final String detail_low = "low";
/*     */   
/*     */   static {
/*  40 */     if (Trace.isOn) {
/*  41 */       Trace.data("com.ibm.msg.client.commonservices.j2se.wmqsupport.TracerImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices.j2se/src/com/ibm/msg/client/commonservices/j2se/wmqsupport/TracerImpl.java");
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
/*  63 */   private String ffdcDirectory = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TracerImpl() {
/*  70 */     if (Trace.isOn) {
/*  71 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.wmqsupport.TracerImpl", "<init>()");
/*     */     }
/*     */ 
/*     */     
/*  75 */     PropertyStore.register("Diagnostics.MQ", "enabled");
/*     */     
/*  77 */     String value = null;
/*  78 */     if (PropertyStore.wasOverridden("com.ibm.mq.commonservices", null)) {
/*  79 */       value = PropertyStore.getStringProperty("Diagnostics.MQ").trim();
/*  80 */       if (value.equalsIgnoreCase("enabled")) {
/*  81 */         PropertyStore.set("com.ibm.msg.client.commonservices.trace.status", "ON");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  86 */     if (Trace.isOn) {
/*  87 */       Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.wmqsupport.TracerImpl", "<init>()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void getTracerOptionsFromProperties() {
/*  95 */     if (Trace.isOn) {
/*  96 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.wmqsupport.TracerImpl", "getTracerOptionsFromProperties()");
/*     */     }
/*     */     
/*  99 */     super.getTracerOptionsFromProperties();
/*     */ 
/*     */ 
/*     */     
/* 103 */     PropertyStore.register("Diagnostics.MQ", "enabled");
/* 104 */     PropertyStore.register("Diagnostics.Java", "");
/* 105 */     PropertyStore.register("Diagnostics.Java.Trace.Detail", "medium");
/* 106 */     PropertyStore.register("Diagnostics.Java.Trace.Destination.File", "disabled");
/*     */     
/* 108 */     PropertyStore.register("Diagnostics.Java.Trace.Destination.Console", "disabled");
/* 109 */     PropertyStore.register("Diagnostics.Java.Trace.Destination.Pathname", "");
/* 110 */     PropertyStore.register("Diagnostics.Java.FFDC.Destination.Pathname", ".");
/*     */     
/* 112 */     String value = null;
/*     */     
/* 114 */     if (PropertyStore.wasOverridden("com.ibm.mq.commonservices", null)) {
/* 115 */       value = PropertyStore.getStringProperty("Diagnostics.MQ").trim();
/* 116 */       if (value.equalsIgnoreCase("enabled")) {
/* 117 */         PropertyStore.set("com.ibm.msg.client.commonservices.trace.status", "ON");
/*     */       }
/*     */ 
/*     */       
/* 121 */       value = PropertyStore.getStringProperty("Diagnostics.Java.Trace.Detail").trim();
/*     */       
/* 123 */       if (value.equalsIgnoreCase("high")) {
/* 124 */         Trace.setTraceLevel(10);
/* 125 */       } else if (value.equalsIgnoreCase("medium")) {
/* 126 */         Trace.setTraceLevel(6);
/* 127 */       } else if (value.equalsIgnoreCase("low")) {
/* 128 */         Trace.setTraceLevel(3);
/*     */       } 
/*     */ 
/*     */       
/* 132 */       value = PropertyStore.getStringProperty("Diagnostics.Java.Trace.Destination.File").trim();
/* 133 */       if (value.equalsIgnoreCase("enabled"));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 139 */       value = PropertyStore.getStringProperty("Diagnostics.Java.Trace.Destination.Console").trim();
/*     */       
/* 141 */       if (value.equalsIgnoreCase("enabled")) {
/* 142 */         getLogger().addHandler(new ConsoleHandler());
/*     */       }
/*     */ 
/*     */       
/* 146 */       value = PropertyStore.getStringProperty("Diagnostics.Java.Trace.Destination.Pathname").trim();
/* 147 */       PropertyStore.set("com.ibm.msg.client.commonservices.trace.outputName", value);
/*     */       
/* 149 */       this.outputFileName = value;
/*     */       
/* 151 */       this
/* 152 */         .ffdcDirectory = PropertyStore.getStringProperty("Diagnostics.Java.FFDC.Destination.Pathname").trim();
/*     */     } 
/*     */ 
/*     */     
/* 156 */     if (Trace.isOn) {
/* 157 */       Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.wmqsupport.TracerImpl", "getTracerOptionsFromProperties()");
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
/*     */   public String ffst(Object sourceClass, String methodSignature, String probeID, HashMap<String, ? extends Object> data, String header) {
/* 170 */     if (Trace.isOn) {
/* 171 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.wmqsupport.TracerImpl", "ffst(Object,String,String,HashMap<String , ? extends Object>,String)", new Object[] { sourceClass, methodSignature, probeID, data, header });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 176 */     String traceRet1 = ffst(sourceClass, methodSignature, probeID, data, header, this.ffdcDirectory);
/*     */     
/* 178 */     if (Trace.isOn) {
/* 179 */       Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.wmqsupport.TracerImpl", "ffst(Object,String,String,HashMap<String , ? extends Object>,String)", traceRet1);
/*     */     }
/*     */     
/* 182 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\j2se\wmqsupport\TracerImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */