/*     */ package com.ibm.mq.jms.admin;
/*     */ 
/*     */ import com.ibm.mq.jms.MQConnectionFactory;
/*     */ import com.ibm.mq.jms.MQQueueConnectionFactory;
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
/*     */ public class QCFBAO
/*     */   extends CFBAO
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/QCFBAO.java";
/*     */   
/*     */   static {
/*  48 */     if (Trace.isOn) {
/*  49 */       Trace.data("com.ibm.mq.jms.admin.QCFBAO", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/QCFBAO.java");
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
/*  68 */   static final AP[] PROPERTIES = new AP[] { new APTM(), new APMRET(), new APTQP() };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public QCFBAO() {
/*  76 */     if (Trace.isOn) {
/*  77 */       Trace.entry(this, "com.ibm.mq.jms.admin.QCFBAO", "<init>()");
/*     */     }
/*  79 */     if (Trace.isOn) {
/*  80 */       Trace.exit(this, "com.ibm.mq.jms.admin.QCFBAO", "<init>()");
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
/*     */   public void setFromProperties(Map<String, Object> props) throws BAOException, JMSException {
/*  97 */     if (Trace.isOn) {
/*  98 */       Trace.data(this, "com.ibm.mq.jms.admin.QCFBAO", "setFromProperties(Map<String , Object>)", "setter", props);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 106 */       this.cf = (MQConnectionFactory)new MQQueueConnectionFactory();
/*     */ 
/*     */ 
/*     */       
/* 110 */       _setFromProperties(props);
/*     */     }
/*     */     finally {
/*     */       
/* 114 */       if (Trace.isOn) {
/* 115 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.QCFBAO", "setFromProperties(Map<String , Object>)");
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
/*     */   
/*     */   public List<String> supportedProperties() {
/* 129 */     if (Trace.isOn) {
/* 130 */       Trace.entry(this, "com.ibm.mq.jms.admin.QCFBAO", "supportedProperties()");
/*     */     }
/* 132 */     List<String> result = commonSupportedProperties();
/* 133 */     for (AP prop : PROPERTIES) {
/* 134 */       result.add(prop.shortName());
/*     */     }
/*     */     
/* 137 */     if (Trace.isOn) {
/* 138 */       Trace.exit(this, "com.ibm.mq.jms.admin.QCFBAO", "supportedProperties()", result);
/*     */     }
/* 140 */     return result;
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
/* 151 */     if (Trace.isOn) {
/* 152 */       Trace.entry(this, "com.ibm.mq.jms.admin.QCFBAO", "getProperties()");
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 157 */       if (this.cf == null) {
/* 158 */         if (Trace.isOn) {
/* 159 */           Trace.exit(this, "com.ibm.mq.jms.admin.QCFBAO", "getProperties()", null, 1);
/*     */         }
/* 161 */         return null;
/*     */       } 
/*     */       
/* 164 */       MQQueueConnectionFactory qcf = (MQQueueConnectionFactory)this.cf;
/*     */       
/* 166 */       Map<String, Object> props = getCommonProperties();
/*     */       
/* 168 */       for (int i = 0; i < PROPERTIES.length; i++) {
/*     */         try {
/* 170 */           PROPERTIES[i].setPropertyFromObject(props, qcf);
/*     */         }
/* 172 */         catch (JMSException e) {
/* 173 */           if (Trace.isOn) {
/* 174 */             Trace.catchBlock(this, "com.ibm.mq.jms.admin.QCFBAO", "getProperties()", (Throwable)e);
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 180 */       if (Trace.isOn) {
/* 181 */         Trace.exit(this, "com.ibm.mq.jms.admin.QCFBAO", "getProperties()", props, 2);
/*     */       }
/* 183 */       return props;
/*     */     }
/*     */     finally {
/*     */       
/* 187 */       if (Trace.isOn) {
/* 188 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.QCFBAO", "getProperties()");
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
/* 201 */     if (Trace.isOn) {
/* 202 */       Trace.data(this, "com.ibm.mq.jms.admin.QCFBAO", "getType()", "getter", 
/* 203 */           Integer.valueOf(0));
/*     */     }
/* 205 */     return 0;
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
/* 216 */     if (Trace.isOn) {
/* 217 */       Trace.data(this, "com.ibm.mq.jms.admin.QCFBAO", "setFromObject(Object)", "setter", obj);
/*     */     }
/* 219 */     if (obj instanceof MQQueueConnectionFactory) {
/* 220 */       this.cf = (MQConnectionFactory)obj;
/*     */     } else {
/*     */       
/* 223 */       BAOException be = new BAOException(10, null, null);
/* 224 */       if (Trace.isOn) {
/* 225 */         Trace.throwing(this, "com.ibm.mq.jms.admin.QCFBAO", "setFromObject(Object)", be);
/*     */       }
/* 227 */       throw be;
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
/* 238 */     if (Trace.isOn) {
/* 239 */       Trace.entry(this, "com.ibm.mq.jms.admin.QCFBAO", "name()");
/*     */     }
/* 241 */     if (Trace.isOn) {
/* 242 */       Trace.exit(this, "com.ibm.mq.jms.admin.QCFBAO", "name()", "QCF");
/*     */     }
/* 244 */     return "QCF";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void _setFromProperties(Map<String, Object> props) throws BAOException, JMSException {
/* 253 */     if (Trace.isOn) {
/* 254 */       Trace.entry(this, "com.ibm.mq.jms.admin.QCFBAO", "_setFromProperties(Map<String , Object>)", new Object[] { props });
/*     */     }
/*     */ 
/*     */     
/* 258 */     _commonSetFromProperties(props);
/*     */ 
/*     */     
/* 261 */     for (int i = 0; i < PROPERTIES.length; i++) {
/* 262 */       PROPERTIES[i].setObjectFromProperty(this.cf, props);
/*     */     }
/*     */     
/* 265 */     if (Trace.isOn)
/* 266 */       Trace.exit(this, "com.ibm.mq.jms.admin.QCFBAO", "_setFromProperties(Map<String , Object>)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\QCFBAO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */