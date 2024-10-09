/*     */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*     */ 
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.wmq.compat.base.internal.MQMessage;
/*     */ import com.ibm.msg.client.wmq.compat.base.internal.MQMsg2;
/*     */ import com.ibm.msg.client.wmq.compat.base.internal.MQSESSION;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.nio.charset.CharacterCodingException;
/*     */ import java.util.GregorianCalendar;
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
/*     */ class DLH
/*     */ {
/*     */   private static final String MQDLH_STRUC_ID = "DLH ";
/*     */   private static final int MQDLH_VERSION_1 = 1;
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/DLH.java";
/*     */   private int codedCharSetId;
/*     */   String destQMgrName;
/*     */   String destQName;
/*     */   private int encoding;
/*     */   private String format;
/*     */   private int msgCharSet;
/*     */   private int msgEncoding;
/*     */   String putApplName;
/*     */   int putApplType;
/*     */   String putDate;
/*     */   String putTime;
/*     */   int reason;
/*     */   private String strucId;
/*     */   private int version;
/*     */   
/*     */   static {
/*  59 */     if (Trace.isOn) {
/*  60 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.DLH", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/DLH.java");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   DLH() {
/* 105 */     if (Trace.isOn) {
/* 106 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.DLH", "<init>()");
/*     */     }
/* 108 */     this.strucId = "DLH ";
/* 109 */     this.version = 1;
/* 110 */     this.reason = 0;
/* 111 */     this.destQName = Utils.padString(null, 48);
/* 112 */     this.destQMgrName = Utils.padString(null, 48);
/* 113 */     this.encoding = 273;
/* 114 */     this.codedCharSetId = 0;
/* 115 */     this.format = "        ";
/* 116 */     this.putApplType = 0;
/* 117 */     this.putApplName = Utils.padString(null, 28);
/* 118 */     this.putDate = Utils.padString(null, 8);
/* 119 */     this.putTime = Utils.padString(null, 8);
/* 120 */     if (Trace.isOn) {
/* 121 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.DLH", "<init>()");
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
/*     */   DLH(MQMessage message) throws JMSException {
/* 142 */     if (Trace.isOn) {
/* 143 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.DLH", "<init>(com.ibm.msg.client.wmq.compat.base.internal.MQMessage)", new Object[] { message });
/*     */     }
/*     */ 
/*     */     
/* 147 */     if (message == null) {
/* 148 */       JMSException je = ConfigEnvironment.newException("MQJMS1074");
/* 149 */       if (Trace.isOn) {
/* 150 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.DLH", "<init>(com.ibm.msg.client.wmq.compat.base.internal.MQMessage)", (Throwable)je, 1);
/*     */       }
/*     */       
/* 153 */       throw je;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 158 */     this.msgCharSet = message.characterSet;
/* 159 */     this.msgEncoding = message.encoding;
/*     */ 
/*     */     
/*     */     try {
/* 163 */       byte[] tmp = new byte[4];
/* 164 */       message.readFully(tmp, 0, 4);
/* 165 */       this.strucId = getString(tmp);
/* 166 */       if (!this.strucId.equals("DLH ")) {
/* 167 */         Exception traceRet1 = new Exception("strucId");
/* 168 */         if (Trace.isOn) {
/* 169 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.DLH", "<init>(com.ibm.msg.client.wmq.compat.base.internal.MQMessage)", traceRet1, 2);
/*     */         }
/*     */         
/* 172 */         throw traceRet1;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 177 */       this.version = message.readInt();
/* 178 */       if (this.version != 1) {
/* 179 */         Exception traceRet2 = new Exception("version");
/* 180 */         if (Trace.isOn) {
/* 181 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.DLH", "<init>(com.ibm.msg.client.wmq.compat.base.internal.MQMessage)", traceRet2, 3);
/*     */         }
/*     */         
/* 184 */         throw traceRet2;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 189 */       this.reason = message.readInt();
/*     */ 
/*     */       
/* 192 */       tmp = new byte[48];
/* 193 */       message.readFully(tmp, 0, 48);
/* 194 */       this.destQName = getString(tmp);
/*     */ 
/*     */       
/* 197 */       message.readFully(tmp, 0, 48);
/* 198 */       this.destQMgrName = getString(tmp);
/*     */ 
/*     */       
/* 201 */       this.encoding = message.readInt();
/*     */ 
/*     */       
/* 204 */       this.codedCharSetId = message.readInt();
/*     */ 
/*     */       
/* 207 */       tmp = new byte[8];
/* 208 */       message.readFully(tmp, 0, 8);
/* 209 */       this.format = getString(tmp);
/*     */ 
/*     */       
/* 212 */       this.putApplType = message.readInt();
/*     */ 
/*     */       
/* 215 */       tmp = new byte[28];
/* 216 */       message.readFully(tmp, 0, 28);
/* 217 */       this.putApplName = getString(tmp);
/*     */ 
/*     */       
/* 220 */       tmp = new byte[8];
/* 221 */       message.readFully(tmp, 0, 8);
/* 222 */       this.putDate = getString(tmp);
/*     */ 
/*     */       
/* 225 */       message.readFully(tmp, 0, 8);
/* 226 */       this.putTime = getString(tmp);
/*     */     
/*     */     }
/* 229 */     catch (Exception e) {
/* 230 */       if (Trace.isOn) {
/* 231 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.DLH", "<init>(com.ibm.msg.client.wmq.compat.base.internal.MQMessage)", e);
/*     */       }
/*     */       
/* 234 */       JMSException je = ConfigEnvironment.newException("MQJMS1076");
/* 235 */       je.setLinkedException(e);
/* 236 */       if (Trace.isOn) {
/* 237 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.DLH", "<init>(com.ibm.msg.client.wmq.compat.base.internal.MQMessage)", (Throwable)je, 4);
/*     */       }
/*     */       
/* 240 */       throw je;
/*     */     } 
/*     */     
/* 243 */     if (Trace.isOn) {
/* 244 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.DLH", "<init>(com.ibm.msg.client.wmq.compat.base.internal.MQMessage)");
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
/*     */   private String getString(byte[] barray) throws UnsupportedEncodingException {
/* 262 */     if (Trace.isOn) {
/* 263 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.DLH", "getString(byte [ ])", new Object[] { barray });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 268 */       JmqiCodepage codepage = JmqiCodepage.getJmqiCodepage(MQSESSION.getJmqiEnv(), this.msgCharSet, this.msgEncoding);
/*     */       
/* 270 */       String traceRet1 = codepage.bytesToString(barray);
/*     */       
/* 272 */       if (Trace.isOn) {
/* 273 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.DLH", "getString(byte [ ])", traceRet1);
/*     */       }
/*     */ 
/*     */       
/* 277 */       if (Trace.isOn) {
/* 278 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.DLH", "getString(byte [ ])", traceRet1);
/*     */       }
/*     */       
/* 281 */       return traceRet1;
/*     */     
/*     */     }
/* 284 */     catch (CharacterCodingException je) {
/* 285 */       if (Trace.isOn) {
/* 286 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.DLH", "getString(byte [ ])", je);
/*     */       }
/*     */ 
/*     */       
/* 290 */       if (Trace.isOn) {
/* 291 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.DLH", "getString(byte [ ])", je, 1);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 296 */       UnsupportedEncodingException uee = new UnsupportedEncodingException(je.getMessage());
/* 297 */       if (Trace.isOn) {
/* 298 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.DLH", "getString(byte [ ])", uee, 1);
/*     */       }
/*     */       
/* 301 */       if (Trace.isOn) {
/* 302 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.DLH", "getString(byte [ ])", uee);
/*     */       }
/*     */       
/* 305 */       throw uee;
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
/*     */   public String toString() {
/* 320 */     if (Trace.isOn) {
/* 321 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.DLH", "toString()");
/*     */     }
/* 323 */     StringBuffer buf = new StringBuffer();
/*     */     
/* 325 */     buf.append("StrucId='" + this.strucId + "'\n");
/* 326 */     buf.append("Version=" + this.version + "\n");
/* 327 */     buf.append("Reason=" + this.reason + "\n");
/* 328 */     buf.append("DestQName='" + this.destQName + "'\n");
/* 329 */     buf.append("DestQMgrName='" + this.destQMgrName + "'\n");
/* 330 */     buf.append("Encoding=" + this.encoding + "\n");
/* 331 */     buf.append("CodedCharSetId=" + this.codedCharSetId + "\n");
/* 332 */     buf.append("Format='" + this.format + "'\n");
/* 333 */     buf.append("PutApplType=" + this.putApplType + "\n");
/* 334 */     buf.append("PutApplName='" + this.putApplName + "'\n");
/* 335 */     buf.append("PutDate='" + this.putDate + "'\n");
/* 336 */     buf.append("PutTime='" + this.putTime + "'");
/*     */     
/* 338 */     String traceRet1 = buf.toString();
/* 339 */     if (Trace.isOn) {
/* 340 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.DLH", "toString()", traceRet1);
/*     */     }
/* 342 */     return traceRet1;
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
/*     */   void write(MQMessage message) throws JMSException {
/* 357 */     if (Trace.isOn) {
/* 358 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.DLH", "write(com.ibm.msg.client.wmq.compat.base.internal.MQMessage)", new Object[] { message });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 364 */       this.encoding = message.encoding;
/* 365 */       this.codedCharSetId = message.characterSet;
/* 366 */       this.format = message.format;
/*     */ 
/*     */       
/* 369 */       message.encoding = 273;
/* 370 */       message.characterSet = 0;
/* 371 */       message.format = "MQDEAD  ";
/*     */ 
/*     */       
/* 374 */       int bodyLen = message.getMessageLength();
/* 375 */       byte[] bodyBuffer = new byte[bodyLen];
/* 376 */       message.seek(0);
/* 377 */       message.readFully(bodyBuffer);
/*     */ 
/*     */       
/* 380 */       message.seek(0);
/* 381 */       writeDLHFields(message);
/*     */ 
/*     */       
/* 384 */       message.write(bodyBuffer);
/*     */     
/*     */     }
/* 387 */     catch (Exception e) {
/* 388 */       if (Trace.isOn) {
/* 389 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.DLH", "write(com.ibm.msg.client.wmq.compat.base.internal.MQMessage)", e);
/*     */       }
/*     */       
/* 392 */       JMSException je = ConfigEnvironment.newException("MQJMS1075");
/* 393 */       je.setLinkedException(e);
/* 394 */       if (Trace.isOn) {
/* 395 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.DLH", "write(com.ibm.msg.client.wmq.compat.base.internal.MQMessage)", (Throwable)je);
/*     */       }
/*     */       
/* 398 */       throw je;
/*     */     } 
/*     */ 
/*     */     
/* 402 */     if (Trace.isOn) {
/* 403 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.DLH", "write(com.ibm.msg.client.wmq.compat.base.internal.MQMessage)");
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
/*     */   protected void write(MQMessage deadMsg, MQMsg2 msg2) throws JMSException {
/* 423 */     if (Trace.isOn) {
/* 424 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.DLH", "write(com.ibm.msg.client.wmq.compat.base.internal.MQMessage,com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", new Object[] { deadMsg, msg2 });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 431 */     ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
/* 432 */     DataOutputStream dataBuffer = new DataOutputStream(byteBuffer);
/* 433 */     GregorianCalendar putDateCal = new GregorianCalendar();
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 438 */       this.encoding = msg2.getEncoding();
/* 439 */       this.codedCharSetId = msg2.getCharacterSet();
/* 440 */       this.format = msg2.getFormat();
/*     */ 
/*     */       
/* 443 */       deadMsg.encoding = 273;
/* 444 */       deadMsg.characterSet = 0;
/* 445 */       deadMsg.format = "MQDEAD  ";
/*     */ 
/*     */       
/* 448 */       deadMsg.accountingToken = msg2.getAccountingToken();
/* 449 */       deadMsg.applicationIdData = msg2.getApplicationIdData();
/* 450 */       deadMsg.applicationOriginData = msg2.getApplicationOriginData();
/* 451 */       deadMsg.backoutCount = msg2.getBackoutCount();
/* 452 */       deadMsg.correlationId = msg2.getCorrelationId();
/* 453 */       deadMsg.expiry = msg2.getExpiry();
/* 454 */       deadMsg.feedback = msg2.getFeedback();
/* 455 */       deadMsg.groupId = msg2.getGroupId();
/* 456 */       deadMsg.messageFlags = msg2.getMessageFlags();
/* 457 */       deadMsg.messageId = msg2.getMessageId();
/* 458 */       deadMsg.messageSequenceNumber = msg2.getMessageSequenceNumber();
/* 459 */       deadMsg.messageType = msg2.getMessageType();
/* 460 */       deadMsg.persistence = msg2.getPersistence();
/* 461 */       deadMsg.priority = msg2.getPriority();
/* 462 */       deadMsg.putApplicationName = msg2.getPutApplicationName();
/* 463 */       deadMsg.putApplicationType = msg2.getPutApplicationType();
/* 464 */       putDateCal.setTimeInMillis(msg2.getPutTimeMillis());
/* 465 */       deadMsg.putDateTime = putDateCal;
/* 466 */       deadMsg.replyToQueueManagerName = msg2.getReplyToQueueManagerName();
/* 467 */       deadMsg.replyToQueueName = msg2.getReplyToQueueName();
/* 468 */       deadMsg.report = msg2.getReport();
/* 469 */       deadMsg.userId = msg2.getUserId();
/*     */ 
/*     */       
/* 472 */       byte[] bodyBuffer = null;
/* 473 */       bodyBuffer = msg2.getMessageData();
/*     */ 
/*     */       
/* 476 */       writeDLHFields(deadMsg);
/*     */ 
/*     */       
/* 479 */       dataBuffer.write(bodyBuffer);
/* 480 */       deadMsg.write(byteBuffer.toByteArray());
/*     */     
/*     */     }
/* 483 */     catch (Exception e) {
/* 484 */       if (Trace.isOn) {
/* 485 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.DLH", "write(com.ibm.msg.client.wmq.compat.base.internal.MQMessage,com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", e);
/*     */       }
/*     */ 
/*     */       
/* 489 */       JMSException je = ConfigEnvironment.newException("MQJMS1075");
/* 490 */       je.setLinkedException(e);
/* 491 */       if (Trace.isOn) {
/* 492 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.DLH", "write(com.ibm.msg.client.wmq.compat.base.internal.MQMessage,com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", (Throwable)je);
/*     */       }
/*     */ 
/*     */       
/* 496 */       throw je;
/*     */     } 
/*     */ 
/*     */     
/* 500 */     if (Trace.isOn) {
/* 501 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.DLH", "write(com.ibm.msg.client.wmq.compat.base.internal.MQMessage,com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)");
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
/*     */   private void writeDLHFields(MQMessage m) throws IOException {
/* 520 */     if (Trace.isOn) {
/* 521 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.DLH", "writeDLHFields(com.ibm.msg.client.wmq.compat.base.internal.MQMessage)", new Object[] { m });
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 541 */     m.writeString(this.strucId);
/* 542 */     m.writeInt(this.version);
/* 543 */     m.writeInt(this.reason);
/* 544 */     m.writeString(Utils.padString(this.destQName, 48));
/* 545 */     m.writeString(Utils.padString(this.destQMgrName, 48));
/* 546 */     m.writeInt(this.encoding);
/* 547 */     m.writeInt(this.codedCharSetId);
/* 548 */     m.writeString(this.format);
/* 549 */     m.writeInt(this.putApplType);
/* 550 */     m.writeString(Utils.padString(this.putApplName, 28));
/* 551 */     m.writeString(Utils.padString(this.putDate, 8));
/* 552 */     m.writeString(Utils.padString(this.putTime, 8));
/* 553 */     if (Trace.isOn)
/* 554 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.DLH", "writeDLHFields(com.ibm.msg.client.wmq.compat.base.internal.MQMessage)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\DLH.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */