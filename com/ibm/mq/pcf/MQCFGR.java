/*      */ package com.ibm.mq.pcf;
/*      */ 
/*      */ import com.ibm.mq.MQException;
/*      */ import com.ibm.mq.MQMessage;
/*      */ import com.ibm.mq.headers.MQDataException;
/*      */ import com.ibm.mq.headers.MQHeader;
/*      */ import com.ibm.mq.headers.MQHeaderContext;
/*      */ import com.ibm.mq.headers.internal.HeaderType;
/*      */ import com.ibm.mq.headers.internal.store.ByteStore;
/*      */ import com.ibm.mq.headers.internal.store.Store;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.DataInput;
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
/*      */ @Deprecated
/*      */ public class MQCFGR
/*      */   extends PCFParameter
/*      */   implements PCFContent
/*      */ {
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/pcf/MQCFGR.java";
/*      */   @Deprecated
/*      */   public static final int SIZE = 16;
/*      */   
/*      */   static {
/*   63 */     if (Trace.isOn) {
/*   64 */       Trace.data("com.ibm.mq.pcf.MQCFGR", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/pcf/MQCFGR.java");
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
/*   83 */   static final HeaderType TYPE = new HeaderType("MQCFGR");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int type = 20;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int strucLength = 16;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int parameter;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int parameterCount;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final com.ibm.mq.headers.pcf.MQCFGR myDelegate;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int write(MQMessage message, int parameter, int parameterCount) throws IOException {
/*  122 */     if (Trace.isOn) {
/*  123 */       Trace.entry("com.ibm.mq.pcf.MQCFGR", "write(MQMessage,int,int)", new Object[] { message, 
/*  124 */             Integer.valueOf(parameter), Integer.valueOf(parameterCount) });
/*      */     }
/*  126 */     com.ibm.mq.headers.pcf.MQCFGR.write(message, parameter, parameterCount);
/*  127 */     if (Trace.isOn) {
/*  128 */       Trace.exit("com.ibm.mq.pcf.MQCFGR", "write(MQMessage,int,int)", Integer.valueOf(16));
/*      */     }
/*      */     
/*  131 */     return 16;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQCFGR() {
/*  138 */     super(TYPE);
/*  139 */     if (Trace.isOn) {
/*  140 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFGR", "<init>()");
/*      */     }
/*  142 */     this.myDelegate = (com.ibm.mq.headers.pcf.MQCFGR)this.delegate;
/*      */     
/*  144 */     if (Trace.isOn) {
/*  145 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFGR", "<init>()");
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
/*      */   public MQCFGR(MQMessage message) throws MQException, IOException {
/*  158 */     this();
/*  159 */     if (Trace.isOn) {
/*  160 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFGR", "<init>(MQMessage)", new Object[] { message });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  165 */       read((DataInput)message);
/*      */     }
/*  167 */     catch (MQDataException mde) {
/*  168 */       if (Trace.isOn) {
/*  169 */         Trace.catchBlock(this, "com.ibm.mq.pcf.MQCFGR", "<init>(MQMessage)", (Throwable)mde);
/*      */       }
/*      */       
/*  172 */       MQException traceRet1 = new MQException(mde.completionCode, mde.reasonCode, mde.exceptionSource);
/*  173 */       if (Trace.isOn) {
/*  174 */         Trace.throwing(this, "com.ibm.mq.pcf.MQCFGR", "<init>(MQMessage)", (Throwable)traceRet1);
/*      */       }
/*  176 */       throw traceRet1;
/*      */     } 
/*  178 */     readCachedContent();
/*  179 */     this.delegate.store(store());
/*  180 */     if (Trace.isOn) {
/*  181 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFGR", "<init>(MQMessage)");
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
/*      */   public MQCFGR(DataInput message, int encoding, int characterSet) throws MQException, IOException {
/*  196 */     this();
/*  197 */     if (Trace.isOn) {
/*  198 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFGR", "<init>(DataInput,int,int)", new Object[] { message, 
/*  199 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*      */     }
/*  201 */     read(message, encoding, characterSet);
/*      */     
/*  203 */     if (Trace.isOn) {
/*  204 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFGR", "<init>(DataInput,int,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int read(DataInput message, int encoding, int characterSet) throws MQException, IOException {
/*      */     int size;
/*  216 */     if (Trace.isOn) {
/*  217 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFGR", "read(DataInput,int,int)", new Object[] { message, 
/*  218 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*      */     }
/*      */     
/*      */     try {
/*  222 */       size = super.read(message, encoding, characterSet);
/*      */     }
/*  224 */     catch (MQDataException mqe) {
/*  225 */       if (Trace.isOn) {
/*  226 */         Trace.catchBlock(this, "com.ibm.mq.pcf.MQCFGR", "read(DataInput,int,int)", (Throwable)mqe, 1);
/*      */       }
/*      */       
/*  229 */       MQException traceRet1 = new MQException(mqe.completionCode, mqe.reasonCode, mqe.exceptionSource);
/*  230 */       if (Trace.isOn) {
/*  231 */         Trace.throwing(this, "com.ibm.mq.pcf.MQCFGR", "read(DataInput,int,int)", (Throwable)traceRet1, 1);
/*      */       }
/*  233 */       throw traceRet1;
/*      */     }
/*  235 */     catch (Exception e) {
/*  236 */       if (Trace.isOn) {
/*  237 */         Trace.catchBlock(this, "com.ibm.mq.pcf.MQCFGR", "read(DataInput,int,int)", e, 2);
/*      */       }
/*  239 */       RuntimeException traceRet2 = new RuntimeException(e);
/*  240 */       if (Trace.isOn) {
/*  241 */         Trace.throwing(this, "com.ibm.mq.pcf.MQCFGR", "read(DataInput,int,int)", traceRet2, 2);
/*      */       }
/*  243 */       throw traceRet2;
/*      */     } 
/*  245 */     int count = getParameterCount();
/*      */     
/*  247 */     PCFHeaderFactory factory = new PCFHeaderFactory();
/*      */     
/*  249 */     MQHeaderContext context = MQHeaderContext.createMQHeaderContext(message, "MQPCF   ", encoding, characterSet);
/*      */ 
/*      */     
/*  252 */     ByteArrayOutputStream baos = new ByteArrayOutputStream();
/*      */ 
/*      */     
/*  255 */     byte[] temp = new byte[size()];
/*  256 */     store().copyTo(temp, 0, size());
/*  257 */     baos.write(temp);
/*      */     
/*  259 */     while (count-- > 0) {
/*      */       
/*  261 */       MQHeader header = factory.decode(context);
/*      */ 
/*      */       
/*  264 */       temp = new byte[header.size()];
/*  265 */       ((PCFParameter)header).store().copyTo(temp, 0, header.size());
/*  266 */       baos.write(temp);
/*      */       
/*  268 */       addParameter((PCFParameter)header);
/*  269 */       size += header.size();
/*      */     } 
/*      */     
/*  272 */     store((Store)new ByteStore(baos.toByteArray(), store().encoding(), store().characterSet()));
/*      */     
/*  274 */     if (Trace.isOn) {
/*  275 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFGR", "read(DataInput,int,int)", Integer.valueOf(size));
/*      */     }
/*  277 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getType() {
/*  287 */     int traceRet1 = this.myDelegate.getType();
/*  288 */     if (Trace.isOn) {
/*  289 */       Trace.data(this, "com.ibm.mq.pcf.MQCFGR", "getType()", "getter", Integer.valueOf(traceRet1));
/*      */     }
/*      */     
/*  292 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getStrucLength() {
/*  301 */     int traceRet1 = this.myDelegate.getStrucLength();
/*  302 */     if (Trace.isOn) {
/*  303 */       Trace.data(this, "com.ibm.mq.pcf.MQCFGR", "getStrucLength()", "getter", 
/*  304 */           Integer.valueOf(traceRet1));
/*      */     }
/*  306 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getParameter() {
/*  316 */     int traceRet1 = this.myDelegate.getParameter();
/*  317 */     if (Trace.isOn) {
/*  318 */       Trace.data(this, "com.ibm.mq.pcf.MQCFGR", "getParameter()", "getter", 
/*  319 */           Integer.valueOf(traceRet1));
/*      */     }
/*  321 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setParameter(int value) {
/*  330 */     if (Trace.isOn) {
/*  331 */       Trace.data(this, "com.ibm.mq.pcf.MQCFGR", "setParameter(int)", "setter", 
/*  332 */           Integer.valueOf(value));
/*      */     }
/*  334 */     this.myDelegate.setParameter(this.parameter = value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getParameterCount() {
/*  344 */     int traceRet1 = this.myDelegate.getParameterCount();
/*  345 */     if (Trace.isOn) {
/*  346 */       Trace.data(this, "com.ibm.mq.pcf.MQCFGR", "getParameterCount()", "getter", 
/*  347 */           Integer.valueOf(traceRet1));
/*      */     }
/*  349 */     return traceRet1;
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
/*  361 */     if (Trace.isOn) {
/*  362 */       Trace.data(this, "com.ibm.mq.pcf.MQCFGR", "setParameterCount(int)", "setter", 
/*  363 */           Integer.valueOf(value));
/*      */     }
/*  365 */     this.parameterCount = this.myDelegate.setParameterCount(value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getValue() {
/*  374 */     Object traceRet1 = Integer.valueOf(getParameterCount());
/*  375 */     if (Trace.isOn) {
/*  376 */       Trace.data(this, "com.ibm.mq.pcf.MQCFGR", "getValue()", "getter", traceRet1);
/*      */     }
/*  378 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getStringValue() {
/*  387 */     String traceRet1 = Integer.toString(getParameterCount());
/*  388 */     if (Trace.isOn) {
/*  389 */       Trace.data(this, "com.ibm.mq.pcf.MQCFGR", "getStringValue()", "getter", traceRet1);
/*      */     }
/*  391 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setValue(Object value) {
/*  399 */     if (Trace.isOn) {
/*  400 */       Trace.data(this, "com.ibm.mq.pcf.MQCFGR", "setValue(Object)", "setter", value);
/*      */     }
/*  402 */     this.myDelegate.setValue(value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addParameter(PCFParameter parameter) {
/*  410 */     if (Trace.isOn) {
/*  411 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFGR", "addParameter(PCFParameter)", new Object[] { parameter });
/*      */     }
/*      */     
/*  414 */     this.myDelegate.addParameter(parameter);
/*      */     
/*  416 */     if (Trace.isOn) {
/*  417 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFGR", "addParameter(PCFParameter)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addParameter(int parameter, int value) {
/*  427 */     if (Trace.isOn)
/*  428 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFGR", "addParameter(int,int)", new Object[] {
/*  429 */             Integer.valueOf(parameter), Integer.valueOf(value)
/*      */           }); 
/*  431 */     addParameter(new MQCFIN(parameter, value));
/*      */     
/*  433 */     if (Trace.isOn) {
/*  434 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFGR", "addParameter(int,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addParameter(int parameter, int[] values) {
/*  444 */     if (Trace.isOn)
/*  445 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFGR", "addParameter(int,int [ ])", new Object[] {
/*  446 */             Integer.valueOf(parameter), values
/*      */           }); 
/*  448 */     addParameter(new MQCFIL(parameter, values));
/*      */     
/*  450 */     if (Trace.isOn) {
/*  451 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFGR", "addParameter(int,int [ ])");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addParameter(int parameter, long value) {
/*  461 */     if (Trace.isOn)
/*  462 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFGR", "addParameter(int,long)", new Object[] {
/*  463 */             Integer.valueOf(parameter), Long.valueOf(value)
/*      */           }); 
/*  465 */     addParameter(new MQCFIN64(parameter, value));
/*      */     
/*  467 */     if (Trace.isOn) {
/*  468 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFGR", "addParameter(int,long)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addParameter(int parameter, long[] values) {
/*  478 */     if (Trace.isOn)
/*  479 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFGR", "addParameter(int,long [ ])", new Object[] {
/*  480 */             Integer.valueOf(parameter), values
/*      */           }); 
/*  482 */     addParameter(new MQCFIL64(parameter, values));
/*      */     
/*  484 */     if (Trace.isOn) {
/*  485 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFGR", "addParameter(int,long [ ])");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addParameter(int parameter, String value) {
/*  495 */     if (Trace.isOn)
/*  496 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFGR", "addParameter(int,String)", new Object[] {
/*  497 */             Integer.valueOf(parameter), value
/*      */           }); 
/*  499 */     addParameter(new MQCFST(parameter, value));
/*      */     
/*  501 */     if (Trace.isOn) {
/*  502 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFGR", "addParameter(int,String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addParameter(int parameter, String[] values) {
/*  512 */     if (Trace.isOn)
/*  513 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFGR", "addParameter(int,String [ ])", new Object[] {
/*  514 */             Integer.valueOf(parameter), values
/*      */           }); 
/*  516 */     addParameter(new MQCFSL(parameter, values));
/*      */     
/*  518 */     if (Trace.isOn) {
/*  519 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFGR", "addParameter(int,String [ ])");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addParameter(int parameter, byte[] value) {
/*  529 */     if (Trace.isOn)
/*  530 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFGR", "addParameter(int,byte [ ])", new Object[] {
/*  531 */             Integer.valueOf(parameter), value
/*      */           }); 
/*  533 */     addParameter(new MQCFBS(parameter, value));
/*      */     
/*  535 */     if (Trace.isOn) {
/*  536 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFGR", "addParameter(int,byte [ ])");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addFilterParameter(int parameter, int operator, int value) {
/*  546 */     if (Trace.isOn)
/*  547 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFGR", "addFilterParameter(int,int,int)", new Object[] {
/*  548 */             Integer.valueOf(parameter), Integer.valueOf(operator), Integer.valueOf(value)
/*      */           }); 
/*  550 */     addParameter(new MQCFIF(parameter, operator, value));
/*      */     
/*  552 */     if (Trace.isOn) {
/*  553 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFGR", "addFilterParameter(int,int,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addFilterParameter(int parameter, int operator, String value) {
/*  563 */     if (Trace.isOn)
/*  564 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFGR", "addFilterParameter(int,int,String)", new Object[] {
/*  565 */             Integer.valueOf(parameter), Integer.valueOf(operator), value
/*      */           }); 
/*  567 */     addParameter(new MQCFSF(parameter, operator, value));
/*      */     
/*  569 */     if (Trace.isOn) {
/*  570 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFGR", "addFilterParameter(int,int,String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addFilterParameter(int parameter, int operator, byte[] value) {
/*  580 */     if (Trace.isOn)
/*  581 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFGR", "addFilterParameter(int,int,byte [ ])", new Object[] {
/*  582 */             Integer.valueOf(parameter), Integer.valueOf(operator), value
/*      */           }); 
/*  584 */     addParameter(new MQCFBF(parameter, operator, value));
/*      */     
/*  586 */     if (Trace.isOn) {
/*  587 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFGR", "addFilterParameter(int,int,byte [ ])");
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
/*  599 */     Enumeration traceRet1 = this.myDelegate.getParameters();
/*  600 */     if (Trace.isOn) {
/*  601 */       Trace.data(this, "com.ibm.mq.pcf.MQCFGR", "getParameters()", "getter", traceRet1);
/*      */     }
/*  603 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PCFParameter getParameter(int parameter) {
/*  612 */     if (Trace.isOn) {
/*  613 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFGR", "getParameter(int)", new Object[] {
/*  614 */             Integer.valueOf(parameter)
/*      */           });
/*      */     }
/*      */     
/*  618 */     Vector<PCFParameter> parameters = this.myDelegate.getParameterVector();
/*  619 */     PCFParameter match = null;
/*      */     
/*  621 */     synchronized (parameters) {
/*  622 */       int i = parameters.size();
/*      */       
/*  624 */       while (i-- > 0 && match == null) {
/*  625 */         PCFParameter p = parameters.elementAt(i);
/*      */         
/*  627 */         if (parameter == p.getParameter()) {
/*  628 */           match = p;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  633 */     if (Trace.isOn) {
/*  634 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFGR", "getParameter(int)", match);
/*      */     }
/*  636 */     return match;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getParameterValue(int parameter) {
/*  644 */     if (Trace.isOn)
/*  645 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFGR", "getParameterValue(int)", new Object[] {
/*  646 */             Integer.valueOf(parameter)
/*      */           }); 
/*  648 */     PCFParameter p = getParameter(parameter);
/*      */     
/*  650 */     Object traceRet1 = (p == null) ? null : p.getValue();
/*      */     
/*  652 */     if (Trace.isOn) {
/*  653 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFGR", "getParameterValue(int)", traceRet1);
/*      */     }
/*  655 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getIntParameterValue(int parameter) throws PCFException {
/*  663 */     if (Trace.isOn)
/*  664 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFGR", "getIntParameterValue(int)", new Object[] {
/*  665 */             Integer.valueOf(parameter)
/*      */           }); 
/*  667 */     Integer value = (Integer)getParameterValue(parameter);
/*      */     
/*  669 */     if (value == null) {
/*  670 */       PCFException traceRet1 = new PCFException(2, 3014, this);
/*      */       
/*  672 */       if (Trace.isOn) {
/*  673 */         Trace.throwing(this, "com.ibm.mq.pcf.MQCFGR", "getIntParameterValue(int)", (Throwable)traceRet1);
/*      */       }
/*  675 */       throw traceRet1;
/*      */     } 
/*      */     
/*  678 */     int traceRet2 = value.intValue();
/*      */     
/*  680 */     if (Trace.isOn) {
/*  681 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFGR", "getIntParameterValue(int)", 
/*  682 */           Integer.valueOf(traceRet2));
/*      */     }
/*  684 */     return traceRet2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int[] getIntListParameterValue(int parameter) throws PCFException {
/*  692 */     if (Trace.isOn)
/*  693 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFGR", "getIntListParameterValue(int)", new Object[] {
/*  694 */             Integer.valueOf(parameter)
/*      */           }); 
/*  696 */     int[] values = (int[])getParameterValue(parameter);
/*      */     
/*  698 */     if (values == null) {
/*  699 */       PCFException traceRet1 = new PCFException(2, 3047, this);
/*      */       
/*  701 */       if (Trace.isOn) {
/*  702 */         Trace.throwing(this, "com.ibm.mq.pcf.MQCFGR", "getIntListParameterValue(int)", (Throwable)traceRet1);
/*      */       }
/*  704 */       throw traceRet1;
/*      */     } 
/*      */     
/*  707 */     if (Trace.isOn) {
/*  708 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFGR", "getIntListParameterValue(int)", values);
/*      */     }
/*  710 */     return values;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getInt64ParameterValue(int parameter) throws PCFException {
/*  718 */     if (Trace.isOn)
/*  719 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFGR", "getInt64ParameterValue(int)", new Object[] {
/*  720 */             Integer.valueOf(parameter)
/*      */           }); 
/*  722 */     Long value = (Long)getParameterValue(parameter);
/*      */     
/*  724 */     if (value == null) {
/*  725 */       PCFException traceRet1 = new PCFException(2, 3014, this);
/*      */       
/*  727 */       if (Trace.isOn) {
/*  728 */         Trace.throwing(this, "com.ibm.mq.pcf.MQCFGR", "getInt64ParameterValue(int)", (Throwable)traceRet1);
/*      */       }
/*  730 */       throw traceRet1;
/*      */     } 
/*      */     
/*  733 */     long traceRet2 = value.longValue();
/*      */     
/*  735 */     if (Trace.isOn) {
/*  736 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFGR", "getInt64ParameterValue(int)", 
/*  737 */           Long.valueOf(traceRet2));
/*      */     }
/*  739 */     return traceRet2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long[] getIntList64ParameterValue(int parameter) throws PCFException {
/*  747 */     if (Trace.isOn)
/*  748 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFGR", "getIntList64ParameterValue(int)", new Object[] {
/*  749 */             Integer.valueOf(parameter)
/*      */           }); 
/*  751 */     long[] values = (long[])getParameterValue(parameter);
/*      */     
/*  753 */     if (values == null) {
/*  754 */       PCFException traceRet1 = new PCFException(2, 3047, this);
/*      */       
/*  756 */       if (Trace.isOn) {
/*  757 */         Trace.throwing(this, "com.ibm.mq.pcf.MQCFGR", "getIntList64ParameterValue(int)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  760 */       throw traceRet1;
/*      */     } 
/*      */     
/*  763 */     if (Trace.isOn) {
/*  764 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFGR", "getIntList64ParameterValue(int)", values);
/*      */     }
/*  766 */     return values;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getStringParameterValue(int parameter) throws PCFException {
/*  774 */     if (Trace.isOn)
/*  775 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFGR", "getStringParameterValue(int)", new Object[] {
/*  776 */             Integer.valueOf(parameter)
/*      */           }); 
/*  778 */     String value = (String)getParameterValue(parameter);
/*      */     
/*  780 */     if (value == null) {
/*  781 */       PCFException traceRet1 = new PCFException(2, 3015, this);
/*      */       
/*  783 */       if (Trace.isOn) {
/*  784 */         Trace.throwing(this, "com.ibm.mq.pcf.MQCFGR", "getStringParameterValue(int)", (Throwable)traceRet1);
/*      */       }
/*  786 */       throw traceRet1;
/*      */     } 
/*      */     
/*  789 */     if (Trace.isOn) {
/*  790 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFGR", "getStringParameterValue(int)", value);
/*      */     }
/*  792 */     return value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String[] getStringListParameterValue(int parameter) throws PCFException {
/*  800 */     if (Trace.isOn)
/*  801 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFGR", "getStringListParameterValue(int)", new Object[] {
/*  802 */             Integer.valueOf(parameter)
/*      */           }); 
/*  804 */     String[] values = (String[])getParameterValue(parameter);
/*      */     
/*  806 */     if (values == null) {
/*  807 */       PCFException traceRet1 = new PCFException(2, 3033, this);
/*      */       
/*  809 */       if (Trace.isOn) {
/*  810 */         Trace.throwing(this, "com.ibm.mq.pcf.MQCFGR", "getStringListParameterValue(int)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  813 */       throw traceRet1;
/*      */     } 
/*      */     
/*  816 */     if (Trace.isOn) {
/*  817 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFGR", "getStringListParameterValue(int)", values);
/*      */     }
/*  819 */     return values;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getBytesParameterValue(int parameter) throws PCFException {
/*  827 */     if (Trace.isOn)
/*  828 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFGR", "getBytesParameterValue(int)", new Object[] {
/*  829 */             Integer.valueOf(parameter)
/*      */           }); 
/*  831 */     byte[] value = (byte[])getParameterValue(parameter);
/*      */     
/*  833 */     if (value == null) {
/*  834 */       PCFException traceRet1 = new PCFException(2, 3256, this);
/*      */       
/*  836 */       if (Trace.isOn) {
/*  837 */         Trace.throwing(this, "com.ibm.mq.pcf.MQCFGR", "getBytesParameterValue(int)", (Throwable)traceRet1);
/*      */       }
/*  839 */       throw traceRet1;
/*      */     } 
/*      */     
/*  842 */     if (Trace.isOn) {
/*  843 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFGR", "getBytesParameterValue(int)", value);
/*      */     }
/*  845 */     return value;
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
/*  858 */     if (Trace.isOn) {
/*  859 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFGR", "equals(Object)", new Object[] { obj });
/*      */     }
/*  861 */     if (obj != null && obj instanceof MQCFGR) {
/*  862 */       MQCFGR other = (MQCFGR)obj;
/*      */       
/*  864 */       if (other.parameter == this.parameter && other.parameterCount == this.parameterCount) {
/*  865 */         Enumeration<E> otherParameters = other.getParameters();
/*  866 */         Enumeration parameters = getParameters();
/*  867 */         boolean match = true;
/*      */         
/*      */         try {
/*  870 */           while (match && parameters.hasMoreElements()) {
/*  871 */             match = otherParameters.nextElement().equals(parameters.nextElement());
/*      */           
/*      */           }
/*      */         }
/*  875 */         catch (NoSuchElementException nsee) {
/*  876 */           if (Trace.isOn) {
/*  877 */             Trace.catchBlock(this, "com.ibm.mq.pcf.MQCFGR", "equals(Object)", nsee);
/*      */           }
/*  879 */           if (Trace.isOn) {
/*  880 */             Trace.exit(this, "com.ibm.mq.pcf.MQCFGR", "equals(Object)", Boolean.valueOf(false), 1);
/*      */           }
/*      */           
/*  883 */           return false;
/*      */         } 
/*      */         
/*  886 */         if (Trace.isOn) {
/*  887 */           Trace.exit(this, "com.ibm.mq.pcf.MQCFGR", "equals(Object)", Boolean.valueOf(match), 2);
/*      */         }
/*  889 */         return match;
/*      */       } 
/*      */     } 
/*  892 */     if (Trace.isOn) {
/*  893 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFGR", "equals(Object)", Boolean.valueOf(false), 3);
/*      */     }
/*  895 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int hashCode() {
/*  903 */     if (Trace.isOn) {
/*  904 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFGR", "hashCode()");
/*      */     }
/*  906 */     int hashCode = 0;
/*  907 */     hashCode += getParameter();
/*      */     
/*  909 */     Enumeration<E> parameters = getParameters();
/*  910 */     while (parameters.hasMoreElements()) {
/*  911 */       hashCode += 31 * parameters.nextElement().hashCode();
/*      */     }
/*      */     
/*  914 */     if (Trace.isOn) {
/*  915 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFGR", "hashCode()", Integer.valueOf(hashCode));
/*      */     }
/*  917 */     return hashCode;
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
/*      */   public void readCachedContent() throws MQException, IOException {
/*  930 */     if (Trace.isOn) {
/*  931 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFGR", "readCachedContent()");
/*      */     }
/*  933 */     Vector<PCFParameter> parameters = this.myDelegate.getParameterVector();
/*  934 */     this.parameter = getParameter();
/*  935 */     this.parameterCount = getParameterCount();
/*      */     
/*  937 */     synchronized (parameters) {
/*  938 */       int count = parameters.size();
/*      */       
/*  940 */       while (count-- > 0) {
/*      */         try {
/*  942 */           ((PCFParameter)parameters.get(count)).readCachedContent();
/*      */         }
/*  944 */         catch (MQException e) {
/*  945 */           if (Trace.isOn) {
/*  946 */             Trace.catchBlock(this, "com.ibm.mq.pcf.MQCFGR", "readCachedContent()", (Throwable)e, 1);
/*      */           }
/*      */           
/*  949 */           if (Trace.isOn) {
/*  950 */             Trace.throwing(this, "com.ibm.mq.pcf.MQCFGR", "readCachedContent()", (Throwable)e, 1);
/*      */           }
/*  952 */           throw e;
/*      */         }
/*  954 */         catch (IOException e) {
/*  955 */           if (Trace.isOn) {
/*  956 */             Trace.catchBlock(this, "com.ibm.mq.pcf.MQCFGR", "readCachedContent()", e, 2);
/*      */           }
/*  958 */           if (Trace.isOn) {
/*  959 */             Trace.throwing(this, "com.ibm.mq.pcf.MQCFGR", "readCachedContent()", e, 2);
/*      */           }
/*  961 */           throw e;
/*      */         }
/*  963 */         catch (Exception e) {
/*  964 */           if (Trace.isOn) {
/*  965 */             Trace.catchBlock(this, "com.ibm.mq.pcf.MQCFGR", "readCachedContent()", e, 3);
/*      */           }
/*  967 */           RuntimeException traceRet1 = new RuntimeException(e);
/*  968 */           if (Trace.isOn) {
/*  969 */             Trace.throwing(this, "com.ibm.mq.pcf.MQCFGR", "readCachedContent()", traceRet1, 3);
/*      */           }
/*  971 */           throw traceRet1;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  976 */     if (Trace.isOn) {
/*  977 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFGR", "readCachedContent()");
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
/*      */   public void discardCachedContent() {
/*  989 */     if (Trace.isOn) {
/*  990 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFGR", "discardCachedContent()");
/*      */     }
/*  992 */     this.parameter = 0;
/*  993 */     this.parameterCount = 0;
/*      */     
/*  995 */     if (Trace.isOn) {
/*  996 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFGR", "discardCachedContent()");
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
/*      */   public void writeCachedContent() throws IOException {
/* 1009 */     if (Trace.isOn) {
/* 1010 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFGR", "writeCachedContent()");
/*      */     }
/* 1012 */     setParameter(this.parameter);
/* 1013 */     setParameterCount(this.parameterCount);
/*      */     
/* 1015 */     if (Trace.isOn) {
/* 1016 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFGR", "writeCachedContent()");
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
/*      */   public int getHeaderVersion() {
/* 1028 */     if (Trace.isOn) {
/* 1029 */       Trace.data(this, "com.ibm.mq.pcf.MQCFGR", "getHeaderVersion()", "getter", 
/* 1030 */           Integer.valueOf(3));
/*      */     }
/* 1032 */     return 3;
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
/*      */   public String toString() {
/* 1045 */     if (Trace.isOn) {
/* 1046 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFGR", "toString()");
/*      */     }
/* 1048 */     if (this.myDelegate == null) {
/* 1049 */       if (Trace.isOn) {
/* 1050 */         Trace.exit(this, "com.ibm.mq.pcf.MQCFGR", "toString()", "Uninitialised MGCFGR", 1);
/*      */       }
/* 1052 */       return "Uninitialised MGCFGR";
/*      */     } 
/* 1054 */     Vector parameters = this.myDelegate.getParameterVector();
/* 1055 */     StringBuffer sb = new StringBuffer(super.toString());
/*      */     
/* 1057 */     sb.append(" {");
/*      */     
/* 1059 */     if (parameters != null) {
/* 1060 */       for (int i = 0; i < parameters.size(); i++) {
/* 1061 */         Object p = parameters.elementAt(i);
/* 1062 */         sb.append('\n');
/* 1063 */         sb.append(p.toString());
/*      */       } 
/*      */     }
/* 1066 */     sb.append("}");
/*      */     
/* 1068 */     String traceRet1 = new String(sb);
/* 1069 */     if (Trace.isOn) {
/* 1070 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFGR", "toString()", traceRet1, 2);
/*      */     }
/* 1072 */     return traceRet1;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\pcf\MQCFGR.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */