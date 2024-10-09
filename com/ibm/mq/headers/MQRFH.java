/*     */ package com.ibm.mq.headers;
/*     */ 
/*     */ import com.ibm.mq.headers.internal.CachingHeader;
/*     */ import com.ibm.mq.headers.internal.Header;
/*     */ import com.ibm.mq.headers.internal.HeaderField;
/*     */ import com.ibm.mq.headers.internal.HeaderType;
/*     */ import com.ibm.mq.headers.internal.MessageWrapper;
/*     */ import com.ibm.mq.headers.internal.store.Store;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQRFH
/*     */   extends Header
/*     */   implements CachingHeader, MQChainable
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQRFH.java";
/*     */   
/*     */   static {
/*  67 */     if (Trace.isOn) {
/*  68 */       Trace.data("com.ibm.mq.headers.MQRFH", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQRFH.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  77 */   static final HeaderType TYPE = new HeaderType("MQRFH");
/*     */   
/*  79 */   static final HeaderField StrucId = TYPE.addMQChar("StrucId", "RFH ", true);
/*  80 */   static final HeaderField Version = TYPE.addMQLong("Version", 1, true);
/*  81 */   static final HeaderField StrucLength = TYPE.addMQLong("StrucLength", 32);
/*     */   
/*  83 */   static final HeaderField Encoding = TYPE.addMQLong("Encoding");
/*  84 */   static final HeaderField CodedCharSetId = TYPE.addMQLong("CodedCharSetId");
/*  85 */   static final HeaderField Format = TYPE.addMQChar("Format", 8);
/*  86 */   static final HeaderField Flags = TYPE.addMQLong("Flags");
/*  87 */   static final HeaderField NameValueString = TYPE.addMQChar("NameValueString", null, StrucLength);
/*     */ 
/*     */ 
/*     */   
/*     */   private volatile List contents;
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean changed;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static NameValuePair createNameValuePair(String name, String value) {
/* 101 */     if (Trace.isOn) {
/* 102 */       Trace.entry("com.ibm.mq.headers.MQRFH", "createNameValuePair(String,String)", new Object[] { name, value });
/*     */     }
/*     */     
/* 105 */     NameValuePair traceRet1 = new MQRFH2NameValuePair(name, value);
/* 106 */     if (Trace.isOn) {
/* 107 */       Trace.exit("com.ibm.mq.headers.MQRFH", "createNameValuePair(String,String)", traceRet1);
/*     */     }
/* 109 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQRFH(String nameValueString) {
/* 118 */     this();
/* 119 */     if (Trace.isOn) {
/* 120 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH", "<init>(String)", new Object[] { nameValueString });
/*     */     }
/*     */     
/* 123 */     setNameValueString(nameValueString);
/*     */     
/* 125 */     if (Trace.isOn) {
/* 126 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH", "<init>(String)");
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
/*     */   public MQRFH(NameValuePair[] items) throws IOException {
/* 139 */     this();
/* 140 */     if (Trace.isOn) {
/* 141 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH", "<init>(NameValuePair [ ])", new Object[] { items });
/*     */     }
/*     */     
/* 144 */     setNameValuePairs(items);
/*     */     
/* 146 */     if (Trace.isOn) {
/* 147 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH", "<init>(NameValuePair [ ])");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQRFH() {
/* 156 */     super(TYPE);
/* 157 */     if (Trace.isOn) {
/* 158 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH", "<init>()");
/*     */     }
/* 160 */     if (Trace.isOn) {
/* 161 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH", "<init>()");
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
/*     */   public MQRFH(DataInput message) throws MQDataException, IOException {
/* 174 */     this();
/* 175 */     if (Trace.isOn) {
/* 176 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH", "<init>(DataInput)", new Object[] { message });
/*     */     }
/* 178 */     read((DataInput)MessageWrapper.wrap(message));
/*     */     
/* 180 */     if (Trace.isOn) {
/* 181 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH", "<init>(DataInput)");
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
/*     */   public MQRFH(DataInput message, int encoding, int characterSet) throws MQDataException, IOException {
/* 196 */     this();
/* 197 */     if (Trace.isOn) {
/* 198 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH", "<init>(DataInput,int,int)", new Object[] { message, 
/* 199 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*     */     }
/*     */     try {
/* 202 */       read((DataInput)MessageWrapper.wrap(message), encoding, characterSet);
/*     */     }
/* 204 */     catch (MQDataException mde) {
/* 205 */       if (Trace.isOn) {
/* 206 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQRFH", "<init>(DataInput,int,int)", mde, 1);
/*     */       }
/*     */       
/* 209 */       if (Trace.isOn) {
/* 210 */         Trace.throwing(this, "com.ibm.mq.headers.MQRFH", "<init>(DataInput,int,int)", mde, 1);
/*     */       }
/* 212 */       throw mde;
/*     */     }
/* 214 */     catch (IOException ioe) {
/* 215 */       if (Trace.isOn) {
/* 216 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQRFH", "<init>(DataInput,int,int)", ioe, 2);
/*     */       }
/* 218 */       if (Trace.isOn) {
/* 219 */         Trace.throwing(this, "com.ibm.mq.headers.MQRFH", "<init>(DataInput,int,int)", ioe, 2);
/*     */       }
/* 221 */       throw ioe;
/*     */     }
/* 223 */     catch (Exception e) {
/* 224 */       if (Trace.isOn) {
/* 225 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQRFH", "<init>(DataInput,int,int)", e, 3);
/*     */       }
/* 227 */       RuntimeException traceRet1 = new RuntimeException(e);
/* 228 */       if (Trace.isOn) {
/* 229 */         Trace.throwing(this, "com.ibm.mq.headers.MQRFH", "<init>(DataInput,int,int)", traceRet1, 3);
/*     */       }
/*     */       
/* 232 */       throw traceRet1;
/*     */     } 
/* 234 */     if (Trace.isOn) {
/* 235 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH", "<init>(DataInput,int,int)");
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
/*     */   public synchronized List getNameValuePairs() throws IOException {
/* 248 */     if (this.contents == null) {
/* 249 */       List<MQRFH2NameValuePair> list = new ArrayList();
/* 250 */       StringTokenizer st = new StringTokenizer(getNameValueData(), " \n\r\t\000");
/*     */       
/* 252 */       while (st.hasMoreTokens()) {
/* 253 */         list.add(new MQRFH2NameValuePair(st));
/*     */       }
/*     */       
/* 256 */       this.contents = list;
/*     */     } 
/*     */     
/* 259 */     if (Trace.isOn) {
/* 260 */       Trace.data(this, "com.ibm.mq.headers.MQRFH", "getNameValuePairs()", "getter", this.contents);
/*     */     }
/* 262 */     return this.contents;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNamedValue(String name) throws IOException {
/* 272 */     if (Trace.isOn) {
/* 273 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH", "getNamedValue(String)", new Object[] { name });
/*     */     }
/* 275 */     NameValuePair nvp = getNameValuePair(name);
/*     */     
/* 277 */     String traceRet1 = (nvp == null) ? null : nvp.getValue();
/*     */     
/* 279 */     if (Trace.isOn) {
/* 280 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH", "getNamedValue(String)", traceRet1);
/*     */     }
/* 282 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NameValuePair getNameValuePair(String name) throws IOException {
/* 293 */     if (Trace.isOn) {
/* 294 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH", "getNameValuePair(String)", new Object[] { name });
/*     */     }
/*     */     
/* 297 */     NameValuePair match = null;
/* 298 */     Iterator<?> it = getNameValuePairs().iterator();
/*     */     
/* 300 */     while (match == null && it.hasNext()) {
/* 301 */       MQRFH2NameValuePair nvp = (MQRFH2NameValuePair)it.next();
/*     */       
/* 303 */       if (name.equals(nvp.getName())) {
/* 304 */         match = nvp;
/*     */       }
/*     */     } 
/*     */     
/* 308 */     if (Trace.isOn) {
/* 309 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH", "getNameValuePair(String)", match);
/*     */     }
/* 311 */     return match;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addNameValuePair(NameValuePair item) throws IOException {
/* 322 */     if (Trace.isOn) {
/* 323 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH", "addNameValuePair(NameValuePair)", new Object[] { item });
/*     */     }
/*     */     
/* 326 */     if (item != null) {
/* 327 */       MQRFH2NameValuePair nv = (item.getClass() == MQRFH2NameValuePair.class) ? (MQRFH2NameValuePair)item : new MQRFH2NameValuePair(item.getName(), item.getValue());
/*     */       
/* 329 */       getNameValuePairs().add(nv);
/*     */       
/* 331 */       this.changed = true;
/*     */     } 
/*     */     
/* 334 */     if (Trace.isOn) {
/* 335 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH", "addNameValuePair(NameValuePair)");
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
/*     */   public void addNameValuePair(String name, String value) throws IOException {
/* 349 */     if (Trace.isOn) {
/* 350 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH", "addNameValuePair(String,String)", new Object[] { name, value });
/*     */     }
/*     */     
/* 353 */     addNameValuePair(new MQRFH2NameValuePair(name, value));
/*     */     
/* 355 */     if (Trace.isOn) {
/* 356 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH", "addNameValuePair(String,String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void setNameValuePairs(NameValuePair[] items) throws IOException {
/* 367 */     if (Trace.isOn) {
/* 368 */       Trace.data(this, "com.ibm.mq.headers.MQRFH", "setNameValuePairs(NameValuePair [ ])", "setter", items);
/*     */     }
/*     */     
/* 371 */     if (this.contents != null) {
/* 372 */       this.contents.clear();
/*     */     }
/*     */     
/* 375 */     setIntValue(StrucLength, 32);
/*     */     
/* 377 */     for (int i = 0; i < items.length; i++) {
/* 378 */       addNameValuePair(items[i]);
/*     */     }
/*     */     
/* 381 */     this.changed = true;
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
/*     */   public int write(DataOutput message, int encoding, int characterSet) throws IOException {
/* 395 */     if (Trace.isOn) {
/* 396 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH", "write(DataOutput,int,int)", new Object[] { message, 
/* 397 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*     */     }
/* 399 */     writeCachedContent();
/*     */     
/* 401 */     int traceRet1 = super.write(message, encoding, characterSet);
/*     */     
/* 403 */     if (Trace.isOn) {
/* 404 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH", "write(DataOutput,int,int)", 
/* 405 */           Integer.valueOf(traceRet1));
/*     */     }
/* 407 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeCachedContent() throws IOException {
/* 417 */     if (Trace.isOn) {
/* 418 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH", "writeCachedContent()");
/*     */     }
/* 420 */     if (this.changed && this.contents != null) {
/* 421 */       StringBuffer sb = new StringBuffer();
/* 422 */       Iterator<MQRFH2NameValuePair> it = this.contents.iterator();
/*     */       
/* 424 */       while (it.hasNext()) {
/* 425 */         MQRFH2NameValuePair nv = it.next();
/*     */         
/* 427 */         sb.append(nv.getNameValueString());
/* 428 */         sb.append(false);
/*     */       } 
/*     */       
/* 431 */       int length = sb.length();
/*     */       
/* 433 */       if (length > 0) {
/* 434 */         sb.setLength(--length);
/*     */       }
/*     */       
/* 437 */       length += getPadLength(length);
/* 438 */       this.changed = false;
/*     */       
/* 440 */       Store store = store(32 + length);
/*     */       
/* 442 */       store.setString(32, new String(sb), length, 1208);
/* 443 */       setIntValue(StrucLength, 32 + length);
/*     */     } 
/*     */     
/* 446 */     if (Trace.isOn) {
/* 447 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH", "writeCachedContent()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readCachedContent() throws IOException {
/* 458 */     if (Trace.isOn) {
/* 459 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH", "readCachedContent()");
/*     */     }
/* 461 */     getNameValuePairs();
/*     */     
/* 463 */     if (Trace.isOn) {
/* 464 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH", "readCachedContent()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void discardCachedContent() {
/* 474 */     if (Trace.isOn) {
/* 475 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH", "discardCachedContent()");
/*     */     }
/* 477 */     this.contents = null;
/* 478 */     this.changed = false;
/*     */     
/* 480 */     if (Trace.isOn) {
/* 481 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH", "discardCachedContent()");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private int getPadLength(int length) {
/* 487 */     if (Trace.isOn)
/* 488 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH", "getPadLength(int)", new Object[] {
/* 489 */             Integer.valueOf(length)
/*     */           }); 
/* 491 */     int pad = length % 4;
/*     */     
/* 493 */     int traceRet1 = (pad > 0) ? (4 - pad) : 0;
/*     */     
/* 495 */     if (Trace.isOn) {
/* 496 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH", "getPadLength(int)", Integer.valueOf(traceRet1));
/*     */     }
/*     */     
/* 499 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 507 */     if (Trace.isOn) {
/* 508 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH", "size()");
/*     */     }
/*     */     try {
/* 511 */       writeCachedContent();
/*     */       
/* 513 */       int traceRet1 = super.size();
/*     */       
/* 515 */       if (Trace.isOn) {
/* 516 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH", "size()", Integer.valueOf(traceRet1));
/*     */       }
/* 518 */       return traceRet1;
/*     */     
/*     */     }
/* 521 */     catch (IOException e) {
/* 522 */       if (Trace.isOn) {
/* 523 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQRFH", "size()", e);
/*     */       }
/* 525 */       RuntimeException traceRet2 = new RuntimeException(e);
/* 526 */       if (Trace.isOn) {
/* 527 */         Trace.throwing(this, "com.ibm.mq.headers.MQRFH", "size()", traceRet2);
/*     */       }
/* 529 */       throw traceRet2;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStrucId() {
/* 539 */     String traceRet1 = getStringValue(StrucId);
/* 540 */     if (Trace.isOn) {
/* 541 */       Trace.data(this, "com.ibm.mq.headers.MQRFH", "getStrucId()", "getter", traceRet1);
/*     */     }
/* 543 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion() {
/* 552 */     int traceRet1 = getIntValue(Version);
/* 553 */     if (Trace.isOn) {
/* 554 */       Trace.data(this, "com.ibm.mq.headers.MQRFH", "getVersion()", "getter", 
/* 555 */           Integer.valueOf(traceRet1));
/*     */     }
/* 557 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStrucLength() {
/* 566 */     if (Trace.isOn) {
/* 567 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH", "getStrucLength()");
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 572 */       writeCachedContent();
/*     */       
/* 574 */       int traceRet1 = getIntValue(StrucLength);
/*     */       
/* 576 */       if (Trace.isOn) {
/* 577 */         Trace.exit(this, "com.ibm.mq.headers.MQRFH", "getStrucLength()", 
/* 578 */             Integer.valueOf(traceRet1));
/*     */       }
/* 580 */       return traceRet1;
/*     */     
/*     */     }
/* 583 */     catch (IOException e) {
/* 584 */       if (Trace.isOn) {
/* 585 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQRFH", "getStrucLength()", e);
/*     */       }
/* 587 */       RuntimeException traceRet2 = new RuntimeException(e);
/* 588 */       if (Trace.isOn) {
/* 589 */         Trace.throwing(this, "com.ibm.mq.headers.MQRFH", "getStrucLength()", traceRet2);
/*     */       }
/* 591 */       throw traceRet2;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEncoding() {
/* 601 */     int traceRet1 = getIntValue(Encoding);
/* 602 */     if (Trace.isOn) {
/* 603 */       Trace.data(this, "com.ibm.mq.headers.MQRFH", "getEncoding()", "getter", 
/* 604 */           Integer.valueOf(traceRet1));
/*     */     }
/* 606 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEncoding(int value) {
/* 615 */     if (Trace.isOn) {
/* 616 */       Trace.data(this, "com.ibm.mq.headers.MQRFH", "setEncoding(int)", "setter", 
/* 617 */           Integer.valueOf(value));
/*     */     }
/* 619 */     setIntValue(Encoding, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCodedCharSetId() {
/* 628 */     int traceRet1 = getIntValue(CodedCharSetId);
/* 629 */     if (Trace.isOn) {
/* 630 */       Trace.data(this, "com.ibm.mq.headers.MQRFH", "getCodedCharSetId()", "getter", 
/* 631 */           Integer.valueOf(traceRet1));
/*     */     }
/* 633 */     return traceRet1;
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
/*     */   public void setCodedCharSetId(int value) {
/* 645 */     if (Trace.isOn) {
/* 646 */       Trace.data(this, "com.ibm.mq.headers.MQRFH", "setCodedCharSetId(int)", "setter", 
/* 647 */           Integer.valueOf(value));
/*     */     }
/* 649 */     setIntValue(CodedCharSetId, (value == 0) ? -2 : value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFormat() {
/* 658 */     String traceRet1 = getStringValue(Format);
/* 659 */     if (Trace.isOn) {
/* 660 */       Trace.data(this, "com.ibm.mq.headers.MQRFH", "getFormat()", "getter", traceRet1);
/*     */     }
/* 662 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFormat(String value) {
/* 671 */     if (Trace.isOn) {
/* 672 */       Trace.data(this, "com.ibm.mq.headers.MQRFH", "setFormat(String)", "setter", value);
/*     */     }
/* 674 */     setStringValue(Format, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFlags() {
/* 683 */     int traceRet1 = getIntValue(Flags);
/* 684 */     if (Trace.isOn) {
/* 685 */       Trace.data(this, "com.ibm.mq.headers.MQRFH", "getFlags()", "getter", 
/* 686 */           Integer.valueOf(traceRet1));
/*     */     }
/* 688 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFlags(int value) {
/* 697 */     if (Trace.isOn) {
/* 698 */       Trace.data(this, "com.ibm.mq.headers.MQRFH", "setFlags(int)", "setter", 
/* 699 */           Integer.valueOf(value));
/*     */     }
/* 701 */     setIntValue(Flags, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNameValueData() {
/* 710 */     String traceRet1 = getStringValue(NameValueString);
/* 711 */     if (Trace.isOn) {
/* 712 */       Trace.data(this, "com.ibm.mq.headers.MQRFH", "getNameValueData()", "getter", traceRet1);
/*     */     }
/* 714 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNameValueString(String value) {
/* 723 */     if (Trace.isOn) {
/* 724 */       Trace.data(this, "com.ibm.mq.headers.MQRFH", "setNameValueString(String)", "setter", value);
/*     */     }
/* 726 */     setStringValue(NameValueString, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int nextEncoding() {
/* 736 */     if (Trace.isOn) {
/* 737 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH", "nextEncoding()");
/*     */     }
/* 739 */     int traceRet1 = getEncoding();
/*     */     
/* 741 */     if (Trace.isOn) {
/* 742 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH", "nextEncoding()", Integer.valueOf(traceRet1));
/*     */     }
/* 744 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void nextEncoding(int value) {
/* 752 */     if (Trace.isOn)
/* 753 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH", "nextEncoding(int)", new Object[] {
/* 754 */             Integer.valueOf(value)
/*     */           }); 
/* 756 */     setEncoding(value);
/*     */     
/* 758 */     if (Trace.isOn) {
/* 759 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH", "nextEncoding(int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int nextCharacterSet() {
/* 769 */     if (Trace.isOn) {
/* 770 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH", "nextCharacterSet()");
/*     */     }
/* 772 */     int traceRet1 = getCodedCharSetId();
/*     */     
/* 774 */     if (Trace.isOn) {
/* 775 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH", "nextCharacterSet()", 
/* 776 */           Integer.valueOf(traceRet1));
/*     */     }
/* 778 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void nextCharacterSet(int value) {
/* 786 */     if (Trace.isOn)
/* 787 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH", "nextCharacterSet(int)", new Object[] {
/* 788 */             Integer.valueOf(value)
/*     */           }); 
/* 790 */     setCodedCharSetId(value);
/*     */     
/* 792 */     if (Trace.isOn) {
/* 793 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH", "nextCharacterSet(int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String nextFormat() {
/* 803 */     if (Trace.isOn) {
/* 804 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH", "nextFormat()");
/*     */     }
/* 806 */     String traceRet1 = getFormat();
/*     */     
/* 808 */     if (Trace.isOn) {
/* 809 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH", "nextFormat()", traceRet1);
/*     */     }
/* 811 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void nextFormat(String value) {
/* 819 */     if (Trace.isOn) {
/* 820 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH", "nextFormat(String)", new Object[] { value });
/*     */     }
/* 822 */     setFormat(value);
/*     */     
/* 824 */     if (Trace.isOn) {
/* 825 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH", "nextFormat(String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String format() {
/* 835 */     if (Trace.isOn) {
/* 836 */       Trace.entry(this, "com.ibm.mq.headers.MQRFH", "format()");
/*     */     }
/* 838 */     if (Trace.isOn) {
/* 839 */       Trace.exit(this, "com.ibm.mq.headers.MQRFH", "format()", "MQHRF   ");
/*     */     }
/* 841 */     return "MQHRF   ";
/*     */   }
/*     */   
/*     */   public static interface NameValuePair {
/*     */     String getName();
/*     */     
/*     */     String getValue();
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\MQRFH.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */