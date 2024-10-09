/*      */ package com.ibm.mq.headers;
/*      */ 
/*      */ import com.ibm.mq.headers.internal.HeaderMessages;
/*      */ import com.ibm.mq.headers.internal.HexString;
/*      */ import com.ibm.mq.headers.internal.MessageWrapper;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.DataInput;
/*      */ import java.io.DataInputStream;
/*      */ import java.io.DataOutput;
/*      */ import java.io.DataOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.util.AbstractList;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class MQHeaderList
/*      */   extends AbstractList
/*      */ {
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQHeaderList.java";
/*      */   
/*      */   static {
/*   62 */     if (Trace.isOn) {
/*   63 */       Trace.data("com.ibm.mq.headers.MQHeaderList", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQHeaderList.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   71 */   private final List<MQHeader> list = new ArrayList<>();
/*      */   
/*      */   private DataInput source;
/*      */   
/*      */   private Object body;
/*      */   
/*      */   private String format;
/*      */   
/*      */   private String bodyFormat;
/*      */   private int firstInsert;
/*      */   private int originalSize;
/*      */   
/*      */   public MQHeaderList() {
/*   84 */     if (Trace.isOn) {
/*   85 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderList", "<init>()");
/*      */     }
/*      */     
/*   88 */     if (Trace.isOn) {
/*   89 */       Trace.exit(this, "com.ibm.mq.headers.MQHeaderList", "<init>()");
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
/*      */   public MQHeaderList(String format) {
/*  101 */     this();
/*  102 */     if (Trace.isOn) {
/*  103 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderList", "<init>(String)", new Object[] { format });
/*      */     }
/*      */     
/*  106 */     this.format = format;
/*  107 */     if (Trace.isOn) {
/*  108 */       Trace.exit(this, "com.ibm.mq.headers.MQHeaderList", "<init>(String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQHeaderList(MQHeader[] headers) {
/*  119 */     this();
/*  120 */     if (Trace.isOn) {
/*  121 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderList", "<init>(MQHeader [ ])", new Object[] { headers });
/*      */     }
/*      */     
/*  124 */     for (int i = 0; i < headers.length; i++) {
/*  125 */       add(headers[i]);
/*      */     }
/*  127 */     if (Trace.isOn) {
/*  128 */       Trace.exit(this, "com.ibm.mq.headers.MQHeaderList", "<init>(MQHeader [ ])");
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
/*      */   public MQHeaderList(DataInput message) throws MQDataException, IOException {
/*  144 */     this();
/*  145 */     if (Trace.isOn) {
/*  146 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderList", "<init>(DataInput)", new Object[] { message });
/*      */     }
/*      */     
/*  149 */     this.format = MessageWrapper.wrap(message).getFormat();
/*      */     
/*  151 */     read(message);
/*  152 */     if (Trace.isOn) {
/*  153 */       Trace.exit(this, "com.ibm.mq.headers.MQHeaderList", "<init>(DataInput)");
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
/*      */   public MQHeaderList(DataInput message, boolean readBody) throws MQDataException, IOException {
/*  170 */     this();
/*  171 */     if (Trace.isOn) {
/*  172 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderList", "<init>(DataInput,boolean)", new Object[] { message, 
/*  173 */             Boolean.valueOf(readBody) });
/*      */     }
/*  175 */     this.format = MessageWrapper.wrap(message).getFormat();
/*      */     
/*  177 */     read(message, readBody);
/*  178 */     if (Trace.isOn) {
/*  179 */       Trace.exit(this, "com.ibm.mq.headers.MQHeaderList", "<init>(DataInput,boolean)");
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
/*      */   public MQHeaderList(DataInput message, String format, int encoding, int characterSet) throws MQDataException, IOException {
/*  198 */     this();
/*  199 */     if (Trace.isOn) {
/*  200 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderList", "<init>(DataInput,String,int,int)", new Object[] { message, format, 
/*  201 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*      */     }
/*  203 */     this.format = format;
/*      */     
/*  205 */     read(message, encoding, characterSet);
/*  206 */     if (Trace.isOn) {
/*  207 */       Trace.exit(this, "com.ibm.mq.headers.MQHeaderList", "<init>(DataInput,String,int,int)");
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
/*      */   public String updateHeaderChaining() {
/*  225 */     if (Trace.isOn) {
/*  226 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderList", "updateHeaderChaining()");
/*      */     }
/*  228 */     String traceRet1 = updateHeaderChaining(this.bodyFormat);
/*  229 */     if (Trace.isOn) {
/*  230 */       Trace.exit(this, "com.ibm.mq.headers.MQHeaderList", "updateHeaderChaining()", traceRet1);
/*      */     }
/*  232 */     return traceRet1;
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
/*      */   @Deprecated
/*      */   public String updateHeaderChaining(boolean useBodyFormat) {
/*  247 */     if (Trace.isOn)
/*  248 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderList", "updateHeaderChaining(boolean)", new Object[] {
/*  249 */             Boolean.valueOf(useBodyFormat)
/*      */           }); 
/*  251 */     String traceRet1 = updateHeaderChaining(useBodyFormat ? this.bodyFormat : null);
/*  252 */     if (Trace.isOn) {
/*  253 */       Trace.exit(this, "com.ibm.mq.headers.MQHeaderList", "updateHeaderChaining(boolean)", traceRet1);
/*      */     }
/*      */     
/*  256 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String updateHeaderChaining(String bodyFormat) {
/*  267 */     if (Trace.isOn) {
/*  268 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderList", "updateHeaderChaining(String)", new Object[] { bodyFormat });
/*      */     }
/*      */ 
/*      */     
/*  272 */     String format = this.format;
/*  273 */     Iterator<MQHeader> it = this.list.iterator();
/*  274 */     MQChainable current = null;
/*      */     
/*  276 */     while (current == null && it.hasNext()) {
/*  277 */       MQHeader header = it.next();
/*      */       
/*  279 */       if (header != null && header instanceof MQChainable) {
/*  280 */         current = (MQChainable)header;
/*  281 */         format = current.format();
/*      */       } 
/*      */     } 
/*      */     
/*  285 */     while (current != null && it.hasNext()) {
/*  286 */       MQHeader header = it.next();
/*      */       
/*  288 */       if (header != null && header instanceof MQChainable) {
/*  289 */         MQChainable next = (MQChainable)header;
/*      */         
/*  291 */         current.nextFormat(next.format());
/*  292 */         current = next;
/*      */       } 
/*      */     } 
/*      */     
/*  296 */     if (bodyFormat != null && this.list.size() > 0 && this.list.get(this.list.size() - 1) == current) {
/*  297 */       current.nextFormat(bodyFormat);
/*      */     }
/*      */     
/*  300 */     if (bodyFormat != null && this.list.size() == 0) {
/*  301 */       format = bodyFormat;
/*      */     }
/*      */     
/*  304 */     String traceRet1 = this.format = format;
/*  305 */     if (Trace.isOn) {
/*  306 */       Trace.exit(this, "com.ibm.mq.headers.MQHeaderList", "updateHeaderChaining(String)", traceRet1);
/*      */     }
/*      */     
/*  309 */     return traceRet1;
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
/*      */   public int read(DataInput message) throws MQDataException, IOException {
/*  325 */     if (Trace.isOn) {
/*  326 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderList", "read(DataInput)", new Object[] { message });
/*      */     }
/*      */     
/*  329 */     int traceRet1 = read(message, false);
/*  330 */     if (Trace.isOn) {
/*  331 */       Trace.exit(this, "com.ibm.mq.headers.MQHeaderList", "read(DataInput)", 
/*  332 */           Integer.valueOf(traceRet1));
/*      */     }
/*  334 */     return traceRet1;
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
/*      */   public int read(DataInput message, boolean readBody) throws MQDataException, IOException {
/*  349 */     if (Trace.isOn) {
/*  350 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderList", "read(DataInput,boolean)", new Object[] { message, 
/*  351 */             Boolean.valueOf(readBody) });
/*      */     }
/*  353 */     this.source = message;
/*  354 */     this.format = MessageWrapper.wrap(message).getFormat();
/*      */     
/*  356 */     int traceRet1 = read(new MQHeaderIterator(message), readBody);
/*  357 */     if (Trace.isOn) {
/*  358 */       Trace.exit(this, "com.ibm.mq.headers.MQHeaderList", "read(DataInput,boolean)", 
/*  359 */           Integer.valueOf(traceRet1));
/*      */     }
/*  361 */     return traceRet1;
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
/*      */   public int read(DataInput message, int encoding, int characterSet) throws MQDataException, IOException {
/*  379 */     if (Trace.isOn) {
/*  380 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderList", "read(DataInput,int,int)", new Object[] { message, 
/*  381 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*      */     }
/*  383 */     int traceRet1 = read(message, encoding, characterSet, false);
/*  384 */     if (Trace.isOn) {
/*  385 */       Trace.exit(this, "com.ibm.mq.headers.MQHeaderList", "read(DataInput,int,int)", 
/*  386 */           Integer.valueOf(traceRet1));
/*      */     }
/*  388 */     return traceRet1;
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
/*      */   public int read(DataInput message, int encoding, int characterSet, boolean readBody) throws MQDataException, IOException {
/*  405 */     if (Trace.isOn) {
/*  406 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderList", "read(DataInput,int,int,boolean)", new Object[] { message, 
/*  407 */             Integer.valueOf(encoding), Integer.valueOf(characterSet), 
/*  408 */             Boolean.valueOf(readBody) });
/*      */     }
/*  410 */     this.source = message;
/*      */     
/*  412 */     int traceRet1 = read(new MQHeaderIterator(message, this.format, encoding, characterSet), readBody);
/*  413 */     if (Trace.isOn) {
/*  414 */       Trace.exit(this, "com.ibm.mq.headers.MQHeaderList", "read(DataInput,int,int,boolean)", 
/*  415 */           Integer.valueOf(traceRet1));
/*      */     }
/*  417 */     return traceRet1;
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
/*      */   private int read(MQHeaderIterator it, boolean readBody) throws MQDataException, IOException {
/*  430 */     if (Trace.isOn) {
/*  431 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderList", "read(MQHeaderIterator,boolean)", new Object[] { it, 
/*  432 */             Boolean.valueOf(readBody) });
/*      */     }
/*  434 */     this.list.clear();
/*      */     
/*  436 */     String nextFormat = this.format;
/*  437 */     int size = 0;
/*      */     
/*  439 */     while (it.hasNext()) {
/*  440 */       MQHeader header = (MQHeader)it.next();
/*      */       
/*  442 */       this.list.add(header);
/*  443 */       size += header.size();
/*      */       
/*  445 */       if (header instanceof MQChainable) {
/*  446 */         nextFormat = ((MQChainable)header).nextFormat();
/*      */       }
/*      */     } 
/*      */     
/*  450 */     this.body = readBody ? it.getBody() : null;
/*  451 */     this.firstInsert = -1;
/*  452 */     this.originalSize = size;
/*  453 */     this.bodyFormat = nextFormat;
/*      */     
/*  455 */     if (Trace.isOn) {
/*  456 */       Trace.exit(this, "com.ibm.mq.headers.MQHeaderList", "read(MQHeaderIterator,boolean)", 
/*  457 */           Integer.valueOf(size));
/*      */     }
/*  459 */     return size;
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
/*      */   public int write(DataOutput message) throws IOException {
/*  471 */     if (Trace.isOn) {
/*  472 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderList", "write(DataOutput)", new Object[] { message });
/*      */     }
/*      */     
/*  475 */     int traceRet1 = write(message, false);
/*  476 */     if (Trace.isOn) {
/*  477 */       Trace.exit(this, "com.ibm.mq.headers.MQHeaderList", "write(DataOutput)", 
/*  478 */           Integer.valueOf(traceRet1));
/*      */     }
/*  480 */     return traceRet1;
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
/*      */   public int write(DataOutput message, boolean writeBody) throws IOException {
/*  494 */     if (Trace.isOn) {
/*  495 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderList", "write(DataOutput,boolean)", new Object[] { message, 
/*  496 */             Boolean.valueOf(writeBody) });
/*      */     }
/*      */     
/*  499 */     int traceRet1 = write(message, MessageWrapper.wrap(message).getEncoding(), MessageWrapper.wrap(message).getCharacterSet(), writeBody);
/*  500 */     if (Trace.isOn) {
/*  501 */       Trace.exit(this, "com.ibm.mq.headers.MQHeaderList", "write(DataOutput,boolean)", 
/*  502 */           Integer.valueOf(traceRet1));
/*      */     }
/*  504 */     return traceRet1;
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
/*      */   public int write(DataOutput message, int encoding, int characterSet) throws IOException {
/*  520 */     if (Trace.isOn) {
/*  521 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderList", "write(DataOutput,int,int)", new Object[] { message, 
/*  522 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*      */     }
/*  524 */     int traceRet1 = write(message, encoding, characterSet, false);
/*  525 */     if (Trace.isOn) {
/*  526 */       Trace.exit(this, "com.ibm.mq.headers.MQHeaderList", "write(DataOutput,int,int)", 
/*  527 */           Integer.valueOf(traceRet1));
/*      */     }
/*  529 */     return traceRet1;
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
/*      */   public int write(DataOutput message, int encoding, int characterSet, boolean writeBody) throws IOException {
/*  546 */     if (Trace.isOn) {
/*  547 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderList", "write(DataOutput,int,int,boolean)", new Object[] { message, 
/*  548 */             Integer.valueOf(encoding), Integer.valueOf(characterSet), 
/*  549 */             Boolean.valueOf(writeBody) });
/*      */     }
/*  551 */     int traceRet1 = write(message, MessageWrapper.wrap(message).getEncoding(), 
/*  552 */         MessageWrapper.wrap(message).getCharacterSet(), writeBody, false);
/*      */     
/*  554 */     if (Trace.isOn) {
/*  555 */       Trace.exit(this, "com.ibm.mq.headers.MQHeaderList", "write(DataOutput,int,int,boolean)", 
/*  556 */           Integer.valueOf(traceRet1));
/*      */     }
/*  558 */     return traceRet1;
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
/*      */   protected int write(DataOutput message, int encoding, int characterSet, boolean writeBody, boolean writeStringBodyUsingCharacterSetInLastHeader) throws IOException {
/*  605 */     if (Trace.isOn) {
/*  606 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderList", "write(DataOutput,int,int,boolean,boolean)", new Object[] { message, 
/*  607 */             Integer.valueOf(encoding), Integer.valueOf(characterSet), 
/*  608 */             Boolean.valueOf(writeBody), Boolean.valueOf(writeStringBodyUsingCharacterSetInLastHeader) });
/*      */     }
/*  610 */     int count = this.list.size();
/*  611 */     int size = 0;
/*  612 */     int newSize = 0;
/*  613 */     int totalMsgLen = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  620 */     MessageWrapper wrappedMessage = MessageWrapper.wrap(message);
/*      */ 
/*      */ 
/*      */     
/*  624 */     if (wrappedMessage.isMQMessage()) {
/*      */ 
/*      */       
/*  627 */       totalMsgLen = wrappedMessage.getTotalMessageLength();
/*      */       
/*  629 */       if (!writeBody && message == this.source && this.originalSize < (newSize = asMQData().size()) && this.originalSize < totalMsgLen) {
/*  630 */         wrappedMessage.shuffle(this.originalSize, newSize, totalMsgLen - this.originalSize);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  636 */     if (this.firstInsert >= 0 && this.firstInsert < this.list.size() - 1 && wrappedMessage.isMQMessage()) {
/*      */ 
/*      */ 
/*      */       
/*  640 */       ByteArrayOutputStream buffer = new ByteArrayOutputStream();
/*  641 */       DataOutputStream out = new DataOutputStream(buffer);
/*      */       
/*  643 */       for (int j = this.firstInsert + 1; j < count; j++) {
/*  644 */         ((MQHeader)this.list.get(j)).write(out, encoding, 1208);
/*      */       }
/*      */       
/*  647 */       DataInputStream in = new DataInputStream(new ByteArrayInputStream(buffer.toByteArray()));
/*      */       
/*      */       try {
/*  650 */         for (int k = this.firstInsert + 1; k < count; k++) {
/*  651 */           ((MQHeader)this.list.get(k)).read(in, encoding, 1208);
/*      */         
/*      */         }
/*      */       }
/*  655 */       catch (Exception e) {
/*  656 */         if (Trace.isOn) {
/*  657 */           Trace.catchBlock(this, "com.ibm.mq.headers.MQHeaderList", "write(DataOutput,int,int,boolean,boolean)", e);
/*      */         }
/*      */         
/*  660 */         RuntimeException traceRet1 = new RuntimeException(e);
/*  661 */         if (Trace.isOn) {
/*  662 */           Trace.throwing(this, "com.ibm.mq.headers.MQHeaderList", "write(DataOutput,int,int,boolean,boolean)", traceRet1);
/*      */         }
/*      */         
/*  665 */         throw traceRet1;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  673 */     int characterSetToUseWhenEncodingBody = characterSet;
/*      */     
/*  675 */     for (int i = 0; i < count; i++) {
/*  676 */       MQHeader header = this.list.get(i);
/*      */       
/*  678 */       if (header instanceof MQChainable) {
/*  679 */         MQChainable current = (MQChainable)header;
/*      */ 
/*      */         
/*  682 */         String formatFieldInCurrentHeader = current.nextFormat();
/*  683 */         int ccsidFieldInCurrentHeader = current.nextCharacterSet();
/*      */         
/*  685 */         if (writeBody && writeStringBodyUsingCharacterSetInLastHeader && i == count - 1 && "MQSTR   "
/*      */ 
/*      */           
/*  688 */           .equals(formatFieldInCurrentHeader) && ccsidFieldInCurrentHeader > 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  702 */           characterSetToUseWhenEncodingBody = ccsidFieldInCurrentHeader;
/*      */         } else {
/*  704 */           current.nextEncoding(encoding);
/*  705 */           current.nextCharacterSet(characterSet);
/*      */         } 
/*      */         
/*  708 */         if (i < count - 1) {
/*  709 */           MQHeader following = this.list.get(i + 1);
/*      */           
/*  711 */           if (following instanceof MQChainable) {
/*  712 */             current.nextFormat(((MQChainable)following).format());
/*      */           }
/*      */         } 
/*      */       } 
/*      */       
/*  717 */       size += header.write(message, encoding, characterSet);
/*      */     } 
/*      */     
/*  720 */     if (writeBody) {
/*  721 */       if (this.body != null) {
/*      */ 
/*      */         
/*  724 */         byte[] bytes = (this.body instanceof String) ? CCSID.convert((String)this.body, characterSetToUseWhenEncodingBody) : (byte[])this.body;
/*      */         
/*  726 */         message.write(bytes);
/*  727 */         size += bytes.length;
/*      */       } 
/*      */       
/*  730 */       wrappedMessage.resizeBuffer(size);
/*  731 */     } else if (message == this.source && this.originalSize > (newSize = asMQData().size())) {
/*      */ 
/*      */       
/*  734 */       wrappedMessage.shuffle(this.originalSize, newSize, wrappedMessage.getTotalMessageLength() - this.originalSize);
/*  735 */       wrappedMessage.resizeBuffer(wrappedMessage.getTotalMessageLength() - this.originalSize - newSize);
/*      */     } 
/*      */     
/*  738 */     if (Trace.isOn) {
/*  739 */       Trace.exit(this, "com.ibm.mq.headers.MQHeaderList", "write(DataOutput,int,int,boolean,boolean)", 
/*  740 */           Integer.valueOf(size));
/*      */     }
/*  742 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int indexOf(String type) {
/*  753 */     if (Trace.isOn) {
/*  754 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderList", "indexOf(String)", new Object[] { type });
/*      */     }
/*      */     
/*  757 */     Iterator<MQHeader> it = this.list.iterator();
/*  758 */     int index = -1;
/*      */     
/*  760 */     for (int i = 0; index < 0 && it.hasNext(); i++) {
/*  761 */       MQHeader header = it.next();
/*      */       
/*  763 */       if (type != null && type.equals(header.type())) {
/*  764 */         index = i;
/*      */       }
/*      */     } 
/*      */     
/*  768 */     if (Trace.isOn) {
/*  769 */       Trace.exit(this, "com.ibm.mq.headers.MQHeaderList", "indexOf(String)", 
/*  770 */           Integer.valueOf(index));
/*      */     }
/*  772 */     return index;
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
/*      */   public String getFormat() {
/*  785 */     if (Trace.isOn) {
/*  786 */       Trace.data(this, "com.ibm.mq.headers.MQHeaderList", "getFormat()", "getter", this.format);
/*      */     }
/*  788 */     return this.format;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFormat(String format) {
/*  795 */     if (Trace.isOn) {
/*  796 */       Trace.data(this, "com.ibm.mq.headers.MQHeaderList", "setFormat()", "setter", format);
/*      */     }
/*  798 */     this.format = format;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getBody() {
/*  808 */     if (Trace.isOn) {
/*  809 */       Trace.data(this, "com.ibm.mq.headers.MQHeaderList", "getBody()", "getter", this.body);
/*      */     }
/*  811 */     return this.body;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object get(int index) {
/*  822 */     if (Trace.isOn)
/*  823 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderList", "get(int)", new Object[] {
/*  824 */             Integer.valueOf(index)
/*      */           }); 
/*  826 */     Object traceRet1 = this.list.get(index);
/*  827 */     if (Trace.isOn) {
/*  828 */       Trace.exit(this, "com.ibm.mq.headers.MQHeaderList", "get(int)", traceRet1);
/*      */     }
/*  830 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int size() {
/*  840 */     if (Trace.isOn) {
/*  841 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderList", "size()");
/*      */     }
/*  843 */     int traceRet1 = this.list.size();
/*  844 */     if (Trace.isOn) {
/*  845 */       Trace.exit(this, "com.ibm.mq.headers.MQHeaderList", "size()", Integer.valueOf(traceRet1));
/*      */     }
/*  847 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void add(int index, Object header) {
/*  858 */     if (Trace.isOn)
/*  859 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderList", "add(int,Object)", new Object[] {
/*  860 */             Integer.valueOf(index), header
/*      */           }); 
/*  862 */     if (header instanceof MQHeader) {
/*  863 */       if (this.firstInsert < 0 || index < this.firstInsert) {
/*  864 */         this.firstInsert = index;
/*      */       }
/*      */       
/*  867 */       this.list.add(index, (MQHeader)header);
/*      */     } else {
/*      */       
/*  870 */       IllegalArgumentException traceRet1 = new IllegalArgumentException(HeaderMessages.getMessage("MQHDR0023"));
/*  871 */       if (Trace.isOn) {
/*  872 */         Trace.throwing(this, "com.ibm.mq.headers.MQHeaderList", "add(int,Object)", traceRet1);
/*      */       }
/*  874 */       throw traceRet1;
/*      */     } 
/*      */     
/*  877 */     if (Trace.isOn) {
/*  878 */       Trace.exit(this, "com.ibm.mq.headers.MQHeaderList", "add(int,Object)");
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
/*      */   public boolean add(Object header) {
/*  891 */     if (Trace.isOn) {
/*  892 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderList", "add(Object)", new Object[] { header });
/*      */     }
/*  894 */     if (header instanceof MQHeader) {
/*  895 */       if (this.firstInsert < 0) {
/*  896 */         this.firstInsert = this.list.size();
/*      */       }
/*      */       
/*  899 */       boolean traceRet1 = this.list.add((MQHeader)header);
/*  900 */       if (Trace.isOn) {
/*  901 */         Trace.exit(this, "com.ibm.mq.headers.MQHeaderList", "add(Object)", 
/*  902 */             Boolean.valueOf(traceRet1));
/*      */       }
/*  904 */       return traceRet1;
/*      */     } 
/*      */     
/*  907 */     IllegalArgumentException traceRet2 = new IllegalArgumentException(HeaderMessages.getMessage("MQHDR0023"));
/*  908 */     if (Trace.isOn) {
/*  909 */       Trace.throwing(this, "com.ibm.mq.headers.MQHeaderList", "add(Object)", traceRet2);
/*      */     }
/*  911 */     throw traceRet2;
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
/*      */   public Object remove(int index) {
/*  923 */     if (Trace.isOn)
/*  924 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderList", "remove(int)", new Object[] {
/*  925 */             Integer.valueOf(index)
/*      */           }); 
/*  927 */     if (this.firstInsert < 0 || index < this.firstInsert) {
/*  928 */       this.firstInsert = index;
/*      */     }
/*      */     
/*  931 */     Object traceRet1 = this.list.remove(index);
/*  932 */     if (Trace.isOn) {
/*  933 */       Trace.exit(this, "com.ibm.mq.headers.MQHeaderList", "remove(int)", traceRet1);
/*      */     }
/*  935 */     return traceRet1;
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
/*      */   public Object set(int index, Object header) {
/*  947 */     if (Trace.isOn)
/*  948 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderList", "set(int,Object)", new Object[] {
/*  949 */             Integer.valueOf(index), header
/*      */           }); 
/*  951 */     if (header instanceof MQHeader) {
/*  952 */       Object traceRet1 = this.list.set(index, (MQHeader)header);
/*  953 */       if (Trace.isOn) {
/*  954 */         Trace.exit(this, "com.ibm.mq.headers.MQHeaderList", "set(int,Object)", traceRet1);
/*      */       }
/*  956 */       return traceRet1;
/*      */     } 
/*      */     
/*  959 */     IllegalArgumentException traceRet2 = new IllegalArgumentException(HeaderMessages.getMessage("MQHDR0023"));
/*  960 */     if (Trace.isOn) {
/*  961 */       Trace.throwing(this, "com.ibm.mq.headers.MQHeaderList", "set(int,Object)", traceRet2);
/*      */     }
/*  963 */     throw traceRet2;
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
/*      */   public MQData asMQData() {
/*  977 */     if (Trace.isOn) {
/*  978 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderList", "asMQData()");
/*      */     }
/*  980 */     MQData traceRet1 = new MQData()
/*      */       {
/*      */         
/*      */         public int read(DataInput message) throws MQDataException, IOException
/*      */         {
/*  985 */           if (Trace.isOn) {
/*  986 */             Trace.entry(this, "com.ibm.mq.headers.MQHeaderList", "read(DataInput)", new Object[] { message });
/*      */           }
/*      */ 
/*      */           
/*  990 */           int traceRet1 = MQHeaderList.this.read(message);
/*  991 */           if (Trace.isOn) {
/*  992 */             Trace.exit(this, "com.ibm.mq.headers.null", "read(DataInput)", 
/*  993 */                 Integer.valueOf(traceRet1));
/*      */           }
/*  995 */           return traceRet1;
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public int read(DataInput message, int encoding, int characterSet) throws MQDataException, IOException {
/* 1001 */           if (Trace.isOn) {
/* 1002 */             Trace.entry(this, "com.ibm.mq.headers.MQHeaderList", "read(DataInput,int,int)", new Object[] { message, 
/* 1003 */                   Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*      */           }
/*      */           
/* 1006 */           int traceRet1 = MQHeaderList.this.read(message, encoding, characterSet);
/* 1007 */           if (Trace.isOn) {
/* 1008 */             Trace.exit(this, "com.ibm.mq.headers.null", "read(DataInput,int,int)", 
/* 1009 */                 Integer.valueOf(traceRet1));
/*      */           }
/* 1011 */           return traceRet1;
/*      */         }
/*      */ 
/*      */         
/*      */         public int write(DataOutput message) throws IOException {
/* 1016 */           if (Trace.isOn) {
/* 1017 */             Trace.entry(this, "com.ibm.mq.headers.MQHeaderList", "write(DataOutput)", new Object[] { message });
/*      */           }
/*      */ 
/*      */           
/* 1021 */           int traceRet1 = MQHeaderList.this.write(message);
/* 1022 */           if (Trace.isOn) {
/* 1023 */             Trace.exit(this, "com.ibm.mq.headers.null", "write(DataOutput)", 
/* 1024 */                 Integer.valueOf(traceRet1));
/*      */           }
/* 1026 */           return traceRet1;
/*      */         }
/*      */ 
/*      */         
/*      */         public int write(DataOutput message, int encoding, int characterSet) throws IOException {
/* 1031 */           if (Trace.isOn) {
/* 1032 */             Trace.entry(this, "com.ibm.mq.headers.MQHeaderList", "write(DataOutput,int,int)", new Object[] { message, 
/* 1033 */                   Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*      */           }
/*      */ 
/*      */           
/* 1037 */           int traceRet1 = MQHeaderList.this.write(message, encoding, characterSet);
/* 1038 */           if (Trace.isOn) {
/* 1039 */             Trace.exit(this, "com.ibm.mq.headers.null", "write(DataOutput,int,int)", 
/* 1040 */                 Integer.valueOf(traceRet1));
/*      */           }
/* 1042 */           return traceRet1;
/*      */         }
/*      */ 
/*      */         
/*      */         public int size() {
/* 1047 */           if (Trace.isOn) {
/* 1048 */             Trace.entry(this, "com.ibm.mq.headers.MQHeaderList", "size()");
/*      */           }
/* 1050 */           Iterator<E> it = MQHeaderList.this.iterator();
/* 1051 */           int size = 0;
/*      */           
/* 1053 */           while (it.hasNext()) {
/* 1054 */             size += ((MQHeader)it.next()).size();
/*      */           }
/*      */           
/* 1057 */           if (MQHeaderList.this.body != null) {
/* 1058 */             if (MQHeaderList.this.body instanceof byte[]) {
/* 1059 */               size += ((byte[])MQHeaderList.this.body).length;
/*      */             } else {
/* 1061 */               size += MQHeaderList.this.body.toString().length();
/*      */             } 
/*      */           }
/*      */           
/* 1065 */           if (Trace.isOn) {
/* 1066 */             Trace.exit(this, "com.ibm.mq.headers.null", "size()", Integer.valueOf(size));
/*      */           }
/* 1068 */           return size;
/*      */         }
/*      */       };
/* 1071 */     if (Trace.isOn) {
/* 1072 */       Trace.exit(this, "com.ibm.mq.headers.MQHeaderList", "asMQData()", traceRet1);
/*      */     }
/* 1074 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/* 1085 */     if (Trace.isOn) {
/* 1086 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderList", "toString()");
/*      */     }
/* 1088 */     StringBuffer sb = new StringBuffer(super.toString());
/*      */     
/* 1090 */     sb.setLength(sb.length() - 1);
/*      */     
/* 1092 */     if (sb.length() > 1) {
/* 1093 */       sb.append(", ");
/*      */     }
/*      */     
/* 1096 */     sb.append("body: ");
/*      */     
/* 1098 */     if (this.body == null) {
/* 1099 */       sb.append("null");
/*      */     }
/* 1101 */     else if (this.body instanceof byte[]) {
/* 1102 */       byte[] bytes = (byte[])this.body;
/*      */       
/* 1104 */       sb.append("0x");
/* 1105 */       sb.append(HexString.hexString(bytes));
/* 1106 */       sb.append(" (" + bytes.length + ((bytes.length == 1) ? " byte)" : " bytes)"));
/*      */     } else {
/* 1108 */       String string = this.body.toString();
/*      */       
/* 1110 */       sb.append("\"");
/* 1111 */       sb.append(string);
/* 1112 */       sb.append("\" (" + string.length() + ((string.length() == 1) ? " char)" : " chars)"));
/*      */     } 
/*      */ 
/*      */     
/* 1116 */     sb.append(']');
/*      */     
/* 1118 */     String traceRet1 = new String(sb);
/* 1119 */     if (Trace.isOn) {
/* 1120 */       Trace.exit(this, "com.ibm.mq.headers.MQHeaderList", "toString()", traceRet1);
/*      */     }
/* 1122 */     return traceRet1;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\MQHeaderList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */