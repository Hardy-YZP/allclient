/*     */ package com.ibm.msg.client.wmq.common.internal.messages;
/*     */ 
/*     */ import com.ibm.mq.jmqi.MQMD;
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.mq.jmqi.system.zrfp.Triplet;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WMQSendMarshalMH
/*     */   extends WMQSendMarshal
/*     */ {
/*     */   public static final String sccsid2 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/messages/WMQSendMarshalMH.java";
/*     */   private Triplet[] triplets;
/*     */   
/*     */   static {
/*  44 */     if (Trace.isOn) {
/*  45 */       Trace.data("com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshalMH", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/messages/WMQSendMarshalMH.java");
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
/*     */   WMQSendMarshalMH() {
/*  64 */     if (Trace.isOn) {
/*  65 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshalMH", "<init>()");
/*     */     }
/*     */ 
/*     */     
/*  69 */     if (Trace.isOn) {
/*  70 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshalMH", "<init>()");
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
/*     */   protected void resetSendState() {
/*  83 */     if (Trace.isOn) {
/*  84 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshalMH", "resetSendState()");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  89 */     super.resetSendState();
/*     */ 
/*     */     
/*  92 */     this.triplets = null;
/*  93 */     if (Trace.isOn) {
/*  94 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshalMH", "resetSendState()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Triplet[] getTriplets() {
/* 104 */     if (Trace.isOn) {
/* 105 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshalMH", "getTriplets()", "getter", this.triplets);
/*     */     }
/*     */     
/* 108 */     return this.triplets;
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
/*     */   protected void writeMessageProperties(int bodyEncoding, JmqiCodepage bodyCodepage) throws JMSException {
/* 122 */     if (Trace.isOn) {
/* 123 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshalMH", "writeMessageProperties(int,JmqiCodepage)", new Object[] {
/* 124 */             Integer.valueOf(bodyEncoding), bodyCodepage
/*     */           });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 135 */     String overrideFormat = this.providerMessage.getStringProperty("JMS_IBM_MQMD_Format");
/* 136 */     if (overrideFormat != null && this.destination
/* 137 */       .getBooleanProperty("mdWriteEnabled") == true) {
/* 138 */       super.writeMessageProperties(bodyEncoding, bodyCodepage);
/* 139 */       if (Trace.isOn) {
/* 140 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshalMH", "writeMessageProperties(int,JmqiCodepage)", 1);
/*     */       }
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */     
/* 148 */     if (this.providerMessage.getJMSDeliveryDelay() != 0L) {
/* 149 */       super.writeMessageProperties(bodyEncoding, bodyCodepage);
/* 150 */       if (Trace.isOn) {
/* 151 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshalMH", "writeMessageProperties(int,JmqiCodepage)", 2);
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 157 */     boolean propsRequired = areMessagePropertiesRequired();
/*     */     
/* 159 */     if (propsRequired == true) {
/* 160 */       int index = 0;
/* 161 */       boolean mdPersistence = WMQMarshalUtils.getPersistenceFromMD(this.owner);
/*     */       
/* 163 */       this.triplets = new Triplet[4];
/* 164 */       this.triplets[index++] = this.providerMessage.getTripletMcd();
/* 165 */       this.triplets[index++] = this.providerMessage.getTripletJms(mdPersistence);
/* 166 */       this.triplets[index++] = this.providerMessage.getTripletMqext();
/* 167 */       this.triplets[index++] = this.providerMessage.getTripletUsr();
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 175 */     this.mqmd.setEncoding(bodyEncoding);
/* 176 */     this.mqmd.setCodedCharSetId(bodyCodepage.getCCSID());
/* 177 */     if (Trace.isOn) {
/* 178 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshalMH", "writeMessageProperties(int,JmqiCodepage)", 3);
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
/*     */   public void informSendMarshal(MQMD md) throws JMSException {
/* 193 */     if (Trace.isOn) {
/* 194 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshalMH", "informSendMarshal(MQMD)", new Object[] { md });
/*     */     }
/*     */     
/* 197 */     super.informSendMarshal(md);
/* 198 */     if (Trace.isOn)
/* 199 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshalMH", "informSendMarshal(MQMD)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\common\internal\messages\WMQSendMarshalMH.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */