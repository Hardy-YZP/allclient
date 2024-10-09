/*     */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*     */ 
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
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
/*     */ public class JMSNullMessage
/*     */   extends JMSMessage
/*     */ {
/*     */   static final long serialVersionUID = 1262376870719449729L;
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/JMSNullMessage.java";
/*     */   
/*     */   static {
/*  38 */     if (Trace.isOn) {
/*  39 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.JMSNullMessage", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/JMSNullMessage.java");
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
/*     */   boolean readOnly = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JMSNullMessage(JMSStringResources jmsStrings) throws JMSException {
/*  66 */     if (Trace.isOn) {
/*  67 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSNullMessage", "<init>(JMSStringResources)", new Object[] { jmsStrings });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  72 */     this.messageClass = "jms_none";
/*     */ 
/*     */     
/*  75 */     this.jmsStrings = jmsStrings;
/*     */     
/*  77 */     if (Trace.isOn) {
/*  78 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSNullMessage", "<init>(JMSStringResources)");
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
/*     */   public byte[] _exportBody(int encoding, JmqiCodepage codepage) throws JMSException {
/*  95 */     if (Trace.isOn) {
/*  96 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSNullMessage", "_exportBody(int,JmqiCodepage)", new Object[] {
/*  97 */             Integer.valueOf(encoding), codepage
/*     */           });
/*     */     }
/* 100 */     if (Trace.isOn) {
/* 101 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSNullMessage", "_exportBody(int,JmqiCodepage)", null);
/*     */     }
/*     */     
/* 104 */     return null;
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
/*     */   public void _importBody(byte[] wireformat, int dataStart, int encoding, JmqiCodepage codepage) throws JMSException {
/* 119 */     if (Trace.isOn) {
/* 120 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSNullMessage", "_importBody(byte [ ],int,int,JmqiCodepage)", new Object[] { wireformat, 
/*     */             
/* 122 */             Integer.valueOf(dataStart), Integer.valueOf(encoding), codepage });
/*     */     }
/*     */     
/* 125 */     JMSException traceRet1 = newJMSException(1001);
/* 126 */     if (Trace.isOn) {
/* 127 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSNullMessage", "_importBody(byte [ ],int,int,JmqiCodepage)", (Throwable)traceRet1);
/*     */     }
/*     */     
/* 130 */     throw traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearBody() throws JMSException {
/* 141 */     if (Trace.isOn) {
/* 142 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSNullMessage", "clearBody()");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 147 */     if (Trace.isOn) {
/* 148 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSNullMessage", "clearBody()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void _setBodyReadOnly() {
/* 157 */     if (Trace.isOn) {
/* 158 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSNullMessage", "_setBodyReadOnly()");
/*     */     }
/*     */     
/* 161 */     this.readOnly = true;
/* 162 */     if (Trace.isOn) {
/* 163 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSNullMessage", "_setBodyReadOnly()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasBody() {
/* 172 */     if (Trace.isOn) {
/* 173 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSNullMessage", "hasBody()");
/*     */     }
/* 175 */     if (Trace.isOn) {
/* 176 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSNullMessage", "hasBody()", 
/* 177 */           Boolean.valueOf(false));
/*     */     }
/* 179 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public long getJMSDeliveryTime() throws JMSException {
/* 185 */     if (Trace.isOn) {
/* 186 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSNullMessage", "getJMSDeliveryTime()", "getter", 
/* 187 */           Long.valueOf(0L));
/*     */     }
/* 189 */     return 0L;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setJMSDeliveryTime(long deliveryTime) throws JMSException {
/* 194 */     if (Trace.isOn)
/* 195 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSNullMessage", "setJMSDeliveryTime(long)", "setter", 
/* 196 */           Long.valueOf(deliveryTime)); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\JMSNullMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */