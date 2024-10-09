/*      */ package com.ibm.msg.client.wmq.compat.base.internal;
/*      */ 
/*      */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.io.BufferedReader;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.DataInputStream;
/*      */ import java.io.DataOutputStream;
/*      */ import java.io.EOFException;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStreamReader;
/*      */ import java.io.OutputStreamWriter;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.nio.ByteBuffer;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class MQMessage
/*      */   extends MQMD
/*      */ {
/*      */   private static final int CHAR_SIZEOF = 2;
/*      */   private static final int CODESET_UCS = 1200;
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQMessage.java";
/*      */   private static final int INT_SIZEOF = 4;
/*      */   protected static final int READ_MODE = 1;
/*      */   protected static final int WRITE_MODE = 2;
/*      */   
/*      */   static {
/*   72 */     if (Trace.isOn) {
/*   73 */       Trace.data("com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQMessage.java");
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
/*   87 */   private int _bufferSizeHint = -1;
/*      */   
/*      */   private int _dataLength;
/*      */   
/*      */   private ByteArrayInputStream _readBytes;
/*      */   
/*      */   private ByteArrayOutputStream _writeBytes;
/*      */   
/*      */   private int cursorPos;
/*      */   
/*      */   private byte[] dataBuffer;
/*      */   
/*      */   private int mode;
/*      */   
/*      */   private DataInputStream readStream;
/*  102 */   private byte[] reversingBuffer = new byte[8];
/*      */   
/*  104 */   private ByteArrayOutputStream reversingOutBytes = new ByteArrayOutputStream();
/*      */ 
/*      */   
/*      */   private DataOutputStream reversingOutStream;
/*      */ 
/*      */   
/*      */   private DataOutputStream writeStream;
/*      */ 
/*      */ 
/*      */   
/*      */   public MQMessage() {
/*  115 */     if (Trace.isOn) {
/*  116 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "<init>()");
/*      */     }
/*  118 */     this.dataBuffer = new byte[0];
/*  119 */     this._readBytes = new ByteArrayInputStream(this.dataBuffer);
/*  120 */     this._writeBytes = new ByteArrayOutputStream(100);
/*  121 */     this.readStream = new DataInputStream(this._readBytes);
/*  122 */     this.writeStream = new DataOutputStream(this._writeBytes);
/*  123 */     this.mode = 2;
/*  124 */     this.cursorPos = 0;
/*  125 */     this._dataLength = 0;
/*  126 */     this.reversingOutStream = new DataOutputStream(this.reversingOutBytes);
/*      */     
/*  128 */     if (Trace.isOn) {
/*  129 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "<init>()");
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
/*      */   public void clearMessage() throws IOException {
/*  144 */     if (Trace.isOn) {
/*  145 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "clearMessage()");
/*      */     }
/*      */     
/*  148 */     reset();
/*  149 */     if (Trace.isOn) {
/*  150 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "clearMessage()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getBufferSizeHint() {
/*  160 */     if (Trace.isOn) {
/*  161 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "getBufferSizeHint()", "getter", 
/*  162 */           Integer.valueOf(this._bufferSizeHint));
/*      */     }
/*  164 */     return this._bufferSizeHint;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private JmqiCodepage getCodepage(int charSetP) throws UnsupportedEncodingException {
/*  174 */     if (Trace.isOn) {
/*  175 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "getCodepage(int)", new Object[] {
/*  176 */             Integer.valueOf(charSetP)
/*      */           });
/*      */     }
/*  179 */     int charSet = charSetP;
/*      */     
/*  181 */     if (charSet == 0) {
/*  182 */       charSet = MQSESSION.getDefaultCCSID();
/*      */     }
/*      */ 
/*      */     
/*  186 */     JmqiCodepage retVal = JmqiCodepage.getJmqiCodepage(MQSESSION.getJmqiEnv(), charSet, this.encoding);
/*      */     
/*  188 */     if (Trace.isOn) {
/*  189 */       Trace.traceData(this, "Mapped " + charSet + " -> " + retVal, null);
/*      */     }
/*      */     
/*  192 */     if (Trace.isOn) {
/*  193 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "getCodepage(int)", retVal);
/*      */     }
/*      */     
/*  196 */     return retVal;
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
/*      */   public int getDataLength() throws IOException {
/*  213 */     if (this.mode != 1) {
/*  214 */       setMode(1);
/*      */     }
/*      */     
/*  217 */     int retVal = this._readBytes.available();
/*      */     
/*  219 */     if (Trace.isOn) {
/*  220 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "getDataLength()", "getter", 
/*  221 */           Integer.valueOf(retVal));
/*      */     }
/*  223 */     return retVal;
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
/*      */   public int getDataOffset() throws IOException {
/*  238 */     if (this.mode != 1) {
/*  239 */       setMode(1);
/*      */     }
/*  241 */     int retVal = this._dataLength - this._readBytes.available();
/*      */     
/*  243 */     if (Trace.isOn) {
/*  244 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "getDataOffset()", "getter", 
/*  245 */           Integer.valueOf(retVal));
/*      */     }
/*  247 */     return retVal;
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
/*      */   public int getMessageLength() throws IOException {
/*  263 */     if (this.mode != 1) {
/*  264 */       setMode(1);
/*      */     }
/*      */     
/*  267 */     if (Trace.isOn) {
/*  268 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "getMessageLength()", "getter", 
/*  269 */           Integer.valueOf(this.dataBuffer.length));
/*      */     }
/*  271 */     return this.dataBuffer.length;
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
/*      */   public byte readByte() throws IOException, EOFException {
/*  283 */     if (Trace.isOn) {
/*  284 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "readByte()");
/*      */     }
/*      */     
/*  287 */     if (this.mode != 1) {
/*  288 */       setMode(1);
/*      */     }
/*      */     
/*  291 */     byte retVal = this.readStream.readByte();
/*      */     
/*  293 */     if (Trace.isOn) {
/*  294 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "readByte()", 
/*  295 */           Byte.valueOf(retVal));
/*      */     }
/*  297 */     return retVal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public char readChar() throws IOException, EOFException {
/*      */     DataInputStream rIn;
/*      */     IOException traceRet1;
/*  309 */     if (Trace.isOn) {
/*  310 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "readChar()");
/*      */     }
/*  312 */     char retVal = Character.MIN_VALUE;
/*      */     
/*  314 */     if (this.mode != 1) {
/*  315 */       setMode(1);
/*      */     }
/*  317 */     switch (this.encoding & 0xF) {
/*      */       case 0:
/*      */       case 1:
/*  320 */         retVal = this.readStream.readChar();
/*      */         break;
/*      */       
/*      */       case 2:
/*  324 */         rIn = readReverse(2);
/*  325 */         retVal = rIn.readChar();
/*      */         break;
/*      */       
/*      */       default:
/*  329 */         if (Trace.isOn) {
/*  330 */           Trace.traceData(this, "Invalid encoding : " + (this.encoding & 0xF00), null);
/*      */         }
/*      */         
/*  333 */         traceRet1 = new IOException("Invalid encoding : " + (this.encoding & 0xF00));
/*      */         
/*  335 */         if (Trace.isOn) {
/*  336 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "readChar()", traceRet1);
/*      */         }
/*      */ 
/*      */         
/*  340 */         throw traceRet1;
/*      */     } 
/*      */     
/*  343 */     if (Trace.isOn) {
/*  344 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "readChar()", 
/*  345 */           Character.valueOf(retVal));
/*      */     }
/*  347 */     return retVal;
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
/*      */   private String readConvertedLine() throws IOException {
/*  361 */     if (Trace.isOn) {
/*  362 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "readConvertedLine()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  368 */     byte[] stringBytes = new byte[getDataLength()];
/*  369 */     System.arraycopy(this.dataBuffer, getDataOffset(), stringBytes, 0, stringBytes.length);
/*  370 */     ByteArrayInputStream bais = new ByteArrayInputStream(stringBytes);
/*      */ 
/*      */ 
/*      */     
/*  374 */     BufferedReader isr = new BufferedReader(new InputStreamReader(bais, getCodepage(this.characterSet).getDecoder()));
/*      */     
/*  376 */     if (Trace.isOn) {
/*  377 */       Trace.traceData(this, "Built InputStreamReader", null);
/*      */     }
/*      */     
/*  380 */     StringBuffer retVal = new StringBuffer(getDataLength());
/*  381 */     String ending = "";
/*      */ 
/*      */ 
/*      */     
/*  385 */     boolean readLine = false;
/*  386 */     while (!readLine) {
/*  387 */       if (isr.ready()) {
/*  388 */         int aChar = isr.read();
/*  389 */         switch (aChar) {
/*      */           
/*      */           case -1:
/*  392 */             readLine = true;
/*      */             continue;
/*      */           case 10:
/*  395 */             ending = "\n";
/*  396 */             readLine = true;
/*      */             continue;
/*      */           case 13:
/*  399 */             readLine = true;
/*  400 */             ending = "\r";
/*      */             
/*  402 */             if (isr.ready()) {
/*  403 */               int anotherChar = isr.read();
/*  404 */               if (anotherChar == 10) {
/*  405 */                 ending = "\r\n";
/*      */               }
/*      */             } 
/*      */             continue;
/*      */         } 
/*      */         
/*  411 */         retVal.append((char)aChar);
/*      */         
/*      */         continue;
/*      */       } 
/*  415 */       readLine = true;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  420 */     int bytesUsed = 0;
/*  421 */     ByteArrayOutputStream baos = new ByteArrayOutputStream();
/*  422 */     OutputStreamWriter osw = new OutputStreamWriter(baos, getCodepage(this.characterSet).getEncoder());
/*  423 */     String charsRead = retVal + ending;
/*  424 */     osw.write(charsRead, 0, charsRead.length());
/*  425 */     osw.flush();
/*  426 */     bytesUsed = (baos.toByteArray()).length;
/*  427 */     this.readStream.skip(bytesUsed);
/*      */     
/*  429 */     if (Trace.isOn) {
/*  430 */       Trace.traceData(this, "Read string: '" + retVal + "', " + bytesUsed + " bytes.", null);
/*      */     }
/*      */ 
/*      */     
/*  434 */     String traceRet1 = retVal.toString();
/*  435 */     if (Trace.isOn) {
/*  436 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "readConvertedLine()", traceRet1);
/*      */     }
/*      */     
/*  439 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void readFully(byte[] b) throws IOException {
/*  450 */     if (Trace.isOn) {
/*  451 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "readFully(byte [ ])", new Object[] { b });
/*      */     }
/*      */ 
/*      */     
/*  455 */     if (this.mode != 1) {
/*  456 */       setMode(1);
/*      */     }
/*  458 */     this.readStream.readFully(b);
/*      */     
/*  460 */     if (Trace.isOn) {
/*  461 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "readFully(byte [ ])");
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
/*      */   public void readFully(byte[] b, int off, int len) throws IOException {
/*  477 */     if (Trace.isOn) {
/*  478 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "readFully(byte [ ],int,int)", new Object[] { b, 
/*  479 */             Integer.valueOf(off), Integer.valueOf(len) });
/*      */     }
/*      */     
/*  482 */     if (this.mode != 1) {
/*  483 */       setMode(1);
/*      */     }
/*  485 */     this.readStream.readFully(b, off, len);
/*  486 */     if (Trace.isOn) {
/*  487 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "readFully(byte [ ],int,int)");
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
/*      */   public int readInt() throws IOException {
/*      */     int retVal;
/*      */     DataInputStream rIn;
/*      */     IOException traceRet1;
/*  502 */     if (Trace.isOn) {
/*  503 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "readInt()");
/*      */     }
/*      */ 
/*      */     
/*  507 */     if (this.mode != 1) {
/*  508 */       setMode(1);
/*      */     }
/*  510 */     switch (this.encoding & 0xF) {
/*      */       case 0:
/*      */       case 1:
/*  513 */         retVal = this.readStream.readInt();
/*      */         break;
/*      */       
/*      */       case 2:
/*  517 */         rIn = readReverse(4);
/*  518 */         retVal = rIn.readInt();
/*      */         break;
/*      */       
/*      */       default:
/*  522 */         if (Trace.isOn) {
/*  523 */           Trace.traceData(this, "Invalid encoding : " + (this.encoding & 0xF00), null);
/*      */         }
/*      */         
/*  526 */         traceRet1 = new IOException("Invalid encoding : " + (this.encoding & 0xF00));
/*      */         
/*  528 */         if (Trace.isOn) {
/*  529 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "readInt()", traceRet1);
/*      */         }
/*      */ 
/*      */         
/*  533 */         throw traceRet1;
/*      */     } 
/*      */     
/*  536 */     if (Trace.isOn) {
/*  537 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "readInt()", 
/*  538 */           Integer.valueOf(retVal));
/*      */     }
/*  540 */     return retVal;
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
/*      */   public String readLine() throws IOException {
/*      */     String traceRet1;
/*  553 */     if (Trace.isOn) {
/*  554 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "readLine()");
/*      */     }
/*  556 */     if (this.mode != 1) {
/*  557 */       setMode(1);
/*      */     }
/*      */     
/*  560 */     switch (this.characterSet) {
/*      */       case 1200:
/*  562 */         traceRet1 = readUnicodeLine();
/*  563 */         if (Trace.isOn) {
/*  564 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "readLine()", traceRet1, 1);
/*      */         }
/*      */         
/*  567 */         return traceRet1;
/*      */     } 
/*      */     
/*  570 */     String traceRet3 = readConvertedLine();
/*  571 */     if (Trace.isOn) {
/*  572 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "readLine()", traceRet3, 2);
/*      */     }
/*      */     
/*  575 */     return traceRet3;
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
/*      */   private final DataInputStream readReverse(int numBytes) throws IOException {
/*  587 */     if (Trace.isOn) {
/*  588 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "readReverse(int)", new Object[] {
/*  589 */             Integer.valueOf(numBytes)
/*      */           });
/*      */     }
/*  592 */     this.readStream.read(this.reversingBuffer, 0, numBytes);
/*  593 */     reverse(this.reversingBuffer, numBytes);
/*  594 */     ByteArrayInputStream bIn = new ByteArrayInputStream(this.reversingBuffer);
/*      */     
/*  596 */     DataInputStream dIS = new DataInputStream(bIn);
/*      */     
/*  598 */     if (Trace.isOn) {
/*  599 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "readReverse(int)", dIS);
/*      */     }
/*      */     
/*  602 */     return dIS;
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
/*      */   public String readStringOfByteLength(int numberOfBytes) throws IOException, EOFException {
/*  623 */     if (Trace.isOn)
/*  624 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "readStringOfByteLength(int)", new Object[] {
/*  625 */             Integer.valueOf(numberOfBytes)
/*      */           }); 
/*  627 */     byte[] b = new byte[numberOfBytes];
/*  628 */     String result = null;
/*  629 */     int originalDataOffset = getDataOffset();
/*      */     
/*  631 */     if (this.mode != 1) {
/*  632 */       setMode(1);
/*      */     }
/*      */     
/*      */     try {
/*  636 */       this.readStream.readFully(b, 0, numberOfBytes);
/*  637 */       result = getCodepage(this.characterSet).bytesToString(b);
/*      */     }
/*  639 */     catch (IOException e) {
/*  640 */       if (Trace.isOn) {
/*  641 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "readStringOfByteLength(int)", e);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  646 */       setDataOffset(originalDataOffset);
/*  647 */       if (Trace.isOn) {
/*  648 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "readStringOfByteLength(int)", e);
/*      */       }
/*      */       
/*  651 */       throw e;
/*      */     } 
/*      */     
/*  654 */     if (Trace.isOn) {
/*  655 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "readStringOfByteLength(int)", result);
/*      */     }
/*      */     
/*  658 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String readUnicodeLine() throws IOException {
/*  669 */     if (Trace.isOn) {
/*  670 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "readUnicodeLine()");
/*      */     }
/*      */ 
/*      */     
/*  674 */     StringBuffer retVal = new StringBuffer(getDataLength() / 2);
/*      */     
/*      */     try {
/*  677 */       boolean endOfLine = false;
/*      */       
/*  679 */       while (!endOfLine) {
/*  680 */         char thisChar = readChar();
/*  681 */         switch (thisChar) {
/*      */           case '\n':
/*  683 */             endOfLine = true;
/*      */             continue;
/*      */           
/*      */           case '\r':
/*  687 */             endOfLine = true;
/*  688 */             if (getDataLength() >= 2) {
/*  689 */               int anotherChar = readChar();
/*  690 */               if (anotherChar != 10)
/*      */               {
/*  692 */                 seek(getDataOffset() - 2);
/*      */               }
/*      */             } 
/*      */             continue;
/*      */         } 
/*      */         
/*  698 */         retVal.append(thisChar);
/*      */       }
/*      */     
/*      */     }
/*  702 */     catch (EOFException ex) {
/*  703 */       if (Trace.isOn) {
/*  704 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "readUnicodeLine()", ex);
/*      */       }
/*      */       
/*  707 */       if (Trace.isOn) {
/*  708 */         Trace.traceData(this, "hit end of file!", null);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  713 */     String traceRet1 = retVal.toString();
/*  714 */     if (Trace.isOn) {
/*  715 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "readUnicodeLine()", traceRet1);
/*      */     }
/*      */     
/*  718 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final synchronized void reset() {
/*  726 */     if (Trace.isOn) {
/*  727 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "reset()");
/*      */     }
/*      */     
/*  730 */     this._writeBytes.reset();
/*  731 */     this.dataBuffer = new byte[0];
/*  732 */     this._readBytes = new ByteArrayInputStream(this.dataBuffer);
/*  733 */     this.readStream = new DataInputStream(this._readBytes);
/*  734 */     this.mode = 2;
/*  735 */     this.cursorPos = 0;
/*  736 */     this._dataLength = 0;
/*      */     
/*  738 */     if (Trace.isOn) {
/*  739 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "reset()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private final void reverse(byte[] buffer, int length) {
/*  746 */     if (Trace.isOn) {
/*  747 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "reverse(byte [ ],int)", new Object[] { buffer, 
/*  748 */             Integer.valueOf(length) });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  753 */     for (int i = 0; i < length / 2; i++) {
/*  754 */       byte temp = buffer[i];
/*  755 */       buffer[i] = buffer[length - 1 - i];
/*  756 */       buffer[length - 1 - i] = temp;
/*      */     } 
/*      */     
/*  759 */     if (Trace.isOn) {
/*  760 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "reverse(byte [ ],int)");
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
/*      */   public void seek(int offset) throws EOFException {
/*  775 */     if (Trace.isOn) {
/*  776 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "seek(int)", new Object[] {
/*  777 */             Integer.valueOf(offset)
/*      */           });
/*      */     }
/*      */     
/*  781 */     if (this.mode != 1) {
/*  782 */       setMode(1);
/*      */     }
/*      */     
/*  785 */     if (offset > this._dataLength) {
/*      */       
/*  787 */       EOFException traceRet1 = new EOFException("offset greater than dataLength");
/*  788 */       if (Trace.isOn) {
/*  789 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "seek(int)", traceRet1);
/*      */       }
/*      */       
/*  792 */       throw traceRet1;
/*      */     } 
/*      */     
/*  795 */     this._readBytes.reset();
/*  796 */     this._readBytes.skip(offset);
/*  797 */     this.cursorPos = offset;
/*      */     
/*  799 */     if (Trace.isOn) {
/*  800 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "seek(int)");
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
/*      */   public void setDataOffset(int offset) throws EOFException {
/*  815 */     if (Trace.isOn) {
/*  816 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "setDataOffset(int)", "setter", 
/*  817 */           Integer.valueOf(offset));
/*      */     }
/*  819 */     seek(offset);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final void setMessageData(ByteBuffer data, int messageLength, int totalMessageLength) {
/*  827 */     if (Trace.isOn) {
/*  828 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "setMessageData(ByteBuffer,int,int)", new Object[] { data, 
/*  829 */             Integer.valueOf(messageLength), 
/*  830 */             Integer.valueOf(totalMessageLength) });
/*      */     }
/*      */ 
/*      */     
/*  834 */     this._writeBytes.reset();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  848 */     if (Trace.isOn) {
/*  849 */       Trace.traceData(this, "Array size mismatch: Making copy of byte array", null);
/*      */     }
/*  851 */     this.dataBuffer = new byte[messageLength];
/*  852 */     System.arraycopy(data.array(), 0, this.dataBuffer, 0, messageLength);
/*      */     
/*  854 */     this._dataLength = messageLength;
/*      */     
/*  856 */     this.cursorPos = 0;
/*  857 */     this._readBytes = new ByteArrayInputStream(this.dataBuffer);
/*  858 */     this.readStream = new DataInputStream(this._readBytes);
/*  859 */     this.mode = 1;
/*      */     
/*  861 */     if (Trace.isOn) {
/*  862 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "setMessageData(ByteBuffer,int,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void setMode(int mode) {
/*  870 */     if (Trace.isOn) {
/*  871 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "setMode(int)", "setter", 
/*  872 */           Integer.valueOf(mode));
/*      */     }
/*      */     
/*  875 */     if (mode != this.mode) {
/*  876 */       byte[] writtenBytes; byte[] tempBuffer; switch (mode) {
/*      */         case 1:
/*  878 */           if (Trace.isOn) {
/*  879 */             Trace.traceData(this, "Switching to read mode", null);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  885 */           writtenBytes = null;
/*  886 */           writtenBytes = this._writeBytes.toByteArray();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  892 */           this._dataLength = this.cursorPos + Math.max(writtenBytes.length, this.dataBuffer.length - this.cursorPos);
/*      */ 
/*      */           
/*  895 */           tempBuffer = new byte[this._dataLength];
/*      */           
/*      */           try {
/*  898 */             System.arraycopy(this.dataBuffer, 0, tempBuffer, 0, this.cursorPos);
/*      */             
/*  900 */             System.arraycopy(writtenBytes, 0, tempBuffer, this.cursorPos, writtenBytes.length);
/*      */             
/*  902 */             int bytesRemaining = this.dataBuffer.length - this.cursorPos - writtenBytes.length;
/*  903 */             if (bytesRemaining > 0) {
/*  904 */               System.arraycopy(this.dataBuffer, this.cursorPos + writtenBytes.length, tempBuffer, this.cursorPos + writtenBytes.length, bytesRemaining);
/*      */             
/*      */             }
/*      */           }
/*  908 */           catch (Exception e) {
/*  909 */             if (Trace.isOn) {
/*  910 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "setMode(int)", e);
/*      */             }
/*      */             
/*  913 */             if (Trace.isOn) {
/*  914 */               Trace.traceData(this, "Exception building message data " + e, null);
/*      */             }
/*      */           } 
/*      */ 
/*      */           
/*  919 */           this.dataBuffer = tempBuffer;
/*  920 */           this._readBytes = new ByteArrayInputStream(this.dataBuffer);
/*  921 */           this.readStream = new DataInputStream(this._readBytes);
/*      */ 
/*      */           
/*  924 */           this.cursorPos += writtenBytes.length;
/*      */ 
/*      */           
/*  927 */           this._readBytes.skip(this.cursorPos);
/*      */ 
/*      */           
/*  930 */           this._writeBytes.reset();
/*      */           
/*  932 */           this.mode = 1;
/*      */           return;
/*      */         
/*      */         case 2:
/*  936 */           if (Trace.isOn) {
/*  937 */             Trace.traceData(this, "Switching to write mode", null);
/*      */           }
/*      */ 
/*      */           
/*  941 */           this.cursorPos = this._dataLength - this._readBytes.available();
/*      */           
/*  943 */           this.mode = 2;
/*      */           return;
/*      */       } 
/*  946 */       if (Trace.isOn) {
/*  947 */         Trace.traceData(this, "Invalid mode : " + mode, null);
/*      */       }
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
/*      */   public int skipBytes(int n) throws IOException, EOFException {
/*  964 */     if (Trace.isOn) {
/*  965 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "skipBytes(int)", new Object[] {
/*  966 */             Integer.valueOf(n)
/*      */           });
/*      */     }
/*  969 */     if (this.mode != 1) {
/*  970 */       setMode(1);
/*      */     }
/*      */     
/*  973 */     int maxBytesToSkip = Math.min(n, this._readBytes.available());
/*      */     
/*  975 */     if (Trace.isOn) {
/*  976 */       Trace.traceData(this, "Skipping " + maxBytesToSkip + " bytes.", null);
/*      */     }
/*      */     
/*  979 */     this.readStream.skipBytes(maxBytesToSkip);
/*  980 */     if (maxBytesToSkip < n) {
/*      */       
/*  982 */       EOFException traceRet1 = new EOFException("insufficient bytes remain");
/*  983 */       if (Trace.isOn) {
/*  984 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "skipBytes(int)", traceRet1);
/*      */       }
/*      */       
/*  987 */       throw traceRet1;
/*      */     } 
/*      */     
/*  990 */     if (Trace.isOn) {
/*  991 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "skipBytes(int)", 
/*  992 */           Integer.valueOf(maxBytesToSkip));
/*      */     }
/*  994 */     return maxBytesToSkip;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final byte[] toByteArray() {
/* 1003 */     if (Trace.isOn) {
/* 1004 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "toByteArray()");
/*      */     }
/* 1006 */     if (this.mode != 1) {
/* 1007 */       setMode(1);
/*      */     }
/* 1009 */     if (Trace.isOn) {
/* 1010 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "toByteArray()", this.dataBuffer);
/*      */     }
/*      */     
/* 1013 */     return this.dataBuffer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void write(byte[] b) throws IOException {
/* 1024 */     if (Trace.isOn) {
/* 1025 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "write(byte [ ])", new Object[] { b });
/*      */     }
/*      */ 
/*      */     
/* 1029 */     if (this.mode != 2) {
/* 1030 */       setMode(2);
/*      */     }
/* 1032 */     this.writeStream.write(b);
/*      */     
/* 1034 */     if (Trace.isOn) {
/* 1035 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "write(byte [ ])");
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
/*      */   public void write(int b) throws IOException {
/* 1049 */     if (Trace.isOn) {
/* 1050 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "write(int)", new Object[] {
/* 1051 */             Integer.valueOf(b)
/*      */           });
/*      */     }
/* 1054 */     if (this.mode != 2) {
/* 1055 */       setMode(2);
/*      */     }
/* 1057 */     this.writeStream.write(b);
/*      */     
/* 1059 */     if (Trace.isOn) {
/* 1060 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "write(int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeChar(int v) throws IOException {
/*      */     byte[] b;
/*      */     IOException traceRet1;
/* 1073 */     if (Trace.isOn) {
/* 1074 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "writeChar(int)", new Object[] {
/* 1075 */             Integer.valueOf(v)
/*      */           });
/*      */     }
/* 1078 */     if (this.mode != 2) {
/* 1079 */       setMode(2);
/*      */     }
/*      */     
/* 1082 */     switch (this.encoding & 0xF) {
/*      */       case 0:
/*      */       case 1:
/* 1085 */         this.writeStream.writeChar(v);
/*      */         break;
/*      */       
/*      */       case 2:
/* 1089 */         this.reversingOutBytes.reset();
/* 1090 */         this.reversingOutStream.writeChar(v);
/* 1091 */         b = this.reversingOutBytes.toByteArray();
/* 1092 */         reverse(b, 2);
/* 1093 */         this.writeStream.write(b);
/*      */         break;
/*      */       
/*      */       default:
/* 1097 */         if (Trace.isOn) {
/* 1098 */           Trace.traceData(this, "Unsupported encoding: " + (this.encoding & 0xF), null);
/*      */         }
/*      */ 
/*      */         
/* 1102 */         traceRet1 = new IOException("Unsupported encoding: " + (this.encoding & 0xF));
/*      */         
/* 1104 */         if (Trace.isOn) {
/* 1105 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "writeChar(int)", traceRet1);
/*      */         }
/*      */ 
/*      */         
/* 1109 */         throw traceRet1;
/*      */     } 
/*      */     
/* 1112 */     if (Trace.isOn) {
/* 1113 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "writeChar(int)");
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
/*      */   public void writeChars(String s) throws IOException {
/* 1126 */     if (Trace.isOn) {
/* 1127 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "writeChars(String)", new Object[] { s });
/*      */     }
/*      */     
/* 1130 */     if (this.mode != 2) {
/* 1131 */       setMode(2);
/*      */     }
/*      */     try {
/* 1134 */       for (int i = 0; i < s.length(); i++) {
/* 1135 */         writeChar(s.charAt(i));
/*      */       }
/*      */     }
/* 1138 */     catch (StringIndexOutOfBoundsException ex) {
/* 1139 */       if (Trace.isOn) {
/* 1140 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "writeChars(String)", ex);
/*      */       }
/*      */       
/* 1143 */       IOException traceRet1 = new IOException(ex.toString());
/* 1144 */       if (Trace.isOn) {
/* 1145 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "writeChars(String)", traceRet1);
/*      */       }
/*      */       
/* 1148 */       throw traceRet1;
/*      */     } 
/* 1150 */     if (Trace.isOn) {
/* 1151 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "writeChars(String)");
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
/*      */   public void writeInt(int v) throws IOException {
/*      */     byte[] b;
/*      */     IOException traceRet1;
/* 1171 */     if (Trace.isOn) {
/* 1172 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "writeInt(int)", new Object[] {
/* 1173 */             Integer.valueOf(v)
/*      */           });
/*      */     }
/* 1176 */     if (this.mode != 2) {
/* 1177 */       setMode(2);
/*      */     }
/*      */     
/* 1180 */     switch (this.encoding & 0xF) {
/*      */       case 0:
/*      */       case 1:
/* 1183 */         this.writeStream.writeInt(v);
/*      */         break;
/*      */       
/*      */       case 2:
/* 1187 */         this.reversingOutBytes.reset();
/* 1188 */         this.reversingOutStream.writeInt(v);
/* 1189 */         b = this.reversingOutBytes.toByteArray();
/* 1190 */         reverse(b, 4);
/* 1191 */         this.writeStream.write(b);
/*      */         break;
/*      */       
/*      */       default:
/* 1195 */         if (Trace.isOn) {
/* 1196 */           Trace.traceData(this, "Unsupported encoding: " + (this.encoding & 0xF), null);
/*      */         }
/*      */ 
/*      */         
/* 1200 */         traceRet1 = new IOException("Unsupported encoding: " + (this.encoding & 0xF));
/*      */         
/* 1202 */         if (Trace.isOn) {
/* 1203 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "writeInt(int)", traceRet1);
/*      */         }
/*      */ 
/*      */         
/* 1207 */         throw traceRet1;
/*      */     } 
/*      */     
/* 1210 */     if (Trace.isOn) {
/* 1211 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "writeInt(int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeString(String s) throws IOException {
/*      */     JmqiCodepage codepage;
/*      */     OutputStreamWriter osw;
/* 1224 */     if (Trace.isOn) {
/* 1225 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "writeString(String)", new Object[] { s });
/*      */     }
/*      */ 
/*      */     
/* 1229 */     if (this.mode != 2) {
/* 1230 */       setMode(2);
/*      */     }
/*      */     
/* 1233 */     switch (this.characterSet) {
/*      */       case 1200:
/* 1235 */         writeChars(s);
/*      */         break;
/*      */       
/*      */       case 0:
/* 1239 */         this.characterSet = MQSESSION.getDefaultCCSID();
/*      */ 
/*      */ 
/*      */       
/*      */       default:
/* 1244 */         codepage = getCodepage(this.characterSet);
/* 1245 */         if (codepage.getCCSID() == 930) {
/*      */ 
/*      */ 
/*      */           
/* 1249 */           byte[] stringBytes = codepage.stringToBytes(s);
/* 1250 */           this.writeStream.write(stringBytes, 0, stringBytes.length);
/*      */ 
/*      */           
/*      */           break;
/*      */         } 
/*      */         
/* 1256 */         osw = new OutputStreamWriter(this.writeStream, codepage.getEncoder());
/* 1257 */         osw.write(s, 0, s.length());
/* 1258 */         osw.flush();
/*      */         break;
/*      */     } 
/*      */ 
/*      */     
/* 1263 */     if (Trace.isOn)
/* 1264 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQMessage", "writeString(String)"); 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\base\internal\MQMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */