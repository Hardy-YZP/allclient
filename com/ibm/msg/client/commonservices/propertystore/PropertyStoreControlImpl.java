/*     */ package com.ibm.msg.client.commonservices.propertystore;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PropertyStoreControlImpl
/*     */   implements PropertyStoreControl
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/propertystore/PropertyStoreControlImpl.java";
/*     */   
/*     */   static {
/*  33 */     if (Trace.isOn) {
/*  34 */       Trace.data("com.ibm.msg.client.commonservices.propertystore.PropertyStoreControlImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/propertystore/PropertyStoreControlImpl.java");
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
/*     */   public void setProperty(String propertyName, Object newValue) {
/*  47 */     if (Trace.isOn) {
/*  48 */       Trace.entry(this, "com.ibm.msg.client.commonservices.propertystore.PropertyStoreControlImpl", "setProperty(String,Object)", new Object[] { propertyName, newValue });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  53 */     PropertyStore.set(propertyName, newValue);
/*  54 */     if (Trace.isOn) {
/*  55 */       Trace.exit(this, "com.ibm.msg.client.commonservices.propertystore.PropertyStoreControlImpl", "setProperty(String,Object)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String viewAllProperties() {
/*  66 */     if (Trace.isOn) {
/*  67 */       Trace.entry(this, "com.ibm.msg.client.commonservices.propertystore.PropertyStoreControlImpl", "viewAllProperties()");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  72 */     HashMap<String, Object> map = PropertyStore.getAll();
/*     */     
/*  74 */     StringBuilder buffer = new StringBuilder();
/*     */     
/*  76 */     for (Map.Entry<String, Object> entry : map.entrySet()) {
/*  77 */       buffer.append(entry.getKey());
/*  78 */       buffer.append(":\t");
/*  79 */       buffer.append(entry.getValue().toString());
/*  80 */       buffer.append('\n');
/*     */     } 
/*     */     
/*  83 */     String traceRet1 = buffer.toString();
/*  84 */     if (Trace.isOn) {
/*  85 */       Trace.exit(this, "com.ibm.msg.client.commonservices.propertystore.PropertyStoreControlImpl", "viewAllProperties()", traceRet1);
/*     */     }
/*     */     
/*  88 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String viewProperty(String propertyName) {
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.entry(this, "com.ibm.msg.client.commonservices.propertystore.PropertyStoreControlImpl", "viewProperty(String)", new Object[] { propertyName });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 102 */     StringBuilder buffer = new StringBuilder();
/*     */     
/* 104 */     buffer.append(propertyName);
/* 105 */     buffer.append(":\t");
/* 106 */     String value = PropertyStore.getObjectProperty(propertyName).toString();
/* 107 */     buffer.append(value);
/*     */     
/* 109 */     String traceRet1 = buffer.toString();
/* 110 */     if (Trace.isOn) {
/* 111 */       Trace.exit(this, "com.ibm.msg.client.commonservices.propertystore.PropertyStoreControlImpl", "viewProperty(String)", traceRet1);
/*     */     }
/*     */     
/* 114 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\propertystore\PropertyStoreControlImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */