/*     */ package com.ibm.msg.client.wmq.common.internal.messages;
/*     */ 
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.provider.ProviderStreamMessage;
/*     */ import com.ibm.msg.client.wmq.common.internal.WMQUtils;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
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
/*     */ public class WMQStreamMessage
/*     */   extends WMQMessage
/*     */   implements ProviderStreamMessage
/*     */ {
/*     */   private static final long serialVersionUID = -7838595029912202997L;
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/messages/WMQStreamMessage.java";
/*     */   private static final String STREAM_BODY_NAME = "stream";
/*     */   private static final String STREAM_ELT_NAME = "elt";
/*     */   
/*     */   static {
/*  45 */     if (Trace.isOn) {
/*  46 */       Trace.data("com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/messages/WMQStreamMessage.java");
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
/*  73 */   private ArrayList<Object> stream = new ArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  78 */   private AtomicInteger streamPos = new AtomicInteger();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WMQStreamMessage() throws JMSException {
/*  88 */     if (Trace.isOn) {
/*  89 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "<init>()");
/*     */     }
/*     */ 
/*     */     
/*  93 */     this.messageClass = "jms_stream";
/*     */     
/*  95 */     if (Trace.isOn) {
/*  96 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "<init>()");
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
/*     */   public byte[] _exportBody(int encoding, JmqiCodepage codepage) throws JMSException {
/* 112 */     if (Trace.isOn) {
/* 113 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "_exportBody(int,JmqiCodepage)", new Object[] {
/* 114 */             Integer.valueOf(encoding), codepage
/*     */           });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 120 */     StringBuilder output = new StringBuilder();
/* 121 */     byte[] bytes = null;
/*     */ 
/*     */     
/* 124 */     output.append("<");
/* 125 */     output.append("stream");
/* 126 */     output.append(">");
/*     */     
/* 128 */     Iterator<Object> iterator = this.stream.iterator();
/* 129 */     Object current = null;
/* 130 */     while (iterator.hasNext()) {
/* 131 */       current = iterator.next();
/* 132 */       WMQMessageUtils.formatElement("elt", current, output);
/*     */     } 
/*     */ 
/*     */     
/* 136 */     output.append("</");
/* 137 */     output.append("stream");
/* 138 */     output.append(">");
/*     */     
/* 140 */     bytes = WMQUtils.computeBytesFromText(output.toString(), codepage);
/*     */     
/* 142 */     if (Trace.isOn) {
/* 143 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "_exportBody(int,JmqiCodepage)", bytes);
/*     */     }
/*     */     
/* 146 */     return bytes;
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
/* 162 */     if (Trace.isOn) {
/* 163 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "_importBody(byte [ ],int,int,int,JmqiCodepage)", new Object[] { input, 
/*     */             
/* 165 */             Integer.valueOf(startIndex), Integer.valueOf(endIndex), Integer.valueOf(encoding), codepage });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 170 */     this.streamPos.set(0);
/* 171 */     this.stream.clear();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 176 */     String body = WMQUtils.computeTextFromBytes(input, startIndex, endIndex, codepage);
/*     */ 
/*     */ 
/*     */     
/* 180 */     parseStreamBody(body);
/*     */     
/* 182 */     if (Trace.isOn) {
/* 183 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "_importBody(byte [ ],int,int,int,JmqiCodepage)");
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
/*     */   public synchronized void clearBody() throws JMSException {
/* 196 */     if (Trace.isOn) {
/* 197 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "clearBody()");
/*     */     }
/*     */ 
/*     */     
/* 201 */     this.streamPos.set(0);
/* 202 */     this.stream.clear();
/*     */     
/* 204 */     if (Trace.isOn) {
/* 205 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "clearBody()");
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
/*     */   private void parseStreamBody(String streamBody) throws JMSException {
/* 218 */     if (Trace.isOn) {
/* 219 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "parseStreamBody(String)", new Object[] { streamBody });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 224 */     StringTokenizer dataToBeRead = new StringTokenizer(streamBody, "<>");
/*     */ 
/*     */     
/* 227 */     if (!dataToBeRead.nextToken().equals("stream")) {
/* 228 */       JMSException je = (JMSException)NLSServices.createException("JMSCMQ1048", null);
/* 229 */       if (Trace.isOn) {
/* 230 */         Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "parseStreamBody(String)", (Throwable)je, 1);
/*     */       }
/*     */       
/* 233 */       throw je;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 238 */     String name = dataToBeRead.nextToken();
/* 239 */     String value = null;
/* 240 */     String token = null;
/* 241 */     Object currentField = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 247 */       while (!name.equals("/stream"))
/*     */       {
/*     */         
/* 250 */         boolean shortForm = (name.charAt(name.length() - 1) == '/');
/*     */ 
/*     */         
/* 253 */         int nullIndex = name.indexOf(" xsi:nil");
/* 254 */         if (nullIndex != -1) {
/*     */           
/* 256 */           int spaceIndex = name.indexOf(" ");
/* 257 */           name = name.substring(0, spaceIndex);
/*     */ 
/*     */ 
/*     */           
/* 261 */           if (!shortForm) {
/* 262 */             token = dataToBeRead.nextToken();
/* 263 */             if (token.charAt(0) != '/')
/*     */             {
/*     */               
/* 266 */               token = dataToBeRead.nextToken();
/*     */             }
/*     */ 
/*     */             
/* 270 */             if (token.charAt(0) != '/') {
/*     */               
/* 272 */               JMSException je2 = (JMSException)NLSServices.createException("JMSCMQ1048", null);
/* 273 */               if (Trace.isOn) {
/* 274 */                 Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "parseStreamBody(String)", (Throwable)je2, 2);
/*     */               }
/*     */ 
/*     */               
/* 278 */               throw je2;
/*     */             } 
/*     */           } 
/*     */           
/* 282 */           this.stream.add(null);
/*     */ 
/*     */           
/* 285 */           name = dataToBeRead.nextToken();
/*     */ 
/*     */           
/*     */           continue;
/*     */         } 
/*     */ 
/*     */         
/* 292 */         int index = name.indexOf(" dt=");
/* 293 */         String type = "";
/* 294 */         if (index != -1) {
/* 295 */           type = name.substring(index + 4);
/* 296 */           name = name.substring(0, index);
/*     */         } else {
/*     */           
/* 299 */           type = "'string'";
/*     */         } 
/*     */         
/* 302 */         if (shortForm) {
/* 303 */           currentField = WMQMessageUtils.deformatElement(type, "");
/*     */           
/* 305 */           this.stream.add(currentField);
/*     */ 
/*     */           
/* 308 */           name = dataToBeRead.nextToken();
/*     */           
/*     */           continue;
/*     */         } 
/* 312 */         value = dataToBeRead.nextToken();
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 317 */         if (value.charAt(0) == '/') {
/* 318 */           currentField = WMQMessageUtils.deformatElement(type, "");
/*     */           
/* 320 */           this.stream.add(currentField);
/*     */         }
/*     */         else {
/*     */           
/* 324 */           token = dataToBeRead.nextToken();
/* 325 */           if (token.charAt(0) != '/') {
/* 326 */             JMSException je = (JMSException)NLSServices.createException("JMSCMQ1048", null);
/* 327 */             if (Trace.isOn) {
/* 328 */               Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "parseStreamBody(String)", (Throwable)je, 3);
/*     */             }
/*     */ 
/*     */             
/* 332 */             throw je;
/*     */           } 
/*     */ 
/*     */           
/* 336 */           currentField = WMQMessageUtils.deformatElement(type, value);
/* 337 */           this.stream.add(currentField);
/*     */         } 
/*     */         
/* 340 */         name = dataToBeRead.nextToken();
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */     
/*     */     }
/* 347 */     catch (NoSuchElementException ex) {
/* 348 */       if (Trace.isOn) {
/* 349 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "parseStreamBody(String)", ex);
/*     */       }
/*     */       
/* 352 */       JMSException je = (JMSException)NLSServices.createException("JMSCMQ0009", null);
/* 353 */       je.setLinkedException(ex);
/* 354 */       if (Trace.isOn) {
/* 355 */         Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "parseStreamBody(String)", (Throwable)je, 4);
/*     */       }
/*     */       
/* 358 */       throw je;
/*     */     } 
/*     */     
/* 361 */     if (Trace.isOn) {
/* 362 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "parseStreamBody(String)");
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
/*     */   public synchronized Object readObject() throws JMSException {
/* 375 */     if (Trace.isOn) {
/* 376 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "readObject()");
/*     */     }
/*     */ 
/*     */     
/* 380 */     if (this.streamPos.get() >= this.stream.size()) {
/* 381 */       JMSException je = (JMSException)NLSServices.createException("JMSCMQ0017", null);
/* 382 */       if (Trace.isOn) {
/* 383 */         Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "readObject()", (Throwable)je);
/*     */       }
/*     */       
/* 386 */       throw je;
/*     */     } 
/*     */     
/* 389 */     Object object = this.stream.get(this.streamPos.getAndIncrement());
/*     */     
/* 391 */     if (Trace.isOn) {
/* 392 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "readObject()", object);
/*     */     }
/*     */     
/* 395 */     return object;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void reset() throws JMSException {
/* 405 */     if (Trace.isOn) {
/* 406 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "reset()");
/*     */     }
/*     */ 
/*     */     
/* 410 */     this.streamPos.set(0);
/*     */     
/* 412 */     if (Trace.isOn) {
/* 413 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "reset()");
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
/*     */   public synchronized void stepBack() throws JMSException {
/*     */     boolean succeeded;
/* 426 */     if (Trace.isOn) {
/* 427 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "stepBack()");
/*     */     }
/*     */ 
/*     */     
/*     */     do {
/* 432 */       int currentPos = this.streamPos.get();
/* 433 */       if (currentPos <= 0) {
/*     */         break;
/*     */       }
/*     */ 
/*     */       
/* 438 */       succeeded = this.streamPos.compareAndSet(currentPos, currentPos - 1);
/* 439 */     } while (!succeeded);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 444 */     if (Trace.isOn) {
/* 445 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "stepBack()");
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
/*     */   public void writeBoolean(boolean value) throws JMSException {
/* 458 */     if (Trace.isOn) {
/* 459 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "writeBoolean(boolean)", new Object[] {
/* 460 */             Boolean.valueOf(value)
/*     */           });
/*     */     }
/* 463 */     this.stream.add(Boolean.valueOf(value));
/*     */     
/* 465 */     if (Trace.isOn) {
/* 466 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "writeBoolean(boolean)");
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
/*     */   public void writeByte(byte value) throws JMSException {
/* 479 */     if (Trace.isOn) {
/* 480 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "writeByte(byte)", new Object[] {
/* 481 */             Byte.valueOf(value)
/*     */           });
/*     */     }
/* 484 */     this.stream.add(Byte.valueOf(value));
/*     */     
/* 486 */     if (Trace.isOn) {
/* 487 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "writeByte(byte)");
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
/*     */   public void writeBytes(byte[] value) throws JMSException {
/* 500 */     if (Trace.isOn) {
/* 501 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "writeBytes(byte [ ])", new Object[] { value });
/*     */     }
/*     */ 
/*     */     
/* 505 */     this.stream.add(value);
/*     */     
/* 507 */     if (Trace.isOn) {
/* 508 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "writeBytes(byte [ ])");
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
/*     */   public void writeBytes(byte[] value, int offset, int length) throws JMSException {
/* 521 */     if (Trace.isOn) {
/* 522 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "writeBytes(byte [ ],int,int)", new Object[] { value, 
/* 523 */             Integer.valueOf(offset), 
/* 524 */             Integer.valueOf(length) });
/*     */     }
/*     */     
/* 527 */     byte[] bytes = new byte[length];
/* 528 */     System.arraycopy(value, offset, bytes, 0, length);
/* 529 */     this.stream.add(bytes);
/*     */     
/* 531 */     if (Trace.isOn) {
/* 532 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "writeBytes(byte [ ],int,int)");
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
/*     */   public void writeChar(char value) throws JMSException {
/* 545 */     if (Trace.isOn) {
/* 546 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "writeChar(char)", new Object[] {
/* 547 */             Character.valueOf(value)
/*     */           });
/*     */     }
/* 550 */     this.stream.add(Character.valueOf(value));
/*     */     
/* 552 */     if (Trace.isOn) {
/* 553 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "writeChar(char)");
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
/*     */   public void writeDouble(double value) throws JMSException {
/* 566 */     if (Trace.isOn) {
/* 567 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "writeDouble(double)", new Object[] {
/* 568 */             Double.valueOf(value)
/*     */           });
/*     */     }
/* 571 */     this.stream.add(Double.valueOf(value));
/*     */     
/* 573 */     if (Trace.isOn) {
/* 574 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "writeDouble(double)");
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
/*     */   public void writeFloat(float value) throws JMSException {
/* 587 */     if (Trace.isOn) {
/* 588 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "writeFloat(float)", new Object[] {
/* 589 */             Float.valueOf(value)
/*     */           });
/*     */     }
/* 592 */     this.stream.add(Float.valueOf(value));
/*     */     
/* 594 */     if (Trace.isOn) {
/* 595 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "writeFloat(float)");
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
/*     */   public void writeInt(int value) throws JMSException {
/* 608 */     if (Trace.isOn) {
/* 609 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "writeInt(int)", new Object[] {
/* 610 */             Integer.valueOf(value)
/*     */           });
/*     */     }
/* 613 */     this.stream.add(Integer.valueOf(value));
/*     */     
/* 615 */     if (Trace.isOn) {
/* 616 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "writeInt(int)");
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
/*     */   public void writeLong(long value) throws JMSException {
/* 629 */     if (Trace.isOn) {
/* 630 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "writeLong(long)", new Object[] {
/* 631 */             Long.valueOf(value)
/*     */           });
/*     */     }
/* 634 */     this.stream.add(Long.valueOf(value));
/*     */     
/* 636 */     if (Trace.isOn) {
/* 637 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "writeLong(long)");
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
/*     */   public void writeObject(Object value) throws JMSException {
/* 650 */     if (Trace.isOn) {
/* 651 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "writeObject(Object)", new Object[] { value });
/*     */     }
/*     */ 
/*     */     
/* 655 */     this.stream.add(value);
/*     */     
/* 657 */     if (Trace.isOn) {
/* 658 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "writeObject(Object)");
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
/*     */   public void writeShort(short value) throws JMSException {
/* 671 */     if (Trace.isOn) {
/* 672 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "writeShort(short)", new Object[] {
/* 673 */             Short.valueOf(value)
/*     */           });
/*     */     }
/* 676 */     this.stream.add(Short.valueOf(value));
/*     */     
/* 678 */     if (Trace.isOn) {
/* 679 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "writeShort(short)");
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
/*     */   public void writeString(String value) throws JMSException {
/* 692 */     if (Trace.isOn) {
/* 693 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "writeString(String)", new Object[] { value });
/*     */     }
/*     */ 
/*     */     
/* 697 */     this.stream.add(value);
/*     */     
/* 699 */     if (Trace.isOn) {
/* 700 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "writeString(String)");
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
/*     */   public ArrayList<Object> getStreamData() throws JMSException {
/* 712 */     if (Trace.isOn) {
/* 713 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "getStreamData()", "getter", this.stream);
/*     */     }
/*     */     
/* 716 */     return this.stream;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasBody() {
/* 724 */     if (Trace.isOn) {
/* 725 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "hasBody()");
/*     */     }
/*     */     
/* 728 */     if (Trace.isOn) {
/* 729 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage", "hasBody()", 
/* 730 */           Boolean.valueOf(false));
/*     */     }
/* 732 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\common\internal\messages\WMQStreamMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */