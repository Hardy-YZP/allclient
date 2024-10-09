/*      */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*      */ 
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.provider.ProviderDestination;
/*      */ import com.ibm.msg.client.provider.ProviderMessage;
/*      */ import com.ibm.msg.client.provider.ProviderMessageReference;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.DataInputStream;
/*      */ import java.io.IOException;
/*      */ import java.util.Arrays;
/*      */ import java.util.HashMap;
/*      */ import javax.jms.JMSException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ class MQMessageReference
/*      */   implements ProviderMessageReference, Cloneable
/*      */ {
/*      */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQMessageReference.java";
/*      */   private static final long prime1 = 2147483587L;
/*      */   private static final long prime2 = 2147483647L;
/*      */   
/*      */   static {
/*  102 */     if (Trace.isOn) {
/*  103 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQMessageReference.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  117 */   private JMSMessage jmsMessage = null;
/*      */ 
/*      */   
/*  120 */   private MQJMSMessage mqjmsMessage = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  126 */   private byte[] messageId = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  131 */   private byte[] correlId = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  136 */   private int msgDataLength = 0;
/*  137 */   private int msgHeaderLength = 0;
/*      */ 
/*      */   
/*  140 */   private int hashcode = -1;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean wanted = true;
/*      */ 
/*      */   
/*  147 */   private String referenceQueue = null;
/*      */   
/*  149 */   private int domain = 0;
/*  150 */   private int version = 3;
/*      */   
/*      */   private static final String FLATTENED_HEADER = "MQMR";
/*  153 */   private static final byte[] FLATTENED_HEADER_AS_BYTES = new byte[] { 77, 81, 77, 82 };
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final int MSGREF_VERSION_3 = 3;
/*      */ 
/*      */ 
/*      */   
/*      */   public static final byte OPT_FIELD_MSG_TOKEN = 1;
/*      */ 
/*      */   
/*  164 */   protected byte optionalFields = 0;
/*      */ 
/*      */   
/*  167 */   private byte[] msgToken = null;
/*      */   
/*      */   private static final int MSGREF_DOMAIN_PTP = 0;
/*      */   
/*  171 */   private int dataQuantity = 0;
/*      */ 
/*      */ 
/*      */   
/*      */   private ProviderDestination destination;
/*      */ 
/*      */ 
/*      */   
/*      */   private static final String ASCII = "ASCII";
/*      */ 
/*      */ 
/*      */   
/*      */   private long browseTime;
/*      */ 
/*      */ 
/*      */   
/*  187 */   private MQSession session = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  196 */   private int backoutCount = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   MQMessageReference(MQSession session) {
/*  204 */     if (Trace.isOn) {
/*  205 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "<init>(MQSession)", new Object[] { session });
/*      */     }
/*      */     
/*  208 */     this.session = session;
/*  209 */     if (Trace.isOn) {
/*  210 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "<init>(MQSession)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private MQMessageReference() {
/*  221 */     if (Trace.isOn) {
/*  222 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "<init>()");
/*      */     }
/*      */     
/*  225 */     if (Trace.isOn) {
/*  226 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "<init>()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int checkEyeCatcherAndVersion(DataInputStream dis) throws IOException, JMSException {
/*  245 */     if (Trace.isOn) {
/*  246 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "checkEyeCatcherAndVersion(DataInputStream)", new Object[] { dis });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  252 */     byte[] eyeBytes = new byte[4];
/*  253 */     dis.readFully(eyeBytes);
/*  254 */     String header = new String(eyeBytes, "ASCII");
/*      */     
/*  256 */     if (!"MQMR".equals(header)) {
/*      */       
/*  258 */       if (Trace.isOn) {
/*  259 */         Trace.traceData("MQMessageReference", "Invalid MessageReferenceHeader.", null);
/*      */       }
/*  261 */       JMSException je = ConfigEnvironment.newException("MQJMS1096");
/*  262 */       if (Trace.isOn) {
/*  263 */         Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "checkEyeCatcherAndVersion(DataInputStream)", (Throwable)je, 1);
/*      */       }
/*      */       
/*  266 */       throw je;
/*      */     } 
/*      */     
/*  269 */     int version = dis.readInt();
/*      */     
/*  271 */     if (version != 3) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  276 */       if (Trace.isOn) {
/*  277 */         Trace.traceData("MQMessageReference", "Error. Unrecognised messageReference version.", null);
/*      */       }
/*  279 */       JMSException je = ConfigEnvironment.newException("MQJMS1098");
/*  280 */       if (Trace.isOn) {
/*  281 */         Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "checkEyeCatcherAndVersion(DataInputStream)", (Throwable)je, 2);
/*      */       }
/*      */       
/*  284 */       throw je;
/*      */     } 
/*      */     
/*  287 */     if (Trace.isOn) {
/*  288 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "checkEyeCatcherAndVersion(DataInputStream)", 
/*  289 */           Integer.valueOf(version));
/*      */     }
/*  291 */     return version;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   MQMessageReference(MQSession session, byte[] flatMR, ProviderDestination dest) throws JMSException {
/*  298 */     if (Trace.isOn) {
/*  299 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "<init>(MQSession,byte [ ],ProviderDestination)", new Object[] { session, flatMR, dest });
/*      */     }
/*      */     
/*  302 */     this.session = session;
/*      */     
/*      */     try {
/*  305 */       ByteArrayInputStream newByteStream = new ByteArrayInputStream(flatMR);
/*  306 */       DataInputStream dataStream = new DataInputStream(newByteStream);
/*      */       
/*  308 */       this.messageId = new byte[24];
/*  309 */       this.correlId = new byte[24];
/*  310 */       this.msgToken = new byte[16];
/*  311 */       this.version = checkEyeCatcherAndVersion(dataStream);
/*      */ 
/*      */       
/*  314 */       this.optionalFields = dataStream.readByte();
/*      */ 
/*      */       
/*  317 */       if ((this.optionalFields & 0x1) == 1)
/*      */       {
/*  319 */         newByteStream.read(this.msgToken, 0, 16);
/*      */       }
/*      */       
/*  322 */       newByteStream.read(this.messageId, 0, 24);
/*  323 */       newByteStream.read(this.correlId, 0, 24);
/*      */ 
/*      */       
/*  326 */       String destinationURI = reinflateString(dataStream);
/*  327 */       WMQDestinationURIParser uriParser = new WMQDestinationURIParser(destinationURI);
/*  328 */       this.domain = uriParser.getDomain();
/*      */       
/*  330 */       this.destination = dest;
/*      */ 
/*      */       
/*  333 */       this.msgDataLength = dataStream.readInt();
/*  334 */       this.msgHeaderLength = dataStream.readInt();
/*      */ 
/*      */       
/*  337 */       this.referenceQueue = reinflateString(dataStream);
/*      */     }
/*  339 */     catch (IOException ioe) {
/*  340 */       if (Trace.isOn) {
/*  341 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "<init>(MQSession,byte [ ],ProviderDestination)", ioe);
/*      */       }
/*      */ 
/*      */       
/*  345 */       JMSException je = ConfigEnvironment.newException("MQJMS1096");
/*  346 */       je.setLinkedException(ioe);
/*  347 */       if (Trace.isOn) {
/*  348 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "<init>(MQSession,byte [ ],ProviderDestination)", (Throwable)je);
/*      */       }
/*      */       
/*  351 */       throw je;
/*      */     } 
/*      */     
/*  354 */     if (Trace.isOn) {
/*  355 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "<init>(MQSession,byte [ ],ProviderDestination)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   MQJMSMessage getMQJMSMessage() {
/*  370 */     if (Trace.isOn) {
/*  371 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "getMQJMSMessage()", "getter", this.mqjmsMessage);
/*      */     }
/*      */     
/*  374 */     return this.mqjmsMessage;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setMQJMSMessage(MQJMSMessage message, int dataQuantity) {
/*  389 */     if (Trace.isOn) {
/*  390 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "setMQJMSMessage(MQJMSMessage,int)", new Object[] { message, 
/*  391 */             Integer.valueOf(dataQuantity) });
/*      */     }
/*      */     
/*  394 */     this.mqjmsMessage = message;
/*  395 */     this.jmsMessage = null;
/*  396 */     this.dataQuantity = dataQuantity;
/*      */     
/*  398 */     if (this.mqjmsMessage != null) {
/*  399 */       if (this.mqjmsMessage.getMessageId() != null) {
/*      */         
/*  401 */         this.messageId = this.mqjmsMessage.getMessageId();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  407 */         this.correlId = this.mqjmsMessage.getCorrelationId();
/*      */ 
/*      */         
/*  410 */         this.hashcode = 0;
/*  411 */         byte[] temp = new byte[8];
/*  412 */         for (int i = 0; i + 8 <= this.messageId.length; i += 8) {
/*  413 */           System.arraycopy(this.messageId, i, temp, 0, 8);
/*  414 */           this.hashcode ^= hash8bytes(temp);
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  420 */       this.backoutCount = this.mqjmsMessage.getBackoutCount();
/*      */     } else {
/*      */       
/*  423 */       this.messageId = null;
/*  424 */       this.correlId = null;
/*  425 */       this.hashcode = -1;
/*  426 */       this.backoutCount = 0;
/*      */     } 
/*  428 */     if (Trace.isOn) {
/*  429 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "setMQJMSMessage(MQJMSMessage,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setMQJMSMessage(MQJMSMessage message, int dataQuantity, int msgHeaderLength) {
/*  444 */     if (Trace.isOn) {
/*  445 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "setMQJMSMessage(MQJMSMessage,int,int)", new Object[] { message, 
/*      */             
/*  447 */             Integer.valueOf(dataQuantity), Integer.valueOf(msgHeaderLength) });
/*      */     }
/*      */     
/*  450 */     this.mqjmsMessage = message;
/*  451 */     this.jmsMessage = null;
/*  452 */     this.dataQuantity = dataQuantity;
/*      */     
/*  454 */     this.msgDataLength = message.getMessageDataLength();
/*  455 */     if (Trace.isOn) {
/*  456 */       Trace.traceData(this, "message length: ", Integer.valueOf(this.msgDataLength));
/*      */     }
/*  458 */     this.msgHeaderLength = msgHeaderLength;
/*  459 */     if (Trace.isOn) {
/*  460 */       Trace.traceData(this, "header length:  ", Integer.valueOf(this.msgHeaderLength));
/*      */     }
/*      */ 
/*      */     
/*  464 */     if (this.mqjmsMessage != null) {
/*  465 */       if (this.mqjmsMessage.getMessageId() != null) {
/*      */         
/*  467 */         this.messageId = this.mqjmsMessage.getMessageId();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  473 */         this.correlId = this.mqjmsMessage.getCorrelationId();
/*      */ 
/*      */         
/*  476 */         this.hashcode = 0;
/*  477 */         byte[] temp = new byte[8];
/*  478 */         for (int i = 0; i + 8 <= this.messageId.length; i += 8) {
/*  479 */           System.arraycopy(this.messageId, i, temp, 0, 8);
/*  480 */           this.hashcode ^= hash8bytes(temp);
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  486 */       this.backoutCount = this.mqjmsMessage.getBackoutCount();
/*      */     } else {
/*      */       
/*  489 */       this.messageId = null;
/*  490 */       this.correlId = null;
/*  491 */       this.msgDataLength = 0;
/*  492 */       this.msgHeaderLength = 0;
/*  493 */       this.hashcode = -1;
/*  494 */       this.backoutCount = 0;
/*      */     } 
/*  496 */     if (Trace.isOn) {
/*  497 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "setMQJMSMessage(MQJMSMessage,int,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setJMSMessage(ProviderMessage message, int dataQuantity) {
/*  505 */     if (Trace.isOn) {
/*  506 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "setJMSMessage(ProviderMessage,int)", new Object[] { message, 
/*  507 */             Integer.valueOf(dataQuantity) });
/*      */     }
/*      */     
/*  510 */     this.jmsMessage = (JMSMessage)message;
/*  511 */     this.mqjmsMessage = null;
/*  512 */     this.dataQuantity = dataQuantity;
/*  513 */     if (Trace.isOn) {
/*  514 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "setJMSMessage(ProviderMessage,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int hash8bytes(byte[] bytes) {
/*  527 */     if (Trace.isOn) {
/*  528 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "hash8bytes(byte [ ])", new Object[] { bytes });
/*      */     }
/*      */ 
/*      */     
/*  532 */     int i1 = bytes[0] & 0xFF;
/*  533 */     int i2 = (bytes[1] & 0xFF) << 8;
/*  534 */     int i3 = (bytes[2] & 0xFF) << 16;
/*  535 */     long l4 = (bytes[3] & 0xFF) << 24L;
/*      */     
/*  537 */     long w1 = (i1 + i2 + i3) + l4 | 0x40404040L;
/*      */ 
/*      */     
/*  540 */     w1 *= 2147483587L;
/*      */     
/*  542 */     w1 = w1 >>> 32L ^ w1 & 0xFFFFFFFFL;
/*      */ 
/*      */     
/*  545 */     i1 = bytes[4] & 0xFF;
/*  546 */     i2 = (bytes[5] & 0xFF) << 8;
/*  547 */     i3 = (bytes[6] & 0xFF) << 16;
/*  548 */     l4 = (bytes[7] & 0xFF) << 24L;
/*      */     
/*  550 */     long w2 = (i1 + i2 + i3) + l4 | 0x40404040L;
/*      */ 
/*      */     
/*  553 */     w2 *= 2147483647L;
/*      */     
/*  555 */     w2 = w2 >>> 32L ^ w2 & 0xFFFFFFFFL;
/*      */ 
/*      */     
/*  558 */     w1 *= w2;
/*      */     
/*  560 */     w1 = w1 >>> 32L ^ w1 & 0xFFFFFFFFL;
/*      */ 
/*      */     
/*  563 */     int traceRet1 = (int)w1;
/*  564 */     if (Trace.isOn) {
/*  565 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "hash8bytes(byte [ ])", 
/*  566 */           Integer.valueOf(traceRet1));
/*      */     }
/*  568 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   byte[] getMessageId() {
/*  578 */     byte[] result = null;
/*      */     
/*  580 */     if (this.messageId != null) {
/*  581 */       result = new byte[this.messageId.length];
/*  582 */       System.arraycopy(this.messageId, 0, result, 0, this.messageId.length);
/*      */     } 
/*      */     
/*  585 */     if (Trace.isOn) {
/*  586 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "getMessageId()", "getter", result);
/*      */     }
/*      */     
/*  589 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   byte[] getCorrelId() {
/*  599 */     byte[] result = null;
/*      */     
/*  601 */     if (this.correlId != null) {
/*  602 */       result = new byte[this.correlId.length];
/*  603 */       System.arraycopy(this.correlId, 0, result, 0, this.correlId.length);
/*      */     } 
/*      */     
/*  606 */     if (Trace.isOn) {
/*  607 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "getCorrelId()", "getter", result);
/*      */     }
/*      */     
/*  610 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int hashCode() {
/*  622 */     return this.hashcode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean equals(Object o) {
/*  632 */     boolean result = false;
/*      */ 
/*      */     
/*  635 */     if (o instanceof MQMessageReference) {
/*  636 */       MQMessageReference mr = (MQMessageReference)o;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  642 */       if (mr.messageId != null && this.messageId != null && mr.hashcode == this.hashcode && mr.messageId.length == this.messageId.length) {
/*  643 */         result = Arrays.equals(mr.messageId, this.messageId);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  648 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean matches(MQMessageSelector selector) throws JMSException {
/*  658 */     if (Trace.isOn) {
/*  659 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "matches(MQMessageSelector)", new Object[] { selector });
/*      */     }
/*      */     
/*  662 */     boolean result = false;
/*      */     try {
/*  664 */       result = selector.isSelected(getJMSMessage(), this.mqjmsMessage);
/*      */     }
/*  666 */     catch (JMSException je) {
/*  667 */       if (Trace.isOn) {
/*  668 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "matches(MQMessageSelector)", (Throwable)je);
/*      */       }
/*      */       
/*  671 */       if (Trace.isOn) {
/*  672 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "matches(MQMessageSelector)", (Throwable)je);
/*      */       }
/*      */       
/*  675 */       throw je;
/*      */     } 
/*  677 */     if (Trace.isOn) {
/*  678 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "matches(MQMessageSelector)", 
/*  679 */           Boolean.valueOf(result));
/*      */     }
/*  681 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/*  694 */     StringBuffer buf = new StringBuffer();
/*      */     
/*  696 */     buf.append("\nmessageId=");
/*  697 */     buf.append(Utils.bytesToHex(this.messageId));
/*  698 */     buf.append("\ncorrelId =");
/*  699 */     buf.append(Utils.bytesToHex(this.correlId));
/*  700 */     buf.append("\njmsMessage=" + ((this.jmsMessage == null) ? "unset" : "set"));
/*  701 */     buf.append("\nmqjmsMessage=" + ((this.mqjmsMessage == null) ? "null" : this.mqjmsMessage.toString()));
/*  702 */     buf.append("\nmsgDataLength=" + this.msgDataLength);
/*  703 */     buf.append("\nmsgHeaderLength=" + this.msgHeaderLength);
/*  704 */     buf.append("\nreferenceQueue= " + ((this.referenceQueue == null) ? "unset" : this.referenceQueue));
/*      */     
/*  706 */     String traceRet1 = buf.toString();
/*  707 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getDataQuantity() throws JMSException {
/*  723 */     if (Trace.isOn) {
/*  724 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "getDataQuantity()", "getter", 
/*  725 */           Integer.valueOf(this.dataQuantity));
/*      */     }
/*  727 */     return this.dataQuantity;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   JMSMessage getJMSMessage() throws JMSException {
/*  742 */     if (Trace.isOn) {
/*  743 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "getJMSMessage()");
/*      */     }
/*      */     try {
/*      */       JMSMessage traceRet2;
/*  747 */       switch (this.dataQuantity) {
/*      */         case 0:
/*  749 */           if (Trace.isOn) {
/*  750 */             Trace.traceData(this, "dataQuantity is NO_DATA; returning null", null);
/*      */           }
/*  752 */           if (Trace.isOn) {
/*  753 */             Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "getJMSMessage()", null, 1);
/*      */           }
/*      */           
/*  756 */           return null;
/*      */         
/*      */         case 1:
/*  759 */           if (this.jmsMessage != null) {
/*  760 */             if (Trace.isOn) {
/*  761 */               Trace.traceData(this, "dataQuantity is HEADER_DATA; returning existing message", null);
/*      */             }
/*  763 */             if (Trace.isOn) {
/*  764 */               Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "getJMSMessage()", this.jmsMessage, 2);
/*      */             }
/*      */             
/*  767 */             return this.jmsMessage;
/*      */           } 
/*      */           
/*  770 */           if (Trace.isOn) {
/*  771 */             Trace.traceData(this, "dataQuantity is HEADER_DATA; returning new message", null);
/*      */           }
/*      */           
/*  774 */           this.jmsMessage = this.mqjmsMessage.createJMSMessage(this.session, this.destination, 1);
/*  775 */           if (Trace.isOn) {
/*  776 */             Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "getJMSMessage()", this.jmsMessage, 3);
/*      */           }
/*      */           
/*  779 */           return this.jmsMessage;
/*      */         case 2:
/*  781 */           if (this.jmsMessage != null) {
/*  782 */             if (Trace.isOn) {
/*  783 */               Trace.traceData(this, "dataQuantity is FULL_DATA; returning existing message", null);
/*      */             }
/*  785 */             if (Trace.isOn) {
/*  786 */               Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "getJMSMessage()", this.jmsMessage, 4);
/*      */             }
/*      */             
/*  789 */             return this.jmsMessage;
/*      */           } 
/*  791 */           if (Trace.isOn) {
/*  792 */             Trace.traceData(this, "dataQuantity is FULL_DATA; returning new message", null);
/*      */           }
/*  794 */           traceRet2 = this.jmsMessage = this.mqjmsMessage.createJMSMessage(this.session, this.destination);
/*  795 */           if (Trace.isOn) {
/*  796 */             Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "getJMSMessage()", traceRet2, 5);
/*      */           }
/*      */           
/*  799 */           return traceRet2;
/*      */       } 
/*  801 */       if (Trace.isOn) {
/*  802 */         Trace.traceData(this, "Bad dataQuantity " + this.dataQuantity, null);
/*      */       }
/*  804 */       IllegalStateException traceRet3 = new IllegalStateException(ConfigEnvironment.getErrorMessage("MQJMS0006"));
/*  805 */       if (Trace.isOn) {
/*  806 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "getJMSMessage()", traceRet3, 1);
/*      */       }
/*      */       
/*  809 */       throw traceRet3;
/*      */ 
/*      */     
/*      */     }
/*  813 */     catch (IOException ioe) {
/*  814 */       if (Trace.isOn) {
/*  815 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "getJMSMessage()", ioe);
/*      */       }
/*      */ 
/*      */       
/*  819 */       JMSException je = ConfigEnvironment.newException("MQJMS0006");
/*  820 */       je.setLinkedException(ioe);
/*  821 */       if (Trace.isOn) {
/*  822 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "getJMSMessage()", (Throwable)je, 2);
/*      */       }
/*      */       
/*  825 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ProviderMessage getMessage() throws JMSException {
/*      */     IllegalStateException ise;
/*  840 */     if (Trace.isOn) {
/*  841 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "getMessage()");
/*      */     }
/*      */ 
/*      */     
/*  845 */     switch (this.dataQuantity) {
/*      */       case 0:
/*  847 */         if (Trace.isOn) {
/*  848 */           Trace.traceData(this, "dataQuantity is NO_DATA; returning null", null);
/*      */         }
/*  850 */         if (Trace.isOn) {
/*  851 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "getMessage()", null, 1);
/*      */         }
/*      */         
/*  854 */         return null;
/*      */       case 1:
/*  856 */         if (this.jmsMessage != null) {
/*  857 */           if (Trace.isOn) {
/*  858 */             Trace.traceData(this, "dataQuantity is HEADER_DATA; returning existing message", null);
/*      */           }
/*  860 */           if (Trace.isOn) {
/*  861 */             Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "getMessage()", this.jmsMessage, 2);
/*      */           }
/*      */           
/*  864 */           return this.jmsMessage;
/*      */         } 
/*  866 */         ise = new IllegalStateException("MQMessageReference message not parsed yet");
/*  867 */         if (Trace.isOn) {
/*  868 */           Trace.traceData(this, "dataQuantity is HEADER_DATA but message not parsed yet; ERROR", null);
/*  869 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "getMessage()", ise, 1);
/*      */         } 
/*      */         
/*  872 */         if (Trace.isOn) {
/*  873 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "getMessage()", ise, 1);
/*      */         }
/*      */         
/*  876 */         throw ise;
/*      */ 
/*      */       
/*      */       case 2:
/*  880 */         if (Trace.isOn) {
/*  881 */           Trace.traceData(this, "dataQuantity is FULL_DATA; returning existing message", null);
/*      */         }
/*  883 */         if (Trace.isOn) {
/*  884 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "getMessage()", this.jmsMessage, 3);
/*      */         }
/*      */         
/*  887 */         return this.jmsMessage;
/*      */     } 
/*  889 */     if (Trace.isOn) {
/*  890 */       Trace.traceData(this, "Bad dataQuantity " + this.dataQuantity, null);
/*      */     }
/*  892 */     IllegalStateException traceRet3 = new IllegalStateException(ConfigEnvironment.getErrorMessage("MQJMS0006"));
/*  893 */     if (Trace.isOn) {
/*  894 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "getMessage()", traceRet3, 2);
/*      */     }
/*      */     
/*  897 */     throw traceRet3;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object clone() {
/*  910 */     if (Trace.isOn) {
/*  911 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "clone()");
/*      */     }
/*      */ 
/*      */     
/*  915 */     MQMessageReference newClone = new MQMessageReference();
/*  916 */     newClone.session = this.session;
/*  917 */     newClone.messageId = this.messageId;
/*  918 */     newClone.correlId = this.correlId;
/*  919 */     newClone.msgDataLength = this.msgDataLength;
/*  920 */     newClone.msgHeaderLength = this.msgHeaderLength;
/*  921 */     newClone.hashcode = this.hashcode;
/*  922 */     newClone.referenceQueue = this.referenceQueue;
/*  923 */     newClone.domain = this.domain;
/*  924 */     newClone.version = this.version;
/*      */     
/*  926 */     newClone.dataQuantity = 0;
/*  927 */     newClone.jmsMessage = null;
/*  928 */     newClone.mqjmsMessage = null;
/*  929 */     newClone.wanted = this.wanted;
/*  930 */     newClone.backoutCount = this.backoutCount;
/*      */ 
/*      */     
/*  933 */     newClone.browseTime = this.browseTime;
/*      */     
/*  935 */     if (Trace.isOn) {
/*  936 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "clone()", newClone);
/*      */     }
/*      */     
/*  939 */     return newClone;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] flatten() throws JMSException {
/*  954 */     if (Trace.isOn) {
/*  955 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "flatten()");
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  960 */       ByteArrayOutputStream newStream = new ByteArrayOutputStream();
/*      */ 
/*      */ 
/*      */       
/*  964 */       if (Trace.isOn) {
/*  965 */         Trace.traceData(this, "about to flatten a version " + Integer.toString(this.version) + " messageReference.", null);
/*      */       }
/*  967 */       newStream.write(FLATTENED_HEADER_AS_BYTES);
/*      */       
/*  969 */       newStream.write(intToByteArray(this.version));
/*      */ 
/*      */ 
/*      */       
/*  973 */       if (this.version == 3) {
/*      */ 
/*      */ 
/*      */         
/*  977 */         newStream.write(this.optionalFields);
/*      */ 
/*      */         
/*  980 */         if ((this.optionalFields & 0x1) == 1)
/*      */         {
/*  982 */           newStream.write(this.msgToken);
/*      */         }
/*      */         
/*  985 */         newStream.write(this.messageId);
/*  986 */         newStream.write(this.correlId);
/*      */ 
/*      */         
/*  989 */         flattenString(newStream, this.destination.toURI());
/*      */ 
/*      */         
/*  992 */         if (Trace.isOn) {
/*  993 */           Trace.traceData(this, "writing msgDataLength= ", Integer.valueOf(this.msgDataLength));
/*      */         }
/*      */         
/*  996 */         newStream.write(intToByteArray(this.msgDataLength));
/*      */         
/*  998 */         if (Trace.isOn) {
/*  999 */           Trace.traceData(this, "writing msgHeaderLength= ", Integer.valueOf(this.msgHeaderLength));
/*      */         }
/* 1001 */         newStream.write(intToByteArray(this.msgHeaderLength));
/*      */         
/* 1003 */         if (Trace.isOn) {
/* 1004 */           Trace.traceData(this, "writing referenceQueue= ", this.referenceQueue);
/*      */         }
/* 1006 */         flattenString(newStream, this.referenceQueue);
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 1011 */         if (Trace.isOn) {
/* 1012 */           Trace.traceData(this, "Error. Unrecognised messageReference version.", null);
/*      */         }
/* 1014 */         JMSException je2 = ConfigEnvironment.newException("MQJMS1098");
/* 1015 */         if (Trace.isOn) {
/* 1016 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "flatten()", (Throwable)je2, 1);
/*      */         }
/*      */         
/* 1019 */         throw je2;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1026 */       byte[] traceRet1 = newStream.toByteArray();
/* 1027 */       if (Trace.isOn) {
/* 1028 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "flatten()", traceRet1);
/*      */       }
/*      */       
/* 1031 */       return traceRet1;
/*      */     
/*      */     }
/* 1034 */     catch (IOException ioe) {
/* 1035 */       if (Trace.isOn) {
/* 1036 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "flatten()", ioe, 1);
/*      */       }
/*      */       
/* 1039 */       if (Trace.isOn) {
/* 1040 */         Trace.traceData(this, "exception thrown while attempting to write to byte[]", null);
/*      */       }
/* 1042 */       JMSException je = ConfigEnvironment.newException("MQJMS1098");
/* 1043 */       if (Trace.isOn) {
/* 1044 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "flatten()", (Throwable)je, 2);
/*      */       }
/*      */       
/* 1047 */       throw je;
/*      */     
/*      */     }
/* 1050 */     catch (JMSException je) {
/* 1051 */       if (Trace.isOn) {
/* 1052 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "flatten()", (Throwable)je, 2);
/*      */       }
/*      */       
/* 1055 */       if (Trace.isOn) {
/* 1056 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "flatten()", (Throwable)je, 3);
/*      */       }
/*      */       
/* 1059 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void flattenString(ByteArrayOutputStream baos, String text) throws IOException {
/* 1074 */     if (Trace.isOn) {
/* 1075 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "flattenString(ByteArrayOutputStream,String)", new Object[] { baos, text });
/*      */     }
/*      */ 
/*      */     
/* 1079 */     byte[] bytes = null;
/* 1080 */     if (null != text) {
/* 1081 */       bytes = text.getBytes("ASCII");
/*      */     }
/* 1083 */     flattenBytes(baos, bytes);
/* 1084 */     if (Trace.isOn) {
/* 1085 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "flattenString(ByteArrayOutputStream,String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void flattenBytes(ByteArrayOutputStream baos, byte[] bytes) throws IOException {
/* 1100 */     if (Trace.isOn) {
/* 1101 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "flattenBytes(ByteArrayOutputStream,byte [ ])", new Object[] { baos, bytes });
/*      */     }
/*      */ 
/*      */     
/* 1105 */     if (null == bytes) {
/* 1106 */       baos.write(intToByteArray(0));
/* 1107 */       if (Trace.isOn) {
/* 1108 */         Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "flattenBytes(ByteArrayOutputStream,byte [ ])", 1);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1114 */     baos.write(intToByteArray(bytes.length));
/* 1115 */     baos.write(bytes);
/* 1116 */     if (Trace.isOn) {
/* 1117 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "flattenBytes(ByteArrayOutputStream,byte [ ])", 2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static byte[] reinflateBytes(DataInputStream dis) throws IOException {
/* 1132 */     if (Trace.isOn) {
/* 1133 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "reinflateBytes(DataInputStream)", new Object[] { dis });
/*      */     }
/*      */ 
/*      */     
/* 1137 */     int len = dis.readInt();
/* 1138 */     if (0 > len) {
/*      */       
/* 1140 */       HashMap<String, Integer> info = new HashMap<>();
/* 1141 */       info.put("len", Integer.valueOf(len));
/* 1142 */       Trace.ffst("MQMessageReference", "reinflateString", "XN00F004", info, IOException.class);
/*      */     } 
/*      */     
/* 1145 */     if (0 == len) {
/* 1146 */       if (Trace.isOn) {
/* 1147 */         Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "reinflateBytes(DataInputStream)", null, 1);
/*      */       }
/*      */       
/* 1150 */       return null;
/*      */     } 
/*      */     
/* 1153 */     byte[] bytes = new byte[len];
/* 1154 */     dis.readFully(bytes);
/* 1155 */     if (Trace.isOn) {
/* 1156 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "reinflateBytes(DataInputStream)", bytes, 2);
/*      */     }
/*      */     
/* 1159 */     return bytes;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String reinflateString(DataInputStream dis) throws IOException {
/* 1172 */     if (Trace.isOn) {
/* 1173 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "reinflateString(DataInputStream)", new Object[] { dis });
/*      */     }
/*      */ 
/*      */     
/* 1177 */     byte[] bytes = reinflateBytes(dis);
/* 1178 */     String text = null;
/* 1179 */     if (null != bytes) {
/* 1180 */       text = new String(bytes, "ASCII");
/*      */     }
/* 1182 */     if (Trace.isOn) {
/* 1183 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "reinflateString(DataInputStream)", text);
/*      */     }
/*      */     
/* 1186 */     return text;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static byte[] intToByteArray(int value) {
/* 1196 */     if (Trace.isOn) {
/* 1197 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "intToByteArray(int)", new Object[] {
/* 1198 */             Integer.valueOf(value)
/*      */           });
/*      */     }
/*      */     
/* 1202 */     byte[] traceRet1 = { (byte)(value >>> 24), (byte)(value >>> 16), (byte)(value >>> 8), (byte)value };
/* 1203 */     if (Trace.isOn) {
/* 1204 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "intToByteArray(int)", traceRet1);
/*      */     }
/*      */     
/* 1207 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean checkIfOptionalFieldIsPresent(byte fieldToCheck) {
/* 1218 */     if (Trace.isOn) {
/* 1219 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "checkIfOptionalFieldIsPresent(byte)", new Object[] {
/* 1220 */             Byte.valueOf(fieldToCheck)
/*      */           });
/*      */     }
/*      */     
/* 1224 */     if ((this.optionalFields & fieldToCheck) != 0) {
/*      */       
/* 1226 */       if (Trace.isOn) {
/* 1227 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "checkIfOptionalFieldIsPresent(byte)", 
/* 1228 */             Boolean.valueOf(true), 1);
/*      */       }
/* 1230 */       return true;
/*      */     } 
/*      */     
/* 1233 */     if (Trace.isOn) {
/* 1234 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "checkIfOptionalFieldIsPresent(byte)", 
/* 1235 */           Boolean.valueOf(false), 2);
/*      */     }
/* 1237 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void setOptionalFieldBit(byte fieldToSet, boolean setOn) {
/* 1247 */     if (Trace.isOn) {
/* 1248 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "setOptionalFieldBit(byte,boolean)", new Object[] {
/* 1249 */             Byte.valueOf(fieldToSet), 
/* 1250 */             Boolean.valueOf(setOn)
/*      */           });
/*      */     }
/* 1253 */     if (setOn) {
/* 1254 */       this.optionalFields = (byte)(this.optionalFields | fieldToSet);
/*      */     } else {
/* 1256 */       this.optionalFields = (byte)(this.optionalFields & (fieldToSet ^ 0xFFFFFFFF));
/*      */     } 
/*      */     
/* 1259 */     if (Trace.isOn) {
/* 1260 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "setOptionalFieldBit(byte,boolean)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setVersion(int newVersion) {
/* 1272 */     if (Trace.isOn) {
/* 1273 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "setVersion(int)", "setter", 
/* 1274 */           Integer.valueOf(newVersion));
/*      */     }
/* 1276 */     if (newVersion != 1 && 
/* 1277 */       Trace.isOn) {
/* 1278 */       Trace.traceData(this, "Invalid MessageReference version being set! This is not fully implemented yet.", null);
/*      */     }
/*      */     
/* 1281 */     this.version = newVersion;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setReferenceQueue(String queueName) {
/* 1291 */     if (Trace.isOn) {
/* 1292 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "setReferenceQueue(String)", "setter", queueName);
/*      */     }
/*      */     
/* 1295 */     this.referenceQueue = queueName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   String getReferenceQueue() {
/* 1305 */     if (Trace.isOn) {
/* 1306 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "getReferenceQueue()", "getter", this.referenceQueue);
/*      */     }
/*      */     
/* 1309 */     return this.referenceQueue;
/*      */   }
/*      */ 
/*      */   
/*      */   boolean isWanted() {
/* 1314 */     if (Trace.isOn) {
/* 1315 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "isWanted()", "getter", 
/* 1316 */           Boolean.valueOf(this.wanted));
/*      */     }
/* 1318 */     return this.wanted;
/*      */   }
/*      */ 
/*      */   
/*      */   void setIsWanted(boolean want) {
/* 1323 */     if (Trace.isOn) {
/* 1324 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "setIsWanted(boolean)", "setter", 
/* 1325 */           Boolean.valueOf(want));
/*      */     }
/* 1327 */     this.wanted = want;
/*      */   }
/*      */ 
/*      */   
/*      */   int getDomain() {
/* 1332 */     if (Trace.isOn) {
/* 1333 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "getDomain()", "getter", 
/* 1334 */           Integer.valueOf(this.domain));
/*      */     }
/* 1336 */     return this.domain;
/*      */   }
/*      */ 
/*      */   
/*      */   void setDomain(int domain) {
/* 1341 */     if (Trace.isOn) {
/* 1342 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "setDomain(int)", "setter", 
/* 1343 */           Integer.valueOf(domain));
/*      */     }
/* 1345 */     this.domain = domain;
/*      */   }
/*      */ 
/*      */   
/*      */   void setBrowseTime(long time) {
/* 1350 */     if (Trace.isOn) {
/* 1351 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "setBrowseTime(long)", "setter", 
/* 1352 */           Long.valueOf(time));
/*      */     }
/* 1354 */     this.browseTime = time;
/*      */   }
/*      */ 
/*      */   
/*      */   long getBrowseTime() {
/* 1359 */     if (Trace.isOn) {
/* 1360 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "getBrowseTime()", "getter", 
/* 1361 */           Long.valueOf(this.browseTime));
/*      */     }
/* 1363 */     return this.browseTime;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setBackoutCount(int newCount) throws JMSException {
/* 1377 */     if (Trace.isOn) {
/* 1378 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "setBackoutCount(int)", "setter", 
/* 1379 */           Integer.valueOf(newCount));
/*      */     }
/*      */     
/*      */     try {
/* 1383 */       this.backoutCount = newCount;
/*      */       
/* 1385 */       if (this.mqjmsMessage != null) {
/* 1386 */         this.mqjmsMessage.setBackoutCount(newCount);
/*      */       }
/* 1388 */       if (this.jmsMessage != null) {
/* 1389 */         this.jmsMessage.setJMSRedelivered((newCount != 0));
/* 1390 */         this.jmsMessage._setJMSXDeliveryCountFromInt(newCount + 1);
/*      */       }
/*      */     
/* 1393 */     } catch (JMSException je) {
/* 1394 */       if (Trace.isOn) {
/* 1395 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "setBackoutCount(int)", (Throwable)je);
/*      */       }
/*      */       
/* 1398 */       if (Trace.isOn) {
/* 1399 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "setBackoutCount(int)", (Throwable)je);
/*      */       }
/*      */       
/* 1402 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getBackoutCount() {
/* 1412 */     if (Trace.isOn) {
/* 1413 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "getBackoutCount()", "getter", 
/* 1414 */           Integer.valueOf(this.backoutCount));
/*      */     }
/* 1416 */     return this.backoutCount;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMsgToken(byte[] bs) {
/* 1426 */     if (Trace.isOn) {
/* 1427 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "setMsgToken(byte [ ])", "setter", bs);
/*      */     }
/*      */ 
/*      */     
/* 1431 */     setOptionalFieldBit((byte)1, true);
/*      */     
/* 1433 */     if (this.msgToken == null) {
/* 1434 */       this.msgToken = new byte[16];
/*      */     }
/* 1436 */     for (int i = 0; i < 16; i++) {
/* 1437 */       this.msgToken[i] = bs[i];
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getMsgToken() {
/* 1449 */     if (Trace.isOn) {
/* 1450 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "getMsgToken()", "getter", this.msgToken);
/*      */     }
/*      */     
/* 1453 */     return this.msgToken;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getVersion() {
/* 1463 */     if (Trace.isOn) {
/* 1464 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "getVersion()", "getter", 
/* 1465 */           Integer.valueOf(this.version));
/*      */     }
/* 1467 */     return this.version;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDestinationAsString() {
/* 1478 */     if (Trace.isOn) {
/* 1479 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "getDestinationAsString()", "getter", null);
/*      */     }
/*      */     
/* 1482 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDestination(ProviderDestination dest) {
/* 1491 */     if (Trace.isOn) {
/* 1492 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "setDestination(ProviderDestination)", "setter", dest);
/*      */     }
/*      */     
/* 1495 */     this.destination = dest;
/*      */   }
/*      */   
/*      */   ProviderDestination getDestination() {
/* 1499 */     if (Trace.isOn) {
/* 1500 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "getDestination()", "getter", this.destination);
/*      */     }
/*      */     
/* 1503 */     return this.destination;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMsgDataLength() {
/* 1511 */     if (Trace.isOn) {
/* 1512 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "getMsgDataLength()", "getter", 
/* 1513 */           Integer.valueOf(this.msgDataLength));
/*      */     }
/* 1515 */     return this.msgDataLength;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getHeaderLength() {
/* 1523 */     if (Trace.isOn) {
/* 1524 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageReference", "getHeaderLength()", "getter", 
/* 1525 */           Integer.valueOf(this.msgHeaderLength));
/*      */     }
/* 1527 */     return this.msgHeaderLength;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isManagedQueue() {
/* 1532 */     return false;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\MQMessageReference.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */