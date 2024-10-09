/*     */ package com.ibm.msg.client.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.cssystem.CSSystem;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.HashMap;
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
/*     */ public abstract class JmsFactoryFactory
/*     */ {
/*     */   static final String JMS_FACTORY_FACTORY_IMPL_CLASS = "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl";
/*     */   
/*     */   static {
/*  54 */     if (Trace.isOn) {
/*  55 */       Trace.data("com.ibm.msg.client.jms.JmsFactoryFactory", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms/src/com/ibm/msg/client/jms/JmsFactoryFactory.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected JmsFactoryFactory() {
/*  64 */     if (Trace.isOn) {
/*  65 */       Trace.entry(this, "com.ibm.msg.client.jms.JmsFactoryFactory", "<init>()");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  72 */     if (Trace.isOn) {
/*  73 */       Trace.exit(this, "com.ibm.msg.client.jms.JmsFactoryFactory", "<init>()");
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
/*     */   public static JmsFactoryFactory getInstance(int connectionType) throws JMSException {
/*  87 */     if (Trace.isOn)
/*  88 */       Trace.entry("com.ibm.msg.client.jms.JmsFactoryFactory", "getInstance(int)", new Object[] {
/*  89 */             Integer.valueOf(connectionType)
/*     */           }); 
/*  91 */     JmsFactoryFactory factory = null;
/*     */ 
/*     */     
/*     */     try {
/*  95 */       Class<?> factoryClass = CSSystem.dynamicLoadClass("com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", JmsFactoryFactory.class);
/*     */       
/*  97 */       Method method = factoryClass.getMethod("getInstance", new Class[] { int.class });
/*  98 */       if (method != null)
/*     */       {
/* 100 */         factory = (JmsFactoryFactory)method.invoke(null, new Object[] { Integer.valueOf(connectionType) });
/*     */       
/*     */       }
/*     */     }
/* 104 */     catch (Exception e) {
/* 105 */       if (Trace.isOn) {
/* 106 */         Trace.catchBlock("com.ibm.msg.client.jms.JmsFactoryFactory", "getInstance(int)", e);
/*     */       }
/* 108 */       JMSException rethrow = null;
/*     */ 
/*     */       
/* 111 */       if (e instanceof InvocationTargetException) {
/* 112 */         Throwable cause = ((InvocationTargetException)e).getCause();
/*     */         
/* 114 */         if (cause instanceof JMSException) {
/* 115 */           rethrow = (JMSException)cause;
/*     */         }
/*     */       } 
/*     */       
/* 119 */       if (rethrow == null) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 124 */         rethrow = new JMSException(e.getMessage());
/* 125 */         rethrow.setLinkedException(e);
/*     */ 
/*     */ 
/*     */         
/* 129 */         HashMap<String, Object> data = new HashMap<>();
/* 130 */         data.put("exception", e);
/* 131 */         data.put("connectionType", Integer.valueOf(connectionType));
/* 132 */         Trace.ffst("JmsFactoryFactory", "getIntance(int)", "XI001004", data, null);
/*     */       } 
/*     */ 
/*     */       
/* 136 */       if (Trace.isOn) {
/* 137 */         Trace.throwing("com.ibm.msg.client.jms.JmsFactoryFactory", "getInstance(int)", (Throwable)rethrow);
/*     */       }
/* 139 */       throw rethrow;
/*     */     } 
/*     */     
/* 142 */     if (Trace.isOn) {
/* 143 */       Trace.exit("com.ibm.msg.client.jms.JmsFactoryFactory", "getInstance(int)", factory);
/*     */     }
/* 145 */     return factory;
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
/*     */   public static JmsFactoryFactory getInstance(String connectionTypeName) throws JMSException {
/* 157 */     if (Trace.isOn) {
/* 158 */       Trace.entry("com.ibm.msg.client.jms.JmsFactoryFactory", "getInstance(String)", new Object[] { connectionTypeName });
/*     */     }
/*     */ 
/*     */     
/* 162 */     JmsFactoryFactory factory = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 169 */       Class<?> factoryClass = CSSystem.dynamicLoadClass("com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", JmsFactoryFactory.class, true, 4);
/*     */       
/* 171 */       Method method = factoryClass.getMethod("getInstance", new Class[] { String.class });
/* 172 */       if (method != null) {
/* 173 */         factory = (JmsFactoryFactory)method.invoke(null, new Object[] { connectionTypeName });
/*     */       
/*     */       }
/*     */     }
/* 177 */     catch (Exception e) {
/* 178 */       if (Trace.isOn) {
/* 179 */         Trace.catchBlock("com.ibm.msg.client.jms.JmsFactoryFactory", "getInstance(String)", e);
/*     */       }
/* 181 */       JMSException rethrow = null;
/*     */ 
/*     */       
/* 184 */       if (e instanceof InvocationTargetException) {
/* 185 */         Throwable cause = ((InvocationTargetException)e).getCause();
/*     */         
/* 187 */         if (cause instanceof JMSException) {
/* 188 */           rethrow = (JMSException)cause;
/*     */         }
/*     */       } 
/*     */       
/* 192 */       if (rethrow == null) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 197 */         rethrow = new JMSException(e.getMessage());
/* 198 */         rethrow.setLinkedException(e);
/*     */ 
/*     */ 
/*     */         
/* 202 */         HashMap<String, Object> data = new HashMap<>();
/* 203 */         data.put("exception", e);
/* 204 */         data.put("connectionTypeName", connectionTypeName);
/* 205 */         Trace.ffst("JmsFactoryFactory", "getIntance(int)", "XI001005", data, null);
/*     */       } 
/*     */       
/* 208 */       if (Trace.isOn) {
/* 209 */         Trace.throwing("com.ibm.msg.client.jms.JmsFactoryFactory", "getInstance(String)", (Throwable)rethrow);
/*     */       }
/*     */       
/* 212 */       throw rethrow;
/*     */     } 
/*     */     
/* 215 */     if (Trace.isOn) {
/* 216 */       Trace.exit("com.ibm.msg.client.jms.JmsFactoryFactory", "getInstance(String)", factory);
/*     */     }
/* 218 */     return factory;
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
/*     */   public static JmsFactoryFactory getInstance(HashMap<String, Object> filter) throws JMSException {
/* 235 */     if (Trace.isOn) {
/* 236 */       Trace.entry("com.ibm.msg.client.jms.JmsFactoryFactory", "getInstance(HashMap<String , Object>)", new Object[] { filter });
/*     */     }
/*     */ 
/*     */     
/* 240 */     JmsFactoryFactory factory = null;
/*     */ 
/*     */     
/*     */     try {
/* 244 */       Class<?> factoryClass = CSSystem.dynamicLoadClass("com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", JmsFactoryFactory.class);
/*     */       
/* 246 */       Method method = factoryClass.getMethod("getInstance", new Class[] { HashMap.class });
/* 247 */       if (method != null) {
/* 248 */         factory = (JmsFactoryFactory)method.invoke(null, new Object[] { filter });
/*     */       
/*     */       }
/*     */     }
/* 252 */     catch (Exception e) {
/* 253 */       if (Trace.isOn) {
/* 254 */         Trace.catchBlock("com.ibm.msg.client.jms.JmsFactoryFactory", "getInstance(HashMap<String , Object>)", e);
/*     */       }
/*     */       
/* 257 */       JMSException rethrow = null;
/*     */ 
/*     */       
/* 260 */       if (e instanceof InvocationTargetException) {
/* 261 */         Throwable cause = ((InvocationTargetException)e).getCause();
/*     */         
/* 263 */         if (cause instanceof JMSException) {
/* 264 */           rethrow = (JMSException)cause;
/*     */         }
/*     */       } 
/*     */       
/* 268 */       if (rethrow == null) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 273 */         rethrow = new JMSException(e.getMessage());
/* 274 */         rethrow.setLinkedException(e);
/*     */ 
/*     */ 
/*     */         
/* 278 */         HashMap<String, Object> data = new HashMap<>();
/* 279 */         data.put("exception", e);
/* 280 */         data.put("connectionTypeFilter", filter);
/* 281 */         Trace.ffst("JmsFactoryFactory", "getIntance(int)", "XI001006", data, null);
/*     */       } 
/*     */       
/* 284 */       if (Trace.isOn) {
/* 285 */         Trace.throwing("com.ibm.msg.client.jms.JmsFactoryFactory", "getInstance(HashMap<String , Object>)", (Throwable)rethrow);
/*     */       }
/*     */       
/* 288 */       throw rethrow;
/*     */     } 
/*     */     
/* 291 */     if (Trace.isOn) {
/* 292 */       Trace.exit("com.ibm.msg.client.jms.JmsFactoryFactory", "getInstance(HashMap<String , Object>)", factory);
/*     */     }
/*     */     
/* 295 */     return factory;
/*     */   }
/*     */   
/*     */   public abstract JmsConnectionFactory createConnectionFactory() throws JMSException;
/*     */   
/*     */   public abstract JmsConnectionFactory createConnectionFactory(String paramString) throws JMSException;
/*     */   
/*     */   public abstract JmsQueueConnectionFactory createQueueConnectionFactory() throws JMSException;
/*     */   
/*     */   public abstract JmsQueueConnectionFactory createQueueConnectionFactory(String paramString) throws JMSException;
/*     */   
/*     */   public abstract JmsTopicConnectionFactory createTopicConnectionFactory() throws JMSException;
/*     */   
/*     */   public abstract JmsTopicConnectionFactory createTopicConnectionFactory(String paramString) throws JMSException;
/*     */   
/*     */   public abstract JmsQueue createQueue(String paramString) throws JMSException;
/*     */   
/*     */   public abstract JmsTopic createTopic(String paramString) throws JMSException;
/*     */   
/*     */   public abstract JmsXAConnectionFactory createXAConnectionFactory() throws JMSException;
/*     */   
/*     */   public abstract JmsXAConnectionFactory createXAConnectionFactory(String paramString) throws JMSException;
/*     */   
/*     */   public abstract JmsXAQueueConnectionFactory createXAQueueConnectionFactory() throws JMSException;
/*     */   
/*     */   public abstract JmsXAQueueConnectionFactory createXAQueueConnectionFactory(String paramString) throws JMSException;
/*     */   
/*     */   public abstract JmsXATopicConnectionFactory createXATopicConnectionFactory() throws JMSException;
/*     */   
/*     */   public abstract JmsXATopicConnectionFactory createXATopicConnectionFactory(String paramString) throws JMSException;
/*     */   
/*     */   public abstract JmsConnectionFactory createConnectionFactory(short paramShort) throws JMSException;
/*     */   
/*     */   public abstract JmsDestination createDestination(short paramShort, String paramString) throws JMSException;
/*     */   
/*     */   public abstract JmsPropertyContext createJmsObject(short paramShort, Object paramObject) throws JMSException;
/*     */   
/*     */   public abstract int getConnectionType();
/*     */   
/*     */   public abstract String getConnectionTypeName();
/*     */   
/*     */   public abstract JmsCapabilityContext getCapabilities() throws JMSException;
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\JmsFactoryFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */