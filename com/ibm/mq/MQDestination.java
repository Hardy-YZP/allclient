/*      */ package com.ibm.mq;
/*      */ 
/*      */ import com.ibm.mq.jmqi.JmqiException;
/*      */ import com.ibm.mq.jmqi.JmqiMQ;
/*      */ import com.ibm.mq.jmqi.MQGMO;
/*      */ import com.ibm.mq.jmqi.MQMD;
/*      */ import com.ibm.mq.jmqi.MQOD;
/*      */ import com.ibm.mq.jmqi.MQPMO;
/*      */ import com.ibm.mq.jmqi.handles.PbyteBuffer;
/*      */ import com.ibm.mq.jmqi.internal.Configuration;
/*      */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*      */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*      */ import com.ibm.mq.jmqi.system.JmqiSP;
/*      */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*      */ import com.ibm.mq.jmqi.system.JmqiTls;
/*      */ import com.ibm.mq.jmqi.system.SpiOpenOptions;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.util.GregorianCalendar;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class MQDestination
/*      */   extends MQManagedObject
/*      */ {
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQDestination.java";
/*      */   
/*      */   static {
/*   59 */     if (Trace.isOn) {
/*   60 */       Trace.data("com.ibm.mq.MQDestination", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQDestination.java");
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
/*   72 */   private Pint pMsgTooSmallForBufferCount = new Pint(0);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   77 */   private PbyteBuffer pByteBuffer = this.env.newPbyteBuffer();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int destinationType;
/*      */ 
/*      */ 
/*      */   
/*   86 */   protected Pint completionCode = new Pint();
/*   87 */   protected Pint reason = new Pint();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   94 */   protected int propControl = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean cfgPropControl = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int queueManagerCmdLevel;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected MQDestination(int destType) {
/*  111 */     if (Trace.isOn) {
/*  112 */       Trace.entry(this, "com.ibm.mq.MQDestination", "<init>(int)", new Object[] {
/*  113 */             Integer.valueOf(destType)
/*      */           });
/*      */     }
/*  116 */     if (Trace.isOn) {
/*  117 */       Trace.data(this, "<init>(int)", "sccsid = ", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQDestination.java");
/*      */     }
/*      */     
/*  120 */     this.destinationType = destType;
/*      */     
/*  122 */     if (Trace.isOn) {
/*  123 */       Trace.exit(this, "com.ibm.mq.MQDestination", "<init>(int)");
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
/*      */   protected MQDestination(int destType, MQQueueManager qMgr, String objectName, int options, String altUserId) throws MQException {
/*  144 */     this(destType);
/*  145 */     if (Trace.isOn) {
/*  146 */       Trace.entry(this, "com.ibm.mq.MQDestination", "<init>(int,MQQueueManager,String,int,String)", new Object[] {
/*  147 */             Integer.valueOf(destType), qMgr, objectName, Integer.valueOf(options), altUserId
/*      */           });
/*      */     }
/*      */     
/*  151 */     if (qMgr == null) {
/*  152 */       MQException exception = new MQException(2, 2018, this, "MQJI001");
/*      */       
/*  154 */       if (Trace.isOn) {
/*  155 */         Trace.throwing(this, "com.ibm.mq.MQDestination", "<init>(int,MQQueueManager,String,int,String)", exception, 1);
/*      */       }
/*      */       
/*  158 */       throw exception;
/*      */     } 
/*  160 */     if (!qMgr.connected) {
/*  161 */       MQException exception = new MQException(2, 2018, this, "MQJI002");
/*  162 */       if (Trace.isOn) {
/*  163 */         Trace.throwing(this, "com.ibm.mq.MQDestination", "<init>(int,MQQueueManager,String,int,String)", exception, 2);
/*      */       }
/*      */       
/*  166 */       throw exception;
/*      */     } 
/*      */ 
/*      */     
/*  170 */     this.parentQmgr = qMgr;
/*  171 */     this.Hconn = qMgr.Hconn;
/*  172 */     this.connected = qMgr.connected;
/*  173 */     this.osession = qMgr.osession;
/*  174 */     setConnectionReference(qMgr);
/*  175 */     setName(objectName);
/*  176 */     setOpenOptions(options);
/*  177 */     setAlternateUserId(altUserId);
/*  178 */     if (Trace.isOn) {
/*  179 */       Trace.exit(this, "com.ibm.mq.MQDestination", "<init>(int,MQQueueManager,String,int,String)");
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
/*      */   protected MQOD createMQOD() throws MQException {
/*  193 */     if (Trace.isOn) {
/*  194 */       Trace.entry(this, "com.ibm.mq.MQDestination", "createMQOD()");
/*      */     }
/*      */     
/*  197 */     MQOD mqObjectDescriptor = this.env.newMQOD();
/*      */     
/*  199 */     mqObjectDescriptor.setObjectType(this.destinationType);
/*      */     
/*  201 */     String objectName = getName();
/*  202 */     if (isValidStringParameter(objectName)) {
/*  203 */       mqObjectDescriptor.setObjectName(objectName);
/*      */     } else {
/*      */       
/*  206 */       objectName = mqObjectDescriptor.getObjectName();
/*  207 */       setName(objectName);
/*      */     } 
/*      */     
/*  210 */     String altUserId = getAlternateUserId();
/*  211 */     if (isValidStringParameter(altUserId)) {
/*  212 */       mqObjectDescriptor.setAlternateUserId(altUserId);
/*      */     } else {
/*      */       
/*  215 */       altUserId = mqObjectDescriptor.getAlternateUserId();
/*  216 */       setAlternateUserId(altUserId);
/*      */     } 
/*      */     
/*  219 */     mqObjectDescriptor.getResObjectString().setVsBufSize(10240);
/*  220 */     mqObjectDescriptor.getSelectionString().setVsBufSize(10240);
/*      */     
/*  222 */     if (Trace.isOn) {
/*  223 */       Trace.exit(this, "com.ibm.mq.MQDestination", "createMQOD()", mqObjectDescriptor);
/*      */     }
/*  225 */     return mqObjectDescriptor;
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
/*      */   protected boolean isValidParameter(Object parameter) {
/*  237 */     if (Trace.isOn) {
/*  238 */       Trace.entry(this, "com.ibm.mq.MQDestination", "isValidParameter(Object)", new Object[] { parameter });
/*      */     }
/*      */     
/*  241 */     boolean valid = (parameter != null);
/*      */     
/*  243 */     if (Trace.isOn) {
/*  244 */       Trace.exit(this, "com.ibm.mq.MQDestination", "isValidParameter(Object)", 
/*  245 */           Boolean.valueOf(valid));
/*      */     }
/*  247 */     return valid;
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
/*      */   protected boolean isValidStringParameter(String parameter) {
/*  260 */     if (Trace.isOn) {
/*  261 */       Trace.entry(this, "com.ibm.mq.MQDestination", "isValidStringParameter(String)", new Object[] { parameter });
/*      */     }
/*      */     
/*  264 */     boolean valid = (isValidParameter(parameter) && parameter.length() > 0);
/*      */     
/*  266 */     if (Trace.isOn) {
/*  267 */       Trace.exit(this, "com.ibm.mq.MQDestination", "isValidStringParameter(String)", 
/*  268 */           Boolean.valueOf(valid));
/*      */     }
/*  270 */     return valid;
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
/*      */   protected void open(MQOD mqObjectDescriptor) throws MQException {
/*  282 */     if (Trace.isOn) {
/*  283 */       Trace.entry(this, "com.ibm.mq.MQDestination", "open(MQOD)", new Object[] { mqObjectDescriptor });
/*      */     }
/*      */     
/*  286 */     Pint cc = new Pint();
/*  287 */     Pint rc = new Pint();
/*  288 */     JmqiMQ jmqi = this.osession.getJmqi();
/*  289 */     int options = getOpenOptions();
/*      */     try {
/*  291 */       this.queueManagerCmdLevel = this.Hconn.getHconn().getCmdLevel();
/*      */     }
/*  293 */     catch (JmqiException e) {
/*  294 */       if (Trace.isOn) {
/*  295 */         Trace.catchBlock(this, "com.ibm.mq.MQDestination", "open(MQOD)", (Throwable)e);
/*      */       }
/*      */       
/*  298 */       MQException traceRet1 = new MQException(e.getCompCode(), e.getReason(), this, e);
/*  299 */       if (Trace.isOn) {
/*  300 */         Trace.throwing(this, "com.ibm.mq.MQDestination", "open(MQOD)", traceRet1, 1);
/*      */       }
/*  302 */       throw traceRet1;
/*      */     } 
/*      */     
/*  305 */     if (this.queueManagerCmdLevel >= 700) {
/*  306 */       JmqiSP jmqiSP = (JmqiSP)jmqi;
/*  307 */       mqObjectDescriptor.setVersion(4);
/*  308 */       SpiOpenOptions openOptions = ((JmqiSystemEnvironment)this.env).newSpiOpenOptions();
/*  309 */       openOptions.setOptions(options);
/*      */       
/*  311 */       jmqiSP.spiOpen(this.Hconn.getHconn(), mqObjectDescriptor, openOptions, this.Hobj, cc, rc);
/*  312 */       this.propControl = openOptions.getPropertyControl();
/*  313 */       this.cfgPropControl = this.env.getConfiguration().getBoolValue(Configuration.MESSAGEPROPERTIES_FORCE_RFH2);
/*      */     } else {
/*      */       
/*  316 */       jmqi.MQOPEN(this.Hconn.getHconn(), mqObjectDescriptor, options, this.Hobj, cc, rc);
/*      */     } 
/*      */ 
/*      */     
/*  320 */     if (cc.x != 0 || rc.x != 0) {
/*  321 */       this.resourceOpen = false;
/*  322 */       MQException exception = new MQException(cc.x, rc.x, this, this.osession.getLastJmqiException());
/*  323 */       this.parentQmgr.errorOccurred(exception);
/*  324 */       if (Trace.isOn) {
/*  325 */         Trace.throwing(this, "com.ibm.mq.MQDestination", "open(MQOD)", exception, 2);
/*      */       }
/*  327 */       throw exception;
/*      */     } 
/*      */     
/*  330 */     setResolvedType(mqObjectDescriptor.getResolvedType());
/*  331 */     setResolvedQName(mqObjectDescriptor.getResolvedQName());
/*  332 */     setResolvedObjectString(mqObjectDescriptor.getResObjectString().getVsString());
/*      */     
/*  334 */     this.resourceOpen = true;
/*  335 */     setOpen(true);
/*      */ 
/*      */     
/*  338 */     String newName = mqObjectDescriptor.getObjectName();
/*  339 */     setName(newName);
/*      */     
/*  341 */     if (Trace.isOn) {
/*  342 */       Trace.data(this, "open(MQOD)", "Opened destination name = ", getName());
/*      */     }
/*  344 */     if (Trace.isOn) {
/*  345 */       Trace.exit(this, "com.ibm.mq.MQDestination", "open(MQOD)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void validateDestination() throws MQException {
/*  356 */     if (Trace.isOn) {
/*  357 */       Trace.entry(this, "com.ibm.mq.MQDestination", "validateDestination()");
/*      */     }
/*  359 */     if (this.osession == null && 
/*  360 */       this.parentQmgr != null) {
/*  361 */       this.osession = this.parentQmgr.getSession();
/*      */     }
/*      */     
/*  364 */     if (!this.connected || this.osession == null) {
/*  365 */       MQException exception = new MQException(2, 2018, this, "MQJI002");
/*      */       
/*  367 */       if (Trace.isOn) {
/*  368 */         Trace.throwing(this, "com.ibm.mq.MQDestination", "validateDestination()", exception, 1);
/*      */       }
/*  370 */       throw exception;
/*      */     } 
/*  372 */     if (!this.resourceOpen) {
/*  373 */       MQException exception = new MQException(2, 2019, this, "MQJI027");
/*  374 */       if (Trace.isOn) {
/*  375 */         Trace.throwing(this, "com.ibm.mq.MQDestination", "validateDestination()", exception, 2);
/*      */       }
/*  377 */       throw exception;
/*      */     } 
/*  379 */     if (Trace.isOn) {
/*  380 */       Trace.exit(this, "com.ibm.mq.MQDestination", "validateDestination()");
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
/*      */   public void get(MQMessage message) throws MQException {
/*  403 */     if (Trace.isOn) {
/*  404 */       Trace.entry(this, "com.ibm.mq.MQDestination", "get(MQMessage)", new Object[] { message });
/*      */     }
/*      */     try {
/*  407 */       get(message, new MQGetMessageOptions());
/*      */     } finally {
/*  409 */       if (Trace.isOn) {
/*  410 */         Trace.exit(this, "com.ibm.mq.MQDestination", "get(MQMessage)");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void get(MQMessage message, MQGetMessageOptions getMessageOptions) throws MQException {
/*  433 */     if (Trace.isOn) {
/*  434 */       Trace.entry(this, "com.ibm.mq.MQDestination", "get(MQMessage, MQGetMessageOptions)", new Object[] { message, getMessageOptions });
/*      */     }
/*      */ 
/*      */     
/*  438 */     int expectedMsgLength = -1;
/*  439 */     int maxMsgLength = Integer.MAX_VALUE;
/*  440 */     if (message != null) {
/*      */       
/*  442 */       int bufferSizeHint = message.getBufferSizeHint();
/*  443 */       if (bufferSizeHint != -1) {
/*  444 */         expectedMsgLength = bufferSizeHint;
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  452 */       getInt(message, getMessageOptions, expectedMsgLength, maxMsgLength, false);
/*      */     } finally {
/*  454 */       if (Trace.isOn) {
/*  455 */         Trace.exit(this, "com.ibm.mq.MQDestination", "get(MQMessage, MQGetMessageOptions)");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void get(MQMessage message, MQGetMessageOptions getMessageOptions, int maxMsgSize) throws MQException {
/*  491 */     if (Trace.isOn) {
/*  492 */       Trace.entry(this, "com.ibm.mq.MQDestination", "get(MQMessage, MQGetMessageOptions,int)", new Object[] { message, getMessageOptions, 
/*      */             
/*  494 */             Integer.valueOf(maxMsgSize) });
/*      */     }
/*      */ 
/*      */     
/*  498 */     int expectedMsgLength = -1;
/*  499 */     int maxMsgLength = maxMsgSize;
/*  500 */     if (getMessageOptions != null)
/*      */     {
/*      */       
/*  503 */       if ((getMessageOptions.options & 0x40) == 64) {
/*  504 */         expectedMsgLength = maxMsgLength;
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  512 */       getInt(message, getMessageOptions, expectedMsgLength, maxMsgLength, true);
/*      */     } finally {
/*  514 */       if (Trace.isOn) {
/*  515 */         Trace.exit(this, "com.ibm.mq.MQDestination", "get(MQMessage,MQGetMessageOptions,int)");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void getInt(MQMessage message, MQGetMessageOptions getMessageOptions, int expectedMsgLength, int maxMsgLength, boolean userMaxMsgSize) throws MQException {
/*  555 */     if (Trace.isOn) {
/*  556 */       Trace.entry(this, "com.ibm.mq.MQDestination", "getInt(MQMessage,MQGetMessageOptions,int,int,boolean)", new Object[] { message, getMessageOptions, 
/*      */             
/*  558 */             Integer.valueOf(expectedMsgLength), 
/*  559 */             Integer.valueOf(maxMsgLength), Boolean.valueOf(userMaxMsgSize) });
/*      */     }
/*  561 */     boolean propGmoAsQDef = true;
/*      */     
/*  563 */     validateGetParameters(message, getMessageOptions, maxMsgLength);
/*      */     
/*  565 */     validateDestination();
/*  566 */     int originalGmoOpts = getMessageOptions.options;
/*      */ 
/*      */     
/*  569 */     JmqiSystemEnvironment jmqiSystemEnv = (JmqiSystemEnvironment)MQSESSION.getJmqiEnv();
/*  570 */     JmqiTls jmqiTls = jmqiSystemEnv.getJmqiTls(null);
/*      */     
/*      */     try {
/*  573 */       MQException exception = null;
/*      */       
/*  575 */       synchronized (this) {
/*  576 */         if ((originalGmoOpts & 0x400) == 0 && 
/*  577 */           this.queueManagerCmdLevel >= 700) {
/*  578 */           propGmoAsQDef = ((originalGmoOpts & 0x1E000000) == 0);
/*  579 */           if ((originalGmoOpts & 0x8000000) != 0 || propGmoAsQDef) {
/*  580 */             getMessageOptions.options &= 0xF7FFFFFF;
/*  581 */             getMessageOptions.options |= 0x2000000;
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/*  586 */         JmqiMQ jmqi = this.osession.getJmqi();
/*      */         
/*  588 */         getMessageOptions.calcVersion(this.osession.mqManCon);
/*  589 */         MQGMO jmqiGetMessageOptions = getMessageOptions.getJMQIStructure();
/*  590 */         MQMD jmqiMessage = message.getJMQIStructure(0);
/*      */         
/*  592 */         Pint messageLength = new Pint(-1);
/*  593 */         Pint compCode = new Pint();
/*  594 */         Pint rc = new Pint();
/*  595 */         int targetCP = 0;
/*  596 */         boolean charactersetChanged = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  605 */         jmqiTls.setClassesForJavaMQGET();
/*  606 */         if (userMaxMsgSize) {
/*  607 */           jmqiTls.setClassesForJavaMQGETCalledWithMaxMsgLength();
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  612 */         ByteBuffer messageData = internalGetMessage(jmqi, jmqiMessage, jmqiGetMessageOptions, messageLength, expectedMsgLength, maxMsgLength, compCode, rc);
/*      */ 
/*      */         
/*  615 */         if (rc.x == 2190 && (getMessageOptions.options & 0x4000) == 16384 && messageData.limit() < maxMsgLength) {
/*  616 */           JmqiDC jmqiDataConvert = ((JmqiSystemEnvironment)this.env).getDC();
/*      */           try {
/*  618 */             JmqiCodepage cpSource = JmqiCodepage.getJmqiCodepage(this.env, jmqiMessage.getCodedCharSetId());
/*      */             
/*  620 */             if (cpSource == null) {
/*      */               
/*  622 */               UnsupportedEncodingException traceRet1 = new UnsupportedEncodingException(Integer.toString(jmqiMessage.getCodedCharSetId()));
/*  623 */               if (Trace.isOn) {
/*  624 */                 Trace.throwing(this, "com.ibm.mq.MQDestination", "getInt(MQMessage,MQGetMessageOptions,int,int,boolean)", traceRet1, 1);
/*      */               }
/*      */               
/*  627 */               throw traceRet1;
/*      */             } 
/*      */             
/*  630 */             JmqiCodepage cpTarget = JmqiCodepage.getJmqiCodepage(this.env, message.characterSet);
/*      */             
/*  632 */             if (cpTarget == null) {
/*      */               
/*  634 */               UnsupportedEncodingException traceRet2 = new UnsupportedEncodingException(Integer.toString(message.characterSet));
/*  635 */               if (Trace.isOn) {
/*  636 */                 Trace.throwing(this, "com.ibm.mq.MQDestination", "getInt(MQMessage,MQGetMessageOptions,int,int,boolean)", traceRet2, 2);
/*      */               }
/*      */               
/*  639 */               throw traceRet2;
/*      */             } 
/*      */             
/*  642 */             ByteBuffer convertedMessageData = jmqiDataConvert.convertBuffer(messageData, cpSource, cpTarget);
/*      */             
/*  644 */             if (Trace.isOn) {
/*  645 */               Trace.data(this, "getInt(MQMessage, MQGetMessageOptions,int,int,boolean)", "Converted message size = " + convertedMessageData
/*  646 */                   .limit() + ", Maximum message size = " + maxMsgLength);
/*      */             }
/*  648 */             if (convertedMessageData.limit() < maxMsgLength) {
/*  649 */               messageData = convertedMessageData;
/*  650 */               messageLength.x = messageData.limit();
/*  651 */               rc.x = 0;
/*  652 */               compCode.x = 0;
/*  653 */               charactersetChanged = true;
/*  654 */               targetCP = message.characterSet;
/*      */             }
/*      */           
/*  657 */           } catch (JmqiException|UnsupportedEncodingException e) {
/*  658 */             if (Trace.isOn) {
/*  659 */               Trace.catchBlock(this, "com.ibm.mq.MQDestination", "getInt(MQMessage,MQGetMessageOptions,int,int,boolean)", e);
/*      */             }
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  671 */         message.updateFromJMQIStructure(0);
/*      */         
/*  673 */         if (charactersetChanged) {
/*  674 */           message.characterSet = targetCP;
/*      */         }
/*      */         
/*  677 */         getMessageOptions.updateFromJMQIStructure();
/*      */         
/*  679 */         message.setMessageData(messageData, messageData.limit(), messageLength.x);
/*      */         
/*  681 */         if (compCode.x == 0 || compCode.x == 1) {
/*  682 */           boolean removeRfh2 = false;
/*  683 */           if (this.queueManagerCmdLevel >= 700) {
/*  684 */             if (propGmoAsQDef) {
/*  685 */               if (this.propControl != 3) {
/*  686 */                 removeRfh2 = true;
/*      */               
/*      */               }
/*      */             }
/*  690 */             else if ((originalGmoOpts & 0x8000000) != 0) {
/*  691 */               removeRfh2 = true;
/*      */             } 
/*      */             
/*  694 */             if (this.cfgPropControl) {
/*  695 */               removeRfh2 = false;
/*      */             }
/*      */           } 
/*  698 */           message.setRemoveRfh2AfterGet(removeRfh2);
/*      */           
/*  700 */           if (removeRfh2) {
/*  701 */             message.performProcessingAfterGet();
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/*  706 */         if (compCode.x != 0) {
/*  707 */           exception = new MQException(compCode.x, rc.x, this, this.osession.getLastJmqiException());
/*      */         }
/*      */       } 
/*  710 */       if (exception != null) {
/*  711 */         this.parentQmgr.errorOccurred(exception);
/*  712 */         if (Trace.isOn) {
/*  713 */           Trace.throwing(this, "com.ibm.mq.MQDestination", "getInt(MQMessage,MQGetMessageOptions,int,int,boolean)", exception, 3);
/*      */         }
/*      */         
/*  716 */         throw exception;
/*      */       }
/*      */     
/*      */     } finally {
/*      */       
/*  721 */       if (Trace.isOn) {
/*  722 */         Trace.finallyBlock(this, "com.ibm.mq.MQDestination", "getInt(MQMessage,MQGetMessageOptions,int,int,boolean)");
/*      */       }
/*      */ 
/*      */       
/*  726 */       if ((originalGmoOpts & 0x8000000) != 0) {
/*  727 */         getMessageOptions.options |= 0x8000000;
/*      */       }
/*  729 */       if ((originalGmoOpts & 0x2000000) == 0) {
/*  730 */         getMessageOptions.options &= 0xFDFFFFFF;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  735 */       jmqiTls.unsetClassesForJavaMQGETCalledWithMaxMsgLength();
/*  736 */       jmqiTls.unsetClassesForJavaMQGET();
/*      */     } 
/*      */     
/*  739 */     if (Trace.isOn) {
/*  740 */       Trace.exit(this, "com.ibm.mq.MQDestination", "getInt(MQMessage,MQGetMessageOptions,int,int,boolean)");
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
/*      */   public void getMsg2(MQMsg2 message, MQGetMessageOptions getMessageOptions, int MaxMsgSize) throws MQException {
/*  759 */     if (Trace.isOn) {
/*  760 */       Trace.entry(this, "com.ibm.mq.MQDestination", "getMsg2(MQMsg2,MQGetMessageOptions,int)", new Object[] { message, getMessageOptions, 
/*  761 */             Integer.valueOf(MaxMsgSize) });
/*      */     }
/*  763 */     MQException mqe = null;
/*      */     
/*  765 */     synchronized (this) {
/*      */       
/*  767 */       int expectedMsgLength = -1;
/*  768 */       int maxMsgLength = MaxMsgSize;
/*      */ 
/*      */       
/*  771 */       if ((getMessageOptions.options & 0x40) == 64) {
/*  772 */         expectedMsgLength = maxMsgLength;
/*      */       }
/*      */       
/*  775 */       getMsg2Int(message, getMessageOptions, expectedMsgLength, maxMsgLength, this.completionCode, this.reason);
/*  776 */       if (this.completionCode.x != 0 || this.reason.x != 0) {
/*  777 */         mqe = new MQException(this.completionCode.x, this.reason.x, this, this.osession.getLastJmqiException());
/*      */       }
/*      */     } 
/*  780 */     if (mqe != null) {
/*  781 */       this.parentQmgr.errorOccurred(mqe);
/*      */       
/*  783 */       if (Trace.isOn) {
/*  784 */         Trace.throwing(this, "com.ibm.mq.MQDestination", "getMsg2(MQMsg2,MQGetMessageOptions,int)", mqe);
/*      */       }
/*      */       
/*  787 */       throw mqe;
/*      */     } 
/*  789 */     if (Trace.isOn) {
/*  790 */       Trace.exit(this, "com.ibm.mq.MQDestination", "getMsg2(MQMsg2,MQGetMessageOptions,int)");
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
/*      */   public int getMsg2NoExc(MQMsg2 message, MQGetMessageOptions getMessageOptions, int MaxMsgSize) {
/*  811 */     if (Trace.isOn) {
/*  812 */       Trace.entry(this, "com.ibm.mq.MQDestination", "getMsg2NoExc(MQMsg2,MQGetMessageOptions,int)", new Object[] { message, getMessageOptions, 
/*  813 */             Integer.valueOf(MaxMsgSize) });
/*      */     }
/*      */     
/*  816 */     int expectedMsgLength = -1;
/*  817 */     int maxMsgLength = MaxMsgSize;
/*      */ 
/*      */     
/*  820 */     if ((getMessageOptions.options & 0x40) == 64) {
/*  821 */       expectedMsgLength = maxMsgLength;
/*      */     }
/*      */     
/*      */     try {
/*  825 */       getMsg2Int(message, getMessageOptions, expectedMsgLength, maxMsgLength, this.completionCode, this.reason);
/*      */     }
/*  827 */     catch (MQException mqe) {
/*  828 */       if (Trace.isOn) {
/*  829 */         Trace.catchBlock(this, "com.ibm.mq.MQDestination", "getMsg2NoExc(MQMsg2,MQGetMessageOptions,int)", mqe);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  834 */       this.completionCode.x = mqe.completionCode;
/*  835 */       this.reason.x = mqe.reasonCode;
/*      */     } 
/*  837 */     if (Trace.isOn) {
/*  838 */       Trace.exit(this, "com.ibm.mq.MQDestination", "getMsg2NoExc(MQMsg2,MQGetMessageOptions,int)", 
/*  839 */           Integer.valueOf(this.reason.x));
/*      */     }
/*  841 */     return this.reason.x;
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
/*      */   private void getMsg2Int(MQMsg2 message, MQGetMessageOptions getMessageOptions, int expectedMsgLength, int maxMsgLength, Pint compCode, Pint reasonCode) throws MQException {
/*  856 */     if (Trace.isOn) {
/*  857 */       Trace.entry(this, "com.ibm.mq.MQDestination", "getMsg2Int(MQMsg2,MQGetMessageOptions,int,int,Pint,Pint)", new Object[] { message, getMessageOptions, 
/*      */             
/*  859 */             Integer.valueOf(expectedMsgLength), Integer.valueOf(maxMsgLength), compCode, reasonCode });
/*      */     }
/*      */ 
/*      */     
/*  863 */     validateGetParameters(message, getMessageOptions, maxMsgLength);
/*      */     
/*  865 */     validateDestination();
/*  866 */     Pint msgLength = new Pint(-1);
/*  867 */     if (expectedMsgLength > 0) {
/*  868 */       msgLength.x = expectedMsgLength;
/*      */     }
/*      */     
/*  871 */     JmqiMQ jmqi = this.osession.getJmqi();
/*      */     
/*  873 */     getMessageOptions.calcVersion(this.osession.mqManCon);
/*  874 */     MQGMO jmqiGetMessageOptions = getMessageOptions.getJMQIStructure();
/*  875 */     MQMD jmqiMessage = message.getJMQIStructure();
/*  876 */     ByteBuffer messageData = internalGetMessage(jmqi, jmqiMessage, jmqiGetMessageOptions, msgLength, expectedMsgLength, maxMsgLength, compCode, reasonCode);
/*      */     
/*  878 */     getMessageOptions.updateFromJMQIStructure();
/*  879 */     if (compCode.x == 0) {
/*  880 */       message.setMessageData(messageData.array(), Math.min(maxMsgLength, msgLength.x));
/*      */     }
/*  882 */     if (Trace.isOn) {
/*  883 */       Trace.data(this, "getMsg2Int(MQMsg2,MQGetMessageOptions,int,int,Pint,Pint)", "getMsg2Int completed with cc, rc= ", Integer.toString(compCode.x) + "," + 
/*  884 */           Integer.toString(reasonCode.x));
/*      */     }
/*      */     
/*  887 */     if (Trace.isOn) {
/*  888 */       Trace.exit(this, "com.ibm.mq.MQDestination", "getMsg2Int(MQMsg2,MQGetMessageOptions,int,int,Pint,Pint)");
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
/*      */   public void getMsg2(MQMsg2 message, MQGetMessageOptions getMessageOptions) throws MQException {
/*  910 */     if (Trace.isOn) {
/*  911 */       Trace.entry(this, "com.ibm.mq.MQDestination", "getMsg2(MQMsg2,MQGetMessageOptions)", new Object[] { message, getMessageOptions });
/*      */     }
/*      */ 
/*      */     
/*  915 */     int expectedMsgLength = -1;
/*  916 */     int maxMsgLength = Integer.MAX_VALUE;
/*  917 */     MQException mqe = null;
/*  918 */     synchronized (this) {
/*      */       
/*  920 */       getMsg2Int(message, getMessageOptions, expectedMsgLength, maxMsgLength, this.completionCode, this.reason);
/*  921 */       if (this.completionCode.x != 0 || this.reason.x != 0) {
/*  922 */         mqe = new MQException(this.completionCode.x, this.reason.x, this, this.osession.getLastJmqiException());
/*      */       }
/*      */     } 
/*  925 */     if (mqe != null) {
/*  926 */       this.parentQmgr.errorOccurred(mqe);
/*      */       
/*  928 */       if (Trace.isOn) {
/*  929 */         Trace.throwing(this, "com.ibm.mq.MQDestination", "getMsg2(MQMsg2,MQGetMessageOptions)", mqe);
/*      */       }
/*      */       
/*  932 */       throw mqe;
/*      */     } 
/*  934 */     if (Trace.isOn) {
/*  935 */       Trace.exit(this, "com.ibm.mq.MQDestination", "getMsg2(MQMsg2,MQGetMessageOptions)");
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
/*      */   public synchronized int getMsg2NoExc(MQMsg2 message, MQGetMessageOptions getMessageOptions) {
/*  955 */     if (Trace.isOn) {
/*  956 */       Trace.entry(this, "com.ibm.mq.MQDestination", "getMsg2NoExc(MQMsg2,MQGetMessageOptions)", new Object[] { message, getMessageOptions });
/*      */     }
/*      */ 
/*      */     
/*  960 */     int expectedMsgLength = -1;
/*  961 */     int maxMsgLength = Integer.MAX_VALUE;
/*      */     
/*      */     try {
/*  964 */       getMsg2Int(message, getMessageOptions, expectedMsgLength, maxMsgLength, this.completionCode, this.reason);
/*      */     }
/*  966 */     catch (MQException mqe) {
/*  967 */       if (Trace.isOn) {
/*  968 */         Trace.catchBlock(this, "com.ibm.mq.MQDestination", "getMsg2NoExc(MQMsg2,MQGetMessageOptions)", mqe);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  973 */       this.completionCode.x = mqe.completionCode;
/*  974 */       this.reason.x = mqe.reasonCode;
/*      */     } 
/*  976 */     if (Trace.isOn) {
/*  977 */       Trace.exit(this, "com.ibm.mq.MQDestination", "getMsg2NoExc(MQMsg2,MQGetMessageOptions)", 
/*  978 */           Integer.valueOf(this.reason.x));
/*      */     }
/*  980 */     return this.reason.x;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void getMsg2(MQMsg2 message) throws MQException {
/*  991 */     if (Trace.isOn) {
/*  992 */       Trace.entry(this, "com.ibm.mq.MQDestination", "getMsg2(MQMsg2)", new Object[] { message });
/*      */     }
/*  994 */     getMsg2(message, new MQGetMessageOptions(true));
/*      */     
/*  996 */     if (Trace.isOn) {
/*  997 */       Trace.exit(this, "com.ibm.mq.MQDestination", "getMsg2(MQMsg2)");
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
/*      */   private void validateGetParameters(Object message, MQGetMessageOptions getMessageOptions, int maxMsgSize) throws MQException {
/* 1011 */     if (Trace.isOn) {
/* 1012 */       Trace.entry(this, "com.ibm.mq.MQDestination", "validateGetParameters(Object,MQGetMessageOptions,int)", new Object[] { message, getMessageOptions, 
/*      */             
/* 1014 */             Integer.valueOf(maxMsgSize) });
/*      */     }
/* 1016 */     if (message == null) {
/* 1017 */       MQException exception = new MQException(2, 2026, this, "MQJI025");
/*      */       
/* 1019 */       if (Trace.isOn) {
/* 1020 */         Trace.throwing(this, "com.ibm.mq.MQDestination", "validateGetParameters(Object,MQGetMessageOptions,int)", exception, 1);
/*      */       }
/*      */       
/* 1023 */       throw exception;
/*      */     } 
/* 1025 */     if (getMessageOptions == null) {
/* 1026 */       MQException exception = new MQException(2, 2186, this, "MQJI026");
/* 1027 */       if (Trace.isOn) {
/* 1028 */         Trace.throwing(this, "com.ibm.mq.MQDestination", "validateGetParameters(Object,MQGetMessageOptions,int)", exception, 2);
/*      */       }
/*      */       
/* 1031 */       throw exception;
/*      */     } 
/* 1033 */     if (maxMsgSize < 0) {
/* 1034 */       MQException exception = new MQException(2, 2005, this);
/* 1035 */       if (Trace.isOn) {
/* 1036 */         Trace.throwing(this, "com.ibm.mq.MQDestination", "validateGetParameters(Object,MQGetMessageOptions,int)", exception, 3);
/*      */       }
/*      */       
/* 1039 */       throw exception;
/*      */     } 
/* 1041 */     if (Trace.isOn) {
/* 1042 */       Trace.exit(this, "com.ibm.mq.MQDestination", "validateGetParameters(Object,MQGetMessageOptions,int)");
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
/*      */   protected synchronized ByteBuffer internalGetMessage(JmqiMQ jmqi, MQMD messageDescriptor, MQGMO getMessageOptions, Pint pMessageLength, int expectedMsgLength, int maxMsgLength, Pint pCompletionCode, Pint pReason) {
/* 1067 */     if (Trace.isOn) {
/* 1068 */       Trace.entry(this, "com.ibm.mq.MQDestination", "internalGetMessage(JmqiMQ,MQMD,MQGMO,Pint,int,int,Pint,Pint)", new Object[] { jmqi, messageDescriptor, getMessageOptions, pMessageLength, 
/*      */ 
/*      */             
/* 1071 */             Integer.valueOf(expectedMsgLength), Integer.valueOf(maxMsgLength), pCompletionCode, pReason });
/*      */     }
/*      */ 
/*      */     
/* 1075 */     int prevMatchOptions = getMessageOptions.getMatchOptions();
/*      */     
/* 1077 */     ByteBuffer buffer = ((JmqiSP)jmqi).jmqiGet(this.Hconn.getHconn(), this.Hobj.getHobj(), messageDescriptor, getMessageOptions, expectedMsgLength, maxMsgLength, this.pByteBuffer, this.pMsgTooSmallForBufferCount, pMessageLength, pCompletionCode, pReason);
/*      */ 
/*      */     
/* 1080 */     getMessageOptions.setMatchOptions(prevMatchOptions);
/*      */     
/* 1082 */     if (Trace.isOn) {
/* 1083 */       Trace.exit(this, "com.ibm.mq.MQDestination", "internalGetMessage(JmqiMQ,MQMD,MQGMO,Pint,int,int,Pint,Pint)", buffer);
/*      */     }
/*      */     
/* 1086 */     return buffer;
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
/*      */   public void put(MQMessage message) throws MQException {
/* 1102 */     if (Trace.isOn) {
/* 1103 */       Trace.entry(this, "com.ibm.mq.MQDestination", "put(MQMessage)", new Object[] { message });
/*      */     }
/*      */     
/*      */     try {
/* 1107 */       put(message, new MQPutMessageOptions());
/* 1108 */     } catch (MQException mqe) {
/* 1109 */       if (Trace.isOn) {
/* 1110 */         Trace.catchBlock(this, "com.ibm.mq.MQDestination", "put(MQMessage)", mqe);
/*      */       }
/* 1112 */       throw mqe;
/*      */     } finally {
/* 1114 */       if (Trace.isOn) {
/* 1115 */         Trace.exit(this, "com.ibm.mq.MQDestination", "put(MQMessage)");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void put(MQMessage message, MQPutMessageOptions putMessageOptions) throws MQException {
/* 1151 */     if (Trace.isOn) {
/* 1152 */       Trace.entry(this, "com.ibm.mq.MQDestination", "put(MQMessage,MQPutMessageOptions)", new Object[] { message, putMessageOptions });
/*      */     }
/*      */ 
/*      */     
/* 1156 */     String fid = "put(MQMessage,MQPutMessageOptions)";
/*      */     
/* 1158 */     validatePutParameters(message, putMessageOptions);
/*      */     
/* 1160 */     validateDestination();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1165 */     synchronized (message) {
/* 1166 */       MQException exception = null;
/*      */       
/*      */       try {
/*      */         int qmCcsid;
/*      */         try {
/* 1171 */           qmCcsid = this.Hconn.getHconn().getCcsid();
/*      */         }
/* 1173 */         catch (JmqiException e) {
/* 1174 */           if (Trace.isOn) {
/* 1175 */             Trace.catchBlock(this, "com.ibm.mq.MQDestination", "put(MQMessage,MQPutMessageOptions)", (Throwable)e, 1);
/*      */           }
/*      */ 
/*      */           
/* 1179 */           qmCcsid = 0;
/*      */         } 
/* 1181 */         message.performProcessingBeforePut(qmCcsid);
/*      */ 
/*      */         
/* 1184 */         JmqiMQ jmqi = this.osession.getJmqi();
/*      */ 
/*      */ 
/*      */         
/* 1188 */         if ((putMessageOptions.options & 0x300) != 0) {
/* 1189 */           if (putMessageOptions.contextReference != null) {
/* 1190 */             putMessageOptions.contextReferenceHandle = putMessageOptions.contextReference.getHandle();
/* 1191 */             if (putMessageOptions.contextReferenceHandle == -1) {
/* 1192 */               MQException traceRet5 = new MQException(2, 2097, this);
/*      */               
/* 1194 */               if (Trace.isOn) {
/* 1195 */                 Trace.throwing(this, "com.ibm.mq.MQDestination", "put(MQMessage,MQPutMessageOptions)", traceRet5, 1);
/*      */               }
/*      */               
/* 1198 */               throw traceRet5;
/*      */             } 
/* 1200 */             if (this.parentQmgr != putMessageOptions.contextReference.mgr) {
/* 1201 */               if (Trace.isOn) {
/* 1202 */                 Trace.data(this, "put(MQMessage,MQPutMessageOptions)", "Connection references do not match", "");
/*      */               }
/* 1204 */               MQException traceRet6 = new MQException(2, 2097, this);
/* 1205 */               if (Trace.isOn) {
/* 1206 */                 Trace.throwing(this, "com.ibm.mq.MQDestination", "put(MQMessage,MQPutMessageOptions)", traceRet6, 2);
/*      */               }
/*      */               
/* 1209 */               throw traceRet6;
/*      */             } 
/* 1211 */             if (Trace.isOn) {
/* 1212 */               Trace.data(this, "put(MQMessage,MQPutMessageOptions)", "Obtained context reference handle:", Integer.toString(putMessageOptions.contextReferenceHandle));
/*      */             }
/*      */           } else {
/*      */             
/* 1216 */             if (Trace.isOn) {
/* 1217 */               Trace.data(this, "put(MQMessage,MQPutMessageOptions)", "Context reference queue is null", "");
/*      */             }
/* 1219 */             MQException traceRet7 = new MQException(2, 2097, this);
/* 1220 */             if (Trace.isOn) {
/* 1221 */               Trace.throwing(this, "com.ibm.mq.MQDestination", "put(MQMessage,MQPutMessageOptions)", traceRet7, 3);
/*      */             }
/*      */             
/* 1224 */             throw traceRet7;
/*      */           } 
/*      */         }
/*      */         
/* 1228 */         MQPMO jmqiPutMessageOptions = putMessageOptions.getJMQIStructure();
/* 1229 */         int options = jmqiPutMessageOptions.getOptions();
/* 1230 */         MQMD jmqiMessage = message.getJMQIStructure(options);
/*      */ 
/*      */         
/* 1233 */         ByteBuffer messageData = message.getBuffer();
/* 1234 */         internalMQPUT(jmqi, jmqiMessage, jmqiPutMessageOptions, messageData);
/*      */ 
/*      */         
/* 1237 */         message.updateFromJMQIStructure(options);
/*      */         
/* 1239 */         jmqiPutMessageOptions.setOptions(options);
/* 1240 */         putMessageOptions.updateFromJMQIStructure();
/*      */       }
/* 1242 */       catch (MQException e) {
/* 1243 */         if (Trace.isOn) {
/* 1244 */           Trace.catchBlock(this, "com.ibm.mq.MQDestination", "put(MQMessage,MQPutMessageOptions)", e, 2);
/*      */         }
/*      */ 
/*      */         
/* 1248 */         exception = e;
/*      */       } finally {
/*      */         
/* 1251 */         if (Trace.isOn) {
/* 1252 */           Trace.finallyBlock(this, "com.ibm.mq.MQDestination", "put(MQMessage,MQPutMessageOptions)");
/*      */         }
/*      */ 
/*      */         
/*      */         try {
/* 1257 */           message.performProcessingAfterPut();
/*      */         }
/* 1259 */         catch (MQException e) {
/* 1260 */           if (Trace.isOn) {
/* 1261 */             Trace.catchBlock(this, "com.ibm.mq.MQDestination", "put(MQMessage,MQPutMessageOptions)", e, 3);
/*      */           }
/*      */ 
/*      */           
/* 1265 */           if (exception == null) {
/* 1266 */             exception = e;
/*      */           }
/*      */         } 
/*      */         
/* 1270 */         if (exception != null) {
/* 1271 */           if (Trace.isOn) {
/* 1272 */             Trace.throwing(this, "com.ibm.mq.MQDestination", "put(MQMessage,MQPutMessageOptions)", exception, 4);
/*      */           }
/*      */           
/* 1275 */           throw exception;
/*      */         } 
/*      */       } 
/*      */     } 
/* 1279 */     if (Trace.isOn) {
/* 1280 */       Trace.exit(this, "com.ibm.mq.MQDestination", "put(MQMessage,MQPutMessageOptions)");
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
/*      */   private void validatePutParameters(MQMessage message, MQPutMessageOptions putMessageOptions) throws MQException {
/* 1294 */     if (Trace.isOn) {
/* 1295 */       Trace.entry(this, "com.ibm.mq.MQDestination", "validatePutParameters(MQMessage,MQPutMessageOptions)", new Object[] { message, putMessageOptions });
/*      */     }
/*      */ 
/*      */     
/* 1299 */     if (message == null) {
/* 1300 */       MQException exception = new MQException(2, 2026, this, "MQJI028");
/*      */       
/* 1302 */       if (Trace.isOn) {
/* 1303 */         Trace.throwing(this, "com.ibm.mq.MQDestination", "validatePutParameters(MQMessage,MQPutMessageOptions)", exception, 1);
/*      */       }
/*      */       
/* 1306 */       throw exception;
/*      */     } 
/* 1308 */     if (putMessageOptions == null) {
/* 1309 */       MQException exception = new MQException(2, 2173, this, "MQJI029");
/* 1310 */       if (Trace.isOn) {
/* 1311 */         Trace.throwing(this, "com.ibm.mq.MQDestination", "validatePutParameters(MQMessage,MQPutMessageOptions)", exception, 2);
/*      */       }
/*      */       
/* 1314 */       throw exception;
/*      */     } 
/*      */     
/* 1317 */     if (Trace.isOn) {
/* 1318 */       Trace.exit(this, "com.ibm.mq.MQDestination", "validatePutParameters(MQMessage,MQPutMessageOptions)");
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
/*      */   private void internalMQPUT(JmqiMQ jmqi, MQMD message, MQPMO putMessageOptions, ByteBuffer messageData) throws MQException {
/* 1336 */     if (Trace.isOn) {
/* 1337 */       Trace.entry(this, "com.ibm.mq.MQDestination", "internalMQPUT(JmqiMQ,MQMD,MQPMO,ByteBuffer)", new Object[] { jmqi, message, putMessageOptions, messageData });
/*      */     }
/*      */ 
/*      */     
/* 1341 */     String fid = "internalMQPUT(JmqiMQ,MQMD,MQPMO,ByteBuffer)";
/* 1342 */     MQException exception = null;
/*      */     
/* 1344 */     synchronized (this) {
/*      */       
/* 1346 */       putMessageOptions.setInvalidDestCount(1);
/* 1347 */       putMessageOptions.setKnownDestCount(0);
/* 1348 */       putMessageOptions.setUnknownDestCount(0);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1353 */       int options = putMessageOptions.getOptions();
/* 1354 */       if ((options & 0x6) == 0) {
/* 1355 */         if (Trace.isOn) {
/* 1356 */           Trace.data(this, "internalMQPUT(JmqiMQ,MQMD,MQPMO,ByteBuffer)", "No SyncPoint action specified- implicit MQPMO_NO_SYNCPOINT...", "");
/*      */         }
/* 1358 */         putMessageOptions.setOptions(options | 0x4);
/*      */       } 
/*      */       
/* 1361 */       int bufferLength = messageData.limit();
/*      */       
/* 1363 */       if (Trace.isOn) {
/* 1364 */         Trace.data(this, "internalMQPUT(JmqiMQ,MQMD,MQPMO,ByteBuffer)", "Message length = ", Integer.toString(messageData.capacity()));
/* 1365 */         Trace.data(this, "internalMQPUT(JmqiMQ,MQMD,MQPMO,ByteBuffer)", "put options = " + putMessageOptions.getOptions() + "\nmessage type = " + message
/* 1366 */             .getMsgType() + "\nencoding = " + message
/* 1367 */             .getEncoding() + "\ncharacter set = " + message
/* 1368 */             .getCodedCharSetId() + "\nformat = " + message
/* 1369 */             .getFormat() + "\nmessage id, correlation id follow:", "");
/*      */         
/* 1371 */         Trace.data(this, "internalMQPUT(JmqiMQ,MQMD,MQPMO,ByteBuffer)", "messageId = ", message.getMsgId());
/* 1372 */         Trace.data(this, "internalMQPUT(JmqiMQ,MQMD,MQPMO,ByteBuffer)", "correlId = ", message.getCorrelId());
/*      */       } 
/*      */       
/* 1375 */       Pint completionCode = new Pint();
/* 1376 */       Pint reason = new Pint();
/* 1377 */       jmqi.MQPUT(this.Hconn.getHconn(), this.Hobj.getHobj(), message, putMessageOptions, bufferLength, messageData, completionCode, reason);
/*      */       
/* 1379 */       if (Trace.isOn) {
/* 1380 */         Trace.data(this, "internalMQPUT(JmqiMQ,MQMD,MQPMO,ByteBuffer)", "Returned Id = ", message.getMsgId());
/*      */       }
/*      */       
/* 1383 */       if (completionCode.x != 0 || reason.x != 0) {
/* 1384 */         exception = new MQException(completionCode.x, reason.x, this, this.osession.getLastJmqiException());
/*      */       }
/*      */     } 
/* 1387 */     if (exception != null) {
/* 1388 */       this.parentQmgr.errorOccurred(exception);
/*      */       
/* 1390 */       if (Trace.isOn) {
/* 1391 */         Trace.throwing(this, "com.ibm.mq.MQDestination", "internalMQPUT(JmqiMQ,MQMD,MQPMO,ByteBuffer)", exception);
/*      */       }
/*      */       
/* 1394 */       throw exception;
/*      */     } 
/* 1396 */     if (Trace.isOn) {
/* 1397 */       Trace.exit(this, "com.ibm.mq.MQDestination", "internalMQPUT(JmqiMQ,MQMD,MQPMO,ByteBuffer)");
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
/*      */   public int getDestinationType() {
/* 1410 */     if (Trace.isOn) {
/* 1411 */       Trace.data(this, "com.ibm.mq.MQDestination", "getDestinationType()", "getter", 
/* 1412 */           Integer.valueOf(this.destinationType));
/*      */     }
/* 1414 */     return this.destinationType;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public GregorianCalendar getCreationDateTime() throws MQException {
/* 1425 */     String date = getString(2004, 12);
/* 1426 */     String time = getString(2005, 8);
/* 1427 */     if (Trace.isOn) {
/* 1428 */       Trace.data(this, "getCreationDateTime()", "date = " + date + " time = " + time, "");
/*      */     }
/*      */ 
/*      */     
/* 1432 */     GregorianCalendar retVal = MQSESSION.getInquireCalendar();
/*      */     try {
/* 1434 */       int year = Integer.parseInt(date.substring(0, 4));
/* 1435 */       int month = Integer.parseInt(date.substring(5, 7));
/* 1436 */       int day = Integer.parseInt(date.substring(8, 10));
/* 1437 */       int hour = Integer.parseInt(time.substring(0, 2));
/* 1438 */       int minute = Integer.parseInt(time.substring(3, 5));
/* 1439 */       int second = Integer.parseInt(time.substring(6, 8));
/* 1440 */       retVal.set(year, month - 1, day, hour, minute, second);
/*      */     }
/* 1442 */     catch (StringIndexOutOfBoundsException ex) {
/* 1443 */       if (Trace.isOn) {
/* 1444 */         Trace.catchBlock(this, "com.ibm.mq.MQDestination", "getCreationDateTime()", ex, 1);
/*      */       }
/*      */       
/* 1447 */       retVal = new GregorianCalendar();
/*      */     }
/* 1449 */     catch (NumberFormatException ex) {
/* 1450 */       if (Trace.isOn) {
/* 1451 */         Trace.catchBlock(this, "com.ibm.mq.MQDestination", "getCreationDateTime()", ex, 2);
/*      */       }
/* 1453 */       retVal = new GregorianCalendar();
/*      */     } 
/*      */     
/* 1456 */     if (Trace.isOn) {
/* 1457 */       Trace.data(this, "com.ibm.mq.MQDestination", "getCreationDateTime()", "getter", retVal);
/*      */     }
/* 1459 */     return retVal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getQueueManagerCmdLevel() {
/* 1466 */     if (Trace.isOn) {
/* 1467 */       Trace.data(this, "com.ibm.mq.MQDestination", "getQueueManagerCmdLevel()", "getter", 
/* 1468 */           Integer.valueOf(this.queueManagerCmdLevel));
/*      */     }
/* 1470 */     return this.queueManagerCmdLevel;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setQueueManagerCmdLevel(int queueManagerCmdLevel) {
/* 1477 */     if (Trace.isOn) {
/* 1478 */       Trace.data(this, "com.ibm.mq.MQDestination", "setQueueManagerCmdLevel(int)", "setter", 
/* 1479 */           Integer.valueOf(queueManagerCmdLevel));
/*      */     }
/* 1481 */     this.queueManagerCmdLevel = queueManagerCmdLevel;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQDestination.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */