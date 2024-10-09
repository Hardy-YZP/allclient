/*      */ package com.ibm.mq.headers.pcf;
/*      */ 
/*      */ import com.ibm.mq.headers.MQDataException;
/*      */ import com.ibm.mq.headers.MQHeader;
/*      */ import com.ibm.mq.headers.MQHeaderContext;
/*      */ import com.ibm.mq.headers.internal.HeaderField;
/*      */ import com.ibm.mq.headers.internal.HeaderType;
/*      */ import com.ibm.mq.headers.internal.MessageWrapper;
/*      */ import com.ibm.mq.headers.internal.store.Store;
/*      */ import com.ibm.mq.pcf.PCFParameter;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.io.DataInput;
/*      */ import java.io.DataOutput;
/*      */ import java.io.IOException;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Iterator;
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
/*      */ public class MQCFGR
/*      */   extends PCFParameter
/*      */   implements PCFContent
/*      */ {
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/MQCFGR.java";
/*      */   
/*      */   static {
/*   65 */     if (Trace.isOn) {
/*   66 */       Trace.data("com.ibm.mq.headers.pcf.MQCFGR", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/MQCFGR.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   74 */   static final HeaderType TYPE = new HeaderType("MQCFGR");
/*      */   
/*      */   private static final int type = 20;
/*      */   
/*      */   private static final int strucLength = 16;
/*      */   
/*   80 */   private static final HeaderField Type = TYPE.addMQLong("Type", 20);
/*   81 */   private static final HeaderField StrucLength = TYPE.addMQLong("StrucLength", 16);
/*   82 */   private static final HeaderField Parameter = TYPE.addMQLong("Parameter");
/*   83 */   private static final HeaderField ParameterCount = TYPE.addMQLong("ParameterCount");
/*      */   
/*      */   private static final int HEADER_VERSION = 3;
/*      */   
/*   87 */   private final Vector parameters = new Vector(8, 8);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int write(Object message, int parameter, int parameterCount) throws IOException {
/*  101 */     if (Trace.isOn) {
/*  102 */       Trace.entry("com.ibm.mq.headers.pcf.MQCFGR", "write(Object,int,int)", new Object[] { message, 
/*  103 */             Integer.valueOf(parameter), Integer.valueOf(parameterCount) });
/*      */     }
/*  105 */     MessageWrapper wrappedMessage = MessageWrapper.wrap((DataOutput)message);
/*  106 */     wrappedMessage.writeInt(20);
/*  107 */     wrappedMessage.writeInt(16);
/*  108 */     wrappedMessage.writeInt(parameter);
/*  109 */     wrappedMessage.writeInt(parameterCount);
/*      */     
/*  111 */     if (Trace.isOn) {
/*  112 */       Trace.exit("com.ibm.mq.headers.pcf.MQCFGR", "write(Object,int,int)", 
/*  113 */           Integer.valueOf(16));
/*      */     }
/*  115 */     return 16;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQCFGR() {
/*  122 */     super(TYPE);
/*  123 */     if (Trace.isOn) {
/*  124 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFGR", "<init>()");
/*      */     }
/*  126 */     if (Trace.isOn) {
/*  127 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFGR", "<init>()");
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
/*      */   public MQCFGR(DataInput message) throws MQDataException, IOException {
/*  140 */     this();
/*  141 */     if (Trace.isOn) {
/*  142 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFGR", "<init>(DataInput)", new Object[] { message });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  148 */     read((DataInput)MessageWrapper.wrap(message));
/*      */     
/*  150 */     if (Trace.isOn) {
/*  151 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFGR", "<init>(DataInput)");
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
/*      */   public MQCFGR(DataInput message, int encoding, int characterSet) throws MQDataException, IOException {
/*  166 */     this();
/*  167 */     if (Trace.isOn) {
/*  168 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFGR", "<init>(DataInput,int,int)", new Object[] { message, 
/*  169 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*      */     }
/*  171 */     read((DataInput)MessageWrapper.wrap(message), encoding, characterSet);
/*      */     
/*  173 */     if (Trace.isOn) {
/*  174 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFGR", "<init>(DataInput,int,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int read(DataInput message, int encoding, int characterSet) throws MQDataException, IOException {
/*      */     int size;
/*  184 */     if (Trace.isOn) {
/*  185 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFGR", "read(DataInput,int,int)", new Object[] { message, 
/*  186 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  191 */       size = super.read(message, encoding, characterSet);
/*      */     }
/*  193 */     catch (MQDataException mde) {
/*  194 */       if (Trace.isOn) {
/*  195 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.MQCFGR", "read(DataInput,int,int)", (Throwable)mde, 1);
/*      */       }
/*      */       
/*  198 */       if (Trace.isOn) {
/*  199 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQCFGR", "read(DataInput,int,int)", (Throwable)mde, 1);
/*      */       }
/*  201 */       throw mde;
/*      */     }
/*  203 */     catch (IOException ioe) {
/*  204 */       if (Trace.isOn) {
/*  205 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.MQCFGR", "read(DataInput,int,int)", ioe, 2);
/*      */       }
/*  207 */       if (Trace.isOn) {
/*  208 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQCFGR", "read(DataInput,int,int)", ioe, 2);
/*      */       }
/*  210 */       throw ioe;
/*      */     }
/*  212 */     catch (Exception e) {
/*  213 */       if (Trace.isOn) {
/*  214 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.MQCFGR", "read(DataInput,int,int)", e, 3);
/*      */       }
/*  216 */       RuntimeException traceRet1 = new RuntimeException(e);
/*  217 */       if (Trace.isOn) {
/*  218 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQCFGR", "read(DataInput,int,int)", traceRet1, 3);
/*      */       }
/*  220 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/*  224 */     this.parameters.clear();
/*      */     
/*  226 */     int count = getParameterCount();
/*  227 */     PCFHeaderFactory factory = new PCFHeaderFactory();
/*  228 */     MQHeaderContext context = MQHeaderContext.createMQHeaderContext(message, "MQPCF   ", encoding, characterSet);
/*      */     
/*  230 */     while (count-- > 0) {
/*      */       
/*  232 */       MQHeader header = factory.decode(context);
/*  233 */       addParameter((PCFParameter)header);
/*      */     } 
/*  235 */     if (Trace.isOn) {
/*  236 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFGR", "read(DataInput,int,int)", 
/*  237 */           Integer.valueOf(size));
/*      */     }
/*  239 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int write(DataOutput messageP, int encoding, int characterSet) throws IOException {
/*  247 */     if (Trace.isOn) {
/*  248 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFGR", "write(DataOutput,int,int)", new Object[] { messageP, 
/*  249 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*      */     }
/*      */     
/*  252 */     DataOutput message = messageP;
/*      */     
/*  254 */     int size = 0;
/*      */     
/*  256 */     Store localStore = store();
/*      */     
/*  258 */     if (!(localStore instanceof com.ibm.mq.headers.internal.DefaultStore)) {
/*      */ 
/*      */       
/*  261 */       size = super.write(messageP, encoding, characterSet);
/*      */ 
/*      */       
/*  264 */       Enumeration<PCFParameter> parametersEnumeration = getParameters();
/*  265 */       while (parametersEnumeration.hasMoreElements()) {
/*  266 */         PCFParameter parameter = parametersEnumeration.nextElement();
/*  267 */         size += parameter.write(messageP, encoding, characterSet);
/*      */       
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  274 */       MessageWrapper wrappedMessage = MessageWrapper.wrap(message);
/*  275 */       if (!store().matchesEncoding(encoding)) {
/*  276 */         message = wrappedMessage.getReversed();
/*      */       }
/*  278 */       Iterator<?> it = TYPE.getFields().iterator();
/*      */       
/*  280 */       while (it.hasNext()) {
/*  281 */         HeaderField field = (HeaderField)it.next();
/*      */         
/*  283 */         if (field.isPresent(this)) {
/*  284 */           size += field.write(this, message, encoding, characterSet);
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  289 */     if (Trace.isOn) {
/*  290 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFGR", "write(DataOutput,int,int)", Integer.valueOf(size));
/*      */     }
/*      */     
/*  293 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getType() {
/*  303 */     int traceRet1 = getIntValue(Type);
/*  304 */     if (Trace.isOn) {
/*  305 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFGR", "getType()", "getter", 
/*  306 */           Integer.valueOf(traceRet1));
/*      */     }
/*  308 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getStrucLength() {
/*  317 */     int traceRet1 = getIntValue(StrucLength);
/*  318 */     if (Trace.isOn) {
/*  319 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFGR", "getStrucLength()", "getter", 
/*  320 */           Integer.valueOf(traceRet1));
/*      */     }
/*  322 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getParameter() {
/*  332 */     int traceRet1 = getIntValue(Parameter);
/*  333 */     if (Trace.isOn) {
/*  334 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFGR", "getParameter()", "getter", 
/*  335 */           Integer.valueOf(traceRet1));
/*      */     }
/*  337 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setParameter(int value) {
/*  346 */     if (Trace.isOn) {
/*  347 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFGR", "setParameter(int)", "setter", 
/*  348 */           Integer.valueOf(value));
/*      */     }
/*  350 */     setIntValue(Parameter, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getParameterCount() {
/*  360 */     int traceRet1 = getIntValue(ParameterCount);
/*  361 */     if (Trace.isOn) {
/*  362 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFGR", "getParameterCount()", "getter", 
/*  363 */           Integer.valueOf(traceRet1));
/*      */     }
/*  365 */     return traceRet1;
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
/*      */   public int setParameterCount(int value) {
/*  378 */     if (Trace.isOn)
/*  379 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFGR", "setParameterCount(int)", new Object[] {
/*  380 */             Integer.valueOf(value)
/*      */           }); 
/*  382 */     synchronized (this.parameters) {
/*  383 */       if (getParameterCount() >= 0 && getParameterCount() < this.parameters.size()) {
/*  384 */         setIntValue(ParameterCount, value);
/*      */         
/*  386 */         this.parameters.setSize(value);
/*      */       } 
/*      */     } 
/*  389 */     int traceRet1 = this.parameters.size();
/*      */     
/*  391 */     if (Trace.isOn) {
/*  392 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFGR", "setParameterCount(int)", 
/*  393 */           Integer.valueOf(traceRet1));
/*      */     }
/*  395 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getValue() {
/*  404 */     Object traceRet1 = Integer.valueOf(getParameterCount());
/*  405 */     if (Trace.isOn) {
/*  406 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFGR", "getValue()", "getter", traceRet1);
/*      */     }
/*  408 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getStringValue() {
/*  417 */     String traceRet1 = Integer.toString(getParameterCount());
/*  418 */     if (Trace.isOn) {
/*  419 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFGR", "getStringValue()", "getter", traceRet1);
/*      */     }
/*  421 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setValue(Object value) {
/*  430 */     if (Trace.isOn) {
/*  431 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFGR", "setValue(Object)", "setter", value);
/*      */     }
/*  433 */     synchronized (this.parameters) {
/*  434 */       if (value == null) {
/*  435 */         setParameterCount(0);
/*  436 */         this.parameters.clear();
/*      */       }
/*  438 */       else if (value instanceof PCFParameter[]) {
/*      */         
/*  440 */         PCFParameter[] parameters = (PCFParameter[])value;
/*      */         
/*  442 */         for (int i = 0; i < parameters.length; i++) {
/*  443 */           if (parameters[i] != null) {
/*  444 */             addParameter(parameters[i]);
/*      */           }
/*      */         }
/*      */       
/*  448 */       } else if (value instanceof Enumeration) {
/*  449 */         Enumeration<PCFParameter> e = (Enumeration)value;
/*      */         
/*  451 */         while (e.hasMoreElements()) {
/*  452 */           PCFParameter p = e.nextElement();
/*      */           
/*  454 */           if (p != null) {
/*  455 */             addParameter(e.nextElement());
/*      */           }
/*      */         } 
/*      */       } else {
/*      */         
/*  460 */         IllegalArgumentException traceRet1 = new IllegalArgumentException("Value must be PCFParameter [] or Enumeration of PCFParameter");
/*      */         
/*  462 */         if (Trace.isOn) {
/*  463 */           Trace.throwing(this, "com.ibm.mq.headers.pcf.MQCFGR", "setValue(Object)", traceRet1);
/*      */         }
/*  465 */         throw traceRet1;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addParameter(PCFParameter parameter) {
/*  476 */     if (Trace.isOn) {
/*  477 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFGR", "addParameter(PCFParameter)", new Object[] { parameter });
/*      */     }
/*      */     
/*  480 */     synchronized (this.parameters) {
/*  481 */       this.parameters.addElement(parameter);
/*  482 */       setParameterCount(getParameterCount() + 1);
/*      */     } 
/*      */     
/*  485 */     if (Trace.isOn) {
/*  486 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFGR", "addParameter(PCFParameter)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addParameter(PCFParameter parameter) {
/*  496 */     if (Trace.isOn) {
/*  497 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFGR", "addParameter(com.ibm.mq.pcf.PCFParameter)", new Object[] { parameter });
/*      */     }
/*      */     
/*  500 */     synchronized (this.parameters) {
/*  501 */       this.parameters.addElement(parameter);
/*  502 */       setParameterCount(getParameterCount() + 1);
/*      */     } 
/*      */     
/*  505 */     if (Trace.isOn) {
/*  506 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFGR", "addParameter(com.ibm.mq.pcf.PCFParameter)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addParameter(int parameter, int value) {
/*  517 */     if (Trace.isOn)
/*  518 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFGR", "addParameter(int,int)", new Object[] {
/*  519 */             Integer.valueOf(parameter), Integer.valueOf(value)
/*      */           }); 
/*  521 */     addParameter(new MQCFIN(parameter, value));
/*      */     
/*  523 */     if (Trace.isOn) {
/*  524 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFGR", "addParameter(int,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addParameter(int parameter, int[] values) {
/*  534 */     if (Trace.isOn)
/*  535 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFGR", "addParameter(int,int [ ])", new Object[] {
/*  536 */             Integer.valueOf(parameter), values
/*      */           }); 
/*  538 */     addParameter(new MQCFIL(parameter, values));
/*      */     
/*  540 */     if (Trace.isOn) {
/*  541 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFGR", "addParameter(int,int [ ])");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addParameter(int parameter, long value) {
/*  551 */     if (Trace.isOn)
/*  552 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFGR", "addParameter(int,long)", new Object[] {
/*  553 */             Integer.valueOf(parameter), Long.valueOf(value)
/*      */           }); 
/*  555 */     addParameter(new MQCFIN64(parameter, value));
/*      */     
/*  557 */     if (Trace.isOn) {
/*  558 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFGR", "addParameter(int,long)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addParameter(int parameter, long[] values) {
/*  568 */     if (Trace.isOn)
/*  569 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFGR", "addParameter(int,long [ ])", new Object[] {
/*  570 */             Integer.valueOf(parameter), values
/*      */           }); 
/*  572 */     addParameter(new MQCFIL64(parameter, values));
/*      */     
/*  574 */     if (Trace.isOn) {
/*  575 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFGR", "addParameter(int,long [ ])");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addParameter(int parameter, String value) {
/*  585 */     if (Trace.isOn)
/*  586 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFGR", "addParameter(int,String)", new Object[] {
/*  587 */             Integer.valueOf(parameter), value
/*      */           }); 
/*  589 */     addParameter(new MQCFST(parameter, value));
/*      */     
/*  591 */     if (Trace.isOn) {
/*  592 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFGR", "addParameter(int,String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addParameter(int parameter, String[] values) {
/*  602 */     if (Trace.isOn)
/*  603 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFGR", "addParameter(int,String [ ])", new Object[] {
/*  604 */             Integer.valueOf(parameter), values
/*      */           }); 
/*  606 */     addParameter(new MQCFSL(parameter, values));
/*      */     
/*  608 */     if (Trace.isOn) {
/*  609 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFGR", "addParameter(int,String [ ])");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addParameter(int parameter, byte[] value) {
/*  619 */     if (Trace.isOn)
/*  620 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFGR", "addParameter(int,byte [ ])", new Object[] {
/*  621 */             Integer.valueOf(parameter), value
/*      */           }); 
/*  623 */     addParameter(new MQCFBS(parameter, value));
/*      */     
/*  625 */     if (Trace.isOn) {
/*  626 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFGR", "addParameter(int,byte [ ])");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addFilterParameter(int parameter, int operator, int value) {
/*  636 */     if (Trace.isOn) {
/*  637 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFGR", "addFilterParameter(int,int,int)", new Object[] {
/*  638 */             Integer.valueOf(parameter), Integer.valueOf(operator), Integer.valueOf(value)
/*      */           });
/*      */     }
/*  641 */     addParameter(new MQCFIF(parameter, operator, value));
/*      */     
/*  643 */     if (Trace.isOn) {
/*  644 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFGR", "addFilterParameter(int,int,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addFilterParameter(int parameter, int operator, String value) {
/*  654 */     if (Trace.isOn)
/*  655 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFGR", "addFilterParameter(int,int,String)", new Object[] {
/*  656 */             Integer.valueOf(parameter), Integer.valueOf(operator), value
/*      */           }); 
/*  658 */     addParameter(new MQCFSF(parameter, operator, value));
/*      */     
/*  660 */     if (Trace.isOn) {
/*  661 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFGR", "addFilterParameter(int,int,String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addFilterParameter(int parameter, int operator, byte[] value) {
/*  671 */     if (Trace.isOn)
/*  672 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFGR", "addFilterParameter(int,int,byte [ ])", new Object[] {
/*  673 */             Integer.valueOf(parameter), Integer.valueOf(operator), value
/*      */           }); 
/*  675 */     addParameter(new MQCFBF(parameter, operator, value));
/*      */     
/*  677 */     if (Trace.isOn) {
/*  678 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFGR", "addFilterParameter(int,int,byte [ ])");
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
/*      */   public Enumeration getParameters() {
/*  690 */     Enumeration traceRet1 = this.parameters.elements();
/*  691 */     if (Trace.isOn) {
/*  692 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFGR", "getParameters()", "getter", traceRet1);
/*      */     }
/*  694 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Vector getParameterVector() {
/*  703 */     if (Trace.isOn) {
/*  704 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFGR", "getParameterVector()", "getter", this.parameters);
/*      */     }
/*      */     
/*  707 */     return this.parameters;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PCFParameter getParameter(int parameter) {
/*  715 */     if (Trace.isOn)
/*  716 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFGR", "getParameter(int)", new Object[] {
/*  717 */             Integer.valueOf(parameter)
/*      */           }); 
/*  719 */     PCFParameter match = null;
/*      */     
/*  721 */     synchronized (this.parameters) {
/*  722 */       int i = this.parameters.size();
/*      */       
/*  724 */       while (i-- > 0 && match == null) {
/*  725 */         PCFParameter p = this.parameters.elementAt(i);
/*      */         
/*  727 */         if (parameter == p.getParameter()) {
/*  728 */           match = p;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  733 */     if (Trace.isOn) {
/*  734 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFGR", "getParameter(int)", match);
/*      */     }
/*  736 */     return match;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getParameterValue(int parameter) {
/*  744 */     if (Trace.isOn)
/*  745 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFGR", "getParameterValue(int)", new Object[] {
/*  746 */             Integer.valueOf(parameter)
/*      */           }); 
/*  748 */     PCFParameter p = getParameter(parameter);
/*      */     
/*  750 */     Object traceRet1 = (p == null) ? null : p.getValue();
/*      */     
/*  752 */     if (Trace.isOn) {
/*  753 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFGR", "getParameterValue(int)", traceRet1);
/*      */     }
/*  755 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getIntParameterValue(int parameter) throws PCFException {
/*  763 */     if (Trace.isOn)
/*  764 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFGR", "getIntParameterValue(int)", new Object[] {
/*  765 */             Integer.valueOf(parameter)
/*      */           }); 
/*  767 */     Integer value = (Integer)getParameterValue(parameter);
/*      */     
/*  769 */     if (value == null) {
/*  770 */       PCFException traceRet1 = new PCFException(2, 3014, this);
/*      */       
/*  772 */       if (Trace.isOn) {
/*  773 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQCFGR", "getIntParameterValue(int)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  776 */       throw traceRet1;
/*      */     } 
/*      */     
/*  779 */     int traceRet2 = value.intValue();
/*      */     
/*  781 */     if (Trace.isOn) {
/*  782 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFGR", "getIntParameterValue(int)", 
/*  783 */           Integer.valueOf(traceRet2));
/*      */     }
/*  785 */     return traceRet2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int[] getIntListParameterValue(int parameter) throws PCFException {
/*  793 */     if (Trace.isOn)
/*  794 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFGR", "getIntListParameterValue(int)", new Object[] {
/*  795 */             Integer.valueOf(parameter)
/*      */           }); 
/*  797 */     int[] values = (int[])getParameterValue(parameter);
/*      */     
/*  799 */     if (values == null) {
/*  800 */       PCFException traceRet1 = new PCFException(2, 3047, this);
/*      */       
/*  802 */       if (Trace.isOn) {
/*  803 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQCFGR", "getIntListParameterValue(int)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  806 */       throw traceRet1;
/*      */     } 
/*      */     
/*  809 */     if (Trace.isOn) {
/*  810 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFGR", "getIntListParameterValue(int)", values);
/*      */     }
/*  812 */     return values;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getInt64ParameterValue(int parameter) throws PCFException {
/*  820 */     if (Trace.isOn)
/*  821 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFGR", "getInt64ParameterValue(int)", new Object[] {
/*  822 */             Integer.valueOf(parameter)
/*      */           }); 
/*  824 */     Long value = (Long)getParameterValue(parameter);
/*      */     
/*  826 */     if (value == null) {
/*  827 */       PCFException traceRet1 = new PCFException(2, 3014, this);
/*      */       
/*  829 */       if (Trace.isOn) {
/*  830 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQCFGR", "getInt64ParameterValue(int)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  833 */       throw traceRet1;
/*      */     } 
/*      */     
/*  836 */     long traceRet2 = value.longValue();
/*      */     
/*  838 */     if (Trace.isOn) {
/*  839 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFGR", "getInt64ParameterValue(int)", 
/*  840 */           Long.valueOf(traceRet2));
/*      */     }
/*  842 */     return traceRet2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long[] getIntList64ParameterValue(int parameter) throws PCFException {
/*  850 */     if (Trace.isOn)
/*  851 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFGR", "getIntList64ParameterValue(int)", new Object[] {
/*  852 */             Integer.valueOf(parameter)
/*      */           }); 
/*  854 */     long[] values = (long[])getParameterValue(parameter);
/*      */     
/*  856 */     if (values == null) {
/*  857 */       PCFException traceRet1 = new PCFException(2, 3047, this);
/*      */       
/*  859 */       if (Trace.isOn) {
/*  860 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQCFGR", "getIntList64ParameterValue(int)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  863 */       throw traceRet1;
/*      */     } 
/*      */     
/*  866 */     if (Trace.isOn) {
/*  867 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFGR", "getIntList64ParameterValue(int)", values);
/*      */     }
/*  869 */     return values;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getStringParameterValue(int parameter) throws PCFException {
/*  877 */     if (Trace.isOn)
/*  878 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFGR", "getStringParameterValue(int)", new Object[] {
/*  879 */             Integer.valueOf(parameter)
/*      */           }); 
/*  881 */     String value = trimNulls((String)getParameterValue(parameter));
/*      */     
/*  883 */     if (value == null) {
/*  884 */       PCFException traceRet1 = new PCFException(2, 3015, this);
/*      */       
/*  886 */       if (Trace.isOn) {
/*  887 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQCFGR", "getStringParameterValue(int)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  890 */       throw traceRet1;
/*      */     } 
/*      */     
/*  893 */     if (Trace.isOn) {
/*  894 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFGR", "getStringParameterValue(int)", value);
/*      */     }
/*  896 */     return value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String[] getStringListParameterValue(int parameter) throws PCFException {
/*  904 */     if (Trace.isOn)
/*  905 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFGR", "getStringListParameterValue(int)", new Object[] {
/*  906 */             Integer.valueOf(parameter)
/*      */           }); 
/*  908 */     String[] values = trimNulls((String[])getParameterValue(parameter));
/*      */     
/*  910 */     if (values == null) {
/*  911 */       PCFException traceRet1 = new PCFException(2, 3033, this);
/*      */       
/*  913 */       if (Trace.isOn) {
/*  914 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQCFGR", "getStringListParameterValue(int)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  917 */       throw traceRet1;
/*      */     } 
/*      */     
/*  920 */     if (Trace.isOn) {
/*  921 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFGR", "getStringListParameterValue(int)", values);
/*      */     }
/*  923 */     return values;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getBytesParameterValue(int parameter) throws PCFException {
/*  931 */     if (Trace.isOn)
/*  932 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFGR", "getBytesParameterValue(int)", new Object[] {
/*  933 */             Integer.valueOf(parameter)
/*      */           }); 
/*  935 */     byte[] value = (byte[])getParameterValue(parameter);
/*      */     
/*  937 */     if (value == null) {
/*  938 */       PCFException traceRet1 = new PCFException(2, 3256, this);
/*      */       
/*  940 */       if (Trace.isOn) {
/*  941 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQCFGR", "getBytesParameterValue(int)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  944 */       throw traceRet1;
/*      */     } 
/*      */     
/*  947 */     if (Trace.isOn) {
/*  948 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFGR", "getBytesParameterValue(int)", value);
/*      */     }
/*  950 */     return value;
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
/*      */   public boolean equals(Object obj) {
/*  963 */     if (Trace.isOn) {
/*  964 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFGR", "equals(Object)", new Object[] { obj });
/*      */     }
/*  966 */     if (obj != null && obj instanceof MQCFGR) {
/*  967 */       MQCFGR other = (MQCFGR)obj;
/*      */       
/*  969 */       if (other.getParameter() == getParameter() && other.getParameterCount() == getParameterCount()) {
/*  970 */         Enumeration<E> otherParameters = other.getParameters();
/*      */         
/*  972 */         Enumeration parameters = getParameters();
/*  973 */         boolean match = true;
/*      */         
/*      */         try {
/*  976 */           while (match && parameters.hasMoreElements()) {
/*  977 */             match = otherParameters.nextElement().equals(parameters.nextElement());
/*      */           
/*      */           }
/*      */         }
/*  981 */         catch (NoSuchElementException nsee) {
/*  982 */           if (Trace.isOn) {
/*  983 */             Trace.catchBlock(this, "com.ibm.mq.headers.pcf.MQCFGR", "equals(Object)", nsee);
/*      */           }
/*  985 */           if (Trace.isOn) {
/*  986 */             Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFGR", "equals(Object)", 
/*  987 */                 Boolean.valueOf(false), 1);
/*      */           }
/*  989 */           return false;
/*      */         } 
/*      */         
/*  992 */         if (Trace.isOn) {
/*  993 */           Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFGR", "equals(Object)", 
/*  994 */               Boolean.valueOf(match), 2);
/*      */         }
/*  996 */         return match;
/*      */       } 
/*      */     } 
/*  999 */     if (Trace.isOn) {
/* 1000 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFGR", "equals(Object)", Boolean.valueOf(false), 3);
/*      */     }
/*      */     
/* 1003 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int hashCode() {
/* 1011 */     if (Trace.isOn) {
/* 1012 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFGR", "hashCode()");
/*      */     }
/* 1014 */     int hashCode = 0;
/* 1015 */     hashCode += getParameter();
/*      */     
/* 1017 */     Enumeration<E> parameters = getParameters();
/* 1018 */     while (parameters.hasMoreElements()) {
/* 1019 */       hashCode += 31 * parameters.nextElement().hashCode();
/*      */     }
/*      */     
/* 1022 */     if (Trace.isOn) {
/* 1023 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFGR", "hashCode()", Integer.valueOf(hashCode));
/*      */     }
/* 1025 */     return hashCode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getHeaderVersion() {
/* 1033 */     if (Trace.isOn) {
/* 1034 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFGR", "getHeaderVersion()", "getter", 
/* 1035 */           Integer.valueOf(3));
/*      */     }
/* 1037 */     return 3;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/* 1045 */     if (Trace.isOn) {
/* 1046 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFGR", "toString()");
/*      */     }
/* 1048 */     StringBuffer sb = new StringBuffer(super.toString());
/*      */     
/* 1050 */     sb.append(" {");
/*      */     
/* 1052 */     if (this.parameters != null) {
/* 1053 */       for (int i = 0; i < this.parameters.size(); i++) {
/* 1054 */         Object p = this.parameters.elementAt(i);
/* 1055 */         sb.append('\n');
/* 1056 */         sb.append(p.getClass().getName() + ":");
/* 1057 */         sb.append(p.toString());
/*      */       } 
/*      */     }
/*      */     
/* 1061 */     sb.append("}");
/*      */     
/* 1063 */     String traceRet1 = new String(sb);
/* 1064 */     if (Trace.isOn) {
/* 1065 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFGR", "toString()", traceRet1);
/*      */     }
/* 1067 */     return traceRet1;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\pcf\MQCFGR.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */