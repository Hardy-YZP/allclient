/*      */ package com.ibm.mq.headers;
/*      */ 
/*      */ import com.ibm.mq.headers.internal.Header;
/*      */ import com.ibm.mq.headers.internal.HeaderField;
/*      */ import com.ibm.mq.headers.internal.HeaderMessages;
/*      */ import com.ibm.mq.headers.internal.HeaderType;
/*      */ import com.ibm.mq.headers.internal.HexString;
/*      */ import com.ibm.mq.headers.internal.MessageWrapper;
/*      */ import com.ibm.mq.headers.internal.store.Store;
/*      */ import com.ibm.mq.headers.internal.validator.MQHeaderValidationException;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.io.DataInput;
/*      */ import java.io.DataOutput;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.Reader;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.nio.charset.CharacterCodingException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.NoSuchElementException;
/*      */ import javax.xml.parsers.ParserConfigurationException;
/*      */ import org.xml.sax.Attributes;
/*      */ import org.xml.sax.SAXException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class MQRFH2
/*      */   extends Header
/*      */   implements MQChainable
/*      */ {
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQRFH2.java";
/*      */   
/*      */   static {
/*   77 */     if (Trace.isOn) {
/*   78 */       Trace.data("com.ibm.mq.headers.MQRFH2", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQRFH2.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   86 */   static final HeaderType TYPE = new HeaderType("MQRFH2");
/*      */   
/*   88 */   static final HeaderField StrucId = TYPE.addMQChar("StrucId", "RFH ", true);
/*   89 */   static final HeaderField Version = TYPE.addMQLong("Version", 2, true);
/*   90 */   static final HeaderField StrucLength = TYPE.addMQLong("StrucLength", 36);
/*      */   
/*   92 */   static final HeaderField Encoding = TYPE.addMQLong("Encoding");
/*   93 */   static final HeaderField CodedCharSetId = TYPE.addMQLong("CodedCharSetId");
/*   94 */   static final HeaderField Format = TYPE.addMQChar("Format", 8);
/*   95 */   static final HeaderField Flags = TYPE.addMQLong("Flags");
/*   96 */   static final HeaderField NameValueCCSID = TYPE.addMQLong("NameValueCCSID", 1208);
/*   97 */   static final HeaderField NameValueData = TYPE.addMQByte("NameValueData", null, StrucLength);
/*      */   
/*      */   private List<MQRFH2Element> folders;
/*      */   
/*      */   private MQRFH2FolderParser parser;
/*      */   
/*      */   private boolean changed;
/*      */ 
/*      */   
/*      */   public MQRFH2() {
/*  107 */     super(TYPE);
/*  108 */     if (Trace.isOn) {
/*  109 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "<init>()");
/*      */     }
/*  111 */     if (Trace.isOn) {
/*  112 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "<init>()");
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
/*      */   public MQRFH2(DataInput message) throws MQDataException, IOException {
/*  125 */     this();
/*  126 */     if (Trace.isOn) {
/*  127 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "<init>(DataInput)", new Object[] { message });
/*      */     }
/*      */     
/*  130 */     read((DataInput)MessageWrapper.wrap(message));
/*  131 */     if (Trace.isOn) {
/*  132 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "<init>(DataInput)");
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
/*      */   public MQRFH2(DataInput message, int encoding, int characterSet) throws MQDataException, IOException {
/*  147 */     this();
/*  148 */     if (Trace.isOn) {
/*  149 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "<init>(DataInput,int,int)", new Object[] { message, 
/*  150 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*      */     }
/*      */     
/*  153 */     read((DataInput)MessageWrapper.wrap(message), encoding, characterSet);
/*  154 */     if (Trace.isOn) {
/*  155 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "<init>(DataInput,int,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQRFH2(String[] folderStrings) throws IOException {
/*  166 */     this();
/*  167 */     if (Trace.isOn) {
/*  168 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "<init>(String [ ])", new Object[] { folderStrings });
/*      */     }
/*      */ 
/*      */     
/*  172 */     setFolderStrings(folderStrings);
/*  173 */     if (Trace.isOn) {
/*  174 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "<init>(String [ ])");
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
/*      */   public MQRFH2(String folderName, String folderContent) throws IOException {
/*  186 */     this();
/*  187 */     if (Trace.isOn) {
/*  188 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "<init>(String,String)", new Object[] { folderName, folderContent });
/*      */     }
/*      */ 
/*      */     
/*  192 */     setFolderContent(folderName, folderContent);
/*  193 */     if (Trace.isOn) {
/*  194 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "<init>(String,String)");
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
/*      */   public int read(DataInput input, int encoding, int characterSet) throws IOException, MQDataException {
/*  225 */     if (Trace.isOn) {
/*  226 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "read(DataInput,int,int)", new Object[] { input, 
/*  227 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*      */     }
/*  229 */     clearFolders();
/*      */     try {
/*  231 */       int traceRet1 = super.read(input, encoding, characterSet);
/*  232 */       if (Trace.isOn) {
/*  233 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "read(DataInput,int,int)", 
/*  234 */             Integer.valueOf(traceRet1));
/*      */       }
/*  236 */       return traceRet1;
/*      */     }
/*  238 */     catch (Exception e) {
/*  239 */       if (Trace.isOn) {
/*  240 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQRFH2", "read(DataInput,int,int)", e);
/*      */       }
/*  242 */       MQDataException traceRet2 = MQDataException.getMQDataException(e);
/*  243 */       if (Trace.isOn) {
/*  244 */         Trace.throwing(this, "com.ibm.mq.headers.MQRFH2", "read(DataInput,int,int)", traceRet2);
/*      */       }
/*  246 */       throw traceRet2;
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
/*      */   public int write(DataOutput outputP, int encoding, int characterSet) throws IOException {
/*  261 */     if (Trace.isOn) {
/*  262 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "write(DataOutput,int,int)", new Object[] { outputP, 
/*  263 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*      */     }
/*  265 */     DataOutput output = outputP;
/*  266 */     int size = size();
/*      */     
/*  268 */     if (store().matchesEncoding(encoding) || size == 36) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  273 */       super.write(output, encoding, characterSet);
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  278 */       if (!store().matchesEncoding(encoding)) {
/*  279 */         MessageWrapper wrappedMessage = MessageWrapper.wrap(output);
/*  280 */         output = wrappedMessage.getReversed();
/*      */       } 
/*      */       
/*  283 */       HeaderType headerType = headerType();
/*  284 */       Iterator<?> it = headerType.getFields().iterator();
/*  285 */       int fieldCount = headerType.getFieldCount() - 1;
/*      */       
/*  287 */       while (fieldCount-- > 0) {
/*  288 */         ((HeaderField)it.next()).write(this, output, encoding, characterSet);
/*      */       }
/*      */       
/*  291 */       Store store = store();
/*  292 */       int ccsid = getNameValueCCSID();
/*      */       
/*  294 */       if (store.hasData()) {
/*  295 */         int offset = 36;
/*      */         
/*  297 */         while (offset < size) {
/*  298 */           int stringLength = store.getInt(null, offset);
/*  299 */           String string = store.getString(null, offset + 4, stringLength, ccsid);
/*      */           
/*  301 */           output.writeInt(Store.isReversed(encoding) ? Store.reverse(stringLength) : stringLength);
/*  302 */           byte[] stringBytes = null;
/*      */           try {
/*  304 */             stringBytes = CCSID.getJmqiCodepage(ccsid).stringToBytes(string);
/*      */           }
/*  306 */           catch (CharacterCodingException e) {
/*  307 */             if (Trace.isOn) {
/*  308 */               Trace.catchBlock(this, "com.ibm.mq.headers.MQRFH2", "write(DataOutput,int,int)", e);
/*      */             }
/*      */             
/*  311 */             UnsupportedEncodingException traceRet1 = new UnsupportedEncodingException(e.toString());
/*  312 */             if (Trace.isOn) {
/*  313 */               Trace.throwing(this, "com.ibm.mq.headers.MQRFH2", "write(DataOutput,int,int)", traceRet1);
/*      */             }
/*      */             
/*  316 */             throw traceRet1;
/*      */           } 
/*  318 */           output.write(stringBytes);
/*      */           
/*  320 */           offset = offset + stringLength + 4;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  325 */     if (Trace.isOn) {
/*  326 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "write(DataOutput,int,int)", 
/*  327 */           Integer.valueOf(size));
/*      */     }
/*  329 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int size() {
/*  337 */     if (Trace.isOn) {
/*  338 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "size()");
/*      */     }
/*      */     try {
/*  341 */       writeCachedContent();
/*      */       
/*  343 */       int traceRet1 = super.size();
/*  344 */       if (Trace.isOn) {
/*  345 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "size()", Integer.valueOf(traceRet1));
/*      */       }
/*  347 */       return traceRet1;
/*      */     
/*      */     }
/*  350 */     catch (IOException e) {
/*  351 */       if (Trace.isOn) {
/*  352 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQRFH2", "size()", e);
/*      */       }
/*  354 */       RuntimeException traceRet2 = new RuntimeException(e);
/*  355 */       if (Trace.isOn) {
/*  356 */         Trace.throwing(this, "com.ibm.mq.headers.MQRFH2", "size()", traceRet2);
/*      */       }
/*  358 */       throw traceRet2;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   MQRFH2FolderParser getFolderParser() throws ParserConfigurationException, SAXException {
/*  366 */     if (this.parser == null) {
/*  367 */       this.parser = new MQRFH2FolderParser();
/*      */     }
/*      */     
/*  370 */     if (Trace.isOn) {
/*  371 */       Trace.data(this, "com.ibm.mq.headers.MQRFH2", "getFolderParser()", "getter", this.parser);
/*      */     }
/*  373 */     return this.parser;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private synchronized void clearFolders() {
/*  380 */     if (Trace.isOn) {
/*  381 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "clearFolders()");
/*      */     }
/*  383 */     this.folders = null;
/*  384 */     this.changed = false;
/*  385 */     if (Trace.isOn) {
/*  386 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "clearFolders()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized void writeCachedContent() throws IOException {
/*  396 */     if (Trace.isOn) {
/*  397 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "writeCachedContent()");
/*      */     }
/*  399 */     if (this.changed && this.folders != null) {
/*  400 */       String[] strings = new String[this.folders.size()];
/*      */       
/*  402 */       for (int i = 0; i < strings.length; i++) {
/*  403 */         strings[i] = ((Element)this.folders.get(i)).toXML();
/*      */       }
/*      */       
/*  406 */       this.folders = null;
/*  407 */       this.changed = false;
/*      */       
/*  409 */       setFolderStrings(strings);
/*      */     } 
/*  411 */     if (Trace.isOn) {
/*  412 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "writeCachedContent()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void readCachedContent() throws MQDataException, IOException {
/*  423 */     if (Trace.isOn) {
/*  424 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "readCachedContent()");
/*      */     }
/*  426 */     getExpandFolders();
/*  427 */     if (Trace.isOn) {
/*  428 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "readCachedContent()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void discardCachedContent() {
/*  437 */     if (Trace.isOn) {
/*  438 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "discardCachedContent()");
/*      */     }
/*  440 */     this.folders = null;
/*  441 */     this.changed = false;
/*  442 */     if (Trace.isOn) {
/*  443 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "discardCachedContent()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setChanged() {
/*  453 */     if (Trace.isOn) {
/*  454 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "setChanged()");
/*      */     }
/*  456 */     this.changed = true;
/*  457 */     if (Trace.isOn) {
/*  458 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "setChanged()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int nextEncoding() {
/*  468 */     if (Trace.isOn) {
/*  469 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "nextEncoding()");
/*      */     }
/*  471 */     int traceRet1 = getEncoding();
/*  472 */     if (Trace.isOn) {
/*  473 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "nextEncoding()", Integer.valueOf(traceRet1));
/*      */     }
/*  475 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void nextEncoding(int value) {
/*  483 */     if (Trace.isOn)
/*  484 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "nextEncoding(int)", new Object[] {
/*  485 */             Integer.valueOf(value)
/*      */           }); 
/*  487 */     setEncoding(value);
/*  488 */     if (Trace.isOn) {
/*  489 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "nextEncoding(int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int nextCharacterSet() {
/*  499 */     if (Trace.isOn) {
/*  500 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "nextCharacterSet()");
/*      */     }
/*  502 */     int traceRet1 = getCodedCharSetId();
/*  503 */     if (Trace.isOn) {
/*  504 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "nextCharacterSet()", 
/*  505 */           Integer.valueOf(traceRet1));
/*      */     }
/*  507 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void nextCharacterSet(int value) {
/*  515 */     if (Trace.isOn)
/*  516 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "nextCharacterSet(int)", new Object[] {
/*  517 */             Integer.valueOf(value)
/*      */           }); 
/*  519 */     setCodedCharSetId(value);
/*  520 */     if (Trace.isOn) {
/*  521 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "nextCharacterSet(int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String nextFormat() {
/*  531 */     if (Trace.isOn) {
/*  532 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "nextFormat()");
/*      */     }
/*  534 */     String traceRet1 = getFormat();
/*  535 */     if (Trace.isOn) {
/*  536 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "nextFormat()", traceRet1);
/*      */     }
/*  538 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void nextFormat(String value) {
/*  546 */     if (Trace.isOn) {
/*  547 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "nextFormat(String)", new Object[] { value });
/*      */     }
/*  549 */     setFormat(value);
/*  550 */     if (Trace.isOn) {
/*  551 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "nextFormat(String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String format() {
/*  561 */     if (Trace.isOn) {
/*  562 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "format()");
/*      */     }
/*  564 */     if (Trace.isOn) {
/*  565 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "format()", "MQHRF2  ");
/*      */     }
/*  567 */     return "MQHRF2  ";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getStrucId() {
/*  578 */     String traceRet1 = getStringValue(StrucId);
/*  579 */     if (Trace.isOn) {
/*  580 */       Trace.data(this, "com.ibm.mq.headers.MQRFH2", "getStrucId()", "getter", traceRet1);
/*      */     }
/*  582 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getVersion() {
/*  591 */     int traceRet1 = getIntValue(Version);
/*  592 */     if (Trace.isOn) {
/*  593 */       Trace.data(this, "com.ibm.mq.headers.MQRFH2", "getVersion()", "getter", 
/*  594 */           Integer.valueOf(traceRet1));
/*      */     }
/*  596 */     return traceRet1;
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
/*      */   public int getStrucLength() {
/*  612 */     if (Trace.isOn) {
/*  613 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "getStrucLength()");
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  618 */       writeCachedContent();
/*      */       
/*  620 */       int traceRet1 = getIntValue(StrucLength);
/*  621 */       if (Trace.isOn) {
/*  622 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "getStrucLength()", 
/*  623 */             Integer.valueOf(traceRet1));
/*      */       }
/*  625 */       return traceRet1;
/*      */     
/*      */     }
/*  628 */     catch (IOException e) {
/*  629 */       if (Trace.isOn) {
/*  630 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQRFH2", "getStrucLength()", e);
/*      */       }
/*  632 */       RuntimeException traceRet2 = new RuntimeException(e);
/*  633 */       if (Trace.isOn) {
/*  634 */         Trace.throwing(this, "com.ibm.mq.headers.MQRFH2", "getStrucLength()", traceRet2);
/*      */       }
/*  636 */       throw traceRet2;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getEncoding() {
/*  646 */     int traceRet1 = getIntValue(Encoding);
/*  647 */     if (Trace.isOn) {
/*  648 */       Trace.data(this, "com.ibm.mq.headers.MQRFH2", "getEncoding()", "getter", 
/*  649 */           Integer.valueOf(traceRet1));
/*      */     }
/*  651 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEncoding(int value) {
/*  660 */     if (Trace.isOn) {
/*  661 */       Trace.data(this, "com.ibm.mq.headers.MQRFH2", "setEncoding(int)", "setter", 
/*  662 */           Integer.valueOf(value));
/*      */     }
/*  664 */     setIntValue(Encoding, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCodedCharSetId() {
/*  673 */     int traceRet1 = getIntValue(CodedCharSetId);
/*  674 */     if (Trace.isOn) {
/*  675 */       Trace.data(this, "com.ibm.mq.headers.MQRFH2", "getCodedCharSetId()", "getter", 
/*  676 */           Integer.valueOf(traceRet1));
/*      */     }
/*  678 */     return traceRet1;
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
/*      */   public void setCodedCharSetId(int value) {
/*  690 */     if (Trace.isOn) {
/*  691 */       Trace.data(this, "com.ibm.mq.headers.MQRFH2", "setCodedCharSetId(int)", "setter", 
/*  692 */           Integer.valueOf(value));
/*      */     }
/*  694 */     setIntValue(CodedCharSetId, (value == 0) ? -2 : value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getFormat() {
/*  703 */     String traceRet1 = getStringValue(Format);
/*  704 */     if (Trace.isOn) {
/*  705 */       Trace.data(this, "com.ibm.mq.headers.MQRFH2", "getFormat()", "getter", traceRet1);
/*      */     }
/*  707 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFormat(String value) {
/*  716 */     if (Trace.isOn) {
/*  717 */       Trace.data(this, "com.ibm.mq.headers.MQRFH2", "setFormat(String)", "setter", value);
/*      */     }
/*  719 */     setStringValue(Format, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getFlags() {
/*  728 */     int traceRet1 = getIntValue(Flags);
/*  729 */     if (Trace.isOn) {
/*  730 */       Trace.data(this, "com.ibm.mq.headers.MQRFH2", "getFlags()", "getter", 
/*  731 */           Integer.valueOf(traceRet1));
/*      */     }
/*  733 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFlags(int value) {
/*  742 */     if (Trace.isOn) {
/*  743 */       Trace.data(this, "com.ibm.mq.headers.MQRFH2", "setFlags(int)", "setter", 
/*  744 */           Integer.valueOf(value));
/*      */     }
/*  746 */     setIntValue(Flags, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNameValueCCSID() {
/*  755 */     int traceRet1 = getIntValue(NameValueCCSID);
/*  756 */     if (Trace.isOn) {
/*  757 */       Trace.data(this, "com.ibm.mq.headers.MQRFH2", "getNameValueCCSID()", "getter", 
/*  758 */           Integer.valueOf(traceRet1));
/*      */     }
/*  760 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setNameValueCCSID(int value) {
/*  769 */     if (Trace.isOn) {
/*  770 */       Trace.data(this, "com.ibm.mq.headers.MQRFH2", "setNameValueCCSID(int)", "setter", 
/*  771 */           Integer.valueOf(value));
/*      */     }
/*  773 */     setIntValue(NameValueCCSID, value);
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
/*      */   public int getNameValueLength() {
/*      */     try {
/*  787 */       writeCachedContent();
/*      */     
/*      */     }
/*  790 */     catch (IOException e) {
/*  791 */       if (Trace.isOn) {
/*  792 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQRFH2", "getNameValueLength()", e);
/*      */       }
/*  794 */       RuntimeException traceRet1 = new RuntimeException(e);
/*  795 */       if (Trace.isOn) {
/*  796 */         Trace.throwing(this, "com.ibm.mq.headers.MQRFH2", "getNameValueLength()", traceRet1);
/*      */       }
/*  798 */       throw traceRet1;
/*      */     } 
/*      */     
/*  801 */     int traceRet2 = getIntValue(StrucLength) - 36;
/*  802 */     if (Trace.isOn) {
/*  803 */       Trace.data(this, "com.ibm.mq.headers.MQRFH2", "getNameValueLength()", "getter", 
/*  804 */           Integer.valueOf(traceRet2));
/*      */     }
/*  806 */     return traceRet2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getNameValueData() {
/*  815 */     if (Trace.isOn) {
/*  816 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "getNameValueData()");
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  821 */       writeCachedContent();
/*      */       
/*  823 */       byte[] traceRet1 = getBytesValue(NameValueData);
/*  824 */       if (Trace.isOn) {
/*  825 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "getNameValueData()", traceRet1);
/*      */       }
/*  827 */       return traceRet1;
/*      */     
/*      */     }
/*  830 */     catch (IOException e) {
/*  831 */       if (Trace.isOn) {
/*  832 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQRFH2", "getNameValueData()", e);
/*      */       }
/*  834 */       RuntimeException traceRet2 = new RuntimeException(e);
/*  835 */       if (Trace.isOn) {
/*  836 */         Trace.throwing(this, "com.ibm.mq.headers.MQRFH2", "getNameValueData()", traceRet2);
/*      */       }
/*  838 */       throw traceRet2;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setNameValueData(byte[] value) {
/*  849 */     if (Trace.isOn) {
/*  850 */       Trace.data(this, "com.ibm.mq.headers.MQRFH2", "setNameValueData(byte [ ])", "setter", value);
/*      */     }
/*      */     
/*  853 */     clearFolders();
/*  854 */     setBytesValue(NameValueData, value);
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
/*      */   public void setNameValueData(String value) throws IOException {
/*  867 */     if (Trace.isOn) {
/*  868 */       Trace.data(this, "com.ibm.mq.headers.MQRFH2", "setNameValueData(String)", "setter", value);
/*      */     }
/*  870 */     clearFolders();
/*  871 */     setFolderStrings(new String[] { (value == null) ? "" : value });
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
/*      */   public String[] getFolderStrings() throws IOException {
/*      */     String[] folders;
/*  887 */     if (this.folders == null) {
/*  888 */       Store store = store();
/*  889 */       int ccsid = getNameValueCCSID();
/*      */       
/*  891 */       if (store.hasData()) {
/*  892 */         List<String> list = new ArrayList<>();
/*  893 */         int offset = 36;
/*  894 */         int totalHeaderSize = size();
/*      */         
/*  896 */         while (offset < totalHeaderSize) {
/*  897 */           int nameValueDataLength = store.getInt(null, offset);
/*  898 */           int sizeOfHeaderRemaining = totalHeaderSize - offset - 4;
/*      */ 
/*      */ 
/*      */           
/*  902 */           if (nameValueDataLength > sizeOfHeaderRemaining) {
/*  903 */             MQHeaderValidationException mqHeaderValExc = new MQHeaderValidationException(HeaderMessages.getMessage("MQHDR0016", new Object[] { "MQRFH2", 
/*  904 */                     Integer.valueOf(nameValueDataLength), "NameValueLength" }));
/*      */             
/*  906 */             IOException ioExcepion = new IOException((Throwable)mqHeaderValExc);
/*      */             
/*  908 */             if (Trace.isOn) {
/*  909 */               Trace.throwing(this, "com.ibm.mq.headers.MQRFH2", "getFolderStrings()", ioExcepion);
/*      */             }
/*  911 */             throw ioExcepion;
/*      */           } 
/*      */ 
/*      */           
/*  915 */           list.add(store.getString(null, offset + 4, nameValueDataLength, ccsid).trim());
/*  916 */           offset = offset + nameValueDataLength + 4;
/*      */         } 
/*      */         
/*  919 */         list.toArray(folders = new String[list.size()]);
/*      */       } else {
/*  921 */         folders = new String[0];
/*      */       } 
/*      */     } else {
/*  924 */       folders = new String[this.folders.size()];
/*      */       
/*  926 */       for (int i = 0; i < folders.length; i++) {
/*  927 */         folders[i] = ((MQRFH2Element)this.folders.get(i)).toXML();
/*      */       }
/*      */     } 
/*      */     
/*  931 */     if (Trace.isOn) {
/*  932 */       Trace.data(this, "com.ibm.mq.headers.MQRFH2", "getFolderStrings()", "getter", folders);
/*      */     }
/*  934 */     return folders;
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
/*      */   public void setFolderStrings(String[] folders) throws IOException {
/*  946 */     if (Trace.isOn) {
/*  947 */       Trace.data(this, "com.ibm.mq.headers.MQRFH2", "setFolderStrings(String [ ])", "setter", folders);
/*      */     }
/*      */ 
/*      */     
/*  951 */     int ccsid = getNameValueCCSID();
/*  952 */     byte[][] foldersAsBytes = new byte[folders.length][];
/*      */     
/*  954 */     int nameValueDataLength = 0;
/*      */     
/*  956 */     for (int i = 0; i < folders.length; i++) {
/*  957 */       String folder = folders[i];
/*      */       
/*  959 */       if (folder != null) {
/*  960 */         byte[] folderAsBytes = Charsets.convert(folder, ccsid);
/*  961 */         foldersAsBytes[i] = folderAsBytes;
/*  962 */         nameValueDataLength = nameValueDataLength + 4 + folderAsBytes.length + getPadLength(folderAsBytes.length);
/*      */       } 
/*      */     } 
/*      */     
/*  966 */     int offset = 36;
/*      */     
/*  968 */     Store store = store(offset + nameValueDataLength);
/*      */     
/*  970 */     for (int j = 0; j < folders.length; j++) {
/*  971 */       byte[] folderAsBytes = foldersAsBytes[j];
/*      */       
/*  973 */       if (folderAsBytes != null) {
/*      */ 
/*      */         
/*  976 */         int padLength = getPadLength(folderAsBytes.length);
/*      */ 
/*      */ 
/*      */         
/*  980 */         int stringLength = folderAsBytes.length + padLength;
/*      */         
/*  982 */         store.setInt(offset, stringLength);
/*  983 */         offset += 4;
/*      */ 
/*      */         
/*  986 */         store.setBytes(offset, folderAsBytes, folderAsBytes.length);
/*  987 */         offset += folderAsBytes.length;
/*      */         
/*  989 */         if (padLength != 0) {
/*      */ 
/*      */ 
/*      */           
/*  993 */           char[] paddingChars = new char[padLength];
/*  994 */           Arrays.fill(paddingChars, ' ');
/*  995 */           byte[] paddingCharactersAsBytes = Charsets.convert(new String(paddingChars), ccsid);
/*      */           
/*  997 */           store.setBytes(offset, paddingCharactersAsBytes, padLength);
/*  998 */           offset += padLength;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1003 */     setIntValue(StrucLength, 36 + nameValueDataLength);
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
/*      */   public synchronized String getFolderContent(String name) throws IOException {
/* 1018 */     if (Trace.isOn) {
/* 1019 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "getFolderContent(String)", new Object[] { name });
/*      */     }
/*      */     
/* 1022 */     Element folder = getFolder(name, false);
/*      */     
/* 1024 */     if (folder == null) {
/* 1025 */       if (Trace.isOn) {
/* 1026 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "getFolderContent(String)", null, 1);
/*      */       }
/* 1028 */       return null;
/*      */     } 
/* 1030 */     String traceRet1 = folder.getContent();
/* 1031 */     if (Trace.isOn) {
/* 1032 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "getFolderContent(String)", traceRet1, 2);
/*      */     }
/* 1034 */     return traceRet1;
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
/*      */   @Deprecated
/*      */   public synchronized void setFolderContent(String name, String content) throws IOException {
/* 1050 */     if (Trace.isOn) {
/* 1051 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "setFolderContent(String,String)", new Object[] { name, content });
/*      */     }
/*      */     
/* 1054 */     Element folder = getFolder(name, false);
/*      */     
/* 1056 */     if (folder == null) {
/* 1057 */       if (content != null) {
/*      */ 
/*      */         
/* 1060 */         this.folders.add(new MQRFH2Element('<' + name + '>' + content + "</" + name + '>'));
/* 1061 */         this.changed = true;
/*      */       } 
/* 1063 */     } else if (content == null) {
/*      */ 
/*      */       
/* 1066 */       this.folders.remove(folder);
/* 1067 */       this.changed = true;
/*      */     }
/*      */     else {
/*      */       
/* 1071 */       folder.setStringValue(content, false);
/* 1072 */       this.changed = true;
/*      */     } 
/* 1074 */     if (Trace.isOn) {
/* 1075 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "setFolderContent(String,String)");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private List<MQRFH2Element> getExpandFolders() throws IOException {
/* 1081 */     if (this.folders == null) {
/* 1082 */       String[] strings = getFolderStrings();
/* 1083 */       this.folders = new ArrayList<>(strings.length);
/*      */       
/* 1085 */       for (int i = 0; i < strings.length; i++) {
/* 1086 */         this.folders.add(new MQRFH2Element(strings[i]));
/*      */       }
/*      */     } 
/*      */     
/* 1090 */     if (Trace.isOn) {
/* 1091 */       Trace.data(this, "com.ibm.mq.headers.MQRFH2", "getExpandFolders()", "getter", this.folders);
/*      */     }
/* 1093 */     return this.folders;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Element[] getFolders() throws IOException {
/* 1103 */     List<MQRFH2Element> folders = getExpandFolders();
/*      */     
/* 1105 */     Element[] traceRet1 = folders.<Element>toArray(new Element[folders.size()]);
/* 1106 */     if (Trace.isOn) {
/* 1107 */       Trace.data(this, "com.ibm.mq.headers.MQRFH2", "getFolders()", "getter", traceRet1);
/*      */     }
/* 1109 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Element getFolder(String name, boolean create) throws IOException {
/* 1120 */     if (Trace.isOn) {
/* 1121 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "getFolder(String,boolean)", new Object[] { name, 
/* 1122 */             Boolean.valueOf(create) });
/*      */     }
/* 1124 */     Element traceRet1 = getFolder(name, create, false);
/* 1125 */     if (Trace.isOn) {
/* 1126 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "getFolder(String,boolean)", traceRet1);
/*      */     }
/* 1128 */     return traceRet1;
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
/*      */   public Element getFolder(String name, boolean create, boolean setDt) throws IOException {
/* 1140 */     if (Trace.isOn) {
/* 1141 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "getFolder(String,boolean,boolean)", new Object[] { name, 
/* 1142 */             Boolean.valueOf(create), Boolean.valueOf(setDt) });
/*      */     }
/*      */     
/* 1145 */     List<MQRFH2Element> folders = getExpandFolders();
/* 1146 */     MQRFH2Element folder = null;
/*      */     
/* 1148 */     for (int i = 0; i < folders.size() && folder == null; i++) {
/* 1149 */       MQRFH2Element element = folders.get(i);
/*      */       
/* 1151 */       if (name.equals(element.getName())) {
/* 1152 */         folder = element;
/*      */       }
/*      */     } 
/*      */     
/* 1156 */     if (folder == null && create) {
/* 1157 */       synchronized (this) {
/* 1158 */         folders.add(folder = new MQRFH2Element(name, null, setDt));
/*      */       } 
/*      */     }
/*      */     
/* 1162 */     if (Trace.isOn) {
/* 1163 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "getFolder(String,boolean,boolean)", folder);
/*      */     }
/* 1165 */     return folder;
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
/*      */   public Object getFieldValue(String folder, String tag) throws IOException {
/* 1179 */     if (Trace.isOn) {
/* 1180 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "getFieldValue(String,String)", new Object[] { folder, tag });
/*      */     }
/*      */     
/* 1183 */     Object traceRet1 = getFieldValue(folder, tag, 0);
/* 1184 */     if (Trace.isOn) {
/* 1185 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "getFieldValue(String,String)", traceRet1);
/*      */     }
/* 1187 */     return traceRet1;
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
/*      */   public Object getFieldValue(String folder, String field, int occurrenceP) throws IOException {
/* 1204 */     if (Trace.isOn) {
/* 1205 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "getFieldValue(String,String,int)", new Object[] { folder, field, 
/* 1206 */             Integer.valueOf(occurrenceP) });
/*      */     }
/* 1208 */     int occurrence = occurrenceP;
/* 1209 */     Element element = getFolder(folder, false);
/* 1210 */     Object match = null;
/*      */     
/* 1212 */     if (element != null) {
/* 1213 */       Element[] fields = element.getChildren();
/*      */       
/* 1215 */       for (int i = 0; i < fields.length && match == null; i++) {
/* 1216 */         if (field.equals(fields[i].getName()) && 
/* 1217 */           occurrence-- >= 0) {
/* 1218 */           match = fields[i].getValue();
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1224 */     if (Trace.isOn) {
/* 1225 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "getFieldValue(String,String,int)", match);
/*      */     }
/* 1227 */     return match;
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
/*      */   public List getFieldValues(String folder, String field) throws IOException {
/* 1241 */     if (Trace.isOn) {
/* 1242 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "getFieldValues(String,String)", new Object[] { folder, field });
/*      */     }
/*      */     
/* 1245 */     ArrayList<Object> list = new ArrayList();
/* 1246 */     Element element = getFolder(folder, false);
/* 1247 */     Object match = null;
/*      */     
/* 1249 */     if (element != null) {
/* 1250 */       Element[] fields = element.getChildren();
/*      */       
/* 1252 */       for (int i = 0; i < fields.length && match == null; i++) {
/* 1253 */         if (field.equals(fields[i].getName())) {
/* 1254 */           list.add(fields[i].getValue());
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1259 */     if (Trace.isOn) {
/* 1260 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "getFieldValues(String,String)", list);
/*      */     }
/* 1262 */     return list;
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
/*      */   public void setFieldValue(String folder, String field, Object value) throws IOException {
/* 1274 */     if (Trace.isOn) {
/* 1275 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "setFieldValue(String,String,Object)", new Object[] { folder, field, value });
/*      */     }
/*      */     
/* 1278 */     setFieldValue(folder, field, value, false);
/* 1279 */     if (Trace.isOn) {
/* 1280 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "setFieldValue(String,String,Object)");
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
/*      */   public void setFieldValue(String folder, String field, Object value, boolean setDt) throws IOException {
/* 1294 */     if (Trace.isOn) {
/* 1295 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "setFieldValue(String,String,Object,boolean)", new Object[] { folder, field, value, 
/* 1296 */             Boolean.valueOf(setDt) });
/*      */     }
/* 1298 */     getFolder(folder, true).setValue(field, value, setDt);
/* 1299 */     this.changed = true;
/* 1300 */     if (Trace.isOn) {
/* 1301 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "setFieldValue(String,String,Object,boolean)");
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
/*      */   public void setFieldValues(String folder, String field, List<?> values) throws IOException {
/* 1316 */     if (Trace.isOn) {
/* 1317 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "setFieldValues(String,String,List)", new Object[] { folder, field, values });
/*      */     }
/*      */     
/* 1320 */     Element element = getFolder(folder, true);
/* 1321 */     Element[] children = element.getChildren();
/*      */     
/* 1323 */     for (int i = 0; i < children.length; i++) {
/* 1324 */       if (field.equals(children[i].getName())) {
/* 1325 */         element.setValue(field, null, false);
/*      */       }
/*      */     } 
/*      */     
/* 1329 */     if (values != null) {
/* 1330 */       for (Iterator<?> it = values.iterator(); it.hasNext(); element.addElement(field, it.next()));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1335 */     this.changed = true;
/* 1336 */     if (Trace.isOn) {
/* 1337 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "setFieldValues(String,String,List)");
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
/*      */   public boolean getBooleanFieldValue(String folder, String field) throws IOException {
/* 1351 */     if (Trace.isOn) {
/* 1352 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "getBooleanFieldValue(String,String)", new Object[] { folder, field });
/*      */     }
/*      */     
/* 1355 */     boolean traceRet1 = getField(folder, field).getBooleanValue();
/* 1356 */     if (Trace.isOn) {
/* 1357 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "getBooleanFieldValue(String,String)", 
/* 1358 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 1360 */     return traceRet1;
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
/*      */   public int getIntFieldValue(String folder, String field) throws IOException {
/* 1372 */     if (Trace.isOn) {
/* 1373 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "getIntFieldValue(String,String)", new Object[] { folder, field });
/*      */     }
/*      */     
/* 1376 */     int traceRet1 = getField(folder, field).getIntValue();
/* 1377 */     if (Trace.isOn) {
/* 1378 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "getIntFieldValue(String,String)", 
/* 1379 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1381 */     return traceRet1;
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
/*      */   public void setIntFieldValue(String folder, String field, int value) throws IOException {
/* 1393 */     if (Trace.isOn) {
/* 1394 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "setIntFieldValue(String,String,int)", new Object[] { folder, field, 
/* 1395 */             Integer.valueOf(value) });
/*      */     }
/* 1397 */     getFolder(folder, true).getElement(field, true).setIntValue(value, true);
/* 1398 */     this.changed = true;
/* 1399 */     if (Trace.isOn) {
/* 1400 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "setIntFieldValue(String,String,int)");
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
/*      */   public byte getByteFieldValue(String folder, String field) throws IOException {
/* 1414 */     if (Trace.isOn) {
/* 1415 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "getByteFieldValue(String,String)", new Object[] { folder, field });
/*      */     }
/*      */     
/* 1418 */     byte traceRet1 = getField(folder, field).getByteValue();
/* 1419 */     if (Trace.isOn) {
/* 1420 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "getByteFieldValue(String,String)", 
/* 1421 */           Byte.valueOf(traceRet1));
/*      */     }
/* 1423 */     return traceRet1;
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
/*      */   public void setByteFieldValue(String folder, String field, byte value) throws IOException {
/* 1435 */     if (Trace.isOn) {
/* 1436 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "setByteFieldValue(String,String,byte)", new Object[] { folder, field, 
/* 1437 */             Byte.valueOf(value) });
/*      */     }
/* 1439 */     getFolder(folder, true).getElement(field, true).setByteValue(value, false);
/* 1440 */     this.changed = true;
/* 1441 */     if (Trace.isOn) {
/* 1442 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "setByteFieldValue(String,String,byte)");
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
/*      */   public short getShortFieldValue(String folder, String field) throws IOException {
/* 1456 */     if (Trace.isOn) {
/* 1457 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "getShortFieldValue(String,String)", new Object[] { folder, field });
/*      */     }
/*      */     
/* 1460 */     short traceRet1 = getField(folder, field).getShortValue();
/* 1461 */     if (Trace.isOn) {
/* 1462 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "getShortFieldValue(String,String)", 
/* 1463 */           Short.valueOf(traceRet1));
/*      */     }
/* 1465 */     return traceRet1;
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
/*      */   public void setShortFieldValue(String folder, String field, short value) throws IOException {
/* 1477 */     if (Trace.isOn) {
/* 1478 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "setShortFieldValue(String,String,short)", new Object[] { folder, field, 
/* 1479 */             Short.valueOf(value) });
/*      */     }
/* 1481 */     getFolder(folder, true).getElement(field, true).setShortValue(value, true);
/* 1482 */     this.changed = true;
/* 1483 */     if (Trace.isOn) {
/* 1484 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "setShortFieldValue(String,String,short)");
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
/*      */   public char getCharFieldValue(String folder, String field) throws IOException {
/* 1498 */     if (Trace.isOn) {
/* 1499 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "getCharFieldValue(String,String)", new Object[] { folder, field });
/*      */     }
/*      */     
/* 1502 */     char traceRet1 = getField(folder, field).getCharValue();
/* 1503 */     if (Trace.isOn) {
/* 1504 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "getCharFieldValue(String,String)", 
/* 1505 */           Character.valueOf(traceRet1));
/*      */     }
/* 1507 */     return traceRet1;
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
/*      */   public void setCharFieldValue(String folder, String field, char value) throws IOException {
/* 1519 */     if (Trace.isOn) {
/* 1520 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "setCharFieldValue(String,String,char)", new Object[] { folder, field, 
/* 1521 */             Character.valueOf(value) });
/*      */     }
/* 1523 */     getFolder(folder, true).getElement(field, true).setCharValue(value, false);
/* 1524 */     this.changed = true;
/* 1525 */     if (Trace.isOn) {
/* 1526 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "setCharFieldValue(String,String,char)");
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
/*      */   public long getLongFieldValue(String folder, String field) throws IOException {
/* 1540 */     if (Trace.isOn) {
/* 1541 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "getLongFieldValue(String,String)", new Object[] { folder, field });
/*      */     }
/*      */     
/* 1544 */     long traceRet1 = getField(folder, field).getLongValue();
/* 1545 */     if (Trace.isOn) {
/* 1546 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "getLongFieldValue(String,String)", 
/* 1547 */           Long.valueOf(traceRet1));
/*      */     }
/* 1549 */     return traceRet1;
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
/*      */   public void setLongFieldValue(String folder, String field, long value) throws IOException {
/* 1562 */     if (Trace.isOn) {
/* 1563 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "setLongFieldValue(String,String,long)", new Object[] { folder, field, 
/* 1564 */             Long.valueOf(value) });
/*      */     }
/* 1566 */     getFolder(folder, true).getElement(field, true).setLongValue(value, true);
/* 1567 */     this.changed = true;
/* 1568 */     if (Trace.isOn) {
/* 1569 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "setLongFieldValue(String,String,long)");
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
/*      */   public float getFloatFieldValue(String folder, String field) throws IOException {
/* 1583 */     if (Trace.isOn) {
/* 1584 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "getFloatFieldValue(String,String)", new Object[] { folder, field });
/*      */     }
/*      */     
/* 1587 */     float traceRet1 = getField(folder, field).getFloatValue();
/* 1588 */     if (Trace.isOn) {
/* 1589 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "getFloatFieldValue(String,String)", 
/* 1590 */           Float.valueOf(traceRet1));
/*      */     }
/* 1592 */     return traceRet1;
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
/*      */   public void setFloatFieldValue(String folder, String field, float value) throws IOException {
/* 1604 */     if (Trace.isOn) {
/* 1605 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "setFloatFieldValue(String,String,float)", new Object[] { folder, field, 
/* 1606 */             Float.valueOf(value) });
/*      */     }
/* 1608 */     getFolder(folder, true).getElement(field, true).setFloatValue(value, true);
/* 1609 */     this.changed = true;
/* 1610 */     if (Trace.isOn) {
/* 1611 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "setFloatFieldValue(String,String,float)");
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
/*      */   public double getDoubleFieldValue(String folder, String field) throws IOException {
/* 1625 */     if (Trace.isOn) {
/* 1626 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "getDoubleFieldValue(String,String)", new Object[] { folder, field });
/*      */     }
/*      */     
/* 1629 */     double traceRet1 = getField(folder, field).getDoubleValue();
/* 1630 */     if (Trace.isOn) {
/* 1631 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "getDoubleFieldValue(String,String)", 
/* 1632 */           Double.valueOf(traceRet1));
/*      */     }
/* 1634 */     return traceRet1;
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
/*      */   public void setDoubleFieldValue(String folder, String field, double value) throws IOException {
/* 1646 */     if (Trace.isOn) {
/* 1647 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "setDoubleFieldValue(String,String,double)", new Object[] { folder, field, 
/* 1648 */             Double.valueOf(value) });
/*      */     }
/* 1650 */     getFolder(folder, true).getElement(field, true).setDoubleValue(value, true);
/* 1651 */     this.changed = true;
/* 1652 */     if (Trace.isOn) {
/* 1653 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "setDoubleFieldValue(String,String,double)");
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
/*      */   public byte[] getBytesFieldValue(String folder, String field) throws IOException {
/* 1667 */     if (Trace.isOn) {
/* 1668 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "getBytesFieldValue(String,String)", new Object[] { folder, field });
/*      */     }
/*      */     
/* 1671 */     byte[] traceRet1 = getField(folder, field).getBytesValue();
/* 1672 */     if (Trace.isOn) {
/* 1673 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "getBytesFieldValue(String,String)", traceRet1);
/*      */     }
/*      */     
/* 1676 */     return traceRet1;
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
/*      */   public String getStringFieldValue(String folder, String field) throws IOException {
/* 1688 */     if (Trace.isOn) {
/* 1689 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "getStringFieldValue(String,String)", new Object[] { folder, field });
/*      */     }
/*      */     
/* 1692 */     String traceRet1 = getField(folder, field).getStringValue();
/* 1693 */     if (Trace.isOn) {
/* 1694 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "getStringFieldValue(String,String)", traceRet1);
/*      */     }
/*      */     
/* 1697 */     return traceRet1;
/*      */   }
/*      */   
/*      */   private Element getField(String folder, String field) throws IOException, NoSuchElementException {
/* 1701 */     if (Trace.isOn) {
/* 1702 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "getField(String,String)", new Object[] { folder, field });
/*      */     }
/*      */     
/* 1705 */     Element element = getFolder(folder, false);
/*      */     
/* 1707 */     if (element == null || (element = element.getChild(field)) == null) {
/*      */ 
/*      */       
/* 1710 */       NoSuchElementException traceRet1 = new NoSuchElementException()
/*      */         {
/*      */           private static final long serialVersionUID = -5508232031801764393L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           public Throwable fillInStackTrace() {
/* 1720 */             if (Trace.isOn) {
/* 1721 */               Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "fillInStackTrace()");
/*      */             }
/* 1723 */             if (Trace.isOn) {
/* 1724 */               Trace.exit(this, "com.ibm.mq.headers.null", "fillInStackTrace()", this);
/*      */             }
/* 1726 */             return this;
/*      */           }
/*      */         };
/* 1729 */       if (Trace.isOn) {
/* 1730 */         Trace.throwing(this, "com.ibm.mq.headers.MQRFH2", "getField(String,String)", traceRet1);
/*      */       }
/*      */       
/* 1733 */       throw traceRet1;
/*      */     } 
/*      */     
/* 1736 */     if (Trace.isOn) {
/* 1737 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "getField(String,String)", element);
/*      */     }
/* 1739 */     return element;
/*      */   }
/*      */   
/*      */   private int getPadLength(int length) {
/* 1743 */     if (Trace.isOn)
/* 1744 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "getPadLength(int)", new Object[] {
/* 1745 */             Integer.valueOf(length)
/*      */           }); 
/* 1747 */     int pad = length % 4;
/*      */     
/* 1749 */     int traceRet1 = (pad > 0) ? (4 - pad) : 0;
/* 1750 */     if (Trace.isOn) {
/* 1751 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "getPadLength(int)", 
/* 1752 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1754 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQRFH2 coalesce(MQRFH2 other, boolean relink) throws IOException {
/* 1763 */     if (Trace.isOn) {
/* 1764 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "coalesce(MQRFH2,boolean)", new Object[] { other, 
/* 1765 */             Boolean.valueOf(relink) });
/*      */     }
/* 1767 */     if (other == null || other == this) {
/* 1768 */       if (Trace.isOn) {
/* 1769 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "coalesce(MQRFH2,boolean)", this, 1);
/*      */       }
/* 1771 */       return this;
/*      */     } 
/*      */     
/* 1774 */     if (relink) {
/* 1775 */       setFormat(other.getFormat());
/* 1776 */       setEncoding(other.getEncoding());
/* 1777 */       setCodedCharSetId(other.getCodedCharSetId());
/*      */     } 
/*      */     
/* 1780 */     Iterator<MQRFH2Element> it = other.getExpandFolders().iterator();
/*      */     
/* 1782 */     while (it.hasNext()) {
/* 1783 */       MQRFH2Element element = it.next();
/* 1784 */       String name = element.getName();
/*      */       
/* 1786 */       if (getFolder(name, false) == null) {
/* 1787 */         setFolderContent(name, element.getContent());
/*      */       }
/*      */     } 
/*      */     
/* 1791 */     if (Trace.isOn) {
/* 1792 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "coalesce(MQRFH2,boolean)", this, 2);
/*      */     }
/* 1794 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/* 1800 */     if (Trace.isOn) {
/* 1801 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH2", "toString()");
/*      */     }
/* 1803 */     StringBuffer sb = new StringBuffer(super.toString());
/*      */     
/* 1805 */     if (this.folders != null) {
/* 1806 */       int count = this.folders.size();
/* 1807 */       sb.append("\n\t" + count + ((count == 1) ? " cached folder: " : " cached folders: "));
/*      */       
/* 1809 */       for (int i = 0; i < count; i++) {
/* 1810 */         sb.append("\n\t");
/* 1811 */         sb.append(this.folders.get(i));
/*      */       } 
/*      */     } 
/*      */     
/* 1815 */     String traceRet1 = new String(sb);
/* 1816 */     if (Trace.isOn) {
/* 1817 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH2", "toString()", traceRet1);
/*      */     }
/* 1819 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static interface Element
/*      */   {
/*      */     String getName();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     Object getValue();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     boolean getBooleanValue();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void setBooleanValue(boolean param1Boolean1, boolean param1Boolean2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     byte getByteValue();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void setByteValue(byte param1Byte, boolean param1Boolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     short getShortValue();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void setShortValue(short param1Short, boolean param1Boolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     char getCharValue();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void setCharValue(char param1Char, boolean param1Boolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     int getIntValue();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void setIntValue(int param1Int, boolean param1Boolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     long getLongValue();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void setLongValue(long param1Long, boolean param1Boolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     float getFloatValue();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void setFloatValue(float param1Float, boolean param1Boolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     double getDoubleValue();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void setDoubleValue(double param1Double, boolean param1Boolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     byte[] getBytesValue();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void setBytesValue(byte[] param1ArrayOfbyte, boolean param1Boolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     String getStringValue();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void setStringValue(String param1String, boolean param1Boolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void setValue(Object param1Object, boolean param1Boolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     int getType();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     boolean isNil();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     String getAttributeValue(String param1String);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void setAttributeValue(String param1String1, String param1String2);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     Element[] getChildren();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     List<Element> getChildren(String param1String);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     Element getChild(String param1String);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     Object getValue(String param1String);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void setValue(String param1String, Object param1Object);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void setValue(String param1String, Object param1Object, boolean param1Boolean);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     String getContent();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     Element getElement(String param1String, boolean param1Boolean);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     Element addElement(String param1String);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     Element addElement(String param1String, Object param1Object);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     Element addElement(String param1String, Object param1Object, boolean param1Boolean);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void removeAllElements();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     String toXML();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   class MQRFH2Element
/*      */     implements Element
/*      */   {
/* 2055 */     final StringBuffer chars = new StringBuffer();
/* 2056 */     final List<MQRFH2.Element> children = new ArrayList<>();
/*      */     
/*      */     private final MQRFH2Element parent;
/*      */     
/*      */     private String name;
/*      */     private String xml;
/*      */     private boolean setDt;
/* 2063 */     private RFH2Attribute[] attrs = new RFH2Attribute[0];
/*      */     private RFH2Attribute dtAttr;
/*      */     private RFH2Attribute nilAttr;
/*      */     private MQRFH2Element currentElement;
/*      */     
/*      */     private MQRFH2Element(MQRFH2Element parent) {
/* 2069 */       if (Trace.isOn) {
/* 2070 */         Trace.entry(this, "com.ibm.mq.headers.MQRFH2Element", "<init>(MQRFH2Element)", new Object[] { parent });
/*      */       }
/*      */       
/* 2073 */       this.parent = parent;
/* 2074 */       if (Trace.isOn) {
/* 2075 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "<init>(MQRFH2Element)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     MQRFH2Element(String text) throws IOException {
/* 2085 */       if (Trace.isOn) {
/* 2086 */         Trace.entry(this, "com.ibm.mq.headers.MQRFH2Element", "<init>(String)", new Object[] { text });
/*      */       }
/*      */       
/*      */       try {
/* 2090 */         this.parent = null;
/* 2091 */         this.xml = text;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2097 */         if (text.matches("(?s).*[\\p{Cntrl}&&[^\\t\\n\\r]].*")) {
/* 2098 */           StringBuilder sb = new StringBuilder(text);
/* 2099 */           for (int i = 0; i < sb.length(); i++) {
/* 2100 */             char c = sb.charAt(i);
/* 2101 */             if (Character.isISOControl(c) && c != '\t' && c != '\n' && c != '\r') {
/* 2102 */               sb.deleteCharAt(i);
/* 2103 */               sb.insert(i, String.format("\\u%04x", new Object[] { Integer.valueOf(c) }));
/*      */             } 
/*      */           } 
/* 2106 */           this.xml = sb.toString();
/*      */         } 
/* 2108 */         MQRFH2.this.getFolderParser().parseFolder(this, this.xml);
/*      */       
/*      */       }
/* 2111 */       catch (SAXException e) {
/* 2112 */         if (Trace.isOn) {
/* 2113 */           Trace.catchBlock(this, "com.ibm.mq.headers.MQRFH2Element", "<init>(String)", e, 1);
/*      */         }
/* 2115 */         IOException traceRet1 = new IOException(e.getMessage());
/* 2116 */         this.xml = null;
/* 2117 */         if (Trace.isOn) {
/* 2118 */           Trace.throwing(this, "com.ibm.mq.headers.MQRFH2Element", "<init>(String)", traceRet1, 1);
/*      */         }
/*      */         
/* 2121 */         throw traceRet1;
/*      */       
/*      */       }
/* 2124 */       catch (ParserConfigurationException e) {
/* 2125 */         if (Trace.isOn) {
/* 2126 */           Trace.catchBlock(this, "com.ibm.mq.headers.MQRFH2Element", "<init>(String)", e, 2);
/*      */         }
/* 2128 */         IOException traceRet2 = new IOException(e.getMessage());
/* 2129 */         if (Trace.isOn) {
/* 2130 */           Trace.throwing(this, "com.ibm.mq.headers.MQRFH2Element", "<init>(String)", traceRet2, 2);
/*      */         }
/*      */         
/* 2133 */         throw traceRet2;
/*      */       } 
/* 2135 */       if (Trace.isOn) {
/* 2136 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "<init>(String)");
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
/*      */     public MQRFH2Element(InputStream stream) throws IOException {
/* 2149 */       if (Trace.isOn) {
/* 2150 */         Trace.entry(this, "com.ibm.mq.headers.MQRFH2Element", "<init>(InputStream)", new Object[] { stream });
/*      */       }
/*      */       
/*      */       try {
/* 2154 */         this.parent = null;
/* 2155 */         MQRFH2.this.getFolderParser().parseFolder(this, stream);
/*      */       
/*      */       }
/* 2158 */       catch (SAXException e) {
/* 2159 */         if (Trace.isOn) {
/* 2160 */           Trace.catchBlock(this, "com.ibm.mq.headers.MQRFH2Element", "<init>(InputStream)", e, 1);
/*      */         }
/* 2162 */         IOException traceRet1 = new IOException(e.getMessage());
/* 2163 */         if (Trace.isOn) {
/* 2164 */           Trace.throwing(this, "com.ibm.mq.headers.MQRFH2Element", "<init>(InputStream)", traceRet1, 1);
/*      */         }
/*      */         
/* 2167 */         throw traceRet1;
/*      */       
/*      */       }
/* 2170 */       catch (ParserConfigurationException e) {
/* 2171 */         if (Trace.isOn) {
/* 2172 */           Trace.catchBlock(this, "com.ibm.mq.headers.MQRFH2Element", "<init>(InputStream)", e, 2);
/*      */         }
/* 2174 */         IOException traceRet2 = new IOException(e.getMessage());
/* 2175 */         if (Trace.isOn) {
/* 2176 */           Trace.throwing(this, "com.ibm.mq.headers.MQRFH2Element", "<init>(InputStream)", traceRet2, 2);
/*      */         }
/*      */         
/* 2179 */         throw traceRet2;
/*      */       } 
/* 2181 */       if (Trace.isOn) {
/* 2182 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "<init>(InputStream)");
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
/*      */     public MQRFH2Element(Reader reader) throws IOException {
/* 2195 */       if (Trace.isOn) {
/* 2196 */         Trace.entry(this, "com.ibm.mq.headers.MQRFH2Element", "<init>(Reader)", new Object[] { reader });
/*      */       }
/*      */       
/*      */       try {
/* 2200 */         this.parent = null;
/* 2201 */         MQRFH2.this.getFolderParser().parseFolder(this, reader);
/*      */       
/*      */       }
/* 2204 */       catch (SAXException e) {
/* 2205 */         if (Trace.isOn) {
/* 2206 */           Trace.catchBlock(this, "com.ibm.mq.headers.MQRFH2Element", "<init>(Reader)", e, 1);
/*      */         }
/* 2208 */         IOException traceRet1 = new IOException(e.getMessage());
/* 2209 */         if (Trace.isOn) {
/* 2210 */           Trace.throwing(this, "com.ibm.mq.headers.MQRFH2Element", "<init>(Reader)", traceRet1, 1);
/*      */         }
/*      */         
/* 2213 */         throw traceRet1;
/*      */       
/*      */       }
/* 2216 */       catch (ParserConfigurationException e) {
/* 2217 */         if (Trace.isOn) {
/* 2218 */           Trace.catchBlock(this, "com.ibm.mq.headers.MQRFH2Element", "<init>(Reader)", e, 2);
/*      */         }
/* 2220 */         IOException traceRet2 = new IOException(e.getMessage());
/* 2221 */         if (Trace.isOn) {
/* 2222 */           Trace.throwing(this, "com.ibm.mq.headers.MQRFH2Element", "<init>(Reader)", traceRet2, 2);
/*      */         }
/*      */         
/* 2225 */         throw traceRet2;
/*      */       } 
/* 2227 */       if (Trace.isOn) {
/* 2228 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "<init>(Reader)");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     public MQRFH2Element(String name, Object value) {
/* 2234 */       this(name, value, false);
/* 2235 */       if (Trace.isOn) {
/* 2236 */         Trace.entry(this, "com.ibm.mq.headers.MQRFH2Element", "<init>(String,Object)", new Object[] { name, value });
/*      */       }
/*      */       
/* 2239 */       if (Trace.isOn) {
/* 2240 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "<init>(String,Object)");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     public MQRFH2Element(String name, Object value, boolean setDt) {
/* 2246 */       if (Trace.isOn) {
/* 2247 */         Trace.entry(this, "com.ibm.mq.headers.MQRFH2Element", "<init>(String,Object,boolean)", new Object[] { name, value, 
/* 2248 */               Boolean.valueOf(setDt) });
/*      */       }
/* 2250 */       this.name = name;
/* 2251 */       this.parent = null;
/* 2252 */       this.setDt = setDt;
/*      */       
/* 2254 */       setValue(value, setDt);
/* 2255 */       if (Trace.isOn) {
/* 2256 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "<init>(String,Object,boolean)");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     void start(String qName, Attributes attributes) {
/* 2262 */       if (Trace.isOn) {
/* 2263 */         Trace.entry(this, "com.ibm.mq.headers.MQRFH2Element", "start(String,Attributes)", new Object[] { qName, attributes });
/*      */       }
/*      */       
/* 2266 */       if (this.name == null) {
/*      */ 
/*      */         
/* 2269 */         this.name = qName;
/* 2270 */         this.attrs = new RFH2Attribute[attributes.getLength()];
/*      */         
/* 2272 */         for (int i = 0; i < this.attrs.length; i++)
/*      */         {
/*      */ 
/*      */           
/* 2276 */           this.attrs[i] = new RFH2Attribute(attributes.getQName(i), attributes.getValue(i));
/*      */         }
/*      */         
/*      */         int index;
/*      */         
/* 2281 */         if ((index = attributes.getIndex("dt")) >= 0) {
/* 2282 */           this.dtAttr = this.attrs[index];
/* 2283 */           this.attrs[index] = null;
/*      */ 
/*      */ 
/*      */           
/* 2287 */           if (this.parent != null) {
/* 2288 */             this.parent.setDt = true;
/*      */           }
/*      */         } 
/*      */         
/* 2292 */         if ((index = attributes.getIndex("xsi:nil")) >= 0) {
/* 2293 */           this.nilAttr = this.attrs[index];
/* 2294 */           this.attrs[index] = null;
/*      */         } 
/* 2296 */       } else if (this.currentElement == null) {
/*      */ 
/*      */         
/* 2299 */         this.children.add(this.currentElement = new MQRFH2Element(this));
/* 2300 */         this.currentElement.start(qName, attributes);
/*      */       }
/*      */       else {
/*      */         
/* 2304 */         this.currentElement.start(qName, attributes);
/*      */       } 
/* 2306 */       if (Trace.isOn) {
/* 2307 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "start(String,Attributes)");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     void characters(char[] ch, int start, int length) {
/* 2313 */       if (Trace.isOn) {
/* 2314 */         Trace.entry(this, "com.ibm.mq.headers.MQRFH2Element", "characters(char [ ],int,int)", new Object[] { ch, 
/* 2315 */               Integer.valueOf(start), Integer.valueOf(length) });
/*      */       }
/* 2317 */       if (this.currentElement == null) {
/*      */ 
/*      */         
/* 2320 */         this.chars.append(ch, start, length);
/*      */       }
/*      */       else {
/*      */         
/* 2324 */         this.currentElement.characters(ch, start, length);
/*      */       } 
/* 2326 */       if (Trace.isOn) {
/* 2327 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "characters(char [ ],int,int)");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     boolean end(String qName) {
/* 2333 */       if (Trace.isOn) {
/* 2334 */         Trace.entry(this, "com.ibm.mq.headers.MQRFH2Element", "end(String)", new Object[] { qName });
/*      */       }
/*      */       
/* 2337 */       if (this.currentElement == null) {
/* 2338 */         handleUnicodeEscapes();
/*      */         
/* 2340 */         boolean traceRet1 = qName.equals(this.name);
/* 2341 */         if (Trace.isOn) {
/* 2342 */           Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "end(String)", 
/* 2343 */               Boolean.valueOf(traceRet1), 1);
/*      */         }
/* 2345 */         return traceRet1;
/* 2346 */       }  if (qName.equals(this.currentElement.name)) {
/* 2347 */         this.currentElement = null;
/*      */         
/* 2349 */         if (Trace.isOn) {
/* 2350 */           Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "end(String)", 
/* 2351 */               Boolean.valueOf(true), 2);
/*      */         }
/* 2353 */         return true;
/*      */       } 
/* 2355 */       boolean traceRet2 = this.currentElement.end(qName);
/* 2356 */       if (Trace.isOn) {
/* 2357 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "end(String)", 
/* 2358 */             Boolean.valueOf(traceRet2), 3);
/*      */       }
/* 2360 */       return traceRet2;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public String getName() {
/* 2366 */       if (Trace.isOn) {
/* 2367 */         Trace.data(this, "com.ibm.mq.headers.MQRFH2Element", "getName()", "getter", this.name);
/*      */       }
/* 2369 */       return this.name;
/*      */     }
/*      */     
/*      */     public Object getValue() {
/*      */       Object traceRet8, traceRet9;
/* 2374 */       if (Trace.isOn) {
/* 2375 */         Trace.entry(this, "com.ibm.mq.headers.MQRFH2Element", "getValue()");
/*      */       }
/* 2377 */       if (isNil()) {
/* 2378 */         if (Trace.isOn) {
/* 2379 */           Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "getValue()", null, 1);
/*      */         }
/* 2381 */         return null;
/*      */       } 
/*      */       
/* 2384 */       switch (getType()) {
/*      */         case 1:
/* 2386 */           if (this.chars.length() != 0) {
/* 2387 */             Object traceRet1 = Byte.valueOf(getByteValue());
/* 2388 */             if (Trace.isOn) {
/* 2389 */               Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "getValue()", traceRet1, 2);
/*      */             }
/* 2391 */             return traceRet1;
/*      */           } 
/* 2393 */           if (Trace.isOn) {
/* 2394 */             Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "getValue()", null, 3);
/*      */           }
/* 2396 */           return null;
/*      */         case 2:
/* 2398 */           if (this.chars.length() != 0) {
/* 2399 */             Object traceRet2 = Short.valueOf(getShortValue());
/* 2400 */             if (Trace.isOn) {
/* 2401 */               Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "getValue()", traceRet2, 4);
/*      */             }
/* 2403 */             return traceRet2;
/*      */           } 
/* 2405 */           if (Trace.isOn) {
/* 2406 */             Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "getValue()", null, 5);
/*      */           }
/* 2408 */           return null;
/*      */         case 3:
/* 2410 */           if (this.chars.length() != 0) {
/* 2411 */             Object traceRet3 = Character.valueOf(getCharValue());
/* 2412 */             if (Trace.isOn) {
/* 2413 */               Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "getValue()", traceRet3, 6);
/*      */             }
/* 2415 */             return traceRet3;
/*      */           } 
/* 2417 */           if (Trace.isOn) {
/* 2418 */             Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "getValue()", null, 7);
/*      */           }
/* 2420 */           return null;
/*      */         case 4:
/* 2422 */           if (this.chars.length() != 0) {
/* 2423 */             Object traceRet4 = Integer.valueOf(getIntValue());
/* 2424 */             if (Trace.isOn) {
/* 2425 */               Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "getValue()", traceRet4, 8);
/*      */             }
/* 2427 */             return traceRet4;
/*      */           } 
/* 2429 */           if (Trace.isOn) {
/* 2430 */             Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "getValue()", null, 9);
/*      */           }
/* 2432 */           return null;
/*      */         case 8:
/* 2434 */           if (this.chars.length() != 0) {
/* 2435 */             Object traceRet5 = Long.valueOf(getLongValue());
/* 2436 */             if (Trace.isOn) {
/* 2437 */               Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "getValue()", traceRet5, 10);
/*      */             }
/* 2439 */             return traceRet5;
/*      */           } 
/* 2441 */           if (Trace.isOn) {
/* 2442 */             Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "getValue()", null, 11);
/*      */           }
/* 2444 */           return null;
/*      */         case 9:
/* 2446 */           if (this.chars.length() != 0) {
/* 2447 */             Object traceRet6 = Float.valueOf(getFloatValue());
/* 2448 */             if (Trace.isOn) {
/* 2449 */               Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "getValue()", traceRet6, 12);
/*      */             }
/* 2451 */             return traceRet6;
/*      */           } 
/* 2453 */           if (Trace.isOn) {
/* 2454 */             Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "getValue()", null, 13);
/*      */           }
/* 2456 */           return null;
/*      */         case 10:
/* 2458 */           if (this.chars.length() != 0) {
/* 2459 */             Object traceRet7 = Double.valueOf(getDoubleValue());
/* 2460 */             if (Trace.isOn) {
/* 2461 */               Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "getValue()", traceRet7, 14);
/*      */             }
/* 2463 */             return traceRet7;
/*      */           } 
/* 2465 */           if (Trace.isOn) {
/* 2466 */             Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "getValue()", null, 15);
/*      */           }
/* 2468 */           return null;
/*      */         case 11:
/* 2470 */           traceRet8 = getBytesValue();
/* 2471 */           if (Trace.isOn) {
/* 2472 */             Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "getValue()", traceRet8, 16);
/*      */           }
/* 2474 */           return traceRet8;
/*      */         
/*      */         case 12:
/* 2477 */           traceRet9 = Boolean.valueOf(getBooleanValue());
/* 2478 */           if (Trace.isOn) {
/* 2479 */             Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "getValue()", traceRet9, 17);
/*      */           }
/* 2481 */           return traceRet9;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2486 */       Object traceRet10 = new String(this.chars);
/* 2487 */       if (Trace.isOn) {
/* 2488 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "getValue()", traceRet10, 18);
/*      */       }
/* 2490 */       return traceRet10;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean getBooleanValue() {
/* 2497 */       if (Trace.isOn) {
/* 2498 */         Trace.entry(this, "com.ibm.mq.headers.MQRFH2Element", "getBooleanValue()");
/*      */       }
/*      */       try {
/* 2501 */         boolean traceRet1 = (getIntValue() != 0);
/* 2502 */         if (Trace.isOn) {
/* 2503 */           Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "getBooleanValue()", 
/* 2504 */               Boolean.valueOf(traceRet1), 1);
/*      */         }
/* 2506 */         return traceRet1;
/*      */       
/*      */       }
/* 2509 */       catch (NumberFormatException e) {
/* 2510 */         if (Trace.isOn) {
/* 2511 */           Trace.catchBlock(this, "com.ibm.mq.headers.MQRFH2Element", "getBooleanValue()", e);
/*      */         }
/*      */ 
/*      */         
/* 2515 */         boolean traceRet2 = getStringValue().equalsIgnoreCase("true");
/* 2516 */         if (Trace.isOn) {
/* 2517 */           Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "getBooleanValue()", 
/* 2518 */               Boolean.valueOf(traceRet2), 2);
/*      */         }
/* 2520 */         return traceRet2;
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public void setBooleanValue(boolean value, boolean setDt) {
/* 2526 */       if (Trace.isOn)
/* 2527 */         Trace.entry(this, "com.ibm.mq.headers.MQRFH2Element", "setBooleanValue(boolean,boolean)", new Object[] {
/* 2528 */               Boolean.valueOf(value), Boolean.valueOf(setDt)
/*      */             }); 
/* 2530 */       setValue(value ? "1" : "0", Dt.DtBOOLEAN, setDt);
/* 2531 */       if (Trace.isOn) {
/* 2532 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "setBooleanValue(boolean,boolean)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public byte getByteValue() {
/* 2539 */       byte traceRet1 = Byte.parseByte(new String(this.chars));
/* 2540 */       if (Trace.isOn) {
/* 2541 */         Trace.data(this, "com.ibm.mq.headers.MQRFH2Element", "getByteValue()", "getter", 
/* 2542 */             Byte.valueOf(traceRet1));
/*      */       }
/* 2544 */       return traceRet1;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setByteValue(byte value, boolean setDt) {
/* 2549 */       if (Trace.isOn)
/* 2550 */         Trace.entry(this, "com.ibm.mq.headers.MQRFH2Element", "setByteValue(byte,boolean)", new Object[] {
/* 2551 */               Byte.valueOf(value), Boolean.valueOf(setDt)
/*      */             }); 
/* 2553 */       setValue(Byte.toString(value), Dt.DtBYTE, setDt);
/* 2554 */       if (Trace.isOn) {
/* 2555 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "setByteValue(byte,boolean)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public short getShortValue() {
/* 2562 */       short traceRet1 = Short.parseShort(new String(this.chars));
/* 2563 */       if (Trace.isOn) {
/* 2564 */         Trace.data(this, "com.ibm.mq.headers.MQRFH2Element", "getShortValue()", "getter", 
/* 2565 */             Short.valueOf(traceRet1));
/*      */       }
/* 2567 */       return traceRet1;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setShortValue(short value, boolean setDt) {
/* 2572 */       if (Trace.isOn)
/* 2573 */         Trace.entry(this, "com.ibm.mq.headers.MQRFH2Element", "setShortValue(short,boolean)", new Object[] {
/* 2574 */               Short.valueOf(value), Boolean.valueOf(setDt)
/*      */             }); 
/* 2576 */       setValue(Short.toString(value), Dt.DtSHORT, setDt);
/* 2577 */       if (Trace.isOn) {
/* 2578 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "setShortValue(short,boolean)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public char getCharValue() {
/* 2585 */       char traceRet1 = this.chars.charAt(0);
/* 2586 */       if (Trace.isOn) {
/* 2587 */         Trace.data(this, "com.ibm.mq.headers.MQRFH2Element", "getCharValue()", "getter", 
/* 2588 */             Character.valueOf(traceRet1));
/*      */       }
/* 2590 */       return traceRet1;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setCharValue(char value, boolean setDt) {
/* 2595 */       if (Trace.isOn)
/* 2596 */         Trace.entry(this, "com.ibm.mq.headers.MQRFH2Element", "setCharValue(char,boolean)", new Object[] {
/* 2597 */               Character.valueOf(value), Boolean.valueOf(setDt)
/*      */             }); 
/* 2599 */       setValue(Character.toString(value), Dt.DtCHAR, setDt);
/* 2600 */       if (Trace.isOn) {
/* 2601 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "setCharValue(char,boolean)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public int getIntValue() {
/* 2608 */       int traceRet1 = Integer.parseInt(new String(this.chars));
/* 2609 */       if (Trace.isOn) {
/* 2610 */         Trace.data(this, "com.ibm.mq.headers.MQRFH2Element", "getIntValue()", "getter", 
/* 2611 */             Integer.valueOf(traceRet1));
/*      */       }
/* 2613 */       return traceRet1;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setIntValue(int value, boolean setDt) {
/* 2618 */       if (Trace.isOn)
/* 2619 */         Trace.entry(this, "com.ibm.mq.headers.MQRFH2Element", "setIntValue(int,boolean)", new Object[] {
/* 2620 */               Integer.valueOf(value), Boolean.valueOf(setDt)
/*      */             }); 
/* 2622 */       setValue(Integer.toString(value), Dt.DtINT, setDt);
/* 2623 */       if (Trace.isOn) {
/* 2624 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "setIntValue(int,boolean)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public long getLongValue() {
/* 2631 */       long traceRet1 = Long.parseLong(new String(this.chars));
/* 2632 */       if (Trace.isOn) {
/* 2633 */         Trace.data(this, "com.ibm.mq.headers.MQRFH2Element", "getLongValue()", "getter", 
/* 2634 */             Long.valueOf(traceRet1));
/*      */       }
/* 2636 */       return traceRet1;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setLongValue(long value, boolean setDt) {
/* 2641 */       if (Trace.isOn)
/* 2642 */         Trace.entry(this, "com.ibm.mq.headers.MQRFH2Element", "setLongValue(long,boolean)", new Object[] {
/* 2643 */               Long.valueOf(value), Boolean.valueOf(setDt)
/*      */             }); 
/* 2645 */       setValue(Long.toString(value), Dt.DtLONG, setDt);
/* 2646 */       if (Trace.isOn) {
/* 2647 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "setLongValue(long,boolean)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public float getFloatValue() {
/* 2654 */       float traceRet1 = Float.parseFloat(new String(this.chars));
/* 2655 */       if (Trace.isOn) {
/* 2656 */         Trace.data(this, "com.ibm.mq.headers.MQRFH2Element", "getFloatValue()", "getter", 
/* 2657 */             Float.valueOf(traceRet1));
/*      */       }
/* 2659 */       return traceRet1;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setFloatValue(float value, boolean setDt) {
/* 2664 */       if (Trace.isOn)
/* 2665 */         Trace.entry(this, "com.ibm.mq.headers.MQRFH2Element", "setFloatValue(float,boolean)", new Object[] {
/* 2666 */               Float.valueOf(value), Boolean.valueOf(setDt)
/*      */             }); 
/* 2668 */       setValue(Float.toString(value), Dt.DtFLOAT, setDt);
/* 2669 */       if (Trace.isOn) {
/* 2670 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "setFloatValue(float,boolean)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public double getDoubleValue() {
/* 2677 */       double traceRet1 = Double.parseDouble(new String(this.chars));
/* 2678 */       if (Trace.isOn) {
/* 2679 */         Trace.data(this, "com.ibm.mq.headers.MQRFH2Element", "getDoubleValue()", "getter", 
/* 2680 */             Double.valueOf(traceRet1));
/*      */       }
/* 2682 */       return traceRet1;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setDoubleValue(double value, boolean setDt) {
/* 2687 */       if (Trace.isOn)
/* 2688 */         Trace.entry(this, "com.ibm.mq.headers.MQRFH2Element", "setDoubleValue(double,boolean)", new Object[] {
/* 2689 */               Double.valueOf(value), Boolean.valueOf(setDt)
/*      */             }); 
/* 2691 */       setValue(Double.toString(value), Dt.DtDOUBLE, setDt);
/* 2692 */       if (Trace.isOn) {
/* 2693 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "setDoubleValue(double,boolean)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public byte[] getBytesValue() {
/* 2700 */       byte[] traceRet1 = HexString.parseHex(new String(this.chars));
/* 2701 */       if (Trace.isOn) {
/* 2702 */         Trace.data(this, "com.ibm.mq.headers.MQRFH2Element", "getBytesValue()", "getter", traceRet1);
/*      */       }
/*      */       
/* 2705 */       return traceRet1;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setBytesValue(byte[] value, boolean setDt) {
/* 2710 */       if (Trace.isOn) {
/* 2711 */         Trace.entry(this, "com.ibm.mq.headers.MQRFH2Element", "setBytesValue(byte [ ],boolean)", new Object[] { value, 
/* 2712 */               Boolean.valueOf(setDt) });
/*      */       }
/* 2714 */       setValue(HexString.hexString(value), Dt.DtBINHEX, setDt);
/* 2715 */       if (Trace.isOn) {
/* 2716 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "setBytesValue(byte [ ],boolean)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public String getStringValue() {
/* 2723 */       String traceRet1 = new String(this.chars);
/* 2724 */       if (Trace.isOn) {
/* 2725 */         Trace.data(this, "com.ibm.mq.headers.MQRFH2Element", "getStringValue()", "getter", traceRet1);
/*      */       }
/*      */       
/* 2728 */       return traceRet1;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setStringValue(String value, boolean setDt) {
/* 2733 */       if (Trace.isOn) {
/* 2734 */         Trace.entry(this, "com.ibm.mq.headers.MQRFH2Element", "setStringValue(String,boolean)", new Object[] { value, 
/* 2735 */               Boolean.valueOf(setDt) });
/*      */       }
/* 2737 */       setValue(value, Dt.DtSTRING, setDt);
/* 2738 */       if (Trace.isOn) {
/* 2739 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "setStringValue(String,boolean)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void setValue(Object value, boolean setDt) {
/* 2746 */       if (Trace.isOn) {
/* 2747 */         Trace.entry(this, "com.ibm.mq.headers.MQRFH2Element", "setValue(Object,boolean)", new Object[] { value, 
/* 2748 */               Boolean.valueOf(setDt) });
/*      */       }
/* 2750 */       if (value == null) {
/* 2751 */         setValue((String)null, Dt.DtUNKNOWN, setDt);
/* 2752 */       } else if (value instanceof byte[]) {
/* 2753 */         setValue(HexString.hexString((byte[])value), Dt.DtBINHEX, setDt);
/* 2754 */       } else if (value instanceof Boolean) {
/* 2755 */         boolean b = ((Boolean)value).booleanValue();
/*      */         
/* 2757 */         setValue(b ? "1" : "0", Dt.DtBOOLEAN, setDt);
/*      */       } else {
/* 2759 */         setValue(value.toString(), Dt.getDtEntry(value.getClass()), setDt);
/*      */       } 
/* 2761 */       if (Trace.isOn) {
/* 2762 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "setValue(Object,boolean)");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     private void setValue(String value, Dt dt, boolean setDt) {
/* 2768 */       if (Trace.isOn) {
/* 2769 */         Trace.entry(this, "com.ibm.mq.headers.MQRFH2Element", "setValue(String,Dt,boolean)", new Object[] { value, dt, 
/* 2770 */               Boolean.valueOf(setDt) });
/*      */       }
/* 2772 */       this.chars.setLength(0);
/*      */       
/* 2774 */       if (value == null) {
/* 2775 */         this.dtAttr = null;
/*      */         
/* 2777 */         if (setDt) {
/* 2778 */           this.nilAttr = new RFH2Attribute("xsi:nil", "true");
/*      */         } else {
/* 2780 */           this.nilAttr = null;
/*      */         } 
/*      */       } else {
/* 2783 */         if (setDt && dt != null && dt != Dt.DtUNKNOWN) {
/* 2784 */           this.dtAttr = new RFH2Attribute("dt", dt.name);
/*      */         } else {
/* 2786 */           this.dtAttr = null;
/*      */         } 
/*      */         
/* 2789 */         this.nilAttr = null;
/*      */         
/* 2791 */         this.chars.append(value);
/*      */       } 
/*      */       
/* 2794 */       setChanged();
/* 2795 */       if (Trace.isOn) {
/* 2796 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "setValue(String,Dt,boolean)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public int getType() {
/* 2803 */       int typeCode = -1;
/*      */       
/* 2805 */       if (this.dtAttr != null) {
/* 2806 */         typeCode = Dt.getTypeCode(this.dtAttr.getValue());
/*      */       }
/*      */       
/* 2809 */       if (Trace.isOn) {
/* 2810 */         Trace.data(this, "com.ibm.mq.headers.MQRFH2Element", "getType()", "getter", 
/* 2811 */             Integer.valueOf(typeCode));
/*      */       }
/* 2813 */       return typeCode;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean isNil() {
/* 2818 */       boolean nil = false;
/*      */       
/* 2820 */       if (this.nilAttr != null) {
/* 2821 */         nil = this.nilAttr.getValue().equals("true");
/*      */       }
/*      */       
/* 2824 */       if (Trace.isOn) {
/* 2825 */         Trace.data(this, "com.ibm.mq.headers.MQRFH2Element", "isNil()", "getter", 
/* 2826 */             Boolean.valueOf(nil));
/*      */       }
/* 2828 */       return nil;
/*      */     }
/*      */ 
/*      */     
/*      */     public MQRFH2.Element[] getChildren() {
/* 2833 */       MQRFH2.Element[] traceRet1 = this.children.<MQRFH2.Element>toArray(new MQRFH2.Element[this.children.size()]);
/* 2834 */       if (Trace.isOn) {
/* 2835 */         Trace.data(this, "com.ibm.mq.headers.MQRFH2Element", "getChildren()", "getter", traceRet1);
/*      */       }
/*      */       
/* 2838 */       return traceRet1;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public List getChildren(String name) {
/* 2844 */       if (Trace.isOn) {
/* 2845 */         Trace.entry(this, "com.ibm.mq.headers.MQRFH2Element", "getChildren(String)", new Object[] { name });
/*      */       }
/*      */       
/* 2848 */       ArrayList<MQRFH2.Element> list = new ArrayList<>();
/* 2849 */       MQRFH2.Element[] elements = getChildren();
/*      */       
/* 2851 */       for (int i = 0; i < elements.length && name != null; i++) {
/* 2852 */         if (name.equals(elements[i].getName())) {
/* 2853 */           list.add(elements[i]);
/*      */         }
/*      */       } 
/*      */       
/* 2857 */       if (Trace.isOn) {
/* 2858 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "getChildren(String)", list);
/*      */       }
/* 2860 */       return list;
/*      */     }
/*      */ 
/*      */     
/*      */     public MQRFH2.Element getChild(String name) {
/* 2865 */       if (Trace.isOn) {
/* 2866 */         Trace.entry(this, "com.ibm.mq.headers.MQRFH2Element", "getChild(String)", new Object[] { name });
/*      */       }
/*      */       
/* 2869 */       Iterator<MQRFH2.Element> itty = this.children.iterator();
/*      */       
/* 2871 */       while (itty.hasNext()) {
/* 2872 */         MQRFH2Element element = (MQRFH2Element)itty.next();
/* 2873 */         if (element.getName().equals(name)) {
/* 2874 */           if (Trace.isOn) {
/* 2875 */             Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "getChild(String)", element, 1);
/*      */           }
/* 2877 */           return element;
/*      */         } 
/*      */       } 
/* 2880 */       if (Trace.isOn) {
/* 2881 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "getChild(String)", null, 2);
/*      */       }
/* 2883 */       return null;
/*      */     }
/*      */ 
/*      */     
/*      */     public Object getValue(String name) {
/* 2888 */       if (Trace.isOn) {
/* 2889 */         Trace.entry(this, "com.ibm.mq.headers.MQRFH2Element", "getValue(String)", new Object[] { name });
/*      */       }
/*      */       
/* 2892 */       MQRFH2.Element element = getChild(name);
/*      */       
/* 2894 */       Object traceRet1 = (element == null) ? null : element.getValue();
/* 2895 */       if (Trace.isOn) {
/* 2896 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "getValue(String)", traceRet1);
/*      */       }
/* 2898 */       return traceRet1;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setValue(String name, Object value) {
/* 2903 */       if (Trace.isOn) {
/* 2904 */         Trace.entry(this, "com.ibm.mq.headers.MQRFH2Element", "setValue(String,Object)", new Object[] { name, value });
/*      */       }
/*      */       
/* 2907 */       setValue(name, value, this.setDt);
/* 2908 */       if (Trace.isOn) {
/* 2909 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "setValue(String,Object)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void setValue(String name, Object value, boolean setDt) {
/* 2916 */       if (Trace.isOn) {
/* 2917 */         Trace.entry(this, "com.ibm.mq.headers.MQRFH2Element", "setValue(String,Object,boolean)", new Object[] { name, value, 
/* 2918 */               Boolean.valueOf(setDt) });
/*      */       }
/* 2920 */       MQRFH2.Element element = getChild(name);
/*      */       
/* 2922 */       if (element == null) {
/* 2923 */         if (value != null) {
/* 2924 */           this.children.add(element = new MQRFH2Element(name, value, setDt));
/* 2925 */           setChanged();
/*      */         } 
/* 2927 */       } else if (value == null) {
/* 2928 */         this.children.remove(element);
/* 2929 */         setChanged();
/*      */       } else {
/* 2931 */         element.setValue(value, setDt);
/*      */       } 
/* 2933 */       if (Trace.isOn) {
/* 2934 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "setValue(String,Object,boolean)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public MQRFH2.Element addElement(String name) {
/* 2941 */       if (Trace.isOn) {
/* 2942 */         Trace.entry(this, "com.ibm.mq.headers.MQRFH2Element", "addElement(String)", new Object[] { name });
/*      */       }
/*      */       
/* 2945 */       MQRFH2.Element traceRet1 = addElement(name, null);
/* 2946 */       if (Trace.isOn) {
/* 2947 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "addElement(String)", traceRet1);
/*      */       }
/* 2949 */       return traceRet1;
/*      */     }
/*      */ 
/*      */     
/*      */     public MQRFH2.Element addElement(String name, Object value) {
/* 2954 */       if (Trace.isOn) {
/* 2955 */         Trace.entry(this, "com.ibm.mq.headers.MQRFH2Element", "addElement(String,Object)", new Object[] { name, value });
/*      */       }
/*      */       
/* 2958 */       MQRFH2.Element traceRet1 = addElement(name, value, false);
/* 2959 */       if (Trace.isOn) {
/* 2960 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "addElement(String,Object)", traceRet1);
/*      */       }
/*      */       
/* 2963 */       return traceRet1;
/*      */     }
/*      */ 
/*      */     
/*      */     public MQRFH2.Element addElement(String name, Object value, boolean setDt) {
/* 2968 */       if (Trace.isOn) {
/* 2969 */         Trace.entry(this, "com.ibm.mq.headers.MQRFH2Element", "addElement(String,Object,boolean)", new Object[] { name, value, 
/* 2970 */               Boolean.valueOf(setDt) });
/*      */       }
/* 2972 */       MQRFH2.Element element = new MQRFH2Element(name, value, setDt);
/*      */       
/* 2974 */       this.children.add(element);
/* 2975 */       setChanged();
/*      */       
/* 2977 */       if (Trace.isOn) {
/* 2978 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "addElement(String,Object,boolean)", element);
/*      */       }
/*      */       
/* 2981 */       return element;
/*      */     }
/*      */ 
/*      */     
/*      */     public void removeAllElements() {
/* 2986 */       if (Trace.isOn) {
/* 2987 */         Trace.entry(this, "com.ibm.mq.headers.MQRFH2Element", "removeAllElements()");
/*      */       }
/* 2989 */       this.children.clear();
/* 2990 */       setChanged();
/* 2991 */       if (Trace.isOn) {
/* 2992 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "removeAllElements()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public MQRFH2.Element getElement(String name, boolean create) {
/* 2999 */       if (Trace.isOn) {
/* 3000 */         Trace.entry(this, "com.ibm.mq.headers.MQRFH2Element", "getElement(String,boolean)", new Object[] { name, 
/* 3001 */               Boolean.valueOf(create) });
/*      */       }
/* 3003 */       MQRFH2.Element element = getChild(name);
/*      */       
/* 3005 */       if (element == null && create) {
/* 3006 */         MQRFH2Element created = new MQRFH2Element(this);
/*      */         
/* 3008 */         created.name = name;
/* 3009 */         this.children.add(element = created);
/* 3010 */         setChanged();
/*      */       } 
/*      */       
/* 3013 */       if (Trace.isOn) {
/* 3014 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "getElement(String,boolean)", element);
/*      */       }
/*      */       
/* 3017 */       return element;
/*      */     }
/*      */ 
/*      */     
/*      */     public String getAttributeValue(String name) {
/* 3022 */       if (Trace.isOn) {
/* 3023 */         Trace.entry(this, "com.ibm.mq.headers.MQRFH2Element", "getAttributeValue(String)", new Object[] { name });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 3028 */       int index = getAttributeIndex(name);
/*      */       
/* 3030 */       String traceRet1 = (index < 0 || this.attrs[index] == null) ? null : this.attrs[index].getValue();
/* 3031 */       if (Trace.isOn) {
/* 3032 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "getAttributeValue(String)", traceRet1);
/*      */       }
/*      */       
/* 3035 */       return traceRet1;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setAttributeValue(String name, String value) {
/* 3040 */       if (Trace.isOn) {
/* 3041 */         Trace.entry(this, "com.ibm.mq.headers.MQRFH2Element", "setAttributeValue(String,String)", new Object[] { name, value });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 3046 */       int index = getAttributeIndex(name);
/*      */       
/* 3048 */       if (value == null) {
/* 3049 */         if (index >= 0)
/*      */         {
/*      */           
/* 3052 */           this.attrs[index] = null;
/*      */         }
/*      */       }
/* 3055 */       else if (index >= 0) {
/* 3056 */         this.attrs[index].setValue(value);
/*      */       }
/*      */       else {
/*      */         
/* 3060 */         System.arraycopy(this.attrs, 0, this.attrs = new RFH2Attribute[this.attrs.length + 1], 0, this.attrs.length - 1);
/*      */         
/* 3062 */         this.attrs[this.attrs.length - 1] = new RFH2Attribute(name, value);
/*      */       } 
/*      */       
/* 3065 */       if (Trace.isOn) {
/* 3066 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "setAttributeValue(String,String)");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     private int getAttributeIndex(String name) {
/* 3072 */       if (Trace.isOn) {
/* 3073 */         Trace.entry(this, "com.ibm.mq.headers.MQRFH2Element", "getAttributeIndex(String)", new Object[] { name });
/*      */       }
/*      */       
/* 3076 */       int index = -1;
/*      */       
/* 3078 */       for (int i = 0; index < 0 && i < this.attrs.length; i++) {
/* 3079 */         if (this.attrs[i] != null && 
/* 3080 */           name != null && name.equals(this.attrs[i].getName())) {
/* 3081 */           index = i;
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 3086 */       if (Trace.isOn) {
/* 3087 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "getAttributeIndex(String)", 
/* 3088 */             Integer.valueOf(index));
/*      */       }
/* 3090 */       return index;
/*      */     }
/*      */ 
/*      */     
/*      */     public String getContent() {
/* 3095 */       StringBuffer sb = new StringBuffer();
/*      */       
/* 3097 */       sb.append(this.chars);
/*      */       
/* 3099 */       synchronized (this.children) {
/* 3100 */         int count = this.children.size();
/*      */         
/* 3102 */         for (int i = 0; i < count; i++) {
/* 3103 */           sb.append(((MQRFH2Element)this.children.get(i)).toXML());
/*      */         }
/*      */       } 
/*      */       
/* 3107 */       String traceRet1 = new String(sb);
/* 3108 */       if (Trace.isOn) {
/* 3109 */         Trace.data(this, "com.ibm.mq.headers.MQRFH2Element", "getContent()", "getter", traceRet1);
/*      */       }
/* 3111 */       return traceRet1;
/*      */     }
/*      */ 
/*      */     
/*      */     public String toXML() {
/* 3116 */       if (Trace.isOn) {
/* 3117 */         Trace.entry(this, "com.ibm.mq.headers.MQRFH2Element", "toXML()");
/*      */       }
/* 3119 */       if (this.xml != null) {
/* 3120 */         if (Trace.isOn) {
/* 3121 */           Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "toXML()", this.xml, 1);
/*      */         }
/* 3123 */         return this.xml;
/*      */       } 
/*      */       
/* 3126 */       StringBuffer sb = new StringBuffer();
/*      */       
/* 3128 */       sb.append('<');
/* 3129 */       sb.append(this.name);
/*      */       
/* 3131 */       for (int i = 0; i < this.attrs.length; i++) {
/* 3132 */         if (this.attrs[i] != null) {
/* 3133 */           sb.append(' ');
/* 3134 */           sb.append(this.attrs[i].toXML());
/*      */         } 
/*      */       } 
/*      */       
/* 3138 */       if (this.dtAttr != null) {
/* 3139 */         sb.append(' ');
/* 3140 */         sb.append(this.dtAttr.toXML());
/*      */       } 
/*      */       
/* 3143 */       if (this.nilAttr != null) {
/* 3144 */         sb.append(' ');
/* 3145 */         sb.append(this.nilAttr.toXML());
/*      */       } 
/*      */       
/* 3148 */       sb.append('>');
/*      */       
/* 3150 */       int length = this.chars.length();
/*      */       
/* 3152 */       for (int j = 0; j < length; j++) {
/*      */ 
/*      */         
/* 3155 */         char c = this.chars.charAt(j);
/*      */         
/* 3157 */         switch (c) {
/*      */           case '<':
/* 3159 */             sb.append("&lt;");
/*      */             break;
/*      */           
/*      */           case '>':
/* 3163 */             sb.append("&gt;");
/*      */             break;
/*      */           
/*      */           case '&':
/* 3167 */             sb.append("&amp;");
/*      */             break;
/*      */           
/*      */           default:
/* 3171 */             sb.append(c);
/*      */             break;
/*      */         } 
/*      */       } 
/* 3175 */       synchronized (this.children) {
/* 3176 */         int count = this.children.size();
/*      */         
/* 3178 */         for (int k = 0; k < count; k++) {
/* 3179 */           sb.append(((MQRFH2Element)this.children.get(k)).toXML());
/*      */         }
/*      */       } 
/*      */       
/* 3183 */       sb.append("</");
/* 3184 */       sb.append(this.name);
/* 3185 */       sb.append('>');
/*      */       
/* 3187 */       String traceRet1 = new String(sb);
/* 3188 */       if (Trace.isOn) {
/* 3189 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "toXML()", traceRet1, 2);
/*      */       }
/* 3191 */       return traceRet1;
/*      */     }
/*      */     
/*      */     private void setChanged() {
/* 3195 */       if (Trace.isOn) {
/* 3196 */         Trace.entry(this, "com.ibm.mq.headers.MQRFH2Element", "setChanged()");
/*      */       }
/* 3198 */       MQRFH2.this.setChanged();
/*      */       
/* 3200 */       for (MQRFH2Element element = this; element != null; element = element.parent) {
/* 3201 */         element.xml = null;
/*      */       }
/* 3203 */       if (Trace.isOn) {
/* 3204 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "setChanged()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void handleUnicodeEscapes() {
/* 3215 */       if (Trace.isOn) {
/* 3216 */         Trace.entry(this, "com.ibm.mq.headers.MQRFH2Element", "handleUnicodeEscapes()");
/*      */       }
/* 3218 */       for (int i = 0; i < this.chars.length(); i++) {
/* 3219 */         if (this.chars.charAt(i) == '\\' && this.chars.length() - i > 4 && this.chars.charAt(i + 1) == 'u') {
/* 3220 */           int c0 = Character.digit(this.chars.charAt(i + 2), 16);
/* 3221 */           int c1 = Character.digit(this.chars.charAt(i + 3), 16);
/* 3222 */           int c2 = Character.digit(this.chars.charAt(i + 4), 16);
/* 3223 */           int c3 = Character.digit(this.chars.charAt(i + 5), 16);
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 3228 */           if ((c0 | c1 | c2 | c3) != -1) {
/*      */ 
/*      */             
/* 3231 */             this.chars.setCharAt(i, (char)(c0 << 12 | c1 << 8 | c2 << 4 | c3));
/* 3232 */             this.chars.delete(i + 1, i + 6);
/*      */           } 
/*      */         } 
/*      */       } 
/* 3236 */       if (Trace.isOn) {
/* 3237 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "handleUnicodeEscapes()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public String toString() {
/* 3244 */       if (Trace.isOn) {
/* 3245 */         Trace.entry(this, "com.ibm.mq.headers.MQRFH2Element", "toString()");
/*      */       }
/* 3247 */       String traceRet1 = getClass().getName() + ": " + toXML();
/* 3248 */       if (Trace.isOn) {
/* 3249 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH2Element", "toString()", traceRet1);
/*      */       }
/* 3251 */       return traceRet1;
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\MQRFH2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */