/*      */ package com.ibm.mq.pcf;
/*      */ 
/*      */ import com.ibm.mq.MQException;
/*      */ import com.ibm.mq.MQMessage;
/*      */ import com.ibm.mq.headers.internal.HeaderType;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
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
/*      */ @Deprecated
/*      */ public class PCFMessage
/*      */   extends PCFHeader
/*      */   implements PCFContent
/*      */ {
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/pcf/PCFMessage.java";
/*      */   MQCFH header;
/*      */   
/*      */   static {
/*   55 */     if (Trace.isOn) {
/*   56 */       Trace.data("com.ibm.mq.pcf.PCFMessage", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/pcf/PCFMessage.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   65 */   Vector parameters = null;
/*      */ 
/*      */   
/*      */   private final com.ibm.mq.headers.pcf.PCFMessage myDelegate;
/*      */ 
/*      */   
/*      */   private PCFMessage() {
/*   72 */     super(new HeaderType("PCFMessage"));
/*   73 */     if (Trace.isOn) {
/*   74 */       Trace.entry(this, "com.ibm.mq.pcf.PCFMessage", "<init>()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*   79 */     this.myDelegate = (com.ibm.mq.headers.pcf.PCFMessage)this.delegate;
/*   80 */     this.parameters = this.myDelegate.getParameterVector();
/*      */     
/*   82 */     if (Trace.isOn) {
/*   83 */       Trace.exit(this, "com.ibm.mq.pcf.PCFMessage", "<init>()");
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
/*      */   public PCFMessage(int command) {
/*   96 */     this(1, command, 1, true);
/*   97 */     if (Trace.isOn)
/*   98 */       Trace.entry(this, "com.ibm.mq.pcf.PCFMessage", "<init>(int)", new Object[] {
/*   99 */             Integer.valueOf(command)
/*      */           }); 
/*  101 */     if (Trace.isOn) {
/*  102 */       Trace.exit(this, "com.ibm.mq.pcf.PCFMessage", "<init>(int)");
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
/*      */   public PCFMessage(int type, int command, int msgSeqNumber, boolean last) {
/*  122 */     this();
/*  123 */     if (Trace.isOn)
/*  124 */       Trace.entry(this, "com.ibm.mq.pcf.PCFMessage", "<init>(int,int,int,boolean)", new Object[] {
/*  125 */             Integer.valueOf(type), Integer.valueOf(command), Integer.valueOf(msgSeqNumber), 
/*  126 */             Boolean.valueOf(last)
/*      */           }); 
/*  128 */     this.header = new MQCFH(command, 0);
/*  129 */     this.myDelegate.setHeader(this.header.delegate);
/*      */     
/*  131 */     this.header.setType(this.header.type = type);
/*  132 */     this.header.setMsgSeqNumber(this.header.msgSeqNumber = msgSeqNumber);
/*  133 */     this.header.setControl(this.header.control = last ? 1 : 0);
/*      */     
/*  135 */     if (Trace.isOn) {
/*  136 */       Trace.exit(this, "com.ibm.mq.pcf.PCFMessage", "<init>(int,int,int,boolean)");
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
/*      */   public PCFMessage(MQMessage message) throws MQException, IOException {
/*  151 */     this();
/*  152 */     if (Trace.isOn) {
/*  153 */       Trace.entry(this, "com.ibm.mq.pcf.PCFMessage", "<init>(MQMessage)", new Object[] { message });
/*      */     }
/*  155 */     initialize(message);
/*      */     
/*  157 */     if (Trace.isOn) {
/*  158 */       Trace.exit(this, "com.ibm.mq.pcf.PCFMessage", "<init>(MQMessage)");
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
/*      */   public void initialize(int command) {
/*  170 */     if (Trace.isOn)
/*  171 */       Trace.entry(this, "com.ibm.mq.pcf.PCFMessage", "initialize(int)", new Object[] {
/*  172 */             Integer.valueOf(command)
/*      */           }); 
/*  174 */     initialize(1, command, 1, true);
/*      */     
/*  176 */     if (Trace.isOn) {
/*  177 */       Trace.exit(this, "com.ibm.mq.pcf.PCFMessage", "initialize(int)");
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
/*      */   public void initialize(int type, int command, int msgSeqNumber, boolean last) {
/*  197 */     if (Trace.isOn)
/*  198 */       Trace.entry(this, "com.ibm.mq.pcf.PCFMessage", "initialize(int,int,int,boolean)", new Object[] {
/*  199 */             Integer.valueOf(type), Integer.valueOf(command), 
/*  200 */             Integer.valueOf(msgSeqNumber), Boolean.valueOf(last)
/*      */           }); 
/*  202 */     synchronized (this.parameters) {
/*  203 */       this.header.setType(this.header.type = 1);
/*  204 */       this.header.setVersion(this.header.version = 1);
/*  205 */       this.header.setCommand(this.header.command = command);
/*  206 */       this.header.setMsgSeqNumber(this.header.msgSeqNumber = msgSeqNumber);
/*  207 */       this.header.setControl(this.header.control = last ? 1 : 0);
/*  208 */       this.header.setCompCode(this.header.compCode = 0);
/*  209 */       this.header.setReason(this.header.reason = 0);
/*  210 */       this.header.setParameterCount(this.header.parameterCount = 0);
/*      */       
/*  212 */       this.parameters.removeAllElements();
/*      */     } 
/*      */     
/*  215 */     if (Trace.isOn) {
/*  216 */       Trace.exit(this, "com.ibm.mq.pcf.PCFMessage", "initialize(int,int,int,boolean)");
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
/*      */   public void addParameter(PCFParameter parameter) {
/*  231 */     if (Trace.isOn) {
/*  232 */       Trace.entry(this, "com.ibm.mq.pcf.PCFMessage", "addParameter(PCFParameter)", new Object[] { parameter });
/*      */     }
/*      */     
/*  235 */     synchronized (this.parameters) {
/*  236 */       this.parameters.addElement(parameter);
/*  237 */       this.header.setParameterCount(this.header.getParameterCount() + 1);
/*      */ 
/*      */ 
/*      */       
/*  241 */       this.header.setVersion(Math.max(this.header.version, parameter.getHeaderVersion()));
/*      */     } 
/*      */     
/*  244 */     if (Trace.isOn) {
/*  245 */       Trace.exit(this, "com.ibm.mq.pcf.PCFMessage", "addParameter(PCFParameter)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addParameter(int parameter, int value) {
/*  255 */     if (Trace.isOn)
/*  256 */       Trace.entry(this, "com.ibm.mq.pcf.PCFMessage", "addParameter(int,int)", new Object[] {
/*  257 */             Integer.valueOf(parameter), Integer.valueOf(value)
/*      */           }); 
/*  259 */     addParameter(new MQCFIN(parameter, value));
/*      */     
/*  261 */     if (Trace.isOn) {
/*  262 */       Trace.exit(this, "com.ibm.mq.pcf.PCFMessage", "addParameter(int,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addParameter(int parameter, int[] values) {
/*  272 */     if (Trace.isOn)
/*  273 */       Trace.entry(this, "com.ibm.mq.pcf.PCFMessage", "addParameter(int,int [ ])", new Object[] {
/*  274 */             Integer.valueOf(parameter), values
/*      */           }); 
/*  276 */     addParameter(new MQCFIL(parameter, values));
/*      */     
/*  278 */     if (Trace.isOn) {
/*  279 */       Trace.exit(this, "com.ibm.mq.pcf.PCFMessage", "addParameter(int,int [ ])");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addParameter(int parameter, long value) {
/*  289 */     if (Trace.isOn)
/*  290 */       Trace.entry(this, "com.ibm.mq.pcf.PCFMessage", "addParameter(int,long)", new Object[] {
/*  291 */             Integer.valueOf(parameter), Long.valueOf(value)
/*      */           }); 
/*  293 */     addParameter(new MQCFIN64(parameter, value));
/*      */     
/*  295 */     if (Trace.isOn) {
/*  296 */       Trace.exit(this, "com.ibm.mq.pcf.PCFMessage", "addParameter(int,long)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addParameter(int parameter, long[] values) {
/*  306 */     if (Trace.isOn)
/*  307 */       Trace.entry(this, "com.ibm.mq.pcf.PCFMessage", "addParameter(int,long [ ])", new Object[] {
/*  308 */             Integer.valueOf(parameter), values
/*      */           }); 
/*  310 */     addParameter(new MQCFIL64(parameter, values));
/*      */     
/*  312 */     if (Trace.isOn) {
/*  313 */       Trace.exit(this, "com.ibm.mq.pcf.PCFMessage", "addParameter(int,long [ ])");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addParameter(int parameter, String value) {
/*  323 */     if (Trace.isOn)
/*  324 */       Trace.entry(this, "com.ibm.mq.pcf.PCFMessage", "addParameter(int,String)", new Object[] {
/*  325 */             Integer.valueOf(parameter), value
/*      */           }); 
/*  327 */     addParameter(new MQCFST(parameter, value));
/*      */     
/*  329 */     if (Trace.isOn) {
/*  330 */       Trace.exit(this, "com.ibm.mq.pcf.PCFMessage", "addParameter(int,String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addParameter(int parameter, String[] values) {
/*  340 */     if (Trace.isOn)
/*  341 */       Trace.entry(this, "com.ibm.mq.pcf.PCFMessage", "addParameter(int,String [ ])", new Object[] {
/*  342 */             Integer.valueOf(parameter), values
/*      */           }); 
/*  344 */     addParameter(new MQCFSL(parameter, values));
/*      */     
/*  346 */     if (Trace.isOn) {
/*  347 */       Trace.exit(this, "com.ibm.mq.pcf.PCFMessage", "addParameter(int,String [ ])");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addParameter(int parameter, byte[] value) {
/*  357 */     if (Trace.isOn)
/*  358 */       Trace.entry(this, "com.ibm.mq.pcf.PCFMessage", "addParameter(int,byte [ ])", new Object[] {
/*  359 */             Integer.valueOf(parameter), value
/*      */           }); 
/*  361 */     addParameter(new MQCFBS(parameter, value));
/*      */     
/*  363 */     if (Trace.isOn) {
/*  364 */       Trace.exit(this, "com.ibm.mq.pcf.PCFMessage", "addParameter(int,byte [ ])");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addFilterParameter(int parameter, int operator, int value) {
/*  374 */     if (Trace.isOn) {
/*  375 */       Trace.entry(this, "com.ibm.mq.pcf.PCFMessage", "addFilterParameter(int,int,int)", new Object[] {
/*  376 */             Integer.valueOf(parameter), Integer.valueOf(operator), Integer.valueOf(value)
/*      */           });
/*      */     }
/*  379 */     addParameter(new MQCFIF(parameter, operator, value));
/*      */     
/*  381 */     if (Trace.isOn) {
/*  382 */       Trace.exit(this, "com.ibm.mq.pcf.PCFMessage", "addFilterParameter(int,int,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addFilterParameter(int parameter, int operator, String value) {
/*  392 */     if (Trace.isOn)
/*  393 */       Trace.entry(this, "com.ibm.mq.pcf.PCFMessage", "addFilterParameter(int,int,String)", new Object[] {
/*  394 */             Integer.valueOf(parameter), Integer.valueOf(operator), value
/*      */           }); 
/*  396 */     addParameter(new MQCFSF(parameter, operator, value));
/*      */     
/*  398 */     if (Trace.isOn) {
/*  399 */       Trace.exit(this, "com.ibm.mq.pcf.PCFMessage", "addFilterParameter(int,int,String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addFilterParameter(int parameter, int operator, byte[] value) {
/*  409 */     if (Trace.isOn)
/*  410 */       Trace.entry(this, "com.ibm.mq.pcf.PCFMessage", "addFilterParameter(int,int,byte [ ])", new Object[] {
/*  411 */             Integer.valueOf(parameter), Integer.valueOf(operator), value
/*      */           }); 
/*  413 */     addParameter(new MQCFBF(parameter, operator, value));
/*      */     
/*  415 */     if (Trace.isOn) {
/*  416 */       Trace.exit(this, "com.ibm.mq.pcf.PCFMessage", "addFilterParameter(int,int,byte [ ])");
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
/*      */   public int getType() {
/*  429 */     int traceRet1 = this.header.getType();
/*  430 */     if (Trace.isOn) {
/*  431 */       Trace.data(this, "com.ibm.mq.pcf.PCFMessage", "getType()", "getter", 
/*  432 */           Integer.valueOf(traceRet1));
/*      */     }
/*  434 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCommand() {
/*  444 */     int traceRet1 = this.header.getCommand();
/*  445 */     if (Trace.isOn) {
/*  446 */       Trace.data(this, "com.ibm.mq.pcf.PCFMessage", "getCommand()", "getter", 
/*  447 */           Integer.valueOf(traceRet1));
/*      */     }
/*  449 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMsgSeqNumber() {
/*  459 */     int traceRet1 = this.header.getMsgSeqNumber();
/*  460 */     if (Trace.isOn) {
/*  461 */       Trace.data(this, "com.ibm.mq.pcf.PCFMessage", "getMsgSeqNumber()", "getter", 
/*  462 */           Integer.valueOf(traceRet1));
/*      */     }
/*  464 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getControl() {
/*  474 */     int traceRet1 = this.header.getControl();
/*  475 */     if (Trace.isOn) {
/*  476 */       Trace.data(this, "com.ibm.mq.pcf.PCFMessage", "getControl()", "getter", 
/*  477 */           Integer.valueOf(traceRet1));
/*      */     }
/*  479 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCompCode() {
/*  489 */     int traceRet1 = this.header.getCompCode();
/*  490 */     if (Trace.isOn) {
/*  491 */       Trace.data(this, "com.ibm.mq.pcf.PCFMessage", "getCompCode()", "getter", 
/*  492 */           Integer.valueOf(traceRet1));
/*      */     }
/*  494 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getReason() {
/*  504 */     int traceRet1 = this.header.getReason();
/*  505 */     if (Trace.isOn) {
/*  506 */       Trace.data(this, "com.ibm.mq.pcf.PCFMessage", "getReason()", "getter", 
/*  507 */           Integer.valueOf(traceRet1));
/*      */     }
/*  509 */     return traceRet1;
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
/*      */   public int getParameterCount() {
/*  521 */     int traceRet1 = this.header.getParameterCount();
/*  522 */     if (Trace.isOn) {
/*  523 */       Trace.data(this, "com.ibm.mq.pcf.PCFMessage", "getParameterCount()", "getter", 
/*  524 */           Integer.valueOf(traceRet1));
/*      */     }
/*  526 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Enumeration getParameters() {
/*  534 */     Enumeration traceRet1 = this.myDelegate.getParameters();
/*  535 */     if (Trace.isOn) {
/*  536 */       Trace.data(this, "com.ibm.mq.pcf.PCFMessage", "getParameters()", "getter", traceRet1);
/*      */     }
/*  538 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PCFParameter getParameter(int parameter) {
/*  546 */     if (Trace.isOn) {
/*  547 */       Trace.entry(this, "com.ibm.mq.pcf.PCFMessage", "getParameter(int)", new Object[] {
/*  548 */             Integer.valueOf(parameter)
/*      */           });
/*      */     }
/*      */     
/*  552 */     PCFParameter match = null;
/*      */     
/*  554 */     synchronized (this.parameters) {
/*  555 */       int i = this.parameters.size();
/*      */       
/*  557 */       while (i-- > 0 && match == null) {
/*  558 */         PCFParameter p = this.parameters.elementAt(i);
/*      */         
/*  560 */         if (parameter == p.getParameter()) {
/*  561 */           match = p;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  566 */     if (Trace.isOn) {
/*  567 */       Trace.exit(this, "com.ibm.mq.pcf.PCFMessage", "getParameter(int)", match);
/*      */     }
/*  569 */     return match;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getParameterValue(int parameter) {
/*  577 */     if (Trace.isOn)
/*  578 */       Trace.entry(this, "com.ibm.mq.pcf.PCFMessage", "getParameterValue(int)", new Object[] {
/*  579 */             Integer.valueOf(parameter)
/*      */           }); 
/*  581 */     PCFParameter p = getParameter(parameter);
/*      */     
/*  583 */     Object traceRet1 = (p == null) ? null : p.getValue();
/*      */     
/*  585 */     if (Trace.isOn) {
/*  586 */       Trace.exit(this, "com.ibm.mq.pcf.PCFMessage", "getParameterValue(int)", traceRet1);
/*      */     }
/*  588 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getIntParameterValue(int parameter) throws PCFException {
/*  596 */     if (Trace.isOn)
/*  597 */       Trace.entry(this, "com.ibm.mq.pcf.PCFMessage", "getIntParameterValue(int)", new Object[] {
/*  598 */             Integer.valueOf(parameter)
/*      */           }); 
/*  600 */     Integer value = (Integer)getParameterValue(parameter);
/*      */     
/*  602 */     if (value == null) {
/*  603 */       PCFException traceRet1 = new PCFException(2, 3014, this);
/*      */       
/*  605 */       if (Trace.isOn) {
/*  606 */         Trace.throwing(this, "com.ibm.mq.pcf.PCFMessage", "getIntParameterValue(int)", (Throwable)traceRet1);
/*      */       }
/*  608 */       throw traceRet1;
/*      */     } 
/*  610 */     int traceRet2 = value.intValue();
/*  611 */     if (Trace.isOn) {
/*  612 */       Trace.exit(this, "com.ibm.mq.pcf.PCFMessage", "getIntParameterValue(int)", 
/*  613 */           Integer.valueOf(traceRet2));
/*      */     }
/*  615 */     return traceRet2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int[] getIntListParameterValue(int parameter) throws PCFException {
/*  623 */     if (Trace.isOn)
/*  624 */       Trace.entry(this, "com.ibm.mq.pcf.PCFMessage", "getIntListParameterValue(int)", new Object[] {
/*  625 */             Integer.valueOf(parameter)
/*      */           }); 
/*  627 */     int[] values = (int[])getParameterValue(parameter);
/*      */     
/*  629 */     if (values == null) {
/*  630 */       PCFException traceRet1 = new PCFException(2, 3047, this);
/*      */       
/*  632 */       if (Trace.isOn) {
/*  633 */         Trace.throwing(this, "com.ibm.mq.pcf.PCFMessage", "getIntListParameterValue(int)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  636 */       throw traceRet1;
/*      */     } 
/*  638 */     if (Trace.isOn) {
/*  639 */       Trace.exit(this, "com.ibm.mq.pcf.PCFMessage", "getIntListParameterValue(int)", values);
/*      */     }
/*  641 */     return values;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getInt64ParameterValue(int parameter) throws PCFException {
/*  649 */     if (Trace.isOn)
/*  650 */       Trace.entry(this, "com.ibm.mq.pcf.PCFMessage", "getInt64ParameterValue(int)", new Object[] {
/*  651 */             Integer.valueOf(parameter)
/*      */           }); 
/*  653 */     Long value = (Long)getParameterValue(parameter);
/*      */     
/*  655 */     if (value == null) {
/*  656 */       PCFException traceRet1 = new PCFException(2, 3014, this);
/*      */       
/*  658 */       if (Trace.isOn) {
/*  659 */         Trace.throwing(this, "com.ibm.mq.pcf.PCFMessage", "getInt64ParameterValue(int)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  662 */       throw traceRet1;
/*      */     } 
/*  664 */     long traceRet2 = value.longValue();
/*  665 */     if (Trace.isOn) {
/*  666 */       Trace.exit(this, "com.ibm.mq.pcf.PCFMessage", "getInt64ParameterValue(int)", 
/*  667 */           Long.valueOf(traceRet2));
/*      */     }
/*  669 */     return traceRet2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long[] getIntList64ParameterValue(int parameter) throws PCFException {
/*  677 */     if (Trace.isOn)
/*  678 */       Trace.entry(this, "com.ibm.mq.pcf.PCFMessage", "getIntList64ParameterValue(int)", new Object[] {
/*  679 */             Integer.valueOf(parameter)
/*      */           }); 
/*  681 */     long[] values = (long[])getParameterValue(parameter);
/*      */     
/*  683 */     if (values == null) {
/*  684 */       PCFException traceRet1 = new PCFException(2, 3047, this);
/*      */       
/*  686 */       if (Trace.isOn) {
/*  687 */         Trace.throwing(this, "com.ibm.mq.pcf.PCFMessage", "getIntList64ParameterValue(int)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  690 */       throw traceRet1;
/*      */     } 
/*  692 */     if (Trace.isOn) {
/*  693 */       Trace.exit(this, "com.ibm.mq.pcf.PCFMessage", "getIntList64ParameterValue(int)", values);
/*      */     }
/*  695 */     return values;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getStringParameterValue(int parameter) throws PCFException {
/*  703 */     if (Trace.isOn)
/*  704 */       Trace.entry(this, "com.ibm.mq.pcf.PCFMessage", "getStringParameterValue(int)", new Object[] {
/*  705 */             Integer.valueOf(parameter)
/*      */           }); 
/*  707 */     String value = (String)getParameterValue(parameter);
/*      */     
/*  709 */     if (value == null) {
/*  710 */       PCFException traceRet1 = new PCFException(2, 3015, this);
/*      */       
/*  712 */       if (Trace.isOn) {
/*  713 */         Trace.throwing(this, "com.ibm.mq.pcf.PCFMessage", "getStringParameterValue(int)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  716 */       throw traceRet1;
/*      */     } 
/*  718 */     if (Trace.isOn) {
/*  719 */       Trace.exit(this, "com.ibm.mq.pcf.PCFMessage", "getStringParameterValue(int)", value);
/*      */     }
/*  721 */     return value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String[] getStringListParameterValue(int parameter) throws PCFException {
/*  729 */     if (Trace.isOn)
/*  730 */       Trace.entry(this, "com.ibm.mq.pcf.PCFMessage", "getStringListParameterValue(int)", new Object[] {
/*  731 */             Integer.valueOf(parameter)
/*      */           }); 
/*  733 */     String[] values = (String[])getParameterValue(parameter);
/*      */     
/*  735 */     if (values == null) {
/*      */ 
/*      */       
/*  738 */       PCFException traceRet1 = new PCFException(2, 3033, this);
/*      */       
/*  740 */       if (Trace.isOn) {
/*  741 */         Trace.throwing(this, "com.ibm.mq.pcf.PCFMessage", "getStringListParameterValue(int)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  744 */       throw traceRet1;
/*      */     } 
/*  746 */     if (Trace.isOn) {
/*  747 */       Trace.exit(this, "com.ibm.mq.pcf.PCFMessage", "getStringListParameterValue(int)", values);
/*      */     }
/*  749 */     return values;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getBytesParameterValue(int parameter) throws PCFException {
/*  757 */     if (Trace.isOn)
/*  758 */       Trace.entry(this, "com.ibm.mq.pcf.PCFMessage", "getBytesParameterValue(int)", new Object[] {
/*  759 */             Integer.valueOf(parameter)
/*      */           }); 
/*  761 */     byte[] value = (byte[])getParameterValue(parameter);
/*      */     
/*  763 */     if (value == null) {
/*  764 */       PCFException traceRet1 = new PCFException(2, 3256, this);
/*      */       
/*  766 */       if (Trace.isOn) {
/*  767 */         Trace.throwing(this, "com.ibm.mq.pcf.PCFMessage", "getBytesParameterValue(int)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  770 */       throw traceRet1;
/*      */     } 
/*  772 */     if (Trace.isOn) {
/*  773 */       Trace.exit(this, "com.ibm.mq.pcf.PCFMessage", "getBytesParameterValue(int)", value);
/*      */     }
/*  775 */     return value;
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
/*      */   public void initialize(MQMessage message) throws MQException, IOException {
/*  787 */     if (Trace.isOn) {
/*  788 */       Trace.entry(this, "com.ibm.mq.pcf.PCFMessage", "initialize(MQMessage)", new Object[] { message });
/*      */     }
/*      */     
/*  791 */     synchronized (this.parameters) {
/*  792 */       this.header = new MQCFH(message);
/*  793 */       this.myDelegate.setHeader(this.header.delegate);
/*  794 */       this.parameters.removeAllElements();
/*      */       
/*  796 */       int count = this.header.parameterCount;
/*      */       
/*  798 */       this.header.parameterCount = 0;
/*      */       
/*  800 */       while (count-- > 0)
/*      */       {
/*      */         
/*  803 */         addParameter(PCFParameter.nextParameter(message));
/*      */       }
/*      */     } 
/*      */     
/*  807 */     if (Trace.isOn) {
/*  808 */       Trace.exit(this, "com.ibm.mq.pcf.PCFMessage", "initialize(MQMessage)");
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
/*      */   public int write(MQMessage message) throws IOException {
/*  822 */     if (Trace.isOn) {
/*  823 */       Trace.entry(this, "com.ibm.mq.pcf.PCFMessage", "write(MQMessage)", new Object[] { message });
/*      */     }
/*  825 */     writeCachedContent();
/*  826 */     synchronized (this.parameters) {
/*  827 */       int bytes = this.header.write((DataOutput)message), count = this.parameters.size();
/*      */       
/*  829 */       for (int i = 0; i < count; i++) {
/*  830 */         PCFParameter p = this.parameters.elementAt(i);
/*      */         
/*  832 */         bytes += p.write((DataOutput)message);
/*      */       } 
/*      */       
/*  835 */       if (Trace.isOn) {
/*  836 */         Trace.exit(this, "com.ibm.mq.pcf.PCFMessage", "write(MQMessage)", Integer.valueOf(bytes));
/*      */       }
/*  838 */       return bytes;
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
/*      */   public int write(DataOutput message) throws IOException {
/*  852 */     if (Trace.isOn) {
/*  853 */       Trace.entry(this, "com.ibm.mq.pcf.PCFMessage", "write(DataOutput)", new Object[] { message });
/*      */     }
/*  855 */     writeCachedContent();
/*  856 */     synchronized (this.parameters) {
/*  857 */       int bytes = this.header.write(message), count = this.parameters.size();
/*      */       
/*  859 */       for (int i = 0; i < count; i++) {
/*  860 */         PCFParameter p = this.parameters.elementAt(i);
/*      */         
/*  862 */         bytes += p.write(message);
/*      */       } 
/*      */       
/*  865 */       if (Trace.isOn) {
/*  866 */         Trace.exit(this, "com.ibm.mq.pcf.PCFMessage", "write(DataOutput)", Integer.valueOf(bytes));
/*      */       }
/*      */       
/*  869 */       return bytes;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int size() {
/*  880 */     if (Trace.isOn) {
/*  881 */       Trace.entry(this, "com.ibm.mq.pcf.PCFMessage", "size()");
/*      */     }
/*  883 */     synchronized (this.parameters) {
/*  884 */       int size = this.header.size(), i = this.parameters.size();
/*      */       
/*  886 */       while (i-- > 0) {
/*  887 */         PCFParameter p = this.parameters.elementAt(i);
/*      */         
/*  889 */         size += p.size();
/*      */       } 
/*      */       
/*  892 */       if (Trace.isOn) {
/*  893 */         Trace.exit(this, "com.ibm.mq.pcf.PCFMessage", "size()", Integer.valueOf(size));
/*      */       }
/*  895 */       return size;
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
/*      */   public boolean equals(Object obj) {
/*  911 */     if (Trace.isOn) {
/*  912 */       Trace.entry(this, "com.ibm.mq.pcf.PCFMessage", "equals(Object)", new Object[] { obj });
/*      */     }
/*  914 */     if (obj != null && obj instanceof PCFMessage) {
/*  915 */       PCFMessage other = (PCFMessage)obj;
/*      */       
/*  917 */       if (other.getParameterCount() == getParameterCount() && other.header.equals(this.header)) {
/*  918 */         Enumeration<E> otherParameters = other.getParameters(); Enumeration parameters = getParameters();
/*  919 */         boolean match = true;
/*      */         
/*      */         try {
/*  922 */           while (match && parameters.hasMoreElements()) {
/*  923 */             match = otherParameters.nextElement().equals(parameters.nextElement());
/*      */           
/*      */           }
/*      */         }
/*  927 */         catch (NoSuchElementException nsee) {
/*  928 */           if (Trace.isOn) {
/*  929 */             Trace.catchBlock(this, "com.ibm.mq.pcf.PCFMessage", "equals(Object)", nsee);
/*      */           }
/*  931 */           if (Trace.isOn) {
/*  932 */             Trace.exit(this, "com.ibm.mq.pcf.PCFMessage", "equals(Object)", Boolean.valueOf(false), 1);
/*      */           }
/*      */           
/*  935 */           return false;
/*      */         } 
/*      */         
/*  938 */         if (Trace.isOn) {
/*  939 */           Trace.exit(this, "com.ibm.mq.pcf.PCFMessage", "equals(Object)", Boolean.valueOf(match), 2);
/*      */         }
/*      */         
/*  942 */         return match;
/*      */       } 
/*  944 */       if (Trace.isOn) {
/*  945 */         Trace.exit(this, "com.ibm.mq.pcf.PCFMessage", "equals(Object)", Boolean.valueOf(false), 3);
/*      */       }
/*      */       
/*  948 */       return false;
/*      */     } 
/*  950 */     if (Trace.isOn) {
/*  951 */       Trace.exit(this, "com.ibm.mq.pcf.PCFMessage", "equals(Object)", Boolean.valueOf(false), 4);
/*      */     }
/*  953 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/*  963 */     if (Trace.isOn) {
/*  964 */       Trace.entry(this, "com.ibm.mq.pcf.PCFMessage", "toString()");
/*      */     }
/*  966 */     StringBuffer sb = new StringBuffer();
/*  967 */     sb.append(getUnqualifiedClassName());
/*  968 */     sb.append(": \n");
/*      */     
/*  970 */     if (this.header != null) {
/*  971 */       sb.append(this.header.toString());
/*      */     } else {
/*  973 */       sb.append("No header(yet)");
/*      */     } 
/*  975 */     if (this.parameters != null) {
/*  976 */       synchronized (this.parameters) {
/*  977 */         for (int i = 0; i < this.parameters.size(); i++) {
/*  978 */           PCFParameter p = this.parameters.elementAt(i);
/*  979 */           sb.append('\n');
/*  980 */           sb.append(p.toString());
/*      */         } 
/*      */       } 
/*      */     } else {
/*  984 */       sb.append("\nNo parameters(yet)");
/*      */     } 
/*      */     
/*  987 */     String traceRet1 = new String(sb);
/*  988 */     if (Trace.isOn) {
/*  989 */       Trace.exit(this, "com.ibm.mq.pcf.PCFMessage", "toString()", traceRet1);
/*      */     }
/*  991 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void discardCachedContent() {
/*  999 */     if (Trace.isOn) {
/* 1000 */       Trace.entry(this, "com.ibm.mq.pcf.PCFMessage", "discardCachedContent()");
/*      */     }
/* 1002 */     if (this.header != null) {
/* 1003 */       this.header.discardCachedContent();
/*      */     }
/*      */     
/* 1006 */     synchronized (this.parameters) {
/* 1007 */       for (int i = 0; i < this.parameters.size(); i++) {
/* 1008 */         ((PCFParameter)this.parameters.elementAt(i)).discardCachedContent();
/*      */       }
/*      */     } 
/*      */     
/* 1012 */     if (Trace.isOn) {
/* 1013 */       Trace.exit(this, "com.ibm.mq.pcf.PCFMessage", "discardCachedContent()");
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
/*      */   public void readCachedContent() throws MQException, IOException {
/* 1025 */     if (Trace.isOn) {
/* 1026 */       Trace.entry(this, "com.ibm.mq.pcf.PCFMessage", "readCachedContent()");
/*      */     }
/* 1028 */     if (this.header != null) {
/* 1029 */       this.header.readCachedContent();
/*      */     }
/*      */     
/* 1032 */     synchronized (this.parameters) {
/* 1033 */       for (int i = 0; i < this.parameters.size(); i++) {
/*      */         try {
/* 1035 */           ((PCFParameter)this.parameters.elementAt(i)).readCachedContent();
/*      */         }
/* 1037 */         catch (MQException e) {
/* 1038 */           if (Trace.isOn) {
/* 1039 */             Trace.catchBlock(this, "com.ibm.mq.pcf.PCFMessage", "readCachedContent()", (Throwable)e, 1);
/*      */           }
/*      */           
/* 1042 */           if (Trace.isOn) {
/* 1043 */             Trace.throwing(this, "com.ibm.mq.pcf.PCFMessage", "readCachedContent()", (Throwable)e, 1);
/*      */           }
/* 1045 */           throw e;
/*      */         }
/* 1047 */         catch (IOException e) {
/* 1048 */           if (Trace.isOn) {
/* 1049 */             Trace.catchBlock(this, "com.ibm.mq.pcf.PCFMessage", "readCachedContent()", e, 2);
/*      */           }
/* 1051 */           if (Trace.isOn) {
/* 1052 */             Trace.throwing(this, "com.ibm.mq.pcf.PCFMessage", "readCachedContent()", e, 2);
/*      */           }
/* 1054 */           throw e;
/*      */         }
/* 1056 */         catch (Exception e) {
/* 1057 */           if (Trace.isOn) {
/* 1058 */             Trace.catchBlock(this, "com.ibm.mq.pcf.PCFMessage", "readCachedContent()", e, 3);
/*      */           }
/* 1060 */           RuntimeException traceRet1 = new RuntimeException(e);
/* 1061 */           if (Trace.isOn) {
/* 1062 */             Trace.throwing(this, "com.ibm.mq.pcf.PCFMessage", "readCachedContent()", traceRet1, 3);
/*      */           }
/*      */           
/* 1065 */           throw traceRet1;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1070 */     if (Trace.isOn) {
/* 1071 */       Trace.exit(this, "com.ibm.mq.pcf.PCFMessage", "readCachedContent()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeCachedContent() throws IOException {
/* 1082 */     if (Trace.isOn) {
/* 1083 */       Trace.entry(this, "com.ibm.mq.pcf.PCFMessage", "writeCachedContent()");
/*      */     }
/* 1085 */     if (this.header != null) {
/* 1086 */       this.header.writeCachedContent();
/*      */     }
/*      */     
/* 1089 */     synchronized (this.parameters) {
/* 1090 */       for (int i = 0; i < this.parameters.size(); i++) {
/* 1091 */         ((PCFParameter)this.parameters.elementAt(i)).writeCachedContent();
/*      */       }
/*      */     } 
/*      */     
/* 1095 */     if (Trace.isOn)
/* 1096 */       Trace.exit(this, "com.ibm.mq.pcf.PCFMessage", "writeCachedContent()"); 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\pcf\PCFMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */