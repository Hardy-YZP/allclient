/*     */ package com.ibm.msg.client.jms.admin;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsConnectionFactory;
/*     */ import com.ibm.msg.client.jms.JmsFactoryFactory;
/*     */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*     */ import com.ibm.msg.client.jms.JmsQueue;
/*     */ import com.ibm.msg.client.jms.JmsQueueConnectionFactory;
/*     */ import com.ibm.msg.client.jms.JmsTopic;
/*     */ import com.ibm.msg.client.jms.JmsTopicConnectionFactory;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import javax.naming.Context;
/*     */ import javax.naming.Name;
/*     */ import javax.naming.RefAddr;
/*     */ import javax.naming.Reference;
/*     */ import javax.naming.StringRefAddr;
/*     */ import javax.naming.spi.ObjectFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JmsJndiObjectFactory
/*     */   implements ObjectFactory
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/admin/JmsJndiObjectFactory.java";
/*     */   
/*     */   static {
/*  49 */     if (Trace.isOn) {
/*  50 */       Trace.data("com.ibm.msg.client.jms.admin.JmsJndiObjectFactory", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/admin/JmsJndiObjectFactory.java");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  59 */     if (Trace.isOn) {
/*  60 */       Trace.entry("com.ibm.msg.client.jms.admin.JmsJndiObjectFactory", "static()");
/*     */     }
/*  62 */     if (Trace.isOn) {
/*  63 */       Trace.data("JmsJndiObjectFactory", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/admin/JmsJndiObjectFactory.java");
/*     */     }
/*  65 */     if (Trace.isOn) {
/*  66 */       Trace.exit("com.ibm.msg.client.jms.admin.JmsJndiObjectFactory", "static()");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  71 */     if (Trace.isOn) {
/*  72 */       Trace.entry("com.ibm.msg.client.jms.admin.JmsJndiObjectFactory", "static()");
/*     */     }
/*     */     
/*  75 */     if (Trace.isOn) {
/*  76 */       Trace.exit("com.ibm.msg.client.jms.admin.JmsJndiObjectFactory", "static()");
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
/*     */   public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception {
/*     */     JmsTopic jmsTopic;
/*  99 */     if (Trace.isOn) {
/* 100 */       Trace.entry(this, "com.ibm.msg.client.jms.admin.JmsJndiObjectFactory", "getObjectInstance(Object,Name,Context,Hashtable<? , ?>)", new Object[] { obj, name, nameCtx, environment });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 105 */     JmsPropertyContext adminObject = null;
/*     */ 
/*     */     
/* 108 */     if (obj instanceof Reference) {
/*     */       
/* 110 */       Reference ref = (Reference)obj;
/*     */ 
/*     */       
/* 113 */       StringRefAddr connectionTypeName = (StringRefAddr)ref.get("XMSC_CONNECTION_TYPE_NAME");
/* 114 */       if (connectionTypeName != null) {
/* 115 */         String providerName = (String)connectionTypeName.getContent();
/*     */         
/* 117 */         JmsFactoryFactory factoryFactory = JmsFactoryFactory.getInstance(providerName);
/*     */ 
/*     */ 
/*     */         
/* 121 */         StringRefAddr objectTypeRef = (StringRefAddr)ref.get("XMSC_ADMIN_OBJECT_TYPE");
/* 122 */         if (objectTypeRef != null) {
/* 123 */           short objectType = Short.parseShort((String)objectTypeRef.getContent());
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 128 */           if ((objectType & 0x10) != 0) {
/* 129 */             if ((objectType & 0x1) != 0) {
/* 130 */               JmsQueueConnectionFactory jmsQueueConnectionFactory = factoryFactory.createQueueConnectionFactory();
/* 131 */             } else if ((objectType & 0x2) != 0) {
/* 132 */               JmsTopicConnectionFactory jmsTopicConnectionFactory = factoryFactory.createTopicConnectionFactory();
/* 133 */             } else if ((objectType & 0x4) != 0) {
/* 134 */               JmsConnectionFactory jmsConnectionFactory = factoryFactory.createConnectionFactory();
/*     */             } 
/* 136 */           } else if ((objectType & 0x20) != 0) {
/*     */ 
/*     */ 
/*     */             
/* 140 */             String destname = (String)ref.get("XMSC_DESTINATION_NAME").getContent();
/*     */             
/* 142 */             if ((objectType & 0x1) != 0) {
/* 143 */               JmsQueue jmsQueue = factoryFactory.createQueue(destname);
/* 144 */             } else if ((objectType & 0x2) != 0) {
/* 145 */               jmsTopic = factoryFactory.createTopic(destname);
/*     */             } 
/*     */           } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 154 */           if (jmsTopic != null) {
/* 155 */             Enumeration<RefAddr> addrs = ref.getAll();
/* 156 */             while (addrs.hasMoreElements()) {
/* 157 */               StringRefAddr addr = (StringRefAddr)addrs.nextElement();
/*     */ 
/*     */               
/* 160 */               jmsTopic.setObjectProperty(addr.getType(), addr.getContent());
/*     */             }
/*     */           
/*     */           }
/* 164 */           else if (Trace.isOn) {
/* 165 */             Trace.data("com.ibm.msg.client.jms.admin.JmsJndiObjectFactory", "getObjectInstance(Object,Name,Context,Hashtable<? , ?>)", "Object does not represent a Connection Factory or Destination. Returning null");
/*     */ 
/*     */           
/*     */           }
/*     */ 
/*     */ 
/*     */         
/*     */         }
/* 173 */         else if (Trace.isOn) {
/* 174 */           Trace.data("com.ibm.msg.client.jms.admin.JmsJndiObjectFactory", "getObjectInstance(Object,Name,Context,Hashtable<? , ?>)", "object type not found");
/*     */ 
/*     */         
/*     */         }
/*     */ 
/*     */       
/*     */       }
/* 181 */       else if (Trace.isOn) {
/* 182 */         Trace.data("com.ibm.msg.client.jms.admin.JmsJndiObjectFactory", "getObjectInstance(Object,Name,Context,Hashtable<? , ?>)", "No connection type name found");
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 190 */     if (Trace.isOn) {
/* 191 */       Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsJndiObjectFactory", "getObjectInstance(Object,Name,Context,Hashtable<? , ?>)", jmsTopic);
/*     */     }
/*     */     
/* 194 */     return jmsTopic;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\admin\JmsJndiObjectFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */