/*     */ package com.ibm.mq.jms.admin;
/*     */ 
/*     */ import com.ibm.mq.jms.MQXAQueueConnectionFactory;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.lang.reflect.Method;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WSXAQCFBAO
/*     */   extends BAO
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/WSXAQCFBAO.java";
/*     */   
/*     */   static {
/*  55 */     if (Trace.isOn) {
/*  56 */       Trace.data("com.ibm.mq.jms.admin.WSXAQCFBAO", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/WSXAQCFBAO.java");
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
/*  69 */   private XAQCFBAO xaqcfb = new XAQCFBAO();
/*     */ 
/*     */   
/*  72 */   private Object wsxaqcf = null;
/*     */ 
/*     */   
/*     */   private Class<?> jmsWrapXAQCFClass;
/*     */   
/*     */   private Method getProviderObject;
/*     */ 
/*     */   
/*     */   public WSXAQCFBAO() {
/*  81 */     if (Trace.isOn) {
/*  82 */       Trace.entry(this, "com.ibm.mq.jms.admin.WSXAQCFBAO", "<init>()");
/*     */     }
/*     */     
/*     */     try {
/*  86 */       this.jmsWrapXAQCFClass = Class.forName("com.ibm.ejs.jms.mq.JMSWrapXAQueueConnectionFactory");
/*  87 */       this.getProviderObject = this.jmsWrapXAQCFClass.getMethod("getProviderObject", (Class[])null);
/*     */     }
/*  89 */     catch (ClassNotFoundException|NoSuchMethodException|SecurityException e) {
/*  90 */       if (Trace.isOn) {
/*  91 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.WSXAQCFBAO", "<init>()", e);
/*     */       }
/*  93 */       e.printStackTrace();
/*     */     } 
/*     */     
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.exit(this, "com.ibm.mq.jms.admin.WSXAQCFBAO", "<init>()");
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
/*     */   public Map<String, Object> getProperties() {
/* 109 */     Map<String, Object> traceRet1 = this.xaqcfb.getProperties();
/* 110 */     if (Trace.isOn) {
/* 111 */       Trace.data(this, "com.ibm.mq.jms.admin.WSXAQCFBAO", "getProperties()", "getter", traceRet1);
/*     */     }
/* 113 */     return traceRet1;
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
/* 124 */     if (Trace.isOn) {
/* 125 */       Trace.data(this, "com.ibm.mq.jms.admin.WSXAQCFBAO", "getType()", "getter", 
/* 126 */           Integer.valueOf(6));
/*     */     }
/* 128 */     return 6;
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
/*     */   public Object getObject() {
/* 141 */     if (Trace.isOn) {
/* 142 */       Trace.data(this, "com.ibm.mq.jms.admin.WSXAQCFBAO", "getObject()", "getter", this.wsxaqcf);
/*     */     }
/* 144 */     return this.wsxaqcf;
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
/* 155 */     if (Trace.isOn) {
/* 156 */       Trace.data(this, "com.ibm.mq.jms.admin.WSXAQCFBAO", "setFromObject(Object)", "setter", obj);
/*     */     }
/*     */     
/* 159 */     if (this.jmsWrapXAQCFClass.isInstance(obj)) {
/*     */ 
/*     */       
/*     */       try {
/* 163 */         this.xaqcfb.setFromObject(this.getProviderObject.invoke(obj, (Object[])null));
/*     */       }
/* 165 */       catch (IllegalAccessException|IllegalArgumentException|java.lang.reflect.InvocationTargetException e) {
/* 166 */         if (Trace.isOn) {
/* 167 */           Trace.catchBlock(this, "com.ibm.mq.jms.admin.WSXAQCFBAO", "setFromObject(Object)", e);
/*     */         }
/*     */         
/* 170 */         BAOException be = new BAOException(5, null, null);
/* 171 */         be.initCause(e);
/* 172 */         if (Trace.isOn) {
/* 173 */           Trace.throwing(this, "com.ibm.mq.jms.admin.WSXAQCFBAO", "setFromObject(Object)", be, 1);
/*     */         }
/* 175 */         throw be;
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 181 */       BAOException be = new BAOException(10, null, null);
/* 182 */       if (Trace.isOn) {
/* 183 */         Trace.throwing(this, "com.ibm.mq.jms.admin.WSXAQCFBAO", "setFromObject(Object)", be, 2);
/*     */       }
/* 185 */       throw be;
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
/*     */   public void setFromProperties(Map<String, Object> props) throws BAOException, JMSException {
/* 197 */     if (Trace.isOn) {
/* 198 */       Trace.data(this, "com.ibm.mq.jms.admin.WSXAQCFBAO", "setFromProperties(Map<String , Object>)", "setter", props);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 204 */       this.xaqcfb.setFromProperties(props);
/*     */ 
/*     */       
/* 207 */       MQXAQueueConnectionFactory xaqcf = (MQXAQueueConnectionFactory)this.xaqcfb.getObject();
/*     */ 
/*     */       
/*     */       try {
/* 211 */         this.wsxaqcf = this.jmsWrapXAQCFClass.getConstructor(new Class[] { MQXAQueueConnectionFactory.class }).newInstance(new Object[] { xaqcf });
/*     */       }
/* 213 */       catch (InstantiationException|IllegalAccessException|IllegalArgumentException|java.lang.reflect.InvocationTargetException|NoSuchMethodException|SecurityException e) {
/*     */         
/* 215 */         if (Trace.isOn) {
/* 216 */           Trace.catchBlock(this, "com.ibm.mq.jms.admin.WSXAQCFBAO", "setFromProperties(Map<String , Object>)", e);
/*     */         }
/*     */         
/* 219 */         BAOException be = new BAOException(5, null, null);
/* 220 */         be.initCause(e);
/* 221 */         if (Trace.isOn) {
/* 222 */           Trace.throwing(this, "com.ibm.mq.jms.admin.WSXAQCFBAO", "setFromProperties(Map<String , Object>)", be);
/*     */         }
/*     */         
/* 225 */         throw be;
/*     */       }
/*     */     
/*     */     } finally {
/*     */       
/* 230 */       if (Trace.isOn) {
/* 231 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.WSXAQCFBAO", "setFromProperties(Map<String , Object>)");
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
/*     */   
/*     */   public List<String> supportedProperties() {
/* 246 */     if (Trace.isOn) {
/* 247 */       Trace.entry(this, "com.ibm.mq.jms.admin.WSXAQCFBAO", "supportedProperties()");
/*     */     }
/* 249 */     List<String> traceRet1 = this.xaqcfb.supportedProperties();
/* 250 */     if (Trace.isOn) {
/* 251 */       Trace.exit(this, "com.ibm.mq.jms.admin.WSXAQCFBAO", "supportedProperties()", traceRet1);
/*     */     }
/* 253 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void semanticCheck(Map<String, Object> props) throws BAOException, JMSException {
/* 264 */     if (Trace.isOn) {
/* 265 */       Trace.entry(this, "com.ibm.mq.jms.admin.WSXAQCFBAO", "semanticCheck(Map<String , Object>)", new Object[] { props });
/*     */     }
/*     */     
/* 268 */     this.xaqcfb.semanticCheck(props);
/* 269 */     if (Trace.isOn) {
/* 270 */       Trace.exit(this, "com.ibm.mq.jms.admin.WSXAQCFBAO", "semanticCheck(Map<String , Object>)");
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
/*     */   public String name() {
/* 282 */     if (Trace.isOn) {
/* 283 */       Trace.entry(this, "com.ibm.mq.jms.admin.WSXAQCFBAO", "name()");
/*     */     }
/* 285 */     if (Trace.isOn) {
/* 286 */       Trace.exit(this, "com.ibm.mq.jms.admin.WSXAQCFBAO", "name()", "WSQCF");
/*     */     }
/* 288 */     return "WSQCF";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\WSXAQCFBAO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */