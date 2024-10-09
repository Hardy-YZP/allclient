/*     */ package com.ibm.mq.jmqi.local;
/*     */ 
/*     */ import com.ibm.mq.constants.CMQC;
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.mq.jmqi.handles.Hobj;
/*     */ import com.ibm.mq.jmqi.handles.Phobj;
/*     */ import com.ibm.mq.jmqi.local.internal.LocalProxyConsumer;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
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
/*     */ public class LocalHobj
/*     */   extends JmqiObject
/*     */   implements Hobj
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/LocalHobj.java";
/*     */   private int value;
/*     */   private LocalHconn localHconn;
/*     */   private LocalProxyConsumer proxyConsumer;
/*     */   
/*     */   static {
/*  39 */     if (Trace.isOn) {
/*  40 */       Trace.data("com.ibm.mq.jmqi.local.LocalHobj", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/LocalHobj.java");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   private byte[] ctxToken = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean usedForAsyncConsume = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected LocalHobj(JmqiEnvironment env, int value) {
/*  79 */     super(env);
/*  80 */     if (Trace.isOn) {
/*  81 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalHobj", "<init>(JmqiEnvironment,int)", new Object[] { env, 
/*  82 */             Integer.valueOf(value) });
/*     */     }
/*  84 */     this.value = value;
/*     */     
/*  86 */     if (Trace.isOn) {
/*  87 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalHobj", "<init>(JmqiEnvironment,int)");
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
/*     */   protected LocalHobj(JmqiEnvironment env) {
/*  99 */     super(env);
/* 100 */     if (Trace.isOn) {
/* 101 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalHobj", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/*     */     
/* 104 */     this.value = 0;
/*     */     
/* 106 */     if (Trace.isOn) {
/* 107 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalHobj", "<init>(JmqiEnvironment)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getValue() {
/* 117 */     if (Trace.isOn) {
/* 118 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHobj", "getValue()", "getter", 
/* 119 */           Integer.valueOf(this.value));
/*     */     }
/* 121 */     return this.value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValue(int value) {
/* 129 */     if (Trace.isOn) {
/* 130 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHobj", "setValue(int)", "setter", 
/* 131 */           Integer.valueOf(value));
/*     */     }
/* 133 */     this.value = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 143 */     String stringVal = String.format("LocalHobj@0x%8x - %s", new Object[] { Integer.valueOf(System.identityHashCode(this)), Integer.toHexString(this.value) });
/* 144 */     return stringVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static LocalHobj getLocalHobj(JmqiEnvironment env, Hobj hobj) {
/* 153 */     if (Trace.isOn) {
/* 154 */       Trace.entry("com.ibm.mq.jmqi.local.LocalHobj", "getLocalHobj(JmqiEnvironment,Hobj)", new Object[] { env, hobj });
/*     */     }
/*     */     
/* 157 */     LocalHobj localHobj = null;
/* 158 */     if (hobj instanceof LocalHobj) {
/* 159 */       localHobj = (LocalHobj)hobj;
/*     */     }
/* 161 */     else if (hobj == CMQC.jmqi_MQHO_NONE) {
/* 162 */       localHobj = new LocalHobj(env, 0);
/*     */     } else {
/*     */       
/* 165 */       localHobj = new LocalHobj(env, -1);
/*     */     } 
/*     */     
/* 168 */     if (Trace.isOn) {
/* 169 */       Trace.exit("com.ibm.mq.jmqi.local.LocalHobj", "getLocalHobj(JmqiEnvironment,Hobj)", localHobj);
/*     */     }
/*     */     
/* 172 */     return localHobj;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Hobj getHobj(JmqiEnvironment env, LocalHobj localhobj) {
/* 182 */     if (Trace.isOn) {
/* 183 */       Trace.entry("com.ibm.mq.jmqi.local.LocalHobj", "getHobj(JmqiEnvironment,LocalHobj)", new Object[] { env, localhobj });
/*     */     }
/*     */     
/* 186 */     Hobj hobj = null;
/* 187 */     int value = localhobj.getValue();
/* 188 */     switch (value) {
/*     */       case 0:
/* 190 */         hobj = CMQC.jmqi_MQHO_NONE;
/*     */         break;
/*     */       case -1:
/* 193 */         hobj = CMQC.jmqi_MQHO_UNUSABLE_HOBJ;
/*     */         break;
/*     */       default:
/* 196 */         hobj = localhobj;
/*     */         break;
/*     */     } 
/* 199 */     if (Trace.isOn) {
/* 200 */       Trace.exit("com.ibm.mq.jmqi.local.LocalHobj", "getHobj(JmqiEnvironment,LocalHobj)", hobj);
/*     */     }
/* 202 */     return hobj;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHobj(Phobj phobj) {
/* 210 */     if (Trace.isOn) {
/* 211 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHobj", "setHobj(Phobj)", "setter", phobj);
/*     */     }
/* 213 */     Hobj hobj = null;
/* 214 */     switch (this.value) {
/*     */       case 0:
/* 216 */         hobj = CMQC.jmqi_MQHO_NONE;
/*     */         break;
/*     */       case -1:
/* 219 */         hobj = CMQC.jmqi_MQHO_UNUSABLE_HOBJ;
/*     */         break;
/*     */       default:
/* 222 */         hobj = this; break;
/*     */     } 
/* 224 */     phobj.setHobj(hobj);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getIntegerHandle() {
/* 231 */     if (Trace.isOn) {
/* 232 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHobj", "getIntegerHandle()", "getter", 
/* 233 */           Integer.valueOf(this.value));
/*     */     }
/* 235 */     return this.value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LocalHconn getHconn() {
/* 243 */     if (Trace.isOn) {
/* 244 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHobj", "getHconn()", "getter", this.localHconn);
/*     */     }
/* 246 */     return this.localHconn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHconn(LocalHconn hconn) {
/* 253 */     if (Trace.isOn) {
/* 254 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHobj", "setHconn(LocalHconn)", "setter", hconn);
/*     */     }
/*     */     
/* 257 */     this.localHconn = hconn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LocalProxyConsumer getProxyConsumer() {
/* 264 */     if (this.proxyConsumer == null) {
/* 265 */       this.proxyConsumer = new LocalProxyConsumer(this.env, this.localHconn, this);
/*     */     }
/* 267 */     if (Trace.isOn) {
/* 268 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHobj", "getProxyConsumer()", "getter", this.proxyConsumer);
/*     */     }
/*     */     
/* 271 */     return this.proxyConsumer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getCtxToken() {
/* 279 */     if (Trace.isOn) {
/* 280 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHobj", "getCtxToken()", "getter", this.ctxToken);
/*     */     }
/* 282 */     return this.ctxToken;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCtxToken(byte[] ctxToken) {
/* 290 */     if (Trace.isOn) {
/* 291 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHobj", "setCtxToken(byte [ ])", "setter", ctxToken);
/*     */     }
/*     */     
/* 294 */     this.ctxToken = new byte[ctxToken.length];
/* 295 */     System.arraycopy(ctxToken, 0, this.ctxToken, 0, ctxToken.length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getAMSPolicy() {
/* 304 */     if (Trace.isOn) {
/* 305 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHobj", "getAMSPolicy()", "getter", null);
/*     */     }
/* 307 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAMSErrorQueue() {
/* 316 */     if (Trace.isOn) {
/* 317 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHobj", "getAMSErrorQueue()", "getter", null);
/*     */     }
/* 319 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUsedForAsyncConsume() {
/* 328 */     if (Trace.isOn) {
/* 329 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHobj", "isUsedForAsyncConsume()", "getter", 
/* 330 */           Boolean.valueOf(this.usedForAsyncConsume));
/*     */     }
/* 332 */     return this.usedForAsyncConsume;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUsedForAsyncConsume(boolean asyncConsume) {
/* 341 */     if (Trace.isOn) {
/* 342 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHobj", "setUsedForAsyncConsume(boolean)", "setter", 
/* 343 */           Boolean.valueOf(asyncConsume));
/*     */     }
/* 345 */     this.usedForAsyncConsume = asyncConsume;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\local\LocalHobj.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */