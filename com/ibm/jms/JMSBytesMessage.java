/*     */ package com.ibm.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.internal.JmsBytesMessageImpl;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.ObjectStreamField;
/*     */ import javax.jms.BytesMessage;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.Message;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JMSBytesMessage
/*     */   extends JMSMessage
/*     */   implements BytesMessage
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/jms/JMSBytesMessage.java";
/*     */   static final long serialVersionUID = -8017520360760128818L;
/*     */   
/*     */   static {
/* 113 */     if (Trace.isOn) {
/* 114 */       Trace.data("com.ibm.jms.JMSBytesMessage", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/jms/JMSBytesMessage.java");
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
/* 136 */   private static final ObjectStreamField[] serialPersistentFields = new ObjectStreamField[] { new ObjectStreamField("dataBuffer", byte[].class) };
/*     */   
/*     */   private static final String FORCEDESERIALIZEDMESSAGETOREADONLYMODE = "com.ibm.jms.forceDeserializedJMSBytesMessagesToReadOnlyMode";
/*     */   
/*     */   @Deprecated
/*     */   public static final int ENC_INTEGER_MASK = 15;
/*     */   
/*     */   @Deprecated
/*     */   public static final int ENC_FLOAT_MASK = 3840;
/*     */   
/*     */   @Deprecated
/*     */   public static final int ENC_INTEGER_UNDEFINED = 0;
/*     */   @Deprecated
/*     */   public static final int ENC_INTEGER_NORMAL = 1;
/*     */   
/*     */   @Deprecated
/*     */   public JMSBytesMessage() {
/* 153 */     if (Trace.isOn) {
/* 154 */       Trace.entry(this, "com.ibm.jms.JMSBytesMessage", "<init>()");
/*     */     }
/* 156 */     assert false : "Do not call a message's default constructor, use javax.jms.Session methods instead";
/* 157 */     if (Trace.isOn)
/* 158 */       Trace.exit(this, "com.ibm.jms.JMSBytesMessage", "<init>()"); 
/*     */   } @Deprecated
/*     */   public static final int ENC_INTEGER_REVERSED = 2; @Deprecated
/*     */   public static final int ENC_FLOAT_UNDEFINED = 0; @Deprecated
/*     */   public static final int ENC_FLOAT_IEEE_NORMAL = 256; @Deprecated
/*     */   public static final int ENC_FLOAT_IEEE_REVERSED = 512; @Deprecated
/*     */   public static final int ENC_FLOAT_S390 = 768; protected JMSBytesMessage(Message delegateMsg) {
/* 165 */     super(delegateMsg);
/* 166 */     if (Trace.isOn) {
/* 167 */       Trace.entry(this, "com.ibm.jms.JMSBytesMessage", "<init>(Message)", new Object[] { delegateMsg });
/*     */     }
/*     */     
/* 170 */     if (Trace.isOn) {
/* 171 */       Trace.exit(this, "com.ibm.jms.JMSBytesMessage", "<init>(Message)");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getBodyLength() throws JMSException {
/* 259 */     if (Trace.isOn) {
/* 260 */       Trace.entry(this, "com.ibm.jms.JMSBytesMessage", "getBodyLength()");
/*     */     }
/*     */     
/* 263 */     long traceRet1 = ((BytesMessage)this.delegateMsg).getBodyLength();
/* 264 */     if (Trace.isOn) {
/* 265 */       Trace.exit(this, "com.ibm.jms.JMSBytesMessage", "getBodyLength()", Long.valueOf(traceRet1));
/*     */     }
/* 267 */     return traceRet1;
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
/*     */   public boolean readBoolean() throws JMSException {
/* 279 */     if (Trace.isOn) {
/* 280 */       Trace.entry(this, "com.ibm.jms.JMSBytesMessage", "readBoolean()");
/*     */     }
/* 282 */     boolean traceRet1 = ((BytesMessage)this.delegateMsg).readBoolean();
/* 283 */     if (Trace.isOn) {
/* 284 */       Trace.exit(this, "com.ibm.jms.JMSBytesMessage", "readBoolean()", Boolean.valueOf(traceRet1));
/*     */     }
/* 286 */     return traceRet1;
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
/*     */   public byte readByte() throws JMSException {
/* 298 */     if (Trace.isOn) {
/* 299 */       Trace.entry(this, "com.ibm.jms.JMSBytesMessage", "readByte()");
/*     */     }
/* 301 */     byte traceRet1 = ((BytesMessage)this.delegateMsg).readByte();
/* 302 */     if (Trace.isOn) {
/* 303 */       Trace.exit(this, "com.ibm.jms.JMSBytesMessage", "readByte()", Byte.valueOf(traceRet1));
/*     */     }
/* 305 */     return traceRet1;
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
/*     */   public int readBytes(byte[] value) throws JMSException {
/* 321 */     if (Trace.isOn) {
/* 322 */       Trace.entry(this, "com.ibm.jms.JMSBytesMessage", "readBytes(byte [ ])", new Object[] { value });
/*     */     }
/* 324 */     int traceRet1 = ((BytesMessage)this.delegateMsg).readBytes(value);
/* 325 */     if (Trace.isOn) {
/* 326 */       Trace.exit(this, "com.ibm.jms.JMSBytesMessage", "readBytes(byte [ ])", 
/* 327 */           Integer.valueOf(traceRet1));
/*     */     }
/* 329 */     return traceRet1;
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
/*     */   public int readBytes(byte[] value, int length) throws JMSException {
/* 345 */     if (Trace.isOn) {
/* 346 */       Trace.entry(this, "com.ibm.jms.JMSBytesMessage", "readBytes(byte [ ],int)", new Object[] { value, 
/* 347 */             Integer.valueOf(length) });
/*     */     }
/* 349 */     int traceRet1 = ((BytesMessage)this.delegateMsg).readBytes(value, length);
/* 350 */     if (Trace.isOn) {
/* 351 */       Trace.exit(this, "com.ibm.jms.JMSBytesMessage", "readBytes(byte [ ],int)", 
/* 352 */           Integer.valueOf(traceRet1));
/*     */     }
/* 354 */     return traceRet1;
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
/*     */   public char readChar() throws JMSException {
/* 366 */     if (Trace.isOn) {
/* 367 */       Trace.entry(this, "com.ibm.jms.JMSBytesMessage", "readChar()");
/*     */     }
/* 369 */     char traceRet1 = ((BytesMessage)this.delegateMsg).readChar();
/* 370 */     if (Trace.isOn) {
/* 371 */       Trace.exit(this, "com.ibm.jms.JMSBytesMessage", "readChar()", Character.valueOf(traceRet1));
/*     */     }
/* 373 */     return traceRet1;
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
/*     */   public double readDouble() throws JMSException {
/* 385 */     if (Trace.isOn) {
/* 386 */       Trace.entry(this, "com.ibm.jms.JMSBytesMessage", "readDouble()");
/*     */     }
/* 388 */     double traceRet1 = ((BytesMessage)this.delegateMsg).readDouble();
/* 389 */     if (Trace.isOn) {
/* 390 */       Trace.exit(this, "com.ibm.jms.JMSBytesMessage", "readDouble()", Double.valueOf(traceRet1));
/*     */     }
/* 392 */     return traceRet1;
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
/*     */   public float readFloat() throws JMSException {
/* 404 */     if (Trace.isOn) {
/* 405 */       Trace.entry(this, "com.ibm.jms.JMSBytesMessage", "readFloat()");
/*     */     }
/* 407 */     float traceRet1 = ((BytesMessage)this.delegateMsg).readFloat();
/* 408 */     if (Trace.isOn) {
/* 409 */       Trace.exit(this, "com.ibm.jms.JMSBytesMessage", "readFloat()", Float.valueOf(traceRet1));
/*     */     }
/* 411 */     return traceRet1;
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
/*     */   public int readInt() throws JMSException {
/* 423 */     if (Trace.isOn) {
/* 424 */       Trace.entry(this, "com.ibm.jms.JMSBytesMessage", "readInt()");
/*     */     }
/* 426 */     int traceRet1 = ((BytesMessage)this.delegateMsg).readInt();
/* 427 */     if (Trace.isOn) {
/* 428 */       Trace.exit(this, "com.ibm.jms.JMSBytesMessage", "readInt()", Integer.valueOf(traceRet1));
/*     */     }
/* 430 */     return traceRet1;
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
/*     */   public long readLong() throws JMSException {
/* 442 */     if (Trace.isOn) {
/* 443 */       Trace.entry(this, "com.ibm.jms.JMSBytesMessage", "readLong()");
/*     */     }
/* 445 */     long traceRet1 = ((BytesMessage)this.delegateMsg).readLong();
/* 446 */     if (Trace.isOn) {
/* 447 */       Trace.exit(this, "com.ibm.jms.JMSBytesMessage", "readLong()", Long.valueOf(traceRet1));
/*     */     }
/* 449 */     return traceRet1;
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
/*     */   public short readShort() throws JMSException {
/* 461 */     if (Trace.isOn) {
/* 462 */       Trace.entry(this, "com.ibm.jms.JMSBytesMessage", "readShort()");
/*     */     }
/* 464 */     short traceRet1 = ((BytesMessage)this.delegateMsg).readShort();
/* 465 */     if (Trace.isOn) {
/* 466 */       Trace.exit(this, "com.ibm.jms.JMSBytesMessage", "readShort()", Short.valueOf(traceRet1));
/*     */     }
/* 468 */     return traceRet1;
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
/*     */   public int readUnsignedByte() throws JMSException {
/* 480 */     if (Trace.isOn) {
/* 481 */       Trace.entry(this, "com.ibm.jms.JMSBytesMessage", "readUnsignedByte()");
/*     */     }
/* 483 */     int traceRet1 = ((BytesMessage)this.delegateMsg).readUnsignedByte();
/* 484 */     if (Trace.isOn) {
/* 485 */       Trace.exit(this, "com.ibm.jms.JMSBytesMessage", "readUnsignedByte()", 
/* 486 */           Integer.valueOf(traceRet1));
/*     */     }
/* 488 */     return traceRet1;
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
/*     */   public int readUnsignedShort() throws JMSException {
/* 500 */     if (Trace.isOn) {
/* 501 */       Trace.entry(this, "com.ibm.jms.JMSBytesMessage", "readUnsignedShort()");
/*     */     }
/* 503 */     int traceRet1 = ((BytesMessage)this.delegateMsg).readUnsignedShort();
/* 504 */     if (Trace.isOn) {
/* 505 */       Trace.exit(this, "com.ibm.jms.JMSBytesMessage", "readUnsignedShort()", 
/* 506 */           Integer.valueOf(traceRet1));
/*     */     }
/* 508 */     return traceRet1;
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
/*     */   public String readUTF() throws JMSException {
/* 520 */     if (Trace.isOn) {
/* 521 */       Trace.entry(this, "com.ibm.jms.JMSBytesMessage", "readUTF()");
/*     */     }
/* 523 */     String traceRet1 = ((BytesMessage)this.delegateMsg).readUTF();
/* 524 */     if (Trace.isOn) {
/* 525 */       Trace.exit(this, "com.ibm.jms.JMSBytesMessage", "readUTF()", traceRet1);
/*     */     }
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
/*     */   
/*     */   public void reset() throws JMSException {
/* 539 */     if (Trace.isOn) {
/* 540 */       Trace.entry(this, "com.ibm.jms.JMSBytesMessage", "reset()");
/*     */     }
/* 542 */     ((BytesMessage)this.delegateMsg).reset();
/* 543 */     if (Trace.isOn) {
/* 544 */       Trace.exit(this, "com.ibm.jms.JMSBytesMessage", "reset()");
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
/*     */   public void writeBoolean(boolean value) throws JMSException {
/* 560 */     if (Trace.isOn)
/* 561 */       Trace.entry(this, "com.ibm.jms.JMSBytesMessage", "writeBoolean(boolean)", new Object[] {
/* 562 */             Boolean.valueOf(value)
/*     */           }); 
/* 564 */     ((BytesMessage)this.delegateMsg).writeBoolean(value);
/* 565 */     if (Trace.isOn) {
/* 566 */       Trace.exit(this, "com.ibm.jms.JMSBytesMessage", "writeBoolean(boolean)");
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
/*     */   public void writeByte(byte value) throws JMSException {
/* 580 */     if (Trace.isOn)
/* 581 */       Trace.entry(this, "com.ibm.jms.JMSBytesMessage", "writeByte(byte)", new Object[] {
/* 582 */             Byte.valueOf(value)
/*     */           }); 
/* 584 */     ((BytesMessage)this.delegateMsg).writeByte(value);
/* 585 */     if (Trace.isOn) {
/* 586 */       Trace.exit(this, "com.ibm.jms.JMSBytesMessage", "writeByte(byte)");
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
/*     */   public void writeBytes(byte[] value) throws JMSException {
/* 600 */     if (Trace.isOn) {
/* 601 */       Trace.entry(this, "com.ibm.jms.JMSBytesMessage", "writeBytes(byte [ ])", new Object[] { value });
/*     */     }
/*     */     
/* 604 */     ((BytesMessage)this.delegateMsg).writeBytes(value);
/* 605 */     if (Trace.isOn) {
/* 606 */       Trace.exit(this, "com.ibm.jms.JMSBytesMessage", "writeBytes(byte [ ])");
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
/*     */   public void writeBytes(byte[] value, int offset, int length) throws JMSException {
/* 622 */     if (Trace.isOn) {
/* 623 */       Trace.entry(this, "com.ibm.jms.JMSBytesMessage", "writeBytes(byte [ ],int,int)", new Object[] { value, 
/* 624 */             Integer.valueOf(offset), Integer.valueOf(length) });
/*     */     }
/* 626 */     ((BytesMessage)this.delegateMsg).writeBytes(value, offset, length);
/* 627 */     if (Trace.isOn) {
/* 628 */       Trace.exit(this, "com.ibm.jms.JMSBytesMessage", "writeBytes(byte [ ],int,int)");
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
/*     */   public void writeChar(char value) throws JMSException {
/* 642 */     if (Trace.isOn)
/* 643 */       Trace.entry(this, "com.ibm.jms.JMSBytesMessage", "writeChar(char)", new Object[] {
/* 644 */             Character.valueOf(value)
/*     */           }); 
/* 646 */     ((BytesMessage)this.delegateMsg).writeChar(value);
/* 647 */     if (Trace.isOn) {
/* 648 */       Trace.exit(this, "com.ibm.jms.JMSBytesMessage", "writeChar(char)");
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
/*     */   public void writeDouble(double value) throws JMSException {
/* 663 */     if (Trace.isOn)
/* 664 */       Trace.entry(this, "com.ibm.jms.JMSBytesMessage", "writeDouble(double)", new Object[] {
/* 665 */             Double.valueOf(value)
/*     */           }); 
/* 667 */     ((BytesMessage)this.delegateMsg).writeDouble(value);
/* 668 */     if (Trace.isOn) {
/* 669 */       Trace.exit(this, "com.ibm.jms.JMSBytesMessage", "writeDouble(double)");
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
/*     */   public void writeFloat(float value) throws JMSException {
/* 685 */     if (Trace.isOn)
/* 686 */       Trace.entry(this, "com.ibm.jms.JMSBytesMessage", "writeFloat(float)", new Object[] {
/* 687 */             Float.valueOf(value)
/*     */           }); 
/* 689 */     ((BytesMessage)this.delegateMsg).writeFloat(value);
/* 690 */     if (Trace.isOn) {
/* 691 */       Trace.exit(this, "com.ibm.jms.JMSBytesMessage", "writeFloat(float)");
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
/*     */   public void writeInt(int value) throws JMSException {
/* 706 */     if (Trace.isOn)
/* 707 */       Trace.entry(this, "com.ibm.jms.JMSBytesMessage", "writeInt(int)", new Object[] {
/* 708 */             Integer.valueOf(value)
/*     */           }); 
/* 710 */     ((BytesMessage)this.delegateMsg).writeInt(value);
/* 711 */     if (Trace.isOn) {
/* 712 */       Trace.exit(this, "com.ibm.jms.JMSBytesMessage", "writeInt(int)");
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
/*     */   public void writeLong(long value) throws JMSException {
/* 727 */     if (Trace.isOn)
/* 728 */       Trace.entry(this, "com.ibm.jms.JMSBytesMessage", "writeLong(long)", new Object[] {
/* 729 */             Long.valueOf(value)
/*     */           }); 
/* 731 */     ((BytesMessage)this.delegateMsg).writeLong(value);
/* 732 */     if (Trace.isOn) {
/* 733 */       Trace.exit(this, "com.ibm.jms.JMSBytesMessage", "writeLong(long)");
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
/*     */   public void writeObject(Object value) throws JMSException {
/* 753 */     if (Trace.isOn) {
/* 754 */       Trace.entry(this, "com.ibm.jms.JMSBytesMessage", "writeObject(Object)", new Object[] { value });
/*     */     }
/* 756 */     ((BytesMessage)this.delegateMsg).writeObject(value);
/* 757 */     if (Trace.isOn) {
/* 758 */       Trace.exit(this, "com.ibm.jms.JMSBytesMessage", "writeObject(Object)");
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
/*     */   public void writeShort(short value) throws JMSException {
/* 773 */     if (Trace.isOn)
/* 774 */       Trace.entry(this, "com.ibm.jms.JMSBytesMessage", "writeShort(short)", new Object[] {
/* 775 */             Short.valueOf(value)
/*     */           }); 
/* 777 */     ((BytesMessage)this.delegateMsg).writeShort(value);
/* 778 */     if (Trace.isOn) {
/* 779 */       Trace.exit(this, "com.ibm.jms.JMSBytesMessage", "writeShort(short)");
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
/*     */   public void writeUTF(String value) throws JMSException {
/* 794 */     if (Trace.isOn) {
/* 795 */       Trace.entry(this, "com.ibm.jms.JMSBytesMessage", "writeUTF(String)", new Object[] { value });
/*     */     }
/* 797 */     ((BytesMessage)this.delegateMsg).writeUTF(value);
/* 798 */     if (Trace.isOn) {
/* 799 */       Trace.exit(this, "com.ibm.jms.JMSBytesMessage", "writeUTF(String)");
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
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 811 */     if (Trace.isOn) {
/* 812 */       Trace.entry(this, "com.ibm.jms.JMSBytesMessage", "readObject(java.io.ObjectInputStream)", new Object[] { in });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 822 */       String connectionType = "com.ibm.msg.client.wmq";
/* 823 */       Message superClassMsg = null;
/* 824 */       if (this.delegateMsg != null) {
/* 825 */         superClassMsg = this.delegateMsg;
/*     */       }
/* 827 */       this.delegateMsg = (Message)new JmsBytesMessageImpl(connectionType, superClassMsg);
/*     */     }
/* 829 */     catch (JMSException e) {
/* 830 */       if (Trace.isOn) {
/* 831 */         Trace.catchBlock(this, "com.ibm.jms.JMSBytesMessage", "readObject(java.io.ObjectInputStream)", (Throwable)e, 1);
/*     */       }
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
/* 843 */     ObjectInputStream.GetField fields = in.readFields();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 861 */       if (!fields.defaulted("dataBuffer")) {
/* 862 */         writeBytes((byte[])fields.get("dataBuffer", (Object)null));
/*     */       }
/*     */     }
/* 865 */     catch (JMSException je) {
/* 866 */       if (Trace.isOn) {
/* 867 */         Trace.catchBlock(this, "com.ibm.jms.JMSBytesMessage", "readObject(java.io.ObjectInputStream)", (Throwable)je, 2);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 877 */     if (Trace.isOn) {
/* 878 */       Trace.traceData(this, "checking the property com.ibm.jms.forceDeserializedJMSBytesMessagesToReadOnlyMode to see if the message needs to be returned in read-only or write-only mode", null);
/*     */     }
/*     */ 
/*     */     
/* 882 */     PropertyStore.register("com.ibm.jms.forceDeserializedJMSBytesMessagesToReadOnlyMode", false);
/* 883 */     boolean returnMsgInReadOnlyMode = PropertyStore.getBooleanPropertyObject("com.ibm.jms.forceDeserializedJMSBytesMessagesToReadOnlyMode").booleanValue();
/*     */     
/* 885 */     if (returnMsgInReadOnlyMode) {
/* 886 */       if (Trace.isOn) {
/* 887 */         Trace.traceData(this, "read-only mode requested - calling reset() on the message", null);
/*     */       }
/*     */       try {
/* 890 */         reset();
/*     */       }
/* 892 */       catch (JMSException je) {
/* 893 */         if (Trace.isOn) {
/* 894 */           Trace.catchBlock(this, "com.ibm.jms.JMSBytesMessage", "readObject(java.io.ObjectInputStream)", (Throwable)je, 3);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 899 */         if (Trace.isOn) {
/* 900 */           Trace.catchBlock(this, "com.ibm.jms.JMSBytesMessage", "readObject(java.io.ObjectInputStream)", (Throwable)je, 3);
/*     */           
/* 902 */           Trace.traceData(this, "the call to reset() failed. Returning the message in write-only mode", null);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 909 */     if (Trace.isOn) {
/* 910 */       Trace.exit(this, "com.ibm.jms.JMSBytesMessage", "readObject(java.io.ObjectInputStream)");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 916 */     if (Trace.isOn) {
/* 917 */       Trace.entry(this, "com.ibm.jms.JMSBytesMessage", "writeObject(java.io.ObjectOutputStream)", new Object[] { out });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 925 */     ObjectOutputStream.PutField fields = out.putFields();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 932 */       reset();
/*     */       
/* 934 */       byte[] data = new byte[(int)getBodyLength()];
/* 935 */       int result = readBytes(data);
/* 936 */       if (result != -1) {
/* 937 */         fields.put("dataBuffer", data);
/*     */       }
/*     */     }
/* 940 */     catch (JMSException je) {
/* 941 */       if (Trace.isOn) {
/* 942 */         Trace.catchBlock(this, "com.ibm.jms.JMSBytesMessage", "writeObject(java.io.ObjectOutputStream)", (Throwable)je);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 951 */     out.writeFields();
/*     */     
/* 953 */     if (Trace.isOn)
/* 954 */       Trace.exit(this, "com.ibm.jms.JMSBytesMessage", "writeObject(java.io.ObjectOutputStream)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\jms\JMSBytesMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */