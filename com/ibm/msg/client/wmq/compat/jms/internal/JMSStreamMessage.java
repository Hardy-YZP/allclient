/*      */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*      */ 
/*      */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.provider.ProviderStreamMessage;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.nio.charset.CharacterCodingException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.StringTokenizer;
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
/*      */ public class JMSStreamMessage
/*      */   extends JMSMessage
/*      */   implements ProviderStreamMessage
/*      */ {
/*      */   static final long serialVersionUID = -3015681339377486635L;
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/JMSStreamMessage.java";
/*      */   private static final String STREAM_BODY_NAME = "stream";
/*      */   private static final String STREAM_BODY_CLOSE_TAG = "</stream>";
/*      */   private static final String STREAM_ELT_NAME = "elt";
/*      */   private StringBuffer dataWritten;
/*      */   private String streamBody;
/*      */   private transient StringTokenizer dataToBeRead;
/*      */   private transient Object currentField;
/*      */   
/*      */   static {
/*  100 */     if (Trace.isOn) {
/*  101 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/JMSStreamMessage.java");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private transient boolean retrying = false;
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
/*  150 */   private transient int readPos = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean readOnly = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  161 */   private ArrayList streamForToString = new ArrayList();
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
/*      */   public JMSStreamMessage(JMSStringResources jmsStrings) throws JMSException {
/*  174 */     if (Trace.isOn) {
/*  175 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "<init>(JMSStringResources)", new Object[] { jmsStrings });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  180 */     this.messageClass = "jms_stream";
/*      */ 
/*      */     
/*  183 */     this.jmsStrings = jmsStrings;
/*      */ 
/*      */ 
/*      */     
/*  187 */     clearBody();
/*      */     
/*  189 */     if (Trace.isOn) {
/*  190 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "<init>(JMSStringResources)");
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
/*      */   public byte[] _exportBody(int encoding, JmqiCodepage codepage) throws JMSException {
/*  209 */     if (Trace.isOn) {
/*  210 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "_exportBody(int,JmqiCodepage)", new Object[] {
/*  211 */             Integer.valueOf(encoding), codepage
/*      */           });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  219 */       StringBuffer body = new StringBuffer();
/*  220 */       if (this.dataWritten != null && this.streamBody == null) {
/*      */         
/*  222 */         body.append(this.dataWritten.toString());
/*      */         
/*  224 */         body.append("</stream>");
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*  229 */       else if (this.streamBody != null) {
/*  230 */         body.append(this.streamBody);
/*      */ 
/*      */         
/*  233 */         if (this.streamBody.indexOf("</stream>") == -1) {
/*  234 */           body.append("</stream>");
/*      */         }
/*      */       }
/*      */       else {
/*      */         
/*  239 */         Trace.ffst(this, "_exportBody(int,String)", "XO00G001", new HashMap<>(), JMSException.class);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  244 */       byte[] traceRet1 = codepage.stringToBytes(body.toString());
/*  245 */       if (Trace.isOn) {
/*  246 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "_exportBody(int,JmqiCodepage)", traceRet1);
/*      */       }
/*      */       
/*  249 */       return traceRet1;
/*      */     
/*      */     }
/*  252 */     catch (CharacterCodingException ex) {
/*  253 */       if (Trace.isOn) {
/*  254 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "_exportBody(int,JmqiCodepage)", ex);
/*      */       }
/*      */       
/*  257 */       JMSException jmsEx = newJMSException(1008, codepage);
/*  258 */       jmsEx.setLinkedException(ex);
/*  259 */       if (Trace.isOn) {
/*  260 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "_exportBody(int,JmqiCodepage)", (Throwable)jmsEx);
/*      */       }
/*      */       
/*  263 */       throw jmsEx;
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
/*      */   public void _importBody(byte[] wireformat, int dataStart, int encoding, JmqiCodepage codepage) throws JMSException {
/*  279 */     if (Trace.isOn) {
/*  280 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "_importBody(byte [ ],int,int,JmqiCodepage)", new Object[] { wireformat, 
/*      */             
/*  282 */             Integer.valueOf(dataStart), Integer.valueOf(encoding), codepage });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  287 */       this.streamBody = codepage.bytesToString(wireformat, dataStart, wireformat.length - dataStart);
/*      */ 
/*      */       
/*  290 */       parseStreamBodyForToString(this.streamBody);
/*      */ 
/*      */ 
/*      */       
/*  294 */       this.readOnly = true;
/*      */ 
/*      */ 
/*      */       
/*  298 */       reset();
/*      */     }
/*  300 */     catch (CharacterCodingException ex) {
/*  301 */       if (Trace.isOn) {
/*  302 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "_importBody(byte [ ],int,int,JmqiCodepage)", ex);
/*      */       }
/*      */       
/*  305 */       JMSException jmsEx = newJMSException(1008, codepage);
/*  306 */       jmsEx.setLinkedException(ex);
/*  307 */       if (Trace.isOn) {
/*  308 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "_importBody(byte [ ],int,int,JmqiCodepage)", (Throwable)jmsEx);
/*      */       }
/*      */       
/*  311 */       throw jmsEx;
/*      */     } 
/*      */     
/*  314 */     if (Trace.isOn) {
/*  315 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "_importBody(byte [ ],int,int,JmqiCodepage)");
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
/*      */   public void clearBody() throws JMSException {
/*  328 */     if (Trace.isOn) {
/*  329 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "clearBody()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  335 */     this.readOnly = false;
/*      */ 
/*      */ 
/*      */     
/*  339 */     this.dataToBeRead = null;
/*  340 */     this.streamBody = null;
/*  341 */     this.dataWritten = new StringBuffer("<stream>");
/*      */     
/*  343 */     if (Trace.isOn) {
/*  344 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "clearBody()");
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
/*      */   public boolean readBoolean() throws JMSException {
/*  361 */     if (Trace.isOn) {
/*  362 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "readBoolean()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  367 */     boolean retval = toBoolean(readField());
/*  368 */     this.retrying = false;
/*  369 */     if (Trace.isOn) {
/*  370 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "readBoolean()", 
/*  371 */           Boolean.valueOf(retval));
/*      */     }
/*  373 */     return retval;
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
/*      */   public byte readByte() throws JMSException {
/*  387 */     if (Trace.isOn) {
/*  388 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "readByte()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  393 */     byte retval = toByte(readField());
/*  394 */     this.retrying = false;
/*  395 */     if (Trace.isOn) {
/*  396 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "readByte()", 
/*  397 */           Byte.valueOf(retval));
/*      */     }
/*  399 */     return retval;
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
/*      */   public int readBytes(byte[] value) throws JMSException {
/*  436 */     if (Trace.isOn) {
/*  437 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "readBytes(byte [ ])", new Object[] { value });
/*      */     }
/*      */     
/*  440 */     byte[] msgValue = null;
/*      */ 
/*      */     
/*  443 */     msgValue = toBytes(readField());
/*      */ 
/*      */     
/*  446 */     if (msgValue == null) {
/*  447 */       this.retrying = false;
/*  448 */       int traceRet1 = -1;
/*  449 */       if (Trace.isOn) {
/*  450 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "readBytes(byte [ ])", 
/*  451 */             Integer.valueOf(traceRet1), 1);
/*      */       }
/*  453 */       return traceRet1;
/*      */     } 
/*      */     
/*  456 */     if (msgValue.length == 0) {
/*      */       
/*  458 */       if (value.length > 0) {
/*  459 */         this.retrying = false;
/*      */       }
/*  461 */       if (Trace.isOn) {
/*  462 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "readBytes(byte [ ])", 
/*  463 */             Integer.valueOf(0), 2);
/*      */       }
/*  465 */       return 0;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  471 */     int bytesLeft = msgValue.length - this.readPos;
/*      */ 
/*      */ 
/*      */     
/*  475 */     if (bytesLeft == 0) {
/*  476 */       this.retrying = false;
/*  477 */       this.readPos = 0;
/*  478 */       int traceRet2 = -1;
/*  479 */       if (Trace.isOn) {
/*  480 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "readBytes(byte [ ])", 
/*  481 */             Integer.valueOf(traceRet2), 3);
/*      */       }
/*  483 */       return traceRet2;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  492 */     if (value.length > bytesLeft) {
/*  493 */       System.arraycopy(msgValue, this.readPos, value, 0, bytesLeft);
/*  494 */       this.retrying = false;
/*  495 */       this.readPos = 0;
/*  496 */       if (Trace.isOn) {
/*  497 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "readBytes(byte [ ])", 
/*  498 */             Integer.valueOf(bytesLeft), 4);
/*      */       }
/*  500 */       return bytesLeft;
/*      */     } 
/*  502 */     System.arraycopy(msgValue, this.readPos, value, 0, value.length);
/*  503 */     this.readPos += value.length;
/*  504 */     if (Trace.isOn) {
/*  505 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "readBytes(byte [ ])", 
/*  506 */           Integer.valueOf(value.length), 5);
/*      */     }
/*  508 */     return value.length;
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
/*      */   public char readChar() throws JMSException {
/*  522 */     if (Trace.isOn) {
/*  523 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "readChar()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  528 */     char retval = toChar(readField());
/*  529 */     this.retrying = false;
/*  530 */     if (Trace.isOn) {
/*  531 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "readChar()", 
/*  532 */           Character.valueOf(retval));
/*      */     }
/*  534 */     return retval;
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
/*      */   public double readDouble() throws JMSException {
/*  548 */     if (Trace.isOn) {
/*  549 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "readDouble()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  554 */     double retval = toDouble(readField());
/*  555 */     this.retrying = false;
/*  556 */     if (Trace.isOn) {
/*  557 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "readDouble()", 
/*  558 */           Double.valueOf(retval));
/*      */     }
/*  560 */     return retval;
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
/*      */ 
/*      */ 
/*      */   
/*      */   private Object readField() throws JMSException {
/*  582 */     if (Trace.isOn) {
/*  583 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "readField()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*      */       String type;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  597 */       if (!this.readOnly) {
/*      */         
/*  599 */         JMSException traceRet1 = newMessageNotReadableException();
/*  600 */         if (Trace.isOn) {
/*  601 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "readField()", (Throwable)traceRet1, 1);
/*      */         }
/*      */         
/*  604 */         throw traceRet1;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  609 */       if (this.retrying == true) {
/*  610 */         if (Trace.isOn) {
/*  611 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "readField()", this.currentField, 1);
/*      */         }
/*      */         
/*  614 */         return this.currentField;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  619 */       String name = this.dataToBeRead.nextToken();
/*      */ 
/*      */ 
/*      */       
/*  623 */       if (name.equals("/stream")) {
/*  624 */         JMSException traceRet2 = newMessageEOFException();
/*  625 */         if (Trace.isOn) {
/*  626 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "readField()", (Throwable)traceRet2, 2);
/*      */         }
/*      */         
/*  629 */         throw traceRet2;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  636 */       this.currentField = null;
/*      */ 
/*      */ 
/*      */       
/*  640 */       this.retrying = true;
/*      */ 
/*      */       
/*  643 */       int nullIndex = 0;
/*  644 */       boolean shortForm = false;
/*  645 */       if (name.charAt(name.length() - 1) == '/') {
/*  646 */         shortForm = true;
/*      */       }
/*      */       
/*  649 */       nullIndex = name.indexOf(" xsi:nil");
/*  650 */       if (nullIndex != -1) {
/*      */         
/*  652 */         if (!shortForm) {
/*      */           
/*  654 */           String str = this.dataToBeRead.nextToken();
/*  655 */           if (str.charAt(0) != '/')
/*      */           {
/*  657 */             str = this.dataToBeRead.nextToken();
/*      */           }
/*  659 */           if (str.charAt(0) != '/') {
/*      */             
/*  661 */             JMSException traceRet3 = newMessageFormatException(1010);
/*  662 */             if (Trace.isOn) {
/*  663 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "readField()", (Throwable)traceRet3, 3);
/*      */             }
/*      */             
/*  666 */             throw traceRet3;
/*      */           } 
/*      */         } 
/*  669 */         if (Trace.isOn) {
/*  670 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "readField()", null, 2);
/*      */         }
/*      */         
/*  673 */         return null;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  678 */       int index = name.indexOf(" dt=");
/*  679 */       if (index != -1) {
/*  680 */         type = name.substring(index + 4);
/*      */       } else {
/*      */         
/*  683 */         type = "'string'";
/*      */       } 
/*      */       
/*  686 */       if (shortForm) {
/*  687 */         this.currentField = deformatElement(type, "");
/*  688 */         if (Trace.isOn) {
/*  689 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "readField()", this.currentField, 3);
/*      */         }
/*      */         
/*  692 */         return this.currentField;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  697 */       String value = this.dataToBeRead.nextToken();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  702 */       if (value.charAt(0) == '/') {
/*  703 */         this.currentField = deformatElement(type, "");
/*  704 */         if (Trace.isOn) {
/*  705 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "readField()", this.currentField, 4);
/*      */         }
/*      */         
/*  708 */         return this.currentField;
/*      */       } 
/*      */       
/*  711 */       String token = this.dataToBeRead.nextToken();
/*  712 */       if (token.charAt(0) != '/') {
/*  713 */         JMSException traceRet4 = newMessageFormatException(1010);
/*  714 */         if (Trace.isOn) {
/*  715 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "readField()", (Throwable)traceRet4, 4);
/*      */         }
/*      */         
/*  718 */         throw traceRet4;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  723 */       this.currentField = deformatElement(type, value);
/*  724 */       if (Trace.isOn) {
/*  725 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "readField()", this.currentField, 5);
/*      */       }
/*      */       
/*  728 */       return this.currentField;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*  734 */     catch (NoSuchElementException ex) {
/*  735 */       if (Trace.isOn) {
/*  736 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "readField()", ex);
/*      */       }
/*      */       
/*  739 */       JMSException traceRet5 = newMessageEOFException();
/*  740 */       if (Trace.isOn) {
/*  741 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "readField()", (Throwable)traceRet5, 5);
/*      */       }
/*      */       
/*  744 */       throw traceRet5;
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
/*      */   public float readFloat() throws JMSException {
/*  759 */     if (Trace.isOn) {
/*  760 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "readFloat()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  765 */     float retval = toFloat(readField());
/*  766 */     this.retrying = false;
/*  767 */     if (Trace.isOn) {
/*  768 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "readFloat()", 
/*  769 */           Float.valueOf(retval));
/*      */     }
/*  771 */     return retval;
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
/*      */   public int readInt() throws JMSException {
/*  785 */     if (Trace.isOn) {
/*  786 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "readInt()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  791 */     int retval = toInt(readField());
/*  792 */     this.retrying = false;
/*  793 */     if (Trace.isOn) {
/*  794 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "readInt()", 
/*  795 */           Integer.valueOf(retval));
/*      */     }
/*  797 */     return retval;
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
/*      */   public long readLong() throws JMSException {
/*  811 */     if (Trace.isOn) {
/*  812 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "readLong()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  817 */     long retval = toLong(readField());
/*  818 */     this.retrying = false;
/*  819 */     if (Trace.isOn) {
/*  820 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "readLong()", 
/*  821 */           Long.valueOf(retval));
/*      */     }
/*  823 */     return retval;
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
/*      */   public Object readObject() throws JMSException {
/*  841 */     if (Trace.isOn) {
/*  842 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "readObject()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  847 */     Object retval = readField();
/*  848 */     this.retrying = false;
/*  849 */     if (Trace.isOn) {
/*  850 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "readObject()", retval);
/*      */     }
/*      */     
/*  853 */     return retval;
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
/*      */   public short readShort() throws JMSException {
/*  867 */     if (Trace.isOn) {
/*  868 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "readShort()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  873 */     short retval = toShort(readField());
/*  874 */     this.retrying = false;
/*  875 */     if (Trace.isOn) {
/*  876 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "readShort()", 
/*  877 */           Short.valueOf(retval));
/*      */     }
/*  879 */     return retval;
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
/*      */   public String readString() throws JMSException {
/*  893 */     if (Trace.isOn) {
/*  894 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "readString()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  899 */     Object fieldValue = readField();
/*      */ 
/*      */     
/*  902 */     if (fieldValue instanceof byte[]) {
/*  903 */       JMSException traceRet1 = newMessageFormatException(1011);
/*  904 */       if (Trace.isOn) {
/*  905 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "readString()", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  908 */       throw traceRet1;
/*      */     } 
/*      */     
/*  911 */     this.retrying = false;
/*  912 */     if (fieldValue == null) {
/*  913 */       if (Trace.isOn) {
/*  914 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "readString()", null, 1);
/*      */       }
/*      */       
/*  917 */       return null;
/*      */     } 
/*      */     
/*  920 */     String traceRet2 = fieldValue.toString();
/*  921 */     if (Trace.isOn) {
/*  922 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "readString()", traceRet2, 2);
/*      */     }
/*      */     
/*  925 */     return traceRet2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void reset() throws JMSException {
/*  936 */     if (Trace.isOn) {
/*  937 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "reset()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  945 */     if (!this.readOnly) {
/*  946 */       this.dataWritten.append("</stream>");
/*  947 */       this.streamBody = this.dataWritten.toString();
/*  948 */       this.dataWritten = null;
/*  949 */       this.readOnly = true;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  954 */     this.dataToBeRead = new StringTokenizer(this.streamBody, "<>");
/*  955 */     this.retrying = false;
/*      */ 
/*      */ 
/*      */     
/*  959 */     if (!this.dataToBeRead.nextToken().equals("stream")) {
/*  960 */       JMSException traceRet1 = newMessageFormatException(1010);
/*  961 */       if (Trace.isOn) {
/*  962 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "reset()", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  965 */       throw traceRet1;
/*      */     } 
/*      */     
/*  968 */     if (Trace.isOn) {
/*  969 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "reset()");
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
/*      */   public void writeBoolean(boolean value) throws JMSException {
/*  985 */     if (Trace.isOn) {
/*  986 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "writeBoolean(boolean)", new Object[] {
/*  987 */             Boolean.valueOf(value)
/*      */           });
/*      */     }
/*  990 */     writeObject(Boolean.valueOf(value));
/*  991 */     if (Trace.isOn) {
/*  992 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "writeBoolean(boolean)");
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
/*      */   public void writeByte(byte value) throws JMSException {
/* 1008 */     if (Trace.isOn) {
/* 1009 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "writeByte(byte)", new Object[] {
/* 1010 */             Byte.valueOf(value)
/*      */           });
/*      */     }
/*      */ 
/*      */     
/* 1015 */     if (this.readOnly == true) {
/*      */       
/* 1017 */       JMSException traceRet1 = newMessageNotWriteableException();
/* 1018 */       if (Trace.isOn) {
/* 1019 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "writeByte(byte)", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 1022 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/* 1026 */     formatElement("elt", "'i1'", String.valueOf(value), this.dataWritten);
/*      */     
/* 1028 */     this.streamForToString.add(Byte.valueOf(value));
/*      */     
/* 1030 */     if (Trace.isOn) {
/* 1031 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "writeByte(byte)");
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
/*      */   public void writeBytes(byte[] value) throws JMSException {
/* 1047 */     if (Trace.isOn) {
/* 1048 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "writeBytes(byte [ ])", new Object[] { value });
/*      */     }
/*      */ 
/*      */     
/* 1052 */     writeObject(value);
/* 1053 */     if (Trace.isOn) {
/* 1054 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "writeBytes(byte [ ])");
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
/*      */   public void writeBytes(byte[] value, int offset, int length) throws JMSException {
/* 1072 */     if (Trace.isOn) {
/* 1073 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "writeBytes(byte [ ],int,int)", new Object[] { value, 
/* 1074 */             Integer.valueOf(offset), 
/* 1075 */             Integer.valueOf(length) });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1084 */     if (this.readOnly == true) {
/*      */       
/* 1086 */       JMSException traceRet1 = newMessageNotWriteableException();
/* 1087 */       if (Trace.isOn) {
/* 1088 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "writeBytes(byte [ ],int,int)", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 1091 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/* 1095 */     this.dataWritten.append("<elt dt='bin.hex'>");
/* 1096 */     binToHex(value, offset, length, this.dataWritten);
/* 1097 */     this.dataWritten.append("</elt>");
/*      */     
/* 1099 */     byte[] bytes = new byte[length];
/* 1100 */     System.arraycopy(value, offset, bytes, 0, length);
/* 1101 */     this.streamForToString.add(bytes);
/*      */     
/* 1103 */     if (Trace.isOn) {
/* 1104 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "writeBytes(byte [ ],int,int)");
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
/*      */   public void writeChar(char value) throws JMSException {
/* 1120 */     if (Trace.isOn) {
/* 1121 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "writeChar(char)", new Object[] {
/* 1122 */             Character.valueOf(value)
/*      */           });
/*      */     }
/* 1125 */     writeObject(Character.valueOf(value));
/* 1126 */     if (Trace.isOn) {
/* 1127 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "writeChar(char)");
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
/*      */   public void writeDouble(double value) throws JMSException {
/* 1143 */     if (Trace.isOn) {
/* 1144 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "writeDouble(double)", new Object[] {
/* 1145 */             Double.valueOf(value)
/*      */           });
/*      */     }
/*      */     
/* 1149 */     if (this.readOnly == true) {
/*      */       
/* 1151 */       JMSException traceRet1 = newMessageNotWriteableException();
/* 1152 */       if (Trace.isOn) {
/* 1153 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "writeDouble(double)", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 1156 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/* 1160 */     formatElement("elt", "'r8'", String.valueOf(value), this.dataWritten);
/*      */     
/* 1162 */     this.streamForToString.add(Double.valueOf(value));
/*      */     
/* 1164 */     if (Trace.isOn) {
/* 1165 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "writeDouble(double)");
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
/*      */   public void writeFloat(float value) throws JMSException {
/* 1181 */     if (Trace.isOn) {
/* 1182 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "writeFloat(float)", new Object[] {
/* 1183 */             Float.valueOf(value)
/*      */           });
/*      */     }
/*      */     
/* 1187 */     if (this.readOnly == true) {
/*      */       
/* 1189 */       JMSException traceRet1 = newMessageNotWriteableException();
/* 1190 */       if (Trace.isOn) {
/* 1191 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "writeFloat(float)", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 1194 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/* 1198 */     formatElement("elt", "'r4'", String.valueOf(value), this.dataWritten);
/*      */     
/* 1200 */     this.streamForToString.add(Float.valueOf(value));
/*      */     
/* 1202 */     if (Trace.isOn) {
/* 1203 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "writeFloat(float)");
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
/*      */   public void writeInt(int value) throws JMSException {
/* 1219 */     if (Trace.isOn) {
/* 1220 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "writeInt(int)", new Object[] {
/* 1221 */             Integer.valueOf(value)
/*      */           });
/*      */     }
/*      */     
/* 1225 */     if (this.readOnly == true) {
/*      */       
/* 1227 */       JMSException traceRet1 = newMessageNotWriteableException();
/* 1228 */       if (Trace.isOn) {
/* 1229 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "writeInt(int)", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 1232 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/* 1236 */     formatElement("elt", "'i4'", String.valueOf(value), this.dataWritten);
/*      */     
/* 1238 */     this.streamForToString.add(Integer.valueOf(value));
/*      */     
/* 1240 */     if (Trace.isOn) {
/* 1241 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "writeInt(int)");
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
/*      */   public void writeLong(long value) throws JMSException {
/* 1257 */     if (Trace.isOn) {
/* 1258 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "writeLong(long)", new Object[] {
/* 1259 */             Long.valueOf(value)
/*      */           });
/*      */     }
/*      */     
/* 1263 */     if (this.readOnly == true) {
/*      */       
/* 1265 */       JMSException traceRet1 = newMessageNotWriteableException();
/* 1266 */       if (Trace.isOn) {
/* 1267 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "writeLong(long)", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 1270 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/* 1274 */     formatElement("elt", "'i8'", String.valueOf(value), this.dataWritten);
/*      */     
/* 1276 */     this.streamForToString.add(Long.valueOf(value));
/*      */     
/* 1278 */     if (Trace.isOn) {
/* 1279 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "writeLong(long)");
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
/*      */ 
/*      */   
/*      */   public void writeObject(Object value) throws JMSException {
/* 1300 */     if (Trace.isOn) {
/* 1301 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "writeObject(Object)", new Object[] { value });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1306 */     if (this.readOnly == true) {
/*      */       
/* 1308 */       JMSException traceRet1 = newMessageNotWriteableException();
/* 1309 */       if (Trace.isOn) {
/* 1310 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "writeObject(Object)", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/* 1313 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/* 1317 */     if (value != null && !(value instanceof String) && !(value instanceof Number) && !(value instanceof Boolean) && !(value instanceof Character) && !(value instanceof byte[])) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1323 */       JMSException traceRet2 = newMessageFormatException(1018, value.getClass());
/* 1324 */       if (Trace.isOn) {
/* 1325 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "writeObject(Object)", (Throwable)traceRet2, 2);
/*      */       }
/*      */       
/* 1328 */       throw traceRet2;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1333 */     formatElement("elt", value, this.dataWritten);
/*      */     
/* 1335 */     this.streamForToString.add(value);
/*      */     
/* 1337 */     if (Trace.isOn) {
/* 1338 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "writeObject(Object)");
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
/*      */   public void writeShort(short value) throws JMSException {
/* 1354 */     if (Trace.isOn) {
/* 1355 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "writeShort(short)", new Object[] {
/* 1356 */             Short.valueOf(value)
/*      */           });
/*      */     }
/*      */ 
/*      */     
/* 1361 */     if (this.readOnly == true) {
/*      */       
/* 1363 */       JMSException traceRet1 = newMessageNotWriteableException();
/* 1364 */       if (Trace.isOn) {
/* 1365 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "writeShort(short)", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 1368 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/* 1372 */     formatElement("elt", "'i2'", String.valueOf(value), this.dataWritten);
/*      */     
/* 1374 */     this.streamForToString.add(Short.valueOf(value));
/*      */     
/* 1376 */     if (Trace.isOn) {
/* 1377 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "writeShort(short)");
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
/*      */   public void writeString(String value) throws JMSException {
/* 1393 */     if (Trace.isOn) {
/* 1394 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "writeString(String)", new Object[] { value });
/*      */     }
/*      */ 
/*      */     
/* 1398 */     writeObject(value);
/* 1399 */     if (Trace.isOn) {
/* 1400 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "writeString(String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void _setBodyReadOnly() {
/* 1409 */     if (Trace.isOn) {
/* 1410 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "_setBodyReadOnly()");
/*      */     }
/*      */     
/* 1413 */     this.readOnly = true;
/* 1414 */     if (Trace.isOn) {
/* 1415 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "_setBodyReadOnly()");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 1440 */     if (Trace.isOn) {
/* 1441 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "readObject(java.io.ObjectInputStream)", new Object[] { in });
/*      */     }
/*      */     
/* 1444 */     in.defaultReadObject();
/*      */ 
/*      */     
/* 1447 */     if (this.messageClass.equals("jms_stream")) {
/* 1448 */       this.messageClass = "jms_stream";
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1455 */     if (this.readOnly) {
/* 1456 */       this.currentField = null;
/* 1457 */       this.retrying = false;
/* 1458 */       this.readPos = 0;
/* 1459 */       this.dataToBeRead = new StringTokenizer(this.streamBody, "<>");
/*      */     } 
/* 1461 */     if (Trace.isOn) {
/* 1462 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "readObject(java.io.ObjectInputStream)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void stepBack() throws JMSException {
/* 1473 */     if (Trace.isOn) {
/* 1474 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "stepBack()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1481 */     this.retrying = true;
/* 1482 */     if (Trace.isOn) {
/* 1483 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "stepBack()");
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
/*      */   public ArrayList getStreamData() throws JMSException {
/* 1495 */     if (Trace.isOn) {
/* 1496 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "getStreamData()", "getter", this.streamForToString);
/*      */     }
/*      */     
/* 1499 */     return this.streamForToString;
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
/*      */   private void parseStreamBodyForToString(String streamBody) throws JMSException {
/* 1512 */     if (Trace.isOn) {
/* 1513 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "parseStreamBodyForToString(String)", new Object[] { streamBody });
/*      */     }
/*      */ 
/*      */     
/* 1517 */     StringTokenizer dataToBeRead = new StringTokenizer(streamBody, "<>");
/*      */ 
/*      */     
/* 1520 */     if (!dataToBeRead.nextToken().equals("stream")) {
/* 1521 */       JMSException je = newMessageFormatException(1010);
/* 1522 */       if (Trace.isOn) {
/* 1523 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "parseStreamBodyForToString(String)", (Throwable)je, 1);
/*      */       }
/*      */       
/* 1526 */       throw je;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1531 */     String name = dataToBeRead.nextToken();
/* 1532 */     String value = null;
/* 1533 */     String token = null;
/* 1534 */     Object currentField = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1540 */       while (!name.equals("/stream"))
/*      */       {
/*      */         
/* 1543 */         boolean shortForm = (name.charAt(name.length() - 1) == '/');
/*      */ 
/*      */         
/* 1546 */         int nullIndex = name.indexOf(" xsi:nil");
/* 1547 */         if (nullIndex != -1) {
/*      */           
/* 1549 */           int spaceIndex = name.indexOf(" ");
/* 1550 */           name = name.substring(0, spaceIndex);
/*      */ 
/*      */ 
/*      */           
/* 1554 */           if (!shortForm) {
/* 1555 */             token = dataToBeRead.nextToken();
/* 1556 */             if (token.charAt(0) != '/')
/*      */             {
/*      */               
/* 1559 */               token = dataToBeRead.nextToken();
/*      */             }
/*      */ 
/*      */             
/* 1563 */             if (token.charAt(0) != '/') {
/*      */               
/* 1565 */               JMSException je2 = newMessageFormatException(1010);
/* 1566 */               if (Trace.isOn) {
/* 1567 */                 Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "parseStreamBodyForToString(String)", (Throwable)je2, 2);
/*      */               }
/*      */ 
/*      */               
/* 1571 */               throw je2;
/*      */             } 
/*      */           } 
/*      */           
/* 1575 */           this.streamForToString.add(null);
/*      */ 
/*      */           
/* 1578 */           name = dataToBeRead.nextToken();
/*      */ 
/*      */           
/*      */           continue;
/*      */         } 
/*      */ 
/*      */         
/* 1585 */         int index = name.indexOf(" dt=");
/* 1586 */         String type = "";
/* 1587 */         if (index != -1) {
/* 1588 */           type = name.substring(index + 4);
/* 1589 */           name = name.substring(0, index);
/*      */         } else {
/*      */           
/* 1592 */           type = "'string'";
/*      */         } 
/*      */         
/* 1595 */         if (shortForm) {
/* 1596 */           currentField = deformatElement(type, "");
/* 1597 */           this.streamForToString.add(currentField);
/*      */ 
/*      */           
/* 1600 */           name = dataToBeRead.nextToken();
/*      */           
/*      */           continue;
/*      */         } 
/* 1604 */         value = dataToBeRead.nextToken();
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1609 */         if (value.charAt(0) == '/') {
/* 1610 */           currentField = deformatElement(type, "");
/* 1611 */           this.streamForToString.add(currentField);
/*      */         }
/*      */         else {
/*      */           
/* 1615 */           token = dataToBeRead.nextToken();
/* 1616 */           if (token.charAt(0) != '/') {
/* 1617 */             JMSException je = newMessageFormatException(1010);
/* 1618 */             if (Trace.isOn) {
/* 1619 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "parseStreamBodyForToString(String)", (Throwable)je, 3);
/*      */             }
/*      */             
/* 1622 */             throw je;
/*      */           } 
/*      */ 
/*      */           
/* 1626 */           currentField = deformatElement(type, value);
/* 1627 */           this.streamForToString.add(currentField);
/*      */         } 
/*      */         
/* 1630 */         name = dataToBeRead.nextToken();
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */     
/*      */     }
/* 1637 */     catch (NoSuchElementException ex) {
/* 1638 */       if (Trace.isOn) {
/* 1639 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "parseStreamBodyForToString(String)", ex);
/*      */       }
/*      */       
/* 1642 */       JMSException je = newMessageFormatException(1010);
/* 1643 */       je.setLinkedException(ex);
/* 1644 */       if (Trace.isOn) {
/* 1645 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "parseStreamBodyForToString(String)", (Throwable)je, 4);
/*      */       }
/*      */       
/* 1648 */       throw je;
/*      */     } 
/* 1650 */     if (Trace.isOn) {
/* 1651 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "parseStreamBodyForToString(String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasBody() {
/* 1659 */     if (Trace.isOn) {
/* 1660 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "hasBody()");
/*      */     }
/*      */     
/* 1663 */     if (Trace.isOn) {
/* 1664 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "hasBody()", 
/* 1665 */           Boolean.valueOf(false));
/*      */     }
/* 1667 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public long getJMSDeliveryTime() throws JMSException {
/* 1673 */     if (Trace.isOn) {
/* 1674 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "getJMSDeliveryTime()", "getter", 
/* 1675 */           Long.valueOf(0L));
/*      */     }
/* 1677 */     return 0L;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setJMSDeliveryTime(long deliveryTime) throws JMSException {
/* 1682 */     if (Trace.isOn)
/* 1683 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage", "setJMSDeliveryTime(long)", "setter", 
/* 1684 */           Long.valueOf(deliveryTime)); 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\JMSStreamMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */