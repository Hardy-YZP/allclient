/*     */ package com.ibm.mq.jmqi.local;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LocalClient
/*     */   extends LocalMQ
/*     */ {
/*     */   public static final String sccsid2 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/LocalClient.java";
/*     */   private static final String LIBRARYNAME = "mqjbndc";
/*     */   private static final String ISERIES_LIBRARYNAME = "/QSYS.LIB/QMQM.LIB/MQJBNDC.SRVPGM";
/*     */   private static final String ZOS_BOOTSTRAP_LIBRARYNAME = "mqjbndc";
/*     */   private static final String ZOS_BOOTSTRAP_LIBRARYNAME_64 = "mqjbndc64";
/*     */   private static final String ZOS_BATCH_LIBRARYNAME = "mqjbatchc";
/*     */   private static final String ZOS_BATCH_LIBRARYNAME_64 = "mqjbatch64c";
/*     */   private static final String ZOS_CICS_LIBRARYNAME = "mqjcicsc";
/*     */   private static final String ZOS_CICS_LIBRARYNAME_64 = "mqjcics64c";
/*     */   private static final String ZOS_RRS_LIBRARYNAME = "mqjrrsc";
/*     */   private static final String ZOS_RRS_LIBRARYNAME_64 = "mqjrrs64c";
/*     */   private static final String ZOS_WMB_LIBRARYNAME = "mqjwmbc";
/*     */   private static final String ZOS_WMB_LIBRARYNAME_64 = "mqjwmb64c";
/*     */   private static final String ZOS_EXTERNAL_RRS_LIBRARYNAME = "mqjexrrsc";
/*     */   private static final String ZOS_EXTERNAL_RRS_LIBRARYNAME_64 = "mqjexrrs64c";
/*     */   private static final String ZOS_INTERNAL_RRS_LIBRARYNAME = "mqjinrrsc";
/*     */   private static final String ZOS_INTERNAL_RRS_LIBRARYNAME_64 = "mqjinrrs64c";
/*     */   private static final String WINDOWS_LIBRARYNAME_64 = "mqjbnd64c";
/*     */   
/*     */   static {
/*  35 */     if (Trace.isOn) {
/*  36 */       Trace.data("com.ibm.mq.jmqi.local.LocalClient", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/LocalClient.java");
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
/*     */   public String getLibraryName() {
/*  69 */     return "mqjbndc";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getISeriesLibraryName() {
/*  78 */     return "/QSYS.LIB/QMQM.LIB/MQJBNDC.SRVPGM";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getZosBootstrapLibraryName() {
/*  84 */     return "mqjbndc";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getZosBootstrapLibraryName64() {
/*  93 */     return "mqjbndc64";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getZosBatchLibraryName() {
/* 102 */     return "mqjbatchc";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getZosBatchLibraryName64() {
/* 111 */     return "mqjbatch64c";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getZosCicsLibraryName() {
/* 120 */     return "mqjcicsc";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getZosCicsLibraryName64() {
/* 129 */     return "mqjcics64c";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getZosWASLibraryName() {
/* 138 */     return "mqjrrsc";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getZosWASLibraryName64() {
/* 147 */     return "mqjrrs64c";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getZosWMBLibraryName() {
/* 156 */     return "mqjwmbc";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getZosWMBLibraryName64() {
/* 165 */     return "mqjwmb64c";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getZosExternalRRSLibraryName() {
/* 174 */     return "mqjexrrsc";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getZosExternalRRSLibraryName64() {
/* 183 */     return "mqjexrrs64c";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getZosInternalRRSLibraryName() {
/* 192 */     return "mqjinrrsc";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getZosInternalRRSLibraryName64() {
/* 201 */     return "mqjinrrs64c";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getWindowsLibraryName64() {
/* 210 */     return "mqjbnd64c";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getWindowsLibraryName() {
/* 219 */     return "mqjbndc";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LocalClient(JmqiEnvironment env, int options) throws JmqiException {
/* 230 */     super(env, options);
/* 231 */     if (Trace.isOn) {
/* 232 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalClient", "<init>(JmqiEnvironment,int)", new Object[] { env, 
/* 233 */             Integer.valueOf(options) });
/*     */     }
/* 235 */     if (Trace.isOn) {
/* 236 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalClient", "<init>(JmqiEnvironment,int)");
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
/*     */   public String getJmqiComponentName() {
/* 248 */     if (Trace.isOn) {
/* 249 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalClient", "getJmqiComponentName()", "getter", "jmqi.local.client");
/*     */     }
/*     */     
/* 252 */     return "jmqi.local.client";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMqiId() {
/* 260 */     if (Trace.isOn) {
/* 261 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalClient", "getMqiId()", "getter", 
/* 262 */           Integer.valueOf(1));
/*     */     }
/* 264 */     return 1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\local\LocalClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */