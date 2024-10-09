/*     */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*     */ 
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.provider.ProviderTextMessage;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.nio.charset.CharacterCodingException;
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
/*     */ public class JMSTextMessage
/*     */   extends JMSMessage
/*     */   implements ProviderTextMessage
/*     */ {
/*     */   static final long serialVersionUID = -7013263043146565366L;
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/JMSTextMessage.java";
/*     */   
/*     */   static {
/*  47 */     if (Trace.isOn) {
/*  48 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.JMSTextMessage", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/JMSTextMessage.java");
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
/*  70 */   String messageText = null;
/*     */ 
/*     */ 
/*     */   
/*  74 */   byte[] messageBytes = null;
/*     */   
/*     */   transient JmqiCodepage codepage;
/*     */   
/*     */   int dataStart;
/*     */   
/*  80 */   char[] HPCodepage = null;
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
/*     */   public JMSTextMessage(JMSStringResources jmsStrings) {
/*  94 */     if (Trace.isOn) {
/*  95 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSTextMessage", "<init>(JMSStringResources)", new Object[] { jmsStrings });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 100 */     this.messageClass = "jms_text";
/*     */ 
/*     */     
/* 103 */     this.jmsStrings = jmsStrings;
/*     */     
/* 105 */     if (Trace.isOn) {
/* 106 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSTextMessage", "<init>(JMSStringResources)");
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
/*     */   public JMSTextMessage(JMSStringResources jmsStrings, String string) throws JMSException {
/* 120 */     this(jmsStrings);
/* 121 */     if (Trace.isOn) {
/* 122 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSTextMessage", "<init>(JMSStringResources,String)", new Object[] { jmsStrings, string });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 127 */     this.messageClass = "jms_text";
/*     */     
/* 129 */     setText(string);
/*     */     
/* 131 */     if (Trace.isOn) {
/* 132 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSTextMessage", "<init>(JMSStringResources,String)");
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
/* 149 */     if (Trace.isOn) {
/* 150 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSTextMessage", "_exportBody(int,JmqiCodepage)", new Object[] {
/* 151 */             Integer.valueOf(encoding), codepage
/*     */           });
/*     */     }
/*     */     
/*     */     try {
/* 156 */       if (this.messageBytes == null)
/*     */       {
/*     */         
/* 159 */         if (this.messageText == null) {
/*     */           
/* 161 */           if (Trace.isOn) {
/* 162 */             Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSTextMessage", "_exportBody(int,JmqiCodepage)", null, 1);
/*     */           }
/*     */           
/* 165 */           return null;
/*     */         } 
/*     */         
/* 168 */         this.messageBytes = codepage.stringToBytes(this.messageText);
/* 169 */         this.codepage = codepage;
/* 170 */         this.dataStart = 0;
/*     */       
/*     */       }
/*     */       else
/*     */       {
/* 175 */         if (this.codepage.equals(codepage)) {
/*     */ 
/*     */           
/* 178 */           if (this.dataStart != 0) {
/*     */             
/* 180 */             byte[] tempBytes = new byte[this.messageBytes.length - this.dataStart];
/* 181 */             System.arraycopy(this.messageBytes, this.dataStart, tempBytes, 0, this.messageBytes.length - this.dataStart);
/* 182 */             this.messageBytes = tempBytes;
/* 183 */             this.dataStart = 0;
/* 184 */             if (Trace.isOn) {
/* 185 */               Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSTextMessage", "_exportBody(int,JmqiCodepage)", this.messageBytes, 2);
/*     */             }
/*     */             
/* 188 */             return this.messageBytes;
/*     */           } 
/*     */           
/* 191 */           if (Trace.isOn) {
/* 192 */             Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSTextMessage", "_exportBody(int,JmqiCodepage)", this.messageBytes, 3);
/*     */           }
/*     */           
/* 195 */           return this.messageBytes;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 203 */         this.messageText = this.codepage.bytesToString(this.messageBytes, this.dataStart, this.messageBytes.length - this.dataStart);
/*     */ 
/*     */         
/* 206 */         this.messageBytes = codepage.stringToBytes(this.messageText);
/* 207 */         this.codepage = codepage;
/* 208 */         this.dataStart = 0;
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 213 */     catch (CharacterCodingException ex) {
/* 214 */       if (Trace.isOn) {
/* 215 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSTextMessage", "_exportBody(int,JmqiCodepage)", ex);
/*     */       }
/*     */       
/* 218 */       JMSException jmsEx = newJMSException(1008, codepage);
/* 219 */       jmsEx.setLinkedException(ex);
/* 220 */       if (Trace.isOn) {
/* 221 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSTextMessage", "_exportBody(int,JmqiCodepage)", (Throwable)jmsEx);
/*     */       }
/*     */       
/* 224 */       throw jmsEx;
/*     */     } 
/*     */     
/* 227 */     if (Trace.isOn) {
/* 228 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSTextMessage", "_exportBody(int,JmqiCodepage)", this.messageBytes, 4);
/*     */     }
/*     */     
/* 231 */     return this.messageBytes;
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
/* 246 */     if (Trace.isOn) {
/* 247 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSTextMessage", "_importBody(byte [ ],int,int,JmqiCodepage)", new Object[] { wireformat, 
/*     */             
/* 249 */             Integer.valueOf(dataStart), Integer.valueOf(encoding), codepage });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 254 */     this.messageBytes = wireformat;
/* 255 */     this.messageText = null;
/* 256 */     this.dataStart = dataStart;
/* 257 */     this.codepage = codepage;
/* 258 */     if (Trace.isOn) {
/* 259 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSTextMessage", "_importBody(byte [ ],int,int,JmqiCodepage)");
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
/*     */   public void clearBody() throws JMSException {
/* 273 */     if (Trace.isOn) {
/* 274 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSTextMessage", "clearBody()");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 279 */     this.readOnly = false;
/*     */ 
/*     */     
/* 282 */     this.messageText = null;
/* 283 */     this.messageBytes = null;
/*     */ 
/*     */     
/* 286 */     this.isNullMessage = true;
/*     */     
/* 288 */     if (Trace.isOn) {
/* 289 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSTextMessage", "clearBody()");
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
/* 305 */     if (Trace.isOn) {
/* 306 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSTextMessage", "getText()");
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 311 */       if (this.messageText == null && this.messageBytes != null)
/*     */       {
/* 313 */         this.messageText = this.codepage.bytesToString(this.messageBytes, this.dataStart, this.messageBytes.length - this.dataStart);
/*     */         
/* 315 */         this.messageBytes = null;
/*     */       }
/*     */     
/* 318 */     } catch (CharacterCodingException ex) {
/* 319 */       if (Trace.isOn) {
/* 320 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSTextMessage", "getText()", ex);
/*     */       }
/*     */       
/* 323 */       JMSException jmsEx = newMessageFormatException(1008, this.codepage);
/* 324 */       jmsEx.setLinkedException(ex);
/* 325 */       if (Trace.isOn) {
/* 326 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSTextMessage", "getText()", (Throwable)jmsEx);
/*     */       }
/*     */       
/* 329 */       throw jmsEx;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 335 */     if (this.isNullMessage == true && this.messageText != null && this.messageText.length() == 0) {
/* 336 */       this.messageText = null;
/*     */     }
/* 338 */     else if (!this.isNullMessage && this.messageText == null) {
/* 339 */       this.messageText = "";
/*     */     } 
/*     */     
/* 342 */     if (Trace.isOn) {
/* 343 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSTextMessage", "getText()", this.messageText);
/*     */     }
/*     */     
/* 346 */     return this.messageText;
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
/*     */   public void setText(String messageText) throws JMSException {
/* 359 */     if (Trace.isOn) {
/* 360 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSTextMessage", "setText(String)", "setter", messageText);
/*     */     }
/*     */ 
/*     */     
/* 364 */     if (this.readOnly == true) {
/* 365 */       JMSException traceRet1 = newMessageNotWriteableException();
/* 366 */       if (Trace.isOn) {
/* 367 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSTextMessage", "setText(String)", (Throwable)traceRet1);
/*     */       }
/*     */       
/* 370 */       throw traceRet1;
/*     */     } 
/*     */     
/* 373 */     if (messageText == null) {
/* 374 */       this.isNullMessage = true;
/*     */     } else {
/*     */       
/* 377 */       this.isNullMessage = false;
/*     */     } 
/* 379 */     this.messageText = messageText;
/* 380 */     this.messageBytes = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 391 */     if (Trace.isOn) {
/* 392 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSTextMessage", "toString()");
/*     */     }
/*     */ 
/*     */     
/* 396 */     StringBuffer retval = new StringBuffer();
/*     */ 
/*     */ 
/*     */     
/* 400 */     retval.append(super.toString());
/* 401 */     retval.append("\n");
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 406 */       if (this.messageText == null) {
/* 407 */         getText();
/*     */       }
/*     */     }
/* 410 */     catch (JMSException ex) {
/* 411 */       if (Trace.isOn) {
/* 412 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSTextMessage", "toString()", (Throwable)ex);
/*     */       }
/*     */       
/* 415 */       retval.append(this.jmsStrings.getMessage(1025, ex));
/* 416 */       retval.append(">");
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 421 */     if (this.messageText == null) {
/* 422 */       retval.append("<null>");
/*     */     }
/* 424 */     else if (this.messageText.length() > 100) {
/* 425 */       retval.append(this.messageText.substring(0, 100));
/* 426 */       retval.append("\n" + this.jmsStrings.getMessage(1026, Integer.valueOf(this.messageText.length() - 100)));
/*     */     } else {
/*     */       
/* 429 */       retval.append(this.messageText);
/*     */     } 
/*     */     
/* 432 */     String traceRet1 = retval.toString();
/* 433 */     if (Trace.isOn) {
/* 434 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSTextMessage", "toString()", traceRet1);
/*     */     }
/*     */     
/* 437 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void _setBodyReadOnly() {
/* 446 */     if (Trace.isOn) {
/* 447 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSTextMessage", "_setBodyReadOnly()");
/*     */     }
/*     */     
/* 450 */     this.readOnly = true;
/* 451 */     if (Trace.isOn) {
/* 452 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSTextMessage", "_setBodyReadOnly()");
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
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 472 */     if (Trace.isOn) {
/* 473 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSTextMessage", "readObject(java.io.ObjectInputStream)", new Object[] { in });
/*     */     }
/*     */     
/* 476 */     in.defaultReadObject();
/*     */ 
/*     */     
/* 479 */     if (this.messageClass.equals("jms_text")) {
/* 480 */       this.messageClass = "jms_text";
/*     */     }
/* 482 */     if (Trace.isOn) {
/* 483 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSTextMessage", "readObject(java.io.ObjectInputStream)");
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
/*     */   public String unicodeFrom1051(byte[] messageBytes, int dataStart) {
/* 499 */     if (Trace.isOn) {
/* 500 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSTextMessage", "unicodeFrom1051(byte [ ],int)", new Object[] { messageBytes, 
/* 501 */             Integer.valueOf(dataStart) });
/*     */     }
/*     */ 
/*     */     
/* 505 */     int dataLength = messageBytes.length - dataStart;
/*     */ 
/*     */     
/* 508 */     char[] output = new char[dataLength];
/*     */ 
/*     */     
/* 511 */     if (this.HPCodepage == null) {
/* 512 */       this.HPCodepage = get1051codepage();
/*     */     }
/*     */ 
/*     */     
/* 516 */     for (int i = dataStart, offs = 0; offs < dataLength; i++, offs++) {
/*     */       
/* 518 */       int b = (messageBytes[i] + 256) % 256;
/* 519 */       output[offs] = this.HPCodepage[b];
/*     */     } 
/*     */     
/* 522 */     String traceRet1 = new String(output);
/* 523 */     if (Trace.isOn) {
/* 524 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSTextMessage", "unicodeFrom1051(byte [ ],int)", traceRet1);
/*     */     }
/*     */     
/* 527 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private char[] get1051codepage() {
/* 538 */     char[] cp = new char[256];
/*     */ 
/*     */     
/* 541 */     char[] CP1051_to_chars = { 'À', 'Â', 'È', 'Ê', 'Ë', 'Î', 'Ï', 'ˊ', 'ˋ', 'ˆ', '¨', '˜', 'Ù', 'Û', '₤', '¯', 'Ý', 'ý', '°', 'Ç', 'ç', 'Ñ', 'ñ', '¡', '¿', '¤', '£', '¥', '§', 'ƒ', '¢', 'â', 'ê', 'ô', 'û', 'á', 'é', 'ó', 'ú', 'à', 'è', 'ò', 'ù', 'ä', 'ë', 'ö', 'ü', 'Å', 'î', 'Ø', 'Æ', 'å', 'í', 'ø', 'æ', 'Ä', 'ì', 'Ö', 'Ü', 'É', 'ï', 'ß', 'Ô', 'Á', 'Ã', 'ã', 'Ð', 'ð', 'Í', 'Ì', 'Ó', 'Ò', 'Õ', 'õ', 'Š', 'š', 'Ú', 'Ÿ', 'ÿ', 'Þ', 'þ', '·', 'µ', '¶', '¾', '­', '¼', '½', 'ª', 'º', '«', '■', '»', '±', '\032' };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     int i;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 641 */     for (i = 0; i < 161; i++) {
/* 642 */       cp[i] = (char)i;
/*     */     }
/*     */ 
/*     */     
/* 646 */     for (i = 161; i <= 255; i++) {
/* 647 */       cp[i] = CP1051_to_chars[i - 161];
/*     */     }
/*     */ 
/*     */     
/* 651 */     if (Trace.isOn) {
/* 652 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSTextMessage", "get1051codepage()", "getter", cp);
/*     */     }
/*     */     
/* 655 */     return cp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasBody() {
/* 664 */     if (Trace.isOn) {
/* 665 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSTextMessage", "hasBody()");
/*     */     }
/* 667 */     if (Trace.isOn) {
/* 668 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSTextMessage", "hasBody()", 
/* 669 */           Boolean.valueOf(false));
/*     */     }
/* 671 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getJMSDeliveryTime() throws JMSException {
/* 680 */     if (Trace.isOn) {
/* 681 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSTextMessage", "getJMSDeliveryTime()", "getter", 
/* 682 */           Long.valueOf(0L));
/*     */     }
/* 684 */     return 0L;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setJMSDeliveryTime(long deliveryTime) throws JMSException {
/* 692 */     if (Trace.isOn)
/* 693 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSTextMessage", "setJMSDeliveryTime(long)", "setter", 
/* 694 */           Long.valueOf(deliveryTime)); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\JMSTextMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */