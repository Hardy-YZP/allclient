/*     */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*     */ 
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.wmq.compat.base.internal.MQMessage;
/*     */ import com.ibm.msg.client.wmq.compat.base.internal.MQMsg2;
/*     */ import com.ibm.msg.client.wmq.compat.base.internal.MQSESSION;
/*     */ import java.io.IOException;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.nio.charset.CharacterCodingException;
/*     */ import java.util.StringTokenizer;
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
/*     */ class RFH
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/RFH.java";
/*     */   private static final String MQRFH_STRUC_ID = "RFH ";
/*     */   private static final int MQRFH_ASCII_STRUC_ID_INT = 1380337696;
/*     */   private static final int MQRFH_EBCDIC_STRUC_ID_INT = -641284032;
/*     */   private static final int MQRFH_VERSION_1 = 1;
/*     */   private static final int MQRFH_STRUC_LENGTH_FIXED = 32;
/*     */   private String strucId;
/*     */   private int version;
/*     */   private int strucLength;
/*     */   private int encoding;
/*     */   private int codedCharSetId;
/*     */   private String format;
/*     */   private int flags;
/*     */   private String nameValueString;
/*     */   private int msgCharSet;
/*     */   private int msgEncoding;
/*     */   
/*     */   static {
/*  50 */     if (Trace.isOn) {
/*  51 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.RFH", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/RFH.java");
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
/*     */   RFH() {
/*  90 */     if (Trace.isOn) {
/*  91 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "<init>()");
/*     */     }
/*  93 */     this.strucId = "RFH ";
/*  94 */     this.version = 1;
/*  95 */     this.strucLength = 32;
/*  96 */     this.encoding = 273;
/*  97 */     this.codedCharSetId = 0;
/*  98 */     this.format = "        ";
/*  99 */     this.flags = 0;
/* 100 */     this.nameValueString = null;
/* 101 */     if (Trace.isOn) {
/* 102 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "<init>()");
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
/*     */   RFH(MQMsg2 message) throws JMSException {
/* 125 */     if (Trace.isOn) {
/* 126 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "<init>(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", new Object[] { message });
/*     */     }
/*     */ 
/*     */     
/* 130 */     if (message == null) {
/* 131 */       JMSException je = ConfigEnvironment.newException("MQJMS1074");
/* 132 */       if (Trace.isOn) {
/* 133 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "<init>(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", (Throwable)je, 1);
/*     */       }
/*     */       
/* 136 */       throw je;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 141 */     this.msgCharSet = message.getCharacterSet();
/* 142 */     this.msgEncoding = message.getEncoding();
/*     */     
/*     */     try {
/*     */       Exception traceRet1;
/*     */       
/* 147 */       int strucIdAsInt = message.readInt(1);
/* 148 */       switch (strucIdAsInt) {
/*     */         case 1380337696:
/* 150 */           this.msgCharSet = 819;
/* 151 */           this.strucId = "RFH ";
/*     */           break;
/*     */         case -641284032:
/* 154 */           this.msgCharSet = 500;
/* 155 */           this.strucId = "RFH ";
/*     */           break;
/*     */         default:
/* 158 */           traceRet1 = new Exception("strucId");
/* 159 */           if (Trace.isOn) {
/* 160 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "<init>(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", traceRet1, 2);
/*     */           }
/*     */           
/* 163 */           throw traceRet1;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 168 */       this.version = message.readInt(this.msgEncoding);
/* 169 */       if (this.version != 1) {
/* 170 */         Exception traceRet2 = new Exception("version");
/* 171 */         if (Trace.isOn) {
/* 172 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "<init>(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", traceRet2, 3);
/*     */         }
/*     */         
/* 175 */         throw traceRet2;
/*     */       } 
/*     */ 
/*     */       
/* 179 */       this.strucLength = message.readInt(this.msgEncoding);
/*     */ 
/*     */       
/* 182 */       this.encoding = message.readInt(this.msgEncoding);
/*     */ 
/*     */       
/* 185 */       this.codedCharSetId = message.readInt(this.msgEncoding);
/*     */ 
/*     */       
/* 188 */       byte[] tmp = new byte[8];
/* 189 */       message.read(tmp);
/* 190 */       this.format = getString(tmp);
/*     */ 
/*     */       
/* 193 */       this.flags = message.readInt(this.msgEncoding);
/*     */ 
/*     */       
/* 196 */       int remainingBytes = this.strucLength - 32;
/* 197 */       tmp = new byte[remainingBytes];
/* 198 */       message.read(tmp);
/* 199 */       this.nameValueString = getString(tmp);
/*     */ 
/*     */       
/* 202 */       if (Trace.isOn) {
/* 203 */         Trace.traceData(this, "The following fields have been read:\n" + toString(), null);
/*     */       
/*     */       }
/*     */     }
/* 207 */     catch (Exception e) {
/* 208 */       if (Trace.isOn) {
/* 209 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "<init>(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", e);
/*     */       }
/*     */       
/* 212 */       JMSException je = ConfigEnvironment.newException("MQJMS1086");
/* 213 */       je.setLinkedException(e);
/*     */       
/* 215 */       if (Trace.isOn) {
/* 216 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "<init>(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", (Throwable)je, 4);
/*     */       }
/*     */       
/* 219 */       throw je;
/*     */     } 
/* 221 */     if (Trace.isOn) {
/* 222 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "<init>(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)");
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
/*     */   String getNameValueString() {
/* 235 */     if (Trace.isOn) {
/* 236 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "getNameValueString()", "getter", this.nameValueString);
/*     */     }
/*     */     
/* 239 */     return this.nameValueString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setNameValueString(String nameValueString) {
/* 250 */     if (Trace.isOn) {
/* 251 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "setNameValueString(String)", "setter", nameValueString);
/*     */     }
/*     */     
/* 254 */     this.nameValueString = nameValueString;
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
/*     */   String getValue(String name) {
/* 269 */     if (Trace.isOn) {
/* 270 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "getValue(String)", new Object[] { name });
/*     */     }
/*     */     
/* 273 */     if (Trace.isOn) {
/* 274 */       Trace.traceData(this, "nameValueString='" + this.nameValueString + "'", null);
/*     */     }
/*     */     
/* 277 */     StringTokenizer tok = new StringTokenizer(this.nameValueString);
/*     */     
/* 279 */     while (tok.hasMoreTokens()) {
/* 280 */       String thisName = tok.nextToken();
/* 281 */       String value = tok.nextToken();
/*     */       
/* 283 */       if (thisName.equals(name)) {
/* 284 */         if (Trace.isOn) {
/* 285 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "getValue(String)", value, 1);
/*     */         }
/*     */         
/* 288 */         return value;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 293 */     if (Trace.isOn) {
/* 294 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "getValue(String)", null, 2);
/*     */     }
/*     */     
/* 297 */     return null;
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
/*     */   void write(MQMessage message) throws JMSException {
/* 310 */     if (Trace.isOn) {
/* 311 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "write(com.ibm.msg.client.wmq.compat.base.internal.MQMessage)", new Object[] { message });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 317 */       this.encoding = message.encoding;
/* 318 */       this.codedCharSetId = message.characterSet;
/* 319 */       this.format = message.format;
/*     */ 
/*     */       
/* 322 */       message.encoding = 273;
/* 323 */       message.characterSet = 0;
/* 324 */       message.format = "MQHRF   ";
/*     */ 
/*     */       
/* 327 */       int bodyLen = message.getMessageLength();
/* 328 */       byte[] bodyBuffer = new byte[bodyLen];
/* 329 */       message.seek(0);
/* 330 */       message.readFully(bodyBuffer);
/*     */ 
/*     */       
/* 333 */       message.seek(0);
/* 334 */       writeRFHFields(message);
/*     */ 
/*     */       
/* 337 */       message.write(bodyBuffer);
/*     */     
/*     */     }
/* 340 */     catch (Exception e) {
/* 341 */       if (Trace.isOn) {
/* 342 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "write(com.ibm.msg.client.wmq.compat.base.internal.MQMessage)", e);
/*     */       }
/*     */       
/* 345 */       JMSException je = ConfigEnvironment.newException("MQJMS1085");
/* 346 */       je.setLinkedException(e);
/* 347 */       if (Trace.isOn) {
/* 348 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "write(com.ibm.msg.client.wmq.compat.base.internal.MQMessage)", (Throwable)je);
/*     */       }
/*     */       
/* 351 */       throw je;
/*     */     } 
/*     */     
/* 354 */     if (Trace.isOn) {
/* 355 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "write(com.ibm.msg.client.wmq.compat.base.internal.MQMessage)");
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
/*     */   private void writeRFHFields(MQMessage m) throws IOException {
/* 370 */     if (Trace.isOn) {
/* 371 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "writeRFHFields(com.ibm.msg.client.wmq.compat.base.internal.MQMessage)", new Object[] { m });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 380 */     if (this.nameValueString != null) {
/* 381 */       while (this.nameValueString.length() % 4 != 0) {
/* 382 */         this.nameValueString += " ";
/*     */       }
/* 384 */       this.strucLength = 32 + this.nameValueString.length();
/*     */     } 
/*     */ 
/*     */     
/* 388 */     m.writeString(this.strucId);
/* 389 */     m.writeInt(this.version);
/* 390 */     m.writeInt(this.strucLength);
/* 391 */     m.writeInt(this.encoding);
/* 392 */     m.writeInt(this.codedCharSetId);
/* 393 */     m.writeString(this.format);
/* 394 */     m.writeInt(this.flags);
/* 395 */     if (this.nameValueString != null) {
/* 396 */       m.writeString(this.nameValueString);
/*     */     }
/* 398 */     if (Trace.isOn) {
/* 399 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "writeRFHFields(com.ibm.msg.client.wmq.compat.base.internal.MQMessage)");
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
/*     */   public void write(MQMsg2 message) throws JMSException {
/* 417 */     if (Trace.isOn) {
/* 418 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "write(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", new Object[] { message });
/*     */     }
/*     */     
/*     */     try {
/* 422 */       if (this.nameValueString != null) {
/* 423 */         int l = this.nameValueString.length();
/* 424 */         while (l % 4 != 0) {
/* 425 */           this.nameValueString += " ";
/* 426 */           l++;
/*     */         } 
/* 428 */         this.strucLength = 32 + this.nameValueString.length();
/*     */       } else {
/* 430 */         this.strucLength = 32;
/*     */       } 
/*     */       
/* 433 */       this.msgEncoding = message.getEncoding();
/* 434 */       this.msgCharSet = message.getCharacterSet();
/*     */       
/* 436 */       message.appendByteArray(getByteArray(this.strucId));
/* 437 */       message.appendInt(this.version, this.msgEncoding);
/* 438 */       message.appendInt(this.strucLength, this.msgEncoding);
/* 439 */       message.appendInt(this.encoding, this.msgEncoding);
/* 440 */       message.appendInt(this.codedCharSetId, this.msgEncoding);
/* 441 */       message.appendByteArray(getByteArray(this.format));
/* 442 */       message.appendInt(this.flags, this.msgEncoding);
/* 443 */       if (this.nameValueString != null) {
/* 444 */         message.appendByteArray(getByteArray(this.nameValueString));
/*     */       }
/*     */     }
/* 447 */     catch (Exception e) {
/* 448 */       if (Trace.isOn) {
/* 449 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "write(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", e);
/*     */       }
/*     */       
/* 452 */       JMSException je = ConfigEnvironment.newException("MQJMS1085");
/* 453 */       je.setLinkedException(e);
/* 454 */       if (Trace.isOn) {
/* 455 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "write(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", (Throwable)je);
/*     */       }
/*     */       
/* 458 */       throw je;
/*     */     } 
/* 460 */     if (Trace.isOn) {
/* 461 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "write(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)");
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
/*     */   private String getString(byte[] barray) throws UnsupportedEncodingException {
/* 473 */     if (Trace.isOn) {
/* 474 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "getString(byte [ ])", new Object[] { barray });
/*     */     }
/*     */     
/*     */     try {
/* 478 */       JmqiCodepage codepage = JmqiCodepage.getJmqiCodepage(MQSESSION.getJmqiEnv(), this.msgCharSet, this.msgEncoding);
/*     */       
/* 480 */       String traceRet1 = codepage.bytesToString(barray);
/* 481 */       if (Trace.isOn) {
/* 482 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "getString(byte [ ])", traceRet1);
/*     */       }
/*     */       
/* 485 */       if (Trace.isOn) {
/* 486 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "getString(byte [ ])", traceRet1);
/*     */       }
/*     */       
/* 489 */       return traceRet1;
/*     */     
/*     */     }
/* 492 */     catch (CharacterCodingException je) {
/* 493 */       if (Trace.isOn) {
/* 494 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "getString(byte [ ])", je);
/*     */       }
/*     */ 
/*     */       
/* 498 */       if (Trace.isOn) {
/* 499 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "getString(byte [ ])", je, 1);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 504 */       UnsupportedEncodingException uee = new UnsupportedEncodingException(je.getMessage());
/* 505 */       if (Trace.isOn) {
/* 506 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "getString(byte [ ])", uee, 1);
/*     */       }
/*     */       
/* 509 */       if (Trace.isOn) {
/* 510 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "getString(byte [ ])", uee);
/*     */       }
/*     */       
/* 513 */       throw uee;
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
/*     */   private byte[] getByteArray(String str) throws UnsupportedEncodingException {
/* 526 */     if (Trace.isOn) {
/* 527 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "getByteArray(String)", new Object[] { str });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 532 */       JmqiCodepage codepage = JmqiCodepage.getJmqiCodepage(MQSESSION.getJmqiEnv(), this.msgCharSet, this.msgEncoding);
/*     */       
/* 534 */       byte[] traceRet1 = codepage.stringToBytes(str);
/* 535 */       if (Trace.isOn) {
/* 536 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "getByteArray(String)", traceRet1);
/*     */       }
/*     */       
/* 539 */       if (Trace.isOn) {
/* 540 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "getByteArray(String)", traceRet1);
/*     */       }
/*     */       
/* 543 */       return traceRet1;
/*     */     
/*     */     }
/* 546 */     catch (CharacterCodingException je) {
/* 547 */       if (Trace.isOn) {
/* 548 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "getByteArray(String)", je);
/*     */       }
/*     */ 
/*     */       
/* 552 */       if (Trace.isOn) {
/* 553 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "getByteArray(String)", je, 1);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 558 */       UnsupportedEncodingException uee = new UnsupportedEncodingException(je.getMessage());
/* 559 */       if (Trace.isOn) {
/* 560 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "getByteArray(String))", uee, 1);
/*     */       }
/*     */       
/* 563 */       if (Trace.isOn) {
/* 564 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "getByteArray(String)", uee);
/*     */       }
/*     */       
/* 567 */       throw uee;
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
/*     */   public String toString() {
/* 580 */     if (Trace.isOn) {
/* 581 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "toString()");
/*     */     }
/* 583 */     StringBuffer buf = new StringBuffer();
/*     */     
/* 585 */     buf.append("StrucId='" + this.strucId + "'\n");
/* 586 */     buf.append("Version=" + this.version + "\n");
/* 587 */     buf.append("StrucLength=" + this.strucLength + "\n");
/* 588 */     buf.append("Encoding=" + this.encoding + "\n");
/* 589 */     buf.append("CodedCharSetId=" + this.codedCharSetId + "\n");
/* 590 */     buf.append("Format='" + this.format + "'\n");
/* 591 */     buf.append("Flags=" + this.flags + "\n");
/* 592 */     if (this.nameValueString != null) {
/* 593 */       buf.append("NameValueString='" + this.nameValueString + "'");
/*     */     }
/*     */     
/* 596 */     String traceRet1 = buf.toString();
/* 597 */     if (Trace.isOn) {
/* 598 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "toString()", traceRet1);
/*     */     }
/* 600 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEncoding(int encoding) {
/* 608 */     if (Trace.isOn) {
/* 609 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "setEncoding(int)", "setter", 
/* 610 */           Integer.valueOf(encoding));
/*     */     }
/* 612 */     this.encoding = encoding;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getEncoding() {
/* 617 */     if (Trace.isOn) {
/* 618 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "getEncoding()", "getter", 
/* 619 */           Integer.valueOf(this.encoding));
/*     */     }
/* 621 */     return this.encoding;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCodedCharSetId(int codedCharSetId) {
/* 626 */     if (Trace.isOn) {
/* 627 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "setCodedCharSetId(int)", "setter", 
/* 628 */           Integer.valueOf(codedCharSetId));
/*     */     }
/* 630 */     this.codedCharSetId = codedCharSetId;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCodedCharSetId() {
/* 635 */     if (Trace.isOn) {
/* 636 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "getCodedCharSetId()", "getter", 
/* 637 */           Integer.valueOf(this.codedCharSetId));
/*     */     }
/* 639 */     return this.codedCharSetId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFormat(String format) {
/* 644 */     if (Trace.isOn) {
/* 645 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "setFormat(String)", "setter", format);
/*     */     }
/*     */     
/* 648 */     this.format = format;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFormat() {
/* 653 */     if (Trace.isOn) {
/* 654 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH", "getFormat()", "getter", this.format);
/*     */     }
/*     */     
/* 657 */     return this.format;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\RFH.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */