/*    */ package com.ibm.msg.client.osgi.wmq.factories;
/*    */ 
/*    */ import com.ibm.msg.client.commonservices.componentmanager.Component;
/*    */ import com.ibm.msg.client.commonservices.componentmanager.ComponentManager;
/*    */ import com.ibm.msg.client.commonservices.trace.Trace;
/*    */ import com.ibm.msg.client.wmq.factories.WMQComponent;
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
/*    */ public class Activator
/*    */   implements BundleActivator
/*    */ {
/*    */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.factories/src/com/ibm/msg/client/osgi/wmq/factories/Activator.java";
/*    */   
/*    */   static {
/* 36 */     if (Trace.isOn) {
/* 37 */       Trace.data("com.ibm.msg.client.osgi.wmq.factories.Activator", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.factories/src/com/ibm/msg/client/osgi/wmq/factories/Activator.java");
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
/*    */   public void start(BundleContext context) throws Exception {
/* 50 */     if (Trace.isOn) {
/* 51 */       Trace.entry(this, "com.ibm.msg.client.osgi.wmq.factories.Activator", "start(BundleContext)", new Object[] { context });
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 57 */     ComponentManager compManager = ComponentManager.getInstance();
/* 58 */     WMQComponent wMQComponent = new WMQComponent();
/* 59 */     compManager.registerComponent((Component)wMQComponent);
/* 60 */     if (Trace.isOn) {
/* 61 */       Trace.exit(this, "com.ibm.msg.client.osgi.wmq.factories.Activator", "start(BundleContext)");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void stop(BundleContext context) throws Exception {
/* 70 */     if (Trace.isOn) {
/* 71 */       Trace.entry(this, "com.ibm.msg.client.osgi.wmq.factories.Activator", "stop(BundleContext)", new Object[] { context });
/*    */     }
/*    */     
/* 74 */     if (Trace.isOn)
/* 75 */       Trace.exit(this, "com.ibm.msg.client.osgi.wmq.factories.Activator", "stop(BundleContext)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\osgi\wmq\factories\Activator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */