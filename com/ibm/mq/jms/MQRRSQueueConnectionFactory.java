/*     */ package com.ibm.mq.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Map;
/*     */ import javax.jms.JMSException;
/*     */ import javax.naming.NamingException;
/*     */ import javax.naming.RefAddr;
/*     */ import javax.naming.Reference;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQRRSQueueConnectionFactory
/*     */   extends MQQueueConnectionFactory
/*     */ {
/*     */   public static final long serialVersionUID = -6348569792245910556L;
/*     */   
/*     */   static {
/*  43 */     if (Trace.isOn) {
/*  44 */       Trace.data("com.ibm.mq.jms.MQRRSQueueConnectionFactory", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQRRSQueueConnectionFactory.java");
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
/*     */   public MQRRSQueueConnectionFactory() {
/*  60 */     if (Trace.isOn) {
/*  61 */       Trace.entry(this, "com.ibm.mq.jms.MQRRSQueueConnectionFactory", "<init>()");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  67 */     initialiseMQRRSQueueConnectionFactory();
/*     */     
/*     */     try {
/*  70 */       setIntProperty("XMSC_WMQ_CONNECTION_MODE", 0);
/*     */     }
/*  72 */     catch (JMSException e) {
/*  73 */       if (Trace.isOn) {
/*  74 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQRRSQueueConnectionFactory", "<init>()", (Throwable)e);
/*     */       }
/*  76 */       if (Trace.isOn) {
/*  77 */         Trace.catchBlock(this, "c.i.mq.jms.MQRRSQueueConnectionFactory", "<init>()", (Throwable)e);
/*     */       }
/*     */       
/*  80 */       Trace.ffst(this, "MQRRSQueueConnectionFactory()", "XF006001", null, null);
/*     */     } 
/*  82 */     if (Trace.isOn) {
/*  83 */       Trace.exit(this, "com.ibm.mq.jms.MQRRSQueueConnectionFactory", "<init>()");
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
/*     */   public MQRRSQueueConnectionFactory(MQQueueConnectionFactory cf) throws JMSException {
/*  98 */     this();
/*  99 */     if (Trace.isOn) {
/* 100 */       Trace.entry(this, "com.ibm.mq.jms.MQRRSQueueConnectionFactory", "<init>(MQQueueConnectionFactory)", new Object[] { cf });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 110 */       this.settingDefaults = true;
/* 111 */       putAll((Map)cf);
/*     */     } finally {
/*     */       
/* 114 */       if (Trace.isOn) {
/* 115 */         Trace.finallyBlock(this, "com.ibm.mq.jms.MQRRSQueueConnectionFactory", "<init>(MQQueueConnectionFactory)");
/*     */       }
/*     */       
/* 118 */       this.settingDefaults = false;
/*     */     } 
/*     */ 
/*     */     
/* 122 */     setShortProperty("XMSC_ADMIN_OBJECT_TYPE", (short)(getShortProperty("XMSC_ADMIN_OBJECT_TYPE") | 0x100));
/*     */     
/* 124 */     if (Trace.isOn) {
/* 125 */       Trace.exit(this, "com.ibm.mq.jms.MQRRSQueueConnectionFactory", "<init>(MQQueueConnectionFactory)");
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
/*     */   private void initialiseMQRRSQueueConnectionFactory() {
/* 137 */     if (Trace.isOn) {
/* 138 */       Trace.entry(this, "com.ibm.mq.jms.MQRRSQueueConnectionFactory", "initialiseMQRRSQueueConnectionFactory()");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 143 */     initialiseMQConnectionFactory();
/*     */     
/*     */     try {
/* 146 */       setShortProperty("XMSC_ADMIN_OBJECT_TYPE", (short)273);
/*     */     
/*     */     }
/* 149 */     catch (JMSException je) {
/* 150 */       if (Trace.isOn) {
/* 151 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQRRSQueueConnectionFactory", "initialiseMQRRSQueueConnectionFactory()", (Throwable)je);
/*     */       }
/*     */ 
/*     */       
/* 155 */       Trace.ffst(this, "initialiseMQRRSQueueConnectionFactory()", "XF006002", null, null);
/*     */     } 
/* 157 */     if (Trace.isOn) {
/* 158 */       Trace.exit(this, "com.ibm.mq.jms.MQRRSQueueConnectionFactory", "initialiseMQRRSQueueConnectionFactory()");
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
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 176 */     if (Trace.isOn) {
/* 177 */       Trace.entry(this, "com.ibm.mq.jms.MQRRSQueueConnectionFactory", "readObject(java.io.ObjectInputStream)", new Object[] { in });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 182 */     in.defaultReadObject();
/*     */ 
/*     */ 
/*     */     
/* 186 */     initialiseMQRRSQueueConnectionFactory();
/* 187 */     if (Trace.isOn) {
/* 188 */       Trace.exit(this, "com.ibm.mq.jms.MQRRSQueueConnectionFactory", "readObject(java.io.ObjectInputStream)");
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
/*     */   public Reference getReference() throws NamingException {
/* 204 */     Reference ref = new Reference(getClass().getName(), MQRRSQueueConnectionFactoryFactory.class.getName(), null);
/*     */ 
/*     */     
/* 207 */     Reference superClassReference = super.getReference();
/* 208 */     Enumeration<RefAddr> e = superClassReference.getAll();
/* 209 */     while (e.hasMoreElements()) {
/* 210 */       RefAddr currentRefAddr = e.nextElement();
/* 211 */       ref.add(currentRefAddr);
/*     */     } 
/*     */     
/* 214 */     if (Trace.isOn) {
/* 215 */       Trace.data(this, "com.ibm.mq.jms.MQRRSQueueConnectionFactory", "getReference()", "getter", ref);
/*     */     }
/*     */     
/* 218 */     return ref;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQRRSQueueConnectionFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */