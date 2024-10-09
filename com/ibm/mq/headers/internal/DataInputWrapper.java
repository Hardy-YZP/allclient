/*     */ package com.ibm.mq.headers.internal;
/*     */ 
/*     */ import com.ibm.mq.headers.MQDataException;
/*     */ import com.ibm.mq.headers.internal.store.ByteStore;
/*     */ import com.ibm.mq.headers.internal.store.Store;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.Method;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class DataInputWrapper
/*     */   extends MessageWrapper
/*     */ {
/*     */   static {
/* 341 */     if (Trace.isOn) {
/* 342 */       Trace.data("com.ibm.mq.headers.internal.DataInputWrapper", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/MessageWrapper.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 348 */   private DataInput delegate = null;
/* 349 */   private Method mark = null;
/* 350 */   private Method reset = null;
/* 351 */   private String format = "        ";
/*     */   
/*     */   DataInputWrapper(DataInput in) throws MQDataException {
/* 354 */     if (Trace.isOn) {
/* 355 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataInputWrapper", "<init>(DataInput)", new Object[] { in });
/*     */     }
/*     */     
/* 358 */     this.delegate = in;
/*     */ 
/*     */     
/*     */     try {
/* 362 */       this.mark = in.getClass().getMethod("mark", new Class[] { int.class });
/* 363 */       this.reset = in.getClass().getMethod("reset", new Class[0]);
/*     */     }
/* 365 */     catch (Exception e) {
/* 366 */       if (Trace.isOn) {
/* 367 */         Trace.catchBlock(this, "com.ibm.mq.headers.internal.DataInputWrapper", "<init>(DataInput)", e);
/*     */       }
/*     */       
/* 370 */       MQDataException traceRet1 = MQDataException.getMQDataException(e);
/* 371 */       if (Trace.isOn) {
/* 372 */         Trace.throwing(this, "com.ibm.mq.headers.internal.DataInputWrapper", "<init>(DataInput)", (Throwable)traceRet1);
/*     */       }
/*     */       
/* 375 */       throw traceRet1;
/*     */     } 
/* 377 */     if (Trace.isOn) {
/* 378 */       Trace.exit(this, "com.ibm.mq.headers.internal.DataInputWrapper", "<init>(DataInput)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getDelegate() {
/* 385 */     if (Trace.isOn) {
/* 386 */       Trace.data(this, "com.ibm.mq.headers.internal.DataInputWrapper", "getDelegate()", "getter", this.delegate);
/*     */     }
/*     */     
/* 389 */     return this.delegate;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDataOffset() {
/* 395 */     UnsupportedOperationException traceRet1 = new UnsupportedOperationException(HeaderMessages.getMessage("MQHDR0001"));
/* 396 */     if (Trace.isOn) {
/* 397 */       Trace.throwing(this, "com.ibm.mq.headers.internal.DataInputWrapper", "getDataOffset()", traceRet1);
/*     */     }
/*     */     
/* 400 */     throw traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void seek(int offset) {
/* 405 */     if (Trace.isOn) {
/* 406 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataInputWrapper", "seek(int)", new Object[] {
/* 407 */             Integer.valueOf(offset)
/*     */           });
/*     */     }
/* 410 */     UnsupportedOperationException traceRet1 = new UnsupportedOperationException(HeaderMessages.getMessage("MQHDR0001"));
/* 411 */     if (Trace.isOn) {
/* 412 */       Trace.throwing(this, "com.ibm.mq.headers.internal.DataInputWrapper", "seek(int)", traceRet1);
/*     */     }
/*     */     
/* 415 */     throw traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeBytes(String s) {
/* 420 */     if (Trace.isOn) {
/* 421 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataInputWrapper", "writeBytes(String)", new Object[] { s });
/*     */     }
/*     */ 
/*     */     
/* 425 */     UnsupportedOperationException traceRet1 = new UnsupportedOperationException(HeaderMessages.getMessage("MQHDR0001"));
/* 426 */     if (Trace.isOn) {
/* 427 */       Trace.throwing(this, "com.ibm.mq.headers.internal.DataInputWrapper", "writeBytes(String)", traceRet1);
/*     */     }
/*     */     
/* 430 */     throw traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int readInt() throws IOException {
/* 435 */     if (Trace.isOn) {
/* 436 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataInputWrapper", "readInt()");
/*     */     }
/* 438 */     int traceRet1 = this.delegate.readInt();
/* 439 */     if (Trace.isOn) {
/* 440 */       Trace.exit(this, "com.ibm.mq.headers.internal.DataInputWrapper", "readInt()", 
/* 441 */           Integer.valueOf(traceRet1));
/*     */     }
/* 443 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeString(String string) {
/* 448 */     if (Trace.isOn) {
/* 449 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataInputWrapper", "writeString(String)", new Object[] { string });
/*     */     }
/*     */ 
/*     */     
/* 453 */     UnsupportedOperationException traceRet1 = new UnsupportedOperationException(HeaderMessages.getMessage("MQHDR0001"));
/* 454 */     if (Trace.isOn) {
/* 455 */       Trace.throwing(this, "com.ibm.mq.headers.internal.DataInputWrapper", "writeString(String)", traceRet1);
/*     */     }
/*     */     
/* 458 */     throw traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeInt(int i) {
/* 463 */     if (Trace.isOn) {
/* 464 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataInputWrapper", "writeInt(int)", new Object[] {
/* 465 */             Integer.valueOf(i)
/*     */           });
/*     */     }
/* 468 */     UnsupportedOperationException traceRet1 = new UnsupportedOperationException(HeaderMessages.getMessage("MQHDR0001"));
/* 469 */     if (Trace.isOn) {
/* 470 */       Trace.throwing(this, "com.ibm.mq.headers.internal.DataInputWrapper", "writeInt(int)", traceRet1);
/*     */     }
/*     */     
/* 473 */     throw traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCharacterSet() {
/* 478 */     if (Trace.isOn) {
/* 479 */       Trace.data(this, "com.ibm.mq.headers.internal.DataInputWrapper", "getCharacterSet()", "getter", 
/* 480 */           Integer.valueOf(0));
/*     */     }
/* 482 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getEncoding() {
/* 487 */     if (Trace.isOn) {
/* 488 */       Trace.data(this, "com.ibm.mq.headers.internal.DataInputWrapper", "getEncoding()", "getter", 
/* 489 */           Integer.valueOf(273));
/*     */     }
/* 491 */     return 273;
/*     */   }
/*     */ 
/*     */   
/*     */   public Store getStore(int encoding, int characterSet, int size) throws IOException {
/* 496 */     if (Trace.isOn)
/* 497 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataInputWrapper", "getStore(int,int,int)", new Object[] {
/* 498 */             Integer.valueOf(encoding), Integer.valueOf(characterSet), 
/* 499 */             Integer.valueOf(size)
/*     */           }); 
/* 501 */     ByteStore byteStore = new ByteStore(this.delegate, encoding, characterSet, size);
/* 502 */     if (Trace.isOn) {
/* 503 */       Trace.exit(this, "com.ibm.mq.headers.internal.DataInputWrapper", "getStore(int,int,int)", byteStore);
/*     */     }
/*     */     
/* 506 */     return (Store)byteStore;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DataOutput getReversed() {
/* 513 */     UnsupportedOperationException traceRet1 = new UnsupportedOperationException(HeaderMessages.getMessage("MQHDR0001"));
/* 514 */     if (Trace.isOn) {
/* 515 */       Trace.throwing(this, "com.ibm.mq.headers.internal.DataInputWrapper", "getReversed()", traceRet1);
/*     */     }
/*     */     
/* 518 */     throw traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void readFully(byte[] b, int off, int len) throws IOException {
/* 523 */     if (Trace.isOn) {
/* 524 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataInputWrapper", "readFully(byte [ ],int,int)", new Object[] { b, 
/* 525 */             Integer.valueOf(off), Integer.valueOf(len) });
/*     */     }
/*     */     
/* 528 */     this.delegate.readFully(b, off, len);
/* 529 */     if (Trace.isOn) {
/* 530 */       Trace.exit(this, "com.ibm.mq.headers.internal.DataInputWrapper", "readFully(byte [ ],int,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readFully(byte[] b) throws IOException {
/* 538 */     if (Trace.isOn) {
/* 539 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataInputWrapper", "readFully(byte [ ])", new Object[] { b });
/*     */     }
/*     */     
/* 542 */     this.delegate.readFully(b);
/* 543 */     if (Trace.isOn) {
/* 544 */       Trace.exit(this, "com.ibm.mq.headers.internal.DataInputWrapper", "readFully(byte [ ])");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(byte[] b, int off, int len) throws IOException {
/* 551 */     if (Trace.isOn) {
/* 552 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataInputWrapper", "write(byte [ ],int,int)", new Object[] { b, 
/* 553 */             Integer.valueOf(off), Integer.valueOf(len) });
/*     */     }
/*     */     
/* 556 */     UnsupportedOperationException traceRet1 = new UnsupportedOperationException(HeaderMessages.getMessage("MQHDR0001"));
/* 557 */     if (Trace.isOn) {
/* 558 */       Trace.throwing(this, "com.ibm.mq.headers.internal.DataInputWrapper", "write(byte [ ],int,int)", traceRet1);
/*     */     }
/*     */     
/* 561 */     throw traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(byte[] b) throws IOException {
/* 566 */     if (Trace.isOn) {
/* 567 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataInputWrapper", "write(byte [ ])", new Object[] { b });
/*     */     }
/*     */ 
/*     */     
/* 571 */     UnsupportedOperationException traceRet1 = new UnsupportedOperationException(HeaderMessages.getMessage("MQHDR0001"));
/* 572 */     if (Trace.isOn) {
/* 573 */       Trace.throwing(this, "com.ibm.mq.headers.internal.DataInputWrapper", "write(byte [ ])", traceRet1);
/*     */     }
/*     */     
/* 576 */     throw traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFormat() {
/* 581 */     if (Trace.isOn) {
/* 582 */       Trace.data(this, "com.ibm.mq.headers.internal.DataInputWrapper", "getFormat()", "getter", this.format);
/*     */     }
/*     */     
/* 585 */     return this.format;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTotalMessageLength() {
/* 591 */     UnsupportedOperationException traceRet1 = new UnsupportedOperationException(HeaderMessages.getMessage("MQHDR0001"));
/* 592 */     if (Trace.isOn) {
/* 593 */       Trace.throwing(this, "com.ibm.mq.headers.internal.DataInputWrapper", "getTotalMessageLength()", traceRet1);
/*     */     }
/*     */     
/* 596 */     throw traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void shuffle(int from, int to, int length) throws IOException {
/* 601 */     if (Trace.isOn) {
/* 602 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataInputWrapper", "shuffle(int,int,int)", new Object[] {
/* 603 */             Integer.valueOf(from), Integer.valueOf(to), Integer.valueOf(length)
/*     */           });
/*     */     }
/* 606 */     UnsupportedOperationException traceRet1 = new UnsupportedOperationException(HeaderMessages.getMessage("MQHDR0001"));
/* 607 */     if (Trace.isOn) {
/* 608 */       Trace.throwing(this, "com.ibm.mq.headers.internal.DataInputWrapper", "shuffle(int,int,int)", traceRet1);
/*     */     }
/*     */     
/* 611 */     throw traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void resizeBuffer(int size) {
/* 616 */     if (Trace.isOn) {
/* 617 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataInputWrapper", "resizeBuffer(int)", new Object[] {
/* 618 */             Integer.valueOf(size)
/*     */           });
/*     */     }
/* 621 */     if (Trace.isOn) {
/* 622 */       Trace.exit(this, "com.ibm.mq.headers.internal.DataInputWrapper", "resizeBuffer(int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isMQMessage() {
/* 629 */     if (Trace.isOn) {
/* 630 */       Trace.data(this, "com.ibm.mq.headers.internal.DataInputWrapper", "isMQMessage()", "getter", 
/* 631 */           Boolean.valueOf(false));
/*     */     }
/* 633 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMessageType() throws IOException {
/*     */     int type;
/*     */     try {
/* 640 */       this.mark.invoke(this.delegate, new Object[] { Integer.valueOf(4) });
/* 641 */       type = this.delegate.readInt();
/* 642 */       this.reset.invoke(this.delegate, new Object[0]);
/*     */     
/*     */     }
/* 645 */     catch (RuntimeException rte) {
/* 646 */       if (Trace.isOn) {
/* 647 */         Trace.catchBlock(this, "com.ibm.mq.headers.internal.DataInputWrapper", "getMessageType()", rte, 1);
/*     */       }
/*     */       
/* 650 */       if (Trace.isOn) {
/* 651 */         Trace.throwing(this, "com.ibm.mq.headers.internal.DataInputWrapper", "getMessageType()", rte, 1);
/*     */       }
/*     */       
/* 654 */       throw rte;
/*     */     }
/* 656 */     catch (Exception e) {
/* 657 */       if (Trace.isOn) {
/* 658 */         Trace.catchBlock(this, "com.ibm.mq.headers.internal.DataInputWrapper", "getMessageType()", e, 2);
/*     */       }
/*     */       
/* 661 */       EOFException traceRet1 = new EOFException();
/* 662 */       if (Trace.isOn) {
/* 663 */         Trace.throwing(this, "com.ibm.mq.headers.internal.DataInputWrapper", "getMessageType()", traceRet1, 2);
/*     */       }
/*     */       
/* 666 */       throw traceRet1;
/*     */     } 
/* 668 */     if (Trace.isOn) {
/* 669 */       Trace.data(this, "com.ibm.mq.headers.internal.DataInputWrapper", "getMessageType()", "getter", 
/* 670 */           Integer.valueOf(type));
/*     */     }
/* 672 */     return type;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean readBoolean() throws IOException {
/* 677 */     if (Trace.isOn) {
/* 678 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataInputWrapper", "readBoolean()");
/*     */     }
/* 680 */     boolean traceRet1 = this.delegate.readBoolean();
/* 681 */     if (Trace.isOn) {
/* 682 */       Trace.exit(this, "com.ibm.mq.headers.internal.DataInputWrapper", "readBoolean()", 
/* 683 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 685 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte readByte() throws IOException {
/* 690 */     if (Trace.isOn) {
/* 691 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataInputWrapper", "readByte()");
/*     */     }
/* 693 */     byte traceRet1 = this.delegate.readByte();
/* 694 */     if (Trace.isOn) {
/* 695 */       Trace.exit(this, "com.ibm.mq.headers.internal.DataInputWrapper", "readByte()", 
/* 696 */           Byte.valueOf(traceRet1));
/*     */     }
/* 698 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public char readChar() throws IOException {
/* 703 */     if (Trace.isOn) {
/* 704 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataInputWrapper", "readChar()");
/*     */     }
/* 706 */     char traceRet1 = this.delegate.readChar();
/* 707 */     if (Trace.isOn) {
/* 708 */       Trace.exit(this, "com.ibm.mq.headers.internal.DataInputWrapper", "readChar()", 
/* 709 */           Character.valueOf(traceRet1));
/*     */     }
/* 711 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public double readDouble() throws IOException {
/* 716 */     if (Trace.isOn) {
/* 717 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataInputWrapper", "readDouble()");
/*     */     }
/* 719 */     double traceRet1 = this.delegate.readDouble();
/* 720 */     if (Trace.isOn) {
/* 721 */       Trace.exit(this, "com.ibm.mq.headers.internal.DataInputWrapper", "readDouble()", 
/* 722 */           Double.valueOf(traceRet1));
/*     */     }
/* 724 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public float readFloat() throws IOException {
/* 729 */     if (Trace.isOn) {
/* 730 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataInputWrapper", "readFloat()");
/*     */     }
/* 732 */     float traceRet1 = this.delegate.readFloat();
/* 733 */     if (Trace.isOn) {
/* 734 */       Trace.exit(this, "com.ibm.mq.headers.internal.DataInputWrapper", "readFloat()", 
/* 735 */           Float.valueOf(traceRet1));
/*     */     }
/* 737 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public String readLine() throws IOException {
/* 742 */     if (Trace.isOn) {
/* 743 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataInputWrapper", "readLine()");
/*     */     }
/* 745 */     String traceRet1 = this.delegate.readLine();
/* 746 */     if (Trace.isOn) {
/* 747 */       Trace.exit(this, "com.ibm.mq.headers.internal.DataInputWrapper", "readLine()", traceRet1);
/*     */     }
/* 749 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public long readLong() throws IOException {
/* 754 */     if (Trace.isOn) {
/* 755 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataInputWrapper", "readLong()");
/*     */     }
/* 757 */     long traceRet1 = this.delegate.readLong();
/* 758 */     if (Trace.isOn) {
/* 759 */       Trace.exit(this, "com.ibm.mq.headers.internal.DataInputWrapper", "readLong()", 
/* 760 */           Long.valueOf(traceRet1));
/*     */     }
/* 762 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public short readShort() throws IOException {
/* 767 */     if (Trace.isOn) {
/* 768 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataInputWrapper", "readShort()");
/*     */     }
/* 770 */     short traceRet1 = this.delegate.readShort();
/* 771 */     if (Trace.isOn) {
/* 772 */       Trace.exit(this, "com.ibm.mq.headers.internal.DataInputWrapper", "readShort()", 
/* 773 */           Short.valueOf(traceRet1));
/*     */     }
/* 775 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public String readUTF() throws IOException {
/* 780 */     if (Trace.isOn) {
/* 781 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataInputWrapper", "readUTF()");
/*     */     }
/* 783 */     String traceRet1 = this.delegate.readUTF();
/* 784 */     if (Trace.isOn) {
/* 785 */       Trace.exit(this, "com.ibm.mq.headers.internal.DataInputWrapper", "readUTF()", traceRet1);
/*     */     }
/* 787 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int readUnsignedByte() throws IOException {
/* 792 */     if (Trace.isOn) {
/* 793 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataInputWrapper", "readUnsignedByte()");
/*     */     }
/* 795 */     int traceRet1 = this.delegate.readUnsignedByte();
/* 796 */     if (Trace.isOn) {
/* 797 */       Trace.exit(this, "com.ibm.mq.headers.internal.DataInputWrapper", "readUnsignedByte()", 
/* 798 */           Integer.valueOf(traceRet1));
/*     */     }
/* 800 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int readUnsignedShort() throws IOException {
/* 805 */     if (Trace.isOn) {
/* 806 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataInputWrapper", "readUnsignedShort()");
/*     */     }
/* 808 */     int traceRet1 = this.delegate.readUnsignedShort();
/* 809 */     if (Trace.isOn) {
/* 810 */       Trace.exit(this, "com.ibm.mq.headers.internal.DataInputWrapper", "readUnsignedShort()", 
/* 811 */           Integer.valueOf(traceRet1));
/*     */     }
/* 813 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int skipBytes(int n) throws IOException {
/* 818 */     if (Trace.isOn)
/* 819 */       Trace.entry(this, "com.ibm.mq.headers.internal.DataInputWrapper", "skipBytes(int)", new Object[] {
/* 820 */             Integer.valueOf(n)
/*     */           }); 
/* 822 */     int traceRet1 = this.delegate.skipBytes(n);
/* 823 */     if (Trace.isOn) {
/* 824 */       Trace.exit(this, "com.ibm.mq.headers.internal.DataInputWrapper", "skipBytes(int)", 
/* 825 */           Integer.valueOf(traceRet1));
/*     */     }
/* 827 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\internal\DataInputWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */