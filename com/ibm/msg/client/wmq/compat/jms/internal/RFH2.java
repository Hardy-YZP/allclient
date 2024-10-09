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
/*     */ import java.util.Vector;
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
/*     */ public class RFH2
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/RFH2.java";
/*     */   private static final String MQRFH_STRUC_ID = "RFH ";
/*     */   private static final int MQRFH_ASCII_STRUC_ID_INT = 1380337696;
/*     */   private static final int MQRFH_EBCDIC_STRUC_ID_INT = -641284032;
/*     */   private static final int MQRFH_VERSION_2 = 2;
/*     */   private static final int MQRFH_STRUC_LENGTH_FIXED_2 = 36;
/*     */   
/*     */   static {
/*  50 */     if (Trace.isOn) {
/*  51 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/RFH2.java");
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
/*  69 */   private String strucId = "RFH ";
/*  70 */   private int version = 2;
/*  71 */   private int strucLength = 36;
/*  72 */   private int encoding = 273;
/*  73 */   private int codedCharSetId = -2;
/*  74 */   private String format = "MQSTR   ";
/*  75 */   private int flags = 0;
/*  76 */   private int nameValueCCSID = 1208;
/*  77 */   private Vector<RFH2Folder> folders = null;
/*     */   private String nameValueString;
/*  79 */   private int nameValueLength = 0;
/*     */ 
/*     */   
/*     */   private int msgCharSet;
/*     */   
/*     */   private int msgEncoding;
/*     */   
/*  86 */   private static final byte[] SPACES = new byte[] { 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32 };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RFH2() {
/*  92 */     if (Trace.isOn) {
/*  93 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "<init>()");
/*     */     }
/*  95 */     this.folders = new Vector<>();
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "<init>()");
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
/*     */   public RFH2(MQMsg2 message) throws JMSException {
/* 120 */     if (Trace.isOn) {
/* 121 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "<init>(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", new Object[] { message });
/*     */     }
/*     */     
/* 124 */     this.folders = new Vector<>();
/*     */     
/* 126 */     if (message == null) {
/* 127 */       JMSException je = ConfigEnvironment.newException("MQJMS1074");
/* 128 */       if (Trace.isOn) {
/* 129 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "<init>(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", (Throwable)je, 1);
/*     */       }
/*     */       
/* 132 */       throw je;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 138 */     this.msgCharSet = message.getCharacterSet();
/* 139 */     this.msgEncoding = message.getEncoding();
/*     */     
/*     */     try {
/*     */       Exception traceRet1;
/* 143 */       int strucIdAsInt = message.readInt(1);
/* 144 */       switch (strucIdAsInt) {
/*     */         case 1380337696:
/* 146 */           this.msgCharSet = 819;
/* 147 */           this.strucId = "RFH ";
/*     */           break;
/*     */         case -641284032:
/* 150 */           this.msgCharSet = 500;
/* 151 */           this.strucId = "RFH ";
/*     */           break;
/*     */         default:
/* 154 */           traceRet1 = new Exception("strucId");
/* 155 */           if (Trace.isOn) {
/* 156 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "<init>(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", traceRet1, 2);
/*     */           }
/*     */           
/* 159 */           throw traceRet1;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 164 */       this.version = message.readInt(this.msgEncoding);
/* 165 */       if (this.version != 2) {
/* 166 */         Exception traceRet2 = new Exception("version");
/* 167 */         if (Trace.isOn) {
/* 168 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "<init>(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", traceRet2, 3);
/*     */         }
/*     */         
/* 171 */         throw traceRet2;
/*     */       } 
/*     */ 
/*     */       
/* 175 */       this.strucLength = message.readInt(this.msgEncoding);
/*     */ 
/*     */       
/* 178 */       this.encoding = message.readInt(this.msgEncoding);
/*     */ 
/*     */       
/* 181 */       this.codedCharSetId = message.readInt(this.msgEncoding);
/*     */ 
/*     */       
/* 184 */       byte[] tmp = new byte[8];
/* 185 */       message.read(tmp);
/* 186 */       this.format = getString(tmp);
/*     */ 
/*     */       
/* 189 */       this.flags = message.readInt(this.msgEncoding);
/*     */ 
/*     */       
/* 192 */       this.nameValueCCSID = message.readInt(this.msgEncoding);
/*     */ 
/*     */ 
/*     */       
/* 196 */       if (this.strucLength > 36) {
/*     */ 
/*     */ 
/*     */         
/* 200 */         int remainingBytes = this.strucLength - 40;
/*     */ 
/*     */         
/* 203 */         message.skipReadingBytes(4);
/* 204 */         tmp = new byte[remainingBytes];
/* 205 */         message.read(tmp);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         try {
/* 212 */           JmqiCodepage jmqiCodepage = JmqiCodepage.getJmqiCodepage(MQSESSION.getJmqiEnv(), this.nameValueCCSID);
/* 213 */           this.nameValueString = jmqiCodepage.bytesToString(tmp);
/*     */         }
/* 215 */         catch (CharacterCodingException cce) {
/* 216 */           if (Trace.isOn) {
/* 217 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "<init>(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", cce, 1);
/*     */           }
/*     */           
/* 220 */           if (Trace.isOn) {
/* 221 */             Trace.traceData(this, "RFH2, NameValue CCSID " + this.nameValueCCSID + " not valid, using 819", null);
/*     */           }
/*     */           try {
/* 224 */             JmqiCodepage jmqiCodepage = JmqiCodepage.getJmqiCodepage(MQSESSION.getJmqiEnv(), 819);
/* 225 */             this.nameValueString = jmqiCodepage.bytesToString(tmp);
/*     */           }
/* 227 */           catch (CharacterCodingException cce1) {
/* 228 */             if (Trace.isOn) {
/* 229 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "<init>(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", cce1, 2);
/*     */             }
/*     */             
/* 232 */             this.nameValueString = "";
/*     */           } 
/*     */         } 
/*     */       } else {
/*     */         
/* 237 */         this.nameValueString = "";
/*     */       } 
/*     */       
/* 240 */       if (Trace.isOn) {
/* 241 */         Trace.traceData(this, "The following fields have been read:\n" + toString(), null);
/*     */       
/*     */       }
/*     */     }
/* 245 */     catch (Exception e) {
/* 246 */       if (Trace.isOn) {
/* 247 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "<init>(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", e, 3);
/*     */       }
/*     */       
/* 250 */       JMSException je = ConfigEnvironment.newException("MQJMS1086");
/* 251 */       je.setLinkedException(e);
/*     */       
/* 253 */       if (Trace.isOn) {
/* 254 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "<init>(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", (Throwable)je, 4);
/*     */       }
/*     */       
/* 257 */       throw je;
/*     */     } 
/* 259 */     if (Trace.isOn) {
/* 260 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "<init>(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)");
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
/*     */   public void addFolder(RFH2Folder folder) {
/* 273 */     if (Trace.isOn) {
/* 274 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "addFolder(RFH2Folder)", new Object[] { folder });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 279 */     this.folders.addElement(folder);
/* 280 */     this.strucLength += 4;
/*     */     
/* 282 */     if (Trace.isOn) {
/* 283 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "addFolder(RFH2Folder)");
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
/*     */   private byte[] getByteArray(String str) throws UnsupportedEncodingException {
/* 298 */     if (Trace.isOn) {
/* 299 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "getByteArray(String)", new Object[] { str });
/*     */     }
/*     */     
/*     */     try {
/* 303 */       JmqiCodepage codepage = JmqiCodepage.getJmqiCodepage(MQSESSION.getJmqiEnv(), this.msgCharSet, this.msgEncoding);
/* 304 */       byte[] traceRet1 = codepage.stringToBytes(str);
/* 305 */       if (Trace.isOn) {
/* 306 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "getByteArray(String)", traceRet1);
/*     */       }
/*     */       
/* 309 */       if (Trace.isOn) {
/* 310 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "getByteArray(String)", traceRet1);
/*     */       }
/*     */       
/* 313 */       return traceRet1;
/*     */     
/*     */     }
/* 316 */     catch (CharacterCodingException cce) {
/* 317 */       if (Trace.isOn) {
/* 318 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "getByteArray(String)", cce);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 323 */       UnsupportedEncodingException uee = new UnsupportedEncodingException(cce.getMessage());
/* 324 */       if (Trace.isOn) {
/* 325 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "getByteArray(String)", uee);
/*     */       }
/*     */       
/* 328 */       throw uee;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCodedCharSetId() {
/* 339 */     if (Trace.isOn) {
/* 340 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "getCodedCharSetId()", "getter", 
/* 341 */           Integer.valueOf(this.codedCharSetId));
/*     */     }
/* 343 */     return this.codedCharSetId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEncoding() {
/* 353 */     if (Trace.isOn) {
/* 354 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "getEncoding()", "getter", 
/* 355 */           Integer.valueOf(this.encoding));
/*     */     }
/* 357 */     return this.encoding;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFormat() {
/* 367 */     if (Trace.isOn) {
/* 368 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "getFormat()", "getter", this.format);
/*     */     }
/*     */     
/* 371 */     return this.format;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMsgEncoding() {
/* 381 */     if (Trace.isOn) {
/* 382 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "getMsgEncoding()", "getter", 
/* 383 */           Integer.valueOf(this.msgEncoding));
/*     */     }
/* 385 */     return this.msgEncoding;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNameValueCCSID() {
/* 395 */     if (Trace.isOn) {
/* 396 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "getNameValueCCSID()", "getter", 
/* 397 */           Integer.valueOf(this.nameValueCCSID));
/*     */     }
/* 399 */     return this.nameValueCCSID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNameValueLength() {
/* 409 */     if (Trace.isOn) {
/* 410 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "getNameValueLength()", "getter", 
/* 411 */           Integer.valueOf(this.nameValueLength));
/*     */     }
/* 413 */     return this.nameValueLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNameValueString() {
/* 423 */     if (Trace.isOn) {
/* 424 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "getNameValueString()", "getter", this.nameValueString);
/*     */     }
/*     */     
/* 427 */     return this.nameValueString;
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
/*     */   private String getString(byte[] barray) throws UnsupportedEncodingException {
/* 439 */     if (Trace.isOn) {
/* 440 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "getString(byte [ ])", new Object[] { barray });
/*     */     }
/*     */     
/*     */     try {
/* 444 */       JmqiCodepage codepage = JmqiCodepage.getJmqiCodepage(MQSESSION.getJmqiEnv(), this.msgCharSet, this.msgEncoding);
/* 445 */       String traceRet1 = codepage.bytesToString(barray);
/* 446 */       if (Trace.isOn) {
/* 447 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "getString(byte [ ])", traceRet1);
/*     */       }
/*     */       
/* 450 */       if (Trace.isOn) {
/* 451 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "getString(byte [ ])", traceRet1);
/*     */       }
/*     */       
/* 454 */       return traceRet1;
/*     */     
/*     */     }
/* 457 */     catch (CharacterCodingException cce) {
/* 458 */       if (Trace.isOn) {
/* 459 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "getString(byte [ ])", cce);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 464 */       UnsupportedEncodingException uee = new UnsupportedEncodingException(cce.getMessage());
/*     */       
/* 466 */       if (Trace.isOn) {
/* 467 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "getString(byte [ ])", uee);
/*     */       }
/*     */       
/* 470 */       throw uee;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStrucId() {
/* 481 */     if (Trace.isOn) {
/* 482 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "getStrucId()", "getter", this.strucId);
/*     */     }
/*     */     
/* 485 */     return this.strucId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStrucLength() {
/* 495 */     if (Trace.isOn) {
/* 496 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "getStrucLength()", "getter", 
/* 497 */           Integer.valueOf(this.strucLength));
/*     */     }
/* 499 */     return this.strucLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCodedCharSetId(int newCodedCharSetId) {
/* 509 */     if (Trace.isOn) {
/* 510 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "setCodedCharSetId(int)", "setter", 
/* 511 */           Integer.valueOf(newCodedCharSetId));
/*     */     }
/* 513 */     this.codedCharSetId = newCodedCharSetId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEncoding(int newEncoding) {
/* 523 */     if (Trace.isOn) {
/* 524 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "setEncoding(int)", "setter", 
/* 525 */           Integer.valueOf(newEncoding));
/*     */     }
/* 527 */     this.encoding = newEncoding;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFormat(String newFormat) {
/* 537 */     if (Trace.isOn) {
/* 538 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "setFormat(java.lang.String)", "setter", newFormat);
/*     */     }
/*     */     
/* 541 */     this.format = newFormat;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMsgEncoding(int newMsgEncoding) {
/* 551 */     if (Trace.isOn) {
/* 552 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "setMsgEncoding(int)", "setter", 
/* 553 */           Integer.valueOf(newMsgEncoding));
/*     */     }
/* 555 */     this.msgEncoding = newMsgEncoding;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNameValueCCSID(int newNameValueCCSID) {
/* 565 */     if (Trace.isOn) {
/* 566 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "setNameValueCCSID(int)", "setter", 
/* 567 */           Integer.valueOf(newNameValueCCSID));
/*     */     }
/* 569 */     this.nameValueCCSID = newNameValueCCSID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNameValueLength(int newNameValueLength) {
/* 579 */     if (Trace.isOn) {
/* 580 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "setNameValueLength(int)", "setter", 
/* 581 */           Integer.valueOf(newNameValueLength));
/*     */     }
/* 583 */     this.nameValueLength = newNameValueLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNameValueString(String newNameValueString) {
/* 593 */     if (Trace.isOn) {
/* 594 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "setNameValueString(java.lang.String)", "setter", newNameValueString);
/*     */     }
/*     */     
/* 597 */     this.nameValueString = newNameValueString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStrucId(String newStrucId) {
/* 607 */     if (Trace.isOn) {
/* 608 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "setStrucId(java.lang.String)", "setter", newStrucId);
/*     */     }
/*     */     
/* 611 */     this.strucId = newStrucId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStrucLength(int newStrucLength) {
/* 621 */     if (Trace.isOn) {
/* 622 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "setStrucLength(int)", "setter", 
/* 623 */           Integer.valueOf(newStrucLength));
/*     */     }
/* 625 */     this.strucLength = newStrucLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 634 */     if (Trace.isOn) {
/* 635 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "toString()");
/*     */     }
/* 637 */     StringBuffer buf = new StringBuffer();
/*     */     
/*     */     try {
/* 640 */       buf.append("Dumping fixed portion of RFH2");
/* 641 */       buf.append("\n   StrucId        = " + this.strucId);
/* 642 */       buf.append("\n   Version        = " + this.version);
/* 643 */       buf.append("\n   StrucLength    = " + this.strucLength);
/* 644 */       buf.append("\n   Encoding       = " + this.encoding);
/* 645 */       buf.append("\n   CodedCharSetId = " + this.codedCharSetId);
/* 646 */       buf.append("\n   Format         = " + this.format);
/* 647 */       buf.append("\n   Flags          = " + this.flags);
/* 648 */       buf.append("\n   NameValueCCSID = " + this.nameValueCCSID);
/*     */       
/* 650 */       for (int i = 0; i < this.folders.size(); i++) {
/* 651 */         String thisFolder = ((RFH2Folder)this.folders.elementAt(i)).toString();
/*     */         
/* 653 */         buf.append("\n\nDumping folder " + (i + 1));
/* 654 */         buf.append("\n   " + thisFolder);
/* 655 */         buf.append("\n NameValueLength " + this.nameValueLength);
/*     */       } 
/*     */       
/* 658 */       String traceRet1 = buf.toString();
/* 659 */       if (Trace.isOn) {
/* 660 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "toString()", traceRet1, 1);
/*     */       }
/*     */       
/* 663 */       return traceRet1;
/*     */     }
/* 665 */     catch (Exception e) {
/* 666 */       if (Trace.isOn) {
/* 667 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "toString()", e);
/*     */       }
/*     */       
/* 670 */       buf.append("\n\nRFH2 toString() Exception:\n" + e);
/* 671 */       String traceRet2 = buf.toString();
/* 672 */       if (Trace.isOn) {
/* 673 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "toString()", traceRet2, 2);
/*     */       }
/*     */       
/* 676 */       return traceRet2;
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
/*     */   public void write(MQMsg2 message) throws JMSException {
/* 690 */     if (Trace.isOn) {
/* 691 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "write(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", new Object[] { message });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 697 */       int nameValuePad = 0;
/* 698 */       byte[] nameValueBytes = null;
/*     */       
/* 700 */       if (this.nameValueString != null) {
/*     */ 
/*     */         
/* 703 */         nameValueBytes = this.nameValueString.getBytes("UTF8");
/* 704 */         this.nameValueLength = nameValueBytes.length;
/*     */ 
/*     */         
/* 707 */         nameValuePad = 3 - (this.nameValueLength - 1) % 4;
/* 708 */         this.nameValueLength += nameValuePad;
/*     */         
/* 710 */         if (this.nameValueLength > 0)
/*     */         {
/* 712 */           this.strucLength = this.strucLength + this.nameValueLength + 4;
/*     */         }
/*     */       } else {
/* 715 */         this.strucLength = 36;
/*     */       } 
/*     */       
/* 718 */       this.msgEncoding = message.getEncoding();
/* 719 */       this.msgCharSet = message.getCharacterSet();
/*     */       
/* 721 */       message.appendByteArray(getByteArray(this.strucId));
/* 722 */       message.appendInt(this.version, this.msgEncoding);
/* 723 */       message.appendInt(this.strucLength, this.msgEncoding);
/* 724 */       message.appendInt(this.encoding, this.msgEncoding);
/* 725 */       message.appendInt(this.codedCharSetId, this.msgEncoding);
/* 726 */       message.appendByteArray(getByteArray(this.format));
/* 727 */       message.appendInt(this.flags, this.msgEncoding);
/* 728 */       message.appendInt(this.nameValueCCSID, this.msgEncoding);
/*     */       
/* 730 */       if (this.nameValueString != null) {
/* 731 */         message.appendInt(this.nameValueLength, this.msgEncoding);
/* 732 */         message.appendByteArray(nameValueBytes);
/* 733 */         message.appendByteArray(SPACES, 0, nameValuePad);
/*     */       }
/*     */     
/*     */     }
/* 737 */     catch (Exception e) {
/* 738 */       if (Trace.isOn) {
/* 739 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "write(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", e);
/*     */       }
/*     */       
/* 742 */       JMSException je = ConfigEnvironment.newException("MQJMS1085");
/* 743 */       je.setLinkedException(e);
/* 744 */       if (Trace.isOn) {
/* 745 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "write(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", (Throwable)je);
/*     */       }
/*     */       
/* 748 */       throw je;
/*     */     } 
/* 750 */     if (Trace.isOn) {
/* 751 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "write(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)");
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
/*     */   public void writeToMessage(MQMessage message) throws Exception {
/* 770 */     if (Trace.isOn) {
/* 771 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "writeToMessage(MQMessage)", new Object[] { message });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 776 */     if (message == null) {
/* 777 */       Exception traceRet1 = new Exception("Null message passed in");
/* 778 */       if (Trace.isOn) {
/* 779 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "writeToMessage(MQMessage)", traceRet1, 1);
/*     */       }
/*     */       
/* 782 */       throw traceRet1;
/*     */     } 
/*     */ 
/*     */     
/* 786 */     message.format = "MQHRF2  ";
/*     */ 
/*     */     
/*     */     try {
/* 790 */       message.writeString(this.strucId);
/* 791 */       message.writeInt(this.version);
/* 792 */       message.writeInt(this.strucLength);
/* 793 */       message.writeInt(this.encoding);
/* 794 */       message.writeInt(this.codedCharSetId);
/* 795 */       message.writeString(this.format);
/* 796 */       message.writeInt(this.flags);
/* 797 */       message.writeInt(this.nameValueCCSID);
/*     */ 
/*     */ 
/*     */       
/* 801 */       for (RFH2Folder thisFolder : this.folders) {
/* 802 */         String rendered = thisFolder.render();
/* 803 */         message.writeInt(rendered.length());
/*     */       }
/*     */     
/*     */     }
/* 807 */     catch (IOException iox) {
/* 808 */       if (Trace.isOn) {
/* 809 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "writeToMessage(MQMessage)", iox);
/*     */       }
/*     */ 
/*     */       
/* 813 */       Exception e = new Exception("IOException in writeToMessage: " + iox);
/* 814 */       if (Trace.isOn) {
/* 815 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "writeToMessage(MQMessage)", e, 2);
/*     */       }
/*     */       
/* 818 */       throw e;
/*     */     } 
/* 820 */     if (Trace.isOn)
/* 821 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.RFH2", "writeToMessage(MQMessage)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\RFH2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */