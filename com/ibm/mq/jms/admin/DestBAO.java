/*     */ package com.ibm.mq.jms.admin;
/*     */ 
/*     */ import com.ibm.mq.jms.MQDestination;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.jms.JMSException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class DestBAO
/*     */   extends BAO
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/DestBAO.java";
/*     */   
/*     */   static {
/*  53 */     if (Trace.isOn) {
/*  54 */       Trace.data("com.ibm.mq.jms.admin.DestBAO", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/DestBAO.java");
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
/*  69 */   static final AP[] PROPERTIES = new AP[] { new APVER(), new APEXP(), new APPRI(), new APPER(), new APCCS(), new APUMA(), new APUMR(), new APTC(), new APENC(), new APDESC(), new APFIQ(), new APRACP(), new APRAALD(), new APPAALD(), new APWMB(), new APWMRE(), new APWMWE(), new APWMMC(), new APRTOST(), new APRCCS(), new APRCNV(), new APALTU() };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   MQDestination d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Object> getProperties() {
/* 107 */     if (Trace.isOn) {
/* 108 */       Trace.entry(this, "com.ibm.mq.jms.admin.DestBAO", "getProperties()");
/*     */     }
/*     */     try {
/* 111 */       Map<String, Object> props = new HashMap<>();
/*     */       
/* 113 */       if (this.d == null) {
/* 114 */         if (Trace.isOn) {
/* 115 */           Trace.exit(this, "com.ibm.mq.jms.admin.DestBAO", "getProperties()", null, 1);
/*     */         }
/* 117 */         return null;
/*     */       } 
/*     */       
/* 120 */       for (AP prop : PROPERTIES) {
/*     */         try {
/* 122 */           prop.setPropertyFromObject(props, this.d);
/*     */         }
/* 124 */         catch (JMSException e) {
/* 125 */           if (Trace.isOn) {
/* 126 */             Trace.catchBlock(this, "com.ibm.mq.jms.admin.DestBAO", "getProperties()", (Throwable)e);
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 132 */       if (Trace.isOn) {
/* 133 */         Trace.exit(this, "com.ibm.mq.jms.admin.DestBAO", "getProperties()", props, 2);
/*     */       }
/* 135 */       return props;
/*     */     } finally {
/*     */       
/* 138 */       if (Trace.isOn) {
/* 139 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.DestBAO", "getProperties()");
/*     */       }
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
/*     */   public Object getObject() {
/* 152 */     if (Trace.isOn) {
/* 153 */       Trace.data(this, "com.ibm.mq.jms.admin.DestBAO", "getObject()", "getter", this.d);
/*     */     }
/* 155 */     return this.d;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void semanticCheck(Map<String, Object> props) {
/* 166 */     if (Trace.isOn) {
/* 167 */       Trace.entry(this, "com.ibm.mq.jms.admin.DestBAO", "semanticCheck(Map<String , Object>)", new Object[] { props });
/*     */     }
/*     */ 
/*     */     
/* 171 */     if (Trace.isOn) {
/* 172 */       Trace.exit(this, "com.ibm.mq.jms.admin.DestBAO", "semanticCheck(Map<String , Object>)");
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
/*     */   public List<String> supportedProperties() {
/* 185 */     if (Trace.isOn) {
/* 186 */       Trace.entry(this, "com.ibm.mq.jms.admin.DestBAO", "supportedProperties()");
/*     */     }
/* 188 */     List<String> result = new ArrayList<>();
/* 189 */     for (AP prop : PROPERTIES) {
/* 190 */       result.add(prop.shortName());
/*     */     }
/*     */     
/* 193 */     if (Trace.isOn) {
/* 194 */       Trace.exit(this, "com.ibm.mq.jms.admin.DestBAO", "supportedProperties()", result);
/*     */     }
/* 196 */     return result;
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
/*     */   void _setFromProperties(Map<String, Object> props) throws BAOException, JMSException {
/* 210 */     if (Trace.isOn) {
/* 211 */       Trace.entry(this, "com.ibm.mq.jms.admin.DestBAO", "_setFromProperties(Map<String , Object>)", new Object[] { props });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 216 */       for (AP prop : PROPERTIES) {
/* 217 */         prop.setObjectFromProperty(this.d, props);
/*     */       }
/*     */     }
/*     */     finally {
/*     */       
/* 222 */       if (Trace.isOn) {
/* 223 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.DestBAO", "_setFromProperties(Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 228 */     if (Trace.isOn)
/* 229 */       Trace.exit(this, "com.ibm.mq.jms.admin.DestBAO", "_setFromProperties(Map<String , Object>)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\DestBAO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */