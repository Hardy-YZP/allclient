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
/*     */ public class MQRRSConnectionFactory
/*     */   extends MQConnectionFactory
/*     */ {
/*     */   public static final long serialVersionUID = -5457485831235701676L;
/*     */   
/*     */   static {
/*  43 */     if (Trace.isOn) {
/*  44 */       Trace.data("com.ibm.mq.jms.MQRRSConnectionFactory", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQRRSConnectionFactory.java");
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
/*     */   public MQRRSConnectionFactory() {
/*  60 */     if (Trace.isOn) {
/*  61 */       Trace.entry(this, "com.ibm.mq.jms.MQRRSConnectionFactory", "<init>()");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  66 */     initialiseMQRRSConnectionFactory();
/*     */     
/*     */     try {
/*  69 */       setIntProperty("XMSC_WMQ_CONNECTION_MODE", 0);
/*     */     }
/*  71 */     catch (JMSException e) {
/*  72 */       if (Trace.isOn) {
/*  73 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQRRSConnectionFactory", "<init>()", (Throwable)e);
/*     */       }
/*  75 */       if (Trace.isOn) {
/*  76 */         Trace.catchBlock(this, "c.i.mq.jms.MQRRSConnectionFactory", "<init>()", (Throwable)e);
/*     */       }
/*     */       
/*  79 */       Trace.ffst(this, "MQRRSConnectionFactory()", "XF005001", null, null);
/*     */     } 
/*  81 */     if (Trace.isOn) {
/*  82 */       Trace.exit(this, "com.ibm.mq.jms.MQRRSConnectionFactory", "<init>()");
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
/*     */   public MQRRSConnectionFactory(MQConnectionFactory cf) throws JMSException {
/*  97 */     this();
/*  98 */     if (Trace.isOn) {
/*  99 */       Trace.entry(this, "com.ibm.mq.jms.MQRRSConnectionFactory", "<init>(MQConnectionFactory)", new Object[] { cf });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 104 */     putAll((Map)cf);
/*     */ 
/*     */     
/* 107 */     setShortProperty("XMSC_ADMIN_OBJECT_TYPE", (short)(getShortProperty("XMSC_ADMIN_OBJECT_TYPE") | 0x100));
/*     */     
/* 109 */     if (Trace.isOn) {
/* 110 */       Trace.exit(this, "com.ibm.mq.jms.MQRRSConnectionFactory", "<init>(MQConnectionFactory)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initialiseMQRRSConnectionFactory() {
/* 121 */     if (Trace.isOn) {
/* 122 */       Trace.entry(this, "com.ibm.mq.jms.MQRRSConnectionFactory", "initialiseMQRRSConnectionFactory()");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 127 */     initialiseMQConnectionFactory();
/*     */     
/*     */     try {
/* 130 */       setShortProperty("XMSC_ADMIN_OBJECT_TYPE", (short)276);
/*     */ 
/*     */     
/*     */     }
/* 134 */     catch (JMSException je) {
/* 135 */       if (Trace.isOn) {
/* 136 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQRRSConnectionFactory", "initialiseMQRRSConnectionFactory()", (Throwable)je);
/*     */       }
/*     */ 
/*     */       
/* 140 */       Trace.ffst(this, "initialiseMQRRSConnectionFactory()", "XF005002", null, null);
/*     */     } 
/* 142 */     if (Trace.isOn) {
/* 143 */       Trace.exit(this, "com.ibm.mq.jms.MQRRSConnectionFactory", "initialiseMQRRSConnectionFactory()");
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
/* 161 */     if (Trace.isOn) {
/* 162 */       Trace.entry(this, "com.ibm.mq.jms.MQRRSConnectionFactory", "readObject(java.io.ObjectInputStream)", new Object[] { in });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 167 */     in.defaultReadObject();
/*     */ 
/*     */ 
/*     */     
/* 171 */     initialiseMQRRSConnectionFactory();
/* 172 */     if (Trace.isOn) {
/* 173 */       Trace.exit(this, "com.ibm.mq.jms.MQRRSConnectionFactory", "readObject(java.io.ObjectInputStream)");
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
/* 190 */     Reference ref = new Reference(getClass().getName(), MQRRSConnectionFactoryFactory.class.getName(), null);
/*     */ 
/*     */     
/* 193 */     Reference superClassReference = super.getReference();
/* 194 */     Enumeration<RefAddr> e = superClassReference.getAll();
/* 195 */     while (e.hasMoreElements()) {
/* 196 */       RefAddr currentRefAddr = e.nextElement();
/* 197 */       ref.add(currentRefAddr);
/*     */     } 
/*     */     
/* 200 */     if (Trace.isOn) {
/* 201 */       Trace.data(this, "com.ibm.mq.jms.MQRRSConnectionFactory", "getReference()", "getter", ref);
/*     */     }
/* 203 */     return ref;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQRRSConnectionFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */