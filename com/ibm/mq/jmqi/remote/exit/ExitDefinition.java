/*     */ package com.ibm.mq.jmqi.remote.exit;
/*     */ 
/*     */ import com.ibm.mq.exits.MQCD;
/*     */ import com.ibm.mq.exits.MQCXP;
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.mq.jmqi.remote.rfp.RfpTSH;
/*     */ import com.ibm.mq.jmqi.system.JmqiTls;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.Arrays;
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
/*     */ abstract class ExitDefinition
/*     */   extends JmqiObject
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/exit/ExitDefinition.java";
/*     */   protected RemoteExitChain parent;
/*     */   
/*     */   static {
/*  48 */     if (Trace.isOn) {
/*  49 */       Trace.data("com.ibm.mq.jmqi.remote.exit.ExitDefinition", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/exit/ExitDefinition.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  59 */   protected int exitType = 0;
/*     */   protected boolean inUse = true;
/*     */   protected boolean mqcdExit = false;
/*  62 */   protected byte[] exitUserArea = new byte[16];
/*  63 */   private byte[] exitUserData = new byte[32];
/*  64 */   protected String exitName = null;
/*  65 */   protected int exitSpace = 0;
/*  66 */   protected int exitNumber = 0;
/*     */   
/*     */   ExitDefinition(JmqiEnvironment env, RemoteExitChain parent, String exitName, String exitUserData, boolean mqcdExit, int exitSpace) {
/*  69 */     super(env);
/*  70 */     if (Trace.isOn) {
/*  71 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.ExitDefinition", "<init>(JmqiEnvironment,RemoteExitChain,String,String,boolean,int)", new Object[] { env, parent, exitName, exitUserData, 
/*     */             
/*  73 */             Boolean.valueOf(mqcdExit), Integer.valueOf(exitSpace) });
/*     */     }
/*  75 */     this.env = env;
/*  76 */     this.parent = parent;
/*  77 */     this.exitType = parent.exitType;
/*  78 */     this.exitName = exitName;
/*  79 */     this.mqcdExit = mqcdExit;
/*     */ 
/*     */     
/*  82 */     Arrays.fill(this.exitUserData, " ".getBytes(Charset.defaultCharset())[0]);
/*  83 */     if (exitUserData != null) {
/*  84 */       System.arraycopy(exitUserData.getBytes(Charset.defaultCharset()), 0, this.exitUserData, 0, Math.min(exitUserData.length(), this.exitUserData.length));
/*     */     }
/*  86 */     this.exitSpace = exitSpace;
/*     */     
/*  88 */     if (Trace.isOn) {
/*  89 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.ExitDefinition", "<init>(JmqiEnvironment,RemoteExitChain,String,String,boolean,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void setInUse(boolean inUse) {
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.data(this, "com.ibm.mq.jmqi.remote.exit.ExitDefinition", "setInUse(boolean)", "setter", 
/*  98 */           Boolean.valueOf(inUse));
/*     */     }
/* 100 */     this.inUse = inUse;
/*     */   }
/*     */   
/*     */   boolean isInUse() {
/* 104 */     if (Trace.isOn) {
/* 105 */       Trace.data(this, "com.ibm.mq.jmqi.remote.exit.ExitDefinition", "isInUse()", "getter", 
/* 106 */           Boolean.valueOf(this.inUse));
/*     */     }
/* 108 */     return this.inUse;
/*     */   }
/*     */   
/*     */   boolean isMqcdExit() {
/* 112 */     if (Trace.isOn) {
/* 113 */       Trace.data(this, "com.ibm.mq.jmqi.remote.exit.ExitDefinition", "isMqcdExit()", "getter", 
/* 114 */           Boolean.valueOf(this.mqcdExit));
/*     */     }
/* 116 */     return this.mqcdExit;
/*     */   }
/*     */   
/*     */   void setExitUserArea(byte[] exitUserArea) {
/* 120 */     if (Trace.isOn) {
/* 121 */       Trace.data(this, "com.ibm.mq.jmqi.remote.exit.ExitDefinition", "setExitUserArea(byte [ ])", "setter", exitUserArea);
/*     */     }
/*     */     
/* 124 */     this.exitUserArea = exitUserArea;
/*     */   }
/*     */   
/*     */   byte[] getExitUserArea() {
/* 128 */     if (Trace.isOn) {
/* 129 */       Trace.data(this, "com.ibm.mq.jmqi.remote.exit.ExitDefinition", "getExitUserArea()", "getter", this.exitUserArea);
/*     */     }
/*     */     
/* 132 */     return this.exitUserArea;
/*     */   }
/*     */   
/*     */   int getExitUserAreaLength() {
/* 136 */     if (Trace.isOn) {
/* 137 */       Trace.data(this, "com.ibm.mq.jmqi.remote.exit.ExitDefinition", "getExitUserAreaLength()", "getter", 
/* 138 */           Integer.valueOf(this.exitUserArea.length));
/*     */     }
/* 140 */     return this.exitUserArea.length;
/*     */   }
/*     */   
/*     */   void loadMqcxpUserArea(MQCXP mqcxp) {
/* 144 */     if (Trace.isOn) {
/* 145 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.ExitDefinition", "loadMqcxpUserArea(MQCXP)", new Object[] { mqcxp });
/*     */     }
/*     */     
/* 148 */     System.arraycopy(getExitUserArea(), 0, mqcxp.getExitUserArea(), 0, getExitUserAreaLength());
/*     */     
/* 150 */     if (Trace.isOn) {
/* 151 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.ExitDefinition", "loadMqcxpUserArea(MQCXP)");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void unLoadMqcxpUserArea(MQCXP mqcxp) {
/* 157 */     if (Trace.isOn) {
/* 158 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.ExitDefinition", "unLoadMqcxpUserArea(MQCXP)", new Object[] { mqcxp });
/*     */     }
/*     */     
/* 161 */     System.arraycopy(mqcxp.getExitUserArea(), 0, getExitUserArea(), 0, getExitUserAreaLength());
/*     */     
/* 163 */     if (Trace.isOn) {
/* 164 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.ExitDefinition", "unLoadMqcxpUserArea(MQCXP)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   String getExitName() {
/* 171 */     if (Trace.isOn) {
/* 172 */       Trace.data(this, "com.ibm.mq.jmqi.remote.exit.ExitDefinition", "getExitName()", "getter", this.exitName);
/*     */     }
/*     */     
/* 175 */     return this.exitName;
/*     */   }
/*     */   
/*     */   int getExitSpace() {
/* 179 */     if (Trace.isOn) {
/* 180 */       Trace.data(this, "com.ibm.mq.jmqi.remote.exit.ExitDefinition", "getExitSpace()", "getter", 
/* 181 */           Integer.valueOf(this.exitSpace));
/*     */     }
/* 183 */     return this.exitSpace;
/*     */   }
/*     */   
/*     */   void setExitSpace(int exitSpace) {
/* 187 */     if (Trace.isOn) {
/* 188 */       Trace.data(this, "com.ibm.mq.jmqi.remote.exit.ExitDefinition", "setExitSpace(int)", "setter", 
/* 189 */           Integer.valueOf(exitSpace));
/*     */     }
/* 191 */     this.exitSpace = exitSpace;
/*     */   }
/*     */   
/*     */   Object getObject() {
/* 195 */     if (Trace.isOn) {
/* 196 */       Trace.data(this, "com.ibm.mq.jmqi.remote.exit.ExitDefinition", "getObject()", "getter", null);
/*     */     }
/*     */     
/* 199 */     return null;
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
/*     */   void setExitNumber(int exitNumber) {
/* 219 */     if (Trace.isOn) {
/* 220 */       Trace.data(this, "com.ibm.mq.jmqi.remote.exit.ExitDefinition", "setExitNumber(int)", "setter", 
/* 221 */           Integer.valueOf(exitNumber));
/*     */     }
/* 223 */     this.exitNumber = exitNumber;
/*     */   }
/*     */   
/*     */   int getExitNumber() {
/* 227 */     if (Trace.isOn) {
/* 228 */       Trace.data(this, "com.ibm.mq.jmqi.remote.exit.ExitDefinition", "getExitNumber()", "getter", 
/* 229 */           Integer.valueOf(this.exitNumber));
/*     */     }
/* 231 */     return this.exitNumber;
/*     */   }
/*     */   
/*     */   int getExitType() {
/* 235 */     if (Trace.isOn) {
/* 236 */       Trace.data(this, "com.ibm.mq.jmqi.remote.exit.ExitDefinition", "getExitType()", "getter", 
/* 237 */           Integer.valueOf(this.exitType));
/*     */     }
/* 239 */     return this.exitType;
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
/*     */   protected ByteBuffer prepareBuffer(RfpTSH tsh, boolean forceEmpty, int minAgentBufferSize) {
/*     */     ByteBuffer buffer;
/* 252 */     if (Trace.isOn) {
/* 253 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.ExitDefinition", "prepareBuffer(RfpTSH,boolean,int)", new Object[] { tsh, 
/* 254 */             Boolean.valueOf(forceEmpty), 
/* 255 */             Integer.valueOf(minAgentBufferSize) });
/*     */     }
/*     */     
/* 258 */     if (forceEmpty) {
/* 259 */       buffer = allocateByteBuffer(0);
/*     */     }
/* 261 */     else if (this.exitType == 11) {
/*     */ 
/*     */ 
/*     */       
/* 265 */       int positionOfUserData = tsh.getRfpOffset() + tsh.tshHdrSize() + 4;
/* 266 */       int userDataLength = tsh.getTransLength() - tsh.tshHdrSize() - 4;
/* 267 */       if (userDataLength > 0) {
/* 268 */         if (userDataLength < minAgentBufferSize) {
/* 269 */           buffer = allocateByteBuffer(minAgentBufferSize);
/*     */         } else {
/*     */           
/* 272 */           buffer = allocateByteBuffer(userDataLength);
/*     */         } 
/*     */ 
/*     */         
/* 276 */         buffer.put(tsh.getRfpBuffer(), positionOfUserData, userDataLength);
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */         
/* 285 */         buffer = allocateByteBuffer(minAgentBufferSize);
/*     */       
/*     */       }
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 294 */       int transDataSize = tsh.getTransLength();
/* 295 */       int dataOffset = (tsh.getTshType() == 2) ? 16 : 8;
/*     */ 
/*     */ 
/*     */       
/* 299 */       int payLoadSize = transDataSize - dataOffset;
/* 300 */       int bufferSize = payLoadSize + 8;
/*     */ 
/*     */ 
/*     */       
/* 304 */       if (this.exitType == 13) {
/* 305 */         bufferSize += this.parent.sendExitsUserSpace;
/*     */       }
/* 307 */       buffer = allocateByteBuffer(bufferSize);
/* 308 */       buffer.position(8);
/* 309 */       buffer.put(tsh.getRfpBuffer(), tsh.getRfpOffset() + dataOffset, transDataSize - dataOffset);
/*     */     } 
/*     */     
/* 312 */     if (Trace.isOn) {
/* 313 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.ExitDefinition", "prepareBuffer(RfpTSH,boolean,int)", buffer);
/*     */     }
/*     */     
/* 316 */     return buffer;
/*     */   }
/*     */ 
/*     */   
/*     */   protected ByteBuffer allocateByteBuffer(int bufferSize) {
/* 321 */     if (Trace.isOn)
/* 322 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.ExitDefinition", "allocateByteBuffer(int)", new Object[] {
/* 323 */             Integer.valueOf(bufferSize)
/*     */           }); 
/* 325 */     ByteBuffer traceRet1 = ByteBuffer.allocate(bufferSize);
/*     */     
/* 327 */     if (Trace.isOn) {
/* 328 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.ExitDefinition", "allocateByteBuffer(int)", traceRet1);
/*     */     }
/*     */     
/* 331 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 339 */     if (Trace.isOn) {
/* 340 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.exit.ExitDefinition", "toString()");
/*     */     }
/* 342 */     String traceRet1 = "ExitDefinition: " + this.exitName;
/* 343 */     if (Trace.isOn) {
/* 344 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.exit.ExitDefinition", "toString()", traceRet1);
/*     */     }
/* 346 */     return traceRet1;
/*     */   }
/*     */   
/*     */   protected void setExitUserData(byte[] exitUserData) {
/* 350 */     if (Trace.isOn) {
/* 351 */       Trace.data(this, "com.ibm.mq.jmqi.remote.exit.ExitDefinition", "setExitUserData(byte [ ])", "setter", exitUserData);
/*     */     }
/*     */     
/* 354 */     this.exitUserData = exitUserData;
/*     */   }
/*     */   
/*     */   protected byte[] getExitUserData() {
/* 358 */     if (Trace.isOn) {
/* 359 */       Trace.data(this, "com.ibm.mq.jmqi.remote.exit.ExitDefinition", "getExitUserData()", "getter", this.exitUserData);
/*     */     }
/*     */     
/* 362 */     return this.exitUserData;
/*     */   }
/*     */   
/*     */   abstract Object invoke(JmqiTls paramJmqiTls, RfpTSH paramRfpTSH, MQCD paramMQCD, MQCXP paramMQCXP, byte[] paramArrayOfbyte, boolean paramBoolean) throws JmqiException;
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\exit\ExitDefinition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */