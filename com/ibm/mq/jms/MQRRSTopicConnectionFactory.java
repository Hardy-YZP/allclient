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
/*     */ public class MQRRSTopicConnectionFactory
/*     */   extends MQTopicConnectionFactory
/*     */ {
/*     */   public static final long serialVersionUID = -2479792768681414391L;
/*     */   
/*     */   static {
/*  43 */     if (Trace.isOn) {
/*  44 */       Trace.data("com.ibm.mq.jms.MQRRSTopicConnectionFactory", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQRRSTopicConnectionFactory.java");
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
/*     */   public MQRRSTopicConnectionFactory() {
/*  60 */     if (Trace.isOn) {
/*  61 */       Trace.entry(this, "com.ibm.mq.jms.MQRRSTopicConnectionFactory", "<init>()");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  67 */     initialiseMQRRSTopicConnectionFactory();
/*     */     
/*     */     try {
/*  70 */       setIntProperty("XMSC_WMQ_CONNECTION_MODE", 0);
/*     */     }
/*  72 */     catch (JMSException e) {
/*  73 */       if (Trace.isOn) {
/*  74 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQRRSTopicConnectionFactory", "<init>()", (Throwable)e);
/*     */       }
/*  76 */       if (Trace.isOn) {
/*  77 */         Trace.catchBlock(this, "c.i.mq.jms.MQRRSTopicConnectionFactory", "<init>()", (Throwable)e);
/*     */       }
/*     */       
/*  80 */       Trace.ffst(this, "MQRRSTopicConnectionFactory()", "XF007001", null, null);
/*     */     } 
/*  82 */     if (Trace.isOn) {
/*  83 */       Trace.exit(this, "com.ibm.mq.jms.MQRRSTopicConnectionFactory", "<init>()");
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
/*     */   public MQRRSTopicConnectionFactory(MQTopicConnectionFactory cf) throws JMSException {
/*  98 */     this();
/*  99 */     if (Trace.isOn) {
/* 100 */       Trace.entry(this, "com.ibm.mq.jms.MQRRSTopicConnectionFactory", "<init>(MQTopicConnectionFactory)", new Object[] { cf });
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
/* 115 */         Trace.finallyBlock(this, "com.ibm.mq.jms.MQRRSTopicConnectionFactory", "<init>(MQTopicConnectionFactory)");
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 120 */       this.settingDefaults = false;
/*     */     } 
/*     */ 
/*     */     
/* 124 */     setShortProperty("XMSC_ADMIN_OBJECT_TYPE", (short)(getShortProperty("XMSC_ADMIN_OBJECT_TYPE") | 0x100));
/*     */     
/* 126 */     if (Trace.isOn) {
/* 127 */       Trace.exit(this, "com.ibm.mq.jms.MQRRSTopicConnectionFactory", "<init>(MQTopicConnectionFactory)");
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
/*     */   private void initialiseMQRRSTopicConnectionFactory() {
/* 139 */     if (Trace.isOn) {
/* 140 */       Trace.entry(this, "com.ibm.mq.jms.MQRRSTopicConnectionFactory", "initialiseMQRRSTopicConnectionFactory()");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 145 */     initialiseMQConnectionFactory();
/*     */     
/*     */     try {
/* 148 */       setShortProperty("XMSC_ADMIN_OBJECT_TYPE", (short)274);
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 153 */     catch (JMSException je) {
/* 154 */       if (Trace.isOn) {
/* 155 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQRRSTopicConnectionFactory", "initialiseMQRRSTopicConnectionFactory()", (Throwable)je);
/*     */       }
/*     */ 
/*     */       
/* 159 */       Trace.ffst(this, "initialiseMQRRSTopicConnectionFactory()", "XF007002", null, null);
/*     */     } 
/* 161 */     if (Trace.isOn) {
/* 162 */       Trace.exit(this, "com.ibm.mq.jms.MQRRSTopicConnectionFactory", "initialiseMQRRSTopicConnectionFactory()");
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
/* 180 */     if (Trace.isOn) {
/* 181 */       Trace.entry(this, "com.ibm.mq.jms.MQRRSTopicConnectionFactory", "readObject(java.io.ObjectInputStream)", new Object[] { in });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 186 */     in.defaultReadObject();
/*     */ 
/*     */ 
/*     */     
/* 190 */     initialiseMQRRSTopicConnectionFactory();
/* 191 */     if (Trace.isOn) {
/* 192 */       Trace.exit(this, "com.ibm.mq.jms.MQRRSTopicConnectionFactory", "readObject(java.io.ObjectInputStream)");
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
/*     */   public Reference getReference() throws NamingException {
/* 209 */     Reference ref = new Reference(getClass().getName(), MQRRSTopicConnectionFactoryFactory.class.getName(), null);
/*     */ 
/*     */     
/* 212 */     Reference superClassReference = super.getReference();
/* 213 */     Enumeration<RefAddr> e = superClassReference.getAll();
/* 214 */     while (e.hasMoreElements()) {
/* 215 */       RefAddr currentRefAddr = e.nextElement();
/* 216 */       ref.add(currentRefAddr);
/*     */     } 
/*     */     
/* 219 */     if (Trace.isOn) {
/* 220 */       Trace.data(this, "com.ibm.mq.jms.MQRRSTopicConnectionFactory", "getReference()", "getter", ref);
/*     */     }
/*     */     
/* 223 */     return ref;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQRRSTopicConnectionFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */