/*     */ package com.ibm.msg.client.wmq.common.internal.messages;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.MQHeader;
/*     */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.mq.jmqi.system.JmqiComponentTls;
/*     */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*     */ import com.ibm.mq.jmqi.system.JmqiTls;
/*     */ import com.ibm.mq.jmqi.system.zrfp.ReceiveZRFP;
/*     */ import com.ibm.mq.jmqi.system.zrfp.Triplet;
/*     */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.wmq.common.WMQThreadLocalStorage;
/*     */ import java.io.UnsupportedEncodingException;
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
/*     */ public class WMQReceiveMarshalMH
/*     */   extends WMQReceiveMarshal
/*     */ {
/*     */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/messages/WMQReceiveMarshalMH.java";
/*     */   
/*     */   static {
/*  57 */     if (Trace.isOn) {
/*  58 */       Trace.data("com.ibm.msg.client.wmq.common.internal.messages.WMQReceiveMarshalMH", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/messages/WMQReceiveMarshalMH.java");
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
/*     */   WMQReceiveMarshalMH() {
/*  75 */     if (Trace.isOn) {
/*  76 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQReceiveMarshalMH", "<init>()");
/*     */     }
/*     */ 
/*     */     
/*  80 */     if (Trace.isOn) {
/*  81 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQReceiveMarshalMH", "<init>()");
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
/*     */   protected int constructProviderMessageFromProperties(int messageBodyStyle) throws JMSException {
/*  98 */     return constructProviderMessageFromZRFP(messageBodyStyle);
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
/*     */   private int constructProviderMessageFromZRFP(int messageBodyStyle) throws JMSException {
/* 110 */     if (Trace.isOn) {
/* 111 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQReceiveMarshalMH", "constructProviderMessageFromZRFP(int)", new Object[] {
/* 112 */             Integer.valueOf(messageBodyStyle)
/*     */           });
/*     */     }
/* 115 */     int pos = 0;
/*     */ 
/*     */     
/*     */     try {
/* 119 */       JmqiSystemEnvironment sysenv = (JmqiSystemEnvironment)this.env;
/* 120 */       WMQThreadLocalStorage tls = this.owner.getThreadLocalStorage();
/* 121 */       JmqiTls jTls = sysenv.getJmqiTls((JmqiComponentTls)tls);
/* 122 */       JmqiDC dc = sysenv.getDC();
/*     */ 
/*     */       
/* 125 */       int strucID = dc.readI32(this.buffer, pos, false);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 130 */       ReceiveZRFP receiveZRFP = null;
/* 131 */       if (strucID == 1515341392) {
/*     */ 
/*     */         
/* 134 */         if (tls.receiveZRFP == null) {
/* 135 */           receiveZRFP = new ReceiveZRFP(this.env, jTls);
/* 136 */           tls.receiveZRFP = receiveZRFP;
/*     */         } else {
/* 138 */           receiveZRFP = tls.receiveZRFP;
/*     */         } 
/*     */         
/* 141 */         pos = receiveZRFP.readFromBuffer(this.buffer, pos);
/*     */         
/* 143 */         this.hasMessageProperties = true;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 150 */       int encoding = this.mqmd.getEncoding();
/* 151 */       int ccsid = this.mqmd.getCodedCharSetId();
/* 152 */       String format = this.mqmd.getFormat();
/*     */       
/* 154 */       if (format.startsWith("MQH")) {
/* 155 */         int previousCcsid = this.owner.getHconn().getCcsid();
/* 156 */         int ptrSize = 4;
/* 157 */         while (format.startsWith("MQH")) {
/* 158 */           JmqiCodepage cp; MQHeader header = this.env.newMQHeader();
/*     */ 
/*     */           
/* 161 */           if (ccsid == -2) {
/* 162 */             ccsid = previousCcsid;
/*     */           }
/*     */ 
/*     */           
/* 166 */           header.setFormat(format);
/*     */ 
/*     */           
/* 169 */           boolean swap = ((encoding & 0xF) == 2);
/*     */           
/*     */           try {
/* 172 */             cp = JmqiCodepage.getJmqiCodepage(this.env, ccsid);
/*     */             
/* 174 */             if (cp == null)
/*     */             {
/* 176 */               UnsupportedEncodingException traceRet1 = new UnsupportedEncodingException(String.valueOf(ccsid));
/* 177 */               if (Trace.isOn) {
/* 178 */                 Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQReceiveMarshalMH", "constructProviderMessageFromZRFP(int)", traceRet1, 1);
/*     */               }
/*     */ 
/*     */               
/* 182 */               throw traceRet1;
/*     */             }
/*     */           
/*     */           }
/* 186 */           catch (UnsupportedEncodingException e) {
/* 187 */             if (Trace.isOn) {
/* 188 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQReceiveMarshalMH", "constructProviderMessageFromZRFP(int)", e, 1);
/*     */             }
/*     */ 
/*     */             
/* 192 */             HashMap<String, Object> inserts = new HashMap<>();
/* 193 */             inserts.put("XMSC_INSERT_PROPERTY", "JMS_IBM_Character_Set");
/* 194 */             inserts.put("XMSC_INSERT_VALUE", Integer.valueOf(ccsid));
/* 195 */             JMSException je = (JMSException)NLSServices.createException("JMSCMQ1006", inserts);
/* 196 */             if (Trace.isOn) {
/* 197 */               Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQReceiveMarshalMH", "constructProviderMessageFromZRFP(int)", (Throwable)je, 2);
/*     */             }
/*     */ 
/*     */             
/* 201 */             throw je;
/*     */           } 
/*     */ 
/*     */           
/* 205 */           header.readFromBuffer(this.buffer, pos, ptrSize, swap, cp, jTls, false);
/*     */ 
/*     */           
/* 208 */           pos += header.getStrucLength();
/* 209 */           encoding = header.getEncoding();
/* 210 */           previousCcsid = ccsid;
/* 211 */           ccsid = header.getCodedCharSetId();
/* 212 */           format = header.getFormat();
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 217 */       this.encodingFromLastHeaderBeforeBody = encoding;
/* 218 */       this.ccsidFromLastHeaderBeforeBody = ccsid;
/* 219 */       this.formatFromLastHeaderBeforeBody = format;
/*     */       
/* 221 */       if (Trace.isOn) {
/* 222 */         Trace.data(this, "com.ibm.msg.client.wmq.internal.messages.WMQReceiveMarshalMH", "constructProviderMessageFromZRFP(int)", "All of the headers have been processed");
/* 223 */         Trace.data(this, "com.ibm.msg.client.wmq.internal.messages.WMQReceiveMarshalMH", "constructProviderMessageFromZRFP(int)", "Format of last header in message: " + this.formatFromLastHeaderBeforeBody);
/*     */         
/* 225 */         Trace.data(this, "com.ibm.msg.client.wmq.internal.messages.WMQReceiveMarshalMH", "constructProviderMessageFromZRFP(int)", "CCSID of message body: " + this.ccsidFromLastHeaderBeforeBody);
/*     */         
/* 227 */         Trace.data(this, "com.ibm.msg.client.wmq.internal.messages.WMQReceiveMarshalMH", "constructProviderMessageFromZRFP(int)", "Encoding of message body: " + this.formatFromLastHeaderBeforeBody);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 232 */       String fbClass = format.equals("MQSTR   ") ? "jms_text" : "jms_bytes";
/*     */ 
/*     */       
/* 235 */       String forcedMessageClass = (messageBodyStyle == 1) ? "jms_bytes" : null;
/*     */ 
/*     */       
/* 238 */       Triplet mcdTriplet = null;
/* 239 */       Triplet jmsTriplet = null;
/* 240 */       Triplet triplet1 = null;
/* 241 */       Triplet mqextTriplet = null;
/* 242 */       Triplet mqpsTriplet = null;
/*     */       
/* 244 */       if (receiveZRFP != null) {
/* 245 */         Triplet[] triplets = receiveZRFP.getTriplets();
/*     */         
/* 247 */         for (int i = 0; i < triplets.length; i++) {
/* 248 */           Triplet triplet = triplets[i];
/* 249 */           if (triplet != null) {
/* 250 */             String label = triplet.getLabel();
/* 251 */             char index = label.charAt(0);
/* 252 */             switch (index) {
/*     */               case 'm':
/* 254 */                 if ("mcd".equals(label)) {
/* 255 */                   mcdTriplet = triplet; break;
/* 256 */                 }  if ("mqps".equals(label)) {
/* 257 */                   mqpsTriplet = triplet; break;
/* 258 */                 }  if ("mqext".equals(label)) {
/* 259 */                   mqextTriplet = triplet;
/*     */                 }
/*     */                 break;
/*     */               case 'j':
/* 263 */                 if ("jms".equals(label)) {
/* 264 */                   jmsTriplet = triplet;
/*     */                 }
/*     */                 break;
/*     */               case 'u':
/* 268 */                 if ("usr".equals(label)) {
/* 269 */                   triplet1 = triplet;
/*     */                 }
/*     */                 break;
/*     */             } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           } 
/*     */         } 
/*     */       } 
/* 281 */       if (mcdTriplet != null) {
/* 282 */         this.providerMessage = WMQMessage.setTripletMcd(mcdTriplet, fbClass, forcedMessageClass);
/*     */       
/*     */       }
/* 285 */       else if (forcedMessageClass == null) {
/* 286 */         if (pos >= getSafeBufferEnd()) {
/*     */           
/* 288 */           this.providerMessage = new WMQNullMessage();
/* 289 */           this.providerMessage.isNullMessage = true;
/* 290 */         } else if (format.equals("MQSTR   ")) {
/*     */           
/* 292 */           this.providerMessage = new WMQTextMessage();
/* 293 */           this.providerMessage.isNullMessage = false;
/*     */         } else {
/*     */           
/* 296 */           this.providerMessage = new WMQBytesMessage();
/*     */         }
/*     */       
/* 299 */       } else if (forcedMessageClass.equals("jms_bytes")) {
/* 300 */         this.providerMessage = new WMQBytesMessage();
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 307 */       if (jmsTriplet != null) {
/* 308 */         boolean persistenceFromMD = WMQMarshalUtils.getPersistenceFromMD(this.owner);
/* 309 */         this.providerMessage.setTripletJms(jmsTriplet, persistenceFromMD);
/*     */       } else {
/* 311 */         this.providerMessage.setJMSDeliveryMode(-3);
/*     */       } 
/*     */ 
/*     */       
/* 315 */       if (mqpsTriplet != null) {
/* 316 */         this.providerMessage.setTripletMqps(mqpsTriplet);
/*     */       }
/*     */ 
/*     */       
/* 320 */       if (mqextTriplet != null) {
/* 321 */         this.providerMessage.setTripletMqext(mqextTriplet);
/*     */       }
/*     */ 
/*     */       
/* 325 */       if (triplet1 != null) {
/* 326 */         this.providerMessage.setTripletUsr(triplet1);
/*     */       }
/*     */     }
/* 329 */     catch (JmqiException e) {
/* 330 */       if (Trace.isOn) {
/* 331 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQReceiveMarshalMH", "constructProviderMessageFromZRFP(int)", (Throwable)e, 2);
/*     */       }
/*     */ 
/*     */       
/* 335 */       HashMap<String, Object> inserts = new HashMap<>();
/* 336 */       inserts.put("XMSC_MESSAGE_ID", this.providerMessage.getJMSMessageID());
/* 337 */       inserts.put("XMSC_CORRELATION_ID", this.providerMessage.getJMSCorrelationID());
/*     */       
/* 339 */       JMSException je = (JMSException)NLSServices.createException("JMSCMQ1000", null);
/* 340 */       je.setLinkedException((Exception)e);
/* 341 */       if (Trace.isOn) {
/* 342 */         Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQReceiveMarshalMH", "constructProviderMessageFromZRFP(int)", (Throwable)je, 3);
/*     */       }
/*     */ 
/*     */       
/* 346 */       throw je;
/*     */     } 
/*     */ 
/*     */     
/* 350 */     if (Trace.isOn) {
/* 351 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQReceiveMarshalMH", "constructProviderMessageFromZRFP(int)", 
/* 352 */           Integer.valueOf(pos));
/*     */     }
/* 354 */     return pos;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\common\internal\messages\WMQReceiveMarshalMH.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */