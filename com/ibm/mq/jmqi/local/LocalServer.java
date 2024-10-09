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
/*     */ public class LocalServer
/*     */   extends LocalMQ
/*     */ {
/*     */   public static final String sccsid2 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/LocalServer.java";
/*     */   private static final String LIBRARYNAME = "mqjbnd";
/*     */   private static final String ISERIES_LIBRARYNAME = "/QSYS.LIB/QMQM.LIB/MQJBND.SRVPGM";
/*     */   private static final String ZOS_BOOTSTRAP_LIBRARYNAME = "mqjbnd";
/*     */   private static final String ZOS_BOOTSTRAP_LIBRARYNAME_64 = "mqjbnd64";
/*     */   private static final String ZOS_BATCH_LIBRARYNAME = "mqjbatch";
/*     */   private static final String ZOS_BATCH_LIBRARYNAME_64 = "mqjbatch64";
/*     */   private static final String ZOS_CICS_LIBRARYNAME = "mqjcics";
/*     */   private static final String ZOS_CICS_LIBRARYNAME_64 = "mqjcics64";
/*     */   private static final String ZOS_RRS_LIBRARYNAME = "mqjrrs";
/*     */   private static final String ZOS_RRS_LIBRARYNAME_64 = "mqjrrs64";
/*     */   private static final String ZOS_WMB_LIBRARYNAME = "mqjwmb";
/*     */   private static final String ZOS_WMB_LIBRARYNAME_64 = "mqjwmb64";
/*     */   private static final String ZOS_EXTERNAL_RRS_LIBRARYNAME = "mqjexrrs";
/*     */   private static final String ZOS_EXTERNAL_RRS_LIBRARYNAME_64 = "mqjexrrs64";
/*     */   private static final String ZOS_INTERNAL_RRS_LIBRARYNAME = "mqjinrrs";
/*     */   private static final String ZOS_INTERNAL_RRS_LIBRARYNAME_64 = "mqjinrrs64";
/*     */   private static final String WINDOWS_LIBRARYNAME_64 = "mqjbnd64";
/*     */   
/*     */   static {
/*  35 */     if (Trace.isOn) {
/*  36 */       Trace.data("com.ibm.mq.jmqi.local.LocalServer", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/LocalServer.java");
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
/*  69 */     return "mqjbnd";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getISeriesLibraryName() {
/*  78 */     return "/QSYS.LIB/QMQM.LIB/MQJBND.SRVPGM";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getZosBootstrapLibraryName() {
/*  84 */     return "mqjbnd";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getZosBootstrapLibraryName64() {
/*  93 */     return "mqjbnd64";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getZosBatchLibraryName() {
/* 102 */     return "mqjbatch";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getZosBatchLibraryName64() {
/* 111 */     return "mqjbatch64";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getZosCicsLibraryName() {
/* 120 */     return "mqjcics";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getZosCicsLibraryName64() {
/* 129 */     return "mqjcics64";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getZosWASLibraryName() {
/* 138 */     return "mqjrrs";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getZosWASLibraryName64() {
/* 147 */     return "mqjrrs64";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getZosWMBLibraryName() {
/* 156 */     return "mqjwmb";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getZosWMBLibraryName64() {
/* 165 */     return "mqjwmb64";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getZosExternalRRSLibraryName() {
/* 174 */     return "mqjexrrs";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getZosExternalRRSLibraryName64() {
/* 183 */     return "mqjexrrs64";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getZosInternalRRSLibraryName() {
/* 192 */     return "mqjinrrs";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getZosInternalRRSLibraryName64() {
/* 201 */     return "mqjinrrs64";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getWindowsLibraryName64() {
/* 210 */     return "mqjbnd64";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getWindowsLibraryName() {
/* 219 */     return "mqjbnd";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LocalServer(JmqiEnvironment env, int options) throws JmqiException {
/* 230 */     super(env, options);
/* 231 */     if (Trace.isOn) {
/* 232 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalServer", "<init>(JmqiEnvironment,int)", new Object[] { env, 
/* 233 */             Integer.valueOf(options) });
/*     */     }
/* 235 */     if (Trace.isOn) {
/* 236 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalServer", "<init>(JmqiEnvironment,int)");
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
/* 249 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalServer", "getJmqiComponentName()", "getter", "jmqi.local.server");
/*     */     }
/*     */     
/* 252 */     return "jmqi.local.server";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMqiId() {
/* 260 */     if (Trace.isOn) {
/* 261 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalServer", "getMqiId()", "getter", 
/* 262 */           Integer.valueOf(0));
/*     */     }
/* 264 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\local\LocalServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */