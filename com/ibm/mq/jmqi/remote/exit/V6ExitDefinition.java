/*     */ package com.ibm.mq.jmqi.remote.exit;
/*     */ 
/*     */ import com.ibm.mq.MQChannelDefinition;
/*     */ import com.ibm.mq.MQChannelExit;
/*     */ import com.ibm.mq.MQConnectionSecurityParameters;
/*     */ import com.ibm.mq.MQExitChain;
/*     */ import com.ibm.mq.MQExternalUserExit;
/*     */ import com.ibm.mq.MQReceiveExit;
/*     */ import com.ibm.mq.MQSecurityExit;
/*     */ import com.ibm.mq.MQSendExit;
/*     */ import com.ibm.mq.exits.MQCD;
/*     */ import com.ibm.mq.exits.MQCSP;
/*     */ import com.ibm.mq.exits.MQCXP;
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.remote.impl.RemoteConnection;
/*     */ import com.ibm.mq.jmqi.remote.rfp.RfpTSH;
/*     */ import com.ibm.mq.jmqi.system.JmqiTls;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Vector;
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
/*     */ class V6ExitDefinition
/*     */   extends JavaExitDefinition
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/exit/V6ExitDefinition.java";
/*     */   
/*     */   static {
/*  55 */     if (Trace.isOn) {
/*  56 */       Trace.data("com.ibm.mq.jmqi.remote.exit.V6ExitDefinition", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/exit/V6ExitDefinition.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private V6ExitDefinition(JmqiEnvironment env, RemoteExitChain parent, String exitName, String exitUserData, boolean mqcdExit, int exitSpace, Object exitObject) {
/*  67 */     super(env, parent, exitName, exitUserData, mqcdExit, exitSpace, exitObject);
/*  68 */     if (Trace.isOn) {
/*  69 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.V6ExitDefinition", "<init>(JmqiEnvironment,RemoteExitChain,String,String,boolean,int,Object)", new Object[] { env, parent, exitName, exitUserData, 
/*     */             
/*  71 */             Boolean.valueOf(mqcdExit), Integer.valueOf(exitSpace), exitObject });
/*     */     }
/*     */     
/*  74 */     if (Trace.isOn) {
/*  75 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.V6ExitDefinition", "<init>(JmqiEnvironment,RemoteExitChain,String,String,boolean,int,Object)");
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
/*     */   static boolean isLoadable(Object exitObject) {
/*  88 */     if (Trace.isOn) {
/*  89 */       Trace.entry("com.ibm.mq.jmqi.remote.exit.V6ExitDefinition", "isLoadable(Object)", new Object[] { exitObject });
/*     */     }
/*     */     
/*  92 */     boolean traceRet1 = (exitObject instanceof MQSendExit || exitObject instanceof MQSecurityExit || exitObject instanceof MQReceiveExit || exitObject instanceof MQExitChain || exitObject instanceof MQExternalUserExit);
/*     */     
/*  94 */     if (Trace.isOn) {
/*  95 */       Trace.exit("com.ibm.mq.jmqi.remote.exit.V6ExitDefinition", "isLoadable(Object)", 
/*  96 */           Boolean.valueOf(traceRet1));
/*     */     }
/*  98 */     return traceRet1;
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
/*     */   static void load(JmqiEnvironment env, RemoteExitChain parent, Object exitObject, String exitUserData, RemoteConnection connection, int sizeofNativePointer, boolean mqcdExit) throws JmqiException {
/* 114 */     if (Trace.isOn) {
/* 115 */       Trace.entry("com.ibm.mq.jmqi.remote.exit.V6ExitDefinition", "load(JmqiEnvironment,RemoteExitChain,Object,String,RemoteConnection,int,boolean)", new Object[] { env, parent, exitObject, exitUserData, connection, 
/*     */ 
/*     */             
/* 118 */             Integer.valueOf(sizeofNativePointer), Boolean.valueOf(mqcdExit) });
/*     */     }
/* 120 */     if (exitObject instanceof MQExternalUserExit) {
/*     */       
/* 122 */       MQExternalUserExit extUserExit = (MQExternalUserExit)exitObject;
/* 123 */       NativeExitDefinition.load(env, parent, extUserExit, connection, sizeofNativePointer, mqcdExit);
/*     */     }
/* 125 */     else if (exitObject instanceof MQExitChain) {
/* 126 */       loadExitChain(env, parent, (MQExitChain)exitObject, exitUserData, connection, sizeofNativePointer, mqcdExit);
/*     */     } else {
/*     */       
/* 129 */       ExitDefinition exitDef = new V6ExitDefinition(env, parent, RemoteExitChain.padToLength(exitObject.getClass().getName(), JmqiEnvironment.getMqExitNameLength()), exitUserData, mqcdExit, 0, exitObject);
/*     */       
/* 131 */       parent.addExitDefinition(exitDef);
/*     */     } 
/*     */     
/* 134 */     if (Trace.isOn) {
/* 135 */       Trace.exit("com.ibm.mq.jmqi.remote.exit.V6ExitDefinition", "load(JmqiEnvironment,RemoteExitChain,Object,String,RemoteConnection,int,boolean)");
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
/*     */   static boolean isExternalExit(Object exitObject) {
/* 148 */     if (Trace.isOn) {
/* 149 */       Trace.entry("com.ibm.mq.jmqi.remote.exit.V6ExitDefinition", "isExternalExit(Object)", new Object[] { exitObject });
/*     */     }
/*     */     
/* 152 */     boolean traceRet1 = exitObject instanceof MQExternalUserExit;
/* 153 */     if (Trace.isOn) {
/* 154 */       Trace.exit("com.ibm.mq.jmqi.remote.exit.V6ExitDefinition", "isExternalExit(Object)", 
/* 155 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 157 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static String getExternalExitUserData(Object exitObject) {
/* 167 */     if (Trace.isOn) {
/* 168 */       Trace.entry("com.ibm.mq.jmqi.remote.exit.V6ExitDefinition", "getExternalExitUserData(Object)", new Object[] { exitObject });
/*     */     }
/*     */     
/* 171 */     String traceRet1 = ((MQExternalUserExit)exitObject).getUserData();
/* 172 */     if (Trace.isOn) {
/* 173 */       Trace.exit("com.ibm.mq.jmqi.remote.exit.V6ExitDefinition", "getExternalExitUserData(Object)", traceRet1);
/*     */     }
/*     */     
/* 176 */     return traceRet1;
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
/*     */   private static void loadExitChain(JmqiEnvironment env, RemoteExitChain parent, MQExitChain exitChain, String exitUserData, RemoteConnection connection, int sizeofNativePointer, boolean mqcdExit) throws JmqiException {
/* 189 */     if (Trace.isOn) {
/* 190 */       Trace.entry("com.ibm.mq.jmqi.remote.exit.V6ExitDefinition", "loadExitChain(JmqiEnvironment,RemoteExitChain,MQExitChain,String,RemoteConnection,int,boolean)", new Object[] { env, parent, exitChain, exitUserData, connection, 
/*     */ 
/*     */             
/* 193 */             Integer.valueOf(sizeofNativePointer), Boolean.valueOf(mqcdExit) });
/*     */     }
/* 195 */     List<?> exitList = exitChain.getExitChain();
/* 196 */     if (exitList != null) {
/* 197 */       Iterator<?> iterator = exitList.iterator();
/* 198 */       while (iterator.hasNext()) {
/* 199 */         Object exitObject = iterator.next();
/* 200 */         load(env, parent, exitObject, exitUserData, connection, sizeofNativePointer, mqcdExit);
/*     */       } 
/*     */     } 
/*     */     
/* 204 */     if (Trace.isOn) {
/* 205 */       Trace.exit("com.ibm.mq.jmqi.remote.exit.V6ExitDefinition", "loadExitChain(JmqiEnvironment,RemoteExitChain,MQExitChain,String,RemoteConnection,int,boolean)");
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
/*     */   protected byte[] prepareByteArray(RfpTSH tsh) {
/* 219 */     if (Trace.isOn) {
/* 220 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.V6ExitDefinition", "prepareByteArray(RfpTSH)", new Object[] { tsh });
/*     */     }
/*     */     
/* 223 */     byte[] traceRet1 = prepareBuffer(tsh, false, 0).array();
/*     */     
/* 225 */     if (Trace.isOn) {
/* 226 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.V6ExitDefinition", "prepareByteArray(RfpTSH)", traceRet1);
/*     */     }
/*     */     
/* 229 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Object invoke(JmqiTls tls, RfpTSH tsh, MQCD cd, MQCXP mqcxp, byte[] mqcspStruct, boolean securityFirstInvocation) throws JmqiException {
/* 240 */     if (Trace.isOn) {
/* 241 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.V6ExitDefinition", "invoke(JmqiTls,RfpTSH,MQCD,MQCXP,byte [ ],boolean)", new Object[] { tls, tsh, cd, mqcxp, mqcspStruct, 
/*     */             
/* 243 */             Boolean.valueOf(securityFirstInvocation) });
/*     */     }
/* 245 */     byte[] buffer = null;
/* 246 */     if (tsh != null) {
/* 247 */       buffer = prepareByteArray(tsh);
/*     */     }
/*     */     
/* 250 */     MQChannelDefinition mqcd = writetoMQChannelDefinition(cd);
/*     */     
/* 252 */     MQChannelExit mqce = writetoMQChannelExit(mqcxp);
/* 253 */     MQCSP mqcsp = mqcxp.getSecurityParms();
/* 254 */     MQConnectionSecurityParameters mqSecParms = null;
/* 255 */     if (mqcsp != null)
/*     */     {
/* 257 */       mqSecParms = writeToMQConnectionSecurityParameters(mqcsp);
/*     */     }
/* 259 */     mqce.setMQCSP(mqSecParms);
/*     */ 
/*     */     
/* 262 */     byte[] retBuffer = null; try {
/*     */       MQSecurityExit secExit; MQSendExit sendExit;
/*     */       MQReceiveExit rcvExit;
/* 265 */       switch (this.exitType) {
/*     */         case 11:
/* 267 */           mqcd.securityUserData = cd.getSecurityUserData();
/* 268 */           secExit = (MQSecurityExit)this.object;
/* 269 */           retBuffer = secExit.securityExit(mqce, mqcd, buffer);
/*     */           break;
/*     */         
/*     */         case 13:
/* 273 */           mqcd.sendUserData = cd.getSendUserData();
/* 274 */           sendExit = (MQSendExit)this.object;
/* 275 */           retBuffer = sendExit.sendExit(mqce, mqcd, buffer);
/*     */           break;
/*     */         
/*     */         case 14:
/* 279 */           mqcd.receiveUserData = cd.getReceiveUserData();
/* 280 */           rcvExit = (MQReceiveExit)this.object;
/* 281 */           retBuffer = rcvExit.receiveExit(mqce, mqcd, buffer);
/*     */           break;
/*     */       } 
/*     */ 
/*     */     
/* 286 */     } catch (Exception e) {
/* 287 */       if (Trace.isOn) {
/* 288 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.exit.V6ExitDefinition", "invoke(JmqiTls,RfpTSH,MQCD,MQCXP,byte [ ],boolean)", e);
/*     */       }
/*     */ 
/*     */       
/* 292 */       JmqiException traceRet1 = new JmqiException(this.env, 9535, new String[] { getExitName() }, 2, 2407, e);
/* 293 */       if (Trace.isOn) {
/* 294 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.exit.V6ExitDefinition", "invoke(JmqiTls,RfpTSH,MQCD,MQCXP,byte [ ],boolean)", (Throwable)traceRet1);
/*     */       }
/*     */       
/* 297 */       throw traceRet1;
/*     */     } 
/*     */ 
/*     */     
/* 301 */     readFromMQChannelDefinition(cd, mqcd);
/*     */     
/* 303 */     readFromMQChannelExit(mqcxp, mqce);
/*     */     
/* 305 */     if ((mqSecParms = mqce.getMQCSP()) == null) {
/* 306 */       mqcxp.setSecurityParms(null);
/*     */     }
/*     */     else {
/*     */       
/* 310 */       if (mqcsp == null) {
/* 311 */         mqcsp = this.env.newMQCSP();
/*     */       }
/*     */       
/* 314 */       readFromMQConnectionSecurityParameters(mqcsp, mqSecParms);
/* 315 */       mqcxp.setSecurityParms(mqcsp);
/*     */     } 
/*     */     
/* 318 */     if (Trace.isOn) {
/* 319 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.V6ExitDefinition", "invoke(JmqiTls,RfpTSH,MQCD,MQCXP,byte [ ],boolean)", retBuffer);
/*     */     }
/*     */     
/* 322 */     return retBuffer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void readFromMQConnectionSecurityParameters(MQCSP mqcsp, MQConnectionSecurityParameters mqSecParms) {
/* 333 */     if (Trace.isOn) {
/* 334 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.V6ExitDefinition", "readFromMQConnectionSecurityParameters(MQCSP,MQConnectionSecurityParameters)", new Object[] { mqcsp, mqSecParms });
/*     */     }
/*     */ 
/*     */     
/* 338 */     mqcsp.setVersion(mqSecParms.getVersion());
/* 339 */     mqcsp.setAuthenticationType(mqSecParms.getAuthenticationType());
/* 340 */     mqcsp.setCspUserId(mqSecParms.getCSPUserId());
/* 341 */     mqcsp.setCspPassword(mqSecParms.getCSPPassword());
/*     */     
/* 343 */     if (Trace.isOn) {
/* 344 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.V6ExitDefinition", "readFromMQConnectionSecurityParameters(MQCSP,MQConnectionSecurityParameters)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void readFromMQChannelExit(MQCXP mqcxp, MQChannelExit mqce) {
/* 351 */     if (Trace.isOn) {
/* 352 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.V6ExitDefinition", "readFromMQChannelExit(MQCXP,MQChannelExit)", new Object[] { mqcxp, mqce });
/*     */     }
/*     */     
/* 355 */     mqcxp.setExitResponse(mqce.exitResponse);
/* 356 */     mqcxp.setExitResponse2(0);
/* 357 */     mqcxp.setMaxSegmentLength(mqce.maxSegmentLength);
/* 358 */     System.arraycopy(mqce.exitUserArea, 0, mqcxp.getExitUserArea(), 0, Math.min(mqce.exitUserArea.length, (mqcxp.getExitUserArea()).length));
/* 359 */     mqcxp.setFapLevel(mqce.fapLevel);
/* 360 */     mqcxp.setCapabilityFlags(mqce.capabilityFlags);
/*     */     
/* 362 */     if (Trace.isOn) {
/* 363 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.V6ExitDefinition", "readFromMQChannelExit(MQCXP,MQChannelExit)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void readFromMQChannelDefinition(MQCD cd, MQChannelDefinition mqcd) {
/* 370 */     if (Trace.isOn) {
/* 371 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.V6ExitDefinition", "readFromMQChannelDefinition(MQCD,MQChannelDefinition)", new Object[] { cd, mqcd });
/*     */     }
/*     */     
/* 374 */     cd.setChannelName(mqcd.channelName);
/* 375 */     cd.setQMgrName(mqcd.queueManagerName);
/* 376 */     cd.setMaxMsgLength(mqcd.maxMessageLength);
/* 377 */     cd.setConnectionName(mqcd.connectionName);
/* 378 */     cd.setRemoteUserIdentifier(mqcd.remoteUserId);
/* 379 */     cd.setRemotePassword(mqcd.remotePassword);
/* 380 */     cd.setSslPeerName(mqcd.sslPeerName);
/* 381 */     cd.setLocalAddress(mqcd.localAddress);
/* 382 */     cd.setSharingConversations(mqcd.sharingConversations);
/*     */     
/* 384 */     if (Trace.isOn) {
/* 385 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.V6ExitDefinition", "readFromMQChannelDefinition(MQCD,MQChannelDefinition)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private MQConnectionSecurityParameters writeToMQConnectionSecurityParameters(MQCSP mqcsp) {
/* 392 */     if (Trace.isOn) {
/* 393 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.V6ExitDefinition", "writeToMQConnectionSecurityParameters(MQCSP)", new Object[] { mqcsp });
/*     */     }
/*     */ 
/*     */     
/* 397 */     MQConnectionSecurityParameters mqSecParms = new MQConnectionSecurityParameters();
/* 398 */     mqSecParms.setVersion(mqcsp.getVersion());
/* 399 */     mqSecParms.setAuthenticationType(mqcsp.getAuthenticationType());
/* 400 */     mqSecParms.setCSPUserId(mqcsp.getCspUserId());
/* 401 */     mqSecParms.setCSPPassword(mqcsp.getCspPassword());
/*     */     
/* 403 */     if (Trace.isOn) {
/* 404 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.V6ExitDefinition", "writeToMQConnectionSecurityParameters(MQCSP)", mqSecParms);
/*     */     }
/*     */     
/* 407 */     return mqSecParms;
/*     */   }
/*     */   
/*     */   private MQChannelExit writetoMQChannelExit(MQCXP mqcxp) {
/* 411 */     if (Trace.isOn) {
/* 412 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.V6ExitDefinition", "writetoMQChannelExit(MQCXP)", new Object[] { mqcxp });
/*     */     }
/*     */     
/* 415 */     MQChannelExit mqce = new MQChannelExit();
/* 416 */     mqce.exitID = mqcxp.getExitId();
/* 417 */     mqce.exitReason = mqcxp.getExitReason();
/* 418 */     mqce.exitResponse = 0;
/* 419 */     mqce.maxSegmentLength = mqcxp.getMaxSegmentLength();
/*     */     
/* 421 */     if (mqcxp.getExitUserArea() != null) {
/*     */       
/* 423 */       if (mqce.exitUserArea == null) {
/* 424 */         mqce.exitUserArea = new byte[(mqcxp.getExitUserArea()).length];
/*     */       }
/* 426 */       System.arraycopy(mqcxp.getExitUserArea(), 0, mqce.exitUserArea, 0, (mqcxp.getExitUserArea()).length);
/*     */     } 
/* 428 */     mqce.fapLevel = mqcxp.getFapLevel();
/* 429 */     mqce.capabilityFlags = mqcxp.getCapabilityFlags();
/* 430 */     mqce.CurHdrCompression = mqcxp.getCurHdrCompression();
/* 431 */     mqce.CurMsgCompression = mqcxp.getCurMsgCompression();
/*     */     
/* 433 */     if (Trace.isOn) {
/* 434 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.V6ExitDefinition", "writetoMQChannelExit(MQCXP)", mqce);
/*     */     }
/*     */     
/* 437 */     return mqce;
/*     */   }
/*     */ 
/*     */   
/*     */   private MQChannelDefinition writetoMQChannelDefinition(MQCD cd) {
/* 442 */     if (Trace.isOn) {
/* 443 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.V6ExitDefinition", "writetoMQChannelDefinition(MQCD)", new Object[] { cd });
/*     */     }
/*     */     
/* 446 */     MQChannelDefinition mqcd = new MQChannelDefinition();
/* 447 */     mqcd.channelName = cd.getChannelName();
/* 448 */     mqcd.queueManagerName = cd.getQMgrName();
/* 449 */     mqcd.maxMessageLength = cd.getMaxMsgLength();
/* 450 */     mqcd.connectionName = cd.getConnectionName();
/* 451 */     mqcd.remoteUserId = cd.getRemoteUserIdentifier();
/* 452 */     mqcd.remotePassword = cd.getRemotePassword();
/* 453 */     mqcd.sslPeerName = cd.getSslPeerName();
/* 454 */     mqcd.localAddress = cd.getLocalAddress();
/* 455 */     mqcd.sharingConversations = cd.getSharingConversations();
/* 456 */     mqcd.hdrCompList = new Vector(Arrays.asList((Object[])new int[][] { cd.getHdrCompList() }));
/* 457 */     mqcd.msgCompList = new Vector(Arrays.asList((Object[])new int[][] { cd.getMsgCompList() }));
/*     */     
/* 459 */     if (Trace.isOn) {
/* 460 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.V6ExitDefinition", "writetoMQChannelDefinition(MQCD)", mqcd);
/*     */     }
/*     */     
/* 463 */     return mqcd;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 471 */     if (Trace.isOn) {
/* 472 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.V6ExitDefinition", "toString()");
/*     */     }
/* 474 */     String traceRet1 = super.toString() + "\nV6 Java Exit: " + this.object.getClass().getName();
/* 475 */     if (Trace.isOn) {
/* 476 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.V6ExitDefinition", "toString()", traceRet1);
/*     */     }
/* 478 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\exit\V6ExitDefinition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */