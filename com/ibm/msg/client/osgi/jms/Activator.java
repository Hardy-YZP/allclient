/*     */ package com.ibm.msg.client.osgi.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.componentmanager.ComponentManager;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.net.URL;
/*     */ import org.osgi.framework.Bundle;
/*     */ import org.osgi.framework.BundleActivator;
/*     */ import org.osgi.framework.BundleContext;
/*     */ import org.osgi.framework.BundleException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Activator
/*     */   implements BundleActivator
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms/src/com/ibm/msg/client/osgi/jms/Activator.java";
/*     */   private static final String COMP_INFO_FILE = "META-INF/compinfo.properties";
/*     */   
/*     */   static {
/*  40 */     if (Trace.isOn) {
/*  41 */       Trace.data("com.ibm.msg.client.osgi.jms.Activator", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms/src/com/ibm/msg/client/osgi/jms/Activator.java");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  50 */     ComponentManager._traceData(null, "com.ibm.msg.client.osgi.jms.Activator", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms/src/com/ibm/msg/client/osgi/jms/Activator.java");
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
/*     */   public void start(BundleContext context) throws Exception {
/*  74 */     ComponentManager._traceEntry(this, "com.ibm.msg.client.osgi.jms.Activator", "start(BundleContext)", new Object[] { context });
/*     */ 
/*     */ 
/*     */     
/*  78 */     Bundle[] bundles = context.getBundles();
/*     */ 
/*     */     
/*  81 */     for (int i = 0; i < bundles.length; i++) {
/*  82 */       URL resource = bundles[i].getResource("META-INF/compinfo.properties");
/*     */       
/*  84 */       if (resource != null) {
/*     */         try {
/*  86 */           bundles[i].start();
/*     */         }
/*  88 */         catch (BundleException e) {
/*  89 */           ComponentManager._traceCatchBlock(this, "com.ibm.msg.client.osgi.jms.Activator", "start(BundleContext)", (Throwable)e, 2);
/*     */         } 
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  97 */     ComponentManager._traceExit(this, "com.ibm.msg.client.osgi.jms.Activator", "start(BundleContext)", null, 1);
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
/*     */   public void stop(BundleContext context) throws Exception {
/* 109 */     if (Trace.isOn) {
/* 110 */       Trace.entry(this, "com.ibm.msg.client.osgi.jms.Activator", "stop(BundleContext)", new Object[] { context });
/*     */     }
/*     */     
/* 113 */     if (Trace.isOn)
/* 114 */       Trace.exit(this, "com.ibm.msg.client.osgi.jms.Activator", "stop(BundleContext)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\osgi\jms\Activator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */