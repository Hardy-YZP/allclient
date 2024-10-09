/*     */ package com.ibm.msg.client.wmq.common.internal.messages;
/*     */ 
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.provider.ProviderObjectMessage;
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
/*     */ public class WMQObjectMessage
/*     */   extends WMQMessage
/*     */   implements ProviderObjectMessage
/*     */ {
/*     */   private static final long serialVersionUID = 8504626642994004178L;
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/messages/WMQObjectMessage.java";
/*     */   
/*     */   static {
/*  38 */     if (Trace.isOn) {
/*  39 */       Trace.data("com.ibm.msg.client.wmq.common.internal.messages.WMQObjectMessage", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/messages/WMQObjectMessage.java");
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
/*  55 */   private int dataEnd = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   private int dataStart = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   private byte[] objBytes = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WMQObjectMessage() {
/*  72 */     if (Trace.isOn) {
/*  73 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQObjectMessage", "<init>()");
/*     */     }
/*     */ 
/*     */     
/*  77 */     this.messageClass = "jms_object";
/*     */     
/*  79 */     if (Trace.isOn) {
/*  80 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQObjectMessage", "<init>()");
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
/*  97 */     if (Trace.isOn) {
/*  98 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQObjectMessage", "_exportBody(int,JmqiCodepage)", new Object[] {
/*  99 */             Integer.valueOf(encoding), codepage
/*     */           });
/*     */     }
/* 102 */     if (this.dataStart != 0 || this.dataEnd != -1) {
/*     */ 
/*     */       
/* 105 */       this.objBytes = WMQUtils.computeNewBytesFromBytes(this.objBytes, this.dataStart, this.dataEnd);
/* 106 */       this.dataStart = 0;
/* 107 */       this.dataEnd = -1;
/*     */     } 
/* 109 */     if (Trace.isOn) {
/* 110 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQObjectMessage", "_exportBody(int,JmqiCodepage)", this.objBytes);
/*     */     }
/*     */     
/* 113 */     return this.objBytes;
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
/* 129 */     if (Trace.isOn) {
/* 130 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQObjectMessage", "_importBody(byte [ ],int,int,int,JmqiCodepage)", new Object[] { input, 
/*     */             
/* 132 */             Integer.valueOf(startIndex), Integer.valueOf(endIndex), Integer.valueOf(encoding), codepage });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 139 */     this.objBytes = WMQUtils.computeNewBytesFromBytes(input, startIndex, endIndex);
/* 140 */     this.dataStart = 0;
/* 141 */     this.dataEnd = -1;
/*     */     
/* 143 */     if (Trace.isOn) {
/* 144 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQObjectMessage", "_importBody(byte [ ],int,int,int,JmqiCodepage)");
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
/* 158 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQObjectMessage", "clearBody()");
/*     */     }
/*     */ 
/*     */     
/* 162 */     this.objBytes = null;
/* 163 */     this.dataStart = 0;
/* 164 */     this.dataEnd = -1;
/*     */     
/* 166 */     if (Trace.isOn) {
/* 167 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQObjectMessage", "clearBody()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getSerializedObject() throws JMSException {
/* 178 */     if (Trace.isOn) {
/* 179 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQObjectMessage", "getSerializedObject()");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 185 */       if (this.dataStart == 0 && this.dataEnd == -1) {
/*     */         
/* 187 */         if (Trace.isOn) {
/* 188 */           Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQObjectMessage", "getSerializedObject()", this.objBytes, 1);
/*     */         }
/*     */         
/* 191 */         return this.objBytes;
/*     */       } 
/*     */       
/* 194 */       byte[] result = _exportBody(0, null);
/* 195 */       if (Trace.isOn) {
/* 196 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQObjectMessage", "getSerializedObject()", result, 2);
/*     */       }
/*     */       
/* 199 */       return result;
/*     */     }
/*     */     finally {
/*     */       
/* 203 */       if (Trace.isOn) {
/* 204 */         Trace.finallyBlock(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQObjectMessage", "getSerializedObject()");
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
/*     */   public void setSerializedObject(byte[] objectBytes) throws JMSException {
/* 216 */     if (Trace.isOn) {
/* 217 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQObjectMessage", "setSerializedObject(byte [ ])", "setter", objectBytes);
/*     */     }
/*     */ 
/*     */     
/* 221 */     this.objBytes = objectBytes;
/* 222 */     this.dataStart = 0;
/* 223 */     this.dataEnd = -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasBody() {
/* 232 */     if (Trace.isOn) {
/* 233 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQObjectMessage", "hasBody()");
/*     */     }
/*     */     
/* 236 */     boolean traceRet1 = (this.objBytes != null);
/* 237 */     if (Trace.isOn) {
/* 238 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQObjectMessage", "hasBody()", 
/* 239 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 241 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\common\internal\messages\WMQObjectMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */