/*      */ package com.ibm.mq.jmqi.remote.exit;
/*      */ 
/*      */ import com.ibm.mq.exits.MQCD;
/*      */ import com.ibm.mq.exits.MQCSP;
/*      */ import com.ibm.mq.exits.MQCXP;
/*      */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*      */ import com.ibm.mq.jmqi.JmqiException;
/*      */ import com.ibm.mq.jmqi.JmqiObject;
/*      */ import com.ibm.mq.jmqi.handles.PbyteBuffer;
/*      */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*      */ import com.ibm.mq.jmqi.remote.impl.RemoteConnection;
/*      */ import com.ibm.mq.jmqi.remote.impl.RemoteSession;
/*      */ import com.ibm.mq.jmqi.remote.impl.RemoteTls;
/*      */ import com.ibm.mq.jmqi.remote.rfp.RfpTSH;
/*      */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*      */ import com.ibm.mq.jmqi.system.JmqiComponentTls;
/*      */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*      */ import com.ibm.mq.jmqi.system.JmqiTls;
/*      */ import com.ibm.msg.client.commonservices.cssystem.CSSystem;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.io.File;
/*      */ import java.io.FilenameFilter;
/*      */ import java.lang.reflect.Constructor;
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.lang.reflect.Method;
/*      */ import java.net.MalformedURLException;
/*      */ import java.net.URL;
/*      */ import java.net.URLClassLoader;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.ByteOrder;
/*      */ import java.security.AccessControlException;
/*      */ import java.security.AccessController;
/*      */ import java.security.PrivilegedAction;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.StringTokenizer;
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
/*      */ public class RemoteExitChain
/*      */   extends JmqiObject
/*      */ {
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/exit/RemoteExitChain.java";
/*      */   
/*      */   static {
/*   74 */     if (Trace.isOn) {
/*   75 */       Trace.data("com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/exit/RemoteExitChain.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean nativeLibraryLoadAttempted = false;
/*      */ 
/*      */   
/*      */   private static boolean nativeLibraryLoaded = false;
/*      */   
/*   86 */   private static Throwable loadingError = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   93 */   private static Class<?> V6ExitDefinitionClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static class DummyV6ExitDefinitionsClass
/*      */   {
/*      */     static boolean isLoadable(Object exitObject) {
/*  103 */       if (Trace.isOn) {
/*  104 */         Trace.entry("com.ibm.mq.jmqi.remote.exit.DummyV6ExitDefinitionsClass", "isLoadable(Object)", new Object[] { exitObject });
/*      */       }
/*      */       
/*  107 */       if (Trace.isOn) {
/*  108 */         Trace.exit("com.ibm.mq.jmqi.remote.exit.DummyV6ExitDefinitionsClass", "isLoadable(Object)", 
/*  109 */             Boolean.valueOf(false));
/*      */       }
/*  111 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     static void load(JmqiEnvironment env, RemoteExitChain parent, Object exitObject, String exitUserData, RemoteConnection connection, int sizeofNativePointer, boolean mqcdExit) throws JmqiException {
/*  121 */       if (Trace.isOn) {
/*  122 */         Trace.entry("com.ibm.mq.jmqi.remote.exit.DummyV6ExitDefinitionsClass", "load(JmqiEnvironment,RemoteExitChain,Object,String,RemoteConnection,int,boolean)", new Object[] { env, parent, exitObject, exitUserData, connection, 
/*      */ 
/*      */               
/*  125 */               Integer.valueOf(sizeofNativePointer), Boolean.valueOf(mqcdExit) });
/*      */       }
/*  127 */       if (Trace.isOn) {
/*  128 */         Trace.exit("com.ibm.mq.jmqi.remote.exit.DummyV6ExitDefinitionsClass", "load(JmqiEnvironment,RemoteExitChain,Object,String,RemoteConnection,int,boolean)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     static boolean isExternalExit(Object exitObject) {
/*  140 */       if (Trace.isOn) {
/*  141 */         Trace.entry("com.ibm.mq.jmqi.remote.exit.DummyV6ExitDefinitionsClass", "isExternalExit(Object)", new Object[] { exitObject });
/*      */       }
/*      */       
/*  144 */       if (Trace.isOn) {
/*  145 */         Trace.exit("com.ibm.mq.jmqi.remote.exit.DummyV6ExitDefinitionsClass", "isExternalExit(Object)", 
/*  146 */             Boolean.valueOf(false));
/*      */       }
/*  148 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     static String getExternalExitUserData(Object exitObject) {
/*  157 */       if (Trace.isOn) {
/*  158 */         Trace.entry("com.ibm.mq.jmqi.remote.exit.DummyV6ExitDefinitionsClass", "getExternalExitUserData(Object)", new Object[] { exitObject });
/*      */       }
/*      */       
/*  161 */       if (Trace.isOn) {
/*  162 */         Trace.exit("com.ibm.mq.jmqi.remote.exit.DummyV6ExitDefinitionsClass", "getExternalExitUserData(Object)", null);
/*      */       }
/*      */       
/*  165 */       return null;
/*      */     }
/*      */   }
/*      */   
/*  169 */   private static Method v6isLoadableMethod = null;
/*  170 */   private static Method v6loadMethod = null;
/*  171 */   private static Method v6isExternalExitMethod = null;
/*  172 */   private static Method v6getExternalExitUserDataMethod = null;
/*      */ 
/*      */   
/*      */   static {
/*      */     try {
/*  177 */       V6ExitDefinitionClass = Class.forName("com.ibm.mq.jmqi.remote.exit.V6ExitDefinition");
/*      */     }
/*  179 */     catch (ClassNotFoundException e) {
/*  180 */       V6ExitDefinitionClass = DummyV6ExitDefinitionsClass.class;
/*      */     }
/*  182 */     catch (NoClassDefFoundError e) {
/*  183 */       V6ExitDefinitionClass = DummyV6ExitDefinitionsClass.class;
/*      */     } 
/*      */     try {
/*  186 */       v6isLoadableMethod = V6ExitDefinitionClass.getDeclaredMethod("isLoadable", new Class[] { Object.class });
/*  187 */       v6loadMethod = V6ExitDefinitionClass.getDeclaredMethod("load", new Class[] { JmqiEnvironment.class, RemoteExitChain.class, Object.class, String.class, RemoteConnection.class, int.class, boolean.class });
/*      */       
/*  189 */       v6isExternalExitMethod = V6ExitDefinitionClass.getDeclaredMethod("isExternalExit", new Class[] { Object.class });
/*  190 */       v6getExternalExitUserDataMethod = V6ExitDefinitionClass.getDeclaredMethod("getExternalExitUserData", new Class[] { Object.class });
/*      */     }
/*  192 */     catch (SecurityException securityException) {
/*      */ 
/*      */     
/*  195 */     } catch (NoSuchMethodException noSuchMethodException) {}
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*  200 */   ArrayList<ExitDefinition> exitDefinitions = new ArrayList<>();
/*      */   
/*      */   void addExitDefinition(ExitDefinition exitDefinition) {
/*  203 */     if (Trace.isOn) {
/*  204 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "addExitDefinition(ExitDefinition)", new Object[] { exitDefinition });
/*      */     }
/*      */     
/*  207 */     this.exitDefinitions.add(exitDefinition);
/*  208 */     exitDefinition.setExitNumber(exitCount() - 1);
/*      */     
/*  210 */     if (Trace.isOn) {
/*  211 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "addExitDefinition(ExitDefinition)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   int exitCount() {
/*  218 */     if (Trace.isOn) {
/*  219 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "exitCount()");
/*      */     }
/*  221 */     int traceRet1 = this.exitDefinitions.size();
/*      */     
/*  223 */     if (Trace.isOn) {
/*  224 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "exitCount()", 
/*  225 */           Integer.valueOf(traceRet1));
/*      */     }
/*  227 */     return traceRet1;
/*      */   }
/*      */   
/*  230 */   int exitType = 0;
/*  231 */   PbyteBuffer pMQCSPBuffer = null;
/*  232 */   int exitFapLevel = 0;
/*      */   
/*      */   int sendExitsUserSpace;
/*  235 */   MQCXP mqcxp = null;
/*      */   
/*  237 */   private JmqiCodepage nativeCp = null;
/*      */   private boolean nativeSwap;
/*  239 */   private static int sizeofNativePointer = 0;
/*      */   static {
/*  241 */     if (Trace.isOn) {
/*  242 */       Trace.entry("com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "static()");
/*      */     }
/*  244 */     sizeofNativePointer = JmqiEnvironment.getBitmode().equals("64") ? 8 : 4;
/*  245 */     if (Trace.isOn) {
/*  246 */       Trace.exit("com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "static()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RemoteExitChain(JmqiEnvironment env, int exitType) {
/*  257 */     super(env);
/*  258 */     if (Trace.isOn) {
/*  259 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "<init>(JmqiEnvironment,int)", new Object[] { env, 
/*  260 */             Integer.valueOf(exitType) });
/*      */     }
/*  262 */     this.exitType = exitType;
/*  263 */     this.nativeCp = env.getNativeCharSet();
/*  264 */     this.nativeSwap = !(ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN);
/*      */     
/*  266 */     if (Trace.isOn) {
/*  267 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "<init>(JmqiEnvironment,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static synchronized void loadNativeStubLibrary() {
/*  274 */     if (Trace.isOn) {
/*  275 */       Trace.entry("com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "loadNativeStubLibrary()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  284 */     if (!nativeLibraryLoadAttempted) {
/*  285 */       AccessController.doPrivileged(new PrivilegedAction()
/*      */           {
/*      */             public Object run()
/*      */             {
/*  289 */               if (Trace.isOn) {
/*  290 */                 Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "run()");
/*      */               }
/*  292 */               String libName = null;
/*      */               try {
/*  294 */                 switch (CSSystem.currentPlatform()) {
/*      */                   case OS_ISERIES:
/*  296 */                     libName = "/QSYS.LIB/QMQMJAVA.LIB/MQJSTB02.SRVPGM";
/*      */                     break;
/*      */                   case OS_ZSERIES:
/*  299 */                     libName = "wmqjstb02";
/*      */                     break;
/*      */                   case OS_WINDOWS:
/*  302 */                     if ("64".equals(JmqiEnvironment.getBitmode())) {
/*  303 */                       libName = "mqjexitstub02_64";
/*      */                       break;
/*      */                     } 
/*  306 */                     libName = "mqjexitstub02";
/*      */                     break;
/*      */                   
/*      */                   default:
/*  310 */                     libName = "mqjexitstub02";
/*      */                     break;
/*      */                 } 
/*  313 */                 System.loadLibrary(libName);
/*  314 */                 RemoteExitChain.nativeLibraryLoaded = true;
/*      */               }
/*  316 */               catch (UnsatisfiedLinkError e) {
/*  317 */                 if (Trace.isOn) {
/*  318 */                   Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.exit.null", "run()", e);
/*      */                 }
/*  320 */                 RemoteExitChain.loadingError = e;
/*  321 */                 RemoteExitChain.nativeLibraryLoaded = false;
/*      */               } 
/*  323 */               if (Trace.isOn) {
/*  324 */                 Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.null", "run()", null);
/*      */               }
/*  326 */               return null;
/*      */             }
/*      */           });
/*  329 */       nativeLibraryLoadAttempted = true;
/*      */     } 
/*  331 */     if (Trace.isOn) {
/*  332 */       Trace.exit("com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "loadNativeStubLibrary()");
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
/*      */   public RfpTSH callRcvExit(RemoteTls tls, RemoteConnection connection, RemoteSession remoteSession, RfpTSH tsh) throws JmqiException {
/*  348 */     if (Trace.isOn) {
/*  349 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "callRcvExit(RemoteTls,RemoteConnection,RemoteSession,RfpTSH)", new Object[] { tls, connection, remoteSession, tsh });
/*      */     }
/*      */ 
/*      */     
/*  353 */     RfpTSH traceRet1 = callSendRcvExitCommon(tls, connection, remoteSession, tsh);
/*      */     
/*  355 */     if (Trace.isOn) {
/*  356 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "callRcvExit(RemoteTls,RemoteConnection,RemoteSession,RfpTSH)", traceRet1);
/*      */     }
/*      */     
/*  359 */     return traceRet1;
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
/*      */   public RfpTSH callSendExit(RemoteTls tls, RemoteConnection connection, RemoteSession remoteSession, RfpTSH tsh) throws JmqiException {
/*  373 */     if (Trace.isOn) {
/*  374 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "callSendExit(RemoteTls,RemoteConnection,RemoteSession,RfpTSH)", new Object[] { tls, connection, remoteSession, tsh });
/*      */     }
/*      */ 
/*      */     
/*  378 */     RfpTSH traceRet1 = callSendRcvExitCommon(tls, connection, remoteSession, tsh);
/*      */     
/*  380 */     if (Trace.isOn) {
/*  381 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "callSendExit(RemoteTls,RemoteConnection,RemoteSession,RfpTSH)", traceRet1);
/*      */     }
/*      */     
/*  384 */     return traceRet1;
/*      */   }
/*      */   
/*      */   private RfpTSH callSendRcvExitCommon(RemoteTls tls, RemoteConnection connection, RemoteSession remoteSession, RfpTSH tsh) throws JmqiException {
/*  388 */     if (Trace.isOn) {
/*  389 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "callSendRcvExitCommon(RemoteTls,RemoteConnection,RemoteSession,RfpTSH)", new Object[] { tls, connection, remoteSession, tsh });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  394 */     JmqiTls jTls = ((JmqiSystemEnvironment)this.env).getJmqiTls((JmqiComponentTls)tls);
/*  395 */     tls.inExit = true;
/*      */     try {
/*  397 */       MQCD cd = connection.getNegotiatedChannel();
/*  398 */       int UserDataForRestOfChain = (this.exitType == 13) ? this.sendExitsUserSpace : 0;
/*      */       
/*  400 */       for (ExitDefinition currentExit : this.exitDefinitions) {
/*  401 */         JmqiException traceRet2, traceRet3; if (!currentExit.isInUse()) {
/*      */           continue;
/*      */         }
/*  404 */         setUpExit(cd, currentExit, this.mqcxp);
/*      */         
/*  406 */         this.mqcxp.setExitNumber(currentExit.getExitNumber() + 1);
/*  407 */         this.mqcxp.setExitReason(14);
/*  408 */         this.mqcxp.setExitResponse(0);
/*  409 */         this.mqcxp.setExitResponse2(0);
/*  410 */         this.mqcxp.setSharingConversations(connection.isMultiplexingEnabled());
/*  411 */         this.mqcxp.setExitSpace(currentExit.getExitSpace());
/*  412 */         this.mqcxp.setMaxSegmentLength(connection.getMaxTransmissionSize() - UserDataForRestOfChain);
/*  413 */         currentExit.loadMqcxpUserArea(this.mqcxp);
/*  414 */         if (this.exitType == 13) {
/*  415 */           UserDataForRestOfChain -= currentExit.getExitSpace();
/*      */         }
/*      */         
/*  418 */         Object retBuffer = currentExit.invoke(jTls, tsh, cd, this.mqcxp, null, false);
/*  419 */         if (retBuffer != null) {
/*  420 */           processExitReturnBuffer(retBuffer, tsh, currentExit.getExitName());
/*      */         }
/*      */ 
/*      */         
/*  424 */         currentExit.unLoadMqcxpUserArea(this.mqcxp);
/*      */         
/*  426 */         int exitResponse = this.mqcxp.getExitResponse();
/*  427 */         switch (exitResponse) {
/*      */           
/*      */           case -6:
/*  430 */             remoteSession.setTerminatedByExit(true);
/*  431 */             traceRet2 = new JmqiException(this.env, 9536, new String[] { cd.getChannelName(), currentExit.getExitName() }, 2, 2537, null);
/*      */ 
/*      */             
/*  434 */             if (Trace.isOn) {
/*  435 */               Trace.throwing(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "callSendRcvExitCommon(RemoteTls,RemoteConnection,RemoteSession,RfpTSH)", (Throwable)traceRet2, 1);
/*      */             }
/*      */ 
/*      */             
/*  439 */             throw traceRet2;
/*      */ 
/*      */           
/*      */           case -5:
/*  443 */             currentExit.setInUse(false);
/*      */             break;
/*      */ 
/*      */           
/*      */           case 0:
/*      */             break;
/*      */           
/*      */           default:
/*  451 */             traceRet3 = new JmqiException(this.env, 9181, new String[] { Integer.toString(exitResponse) + " (0x" + Integer.toHexString(exitResponse) + ")", null, currentExit.getExitName() }, 2, 2009, null);
/*  452 */             if (Trace.isOn) {
/*  453 */               Trace.throwing(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "callSendRcvExitCommon(RemoteTls,RemoteConnection,RemoteSession,RfpTSH)", (Throwable)traceRet3, 2);
/*      */             }
/*      */ 
/*      */             
/*  457 */             throw traceRet3;
/*      */         } 
/*      */ 
/*      */         
/*  461 */         if ((this.mqcxp.getExitResponse2() & 0x10) != 0) {
/*      */           break;
/*      */         }
/*      */       } 
/*      */       
/*  466 */       if (Trace.isOn) {
/*  467 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "callSendRcvExitCommon(RemoteTls,RemoteConnection,RemoteSession,RfpTSH)", tsh);
/*      */       }
/*      */       
/*  470 */       return tsh;
/*      */     }
/*      */     finally {
/*      */       
/*  474 */       if (Trace.isOn) {
/*  475 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "callSendRcvExitCommon(RemoteTls,RemoteConnection,RemoteSession,RfpTSH)");
/*      */       }
/*      */       
/*  478 */       tls.inExit = false;
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
/*      */   public synchronized MQCSP callScyExitSecParms(RemoteTls tls, MQCSP securityParmsP, MQCD cd, RemoteConnection connection, RemoteSession remoteSession) throws JmqiException {
/*  494 */     if (Trace.isOn) {
/*  495 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "callScyExitSecParms(RemoteTls,MQCSP,MQCD,RemoteConnection,RemoteSession)", new Object[] { tls, securityParmsP, cd, connection, remoteSession });
/*      */     }
/*      */ 
/*      */     
/*  499 */     if (exitCount() == 0) {
/*      */       
/*  501 */       if (Trace.isOn) {
/*  502 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "callScyExitSecParms(RemoteTls,MQCSP,MQCD,RemoteConnection,RemoteSession)", null, 1);
/*      */       }
/*      */       
/*  505 */       return null;
/*      */     } 
/*      */     
/*  508 */     MQCSP securityParms = securityParmsP;
/*      */ 
/*      */     
/*  511 */     tls.inExit = true;
/*  512 */     JmqiTls jTls = ((JmqiSystemEnvironment)this.env).getJmqiTls((JmqiComponentTls)tls);
/*  513 */     byte[] mqcspStruct = null;
/*      */     
/*      */     try {
/*      */       JmqiException traceRet2, traceRet3;
/*  517 */       ExitDefinition currentExit = this.exitDefinitions.get(0);
/*      */       
/*  519 */       setUpExit(cd, currentExit, this.mqcxp);
/*      */       
/*  521 */       this.mqcxp.setExitNumber(1);
/*  522 */       this.mqcxp.setExitReason(29);
/*  523 */       this.mqcxp.setExitResponse(0);
/*  524 */       this.mqcxp.setExitResponse2(0);
/*  525 */       this.mqcxp.setSharingConversations(false);
/*  526 */       this.mqcxp.setSecurityParms(securityParms);
/*      */       
/*  528 */       currentExit.loadMqcxpUserArea(this.mqcxp);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  534 */       if (currentExit instanceof NativeExitDefinition) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  541 */         if (securityParms != null) {
/*      */           
/*  543 */           int size = securityParms.getRequiredBufferSize(sizeofNativePointer, this.nativeCp);
/*  544 */           mqcspStruct = new byte[size];
/*  545 */           securityParms.writeToBuffer(mqcspStruct, 0, sizeofNativePointer, this.nativeSwap, this.nativeCp, jTls);
/*      */         } else {
/*      */           
/*  548 */           mqcspStruct = null;
/*      */         } 
/*      */         
/*  551 */         currentExit.invoke(jTls, null, cd, this.mqcxp, mqcspStruct, false);
/*      */ 
/*      */ 
/*      */         
/*  555 */         if ((((NativeExitDefinition)currentExit).getJniParms()).mqcsp != null) {
/*      */ 
/*      */ 
/*      */           
/*  559 */           if (mqcspStruct == null || mqcspStruct.length < (((NativeExitDefinition)currentExit).getJniParms()).mqcsp.capacity()) {
/*  560 */             mqcspStruct = new byte[(((NativeExitDefinition)currentExit).getJniParms()).mqcsp.capacity()];
/*      */           }
/*  562 */           (((NativeExitDefinition)currentExit).getJniParms()).mqcsp.clear();
/*  563 */           (((NativeExitDefinition)currentExit).getJniParms()).mqcsp.get(mqcspStruct);
/*      */           
/*  565 */           if (securityParms != null) {
/*      */             
/*  567 */             securityParms.readFromBuffer(mqcspStruct, 0, sizeofNativePointer, this.nativeSwap, this.nativeCp, jTls);
/*      */           } else {
/*      */             
/*  570 */             MQCSP newSecurityParms = this.env.newMQCSP();
/*      */             
/*  572 */             newSecurityParms.readFromBuffer(mqcspStruct, 0, sizeofNativePointer, this.nativeSwap, this.nativeCp, jTls);
/*      */             
/*  574 */             securityParms = newSecurityParms;
/*      */           } 
/*      */         } else {
/*      */           
/*  578 */           securityParms = null;
/*      */         } 
/*      */         
/*  581 */         this.mqcxp.setSecurityParms(securityParms);
/*      */       } else {
/*  583 */         currentExit.invoke(jTls, null, cd, this.mqcxp, mqcspStruct, false);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  588 */       securityParms = this.mqcxp.getSecurityParms();
/*      */ 
/*      */       
/*  591 */       int exitResponse = this.mqcxp.getExitResponse();
/*  592 */       switch (exitResponse) {
/*      */         
/*      */         case -6:
/*      */         case -1:
/*  596 */           remoteSession.setTerminatedByExit(true);
/*  597 */           traceRet2 = new JmqiException(this.env, 9536, new String[] { cd.getChannelName(), currentExit.getExitName() }, 2, 2537, null);
/*      */           
/*  599 */           if (Trace.isOn) {
/*  600 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "callScyExitSecParms(RemoteTls,MQCSP,MQCD,RemoteConnection,RemoteSession)", (Throwable)traceRet2, 1);
/*      */           }
/*      */ 
/*      */           
/*  604 */           throw traceRet2;
/*      */ 
/*      */         
/*      */         case 0:
/*      */           break;
/*      */ 
/*      */         
/*      */         default:
/*  612 */           traceRet3 = new JmqiException(this.env, 9181, new String[] { Integer.toString(exitResponse) + " (0x" + Integer.toHexString(exitResponse) + ")", null, currentExit.getExitName() }, 2, 2009, null);
/*  613 */           if (Trace.isOn) {
/*  614 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "callScyExitSecParms(RemoteTls,MQCSP,MQCD,RemoteConnection,RemoteSession)", (Throwable)traceRet3, 2);
/*      */           }
/*      */ 
/*      */           
/*  618 */           throw traceRet3;
/*      */       } 
/*      */ 
/*      */       
/*  622 */       currentExit.unLoadMqcxpUserArea(this.mqcxp);
/*      */       
/*  624 */       if (Trace.isOn) {
/*  625 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "callScyExitSecParms(RemoteTls,MQCSP,MQCD,RemoteConnection,RemoteSession)", securityParms, 2);
/*      */       }
/*      */ 
/*      */       
/*  629 */       return securityParms;
/*      */     }
/*      */     finally {
/*      */       
/*  633 */       if (Trace.isOn) {
/*  634 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "callScyExitSecParms(RemoteTls,MQCSP,MQCD,RemoteConnection,RemoteSession)");
/*      */       }
/*      */       
/*  637 */       tls.inExit = false;
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
/*      */   public synchronized void sendSecurityFlows(RemoteTls tls, MQCD cd, RemoteConnection connection) throws JmqiException {
/*  652 */     if (Trace.isOn) {
/*  653 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "sendSecurityFlows(RemoteTls,MQCD,RemoteConnection)", new Object[] { tls, cd, connection });
/*      */     }
/*      */     
/*  656 */     if (exitCount() == 0) {
/*      */       
/*  658 */       if (Trace.isOn) {
/*  659 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "sendSecurityFlows(RemoteTls,MQCD,RemoteConnection)", 1);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  665 */     JmqiTls jTls = ((JmqiSystemEnvironment)this.env).getJmqiTls((JmqiComponentTls)tls);
/*  666 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*      */ 
/*      */     
/*  669 */     tls.inExit = true;
/*      */     
/*  671 */     ExitDefinition currentExit = this.exitDefinitions.get(0);
/*      */ 
/*      */     
/*      */     try {
/*  675 */       this.mqcxp.setExitNumber(1);
/*  676 */       this.mqcxp.setExitReason(16);
/*  677 */       this.mqcxp.setExitResponse(0);
/*  678 */       this.mqcxp.setExitResponse2(0);
/*  679 */       this.mqcxp.setSharingConversations(false);
/*  680 */       this.mqcxp.setExitData(cd.getSecurityUserData());
/*  681 */       this.mqcxp.setPartnerName(cd.getQMgrName());
/*      */       
/*  683 */       currentExit.loadMqcxpUserArea(this.mqcxp);
/*      */       
/*  685 */       if (Trace.isOn) {
/*  686 */         Trace.data(this, "sendSecurityFlows(RemoteTls,MQCD,RemoteConnection)", "MQCXP:\n" + this.mqcxp.toStringMultiLine());
/*      */       }
/*      */       
/*  689 */       boolean securityExchangeComplete = false;
/*  690 */       boolean serverResponseRequired = false;
/*  691 */       boolean dataToSend = false;
/*  692 */       boolean firstInvocation = true;
/*  693 */       int userDataLength = 0;
/*  694 */       RfpTSH tsh = connection.allocInitialDataTsh(6);
/*      */ 
/*      */ 
/*      */       
/*  698 */       tsh.setTransLength(tsh.tshHdrSize() + 4);
/*      */ 
/*      */       
/*  701 */       dc.writeI32(0, tsh.getRfpBuffer(), tsh.tshHdrSize(), this.nativeSwap);
/*      */ 
/*      */       
/*  704 */       RfpTSH rTSH = null;
/*      */ 
/*      */       
/*      */       try {
/*  708 */         while (!securityExchangeComplete) {
/*      */           JmqiException traceRet2, traceRet5, traceRet3;
/*      */           
/*      */           HashMap<String, Object> info;
/*      */           
/*  713 */           if (!firstInvocation) {
/*      */ 
/*      */             
/*  716 */             tsh = connection.allocInitialDataTsh(6);
/*      */ 
/*      */ 
/*      */             
/*  720 */             System.arraycopy(rTSH.getRfpBuffer(), 0, tsh.getRfpBuffer(), 0, rTSH.getTransLength());
/*      */ 
/*      */ 
/*      */             
/*  724 */             tsh.setTransLength(rTSH.getTransLength());
/*      */           } 
/*      */           
/*  727 */           Object retBuffer = currentExit.invoke(jTls, tsh, cd, this.mqcxp, null, firstInvocation);
/*  728 */           if (this.mqcxp.getExitResponse() == 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  733 */             if (Trace.isOn) {
/*  734 */               Trace.data(this, "sendSecurityFlows(RemoteTls,MQCD,RemoteConnection)", "Security exit returned MQXCC_OK, setting buffer to empty byte array");
/*      */             }
/*  736 */             retBuffer = new byte[0];
/*      */           } 
/*  738 */           userDataLength = processExitReturnBuffer(retBuffer, tsh, currentExit.getExitName());
/*      */ 
/*      */           
/*  741 */           currentExit.unLoadMqcxpUserArea(this.mqcxp);
/*      */ 
/*      */           
/*  744 */           int exitResponse = this.mqcxp.getExitResponse();
/*  745 */           switch (exitResponse) {
/*      */             
/*      */             case -6:
/*      */             case -1:
/*  749 */               traceRet2 = new JmqiException(this.env, 9536, new String[] { cd.getChannelName(), currentExit.getExitName() }, 2, 2063, null);
/*      */               
/*  751 */               if (Trace.isOn) {
/*  752 */                 Trace.throwing(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "sendSecurityFlows(RemoteTls,MQCD,RemoteConnection)", (Throwable)traceRet2, 1);
/*      */               }
/*      */               
/*  755 */               throw traceRet2;
/*      */ 
/*      */             
/*      */             case -3:
/*  759 */               serverResponseRequired = true;
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*      */             case -4:
/*  765 */               dataToSend = true;
/*      */               
/*  767 */               tsh.setEncoding(this.nativeSwap ? 2 : 1);
/*  768 */               tsh.setMQEncoding(273, this.nativeSwap);
/*  769 */               tsh.setCcsid(this.nativeCp.getCCSID(), this.nativeSwap);
/*      */               
/*  771 */               tsh.setSegmentType(6);
/*  772 */               dc.writeI32(userDataLength, tsh.getRfpBuffer(), tsh.tshHdrSize(), this.nativeSwap);
/*      */               break;
/*      */ 
/*      */ 
/*      */             
/*      */             case 0:
/*      */               break;
/*      */ 
/*      */ 
/*      */             
/*      */             default:
/*  783 */               traceRet3 = new JmqiException(this.env, 9181, new String[] { Integer.toString(exitResponse) + " (0x" + Integer.toHexString(exitResponse) + ")", null, currentExit.getExitName() }, 2, 2009, null);
/*  784 */               if (Trace.isOn) {
/*  785 */                 Trace.throwing(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "sendSecurityFlows(RemoteTls,MQCD,RemoteConnection)", (Throwable)traceRet3, 2);
/*      */               }
/*      */               
/*  788 */               throw traceRet3;
/*      */           } 
/*      */ 
/*      */           
/*  792 */           if (!dataToSend && !firstInvocation) {
/*  793 */             securityExchangeComplete = true;
/*      */ 
/*      */             
/*      */             continue;
/*      */           } 
/*      */ 
/*      */           
/*  800 */           if (userDataLength == 0 || !dataToSend) {
/*      */             
/*  802 */             if (userDataLength == 0 && Trace.isOn) {
/*  803 */               Trace.data(this, "sendSecurityFlows(RemoteTls,MQCD,RemoteConnection)", "userDataLength is 0, setting buffer size");
/*      */             }
/*      */             
/*  806 */             dataToSend = true;
/*      */             
/*  808 */             tsh.setEncoding(this.nativeSwap ? 2 : 1);
/*  809 */             tsh.setMQEncoding(273, this.nativeSwap);
/*  810 */             tsh.setCcsid(this.nativeCp.getCCSID(), this.nativeSwap);
/*      */             
/*  812 */             tsh.setTransLength(tsh.tshHdrSize() + 4);
/*      */ 
/*      */             
/*  815 */             dc.writeI32(0, tsh.getRfpBuffer(), tsh.tshHdrSize(), this.nativeSwap);
/*      */             
/*  817 */             tsh.setSegmentType(6);
/*      */           } 
/*      */ 
/*      */           
/*  821 */           tsh.setControlFlags1(1);
/*      */ 
/*      */           
/*  824 */           connection.sendTSH(tls, tsh, null);
/*      */ 
/*      */           
/*  827 */           if (rTSH != null) {
/*  828 */             connection.releaseReceivedTSH(rTSH);
/*      */             
/*  830 */             rTSH = null;
/*      */           } 
/*      */ 
/*      */           
/*  834 */           rTSH = connection.receiveTSH(null, tls, null);
/*      */           
/*  836 */           switch (rTSH.getSegmentType()) {
/*      */ 
/*      */ 
/*      */             
/*      */             case 5:
/*  841 */               if (Trace.isOn) {
/*  842 */                 Trace.data(this, "sendSecurityFlows(RemoteTls,MQCD,RemoteConnection)", "About to analyse error if control flags includes close channel: ", String.valueOf(rTSH
/*  843 */                       .getControlFlags1()));
/*      */               }
/*  845 */               if ((rTSH.getControlFlags1() & 0x8) != 0)
/*      */               {
/*  847 */                 connection.analyseErrorSegment(rTSH);
/*      */               }
/*  849 */               if (serverResponseRequired) {
/*      */                 
/*  851 */                 JmqiException traceRet4 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*      */                 
/*  853 */                 HashMap<String, Object> hashMap = new HashMap<>();
/*  854 */                 hashMap.put("Description", "Server sent unexpected TST_STATUS_DATA");
/*  855 */                 Trace.ffst(this, "sendSecurityFlows(RemoteTls,MQCD,RemoteConnection)", "01", hashMap, null);
/*  856 */                 if (Trace.isOn) {
/*  857 */                   Trace.throwing(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "sendSecurityFlows(RemoteTls,MQCD,RemoteConnection)", (Throwable)traceRet4, 3);
/*      */                 }
/*      */                 
/*  860 */                 throw traceRet4;
/*      */               } 
/*      */               
/*  863 */               securityExchangeComplete = true;
/*      */               break;
/*      */ 
/*      */             
/*      */             case 6:
/*  868 */               this.mqcxp.setExitReason(15);
/*      */ 
/*      */               
/*  871 */               serverResponseRequired = false;
/*      */               break;
/*      */ 
/*      */ 
/*      */             
/*      */             default:
/*  877 */               traceRet5 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*      */               
/*  879 */               info = new HashMap<>();
/*  880 */               info.put("Description", "Server sent unexpected TST_STATUS_DATA");
/*  881 */               Trace.ffst(this, "sendSecurityFlows(RemoteTls,MQCD,RemoteConnection)", "02", info, null);
/*  882 */               if (Trace.isOn) {
/*  883 */                 Trace.throwing(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "sendSecurityFlows(RemoteTls,MQCD,RemoteConnection)", (Throwable)traceRet5, 4);
/*      */               }
/*      */               
/*  886 */               throw traceRet5;
/*      */           } 
/*      */           
/*  889 */           firstInvocation = false;
/*      */         } 
/*      */       } finally {
/*      */         
/*  893 */         if (Trace.isOn) {
/*  894 */           Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "sendSecurityFlows(RemoteTls,MQCD,RemoteConnection)", 1);
/*      */         }
/*      */ 
/*      */         
/*  898 */         if (rTSH != null) {
/*  899 */           connection.releaseReceivedTSH(rTSH);
/*  900 */           rTSH = null;
/*  901 */           tsh = null;
/*      */         }
/*      */       
/*      */       } 
/*      */     } finally {
/*      */       
/*  907 */       if (Trace.isOn) {
/*  908 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "sendSecurityFlows(RemoteTls,MQCD,RemoteConnection)", 2);
/*      */       }
/*      */       
/*  911 */       tls.inExit = false;
/*      */     } 
/*  913 */     if (Trace.isOn) {
/*  914 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "sendSecurityFlows(RemoteTls,MQCD,RemoteConnection)", 2);
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
/*      */   public void loadExits(MQCD cd, Object exitChain, RemoteConnection connection, String exitUserData, String exitClassPath) throws JmqiException {
/*      */     JmqiException traceRet4;
/*      */     HashMap<String, Object> info;
/*  948 */     if (Trace.isOn) {
/*  949 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "loadExits(MQCD,Object,RemoteConnection,String,String)", new Object[] { cd, exitChain, connection, exitUserData, exitClassPath });
/*      */     }
/*      */ 
/*      */     
/*  953 */     switch (this.exitType) {
/*      */       case 11:
/*  955 */         parseSecurityExit(cd.getSecurityExit(), exitChain, cd, exitClassPath, connection, exitUserData);
/*      */         break;
/*      */       case 13:
/*  958 */         parseSendReceiveExitsChain(cd.getSendExitPtr(), cd.getSendExitsDefined(), exitChain, cd, exitClassPath, connection, exitUserData);
/*      */         break;
/*      */       case 14:
/*  961 */         parseSendReceiveExitsChain(cd.getReceiveExitPtr(), cd.getReceiveExitsDefined(), exitChain, cd, exitClassPath, connection, exitUserData);
/*      */         break;
/*      */       default:
/*  964 */         traceRet4 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*      */         
/*  966 */         info = new HashMap<>();
/*  967 */         info.put("exitType", Integer.valueOf(this.exitType));
/*  968 */         info.put("Description", "Unexpected exit type");
/*  969 */         Trace.ffst(this, "loadExits(MQCD,Object,RemoteConnection,String,String)", "01", info, null);
/*  970 */         if (Trace.isOn) {
/*  971 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "loadExits(MQCD,Object,RemoteConnection,String,String)", (Throwable)traceRet4);
/*      */         }
/*      */         
/*  974 */         throw traceRet4;
/*      */     } 
/*      */ 
/*      */     
/*  978 */     if (exitCount() > 0) {
/*  979 */       String remoteProductId = connection.getRemoteProductId();
/*  980 */       this.sendExitsUserSpace = 0;
/*      */ 
/*      */ 
/*      */       
/*  984 */       this.mqcxp = this.env.newMQCXP();
/*      */       
/*  986 */       this.mqcxp.setVersion(9);
/*  987 */       this.mqcxp.setExitId(this.exitType);
/*  988 */       this.mqcxp.setMaxSegmentLength(connection.getMaxTransmissionSize());
/*  989 */       this.mqcxp.setPartnerName(connection.getRemoteQmgrName());
/*  990 */       this.mqcxp.setFapLevel(connection.getFapLevel());
/*      */       
/*  992 */       if (remoteProductId != null) {
/*  993 */         this.mqcxp.setRemoteProduct(remoteProductId.substring(0, 4));
/*  994 */         this.mqcxp.setRemoteVersion(remoteProductId.substring(4, 12));
/*      */       } 
/*      */       
/*  997 */       this.mqcxp.setCapabilityFlags(0);
/*  998 */       this.mqcxp.setCurHdrCompression(connection.getCurHdrCompression());
/*  999 */       this.mqcxp.setCurMsgCompression(connection.getCurMsgCompression());
/*      */       
/* 1001 */       this.mqcxp.setSslRemCertIssName(connection.getRemoteCertIssuerDN());
/*      */     } 
/*      */     
/* 1004 */     if (Trace.isOn) {
/* 1005 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "loadExits(MQCD,Object,RemoteConnection,String,String)");
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
/*      */   public URL[] getWMQExitClasspath(String exitClassPath) {
/* 1026 */     if (Trace.isOn) {
/* 1027 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "getWMQExitClasspath(String)", new Object[] { exitClassPath });
/*      */     }
/*      */     
/* 1030 */     Vector<URL> url = new Vector<>();
/*      */     
/* 1032 */     String propertyClasspath = (exitClassPath != null) ? exitClassPath : this.env.getConfiguration().getJavaExitsClasspath();
/* 1033 */     if (Trace.isOn) {
/* 1034 */       Trace.data(this, "getWMQExitClasspath(String)", "RemoteExit", "getWMQExitClasspath::located system property com.ibm.mq.exitClasspath=" + propertyClasspath);
/*      */     }
/*      */     
/* 1037 */     if (propertyClasspath != null && !propertyClasspath.equals("")) {
/* 1038 */       String[] entries = propertyClasspath.split(File.pathSeparator);
/* 1039 */       for (int count = 0; count < entries.length; count++) {
/*      */         try {
/* 1041 */           url.add((new File(entries[count])).toURI().toURL());
/*      */         }
/* 1043 */         catch (MalformedURLException e) {
/* 1044 */           if (Trace.isOn) {
/* 1045 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "getWMQExitClasspath(String)", e, 1);
/*      */           }
/*      */ 
/*      */           
/* 1049 */           if (Trace.isOn) {
/* 1050 */             Trace.data(this, "getWMQExitClasspath(String)", "MQExternalUserExit", "The URL " + entries[count] + " in the exitClassPath is malformed.");
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1057 */     if (isNativeLibraryLoaded()) {
/*      */       
/*      */       try {
/*      */ 
/*      */ 
/*      */         
/* 1063 */         String wmqExitDirectory = getExitClasspath();
/*      */         
/* 1065 */         if (Trace.isOn) {
/* 1066 */           Trace.data(this, "getWMQExitClasspath(String)", "RemoteExit.getWMQExitClasspath", wmqExitDirectory);
/*      */         }
/*      */         
/* 1069 */         if (wmqExitDirectory != null && !wmqExitDirectory.equals("")) {
/*      */           
/* 1071 */           final File directory = new File(wmqExitDirectory);
/* 1072 */           Boolean isDir = AccessController.<Boolean>doPrivileged(new PrivilegedAction<Boolean>()
/*      */               {
/*      */                 public Boolean run()
/*      */                 {
/* 1076 */                   if (Trace.isOn) {
/* 1077 */                     Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "run()");
/*      */                   }
/*      */                   try {
/* 1080 */                     Boolean traceRet1 = Boolean.valueOf(directory.isDirectory());
/* 1081 */                     if (Trace.isOn) {
/* 1082 */                       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.null", "run()", traceRet1, 1);
/*      */                     }
/* 1084 */                     return traceRet1;
/*      */                   }
/* 1086 */                   catch (AccessControlException ace) {
/* 1087 */                     if (Trace.isOn) {
/* 1088 */                       Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.exit.null", "run()", ace);
/*      */                     }
/*      */                     
/* 1091 */                     if (Trace.isOn) {
/* 1092 */                       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.null", "run()", Boolean.FALSE, 2);
/*      */                     }
/* 1094 */                     return Boolean.FALSE;
/*      */                   } 
/*      */                 }
/*      */               });
/* 1098 */           if (isDir.booleanValue())
/*      */           {
/*      */             
/*      */             try {
/* 1102 */               url.add(directory.toURI().toURL());
/*      */             }
/* 1104 */             catch (MalformedURLException e) {
/* 1105 */               if (Trace.isOn) {
/* 1106 */                 Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "getWMQExitClasspath(String)", e, 2);
/*      */               }
/*      */             } 
/*      */ 
/*      */             
/* 1111 */             final FilenameFilter filter = new FilenameFilter()
/*      */               {
/*      */                 public boolean accept(File dir, String name)
/*      */                 {
/* 1115 */                   if (Trace.isOn) {
/* 1116 */                     Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "accept(File,String)", new Object[] { dir, name });
/*      */                   }
/*      */                   
/* 1119 */                   if (dir.equals(directory) && name.endsWith(".jar")) {
/*      */                     
/* 1121 */                     if (Trace.isOn) {
/* 1122 */                       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.null", "accept(File,String)", 
/* 1123 */                           Boolean.valueOf(true), 1);
/*      */                     }
/* 1125 */                     return true;
/*      */                   } 
/*      */                   
/* 1128 */                   if (Trace.isOn) {
/* 1129 */                     Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.null", "accept(File,String)", 
/* 1130 */                         Boolean.valueOf(false), 2);
/*      */                   }
/* 1132 */                   return false;
/*      */                 }
/*      */               };
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1139 */             File[] files = AccessController.<File[]>doPrivileged((PrivilegedAction)new PrivilegedAction<File[]>()
/*      */                 {
/*      */                   public File[] run()
/*      */                   {
/* 1143 */                     if (Trace.isOn) {
/* 1144 */                       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "run()");
/*      */                     }
/*      */                     try {
/* 1147 */                       File[] traceRet1 = directory.listFiles(filter);
/* 1148 */                       if (Trace.isOn) {
/* 1149 */                         Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.null", "run()", traceRet1, 1);
/*      */                       }
/* 1151 */                       return traceRet1;
/*      */                     }
/* 1153 */                     catch (AccessControlException ace) {
/* 1154 */                       if (Trace.isOn) {
/* 1155 */                         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.exit.null", "run()", ace);
/*      */                       }
/*      */                       
/* 1158 */                       File[] traceRet2 = new File[0];
/* 1159 */                       if (Trace.isOn) {
/* 1160 */                         Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.null", "run()", traceRet2, 2);
/*      */                       }
/* 1162 */                       return traceRet2;
/*      */                     } 
/*      */                   }
/*      */                 });
/*      */             try {
/* 1167 */               for (int i = 0; i < files.length; i++) {
/* 1168 */                 url.add(files[i].toURI().toURL());
/*      */               }
/*      */             }
/* 1171 */             catch (Exception e) {
/* 1172 */               if (Trace.isOn) {
/* 1173 */                 Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "getWMQExitClasspath(String)", e, 3);
/*      */               }
/*      */             }
/*      */           
/*      */           }
/*      */         
/*      */         } 
/* 1180 */       } catch (UnsatisfiedLinkError error) {
/* 1181 */         if (Trace.isOn) {
/* 1182 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "getWMQExitClasspath(String)", error, 4);
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1196 */     URL[] urls = new URL[url.size()];
/*      */     
/* 1198 */     URL[] traceRet1 = url.<URL>toArray(urls);
/* 1199 */     if (Trace.isOn) {
/* 1200 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "getWMQExitClasspath(String)", traceRet1);
/*      */     }
/*      */     
/* 1203 */     return traceRet1;
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
/*      */   public void initExits(MQCD cd, int fapLevel, String remoteProductId, boolean firstConv) throws JmqiException {
/* 1216 */     if (Trace.isOn) {
/* 1217 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "initExits(MQCD,int,String,boolean)", new Object[] { cd, 
/* 1218 */             Integer.valueOf(fapLevel), remoteProductId, 
/* 1219 */             Boolean.valueOf(firstConv) });
/*      */     }
/* 1221 */     if (this.exitType != 11) {
/* 1222 */       byte[] userDataPtr = new byte[32 * exitCount()];
/* 1223 */       int userDataOffset = 0;
/* 1224 */       for (ExitDefinition currentExit : this.exitDefinitions) {
/* 1225 */         System.arraycopy(currentExit.getExitUserData(), 0, userDataPtr, userDataOffset, 32);
/* 1226 */         userDataOffset += 32;
/*      */       } 
/* 1228 */       switch (this.exitType) {
/*      */         case 13:
/* 1230 */           cd.setSendUserDataPtr(userDataPtr);
/*      */           break;
/*      */         case 14:
/* 1233 */           cd.setReceiveUserDataPtr(userDataPtr);
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */     
/*      */     } 
/* 1240 */     for (ExitDefinition currentExit : this.exitDefinitions) {
/* 1241 */       initExit(cd, currentExit, fapLevel, remoteProductId, firstConv);
/*      */     }
/*      */     
/* 1244 */     if (Trace.isOn) {
/* 1245 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "initExits(MQCD,int,String,boolean)");
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
/*      */   private void initExit(MQCD cd, ExitDefinition currentExit, int fapLevel, String remoteProductId, boolean firstConv) throws JmqiException {
/*      */     JmqiTls jTls;
/*      */     JmqiException traceRet2, traceRet3;
/* 1262 */     if (Trace.isOn) {
/* 1263 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "initExit(MQCD,ExitDefinition,int,String,boolean)", new Object[] { cd, currentExit, 
/*      */             
/* 1265 */             Integer.valueOf(fapLevel), remoteProductId, Boolean.valueOf(firstConv) });
/*      */     }
/* 1267 */     setupMqcxp(currentExit.getExitNumber(), fapLevel, remoteProductId, 11, cd.getQMgrName());
/* 1268 */     if (firstConv) {
/* 1269 */       this.mqcxp.setSharingConversations(false);
/*      */     } else {
/* 1271 */       this.mqcxp.setSharingConversations(true);
/*      */     } 
/* 1273 */     setUpExit(cd, currentExit, this.mqcxp);
/* 1274 */     switch (this.exitType) {
/*      */       case 11:
/*      */       case 13:
/*      */       case 14:
/* 1278 */         jTls = ((JmqiSystemEnvironment)this.env).getJmqiTls(null);
/* 1279 */         currentExit.invoke(jTls, null, cd, this.mqcxp, null, firstConv);
/*      */         break;
/*      */       
/*      */       default:
/* 1283 */         if (Trace.isOn) {
/* 1284 */           Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "initExit(MQCD,ExitDefinition,int,String,boolean)", 1);
/*      */         }
/*      */         return;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1292 */     currentExit.unLoadMqcxpUserArea(this.mqcxp);
/*      */     
/* 1294 */     this.sendExitsUserSpace += this.mqcxp.getExitSpace();
/* 1295 */     currentExit.setExitSpace(this.mqcxp.getExitSpace());
/*      */     
/* 1297 */     int exitResponse = this.mqcxp.getExitResponse();
/* 1298 */     switch (exitResponse) {
/*      */       
/*      */       case -6:
/*      */       case -1:
/* 1302 */         traceRet2 = new JmqiException(this.env, 9536, new String[] { cd.getChannelName(), currentExit.getExitName() }, 2, 2537, null);
/*      */ 
/*      */         
/* 1305 */         if (Trace.isOn) {
/* 1306 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "initExit(MQCD,ExitDefinition,int,String,boolean)", (Throwable)traceRet2, 1);
/*      */         }
/*      */         
/* 1309 */         throw traceRet2;
/*      */ 
/*      */ 
/*      */       
/*      */       case -5:
/* 1314 */         currentExit.setInUse(false);
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 0:
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       default:
/* 1326 */         traceRet3 = new JmqiException(this.env, 9181, new String[] { Integer.toString(exitResponse) + " (0x" + Integer.toHexString(exitResponse) + ")", null, currentExit.getExitName() }, 2, 2009, null);
/* 1327 */         if (Trace.isOn) {
/* 1328 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "initExit(MQCD,ExitDefinition,int,String,boolean)", (Throwable)traceRet3, 2);
/*      */         }
/*      */         
/* 1331 */         throw traceRet3;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1336 */     this.exitFapLevel = this.mqcxp.getFapLevel();
/*      */     
/* 1338 */     if (Trace.isOn) {
/* 1339 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "initExit(MQCD,ExitDefinition,int,String,boolean)", 2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void setupMqcxp(int exitIndex, int fapLevel, String remoteProductId, int exitReason, String mgrName) {
/* 1346 */     if (Trace.isOn)
/* 1347 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "setupMqcxp(int,int,String,int,String)", new Object[] {
/* 1348 */             Integer.valueOf(exitIndex), 
/* 1349 */             Integer.valueOf(fapLevel), remoteProductId, Integer.valueOf(exitReason), mgrName
/*      */           }); 
/* 1351 */     this.mqcxp.setVersion(9);
/* 1352 */     this.mqcxp.setFapLevel(fapLevel);
/*      */     
/* 1354 */     if (remoteProductId != null) {
/* 1355 */       this.mqcxp.setRemoteProduct(remoteProductId.substring(0, 4));
/* 1356 */       this.mqcxp.setRemoteVersion(remoteProductId.substring(4, 12));
/*      */     } 
/* 1358 */     this.mqcxp.setFeedback(0);
/* 1359 */     this.mqcxp.setExitSpace(0);
/* 1360 */     this.mqcxp.setExitNumber(exitIndex + 1);
/* 1361 */     this.mqcxp.setExitReason(exitReason);
/* 1362 */     this.mqcxp.setPartnerName(mgrName);
/* 1363 */     this.mqcxp.setExitResponse(0);
/* 1364 */     this.mqcxp.setExitResponse2(0);
/*      */     
/* 1366 */     if (Trace.isOn) {
/* 1367 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "setupMqcxp(int,int,String,int,String)");
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
/*      */   private String getElement(String string, int elementNumber, int elementLength) {
/* 1382 */     if (Trace.isOn) {
/* 1383 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "getElement(String,int,int)", new Object[] { string, 
/* 1384 */             Integer.valueOf(elementNumber), 
/* 1385 */             Integer.valueOf(elementLength) });
/*      */     }
/* 1387 */     if (string == null) {
/*      */       
/* 1389 */       if (Trace.isOn) {
/* 1390 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "getElement(String,int,int)", "", 1);
/*      */       }
/*      */       
/* 1393 */       return "";
/*      */     } 
/* 1395 */     int stringLength = string.length();
/* 1396 */     int startPos = elementNumber * elementLength;
/* 1397 */     if (stringLength > startPos) {
/* 1398 */       int remainder = stringLength - startPos;
/* 1399 */       int toget = Math.min(remainder, elementLength);
/*      */ 
/*      */       
/* 1402 */       int endPos = startPos + toget;
/* 1403 */       String traceRet1 = string.substring(startPos, endPos);
/* 1404 */       if (Trace.isOn) {
/* 1405 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "getElement(String,int,int)", traceRet1, 2);
/*      */       }
/*      */       
/* 1408 */       return traceRet1;
/*      */     } 
/* 1410 */     if (Trace.isOn) {
/* 1411 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "getElement(String,int,int)", "", 3);
/*      */     }
/*      */     
/* 1414 */     return "";
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
/*      */   public void termExits(MQCD cd, int fapLevel, String remoteProductId) throws JmqiException {
/* 1426 */     if (Trace.isOn) {
/* 1427 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "termExits(MQCD,int,String)", new Object[] { cd, 
/* 1428 */             Integer.valueOf(fapLevel), remoteProductId });
/*      */     }
/*      */     
/* 1431 */     for (ExitDefinition currentExit : this.exitDefinitions) {
/* 1432 */       termExit(cd, currentExit, fapLevel, remoteProductId);
/*      */     }
/*      */     
/* 1435 */     if (Trace.isOn) {
/* 1436 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "termExits(MQCD,int,String)");
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
/*      */   private void termExit(MQCD cd, ExitDefinition currentExit, int fapLevel, String remoteProductId) throws JmqiException {
/*      */     JmqiException traceRet2;
/* 1452 */     if (Trace.isOn) {
/* 1453 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "termExit(MQCD,ExitDefinition,int,String)", new Object[] { cd, currentExit, 
/*      */             
/* 1455 */             Integer.valueOf(fapLevel), remoteProductId });
/*      */     }
/* 1457 */     setupMqcxp(currentExit.getExitNumber(), fapLevel, remoteProductId, 12, cd.getQMgrName());
/* 1458 */     currentExit.loadMqcxpUserArea(this.mqcxp);
/* 1459 */     setUpExit(cd, currentExit, this.mqcxp);
/* 1460 */     JmqiTls jTls = ((JmqiSystemEnvironment)this.env).getJmqiTls(null);
/* 1461 */     switch (this.exitType) {
/*      */       case 11:
/* 1463 */         currentExit.invoke(jTls, null, cd, this.mqcxp, null, false);
/*      */         
/* 1465 */         if (Trace.isOn) {
/* 1466 */           Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "termExit(MQCD,ExitDefinition,int,String)", 1);
/*      */         }
/*      */         return;
/*      */ 
/*      */       
/*      */       case 13:
/*      */       case 14:
/* 1473 */         currentExit.invoke(jTls, null, cd, this.mqcxp, null, false);
/*      */         break;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1479 */     currentExit.unLoadMqcxpUserArea(this.mqcxp);
/*      */     
/* 1481 */     int exitResponse = this.mqcxp.getExitResponse();
/* 1482 */     switch (exitResponse) {
/*      */       case 0:
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       default:
/* 1491 */         traceRet2 = new JmqiException(this.env, 9181, new String[] { Integer.toString(exitResponse) + " (0x" + Integer.toHexString(exitResponse) + ")", null, currentExit.getExitName() }, 2, 2009, null);
/* 1492 */         if (Trace.isOn) {
/* 1493 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "termExit(MQCD,ExitDefinition,int,String)", (Throwable)traceRet2);
/*      */         }
/*      */         
/* 1496 */         throw traceRet2;
/*      */     } 
/*      */ 
/*      */     
/* 1500 */     if (Trace.isOn) {
/* 1501 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "termExit(MQCD,ExitDefinition,int,String)", 2);
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
/*      */   private void setUpExit(MQCD cd, ExitDefinition currentExit, MQCXP mqcxp) {
/* 1515 */     if (Trace.isOn) {
/* 1516 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "setUpExit(MQCD,ExitDefinition,MQCXP)", new Object[] { cd, currentExit, mqcxp });
/*      */     }
/*      */     
/* 1519 */     Object exitObject = currentExit.getObject();
/* 1520 */     switch (this.exitType) {
/*      */       case 13:
/* 1522 */         if (currentExit.isMqcdExit()) {
/* 1523 */           cd.setSendExit(getElement(cd.getSendExitPtr(), currentExit.getExitNumber(), cd.getExitNameLength()));
/*      */         }
/* 1525 */         if (exitObject != null && v6isExternalObject(exitObject)) {
/* 1526 */           cd.setSendUserData(v6GetExternalExitUserData(exitObject));
/*      */         } else {
/* 1528 */           cd.setSendUserData(getElement(cd.getSendUserDataPtr(), currentExit.getExitNumber(), cd.getExitDataLength()));
/*      */         } 
/* 1530 */         mqcxp.setExitData(cd.getSendUserData());
/*      */         break;
/*      */       case 14:
/* 1533 */         if (currentExit.isMqcdExit()) {
/* 1534 */           cd.setReceiveExit(getElement(cd.getReceiveExitPtr(), currentExit.getExitNumber(), cd.getExitNameLength()));
/*      */         }
/* 1536 */         if (exitObject != null && v6isExternalObject(exitObject)) {
/* 1537 */           cd.setReceiveUserData(v6GetExternalExitUserData(exitObject));
/*      */         } else {
/* 1539 */           cd.setReceiveUserData(getElement(cd.getReceiveUserDataPtr(), currentExit.getExitNumber(), cd.getExitDataLength()));
/*      */         } 
/* 1541 */         mqcxp.setExitData(cd.getReceiveUserData());
/*      */         break;
/*      */       case 11:
/* 1544 */         if (exitObject != null && v6isExternalObject(exitObject) && mqcxp.getExitReason() == 11) {
/* 1545 */           cd.setSecurityUserData(v6GetExternalExitUserData(exitObject));
/*      */         }
/* 1547 */         mqcxp.setExitData(cd.getSecurityUserData());
/*      */         break;
/*      */     } 
/*      */ 
/*      */     
/* 1552 */     if (Trace.isOn) {
/* 1553 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "setUpExit(MQCD,ExitDefinition,MQCXP)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private String v6GetExternalExitUserData(Object exitObject) {
/* 1560 */     if (Trace.isOn) {
/* 1561 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "v6GetExternalExitUserData(Object)", new Object[] { exitObject });
/*      */     }
/*      */     
/*      */     try {
/* 1565 */       String traceRet1 = (String)v6getExternalExitUserDataMethod.invoke(null, new Object[] { exitObject });
/*      */       
/* 1567 */       if (Trace.isOn) {
/* 1568 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "v6GetExternalExitUserData(Object)", traceRet1, 1);
/*      */       }
/*      */       
/* 1571 */       return traceRet1;
/*      */     }
/* 1573 */     catch (IllegalArgumentException e) {
/* 1574 */       if (Trace.isOn) {
/* 1575 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "v6GetExternalExitUserData(Object)", e, 1);
/*      */       }
/*      */       
/* 1578 */       if (Trace.isOn) {
/* 1579 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "v6GetExternalExitUserData(Object)", null, 2);
/*      */       }
/*      */       
/* 1582 */       return null;
/*      */     }
/* 1584 */     catch (IllegalAccessException e) {
/* 1585 */       if (Trace.isOn) {
/* 1586 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "v6GetExternalExitUserData(Object)", e, 2);
/*      */       }
/*      */       
/* 1589 */       if (Trace.isOn) {
/* 1590 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "v6GetExternalExitUserData(Object)", null, 3);
/*      */       }
/*      */       
/* 1593 */       return null;
/*      */     }
/* 1595 */     catch (InvocationTargetException e) {
/* 1596 */       if (Trace.isOn) {
/* 1597 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "v6GetExternalExitUserData(Object)", e, 3);
/*      */       }
/*      */       
/* 1600 */       if (Trace.isOn) {
/* 1601 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "v6GetExternalExitUserData(Object)", null, 4);
/*      */       }
/*      */       
/* 1604 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   private boolean v6isExternalObject(Object exitObject) {
/* 1609 */     if (Trace.isOn) {
/* 1610 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "v6isExternalObject(Object)", new Object[] { exitObject });
/*      */     }
/*      */     
/*      */     try {
/* 1614 */       boolean traceRet1 = ((Boolean)v6isExternalExitMethod.invoke(null, new Object[] { exitObject })).booleanValue();
/*      */       
/* 1616 */       if (Trace.isOn) {
/* 1617 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "v6isExternalObject(Object)", 
/* 1618 */             Boolean.valueOf(traceRet1), 1);
/*      */       }
/* 1620 */       return traceRet1;
/*      */     }
/* 1622 */     catch (IllegalArgumentException e) {
/* 1623 */       if (Trace.isOn) {
/* 1624 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "v6isExternalObject(Object)", e, 1);
/*      */       }
/*      */       
/* 1627 */       if (Trace.isOn) {
/* 1628 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "v6isExternalObject(Object)", 
/* 1629 */             Boolean.valueOf(false), 2);
/*      */       }
/* 1631 */       return false;
/*      */     }
/* 1633 */     catch (IllegalAccessException e) {
/* 1634 */       if (Trace.isOn) {
/* 1635 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "v6isExternalObject(Object)", e, 2);
/*      */       }
/*      */       
/* 1638 */       if (Trace.isOn) {
/* 1639 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "v6isExternalObject(Object)", 
/* 1640 */             Boolean.valueOf(false), 3);
/*      */       }
/* 1642 */       return false;
/*      */     }
/* 1644 */     catch (InvocationTargetException e) {
/* 1645 */       if (Trace.isOn) {
/* 1646 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "v6isExternalObject(Object)", e, 3);
/*      */       }
/*      */       
/* 1649 */       if (Trace.isOn) {
/* 1650 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "v6isExternalObject(Object)", 
/* 1651 */             Boolean.valueOf(false), 4);
/*      */       }
/* 1653 */       return false;
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
/*      */   private int processExitReturnBuffer(Object retBuffer, RfpTSH tsh, String exitName) throws JmqiException {
/* 1667 */     if (Trace.isOn) {
/* 1668 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "processExitReturnBuffer(Object,RfpTSH,String)", new Object[] { retBuffer, tsh, exitName });
/*      */     }
/*      */ 
/*      */     
/* 1672 */     if (retBuffer == null) {
/* 1673 */       JmqiException traceRet1 = new JmqiException(this.env, 9184, new String[] { "null (0)", null, exitName }, 2, 2407, null);
/*      */       
/* 1675 */       if (Trace.isOn) {
/* 1676 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "processExitReturnBuffer(Object,RfpTSH,String)", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 1679 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/* 1683 */     byte[] sourceBuffer = null;
/* 1684 */     int sourceOffset = 0;
/*      */ 
/*      */     
/* 1687 */     int sourceLength = 0;
/*      */     
/* 1689 */     if (retBuffer instanceof byte[]) {
/* 1690 */       sourceBuffer = (byte[])retBuffer;
/* 1691 */       sourceLength = sourceBuffer.length;
/* 1692 */       sourceOffset = 0;
/*      */     } else {
/* 1694 */       ByteBuffer retByteBuffer = (ByteBuffer)retBuffer;
/* 1695 */       sourceLength = retByteBuffer.position();
/* 1696 */       if (retByteBuffer.hasArray()) {
/* 1697 */         sourceBuffer = retByteBuffer.array();
/* 1698 */         sourceOffset = retByteBuffer.arrayOffset();
/*      */       } else {
/* 1700 */         sourceBuffer = new byte[sourceLength];
/* 1701 */         retByteBuffer.position(0);
/* 1702 */         retByteBuffer.get(sourceBuffer);
/* 1703 */         sourceOffset = 0;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1708 */     int destOffset = 0;
/*      */ 
/*      */     
/* 1711 */     int tshDataOffset = (tsh.getTshType() == 2) ? 16 : 8;
/*      */     
/* 1713 */     if (this.exitType == 11) {
/*      */       
/* 1715 */       destOffset = tsh.tshHdrSize() + 4;
/*      */     } else {
/*      */       
/* 1718 */       sourceOffset += 8;
/* 1719 */       sourceLength -= 8;
/*      */       
/* 1721 */       destOffset = tshDataOffset;
/*      */     } 
/*      */ 
/*      */     
/* 1725 */     int newTshLength = sourceLength + destOffset;
/*      */ 
/*      */     
/* 1728 */     int tshSpaceAvailable = (tsh.getRfpBuffer()).length - tsh.getRfpOffset();
/*      */ 
/*      */     
/* 1731 */     if (newTshLength > tshSpaceAvailable) {
/* 1732 */       byte[] oldTshBuff = tsh.getRfpBuffer();
/* 1733 */       int oldTshRfpOffset = tsh.getRfpOffset();
/* 1734 */       byte[] newTshBuff = resizeTSH(tsh, newTshLength);
/* 1735 */       System.arraycopy(oldTshBuff, oldTshRfpOffset, newTshBuff, 0, destOffset);
/*      */     } 
/*      */ 
/*      */     
/* 1739 */     System.arraycopy(sourceBuffer, sourceOffset, tsh.getRfpBuffer(), tsh.getRfpOffset() + destOffset, sourceLength);
/* 1740 */     tsh.setTransLength(newTshLength);
/* 1741 */     tsh.hardenTransLength();
/*      */     
/* 1743 */     if (Trace.isOn) {
/* 1744 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "processExitReturnBuffer(Object,RfpTSH,String)", 
/* 1745 */           Integer.valueOf(sourceLength));
/*      */     }
/* 1747 */     return sourceLength;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private byte[] resizeTSH(RfpTSH tsh, int newTshLength) {
/* 1758 */     if (Trace.isOn) {
/* 1759 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "resizeTSH(RfpTSH,int)", new Object[] { tsh, 
/* 1760 */             Integer.valueOf(newTshLength) });
/*      */     }
/* 1762 */     byte[] newTshBuff = new byte[newTshLength];
/* 1763 */     tsh.setRfpBuffer(newTshBuff);
/* 1764 */     tsh.setRfpOffset(0);
/*      */     
/* 1766 */     if (Trace.isOn) {
/* 1767 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "resizeTSH(RfpTSH,int)", newTshBuff);
/*      */     }
/*      */     
/* 1770 */     return newTshBuff;
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
/*      */   public int getExitFapLevel() {
/* 1782 */     if (Trace.isOn) {
/* 1783 */       Trace.data(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "getExitFapLevel()", "getter", 
/* 1784 */           Integer.valueOf(this.exitFapLevel));
/*      */     }
/* 1786 */     return this.exitFapLevel;
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
/*      */   private void parseSecurityExit(String mqcdExits, Object exitChain, MQCD mqcd, String exitClassPath, RemoteConnection connection, String exitUserData) throws JmqiException {
/* 1801 */     if (Trace.isOn) {
/* 1802 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "parseSecurityExit(String,Object,MQCD,String,RemoteConnection,String)", new Object[] { mqcdExits, exitChain, mqcd, exitClassPath, connection, exitUserData });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1811 */     if (mqcdExits != null && mqcdExits.trim().length() > 0) {
/* 1812 */       loadByName(mqcdExits, exitClassPath, connection, mqcd, exitUserData, true);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1821 */     if (exitChain != null)
/*      */     {
/* 1823 */       if (V7ExitDefinition.isLoadable(exitChain)) {
/* 1824 */         V7ExitDefinition.load(this.env, this, exitChain, exitUserData, connection, false);
/* 1825 */       } else if (v6IsLoadable(exitChain)) {
/* 1826 */         v6Load(this.env, this, exitChain, exitUserData, connection, false);
/* 1827 */       } else if (exitChain instanceof String) {
/* 1828 */         String exitName = ((String)exitChain).trim();
/* 1829 */         int exitNameLength = exitName.length();
/* 1830 */         if (exitNameLength > 0) {
/* 1831 */           char[] exitNameBuffer = new char[JmqiEnvironment.getMqExitNameLength()];
/*      */           
/* 1833 */           System.arraycopy(exitName.toCharArray(), 0, exitNameBuffer, 0, exitNameLength);
/* 1834 */           Arrays.fill(exitNameBuffer, exitNameLength, JmqiEnvironment.getMqExitNameLength(), ' ');
/*      */           
/* 1836 */           loadByName(new String(exitNameBuffer), exitClassPath, connection, mqcd, exitUserData, false);
/*      */         } 
/*      */       } else {
/*      */         
/* 1840 */         JmqiException traceRet1 = new JmqiException(this.env, 9535, new String[] { mqcd.getChannelName(), exitChain.getClass().getName() }, 2, 2406, null);
/*      */ 
/*      */         
/* 1843 */         if (Trace.isOn) {
/* 1844 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "parseSecurityExit(String,Object,MQCD,String,RemoteConnection,String)", (Throwable)traceRet1);
/*      */         }
/*      */         
/* 1847 */         throw traceRet1;
/*      */       } 
/*      */     }
/* 1850 */     if (Trace.isOn) {
/* 1851 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "parseSecurityExit(String,Object,MQCD,String,RemoteConnection,String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void v6Load(JmqiEnvironment env, RemoteExitChain parent, Object exitChain, String exitUserData, RemoteConnection connection, boolean mqcdExit) throws JmqiException {
/* 1859 */     if (Trace.isOn) {
/* 1860 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "v6Load(JmqiEnvironment,RemoteExitChain,Object,String,RemoteConnection,boolean)", new Object[] { env, parent, exitChain, exitUserData, connection, 
/*      */             
/* 1862 */             Boolean.valueOf(mqcdExit) });
/*      */     }
/*      */     
/*      */     try {
/* 1866 */       v6loadMethod.invoke(null, new Object[] { env, parent, exitChain, exitUserData, connection, Integer.valueOf(sizeofNativePointer), Boolean.valueOf(mqcdExit) });
/*      */     }
/* 1868 */     catch (Exception e) {
/* 1869 */       if (Trace.isOn) {
/* 1870 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "v6Load(JmqiEnvironment,RemoteExitChain,Object,String,RemoteConnection,boolean)", e);
/*      */       }
/*      */ 
/*      */       
/* 1874 */       JmqiException traceRet1 = new JmqiException(env, 9535, new String[] { exitChain.getClass().getName() }, 2, 2406, e);
/* 1875 */       if (Trace.isOn) {
/* 1876 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "v6Load(JmqiEnvironment,RemoteExitChain,Object,String,RemoteConnection,boolean)", (Throwable)traceRet1);
/*      */       }
/*      */ 
/*      */       
/* 1880 */       throw traceRet1;
/*      */     } 
/* 1882 */     if (Trace.isOn) {
/* 1883 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "v6Load(JmqiEnvironment,RemoteExitChain,Object,String,RemoteConnection,boolean)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean v6IsLoadable(Object exitChain) {
/* 1890 */     if (Trace.isOn) {
/* 1891 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "v6IsLoadable(Object)", new Object[] { exitChain });
/*      */     }
/*      */     
/*      */     try {
/* 1895 */       boolean traceRet1 = ((Boolean)v6isLoadableMethod.invoke(null, new Object[] { exitChain })).booleanValue();
/*      */       
/* 1897 */       if (Trace.isOn) {
/* 1898 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "v6IsLoadable(Object)", 
/* 1899 */             Boolean.valueOf(traceRet1), 1);
/*      */       }
/* 1901 */       return traceRet1;
/*      */     }
/* 1903 */     catch (IllegalArgumentException e) {
/* 1904 */       if (Trace.isOn) {
/* 1905 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "v6IsLoadable(Object)", e, 1);
/*      */       }
/*      */       
/* 1908 */       if (Trace.isOn) {
/* 1909 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "v6IsLoadable(Object)", 
/* 1910 */             Boolean.valueOf(false), 2);
/*      */       }
/* 1912 */       return false;
/*      */     }
/* 1914 */     catch (IllegalAccessException e) {
/* 1915 */       if (Trace.isOn) {
/* 1916 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "v6IsLoadable(Object)", e, 2);
/*      */       }
/*      */       
/* 1919 */       if (Trace.isOn) {
/* 1920 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "v6IsLoadable(Object)", 
/* 1921 */             Boolean.valueOf(false), 3);
/*      */       }
/* 1923 */       return false;
/*      */     }
/* 1925 */     catch (InvocationTargetException e) {
/* 1926 */       if (Trace.isOn) {
/* 1927 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "v6IsLoadable(Object)", e, 3);
/*      */       }
/*      */       
/* 1930 */       if (Trace.isOn) {
/* 1931 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "v6IsLoadable(Object)", 
/* 1932 */             Boolean.valueOf(false), 4);
/*      */       }
/* 1934 */       return false;
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
/*      */   private void parseSendReceiveExitsChain(String mqcdExitPtr, int mqcdExitsDefined, Object exitChain, MQCD mqcd, String exitClassPath, RemoteConnection connection, String exitUserData) throws JmqiException {
/* 1952 */     if (Trace.isOn) {
/* 1953 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "parseSendReceiveExitsChain(String,int,Object,MQCD,String,RemoteConnection,String)", new Object[] { mqcdExitPtr, 
/*      */             
/* 1955 */             Integer.valueOf(mqcdExitsDefined), exitChain, mqcd, exitClassPath, connection, exitUserData });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1961 */     if (mqcdExitPtr != null && mqcdExitPtr.trim().length() > 0 && mqcdExitsDefined > 0)
/*      */     {
/* 1963 */       if (mqcdExitsDefined > 1) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1969 */         if (mqcdExitPtr.length() == mqcdExitsDefined * JmqiEnvironment.getMqExitNameLength()) {
/* 1970 */           loadExitList(mqcdExitPtr, exitClassPath, connection, mqcd, exitUserData, true);
/*      */         } else {
/*      */           
/* 1973 */           JmqiException traceRet1 = new JmqiException(this.env, 9231, new String[] { mqcdExitPtr }, 2, 2323, null);
/*      */           
/* 1975 */           if (Trace.isOn) {
/* 1976 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "parseSendReceiveExitsChain(String,int,Object,MQCD,String,RemoteConnection,String)", (Throwable)traceRet1, 1);
/*      */           }
/*      */ 
/*      */           
/* 1980 */           throw traceRet1;
/*      */         } 
/*      */       } else {
/* 1983 */         loadByName(mqcdExitPtr, exitClassPath, connection, mqcd, exitUserData, true);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1991 */     if (exitChain != null)
/*      */     {
/* 1993 */       if (V7ExitDefinition.isLoadable(exitChain)) {
/* 1994 */         V7ExitDefinition.load(this.env, this, exitChain, exitUserData, connection, false);
/*      */       
/*      */       }
/* 1997 */       else if (v6IsLoadable(exitChain)) {
/* 1998 */         v6Load(this.env, this, exitChain, exitUserData, connection, false);
/*      */       
/*      */       }
/* 2001 */       else if (exitChain instanceof String) {
/* 2002 */         StringTokenizer exitTokenizer = new StringTokenizer((String)exitChain, ",");
/* 2003 */         StringTokenizer userDataTokenizer = new StringTokenizer((exitUserData != null) ? exitUserData : "", ",");
/* 2004 */         while (exitTokenizer.hasMoreTokens()) {
/* 2005 */           String exitList = exitTokenizer.nextToken();
/* 2006 */           String userData = null;
/* 2007 */           if (userDataTokenizer.hasMoreTokens()) {
/* 2008 */             userData = userDataTokenizer.nextToken();
/*      */           } else {
/* 2010 */             userData = "";
/*      */           } 
/* 2012 */           loadExitList(exitList, exitClassPath, connection, mqcd, userData, false);
/*      */         } 
/* 2014 */       } else if (exitChain instanceof List) {
/* 2015 */         Iterator<?> i = ((List)exitChain).iterator();
/*      */         
/* 2017 */         while (i.hasNext()) {
/* 2018 */           Object exitObject = i.next();
/* 2019 */           if (v6IsLoadable(exitObject)) {
/* 2020 */             v6Load(this.env, this, exitObject, exitUserData, connection, false); continue;
/* 2021 */           }  if (V7ExitDefinition.isLoadable(exitObject)) {
/* 2022 */             V7ExitDefinition.load(this.env, this, exitObject, exitUserData, connection, false); continue;
/* 2023 */           }  if (exitObject instanceof String) {
/* 2024 */             String exitList = (String)exitObject;
/* 2025 */             if (exitList.trim().length() > 0) {
/* 2026 */               loadExitList(exitList, exitClassPath, connection, mqcd, exitUserData, false);
/*      */             }
/*      */             continue;
/*      */           } 
/* 2030 */           JmqiException traceRet1 = new JmqiException(this.env, 9535, new String[] { mqcd.getChannelName() }, 2, 2406, null);
/* 2031 */           if (Trace.isOn) {
/* 2032 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "parseSendReceiveExitsChain(String,int,Object,MQCD,String,RemoteConnection,String)", (Throwable)traceRet1, 2);
/*      */           }
/*      */ 
/*      */           
/* 2036 */           throw traceRet1;
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 2041 */         JmqiException traceRet2 = new JmqiException(this.env, 9535, new String[] { mqcd.getChannelName() }, 2, 2406, null);
/* 2042 */         if (Trace.isOn) {
/* 2043 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "parseSendReceiveExitsChain(String,int,Object,MQCD,String,RemoteConnection,String)", (Throwable)traceRet2, 3);
/*      */         }
/*      */ 
/*      */         
/* 2047 */         throw traceRet2;
/*      */       } 
/*      */     }
/* 2050 */     if (Trace.isOn) {
/* 2051 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "parseSendReceiveExitsChain(String,int,Object,MQCD,String,RemoteConnection,String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static String alignToSize(String stringP, int size) {
/* 2058 */     if (Trace.isOn) {
/* 2059 */       Trace.entry("com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "alignToSize(String,int)", new Object[] { stringP, 
/* 2060 */             Integer.valueOf(size) });
/*      */     }
/* 2062 */     String string = stringP;
/* 2063 */     if (string == null) {
/* 2064 */       string = "";
/*      */     }
/* 2066 */     if (string.length() % size == 0) {
/* 2067 */       if (Trace.isOn) {
/* 2068 */         Trace.exit("com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "alignToSize(String,int)", string, 1);
/*      */       }
/*      */       
/* 2071 */       return string;
/*      */     } 
/* 2073 */     String traceRet1 = padToLength(string, size * (string.length() / size + 1));
/* 2074 */     if (Trace.isOn) {
/* 2075 */       Trace.exit("com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "alignToSize(String,int)", traceRet1, 2);
/*      */     }
/*      */     
/* 2078 */     return traceRet1;
/*      */   }
/*      */   
/*      */   static String padToLength(String string, int length) {
/* 2082 */     if (Trace.isOn) {
/* 2083 */       Trace.entry("com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "padToLength(String,int)", new Object[] { string, 
/* 2084 */             Integer.valueOf(length) });
/*      */     }
/* 2086 */     if (string.length() == length) {
/* 2087 */       if (Trace.isOn) {
/* 2088 */         Trace.exit("com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "padToLength(String,int)", string, 1);
/*      */       }
/*      */       
/* 2091 */       return string;
/*      */     } 
/* 2093 */     char[] resultArray = new char[length];
/* 2094 */     Arrays.fill(resultArray, ' ');
/* 2095 */     System.arraycopy(string.toCharArray(), 0, resultArray, 0, Math.min(length, string.length()));
/* 2096 */     String traceRet1 = new String(resultArray);
/* 2097 */     if (Trace.isOn) {
/* 2098 */       Trace.exit("com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "padToLength(String,int)", traceRet1, 2);
/*      */     }
/*      */     
/* 2101 */     return traceRet1;
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
/*      */   private void loadExitList(String exitList, String exitClassPath, RemoteConnection connection, MQCD mqcd, String userData, boolean mqcdExit) throws JmqiException {
/* 2117 */     if (Trace.isOn) {
/* 2118 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "loadExitList(String,String,RemoteConnection,MQCD,String,boolean)", new Object[] { exitList, exitClassPath, connection, mqcd, userData, 
/*      */             
/* 2120 */             Boolean.valueOf(mqcdExit) });
/*      */     }
/* 2122 */     String exitNameList = alignToSize(exitList, JmqiEnvironment.getMqExitNameLength());
/* 2123 */     String exitUserDataList = alignToSize(userData, 32);
/* 2124 */     int numberOfExits = exitNameList.length() / JmqiEnvironment.getMqExitNameLength();
/* 2125 */     for (int exitNumber = 0; exitNumber < numberOfExits; exitNumber++) {
/* 2126 */       String exitName = getElement(exitNameList, exitNumber, JmqiEnvironment.getMqExitNameLength());
/* 2127 */       String exitUserData = getElement(exitUserDataList, exitNumber, 32);
/* 2128 */       loadByName(exitName, exitClassPath, connection, mqcd, exitUserData, mqcdExit);
/*      */     } 
/*      */     
/* 2131 */     if (Trace.isOn) {
/* 2132 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "loadExitList(String,String,RemoteConnection,MQCD,String,boolean)");
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
/*      */   private void loadByName(String exitName, String exitClassPath, RemoteConnection connection, MQCD cd, String exitUserData, boolean mqcdExit) throws JmqiException {
/* 2155 */     if (Trace.isOn) {
/* 2156 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "loadByName(String,String,RemoteConnection,MQCD,String,boolean)", new Object[] { exitName, exitClassPath, connection, cd, exitUserData, 
/*      */             
/* 2158 */             Boolean.valueOf(mqcdExit) });
/*      */     }
/* 2160 */     String libName = null;
/* 2161 */     String funcName = null;
/* 2162 */     StringTokenizer tok = new StringTokenizer(exitName, "()");
/* 2163 */     libName = tok.nextToken().trim();
/* 2164 */     if (tok.hasMoreTokens()) {
/* 2165 */       funcName = tok.nextToken().trim();
/*      */     }
/* 2167 */     if (libName != null && libName.length() > 0) {
/* 2168 */       if (funcName != null && funcName.length() > 0) {
/* 2169 */         NativeExitDefinition.load(this.env, this, libName, funcName, exitUserData, connection, sizeofNativePointer, mqcdExit);
/*      */       } else {
/* 2171 */         loadJavaExitByName(libName, exitClassPath, cd, exitUserData, mqcdExit);
/*      */       } 
/*      */     }
/*      */     
/* 2175 */     if (Trace.isOn) {
/* 2176 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "loadByName(String,String,RemoteConnection,MQCD,String,boolean)");
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
/*      */   private void loadJavaExitByName(String exitName, String exitClassPath, MQCD cd, String exitUserDataP, boolean mqcdExit) throws JmqiException {
/* 2194 */     if (Trace.isOn) {
/* 2195 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "loadJavaExitByName(String,String,MQCD,String,boolean)", new Object[] { exitName, exitClassPath, cd, exitUserDataP, 
/*      */             
/* 2197 */             Boolean.valueOf(mqcdExit) });
/*      */     }
/* 2199 */     String exitUserData = exitUserDataP;
/*      */     
/* 2201 */     final URL[] classpath = getWMQExitClasspath(exitClassPath);
/*      */ 
/*      */     
/* 2204 */     URLClassLoader exitClassloader = AccessController.<URLClassLoader>doPrivileged(new PrivilegedAction<URLClassLoader>()
/*      */         {
/*      */           public URLClassLoader run()
/*      */           {
/* 2208 */             if (Trace.isOn) {
/* 2209 */               Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "run()");
/*      */             }
/*      */ 
/*      */             
/* 2213 */             URLClassLoader traceRet1 = new URLClassLoader(classpath, RemoteExitChain.class.getClassLoader());
/* 2214 */             if (Trace.isOn) {
/* 2215 */               Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.null", "run()", traceRet1);
/*      */             }
/* 2217 */             return traceRet1;
/*      */           }
/*      */         });
/*      */     
/* 2221 */     Class<?> exitClass = null;
/* 2222 */     Object exitObject = null;
/*      */     try {
/*      */       try {
/* 2225 */         exitClass = exitClassloader.loadClass(exitName);
/*      */       }
/* 2227 */       catch (ClassNotFoundException cnfe) {
/* 2228 */         if (Trace.isOn) {
/* 2229 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "loadJavaExitByName(String,String,MQCD,String,boolean)", cnfe, 1);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         try {
/* 2235 */           exitClassloader = new URLClassLoader(classpath, Thread.currentThread().getContextClassLoader());
/* 2236 */           exitClass = exitClassloader.loadClass(exitName);
/*      */         }
/* 2238 */         catch (ClassNotFoundException e) {
/* 2239 */           if (Trace.isOn) {
/* 2240 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "loadJavaExitByName(String,String,MQCD,String,boolean)", e, 2);
/*      */           }
/*      */           
/* 2243 */           JmqiException traceRet1 = new JmqiException(this.env, 9535, new String[] { exitName }, 2, 2406, e);
/* 2244 */           if (exitClassloader != null) {
/* 2245 */             exitClassloader.close();
/*      */           }
/* 2247 */           if (Trace.isOn) {
/* 2248 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "loadJavaExitByName(String,String,MQCD,String,boolean)", (Throwable)traceRet1, 1);
/*      */           }
/*      */           
/* 2251 */           throw traceRet1;
/*      */         }
/*      */       
/*      */       } 
/* 2255 */     } catch (Throwable e) {
/* 2256 */       if (Trace.isOn) {
/* 2257 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "loadJavaExitByName(String,String,MQCD,String,boolean)", e, 3);
/*      */       }
/*      */ 
/*      */       
/* 2261 */       JmqiException traceRet1 = new JmqiException(this.env, 9535, new String[] { exitName }, 2, 2406, e);
/* 2262 */       if (Trace.isOn) {
/* 2263 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "loadJavaExitByName(String,String,MQCD,String,boolean)", (Throwable)traceRet1, 2);
/*      */       }
/*      */       
/* 2266 */       throw traceRet1;
/*      */     } 
/*      */     
/*      */     try {
/* 2270 */       if (mqcdExit)
/*      */       {
/*      */ 
/*      */         
/* 2274 */         if (exitUserData == null || exitUserData.length() == 0) {
/* 2275 */           if (this.exitType == 13) {
/* 2276 */             exitUserData = getElement(cd.getSendUserDataPtr(), exitCount(), cd.getExitDataLength());
/* 2277 */           } else if (this.exitType == 14) {
/* 2278 */             exitUserData = getElement(cd.getReceiveUserDataPtr(), exitCount(), cd.getExitDataLength());
/* 2279 */           } else if (this.exitType == 11) {
/* 2280 */             exitUserData = cd.getSecurityUserData();
/*      */           } 
/*      */         }
/*      */       }
/* 2284 */       if (exitUserData == null) {
/* 2285 */         if (Trace.isOn) {
/* 2286 */           Trace.data(this, "loadJavaExitByName(String,String,MQCD,String,boolean)", "exitUserData is null, setting to blank");
/*      */         }
/* 2288 */         exitUserData = "";
/*      */       } 
/* 2290 */       Constructor<?> construct = exitClass.getConstructor(new Class[] { String.class });
/* 2291 */       exitObject = construct.newInstance(new Object[] { exitUserData.trim() });
/*      */     }
/* 2293 */     catch (NoSuchMethodException nsme) {
/* 2294 */       if (Trace.isOn) {
/* 2295 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "loadJavaExitByName(String,String,MQCD,String,boolean)", nsme, 4);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 2301 */         exitObject = exitClass.newInstance();
/*      */       }
/* 2303 */       catch (Exception e) {
/* 2304 */         if (Trace.isOn) {
/* 2305 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "loadJavaExitByName(String,String,MQCD,String,boolean)", e, 5);
/*      */         }
/*      */         
/* 2308 */         JmqiException traceRet2 = new JmqiException(this.env, 9535, new String[] { cd.getChannelName(), exitName }, 2, 2406, e);
/* 2309 */         if (Trace.isOn) {
/* 2310 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "loadJavaExitByName(String,String,MQCD,String,boolean)", (Throwable)traceRet2, 3);
/*      */         }
/*      */         
/* 2313 */         throw traceRet2;
/*      */       }
/*      */     
/* 2316 */     } catch (Exception e) {
/* 2317 */       if (Trace.isOn) {
/* 2318 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "loadJavaExitByName(String,String,MQCD,String,boolean)", e, 6);
/*      */       }
/*      */       
/* 2321 */       JmqiException traceRet3 = new JmqiException(this.env, 9535, new String[] { cd.getChannelName(), exitName }, 2, 2406, e);
/* 2322 */       if (Trace.isOn) {
/* 2323 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "loadJavaExitByName(String,String,MQCD,String,boolean)", (Throwable)traceRet3, 4);
/*      */       }
/*      */       
/* 2326 */       throw traceRet3;
/*      */     } 
/*      */     
/* 2329 */     if (V7ExitDefinition.isLoadable(exitObject)) {
/* 2330 */       V7ExitDefinition.load(this.env, this, exitObject, exitUserData, null, mqcdExit);
/* 2331 */     } else if (v6IsLoadable(exitObject)) {
/* 2332 */       v6Load(this.env, this, exitObject, exitUserData, null, mqcdExit);
/*      */     } 
/* 2334 */     if (Trace.isOn) {
/* 2335 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "loadJavaExitByName(String,String,MQCD,String,boolean)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   JmqiCodepage getNativeCp() {
/* 2342 */     if (Trace.isOn) {
/* 2343 */       Trace.data(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "getNativeCp()", "getter", this.nativeCp);
/*      */     }
/*      */     
/* 2346 */     return this.nativeCp;
/*      */   }
/*      */   
/*      */   boolean isNativeSwap() {
/* 2350 */     if (Trace.isOn) {
/* 2351 */       Trace.data(this, "com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "isNativeSwap()", "getter", 
/* 2352 */           Boolean.valueOf(this.nativeSwap));
/*      */     }
/* 2354 */     return this.nativeSwap;
/*      */   }
/*      */   
/*      */   static boolean isNativeLibraryLoaded() {
/* 2358 */     loadNativeStubLibrary();
/* 2359 */     if (Trace.isOn) {
/* 2360 */       Trace.data("com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "isNativeLibraryLoaded()", "getter", 
/* 2361 */           Boolean.valueOf(nativeLibraryLoaded));
/*      */     }
/* 2363 */     return nativeLibraryLoaded;
/*      */   }
/*      */   
/*      */   static Throwable getLoadingError() {
/* 2367 */     if (Trace.isOn) {
/* 2368 */       Trace.data("com.ibm.mq.jmqi.remote.exit.RemoteExitChain", "getLoadingError()", "getter", loadingError);
/*      */     }
/*      */     
/* 2371 */     return loadingError;
/*      */   }
/*      */   
/*      */   public static native String getExitClasspath();
/*      */   
/*      */   protected static native int jniGetPtrSize();
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\exit\RemoteExitChain.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */