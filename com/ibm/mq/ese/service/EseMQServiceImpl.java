/*      */ package com.ibm.mq.ese.service;
/*      */ 
/*      */ import com.ibm.mq.ese.nls.AmsErrorMessages;
/*      */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*      */ import com.ibm.mq.jmqi.JmqiException;
/*      */ import com.ibm.mq.jmqi.JmqiMQ;
/*      */ import com.ibm.mq.jmqi.JmqiUtils;
/*      */ import com.ibm.mq.jmqi.MQDLH;
/*      */ import com.ibm.mq.jmqi.MQGMO;
/*      */ import com.ibm.mq.jmqi.MQMD;
/*      */ import com.ibm.mq.jmqi.MQOD;
/*      */ import com.ibm.mq.jmqi.MQPMO;
/*      */ import com.ibm.mq.jmqi.handles.Hconn;
/*      */ import com.ibm.mq.jmqi.handles.Hobj;
/*      */ import com.ibm.mq.jmqi.handles.PbyteBuffer;
/*      */ import com.ibm.mq.jmqi.handles.Phobj;
/*      */ import com.ibm.mq.jmqi.handles.Pint;
/*      */ import com.ibm.mq.jmqi.internal.Configuration;
/*      */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*      */ import com.ibm.mq.jmqi.system.JmqiSP;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.util.HashMap;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class EseMQServiceImpl
/*      */   implements EseMQService
/*      */ {
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/service/EseMQServiceImpl.java";
/*      */   private static final int DEFAULT_JMQI_POINTER_SIZE = 4;
/*      */   
/*      */   static {
/*   60 */     if (Trace.isOn) {
/*   61 */       Trace.data("com.ibm.mq.ese.service.EseMQServiceImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/service/EseMQServiceImpl.java");
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
/*   73 */   private static final String CLASS = EseMQServiceImpl.class.getName();
/*      */   
/*      */   protected JmqiMQ jmqi;
/*      */   
/*      */   private JmqiEnvironment env;
/*      */   
/*      */   private static final int QUIT = 0;
/*      */   
/*      */   private static final int GET_MESSAGE = 1;
/*      */   
/*      */   private static final int CONVERT_MESSAGE = 2;
/*      */   
/*      */   public EseMQServiceImpl(JmqiMQ jmqi, JmqiEnvironment env) {
/*   86 */     if (Trace.isOn) {
/*   87 */       Trace.entry(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "<init>(JmqiMQ,JmqiEnvironment)", new Object[] { jmqi, env });
/*      */     }
/*      */     
/*   90 */     this.jmqi = jmqi;
/*   91 */     this.env = env;
/*   92 */     if (Trace.isOn) {
/*   93 */       Trace.exit(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "<init>(JmqiMQ,JmqiEnvironment)");
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
/*      */   public long inqQmgrCcsid(Hconn hconn) throws EseMQException {
/*  105 */     if (Trace.isOn) {
/*  106 */       Trace.entry(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqQmgrCcsid(final Hconn)", new Object[] { hconn });
/*      */     }
/*      */ 
/*      */     
/*  110 */     int[] select = new int[1];
/*  111 */     select[0] = 2;
/*  112 */     int[] value = new int[1];
/*      */     try {
/*  114 */       inqLongQmgr(hconn, select, value);
/*      */     }
/*  116 */     catch (EseMQException e) {
/*  117 */       if (Trace.isOn) {
/*  118 */         Trace.catchBlock(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqQmgrCcsid(final Hconn)", (Throwable)e);
/*      */       }
/*      */       
/*  121 */       HashMap<String, Object> inserts = new HashMap<>();
/*  122 */       inserts.put("AMS_INSERT_MQ_ERROR_CODE", Integer.valueOf(e.getReason()).toString());
/*  123 */       EseMQException ex = new EseMQException(AmsErrorMessages.mqm_s_put_could_not_find_local_ccsid, inserts, (Throwable)e);
/*      */       
/*  125 */       ex.setReason(e.getReason());
/*  126 */       if (Trace.isOn) {
/*  127 */         Trace.throwing(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqQmgrCcsid(final Hconn)", (Throwable)ex);
/*      */       }
/*  129 */       throw ex;
/*      */     } 
/*      */     
/*  132 */     if (Trace.isOn) {
/*  133 */       Trace.exit(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqQmgrCcsid(final Hconn)", 
/*  134 */           Long.valueOf(value[0]));
/*      */     }
/*  136 */     return value[0];
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
/*      */   private void inqLongQmgr(Hconn hconn, int[] select, int[] value) throws EseMQException {
/*  152 */     if (Trace.isOn) {
/*  153 */       Trace.entry(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqLongQmgr(final Hconn,final int [ ],final int [ ])", new Object[] { hconn, select, value });
/*      */     }
/*      */ 
/*      */     
/*  157 */     Pint cc = this.env.newPint();
/*  158 */     Pint rc = this.env.newPint();
/*  159 */     MQOD od = this.env.newMQOD();
/*  160 */     int openOptions = 8224;
/*  161 */     od.setObjectType(5);
/*  162 */     od.setObjectName(null);
/*  163 */     od.setObjectQMgrName(null);
/*  164 */     Phobj pHobj = this.env.newPhobj();
/*      */     
/*  166 */     if (Trace.isOn) {
/*  167 */       Trace.traceInfo(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqLongQmgr(final Hconn, final int[], final int[])", "About to call MQOPEN ", new Object[] { hconn, od });
/*      */     }
/*      */ 
/*      */     
/*  171 */     this.jmqi.MQOPEN(hconn, od, openOptions, pHobj, cc, rc);
/*  172 */     if (cc.x == 2) {
/*  173 */       EseMQException traceRet1 = exceptionOnMQOPEN(cc, rc);
/*  174 */       if (Trace.isOn) {
/*  175 */         Trace.throwing(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqLongQmgr(final Hconn,final int [ ],final int [ ])", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/*  178 */       throw traceRet1;
/*      */     } 
/*  180 */     if (Trace.isOn) {
/*  181 */       Trace.traceInfo(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqLongQmgr(final Hconn, final int[], final int[])", "About to call MQINQ ", new Object[] { hconn, pHobj, select });
/*      */     }
/*      */ 
/*      */     
/*  185 */     this.jmqi.MQINQ(hconn, pHobj.getHobj(), 1, select, 1, value, 0, null, cc, rc);
/*  186 */     if (cc.x == 2) {
/*  187 */       EseMQException traceRet2 = exceptionOnMQINQ(cc, rc);
/*  188 */       if (Trace.isOn) {
/*  189 */         Trace.throwing(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqLongQmgr(final Hconn,final int [ ],final int [ ])", (Throwable)traceRet2, 2);
/*      */       }
/*      */       
/*  192 */       throw traceRet2;
/*      */     } 
/*  194 */     this.jmqi.MQCLOSE(hconn, pHobj, 0, cc, rc);
/*  195 */     if (cc.x == 2) {
/*  196 */       logMQCLOSEfailure(cc, rc, "inqLongQmgr(final Hconn, final int[], final int[])");
/*      */     }
/*      */     
/*  199 */     if (Trace.isOn) {
/*  200 */       Trace.exit(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqLongQmgr(final Hconn,final int [ ],final int [ ])");
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
/*      */   public String inqQmgrDlq(Hconn hconn) throws EseMQException {
/*  213 */     if (Trace.isOn) {
/*  214 */       Trace.entry(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqQmgrDlq(final Hconn)", new Object[] { hconn });
/*      */     }
/*      */ 
/*      */     
/*  218 */     int[] select = { 2006 };
/*  219 */     byte[] qmgrDlqBytes = new byte[48];
/*      */     try {
/*  221 */       inqStringQmgr(hconn, select, 48, qmgrDlqBytes);
/*      */     
/*      */     }
/*  224 */     catch (EseMQException e) {
/*  225 */       if (Trace.isOn) {
/*  226 */         Trace.catchBlock(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqQmgrDlq(final Hconn)", (Throwable)e, 1);
/*      */       }
/*  228 */       HashMap<String, Object> inserts = new HashMap<>();
/*  229 */       inserts.put("AMS_INSERT_MQ_ERROR_CODE", Integer.valueOf(e.getReason()).toString());
/*  230 */       EseMQException ex = new EseMQException(AmsErrorMessages.mqm_s_put_could_not_inquire_qmgr_property, inserts, (Throwable)e);
/*  231 */       ex.setReason(e.getReason());
/*  232 */       if (Trace.isOn) {
/*  233 */         Trace.throwing(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqQmgrDlq(final Hconn)", (Throwable)ex, 1);
/*      */       }
/*  235 */       throw ex;
/*      */     } 
/*      */     
/*  238 */     int ccsid = 0;
/*  239 */     String dlqName = null;
/*      */     try {
/*  241 */       ccsid = hconn.getCcsid();
/*  242 */       dlqName = JmqiUtils.qmgrBytesToString(this.env, hconn, qmgrDlqBytes, 0, qmgrDlqBytes.length);
/*      */     
/*      */     }
/*  245 */     catch (UnsupportedEncodingException e) {
/*  246 */       if (Trace.isOn) {
/*  247 */         Trace.catchBlock(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqQmgrDlq(final Hconn)", e, 2);
/*      */       }
/*  249 */       HashMap<String, String> inserts = new HashMap<>();
/*  250 */       inserts.put("AMS_INSERT_CHARACTER_ENCODING", String.valueOf(ccsid));
/*  251 */       EseMQException ex = new EseMQException(AmsErrorMessages.mju_ambi_header_convert_error_EseMQException, (HashMap)inserts, e);
/*  252 */       ex.setReason(2330);
/*      */     }
/*  254 */     catch (JmqiException e) {
/*  255 */       if (Trace.isOn) {
/*  256 */         Trace.catchBlock(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqQmgrDlq(final Hconn)", (Throwable)e, 3);
/*      */       }
/*  258 */       EseMQException traceRet1 = new EseMQException(e);
/*  259 */       if (Trace.isOn) {
/*  260 */         Trace.throwing(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqQmgrDlq(final Hconn)", (Throwable)traceRet1, 2);
/*      */       }
/*      */       
/*  263 */       throw traceRet1;
/*      */     } 
/*      */     
/*  266 */     if (Trace.isOn) {
/*  267 */       Trace.exit(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqQmgrDlq(final Hconn)", dlqName);
/*      */     }
/*  269 */     return dlqName;
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
/*      */   private void inqStringQmgr(Hconn hconn, int[] strAttrSelector, int strAttrLength, byte[] strAttrs) throws EseMQException {
/*  288 */     if (Trace.isOn) {
/*  289 */       Trace.entry(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqStringQmgr(final Hconn,final int [ ],final int,final byte [ ])", new Object[] { hconn, strAttrSelector, 
/*      */             
/*  291 */             Integer.valueOf(strAttrLength), strAttrs });
/*      */     }
/*      */     
/*  294 */     Pint cc = this.env.newPint();
/*  295 */     Pint rc = this.env.newPint();
/*  296 */     MQOD od = this.env.newMQOD();
/*  297 */     int openOptions = 8224;
/*  298 */     od.setObjectType(5);
/*  299 */     od.setObjectName(null);
/*  300 */     od.setObjectQMgrName(null);
/*  301 */     Phobj pHobj = this.env.newPhobj();
/*      */     
/*  303 */     if (Trace.isOn) {
/*  304 */       Trace.traceInfo(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqStringQmgr(final Hconn, final int[], final int, final byte[])", "About to call MQOPEN ", new Object[] { hconn, od });
/*      */     }
/*      */ 
/*      */     
/*  308 */     this.jmqi.MQOPEN(hconn, od, openOptions, pHobj, cc, rc);
/*  309 */     if (cc.x == 2) {
/*  310 */       EseMQException traceRet1 = exceptionOnMQOPEN(cc, rc);
/*  311 */       if (Trace.isOn) {
/*  312 */         Trace.throwing(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqStringQmgr(final Hconn,final int [ ],final int,final byte [ ])", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/*  315 */       throw traceRet1;
/*      */     } 
/*  317 */     if (Trace.isOn) {
/*  318 */       Trace.traceInfo(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqStringQmgr(final Hconn, final int[], final int, final byte[])", "About to call MQINQ ", new Object[] { hconn, pHobj, strAttrSelector });
/*      */     }
/*      */ 
/*      */     
/*  322 */     this.jmqi.MQINQ(hconn, pHobj.getHobj(), 1, strAttrSelector, 0, null, strAttrLength, strAttrs, cc, rc);
/*      */     
/*  324 */     if (cc.x == 2) {
/*  325 */       EseMQException traceRet2 = exceptionOnMQINQ(cc, rc);
/*  326 */       if (Trace.isOn) {
/*  327 */         Trace.throwing(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqStringQmgr(final Hconn,final int [ ],final int,final byte [ ])", (Throwable)traceRet2, 2);
/*      */       }
/*      */       
/*  330 */       throw traceRet2;
/*      */     } 
/*  332 */     this.jmqi.MQCLOSE(hconn, pHobj, 0, cc, rc);
/*  333 */     if (cc.x == 2) {
/*  334 */       logMQCLOSEfailure(cc, rc, "inqStringQmgr(final Hconn, final int[], final int, final byte[])");
/*      */     }
/*      */     
/*  337 */     if (Trace.isOn) {
/*  338 */       Trace.exit(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqStringQmgr(final Hconn,final int [ ],final int,final byte [ ])");
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
/*      */   public void checkQueueExists(Hconn hconn, String queueName, Pint cc, Pint rc) throws EseMQException {
/*  352 */     if (Trace.isOn) {
/*  353 */       Trace.entry(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "checkQueueExists(final Hconn,final String,Pint,Pint)", new Object[] { hconn, queueName, cc, rc });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  358 */     MQOD mqod = this.env.newMQOD();
/*  359 */     mqod.setObjectName(queueName);
/*  360 */     mqod.setObjectType(1);
/*      */     try {
/*  362 */       String queueManagerName = hconn.getName();
/*  363 */       mqod.setObjectQMgrName(queueManagerName);
/*      */     }
/*  365 */     catch (JmqiException e) {
/*  366 */       if (Trace.isOn) {
/*  367 */         Trace.catchBlock(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "checkQueueExists(final Hconn,final String,Pint,Pint)", (Throwable)e);
/*      */       }
/*      */       
/*  370 */       EseMQException traceRet1 = new EseMQException(e);
/*  371 */       if (Trace.isOn) {
/*  372 */         Trace.throwing(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "checkQueueExists(final Hconn,final String,Pint,Pint)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  375 */       throw traceRet1;
/*      */     } 
/*  377 */     Phobj phobj = this.env.newPhobj();
/*  378 */     this.jmqi.MQOPEN(hconn, mqod, 16, phobj, cc, rc);
/*  379 */     if (rc.x != 2085)
/*      */     {
/*      */       
/*  382 */       if (cc.x != 0 && rc.x != 2085) {
/*      */         
/*  384 */         logMQOPENfailure(cc, rc, "checkQueueExists(final Hconn, final String, Pint, Pint)");
/*      */       }
/*  386 */       else if (cc.x == 0) {
/*      */         
/*  388 */         this.jmqi.MQCLOSE(hconn, phobj, 0, cc, rc);
/*  389 */         if (cc.x != 0) {
/*  390 */           logMQCLOSEfailure(cc, rc, "checkQueueExists(final Hconn, final String, Pint, Pint)");
/*      */         }
/*      */       } 
/*      */     }
/*  394 */     if (Trace.isOn) {
/*  395 */       Trace.exit(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "checkQueueExists(final Hconn,final String,Pint,Pint)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void logMQOPENfailure(Pint cc, Pint rc, String meth) {
/*  402 */     if (Trace.isOn) {
/*  403 */       Trace.entry(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "logMQOPENfailure(Pint,Pint,String)", new Object[] { cc, rc, meth });
/*      */     }
/*      */     
/*  406 */     HashMap<String, Object> inserts = new HashMap<>();
/*  407 */     inserts.put("AMS_INSERT_MQ_REASON_CODE", Integer.valueOf(rc.x)).toString();
/*  408 */     AmsErrorMessages.log(CLASS, meth, AmsErrorMessages.mqm_s_open_real_open_error, inserts);
/*  409 */     if (Trace.isOn) {
/*  410 */       Trace.traceInfo(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "logMQOPENfailure(Pint, Pint, String)", "MQOPEN failed with compcode " + cc.x + "reason " + rc.x, "");
/*      */     }
/*      */ 
/*      */     
/*  414 */     if (Trace.isOn) {
/*  415 */       Trace.exit(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "logMQOPENfailure(Pint,Pint,String)");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void logMQCLOSEfailure(Pint cc, Pint rc, String meth) {
/*  421 */     if (Trace.isOn) {
/*  422 */       Trace.entry(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "logMQCLOSEfailure(Pint,Pint,String)", new Object[] { cc, rc, meth });
/*      */     }
/*      */     
/*  425 */     HashMap<String, Object> inserts = new HashMap<>();
/*  426 */     inserts.put("AMS_INSERT_MQ_REASON_CODE", Integer.valueOf(rc.x).toString());
/*  427 */     AmsErrorMessages.log(CLASS, "logMQCLOSEfailure(Pint, Pint, String)", AmsErrorMessages.mqm_s_open_hobj_close, inserts);
/*      */     
/*  429 */     if (Trace.isOn) {
/*  430 */       Trace.traceInfo(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "logMQCLOSEfailure(Pint, Pint, String)", "MQCLOSE failed with compcode " + cc.x + "reason " + rc.x, "");
/*      */     }
/*      */ 
/*      */     
/*  434 */     if (Trace.isOn) {
/*  435 */       Trace.exit(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "logMQCLOSEfailure(Pint,Pint,String)");
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
/*      */   public int inqQueueType(Hconn hconn, String queueName) throws EseMQException {
/*  449 */     if (Trace.isOn) {
/*  450 */       Trace.entry(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqQueueType(final Hconn,String)", new Object[] { hconn, queueName });
/*      */     }
/*      */ 
/*      */     
/*  454 */     int ret = 0;
/*  455 */     MQOD mqod = this.env.newMQOD();
/*      */     
/*  457 */     mqod.setObjectName(queueName);
/*      */ 
/*      */     
/*  460 */     int openOptions = 32;
/*  461 */     Phobj phobj = this.env.newPhobj();
/*  462 */     Pint cc = this.env.newPint();
/*  463 */     Pint rc = this.env.newPint();
/*      */     
/*  465 */     if (Trace.isOn) {
/*  466 */       Trace.traceInfo(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqQueueType(final Hconn, String)", "About to call MQOPEN ", new Object[] { hconn, mqod });
/*      */     }
/*      */ 
/*      */     
/*  470 */     this.jmqi.MQOPEN(hconn, mqod, openOptions, phobj, cc, rc);
/*  471 */     if (cc.x == 2) {
/*  472 */       EseMQException traceRet1 = exceptionOnMQOPEN(cc, rc);
/*  473 */       if (Trace.isOn) {
/*  474 */         Trace.throwing(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqQueueType(final Hconn,String)", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/*  477 */       throw traceRet1;
/*      */     } 
/*      */     
/*      */     try {
/*  481 */       int[] selectors = { 20 };
/*  482 */       int[] attrs = new int[1];
/*  483 */       if (Trace.isOn) {
/*  484 */         Trace.traceInfo(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqQueueType(final Hconn, String)", "About to call MQINQ ", new Object[] { hconn, phobj, selectors });
/*      */       }
/*      */ 
/*      */       
/*  488 */       this.jmqi.MQINQ(hconn, phobj.getHobj(), selectors.length, selectors, attrs.length, attrs, 0, null, cc, rc);
/*      */       
/*  490 */       if (cc.x == 2) {
/*  491 */         EseMQException traceRet2 = exceptionOnMQINQ(cc, rc);
/*  492 */         if (Trace.isOn) {
/*  493 */           Trace.throwing(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqQueueType(final Hconn,String)", (Throwable)traceRet2, 2);
/*      */         }
/*      */         
/*  496 */         throw traceRet2;
/*      */       } 
/*  498 */       if (isQueueDynamic(queueName, mqod)) {
/*  499 */         ret = 2;
/*      */       } else {
/*      */         
/*  502 */         ret = attrs[0];
/*      */       } 
/*  504 */       if (!isProperQueueType(ret)) {
/*  505 */         EseMQObjectTypeException traceRet3 = new EseMQObjectTypeException();
/*  506 */         if (Trace.isOn) {
/*  507 */           Trace.throwing(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqQueueType(final Hconn,String)", (Throwable)traceRet3, 3);
/*      */         }
/*      */         
/*  510 */         throw traceRet3;
/*      */       } 
/*  512 */       if (Trace.isOn) {
/*  513 */         Trace.exit(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqQueueType(final Hconn,String)", 
/*  514 */             Integer.valueOf(ret));
/*      */       }
/*  516 */       return ret;
/*      */     } finally {
/*      */       
/*  519 */       if (Trace.isOn) {
/*  520 */         Trace.finallyBlock(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqQueueType(final Hconn,String)");
/*      */       }
/*      */       
/*  523 */       this.jmqi.MQCLOSE(hconn, phobj, 0, cc, rc);
/*  524 */       if (cc.x == 2) {
/*  525 */         EseMQException traceRet4 = exceptionOnMQCLOSE(cc, rc);
/*  526 */         if (Trace.isOn) {
/*  527 */           Trace.throwing(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqQueueType(final Hconn,String)", (Throwable)traceRet4, 4);
/*      */         }
/*      */         
/*  530 */         throw traceRet4;
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
/*      */   private boolean isProperQueueType(int type) {
/*  548 */     if (Trace.isOn)
/*  549 */       Trace.entry(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "isProperQueueType(int)", new Object[] {
/*  550 */             Integer.valueOf(type)
/*      */           }); 
/*  552 */     boolean traceRet1 = (type == 1 || type == 6 || type == 3 || type == 7 || type == 2);
/*  553 */     if (Trace.isOn) {
/*  554 */       Trace.exit(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "isProperQueueType(int)", 
/*  555 */           Boolean.valueOf(traceRet1));
/*      */     }
/*  557 */     return traceRet1;
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
/*      */   private boolean isQueueDynamic(String queueName, MQOD mqod) {
/*  572 */     if (Trace.isOn) {
/*  573 */       Trace.entry(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "isQueueDynamic(final String,final MQOD)", new Object[] { queueName, mqod });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  578 */     boolean traceRet1 = (mqod.getObjectName() != null && !queueName.equalsIgnoreCase(mqod.getObjectName().trim()));
/*  579 */     if (Trace.isOn) {
/*  580 */       Trace.exit(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "isQueueDynamic(final String,final MQOD)", 
/*  581 */           Boolean.valueOf(traceRet1));
/*      */     }
/*  583 */     return traceRet1;
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
/*      */   public String[] inqResolveQueue(Hconn hconn, int options, String origQmgrName, String origQueueName, int queueType, boolean forceRemoteResolution) throws EseMQException {
/*  596 */     if (Trace.isOn) {
/*  597 */       Trace.entry(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqResolveQueue(final Hconn,final int,final String,final String,final int,final boolean)", new Object[] { hconn, 
/*      */             
/*  599 */             Integer.valueOf(options), origQmgrName, origQueueName, 
/*  600 */             Integer.valueOf(queueType), Boolean.valueOf(forceRemoteResolution) });
/*      */     }
/*      */     
/*  603 */     String[] ret = null;
/*  604 */     if (queueType == 1 || queueType == 3 || queueType == 2) {
/*  605 */       ret = new String[] { origQmgrName, origQueueName };
/*      */     }
/*  607 */     else if (queueType == 6) {
/*  608 */       if (forceRemoteResolution) {
/*  609 */         String[] remoteInfo = inqResolveRemote(hconn, origQueueName);
/*  610 */         ret = new String[] { remoteInfo[0], remoteInfo[1] };
/*      */       } else {
/*      */         
/*  613 */         ret = new String[] { origQmgrName, origQueueName };
/*      */       }
/*      */     
/*  616 */     } else if (queueType == 7) {
/*  617 */       String[] clusterInfo = inqResolveCluster(hconn, options, origQueueName);
/*      */       
/*  619 */       ret = new String[] { clusterInfo[0], clusterInfo[1] };
/*      */     } else {
/*      */       
/*  622 */       EseMQObjectTypeException traceRet1 = new EseMQObjectTypeException();
/*  623 */       if (Trace.isOn) {
/*  624 */         Trace.throwing(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqResolveQueue(final Hconn,final int,final String,final String,final int,final boolean)", (Throwable)traceRet1, 1);
/*      */       }
/*      */ 
/*      */       
/*  628 */       throw traceRet1;
/*      */     } 
/*  630 */     if (ret[1] == null || ret[0] == null) {
/*  631 */       HashMap<String, Object> inserts = new HashMap<>();
/*  632 */       inserts.put("AMS_INSERT_MQ_ERROR_CODE", Integer.valueOf(2035).toString());
/*  633 */       EseMQException ex = new EseMQException(AmsErrorMessages.mqm_s_open_resolve_qname_err, inserts);
/*  634 */       ex.setReason(2035);
/*  635 */       if (Trace.isOn) {
/*  636 */         Trace.throwing(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqResolveQueue(final Hconn,final int,final String,final String,final int,final boolean)", (Throwable)ex, 2);
/*      */       }
/*      */ 
/*      */       
/*  640 */       throw ex;
/*      */     } 
/*      */     
/*  643 */     if (Trace.isOn) {
/*  644 */       Trace.exit(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqResolveQueue(final Hconn,final int,final String,final String,final int,final boolean)", ret);
/*      */     }
/*      */     
/*  647 */     return ret;
/*      */   }
/*      */ 
/*      */   
/*      */   private String[] inqResolveCluster(Hconn hconn, int options, String origQueueName) throws EseMQException {
/*  652 */     if (Trace.isOn) {
/*  653 */       Trace.entry(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqResolveCluster(final Hconn,final int,final String)", new Object[] { hconn, 
/*      */             
/*  655 */             Integer.valueOf(options), origQueueName });
/*      */     }
/*      */ 
/*      */     
/*  659 */     MQOD mqod = this.env.newMQOD();
/*  660 */     Phobj phobj = this.env.newPhobj();
/*  661 */     Pint cc = this.env.newPint();
/*  662 */     Pint rc = this.env.newPint();
/*      */     
/*  664 */     mqod.setObjectName(origQueueName);
/*  665 */     mqod.setVersion(4);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  681 */     int O_options = 16384;
/*  682 */     O_options |= options & 0x1F;
/*  683 */     if (O_options == 16384) {
/*  684 */       O_options += 16;
/*      */     }
/*  686 */     this.jmqi.MQOPEN(hconn, mqod, O_options, phobj, cc, rc);
/*  687 */     if (cc.x == 2) {
/*  688 */       EseMQException traceRet1 = exceptionOnMQOPEN(cc, rc);
/*  689 */       if (Trace.isOn) {
/*  690 */         Trace.throwing(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqResolveCluster(final Hconn,final int,final String)", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/*  693 */       throw traceRet1;
/*      */     } 
/*  695 */     String[] ret = new String[2];
/*      */     try {
/*  697 */       if (mqod.getVersion() >= 3 && mqod.getResolvedQMgrName() != null) {
/*  698 */         ret[0] = mqod.getResolvedQMgrName();
/*      */       }
/*  700 */       if (mqod.getVersion() >= 3 && mqod.getResolvedQName() != null) {
/*  701 */         ret[1] = mqod.getResolvedQName();
/*      */       }
/*  703 */       if (Trace.isOn) {
/*  704 */         Trace.exit(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqResolveCluster(final Hconn,final int,final String)", ret);
/*      */       }
/*      */       
/*  707 */       return ret;
/*      */     } finally {
/*      */       
/*  710 */       if (Trace.isOn) {
/*  711 */         Trace.finallyBlock(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqResolveCluster(final Hconn,final int,final String)");
/*      */       }
/*      */       
/*  714 */       this.jmqi.MQCLOSE(hconn, phobj, 0, cc, rc);
/*  715 */       if (cc.x == 2) {
/*  716 */         EseMQException traceRet2 = exceptionOnMQCLOSE(cc, rc);
/*  717 */         if (Trace.isOn) {
/*  718 */           Trace.throwing(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqResolveCluster(final Hconn,final int,final String)", (Throwable)traceRet2, 2);
/*      */         }
/*      */         
/*  721 */         throw traceRet2;
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
/*      */   private String[] inqResolveRemote(Hconn hconn, String queueDefinition) throws EseMQException {
/*  738 */     if (Trace.isOn) {
/*  739 */       Trace.entry(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqResolveRemote(final Hconn,final String)", new Object[] { hconn, queueDefinition });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  744 */     MQOD mqod = this.env.newMQOD();
/*  745 */     int O_options = 8224;
/*  746 */     Phobj phobj = this.env.newPhobj();
/*  747 */     Pint cc = this.env.newPint();
/*  748 */     Pint rc = this.env.newPint();
/*  749 */     mqod.setObjectName(queueDefinition);
/*      */     
/*  751 */     this.jmqi.MQOPEN(hconn, mqod, O_options, phobj, cc, rc);
/*  752 */     if (cc.x == 2) {
/*  753 */       EseMQException traceRet1 = exceptionOnMQOPEN(cc, rc);
/*  754 */       if (Trace.isOn) {
/*  755 */         Trace.throwing(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqResolveRemote(final Hconn,final String)", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/*  758 */       throw traceRet1;
/*      */     } 
/*      */     
/*  761 */     byte[] qmgrNameBytes = new byte[48];
/*  762 */     this.jmqi.MQINQ(hconn, phobj.getHobj(), 1, new int[] { 2017 }, 0, null, 48, qmgrNameBytes, cc, rc);
/*      */ 
/*      */     
/*  765 */     if (cc.x == 2) {
/*  766 */       EseMQException traceRet2 = exceptionOnMQINQ(cc, rc);
/*  767 */       if (Trace.isOn) {
/*  768 */         Trace.throwing(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqResolveRemote(final Hconn,final String)", (Throwable)traceRet2, 2);
/*      */       }
/*      */       
/*  771 */       throw traceRet2;
/*      */     } 
/*      */     
/*  774 */     byte[] queueNameBytes = new byte[48];
/*  775 */     this.jmqi.MQINQ(hconn, phobj.getHobj(), 1, new int[] { 2018 }, 0, null, 48, queueNameBytes, cc, rc);
/*      */ 
/*      */     
/*  778 */     if (cc.x == 2) {
/*  779 */       EseMQException traceRet3 = exceptionOnMQINQ(cc, rc);
/*  780 */       if (Trace.isOn) {
/*  781 */         Trace.throwing(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqResolveRemote(final Hconn,final String)", (Throwable)traceRet3, 3);
/*      */       }
/*      */       
/*  784 */       throw traceRet3;
/*      */     } 
/*      */     
/*  787 */     int ccsid = 0;
/*  788 */     String[] ret = new String[2];
/*      */     try {
/*  790 */       ccsid = hconn.getCcsid();
/*  791 */       String remoteQmgr = JmqiUtils.qmgrBytesToString(this.env, hconn, qmgrNameBytes, 0, qmgrNameBytes.length);
/*      */       
/*  793 */       String remoteQ = JmqiUtils.qmgrBytesToString(this.env, hconn, queueNameBytes, 0, queueNameBytes.length);
/*      */       
/*  795 */       ret[0] = remoteQmgr;
/*  796 */       ret[1] = remoteQ;
/*  797 */       if (Trace.isOn) {
/*  798 */         Trace.exit(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqResolveRemote(final Hconn,final String)", ret);
/*      */       }
/*      */       
/*  801 */       return ret;
/*      */     }
/*  803 */     catch (UnsupportedEncodingException e) {
/*  804 */       if (Trace.isOn) {
/*  805 */         Trace.catchBlock(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqResolveRemote(final Hconn,final String)", e, 1);
/*      */       }
/*      */       
/*  808 */       HashMap<String, Object> inserts = new HashMap<>();
/*  809 */       inserts.put("AMS_INSERT_CHARACTER_ENCODING", Integer.valueOf(ccsid).toString());
/*  810 */       EseMQException ex = new EseMQException(AmsErrorMessages.mju_ambi_header_convert_error_EseMQException, inserts, e);
/*  811 */       ex.setReason(2330);
/*  812 */       if (Trace.isOn) {
/*  813 */         Trace.throwing(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqResolveRemote(final Hconn,final String)", (Throwable)ex, 4);
/*      */       }
/*      */       
/*  816 */       throw ex;
/*      */     }
/*  818 */     catch (JmqiException e) {
/*  819 */       if (Trace.isOn) {
/*  820 */         Trace.catchBlock(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqResolveRemote(final Hconn,final String)", (Throwable)e, 2);
/*      */       }
/*      */       
/*  823 */       EseMQException traceRet4 = new EseMQException(e);
/*  824 */       if (Trace.isOn) {
/*  825 */         Trace.throwing(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqResolveRemote(final Hconn,final String)", (Throwable)traceRet4, 5);
/*      */       }
/*      */       
/*  828 */       throw traceRet4;
/*      */     } finally {
/*      */       
/*  831 */       if (Trace.isOn) {
/*  832 */         Trace.finallyBlock(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqResolveRemote(final Hconn,final String)");
/*      */       }
/*      */       
/*  835 */       this.jmqi.MQCLOSE(hconn, phobj, 0, cc, rc);
/*  836 */       if (cc.x == 2) {
/*  837 */         EseMQException traceRet5 = exceptionOnMQCLOSE(cc, rc);
/*  838 */         if (Trace.isOn) {
/*  839 */           Trace.throwing(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqResolveRemote(final Hconn,final String)", (Throwable)traceRet5, 6);
/*      */         }
/*      */         
/*  842 */         throw traceRet5;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private EseMQException exceptionOnMQCLOSE(Pint cc, Pint rc) {
/*  849 */     if (Trace.isOn) {
/*  850 */       Trace.entry(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "exceptionOnMQCLOSE(Pint,Pint)", new Object[] { cc, rc });
/*      */     }
/*      */     
/*  853 */     HashMap<String, Object> inserts = new HashMap<>();
/*  854 */     inserts.put("AMS_INSERT_MQ_REASON_CODE", Integer.valueOf(rc.x).toString());
/*      */     
/*  856 */     EseMQException ex = new EseMQException(AmsErrorMessages.mqm_s_open_hobj_close, inserts);
/*  857 */     ex.setReason(rc.x);
/*  858 */     if (Trace.isOn) {
/*  859 */       Trace.exit(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "exceptionOnMQCLOSE(Pint,Pint)", ex);
/*      */     }
/*  861 */     return ex;
/*      */   }
/*      */   
/*      */   private EseMQException exceptionOnMQINQ(Pint cc, Pint rc) {
/*  865 */     if (Trace.isOn) {
/*  866 */       Trace.entry(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "exceptionOnMQINQ(Pint,Pint)", new Object[] { cc, rc });
/*      */     }
/*      */     
/*  869 */     HashMap<String, Object> inserts = new HashMap<>();
/*  870 */     inserts.put("AMS_INSERT_STRINGIFIABLE_OBJECT", "MQINQ");
/*  871 */     EseMQException ex = new EseMQException(AmsErrorMessages.show_object, inserts);
/*  872 */     ex.setReason(rc.x);
/*  873 */     if (Trace.isOn) {
/*  874 */       Trace.exit(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "exceptionOnMQINQ(Pint,Pint)", ex);
/*      */     }
/*  876 */     return ex;
/*      */   }
/*      */   
/*      */   private EseMQException exceptionOnMQOPEN(Pint cc, Pint rc) {
/*  880 */     if (Trace.isOn) {
/*  881 */       Trace.entry(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "exceptionOnMQOPEN(Pint,Pint)", new Object[] { cc, rc });
/*      */     }
/*      */     
/*  884 */     HashMap<String, Object> inserts = new HashMap<>();
/*  885 */     inserts.put("AMS_INSERT_MQ_REASON_CODE", Integer.valueOf(rc.x).toString());
/*  886 */     EseMQException ex = new EseMQException(AmsErrorMessages.mqm_s_open_real_open_error, inserts);
/*  887 */     ex.setReason(rc.x);
/*  888 */     if (Trace.isOn) {
/*  889 */       Trace.exit(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "exceptionOnMQOPEN(Pint,Pint)", ex);
/*      */     }
/*  891 */     return ex;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String[] inqResolveQueue2(Hconn hconn, String objectQMgrName, String objectName, int queueType) throws EseMQException {
/*  898 */     if (Trace.isOn) {
/*  899 */       Trace.entry(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqResolveQueue2(final Hconn,final String,final String,final int)", new Object[] { hconn, objectQMgrName, objectName, 
/*      */             
/*  901 */             Integer.valueOf(queueType) });
/*      */     }
/*      */     
/*  904 */     String[] ret = null;
/*  905 */     if (queueType == 1) {
/*  906 */       ret = new String[] { objectQMgrName, objectName };
/*      */     }
/*  908 */     else if (queueType == 6) {
/*  909 */       byte[] qmgrNameBytes = new byte[48];
/*  910 */       doInqQmrAlias(hconn, objectQMgrName, qmgrNameBytes);
/*  911 */       int ccsid = 0;
/*      */       try {
/*  913 */         ccsid = hconn.getCcsid();
/*  914 */         String remoteQmgr = JmqiUtils.qmgrBytesToString(this.env, hconn, qmgrNameBytes, 0, qmgrNameBytes.length);
/*      */         
/*  916 */         ret = new String[] { remoteQmgr, objectName };
/*      */       }
/*  918 */       catch (UnsupportedEncodingException e) {
/*  919 */         if (Trace.isOn) {
/*  920 */           Trace.catchBlock(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqResolveQueue2(final Hconn,final String,final String,final int)", e, 1);
/*      */         }
/*      */         
/*  923 */         HashMap<String, Object> inserts = new HashMap<>();
/*  924 */         inserts.put("AMS_INSERT_CHARACTER_ENCODING", Integer.valueOf(ccsid).toString());
/*  925 */         EseMQException ex = new EseMQException(AmsErrorMessages.mju_ambi_header_convert_error_EseMQException, inserts, e);
/*  926 */         ex.setReason(2330);
/*  927 */         if (Trace.isOn) {
/*  928 */           Trace.throwing(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqResolveQueue2(final Hconn,final String,final String,final int)", (Throwable)ex, 1);
/*      */         }
/*      */         
/*  931 */         throw ex;
/*      */       }
/*  933 */       catch (JmqiException e) {
/*  934 */         if (Trace.isOn) {
/*  935 */           Trace.catchBlock(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqResolveQueue2(final Hconn,final String,final String,final int)", (Throwable)e, 2);
/*      */         }
/*      */         
/*  938 */         EseMQException traceRet1 = new EseMQException(e);
/*  939 */         if (Trace.isOn) {
/*  940 */           Trace.throwing(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqResolveQueue2(final Hconn,final String,final String,final int)", (Throwable)traceRet1, 2);
/*      */         }
/*      */         
/*  943 */         throw traceRet1;
/*      */       } 
/*      */     } else {
/*      */       
/*  947 */       if (Trace.isOn) {
/*  948 */         Trace.traceInfo(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqResolveQueue2(final Hconn, final String, final String, final int)", "queue manager is not of qlocal or qremote type", "");
/*      */       }
/*      */ 
/*      */       
/*  952 */       ret = new String[] { objectQMgrName, objectName };
/*      */     } 
/*      */     
/*  955 */     if (Trace.isOn) {
/*  956 */       Trace.exit(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "inqResolveQueue2(final Hconn,final String,final String,final int)", ret);
/*      */     }
/*      */     
/*  959 */     return ret;
/*      */   }
/*      */ 
/*      */   
/*      */   private void doInqQmrAlias(Hconn hconn, String objectQMgrName, byte[] qmgrNameBytes) throws EseMQException {
/*  964 */     if (Trace.isOn) {
/*  965 */       Trace.entry(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "doInqQmrAlias(final Hconn,final String,byte [ ])", new Object[] { hconn, objectQMgrName, qmgrNameBytes });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  970 */     MQOD mqod = this.env.newMQOD();
/*  971 */     int O_options = 32;
/*  972 */     Phobj phobj = this.env.newPhobj();
/*  973 */     Pint cc = this.env.newPint();
/*  974 */     Pint rc = this.env.newPint();
/*  975 */     mqod.setObjectName(objectQMgrName);
/*  976 */     this.jmqi.MQOPEN(hconn, mqod, O_options, phobj, cc, rc);
/*  977 */     if (cc.x == 2) {
/*  978 */       EseMQException traceRet1 = exceptionOnMQOPEN(cc, rc);
/*  979 */       if (Trace.isOn) {
/*  980 */         Trace.throwing(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "doInqQmrAlias(final Hconn,final String,byte [ ])", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/*  983 */       throw traceRet1;
/*      */     } 
/*      */     
/*      */     try {
/*  987 */       this.jmqi.MQINQ(hconn, phobj.getHobj(), 1, new int[] { 2017 }, 0, null, 48, qmgrNameBytes, cc, rc);
/*      */ 
/*      */       
/*  990 */       if (cc.x == 2) {
/*  991 */         EseMQException traceRet2 = exceptionOnMQINQ(cc, rc);
/*  992 */         if (Trace.isOn) {
/*  993 */           Trace.throwing(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "doInqQmrAlias(final Hconn,final String,byte [ ])", (Throwable)traceRet2, 2);
/*      */         }
/*      */         
/*  996 */         throw traceRet2;
/*      */       } 
/*      */     } finally {
/*      */       
/* 1000 */       if (Trace.isOn) {
/* 1001 */         Trace.finallyBlock(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "doInqQmrAlias(final Hconn,final String,byte [ ])");
/*      */       }
/*      */       
/* 1004 */       this.jmqi.MQCLOSE(hconn, phobj, 0, cc, rc);
/* 1005 */       if (cc.x == 2) {
/* 1006 */         EseMQException traceRet3 = exceptionOnMQCLOSE(cc, rc);
/* 1007 */         if (Trace.isOn) {
/* 1008 */           Trace.throwing(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "doInqQmrAlias(final Hconn,final String,byte [ ])", (Throwable)traceRet3, 3);
/*      */         }
/*      */         
/* 1011 */         throw traceRet3;
/*      */       } 
/*      */     } 
/* 1014 */     if (Trace.isOn) {
/* 1015 */       Trace.exit(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "doInqQmrAlias(final Hconn,final String,byte [ ])");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   JmqiMQ getJmqi() {
/* 1022 */     if (Trace.isOn) {
/* 1023 */       Trace.data(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "getJmqi()", "getter", this.jmqi);
/*      */     }
/* 1025 */     return this.jmqi;
/*      */   }
/*      */   
/*      */   void setJmqi(JmqiMQ jmqi) {
/* 1029 */     if (Trace.isOn) {
/* 1030 */       Trace.data(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "setJmqi(JmqiMQ)", "setter", jmqi);
/*      */     }
/* 1032 */     this.jmqi = jmqi;
/*      */   }
/*      */   
/*      */   JmqiEnvironment getEnv() {
/* 1036 */     if (Trace.isOn) {
/* 1037 */       Trace.data(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "getEnv()", "getter", this.env);
/*      */     }
/* 1039 */     return this.env;
/*      */   }
/*      */   
/*      */   void setEnv(JmqiEnvironment env) {
/* 1043 */     if (Trace.isOn) {
/* 1044 */       Trace.data(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "setEnv(JmqiEnvironment)", "setter", env);
/*      */     }
/*      */     
/* 1047 */     this.env = env;
/*      */   }
/*      */   
/*      */   private byte[] copyArray(byte[] src, int length) {
/* 1051 */     if (Trace.isOn) {
/* 1052 */       Trace.entry(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "copyArray(byte [ ],int)", new Object[] { src, 
/* 1053 */             Integer.valueOf(length) });
/*      */     }
/* 1055 */     byte[] bytes = new byte[length];
/* 1056 */     System.arraycopy(src, 0, bytes, 0, length);
/* 1057 */     if (Trace.isOn) {
/* 1058 */       Trace.exit(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "copyArray(byte [ ],int)", bytes);
/*      */     }
/* 1060 */     return bytes;
/*      */   }
/*      */ 
/*      */   
/*      */   public MQGMO copyGetMsgOpts(MQGMO target, MQGMO source) {
/* 1065 */     if (Trace.isOn) {
/* 1066 */       Trace.entry(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "copyGetMsgOpts(MQGMO,final MQGMO)", new Object[] { target, source });
/*      */     }
/*      */     
/* 1069 */     target.setVersion(source.getVersion());
/* 1070 */     target.setOptions(source.getOptions());
/* 1071 */     target.setWaitInterval(source.getWaitInterval());
/*      */ 
/*      */     
/* 1074 */     target.setResolvedQName(source.getResolvedQName());
/*      */     
/* 1076 */     if (source.getVersion() >= 2) {
/* 1077 */       target.setMatchOptions(source.getMatchOptions());
/* 1078 */       target.setGroupStatus(source.getGroupStatus());
/* 1079 */       target.setSegmentation(source.getSegmentation());
/* 1080 */       target.setSegmentStatus(source.getSegmentStatus());
/*      */     } 
/* 1082 */     if (source.getVersion() >= 3) {
/* 1083 */       byte[] msgToken = copyArray(source.getMsgToken(), 16);
/*      */       
/* 1085 */       target.setMsgToken(msgToken);
/* 1086 */       target.setReturnedLength(source.getReturnedLength());
/*      */     } 
/* 1088 */     if (source.getVersion() >= 4) {
/* 1089 */       target.setMessageHandle(source.getMessageHandle());
/*      */     }
/* 1091 */     if (Trace.isOn) {
/* 1092 */       Trace.exit(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "copyGetMsgOpts(MQGMO,final MQGMO)", target);
/*      */     }
/*      */     
/* 1095 */     return target;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ByteBuffer getMessage(Hconn hconn, Hobj hobj, MQMD md, MQGMO gmo, int expectedMsgLength, int pMaxMsgLength, ByteBuffer byteBuffer, Pint pDataLength, Pint pCC, Pint pRC) {
/* 1106 */     if (Trace.isOn) {
/* 1107 */       Trace.entry(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "getMessage(Hconn,Hobj,MQMD,MQGMO,int,int,ByteBuffer,Pint,Pint,Pint)", new Object[] { hconn, hobj, md, gmo, 
/*      */             
/* 1109 */             Integer.valueOf(expectedMsgLength), Integer.valueOf(pMaxMsgLength), byteBuffer, pDataLength, pCC, pRC });
/*      */     }
/*      */ 
/*      */     
/* 1113 */     int maxMsgLength = pMaxMsgLength;
/* 1114 */     int defaultMaxMsgSize = getDefaultMaxMessageSize();
/* 1115 */     int threshold = getSmallMsgBufferReductionThreshold();
/* 1116 */     Pint pMsgTooSmallForBufferCount = this.env.newPint();
/*      */ 
/*      */     
/* 1119 */     PbyteBuffer pByteBuffer = getMsgAllocateInitialBuffer(byteBuffer, expectedMsgLength, maxMsgLength, defaultMaxMsgSize);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1124 */     int reqEncoding = md.getEncoding();
/* 1125 */     int reqCCSID = md.getCodedCharSetId();
/* 1126 */     byte[] reqMsgId = md.getMsgId();
/* 1127 */     byte[] reqCorrelId = md.getCorrelId();
/* 1128 */     int appOptions = gmo.getOptions();
/* 1129 */     boolean callExitOnLenErr = false;
/*      */     
/* 1131 */     Pint returnedLength = this.env.newPint();
/* 1132 */     int bufferLength = 0;
/* 1133 */     byte[] array = null;
/*      */ 
/*      */     
/* 1136 */     pByteBuffer.getBuffer().limit(pByteBuffer.getBuffer().capacity());
/* 1137 */     pByteBuffer.getBuffer().position(0);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1142 */     int state = 1;
/* 1143 */     while (state != 0) {
/*      */       int availableLength; byte[] oldArray; byte[] newArray; boolean finished;
/* 1145 */       switch (state) {
/*      */ 
/*      */ 
/*      */         
/*      */         case 1:
/* 1150 */           bufferLength = Math.min(pByteBuffer.getBuffer().capacity(), maxMsgLength);
/*      */ 
/*      */           
/* 1153 */           ((JmqiSP)this.jmqi).jmqiGet(hconn, hobj, md, gmo, bufferLength, bufferLength, pByteBuffer, pMsgTooSmallForBufferCount, pDataLength, pCC, pRC);
/*      */ 
/*      */ 
/*      */           
/* 1157 */           switch (pRC.x) {
/*      */             case 0:
/* 1159 */               state = 0;
/*      */               continue;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*      */             case 2010:
/* 1169 */               maxMsgLength = pDataLength.x;
/* 1170 */               md.setEncoding(reqEncoding);
/* 1171 */               md.setCodedCharSetId(reqCCSID);
/* 1172 */               md.setMsgId(reqMsgId);
/* 1173 */               md.setCorrelId(reqCorrelId);
/* 1174 */               state = 1;
/*      */               continue;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*      */             case 2080:
/* 1183 */               pMsgTooSmallForBufferCount.x = 0;
/* 1184 */               if (pDataLength.x < maxMsgLength) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/* 1190 */                 if (bufferLength < pDataLength.x) {
/*      */                   
/* 1192 */                   bufferLength = Math.min(pDataLength.x, maxMsgLength);
/*      */                 } else {
/*      */                   
/* 1195 */                   bufferLength = Math.min(bufferLength * 2, maxMsgLength);
/*      */                 } 
/*      */                 
/* 1198 */                 array = new byte[bufferLength];
/* 1199 */                 pByteBuffer.setBuffer(ByteBuffer.wrap(array));
/* 1200 */                 md.setEncoding(reqEncoding);
/* 1201 */                 md.setCodedCharSetId(reqCCSID);
/* 1202 */                 md.setMsgId(reqMsgId);
/* 1203 */                 md.setCorrelId(reqCorrelId);
/* 1204 */                 state = 1;
/*      */                 continue;
/*      */               } 
/* 1207 */               state = 0;
/*      */               continue;
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*      */             case 2120:
/*      */             case 2190:
/* 1215 */               pMsgTooSmallForBufferCount.x = 0;
/* 1216 */               if (pDataLength.x < maxMsgLength) {
/*      */                 
/* 1218 */                 bufferLength = Math.min(pDataLength.x * 2, maxMsgLength);
/* 1219 */                 state = 2;
/*      */                 continue;
/*      */               } 
/* 1222 */               state = 0;
/*      */               continue;
/*      */           } 
/*      */ 
/*      */           
/* 1227 */           state = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 2:
/* 1240 */           oldArray = pByteBuffer.getBuffer().array();
/* 1241 */           newArray = new byte[bufferLength];
/* 1242 */           System.arraycopy(oldArray, 0, newArray, 0, pDataLength.x);
/* 1243 */           pByteBuffer.setBuffer(ByteBuffer.wrap(newArray));
/* 1244 */           availableLength = pDataLength.x;
/*      */ 
/*      */           
/* 1247 */           finished = ((JmqiSP)this.jmqi).jmqiConvertMessage(hconn, hobj, reqEncoding, reqCCSID, appOptions, callExitOnLenErr, md, pByteBuffer
/*      */               
/* 1249 */               .getBuffer(), pDataLength, availableLength, bufferLength, pCC, pRC, returnedLength);
/*      */ 
/*      */           
/* 1252 */           if (finished) {
/* 1253 */             state = 0;
/*      */             continue;
/*      */           } 
/* 1256 */           switch (pRC.x) {
/*      */             case 0:
/* 1258 */               state = 0;
/*      */               continue;
/*      */ 
/*      */ 
/*      */             
/*      */             case 2120:
/*      */             case 2190:
/* 1265 */               if (pDataLength.x < maxMsgLength) {
/* 1266 */                 bufferLength = Math.min(bufferLength * 2, maxMsgLength);
/*      */                 
/* 1268 */                 state = 2;
/*      */                 continue;
/*      */               } 
/* 1271 */               state = 0;
/*      */               continue;
/*      */           } 
/*      */           
/* 1275 */           state = 0;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     } 
/* 1285 */     ByteBuffer returnBuffer = pByteBuffer.getBuffer();
/* 1286 */     if (pCC.x == 0 || pCC.x == 1) {
/*      */ 
/*      */       
/* 1289 */       if (bufferLength > pDataLength.x * 2 && bufferLength > defaultMaxMsgSize) {
/* 1290 */         pMsgTooSmallForBufferCount.x++;
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 1295 */         pMsgTooSmallForBufferCount.x = 0;
/*      */       } 
/*      */ 
/*      */       
/* 1299 */       if (pMsgTooSmallForBufferCount.x > threshold) {
/* 1300 */         pByteBuffer.setBuffer(null);
/* 1301 */         pMsgTooSmallForBufferCount.x = 0;
/*      */       } 
/*      */ 
/*      */       
/* 1305 */       int limit = Math.min(returnBuffer.capacity(), Math.max(pDataLength.x, 0));
/*      */       
/* 1307 */       returnBuffer.limit(limit);
/* 1308 */       returnBuffer.position(0);
/*      */     } 
/*      */     
/* 1311 */     if (Trace.isOn) {
/* 1312 */       Trace.exit(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "getMessage(Hconn,Hobj,MQMD,MQGMO,int,int,ByteBuffer,Pint,Pint,Pint)", returnBuffer);
/*      */     }
/*      */     
/* 1315 */     return returnBuffer;
/*      */   }
/*      */ 
/*      */   
/*      */   private PbyteBuffer getMsgAllocateInitialBuffer(ByteBuffer byteBuffer, int expectedMsgLength, int maxMsgLength, int defaultMaxMsgSize) {
/* 1320 */     if (Trace.isOn) {
/* 1321 */       Trace.entry(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "getMsgAllocateInitialBuffer(ByteBuffer,int,int,int)", new Object[] { byteBuffer, 
/*      */             
/* 1323 */             Integer.valueOf(expectedMsgLength), Integer.valueOf(maxMsgLength), 
/* 1324 */             Integer.valueOf(defaultMaxMsgSize) });
/*      */     }
/* 1326 */     int initialBufferSize = (expectedMsgLength < 0) ? defaultMaxMsgSize : expectedMsgLength;
/*      */ 
/*      */     
/* 1329 */     initialBufferSize = Math.min(initialBufferSize, maxMsgLength);
/* 1330 */     PbyteBuffer pByteBuffer = this.env.newPbyteBuffer(byteBuffer);
/* 1331 */     if (pByteBuffer.getBuffer() == null || pByteBuffer.getBuffer().capacity() < initialBufferSize) {
/* 1332 */       byte[] array = new byte[initialBufferSize];
/* 1333 */       pByteBuffer.setBuffer(ByteBuffer.wrap(array));
/*      */     } 
/* 1335 */     if (Trace.isOn) {
/* 1336 */       Trace.exit(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "getMsgAllocateInitialBuffer(ByteBuffer,int,int,int)", pByteBuffer);
/*      */     }
/*      */     
/* 1339 */     return pByteBuffer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int getSmallMsgBufferReductionThreshold() {
/* 1348 */     Configuration configuration = this.env.getConfiguration();
/* 1349 */     Configuration.IntCfgProperty property2 = Configuration.smallMsgsBufferReductionThresholdProperty;
/* 1350 */     int threshold = configuration.getIntValue(property2);
/* 1351 */     if (Trace.isOn) {
/* 1352 */       Trace.data(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "getSmallMsgBufferReductionThreshold()", "getter", 
/* 1353 */           Integer.valueOf(threshold));
/*      */     }
/* 1355 */     return threshold;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int getDefaultMaxMessageSize() {
/* 1364 */     Configuration configuration = this.env.getConfiguration();
/* 1365 */     Configuration.IntCfgProperty property1 = Configuration.defaultMaxMsgSizeProperty;
/* 1366 */     int defaultMaxMsgSize = configuration.getIntValue(property1);
/* 1367 */     if (Trace.isOn) {
/* 1368 */       Trace.data(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "getDefaultMaxMessageSize()", "getter", 
/* 1369 */           Integer.valueOf(defaultMaxMsgSize));
/*      */     }
/* 1371 */     return defaultMaxMsgSize;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void putInErrorq(Hconn hconn, String errorq, String qmgrErrorq, String qmgr, String queue, MQMD mqmd, boolean inSyncpoint, ByteBuffer message, int rc) throws EseMQException {
/* 1378 */     if (Trace.isOn) {
/* 1379 */       Trace.entry(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "putInErrorq(Hconn,String,String,String,String,MQMD,boolean,ByteBuffer,int)", new Object[] { hconn, errorq, qmgrErrorq, qmgr, queue, mqmd, 
/*      */             
/* 1381 */             Boolean.valueOf(inSyncpoint), message, 
/* 1382 */             Integer.valueOf(rc) });
/*      */     }
/*      */     
/* 1385 */     MQDLH mqdlh = this.env.newMQDLH();
/* 1386 */     mqdlh.setDestQName(queue);
/* 1387 */     mqdlh.setDestQMgrName(qmgr);
/* 1388 */     mqdlh.setEncoding(mqmd.getEncoding());
/* 1389 */     mqdlh.setCodedCharSetId(mqmd.getCodedCharSetId());
/* 1390 */     mqdlh.setFormat(mqmd.getFormat());
/* 1391 */     mqdlh.setPutApplType(mqmd.getPutApplType());
/* 1392 */     mqdlh.setPutApplName(mqmd.getPutApplName());
/* 1393 */     mqdlh.setPutDate(mqmd.getPutDate());
/* 1394 */     mqdlh.setPutTime(mqmd.getPutTime());
/* 1395 */     mqdlh.setReason(rc);
/*      */     
/* 1397 */     MQOD mqod = this.env.newMQOD();
/* 1398 */     mqod.setObjectType(1);
/* 1399 */     mqod.setObjectName(errorq);
/* 1400 */     if (Trace.isOn) {
/* 1401 */       Trace.traceInfo(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "putInErrorq(Hconn, String, String, String, String, MQMD, boolean, ByteBuffer, int))", "Going to put defective message into error handling queue " + errorq + " original queue was " + queue + " qmgr is " + qmgr, "");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1407 */     MQMD pMsgDesc = this.env.newMQMD();
/* 1408 */     pMsgDesc.setPersistence(1);
/* 1409 */     pMsgDesc.setFormat("MQDEAD  ");
/* 1410 */     MQPMO pPutMsgOpts = this.env.newMQPMO();
/* 1411 */     int bufferLength = MQDLH.getSizeV1(4);
/* 1412 */     byte[] dlhdata = new byte[bufferLength];
/*      */     
/* 1414 */     boolean swap = ((mqdlh.getEncoding() & 0xF) == 2);
/* 1415 */     pMsgDesc.setCodedCharSetId(mqdlh.getCodedCharSetId());
/* 1416 */     pMsgDesc.setEncoding(mqdlh.getEncoding());
/*      */     
/*      */     try {
/* 1419 */       JmqiCodepage cp = JmqiCodepage.getJmqiCodepage(this.env, pMsgDesc.getCodedCharSetId());
/* 1420 */       mqdlh.writeToBuffer(dlhdata, 0, 4, swap, cp, this.jmqi);
/*      */     }
/* 1422 */     catch (JmqiException e) {
/* 1423 */       if (Trace.isOn) {
/* 1424 */         Trace.catchBlock(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "putInErrorq(Hconn,String,String,String,String,MQMD,boolean,ByteBuffer,int)", (Throwable)e, 1);
/*      */       }
/*      */       
/* 1427 */       EseMQException traceRet1 = new EseMQException(e);
/* 1428 */       if (Trace.isOn) {
/* 1429 */         Trace.throwing(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "putInErrorq(Hconn,String,String,String,String,MQMD,boolean,ByteBuffer,int)", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/* 1432 */       throw traceRet1;
/*      */     }
/* 1434 */     catch (Exception e) {
/* 1435 */       if (Trace.isOn) {
/* 1436 */         Trace.catchBlock(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "putInErrorq(Hconn,String,String,String,String,MQMD,boolean,ByteBuffer,int)", e, 2);
/*      */       }
/*      */       
/* 1439 */       EseMQException traceRet2 = new EseMQException(e, 2195);
/* 1440 */       if (Trace.isOn) {
/* 1441 */         Trace.throwing(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "putInErrorq(Hconn,String,String,String,String,MQMD,boolean,ByteBuffer,int)", (Throwable)traceRet2, 2);
/*      */       }
/*      */       
/* 1444 */       throw traceRet2;
/*      */     } 
/* 1446 */     bufferLength += message.limit();
/* 1447 */     ByteBuffer buffer = ByteBuffer.allocate(bufferLength);
/* 1448 */     buffer.put(dlhdata);
/* 1449 */     buffer.put(message);
/* 1450 */     Pint pCompCode = this.env.newPint();
/* 1451 */     Pint pReason = this.env.newPint();
/* 1452 */     if (buffer.position() == buffer.limit()) {
/* 1453 */       buffer.flip();
/*      */     }
/* 1455 */     if (inSyncpoint) {
/* 1456 */       pPutMsgOpts.setOptions(pPutMsgOpts.getOptions() | 0x2);
/*      */     }
/*      */     
/* 1459 */     this.jmqi.MQPUT1(hconn, mqod, pMsgDesc, pPutMsgOpts, bufferLength, buffer, pCompCode, pReason);
/*      */     
/* 1461 */     if (pCompCode.x == 2) {
/* 1462 */       logPutInErrorqFailure(pReason);
/*      */       
/* 1464 */       if (qmgrErrorq.length() > 0) {
/* 1465 */         Pint dlqCC = this.env.newPint(0);
/* 1466 */         Pint dlqRC = this.env.newPint(0);
/*      */         
/* 1468 */         mqod.setObjectName(qmgrErrorq);
/*      */         
/* 1470 */         if (Trace.isOn) {
/* 1471 */           Trace.traceInfo(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "putInErrorq(Hconn, String, String, String, String, MQMD, boolean, ByteBuffer, int))", "Error queue failed, falling back to ", qmgrErrorq);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1476 */         this.jmqi.MQPUT1(hconn, mqod, pMsgDesc, pPutMsgOpts, bufferLength, buffer, dlqCC, dlqRC);
/*      */         
/* 1478 */         if (dlqCC.x == 2) {
/* 1479 */           HashMap<String, Object> inserts = new HashMap<>();
/* 1480 */           inserts.put("AMS_INSERT_MQ_REASON_CODE", Integer.valueOf(dlqRC.x).toString());
/* 1481 */           EseMQException ex = new EseMQException(AmsErrorMessages.mqm_s_get_error_q_failed, inserts);
/* 1482 */           ex.setReason(dlqRC.x);
/* 1483 */           if (Trace.isOn) {
/* 1484 */             Trace.throwing(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "putInErrorq(Hconn,String,String,String,String,MQMD,boolean,ByteBuffer,int)", (Throwable)ex, 3);
/*      */           }
/*      */           
/* 1487 */           throw ex;
/*      */         } 
/* 1489 */         logPutInErrorqSuccess(qmgrErrorq);
/*      */       } 
/*      */     } else {
/*      */       
/* 1493 */       logPutInErrorqSuccess(errorq);
/*      */     } 
/*      */     
/* 1496 */     if (Trace.isOn) {
/* 1497 */       Trace.exit(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "putInErrorq(Hconn,String,String,String,String,MQMD,boolean,ByteBuffer,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void logPutInErrorqFailure(Pint reason) {
/* 1504 */     if (Trace.isOn) {
/* 1505 */       Trace.entry(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "logPutInErrorqFailure(Pint)", new Object[] { reason });
/*      */     }
/*      */     
/* 1508 */     HashMap<String, Object> inserts = new HashMap<>();
/* 1509 */     inserts.put("AMS_INSERT_MQ_REASON_CODE", Integer.valueOf(reason.x).toString());
/* 1510 */     AmsErrorMessages.log(CLASS, "putInErrorq", AmsErrorMessages.mqm_s_get_error_q_failed, inserts);
/* 1511 */     if (Trace.isOn) {
/* 1512 */       Trace.exit(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "logPutInErrorqFailure(Pint)");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void logPutInErrorqSuccess(String errorQName) {
/* 1518 */     if (Trace.isOn) {
/* 1519 */       Trace.entry(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "logPutInErrorqSuccess(String)", new Object[] { errorQName });
/*      */     }
/*      */     
/* 1522 */     HashMap<String, Object> inserts = new HashMap<>();
/* 1523 */     inserts.put("AMS_INSERT_Q_NAME", errorQName);
/* 1524 */     AmsErrorMessages.log(CLASS, "putInErrorq", AmsErrorMessages.mqm_s_get_error_q_ok, inserts);
/* 1525 */     if (Trace.isOn)
/* 1526 */       Trace.exit(this, "com.ibm.mq.ese.service.EseMQServiceImpl", "logPutInErrorqSuccess(String)"); 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\service\EseMQServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */