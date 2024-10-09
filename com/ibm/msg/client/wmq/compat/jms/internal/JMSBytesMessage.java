/*      */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*      */ 
/*      */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.provider.ProviderBytesMessage;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.DataOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.util.HashMap;
/*      */ import javax.jms.JMSException;
/*      */ import javax.jms.MessageNotReadableException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class JMSBytesMessage
/*      */   extends JMSMessage
/*      */   implements ProviderBytesMessage
/*      */ {
/*      */   static final long serialVersionUID = -8017520360760128818L;
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/JMSBytesMessage.java";
/*      */   public static final int ENC_INTEGER_MASK = 15;
/*      */   public static final int ENC_FLOAT_MASK = 3840;
/*      */   public static final int ENC_INTEGER_UNDEFINED = 0;
/*      */   public static final int ENC_INTEGER_NORMAL = 1;
/*      */   public static final int ENC_INTEGER_REVERSED = 2;
/*      */   public static final int ENC_FLOAT_UNDEFINED = 0;
/*      */   public static final int ENC_FLOAT_IEEE_NORMAL = 256;
/*      */   public static final int ENC_FLOAT_IEEE_REVERSED = 512;
/*      */   public static final int ENC_FLOAT_S390 = 768;
/*      */   private transient ByteArrayOutputStream _writeBytes;
/*      */   private transient DataOutputStream writeStream;
/*      */   private byte[] dataBuffer;
/*      */   private int dataStart;
/*      */   private transient ByteArrayInputStream readStream;
/*      */   private transient ByteArrayInputStream readLengthStream;
/*      */   
/*      */   static {
/*  128 */     if (Trace.isOn) {
/*  129 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/JMSBytesMessage.java");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean lengthCheck = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JMSBytesMessage(JMSStringResources jmsStrings) throws JMSException {
/*  216 */     if (Trace.isOn) {
/*  217 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "<init>(JMSStringResources)", new Object[] { jmsStrings });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  222 */     this.messageClass = "jms_bytes";
/*      */ 
/*      */     
/*  225 */     this.jmsStrings = jmsStrings;
/*      */ 
/*      */     
/*  228 */     clearBody();
/*  229 */     if (Trace.isOn) {
/*  230 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "<init>(JMSStringResources)");
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
/*      */   public byte[] _exportBody(int encoding, JmqiCodepage codepage) throws JMSException {
/*  253 */     if (Trace.isOn) {
/*  254 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "_exportBody(int,JmqiCodepage)", new Object[] {
/*  255 */             Integer.valueOf(encoding), codepage
/*      */           });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  263 */     getReadStream(false);
/*  264 */     byte[] buff = new byte[this.dataBuffer.length - this.dataStart];
/*  265 */     System.arraycopy(this.dataBuffer, this.dataStart, buff, 0, this.dataBuffer.length - this.dataStart);
/*  266 */     if (Trace.isOn) {
/*  267 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "_exportBody(int,JmqiCodepage)", buff);
/*      */     }
/*      */     
/*  270 */     return buff;
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
/*      */   public void _importBody(byte[] wireformat, int dataStart, int encoding, JmqiCodepage codepage) throws JMSException {
/*  286 */     if (Trace.isOn) {
/*  287 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "_importBody(byte [ ],int,int,JmqiCodepage)", new Object[] { wireformat, 
/*      */             
/*  289 */             Integer.valueOf(dataStart), Integer.valueOf(encoding), codepage });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  294 */     getReadStream(true);
/*      */ 
/*      */     
/*  297 */     this.dataBuffer = wireformat;
/*  298 */     this.dataStart = dataStart;
/*      */     
/*  300 */     _setJMSXObjectProperty("JMS_IBM_Encoding", Integer.valueOf(encoding));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  306 */     String exposedCharSet = codepage.getCharsetName();
/*      */ 
/*      */     
/*  309 */     _setJMSXObjectProperty("JMS_IBM_Character_Set", exposedCharSet);
/*      */ 
/*      */     
/*  312 */     reset();
/*  313 */     if (Trace.isOn) {
/*  314 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "_importBody(byte [ ],int,int,JmqiCodepage)");
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
/*      */   public void clearBody() throws JMSException {
/*  328 */     if (Trace.isOn) {
/*  329 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "clearBody()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  335 */     this.dataBuffer = null;
/*  336 */     this.dataStart = 0;
/*  337 */     this.readStream = null;
/*  338 */     this.readLengthStream = null;
/*  339 */     this._writeBytes = new ByteArrayOutputStream();
/*  340 */     this.writeStream = new DataOutputStream(this._writeBytes);
/*      */     
/*  342 */     if (Trace.isOn) {
/*  343 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "clearBody()");
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
/*      */   public int readBytes(byte[] value) throws JMSException {
/*  363 */     if (Trace.isOn) {
/*  364 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "readBytes(byte [ ])", new Object[] { value });
/*      */     }
/*      */     
/*  367 */     int traceRet1 = readBytes(value, value.length);
/*  368 */     if (Trace.isOn) {
/*  369 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "readBytes(byte [ ])", 
/*  370 */           Integer.valueOf(traceRet1));
/*      */     }
/*  372 */     return traceRet1;
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
/*      */   public int readBytes(byte[] value, int length) throws JMSException {
/*  390 */     if (Trace.isOn) {
/*  391 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "readBytes(byte [ ],int)", new Object[] { value, 
/*  392 */             Integer.valueOf(length) });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  398 */     if (value.length < length || length < 0) {
/*  399 */       IndexOutOfBoundsException traceRet2 = new IndexOutOfBoundsException();
/*  400 */       if (Trace.isOn) {
/*  401 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "readBytes(byte [ ],int)", traceRet2);
/*      */       }
/*      */       
/*  404 */       throw traceRet2;
/*      */     } 
/*      */     
/*  407 */     checkReadStream();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  416 */     int traceRet4 = this.readStream.read(value, 0, length);
/*  417 */     if (Trace.isOn) {
/*  418 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "readBytes(byte [ ],int)", 
/*  419 */           Integer.valueOf(traceRet4));
/*      */     }
/*  421 */     return traceRet4;
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
/*  432 */     if (Trace.isOn) {
/*  433 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "reset()");
/*      */     }
/*  435 */     getReadStream(true);
/*  436 */     if (Trace.isOn) {
/*  437 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "reset()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void getReadStream(boolean removeWriteStream) throws JMSException {
/*  447 */     if (Trace.isOn) {
/*  448 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "getReadStream(boolean)", new Object[] {
/*  449 */             Boolean.valueOf(removeWriteStream)
/*      */           });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  457 */     if (this.writeStream != null && this._writeBytes != null) {
/*      */       
/*  459 */       this.dataBuffer = this._writeBytes.toByteArray();
/*  460 */       this.dataStart = 0;
/*      */       
/*  462 */       if (removeWriteStream) {
/*      */         
/*  464 */         this.writeStream = null;
/*  465 */         this._writeBytes = null;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  472 */     if (this.dataBuffer == null) {
/*  473 */       this.dataBuffer = new byte[0];
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  481 */     this.readStream = new ByteArrayInputStream(this.dataBuffer);
/*  482 */     this.readLengthStream = new ByteArrayInputStream(this.dataBuffer);
/*      */ 
/*      */     
/*  485 */     this.readStream.skip(this.dataStart);
/*  486 */     this.readLengthStream.skip(this.dataStart);
/*  487 */     if (Trace.isOn) {
/*  488 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "getReadStream(boolean)");
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
/*      */   public String toString() {
/*  502 */     if (Trace.isOn) {
/*  503 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "toString()");
/*      */     }
/*      */ 
/*      */     
/*  507 */     int MAX_LINES = 10;
/*  508 */     int LINE_LENGTH = 40;
/*      */ 
/*      */     
/*  511 */     StringBuffer retval = new StringBuffer();
/*      */     
/*  513 */     Object encoding = null;
/*      */     try {
/*  515 */       encoding = getObjectProperty("JMS_IBM_Encoding");
/*      */     }
/*  517 */     catch (JMSException e) {
/*  518 */       if (Trace.isOn) {
/*  519 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "toString()", (Throwable)e);
/*      */       }
/*      */ 
/*      */       
/*  523 */       if (Trace.isOn) {
/*  524 */         Trace.data(this, "toString", "Exception reading encoding prop - ignoring and using defaults");
/*      */       }
/*      */     } 
/*      */     
/*  528 */     int integerEncoding = (encoding instanceof Integer) ? (((Integer)encoding).intValue() & 0xF) : 1;
/*  529 */     int floatEncoding = (encoding instanceof Integer) ? (((Integer)encoding).intValue() & 0xF00) : 256;
/*      */ 
/*      */     
/*  532 */     retval.append(super.toString() + "\n");
/*  533 */     retval.append(this.jmsStrings.getMessage(1027, 
/*  534 */           Integer.valueOf(integerEncoding), 
/*  535 */           Integer.valueOf(floatEncoding)));
/*  536 */     retval.append("\n");
/*      */ 
/*      */     
/*  539 */     if (this._writeBytes != null) {
/*  540 */       this.dataBuffer = this._writeBytes.toByteArray();
/*      */     }
/*      */     
/*  543 */     if (this.dataBuffer != null) {
/*      */ 
/*      */       
/*  546 */       int curpos = this.dataStart;
/*  547 */       int lines_written = 0;
/*      */ 
/*      */       
/*  550 */       while (lines_written < MAX_LINES) {
/*  551 */         if (this.dataBuffer.length <= curpos + LINE_LENGTH) {
/*      */           
/*  553 */           binToHex(this.dataBuffer, curpos, this.dataBuffer.length - curpos, retval);
/*  554 */           curpos = this.dataBuffer.length;
/*  555 */           retval.append("\n");
/*      */           
/*      */           break;
/*      */         } 
/*  559 */         binToHex(this.dataBuffer, curpos, LINE_LENGTH, retval);
/*  560 */         retval.append("\n");
/*  561 */         curpos += LINE_LENGTH;
/*  562 */         lines_written++;
/*      */       } 
/*      */ 
/*      */       
/*  566 */       if (curpos != this.dataBuffer.length) {
/*  567 */         retval.append("...\n");
/*      */       }
/*      */     } 
/*      */     
/*  571 */     String traceRet1 = retval.toString();
/*  572 */     if (Trace.isOn) {
/*  573 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "toString()", traceRet1);
/*      */     }
/*      */     
/*  576 */     return traceRet1;
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
/*      */   public void writeBytes(byte[] value) throws JMSException {
/*  593 */     if (Trace.isOn) {
/*  594 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "writeBytes(byte [ ])", new Object[] { value });
/*      */     }
/*      */ 
/*      */     
/*  598 */     writeBytes(value, 0, value.length);
/*  599 */     if (Trace.isOn) {
/*  600 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "writeBytes(byte [ ])");
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
/*      */   public void writeBytes(byte[] value, int offset, int length) throws JMSException {
/*  622 */     if (Trace.isOn) {
/*  623 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "writeBytes(byte [ ],int,int)", new Object[] { value, 
/*  624 */             Integer.valueOf(offset), 
/*  625 */             Integer.valueOf(length) });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  640 */       if (this.writeStream != null) {
/*  641 */         this.writeStream.write(value, offset, length);
/*      */       
/*      */       }
/*      */     
/*      */     }
/*  646 */     catch (IOException ex) {
/*  647 */       if (Trace.isOn) {
/*  648 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "writeBytes(byte [ ],int,int)", ex);
/*      */       }
/*      */       
/*  651 */       JMSException jmsEx = newResourceAllocationException();
/*  652 */       jmsEx.setLinkedException(ex);
/*  653 */       if (Trace.isOn) {
/*  654 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "writeBytes(byte [ ],int,int)", (Throwable)jmsEx);
/*      */       }
/*      */       
/*  657 */       throw jmsEx;
/*      */     } 
/*      */     
/*  660 */     if (Trace.isOn) {
/*  661 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "writeBytes(byte [ ],int,int)");
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
/*      */   void _setBodyReadOnly() {
/*  673 */     if (Trace.isOn) {
/*  674 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "_setBodyReadOnly()");
/*      */     }
/*      */     
/*      */     try {
/*  678 */       reset();
/*      */     }
/*  680 */     catch (JMSException ex) {
/*  681 */       if (Trace.isOn) {
/*  682 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "_setBodyReadOnly()", (Throwable)ex);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  687 */     if (Trace.isOn) {
/*  688 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "_setBodyReadOnly()");
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
/*      */   private void writeObject(ObjectOutputStream out) throws IOException {
/*  702 */     if (Trace.isOn) {
/*  703 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "writeObject(java.io.ObjectOutputStream)", new Object[] { out });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*      */       try {
/*  710 */         reset();
/*      */       }
/*  712 */       catch (JMSException je) {
/*  713 */         if (Trace.isOn) {
/*  714 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "writeObject(java.io.ObjectOutputStream)", (Throwable)je, 1);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  719 */         IOException traceRet1 = new IOException("failed to reset ProviderBytesMessage for serialization");
/*  720 */         if (Trace.isOn) {
/*  721 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "writeObject(java.io.ObjectOutputStream)", traceRet1, 1);
/*      */         }
/*      */         
/*  724 */         throw traceRet1;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  732 */       out.defaultWriteObject();
/*      */     }
/*  734 */     catch (IOException e) {
/*  735 */       if (Trace.isOn) {
/*  736 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "writeObject(java.io.ObjectOutputStream)", e, 2);
/*      */       }
/*      */       
/*  739 */       if (Trace.isOn) {
/*  740 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "writeObject(java.io.ObjectOutputStream)", e, 2);
/*      */       }
/*      */       
/*  743 */       throw e;
/*      */     } 
/*      */     
/*  746 */     if (Trace.isOn) {
/*  747 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "writeObject(java.io.ObjectOutputStream)");
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
/*      */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/*  762 */     if (Trace.isOn) {
/*  763 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "readObject(java.io.ObjectInputStream)", new Object[] { in });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  772 */       in.defaultReadObject();
/*      */ 
/*      */       
/*      */       try {
/*  776 */         reset();
/*      */       }
/*  778 */       catch (JMSException je) {
/*  779 */         if (Trace.isOn) {
/*  780 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "readObject(java.io.ObjectInputStream)", (Throwable)je, 1);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  785 */         IOException traceRet1 = new IOException("failed to reset ProviderBytesMessage for serialisation");
/*  786 */         if (Trace.isOn) {
/*  787 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "readObject(java.io.ObjectInputStream)", traceRet1, 1);
/*      */         }
/*      */         
/*  790 */         throw traceRet1;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  798 */       if (this.messageClass.equals("jms_bytes")) {
/*  799 */         this.messageClass = "jms_bytes";
/*      */       }
/*      */     }
/*  802 */     catch (IOException e) {
/*  803 */       if (Trace.isOn) {
/*  804 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "readObject(java.io.ObjectInputStream)", e, 2);
/*      */       }
/*      */       
/*  807 */       if (Trace.isOn) {
/*  808 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "readObject(java.io.ObjectInputStream)", e, 2);
/*      */       }
/*      */       
/*  811 */       throw e;
/*      */     } 
/*      */     
/*  814 */     if (Trace.isOn) {
/*  815 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "readObject(java.io.ObjectInputStream)");
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
/*      */   public long getBodyLength() throws JMSException, MessageNotReadableException {
/*  838 */     if (Trace.isOn) {
/*  839 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "getBodyLength()");
/*      */     }
/*      */     
/*  842 */     long length = 0L;
/*  843 */     byte[] copyBuffer = new byte[4000];
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  848 */       if (!this.lengthCheck) {
/*  849 */         this.readLengthStream.mark(0);
/*  850 */         this.lengthCheck = true;
/*      */       }
/*      */       else {
/*      */         
/*  854 */         this.readLengthStream.reset();
/*      */       } 
/*      */       while (true) {
/*  857 */         int moreBytes = this.readLengthStream.read(copyBuffer);
/*  858 */         if (moreBytes < 0) {
/*      */           break;
/*      */         }
/*      */         
/*  862 */         length += moreBytes;
/*      */       } 
/*      */       
/*  865 */       if (Trace.isOn) {
/*  866 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "getBodyLength()", 
/*  867 */             Long.valueOf(length));
/*      */       }
/*  869 */       return length;
/*      */     }
/*  871 */     catch (IOException ioe) {
/*  872 */       if (Trace.isOn) {
/*  873 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "getBodyLength()", ioe);
/*      */       }
/*      */       
/*  876 */       JMSException jmsEx = newResourceAllocationException();
/*  877 */       jmsEx.setLinkedException(ioe);
/*  878 */       if (Trace.isOn) {
/*  879 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "getBodyLength()", (Throwable)jmsEx);
/*      */       }
/*      */       
/*  882 */       throw jmsEx;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getBytes() throws JMSException {
/*  891 */     byte[] bytes = new byte[this.dataBuffer.length - this.dataStart];
/*  892 */     readBytes(bytes);
/*  893 */     if (Trace.isOn) {
/*  894 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "getBytes()", "getter", bytes);
/*      */     }
/*      */     
/*  897 */     return bytes;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBytes(byte[] bytes) throws JMSException {
/*  905 */     if (Trace.isOn) {
/*  906 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "setBytes(byte [ ])", "setter", bytes);
/*      */     }
/*      */     
/*  909 */     clearBody();
/*  910 */     writeBytes(bytes);
/*  911 */     reset();
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
/*      */   private void checkReadStream() throws JMSException {
/*  947 */     if (Trace.isOn) {
/*  948 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "checkReadStream()");
/*      */     }
/*      */ 
/*      */     
/*  952 */     if (this.readStream == null) {
/*  953 */       String message = "ByteMessage readStream was null. Shouldn't happen when the message is in readOnly mode";
/*  954 */       if (Trace.isOn) {
/*  955 */         Trace.data(this, "checkReadStream", message);
/*      */       }
/*      */       
/*  958 */       HashMap<Object, Object> data = new HashMap<>();
/*  959 */       data.put("Message", message);
/*      */       
/*  961 */       Trace.ffst(this, "readInt()", "", data, null);
/*  962 */       JMSException je = newMessageEOFException();
/*  963 */       if (Trace.isOn) {
/*  964 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "checkReadStream()", (Throwable)je);
/*      */       }
/*      */       
/*  967 */       throw je;
/*      */     } 
/*      */     
/*  970 */     if (Trace.isOn) {
/*  971 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "checkReadStream()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasBody() {
/*  980 */     if (Trace.isOn) {
/*  981 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "hasBody()");
/*      */     }
/*      */     
/*  984 */     if (Trace.isOn) {
/*  985 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "hasBody()", 
/*  986 */           Boolean.valueOf(false));
/*      */     }
/*  988 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public long getJMSDeliveryTime() throws JMSException {
/*  994 */     if (Trace.isOn) {
/*  995 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "getJMSDeliveryTime()", "getter", 
/*  996 */           Long.valueOf(0L));
/*      */     }
/*  998 */     return 0L;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setJMSDeliveryTime(long deliveryTime) throws JMSException {
/* 1003 */     if (Trace.isOn)
/* 1004 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage", "setJMSDeliveryTime(long)", "setter", 
/* 1005 */           Long.valueOf(deliveryTime)); 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\JMSBytesMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */