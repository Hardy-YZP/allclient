/*     */ package com.ibm.mq.jms.admin;
/*     */ 
/*     */ import com.ibm.mq.jms.MQDestination;
/*     */ import com.ibm.mq.jms.MQQueue;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
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
/*     */ public class QueueBAO
/*     */   extends DestBAO
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/QueueBAO.java";
/*     */   
/*     */   static {
/*  50 */     if (Trace.isOn) {
/*  51 */       Trace.data("com.ibm.mq.jms.admin.QueueBAO", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/QueueBAO.java");
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
/*  68 */   static final AP[] PROPERTIES = new AP[] { new APQU(), new APQMGR() };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public QueueBAO() {
/*  76 */     if (Trace.isOn) {
/*  77 */       Trace.entry(this, "com.ibm.mq.jms.admin.QueueBAO", "<init>()");
/*     */     }
/*  79 */     if (Trace.isOn) {
/*  80 */       Trace.exit(this, "com.ibm.mq.jms.admin.QueueBAO", "<init>()");
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
/*     */   public void setFromProperties(Map<String, Object> props) throws BAOException, JMSException {
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.data(this, "com.ibm.mq.jms.admin.QueueBAO", "setFromProperties(Map<String , Object>)", "setter", props);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 103 */     this.d = (MQDestination)new MQQueue();
/*     */ 
/*     */ 
/*     */     
/* 107 */     _setFromProperties(props);
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
/* 119 */     if (Trace.isOn) {
/* 120 */       Trace.entry(this, "com.ibm.mq.jms.admin.QueueBAO", "supportedProperties()");
/*     */     }
/* 122 */     List<String> result = super.supportedProperties();
/* 123 */     for (AP prop : PROPERTIES) {
/* 124 */       result.add(prop.shortName());
/*     */     }
/*     */     
/* 127 */     if (Trace.isOn) {
/* 128 */       Trace.exit(this, "com.ibm.mq.jms.admin.QueueBAO", "supportedProperties()", result);
/*     */     }
/* 130 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Object> getProperties() {
/* 141 */     if (Trace.isOn) {
/* 142 */       Trace.entry(this, "com.ibm.mq.jms.admin.QueueBAO", "getProperties()");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 148 */       if (this.d == null) {
/* 149 */         if (Trace.isOn) {
/* 150 */           Trace.exit(this, "com.ibm.mq.jms.admin.QueueBAO", "getProperties()", null, 1);
/*     */         }
/* 152 */         return null;
/*     */       } 
/*     */       
/* 155 */       Map<String, Object> props = super.getProperties();
/*     */       
/* 157 */       MQQueue q = (MQQueue)this.d;
/*     */       
/* 159 */       for (AP prop : PROPERTIES) {
/*     */         try {
/* 161 */           prop.setPropertyFromObject(props, q);
/*     */         }
/* 163 */         catch (JMSException e) {
/* 164 */           if (Trace.isOn) {
/* 165 */             Trace.catchBlock(this, "com.ibm.mq.jms.admin.QueueBAO", "getProperties()", (Throwable)e);
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 171 */       if (Trace.isOn) {
/* 172 */         Trace.exit(this, "com.ibm.mq.jms.admin.QueueBAO", "getProperties()", props, 2);
/*     */       }
/* 174 */       return props;
/*     */     }
/*     */     finally {
/*     */       
/* 178 */       if (Trace.isOn) {
/* 179 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.QueueBAO", "getProperties()");
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
/*     */   public int getType() {
/* 192 */     if (Trace.isOn) {
/* 193 */       Trace.data(this, "com.ibm.mq.jms.admin.QueueBAO", "getType()", "getter", 
/* 194 */           Integer.valueOf(1));
/*     */     }
/* 196 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFromObject(Object obj) throws BAOException {
/* 207 */     if (Trace.isOn) {
/* 208 */       Trace.data(this, "com.ibm.mq.jms.admin.QueueBAO", "setFromObject(Object)", "setter", obj);
/*     */     }
/* 210 */     if (obj instanceof MQQueue) {
/* 211 */       this.d = (MQDestination)obj;
/*     */     } else {
/*     */       
/* 214 */       BAOException be = new BAOException(10, null, null);
/* 215 */       if (Trace.isOn) {
/* 216 */         Trace.throwing(this, "com.ibm.mq.jms.admin.QueueBAO", "setFromObject(Object)", be);
/*     */       }
/* 218 */       throw be;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String name() {
/* 229 */     if (Trace.isOn) {
/* 230 */       Trace.entry(this, "com.ibm.mq.jms.admin.QueueBAO", "name()");
/*     */     }
/* 232 */     if (Trace.isOn) {
/* 233 */       Trace.exit(this, "com.ibm.mq.jms.admin.QueueBAO", "name()", "Q");
/*     */     }
/* 235 */     return "Q";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void _setFromProperties(Map<String, Object> props) throws BAOException, JMSException {
/* 244 */     if (Trace.isOn) {
/* 245 */       Trace.entry(this, "com.ibm.mq.jms.admin.QueueBAO", "_setFromProperties(Map<String , Object>)", new Object[] { props });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 250 */       super._setFromProperties(props);
/*     */       
/* 252 */       MQQueue q = (MQQueue)this.d;
/*     */       
/* 254 */       for (AP prop : PROPERTIES) {
/* 255 */         prop.setObjectFromProperty(q, props);
/*     */       }
/*     */     }
/*     */     finally {
/*     */       
/* 260 */       if (Trace.isOn) {
/* 261 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.QueueBAO", "_setFromProperties(Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 266 */     if (Trace.isOn)
/* 267 */       Trace.exit(this, "com.ibm.mq.jms.admin.QueueBAO", "_setFromProperties(Map<String , Object>)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\QueueBAO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */