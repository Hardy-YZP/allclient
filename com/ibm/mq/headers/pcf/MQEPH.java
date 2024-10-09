/*      */ package com.ibm.mq.headers.pcf;
/*      */ 
/*      */ import com.ibm.mq.headers.MQChainable;
/*      */ import com.ibm.mq.headers.MQDataException;
/*      */ import com.ibm.mq.headers.MQHeader;
/*      */ import com.ibm.mq.headers.MQHeaderContext;
/*      */ import com.ibm.mq.headers.internal.Header;
/*      */ import com.ibm.mq.headers.internal.HeaderField;
/*      */ import com.ibm.mq.headers.internal.HeaderType;
/*      */ import com.ibm.mq.headers.internal.MessageWrapper;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.io.DataInput;
/*      */ import java.io.DataOutput;
/*      */ import java.io.IOException;
/*      */ import java.util.Enumeration;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Vector;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class MQEPH
/*      */   extends Header
/*      */   implements MQChainable, PCFContent
/*      */ {
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/MQEPH.java";
/*      */   public static final int SIZE = 68;
/*      */   
/*      */   static {
/*   65 */     if (Trace.isOn) {
/*   66 */       Trace.data("com.ibm.mq.headers.pcf.MQEPH", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/MQEPH.java");
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
/*   79 */   public static final HeaderType TYPE = new HeaderType("MQEPH");
/*      */   
/*   81 */   static final HeaderField StrucId = TYPE.addMQChar("StrucId", "EPH ");
/*   82 */   static final HeaderField Version = TYPE.addMQLong("Version", 1);
/*   83 */   static final HeaderField StrucLength = TYPE.addMQLong("StrucLength", 68);
/*   84 */   static final HeaderField Encoding = TYPE.addMQLong("Encoding");
/*   85 */   static final HeaderField CodedCharSetId = TYPE.addMQLong("CodedCharSetId");
/*   86 */   static final HeaderField Format = TYPE.addMQChar("Format", 8);
/*   87 */   static final HeaderField Flags = TYPE.addMQLong("Flags");
/*   88 */   static final HeaderField PCFHeader = TYPE.addMQHeader("PCFHeader", MQCFH.TYPE, MQCFH.class);
/*      */   
/*   90 */   private final Vector<PCFParameter> parameters = new Vector<>(8, 8);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQEPH() {
/*   96 */     super(TYPE);
/*   97 */     if (Trace.isOn) {
/*   98 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQEPH", "<init>()");
/*      */     }
/*  100 */     if (Trace.isOn) {
/*  101 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQEPH", "<init>()");
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
/*      */   public MQEPH(DataInput message) throws MQDataException, IOException {
/*  114 */     this();
/*  115 */     if (Trace.isOn) {
/*  116 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQEPH", "<init>(DataInput)", new Object[] { message });
/*      */     }
/*      */     
/*  119 */     read((DataInput)MessageWrapper.wrap(message));
/*      */     
/*  121 */     if (Trace.isOn) {
/*  122 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQEPH", "<init>(DataInput)");
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
/*      */   public MQEPH(DataInput message, int encoding, int characterSet) throws MQDataException, IOException {
/*  138 */     this();
/*  139 */     if (Trace.isOn) {
/*  140 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQEPH", "<init>(DataInput,int,int)", new Object[] { message, 
/*  141 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*      */     }
/*  143 */     read((DataInput)MessageWrapper.wrap(message), encoding, characterSet);
/*      */     
/*  145 */     if (Trace.isOn) {
/*  146 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQEPH", "<init>(DataInput,int,int)");
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
/*      */   public int read(DataInput message, int encoding, int characterSet) throws MQDataException, IOException {
/*      */     int size;
/*  164 */     if (Trace.isOn) {
/*  165 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQEPH", "read(DataInput,int,int)", new Object[] { message, 
/*  166 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*      */     }
/*      */     
/*      */     try {
/*  170 */       size = super.read(message, encoding, characterSet);
/*      */     }
/*  172 */     catch (MQDataException mde) {
/*  173 */       if (Trace.isOn) {
/*  174 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.MQEPH", "read(DataInput,int,int)", (Throwable)mde, 1);
/*      */       }
/*      */       
/*  177 */       if (Trace.isOn) {
/*  178 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQEPH", "read(DataInput,int,int)", (Throwable)mde, 1);
/*      */       }
/*  180 */       throw mde;
/*      */     }
/*  182 */     catch (IOException ioe) {
/*  183 */       if (Trace.isOn) {
/*  184 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.MQEPH", "read(DataInput,int,int)", ioe, 2);
/*      */       }
/*  186 */       if (Trace.isOn) {
/*  187 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQEPH", "read(DataInput,int,int)", ioe, 2);
/*      */       }
/*  189 */       throw ioe;
/*      */     }
/*  191 */     catch (Exception e) {
/*  192 */       if (Trace.isOn) {
/*  193 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.MQEPH", "read(DataInput,int,int)", e, 3);
/*      */       }
/*  195 */       RuntimeException traceRet1 = new RuntimeException(e);
/*  196 */       if (Trace.isOn) {
/*  197 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQEPH", "read(DataInput,int,int)", traceRet1, 3);
/*      */       }
/*      */       
/*  200 */       throw traceRet1;
/*      */     } 
/*  202 */     int count = getPCFHeader().getParameterCount();
/*  203 */     PCFHeaderFactory factory = new PCFHeaderFactory();
/*  204 */     MQHeaderContext context = MQHeaderContext.createMQHeaderContext(message, "MQPCF   ", encoding, characterSet);
/*      */ 
/*      */     
/*  207 */     while (count-- > 0) {
/*  208 */       MQHeader header = factory.decode(context);
/*      */       
/*  210 */       addParameter((PCFParameter)header);
/*  211 */       size += header.size();
/*      */     } 
/*      */     
/*  214 */     if (Trace.isOn) {
/*  215 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQEPH", "read(DataInput,int,int)", 
/*  216 */           Integer.valueOf(size));
/*      */     }
/*  218 */     return size;
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
/*      */   public int write(DataOutput message, int encoding, int characterSet) throws IOException {
/*  232 */     if (Trace.isOn) {
/*  233 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQEPH", "write(DataOutput,int,int)", new Object[] { message, 
/*  234 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*      */     }
/*  236 */     int size = super.write(message, encoding, characterSet);
/*      */     
/*  238 */     Enumeration<PCFParameter> parameters = getParameters();
/*      */     
/*  240 */     while (parameters.hasMoreElements()) {
/*  241 */       size += ((PCFParameter)parameters.nextElement()).write(message, encoding, characterSet);
/*      */     }
/*      */     
/*  244 */     if (Trace.isOn) {
/*  245 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQEPH", "write(DataOutput,int,int)", 
/*  246 */           Integer.valueOf(size));
/*      */     }
/*  248 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getStrucId() {
/*  257 */     String traceRet1 = getStringValue(StrucId);
/*  258 */     if (Trace.isOn) {
/*  259 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQEPH", "getStrucId()", "getter", traceRet1);
/*      */     }
/*  261 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getVersion() {
/*  270 */     int traceRet1 = getIntValue(Version);
/*  271 */     if (Trace.isOn) {
/*  272 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQEPH", "getVersion()", "getter", 
/*  273 */           Integer.valueOf(traceRet1));
/*      */     }
/*  275 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getEncoding() {
/*  284 */     int traceRet1 = getIntValue(Encoding);
/*  285 */     if (Trace.isOn) {
/*  286 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQEPH", "getEncoding()", "getter", 
/*  287 */           Integer.valueOf(traceRet1));
/*      */     }
/*  289 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEncoding(int value) {
/*  298 */     if (Trace.isOn) {
/*  299 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQEPH", "setEncoding(int)", "setter", 
/*  300 */           Integer.valueOf(value));
/*      */     }
/*  302 */     setIntValue(Encoding, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCodedCharSetId() {
/*  311 */     int traceRet1 = getIntValue(CodedCharSetId);
/*  312 */     if (Trace.isOn) {
/*  313 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQEPH", "getCodedCharSetId()", "getter", 
/*  314 */           Integer.valueOf(traceRet1));
/*      */     }
/*  316 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCodedCharSetId(int value) {
/*  325 */     if (Trace.isOn) {
/*  326 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQEPH", "setCodedCharSetId(int)", "setter", 
/*  327 */           Integer.valueOf(value));
/*      */     }
/*  329 */     setIntValue(CodedCharSetId, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getFormat() {
/*  338 */     String traceRet1 = getStringValue(Format);
/*  339 */     if (Trace.isOn) {
/*  340 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQEPH", "getFormat()", "getter", traceRet1);
/*      */     }
/*  342 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFormat(String value) {
/*  351 */     if (Trace.isOn) {
/*  352 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQEPH", "setFormat(String)", "setter", value);
/*      */     }
/*  354 */     setStringValue(Format, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getFlags() {
/*  363 */     int traceRet1 = getIntValue(Flags);
/*  364 */     if (Trace.isOn) {
/*  365 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQEPH", "getFlags()", "getter", 
/*  366 */           Integer.valueOf(traceRet1));
/*      */     }
/*  368 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFlags(int value) {
/*  377 */     if (Trace.isOn) {
/*  378 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQEPH", "setFlags(int)", "setter", 
/*  379 */           Integer.valueOf(value));
/*      */     }
/*  381 */     setIntValue(Flags, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQCFH getPCFHeader() {
/*  390 */     MQCFH traceRet1 = (MQCFH)getValue(PCFHeader);
/*  391 */     if (Trace.isOn) {
/*  392 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQEPH", "getPCFHeader()", "getter", traceRet1);
/*      */     }
/*  394 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPCFHeader(MQCFH value) {
/*  403 */     if (Trace.isOn) {
/*  404 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQEPH", "setPCFHeader(MQCFH)", "setter", value);
/*      */     }
/*  406 */     setValue(PCFHeader, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int nextEncoding() {
/*  416 */     if (Trace.isOn) {
/*  417 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQEPH", "nextEncoding()");
/*      */     }
/*  419 */     int traceRet1 = getEncoding();
/*      */     
/*  421 */     if (Trace.isOn) {
/*  422 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQEPH", "nextEncoding()", 
/*  423 */           Integer.valueOf(traceRet1));
/*      */     }
/*  425 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void nextEncoding(int value) {
/*  435 */     if (Trace.isOn)
/*  436 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQEPH", "nextEncoding(int)", new Object[] {
/*  437 */             Integer.valueOf(value)
/*      */           }); 
/*  439 */     setEncoding(value);
/*      */     
/*  441 */     if (Trace.isOn) {
/*  442 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQEPH", "nextEncoding(int)");
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
/*      */   public int nextCharacterSet() {
/*  454 */     if (Trace.isOn) {
/*  455 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQEPH", "nextCharacterSet()");
/*      */     }
/*  457 */     int traceRet1 = getCodedCharSetId();
/*      */     
/*  459 */     if (Trace.isOn) {
/*  460 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQEPH", "nextCharacterSet()", 
/*  461 */           Integer.valueOf(traceRet1));
/*      */     }
/*  463 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void nextCharacterSet(int value) {
/*  473 */     if (Trace.isOn)
/*  474 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQEPH", "nextCharacterSet(int)", new Object[] {
/*  475 */             Integer.valueOf(value)
/*      */           }); 
/*  477 */     setCodedCharSetId(value);
/*      */     
/*  479 */     if (Trace.isOn) {
/*  480 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQEPH", "nextCharacterSet(int)");
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
/*      */   public String nextFormat() {
/*  492 */     if (Trace.isOn) {
/*  493 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQEPH", "nextFormat()");
/*      */     }
/*  495 */     String traceRet1 = getFormat();
/*      */     
/*  497 */     if (Trace.isOn) {
/*  498 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQEPH", "nextFormat()", traceRet1);
/*      */     }
/*  500 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void nextFormat(String value) {
/*  510 */     if (Trace.isOn) {
/*  511 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQEPH", "nextFormat(String)", new Object[] { value });
/*      */     }
/*  513 */     setFormat(value);
/*      */     
/*  515 */     if (Trace.isOn) {
/*  516 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQEPH", "nextFormat(String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String format() {
/*  526 */     if (Trace.isOn) {
/*  527 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQEPH", "format()");
/*      */     }
/*  529 */     if (Trace.isOn) {
/*  530 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQEPH", "format()", "MQHEPCF ");
/*      */     }
/*  532 */     return "MQHEPCF ";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addParameter(PCFParameter parameter) {
/*  540 */     if (Trace.isOn) {
/*  541 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQEPH", "addParameter(PCFParameter)", new Object[] { parameter });
/*      */     }
/*      */     
/*  544 */     synchronized (this.parameters) {
/*  545 */       this.parameters.addElement(parameter);
/*  546 */       getPCFHeader().setParameterCount(this.parameters.size());
/*      */     } 
/*      */     
/*  549 */     if (Trace.isOn) {
/*  550 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQEPH", "addParameter(PCFParameter)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addParameter(int parameter, int value) {
/*  560 */     if (Trace.isOn)
/*  561 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQEPH", "addParameter(int,int)", new Object[] {
/*  562 */             Integer.valueOf(parameter), Integer.valueOf(value)
/*      */           }); 
/*  564 */     addParameter(new MQCFIN(parameter, value));
/*      */     
/*  566 */     if (Trace.isOn) {
/*  567 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQEPH", "addParameter(int,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addParameter(int parameter, int[] values) {
/*  577 */     if (Trace.isOn)
/*  578 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQEPH", "addParameter(int,int [ ])", new Object[] {
/*  579 */             Integer.valueOf(parameter), values
/*      */           }); 
/*  581 */     addParameter(new MQCFIL(parameter, values));
/*      */     
/*  583 */     if (Trace.isOn) {
/*  584 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQEPH", "addParameter(int,int [ ])");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addParameter(int parameter, long value) {
/*  594 */     if (Trace.isOn)
/*  595 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQEPH", "addParameter(int,long)", new Object[] {
/*  596 */             Integer.valueOf(parameter), Long.valueOf(value)
/*      */           }); 
/*  598 */     addParameter(new MQCFIN64(parameter, value));
/*      */     
/*  600 */     if (Trace.isOn) {
/*  601 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQEPH", "addParameter(int,long)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addParameter(int parameter, long[] values) {
/*  611 */     if (Trace.isOn)
/*  612 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQEPH", "addParameter(int,long [ ])", new Object[] {
/*  613 */             Integer.valueOf(parameter), values
/*      */           }); 
/*  615 */     addParameter(new MQCFIL64(parameter, values));
/*      */     
/*  617 */     if (Trace.isOn) {
/*  618 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQEPH", "addParameter(int,long [ ])");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addParameter(int parameter, String value) {
/*  628 */     if (Trace.isOn)
/*  629 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQEPH", "addParameter(int,String)", new Object[] {
/*  630 */             Integer.valueOf(parameter), value
/*      */           }); 
/*  632 */     addParameter(new MQCFST(parameter, value));
/*      */     
/*  634 */     if (Trace.isOn) {
/*  635 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQEPH", "addParameter(int,String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addParameter(int parameter, String[] values) {
/*  645 */     if (Trace.isOn)
/*  646 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQEPH", "addParameter(int,String [ ])", new Object[] {
/*  647 */             Integer.valueOf(parameter), values
/*      */           }); 
/*  649 */     addParameter(new MQCFSL(parameter, values));
/*      */     
/*  651 */     if (Trace.isOn) {
/*  652 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQEPH", "addParameter(int,String [ ])");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addParameter(int parameter, byte[] value) {
/*  662 */     if (Trace.isOn)
/*  663 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQEPH", "addParameter(int,byte [ ])", new Object[] {
/*  664 */             Integer.valueOf(parameter), value
/*      */           }); 
/*  666 */     addParameter(new MQCFBS(parameter, value));
/*      */     
/*  668 */     if (Trace.isOn) {
/*  669 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQEPH", "addParameter(int,byte [ ])");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addFilterParameter(int parameter, int operator, int value) {
/*  679 */     if (Trace.isOn) {
/*  680 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQEPH", "addFilterParameter(int,int,int)", new Object[] {
/*  681 */             Integer.valueOf(parameter), Integer.valueOf(operator), Integer.valueOf(value)
/*      */           });
/*      */     }
/*  684 */     addParameter(new MQCFIF(parameter, operator, value));
/*      */     
/*  686 */     if (Trace.isOn) {
/*  687 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQEPH", "addFilterParameter(int,int,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addFilterParameter(int parameter, int operator, String value) {
/*  697 */     if (Trace.isOn)
/*  698 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQEPH", "addFilterParameter(int,int,String)", new Object[] {
/*  699 */             Integer.valueOf(parameter), Integer.valueOf(operator), value
/*      */           }); 
/*  701 */     addParameter(new MQCFSF(parameter, operator, value));
/*      */     
/*  703 */     if (Trace.isOn) {
/*  704 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQEPH", "addFilterParameter(int,int,String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addFilterParameter(int parameter, int operator, byte[] value) {
/*  714 */     if (Trace.isOn)
/*  715 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQEPH", "addFilterParameter(int,int,byte [ ])", new Object[] {
/*  716 */             Integer.valueOf(parameter), Integer.valueOf(operator), value
/*      */           }); 
/*  718 */     addParameter(new MQCFBF(parameter, operator, value));
/*      */     
/*  720 */     if (Trace.isOn) {
/*  721 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQEPH", "addFilterParameter(int,int,byte [ ])");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getParameterCount() {
/*  731 */     int traceRet1 = this.parameters.size();
/*  732 */     if (Trace.isOn) {
/*  733 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQEPH", "getParameterCount()", "getter", 
/*  734 */           Integer.valueOf(traceRet1));
/*      */     }
/*  736 */     return traceRet1;
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
/*      */   public void setParameterCount(int value) {
/*  748 */     if (Trace.isOn) {
/*  749 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQEPH", "setParameterCount(int)", "setter", 
/*  750 */           Integer.valueOf(value));
/*      */     }
/*  752 */     synchronized (this.parameters) {
/*  753 */       if (value >= 0 && value < this.parameters.size()) {
/*  754 */         this.parameters.setSize(value);
/*  755 */         getPCFHeader().setParameterCount(value);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Enumeration getParameters() {
/*  766 */     Enumeration<PCFParameter> traceRet1 = this.parameters.elements();
/*  767 */     if (Trace.isOn) {
/*  768 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQEPH", "getParameters()", "getter", traceRet1);
/*      */     }
/*  770 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PCFParameter getParameter(int parameter) {
/*  778 */     if (Trace.isOn)
/*  779 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQEPH", "getParameter(int)", new Object[] {
/*  780 */             Integer.valueOf(parameter)
/*      */           }); 
/*  782 */     PCFParameter match = null;
/*      */     
/*  784 */     synchronized (this.parameters) {
/*  785 */       int i = this.parameters.size();
/*      */       
/*  787 */       while (i-- > 0 && match == null) {
/*  788 */         PCFParameter p = this.parameters.elementAt(i);
/*      */         
/*  790 */         if (parameter == p.getParameter()) {
/*  791 */           match = p;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  796 */     if (Trace.isOn) {
/*  797 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQEPH", "getParameter(int)", match);
/*      */     }
/*  799 */     return match;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getParameterValue(int parameter) {
/*  807 */     if (Trace.isOn)
/*  808 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQEPH", "getParameterValue(int)", new Object[] {
/*  809 */             Integer.valueOf(parameter)
/*      */           }); 
/*  811 */     PCFParameter p = getParameter(parameter);
/*      */     
/*  813 */     Object traceRet1 = (p == null) ? null : p.getValue();
/*      */     
/*  815 */     if (Trace.isOn) {
/*  816 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQEPH", "getParameterValue(int)", traceRet1);
/*      */     }
/*  818 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getIntParameterValue(int parameter) throws PCFException {
/*  826 */     if (Trace.isOn)
/*  827 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQEPH", "getIntParameterValue(int)", new Object[] {
/*  828 */             Integer.valueOf(parameter)
/*      */           }); 
/*  830 */     Integer value = (Integer)getParameterValue(parameter);
/*      */     
/*  832 */     if (value == null) {
/*  833 */       PCFException traceRet1 = new PCFException(2, 3014, this);
/*      */ 
/*      */       
/*  836 */       if (Trace.isOn) {
/*  837 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQEPH", "getIntParameterValue(int)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  840 */       throw traceRet1;
/*      */     } 
/*  842 */     int traceRet2 = value.intValue();
/*  843 */     if (Trace.isOn) {
/*  844 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQEPH", "getIntParameterValue(int)", 
/*  845 */           Integer.valueOf(traceRet2));
/*      */     }
/*  847 */     return traceRet2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int[] getIntListParameterValue(int parameter) throws PCFException {
/*  855 */     if (Trace.isOn)
/*  856 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQEPH", "getIntListParameterValue(int)", new Object[] {
/*  857 */             Integer.valueOf(parameter)
/*      */           }); 
/*  859 */     int[] values = (int[])getParameterValue(parameter);
/*      */     
/*  861 */     if (values == null) {
/*  862 */       PCFException traceRet1 = new PCFException(2, 3047, this);
/*      */ 
/*      */       
/*  865 */       if (Trace.isOn) {
/*  866 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQEPH", "getIntListParameterValue(int)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  869 */       throw traceRet1;
/*      */     } 
/*  871 */     if (Trace.isOn) {
/*  872 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQEPH", "getIntListParameterValue(int)", values);
/*      */     }
/*  874 */     return values;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getInt64ParameterValue(int parameter) throws PCFException {
/*  882 */     if (Trace.isOn)
/*  883 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQEPH", "getInt64ParameterValue(int)", new Object[] {
/*  884 */             Integer.valueOf(parameter)
/*      */           }); 
/*  886 */     Long value = (Long)getParameterValue(parameter);
/*      */     
/*  888 */     if (value == null) {
/*  889 */       PCFException traceRet1 = new PCFException(2, 3014, this);
/*      */ 
/*      */       
/*  892 */       if (Trace.isOn) {
/*  893 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQEPH", "getInt64ParameterValue(int)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  896 */       throw traceRet1;
/*      */     } 
/*  898 */     long traceRet2 = value.longValue();
/*  899 */     if (Trace.isOn) {
/*  900 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQEPH", "getInt64ParameterValue(int)", 
/*  901 */           Long.valueOf(traceRet2));
/*      */     }
/*  903 */     return traceRet2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long[] getIntList64ParameterValue(int parameter) throws PCFException {
/*  911 */     if (Trace.isOn)
/*  912 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQEPH", "getIntList64ParameterValue(int)", new Object[] {
/*  913 */             Integer.valueOf(parameter)
/*      */           }); 
/*  915 */     long[] values = (long[])getParameterValue(parameter);
/*      */     
/*  917 */     if (values == null) {
/*  918 */       PCFException traceRet1 = new PCFException(2, 3047, this);
/*      */ 
/*      */       
/*  921 */       if (Trace.isOn) {
/*  922 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQEPH", "getIntList64ParameterValue(int)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  925 */       throw traceRet1;
/*      */     } 
/*  927 */     if (Trace.isOn) {
/*  928 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQEPH", "getIntList64ParameterValue(int)", values);
/*      */     }
/*  930 */     return values;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getStringParameterValue(int parameter) throws PCFException {
/*  938 */     if (Trace.isOn)
/*  939 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQEPH", "getStringParameterValue(int)", new Object[] {
/*  940 */             Integer.valueOf(parameter)
/*      */           }); 
/*  942 */     String value = trimNulls((String)getParameterValue(parameter));
/*      */     
/*  944 */     if (value == null) {
/*  945 */       PCFException traceRet1 = new PCFException(2, 3015, this);
/*      */ 
/*      */       
/*  948 */       if (Trace.isOn) {
/*  949 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQEPH", "getStringParameterValue(int)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  952 */       throw traceRet1;
/*      */     } 
/*  954 */     if (Trace.isOn) {
/*  955 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQEPH", "getStringParameterValue(int)", value);
/*      */     }
/*  957 */     return value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String[] getStringListParameterValue(int parameter) throws PCFException {
/*  965 */     if (Trace.isOn)
/*  966 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQEPH", "getStringListParameterValue(int)", new Object[] {
/*  967 */             Integer.valueOf(parameter)
/*      */           }); 
/*  969 */     String[] values = trimNulls((String[])getParameterValue(parameter));
/*      */     
/*  971 */     if (values == null) {
/*  972 */       PCFException traceRet1 = new PCFException(2, 3033, this);
/*      */ 
/*      */       
/*  975 */       if (Trace.isOn) {
/*  976 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQEPH", "getStringListParameterValue(int)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  979 */       throw traceRet1;
/*      */     } 
/*  981 */     if (Trace.isOn) {
/*  982 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQEPH", "getStringListParameterValue(int)", values);
/*      */     }
/*  984 */     return values;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getBytesParameterValue(int parameter) throws PCFException {
/*  992 */     if (Trace.isOn)
/*  993 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQEPH", "getBytesParameterValue(int)", new Object[] {
/*  994 */             Integer.valueOf(parameter)
/*      */           }); 
/*  996 */     byte[] value = (byte[])getParameterValue(parameter);
/*      */     
/*  998 */     if (value == null) {
/*  999 */       PCFException traceRet1 = new PCFException(2, 3256, this);
/*      */ 
/*      */       
/* 1002 */       if (Trace.isOn) {
/* 1003 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQEPH", "getBytesParameterValue(int)", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 1006 */       throw traceRet1;
/*      */     } 
/* 1008 */     if (Trace.isOn) {
/* 1009 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQEPH", "getBytesParameterValue(int)", value);
/*      */     }
/* 1011 */     return value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean equals(Object obj) {
/* 1020 */     if (Trace.isOn) {
/* 1021 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQEPH", "equals(Object)", new Object[] { obj });
/*      */     }
/* 1023 */     if (obj != null && obj instanceof MQEPH) {
/* 1024 */       MQEPH other = (MQEPH)obj;
/*      */       
/* 1026 */       if (other.getParameterCount() == getParameterCount()) {
/*      */         
/* 1028 */         Enumeration<E> otherParameters = other.getParameters();
/*      */         
/* 1030 */         Enumeration parameters = getParameters();
/* 1031 */         boolean match = true;
/*      */         
/*      */         try {
/* 1034 */           while (match && parameters.hasMoreElements()) {
/* 1035 */             match = otherParameters.nextElement().equals(parameters.nextElement());
/*      */           
/*      */           }
/*      */         }
/* 1039 */         catch (NoSuchElementException nsee) {
/* 1040 */           if (Trace.isOn) {
/* 1041 */             Trace.catchBlock(this, "com.ibm.mq.headers.pcf.MQEPH", "equals(Object)", nsee);
/*      */           }
/* 1043 */           if (Trace.isOn) {
/* 1044 */             Trace.exit(this, "com.ibm.mq.headers.pcf.MQEPH", "equals(Object)", 
/* 1045 */                 Boolean.valueOf(false), 1);
/*      */           }
/* 1047 */           return false;
/*      */         } 
/*      */         
/* 1050 */         if (Trace.isOn) {
/* 1051 */           Trace.exit(this, "com.ibm.mq.headers.pcf.MQEPH", "equals(Object)", 
/* 1052 */               Boolean.valueOf(match), 2);
/*      */         }
/* 1054 */         return match;
/*      */       } 
/* 1056 */       if (Trace.isOn) {
/* 1057 */         Trace.exit(this, "com.ibm.mq.headers.pcf.MQEPH", "equals(Object)", Boolean.valueOf(false), 3);
/*      */       }
/*      */       
/* 1060 */       return false;
/*      */     } 
/* 1062 */     if (Trace.isOn) {
/* 1063 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQEPH", "equals(Object)", Boolean.valueOf(false), 4);
/*      */     }
/* 1065 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/* 1073 */     if (Trace.isOn) {
/* 1074 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQEPH", "toString()");
/*      */     }
/* 1076 */     StringBuffer sb = new StringBuffer(super.toString());
/*      */     
/* 1078 */     sb.append(" {");
/*      */     
/* 1080 */     for (int i = 0; i < this.parameters.size(); i++) {
/* 1081 */       PCFParameter p = this.parameters.elementAt(i);
/* 1082 */       sb.append('\n');
/* 1083 */       sb.append(p.toString());
/*      */     } 
/*      */     
/* 1086 */     sb.append("}");
/*      */     
/* 1088 */     String traceRet1 = new String(sb);
/* 1089 */     if (Trace.isOn) {
/* 1090 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQEPH", "toString()", traceRet1);
/*      */     }
/* 1092 */     return traceRet1;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\pcf\MQEPH.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */