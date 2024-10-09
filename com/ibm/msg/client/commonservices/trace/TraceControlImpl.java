/*     */ package com.ibm.msg.client.commonservices.trace;
/*     */ 
/*     */ import java.security.AccessControlException;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TraceControlImpl
/*     */   implements TraceControl
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/trace/TraceControlImpl.java";
/*     */   
/*     */   public boolean isOn() {
/*  45 */     return Trace.isOn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOn() {
/*  54 */     Trace.setOn(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOff() {
/*  63 */     Trace.setOn(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void includePackage(String packageName) {
/*  71 */     Trace.includePackage(packageName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void excludePackage(String packageName) {
/*  79 */     Trace.excludePackage(packageName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PackageNode rootNode() {
/*  87 */     return Trace.getRootNode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTraceFileName() {
/*  95 */     return Trace.getTraceFileName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUserDir() {
/*     */     String userDir;
/*     */     try {
/* 105 */       userDir = AccessController.<String>doPrivileged(new PrivilegedAction<String>()
/*     */           {
/*     */             public String run()
/*     */             {
/* 109 */               return System.getProperty("user.dir");
/*     */             }
/*     */           });
/*     */     }
/* 113 */     catch (AccessControlException ace) {
/* 114 */       userDir = "<unknown>";
/*     */     } 
/* 116 */     return userDir;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String dumpState(String dumpLocation, boolean compress) {
/* 124 */     return Trace.dumpState(dumpLocation, compress);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\trace\TraceControlImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */