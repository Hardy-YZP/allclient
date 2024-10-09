/*     */ package com.ibm.msg.client.wmq.common.internal.messages;
/*     */ 
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.provider.ProviderTextMessage;
/*     */ import com.ibm.msg.client.wmq.common.WMQThreadLocalStorage;
/*     */ import com.ibm.msg.client.wmq.common.internal.WMQUtils;
/*     */ import java.nio.ByteBuffer;
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
/*     */ public class WMQTextMessage
/*     */   extends WMQMessage
/*     */   implements ProviderTextMessage
/*     */ {
/*     */   private static final long serialVersionUID = -8937815037534630461L;
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/messages/WMQTextMessage.java";
/*     */   private transient JmqiCodepage codepage;
/*     */   private transient ByteBuffer body;
/*     */   private String text;
/*     */   
/*     */   static {
/*  41 */     if (Trace.isOn) {
/*  42 */       Trace.data("com.ibm.msg.client.wmq.common.internal.messages.WMQTextMessage", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/messages/WMQTextMessage.java");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WMQTextMessage() {
/*  75 */     if (Trace.isOn) {
/*  76 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQTextMessage", "<init>()");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  81 */     this.messageClass = "jms_text";
/*     */ 
/*     */     
/*  84 */     initialize();
/*     */     
/*  86 */     if (Trace.isOn) {
/*  87 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQTextMessage", "<init>()");
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
/*     */   ByteBuffer _exportBodyUsingTls(int encoding, JmqiCodepage bodyCodepage, WMQThreadLocalStorage tls) throws JMSException {
/*     */     ByteBuffer result;
/* 103 */     if (Trace.isOn) {
/* 104 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQTextMessage", "_exportBodyUsingTls(int,JmqiCodepage,WMQThreadLocalStorage)", new Object[] {
/*     */             
/* 106 */             Integer.valueOf(encoding), bodyCodepage, tls
/*     */           });
/*     */     }
/*     */     
/* 110 */     if (this.body == null) {
/*     */ 
/*     */       
/* 113 */       if (this.text == null)
/*     */       {
/* 115 */         result = null;
/*     */       }
/*     */       else
/*     */       {
/* 119 */         result = WMQUtils.computeBytesFromTextUsingTls(this.text, bodyCodepage, tls);
/* 120 */         setNewBody(result, bodyCodepage, false);
/*     */       
/*     */       }
/*     */ 
/*     */     
/*     */     }
/* 126 */     else if (this.codepage.equals(bodyCodepage)) {
/*     */       
/* 128 */       result = this.body;
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/* 136 */       String tmp = WMQUtils.computeTextFromByteBuffer(this.body, this.codepage);
/*     */ 
/*     */       
/* 139 */       result = WMQUtils.computeBytesFromTextUsingTls(tmp, bodyCodepage, tls);
/* 140 */       setNewBody(result, bodyCodepage, false);
/*     */     } 
/*     */ 
/*     */     
/* 144 */     if (Trace.isOn) {
/* 145 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQTextMessage", "_exportBodyUsingTls(int,JmqiCodepage,WMQThreadLocalStorage)", result);
/*     */     }
/*     */     
/* 148 */     return result;
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
/*     */   public byte[] _exportBody(int encoding, JmqiCodepage codepage) throws JMSException {
/* 163 */     ByteBuffer result = _exportBodyUsingTls(encoding, codepage, (WMQThreadLocalStorage)null);
/*     */ 
/*     */     
/* 166 */     return (result == null) ? null : result.array();
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
/* 182 */     if (Trace.isOn) {
/* 183 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQTextMessage", "_importBody(byte [ ],int,int,int,JmqiCodepage)", new Object[] { input, 
/*     */             
/* 185 */             Integer.valueOf(startIndex), Integer.valueOf(endIndex), Integer.valueOf(encoding), codepage });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 197 */     int length = (endIndex == -1) ? input.length : (endIndex - startIndex);
/* 198 */     byte[] bytes = new byte[length];
/* 199 */     System.arraycopy(input, startIndex, bytes, 0, length);
/* 200 */     ByteBuffer newBody = ByteBuffer.wrap(bytes);
/* 201 */     setNewBody(newBody, codepage, true);
/*     */     
/* 203 */     if (Trace.isOn) {
/* 204 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQTextMessage", "_importBody(byte [ ],int,int,int,JmqiCodepage)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearBody() {
/* 215 */     if (Trace.isOn) {
/* 216 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQTextMessage", "clearBody()");
/*     */     }
/*     */ 
/*     */     
/* 220 */     initialize();
/*     */     
/* 222 */     if (Trace.isOn) {
/* 223 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQTextMessage", "clearBody()");
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
/*     */   public String getText() throws JMSException {
/* 239 */     if (this.text == null && this.body != null) {
/* 240 */       String result = WMQUtils.computeTextFromByteBuffer(this.body, this.codepage);
/* 241 */       setMessageText(result, false);
/*     */     } 
/*     */     
/* 244 */     if (Trace.isOn) {
/* 245 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQTextMessage", "getText()", "getter", this.text);
/*     */     }
/*     */     
/* 248 */     return this.text;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initialize() {
/* 256 */     this.text = null;
/* 257 */     this.body = null;
/* 258 */     this.codepage = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setNewBody(ByteBuffer newBody, JmqiCodepage bodyCodepage, boolean inValidateCachedText) {
/* 269 */     if (Trace.isOn) {
/* 270 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQTextMessage", "setNewBody(ByteBuffer,JmqiCodepage,boolean)", new Object[] { newBody, bodyCodepage, 
/*     */             
/* 272 */             Boolean.valueOf(inValidateCachedText) });
/*     */     }
/* 274 */     if (inValidateCachedText) {
/* 275 */       this.text = null;
/*     */     }
/* 277 */     this.body = newBody;
/* 278 */     this.codepage = bodyCodepage;
/* 279 */     if (Trace.isOn) {
/* 280 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQTextMessage", "setNewBody(ByteBuffer,JmqiCodepage,boolean)");
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
/*     */   private void setMessageText(String newText, boolean inValidateCachedBytes) {
/* 292 */     if (Trace.isOn) {
/* 293 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQTextMessage", "setMessageText(String,boolean)", new Object[] { newText, 
/*     */             
/* 295 */             Boolean.valueOf(inValidateCachedBytes) });
/*     */     }
/* 297 */     this.text = newText;
/*     */     
/* 299 */     if (inValidateCachedBytes) {
/* 300 */       this.body = null;
/* 301 */       this.codepage = null;
/*     */     } 
/*     */ 
/*     */     
/* 305 */     if (this.isNullMessage == true) {
/* 306 */       if (Trace.isOn) {
/* 307 */         Trace.data(this, "setMessageText(String)", "isNullMessage is true. Ignoring new message text = ", newText);
/*     */       }
/* 309 */       this.text = null;
/*     */     }
/* 311 */     else if (!this.isNullMessage && this.text == null) {
/* 312 */       if (Trace.isOn) {
/* 313 */         Trace.data(this, "setMessageText(String)", "isNullMessage is false. New message text is null, but using empty string.", null);
/*     */       }
/* 315 */       this.text = "";
/*     */     } 
/* 317 */     if (Trace.isOn) {
/* 318 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQTextMessage", "setMessageText(String,boolean)");
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
/*     */   public void setText(String newText) throws JMSException {
/* 332 */     if (Trace.isOn) {
/* 333 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQTextMessage", "setText(String)", "setter", newText);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 338 */     this.isNullMessage = (newText == null);
/*     */     
/* 340 */     setMessageText(newText, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasBody() {
/* 349 */     if (Trace.isOn) {
/* 350 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQTextMessage", "hasBody()");
/*     */     }
/*     */     
/* 353 */     boolean traceRet1 = (this.body != null || this.text != null);
/* 354 */     if (Trace.isOn) {
/* 355 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQTextMessage", "hasBody()", 
/* 356 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 358 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\common\internal\messages\WMQTextMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */