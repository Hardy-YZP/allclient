/*      */ package com.ibm.msg.client.jms.internal;
/*      */ 
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.provider.ProviderMessage;
/*      */ import com.ibm.msg.client.provider.ProviderStreamMessage;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import javax.jms.JMSException;
/*      */ import javax.jms.Message;
/*      */ import javax.jms.MessageEOFException;
/*      */ import javax.jms.MessageFormatException;
/*      */ import javax.jms.MessageNotReadableException;
/*      */ import javax.jms.StreamMessage;
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
/*      */ public class JmsStreamMessageImpl
/*      */   extends JmsMessageImpl
/*      */   implements StreamMessage
/*      */ {
/*      */   private static final long serialVersionUID = 4101671711366200546L;
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsStreamMessageImpl.java";
/*      */   private ProviderStreamMessage providerStreamMessage;
/*      */   
/*      */   static {
/*   53 */     if (Trace.isOn) {
/*   54 */       Trace.data("com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsStreamMessageImpl.java");
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
/*      */   private boolean partReadBytesElement = false;
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
/*   82 */   private byte[] bytesElement = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int bytesElementOffset;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int MAX_ELEMENTS_IN_TO_STRING = 32;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JmsStreamMessageImpl(JmsSessionImpl session) throws JMSException {
/*  101 */     super(session);
/*  102 */     if (Trace.isOn) {
/*  103 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "<init>(JmsSessionImpl)", new Object[] { session });
/*      */     }
/*      */ 
/*      */     
/*  107 */     this.messageType = "jms_stream";
/*      */     
/*  109 */     if (Trace.isOn) {
/*  110 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "<init>(JmsSessionImpl)");
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
/*      */   public JmsStreamMessageImpl(String connectionType, Message message) throws JMSException {
/*  125 */     super(connectionType, message);
/*  126 */     if (Trace.isOn) {
/*  127 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "<init>(String,Message)", new Object[] { connectionType, message });
/*      */     }
/*      */ 
/*      */     
/*  131 */     this.messageType = "jms_stream";
/*      */     
/*  133 */     if (Trace.isOn) {
/*  134 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "<init>(String,Message)");
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
/*      */   JmsStreamMessageImpl(ProviderStreamMessage newMsg, JmsSessionImpl newSess, String connectionTypeName) throws JMSException {
/*  150 */     super((ProviderMessage)newMsg, newSess, connectionTypeName);
/*  151 */     if (Trace.isOn) {
/*  152 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "<init>(ProviderStreamMessage,JmsSessionImpl,String)", new Object[] { newMsg, newSess, connectionTypeName });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  158 */     this.providerStreamMessage = newMsg;
/*  159 */     this.messageType = "jms_stream";
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  164 */       this.providerStreamMessage.reset();
/*      */     }
/*  166 */     catch (JMSException e) {
/*  167 */       if (Trace.isOn) {
/*  168 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "<init>(ProviderStreamMessage,JmsSessionImpl,String)", (Throwable)e);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  176 */     if (Trace.isOn) {
/*  177 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "<init>(ProviderStreamMessage,JmsSessionImpl,String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   JmsStreamMessageImpl(JmsSessionImpl session, StreamMessage streamMessage) throws JMSException {
/*  188 */     super(session, (Message)streamMessage);
/*  189 */     if (Trace.isOn) {
/*  190 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "<init>(JmsSessionImpl,StreamMessage)", new Object[] { session, streamMessage });
/*      */     }
/*      */ 
/*      */     
/*  194 */     this.messageType = "jms_stream";
/*      */ 
/*      */     
/*  197 */     streamMessage.reset();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*      */       while (true) {
/*  204 */         writeObject(streamMessage.readObject());
/*      */       
/*      */       }
/*      */     }
/*  208 */     catch (JMSException e) {
/*  209 */       if (Trace.isOn) {
/*  210 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "<init>(JmsSessionImpl,StreamMessage)", (Throwable)e);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  218 */       if (Trace.isOn) {
/*  219 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "<init>(JmsSessionImpl,StreamMessage)");
/*      */       }
/*      */       return;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean readBoolean() throws JMSException {
/*      */     boolean result;
/*  229 */     if (Trace.isOn) {
/*  230 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readBoolean()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  235 */     Object nextObj = getNextField("readBoolean");
/*      */ 
/*      */     
/*      */     try {
/*  239 */       result = JmsPropertyContextImpl.parseBoolean(nextObj, "", (Class)MessageFormatException.class);
/*      */     
/*      */     }
/*  242 */     catch (JMSException e) {
/*  243 */       if (Trace.isOn) {
/*  244 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readBoolean()", (Throwable)e, 1);
/*      */       }
/*      */ 
/*      */       
/*  248 */       this.providerStreamMessage.stepBack();
/*  249 */       if (Trace.isOn) {
/*  250 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readBoolean()", (Throwable)e, 1);
/*      */       }
/*      */       
/*  253 */       throw e;
/*      */     
/*      */     }
/*  256 */     catch (RuntimeException e) {
/*  257 */       if (Trace.isOn) {
/*  258 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readBoolean()", e, 2);
/*      */       }
/*      */ 
/*      */       
/*  262 */       this.providerStreamMessage.stepBack();
/*  263 */       if (Trace.isOn) {
/*  264 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readBoolean()", e, 2);
/*      */       }
/*      */       
/*  267 */       throw e;
/*      */     } 
/*      */ 
/*      */     
/*  271 */     if (Trace.isOn) {
/*  272 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readBoolean()", 
/*  273 */           Boolean.valueOf(result));
/*      */     }
/*  275 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte readByte() throws JMSException {
/*      */     byte result;
/*  283 */     if (Trace.isOn) {
/*  284 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readByte()");
/*      */     }
/*      */ 
/*      */     
/*  288 */     Object nextObj = getNextField("readByte");
/*      */ 
/*      */     
/*      */     try {
/*  292 */       result = JmsPropertyContextImpl.parseByte(nextObj, "", (Class)MessageFormatException.class);
/*      */     
/*      */     }
/*  295 */     catch (RuntimeException e) {
/*  296 */       if (Trace.isOn) {
/*  297 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readByte()", e, 1);
/*      */       }
/*      */ 
/*      */       
/*  301 */       this.providerStreamMessage.stepBack();
/*  302 */       if (Trace.isOn) {
/*  303 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readByte()", e, 1);
/*      */       }
/*      */       
/*  306 */       throw e;
/*      */     
/*      */     }
/*  309 */     catch (JMSException e) {
/*  310 */       if (Trace.isOn) {
/*  311 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readByte()", (Throwable)e, 2);
/*      */       }
/*      */ 
/*      */       
/*  315 */       this.providerStreamMessage.stepBack();
/*  316 */       if (Trace.isOn) {
/*  317 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readByte()", (Throwable)e, 2);
/*      */       }
/*      */       
/*  320 */       throw e;
/*      */     } 
/*      */ 
/*      */     
/*  324 */     if (Trace.isOn) {
/*  325 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readByte()", 
/*  326 */           Byte.valueOf(result));
/*      */     }
/*  328 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public short readShort() throws JMSException {
/*      */     short result;
/*  336 */     if (Trace.isOn) {
/*  337 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readShort()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  342 */     Object nextObj = getNextField("readShort");
/*      */ 
/*      */     
/*      */     try {
/*  346 */       result = JmsPropertyContextImpl.parseShort(nextObj, "", (Class)MessageFormatException.class);
/*      */     
/*      */     }
/*  349 */     catch (JMSException e) {
/*  350 */       if (Trace.isOn) {
/*  351 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readShort()", (Throwable)e, 1);
/*      */       }
/*      */ 
/*      */       
/*  355 */       this.providerStreamMessage.stepBack();
/*  356 */       if (Trace.isOn) {
/*  357 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readShort()", (Throwable)e, 1);
/*      */       }
/*      */       
/*  360 */       throw e;
/*      */     
/*      */     }
/*  363 */     catch (RuntimeException e) {
/*  364 */       if (Trace.isOn) {
/*  365 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readShort()", e, 2);
/*      */       }
/*      */ 
/*      */       
/*  369 */       this.providerStreamMessage.stepBack();
/*  370 */       if (Trace.isOn) {
/*  371 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readShort()", e, 2);
/*      */       }
/*      */       
/*  374 */       throw e;
/*      */     } 
/*      */ 
/*      */     
/*  378 */     if (Trace.isOn) {
/*  379 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readShort()", 
/*  380 */           Short.valueOf(result));
/*      */     }
/*  382 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public char readChar() throws JMSException {
/*  390 */     if (Trace.isOn) {
/*  391 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readChar()");
/*      */     }
/*      */     
/*  394 */     char result = Character.MIN_VALUE;
/*      */     
/*  396 */     Object nextObj = getNextField("readChar");
/*      */     
/*  398 */     if (nextObj == null) {
/*      */ 
/*      */       
/*  401 */       NullPointerException npe = (NullPointerException)JmsErrorUtils.createException("JMSCC0062", null);
/*      */       
/*  403 */       if (Trace.isOn) {
/*  404 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readChar()", npe);
/*      */       }
/*      */       
/*  407 */       throw npe;
/*      */     } 
/*  409 */     if (nextObj instanceof Character) {
/*      */       
/*  411 */       result = ((Character)nextObj).charValue();
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  416 */       this.providerStreamMessage.stepBack();
/*      */ 
/*      */       
/*  419 */       throwBadConvertException(nextObj, "", "Character", (Class)MessageFormatException.class);
/*      */     } 
/*      */ 
/*      */     
/*  423 */     if (Trace.isOn) {
/*  424 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readChar()", 
/*  425 */           Character.valueOf(result));
/*      */     }
/*  427 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int readInt() throws JMSException {
/*      */     int result;
/*  435 */     if (Trace.isOn) {
/*  436 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readInt()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  441 */     Object nextObj = getNextField("readInt");
/*      */ 
/*      */     
/*      */     try {
/*  445 */       result = JmsPropertyContextImpl.parseInt(nextObj, "", (Class)MessageFormatException.class);
/*      */     
/*      */     }
/*  448 */     catch (JMSException e) {
/*  449 */       if (Trace.isOn) {
/*  450 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readInt()", (Throwable)e, 1);
/*      */       }
/*      */ 
/*      */       
/*  454 */       this.providerStreamMessage.stepBack();
/*  455 */       if (Trace.isOn) {
/*  456 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readInt()", (Throwable)e, 1);
/*      */       }
/*      */       
/*  459 */       throw e;
/*      */     
/*      */     }
/*  462 */     catch (RuntimeException e) {
/*  463 */       if (Trace.isOn) {
/*  464 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readInt()", e, 2);
/*      */       }
/*      */ 
/*      */       
/*  468 */       this.providerStreamMessage.stepBack();
/*  469 */       if (Trace.isOn) {
/*  470 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readInt()", e, 2);
/*      */       }
/*      */       
/*  473 */       throw e;
/*      */     } 
/*      */ 
/*      */     
/*  477 */     if (Trace.isOn) {
/*  478 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readInt()", 
/*  479 */           Integer.valueOf(result));
/*      */     }
/*  481 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long readLong() throws JMSException {
/*      */     long result;
/*  489 */     if (Trace.isOn) {
/*  490 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readLong()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  495 */     Object nextObj = getNextField("readLong");
/*      */ 
/*      */     
/*      */     try {
/*  499 */       result = JmsPropertyContextImpl.parseLong(nextObj, "", (Class)MessageFormatException.class);
/*      */     
/*      */     }
/*  502 */     catch (JMSException e) {
/*  503 */       if (Trace.isOn) {
/*  504 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readLong()", (Throwable)e, 1);
/*      */       }
/*      */ 
/*      */       
/*  508 */       this.providerStreamMessage.stepBack();
/*  509 */       if (Trace.isOn) {
/*  510 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readLong()", (Throwable)e, 1);
/*      */       }
/*      */       
/*  513 */       throw e;
/*      */     
/*      */     }
/*  516 */     catch (RuntimeException e) {
/*  517 */       if (Trace.isOn) {
/*  518 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readLong()", e, 2);
/*      */       }
/*      */ 
/*      */       
/*  522 */       this.providerStreamMessage.stepBack();
/*  523 */       if (Trace.isOn) {
/*  524 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readLong()", e, 2);
/*      */       }
/*      */       
/*  527 */       throw e;
/*      */     } 
/*      */ 
/*      */     
/*  531 */     if (Trace.isOn) {
/*  532 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readLong()", 
/*  533 */           Long.valueOf(result));
/*      */     }
/*  535 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float readFloat() throws JMSException {
/*      */     float result;
/*  543 */     if (Trace.isOn) {
/*  544 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readFloat()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  549 */     Object nextObj = getNextField("readFloat");
/*      */ 
/*      */     
/*      */     try {
/*  553 */       result = JmsPropertyContextImpl.parseFloat(nextObj, "", (Class)MessageFormatException.class);
/*      */     
/*      */     }
/*  556 */     catch (JMSException e) {
/*  557 */       if (Trace.isOn) {
/*  558 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readFloat()", (Throwable)e, 1);
/*      */       }
/*      */ 
/*      */       
/*  562 */       this.providerStreamMessage.stepBack();
/*  563 */       if (Trace.isOn) {
/*  564 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readFloat()", (Throwable)e, 1);
/*      */       }
/*      */       
/*  567 */       throw e;
/*      */     
/*      */     }
/*  570 */     catch (RuntimeException e) {
/*  571 */       if (Trace.isOn) {
/*  572 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readFloat()", e, 2);
/*      */       }
/*      */ 
/*      */       
/*  576 */       this.providerStreamMessage.stepBack();
/*  577 */       if (Trace.isOn) {
/*  578 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readFloat()", e, 2);
/*      */       }
/*      */       
/*  581 */       throw e;
/*      */     } 
/*      */ 
/*      */     
/*  585 */     if (Trace.isOn) {
/*  586 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readFloat()", 
/*  587 */           Float.valueOf(result));
/*      */     }
/*  589 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double readDouble() throws JMSException {
/*      */     double result;
/*  597 */     if (Trace.isOn) {
/*  598 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readDouble()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  603 */     Object nextObj = getNextField("readDouble");
/*      */ 
/*      */     
/*      */     try {
/*  607 */       result = JmsPropertyContextImpl.parseDouble(nextObj, "", (Class)MessageFormatException.class);
/*      */     
/*      */     }
/*  610 */     catch (JMSException e) {
/*  611 */       if (Trace.isOn) {
/*  612 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readDouble()", (Throwable)e, 1);
/*      */       }
/*      */ 
/*      */       
/*  616 */       this.providerStreamMessage.stepBack();
/*  617 */       if (Trace.isOn) {
/*  618 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readDouble()", (Throwable)e, 1);
/*      */       }
/*      */       
/*  621 */       throw e;
/*      */     
/*      */     }
/*  624 */     catch (RuntimeException e) {
/*  625 */       if (Trace.isOn) {
/*  626 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readDouble()", e, 2);
/*      */       }
/*      */ 
/*      */       
/*  630 */       this.providerStreamMessage.stepBack();
/*  631 */       if (Trace.isOn) {
/*  632 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readDouble()", e, 2);
/*      */       }
/*      */       
/*  635 */       throw e;
/*      */     } 
/*      */ 
/*      */     
/*  639 */     if (Trace.isOn) {
/*  640 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readDouble()", 
/*  641 */           Double.valueOf(result));
/*      */     }
/*  643 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String readString() throws JMSException {
/*  651 */     if (Trace.isOn) {
/*  652 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readString()");
/*      */     }
/*      */     
/*  655 */     Object obj = getNextField("readString");
/*      */     
/*  657 */     String value = null;
/*      */     
/*  659 */     if (obj instanceof String || obj == null) {
/*  660 */       value = (String)obj;
/*      */     }
/*  662 */     else if (obj instanceof byte[]) {
/*      */ 
/*      */       
/*  665 */       this.providerStreamMessage.stepBack();
/*      */       
/*  667 */       throwBadConvertException(obj, "", "String", (Class)MessageFormatException.class);
/*      */     }
/*      */     else {
/*      */       
/*  671 */       value = obj.toString();
/*      */     } 
/*      */     
/*  674 */     if (Trace.isOn) {
/*  675 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readString()", value);
/*      */     }
/*      */     
/*  678 */     return value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int readBytes(byte[] value) throws JMSException {
/*      */     int nBytes;
/*  686 */     if (Trace.isOn) {
/*  687 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readBytes(byte [ ])", new Object[] { value });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  693 */     checkBodyReadable("readBytes");
/*      */ 
/*      */     
/*  696 */     if (!this.partReadBytesElement) {
/*      */ 
/*      */       
/*  699 */       Object obj = getNextField("readBytes");
/*      */       
/*  701 */       if (obj == null) {
/*      */ 
/*      */         
/*  704 */         int traceRet1 = -1;
/*  705 */         if (Trace.isOn) {
/*  706 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readBytes(byte [ ])", 
/*  707 */               Integer.valueOf(traceRet1), 1);
/*      */         }
/*  709 */         return traceRet1;
/*      */       } 
/*      */       
/*  712 */       if (obj instanceof byte[]) {
/*  713 */         this.bytesElement = (byte[])obj;
/*  714 */         this.bytesElementOffset = 0;
/*      */ 
/*      */         
/*  717 */         if (this.bytesElement.length == 0) {
/*  718 */           if (Trace.isOn) {
/*  719 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readBytes(byte [ ])", 
/*  720 */                 Integer.valueOf(0), 2);
/*      */           }
/*  722 */           return 0;
/*      */         }
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  728 */         this.providerStreamMessage.stepBack();
/*      */         
/*  730 */         throwBadConvertException(obj, "", "byte[]", (Class)MessageFormatException.class);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  735 */     if (value == null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  743 */       NullPointerException npe = (NullPointerException)JmsErrorUtils.createException("JMSCC0059", null);
/*      */       
/*  745 */       if (Trace.isOn) {
/*  746 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readBytes(byte [ ])", npe);
/*      */       }
/*      */       
/*  749 */       throw npe;
/*      */     } 
/*      */ 
/*      */     
/*  753 */     int nBytesLeft = this.bytesElement.length - this.bytesElementOffset;
/*  754 */     if (nBytesLeft > value.length) {
/*      */       
/*  756 */       nBytes = value.length;
/*      */     } else {
/*      */       
/*  759 */       nBytes = nBytesLeft;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  764 */     if (nBytes == value.length) {
/*      */       
/*  766 */       this.partReadBytesElement = true;
/*      */     } else {
/*  768 */       this.partReadBytesElement = false;
/*      */     } 
/*      */     
/*  771 */     if (nBytes == 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  776 */       int traceRet3 = -1;
/*  777 */       if (Trace.isOn) {
/*  778 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readBytes(byte [ ])", 
/*  779 */             Integer.valueOf(traceRet3), 3);
/*      */       }
/*  781 */       return traceRet3;
/*      */     } 
/*      */     
/*  784 */     System.arraycopy(this.bytesElement, this.bytesElementOffset, value, 0, nBytes);
/*      */     
/*  786 */     this.bytesElementOffset += nBytes;
/*  787 */     if (Trace.isOn) {
/*  788 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readBytes(byte [ ])", 
/*  789 */           Integer.valueOf(nBytes), 4);
/*      */     }
/*  791 */     return nBytes;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object readObject() throws JMSException {
/*      */     Object result;
/*  799 */     if (Trace.isOn) {
/*  800 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readObject()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  806 */     Object obj = getNextField("readObject");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  812 */     if (obj instanceof byte[]) {
/*  813 */       int len = ((byte[])obj).length;
/*  814 */       result = new byte[len];
/*  815 */       System.arraycopy(obj, 0, result, 0, len);
/*      */     } else {
/*  817 */       result = obj;
/*      */     } 
/*      */     
/*  820 */     if (Trace.isOn) {
/*  821 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "readObject()", result);
/*      */     }
/*      */     
/*  824 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeBoolean(boolean x) throws JMSException {
/*  832 */     if (Trace.isOn) {
/*  833 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "writeBoolean(boolean)", new Object[] {
/*  834 */             Boolean.valueOf(x)
/*      */           });
/*      */     }
/*  837 */     checkBodyWriteable("writeBoolean");
/*      */     
/*  839 */     this.providerStreamMessage.writeBoolean(x);
/*      */     
/*  841 */     if (Trace.isOn) {
/*  842 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "writeBoolean(boolean)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeByte(byte x) throws JMSException {
/*  853 */     if (Trace.isOn) {
/*  854 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "writeByte(byte)", new Object[] {
/*  855 */             Byte.valueOf(x)
/*      */           });
/*      */     }
/*  858 */     checkBodyWriteable("writeByte");
/*      */     
/*  860 */     this.providerStreamMessage.writeByte(x);
/*      */     
/*  862 */     if (Trace.isOn) {
/*  863 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "writeByte(byte)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeShort(short x) throws JMSException {
/*  873 */     if (Trace.isOn) {
/*  874 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "writeShort(short)", new Object[] {
/*  875 */             Short.valueOf(x)
/*      */           });
/*      */     }
/*  878 */     checkBodyWriteable("writeShort");
/*      */     
/*  880 */     this.providerStreamMessage.writeShort(x);
/*      */     
/*  882 */     if (Trace.isOn) {
/*  883 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "writeShort(short)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeChar(char x) throws JMSException {
/*  894 */     if (Trace.isOn) {
/*  895 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "writeChar(char)", new Object[] {
/*  896 */             Character.valueOf(x)
/*      */           });
/*      */     }
/*  899 */     checkBodyWriteable("writeChar");
/*      */     
/*  901 */     this.providerStreamMessage.writeChar(x);
/*      */     
/*  903 */     if (Trace.isOn) {
/*  904 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "writeChar(char)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeInt(int x) throws JMSException {
/*  914 */     if (Trace.isOn) {
/*  915 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "writeInt(int)", new Object[] {
/*  916 */             Integer.valueOf(x)
/*      */           });
/*      */     }
/*  919 */     checkBodyWriteable("writeInt");
/*      */     
/*  921 */     this.providerStreamMessage.writeInt(x);
/*      */     
/*  923 */     if (Trace.isOn) {
/*  924 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "writeInt(int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeLong(long x) throws JMSException {
/*  934 */     if (Trace.isOn) {
/*  935 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "writeLong(long)", new Object[] {
/*  936 */             Long.valueOf(x)
/*      */           });
/*      */     }
/*  939 */     checkBodyWriteable("writeLong");
/*      */     
/*  941 */     this.providerStreamMessage.writeLong(x);
/*      */     
/*  943 */     if (Trace.isOn) {
/*  944 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "writeLong(long)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeFloat(float x) throws JMSException {
/*  954 */     if (Trace.isOn) {
/*  955 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "writeFloat(float)", new Object[] {
/*  956 */             Float.valueOf(x)
/*      */           });
/*      */     }
/*  959 */     checkBodyWriteable("writeFloat");
/*      */     
/*  961 */     this.providerStreamMessage.writeFloat(x);
/*      */     
/*  963 */     if (Trace.isOn) {
/*  964 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "writeFloat(float)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeDouble(double x) throws JMSException {
/*  975 */     if (Trace.isOn) {
/*  976 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "writeDouble(double)", new Object[] {
/*  977 */             Double.valueOf(x)
/*      */           });
/*      */     }
/*  980 */     checkBodyWriteable("writeDouble");
/*      */     
/*  982 */     this.providerStreamMessage.writeDouble(x);
/*      */     
/*  984 */     if (Trace.isOn) {
/*  985 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "writeDouble(double)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeString(String x) throws JMSException {
/*  996 */     if (Trace.isOn) {
/*  997 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "writeString(String)", new Object[] { x });
/*      */     }
/*      */ 
/*      */     
/* 1001 */     checkBodyWriteable("writeString");
/*      */     
/* 1003 */     this.providerStreamMessage.writeString(x);
/*      */     
/* 1005 */     if (Trace.isOn) {
/* 1006 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "writeString(String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeBytes(byte[] xP) throws JMSException {
/* 1017 */     if (Trace.isOn) {
/* 1018 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "writeBytes(byte [ ])", new Object[] { xP });
/*      */     }
/*      */ 
/*      */     
/* 1022 */     byte[] x = xP;
/*      */     
/* 1024 */     checkBodyWriteable("writeBytes");
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1029 */     if (x != null) {
/* 1030 */       byte[] c = new byte[x.length];
/* 1031 */       System.arraycopy(x, 0, c, 0, x.length);
/* 1032 */       x = c;
/*      */     } 
/*      */     
/* 1035 */     this.providerStreamMessage.writeBytes(x);
/*      */     
/* 1037 */     if (Trace.isOn) {
/* 1038 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "writeBytes(byte [ ])");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeBytes(byte[] x, int offset, int len) throws JMSException {
/* 1049 */     if (Trace.isOn) {
/* 1050 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "writeBytes(byte [ ],int,int)", new Object[] { x, 
/* 1051 */             Integer.valueOf(offset), 
/* 1052 */             Integer.valueOf(len) });
/*      */     }
/*      */     
/* 1055 */     checkBodyWriteable("writeBytes");
/*      */ 
/*      */     
/* 1058 */     byte[] subA = new byte[len];
/* 1059 */     System.arraycopy(x, offset, subA, 0, len);
/*      */     
/* 1061 */     this.providerStreamMessage.writeBytes(subA);
/*      */     
/* 1063 */     if (Trace.isOn) {
/* 1064 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "writeBytes(byte [ ],int,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeObject(Object xP) throws JMSException {
/* 1075 */     if (Trace.isOn) {
/* 1076 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "writeObject(Object)", new Object[] { xP });
/*      */     }
/*      */ 
/*      */     
/* 1080 */     Object x = xP;
/*      */     
/* 1082 */     checkBodyWriteable("writeObject");
/*      */ 
/*      */     
/* 1085 */     if (x != null && !(x instanceof String) && !(x instanceof Number) && !(x instanceof Boolean) && !(x instanceof Character) && !(x instanceof byte[])) {
/*      */ 
/*      */ 
/*      */       
/* 1089 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1090 */       inserts.put("XMSC_INSERT_OBJECT", x.getClass().getName());
/* 1091 */       JMSException je2 = (JMSException)JmsErrorUtils.createException("JMSCC0083", inserts);
/*      */       
/* 1093 */       if (Trace.isOn) {
/* 1094 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "writeObject(Object)", (Throwable)je2);
/*      */       }
/*      */       
/* 1097 */       throw je2;
/*      */     } 
/*      */     
/* 1100 */     if (x instanceof byte[]) {
/*      */ 
/*      */ 
/*      */       
/* 1104 */       byte[] v = (byte[])x;
/* 1105 */       byte[] tmp = new byte[v.length];
/* 1106 */       System.arraycopy(v, 0, tmp, 0, v.length);
/* 1107 */       x = tmp;
/*      */     } 
/*      */     
/* 1110 */     this.providerStreamMessage.writeObject(x);
/*      */     
/* 1112 */     if (Trace.isOn) {
/* 1113 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "writeObject(Object)");
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
/*      */   public void reset() throws JMSException {
/* 1128 */     if (Trace.isOn) {
/* 1129 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "reset()");
/*      */     }
/*      */ 
/*      */     
/* 1133 */     setBodyReadOnly();
/*      */ 
/*      */     
/* 1136 */     this.providerStreamMessage.reset();
/*      */     
/* 1138 */     if (Trace.isOn) {
/* 1139 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "reset()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected ProviderMessage createProviderMessage(JmsSessionImpl session) throws Exception {
/* 1148 */     if (Trace.isOn) {
/* 1149 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "createProviderMessage(JmsSessionImpl)", new Object[] { session });
/*      */     }
/*      */ 
/*      */     
/* 1153 */     if (session != null) {
/* 1154 */       this.providerStreamMessage = getProviderMessageFactory().createStreamMessage(session
/* 1155 */           .getProviderSession());
/*      */     } else {
/* 1157 */       this.providerStreamMessage = getProviderMessageFactory().createStreamMessage(null);
/*      */     } 
/*      */     
/* 1160 */     if (Trace.isOn) {
/* 1161 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "createProviderMessage(JmsSessionImpl)", this.providerStreamMessage);
/*      */     }
/*      */     
/* 1164 */     return (ProviderMessage)this.providerStreamMessage;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int hashCode() {
/* 1173 */     if (Trace.isOn) {
/* 1174 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "hashCode()");
/*      */     }
/*      */     
/* 1177 */     if (this.providerStreamMessage == null) {
/* 1178 */       if (Trace.isOn) {
/* 1179 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "hashCode()", 
/* 1180 */             Integer.valueOf(0), 1);
/*      */       }
/* 1182 */       return 0;
/*      */     } 
/*      */     
/* 1185 */     int traceRet1 = this.providerStreamMessage.hashCode();
/* 1186 */     if (Trace.isOn) {
/* 1187 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "hashCode()", 
/* 1188 */           Integer.valueOf(traceRet1), 2);
/*      */     }
/* 1190 */     return traceRet1;
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
/*      */   
/*      */   public Object getNextField(String callingMethodName) throws MessageEOFException, MessageNotReadableException, MessageFormatException, JMSException {
/* 1209 */     if (Trace.isOn) {
/* 1210 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "getNextField(String)", new Object[] { callingMethodName });
/*      */     }
/*      */ 
/*      */     
/* 1214 */     checkBodyReadable(callingMethodName);
/*      */     
/* 1216 */     if (this.partReadBytesElement) {
/* 1217 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0061", null);
/*      */       
/* 1219 */       if (Trace.isOn) {
/* 1220 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "getNextField(String)", (Throwable)je, 1);
/*      */       }
/*      */       
/* 1223 */       throw je;
/*      */     } 
/*      */     
/* 1226 */     Object nextObj = null;
/*      */     
/* 1228 */     nextObj = this.providerStreamMessage.readObject();
/*      */     
/* 1230 */     if (nextObj == null)
/*      */     {
/*      */ 
/*      */       
/* 1234 */       if (!"readBytes".equals(callingMethodName) && !"readString".equals(callingMethodName) && 
/* 1235 */         !"readChar".equals(callingMethodName) && !"readObject".equals(callingMethodName)) {
/* 1236 */         JMSException je2 = (JMSException)JmsErrorUtils.createException("JMSCC0060", null);
/*      */         
/* 1238 */         if (Trace.isOn) {
/* 1239 */           Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "getNextField(String)", (Throwable)je2, 2);
/*      */         }
/*      */         
/* 1242 */         throw je2;
/*      */       } 
/*      */     }
/*      */     
/* 1246 */     if (Trace.isOn) {
/* 1247 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "getNextField(String)", nextObj);
/*      */     }
/*      */     
/* 1250 */     return nextObj;
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
/*      */   public String toString() {
/* 1262 */     if (Trace.isOn) {
/* 1263 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "toString()");
/*      */     }
/*      */     
/* 1266 */     String val = super.toString();
/*      */ 
/*      */     
/* 1269 */     StringBuffer sb = new StringBuffer("\n");
/*      */     try {
/* 1271 */       ArrayList<?> streamData = this.providerStreamMessage.getStreamData();
/* 1272 */       if (streamData != null) {
/* 1273 */         int size = streamData.size();
/* 1274 */         int unfoldSize = (size <= 32) ? size : 32;
/* 1275 */         for (int i = 0; i < unfoldSize; i++) {
/* 1276 */           Object value = streamData.get(i);
/*      */           
/* 1278 */           if (value == null) {
/* 1279 */             value = "<null>";
/*      */           }
/* 1281 */           sb.append(value);
/* 1282 */           sb.append("\n");
/*      */         } 
/*      */         
/* 1285 */         if (size > 32) {
/* 1286 */           sb.append("...\n");
/*      */         }
/*      */       }
/*      */     
/* 1290 */     } catch (JMSException e) {
/* 1291 */       if (Trace.isOn) {
/* 1292 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "toString()", (Throwable)e);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1299 */     val = val + sb.toString();
/*      */     
/* 1301 */     if (Trace.isOn) {
/* 1302 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsStreamMessageImpl", "toString()", val);
/*      */     }
/* 1304 */     return val;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsStreamMessageImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */