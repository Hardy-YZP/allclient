/*      */ package com.ibm.mq.jmqi.monitoring;
/*      */ 
/*      */ import com.ibm.mq.exits.MQCSP;
/*      */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*      */ import com.ibm.mq.jmqi.JmqiException;
/*      */ import com.ibm.mq.jmqi.JmqiMQ;
/*      */ import com.ibm.mq.jmqi.JmqiXA;
/*      */ import com.ibm.mq.jmqi.MQBMHO;
/*      */ import com.ibm.mq.jmqi.MQBO;
/*      */ import com.ibm.mq.jmqi.MQCBD;
/*      */ import com.ibm.mq.jmqi.MQCHARV;
/*      */ import com.ibm.mq.jmqi.MQCMHO;
/*      */ import com.ibm.mq.jmqi.MQCNO;
/*      */ import com.ibm.mq.jmqi.MQCTLO;
/*      */ import com.ibm.mq.jmqi.MQDMHO;
/*      */ import com.ibm.mq.jmqi.MQDMPO;
/*      */ import com.ibm.mq.jmqi.MQGMO;
/*      */ import com.ibm.mq.jmqi.MQIMPO;
/*      */ import com.ibm.mq.jmqi.MQMD;
/*      */ import com.ibm.mq.jmqi.MQMHBO;
/*      */ import com.ibm.mq.jmqi.MQOD;
/*      */ import com.ibm.mq.jmqi.MQPD;
/*      */ import com.ibm.mq.jmqi.MQPMO;
/*      */ import com.ibm.mq.jmqi.MQSD;
/*      */ import com.ibm.mq.jmqi.MQSMPO;
/*      */ import com.ibm.mq.jmqi.MQSRO;
/*      */ import com.ibm.mq.jmqi.MQSTS;
/*      */ import com.ibm.mq.jmqi.handles.Hconn;
/*      */ import com.ibm.mq.jmqi.handles.Hmsg;
/*      */ import com.ibm.mq.jmqi.handles.Hobj;
/*      */ import com.ibm.mq.jmqi.handles.PbyteBuffer;
/*      */ import com.ibm.mq.jmqi.handles.Phconn;
/*      */ import com.ibm.mq.jmqi.handles.Phmsg;
/*      */ import com.ibm.mq.jmqi.handles.Phobj;
/*      */ import com.ibm.mq.jmqi.handles.Pint;
/*      */ import com.ibm.mq.jmqi.system.JmqiConnectOptions;
/*      */ import com.ibm.mq.jmqi.system.JmqiMetaData;
/*      */ import com.ibm.mq.jmqi.system.JmqiRunnable;
/*      */ import com.ibm.mq.jmqi.system.JmqiSP;
/*      */ import com.ibm.mq.jmqi.system.LexContext;
/*      */ import com.ibm.mq.jmqi.system.LexFilterElement;
/*      */ import com.ibm.mq.jmqi.system.LexObjectSelector;
/*      */ import com.ibm.mq.jmqi.system.LpiAdoptUserOptions;
/*      */ import com.ibm.mq.jmqi.system.LpiCALLOPT;
/*      */ import com.ibm.mq.jmqi.system.LpiCHLAUTHQuery;
/*      */ import com.ibm.mq.jmqi.system.LpiNotifyDetails;
/*      */ import com.ibm.mq.jmqi.system.LpiPrivConnStruct;
/*      */ import com.ibm.mq.jmqi.system.LpiSD;
/*      */ import com.ibm.mq.jmqi.system.LpiSRD;
/*      */ import com.ibm.mq.jmqi.system.LpiUSD;
/*      */ import com.ibm.mq.jmqi.system.SpiActivate;
/*      */ import com.ibm.mq.jmqi.system.SpiGetOptions;
/*      */ import com.ibm.mq.jmqi.system.SpiOpenOptions;
/*      */ import com.ibm.mq.jmqi.system.SpiPutOptions;
/*      */ import com.ibm.mq.jmqi.system.SpiSyncPointOptions;
/*      */ import com.ibm.mq.jmqi.system.zrfp.Triplet;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.util.List;
/*      */ import javax.transaction.xa.Xid;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class JmqiInterceptAdapter
/*      */   implements JmqiMQ, JmqiSP, JmqiXA
/*      */ {
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/monitoring/JmqiInterceptAdapter.java";
/*      */   private JmqiMQ mq;
/*      */   
/*      */   static {
/*   92 */     if (Trace.isOn) {
/*   93 */       Trace.data("com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/monitoring/JmqiInterceptAdapter.java");
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
/*      */   JmqiInterceptAdapter(JmqiEnvironment env, int options, JmqiMQ mq) {
/*  110 */     if (Trace.isOn) {
/*  111 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "<init>(JmqiEnvironment,int,JmqiMQ)", new Object[] { env, 
/*  112 */             Integer.valueOf(options), mq });
/*      */     }
/*  114 */     this.mq = mq;
/*  115 */     if (Trace.isOn) {
/*  116 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "<init>(JmqiEnvironment,int,JmqiMQ)");
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
/*      */   public void MQBACK(Hconn hconn, Pint compCode, Pint reason) {
/*  129 */     if (Trace.isOn) {
/*  130 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQBACK(Hconn,Pint,Pint)", new Object[] { hconn, compCode, reason });
/*      */     }
/*      */     
/*  133 */     this.mq.MQBACK(hconn, compCode, reason);
/*  134 */     if (Trace.isOn) {
/*  135 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQBACK(Hconn,Pint,Pint)");
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
/*      */   public void MQBEGIN(Hconn hconn, MQBO beginOptions, Pint compCode, Pint reason) {
/*  150 */     if (Trace.isOn) {
/*  151 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQBEGIN(Hconn,MQBO,Pint,Pint)", new Object[] { hconn, beginOptions, compCode, reason });
/*      */     }
/*      */     
/*  154 */     this.mq.MQBEGIN(hconn, beginOptions, compCode, reason);
/*  155 */     if (Trace.isOn) {
/*  156 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQBEGIN(Hconn,MQBO,Pint,Pint)");
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
/*      */   public void MQBUFMH(Hconn conn, Hmsg hmsg, MQBMHO bufMsgHOpts, MQMD msgDesc, int BufferLength, ByteBuffer buffer, Pint dataLength, Pint compCode, Pint reason) {
/*  175 */     if (Trace.isOn) {
/*  176 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQBUFMH(Hconn,Hmsg,MQBMHO,MQMD,int,ByteBuffer,Pint,Pint,Pint)", new Object[] { conn, hmsg, bufMsgHOpts, msgDesc, 
/*      */             
/*  178 */             Integer.valueOf(BufferLength), buffer, dataLength, compCode, reason });
/*      */     }
/*  180 */     this.mq.MQBUFMH(conn, hmsg, bufMsgHOpts, msgDesc, BufferLength, buffer, dataLength, compCode, reason);
/*  181 */     if (Trace.isOn) {
/*  182 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQBUFMH(Hconn,Hmsg,MQBMHO,MQMD,int,ByteBuffer,Pint,Pint,Pint)");
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
/*      */   public void MQCB(Hconn hconn, int operation, MQCBD callbackDesc, Hobj hobj, MQMD msgDesc, MQGMO getMsgOpts, Pint compCode, Pint reason) {
/*  200 */     if (Trace.isOn) {
/*  201 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQCB(Hconn,int,MQCBD,Hobj,MQMD,MQGMO,Pint,Pint)", new Object[] { hconn, 
/*      */             
/*  203 */             Integer.valueOf(operation), callbackDesc, hobj, msgDesc, getMsgOpts, compCode, reason });
/*      */     }
/*  205 */     this.mq.MQCB(hconn, operation, callbackDesc, hobj, msgDesc, getMsgOpts, compCode, reason);
/*  206 */     if (Trace.isOn) {
/*  207 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQCB(Hconn,int,MQCBD,Hobj,MQMD,MQGMO,Pint,Pint)");
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
/*      */   public void MQCLOSE(Hconn hconn, Phobj hobj, int Options, Pint compCode, Pint reason) {
/*  222 */     if (Trace.isOn) {
/*  223 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQCLOSE(Hconn,Phobj,int,Pint,Pint)", new Object[] { hconn, hobj, 
/*  224 */             Integer.valueOf(Options), compCode, reason });
/*      */     }
/*      */     
/*  227 */     this.mq.MQCLOSE(hconn, hobj, Options, compCode, reason);
/*  228 */     if (Trace.isOn) {
/*  229 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQCLOSE(Hconn,Phobj,int,Pint,Pint)");
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
/*      */   public void MQCMIT(Hconn hconn, Pint compCode, Pint reason) {
/*  242 */     if (Trace.isOn) {
/*  243 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQCMIT(Hconn,Pint,Pint)", new Object[] { hconn, compCode, reason });
/*      */     }
/*      */     
/*  246 */     this.mq.MQCMIT(hconn, compCode, reason);
/*  247 */     if (Trace.isOn) {
/*  248 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQCMIT(Hconn,Pint,Pint)");
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
/*      */   public void MQCONN(String mgrName, Phconn hconn, Pint compCode, Pint reason) {
/*  262 */     if (Trace.isOn) {
/*  263 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQCONN(String,Phconn,Pint,Pint)", new Object[] { mgrName, hconn, compCode, reason });
/*      */     }
/*      */     
/*  266 */     this.mq.MQCONN(mgrName, hconn, compCode, reason);
/*  267 */     if (Trace.isOn) {
/*  268 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQCONN(String,Phconn,Pint,Pint)");
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
/*      */   public void MQCONNX(String mgrName, MQCNO connectOpts, Phconn hconn, Pint compCode, Pint reason) {
/*  283 */     if (Trace.isOn) {
/*  284 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQCONNX(String,MQCNO,Phconn,Pint,Pint)", new Object[] { mgrName, connectOpts, hconn, compCode, reason });
/*      */     }
/*      */ 
/*      */     
/*  288 */     this.mq.MQCONNX(mgrName, connectOpts, hconn, compCode, reason);
/*  289 */     if (Trace.isOn) {
/*  290 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQCONNX(String,MQCNO,Phconn,Pint,Pint)");
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
/*      */   public void MQCRTMH(Hconn conn, MQCMHO crtMsgHOpts, Phmsg hmsg, Pint compCode, Pint reason) {
/*  305 */     if (Trace.isOn) {
/*  306 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQCRTMH(Hconn,MQCMHO,Phmsg,Pint,Pint)", new Object[] { conn, crtMsgHOpts, hmsg, compCode, reason });
/*      */     }
/*      */ 
/*      */     
/*  310 */     this.mq.MQCRTMH(conn, crtMsgHOpts, hmsg, compCode, reason);
/*  311 */     if (Trace.isOn) {
/*  312 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQCRTMH(Hconn,MQCMHO,Phmsg,Pint,Pint)");
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
/*      */   public void MQCTL(Hconn hconn, int Operation, MQCTLO controlOpts, Pint compCode, Pint reason) {
/*  327 */     if (Trace.isOn) {
/*  328 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQCTL(Hconn,int,MQCTLO,Pint,Pint)", new Object[] { hconn, 
/*  329 */             Integer.valueOf(Operation), controlOpts, compCode, reason });
/*      */     }
/*      */     
/*  332 */     this.mq.MQCTL(hconn, Operation, controlOpts, compCode, reason);
/*  333 */     if (Trace.isOn) {
/*  334 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQCTL(Hconn,int,MQCTLO,Pint,Pint)");
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
/*      */   public void MQDISC(Phconn hconn, Pint compCode, Pint reason) {
/*  347 */     if (Trace.isOn) {
/*  348 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQDISC(Phconn,Pint,Pint)", new Object[] { hconn, compCode, reason });
/*      */     }
/*      */     
/*  351 */     this.mq.MQDISC(hconn, compCode, reason);
/*  352 */     if (Trace.isOn) {
/*  353 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQDISC(Phconn,Pint,Pint)");
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
/*      */   public void MQDLTMH(Hconn conn, Phmsg hmsg, MQDMHO dltMsgHOpts, Pint compCode, Pint reason) {
/*  368 */     if (Trace.isOn) {
/*  369 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQDLTMH(Hconn,Phmsg,MQDMHO,Pint,Pint)", new Object[] { conn, hmsg, dltMsgHOpts, compCode, reason });
/*      */     }
/*      */ 
/*      */     
/*  373 */     this.mq.MQDLTMH(conn, hmsg, dltMsgHOpts, compCode, reason);
/*  374 */     if (Trace.isOn) {
/*  375 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQDLTMH(Hconn,Phmsg,MQDMHO,Pint,Pint)");
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
/*      */   public void MQDLTMP(Hconn conn, Hmsg hmsg, MQDMPO dltPropOpts, MQCHARV name, Pint compCode, Pint reason) {
/*  391 */     if (Trace.isOn) {
/*  392 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQDLTMP(Hconn,Hmsg,MQDMPO,MQCHARV,Pint,Pint)", new Object[] { conn, hmsg, dltPropOpts, name, compCode, reason });
/*      */     }
/*      */     
/*  395 */     this.mq.MQDLTMP(conn, hmsg, dltPropOpts, name, compCode, reason);
/*  396 */     if (Trace.isOn) {
/*  397 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQDLTMP(Hconn,Hmsg,MQDMPO,MQCHARV,Pint,Pint)");
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
/*      */   public void MQGET(Hconn hconn, Hobj hobj, MQMD msgDesc, MQGMO getMsgOpts, int BufferLength, ByteBuffer buffer, Pint dataLength, Pint compCode, Pint reason) {
/*  416 */     if (Trace.isOn) {
/*  417 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQGET(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,Pint,Pint)", new Object[] { hconn, hobj, msgDesc, getMsgOpts, 
/*      */             
/*  419 */             Integer.valueOf(BufferLength), buffer, dataLength, compCode, reason });
/*      */     }
/*  421 */     this.mq.MQGET(hconn, hobj, msgDesc, getMsgOpts, BufferLength, buffer, dataLength, compCode, reason);
/*  422 */     if (Trace.isOn) {
/*  423 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQGET(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,Pint,Pint)");
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
/*      */   public void MQINQ(Hconn hconn, Hobj hobj, int SelectorCount, int[] selectors, int IntAttrCount, int[] intAttrs, int CharAttrLength, byte[] charAttrs, Pint compCode, Pint reason) {
/*  444 */     if (Trace.isOn) {
/*  445 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", new Object[] { hconn, hobj, 
/*      */             
/*  447 */             Integer.valueOf(SelectorCount), selectors, Integer.valueOf(IntAttrCount), intAttrs, 
/*  448 */             Integer.valueOf(CharAttrLength), charAttrs, compCode, reason });
/*      */     }
/*  450 */     this.mq.MQINQ(hconn, hobj, SelectorCount, selectors, IntAttrCount, intAttrs, CharAttrLength, charAttrs, compCode, reason);
/*  451 */     if (Trace.isOn) {
/*  452 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)");
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
/*      */   public void MQINQMP(Hconn conn, Hmsg hmsg, MQIMPO inqPropOpts, MQCHARV name, MQPD propDesc, Pint type, int ValueLength, ByteBuffer value, Pint dataLength, Pint compCode, Pint reason) {
/*  474 */     if (Trace.isOn) {
/*  475 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQINQMP(Hconn,Hmsg,MQIMPO,MQCHARV,MQPD,Pint,int,ByteBuffer,Pint,Pint,Pint)", new Object[] { conn, hmsg, inqPropOpts, name, propDesc, type, 
/*      */             
/*  477 */             Integer.valueOf(ValueLength), value, dataLength, compCode, reason });
/*      */     }
/*      */     
/*  480 */     this.mq.MQINQMP(conn, hmsg, inqPropOpts, name, propDesc, type, ValueLength, value, dataLength, compCode, reason);
/*  481 */     if (Trace.isOn) {
/*  482 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQINQMP(Hconn,Hmsg,MQIMPO,MQCHARV,MQPD,Pint,int,ByteBuffer,Pint,Pint,Pint)");
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
/*      */   public void MQMHBUF(Hconn conn, Hmsg hmsg, MQMHBO msgHBufOpts, MQCHARV name, MQMD msgDesc, int BufferLength, ByteBuffer buffer, Pint dataLength, Pint compCode, Pint reason) {
/*  502 */     if (Trace.isOn) {
/*  503 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQMHBUF(Hconn,Hmsg,MQMHBO,MQCHARV,MQMD,int,ByteBuffer,Pint,Pint,Pint)", new Object[] { conn, hmsg, msgHBufOpts, name, msgDesc, 
/*      */             
/*  505 */             Integer.valueOf(BufferLength), buffer, dataLength, compCode, reason });
/*      */     }
/*      */     
/*  508 */     this.mq.MQMHBUF(conn, hmsg, msgHBufOpts, name, msgDesc, BufferLength, buffer, dataLength, compCode, reason);
/*  509 */     if (Trace.isOn) {
/*  510 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQMHBUF(Hconn,Hmsg,MQMHBO,MQCHARV,MQMD,int,ByteBuffer,Pint,Pint,Pint)");
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
/*      */   public void MQOPEN(Hconn hconn, MQOD objDesc, int Options, Phobj hobj, Pint compCode, Pint reason) {
/*  526 */     if (Trace.isOn) {
/*  527 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint)", new Object[] { hconn, objDesc, 
/*      */             
/*  529 */             Integer.valueOf(Options), hobj, compCode, reason });
/*      */     }
/*  531 */     this.mq.MQOPEN(hconn, objDesc, Options, hobj, compCode, reason);
/*  532 */     if (Trace.isOn) {
/*  533 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint)");
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
/*      */   public void MQPUT(Hconn hconn, Hobj hobj, MQMD msgDesc, MQPMO putMsgOpts, int BufferLength, ByteBuffer buffer, Pint compCode, Pint reason) {
/*  551 */     if (Trace.isOn) {
/*  552 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQPUT(Hconn,Hobj,MQMD,MQPMO,int,ByteBuffer,Pint,Pint)", new Object[] { hconn, hobj, msgDesc, putMsgOpts, 
/*  553 */             Integer.valueOf(BufferLength), buffer, compCode, reason });
/*      */     }
/*  555 */     this.mq.MQPUT(hconn, hobj, msgDesc, putMsgOpts, BufferLength, buffer, compCode, reason);
/*  556 */     if (Trace.isOn) {
/*  557 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQPUT(Hconn,Hobj,MQMD,MQPMO,int,ByteBuffer,Pint,Pint)");
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
/*      */   public void MQPUT1(Hconn hconn, MQOD objDesc, MQMD msgDesc, MQPMO putMsgOpts, int BufferLength, ByteBuffer buffer, Pint compCode, Pint reason) {
/*  575 */     if (Trace.isOn) {
/*  576 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQPUT1(Hconn,MQOD,MQMD,MQPMO,int,ByteBuffer,Pint,Pint)", new Object[] { hconn, objDesc, msgDesc, putMsgOpts, 
/*      */             
/*  578 */             Integer.valueOf(BufferLength), buffer, compCode, reason });
/*      */     }
/*  580 */     this.mq.MQPUT1(hconn, objDesc, msgDesc, putMsgOpts, BufferLength, buffer, compCode, reason);
/*  581 */     if (Trace.isOn) {
/*  582 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQPUT1(Hconn,MQOD,MQMD,MQPMO,int,ByteBuffer,Pint,Pint)");
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
/*      */   public void MQSET(Hconn hconn, Hobj hobj, int SelectorCount, int[] selectors, int IntAttrCount, int[] intAttrs, int CharAttrLength, byte[] charAttrs, Pint compCode, Pint reason) {
/*  603 */     if (Trace.isOn) {
/*  604 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", new Object[] { hconn, hobj, 
/*      */             
/*  606 */             Integer.valueOf(SelectorCount), selectors, Integer.valueOf(IntAttrCount), intAttrs, 
/*  607 */             Integer.valueOf(CharAttrLength), charAttrs, compCode, reason });
/*      */     }
/*  609 */     this.mq.MQSET(hconn, hobj, SelectorCount, selectors, IntAttrCount, intAttrs, CharAttrLength, charAttrs, compCode, reason);
/*  610 */     if (Trace.isOn) {
/*  611 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)");
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
/*      */   public void MQSETMP(Hconn conn, Hmsg hmsg, MQSMPO setPropOpts, MQCHARV name, MQPD propDesc, int Type, int ValueLength, ByteBuffer value, Pint compCode, Pint reason) {
/*  631 */     if (Trace.isOn) {
/*  632 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQSETMP(Hconn,Hmsg,MQSMPO,MQCHARV,MQPD,int,int,ByteBuffer,Pint,Pint)", new Object[] { conn, hmsg, setPropOpts, name, propDesc, 
/*      */             
/*  634 */             Integer.valueOf(Type), Integer.valueOf(ValueLength), value, compCode, reason });
/*      */     }
/*      */     
/*  637 */     this.mq.MQSETMP(conn, hmsg, setPropOpts, name, propDesc, Type, ValueLength, value, compCode, reason);
/*  638 */     if (Trace.isOn) {
/*  639 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQSETMP(Hconn,Hmsg,MQSMPO,MQCHARV,MQPD,int,int,ByteBuffer,Pint,Pint)");
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
/*      */   public void MQSTAT(Hconn hconn, int type, MQSTS stat, Pint compCode, Pint reason) {
/*  654 */     if (Trace.isOn) {
/*  655 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQSTAT(Hconn,int,MQSTS,Pint,Pint)", new Object[] { hconn, 
/*  656 */             Integer.valueOf(type), stat, compCode, reason });
/*      */     }
/*      */     
/*  659 */     this.mq.MQSTAT(hconn, type, stat, compCode, reason);
/*  660 */     if (Trace.isOn) {
/*  661 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQSTAT(Hconn,int,MQSTS,Pint,Pint)");
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
/*      */   public void MQSUB(Hconn hconn, MQSD subDesc, Phobj hobj, Phobj hsub, Pint compCode, Pint reason) {
/*  677 */     if (Trace.isOn) {
/*  678 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQSUB(Hconn,MQSD,Phobj,Phobj,Pint,Pint)", new Object[] { hconn, subDesc, hobj, hsub, compCode, reason });
/*      */     }
/*      */ 
/*      */     
/*  682 */     this.mq.MQSUB(hconn, subDesc, hobj, hsub, compCode, reason);
/*  683 */     if (Trace.isOn) {
/*  684 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQSUB(Hconn,MQSD,Phobj,Phobj,Pint,Pint)");
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
/*      */   @Deprecated
/*      */   public void MQSUBRQ(Hconn hconn, Phobj hsub, int action, MQSRO subRqOpts, Pint compCode, Pint reason) {
/*  702 */     if (Trace.isOn) {
/*  703 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQSUBRQ(Hconn,Phobj,int,MQSRO,Pint,Pint)", new Object[] { hconn, hsub, 
/*      */             
/*  705 */             Integer.valueOf(action), subRqOpts, compCode, reason });
/*      */     }
/*  707 */     this.mq.MQSUBRQ(hconn, hsub, action, subRqOpts, compCode, reason);
/*  708 */     if (Trace.isOn) {
/*  709 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQSUBRQ(Hconn,Phobj,int,MQSRO,Pint,Pint)");
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
/*      */   public void MQSUBRQ(Hconn hconn, Hobj hsub, int action, MQSRO subRqOpts, Pint compCode, Pint reason) {
/*  725 */     if (Trace.isOn) {
/*  726 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQSUBRQ(Hconn,Hobj,int,MQSRO,Pint,Pint)", new Object[] { hconn, hsub, 
/*      */             
/*  728 */             Integer.valueOf(action), subRqOpts, compCode, reason });
/*      */     }
/*  730 */     this.mq.MQSUBRQ(hconn, hsub, action, subRqOpts, compCode, reason);
/*  731 */     if (Trace.isOn) {
/*  732 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "MQSUBRQ(Hconn,Hobj,int,MQSRO,Pint,Pint)");
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
/*      */   public void authenticate(Hconn hconn, String userId, String password, Pint compCode, Pint reason) {
/*  747 */     if (Trace.isOn) {
/*  748 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "authenticate(Hconn,String,String,Pint,Pint)", new Object[] { hconn, userId, "************", compCode, reason });
/*      */     }
/*      */ 
/*      */     
/*  752 */     this.mq.authenticate(hconn, userId, password, compCode, reason);
/*  753 */     if (Trace.isOn) {
/*  754 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "authenticate(Hconn,String,String,Pint,Pint)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getTlsComponentId() {
/*  765 */     int traceRet1 = this.mq.getTlsComponentId();
/*  766 */     if (Trace.isOn) {
/*  767 */       Trace.data(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "getTlsComponentId()", "getter", 
/*  768 */           Integer.valueOf(traceRet1));
/*      */     }
/*  770 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isAsyncConsumeThread(Hconn hconn) {
/*  779 */     if (Trace.isOn) {
/*  780 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "isAsyncConsumeThread(Hconn)", new Object[] { hconn });
/*      */     }
/*      */     
/*  783 */     boolean traceRet1 = this.mq.isAsyncConsumeThread(hconn);
/*  784 */     if (Trace.isOn) {
/*  785 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "isAsyncConsumeThread(Hconn)", 
/*  786 */           Boolean.valueOf(traceRet1));
/*      */     }
/*  788 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSharedHandlesSupported() throws JmqiException {
/*  797 */     boolean traceRet1 = this.mq.isSharedHandlesSupported();
/*  798 */     if (Trace.isOn) {
/*  799 */       Trace.data(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "isSharedHandlesSupported()", "getter", 
/*  800 */           Boolean.valueOf(traceRet1));
/*      */     }
/*  802 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void checkCmdLevel(Hconn hconn) throws JmqiException {
/*  811 */     if (Trace.isOn) {
/*  812 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "checkCmdLevel(Hconn)", new Object[] { hconn });
/*      */     }
/*      */     
/*  815 */     JmqiSP mqsp = (JmqiSP)this.mq;
/*  816 */     mqsp.checkCmdLevel(hconn);
/*  817 */     if (Trace.isOn) {
/*  818 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "checkCmdLevel(Hconn)");
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
/*      */   public void executeRunnable(Hconn hconn, JmqiRunnable job) throws JmqiException, Exception {
/*  831 */     if (Trace.isOn) {
/*  832 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "executeRunnable(Hconn,JmqiRunnable)", new Object[] { hconn, job });
/*      */     }
/*      */     
/*  835 */     JmqiSP mqsp = (JmqiSP)this.mq;
/*  836 */     mqsp.executeRunnable(hconn, job);
/*  837 */     if (Trace.isOn) {
/*  838 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "executeRunnable(Hconn,JmqiRunnable)");
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
/*      */   public void getMetaData(JmqiMetaData metadata, Pint compCode, Pint reason) {
/*  851 */     if (Trace.isOn) {
/*  852 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "getMetaData(JmqiMetaData,Pint,Pint)", new Object[] { metadata, compCode, reason });
/*      */     }
/*      */     
/*  855 */     JmqiSP mqsp = (JmqiSP)this.mq;
/*  856 */     mqsp.getMetaData(metadata, compCode, reason);
/*  857 */     if (Trace.isOn) {
/*  858 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "getMetaData(JmqiMetaData,Pint,Pint)");
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
/*      */   public void honourRRS(Hconn hconn, Pint compCode, Pint reason) {
/*  871 */     if (Trace.isOn) {
/*  872 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "honourRRS(Hconn,Pint,Pint)", new Object[] { hconn, compCode, reason });
/*      */     }
/*      */     
/*  875 */     JmqiSP mqsp = (JmqiSP)this.mq;
/*  876 */     mqsp.honourRRS(hconn, compCode, reason);
/*  877 */     if (Trace.isOn) {
/*  878 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "honourRRS(Hconn,Pint,Pint)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void jmqiCancelWaitingGets(Hconn notifyHconn, Hconn getterHconn, Hconn helperHconn, Pint pCompCode, Pint pReason) {
/*  889 */     if (Trace.isOn) {
/*  890 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "jmqiCancelWaitingGets(Hconn, Hconn, Hconn, Pint, Pint)", new Object[] { notifyHconn, getterHconn, helperHconn, pCompCode, pReason });
/*      */     }
/*      */ 
/*      */     
/*  894 */     JmqiSP mqsp = (JmqiSP)this.mq;
/*  895 */     mqsp.jmqiCancelWaitingGets(notifyHconn, getterHconn, helperHconn, pCompCode, pReason);
/*  896 */     if (Trace.isOn) {
/*  897 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "jmqiCancelWaitingGets(Hconn, Hconn, Hconn, Pint, Pint)");
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
/*      */   public void jmqiConnect(String mgrName, JmqiConnectOptions jmqiConnectOpts, MQCNO connectOpts, Hconn parentHconn, Phconn hconn, Pint compCode, Pint reason) {
/*  913 */     if (Trace.isOn) {
/*  914 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,Pint,Pint)", new Object[] { mgrName, jmqiConnectOpts, connectOpts, parentHconn, hconn, compCode, reason });
/*      */     }
/*      */ 
/*      */     
/*  918 */     JmqiSP mqsp = (JmqiSP)this.mq;
/*  919 */     mqsp.jmqiConnect(mgrName, jmqiConnectOpts, connectOpts, parentHconn, hconn, compCode, reason);
/*  920 */     if (Trace.isOn) {
/*  921 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,Pint,Pint)");
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
/*      */   public boolean jmqiConvertMessage(Hconn hconn, Hobj hobj, int encoding, int ccsid, int options, boolean callExitOnLenErr, MQMD md, ByteBuffer buffer, Pint dataLength, int availableLength, int bufferLength, Pint compCode, Pint reason, Pint returnedLength) {
/*  947 */     if (Trace.isOn) {
/*  948 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "jmqiConvertMessage(Hconn,Hobj,int,int,int,boolean,MQMD,ByteBuffer,Pint,int,int,Pint,Pint,Pint)", new Object[] { hconn, hobj, 
/*      */             
/*  950 */             Integer.valueOf(encoding), Integer.valueOf(ccsid), 
/*  951 */             Integer.valueOf(options), Boolean.valueOf(callExitOnLenErr), md, buffer, dataLength, 
/*  952 */             Integer.valueOf(availableLength), Integer.valueOf(bufferLength), compCode, reason, returnedLength });
/*      */     }
/*      */     
/*  955 */     JmqiSP mqsp = (JmqiSP)this.mq;
/*      */     
/*  957 */     boolean traceRet1 = mqsp.jmqiConvertMessage(hconn, hobj, encoding, ccsid, options, callExitOnLenErr, md, buffer, dataLength, availableLength, bufferLength, compCode, reason, returnedLength);
/*  958 */     if (Trace.isOn) {
/*  959 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "jmqiConvertMessage(Hconn,Hobj,int,int,int,boolean,MQMD,ByteBuffer,Pint,int,int,Pint,Pint,Pint)", 
/*      */           
/*  961 */           Boolean.valueOf(traceRet1));
/*      */     }
/*  963 */     return traceRet1;
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
/*      */   public ByteBuffer jmqiGet(Hconn hconn, Hobj hobj, MQMD md, MQGMO gmo, int expectedMsgLength, int maxMsgLength, PbyteBuffer byteBuffer, Pint msgTooSmallForBufferCount, Pint dataLength, Pint compCode, Pint reason) {
/*  983 */     if (Trace.isOn) {
/*  984 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "jmqiGet(Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", new Object[] { hconn, hobj, md, gmo, 
/*      */             
/*  986 */             Integer.valueOf(expectedMsgLength), Integer.valueOf(maxMsgLength), byteBuffer, msgTooSmallForBufferCount, dataLength, compCode, reason });
/*      */     }
/*      */     
/*  989 */     JmqiSP mqsp = (JmqiSP)this.mq;
/*  990 */     ByteBuffer traceRet1 = mqsp.jmqiGet(hconn, hobj, md, gmo, expectedMsgLength, maxMsgLength, byteBuffer, msgTooSmallForBufferCount, dataLength, compCode, reason);
/*  991 */     if (Trace.isOn) {
/*  992 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "jmqiGet(Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", traceRet1);
/*      */     }
/*      */     
/*  995 */     return traceRet1;
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
/*      */   public void jmqiNotify(Hconn notifyHconn, Hconn getterHconn, int options, LpiNotifyDetails notifyDetails, Pint compCode, Pint reason) {
/* 1008 */     if (Trace.isOn) {
/* 1009 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "jmqiNotify(Hconn,Hconn,int,LpiNotifyDetails,Pint,Pint)", new Object[] { notifyHconn, getterHconn, 
/*      */             
/* 1011 */             Integer.valueOf(options), notifyDetails, compCode, reason });
/*      */     }
/* 1013 */     JmqiSP mqsp = (JmqiSP)this.mq;
/* 1014 */     mqsp.jmqiNotify(notifyHconn, getterHconn, options, notifyDetails, compCode, reason);
/* 1015 */     if (Trace.isOn) {
/* 1016 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "jmqiNotify(Hconn,Hconn,int,LpiNotifyDetails,Pint,Pint)");
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
/*      */   public void jmqiPut(Hconn hconn, Hobj hobj, MQMD msgDesc, MQPMO putMsgOpts, ByteBuffer[] buffers, Pint compCode, Pint reason) {
/* 1033 */     if (Trace.isOn) {
/* 1034 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],Pint,Pint)", new Object[] { hconn, hobj, msgDesc, putMsgOpts, buffers, compCode, reason });
/*      */     }
/*      */ 
/*      */     
/* 1038 */     JmqiSP mqsp = (JmqiSP)this.mq;
/* 1039 */     mqsp.jmqiPut(hconn, hobj, msgDesc, putMsgOpts, buffers, compCode, reason);
/* 1040 */     if (Trace.isOn) {
/* 1041 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],Pint,Pint)");
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
/*      */   public void jmqiPut1(Hconn hconn, MQOD objDesc, MQMD msgDesc, MQPMO putMsgOpts, ByteBuffer[] buffers, Pint compCode, Pint reason) {
/* 1058 */     if (Trace.isOn) {
/* 1059 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "jmqiPut1(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],Pint,Pint)", new Object[] { hconn, objDesc, msgDesc, putMsgOpts, buffers, compCode, reason });
/*      */     }
/*      */ 
/*      */     
/* 1063 */     JmqiSP mqsp = (JmqiSP)this.mq;
/* 1064 */     mqsp.jmqiPut1(hconn, objDesc, msgDesc, putMsgOpts, buffers, compCode, reason);
/* 1065 */     if (Trace.isOn) {
/* 1066 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "jmqiPut1(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],Pint,Pint)");
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
/*      */   public void spiActivateMessage(Hconn hconn, SpiActivate activateOpts, Pint compCode, Pint reason) {
/* 1080 */     if (Trace.isOn) {
/* 1081 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "spiActivateMessage(Hconn,SpiActivate,Pint,Pint)", new Object[] { hconn, activateOpts, compCode, reason });
/*      */     }
/*      */ 
/*      */     
/* 1085 */     JmqiSP mqsp = (JmqiSP)this.mq;
/* 1086 */     mqsp.spiActivateMessage(hconn, activateOpts, compCode, reason);
/* 1087 */     if (Trace.isOn) {
/* 1088 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "spiActivateMessage(Hconn,SpiActivate,Pint,Pint)");
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
/*      */   public void spiConnect(String mgrName, LpiPrivConnStruct spiConnectOpts, MQCNO connectOpts, Phconn hconn, Pint compCode, Pint reason) {
/* 1104 */     if (Trace.isOn) {
/* 1105 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "spiConnect(String,LpiPrivConnStruct,MQCNO,Phconn,Pint,Pint)", new Object[] { mgrName, spiConnectOpts, connectOpts, hconn, compCode, reason });
/*      */     }
/*      */ 
/*      */     
/* 1109 */     JmqiSP mqsp = (JmqiSP)this.mq;
/* 1110 */     mqsp.spiConnect(mgrName, spiConnectOpts, connectOpts, hconn, compCode, reason);
/* 1111 */     if (Trace.isOn) {
/* 1112 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "spiConnect(String,LpiPrivConnStruct,MQCNO,Phconn,Pint,Pint)");
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
/*      */   public void spiGet(Hconn hconn, Hobj hobj, MQMD mqmd, MQGMO mqgmo, SpiGetOptions spiOptions, int bufferLength, ByteBuffer buffer, Pint dataLength, Pint compCode, Pint reason) {
/* 1132 */     if (Trace.isOn) {
/* 1133 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,int,ByteBuffer,Pint,Pint,Pint)", new Object[] { hconn, hobj, mqmd, mqgmo, spiOptions, 
/*      */             
/* 1135 */             Integer.valueOf(bufferLength), buffer, dataLength, compCode, reason });
/*      */     }
/*      */     
/* 1138 */     JmqiSP mqsp = (JmqiSP)this.mq;
/* 1139 */     mqsp.spiGet(hconn, hobj, mqmd, mqgmo, spiOptions, bufferLength, buffer, dataLength, compCode, reason);
/* 1140 */     if (Trace.isOn) {
/* 1141 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,int,ByteBuffer,Pint,Pint,Pint)");
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
/*      */   public void spiOpen(Hconn hconn, MQOD od, SpiOpenOptions options, Phobj hobj, Pint compCode, Pint reason) {
/* 1157 */     if (Trace.isOn) {
/* 1158 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "spiOpen(Hconn,MQOD,SpiOpenOptions,Phobj,Pint,Pint)", new Object[] { hconn, od, options, hobj, compCode, reason });
/*      */     }
/*      */ 
/*      */     
/* 1162 */     JmqiSP mqsp = (JmqiSP)this.mq;
/* 1163 */     mqsp.spiOpen(hconn, od, options, hobj, compCode, reason);
/* 1164 */     if (Trace.isOn) {
/* 1165 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "spiOpen(Hconn,MQOD,SpiOpenOptions,Phobj,Pint,Pint)");
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
/*      */   public void spiPut(Hconn hconn, Hobj hobj, MQMD mqmd, MQPMO mqpmo, SpiPutOptions spiPutOpts, int bufferLength, ByteBuffer buffer, Pint compCode, Pint reason) {
/* 1184 */     if (Trace.isOn) {
/* 1185 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,int,ByteBuffer,Pint,Pint)", new Object[] { hconn, hobj, mqmd, mqpmo, spiPutOpts, 
/*      */             
/* 1187 */             Integer.valueOf(bufferLength), buffer, compCode, reason });
/*      */     }
/* 1189 */     JmqiSP mqsp = (JmqiSP)this.mq;
/* 1190 */     mqsp.spiPut(hconn, hobj, mqmd, mqpmo, spiPutOpts, bufferLength, buffer, compCode, reason);
/* 1191 */     if (Trace.isOn) {
/* 1192 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,int,ByteBuffer,Pint,Pint)");
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
/*      */   public void spiSubscribe(Hconn hconn, LpiSD plpiSD, MQSD subDesc, Phobj hobj, Phobj hsub, Pint compCode, Pint reason) {
/* 1209 */     if (Trace.isOn) {
/* 1210 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,Pint,Pint)", new Object[] { hconn, plpiSD, subDesc, hobj, hsub, compCode, reason });
/*      */     }
/*      */ 
/*      */     
/* 1214 */     JmqiSP mqsp = (JmqiSP)this.mq;
/* 1215 */     mqsp.spiSubscribe(hconn, plpiSD, subDesc, hobj, hsub, compCode, reason);
/* 1216 */     if (Trace.isOn) {
/* 1217 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,Pint,Pint)");
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
/*      */   public void spiSyncPoint(Hconn hconn, SpiSyncPointOptions spo, Pint compCode, Pint reason) {
/* 1231 */     if (Trace.isOn) {
/* 1232 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "spiSyncPoint(Hconn,SpiSyncPointOptions,Pint,Pint)", new Object[] { hconn, spo, compCode, reason });
/*      */     }
/*      */ 
/*      */     
/* 1236 */     JmqiSP mqsp = (JmqiSP)this.mq;
/* 1237 */     mqsp.spiSyncPoint(hconn, spo, compCode, reason);
/* 1238 */     if (Trace.isOn) {
/* 1239 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "spiSyncPoint(Hconn,SpiSyncPointOptions,Pint,Pint)");
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
/*      */   public void spiUnsubscribe(Hconn hconn, LpiUSD plpiUSD, Pint compCode, Pint reason) {
/* 1253 */     if (Trace.isOn) {
/* 1254 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "spiUnsubscribe(Hconn,LpiUSD,Pint,Pint)", new Object[] { hconn, plpiUSD, compCode, reason });
/*      */     }
/*      */     
/* 1257 */     JmqiSP mqsp = (JmqiSP)this.mq;
/* 1258 */     mqsp.spiUnsubscribe(hconn, plpiUSD, compCode, reason);
/* 1259 */     if (Trace.isOn) {
/* 1260 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "spiUnsubscribe(Hconn,LpiUSD,Pint,Pint)");
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
/*      */   public int xa_close(Hconn hconn, String name, int rmid, int flags) {
/* 1275 */     if (Trace.isOn) {
/* 1276 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "xa_close(Hconn,String,int,int)", new Object[] { hconn, name, 
/* 1277 */             Integer.valueOf(rmid), 
/* 1278 */             Integer.valueOf(flags) });
/*      */     }
/* 1280 */     JmqiXA mqxa = (JmqiXA)this.mq;
/* 1281 */     int traceRet1 = mqxa.xa_close(hconn, name, rmid, flags);
/* 1282 */     if (Trace.isOn) {
/* 1283 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "xa_close(Hconn,String,int,int)", 
/* 1284 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1286 */     return traceRet1;
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
/*      */   public int xa_commit(Hconn hconn, Xid xid, int rmid, int flags) {
/* 1298 */     if (Trace.isOn) {
/* 1299 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "xa_commit(Hconn,Xid,int,int)", new Object[] { hconn, xid, 
/* 1300 */             Integer.valueOf(rmid), 
/* 1301 */             Integer.valueOf(flags) });
/*      */     }
/* 1303 */     JmqiXA mqxa = (JmqiXA)this.mq;
/* 1304 */     int traceRet1 = mqxa.xa_commit(hconn, xid, rmid, flags);
/* 1305 */     if (Trace.isOn) {
/* 1306 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "xa_commit(Hconn,Xid,int,int)", 
/* 1307 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1309 */     return traceRet1;
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
/*      */   public int xa_complete(Hconn hconn, Pint handle, Pint retval, int rmid, int flags) {
/* 1322 */     if (Trace.isOn) {
/* 1323 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "xa_complete(Hconn,Pint,Pint,int,int)", new Object[] { hconn, handle, retval, 
/*      */             
/* 1325 */             Integer.valueOf(rmid), Integer.valueOf(flags) });
/*      */     }
/* 1327 */     JmqiXA mqxa = (JmqiXA)this.mq;
/* 1328 */     int traceRet1 = mqxa.xa_complete(hconn, handle, retval, rmid, flags);
/* 1329 */     if (Trace.isOn) {
/* 1330 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "xa_complete(Hconn,Pint,Pint,int,int)", 
/* 1331 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1333 */     return traceRet1;
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
/*      */   public int xa_end(Hconn hconn, Xid xid, int rmid, int flags) {
/* 1345 */     if (Trace.isOn) {
/* 1346 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "xa_end(Hconn,Xid,int,int)", new Object[] { hconn, xid, 
/* 1347 */             Integer.valueOf(rmid), 
/* 1348 */             Integer.valueOf(flags) });
/*      */     }
/* 1350 */     JmqiXA mqxa = (JmqiXA)this.mq;
/* 1351 */     int traceRet1 = mqxa.xa_end(hconn, xid, rmid, flags);
/* 1352 */     if (Trace.isOn) {
/* 1353 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "xa_end(Hconn,Xid,int,int)", 
/* 1354 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1356 */     return traceRet1;
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
/*      */   public int xa_forget(Hconn hconn, Xid xid, int rmid, int flags) {
/* 1368 */     if (Trace.isOn) {
/* 1369 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "xa_forget(Hconn,Xid,int,int)", new Object[] { hconn, xid, 
/* 1370 */             Integer.valueOf(rmid), 
/* 1371 */             Integer.valueOf(flags) });
/*      */     }
/* 1373 */     JmqiXA mqxa = (JmqiXA)this.mq;
/* 1374 */     int traceRet1 = mqxa.xa_forget(hconn, xid, rmid, flags);
/* 1375 */     if (Trace.isOn) {
/* 1376 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "xa_forget(Hconn,Xid,int,int)", 
/* 1377 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1379 */     return traceRet1;
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
/*      */   public int xa_open(Hconn hconn, String name, int rmid, int flags) {
/* 1391 */     if (Trace.isOn) {
/* 1392 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "xa_open(Hconn,String,int,int)", new Object[] { hconn, name, 
/* 1393 */             Integer.valueOf(rmid), 
/* 1394 */             Integer.valueOf(flags) });
/*      */     }
/* 1396 */     JmqiXA mqxa = (JmqiXA)this.mq;
/* 1397 */     int traceRet1 = mqxa.xa_open(hconn, name, rmid, flags);
/* 1398 */     if (Trace.isOn) {
/* 1399 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "xa_open(Hconn,String,int,int)", 
/* 1400 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1402 */     return traceRet1;
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
/*      */   public int xa_open_tm(Hconn hconn, String name, int rmid, int flags) {
/* 1414 */     if (Trace.isOn) {
/* 1415 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "xa_open_tm(Hconn,String,int,int)", new Object[] { hconn, name, 
/* 1416 */             Integer.valueOf(rmid), 
/* 1417 */             Integer.valueOf(flags) });
/*      */     }
/* 1419 */     JmqiXA mqxa = (JmqiXA)this.mq;
/* 1420 */     int traceRet1 = mqxa.xa_open_tm(hconn, name, rmid, flags);
/* 1421 */     if (Trace.isOn) {
/* 1422 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "xa_open_tm(Hconn,String,int,int)", 
/* 1423 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1425 */     return traceRet1;
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
/*      */   public int xa_prepare(Hconn hconn, Xid xid, int rmid, int flags) {
/* 1437 */     if (Trace.isOn) {
/* 1438 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "xa_prepare(Hconn,Xid,int,int)", new Object[] { hconn, xid, 
/* 1439 */             Integer.valueOf(rmid), 
/* 1440 */             Integer.valueOf(flags) });
/*      */     }
/* 1442 */     JmqiXA mqxa = (JmqiXA)this.mq;
/* 1443 */     int traceRet1 = mqxa.xa_prepare(hconn, xid, rmid, flags);
/* 1444 */     if (Trace.isOn) {
/* 1445 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "xa_prepare(Hconn,Xid,int,int)", 
/* 1446 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1448 */     return traceRet1;
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
/*      */   public int xa_recover(Hconn hconn, Xid[] xids, int rmid, int flags) {
/* 1460 */     if (Trace.isOn) {
/* 1461 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "xa_recover(Hconn,Xid [ ],int,int)", new Object[] { hconn, xids, 
/* 1462 */             Integer.valueOf(rmid), 
/* 1463 */             Integer.valueOf(flags) });
/*      */     }
/* 1465 */     JmqiXA mqxa = (JmqiXA)this.mq;
/* 1466 */     int traceRet1 = mqxa.xa_recover(hconn, xids, rmid, flags);
/* 1467 */     if (Trace.isOn) {
/* 1468 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "xa_recover(Hconn,Xid [ ],int,int)", 
/* 1469 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1471 */     return traceRet1;
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
/*      */   public int xa_rollback(Hconn hconn, Xid xid, int rmid, int flags) {
/* 1483 */     if (Trace.isOn) {
/* 1484 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "xa_rollback(Hconn,Xid,int,int)", new Object[] { hconn, xid, 
/* 1485 */             Integer.valueOf(rmid), 
/* 1486 */             Integer.valueOf(flags) });
/*      */     }
/* 1488 */     JmqiXA mqxa = (JmqiXA)this.mq;
/* 1489 */     int traceRet1 = mqxa.xa_rollback(hconn, xid, rmid, flags);
/* 1490 */     if (Trace.isOn) {
/* 1491 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "xa_rollback(Hconn,Xid,int,int)", 
/* 1492 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1494 */     return traceRet1;
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
/*      */   public int xa_start(Hconn hconn, Xid xid, int rmid, int flags) {
/* 1506 */     if (Trace.isOn) {
/* 1507 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "xa_start(Hconn,Xid,int,int)", new Object[] { hconn, xid, 
/* 1508 */             Integer.valueOf(rmid), 
/* 1509 */             Integer.valueOf(flags) });
/*      */     }
/* 1511 */     JmqiXA mqxa = (JmqiXA)this.mq;
/* 1512 */     int traceRet1 = mqxa.xa_start(hconn, xid, rmid, flags);
/* 1513 */     if (Trace.isOn) {
/* 1514 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "xa_start(Hconn,Xid,int,int)", 
/* 1515 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1517 */     return traceRet1;
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
/*      */   public void jmqiPut1WithTriplets(Hconn hconn, MQOD objDesc, MQMD msgDesc, MQPMO putMsgOpts, ByteBuffer[] buffers, Triplet[] triplets, Pint compCode, Pint reason) {
/* 1532 */     if (Trace.isOn) {
/* 1533 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "jmqiPut1WithTriplets(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],Triplet [ ],Pint,Pint)", new Object[] { hconn, objDesc, msgDesc, putMsgOpts, buffers, triplets, compCode, reason });
/*      */     }
/*      */ 
/*      */     
/* 1537 */     JmqiSP mqsp = (JmqiSP)this.mq;
/* 1538 */     mqsp.jmqiPut1WithTriplets(hconn, objDesc, msgDesc, putMsgOpts, buffers, triplets, compCode, reason);
/* 1539 */     if (Trace.isOn) {
/* 1540 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "jmqiPut1WithTriplets(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],Triplet [ ],Pint,Pint)");
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
/*      */   public void jmqiPutWithTriplets(Hconn hconn, Hobj hobj, MQMD msgDesc, MQPMO putMsgOpts, ByteBuffer[] buffers, Triplet[] triplets, Pint compCode, Pint reason) {
/* 1558 */     if (Trace.isOn) {
/* 1559 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "jmqiPutWithTriplets(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],Triplet [ ],Pint,Pint)", new Object[] { hconn, hobj, msgDesc, putMsgOpts, buffers, triplets, compCode, reason });
/*      */     }
/*      */ 
/*      */     
/* 1563 */     JmqiSP mqsp = (JmqiSP)this.mq;
/* 1564 */     mqsp.jmqiPutWithTriplets(hconn, hobj, msgDesc, putMsgOpts, buffers, triplets, compCode, reason);
/* 1565 */     if (Trace.isOn) {
/* 1566 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "jmqiPutWithTriplets(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],Triplet [ ],Pint,Pint)");
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
/*      */   public void spiDefine(Hconn hconn, boolean replace, LexObjectSelector objectSelector, String likeObjectName, int SelectorCount, int[] pSelectors, int IntAttrCount, int[] pIntAttrs, int CharAttrLength, byte[] pCharAttrs, LexFilterElement filterElement, int AttrErrorsCount, int[] AttrErrors, Pint pCompCode, Pint pReason) {
/* 1592 */     if (Trace.isOn) {
/* 1593 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "spiDefine(Hconn,boolean,LexObjectSelector,String,int,int [ ],final int,int [ ],int,byte [ ],LexFilterElement,int,int [ ],Pint,Pint)", new Object[] { hconn, 
/*      */             
/* 1595 */             Boolean.valueOf(replace), objectSelector, likeObjectName, 
/* 1596 */             Integer.valueOf(SelectorCount), pSelectors, Integer.valueOf(IntAttrCount), pIntAttrs, 
/* 1597 */             Integer.valueOf(CharAttrLength), pCharAttrs, filterElement, 
/* 1598 */             Integer.valueOf(AttrErrorsCount), AttrErrors, pCompCode, pReason });
/*      */     }
/* 1600 */     JmqiSP mqsp = (JmqiSP)this.mq;
/* 1601 */     mqsp.spiDefine(hconn, replace, objectSelector, likeObjectName, SelectorCount, pSelectors, IntAttrCount, pIntAttrs, CharAttrLength, pCharAttrs, filterElement, AttrErrorsCount, AttrErrors, pCompCode, pReason);
/*      */     
/* 1603 */     if (Trace.isOn) {
/* 1604 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "spiDefine(Hconn,boolean,LexObjectSelector,String,int,int [ ],final int,int [ ],int,byte [ ],LexFilterElement,int,int [ ],Pint,Pint)");
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
/*      */   public void spiInquire(Hconn hconn, LexObjectSelector objectSelector, int SelectorCount, int[] pSelectors, int IntAttrCount, int[] pIntAttrs, int CharAttrLength, byte[] pCharAttrs, LexFilterElement filterElement, Pint pCompCode, Pint pReason) {
/* 1626 */     if (Trace.isOn) {
/* 1627 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "spiInquire(Hconn,LexObjectSelector,int,int [ ],int,int [ ],int,final byte [ ],LexFilterElement,Pint,final Pint)", new Object[] { hconn, objectSelector, 
/*      */             
/* 1629 */             Integer.valueOf(SelectorCount), pSelectors, 
/* 1630 */             Integer.valueOf(IntAttrCount), pIntAttrs, Integer.valueOf(CharAttrLength), pCharAttrs, filterElement, pCompCode, pReason });
/*      */     }
/*      */     
/* 1633 */     JmqiSP mqsp = (JmqiSP)this.mq;
/* 1634 */     mqsp.spiInquire(hconn, objectSelector, SelectorCount, pSelectors, IntAttrCount, pIntAttrs, CharAttrLength, pCharAttrs, filterElement, pCompCode, pReason);
/* 1635 */     if (Trace.isOn) {
/* 1636 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "spiInquire(Hconn,LexObjectSelector,int,int [ ],int,int [ ],int,final byte [ ],LexFilterElement,Pint,final Pint)");
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
/*      */   public void spiDelete(Hconn hconn, LexObjectSelector objectSelector, boolean force, Pint pCompCode, Pint pReason) {
/* 1651 */     if (Trace.isOn) {
/* 1652 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "spiDelete(Hconn,LexObjectSelector,boolean,Pint,Pint)", new Object[] { hconn, objectSelector, 
/* 1653 */             Boolean.valueOf(force), pCompCode, pReason });
/*      */     }
/* 1655 */     JmqiSP mqsp = (JmqiSP)this.mq;
/* 1656 */     mqsp.spiDelete(hconn, objectSelector, force, pCompCode, pReason);
/* 1657 */     if (Trace.isOn) {
/* 1658 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "spiDelete(Hconn,LexObjectSelector,boolean,Pint,Pint)");
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
/*      */   public void lpiSPISubscriptionRequest(Hconn hconn, Hobj hSub, int Action, LpiSRD lpiSRD, MQSRO SubReqOptPint, Pint pCompCode, Pint pReason) {
/* 1675 */     if (Trace.isOn) {
/* 1676 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "lpiSPISubscriptionRequest(Hconn,Hobj,int,LpiSRD,MQSRO,Pint,Pint)", new Object[] { hconn, hSub, 
/*      */             
/* 1678 */             Integer.valueOf(Action), lpiSRD, SubReqOptPint, pCompCode, pReason });
/*      */     }
/* 1680 */     JmqiSP mqsp = (JmqiSP)this.mq;
/* 1681 */     mqsp.lpiSPISubscriptionRequest(hconn, hSub, Action, lpiSRD, SubReqOptPint, pCompCode, pReason);
/* 1682 */     if (Trace.isOn) {
/* 1683 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "lpiSPISubscriptionRequest(Hconn,Hobj,int,LpiSRD,MQSRO,Pint,Pint)");
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
/*      */   public List<byte[]> jmqiInquireNamedSubscribers(Hconn hconn, LpiCALLOPT callOptions, String subName, Pint pCompCode, Pint pReason) {
/* 1699 */     if (Trace.isOn) {
/* 1700 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "jmqiInquireNamedSubscribers(Hconn,LpiCALLOPT,String,Pint,Pint)", new Object[] { hconn, callOptions, subName, pCompCode, pReason });
/*      */     }
/*      */ 
/*      */     
/* 1704 */     JmqiSP mqsp = (JmqiSP)this.mq;
/* 1705 */     List<byte[]> traceRet1 = mqsp.jmqiInquireNamedSubscribers(hconn, callOptions, subName, pCompCode, pReason);
/* 1706 */     if (Trace.isOn) {
/* 1707 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "jmqiInquireNamedSubscribers(Hconn,LpiCALLOPT,String,Pint,Pint)", traceRet1);
/*      */     }
/*      */     
/* 1710 */     return traceRet1;
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
/*      */   public void lpiSPIInquireNamedSubscribers(Hconn hconn, byte[] CallOptions, int CallOptionsLength, byte[] SubName, int SubNameLength, byte[] SubIdBuffer, Pint SubIdBufferLength, Pint SubscribersReturned, Pint pCompCode, Pint pReason) {
/* 1731 */     if (Trace.isOn) {
/* 1732 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "lpiSPIInquireNamedSubscribers(Hconn,byte [ ],int,byte [ ],int,byte [ ],Pint,Pint,Pint,Pint)", new Object[] { hconn, CallOptions, 
/*      */             
/* 1734 */             Integer.valueOf(CallOptionsLength), SubName, 
/* 1735 */             Integer.valueOf(SubNameLength), SubIdBuffer, SubIdBufferLength, SubscribersReturned, pCompCode, pReason });
/*      */     }
/*      */     
/* 1738 */     JmqiSP mqsp = (JmqiSP)this.mq;
/* 1739 */     mqsp.lpiSPIInquireNamedSubscribers(hconn, CallOptions, CallOptionsLength, SubName, SubNameLength, SubIdBuffer, SubIdBufferLength, SubscribersReturned, pCompCode, pReason);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1744 */     if (Trace.isOn) {
/* 1745 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "lpiSPIInquireNamedSubscribers(Hconn,byte [ ],int,byte [ ],int,byte [ ],Pint,Pint,Pint,Pint)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMqiId() {
/* 1756 */     int traceRet1 = ((JmqiSP)this.mq).getMqiId();
/* 1757 */     if (Trace.isOn) {
/* 1758 */       Trace.data(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "getMqiId()", "getter", 
/* 1759 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1761 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void spiPut(Hconn hconn, Hobj hobj, MQMD pMqmd, MQPMO pMqpmo, SpiPutOptions pSpiPutOpts, ByteBuffer[] pBuffers, Pint pCompCode, Pint pReason) {
/* 1769 */     if (Trace.isOn) {
/* 1770 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,ByteBuffer [ ],Pint,Pint)", new Object[] { hconn, hobj, pMqmd, pMqpmo, pSpiPutOpts, pBuffers, pCompCode, pReason });
/*      */     }
/*      */     
/* 1773 */     ((JmqiSP)this.mq).spiPut(hconn, hobj, pMqmd, pMqpmo, pSpiPutOpts, pBuffers, pCompCode, pReason);
/*      */     
/* 1775 */     if (Trace.isOn) {
/* 1776 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,ByteBuffer [ ],Pint,Pint)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isLocal() {
/* 1787 */     boolean traceRet1 = this.mq.isLocal();
/* 1788 */     if (Trace.isOn) {
/* 1789 */       Trace.data(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "isLocal()", "getter", 
/* 1790 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 1792 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isCICS() {
/* 1800 */     boolean traceRet1 = ((JmqiSP)this.mq).isCICS();
/* 1801 */     if (Trace.isOn) {
/* 1802 */       Trace.data(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "isCICS()", "getter", 
/* 1803 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 1805 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isIMS() {
/* 1813 */     boolean traceRet1 = ((JmqiSP)this.mq).isIMS();
/* 1814 */     if (Trace.isOn) {
/* 1815 */       Trace.data(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "isIMS()", "getter", 
/* 1816 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 1818 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void lpiSPIAdoptUser(Hconn hconn, int flags, LexContext userContext, String applName, int applType, byte[] acctToken, int authToken, LpiAdoptUserOptions options, MQCSP securityParms, int connectOptions, byte[] connectionId, Pint pCompCode, Pint pReason) {
/* 1827 */     if (Trace.isOn) {
/* 1828 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "lpiSPIAdoptUser(Hconn,int,LexContext,String,int,byte [ ],int,LpiAdoptUserOptions,MQCSP,int,byte [ ],Pint,Pint)", new Object[] { hconn, 
/*      */             
/* 1830 */             Integer.valueOf(flags), userContext, applName, 
/* 1831 */             Integer.valueOf(applType), acctToken, Integer.valueOf(authToken), options, securityParms, 
/* 1832 */             Integer.valueOf(connectOptions), connectionId, pCompCode, pReason });
/*      */     }
/* 1834 */     ((JmqiSP)this.mq).lpiSPIAdoptUser(hconn, flags, userContext, applName, applType, acctToken, authToken, options, securityParms, connectOptions, connectionId, pCompCode, pReason);
/*      */ 
/*      */     
/* 1837 */     if (Trace.isOn) {
/* 1838 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "lpiSPIAdoptUser(Hconn,int,LexContext,String,int,byte [ ],int,LpiAdoptUserOptions,MQCSP,int,byte [ ],Pint,Pint)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void lpiSPICHLAUTH(Hconn hConn, LpiCHLAUTHQuery pParms, Pint pCompCode, Pint pReason) {
/* 1849 */     if (Trace.isOn) {
/* 1850 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "lpiSPICHLAUTH(Hconn,LpiCHLAUTHQuery,Pint,Pint)", new Object[] { hConn, pParms, pCompCode, pReason });
/*      */     }
/*      */ 
/*      */     
/* 1854 */     ((JmqiSP)this.mq).lpiSPICHLAUTH(hConn, pParms, pCompCode, pReason);
/*      */     
/* 1856 */     if (Trace.isOn) {
/* 1857 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "lpiSPICHLAUTH(Hconn,LpiCHLAUTHQuery,Pint,Pint)");
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
/*      */   public void lpiSPIMapCredentials(Hconn hConn, LexContext userContext, String mcaUser, MQCSP securityParms, byte[] accountingToken, String applName, String channelName, String connectionName, Pint pCompCode, Pint pReason) {
/* 1874 */     if (Trace.isOn) {
/* 1875 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "lpiSPIMapCredentials(Hconn,LexContext,String,MQCSP,byte [ ],String,String,String,Pint,Pint)", new Object[] { hConn, userContext, mcaUser, securityParms, accountingToken, applName, channelName, connectionName, pCompCode, pReason });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1880 */     ((JmqiSP)this.mq).lpiSPIMapCredentials(hConn, userContext, mcaUser, securityParms, accountingToken, applName, channelName, connectionName, pCompCode, pReason);
/*      */     
/* 1882 */     if (Trace.isOn) {
/* 1883 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.JmqiInterceptAdapter", "lpiSPIMapCredentials(Hconn,LexContext,String,MQCSP,byte [ ],String,String,String,Pint,Pint)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void lpiSPICheckPrivileged(Hconn hconn, String componentName, String entityName, int entityType, Pint pCompCode, Pint pReason) {
/* 1894 */     if (Trace.isOn) {
/* 1895 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "lpiSPICheckPrivileged(Hconn,String,String,int,Pint,Pint)", new Object[] { hconn, componentName, entityName, 
/*      */             
/* 1897 */             Integer.valueOf(entityType), pCompCode, pReason });
/*      */     }
/* 1899 */     ((JmqiSP)this.mq).lpiSPICheckPrivileged(hconn, componentName, entityName, entityType, pCompCode, pReason);
/*      */     
/* 1901 */     if (Trace.isOn) {
/* 1902 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteFAP", "lpiSPICheckPrivileged(Hconn,String,String,int,Pint,Pint)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getReconnectCycle() {
/* 1913 */     return ((JmqiSP)this.mq).getReconnectCycle();
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\monitoring\JmqiInterceptAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */