/*    */ package com.ibm.msg.client.osgi.commonservices.j2se;
/*    */ 
/*    */ import com.ibm.msg.client.commonservices.componentmanager.Component;
/*    */ import com.ibm.msg.client.commonservices.componentmanager.ComponentManager;
/*    */ import com.ibm.msg.client.commonservices.j2se.J2SEComponent;
/*    */ import com.ibm.msg.client.commonservices.trace.Trace;
/*    */ import org.osgi.framework.BundleActivator;
/*    */ import org.osgi.framework.BundleContext;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Activator
/*    */   implements BundleActivator
/*    */ {
/*    */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices.j2se/src/com/ibm/msg/client/osgi/commonservices/j2se/Activator.java";
/*    */   
/*    */   static {
/* 37 */     if (Trace.isOn) {
/* 38 */       Trace.data("com.ibm.msg.client.osgi.commonservices.j2se.Activator", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices.j2se/src/com/ibm/msg/client/osgi/commonservices/j2se/Activator.java");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void start(BundleContext context) throws Exception {
/* 52 */     if (Trace.isOn) {
/* 53 */       Trace.entry(this, "com.ibm.msg.client.osgi.commonservices.j2se.Activator", "start(BundleContext)", new Object[] { context });
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 59 */     ComponentManager compManager = ComponentManager.getInstance();
/* 60 */     J2SEComponent j2SEComponent = new J2SEComponent();
/* 61 */     compManager.registerComponent((Component)j2SEComponent);
/* 62 */     if (Trace.isOn) {
/* 63 */       Trace.exit(this, "com.ibm.msg.client.osgi.commonservices.j2se.Activator", "start(BundleContext)");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void stop(BundleContext context) throws Exception {
/* 74 */     if (Trace.isOn) {
/* 75 */       Trace.entry(this, "com.ibm.msg.client.osgi.commonservices.j2se.Activator", "stop(BundleContext)", new Object[] { context });
/*    */     }
/*    */     
/* 78 */     if (Trace.isOn)
/* 79 */       Trace.exit(this, "com.ibm.msg.client.osgi.commonservices.j2se.Activator", "stop(BundleContext)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\osgi\commonservices\j2se\Activator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */