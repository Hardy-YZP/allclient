/*     */ package com.ibm.msg.client.commonservices.cssystem;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.CSIException;
/*     */ import com.ibm.msg.client.commonservices.componentmanager.Component;
/*     */ import com.ibm.msg.client.commonservices.componentmanager.ComponentManager;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WASSupport
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/cssystem/WASSupport.java";
/*     */   
/*     */   static {
/*  40 */     if (Trace.isOn) {
/*  41 */       Trace.data("com.ibm.msg.client.commonservices.cssystem.WASSupport", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/cssystem/WASSupport.java");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 137 */   private Boolean usingWASCommonServices = null;
/*     */   
/*     */   private WASSupport() {
/* 140 */     if (Trace.isOn) {
/* 141 */       Trace.entry(this, "com.ibm.msg.client.commonservices.cssystem.WASSupport", "<init>()");
/*     */     }
/*     */     
/* 144 */     if (Trace.isOn) {
/* 145 */       Trace.exit(this, "com.ibm.msg.client.commonservices.cssystem.WASSupport", "<init>()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized WASSupport getWASSupport() {
/* 156 */     WASSupport traceRet1 = new WASSupport();
/* 157 */     if (Trace.isOn) {
/* 158 */       Trace.data("com.ibm.msg.client.commonservices.cssystem.WASSupport", "getWASSupport()", "getter", traceRet1);
/*     */     }
/*     */     
/* 161 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized boolean isWASCommonServicesPresent() {
/* 169 */     if (this.usingWASCommonServices == null) {
/* 170 */       String csName = null;
/*     */       try {
/* 172 */         Component csComp = ComponentManager.getInstance().getComponent("CSI", null);
/* 173 */         csName = csComp.getName();
/*     */         
/* 175 */         if (Trace.isOn) {
/* 176 */           Trace.data(this, "<init>", "Located CS Component", csName);
/*     */         
/*     */         }
/*     */       }
/* 180 */       catch (CSIException e1) {
/* 181 */         if (Trace.isOn) {
/* 182 */           Trace.data(this, "com.ibm.msg.client.commonservices.cssystem.WASSupport", "isWASCommonServicesPresent()", "Caught expected exception", e1);
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 189 */       if (csName != null && csName.equalsIgnoreCase("com.ibm.ws.wmqcsi")) {
/* 190 */         this.usingWASCommonServices = Boolean.TRUE;
/*     */       } else {
/* 192 */         this.usingWASCommonServices = Boolean.FALSE;
/*     */       } 
/*     */     } 
/* 195 */     boolean traceRet1 = this.usingWASCommonServices.booleanValue();
/* 196 */     if (Trace.isOn) {
/* 197 */       Trace.data(this, "com.ibm.msg.client.commonservices.cssystem.WASSupport", "isWASCommonServicesPresent()", "getter", 
/* 198 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 200 */     return traceRet1;
/*     */   }
/*     */   
/*     */   public static interface WASRuntimeHelper {
/*     */     public static final String WAS_RUNTIME_HELPER_KEY = "com.ibm.mq.connector.JCARuntimeHelper";
/*     */     public static final int OUTSIDE_WAS = 1;
/*     */     public static final int APPLICATION_CLIENT = 2;
/*     */     public static final int THIN_CLIENT = 4;
/*     */     public static final int ZOS_CR = 8;
/*     */     public static final int ZOS_CRA = 16;
/*     */     public static final int ZOS_SR = 32;
/*     */     public static final int DISTRIBUTED_SERVER = 64;
/*     */     public static final int LIBERTY = 128;
/*     */     public static final int ZOS_LIBERTY = 184;
/*     */     public static final int DIST_LIBERTY = 192;
/*     */     
/*     */     int getEnvironment();
/*     */     
/*     */     long getLocalRRSTranId();
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\cssystem\WASSupport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */