/*      */ package com.ibm.jms;
/*      */ 
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.jms.internal.JmsStreamMessageImpl;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.ObjectStreamField;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.StringTokenizer;
/*      */ import javax.jms.JMSException;
/*      */ import javax.jms.Message;
/*      */ import javax.jms.MessageEOFException;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*      */   implements StreamMessage
/*      */ {
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/jms/JMSStreamMessage.java";
/*      */   static final long serialVersionUID = -3015681339377486635L;
/*      */   
/*      */   static {
/*  131 */     if (Trace.isOn) {
/*  132 */       Trace.data("com.ibm.jms.JMSStreamMessage", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/jms/JMSStreamMessage.java");
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
/*  143 */   private static final char[] BIN2HEX = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final String STREAM_BODY_NAME = "stream";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final String STREAM_ELT_NAME = "elt";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  162 */   private static final ObjectStreamField[] serialPersistentFields = new ObjectStreamField[] { new ObjectStreamField("dataWritten", StringBuffer.class), new ObjectStreamField("streamBody", String.class) };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public JMSStreamMessage() {
/*  179 */     if (Trace.isOn) {
/*  180 */       Trace.entry(this, "com.ibm.jms.JMSStreamMessage", "<init>()");
/*      */     }
/*  182 */     assert false : "Do not call a message's default constructor, use javax.jms.Session methods instead";
/*  183 */     if (Trace.isOn) {
/*  184 */       Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "<init>()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected JMSStreamMessage(Message delegateMsg) {
/*  191 */     super(delegateMsg);
/*  192 */     if (Trace.isOn) {
/*  193 */       Trace.entry(this, "com.ibm.jms.JMSStreamMessage", "<init>(Message)", new Object[] { delegateMsg });
/*      */     }
/*      */     
/*  196 */     if (Trace.isOn) {
/*  197 */       Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "<init>(Message)");
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
/*      */   public boolean readBoolean() throws JMSException {
/*  212 */     if (Trace.isOn) {
/*  213 */       Trace.entry(this, "com.ibm.jms.JMSStreamMessage", "readBoolean()");
/*      */     }
/*  215 */     boolean traceRet1 = ((StreamMessage)this.delegateMsg).readBoolean();
/*  216 */     if (Trace.isOn) {
/*  217 */       Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "readBoolean()", Boolean.valueOf(traceRet1));
/*      */     }
/*      */     
/*  220 */     return traceRet1;
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
/*  234 */     if (Trace.isOn) {
/*  235 */       Trace.entry(this, "com.ibm.jms.JMSStreamMessage", "readByte()");
/*      */     }
/*  237 */     byte traceRet1 = ((StreamMessage)this.delegateMsg).readByte();
/*  238 */     if (Trace.isOn) {
/*  239 */       Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "readByte()", Byte.valueOf(traceRet1));
/*      */     }
/*  241 */     return traceRet1;
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
/*      */   public int readBytes(byte[] value) throws JMSException {
/*  284 */     if (Trace.isOn) {
/*  285 */       Trace.entry(this, "com.ibm.jms.JMSStreamMessage", "readBytes(byte [ ])", new Object[] { value });
/*      */     }
/*      */     
/*  288 */     int traceRet1 = ((StreamMessage)this.delegateMsg).readBytes(value);
/*  289 */     if (Trace.isOn) {
/*  290 */       Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "readBytes(byte [ ])", 
/*  291 */           Integer.valueOf(traceRet1));
/*      */     }
/*  293 */     return traceRet1;
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
/*      */   public char readChar() throws JMSException {
/*  308 */     if (Trace.isOn) {
/*  309 */       Trace.entry(this, "com.ibm.jms.JMSStreamMessage", "readChar()");
/*      */     }
/*  311 */     char traceRet1 = ((StreamMessage)this.delegateMsg).readChar();
/*  312 */     if (Trace.isOn) {
/*  313 */       Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "readChar()", Character.valueOf(traceRet1));
/*      */     }
/*      */     
/*  316 */     return traceRet1;
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
/*  330 */     if (Trace.isOn) {
/*  331 */       Trace.entry(this, "com.ibm.jms.JMSStreamMessage", "readDouble()");
/*      */     }
/*  333 */     double traceRet1 = ((StreamMessage)this.delegateMsg).readDouble();
/*  334 */     if (Trace.isOn) {
/*  335 */       Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "readDouble()", Double.valueOf(traceRet1));
/*      */     }
/*  337 */     return traceRet1;
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
/*  351 */     if (Trace.isOn) {
/*  352 */       Trace.entry(this, "com.ibm.jms.JMSStreamMessage", "readFloat()");
/*      */     }
/*  354 */     float traceRet1 = ((StreamMessage)this.delegateMsg).readFloat();
/*  355 */     if (Trace.isOn) {
/*  356 */       Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "readFloat()", Float.valueOf(traceRet1));
/*      */     }
/*  358 */     return traceRet1;
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
/*      */   public int readInt() throws JMSException {
/*  373 */     if (Trace.isOn) {
/*  374 */       Trace.entry(this, "com.ibm.jms.JMSStreamMessage", "readInt()");
/*      */     }
/*  376 */     int traceRet1 = ((StreamMessage)this.delegateMsg).readInt();
/*  377 */     if (Trace.isOn) {
/*  378 */       Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "readInt()", Integer.valueOf(traceRet1));
/*      */     }
/*  380 */     return traceRet1;
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
/*      */   public long readLong() throws JMSException {
/*  395 */     if (Trace.isOn) {
/*  396 */       Trace.entry(this, "com.ibm.jms.JMSStreamMessage", "readLong()");
/*      */     }
/*  398 */     long traceRet1 = ((StreamMessage)this.delegateMsg).readLong();
/*  399 */     if (Trace.isOn) {
/*  400 */       Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "readLong()", Long.valueOf(traceRet1));
/*      */     }
/*  402 */     return traceRet1;
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
/*      */   public Object readObject() throws JMSException {
/*  423 */     if (Trace.isOn) {
/*  424 */       Trace.entry(this, "com.ibm.jms.JMSStreamMessage", "readObject()");
/*      */     }
/*  426 */     Object traceRet1 = ((StreamMessage)this.delegateMsg).readObject();
/*  427 */     if (Trace.isOn) {
/*  428 */       Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "readObject()", traceRet1);
/*      */     }
/*  430 */     return traceRet1;
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
/*      */   public short readShort() throws JMSException {
/*  445 */     if (Trace.isOn) {
/*  446 */       Trace.entry(this, "com.ibm.jms.JMSStreamMessage", "readShort()");
/*      */     }
/*  448 */     short traceRet1 = ((StreamMessage)this.delegateMsg).readShort();
/*  449 */     if (Trace.isOn) {
/*  450 */       Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "readShort()", Short.valueOf(traceRet1));
/*      */     }
/*  452 */     return traceRet1;
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
/*      */   public String readString() throws JMSException {
/*  467 */     if (Trace.isOn) {
/*  468 */       Trace.entry(this, "com.ibm.jms.JMSStreamMessage", "readString()");
/*      */     }
/*  470 */     String traceRet1 = ((StreamMessage)this.delegateMsg).readString();
/*  471 */     if (Trace.isOn) {
/*  472 */       Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "readString()", traceRet1);
/*      */     }
/*  474 */     return traceRet1;
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
/*  485 */     if (Trace.isOn) {
/*  486 */       Trace.entry(this, "com.ibm.jms.JMSStreamMessage", "reset()");
/*      */     }
/*  488 */     ((StreamMessage)this.delegateMsg).reset();
/*  489 */     if (Trace.isOn) {
/*  490 */       Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "reset()");
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
/*      */   public void writeBoolean(boolean value) throws JMSException {
/*  508 */     if (Trace.isOn)
/*  509 */       Trace.entry(this, "com.ibm.jms.JMSStreamMessage", "writeBoolean(boolean)", new Object[] {
/*  510 */             Boolean.valueOf(value)
/*      */           }); 
/*  512 */     ((StreamMessage)this.delegateMsg).writeBoolean(value);
/*  513 */     if (Trace.isOn) {
/*  514 */       Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "writeBoolean(boolean)");
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
/*      */   public void writeByte(byte value) throws JMSException {
/*  529 */     if (Trace.isOn)
/*  530 */       Trace.entry(this, "com.ibm.jms.JMSStreamMessage", "writeByte(byte)", new Object[] {
/*  531 */             Byte.valueOf(value)
/*      */           }); 
/*  533 */     ((StreamMessage)this.delegateMsg).writeByte(value);
/*  534 */     if (Trace.isOn) {
/*  535 */       Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "writeByte(byte)");
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
/*  551 */     if (Trace.isOn) {
/*  552 */       Trace.entry(this, "com.ibm.jms.JMSStreamMessage", "writeBytes(byte [ ])", new Object[] { value });
/*      */     }
/*      */     
/*  555 */     ((StreamMessage)this.delegateMsg).writeBytes(value);
/*  556 */     if (Trace.isOn) {
/*  557 */       Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "writeBytes(byte [ ])");
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
/*  575 */     if (Trace.isOn) {
/*  576 */       Trace.entry(this, "com.ibm.jms.JMSStreamMessage", "writeBytes(byte [ ],int,int)", new Object[] { value, 
/*  577 */             Integer.valueOf(offset), Integer.valueOf(length) });
/*      */     }
/*  579 */     ((StreamMessage)this.delegateMsg).writeBytes(value, offset, length);
/*  580 */     if (Trace.isOn) {
/*  581 */       Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "writeBytes(byte [ ],int,int)");
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
/*      */   public void writeChar(char value) throws JMSException {
/*  596 */     if (Trace.isOn)
/*  597 */       Trace.entry(this, "com.ibm.jms.JMSStreamMessage", "writeChar(char)", new Object[] {
/*  598 */             Character.valueOf(value)
/*      */           }); 
/*  600 */     ((StreamMessage)this.delegateMsg).writeChar(value);
/*  601 */     if (Trace.isOn) {
/*  602 */       Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "writeChar(char)");
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
/*  618 */     if (Trace.isOn)
/*  619 */       Trace.entry(this, "com.ibm.jms.JMSStreamMessage", "writeDouble(double)", new Object[] {
/*  620 */             Double.valueOf(value)
/*      */           }); 
/*  622 */     ((StreamMessage)this.delegateMsg).writeDouble(value);
/*  623 */     if (Trace.isOn) {
/*  624 */       Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "writeDouble(double)");
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
/*  640 */     if (Trace.isOn)
/*  641 */       Trace.entry(this, "com.ibm.jms.JMSStreamMessage", "writeFloat(float)", new Object[] {
/*  642 */             Float.valueOf(value)
/*      */           }); 
/*  644 */     ((StreamMessage)this.delegateMsg).writeFloat(value);
/*  645 */     if (Trace.isOn) {
/*  646 */       Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "writeFloat(float)");
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
/*      */   public void writeInt(int value) throws JMSException {
/*  661 */     if (Trace.isOn)
/*  662 */       Trace.entry(this, "com.ibm.jms.JMSStreamMessage", "writeInt(int)", new Object[] {
/*  663 */             Integer.valueOf(value)
/*      */           }); 
/*  665 */     ((StreamMessage)this.delegateMsg).writeInt(value);
/*  666 */     if (Trace.isOn) {
/*  667 */       Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "writeInt(int)");
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
/*      */   public void writeLong(long value) throws JMSException {
/*  682 */     if (Trace.isOn)
/*  683 */       Trace.entry(this, "com.ibm.jms.JMSStreamMessage", "writeLong(long)", new Object[] {
/*  684 */             Long.valueOf(value)
/*      */           }); 
/*  686 */     ((StreamMessage)this.delegateMsg).writeLong(value);
/*  687 */     if (Trace.isOn) {
/*  688 */       Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "writeLong(long)");
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
/*      */   public void writeObject(Object value) throws JMSException {
/*  710 */     if (Trace.isOn) {
/*  711 */       Trace.entry(this, "com.ibm.jms.JMSStreamMessage", "writeObject(Object)", new Object[] { value });
/*      */     }
/*      */     
/*  714 */     ((StreamMessage)this.delegateMsg).writeObject(value);
/*  715 */     if (Trace.isOn) {
/*  716 */       Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "writeObject(Object)");
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
/*  732 */     if (Trace.isOn)
/*  733 */       Trace.entry(this, "com.ibm.jms.JMSStreamMessage", "writeShort(short)", new Object[] {
/*  734 */             Short.valueOf(value)
/*      */           }); 
/*  736 */     ((StreamMessage)this.delegateMsg).writeShort(value);
/*  737 */     if (Trace.isOn) {
/*  738 */       Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "writeShort(short)");
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
/*  754 */     if (Trace.isOn) {
/*  755 */       Trace.entry(this, "com.ibm.jms.JMSStreamMessage", "writeString(String)", new Object[] { value });
/*      */     }
/*      */     
/*  758 */     ((StreamMessage)this.delegateMsg).writeString(value);
/*  759 */     if (Trace.isOn) {
/*  760 */       Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "writeString(String)");
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
/*      */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/*  772 */     if (Trace.isOn) {
/*  773 */       Trace.entry(this, "com.ibm.jms.JMSStreamMessage", "readObject(java.io.ObjectInputStream)", new Object[] { in });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  783 */       String connectionType = "com.ibm.msg.client.wmq";
/*  784 */       Message superClassMsg = null;
/*  785 */       if (this.delegateMsg != null) {
/*  786 */         superClassMsg = this.delegateMsg;
/*      */       }
/*  788 */       this.delegateMsg = (Message)new JmsStreamMessageImpl(connectionType, superClassMsg);
/*      */     }
/*  790 */     catch (JMSException e) {
/*  791 */       if (Trace.isOn) {
/*  792 */         Trace.catchBlock(this, "com.ibm.jms.JMSStreamMessage", "readObject(java.io.ObjectInputStream)", (Throwable)e, 1);
/*      */       }
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
/*  804 */     ObjectInputStream.GetField fields = in.readFields();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*  822 */       String unbuffered = null;
/*  823 */       StringBuffer buffer = null;
/*      */       
/*  825 */       if (!fields.defaulted("streamBody")) {
/*  826 */         unbuffered = (String)fields.get("streamBody", (Object)null);
/*      */       }
/*      */       
/*  829 */       if (!fields.defaulted("dataWritten")) {
/*  830 */         buffer = (StringBuffer)fields.get("dataWritten", (Object)null);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  835 */       if (buffer != null) {
/*  836 */         unbuffered = buffer.toString();
/*      */       }
/*      */       
/*  839 */       if (unbuffered != null)
/*      */       {
/*  841 */         StringTokenizer st = new StringTokenizer(unbuffered, "<>");
/*      */ 
/*      */         
/*  844 */         boolean processing = true;
/*  845 */         while (processing == true) {
/*  846 */           Object obj = readField(st);
/*  847 */           if (obj == null) {
/*  848 */             processing = false;
/*      */             continue;
/*      */           } 
/*  851 */           writeObject(obj);
/*      */         }
/*      */       
/*      */       }
/*      */     
/*  856 */     } catch (JMSException je) {
/*  857 */       if (Trace.isOn) {
/*  858 */         Trace.catchBlock(this, "com.ibm.jms.JMSStreamMessage", "readObject(java.io.ObjectInputStream)", (Throwable)je, 2);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  865 */     if (Trace.isOn) {
/*  866 */       Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "readObject(java.io.ObjectInputStream)");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void writeObject(ObjectOutputStream out) throws IOException {
/*  872 */     if (Trace.isOn) {
/*  873 */       Trace.entry(this, "com.ibm.jms.JMSStreamMessage", "writeObject(java.io.ObjectOutputStream)", new Object[] { out });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  881 */     ObjectOutputStream.PutField fields = out.putFields();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  888 */       reset();
/*      */ 
/*      */       
/*  891 */       StringBuffer buffer = new StringBuffer();
/*  892 */       String unbuffered = "";
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*      */         while (true) {
/*  899 */           Object element = ((JmsStreamMessageImpl)this.delegateMsg).getNextField("writeObject");
/*  900 */           formatElement("elt", element, buffer);
/*      */         }
/*      */       
/*  903 */       } catch (MessageEOFException e) {
/*  904 */         if (Trace.isOn) {
/*  905 */           Trace.catchBlock(this, "com.ibm.jms.JMSStreamMessage", "writeObject(java.io.ObjectOutputStream)", (Throwable)e, 1);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  911 */         if (buffer.length() > 0) {
/*  912 */           unbuffered = buffer.toString();
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  918 */         fields.put("dataWritten", buffer);
/*  919 */         fields.put("streamBody", unbuffered);
/*      */       }
/*      */     
/*  922 */     } catch (JMSException je) {
/*  923 */       if (Trace.isOn) {
/*  924 */         Trace.catchBlock(this, "com.ibm.jms.JMSStreamMessage", "writeObject(java.io.ObjectOutputStream)", (Throwable)je, 2);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  933 */     out.writeFields();
/*      */     
/*  935 */     if (Trace.isOn) {
/*  936 */       Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "writeObject(java.io.ObjectOutputStream)");
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
/*      */   private void formatElement(String name, Object element, StringBuffer buffer) {
/*  951 */     if (Trace.isOn) {
/*  952 */       Trace.entry(this, "com.ibm.jms.JMSStreamMessage", "formatElement(String,Object,StringBuffer)", new Object[] { name, element, buffer });
/*      */     }
/*      */ 
/*      */     
/*  956 */     Object value = element;
/*      */     
/*  958 */     boolean written = false;
/*  959 */     buffer.append("<");
/*  960 */     buffer.append(name);
/*      */     
/*  962 */     if (value instanceof String) {
/*  963 */       buffer.append(">");
/*  964 */       backReference(buffer, (String)value);
/*  965 */       written = true;
/*      */     }
/*  967 */     else if (value instanceof Integer) {
/*  968 */       buffer.append(" dt='i4'>");
/*      */     }
/*  970 */     else if (value instanceof Short) {
/*  971 */       buffer.append(" dt='i2'>");
/*      */     }
/*  973 */     else if (value instanceof Byte) {
/*  974 */       buffer.append(" dt='i1'>");
/*      */     }
/*  976 */     else if (value instanceof Long) {
/*  977 */       buffer.append(" dt='i8'>");
/*      */     }
/*  979 */     else if (value instanceof Float) {
/*  980 */       buffer.append(" dt='r4'>");
/*      */     }
/*  982 */     else if (value instanceof Double) {
/*  983 */       buffer.append(" dt='r8'>");
/*      */     }
/*  985 */     else if (value instanceof byte[]) {
/*  986 */       buffer.append(" dt='bin.hex'>");
/*  987 */       binToHex((byte[])value, 0, ((byte[])value).length, buffer);
/*  988 */       written = true;
/*      */     }
/*  990 */     else if (value instanceof Boolean) {
/*  991 */       buffer.append(" dt='boolean'>");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  996 */       if (((Boolean)value).booleanValue() == true) {
/*  997 */         value = "1";
/*      */       } else {
/*      */         
/* 1000 */         value = "0";
/*      */       }
/*      */     
/* 1003 */     } else if (value instanceof Character) {
/* 1004 */       buffer.append(" dt='char'>");
/* 1005 */       backReference(buffer, ((Character)value).toString());
/*      */       
/* 1007 */       written = true;
/*      */     }
/* 1009 */     else if (value == null) {
/*      */ 
/*      */ 
/*      */       
/* 1013 */       buffer.append(" xsi:nil='true'>");
/* 1014 */       buffer.append("</");
/* 1015 */       buffer.append(name);
/* 1016 */       buffer.append(">");
/* 1017 */       if (Trace.isOn) {
/* 1018 */         Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "formatElement(String,Object,StringBuffer)", 1);
/*      */       }
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1025 */     if (!written) {
/* 1026 */       buffer.append(value.toString());
/*      */     }
/*      */ 
/*      */     
/* 1030 */     buffer.append("</");
/* 1031 */     buffer.append(name);
/* 1032 */     buffer.append(">");
/* 1033 */     if (Trace.isOn) {
/* 1034 */       Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "formatElement(String,Object,StringBuffer)", 2);
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
/*      */   private void backReference(StringBuffer sb, String string) {
/* 1052 */     if (Trace.isOn) {
/* 1053 */       Trace.entry(this, "com.ibm.jms.JMSStreamMessage", "backReference(StringBuffer,String)", new Object[] { sb, string });
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
/* 1065 */     for (int i = 0; i < string.length(); i++) {
/* 1066 */       char ch = string.charAt(i);
/* 1067 */       if ('<' == ch) {
/* 1068 */         sb.append("&lt;");
/*      */       }
/* 1070 */       else if ('>' == ch) {
/* 1071 */         sb.append("&gt;");
/*      */       }
/* 1073 */       else if ('&' == ch) {
/* 1074 */         sb.append("&amp;");
/*      */       
/*      */       }
/* 1077 */       else if ('"' == ch) {
/* 1078 */         sb.append("&quot;");
/*      */       }
/* 1080 */       else if ('\'' == ch) {
/* 1081 */         sb.append("&apos;");
/*      */       
/*      */       }
/* 1084 */       else if ('?' <= ch && ch < '?') {
/*      */         
/* 1086 */         int next = string.charAt(++i);
/* 1087 */         next = (ch - 55296 << 10) + next - 56320 + 65536;
/* 1088 */         sb.append("&#x");
/* 1089 */         sb.append(Integer.toHexString(next));
/* 1090 */         sb.append(";");
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/* 1097 */         sb.append(ch);
/*      */       } 
/*      */     } 
/* 1100 */     if (Trace.isOn) {
/* 1101 */       Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "backReference(StringBuffer,String)");
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
/*      */   private int binToHex(byte[] bin, int start, int length, StringBuffer hex) {
/* 1115 */     if (Trace.isOn) {
/* 1116 */       Trace.entry(this, "com.ibm.jms.JMSStreamMessage", "binToHex(byte [ ],int,int,StringBuffer)", new Object[] { bin, 
/* 1117 */             Integer.valueOf(start), Integer.valueOf(length), hex });
/*      */     }
/*      */ 
/*      */     
/* 1121 */     int sum = 0;
/*      */     
/* 1123 */     for (int i = start; i < start + length; i++) {
/* 1124 */       int binByte = bin[i];
/*      */       
/* 1126 */       if (binByte < 0) {
/* 1127 */         binByte += 256;
/*      */       }
/*      */       
/* 1130 */       sum += binByte;
/* 1131 */       hex.append(BIN2HEX[binByte / 16]);
/* 1132 */       hex.append(BIN2HEX[binByte % 16]);
/*      */     } 
/*      */     
/* 1135 */     if (Trace.isOn) {
/* 1136 */       Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "binToHex(byte [ ],int,int,StringBuffer)", 
/* 1137 */           Integer.valueOf(sum));
/*      */     }
/* 1139 */     return sum;
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
/*      */   private Object readField(StringTokenizer st) {
/* 1162 */     if (Trace.isOn) {
/* 1163 */       Trace.entry(this, "com.ibm.jms.JMSStreamMessage", "readField(StringTokenizer)", new Object[] { st });
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
/* 1178 */       String type, name = st.nextToken();
/*      */ 
/*      */       
/* 1181 */       if (name.equals("/stream")) {
/* 1182 */         if (Trace.isOn) {
/* 1183 */           Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "readField(StringTokenizer)", null, 1);
/*      */         }
/* 1185 */         return null;
/*      */       } 
/*      */ 
/*      */       
/* 1189 */       int nullIndex = 0;
/* 1190 */       boolean shortForm = false;
/* 1191 */       if (name.charAt(name.length() - 1) == '/') {
/* 1192 */         shortForm = true;
/*      */       }
/*      */       
/* 1195 */       nullIndex = name.indexOf(" xsi:nil");
/* 1196 */       if (nullIndex != -1) {
/* 1197 */         if (!shortForm) {
/*      */           
/* 1199 */           String str = st.nextToken();
/* 1200 */           if (str.charAt(0) != '/')
/*      */           {
/* 1202 */             str = st.nextToken();
/*      */           }
/*      */           
/* 1205 */           if (str.charAt(0) != '/') {
/*      */             
/* 1207 */             if (Trace.isOn) {
/* 1208 */               Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "readField(StringTokenizer)", null, 2);
/*      */             }
/*      */             
/* 1211 */             return null;
/*      */           } 
/*      */         } 
/* 1214 */         if (Trace.isOn) {
/* 1215 */           Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "readField(StringTokenizer)", null, 3);
/*      */         }
/*      */         
/* 1218 */         return null;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1223 */       int index = name.indexOf(" dt=");
/* 1224 */       if (index != -1) {
/* 1225 */         type = name.substring(index + 4);
/*      */       } else {
/*      */         
/* 1228 */         type = "'string'";
/*      */       } 
/*      */       
/* 1231 */       if (shortForm) {
/* 1232 */         Object object = deformatElement(type, "");
/*      */         
/* 1234 */         if (Trace.isOn) {
/* 1235 */           Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "readField(StringTokenizer)", object, 4);
/*      */         }
/*      */         
/* 1238 */         return object;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1243 */       String value = st.nextToken();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1249 */       if (value.charAt(0) == '/') {
/* 1250 */         Object object = deformatElement(type, "");
/*      */         
/* 1252 */         if (Trace.isOn) {
/* 1253 */           Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "readField(StringTokenizer)", object, 5);
/*      */         }
/*      */         
/* 1256 */         return object;
/*      */       } 
/*      */       
/* 1259 */       String token = st.nextToken();
/* 1260 */       if (token.charAt(0) != '/') {
/* 1261 */         if (Trace.isOn) {
/* 1262 */           Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "readField(StringTokenizer)", null, 6);
/*      */         }
/* 1264 */         return null;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1269 */       Object currentField = deformatElement(type, value);
/* 1270 */       if (Trace.isOn) {
/* 1271 */         Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "readField(StringTokenizer)", currentField, 7);
/*      */       }
/*      */       
/* 1274 */       return currentField;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 1280 */     catch (NoSuchElementException ex) {
/* 1281 */       if (Trace.isOn) {
/* 1282 */         Trace.catchBlock(this, "com.ibm.jms.JMSStreamMessage", "readField(StringTokenizer)", ex);
/*      */       }
/* 1284 */       if (Trace.isOn) {
/* 1285 */         Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "readField(StringTokenizer)", null, 8);
/*      */       }
/* 1287 */       return null;
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
/*      */   private Object deformatElement(String datatype, String value) {
/* 1302 */     if (Trace.isOn) {
/* 1303 */       Trace.entry(this, "com.ibm.jms.JMSStreamMessage", "deformatElement(String,String)", new Object[] { datatype, value });
/*      */     }
/*      */ 
/*      */     
/* 1307 */     if (Trace.isOn) {
/* 1308 */       if (value == null || value.length() <= 50) {
/*      */         
/* 1310 */         Trace.data(this, "valcheck1", "type is " + datatype + " value is " + value);
/*      */       } else {
/*      */         
/* 1313 */         Trace.data(this, "valcheck2", "type is " + datatype + " value starts " + value
/* 1314 */             .substring(0, 50));
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 1319 */     char openingQuote = datatype.charAt(0);
/*      */     
/* 1321 */     if (openingQuote != '\'' && openingQuote != '"') {
/*      */       
/* 1323 */       if (Trace.isOn) {
/* 1324 */         Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "deformatElement(String,String)", null, 1);
/*      */       }
/*      */       
/* 1327 */       return null;
/*      */     } 
/*      */ 
/*      */     
/* 1331 */     String type = datatype.substring(1, datatype.indexOf(openingQuote, 1));
/* 1332 */     if (type.equals("string")) {
/* 1333 */       if (value == null) {
/* 1334 */         if (Trace.isOn) {
/* 1335 */           Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "deformatElement(String,String)", null, 2);
/*      */         }
/*      */         
/* 1338 */         return null;
/*      */       } 
/* 1340 */       Object traceRet1 = expandRefs(value);
/* 1341 */       if (Trace.isOn) {
/* 1342 */         Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "deformatElement(String,String)", traceRet1, 3);
/*      */       }
/*      */       
/* 1345 */       return traceRet1;
/*      */     } 
/* 1347 */     if (type.equals("i4")) {
/* 1348 */       Object traceRet2 = Integer.valueOf(value);
/* 1349 */       if (Trace.isOn) {
/* 1350 */         Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "deformatElement(String,String)", traceRet2, 4);
/*      */       }
/*      */       
/* 1353 */       return traceRet2;
/*      */     } 
/* 1355 */     if (type.equals("i2")) {
/* 1356 */       Object traceRet3 = Short.valueOf(value);
/* 1357 */       if (Trace.isOn) {
/* 1358 */         Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "deformatElement(String,String)", traceRet3, 5);
/*      */       }
/*      */       
/* 1361 */       return traceRet3;
/*      */     } 
/* 1363 */     if (type.equals("i8")) {
/* 1364 */       Object traceRet4 = Long.valueOf(value);
/* 1365 */       if (Trace.isOn) {
/* 1366 */         Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "deformatElement(String,String)", traceRet4, 6);
/*      */       }
/*      */       
/* 1369 */       return traceRet4;
/*      */     } 
/* 1371 */     if (type.equals("i1")) {
/* 1372 */       Object traceRet5 = Byte.valueOf(value);
/* 1373 */       if (Trace.isOn) {
/* 1374 */         Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "deformatElement(String,String)", traceRet5, 7);
/*      */       }
/*      */       
/* 1377 */       return traceRet5;
/*      */     } 
/* 1379 */     if (type.equals("r4")) {
/* 1380 */       Object traceRet6 = Float.valueOf(value);
/* 1381 */       if (Trace.isOn) {
/* 1382 */         Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "deformatElement(String,String)", traceRet6, 8);
/*      */       }
/*      */       
/* 1385 */       return traceRet6;
/*      */     } 
/* 1387 */     if (type.equals("r8")) {
/* 1388 */       Object traceRet7 = Double.valueOf(value);
/* 1389 */       if (Trace.isOn) {
/* 1390 */         Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "deformatElement(String,String)", traceRet7, 9);
/*      */       }
/*      */       
/* 1393 */       return traceRet7;
/*      */     } 
/* 1395 */     if (type.equals("bin.hex")) {
/* 1396 */       if (value.length() == 0) {
/* 1397 */         Object traceRet8 = new byte[0];
/* 1398 */         if (Trace.isOn) {
/* 1399 */           Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "deformatElement(String,String)", traceRet8, 10);
/*      */         }
/*      */         
/* 1402 */         return traceRet8;
/*      */       } 
/* 1404 */       Object traceRet9 = hexToBin(value, 0);
/* 1405 */       if (Trace.isOn) {
/* 1406 */         Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "deformatElement(String,String)", traceRet9, 11);
/*      */       }
/*      */       
/* 1409 */       return traceRet9;
/*      */     } 
/* 1411 */     if (type.equals("boolean")) {
/*      */       
/* 1413 */       if (value.equals("1")) {
/* 1414 */         Object traceRet10 = Boolean.valueOf(true);
/* 1415 */         if (Trace.isOn) {
/* 1416 */           Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "deformatElement(String,String)", traceRet10, 12);
/*      */         }
/*      */         
/* 1419 */         return traceRet10;
/*      */       } 
/* 1421 */       if (value.equals("0")) {
/* 1422 */         Object traceRet11 = Boolean.valueOf(false);
/* 1423 */         if (Trace.isOn) {
/* 1424 */           Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "deformatElement(String,String)", traceRet11, 13);
/*      */         }
/*      */         
/* 1427 */         return traceRet11;
/*      */       } 
/*      */       
/* 1430 */       if (Trace.isOn) {
/* 1431 */         Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "deformatElement(String,String)", null, 14);
/*      */       }
/*      */       
/* 1434 */       return null;
/*      */     } 
/*      */     
/* 1437 */     if (type.equals("char")) {
/* 1438 */       Object traceRet12 = Character.valueOf(expandRefs(value).charAt(0));
/* 1439 */       if (Trace.isOn) {
/* 1440 */         Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "deformatElement(String,String)", traceRet12, 15);
/*      */       }
/*      */       
/* 1443 */       return traceRet12;
/*      */     } 
/*      */     
/* 1446 */     if (Trace.isOn) {
/* 1447 */       Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "deformatElement(String,String)", null, 16);
/*      */     }
/*      */     
/* 1450 */     return null;
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
/*      */   private String expandRefs(String string) {
/* 1465 */     if (Trace.isOn) {
/* 1466 */       Trace.entry(this, "com.ibm.jms.JMSStreamMessage", "expandRefs(String)", new Object[] { string });
/*      */     }
/*      */     
/* 1469 */     if (string.length() == 0) {
/* 1470 */       String traceRet1 = "";
/* 1471 */       if (Trace.isOn) {
/* 1472 */         Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "expandRefs(String)", traceRet1, 1);
/*      */       }
/* 1474 */       return traceRet1;
/*      */     } 
/* 1476 */     StringBuffer sb = new StringBuffer(string.length());
/*      */ 
/*      */     
/* 1479 */     for (int i = 0; i < string.length(); i++) {
/* 1480 */       char ch = string.charAt(i);
/* 1481 */       if ('&' == ch) {
/* 1482 */         String entity = string.substring(i + 1, i + 4);
/*      */         
/* 1484 */         if (entity.equals("lt;")) {
/* 1485 */           sb.append('<');
/* 1486 */           i += 3;
/*      */         }
/* 1488 */         else if (entity.equals("gt;")) {
/* 1489 */           sb.append('>');
/* 1490 */           i += 3;
/*      */         }
/* 1492 */         else if (string.substring(i + 1, i + 5).equals("amp;")) {
/* 1493 */           sb.append("&");
/* 1494 */           i += 4;
/*      */         }
/* 1496 */         else if (string.substring(i + 1, i + 6).equals("apos;")) {
/*      */           
/* 1498 */           sb.append("'");
/* 1499 */           i += 5;
/*      */         }
/* 1501 */         else if (string.substring(i + 1, i + 6).equals("quot;")) {
/*      */           
/* 1503 */           sb.append('"');
/* 1504 */           i += 5;
/*      */         } else {
/*      */           
/* 1507 */           if (Trace.isOn) {
/* 1508 */             Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "expandRefs(String)", null, 2);
/*      */           }
/* 1510 */           return null;
/*      */         } 
/*      */       } else {
/*      */         
/* 1514 */         sb.append(ch);
/*      */       } 
/*      */     } 
/* 1517 */     String traceRet2 = sb.toString();
/* 1518 */     if (Trace.isOn) {
/* 1519 */       Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "expandRefs(String)", traceRet2, 3);
/*      */     }
/* 1521 */     return traceRet2;
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
/*      */   private byte[] hexToBin(String hex, int start) {
/* 1533 */     if (Trace.isOn) {
/* 1534 */       Trace.entry(this, "com.ibm.jms.JMSStreamMessage", "hexToBin(String,int)", new Object[] { hex, 
/* 1535 */             Integer.valueOf(start) });
/*      */     }
/*      */     
/* 1538 */     int length = hex.length() - start;
/*      */ 
/*      */     
/* 1541 */     if (length == 0) {
/* 1542 */       byte[] traceRet1 = new byte[0];
/* 1543 */       if (Trace.isOn) {
/* 1544 */         Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "hexToBin(String,int)", traceRet1, 1);
/*      */       }
/* 1546 */       return traceRet1;
/*      */     } 
/*      */ 
/*      */     
/* 1550 */     if (length < 0 || length % 2 != 0) {
/* 1551 */       if (Trace.isOn) {
/* 1552 */         Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "hexToBin(String,int)", null, 2);
/*      */       }
/* 1554 */       return null;
/*      */     } 
/*      */ 
/*      */     
/* 1558 */     length /= 2;
/*      */ 
/*      */     
/* 1561 */     byte[] retval = new byte[length];
/*      */ 
/*      */     
/* 1564 */     for (int i = 0; i < length; i++) {
/* 1565 */       int digit1 = Character.digit(hex.charAt(2 * i + start), 16) << 4;
/* 1566 */       int digit2 = Character.digit(hex.charAt(2 * i + start + 1), 16);
/*      */ 
/*      */       
/* 1569 */       if (digit1 < 0 || digit2 < 0) {
/* 1570 */         if (Trace.isOn) {
/* 1571 */           Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "hexToBin(String,int)", null, 3);
/*      */         }
/* 1573 */         return null;
/*      */       } 
/*      */       
/* 1576 */       retval[i] = (byte)(digit1 + digit2);
/*      */     } 
/* 1578 */     if (Trace.isOn) {
/* 1579 */       Trace.exit(this, "com.ibm.jms.JMSStreamMessage", "hexToBin(String,int)", retval, 4);
/*      */     }
/* 1581 */     return retval;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\jms\JMSStreamMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */