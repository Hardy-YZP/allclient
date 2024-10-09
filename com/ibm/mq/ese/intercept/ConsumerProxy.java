/*     */ package com.ibm.mq.ese.intercept;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.MQCBC;
/*     */ import com.ibm.mq.jmqi.MQConsumer;
/*     */ import com.ibm.mq.jmqi.MQGMO;
/*     */ import com.ibm.mq.jmqi.MQMD;
/*     */ import com.ibm.mq.jmqi.handles.Hconn;
/*     */ import com.ibm.mq.jmqi.handles.PbyteBuffer;
/*     */ import com.ibm.mq.jmqi.handles.Pint;
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
/*     */ public class ConsumerProxy
/*     */   implements MQConsumer
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/intercept/ConsumerProxy.java";
/*     */   private final MQConsumer delegate;
/*     */   private final int delegateMaxMsgLength;
/*     */   private JmqiGetInterceptor getInterceptor;
/*     */   private final JmqiEnvironment env;
/*     */   private final SmqiObject smqiObject;
/*     */   private MQMD msgDesc;
/*     */   private MQGMO getMsgOpts;
/*     */   
/*     */   static {
/*  56 */     if (Trace.isOn) {
/*  57 */       Trace.data("com.ibm.mq.ese.intercept.ConsumerProxy", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/intercept/ConsumerProxy.java");
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
/*     */   public ConsumerProxy(MQConsumer delegate, int maxMsgLength, SmqiObject smqiObject, MQMD msgDesc, MQGMO getMsgOpts, JmqiEnvironment env) {
/* 115 */     if (Trace.isOn) {
/* 116 */       Trace.entry(this, "com.ibm.mq.ese.intercept.ConsumerProxy", "<init>(MQConsumer,int,SmqiObject,MQMD,MQGMO,JmqiEnvironment)", new Object[] { delegate, 
/*     */             
/* 118 */             Integer.valueOf(maxMsgLength), smqiObject, msgDesc, getMsgOpts, env });
/*     */     }
/*     */     
/* 121 */     if (delegate == null) {
/* 122 */       IllegalArgumentException traceRet1 = new IllegalArgumentException("MQConsumer must not be null");
/*     */       
/* 124 */       if (Trace.isOn) {
/* 125 */         Trace.throwing(this, "com.ibm.mq.ese.intercept.ConsumerProxy", "<init>(MQConsumer,int,SmqiObject,MQMD,MQGMO,JmqiEnvironment)", traceRet1, 1);
/*     */       }
/*     */       
/* 128 */       throw traceRet1;
/*     */     } 
/* 130 */     if (smqiObject == null) {
/* 131 */       IllegalArgumentException traceRet2 = new IllegalArgumentException("QueueInfo must not be null");
/* 132 */       if (Trace.isOn) {
/* 133 */         Trace.throwing(this, "com.ibm.mq.ese.intercept.ConsumerProxy", "<init>(MQConsumer,int,SmqiObject,MQMD,MQGMO,JmqiEnvironment)", traceRet2, 2);
/*     */       }
/*     */       
/* 136 */       throw traceRet2;
/*     */     } 
/* 138 */     if (env == null) {
/* 139 */       IllegalArgumentException traceRet3 = new IllegalArgumentException("JmqiEnvironment must not be null");
/*     */ 
/*     */       
/* 142 */       if (Trace.isOn) {
/* 143 */         Trace.throwing(this, "com.ibm.mq.ese.intercept.ConsumerProxy", "<init>(MQConsumer,int,SmqiObject,MQMD,MQGMO,JmqiEnvironment)", traceRet3, 3);
/*     */       }
/*     */       
/* 146 */       throw traceRet3;
/*     */     } 
/* 148 */     if (msgDesc == null) {
/* 149 */       IllegalArgumentException traceRet4 = new IllegalArgumentException("MQMD must not be null");
/* 150 */       if (Trace.isOn) {
/* 151 */         Trace.throwing(this, "com.ibm.mq.ese.intercept.ConsumerProxy", "<init>(MQConsumer,int,SmqiObject,MQMD,MQGMO,JmqiEnvironment)", traceRet4, 4);
/*     */       }
/*     */       
/* 154 */       throw traceRet4;
/*     */     } 
/* 156 */     if (getMsgOpts == null) {
/* 157 */       IllegalArgumentException traceRet5 = new IllegalArgumentException("MQGMO must not be null");
/* 158 */       if (Trace.isOn) {
/* 159 */         Trace.throwing(this, "com.ibm.mq.ese.intercept.ConsumerProxy", "<init>(MQConsumer,int,SmqiObject,MQMD,MQGMO,JmqiEnvironment)", traceRet5, 5);
/*     */       }
/*     */       
/* 162 */       throw traceRet5;
/*     */     } 
/* 164 */     this.delegate = delegate;
/* 165 */     this.delegateMaxMsgLength = maxMsgLength;
/* 166 */     this.smqiObject = smqiObject;
/* 167 */     this.msgDesc = msgDesc;
/* 168 */     this.getMsgOpts = getMsgOpts;
/* 169 */     this.env = env;
/*     */     
/* 171 */     if (Trace.isOn) {
/* 172 */       Trace.exit(this, "com.ibm.mq.ese.intercept.ConsumerProxy", "<init>(MQConsumer,int,SmqiObject,MQMD,MQGMO,JmqiEnvironment)");
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
/*     */   public void consumer(Hconn hconn, MQMD md, MQGMO gmo, ByteBuffer buffer, MQCBC cbc) {
/* 186 */     if (Trace.isOn) {
/* 187 */       Trace.entry(this, "com.ibm.mq.ese.intercept.ConsumerProxy", "consumer(Hconn,MQMD,MQGMO,ByteBuffer,MQCBC)", new Object[] { hconn, md, gmo, buffer, cbc });
/*     */     }
/*     */ 
/*     */     
/* 191 */     if (!isCallbackTypeValid(cbc) || cbc.getCompCode() == 2) {
/* 192 */       if (Trace.isOn) {
/* 193 */         Trace.traceInfo(this, "com.ibm.mq.ese.intercept.ConsumerProxy", "consumer(Hconn, MQMD, MQGMO, ByteBuffer, MQCBC)", "callback not valid for MQAMS", "");
/*     */       }
/*     */ 
/*     */       
/* 197 */       this.delegate.consumer(hconn, md, gmo, buffer, cbc);
/* 198 */       if (Trace.isOn) {
/* 199 */         Trace.exit(this, "com.ibm.mq.ese.intercept.ConsumerProxy", "consumer(Hconn,MQMD,MQGMO,ByteBuffer,MQCBC)");
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/* 204 */     Pint cc = this.env.newPint(cbc.getCompCode());
/* 205 */     Pint rc = this.env.newPint(cbc.getReason());
/* 206 */     Pint dataLength = this.env.newPint(cbc.getDataLength());
/*     */ 
/*     */     
/* 209 */     MQGetContext getContext = new MQGetContext(this.msgDesc, this.getMsgOpts, this.smqiObject);
/*     */     
/* 211 */     int maxLength = cbc.getBufferLength();
/* 212 */     if (this.delegateMaxMsgLength != -1 && 
/* 213 */       this.delegateMaxMsgLength >= 0 && this.delegateMaxMsgLength < maxLength) {
/* 214 */       maxLength = this.delegateMaxMsgLength;
/*     */     }
/*     */ 
/*     */     
/* 218 */     PbyteBuffer pByteBuffer = this.env.newPbyteBuffer(buffer);
/* 219 */     Pint msgTooSmallCount = this.env.newPint(0);
/*     */     
/* 221 */     getContext.setAsyncConsume(true);
/* 222 */     this.getInterceptor.afterJmqiGet(getContext, hconn, cbc.getHobj(), md, gmo, -1, maxLength, pByteBuffer, msgTooSmallCount, dataLength, cc, rc);
/*     */ 
/*     */     
/* 225 */     cbc.setDataLength(dataLength.x);
/* 226 */     cbc.setCompCode(cc.x);
/* 227 */     cbc.setReason(rc.x);
/*     */ 
/*     */     
/* 230 */     if (cbc.getCompCode() == 0 || (cbc
/* 231 */       .getCompCode() == 1 && (cbc.getReason() == 2079 || cbc
/* 232 */       .getReason() == 2080 || cbc
/* 233 */       .getReason() == 2120))) {
/*     */ 
/*     */       
/* 236 */       if ((cbc.getReason() == 2079 || cbc
/* 237 */         .getReason() == 0 || cbc
/* 238 */         .getReason() == 2120) && (
/* 239 */         gmo.getOptions() & 0x10) != 16 && (gmo
/* 240 */         .getOptions() & 0x20) != 32 && (gmo
/* 241 */         .getOptions() & 0x800) != 2048) {
/* 242 */         cbc.setCallType(6);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 247 */       this.delegate.consumer(hconn, md, gmo, buffer, cbc);
/*     */     } else {
/* 249 */       cbc.setCallType(5);
/* 250 */       cbc.setState(3);
/* 251 */       if (Trace.isOn) {
/* 252 */         Trace.traceWarning(this, "consumer(Hconn, MQMD, MQGMO, ByteBuffer, MQCBC)", "MQGET Interceptor failed, suspending consumer", null);
/*     */       }
/*     */       
/* 255 */       this.delegate.consumer(hconn, md, gmo, buffer, cbc);
/*     */     } 
/*     */     
/* 258 */     if (Trace.isOn) {
/* 259 */       Trace.exit(this, "com.ibm.mq.ese.intercept.ConsumerProxy", "consumer(Hconn,MQMD,MQGMO,ByteBuffer,MQCBC)");
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
/*     */   private boolean isCallbackTypeValid(MQCBC cbc) {
/* 271 */     if (Trace.isOn) {
/* 272 */       Trace.entry(this, "com.ibm.mq.ese.intercept.ConsumerProxy", "isCallbackTypeValid(MQCBC)", new Object[] { cbc });
/*     */     }
/*     */     
/* 275 */     if (cbc.getCallType() == 6 || cbc
/* 276 */       .getCallType() == 7) {
/* 277 */       if (Trace.isOn) {
/* 278 */         Trace.exit(this, "com.ibm.mq.ese.intercept.ConsumerProxy", "isCallbackTypeValid(MQCBC)", 
/* 279 */             Boolean.valueOf(true), 1);
/*     */       }
/* 281 */       return true;
/*     */     } 
/* 283 */     if (Trace.isOn) {
/* 284 */       Trace.exit(this, "com.ibm.mq.ese.intercept.ConsumerProxy", "isCallbackTypeValid(MQCBC)", 
/* 285 */           Boolean.valueOf(false), 2);
/*     */     }
/* 287 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDelegateMaxMsgLength() {
/* 295 */     if (Trace.isOn) {
/* 296 */       Trace.data(this, "com.ibm.mq.ese.intercept.ConsumerProxy", "getDelegateMaxMsgLength()", "getter", 
/* 297 */           Integer.valueOf(this.delegateMaxMsgLength));
/*     */     }
/* 299 */     return this.delegateMaxMsgLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGetInterceptor(JmqiGetInterceptor getInterceptor) {
/* 308 */     if (Trace.isOn) {
/* 309 */       Trace.data(this, "com.ibm.mq.ese.intercept.ConsumerProxy", "setGetInterceptor(JmqiGetInterceptor)", "setter", getInterceptor);
/*     */     }
/*     */     
/* 312 */     this.getInterceptor = getInterceptor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQConsumer getDelegate() {
/* 320 */     if (Trace.isOn) {
/* 321 */       Trace.data(this, "com.ibm.mq.ese.intercept.ConsumerProxy", "getDelegate()", "getter", this.delegate);
/*     */     }
/*     */     
/* 324 */     return this.delegate;
/*     */   }
/*     */   
/*     */   public MQGMO getGetMsgOpts() {
/* 328 */     if (Trace.isOn) {
/* 329 */       Trace.data(this, "com.ibm.mq.ese.intercept.ConsumerProxy", "getGetMsgOpts()", "getter", this.getMsgOpts);
/*     */     }
/*     */     
/* 332 */     return this.getMsgOpts;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\intercept\ConsumerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */