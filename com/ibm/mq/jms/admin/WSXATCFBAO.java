/*     */ package com.ibm.mq.jms.admin;
/*     */ 
/*     */ import com.ibm.mq.jms.MQXATopicConnectionFactory;
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
/*     */ public class WSXATCFBAO
/*     */   extends BAO
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/WSXATCFBAO.java";
/*     */   
/*     */   static {
/*  55 */     if (Trace.isOn) {
/*  56 */       Trace.data("com.ibm.mq.jms.admin.WSXATCFBAO", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/WSXATCFBAO.java");
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
/*  69 */   private XATCFBAO xatcfb = new XATCFBAO();
/*     */ 
/*     */   
/*  72 */   private Object wsxatcf = null;
/*     */ 
/*     */   
/*     */   private Class<?> jmsWrapXATCFClass;
/*     */   
/*     */   private Method getProviderObject;
/*     */ 
/*     */   
/*     */   public WSXATCFBAO() {
/*  81 */     if (Trace.isOn) {
/*  82 */       Trace.entry(this, "com.ibm.mq.jms.admin.WSXATCFBAO", "<init>()");
/*     */     }
/*     */     
/*     */     try {
/*  86 */       this.jmsWrapXATCFClass = Class.forName("com.ibm.ejs.jms.mq.JMSWrapXATopicConnectionFactory");
/*  87 */       this.getProviderObject = this.jmsWrapXATCFClass.getMethod("getProviderObject", (Class[])null);
/*     */     }
/*  89 */     catch (ClassNotFoundException|NoSuchMethodException|SecurityException e) {
/*  90 */       if (Trace.isOn) {
/*  91 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.WSXATCFBAO", "<init>()", e);
/*     */       }
/*  93 */       e.printStackTrace();
/*     */     } 
/*     */     
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.exit(this, "com.ibm.mq.jms.admin.WSXATCFBAO", "<init>()");
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
/* 109 */     Map<String, Object> traceRet1 = this.xatcfb.getProperties();
/* 110 */     if (Trace.isOn) {
/* 111 */       Trace.data(this, "com.ibm.mq.jms.admin.WSXATCFBAO", "getProperties()", "getter", traceRet1);
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
/* 125 */       Trace.data(this, "com.ibm.mq.jms.admin.WSXATCFBAO", "getType()", "getter", 
/* 126 */           Integer.valueOf(7));
/*     */     }
/* 128 */     return 7;
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
/* 142 */       Trace.data(this, "com.ibm.mq.jms.admin.WSXATCFBAO", "getObject()", "getter", this.wsxatcf);
/*     */     }
/* 144 */     return this.wsxatcf;
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
/* 156 */       Trace.data(this, "com.ibm.mq.jms.admin.WSXATCFBAO", "setFromObject(Object)", "setter", obj);
/*     */     }
/* 158 */     if (this.jmsWrapXATCFClass.isInstance(obj)) {
/*     */ 
/*     */       
/*     */       try {
/* 162 */         this.xatcfb.setFromObject(this.getProviderObject.invoke(obj, (Object[])null));
/*     */       }
/* 164 */       catch (IllegalAccessException|IllegalArgumentException|java.lang.reflect.InvocationTargetException e) {
/* 165 */         if (Trace.isOn) {
/* 166 */           Trace.catchBlock(this, "com.ibm.mq.jms.admin.WSXATCFBAO", "setFromObject(Object)", e);
/*     */         }
/*     */         
/* 169 */         BAOException be = new BAOException(5, null, null);
/* 170 */         be.initCause(e);
/* 171 */         if (Trace.isOn) {
/* 172 */           Trace.throwing(this, "com.ibm.mq.jms.admin.WSXATCFBAO", "setFromObject(Object)", be, 1);
/*     */         }
/* 174 */         throw be;
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 180 */       BAOException be = new BAOException(10, null, null);
/* 181 */       if (Trace.isOn) {
/* 182 */         Trace.throwing(this, "com.ibm.mq.jms.admin.WSXATCFBAO", "setFromObject(Object)", be, 2);
/*     */       }
/* 184 */       throw be;
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
/* 196 */     if (Trace.isOn) {
/* 197 */       Trace.data(this, "com.ibm.mq.jms.admin.WSXATCFBAO", "setFromProperties(Map<String , Object>)", "setter", props);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 203 */       this.xatcfb.setFromProperties(props);
/*     */ 
/*     */       
/* 206 */       MQXATopicConnectionFactory xatcf = (MQXATopicConnectionFactory)this.xatcfb.getObject();
/*     */       
/* 208 */       this.wsxatcf = this.jmsWrapXATCFClass.getConstructor(new Class[] { MQXATopicConnectionFactory.class }).newInstance(new Object[] { xatcf });
/*     */     
/*     */     }
/* 211 */     catch (InstantiationException|IllegalAccessException|IllegalArgumentException|java.lang.reflect.InvocationTargetException|NoSuchMethodException|SecurityException e) {
/*     */       
/* 213 */       if (Trace.isOn) {
/* 214 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.WSXATCFBAO", "setFromProperties(Map<String , Object>)", e);
/*     */       }
/*     */       
/* 217 */       BAOException be = new BAOException(5, null, null);
/* 218 */       be.initCause(e);
/* 219 */       if (Trace.isOn) {
/* 220 */         Trace.throwing(this, "com.ibm.mq.jms.admin.WSXATCFBAO", "setFromProperties(Map<String , Object>)", be);
/*     */       }
/*     */       
/* 223 */       throw be;
/*     */     } finally {
/*     */       
/* 226 */       if (Trace.isOn) {
/* 227 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.WSXATCFBAO", "setFromProperties(Map<String , Object>)");
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
/* 242 */     if (Trace.isOn) {
/* 243 */       Trace.entry(this, "com.ibm.mq.jms.admin.WSXATCFBAO", "supportedProperties()");
/*     */     }
/* 245 */     List<String> traceRet1 = this.xatcfb.supportedProperties();
/* 246 */     if (Trace.isOn) {
/* 247 */       Trace.exit(this, "com.ibm.mq.jms.admin.WSXATCFBAO", "supportedProperties()", traceRet1);
/*     */     }
/* 249 */     return traceRet1;
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
/* 260 */     if (Trace.isOn) {
/* 261 */       Trace.entry(this, "com.ibm.mq.jms.admin.WSXATCFBAO", "semanticCheck(Map<String , Object>)", new Object[] { props });
/*     */     }
/*     */     
/* 264 */     this.xatcfb.semanticCheck(props);
/* 265 */     if (Trace.isOn) {
/* 266 */       Trace.exit(this, "com.ibm.mq.jms.admin.WSXATCFBAO", "semanticCheck(Map<String , Object>)");
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
/* 278 */     if (Trace.isOn) {
/* 279 */       Trace.entry(this, "com.ibm.mq.jms.admin.WSXATCFBAO", "name()");
/*     */     }
/* 281 */     if (Trace.isOn) {
/* 282 */       Trace.exit(this, "com.ibm.mq.jms.admin.WSXATCFBAO", "name()", "WSTCF");
/*     */     }
/* 284 */     return "WSTCF";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\WSXATCFBAO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */