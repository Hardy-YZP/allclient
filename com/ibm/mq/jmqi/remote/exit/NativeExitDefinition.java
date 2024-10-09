/*     */ package com.ibm.mq.jmqi.remote.exit;
/*     */ 
/*     */ import com.ibm.mq.MQExternalUserExit;
/*     */ import com.ibm.mq.exits.MQCD;
/*     */ import com.ibm.mq.exits.MQCXP;
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.mq.jmqi.internal.Configuration;
/*     */ import com.ibm.mq.jmqi.remote.impl.RemoteConnection;
/*     */ import com.ibm.mq.jmqi.remote.rfp.RfpTSH;
/*     */ import com.ibm.mq.jmqi.system.JmqiTls;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.nio.ByteBuffer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class NativeExitDefinition
/*     */   extends ExitDefinition
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/exit/NativeExitDefinition.java";
/*     */   
/*     */   static {
/*  56 */     if (Trace.isOn) {
/*  57 */       Trace.data("com.ibm.mq.jmqi.remote.exit.NativeExitDefinition", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/exit/NativeExitDefinition.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  67 */   private int sizeofNativePointer = 0;
/*     */   
/*  69 */   private String libraryName = null;
/*  70 */   private String functionName = null;
/*  71 */   private JniParameters jniParms = null;
/*     */   
/*  73 */   private Object externalExit = null;
/*     */   
/*     */   JniParameters getJniParms() {
/*  76 */     if (Trace.isOn) {
/*  77 */       Trace.data(this, "com.ibm.mq.jmqi.remote.exit.NativeExitDefinition", "getJniParms()", "getter", this.jniParms);
/*     */     }
/*     */     
/*  80 */     return this.jniParms;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private NativeExitDefinition(JmqiEnvironment env, RemoteExitChain parent, String exitName, String exitUserData, boolean mqcdExit, int exitSpace, String libraryName, String functionName, JniParameters jniParms, int sizeofNativePointer, JmqiObject externalExit) {
/*  96 */     super(env, parent, exitName, exitUserData, mqcdExit, exitSpace);
/*  97 */     if (Trace.isOn) {
/*  98 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.NativeExitDefinition", "<init>(JmqiEnvironment,RemoteExitChain,String,String,boolean,int,String,String,JniParameters,int,JmqiObject)", new Object[] { env, parent, exitName, exitUserData, 
/*     */             
/* 100 */             Boolean.valueOf(mqcdExit), 
/* 101 */             Integer.valueOf(exitSpace), libraryName, functionName, jniParms, 
/* 102 */             Integer.valueOf(sizeofNativePointer), externalExit });
/*     */     }
/* 104 */     this.libraryName = libraryName;
/* 105 */     this.functionName = functionName;
/* 106 */     this.jniParms = jniParms;
/* 107 */     this.sizeofNativePointer = sizeofNativePointer;
/* 108 */     this.externalExit = externalExit;
/*     */     
/* 110 */     if (Trace.isOn) {
/* 111 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.NativeExitDefinition", "<init>(JmqiEnvironment,RemoteExitChain,String,String,boolean,int,String,String,JniParameters,int,JmqiObject)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected byte[] getDllHandle() {
/* 119 */     if (Trace.isOn) {
/* 120 */       Trace.data(this, "com.ibm.mq.jmqi.remote.exit.NativeExitDefinition", "getDllHandle()", "getter", this.jniParms.dllHandle);
/*     */     }
/*     */     
/* 123 */     return this.jniParms.dllHandle;
/*     */   }
/*     */   
/*     */   protected byte[] getFnPointer() {
/* 127 */     if (Trace.isOn) {
/* 128 */       Trace.data(this, "com.ibm.mq.jmqi.remote.exit.NativeExitDefinition", "getFnPointer()", "getter", this.jniParms.fnPointer);
/*     */     }
/*     */     
/* 131 */     return this.jniParms.fnPointer;
/*     */   }
/*     */ 
/*     */   
/*     */   protected ByteBuffer allocateByteBuffer(int bufferSize) {
/* 136 */     if (Trace.isOn)
/* 137 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.NativeExitDefinition", "allocateByteBuffer(int)", new Object[] {
/* 138 */             Integer.valueOf(bufferSize)
/*     */           }); 
/* 140 */     ByteBuffer traceRet1 = ByteBuffer.allocateDirect(bufferSize);
/*     */     
/* 142 */     if (Trace.isOn) {
/* 143 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.NativeExitDefinition", "allocateByteBuffer(int)", traceRet1);
/*     */     }
/*     */     
/* 146 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void load(JmqiEnvironment env, RemoteExitChain parent, String libName, String funcName, String exitUserData, RemoteConnection connection, int sizeofNativePointer, boolean mqcdExit) throws JmqiException {
/* 163 */     if (Trace.isOn) {
/* 164 */       Trace.entry("com.ibm.mq.jmqi.remote.exit.NativeExitDefinition", "load(JmqiEnvironment,RemoteExitChain,String,String,String,RemoteConnection,int,boolean)", new Object[] { env, parent, libName, funcName, exitUserData, connection, 
/*     */ 
/*     */             
/* 167 */             Integer.valueOf(sizeofNativePointer), Boolean.valueOf(mqcdExit) });
/*     */     }
/* 169 */     load(env, parent, libName, funcName, exitUserData, connection, sizeofNativePointer, mqcdExit, (JmqiObject)null);
/*     */     
/* 171 */     if (Trace.isOn) {
/* 172 */       Trace.exit("com.ibm.mq.jmqi.remote.exit.NativeExitDefinition", "load(JmqiEnvironment,RemoteExitChain,String,String,String,RemoteConnection,int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void load(JmqiEnvironment env, RemoteExitChain parent, MQExternalUserExit externalExit, RemoteConnection connection, int sizeofNativePointer, boolean mqcdExit) throws JmqiException {
/* 193 */     if (Trace.isOn) {
/* 194 */       Trace.entry("com.ibm.mq.jmqi.remote.exit.NativeExitDefinition", "load(JmqiEnvironment,RemoteExitChain,MQExternalUserExit,RemoteConnection,int,boolean)", new Object[] { env, parent, externalExit, connection, 
/*     */             
/* 196 */             Integer.valueOf(sizeofNativePointer), 
/* 197 */             Boolean.valueOf(mqcdExit) });
/*     */     }
/* 199 */     load(env, parent, externalExit.getLibraryName(), externalExit.getEntryPointName(), externalExit.getUserData(), connection, sizeofNativePointer, mqcdExit, (JmqiObject)externalExit);
/*     */     
/* 201 */     if (Trace.isOn) {
/* 202 */       Trace.exit("com.ibm.mq.jmqi.remote.exit.NativeExitDefinition", "load(JmqiEnvironment,RemoteExitChain,MQExternalUserExit,RemoteConnection,int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void load(JmqiEnvironment env, RemoteExitChain parent, String libName, String funcName, String exitUserData, RemoteConnection connection, int sizeofNativePointer, boolean mqcdExit, JmqiObject externalExit) throws JmqiException {
/*     */     JmqiException traceRet2;
/* 224 */     if (Trace.isOn) {
/* 225 */       Trace.entry("com.ibm.mq.jmqi.remote.exit.NativeExitDefinition", "load(JmqiEnvironment,RemoteExitChain,String,String,String,RemoteConnection,int,boolean,JmqiObject)", new Object[] { env, parent, libName, funcName, exitUserData, connection, 
/*     */ 
/*     */             
/* 228 */             Integer.valueOf(sizeofNativePointer), Boolean.valueOf(mqcdExit), externalExit });
/*     */     }
/*     */ 
/*     */     
/* 232 */     if (!RemoteExitChain.isNativeLibraryLoaded()) {
/*     */       
/* 234 */       JmqiException traceRet1 = new JmqiException(env, 9535, new String[] { libName, funcName }, 2, 2406, RemoteExitChain.getLoadingError());
/*     */       
/* 236 */       if (Trace.isOn) {
/* 237 */         Trace.throwing("com.ibm.mq.jmqi.remote.exit.NativeExitDefinition", "load(JmqiEnvironment,RemoteExitChain,String,String,String,RemoteConnection,int,boolean,JmqiObject)", (Throwable)traceRet1, 1);
/*     */       }
/*     */ 
/*     */       
/* 241 */       throw traceRet1;
/*     */     } 
/*     */     
/* 244 */     JniParameters jniParms = new JniParameters();
/*     */ 
/*     */     
/* 247 */     NativeExitDefinition nativeExit = new NativeExitDefinition(env, parent, libName + "(" + funcName + ")", exitUserData, mqcdExit, 0, libName, funcName, jniParms, sizeofNativePointer, externalExit);
/*     */ 
/*     */     
/* 250 */     Configuration myCfg = new Configuration(env);
/* 251 */     String exitsDefaultPath = myCfg.getStringValue(Configuration.CLIENTEXITPATH_EXITSDEFAULTPATH);
/* 252 */     String exitsDefaultPath64 = myCfg.getStringValue(Configuration.CLIENTEXITPATH_EXITSDEFAULTPATH64);
/*     */     
/* 254 */     int reply = jniLoadExit(libName, funcName, jniParms, exitsDefaultPath, exitsDefaultPath64, Trace.isOn);
/*     */     
/* 256 */     switch (reply) {
/*     */       case 0:
/* 258 */         parent.addExitDefinition(nativeExit);
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       default:
/* 264 */         traceRet2 = new JmqiException(env, 9535, new String[] { libName, funcName }, 2, reply, null);
/* 265 */         if (Trace.isOn) {
/* 266 */           Trace.throwing("com.ibm.mq.jmqi.remote.exit.NativeExitDefinition", "load(JmqiEnvironment,RemoteExitChain,String,String,String,RemoteConnection,int,boolean,JmqiObject)", (Throwable)traceRet2, 2);
/*     */         }
/*     */ 
/*     */         
/* 270 */         throw traceRet2;
/*     */     } 
/*     */     
/* 273 */     if (Trace.isOn) {
/* 274 */       Trace.exit("com.ibm.mq.jmqi.remote.exit.NativeExitDefinition", "load(JmqiEnvironment,RemoteExitChain,String,String,String,RemoteConnection,int,boolean,JmqiObject)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void packMQCD(JmqiTls jTls, MQCD cd, byte[] mqcdStruct) throws JmqiException {
/* 291 */     if (Trace.isOn) {
/* 292 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.NativeExitDefinition", "packMQCD(JmqiTls,MQCD)", new Object[] { jTls, cd });
/*     */     }
/*     */     
/* 295 */     cd.writeToBuffer(mqcdStruct, 0, this.sizeofNativePointer, this.parent.isNativeSwap(), this.parent.getNativeCp(), jTls);
/* 296 */     this.jniParms.exitNameLength = cd.getExitNameLength();
/* 297 */     this.jniParms.exitDataLength = cd.getExitDataLength();
/* 298 */     this.jniParms.msgExitsDefined = cd.getMsgExitsDefined();
/* 299 */     this.jniParms.msgExitPtr = cd.getMsgExitPtrBytes();
/* 300 */     this.jniParms.msgUserDataPtr = cd.getMsgUserDataPtrBytes();
/* 301 */     switch (this.exitType) {
/*     */       case 13:
/* 303 */         this.jniParms.sendExitsDefined = this.parent.exitCount();
/* 304 */         this.jniParms.sendExitPtr = cd.getSendExitPtrBytes();
/* 305 */         this.jniParms.sendUserDataPtr = cd.getSendUserDataPtrBytes();
/*     */         break;
/*     */       case 14:
/* 308 */         this.jniParms.receiveExitsDefined = this.parent.exitCount();
/* 309 */         this.jniParms.receiveExitPtr = cd.getReceiveExitPtrBytes();
/* 310 */         this.jniParms.receiveUserDataPtr = cd.getReceiveUserDataPtrBytes();
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 317 */     if (Trace.isOn) {
/* 318 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.NativeExitDefinition", "packMQCD(JmqiTls,MQCD)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void unpackMQCD(JmqiTls jTls, MQCD cd, byte[] mqcdStruct) throws JmqiException {
/* 334 */     if (Trace.isOn) {
/* 335 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.NativeExitDefinition", "unpackMQCD(JmqiTls,MQCD)", new Object[] { jTls, cd });
/*     */     }
/*     */     
/* 338 */     cd.readFromBuffer(mqcdStruct, 0, this.sizeofNativePointer, this.parent.isNativeSwap(), this.parent.getNativeCp(), jTls);
/*     */     
/* 340 */     if (Trace.isOn) {
/* 341 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.NativeExitDefinition", "unpackMQCD(JmqiTls,MQCD)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Object invoke(JmqiTls jTls, RfpTSH tsh, MQCD cd, MQCXP mqcxp, byte[] mqcspStruct, boolean securityFirstInvocation) throws JmqiException {
/* 352 */     if (Trace.isOn) {
/* 353 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.NativeExitDefinition", "invoke(JmqiTls,RfpTSH,MQCD,MQCXP,byte [ ],boolean)", new Object[] { jTls, tsh, cd, mqcxp, mqcspStruct, 
/*     */             
/* 355 */             Boolean.valueOf(securityFirstInvocation) });
/*     */     }
/*     */     
/* 358 */     byte[] mqcxpStruct = new byte[mqcxp.getRequiredBufferSize(this.sizeofNativePointer, null)];
/* 359 */     byte[] mqcdStruct = new byte[cd.getRequiredBufferSize(this.sizeofNativePointer, null)];
/*     */     
/* 361 */     packMQCD(jTls, cd, mqcdStruct);
/*     */     
/* 363 */     mqcxp.writeToBuffer(mqcxpStruct, 0, this.sizeofNativePointer, this.parent.isNativeSwap(), this.parent.getNativeCp(), jTls);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 378 */     ByteBuffer buffer = null;
/* 379 */     if (tsh != null) {
/*     */ 
/*     */ 
/*     */       
/* 383 */       buffer = prepareBuffer(tsh, false, 3968);
/* 384 */       this.jniParms.dataLength = buffer.position();
/*     */     } else {
/*     */       
/* 387 */       buffer = prepareBuffer((RfpTSH)null, true, 3968);
/* 388 */       this.jniParms.dataLength = 0;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 395 */     this.jniParms.sslRemCertIssName = mqcxp.getSslRemCertIssName();
/* 396 */     this.jniParms.longMCAUserId = cd.getMcaUserIdentifier();
/* 397 */     this.jniParms.longRemoteUserId = cd.getRemoteUserIdentifier();
/* 398 */     this.jniParms.sslPeerName = cd.getSslPeerName();
/*     */     
/* 400 */     int reply = jniCallExit(mqcxpStruct, mqcdStruct, mqcspStruct, buffer, getDllHandle(), getFnPointer(), this.jniParms, Trace.isOn);
/* 401 */     if (reply != 0) {
/* 402 */       JmqiException traceRet1 = new JmqiException(this.env, 9535, new String[] { cd.getChannelName(), getExitName() }, 2, reply, null);
/*     */       
/* 404 */       if (Trace.isOn) {
/* 405 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.exit.NativeExitDefinition", "invoke(JmqiTls,RfpTSH,MQCD,MQCXP,byte [ ],boolean)", (Throwable)traceRet1);
/*     */       }
/*     */       
/* 408 */       throw traceRet1;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 413 */     mqcxp.readFromBuffer(mqcxpStruct, 0, this.sizeofNativePointer, this.parent.isNativeSwap(), this.parent.getNativeCp(), jTls);
/*     */ 
/*     */     
/* 416 */     unpackMQCD(jTls, cd, mqcdStruct);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 426 */     cd.setMcaUserIdentifier(this.jniParms.longMCAUserId);
/* 427 */     cd.setRemoteUserIdentifier(this.jniParms.longRemoteUserId);
/* 428 */     cd.setSslPeerName(this.jniParms.sslPeerName);
/*     */ 
/*     */ 
/*     */     
/* 432 */     if ((mqcxp.getExitResponse2() & 0x4) != 0) {
/* 433 */       Object traceRet2 = this.jniParms.exitBuffer.position(this.jniParms.dataLength);
/* 434 */       if (Trace.isOn) {
/* 435 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.NativeExitDefinition", "invoke(JmqiTls,RfpTSH,MQCD,MQCXP,byte [ ],boolean)", traceRet2, 1);
/*     */       }
/*     */       
/* 438 */       return traceRet2;
/*     */     } 
/*     */     
/* 441 */     Object traceRet3 = buffer.position(this.jniParms.dataLength);
/* 442 */     if (Trace.isOn) {
/* 443 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.NativeExitDefinition", "invoke(JmqiTls,RfpTSH,MQCD,MQCXP,byte [ ],boolean)", traceRet3, 2);
/*     */     }
/*     */     
/* 446 */     return traceRet3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 454 */     if (Trace.isOn) {
/* 455 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.NativeExitDefinition", "toString()");
/*     */     }
/*     */     
/* 458 */     String traceRet1 = super.toString() + "\nNative Exit: " + this.libraryName + "(" + this.functionName + ")";
/* 459 */     if (Trace.isOn) {
/* 460 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.NativeExitDefinition", "toString()", traceRet1);
/*     */     }
/*     */     
/* 463 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object getObject() {
/* 473 */     if (Trace.isOn) {
/* 474 */       Trace.data(this, "com.ibm.mq.jmqi.remote.exit.NativeExitDefinition", "getObject()", "getter", this.externalExit);
/*     */     }
/*     */     
/* 477 */     return this.externalExit;
/*     */   }
/*     */   
/*     */   protected static native int jniLoadExit(String paramString1, String paramString2, JniParameters paramJniParameters, String paramString3, String paramString4, boolean paramBoolean);
/*     */   
/*     */   protected native int jniCallExit(byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, byte[] paramArrayOfbyte3, ByteBuffer paramByteBuffer, byte[] paramArrayOfbyte4, byte[] paramArrayOfbyte5, JniParameters paramJniParameters, boolean paramBoolean);
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\exit\NativeExitDefinition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */