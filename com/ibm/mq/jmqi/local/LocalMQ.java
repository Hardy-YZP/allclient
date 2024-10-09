/*       */ package com.ibm.mq.jmqi.local;
/*       */ 
/*       */ import com.ibm.mq.constants.CMQC;
/*       */ import com.ibm.mq.constants.MQConstants;
/*       */ import com.ibm.mq.exits.MQCD;
/*       */ import com.ibm.mq.exits.MQCSP;
/*       */ import com.ibm.mq.exits.MQCXP;
/*       */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*       */ import com.ibm.mq.jmqi.JmqiException;
/*       */ import com.ibm.mq.jmqi.JmqiImplementation;
/*       */ import com.ibm.mq.jmqi.JmqiMQ;
/*       */ import com.ibm.mq.jmqi.JmqiXA;
/*       */ import com.ibm.mq.jmqi.JmqiXid;
/*       */ import com.ibm.mq.jmqi.MQAIR;
/*       */ import com.ibm.mq.jmqi.MQBMHO;
/*       */ import com.ibm.mq.jmqi.MQBO;
/*       */ import com.ibm.mq.jmqi.MQCBC;
/*       */ import com.ibm.mq.jmqi.MQCBD;
/*       */ import com.ibm.mq.jmqi.MQCHARV;
/*       */ import com.ibm.mq.jmqi.MQCMHO;
/*       */ import com.ibm.mq.jmqi.MQCNO;
/*       */ import com.ibm.mq.jmqi.MQCTLO;
/*       */ import com.ibm.mq.jmqi.MQDLH;
/*       */ import com.ibm.mq.jmqi.MQDMHO;
/*       */ import com.ibm.mq.jmqi.MQDMPO;
/*       */ import com.ibm.mq.jmqi.MQGMO;
/*       */ import com.ibm.mq.jmqi.MQIMPO;
/*       */ import com.ibm.mq.jmqi.MQMD;
/*       */ import com.ibm.mq.jmqi.MQMDE;
/*       */ import com.ibm.mq.jmqi.MQMHBO;
/*       */ import com.ibm.mq.jmqi.MQOD;
/*       */ import com.ibm.mq.jmqi.MQOR;
/*       */ import com.ibm.mq.jmqi.MQPD;
/*       */ import com.ibm.mq.jmqi.MQPMO;
/*       */ import com.ibm.mq.jmqi.MQRFH;
/*       */ import com.ibm.mq.jmqi.MQRR;
/*       */ import com.ibm.mq.jmqi.MQSCO;
/*       */ import com.ibm.mq.jmqi.MQSD;
/*       */ import com.ibm.mq.jmqi.MQSMPO;
/*       */ import com.ibm.mq.jmqi.MQSRO;
/*       */ import com.ibm.mq.jmqi.MQSTS;
/*       */ import com.ibm.mq.jmqi.handles.Hconn;
/*       */ import com.ibm.mq.jmqi.handles.Hmsg;
/*       */ import com.ibm.mq.jmqi.handles.Hobj;
/*       */ import com.ibm.mq.jmqi.handles.PbyteBuffer;
/*       */ import com.ibm.mq.jmqi.handles.Phconn;
/*       */ import com.ibm.mq.jmqi.handles.Phmsg;
/*       */ import com.ibm.mq.jmqi.handles.Phobj;
/*       */ import com.ibm.mq.jmqi.handles.Pint;
/*       */ import com.ibm.mq.jmqi.internal.Configuration;
/*       */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*       */ import com.ibm.mq.jmqi.internal.JmqiTools;
/*       */ import com.ibm.mq.jmqi.local.internal.LocalProxyConsumer;
/*       */ import com.ibm.mq.jmqi.local.internal.MqStructure;
/*       */ import com.ibm.mq.jmqi.local.internal.MqStructureKey;
/*       */ import com.ibm.mq.jmqi.local.internal.NativeTraceHandler;
/*       */ import com.ibm.mq.jmqi.local.internal.adapters.Adapter;
/*       */ import com.ibm.mq.jmqi.local.internal.adapters.BatchAdapter32;
/*       */ import com.ibm.mq.jmqi.local.internal.adapters.BatchAdapter64;
/*       */ import com.ibm.mq.jmqi.local.internal.adapters.CICSAdapter32;
/*       */ import com.ibm.mq.jmqi.local.internal.adapters.CICSAdapter64;
/*       */ import com.ibm.mq.jmqi.local.internal.adapters.DefaultAdapter;
/*       */ import com.ibm.mq.jmqi.local.internal.adapters.ExternalRRSAdapter32;
/*       */ import com.ibm.mq.jmqi.local.internal.adapters.ExternalRRSAdapter64;
/*       */ import com.ibm.mq.jmqi.local.internal.adapters.IMSAdapter31;
/*       */ import com.ibm.mq.jmqi.local.internal.adapters.IMSAdapter64;
/*       */ import com.ibm.mq.jmqi.local.internal.adapters.InternalRRSAdapter32;
/*       */ import com.ibm.mq.jmqi.local.internal.adapters.InternalRRSAdapter64;
/*       */ import com.ibm.mq.jmqi.local.internal.adapters.IseriesAdapter;
/*       */ import com.ibm.mq.jmqi.local.internal.adapters.WASAdapter32;
/*       */ import com.ibm.mq.jmqi.local.internal.adapters.WASAdapter64;
/*       */ import com.ibm.mq.jmqi.local.internal.adapters.WMBAdapter32;
/*       */ import com.ibm.mq.jmqi.local.internal.adapters.WMBAdapter64;
/*       */ import com.ibm.mq.jmqi.local.internal.adapters.WindowsAdapter;
/*       */ import com.ibm.mq.jmqi.local.internal.adapters.WindowsAdapter64;
/*       */ import com.ibm.mq.jmqi.local.internal.base.Native;
/*       */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*       */ import com.ibm.mq.jmqi.system.JmqiComponent;
/*       */ import com.ibm.mq.jmqi.system.JmqiComponentTls;
/*       */ import com.ibm.mq.jmqi.system.JmqiConnectOptions;
/*       */ import com.ibm.mq.jmqi.system.JmqiMetaData;
/*       */ import com.ibm.mq.jmqi.system.JmqiRunnable;
/*       */ import com.ibm.mq.jmqi.system.JmqiSP;
/*       */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*       */ import com.ibm.mq.jmqi.system.JmqiTls;
/*       */ import com.ibm.mq.jmqi.system.LexContext;
/*       */ import com.ibm.mq.jmqi.system.LexFilterElement;
/*       */ import com.ibm.mq.jmqi.system.LexObjectSelector;
/*       */ import com.ibm.mq.jmqi.system.LpiAdoptUserOptions;
/*       */ import com.ibm.mq.jmqi.system.LpiCALLOPT;
/*       */ import com.ibm.mq.jmqi.system.LpiCHLAUTHQuery;
/*       */ import com.ibm.mq.jmqi.system.LpiNotifyDetails;
/*       */ import com.ibm.mq.jmqi.system.LpiPrivConnStruct;
/*       */ import com.ibm.mq.jmqi.system.LpiSD;
/*       */ import com.ibm.mq.jmqi.system.LpiSDSubProps;
/*       */ import com.ibm.mq.jmqi.system.LpiSRD;
/*       */ import com.ibm.mq.jmqi.system.LpiUSD;
/*       */ import com.ibm.mq.jmqi.system.RXPB;
/*       */ import com.ibm.mq.jmqi.system.SpiActivate;
/*       */ import com.ibm.mq.jmqi.system.SpiGetOptions;
/*       */ import com.ibm.mq.jmqi.system.SpiOpenOptions;
/*       */ import com.ibm.mq.jmqi.system.SpiPutOptions;
/*       */ import com.ibm.mq.jmqi.system.SpiSyncPointOptions;
/*       */ import com.ibm.mq.jmqi.system.zrfp.Triplet;
/*       */ import com.ibm.msg.client.commonservices.cssystem.CSSystem;
/*       */ import com.ibm.msg.client.commonservices.trace.DumpableComponent;
/*       */ import com.ibm.msg.client.commonservices.trace.Trace;
/*       */ import java.io.File;
/*       */ import java.io.PrintWriter;
/*       */ import java.io.UnsupportedEncodingException;
/*       */ import java.nio.ByteBuffer;
/*       */ import java.nio.ByteOrder;
/*       */ import java.security.AccessControlException;
/*       */ import java.security.AccessController;
/*       */ import java.security.PrivilegedAction;
/*       */ import java.util.ArrayList;
/*       */ import java.util.Arrays;
/*       */ import java.util.HashMap;
/*       */ import java.util.List;
/*       */ import java.util.Map;
/*       */ import javax.transaction.xa.Xid;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ public abstract class LocalMQ
/*       */   extends JmqiImplementation
/*       */   implements JmqiMQ, JmqiSP, JmqiXA, JmqiComponent, DumpableComponent
/*       */ {
/*       */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/LocalMQ.java";
/*       */   public static final boolean DEBUG = false;
/*       */   private static final int TYPE_BATCH = 1;
/*       */   private static final int TYPE_CICS = 2;
/*       */   private static final int TYPE_WEBSPHERE = 3;
/*       */   private static final int TYPE_IMS = 4;
/*       */   private static final int JAVA_HANDLES_RXPB_FLAGS = 1;
/*       */   private static JmqiCodepage nativeCp;
/*       */   private static boolean swap;
/*       */   
/*       */   static {
/*   152 */     if (Trace.isOn) {
/*   153 */       Trace.data("com.ibm.mq.jmqi.local.LocalMQ", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/LocalMQ.java");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   private static boolean isInitialised = false;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*   181 */   private static final Object initialiseLock = new Object();
/*       */   
/*       */   private static Adapter cachedAdapter;
/*       */   
/*       */   private static int ptrSize;
/*       */   
/*       */   private static int cmdLevel;
/*       */   
/*       */   private JmqiSystemEnvironment sysenv;
/*       */   
/*       */   private int jmqiCompId;
/*       */   
/*       */   private int mqiOptions;
/*       */   
/*       */   private boolean useWorkerThread;
/*       */   
/*       */   private boolean useWorkerThreadForAsyncOnly = false;
/*       */   
/*       */   private boolean useSharedHconn;
/*       */   
/*       */   private NativeTraceHandler nativeTraceHandler;
/*       */   private boolean ffstOnError;
/*       */   private boolean inheritRRSContext;
/*       */   private boolean adapterIsRRS;
/*       */   
/*       */   private void initialise() throws JmqiException {
/*   207 */     if (Trace.isOn) {
/*   208 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "initialise()");
/*       */     }
/*       */     
/*   211 */     if (Trace.isOn) {
/*   212 */       Trace.data(this, "initialise()", "Before initialise_inner ptrSize", Integer.valueOf(ptrSize));
/*       */     }
/*       */ 
/*       */     
/*   216 */     if (!isInitialised) {
/*   217 */       synchronized (initialiseLock) {
/*   218 */         if (!isInitialised) {
/*   219 */           initialise_inner();
/*   220 */           isInitialised = true;
/*       */         } 
/*       */       } 
/*       */     }
/*       */     
/*   225 */     if (Trace.isOn) {
/*   226 */       Trace.data(this, "initialise()", "After initialise_inner ptrSize", Integer.valueOf(ptrSize));
/*       */     }
/*       */     
/*   229 */     if (Trace.isOn) {
/*   230 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "initialise()");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   private void initialise_inner() throws JmqiException {
/*   242 */     if (Trace.isOn) {
/*   243 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "initialise_inner()");
/*       */     }
/*   245 */     String fid = "initialise_inner()";
/*       */     
/*   247 */     if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
/*   248 */       swap = true;
/*       */     }
/*       */     
/*   251 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*   252 */     final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/*       */     
/*   254 */     nativeCp = this.env.getNativeCharSet();
/*       */     
/*   256 */     final LocalMQ parent = this;
/*   257 */     Object object = AccessController.doPrivileged(new PrivilegedAction()
/*       */         {
/*       */           public Object run()
/*       */           {
/*   261 */             if (Trace.isOn) {
/*   262 */               Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */             }
/*   264 */             Object returnValue = null;
/*   265 */             String libraryName = null;
/*       */ 
/*       */ 
/*       */             
/*       */             try {
/*   270 */               if (LocalMQ.getCachedAdapter() == null) {
/*   271 */                 IseriesAdapter iseriesAdapter; WindowsAdapter windowsAdapter; DefaultAdapter defaultAdapter; Adapter adapter = null;
/*   272 */                 switch (CSSystem.currentPlatform()) {
/*       */                   case BATCH:
/*   274 */                     adapter = LocalMQ.this.get390Adapter();
/*       */                     break;
/*       */                   case WAS:
/*   277 */                     iseriesAdapter = new IseriesAdapter(LocalMQ.this.env, parent);
/*       */                     break;
/*       */                   case CICS:
/*   280 */                     if ("64".equals(JmqiEnvironment.getBitmode())) {
/*   281 */                       WindowsAdapter64 windowsAdapter64 = new WindowsAdapter64(LocalMQ.this.env, parent);
/*       */                       break;
/*       */                     } 
/*   284 */                     windowsAdapter = new WindowsAdapter(LocalMQ.this.env, parent);
/*       */                     break;
/*       */                   
/*       */                   default:
/*   288 */                     defaultAdapter = new DefaultAdapter(LocalMQ.this.env, parent);
/*       */                     break;
/*       */                 } 
/*       */                 
/*   292 */                 LocalMQ.cacheAdapter((Adapter)defaultAdapter);
/*   293 */                 if (Trace.isOn) {
/*   294 */                   StringBuffer buffer = new StringBuffer();
/*   295 */                   buffer.append("libraryName='");
/*   296 */                   buffer.append(defaultAdapter.getLibraryName());
/*   297 */                   buffer.append("'");
/*   298 */                   Trace.data(this, "initialise_inner()", buffer.toString(), null);
/*       */                 } 
/*       */               } 
/*   301 */               libraryName = LocalMQ.getCachedAdapter().getLibraryName();
/*   302 */               LocalMQ.this.loadLib(libraryName);
/*       */             }
/*   304 */             catch (JmqiException jmqie) {
/*   305 */               if (Trace.isOn) {
/*   306 */                 Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)jmqie);
/*       */               }
/*       */               
/*   309 */               jTls.lastException = jmqie;
/*   310 */               returnValue = jmqie;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */               
/*   317 */               if (!(jmqie.getCause() instanceof UnsatisfiedLinkError)) {
/*       */ 
/*       */ 
/*       */                 
/*   321 */                 HashMap<String, Object> ffstInfo = new HashMap<>();
/*   322 */                 ffstInfo.put("exception", jmqie);
/*   323 */                 ffstInfo.put("Exception summary", JmqiTools.getExSumm((Throwable)jmqie));
/*   324 */                 ffstInfo.put("Description", "Unexpected Exception");
/*   325 */                 Trace.ffst(this, "run()", "01", ffstInfo, jmqie.getClass());
/*       */               } 
/*       */             } 
/*       */             
/*   329 */             if (Trace.isOn) {
/*   330 */               Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()", returnValue);
/*       */             }
/*   332 */             return returnValue;
/*       */           }
/*       */         });
/*       */ 
/*       */ 
/*       */     
/*   338 */     if (object != null && 
/*   339 */       object instanceof JmqiException) {
/*   340 */       JmqiException e = (JmqiException)object;
/*   341 */       if (Trace.isOn) {
/*   342 */         Trace.throwing(this, "com.ibm.mq.jmqi.local.LocalMQ", "initialise_inner()", (Throwable)e);
/*       */       }
/*   344 */       throw e;
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*   351 */     this.nativeTraceHandler = new NativeTraceHandler(this.env);
/*       */     
/*   353 */     int limit = 100;
/*   354 */     Pint[] array = new Pint[limit];
/*   355 */     for (int i = 0; i < limit; i++) {
/*   356 */       array[i] = this.env.newPint(-1);
/*       */     }
/*       */ 
/*       */ 
/*       */     
/*   361 */     (array[0]).x = 1;
/*       */     
/*   363 */     boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*   364 */     if (!Native.init_conversion(isClassTraced, this.nativeTraceHandler, array)) {
/*   365 */       Trace.data(this, "initialise_inner()", "Failed to setup conversion");
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*   373 */     ptrSize = (array[0]).x;
/*   374 */     cmdLevel = (array[1]).x;
/*   375 */     int count = (array[2]).x;
/*   376 */     int nativeSupportFlags = (array[3]).x;
/*       */     
/*   378 */     if (Trace.isOn) {
/*   379 */       StringBuffer sb = new StringBuffer();
/*   380 */       sb.append("[0] ptrSize: ");
/*   381 */       sb.append(ptrSize);
/*   382 */       sb.append(" [1] cmdLevel: ");
/*   383 */       sb.append(cmdLevel);
/*   384 */       sb.append(" [2] count: ");
/*   385 */       sb.append(count);
/*   386 */       sb.append(" [3] nativeSupportFlags: ");
/*   387 */       sb.append(nativeSupportFlags);
/*   388 */       Trace.data(this, "initialise_inner()", sb.toString());
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*   405 */     ArrayList<String> names = new ArrayList<>();
/*       */ 
/*       */     
/*   408 */     HashMap<MqStructureKey, MqStructure> map = new HashMap<>();
/*       */ 
/*       */     
/*   411 */     addItem(names, map, 0, "MQAIR", 1, MQAIR.getSizeV1(ptrSize));
/*   412 */     addItem(names, map, 0, "MQAIR", 2, MQAIR.getSizeV2(ptrSize));
/*   413 */     addItem(names, map, 1, "MQBO", 1, MQBO.getSizeV1(ptrSize));
/*   414 */     addItem(names, map, 2, "MQCNO", 5, MQCNO.getSizeV5(ptrSize));
/*   415 */     addItem(names, map, 2, "MQCNO", 6, MQCNO.getSizeV6(ptrSize));
/*   416 */     addItem(names, map, 2, "MQCNO", 7, MQCNO.getSizeV7(ptrSize));
/*   417 */     addItem(names, map, 2, "MQCNO", 8, MQCNO.getSizeV8(ptrSize));
/*   418 */     addItem(names, map, 3, "MQGMO", 3, MQGMO.getSizeV3(ptrSize));
/*   419 */     addItem(names, map, 3, "MQGMO", 4, MQGMO.getSizeV4(ptrSize));
/*   420 */     addItem(names, map, 4, "MQMD", 1, MQMD.getSizeV1(ptrSize));
/*   421 */     addItem(names, map, 4, "MQMD", 2, MQMD.getSizeV2(ptrSize));
/*   422 */     addItem(names, map, 5, "MQMDE", 2, MQMDE.getSizeV2(this.env, ptrSize));
/*   423 */     addItem(names, map, 6, "MQOD", 3, MQOD.getSizeV3(ptrSize));
/*   424 */     addItem(names, map, 6, "MQOD", 4, MQOD.getSizeV4(ptrSize));
/*   425 */     addItem(names, map, 7, "MQOR", 0, MQOR.getSize(this.env, ptrSize));
/*   426 */     addItem(names, map, 8, "MQPMO", 1, MQPMO.getSizeV1(ptrSize));
/*   427 */     addItem(names, map, 8, "MQPMO", 2, MQPMO.getSizeV2(ptrSize));
/*   428 */     addItem(names, map, 8, "MQPMO", 3, MQPMO.getSizeV3(ptrSize));
/*   429 */     addItem(names, map, 9, "MQRR", 0, MQRR.getSize(this.env, ptrSize));
/*   430 */     addItem(names, map, 10, "MQSCO", 2, MQSCO.getSizeV2(this.env, ptrSize));
/*   431 */     addItem(names, map, 10, "MQSCO", 3, MQSCO.getSizeV3(this.env, ptrSize));
/*   432 */     addItem(names, map, 10, "MQSCO", 4, MQSCO.getSizeV4(this.env, ptrSize));
/*   433 */     addItem(names, map, 10, "MQSCO", 5, MQSCO.getSizeV5(this.env, ptrSize));
/*   434 */     addItem(names, map, 10, "MQSCO", 6, MQSCO.getSizeV6(this.env, ptrSize));
/*   435 */     addItem(names, map, 11, "MQRFH", 2, MQRFH.getSizeV2(this.env, ptrSize));
/*   436 */     addItem(names, map, 12, "MQDLH", 1, MQDLH.getSizeV1(ptrSize));
/*   437 */     addItem(names, map, 13, "MQCD", 8, MQCD.getSizeV8(ptrSize));
/*   438 */     addItem(names, map, 13, "MQCD", 9, MQCD.getSizeV9(ptrSize));
/*   439 */     addItem(names, map, 13, "MQCD", 10, MQCD.getSizeV10(ptrSize));
/*   440 */     addItem(names, map, 13, "MQCD", 11, MQCD.getSizeV11(ptrSize));
/*   441 */     addItem(names, map, 13, "MQCD", 12, MQCD.getSizeV12(ptrSize));
/*   442 */     addItem(names, map, 14, "MQCSP", 1, MQCSP.getSizeV1(ptrSize));
/*   443 */     addItem(names, map, 14, "MQCSP", 2, MQCSP.getSizeV2(ptrSize));
/*   444 */     addItem(names, map, 15, "MQCXP", 6, MQCXP.getSizeV6(this.env, ptrSize));
/*   445 */     addItem(names, map, 15, "MQCXP", 7, MQCXP.getSizeV7(this.env, ptrSize));
/*   446 */     addItem(names, map, 15, "MQCXP", 8, MQCXP.getSizeV8(this.env, ptrSize));
/*   447 */     addItem(names, map, 15, "MQCXP", 9, MQCXP.getSizeV9(this.env, ptrSize));
/*   448 */     addItem(names, map, 16, "SpiActivate", 1, SpiActivate.getSizeV1(ptrSize));
/*   449 */     addItem(names, map, 17, "LpiPrivConnStruct", 2, LpiPrivConnStruct.getSizeV2(this.env, ptrSize));
/*   450 */     addItem(names, map, 17, "LpiPrivConnStruct", 3, LpiPrivConnStruct.getSizeV3(this.env, ptrSize));
/*   451 */     addItem(names, map, 17, "LpiPrivConnStruct", 4, LpiPrivConnStruct.getSizeV4(this.env, ptrSize));
/*   452 */     addItem(names, map, 18, "SpiGetOptions.v", 2, SpiGetOptions.getSizeV2(ptrSize, true));
/*   453 */     addItem(names, map, 18, "SpiGetOptions.v", 3, SpiGetOptions.getSizeV3(ptrSize, true));
/*   454 */     addItem(names, map, 18, "SpiGetOptions.v", 4, SpiGetOptions.getSizeV4(ptrSize, true));
/*   455 */     addItem(names, map, 18, "SpiGetOptions.v", 5, SpiGetOptions.getSizeV5(ptrSize, true));
/*   456 */     addItem(names, map, 19, "SpiGetOptions.p", 2, SpiGetOptions.getSizeV2(ptrSize, false));
/*   457 */     addItem(names, map, 19, "SpiGetOptions.p", 3, SpiGetOptions.getSizeV3(ptrSize, false));
/*   458 */     addItem(names, map, 19, "SpiGetOptions.p", 5, SpiGetOptions.getSizeV5(ptrSize, false));
/*   459 */     addItem(names, map, 20, "SpiPutOptions", 1, SpiPutOptions.getSizeV1(ptrSize));
/*   460 */     addItem(names, map, 20, "SpiPutOptions", 3, SpiPutOptions.getSizeV3(ptrSize));
/*   461 */     addItem(names, map, 20, "SpiPutOptions", 4, SpiPutOptions.getSizeV4(ptrSize));
/*       */ 
/*       */     
/*   464 */     addItem(names, map, 21, "MQCBC", 1, MQCBC.getSizeV1(ptrSize));
/*   465 */     addItem(names, map, 21, "MQCBC", 2, MQCBC.getSizeV2(ptrSize));
/*   466 */     addItem(names, map, 22, "MQCBD", 1, MQCBD.getSizeV1(ptrSize));
/*   467 */     addItem(names, map, 23, "MQCTLO", 1, MQCTLO.getSizeV1(ptrSize));
/*   468 */     addItem(names, map, 24, "MQSD", 1, MQSD.getSizeV1(ptrSize));
/*   469 */     addItem(names, map, 25, "MQSTS", 1, MQSTS.getSizeV1(ptrSize));
/*   470 */     addItem(names, map, 25, "MQSTS", 2, MQSTS.getSizeV2(ptrSize));
/*   471 */     addItem(names, map, 26, "LpiSDSubProps", 0, LpiSDSubProps.getSizeV1(ptrSize));
/*   472 */     addItem(names, map, 27, "LpiSD", 1, LpiSD.getSizeV1(ptrSize));
/*   473 */     addItem(names, map, 27, "LpiSD", 2, LpiSD.getSizeV2(ptrSize));
/*   474 */     addItem(names, map, 28, "MQSRO", 1, MQSRO.getSizeV1(ptrSize));
/*   475 */     addItem(names, map, 29, "SpiOpenOptions.p", 1, SpiOpenOptions.getSizeV1(ptrSize));
/*   476 */     addItem(names, map, 29, "SpiOpenOptions.p", 2, SpiOpenOptions.getSizeV2(ptrSize, false));
/*   477 */     addItem(names, map, 29, "SpiOpenOptions.p", 3, SpiOpenOptions.getSizeV3(ptrSize, false));
/*   478 */     addItem(names, map, 29, "SpiOpenOptions.p", 4, SpiOpenOptions.getSizeV4(ptrSize, false));
/*   479 */     addItem(names, map, 30, "SpiSyncPointOptions", 1, SpiSyncPointOptions.getSizeV1(ptrSize));
/*   480 */     addItem(names, map, 31, "LpiUSD", 1, LpiUSD.getSizeV1(ptrSize));
/*   481 */     addItem(names, map, 32, "LpiNotifyDetails", 1, LpiNotifyDetails.getSizeV1(ptrSize));
/*   482 */     addItem(names, map, 33, "RXPB", 2, RXPB.getSizeV2(ptrSize));
/*   483 */     addItem(names, map, 33, "RXPB", 3, RXPB.getSizeV3(ptrSize));
/*       */ 
/*       */     
/*   486 */     addItem(names, map, 34, "LexFilterElement", 1, LexFilterElement.getSizeV1(ptrSize));
/*   487 */     addItem(names, map, 35, "LexObjectSelector", 1, LexObjectSelector.getSizeV1(ptrSize));
/*   488 */     addItem(names, map, 35, "LexObjectSelector", 2, LexObjectSelector.getSizeV2(ptrSize));
/*       */ 
/*       */     
/*   491 */     addItem(names, map, 36, "SpiOpenOptions.v", 1, SpiOpenOptions.getSizeV1(ptrSize));
/*   492 */     addItem(names, map, 36, "SpiOpenOptions.v", 2, SpiOpenOptions.getSizeV2(ptrSize, true));
/*   493 */     addItem(names, map, 36, "SpiOpenOptions.v", 3, SpiOpenOptions.getSizeV3(ptrSize, true));
/*   494 */     addItem(names, map, 36, "SpiOpenOptions.v", 4, SpiOpenOptions.getSizeV4(ptrSize, true));
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*   503 */     if (!tls.alreadyCheckSize) {
/*   504 */       tls.alreadyCheckSize = true;
/*       */ 
/*       */       
/*   507 */       for (int index = 0; index < count; index++) {
/*   508 */         checkSize(names, map, array, index, cmdLevel);
/*       */       }
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*   517 */     if (Trace.isOn) {
/*   518 */       Pint cc = this.env.newPint(0);
/*   519 */       Pint rc = this.env.newPint(0);
/*   520 */       JmqiMetaData metadata = this.sysenv.newJmqiMetaData();
/*       */       
/*   522 */       getMetaData(metadata, cc, rc);
/*   523 */       String cmvcLevel = metadata.getCmvcLevel();
/*   524 */       int installFlags = metadata.getInstallFlags();
/*   525 */       String adapterName = null;
/*       */       
/*       */       try {
/*   528 */         Adapter adapter = getAdapter();
/*   529 */         adapterName = adapter.getLibraryName();
/*       */       }
/*   531 */       catch (Exception e) {
/*   532 */         if (Trace.isOn) {
/*   533 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "initialise_inner()", e, 1);
/*       */         }
/*   535 */         adapterName = "unknown";
/*       */       } 
/*       */       
/*   538 */       StringBuffer sb = new StringBuffer();
/*   539 */       sb.append("adapter: ");
/*   540 */       sb.append(adapterName);
/*   541 */       sb.append(", CmvcLevel: ");
/*   542 */       sb.append(cmvcLevel);
/*   543 */       sb.append(", InstallFlags: ");
/*   544 */       sb.append(installFlags);
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*   549 */       Trace.data(this, "initialise_inner()", sb.toString());
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*   556 */       String string = JmqiTools.getSystemProperty("com.ibm.mq.jmqi.ffstOnError");
/*   557 */       if (string != null && (
/*   558 */         "on".equalsIgnoreCase(string) || "yes".equalsIgnoreCase(string) || "true".equalsIgnoreCase(string))) {
/*   559 */         this.ffstOnError = true;
/*       */       
/*       */       }
/*       */     }
/*   563 */     catch (AccessControlException ace) {
/*   564 */       if (Trace.isOn) {
/*   565 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "initialise_inner()", ace, 2);
/*       */       }
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*   574 */     if (Trace.isOn) {
/*   575 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "initialise_inner()");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   private void addItem(ArrayList<String> names, HashMap<MqStructureKey, MqStructure> map, int index, String name, int version, int size) {
/*   590 */     MqStructureKey key = new MqStructureKey(this.env, index, version);
/*   591 */     MqStructure value = new MqStructure(this.env, index, name, version, size);
/*   592 */     names.add(index, name);
/*   593 */     map.put(key, value);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   private void checkSize(ArrayList<String> names, HashMap<MqStructureKey, MqStructure> map, Pint[] array, int index, int cmdLevel) {
/*   624 */     String fid = "checkSize";
/*       */ 
/*       */     
/*   627 */     int i = 4 + 2 * index;
/*   628 */     int version = (array[i]).x;
/*   629 */     int nativeSize = (array[i + 1]).x;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*   636 */     if (version >= 0) {
/*   637 */       MqStructureKey key = new MqStructureKey(this.env, index, version);
/*   638 */       MqStructure structure = map.get(key);
/*       */       
/*   640 */       String name = "index:" + index + "   " + (String)names.get(index) + " version:" + version;
/*       */       
/*   642 */       if (structure == null) {
/*   643 */         if (cmdLevel <= 932) {
/*   644 */           HashMap<String, Object> ffstInfo = new HashMap<>();
/*   645 */           ffstInfo.put("name", name);
/*   646 */           ffstInfo.put("nativeSize", Integer.valueOf(nativeSize));
/*   647 */           ffstInfo.put("Description", "MQ Structure size check, missing item");
/*   648 */           ffstInfo.put("CmdLevel", Integer.valueOf(cmdLevel));
/*   649 */           Trace.ffst(this, "checkSize", "01", ffstInfo, null);
/*       */         } 
/*       */       } else {
/*       */         
/*   653 */         int javaSize = structure.getSize();
/*   654 */         if (nativeSize != javaSize) {
/*   655 */           HashMap<String, Object> ffstInfo = new HashMap<>();
/*   656 */           ffstInfo.put("name", name);
/*   657 */           ffstInfo.put("nativeSize", Integer.valueOf(nativeSize));
/*   658 */           ffstInfo.put("javaSize", Integer.valueOf(javaSize));
/*   659 */           ffstInfo.put("Description", "MQ Structure size check,size mismatch");
/*   660 */           Trace.ffst(this, "checkSize", "01", ffstInfo, null);
/*       */         } 
/*       */       } 
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public JmqiComponentTls newTlsObject() {
/*   686 */     if (Trace.isOn) {
/*   687 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "newTlsObject()");
/*       */     }
/*   689 */     JmqiComponentTls traceRet1 = new LocalTls();
/*       */     
/*   691 */     if (Trace.isOn) {
/*   692 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "newTlsObject()", traceRet1);
/*       */     }
/*   694 */     return traceRet1;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public Adapter getAdapter() throws JmqiException {
/*   791 */     if (Trace.isOn) {
/*   792 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalMQ", "getAdapter()", "getter", getCachedAdapter());
/*       */     }
/*   794 */     return getCachedAdapter();
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   private enum ZosAdapterType
/*       */   {
/*   801 */     BATCH, CICS, RRS, INTERNALRRS, EXTERNALRRS, WMB, IMS, WAS, UNKNOWN;
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   private enum ZosEnvironmentType
/*       */   {
/*   808 */     IMS, WEBSPHERE, CICS, BATCH;
/*       */     
/*       */     private boolean isBatchOrWas() {
/*   811 */       return (this == BATCH || this == WEBSPHERE);
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   private ZosEnvironmentType zosEnvironment() throws JmqiException {
/*       */     ZosEnvironmentType environment;
/*   823 */     if (this.env.isRunningUnderIMS()) {
/*   824 */       environment = ZosEnvironmentType.IMS;
/*       */     
/*       */     }
/*       */     else {
/*       */ 
/*       */       
/*   830 */       String wasdet = this.env.getConfiguration().getStringValue(Configuration.webSphereServerTypeProperty);
/*   831 */       if (wasdet != null) {
/*   832 */         Trace.data(this, "getZosEnvType()", "WebSphere detected - com.ibm.websphere.ServerType is '" + wasdet + "'");
/*   833 */         environment = ZosEnvironmentType.WEBSPHERE;
/*       */       }
/*   835 */       else if (this.env.isRunningUnderCICS()) {
/*   836 */         environment = ZosEnvironmentType.CICS;
/*       */       } else {
/*       */         
/*   839 */         Trace.data(this, "getZosEnvType()", "None of IMS, WebSphere or CICS detected");
/*   840 */         environment = ZosEnvironmentType.BATCH;
/*       */       } 
/*       */     } 
/*   843 */     if (Trace.isOn) {
/*   844 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalMQ", "zosEnvironment()", "getter", environment);
/*       */     }
/*   846 */     return environment;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   private ZosAdapterType findZosAdapterType(ZosEnvironmentType environment) throws JmqiException {
/*       */     ZosAdapterType adapterType;
/*   855 */     Configuration config = this.env.getConfiguration();
/*   856 */     String requestedAdapter = config.getStringValue(Configuration.requestedAdapterProperty);
/*   857 */     boolean is64bit = "64".equals(JmqiEnvironment.getBitmode());
/*       */ 
/*       */     
/*   860 */     if (Trace.isOn) {
/*   861 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "findZosAdapterType(ZosEnvironment)", new Object[] { environment });
/*       */     }
/*       */     
/*   864 */     if (requestedAdapter != null) {
/*   865 */       requestedAdapter = requestedAdapter.trim().toLowerCase();
/*   866 */       if (is64bit) {
/*       */         
/*   868 */         if (requestedAdapter.equals("cics") && environment == ZosEnvironmentType.WEBSPHERE) {
/*       */           
/*   870 */           HashMap<String, Object> info = new HashMap<>();
/*   871 */           info.put("Error text", "Adapter combination disallowed");
/*   872 */           info.put("com.ibm.websphere.ServerType value", config.getStringValue(Configuration.webSphereServerTypeProperty));
/*   873 */           info.put("com.ibm.mq.adapter value", requestedAdapter);
/*   874 */           Trace.ffst(this, "findZosAdapterType(ZosEnvironmentType)", "1", info, null);
/*       */         } 
/*       */         
/*   877 */         if (!requestedAdapter.equals("wmb")) {
/*   878 */           if (Trace.isOn) {
/*   879 */             Trace.data(this, "findZosAdapterType(ZosEnvironmentType)", "Overriding requested adapter '" + requestedAdapter + "' to null in 64-bit environment.");
/*       */           }
/*       */           
/*   882 */           requestedAdapter = null;
/*       */         } 
/*       */       } 
/*       */     } 
/*   886 */     if (requestedAdapter != null && Trace.isOn) {
/*   887 */       Trace.data(this, "findZosAdapterType(ZosEnvironmentType)", "com.ibm.mq.adapter is '" + requestedAdapter + "'");
/*       */     }
/*       */     
/*   890 */     if (requestedAdapter == null) {
/*   891 */       switch (environment) {
/*       */         case BATCH:
/*   893 */           adapterType = ZosAdapterType.CICS;
/*       */           break;
/*       */         case WAS:
/*   896 */           adapterType = ZosAdapterType.IMS;
/*       */           break;
/*       */         case CICS:
/*   899 */           adapterType = ZosAdapterType.WAS;
/*       */           break;
/*       */         case IMS:
/*   902 */           adapterType = ZosAdapterType.BATCH;
/*       */           break;
/*       */         default:
/*   905 */           adapterType = ZosAdapterType.UNKNOWN;
/*       */           break;
/*       */       } 
/*       */ 
/*       */     
/*   910 */     } else if (requestedAdapter.equals("batch") && environment.isBatchOrWas()) {
/*   911 */       adapterType = ZosAdapterType.BATCH;
/*       */     }
/*   913 */     else if (requestedAdapter.equals("rrs") && environment.isBatchOrWas()) {
/*   914 */       adapterType = ZosAdapterType.WAS;
/*       */     }
/*   916 */     else if (requestedAdapter.equals("internalrrs") && environment.isBatchOrWas()) {
/*   917 */       adapterType = ZosAdapterType.INTERNALRRS;
/*       */     }
/*   919 */     else if (requestedAdapter.equals("externalrrs") && environment.isBatchOrWas()) {
/*   920 */       adapterType = ZosAdapterType.EXTERNALRRS;
/*       */     }
/*   922 */     else if (requestedAdapter.equals("wmb") && environment.isBatchOrWas()) {
/*   923 */       adapterType = ZosAdapterType.WMB;
/*       */     }
/*   925 */     else if (requestedAdapter.equals("cics") && environment == ZosEnvironmentType.CICS) {
/*   926 */       adapterType = ZosAdapterType.CICS;
/*       */     }
/*   928 */     else if (requestedAdapter.equals("ims") && environment == ZosEnvironmentType.IMS) {
/*   929 */       adapterType = ZosAdapterType.IMS;
/*       */     } else {
/*       */       
/*   932 */       if (is64bit) {
/*       */ 
/*       */         
/*   935 */         JmqiException jmqiException = new JmqiException(this.env, 6271, new String[] { requestedAdapter, environment.toString(), null, null, null }, 2, 2012, null);
/*       */ 
/*       */         
/*   938 */         if (Trace.isOn) {
/*   939 */           Trace.throwing(this, "com.ibm.mq.jmqi.local.LocalMQ", "findZosAdapterType(ZosEnvironmentType)", (Throwable)jmqiException, 1);
/*       */         }
/*   941 */         throw jmqiException;
/*       */       } 
/*       */ 
/*       */       
/*   945 */       Error e = new Error("com.ibm.mq.adapter set to '" + requestedAdapter + "', which is invalid.");
/*   946 */       if (Trace.isOn) {
/*   947 */         Trace.throwing(this, "com.ibm.mq.jmqi.local.LocalMQ", "findZosAdapterType(ZosEnvironmentType)", e, 2);
/*       */       }
/*   949 */       throw e;
/*       */     } 
/*       */ 
/*       */     
/*   953 */     if (Trace.isOn) {
/*   954 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "findZosAdapterType(ZosEnvironment)", adapterType);
/*       */     }
/*       */     
/*   957 */     return adapterType;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   protected Adapter get390Adapter() throws JmqiException {
/*       */     Adapter adapter;
/*   969 */     ZosEnvironmentType environment = zosEnvironment();
/*       */     
/*   971 */     ZosAdapterType adapterType = findZosAdapterType(environment);
/*   972 */     boolean is64bit = "64".equals(JmqiEnvironment.getBitmode());
/*       */ 
/*       */ 
/*       */     
/*   976 */     if (is64bit) {
/*   977 */       BatchAdapter64 batchAdapter64; WASAdapter64 wASAdapter64; CICSAdapter64 cICSAdapter64; IMSAdapter64 iMSAdapter64; InternalRRSAdapter64 internalRRSAdapter64; ExternalRRSAdapter64 externalRRSAdapter64; WMBAdapter64 wMBAdapter64; switch (adapterType) {
/*       */         case BATCH:
/*   979 */           batchAdapter64 = new BatchAdapter64(this.env, this);
/*       */           break;
/*       */         case WAS:
/*   982 */           wASAdapter64 = new WASAdapter64(this.env, this);
/*       */           break;
/*       */         case CICS:
/*   985 */           cICSAdapter64 = new CICSAdapter64(this.env, this);
/*       */           break;
/*       */         case IMS:
/*   988 */           iMSAdapter64 = new IMSAdapter64(this.env, this);
/*       */           break;
/*       */ 
/*       */         
/*       */         case INTERNALRRS:
/*   993 */           internalRRSAdapter64 = new InternalRRSAdapter64(this.env, this);
/*       */           break;
/*       */         case EXTERNALRRS:
/*   996 */           externalRRSAdapter64 = new ExternalRRSAdapter64(this.env, this);
/*       */           break;
/*       */         case WMB:
/*   999 */           wMBAdapter64 = new WMBAdapter64(this.env, this);
/*       */           break;
/*       */         default:
/*  1002 */           adapter = null; break;
/*       */       } 
/*       */     } else {
/*       */       BatchAdapter32 batchAdapter32; WASAdapter32 wASAdapter32; CICSAdapter32 cICSAdapter32; IMSAdapter31 iMSAdapter31; InternalRRSAdapter32 internalRRSAdapter32; ExternalRRSAdapter32 externalRRSAdapter32;
/*       */       WMBAdapter32 wMBAdapter32;
/*  1007 */       switch (adapterType) {
/*       */         case BATCH:
/*  1009 */           batchAdapter32 = new BatchAdapter32(this.env, this);
/*       */           break;
/*       */         case WAS:
/*  1012 */           wASAdapter32 = new WASAdapter32(this.env, this);
/*       */           break;
/*       */         case CICS:
/*  1015 */           cICSAdapter32 = new CICSAdapter32(this.env, this);
/*       */           break;
/*       */         case IMS:
/*  1018 */           iMSAdapter31 = new IMSAdapter31(this.env, this);
/*       */           break;
/*       */         case INTERNALRRS:
/*  1021 */           internalRRSAdapter32 = new InternalRRSAdapter32(this.env, this);
/*       */           break;
/*       */         case EXTERNALRRS:
/*  1024 */           externalRRSAdapter32 = new ExternalRRSAdapter32(this.env, this);
/*       */           break;
/*       */         case WMB:
/*  1027 */           wMBAdapter32 = new WMBAdapter32(this.env, this);
/*       */           break;
/*       */         default:
/*  1030 */           adapter = null;
/*       */           break;
/*       */       } 
/*       */ 
/*       */ 
/*       */     
/*       */     } 
/*  1037 */     if (adapter == null || adapter.getLibraryName() == null) {
/*  1038 */       Error e = new Error("Invalid or missing adapter " + adapterType.toString() + "for environment " + environment.toString() + ", amode " + (is64bit ? "64" : "31"));
/*       */       
/*  1040 */       if (Trace.isOn) {
/*  1041 */         Trace.throwing(this, "com.ibm.mq.jmqi.local.LocalMQ", "get390Adapter()", e, 1);
/*       */       }
/*  1043 */       throw e;
/*       */     } 
/*       */     
/*  1046 */     if (Trace.isOn) {
/*  1047 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalMQ", "get390Adapter()", "getter", adapter);
/*       */     }
/*  1049 */     return adapter;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void loadLib(String libraryName) throws JmqiException {
/*  1062 */     if (Trace.isOn) {
/*  1063 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "loadLib(String)", new Object[] { libraryName });
/*       */     }
/*       */     
/*  1066 */     String fid = "loadLib(String)";
/*       */     try {
/*  1068 */       if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ISERIES) {
/*  1069 */         System.load(libraryName);
/*       */       }
/*       */       else {
/*       */         
/*  1073 */         Configuration config = this.env.getConfiguration();
/*  1074 */         String libPath = config.getStringValue(Configuration.JMQI_LIB_PATH);
/*  1075 */         if (libPath != null) {
/*  1076 */           if (Trace.isOn) {
/*  1077 */             Trace.data(this, "loadLib(String)", "jmqi.libpath property found in environment: \"" + libPath + "\"");
/*       */           }
/*       */ 
/*       */           
/*  1081 */           String libPathPrefix = libPath + File.separator;
/*  1082 */           String absolute_lib_path = null;
/*       */           
/*  1084 */           boolean libraryFound = false;
/*       */           
/*  1086 */           for (int lookupAttempt = 0; !libraryFound && lookupAttempt < 3; lookupAttempt++) {
/*  1087 */             String candidateLibraryName = null;
/*  1088 */             switch (lookupAttempt) {
/*       */               case 0:
/*  1090 */                 candidateLibraryName = libraryName;
/*       */                 break;
/*       */               
/*       */               case 1:
/*  1094 */                 candidateLibraryName = System.mapLibraryName(libraryName);
/*       */                 break;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */               
/*       */               case 2:
/*  1103 */                 candidateLibraryName = System.mapLibraryName(libraryName);
/*  1104 */                 if (candidateLibraryName.endsWith(".a")) {
/*  1105 */                   candidateLibraryName = candidateLibraryName.substring(0, candidateLibraryName.length() - 2) + ".so";
/*       */                 }
/*       */                 break;
/*       */             } 
/*       */ 
/*       */ 
/*       */ 
/*       */             
/*  1113 */             absolute_lib_path = libPathPrefix + candidateLibraryName;
/*  1114 */             File fLibrary = new File(absolute_lib_path);
/*  1115 */             libraryFound = fLibrary.exists();
/*  1116 */             if (Trace.isOn) {
/*  1117 */               Trace.data(this, "loadLib(String)", "Attempt " + lookupAttempt + " candidate file " + absolute_lib_path + (libraryFound ? " exists" : " does not exist"));
/*       */             }
/*       */           } 
/*  1120 */           if (Trace.isOn) {
/*  1121 */             Trace.data(this, "loadLib(String)", "Loading JMQI library: " + absolute_lib_path);
/*       */           }
/*  1123 */           System.load(absolute_lib_path);
/*       */ 
/*       */         
/*       */         }
/*       */         else {
/*       */ 
/*       */ 
/*       */           
/*  1131 */           if (Trace.isOn) {
/*  1132 */             Trace.data(this, "loadLib(String)", "Native library to load: " + libraryName);
/*  1133 */             Trace.data(this, "loadLib(String)", "Current Java Library Path = " + System.getProperty("java.library.path"));
/*       */           } 
/*  1135 */           System.loadLibrary(libraryName);
/*       */         }
/*       */       
/*       */       } 
/*  1139 */     } catch (UnsatisfiedLinkError ule) {
/*  1140 */       if (Trace.isOn) {
/*  1141 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "loadLib(String)", ule, 1);
/*       */       }
/*       */ 
/*       */ 
/*       */       
/*  1146 */       String jvmReportedFailureMsg = ule.getMessage();
/*  1147 */       String mappedLibraryName = System.mapLibraryName(libraryName);
/*       */       
/*  1149 */       String jvmBitSize = JmqiEnvironment.getBitmode();
/*       */ 
/*       */ 
/*       */       
/*  1153 */       String pathSeperator = System.getProperty("path.separator");
/*  1154 */       String javaLibraryPath = System.getProperty("java.library.path");
/*       */       
/*  1156 */       String formattedJavaLibraryPath = "*** Configured java.library.path **********************************\n|  \"" + javaLibraryPath.replaceAll(pathSeperator, "\"\n|  \"") + "\"\n| ********************************************************************";
/*       */ 
/*       */       
/*  1159 */       if (libraryName.equals("mqjbnd")) {
/*  1160 */         String[] arrayOfString = { jvmReportedFailureMsg, mappedLibraryName, libraryName, jvmBitSize, formattedJavaLibraryPath };
/*       */         
/*  1162 */         JmqiException jmqiException = new JmqiException(this.env, 8598, arrayOfString, 2, 2495, ule)
/*       */           {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */             
/*       */             public String getMessage()
/*       */             {
/*  1173 */               if (Trace.isOn) {
/*  1174 */                 Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "getMessage()");
/*       */               }
/*  1176 */               String traceRet1 = getMessage(false);
/*  1177 */               if (Trace.isOn) {
/*  1178 */                 Trace.exit(this, "com.ibm.mq.jmqi.local.null", "getMessage()", traceRet1);
/*       */               }
/*  1180 */               return traceRet1;
/*       */             }
/*       */           };
/*  1183 */         if (Trace.isOn) {
/*  1184 */           Trace.throwing(this, "com.ibm.mq.jmqi.local.LocalMQ", "loadLib(String)", (Throwable)jmqiException, 1);
/*       */         }
/*  1186 */         throw jmqiException;
/*       */       } 
/*       */       
/*  1189 */       String[] inserts = { null, null, libraryName };
/*  1190 */       JmqiException jmqie = new JmqiException(this.env, 8568, inserts, 2, 2495, ule);
/*  1191 */       if (Trace.isOn) {
/*  1192 */         Trace.throwing(this, "com.ibm.mq.jmqi.local.LocalMQ", "loadLib(String)", ule, 1);
/*       */       }
/*  1194 */       if (Trace.isOn) {
/*  1195 */         Trace.throwing(this, "com.ibm.mq.jmqi.local.LocalMQ", "loadLib(String)", (Throwable)jmqie, 2);
/*       */       }
/*  1197 */       throw jmqie;
/*       */     }
/*  1199 */     catch (Error e) {
/*  1200 */       if (Trace.isOn) {
/*  1201 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "loadLib(String)", e, 2);
/*       */       }
/*  1203 */       if (Trace.isOn) {
/*  1204 */         Trace.throwing(this, "com.ibm.mq.jmqi.local.LocalMQ", "loadLib(String)", e, 3);
/*       */       }
/*  1206 */       throw e;
/*       */     } finally {
/*       */       
/*  1209 */       if (Trace.isOn) {
/*  1210 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "loadLib(String)");
/*       */       }
/*       */     } 
/*  1213 */     if (Trace.isOn) {
/*  1214 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "loadLib(String)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public LocalMQ(JmqiEnvironment env, int options) throws JmqiException {
/*  1229 */     super(env);
/*  1230 */     if (Trace.isOn) {
/*  1231 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "<init>(JmqiEnvironment,int)", new Object[] { env, 
/*  1232 */             Integer.valueOf(options) });
/*       */     }
/*  1234 */     String fid = "<init>(JmqiEnvironment,int)";
/*  1235 */     Trace.registerDumpableComponent(this);
/*  1236 */     this.mqiOptions = options;
/*       */     
/*  1238 */     if (env instanceof JmqiSystemEnvironment)
/*       */     {
/*  1240 */       this.sysenv = (JmqiSystemEnvironment)env;
/*       */     }
/*  1242 */     this.jmqiCompId = this.sysenv.registerComponent(this);
/*       */     
/*  1244 */     initialise();
/*  1245 */     StringBuffer sb = new StringBuffer();
/*  1246 */     Adapter adapter = getAdapter();
/*  1247 */     if (!adapter.isSharedHandlesSupported()) {
/*  1248 */       this.useSharedHconn = false;
/*       */ 
/*       */ 
/*       */       
/*  1252 */       if (!adapter.isWorkerThreadSupported()) {
/*  1253 */         this.useWorkerThread = false;
/*       */       }
/*  1255 */       else if (adapter.isRRS()) {
/*  1256 */         this.useWorkerThread = false;
/*  1257 */         if ((this.mqiOptions & 0x20) != 0) {
/*  1258 */           this.useWorkerThreadForAsyncOnly = true;
/*       */         }
/*       */       } else {
/*       */         
/*  1262 */         this.useWorkerThread = true;
/*       */       } 
/*       */     } else {
/*       */       
/*  1266 */       this.useSharedHconn = true;
/*  1267 */       if ((this.mqiOptions & 0x1) != 0) {
/*  1268 */         this.useWorkerThread = true;
/*       */       } else {
/*       */         
/*  1271 */         this.useWorkerThread = false;
/*       */       } 
/*       */     } 
/*       */     
/*  1275 */     if ((this.mqiOptions & 0x4) != 0) {
/*  1276 */       Trace.data("<init>(JmqiEnvironment,int)", "The option FORCE_USE_WORKER_THREAD is set", null);
/*  1277 */       this.useWorkerThread = true;
/*       */     } 
/*  1279 */     if ((this.mqiOptions & 0x8) != 0) {
/*  1280 */       Trace.data("<init>(JmqiEnvironment,int)", "The option FORCE_DONT_USE_WORKER_THREAD is set", null);
/*  1281 */       this.useWorkerThread = false;
/*       */     } 
/*  1283 */     if ((this.mqiOptions & 0x10) != 0) {
/*  1284 */       Trace.data("<init>(JmqiEnvironment,int)", "The option FORCE_DONT_USE_SHARED_HCONN is set", null);
/*  1285 */       this.useSharedHconn = false;
/*       */     } 
/*       */     
/*  1288 */     sb.setLength(0);
/*  1289 */     sb.append("useWorkerThread = ");
/*  1290 */     sb.append(this.useWorkerThread);
/*  1291 */     sb.append(", useWorkerThreadForAsyncOnly = ");
/*  1292 */     sb.append(this.useWorkerThreadForAsyncOnly);
/*  1293 */     sb.append(", useSharedHconn = ");
/*  1294 */     sb.append(this.useSharedHconn);
/*  1295 */     Trace.data("<init>(JmqiEnvironment,int)", sb.toString(), null);
/*       */ 
/*       */     
/*  1298 */     if ((this.mqiOptions & 0x40) != 0 && JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES) {
/*  1299 */       Trace.data("<init>(JmqiEnvironment,int)", "The option INHERIT RRS CONTEXT is set", null);
/*  1300 */       this.inheritRRSContext = true;
/*       */     } 
/*       */     
/*  1303 */     this.adapterIsRRS = adapter.isRRS();
/*       */     
/*  1305 */     if (Trace.isOn) {
/*  1306 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "<init>(JmqiEnvironment,int)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public LocalHconn getLocalHconn(Hconn hconn) throws JmqiException {
/*  1317 */     if (Trace.isOn) {
/*  1318 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "getLocalHconn(Hconn)", new Object[] { hconn });
/*       */     }
/*       */     
/*  1321 */     LocalHconn traceRet1 = LocalHconn.getLocalHconn(this.env, this.useWorkerThread, hconn);
/*       */     
/*  1323 */     if (Trace.isOn) {
/*  1324 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "getLocalHconn(Hconn)", traceRet1);
/*       */     }
/*  1326 */     return traceRet1;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   private void castUnexpectedException(String fid, int probe, Throwable e) {
/*  1341 */     if (e instanceof ThreadDeath) {
/*  1342 */       throw (ThreadDeath)e;
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  1349 */     String exceptionSummary = null;
/*  1350 */     StackTraceElement[] st = e.getStackTrace();
/*  1351 */     if (st != null && st.length > 0) {
/*  1352 */       exceptionSummary = st[0].toString() + ": " + e.getMessage();
/*       */     } else {
/*       */       
/*  1355 */       exceptionSummary = e.getMessage();
/*       */     } 
/*  1357 */     Throwable cause = e.getCause();
/*  1358 */     String causeSummary = null;
/*  1359 */     if (cause != null) {
/*  1360 */       st = cause.getStackTrace();
/*  1361 */       if (st != null && st.length > 0) {
/*  1362 */         causeSummary = st[0].toString() + ": " + cause.getMessage();
/*       */       } else {
/*       */         
/*  1365 */         causeSummary = cause.getMessage();
/*       */       } 
/*       */     } 
/*       */ 
/*       */     
/*  1370 */     HashMap<String, Object> ffstInfo = new HashMap<>();
/*  1371 */     ffstInfo.put("Exception class", e.getClass().getName());
/*  1372 */     ffstInfo.put("Exception summary", exceptionSummary);
/*  1373 */     ffstInfo.put("Cause summary", causeSummary);
/*  1374 */     ffstInfo.put("Description", "No request with this id found");
/*  1375 */     Trace.ffst(this, fid, Integer.toString(probe), ffstInfo, null);
/*       */ 
/*       */     
/*  1378 */     if (e instanceof RuntimeException) {
/*  1379 */       throw (RuntimeException)e;
/*       */     }
/*  1381 */     if (e instanceof Error) {
/*  1382 */       throw (Error)e;
/*       */     }
/*       */     
/*  1385 */     throw new RuntimeException(e);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public JmqiEnvironment getEnv() {
/*  1393 */     if (Trace.isOn) {
/*  1394 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalMQ", "getEnv()", "getter", this.env);
/*       */     }
/*  1396 */     return this.env;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static int getPtrSize() {
/*  1403 */     if (Trace.isOn) {
/*  1404 */       Trace.data("com.ibm.mq.jmqi.local.LocalMQ", "getPtrSize()", "getter", 
/*  1405 */           Integer.valueOf(ptrSize));
/*       */     }
/*  1407 */     return ptrSize;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static boolean getSwap() {
/*  1414 */     if (Trace.isOn) {
/*  1415 */       Trace.data("com.ibm.mq.jmqi.local.LocalMQ", "getSwap()", "getter", Boolean.valueOf(swap));
/*       */     }
/*  1417 */     return swap;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public static JmqiCodepage getCp() {
/*  1424 */     if (Trace.isOn) {
/*  1425 */       Trace.data("com.ibm.mq.jmqi.local.LocalMQ", "getCp()", "getter", nativeCp);
/*       */     }
/*  1427 */     return nativeCp;
/*       */   }
/*       */   
/*       */   private static void cacheAdapter(Adapter adapter) {
/*  1431 */     cachedAdapter = adapter;
/*       */   }
/*       */   
/*       */   private static Adapter getCachedAdapter() {
/*  1435 */     return cachedAdapter;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int getJmqiCompId() {
/*  1442 */     if (Trace.isOn) {
/*  1443 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalMQ", "getJmqiCompId()", "getter", 
/*  1444 */           Integer.valueOf(this.jmqiCompId));
/*       */     }
/*  1446 */     return this.jmqiCompId;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQBACK(Hconn hconn, final Pint pCompCode, final Pint pReason) {
/*  1458 */     if (Trace.isOn) {
/*  1459 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQBACK(Hconn,final Pint,final Pint)", new Object[] { hconn, pCompCode, pReason });
/*       */     }
/*       */     
/*  1462 */     String fid = "MQBACK(Hconn,final Pint,final Pint)";
/*       */ 
/*       */     
/*  1465 */     if (Trace.isOn) {
/*  1466 */       Trace.data(this, "MQBACK(Hconn,final Pint,final Pint)", "__________");
/*  1467 */       Trace.data(this, "MQBACK(Hconn,final Pint,final Pint)", "MQBACK >>");
/*  1468 */       Trace.data(this, "MQBACK(Hconn,final Pint,final Pint)", "Hconn", hconn);
/*  1469 */       Trace.data(this, "MQBACK(Hconn,final Pint,final Pint)", "CompCode", pCompCode);
/*  1470 */       Trace.data(this, "MQBACK(Hconn,final Pint,final Pint)", "Reason", pReason);
/*  1471 */       Trace.data(this, "MQBACK(Hconn,final Pint,final Pint)", "__________");
/*       */     } 
/*       */     
/*  1474 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  1475 */     final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/*  1476 */     LocalHconn lockedLocalhconn = null;
/*       */     try {
/*       */       final byte[] rxpbBuf;
/*  1479 */       final LocalHconn localhconn = getLocalHconn(hconn);
/*  1480 */       final byte[] qmName = localhconn.getQMNameBytes();
/*       */       
/*  1482 */       RXPB rxpb = null;
/*       */       
/*  1484 */       if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/*  1485 */         rxpbBuf = null;
/*       */       } else {
/*       */         
/*  1488 */         if (this.inheritRRSContext) {
/*  1489 */           rxpb = getInheritedRxpb(tls, jTls, localhconn);
/*       */         } else {
/*       */           
/*  1492 */           rxpb = localhconn.getRxpb();
/*       */         } 
/*  1494 */         int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/*  1495 */         if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/*  1496 */           tls.rxpbBuf = new byte[rxpbLen];
/*       */         }
/*  1498 */         rxpbBuf = tls.rxpbBuf;
/*  1499 */         rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  1505 */       if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hconn)) {
/*  1506 */         localhconn.syncExec(new JmqiRunnable(this.env)
/*       */             {
/*       */               public void run()
/*       */               {
/*  1510 */                 if (Trace.isOn) {
/*  1511 */                   Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                 }
/*       */ 
/*       */                 
/*       */                 try {
/*  1516 */                   localhconn.enterCall();
/*       */                   
/*  1518 */                   boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  1519 */                   Native.MQBACK(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, pCompCode, pReason);
/*       */                 }
/*  1521 */                 catch (JmqiException e) {
/*  1522 */                   if (Trace.isOn) {
/*  1523 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                   }
/*       */                   
/*  1526 */                   jTls.lastException = e;
/*  1527 */                   pCompCode.x = e.getCompCode();
/*  1528 */                   pReason.x = e.getReason();
/*       */                 }
/*  1530 */                 catch (UnsatisfiedLinkError e) {
/*  1531 */                   if (Trace.isOn) {
/*  1532 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                   }
/*  1534 */                   pCompCode.x = 2;
/*  1535 */                   pReason.x = 2298;
/*       */                 } finally {
/*       */                   
/*  1538 */                   if (Trace.isOn) {
/*  1539 */                     Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                   
/*       */                   try {
/*  1543 */                     localhconn.leaveCall();
/*       */                   }
/*  1545 */                   catch (JmqiException e) {
/*  1546 */                     if (Trace.isOn) {
/*  1547 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                     }
/*  1549 */                     jTls.lastException = e;
/*  1550 */                     pCompCode.x = e.getCompCode();
/*  1551 */                     pReason.x = e.getReason();
/*       */                   } 
/*       */                 } 
/*  1554 */                 if (Trace.isOn) {
/*  1555 */                   Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                 
/*       */                 }
/*       */               }
/*       */             });
/*       */       }
/*       */       else {
/*       */         
/*  1563 */         if (this.adapterIsRRS) {
/*  1564 */           localhconn.enterCall();
/*  1565 */           lockedLocalhconn = localhconn;
/*       */         } 
/*  1567 */         boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  1568 */         Native.MQBACK(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, pCompCode, pReason);
/*       */       } 
/*       */ 
/*       */       
/*  1572 */       if (this.ffstOnError && pCompCode.x == 2) {
/*  1573 */         HashMap<String, Object> info = new HashMap<>();
/*  1574 */         info.put("Hconn", hconn);
/*  1575 */         info.put("CompCode", pCompCode);
/*  1576 */         info.put("Reason", pReason);
/*  1577 */         Trace.ffst(this, "MQBACK(Hconn,final Pint,final Pint)", "1", info, null);
/*       */       }
/*       */     
/*  1580 */     } catch (JmqiException e) {
/*  1581 */       if (Trace.isOn) {
/*  1582 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQBACK(Hconn,final Pint,final Pint)", (Throwable)e, 1);
/*       */       }
/*       */       
/*  1585 */       jTls.lastException = e;
/*  1586 */       pCompCode.x = e.getCompCode();
/*  1587 */       pReason.x = e.getReason();
/*       */     }
/*  1589 */     catch (UnsatisfiedLinkError e) {
/*  1590 */       if (Trace.isOn) {
/*  1591 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQBACK(Hconn,final Pint,final Pint)", e, 2);
/*       */       }
/*       */       
/*  1594 */       pCompCode.x = 2;
/*  1595 */       pReason.x = 2298;
/*       */     }
/*  1597 */     catch (Throwable e) {
/*  1598 */       if (Trace.isOn) {
/*  1599 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQBACK(Hconn,final Pint,final Pint)", e, 3);
/*       */       }
/*       */       
/*  1602 */       castUnexpectedException("MQBACK(Hconn,final Pint,final Pint)", 1, e);
/*       */     } finally {
/*       */       
/*  1605 */       if (Trace.isOn) {
/*  1606 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQBACK(Hconn,final Pint,final Pint)");
/*       */       }
/*       */       
/*  1609 */       if (lockedLocalhconn != null) {
/*       */         
/*       */         try {
/*  1612 */           lockedLocalhconn.leaveCall();
/*       */         }
/*  1614 */         catch (JmqiException e) {
/*  1615 */           if (Trace.isOn) {
/*  1616 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQBACK(Hconn,final Pint,final Pint)", (Throwable)e, 4);
/*       */           }
/*       */           
/*  1619 */           jTls.lastException = e;
/*  1620 */           pCompCode.x = e.getCompCode();
/*  1621 */           pReason.x = e.getReason();
/*       */         } 
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  1630 */       if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES && this.inheritRRSContext && 
/*  1631 */         pReason.x == 2009) {
/*  1632 */         clearInheritedRXPB(jTls);
/*       */       }
/*       */     } 
/*       */ 
/*       */ 
/*       */     
/*  1638 */     if (Trace.isOn) {
/*  1639 */       Trace.data(this, "MQBACK(Hconn,final Pint,final Pint)", "__________");
/*  1640 */       Trace.data(this, "MQBACK(Hconn,final Pint,final Pint)", "MQBACK <<");
/*  1641 */       Trace.data(this, "MQBACK(Hconn,final Pint,final Pint)", "Hconn", hconn);
/*  1642 */       Trace.data(this, "MQBACK(Hconn,final Pint,final Pint)", "CompCode", pCompCode);
/*  1643 */       Trace.data(this, "MQBACK(Hconn,final Pint,final Pint)", "Reason", pReason);
/*  1644 */       Trace.data(this, "MQBACK(Hconn,final Pint,final Pint)", "__________");
/*       */     } 
/*  1646 */     if (Trace.isOn) {
/*  1647 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQBACK(Hconn,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQBEGIN(Hconn hconn, MQBO pBeginOptions, final Pint pCompCode, final Pint pReason) {
/*  1663 */     if (Trace.isOn) {
/*  1664 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQBEGIN(Hconn,MQBO,final Pint,final Pint)", new Object[] { hconn, pBeginOptions, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */     
/*  1668 */     String fid = "MQBEGIN(Hconn,MQBO,final Pint,final Pint)";
/*       */ 
/*       */     
/*  1671 */     if (Trace.isOn) {
/*  1672 */       Trace.data(this, "MQBEGIN(Hconn,MQBO,final Pint,final Pint)", "__________");
/*  1673 */       Trace.data(this, "MQBEGIN(Hconn,MQBO,final Pint,final Pint)", "MQBEGIN >>");
/*  1674 */       Trace.data(this, "MQBEGIN(Hconn,MQBO,final Pint,final Pint)", "Hconn", hconn);
/*  1675 */       Trace.data(this, "MQBEGIN(Hconn,MQBO,final Pint,final Pint)", "BeginOptions", pBeginOptions);
/*  1676 */       Trace.data(this, "MQBEGIN(Hconn,MQBO,final Pint,final Pint)", "CompCode", pCompCode);
/*  1677 */       Trace.data(this, "MQBEGIN(Hconn,MQBO,final Pint,final Pint)", "Reason", pReason);
/*  1678 */       Trace.data(this, "MQBEGIN(Hconn,MQBO,final Pint,final Pint)", "__________");
/*       */     } 
/*       */     
/*  1681 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  1682 */     final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/*  1683 */     LocalHconn lockedLocalhconn = null;
/*       */     try {
/*       */       final byte[] rxpbBuf, boBuf;
/*  1686 */       final LocalHconn localhconn = getLocalHconn(hconn);
/*  1687 */       final byte[] qmName = localhconn.getQMNameBytes();
/*       */       
/*  1689 */       RXPB rxpb = null;
/*       */       
/*  1691 */       if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/*  1692 */         rxpbBuf = null;
/*       */       } else {
/*       */         
/*  1695 */         if (this.inheritRRSContext) {
/*  1696 */           rxpb = getInheritedRxpb(tls, jTls, localhconn);
/*       */         } else {
/*       */           
/*  1699 */           rxpb = localhconn.getRxpb();
/*       */         } 
/*  1701 */         int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/*  1702 */         if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/*  1703 */           tls.rxpbBuf = new byte[rxpbLen];
/*       */         }
/*  1705 */         rxpbBuf = tls.rxpbBuf;
/*  1706 */         rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */       
/*  1710 */       if (pBeginOptions != null) {
/*  1711 */         int boLen = pBeginOptions.getRequiredBufferSize(ptrSize, nativeCp);
/*  1712 */         boBuf = new byte[boLen];
/*  1713 */         pBeginOptions.writeToBuffer(boBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } else {
/*       */         
/*  1716 */         boBuf = null;
/*       */       } 
/*  1718 */       if (Trace.isOn) {
/*  1719 */         Trace.data(this, "MQBEGIN(Hconn,MQBO,final Pint,final Pint)", "BeginOptions", boBuf);
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  1725 */       if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hconn)) {
/*  1726 */         localhconn.syncExec(new JmqiRunnable(this.env)
/*       */             {
/*       */               public void run()
/*       */               {
/*  1730 */                 if (Trace.isOn) {
/*  1731 */                   Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                 }
/*       */ 
/*       */                 
/*       */                 try {
/*  1736 */                   localhconn.enterCall();
/*       */                   
/*  1738 */                   boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  1739 */                   Native.MQBEGIN(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, boBuf, pCompCode, pReason);
/*       */                 }
/*  1741 */                 catch (JmqiException e) {
/*  1742 */                   if (Trace.isOn) {
/*  1743 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                   }
/*       */                   
/*  1746 */                   jTls.lastException = e;
/*  1747 */                   pCompCode.x = e.getCompCode();
/*  1748 */                   pReason.x = e.getReason();
/*       */                 }
/*  1750 */                 catch (UnsatisfiedLinkError e) {
/*  1751 */                   if (Trace.isOn) {
/*  1752 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                   }
/*  1754 */                   pCompCode.x = 2;
/*  1755 */                   pReason.x = 2298;
/*       */                 } finally {
/*       */                   
/*  1758 */                   if (Trace.isOn) {
/*  1759 */                     Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                   
/*       */                   try {
/*  1763 */                     localhconn.leaveCall();
/*       */                   }
/*  1765 */                   catch (JmqiException e) {
/*  1766 */                     if (Trace.isOn) {
/*  1767 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                     }
/*  1769 */                     jTls.lastException = e;
/*  1770 */                     pCompCode.x = e.getCompCode();
/*  1771 */                     pReason.x = e.getReason();
/*       */                   } 
/*       */                 } 
/*  1774 */                 if (Trace.isOn) {
/*  1775 */                   Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                 
/*       */                 }
/*       */               }
/*       */             });
/*       */       }
/*       */       else {
/*       */         
/*  1783 */         if (this.adapterIsRRS) {
/*  1784 */           localhconn.enterCall();
/*  1785 */           lockedLocalhconn = localhconn;
/*       */         } 
/*  1787 */         boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  1788 */         Native.MQBEGIN(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, boBuf, pCompCode, pReason);
/*       */       } 
/*       */ 
/*       */       
/*  1792 */       if (pBeginOptions != null) {
/*  1793 */         pBeginOptions.readFromBuffer(boBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       }
/*       */     }
/*  1796 */     catch (JmqiException e) {
/*  1797 */       if (Trace.isOn) {
/*  1798 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQBEGIN(Hconn,MQBO,final Pint,final Pint)", (Throwable)e, 1);
/*       */       }
/*       */       
/*  1801 */       jTls.lastException = e;
/*  1802 */       pCompCode.x = e.getCompCode();
/*  1803 */       pReason.x = e.getReason();
/*       */     }
/*  1805 */     catch (UnsatisfiedLinkError e) {
/*  1806 */       if (Trace.isOn) {
/*  1807 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQBEGIN(Hconn,MQBO,final Pint,final Pint)", e, 2);
/*       */       }
/*       */       
/*  1810 */       pCompCode.x = 2;
/*  1811 */       pReason.x = 2298;
/*       */     }
/*  1813 */     catch (Throwable e) {
/*  1814 */       if (Trace.isOn) {
/*  1815 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQBEGIN(Hconn,MQBO,final Pint,final Pint)", e, 3);
/*       */       }
/*       */       
/*  1818 */       castUnexpectedException("MQBEGIN(Hconn,MQBO,final Pint,final Pint)", 1, e);
/*       */     } finally {
/*       */       
/*  1821 */       if (Trace.isOn) {
/*  1822 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQBEGIN(Hconn,MQBO,final Pint,final Pint)");
/*       */       }
/*       */       
/*  1825 */       if (lockedLocalhconn != null) {
/*       */         
/*       */         try {
/*  1828 */           lockedLocalhconn.leaveCall();
/*       */         }
/*  1830 */         catch (JmqiException e) {
/*  1831 */           if (Trace.isOn) {
/*  1832 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQBEGIN(Hconn,MQBO,final Pint,final Pint)", (Throwable)e, 4);
/*       */           }
/*       */           
/*  1835 */           jTls.lastException = e;
/*  1836 */           pCompCode.x = e.getCompCode();
/*  1837 */           pReason.x = e.getReason();
/*       */         } 
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  1846 */       if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES && this.inheritRRSContext && 
/*  1847 */         pReason.x == 2009) {
/*  1848 */         clearInheritedRXPB(jTls);
/*       */       }
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  1855 */     if (Trace.isOn) {
/*  1856 */       Trace.data(this, "MQBEGIN(Hconn,MQBO,final Pint,final Pint)", "__________");
/*  1857 */       Trace.data(this, "MQBEGIN(Hconn,MQBO,final Pint,final Pint)", "MQBEGIN <<");
/*  1858 */       Trace.data(this, "MQBEGIN(Hconn,MQBO,final Pint,final Pint)", "Hconn", hconn);
/*  1859 */       Trace.data(this, "MQBEGIN(Hconn,MQBO,final Pint,final Pint)", "BeginOptions", pBeginOptions);
/*  1860 */       Trace.data(this, "MQBEGIN(Hconn,MQBO,final Pint,final Pint)", "CompCode", pCompCode);
/*  1861 */       Trace.data(this, "MQBEGIN(Hconn,MQBO,final Pint,final Pint)", "Reason", pReason);
/*  1862 */       Trace.data(this, "MQBEGIN(Hconn,MQBO,final Pint,final Pint)", "__________");
/*       */     } 
/*  1864 */     if (Trace.isOn) {
/*  1865 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQBEGIN(Hconn,MQBO,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQCLOSE(Hconn hconn, Phobj phobj, final int options, final Pint pCompCode, final Pint pReason) {
/*  1882 */     if (Trace.isOn) {
/*  1883 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQCLOSE(Hconn,Phobj,final int,final Pint,final Pint)", new Object[] { hconn, phobj, 
/*       */             
/*  1885 */             Integer.valueOf(options), pCompCode, pReason });
/*       */     }
/*  1887 */     String fid = "MQCLOSE(Hconn,Phobj,final int,final Pint,final Pint)";
/*       */ 
/*       */     
/*  1890 */     if (Trace.isOn) {
/*  1891 */       Trace.data(this, "MQCLOSE(Hconn,Phobj,final int,final Pint,final Pint)", "__________");
/*  1892 */       Trace.data(this, "MQCLOSE(Hconn,Phobj,final int,final Pint,final Pint)", "MQCLOSE >>");
/*  1893 */       Trace.data(this, "MQCLOSE(Hconn,Phobj,final int,final Pint,final Pint)", "Hconn", hconn);
/*  1894 */       Trace.data(this, "MQCLOSE(Hconn,Phobj,final int,final Pint,final Pint)", "Options", "0x" + Integer.toHexString(options));
/*  1895 */       Trace.data(this, "MQCLOSE(Hconn,Phobj,final int,final Pint,final Pint)", "Phobj", phobj);
/*  1896 */       Trace.data(this, "MQCLOSE(Hconn,Phobj,final int,final Pint,final Pint)", "CompCode", pCompCode);
/*  1897 */       Trace.data(this, "MQCLOSE(Hconn,Phobj,final int,final Pint,final Pint)", "Reason", pReason);
/*  1898 */       Trace.data(this, "MQCLOSE(Hconn,Phobj,final int,final Pint,final Pint)", "__________");
/*       */     } 
/*       */     
/*  1901 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  1902 */     final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/*  1903 */     LocalHconn lockedLocalhconn = null; try {
/*       */       final byte[] rxpbBuf;
/*  1905 */       final LocalHconn localhconn = getLocalHconn(hconn);
/*  1906 */       final byte[] qmName = localhconn.getQMNameBytes();
/*  1907 */       final LocalHobj localhobj = LocalHobj.getLocalHobj(this.env, phobj.getHobj());
/*       */       
/*  1909 */       RXPB rxpb = null;
/*       */       
/*  1911 */       if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/*  1912 */         rxpbBuf = null;
/*       */       } else {
/*       */         
/*  1915 */         if (this.inheritRRSContext) {
/*  1916 */           rxpb = getInheritedRxpb(tls, jTls, localhconn);
/*  1917 */           if (!Arrays.equals(localhobj.getCtxToken(), rxpb.getCtxTkn()))
/*       */           {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */             
/*  1924 */             JmqiException e = new JmqiException(this.env, -1, null, 2, 2298, null);
/*       */             
/*  1926 */             if (Trace.isOn) {
/*  1927 */               Trace.throwing(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQCLOSE(Hconn,Phobj,final int,final Pint,final Pint)", (Throwable)e);
/*       */             }
/*       */             
/*  1930 */             throw e;
/*       */           }
/*       */         
/*       */         } else {
/*       */           
/*  1935 */           rxpb = localhconn.getRxpb();
/*       */         } 
/*  1937 */         int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/*  1938 */         if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/*  1939 */           tls.rxpbBuf = new byte[rxpbLen];
/*       */         }
/*  1941 */         rxpbBuf = tls.rxpbBuf;
/*  1942 */         rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  1954 */       if ((this.useWorkerThread || (this.useWorkerThreadForAsyncOnly && localhobj.isUsedForAsyncConsume())) && !jTls.isAsyncConsumeThread(hconn)) {
/*  1955 */         localhconn.syncExec(new JmqiRunnable(this.env)
/*       */             {
/*       */               public void run()
/*       */               {
/*  1959 */                 if (Trace.isOn) {
/*  1960 */                   Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                 }
/*       */ 
/*       */                 
/*       */                 try {
/*  1965 */                   localhconn.enterCall();
/*       */                   
/*  1967 */                   boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  1968 */                   Native.MQCLOSE(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, localhobj, options, pCompCode, pReason);
/*       */                 }
/*  1970 */                 catch (JmqiException e) {
/*  1971 */                   if (Trace.isOn) {
/*  1972 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                   }
/*       */                   
/*  1975 */                   jTls.lastException = e;
/*  1976 */                   pCompCode.x = e.getCompCode();
/*  1977 */                   pReason.x = e.getReason();
/*       */                 }
/*  1979 */                 catch (UnsatisfiedLinkError e) {
/*  1980 */                   if (Trace.isOn) {
/*  1981 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                   }
/*  1983 */                   pCompCode.x = 2;
/*  1984 */                   pReason.x = 2298;
/*       */                 } finally {
/*       */                   
/*  1987 */                   if (Trace.isOn) {
/*  1988 */                     Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                   
/*       */                   try {
/*  1992 */                     localhconn.leaveCall();
/*       */                   }
/*  1994 */                   catch (JmqiException e) {
/*  1995 */                     if (Trace.isOn) {
/*  1996 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                     }
/*  1998 */                     jTls.lastException = e;
/*  1999 */                     pCompCode.x = e.getCompCode();
/*  2000 */                     pReason.x = e.getReason();
/*       */                   } 
/*       */                 } 
/*  2003 */                 if (Trace.isOn) {
/*  2004 */                   Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                 
/*       */                 }
/*       */               }
/*       */             });
/*       */       }
/*       */       else {
/*       */         
/*  2012 */         if (this.adapterIsRRS) {
/*  2013 */           localhconn.enterCall();
/*  2014 */           lockedLocalhconn = localhconn;
/*       */         } 
/*       */ 
/*       */ 
/*       */         
/*  2019 */         boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  2020 */         Native.MQCLOSE(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, localhobj, options, pCompCode, pReason);
/*       */       } 
/*       */ 
/*       */       
/*  2024 */       localhobj.setHobj(phobj);
/*       */     }
/*  2026 */     catch (JmqiException e) {
/*  2027 */       if (Trace.isOn) {
/*  2028 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQCLOSE(Hconn,Phobj,final int,final Pint,final Pint)", (Throwable)e, 1);
/*       */       }
/*       */       
/*  2031 */       jTls.lastException = e;
/*  2032 */       pCompCode.x = e.getCompCode();
/*  2033 */       pReason.x = e.getReason();
/*       */     }
/*  2035 */     catch (Throwable e) {
/*  2036 */       if (Trace.isOn) {
/*  2037 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQCLOSE(Hconn,Phobj,final int,final Pint,final Pint)", e, 2);
/*       */       }
/*       */       
/*  2040 */       castUnexpectedException("MQCLOSE(Hconn,Phobj,final int,final Pint,final Pint)", 1, e);
/*       */     }
/*       */     finally {
/*       */       
/*  2044 */       if (Trace.isOn) {
/*  2045 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQCLOSE(Hconn,Phobj,final int,final Pint,final Pint)");
/*       */       }
/*       */       
/*  2048 */       if (lockedLocalhconn != null) {
/*       */         
/*       */         try {
/*  2051 */           lockedLocalhconn.leaveCall();
/*       */         }
/*  2053 */         catch (JmqiException e) {
/*  2054 */           if (Trace.isOn) {
/*  2055 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQCLOSE(Hconn,Phobj,final int,final Pint,final Pint)", (Throwable)e, 3);
/*       */           }
/*       */           
/*  2058 */           jTls.lastException = e;
/*  2059 */           pCompCode.x = e.getCompCode();
/*  2060 */           pReason.x = e.getReason();
/*       */         } 
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  2069 */       if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES && this.inheritRRSContext && 
/*  2070 */         pReason.x == 2009) {
/*  2071 */         clearInheritedRXPB(jTls);
/*       */       }
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  2078 */     if (Trace.isOn) {
/*  2079 */       Trace.data(this, "MQCLOSE(Hconn,Phobj,final int,final Pint,final Pint)", "__________");
/*  2080 */       Trace.data(this, "MQCLOSE(Hconn,Phobj,final int,final Pint,final Pint)", "MQCLOSE <<");
/*  2081 */       Trace.data(this, "MQCLOSE(Hconn,Phobj,final int,final Pint,final Pint)", "Hconn", hconn);
/*  2082 */       Trace.data(this, "MQCLOSE(Hconn,Phobj,final int,final Pint,final Pint)", "Options", "0x" + Integer.toHexString(options));
/*  2083 */       Trace.data(this, "MQCLOSE(Hconn,Phobj,final int,final Pint,final Pint)", "Hobj", phobj);
/*  2084 */       Trace.data(this, "MQCLOSE(Hconn,Phobj,final int,final Pint,final Pint)", "CompCode", pCompCode);
/*  2085 */       Trace.data(this, "MQCLOSE(Hconn,Phobj,final int,final Pint,final Pint)", "Reason", pReason);
/*  2086 */       Trace.data(this, "MQCLOSE(Hconn,Phobj,final int,final Pint,final Pint)", "__________");
/*       */     } 
/*  2088 */     if (Trace.isOn) {
/*  2089 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQCLOSE(Hconn,Phobj,final int,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQCB(Hconn hconn, final int operation, MQCBD pCallbackDesc, Hobj hobj, MQMD pMsgDesc, MQGMO getMsgOpts, final Pint pCompCode, final Pint pReason) {
/*  2117 */     if (Trace.isOn) {
/*  2118 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQCB(final Hconn,final int,final MQCBD,Hobj,final MQMD,final MQGMO,final Pint,final Pint)", new Object[] { hconn, 
/*       */             
/*  2120 */             Integer.valueOf(operation), pCallbackDesc, hobj, pMsgDesc, getMsgOpts, pCompCode, pReason });
/*       */     }
/*       */     
/*  2123 */     String fid = "MQCB(final Hconn,final int,final MQCBD,Hobj,final MQMD,final MQGMO,final Pint,final Pint)";
/*       */ 
/*       */     
/*  2126 */     if (Trace.isOn) {
/*  2127 */       Trace.data(this, "MQCB(final Hconn,final int,final MQCBD,Hobj,final MQMD,final MQGMO,final Pint,final Pint)", "__________");
/*  2128 */       Trace.data(this, "MQCB(final Hconn,final int,final MQCBD,Hobj,final MQMD,final MQGMO,final Pint,final Pint)", "MQCB >>");
/*  2129 */       Trace.data(this, "MQCB(final Hconn,final int,final MQCBD,Hobj,final MQMD,final MQGMO,final Pint,final Pint)", "Hconn", hconn);
/*  2130 */       Trace.data(this, "MQCB(final Hconn,final int,final MQCBD,Hobj,final MQMD,final MQGMO,final Pint,final Pint)", "Operation", Integer.toString(operation));
/*  2131 */       Trace.data(this, "MQCB(final Hconn,final int,final MQCBD,Hobj,final MQMD,final MQGMO,final Pint,final Pint)", "CallbackDesc", pCallbackDesc);
/*  2132 */       Trace.data(this, "MQCB(final Hconn,final int,final MQCBD,Hobj,final MQMD,final MQGMO,final Pint,final Pint)", "Hobj", hobj);
/*  2133 */       Trace.data(this, "MQCB(final Hconn,final int,final MQCBD,Hobj,final MQMD,final MQGMO,final Pint,final Pint)", "MsgDesc", pMsgDesc);
/*  2134 */       Trace.data(this, "MQCB(final Hconn,final int,final MQCBD,Hobj,final MQMD,final MQGMO,final Pint,final Pint)", "GetMsgOpts", getMsgOpts);
/*  2135 */       Trace.data(this, "MQCB(final Hconn,final int,final MQCBD,Hobj,final MQMD,final MQGMO,final Pint,final Pint)", "CompCode", pCompCode);
/*  2136 */       Trace.data(this, "MQCB(final Hconn,final int,final MQCBD,Hobj,final MQMD,final MQGMO,final Pint,final Pint)", "Reason", pReason);
/*  2137 */       Trace.data(this, "MQCB(final Hconn,final int,final MQCBD,Hobj,final MQMD,final MQGMO,final Pint,final Pint)", "__________");
/*       */     } 
/*       */     
/*  2140 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  2141 */     final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/*  2142 */     LocalHconn lockedLocalhconn = null; try {
/*       */       final byte[] rxpbBuf, mqmdBuf, optBuf; LocalProxyConsumer proxy;
/*  2144 */       final LocalHconn localhconn = getLocalHconn(hconn);
/*  2145 */       final LocalHobj localhobj = LocalHobj.getLocalHobj(this.env, hobj);
/*  2146 */       final byte[] qmName = localhconn.getQMNameBytes();
/*  2147 */       int userMqcbdOptions = 0;
/*       */       
/*  2149 */       RXPB rxpb = null;
/*       */       
/*  2151 */       if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/*  2152 */         rxpbBuf = null;
/*       */       } else {
/*       */         
/*  2155 */         rxpb = localhconn.getRxpb();
/*  2156 */         int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/*  2157 */         if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/*  2158 */           tls.rxpbBuf = new byte[rxpbLen];
/*       */         }
/*  2160 */         rxpbBuf = tls.rxpbBuf;
/*  2161 */         rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */       
/*  2165 */       if (pCallbackDesc == null) {
/*  2166 */         pCompCode.x = 2;
/*  2167 */         pReason.x = 2321;
/*  2168 */         if (Trace.isOn) {
/*  2169 */           Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQCB(final Hconn,final int,final MQCBD,Hobj,final MQMD,final MQGMO,final Pint,final Pint)", 1);
/*       */         }
/*       */ 
/*       */ 
/*       */         
/*       */         return;
/*       */       } 
/*       */ 
/*       */       
/*  2178 */       int jmqiMqcbdOptions = userMqcbdOptions = pCallbackDesc.getOptions();
/*  2179 */       if (pCallbackDesc.getCallbackType() == 2) {
/*  2180 */         jmqiMqcbdOptions |= 0x200;
/*       */       } else {
/*       */         
/*  2183 */         jmqiMqcbdOptions |= 0x204;
/*       */       } 
/*  2185 */       pCallbackDesc.setOptions(jmqiMqcbdOptions);
/*  2186 */       int cbdLen = pCallbackDesc.getRequiredBufferSize(ptrSize, nativeCp);
/*  2187 */       if (tls.descBuf == null || tls.descBuf.length < cbdLen) {
/*  2188 */         tls.descBuf = new byte[cbdLen];
/*       */       }
/*  2190 */       final byte[] descBuf = tls.descBuf;
/*  2191 */       pCallbackDesc.writeToBuffer(descBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */ 
/*       */       
/*  2194 */       if (pMsgDesc == null) {
/*  2195 */         mqmdBuf = null;
/*       */       } else {
/*       */         
/*  2198 */         int mqmdLen = pMsgDesc.getRequiredBufferSize(ptrSize, nativeCp);
/*  2199 */         if (tls.mqmdBuf == null || tls.mqmdBuf.length < mqmdLen) {
/*  2200 */           tls.mqmdBuf = new byte[mqmdLen];
/*       */         }
/*  2202 */         mqmdBuf = tls.mqmdBuf;
/*  2203 */         pMsgDesc.writeToBuffer(mqmdBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */       
/*  2207 */       if (getMsgOpts == null) {
/*  2208 */         optBuf = null;
/*       */       } else {
/*       */         
/*  2211 */         int gmoLen = getMsgOpts.getRequiredBufferSize(ptrSize, nativeCp);
/*  2212 */         if (tls.optBuf == null || tls.optBuf.length < gmoLen) {
/*  2213 */           tls.optBuf = new byte[gmoLen];
/*       */         }
/*  2215 */         optBuf = tls.optBuf;
/*  2216 */         getMsgOpts.writeToBuffer(optBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  2227 */       int callbackType = pCallbackDesc.getCallbackType();
/*  2228 */       if (callbackType == 2) {
/*  2229 */         proxy = localhconn.getProxyConsumer();
/*       */       } else {
/*       */         
/*  2232 */         proxy = localhobj.getProxyConsumer();
/*       */         
/*  2234 */         localhobj.setUsedForAsyncConsume(true);
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/*  2239 */       proxy.setOptions(userMqcbdOptions);
/*  2240 */       proxy.setMQCBD(pCallbackDesc);
/*  2241 */       proxy.setThreadClassloader(JmqiTools.getThreadContextClassLoader());
/*       */       
/*  2243 */       int callbackIndex = 0;
/*  2244 */       final Class<LocalProxyConsumer> callbackClass = LocalProxyConsumer.class;
/*  2245 */       final Object callbackObject = proxy;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  2253 */       if ((this.useWorkerThread || this.useWorkerThreadForAsyncOnly) && !jTls.isAsyncConsumeThread(hconn)) {
/*  2254 */         localhconn.syncExec(new JmqiRunnable(this.env)
/*       */             {
/*       */               public void run()
/*       */               {
/*  2258 */                 if (Trace.isOn) {
/*  2259 */                   Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                 }
/*       */ 
/*       */                 
/*       */                 try {
/*  2264 */                   localhconn.enterCall();
/*       */                   
/*  2266 */                   boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  2267 */                   Native.MQCB(isClassTraced, this.env, 0, callbackClass, callbackObject, localhconn
/*       */ 
/*       */ 
/*       */ 
/*       */                       
/*  2272 */                       .getValue(), qmName, rxpbBuf, operation, localhobj, mqmdBuf, optBuf, descBuf, pCompCode, pReason);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */                 
/*       */                 }
/*  2283 */                 catch (JmqiException e) {
/*  2284 */                   if (Trace.isOn) {
/*  2285 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                   }
/*       */                   
/*  2288 */                   jTls.lastException = e;
/*  2289 */                   pCompCode.x = e.getCompCode();
/*  2290 */                   pReason.x = e.getReason();
/*       */                 } finally {
/*       */                   
/*  2293 */                   if (Trace.isOn) {
/*  2294 */                     Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                   
/*       */                   try {
/*  2298 */                     localhconn.leaveCall();
/*       */                   }
/*  2300 */                   catch (JmqiException e) {
/*  2301 */                     if (Trace.isOn) {
/*  2302 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 2);
/*       */                     }
/*  2304 */                     jTls.lastException = e;
/*  2305 */                     pCompCode.x = e.getCompCode();
/*  2306 */                     pReason.x = e.getReason();
/*       */                   } 
/*       */                 } 
/*  2309 */                 if (Trace.isOn) {
/*  2310 */                   Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                 }
/*       */               }
/*       */             });
/*       */       }
/*       */       else {
/*       */         
/*  2317 */         if (this.adapterIsRRS) {
/*  2318 */           localhconn.enterCall();
/*  2319 */           lockedLocalhconn = localhconn;
/*       */         } 
/*  2321 */         boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  2322 */         Native.MQCB(isClassTraced, this.env, 0, callbackClass, callbackObject, localhconn
/*       */ 
/*       */ 
/*       */ 
/*       */             
/*  2327 */             .getValue(), qmName, rxpbBuf, operation, localhobj, mqmdBuf, optBuf, descBuf, pCompCode, pReason);
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  2340 */       pCallbackDesc.readFromBuffer(tls.descBuf, 0, ptrSize, swap, nativeCp, jTls);
/*  2341 */       pCallbackDesc.setOptions(userMqcbdOptions);
/*       */       
/*  2343 */       if (pMsgDesc != null) {
/*  2344 */         pMsgDesc.readFromBuffer(tls.mqmdBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       }
/*  2346 */       if (getMsgOpts != null) {
/*  2347 */         getMsgOpts.readFromBuffer(tls.optBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       }
/*       */     }
/*  2350 */     catch (JmqiException e) {
/*  2351 */       if (Trace.isOn) {
/*  2352 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQCB(final Hconn,final int,final MQCBD,Hobj,final MQMD,final MQGMO,final Pint,final Pint)", (Throwable)e, 1);
/*       */       }
/*       */ 
/*       */       
/*  2356 */       jTls.lastException = e;
/*  2357 */       pCompCode.x = e.getCompCode();
/*  2358 */       pReason.x = e.getReason();
/*       */     }
/*  2360 */     catch (UnsatisfiedLinkError e) {
/*  2361 */       if (Trace.isOn) {
/*  2362 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQCB(final Hconn,final int,final MQCBD,Hobj,final MQMD,final MQGMO,final Pint,final Pint)", e, 2);
/*       */       }
/*       */ 
/*       */       
/*  2366 */       pCompCode.x = 2;
/*  2367 */       pReason.x = 2298;
/*       */     }
/*  2369 */     catch (Throwable e) {
/*  2370 */       if (Trace.isOn) {
/*  2371 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQCB(final Hconn,final int,final MQCBD,Hobj,final MQMD,final MQGMO,final Pint,final Pint)", e, 3);
/*       */       }
/*       */ 
/*       */       
/*  2375 */       castUnexpectedException("MQCB(final Hconn,final int,final MQCBD,Hobj,final MQMD,final MQGMO,final Pint,final Pint)", 1, e);
/*       */     } finally {
/*       */       
/*  2378 */       if (Trace.isOn) {
/*  2379 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQCB(final Hconn,final int,final MQCBD,Hobj,final MQMD,final MQGMO,final Pint,final Pint)");
/*       */       }
/*       */       
/*  2382 */       if (lockedLocalhconn != null) {
/*       */         
/*       */         try {
/*  2385 */           lockedLocalhconn.leaveCall();
/*       */         }
/*  2387 */         catch (JmqiException e) {
/*  2388 */           if (Trace.isOn) {
/*  2389 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQCB(final Hconn,final int,final MQCBD,Hobj,final MQMD,final MQGMO,final Pint,final Pint)", (Throwable)e, 4);
/*       */           }
/*       */ 
/*       */           
/*  2393 */           jTls.lastException = e;
/*  2394 */           pCompCode.x = e.getCompCode();
/*  2395 */           pReason.x = e.getReason();
/*       */         } 
/*       */       }
/*       */     } 
/*       */ 
/*       */     
/*  2401 */     if (Trace.isOn) {
/*  2402 */       Trace.data(this, "MQCB(final Hconn,final int,final MQCBD,Hobj,final MQMD,final MQGMO,final Pint,final Pint)", "__________");
/*  2403 */       Trace.data(this, "MQCB(final Hconn,final int,final MQCBD,Hobj,final MQMD,final MQGMO,final Pint,final Pint)", "MQCB <<");
/*  2404 */       Trace.data(this, "MQCB(final Hconn,final int,final MQCBD,Hobj,final MQMD,final MQGMO,final Pint,final Pint)", "Hconn", hconn);
/*  2405 */       Trace.data(this, "MQCB(final Hconn,final int,final MQCBD,Hobj,final MQMD,final MQGMO,final Pint,final Pint)", "Operation", Integer.toString(operation));
/*  2406 */       Trace.data(this, "MQCB(final Hconn,final int,final MQCBD,Hobj,final MQMD,final MQGMO,final Pint,final Pint)", "CallbackDesc", pCallbackDesc);
/*  2407 */       Trace.data(this, "MQCB(final Hconn,final int,final MQCBD,Hobj,final MQMD,final MQGMO,final Pint,final Pint)", "Hobj", hobj);
/*  2408 */       Trace.data(this, "MQCB(final Hconn,final int,final MQCBD,Hobj,final MQMD,final MQGMO,final Pint,final Pint)", "MsgDesc", pMsgDesc);
/*  2409 */       Trace.data(this, "MQCB(final Hconn,final int,final MQCBD,Hobj,final MQMD,final MQGMO,final Pint,final Pint)", "GetMsgOpts", getMsgOpts);
/*  2410 */       Trace.data(this, "MQCB(final Hconn,final int,final MQCBD,Hobj,final MQMD,final MQGMO,final Pint,final Pint)", "CompCode", pCompCode);
/*  2411 */       Trace.data(this, "MQCB(final Hconn,final int,final MQCBD,Hobj,final MQMD,final MQGMO,final Pint,final Pint)", "Reason", pReason);
/*  2412 */       Trace.data(this, "MQCB(final Hconn,final int,final MQCBD,Hobj,final MQMD,final MQGMO,final Pint,final Pint)", "__________");
/*       */     } 
/*  2414 */     if (Trace.isOn) {
/*  2415 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQCB(final Hconn,final int,final MQCBD,Hobj,final MQMD,final MQGMO,final Pint,final Pint)", 2);
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQCMIT(Hconn hconn, final Pint pCompCode, final Pint pReason) {
/*  2430 */     if (Trace.isOn) {
/*  2431 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQCMIT(Hconn,final Pint,final Pint)", new Object[] { hconn, pCompCode, pReason });
/*       */     }
/*       */     
/*  2434 */     String fid = "MQCMIT(Hconn,final Pint,final Pint)";
/*       */ 
/*       */     
/*  2437 */     if (Trace.isOn) {
/*  2438 */       Trace.data(this, "MQCMIT(Hconn,final Pint,final Pint)", "__________");
/*  2439 */       Trace.data(this, "MQCMIT(Hconn,final Pint,final Pint)", "MQCMIT >>");
/*  2440 */       Trace.data(this, "MQCMIT(Hconn,final Pint,final Pint)", "Hconn", hconn);
/*  2441 */       Trace.data(this, "MQCMIT(Hconn,final Pint,final Pint)", "CompCode", pCompCode);
/*  2442 */       Trace.data(this, "MQCMIT(Hconn,final Pint,final Pint)", "Reason", pReason);
/*  2443 */       Trace.data(this, "MQCMIT(Hconn,final Pint,final Pint)", "__________");
/*       */     } 
/*       */     
/*  2446 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  2447 */     final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/*  2448 */     LocalHconn lockedLocalhconn = null; try {
/*       */       final byte[] rxpbBuf;
/*  2450 */       final LocalHconn localhconn = getLocalHconn(hconn);
/*  2451 */       final byte[] qmName = localhconn.getQMNameBytes();
/*       */       
/*  2453 */       RXPB rxpb = null;
/*       */       
/*  2455 */       if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/*  2456 */         rxpbBuf = null;
/*       */       } else {
/*       */         
/*  2459 */         if (this.inheritRRSContext) {
/*  2460 */           rxpb = getInheritedRxpb(tls, jTls, localhconn);
/*       */         } else {
/*       */           
/*  2463 */           rxpb = localhconn.getRxpb();
/*       */         } 
/*  2465 */         int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/*  2466 */         if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/*  2467 */           tls.rxpbBuf = new byte[rxpbLen];
/*       */         }
/*  2469 */         rxpbBuf = tls.rxpbBuf;
/*  2470 */         rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  2476 */       if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hconn)) {
/*  2477 */         localhconn.syncExec(new JmqiRunnable(this.env)
/*       */             {
/*       */               public void run()
/*       */               {
/*  2481 */                 if (Trace.isOn) {
/*  2482 */                   Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                 }
/*       */ 
/*       */                 
/*       */                 try {
/*  2487 */                   localhconn.enterCall();
/*       */                   
/*  2489 */                   boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  2490 */                   Native.MQCMIT(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, pCompCode, pReason);
/*       */                 }
/*  2492 */                 catch (JmqiException e) {
/*  2493 */                   if (Trace.isOn) {
/*  2494 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                   }
/*       */                   
/*  2497 */                   jTls.lastException = e;
/*  2498 */                   pCompCode.x = e.getCompCode();
/*  2499 */                   pReason.x = e.getReason();
/*       */                 }
/*  2501 */                 catch (UnsatisfiedLinkError e) {
/*  2502 */                   if (Trace.isOn) {
/*  2503 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                   }
/*  2505 */                   pCompCode.x = 2;
/*  2506 */                   pReason.x = 2298;
/*       */                 } finally {
/*       */                   
/*  2509 */                   if (Trace.isOn) {
/*  2510 */                     Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                   
/*       */                   try {
/*  2514 */                     localhconn.leaveCall();
/*       */                   }
/*  2516 */                   catch (JmqiException e) {
/*  2517 */                     if (Trace.isOn) {
/*  2518 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                     }
/*  2520 */                     jTls.lastException = e;
/*  2521 */                     pCompCode.x = e.getCompCode();
/*  2522 */                     pReason.x = e.getReason();
/*       */                   } 
/*       */                 } 
/*  2525 */                 if (Trace.isOn) {
/*  2526 */                   Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                 
/*       */                 }
/*       */               }
/*       */             });
/*       */       }
/*       */       else {
/*       */         
/*  2534 */         if (this.adapterIsRRS) {
/*  2535 */           localhconn.enterCall();
/*  2536 */           lockedLocalhconn = localhconn;
/*       */         } 
/*  2538 */         boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  2539 */         Native.MQCMIT(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, pCompCode, pReason);
/*       */       }
/*       */     
/*  2542 */     } catch (JmqiException e) {
/*  2543 */       if (Trace.isOn) {
/*  2544 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQCMIT(Hconn,final Pint,final Pint)", (Throwable)e, 1);
/*       */       }
/*       */       
/*  2547 */       jTls.lastException = e;
/*  2548 */       pCompCode.x = e.getCompCode();
/*  2549 */       pReason.x = e.getReason();
/*       */     }
/*  2551 */     catch (UnsatisfiedLinkError e) {
/*  2552 */       if (Trace.isOn) {
/*  2553 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQCMIT(Hconn,final Pint,final Pint)", e, 2);
/*       */       }
/*       */       
/*  2556 */       pCompCode.x = 2;
/*  2557 */       pReason.x = 2298;
/*       */     }
/*  2559 */     catch (Throwable e) {
/*  2560 */       if (Trace.isOn) {
/*  2561 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQCMIT(Hconn,final Pint,final Pint)", e, 3);
/*       */       }
/*       */       
/*  2564 */       castUnexpectedException("MQCMIT(Hconn,final Pint,final Pint)", 1, e);
/*       */     } finally {
/*       */       
/*  2567 */       if (Trace.isOn) {
/*  2568 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQCMIT(Hconn,final Pint,final Pint)");
/*       */       }
/*       */       
/*  2571 */       if (lockedLocalhconn != null) {
/*       */         
/*       */         try {
/*  2574 */           lockedLocalhconn.leaveCall();
/*       */         }
/*  2576 */         catch (JmqiException e) {
/*  2577 */           if (Trace.isOn) {
/*  2578 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQCMIT(Hconn,final Pint,final Pint)", (Throwable)e, 4);
/*       */           }
/*       */           
/*  2581 */           jTls.lastException = e;
/*  2582 */           pCompCode.x = e.getCompCode();
/*  2583 */           pReason.x = e.getReason();
/*       */         } 
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  2592 */       if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES && this.inheritRRSContext && 
/*  2593 */         pReason.x == 2009) {
/*  2594 */         clearInheritedRXPB(jTls);
/*       */       }
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  2601 */     if (Trace.isOn) {
/*  2602 */       Trace.data(this, "MQCMIT(Hconn,final Pint,final Pint)", "__________");
/*  2603 */       Trace.data(this, "MQCMIT(Hconn,final Pint,final Pint)", "MQCMIT <<");
/*  2604 */       Trace.data(this, "MQCMIT(Hconn,final Pint,final Pint)", "Hconn", hconn);
/*  2605 */       Trace.data(this, "MQCMIT(Hconn,final Pint,final Pint)", "CompCode", pCompCode);
/*  2606 */       Trace.data(this, "MQCMIT(Hconn,final Pint,final Pint)", "Reason", pReason);
/*  2607 */       Trace.data(this, "MQCMIT(Hconn,final Pint,final Pint)", "__________");
/*       */     } 
/*  2609 */     if (Trace.isOn) {
/*  2610 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQCMIT(Hconn,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQCONN(String pQMgrNameParam, Phconn phconn, final Pint pCompCode, final Pint pReason) {
/*  2625 */     if (Trace.isOn) {
/*  2626 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQCONN(String,Phconn,final Pint,final Pint)", new Object[] { pQMgrNameParam, phconn, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */     
/*  2630 */     String fid = "MQCONN(String,Phconn,final Pint,final Pint)";
/*       */     
/*  2632 */     String pQMgrName = pQMgrNameParam;
/*       */     
/*  2634 */     if (Trace.isOn) {
/*  2635 */       Trace.data(this, "MQCONN(String,Phconn,final Pint,final Pint)", "__________");
/*  2636 */       Trace.data(this, "MQCONN(String,Phconn,final Pint,final Pint)", "MQCONN >>");
/*  2637 */       Trace.data(this, "MQCONN(String,Phconn,final Pint,final Pint)", "Hconn", phconn);
/*  2638 */       Trace.data(this, "MQCONN(String,Phconn,final Pint,final Pint)", "CompCode", pCompCode);
/*  2639 */       Trace.data(this, "MQCONN(String,Phconn,final Pint,final Pint)", "Reason", pReason);
/*  2640 */       Trace.data(this, "MQCONN(String,Phconn,final Pint,final Pint)", "__________");
/*       */     } 
/*       */     
/*  2643 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  2644 */     final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/*  2645 */     LocalHconn lockedLocalhconn = null;
/*  2646 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*       */ 
/*       */ 
/*       */     
/*       */     try {
/*  2651 */       final byte[] qmNameBytes = new byte[48];
/*  2652 */       dc.writeMQField(pQMgrName, qmNameBytes, 0, 48, nativeCp, jTls);
/*       */       
/*  2654 */       if (phconn == null) {
/*  2655 */         pCompCode.x = 2;
/*  2656 */         pReason.x = 2018;
/*       */       } else {
/*       */         final byte[] rxpbBuf;
/*  2659 */         final LocalHconn localhconn = getLocalHconn(phconn.getHconn());
/*  2660 */         localhconn.setQMNameBytes(qmNameBytes);
/*  2661 */         localhconn.setOriginalQueueManagerName(pQMgrName);
/*       */         
/*  2663 */         RXPB rxpb = null;
/*  2664 */         int originalRxpbFlags = 0;
/*       */         
/*  2666 */         if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/*  2667 */           rxpbBuf = null;
/*       */         } else {
/*       */           
/*  2670 */           Adapter adapter = getAdapter();
/*  2671 */           rxpb = adapter.buildNewRxpb(this.env, null);
/*       */           
/*  2673 */           originalRxpbFlags = rxpb.getFlags();
/*  2674 */           setRXPBFlags(tls, jTls, localhconn, rxpb, null);
/*  2675 */           int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/*  2676 */           if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/*  2677 */             tls.rxpbBuf = new byte[rxpbLen];
/*       */           }
/*  2679 */           rxpbBuf = tls.rxpbBuf;
/*  2680 */           rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  2687 */         if (this.useWorkerThread) {
/*  2688 */           localhconn.syncExec(new JmqiRunnable(this.env)
/*       */               {
/*       */                 public void run()
/*       */                 {
/*  2692 */                   if (Trace.isOn) {
/*  2693 */                     Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                   }
/*       */ 
/*       */                   
/*       */                   try {
/*  2698 */                     localhconn.enterCall();
/*       */ 
/*       */                     
/*  2701 */                     Adapter adapter = LocalMQ.this.getAdapter();
/*  2702 */                     boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  2703 */                     adapter.connect(0, isClassTraced, qmNameBytes, rxpbBuf, null, null, localhconn, -1, pCompCode, pReason);
/*       */                   }
/*  2705 */                   catch (JmqiException e) {
/*  2706 */                     if (Trace.isOn) {
/*  2707 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                     }
/*       */                     
/*  2710 */                     jTls.lastException = e;
/*  2711 */                     pCompCode.x = e.getCompCode();
/*  2712 */                     pReason.x = e.getReason();
/*       */                   }
/*  2714 */                   catch (UnsatisfiedLinkError e) {
/*  2715 */                     if (Trace.isOn) {
/*  2716 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                     }
/*  2718 */                     pCompCode.x = 2;
/*  2719 */                     pReason.x = 2298;
/*       */                   } finally {
/*       */                     
/*  2722 */                     if (Trace.isOn) {
/*  2723 */                       Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                     }
/*       */                     
/*       */                     try {
/*  2727 */                       localhconn.leaveCall();
/*       */                     }
/*  2729 */                     catch (JmqiException e) {
/*  2730 */                       if (Trace.isOn) {
/*  2731 */                         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                       }
/*  2733 */                       jTls.lastException = e;
/*  2734 */                       pCompCode.x = e.getCompCode();
/*  2735 */                       pReason.x = e.getReason();
/*       */                     } 
/*       */                   } 
/*  2738 */                   if (Trace.isOn) {
/*  2739 */                     Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                 }
/*       */               });
/*       */         }
/*       */         else {
/*       */           
/*  2746 */           if (this.adapterIsRRS) {
/*       */             
/*  2748 */             localhconn.enterCall();
/*  2749 */             lockedLocalhconn = localhconn;
/*       */           } 
/*       */ 
/*       */           
/*  2753 */           Adapter adapter = getAdapter();
/*  2754 */           boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  2755 */           adapter.connect(0, isClassTraced, qmNameBytes, rxpbBuf, null, null, localhconn, -1, pCompCode, pReason);
/*       */         } 
/*       */ 
/*       */         
/*  2759 */         localhconn.updateHconn(this, phconn);
/*       */         
/*  2761 */         if (rxpb != null && rxpbBuf != null) {
/*  2762 */           rxpb.readFromBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  2770 */           if (Trace.isOn) {
/*  2771 */             Trace.data("MQCONN(String,Phconn,final Pint,final Pint)", "RXPB after conn conn call", rxpb);
/*       */           }
/*       */           
/*  2774 */           rxpb.setFlags(originalRxpbFlags);
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  2782 */         if ((pCompCode.x == 0 || pCompCode.x == 1) && (
/*  2783 */           pQMgrName == null || "".equals(pQMgrName.trim()))) {
/*  2784 */           pQMgrName = localhconn.getName();
/*  2785 */           dc.writeMQField(pQMgrName, qmNameBytes, 0, 48, nativeCp, jTls);
/*  2786 */           localhconn.setQMNameBytes(qmNameBytes);
/*       */         }
/*       */       
/*       */       }
/*       */     
/*  2791 */     } catch (JmqiException e) {
/*  2792 */       if (Trace.isOn) {
/*  2793 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQCONN(String,Phconn,final Pint,final Pint)", (Throwable)e, 1);
/*       */       }
/*       */       
/*  2796 */       jTls.lastException = e;
/*  2797 */       pCompCode.x = e.getCompCode();
/*  2798 */       pReason.x = e.getReason();
/*       */     }
/*  2800 */     catch (UnsatisfiedLinkError e) {
/*  2801 */       if (Trace.isOn) {
/*  2802 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQCONN(String,Phconn,final Pint,final Pint)", e, 2);
/*       */       }
/*       */       
/*  2805 */       pCompCode.x = 2;
/*  2806 */       pReason.x = 2298;
/*       */     }
/*  2808 */     catch (UnsupportedEncodingException e) {
/*  2809 */       if (Trace.isOn) {
/*  2810 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQCONN(String,Phconn,final Pint,final Pint)", e, 3);
/*       */       }
/*       */       
/*  2813 */       pCompCode.x = 2;
/*  2814 */       pReason.x = 2340;
/*       */     }
/*  2816 */     catch (Throwable e) {
/*  2817 */       if (Trace.isOn) {
/*  2818 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQCONN(String,Phconn,final Pint,final Pint)", e, 4);
/*       */       }
/*       */       
/*  2821 */       castUnexpectedException("MQCONN(String,Phconn,final Pint,final Pint)", 1, e);
/*       */     } finally {
/*       */       
/*  2824 */       if (Trace.isOn) {
/*  2825 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQCONN(String,Phconn,final Pint,final Pint)");
/*       */       }
/*       */       
/*  2828 */       if (lockedLocalhconn != null) {
/*       */         
/*       */         try {
/*  2831 */           lockedLocalhconn.leaveCall();
/*       */         }
/*  2833 */         catch (JmqiException e) {
/*  2834 */           if (Trace.isOn) {
/*  2835 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQCONN(String,Phconn,final Pint,final Pint)", (Throwable)e, 5);
/*       */           }
/*       */           
/*  2838 */           jTls.lastException = e;
/*  2839 */           pCompCode.x = e.getCompCode();
/*  2840 */           pReason.x = e.getReason();
/*       */         } 
/*       */       }
/*       */     } 
/*       */ 
/*       */     
/*  2846 */     if (Trace.isOn) {
/*  2847 */       Trace.data(this, "MQCONN(String,Phconn,final Pint,final Pint)", "__________");
/*  2848 */       Trace.data(this, "MQCONN(String,Phconn,final Pint,final Pint)", "MQCONN <<");
/*  2849 */       Trace.data(this, "MQCONN(String,Phconn,final Pint,final Pint)", "Hconn", phconn);
/*  2850 */       Trace.data(this, "MQCONN(String,Phconn,final Pint,final Pint)", "CompCode", pCompCode);
/*  2851 */       Trace.data(this, "MQCONN(String,Phconn,final Pint,final Pint)", "Reason", pReason);
/*  2852 */       Trace.data(this, "MQCONN(String,Phconn,final Pint,final Pint)", "__________");
/*       */     } 
/*  2854 */     if (Trace.isOn) {
/*  2855 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQCONN(String,Phconn,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQCONNX(String pQMgrName, MQCNO pConnectOpts, Phconn phconn, Pint pCompCode, Pint pReason) {
/*  2873 */     jmqiConnect(pQMgrName, null, pConnectOpts, null, phconn, pCompCode, pReason);
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQCTL(Hconn hconn, final int operation, MQCTLO pControlOpts, final Pint pCompCode, final Pint pReason) {
/*  2887 */     if (Trace.isOn) {
/*  2888 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQCTL(Hconn,final int,MQCTLO,final Pint,final Pint)", new Object[] { hconn, 
/*       */             
/*  2890 */             Integer.valueOf(operation), pControlOpts, pCompCode, pReason });
/*       */     }
/*  2892 */     String fid = "MQCTL(Hconn,final int,MQCTLO,final Pint,final Pint)";
/*       */ 
/*       */     
/*  2895 */     if (Trace.isOn) {
/*  2896 */       Trace.data(this, "MQCTL(Hconn,final int,MQCTLO,final Pint,final Pint)", "__________");
/*  2897 */       Trace.data(this, "MQCTL(Hconn,final int,MQCTLO,final Pint,final Pint)", "MQCTL >>");
/*  2898 */       Trace.data(this, "MQCTL(Hconn,final int,MQCTLO,final Pint,final Pint)", "Operation", Integer.toString(operation));
/*  2899 */       Trace.data(this, "MQCTL(Hconn,final int,MQCTLO,final Pint,final Pint)", "ControlOpts", pControlOpts);
/*  2900 */       Trace.data(this, "MQCTL(Hconn,final int,MQCTLO,final Pint,final Pint)", "CompCode", pCompCode);
/*  2901 */       Trace.data(this, "MQCTL(Hconn,final int,MQCTLO,final Pint,final Pint)", "Reason", pReason);
/*  2902 */       Trace.data(this, "MQCTL(Hconn,final int,MQCTLO,final Pint,final Pint)", "__________");
/*       */     } 
/*       */     
/*  2905 */     final LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  2906 */     final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/*  2907 */     LocalHconn lockedLocalhconn = null; try {
/*       */       final byte[] rxpbBuf;
/*  2909 */       final LocalHconn localhconn = getLocalHconn(hconn);
/*  2910 */       final byte[] qmName = localhconn.getQMNameBytes();
/*       */       
/*  2912 */       RXPB rxpb = null;
/*       */       
/*  2914 */       if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/*  2915 */         rxpbBuf = null;
/*       */       } else {
/*       */         
/*  2918 */         rxpb = localhconn.getRxpb();
/*  2919 */         int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/*  2920 */         if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/*  2921 */           tls.rxpbBuf = new byte[rxpbLen];
/*       */         }
/*  2923 */         rxpbBuf = tls.rxpbBuf;
/*  2924 */         rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/*  2929 */       if (pControlOpts == null) {
/*  2930 */         int ctloLen = 0;
/*       */       } else {
/*       */         
/*  2933 */         int ctloLen = pControlOpts.getRequiredBufferSize(ptrSize, nativeCp);
/*  2934 */         if (tls.optBuf == null || tls.optBuf.length < ctloLen) {
/*  2935 */           tls.optBuf = new byte[ctloLen];
/*       */         }
/*  2937 */         pControlOpts.writeToBuffer(tls.optBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/*  2942 */       if ((this.useWorkerThread || this.useWorkerThreadForAsyncOnly) && !jTls.isAsyncConsumeThread(hconn)) {
/*  2943 */         localhconn.syncExec(new JmqiRunnable(this.env)
/*       */             {
/*       */               public void run()
/*       */               {
/*  2947 */                 if (Trace.isOn) {
/*  2948 */                   Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                 }
/*       */ 
/*       */                 
/*       */                 try {
/*  2953 */                   localhconn.enterCall();
/*       */                   
/*  2955 */                   boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  2956 */                   Native.MQCTL(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, operation, tls.optBuf, pCompCode, pReason);
/*       */                 }
/*  2958 */                 catch (JmqiException e) {
/*  2959 */                   if (Trace.isOn) {
/*  2960 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                   }
/*       */                   
/*  2963 */                   jTls.lastException = e;
/*  2964 */                   pCompCode.x = e.getCompCode();
/*  2965 */                   pReason.x = e.getReason();
/*       */                 }
/*  2967 */                 catch (UnsatisfiedLinkError e) {
/*  2968 */                   if (Trace.isOn) {
/*  2969 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                   }
/*  2971 */                   pCompCode.x = 2;
/*  2972 */                   pReason.x = 2298;
/*       */                 } finally {
/*       */                   
/*  2975 */                   if (Trace.isOn) {
/*  2976 */                     Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                   
/*       */                   try {
/*  2980 */                     localhconn.leaveCall();
/*       */                   }
/*  2982 */                   catch (JmqiException e) {
/*  2983 */                     if (Trace.isOn) {
/*  2984 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                     }
/*  2986 */                     jTls.lastException = e;
/*  2987 */                     pCompCode.x = e.getCompCode();
/*  2988 */                     pReason.x = e.getReason();
/*       */                   } 
/*       */                 } 
/*  2991 */                 if (Trace.isOn) {
/*  2992 */                   Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                 }
/*       */               }
/*       */             });
/*       */       }
/*       */       else {
/*       */         
/*  2999 */         if (this.adapterIsRRS) {
/*  3000 */           localhconn.enterCall();
/*  3001 */           lockedLocalhconn = localhconn;
/*       */         } 
/*  3003 */         boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  3004 */         Native.MQCTL(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, operation, tls.optBuf, pCompCode, pReason);
/*       */       } 
/*       */ 
/*       */       
/*  3008 */       if (pControlOpts != null) {
/*  3009 */         pControlOpts.readFromBuffer(tls.optBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       }
/*       */     }
/*  3012 */     catch (JmqiException e) {
/*  3013 */       if (Trace.isOn) {
/*  3014 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQCTL(Hconn,final int,MQCTLO,final Pint,final Pint)", (Throwable)e, 1);
/*       */       }
/*       */       
/*  3017 */       jTls.lastException = e;
/*  3018 */       pCompCode.x = e.getCompCode();
/*  3019 */       pReason.x = e.getReason();
/*       */     }
/*  3021 */     catch (UnsatisfiedLinkError e) {
/*  3022 */       if (Trace.isOn) {
/*  3023 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQCTL(Hconn,final int,MQCTLO,final Pint,final Pint)", e, 2);
/*       */       }
/*       */       
/*  3026 */       pCompCode.x = 2;
/*  3027 */       pReason.x = 2298;
/*       */     }
/*  3029 */     catch (Throwable e) {
/*  3030 */       if (Trace.isOn) {
/*  3031 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQCTL(Hconn,final int,MQCTLO,final Pint,final Pint)", e, 3);
/*       */       }
/*       */       
/*  3034 */       castUnexpectedException("MQCTL(Hconn,final int,MQCTLO,final Pint,final Pint)", 1, e);
/*       */     } finally {
/*       */       
/*  3037 */       if (Trace.isOn) {
/*  3038 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQCTL(Hconn,final int,MQCTLO,final Pint,final Pint)");
/*       */       }
/*       */       
/*  3041 */       if (lockedLocalhconn != null) {
/*       */         
/*       */         try {
/*  3044 */           lockedLocalhconn.leaveCall();
/*       */         }
/*  3046 */         catch (JmqiException e) {
/*  3047 */           if (Trace.isOn) {
/*  3048 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQCTL(Hconn,final int,MQCTLO,final Pint,final Pint)", (Throwable)e, 4);
/*       */           }
/*       */           
/*  3051 */           jTls.lastException = e;
/*  3052 */           pCompCode.x = e.getCompCode();
/*  3053 */           pReason.x = e.getReason();
/*       */         } 
/*       */       }
/*       */     } 
/*       */ 
/*       */     
/*  3059 */     if (Trace.isOn) {
/*  3060 */       Trace.data(this, "MQCTL(Hconn,final int,MQCTLO,final Pint,final Pint)", "__________");
/*  3061 */       Trace.data(this, "MQCTL(Hconn,final int,MQCTLO,final Pint,final Pint)", "MQCTL <<");
/*  3062 */       Trace.data(this, "MQCTL(Hconn,final int,MQCTLO,final Pint,final Pint)", "Operation", Integer.toString(operation));
/*  3063 */       Trace.data(this, "MQCTL(Hconn,final int,MQCTLO,final Pint,final Pint)", "ControlOpts", pControlOpts);
/*  3064 */       Trace.data(this, "MQCTL(Hconn,final int,MQCTLO,final Pint,final Pint)", "CompCode", pCompCode);
/*  3065 */       Trace.data(this, "MQCTL(Hconn,final int,MQCTLO,final Pint,final Pint)", "Reason", pReason);
/*  3066 */       Trace.data(this, "MQCTL(Hconn,final int,MQCTLO,final Pint,final Pint)", "__________");
/*       */     } 
/*  3068 */     if (Trace.isOn) {
/*  3069 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQCTL(Hconn,final int,MQCTLO,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQDISC(Phconn phconn, final Pint pCompCode, final Pint pReason) {
/*  3084 */     if (Trace.isOn) {
/*  3085 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQDISC(Phconn,final Pint,final Pint)", new Object[] { phconn, pCompCode, pReason });
/*       */     }
/*       */     
/*  3088 */     String fid = "MQDISC(Phconn,final Pint,final Pint)";
/*       */ 
/*       */     
/*  3091 */     if (Trace.isOn) {
/*  3092 */       Trace.data(this, "MQDISC(Phconn,final Pint,final Pint)", "__________");
/*  3093 */       Trace.data(this, "MQDISC(Phconn,final Pint,final Pint)", "MQDISC >>");
/*  3094 */       Trace.data(this, "MQDISC(Phconn,final Pint,final Pint)", "Hconn", phconn);
/*  3095 */       Trace.data(this, "MQDISC(Phconn,final Pint,final Pint)", "CompCode", pCompCode);
/*  3096 */       Trace.data(this, "MQDISC(Phconn,final Pint,final Pint)", "Reason", pReason);
/*  3097 */       Trace.data(this, "MQDISC(Phconn,final Pint,final Pint)", "__________");
/*       */     } 
/*       */     
/*  3100 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  3101 */     final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/*  3102 */     LocalHconn lockedLocalhconn = null;
/*       */ 
/*       */     
/*       */     try {
/*  3106 */       if (phconn == null) {
/*  3107 */         pCompCode.x = 2;
/*  3108 */         pReason.x = 2018;
/*       */       } else {
/*       */         final byte[] rxpbBuf;
/*  3111 */         final LocalHconn localhconn = getLocalHconn(phconn.getHconn());
/*  3112 */         final byte[] qmName = localhconn.getQMNameBytes();
/*       */         
/*  3114 */         RXPB rxpb = null;
/*       */         
/*  3116 */         if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/*  3117 */           rxpbBuf = null;
/*       */         } else {
/*       */           
/*  3120 */           rxpb = localhconn.getRxpb();
/*  3121 */           int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/*  3122 */           if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/*  3123 */             tls.rxpbBuf = new byte[rxpbLen];
/*       */           }
/*  3125 */           rxpbBuf = tls.rxpbBuf;
/*  3126 */           rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  3133 */         if (this.useWorkerThread && !jTls.isAsyncConsumeThread(localhconn)) {
/*  3134 */           localhconn.syncExec(new JmqiRunnable(this.env)
/*       */               {
/*       */                 public void run()
/*       */                 {
/*  3138 */                   if (Trace.isOn) {
/*  3139 */                     Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                   }
/*       */ 
/*       */                   
/*       */                   try {
/*  3144 */                     localhconn.enterCall();
/*  3145 */                     int jmqiOptions = LocalMQ.this.getJmqiDiscOptions(true);
/*  3146 */                     boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  3147 */                     LocalMQ.this.disconnect(jmqiOptions, isClassTraced, localhconn, qmName, rxpbBuf, pCompCode, pReason);
/*       */                   }
/*  3149 */                   catch (JmqiException e) {
/*  3150 */                     if (Trace.isOn) {
/*  3151 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                     }
/*       */                     
/*  3154 */                     jTls.lastException = e;
/*  3155 */                     pCompCode.x = e.getCompCode();
/*  3156 */                     pReason.x = e.getReason();
/*       */                   }
/*  3158 */                   catch (UnsatisfiedLinkError e) {
/*  3159 */                     if (Trace.isOn) {
/*  3160 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                     }
/*  3162 */                     pCompCode.x = 2;
/*  3163 */                     pReason.x = 2298;
/*       */                   } finally {
/*       */                     
/*  3166 */                     if (Trace.isOn) {
/*  3167 */                       Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                     }
/*       */                     
/*       */                     try {
/*  3171 */                       localhconn.leaveCall();
/*       */                     }
/*  3173 */                     catch (JmqiException e) {
/*  3174 */                       if (Trace.isOn) {
/*  3175 */                         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                       }
/*  3177 */                       jTls.lastException = e;
/*  3178 */                       pCompCode.x = e.getCompCode();
/*  3179 */                       pReason.x = e.getReason();
/*       */                     } 
/*       */                   } 
/*  3182 */                   if (Trace.isOn) {
/*  3183 */                     Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   
/*       */                   }
/*       */                 }
/*       */               });
/*       */         }
/*       */         else {
/*       */           
/*  3191 */           if (this.adapterIsRRS) {
/*       */             
/*  3193 */             localhconn.enterCall();
/*  3194 */             lockedLocalhconn = localhconn;
/*       */           } 
/*  3196 */           int jmqiOptions = getJmqiDiscOptions(false);
/*  3197 */           boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  3198 */           disconnect(jmqiOptions, isClassTraced, localhconn, qmName, rxpbBuf, pCompCode, pReason);
/*       */         } 
/*       */ 
/*       */         
/*  3202 */         localhconn.updateHconn(this, phconn);
/*       */       }
/*       */     
/*  3205 */     } catch (JmqiException e) {
/*  3206 */       if (Trace.isOn) {
/*  3207 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQDISC(Phconn,final Pint,final Pint)", (Throwable)e, 1);
/*       */       }
/*       */       
/*  3210 */       jTls.lastException = e;
/*  3211 */       pCompCode.x = e.getCompCode();
/*  3212 */       pReason.x = e.getReason();
/*       */     }
/*  3214 */     catch (UnsatisfiedLinkError e) {
/*  3215 */       if (Trace.isOn) {
/*  3216 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQDISC(Phconn,final Pint,final Pint)", e, 2);
/*       */       }
/*       */       
/*  3219 */       pCompCode.x = 2;
/*  3220 */       pReason.x = 2298;
/*       */     }
/*  3222 */     catch (Throwable e) {
/*  3223 */       if (Trace.isOn) {
/*  3224 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQDISC(Phconn,final Pint,final Pint)", e, 3);
/*       */       }
/*       */       
/*  3227 */       castUnexpectedException("MQDISC(Phconn,final Pint,final Pint)", 1, e);
/*       */     } finally {
/*       */       
/*  3230 */       if (Trace.isOn) {
/*  3231 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQDISC(Phconn,final Pint,final Pint)");
/*       */       }
/*       */       
/*  3234 */       if (lockedLocalhconn != null) {
/*       */         
/*       */         try {
/*  3237 */           lockedLocalhconn.leaveCall();
/*       */         }
/*  3239 */         catch (JmqiException e) {
/*  3240 */           if (Trace.isOn) {
/*  3241 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQDISC(Phconn,final Pint,final Pint)", (Throwable)e, 4);
/*       */           }
/*       */           
/*  3244 */           jTls.lastException = e;
/*  3245 */           pCompCode.x = e.getCompCode();
/*  3246 */           pReason.x = e.getReason();
/*       */         } 
/*       */       }
/*       */     } 
/*       */ 
/*       */     
/*  3252 */     if (Trace.isOn) {
/*  3253 */       Trace.data(this, "MQDISC(Phconn,final Pint,final Pint)", "__________");
/*  3254 */       Trace.data(this, "MQDISC(Phconn,final Pint,final Pint)", "MQDISC <<");
/*  3255 */       Trace.data(this, "MQDISC(Phconn,final Pint,final Pint)", "Hconn", phconn);
/*  3256 */       Trace.data(this, "MQDISC(Phconn,final Pint,final Pint)", "CompCode", pCompCode);
/*  3257 */       Trace.data(this, "MQDISC(Phconn,final Pint,final Pint)", "Reason", pReason);
/*  3258 */       Trace.data(this, "MQDISC(Phconn,final Pint,final Pint)", "__________");
/*       */     } 
/*  3260 */     if (Trace.isOn) {
/*  3261 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQDISC(Phconn,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   private void disconnect(int jmqiOptions, boolean isTraceActive, LocalHconn localhconn, byte[] qmName, byte[] rxpbBuf, Pint pCompCode, Pint pReason) throws JmqiException {
/*  3288 */     if (Trace.isOn) {
/*  3289 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "disconnect(int,boolean,LocalHconn,byte [ ],byte [ ],Pint,Pint)", new Object[] {
/*       */             
/*  3291 */             Integer.valueOf(jmqiOptions), Boolean.valueOf(isTraceActive), localhconn, qmName, rxpbBuf, pCompCode, pReason
/*       */           });
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  3298 */     boolean performDisconnect = true;
/*       */ 
/*       */ 
/*       */     
/*  3302 */     if (isIMS()) {
/*       */       
/*  3304 */       LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  3305 */       JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/*       */ 
/*       */ 
/*       */       
/*  3309 */       performDisconnect = jTls.removeHConnFromIMSState(localhconn);
/*       */       
/*  3311 */       if (!performDisconnect) {
/*  3312 */         localhconn.setValue(-3);
/*       */       }
/*       */     } 
/*       */     
/*  3316 */     if (performDisconnect) {
/*       */       try {
/*  3318 */         Native.jmqiDisc(jmqiOptions, isTraceActive, localhconn, qmName, rxpbBuf, pCompCode, pReason);
/*       */       }
/*  3320 */       catch (UnsatisfiedLinkError e) {
/*  3321 */         if (Trace.isOn) {
/*  3322 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "disconnect(int,boolean,LocalHconn,byte [ ],byte [ ],Pint,Pint)", e);
/*       */         }
/*       */         
/*  3325 */         Native.MQDISC(isTraceActive, localhconn, qmName, rxpbBuf, pCompCode, pReason);
/*       */       } 
/*       */     }
/*       */     
/*  3329 */     if (Trace.isOn) {
/*  3330 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "disconnect(int,boolean,LocalHconn,byte [ ],byte [ ],Pint,Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   private int getJmqiDiscOptions(boolean jmqiWorkerThread) throws JmqiException {
/*  3342 */     if (Trace.isOn)
/*  3343 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "getJmqiDiscOptions(boolean)", new Object[] {
/*  3344 */             Boolean.valueOf(jmqiWorkerThread)
/*       */           }); 
/*  3346 */     int result = 0;
/*       */     
/*  3348 */     if (jmqiWorkerThread) {
/*  3349 */       result |= 0x1;
/*       */     }
/*       */     
/*  3352 */     Adapter adapter = getAdapter();
/*  3353 */     if (adapter.isRRS()) {
/*  3354 */       result |= 0x2;
/*       */     }
/*       */     
/*  3357 */     if (Trace.isOn) {
/*  3358 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "getJmqiDiscOptions(boolean)", 
/*  3359 */           Integer.valueOf(result));
/*       */     }
/*  3361 */     return result;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQGET(Hconn hconn, Hobj hobj, MQMD pMsgDesc, MQGMO pGetMsgOpts, final int BufferLength, ByteBuffer pBuffer, final Pint pDataLength, final Pint pCompCode, final Pint pReason) {
/*  3388 */     if (Trace.isOn) {
/*  3389 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQGET(Hconn,Hobj,MQMD,MQGMO,final int,ByteBuffer,final Pint,final Pint,final Pint)", new Object[] { hconn, hobj, pMsgDesc, pGetMsgOpts, 
/*       */             
/*  3391 */             Integer.valueOf(BufferLength), pBuffer, pDataLength, pCompCode, pReason });
/*       */     }
/*       */     
/*  3394 */     if (pBuffer == null) {
/*  3395 */       NullPointerException npe = new NullPointerException();
/*  3396 */       if (Trace.isOn) {
/*  3397 */         Trace.throwing("com.ibm.mq.jmqi.local.LocalMQ", "MQGET(Hconn,Hobj,MQMD,MQGMO,final int,ByteBuffer,final Pint,final Pint,final Pint)", npe);
/*       */       }
/*       */ 
/*       */ 
/*       */       
/*  3402 */       if (Trace.isOn) {
/*  3403 */         Trace.throwing(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQGET(Hconn,Hobj,MQMD,MQGMO,final int,ByteBuffer,final Pint,final Pint,final Pint)", npe, 1);
/*       */       }
/*       */       
/*  3406 */       throw npe;
/*       */     } 
/*  3408 */     String fid = "MQGET(Hconn,Hobj,MQMD,MQGMO,final int,ByteBuffer,final Pint,final Pint,final Pint)";
/*       */ 
/*       */     
/*  3411 */     if (Trace.isOn) {
/*  3412 */       Trace.data(this, "MQGET(Hconn,Hobj,MQMD,MQGMO,final int,ByteBuffer,final Pint,final Pint,final Pint)", "__________");
/*  3413 */       Trace.data(this, "MQGET(Hconn,Hobj,MQMD,MQGMO,final int,ByteBuffer,final Pint,final Pint,final Pint)", "MQGET >>");
/*  3414 */       Trace.data(this, "MQGET(Hconn,Hobj,MQMD,MQGMO,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Hconn", hconn);
/*  3415 */       Trace.data(this, "MQGET(Hconn,Hobj,MQMD,MQGMO,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Hobj", hobj);
/*  3416 */       Trace.data(this, "MQGET(Hconn,Hobj,MQMD,MQGMO,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Msgdesc", pMsgDesc);
/*  3417 */       Trace.data(this, "MQGET(Hconn,Hobj,MQMD,MQGMO,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Getmsgops", pGetMsgOpts);
/*  3418 */       Trace.data(this, "MQGET(Hconn,Hobj,MQMD,MQGMO,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Bufferlength", Integer.toString(BufferLength));
/*  3419 */       Trace.data(this, "MQGET(Hconn,Hobj,MQMD,MQGMO,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Datalength", pDataLength);
/*  3420 */       Trace.data(this, "MQGET(Hconn,Hobj,MQMD,MQGMO,final int,ByteBuffer,final Pint,final Pint,final Pint)", "CompCode", pCompCode);
/*  3421 */       Trace.data(this, "MQGET(Hconn,Hobj,MQMD,MQGMO,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Reason", pReason);
/*  3422 */       Trace.data(this, "MQGET(Hconn,Hobj,MQMD,MQGMO,final int,ByteBuffer,final Pint,final Pint,final Pint)", "__________");
/*       */     } 
/*  3424 */     pCompCode.x = 0;
/*  3425 */     pReason.x = 0;
/*       */ 
/*       */     
/*  3428 */     if (BufferLength > 0 && 
/*  3429 */       BufferLength > pBuffer.limit()) {
/*  3430 */       Trace.data(this, "MQGET(Hconn,Hobj,MQMD,MQGMO,final int,ByteBuffer,final Pint,final Pint,final Pint)", "BufferLength:" + BufferLength + ", pBuffer.limit():" + pBuffer.limit(), null);
/*  3431 */       pCompCode.x = 2;
/*  3432 */       pReason.x = 2005;
/*       */     } 
/*       */     
/*  3435 */     if (pReason.x == 0) {
/*       */       
/*  3437 */       LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  3438 */       final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/*  3439 */       LocalHconn lockedLocalhconn = null;
/*       */ 
/*       */       
/*  3442 */       final byte[] pArray = pBuffer.array(); try {
/*       */         final byte[] rxpbBuf, mqmdBuf, optBuf;
/*  3444 */         final LocalHconn localhconn = getLocalHconn(hconn);
/*  3445 */         final LocalHobj localhobj = LocalHobj.getLocalHobj(this.env, hobj);
/*  3446 */         final byte[] qmName = localhconn.getQMNameBytes();
/*       */         
/*  3448 */         RXPB rxpb = null;
/*       */         
/*  3450 */         if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/*  3451 */           rxpbBuf = null;
/*       */         } else {
/*       */           
/*  3454 */           if (this.inheritRRSContext) {
/*  3455 */             rxpb = getInheritedRxpb(tls, jTls, localhconn);
/*  3456 */             if (!Arrays.equals(localhobj.getCtxToken(), rxpb.getCtxTkn()))
/*       */             {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */               
/*  3464 */               JmqiException e = new JmqiException(this.env, -1, null, 2, 2298, null);
/*       */               
/*  3466 */               if (Trace.isOn) {
/*  3467 */                 Trace.throwing(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQGET(Hconn,Hobj,MQMD,MQGMO,final int,ByteBuffer,final Pint,final Pint,final Pint)", (Throwable)e, 2);
/*       */               }
/*       */ 
/*       */               
/*  3471 */               throw e;
/*       */             }
/*       */           
/*       */           } else {
/*       */             
/*  3476 */             rxpb = localhconn.getRxpb();
/*       */           } 
/*  3478 */           int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/*  3479 */           if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/*  3480 */             tls.rxpbBuf = new byte[rxpbLen];
/*       */           }
/*  3482 */           rxpbBuf = tls.rxpbBuf;
/*  3483 */           rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         } 
/*       */ 
/*       */         
/*  3487 */         if (pMsgDesc == null) {
/*  3488 */           mqmdBuf = null;
/*       */         } else {
/*       */           
/*  3491 */           int mqmdLen = pMsgDesc.getRequiredBufferSize(ptrSize, nativeCp);
/*  3492 */           if (tls.mqmdBuf == null || tls.mqmdBuf.length < mqmdLen) {
/*  3493 */             tls.mqmdBuf = new byte[mqmdLen];
/*       */           }
/*  3495 */           mqmdBuf = tls.mqmdBuf;
/*  3496 */           pMsgDesc.writeToBuffer(mqmdBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         } 
/*       */ 
/*       */         
/*  3500 */         if (pGetMsgOpts == null) {
/*  3501 */           optBuf = null;
/*       */         } else {
/*       */           
/*  3504 */           int gmoLen = pGetMsgOpts.getRequiredBufferSize(ptrSize, nativeCp);
/*  3505 */           if (tls.optBuf == null || tls.optBuf.length < gmoLen) {
/*  3506 */             tls.optBuf = new byte[gmoLen];
/*       */           }
/*  3508 */           optBuf = tls.optBuf;
/*  3509 */           pGetMsgOpts.writeToBuffer(optBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         } 
/*       */ 
/*       */         
/*  3513 */         if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hconn)) {
/*  3514 */           localhconn.syncExec(new JmqiRunnable(this.env)
/*       */               {
/*       */                 public void run()
/*       */                 {
/*  3518 */                   if (Trace.isOn) {
/*  3519 */                     Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                   }
/*  3521 */                   boolean MQGET_inProgressRegistered = false;
/*       */ 
/*       */                   
/*       */                   try {
/*  3525 */                     localhconn.enterCall();
/*       */                     
/*  3527 */                     localhconn.incrementMQGETinProgressCounter();
/*  3528 */                     MQGET_inProgressRegistered = true;
/*       */                     
/*  3530 */                     boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  3531 */                     Native.MQGET(isClassTraced, localhconn
/*  3532 */                         .getValue(), qmName, rxpbBuf, localhobj
/*       */ 
/*       */                         
/*  3535 */                         .getValue(), mqmdBuf, optBuf, BufferLength, pArray, pDataLength, pCompCode, pReason);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */                   
/*       */                   }
/*  3544 */                   catch (JmqiException e) {
/*  3545 */                     if (Trace.isOn) {
/*  3546 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                     }
/*       */                     
/*  3549 */                     jTls.lastException = e;
/*  3550 */                     pCompCode.x = e.getCompCode();
/*  3551 */                     pReason.x = e.getReason();
/*       */                   }
/*  3553 */                   catch (UnsatisfiedLinkError e) {
/*  3554 */                     if (Trace.isOn) {
/*  3555 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                     }
/*  3557 */                     pCompCode.x = 2;
/*  3558 */                     pReason.x = 2298;
/*       */                   } finally {
/*       */                     
/*  3561 */                     if (Trace.isOn) {
/*  3562 */                       Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                     }
/*  3564 */                     if (MQGET_inProgressRegistered) {
/*  3565 */                       localhconn.decrementMQGETinProgressCounter();
/*       */                     }
/*       */ 
/*       */                     
/*       */                     try {
/*  3570 */                       localhconn.leaveCall();
/*       */                     }
/*  3572 */                     catch (JmqiException e) {
/*  3573 */                       if (Trace.isOn) {
/*  3574 */                         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                       }
/*  3576 */                       jTls.lastException = e;
/*  3577 */                       pCompCode.x = e.getCompCode();
/*  3578 */                       pReason.x = e.getReason();
/*       */                     } 
/*       */                   } 
/*  3581 */                   if (Trace.isOn) {
/*  3582 */                     Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   
/*       */                   }
/*       */                 }
/*       */               });
/*       */         }
/*       */         else {
/*       */           
/*  3590 */           if (this.adapterIsRRS) {
/*  3591 */             localhconn.enterCall();
/*  3592 */             lockedLocalhconn = localhconn;
/*       */           } 
/*       */           try {
/*  3595 */             localhconn.incrementMQGETinProgressCounter();
/*  3596 */             boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  3597 */             Native.MQGET(isClassTraced, localhconn
/*  3598 */                 .getValue(), qmName, rxpbBuf, localhobj
/*       */ 
/*       */                 
/*  3601 */                 .getValue(), mqmdBuf, optBuf, BufferLength, pArray, pDataLength, pCompCode, pReason);
/*       */ 
/*       */ 
/*       */           
/*       */           }
/*       */           finally {
/*       */ 
/*       */ 
/*       */ 
/*       */             
/*  3611 */             if (Trace.isOn) {
/*  3612 */               Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQGET(Hconn,Hobj,MQMD,MQGMO,final int,ByteBuffer,final Pint,final Pint,final Pint)", 1);
/*       */             }
/*       */             
/*  3615 */             localhconn.decrementMQGETinProgressCounter();
/*       */           } 
/*       */         } 
/*       */ 
/*       */         
/*  3620 */         if (pMsgDesc != null) {
/*  3621 */           pMsgDesc.readFromBuffer(mqmdBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         }
/*       */         
/*  3624 */         if (pGetMsgOpts != null) {
/*  3625 */           pGetMsgOpts.readFromBuffer(optBuf, 0, true, ptrSize, swap, nativeCp, jTls);
/*       */         }
/*       */       }
/*  3628 */       catch (JmqiException e) {
/*  3629 */         if (Trace.isOn) {
/*  3630 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQGET(Hconn,Hobj,MQMD,MQGMO,final int,ByteBuffer,final Pint,final Pint,final Pint)", (Throwable)e, 1);
/*       */         }
/*       */         
/*  3633 */         jTls.lastException = e;
/*  3634 */         pCompCode.x = e.getCompCode();
/*  3635 */         pReason.x = e.getReason();
/*       */       }
/*  3637 */       catch (UnsatisfiedLinkError e) {
/*  3638 */         if (Trace.isOn) {
/*  3639 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQGET(Hconn,Hobj,MQMD,MQGMO,final int,ByteBuffer,final Pint,final Pint,final Pint)", e, 2);
/*       */         }
/*       */         
/*  3642 */         pCompCode.x = 2;
/*  3643 */         pReason.x = 2298;
/*       */       }
/*  3645 */       catch (Throwable e) {
/*  3646 */         if (Trace.isOn) {
/*  3647 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQGET(Hconn,Hobj,MQMD,MQGMO,final int,ByteBuffer,final Pint,final Pint,final Pint)", e, 3);
/*       */         }
/*       */         
/*  3650 */         castUnexpectedException("MQGET(Hconn,Hobj,MQMD,MQGMO,final int,ByteBuffer,final Pint,final Pint,final Pint)", 1, e);
/*       */       } finally {
/*       */         
/*  3653 */         if (Trace.isOn) {
/*  3654 */           Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQGET(Hconn,Hobj,MQMD,MQGMO,final int,ByteBuffer,final Pint,final Pint,final Pint)", 2);
/*       */         }
/*       */         
/*  3657 */         if (lockedLocalhconn != null) {
/*       */           
/*       */           try {
/*  3660 */             lockedLocalhconn.leaveCall();
/*       */           }
/*  3662 */           catch (JmqiException e) {
/*  3663 */             if (Trace.isOn) {
/*  3664 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQGET(Hconn,Hobj,MQMD,MQGMO,final int,ByteBuffer,final Pint,final Pint,final Pint)", (Throwable)e, 4);
/*       */             }
/*       */ 
/*       */             
/*  3668 */             jTls.lastException = e;
/*  3669 */             pCompCode.x = e.getCompCode();
/*  3670 */             pReason.x = e.getReason();
/*       */           } 
/*       */         }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  3682 */         if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES && this.inheritRRSContext && 
/*  3683 */           pReason.x == 2009) {
/*  3684 */           clearInheritedRXPB(jTls);
/*       */         }
/*       */       } 
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  3692 */     if (Trace.isOn) {
/*  3693 */       Trace.data(this, "MQGET(Hconn,Hobj,MQMD,MQGMO,final int,ByteBuffer,final Pint,final Pint,final Pint)", "__________");
/*  3694 */       Trace.data(this, "MQGET(Hconn,Hobj,MQMD,MQGMO,final int,ByteBuffer,final Pint,final Pint,final Pint)", "MQGET <<");
/*  3695 */       Trace.data(this, "MQGET(Hconn,Hobj,MQMD,MQGMO,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Hconn", hconn);
/*  3696 */       Trace.data(this, "MQGET(Hconn,Hobj,MQMD,MQGMO,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Hobj", hobj);
/*  3697 */       Trace.data(this, "MQGET(Hconn,Hobj,MQMD,MQGMO,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Msgdesc", pMsgDesc);
/*  3698 */       Trace.data(this, "MQGET(Hconn,Hobj,MQMD,MQGMO,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Getmsgops", pGetMsgOpts);
/*  3699 */       Trace.data(this, "MQGET(Hconn,Hobj,MQMD,MQGMO,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Bufferlength", Integer.toString(BufferLength));
/*  3700 */       JmqiTools.traceApiBuffer(this, "MQGET(Hconn,Hobj,MQMD,MQGMO,final int,ByteBuffer,final Pint,final Pint,final Pint)", pBuffer, BufferLength);
/*  3701 */       Trace.data(this, "MQGET(Hconn,Hobj,MQMD,MQGMO,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Datalength", pDataLength);
/*  3702 */       Trace.data(this, "MQGET(Hconn,Hobj,MQMD,MQGMO,final int,ByteBuffer,final Pint,final Pint,final Pint)", "CompCode", pCompCode);
/*  3703 */       Trace.data(this, "MQGET(Hconn,Hobj,MQMD,MQGMO,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Reason", pReason);
/*  3704 */       Trace.data(this, "MQGET(Hconn,Hobj,MQMD,MQGMO,final int,ByteBuffer,final Pint,final Pint,final Pint)", "__________");
/*       */     } 
/*  3706 */     if (Trace.isOn) {
/*  3707 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQGET(Hconn,Hobj,MQMD,MQGMO,final int,ByteBuffer,final Pint,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQINQ(Hconn hconn, Hobj hobj, final int SelectorCount, int[] pSelectors, final int IntAttrCount, int[] pIntAttrs, final int CharAttrLength, final byte[] pCharAttrs, final Pint pCompCode, final Pint pReason) {
/*  3739 */     if (Trace.isOn) {
/*  3740 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQINQ(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", new Object[] { hconn, hobj, 
/*       */             
/*  3742 */             Integer.valueOf(SelectorCount), pSelectors, 
/*  3743 */             Integer.valueOf(IntAttrCount), pIntAttrs, Integer.valueOf(CharAttrLength), pCharAttrs, pCompCode, pReason });
/*       */     }
/*       */     
/*  3746 */     String fid = "MQINQ(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)";
/*       */ 
/*       */     
/*  3749 */     if (Trace.isOn) {
/*  3750 */       Trace.data(this, "MQINQ(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "__________");
/*  3751 */       Trace.data(this, "MQINQ(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "MQINQ >>");
/*  3752 */       Trace.data(this, "MQINQ(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "Hconn", hconn);
/*  3753 */       Trace.data(this, "MQINQ(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "Hobj", hobj);
/*  3754 */       Trace.data(this, "MQINQ(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "Selectorcount", Integer.toString(SelectorCount));
/*  3755 */       Trace.data(this, "MQINQ(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "Selectors", pSelectors);
/*  3756 */       Trace.data(this, "MQINQ(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "Intattrcount", Integer.toString(IntAttrCount));
/*  3757 */       Trace.data(this, "MQINQ(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "Intattrs", pIntAttrs);
/*  3758 */       Trace.data(this, "MQINQ(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "Charattrlength", Integer.toString(CharAttrLength));
/*  3759 */       Trace.data(this, "MQINQ(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "Charattrs", pCharAttrs);
/*  3760 */       Trace.data(this, "MQINQ(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "CompCode", pCompCode);
/*  3761 */       Trace.data(this, "MQINQ(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "Reason", pReason);
/*  3762 */       Trace.data(this, "MQINQ(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "__________");
/*       */     } 
/*       */     
/*  3765 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  3766 */     final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/*  3767 */     LocalHconn lockedLocalhconn = null;
/*  3768 */     JmqiDC dc = this.sysenv.getDC(); try {
/*       */       final byte[] rxpbBuf, selectors, intAttrs;
/*  3770 */       final LocalHconn localhconn = getLocalHconn(hconn);
/*  3771 */       final LocalHobj localhobj = LocalHobj.getLocalHobj(this.env, hobj);
/*  3772 */       final byte[] qmName = localhconn.getQMNameBytes();
/*       */       
/*  3774 */       RXPB rxpb = null;
/*       */       
/*  3776 */       if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/*  3777 */         rxpbBuf = null;
/*       */       } else {
/*       */         
/*  3780 */         if (this.inheritRRSContext) {
/*  3781 */           rxpb = getInheritedRxpb(tls, jTls, localhconn);
/*  3782 */           if (!Arrays.equals(localhobj.getCtxToken(), rxpb.getCtxTkn()))
/*       */           {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */             
/*  3789 */             JmqiException e = new JmqiException(this.env, -1, null, 2, 2298, null);
/*       */             
/*  3791 */             if (Trace.isOn) {
/*  3792 */               Trace.throwing(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQINQ(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", (Throwable)e);
/*       */             }
/*       */ 
/*       */             
/*  3796 */             throw e;
/*       */           }
/*       */         
/*       */         } else {
/*       */           
/*  3801 */           rxpb = localhconn.getRxpb();
/*       */         } 
/*  3803 */         int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/*  3804 */         if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/*  3805 */           tls.rxpbBuf = new byte[rxpbLen];
/*       */         }
/*  3807 */         rxpbBuf = tls.rxpbBuf;
/*  3808 */         rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */       
/*  3812 */       if (pSelectors == null) {
/*  3813 */         selectors = null;
/*       */       } else {
/*       */         
/*  3816 */         selectors = new byte[SelectorCount * 4];
/*  3817 */         for (int i = 0; i < SelectorCount; i++) {
/*  3818 */           dc.writeI32(pSelectors[i], selectors, i * 4, swap);
/*       */         }
/*       */       } 
/*       */ 
/*       */       
/*  3823 */       if (pIntAttrs == null) {
/*  3824 */         intAttrs = null;
/*       */       } else {
/*       */         
/*  3827 */         intAttrs = new byte[IntAttrCount * 4];
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/*  3832 */       if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hconn)) {
/*  3833 */         localhconn.syncExec(new JmqiRunnable(this.env)
/*       */             {
/*       */               public void run()
/*       */               {
/*  3837 */                 if (Trace.isOn) {
/*  3838 */                   Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                 }
/*       */ 
/*       */                 
/*       */                 try {
/*  3843 */                   localhconn.enterCall();
/*       */                   
/*  3845 */                   boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  3846 */                   Native.MQINQ(isClassTraced, localhconn
/*  3847 */                       .getValue(), qmName, rxpbBuf, localhobj
/*       */ 
/*       */                       
/*  3850 */                       .getValue(), SelectorCount, selectors, IntAttrCount, intAttrs, CharAttrLength, pCharAttrs, pCompCode, pReason);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */                 
/*       */                 }
/*  3860 */                 catch (JmqiException e) {
/*  3861 */                   if (Trace.isOn) {
/*  3862 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                   }
/*       */                   
/*  3865 */                   jTls.lastException = e;
/*  3866 */                   pCompCode.x = e.getCompCode();
/*  3867 */                   pReason.x = e.getReason();
/*       */                 }
/*  3869 */                 catch (UnsatisfiedLinkError e) {
/*  3870 */                   if (Trace.isOn) {
/*  3871 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                   }
/*  3873 */                   pCompCode.x = 2;
/*  3874 */                   pReason.x = 2298;
/*       */                 } finally {
/*       */                   
/*  3877 */                   if (Trace.isOn) {
/*  3878 */                     Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                   
/*       */                   try {
/*  3882 */                     localhconn.leaveCall();
/*       */                   }
/*  3884 */                   catch (JmqiException e) {
/*  3885 */                     if (Trace.isOn) {
/*  3886 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                     }
/*  3888 */                     jTls.lastException = e;
/*  3889 */                     pCompCode.x = e.getCompCode();
/*  3890 */                     pReason.x = e.getReason();
/*       */                   } 
/*       */                 } 
/*  3893 */                 if (Trace.isOn) {
/*  3894 */                   Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                 
/*       */                 }
/*       */               }
/*       */             });
/*       */       }
/*       */       else {
/*       */         
/*  3902 */         if (this.adapterIsRRS) {
/*  3903 */           localhconn.enterCall();
/*  3904 */           lockedLocalhconn = localhconn;
/*       */         } 
/*  3906 */         boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  3907 */         Native.MQINQ(isClassTraced, localhconn
/*  3908 */             .getValue(), qmName, rxpbBuf, localhobj
/*       */ 
/*       */             
/*  3911 */             .getValue(), SelectorCount, selectors, IntAttrCount, intAttrs, CharAttrLength, pCharAttrs, pCompCode, pReason);
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  3923 */       if (pIntAttrs != null) {
/*  3924 */         for (int i = 0; i < IntAttrCount; i++) {
/*  3925 */           pIntAttrs[i] = dc.readI32(intAttrs, i * 4, swap);
/*       */         }
/*       */       }
/*       */     }
/*  3929 */     catch (JmqiException e) {
/*  3930 */       if (Trace.isOn) {
/*  3931 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQINQ(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", (Throwable)e, 1);
/*       */       }
/*       */ 
/*       */       
/*  3935 */       jTls.lastException = e;
/*  3936 */       pCompCode.x = e.getCompCode();
/*  3937 */       pReason.x = e.getReason();
/*       */     }
/*  3939 */     catch (UnsatisfiedLinkError e) {
/*  3940 */       if (Trace.isOn) {
/*  3941 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQINQ(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", e, 2);
/*       */       }
/*       */ 
/*       */       
/*  3945 */       pCompCode.x = 2;
/*  3946 */       pReason.x = 2298;
/*       */     }
/*  3948 */     catch (Throwable e) {
/*  3949 */       if (Trace.isOn) {
/*  3950 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQINQ(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", e, 3);
/*       */       }
/*       */ 
/*       */       
/*  3954 */       castUnexpectedException("MQINQ(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", 1, e);
/*       */     } finally {
/*       */       
/*  3957 */       if (Trace.isOn) {
/*  3958 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQINQ(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)");
/*       */       }
/*       */       
/*  3961 */       if (lockedLocalhconn != null) {
/*       */         
/*       */         try {
/*  3964 */           lockedLocalhconn.leaveCall();
/*       */         }
/*  3966 */         catch (JmqiException e) {
/*  3967 */           if (Trace.isOn) {
/*  3968 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQINQ(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", (Throwable)e, 4);
/*       */           }
/*       */ 
/*       */           
/*  3972 */           jTls.lastException = e;
/*  3973 */           pCompCode.x = e.getCompCode();
/*  3974 */           pReason.x = e.getReason();
/*       */         } 
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  3983 */       if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES && this.inheritRRSContext && 
/*  3984 */         pReason.x == 2009) {
/*  3985 */         clearInheritedRXPB(jTls);
/*       */       }
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  3992 */     if (Trace.isOn) {
/*  3993 */       Trace.data(this, "MQINQ(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "__________");
/*  3994 */       Trace.data(this, "MQINQ(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "MQINQ <<");
/*  3995 */       Trace.data(this, "MQINQ(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "Hconn", hconn);
/*  3996 */       Trace.data(this, "MQINQ(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "Hobj", hobj);
/*  3997 */       Trace.data(this, "MQINQ(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "Selectorcount", Integer.toString(SelectorCount));
/*  3998 */       Trace.data(this, "MQINQ(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "Selectors", pSelectors);
/*  3999 */       Trace.data(this, "MQINQ(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "Intattrcount", Integer.toString(IntAttrCount));
/*  4000 */       Trace.data(this, "MQINQ(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "Intattrs", pIntAttrs);
/*  4001 */       Trace.data(this, "MQINQ(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "Charattrlength", Integer.toString(CharAttrLength));
/*  4002 */       Trace.data(this, "MQINQ(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "Charattrs", pCharAttrs);
/*  4003 */       Trace.data(this, "MQINQ(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "CompCode", pCompCode);
/*  4004 */       Trace.data(this, "MQINQ(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "Reason", pReason);
/*  4005 */       Trace.data(this, "MQINQ(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "__________");
/*       */     } 
/*  4007 */     if (Trace.isOn) {
/*  4008 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQINQ(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   class BooleanWrapper
/*       */   {
/*       */     volatile boolean waiting;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQOPEN(final Hconn hconn, MQOD pObjDesc, final int Options, Phobj phobj, final Pint pCompCode, final Pint pReason) {
/*  4034 */     if (Trace.isOn) {
/*  4035 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQOPEN(Hconn,MQOD,final int,Phobj,final Pint,final Pint)", new Object[] { hconn, pObjDesc, 
/*       */             
/*  4037 */             Integer.valueOf(Options), phobj, pCompCode, pReason });
/*       */     }
/*  4039 */     String fid = "MQOPEN(Hconn,MQOD,final int,Phobj,final Pint,final Pint)";
/*       */ 
/*       */     
/*  4042 */     if (Trace.isOn) {
/*  4043 */       Trace.data(this, "MQOPEN(Hconn,MQOD,final int,Phobj,final Pint,final Pint)", "__________");
/*  4044 */       Trace.data(this, "MQOPEN(Hconn,MQOD,final int,Phobj,final Pint,final Pint)", "MQOPEN >>");
/*  4045 */       Trace.data(this, "MQOPEN(Hconn,MQOD,final int,Phobj,final Pint,final Pint)", "Hconn", hconn);
/*  4046 */       Trace.data(this, "MQOPEN(Hconn,MQOD,final int,Phobj,final Pint,final Pint)", "Objdesc", pObjDesc);
/*  4047 */       Trace.data(this, "MQOPEN(Hconn,MQOD,final int,Phobj,final Pint,final Pint)", "Options", "0x" + Integer.toHexString(Options));
/*  4048 */       Trace.data(this, "MQOPEN(Hconn,MQOD,final int,Phobj,final Pint,final Pint)", "Hobj", phobj);
/*  4049 */       Trace.data(this, "MQOPEN(Hconn,MQOD,final int,Phobj,final Pint,final Pint)", "CompCode", pCompCode);
/*  4050 */       Trace.data(this, "MQOPEN(Hconn,MQOD,final int,Phobj,final Pint,final Pint)", "Reason", pReason);
/*  4051 */       Trace.data(this, "MQOPEN(Hconn,MQOD,final int,Phobj,final Pint,final Pint)", "__________");
/*       */     } 
/*       */     
/*  4054 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  4055 */     final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/*  4056 */     LocalHconn lockedLocalhconn = null;
/*       */     try {
/*       */       final byte[] rxpbBuf, descBuf;
/*  4059 */       if ((Options & 0x100000) != 0 && (Options & 0x80000) != 0) {
/*  4060 */         JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2046, null);
/*       */ 
/*       */         
/*  4063 */         if (Trace.isOn) {
/*  4064 */           Trace.throwing(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQOPEN(Hconn,MQOD,final int,Phobj,final Pint,final Pint)", (Throwable)traceRet1);
/*       */         }
/*       */         
/*  4067 */         throw traceRet1;
/*       */       } 
/*       */       
/*  4070 */       final LocalHconn localhconn = getLocalHconn(hconn);
/*  4071 */       final LocalHobj localhobj = LocalHobj.getLocalHobj(this.env, phobj.getHobj());
/*  4072 */       final byte[] qmName = localhconn.getQMNameBytes();
/*       */ 
/*       */       
/*  4075 */       RXPB rxpb = null;
/*       */       
/*  4077 */       if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/*  4078 */         rxpbBuf = null;
/*       */       } else {
/*       */         
/*  4081 */         if (this.inheritRRSContext) {
/*  4082 */           rxpb = getInheritedRxpb(tls, jTls, localhconn);
/*       */         } else {
/*       */           
/*  4085 */           rxpb = localhconn.getRxpb();
/*       */         } 
/*  4087 */         int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/*  4088 */         if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/*  4089 */           tls.rxpbBuf = new byte[rxpbLen];
/*       */         }
/*  4091 */         rxpbBuf = tls.rxpbBuf;
/*  4092 */         rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/*  4097 */       if (pObjDesc == null) {
/*  4098 */         descBuf = null;
/*       */       } else {
/*       */         
/*  4101 */         int odLen = pObjDesc.getRequiredBufferSize(ptrSize, nativeCp);
/*  4102 */         if (tls.descBuf == null || tls.descBuf.length < odLen) {
/*  4103 */           tls.descBuf = new byte[odLen];
/*       */         }
/*  4105 */         descBuf = tls.descBuf;
/*  4106 */         pObjDesc.writeToBuffer(descBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/*  4111 */       final Object nativeMethodObjectSyncLock = new Object();
/*  4112 */       final BooleanWrapper booleanWrapper = new BooleanWrapper();
/*  4113 */       booleanWrapper.waiting = true;
/*       */ 
/*       */ 
/*       */       
/*  4117 */       if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hconn)) {
/*  4118 */         localhconn.syncExec(new JmqiRunnable(this.env)
/*       */             {
/*       */               public void run()
/*       */               {
/*  4122 */                 if (Trace.isOn) {
/*  4123 */                   Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                 }
/*       */ 
/*       */                 
/*       */                 try {
/*  4128 */                   localhconn.enterCall();
/*       */                   
/*  4130 */                   boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*       */                   
/*  4132 */                   synchronized (nativeMethodObjectSyncLock)
/*       */                   {
/*  4134 */                     Native.MQOPEN(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, descBuf, Options, localhobj, pCompCode, pReason);
/*       */                     
/*  4136 */                     booleanWrapper.waiting = false;
/*  4137 */                     nativeMethodObjectSyncLock.notify();
/*       */ 
/*       */                     
/*  4140 */                     if (Trace.isOn) {
/*  4141 */                       Trace.data(this, "MQOPEN(Hconn,MQOD,final int,Phobj,final Pint,final Pint)", "Hconn = '" + hconn.getConnectionIdAsString() + "', Hobj = '" + localhobj.getIntegerHandle() + "'");
/*       */                     }
/*       */                   }
/*       */                 
/*       */                 }
/*  4146 */                 catch (JmqiException e) {
/*  4147 */                   if (Trace.isOn) {
/*  4148 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                   }
/*       */                   
/*  4151 */                   jTls.lastException = e;
/*  4152 */                   pCompCode.x = e.getCompCode();
/*  4153 */                   pReason.x = e.getReason();
/*       */                 }
/*  4155 */                 catch (UnsatisfiedLinkError e) {
/*  4156 */                   if (Trace.isOn) {
/*  4157 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                   }
/*  4159 */                   pCompCode.x = 2;
/*  4160 */                   pReason.x = 2298;
/*       */                 } finally {
/*       */                   
/*  4163 */                   if (Trace.isOn) {
/*  4164 */                     Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                   
/*       */                   try {
/*  4168 */                     localhconn.leaveCall();
/*       */                   }
/*  4170 */                   catch (JmqiException e) {
/*  4171 */                     if (Trace.isOn) {
/*  4172 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                     }
/*  4174 */                     jTls.lastException = e;
/*  4175 */                     pCompCode.x = e.getCompCode();
/*  4176 */                     pReason.x = e.getReason();
/*       */                   } 
/*       */                 } 
/*  4179 */                 if (Trace.isOn) {
/*  4180 */                   Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                 
/*       */                 }
/*       */               }
/*       */             });
/*       */       }
/*       */       else {
/*       */         
/*  4188 */         if (this.adapterIsRRS) {
/*  4189 */           localhconn.enterCall();
/*  4190 */           lockedLocalhconn = localhconn;
/*       */         } 
/*  4192 */         boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  4193 */         Native.MQOPEN(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, descBuf, Options, localhobj, pCompCode, pReason);
/*  4194 */         booleanWrapper.waiting = false;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  4205 */       synchronized (nativeMethodObjectSyncLock)
/*       */       {
/*       */         
/*  4208 */         while (booleanWrapper.waiting) {
/*       */           try {
/*  4210 */             nativeMethodObjectSyncLock.wait();
/*  4211 */           } catch (InterruptedException ie) {
/*       */             
/*  4213 */             if (Trace.isOn) {
/*  4214 */               Trace.catchBlock("com.ibm.mq.jmqi.local.LocalMQ", "MQOPEN(Hconn,MQOD,final int,Phobj,final Pint,final Pint)", ie);
/*       */             }
/*       */           } 
/*       */         } 
/*       */ 
/*       */         
/*  4220 */         if (Trace.isOn) {
/*  4221 */           Trace.data(this, "MQOPEN(Hconn,MQOD,final int,Phobj,final Pint,final Pint)", "Hconn = '" + hconn.getConnectionIdAsString() + "', Hobj = '" + localhobj.getIntegerHandle() + "'");
/*       */         }
/*       */ 
/*       */ 
/*       */         
/*  4226 */         localhobj.setHobj(phobj);
/*  4227 */         localhobj.setHconn(localhconn);
/*  4228 */         if (pObjDesc != null) {
/*  4229 */           pObjDesc.readFromBuffer(descBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         }
/*       */         
/*  4232 */         if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES)
/*       */         {
/*  4234 */           localhobj.setCtxToken(rxpb.getCtxTkn());
/*       */         }
/*       */       }
/*       */     
/*  4238 */     } catch (JmqiException e) {
/*  4239 */       if (Trace.isOn) {
/*  4240 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQOPEN(Hconn,MQOD,final int,Phobj,final Pint,final Pint)", (Throwable)e, 1);
/*       */       }
/*       */       
/*  4243 */       jTls.lastException = e;
/*  4244 */       pCompCode.x = e.getCompCode();
/*  4245 */       pReason.x = e.getReason();
/*       */     }
/*  4247 */     catch (UnsatisfiedLinkError e) {
/*  4248 */       if (Trace.isOn) {
/*  4249 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQOPEN(Hconn,MQOD,final int,Phobj,final Pint,final Pint)", e, 2);
/*       */       }
/*       */       
/*  4252 */       pCompCode.x = 2;
/*  4253 */       pReason.x = 2298;
/*       */     }
/*  4255 */     catch (Throwable e) {
/*  4256 */       if (Trace.isOn) {
/*  4257 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQOPEN(Hconn,MQOD,final int,Phobj,final Pint,final Pint)", e, 3);
/*       */       }
/*       */       
/*  4260 */       castUnexpectedException("MQOPEN(Hconn,MQOD,final int,Phobj,final Pint,final Pint)", 1, e);
/*       */     } finally {
/*       */       
/*  4263 */       if (Trace.isOn) {
/*  4264 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQOPEN(Hconn,MQOD,final int,Phobj,final Pint,final Pint)");
/*       */       }
/*       */       
/*  4267 */       if (lockedLocalhconn != null) {
/*       */         
/*       */         try {
/*  4270 */           lockedLocalhconn.leaveCall();
/*       */         }
/*  4272 */         catch (JmqiException e) {
/*  4273 */           if (Trace.isOn) {
/*  4274 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQOPEN(Hconn,MQOD,final int,Phobj,final Pint,final Pint)", (Throwable)e, 4);
/*       */           }
/*       */           
/*  4277 */           jTls.lastException = e;
/*  4278 */           pCompCode.x = e.getCompCode();
/*  4279 */           pReason.x = e.getReason();
/*       */         } 
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  4288 */       if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES && this.inheritRRSContext && 
/*  4289 */         pReason.x == 2009) {
/*  4290 */         clearInheritedRXPB(jTls);
/*       */       }
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  4297 */     if (Trace.isOn) {
/*  4298 */       Trace.data(this, "MQOPEN(Hconn,MQOD,final int,Phobj,final Pint,final Pint)", "__________");
/*  4299 */       Trace.data(this, "MQOPEN(Hconn,MQOD,final int,Phobj,final Pint,final Pint)", "MQOPEN <<");
/*  4300 */       Trace.data(this, "MQOPEN(Hconn,MQOD,final int,Phobj,final Pint,final Pint)", "Hconn", hconn);
/*  4301 */       Trace.data(this, "MQOPEN(Hconn,MQOD,final int,Phobj,final Pint,final Pint)", "Objdesc", pObjDesc);
/*  4302 */       Trace.data(this, "MQOPEN(Hconn,MQOD,final int,Phobj,final Pint,final Pint)", "Options", "0x" + Integer.toHexString(Options));
/*  4303 */       Trace.data(this, "MQOPEN(Hconn,MQOD,final int,Phobj,final Pint,final Pint)", "Hobj", phobj);
/*  4304 */       Trace.data(this, "MQOPEN(Hconn,MQOD,final int,Phobj,final Pint,final Pint)", "CompCode", pCompCode);
/*  4305 */       Trace.data(this, "MQOPEN(Hconn,MQOD,final int,Phobj,final Pint,final Pint)", "Reason", pReason);
/*  4306 */       Trace.data(this, "MQOPEN(Hconn,MQOD,final int,Phobj,final Pint,final Pint)", "__________");
/*       */     } 
/*  4308 */     if (Trace.isOn) {
/*  4309 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQOPEN(Hconn,MQOD,final int,Phobj,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQPUT(Hconn hconn, Hobj hobj, MQMD pMsgDesc, MQPMO pPutMsgOpts, final int bufferLength, ByteBuffer pBuffer, final Pint pCompCode, final Pint pReason) {
/*  4337 */     if (Trace.isOn) {
/*  4338 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQPUT(Hconn,Hobj,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", new Object[] { hconn, hobj, pMsgDesc, pPutMsgOpts, 
/*       */             
/*  4340 */             Integer.valueOf(bufferLength), pBuffer, pCompCode, pReason });
/*       */     }
/*       */     
/*  4343 */     String fid = "MQPUT(Hconn,Hobj,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)";
/*       */ 
/*       */     
/*  4346 */     if (Trace.isOn) {
/*  4347 */       Trace.data(this, "MQPUT(Hconn,Hobj,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "__________");
/*  4348 */       Trace.data(this, "MQPUT(Hconn,Hobj,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "MQPUT >>");
/*  4349 */       Trace.data(this, "MQPUT(Hconn,Hobj,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "Hconn", hconn);
/*  4350 */       Trace.data(this, "MQPUT(Hconn,Hobj,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "Hobj", hobj);
/*  4351 */       Trace.data(this, "MQPUT(Hconn,Hobj,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "Msgdesc", pMsgDesc);
/*  4352 */       Trace.data(this, "MQPUT(Hconn,Hobj,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "Putmsgopts", pPutMsgOpts);
/*  4353 */       Trace.data(this, "MQPUT(Hconn,Hobj,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "BufferLength" + bufferLength);
/*  4354 */       if (pBuffer == null) {
/*  4355 */         Trace.data(this, "MQPUT(Hconn,Hobj,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "buffer is (null)");
/*       */       } else {
/*       */         
/*  4358 */         JmqiTools.traceApiBuffer(this, "MQPUT(Hconn,Hobj,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", pBuffer, pBuffer.limit());
/*       */       } 
/*  4360 */       Trace.data(this, "MQPUT(Hconn,Hobj,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "CompCode", pCompCode);
/*  4361 */       Trace.data(this, "MQPUT(Hconn,Hobj,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "Reason", pReason);
/*  4362 */       Trace.data(this, "MQPUT(Hconn,Hobj,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "__________");
/*       */     } 
/*  4364 */     pCompCode.x = 0;
/*  4365 */     pReason.x = 0;
/*       */ 
/*       */     
/*  4368 */     if (bufferLength > 0) {
/*  4369 */       if (pBuffer == null) {
/*  4370 */         Trace.data(this, "MQPUT(Hconn,Hobj,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "BufferLength: " + bufferLength + ", pBuffer is (null)", null);
/*  4371 */         pCompCode.x = 2;
/*  4372 */         pReason.x = 2005;
/*       */       }
/*  4374 */       else if (bufferLength > pBuffer.limit()) {
/*  4375 */         Trace.data(this, "MQPUT(Hconn,Hobj,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "BufferLength:" + bufferLength + ", pBuffer.limit():" + pBuffer.limit(), null);
/*  4376 */         pCompCode.x = 2;
/*  4377 */         pReason.x = 2005;
/*       */       }
/*       */     
/*  4380 */     } else if (bufferLength < 0) {
/*  4381 */       Trace.data(this, "MQPUT(Hconn,Hobj,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "BufferLength is negative: " + bufferLength, null);
/*  4382 */       pCompCode.x = 2;
/*  4383 */       pReason.x = 2005;
/*       */     } 
/*  4385 */     if (pReason.x == 0) {
/*       */       
/*  4387 */       LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  4388 */       final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/*  4389 */       LocalHconn lockedLocalhconn = null; try {
/*       */         final byte[] rxpbBuf, mqmdBuf, optBuf, pBufferArray;
/*  4391 */         final LocalHconn localhconn = getLocalHconn(hconn);
/*  4392 */         final LocalHobj localhobj = LocalHobj.getLocalHobj(this.env, hobj);
/*  4393 */         final byte[] qmName = localhconn.getQMNameBytes();
/*       */         
/*  4395 */         RXPB rxpb = null;
/*       */         
/*  4397 */         if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/*  4398 */           rxpbBuf = null;
/*       */         } else {
/*       */           
/*  4401 */           if (this.inheritRRSContext) {
/*  4402 */             rxpb = getInheritedRxpb(tls, jTls, localhconn);
/*  4403 */             if (!Arrays.equals(localhobj.getCtxToken(), rxpb.getCtxTkn()))
/*       */             {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */               
/*  4411 */               JmqiException e = new JmqiException(this.env, -1, null, 2, 2298, null);
/*       */               
/*  4413 */               if (Trace.isOn) {
/*  4414 */                 Trace.throwing(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQPUT(Hconn,Hobj,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", (Throwable)e);
/*       */               }
/*       */               
/*  4417 */               throw e;
/*       */             }
/*       */           
/*       */           } else {
/*       */             
/*  4422 */             rxpb = localhconn.getRxpb();
/*       */           } 
/*  4424 */           int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/*  4425 */           if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/*  4426 */             tls.rxpbBuf = new byte[rxpbLen];
/*       */           }
/*  4428 */           rxpbBuf = tls.rxpbBuf;
/*  4429 */           rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         } 
/*       */ 
/*       */         
/*  4433 */         if (pMsgDesc == null) {
/*  4434 */           mqmdBuf = null;
/*       */         } else {
/*       */           
/*  4437 */           int mqmdLen = pMsgDesc.getRequiredBufferSize(ptrSize, nativeCp);
/*  4438 */           if (tls.mqmdBuf == null || tls.mqmdBuf.length < mqmdLen) {
/*  4439 */             tls.mqmdBuf = new byte[mqmdLen];
/*       */           }
/*  4441 */           mqmdBuf = tls.mqmdBuf;
/*  4442 */           pMsgDesc.writeToBuffer(mqmdBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         } 
/*       */ 
/*       */         
/*  4446 */         if (pPutMsgOpts == null) {
/*  4447 */           optBuf = null;
/*       */         } else {
/*       */           
/*  4450 */           int pmoLen = pPutMsgOpts.getRequiredBufferSize(ptrSize, nativeCp);
/*  4451 */           if (tls.optBuf == null || tls.optBuf.length < pmoLen) {
/*  4452 */             tls.optBuf = new byte[pmoLen];
/*       */           }
/*  4454 */           optBuf = tls.optBuf;
/*  4455 */           pPutMsgOpts.writeToBuffer(optBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         } 
/*       */ 
/*       */ 
/*       */         
/*  4460 */         if (pBuffer == null) {
/*  4461 */           pBufferArray = null;
/*       */         } else {
/*       */           
/*  4464 */           pBufferArray = pBuffer.array();
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  4470 */         if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hconn)) {
/*  4471 */           localhconn.syncExec(new JmqiRunnable(this.env)
/*       */               {
/*       */                 public void run()
/*       */                 {
/*  4475 */                   if (Trace.isOn) {
/*  4476 */                     Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                   }
/*       */ 
/*       */                   
/*       */                   try {
/*  4481 */                     localhconn.enterCall();
/*       */ 
/*       */                     
/*  4484 */                     boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  4485 */                     Native.MQPUT(isClassTraced, localhconn
/*  4486 */                         .getValue(), qmName, rxpbBuf, localhobj
/*       */ 
/*       */                         
/*  4489 */                         .getValue(), mqmdBuf, optBuf, bufferLength, pBufferArray, pCompCode, pReason);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */                   
/*       */                   }
/*  4497 */                   catch (JmqiException e) {
/*  4498 */                     if (Trace.isOn) {
/*  4499 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                     }
/*       */                     
/*  4502 */                     jTls.lastException = e;
/*  4503 */                     pCompCode.x = e.getCompCode();
/*  4504 */                     pReason.x = e.getReason();
/*       */                   }
/*  4506 */                   catch (UnsatisfiedLinkError e) {
/*  4507 */                     if (Trace.isOn) {
/*  4508 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                     }
/*  4510 */                     pCompCode.x = 2;
/*  4511 */                     pReason.x = 2298;
/*       */                   } finally {
/*       */                     
/*  4514 */                     if (Trace.isOn) {
/*  4515 */                       Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                     }
/*       */                     
/*       */                     try {
/*  4519 */                       localhconn.leaveCall();
/*       */                     }
/*  4521 */                     catch (JmqiException e) {
/*  4522 */                       if (Trace.isOn) {
/*  4523 */                         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                       }
/*  4525 */                       jTls.lastException = e;
/*  4526 */                       pCompCode.x = e.getCompCode();
/*  4527 */                       pReason.x = e.getReason();
/*       */                     } 
/*       */                   } 
/*  4530 */                   if (Trace.isOn) {
/*  4531 */                     Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                 }
/*       */               });
/*       */         }
/*       */         else {
/*       */           
/*  4538 */           if (this.adapterIsRRS) {
/*  4539 */             localhconn.enterCall();
/*  4540 */             lockedLocalhconn = localhconn;
/*       */           } 
/*  4542 */           boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  4543 */           Native.MQPUT(isClassTraced, localhconn
/*  4544 */               .getValue(), qmName, rxpbBuf, localhobj
/*       */ 
/*       */               
/*  4547 */               .getValue(), mqmdBuf, optBuf, bufferLength, pBufferArray, pCompCode, pReason);
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  4557 */         if (pMsgDesc != null) {
/*  4558 */           pMsgDesc.readFromBuffer(tls.mqmdBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         }
/*  4560 */         if (pPutMsgOpts != null) {
/*  4561 */           pPutMsgOpts.readFromBuffer(tls.optBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         }
/*       */       }
/*  4564 */       catch (JmqiException e) {
/*  4565 */         if (Trace.isOn) {
/*  4566 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQPUT(Hconn,Hobj,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", (Throwable)e, 1);
/*       */         }
/*       */         
/*  4569 */         jTls.lastException = e;
/*  4570 */         pCompCode.x = e.getCompCode();
/*  4571 */         pReason.x = e.getReason();
/*       */       }
/*  4573 */       catch (UnsatisfiedLinkError e) {
/*  4574 */         if (Trace.isOn) {
/*  4575 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQPUT(Hconn,Hobj,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", e, 2);
/*       */         }
/*       */         
/*  4578 */         pCompCode.x = 2;
/*  4579 */         pReason.x = 2298;
/*       */       }
/*  4581 */       catch (Throwable e) {
/*  4582 */         if (Trace.isOn) {
/*  4583 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQPUT(Hconn,Hobj,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", e, 3);
/*       */         }
/*       */         
/*  4586 */         castUnexpectedException("MQPUT(Hconn,Hobj,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", 1, e);
/*       */       } finally {
/*       */         
/*  4589 */         if (Trace.isOn) {
/*  4590 */           Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQPUT(Hconn,Hobj,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)");
/*       */         }
/*       */         
/*  4593 */         if (lockedLocalhconn != null) {
/*       */           
/*       */           try {
/*  4596 */             lockedLocalhconn.leaveCall();
/*       */           }
/*  4598 */           catch (JmqiException e) {
/*  4599 */             if (Trace.isOn) {
/*  4600 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQPUT(Hconn,Hobj,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", (Throwable)e, 4);
/*       */             }
/*       */             
/*  4603 */             jTls.lastException = e;
/*  4604 */             pCompCode.x = e.getCompCode();
/*  4605 */             pReason.x = e.getReason();
/*       */           } 
/*       */         }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  4617 */         if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES && this.inheritRRSContext && 
/*  4618 */           pReason.x == 2009) {
/*  4619 */           clearInheritedRXPB(jTls);
/*       */         }
/*       */       } 
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  4627 */     if (Trace.isOn) {
/*  4628 */       Trace.data(this, "MQPUT(Hconn,Hobj,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "__________");
/*  4629 */       Trace.data(this, "MQPUT(Hconn,Hobj,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "MQPUT <<");
/*  4630 */       Trace.data(this, "MQPUT(Hconn,Hobj,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "Hconn", hconn);
/*  4631 */       Trace.data(this, "MQPUT(Hconn,Hobj,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "Hobj", hobj);
/*  4632 */       Trace.data(this, "MQPUT(Hconn,Hobj,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "Msgdesc", pMsgDesc);
/*  4633 */       Trace.data(this, "MQPUT(Hconn,Hobj,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "Putmsgopts", pPutMsgOpts);
/*  4634 */       Trace.data(this, "MQPUT(Hconn,Hobj,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "Bufferlength", "input");
/*  4635 */       Trace.data(this, "MQPUT(Hconn,Hobj,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "CompCode", pCompCode);
/*  4636 */       Trace.data(this, "MQPUT(Hconn,Hobj,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "Reason", pReason);
/*  4637 */       Trace.data(this, "MQPUT(Hconn,Hobj,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "__________");
/*       */     } 
/*  4639 */     if (Trace.isOn) {
/*  4640 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQPUT(Hconn,Hobj,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQPUT1(Hconn hconn, MQOD pObjDesc, MQMD pMsgDesc, MQPMO pPutMsgOpts, final int bufferLength, ByteBuffer pBuffer, final Pint pCompCode, final Pint pReason) {
/*  4668 */     if (Trace.isOn) {
/*  4669 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQPUT1(Hconn,MQOD,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", new Object[] { hconn, pObjDesc, pMsgDesc, pPutMsgOpts, 
/*       */             
/*  4671 */             Integer.valueOf(bufferLength), pBuffer, pCompCode, pReason });
/*       */     }
/*       */     
/*  4674 */     String fid = "MQPUT1(Hconn,MQOD,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)";
/*       */ 
/*       */     
/*  4677 */     if (Trace.isOn) {
/*  4678 */       Trace.data(this, "MQPUT1(Hconn,MQOD,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "__________");
/*  4679 */       Trace.data(this, "MQPUT1(Hconn,MQOD,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "MQPUT1 >>");
/*  4680 */       Trace.data(this, "MQPUT1(Hconn,MQOD,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "Hconn", hconn);
/*  4681 */       Trace.data(this, "MQPUT1(Hconn,MQOD,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "Objdesc", pObjDesc);
/*  4682 */       Trace.data(this, "MQPUT1(Hconn,MQOD,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "Msgdesc", pMsgDesc);
/*  4683 */       Trace.data(this, "MQPUT1(Hconn,MQOD,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "Putmsgopts", pPutMsgOpts);
/*  4684 */       Trace.data(this, "MQPUT1(Hconn,MQOD,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "BufferLength" + bufferLength);
/*  4685 */       if (pBuffer == null) {
/*  4686 */         Trace.data(this, "MQPUT1(Hconn,MQOD,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "buffer is (null)");
/*       */       } else {
/*       */         
/*  4689 */         JmqiTools.traceApiBuffer(this, "MQPUT1(Hconn,MQOD,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", pBuffer, pBuffer.limit());
/*       */       } 
/*  4691 */       Trace.data(this, "MQPUT1(Hconn,MQOD,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "CompCode", pCompCode);
/*  4692 */       Trace.data(this, "MQPUT1(Hconn,MQOD,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "Reason", pReason);
/*  4693 */       Trace.data(this, "MQPUT1(Hconn,MQOD,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "__________");
/*       */     } 
/*  4695 */     pCompCode.x = 0;
/*  4696 */     pReason.x = 0;
/*       */ 
/*       */     
/*  4699 */     if (bufferLength > 0) {
/*  4700 */       if (pBuffer == null) {
/*  4701 */         Trace.data(this, "MQPUT1(Hconn,MQOD,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "BufferLength: " + bufferLength + ", pBuffer is (null)", null);
/*  4702 */         pCompCode.x = 2;
/*  4703 */         pReason.x = 2005;
/*       */       }
/*  4705 */       else if (bufferLength > pBuffer.limit()) {
/*  4706 */         Trace.data(this, "MQPUT1(Hconn,MQOD,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "BufferLength:" + bufferLength + ", pBuffer.limit():" + pBuffer.limit(), null);
/*  4707 */         pCompCode.x = 2;
/*  4708 */         pReason.x = 2005;
/*       */       }
/*       */     
/*  4711 */     } else if (bufferLength < 0) {
/*  4712 */       Trace.data(this, "MQPUT1(Hconn,MQOD,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "BufferLength is negative: " + bufferLength, null);
/*  4713 */       pCompCode.x = 2;
/*  4714 */       pReason.x = 2005;
/*       */     } 
/*  4716 */     if (pReason.x == 0) {
/*       */       
/*  4718 */       LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  4719 */       final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/*  4720 */       LocalHconn lockedLocalhconn = null; try {
/*       */         final byte[] rxpbBuf, descBuf, mqmdBuf, optBuf, pBufferArray;
/*  4722 */         final LocalHconn localhconn = getLocalHconn(hconn);
/*  4723 */         final byte[] qmName = localhconn.getQMNameBytes();
/*       */         
/*  4725 */         RXPB rxpb = null;
/*       */         
/*  4727 */         if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/*  4728 */           rxpbBuf = null;
/*       */         } else {
/*       */           
/*  4731 */           if (this.inheritRRSContext) {
/*  4732 */             rxpb = getInheritedRxpb(tls, jTls, localhconn);
/*       */           } else {
/*       */             
/*  4735 */             rxpb = localhconn.getRxpb();
/*       */           } 
/*  4737 */           int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/*  4738 */           if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/*  4739 */             tls.rxpbBuf = new byte[rxpbLen];
/*       */           }
/*  4741 */           rxpbBuf = tls.rxpbBuf;
/*  4742 */           rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         } 
/*       */ 
/*       */         
/*  4746 */         if (pObjDesc == null) {
/*  4747 */           descBuf = null;
/*       */         } else {
/*       */           
/*  4750 */           int odLen = pObjDesc.getRequiredBufferSize(ptrSize, nativeCp);
/*  4751 */           if (tls.descBuf == null || tls.descBuf.length < odLen) {
/*  4752 */             tls.descBuf = new byte[odLen];
/*       */           }
/*  4754 */           descBuf = tls.descBuf;
/*  4755 */           pObjDesc.writeToBuffer(descBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         } 
/*       */ 
/*       */         
/*  4759 */         if (pMsgDesc == null) {
/*  4760 */           mqmdBuf = null;
/*       */         } else {
/*       */           
/*  4763 */           int mqmdLen = pMsgDesc.getRequiredBufferSize(ptrSize, nativeCp);
/*  4764 */           if (tls.mqmdBuf == null || tls.mqmdBuf.length < mqmdLen) {
/*  4765 */             tls.mqmdBuf = new byte[mqmdLen];
/*       */           }
/*  4767 */           mqmdBuf = tls.mqmdBuf;
/*  4768 */           pMsgDesc.writeToBuffer(mqmdBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         } 
/*       */ 
/*       */         
/*  4772 */         if (pPutMsgOpts == null) {
/*  4773 */           optBuf = null;
/*       */         } else {
/*       */           
/*  4776 */           int pmoLen = pPutMsgOpts.getRequiredBufferSize(ptrSize, nativeCp);
/*  4777 */           if (tls.optBuf == null || tls.optBuf.length < pmoLen) {
/*  4778 */             tls.optBuf = new byte[pmoLen];
/*       */           }
/*  4780 */           optBuf = tls.optBuf;
/*  4781 */           pPutMsgOpts.writeToBuffer(optBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         } 
/*       */ 
/*       */ 
/*       */         
/*  4786 */         if (pBuffer == null) {
/*  4787 */           pBufferArray = null;
/*       */         } else {
/*       */           
/*  4790 */           pBufferArray = pBuffer.array();
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  4796 */         if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hconn)) {
/*  4797 */           localhconn.syncExec(new JmqiRunnable(this.env)
/*       */               {
/*       */                 public void run()
/*       */                 {
/*  4801 */                   if (Trace.isOn) {
/*  4802 */                     Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                   }
/*       */ 
/*       */                   
/*       */                   try {
/*  4807 */                     localhconn.enterCall();
/*       */ 
/*       */                     
/*  4810 */                     boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  4811 */                     Native.MQPUT1(isClassTraced, localhconn
/*  4812 */                         .getValue(), qmName, rxpbBuf, descBuf, mqmdBuf, optBuf, bufferLength, pBufferArray, pCompCode, pReason);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */                   
/*       */                   }
/*  4823 */                   catch (JmqiException e) {
/*  4824 */                     if (Trace.isOn) {
/*  4825 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                     }
/*       */                     
/*  4828 */                     jTls.lastException = e;
/*  4829 */                     pCompCode.x = e.getCompCode();
/*  4830 */                     pReason.x = e.getReason();
/*       */                   }
/*  4832 */                   catch (UnsatisfiedLinkError e) {
/*  4833 */                     if (Trace.isOn) {
/*  4834 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                     }
/*  4836 */                     pCompCode.x = 2;
/*  4837 */                     pReason.x = 2298;
/*       */                   } finally {
/*       */                     
/*  4840 */                     if (Trace.isOn) {
/*  4841 */                       Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                     }
/*       */                     
/*       */                     try {
/*  4845 */                       localhconn.leaveCall();
/*       */                     }
/*  4847 */                     catch (JmqiException e) {
/*  4848 */                       if (Trace.isOn) {
/*  4849 */                         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                       }
/*  4851 */                       jTls.lastException = e;
/*  4852 */                       pCompCode.x = e.getCompCode();
/*  4853 */                       pReason.x = e.getReason();
/*       */                     } 
/*       */                   } 
/*  4856 */                   if (Trace.isOn) {
/*  4857 */                     Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                 }
/*       */               });
/*       */         }
/*       */         else {
/*       */           
/*  4864 */           if (this.adapterIsRRS) {
/*  4865 */             localhconn.enterCall();
/*  4866 */             lockedLocalhconn = localhconn;
/*       */           } 
/*  4868 */           boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  4869 */           Native.MQPUT1(isClassTraced, localhconn
/*  4870 */               .getValue(), qmName, rxpbBuf, descBuf, mqmdBuf, optBuf, bufferLength, pBufferArray, pCompCode, pReason);
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  4883 */         if (pObjDesc != null) {
/*  4884 */           pObjDesc.readFromBuffer(tls.descBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         }
/*  4886 */         if (pMsgDesc != null) {
/*  4887 */           pMsgDesc.readFromBuffer(tls.mqmdBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         }
/*  4889 */         if (pPutMsgOpts != null) {
/*  4890 */           pPutMsgOpts.readFromBuffer(tls.optBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         }
/*       */       }
/*  4893 */       catch (JmqiException e) {
/*  4894 */         if (Trace.isOn) {
/*  4895 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQPUT1(Hconn,MQOD,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", (Throwable)e, 1);
/*       */         }
/*       */         
/*  4898 */         jTls.lastException = e;
/*  4899 */         pCompCode.x = e.getCompCode();
/*  4900 */         pReason.x = e.getReason();
/*       */       }
/*  4902 */       catch (UnsatisfiedLinkError e) {
/*  4903 */         if (Trace.isOn) {
/*  4904 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQPUT1(Hconn,MQOD,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", e, 2);
/*       */         }
/*       */         
/*  4907 */         pCompCode.x = 2;
/*  4908 */         pReason.x = 2298;
/*       */       }
/*  4910 */       catch (Throwable e) {
/*  4911 */         if (Trace.isOn) {
/*  4912 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQPUT1(Hconn,MQOD,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", e, 3);
/*       */         }
/*       */         
/*  4915 */         castUnexpectedException("MQPUT1(Hconn,MQOD,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", 1, e);
/*       */       } finally {
/*       */         
/*  4918 */         if (Trace.isOn) {
/*  4919 */           Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQPUT1(Hconn,MQOD,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)");
/*       */         }
/*       */         
/*  4922 */         if (lockedLocalhconn != null) {
/*       */           
/*       */           try {
/*  4925 */             lockedLocalhconn.leaveCall();
/*       */           }
/*  4927 */           catch (JmqiException e) {
/*  4928 */             if (Trace.isOn) {
/*  4929 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQPUT1(Hconn,MQOD,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", (Throwable)e, 4);
/*       */             }
/*       */             
/*  4932 */             jTls.lastException = e;
/*  4933 */             pCompCode.x = e.getCompCode();
/*  4934 */             pReason.x = e.getReason();
/*       */           } 
/*       */         }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  4946 */         if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES && this.inheritRRSContext && 
/*  4947 */           pReason.x == 2009) {
/*  4948 */           clearInheritedRXPB(jTls);
/*       */         }
/*       */       } 
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  4956 */     if (Trace.isOn) {
/*  4957 */       Trace.data(this, "MQPUT1(Hconn,MQOD,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "__________");
/*  4958 */       Trace.data(this, "MQPUT1(Hconn,MQOD,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "MQPUT1 <<");
/*  4959 */       Trace.data(this, "MQPUT1(Hconn,MQOD,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "Hconn", hconn);
/*  4960 */       Trace.data(this, "MQPUT1(Hconn,MQOD,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "Objdesc", pObjDesc);
/*  4961 */       Trace.data(this, "MQPUT1(Hconn,MQOD,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "Msgdesc", pMsgDesc);
/*  4962 */       Trace.data(this, "MQPUT1(Hconn,MQOD,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "Putmsgopts", pPutMsgOpts);
/*  4963 */       Trace.data(this, "MQPUT1(Hconn,MQOD,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "Bufferlength", "input");
/*  4964 */       Trace.data(this, "MQPUT1(Hconn,MQOD,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "CompCode", pCompCode);
/*  4965 */       Trace.data(this, "MQPUT1(Hconn,MQOD,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "Reason", pReason);
/*  4966 */       Trace.data(this, "MQPUT1(Hconn,MQOD,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)", "__________");
/*       */     } 
/*  4968 */     if (Trace.isOn) {
/*  4969 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQPUT1(Hconn,MQOD,MQMD,MQPMO,final int,ByteBuffer,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQSET(Hconn hconn, Hobj hobj, final int SelectorCount, int[] pSelectors, final int IntAttrCount, int[] pIntAttrs, final int CharAttrLength, final byte[] pCharAttrs, final Pint pCompCode, final Pint pReason) {
/*       */     final byte[] selectors, intAttrs;
/*  5001 */     if (Trace.isOn) {
/*  5002 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQSET(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", new Object[] { hconn, hobj, 
/*       */             
/*  5004 */             Integer.valueOf(SelectorCount), pSelectors, 
/*  5005 */             Integer.valueOf(IntAttrCount), pIntAttrs, Integer.valueOf(CharAttrLength), pCharAttrs, pCompCode, pReason });
/*       */     }
/*       */     
/*  5008 */     String fid = "MQSET(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)";
/*       */ 
/*       */     
/*  5011 */     if (Trace.isOn) {
/*  5012 */       Trace.data(this, "MQSET(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "__________");
/*  5013 */       Trace.data(this, "MQSET(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "MQSET >>");
/*  5014 */       Trace.data(this, "MQSET(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "Hconn", hconn);
/*  5015 */       Trace.data(this, "MQSET(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "Hobj", hobj);
/*  5016 */       Trace.data(this, "MQSET(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "Selectorcount", Integer.toString(SelectorCount));
/*  5017 */       Trace.data(this, "MQSET(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "Selectors", pSelectors);
/*  5018 */       Trace.data(this, "MQSET(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "IntAttrcount", Integer.toString(IntAttrCount));
/*  5019 */       Trace.data(this, "MQSET(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "IntAttrs", pIntAttrs);
/*  5020 */       Trace.data(this, "MQSET(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "Charattrlength", Integer.toString(CharAttrLength));
/*  5021 */       Trace.data(this, "MQSET(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "Charattrs", pCharAttrs);
/*  5022 */       Trace.data(this, "MQSET(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "CompCode", pCompCode);
/*  5023 */       Trace.data(this, "MQSET(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "Reason", pReason);
/*  5024 */       Trace.data(this, "MQSET(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "__________");
/*       */     } 
/*  5026 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*       */ 
/*       */     
/*  5029 */     if (pSelectors == null) {
/*  5030 */       selectors = null;
/*       */     } else {
/*       */       
/*  5033 */       selectors = new byte[SelectorCount * 4];
/*  5034 */       for (int i = 0; i < SelectorCount; i++) {
/*  5035 */         dc.writeI32(pSelectors[i], selectors, i * 4, swap);
/*       */       }
/*       */     } 
/*       */ 
/*       */     
/*  5040 */     if (pIntAttrs == null) {
/*  5041 */       intAttrs = null;
/*       */     } else {
/*       */       
/*  5044 */       intAttrs = new byte[IntAttrCount * 4];
/*  5045 */       for (int i = 0; i < IntAttrCount; i++) {
/*  5046 */         dc.writeI32(pIntAttrs[i], intAttrs, i * 4, swap);
/*       */       }
/*       */     } 
/*       */     
/*  5050 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  5051 */     final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/*  5052 */     LocalHconn lockedLocalhconn = null; try {
/*       */       final byte[] rxpbBuf;
/*  5054 */       final LocalHconn localhconn = getLocalHconn(hconn);
/*  5055 */       final LocalHobj localhobj = LocalHobj.getLocalHobj(this.env, hobj);
/*  5056 */       final byte[] qmName = localhconn.getQMNameBytes();
/*       */       
/*  5058 */       RXPB rxpb = null;
/*       */       
/*  5060 */       if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/*  5061 */         rxpbBuf = null;
/*       */       } else {
/*       */         
/*  5064 */         if (this.inheritRRSContext) {
/*  5065 */           rxpb = getInheritedRxpb(tls, jTls, localhconn);
/*  5066 */           if (!Arrays.equals(localhobj.getCtxToken(), rxpb.getCtxTkn()))
/*       */           {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */             
/*  5073 */             JmqiException e = new JmqiException(this.env, -1, null, 2, 2298, null);
/*       */             
/*  5075 */             if (Trace.isOn) {
/*  5076 */               Trace.throwing(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQSET(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", (Throwable)e);
/*       */             }
/*       */ 
/*       */             
/*  5080 */             throw e;
/*       */           }
/*       */         
/*       */         } else {
/*       */           
/*  5085 */           rxpb = localhconn.getRxpb();
/*       */         } 
/*  5087 */         int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/*  5088 */         if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/*  5089 */           tls.rxpbBuf = new byte[rxpbLen];
/*       */         }
/*  5091 */         rxpbBuf = tls.rxpbBuf;
/*  5092 */         rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/*  5097 */       if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hconn)) {
/*  5098 */         localhconn.syncExec(new JmqiRunnable(this.env)
/*       */             {
/*       */               public void run()
/*       */               {
/*  5102 */                 if (Trace.isOn) {
/*  5103 */                   Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                 }
/*       */ 
/*       */                 
/*       */                 try {
/*  5108 */                   localhconn.enterCall();
/*       */                   
/*  5110 */                   boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  5111 */                   Native.MQSET(isClassTraced, localhconn
/*  5112 */                       .getValue(), qmName, rxpbBuf, localhobj
/*       */ 
/*       */                       
/*  5115 */                       .getValue(), SelectorCount, selectors, IntAttrCount, intAttrs, CharAttrLength, pCharAttrs, pCompCode, pReason);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */                 
/*       */                 }
/*  5125 */                 catch (JmqiException e) {
/*  5126 */                   if (Trace.isOn) {
/*  5127 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                   }
/*       */                   
/*  5130 */                   jTls.lastException = e;
/*  5131 */                   pCompCode.x = e.getCompCode();
/*  5132 */                   pReason.x = e.getReason();
/*       */                 }
/*  5134 */                 catch (UnsatisfiedLinkError e) {
/*  5135 */                   if (Trace.isOn) {
/*  5136 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                   }
/*  5138 */                   pCompCode.x = 2;
/*  5139 */                   pReason.x = 2298;
/*       */                 } finally {
/*       */                   
/*  5142 */                   if (Trace.isOn) {
/*  5143 */                     Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                   
/*       */                   try {
/*  5147 */                     localhconn.leaveCall();
/*       */                   }
/*  5149 */                   catch (JmqiException e) {
/*  5150 */                     if (Trace.isOn) {
/*  5151 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                     }
/*  5153 */                     jTls.lastException = e;
/*  5154 */                     pCompCode.x = e.getCompCode();
/*  5155 */                     pReason.x = e.getReason();
/*       */                   } 
/*       */                 } 
/*  5158 */                 if (Trace.isOn) {
/*  5159 */                   Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                 
/*       */                 }
/*       */               }
/*       */             });
/*       */       }
/*       */       else {
/*       */         
/*  5167 */         if (this.adapterIsRRS) {
/*  5168 */           localhconn.enterCall();
/*  5169 */           lockedLocalhconn = localhconn;
/*       */         } 
/*  5171 */         boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  5172 */         Native.MQSET(isClassTraced, localhconn
/*  5173 */             .getValue(), qmName, rxpbBuf, localhobj
/*       */ 
/*       */             
/*  5176 */             .getValue(), SelectorCount, selectors, IntAttrCount, intAttrs, CharAttrLength, pCharAttrs, pCompCode, pReason);
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     }
/*  5187 */     catch (JmqiException e) {
/*  5188 */       if (Trace.isOn) {
/*  5189 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQSET(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", (Throwable)e, 1);
/*       */       }
/*       */ 
/*       */       
/*  5193 */       jTls.lastException = e;
/*  5194 */       pCompCode.x = e.getCompCode();
/*  5195 */       pReason.x = e.getReason();
/*       */     }
/*  5197 */     catch (UnsatisfiedLinkError e) {
/*  5198 */       if (Trace.isOn) {
/*  5199 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQSET(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", e, 2);
/*       */       }
/*       */ 
/*       */       
/*  5203 */       pCompCode.x = 2;
/*  5204 */       pReason.x = 2298;
/*       */     }
/*  5206 */     catch (Throwable e) {
/*  5207 */       if (Trace.isOn) {
/*  5208 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQSET(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", e, 3);
/*       */       }
/*       */ 
/*       */       
/*  5212 */       castUnexpectedException("MQSET(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", 1, e);
/*       */     } finally {
/*       */       
/*  5215 */       if (Trace.isOn) {
/*  5216 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQSET(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)");
/*       */       }
/*       */       
/*  5219 */       if (lockedLocalhconn != null) {
/*       */         
/*       */         try {
/*  5222 */           lockedLocalhconn.leaveCall();
/*       */         }
/*  5224 */         catch (JmqiException e) {
/*  5225 */           if (Trace.isOn) {
/*  5226 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQSET(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", (Throwable)e, 4);
/*       */           }
/*       */ 
/*       */           
/*  5230 */           jTls.lastException = e;
/*  5231 */           pCompCode.x = e.getCompCode();
/*  5232 */           pReason.x = e.getReason();
/*       */         } 
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  5241 */       if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES && this.inheritRRSContext && 
/*  5242 */         pReason.x == 2009) {
/*  5243 */         clearInheritedRXPB(jTls);
/*       */       }
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  5250 */     if (Trace.isOn) {
/*  5251 */       Trace.data(this, "MQSET(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "__________");
/*  5252 */       Trace.data(this, "MQSET(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "MQSET <<");
/*  5253 */       Trace.data(this, "MQSET(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "Hconn", hconn);
/*  5254 */       Trace.data(this, "MQSET(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "Hobj", hobj);
/*  5255 */       Trace.data(this, "MQSET(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "Selectorcount", Integer.toString(SelectorCount));
/*  5256 */       Trace.data(this, "MQSET(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "Selectors", selectors);
/*  5257 */       Trace.data(this, "MQSET(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "Intattrcount", Integer.toString(IntAttrCount));
/*  5258 */       Trace.data(this, "MQSET(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "Intattrs", intAttrs);
/*  5259 */       Trace.data(this, "MQSET(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "Charattrlength", Integer.toString(CharAttrLength));
/*  5260 */       Trace.data(this, "MQSET(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "Charattrs", pCharAttrs);
/*  5261 */       Trace.data(this, "MQSET(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "CompCode", pCompCode);
/*  5262 */       Trace.data(this, "MQSET(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "Reason", pReason);
/*  5263 */       Trace.data(this, "MQSET(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)", "__________");
/*       */     } 
/*  5265 */     if (Trace.isOn) {
/*  5266 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQSET(Hconn,Hobj,final int,int [ ],final int,int [ ],final int,final byte [ ],final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQSTAT(Hconn hconn, final int type, MQSTS pStatus, final Pint pCompCode, final Pint pReason) {
/*  5283 */     if (Trace.isOn) {
/*  5284 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQSTAT(Hconn,final int,MQSTS,final Pint,final Pint)", new Object[] { hconn, 
/*       */             
/*  5286 */             Integer.valueOf(type), pStatus, pCompCode, pReason });
/*       */     }
/*  5288 */     String fid = "MQSTAT(Hconn,final int,MQSTS,final Pint,final Pint)";
/*       */ 
/*       */     
/*  5291 */     if (Trace.isOn) {
/*  5292 */       Trace.data(this, "MQSTAT(Hconn,final int,MQSTS,final Pint,final Pint)", "__________");
/*  5293 */       Trace.data(this, "MQSTAT(Hconn,final int,MQSTS,final Pint,final Pint)", "MQSTAT >>");
/*  5294 */       Trace.data(this, "MQSTAT(Hconn,final int,MQSTS,final Pint,final Pint)", "Hconn", hconn);
/*  5295 */       Trace.data(this, "MQSTAT(Hconn,final int,MQSTS,final Pint,final Pint)", "Type", "0x" + Integer.toHexString(type));
/*  5296 */       Trace.data(this, "MQSTAT(Hconn,final int,MQSTS,final Pint,final Pint)", "Stat", pStatus);
/*  5297 */       Trace.data(this, "MQSTAT(Hconn,final int,MQSTS,final Pint,final Pint)", "CompCode", pCompCode);
/*  5298 */       Trace.data(this, "MQSTAT(Hconn,final int,MQSTS,final Pint,final Pint)", "Reason", pReason);
/*  5299 */       Trace.data(this, "MQSTAT(Hconn,final int,MQSTS,final Pint,final Pint)", "__________");
/*       */     } 
/*       */     
/*  5302 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  5303 */     final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/*  5304 */     LocalHconn lockedLocalhconn = null; try {
/*       */       final byte[] rxpbBuf, optBuf;
/*  5306 */       final LocalHconn localhconn = getLocalHconn(hconn);
/*  5307 */       final byte[] qmName = localhconn.getQMNameBytes();
/*       */       
/*  5309 */       RXPB rxpb = null;
/*       */       
/*  5311 */       if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/*  5312 */         rxpbBuf = null;
/*       */       } else {
/*       */         
/*  5315 */         if (this.inheritRRSContext) {
/*  5316 */           rxpb = getInheritedRxpb(tls, jTls, localhconn);
/*       */         } else {
/*       */           
/*  5319 */           rxpb = localhconn.getRxpb();
/*       */         } 
/*  5321 */         int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/*  5322 */         if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/*  5323 */           tls.rxpbBuf = new byte[rxpbLen];
/*       */         }
/*  5325 */         rxpbBuf = tls.rxpbBuf;
/*  5326 */         rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */       
/*  5330 */       if (pStatus == null) {
/*  5331 */         optBuf = null;
/*       */       } else {
/*       */         
/*  5334 */         int stsLen = pStatus.getRequiredBufferSize(ptrSize, nativeCp);
/*  5335 */         if (tls.optBuf == null || tls.optBuf.length < stsLen) {
/*  5336 */           tls.optBuf = new byte[stsLen];
/*       */         }
/*  5338 */         optBuf = tls.optBuf;
/*  5339 */         pStatus.writeToBuffer(optBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  5345 */       if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hconn)) {
/*  5346 */         localhconn.syncExec(new JmqiRunnable(this.env)
/*       */             {
/*       */               public void run()
/*       */               {
/*  5350 */                 if (Trace.isOn) {
/*  5351 */                   Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                 }
/*       */ 
/*       */                 
/*       */                 try {
/*  5356 */                   localhconn.enterCall();
/*       */                   
/*  5358 */                   boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  5359 */                   Native.MQSTAT(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, type, optBuf, pCompCode, pReason);
/*       */                 }
/*  5361 */                 catch (JmqiException e) {
/*  5362 */                   if (Trace.isOn) {
/*  5363 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                   }
/*       */                   
/*  5366 */                   jTls.lastException = e;
/*  5367 */                   pCompCode.x = e.getCompCode();
/*  5368 */                   pReason.x = e.getReason();
/*       */                 }
/*  5370 */                 catch (UnsatisfiedLinkError e) {
/*  5371 */                   if (Trace.isOn) {
/*  5372 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                   }
/*  5374 */                   pCompCode.x = 2;
/*  5375 */                   pReason.x = 2298;
/*       */                 } finally {
/*       */                   
/*  5378 */                   if (Trace.isOn) {
/*  5379 */                     Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                   
/*       */                   try {
/*  5383 */                     localhconn.leaveCall();
/*       */                   }
/*  5385 */                   catch (JmqiException e) {
/*  5386 */                     if (Trace.isOn) {
/*  5387 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                     }
/*  5389 */                     jTls.lastException = e;
/*  5390 */                     pCompCode.x = e.getCompCode();
/*  5391 */                     pReason.x = e.getReason();
/*       */                   } 
/*       */                 } 
/*  5394 */                 if (Trace.isOn) {
/*  5395 */                   Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                 
/*       */                 }
/*       */               }
/*       */             });
/*       */       }
/*       */       else {
/*       */         
/*  5403 */         if (this.adapterIsRRS) {
/*  5404 */           localhconn.enterCall();
/*  5405 */           lockedLocalhconn = localhconn;
/*       */         } 
/*  5407 */         boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  5408 */         Native.MQSTAT(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, type, optBuf, pCompCode, pReason);
/*       */       } 
/*       */ 
/*       */       
/*  5412 */       if (pStatus != null) {
/*  5413 */         pStatus.readFromBuffer(optBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       }
/*       */     }
/*  5416 */     catch (JmqiException e) {
/*  5417 */       if (Trace.isOn) {
/*  5418 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQSTAT(Hconn,final int,MQSTS,final Pint,final Pint)", (Throwable)e, 1);
/*       */       }
/*       */       
/*  5421 */       jTls.lastException = e;
/*  5422 */       pCompCode.x = e.getCompCode();
/*  5423 */       pReason.x = e.getReason();
/*       */     }
/*  5425 */     catch (UnsatisfiedLinkError e) {
/*  5426 */       if (Trace.isOn) {
/*  5427 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQSTAT(Hconn,final int,MQSTS,final Pint,final Pint)", e, 2);
/*       */       }
/*       */       
/*  5430 */       pCompCode.x = 2;
/*  5431 */       pReason.x = 2298;
/*       */     }
/*  5433 */     catch (Throwable e) {
/*  5434 */       if (Trace.isOn) {
/*  5435 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQSTAT(Hconn,final int,MQSTS,final Pint,final Pint)", e, 3);
/*       */       }
/*       */       
/*  5438 */       castUnexpectedException("MQSTAT(Hconn,final int,MQSTS,final Pint,final Pint)", 1, e);
/*       */     } finally {
/*       */       
/*  5441 */       if (Trace.isOn) {
/*  5442 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQSTAT(Hconn,final int,MQSTS,final Pint,final Pint)");
/*       */       }
/*       */       
/*  5445 */       if (lockedLocalhconn != null) {
/*       */         
/*       */         try {
/*  5448 */           lockedLocalhconn.leaveCall();
/*       */         }
/*  5450 */         catch (JmqiException e) {
/*  5451 */           if (Trace.isOn) {
/*  5452 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQSTAT(Hconn,final int,MQSTS,final Pint,final Pint)", (Throwable)e, 4);
/*       */           }
/*       */           
/*  5455 */           jTls.lastException = e;
/*  5456 */           pCompCode.x = e.getCompCode();
/*  5457 */           pReason.x = e.getReason();
/*       */         } 
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  5466 */       if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES && this.inheritRRSContext && 
/*  5467 */         pReason.x == 2009) {
/*  5468 */         clearInheritedRXPB(jTls);
/*       */       }
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  5475 */     if (Trace.isOn) {
/*  5476 */       Trace.data(this, "MQSTAT(Hconn,final int,MQSTS,final Pint,final Pint)", "__________");
/*  5477 */       Trace.data(this, "MQSTAT(Hconn,final int,MQSTS,final Pint,final Pint)", "MQSTAT <<");
/*  5478 */       Trace.data(this, "MQSTAT(Hconn,final int,MQSTS,final Pint,final Pint)", "Hconn", hconn);
/*  5479 */       Trace.data(this, "MQSTAT(Hconn,final int,MQSTS,final Pint,final Pint)", "Type", "0x" + Integer.toHexString(type));
/*  5480 */       Trace.data(this, "MQSTAT(Hconn,final int,MQSTS,final Pint,final Pint)", "Stat", pStatus);
/*  5481 */       Trace.data(this, "MQSTAT(Hconn,final int,MQSTS,final Pint,final Pint)", "CompCode", pCompCode);
/*  5482 */       Trace.data(this, "MQSTAT(Hconn,final int,MQSTS,final Pint,final Pint)", "Reason", pReason);
/*  5483 */       Trace.data(this, "MQSTAT(Hconn,final int,MQSTS,final Pint,final Pint)", "__________");
/*       */     } 
/*  5485 */     if (Trace.isOn) {
/*  5486 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQSTAT(Hconn,final int,MQSTS,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQSUB(Hconn hconn, MQSD pSubDesc, Phobj pHobj, Phobj pHsub, final Pint pCompCode, final Pint pReason) {
/*  5504 */     if (Trace.isOn) {
/*  5505 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQSUB(Hconn,MQSD,Phobj,Phobj,final Pint,final Pint)", new Object[] { hconn, pSubDesc, pHobj, pHsub, pCompCode, pReason });
/*       */     }
/*       */     
/*  5508 */     String fid = "MQSUB(Hconn,MQSD,Phobj,Phobj,final Pint,final Pint)";
/*       */ 
/*       */     
/*  5511 */     if (Trace.isOn) {
/*  5512 */       Trace.data(this, "MQSUB(Hconn,MQSD,Phobj,Phobj,final Pint,final Pint)", "__________");
/*  5513 */       Trace.data(this, "MQSUB(Hconn,MQSD,Phobj,Phobj,final Pint,final Pint)", "MQSUB >>");
/*  5514 */       Trace.data(this, "MQSUB(Hconn,MQSD,Phobj,Phobj,final Pint,final Pint)", "Hconn", hconn);
/*  5515 */       Trace.data(this, "MQSUB(Hconn,MQSD,Phobj,Phobj,final Pint,final Pint)", "Subdesc", pSubDesc);
/*  5516 */       Trace.data(this, "MQSUB(Hconn,MQSD,Phobj,Phobj,final Pint,final Pint)", "Hobj", pHobj);
/*  5517 */       Trace.data(this, "MQSUB(Hconn,MQSD,Phobj,Phobj,final Pint,final Pint)", "Hsub", pHsub);
/*  5518 */       Trace.data(this, "MQSUB(Hconn,MQSD,Phobj,Phobj,final Pint,final Pint)", "CompCode", pCompCode);
/*  5519 */       Trace.data(this, "MQSUB(Hconn,MQSD,Phobj,Phobj,final Pint,final Pint)", "Reason", pReason);
/*  5520 */       Trace.data(this, "MQSUB(Hconn,MQSD,Phobj,Phobj,final Pint,final Pint)", "__________");
/*       */     } 
/*       */     
/*  5523 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  5524 */     final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/*  5525 */     LocalHconn lockedLocalhconn = null; try {
/*       */       final byte[] rxpbBuf, descBuf;
/*  5527 */       final LocalHobj localhobj = LocalHobj.getLocalHobj(this.env, pHobj.getHobj());
/*  5528 */       final LocalHobj localhsub = LocalHobj.getLocalHobj(this.env, pHsub.getHobj());
/*  5529 */       final LocalHconn localhconn = getLocalHconn(hconn);
/*  5530 */       final byte[] qmName = localhconn.getQMNameBytes();
/*       */       
/*  5532 */       RXPB rxpb = null;
/*       */       
/*  5534 */       if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/*  5535 */         rxpbBuf = null;
/*       */       } else {
/*       */         
/*  5538 */         if (this.inheritRRSContext) {
/*  5539 */           rxpb = getInheritedRxpb(tls, jTls, localhconn);
/*  5540 */           if (localhobj.getCtxToken() != null && !Arrays.equals(localhobj.getCtxToken(), rxpb.getCtxTkn()))
/*       */           {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */             
/*  5547 */             JmqiException e = new JmqiException(this.env, -1, null, 2, 2298, null);
/*       */             
/*  5549 */             if (Trace.isOn) {
/*  5550 */               Trace.throwing(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQSUB(Hconn,MQSD,Phobj,Phobj,final Pint,final Pint)", (Throwable)e);
/*       */             }
/*       */             
/*  5553 */             throw e;
/*       */           }
/*       */         
/*       */         } else {
/*       */           
/*  5558 */           rxpb = localhconn.getRxpb();
/*       */         } 
/*  5560 */         int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/*  5561 */         if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/*  5562 */           tls.rxpbBuf = new byte[rxpbLen];
/*       */         }
/*  5564 */         rxpbBuf = tls.rxpbBuf;
/*  5565 */         rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */       
/*  5569 */       if (pSubDesc == null) {
/*  5570 */         descBuf = null;
/*       */       } else {
/*       */         
/*  5573 */         int sdLen = pSubDesc.getRequiredBufferSize(ptrSize, nativeCp);
/*  5574 */         if (tls.descBuf == null || tls.descBuf.length < sdLen) {
/*  5575 */           tls.descBuf = new byte[sdLen];
/*       */         }
/*  5577 */         descBuf = tls.descBuf;
/*  5578 */         pSubDesc.writeToBuffer(descBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/*  5583 */       if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hconn)) {
/*  5584 */         localhconn.syncExec(new JmqiRunnable(this.env)
/*       */             {
/*       */               public void run()
/*       */               {
/*  5588 */                 if (Trace.isOn) {
/*  5589 */                   Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                 }
/*       */ 
/*       */                 
/*       */                 try {
/*  5594 */                   localhconn.enterCall();
/*       */                   
/*  5596 */                   boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  5597 */                   Native.MQSUB(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, descBuf, localhobj, localhsub, pCompCode, pReason);
/*       */                 }
/*  5599 */                 catch (JmqiException e) {
/*  5600 */                   if (Trace.isOn) {
/*  5601 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                   }
/*       */                   
/*  5604 */                   jTls.lastException = e;
/*  5605 */                   pCompCode.x = e.getCompCode();
/*  5606 */                   pReason.x = e.getReason();
/*       */                 }
/*  5608 */                 catch (UnsatisfiedLinkError e) {
/*  5609 */                   if (Trace.isOn) {
/*  5610 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                   }
/*  5612 */                   pCompCode.x = 2;
/*  5613 */                   pReason.x = 2298;
/*       */                 } finally {
/*       */                   
/*  5616 */                   if (Trace.isOn) {
/*  5617 */                     Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                   
/*       */                   try {
/*  5621 */                     localhconn.leaveCall();
/*       */                   }
/*  5623 */                   catch (JmqiException e) {
/*  5624 */                     if (Trace.isOn) {
/*  5625 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                     }
/*  5627 */                     jTls.lastException = e;
/*  5628 */                     pCompCode.x = e.getCompCode();
/*  5629 */                     pReason.x = e.getReason();
/*       */                   } 
/*       */                 } 
/*  5632 */                 if (Trace.isOn) {
/*  5633 */                   Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                 
/*       */                 }
/*       */               }
/*       */             });
/*       */       }
/*       */       else {
/*       */         
/*  5641 */         if (this.adapterIsRRS) {
/*  5642 */           localhconn.enterCall();
/*  5643 */           lockedLocalhconn = localhconn;
/*       */         } 
/*  5645 */         boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  5646 */         Native.MQSUB(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, descBuf, localhobj, localhsub, pCompCode, pReason);
/*       */       } 
/*       */ 
/*       */       
/*  5650 */       localhobj.setHobj(pHobj);
/*  5651 */       localhsub.setHobj(pHsub);
/*  5652 */       localhobj.setHconn(localhconn);
/*  5653 */       localhsub.setHconn(localhconn);
/*  5654 */       if (pSubDesc != null) {
/*  5655 */         pSubDesc.readFromBuffer(descBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       }
/*       */       
/*  5658 */       if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES)
/*       */       {
/*  5660 */         localhobj.setCtxToken(rxpb.getCtxTkn());
/*  5661 */         localhsub.setCtxToken(rxpb.getCtxTkn());
/*       */       }
/*       */     
/*       */     }
/*  5665 */     catch (JmqiException e) {
/*  5666 */       if (Trace.isOn) {
/*  5667 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQSUB(Hconn,MQSD,Phobj,Phobj,final Pint,final Pint)", (Throwable)e, 1);
/*       */       }
/*       */       
/*  5670 */       jTls.lastException = e;
/*  5671 */       pCompCode.x = e.getCompCode();
/*  5672 */       pReason.x = e.getReason();
/*       */     }
/*  5674 */     catch (UnsatisfiedLinkError e) {
/*  5675 */       if (Trace.isOn) {
/*  5676 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQSUB(Hconn,MQSD,Phobj,Phobj,final Pint,final Pint)", e, 2);
/*       */       }
/*       */       
/*  5679 */       pCompCode.x = 2;
/*  5680 */       pReason.x = 2298;
/*       */     }
/*  5682 */     catch (Throwable e) {
/*  5683 */       if (Trace.isOn) {
/*  5684 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQSUB(Hconn,MQSD,Phobj,Phobj,final Pint,final Pint)", e, 3);
/*       */       }
/*       */       
/*  5687 */       castUnexpectedException("MQSUB(Hconn,MQSD,Phobj,Phobj,final Pint,final Pint)", 1, e);
/*       */     } finally {
/*       */       
/*  5690 */       if (Trace.isOn) {
/*  5691 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQSUB(Hconn,MQSD,Phobj,Phobj,final Pint,final Pint)");
/*       */       }
/*       */       
/*  5694 */       if (lockedLocalhconn != null) {
/*       */         
/*       */         try {
/*  5697 */           lockedLocalhconn.leaveCall();
/*       */         }
/*  5699 */         catch (JmqiException e) {
/*  5700 */           if (Trace.isOn) {
/*  5701 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQSUB(Hconn,MQSD,Phobj,Phobj,final Pint,final Pint)", (Throwable)e, 4);
/*       */           }
/*       */           
/*  5704 */           jTls.lastException = e;
/*  5705 */           pCompCode.x = e.getCompCode();
/*  5706 */           pReason.x = e.getReason();
/*       */         } 
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  5715 */       if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES && this.inheritRRSContext && 
/*  5716 */         pReason.x == 2009) {
/*  5717 */         clearInheritedRXPB(jTls);
/*       */       }
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  5724 */     if (Trace.isOn) {
/*  5725 */       Trace.data(this, "MQSUB(Hconn,MQSD,Phobj,Phobj,final Pint,final Pint)", "__________");
/*  5726 */       Trace.data(this, "MQSUB(Hconn,MQSD,Phobj,Phobj,final Pint,final Pint)", "MQSUB <<");
/*  5727 */       Trace.data(this, "MQSUB(Hconn,MQSD,Phobj,Phobj,final Pint,final Pint)", "Hconn", hconn);
/*  5728 */       Trace.data(this, "MQSUB(Hconn,MQSD,Phobj,Phobj,final Pint,final Pint)", "Subdesc", pSubDesc);
/*  5729 */       Trace.data(this, "MQSUB(Hconn,MQSD,Phobj,Phobj,final Pint,final Pint)", "Hobj", pHobj);
/*  5730 */       Trace.data(this, "MQSUB(Hconn,MQSD,Phobj,Phobj,final Pint,final Pint)", "Hsub", pHsub);
/*  5731 */       Trace.data(this, "MQSUB(Hconn,MQSD,Phobj,Phobj,final Pint,final Pint)", "CompCode", pCompCode);
/*  5732 */       Trace.data(this, "MQSUB(Hconn,MQSD,Phobj,Phobj,final Pint,final Pint)", "Reason", pReason);
/*  5733 */       Trace.data(this, "MQSUB(Hconn,MQSD,Phobj,Phobj,final Pint,final Pint)", "__________");
/*       */     } 
/*  5735 */     if (Trace.isOn) {
/*  5736 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQSUB(Hconn,MQSD,Phobj,Phobj,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   @Deprecated
/*       */   public void MQSUBRQ(Hconn hconn, Phobj pHsub, int action, MQSRO subRqOpts, Pint pCompCode, Pint pReason) {
/*  5757 */     if (Trace.isOn) {
/*  5758 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQSUBRQ(Hconn,Phobj,final int,MQSRO,final Pint,final Pint)", new Object[] { hconn, pHsub, 
/*       */             
/*  5760 */             Integer.valueOf(action), subRqOpts, pCompCode, pReason });
/*       */     }
/*       */     
/*  5763 */     Hobj hsub = pHsub.getHobj();
/*  5764 */     MQSUBRQ(hconn, hsub, action, subRqOpts, pCompCode, pReason);
/*  5765 */     if (Trace.isOn) {
/*  5766 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQSUBRQ(Hconn,Phobj,final int,MQSRO,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQSUBRQ(Hconn hconn, Hobj hsub, final int action, MQSRO subRqOpts, final Pint pCompCode, final Pint pReason) {
/*  5784 */     if (Trace.isOn) {
/*  5785 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQSUBRQ(Hconn,Hobj,final int,MQSRO,final Pint,final Pint)", new Object[] { hconn, hsub, 
/*       */             
/*  5787 */             Integer.valueOf(action), subRqOpts, pCompCode, pReason });
/*       */     }
/*  5789 */     String fid = "MQSUBRQ(Hconn,Hobj,final int,MQSRO,final Pint,final Pint)";
/*       */ 
/*       */     
/*  5792 */     if (Trace.isOn) {
/*  5793 */       Trace.data(this, "MQSUBRQ(Hconn,Hobj,final int,MQSRO,final Pint,final Pint)", "__________");
/*  5794 */       Trace.data(this, "MQSUBRQ(Hconn,Hobj,final int,MQSRO,final Pint,final Pint)", "MQSUBRQ >>");
/*  5795 */       Trace.data(this, "MQSUBRQ(Hconn,Hobj,final int,MQSRO,final Pint,final Pint)", "Hconn", hconn);
/*  5796 */       Trace.data(this, "MQSUBRQ(Hconn,Hobj,final int,MQSRO,final Pint,final Pint)", "Hsub", hsub);
/*  5797 */       Trace.data(this, "MQSUBRQ(Hconn,Hobj,final int,MQSRO,final Pint,final Pint)", "action", Integer.valueOf(action));
/*  5798 */       Trace.data(this, "MQSUBRQ(Hconn,Hobj,final int,MQSRO,final Pint,final Pint)", "MQSRO", subRqOpts);
/*  5799 */       Trace.data(this, "MQSUBRQ(Hconn,Hobj,final int,MQSRO,final Pint,final Pint)", "CompCode", pCompCode);
/*  5800 */       Trace.data(this, "MQSUBRQ(Hconn,Hobj,final int,MQSRO,final Pint,final Pint)", "Reason", pReason);
/*  5801 */       Trace.data(this, "MQSUBRQ(Hconn,Hobj,final int,MQSRO,final Pint,final Pint)", "__________");
/*       */     } 
/*       */     
/*  5804 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  5805 */     JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/*  5806 */     LocalHconn lockedLocalhconn = null; try {
/*       */       final byte[] rxpbBuf, optBuf;
/*  5808 */       final LocalHconn localhconn = getLocalHconn(hconn);
/*  5809 */       final LocalHobj localhsub = LocalHobj.getLocalHobj(this.env, hsub);
/*  5810 */       final byte[] qmName = localhconn.getQMNameBytes();
/*       */       
/*  5812 */       RXPB rxpb = null;
/*       */       
/*  5814 */       if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/*  5815 */         rxpbBuf = null;
/*       */       } else {
/*       */         
/*  5818 */         if (this.inheritRRSContext) {
/*  5819 */           rxpb = getInheritedRxpb(tls, jTls, localhconn);
/*       */         } else {
/*       */           
/*  5822 */           rxpb = localhconn.getRxpb();
/*       */         } 
/*  5824 */         int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/*  5825 */         if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/*  5826 */           tls.rxpbBuf = new byte[rxpbLen];
/*       */         }
/*  5828 */         rxpbBuf = tls.rxpbBuf;
/*  5829 */         rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */       
/*  5833 */       if (subRqOpts == null) {
/*  5834 */         optBuf = null;
/*       */       } else {
/*       */         
/*  5837 */         int sroLen = subRqOpts.getRequiredBufferSize(ptrSize, nativeCp);
/*  5838 */         if (tls.optBuf == null || tls.optBuf.length < sroLen) {
/*  5839 */           tls.optBuf = new byte[sroLen];
/*       */         }
/*  5841 */         optBuf = tls.optBuf;
/*  5842 */         subRqOpts.writeToBuffer(optBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  5848 */       if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hconn)) {
/*  5849 */         localhconn.syncExec(new JmqiRunnable(this.env)
/*       */             {
/*       */               public void run()
/*       */               {
/*  5853 */                 if (Trace.isOn) {
/*  5854 */                   Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                 }
/*       */ 
/*       */                 
/*       */                 try {
/*  5859 */                   localhconn.enterCall();
/*       */                   
/*  5861 */                   boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  5862 */                   Native.MQSUBRQ(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, localhsub, action, optBuf, pCompCode, pReason);
/*       */                 }
/*  5864 */                 catch (JmqiException e) {
/*  5865 */                   if (Trace.isOn) {
/*  5866 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                   }
/*       */                   
/*  5869 */                   pCompCode.x = e.getCompCode();
/*  5870 */                   pReason.x = e.getReason();
/*       */                 }
/*  5872 */                 catch (UnsatisfiedLinkError e) {
/*  5873 */                   if (Trace.isOn) {
/*  5874 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                   }
/*  5876 */                   pCompCode.x = 2;
/*  5877 */                   pReason.x = 2298;
/*       */                 } finally {
/*       */                   
/*  5880 */                   if (Trace.isOn) {
/*  5881 */                     Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                   
/*       */                   try {
/*  5885 */                     localhconn.leaveCall();
/*       */                   }
/*  5887 */                   catch (JmqiException e) {
/*  5888 */                     if (Trace.isOn) {
/*  5889 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                     }
/*  5891 */                     pCompCode.x = e.getCompCode();
/*  5892 */                     pReason.x = e.getReason();
/*       */                   } 
/*       */                 } 
/*  5895 */                 if (Trace.isOn) {
/*  5896 */                   Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                 
/*       */                 }
/*       */               }
/*       */             });
/*       */       }
/*       */       else {
/*       */         
/*  5904 */         if (this.adapterIsRRS) {
/*  5905 */           localhconn.enterCall();
/*  5906 */           lockedLocalhconn = localhconn;
/*       */         } 
/*  5908 */         boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  5909 */         Native.MQSUBRQ(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, localhsub, action, optBuf, pCompCode, pReason);
/*       */       } 
/*       */ 
/*       */       
/*  5913 */       if (subRqOpts != null) {
/*  5914 */         subRqOpts.readFromBuffer(optBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       }
/*       */       
/*  5917 */       if (rxpb != null) {
/*  5918 */         localhsub.setCtxToken(rxpb.getCtxTkn());
/*       */       
/*       */       }
/*       */     }
/*  5922 */     catch (JmqiException e) {
/*  5923 */       if (Trace.isOn) {
/*  5924 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQSUBRQ(Hconn,Hobj,final int,MQSRO,final Pint,final Pint)", (Throwable)e, 1);
/*       */       }
/*       */       
/*  5927 */       pCompCode.x = e.getCompCode();
/*  5928 */       pReason.x = e.getReason();
/*       */     }
/*  5930 */     catch (UnsatisfiedLinkError e) {
/*  5931 */       if (Trace.isOn) {
/*  5932 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQSUBRQ(Hconn,Hobj,final int,MQSRO,final Pint,final Pint)", e, 2);
/*       */       }
/*       */       
/*  5935 */       pCompCode.x = 2;
/*  5936 */       pReason.x = 2298;
/*       */     }
/*  5938 */     catch (Throwable e) {
/*  5939 */       if (Trace.isOn) {
/*  5940 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQSUBRQ(Hconn,Hobj,final int,MQSRO,final Pint,final Pint)", e, 3);
/*       */       
/*       */       }
/*       */     }
/*       */     finally {
/*       */       
/*  5946 */       if (Trace.isOn) {
/*  5947 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQSUBRQ(Hconn,Hobj,final int,MQSRO,final Pint,final Pint)");
/*       */       }
/*       */       
/*  5950 */       if (lockedLocalhconn != null) {
/*       */         
/*       */         try {
/*  5953 */           lockedLocalhconn.leaveCall();
/*       */         }
/*  5955 */         catch (JmqiException e) {
/*  5956 */           if (Trace.isOn) {
/*  5957 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQSUBRQ(Hconn,Hobj,final int,MQSRO,final Pint,final Pint)", (Throwable)e, 4);
/*       */           }
/*       */           
/*  5960 */           jTls.lastException = e;
/*  5961 */           pCompCode.x = e.getCompCode();
/*  5962 */           pReason.x = e.getReason();
/*       */         } 
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  5971 */       if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES && this.inheritRRSContext && 
/*  5972 */         pReason.x == 2009) {
/*  5973 */         clearInheritedRXPB(jTls);
/*       */       }
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  5980 */     if (Trace.isOn) {
/*  5981 */       Trace.data(this, "MQSUBRQ(Hconn,Hobj,final int,MQSRO,final Pint,final Pint)", "__________");
/*  5982 */       Trace.data(this, "MQSUBRQ(Hconn,Hobj,final int,MQSRO,final Pint,final Pint)", "MQSUBRQ <<");
/*  5983 */       Trace.data(this, "MQSUBRQ(Hconn,Hobj,final int,MQSRO,final Pint,final Pint)", "Hconn", hconn);
/*  5984 */       Trace.data(this, "MQSUBRQ(Hconn,Hobj,final int,MQSRO,final Pint,final Pint)", "Hsub", hsub);
/*  5985 */       Trace.data(this, "MQSUBRQ(Hconn,Hobj,final int,MQSRO,final Pint,final Pint)", "action", Integer.valueOf(action));
/*  5986 */       Trace.data(this, "MQSUBRQ(Hconn,Hobj,final int,MQSRO,final Pint,final Pint)", "MQSRO", subRqOpts);
/*  5987 */       Trace.data(this, "MQSUBRQ(Hconn,Hobj,final int,MQSRO,final Pint,final Pint)", "CompCode", pCompCode);
/*  5988 */       Trace.data(this, "MQSUBRQ(Hconn,Hobj,final int,MQSRO,final Pint,final Pint)", "Reason", pReason);
/*  5989 */       Trace.data(this, "MQSUBRQ(Hconn,Hobj,final int,MQSRO,final Pint,final Pint)", "__________");
/*       */     } 
/*  5991 */     if (Trace.isOn) {
/*  5992 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQSUBRQ(Hconn,Hobj,final int,MQSRO,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQCRTMH(Hconn hConn, MQCMHO pCrtMsgHOpts, Phmsg pHmsg, final Pint pCompCode, final Pint pReason) {
/*  6009 */     if (Trace.isOn) {
/*  6010 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQCRTMH(Hconn,MQCMHO,final Phmsg,final Pint,final Pint)", new Object[] { hConn, pCrtMsgHOpts, pHmsg, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */     
/*  6014 */     String fid = "MQCRTMH(Hconn,MQCMHO,final Phmsg,final Pint,final Pint)";
/*       */     
/*  6016 */     if (Trace.isOn) {
/*  6017 */       Trace.data(this, "MQCRTMH(Hconn,MQCMHO,final Phmsg,final Pint,final Pint)", "__________");
/*  6018 */       Trace.data(this, "MQCRTMH(Hconn,MQCMHO,final Phmsg,final Pint,final Pint)", "MQCRTMH >>");
/*  6019 */       Trace.data(this, "MQCRTMH(Hconn,MQCMHO,final Phmsg,final Pint,final Pint)", "Hconn", hConn);
/*  6020 */       Trace.data(this, "MQCRTMH(Hconn,MQCMHO,final Phmsg,final Pint,final Pint)", "CrtMsgHOpts", pCrtMsgHOpts);
/*  6021 */       Trace.data(this, "MQCRTMH(Hconn,MQCMHO,final Phmsg,final Pint,final Pint)", "Hmsg", pHmsg);
/*  6022 */       Trace.data(this, "MQCRTMH(Hconn,MQCMHO,final Phmsg,final Pint,final Pint)", "CompCode", pCompCode);
/*  6023 */       Trace.data(this, "MQCRTMH(Hconn,MQCMHO,final Phmsg,final Pint,final Pint)", "Reason", pReason);
/*  6024 */       Trace.data(this, "MQCRTMH(Hconn,MQCMHO,final Phmsg,final Pint,final Pint)", "__________");
/*       */     } 
/*       */ 
/*       */     
/*  6028 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  6029 */     final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/*  6030 */     LocalHconn lockedLocalhconn = null;
/*       */     
/*       */     try {
/*       */       final byte[] rxpbBuf, optBuf;
/*  6034 */       final LocalHconn localhconn = getLocalHconn(hConn);
/*  6035 */       final byte[] qmName = localhconn.getQMNameBytes();
/*       */ 
/*       */       
/*  6038 */       RXPB rxpb = null;
/*       */       
/*  6040 */       if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/*  6041 */         rxpbBuf = null;
/*       */       } else {
/*       */         
/*  6044 */         if (this.inheritRRSContext) {
/*  6045 */           rxpb = getInheritedRxpb(tls, jTls, localhconn);
/*       */         } else {
/*       */           
/*  6048 */           rxpb = localhconn.getRxpb();
/*       */         } 
/*  6050 */         int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/*  6051 */         if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/*  6052 */           tls.rxpbBuf = new byte[rxpbLen];
/*       */         }
/*  6054 */         rxpbBuf = tls.rxpbBuf;
/*  6055 */         rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/*  6060 */       if (pCrtMsgHOpts == null) {
/*  6061 */         optBuf = null;
/*       */       } else {
/*       */         
/*  6064 */         int cmhoLen = pCrtMsgHOpts.getRequiredBufferSize(ptrSize, nativeCp);
/*  6065 */         if (tls.optBuf == null || tls.optBuf.length < cmhoLen) {
/*  6066 */           tls.optBuf = new byte[cmhoLen];
/*       */         }
/*  6068 */         optBuf = tls.optBuf;
/*  6069 */         pCrtMsgHOpts.writeToBuffer(optBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */       
/*  6073 */       final Hmsg hmsg = pHmsg.getHmsg();
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  6078 */       if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hConn)) {
/*  6079 */         localhconn.syncExec(new JmqiRunnable(this.env)
/*       */             {
/*       */               public void run()
/*       */               {
/*  6083 */                 if (Trace.isOn) {
/*  6084 */                   Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                 }
/*       */ 
/*       */                 
/*       */                 try {
/*  6089 */                   localhconn.enterCall();
/*       */ 
/*       */                   
/*  6092 */                   boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  6093 */                   Native.MQCRTMH(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, optBuf, hmsg, pCompCode, pReason);
/*       */                 }
/*  6095 */                 catch (JmqiException e) {
/*  6096 */                   if (Trace.isOn) {
/*  6097 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                   }
/*  6099 */                   jTls.lastException = e;
/*  6100 */                   pCompCode.x = e.getCompCode();
/*  6101 */                   pReason.x = e.getReason();
/*       */                 }
/*  6103 */                 catch (UnsatisfiedLinkError e) {
/*  6104 */                   if (Trace.isOn) {
/*  6105 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                   }
/*  6107 */                   pCompCode.x = 2;
/*  6108 */                   pReason.x = 2298;
/*       */                 } finally {
/*       */                   
/*  6111 */                   if (Trace.isOn) {
/*  6112 */                     Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                   
/*       */                   try {
/*  6116 */                     localhconn.leaveCall();
/*       */                   }
/*  6118 */                   catch (JmqiException e) {
/*  6119 */                     if (Trace.isOn) {
/*  6120 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                     }
/*  6122 */                     jTls.lastException = e;
/*  6123 */                     pCompCode.x = e.getCompCode();
/*  6124 */                     pReason.x = e.getReason();
/*       */                   } 
/*       */                 } 
/*  6127 */                 if (Trace.isOn) {
/*  6128 */                   Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                 }
/*       */               }
/*       */             });
/*       */       }
/*       */       else {
/*       */         
/*  6135 */         if (this.adapterIsRRS) {
/*  6136 */           localhconn.enterCall();
/*  6137 */           lockedLocalhconn = localhconn;
/*       */         } 
/*  6139 */         boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  6140 */         Native.MQCRTMH(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, optBuf, hmsg, pCompCode, pReason);
/*       */       } 
/*       */ 
/*       */       
/*  6144 */       pHmsg.setHmsg(hmsg);
/*       */     }
/*  6146 */     catch (JmqiException e) {
/*  6147 */       if (Trace.isOn) {
/*  6148 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQCRTMH(Hconn,MQCMHO,final Phmsg,final Pint,final Pint)", (Throwable)e, 1);
/*       */       }
/*       */       
/*  6151 */       jTls.lastException = e;
/*  6152 */       pCompCode.x = e.getCompCode();
/*  6153 */       pReason.x = e.getReason();
/*       */     }
/*  6155 */     catch (UnsatisfiedLinkError e) {
/*  6156 */       if (Trace.isOn) {
/*  6157 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQCRTMH(Hconn,MQCMHO,final Phmsg,final Pint,final Pint)", e, 2);
/*       */       }
/*       */       
/*  6160 */       pCompCode.x = 2;
/*  6161 */       pReason.x = 2298;
/*       */     }
/*  6163 */     catch (Throwable e) {
/*  6164 */       if (Trace.isOn) {
/*  6165 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQCRTMH(Hconn,MQCMHO,final Phmsg,final Pint,final Pint)", e, 3);
/*       */       }
/*       */       
/*  6168 */       castUnexpectedException("MQCRTMH(Hconn,MQCMHO,final Phmsg,final Pint,final Pint)", 1, e);
/*       */     } finally {
/*       */       
/*  6171 */       if (Trace.isOn) {
/*  6172 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQCRTMH(Hconn,MQCMHO,final Phmsg,final Pint,final Pint)");
/*       */       }
/*       */       
/*  6175 */       if (lockedLocalhconn != null) {
/*       */         
/*       */         try {
/*  6178 */           lockedLocalhconn.leaveCall();
/*       */         }
/*  6180 */         catch (JmqiException e) {
/*  6181 */           if (Trace.isOn) {
/*  6182 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQCRTMH(Hconn,MQCMHO,final Phmsg,final Pint,final Pint)", (Throwable)e, 4);
/*       */           }
/*       */           
/*  6185 */           jTls.lastException = e;
/*  6186 */           pCompCode.x = e.getCompCode();
/*  6187 */           pReason.x = e.getReason();
/*       */         } 
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  6196 */       if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES && this.inheritRRSContext && 
/*  6197 */         pReason.x == 2009) {
/*  6198 */         clearInheritedRXPB(jTls);
/*       */       }
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  6205 */     if (Trace.isOn) {
/*  6206 */       Trace.data(this, "MQCRTMH(Hconn,MQCMHO,final Phmsg,final Pint,final Pint)", "__________");
/*  6207 */       Trace.data(this, "MQCRTMH(Hconn,MQCMHO,final Phmsg,final Pint,final Pint)", "MQCRTMH <<");
/*  6208 */       Trace.data(this, "MQCRTMH(Hconn,MQCMHO,final Phmsg,final Pint,final Pint)", "Hconn", hConn);
/*  6209 */       Trace.data(this, "MQCRTMH(Hconn,MQCMHO,final Phmsg,final Pint,final Pint)", "CrtMsgHOpts", pCrtMsgHOpts);
/*  6210 */       Trace.data(this, "MQCRTMH(Hconn,MQCMHO,final Phmsg,final Pint,final Pint)", "Hmsg", pHmsg);
/*  6211 */       Trace.data(this, "MQCRTMH(Hconn,MQCMHO,final Phmsg,final Pint,final Pint)", "CompCode", pCompCode);
/*  6212 */       Trace.data(this, "MQCRTMH(Hconn,MQCMHO,final Phmsg,final Pint,final Pint)", "Reason", pReason);
/*  6213 */       Trace.data(this, "MQCRTMH(Hconn,MQCMHO,final Phmsg,final Pint,final Pint)", "__________");
/*       */     } 
/*  6215 */     if (Trace.isOn) {
/*  6216 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQCRTMH(Hconn,MQCMHO,final Phmsg,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQDLTMH(Hconn hConn, Phmsg pHmsg, MQDMHO pDltMsgHOpts, final Pint pCompCode, final Pint pReason) {
/*  6234 */     if (Trace.isOn) {
/*  6235 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQDLTMH(Hconn,final Phmsg,MQDMHO,final Pint,final Pint)", new Object[] { hConn, pHmsg, pDltMsgHOpts, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */     
/*  6239 */     String fid = "MQDLTMH(Hconn,final Phmsg,MQDMHO,final Pint,final Pint)";
/*       */     
/*  6241 */     if (Trace.isOn) {
/*  6242 */       Trace.data(this, "MQDLTMH(Hconn,final Phmsg,MQDMHO,final Pint,final Pint)", "__________");
/*  6243 */       Trace.data(this, "MQDLTMH(Hconn,final Phmsg,MQDMHO,final Pint,final Pint)", "MQDLTMH >>");
/*  6244 */       Trace.data(this, "MQDLTMH(Hconn,final Phmsg,MQDMHO,final Pint,final Pint)", "Hconn", hConn);
/*  6245 */       Trace.data(this, "MQDLTMH(Hconn,final Phmsg,MQDMHO,final Pint,final Pint)", "Hmsg", pHmsg);
/*  6246 */       Trace.data(this, "MQDLTMH(Hconn,final Phmsg,MQDMHO,final Pint,final Pint)", "DltMsgHOpts", pDltMsgHOpts);
/*  6247 */       Trace.data(this, "MQDLTMH(Hconn,final Phmsg,MQDMHO,final Pint,final Pint)", "CompCode", pCompCode);
/*  6248 */       Trace.data(this, "MQDLTMH(Hconn,final Phmsg,MQDMHO,final Pint,final Pint)", "Reason", pReason);
/*  6249 */       Trace.data(this, "MQDLTMH(Hconn,final Phmsg,MQDMHO,final Pint,final Pint)", "__________");
/*       */     } 
/*       */ 
/*       */     
/*  6253 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  6254 */     final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/*  6255 */     LocalHconn lockedLocalhconn = null;
/*       */     
/*       */     try {
/*       */       final byte[] rxpbBuf, optBuf;
/*  6259 */       final LocalHconn localhconn = getLocalHconn(hConn);
/*  6260 */       final byte[] qmName = localhconn.getQMNameBytes();
/*       */ 
/*       */       
/*  6263 */       RXPB rxpb = null;
/*       */       
/*  6265 */       if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/*  6266 */         rxpbBuf = null;
/*       */       } else {
/*       */         
/*  6269 */         if (this.inheritRRSContext) {
/*  6270 */           rxpb = getInheritedRxpb(tls, jTls, localhconn);
/*       */         } else {
/*       */           
/*  6273 */           rxpb = localhconn.getRxpb();
/*       */         } 
/*  6275 */         int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/*  6276 */         if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/*  6277 */           tls.rxpbBuf = new byte[rxpbLen];
/*       */         }
/*  6279 */         rxpbBuf = tls.rxpbBuf;
/*  6280 */         rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/*  6285 */       if (pDltMsgHOpts == null) {
/*  6286 */         optBuf = null;
/*       */       } else {
/*       */         
/*  6289 */         int dmhoLen = pDltMsgHOpts.getRequiredBufferSize(ptrSize, nativeCp);
/*  6290 */         if (tls.optBuf == null || tls.optBuf.length < dmhoLen) {
/*  6291 */           tls.optBuf = new byte[dmhoLen];
/*       */         }
/*  6293 */         optBuf = tls.optBuf;
/*  6294 */         pDltMsgHOpts.writeToBuffer(optBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */       
/*  6298 */       final Hmsg hmsg = pHmsg.getHmsg();
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  6303 */       if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hConn)) {
/*  6304 */         localhconn.syncExec(new JmqiRunnable(this.env)
/*       */             {
/*       */               public void run()
/*       */               {
/*  6308 */                 if (Trace.isOn) {
/*  6309 */                   Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                 }
/*       */ 
/*       */                 
/*       */                 try {
/*  6314 */                   localhconn.enterCall();
/*       */ 
/*       */                   
/*  6317 */                   boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  6318 */                   Native.MQDLTMH(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, hmsg, optBuf, pCompCode, pReason);
/*       */                 }
/*  6320 */                 catch (JmqiException e) {
/*  6321 */                   if (Trace.isOn) {
/*  6322 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                   }
/*  6324 */                   jTls.lastException = e;
/*  6325 */                   pCompCode.x = e.getCompCode();
/*  6326 */                   pReason.x = e.getReason();
/*       */                 }
/*  6328 */                 catch (UnsatisfiedLinkError e) {
/*  6329 */                   if (Trace.isOn) {
/*  6330 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                   }
/*  6332 */                   pCompCode.x = 2;
/*  6333 */                   pReason.x = 2298;
/*       */                 } finally {
/*       */                   
/*  6336 */                   if (Trace.isOn) {
/*  6337 */                     Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                   
/*       */                   try {
/*  6341 */                     localhconn.leaveCall();
/*       */                   }
/*  6343 */                   catch (JmqiException e) {
/*  6344 */                     if (Trace.isOn) {
/*  6345 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                     }
/*  6347 */                     jTls.lastException = e;
/*  6348 */                     pCompCode.x = e.getCompCode();
/*  6349 */                     pReason.x = e.getReason();
/*       */                   } 
/*       */                 } 
/*  6352 */                 if (Trace.isOn) {
/*  6353 */                   Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                 }
/*       */               }
/*       */             });
/*       */       }
/*       */       else {
/*       */         
/*  6360 */         if (this.adapterIsRRS) {
/*  6361 */           localhconn.enterCall();
/*  6362 */           lockedLocalhconn = localhconn;
/*       */         } 
/*  6364 */         boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  6365 */         Native.MQDLTMH(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, hmsg, optBuf, pCompCode, pReason);
/*       */       } 
/*       */ 
/*       */       
/*  6369 */       pHmsg.setHmsg(hmsg);
/*       */     }
/*  6371 */     catch (JmqiException e) {
/*  6372 */       if (Trace.isOn) {
/*  6373 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQDLTMH(Hconn,final Phmsg,MQDMHO,final Pint,final Pint)", (Throwable)e, 1);
/*       */       }
/*       */       
/*  6376 */       jTls.lastException = e;
/*  6377 */       pCompCode.x = e.getCompCode();
/*  6378 */       pReason.x = e.getReason();
/*       */     }
/*  6380 */     catch (UnsatisfiedLinkError e) {
/*  6381 */       if (Trace.isOn) {
/*  6382 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQDLTMH(Hconn,final Phmsg,MQDMHO,final Pint,final Pint)", e, 2);
/*       */       }
/*       */       
/*  6385 */       pCompCode.x = 2;
/*  6386 */       pReason.x = 2298;
/*       */     }
/*  6388 */     catch (Throwable e) {
/*  6389 */       if (Trace.isOn) {
/*  6390 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQDLTMH(Hconn,final Phmsg,MQDMHO,final Pint,final Pint)", e, 3);
/*       */       }
/*       */       
/*  6393 */       castUnexpectedException("MQDLTMH(Hconn,final Phmsg,MQDMHO,final Pint,final Pint)", 1, e);
/*       */     } finally {
/*       */       
/*  6396 */       if (Trace.isOn) {
/*  6397 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQDLTMH(Hconn,final Phmsg,MQDMHO,final Pint,final Pint)");
/*       */       }
/*       */       
/*  6400 */       if (lockedLocalhconn != null) {
/*       */         
/*       */         try {
/*  6403 */           lockedLocalhconn.leaveCall();
/*       */         }
/*  6405 */         catch (JmqiException e) {
/*  6406 */           if (Trace.isOn) {
/*  6407 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQDLTMH(Hconn,final Phmsg,MQDMHO,final Pint,final Pint)", (Throwable)e, 4);
/*       */           }
/*       */           
/*  6410 */           jTls.lastException = e;
/*  6411 */           pCompCode.x = e.getCompCode();
/*  6412 */           pReason.x = e.getReason();
/*       */         } 
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  6421 */       if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES && this.inheritRRSContext && 
/*  6422 */         pReason.x == 2009) {
/*  6423 */         clearInheritedRXPB(jTls);
/*       */       }
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  6430 */     if (Trace.isOn) {
/*  6431 */       Trace.data(this, "MQDLTMH(Hconn,final Phmsg,MQDMHO,final Pint,final Pint)", "__________");
/*  6432 */       Trace.data(this, "MQDLTMH(Hconn,final Phmsg,MQDMHO,final Pint,final Pint)", "MQDLTMH <<");
/*  6433 */       Trace.data(this, "MQDLTMH(Hconn,final Phmsg,MQDMHO,final Pint,final Pint)", "Hconn", hConn);
/*  6434 */       Trace.data(this, "MQDLTMH(Hconn,final Phmsg,MQDMHO,final Pint,final Pint)", "Hmsg", pHmsg);
/*  6435 */       Trace.data(this, "MQDLTMH(Hconn,final Phmsg,MQDMHO,final Pint,final Pint)", "DltMsgHOpts", pDltMsgHOpts);
/*  6436 */       Trace.data(this, "MQDLTMH(Hconn,final Phmsg,MQDMHO,final Pint,final Pint)", "CompCode", pCompCode);
/*  6437 */       Trace.data(this, "MQDLTMH(Hconn,final Phmsg,MQDMHO,final Pint,final Pint)", "Reason", pReason);
/*  6438 */       Trace.data(this, "MQDLTMH(Hconn,final Phmsg,MQDMHO,final Pint,final Pint)", "__________");
/*       */     } 
/*  6440 */     if (Trace.isOn) {
/*  6441 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQDLTMH(Hconn,final Phmsg,MQDMHO,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQSETMP(Hconn hConn, Hmsg hmsg, MQSMPO pSetPropOpts, MQCHARV pName, MQPD pPropDesc, final int Type, final int ValueLength, ByteBuffer pValue, final Pint pCompCode, final Pint pReason) {
/*  6476 */     if (Trace.isOn) {
/*  6477 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQSETMP(Hconn,final Hmsg,MQSMPO,MQCHARV,MQPD,final int,final int,ByteBuffer,final Pint,final Pint)", new Object[] { hConn, hmsg, pSetPropOpts, pName, pPropDesc, 
/*       */             
/*  6479 */             Integer.valueOf(Type), 
/*  6480 */             Integer.valueOf(ValueLength), pValue, pCompCode, pReason });
/*       */     }
/*  6482 */     String fid = "MQSETMP(Hconn,final Hmsg,MQSMPO,MQCHARV,MQPD,final int,final int,ByteBuffer,final Pint,final Pint)";
/*       */     
/*  6484 */     if (Trace.isOn) {
/*  6485 */       Trace.data(this, "MQSETMP(Hconn,final Hmsg,MQSMPO,MQCHARV,MQPD,final int,final int,ByteBuffer,final Pint,final Pint)", "__________");
/*  6486 */       Trace.data(this, "MQSETMP(Hconn,final Hmsg,MQSMPO,MQCHARV,MQPD,final int,final int,ByteBuffer,final Pint,final Pint)", "MQSETMP >>");
/*  6487 */       Trace.data(this, "MQSETMP(Hconn,final Hmsg,MQSMPO,MQCHARV,MQPD,final int,final int,ByteBuffer,final Pint,final Pint)", "Hconn", hConn);
/*  6488 */       Trace.data(this, "MQSETMP(Hconn,final Hmsg,MQSMPO,MQCHARV,MQPD,final int,final int,ByteBuffer,final Pint,final Pint)", "Hmsg", hmsg);
/*  6489 */       Trace.data(this, "MQSETMP(Hconn,final Hmsg,MQSMPO,MQCHARV,MQPD,final int,final int,ByteBuffer,final Pint,final Pint)", "SetPropOpts", pSetPropOpts);
/*  6490 */       Trace.data(this, "MQSETMP(Hconn,final Hmsg,MQSMPO,MQCHARV,MQPD,final int,final int,ByteBuffer,final Pint,final Pint)", "Name", pName);
/*  6491 */       Trace.data(this, "MQSETMP(Hconn,final Hmsg,MQSMPO,MQCHARV,MQPD,final int,final int,ByteBuffer,final Pint,final Pint)", "PropDesc", pPropDesc);
/*  6492 */       Trace.data(this, "MQSETMP(Hconn,final Hmsg,MQSMPO,MQCHARV,MQPD,final int,final int,ByteBuffer,final Pint,final Pint)", "Type", Integer.toString(Type));
/*  6493 */       Trace.data(this, "MQSETMP(Hconn,final Hmsg,MQSMPO,MQCHARV,MQPD,final int,final int,ByteBuffer,final Pint,final Pint)", "ValueLength", Integer.toString(ValueLength));
/*  6494 */       if (pValue == null) {
/*  6495 */         Trace.data(this, "MQSETMP(Hconn,final Hmsg,MQSMPO,MQCHARV,MQPD,final int,final int,ByteBuffer,final Pint,final Pint)", "buffer is (null)");
/*       */       } else {
/*       */         
/*  6498 */         JmqiTools.traceApiBuffer(this, "MQSETMP(Hconn,final Hmsg,MQSMPO,MQCHARV,MQPD,final int,final int,ByteBuffer,final Pint,final Pint)", pValue, pValue.limit());
/*       */       } 
/*  6500 */       Trace.data(this, "MQSETMP(Hconn,final Hmsg,MQSMPO,MQCHARV,MQPD,final int,final int,ByteBuffer,final Pint,final Pint)", "CompCode", pCompCode);
/*  6501 */       Trace.data(this, "MQSETMP(Hconn,final Hmsg,MQSMPO,MQCHARV,MQPD,final int,final int,ByteBuffer,final Pint,final Pint)", "Reason", pReason);
/*  6502 */       Trace.data(this, "MQSETMP(Hconn,final Hmsg,MQSMPO,MQCHARV,MQPD,final int,final int,ByteBuffer,final Pint,final Pint)", "__________");
/*       */     } 
/*       */     
/*  6505 */     pCompCode.x = 0;
/*  6506 */     pReason.x = 0;
/*       */ 
/*       */ 
/*       */     
/*  6510 */     if (ValueLength > 0) {
/*  6511 */       if (pValue == null) {
/*  6512 */         Trace.data(this, "MQSETMP(Hconn,final Hmsg,MQSMPO,MQCHARV,MQPD,final int,final int,ByteBuffer,final Pint,final Pint)", "ValueLength: " + ValueLength + ", pValue is (null)", null);
/*  6513 */         pCompCode.x = 2;
/*  6514 */         pReason.x = 2005;
/*       */       }
/*  6516 */       else if (ValueLength > pValue.limit()) {
/*  6517 */         Trace.data(this, "MQSETMP(Hconn,final Hmsg,MQSMPO,MQCHARV,MQPD,final int,final int,ByteBuffer,final Pint,final Pint)", "ValueLength:" + ValueLength + ", pValue.limit():" + pValue.limit(), null);
/*  6518 */         pCompCode.x = 2;
/*  6519 */         pReason.x = 2005;
/*       */       }
/*       */     
/*  6522 */     } else if (ValueLength < 0) {
/*  6523 */       Trace.data(this, "MQSETMP(Hconn,final Hmsg,MQSMPO,MQCHARV,MQPD,final int,final int,ByteBuffer,final Pint,final Pint)", "ValueLength is negative: " + ValueLength, null);
/*  6524 */       pCompCode.x = 2;
/*  6525 */       pReason.x = 2005;
/*       */     } 
/*       */     
/*  6528 */     if (pReason.x == 0) {
/*       */ 
/*       */       
/*  6531 */       LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  6532 */       final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/*  6533 */       LocalHconn lockedLocalhconn = null;
/*       */       
/*       */       try {
/*       */         final byte[] rxpbBuf, optBuf, pArray;
/*  6537 */         final LocalHconn localhconn = getLocalHconn(hConn);
/*  6538 */         final byte[] qmName = localhconn.getQMNameBytes();
/*       */ 
/*       */         
/*  6541 */         RXPB rxpb = null;
/*       */         
/*  6543 */         if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/*  6544 */           rxpbBuf = null;
/*       */         } else {
/*       */           
/*  6547 */           if (this.inheritRRSContext) {
/*  6548 */             rxpb = getInheritedRxpb(tls, jTls, localhconn);
/*       */           } else {
/*       */             
/*  6551 */             rxpb = localhconn.getRxpb();
/*       */           } 
/*  6553 */           int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/*  6554 */           if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/*  6555 */             tls.rxpbBuf = new byte[rxpbLen];
/*       */           }
/*  6557 */           rxpbBuf = tls.rxpbBuf;
/*  6558 */           rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         } 
/*       */ 
/*       */ 
/*       */         
/*  6563 */         if (pSetPropOpts == null) {
/*  6564 */           optBuf = null;
/*       */         } else {
/*       */           
/*  6567 */           int smpoLen = pSetPropOpts.getRequiredBufferSize(ptrSize, nativeCp);
/*  6568 */           if (tls.optBuf == null || tls.optBuf.length < smpoLen) {
/*  6569 */             tls.optBuf = new byte[smpoLen];
/*       */           }
/*  6571 */           optBuf = tls.optBuf;
/*  6572 */           pSetPropOpts.writeToBuffer(optBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         } 
/*       */ 
/*       */         
/*  6576 */         int nameLenVS = pName.getRequiredBufferSize(ptrSize, nativeCp);
/*  6577 */         int nameLen = MQCHARV.getSize(ptrSize);
/*  6578 */         final byte[] nameBuf = new byte[nameLen + nameLenVS];
/*  6579 */         pName.writeToBuffer(nameBuf, 0, 0, nameLen, ptrSize, swap, nativeCp);
/*       */ 
/*       */         
/*  6582 */         int pdLen = pPropDesc.getRequiredBufferSize(ptrSize, nativeCp);
/*  6583 */         final byte[] pdBuf = new byte[pdLen];
/*  6584 */         pPropDesc.writeToBuffer(pdBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */ 
/*       */ 
/*       */         
/*  6588 */         if (pValue == null) {
/*  6589 */           pArray = null;
/*       */         } else {
/*       */           
/*  6592 */           pArray = pValue.array();
/*       */         } 
/*       */ 
/*       */         
/*  6596 */         final long HmsgValue = hmsg.getLongHandle();
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  6601 */         if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hConn)) {
/*  6602 */           localhconn.syncExec(new JmqiRunnable(this.env)
/*       */               {
/*       */                 public void run()
/*       */                 {
/*  6606 */                   if (Trace.isOn) {
/*  6607 */                     Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                   }
/*       */ 
/*       */                   
/*       */                   try {
/*  6612 */                     localhconn.enterCall();
/*       */ 
/*       */                     
/*  6615 */                     boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  6616 */                     Native.MQSETMP(isClassTraced, localhconn
/*  6617 */                         .getValue(), qmName, rxpbBuf, HmsgValue, optBuf, nameBuf, pdBuf, Type, ValueLength, pArray, pCompCode, pReason);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */                   
/*       */                   }
/*  6630 */                   catch (JmqiException e) {
/*  6631 */                     if (Trace.isOn) {
/*  6632 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                     }
/*  6634 */                     jTls.lastException = e;
/*  6635 */                     pCompCode.x = e.getCompCode();
/*  6636 */                     pReason.x = e.getReason();
/*       */                   }
/*  6638 */                   catch (UnsatisfiedLinkError e) {
/*  6639 */                     if (Trace.isOn) {
/*  6640 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                     }
/*  6642 */                     pCompCode.x = 2;
/*  6643 */                     pReason.x = 2298;
/*       */                   } finally {
/*       */                     
/*  6646 */                     if (Trace.isOn) {
/*  6647 */                       Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                     }
/*       */                     
/*       */                     try {
/*  6651 */                       localhconn.leaveCall();
/*       */                     }
/*  6653 */                     catch (JmqiException e) {
/*  6654 */                       if (Trace.isOn) {
/*  6655 */                         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                       }
/*  6657 */                       jTls.lastException = e;
/*  6658 */                       pCompCode.x = e.getCompCode();
/*  6659 */                       pReason.x = e.getReason();
/*       */                     } 
/*       */                   } 
/*  6662 */                   if (Trace.isOn) {
/*  6663 */                     Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                 }
/*       */               });
/*       */         }
/*       */         else {
/*       */           
/*  6670 */           if (this.adapterIsRRS) {
/*  6671 */             localhconn.enterCall();
/*  6672 */             lockedLocalhconn = localhconn;
/*       */           } 
/*  6674 */           boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  6675 */           Native.MQSETMP(isClassTraced, localhconn
/*  6676 */               .getValue(), qmName, rxpbBuf, HmsgValue, optBuf, nameBuf, pdBuf, Type, ValueLength, pArray, pCompCode, pReason);
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  6691 */         pPropDesc.readFromBuffer(pdBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       
/*       */       }
/*  6694 */       catch (JmqiException e) {
/*  6695 */         if (Trace.isOn) {
/*  6696 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQSETMP(Hconn,final Hmsg,MQSMPO,MQCHARV,MQPD,final int,final int,ByteBuffer,final Pint,final Pint)", (Throwable)e, 1);
/*       */         }
/*       */ 
/*       */         
/*  6700 */         jTls.lastException = e;
/*  6701 */         pCompCode.x = e.getCompCode();
/*  6702 */         pReason.x = e.getReason();
/*       */       }
/*  6704 */       catch (UnsatisfiedLinkError e) {
/*  6705 */         if (Trace.isOn) {
/*  6706 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQSETMP(Hconn,final Hmsg,MQSMPO,MQCHARV,MQPD,final int,final int,ByteBuffer,final Pint,final Pint)", e, 2);
/*       */         }
/*       */ 
/*       */         
/*  6710 */         pCompCode.x = 2;
/*  6711 */         pReason.x = 2298;
/*       */       }
/*  6713 */       catch (Throwable e) {
/*  6714 */         if (Trace.isOn) {
/*  6715 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQSETMP(Hconn,final Hmsg,MQSMPO,MQCHARV,MQPD,final int,final int,ByteBuffer,final Pint,final Pint)", e, 3);
/*       */         }
/*       */ 
/*       */         
/*  6719 */         castUnexpectedException("MQSETMP(Hconn,final Hmsg,MQSMPO,MQCHARV,MQPD,final int,final int,ByteBuffer,final Pint,final Pint)", 1, e);
/*       */       } finally {
/*       */         
/*  6722 */         if (Trace.isOn) {
/*  6723 */           Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQSETMP(Hconn,final Hmsg,MQSMPO,MQCHARV,MQPD,final int,final int,ByteBuffer,final Pint,final Pint)");
/*       */         }
/*       */         
/*  6726 */         if (lockedLocalhconn != null) {
/*       */           
/*       */           try {
/*  6729 */             lockedLocalhconn.leaveCall();
/*       */           }
/*  6731 */           catch (JmqiException e) {
/*  6732 */             if (Trace.isOn) {
/*  6733 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQSETMP(Hconn,final Hmsg,MQSMPO,MQCHARV,MQPD,final int,final int,ByteBuffer,final Pint,final Pint)", (Throwable)e, 4);
/*       */             }
/*       */ 
/*       */             
/*  6737 */             jTls.lastException = e;
/*  6738 */             pCompCode.x = e.getCompCode();
/*  6739 */             pReason.x = e.getReason();
/*       */           } 
/*       */         }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  6751 */         if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES && this.inheritRRSContext && 
/*  6752 */           pReason.x == 2009) {
/*  6753 */           clearInheritedRXPB(jTls);
/*       */         }
/*       */       } 
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  6761 */     if (Trace.isOn) {
/*  6762 */       Trace.data(this, "MQSETMP(Hconn,final Hmsg,MQSMPO,MQCHARV,MQPD,final int,final int,ByteBuffer,final Pint,final Pint)", "__________");
/*  6763 */       Trace.data(this, "MQSETMP(Hconn,final Hmsg,MQSMPO,MQCHARV,MQPD,final int,final int,ByteBuffer,final Pint,final Pint)", "MQSETMP <<");
/*  6764 */       Trace.data(this, "MQSETMP(Hconn,final Hmsg,MQSMPO,MQCHARV,MQPD,final int,final int,ByteBuffer,final Pint,final Pint)", "Hconn", hConn);
/*  6765 */       Trace.data(this, "MQSETMP(Hconn,final Hmsg,MQSMPO,MQCHARV,MQPD,final int,final int,ByteBuffer,final Pint,final Pint)", "Hmsg", hmsg);
/*  6766 */       Trace.data(this, "MQSETMP(Hconn,final Hmsg,MQSMPO,MQCHARV,MQPD,final int,final int,ByteBuffer,final Pint,final Pint)", "SetPropOpts", pSetPropOpts);
/*  6767 */       Trace.data(this, "MQSETMP(Hconn,final Hmsg,MQSMPO,MQCHARV,MQPD,final int,final int,ByteBuffer,final Pint,final Pint)", "Name", pName);
/*  6768 */       Trace.data(this, "MQSETMP(Hconn,final Hmsg,MQSMPO,MQCHARV,MQPD,final int,final int,ByteBuffer,final Pint,final Pint)", "PropDesc", pPropDesc);
/*  6769 */       Trace.data(this, "MQSETMP(Hconn,final Hmsg,MQSMPO,MQCHARV,MQPD,final int,final int,ByteBuffer,final Pint,final Pint)", "Type", Integer.toString(Type));
/*  6770 */       Trace.data(this, "MQSETMP(Hconn,final Hmsg,MQSMPO,MQCHARV,MQPD,final int,final int,ByteBuffer,final Pint,final Pint)", "ValueLength", Integer.toString(ValueLength));
/*       */       
/*  6772 */       Trace.data(this, "MQSETMP(Hconn,final Hmsg,MQSMPO,MQCHARV,MQPD,final int,final int,ByteBuffer,final Pint,final Pint)", "CompCode", pCompCode);
/*  6773 */       Trace.data(this, "MQSETMP(Hconn,final Hmsg,MQSMPO,MQCHARV,MQPD,final int,final int,ByteBuffer,final Pint,final Pint)", "Reason", pReason);
/*  6774 */       Trace.data(this, "MQSETMP(Hconn,final Hmsg,MQSMPO,MQCHARV,MQPD,final int,final int,ByteBuffer,final Pint,final Pint)", "__________");
/*       */     } 
/*  6776 */     if (Trace.isOn) {
/*  6777 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQSETMP(Hconn,final Hmsg,MQSMPO,MQCHARV,MQPD,final int,final int,ByteBuffer,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQINQMP(Hconn hConn, Hmsg hmsg, MQIMPO pInqPropOpts, MQCHARV pName, MQPD pPropDesc, final Pint pType, final int ValueLength, ByteBuffer pValue, final Pint pDataLength, final Pint pCompCode, final Pint pReason) {
/*  6812 */     if (Trace.isOn) {
/*  6813 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQINQMP(Hconn,final Hmsg,MQIMPO,MQCHARV,MQPD,final Pint,final int,ByteBuffer,final Pint,final Pint,final Pint)", new Object[] { hConn, hmsg, pInqPropOpts, pName, pPropDesc, pType, 
/*       */ 
/*       */             
/*  6816 */             Integer.valueOf(ValueLength), pValue, pDataLength, pCompCode, pReason });
/*       */     }
/*  6818 */     String fid = "MQINQMP(Hconn,final Hmsg,MQIMPO,MQCHARV,MQPD,final Pint,final int,ByteBuffer,final Pint,final Pint,final Pint)";
/*       */     
/*  6820 */     if (Trace.isOn) {
/*  6821 */       Trace.data(this, "MQINQMP(Hconn,final Hmsg,MQIMPO,MQCHARV,MQPD,final Pint,final int,ByteBuffer,final Pint,final Pint,final Pint)", "__________");
/*  6822 */       Trace.data(this, "MQINQMP(Hconn,final Hmsg,MQIMPO,MQCHARV,MQPD,final Pint,final int,ByteBuffer,final Pint,final Pint,final Pint)", "MQINQMP >>");
/*  6823 */       Trace.data(this, "MQINQMP(Hconn,final Hmsg,MQIMPO,MQCHARV,MQPD,final Pint,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Hconn", hConn);
/*  6824 */       Trace.data(this, "MQINQMP(Hconn,final Hmsg,MQIMPO,MQCHARV,MQPD,final Pint,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Hmsg", hmsg);
/*  6825 */       Trace.data(this, "MQINQMP(Hconn,final Hmsg,MQIMPO,MQCHARV,MQPD,final Pint,final int,ByteBuffer,final Pint,final Pint,final Pint)", "InqPropOpts", pInqPropOpts);
/*  6826 */       Trace.data(this, "MQINQMP(Hconn,final Hmsg,MQIMPO,MQCHARV,MQPD,final Pint,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Name", pName);
/*  6827 */       Trace.data(this, "MQINQMP(Hconn,final Hmsg,MQIMPO,MQCHARV,MQPD,final Pint,final int,ByteBuffer,final Pint,final Pint,final Pint)", "PropDesc", pPropDesc);
/*  6828 */       Trace.data(this, "MQINQMP(Hconn,final Hmsg,MQIMPO,MQCHARV,MQPD,final Pint,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Type", pType);
/*  6829 */       Trace.data(this, "MQINQMP(Hconn,final Hmsg,MQIMPO,MQCHARV,MQPD,final Pint,final int,ByteBuffer,final Pint,final Pint,final Pint)", "ValueLength", Integer.toString(ValueLength));
/*       */       
/*  6831 */       Trace.data(this, "MQINQMP(Hconn,final Hmsg,MQIMPO,MQCHARV,MQPD,final Pint,final int,ByteBuffer,final Pint,final Pint,final Pint)", "DataLength", pDataLength);
/*  6832 */       Trace.data(this, "MQINQMP(Hconn,final Hmsg,MQIMPO,MQCHARV,MQPD,final Pint,final int,ByteBuffer,final Pint,final Pint,final Pint)", "CompCode", pCompCode);
/*  6833 */       Trace.data(this, "MQINQMP(Hconn,final Hmsg,MQIMPO,MQCHARV,MQPD,final Pint,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Reason", pReason);
/*  6834 */       Trace.data(this, "MQINQMP(Hconn,final Hmsg,MQIMPO,MQCHARV,MQPD,final Pint,final int,ByteBuffer,final Pint,final Pint,final Pint)", "__________");
/*       */     } 
/*       */     
/*  6837 */     pCompCode.x = 0;
/*  6838 */     pReason.x = 0;
/*       */ 
/*       */ 
/*       */     
/*  6842 */     if (ValueLength > 0) {
/*  6843 */       if (pValue == null) {
/*  6844 */         Trace.data(this, "MQINQMP(Hconn,final Hmsg,MQIMPO,MQCHARV,MQPD,final Pint,final int,ByteBuffer,final Pint,final Pint,final Pint)", "ValueLength: " + ValueLength + ", pValue is (null)", null);
/*  6845 */         pCompCode.x = 2;
/*  6846 */         pReason.x = 2005;
/*       */       }
/*  6848 */       else if (ValueLength > pValue.limit()) {
/*  6849 */         Trace.data(this, "MQINQMP(Hconn,final Hmsg,MQIMPO,MQCHARV,MQPD,final Pint,final int,ByteBuffer,final Pint,final Pint,final Pint)", "ValueLength:" + ValueLength + ", pValue.limit():" + pValue.limit(), null);
/*  6850 */         pCompCode.x = 2;
/*  6851 */         pReason.x = 2005;
/*       */       } 
/*       */     }
/*       */     
/*  6855 */     if (pReason.x == 0) {
/*       */ 
/*       */       
/*  6858 */       LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  6859 */       final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/*  6860 */       LocalHconn lockedLocalhconn = null;
/*       */       
/*       */       try {
/*       */         final byte[] rxpbBuf, optBuf, pArray;
/*  6864 */         final LocalHconn localhconn = getLocalHconn(hConn);
/*  6865 */         final byte[] qmName = localhconn.getQMNameBytes();
/*       */ 
/*       */         
/*  6868 */         RXPB rxpb = null;
/*       */         
/*  6870 */         if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/*  6871 */           rxpbBuf = null;
/*       */         } else {
/*       */           
/*  6874 */           if (this.inheritRRSContext) {
/*  6875 */             rxpb = getInheritedRxpb(tls, jTls, localhconn);
/*       */           } else {
/*       */             
/*  6878 */             rxpb = localhconn.getRxpb();
/*       */           } 
/*  6880 */           int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/*  6881 */           if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/*  6882 */             tls.rxpbBuf = new byte[rxpbLen];
/*       */           }
/*  6884 */           rxpbBuf = tls.rxpbBuf;
/*  6885 */           rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         } 
/*       */ 
/*       */ 
/*       */         
/*  6890 */         if (pInqPropOpts == null) {
/*  6891 */           optBuf = null;
/*       */         } else {
/*       */           
/*  6894 */           int impoLen = pInqPropOpts.getRequiredBufferSize(ptrSize, nativeCp);
/*  6895 */           if (tls.optBuf == null || tls.optBuf.length < impoLen) {
/*  6896 */             tls.optBuf = new byte[impoLen];
/*       */           }
/*  6898 */           optBuf = tls.optBuf;
/*  6899 */           pInqPropOpts.writeToBuffer(optBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         } 
/*       */ 
/*       */         
/*  6903 */         int nameLenVS = pName.getRequiredBufferSize(ptrSize, nativeCp);
/*  6904 */         int nameLen = MQCHARV.getSize(ptrSize);
/*  6905 */         final byte[] nameBuf = new byte[nameLen + nameLenVS];
/*  6906 */         pName.writeToBuffer(nameBuf, 0, 0, nameLen, ptrSize, swap, nativeCp);
/*       */ 
/*       */         
/*  6909 */         int pdLen = pPropDesc.getRequiredBufferSize(ptrSize, nativeCp);
/*  6910 */         final byte[] pdBuf = new byte[pdLen];
/*  6911 */         pPropDesc.writeToBuffer(pdBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */ 
/*       */ 
/*       */         
/*  6915 */         if (pValue == null) {
/*  6916 */           pArray = null;
/*       */         } else {
/*       */           
/*  6919 */           pArray = pValue.array();
/*       */         } 
/*       */ 
/*       */         
/*  6923 */         final long HmsgValue = hmsg.getLongHandle();
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  6928 */         if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hConn)) {
/*  6929 */           localhconn.syncExec(new JmqiRunnable(this.env)
/*       */               {
/*       */                 public void run()
/*       */                 {
/*  6933 */                   if (Trace.isOn) {
/*  6934 */                     Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                   }
/*       */ 
/*       */                   
/*       */                   try {
/*  6939 */                     localhconn.enterCall();
/*       */ 
/*       */                     
/*  6942 */                     boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  6943 */                     Native.MQINQMP(isClassTraced, localhconn
/*  6944 */                         .getValue(), qmName, rxpbBuf, HmsgValue, optBuf, nameBuf, pdBuf, pType, ValueLength, pArray, pDataLength, pCompCode, pReason);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */                   
/*       */                   }
/*  6958 */                   catch (JmqiException e) {
/*  6959 */                     if (Trace.isOn) {
/*  6960 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                     }
/*  6962 */                     jTls.lastException = e;
/*  6963 */                     pCompCode.x = e.getCompCode();
/*  6964 */                     pReason.x = e.getReason();
/*       */                   }
/*  6966 */                   catch (UnsatisfiedLinkError e) {
/*  6967 */                     if (Trace.isOn) {
/*  6968 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                     }
/*  6970 */                     pCompCode.x = 2;
/*  6971 */                     pReason.x = 2298;
/*       */                   } finally {
/*       */                     
/*  6974 */                     if (Trace.isOn) {
/*  6975 */                       Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                     }
/*       */                     
/*       */                     try {
/*  6979 */                       localhconn.leaveCall();
/*       */                     }
/*  6981 */                     catch (JmqiException e) {
/*  6982 */                       if (Trace.isOn) {
/*  6983 */                         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                       }
/*  6985 */                       jTls.lastException = e;
/*  6986 */                       pCompCode.x = e.getCompCode();
/*  6987 */                       pReason.x = e.getReason();
/*       */                     } 
/*       */                   } 
/*  6990 */                   if (Trace.isOn) {
/*  6991 */                     Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                 }
/*       */               });
/*       */         }
/*       */         else {
/*       */           
/*  6998 */           if (this.adapterIsRRS) {
/*  6999 */             localhconn.enterCall();
/*  7000 */             lockedLocalhconn = localhconn;
/*       */           } 
/*  7002 */           boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  7003 */           Native.MQINQMP(isClassTraced, localhconn
/*  7004 */               .getValue(), qmName, rxpbBuf, HmsgValue, optBuf, nameBuf, pdBuf, pType, ValueLength, pArray, pDataLength, pCompCode, pReason);
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  7020 */         if (pInqPropOpts != null) {
/*  7021 */           pInqPropOpts.readFromBuffer(optBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         }
/*       */         
/*  7024 */         pPropDesc.readFromBuffer(pdBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       
/*       */       }
/*  7027 */       catch (JmqiException e) {
/*  7028 */         if (Trace.isOn) {
/*  7029 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQINQMP(Hconn,final Hmsg,MQIMPO,MQCHARV,MQPD,final Pint,final int,ByteBuffer,final Pint,final Pint,final Pint)", (Throwable)e, 1);
/*       */         }
/*       */ 
/*       */         
/*  7033 */         jTls.lastException = e;
/*  7034 */         pCompCode.x = e.getCompCode();
/*  7035 */         pReason.x = e.getReason();
/*       */       }
/*  7037 */       catch (UnsatisfiedLinkError e) {
/*  7038 */         if (Trace.isOn) {
/*  7039 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQINQMP(Hconn,final Hmsg,MQIMPO,MQCHARV,MQPD,final Pint,final int,ByteBuffer,final Pint,final Pint,final Pint)", e, 2);
/*       */         }
/*       */ 
/*       */         
/*  7043 */         pCompCode.x = 2;
/*  7044 */         pReason.x = 2298;
/*       */       }
/*  7046 */       catch (Throwable e) {
/*  7047 */         if (Trace.isOn) {
/*  7048 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQINQMP(Hconn,final Hmsg,MQIMPO,MQCHARV,MQPD,final Pint,final int,ByteBuffer,final Pint,final Pint,final Pint)", e, 3);
/*       */         }
/*       */ 
/*       */         
/*  7052 */         castUnexpectedException("MQINQMP(Hconn,final Hmsg,MQIMPO,MQCHARV,MQPD,final Pint,final int,ByteBuffer,final Pint,final Pint,final Pint)", 1, e);
/*       */       } finally {
/*       */         
/*  7055 */         if (Trace.isOn) {
/*  7056 */           Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQINQMP(Hconn,final Hmsg,MQIMPO,MQCHARV,MQPD,final Pint,final int,ByteBuffer,final Pint,final Pint,final Pint)");
/*       */         }
/*       */         
/*  7059 */         if (lockedLocalhconn != null) {
/*       */           
/*       */           try {
/*  7062 */             lockedLocalhconn.leaveCall();
/*       */           }
/*  7064 */           catch (JmqiException e) {
/*  7065 */             if (Trace.isOn) {
/*  7066 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQINQMP(Hconn,final Hmsg,MQIMPO,MQCHARV,MQPD,final Pint,final int,ByteBuffer,final Pint,final Pint,final Pint)", (Throwable)e, 4);
/*       */             }
/*       */ 
/*       */             
/*  7070 */             jTls.lastException = e;
/*  7071 */             pCompCode.x = e.getCompCode();
/*  7072 */             pReason.x = e.getReason();
/*       */           } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  7084 */           if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES && this.inheritRRSContext && 
/*  7085 */             pReason.x == 2009) {
/*  7086 */             clearInheritedRXPB(jTls);
/*       */           }
/*       */         } 
/*       */       } 
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  7095 */     if (Trace.isOn) {
/*  7096 */       Trace.data(this, "MQINQMP(Hconn,final Hmsg,MQIMPO,MQCHARV,MQPD,final Pint,final int,ByteBuffer,final Pint,final Pint,final Pint)", "__________");
/*  7097 */       Trace.data(this, "MQINQMP(Hconn,final Hmsg,MQIMPO,MQCHARV,MQPD,final Pint,final int,ByteBuffer,final Pint,final Pint,final Pint)", "MQINQMP <<");
/*  7098 */       Trace.data(this, "MQINQMP(Hconn,final Hmsg,MQIMPO,MQCHARV,MQPD,final Pint,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Hconn", hConn);
/*  7099 */       Trace.data(this, "MQINQMP(Hconn,final Hmsg,MQIMPO,MQCHARV,MQPD,final Pint,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Hmsg", hmsg);
/*  7100 */       Trace.data(this, "MQINQMP(Hconn,final Hmsg,MQIMPO,MQCHARV,MQPD,final Pint,final int,ByteBuffer,final Pint,final Pint,final Pint)", "InqPropOpts", pInqPropOpts);
/*  7101 */       Trace.data(this, "MQINQMP(Hconn,final Hmsg,MQIMPO,MQCHARV,MQPD,final Pint,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Name", pName);
/*  7102 */       Trace.data(this, "MQINQMP(Hconn,final Hmsg,MQIMPO,MQCHARV,MQPD,final Pint,final int,ByteBuffer,final Pint,final Pint,final Pint)", "PropDesc", pPropDesc);
/*  7103 */       Trace.data(this, "MQINQMP(Hconn,final Hmsg,MQIMPO,MQCHARV,MQPD,final Pint,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Type", pType);
/*  7104 */       Trace.data(this, "MQINQMP(Hconn,final Hmsg,MQIMPO,MQCHARV,MQPD,final Pint,final int,ByteBuffer,final Pint,final Pint,final Pint)", "ValueLength", Integer.toString(ValueLength));
/*  7105 */       JmqiTools.traceApiBuffer(this, "MQINQMP(Hconn,final Hmsg,MQIMPO,MQCHARV,MQPD,final Pint,final int,ByteBuffer,final Pint,final Pint,final Pint)", pValue, ValueLength);
/*  7106 */       Trace.data(this, "MQINQMP(Hconn,final Hmsg,MQIMPO,MQCHARV,MQPD,final Pint,final int,ByteBuffer,final Pint,final Pint,final Pint)", "DataLength", pDataLength);
/*  7107 */       Trace.data(this, "MQINQMP(Hconn,final Hmsg,MQIMPO,MQCHARV,MQPD,final Pint,final int,ByteBuffer,final Pint,final Pint,final Pint)", "CompCode", pCompCode);
/*  7108 */       Trace.data(this, "MQINQMP(Hconn,final Hmsg,MQIMPO,MQCHARV,MQPD,final Pint,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Reason", pReason);
/*  7109 */       Trace.data(this, "MQINQMP(Hconn,final Hmsg,MQIMPO,MQCHARV,MQPD,final Pint,final int,ByteBuffer,final Pint,final Pint,final Pint)", "__________");
/*       */     } 
/*  7111 */     if (Trace.isOn) {
/*  7112 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQINQMP(Hconn,final Hmsg,MQIMPO,MQCHARV,MQPD,final Pint,final int,ByteBuffer,final Pint,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQDLTMP(Hconn hConn, Hmsg hmsg, MQDMPO pDltPropOpts, MQCHARV pName, final Pint pCompCode, final Pint pReason) {
/*  7130 */     if (Trace.isOn) {
/*  7131 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQDLTMP(Hconn,final Hmsg,MQDMPO,MQCHARV,final Pint,final Pint)", new Object[] { hConn, hmsg, pDltPropOpts, pName, pCompCode, pReason });
/*       */     }
/*       */     
/*  7134 */     String fid = "MQDLTMP(Hconn,final Hmsg,MQDMPO,MQCHARV,final Pint,final Pint)";
/*       */     
/*  7136 */     if (Trace.isOn) {
/*  7137 */       Trace.data(this, "MQDLTMP(Hconn,final Hmsg,MQDMPO,MQCHARV,final Pint,final Pint)", "__________");
/*  7138 */       Trace.data(this, "MQDLTMP(Hconn,final Hmsg,MQDMPO,MQCHARV,final Pint,final Pint)", "MQDLTMP >>");
/*  7139 */       Trace.data(this, "MQDLTMP(Hconn,final Hmsg,MQDMPO,MQCHARV,final Pint,final Pint)", "Hconn", hConn);
/*  7140 */       Trace.data(this, "MQDLTMP(Hconn,final Hmsg,MQDMPO,MQCHARV,final Pint,final Pint)", "Hmsg", hmsg);
/*  7141 */       Trace.data(this, "MQDLTMP(Hconn,final Hmsg,MQDMPO,MQCHARV,final Pint,final Pint)", "DltPropOpts", pDltPropOpts);
/*  7142 */       Trace.data(this, "MQDLTMP(Hconn,final Hmsg,MQDMPO,MQCHARV,final Pint,final Pint)", "Name", pName);
/*  7143 */       Trace.data(this, "MQDLTMP(Hconn,final Hmsg,MQDMPO,MQCHARV,final Pint,final Pint)", "CompCode", pCompCode);
/*  7144 */       Trace.data(this, "MQDLTMP(Hconn,final Hmsg,MQDMPO,MQCHARV,final Pint,final Pint)", "Reason", pReason);
/*  7145 */       Trace.data(this, "MQDLTMP(Hconn,final Hmsg,MQDMPO,MQCHARV,final Pint,final Pint)", "__________");
/*       */     } 
/*       */ 
/*       */     
/*  7149 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  7150 */     final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/*  7151 */     LocalHconn lockedLocalhconn = null;
/*       */     
/*       */     try {
/*       */       final byte[] rxpbBuf, optBuf;
/*  7155 */       final LocalHconn localhconn = getLocalHconn(hConn);
/*  7156 */       final byte[] qmName = localhconn.getQMNameBytes();
/*       */ 
/*       */       
/*  7159 */       RXPB rxpb = null;
/*       */       
/*  7161 */       if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/*  7162 */         rxpbBuf = null;
/*       */       } else {
/*       */         
/*  7165 */         if (this.inheritRRSContext) {
/*  7166 */           rxpb = getInheritedRxpb(tls, jTls, localhconn);
/*       */         } else {
/*       */           
/*  7169 */           rxpb = localhconn.getRxpb();
/*       */         } 
/*  7171 */         int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/*  7172 */         if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/*  7173 */           tls.rxpbBuf = new byte[rxpbLen];
/*       */         }
/*  7175 */         rxpbBuf = tls.rxpbBuf;
/*  7176 */         rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/*  7181 */       if (pDltPropOpts == null) {
/*  7182 */         optBuf = null;
/*       */       } else {
/*       */         
/*  7185 */         int dmpoLen = pDltPropOpts.getRequiredBufferSize(ptrSize, nativeCp);
/*  7186 */         if (tls.optBuf == null || tls.optBuf.length < dmpoLen) {
/*  7187 */           tls.optBuf = new byte[dmpoLen];
/*       */         }
/*  7189 */         optBuf = tls.optBuf;
/*  7190 */         pDltPropOpts.writeToBuffer(optBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */       
/*  7194 */       int nameLenVS = pName.getRequiredBufferSize(ptrSize, nativeCp);
/*  7195 */       int nameLen = MQCHARV.getSize(ptrSize);
/*  7196 */       final byte[] nameBuf = new byte[nameLen + nameLenVS];
/*  7197 */       pName.writeToBuffer(nameBuf, 0, 0, nameLen, ptrSize, swap, nativeCp);
/*       */ 
/*       */       
/*  7200 */       final long HmsgValue = hmsg.getLongHandle();
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  7205 */       if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hConn)) {
/*  7206 */         localhconn.syncExec(new JmqiRunnable(this.env)
/*       */             {
/*       */               public void run()
/*       */               {
/*  7210 */                 if (Trace.isOn) {
/*  7211 */                   Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                 }
/*       */ 
/*       */                 
/*       */                 try {
/*  7216 */                   localhconn.enterCall();
/*       */ 
/*       */                   
/*  7219 */                   boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  7220 */                   Native.MQDLTMP(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, HmsgValue, optBuf, nameBuf, pCompCode, pReason);
/*       */                 }
/*  7222 */                 catch (JmqiException e) {
/*  7223 */                   if (Trace.isOn) {
/*  7224 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                   }
/*  7226 */                   jTls.lastException = e;
/*  7227 */                   pCompCode.x = e.getCompCode();
/*  7228 */                   pReason.x = e.getReason();
/*       */                 }
/*  7230 */                 catch (UnsatisfiedLinkError e) {
/*  7231 */                   if (Trace.isOn) {
/*  7232 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                   }
/*  7234 */                   pCompCode.x = 2;
/*  7235 */                   pReason.x = 2298;
/*       */                 } finally {
/*       */                   
/*  7238 */                   if (Trace.isOn) {
/*  7239 */                     Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                   
/*       */                   try {
/*  7243 */                     localhconn.leaveCall();
/*       */                   }
/*  7245 */                   catch (JmqiException e) {
/*  7246 */                     if (Trace.isOn) {
/*  7247 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                     }
/*  7249 */                     jTls.lastException = e;
/*  7250 */                     pCompCode.x = e.getCompCode();
/*  7251 */                     pReason.x = e.getReason();
/*       */                   } 
/*       */                 } 
/*  7254 */                 if (Trace.isOn) {
/*  7255 */                   Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                 }
/*       */               }
/*       */             });
/*       */       }
/*       */       else {
/*       */         
/*  7262 */         if (this.adapterIsRRS) {
/*  7263 */           localhconn.enterCall();
/*  7264 */           lockedLocalhconn = localhconn;
/*       */         } 
/*  7266 */         boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  7267 */         Native.MQDLTMP(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, HmsgValue, optBuf, nameBuf, pCompCode, pReason);
/*       */       }
/*       */     
/*  7270 */     } catch (JmqiException e) {
/*  7271 */       if (Trace.isOn) {
/*  7272 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQDLTMP(Hconn,final Hmsg,MQDMPO,MQCHARV,final Pint,final Pint)", (Throwable)e, 1);
/*       */       }
/*       */       
/*  7275 */       jTls.lastException = e;
/*  7276 */       pCompCode.x = e.getCompCode();
/*  7277 */       pReason.x = e.getReason();
/*       */     }
/*  7279 */     catch (UnsatisfiedLinkError e) {
/*  7280 */       if (Trace.isOn) {
/*  7281 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQDLTMP(Hconn,final Hmsg,MQDMPO,MQCHARV,final Pint,final Pint)", e, 2);
/*       */       }
/*       */       
/*  7284 */       pCompCode.x = 2;
/*  7285 */       pReason.x = 2298;
/*       */     }
/*  7287 */     catch (Throwable e) {
/*  7288 */       if (Trace.isOn) {
/*  7289 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQDLTMP(Hconn,final Hmsg,MQDMPO,MQCHARV,final Pint,final Pint)", e, 3);
/*       */       }
/*       */       
/*  7292 */       castUnexpectedException("MQDLTMP(Hconn,final Hmsg,MQDMPO,MQCHARV,final Pint,final Pint)", 1, e);
/*       */     } finally {
/*       */       
/*  7295 */       if (Trace.isOn) {
/*  7296 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQDLTMP(Hconn,final Hmsg,MQDMPO,MQCHARV,final Pint,final Pint)");
/*       */       }
/*       */       
/*  7299 */       if (lockedLocalhconn != null) {
/*       */         
/*       */         try {
/*  7302 */           lockedLocalhconn.leaveCall();
/*       */         }
/*  7304 */         catch (JmqiException e) {
/*  7305 */           if (Trace.isOn) {
/*  7306 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQDLTMP(Hconn,final Hmsg,MQDMPO,MQCHARV,final Pint,final Pint)", (Throwable)e, 4);
/*       */           }
/*       */           
/*  7309 */           jTls.lastException = e;
/*  7310 */           pCompCode.x = e.getCompCode();
/*  7311 */           pReason.x = e.getReason();
/*       */         } 
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  7320 */       if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES && this.inheritRRSContext && 
/*  7321 */         pReason.x == 2009) {
/*  7322 */         clearInheritedRXPB(jTls);
/*       */       }
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  7329 */     if (Trace.isOn) {
/*  7330 */       Trace.data(this, "MQDLTMP(Hconn,final Hmsg,MQDMPO,MQCHARV,final Pint,final Pint)", "__________");
/*  7331 */       Trace.data(this, "MQDLTMP(Hconn,final Hmsg,MQDMPO,MQCHARV,final Pint,final Pint)", "MQDLTMP <<");
/*  7332 */       Trace.data(this, "MQDLTMP(Hconn,final Hmsg,MQDMPO,MQCHARV,final Pint,final Pint)", "Hconn", hConn);
/*  7333 */       Trace.data(this, "MQDLTMP(Hconn,final Hmsg,MQDMPO,MQCHARV,final Pint,final Pint)", "Hmsg", hmsg);
/*  7334 */       Trace.data(this, "MQDLTMP(Hconn,final Hmsg,MQDMPO,MQCHARV,final Pint,final Pint)", "DltPropOpts", pDltPropOpts);
/*  7335 */       Trace.data(this, "MQDLTMP(Hconn,final Hmsg,MQDMPO,MQCHARV,final Pint,final Pint)", "Name", pName);
/*  7336 */       Trace.data(this, "MQDLTMP(Hconn,final Hmsg,MQDMPO,MQCHARV,final Pint,final Pint)", "CompCode", pCompCode);
/*  7337 */       Trace.data(this, "MQDLTMP(Hconn,final Hmsg,MQDMPO,MQCHARV,final Pint,final Pint)", "Reason", pReason);
/*  7338 */       Trace.data(this, "MQDLTMP(Hconn,final Hmsg,MQDMPO,MQCHARV,final Pint,final Pint)", "__________");
/*       */     } 
/*  7340 */     if (Trace.isOn) {
/*  7341 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQDLTMP(Hconn,final Hmsg,MQDMPO,MQCHARV,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQMHBUF(Hconn hConn, Hmsg hmsg, MQMHBO pMsgHBufOpts, MQCHARV pName, MQMD pMsgDesc, final int BufferLength, ByteBuffer pBuffer, final Pint pDataLength, final Pint pCompCode, final Pint pReason) {
/*  7378 */     if (Trace.isOn) {
/*  7379 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQMHBUF(Hconn,final Hmsg,MQMHBO,MQCHARV,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", new Object[] { hConn, hmsg, pMsgHBufOpts, pName, pMsgDesc, 
/*       */             
/*  7381 */             Integer.valueOf(BufferLength), pBuffer, pDataLength, pCompCode, pReason });
/*       */     }
/*       */     
/*  7384 */     String fid = "MQMHBUF(Hconn,final Hmsg,MQMHBO,MQCHARV,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)";
/*       */     
/*  7386 */     if (Trace.isOn) {
/*  7387 */       Trace.data(this, "MQMHBUF(Hconn,final Hmsg,MQMHBO,MQCHARV,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "__________");
/*  7388 */       Trace.data(this, "MQMHBUF(Hconn,final Hmsg,MQMHBO,MQCHARV,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "MQMHBUF >>");
/*  7389 */       Trace.data(this, "MQMHBUF(Hconn,final Hmsg,MQMHBO,MQCHARV,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Hconn", hConn);
/*  7390 */       Trace.data(this, "MQMHBUF(Hconn,final Hmsg,MQMHBO,MQCHARV,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Hmsg", hmsg);
/*  7391 */       Trace.data(this, "MQMHBUF(Hconn,final Hmsg,MQMHBO,MQCHARV,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "MsgHBufOpts", pMsgHBufOpts);
/*  7392 */       Trace.data(this, "MQMHBUF(Hconn,final Hmsg,MQMHBO,MQCHARV,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Name", pName);
/*  7393 */       Trace.data(this, "MQMHBUF(Hconn,final Hmsg,MQMHBO,MQCHARV,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "MsgDesc", pMsgDesc);
/*  7394 */       Trace.data(this, "MQMHBUF(Hconn,final Hmsg,MQMHBO,MQCHARV,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "BufferLength", Integer.toString(BufferLength));
/*       */       
/*  7396 */       Trace.data(this, "MQMHBUF(Hconn,final Hmsg,MQMHBO,MQCHARV,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "DataLength", pDataLength);
/*  7397 */       Trace.data(this, "MQMHBUF(Hconn,final Hmsg,MQMHBO,MQCHARV,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "CompCode", pCompCode);
/*  7398 */       Trace.data(this, "MQMHBUF(Hconn,final Hmsg,MQMHBO,MQCHARV,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Reason", pReason);
/*  7399 */       Trace.data(this, "MQMHBUF(Hconn,final Hmsg,MQMHBO,MQCHARV,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "__________");
/*       */     } 
/*       */     
/*  7402 */     pCompCode.x = 0;
/*  7403 */     pReason.x = 0;
/*       */ 
/*       */ 
/*       */     
/*  7407 */     if (BufferLength > 0) {
/*  7408 */       if (pBuffer == null) {
/*  7409 */         Trace.data(this, "MQMHBUF(Hconn,final Hmsg,MQMHBO,MQCHARV,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "BufferLength: " + BufferLength + ", pBuffer is (null)", null);
/*  7410 */         pCompCode.x = 2;
/*  7411 */         pReason.x = 2005;
/*       */       }
/*  7413 */       else if (BufferLength > pBuffer.limit()) {
/*  7414 */         Trace.data(this, "MQMHBUF(Hconn,final Hmsg,MQMHBO,MQCHARV,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "BufferLength:" + BufferLength + ", pBuffer.limit():" + pBuffer.limit(), null);
/*  7415 */         pCompCode.x = 2;
/*  7416 */         pReason.x = 2005;
/*       */       } 
/*       */     }
/*       */     
/*  7420 */     if (pReason.x == 0) {
/*       */ 
/*       */       
/*  7423 */       LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  7424 */       final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/*  7425 */       LocalHconn lockedLocalhconn = null;
/*       */       
/*       */       try {
/*       */         final byte[] rxpbBuf, optBuf, mqmdBuf, pArray;
/*  7429 */         final LocalHconn localhconn = getLocalHconn(hConn);
/*  7430 */         final byte[] qmName = localhconn.getQMNameBytes();
/*       */ 
/*       */         
/*  7433 */         RXPB rxpb = null;
/*       */         
/*  7435 */         if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/*  7436 */           rxpbBuf = null;
/*       */         } else {
/*       */           
/*  7439 */           if (this.inheritRRSContext) {
/*  7440 */             rxpb = getInheritedRxpb(tls, jTls, localhconn);
/*       */           } else {
/*       */             
/*  7443 */             rxpb = localhconn.getRxpb();
/*       */           } 
/*  7445 */           int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/*  7446 */           if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/*  7447 */             tls.rxpbBuf = new byte[rxpbLen];
/*       */           }
/*  7449 */           rxpbBuf = tls.rxpbBuf;
/*  7450 */           rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         } 
/*       */ 
/*       */ 
/*       */         
/*  7455 */         if (pMsgHBufOpts == null) {
/*  7456 */           optBuf = null;
/*       */         } else {
/*       */           
/*  7459 */           int mhboLen = pMsgHBufOpts.getRequiredBufferSize(ptrSize, nativeCp);
/*  7460 */           if (tls.optBuf == null || tls.optBuf.length < mhboLen) {
/*  7461 */             tls.optBuf = new byte[mhboLen];
/*       */           }
/*  7463 */           optBuf = tls.optBuf;
/*  7464 */           pMsgHBufOpts.writeToBuffer(optBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         } 
/*       */ 
/*       */         
/*  7468 */         int nameLenVS = pName.getRequiredBufferSize(ptrSize, nativeCp);
/*  7469 */         int nameLen = MQCHARV.getSize(ptrSize);
/*  7470 */         final byte[] nameBuf = new byte[nameLen + nameLenVS];
/*  7471 */         pName.writeToBuffer(nameBuf, 0, 0, nameLen, ptrSize, swap, nativeCp);
/*       */ 
/*       */ 
/*       */         
/*  7475 */         if (pMsgDesc == null) {
/*  7476 */           mqmdBuf = null;
/*       */         } else {
/*       */           
/*  7479 */           int mqmdLen = pMsgDesc.getRequiredBufferSize(ptrSize, nativeCp);
/*  7480 */           if (tls.mqmdBuf == null || tls.mqmdBuf.length < mqmdLen) {
/*  7481 */             tls.mqmdBuf = new byte[mqmdLen];
/*       */           }
/*  7483 */           mqmdBuf = tls.mqmdBuf;
/*  7484 */           pMsgDesc.writeToBuffer(mqmdBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         } 
/*       */ 
/*       */ 
/*       */         
/*  7489 */         if (pBuffer == null) {
/*  7490 */           pArray = null;
/*       */         } else {
/*       */           
/*  7493 */           pArray = pBuffer.array();
/*       */         } 
/*       */ 
/*       */         
/*  7497 */         final long HmsgValue = hmsg.getLongHandle();
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  7502 */         if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hConn)) {
/*  7503 */           localhconn.syncExec(new JmqiRunnable(this.env)
/*       */               {
/*       */                 public void run()
/*       */                 {
/*  7507 */                   if (Trace.isOn) {
/*  7508 */                     Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                   }
/*       */ 
/*       */                   
/*       */                   try {
/*  7513 */                     localhconn.enterCall();
/*       */ 
/*       */                     
/*  7516 */                     boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  7517 */                     Native.MQMHBUF(isClassTraced, localhconn
/*  7518 */                         .getValue(), qmName, rxpbBuf, HmsgValue, optBuf, nameBuf, mqmdBuf, BufferLength, pArray, pDataLength, pCompCode, pReason);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */                   
/*       */                   }
/*  7531 */                   catch (JmqiException e) {
/*  7532 */                     if (Trace.isOn) {
/*  7533 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                     }
/*  7535 */                     jTls.lastException = e;
/*  7536 */                     pCompCode.x = e.getCompCode();
/*  7537 */                     pReason.x = e.getReason();
/*       */                   }
/*  7539 */                   catch (UnsatisfiedLinkError e) {
/*  7540 */                     if (Trace.isOn) {
/*  7541 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                     }
/*  7543 */                     pCompCode.x = 2;
/*  7544 */                     pReason.x = 2298;
/*       */                   } finally {
/*       */                     
/*  7547 */                     if (Trace.isOn) {
/*  7548 */                       Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                     }
/*       */                     
/*       */                     try {
/*  7552 */                       localhconn.leaveCall();
/*       */                     }
/*  7554 */                     catch (JmqiException e) {
/*  7555 */                       if (Trace.isOn) {
/*  7556 */                         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                       }
/*  7558 */                       jTls.lastException = e;
/*  7559 */                       pCompCode.x = e.getCompCode();
/*  7560 */                       pReason.x = e.getReason();
/*       */                     } 
/*       */                   } 
/*  7563 */                   if (Trace.isOn) {
/*  7564 */                     Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                 }
/*       */               });
/*       */         }
/*       */         else {
/*       */           
/*  7571 */           if (this.adapterIsRRS) {
/*  7572 */             localhconn.enterCall();
/*  7573 */             lockedLocalhconn = localhconn;
/*       */           } 
/*  7575 */           boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  7576 */           Native.MQMHBUF(isClassTraced, localhconn
/*  7577 */               .getValue(), qmName, rxpbBuf, HmsgValue, optBuf, nameBuf, mqmdBuf, BufferLength, pArray, pDataLength, pCompCode, pReason);
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  7592 */         if (pMsgDesc != null) {
/*  7593 */           pMsgDesc.readFromBuffer(mqmdBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         
/*       */         }
/*       */       }
/*  7597 */       catch (JmqiException e) {
/*  7598 */         if (Trace.isOn) {
/*  7599 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQMHBUF(Hconn,final Hmsg,MQMHBO,MQCHARV,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", (Throwable)e, 1);
/*       */         }
/*       */ 
/*       */         
/*  7603 */         jTls.lastException = e;
/*  7604 */         pCompCode.x = e.getCompCode();
/*  7605 */         pReason.x = e.getReason();
/*       */       }
/*  7607 */       catch (UnsatisfiedLinkError e) {
/*  7608 */         if (Trace.isOn) {
/*  7609 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQMHBUF(Hconn,final Hmsg,MQMHBO,MQCHARV,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", e, 2);
/*       */         }
/*       */ 
/*       */         
/*  7613 */         pCompCode.x = 2;
/*  7614 */         pReason.x = 2298;
/*       */       }
/*  7616 */       catch (Throwable e) {
/*  7617 */         if (Trace.isOn) {
/*  7618 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQMHBUF(Hconn,final Hmsg,MQMHBO,MQCHARV,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", e, 3);
/*       */         }
/*       */ 
/*       */         
/*  7622 */         castUnexpectedException("MQMHBUF(Hconn,final Hmsg,MQMHBO,MQCHARV,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", 1, e);
/*       */       } finally {
/*       */         
/*  7625 */         if (Trace.isOn) {
/*  7626 */           Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQMHBUF(Hconn,final Hmsg,MQMHBO,MQCHARV,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)");
/*       */         }
/*       */         
/*  7629 */         if (lockedLocalhconn != null) {
/*       */           
/*       */           try {
/*  7632 */             lockedLocalhconn.leaveCall();
/*       */           }
/*  7634 */           catch (JmqiException e) {
/*  7635 */             if (Trace.isOn) {
/*  7636 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQMHBUF(Hconn,final Hmsg,MQMHBO,MQCHARV,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", (Throwable)e, 4);
/*       */             }
/*       */ 
/*       */             
/*  7640 */             jTls.lastException = e;
/*  7641 */             pCompCode.x = e.getCompCode();
/*  7642 */             pReason.x = e.getReason();
/*       */           } 
/*       */         }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  7654 */         if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES && this.inheritRRSContext && 
/*  7655 */           pReason.x == 2009) {
/*  7656 */           clearInheritedRXPB(jTls);
/*       */         }
/*       */       } 
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  7664 */     if (Trace.isOn) {
/*  7665 */       Trace.data(this, "MQMHBUF(Hconn,final Hmsg,MQMHBO,MQCHARV,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "__________");
/*  7666 */       Trace.data(this, "MQMHBUF(Hconn,final Hmsg,MQMHBO,MQCHARV,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "MQMHBUF <<");
/*  7667 */       Trace.data(this, "MQMHBUF(Hconn,final Hmsg,MQMHBO,MQCHARV,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Hconn", hConn);
/*  7668 */       Trace.data(this, "MQMHBUF(Hconn,final Hmsg,MQMHBO,MQCHARV,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Hmsg", hmsg);
/*  7669 */       Trace.data(this, "MQMHBUF(Hconn,final Hmsg,MQMHBO,MQCHARV,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "MsgHBufOpts", pMsgHBufOpts);
/*  7670 */       Trace.data(this, "MQMHBUF(Hconn,final Hmsg,MQMHBO,MQCHARV,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Name", pName);
/*  7671 */       Trace.data(this, "MQMHBUF(Hconn,final Hmsg,MQMHBO,MQCHARV,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "MsgDesc", pMsgDesc);
/*  7672 */       Trace.data(this, "MQMHBUF(Hconn,final Hmsg,MQMHBO,MQCHARV,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "BufferLength", Integer.toString(BufferLength));
/*  7673 */       JmqiTools.traceApiBuffer(this, "MQMHBUF(Hconn,final Hmsg,MQMHBO,MQCHARV,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", pBuffer, BufferLength);
/*  7674 */       Trace.data(this, "MQMHBUF(Hconn,final Hmsg,MQMHBO,MQCHARV,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "DataLength", pDataLength);
/*  7675 */       Trace.data(this, "MQMHBUF(Hconn,final Hmsg,MQMHBO,MQCHARV,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "CompCode", pCompCode);
/*  7676 */       Trace.data(this, "MQMHBUF(Hconn,final Hmsg,MQMHBO,MQCHARV,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Reason", pReason);
/*  7677 */       Trace.data(this, "MQMHBUF(Hconn,final Hmsg,MQMHBO,MQCHARV,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "__________");
/*       */     } 
/*  7679 */     if (Trace.isOn) {
/*  7680 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQMHBUF(Hconn,final Hmsg,MQMHBO,MQCHARV,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void MQBUFMH(Hconn hConn, Hmsg hmsg, MQBMHO pBufMsgHOpts, MQMD pMsgDesc, final int BufferLength, ByteBuffer pBuffer, final Pint pDataLength, final Pint pCompCode, final Pint pReason) {
/*  7714 */     if (Trace.isOn) {
/*  7715 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQBUFMH(Hconn,final Hmsg,MQBMHO,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", new Object[] { hConn, hmsg, pBufMsgHOpts, pMsgDesc, 
/*       */             
/*  7717 */             Integer.valueOf(BufferLength), pBuffer, pDataLength, pCompCode, pReason });
/*       */     }
/*  7719 */     String fid = "MQBUFMH(Hconn,final Hmsg,MQBMHO,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)";
/*       */     
/*  7721 */     if (Trace.isOn) {
/*  7722 */       Trace.data(this, "MQBUFMH(Hconn,final Hmsg,MQBMHO,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "__________");
/*  7723 */       Trace.data(this, "MQBUFMH(Hconn,final Hmsg,MQBMHO,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "MQBUFMH >>");
/*  7724 */       Trace.data(this, "MQBUFMH(Hconn,final Hmsg,MQBMHO,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Hconn", hConn);
/*  7725 */       Trace.data(this, "MQBUFMH(Hconn,final Hmsg,MQBMHO,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Hmsg", hmsg);
/*  7726 */       Trace.data(this, "MQBUFMH(Hconn,final Hmsg,MQBMHO,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "BufMsgHOpts", pBufMsgHOpts);
/*  7727 */       Trace.data(this, "MQBUFMH(Hconn,final Hmsg,MQBMHO,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "MsgDesc", pMsgDesc);
/*  7728 */       Trace.data(this, "MQBUFMH(Hconn,final Hmsg,MQBMHO,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "BufferLength", Integer.toString(BufferLength));
/*  7729 */       if (pBuffer == null) {
/*  7730 */         Trace.data(this, "MQBUFMH(Hconn,final Hmsg,MQBMHO,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "buffer is (null)");
/*       */       } else {
/*       */         
/*  7733 */         JmqiTools.traceApiBuffer(this, "MQBUFMH(Hconn,final Hmsg,MQBMHO,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", pBuffer, pBuffer.limit());
/*       */       } 
/*  7735 */       Trace.data(this, "MQBUFMH(Hconn,final Hmsg,MQBMHO,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "DataLength", pDataLength);
/*  7736 */       Trace.data(this, "MQBUFMH(Hconn,final Hmsg,MQBMHO,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "CompCode", pCompCode);
/*  7737 */       Trace.data(this, "MQBUFMH(Hconn,final Hmsg,MQBMHO,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Reason", pReason);
/*  7738 */       Trace.data(this, "MQBUFMH(Hconn,final Hmsg,MQBMHO,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "__________");
/*       */     } 
/*       */     
/*  7741 */     pCompCode.x = 0;
/*  7742 */     pReason.x = 0;
/*       */ 
/*       */ 
/*       */     
/*  7746 */     if (BufferLength > 0) {
/*  7747 */       if (pBuffer == null) {
/*  7748 */         Trace.data(this, "MQBUFMH(Hconn,final Hmsg,MQBMHO,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "BufferLength: " + BufferLength + ", pBuffer is (null)", null);
/*  7749 */         pCompCode.x = 2;
/*  7750 */         pReason.x = 2005;
/*       */       }
/*  7752 */       else if (BufferLength > pBuffer.limit()) {
/*  7753 */         Trace.data(this, "MQBUFMH(Hconn,final Hmsg,MQBMHO,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "BufferLength:" + BufferLength + ", pBuffer.limit():" + pBuffer.limit(), null);
/*  7754 */         pCompCode.x = 2;
/*  7755 */         pReason.x = 2005;
/*       */       }
/*       */     
/*  7758 */     } else if (BufferLength < 0) {
/*  7759 */       Trace.data(this, "MQBUFMH(Hconn,final Hmsg,MQBMHO,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "BufferLength is negative: " + BufferLength, null);
/*  7760 */       pCompCode.x = 2;
/*  7761 */       pReason.x = 2005;
/*       */     } 
/*       */     
/*  7764 */     if (pReason.x == 0) {
/*       */ 
/*       */       
/*  7767 */       LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  7768 */       final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/*  7769 */       LocalHconn lockedLocalhconn = null;
/*       */       
/*       */       try {
/*       */         final byte[] rxpbBuf, optBuf, mqmdBuf, pArray;
/*  7773 */         final LocalHconn localhconn = getLocalHconn(hConn);
/*  7774 */         final byte[] qmName = localhconn.getQMNameBytes();
/*       */ 
/*       */         
/*  7777 */         RXPB rxpb = null;
/*       */         
/*  7779 */         if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/*  7780 */           rxpbBuf = null;
/*       */         } else {
/*       */           
/*  7783 */           if (this.inheritRRSContext) {
/*  7784 */             rxpb = getInheritedRxpb(tls, jTls, localhconn);
/*       */           } else {
/*       */             
/*  7787 */             rxpb = localhconn.getRxpb();
/*       */           } 
/*  7789 */           int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/*  7790 */           if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/*  7791 */             tls.rxpbBuf = new byte[rxpbLen];
/*       */           }
/*  7793 */           rxpbBuf = tls.rxpbBuf;
/*  7794 */           rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         } 
/*       */ 
/*       */ 
/*       */         
/*  7799 */         if (pBufMsgHOpts == null) {
/*  7800 */           optBuf = null;
/*       */         } else {
/*       */           
/*  7803 */           int bmhoLen = pBufMsgHOpts.getRequiredBufferSize(ptrSize, nativeCp);
/*  7804 */           if (tls.optBuf == null || tls.optBuf.length < bmhoLen) {
/*  7805 */             tls.optBuf = new byte[bmhoLen];
/*       */           }
/*  7807 */           optBuf = tls.optBuf;
/*  7808 */           pBufMsgHOpts.writeToBuffer(optBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         } 
/*       */ 
/*       */ 
/*       */         
/*  7813 */         if (pMsgDesc == null) {
/*  7814 */           mqmdBuf = null;
/*       */         } else {
/*       */           
/*  7817 */           int mqmdLen = pMsgDesc.getRequiredBufferSize(ptrSize, nativeCp);
/*  7818 */           if (tls.mqmdBuf == null || tls.mqmdBuf.length < mqmdLen) {
/*  7819 */             tls.mqmdBuf = new byte[mqmdLen];
/*       */           }
/*  7821 */           mqmdBuf = tls.mqmdBuf;
/*  7822 */           pMsgDesc.writeToBuffer(mqmdBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         } 
/*       */ 
/*       */ 
/*       */         
/*  7827 */         if (pBuffer == null) {
/*  7828 */           pArray = null;
/*       */         } else {
/*       */           
/*  7831 */           pArray = pBuffer.array();
/*       */         } 
/*       */ 
/*       */         
/*  7835 */         final long HmsgValue = hmsg.getLongHandle();
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  7840 */         if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hConn)) {
/*  7841 */           localhconn.syncExec(new JmqiRunnable(this.env)
/*       */               {
/*       */                 public void run()
/*       */                 {
/*  7845 */                   if (Trace.isOn) {
/*  7846 */                     Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                   }
/*       */                   
/*       */                   try {
/*  7850 */                     localhconn.enterCall();
/*       */ 
/*       */                     
/*  7853 */                     boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  7854 */                     Native.MQBUFMH(isClassTraced, localhconn
/*  7855 */                         .getValue(), qmName, rxpbBuf, HmsgValue, optBuf, mqmdBuf, BufferLength, pArray, pDataLength, pCompCode, pReason);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */                   
/*       */                   }
/*  7867 */                   catch (JmqiException e) {
/*  7868 */                     if (Trace.isOn) {
/*  7869 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                     }
/*  7871 */                     jTls.lastException = e;
/*  7872 */                     pCompCode.x = e.getCompCode();
/*  7873 */                     pReason.x = e.getReason();
/*       */                   }
/*  7875 */                   catch (UnsatisfiedLinkError e) {
/*  7876 */                     if (Trace.isOn) {
/*  7877 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                     }
/*  7879 */                     pCompCode.x = 2;
/*  7880 */                     pReason.x = 2298;
/*       */                   } finally {
/*       */                     
/*  7883 */                     if (Trace.isOn) {
/*  7884 */                       Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                     }
/*       */                     
/*       */                     try {
/*  7888 */                       localhconn.leaveCall();
/*       */                     }
/*  7890 */                     catch (JmqiException e) {
/*  7891 */                       if (Trace.isOn) {
/*  7892 */                         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                       }
/*  7894 */                       jTls.lastException = e;
/*  7895 */                       pCompCode.x = e.getCompCode();
/*  7896 */                       pReason.x = e.getReason();
/*       */                     } 
/*       */                   } 
/*  7899 */                   if (Trace.isOn) {
/*  7900 */                     Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                 }
/*       */               });
/*       */         }
/*       */         else {
/*       */           
/*  7907 */           if (this.adapterIsRRS) {
/*  7908 */             localhconn.enterCall();
/*  7909 */             lockedLocalhconn = localhconn;
/*       */           } 
/*  7911 */           boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  7912 */           Native.MQBUFMH(isClassTraced, localhconn
/*  7913 */               .getValue(), qmName, rxpbBuf, HmsgValue, optBuf, mqmdBuf, BufferLength, pArray, pDataLength, pCompCode, pReason);
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  7927 */         if (pMsgDesc != null) {
/*  7928 */           pMsgDesc.readFromBuffer(mqmdBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         
/*       */         }
/*       */       }
/*  7932 */       catch (JmqiException e) {
/*  7933 */         if (Trace.isOn) {
/*  7934 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQBUFMH(Hconn,final Hmsg,MQBMHO,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", (Throwable)e, 1);
/*       */         }
/*       */ 
/*       */         
/*  7938 */         jTls.lastException = e;
/*  7939 */         pCompCode.x = e.getCompCode();
/*  7940 */         pReason.x = e.getReason();
/*       */       }
/*  7942 */       catch (UnsatisfiedLinkError e) {
/*  7943 */         if (Trace.isOn) {
/*  7944 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQBUFMH(Hconn,final Hmsg,MQBMHO,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", e, 2);
/*       */         }
/*       */ 
/*       */         
/*  7948 */         pCompCode.x = 2;
/*  7949 */         pReason.x = 2298;
/*       */       }
/*  7951 */       catch (Throwable e) {
/*  7952 */         if (Trace.isOn) {
/*  7953 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQBUFMH(Hconn,final Hmsg,MQBMHO,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", e, 3);
/*       */         }
/*       */ 
/*       */         
/*  7957 */         castUnexpectedException("MQBUFMH(Hconn,final Hmsg,MQBMHO,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", 1, e);
/*       */       } finally {
/*       */         
/*  7960 */         if (Trace.isOn) {
/*  7961 */           Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQBUFMH(Hconn,final Hmsg,MQBMHO,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)");
/*       */         }
/*       */         
/*  7964 */         if (lockedLocalhconn != null) {
/*       */           
/*       */           try {
/*  7967 */             lockedLocalhconn.leaveCall();
/*       */           }
/*  7969 */           catch (JmqiException e) {
/*  7970 */             if (Trace.isOn) {
/*  7971 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQBUFMH(Hconn,final Hmsg,MQBMHO,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", (Throwable)e, 4);
/*       */             }
/*       */ 
/*       */             
/*  7975 */             jTls.lastException = e;
/*  7976 */             pCompCode.x = e.getCompCode();
/*  7977 */             pReason.x = e.getReason();
/*       */           } 
/*       */         }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  7989 */         if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES && this.inheritRRSContext && 
/*  7990 */           pReason.x == 2009) {
/*  7991 */           clearInheritedRXPB(jTls);
/*       */         }
/*       */       } 
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  7999 */     if (Trace.isOn) {
/*  8000 */       Trace.data(this, "MQBUFMH(Hconn,final Hmsg,MQBMHO,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "__________");
/*  8001 */       Trace.data(this, "MQBUFMH(Hconn,final Hmsg,MQBMHO,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "MQBUFMH <<");
/*  8002 */       Trace.data(this, "MQBUFMH(Hconn,final Hmsg,MQBMHO,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Hconn", hConn);
/*  8003 */       Trace.data(this, "MQBUFMH(Hconn,final Hmsg,MQBMHO,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Hmsg", hmsg);
/*  8004 */       Trace.data(this, "MQBUFMH(Hconn,final Hmsg,MQBMHO,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "BufMsgHOpts", pBufMsgHOpts);
/*  8005 */       Trace.data(this, "MQBUFMH(Hconn,final Hmsg,MQBMHO,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "MsgDesc", pMsgDesc);
/*  8006 */       Trace.data(this, "MQBUFMH(Hconn,final Hmsg,MQBMHO,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "BufferLength", Integer.toString(BufferLength));
/*       */       
/*  8008 */       Trace.data(this, "MQBUFMH(Hconn,final Hmsg,MQBMHO,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "DataLength", pDataLength);
/*  8009 */       Trace.data(this, "MQBUFMH(Hconn,final Hmsg,MQBMHO,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "CompCode", pCompCode);
/*  8010 */       Trace.data(this, "MQBUFMH(Hconn,final Hmsg,MQBMHO,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Reason", pReason);
/*  8011 */       Trace.data(this, "MQBUFMH(Hconn,final Hmsg,MQBMHO,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)", "__________");
/*       */     } 
/*  8013 */     if (Trace.isOn) {
/*  8014 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "MQBUFMH(Hconn,final Hmsg,MQBMHO,MQMD,final int,ByteBuffer,final Pint,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void spiSubscribe(Hconn hconn, LpiSD lpiSD, MQSD subDesc, Phobj pHobj, Phobj pHsub, final Pint pCompCode, final Pint pReason) {
/*  8033 */     if (Trace.isOn) {
/*  8034 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,final Pint,final Pint)", new Object[] { hconn, lpiSD, subDesc, pHobj, pHsub, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */     
/*  8038 */     String fid = "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,final Pint,final Pint)";
/*       */ 
/*       */     
/*  8041 */     if (Trace.isOn) {
/*  8042 */       Trace.data(this, "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,final Pint,final Pint)", "__________");
/*  8043 */       Trace.data(this, "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,final Pint,final Pint)", "spiSubscribe >>");
/*  8044 */       Trace.data(this, "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,final Pint,final Pint)", "Hconn", hconn);
/*  8045 */       Trace.data(this, "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,final Pint,final Pint)", "LpiSD", lpiSD);
/*  8046 */       Trace.data(this, "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,final Pint,final Pint)", "SubDesc", subDesc);
/*  8047 */       Trace.data(this, "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,final Pint,final Pint)", "Hobj", pHobj);
/*  8048 */       Trace.data(this, "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,final Pint,final Pint)", "Hsub", pHsub);
/*  8049 */       Trace.data(this, "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,final Pint,final Pint)", "CompCode", pCompCode);
/*  8050 */       Trace.data(this, "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,final Pint,final Pint)", "Reason", pReason);
/*  8051 */       Trace.data(this, "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,final Pint,final Pint)", "__________");
/*       */     } 
/*       */     
/*  8054 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  8055 */     final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/*  8056 */     LocalHconn lockedLocalhconn = null; try {
/*       */       final byte[] rxpbBuf, lpiSDBuf, subDescBuf;
/*  8058 */       final LocalHobj localhobj = LocalHobj.getLocalHobj(this.env, pHobj.getHobj());
/*  8059 */       final LocalHobj localhsub = LocalHobj.getLocalHobj(this.env, pHsub.getHobj());
/*  8060 */       final LocalHconn localhconn = getLocalHconn(hconn);
/*  8061 */       final byte[] qmName = localhconn.getQMNameBytes();
/*       */       
/*  8063 */       RXPB rxpb = null;
/*       */       
/*  8065 */       if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/*  8066 */         rxpbBuf = null;
/*       */       } else {
/*       */         
/*  8069 */         if (this.inheritRRSContext) {
/*  8070 */           rxpb = getInheritedRxpb(tls, jTls, localhconn);
/*  8071 */           if (localhobj.getCtxToken() != null && !Arrays.equals(localhobj.getCtxToken(), rxpb.getCtxTkn()))
/*       */           {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */             
/*  8078 */             JmqiException e = new JmqiException(this.env, -1, null, 2, 2298, null);
/*       */             
/*  8080 */             if (Trace.isOn) {
/*  8081 */               Trace.throwing(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,final Pint,final Pint)", (Throwable)e);
/*       */             }
/*       */             
/*  8084 */             throw e;
/*       */           }
/*       */         
/*       */         } else {
/*       */           
/*  8089 */           rxpb = localhconn.getRxpb();
/*       */         } 
/*  8091 */         int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/*  8092 */         if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/*  8093 */           tls.rxpbBuf = new byte[rxpbLen];
/*       */         }
/*  8095 */         rxpbBuf = tls.rxpbBuf;
/*  8096 */         rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */       
/*  8100 */       if (lpiSD == null) {
/*  8101 */         lpiSDBuf = null;
/*       */       } else {
/*       */         
/*  8104 */         int lpiSDLen = lpiSD.getRequiredBufferSize(ptrSize, nativeCp);
/*  8105 */         lpiSDBuf = new byte[lpiSDLen];
/*  8106 */         lpiSD.writeToBuffer(lpiSDBuf, 0, ptrSize, swap, nativeCp, jTls, true);
/*       */       } 
/*       */ 
/*       */       
/*  8110 */       if (subDesc == null) {
/*  8111 */         subDescBuf = null;
/*       */       } else {
/*       */         
/*  8114 */         int subDescLen = subDesc.getRequiredBufferSize(ptrSize, nativeCp);
/*  8115 */         subDescBuf = new byte[subDescLen];
/*  8116 */         subDesc.writeToBuffer(subDescBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  8122 */       if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hconn)) {
/*  8123 */         localhconn.syncExec(new JmqiRunnable(this.env)
/*       */             {
/*       */               public void run()
/*       */               {
/*  8127 */                 if (Trace.isOn) {
/*  8128 */                   Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                 }
/*       */ 
/*       */                 
/*       */                 try {
/*  8133 */                   localhconn.enterCall();
/*       */                   
/*  8135 */                   if (Trace.isOn) {
/*  8136 */                     Trace.data(this, "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,final Pint,final Pint)", "subDescBuf", subDescBuf);
/*  8137 */                     Trace.data(this, "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,final Pint,final Pint)", "lpiSDBuf", lpiSDBuf);
/*       */                   } 
/*  8139 */                   boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  8140 */                   Native.lpiSpiSubscribe(isClassTraced, localhconn
/*  8141 */                       .getValue(), qmName, rxpbBuf, lpiSDBuf, subDescBuf, localhobj, localhsub, pCompCode, pReason);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */                 
/*       */                 }
/*  8151 */                 catch (JmqiException e) {
/*  8152 */                   if (Trace.isOn) {
/*  8153 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                   }
/*       */                   
/*  8156 */                   jTls.lastException = e;
/*  8157 */                   pCompCode.x = e.getCompCode();
/*  8158 */                   pReason.x = e.getReason();
/*       */                 }
/*  8160 */                 catch (UnsatisfiedLinkError e) {
/*  8161 */                   if (Trace.isOn) {
/*  8162 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                   }
/*  8164 */                   pCompCode.x = 2;
/*  8165 */                   pReason.x = 2298;
/*       */                 } finally {
/*       */                   
/*  8168 */                   if (Trace.isOn) {
/*  8169 */                     Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                   
/*       */                   try {
/*  8173 */                     localhconn.leaveCall();
/*       */                   }
/*  8175 */                   catch (JmqiException e) {
/*  8176 */                     if (Trace.isOn) {
/*  8177 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                     }
/*  8179 */                     jTls.lastException = e;
/*  8180 */                     pCompCode.x = e.getCompCode();
/*  8181 */                     pReason.x = e.getReason();
/*       */                   } 
/*       */                 } 
/*  8184 */                 if (Trace.isOn) {
/*  8185 */                   Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                 
/*       */                 }
/*       */               }
/*       */             });
/*       */       
/*       */       }
/*       */       else {
/*       */         
/*  8194 */         if (this.adapterIsRRS) {
/*       */           
/*  8196 */           localhconn.enterCall();
/*  8197 */           lockedLocalhconn = localhconn;
/*       */         } 
/*       */         
/*  8200 */         if (Trace.isOn) {
/*  8201 */           Trace.data(this, "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,final Pint,final Pint)", "subDescBuf", subDescBuf);
/*  8202 */           Trace.data(this, "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,final Pint,final Pint)", "lpiSDBuf", lpiSDBuf);
/*       */         } 
/*  8204 */         boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  8205 */         Native.lpiSpiSubscribe(isClassTraced, localhconn
/*  8206 */             .getValue(), qmName, rxpbBuf, lpiSDBuf, subDescBuf, localhobj, localhsub, pCompCode, pReason);
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  8218 */       localhobj.setHobj(pHobj);
/*  8219 */       localhsub.setHobj(pHsub);
/*  8220 */       localhobj.setHconn(localhconn);
/*  8221 */       localhsub.setHconn(localhconn);
/*  8222 */       if (subDesc != null) {
/*  8223 */         subDesc.readFromBuffer(subDescBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       }
/*  8225 */       if (lpiSD != null) {
/*  8226 */         lpiSD.readFromBuffer(lpiSDBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       }
/*  8228 */       if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES)
/*       */       {
/*  8230 */         localhobj.setCtxToken(rxpb.getCtxTkn());
/*  8231 */         localhsub.setCtxToken(rxpb.getCtxTkn());
/*       */       }
/*       */     
/*  8234 */     } catch (JmqiException e) {
/*  8235 */       if (Trace.isOn) {
/*  8236 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,final Pint,final Pint)", (Throwable)e, 1);
/*       */       }
/*       */       
/*  8239 */       jTls.lastException = e;
/*  8240 */       pCompCode.x = e.getCompCode();
/*  8241 */       pReason.x = e.getReason();
/*       */     }
/*  8243 */     catch (UnsatisfiedLinkError e) {
/*  8244 */       if (Trace.isOn) {
/*  8245 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,final Pint,final Pint)", e, 2);
/*       */       }
/*       */       
/*  8248 */       pCompCode.x = 2;
/*  8249 */       pReason.x = 2298;
/*       */     }
/*  8251 */     catch (Throwable e) {
/*  8252 */       if (Trace.isOn) {
/*  8253 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,final Pint,final Pint)", e, 3);
/*       */       }
/*       */       
/*  8256 */       castUnexpectedException("spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,final Pint,final Pint)", 1, e);
/*       */     } finally {
/*       */       
/*  8259 */       if (Trace.isOn) {
/*  8260 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,final Pint,final Pint)");
/*       */       }
/*       */       
/*  8263 */       if (lockedLocalhconn != null) {
/*       */         
/*       */         try {
/*  8266 */           lockedLocalhconn.leaveCall();
/*       */         }
/*  8268 */         catch (JmqiException e) {
/*  8269 */           if (Trace.isOn) {
/*  8270 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,final Pint,final Pint)", (Throwable)e, 4);
/*       */           }
/*       */           
/*  8273 */           jTls.lastException = e;
/*  8274 */           pCompCode.x = e.getCompCode();
/*  8275 */           pReason.x = e.getReason();
/*       */         } 
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  8284 */       if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES && this.inheritRRSContext && 
/*  8285 */         pReason.x == 2009) {
/*  8286 */         clearInheritedRXPB(jTls);
/*       */       }
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  8293 */     if (Trace.isOn) {
/*  8294 */       Trace.data(this, "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,final Pint,final Pint)", "__________");
/*  8295 */       Trace.data(this, "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,final Pint,final Pint)", "spiSubscribe <<");
/*  8296 */       Trace.data(this, "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,final Pint,final Pint)", "Hconn", hconn);
/*  8297 */       Trace.data(this, "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,final Pint,final Pint)", "LpiSD", lpiSD);
/*  8298 */       Trace.data(this, "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,final Pint,final Pint)", "SubDesc", subDesc);
/*  8299 */       Trace.data(this, "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,final Pint,final Pint)", "Hobj", pHobj);
/*  8300 */       Trace.data(this, "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,final Pint,final Pint)", "Hsub", pHsub);
/*  8301 */       Trace.data(this, "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,final Pint,final Pint)", "CompCode", pCompCode);
/*  8302 */       Trace.data(this, "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,final Pint,final Pint)", "Reason", pReason);
/*  8303 */       Trace.data(this, "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,final Pint,final Pint)", "__________");
/*       */     } 
/*  8305 */     if (Trace.isOn) {
/*  8306 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void spiUnsubscribe(Hconn hconn, LpiUSD lpiUSD, final Pint pCompCode, final Pint pReason) {
/*  8325 */     if (Trace.isOn) {
/*  8326 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiUnsubscribe(Hconn,LpiUSD,final Pint,final Pint)", new Object[] { hconn, lpiUSD, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */     
/*  8330 */     String fid = "spiUnsubscribe(Hconn,LpiUSD,final Pint,final Pint)";
/*       */ 
/*       */     
/*  8333 */     if (Trace.isOn) {
/*  8334 */       Trace.data(this, "spiUnsubscribe(Hconn,LpiUSD,final Pint,final Pint)", "__________");
/*  8335 */       Trace.data(this, "spiUnsubscribe(Hconn,LpiUSD,final Pint,final Pint)", "spiUnsubscribe >>");
/*  8336 */       Trace.data(this, "spiUnsubscribe(Hconn,LpiUSD,final Pint,final Pint)", "Hconn", hconn);
/*  8337 */       Trace.data(this, "spiUnsubscribe(Hconn,LpiUSD,final Pint,final Pint)", "LpiUSD", lpiUSD);
/*  8338 */       Trace.data(this, "spiUnsubscribe(Hconn,LpiUSD,final Pint,final Pint)", "CompCode", pCompCode);
/*  8339 */       Trace.data(this, "spiUnsubscribe(Hconn,LpiUSD,final Pint,final Pint)", "Reason", pReason);
/*  8340 */       Trace.data(this, "spiUnsubscribe(Hconn,LpiUSD,final Pint,final Pint)", "__________");
/*       */     } 
/*       */     
/*  8343 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  8344 */     final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/*  8345 */     LocalHconn lockedLocalhconn = null; try {
/*       */       final byte[] rxpbBuf, lpiUSDBuf;
/*  8347 */       final LocalHconn localhconn = getLocalHconn(hconn);
/*  8348 */       final byte[] qmName = localhconn.getQMNameBytes();
/*       */       
/*  8350 */       RXPB rxpb = null;
/*       */       
/*  8352 */       if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/*  8353 */         rxpbBuf = null;
/*       */       } else {
/*       */         
/*  8356 */         if (this.inheritRRSContext) {
/*  8357 */           rxpb = getInheritedRxpb(tls, jTls, localhconn);
/*       */         } else {
/*       */           
/*  8360 */           rxpb = localhconn.getRxpb();
/*       */         } 
/*  8362 */         int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/*  8363 */         if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/*  8364 */           tls.rxpbBuf = new byte[rxpbLen];
/*       */         }
/*  8366 */         rxpbBuf = tls.rxpbBuf;
/*  8367 */         rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */       
/*  8371 */       if (lpiUSD == null) {
/*  8372 */         lpiUSDBuf = null;
/*       */       } else {
/*       */         
/*  8375 */         int lpiUSDLen = lpiUSD.getRequiredBufferSize(ptrSize, nativeCp);
/*  8376 */         lpiUSDBuf = new byte[lpiUSDLen];
/*  8377 */         lpiUSD.writeToBuffer(lpiUSDBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  8383 */       if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hconn)) {
/*  8384 */         localhconn.syncExec(new JmqiRunnable(this.env)
/*       */             {
/*       */               public void run()
/*       */               {
/*  8388 */                 if (Trace.isOn) {
/*  8389 */                   Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                 }
/*       */ 
/*       */                 
/*       */                 try {
/*  8394 */                   localhconn.enterCall();
/*       */                   
/*  8396 */                   boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  8397 */                   Native.lpiSpiUnsubscribe(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, lpiUSDBuf, pCompCode, pReason);
/*       */                 }
/*  8399 */                 catch (JmqiException e) {
/*  8400 */                   if (Trace.isOn) {
/*  8401 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                   }
/*       */                   
/*  8404 */                   jTls.lastException = e;
/*  8405 */                   pCompCode.x = e.getCompCode();
/*  8406 */                   pReason.x = e.getReason();
/*       */                 }
/*  8408 */                 catch (UnsatisfiedLinkError e) {
/*  8409 */                   if (Trace.isOn) {
/*  8410 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                   }
/*  8412 */                   pCompCode.x = 2;
/*  8413 */                   pReason.x = 2298;
/*       */                 } finally {
/*       */                   
/*  8416 */                   if (Trace.isOn) {
/*  8417 */                     Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                   
/*       */                   try {
/*  8421 */                     localhconn.leaveCall();
/*       */                   }
/*  8423 */                   catch (JmqiException e) {
/*  8424 */                     if (Trace.isOn) {
/*  8425 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                     }
/*  8427 */                     jTls.lastException = e;
/*  8428 */                     pCompCode.x = e.getCompCode();
/*  8429 */                     pReason.x = e.getReason();
/*       */                   } 
/*       */                 } 
/*  8432 */                 if (Trace.isOn) {
/*  8433 */                   Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                 
/*       */                 }
/*       */               }
/*       */             });
/*       */       }
/*       */       else {
/*       */         
/*  8441 */         if (this.adapterIsRRS) {
/*       */           
/*  8443 */           localhconn.enterCall();
/*  8444 */           lockedLocalhconn = localhconn;
/*       */         } 
/*  8446 */         boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  8447 */         Native.lpiSpiUnsubscribe(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, lpiUSDBuf, pCompCode, pReason);
/*       */       }
/*       */     
/*  8450 */     } catch (JmqiException e) {
/*  8451 */       if (Trace.isOn) {
/*  8452 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiUnsubscribe(Hconn,LpiUSD,final Pint,final Pint)", (Throwable)e, 1);
/*       */       }
/*       */       
/*  8455 */       jTls.lastException = e;
/*  8456 */       pCompCode.x = e.getCompCode();
/*  8457 */       pReason.x = e.getReason();
/*       */     }
/*  8459 */     catch (UnsatisfiedLinkError e) {
/*  8460 */       if (Trace.isOn) {
/*  8461 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiUnsubscribe(Hconn,LpiUSD,final Pint,final Pint)", e, 2);
/*       */       }
/*       */       
/*  8464 */       pCompCode.x = 2;
/*  8465 */       pReason.x = 2298;
/*       */     }
/*  8467 */     catch (Throwable e) {
/*  8468 */       if (Trace.isOn) {
/*  8469 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiUnsubscribe(Hconn,LpiUSD,final Pint,final Pint)", e, 3);
/*       */       }
/*       */       
/*  8472 */       castUnexpectedException("spiUnsubscribe(Hconn,LpiUSD,final Pint,final Pint)", 1, e);
/*       */     } finally {
/*       */       
/*  8475 */       if (Trace.isOn) {
/*  8476 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiUnsubscribe(Hconn,LpiUSD,final Pint,final Pint)");
/*       */       }
/*       */       
/*  8479 */       if (lockedLocalhconn != null) {
/*       */         
/*       */         try {
/*  8482 */           lockedLocalhconn.leaveCall();
/*       */         }
/*  8484 */         catch (JmqiException e) {
/*  8485 */           if (Trace.isOn) {
/*  8486 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiUnsubscribe(Hconn,LpiUSD,final Pint,final Pint)", (Throwable)e, 4);
/*       */           }
/*       */           
/*  8489 */           jTls.lastException = e;
/*  8490 */           pCompCode.x = e.getCompCode();
/*  8491 */           pReason.x = e.getReason();
/*       */         } 
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  8500 */       if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES && this.inheritRRSContext && 
/*  8501 */         pReason.x == 2009) {
/*  8502 */         clearInheritedRXPB(jTls);
/*       */       }
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  8509 */     if (Trace.isOn) {
/*  8510 */       Trace.data(this, "spiUnsubscribe(Hconn,LpiUSD,final Pint,final Pint)", "__________");
/*  8511 */       Trace.data(this, "spiUnsubscribe(Hconn,LpiUSD,final Pint,final Pint)", "spiUnsubscribe <<");
/*  8512 */       Trace.data(this, "spiUnsubscribe(Hconn,LpiUSD,final Pint,final Pint)", "Hconn", hconn);
/*  8513 */       Trace.data(this, "spiUnsubscribe(Hconn,LpiUSD,final Pint,final Pint)", "LpiSD", lpiUSD);
/*  8514 */       Trace.data(this, "spiUnsubscribe(Hconn,LpiUSD,final Pint,final Pint)", "CompCode", pCompCode);
/*  8515 */       Trace.data(this, "spiUnsubscribe(Hconn,LpiUSD,final Pint,final Pint)", "Reason", pReason);
/*  8516 */       Trace.data(this, "spiUnsubscribe(Hconn,LpiUSD,final Pint,final Pint)", "__________");
/*       */     } 
/*  8518 */     if (Trace.isOn) {
/*  8519 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiUnsubscribe(Hconn,LpiUSD,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void spiConnect(String pQMgrNameParam, LpiPrivConnStruct pSpiConnectOpts, MQCNO pConnectOpts, Phconn phconn, Pint pCompCode, Pint pReason) {
/*  8543 */     if (Trace.isOn) {
/*  8544 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiConnect(String,LpiPrivConnStruct,MQCNO,Phconn,final Pint,final Pint)", new Object[] { pQMgrNameParam, pSpiConnectOpts, pConnectOpts, phconn, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */     
/*  8548 */     String fid = "spiConnect(String,LpiPrivConnStruct,MQCNO,Phconn,final Pint,final Pint)";
/*       */ 
/*       */     
/*  8551 */     if (Trace.isOn) {
/*  8552 */       Trace.data(this, "spiConnect(String,LpiPrivConnStruct,MQCNO,Phconn,final Pint,final Pint)", "__________");
/*  8553 */       Trace.data(this, "spiConnect(String,LpiPrivConnStruct,MQCNO,Phconn,final Pint,final Pint)", "spiConnect >>");
/*  8554 */       Trace.data(this, "spiConnect(String,LpiPrivConnStruct,MQCNO,Phconn,final Pint,final Pint)", "SpiConnectOptions", pSpiConnectOpts);
/*  8555 */       Trace.data(this, "spiConnect(String,LpiPrivConnStruct,MQCNO,Phconn,final Pint,final Pint)", "ConnectOpts", pConnectOpts);
/*  8556 */       Trace.data(this, "spiConnect(String,LpiPrivConnStruct,MQCNO,Phconn,final Pint,final Pint)", "Hconn", phconn);
/*  8557 */       Trace.data(this, "spiConnect(String,LpiPrivConnStruct,MQCNO,Phconn,final Pint,final Pint)", "CompCode", pCompCode);
/*  8558 */       Trace.data(this, "spiConnect(String,LpiPrivConnStruct,MQCNO,Phconn,final Pint,final Pint)", "Reason", pReason);
/*  8559 */       Trace.data(this, "spiConnect(String,LpiPrivConnStruct,MQCNO,Phconn,final Pint,final Pint)", "__________");
/*       */     } 
/*  8561 */     String pQMgrName = pQMgrNameParam;
/*  8562 */     int shareOption = 0;
/*       */     
/*  8564 */     if (pConnectOpts == null) {
/*  8565 */       pCompCode.x = 2;
/*  8566 */       pReason.x = 2139;
/*       */     } 
/*       */ 
/*       */ 
/*       */     
/*  8571 */     if (this.useWorkerThread) {
/*       */       
/*  8573 */       shareOption = pConnectOpts.getOptions() & 0xE0;
/*  8574 */       if (shareOption == 0) {
/*  8575 */         shareOption = 32;
/*       */       }
/*  8577 */       if (shareOption != 32 && shareOption != 64 && shareOption != 128) {
/*  8578 */         pCompCode.x = 2;
/*  8579 */         pReason.x = 2046;
/*       */       } 
/*       */     } 
/*       */ 
/*       */ 
/*       */     
/*  8585 */     if (!this.useSharedHconn || this.useWorkerThread) {
/*  8586 */       int options = pConnectOpts.getOptions();
/*  8587 */       options &= 0xFFFFFFDF;
/*  8588 */       options &= 0xFFFFFFBF;
/*  8589 */       options &= 0xFFFFFF7F;
/*  8590 */       pConnectOpts.setOptions(options);
/*       */     } 
/*       */ 
/*       */     
/*  8594 */     if (pReason.x == 0) {
/*       */       
/*  8596 */       LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  8597 */       JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/*  8598 */       JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*       */       try {
/*       */         byte[] rxpbBuf, spiBuf;
/*       */         int environment, origCnoVer;
/*  8602 */         byte[] cnoBuf, qmNameBytes = new byte[48];
/*  8603 */         dc.writeMQField(pQMgrName, qmNameBytes, 0, 48, nativeCp, jTls);
/*  8604 */         LocalHconn localhconn = getLocalHconn(phconn.getHconn());
/*  8605 */         localhconn.setOriginalQueueManagerName(pQMgrName);
/*       */         
/*  8607 */         RXPB rxpb = null;
/*  8608 */         int originalRxpbFlags = 0;
/*       */         
/*  8610 */         if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/*  8611 */           rxpbBuf = null;
/*       */         } else {
/*       */           
/*  8614 */           Adapter adapter = getAdapter();
/*  8615 */           rxpb = adapter.buildNewRxpb(this.env, pConnectOpts);
/*       */           
/*  8617 */           originalRxpbFlags = rxpb.getFlags();
/*  8618 */           setRXPBFlags(tls, jTls, localhconn, rxpb, null);
/*  8619 */           int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/*  8620 */           if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/*  8621 */             tls.rxpbBuf = new byte[rxpbLen];
/*       */           }
/*  8623 */           rxpbBuf = tls.rxpbBuf;
/*  8624 */           rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  8636 */         if (pSpiConnectOpts == null) {
/*  8637 */           spiBuf = null;
/*  8638 */           environment = -1;
/*       */         } else {
/*       */           
/*  8641 */           int spiLen = pSpiConnectOpts.getRequiredBufferSize(ptrSize, nativeCp);
/*  8642 */           spiBuf = new byte[spiLen];
/*  8643 */           pSpiConnectOpts.writeToBuffer(spiBuf, 0, ptrSize, swap, nativeCp, jTls);
/*  8644 */           environment = pSpiConnectOpts.getEnvironment();
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  8652 */         if (pConnectOpts == null) {
/*  8653 */           origCnoVer = -1;
/*  8654 */           cnoBuf = null;
/*       */         } else {
/*       */           
/*  8657 */           origCnoVer = pConnectOpts.getVersion();
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  8662 */           if (cmdLevel >= 912 && JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/*  8663 */             String appName = JmqiTools.getAppName(pConnectOpts);
/*  8664 */             pConnectOpts.setApplName(appName);
/*       */           } 
/*       */           
/*  8667 */           int cnoLen = pConnectOpts.getRequiredBufferSize(ptrSize, nativeCp);
/*  8668 */           cnoBuf = new byte[cnoLen];
/*  8669 */           pConnectOpts.writeToBuffer(cnoBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         } 
/*       */ 
/*       */         
/*  8673 */         performConnect(jTls, 2, qmNameBytes, rxpbBuf, spiBuf, cnoBuf, localhconn, environment, pCompCode, pReason);
/*       */ 
/*       */         
/*  8676 */         if (pReason.x == 2139)
/*       */         {
/*  8678 */           if (pConnectOpts != null && origCnoVer != pConnectOpts.getVersion()) {
/*       */             
/*  8680 */             pConnectOpts.setVersion(origCnoVer);
/*  8681 */             int cnoLen = pConnectOpts.getRequiredBufferSize(ptrSize, nativeCp);
/*  8682 */             cnoBuf = new byte[cnoLen];
/*  8683 */             pConnectOpts.writeToBuffer(cnoBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */ 
/*       */             
/*  8686 */             performConnect(jTls, 2, qmNameBytes, rxpbBuf, spiBuf, cnoBuf, localhconn, environment, pCompCode, pReason);
/*       */           } 
/*       */         }
/*       */ 
/*       */         
/*  8691 */         localhconn.updateHconn(this, phconn);
/*  8692 */         localhconn.setShareOption(shareOption);
/*  8693 */         if (pConnectOpts != null) {
/*  8694 */           pConnectOpts.readFromBuffer(cnoBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         }
/*  8696 */         if (pSpiConnectOpts != null) {
/*  8697 */           pSpiConnectOpts.readFromBuffer(spiBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         }
/*  8699 */         if (rxpb != null && rxpbBuf != null) {
/*  8700 */           rxpb.readFromBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  8708 */           if (Trace.isOn) {
/*  8709 */             Trace.data("spiConnect(String,LpiPrivConnStruct,MQCNO,Phconn,final Pint,final Pint)", "RXPB after conn conn call", rxpb);
/*       */           }
/*  8711 */           rxpb.setFlags(originalRxpbFlags);
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  8719 */         if (pQMgrName == null || "".equals(pQMgrName.trim())) {
/*  8720 */           pQMgrName = localhconn.getName();
/*  8721 */           dc.writeMQField(pQMgrName, qmNameBytes, 0, 48, nativeCp, jTls);
/*  8722 */           localhconn.setQMNameBytes(qmNameBytes);
/*       */         }
/*       */       
/*  8725 */       } catch (JmqiException e) {
/*  8726 */         if (Trace.isOn) {
/*  8727 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiConnect(String,LpiPrivConnStruct,MQCNO,Phconn,final Pint,final Pint)", (Throwable)e, 1);
/*       */         }
/*       */         
/*  8730 */         jTls.lastException = e;
/*  8731 */         pCompCode.x = e.getCompCode();
/*  8732 */         pReason.x = e.getReason();
/*       */       }
/*  8734 */       catch (UnsupportedEncodingException e) {
/*  8735 */         if (Trace.isOn) {
/*  8736 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiConnect(String,LpiPrivConnStruct,MQCNO,Phconn,final Pint,final Pint)", e, 2);
/*       */         }
/*       */         
/*  8739 */         pCompCode.x = 2;
/*  8740 */         pReason.x = 2340;
/*       */       }
/*  8742 */       catch (UnsatisfiedLinkError e) {
/*  8743 */         if (Trace.isOn) {
/*  8744 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiConnect(String,LpiPrivConnStruct,MQCNO,Phconn,final Pint,final Pint)", e, 3);
/*       */         }
/*       */         
/*  8747 */         pCompCode.x = 2;
/*  8748 */         pReason.x = 2298;
/*       */       }
/*  8750 */       catch (Throwable e) {
/*  8751 */         if (Trace.isOn) {
/*  8752 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiConnect(String,LpiPrivConnStruct,MQCNO,Phconn,final Pint,final Pint)", e, 4);
/*       */         }
/*       */         
/*  8755 */         castUnexpectedException("spiConnect(String,LpiPrivConnStruct,MQCNO,Phconn,final Pint,final Pint)", 1, e);
/*       */       } finally {
/*       */         
/*  8758 */         if (Trace.isOn) {
/*  8759 */           Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiConnect(String,LpiPrivConnStruct,MQCNO,Phconn,final Pint,final Pint)");
/*       */         }
/*       */       } 
/*       */     } 
/*       */ 
/*       */ 
/*       */     
/*  8766 */     if (Trace.isOn) {
/*  8767 */       Trace.data(this, "spiConnect(String,LpiPrivConnStruct,MQCNO,Phconn,final Pint,final Pint)", "__________");
/*  8768 */       Trace.data(this, "spiConnect(String,LpiPrivConnStruct,MQCNO,Phconn,final Pint,final Pint)", "MQCONNX <<");
/*  8769 */       Trace.data(this, "spiConnect(String,LpiPrivConnStruct,MQCNO,Phconn,final Pint,final Pint)", "SpiConnectOptions", pSpiConnectOpts);
/*  8770 */       Trace.data(this, "spiConnect(String,LpiPrivConnStruct,MQCNO,Phconn,final Pint,final Pint)", "ConnectOpts", pConnectOpts);
/*  8771 */       Trace.data(this, "spiConnect(String,LpiPrivConnStruct,MQCNO,Phconn,final Pint,final Pint)", "Hconn", phconn);
/*  8772 */       Trace.data(this, "spiConnect(String,LpiPrivConnStruct,MQCNO,Phconn,final Pint,final Pint)", "CompCode", pCompCode);
/*  8773 */       Trace.data(this, "spiConnect(String,LpiPrivConnStruct,MQCNO,Phconn,final Pint,final Pint)", "Reason", pReason);
/*  8774 */       Trace.data(this, "spiConnect(String,LpiPrivConnStruct,MQCNO,Phconn,final Pint,final Pint)", "__________");
/*       */     } 
/*  8776 */     if (Trace.isOn) {
/*  8777 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiConnect(String,LpiPrivConnStruct,MQCNO,Phconn,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   private void performConnect(final JmqiTls jTls, final int requestedConnType, final byte[] qmNameBytes, final byte[] rxpbBuf, final byte[] spiBuf, final byte[] cnoBuf, final LocalHconn localhconn, final int environment, final Pint pCompCode, final Pint pReason) throws JmqiException, Exception {
/*  8799 */     String fid = "performConnect(JmqiTls,int,byte[],byte[],byte[],byte[],LocalHconn,int,Pint,Pint)";
/*  8800 */     if (Trace.isOn) {
/*  8801 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "performConnect(JmqiTls,int,byte[],byte[],byte[],byte[],LocalHconn,int,Pint,Pint)", new Object[] { jTls, 
/*  8802 */             Integer.valueOf(requestedConnType), qmNameBytes, rxpbBuf, spiBuf, cnoBuf, localhconn, 
/*  8803 */             Integer.valueOf(environment), pCompCode, pReason });
/*       */     }
/*       */ 
/*       */ 
/*       */     
/*  8808 */     if (this.useWorkerThread) {
/*  8809 */       localhconn.syncExec(new JmqiRunnable(this.env)
/*       */           {
/*       */             public void run()
/*       */             {
/*  8813 */               if (Trace.isOn) {
/*  8814 */                 Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */               }
/*       */ 
/*       */               
/*       */               try {
/*  8819 */                 Adapter adapter = LocalMQ.this.getAdapter();
/*  8820 */                 boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  8821 */                 adapter.connect(requestedConnType, isClassTraced, qmNameBytes, rxpbBuf, spiBuf, cnoBuf, localhconn, environment, pCompCode, pReason);
/*       */               }
/*  8823 */               catch (JmqiException e) {
/*  8824 */                 if (Trace.isOn) {
/*  8825 */                   Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                 }
/*       */                 
/*  8828 */                 jTls.lastException = e;
/*  8829 */                 pCompCode.x = e.getCompCode();
/*  8830 */                 pReason.x = e.getReason();
/*       */               }
/*  8832 */               catch (UnsatisfiedLinkError e) {
/*  8833 */                 if (Trace.isOn) {
/*  8834 */                   Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                 }
/*  8836 */                 pCompCode.x = 2;
/*  8837 */                 pReason.x = 2298;
/*       */               } 
/*  8839 */               if (Trace.isOn) {
/*  8840 */                 Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */               
/*       */               }
/*       */             }
/*       */           });
/*       */     }
/*       */     else {
/*       */       
/*  8848 */       Adapter adapter = getAdapter();
/*  8849 */       boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  8850 */       adapter.connect(requestedConnType, isClassTraced, qmNameBytes, rxpbBuf, spiBuf, cnoBuf, localhconn, environment, pCompCode, pReason);
/*       */     } 
/*       */     
/*  8853 */     if (Trace.isOn) {
/*  8854 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "performConnect(JmqiTls,int,byte[],byte[],byte[],byte[],LocalHconn,int,Pint,Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void jmqiCancelWaitingGets(Hconn notifyHconn, Hconn getterHconn, Hconn helperHconn, Pint pCompCode, Pint pReason) {
/*  8868 */     if (Trace.isOn) {
/*  8869 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "jmqiCancelWaitingGets(Hconn, Hconn, Hconn, Pint, Pint)", new Object[] { notifyHconn, getterHconn, helperHconn, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */ 
/*       */     
/*  8874 */     JmqiTools.cancelWaitingGets((JmqiEnvironment)this.sysenv, this, notifyHconn, getterHconn, helperHconn, pCompCode, pReason);
/*  8875 */     if (Trace.isOn) {
/*  8876 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "jmqiCancelWaitingGets(Hconn, Hconn, Hconn, Pint, Pint)", new Object[] { notifyHconn, getterHconn, helperHconn, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */ 
/*       */     
/*  8881 */     if (Trace.isOn) {
/*  8882 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.LocalMQ", "jmqiCancelWaitingGets(Hconn, Hconn, Hconn, Pint, Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void jmqiConnect(String pQMgrNameParam, JmqiConnectOptions pJmqiConnectOptions, MQCNO pConnectOpts, Hconn parentHconn, Phconn phconn, Pint pCompCode, Pint pReason) {
/*       */     int requestedConnType;
/*  8908 */     if (Trace.isOn) {
/*  8909 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,final Pint,final Pint)", new Object[] { pQMgrNameParam, pJmqiConnectOptions, pConnectOpts, parentHconn, phconn, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */ 
/*       */     
/*  8914 */     String fid = "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,final Pint,final Pint)";
/*       */ 
/*       */     
/*  8917 */     if (Trace.isOn) {
/*  8918 */       Trace.data(this, "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,final Pint,final Pint)", "__________");
/*  8919 */       Trace.data(this, "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,final Pint,final Pint)", "MQCONNX >>");
/*  8920 */       Trace.data(this, "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,final Pint,final Pint)", "JmqiConnectOptions", pJmqiConnectOptions);
/*  8921 */       Trace.data(this, "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,final Pint,final Pint)", "ConnectOpts", pConnectOpts);
/*  8922 */       Trace.data(this, "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,final Pint,final Pint)", "parentHconn", parentHconn);
/*  8923 */       Trace.data(this, "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,final Pint,final Pint)", "Hconn", phconn);
/*  8924 */       Trace.data(this, "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,final Pint,final Pint)", "CompCode", pCompCode);
/*  8925 */       Trace.data(this, "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,final Pint,final Pint)", "Reason", pReason);
/*  8926 */       Trace.data(this, "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,final Pint,final Pint)", "__________");
/*       */     } 
/*       */ 
/*       */     
/*  8930 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  8931 */     JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/*  8932 */     jTls.lastException = null;
/*       */     
/*  8934 */     String pQMgrName = pQMgrNameParam;
/*       */     
/*  8936 */     if (!(phconn.getHconn() instanceof com.ibm.mq.jmqi.internal.HconnAdapter))
/*       */     {
/*       */ 
/*       */ 
/*       */       
/*  8941 */       phconn.setHconn(CMQC.jmqi_MQHC_DEF_HCONN);
/*       */     }
/*       */     
/*  8944 */     int shareOption = 0;
/*       */     
/*  8946 */     if (pConnectOpts == null) {
/*  8947 */       pCompCode.x = 2;
/*  8948 */       pReason.x = 2139;
/*       */     } 
/*       */ 
/*       */     
/*  8952 */     JmqiConnectOptions pJmqiConnectOpts = pJmqiConnectOptions;
/*  8953 */     LpiPrivConnStruct pSpiConnectOpts = null;
/*       */     
/*  8955 */     if (pJmqiConnectOptions == null) {
/*  8956 */       pJmqiConnectOpts = this.sysenv.newJmqiConnectOptions();
/*       */ 
/*       */     
/*       */     }
/*  8960 */     else if (parentHconn != null && !(parentHconn instanceof LocalHconn)) {
/*  8961 */       pCompCode.x = 2;
/*  8962 */       pReason.x = 2294;
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  8969 */     if (pJmqiConnectOpts.isWebAdminConnection() && 
/*  8970 */       JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/*       */       
/*  8972 */       pSpiConnectOpts = this.sysenv.newSpiConnectOptions();
/*  8973 */       pSpiConnectOpts.setVersion(3);
/*  8974 */       pSpiConnectOpts.setOptions(16);
/*  8975 */       pSpiConnectOpts.setApplType(69);
/*  8976 */       requestedConnType = 2;
/*       */     } else {
/*       */       
/*  8979 */       requestedConnType = 1;
/*       */     } 
/*       */ 
/*       */ 
/*       */     
/*  8984 */     if ((this.useWorkerThread | this.adapterIsRRS) != 0) {
/*       */       
/*  8986 */       shareOption = pConnectOpts.getOptions() & 0xE0;
/*  8987 */       if (shareOption == 0) {
/*  8988 */         shareOption = 32;
/*       */       }
/*  8990 */       if (shareOption != 32 && shareOption != 64 && shareOption != 128) {
/*  8991 */         pCompCode.x = 2;
/*  8992 */         pReason.x = 2046;
/*       */       } 
/*       */     } 
/*       */     
/*  8996 */     int options = pConnectOpts.getOptions();
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  9001 */     if (!this.useSharedHconn || this.useWorkerThread) {
/*  9002 */       options &= 0xFFFFFFDF;
/*  9003 */       options &= 0xFFFFFFBF;
/*  9004 */       options &= 0xFFFFFF7F;
/*       */     } 
/*       */     
/*  9007 */     if (CSSystem.currentPlatform() != CSSystem.Platform.OS_ZSERIES && 
/*  9008 */       cmdLevel >= 913)
/*       */     {
/*       */ 
/*       */ 
/*       */       
/*  9013 */       if (parentHconn == null) {
/*       */         
/*  9015 */         options |= 0x200000;
/*       */       }
/*       */       else {
/*       */         
/*  9019 */         pConnectOpts.setConnTag(parentHconn.getConnTag());
/*       */       } 
/*       */     }
/*       */     
/*  9023 */     pConnectOpts.setOptions(options);
/*       */ 
/*       */ 
/*       */     
/*  9027 */     if (pReason.x == 0) {
/*  9028 */       JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*       */ 
/*       */       
/*       */       try {
/*  9032 */         byte[] rxpbBuf, spiBuf, qmNameBytes = new byte[48];
/*  9033 */         dc.writeMQField(pQMgrName, qmNameBytes, 0, 48, nativeCp, jTls);
/*       */ 
/*       */         
/*  9036 */         LocalHconn localhconn = getLocalHconn(phconn.getHconn());
/*  9037 */         localhconn.setQMNameBytes(qmNameBytes);
/*  9038 */         localhconn.setOriginalQueueManagerName(pQMgrName);
/*  9039 */         localhconn.setJmqiConnectOpts(pJmqiConnectOpts);
/*       */         
/*  9041 */         RXPB rxpb = null;
/*  9042 */         int originalRxpbFlags = 0;
/*       */         
/*  9044 */         if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/*  9045 */           rxpbBuf = null;
/*       */         } else {
/*       */           
/*  9048 */           Adapter adapter = getAdapter();
/*  9049 */           rxpb = adapter.buildNewRxpb(this.env, pConnectOpts);
/*       */           
/*  9051 */           originalRxpbFlags = rxpb.getFlags();
/*  9052 */           setRXPBFlags(tls, jTls, localhconn, rxpb, parentHconn);
/*  9053 */           int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/*  9054 */           if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/*  9055 */             tls.rxpbBuf = new byte[rxpbLen];
/*       */           }
/*  9057 */           rxpbBuf = tls.rxpbBuf;
/*  9058 */           rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         } 
/*       */ 
/*       */ 
/*       */         
/*  9063 */         if (pSpiConnectOpts != null) {
/*  9064 */           int spiLen = pSpiConnectOpts.getRequiredBufferSize(ptrSize, nativeCp);
/*  9065 */           spiBuf = new byte[spiLen];
/*  9066 */           pSpiConnectOpts.writeToBuffer(spiBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         } else {
/*       */           
/*  9069 */           spiBuf = null;
/*       */         } 
/*       */         
/*  9072 */         int origCnoVer = pConnectOpts.getVersion();
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  9079 */         if (cmdLevel >= 912 && JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/*  9080 */           String appName = JmqiTools.getAppName(pConnectOpts, pJmqiConnectOpts);
/*  9081 */           pConnectOpts.setApplName(appName);
/*       */         } 
/*       */ 
/*       */ 
/*       */         
/*  9086 */         int cnoLen = pConnectOpts.getRequiredBufferSize(ptrSize, nativeCp);
/*  9087 */         byte[] cnoBuf = new byte[cnoLen];
/*  9088 */         pConnectOpts.writeToBuffer(cnoBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */ 
/*       */         
/*  9091 */         performConnect(jTls, requestedConnType, qmNameBytes, rxpbBuf, spiBuf, cnoBuf, localhconn, -1, pCompCode, pReason);
/*       */ 
/*       */         
/*  9094 */         if (pReason.x == 2139)
/*       */         {
/*  9096 */           if (origCnoVer != pConnectOpts.getVersion()) {
/*       */             
/*  9098 */             pConnectOpts.setVersion(origCnoVer);
/*  9099 */             cnoLen = pConnectOpts.getRequiredBufferSize(ptrSize, nativeCp);
/*  9100 */             cnoBuf = new byte[cnoLen];
/*  9101 */             pConnectOpts.writeToBuffer(cnoBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */ 
/*       */             
/*  9104 */             performConnect(jTls, requestedConnType, qmNameBytes, rxpbBuf, spiBuf, cnoBuf, localhconn, -1, pCompCode, pReason);
/*       */           } 
/*       */         }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  9113 */         if (pReason.x == 2046) {
/*  9114 */           int opts = pConnectOpts.getOptions();
/*  9115 */           if (parentHconn == null) {
/*       */             
/*  9117 */             opts &= 0xFFDFFFFF;
/*  9118 */             pConnectOpts.setOptions(opts);
/*       */           }
/*       */           else {
/*       */             
/*  9122 */             pConnectOpts.setConnTag(MQConstants.MQCT_NONE);
/*       */           } 
/*  9124 */           cnoLen = pConnectOpts.getRequiredBufferSize(ptrSize, nativeCp);
/*  9125 */           cnoBuf = new byte[cnoLen];
/*  9126 */           pConnectOpts.writeToBuffer(cnoBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */ 
/*       */           
/*  9129 */           performConnect(jTls, requestedConnType, qmNameBytes, rxpbBuf, spiBuf, cnoBuf, localhconn, -1, pCompCode, pReason);
/*       */         } 
/*       */ 
/*       */         
/*  9133 */         localhconn.updateHconn(this, phconn);
/*  9134 */         localhconn.setShareOption(shareOption);
/*  9135 */         localhconn.setParent(parentHconn);
/*  9136 */         if (pConnectOpts != null) {
/*  9137 */           pConnectOpts.readFromBuffer(cnoBuf, 0, ptrSize, swap, nativeCp, jTls);
/*  9138 */           localhconn.setConnTag(pConnectOpts.getConnTag());
/*       */         } 
/*  9140 */         if (rxpb != null && rxpbBuf != null) {
/*  9141 */           rxpb.readFromBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */           
/*  9148 */           if (Trace.isOn) {
/*  9149 */             Trace.data("jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,final Pint,final Pint)", "RXPB after conn conn call", rxpb);
/*       */           }
/*  9151 */           rxpb.setFlags(originalRxpbFlags);
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  9158 */         if ((pCompCode.x == 0 || pCompCode.x == 1) && (
/*  9159 */           pQMgrName == null || "".equals(pQMgrName.trim()))) {
/*  9160 */           pQMgrName = localhconn.getName();
/*  9161 */           dc.writeMQField(pQMgrName, qmNameBytes, 0, 48, nativeCp, jTls);
/*  9162 */           localhconn.setQMNameBytes(qmNameBytes);
/*       */         } 
/*       */ 
/*       */ 
/*       */         
/*  9167 */         localhconn.setConnectionId(pConnectOpts.getConnectionId());
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  9187 */         if (isIMS() && pCompCode.x != 2) {
/*  9188 */           if (pCompCode.x == 0) {
/*  9189 */             jTls.resetIMSState(localhconn);
/*       */           }
/*  9191 */           else if (pCompCode.x == 1 && pReason.x == 2002) {
/*  9192 */             jTls.addHConnToIMSState(localhconn);
/*       */             
/*  9194 */             pCompCode.x = 0;
/*  9195 */             pReason.x = 0;
/*       */           } 
/*       */         }
/*       */ 
/*       */         
/*  9200 */         if (this.ffstOnError && pCompCode.x == 2) {
/*  9201 */           HashMap<String, Object> info = new HashMap<>();
/*  9202 */           info.put("JmqiTools.FFST_KEY_INSERT3", pJmqiConnectOpts);
/*  9203 */           info.put("JmqiTools.FFST_KEY_INSERT4", pConnectOpts);
/*  9204 */           info.put("ConnectOpts buffer", JmqiTools.toString(cnoBuf));
/*  9205 */           info.put("JmqiTools.FFST_KEY_INSERT5", phconn);
/*  9206 */           info.put("CompCode", pCompCode);
/*  9207 */           info.put("Reason", pReason);
/*  9208 */           Trace.ffst(this, "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,final Pint,final Pint)", "1", info, null);
/*       */         }
/*       */       
/*  9211 */       } catch (JmqiException e) {
/*  9212 */         if (Trace.isOn) {
/*  9213 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,final Pint,final Pint)", (Throwable)e, 1);
/*       */         }
/*       */         
/*  9216 */         jTls.lastException = e;
/*  9217 */         pCompCode.x = e.getCompCode();
/*  9218 */         pReason.x = e.getReason();
/*       */       }
/*  9220 */       catch (UnsupportedEncodingException e) {
/*  9221 */         if (Trace.isOn) {
/*  9222 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,final Pint,final Pint)", e, 2);
/*       */         }
/*       */         
/*  9225 */         pCompCode.x = 2;
/*  9226 */         pReason.x = 2340;
/*       */       }
/*  9228 */       catch (UnsatisfiedLinkError e) {
/*  9229 */         if (Trace.isOn) {
/*  9230 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,final Pint,final Pint)", e, 3);
/*       */         }
/*       */         
/*  9233 */         pCompCode.x = 2;
/*  9234 */         pReason.x = 2298;
/*       */       }
/*  9236 */       catch (Throwable e) {
/*  9237 */         if (Trace.isOn) {
/*  9238 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,final Pint,final Pint)", e, 4);
/*       */         }
/*       */         
/*  9241 */         castUnexpectedException("jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,final Pint,final Pint)", 1, e);
/*       */       } finally {
/*       */         
/*  9244 */         if (Trace.isOn) {
/*  9245 */           Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,final Pint,final Pint)");
/*       */         }
/*       */       } 
/*       */     } 
/*       */ 
/*       */ 
/*       */     
/*  9252 */     if (Trace.isOn) {
/*  9253 */       Trace.data(this, "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,final Pint,final Pint)", "__________");
/*  9254 */       Trace.data(this, "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,final Pint,final Pint)", "MQCONNX <<");
/*  9255 */       Trace.data(this, "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,final Pint,final Pint)", "JmqiConnectOptions", pJmqiConnectOpts);
/*  9256 */       Trace.data(this, "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,final Pint,final Pint)", "ConnectOpts", pConnectOpts);
/*  9257 */       Trace.data(this, "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,final Pint,final Pint)", "Hconn", phconn);
/*  9258 */       Trace.data(this, "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,final Pint,final Pint)", "CompCode", pCompCode);
/*  9259 */       Trace.data(this, "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,final Pint,final Pint)", "Reason", pReason);
/*  9260 */       Trace.data(this, "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,final Pint,final Pint)", "__________");
/*       */     } 
/*  9262 */     if (Trace.isOn) {
/*  9263 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,final Pint,final Pint)", 2);
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   private void setRXPBFlags(LocalTls tls, JmqiTls jTls, LocalHconn localhconn, RXPB rxpb, Hconn parentHconn) throws JmqiException {
/*  9278 */     if (Trace.isOn) {
/*  9279 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "setRXPBFlags(LocalTls,JmqiTls,LocalHconn,RXPB,Hconn)", new Object[] { tls, jTls, localhconn, rxpb, parentHconn });
/*       */     }
/*       */ 
/*       */     
/*  9283 */     String fid = "setRXPBFlags(LocalTls,JmqiTls,LocalHconn,RXPB,Hconn)";
/*       */     
/*  9285 */     if (Trace.isOn) {
/*  9286 */       Trace.data(this, "setRXPBFlags(LocalTls,JmqiTls,LocalHconn,RXPB,Hconn)", "LocalMQ.setRXPBFlags() >>");
/*       */     }
/*       */ 
/*       */     
/*  9290 */     localhconn.setRxpb(rxpb);
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  9295 */     int flags = rxpb.getFlags();
/*  9296 */     flags |= 0x2;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  9302 */     if (parentHconn != null && parentHconn instanceof LocalHconn == true) {
/*  9303 */       LocalHconn localParentHconn = (LocalHconn)parentHconn;
/*  9304 */       RXPB parentRXPB = localParentHconn.getRxpb();
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  9309 */       rxpb.setCtxTkn(parentRXPB.getCtxTkn());
/*       */ 
/*       */       
/*  9312 */       flags |= 0x8;
/*  9313 */       if (Trace.isOn) {
/*  9314 */         Trace.data(this, "setRXPBFlags(LocalTls,JmqiTls,LocalHconn,RXPB,Hconn)", "Creating a new context based on a parent context, setting copy uid flag");
/*       */       }
/*       */     } 
/*  9317 */     rxpb.setFlags(flags);
/*       */     
/*  9319 */     if (Trace.isOn) {
/*  9320 */       Trace.data(this, "setRXPBFlags(LocalTls,JmqiTls,LocalHconn,RXPB,Hconn)", "LocalMQ.setRXPBFlags(): RXPB.Flags = " + flags);
/*       */     }
/*  9322 */     if (Trace.isOn) {
/*  9323 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "setRXPBFlags(LocalTls,JmqiTls,LocalHconn,RXPB,Hconn)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   private RXPB getInheritedRxpb(LocalTls tls, JmqiTls jTls, LocalHconn localhconn) throws JmqiException {
/*  9340 */     if (Trace.isOn) {
/*  9341 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "getInheritedRxpb(LocalTls,JmqiTls,LocalHconn)", new Object[] { tls, jTls, localhconn });
/*       */     }
/*       */     
/*  9344 */     String fid = "getInheritedRxpb(LocalTls,JmqiTls,LocalHconn)";
/*  9345 */     if (Trace.isOn) {
/*  9346 */       Trace.data(this, "getInheritedRxpb(LocalTls,JmqiTls,LocalHconn)", "LocalMQ.getInheritedRxpb() >>");
/*       */     }
/*       */     
/*  9349 */     String qmname = localhconn.getOriginalQueueManagerName();
/*       */     
/*  9351 */     byte[] foundContext = null;
/*  9352 */     RXPB newrxpb = null;
/*       */ 
/*       */ 
/*       */     
/*  9356 */     boolean doit = this.inheritRRSContext;
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  9361 */     if (cmdLevel < 701) {
/*  9362 */       doit = false;
/*  9363 */       if (Trace.isOn) {
/*  9364 */         Trace.data(this, "getInheritedRxpb(LocalTls,JmqiTls,LocalHconn)", "cmdLevel: " + cmdLevel + ", The Queue Manager does not support RXPB version 3");
/*       */       }
/*       */     }
/*  9367 */     else if (qmname == null) {
/*  9368 */       doit = false;
/*  9369 */       if (Trace.isOn) {
/*  9370 */         Trace.data(this, "getInheritedRxpb(LocalTls,JmqiTls,LocalHconn)", "QMGR name is null. Can't check the Queue Manager name");
/*       */       }
/*       */     } else {
/*       */       
/*  9374 */       qmname = qmname.trim();
/*  9375 */       if (qmname.length() == 0) {
/*  9376 */         doit = false;
/*  9377 */         if (Trace.isOn) {
/*  9378 */           Trace.data(this, "getInheritedRxpb(LocalTls,JmqiTls,LocalHconn)", "QMGR name is empty string. Can't check the Queue Manager name");
/*       */         }
/*       */       } 
/*       */     } 
/*       */ 
/*       */     
/*  9384 */     long wasLocalUOWID = localhconn.getWASLocalWOWD();
/*  9385 */     if (wasLocalUOWID == -1L) {
/*  9386 */       doit = false;
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  9394 */     if (doit) {
/*       */       
/*  9396 */       newrxpb = this.sysenv.newRXPB();
/*  9397 */       newrxpb.setVersion(3);
/*  9398 */       newrxpb.setQMId(qmname);
/*  9399 */       newrxpb.setFlags(localhconn.getRxpb().getFlags());
/*  9400 */       newrxpb.setWasTranId(localhconn.getRxpb().getWasTranId());
/*       */       
/*  9402 */       if (Trace.isOn) {
/*  9403 */         Trace.data(this, "getInheritedRxpb(LocalTls,JmqiTls,LocalHconn)", "About to do RRS Optimization: TranID=" + wasLocalUOWID + " QMID=" + qmname + " rxpb now=" + localhconn
/*       */             
/*  9405 */             .getRxpb());
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  9421 */       if (wasLocalUOWID == jTls.getWASTranID()) {
/*  9422 */         if (Trace.isOn) {
/*  9423 */           Trace.data(this, "getInheritedRxpb(LocalTls,JmqiTls,LocalHconn)", "TranId unchanged as " + wasLocalUOWID);
/*       */         }
/*       */         
/*  9426 */         Map<String, byte[]> cachedCtx = jTls.getConnections();
/*  9427 */         foundContext = cachedCtx.get(qmname);
/*       */ 
/*       */ 
/*       */         
/*  9431 */         if (foundContext == null) {
/*       */           
/*  9433 */           foundContext = localhconn.getRxpb().getCtxTkn();
/*       */ 
/*       */ 
/*       */           
/*  9437 */           byte[] contextToStore = new byte[foundContext.length];
/*  9438 */           System.arraycopy(foundContext, 0, contextToStore, 0, foundContext.length);
/*       */           
/*  9440 */           cachedCtx.put(qmname, contextToStore);
/*  9441 */           if (Trace.isOn) {
/*  9442 */             Trace.data(this, "getInheritedRxpb(LocalTls,JmqiTls,LocalHconn)", "Storing new context as " + JmqiTools.arrayToHexString(contextToStore));
/*       */           }
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  9449 */         newrxpb.setCtxTkn(foundContext);
/*       */ 
/*       */       
/*       */       }
/*       */       else {
/*       */ 
/*       */ 
/*       */         
/*  9457 */         if (Trace.isOn) {
/*  9458 */           Trace.data(this, "getInheritedRxpb(LocalTls,JmqiTls,LocalHconn)", "Altered trand id: current=" + wasLocalUOWID + " previous=" + jTls.getWASTranID());
/*       */         }
/*  9460 */         Map<String, byte[]> cachedCtx = jTls.getConnections();
/*  9461 */         cachedCtx.clear();
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/*  9467 */         jTls.setWASTranID(wasLocalUOWID);
/*       */         
/*  9469 */         foundContext = localhconn.getRxpb().getCtxTkn();
/*       */ 
/*       */ 
/*       */         
/*  9473 */         byte[] contextToStore = new byte[foundContext.length];
/*  9474 */         System.arraycopy(foundContext, 0, contextToStore, 0, foundContext.length);
/*       */ 
/*       */         
/*  9477 */         cachedCtx.put(qmname, contextToStore);
/*       */ 
/*       */         
/*  9480 */         newrxpb.setCtxTkn(contextToStore);
/*       */         
/*  9482 */         if (Trace.isOn) {
/*  9483 */           Trace.data(this, "getInheritedRxpb(LocalTls,JmqiTls,LocalHconn)", "Storing new context=" + JmqiTools.arrayToHexString(contextToStore));
/*       */         }
/*       */       }
/*       */     
/*       */     }
/*       */     else {
/*       */       
/*  9490 */       newrxpb = localhconn.getRxpb();
/*       */     } 
/*       */     
/*  9493 */     if (Trace.isOn) {
/*  9494 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "getInheritedRxpb(LocalTls,JmqiTls,LocalHconn)", newrxpb);
/*       */     }
/*       */     
/*  9497 */     return newrxpb;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void spiSyncPoint(Hconn hconn, SpiSyncPointOptions pSpo, final Pint pCompCode, final Pint pReason) {
/*  9511 */     if (Trace.isOn) {
/*  9512 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiSyncPoint(Hconn,SpiSyncPointOptions,final Pint,final Pint)", new Object[] { hconn, pSpo, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */     
/*  9516 */     String fid = "spiSyncPoint(Hconn,SpiSyncPointOptions,final Pint,final Pint)";
/*       */ 
/*       */     
/*  9519 */     if (Trace.isOn) {
/*  9520 */       Trace.data(this, "spiSyncPoint(Hconn,SpiSyncPointOptions,final Pint,final Pint)", "__________");
/*  9521 */       Trace.data(this, "spiSyncPoint(Hconn,SpiSyncPointOptions,final Pint,final Pint)", "spiSyncPoint >>");
/*  9522 */       Trace.data(this, "spiSyncPoint(Hconn,SpiSyncPointOptions,final Pint,final Pint)", "Hconn", hconn);
/*  9523 */       Trace.data(this, "spiSyncPoint(Hconn,SpiSyncPointOptions,final Pint,final Pint)", "SpiSyncPointOptions", pSpo);
/*  9524 */       Trace.data(this, "spiSyncPoint(Hconn,SpiSyncPointOptions,final Pint,final Pint)", "CompCode", pCompCode);
/*  9525 */       Trace.data(this, "spiSyncPoint(Hconn,SpiSyncPointOptions,final Pint,final Pint)", "Reason", pReason);
/*  9526 */       Trace.data(this, "spiSyncPoint(Hconn,SpiSyncPointOptions,final Pint,final Pint)", "__________");
/*       */     } 
/*       */     
/*  9529 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  9530 */     final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/*  9531 */     LocalHconn lockedLocalhconn = null; try {
/*       */       final byte[] rxpbBuf, optBuf;
/*  9533 */       final LocalHconn localhconn = getLocalHconn(hconn);
/*  9534 */       final byte[] qmName = localhconn.getQMNameBytes();
/*       */       
/*  9536 */       RXPB rxpb = null;
/*       */       
/*  9538 */       if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/*  9539 */         rxpbBuf = null;
/*       */       } else {
/*       */         
/*  9542 */         if (this.inheritRRSContext) {
/*  9543 */           rxpb = getInheritedRxpb(tls, jTls, localhconn);
/*       */         } else {
/*       */           
/*  9546 */           rxpb = localhconn.getRxpb();
/*       */         } 
/*  9548 */         int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/*  9549 */         if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/*  9550 */           tls.rxpbBuf = new byte[rxpbLen];
/*       */         }
/*  9552 */         rxpbBuf = tls.rxpbBuf;
/*  9553 */         rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */       
/*  9557 */       if (pSpo == null) {
/*  9558 */         optBuf = null;
/*       */       } else {
/*       */         
/*  9561 */         int spoLen = pSpo.getRequiredBufferSize(ptrSize, nativeCp);
/*  9562 */         if (tls.optBuf == null || tls.optBuf.length < spoLen) {
/*  9563 */           tls.optBuf = new byte[spoLen];
/*       */         }
/*  9565 */         optBuf = tls.optBuf;
/*  9566 */         pSpo.writeToBuffer(optBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  9572 */       if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hconn)) {
/*  9573 */         localhconn.syncExec(new JmqiRunnable(this.env)
/*       */             {
/*       */               public void run()
/*       */               {
/*  9577 */                 if (Trace.isOn) {
/*  9578 */                   Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                 }
/*       */ 
/*       */                 
/*       */                 try {
/*  9583 */                   localhconn.enterCall();
/*       */                   
/*  9585 */                   boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  9586 */                   Native.spiSyncPoint(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, optBuf, pCompCode, pReason);
/*       */                 }
/*  9588 */                 catch (JmqiException e) {
/*  9589 */                   if (Trace.isOn) {
/*  9590 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                   }
/*       */                   
/*  9593 */                   jTls.lastException = e;
/*  9594 */                   pCompCode.x = e.getCompCode();
/*  9595 */                   pReason.x = e.getReason();
/*       */                 } finally {
/*       */                   
/*  9598 */                   if (Trace.isOn) {
/*  9599 */                     Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                   
/*       */                   try {
/*  9603 */                     localhconn.leaveCall();
/*       */                   }
/*  9605 */                   catch (JmqiException e) {
/*  9606 */                     if (Trace.isOn) {
/*  9607 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 2);
/*       */                     }
/*  9609 */                     jTls.lastException = e;
/*  9610 */                     pCompCode.x = e.getCompCode();
/*  9611 */                     pReason.x = e.getReason();
/*       */                   } 
/*       */                 } 
/*  9614 */                 if (Trace.isOn) {
/*  9615 */                   Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                 }
/*       */               }
/*       */             });
/*       */       }
/*       */       else {
/*       */         
/*  9622 */         if (this.adapterIsRRS) {
/*       */           
/*  9624 */           localhconn.enterCall();
/*  9625 */           lockedLocalhconn = localhconn;
/*       */         } 
/*  9627 */         boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/*  9628 */         Native.spiSyncPoint(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, optBuf, pCompCode, pReason);
/*       */       } 
/*       */ 
/*       */       
/*  9632 */       if (pSpo != null) {
/*  9633 */         pSpo.readFromBuffer(optBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       }
/*       */     }
/*  9636 */     catch (JmqiException e) {
/*  9637 */       if (Trace.isOn) {
/*  9638 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiSyncPoint(Hconn,SpiSyncPointOptions,final Pint,final Pint)", (Throwable)e, 1);
/*       */       }
/*       */       
/*  9641 */       jTls.lastException = e;
/*  9642 */       pCompCode.x = e.getCompCode();
/*  9643 */       pReason.x = e.getReason();
/*       */     }
/*  9645 */     catch (Throwable e) {
/*  9646 */       if (Trace.isOn) {
/*  9647 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiSyncPoint(Hconn,SpiSyncPointOptions,final Pint,final Pint)", e, 2);
/*       */       }
/*       */       
/*  9650 */       castUnexpectedException("spiSyncPoint(Hconn,SpiSyncPointOptions,final Pint,final Pint)", 1, e);
/*       */     } finally {
/*       */       
/*  9653 */       if (Trace.isOn) {
/*  9654 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiSyncPoint(Hconn,SpiSyncPointOptions,final Pint,final Pint)");
/*       */       }
/*       */       
/*  9657 */       if (lockedLocalhconn != null) {
/*       */         
/*       */         try {
/*  9660 */           lockedLocalhconn.leaveCall();
/*       */         }
/*  9662 */         catch (JmqiException e) {
/*  9663 */           if (Trace.isOn) {
/*  9664 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiSyncPoint(Hconn,SpiSyncPointOptions,final Pint,final Pint)", (Throwable)e, 3);
/*       */           }
/*       */           
/*  9667 */           jTls.lastException = e;
/*  9668 */           pCompCode.x = e.getCompCode();
/*  9669 */           pReason.x = e.getReason();
/*       */         } 
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*  9678 */       if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES && this.inheritRRSContext && 
/*  9679 */         pReason.x == 2009) {
/*  9680 */         clearInheritedRXPB(jTls);
/*       */       }
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  9687 */     if (Trace.isOn) {
/*  9688 */       Trace.data(this, "spiSyncPoint(Hconn,SpiSyncPointOptions,final Pint,final Pint)", "__________");
/*  9689 */       Trace.data(this, "spiSyncPoint(Hconn,SpiSyncPointOptions,final Pint,final Pint)", "spiSyncPoint <<");
/*  9690 */       Trace.data(this, "spiSyncPoint(Hconn,SpiSyncPointOptions,final Pint,final Pint)", "Hconn", hconn);
/*  9691 */       Trace.data(this, "spiSyncPoint(Hconn,SpiSyncPointOptions,final Pint,final Pint)", "SpiSyncPointOptions", pSpo);
/*  9692 */       Trace.data(this, "spiSyncPoint(Hconn,SpiSyncPointOptions,final Pint,final Pint)", "CompCode", pCompCode);
/*  9693 */       Trace.data(this, "spiSyncPoint(Hconn,SpiSyncPointOptions,final Pint,final Pint)", "Reason", pReason);
/*  9694 */       Trace.data(this, "spiSyncPoint(Hconn,SpiSyncPointOptions,final Pint,final Pint)", "__________");
/*       */     } 
/*  9696 */     if (Trace.isOn) {
/*  9697 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiSyncPoint(Hconn,SpiSyncPointOptions,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public ByteBuffer jmqiGet(Hconn hconn, Hobj hobj, MQMD md, MQGMO gmo, int expectedMsgLength, int maxMsgLength, PbyteBuffer pByteBuffer, Pint pMsgTooSmallForBufferCount, Pint pDataLength, Pint pCompCode, Pint pReason) {
/*  9734 */     if (Trace.isOn) {
/*  9735 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "jmqiGet(Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", new Object[] { hconn, hobj, md, gmo, 
/*       */             
/*  9737 */             Integer.valueOf(expectedMsgLength), Integer.valueOf(maxMsgLength), pByteBuffer, pMsgTooSmallForBufferCount, pDataLength, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */     
/*  9741 */     String fid = "jmqiGet(Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)";
/*       */ 
/*       */     
/*  9744 */     int originalOptions = gmo.getOptions();
/*  9745 */     int originalMatchOptions = gmo.getMatchOptions();
/*       */     
/*  9747 */     if ((originalOptions & 0x1006) == 0) {
/*  9748 */       gmo.setOptions(originalOptions | 0x4);
/*       */     }
/*  9750 */     ByteBuffer traceRet1 = JmqiTools.getMessage(this.env, this, hconn, hobj, md, gmo, expectedMsgLength, maxMsgLength, pByteBuffer, pMsgTooSmallForBufferCount, pDataLength, pCompCode, pReason);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  9763 */     gmo.setOptions(originalOptions);
/*  9764 */     gmo.setMatchOptions(originalMatchOptions);
/*       */ 
/*       */     
/*  9767 */     if (Trace.isOn) {
/*  9768 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "jmqiGet(Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", traceRet1);
/*       */     }
/*       */     
/*  9771 */     return traceRet1;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void jmqiGetInternal(Hconn hconn, Hobj hobj, MQMD md, MQGMO gmo, int bufferLength, ByteBuffer buffer, Pint pDataLength, Pint pCompCode, Pint pReason) {
/*  9797 */     if (Trace.isOn) {
/*  9798 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "jmqiGetInternal(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,Pint,Pint)", new Object[] { hconn, hobj, md, gmo, 
/*       */             
/*  9800 */             Integer.valueOf(bufferLength), buffer, pDataLength, pCompCode, pReason });
/*       */     }
/*       */     
/*  9803 */     MQGET(hconn, hobj, md, gmo, bufferLength, buffer, pDataLength, pCompCode, pReason);
/*       */     
/*  9805 */     if (Trace.isOn) {
/*  9806 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "jmqiGetInternal(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,Pint,Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean jmqiConvertMessage(Hconn hconn, Hobj hobj, int encoding, int ccsid, int options, boolean callExitOnLenErr, MQMD md, ByteBuffer buffer, Pint pDataLength, int availableLength, int bufferLength, Pint pCompCode, Pint pReason, Pint pReturnedLength) {
/*  9846 */     if (Trace.isOn) {
/*  9847 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "jmqiConvertMessage(Hconn,Hobj,int,int,int,boolean,MQMD,ByteBuffer,Pint,int,int,Pint,Pint,Pint)", new Object[] { hconn, hobj, 
/*       */             
/*  9849 */             Integer.valueOf(encoding), Integer.valueOf(ccsid), 
/*  9850 */             Integer.valueOf(options), Boolean.valueOf(callExitOnLenErr), md, buffer, pDataLength, 
/*  9851 */             Integer.valueOf(availableLength), Integer.valueOf(bufferLength), pCompCode, pReason, pReturnedLength });
/*       */     }
/*       */ 
/*       */     
/*  9855 */     vpiConvertData(hconn, encoding, ccsid, options, callExitOnLenErr, md, buffer, pDataLength, availableLength, bufferLength, pCompCode, pReason, pReturnedLength);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*  9872 */     if (Trace.isOn) {
/*  9873 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "jmqiConvertMessage(Hconn,Hobj,int,int,int,boolean,MQMD,ByteBuffer,Pint,int,int,Pint,Pint,Pint)", 
/*       */           
/*  9875 */           Boolean.valueOf(false));
/*       */     }
/*  9877 */     return false;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void spiGet(Hconn hconn, Hobj hobj, MQMD pMsgDesc, MQGMO pGetMsgOpts, SpiGetOptions pSpiGetOpts, final int BufferLength, ByteBuffer pBuffer, final Pint pDataLength, final Pint pCompCode, final Pint pReason) {
/*  9906 */     if (Trace.isOn) {
/*  9907 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,final int,ByteBuffer,final Pint,final Pint,final Pint)", new Object[] { hconn, hobj, pMsgDesc, pGetMsgOpts, pSpiGetOpts, 
/*       */             
/*  9909 */             Integer.valueOf(BufferLength), pBuffer, pDataLength, pCompCode, pReason });
/*       */     }
/*  9911 */     if (pBuffer == null) {
/*  9912 */       NullPointerException npe = new NullPointerException();
/*  9913 */       if (Trace.isOn) {
/*  9914 */         Trace.throwing("com.ibm.mq.jmqi.local.LocalMQ", "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,final int,ByteBuffer,final Pint,final Pint,final Pint)", npe);
/*       */       }
/*       */ 
/*       */       
/*  9918 */       if (Trace.isOn) {
/*  9919 */         Trace.throwing(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,final int,ByteBuffer,final Pint,final Pint,final Pint)", npe, 1);
/*       */       }
/*       */ 
/*       */       
/*  9923 */       throw npe;
/*       */     } 
/*  9925 */     String fid = "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,final int,ByteBuffer,final Pint,final Pint,final Pint)";
/*       */ 
/*       */     
/*  9928 */     if (Trace.isOn) {
/*  9929 */       Trace.data(this, "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,final int,ByteBuffer,final Pint,final Pint,final Pint)", "__________");
/*  9930 */       Trace.data(this, "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,final int,ByteBuffer,final Pint,final Pint,final Pint)", "spiGet >>");
/*  9931 */       Trace.data(this, "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Hconn", hconn);
/*  9932 */       Trace.data(this, "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Hobj", hobj);
/*  9933 */       Trace.data(this, "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Msgdesc", pMsgDesc);
/*  9934 */       Trace.data(this, "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Getmsgops", pGetMsgOpts);
/*  9935 */       Trace.data(this, "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,final int,ByteBuffer,final Pint,final Pint,final Pint)", "SpiGetOptions", pSpiGetOpts);
/*  9936 */       Trace.data(this, "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Bufferlength", Integer.toString(BufferLength));
/*  9937 */       Trace.data(this, "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Datalength", pDataLength);
/*  9938 */       Trace.data(this, "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,final int,ByteBuffer,final Pint,final Pint,final Pint)", "CompCode", pCompCode);
/*  9939 */       Trace.data(this, "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Reason", pReason);
/*  9940 */       Trace.data(this, "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,final int,ByteBuffer,final Pint,final Pint,final Pint)", "__________");
/*       */     } 
/*  9942 */     pCompCode.x = 0;
/*  9943 */     pReason.x = 0;
/*       */ 
/*       */     
/*  9946 */     if (BufferLength > 0 && 
/*  9947 */       BufferLength > pBuffer.limit()) {
/*  9948 */       Trace.data(this, "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,final int,ByteBuffer,final Pint,final Pint,final Pint)", "BufferLength:" + BufferLength + ", pBuffer.limit():" + pBuffer.limit(), null);
/*  9949 */       pCompCode.x = 2;
/*  9950 */       pReason.x = 2005;
/*       */     } 
/*       */     
/*  9953 */     if (pReason.x == 0) {
/*       */       
/*  9955 */       LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/*  9956 */       final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/*  9957 */       LocalHconn lockedLocalhconn = null;
/*       */ 
/*       */       
/*  9960 */       final byte[] pBufferArray = pBuffer.array(); try {
/*       */         final byte[] rxpbBuf, mqmdBuf, optBuf, spiOptBuf;
/*  9962 */         final LocalHconn localhconn = getLocalHconn(hconn);
/*  9963 */         final LocalHobj localhobj = LocalHobj.getLocalHobj(this.env, hobj);
/*  9964 */         final byte[] qmName = localhconn.getQMNameBytes();
/*       */         
/*  9966 */         RXPB rxpb = null;
/*       */         
/*  9968 */         if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/*  9969 */           rxpbBuf = null;
/*       */         } else {
/*       */           
/*  9972 */           if (this.inheritRRSContext) {
/*  9973 */             rxpb = getInheritedRxpb(tls, jTls, localhconn);
/*  9974 */             if (!Arrays.equals(localhobj.getCtxToken(), rxpb.getCtxTkn()))
/*       */             {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */               
/*  9982 */               JmqiException e = new JmqiException(this.env, -1, null, 2, 2298, null);
/*       */               
/*  9984 */               if (Trace.isOn) {
/*  9985 */                 Trace.throwing(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,final int,ByteBuffer,final Pint,final Pint,final Pint)", (Throwable)e, 2);
/*       */               }
/*       */ 
/*       */               
/*  9989 */               throw e;
/*       */             }
/*       */           
/*       */           } else {
/*       */             
/*  9994 */             rxpb = localhconn.getRxpb();
/*       */           } 
/*  9996 */           int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/*  9997 */           if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/*  9998 */             tls.rxpbBuf = new byte[rxpbLen];
/*       */           }
/* 10000 */           rxpbBuf = tls.rxpbBuf;
/* 10001 */           rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         } 
/*       */ 
/*       */         
/* 10005 */         if (pMsgDesc == null) {
/* 10006 */           mqmdBuf = null;
/*       */         } else {
/*       */           
/* 10009 */           int mqmdLen = pMsgDesc.getRequiredBufferSize(ptrSize, nativeCp);
/* 10010 */           if (tls.mqmdBuf == null || tls.mqmdBuf.length < mqmdLen) {
/* 10011 */             tls.mqmdBuf = new byte[mqmdLen];
/*       */           }
/* 10013 */           mqmdBuf = tls.mqmdBuf;
/* 10014 */           pMsgDesc.writeToBuffer(mqmdBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         } 
/*       */ 
/*       */         
/* 10018 */         if (pGetMsgOpts == null) {
/* 10019 */           optBuf = null;
/*       */         } else {
/*       */           
/* 10022 */           int gmoLen = pGetMsgOpts.getRequiredBufferSize(ptrSize, nativeCp);
/* 10023 */           if (tls.optBuf == null || tls.optBuf.length < gmoLen) {
/* 10024 */             tls.optBuf = new byte[gmoLen];
/*       */           }
/* 10026 */           optBuf = tls.optBuf;
/* 10027 */           pGetMsgOpts.writeToBuffer(optBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         } 
/*       */ 
/*       */ 
/*       */         
/* 10032 */         if (pSpiGetOpts == null) {
/* 10033 */           spiOptBuf = null;
/*       */         } else {
/*       */           
/* 10036 */           int spgoLen = pSpiGetOpts.getRequiredBufferSize(ptrSize, nativeCp);
/* 10037 */           if (tls.descBuf == null || tls.descBuf.length < spgoLen) {
/* 10038 */             tls.descBuf = new byte[spgoLen];
/*       */           }
/* 10040 */           spiOptBuf = tls.descBuf;
/* 10041 */           pSpiGetOpts.writeToBuffer(spiOptBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/* 10047 */         if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hconn)) {
/* 10048 */           localhconn.syncExec(new JmqiRunnable(this.env)
/*       */               {
/*       */                 public void run()
/*       */                 {
/* 10052 */                   if (Trace.isOn) {
/* 10053 */                     Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                   }
/*       */ 
/*       */                   
/*       */                   try {
/* 10058 */                     localhconn.enterCall();
/*       */ 
/*       */                     
/* 10061 */                     boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 10062 */                     Native.spiGet(isClassTraced, localhconn
/* 10063 */                         .getValue(), qmName, rxpbBuf, localhobj
/*       */ 
/*       */                         
/* 10066 */                         .getValue(), mqmdBuf, optBuf, spiOptBuf, BufferLength, pBufferArray, pDataLength, pCompCode, pReason);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */                   
/*       */                   }
/* 10076 */                   catch (JmqiException e) {
/* 10077 */                     if (Trace.isOn) {
/* 10078 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                     }
/*       */                     
/* 10081 */                     jTls.lastException = e;
/* 10082 */                     pCompCode.x = e.getCompCode();
/* 10083 */                     pReason.x = e.getReason();
/*       */                   } finally {
/*       */                     
/* 10086 */                     if (Trace.isOn) {
/* 10087 */                       Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                     }
/*       */                     
/*       */                     try {
/* 10091 */                       localhconn.leaveCall();
/*       */                     }
/* 10093 */                     catch (JmqiException e) {
/* 10094 */                       if (Trace.isOn) {
/* 10095 */                         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 2);
/*       */                       }
/* 10097 */                       jTls.lastException = e;
/* 10098 */                       pCompCode.x = e.getCompCode();
/* 10099 */                       pReason.x = e.getReason();
/*       */                     } 
/*       */                   } 
/* 10102 */                   if (Trace.isOn) {
/* 10103 */                     Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                 }
/*       */               });
/*       */         }
/*       */         else {
/*       */           
/* 10110 */           if (this.adapterIsRRS) {
/*       */             
/* 10112 */             localhconn.enterCall();
/* 10113 */             lockedLocalhconn = localhconn;
/*       */           } 
/* 10115 */           boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 10116 */           Native.spiGet(isClassTraced, localhconn
/* 10117 */               .getValue(), qmName, rxpbBuf, localhobj
/*       */ 
/*       */               
/* 10120 */               .getValue(), mqmdBuf, optBuf, spiOptBuf, BufferLength, pBufferArray, pDataLength, pCompCode, pReason);
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/* 10132 */         if (pMsgDesc != null) {
/* 10133 */           pMsgDesc.readFromBuffer(mqmdBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         }
/* 10135 */         if (pGetMsgOpts != null) {
/* 10136 */           pGetMsgOpts.readFromBuffer(optBuf, 0, true, ptrSize, swap, nativeCp, jTls);
/*       */         }
/* 10138 */         if (pSpiGetOpts != null) {
/* 10139 */           pSpiGetOpts.readFromBuffer(spiOptBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         }
/*       */       }
/* 10142 */       catch (JmqiException e) {
/* 10143 */         if (Trace.isOn) {
/* 10144 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,final int,ByteBuffer,final Pint,final Pint,final Pint)", (Throwable)e, 1);
/*       */         }
/*       */ 
/*       */         
/* 10148 */         jTls.lastException = e;
/* 10149 */         pCompCode.x = e.getCompCode();
/* 10150 */         pReason.x = e.getReason();
/*       */       }
/* 10152 */       catch (Throwable e) {
/* 10153 */         if (Trace.isOn) {
/* 10154 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,final int,ByteBuffer,final Pint,final Pint,final Pint)", e, 2);
/*       */         }
/*       */ 
/*       */         
/* 10158 */         castUnexpectedException("spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,final int,ByteBuffer,final Pint,final Pint,final Pint)", 1, e);
/*       */       } finally {
/*       */         
/* 10161 */         if (Trace.isOn) {
/* 10162 */           Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,final int,ByteBuffer,final Pint,final Pint,final Pint)");
/*       */         }
/*       */         
/* 10165 */         if (lockedLocalhconn != null) {
/*       */           
/*       */           try {
/* 10168 */             lockedLocalhconn.leaveCall();
/*       */           }
/* 10170 */           catch (JmqiException e) {
/* 10171 */             if (Trace.isOn) {
/* 10172 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,final int,ByteBuffer,final Pint,final Pint,final Pint)", (Throwable)e, 3);
/*       */             }
/*       */ 
/*       */             
/* 10176 */             jTls.lastException = e;
/* 10177 */             pCompCode.x = e.getCompCode();
/* 10178 */             pReason.x = e.getReason();
/*       */           } 
/*       */         }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/* 10190 */         if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES && this.inheritRRSContext && 
/* 10191 */           pReason.x == 2009) {
/* 10192 */           clearInheritedRXPB(jTls);
/*       */         }
/*       */       } 
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/* 10200 */     if (Trace.isOn) {
/* 10201 */       Trace.data(this, "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,final int,ByteBuffer,final Pint,final Pint,final Pint)", "__________");
/* 10202 */       Trace.data(this, "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,final int,ByteBuffer,final Pint,final Pint,final Pint)", "spiGet <<");
/* 10203 */       Trace.data(this, "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Hconn", hconn);
/* 10204 */       Trace.data(this, "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Hobj", hobj);
/* 10205 */       Trace.data(this, "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Msgdesc", pMsgDesc);
/* 10206 */       Trace.data(this, "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Getmsgops", pGetMsgOpts);
/* 10207 */       Trace.data(this, "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,final int,ByteBuffer,final Pint,final Pint,final Pint)", "SpiGetOptions", pSpiGetOpts);
/* 10208 */       Trace.data(this, "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Bufferlength", Integer.toString(BufferLength));
/* 10209 */       JmqiTools.traceApiBuffer(this, "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,final int,ByteBuffer,final Pint,final Pint,final Pint)", pBuffer, BufferLength);
/* 10210 */       Trace.data(this, "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Datalength", pDataLength);
/* 10211 */       Trace.data(this, "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,final int,ByteBuffer,final Pint,final Pint,final Pint)", "CompCode", pCompCode);
/* 10212 */       Trace.data(this, "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,final int,ByteBuffer,final Pint,final Pint,final Pint)", "Reason", pReason);
/* 10213 */       Trace.data(this, "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,final int,ByteBuffer,final Pint,final Pint,final Pint)", "__________");
/*       */     } 
/* 10215 */     if (Trace.isOn) {
/* 10216 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,final int,ByteBuffer,final Pint,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void spiPut(Hconn hconn, Hobj hobj, MQMD pMsgDesc, MQPMO pPutMsgOpts, SpiPutOptions pSpiPutOpts, final int BufferLength, ByteBuffer pBuffer, final Pint pCompCode, final Pint pReason) {
/* 10246 */     if (Trace.isOn) {
/* 10247 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,final int,ByteBuffer,final Pint,final Pint)", new Object[] { hconn, hobj, pMsgDesc, pPutMsgOpts, pSpiPutOpts, 
/*       */ 
/*       */             
/* 10250 */             Integer.valueOf(BufferLength), pBuffer, pCompCode, pReason });
/*       */     }
/* 10252 */     String fid = "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,final int,ByteBuffer,final Pint,final Pint)";
/*       */ 
/*       */     
/* 10255 */     if (Trace.isOn) {
/* 10256 */       Trace.data(this, "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,final int,ByteBuffer,final Pint,final Pint)", "__________");
/* 10257 */       Trace.data(this, "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,final int,ByteBuffer,final Pint,final Pint)", "spiPut >>");
/* 10258 */       Trace.data(this, "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,final int,ByteBuffer,final Pint,final Pint)", "Hconn", hconn);
/* 10259 */       Trace.data(this, "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,final int,ByteBuffer,final Pint,final Pint)", "Hobj", hobj);
/* 10260 */       Trace.data(this, "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,final int,ByteBuffer,final Pint,final Pint)", "Msgdesc", pMsgDesc);
/* 10261 */       Trace.data(this, "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,final int,ByteBuffer,final Pint,final Pint)", "Putmsgopts", pPutMsgOpts);
/* 10262 */       Trace.data(this, "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,final int,ByteBuffer,final Pint,final Pint)", "SpiPutOptions", pSpiPutOpts);
/* 10263 */       Trace.data(this, "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,final int,ByteBuffer,final Pint,final Pint)", "BufferLength" + BufferLength);
/* 10264 */       if (pBuffer == null) {
/* 10265 */         Trace.data(this, "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,final int,ByteBuffer,final Pint,final Pint)", "buffer is (null)");
/*       */       } else {
/*       */         
/* 10268 */         JmqiTools.traceApiBuffer(this, "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,final int,ByteBuffer,final Pint,final Pint)", pBuffer, pBuffer.limit());
/*       */       } 
/* 10270 */       Trace.data(this, "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,final int,ByteBuffer,final Pint,final Pint)", "CompCode", pCompCode);
/* 10271 */       Trace.data(this, "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,final int,ByteBuffer,final Pint,final Pint)", "Reason", pReason);
/* 10272 */       Trace.data(this, "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,final int,ByteBuffer,final Pint,final Pint)", "__________");
/*       */     } 
/* 10274 */     pCompCode.x = 0;
/* 10275 */     pReason.x = 0;
/*       */ 
/*       */     
/* 10278 */     if (BufferLength > 0) {
/* 10279 */       if (pBuffer == null) {
/* 10280 */         Trace.data(this, "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,final int,ByteBuffer,final Pint,final Pint)", "BufferLength: " + BufferLength + ", pBuffer is (null)", null);
/* 10281 */         pCompCode.x = 2;
/* 10282 */         pReason.x = 2005;
/*       */       }
/* 10284 */       else if (BufferLength > pBuffer.limit()) {
/* 10285 */         Trace.data(this, "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,final int,ByteBuffer,final Pint,final Pint)", "BufferLength:" + BufferLength + ", pBuffer.limit():" + pBuffer.limit(), null);
/* 10286 */         pCompCode.x = 2;
/* 10287 */         pReason.x = 2005;
/*       */       }
/*       */     
/* 10290 */     } else if (BufferLength < 0) {
/* 10291 */       Trace.data(this, "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,final int,ByteBuffer,final Pint,final Pint)", "BufferLength is negative: " + BufferLength, null);
/* 10292 */       pCompCode.x = 2;
/* 10293 */       pReason.x = 2005;
/*       */     } 
/* 10295 */     if (pReason.x == 0) {
/*       */       
/* 10297 */       LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 10298 */       final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/* 10299 */       LocalHconn lockedLocalhconn = null; try {
/*       */         final byte[] rxpbBuf, mqmdBuf, optBuf, spiOptBuf, pBufferArray;
/* 10301 */         final LocalHconn localhconn = getLocalHconn(hconn);
/* 10302 */         final LocalHobj localhobj = LocalHobj.getLocalHobj(this.env, hobj);
/* 10303 */         final byte[] qmName = localhconn.getQMNameBytes();
/*       */         
/* 10305 */         RXPB rxpb = null;
/*       */         
/* 10307 */         if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/* 10308 */           rxpbBuf = null;
/*       */         } else {
/*       */           
/* 10311 */           if (this.inheritRRSContext) {
/* 10312 */             rxpb = getInheritedRxpb(tls, jTls, localhconn);
/* 10313 */             if (!Arrays.equals(localhobj.getCtxToken(), rxpb.getCtxTkn()))
/*       */             {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */               
/* 10321 */               JmqiException e = new JmqiException(this.env, -1, null, 2, 2298, null);
/*       */               
/* 10323 */               if (Trace.isOn) {
/* 10324 */                 Trace.throwing(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,final int,ByteBuffer,final Pint,final Pint)", (Throwable)e);
/*       */               }
/*       */ 
/*       */               
/* 10328 */               throw e;
/*       */             }
/*       */           
/*       */           } else {
/*       */             
/* 10333 */             rxpb = localhconn.getRxpb();
/*       */           } 
/* 10335 */           int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/* 10336 */           if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/* 10337 */             tls.rxpbBuf = new byte[rxpbLen];
/*       */           }
/* 10339 */           rxpbBuf = tls.rxpbBuf;
/* 10340 */           rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         } 
/*       */ 
/*       */         
/* 10344 */         if (pMsgDesc == null) {
/* 10345 */           mqmdBuf = null;
/*       */         } else {
/*       */           
/* 10348 */           int mqmdLen = pMsgDesc.getRequiredBufferSize(ptrSize, nativeCp);
/* 10349 */           if (tls.mqmdBuf == null || tls.mqmdBuf.length < mqmdLen) {
/* 10350 */             tls.mqmdBuf = new byte[mqmdLen];
/*       */           }
/* 10352 */           mqmdBuf = tls.mqmdBuf;
/* 10353 */           pMsgDesc.writeToBuffer(mqmdBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         } 
/*       */ 
/*       */         
/* 10357 */         if (pPutMsgOpts == null) {
/* 10358 */           optBuf = null;
/*       */         } else {
/*       */           
/* 10361 */           int pmoLen = pPutMsgOpts.getRequiredBufferSize(ptrSize, nativeCp);
/* 10362 */           if (tls.optBuf == null || tls.optBuf.length < pmoLen) {
/* 10363 */             tls.optBuf = new byte[pmoLen];
/*       */           }
/* 10365 */           optBuf = tls.optBuf;
/* 10366 */           pPutMsgOpts.writeToBuffer(optBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         } 
/*       */ 
/*       */ 
/*       */         
/* 10371 */         if (pSpiPutOpts == null) {
/* 10372 */           spiOptBuf = null;
/*       */         } else {
/*       */           
/* 10375 */           int sppoLen = pSpiPutOpts.getRequiredBufferSize(ptrSize, nativeCp);
/* 10376 */           if (tls.descBuf == null || tls.descBuf.length < sppoLen) {
/* 10377 */             tls.descBuf = new byte[sppoLen];
/*       */           }
/* 10379 */           spiOptBuf = tls.descBuf;
/* 10380 */           pSpiPutOpts.writeToBuffer(spiOptBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         } 
/*       */ 
/*       */ 
/*       */         
/* 10385 */         if (pBuffer == null) {
/* 10386 */           pBufferArray = null;
/*       */         } else {
/*       */           
/* 10389 */           pBufferArray = pBuffer.array();
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/* 10395 */         if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hconn)) {
/* 10396 */           localhconn.syncExec(new JmqiRunnable(this.env)
/*       */               {
/*       */                 public void run()
/*       */                 {
/* 10400 */                   if (Trace.isOn) {
/* 10401 */                     Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                   }
/*       */ 
/*       */                   
/*       */                   try {
/* 10406 */                     localhconn.enterCall();
/*       */ 
/*       */                     
/* 10409 */                     boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 10410 */                     Native.spiPut(isClassTraced, localhconn
/* 10411 */                         .getValue(), qmName, rxpbBuf, localhobj
/*       */ 
/*       */                         
/* 10414 */                         .getValue(), mqmdBuf, optBuf, spiOptBuf, BufferLength, pBufferArray, pCompCode, pReason);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */                   
/*       */                   }
/* 10423 */                   catch (JmqiException e) {
/* 10424 */                     if (Trace.isOn) {
/* 10425 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                     }
/*       */                     
/* 10428 */                     jTls.lastException = e;
/* 10429 */                     pCompCode.x = e.getCompCode();
/* 10430 */                     pReason.x = e.getReason();
/*       */                   } finally {
/*       */                     
/* 10433 */                     if (Trace.isOn) {
/* 10434 */                       Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                     }
/*       */                     
/*       */                     try {
/* 10438 */                       localhconn.leaveCall();
/*       */                     }
/* 10440 */                     catch (JmqiException e) {
/* 10441 */                       if (Trace.isOn) {
/* 10442 */                         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 2);
/*       */                       }
/* 10444 */                       jTls.lastException = e;
/* 10445 */                       pCompCode.x = e.getCompCode();
/* 10446 */                       pReason.x = e.getReason();
/*       */                     } 
/*       */                   } 
/* 10449 */                   if (Trace.isOn) {
/* 10450 */                     Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                 }
/*       */               });
/*       */         }
/*       */         else {
/*       */           
/* 10457 */           if (this.adapterIsRRS) {
/*       */             
/* 10459 */             localhconn.enterCall();
/* 10460 */             lockedLocalhconn = localhconn;
/*       */           } 
/* 10462 */           boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 10463 */           Native.spiPut(isClassTraced, localhconn
/* 10464 */               .getValue(), qmName, rxpbBuf, localhobj
/*       */ 
/*       */               
/* 10467 */               .getValue(), mqmdBuf, optBuf, spiOptBuf, BufferLength, pBufferArray, pCompCode, pReason);
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/* 10478 */         if (pMsgDesc != null) {
/* 10479 */           pMsgDesc.readFromBuffer(mqmdBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         }
/* 10481 */         if (pPutMsgOpts != null) {
/* 10482 */           pPutMsgOpts.readFromBuffer(optBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         }
/* 10484 */         if (pSpiPutOpts != null) {
/* 10485 */           pSpiPutOpts.readFromBuffer(spiOptBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         }
/*       */       }
/* 10488 */       catch (JmqiException e) {
/* 10489 */         if (Trace.isOn) {
/* 10490 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,final int,ByteBuffer,final Pint,final Pint)", (Throwable)e, 1);
/*       */         }
/*       */ 
/*       */         
/* 10494 */         jTls.lastException = e;
/* 10495 */         pCompCode.x = e.getCompCode();
/* 10496 */         pReason.x = e.getReason();
/*       */       }
/* 10498 */       catch (Throwable e) {
/* 10499 */         if (Trace.isOn) {
/* 10500 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,final int,ByteBuffer,final Pint,final Pint)", e, 2);
/*       */         }
/*       */ 
/*       */         
/* 10504 */         castUnexpectedException("spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,final int,ByteBuffer,final Pint,final Pint)", 1, e);
/*       */       } finally {
/*       */         
/* 10507 */         if (Trace.isOn) {
/* 10508 */           Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,final int,ByteBuffer,final Pint,final Pint)");
/*       */         }
/*       */         
/* 10511 */         if (lockedLocalhconn != null) {
/*       */           
/*       */           try {
/* 10514 */             lockedLocalhconn.leaveCall();
/*       */           }
/* 10516 */           catch (JmqiException e) {
/* 10517 */             if (Trace.isOn) {
/* 10518 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,final int,ByteBuffer,final Pint,final Pint)", (Throwable)e, 3);
/*       */             }
/*       */ 
/*       */             
/* 10522 */             jTls.lastException = e;
/* 10523 */             pCompCode.x = e.getCompCode();
/* 10524 */             pReason.x = e.getReason();
/*       */           } 
/*       */         }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/* 10536 */         if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES && this.inheritRRSContext && 
/* 10537 */           pReason.x == 2009) {
/* 10538 */           clearInheritedRXPB(jTls);
/*       */         }
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 10545 */       if (Trace.isOn) {
/* 10546 */         Trace.data(this, "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,final int,ByteBuffer,final Pint,final Pint)", "__________");
/* 10547 */         Trace.data(this, "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,final int,ByteBuffer,final Pint,final Pint)", "spiPut <<");
/* 10548 */         Trace.data(this, "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,final int,ByteBuffer,final Pint,final Pint)", "Hconn", hconn);
/* 10549 */         Trace.data(this, "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,final int,ByteBuffer,final Pint,final Pint)", "Hobj", hobj);
/* 10550 */         Trace.data(this, "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,final int,ByteBuffer,final Pint,final Pint)", "Msgdesc", pMsgDesc);
/* 10551 */         Trace.data(this, "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,final int,ByteBuffer,final Pint,final Pint)", "Putmsgopts", pPutMsgOpts);
/* 10552 */         Trace.data(this, "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,final int,ByteBuffer,final Pint,final Pint)", "SpiPutOptions", pSpiPutOpts);
/* 10553 */         Trace.data(this, "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,final int,ByteBuffer,final Pint,final Pint)", "Bufferlength", "input");
/* 10554 */         Trace.data(this, "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,final int,ByteBuffer,final Pint,final Pint)", "CompCode", pCompCode);
/* 10555 */         Trace.data(this, "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,final int,ByteBuffer,final Pint,final Pint)", "Reason", pReason);
/* 10556 */         Trace.data(this, "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,final int,ByteBuffer,final Pint,final Pint)", "__________");
/*       */       } 
/*       */     } 
/* 10559 */     if (Trace.isOn) {
/* 10560 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,final int,ByteBuffer,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void spiPut(Hconn hconn, Hobj hobj, MQMD pMsgDesc, MQPMO pPutMsgOpts, SpiPutOptions pSpiPutOpts, ByteBuffer[] pBuffers, Pint pCompCode, Pint pReason) {
/* 10582 */     if (Trace.isOn) {
/* 10583 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,ByteBuffer [ ],final Pint,final Pint)", new Object[] { hconn, hobj, pMsgDesc, pPutMsgOpts, pSpiPutOpts, pBuffers, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */ 
/*       */     
/* 10588 */     String fid = "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,ByteBuffer[],final Pint,final Pint)";
/*       */ 
/*       */     
/* 10591 */     if (Trace.isOn) {
/* 10592 */       Trace.data(this, "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,ByteBuffer[],final Pint,final Pint)", "__________");
/* 10593 */       Trace.data(this, "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,ByteBuffer[],final Pint,final Pint)", "spiPut >>");
/* 10594 */       Trace.data(this, "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,ByteBuffer[],final Pint,final Pint)", "Hconn", hconn);
/* 10595 */       Trace.data(this, "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,ByteBuffer[],final Pint,final Pint)", "Hobj", hobj);
/* 10596 */       Trace.data(this, "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,ByteBuffer[],final Pint,final Pint)", "Msgdesc", pMsgDesc);
/* 10597 */       Trace.data(this, "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,ByteBuffer[],final Pint,final Pint)", "Putmsgopts", pPutMsgOpts);
/* 10598 */       Trace.data(this, "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,ByteBuffer[],final Pint,final Pint)", "SpiPutOptions", pSpiPutOpts);
/* 10599 */       for (ByteBuffer pBuffer : pBuffers) {
/* 10600 */         if (pBuffer == null) {
/* 10601 */           Trace.data(this, "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,ByteBuffer[],final Pint,final Pint)", "buffer is (null)");
/*       */         } else {
/*       */           
/* 10604 */           JmqiTools.traceApiBuffer(this, "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,ByteBuffer[],final Pint,final Pint)", pBuffer, pBuffer.limit());
/*       */         } 
/*       */       } 
/* 10607 */       Trace.data(this, "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,ByteBuffer[],final Pint,final Pint)", "CompCode", pCompCode);
/* 10608 */       Trace.data(this, "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,ByteBuffer[],final Pint,final Pint)", "Reason", pReason);
/* 10609 */       Trace.data(this, "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,ByteBuffer[],final Pint,final Pint)", "__________");
/*       */     } 
/*       */     
/* 10612 */     ByteBuffer mergedByteBuffer = JmqiTools.mergeByteBuffers(pBuffers);
/* 10613 */     spiPut(hconn, hobj, pMsgDesc, pPutMsgOpts, pSpiPutOpts, mergedByteBuffer.limit(), mergedByteBuffer, pCompCode, pReason);
/*       */     
/* 10615 */     if (Trace.isOn) {
/* 10616 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,ByteBuffer [ ],final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void spiActivateMessage(Hconn hconn, SpiActivate pActivateOpts, final Pint pCompCode, final Pint pReason) {
/* 10633 */     if (Trace.isOn) {
/* 10634 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiActivateMessage(Hconn,SpiActivate,final Pint,final Pint)", new Object[] { hconn, pActivateOpts, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */     
/* 10638 */     String fid = "spiActivateMessage(Hconn,SpiActivate,final Pint,final Pint)";
/*       */ 
/*       */     
/* 10641 */     if (Trace.isOn) {
/* 10642 */       Trace.data(this, "spiActivateMessage(Hconn,SpiActivate,final Pint,final Pint)", "__________");
/* 10643 */       Trace.data(this, "spiActivateMessage(Hconn,SpiActivate,final Pint,final Pint)", "spiActivateMessage >>");
/* 10644 */       Trace.data(this, "spiActivateMessage(Hconn,SpiActivate,final Pint,final Pint)", "Hconn", hconn);
/* 10645 */       Trace.data(this, "spiActivateMessage(Hconn,SpiActivate,final Pint,final Pint)", "SpiActivate", pActivateOpts);
/* 10646 */       Trace.data(this, "spiActivateMessage(Hconn,SpiActivate,final Pint,final Pint)", "CompCode", pCompCode);
/* 10647 */       Trace.data(this, "spiActivateMessage(Hconn,SpiActivate,final Pint,final Pint)", "Reason", pReason);
/* 10648 */       Trace.data(this, "spiActivateMessage(Hconn,SpiActivate,final Pint,final Pint)", "__________");
/*       */     } 
/*       */     
/* 10651 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 10652 */     final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/* 10653 */     LocalHconn lockedLocalhconn = null; try {
/*       */       final byte[] rxpbBuf, optBuf;
/* 10655 */       final LocalHconn localhconn = getLocalHconn(hconn);
/* 10656 */       final byte[] qmName = localhconn.getQMNameBytes();
/*       */       
/* 10658 */       RXPB rxpb = null;
/*       */       
/* 10660 */       if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/* 10661 */         rxpbBuf = null;
/*       */       } else {
/*       */         
/* 10664 */         if (this.inheritRRSContext) {
/* 10665 */           rxpb = getInheritedRxpb(tls, jTls, localhconn);
/*       */         } else {
/*       */           
/* 10668 */           rxpb = localhconn.getRxpb();
/*       */         } 
/* 10670 */         int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/* 10671 */         if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/* 10672 */           tls.rxpbBuf = new byte[rxpbLen];
/*       */         }
/* 10674 */         rxpbBuf = tls.rxpbBuf;
/* 10675 */         rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */       
/* 10679 */       if (pActivateOpts == null) {
/* 10680 */         optBuf = null;
/*       */       } else {
/*       */         
/* 10683 */         int spaLen = pActivateOpts.getRequiredBufferSize(ptrSize, nativeCp);
/* 10684 */         if (tls.optBuf == null || tls.optBuf.length < spaLen) {
/* 10685 */           tls.optBuf = new byte[spaLen];
/*       */         }
/* 10687 */         optBuf = tls.optBuf;
/* 10688 */         pActivateOpts.writeToBuffer(optBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 10694 */       if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hconn)) {
/* 10695 */         localhconn.syncExec(new JmqiRunnable(this.env)
/*       */             {
/*       */               public void run()
/*       */               {
/* 10699 */                 if (Trace.isOn) {
/* 10700 */                   Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                 }
/*       */ 
/*       */                 
/*       */                 try {
/* 10705 */                   localhconn.enterCall();
/*       */                   
/* 10707 */                   boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 10708 */                   Native.spiActivateMessage(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, optBuf, pCompCode, pReason);
/*       */                 }
/* 10710 */                 catch (JmqiException e) {
/* 10711 */                   if (Trace.isOn) {
/* 10712 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                   }
/*       */                   
/* 10715 */                   jTls.lastException = e;
/* 10716 */                   pCompCode.x = e.getCompCode();
/* 10717 */                   pReason.x = e.getReason();
/*       */                 } finally {
/*       */                   
/* 10720 */                   if (Trace.isOn) {
/* 10721 */                     Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                   
/*       */                   try {
/* 10725 */                     localhconn.leaveCall();
/*       */                   }
/* 10727 */                   catch (JmqiException e) {
/* 10728 */                     if (Trace.isOn) {
/* 10729 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 2);
/*       */                     }
/* 10731 */                     jTls.lastException = e;
/* 10732 */                     pCompCode.x = e.getCompCode();
/* 10733 */                     pReason.x = e.getReason();
/*       */                   } 
/*       */                 } 
/* 10736 */                 if (Trace.isOn) {
/* 10737 */                   Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                 
/*       */                 }
/*       */               }
/*       */             });
/*       */       }
/*       */       else {
/*       */         
/* 10745 */         if (this.adapterIsRRS) {
/*       */           
/* 10747 */           localhconn.enterCall();
/* 10748 */           lockedLocalhconn = localhconn;
/*       */         } 
/* 10750 */         boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 10751 */         Native.spiActivateMessage(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, optBuf, pCompCode, pReason);
/*       */       } 
/*       */ 
/*       */       
/* 10755 */       if (pActivateOpts != null) {
/* 10756 */         pActivateOpts.readFromBuffer(optBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       }
/*       */     }
/* 10759 */     catch (JmqiException e) {
/* 10760 */       if (Trace.isOn) {
/* 10761 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiActivateMessage(Hconn,SpiActivate,final Pint,final Pint)", (Throwable)e, 1);
/*       */       }
/*       */       
/* 10764 */       jTls.lastException = e;
/* 10765 */       pCompCode.x = e.getCompCode();
/* 10766 */       pReason.x = e.getReason();
/*       */     }
/* 10768 */     catch (Throwable e) {
/* 10769 */       if (Trace.isOn) {
/* 10770 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiActivateMessage(Hconn,SpiActivate,final Pint,final Pint)", e, 2);
/*       */       }
/*       */       
/* 10773 */       castUnexpectedException("spiActivateMessage(Hconn,SpiActivate,final Pint,final Pint)", 1, e);
/*       */     } finally {
/*       */       
/* 10776 */       if (Trace.isOn) {
/* 10777 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiActivateMessage(Hconn,SpiActivate,final Pint,final Pint)");
/*       */       }
/*       */       
/* 10780 */       if (lockedLocalhconn != null) {
/*       */         
/*       */         try {
/* 10783 */           lockedLocalhconn.leaveCall();
/*       */         }
/* 10785 */         catch (JmqiException e) {
/* 10786 */           if (Trace.isOn) {
/* 10787 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiActivateMessage(Hconn,SpiActivate,final Pint,final Pint)", (Throwable)e, 3);
/*       */           }
/*       */           
/* 10790 */           jTls.lastException = e;
/* 10791 */           pCompCode.x = e.getCompCode();
/* 10792 */           pReason.x = e.getReason();
/*       */         } 
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 10801 */       if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES && this.inheritRRSContext && 
/* 10802 */         pReason.x == 2009) {
/* 10803 */         clearInheritedRXPB(jTls);
/*       */       }
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/* 10810 */     if (Trace.isOn) {
/* 10811 */       Trace.data(this, "spiActivateMessage(Hconn,SpiActivate,final Pint,final Pint)", "__________");
/* 10812 */       Trace.data(this, "spiActivateMessage(Hconn,SpiActivate,final Pint,final Pint)", "spiActivateMessage <<");
/* 10813 */       Trace.data(this, "spiActivateMessage(Hconn,SpiActivate,final Pint,final Pint)", "Hconn", hconn);
/* 10814 */       Trace.data(this, "spiActivateMessage(Hconn,SpiActivate,final Pint,final Pint)", "SpiActivate", pActivateOpts);
/* 10815 */       Trace.data(this, "spiActivateMessage(Hconn,SpiActivate,final Pint,final Pint)", "CompCode", pCompCode);
/* 10816 */       Trace.data(this, "spiActivateMessage(Hconn,SpiActivate,final Pint,final Pint)", "Reason", pReason);
/* 10817 */       Trace.data(this, "spiActivateMessage(Hconn,SpiActivate,final Pint,final Pint)", "__________");
/*       */     } 
/* 10819 */     if (Trace.isOn) {
/* 10820 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiActivateMessage(Hconn,SpiActivate,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void lpiSPIAdoptUser(Hconn hconn, final int flags, LexContext userContext, String applName, final int applType, final byte[] acctToken, final int authToken, LpiAdoptUserOptions options, MQCSP securityParms, final int connectOptions, final byte[] connectionId, final Pint pCompCode, final Pint pReason) {
/* 10846 */     if (Trace.isOn) {
/* 10847 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "lpiSPIAdoptUser(Hconn,final int,final LexContext,final String,final int,final byte [ ],final int,final LpiAdoptUserOptions,MQCSP,final int,final byte [ ],final Pint,final Pint)", new Object[] { hconn, 
/*       */             
/* 10849 */             Integer.valueOf(flags), userContext, applName, 
/* 10850 */             Integer.valueOf(applType), acctToken, Integer.valueOf(authToken), options, securityParms, 
/* 10851 */             Integer.valueOf(connectOptions), connectionId, pCompCode, pReason });
/*       */     }
/* 10853 */     String fid = "lpiSPIAdoptUser(Hconn,int,LexContext,String,int,byte[],int,LpiAdoptUserOptions,MQCSP,int, byte[],final Pint,final Pint)";
/*       */     
/* 10855 */     if (Trace.isOn) {
/* 10856 */       Trace.data(this, "lpiSPIAdoptUser(Hconn,int,LexContext,String,int,byte[],int,LpiAdoptUserOptions,MQCSP,int, byte[],final Pint,final Pint)", "__________");
/* 10857 */       Trace.data(this, "lpiSPIAdoptUser(Hconn,int,LexContext,String,int,byte[],int,LpiAdoptUserOptions,MQCSP,int, byte[],final Pint,final Pint)", "lpiSPIAdoptUser >>");
/* 10858 */       Trace.data(this, "lpiSPIAdoptUser(Hconn,int,LexContext,String,int,byte[],int,LpiAdoptUserOptions,MQCSP,int, byte[],final Pint,final Pint)", "Hconn", hconn);
/* 10859 */       Trace.data(this, "lpiSPIAdoptUser(Hconn,int,LexContext,String,int,byte[],int,LpiAdoptUserOptions,MQCSP,int, byte[],final Pint,final Pint)", "flags", Integer.valueOf(flags));
/* 10860 */       Trace.data(this, "lpiSPIAdoptUser(Hconn,int,LexContext,String,int,byte[],int,LpiAdoptUserOptions,MQCSP,int, byte[],final Pint,final Pint)", "userContext", userContext);
/* 10861 */       Trace.data(this, "lpiSPIAdoptUser(Hconn,int,LexContext,String,int,byte[],int,LpiAdoptUserOptions,MQCSP,int, byte[],final Pint,final Pint)", "applName", applName);
/* 10862 */       Trace.data(this, "lpiSPIAdoptUser(Hconn,int,LexContext,String,int,byte[],int,LpiAdoptUserOptions,MQCSP,int, byte[],final Pint,final Pint)", "applType", Integer.valueOf(applType));
/* 10863 */       Trace.data(this, "lpiSPIAdoptUser(Hconn,int,LexContext,String,int,byte[],int,LpiAdoptUserOptions,MQCSP,int, byte[],final Pint,final Pint)", "acctToken", acctToken);
/* 10864 */       Trace.data(this, "lpiSPIAdoptUser(Hconn,int,LexContext,String,int,byte[],int,LpiAdoptUserOptions,MQCSP,int, byte[],final Pint,final Pint)", "authToken", Integer.valueOf(authToken));
/* 10865 */       Trace.data(this, "lpiSPIAdoptUser(Hconn,int,LexContext,String,int,byte[],int,LpiAdoptUserOptions,MQCSP,int, byte[],final Pint,final Pint)", "options", options);
/* 10866 */       Trace.data(this, "lpiSPIAdoptUser(Hconn,int,LexContext,String,int,byte[],int,LpiAdoptUserOptions,MQCSP,int, byte[],final Pint,final Pint)", "securityParms", securityParms);
/* 10867 */       Trace.data(this, "lpiSPIAdoptUser(Hconn,int,LexContext,String,int,byte[],int,LpiAdoptUserOptions,MQCSP,int, byte[],final Pint,final Pint)", "connectOptions", Integer.valueOf(connectOptions));
/* 10868 */       Trace.data(this, "lpiSPIAdoptUser(Hconn,int,LexContext,String,int,byte[],int,LpiAdoptUserOptions,MQCSP,int, byte[],final Pint,final Pint)", "connectionId", connectionId);
/* 10869 */       Trace.data(this, "lpiSPIAdoptUser(Hconn,int,LexContext,String,int,byte[],int,LpiAdoptUserOptions,MQCSP,int, byte[],final Pint,final Pint)", "CompCode", pCompCode);
/* 10870 */       Trace.data(this, "lpiSPIAdoptUser(Hconn,int,LexContext,String,int,byte[],int,LpiAdoptUserOptions,MQCSP,int, byte[],final Pint,final Pint)", "Reason", pReason);
/* 10871 */       Trace.data(this, "lpiSPIAdoptUser(Hconn,int,LexContext,String,int,byte[],int,LpiAdoptUserOptions,MQCSP,int, byte[],final Pint,final Pint)", "__________");
/*       */     } 
/*       */     
/* 10874 */     pCompCode.x = 0;
/* 10875 */     pReason.x = 0;
/*       */     
/* 10877 */     if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES) {
/* 10878 */       pCompCode.x = 2;
/* 10879 */       pReason.x = 2534;
/*       */     } 
/*       */     
/* 10882 */     if (pCompCode.x == 0)
/*       */     {
/*       */       
/* 10885 */       if (connectionId == null) {
/* 10886 */         Trace.data(this, "lpiSPIAdoptUser(Hconn,int,LexContext,String,int,byte[],int,LpiAdoptUserOptions,MQCSP,int, byte[],final Pint,final Pint)", "No connection id buffer provided", null);
/* 10887 */         pCompCode.x = 2;
/* 10888 */         pReason.x = 2005;
/*       */       }
/* 10890 */       else if (connectionId.length != 24) {
/* 10891 */         Trace.data(this, "lpiSPIAdoptUser(Hconn,int,LexContext,String,int,byte[],int,LpiAdoptUserOptions,MQCSP,int, byte[],final Pint,final Pint)", "Connection id buffer has incorrect length " + connectionId.length + " should be " + '\030', null);
/* 10892 */         pCompCode.x = 2;
/* 10893 */         pReason.x = 2005;
/*       */       } 
/*       */     }
/*       */     
/* 10897 */     if (pCompCode.x == 0) {
/*       */       
/* 10899 */       LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 10900 */       final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/* 10901 */       LocalHconn lockedLocalhconn = null; try {
/*       */         final byte[] rxpbBuf, contextBuf, mqcspBuf, applNameBytes; MQCD channel; final byte[] mqcdBuf, connectionNameBytes; final Pint environmentPint; final int adoptConnectOptions; final byte[] productIdentifierBytes, clientIdentifierBytes;
/* 10903 */         final LocalHconn localhconn = getLocalHconn(hconn);
/* 10904 */         final byte[] qmName = localhconn.getQMNameBytes();
/*       */ 
/*       */         
/* 10907 */         RXPB rxpb = null;
/*       */         
/* 10909 */         if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/* 10910 */           rxpbBuf = null;
/*       */         } else {
/*       */           
/* 10913 */           if (this.inheritRRSContext) {
/* 10914 */             rxpb = getInheritedRxpb(tls, jTls, localhconn);
/*       */           } else {
/*       */             
/* 10917 */             rxpb = localhconn.getRxpb();
/*       */           } 
/* 10919 */           int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/* 10920 */           if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/* 10921 */             tls.rxpbBuf = new byte[rxpbLen];
/*       */           }
/* 10923 */           rxpbBuf = tls.rxpbBuf;
/* 10924 */           rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         } 
/*       */ 
/*       */ 
/*       */         
/* 10929 */         if (userContext == null) {
/* 10930 */           contextBuf = null;
/*       */         } else {
/*       */           
/* 10933 */           contextBuf = new byte[userContext.getRequiredBufferSize(ptrSize, nativeCp)];
/* 10934 */           userContext.writeToBuffer(contextBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         } 
/*       */ 
/*       */ 
/*       */         
/* 10939 */         if (securityParms == null) {
/* 10940 */           mqcspBuf = null;
/*       */         } else {
/*       */           
/* 10943 */           mqcspBuf = new byte[securityParms.getRequiredBufferSize(ptrSize, nativeCp)];
/* 10944 */           securityParms.writeToBuffer(mqcspBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         } 
/*       */ 
/*       */ 
/*       */         
/* 10949 */         if (applName == null) {
/* 10950 */           applNameBytes = null;
/*       */         } else {
/*       */           
/* 10953 */           applNameBytes = Arrays.copyOf(nativeCp.stringToBytes(applName), 28);
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/* 10964 */         if (options == null) {
/* 10965 */           channel = null;
/* 10966 */           mqcdBuf = null;
/* 10967 */           connectionNameBytes = null;
/* 10968 */           environmentPint = this.env.newPint(0);
/* 10969 */           adoptConnectOptions = 0;
/* 10970 */           productIdentifierBytes = new byte[12];
/* 10971 */           clientIdentifierBytes = null;
/*       */         } else {
/*       */           
/* 10974 */           channel = options.getChannel();
/* 10975 */           if (channel == null) {
/* 10976 */             mqcdBuf = null;
/*       */           } else {
/*       */             
/* 10979 */             mqcdBuf = new byte[channel.getRequiredBufferSize(ptrSize, nativeCp)];
/* 10980 */             channel.writeToBuffer(mqcdBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */           } 
/*       */           
/* 10983 */           String connectionName = options.getConnectionName();
/* 10984 */           if (connectionName == null) {
/* 10985 */             connectionNameBytes = null;
/*       */           } else {
/*       */             
/* 10988 */             connectionNameBytes = Arrays.copyOf(nativeCp.stringToBytes(connectionName), 264);
/*       */           } 
/*       */           
/* 10991 */           environmentPint = this.env.newPint(options.getEnvironment());
/*       */           
/* 10993 */           adoptConnectOptions = options.getConnectOptions();
/*       */           
/* 10995 */           String productIdentifier = options.getProductIdentifier();
/* 10996 */           if (productIdentifier == null) {
/* 10997 */             productIdentifierBytes = null;
/*       */           } else {
/*       */             
/* 11000 */             productIdentifierBytes = Arrays.copyOf(nativeCp.stringToBytes(productIdentifier), 12);
/*       */           } 
/*       */           
/* 11003 */           String clientIdentifier = options.getClientIdentifier();
/* 11004 */           if (clientIdentifier == null) {
/* 11005 */             clientIdentifierBytes = null;
/*       */           } else {
/*       */             
/* 11008 */             clientIdentifierBytes = nativeCp.stringToBytes(clientIdentifier);
/*       */           } 
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/* 11015 */         if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hconn)) {
/* 11016 */           localhconn.syncExec(new JmqiRunnable(this.env)
/*       */               {
/*       */                 public void run()
/*       */                 {
/* 11020 */                   if (Trace.isOn) {
/* 11021 */                     Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                   }
/*       */ 
/*       */                   
/*       */                   try {
/* 11026 */                     localhconn.enterCall();
/*       */                     
/* 11028 */                     boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 11029 */                     Native.lpiSPIAdoptUser(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, flags, contextBuf, applNameBytes, applType, acctToken, authToken, mqcdBuf, connectionNameBytes, environmentPint, adoptConnectOptions, productIdentifierBytes, clientIdentifierBytes, LocalMQ
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */                         
/* 11037 */                         .nativeCp.getCCSID(), mqcspBuf, connectOptions, connectionId, pCompCode, pReason);
/*       */                   
/*       */                   }
/* 11040 */                   catch (JmqiException e) {
/* 11041 */                     if (Trace.isOn) {
/* 11042 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                     }
/*       */                     
/* 11045 */                     jTls.lastException = e;
/*       */                     
/* 11047 */                     pCompCode.x = e.getCompCode();
/* 11048 */                     pReason.x = e.getReason();
/*       */                   } finally {
/*       */                     
/* 11051 */                     if (Trace.isOn) {
/* 11052 */                       Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                     }
/*       */                     
/*       */                     try {
/* 11056 */                       localhconn.leaveCall();
/*       */                     }
/* 11058 */                     catch (JmqiException e) {
/* 11059 */                       if (Trace.isOn) {
/* 11060 */                         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 2);
/*       */                       }
/* 11062 */                       jTls.lastException = e;
/* 11063 */                       pCompCode.x = e.getCompCode();
/* 11064 */                       pReason.x = e.getReason();
/*       */                     } 
/*       */                   } 
/* 11067 */                   if (Trace.isOn) {
/* 11068 */                     Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   
/*       */                   }
/*       */                 }
/*       */               });
/*       */         }
/*       */         else {
/*       */           
/* 11076 */           if (this.adapterIsRRS) {
/*       */             
/* 11078 */             localhconn.enterCall();
/* 11079 */             lockedLocalhconn = localhconn;
/*       */           } 
/*       */           
/* 11082 */           boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 11083 */           Native.lpiSPIAdoptUser(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, flags, contextBuf, applNameBytes, applType, acctToken, authToken, mqcdBuf, connectionNameBytes, environmentPint, adoptConnectOptions, productIdentifierBytes, clientIdentifierBytes, nativeCp
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */               
/* 11091 */               .getCCSID(), mqcspBuf, connectOptions, connectionId, pCompCode, pReason);
/*       */         } 
/*       */ 
/*       */ 
/*       */         
/* 11096 */         if (userContext != null) {
/* 11097 */           userContext.readFromBuffer(contextBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         }
/* 11099 */         if (securityParms != null) {
/* 11100 */           securityParms.readFromBuffer(mqcspBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         }
/* 11102 */         if (channel != null) {
/* 11103 */           channel.readFromBuffer(mqcdBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         }
/* 11105 */         if (options != null) {
/* 11106 */           options.setEnvironment(environmentPint.x);
/*       */         }
/*       */       }
/* 11109 */       catch (JmqiException e) {
/* 11110 */         if (Trace.isOn)
/*       */         {
/* 11112 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "lpiSPIAdoptUser(Hconn,final int,final LexContext,final String,final int,final byte [ ],final int,final LpiAdoptUserOptions,MQCSP,final int,final byte [ ],final Pint,final Pint)", (Throwable)e, 1);
/*       */         }
/*       */ 
/*       */ 
/*       */ 
/*       */         
/* 11118 */         jTls.lastException = e;
/* 11119 */         pCompCode.x = e.getCompCode();
/* 11120 */         pReason.x = e.getReason();
/*       */       }
/* 11122 */       catch (Throwable e) {
/* 11123 */         if (Trace.isOn)
/*       */         {
/* 11125 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "lpiSPIAdoptUser(Hconn,final int,final LexContext,final String,final int,final byte [ ],final int,final LpiAdoptUserOptions,MQCSP,final int,final byte [ ],final Pint,final Pint)", e, 2);
/*       */         }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/* 11132 */         castUnexpectedException("lpiSPIAdoptUser(Hconn,int,LexContext,String,int,byte[],int,LpiAdoptUserOptions,MQCSP,int, byte[],final Pint,final Pint)", 1, e);
/*       */       } finally {
/*       */         
/* 11135 */         if (Trace.isOn) {
/* 11136 */           Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "lpiSPIAdoptUser(Hconn,final int,final LexContext,final String,final int,final byte [ ],final int,final LpiAdoptUserOptions,MQCSP,final int,final byte [ ],final Pint,final Pint)");
/*       */         }
/*       */         
/* 11139 */         if (lockedLocalhconn != null) {
/*       */           
/*       */           try {
/* 11142 */             lockedLocalhconn.leaveCall();
/*       */           }
/* 11144 */           catch (JmqiException e) {
/* 11145 */             if (Trace.isOn)
/*       */             {
/* 11147 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "lpiSPIAdoptUser(Hconn,final int,final LexContext,final String,final int,final byte [ ],final int,final LpiAdoptUserOptions,MQCSP,final int,final byte [ ],final Pint,final Pint)", (Throwable)e, 3);
/*       */             }
/*       */ 
/*       */ 
/*       */ 
/*       */             
/* 11153 */             jTls.lastException = e;
/* 11154 */             pCompCode.x = e.getCompCode();
/* 11155 */             pReason.x = e.getReason();
/*       */           } 
/*       */         }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/* 11164 */         if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES && this.inheritRRSContext && 
/* 11165 */           pReason.x == 2009) {
/* 11166 */           clearInheritedRXPB(jTls);
/*       */         }
/*       */       } 
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/* 11174 */     if (Trace.isOn) {
/* 11175 */       Trace.data(this, "lpiSPIAdoptUser(Hconn,int,LexContext,String,int,byte[],int,LpiAdoptUserOptions,MQCSP,int, byte[],final Pint,final Pint)", "__________");
/* 11176 */       Trace.data(this, "lpiSPIAdoptUser(Hconn,int,LexContext,String,int,byte[],int,LpiAdoptUserOptions,MQCSP,int, byte[],final Pint,final Pint)", "lpiSPIAdoptUser <<");
/* 11177 */       Trace.data(this, "lpiSPIAdoptUser(Hconn,int,LexContext,String,int,byte[],int,LpiAdoptUserOptions,MQCSP,int, byte[],final Pint,final Pint)", "Hconn", hconn);
/* 11178 */       Trace.data(this, "lpiSPIAdoptUser(Hconn,int,LexContext,String,int,byte[],int,LpiAdoptUserOptions,MQCSP,int, byte[],final Pint,final Pint)", "flags", Integer.valueOf(flags));
/* 11179 */       Trace.data(this, "lpiSPIAdoptUser(Hconn,int,LexContext,String,int,byte[],int,LpiAdoptUserOptions,MQCSP,int, byte[],final Pint,final Pint)", "userContext", userContext);
/* 11180 */       Trace.data(this, "lpiSPIAdoptUser(Hconn,int,LexContext,String,int,byte[],int,LpiAdoptUserOptions,MQCSP,int, byte[],final Pint,final Pint)", "applName", applName);
/* 11181 */       Trace.data(this, "lpiSPIAdoptUser(Hconn,int,LexContext,String,int,byte[],int,LpiAdoptUserOptions,MQCSP,int, byte[],final Pint,final Pint)", "applType", Integer.valueOf(applType));
/* 11182 */       Trace.data(this, "lpiSPIAdoptUser(Hconn,int,LexContext,String,int,byte[],int,LpiAdoptUserOptions,MQCSP,int, byte[],final Pint,final Pint)", "acctToken", acctToken);
/* 11183 */       Trace.data(this, "lpiSPIAdoptUser(Hconn,int,LexContext,String,int,byte[],int,LpiAdoptUserOptions,MQCSP,int, byte[],final Pint,final Pint)", "authToken", Integer.valueOf(authToken));
/* 11184 */       Trace.data(this, "lpiSPIAdoptUser(Hconn,int,LexContext,String,int,byte[],int,LpiAdoptUserOptions,MQCSP,int, byte[],final Pint,final Pint)", "options", options);
/* 11185 */       Trace.data(this, "lpiSPIAdoptUser(Hconn,int,LexContext,String,int,byte[],int,LpiAdoptUserOptions,MQCSP,int, byte[],final Pint,final Pint)", "securityParms", securityParms);
/* 11186 */       Trace.data(this, "lpiSPIAdoptUser(Hconn,int,LexContext,String,int,byte[],int,LpiAdoptUserOptions,MQCSP,int, byte[],final Pint,final Pint)", "connectOptions", Integer.valueOf(connectOptions));
/* 11187 */       Trace.data(this, "lpiSPIAdoptUser(Hconn,int,LexContext,String,int,byte[],int,LpiAdoptUserOptions,MQCSP,int, byte[],final Pint,final Pint)", "connectionId", connectionId);
/* 11188 */       Trace.data(this, "lpiSPIAdoptUser(Hconn,int,LexContext,String,int,byte[],int,LpiAdoptUserOptions,MQCSP,int, byte[],final Pint,final Pint)", "CompCode", pCompCode);
/* 11189 */       Trace.data(this, "lpiSPIAdoptUser(Hconn,int,LexContext,String,int,byte[],int,LpiAdoptUserOptions,MQCSP,int, byte[],final Pint,final Pint)", "Reason", pReason);
/* 11190 */       Trace.data(this, "lpiSPIAdoptUser(Hconn,int,LexContext,String,int,byte[],int,LpiAdoptUserOptions,MQCSP,int, byte[],final Pint,final Pint)", "__________");
/*       */     } 
/* 11192 */     Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "lpiSPIAdoptUser(Hconn,int,LexContext,String,int,byte[],int,LpiAdoptUserOptions,MQCSP,int, byte[],final Pint,final Pint)", new Object[] { hconn, 
/*       */           
/* 11194 */           Integer.valueOf(flags), userContext, applName, Integer.valueOf(applType), acctToken, Integer.valueOf(authToken), options, securityParms, Integer.valueOf(connectOptions), connectionId, pCompCode, pReason });
/* 11195 */     if (Trace.isOn) {
/* 11196 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "lpiSPIAdoptUser(Hconn,final int,final LexContext,final String,final int,final byte [ ],final int,final LpiAdoptUserOptions,MQCSP,final int,final byte [ ],final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void lpiSPICHLAUTH(Hconn hConn, LpiCHLAUTHQuery pParms, final Pint pCompCode, final Pint pReason) {
/* 11207 */     if (Trace.isOn) {
/* 11208 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "lpiSPICHLAUTH(Hconn,LpiCHLAUTHQuery,final Pint,final Pint)", new Object[] { hConn, pParms, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */     
/* 11212 */     String fid = "lpiSPICHLAUTH(Hconn, LpiCHLAUTHQuery, byte[], int, Pint, Pint )";
/*       */     
/* 11214 */     if (Trace.isOn) {
/* 11215 */       Trace.data(this, "lpiSPICHLAUTH(Hconn, LpiCHLAUTHQuery, byte[], int, Pint, Pint )", "__________");
/* 11216 */       Trace.data(this, "lpiSPICHLAUTH(Hconn, LpiCHLAUTHQuery, byte[], int, Pint, Pint )", "lpiSPICHLAUTH >>");
/* 11217 */       Trace.data(this, "lpiSPICHLAUTH(Hconn, LpiCHLAUTHQuery, byte[], int, Pint, Pint )", "hConn", hConn);
/* 11218 */       Trace.data(this, "lpiSPICHLAUTH(Hconn, LpiCHLAUTHQuery, byte[], int, Pint, Pint )", "pParms", pParms);
/* 11219 */       Trace.data(this, "lpiSPICHLAUTH(Hconn, LpiCHLAUTHQuery, byte[], int, Pint, Pint )", "CompCode", pCompCode);
/* 11220 */       Trace.data(this, "lpiSPICHLAUTH(Hconn, LpiCHLAUTHQuery, byte[], int, Pint, Pint )", "Reason", pReason);
/* 11221 */       Trace.data(this, "lpiSPICHLAUTH(Hconn, LpiCHLAUTHQuery, byte[], int, Pint, Pint )", "__________");
/*       */     } 
/*       */     
/* 11224 */     pCompCode.x = 0;
/* 11225 */     pReason.x = 0;
/*       */     
/* 11227 */     if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES) {
/* 11228 */       pCompCode.x = 2;
/* 11229 */       pReason.x = 2534;
/*       */     } 
/*       */     
/* 11232 */     if (pCompCode.x == 0) {
/*       */       
/* 11234 */       LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 11235 */       final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/* 11236 */       LocalHconn lockedLocalhconn = null; try {
/*       */         final byte[] rxpbBuf, lpiCHLAUTHQueryBuff;
/* 11238 */         final LocalHconn localhconn = getLocalHconn(hConn);
/* 11239 */         final byte[] qmName = localhconn.getQMNameBytes();
/*       */ 
/*       */         
/* 11242 */         RXPB rxpb = null;
/*       */         
/* 11244 */         if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/* 11245 */           rxpbBuf = null;
/*       */         } else {
/*       */           
/* 11248 */           if (this.inheritRRSContext) {
/* 11249 */             rxpb = getInheritedRxpb(tls, jTls, localhconn);
/*       */           } else {
/*       */             
/* 11252 */             rxpb = localhconn.getRxpb();
/*       */           } 
/* 11254 */           int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/* 11255 */           if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/* 11256 */             tls.rxpbBuf = new byte[rxpbLen];
/*       */           }
/* 11258 */           rxpbBuf = tls.rxpbBuf;
/* 11259 */           rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         } 
/*       */ 
/*       */ 
/*       */         
/* 11264 */         if (pParms == null) {
/* 11265 */           lpiCHLAUTHQueryBuff = null;
/*       */         } else {
/*       */           
/* 11268 */           int parmLen = pParms.getRequiredBufferSize(ptrSize, nativeCp);
/* 11269 */           if (tls.lpiCHLAUTHQueryBuff == null || tls.lpiCHLAUTHQueryBuff.length < parmLen) {
/* 11270 */             tls.lpiCHLAUTHQueryBuff = new byte[parmLen];
/*       */           }
/* 11272 */           lpiCHLAUTHQueryBuff = tls.lpiCHLAUTHQueryBuff;
/* 11273 */           pParms.writeToBuffer(lpiCHLAUTHQueryBuff, 0, ptrSize, swap, nativeCp, jTls);
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/* 11279 */         if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hConn)) {
/* 11280 */           localhconn.syncExec(new JmqiRunnable(this.env)
/*       */               {
/*       */                 public void run()
/*       */                 {
/* 11284 */                   if (Trace.isOn) {
/* 11285 */                     Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                   }
/*       */ 
/*       */                   
/*       */                   try {
/* 11290 */                     localhconn.enterCall();
/*       */                     
/* 11292 */                     boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 11293 */                     Native.lpiSPICHLAUTH(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, lpiCHLAUTHQueryBuff, pCompCode, pReason);
/*       */                   }
/* 11295 */                   catch (JmqiException e) {
/* 11296 */                     if (Trace.isOn) {
/* 11297 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                     }
/*       */                     
/* 11300 */                     jTls.lastException = e;
/*       */                     
/* 11302 */                     pCompCode.x = e.getCompCode();
/* 11303 */                     pReason.x = e.getReason();
/*       */                   } finally {
/*       */                     
/* 11306 */                     if (Trace.isOn) {
/* 11307 */                       Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                     }
/*       */                     
/*       */                     try {
/* 11311 */                       localhconn.leaveCall();
/*       */                     }
/* 11313 */                     catch (JmqiException e) {
/* 11314 */                       if (Trace.isOn) {
/* 11315 */                         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 2);
/*       */                       }
/* 11317 */                       jTls.lastException = e;
/* 11318 */                       pCompCode.x = e.getCompCode();
/* 11319 */                       pReason.x = e.getReason();
/*       */                     } 
/*       */                   } 
/* 11322 */                   if (Trace.isOn) {
/* 11323 */                     Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   
/*       */                   }
/*       */                 }
/*       */               });
/*       */         }
/*       */         else {
/*       */           
/* 11331 */           if (this.adapterIsRRS) {
/*       */             
/* 11333 */             localhconn.enterCall();
/* 11334 */             lockedLocalhconn = localhconn;
/*       */           } 
/*       */           
/* 11337 */           boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 11338 */           Native.lpiSPICHLAUTH(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, lpiCHLAUTHQueryBuff, pCompCode, pReason);
/*       */         } 
/*       */ 
/*       */         
/* 11342 */         if ((((lpiCHLAUTHQueryBuff != null) ? 1 : 0) & ((pParms != null) ? 1 : 0)) != 0) {
/* 11343 */           pParms.readFromBuffer(lpiCHLAUTHQueryBuff, 0, lpiCHLAUTHQueryBuff.length, swap, nativeCp, jTls);
/*       */         }
/*       */       }
/* 11346 */       catch (JmqiException e) {
/* 11347 */         if (Trace.isOn) {
/* 11348 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "lpiSPICHLAUTH(Hconn,LpiCHLAUTHQuery,final Pint,final Pint)", (Throwable)e, 1);
/*       */         }
/*       */         
/* 11351 */         jTls.lastException = e;
/* 11352 */         pCompCode.x = e.getCompCode();
/* 11353 */         pReason.x = e.getReason();
/*       */       }
/* 11355 */       catch (Throwable e) {
/* 11356 */         if (Trace.isOn) {
/* 11357 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "lpiSPICHLAUTH(Hconn,LpiCHLAUTHQuery,final Pint,final Pint)", e, 2);
/*       */         }
/*       */ 
/*       */         
/* 11361 */         castUnexpectedException("lpiSPICHLAUTH(Hconn, LpiCHLAUTHQuery, byte[], int, Pint, Pint )", 1, e);
/*       */       } finally {
/*       */         
/* 11364 */         if (Trace.isOn) {
/* 11365 */           Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "lpiSPICHLAUTH(Hconn,LpiCHLAUTHQuery,final Pint,final Pint)");
/*       */         }
/*       */         
/* 11368 */         if (lockedLocalhconn != null) {
/*       */           
/*       */           try {
/* 11371 */             lockedLocalhconn.leaveCall();
/*       */           }
/* 11373 */           catch (JmqiException e) {
/* 11374 */             if (Trace.isOn) {
/* 11375 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "lpiSPICHLAUTH(Hconn,LpiCHLAUTHQuery,final Pint,final Pint)", (Throwable)e, 3);
/*       */             }
/*       */             
/* 11378 */             jTls.lastException = e;
/* 11379 */             pCompCode.x = e.getCompCode();
/* 11380 */             pReason.x = e.getReason();
/*       */           } 
/*       */         }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/* 11389 */         if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES && this.inheritRRSContext && 
/* 11390 */           pReason.x == 2009) {
/* 11391 */           clearInheritedRXPB(jTls);
/*       */         }
/*       */       } 
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/* 11399 */     if (Trace.isOn) {
/* 11400 */       Trace.data(this, "lpiSPICHLAUTH(Hconn, LpiCHLAUTHQuery, byte[], int, Pint, Pint )", "__________");
/* 11401 */       Trace.data(this, "lpiSPICHLAUTH(Hconn, LpiCHLAUTHQuery, byte[], int, Pint, Pint )", "lpiSPICHLAUTH <<");
/* 11402 */       Trace.data(this, "lpiSPICHLAUTH(Hconn, LpiCHLAUTHQuery, byte[], int, Pint, Pint )", "hConn", hConn);
/* 11403 */       Trace.data(this, "lpiSPICHLAUTH(Hconn, LpiCHLAUTHQuery, byte[], int, Pint, Pint )", "pParms", pParms);
/* 11404 */       Trace.data(this, "lpiSPICHLAUTH(Hconn, LpiCHLAUTHQuery, byte[], int, Pint, Pint )", "CompCode", pCompCode);
/* 11405 */       Trace.data(this, "lpiSPICHLAUTH(Hconn, LpiCHLAUTHQuery, byte[], int, Pint, Pint )", "Reason", pReason);
/* 11406 */       Trace.data(this, "lpiSPICHLAUTH(Hconn, LpiCHLAUTHQuery, byte[], int, Pint, Pint )", "__________");
/*       */     } 
/* 11408 */     if (Trace.isOn) {
/* 11409 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "lpiSPICHLAUTH(Hconn,LpiCHLAUTHQuery,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void lpiSPIMapCredentials(Hconn hConn, LexContext userContext, String mcaUser, MQCSP securityParms, final byte[] accountingToken, String applName, String channelName, String connectionName, final Pint pCompCode, final Pint pReason) {
/* 11421 */     if (Trace.isOn) {
/* 11422 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "lpiSPIMapCredentials(Hconn,LexContext,MQCSP,final byte [ ],String,String,String,final Pint,final Pint)", new Object[] { hConn, userContext, securityParms, accountingToken, applName, channelName, connectionName, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */ 
/*       */     
/* 11427 */     String fid = "lpiSPIMapCredentials(Hconn, LpiPreAdoptUserQueryRequest, byte[], int, Pint, Pint )";
/*       */     
/* 11429 */     if (Trace.isOn) {
/* 11430 */       Trace.data(this, "lpiSPIMapCredentials(Hconn, LpiPreAdoptUserQueryRequest, byte[], int, Pint, Pint )", "__________");
/* 11431 */       Trace.data(this, "lpiSPIMapCredentials(Hconn, LpiPreAdoptUserQueryRequest, byte[], int, Pint, Pint )", "lpiSPIMapCredentials >>");
/* 11432 */       Trace.data(this, "lpiSPIMapCredentials(Hconn, LpiPreAdoptUserQueryRequest, byte[], int, Pint, Pint )", "hConn", hConn);
/* 11433 */       Trace.data(this, "lpiSPIMapCredentials(Hconn, LpiPreAdoptUserQueryRequest, byte[], int, Pint, Pint )", "userContext", userContext);
/* 11434 */       Trace.data(this, "lpiSPIMapCredentials(Hconn, LpiPreAdoptUserQueryRequest, byte[], int, Pint, Pint )", "mcaUser", mcaUser);
/* 11435 */       Trace.data(this, "lpiSPIMapCredentials(Hconn, LpiPreAdoptUserQueryRequest, byte[], int, Pint, Pint )", "securityParms", securityParms);
/* 11436 */       Trace.data(this, "lpiSPIMapCredentials(Hconn, LpiPreAdoptUserQueryRequest, byte[], int, Pint, Pint )", "accountingToken", accountingToken);
/* 11437 */       Trace.data(this, "lpiSPIMapCredentials(Hconn, LpiPreAdoptUserQueryRequest, byte[], int, Pint, Pint )", "applName", applName);
/* 11438 */       Trace.data(this, "lpiSPIMapCredentials(Hconn, LpiPreAdoptUserQueryRequest, byte[], int, Pint, Pint )", "channelName", channelName);
/* 11439 */       Trace.data(this, "lpiSPIMapCredentials(Hconn, LpiPreAdoptUserQueryRequest, byte[], int, Pint, Pint )", "connectionName", connectionName);
/* 11440 */       Trace.data(this, "lpiSPIMapCredentials(Hconn, LpiPreAdoptUserQueryRequest, byte[], int, Pint, Pint )", "CompCode", pCompCode);
/* 11441 */       Trace.data(this, "lpiSPIMapCredentials(Hconn, LpiPreAdoptUserQueryRequest, byte[], int, Pint, Pint )", "Reason", pReason);
/* 11442 */       Trace.data(this, "lpiSPIMapCredentials(Hconn, LpiPreAdoptUserQueryRequest, byte[], int, Pint, Pint )", "__________");
/*       */     } 
/*       */     
/* 11445 */     pCompCode.x = 0;
/* 11446 */     pReason.x = 0;
/*       */     
/* 11448 */     if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES) {
/* 11449 */       pCompCode.x = 2;
/* 11450 */       pReason.x = 2534;
/*       */     }
/* 11452 */     else if (userContext == null) {
/* 11453 */       pCompCode.x = 2;
/* 11454 */       pReason.x = 2321;
/*       */     }
/* 11456 */     else if (accountingToken.length != 32) {
/* 11457 */       pCompCode.x = 2;
/* 11458 */       pReason.x = 6114;
/*       */     } 
/*       */     
/* 11461 */     int qmCcsid = 0;
/* 11462 */     if (pCompCode.x != 2) {
/*       */ 
/*       */       
/* 11465 */       MQOD mqod = this.env.newMQOD();
/* 11466 */       mqod.setObjectType(5);
/* 11467 */       int options = 8224;
/* 11468 */       Phobj phobj = this.env.newPhobj();
/* 11469 */       MQOPEN(hConn, mqod, options, phobj, pCompCode, pReason);
/* 11470 */       if (pCompCode.x != 2) {
/* 11471 */         int[] selectors = { 2 };
/* 11472 */         int[] intAttrs = new int[1];
/* 11473 */         MQINQ(hConn, phobj.getHobj(), 1, selectors, 1, intAttrs, 0, null, pCompCode, pReason);
/* 11474 */         if (pCompCode.x != 2) {
/* 11475 */           qmCcsid = intAttrs[0];
/*       */         }
/* 11477 */         MQCLOSE(hConn, phobj, 0, pCompCode, pReason);
/*       */       } 
/*       */     } 
/*       */     
/* 11481 */     if (pCompCode.x != 2) {
/*       */       
/* 11483 */       LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 11484 */       final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/* 11485 */       LocalHconn lockedLocalhconn = null; try {
/*       */         final byte[] rxpbBuf, mqcspBuf;
/* 11487 */         final LocalHconn localhconn = getLocalHconn(hConn);
/* 11488 */         final byte[] qmName = localhconn.getQMNameBytes();
/*       */ 
/*       */         
/* 11491 */         RXPB rxpb = null;
/*       */         
/* 11493 */         if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/* 11494 */           rxpbBuf = null;
/*       */         } else {
/*       */           
/* 11497 */           if (this.inheritRRSContext) {
/* 11498 */             rxpb = getInheritedRxpb(tls, jTls, localhconn);
/*       */           } else {
/*       */             
/* 11501 */             rxpb = localhconn.getRxpb();
/*       */           } 
/* 11503 */           int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/* 11504 */           if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/* 11505 */             tls.rxpbBuf = new byte[rxpbLen];
/*       */           }
/* 11507 */           rxpbBuf = tls.rxpbBuf;
/* 11508 */           rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         } 
/*       */ 
/*       */         
/* 11512 */         final byte[] contextBuf = new byte[userContext.getRequiredBufferSize(ptrSize, nativeCp)];
/* 11513 */         JmqiCodepage qmCp = JmqiCodepage.getJmqiCodepage(this.env, qmCcsid);
/* 11514 */         userContext.writeToBuffer(contextBuf, 0, ptrSize, swap, qmCp, jTls);
/*       */ 
/*       */ 
/*       */         
/* 11518 */         if (securityParms == null) {
/* 11519 */           mqcspBuf = null;
/*       */         } else {
/*       */           
/* 11522 */           mqcspBuf = new byte[securityParms.getRequiredBufferSize(ptrSize, nativeCp)];
/* 11523 */           securityParms.writeToBuffer(mqcspBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         } 
/*       */ 
/*       */         
/* 11527 */         JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 11528 */         final byte[] mcaUserBuf = serializeToFixedByte(mcaUser, jTls, dc, 64);
/* 11529 */         final byte[] applNameBuf = serializeToFixedByte(applName, jTls, dc, 28);
/* 11530 */         final byte[] channelNameBuf = serializeToFixedByte(channelName, jTls, dc, 20);
/* 11531 */         final byte[] connectionNameBuf = serializeToFixedByte(connectionName, jTls, dc, 264);
/*       */ 
/*       */ 
/*       */ 
/*       */         
/* 11536 */         if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hConn)) {
/* 11537 */           localhconn.syncExec(new JmqiRunnable(this.env)
/*       */               {
/*       */                 public void run()
/*       */                 {
/* 11541 */                   if (Trace.isOn) {
/* 11542 */                     Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                   }
/*       */ 
/*       */                   
/*       */                   try {
/* 11547 */                     localhconn.enterCall();
/*       */                     
/* 11549 */                     boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 11550 */                     Native.lpiSPIMapCredentials(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, contextBuf, mcaUserBuf, mqcspBuf, accountingToken, applNameBuf, channelNameBuf, connectionNameBuf, pCompCode, pReason);
/*       */                   
/*       */                   }
/* 11553 */                   catch (JmqiException e) {
/* 11554 */                     if (Trace.isOn) {
/* 11555 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                     }
/*       */                     
/* 11558 */                     jTls.lastException = e;
/*       */                     
/* 11560 */                     pCompCode.x = e.getCompCode();
/* 11561 */                     pReason.x = e.getReason();
/*       */                   } finally {
/*       */                     
/* 11564 */                     if (Trace.isOn) {
/* 11565 */                       Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                     }
/*       */                     
/*       */                     try {
/* 11569 */                       localhconn.leaveCall();
/*       */                     }
/* 11571 */                     catch (JmqiException e) {
/* 11572 */                       if (Trace.isOn) {
/* 11573 */                         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 2);
/*       */                       }
/* 11575 */                       jTls.lastException = e;
/* 11576 */                       pCompCode.x = e.getCompCode();
/* 11577 */                       pReason.x = e.getReason();
/*       */                     } 
/*       */                   } 
/* 11580 */                   if (Trace.isOn) {
/* 11581 */                     Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   
/*       */                   }
/*       */                 }
/*       */               });
/*       */         }
/*       */         else {
/*       */           
/* 11589 */           if (this.adapterIsRRS) {
/*       */             
/* 11591 */             localhconn.enterCall();
/* 11592 */             lockedLocalhconn = localhconn;
/*       */           } 
/*       */           
/* 11595 */           boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 11596 */           Native.lpiSPIMapCredentials(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, contextBuf, mcaUserBuf, mqcspBuf, accountingToken, applNameBuf, channelNameBuf, connectionNameBuf, pCompCode, pReason);
/*       */         } 
/*       */ 
/*       */         
/* 11600 */         if (pCompCode.x != 2)
/*       */         {
/* 11602 */           userContext.readFromBuffer(contextBuf, 0, contextBuf.length, swap, qmCp, jTls);
/* 11603 */           mcaUser = dc.readField(mcaUserBuf, 0, mcaUserBuf.length, qmCp, jTls);
/*       */         }
/*       */       
/* 11606 */       } catch (JmqiException e) {
/* 11607 */         if (Trace.isOn) {
/* 11608 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "lpiSPIMapCredentials(Hconn,LexContext,MQCSP,final byte [ ],String,String,String,final Pint,final Pint)", (Throwable)e, 1);
/*       */         }
/*       */ 
/*       */         
/* 11612 */         jTls.lastException = e;
/* 11613 */         pCompCode.x = e.getCompCode();
/* 11614 */         pReason.x = e.getReason();
/*       */       }
/* 11616 */       catch (Throwable e) {
/* 11617 */         if (Trace.isOn) {
/* 11618 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "lpiSPIMapCredentials(Hconn,LexContext,MQCSP,final byte [ ],String,String,String,final Pint,final Pint)", e, 2);
/*       */         }
/*       */ 
/*       */ 
/*       */         
/* 11623 */         castUnexpectedException("lpiSPIMapCredentials(Hconn, LpiPreAdoptUserQueryRequest, byte[], int, Pint, Pint )", 1, e);
/*       */       } finally {
/*       */         
/* 11626 */         if (Trace.isOn) {
/* 11627 */           Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "lpiSPIMapCredentials(Hconn,LexContext,MQCSP,final byte [ ],String,String,String,final Pint,final Pint)");
/*       */         }
/*       */         
/* 11630 */         if (lockedLocalhconn != null) {
/*       */           
/*       */           try {
/* 11633 */             lockedLocalhconn.leaveCall();
/*       */           }
/* 11635 */           catch (JmqiException e) {
/* 11636 */             if (Trace.isOn) {
/* 11637 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "lpiSPIMapCredentials(Hconn,LexContext,MQCSP,final byte [ ],String,String,String,final Pint,final Pint)", (Throwable)e, 3);
/*       */             }
/*       */ 
/*       */             
/* 11641 */             jTls.lastException = e;
/* 11642 */             pCompCode.x = e.getCompCode();
/* 11643 */             pReason.x = e.getReason();
/*       */           } 
/*       */         }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/* 11652 */         if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES && this.inheritRRSContext && 
/* 11653 */           pReason.x == 2009) {
/* 11654 */           clearInheritedRXPB(jTls);
/*       */         }
/*       */       } 
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/* 11662 */     if (Trace.isOn) {
/* 11663 */       Trace.data(this, "lpiSPIMapCredentials(Hconn, LpiPreAdoptUserQueryRequest, byte[], int, Pint, Pint )", "__________");
/* 11664 */       Trace.data(this, "lpiSPIMapCredentials(Hconn, LpiPreAdoptUserQueryRequest, byte[], int, Pint, Pint )", "lpiSPIMapCredentials >>");
/* 11665 */       Trace.data(this, "lpiSPIMapCredentials(Hconn, LpiPreAdoptUserQueryRequest, byte[], int, Pint, Pint )", "hConn", hConn);
/* 11666 */       Trace.data(this, "lpiSPIMapCredentials(Hconn, LpiPreAdoptUserQueryRequest, byte[], int, Pint, Pint )", "userContext", userContext);
/* 11667 */       Trace.data(this, "lpiSPIMapCredentials(Hconn, LpiPreAdoptUserQueryRequest, byte[], int, Pint, Pint )", "mcaUser", mcaUser);
/* 11668 */       Trace.data(this, "lpiSPIMapCredentials(Hconn, LpiPreAdoptUserQueryRequest, byte[], int, Pint, Pint )", "securityParms", securityParms);
/* 11669 */       Trace.data(this, "lpiSPIMapCredentials(Hconn, LpiPreAdoptUserQueryRequest, byte[], int, Pint, Pint )", "accountingToken", accountingToken);
/* 11670 */       Trace.data(this, "lpiSPIMapCredentials(Hconn, LpiPreAdoptUserQueryRequest, byte[], int, Pint, Pint )", "applName", applName);
/* 11671 */       Trace.data(this, "lpiSPIMapCredentials(Hconn, LpiPreAdoptUserQueryRequest, byte[], int, Pint, Pint )", "channelName", channelName);
/* 11672 */       Trace.data(this, "lpiSPIMapCredentials(Hconn, LpiPreAdoptUserQueryRequest, byte[], int, Pint, Pint )", "connectionName", connectionName);
/* 11673 */       Trace.data(this, "lpiSPIMapCredentials(Hconn, LpiPreAdoptUserQueryRequest, byte[], int, Pint, Pint )", "CompCode", pCompCode);
/* 11674 */       Trace.data(this, "lpiSPIMapCredentials(Hconn, LpiPreAdoptUserQueryRequest, byte[], int, Pint, Pint )", "Reason", pReason);
/* 11675 */       Trace.data(this, "lpiSPIMapCredentials(Hconn, LpiPreAdoptUserQueryRequest, byte[], int, Pint, Pint )", "__________");
/*       */     } 
/* 11677 */     if (Trace.isOn) {
/* 11678 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "lpiSPIMapCredentials(Hconn,LexContext,String,MQCSP,final byte [ ],String,String,String,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void lpiSPICheckPrivileged(Hconn hconn, String componentName, String entityName, final int entityType, final Pint pCompCode, final Pint pReason) {
/* 11689 */     if (Trace.isOn) {
/* 11690 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "lpiSPICheckPrivileged(Hconn,String,String,int,Pint,Pint)", new Object[] { hconn, componentName, entityName, 
/*       */             
/* 11692 */             Integer.valueOf(entityType), pCompCode, pReason });
/*       */     }
/* 11694 */     String fid = "lpiSPICheckPrivileged(Hconn, String, String, int, Pint, Pint)";
/*       */     
/* 11696 */     if (Trace.isOn) {
/* 11697 */       Trace.data(this, "lpiSPICheckPrivileged(Hconn, String, String, int, Pint, Pint)", "__________");
/* 11698 */       Trace.data(this, "lpiSPICheckPrivileged(Hconn, String, String, int, Pint, Pint)", "lpiSPICheckPrivileged >>");
/* 11699 */       Trace.data(this, "lpiSPICheckPrivileged(Hconn, String, String, int, Pint, Pint)", "hconn", hconn);
/* 11700 */       Trace.data(this, "lpiSPICheckPrivileged(Hconn, String, String, int, Pint, Pint)", "componentName", componentName);
/* 11701 */       Trace.data(this, "lpiSPICheckPrivileged(Hconn, String, String, int, Pint, Pint)", "entityName", entityName);
/* 11702 */       Trace.data(this, "lpiSPICheckPrivileged(Hconn, String, String, int, Pint, Pint)", "entityType", Integer.valueOf(entityType));
/* 11703 */       Trace.data(this, "lpiSPICheckPrivileged(Hconn, String, String, int, Pint, Pint)", "CompCode", pCompCode);
/* 11704 */       Trace.data(this, "lpiSPICheckPrivileged(Hconn, String, String, int, Pint, Pint)", "Reason", pReason);
/* 11705 */       Trace.data(this, "lpiSPICheckPrivileged(Hconn, String, String, int, Pint, Pint)", "__________");
/*       */     } 
/*       */     
/* 11708 */     pCompCode.x = 0;
/* 11709 */     pReason.x = 0;
/*       */     
/* 11711 */     if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES) {
/* 11712 */       pCompCode.x = 2;
/* 11713 */       pReason.x = 2534;
/*       */     }
/* 11715 */     else if (entityName == null) {
/* 11716 */       pCompCode.x = 2;
/* 11717 */       pReason.x = 2321;
/*       */     }
/* 11719 */     else if (entityName.length() == 0) {
/* 11720 */       pCompCode.x = 2;
/* 11721 */       pReason.x = 6114;
/*       */     } 
/*       */     
/* 11724 */     if (pCompCode.x != 2) {
/*       */       
/* 11726 */       LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 11727 */       final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/* 11728 */       LocalHconn lockedLocalhconn = null; try {
/*       */         final byte[] rxpbBuf;
/* 11730 */         final LocalHconn localhconn = getLocalHconn(hconn);
/* 11731 */         final byte[] qmName = localhconn.getQMNameBytes();
/*       */ 
/*       */         
/* 11734 */         RXPB rxpb = null;
/*       */         
/* 11736 */         if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/* 11737 */           rxpbBuf = null;
/*       */         } else {
/*       */           
/* 11740 */           if (this.inheritRRSContext) {
/* 11741 */             rxpb = getInheritedRxpb(tls, jTls, localhconn);
/*       */           } else {
/*       */             
/* 11744 */             rxpb = localhconn.getRxpb();
/*       */           } 
/* 11746 */           int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/* 11747 */           if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/* 11748 */             tls.rxpbBuf = new byte[rxpbLen];
/*       */           }
/* 11750 */           rxpbBuf = tls.rxpbBuf;
/* 11751 */           rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         } 
/*       */ 
/*       */         
/* 11755 */         JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 11756 */         final byte[] componentNameBuf = (componentName == null) ? null : serializeToFixedByte(componentName, jTls, dc, 48);
/* 11757 */         final byte[] entityNameBuf = serializeToFixedByte(entityName, jTls, dc, 1024);
/*       */ 
/*       */ 
/*       */ 
/*       */         
/* 11762 */         if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hconn)) {
/* 11763 */           localhconn.syncExec(new JmqiRunnable(this.env)
/*       */               {
/*       */                 public void run()
/*       */                 {
/* 11767 */                   if (Trace.isOn) {
/* 11768 */                     Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                   }
/*       */ 
/*       */                   
/*       */                   try {
/* 11773 */                     localhconn.enterCall();
/*       */                     
/* 11775 */                     boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 11776 */                     Native.lpiSPICheckPrivileged(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, componentNameBuf, entityNameBuf, entityType, pCompCode, pReason);
/*       */                   
/*       */                   }
/* 11779 */                   catch (JmqiException e) {
/* 11780 */                     if (Trace.isOn) {
/* 11781 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                     }
/*       */                     
/* 11784 */                     jTls.lastException = e;
/*       */                     
/* 11786 */                     pCompCode.x = e.getCompCode();
/* 11787 */                     pReason.x = e.getReason();
/*       */                   } finally {
/*       */                     
/* 11790 */                     if (Trace.isOn) {
/* 11791 */                       Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                     }
/*       */                     
/*       */                     try {
/* 11795 */                       localhconn.leaveCall();
/*       */                     }
/* 11797 */                     catch (JmqiException e) {
/* 11798 */                       if (Trace.isOn) {
/* 11799 */                         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 2);
/*       */                       }
/* 11801 */                       jTls.lastException = e;
/* 11802 */                       pCompCode.x = e.getCompCode();
/* 11803 */                       pReason.x = e.getReason();
/*       */                     } 
/*       */                   } 
/* 11806 */                   if (Trace.isOn) {
/* 11807 */                     Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   
/*       */                   }
/*       */                 }
/*       */               });
/*       */         }
/*       */         else {
/*       */           
/* 11815 */           if (this.adapterIsRRS) {
/* 11816 */             localhconn.enterCall();
/* 11817 */             lockedLocalhconn = localhconn;
/*       */           } 
/*       */           
/* 11820 */           boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 11821 */           Native.lpiSPICheckPrivileged(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, componentNameBuf, entityNameBuf, entityType, pCompCode, pReason);
/*       */         
/*       */         }
/*       */       
/*       */       }
/* 11826 */       catch (JmqiException e) {
/* 11827 */         if (Trace.isOn) {
/* 11828 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "lpiSPICheckPrivileged(Hconn,String,String,int,Pint,Pint)", (Throwable)e, 1);
/*       */         }
/*       */ 
/*       */         
/* 11832 */         jTls.lastException = e;
/* 11833 */         pCompCode.x = e.getCompCode();
/* 11834 */         pReason.x = e.getReason();
/*       */       }
/* 11836 */       catch (Throwable e) {
/* 11837 */         if (Trace.isOn) {
/* 11838 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "lpiSPICheckPrivileged(Hconn,String,String,int,Pint,Pint)", e, 2);
/*       */         }
/*       */ 
/*       */ 
/*       */         
/* 11843 */         castUnexpectedException("lpiSPICheckPrivileged(Hconn, String, String, int, Pint, Pint)", 1, e);
/*       */       } finally {
/*       */         
/* 11846 */         if (Trace.isOn) {
/* 11847 */           Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "lpiSPICheckPrivileged(Hconn,String,String,int,Pint,Pint)");
/*       */         }
/*       */         
/* 11850 */         if (lockedLocalhconn != null) {
/*       */           
/*       */           try {
/* 11853 */             lockedLocalhconn.leaveCall();
/*       */           }
/* 11855 */           catch (JmqiException e) {
/* 11856 */             if (Trace.isOn) {
/* 11857 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "lpiSPICheckPrivileged(Hconn,String,String,int,Pint,Pint)", (Throwable)e, 3);
/*       */             }
/*       */ 
/*       */             
/* 11861 */             jTls.lastException = e;
/* 11862 */             pCompCode.x = e.getCompCode();
/* 11863 */             pReason.x = e.getReason();
/*       */           } 
/*       */         }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/* 11872 */         if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES && this.inheritRRSContext && 
/* 11873 */           pReason.x == 2009) {
/* 11874 */           clearInheritedRXPB(jTls);
/*       */         }
/*       */       } 
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/* 11882 */     if (Trace.isOn) {
/* 11883 */       Trace.data(this, "lpiSPICheckPrivileged(Hconn, String, String, int, Pint, Pint)", "__________");
/* 11884 */       Trace.data(this, "lpiSPICheckPrivileged(Hconn, String, String, int, Pint, Pint)", "lpiSPICheckPrivileged >>");
/* 11885 */       Trace.data(this, "lpiSPICheckPrivileged(Hconn, String, String, int, Pint, Pint)", "hconn", hconn);
/* 11886 */       Trace.data(this, "lpiSPICheckPrivileged(Hconn, String, String, int, Pint, Pint)", "componentName", componentName);
/* 11887 */       Trace.data(this, "lpiSPICheckPrivileged(Hconn, String, String, int, Pint, Pint)", "entityName", entityName);
/* 11888 */       Trace.data(this, "lpiSPICheckPrivileged(Hconn, String, String, int, Pint, Pint)", "entityType", Integer.valueOf(entityType));
/* 11889 */       Trace.data(this, "lpiSPICheckPrivileged(Hconn, String, String, int, Pint, Pint)", "CompCode", pCompCode);
/* 11890 */       Trace.data(this, "lpiSPICheckPrivileged(Hconn, String, String, int, Pint, Pint)", "Reason", pReason);
/* 11891 */       Trace.data(this, "lpiSPICheckPrivileged(Hconn, String, String, int, Pint, Pint)", "__________");
/*       */     } 
/* 11893 */     if (Trace.isOn) {
/* 11894 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "lpiSPICheckPrivileged(Hconn,String,String,int,Pint,Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */   
/*       */   protected byte[] serializeToFixedByte(String s, JmqiTls jTls, JmqiDC dc, int length) throws JmqiException {
/* 11901 */     if (Trace.isOn) {
/* 11902 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "serializeToFixedByte(String,final JmqiTls,JmqiDC,int)", new Object[] { s, jTls, dc, 
/*       */             
/* 11904 */             Integer.valueOf(length) });
/*       */     }
/* 11906 */     byte[] result = null;
/* 11907 */     if (s != null) {
/* 11908 */       result = new byte[length];
/* 11909 */       dc.writeField(s, result, 0, length, nativeCp, jTls);
/*       */     } 
/* 11911 */     if (Trace.isOn) {
/* 11912 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "serializeToFixedByte(String,final JmqiTls,JmqiDC,int)", result);
/*       */     }
/*       */     
/* 11915 */     return result;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void jmqiPut(Hconn hconn, Hobj hobj, MQMD pMsgDesc, MQPMO pPutMsgOpts, ByteBuffer[] buffers, final Pint pCompCode, final Pint pReason) {
/* 11939 */     if (Trace.isOn) {
/* 11940 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", new Object[] { hconn, hobj, pMsgDesc, pPutMsgOpts, buffers, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */     
/* 11944 */     String fid = "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)";
/*       */ 
/*       */     
/* 11947 */     if (Trace.isOn) {
/* 11948 */       Trace.data(this, "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", "__________");
/* 11949 */       Trace.data(this, "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", "jmqiPut >>");
/* 11950 */       Trace.data(this, "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", "Hconn", hconn);
/* 11951 */       Trace.data(this, "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", "Hobj", hobj);
/* 11952 */       Trace.data(this, "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", "Msgdesc", pMsgDesc);
/* 11953 */       Trace.data(this, "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", "Putmsgopts", pPutMsgOpts);
/* 11954 */       if (buffers == null) {
/* 11955 */         Trace.data(this, "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", "Buffers", "(null)");
/*       */       } else {
/*       */         
/* 11958 */         for (int i = 0; i < buffers.length; i++) {
/* 11959 */           JmqiTools.traceApiBuffer(this, "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", buffers[i], buffers[i].limit());
/*       */         }
/*       */       } 
/* 11962 */       Trace.data(this, "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", "CompCode", pCompCode);
/* 11963 */       Trace.data(this, "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", "Reason", pReason);
/* 11964 */       Trace.data(this, "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", "__________");
/*       */     } 
/*       */     
/* 11967 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 11968 */     final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/* 11969 */     LocalHconn lockedLocalhconn = null;
/*       */     
/* 11971 */     int numBuffers = (buffers == null) ? 0 : buffers.length;
/* 11972 */     final byte[][] bufferArrays = new byte[numBuffers][];
/* 11973 */     final int[] offsets = new int[numBuffers];
/* 11974 */     final int[] lengths = new int[numBuffers];
/* 11975 */     int index = 0;
/* 11976 */     if (buffers != null) {
/* 11977 */       for (int i = 0; i < bufferArrays.length; i++) {
/* 11978 */         if (buffers[i] != null) {
/* 11979 */           bufferArrays[index] = buffers[i].array();
/* 11980 */           offsets[index] = buffers[i].position();
/* 11981 */           lengths[index] = buffers[i].remaining();
/* 11982 */           index++;
/*       */         } 
/*       */       } 
/*       */     }
/* 11986 */     final int actualNumBuffers = index;
/*       */     
/* 11988 */     int originalPutOpts = 0;
/* 11989 */     if (pPutMsgOpts != null) {
/* 11990 */       originalPutOpts = pPutMsgOpts.getOptions();
/*       */     }
/* 11992 */     boolean setId = ((originalPutOpts & 0xC00) != 0);
/* 11993 */     boolean setOrig = ((originalPutOpts & 0x800) != 0);
/*       */     try {
/*       */       final byte[] rxpbBuf, mqmdBuf, optBuf;
/* 11996 */       final LocalHconn localhconn = getLocalHconn(hconn);
/* 11997 */       final LocalHobj localhobj = LocalHobj.getLocalHobj(this.env, hobj);
/* 11998 */       final byte[] qmName = localhconn.getQMNameBytes();
/*       */       
/* 12000 */       RXPB rxpb = null;
/*       */       
/* 12002 */       if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/* 12003 */         rxpbBuf = null;
/*       */       } else {
/*       */         
/* 12006 */         if (this.inheritRRSContext) {
/* 12007 */           rxpb = getInheritedRxpb(tls, jTls, localhconn);
/* 12008 */           if (!Arrays.equals(localhobj.getCtxToken(), rxpb.getCtxTkn()))
/*       */           {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */             
/* 12015 */             JmqiException e = new JmqiException(this.env, -1, null, 2, 2298, null);
/*       */             
/* 12017 */             if (Trace.isOn) {
/* 12018 */               Trace.throwing(this, "com.ibm.mq.jmqi.local.LocalMQ", "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", (Throwable)e);
/*       */             }
/*       */             
/* 12021 */             throw e;
/*       */           }
/*       */         
/*       */         } else {
/*       */           
/* 12026 */           rxpb = localhconn.getRxpb();
/*       */         } 
/* 12028 */         int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/* 12029 */         if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/* 12030 */           tls.rxpbBuf = new byte[rxpbLen];
/*       */         }
/* 12032 */         rxpbBuf = tls.rxpbBuf;
/* 12033 */         rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */       
/* 12037 */       if (pMsgDesc == null) {
/* 12038 */         mqmdBuf = null;
/*       */       } else {
/*       */         
/* 12041 */         int mqmdLen = pMsgDesc.getRequiredBufferSize(ptrSize, nativeCp);
/* 12042 */         if (tls.mqmdBuf == null || tls.mqmdBuf.length < mqmdLen) {
/* 12043 */           tls.mqmdBuf = new byte[mqmdLen];
/*       */         }
/* 12045 */         mqmdBuf = tls.mqmdBuf;
/* 12046 */         pMsgDesc.writeToBuffer(mqmdBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */       
/* 12050 */       if (pPutMsgOpts == null) {
/* 12051 */         optBuf = null;
/*       */       } else {
/*       */         
/* 12054 */         int pmoLen = pPutMsgOpts.getRequiredBufferSize(ptrSize, nativeCp);
/* 12055 */         if (tls.optBuf == null || tls.optBuf.length < pmoLen) {
/* 12056 */           tls.optBuf = new byte[pmoLen];
/*       */         }
/* 12058 */         optBuf = tls.optBuf;
/* 12059 */         pPutMsgOpts.writeToBuffer(optBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 12065 */       if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hconn)) {
/* 12066 */         localhconn.syncExec(new JmqiRunnable(this.env)
/*       */             {
/*       */               public void run()
/*       */               {
/* 12070 */                 if (Trace.isOn) {
/* 12071 */                   Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                 }
/*       */ 
/*       */                 
/*       */                 try {
/* 12076 */                   localhconn.enterCall();
/*       */                   
/* 12078 */                   boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 12079 */                   Native.jmqiPut(isClassTraced, localhconn
/* 12080 */                       .getValue(), qmName, rxpbBuf, localhobj
/*       */ 
/*       */                       
/* 12083 */                       .getValue(), mqmdBuf, optBuf, actualNumBuffers, bufferArrays, offsets, lengths, pCompCode, pReason);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */                 
/*       */                 }
/* 12093 */                 catch (JmqiException e) {
/* 12094 */                   if (Trace.isOn) {
/* 12095 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                   }
/*       */                   
/* 12098 */                   jTls.lastException = e;
/* 12099 */                   pCompCode.x = e.getCompCode();
/* 12100 */                   pReason.x = e.getReason();
/*       */                 }
/* 12102 */                 catch (UnsatisfiedLinkError e) {
/* 12103 */                   if (Trace.isOn) {
/* 12104 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                   }
/* 12106 */                   pCompCode.x = 2;
/* 12107 */                   pReason.x = 2298;
/*       */                 } finally {
/*       */                   
/* 12110 */                   if (Trace.isOn) {
/* 12111 */                     Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                   
/*       */                   try {
/* 12115 */                     localhconn.leaveCall();
/*       */                   }
/* 12117 */                   catch (JmqiException e) {
/* 12118 */                     if (Trace.isOn) {
/* 12119 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                     }
/* 12121 */                     jTls.lastException = e;
/* 12122 */                     pCompCode.x = e.getCompCode();
/* 12123 */                     pReason.x = e.getReason();
/*       */                   } 
/*       */                 } 
/* 12126 */                 if (Trace.isOn) {
/* 12127 */                   Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                 }
/*       */               }
/*       */             });
/*       */       }
/*       */       else {
/*       */         
/* 12134 */         if (this.adapterIsRRS) {
/*       */           
/* 12136 */           localhconn.enterCall();
/* 12137 */           lockedLocalhconn = localhconn;
/*       */         } 
/* 12139 */         boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 12140 */         Native.jmqiPut(isClassTraced, localhconn
/* 12141 */             .getValue(), qmName, rxpbBuf, localhobj
/*       */ 
/*       */             
/* 12144 */             .getValue(), mqmdBuf, optBuf, actualNumBuffers, bufferArrays, offsets, lengths, pCompCode, pReason);
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 12156 */       if (pMsgDesc != null) {
/* 12157 */         pMsgDesc.readFromBuffer(tls.mqmdBuf, 0, true, setId, setOrig, ptrSize, swap, nativeCp, jTls);
/*       */       }
/* 12159 */       if (pPutMsgOpts != null) {
/* 12160 */         pPutMsgOpts.readFromBuffer(tls.optBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       }
/*       */     }
/* 12163 */     catch (JmqiException e) {
/* 12164 */       if (Trace.isOn) {
/* 12165 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", (Throwable)e, 1);
/*       */       }
/*       */       
/* 12168 */       jTls.lastException = e;
/* 12169 */       pCompCode.x = e.getCompCode();
/* 12170 */       pReason.x = e.getReason();
/*       */     }
/* 12172 */     catch (Throwable e) {
/* 12173 */       if (Trace.isOn) {
/* 12174 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", e, 2);
/*       */       }
/*       */       
/* 12177 */       castUnexpectedException("jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", 1, e);
/*       */     } finally {
/*       */       
/* 12180 */       if (Trace.isOn) {
/* 12181 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)");
/*       */       }
/*       */       
/* 12184 */       if (lockedLocalhconn != null) {
/*       */         
/*       */         try {
/* 12187 */           lockedLocalhconn.leaveCall();
/*       */         }
/* 12189 */         catch (JmqiException e) {
/* 12190 */           if (Trace.isOn) {
/* 12191 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", (Throwable)e, 3);
/*       */           }
/*       */           
/* 12194 */           jTls.lastException = e;
/* 12195 */           pCompCode.x = e.getCompCode();
/* 12196 */           pReason.x = e.getReason();
/*       */         } 
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 12205 */       if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES && this.inheritRRSContext && 
/* 12206 */         pReason.x == 2009) {
/* 12207 */         clearInheritedRXPB(jTls);
/*       */       }
/*       */     } 
/*       */ 
/*       */ 
/*       */     
/* 12213 */     if (Trace.isOn) {
/* 12214 */       Trace.data(this, "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", "__________");
/* 12215 */       Trace.data(this, "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", "jmqiPut <<");
/* 12216 */       Trace.data(this, "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", "Hconn", hconn);
/* 12217 */       Trace.data(this, "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", "Hobj", hobj);
/* 12218 */       Trace.data(this, "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", "Msgdesc", pMsgDesc);
/* 12219 */       Trace.data(this, "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", "Putmsgopts", pPutMsgOpts);
/* 12220 */       Trace.data(this, "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", "Buffers", "input");
/* 12221 */       Trace.data(this, "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", "CompCode", pCompCode);
/* 12222 */       Trace.data(this, "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", "Reason", pReason);
/* 12223 */       Trace.data(this, "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", "__________");
/*       */     } 
/*       */     
/* 12226 */     if (Trace.isOn) {
/* 12227 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void jmqiPut1(Hconn hconn, MQOD pObjDesc, MQMD pMsgDesc, MQPMO pPutMsgOpts, ByteBuffer[] buffers, final Pint pCompCode, final Pint pReason) {
/* 12247 */     if (Trace.isOn) {
/* 12248 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "jmqiPut1(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", new Object[] { hconn, pObjDesc, pMsgDesc, pPutMsgOpts, buffers, pCompCode, pReason });
/*       */     }
/*       */     
/* 12251 */     String fid = "jmqiPut1(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)";
/*       */ 
/*       */     
/* 12254 */     if (Trace.isOn) {
/* 12255 */       Trace.data(this, "jmqiPut1(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", "__________");
/* 12256 */       Trace.data(this, "jmqiPut1(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", "jmqiPut1 >>");
/* 12257 */       Trace.data(this, "jmqiPut1(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", "Hconn", hconn);
/* 12258 */       Trace.data(this, "jmqiPut1(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", "Objdesc", pObjDesc);
/* 12259 */       Trace.data(this, "jmqiPut1(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", "Msgdesc", pMsgDesc);
/* 12260 */       Trace.data(this, "jmqiPut1(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", "Putmsgopts", pPutMsgOpts);
/* 12261 */       if (buffers == null) {
/* 12262 */         Trace.data(this, "jmqiPut1(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", "Buffers", "(null)");
/*       */       } else {
/*       */         
/* 12265 */         for (int i = 0; i < buffers.length; i++) {
/* 12266 */           JmqiTools.traceApiBuffer(this, "jmqiPut1(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", buffers[i], buffers[i].limit());
/*       */         }
/*       */       } 
/* 12269 */       Trace.data(this, "jmqiPut1(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", "CompCode", pCompCode);
/* 12270 */       Trace.data(this, "jmqiPut1(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", "Reason", pReason);
/* 12271 */       Trace.data(this, "jmqiPut1(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", "__________");
/*       */     } 
/*       */     
/* 12274 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 12275 */     final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/* 12276 */     LocalHconn lockedLocalhconn = null;
/*       */     
/* 12278 */     int numBuffers = (buffers == null) ? 0 : buffers.length;
/* 12279 */     final byte[][] bufferArrays = new byte[numBuffers][];
/* 12280 */     final int[] offsets = new int[numBuffers];
/* 12281 */     final int[] lengths = new int[numBuffers];
/* 12282 */     int index = 0;
/* 12283 */     if (buffers != null) {
/* 12284 */       for (int i = 0; i < bufferArrays.length; i++) {
/* 12285 */         if (buffers[i] != null) {
/* 12286 */           bufferArrays[index] = buffers[i].array();
/* 12287 */           offsets[index] = buffers[i].position();
/* 12288 */           lengths[index] = buffers[i].remaining();
/* 12289 */           index++;
/*       */         } 
/*       */       } 
/*       */     }
/* 12293 */     final int actualNumBuffers = index; try {
/*       */       final byte[] rxpbBuf, descBuf, mqmdBuf, optBuf;
/* 12295 */       final LocalHconn localhconn = getLocalHconn(hconn);
/* 12296 */       final byte[] qmName = localhconn.getQMNameBytes();
/*       */       
/* 12298 */       RXPB rxpb = null;
/*       */       
/* 12300 */       if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/* 12301 */         rxpbBuf = null;
/*       */       } else {
/*       */         
/* 12304 */         if (this.inheritRRSContext) {
/* 12305 */           rxpb = getInheritedRxpb(tls, jTls, localhconn);
/*       */         } else {
/*       */           
/* 12308 */           rxpb = localhconn.getRxpb();
/*       */         } 
/* 12310 */         int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/* 12311 */         if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/* 12312 */           tls.rxpbBuf = new byte[rxpbLen];
/*       */         }
/* 12314 */         rxpbBuf = tls.rxpbBuf;
/* 12315 */         rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */       
/* 12319 */       if (pObjDesc == null) {
/* 12320 */         descBuf = null;
/*       */       } else {
/*       */         
/* 12323 */         int mqodLen = pObjDesc.getRequiredBufferSize(ptrSize, nativeCp);
/* 12324 */         if (tls.descBuf == null || tls.descBuf.length < mqodLen) {
/* 12325 */           tls.descBuf = new byte[mqodLen];
/*       */         }
/* 12327 */         descBuf = tls.descBuf;
/* 12328 */         pObjDesc.writeToBuffer(descBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */       
/* 12332 */       if (pMsgDesc == null) {
/* 12333 */         mqmdBuf = null;
/*       */       } else {
/*       */         
/* 12336 */         int mqmdLen = pMsgDesc.getRequiredBufferSize(ptrSize, nativeCp);
/* 12337 */         if (tls.mqmdBuf == null || tls.mqmdBuf.length < mqmdLen) {
/* 12338 */           tls.mqmdBuf = new byte[mqmdLen];
/*       */         }
/* 12340 */         mqmdBuf = tls.mqmdBuf;
/* 12341 */         pMsgDesc.writeToBuffer(mqmdBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */       
/* 12345 */       if (pPutMsgOpts == null) {
/* 12346 */         optBuf = null;
/*       */       } else {
/*       */         
/* 12349 */         int pmoLen = pPutMsgOpts.getRequiredBufferSize(ptrSize, nativeCp);
/* 12350 */         if (tls.optBuf == null || tls.optBuf.length < pmoLen) {
/* 12351 */           tls.optBuf = new byte[pmoLen];
/*       */         }
/* 12353 */         optBuf = tls.optBuf;
/* 12354 */         pPutMsgOpts.writeToBuffer(optBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 12360 */       if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hconn)) {
/* 12361 */         localhconn.syncExec(new JmqiRunnable(this.env)
/*       */             {
/*       */               public void run()
/*       */               {
/* 12365 */                 if (Trace.isOn) {
/* 12366 */                   Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                 }
/*       */ 
/*       */                 
/*       */                 try {
/* 12371 */                   localhconn.enterCall();
/*       */                   
/* 12373 */                   boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 12374 */                   Native.jmqiPut1(isClassTraced, localhconn
/* 12375 */                       .getValue(), qmName, rxpbBuf, descBuf, mqmdBuf, optBuf, actualNumBuffers, bufferArrays, offsets, lengths, pCompCode, pReason);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */                 
/*       */                 }
/* 12388 */                 catch (JmqiException e) {
/* 12389 */                   if (Trace.isOn) {
/* 12390 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                   }
/*       */                   
/* 12393 */                   jTls.lastException = e;
/* 12394 */                   pCompCode.x = e.getCompCode();
/* 12395 */                   pReason.x = e.getReason();
/*       */                 }
/* 12397 */                 catch (UnsatisfiedLinkError e) {
/* 12398 */                   if (Trace.isOn) {
/* 12399 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                   }
/* 12401 */                   pCompCode.x = 2;
/* 12402 */                   pReason.x = 2298;
/*       */                 } finally {
/*       */                   
/* 12405 */                   if (Trace.isOn) {
/* 12406 */                     Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                   
/*       */                   try {
/* 12410 */                     localhconn.leaveCall();
/*       */                   }
/* 12412 */                   catch (JmqiException e) {
/* 12413 */                     if (Trace.isOn) {
/* 12414 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                     }
/* 12416 */                     jTls.lastException = e;
/* 12417 */                     pCompCode.x = e.getCompCode();
/* 12418 */                     pReason.x = e.getReason();
/*       */                   } 
/*       */                 } 
/* 12421 */                 if (Trace.isOn) {
/* 12422 */                   Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                 }
/*       */               }
/*       */             });
/*       */       }
/*       */       else {
/*       */         
/* 12429 */         if (this.adapterIsRRS) {
/*       */           
/* 12431 */           localhconn.enterCall();
/* 12432 */           lockedLocalhconn = localhconn;
/*       */         } 
/* 12434 */         boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 12435 */         Native.jmqiPut1(isClassTraced, localhconn
/* 12436 */             .getValue(), qmName, rxpbBuf, descBuf, mqmdBuf, optBuf, actualNumBuffers, bufferArrays, offsets, lengths, pCompCode, pReason);
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 12451 */       if (pObjDesc != null) {
/* 12452 */         pObjDesc.readFromBuffer(descBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       }
/* 12454 */       if (pMsgDesc != null) {
/* 12455 */         pMsgDesc.readFromBuffer(mqmdBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       }
/* 12457 */       if (pPutMsgOpts != null) {
/* 12458 */         pPutMsgOpts.readFromBuffer(optBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       }
/*       */     }
/* 12461 */     catch (JmqiException e) {
/* 12462 */       if (Trace.isOn) {
/* 12463 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "jmqiPut1(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", (Throwable)e, 1);
/*       */       }
/*       */       
/* 12466 */       jTls.lastException = e;
/* 12467 */       pCompCode.x = e.getCompCode();
/* 12468 */       pReason.x = e.getReason();
/*       */     }
/* 12470 */     catch (UnsatisfiedLinkError e) {
/* 12471 */       if (Trace.isOn) {
/* 12472 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "jmqiPut1(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", e, 2);
/*       */       }
/*       */       
/* 12475 */       pCompCode.x = 2;
/* 12476 */       pReason.x = 2298;
/*       */     }
/* 12478 */     catch (Throwable e) {
/* 12479 */       if (Trace.isOn) {
/* 12480 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "jmqiPut1(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", e, 3);
/*       */       }
/*       */       
/* 12483 */       castUnexpectedException("jmqiPut1(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", 1, e);
/*       */     }
/*       */     finally {
/*       */       
/* 12487 */       if (Trace.isOn) {
/* 12488 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "jmqiPut1(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)");
/*       */       }
/*       */       
/* 12491 */       if (lockedLocalhconn != null) {
/*       */         
/*       */         try {
/* 12494 */           lockedLocalhconn.leaveCall();
/*       */         }
/* 12496 */         catch (JmqiException e) {
/* 12497 */           if (Trace.isOn) {
/* 12498 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "jmqiPut1(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", (Throwable)e, 4);
/*       */           }
/*       */           
/* 12501 */           jTls.lastException = e;
/* 12502 */           pCompCode.x = e.getCompCode();
/* 12503 */           pReason.x = e.getReason();
/*       */         } 
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 12514 */       if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES && this.inheritRRSContext && 
/* 12515 */         pReason.x == 2009) {
/* 12516 */         clearInheritedRXPB(jTls);
/*       */       }
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/* 12523 */     if (Trace.isOn) {
/* 12524 */       Trace.data(this, "jmqiPut1(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", "__________");
/* 12525 */       Trace.data(this, "jmqiPut1(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", "jmqiPut1 <<");
/* 12526 */       Trace.data(this, "jmqiPut1(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", "Hconn", hconn);
/* 12527 */       Trace.data(this, "jmqiPut1(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", "Objdesc", pObjDesc);
/* 12528 */       Trace.data(this, "jmqiPut1(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", "Msgdesc", pMsgDesc);
/* 12529 */       Trace.data(this, "jmqiPut1(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", "Putmsgopts", pPutMsgOpts);
/* 12530 */       Trace.data(this, "jmqiPut1(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", "Buffers", "input");
/* 12531 */       Trace.data(this, "jmqiPut1(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", "CompCode", pCompCode);
/* 12532 */       Trace.data(this, "jmqiPut1(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", "Reason", pReason);
/* 12533 */       Trace.data(this, "jmqiPut1(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)", "__________");
/*       */     } 
/* 12535 */     if (Trace.isOn) {
/* 12536 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "jmqiPut1(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void jmqiPutWithTriplets(Hconn hconn, Hobj hobj, MQMD pMsgDesc, MQPMO pPutMsgOpts, ByteBuffer[] buffers, Triplet[] triplets, Pint pCompCode, Pint pReason) {
/* 12556 */     if (Trace.isOn) {
/* 12557 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "jmqiPutWithTriplets(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],Triplet [ ],Pint,Pint)", new Object[] { hconn, hobj, pMsgDesc, pPutMsgOpts, buffers, triplets, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */     
/* 12561 */     if (Trace.isOn) {
/* 12562 */       Trace.data(this, "jmqiPutWithTriplets(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],Triplet [ ],Pint,Pint)", "LocalMQ.jmqiPutWithTriplets() >>", new Object[] { hconn, hobj, pMsgDesc, pPutMsgOpts, buffers, triplets, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/* 12569 */     pCompCode.x = 2;
/* 12570 */     pReason.x = 2298;
/*       */     
/* 12572 */     if (Trace.isOn) {
/* 12573 */       Trace.data(this, "jmqiPutWithTriplets(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],Triplet [ ],Pint,Pint)", "LocalMQ.jmqiPutWithTriplets() <<");
/*       */     }
/*       */ 
/*       */     
/* 12577 */     if (Trace.isOn) {
/* 12578 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "jmqiPutWithTriplets(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],Triplet [ ],Pint,Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void jmqiPut1WithTriplets(Hconn hconn, MQOD pObjDesc, MQMD pMsgDesc, MQPMO pPutMsgOpts, ByteBuffer[] buffers, Triplet[] triplets, Pint pCompCode, Pint pReason) {
/* 12598 */     if (Trace.isOn) {
/* 12599 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "jmqiPut1WithTriplets(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],Triplet [ ],Pint,Pint)", new Object[] { hconn, pObjDesc, pMsgDesc, pPutMsgOpts, buffers, triplets, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */ 
/*       */     
/* 12604 */     if (Trace.isOn) {
/* 12605 */       Trace.data(this, "jmqiPut1WithTriplets(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],Triplet [ ],Pint,Pint)", "LocalMQ.jmqiPutWithTriplets() >>", new Object[] { hconn, pObjDesc, pMsgDesc, pPutMsgOpts, buffers, triplets, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/* 12612 */     pCompCode.x = 2;
/* 12613 */     pReason.x = 2298;
/*       */     
/* 12615 */     if (Trace.isOn) {
/* 12616 */       Trace.data(this, "jmqiPut1WithTriplets(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],Triplet [ ],Pint,Pint)", "LocalMQ.jmqiPut1WithTriplets() <<");
/*       */     }
/*       */ 
/*       */     
/* 12620 */     if (Trace.isOn) {
/* 12621 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "jmqiPut1WithTriplets(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],Triplet [ ],Pint,Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void spiOpen(Hconn hconn, MQOD od, SpiOpenOptions options, Phobj phobj, final Pint pCompCode, final Pint pReason) {
/* 12639 */     if (Trace.isOn) {
/* 12640 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint)", new Object[] { hconn, od, options, phobj, pCompCode, pReason });
/*       */     }
/*       */     
/* 12643 */     String fid = "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint)";
/*       */ 
/*       */     
/* 12646 */     if (Trace.isOn) {
/* 12647 */       Trace.data(this, "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint)", "__________");
/* 12648 */       Trace.data(this, "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint)", "spiOpen >>");
/* 12649 */       Trace.data(this, "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint)", "hconn", hconn);
/* 12650 */       Trace.data(this, "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint)", "od", od);
/* 12651 */       Trace.data(this, "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint)", "options", options);
/* 12652 */       Trace.data(this, "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint)", "hobj", phobj);
/* 12653 */       Trace.data(this, "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint)", "CompCode", pCompCode);
/* 12654 */       Trace.data(this, "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint)", "Reason", pReason);
/* 12655 */       Trace.data(this, "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint)", "__________");
/*       */     } 
/*       */     
/* 12658 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 12659 */     final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/* 12660 */     LocalHconn lockedLocalhconn = null; try {
/*       */       final byte[] rxpbBuf, descBuf, optionsBuf;
/* 12662 */       int openOptions = options.getOptions();
/*       */       
/* 12664 */       if ((openOptions & 0x100000) != 0 && (openOptions & 0x80000) != 0) {
/* 12665 */         JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2046, null);
/*       */ 
/*       */         
/* 12668 */         if (Trace.isOn) {
/* 12669 */           Trace.throwing(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint)", (Throwable)traceRet1);
/*       */         }
/*       */         
/* 12672 */         throw traceRet1;
/*       */       } 
/*       */       
/* 12675 */       final LocalHconn localhconn = getLocalHconn(hconn);
/* 12676 */       final LocalHobj localhobj = LocalHobj.getLocalHobj(this.env, phobj.getHobj());
/* 12677 */       final byte[] qmName = localhconn.getQMNameBytes();
/*       */ 
/*       */       
/* 12680 */       RXPB rxpb = null;
/*       */       
/* 12682 */       if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/* 12683 */         rxpbBuf = null;
/*       */       } else {
/*       */         
/* 12686 */         if (this.inheritRRSContext) {
/* 12687 */           rxpb = getInheritedRxpb(tls, jTls, localhconn);
/*       */         } else {
/*       */           
/* 12690 */           rxpb = localhconn.getRxpb();
/*       */         } 
/* 12692 */         int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/* 12693 */         if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/* 12694 */           tls.rxpbBuf = new byte[rxpbLen];
/*       */         }
/* 12696 */         rxpbBuf = tls.rxpbBuf;
/* 12697 */         rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/* 12702 */       if (od == null) {
/* 12703 */         descBuf = null;
/*       */       } else {
/*       */         
/* 12706 */         int odLen = od.getRequiredBufferSize(ptrSize, nativeCp);
/* 12707 */         if (tls.descBuf == null || tls.descBuf.length < odLen) {
/* 12708 */           tls.descBuf = new byte[odLen];
/*       */         }
/* 12710 */         descBuf = tls.descBuf;
/* 12711 */         od.writeToBuffer(descBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/* 12716 */       if (od == null) {
/* 12717 */         optionsBuf = null;
/*       */       } else {
/*       */         
/* 12720 */         int openOptionsLength = options.getRequiredBufferSize(ptrSize, nativeCp);
/* 12721 */         if (tls.optBuf == null || tls.optBuf.length < openOptionsLength) {
/* 12722 */           tls.optBuf = new byte[openOptionsLength];
/*       */         }
/* 12724 */         optionsBuf = tls.optBuf;
/* 12725 */         options.writeToBuffer(optionsBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 12731 */       if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hconn)) {
/* 12732 */         localhconn.syncExec(new JmqiRunnable(this.env)
/*       */             {
/*       */               public void run()
/*       */               {
/* 12736 */                 if (Trace.isOn) {
/* 12737 */                   Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                 }
/*       */ 
/*       */                 
/*       */                 try {
/* 12742 */                   localhconn.enterCall();
/*       */                   
/* 12744 */                   boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 12745 */                   Native.spiOpen(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, descBuf, optionsBuf, localhobj, pCompCode, pReason);
/*       */                 }
/* 12747 */                 catch (JmqiException e) {
/* 12748 */                   if (Trace.isOn) {
/* 12749 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                   }
/*       */                   
/* 12752 */                   jTls.lastException = e;
/* 12753 */                   pCompCode.x = e.getCompCode();
/* 12754 */                   pReason.x = e.getReason();
/*       */                 }
/* 12756 */                 catch (UnsatisfiedLinkError e) {
/* 12757 */                   if (Trace.isOn) {
/* 12758 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                   }
/* 12760 */                   pCompCode.x = 2;
/* 12761 */                   pReason.x = 2298;
/*       */                 } finally {
/*       */                   
/* 12764 */                   if (Trace.isOn) {
/* 12765 */                     Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                   
/*       */                   try {
/* 12769 */                     localhconn.leaveCall();
/*       */                   }
/* 12771 */                   catch (JmqiException e) {
/* 12772 */                     if (Trace.isOn) {
/* 12773 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                     }
/* 12775 */                     jTls.lastException = e;
/* 12776 */                     pCompCode.x = e.getCompCode();
/* 12777 */                     pReason.x = e.getReason();
/*       */                   } 
/*       */                 } 
/* 12780 */                 if (Trace.isOn) {
/* 12781 */                   Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                 
/*       */                 }
/*       */               }
/*       */             });
/*       */       }
/*       */       else {
/*       */         
/* 12789 */         if (this.adapterIsRRS) {
/*       */           
/* 12791 */           localhconn.enterCall();
/* 12792 */           lockedLocalhconn = localhconn;
/*       */         } 
/* 12794 */         boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 12795 */         Native.spiOpen(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, descBuf, optionsBuf, localhobj, pCompCode, pReason);
/*       */       } 
/*       */ 
/*       */       
/* 12799 */       localhobj.setHobj(phobj);
/* 12800 */       localhobj.setHconn(localhconn);
/* 12801 */       if (od != null) {
/* 12802 */         od.readFromBuffer(descBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       }
/* 12804 */       options.readFromBuffer(optionsBuf, 0, ptrSize, swap, nativeCp, jTls);
/* 12805 */       if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES)
/*       */       {
/* 12807 */         localhobj.setCtxToken(rxpb.getCtxTkn());
/*       */       }
/*       */     }
/* 12810 */     catch (JmqiException e) {
/* 12811 */       if (Trace.isOn) {
/* 12812 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint)", (Throwable)e, 1);
/*       */       }
/*       */       
/* 12815 */       jTls.lastException = e;
/* 12816 */       pCompCode.x = e.getCompCode();
/* 12817 */       pReason.x = e.getReason();
/*       */     }
/* 12819 */     catch (UnsatisfiedLinkError e) {
/* 12820 */       if (Trace.isOn) {
/* 12821 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint)", e, 2);
/*       */       }
/*       */       
/* 12824 */       pCompCode.x = 2;
/* 12825 */       pReason.x = 2298;
/*       */     }
/* 12827 */     catch (Throwable e) {
/* 12828 */       if (Trace.isOn) {
/* 12829 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint)", e, 3);
/*       */       }
/*       */       
/* 12832 */       castUnexpectedException("spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint)", 1, e);
/*       */     } finally {
/*       */       
/* 12835 */       if (Trace.isOn) {
/* 12836 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint)");
/*       */       }
/*       */       
/* 12839 */       if (lockedLocalhconn != null) {
/*       */         
/*       */         try {
/* 12842 */           lockedLocalhconn.leaveCall();
/*       */         }
/* 12844 */         catch (JmqiException e) {
/* 12845 */           if (Trace.isOn) {
/* 12846 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint)", (Throwable)e, 4);
/*       */           }
/*       */           
/* 12849 */           jTls.lastException = e;
/* 12850 */           pCompCode.x = e.getCompCode();
/* 12851 */           pReason.x = e.getReason();
/*       */         } 
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 12860 */       if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES && this.inheritRRSContext && 
/* 12861 */         pReason.x == 2009) {
/* 12862 */         clearInheritedRXPB(jTls);
/*       */       }
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/* 12869 */     if (Trace.isOn) {
/* 12870 */       Trace.data(this, "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint)", "__________");
/* 12871 */       Trace.data(this, "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint)", "spiOpen <<");
/* 12872 */       Trace.data(this, "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint)", "hconn", hconn);
/* 12873 */       Trace.data(this, "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint)", "od", od);
/* 12874 */       Trace.data(this, "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint)", "options", options);
/* 12875 */       Trace.data(this, "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint)", "hobj", phobj);
/* 12876 */       Trace.data(this, "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint)", "CompCode", pCompCode);
/* 12877 */       Trace.data(this, "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint)", "Reason", pReason);
/* 12878 */       Trace.data(this, "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint)", "__________");
/*       */     } 
/* 12880 */     if (Trace.isOn) {
/* 12881 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiOpen(Hconn,MQOD,final SpiOpenOptions,Phobj,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void jmqiNotify(Hconn notifyHconn, Hconn getterHconn, int options, LpiNotifyDetails notifyDetails, Pint pCompCode, Pint pReason) {
/* 12903 */     if (Trace.isOn) {
/* 12904 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "jmqiNotify(Hconn,Hconn,final int,LpiNotifyDetails,final Pint,final Pint)", new Object[] { notifyHconn, getterHconn, 
/*       */             
/* 12906 */             Integer.valueOf(options), notifyDetails, pCompCode, pReason });
/*       */     }
/* 12908 */     boolean callSpiNotify = true;
/*       */     
/* 12910 */     if (notifyDetails.getReason() != 2107) {
/* 12911 */       if (Trace.isOn) {
/* 12912 */         Trace.data(this, "jmqiNotify", "invalid reason code " + notifyDetails.getReason());
/*       */       }
/*       */ 
/*       */       
/* 12916 */       pCompCode.x = 2;
/* 12917 */       pReason.x = 2298;
/* 12918 */       callSpiNotify = false;
/*       */     }
/* 12920 */     else if (getterHconn instanceof LocalHconn && (
/* 12921 */       (LocalHconn)getterHconn).getMQGETinProgressCount() == 0) {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 12927 */       if (Trace.isOn) {
/* 12928 */         Trace.data(this, "jmqiNotify", "Not calling spiNotify as no MQGET calls active", "0");
/*       */       }
/*       */ 
/*       */       
/* 12932 */       pCompCode.x = 0;
/* 12933 */       pReason.x = 0;
/* 12934 */       callSpiNotify = false;
/*       */     } 
/*       */     
/* 12937 */     if (callSpiNotify) {
/* 12938 */       spiNotify(notifyHconn, options, notifyDetails, pCompCode, pReason);
/*       */     }
/*       */     
/* 12941 */     if (Trace.isOn) {
/* 12942 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "jmqiNotify(Hconn,Hconn,final int,LpiNotifyDetails,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void spiNotify(Hconn hconn, final int options, LpiNotifyDetails notifyDetails, final Pint pCompCode, final Pint pReason) {
/* 12956 */     if (Trace.isOn) {
/* 12957 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiNotify(Hconn,final int,LpiNotifyDetails,final Pint,final Pint)", new Object[] { hconn, 
/*       */             
/* 12959 */             Integer.valueOf(options), notifyDetails, pCompCode, pReason });
/*       */     }
/* 12961 */     String fid = "spiNotify(Hconn,final int,LpiNotifyDetails,final Pint,final Pint)";
/*       */ 
/*       */     
/* 12964 */     if (Trace.isOn) {
/* 12965 */       Trace.data(this, "spiNotify(Hconn,final int,LpiNotifyDetails,final Pint,final Pint)", "__________");
/* 12966 */       Trace.data(this, "spiNotify(Hconn,final int,LpiNotifyDetails,final Pint,final Pint)", "spiNotify >>");
/* 12967 */       Trace.data(this, "spiNotify(Hconn,final int,LpiNotifyDetails,final Pint,final Pint)", "Hconn", hconn);
/* 12968 */       Trace.data(this, "spiNotify(Hconn,final int,LpiNotifyDetails,final Pint,final Pint)", "Options", Integer.valueOf(options));
/* 12969 */       Trace.data(this, "spiNotify(Hconn,final int,LpiNotifyDetails,final Pint,final Pint)", "LpiNotifyDetails", notifyDetails);
/* 12970 */       Trace.data(this, "spiNotify(Hconn,final int,LpiNotifyDetails,final Pint,final Pint)", "CompCode", pCompCode);
/* 12971 */       Trace.data(this, "spiNotify(Hconn,final int,LpiNotifyDetails,final Pint,final Pint)", "Reason", pReason);
/* 12972 */       Trace.data(this, "spiNotify(Hconn,final int,LpiNotifyDetails,final Pint,final Pint)", "__________");
/*       */     } 
/*       */     
/* 12975 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 12976 */     final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/* 12977 */     LocalHconn lockedLocalhconn = null; try {
/*       */       final byte[] rxpbBuf, notifyDetailsBuf;
/* 12979 */       final LocalHconn localhconn = getLocalHconn(hconn);
/* 12980 */       final byte[] qmName = localhconn.getQMNameBytes();
/*       */       
/* 12982 */       RXPB rxpb = null;
/*       */       
/* 12984 */       if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/* 12985 */         rxpbBuf = null;
/*       */       } else {
/*       */         
/* 12988 */         if (this.inheritRRSContext) {
/* 12989 */           rxpb = getInheritedRxpb(tls, jTls, localhconn);
/*       */         } else {
/*       */           
/* 12992 */           rxpb = localhconn.getRxpb();
/*       */         } 
/* 12994 */         int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/* 12995 */         if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/* 12996 */           tls.rxpbBuf = new byte[rxpbLen];
/*       */         }
/* 12998 */         rxpbBuf = tls.rxpbBuf;
/* 12999 */         rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */       
/* 13003 */       if (notifyDetails != null) {
/* 13004 */         int boLen = notifyDetails.getRequiredBufferSize(ptrSize, nativeCp);
/* 13005 */         notifyDetailsBuf = new byte[boLen];
/* 13006 */         notifyDetails.writeToBuffer(notifyDetailsBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } else {
/*       */         
/* 13009 */         notifyDetailsBuf = null;
/*       */       } 
/* 13011 */       if (Trace.isOn) {
/* 13012 */         Trace.data(this, "spiNotify(Hconn,final int,LpiNotifyDetails,final Pint,final Pint)", "BeginOptions", notifyDetailsBuf);
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 13018 */       if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hconn)) {
/* 13019 */         localhconn.syncExec(new JmqiRunnable(this.env)
/*       */             {
/*       */               public void run()
/*       */               {
/* 13023 */                 if (Trace.isOn) {
/* 13024 */                   Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                 }
/*       */ 
/*       */                 
/*       */                 try {
/* 13029 */                   localhconn.enterCall();
/*       */                   
/* 13031 */                   boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 13032 */                   Native.spiNotify(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, options, notifyDetailsBuf, pCompCode, pReason);
/*       */                 }
/* 13034 */                 catch (JmqiException e) {
/* 13035 */                   if (Trace.isOn) {
/* 13036 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                   }
/*       */                   
/* 13039 */                   jTls.lastException = e;
/* 13040 */                   pCompCode.x = e.getCompCode();
/* 13041 */                   pReason.x = e.getReason();
/*       */                 }
/* 13043 */                 catch (UnsatisfiedLinkError e) {
/* 13044 */                   if (Trace.isOn) {
/* 13045 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                   }
/* 13047 */                   pCompCode.x = 2;
/* 13048 */                   pReason.x = 2298;
/*       */                 } finally {
/*       */                   
/* 13051 */                   if (Trace.isOn) {
/* 13052 */                     Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                   
/*       */                   try {
/* 13056 */                     localhconn.leaveCall();
/*       */                   }
/* 13058 */                   catch (JmqiException e) {
/* 13059 */                     if (Trace.isOn) {
/* 13060 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                     }
/* 13062 */                     jTls.lastException = e;
/* 13063 */                     pCompCode.x = e.getCompCode();
/* 13064 */                     pReason.x = e.getReason();
/*       */                   } 
/*       */                 } 
/* 13067 */                 if (Trace.isOn) {
/* 13068 */                   Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                 
/*       */                 }
/*       */               }
/*       */             });
/*       */       }
/*       */       else {
/*       */         
/* 13076 */         if (this.adapterIsRRS) {
/*       */           
/* 13078 */           localhconn.enterCall();
/* 13079 */           lockedLocalhconn = localhconn;
/*       */         } 
/* 13081 */         boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 13082 */         Native.spiNotify(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, options, notifyDetailsBuf, pCompCode, pReason);
/*       */       } 
/*       */ 
/*       */       
/* 13086 */       if (notifyDetails != null) {
/* 13087 */         notifyDetails.readFromBuffer(notifyDetailsBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       }
/*       */     }
/* 13090 */     catch (JmqiException e) {
/* 13091 */       if (Trace.isOn) {
/* 13092 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiNotify(Hconn,final int,LpiNotifyDetails,final Pint,final Pint)", (Throwable)e, 1);
/*       */       }
/*       */       
/* 13095 */       jTls.lastException = e;
/* 13096 */       pCompCode.x = e.getCompCode();
/* 13097 */       pReason.x = e.getReason();
/*       */     }
/* 13099 */     catch (UnsatisfiedLinkError e) {
/* 13100 */       if (Trace.isOn) {
/* 13101 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiNotify(Hconn,final int,LpiNotifyDetails,final Pint,final Pint)", e, 2);
/*       */       }
/*       */       
/* 13104 */       pCompCode.x = 2;
/* 13105 */       pReason.x = 2298;
/*       */     }
/* 13107 */     catch (Throwable e) {
/* 13108 */       if (Trace.isOn) {
/* 13109 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiNotify(Hconn,final int,LpiNotifyDetails,final Pint,final Pint)", e, 3);
/*       */       }
/*       */       
/* 13112 */       castUnexpectedException("spiNotify(Hconn,final int,LpiNotifyDetails,final Pint,final Pint)", 1, e);
/*       */     } finally {
/*       */       
/* 13115 */       if (Trace.isOn) {
/* 13116 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiNotify(Hconn,final int,LpiNotifyDetails,final Pint,final Pint)");
/*       */       }
/*       */       
/* 13119 */       if (lockedLocalhconn != null) {
/*       */         
/*       */         try {
/* 13122 */           lockedLocalhconn.leaveCall();
/*       */         }
/* 13124 */         catch (JmqiException e) {
/* 13125 */           if (Trace.isOn) {
/* 13126 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiNotify(Hconn,final int,LpiNotifyDetails,final Pint,final Pint)", (Throwable)e, 4);
/*       */           }
/*       */           
/* 13129 */           jTls.lastException = e;
/* 13130 */           pCompCode.x = e.getCompCode();
/* 13131 */           pReason.x = e.getReason();
/*       */         } 
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 13140 */       if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES && this.inheritRRSContext && 
/* 13141 */         pReason.x == 2009) {
/* 13142 */         clearInheritedRXPB(jTls);
/*       */       }
/*       */     } 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/* 13149 */     if (Trace.isOn) {
/* 13150 */       Trace.data(this, "spiNotify(Hconn,final int,LpiNotifyDetails,final Pint,final Pint)", "__________");
/* 13151 */       Trace.data(this, "spiNotify(Hconn,final int,LpiNotifyDetails,final Pint,final Pint)", "spiNotify <<");
/* 13152 */       Trace.data(this, "spiNotify(Hconn,final int,LpiNotifyDetails,final Pint,final Pint)", "Hconn", hconn);
/* 13153 */       Trace.data(this, "spiNotify(Hconn,final int,LpiNotifyDetails,final Pint,final Pint)", "Options", Integer.valueOf(options));
/* 13154 */       Trace.data(this, "spiNotify(Hconn,final int,LpiNotifyDetails,final Pint,final Pint)", "LpiNotifyDetails", notifyDetails);
/* 13155 */       Trace.data(this, "spiNotify(Hconn,final int,LpiNotifyDetails,final Pint,final Pint)", "CompCode", pCompCode);
/* 13156 */       Trace.data(this, "spiNotify(Hconn,final int,LpiNotifyDetails,final Pint,final Pint)", "Reason", pReason);
/* 13157 */       Trace.data(this, "spiNotify(Hconn,final int,LpiNotifyDetails,final Pint,final Pint)", "__________");
/*       */     } 
/* 13159 */     if (Trace.isOn) {
/* 13160 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiNotify(Hconn,final int,LpiNotifyDetails,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int xa_close(final Hconn hconn, String xa_info, final int rmid, final int flags) {
/* 13171 */     if (Trace.isOn) {
/* 13172 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_close(Hconn,String,final int,final int)", new Object[] { hconn, xa_info, 
/*       */             
/* 13174 */             Integer.valueOf(rmid), Integer.valueOf(flags) });
/*       */     }
/* 13176 */     String fid = "xa_close(Hconn,String,final int,final int)";
/*       */     
/* 13178 */     final Pint returncode = this.env.newPint(0);
/*       */     
/* 13180 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 13181 */     final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/* 13182 */     LocalHconn lockedLocalhconn = null;
/*       */     
/*       */     try {
/*       */       final byte[] xa_info_buffer;
/* 13186 */       if (xa_info == null) {
/* 13187 */         xa_info_buffer = null;
/*       */       } else {
/*       */         
/* 13190 */         xa_info_buffer = new byte[257];
/* 13191 */         byte[] xa_info_noterm = nativeCp.stringToBytes(xa_info);
/* 13192 */         int xa_info_len = Math.min(256, xa_info_noterm.length);
/* 13193 */         System.arraycopy(xa_info_noterm, 0, xa_info_buffer, 0, xa_info_len);
/* 13194 */         xa_info_buffer[xa_info_len] = 0;
/*       */       } 
/* 13196 */       final LocalHconn localhconn = getLocalHconn(hconn);
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 13201 */       if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hconn)) {
/* 13202 */         localhconn.syncExec(new JmqiRunnable(this.env)
/*       */             {
/*       */               public void run()
/*       */               {
/* 13206 */                 if (Trace.isOn) {
/* 13207 */                   Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                 }
/*       */ 
/*       */                 
/*       */                 try {
/* 13212 */                   localhconn.enterCall();
/*       */                   
/* 13214 */                   boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 13215 */                   returncode.x = Native.zstXAClose(isClassTraced, xa_info_buffer, rmid, flags);
/*       */                 }
/* 13217 */                 catch (JmqiException e) {
/* 13218 */                   if (Trace.isOn) {
/* 13219 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                   }
/*       */                   
/* 13222 */                   jTls.lastException = e;
/* 13223 */                   returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */                 }
/* 13225 */                 catch (UnsatisfiedLinkError e) {
/* 13226 */                   if (Trace.isOn) {
/* 13227 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                   }
/* 13229 */                   returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), e);
/*       */                 } finally {
/*       */                   
/* 13232 */                   if (Trace.isOn) {
/* 13233 */                     Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                   
/*       */                   try {
/* 13237 */                     localhconn.leaveCall();
/*       */                   }
/* 13239 */                   catch (JmqiException e) {
/* 13240 */                     if (Trace.isOn) {
/* 13241 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                     }
/* 13243 */                     jTls.lastException = e;
/* 13244 */                     returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */                   } 
/*       */                 } 
/* 13247 */                 if (Trace.isOn) {
/* 13248 */                   Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                 
/*       */                 }
/*       */               }
/*       */             });
/*       */       }
/*       */       else {
/*       */         
/* 13256 */         if (this.adapterIsRRS) {
/* 13257 */           localhconn.enterCall();
/* 13258 */           lockedLocalhconn = localhconn;
/*       */         } 
/* 13260 */         boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 13261 */         returncode.x = Native.zstXAClose(isClassTraced, xa_info_buffer, rmid, flags);
/*       */       }
/*       */     
/* 13264 */     } catch (JmqiException e) {
/* 13265 */       if (Trace.isOn) {
/* 13266 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_close(Hconn,String,final int,final int)", (Throwable)e, 1);
/*       */       }
/*       */       
/* 13269 */       jTls.lastException = e;
/* 13270 */       returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */     }
/* 13272 */     catch (UnsupportedEncodingException e) {
/* 13273 */       if (Trace.isOn) {
/* 13274 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_close(Hconn,String,final int,final int)", e, 2);
/*       */       }
/*       */       
/* 13277 */       returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), e);
/*       */     }
/* 13279 */     catch (UnsatisfiedLinkError e) {
/* 13280 */       if (Trace.isOn) {
/* 13281 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_close(Hconn,String,final int,final int)", e, 3);
/*       */       }
/*       */       
/* 13284 */       returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), e);
/*       */     }
/* 13286 */     catch (Throwable e) {
/* 13287 */       if (Trace.isOn) {
/* 13288 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_close(Hconn,String,final int,final int)", e, 4);
/*       */       }
/*       */       
/* 13291 */       castUnexpectedException("xa_close(Hconn,String,final int,final int)", 1, e);
/*       */     }
/*       */     finally {
/*       */       
/* 13295 */       if (Trace.isOn) {
/* 13296 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_close(Hconn,String,final int,final int)");
/*       */       }
/*       */       
/* 13299 */       if (lockedLocalhconn != null) {
/*       */         
/*       */         try {
/* 13302 */           lockedLocalhconn.leaveCall();
/*       */         }
/* 13304 */         catch (JmqiException e) {
/* 13305 */           if (Trace.isOn) {
/* 13306 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_close(Hconn,String,final int,final int)", (Throwable)e, 5);
/*       */           }
/*       */           
/* 13309 */           jTls.lastException = e;
/* 13310 */           returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */         } 
/*       */       }
/*       */     } 
/*       */     
/* 13315 */     if (Trace.isOn) {
/* 13316 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_close(Hconn,String,final int,final int)", 
/* 13317 */           Integer.valueOf(returncode.x));
/*       */     }
/* 13319 */     return returncode.x;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int xa_commit(final Hconn hconn, Xid xid, final int rmid, final int flags) {
/* 13329 */     if (Trace.isOn) {
/* 13330 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_commit(Hconn,Xid,final int,final int)", new Object[] { hconn, xid, 
/* 13331 */             Integer.valueOf(rmid), Integer.valueOf(flags) });
/*       */     }
/* 13333 */     String fid = "xa_commit(Hconn,Xid,final int,final int)";
/*       */     
/* 13335 */     final Pint returncode = this.env.newPint(0);
/*       */     
/* 13337 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 13338 */     final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/* 13339 */     LocalHconn lockedLocalhconn = null;
/*       */     
/*       */     try {
/*       */       final byte[] xid_buffer;
/* 13343 */       if (xid == null) {
/* 13344 */         xid_buffer = null;
/*       */       } else {
/*       */         
/* 13347 */         xid_buffer = JmqiXid.allocateBuffer();
/* 13348 */         JmqiXid.writeToBuffer(this.env, xid, xid_buffer, 0, swap);
/*       */       } 
/* 13350 */       final LocalHconn localhconn = getLocalHconn(hconn);
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 13355 */       if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hconn)) {
/* 13356 */         localhconn.syncExec(new JmqiRunnable(this.env)
/*       */             {
/*       */               public void run()
/*       */               {
/* 13360 */                 if (Trace.isOn) {
/* 13361 */                   Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                 }
/*       */ 
/*       */                 
/*       */                 try {
/* 13366 */                   localhconn.enterCall();
/*       */                   
/* 13368 */                   boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 13369 */                   returncode.x = Native.zstXACommit(isClassTraced, xid_buffer, rmid, flags);
/*       */                 }
/* 13371 */                 catch (JmqiException e) {
/* 13372 */                   if (Trace.isOn) {
/* 13373 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                   }
/*       */                   
/* 13376 */                   jTls.lastException = e;
/* 13377 */                   returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */                 }
/* 13379 */                 catch (UnsatisfiedLinkError e) {
/* 13380 */                   if (Trace.isOn) {
/* 13381 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                   }
/* 13383 */                   returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), e);
/*       */                 } finally {
/*       */                   
/* 13386 */                   if (Trace.isOn) {
/* 13387 */                     Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                   
/*       */                   try {
/* 13391 */                     localhconn.leaveCall();
/*       */                   }
/* 13393 */                   catch (JmqiException e) {
/* 13394 */                     if (Trace.isOn) {
/* 13395 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                     }
/* 13397 */                     jTls.lastException = e;
/* 13398 */                     returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */                   } 
/*       */                 } 
/* 13401 */                 if (Trace.isOn) {
/* 13402 */                   Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                 
/*       */                 }
/*       */               }
/*       */             });
/*       */       }
/*       */       else {
/*       */         
/* 13410 */         if (this.adapterIsRRS) {
/* 13411 */           localhconn.enterCall();
/* 13412 */           lockedLocalhconn = localhconn;
/*       */         } 
/* 13414 */         boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 13415 */         returncode.x = Native.zstXACommit(isClassTraced, xid_buffer, rmid, flags);
/*       */       }
/*       */     
/* 13418 */     } catch (JmqiException e) {
/* 13419 */       if (Trace.isOn) {
/* 13420 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_commit(Hconn,Xid,final int,final int)", (Throwable)e, 1);
/*       */       }
/*       */       
/* 13423 */       jTls.lastException = e;
/* 13424 */       returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */     }
/* 13426 */     catch (UnsatisfiedLinkError e) {
/* 13427 */       if (Trace.isOn) {
/* 13428 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_commit(Hconn,Xid,final int,final int)", e, 2);
/*       */       }
/*       */       
/* 13431 */       returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), e);
/*       */     }
/* 13433 */     catch (Throwable e) {
/* 13434 */       if (Trace.isOn) {
/* 13435 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_commit(Hconn,Xid,final int,final int)", e, 3);
/*       */       }
/*       */       
/* 13438 */       castUnexpectedException("xa_commit(Hconn,Xid,final int,final int)", 1, e);
/*       */     } finally {
/*       */       
/* 13441 */       if (Trace.isOn) {
/* 13442 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_commit(Hconn,Xid,final int,final int)");
/*       */       }
/*       */       
/* 13445 */       hconn.setXAPrepared(false);
/* 13446 */       if (lockedLocalhconn != null) {
/*       */         
/*       */         try {
/* 13449 */           lockedLocalhconn.leaveCall();
/*       */         }
/* 13451 */         catch (JmqiException e) {
/* 13452 */           if (Trace.isOn) {
/* 13453 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_commit(Hconn,Xid,final int,final int)", (Throwable)e, 4);
/*       */           }
/*       */           
/* 13456 */           jTls.lastException = e;
/* 13457 */           returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */         } 
/*       */       }
/*       */     } 
/*       */     
/* 13462 */     if (Trace.isOn) {
/* 13463 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_commit(Hconn,Xid,final int,final int)", 
/* 13464 */           Integer.valueOf(returncode.x));
/*       */     }
/* 13466 */     return returncode.x;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int xa_complete(final Hconn hconn, final Pint pHandle, final Pint pRetval, final int rmid, final int flags) {
/* 13475 */     if (Trace.isOn) {
/* 13476 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_complete(Hconn,final Pint,final Pint,final int,final int)", new Object[] { hconn, pHandle, pRetval, 
/*       */             
/* 13478 */             Integer.valueOf(rmid), Integer.valueOf(flags) });
/*       */     }
/* 13480 */     String fid = "xa_complete(Hconn,final Pint,final Pint,final int,final int)";
/*       */     
/* 13482 */     final Pint returncode = this.env.newPint(0);
/*       */     
/* 13484 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 13485 */     final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/* 13486 */     LocalHconn lockedLocalhconn = null;
/*       */     try {
/* 13488 */       final LocalHconn localhconn = getLocalHconn(hconn);
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 13493 */       if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hconn)) {
/* 13494 */         localhconn.syncExec(new JmqiRunnable(this.env)
/*       */             {
/*       */               public void run()
/*       */               {
/* 13498 */                 if (Trace.isOn) {
/* 13499 */                   Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                 }
/*       */ 
/*       */                 
/*       */                 try {
/* 13504 */                   localhconn.enterCall();
/*       */                   
/* 13506 */                   boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 13507 */                   returncode.x = Native.zstXAComplete(isClassTraced, pHandle, pRetval, rmid, flags);
/*       */                 }
/* 13509 */                 catch (JmqiException e) {
/* 13510 */                   if (Trace.isOn) {
/* 13511 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                   }
/*       */                   
/* 13514 */                   jTls.lastException = e;
/* 13515 */                   returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */                 }
/* 13517 */                 catch (UnsatisfiedLinkError e) {
/* 13518 */                   if (Trace.isOn) {
/* 13519 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                   }
/* 13521 */                   returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), e);
/*       */                 } finally {
/*       */                   
/* 13524 */                   if (Trace.isOn) {
/* 13525 */                     Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                   
/*       */                   try {
/* 13529 */                     localhconn.leaveCall();
/*       */                   }
/* 13531 */                   catch (JmqiException e) {
/* 13532 */                     if (Trace.isOn) {
/* 13533 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                     }
/* 13535 */                     jTls.lastException = e;
/* 13536 */                     returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */                   } 
/*       */                 } 
/* 13539 */                 if (Trace.isOn) {
/* 13540 */                   Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                 
/*       */                 }
/*       */               }
/*       */             });
/*       */       }
/*       */       else {
/*       */         
/* 13548 */         if (this.adapterIsRRS) {
/* 13549 */           localhconn.enterCall();
/* 13550 */           lockedLocalhconn = localhconn;
/*       */         } 
/* 13552 */         boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 13553 */         returncode.x = Native.zstXAComplete(isClassTraced, pHandle, pRetval, rmid, flags);
/*       */       }
/*       */     
/* 13556 */     } catch (JmqiException e) {
/* 13557 */       if (Trace.isOn) {
/* 13558 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_complete(Hconn,final Pint,final Pint,final int,final int)", (Throwable)e, 1);
/*       */       }
/*       */       
/* 13561 */       jTls.lastException = e;
/* 13562 */       returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */     }
/* 13564 */     catch (UnsatisfiedLinkError e) {
/* 13565 */       if (Trace.isOn) {
/* 13566 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_complete(Hconn,final Pint,final Pint,final int,final int)", e, 2);
/*       */       }
/*       */       
/* 13569 */       returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), e);
/*       */     }
/* 13571 */     catch (Throwable e) {
/* 13572 */       if (Trace.isOn) {
/* 13573 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_complete(Hconn,final Pint,final Pint,final int,final int)", e, 3);
/*       */       }
/*       */       
/* 13576 */       castUnexpectedException("xa_complete(Hconn,final Pint,final Pint,final int,final int)", 1, e);
/*       */     } finally {
/*       */       
/* 13579 */       if (Trace.isOn) {
/* 13580 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_complete(Hconn,final Pint,final Pint,final int,final int)");
/*       */       }
/*       */       
/* 13583 */       if (lockedLocalhconn != null) {
/*       */         
/*       */         try {
/* 13586 */           lockedLocalhconn.leaveCall();
/*       */         }
/* 13588 */         catch (JmqiException e) {
/* 13589 */           if (Trace.isOn) {
/* 13590 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_complete(Hconn,final Pint,final Pint,final int,final int)", (Throwable)e, 4);
/*       */           }
/*       */           
/* 13593 */           jTls.lastException = e;
/* 13594 */           returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */         } 
/*       */       }
/*       */     } 
/*       */     
/* 13599 */     if (Trace.isOn) {
/* 13600 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_complete(Hconn,final Pint,final Pint,final int,final int)", 
/*       */           
/* 13602 */           Integer.valueOf(returncode.x));
/*       */     }
/* 13604 */     return returncode.x;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int xa_end(final Hconn hconn, Xid xid, final int rmid, final int flags) {
/* 13614 */     if (Trace.isOn) {
/* 13615 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_end(Hconn,Xid,final int,final int)", new Object[] { hconn, xid, 
/* 13616 */             Integer.valueOf(rmid), Integer.valueOf(flags) });
/*       */     }
/* 13618 */     String fid = "xa_end(Hconn,Xid,final int,final int)";
/*       */     
/* 13620 */     final Pint returncode = this.env.newPint(0);
/*       */     
/* 13622 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 13623 */     final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/* 13624 */     LocalHconn lockedLocalhconn = null;
/*       */     try {
/*       */       final byte[] xid_buffer;
/* 13627 */       if (xid == null) {
/* 13628 */         xid_buffer = null;
/*       */       } else {
/*       */         
/* 13631 */         xid_buffer = JmqiXid.allocateBuffer();
/* 13632 */         JmqiXid.writeToBuffer(this.env, xid, xid_buffer, 0, swap);
/*       */       } 
/* 13634 */       final LocalHconn localhconn = getLocalHconn(hconn);
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 13639 */       if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hconn)) {
/* 13640 */         localhconn.syncExec(new JmqiRunnable(this.env)
/*       */             {
/*       */               public void run()
/*       */               {
/* 13644 */                 if (Trace.isOn) {
/* 13645 */                   Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                 }
/*       */ 
/*       */                 
/*       */                 try {
/* 13650 */                   localhconn.enterCall();
/*       */                   
/* 13652 */                   boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 13653 */                   returncode.x = Native.zstXAEnd(isClassTraced, xid_buffer, rmid, flags);
/*       */                 }
/* 13655 */                 catch (JmqiException e) {
/* 13656 */                   if (Trace.isOn) {
/* 13657 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                   }
/*       */                   
/* 13660 */                   jTls.lastException = e;
/* 13661 */                   returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */                 }
/* 13663 */                 catch (UnsatisfiedLinkError e) {
/* 13664 */                   if (Trace.isOn) {
/* 13665 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                   }
/* 13667 */                   returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), e);
/*       */                 } finally {
/*       */                   
/* 13670 */                   if (Trace.isOn) {
/* 13671 */                     Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                   
/*       */                   try {
/* 13675 */                     localhconn.leaveCall();
/*       */                   }
/* 13677 */                   catch (JmqiException e) {
/* 13678 */                     if (Trace.isOn) {
/* 13679 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                     }
/* 13681 */                     jTls.lastException = e;
/* 13682 */                     returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */                   } 
/*       */                 } 
/* 13685 */                 if (Trace.isOn) {
/* 13686 */                   Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                 
/*       */                 }
/*       */               }
/*       */             });
/*       */       }
/*       */       else {
/*       */         
/* 13694 */         if (this.adapterIsRRS) {
/* 13695 */           localhconn.enterCall();
/* 13696 */           lockedLocalhconn = localhconn;
/*       */         } 
/* 13698 */         boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 13699 */         returncode.x = Native.zstXAEnd(isClassTraced, xid_buffer, rmid, flags);
/*       */       }
/*       */     
/* 13702 */     } catch (JmqiException e) {
/* 13703 */       if (Trace.isOn) {
/* 13704 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_end(Hconn,Xid,final int,final int)", (Throwable)e, 1);
/*       */       }
/*       */       
/* 13707 */       jTls.lastException = e;
/* 13708 */       returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */     }
/* 13710 */     catch (UnsatisfiedLinkError e) {
/* 13711 */       if (Trace.isOn) {
/* 13712 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_end(Hconn,Xid,final int,final int)", e, 2);
/*       */       }
/*       */       
/* 13715 */       returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), e);
/*       */     }
/* 13717 */     catch (Throwable e) {
/* 13718 */       if (Trace.isOn) {
/* 13719 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_end(Hconn,Xid,final int,final int)", e, 3);
/*       */       }
/*       */       
/* 13722 */       castUnexpectedException("xa_end(Hconn,Xid,final int,final int)", 1, e);
/*       */     } finally {
/*       */       
/* 13725 */       if (Trace.isOn) {
/* 13726 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_end(Hconn,Xid,final int,final int)");
/*       */       }
/*       */       
/* 13729 */       if (lockedLocalhconn != null) {
/*       */         
/*       */         try {
/* 13732 */           lockedLocalhconn.leaveCall();
/*       */         }
/* 13734 */         catch (JmqiException e) {
/* 13735 */           if (Trace.isOn) {
/* 13736 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_end(Hconn,Xid,final int,final int)", (Throwable)e, 4);
/*       */           }
/*       */           
/* 13739 */           jTls.lastException = e;
/* 13740 */           returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */         } 
/*       */       }
/*       */     } 
/*       */     
/* 13745 */     if (Trace.isOn) {
/* 13746 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_end(Hconn,Xid,final int,final int)", 
/* 13747 */           Integer.valueOf(returncode.x));
/*       */     }
/* 13749 */     return returncode.x;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int xa_forget(final Hconn hconn, Xid xid, final int rmid, final int flags) {
/* 13759 */     if (Trace.isOn) {
/* 13760 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_forget(Hconn,Xid,final int,final int)", new Object[] { hconn, xid, 
/* 13761 */             Integer.valueOf(rmid), Integer.valueOf(flags) });
/*       */     }
/* 13763 */     String fid = "xa_forget(Hconn,Xid,final int,final int)";
/*       */     
/* 13765 */     final Pint returncode = this.env.newPint(0);
/*       */     
/* 13767 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 13768 */     final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/* 13769 */     LocalHconn lockedLocalhconn = null;
/*       */     try {
/*       */       final byte[] xid_buffer;
/* 13772 */       if (xid == null) {
/* 13773 */         xid_buffer = null;
/*       */       } else {
/*       */         
/* 13776 */         xid_buffer = JmqiXid.allocateBuffer();
/* 13777 */         JmqiXid.writeToBuffer(this.env, xid, xid_buffer, 0, swap);
/*       */       } 
/* 13779 */       final LocalHconn localhconn = getLocalHconn(hconn);
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 13784 */       if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hconn)) {
/* 13785 */         localhconn.syncExec(new JmqiRunnable(this.env)
/*       */             {
/*       */               public void run()
/*       */               {
/* 13789 */                 if (Trace.isOn) {
/* 13790 */                   Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                 }
/*       */ 
/*       */                 
/*       */                 try {
/* 13795 */                   localhconn.enterCall();
/*       */                   
/* 13797 */                   boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 13798 */                   returncode.x = Native.zstXAForget(isClassTraced, xid_buffer, rmid, flags);
/*       */                 }
/* 13800 */                 catch (JmqiException e) {
/* 13801 */                   if (Trace.isOn) {
/* 13802 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                   }
/*       */                   
/* 13805 */                   jTls.lastException = e;
/* 13806 */                   returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */                 }
/* 13808 */                 catch (UnsatisfiedLinkError e) {
/* 13809 */                   if (Trace.isOn) {
/* 13810 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                   }
/* 13812 */                   returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), e);
/*       */                 } finally {
/*       */                   
/* 13815 */                   if (Trace.isOn) {
/* 13816 */                     Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                   
/*       */                   try {
/* 13820 */                     localhconn.leaveCall();
/*       */                   }
/* 13822 */                   catch (JmqiException e) {
/* 13823 */                     if (Trace.isOn) {
/* 13824 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                     }
/* 13826 */                     jTls.lastException = e;
/* 13827 */                     returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */                   } 
/*       */                 } 
/* 13830 */                 if (Trace.isOn) {
/* 13831 */                   Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                 
/*       */                 }
/*       */               }
/*       */             });
/*       */       }
/*       */       else {
/*       */         
/* 13839 */         if (this.adapterIsRRS) {
/* 13840 */           localhconn.enterCall();
/* 13841 */           lockedLocalhconn = localhconn;
/*       */         } 
/* 13843 */         boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 13844 */         returncode.x = Native.zstXAForget(isClassTraced, xid_buffer, rmid, flags);
/*       */       }
/*       */     
/* 13847 */     } catch (JmqiException e) {
/* 13848 */       if (Trace.isOn) {
/* 13849 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_forget(Hconn,Xid,final int,final int)", (Throwable)e, 1);
/*       */       }
/*       */       
/* 13852 */       jTls.lastException = e;
/* 13853 */       returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */     }
/* 13855 */     catch (UnsatisfiedLinkError e) {
/* 13856 */       if (Trace.isOn) {
/* 13857 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_forget(Hconn,Xid,final int,final int)", e, 2);
/*       */       }
/*       */       
/* 13860 */       returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), e);
/*       */     }
/* 13862 */     catch (Throwable e) {
/* 13863 */       if (Trace.isOn) {
/* 13864 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_forget(Hconn,Xid,final int,final int)", e, 3);
/*       */       }
/*       */       
/* 13867 */       castUnexpectedException("xa_forget(Hconn,Xid,final int,final int)", 1, e);
/*       */     } finally {
/*       */       
/* 13870 */       if (Trace.isOn) {
/* 13871 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_forget(Hconn,Xid,final int,final int)");
/*       */       }
/*       */       
/* 13874 */       if (lockedLocalhconn != null) {
/*       */         
/*       */         try {
/* 13877 */           lockedLocalhconn.leaveCall();
/*       */         }
/* 13879 */         catch (JmqiException e) {
/* 13880 */           if (Trace.isOn) {
/* 13881 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_forget(Hconn,Xid,final int,final int)", (Throwable)e, 4);
/*       */           }
/*       */           
/* 13884 */           jTls.lastException = e;
/* 13885 */           returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */         } 
/*       */       }
/*       */     } 
/*       */     
/* 13890 */     if (Trace.isOn) {
/* 13891 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_forget(Hconn,Xid,final int,final int)", 
/* 13892 */           Integer.valueOf(returncode.x));
/*       */     }
/* 13894 */     return returncode.x;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int xa_open(final Hconn hconn, String xa_info, final int rmid, final int flags) {
/* 13903 */     if (Trace.isOn) {
/* 13904 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_open(Hconn,String,final int,final int)", new Object[] { hconn, xa_info, 
/*       */             
/* 13906 */             Integer.valueOf(rmid), Integer.valueOf(flags) });
/*       */     }
/* 13908 */     String fid = "xa_open(Hconn,String,final int,final int)";
/*       */     
/* 13910 */     final Pint returncode = this.env.newPint(0);
/*       */     
/* 13912 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 13913 */     final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/* 13914 */     LocalHconn lockedLocalhconn = null;
/*       */     
/*       */     try {
/*       */       final byte[] xa_info_buffer;
/* 13918 */       if (xa_info == null) {
/* 13919 */         xa_info_buffer = null;
/*       */       } else {
/*       */         
/* 13922 */         xa_info_buffer = new byte[257];
/* 13923 */         byte[] xa_info_noterm = nativeCp.stringToBytes(xa_info);
/* 13924 */         int xa_info_len = Math.min(256, xa_info_noterm.length);
/* 13925 */         System.arraycopy(xa_info_noterm, 0, xa_info_buffer, 0, xa_info_len);
/* 13926 */         xa_info_buffer[xa_info_len] = 0;
/*       */       } 
/* 13928 */       final LocalHconn localhconn = getLocalHconn(hconn);
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 13933 */       if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hconn)) {
/* 13934 */         localhconn.syncExec(new JmqiRunnable(this.env)
/*       */             {
/*       */               public void run()
/*       */               {
/* 13938 */                 if (Trace.isOn) {
/* 13939 */                   Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                 }
/*       */ 
/*       */                 
/*       */                 try {
/* 13944 */                   localhconn.enterCall();
/*       */                   
/* 13946 */                   boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 13947 */                   returncode.x = Native.zstXAOpen(isClassTraced, xa_info_buffer, rmid, flags);
/*       */                 }
/* 13949 */                 catch (JmqiException e) {
/* 13950 */                   if (Trace.isOn) {
/* 13951 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                   }
/*       */                   
/* 13954 */                   jTls.lastException = e;
/* 13955 */                   returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */                 }
/* 13957 */                 catch (UnsatisfiedLinkError e) {
/* 13958 */                   if (Trace.isOn) {
/* 13959 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                   }
/* 13961 */                   returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), e);
/*       */                 } finally {
/*       */                   
/* 13964 */                   if (Trace.isOn) {
/* 13965 */                     Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                   
/*       */                   try {
/* 13969 */                     localhconn.leaveCall();
/*       */                   }
/* 13971 */                   catch (JmqiException e) {
/* 13972 */                     if (Trace.isOn) {
/* 13973 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                     }
/* 13975 */                     jTls.lastException = e;
/* 13976 */                     returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */                   } 
/*       */                 } 
/* 13979 */                 if (Trace.isOn) {
/* 13980 */                   Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                 
/*       */                 }
/*       */               }
/*       */             });
/*       */       }
/*       */       else {
/*       */         
/* 13988 */         if (this.adapterIsRRS) {
/* 13989 */           localhconn.enterCall();
/* 13990 */           lockedLocalhconn = localhconn;
/*       */         } 
/* 13992 */         boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 13993 */         returncode.x = Native.zstXAOpen(isClassTraced, xa_info_buffer, rmid, flags);
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/* 13998 */       if (returncode.x != 0) {
/* 13999 */         String xa_info_bytearray_string = JmqiTools.arrayToHexString(xa_info_buffer);
/* 14000 */         HashMap<String, Object> ffstInfo = new HashMap<>();
/* 14001 */         ffstInfo.put("returncode.x", Integer.valueOf(returncode.x));
/* 14002 */         ffstInfo.put("xa_info", xa_info);
/* 14003 */         ffstInfo.put("xa_info_bytearray_string", xa_info_bytearray_string);
/* 14004 */         ffstInfo.put("Description", "unexpected XA failure");
/* 14005 */         Trace.ffst(this, "xa_open(Hconn,String,final int,final int)", "01", ffstInfo, null);
/*       */       }
/*       */     
/* 14008 */     } catch (JmqiException e) {
/* 14009 */       if (Trace.isOn) {
/* 14010 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_open(Hconn,String,final int,final int)", (Throwable)e, 1);
/*       */       }
/*       */       
/* 14013 */       jTls.lastException = e;
/* 14014 */       returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */     }
/* 14016 */     catch (UnsupportedEncodingException e) {
/* 14017 */       if (Trace.isOn) {
/* 14018 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_open(Hconn,String,final int,final int)", e, 2);
/*       */       }
/*       */       
/* 14021 */       returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), e);
/*       */     }
/* 14023 */     catch (UnsatisfiedLinkError e) {
/* 14024 */       if (Trace.isOn) {
/* 14025 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_open(Hconn,String,final int,final int)", e, 3);
/*       */       }
/*       */       
/* 14028 */       returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), e);
/*       */     }
/* 14030 */     catch (Throwable e) {
/* 14031 */       if (Trace.isOn) {
/* 14032 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_open(Hconn,String,final int,final int)", e, 4);
/*       */       }
/*       */       
/* 14035 */       castUnexpectedException("xa_open(Hconn,String,final int,final int)", 1, e);
/*       */     } finally {
/*       */       
/* 14038 */       if (Trace.isOn) {
/* 14039 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_open(Hconn,String,final int,final int)");
/*       */       }
/*       */       
/* 14042 */       if (lockedLocalhconn != null) {
/*       */         
/*       */         try {
/* 14045 */           lockedLocalhconn.leaveCall();
/*       */         }
/* 14047 */         catch (JmqiException e) {
/* 14048 */           if (Trace.isOn) {
/* 14049 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_open(Hconn,String,final int,final int)", (Throwable)e, 5);
/*       */           }
/*       */           
/* 14052 */           jTls.lastException = e;
/* 14053 */           returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */         } 
/*       */       }
/*       */     } 
/*       */     
/* 14058 */     if (Trace.isOn) {
/* 14059 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_open(Hconn,String,final int,final int)", 
/* 14060 */           Integer.valueOf(returncode.x));
/*       */     }
/* 14062 */     return returncode.x;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int xa_open_tm(final Hconn hconn, String xa_info, final int rmid, final int flags) {
/* 14071 */     if (Trace.isOn) {
/* 14072 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_open_tm(Hconn,String,final int,final int)", new Object[] { hconn, xa_info, 
/*       */             
/* 14074 */             Integer.valueOf(rmid), Integer.valueOf(flags) });
/*       */     }
/* 14076 */     String fid = "xa_open_tm(Hconn,String,final int,final int)";
/*       */     
/* 14078 */     final Pint returncode = this.env.newPint(0);
/*       */     
/* 14080 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 14081 */     final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/* 14082 */     LocalHconn lockedLocalhconn = null;
/*       */     
/*       */     try {
/*       */       final byte[] xa_info_buffer;
/* 14086 */       if (xa_info == null) {
/* 14087 */         xa_info_buffer = null;
/*       */       } else {
/*       */         
/* 14090 */         xa_info_buffer = new byte[257];
/* 14091 */         byte[] xa_info_noterm = nativeCp.stringToBytes(xa_info);
/* 14092 */         int xa_info_len = Math.min(256, xa_info_noterm.length);
/* 14093 */         System.arraycopy(xa_info_noterm, 0, xa_info_buffer, 0, xa_info_len);
/* 14094 */         xa_info_buffer[xa_info_len] = 0;
/*       */       } 
/* 14096 */       final LocalHconn localhconn = getLocalHconn(hconn);
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 14101 */       if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hconn)) {
/* 14102 */         localhconn.syncExec(new JmqiRunnable(this.env)
/*       */             {
/*       */               public void run()
/*       */               {
/* 14106 */                 if (Trace.isOn) {
/* 14107 */                   Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                 }
/*       */ 
/*       */                 
/*       */                 try {
/* 14112 */                   localhconn.enterCall();
/*       */                   
/* 14114 */                   boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 14115 */                   returncode.x = Native.zstXAOpen_tm(isClassTraced, xa_info_buffer, rmid, flags);
/*       */                 }
/* 14117 */                 catch (JmqiException e) {
/* 14118 */                   if (Trace.isOn) {
/* 14119 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                   }
/*       */                   
/* 14122 */                   jTls.lastException = e;
/* 14123 */                   returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */                 }
/* 14125 */                 catch (UnsatisfiedLinkError e) {
/* 14126 */                   if (Trace.isOn) {
/* 14127 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                   }
/* 14129 */                   returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), e);
/*       */                 } finally {
/*       */                   
/* 14132 */                   if (Trace.isOn) {
/* 14133 */                     Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                   
/*       */                   try {
/* 14137 */                     localhconn.leaveCall();
/*       */                   }
/* 14139 */                   catch (JmqiException e) {
/* 14140 */                     if (Trace.isOn) {
/* 14141 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                     }
/* 14143 */                     jTls.lastException = e;
/* 14144 */                     returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */                   } 
/*       */                 } 
/* 14147 */                 if (Trace.isOn) {
/* 14148 */                   Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                 
/*       */                 }
/*       */               }
/*       */             });
/*       */       }
/*       */       else {
/*       */         
/* 14156 */         if (this.adapterIsRRS) {
/* 14157 */           localhconn.enterCall();
/* 14158 */           lockedLocalhconn = localhconn;
/*       */         } 
/* 14160 */         boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 14161 */         returncode.x = Native.zstXAOpen_tm(isClassTraced, xa_info_buffer, rmid, flags);
/*       */       }
/*       */     
/* 14164 */     } catch (JmqiException e) {
/* 14165 */       if (Trace.isOn) {
/* 14166 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_open_tm(Hconn,String,final int,final int)", (Throwable)e, 1);
/*       */       }
/*       */       
/* 14169 */       jTls.lastException = e;
/* 14170 */       returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */     }
/* 14172 */     catch (UnsupportedEncodingException e) {
/* 14173 */       if (Trace.isOn) {
/* 14174 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_open_tm(Hconn,String,final int,final int)", e, 2);
/*       */       }
/*       */       
/* 14177 */       returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), e);
/*       */     }
/* 14179 */     catch (UnsatisfiedLinkError e) {
/* 14180 */       if (Trace.isOn) {
/* 14181 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_open_tm(Hconn,String,final int,final int)", e, 3);
/*       */       }
/*       */       
/* 14184 */       returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), e);
/*       */     }
/* 14186 */     catch (Throwable e) {
/* 14187 */       if (Trace.isOn) {
/* 14188 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_open_tm(Hconn,String,final int,final int)", e, 4);
/*       */       }
/*       */       
/* 14191 */       castUnexpectedException("xa_open_tm(Hconn,String,final int,final int)", 1, e);
/*       */     } finally {
/*       */       
/* 14194 */       if (Trace.isOn) {
/* 14195 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_open_tm(Hconn,String,final int,final int)");
/*       */       }
/*       */       
/* 14198 */       if (lockedLocalhconn != null) {
/*       */         
/*       */         try {
/* 14201 */           lockedLocalhconn.leaveCall();
/*       */         }
/* 14203 */         catch (JmqiException e) {
/* 14204 */           if (Trace.isOn) {
/* 14205 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_open_tm(Hconn,String,final int,final int)", (Throwable)e, 5);
/*       */           }
/*       */           
/* 14208 */           jTls.lastException = e;
/* 14209 */           returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */         } 
/*       */       }
/*       */     } 
/*       */     
/* 14214 */     if (Trace.isOn) {
/* 14215 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_open_tm(Hconn,String,final int,final int)", 
/* 14216 */           Integer.valueOf(returncode.x));
/*       */     }
/* 14218 */     return returncode.x;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int xa_prepare(final Hconn hconn, Xid xid, final int rmid, final int flags) {
/* 14227 */     if (Trace.isOn) {
/* 14228 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_prepare(Hconn,Xid,final int,final int)", new Object[] { hconn, xid, 
/*       */             
/* 14230 */             Integer.valueOf(rmid), Integer.valueOf(flags) });
/*       */     }
/* 14232 */     String fid = "xa_prepare(Hconn,Xid,final int,final int)";
/*       */     
/* 14234 */     final Pint returncode = this.env.newPint(0);
/*       */     
/* 14236 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 14237 */     final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/* 14238 */     LocalHconn lockedLocalhconn = null;
/*       */     try {
/*       */       final byte[] xid_buffer;
/* 14241 */       if (xid == null) {
/* 14242 */         xid_buffer = null;
/*       */       } else {
/*       */         
/* 14245 */         xid_buffer = JmqiXid.allocateBuffer();
/* 14246 */         JmqiXid.writeToBuffer(this.env, xid, xid_buffer, 0, swap);
/*       */       } 
/* 14248 */       final LocalHconn localhconn = getLocalHconn(hconn);
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 14253 */       if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hconn)) {
/* 14254 */         localhconn.syncExec(new JmqiRunnable(this.env)
/*       */             {
/*       */               public void run()
/*       */               {
/* 14258 */                 if (Trace.isOn) {
/* 14259 */                   Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                 }
/*       */ 
/*       */                 
/*       */                 try {
/* 14264 */                   localhconn.enterCall();
/*       */                   
/* 14266 */                   boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 14267 */                   returncode.x = Native.zstXAPrepare(isClassTraced, xid_buffer, rmid, flags);
/*       */                   
/* 14269 */                   if (returncode.x == 0 || returncode.x == 3) {
/* 14270 */                     hconn.setXAPrepared(true);
/*       */                   }
/*       */                 }
/* 14273 */                 catch (JmqiException e) {
/* 14274 */                   if (Trace.isOn) {
/* 14275 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                   }
/*       */                   
/* 14278 */                   jTls.lastException = e;
/* 14279 */                   returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */                 }
/* 14281 */                 catch (UnsatisfiedLinkError e) {
/* 14282 */                   if (Trace.isOn) {
/* 14283 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                   }
/* 14285 */                   returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), e);
/*       */                 } finally {
/*       */                   
/* 14288 */                   if (Trace.isOn) {
/* 14289 */                     Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                   
/*       */                   try {
/* 14293 */                     localhconn.leaveCall();
/*       */                   }
/* 14295 */                   catch (JmqiException e) {
/* 14296 */                     if (Trace.isOn) {
/* 14297 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                     }
/* 14299 */                     jTls.lastException = e;
/* 14300 */                     returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */                   } 
/*       */                 } 
/* 14303 */                 if (Trace.isOn) {
/* 14304 */                   Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                 
/*       */                 }
/*       */               }
/*       */             });
/*       */       }
/*       */       else {
/*       */         
/* 14312 */         if (this.adapterIsRRS) {
/* 14313 */           localhconn.enterCall();
/* 14314 */           lockedLocalhconn = localhconn;
/*       */         } 
/* 14316 */         boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 14317 */         returncode.x = Native.zstXAPrepare(isClassTraced, xid_buffer, rmid, flags);
/*       */       }
/*       */     
/* 14320 */     } catch (JmqiException e) {
/* 14321 */       if (Trace.isOn) {
/* 14322 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_prepare(Hconn,Xid,final int,final int)", (Throwable)e, 1);
/*       */       }
/*       */       
/* 14325 */       jTls.lastException = e;
/* 14326 */       returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */     }
/* 14328 */     catch (UnsatisfiedLinkError e) {
/* 14329 */       if (Trace.isOn) {
/* 14330 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_prepare(Hconn,Xid,final int,final int)", e, 2);
/*       */       }
/*       */       
/* 14333 */       returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), e);
/*       */     }
/* 14335 */     catch (Throwable e) {
/* 14336 */       if (Trace.isOn) {
/* 14337 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_prepare(Hconn,Xid,final int,final int)", e, 3);
/*       */       }
/*       */       
/* 14340 */       castUnexpectedException("xa_prepare(Hconn,Xid,final int,final int)", 1, e);
/*       */     } finally {
/*       */       
/* 14343 */       if (Trace.isOn) {
/* 14344 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_prepare(Hconn,Xid,final int,final int)");
/*       */       }
/*       */       
/* 14347 */       if (lockedLocalhconn != null) {
/*       */         
/*       */         try {
/* 14350 */           lockedLocalhconn.leaveCall();
/*       */         }
/* 14352 */         catch (JmqiException e) {
/* 14353 */           if (Trace.isOn) {
/* 14354 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_prepare(Hconn,Xid,final int,final int)", (Throwable)e, 4);
/*       */           }
/*       */           
/* 14357 */           jTls.lastException = e;
/* 14358 */           returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */         } 
/*       */       }
/*       */     } 
/*       */     
/* 14363 */     if (Trace.isOn) {
/* 14364 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_prepare(Hconn,Xid,final int,final int)", 
/* 14365 */           Integer.valueOf(returncode.x));
/*       */     }
/* 14367 */     return returncode.x;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int xa_recover(final Hconn hconn, Xid[] xids, final int rmid, final int flags) {
/* 14376 */     if (Trace.isOn) {
/* 14377 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_recover(Hconn,Xid [ ],final int,final int)", new Object[] { hconn, xids, 
/*       */             
/* 14379 */             Integer.valueOf(rmid), Integer.valueOf(flags) });
/*       */     }
/* 14381 */     String fid = "xa_recover(Hconn,Xid [ ],final int,final int)";
/*       */     
/* 14383 */     final Pint returncode = this.env.newPint(0);
/*       */     
/* 14385 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 14386 */     final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/* 14387 */     LocalHconn lockedLocalhconn = null;
/*       */     
/*       */     try {
/*       */       final byte[] xid_buffer;
/* 14391 */       if (xids == null) {
/* 14392 */         xid_buffer = null;
/*       */       } else {
/*       */         
/* 14395 */         xid_buffer = JmqiXid.allocateBuffer(xids.length);
/*       */       } 
/* 14397 */       final LocalHconn localhconn = getLocalHconn(hconn);
/*       */       
/* 14399 */       final int xids_length = (xids == null) ? 0 : xids.length;
/*       */ 
/*       */ 
/*       */       
/* 14403 */       if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hconn)) {
/* 14404 */         localhconn.syncExec(new JmqiRunnable(this.env)
/*       */             {
/*       */               public void run()
/*       */               {
/* 14408 */                 if (Trace.isOn) {
/* 14409 */                   Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                 }
/*       */ 
/*       */                 
/*       */                 try {
/* 14414 */                   localhconn.enterCall();
/*       */                   
/* 14416 */                   boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 14417 */                   returncode.x = Native.zstXARecover(isClassTraced, xid_buffer, xids_length, rmid, flags);
/*       */                 }
/* 14419 */                 catch (JmqiException e) {
/* 14420 */                   if (Trace.isOn) {
/* 14421 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                   }
/*       */                   
/* 14424 */                   jTls.lastException = e;
/* 14425 */                   returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */                 }
/* 14427 */                 catch (UnsatisfiedLinkError e) {
/* 14428 */                   if (Trace.isOn) {
/* 14429 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                   }
/* 14431 */                   returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), e);
/*       */                 } finally {
/*       */                   
/* 14434 */                   if (Trace.isOn) {
/* 14435 */                     Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                   
/*       */                   try {
/* 14439 */                     localhconn.leaveCall();
/*       */                   }
/* 14441 */                   catch (JmqiException e) {
/* 14442 */                     if (Trace.isOn) {
/* 14443 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                     }
/* 14445 */                     jTls.lastException = e;
/* 14446 */                     returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */                   } 
/*       */                 } 
/* 14449 */                 if (Trace.isOn) {
/* 14450 */                   Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                 
/*       */                 }
/*       */               }
/*       */             });
/*       */       }
/*       */       else {
/*       */         
/* 14458 */         if (this.adapterIsRRS) {
/* 14459 */           localhconn.enterCall();
/* 14460 */           lockedLocalhconn = localhconn;
/*       */         } 
/* 14462 */         boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 14463 */         returncode.x = Native.zstXARecover(isClassTraced, xid_buffer, xids_length, rmid, flags);
/*       */       } 
/*       */ 
/*       */       
/* 14467 */       if (xid_buffer != null) {
/* 14468 */         int offset = 0;
/* 14469 */         for (int i = 0; i < returncode.x; i++) {
/* 14470 */           JmqiXid jmqiXid = this.env.newJmqiXid();
/* 14471 */           int size = jmqiXid.readFromBuffer(xid_buffer, offset, swap);
/* 14472 */           xids[i] = (Xid)jmqiXid;
/* 14473 */           offset += size;
/*       */         }
/*       */       
/*       */       } 
/* 14477 */     } catch (JmqiException e) {
/* 14478 */       if (Trace.isOn) {
/* 14479 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_recover(Hconn,Xid [ ],final int,final int)", (Throwable)e, 1);
/*       */       }
/*       */       
/* 14482 */       jTls.lastException = e;
/* 14483 */       returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */     }
/* 14485 */     catch (UnsatisfiedLinkError e) {
/* 14486 */       if (Trace.isOn) {
/* 14487 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_recover(Hconn,Xid [ ],final int,final int)", e, 2);
/*       */       }
/*       */       
/* 14490 */       returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), e);
/*       */     }
/* 14492 */     catch (Throwable e) {
/* 14493 */       if (Trace.isOn) {
/* 14494 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_recover(Hconn,Xid [ ],final int,final int)", e, 3);
/*       */       }
/*       */       
/* 14497 */       castUnexpectedException("xa_recover(Hconn,Xid [ ],final int,final int)", 1, e);
/*       */     } finally {
/*       */       
/* 14500 */       if (Trace.isOn) {
/* 14501 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_recover(Hconn,Xid [ ],final int,final int)");
/*       */       }
/*       */       
/* 14504 */       if (lockedLocalhconn != null) {
/*       */         
/*       */         try {
/* 14507 */           lockedLocalhconn.leaveCall();
/*       */         }
/* 14509 */         catch (JmqiException e) {
/* 14510 */           if (Trace.isOn) {
/* 14511 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_recover(Hconn,Xid [ ],final int,final int)", (Throwable)e, 4);
/*       */           }
/*       */           
/* 14514 */           jTls.lastException = e;
/* 14515 */           returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */         } 
/*       */       }
/*       */     } 
/*       */     
/* 14520 */     if (Trace.isOn) {
/* 14521 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_recover(Hconn,Xid [ ],final int,final int)", 
/* 14522 */           Integer.valueOf(returncode.x));
/*       */     }
/* 14524 */     return returncode.x;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int xa_rollback(final Hconn hconn, Xid xid, final int rmid, final int flags) {
/* 14534 */     if (Trace.isOn) {
/* 14535 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_rollback(Hconn,Xid,final int,final int)", new Object[] { hconn, xid, 
/*       */             
/* 14537 */             Integer.valueOf(rmid), Integer.valueOf(flags) });
/*       */     }
/* 14539 */     String fid = "xa_rollback(Hconn,Xid,final int,final int)";
/*       */     
/* 14541 */     final Pint returncode = this.env.newPint(0);
/*       */     
/* 14543 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 14544 */     final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/* 14545 */     LocalHconn lockedLocalhconn = null;
/*       */     try {
/*       */       final byte[] xid_buffer;
/* 14548 */       if (xid == null) {
/* 14549 */         xid_buffer = null;
/*       */       } else {
/*       */         
/* 14552 */         xid_buffer = JmqiXid.allocateBuffer();
/* 14553 */         JmqiXid.writeToBuffer(this.env, xid, xid_buffer, 0, swap);
/*       */       } 
/* 14555 */       final LocalHconn localhconn = getLocalHconn(hconn);
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 14560 */       if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hconn)) {
/* 14561 */         localhconn.syncExec(new JmqiRunnable(this.env)
/*       */             {
/*       */               public void run()
/*       */               {
/* 14565 */                 if (Trace.isOn) {
/* 14566 */                   Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                 }
/*       */ 
/*       */                 
/*       */                 try {
/* 14571 */                   localhconn.enterCall();
/*       */                   
/* 14573 */                   boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 14574 */                   returncode.x = Native.zstXARollback(isClassTraced, xid_buffer, rmid, flags);
/*       */                 }
/* 14576 */                 catch (JmqiException e) {
/* 14577 */                   if (Trace.isOn) {
/* 14578 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                   }
/*       */                   
/* 14581 */                   jTls.lastException = e;
/* 14582 */                   returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */                 }
/* 14584 */                 catch (UnsatisfiedLinkError e) {
/* 14585 */                   if (Trace.isOn) {
/* 14586 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                   }
/* 14588 */                   returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), e);
/*       */                 } finally {
/*       */                   
/* 14591 */                   if (Trace.isOn) {
/* 14592 */                     Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                   
/*       */                   try {
/* 14596 */                     localhconn.leaveCall();
/*       */                   }
/* 14598 */                   catch (JmqiException e) {
/* 14599 */                     if (Trace.isOn) {
/* 14600 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                     }
/* 14602 */                     jTls.lastException = e;
/* 14603 */                     returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */                   } 
/*       */                 } 
/* 14606 */                 if (Trace.isOn) {
/* 14607 */                   Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                 
/*       */                 }
/*       */               }
/*       */             });
/*       */       }
/*       */       else {
/*       */         
/* 14615 */         if (this.adapterIsRRS) {
/* 14616 */           localhconn.enterCall();
/* 14617 */           lockedLocalhconn = localhconn;
/*       */         } 
/* 14619 */         boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 14620 */         returncode.x = Native.zstXARollback(isClassTraced, xid_buffer, rmid, flags);
/*       */       }
/*       */     
/* 14623 */     } catch (JmqiException e) {
/* 14624 */       if (Trace.isOn) {
/* 14625 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_rollback(Hconn,Xid,final int,final int)", (Throwable)e, 1);
/*       */       }
/*       */       
/* 14628 */       jTls.lastException = e;
/* 14629 */       returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */     }
/* 14631 */     catch (UnsatisfiedLinkError e) {
/* 14632 */       if (Trace.isOn) {
/* 14633 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_rollback(Hconn,Xid,final int,final int)", e, 2);
/*       */       }
/*       */       
/* 14636 */       returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), e);
/*       */     }
/* 14638 */     catch (Throwable e) {
/* 14639 */       if (Trace.isOn) {
/* 14640 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_rollback(Hconn,Xid,final int,final int)", e, 3);
/*       */       }
/*       */       
/* 14643 */       castUnexpectedException("xa_rollback(Hconn,Xid,final int,final int)", 1, e);
/*       */     } finally {
/*       */       
/* 14646 */       if (Trace.isOn) {
/* 14647 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_rollback(Hconn,Xid,final int,final int)");
/*       */       }
/*       */       
/* 14650 */       hconn.setXAPrepared(false);
/* 14651 */       if (lockedLocalhconn != null) {
/*       */         
/*       */         try {
/* 14654 */           lockedLocalhconn.leaveCall();
/*       */         }
/* 14656 */         catch (JmqiException e) {
/* 14657 */           if (Trace.isOn) {
/* 14658 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_rollback(Hconn,Xid,final int,final int)", (Throwable)e, 4);
/*       */           }
/*       */           
/* 14661 */           jTls.lastException = e;
/* 14662 */           returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */         } 
/*       */       }
/*       */     } 
/*       */     
/* 14667 */     if (Trace.isOn) {
/* 14668 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_rollback(Hconn,Xid,final int,final int)", 
/* 14669 */           Integer.valueOf(returncode.x));
/*       */     }
/* 14671 */     return returncode.x;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int xa_start(final Hconn hconn, Xid xid, final int rmid, final int flags) {
/* 14680 */     if (Trace.isOn) {
/* 14681 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_start(Hconn,Xid,final int,final int)", new Object[] { hconn, xid, Integer.valueOf(rmid), Integer.valueOf(flags) });
/*       */     }
/* 14683 */     String fid = "xa_start(Hconn,Xid,final int,final int)";
/*       */     
/* 14685 */     final Pint returncode = this.env.newPint(0);
/*       */     
/* 14687 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 14688 */     final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/* 14689 */     LocalHconn lockedLocalhconn = null;
/*       */     
/*       */     try {
/*       */       final byte[] xid_buffer;
/* 14693 */       if (xid == null) {
/* 14694 */         xid_buffer = null;
/*       */       } else {
/*       */         
/* 14697 */         xid_buffer = JmqiXid.allocateBuffer();
/* 14698 */         JmqiXid.writeToBuffer(this.env, xid, xid_buffer, 0, swap);
/*       */       } 
/* 14700 */       final LocalHconn localhconn = getLocalHconn(hconn);
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 14705 */       if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hconn)) {
/* 14706 */         localhconn.syncExec(new JmqiRunnable(this.env)
/*       */             {
/*       */               public void run()
/*       */               {
/* 14710 */                 if (Trace.isOn) {
/* 14711 */                   Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                 }
/*       */ 
/*       */                 
/*       */                 try {
/* 14716 */                   localhconn.enterCall();
/*       */                   
/* 14718 */                   boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 14719 */                   returncode.x = Native.zstXAStart(isClassTraced, xid_buffer, rmid, flags);
/*       */                 }
/* 14721 */                 catch (JmqiException e) {
/* 14722 */                   if (Trace.isOn) {
/* 14723 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                   }
/*       */                   
/* 14726 */                   jTls.lastException = e;
/* 14727 */                   returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */                 }
/* 14729 */                 catch (UnsatisfiedLinkError e) {
/* 14730 */                   if (Trace.isOn) {
/* 14731 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                   }
/* 14733 */                   returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), e);
/*       */                 } finally {
/*       */                   
/* 14736 */                   if (Trace.isOn) {
/* 14737 */                     Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                   
/*       */                   try {
/* 14741 */                     localhconn.leaveCall();
/*       */                   }
/* 14743 */                   catch (JmqiException e) {
/* 14744 */                     if (Trace.isOn) {
/* 14745 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                     }
/* 14747 */                     jTls.lastException = e;
/* 14748 */                     returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */                   } 
/*       */                 } 
/* 14751 */                 if (Trace.isOn) {
/* 14752 */                   Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                 
/*       */                 }
/*       */               }
/*       */             });
/*       */       }
/*       */       else {
/*       */         
/* 14760 */         if (this.adapterIsRRS) {
/* 14761 */           localhconn.enterCall();
/* 14762 */           lockedLocalhconn = localhconn;
/*       */         } 
/* 14764 */         boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 14765 */         returncode.x = Native.zstXAStart(isClassTraced, xid_buffer, rmid, flags);
/*       */       }
/*       */     
/* 14768 */     } catch (JmqiException e) {
/* 14769 */       if (Trace.isOn) {
/* 14770 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_start(Hconn,Xid,final int,final int)", (Throwable)e, 1);
/*       */       }
/*       */       
/* 14773 */       jTls.lastException = e;
/* 14774 */       returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */     }
/* 14776 */     catch (UnsatisfiedLinkError e) {
/* 14777 */       if (Trace.isOn) {
/* 14778 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_start(Hconn,Xid,final int,final int)", e, 2);
/*       */       }
/*       */       
/* 14781 */       returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), e);
/*       */     }
/* 14783 */     catch (Throwable e) {
/* 14784 */       if (Trace.isOn) {
/* 14785 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_start(Hconn,Xid,final int,final int)", e, 3);
/*       */       }
/*       */       
/* 14788 */       castUnexpectedException("xa_start(Hconn,Xid,final int,final int)", 1, e);
/*       */     } finally {
/*       */       
/* 14791 */       if (Trace.isOn) {
/* 14792 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_start(Hconn,Xid,final int,final int)");
/*       */       }
/*       */       
/* 14795 */       if (lockedLocalhconn != null) {
/*       */         
/*       */         try {
/* 14798 */           lockedLocalhconn.leaveCall();
/*       */         }
/* 14800 */         catch (JmqiException e) {
/* 14801 */           if (Trace.isOn) {
/* 14802 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_start(Hconn,Xid,final int,final int)", (Throwable)e, 4);
/*       */           }
/*       */           
/* 14805 */           jTls.lastException = e;
/* 14806 */           returncode.x = JmqiTools.getXAErrorCode(hconn.isXAPrepared(), (Throwable)e);
/*       */         } 
/*       */       }
/*       */     } 
/*       */     
/* 14811 */     if (Trace.isOn) {
/* 14812 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "xa_start(Hconn,Xid,final int,final int)", 
/* 14813 */           Integer.valueOf(returncode.x));
/*       */     }
/* 14815 */     return returncode.x;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void executeRunnable(Hconn hconn, JmqiRunnable job) throws JmqiException, Exception {
/* 14824 */     if (Trace.isOn) {
/* 14825 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "executeRunnable(Hconn,JmqiRunnable)", new Object[] { hconn, job });
/*       */     }
/*       */     
/* 14828 */     LocalHconn localhconn = getLocalHconn(hconn);
/*       */     
/* 14830 */     if (this.useWorkerThread) {
/* 14831 */       localhconn.syncExec(job);
/*       */     
/*       */     }
/*       */     else {
/*       */       
/* 14836 */       job.run();
/*       */     } 
/*       */     
/* 14839 */     if (Trace.isOn) {
/* 14840 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "executeRunnable(Hconn,JmqiRunnable)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void getMetaData(JmqiMetaData metadata, Pint pCompCode, Pint pReason) {
/* 14854 */     if (Trace.isOn) {
/* 14855 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "getMetaData(JmqiMetaData,Pint,Pint)", new Object[] { metadata, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/* 14862 */     if (Trace.isOn) {
/* 14863 */       Trace.data(this, "getMetaData(JmqiMetaData,Pint,Pint)", "Connecting to cmdLevel " + cmdLevel + " queuemanager");
/*       */     }
/* 14865 */     if (cmdLevel < 800) {
/* 14866 */       if (Trace.isOn) {
/* 14867 */         Trace.data(this, "getMetaData(JmqiMetaData,Pint,Pint)", "Requesting version 1 metadata");
/*       */       }
/* 14869 */       metadata.setVersion(1);
/*       */     } else {
/*       */       
/* 14872 */       if (Trace.isOn) {
/* 14873 */         Trace.data(this, "getMetaData(JmqiMetaData,Pint,Pint)", "Requesting version 2 metadata");
/*       */       }
/* 14875 */       metadata.setVersion(2);
/*       */     } 
/*       */ 
/*       */     
/* 14879 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 14880 */     JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/*       */ 
/*       */     
/*       */     try {
/* 14884 */       byte[] metadataBuffer = null;
/* 14885 */       int size = metadata.getRequiredBufferSize(ptrSize, nativeCp);
/* 14886 */       metadataBuffer = new byte[size];
/* 14887 */       metadata.writeToBuffer(metadataBuffer, 0, ptrSize, swap, nativeCp, jTls);
/* 14888 */       boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 14889 */       Native.getMetaData(isClassTraced, metadataBuffer, pCompCode, pReason);
/*       */       
/* 14891 */       if (pCompCode.x == 0 || pCompCode.x == 1) {
/* 14892 */         metadata.readFromBuffer(metadataBuffer, 0, ptrSize, swap, nativeCp, jTls);
/*       */       }
/*       */     }
/* 14895 */     catch (JmqiException e1) {
/* 14896 */       if (Trace.isOn) {
/* 14897 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "getMetaData(JmqiMetaData,Pint,Pint)", (Throwable)e1, 1);
/*       */       }
/*       */ 
/*       */       
/* 14901 */       jTls.lastException = e1;
/* 14902 */       pCompCode.x = e1.getCompCode();
/* 14903 */       pReason.x = e1.getReason();
/*       */     }
/* 14905 */     catch (UnsatisfiedLinkError e) {
/* 14906 */       if (Trace.isOn) {
/* 14907 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "getMetaData(JmqiMetaData,Pint,Pint)", e, 2);
/*       */       }
/*       */ 
/*       */       
/* 14911 */       pCompCode.x = 2;
/* 14912 */       pReason.x = 2298;
/*       */     } 
/* 14914 */     if (Trace.isOn) {
/* 14915 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "getMetaData(JmqiMetaData,Pint,Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void authenticate(Hconn hconn, String userId, String password, Pint pCompCode, Pint pReason) {
/* 14930 */     String fid = "authenticate(Hconn,String,String,final Pint,final Pint)";
/*       */     
/* 14932 */     if (Trace.isOn) {
/* 14933 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "authenticate(Hconn,String,String,final Pint,final Pint)", new Object[] { hconn, userId, (password == null) ? password : 
/* 14934 */             Integer.valueOf(password.length()), pCompCode, pReason });
/*       */     }
/*       */     
/* 14937 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 14938 */     JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/*       */     try {
/* 14940 */       Adapter adapter = getAdapter();
/* 14941 */       adapter.authenticate(hconn, userId, password, pCompCode, pReason);
/*       */     }
/* 14943 */     catch (JmqiException e) {
/* 14944 */       if (Trace.isOn) {
/* 14945 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "authenticate(Hconn,String,String,final Pint,final Pint)", (Throwable)e);
/*       */       }
/* 14947 */       jTls.lastException = e;
/* 14948 */       pCompCode.x = e.getCompCode();
/* 14949 */       pReason.x = e.getReason();
/*       */     } 
/*       */ 
/*       */     
/* 14953 */     if (Trace.isOn) {
/* 14954 */       Trace.data(this, "authenticate(Hconn,String,String,final Pint,final Pint)", "__________");
/* 14955 */       Trace.data(this, "authenticate(Hconn,String,String,final Pint,final Pint)", "authenticate <<");
/* 14956 */       Trace.data(this, "authenticate(Hconn,String,String,final Pint,final Pint)", "Hconn", hconn);
/* 14957 */       Trace.data(this, "authenticate(Hconn,String,String,final Pint,final Pint)", "userId", userId);
/* 14958 */       Trace.data(this, "authenticate(Hconn,String,String,final Pint,final Pint)", "password", JmqiTools.tracePassword(password));
/* 14959 */       Trace.data(this, "authenticate(Hconn,String,String,final Pint,final Pint)", "CompCode", pCompCode);
/* 14960 */       Trace.data(this, "authenticate(Hconn,String,String,final Pint,final Pint)", "Reason", pReason);
/* 14961 */       Trace.data(this, "authenticate(Hconn,String,String,final Pint,final Pint)", "__________");
/*       */     } 
/*       */     
/* 14964 */     if (Trace.isOn) {
/* 14965 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "authenticate(Hconn,String,String,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void authenticate_dummy(Hconn hconn, String userId, String password, Pint pCompCode, Pint pReason) {}
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void authenticate_native(Hconn hconn, String userId, String password, final Pint pCompCode, final Pint pReason) {
/* 14995 */     if (Trace.isOn) {
/* 14996 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "authenticate_native(Hconn,String,String,final Pint,final Pint)", new Object[] { hconn, userId, 
/*       */             
/* 14998 */             JmqiTools.tracePassword(password), pCompCode, pReason });
/*       */     }
/* 15000 */     String fid = "authenticate_native(Hconn,String,String,final Pint,final Pint)";
/*       */     
/* 15002 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 15003 */     final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/* 15004 */     LocalHconn lockedLocalhconn = null; try {
/*       */       final byte[] rxpbBuf, userIdBytes, passwordBytes;
/* 15006 */       final LocalHconn localhconn = getLocalHconn(hconn);
/* 15007 */       final byte[] qmName = localhconn.getQMNameBytes();
/*       */       
/* 15009 */       RXPB rxpb = null;
/*       */       
/* 15011 */       if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/* 15012 */         rxpbBuf = null;
/*       */       } else {
/*       */         
/* 15015 */         rxpb = localhconn.getUpdatedRxpb();
/* 15016 */         int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/* 15017 */         if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/* 15018 */           tls.rxpbBuf = new byte[rxpbLen];
/*       */         }
/* 15020 */         rxpbBuf = tls.rxpbBuf;
/* 15021 */         rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */       
/* 15025 */       if (userId == null) {
/* 15026 */         userIdBytes = null;
/*       */       } else {
/*       */         
/* 15029 */         byte[] userIdBytesNoTerm = nativeCp.stringToBytes(userId);
/* 15030 */         userIdBytes = new byte[userIdBytesNoTerm.length + 1];
/* 15031 */         System.arraycopy(userIdBytesNoTerm, 0, userIdBytes, 0, userIdBytesNoTerm.length);
/* 15032 */         userIdBytes[userIdBytesNoTerm.length] = 0;
/*       */       } 
/*       */ 
/*       */       
/* 15036 */       if (password == null) {
/* 15037 */         passwordBytes = null;
/*       */       } else {
/*       */         
/* 15040 */         byte[] passwordBytesNoTerm = nativeCp.stringToBytes(password);
/* 15041 */         passwordBytes = new byte[passwordBytesNoTerm.length + 1];
/* 15042 */         System.arraycopy(passwordBytesNoTerm, 0, passwordBytes, 0, passwordBytesNoTerm.length);
/* 15043 */         passwordBytes[passwordBytesNoTerm.length] = 0;
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 15049 */       if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hconn)) {
/* 15050 */         localhconn.syncExec(new JmqiRunnable(this.env)
/*       */             {
/*       */               public void run()
/*       */               {
/* 15054 */                 if (Trace.isOn) {
/* 15055 */                   Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                 }
/*       */ 
/*       */                 
/*       */                 try {
/* 15060 */                   localhconn.enterCall();
/*       */                   
/* 15062 */                   boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 15063 */                   Native.authenticate(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, userIdBytes, passwordBytes, pCompCode, pReason);
/*       */                 }
/* 15065 */                 catch (JmqiException e) {
/* 15066 */                   if (Trace.isOn) {
/* 15067 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                   }
/*       */                   
/* 15070 */                   jTls.lastException = e;
/* 15071 */                   pCompCode.x = e.getCompCode();
/* 15072 */                   pReason.x = e.getReason();
/*       */                 }
/* 15074 */                 catch (UnsatisfiedLinkError e) {
/* 15075 */                   if (Trace.isOn) {
/* 15076 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                   }
/* 15078 */                   pCompCode.x = 2;
/* 15079 */                   pReason.x = 2298;
/*       */                 } finally {
/*       */                   
/* 15082 */                   if (Trace.isOn) {
/* 15083 */                     Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                   
/*       */                   try {
/* 15087 */                     localhconn.leaveCall();
/*       */                   }
/* 15089 */                   catch (JmqiException e) {
/* 15090 */                     if (Trace.isOn) {
/* 15091 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                     }
/* 15093 */                     jTls.lastException = e;
/* 15094 */                     pCompCode.x = e.getCompCode();
/* 15095 */                     pReason.x = e.getReason();
/*       */                   } 
/*       */                 } 
/* 15098 */                 if (Trace.isOn) {
/* 15099 */                   Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                 
/*       */                 }
/*       */               }
/*       */             });
/*       */       }
/*       */       else {
/*       */         
/* 15107 */         if (this.adapterIsRRS) {
/* 15108 */           localhconn.enterCall();
/* 15109 */           lockedLocalhconn = localhconn;
/*       */         } 
/* 15111 */         boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 15112 */         Native.authenticate(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, userIdBytes, passwordBytes, pCompCode, pReason);
/*       */       }
/*       */     
/* 15115 */     } catch (JmqiException e) {
/* 15116 */       if (Trace.isOn) {
/* 15117 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "authenticate_native(Hconn,String,String,final Pint,final Pint)", (Throwable)e, 1);
/*       */       }
/*       */       
/* 15120 */       jTls.lastException = e;
/* 15121 */       pCompCode.x = e.getCompCode();
/* 15122 */       pReason.x = e.getReason();
/*       */     }
/* 15124 */     catch (Throwable e) {
/* 15125 */       if (Trace.isOn) {
/* 15126 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "authenticate_native(Hconn,String,String,final Pint,final Pint)", e, 2);
/*       */       }
/*       */       
/* 15129 */       castUnexpectedException("authenticate_native(Hconn,String,String,final Pint,final Pint)", 1, e);
/*       */     } finally {
/*       */       
/* 15132 */       if (Trace.isOn) {
/* 15133 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "authenticate_native(Hconn,String,String,final Pint,final Pint)");
/*       */       }
/*       */       
/* 15136 */       if (lockedLocalhconn != null) {
/*       */         
/*       */         try {
/* 15139 */           lockedLocalhconn.leaveCall();
/*       */         }
/* 15141 */         catch (JmqiException e) {
/* 15142 */           if (Trace.isOn) {
/* 15143 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "authenticate_native(Hconn,String,String,final Pint,final Pint)", (Throwable)e, 3);
/*       */           }
/*       */           
/* 15146 */           jTls.lastException = e;
/* 15147 */           pCompCode.x = e.getCompCode();
/* 15148 */           pReason.x = e.getReason();
/*       */         } 
/*       */       }
/*       */     } 
/*       */     
/* 15153 */     if (Trace.isOn) {
/* 15154 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "authenticate_native(Hconn,String,String,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean isSharedHandlesSupported() throws JmqiException {
/* 15167 */     Adapter adapter = getAdapter();
/* 15168 */     boolean result = adapter.isSharedHandlesSupported();
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/* 15176 */     if ((this.mqiOptions & 0x2) != 0) {
/* 15177 */       result = false;
/*       */     }
/*       */     
/* 15180 */     if (Trace.isOn) {
/* 15181 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalMQ", "isSharedHandlesSupported()", "getter", 
/* 15182 */           Boolean.valueOf(result));
/*       */     }
/* 15184 */     return result;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int getTlsComponentId() {
/* 15192 */     if (Trace.isOn) {
/* 15193 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalMQ", "getTlsComponentId()", "getter", 
/* 15194 */           Integer.valueOf(this.jmqiCompId));
/*       */     }
/* 15196 */     return this.jmqiCompId;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void checkCmdLevel(Hconn hconn) throws JmqiException {
/* 15207 */     if (Trace.isOn) {
/* 15208 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "checkCmdLevel(Hconn)", new Object[] { hconn });
/*       */     }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/* 15215 */     if (Trace.isOn) {
/* 15216 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "checkCmdLevel(Hconn)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void vpiConvertData(Hconn hconn, final int reqEncoding, final int reqCCSID, final int appOptions, final boolean callExitOnLenErr, MQMD msgDesc, ByteBuffer msgData, final Pint dataLength, final int availableLength, final int bufferLength, final Pint CompCode, final Pint Reason, final Pint returnedLength) {
/* 15250 */     if (Trace.isOn) {
/* 15251 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "vpiConvertData(Hconn,final int,final int,final int,final boolean,MQMD,ByteBuffer,final Pint,final int,final int,final Pint,final Pint,final Pint)", new Object[] { hconn, 
/*       */             
/* 15253 */             Integer.valueOf(reqEncoding), Integer.valueOf(reqCCSID), 
/* 15254 */             Integer.valueOf(appOptions), Boolean.valueOf(callExitOnLenErr), msgDesc, msgData, dataLength, 
/* 15255 */             Integer.valueOf(availableLength), Integer.valueOf(bufferLength), CompCode, Reason, returnedLength });
/*       */     }
/*       */     
/* 15258 */     String fid = "vpiConvertData(Hconn,final int,final int,final int,final boolean,MQMD,ByteBuffer,final Pint,final int,final int,final Pint,final Pint,final Pint)";
/*       */ 
/*       */     
/* 15261 */     if (Trace.isOn) {
/* 15262 */       Trace.data(this, "vpiConvertData(Hconn,final int,final int,final int,final boolean,MQMD,ByteBuffer,final Pint,final int,final int,final Pint,final Pint,final Pint)", "__________");
/* 15263 */       Trace.data(this, "vpiConvertData(Hconn,final int,final int,final int,final boolean,MQMD,ByteBuffer,final Pint,final int,final int,final Pint,final Pint,final Pint)", "vpiConvertData >>");
/* 15264 */       Trace.data(this, "vpiConvertData(Hconn,final int,final int,final int,final boolean,MQMD,ByteBuffer,final Pint,final int,final int,final Pint,final Pint,final Pint)", "hconn", hconn);
/* 15265 */       Trace.data(this, "vpiConvertData(Hconn,final int,final int,final int,final boolean,MQMD,ByteBuffer,final Pint,final int,final int,final Pint,final Pint,final Pint)", "reqEncoding", Integer.toString(reqEncoding));
/* 15266 */       Trace.data(this, "vpiConvertData(Hconn,final int,final int,final int,final boolean,MQMD,ByteBuffer,final Pint,final int,final int,final Pint,final Pint,final Pint)", "reqCCSID", Integer.toString(reqCCSID));
/* 15267 */       Trace.data(this, "vpiConvertData(Hconn,final int,final int,final int,final boolean,MQMD,ByteBuffer,final Pint,final int,final int,final Pint,final Pint,final Pint)", "appOptions", "0x" + Integer.toHexString(appOptions));
/* 15268 */       Trace.data(this, "vpiConvertData(Hconn,final int,final int,final int,final boolean,MQMD,ByteBuffer,final Pint,final int,final int,final Pint,final Pint,final Pint)", "callExitOnLenErr", Boolean.toString(callExitOnLenErr));
/* 15269 */       Trace.data(this, "vpiConvertData(Hconn,final int,final int,final int,final boolean,MQMD,ByteBuffer,final Pint,final int,final int,final Pint,final Pint,final Pint)", "msgDesc", msgDesc);
/* 15270 */       JmqiTools.traceApiBuffer(this, "vpiConvertData(Hconn,final int,final int,final int,final boolean,MQMD,ByteBuffer,final Pint,final int,final int,final Pint,final Pint,final Pint)", msgData, bufferLength);
/* 15271 */       Trace.data(this, "vpiConvertData(Hconn,final int,final int,final int,final boolean,MQMD,ByteBuffer,final Pint,final int,final int,final Pint,final Pint,final Pint)", "dataLength", dataLength);
/* 15272 */       Trace.data(this, "vpiConvertData(Hconn,final int,final int,final int,final boolean,MQMD,ByteBuffer,final Pint,final int,final int,final Pint,final Pint,final Pint)", "availableLength", Integer.toString(availableLength));
/* 15273 */       Trace.data(this, "vpiConvertData(Hconn,final int,final int,final int,final boolean,MQMD,ByteBuffer,final Pint,final int,final int,final Pint,final Pint,final Pint)", "bufferlength", Integer.toString(bufferLength));
/* 15274 */       Trace.data(this, "vpiConvertData(Hconn,final int,final int,final int,final boolean,MQMD,ByteBuffer,final Pint,final int,final int,final Pint,final Pint,final Pint)", "CompCode", CompCode);
/* 15275 */       Trace.data(this, "vpiConvertData(Hconn,final int,final int,final int,final boolean,MQMD,ByteBuffer,final Pint,final int,final int,final Pint,final Pint,final Pint)", "Reason", Reason);
/* 15276 */       Trace.data(this, "vpiConvertData(Hconn,final int,final int,final int,final boolean,MQMD,ByteBuffer,final Pint,final int,final int,final Pint,final Pint,final Pint)", "returnedLength", returnedLength);
/* 15277 */       Trace.data(this, "vpiConvertData(Hconn,final int,final int,final int,final boolean,MQMD,ByteBuffer,final Pint,final int,final int,final Pint,final Pint,final Pint)", "__________");
/*       */     } 
/* 15279 */     CompCode.x = 0;
/* 15280 */     Reason.x = 0;
/*       */ 
/*       */     
/* 15283 */     if (bufferLength > 0) {
/* 15284 */       if (msgData == null) {
/* 15285 */         Trace.data(this, "vpiConvertData(Hconn,final int,final int,final int,final boolean,MQMD,ByteBuffer,final Pint,final int,final int,final Pint,final Pint,final Pint)", "BufferLength: " + bufferLength + ", pBuffer is (null)", null);
/* 15286 */         CompCode.x = 2;
/* 15287 */         Reason.x = 2005;
/*       */       }
/* 15289 */       else if (bufferLength > msgData.limit()) {
/* 15290 */         Trace.data(this, "vpiConvertData(Hconn,final int,final int,final int,final boolean,MQMD,ByteBuffer,final Pint,final int,final int,final Pint,final Pint,final Pint)", "BufferLength:" + bufferLength + ", pBuffer.limit():" + msgData.limit(), null);
/* 15291 */         CompCode.x = 2;
/* 15292 */         Reason.x = 2005;
/*       */       } 
/*       */     }
/* 15295 */     if (Reason.x == 0) {
/*       */       final byte[] pBufferArray;
/* 15297 */       LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 15298 */       final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/* 15299 */       LocalHconn lockedLocalhconn = null;
/*       */ 
/*       */       
/* 15302 */       if (msgData == null) {
/* 15303 */         pBufferArray = null;
/*       */       } else {
/*       */         
/* 15306 */         pBufferArray = msgData.array();
/*       */       }  try {
/*       */         final byte[] rxpbBuf, mqmdBuf;
/* 15309 */         final LocalHconn localhconn = getLocalHconn(hconn);
/* 15310 */         final byte[] qmName = localhconn.getQMNameBytes();
/*       */         
/* 15312 */         RXPB rxpb = null;
/*       */         
/* 15314 */         if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/* 15315 */           rxpbBuf = null;
/*       */         } else {
/*       */           
/* 15318 */           if (this.inheritRRSContext) {
/* 15319 */             rxpb = getInheritedRxpb(tls, jTls, localhconn);
/*       */           } else {
/*       */             
/* 15322 */             rxpb = localhconn.getRxpb();
/*       */           } 
/* 15324 */           int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/* 15325 */           if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/* 15326 */             tls.rxpbBuf = new byte[rxpbLen];
/*       */           }
/* 15328 */           rxpbBuf = tls.rxpbBuf;
/* 15329 */           rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         } 
/*       */ 
/*       */         
/* 15333 */         if (msgDesc == null) {
/* 15334 */           mqmdBuf = null;
/*       */         } else {
/*       */           
/* 15337 */           int mqmdLen = msgDesc.getRequiredBufferSize(ptrSize, nativeCp);
/* 15338 */           if (tls.mqmdBuf == null || tls.mqmdBuf.length < mqmdLen) {
/* 15339 */             tls.mqmdBuf = new byte[mqmdLen];
/*       */           }
/* 15341 */           mqmdBuf = tls.mqmdBuf;
/* 15342 */           msgDesc.writeToBuffer(mqmdBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/* 15348 */         if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hconn)) {
/* 15349 */           localhconn.syncExec(new JmqiRunnable(this.env)
/*       */               {
/*       */                 public void run()
/*       */                 {
/* 15353 */                   if (Trace.isOn) {
/* 15354 */                     Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                   }
/*       */ 
/*       */                   
/*       */                   try {
/* 15359 */                     localhconn.enterCall();
/*       */ 
/*       */                     
/* 15362 */                     boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 15363 */                     Native.vpiConvertData(isClassTraced, localhconn
/* 15364 */                         .getValue(), qmName, rxpbBuf, reqEncoding, reqCCSID, appOptions, callExitOnLenErr, mqmdBuf, pBufferArray, dataLength, availableLength, bufferLength, CompCode, Reason, returnedLength);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */                   
/*       */                   }
/* 15380 */                   catch (JmqiException e) {
/* 15381 */                     if (Trace.isOn) {
/* 15382 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                     }
/*       */                     
/* 15385 */                     jTls.lastException = e;
/* 15386 */                     CompCode.x = e.getCompCode();
/* 15387 */                     Reason.x = e.getReason();
/*       */                   } finally {
/*       */                     
/* 15390 */                     if (Trace.isOn) {
/* 15391 */                       Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                     }
/*       */                     
/*       */                     try {
/* 15395 */                       localhconn.leaveCall();
/*       */                     }
/* 15397 */                     catch (JmqiException e) {
/* 15398 */                       if (Trace.isOn) {
/* 15399 */                         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 2);
/*       */                       }
/* 15401 */                       jTls.lastException = e;
/* 15402 */                       CompCode.x = e.getCompCode();
/* 15403 */                       Reason.x = e.getReason();
/*       */                     } 
/*       */                   } 
/* 15406 */                   if (Trace.isOn) {
/* 15407 */                     Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                 }
/*       */               });
/*       */         }
/*       */         else {
/*       */           
/* 15414 */           if (this.adapterIsRRS) {
/*       */             
/* 15416 */             localhconn.enterCall();
/* 15417 */             lockedLocalhconn = localhconn;
/*       */           } 
/* 15419 */           boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 15420 */           Native.vpiConvertData(isClassTraced, localhconn
/* 15421 */               .getValue(), qmName, rxpbBuf, reqEncoding, reqCCSID, appOptions, callExitOnLenErr, mqmdBuf, pBufferArray, dataLength, availableLength, bufferLength, CompCode, Reason, returnedLength);
/*       */         } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         
/* 15439 */         if (msgDesc != null) {
/* 15440 */           msgDesc.readFromBuffer(mqmdBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */         }
/*       */       }
/* 15443 */       catch (JmqiException e) {
/* 15444 */         if (Trace.isOn) {
/* 15445 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "vpiConvertData(Hconn,final int,final int,final int,final boolean,MQMD,ByteBuffer,final Pint,final int,final int,final Pint,final Pint,final Pint)", (Throwable)e, 1);
/*       */         }
/*       */ 
/*       */         
/* 15449 */         jTls.lastException = e;
/* 15450 */         CompCode.x = e.getCompCode();
/* 15451 */         Reason.x = e.getReason();
/*       */       }
/* 15453 */       catch (Throwable e) {
/* 15454 */         if (Trace.isOn) {
/* 15455 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "vpiConvertData(Hconn,final int,final int,final int,final boolean,MQMD,ByteBuffer,final Pint,final int,final int,final Pint,final Pint,final Pint)", e, 2);
/*       */         }
/*       */ 
/*       */         
/* 15459 */         castUnexpectedException("vpiConvertData(Hconn,final int,final int,final int,final boolean,MQMD,ByteBuffer,final Pint,final int,final int,final Pint,final Pint,final Pint)", 1, e);
/*       */       } finally {
/*       */         
/* 15462 */         if (Trace.isOn) {
/* 15463 */           Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "vpiConvertData(Hconn,final int,final int,final int,final boolean,MQMD,ByteBuffer,final Pint,final int,final int,final Pint,final Pint,final Pint)");
/*       */         }
/*       */         
/* 15466 */         if (lockedLocalhconn != null) {
/*       */           
/*       */           try {
/* 15469 */             lockedLocalhconn.leaveCall();
/*       */           }
/* 15471 */           catch (JmqiException e) {
/* 15472 */             if (Trace.isOn) {
/* 15473 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "vpiConvertData(Hconn,final int,final int,final int,final boolean,MQMD,ByteBuffer,final Pint,final int,final int,final Pint,final Pint,final Pint)", (Throwable)e, 3);
/*       */             }
/*       */ 
/*       */             
/* 15477 */             jTls.lastException = e;
/* 15478 */             CompCode.x = e.getCompCode();
/* 15479 */             Reason.x = e.getReason();
/*       */           } 
/*       */         }
/*       */       } 
/*       */     } 
/*       */ 
/*       */     
/* 15486 */     if (Trace.isOn) {
/* 15487 */       Trace.data(this, "vpiConvertData(Hconn,final int,final int,final int,final boolean,MQMD,ByteBuffer,final Pint,final int,final int,final Pint,final Pint,final Pint)", "__________");
/* 15488 */       Trace.data(this, "vpiConvertData(Hconn,final int,final int,final int,final boolean,MQMD,ByteBuffer,final Pint,final int,final int,final Pint,final Pint,final Pint)", "vpiConvertData <<");
/* 15489 */       Trace.data(this, "vpiConvertData(Hconn,final int,final int,final int,final boolean,MQMD,ByteBuffer,final Pint,final int,final int,final Pint,final Pint,final Pint)", "hconn", hconn);
/* 15490 */       Trace.data(this, "vpiConvertData(Hconn,final int,final int,final int,final boolean,MQMD,ByteBuffer,final Pint,final int,final int,final Pint,final Pint,final Pint)", "reqEncoding", Integer.toString(reqEncoding));
/* 15491 */       Trace.data(this, "vpiConvertData(Hconn,final int,final int,final int,final boolean,MQMD,ByteBuffer,final Pint,final int,final int,final Pint,final Pint,final Pint)", "reqCCSID", Integer.toString(reqCCSID));
/* 15492 */       Trace.data(this, "vpiConvertData(Hconn,final int,final int,final int,final boolean,MQMD,ByteBuffer,final Pint,final int,final int,final Pint,final Pint,final Pint)", "appOptions", "0x" + Integer.toHexString(appOptions));
/* 15493 */       Trace.data(this, "vpiConvertData(Hconn,final int,final int,final int,final boolean,MQMD,ByteBuffer,final Pint,final int,final int,final Pint,final Pint,final Pint)", "callExitOnLenErr", Boolean.toString(callExitOnLenErr));
/* 15494 */       Trace.data(this, "vpiConvertData(Hconn,final int,final int,final int,final boolean,MQMD,ByteBuffer,final Pint,final int,final int,final Pint,final Pint,final Pint)", "msgDesc", msgDesc);
/* 15495 */       JmqiTools.traceApiBuffer(this, "vpiConvertData(Hconn,final int,final int,final int,final boolean,MQMD,ByteBuffer,final Pint,final int,final int,final Pint,final Pint,final Pint)", msgData, bufferLength);
/* 15496 */       Trace.data(this, "vpiConvertData(Hconn,final int,final int,final int,final boolean,MQMD,ByteBuffer,final Pint,final int,final int,final Pint,final Pint,final Pint)", "dataLength", dataLength);
/* 15497 */       Trace.data(this, "vpiConvertData(Hconn,final int,final int,final int,final boolean,MQMD,ByteBuffer,final Pint,final int,final int,final Pint,final Pint,final Pint)", "availableLength", Integer.toString(availableLength));
/* 15498 */       Trace.data(this, "vpiConvertData(Hconn,final int,final int,final int,final boolean,MQMD,ByteBuffer,final Pint,final int,final int,final Pint,final Pint,final Pint)", "bufferlength", Integer.toString(bufferLength));
/* 15499 */       Trace.data(this, "vpiConvertData(Hconn,final int,final int,final int,final boolean,MQMD,ByteBuffer,final Pint,final int,final int,final Pint,final Pint,final Pint)", "CompCode", CompCode);
/* 15500 */       Trace.data(this, "vpiConvertData(Hconn,final int,final int,final int,final boolean,MQMD,ByteBuffer,final Pint,final int,final int,final Pint,final Pint,final Pint)", "Reason", Reason);
/* 15501 */       Trace.data(this, "vpiConvertData(Hconn,final int,final int,final int,final boolean,MQMD,ByteBuffer,final Pint,final int,final int,final Pint,final Pint,final Pint)", "returnedLength", returnedLength);
/* 15502 */       Trace.data(this, "vpiConvertData(Hconn,final int,final int,final int,final boolean,MQMD,ByteBuffer,final Pint,final int,final int,final Pint,final Pint,final Pint)", "__________");
/*       */     } 
/* 15504 */     if (Trace.isOn) {
/* 15505 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "vpiConvertData(Hconn,final int,final int,final int,final boolean,MQMD,ByteBuffer,final Pint,final int,final int,final Pint,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void honourRRS(Hconn hconn, Pint pCompCode, Pint pReason) {
/* 15521 */     if (Trace.isOn) {
/* 15522 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "honourRRS(Hconn,Pint,Pint)", new Object[] { hconn, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */ 
/*       */     
/* 15527 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 15528 */     JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/*       */     try {
/* 15530 */       Adapter adapter = getAdapter();
/* 15531 */       adapter.honourRRS(hconn, pCompCode, pReason);
/*       */     }
/* 15533 */     catch (JmqiException e) {
/* 15534 */       if (Trace.isOn) {
/* 15535 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "honourRRS(Hconn,Pint,Pint)", (Throwable)e);
/*       */       }
/*       */       
/* 15538 */       jTls.lastException = e;
/* 15539 */       pCompCode.x = e.getCompCode();
/* 15540 */       pReason.x = e.getReason();
/*       */     } 
/*       */     
/* 15543 */     if (Trace.isOn) {
/* 15544 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "honourRRS(Hconn,Pint,Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean isAsyncConsumeThread(Hconn hconn) {
/* 15555 */     if (Trace.isOn) {
/* 15556 */       Trace.data(this, "isAsyncConsumeThread(Hconn)", "isAsyncConsumeThread >>", hconn);
/*       */     }
/*       */     
/* 15559 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 15560 */     JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/* 15561 */     boolean traceRet1 = jTls.isAsyncConsumeThread(hconn);
/* 15562 */     return traceRet1;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void spiDefine(Hconn hconn, final boolean replace, LexObjectSelector objectSelector, String likeObjectName, final int SelectorCount, int[] pSelectors, final int IntAttrCount, int[] pIntAttrs, final int CharAttrLength, final byte[] pCharAttrs, LexFilterElement filterElement, final int AttrErrorsCount, int[] AttrErrors, final Pint pCompCode, final Pint pReason) {
/* 15599 */     if (Trace.isOn) {
/* 15600 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiDefine(Hconn,final boolean,LexObjectSelector,String,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final int,int [ ],final Pint,final Pint)", new Object[] { hconn, 
/*       */             
/* 15602 */             Boolean.valueOf(replace), objectSelector, likeObjectName, 
/* 15603 */             Integer.valueOf(SelectorCount), pSelectors, Integer.valueOf(IntAttrCount), pIntAttrs, 
/* 15604 */             Integer.valueOf(CharAttrLength), pCharAttrs, filterElement, 
/* 15605 */             Integer.valueOf(AttrErrorsCount), AttrErrors, pCompCode, pReason });
/*       */     }
/* 15607 */     String fid = "spiDefine(Hconn,final boolean,LexObjectSelector,String,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final int,int [ ],final Pint,final Pint)";
/*       */ 
/*       */     
/* 15610 */     if (Trace.isOn) {
/* 15611 */       Trace.data(this, "spiDefine(Hconn,final boolean,LexObjectSelector,String,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final int,int [ ],final Pint,final Pint)", "__________");
/* 15612 */       Trace.data(this, "spiDefine(Hconn,final boolean,LexObjectSelector,String,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final int,int [ ],final Pint,final Pint)", "spiDefine >>");
/* 15613 */       Trace.data(this, "spiDefine(Hconn,final boolean,LexObjectSelector,String,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final int,int [ ],final Pint,final Pint)", "Hconn", hconn);
/* 15614 */       Trace.data(this, "spiDefine(Hconn,final boolean,LexObjectSelector,String,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final int,int [ ],final Pint,final Pint)", "replace", Boolean.valueOf(replace));
/* 15615 */       Trace.data(this, "spiDefine(Hconn,final boolean,LexObjectSelector,String,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final int,int [ ],final Pint,final Pint)", "objectSelector", objectSelector);
/* 15616 */       Trace.data(this, "spiDefine(Hconn,final boolean,LexObjectSelector,String,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final int,int [ ],final Pint,final Pint)", "likeObject", likeObjectName);
/* 15617 */       Trace.data(this, "spiDefine(Hconn,final boolean,LexObjectSelector,String,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final int,int [ ],final Pint,final Pint)", "SelectorCount", Integer.toString(SelectorCount));
/* 15618 */       Trace.data(this, "spiDefine(Hconn,final boolean,LexObjectSelector,String,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final int,int [ ],final Pint,final Pint)", "Selectors", pSelectors);
/* 15619 */       Trace.data(this, "spiDefine(Hconn,final boolean,LexObjectSelector,String,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final int,int [ ],final Pint,final Pint)", "IntattrCount", Integer.toString(IntAttrCount));
/* 15620 */       Trace.data(this, "spiDefine(Hconn,final boolean,LexObjectSelector,String,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final int,int [ ],final Pint,final Pint)", "Intattrs", pIntAttrs);
/* 15621 */       Trace.data(this, "spiDefine(Hconn,final boolean,LexObjectSelector,String,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final int,int [ ],final Pint,final Pint)", "CharAttrLength", Integer.toString(CharAttrLength));
/* 15622 */       Trace.data(this, "spiDefine(Hconn,final boolean,LexObjectSelector,String,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final int,int [ ],final Pint,final Pint)", "CharAttrs", pCharAttrs);
/* 15623 */       Trace.data(this, "spiDefine(Hconn,final boolean,LexObjectSelector,String,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final int,int [ ],final Pint,final Pint)", "filterElement", filterElement);
/* 15624 */       Trace.data(this, "spiDefine(Hconn,final boolean,LexObjectSelector,String,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final int,int [ ],final Pint,final Pint)", "AttrErrors", AttrErrors);
/* 15625 */       Trace.data(this, "spiDefine(Hconn,final boolean,LexObjectSelector,String,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final int,int [ ],final Pint,final Pint)", "CompCode", pCompCode);
/* 15626 */       Trace.data(this, "spiDefine(Hconn,final boolean,LexObjectSelector,String,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final int,int [ ],final Pint,final Pint)", "Reason", pReason);
/* 15627 */       Trace.data(this, "spiDefine(Hconn,final boolean,LexObjectSelector,String,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final int,int [ ],final Pint,final Pint)", "__________");
/*       */     } 
/*       */     
/* 15630 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 15631 */     final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/* 15632 */     LocalHconn lockedLocalhconn = null;
/* 15633 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*       */     try {
/*       */       final byte[] rxpbBuf, objectSelectorBuffer, selectors, intAttrs, filterElementBuffer, AttrErrorsBuffer;
/* 15636 */       final LocalHconn localhconn = getLocalHconn(hconn);
/* 15637 */       final byte[] qmName = localhconn.getQMNameBytes();
/*       */       
/* 15639 */       RXPB rxpb = null;
/*       */       
/* 15641 */       if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/* 15642 */         rxpbBuf = null;
/*       */       } else {
/*       */         
/* 15645 */         rxpb = localhconn.getUpdatedRxpb();
/* 15646 */         int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/* 15647 */         if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/* 15648 */           tls.rxpbBuf = new byte[rxpbLen];
/*       */         }
/* 15650 */         rxpbBuf = tls.rxpbBuf;
/* 15651 */         rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/* 15656 */       if (objectSelector == null) {
/* 15657 */         objectSelectorBuffer = null;
/*       */       } else {
/*       */         
/* 15660 */         int objectSelectorLength = objectSelector.getRequiredBufferSize(ptrSize, nativeCp);
/* 15661 */         objectSelectorBuffer = new byte[objectSelectorLength];
/* 15662 */         objectSelector.writeToBuffer(objectSelectorBuffer, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/* 15667 */       final byte[] likeObjectNameBytes = new byte[48];
/* 15668 */       dc.writeMQField(likeObjectName, likeObjectNameBytes, 0, 48, nativeCp, jTls);
/*       */ 
/*       */ 
/*       */       
/* 15672 */       if (pSelectors == null) {
/* 15673 */         selectors = null;
/*       */       } else {
/*       */         
/* 15676 */         selectors = new byte[SelectorCount * 4];
/* 15677 */         for (int i = 0; i < SelectorCount; i++) {
/* 15678 */           dc.writeI32(pSelectors[i], selectors, i * 4, swap);
/*       */         }
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/* 15684 */       if (pIntAttrs == null) {
/* 15685 */         intAttrs = null;
/*       */       } else {
/*       */         
/* 15688 */         intAttrs = new byte[IntAttrCount * 4];
/* 15689 */         for (int i = 0; i < IntAttrCount; i++) {
/* 15690 */           dc.writeI32(pIntAttrs[i], intAttrs, i * 4, swap);
/*       */         }
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/* 15696 */       if (filterElement == null) {
/* 15697 */         filterElementBuffer = null;
/*       */       } else {
/*       */         
/* 15700 */         int filterElementLength = filterElement.getRequiredBufferSize(ptrSize, nativeCp);
/* 15701 */         filterElementBuffer = new byte[filterElementLength];
/* 15702 */         filterElement.writeToBuffer(filterElementBuffer, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/* 15707 */       if (AttrErrors == null) {
/* 15708 */         AttrErrorsBuffer = null;
/*       */       } else {
/*       */         
/* 15711 */         AttrErrorsBuffer = new byte[AttrErrorsCount * 4];
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 15717 */       if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hconn)) {
/* 15718 */         localhconn.syncExec(new JmqiRunnable(this.env)
/*       */             {
/*       */               public void run()
/*       */               {
/* 15722 */                 if (Trace.isOn) {
/* 15723 */                   Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                 }
/*       */ 
/*       */                 
/*       */                 try {
/* 15728 */                   localhconn.enterCall();
/*       */                   
/* 15730 */                   boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 15731 */                   Native.spiDefine(isClassTraced, localhconn
/* 15732 */                       .getValue(), qmName, rxpbBuf, replace, objectSelectorBuffer, likeObjectNameBytes, SelectorCount, selectors, IntAttrCount, intAttrs, CharAttrLength, pCharAttrs, filterElementBuffer, AttrErrorsCount, AttrErrorsBuffer, pCompCode, pReason);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */                 
/*       */                 }
/* 15750 */                 catch (JmqiException e) {
/* 15751 */                   if (Trace.isOn) {
/* 15752 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                   }
/* 15754 */                   jTls.lastException = e;
/* 15755 */                   pCompCode.x = e.getCompCode();
/* 15756 */                   pReason.x = e.getReason();
/*       */                 }
/* 15758 */                 catch (UnsatisfiedLinkError e) {
/* 15759 */                   if (Trace.isOn) {
/* 15760 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                   }
/* 15762 */                   pCompCode.x = 2;
/* 15763 */                   pReason.x = 2298;
/*       */                 } finally {
/*       */                   
/* 15766 */                   if (Trace.isOn) {
/* 15767 */                     Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                   
/*       */                   try {
/* 15771 */                     localhconn.leaveCall();
/*       */                   }
/* 15773 */                   catch (JmqiException e) {
/* 15774 */                     if (Trace.isOn) {
/* 15775 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                     }
/* 15777 */                     jTls.lastException = e;
/* 15778 */                     pCompCode.x = e.getCompCode();
/* 15779 */                     pReason.x = e.getReason();
/*       */                   } 
/*       */                 } 
/* 15782 */                 if (Trace.isOn) {
/* 15783 */                   Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                 
/*       */                 }
/*       */               }
/*       */             });
/*       */       }
/*       */       else {
/*       */         
/* 15791 */         if (this.adapterIsRRS) {
/*       */           
/* 15793 */           localhconn.enterCall();
/* 15794 */           lockedLocalhconn = localhconn;
/*       */         } 
/* 15796 */         boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 15797 */         Native.spiDefine(isClassTraced, localhconn
/* 15798 */             .getValue(), qmName, rxpbBuf, replace, objectSelectorBuffer, likeObjectNameBytes, SelectorCount, selectors, IntAttrCount, intAttrs, CharAttrLength, pCharAttrs, filterElementBuffer, AttrErrorsCount, AttrErrorsBuffer, pCompCode, pReason);
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 15818 */       if (AttrErrors != null) {
/* 15819 */         for (int i = 0; i < AttrErrorsCount; i++) {
/* 15820 */           AttrErrors[i] = dc.readI32(AttrErrorsBuffer, i * 4, swap);
/*       */         }
/*       */       }
/*       */     }
/* 15824 */     catch (JmqiException e) {
/* 15825 */       if (Trace.isOn)
/*       */       {
/* 15827 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiDefine(Hconn,final boolean,LexObjectSelector,String,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final int,int [ ],final Pint,final Pint)", (Throwable)e, 1);
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 15833 */       jTls.lastException = e;
/* 15834 */       pCompCode.x = e.getCompCode();
/* 15835 */       pReason.x = e.getReason();
/*       */     }
/* 15837 */     catch (UnsatisfiedLinkError e) {
/* 15838 */       if (Trace.isOn)
/*       */       {
/* 15840 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiDefine(Hconn,final boolean,LexObjectSelector,String,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final int,int [ ],final Pint,final Pint)", e, 2);
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 15846 */       pCompCode.x = 2;
/* 15847 */       pReason.x = 2298;
/*       */     }
/* 15849 */     catch (Throwable e) {
/* 15850 */       if (Trace.isOn)
/*       */       {
/* 15852 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiDefine(Hconn,final boolean,LexObjectSelector,String,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final int,int [ ],final Pint,final Pint)", e, 3);
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 15858 */       castUnexpectedException("spiDefine(Hconn,final boolean,LexObjectSelector,String,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final int,int [ ],final Pint,final Pint)", 1, e);
/*       */     } finally {
/*       */       
/* 15861 */       if (Trace.isOn) {
/* 15862 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiDefine(Hconn,final boolean,LexObjectSelector,String,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final int,int [ ],final Pint,final Pint)");
/*       */       }
/*       */       
/* 15865 */       if (lockedLocalhconn != null) {
/*       */         
/*       */         try {
/* 15868 */           lockedLocalhconn.leaveCall();
/*       */         }
/* 15870 */         catch (JmqiException e) {
/* 15871 */           if (Trace.isOn)
/*       */           {
/* 15873 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiDefine(Hconn,final boolean,LexObjectSelector,String,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final int,int [ ],final Pint,final Pint)", (Throwable)e, 4);
/*       */           }
/*       */ 
/*       */ 
/*       */ 
/*       */           
/* 15879 */           jTls.lastException = e;
/* 15880 */           pCompCode.x = e.getCompCode();
/* 15881 */           pReason.x = e.getReason();
/*       */         } 
/*       */       }
/*       */     } 
/*       */ 
/*       */     
/* 15887 */     if (Trace.isOn) {
/* 15888 */       Trace.data(this, "spiDefine(Hconn,final boolean,LexObjectSelector,String,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final int,int [ ],final Pint,final Pint)", "__________");
/* 15889 */       Trace.data(this, "spiDefine(Hconn,final boolean,LexObjectSelector,String,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final int,int [ ],final Pint,final Pint)", "spiDefine <<");
/* 15890 */       Trace.data(this, "spiDefine(Hconn,final boolean,LexObjectSelector,String,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final int,int [ ],final Pint,final Pint)", "Hconn", hconn);
/* 15891 */       Trace.data(this, "spiDefine(Hconn,final boolean,LexObjectSelector,String,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final int,int [ ],final Pint,final Pint)", "replace", Boolean.valueOf(replace));
/* 15892 */       Trace.data(this, "spiDefine(Hconn,final boolean,LexObjectSelector,String,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final int,int [ ],final Pint,final Pint)", "objectSelector", objectSelector);
/* 15893 */       Trace.data(this, "spiDefine(Hconn,final boolean,LexObjectSelector,String,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final int,int [ ],final Pint,final Pint)", "likeObject", likeObjectName);
/* 15894 */       Trace.data(this, "spiDefine(Hconn,final boolean,LexObjectSelector,String,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final int,int [ ],final Pint,final Pint)", "SelectorCount", Integer.toString(SelectorCount));
/* 15895 */       Trace.data(this, "spiDefine(Hconn,final boolean,LexObjectSelector,String,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final int,int [ ],final Pint,final Pint)", "Selectors", pSelectors);
/* 15896 */       Trace.data(this, "spiDefine(Hconn,final boolean,LexObjectSelector,String,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final int,int [ ],final Pint,final Pint)", "IntattrCount", Integer.toString(IntAttrCount));
/* 15897 */       Trace.data(this, "spiDefine(Hconn,final boolean,LexObjectSelector,String,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final int,int [ ],final Pint,final Pint)", "Intattrs", pIntAttrs);
/* 15898 */       Trace.data(this, "spiDefine(Hconn,final boolean,LexObjectSelector,String,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final int,int [ ],final Pint,final Pint)", "CharAttrLength", Integer.toString(CharAttrLength));
/* 15899 */       Trace.data(this, "spiDefine(Hconn,final boolean,LexObjectSelector,String,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final int,int [ ],final Pint,final Pint)", "CharAttrs", pCharAttrs);
/* 15900 */       Trace.data(this, "spiDefine(Hconn,final boolean,LexObjectSelector,String,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final int,int [ ],final Pint,final Pint)", "filterElement", filterElement);
/* 15901 */       Trace.data(this, "spiDefine(Hconn,final boolean,LexObjectSelector,String,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final int,int [ ],final Pint,final Pint)", "AttrErrors", AttrErrors);
/* 15902 */       Trace.data(this, "spiDefine(Hconn,final boolean,LexObjectSelector,String,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final int,int [ ],final Pint,final Pint)", "CompCode", pCompCode);
/* 15903 */       Trace.data(this, "spiDefine(Hconn,final boolean,LexObjectSelector,String,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final int,int [ ],final Pint,final Pint)", "Reason", pReason);
/* 15904 */       Trace.data(this, "spiDefine(Hconn,final boolean,LexObjectSelector,String,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final int,int [ ],final Pint,final Pint)", "__________");
/*       */     } 
/* 15906 */     if (Trace.isOn) {
/* 15907 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiDefine(Hconn,final boolean,LexObjectSelector,String,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final int,int [ ],final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void spiInquire(Hconn hconn, LexObjectSelector objectSelector, final int SelectorCount, int[] pSelectors, final int IntAttrCount, int[] pIntAttrs, final int CharAttrLength, final byte[] pCharAttrs, LexFilterElement filterElement, final Pint pCompCode, final Pint pReason) {
/* 15939 */     if (Trace.isOn) {
/* 15940 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiInquire(Hconn,LexObjectSelector,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final Pint,final Pint)", new Object[] { hconn, objectSelector, 
/*       */             
/* 15942 */             Integer.valueOf(SelectorCount), pSelectors, 
/* 15943 */             Integer.valueOf(IntAttrCount), pIntAttrs, Integer.valueOf(CharAttrLength), pCharAttrs, filterElement, pCompCode, pReason });
/*       */     }
/*       */     
/* 15946 */     String fid = "spiInquire(Hconn,LexObjectSelector,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final Pint,final Pint)";
/*       */ 
/*       */     
/* 15949 */     if (Trace.isOn) {
/* 15950 */       Trace.data(this, "spiInquire(Hconn,LexObjectSelector,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final Pint,final Pint)", "__________");
/* 15951 */       Trace.data(this, "spiInquire(Hconn,LexObjectSelector,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final Pint,final Pint)", "spiInquire >>");
/* 15952 */       Trace.data(this, "spiInquire(Hconn,LexObjectSelector,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final Pint,final Pint)", "Hconn", hconn);
/* 15953 */       Trace.data(this, "spiInquire(Hconn,LexObjectSelector,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final Pint,final Pint)", "objectSelector", objectSelector);
/* 15954 */       Trace.data(this, "spiInquire(Hconn,LexObjectSelector,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final Pint,final Pint)", "SelectorCount", Integer.toString(SelectorCount));
/* 15955 */       Trace.data(this, "spiInquire(Hconn,LexObjectSelector,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final Pint,final Pint)", "Selectors", pSelectors);
/* 15956 */       Trace.data(this, "spiInquire(Hconn,LexObjectSelector,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final Pint,final Pint)", "IntAttrCount", Integer.toString(IntAttrCount));
/* 15957 */       Trace.data(this, "spiInquire(Hconn,LexObjectSelector,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final Pint,final Pint)", "IntAttrs", pIntAttrs);
/* 15958 */       Trace.data(this, "spiInquire(Hconn,LexObjectSelector,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final Pint,final Pint)", "CharAttrLength", Integer.toString(CharAttrLength));
/* 15959 */       Trace.data(this, "spiInquire(Hconn,LexObjectSelector,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final Pint,final Pint)", "CharAttrs", pCharAttrs);
/* 15960 */       Trace.data(this, "spiInquire(Hconn,LexObjectSelector,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final Pint,final Pint)", "CompCode", pCompCode);
/* 15961 */       Trace.data(this, "spiInquire(Hconn,LexObjectSelector,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final Pint,final Pint)", "Reason", pReason);
/* 15962 */       Trace.data(this, "spiInquire(Hconn,LexObjectSelector,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final Pint,final Pint)", "__________");
/*       */     } 
/*       */     
/* 15965 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 15966 */     final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/* 15967 */     LocalHconn lockedLocalhconn = null;
/* 15968 */     JmqiDC dc = this.sysenv.getDC(); try {
/*       */       final byte[] rxpbBuf, objectSelectorBuffer, selectors, intAttrs, filterElementBuffer;
/* 15970 */       final LocalHconn localhconn = getLocalHconn(hconn);
/* 15971 */       final byte[] qmName = localhconn.getQMNameBytes();
/*       */       
/* 15973 */       RXPB rxpb = null;
/*       */       
/* 15975 */       if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/* 15976 */         rxpbBuf = null;
/*       */       } else {
/*       */         
/* 15979 */         rxpb = localhconn.getUpdatedRxpb();
/* 15980 */         int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/* 15981 */         if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/* 15982 */           tls.rxpbBuf = new byte[rxpbLen];
/*       */         }
/* 15984 */         rxpbBuf = tls.rxpbBuf;
/* 15985 */         rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/* 15990 */       if (objectSelector == null) {
/* 15991 */         objectSelectorBuffer = null;
/*       */       } else {
/*       */         
/* 15994 */         int objectSelectorLength = objectSelector.getRequiredBufferSize(ptrSize, nativeCp);
/* 15995 */         objectSelectorBuffer = new byte[objectSelectorLength];
/* 15996 */         objectSelector.writeToBuffer(objectSelectorBuffer, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/* 16001 */       if (pSelectors == null) {
/* 16002 */         selectors = null;
/*       */       } else {
/*       */         
/* 16005 */         selectors = new byte[SelectorCount * 4];
/* 16006 */         for (int i = 0; i < SelectorCount; i++) {
/* 16007 */           dc.writeI32(pSelectors[i], selectors, i * 4, swap);
/*       */         }
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/* 16013 */       if (pIntAttrs == null) {
/* 16014 */         intAttrs = null;
/*       */       } else {
/*       */         
/* 16017 */         intAttrs = new byte[IntAttrCount * 4];
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/* 16022 */       if (filterElement == null) {
/* 16023 */         filterElementBuffer = null;
/*       */       } else {
/*       */         
/* 16026 */         int filterElementLength = filterElement.getRequiredBufferSize(ptrSize, nativeCp);
/* 16027 */         filterElementBuffer = new byte[filterElementLength];
/* 16028 */         filterElement.writeToBuffer(filterElementBuffer, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/* 16033 */       if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hconn)) {
/* 16034 */         localhconn.syncExec(new JmqiRunnable(this.env)
/*       */             {
/*       */               public void run()
/*       */               {
/* 16038 */                 if (Trace.isOn) {
/* 16039 */                   Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                 }
/*       */ 
/*       */                 
/*       */                 try {
/* 16044 */                   localhconn.enterCall();
/*       */                   
/* 16046 */                   boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 16047 */                   Native.spiInquire(isClassTraced, localhconn
/* 16048 */                       .getValue(), qmName, rxpbBuf, objectSelectorBuffer, SelectorCount, selectors, IntAttrCount, intAttrs, CharAttrLength, pCharAttrs, filterElementBuffer, pCompCode, pReason);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */                 
/*       */                 }
/* 16062 */                 catch (JmqiException e) {
/* 16063 */                   if (Trace.isOn) {
/* 16064 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                   }
/*       */                   
/* 16067 */                   jTls.lastException = e;
/* 16068 */                   pCompCode.x = e.getCompCode();
/* 16069 */                   pReason.x = e.getReason();
/*       */                 }
/* 16071 */                 catch (UnsatisfiedLinkError e) {
/* 16072 */                   if (Trace.isOn) {
/* 16073 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                   }
/* 16075 */                   pCompCode.x = 2;
/* 16076 */                   pReason.x = 2298;
/*       */                 } finally {
/*       */                   
/* 16079 */                   if (Trace.isOn) {
/* 16080 */                     Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                   
/*       */                   try {
/* 16084 */                     localhconn.leaveCall();
/*       */                   }
/* 16086 */                   catch (JmqiException e) {
/* 16087 */                     if (Trace.isOn) {
/* 16088 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                     }
/* 16090 */                     jTls.lastException = e;
/* 16091 */                     pCompCode.x = e.getCompCode();
/* 16092 */                     pReason.x = e.getReason();
/*       */                   } 
/*       */                 } 
/* 16095 */                 if (Trace.isOn) {
/* 16096 */                   Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                 
/*       */                 }
/*       */               }
/*       */             });
/*       */       }
/*       */       else {
/*       */         
/* 16104 */         if (this.adapterIsRRS) {
/*       */           
/* 16106 */           localhconn.enterCall();
/* 16107 */           lockedLocalhconn = localhconn;
/*       */         } 
/* 16109 */         boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 16110 */         Native.spiInquire(isClassTraced, localhconn
/* 16111 */             .getValue(), qmName, rxpbBuf, objectSelectorBuffer, SelectorCount, selectors, IntAttrCount, intAttrs, CharAttrLength, pCharAttrs, filterElementBuffer, pCompCode, pReason);
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 16127 */       if (pIntAttrs != null) {
/* 16128 */         for (int i = 0; i < IntAttrCount; i++) {
/* 16129 */           pIntAttrs[i] = dc.readI32(intAttrs, i * 4, swap);
/*       */         }
/*       */       }
/*       */     }
/* 16133 */     catch (JmqiException e) {
/* 16134 */       if (Trace.isOn) {
/* 16135 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiInquire(Hconn,LexObjectSelector,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final Pint,final Pint)", (Throwable)e, 1);
/*       */       }
/*       */ 
/*       */       
/* 16139 */       jTls.lastException = e;
/* 16140 */       pCompCode.x = e.getCompCode();
/* 16141 */       pReason.x = e.getReason();
/*       */     }
/* 16143 */     catch (UnsatisfiedLinkError e) {
/* 16144 */       if (Trace.isOn) {
/* 16145 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiInquire(Hconn,LexObjectSelector,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final Pint,final Pint)", e, 2);
/*       */       }
/*       */ 
/*       */       
/* 16149 */       pCompCode.x = 2;
/* 16150 */       pReason.x = 2298;
/*       */     }
/* 16152 */     catch (Throwable e) {
/* 16153 */       if (Trace.isOn) {
/* 16154 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiInquire(Hconn,LexObjectSelector,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final Pint,final Pint)", e, 3);
/*       */       }
/*       */ 
/*       */       
/* 16158 */       castUnexpectedException("spiInquire(Hconn,LexObjectSelector,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final Pint,final Pint)", 1, e);
/*       */     } finally {
/*       */       
/* 16161 */       if (Trace.isOn) {
/* 16162 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiInquire(Hconn,LexObjectSelector,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final Pint,final Pint)");
/*       */       }
/*       */       
/* 16165 */       if (lockedLocalhconn != null) {
/*       */         
/*       */         try {
/* 16168 */           lockedLocalhconn.leaveCall();
/*       */         }
/* 16170 */         catch (JmqiException e) {
/* 16171 */           if (Trace.isOn) {
/* 16172 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiInquire(Hconn,LexObjectSelector,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final Pint,final Pint)", (Throwable)e, 4);
/*       */           }
/*       */ 
/*       */           
/* 16176 */           jTls.lastException = e;
/* 16177 */           pCompCode.x = e.getCompCode();
/* 16178 */           pReason.x = e.getReason();
/*       */         } 
/*       */       }
/*       */     } 
/*       */ 
/*       */     
/* 16184 */     if (Trace.isOn) {
/* 16185 */       Trace.data(this, "spiInquire(Hconn,LexObjectSelector,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final Pint,final Pint)", "__________");
/* 16186 */       Trace.data(this, "spiInquire(Hconn,LexObjectSelector,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final Pint,final Pint)", "spiInquire <<");
/* 16187 */       Trace.data(this, "spiInquire(Hconn,LexObjectSelector,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final Pint,final Pint)", "Hconn", hconn);
/* 16188 */       Trace.data(this, "spiInquire(Hconn,LexObjectSelector,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final Pint,final Pint)", "objectSelector", objectSelector);
/* 16189 */       Trace.data(this, "spiInquire(Hconn,LexObjectSelector,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final Pint,final Pint)", "SelectorCount", Integer.toString(SelectorCount));
/* 16190 */       Trace.data(this, "spiInquire(Hconn,LexObjectSelector,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final Pint,final Pint)", "Selectors", pSelectors);
/* 16191 */       Trace.data(this, "spiInquire(Hconn,LexObjectSelector,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final Pint,final Pint)", "IntattrCount", Integer.toString(IntAttrCount));
/* 16192 */       Trace.data(this, "spiInquire(Hconn,LexObjectSelector,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final Pint,final Pint)", "IntAttrs", pIntAttrs);
/* 16193 */       Trace.data(this, "spiInquire(Hconn,LexObjectSelector,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final Pint,final Pint)", "CharAttrLength", Integer.toString(CharAttrLength));
/* 16194 */       Trace.data(this, "spiInquire(Hconn,LexObjectSelector,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final Pint,final Pint)", "CharAttrs", pCharAttrs);
/* 16195 */       Trace.data(this, "spiInquire(Hconn,LexObjectSelector,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final Pint,final Pint)", "CompCode", pCompCode);
/* 16196 */       Trace.data(this, "spiInquire(Hconn,LexObjectSelector,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final Pint,final Pint)", "Reason", pReason);
/* 16197 */       Trace.data(this, "spiInquire(Hconn,LexObjectSelector,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final Pint,final Pint)", "__________");
/*       */     } 
/* 16199 */     if (Trace.isOn) {
/* 16200 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiInquire(Hconn,LexObjectSelector,final int,int [ ],final int,int [ ],final int,final byte [ ],LexFilterElement,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void spiDelete(Hconn hconn, LexObjectSelector objectSelector, final boolean force, final Pint pCompCode, final Pint pReason) {
/* 16215 */     if (Trace.isOn) {
/* 16216 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiDelete(Hconn,LexObjectSelector,final boolean,final Pint,final Pint)", new Object[] { hconn, objectSelector, 
/*       */             
/* 16218 */             Boolean.valueOf(force), pCompCode, pReason });
/*       */     }
/* 16220 */     String fid = "spiDelete(Hconn,LexObjectSelector,final boolean,final Pint,final Pint)";
/*       */ 
/*       */     
/* 16223 */     if (Trace.isOn) {
/* 16224 */       Trace.data(this, "spiDelete(Hconn,LexObjectSelector,final boolean,final Pint,final Pint)", "__________");
/* 16225 */       Trace.data(this, "spiDelete(Hconn,LexObjectSelector,final boolean,final Pint,final Pint)", "spiDelete >>");
/* 16226 */       Trace.data(this, "spiDelete(Hconn,LexObjectSelector,final boolean,final Pint,final Pint)", "Hconn", hconn);
/* 16227 */       Trace.data(this, "spiDelete(Hconn,LexObjectSelector,final boolean,final Pint,final Pint)", "objectSelector", objectSelector);
/* 16228 */       Trace.data(this, "spiDelete(Hconn,LexObjectSelector,final boolean,final Pint,final Pint)", "force", Boolean.valueOf(force));
/* 16229 */       Trace.data(this, "spiDelete(Hconn,LexObjectSelector,final boolean,final Pint,final Pint)", "CompCode", pCompCode);
/* 16230 */       Trace.data(this, "spiDelete(Hconn,LexObjectSelector,final boolean,final Pint,final Pint)", "Reason", pReason);
/* 16231 */       Trace.data(this, "spiDelete(Hconn,LexObjectSelector,final boolean,final Pint,final Pint)", "__________");
/*       */     } 
/*       */     
/* 16234 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 16235 */     final JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/* 16236 */     LocalHconn lockedLocalhconn = null; try {
/*       */       final byte[] rxpbBuf, objectSelectorBuffer;
/* 16238 */       final LocalHconn localhconn = getLocalHconn(hconn);
/* 16239 */       final byte[] qmName = localhconn.getQMNameBytes();
/*       */       
/* 16241 */       RXPB rxpb = null;
/*       */       
/* 16243 */       if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/* 16244 */         rxpbBuf = null;
/*       */       } else {
/*       */         
/* 16247 */         rxpb = localhconn.getUpdatedRxpb();
/* 16248 */         int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/* 16249 */         if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/* 16250 */           tls.rxpbBuf = new byte[rxpbLen];
/*       */         }
/* 16252 */         rxpbBuf = tls.rxpbBuf;
/* 16253 */         rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/* 16258 */       if (objectSelector == null) {
/* 16259 */         objectSelectorBuffer = null;
/*       */       } else {
/*       */         
/* 16262 */         int objectSelectorLength = objectSelector.getRequiredBufferSize(ptrSize, nativeCp);
/* 16263 */         objectSelectorBuffer = new byte[objectSelectorLength];
/* 16264 */         objectSelector.writeToBuffer(objectSelectorBuffer, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 16270 */       if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hconn)) {
/* 16271 */         localhconn.syncExec(new JmqiRunnable(this.env)
/*       */             {
/*       */               public void run()
/*       */               {
/* 16275 */                 if (Trace.isOn) {
/* 16276 */                   Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                 }
/*       */ 
/*       */                 
/*       */                 try {
/* 16281 */                   localhconn.enterCall();
/*       */                   
/* 16283 */                   boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 16284 */                   Native.spiDelete(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, objectSelectorBuffer, force, pCompCode, pReason);
/*       */                 }
/* 16286 */                 catch (JmqiException e) {
/* 16287 */                   if (Trace.isOn) {
/* 16288 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                   }
/*       */                   
/* 16291 */                   jTls.lastException = e;
/* 16292 */                   pCompCode.x = e.getCompCode();
/* 16293 */                   pReason.x = e.getReason();
/*       */                 }
/* 16295 */                 catch (UnsatisfiedLinkError e) {
/* 16296 */                   if (Trace.isOn) {
/* 16297 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                   }
/* 16299 */                   pCompCode.x = 2;
/* 16300 */                   pReason.x = 2298;
/*       */                 } finally {
/*       */                   
/* 16303 */                   if (Trace.isOn) {
/* 16304 */                     Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                   
/*       */                   try {
/* 16308 */                     localhconn.leaveCall();
/*       */                   }
/* 16310 */                   catch (JmqiException e) {
/* 16311 */                     if (Trace.isOn) {
/* 16312 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                     }
/* 16314 */                     jTls.lastException = e;
/* 16315 */                     pCompCode.x = e.getCompCode();
/* 16316 */                     pReason.x = e.getReason();
/*       */                   } 
/*       */                 } 
/* 16319 */                 if (Trace.isOn) {
/* 16320 */                   Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                 
/*       */                 }
/*       */               }
/*       */             });
/*       */       }
/*       */       else {
/*       */         
/* 16328 */         if (this.adapterIsRRS) {
/*       */           
/* 16330 */           localhconn.enterCall();
/* 16331 */           lockedLocalhconn = localhconn;
/*       */         } 
/* 16333 */         boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 16334 */         Native.spiDelete(isClassTraced, localhconn.getValue(), qmName, rxpbBuf, objectSelectorBuffer, force, pCompCode, pReason);
/*       */       }
/*       */     
/* 16337 */     } catch (JmqiException e) {
/* 16338 */       if (Trace.isOn) {
/* 16339 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiDelete(Hconn,LexObjectSelector,final boolean,final Pint,final Pint)", (Throwable)e, 1);
/*       */       }
/*       */       
/* 16342 */       jTls.lastException = e;
/* 16343 */       pCompCode.x = e.getCompCode();
/* 16344 */       pReason.x = e.getReason();
/*       */     }
/* 16346 */     catch (UnsatisfiedLinkError e) {
/* 16347 */       if (Trace.isOn) {
/* 16348 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiDelete(Hconn,LexObjectSelector,final boolean,final Pint,final Pint)", e, 2);
/*       */       }
/*       */       
/* 16351 */       pCompCode.x = 2;
/* 16352 */       pReason.x = 2298;
/*       */     }
/* 16354 */     catch (Throwable e) {
/* 16355 */       if (Trace.isOn) {
/* 16356 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiDelete(Hconn,LexObjectSelector,final boolean,final Pint,final Pint)", e, 3);
/*       */       }
/*       */       
/* 16359 */       castUnexpectedException("spiDelete(Hconn,LexObjectSelector,final boolean,final Pint,final Pint)", 1, e);
/*       */     } finally {
/*       */       
/* 16362 */       if (Trace.isOn) {
/* 16363 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiDelete(Hconn,LexObjectSelector,final boolean,final Pint,final Pint)");
/*       */       }
/*       */       
/* 16366 */       if (lockedLocalhconn != null) {
/*       */         
/*       */         try {
/* 16369 */           lockedLocalhconn.leaveCall();
/*       */         }
/* 16371 */         catch (JmqiException e) {
/* 16372 */           if (Trace.isOn) {
/* 16373 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiDelete(Hconn,LexObjectSelector,final boolean,final Pint,final Pint)", (Throwable)e, 4);
/*       */           }
/*       */           
/* 16376 */           jTls.lastException = e;
/* 16377 */           pCompCode.x = e.getCompCode();
/* 16378 */           pReason.x = e.getReason();
/*       */         } 
/*       */       }
/*       */     } 
/*       */ 
/*       */     
/* 16384 */     if (Trace.isOn) {
/* 16385 */       Trace.data(this, "spiDelete(Hconn,LexObjectSelector,final boolean,final Pint,final Pint)", "__________");
/* 16386 */       Trace.data(this, "spiDelete(Hconn,LexObjectSelector,final boolean,final Pint,final Pint)", "spiDelete <<");
/* 16387 */       Trace.data(this, "spiDelete(Hconn,LexObjectSelector,final boolean,final Pint,final Pint)", "Hconn", hconn);
/* 16388 */       Trace.data(this, "spiDelete(Hconn,LexObjectSelector,final boolean,final Pint,final Pint)", "objectSelector", objectSelector);
/* 16389 */       Trace.data(this, "spiDelete(Hconn,LexObjectSelector,final boolean,final Pint,final Pint)", "force", Boolean.valueOf(force));
/* 16390 */       Trace.data(this, "spiDelete(Hconn,LexObjectSelector,final boolean,final Pint,final Pint)", "CompCode", pCompCode);
/* 16391 */       Trace.data(this, "spiDelete(Hconn,LexObjectSelector,final boolean,final Pint,final Pint)", "Reason", pReason);
/* 16392 */       Trace.data(this, "spiDelete(Hconn,LexObjectSelector,final boolean,final Pint,final Pint)", "__________");
/*       */     } 
/* 16394 */     if (Trace.isOn) {
/* 16395 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "spiDelete(Hconn,LexObjectSelector,final boolean,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void lpiSPISubscriptionRequest(Hconn hconn, Hobj hsub, final int action, LpiSRD lpiSRD, MQSRO subRqOpts, final Pint pCompCode, final Pint pReason) {
/* 16419 */     if (Trace.isOn) {
/* 16420 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "lpiSPISubscriptionRequest(Hconn,Hobj,final int,LpiSRD,MQSRO,final Pint,final Pint)", new Object[] { hconn, hsub, 
/*       */             
/* 16422 */             Integer.valueOf(action), lpiSRD, subRqOpts, pCompCode, pReason });
/*       */     }
/* 16424 */     String fid = "lpiSPISubscriptionRequest(Hconn,Hobj,final int,LpiSRD,MQSRO,final Pint,final Pint)";
/*       */ 
/*       */     
/* 16427 */     if (Trace.isOn) {
/* 16428 */       Trace.data(this, "lpiSPISubscriptionRequest(Hconn,Hobj,final int,LpiSRD,MQSRO,final Pint,final Pint)", "__________");
/* 16429 */       Trace.data(this, "lpiSPISubscriptionRequest(Hconn,Hobj,final int,LpiSRD,MQSRO,final Pint,final Pint)", "lpiSPISubscriptionRequest >>");
/* 16430 */       Trace.data(this, "lpiSPISubscriptionRequest(Hconn,Hobj,final int,LpiSRD,MQSRO,final Pint,final Pint)", "Hconn", hconn);
/* 16431 */       Trace.data(this, "lpiSPISubscriptionRequest(Hconn,Hobj,final int,LpiSRD,MQSRO,final Pint,final Pint)", "Hsub", hsub);
/* 16432 */       Trace.data(this, "lpiSPISubscriptionRequest(Hconn,Hobj,final int,LpiSRD,MQSRO,final Pint,final Pint)", "action", Integer.valueOf(action));
/* 16433 */       Trace.data(this, "lpiSPISubscriptionRequest(Hconn,Hobj,final int,LpiSRD,MQSRO,final Pint,final Pint)", "LpiSRD", lpiSRD);
/* 16434 */       Trace.data(this, "lpiSPISubscriptionRequest(Hconn,Hobj,final int,LpiSRD,MQSRO,final Pint,final Pint)", "MQSRO", subRqOpts);
/* 16435 */       Trace.data(this, "lpiSPISubscriptionRequest(Hconn,Hobj,final int,LpiSRD,MQSRO,final Pint,final Pint)", "CompCode", pCompCode);
/* 16436 */       Trace.data(this, "lpiSPISubscriptionRequest(Hconn,Hobj,final int,LpiSRD,MQSRO,final Pint,final Pint)", "Reason", pReason);
/* 16437 */       Trace.data(this, "lpiSPISubscriptionRequest(Hconn,Hobj,final int,LpiSRD,MQSRO,final Pint,final Pint)", "__________");
/*       */     } 
/*       */     
/* 16440 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 16441 */     JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/* 16442 */     LocalHconn lockedLocalhconn = null; try {
/*       */       final byte[] rxpbBuf, lpiSRDBuf, optBuf;
/* 16444 */       final LocalHconn localhconn = getLocalHconn(hconn);
/* 16445 */       final LocalHobj localhsub = LocalHobj.getLocalHobj(this.env, hsub);
/* 16446 */       final byte[] qmName = localhconn.getQMNameBytes();
/*       */       
/* 16448 */       RXPB rxpb = null;
/*       */       
/* 16450 */       if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/* 16451 */         rxpbBuf = null;
/*       */       } else {
/*       */         
/* 16454 */         rxpb = localhconn.getUpdatedRxpb();
/* 16455 */         int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/* 16456 */         if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/* 16457 */           tls.rxpbBuf = new byte[rxpbLen];
/*       */         }
/* 16459 */         rxpbBuf = tls.rxpbBuf;
/* 16460 */         rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/* 16465 */       if (lpiSRD == null) {
/* 16466 */         lpiSRDBuf = null;
/*       */       } else {
/*       */         
/* 16469 */         int lpiSRDLen = lpiSRD.getRequiredBufferSize(ptrSize, nativeCp);
/* 16470 */         if (tls.lpiOptBuf == null || tls.lpiOptBuf.length < lpiSRDLen) {
/* 16471 */           tls.lpiOptBuf = new byte[lpiSRDLen];
/*       */         }
/* 16473 */         lpiSRDBuf = tls.lpiOptBuf;
/* 16474 */         lpiSRD.writeToBuffer(lpiSRDBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */ 
/*       */       
/* 16479 */       if (subRqOpts == null) {
/* 16480 */         optBuf = null;
/*       */       } else {
/*       */         
/* 16483 */         int sroLen = subRqOpts.getRequiredBufferSize(ptrSize, nativeCp);
/* 16484 */         if (tls.optBuf == null || tls.optBuf.length < sroLen) {
/* 16485 */           tls.optBuf = new byte[sroLen];
/*       */         }
/* 16487 */         optBuf = tls.optBuf;
/* 16488 */         subRqOpts.writeToBuffer(optBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 16494 */       if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hconn)) {
/* 16495 */         localhconn.syncExec(new JmqiRunnable(this.env)
/*       */             {
/*       */               public void run()
/*       */               {
/* 16499 */                 if (Trace.isOn) {
/* 16500 */                   Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                 }
/*       */ 
/*       */                 
/*       */                 try {
/* 16505 */                   localhconn.enterCall();
/*       */                   
/* 16507 */                   boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 16508 */                   Native.lpiSPISubscriptionRequest(isClassTraced, localhconn
/* 16509 */                       .getValue(), qmName, rxpbBuf, localhsub, action, lpiSRDBuf, optBuf, pCompCode, pReason);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */                 
/*       */                 }
/* 16519 */                 catch (JmqiException e) {
/* 16520 */                   if (Trace.isOn) {
/* 16521 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                   }
/*       */                   
/* 16524 */                   pCompCode.x = e.getCompCode();
/* 16525 */                   pReason.x = e.getReason();
/*       */                 }
/* 16527 */                 catch (UnsatisfiedLinkError e) {
/* 16528 */                   if (Trace.isOn) {
/* 16529 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                   }
/* 16531 */                   pCompCode.x = 2;
/* 16532 */                   pReason.x = 2298;
/*       */                 } finally {
/*       */                   
/* 16535 */                   if (Trace.isOn) {
/* 16536 */                     Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                   
/*       */                   try {
/* 16540 */                     localhconn.leaveCall();
/*       */                   }
/* 16542 */                   catch (JmqiException e) {
/* 16543 */                     if (Trace.isOn) {
/* 16544 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                     }
/* 16546 */                     pCompCode.x = e.getCompCode();
/* 16547 */                     pReason.x = e.getReason();
/*       */                   } 
/*       */                 } 
/* 16550 */                 if (Trace.isOn) {
/* 16551 */                   Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                 }
/*       */               }
/*       */             });
/*       */       }
/*       */       else {
/*       */         
/* 16558 */         if (this.adapterIsRRS) {
/*       */           
/* 16560 */           localhconn.enterCall();
/* 16561 */           lockedLocalhconn = localhconn;
/*       */         } 
/* 16563 */         boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 16564 */         Native.lpiSPISubscriptionRequest(isClassTraced, localhconn
/* 16565 */             .getValue(), qmName, rxpbBuf, localhsub, action, lpiSRDBuf, optBuf, pCompCode, pReason);
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 16577 */       if (lpiSRD != null) {
/* 16578 */         lpiSRD.readFromBuffer(lpiSRDBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       }
/* 16580 */       if (subRqOpts != null) {
/* 16581 */         subRqOpts.readFromBuffer(optBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       }
/*       */     }
/* 16584 */     catch (JmqiException e) {
/* 16585 */       if (Trace.isOn) {
/* 16586 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "lpiSPISubscriptionRequest(Hconn,Hobj,final int,LpiSRD,MQSRO,final Pint,final Pint)", (Throwable)e, 1);
/*       */       }
/*       */ 
/*       */       
/* 16590 */       pCompCode.x = e.getCompCode();
/* 16591 */       pReason.x = e.getReason();
/*       */     }
/* 16593 */     catch (UnsatisfiedLinkError e) {
/* 16594 */       if (Trace.isOn) {
/* 16595 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "lpiSPISubscriptionRequest(Hconn,Hobj,final int,LpiSRD,MQSRO,final Pint,final Pint)", e, 2);
/*       */       }
/*       */ 
/*       */       
/* 16599 */       pCompCode.x = 2;
/* 16600 */       pReason.x = 2298;
/*       */     }
/* 16602 */     catch (Throwable e) {
/* 16603 */       if (Trace.isOn) {
/* 16604 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "lpiSPISubscriptionRequest(Hconn,Hobj,final int,LpiSRD,MQSRO,final Pint,final Pint)", e, 3);
/*       */       
/*       */       }
/*       */     
/*       */     }
/*       */     finally {
/*       */       
/* 16611 */       if (Trace.isOn) {
/* 16612 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "lpiSPISubscriptionRequest(Hconn,Hobj,final int,LpiSRD,MQSRO,final Pint,final Pint)");
/*       */       }
/*       */       
/* 16615 */       if (lockedLocalhconn != null) {
/*       */         
/*       */         try {
/* 16618 */           lockedLocalhconn.leaveCall();
/*       */         }
/* 16620 */         catch (JmqiException e) {
/* 16621 */           if (Trace.isOn) {
/* 16622 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "lpiSPISubscriptionRequest(Hconn,Hobj,final int,LpiSRD,MQSRO,final Pint,final Pint)", (Throwable)e, 4);
/*       */           }
/*       */ 
/*       */           
/* 16626 */           jTls.lastException = e;
/* 16627 */           pCompCode.x = e.getCompCode();
/* 16628 */           pReason.x = e.getReason();
/*       */         } 
/*       */       }
/*       */     } 
/*       */ 
/*       */     
/* 16634 */     if (Trace.isOn) {
/* 16635 */       Trace.data(this, "lpiSPISubscriptionRequest(Hconn,Hobj,final int,LpiSRD,MQSRO,final Pint,final Pint)", "__________");
/* 16636 */       Trace.data(this, "lpiSPISubscriptionRequest(Hconn,Hobj,final int,LpiSRD,MQSRO,final Pint,final Pint)", "lpiSPISubscriptionRequest <<");
/* 16637 */       Trace.data(this, "lpiSPISubscriptionRequest(Hconn,Hobj,final int,LpiSRD,MQSRO,final Pint,final Pint)", "Hconn", hconn);
/* 16638 */       Trace.data(this, "lpiSPISubscriptionRequest(Hconn,Hobj,final int,LpiSRD,MQSRO,final Pint,final Pint)", "Hsub", hsub);
/* 16639 */       Trace.data(this, "lpiSPISubscriptionRequest(Hconn,Hobj,final int,LpiSRD,MQSRO,final Pint,final Pint)", "action", Integer.valueOf(action));
/* 16640 */       Trace.data(this, "lpiSPISubscriptionRequest(Hconn,Hobj,final int,LpiSRD,MQSRO,final Pint,final Pint)", "LpiSRD", lpiSRD);
/* 16641 */       Trace.data(this, "lpiSPISubscriptionRequest(Hconn,Hobj,final int,LpiSRD,MQSRO,final Pint,final Pint)", "MQSRO", subRqOpts);
/* 16642 */       Trace.data(this, "lpiSPISubscriptionRequest(Hconn,Hobj,final int,LpiSRD,MQSRO,final Pint,final Pint)", "CompCode", pCompCode);
/* 16643 */       Trace.data(this, "lpiSPISubscriptionRequest(Hconn,Hobj,final int,LpiSRD,MQSRO,final Pint,final Pint)", "Reason", pReason);
/* 16644 */       Trace.data(this, "lpiSPISubscriptionRequest(Hconn,Hobj,final int,LpiSRD,MQSRO,final Pint,final Pint)", "__________");
/*       */     } 
/* 16646 */     if (Trace.isOn) {
/* 16647 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "lpiSPISubscriptionRequest(Hconn,Hobj,final int,LpiSRD,MQSRO,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public List<byte[]> jmqiInquireNamedSubscribers(Hconn hconn, LpiCALLOPT callOptions, String subName, Pint pCompCode, Pint pReason) {
/* 16668 */     if (Trace.isOn) {
/* 16669 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "jmqiInquireNamedSubscribers(Hconn,LpiCALLOPT,String,final Pint,final Pint)", new Object[] { hconn, callOptions, subName, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */ 
/*       */     
/* 16674 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 16675 */     JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/*       */     
/* 16677 */     List<byte[]> traceRet1 = JmqiTools.jmqiInquireNamedSubscribers(this.env, this, jTls, hconn, callOptions, subName, pCompCode, pReason);
/* 16678 */     if (Trace.isOn) {
/* 16679 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "jmqiInquireNamedSubscribers(Hconn,LpiCALLOPT,String,final Pint,final Pint)", traceRet1);
/*       */     }
/*       */     
/* 16682 */     return traceRet1;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void lpiSPIInquireNamedSubscribers(Hconn hconn, final byte[] callOptionsBuffer, int callOptionsBufferLength, final byte[] subNameBuffer, final int subNameBufferLength, final byte[] subIdBuffer, final Pint subIdBufferLength, final Pint subscribersReturned, final Pint pCompCode, final Pint pReason) {
/* 16709 */     if (Trace.isOn) {
/* 16710 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "lpiSPIInquireNamedSubscribers(Hconn,final byte [ ],int,final byte [ ],final int,final byte [ ],final Pint,final Pint,final Pint,final Pint)", new Object[] { hconn, callOptionsBuffer, 
/*       */             
/* 16712 */             Integer.valueOf(callOptionsBufferLength), subNameBuffer, 
/* 16713 */             Integer.valueOf(subNameBufferLength), subIdBuffer, subIdBufferLength, subscribersReturned, pCompCode, pReason });
/*       */     }
/*       */ 
/*       */     
/* 16717 */     String fid = "lpiSPIInquireNamedSubscribers(Hconn,final byte [ ],int,final byte [ ],final int,final byte [ ],final Pint,final Pint,final Pint,final Pint)";
/*       */ 
/*       */ 
/*       */     
/* 16721 */     LocalTls tls = (LocalTls)this.sysenv.getComponentTls(this.jmqiCompId);
/* 16722 */     JmqiTls jTls = this.sysenv.getJmqiTls(tls);
/* 16723 */     LocalHconn lockedLocalhconn = null; try {
/*       */       final byte[] rxpbBuf;
/* 16725 */       final LocalHconn localhconn = getLocalHconn(hconn);
/* 16726 */       final byte[] qmName = localhconn.getQMNameBytes();
/*       */       
/* 16728 */       RXPB rxpb = null;
/*       */       
/* 16730 */       if (JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES) {
/* 16731 */         rxpbBuf = null;
/*       */       } else {
/*       */         
/* 16734 */         rxpb = localhconn.getUpdatedRxpb();
/* 16735 */         int rxpbLen = rxpb.getRequiredBufferSize(ptrSize, nativeCp);
/* 16736 */         if (tls.rxpbBuf == null || tls.rxpbBuf.length < rxpbLen) {
/* 16737 */           tls.rxpbBuf = new byte[rxpbLen];
/*       */         }
/* 16739 */         rxpbBuf = tls.rxpbBuf;
/* 16740 */         rxpb.writeToBuffer(rxpbBuf, 0, ptrSize, swap, nativeCp, jTls);
/*       */       } 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/* 16746 */       if (this.useWorkerThread && !jTls.isAsyncConsumeThread(hconn)) {
/* 16747 */         localhconn.syncExec(new JmqiRunnable(this.env)
/*       */             {
/*       */               public void run()
/*       */               {
/* 16751 */                 if (Trace.isOn) {
/* 16752 */                   Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "run()");
/*       */                 }
/*       */ 
/*       */                 
/*       */                 try {
/* 16757 */                   localhconn.enterCall();
/*       */                   
/* 16759 */                   boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 16760 */                   Native.lpiSPIInquireNamedSubscribers(isClassTraced, localhconn
/* 16761 */                       .getValue(), qmName, rxpbBuf, callOptionsBuffer, subNameBuffer, subNameBufferLength, subIdBuffer, subIdBufferLength, subscribersReturned, pCompCode, pReason);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */                 
/*       */                 }
/* 16773 */                 catch (JmqiException e) {
/* 16774 */                   if (Trace.isOn) {
/* 16775 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 1);
/*       */                   }
/*       */                   
/* 16778 */                   pCompCode.x = e.getCompCode();
/* 16779 */                   pReason.x = e.getReason();
/*       */                 }
/* 16781 */                 catch (UnsatisfiedLinkError e) {
/* 16782 */                   if (Trace.isOn) {
/* 16783 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", e, 2);
/*       */                   }
/* 16785 */                   pCompCode.x = 2;
/* 16786 */                   pReason.x = 2298;
/*       */                 } finally {
/*       */                   
/* 16789 */                   if (Trace.isOn) {
/* 16790 */                     Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                   }
/*       */                   
/*       */                   try {
/* 16794 */                     localhconn.leaveCall();
/*       */                   }
/* 16796 */                   catch (JmqiException e) {
/* 16797 */                     if (Trace.isOn) {
/* 16798 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.local.null", "run()", (Throwable)e, 3);
/*       */                     }
/* 16800 */                     pCompCode.x = e.getCompCode();
/* 16801 */                     pReason.x = e.getReason();
/*       */                   } 
/*       */                 } 
/* 16804 */                 if (Trace.isOn) {
/* 16805 */                   Trace.exit(this, "com.ibm.mq.jmqi.local.null", "run()");
/*       */                 }
/*       */               }
/*       */             });
/*       */       }
/*       */       else {
/*       */         
/* 16812 */         if (this.adapterIsRRS) {
/*       */           
/* 16814 */           localhconn.enterCall();
/* 16815 */           lockedLocalhconn = localhconn;
/*       */         } 
/* 16817 */         boolean isClassTraced = (Trace.isOn && Trace.isClassTraced(this, "com.ibm.mq.jmqi.local.LocalMQ.Native"));
/* 16818 */         Native.lpiSPIInquireNamedSubscribers(isClassTraced, localhconn
/* 16819 */             .getValue(), qmName, rxpbBuf, callOptionsBuffer, subNameBuffer, subNameBufferLength, subIdBuffer, subIdBufferLength, subscribersReturned, pCompCode, pReason);
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       
/*       */       }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     
/*       */     }
/* 16832 */     catch (JmqiException e) {
/* 16833 */       if (Trace.isOn) {
/* 16834 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "lpiSPIInquireNamedSubscribers(Hconn,final byte [ ],int,final byte [ ],final int,final byte [ ],final Pint,final Pint,final Pint,final Pint)", (Throwable)e, 1);
/*       */       }
/*       */ 
/*       */       
/* 16838 */       pCompCode.x = e.getCompCode();
/* 16839 */       pReason.x = e.getReason();
/*       */     }
/* 16841 */     catch (UnsatisfiedLinkError e) {
/* 16842 */       if (Trace.isOn) {
/* 16843 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "lpiSPIInquireNamedSubscribers(Hconn,final byte [ ],int,final byte [ ],final int,final byte [ ],final Pint,final Pint,final Pint,final Pint)", e, 2);
/*       */       }
/*       */ 
/*       */       
/* 16847 */       pCompCode.x = 2;
/* 16848 */       pReason.x = 2298;
/*       */     }
/* 16850 */     catch (Throwable e) {
/* 16851 */       if (Trace.isOn) {
/* 16852 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "lpiSPIInquireNamedSubscribers(Hconn,final byte [ ],int,final byte [ ],final int,final byte [ ],final Pint,final Pint,final Pint,final Pint)", e, 3);
/*       */       
/*       */       }
/*       */     
/*       */     }
/*       */     finally {
/*       */ 
/*       */       
/* 16860 */       if (Trace.isOn) {
/* 16861 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "lpiSPIInquireNamedSubscribers(Hconn,final byte [ ],int,final byte [ ],final int,final byte [ ],final Pint,final Pint,final Pint,final Pint)");
/*       */       }
/*       */       
/* 16864 */       if (lockedLocalhconn != null) {
/*       */         
/*       */         try {
/* 16867 */           lockedLocalhconn.leaveCall();
/*       */         }
/* 16869 */         catch (JmqiException e) {
/* 16870 */           if (Trace.isOn) {
/* 16871 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "lpiSPIInquireNamedSubscribers(Hconn,final byte [ ],int,final byte [ ],final int,final byte [ ],final Pint,final Pint,final Pint,final Pint)", (Throwable)e, 4);
/*       */           }
/*       */ 
/*       */           
/* 16875 */           jTls.lastException = e;
/* 16876 */           pCompCode.x = e.getCompCode();
/* 16877 */           pReason.x = e.getReason();
/*       */         } 
/*       */       }
/*       */     } 
/* 16881 */     if (Trace.isOn) {
/* 16882 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "lpiSPIInquireNamedSubscribers(Hconn,final byte [ ],int,final byte [ ],final int,final byte [ ],final Pint,final Pint,final Pint,final Pint)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   private void clearInheritedRXPB(JmqiTls jTls) {
/* 16893 */     if (Trace.isOn) {
/* 16894 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "clearInheritedRXPB(JmqiTls)", new Object[] { jTls });
/*       */     }
/*       */ 
/*       */     
/* 16898 */     if (cmdLevel >= 701) {
/* 16899 */       if (Trace.isOn) {
/* 16900 */         Trace.data(this, "clearInheritedRXPB(JmqiTls)", "cmdLevel: " + cmdLevel + ", The Queue Manager does support RXPB version 3");
/*       */       }
/*       */       
/* 16903 */       Map<String, byte[]> cachedCtx = jTls.getConnections();
/* 16904 */       if (Trace.isOn) {
/* 16905 */         Trace.data(this, "clearInheritedRXPB(JmqiTls)", "About to clear " + cachedCtx.size() + " cached context tokens");
/*       */       }
/* 16907 */       cachedCtx.clear();
/*       */     } 
/*       */     
/* 16910 */     if (Trace.isOn) {
/* 16911 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "clearInheritedRXPB(JmqiTls)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public void dump(PrintWriter pw, int level) {
/* 16922 */     if (Trace.isOn) {
/* 16923 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "dump(PrintWriter,int)", new Object[] { pw, Integer.valueOf(level) });
/*       */     }
/* 16925 */     String prefix = Trace.buildPrefix(level);
/* 16926 */     pw.format("%s%s%n", new Object[] { prefix, toString() });
/* 16927 */     pw.format("%s  cmdLevel %d%n", new Object[] { prefix, Integer.valueOf(cmdLevel) });
/* 16928 */     pw.format("%s  ptrSize %d%n", new Object[] { prefix, Integer.valueOf(ptrSize) });
/* 16929 */     pw.format("%s  codepage %s%n", new Object[] { prefix, String.valueOf(nativeCp) });
/* 16930 */     pw.format("%s  swap %b%n", new Object[] { prefix, Boolean.valueOf(swap) });
/* 16931 */     pw.format("%s  isInitialised %b%n", new Object[] { prefix, Boolean.valueOf(isInitialised) });
/* 16932 */     pw.format("%s  useSharedHconn %b%n", new Object[] { prefix, Boolean.valueOf(this.useSharedHconn) });
/* 16933 */     pw.format("%s  useWorkerThread %b%n", new Object[] { prefix, Boolean.valueOf(this.useWorkerThread) });
/* 16934 */     pw.format("%s  useWorkerThreadForAsyncOnly %b%n", new Object[] { prefix, Boolean.valueOf(this.useWorkerThreadForAsyncOnly) });
/* 16935 */     if (Trace.isOn) {
/* 16936 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "dump(PrintWriter,int)");
/*       */     }
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public String getComponentName() {
/* 16946 */     if (Trace.isOn) {
/* 16947 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalMQ", "getComponentName()", "getter", "JMQI Local");
/*       */     }
/*       */     
/* 16950 */     return "JMQI Local";
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean isLocal() {
/* 16958 */     if (Trace.isOn) {
/* 16959 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalMQ", "isLocal()", "getter", 
/* 16960 */           Boolean.valueOf(true));
/*       */     }
/* 16962 */     return true;
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean isCICS() {
/* 16970 */     if (Trace.isOn) {
/* 16971 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "isCICS()");
/*       */     }
/*       */     try {
/* 16974 */       boolean traceRet1 = getAdapter().isCICS();
/* 16975 */       if (Trace.isOn) {
/* 16976 */         Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "isCICS()", Boolean.valueOf(traceRet1), 1);
/*       */       }
/*       */       
/* 16979 */       return traceRet1;
/*       */     }
/* 16981 */     catch (JmqiException e) {
/* 16982 */       if (Trace.isOn) {
/* 16983 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "isCICS()", (Throwable)e);
/*       */       }
/* 16985 */       if (Trace.isOn) {
/* 16986 */         Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "isCICS()", Boolean.valueOf(false), 2);
/*       */       }
/* 16988 */       return false;
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public boolean isIMS() {
/* 16997 */     if (Trace.isOn) {
/* 16998 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalMQ", "isIMS()");
/*       */     }
/*       */     try {
/* 17001 */       boolean traceRet1 = getAdapter().isIMS();
/* 17002 */       if (Trace.isOn) {
/* 17003 */         Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "isIMS()", Boolean.valueOf(traceRet1), 1);
/*       */       }
/*       */       
/* 17006 */       return traceRet1;
/*       */     }
/* 17008 */     catch (JmqiException e) {
/* 17009 */       if (Trace.isOn) {
/* 17010 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalMQ", "isIMS()", (Throwable)e);
/*       */       }
/* 17012 */       if (Trace.isOn) {
/* 17013 */         Trace.exit(this, "com.ibm.mq.jmqi.local.LocalMQ", "isIMS()", Boolean.valueOf(false), 2);
/*       */       }
/* 17015 */       return false;
/*       */     } 
/*       */   }
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   
/*       */   public int getReconnectCycle() {
/* 17024 */     return 0;
/*       */   }
/*       */   
/*       */   public abstract String getLibraryName();
/*       */   
/*       */   public abstract String getISeriesLibraryName();
/*       */   
/*       */   protected abstract String getZosBootstrapLibraryName();
/*       */   
/*       */   protected abstract String getZosBootstrapLibraryName64();
/*       */   
/*       */   public abstract String getZosBatchLibraryName();
/*       */   
/*       */   public abstract String getZosBatchLibraryName64();
/*       */   
/*       */   public abstract String getZosCicsLibraryName();
/*       */   
/*       */   public abstract String getZosCicsLibraryName64();
/*       */   
/*       */   public abstract String getZosWASLibraryName();
/*       */   
/*       */   public abstract String getZosWASLibraryName64();
/*       */   
/*       */   public abstract String getZosWMBLibraryName();
/*       */   
/*       */   public abstract String getZosWMBLibraryName64();
/*       */   
/*       */   public abstract String getZosInternalRRSLibraryName();
/*       */   
/*       */   public abstract String getZosInternalRRSLibraryName64();
/*       */   
/*       */   public abstract String getZosExternalRRSLibraryName();
/*       */   
/*       */   public abstract String getZosExternalRRSLibraryName64();
/*       */   
/*       */   public abstract String getWindowsLibraryName64();
/*       */   
/*       */   public abstract String getWindowsLibraryName();
/*       */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\local\LocalMQ.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */