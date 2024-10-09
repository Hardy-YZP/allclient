/*     */ package com.ibm.msg.client.commonservices.j2se.wmqsupport;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.j2se.propertystore.PropertyStoreImpl;
/*     */ import com.ibm.msg.client.commonservices.propertystore.PropertiesFileException;
/*     */ import com.ibm.msg.client.commonservices.provider.propertystore.CSPPropertyStore;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.File;
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
/*     */ public class PropertyStoreImpl
/*     */   extends PropertyStoreImpl
/*     */   implements CSPPropertyStore
/*     */ {
/*     */   private static final String mqPropertyFile = "com.ibm.mq.commonservices";
/*     */   
/*     */   static {
/*  63 */     if (Trace.isOn) {
/*  64 */       Trace.data("com.ibm.msg.client.commonservices.j2se.wmqsupport.PropertyStoreImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices.j2se/src/com/ibm/msg/client/commonservices/j2se/wmqsupport/PropertyStoreImpl.java");
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
/*     */   public PropertyStoreImpl() {
/*  77 */     if (Trace.isOn) {
/*  78 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.wmqsupport.PropertyStoreImpl", "<init>()");
/*     */     }
/*     */     
/*  81 */     getOptionsFromMQPropertiesFile();
/*  82 */     if (Trace.isOn) {
/*  83 */       Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.wmqsupport.PropertyStoreImpl", "<init>()");
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
/*     */   private void getOptionsFromMQPropertiesFile() {
/* 146 */     if (Trace.isOn) {
/* 147 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.wmqsupport.PropertyStoreImpl", "getOptionsFromMQPropertiesFile()");
/*     */     }
/*     */     
/* 150 */     String wmqPropsFilename = (String)getProperty("com.ibm.mq.commonservices");
/*     */     
/* 152 */     if (wmqPropsFilename != null && !wmqPropsFilename.equals("")) {
/* 153 */       File propsFile = new File(wmqPropsFilename);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 159 */       if (!propsFile.exists() || !propsFile.isFile() || !propsFile.canRead()) {
/* 160 */         throw new RuntimeException(String.format("'%s' is not a readable file", new Object[] { wmqPropsFilename }));
/*     */       }
/*     */       
/*     */       try {
/* 164 */         addPropertiesFile(wmqPropsFilename, null, false);
/*     */       }
/* 166 */       catch (PropertiesFileException e) {
/* 167 */         if (Trace.isOn) {
/* 168 */           Trace.catchBlock(this, "com.ibm.msg.client.commonservices.j2se.wmqsupport.PropertyStoreImpl", "getOptionsFromMQPropertiesFile()", (Throwable)e);
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 178 */     if (Trace.isOn)
/* 179 */       Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.wmqsupport.PropertyStoreImpl", "getOptionsFromMQPropertiesFile()"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\j2se\wmqsupport\PropertyStoreImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */