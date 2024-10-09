/*    */ package com.ibm.msg.client.commonservices.j2se.wmqsupport;
/*    */ 
/*    */ import com.ibm.msg.client.commonservices.j2se.log.DefaultLogger;
/*    */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*    */ import com.ibm.msg.client.commonservices.trace.Trace;
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
/*    */ public class LoggerImpl
/*    */   extends DefaultLogger
/*    */ {
/*    */   private static final String DiagnosticsJavaErrorsDestinationFilename = "Diagnostics.Java.Errors.Destination.Filename";
/*    */   private static final String errorFile = "AMQJAVA.LOG";
/*    */   
/*    */   static {
/* 35 */     if (Trace.isOn) {
/* 36 */       Trace.data("com.ibm.msg.client.commonservices.j2se.wmqsupport.LoggerImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices.j2se/src/com/ibm/msg/client/commonservices/j2se/wmqsupport/LoggerImpl.java");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void getTracerOptionsFromProperties() {
/* 47 */     if (Trace.isOn) {
/* 48 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.wmqsupport.LoggerImpl", "getTracerOptionsFromProperties()");
/*    */     }
/*    */ 
/*    */     
/* 52 */     super.getTracerOptionsFromProperties();
/*    */     
/* 54 */     PropertyStore.register("Diagnostics.Java.Errors.Destination.Filename", "AMQJAVA.LOG");
/* 55 */     String value = PropertyStore.getStringProperty("Diagnostics.Java.Errors.Destination.Filename").trim();
/*    */     
/* 57 */     if (PropertyStore.wasOverridden("Diagnostics.Java.Errors.Destination.Filename", null))
/*    */     {
/* 59 */       PropertyStore.set("com.ibm.msg.client.commonservices.log.outputName", value);
/*    */     }
/*    */     
/* 62 */     if (Trace.isOn)
/* 63 */       Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.wmqsupport.LoggerImpl", "getTracerOptionsFromProperties()"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\j2se\wmqsupport\LoggerImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */