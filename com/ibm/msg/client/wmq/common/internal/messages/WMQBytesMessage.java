/*     */ package com.ibm.msg.client.wmq.common.internal.messages;
/*     */ 
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.provider.ProviderBytesMessage;
/*     */ import com.ibm.msg.client.wmq.common.internal.WMQUtils;
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
/*     */ 
/*     */ public class WMQBytesMessage
/*     */   extends WMQMessage
/*     */   implements ProviderBytesMessage
/*     */ {
/*     */   private static final long serialVersionUID = 4939588168641754626L;
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/messages/WMQBytesMessage.java";
/*     */   private byte[] bytes;
/*     */   
/*     */   static {
/*  58 */     if (Trace.isOn) {
/*  59 */       Trace.data("com.ibm.msg.client.wmq.common.internal.messages.WMQBytesMessage", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/messages/WMQBytesMessage.java");
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
/*  74 */   private int dataEnd = -1;
/*     */   
/*  76 */   private int dataStart = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WMQBytesMessage() {
/*  83 */     if (Trace.isOn) {
/*  84 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQBytesMessage", "<init>()");
/*     */     }
/*     */ 
/*     */     
/*  88 */     this.messageClass = "jms_bytes";
/*     */     
/*  90 */     if (Trace.isOn) {
/*  91 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQBytesMessage", "<init>()");
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
/*     */   public byte[] _exportBody(int encoding, JmqiCodepage codepage) throws JMSException {
/* 103 */     if (Trace.isOn) {
/* 104 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQBytesMessage", "_exportBody(int,JmqiCodepage)", new Object[] {
/* 105 */             Integer.valueOf(encoding), codepage
/*     */           });
/*     */     }
/* 108 */     byte[] bodyByteArray = getBodyByteArray();
/*     */     
/* 110 */     if (Trace.isOn) {
/* 111 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQBytesMessage", "_exportBody(int,JmqiCodepage)", bodyByteArray);
/*     */     }
/*     */     
/* 114 */     return bodyByteArray;
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
/*     */   public void _importBody(byte[] input, int startIndex, int endIndex, int encoding, JmqiCodepage codepage) throws JMSException {
/* 130 */     if (Trace.isOn) {
/* 131 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQBytesMessage", "_importBody(byte [ ],int,int,int,JmqiCodepage)", new Object[] { input, 
/*     */             
/* 133 */             Integer.valueOf(startIndex), Integer.valueOf(endIndex), Integer.valueOf(encoding), codepage });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 139 */     this.bytes = WMQUtils.computeNewBytesFromBytes(input, startIndex, endIndex);
/* 140 */     this.dataStart = 0;
/* 141 */     this.dataEnd = -1;
/*     */     
/* 143 */     if (Trace.isOn) {
/* 144 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQBytesMessage", "_importBody(byte [ ],int,int,int,JmqiCodepage)");
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
/*     */   public void clearBody() throws JMSException {
/* 157 */     if (Trace.isOn) {
/* 158 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQBytesMessage", "clearBody()");
/*     */     }
/*     */ 
/*     */     
/* 162 */     this.bytes = new byte[0];
/*     */     
/* 164 */     if (Trace.isOn) {
/* 165 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQBytesMessage", "clearBody()");
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
/*     */   private byte[] getBodyByteArray() {
/* 180 */     if (this.dataStart != 0 || this.dataEnd != -1) {
/*     */ 
/*     */       
/* 183 */       this.bytes = WMQUtils.computeNewBytesFromBytes(this.bytes, this.dataStart, this.dataEnd);
/* 184 */       this.dataStart = 0;
/* 185 */       this.dataEnd = -1;
/*     */     } 
/* 187 */     if (Trace.isOn) {
/* 188 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQBytesMessage", "getBodyByteArray()", "getter", this.bytes);
/*     */     }
/*     */     
/* 191 */     return this.bytes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getBytes() {
/* 201 */     byte[] bodyByteArray = getBodyByteArray();
/*     */     
/* 203 */     if (Trace.isOn) {
/* 204 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQBytesMessage", "getBytes()", "getter", bodyByteArray);
/*     */     }
/*     */     
/* 207 */     return bodyByteArray;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBytes(byte[] newBytes) {
/* 216 */     if (Trace.isOn) {
/* 217 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQBytesMessage", "setBytes(byte [ ])", "setter", newBytes);
/*     */     }
/*     */ 
/*     */     
/* 221 */     this.bytes = newBytes;
/* 222 */     this.dataStart = 0;
/* 223 */     this.dataEnd = -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasBody() {
/* 229 */     if (Trace.isOn) {
/* 230 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQBytesMessage", "hasBody()");
/*     */     }
/*     */     
/* 233 */     int i = ((this.bytes == null) ? 1 : 0) | ((this.bytes.length == 0) ? 1 : 0);
/* 234 */     if (Trace.isOn) {
/* 235 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQBytesMessage", "hasBody()", 
/* 236 */           Boolean.valueOf((i == 0)));
/*     */     }
/* 238 */     return (i == 0);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\common\internal\messages\WMQBytesMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */