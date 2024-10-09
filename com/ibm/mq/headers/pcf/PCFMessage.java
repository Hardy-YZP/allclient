/*      */ package com.ibm.mq.headers.pcf;
/*      */ 
/*      */ import com.ibm.mq.headers.MQDataException;
/*      */ import com.ibm.mq.headers.internal.HeaderType;
/*      */ import com.ibm.mq.headers.internal.MessageWrapper;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import io.swagger.annotations.ApiModelProperty;
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
/*      */ public class PCFMessage
/*      */   extends PCFHeader
/*      */   implements PCFContent
/*      */ {
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/PCFMessage.java";
/*      */   MQCFH header;
/*      */   
/*      */   static {
/*   55 */     if (Trace.isOn) {
/*   56 */       Trace.data("com.ibm.mq.headers.pcf.PCFMessage", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/PCFMessage.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   66 */   Vector<PCFParameter> parameters = new Vector<>(8, 8);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public PCFMessage() {
/*   76 */     super(new HeaderType("PCFMessage"));
/*   77 */     if (Trace.isOn) {
/*   78 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFMessage", "<init>()");
/*      */     }
/*      */     
/*   81 */     if (Trace.isOn) {
/*   82 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFMessage", "<init>()");
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
/*   95 */     this(1, command, 1, true);
/*   96 */     if (Trace.isOn)
/*   97 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFMessage", "<init>(int)", new Object[] {
/*   98 */             Integer.valueOf(command)
/*      */           }); 
/*  100 */     if (Trace.isOn) {
/*  101 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFMessage", "<init>(int)");
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
/*  121 */     this();
/*  122 */     if (Trace.isOn)
/*  123 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFMessage", "<init>(int,int,int,boolean)", new Object[] {
/*  124 */             Integer.valueOf(type), Integer.valueOf(command), 
/*  125 */             Integer.valueOf(msgSeqNumber), Boolean.valueOf(last)
/*      */           }); 
/*  127 */     this.header = new MQCFH(command, 0);
/*      */     
/*  129 */     this.header.setType(type);
/*  130 */     this.header.setMsgSeqNumber(msgSeqNumber);
/*  131 */     this.header.setControl(last ? 1 : 0);
/*      */     
/*  133 */     if (Trace.isOn) {
/*  134 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFMessage", "<init>(int,int,int,boolean)");
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
/*      */   public PCFMessage(DataInput message) throws MQDataException, IOException {
/*  149 */     this();
/*  150 */     if (Trace.isOn) {
/*  151 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFMessage", "<init>(DataInput)", new Object[] { message });
/*      */     }
/*      */     
/*  154 */     initialize((DataInput)MessageWrapper.wrap(message));
/*      */     
/*  156 */     if (Trace.isOn) {
/*  157 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFMessage", "<init>(DataInput)");
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
/*  169 */     if (Trace.isOn)
/*  170 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFMessage", "initialize(int)", new Object[] {
/*  171 */             Integer.valueOf(command)
/*      */           }); 
/*  173 */     initialize(1, command, 1, true);
/*      */     
/*  175 */     if (Trace.isOn) {
/*  176 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFMessage", "initialize(int)");
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
/*  196 */     if (Trace.isOn)
/*  197 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFMessage", "initialize(int,int,int,boolean)", new Object[] {
/*  198 */             Integer.valueOf(type), Integer.valueOf(command), 
/*  199 */             Integer.valueOf(msgSeqNumber), Boolean.valueOf(last)
/*      */           }); 
/*  201 */     synchronized (this.parameters) {
/*  202 */       this.header.setType(1);
/*  203 */       this.header.setVersion(1);
/*  204 */       this.header.setCommand(command);
/*  205 */       this.header.setMsgSeqNumber(msgSeqNumber);
/*  206 */       this.header.setControl(last ? 1 : 0);
/*  207 */       this.header.setCompCode(0);
/*  208 */       this.header.setReason(0);
/*  209 */       this.header.setParameterCount(0);
/*      */       
/*  211 */       this.parameters.removeAllElements();
/*      */     } 
/*      */     
/*  214 */     if (Trace.isOn) {
/*  215 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFMessage", "initialize(int,int,int,boolean)");
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
/*  230 */     if (Trace.isOn) {
/*  231 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFMessage", "addParameter(PCFParameter)", new Object[] { parameter });
/*      */     }
/*      */     
/*  234 */     synchronized (this.parameters) {
/*  235 */       this.parameters.addElement(parameter);
/*  236 */       this.header.setParameterCount(this.header.getParameterCount() + 1);
/*      */ 
/*      */ 
/*      */       
/*  240 */       this.header.setVersion(Math.max(this.header.getVersion(), parameter.getHeaderVersion()));
/*      */     } 
/*      */     
/*  243 */     if (Trace.isOn) {
/*  244 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFMessage", "addParameter(PCFParameter)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addParameter(int parameter, int value) {
/*  254 */     if (Trace.isOn)
/*  255 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFMessage", "addParameter(int,int)", new Object[] {
/*  256 */             Integer.valueOf(parameter), Integer.valueOf(value)
/*      */           }); 
/*  258 */     addParameter(new MQCFIN(parameter, value));
/*      */     
/*  260 */     if (Trace.isOn) {
/*  261 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFMessage", "addParameter(int,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addParameter(int parameter, int[] values) {
/*  271 */     if (Trace.isOn)
/*  272 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFMessage", "addParameter(int,int [ ])", new Object[] {
/*  273 */             Integer.valueOf(parameter), values
/*      */           }); 
/*  275 */     addParameter(new MQCFIL(parameter, values));
/*      */     
/*  277 */     if (Trace.isOn) {
/*  278 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFMessage", "addParameter(int,int [ ])");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addParameter(int parameter, long value) {
/*  288 */     if (Trace.isOn)
/*  289 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFMessage", "addParameter(int,long)", new Object[] {
/*  290 */             Integer.valueOf(parameter), Long.valueOf(value)
/*      */           }); 
/*  292 */     addParameter(new MQCFIN64(parameter, value));
/*      */     
/*  294 */     if (Trace.isOn) {
/*  295 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFMessage", "addParameter(int,long)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addParameter(int parameter, long[] values) {
/*  305 */     if (Trace.isOn)
/*  306 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFMessage", "addParameter(int,long [ ])", new Object[] {
/*  307 */             Integer.valueOf(parameter), values
/*      */           }); 
/*  309 */     addParameter(new MQCFIL64(parameter, values));
/*      */     
/*  311 */     if (Trace.isOn) {
/*  312 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFMessage", "addParameter(int,long [ ])");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addParameter(int parameter, String value) {
/*  322 */     if (Trace.isOn)
/*  323 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFMessage", "addParameter(int,String)", new Object[] {
/*  324 */             Integer.valueOf(parameter), value
/*      */           }); 
/*  326 */     addParameter(new MQCFST(parameter, value));
/*      */     
/*  328 */     if (Trace.isOn) {
/*  329 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFMessage", "addParameter(int,String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addParameter(int parameter, String[] values) {
/*  339 */     if (Trace.isOn)
/*  340 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFMessage", "addParameter(int,String [ ])", new Object[] {
/*  341 */             Integer.valueOf(parameter), values
/*      */           }); 
/*  343 */     addParameter(new MQCFSL(parameter, values));
/*      */     
/*  345 */     if (Trace.isOn) {
/*  346 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFMessage", "addParameter(int,String [ ])");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addParameter(int parameter, byte[] value) {
/*  356 */     if (Trace.isOn)
/*  357 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFMessage", "addParameter(int,byte [ ])", new Object[] {
/*  358 */             Integer.valueOf(parameter), value
/*      */           }); 
/*  360 */     addParameter(new MQCFBS(parameter, value));
/*      */     
/*  362 */     if (Trace.isOn) {
/*  363 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFMessage", "addParameter(int,byte [ ])");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addFilterParameter(int parameter, int operator, int value) {
/*  373 */     if (Trace.isOn) {
/*  374 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFMessage", "addFilterParameter(int,int,int)", new Object[] {
/*  375 */             Integer.valueOf(parameter), Integer.valueOf(operator), Integer.valueOf(value)
/*      */           });
/*      */     }
/*  378 */     addParameter(new MQCFIF(parameter, operator, value));
/*      */     
/*  380 */     if (Trace.isOn) {
/*  381 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFMessage", "addFilterParameter(int,int,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addFilterParameter(int parameter, int operator, String value) {
/*  391 */     if (Trace.isOn)
/*  392 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFMessage", "addFilterParameter(int,int,String)", new Object[] {
/*  393 */             Integer.valueOf(parameter), Integer.valueOf(operator), value
/*      */           }); 
/*  395 */     addParameter(new MQCFSF(parameter, operator, value));
/*      */     
/*  397 */     if (Trace.isOn) {
/*  398 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFMessage", "addFilterParameter(int,int,String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addFilterParameter(int parameter, int operator, byte[] value) {
/*  408 */     if (Trace.isOn)
/*  409 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFMessage", "addFilterParameter(int,int,byte [ ])", new Object[] {
/*  410 */             Integer.valueOf(parameter), 
/*  411 */             Integer.valueOf(operator), value
/*      */           }); 
/*  413 */     addParameter(new MQCFBF(parameter, operator, value));
/*      */     
/*  415 */     if (Trace.isOn) {
/*  416 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFMessage", "addFilterParameter(int,int,byte [ ])");
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
/*  431 */       Trace.data(this, "com.ibm.mq.headers.pcf.PCFMessage", "getType()", "getter", 
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
/*  446 */       Trace.data(this, "com.ibm.mq.headers.pcf.PCFMessage", "getCommand()", "getter", 
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
/*  461 */       Trace.data(this, "com.ibm.mq.headers.pcf.PCFMessage", "getMsgSeqNumber()", "getter", 
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
/*  476 */       Trace.data(this, "com.ibm.mq.headers.pcf.PCFMessage", "getControl()", "getter", 
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
/*  491 */       Trace.data(this, "com.ibm.mq.headers.pcf.PCFMessage", "getCompCode()", "getter", 
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
/*  506 */       Trace.data(this, "com.ibm.mq.headers.pcf.PCFMessage", "getReason()", "getter", 
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
/*  523 */       Trace.data(this, "com.ibm.mq.headers.pcf.PCFMessage", "getParameterCount()", "getter", 
/*  524 */           Integer.valueOf(traceRet1));
/*      */     }
/*  526 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Enumeration getParameters() {
/*  535 */     Enumeration<PCFParameter> traceRet1 = this.parameters.elements();
/*  536 */     if (Trace.isOn) {
/*  537 */       Trace.data(this, "com.ibm.mq.headers.pcf.PCFMessage", "getParameters()", "getter", traceRet1);
/*      */     }
/*  539 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public Vector getParameterVector() {
/*  551 */     if (Trace.isOn) {
/*  552 */       Trace.data(this, "com.ibm.mq.headers.pcf.PCFMessage", "getParameterVector()", "getter", this.parameters);
/*      */     }
/*      */     
/*  555 */     return this.parameters;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void setHeader(PCFHeader header) {
/*  565 */     if (Trace.isOn) {
/*  566 */       Trace.data(this, "com.ibm.mq.headers.pcf.PCFMessage", "setHeader(PCFHeader)", "setter", header);
/*      */     }
/*      */     
/*  569 */     this.header = (MQCFH)header;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @ApiModelProperty(hidden = true)
/*      */   public void setHeader(MQCFH header) {
/*  580 */     if (Trace.isOn) {
/*  581 */       Trace.data(this, "com.ibm.mq.headers.pcf.PCFMessage", "setHeader(MQCFH)", "setter", header);
/*      */     }
/*      */     
/*  584 */     this.header = header;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQCFH getHeader() {
/*  592 */     if (Trace.isOn) {
/*  593 */       Trace.data(this, "com.ibm.mq.headers.pcf.PCFMessage", "getHeader()", "getter", this.header);
/*      */     }
/*      */ 
/*      */     
/*  597 */     return this.header;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PCFParameter getParameter(int parameter) {
/*  606 */     if (Trace.isOn) {
/*  607 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFMessage", "getParameter(int)", new Object[] {
/*  608 */             Integer.valueOf(parameter)
/*      */           });
/*      */     }
/*      */     
/*  612 */     PCFParameter match = null;
/*      */     
/*  614 */     synchronized (this.parameters) {
/*  615 */       int i = this.parameters.size();
/*      */       
/*  617 */       while (i-- > 0 && match == null) {
/*  618 */         PCFParameter p = this.parameters.elementAt(i);
/*      */         
/*  620 */         if (parameter == p.getParameter()) {
/*  621 */           match = p;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  626 */     if (Trace.isOn) {
/*  627 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFMessage", "getParameter(int)", match);
/*      */     }
/*  629 */     return match;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getParameterValue(int parameter) {
/*  637 */     if (Trace.isOn)
/*  638 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFMessage", "getParameterValue(int)", new Object[] {
/*  639 */             Integer.valueOf(parameter)
/*      */           }); 
/*  641 */     PCFParameter p = getParameter(parameter);
/*      */     
/*  643 */     Object traceRet1 = (p == null) ? null : p.getValue();
/*      */     
/*  645 */     if (Trace.isOn) {
/*  646 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFMessage", "getParameterValue(int)", traceRet1);
/*      */     }
/*  648 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getIntParameterValue(int parameter) throws PCFException {
/*  656 */     if (Trace.isOn)
/*  657 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFMessage", "getIntParameterValue(int)", new Object[] {
/*  658 */             Integer.valueOf(parameter)
/*      */           }); 
/*  660 */     Integer value = (Integer)getParameterValue(parameter);
/*      */     
/*  662 */     if (value == null) {
/*  663 */       PCFException traceRet1 = new PCFException(2, 3014, this);
/*      */       
/*  665 */       if (Trace.isOn) {
/*  666 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.PCFMessage", "getIntParameterValue(int)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  669 */       throw traceRet1;
/*      */     } 
/*  671 */     int traceRet2 = value.intValue();
/*  672 */     if (Trace.isOn) {
/*  673 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFMessage", "getIntParameterValue(int)", 
/*  674 */           Integer.valueOf(traceRet2));
/*      */     }
/*  676 */     return traceRet2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int[] getIntListParameterValue(int parameter) throws PCFException {
/*  684 */     if (Trace.isOn)
/*  685 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFMessage", "getIntListParameterValue(int)", new Object[] {
/*  686 */             Integer.valueOf(parameter)
/*      */           }); 
/*  688 */     int[] values = (int[])getParameterValue(parameter);
/*      */     
/*  690 */     if (values == null) {
/*  691 */       PCFException traceRet1 = new PCFException(2, 3047, this);
/*      */       
/*  693 */       if (Trace.isOn) {
/*  694 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.PCFMessage", "getIntListParameterValue(int)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  697 */       throw traceRet1;
/*      */     } 
/*  699 */     if (Trace.isOn) {
/*  700 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFMessage", "getIntListParameterValue(int)", values);
/*      */     }
/*      */     
/*  703 */     return values;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getInt64ParameterValue(int parameter) throws PCFException {
/*  711 */     if (Trace.isOn)
/*  712 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFMessage", "getInt64ParameterValue(int)", new Object[] {
/*  713 */             Integer.valueOf(parameter)
/*      */           }); 
/*  715 */     Long value = (Long)getParameterValue(parameter);
/*      */     
/*  717 */     if (value == null) {
/*  718 */       PCFException traceRet1 = new PCFException(2, 3014, this);
/*      */       
/*  720 */       if (Trace.isOn) {
/*  721 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.PCFMessage", "getInt64ParameterValue(int)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  724 */       throw traceRet1;
/*      */     } 
/*  726 */     long traceRet2 = value.longValue();
/*  727 */     if (Trace.isOn) {
/*  728 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFMessage", "getInt64ParameterValue(int)", 
/*  729 */           Long.valueOf(traceRet2));
/*      */     }
/*  731 */     return traceRet2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long[] getIntList64ParameterValue(int parameter) throws PCFException {
/*  739 */     if (Trace.isOn)
/*  740 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFMessage", "getIntList64ParameterValue(int)", new Object[] {
/*  741 */             Integer.valueOf(parameter)
/*      */           }); 
/*  743 */     long[] values = (long[])getParameterValue(parameter);
/*      */     
/*  745 */     if (values == null) {
/*  746 */       PCFException traceRet1 = new PCFException(2, 3047, this);
/*      */       
/*  748 */       if (Trace.isOn) {
/*  749 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.PCFMessage", "getIntList64ParameterValue(int)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  752 */       throw traceRet1;
/*      */     } 
/*  754 */     if (Trace.isOn) {
/*  755 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFMessage", "getIntList64ParameterValue(int)", values);
/*      */     }
/*      */     
/*  758 */     return values;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getStringParameterValue(int parameter) throws PCFException {
/*  766 */     if (Trace.isOn)
/*  767 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFMessage", "getStringParameterValue(int)", new Object[] {
/*  768 */             Integer.valueOf(parameter)
/*      */           }); 
/*  770 */     String value = trimNulls((String)getParameterValue(parameter));
/*      */     
/*  772 */     if (value == null) {
/*  773 */       PCFException traceRet1 = new PCFException(2, 3015, this);
/*      */       
/*  775 */       if (Trace.isOn) {
/*  776 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.PCFMessage", "getStringParameterValue(int)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  779 */       throw traceRet1;
/*      */     } 
/*  781 */     if (Trace.isOn) {
/*  782 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFMessage", "getStringParameterValue(int)", value);
/*      */     }
/*  784 */     return value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String[] getStringListParameterValue(int parameter) throws PCFException {
/*  792 */     if (Trace.isOn)
/*  793 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFMessage", "getStringListParameterValue(int)", new Object[] {
/*  794 */             Integer.valueOf(parameter)
/*      */           }); 
/*  796 */     String[] values = trimNulls((String[])getParameterValue(parameter));
/*      */     
/*  798 */     if (values == null) {
/*      */ 
/*      */       
/*  801 */       PCFException traceRet1 = new PCFException(2, 3033, this);
/*      */       
/*  803 */       if (Trace.isOn) {
/*  804 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.PCFMessage", "getStringListParameterValue(int)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  807 */       throw traceRet1;
/*      */     } 
/*  809 */     if (Trace.isOn) {
/*  810 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFMessage", "getStringListParameterValue(int)", values);
/*      */     }
/*      */     
/*  813 */     return values;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getBytesParameterValue(int parameter) throws PCFException {
/*  821 */     if (Trace.isOn)
/*  822 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFMessage", "getBytesParameterValue(int)", new Object[] {
/*  823 */             Integer.valueOf(parameter)
/*      */           }); 
/*  825 */     byte[] value = (byte[])getParameterValue(parameter);
/*      */     
/*  827 */     if (value == null) {
/*  828 */       PCFException traceRet1 = new PCFException(2, 3256, this);
/*      */       
/*  830 */       if (Trace.isOn) {
/*  831 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.PCFMessage", "getBytesParameterValue(int)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  834 */       throw traceRet1;
/*      */     } 
/*  836 */     if (Trace.isOn) {
/*  837 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFMessage", "getBytesParameterValue(int)", value);
/*      */     }
/*  839 */     return value;
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
/*      */   private void initialize(DataInput message) throws MQDataException, IOException {
/*  852 */     if (Trace.isOn) {
/*  853 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFMessage", "initialize(DataInput)", new Object[] { message });
/*      */     }
/*      */     
/*  856 */     synchronized (this.parameters) {
/*  857 */       this.header = new MQCFH(message);
/*  858 */       this.parameters.removeAllElements();
/*      */       
/*  860 */       int count = this.header.getParameterCount();
/*      */       
/*  862 */       this.header.setParameterCount(0);
/*      */       
/*  864 */       while (count-- > 0)
/*      */       {
/*      */         
/*  867 */         addParameter(PCFParameter.nextParameter(message));
/*      */       }
/*      */     } 
/*      */     
/*  871 */     if (Trace.isOn) {
/*  872 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFMessage", "initialize(DataInput)");
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
/*      */   public int write(DataOutput message) throws IOException {
/*  884 */     if (Trace.isOn) {
/*  885 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFMessage", "write(DataOutput)", new Object[] { message });
/*      */     }
/*      */     
/*  888 */     synchronized (this.parameters) {
/*  889 */       int bytes = this.header.write(message), count = this.parameters.size();
/*      */       
/*  891 */       for (int i = 0; i < count; i++) {
/*  892 */         PCFParameter p = this.parameters.elementAt(i);
/*      */         
/*  894 */         bytes += p.write(message);
/*      */       } 
/*      */       
/*  897 */       if (Trace.isOn) {
/*  898 */         Trace.exit(this, "com.ibm.mq.headers.pcf.PCFMessage", "write(DataOutput)", 
/*  899 */             Integer.valueOf(bytes));
/*      */       }
/*  901 */       return bytes;
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
/*      */   public int write(DataOutput message, int encoding, int characterSet) throws IOException {
/*  918 */     if (Trace.isOn) {
/*  919 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFMessage", "write(DataOutput,int,int)", new Object[] { message, 
/*      */ 
/*      */             
/*  922 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*      */     }
/*      */     
/*  925 */     synchronized (this.parameters) {
/*  926 */       int bytes = this.header.write(message, encoding, characterSet);
/*  927 */       int count = this.parameters.size();
/*      */ 
/*      */       
/*  930 */       for (int i = 0; i < count; i++) {
/*  931 */         PCFParameter p = this.parameters.elementAt(i);
/*      */         
/*  933 */         bytes += p.write(message, encoding, characterSet);
/*      */       } 
/*      */       
/*  936 */       if (Trace.isOn) {
/*  937 */         Trace.exit(this, "com.ibm.mq.headers.pcf.PCFMessage", "write(DataOutput,int,int)", 
/*      */ 
/*      */             
/*  940 */             Integer.valueOf(bytes));
/*      */       }
/*  942 */       return bytes;
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
/*      */   public int size() {
/*  954 */     if (Trace.isOn) {
/*  955 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFMessage", "size()");
/*      */     }
/*  957 */     synchronized (this.parameters) {
/*  958 */       int size = this.header.size(), i = this.parameters.size();
/*      */       
/*  960 */       while (i-- > 0) {
/*  961 */         PCFParameter p = this.parameters.elementAt(i);
/*      */         
/*  963 */         size += p.size();
/*      */       } 
/*      */       
/*  966 */       if (Trace.isOn) {
/*  967 */         Trace.exit(this, "com.ibm.mq.headers.pcf.PCFMessage", "size()", Integer.valueOf(size));
/*      */       }
/*  969 */       return size;
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
/*      */   public boolean equals(Object obj) {
/*  986 */     if (Trace.isOn) {
/*  987 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFMessage", "equals(Object)", new Object[] { obj });
/*      */     }
/*  989 */     if (obj != null && obj instanceof PCFMessage) {
/*  990 */       PCFMessage other = (PCFMessage)obj;
/*      */       
/*  992 */       if (other.getParameterCount() == getParameterCount() && other.header.equals(this.header)) {
/*  993 */         Enumeration<E> otherParameters = other.getParameters();
/*      */         
/*  995 */         Enumeration parameters = getParameters();
/*  996 */         boolean match = true;
/*      */         
/*      */         try {
/*  999 */           while (match && parameters.hasMoreElements()) {
/* 1000 */             match = otherParameters.nextElement().equals(parameters.nextElement());
/*      */           
/*      */           }
/*      */         }
/* 1004 */         catch (NoSuchElementException nsee) {
/* 1005 */           if (Trace.isOn) {
/* 1006 */             Trace.catchBlock(this, "com.ibm.mq.headers.pcf.PCFMessage", "equals(Object)", nsee);
/*      */           }
/* 1008 */           if (Trace.isOn) {
/* 1009 */             Trace.exit(this, "com.ibm.mq.headers.pcf.PCFMessage", "equals(Object)", 
/* 1010 */                 Boolean.valueOf(false), 1);
/*      */           }
/* 1012 */           return false;
/*      */         } 
/*      */         
/* 1015 */         if (Trace.isOn) {
/* 1016 */           Trace.exit(this, "com.ibm.mq.headers.pcf.PCFMessage", "equals(Object)", 
/* 1017 */               Boolean.valueOf(match), 2);
/*      */         }
/* 1019 */         return match;
/*      */       } 
/* 1021 */       if (Trace.isOn) {
/* 1022 */         Trace.exit(this, "com.ibm.mq.headers.pcf.PCFMessage", "equals(Object)", 
/* 1023 */             Boolean.valueOf(false), 3);
/*      */       }
/* 1025 */       return false;
/*      */     } 
/* 1027 */     if (Trace.isOn) {
/* 1028 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFMessage", "equals(Object)", 
/* 1029 */           Boolean.valueOf(false), 4);
/*      */     }
/* 1031 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final String getUnqualifiedClassName() {
/* 1040 */     String className = getClass().getName();
/*      */     
/* 1042 */     if (className.lastIndexOf('.') > 0) {
/* 1043 */       className = className.substring(className.lastIndexOf('.') + 1);
/*      */     }
/*      */     
/* 1046 */     if (Trace.isOn) {
/* 1047 */       Trace.data(this, "com.ibm.mq.headers.pcf.PCFMessage", "getUnqualifiedClassName()", "getter", className);
/*      */     }
/*      */     
/* 1050 */     return className;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/* 1060 */     if (Trace.isOn) {
/* 1061 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFMessage", "toString()");
/*      */     }
/* 1063 */     StringBuffer sb = new StringBuffer();
/* 1064 */     sb.append(getUnqualifiedClassName());
/* 1065 */     sb.append(": \n");
/*      */     
/* 1067 */     if (this.header != null) {
/* 1068 */       sb.append(this.header.toString());
/*      */     } else {
/*      */       
/* 1071 */       sb.append("No header(yet)");
/*      */     } 
/* 1073 */     if (this.parameters != null) {
/* 1074 */       synchronized (this.parameters) {
/* 1075 */         for (int i = 0; i < this.parameters.size(); i++) {
/* 1076 */           PCFParameter p = this.parameters.elementAt(i);
/* 1077 */           sb.append('\n');
/* 1078 */           sb.append(p.toString());
/*      */         } 
/*      */       } 
/*      */     } else {
/*      */       
/* 1083 */       sb.append("\nNo parameters(yet)");
/*      */     } 
/*      */     
/* 1086 */     String traceRet1 = new String(sb);
/* 1087 */     if (Trace.isOn) {
/* 1088 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFMessage", "toString()", traceRet1);
/*      */     }
/* 1090 */     return traceRet1;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\pcf\PCFMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */