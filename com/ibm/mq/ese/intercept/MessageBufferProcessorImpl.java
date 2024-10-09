/*      */ package com.ibm.mq.ese.intercept;
/*      */ 
/*      */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*      */ import com.ibm.mq.jmqi.JmqiException;
/*      */ import com.ibm.mq.jmqi.JmqiMQ;
/*      */ import com.ibm.mq.jmqi.MQDLH;
/*      */ import com.ibm.mq.jmqi.MQHeader;
/*      */ import com.ibm.mq.jmqi.MQMD;
/*      */ import com.ibm.mq.jmqi.MQRFH;
/*      */ import com.ibm.mq.jmqi.handles.Pint;
/*      */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*      */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*      */ import com.ibm.mq.jmqi.system.JmqiComponentTls;
/*      */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*      */ import com.ibm.mq.jmqi.system.JmqiTls;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.CharBuffer;
/*      */ import java.util.ArrayList;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class MessageBufferProcessorImpl
/*      */   implements MessageBufferProcessor
/*      */ {
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/intercept/MessageBufferProcessorImpl.java";
/*      */   private static final int PTR_SIZE = 4;
/*      */   private JmqiEnvironment env;
/*      */   private JmqiMQ jmqi;
/*      */   private MQMD mqmd;
/*      */   private ByteBuffer[] messageBuffers;
/*      */   private int payloadIndex;
/*      */   private int payloadPos;
/*      */   private int payloadLength;
/*      */   
/*      */   static {
/*   58 */     if (Trace.isOn) {
/*   59 */       Trace.data("com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/intercept/MessageBufferProcessorImpl.java");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  118 */   private List<MQHeaderAccessor> headers = new ArrayList<>(4);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int payloadEncoding;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int payloadCcsid;
/*      */ 
/*      */ 
/*      */   
/*      */   private String payloadFormat;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MessageBufferProcessorImpl(JmqiEnvironment env, JmqiMQ jmqi) {
/*  138 */     if (Trace.isOn) {
/*  139 */       Trace.entry(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "<init>(JmqiEnvironment,JmqiMQ)", new Object[] { env, jmqi });
/*      */     }
/*      */     
/*  142 */     this.env = env;
/*  143 */     this.jmqi = jmqi;
/*  144 */     if (Trace.isOn) {
/*  145 */       Trace.exit(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "<init>(JmqiEnvironment,JmqiMQ)");
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
/*      */   public void wrap(MQMD mqmd, ByteBuffer[] buffers) {
/*  160 */     if (Trace.isOn) {
/*  161 */       Trace.entry(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "wrap(MQMD,ByteBuffer [ ])", new Object[] { mqmd, buffers });
/*      */     }
/*      */     
/*  164 */     this.mqmd = mqmd;
/*  165 */     this.messageBuffers = buffers;
/*      */     
/*  167 */     if (Trace.isOn) {
/*  168 */       Trace.exit(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "wrap(MQMD,ByteBuffer [ ])");
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
/*      */   public void wrap(MQMD mqmd, ByteBuffer buffer) {
/*  183 */     if (Trace.isOn) {
/*  184 */       Trace.entry(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "wrap(MQMD,ByteBuffer)", new Object[] { mqmd, buffer });
/*      */     }
/*      */     
/*  187 */     this.mqmd = mqmd;
/*  188 */     this.messageBuffers = new ByteBuffer[1];
/*  189 */     this.messageBuffers[0] = buffer;
/*      */     
/*  191 */     if (Trace.isOn) {
/*  192 */       Trace.exit(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "wrap(MQMD,ByteBuffer)");
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
/*      */   public void process(int defaultCCSID) throws JmqiException {
/*  205 */     if (Trace.isOn)
/*  206 */       Trace.entry(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "process(int)", new Object[] {
/*  207 */             Integer.valueOf(defaultCCSID)
/*      */           }); 
/*  209 */     JmqiTls jTls = getTLS();
/*      */ 
/*      */     
/*  212 */     int encoding = this.mqmd.getEncoding();
/*  213 */     int ccsid = this.mqmd.getCodedCharSetId();
/*  214 */     if (ccsid == 0) {
/*  215 */       ccsid = defaultCCSID;
/*      */     }
/*  217 */     JmqiCodepage cp = JmqiCodepage.getJmqiCodepage(this.env, ccsid);
/*      */     
/*  219 */     if (cp == null) {
/*  220 */       JmqiException traceRet1 = new JmqiException(this.env, 6047, new String[] { Integer.toString(ccsid), null, null, null, "???" }, 2, 2195, null);
/*      */       
/*  222 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/*  226 */     String format = this.mqmd.getFormat();
/*  227 */     if (format == null) {
/*  228 */       format = "        ";
/*      */     }
/*      */     
/*  231 */     if (this.messageBuffers == null) {
/*  232 */       setPayloadData(encoding, ccsid, format, 0, 0, 0);
/*  233 */       if (Trace.isOn) {
/*  234 */         Trace.exit(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "process(int)", 1);
/*      */       }
/*      */       return;
/*      */     } 
/*  238 */     if (this.messageBuffers[0] == null) {
/*  239 */       setPayloadData(encoding, ccsid, format, 0, 0, 0);
/*  240 */       if (Trace.isOn) {
/*  241 */         Trace.exit(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "process(int)", 2);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*  246 */     if ("        ".equals(format)) {
/*  247 */       ByteBuffer byteBuffer = this.messageBuffers[0];
/*  248 */       setPayloadData(encoding, ccsid, format, 0, 0, byteBuffer.limit());
/*  249 */       if (Trace.isOn) {
/*  250 */         Trace.exit(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "process(int)", 3);
/*      */       }
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  257 */     long formatLong = 0L;
/*      */     try {
/*  259 */       formatLong = MQHeader.convertFormatToLong(this.env, cp, format);
/*      */     }
/*  261 */     catch (NullPointerException e) {
/*  262 */       if (Trace.isOn) {
/*  263 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "process(int)", e);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  270 */       JmqiException traceRet1 = new JmqiException(this.env, 0, new String[0], 2, 2111, new UnsupportedEncodingException(String.valueOf(ccsid)));
/*  271 */       if (Trace.isOn) {
/*  272 */         Trace.throwing(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "process(int)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  275 */       throw traceRet1;
/*      */     } 
/*      */     
/*  278 */     for (int i = 0; i < this.messageBuffers.length; i++) {
/*  279 */       int pos = 0;
/*  280 */       ByteBuffer byteBuffer = this.messageBuffers[i];
/*  281 */       if (byteBuffer == null) {
/*  282 */         setPayloadData(encoding, ccsid, format, 0, 0, 0);
/*      */       } else {
/*      */         
/*  285 */         byte[] byteArr = byteBuffer.array();
/*  286 */         if (byteArr == null)
/*  287 */         { setPayloadData(encoding, ccsid, format, 0, 0, 0); }
/*      */         else
/*      */         
/*      */         { while (true)
/*      */           
/*  292 */           { if (formatLong == 5571313732236222496L || formatLong == -3109514705028104128L || formatLong == 5571313732235042848L || formatLong == -3109514705039769536L || formatLong == 5571313710729076768L || formatLong == -3109514726539444160L || formatLong == 5571313753762832416L || formatLong == -3109514649145950144L || formatLong == 5571313732220756000L || formatLong == -3109514705047764928L || formatLong == 5571314810489937952L || formatLong == -3109513626533216192L || formatLong == 5571308195975208992L || formatLong == -3109520271357099968L || formatLong == 5571313672159188000L || formatLong == -3109514795173092544L || formatLong == 5571309278272430112L || formatLong == -3109519189060861888L || formatLong == 5571313676570543648L || formatLong == -3109514790645283264L) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  313 */               if (Trace.isOn) {
/*  314 */                 Trace.data("com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "process()", "Found routing header ", 
/*  315 */                     Long.valueOf(formatLong));
/*      */               }
/*      */ 
/*      */               
/*  319 */               boolean swap = ((encoding & 0xF) == 2);
/*      */               
/*  321 */               cp = JmqiCodepage.getJmqiCodepage(this.env, ccsid);
/*      */               
/*  323 */               if (cp == null) {
/*  324 */                 JmqiException traceRet1 = new JmqiException(this.env, 6047, new String[] { Integer.toString(ccsid), null, null, null, "???" }, 2, 2195, null);
/*      */                 
/*  326 */                 throw traceRet1;
/*      */               } 
/*      */ 
/*      */               
/*  330 */               if (pos >= byteArr.length) {
/*      */                 break;
/*      */               }
/*      */               
/*  334 */               if (formatLong == 5571309278272430112L || formatLong == -3109519189060861888L) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*  345 */                 MQDLH dlh = this.env.newMQDLH();
/*  346 */                 dlh.readFromBuffer(byteArr, pos, 4, swap, cp, jTls);
/*      */                 
/*  348 */                 addDLQHeader(i, pos, 172, dlh);
/*  349 */                 pos += 172;
/*  350 */                 encoding = dlh.getEncoding();
/*  351 */                 ccsid = dlh.getCodedCharSetId();
/*  352 */                 if (ccsid == 0) {
/*  353 */                   ccsid = defaultCCSID;
/*      */                 }
/*  355 */                 format = dlh.getFormat();
/*  356 */                 formatLong = MQHeader.convertFormatToLong(this.env, cp, format); continue;
/*      */               } 
/*  358 */               if (formatLong == 5571314810489937952L || formatLong == -3109513626533216192L || formatLong == 5571308195975208992L || formatLong == -3109520271357099968L) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*  371 */                 MQHeader mQHeader = this.env.newMQHeader();
/*  372 */                 mQHeader.readFromBuffer(byteArr, pos, 4, swap, cp, jTls, true);
/*      */                 
/*  374 */                 addHeader(i, pos, mQHeader.getStrucLength(), mQHeader);
/*  375 */                 pos += mQHeader.getStrucLength();
/*  376 */                 encoding = mQHeader.getEncoding();
/*      */                 
/*  378 */                 format = mQHeader.getFormat();
/*  379 */                 formatLong = mQHeader.getFormatLong(cp);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*      */                 continue;
/*      */               } 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  391 */               MQHeader header = this.env.newMQHeader();
/*  392 */               header.readFromBuffer(byteArr, pos, 4, swap, cp, jTls, true);
/*      */               
/*  394 */               addHeader(i, pos, header.getStrucLength(), header);
/*  395 */               pos += header.getStrucLength();
/*  396 */               encoding = header.getEncoding();
/*  397 */               ccsid = header.getCodedCharSetId();
/*  398 */               if (ccsid == 0) {
/*  399 */                 ccsid = defaultCCSID;
/*      */               }
/*  401 */               format = header.getFormat();
/*  402 */               formatLong = header.getFormatLong(cp);
/*      */               continue;
/*      */             } 
/*  405 */             setPayloadData(encoding, ccsid, format, i, pos, byteBuffer.limit() - pos); break; }  } 
/*      */       } 
/*  407 */     }  if (Trace.isOn) {
/*  408 */       Trace.exit(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "process(int)", 4);
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
/*      */   private void addHeader(int hdrIndex, int hdrPos, int strucLength, MQHeader hdr) {
/*  427 */     if (Trace.isOn)
/*  428 */       Trace.entry(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "addHeader(int,int,int,MQHeader)", new Object[] {
/*  429 */             Integer.valueOf(hdrIndex), 
/*  430 */             Integer.valueOf(hdrPos), Integer.valueOf(strucLength), hdr
/*      */           }); 
/*  432 */     this.headers.add(new MQHeaderAccessor(hdrIndex, hdrPos, strucLength, hdr));
/*  433 */     if (Trace.isOn) {
/*  434 */       Trace.exit(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "addHeader(int,int,int,MQHeader)");
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
/*      */   private void addDLQHeader(int hdrIndex, int hdrPos, int strucLength, MQDLH hdr) {
/*  453 */     if (Trace.isOn)
/*  454 */       Trace.entry(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "addDLQHeader(int,int,int,MQDLH)", new Object[] {
/*  455 */             Integer.valueOf(hdrIndex), 
/*  456 */             Integer.valueOf(hdrPos), Integer.valueOf(strucLength), hdr
/*      */           }); 
/*  458 */     this.headers.add(new MQHeaderAccessor(hdrIndex, hdrPos, strucLength, hdr));
/*  459 */     if (Trace.isOn) {
/*  460 */       Trace.exit(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "addDLQHeader(int,int,int,MQDLH)");
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
/*      */   private void setPayloadData(int encoding, int ccsid, String format, int index, int pos, int length) throws JmqiException {
/*  485 */     if (Trace.isOn)
/*  486 */       Trace.entry(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "setPayloadData(int,int,String,int,int,int)", new Object[] {
/*  487 */             Integer.valueOf(encoding), 
/*  488 */             Integer.valueOf(ccsid), format, Integer.valueOf(index), Integer.valueOf(pos), 
/*  489 */             Integer.valueOf(length)
/*      */           }); 
/*  491 */     if (index < 0 || pos < 0 || length < 0) {
/*  492 */       JmqiException traceRet1 = new JmqiException(this.env, -1, new String[0], 2, 2142, new IllegalArgumentException());
/*      */ 
/*      */ 
/*      */       
/*  496 */       if (Trace.isOn) {
/*  497 */         Trace.throwing(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "setPayloadData(int,int,String,int,int,int)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  500 */       throw traceRet1;
/*      */     } 
/*  502 */     this.payloadEncoding = encoding;
/*  503 */     this.payloadCcsid = ccsid;
/*  504 */     this.payloadFormat = format;
/*  505 */     this.payloadIndex = index;
/*  506 */     this.payloadPos = pos;
/*  507 */     this.payloadLength = length;
/*  508 */     if (Trace.isOn) {
/*  509 */       Trace.exit(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "setPayloadData(int,int,String,int,int,int)");
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
/*      */   private JmqiTls getTLS() {
/*  521 */     JmqiSystemEnvironment sysenv = (JmqiSystemEnvironment)this.env;
/*  522 */     int componentId = this.jmqi.getTlsComponentId();
/*  523 */     JmqiComponentTls componentTls = sysenv.getComponentTls(componentId);
/*  524 */     JmqiTls jTls = sysenv.getJmqiTls(componentTls);
/*  525 */     if (Trace.isOn) {
/*  526 */       Trace.data(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "getTLS()", "getter", jTls);
/*      */     }
/*      */     
/*  529 */     return jTls;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPayloadCcsid() {
/*  539 */     if (Trace.isOn) {
/*  540 */       Trace.data(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "getPayloadCcsid()", "getter", 
/*  541 */           Integer.valueOf(this.payloadCcsid));
/*      */     }
/*  543 */     return this.payloadCcsid;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPayloadEncoding() {
/*  553 */     if (Trace.isOn) {
/*  554 */       Trace.data(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "getPayloadEncoding()", "getter", 
/*  555 */           Integer.valueOf(this.payloadEncoding));
/*      */     }
/*  557 */     return this.payloadEncoding;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getPayloadFormat() {
/*  567 */     if (Trace.isOn) {
/*  568 */       Trace.data(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "getPayloadFormat()", "getter", this.payloadFormat);
/*      */     }
/*      */     
/*  571 */     return this.payloadFormat;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPayloadIndex() {
/*  581 */     if (Trace.isOn) {
/*  582 */       Trace.data(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "getPayloadIndex()", "getter", 
/*  583 */           Integer.valueOf(this.payloadIndex));
/*      */     }
/*  585 */     return this.payloadIndex;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPayloadLength() {
/*  595 */     if (Trace.isOn) {
/*  596 */       Trace.data(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "getPayloadLength()", "getter", 
/*  597 */           Integer.valueOf(this.payloadLength));
/*      */     }
/*  599 */     return this.payloadLength;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getPayloadOnly() {
/*  609 */     if (Trace.isOn) {
/*  610 */       Trace.entry(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "getPayloadOnly()");
/*      */     }
/*  612 */     if (this.messageBuffers == null) {
/*  613 */       if (Trace.isOn) {
/*  614 */         Trace.exit(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "getPayloadOnly()", null, 1);
/*      */       }
/*      */       
/*  617 */       return null;
/*      */     } 
/*  619 */     if (this.payloadIndex < 0 || this.payloadIndex >= this.messageBuffers.length) {
/*  620 */       IllegalStateException traceRet1 = new IllegalStateException("Wrong payloadIndex value: " + this.payloadIndex);
/*      */ 
/*      */       
/*  623 */       if (Trace.isOn) {
/*  624 */         Trace.throwing(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "getPayloadOnly()", traceRet1);
/*      */       }
/*      */       
/*  627 */       throw traceRet1;
/*      */     } 
/*  629 */     ByteBuffer buffer = this.messageBuffers[this.payloadIndex];
/*  630 */     if (buffer == null || buffer.array() == null) {
/*  631 */       if (Trace.isOn) {
/*  632 */         Trace.exit(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "getPayloadOnly()", null, 2);
/*      */       }
/*      */       
/*  635 */       return null;
/*      */     } 
/*  637 */     byte[] array = buffer.array();
/*      */ 
/*      */     
/*  640 */     if (this.payloadPos == 0 && this.payloadLength == array.length) {
/*  641 */       if (Trace.isOn) {
/*  642 */         Trace.exit(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "getPayloadOnly()", array, 3);
/*      */       }
/*      */       
/*  645 */       return array;
/*      */     } 
/*  647 */     byte[] payloadData = new byte[this.payloadLength];
/*  648 */     System.arraycopy(array, this.payloadPos, payloadData, 0, this.payloadLength);
/*  649 */     if (Trace.isOn) {
/*  650 */       Trace.exit(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "getPayloadOnly()", payloadData, 4);
/*      */     }
/*      */     
/*  653 */     return payloadData;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPayloadPos() {
/*  663 */     if (Trace.isOn) {
/*  664 */       Trace.data(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "getPayloadPos()", "getter", 
/*  665 */           Integer.valueOf(this.payloadPos));
/*      */     }
/*  667 */     return this.payloadPos;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean containsHeaders() {
/*  677 */     if (Trace.isOn) {
/*  678 */       Trace.entry(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "containsHeaders()");
/*      */     }
/*  680 */     boolean traceRet1 = (this.headers != null && this.headers.size() > 0);
/*  681 */     if (Trace.isOn) {
/*  682 */       Trace.exit(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "containsHeaders()", 
/*  683 */           Boolean.valueOf(traceRet1));
/*      */     }
/*  685 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ByteBuffer[] getAllBuffers() {
/*  695 */     if (Trace.isOn) {
/*  696 */       Trace.data(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "getAllBuffers()", "getter", this.messageBuffers);
/*      */     }
/*      */     
/*  699 */     return this.messageBuffers;
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
/*      */   public ByteBuffer getEntireBuffer() {
/*  711 */     ByteBuffer traceRet1 = (this.messageBuffers != null && this.messageBuffers.length > 0) ? this.messageBuffers[0] : ByteBuffer.allocate(0);
/*  712 */     if (Trace.isOn) {
/*  713 */       Trace.data(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "getEntireBuffer()", "getter", traceRet1);
/*      */     }
/*      */     
/*  716 */     return traceRet1;
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
/*      */   public void setPayloadFormat(String format, int defaultCCSID) throws JmqiException {
/*  729 */     if (Trace.isOn) {
/*  730 */       Trace.entry(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "setPayloadFormat(String,int)", new Object[] { format, 
/*  731 */             Integer.valueOf(defaultCCSID) });
/*      */     }
/*  733 */     setPayloadFormatInternal(format, defaultCCSID);
/*  734 */     this.payloadFormat = format;
/*  735 */     if (Trace.isOn) {
/*  736 */       Trace.exit(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "setPayloadFormat(String,int)");
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
/*      */   private void setPayloadFormatInternal(String newValue, int defaultCCSID) throws JmqiException {
/*  753 */     if (Trace.isOn) {
/*  754 */       Trace.entry(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "setPayloadFormatInternal(String,int)", new Object[] { newValue, 
/*  755 */             Integer.valueOf(defaultCCSID) });
/*      */     }
/*      */     
/*  758 */     if (containsHeaders()) {
/*      */       
/*  760 */       int lastHeaderIndex = this.headers.size() - 1;
/*  761 */       MQHeaderAccessor lastHeader = this.headers.get(lastHeaderIndex);
/*      */ 
/*      */ 
/*      */       
/*  765 */       int headerCCSID = 0;
/*  766 */       int encoding = 0;
/*      */       
/*  768 */       if (lastHeaderIndex > 0) {
/*  769 */         MQHeaderAccessor lastButOneHeader = this.headers.get(lastHeaderIndex - 1);
/*  770 */         if (lastButOneHeader.dlqHeader != null) {
/*  771 */           headerCCSID = lastButOneHeader.dlqHeader.getCodedCharSetId();
/*  772 */           encoding = lastButOneHeader.dlqHeader.getEncoding();
/*      */         } else {
/*  774 */           headerCCSID = lastButOneHeader.header.getCodedCharSetId();
/*  775 */           encoding = lastButOneHeader.header.getEncoding();
/*      */         } 
/*      */       } else {
/*  778 */         headerCCSID = this.mqmd.getCodedCharSetId();
/*  779 */         encoding = this.mqmd.getEncoding();
/*      */       } 
/*  781 */       if (headerCCSID <= 0) {
/*  782 */         headerCCSID = defaultCCSID;
/*      */       }
/*  784 */       int lastHdrIndex = lastHeader.index;
/*  785 */       int lastHdrPos = lastHeader.pos;
/*  786 */       JmqiCodepage cp = JmqiCodepage.getJmqiCodepage(this.env, headerCCSID);
/*  787 */       if (cp == null) {
/*  788 */         JmqiException traceRet1 = new JmqiException(this.env, 6047, new String[] { Integer.toString(headerCCSID), null, null, null, "???" }, 2, 2195, null);
/*      */         
/*  790 */         throw traceRet1;
/*      */       } 
/*      */ 
/*      */       
/*  794 */       if (lastHeader.dlqHeader != null) {
/*  795 */         lastHeader.dlqHeader.setFormat(newValue);
/*      */       } else {
/*  797 */         lastHeader.header.setFormat(newValue);
/*  798 */         long formatLong = MQHeader.convertFormatToLong(this.env, cp, newValue);
/*  799 */         lastHeader.header.setFormatLong(cp, formatLong);
/*      */       } 
/*      */       
/*  802 */       boolean swap = ((encoding & 0xF) == 2);
/*  803 */       if ((this.messageBuffers[lastHdrIndex].array()).length >= 4 && (this.messageBuffers[lastHdrIndex]
/*  804 */         .array()).length + lastHdrPos >= 4)
/*      */       {
/*  806 */         if (lastHeader.dlqHeader != null) {
/*  807 */           lastHeader.dlqHeader.writeToBuffer(this.messageBuffers[lastHdrIndex].array(), lastHdrPos, 4, swap, cp, this.jmqi);
/*      */         } else {
/*  809 */           lastHeader.header.writeToBuffer(this.messageBuffers[lastHdrIndex].array(), lastHdrPos, 4, swap, cp, this.jmqi);
/*      */         } 
/*      */       }
/*      */     } else {
/*  813 */       if (this.mqmd == null) {
/*  814 */         IllegalStateException traceRet1 = new IllegalStateException("Buffer contains neither MQMD nor MQ Headers");
/*      */ 
/*      */         
/*  817 */         if (Trace.isOn) {
/*  818 */           Trace.throwing(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "setPayloadFormatInternal(String,int)", traceRet1);
/*      */         }
/*      */         
/*  821 */         throw traceRet1;
/*      */       } 
/*  823 */       this.mqmd.setFormat(newValue);
/*      */     } 
/*  825 */     if (Trace.isOn) {
/*  826 */       Trace.exit(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "setPayloadFormatInternal(String,int)");
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
/*      */   public void setPayloadEncodingCcsid(int encoding, int ccsid) throws JmqiException {
/*  842 */     if (Trace.isOn)
/*  843 */       Trace.entry(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "setPayloadEncodingCcsid(int,int)", new Object[] {
/*  844 */             Integer.valueOf(encoding), 
/*  845 */             Integer.valueOf(ccsid)
/*      */           }); 
/*  847 */     setPayloadEncodingCcsidInternal(encoding, ccsid);
/*  848 */     this.payloadEncoding = encoding;
/*  849 */     this.payloadCcsid = ccsid;
/*  850 */     if (Trace.isOn) {
/*  851 */       Trace.exit(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "setPayloadEncodingCcsid(int,int)");
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
/*      */   private void setPayloadEncodingCcsidInternal(int newEncoding, int newCcsid) throws JmqiException {
/*  865 */     if (Trace.isOn)
/*  866 */       Trace.entry(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "setPayloadEncodingCcsidInternal(int,int)", new Object[] {
/*  867 */             Integer.valueOf(newEncoding), 
/*  868 */             Integer.valueOf(newCcsid)
/*      */           }); 
/*  870 */     if (containsHeaders()) {
/*  871 */       Pint hdrCCSID = this.env.newPint();
/*  872 */       Pint hdrEncoding = this.env.newPint();
/*  873 */       Pint hdrIndex = this.env.newPint();
/*  874 */       Pint hdrPos = this.env.newPint();
/*  875 */       MQHeaderAccessor lastHeader = getLastHeaderInfo(hdrCCSID, hdrEncoding, hdrIndex, hdrPos);
/*  876 */       JmqiCodepage cp = JmqiCodepage.getJmqiCodepage(this.env, hdrCCSID.x);
/*  877 */       if (cp == null) {
/*  878 */         JmqiException traceRet1 = new JmqiException(this.env, 6047, new String[] { Integer.toString(hdrCCSID.x), null, null, null, "???" }, 2, 2195, null);
/*      */         
/*  880 */         throw traceRet1;
/*      */       } 
/*  882 */       boolean swap = ((hdrEncoding.x & 0xF) == 2);
/*  883 */       if (lastHeader.dlqHeader != null) {
/*  884 */         lastHeader.dlqHeader.setEncoding(newEncoding);
/*  885 */         lastHeader.dlqHeader.setCodedCharSetId(newCcsid);
/*  886 */         lastHeader.dlqHeader.writeToBuffer(this.messageBuffers[hdrIndex.x].array(), hdrPos.x, 4, swap, cp, this.jmqi);
/*      */       } else {
/*  888 */         lastHeader.header.setEncoding(newEncoding);
/*  889 */         lastHeader.header.setCodedCharSetId(newCcsid);
/*  890 */         lastHeader.header.writeToBuffer(this.messageBuffers[hdrIndex.x].array(), hdrPos.x, 4, swap, cp, this.jmqi);
/*      */       } 
/*      */     } else {
/*  893 */       if (this.mqmd == null) {
/*  894 */         IllegalStateException traceRet1 = new IllegalStateException("Buffer contains neither MQMD nor MQ Headers");
/*      */ 
/*      */         
/*  897 */         if (Trace.isOn) {
/*  898 */           Trace.throwing(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "setPayloadEncodingCcsidInternal(int,int)", traceRet1);
/*      */         }
/*      */         
/*  901 */         throw traceRet1;
/*      */       } 
/*  903 */       this.mqmd.setEncoding(newEncoding);
/*  904 */       this.mqmd.setCodedCharSetId(newCcsid);
/*      */     } 
/*  906 */     if (Trace.isOn) {
/*  907 */       Trace.exit(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "setPayloadEncodingCcsidInternal(int,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private MQHeaderAccessor getLastHeaderInfo(Pint hdrCCSID, Pint hdrEncoding, Pint hdrIndex, Pint hdrPos) {
/*  915 */     if (Trace.isOn) {
/*  916 */       Trace.entry(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "getLastHeaderInfo(Pint,Pint,Pint,Pint)", new Object[] { hdrCCSID, hdrEncoding, hdrIndex, hdrPos });
/*      */     }
/*      */ 
/*      */     
/*  920 */     int lastHeaderIndex = this.headers.size() - 1;
/*      */     
/*  922 */     MQHeaderAccessor lastHeader = this.headers.get(lastHeaderIndex);
/*  923 */     if (lastHeaderIndex > 0) {
/*  924 */       MQHeaderAccessor lastButOneHeader = this.headers.get(lastHeaderIndex - 1);
/*  925 */       if (lastButOneHeader.dlqHeader != null) {
/*  926 */         hdrCCSID.x = lastButOneHeader.dlqHeader.getCodedCharSetId();
/*  927 */         hdrEncoding.x = lastButOneHeader.dlqHeader.getEncoding();
/*      */       } else {
/*  929 */         hdrCCSID.x = lastButOneHeader.header.getCodedCharSetId();
/*  930 */         hdrEncoding.x = lastButOneHeader.header.getEncoding();
/*      */       } 
/*      */     } else {
/*  933 */       hdrCCSID.x = this.mqmd.getCodedCharSetId();
/*  934 */       hdrEncoding.x = this.mqmd.getEncoding();
/*      */     } 
/*  936 */     hdrIndex.x = lastHeader.index;
/*  937 */     hdrPos.x = lastHeader.pos;
/*  938 */     if (Trace.isOn) {
/*  939 */       Trace.exit(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "getLastHeaderInfo(Pint,Pint,Pint,Pint)", lastHeader);
/*      */     }
/*      */     
/*  942 */     return lastHeader;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static class MQHeaderAccessor
/*      */   {
/*      */     private int index;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private int pos;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private MQHeader header;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private MQDLH dlqHeader;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public MQHeaderAccessor(int index, int pos, int length, MQHeader header) {
/*  972 */       if (Trace.isOn) {
/*  973 */         Trace.entry(this, "com.ibm.mq.ese.intercept.MQHeaderAccessor", "<init>(int,int,int,MQHeader)", new Object[] {
/*  974 */               Integer.valueOf(index), Integer.valueOf(pos), Integer.valueOf(length), header
/*      */             });
/*      */       }
/*  977 */       this.index = index;
/*  978 */       this.pos = pos;
/*  979 */       this.header = header;
/*  980 */       this.dlqHeader = null;
/*  981 */       if (Trace.isOn) {
/*  982 */         Trace.exit(this, "com.ibm.mq.ese.intercept.MQHeaderAccessor", "<init>(int,int,int,MQHeader)");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     public MQHeaderAccessor(int index, int pos, int length, MQDLH dlqHeader) {
/*  988 */       if (Trace.isOn) {
/*  989 */         Trace.entry(this, "com.ibm.mq.ese.intercept.MQHeaderAccessor", "<init>(int,int,int,MQDLH)", new Object[] {
/*  990 */               Integer.valueOf(index), Integer.valueOf(pos), Integer.valueOf(length), dlqHeader
/*      */             });
/*      */       }
/*  993 */       this.index = index;
/*  994 */       this.pos = pos;
/*  995 */       this.header = null;
/*  996 */       this.dlqHeader = dlqHeader;
/*  997 */       if (Trace.isOn) {
/*  998 */         Trace.exit(this, "com.ibm.mq.ese.intercept.MQHeaderAccessor", "<init>(int,int,int,MQDLH)");
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
/*      */   public ByteBuffer[] setProtectedPayload(byte[] protectedDataWithAmbiHeader) {
/* 1013 */     if (Trace.isOn) {
/* 1014 */       Trace.entry(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "setProtectedPayload(byte [ ])", new Object[] { protectedDataWithAmbiHeader });
/*      */     }
/*      */     
/* 1017 */     if (this.messageBuffers == null) {
/* 1018 */       this.messageBuffers = new ByteBuffer[1];
/*      */     }
/* 1020 */     if (this.payloadIndex < 0 || this.payloadIndex >= this.messageBuffers.length) {
/* 1021 */       IllegalStateException traceRet1 = new IllegalStateException("Wrong payloadIndex value: " + this.payloadIndex);
/*      */       
/* 1023 */       if (Trace.isOn) {
/* 1024 */         Trace.throwing(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "setProtectedPayload(byte [ ])", traceRet1);
/*      */       }
/*      */       
/* 1027 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/* 1031 */     ByteBuffer payloadBuffer = this.messageBuffers[this.payloadIndex];
/*      */     
/* 1033 */     if (payloadBuffer == null || payloadBuffer.array() == null) {
/*      */ 
/*      */       
/* 1036 */       payloadBuffer = ByteBuffer.wrap(protectedDataWithAmbiHeader);
/* 1037 */       this.messageBuffers[this.payloadIndex] = payloadBuffer;
/* 1038 */       if (Trace.isOn) {
/* 1039 */         Trace.exit(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "setProtectedPayload(byte [ ])", this.messageBuffers, 1);
/*      */       }
/*      */       
/* 1042 */       return this.messageBuffers;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1048 */     if (this.payloadPos > 0) {
/* 1049 */       byte[] allData = payloadBuffer.array();
/* 1050 */       int mqHeadersLength = this.payloadPos;
/*      */       
/* 1052 */       payloadBuffer = ByteBuffer.allocate(mqHeadersLength + protectedDataWithAmbiHeader.length);
/*      */ 
/*      */       
/* 1055 */       System.arraycopy(allData, 0, payloadBuffer
/* 1056 */           .array(), 0, mqHeadersLength);
/*      */ 
/*      */       
/* 1059 */       System.arraycopy(protectedDataWithAmbiHeader, 0, payloadBuffer
/* 1060 */           .array(), mqHeadersLength, protectedDataWithAmbiHeader.length);
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 1065 */       payloadBuffer = ByteBuffer.wrap(protectedDataWithAmbiHeader);
/*      */     } 
/*      */ 
/*      */     
/* 1069 */     this.messageBuffers[this.payloadIndex] = payloadBuffer;
/*      */     
/* 1071 */     if (Trace.isOn) {
/* 1072 */       Trace.exit(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "setProtectedPayload(byte [ ])", this.messageBuffers, 2);
/*      */     }
/*      */     
/* 1075 */     return this.messageBuffers;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMqHeadersLength() {
/* 1086 */     int payloadIndex = getPayloadIndex();
/* 1087 */     int pos = getPayloadPos();
/* 1088 */     int ret = 0;
/* 1089 */     for (int i = 0; i < payloadIndex; i++) {
/* 1090 */       ret += this.messageBuffers[i].limit();
/*      */     }
/* 1092 */     ret += pos;
/* 1093 */     if (Trace.isOn) {
/* 1094 */       Trace.data(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "getMqHeadersLength()", "getter", 
/* 1095 */           Integer.valueOf(ret));
/*      */     }
/* 1097 */     return ret;
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
/*      */   public void convertHeaders(int targetEncoding, int targetCCSID) throws JmqiException {
/* 1114 */     if (Trace.isOn)
/* 1115 */       Trace.entry(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "convertHeaders(int,int)", new Object[] {
/* 1116 */             Integer.valueOf(targetEncoding), 
/* 1117 */             Integer.valueOf(targetCCSID)
/*      */           }); 
/* 1119 */     JmqiTls jTls = getTLS();
/*      */     
/* 1121 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*      */     
/* 1123 */     List<byte[]> convertedArrays = (List)new LinkedList<>();
/* 1124 */     boolean conversionPerformed = false;
/* 1125 */     JmqiCodepage targetCP = JmqiCodepage.getJmqiCodepage(this.env, targetCCSID);
/* 1126 */     if (targetCP == null) {
/* 1127 */       JmqiException traceRet1 = new JmqiException(this.env, 6047, new String[] { Integer.toString(targetCCSID), null, null, null, "???" }, 2, 2195, null);
/*      */       
/* 1129 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/* 1133 */     JmqiCodepage cp = JmqiCodepage.getJmqiCodepage(this.env, this.mqmd.getCodedCharSetId());
/*      */     
/* 1135 */     if (cp == null) {
/* 1136 */       JmqiException traceRet1 = new JmqiException(this.env, 6047, new String[] { Integer.toString(this.mqmd
/* 1137 */               .getCodedCharSetId()), null, null, null, "???" }, 2, 2195, null);
/*      */       
/* 1139 */       throw traceRet1;
/*      */     } 
/* 1141 */     int encoding = this.mqmd.getEncoding();
/* 1142 */     String format = this.mqmd.getFormat();
/* 1143 */     int sumLength = 0;
/*      */     
/* 1145 */     for (MQHeaderAccessor mqha : this.headers) {
/* 1146 */       boolean swap = ((encoding & 0xF) == 2);
/*      */ 
/*      */       
/* 1149 */       int nextCCSID = 0;
/* 1150 */       int nextEncoding = 0;
/* 1151 */       byte[] headerCopy = null;
/*      */ 
/*      */       
/* 1154 */       if (mqha.dlqHeader != null) {
/* 1155 */         nextCCSID = mqha.dlqHeader.getCodedCharSetId();
/* 1156 */         nextEncoding = mqha.dlqHeader.getEncoding();
/* 1157 */         headerCopy = new byte[172];
/*      */       } else {
/* 1159 */         nextCCSID = mqha.header.getCodedCharSetId();
/* 1160 */         nextEncoding = mqha.header.getEncoding();
/* 1161 */         headerCopy = new byte[mqha.header.getStrucLength()];
/*      */       } 
/*      */       
/* 1164 */       System.arraycopy(this.messageBuffers[mqha.index].array(), mqha.pos, headerCopy, 0, headerCopy.length);
/*      */       
/* 1166 */       if (targetCCSID != cp.getCCSID()) {
/* 1167 */         conversionPerformed = true;
/*      */         
/* 1169 */         if ("MQHRF2  ".equals(format)) {
/* 1170 */           MQRFH rfh = this.env.newMQRFH(256);
/* 1171 */           rfh.readFromBuffer(headerCopy, 0, 4, swap, cp, jTls);
/* 1172 */           int nameValueDataLength = rfh.getNameValueDataLength();
/* 1173 */           if (nameValueDataLength > 256) {
/* 1174 */             rfh = this.env.newMQRFH(nameValueDataLength);
/* 1175 */             rfh.readFromBuffer(headerCopy, 0, 4, swap, cp, jTls);
/*      */           } 
/*      */           
/* 1178 */           swap = ((targetEncoding & 0xF) == 2);
/* 1179 */           int requiredBufferSize = rfh.getRequiredBufferSize(this.jmqi, 4, targetCP, swap);
/*      */           
/* 1181 */           byte[] headerConverted = new byte[requiredBufferSize];
/*      */           
/* 1183 */           long formatLong = MQHeader.convertFormatToLong(this.env, targetCP, rfh
/* 1184 */               .getFormat());
/* 1185 */           rfh.getMqHeader().setFormatLong(targetCP, formatLong);
/* 1186 */           rfh.setCodedCharSetId(targetCCSID);
/*      */           
/* 1188 */           rfh.writeToBuffer(headerConverted, 0, 4, swap, targetCP, jTls);
/*      */           
/* 1190 */           convertedArrays.add(headerConverted);
/* 1191 */           sumLength += headerConverted.length;
/*      */         }
/* 1193 */         else if ("MQHRF   ".equals(format)) {
/* 1194 */           MQHeader rfh = this.env.newMQHeader();
/* 1195 */           rfh.readFromBuffer(headerCopy, 0, 4, swap, cp, jTls);
/* 1196 */           int nvLength = rfh.getStrucLength() - 32;
/*      */           
/* 1198 */           int requiredBufferSize = rfh.getRequiredBufferSize(4, targetCP) + nvLength;
/*      */ 
/*      */           
/* 1201 */           byte[] headerConverted = new byte[requiredBufferSize];
/*      */           
/* 1203 */           long formatLong = MQHeader.convertFormatToLong(this.env, targetCP, rfh
/* 1204 */               .getFormat());
/* 1205 */           rfh.setFormatLong(targetCP, formatLong);
/* 1206 */           rfh.setCodedCharSetId(targetCCSID);
/*      */           
/* 1208 */           swap = ((targetEncoding & 0xF) == 2);
/* 1209 */           rfh.writeToBuffer(headerConverted, 0, 4, swap, targetCP, jTls);
/*      */           
/* 1211 */           if (nvLength > 0) {
/* 1212 */             byte[] nvBuff = new byte[nvLength];
/* 1213 */             System.arraycopy(headerCopy, 32, nvBuff, 0, nvLength);
/*      */ 
/*      */             
/* 1216 */             CharBuffer targ = CharBuffer.allocate(nvLength);
/* 1217 */             dc.readString(nvBuff, 0, nvLength, targ, cp, jTls);
/* 1218 */             String targStr = targ.toString();
/* 1219 */             dc.writeString(targStr, headerConverted, 32, nvLength, targetCP, jTls);
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/* 1224 */           convertedArrays.add(headerConverted);
/* 1225 */           sumLength += headerConverted.length;
/*      */         }
/*      */       
/*      */       }
/* 1229 */       else if (this.headers.size() > 1) {
/* 1230 */         convertedArrays.add(headerCopy);
/* 1231 */         sumLength += headerCopy.length;
/*      */       } 
/*      */ 
/*      */       
/* 1235 */       cp = JmqiCodepage.getJmqiCodepage(this.env, nextCCSID);
/* 1236 */       if (cp == null) {
/* 1237 */         JmqiException traceRet1 = new JmqiException(this.env, 6047, new String[] { Integer.toString(nextCCSID), null, null, null, "???" }, 2, 2195, null);
/*      */         
/* 1239 */         throw traceRet1;
/*      */       } 
/* 1241 */       encoding = nextEncoding;
/*      */       
/* 1243 */       if (mqha.dlqHeader != null) {
/* 1244 */         format = mqha.dlqHeader.getFormat();
/* 1245 */         mqha.dlqHeader.setCodedCharSetId(targetCCSID);
/* 1246 */         mqha.dlqHeader.setEncoding(targetEncoding); continue;
/*      */       } 
/* 1248 */       format = mqha.header.getFormat();
/* 1249 */       mqha.header.setCodedCharSetId(targetCCSID);
/* 1250 */       mqha.header.setEncoding(targetEncoding);
/*      */     } 
/*      */ 
/*      */     
/* 1254 */     if (conversionPerformed) {
/* 1255 */       this.mqmd.setCodedCharSetId(targetCCSID);
/* 1256 */       this.mqmd.setEncoding(targetEncoding);
/*      */     } 
/* 1258 */     if (conversionPerformed && sumLength > 0) {
/* 1259 */       ByteBuffer newBuffer = ByteBuffer.wrap(new byte[sumLength + this.payloadLength]);
/*      */       
/* 1261 */       int offset = 0;
/* 1262 */       for (byte[] bytes : convertedArrays) {
/* 1263 */         System.arraycopy(bytes, 0, newBuffer.array(), offset, bytes.length);
/*      */         
/* 1265 */         offset += bytes.length;
/*      */       } 
/* 1267 */       byte[] payload = getPayloadOnly();
/* 1268 */       if (payload != null) {
/* 1269 */         System.arraycopy(payload, 0, newBuffer.array(), sumLength, this.payloadLength);
/*      */         
/* 1271 */         this.payloadPos = sumLength;
/*      */       } 
/* 1273 */       this.messageBuffers = new ByteBuffer[] { newBuffer };
/*      */     } 
/* 1275 */     if (Trace.isOn)
/* 1276 */       Trace.exit(this, "com.ibm.mq.ese.intercept.MessageBufferProcessorImpl", "convertHeaders(int,int)"); 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\intercept\MessageBufferProcessorImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */