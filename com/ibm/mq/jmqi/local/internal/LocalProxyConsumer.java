/*     */ package com.ibm.mq.jmqi.local.internal;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.mq.jmqi.MQCBC;
/*     */ import com.ibm.mq.jmqi.MQCBD;
/*     */ import com.ibm.mq.jmqi.MQConsumer;
/*     */ import com.ibm.mq.jmqi.MQGMO;
/*     */ import com.ibm.mq.jmqi.MQMD;
/*     */ import com.ibm.mq.jmqi.handles.Hconn;
/*     */ import com.ibm.mq.jmqi.handles.Hobj;
/*     */ import com.ibm.mq.jmqi.internal.JmqiTools;
/*     */ import com.ibm.mq.jmqi.local.LocalHconn;
/*     */ import com.ibm.mq.jmqi.local.LocalHobj;
/*     */ import com.ibm.mq.jmqi.local.LocalMQ;
/*     */ import com.ibm.mq.jmqi.local.LocalTls;
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.mq.jmqi.system.JmqiComponentTls;
/*     */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*     */ import com.ibm.mq.jmqi.system.JmqiTls;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.HashMap;
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
/*     */ public class LocalProxyConsumer
/*     */   extends JmqiObject
/*     */ {
/*     */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/internal/LocalProxyConsumer.java";
/*     */   private int options;
/*     */   private MQCBD mqcbd;
/*     */   private LocalHconn localHconn;
/*     */   private LocalHobj localHobj;
/*     */   private ClassLoader threadClassloader;
/*     */   
/*     */   static {
/*  52 */     if (Trace.isOn) {
/*  53 */       Trace.data("com.ibm.mq.jmqi.local.internal.LocalProxyConsumer", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/internal/LocalProxyConsumer.java");
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
/*     */   public LocalProxyConsumer(JmqiEnvironment env, LocalHconn localHconn, LocalHobj localHobj) {
/*  75 */     super(env);
/*  76 */     if (Trace.isOn) {
/*  77 */       Trace.entry(this, "com.ibm.mq.jmqi.local.internal.LocalProxyConsumer", "<init>(JmqiEnvironment,LocalHconn,LocalHobj)", new Object[] { env, localHconn, localHobj });
/*     */     }
/*     */     
/*  80 */     this.localHconn = localHconn;
/*  81 */     this.localHobj = localHobj;
/*     */     
/*  83 */     if (Trace.isOn) {
/*  84 */       Trace.exit(this, "com.ibm.mq.jmqi.local.internal.LocalProxyConsumer", "<init>(JmqiEnvironment,LocalHconn,LocalHobj)");
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
/*     */   public void jmqiConsumerMethod(byte[] mqmd_buf, byte[] mqgmo_buf, byte[] pBuffer, byte[] pContext) {
/* 101 */     String fid = "jmqiConsumerMethod(byte[],byte[],byte[],byte[])";
/*     */     
/* 103 */     if (Trace.isOn) {
/* 104 */       Trace.data(this, "jmqiConsumerMethod(byte[],byte[],byte[],byte[])", "__________");
/* 105 */       Trace.data(this, "jmqiConsumerMethod(byte[],byte[],byte[],byte[])", "JmqiProxyConsumer.jmqiConsumerMethod >>");
/* 106 */       Trace.data(this, "jmqiConsumerMethod(byte[],byte[],byte[],byte[])", "mqmd_buf", mqmd_buf);
/* 107 */       Trace.data(this, "jmqiConsumerMethod(byte[],byte[],byte[],byte[])", "mqgmo_buf", mqgmo_buf);
/* 108 */       Trace.data(this, "jmqiConsumerMethod(byte[],byte[],byte[],byte[])", "pBuffer", pBuffer);
/* 109 */       Trace.data(this, "jmqiConsumerMethod(byte[],byte[],byte[],byte[])", "pContext", pContext);
/* 110 */       Trace.data(this, "jmqiConsumerMethod(byte[],byte[],byte[],byte[])", "__________");
/*     */     } 
/*     */     try {
/*     */       MQCBC mqcbc;
/* 114 */       LocalMQ localMQ = (LocalMQ)this.localHconn.getMq();
/* 115 */       int jmqiCompId = localMQ.getJmqiCompId();
/* 116 */       int ptrSize = LocalMQ.getPtrSize();
/* 117 */       boolean swap = LocalMQ.getSwap();
/* 118 */       JmqiCodepage nativeCp = this.env.getNativeCharSet();
/* 119 */       JmqiSystemEnvironment sysenv = (JmqiSystemEnvironment)this.env;
/*     */       
/* 121 */       LocalTls tls = (LocalTls)sysenv.getComponentTls(jmqiCompId);
/* 122 */       JmqiTls jTls = sysenv.getJmqiTls((JmqiComponentTls)tls);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 127 */       jTls.setAsyncConsumeThread((Hconn)this.localHconn);
/*     */ 
/*     */       
/* 130 */       if (pContext != null) {
/* 131 */         mqcbc = this.env.newMQCBC();
/* 132 */         mqcbc.readFromBuffer(pContext, 0, ptrSize, swap, nativeCp, jTls);
/* 133 */         mqcbc.setHobj((Hobj)this.localHobj);
/* 134 */         mqcbc.setCallbackArea(this.mqcbd.getCallbackArea());
/* 135 */         mqcbc.setConnectionArea(this.localHconn.getConnectionArea());
/*     */       } else {
/* 137 */         throw new NullPointerException();
/*     */       } 
/*     */ 
/*     */       
/* 141 */       boolean CallJmqiConsumeMethod = false;
/* 142 */       int callType = mqcbc.getCallType();
/* 143 */       switch (callType) {
/*     */         case 4:
/* 145 */           if ((this.options & 0x200) != 0) {
/* 146 */             CallJmqiConsumeMethod = true;
/*     */           }
/*     */           break;
/*     */         case 2:
/* 150 */           if ((this.options & 0x4) != 0) {
/* 151 */             CallJmqiConsumeMethod = true;
/*     */           }
/*     */           break;
/*     */         
/*     */         default:
/* 156 */           CallJmqiConsumeMethod = true;
/*     */           break;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 162 */       ByteBuffer byteBuf = null;
/* 163 */       MQMD mqmd = null;
/* 164 */       MQGMO mqgmo = null;
/* 165 */       if (callType != 5) {
/* 166 */         if (pBuffer != null) {
/* 167 */           byteBuf = ByteBuffer.allocate(pBuffer.length);
/* 168 */           byteBuf.put(pBuffer);
/*     */         } else {
/* 170 */           byteBuf = null;
/*     */         } 
/*     */         
/* 173 */         if (mqmd_buf != null) {
/* 174 */           if (tls.mqmd == null) {
/* 175 */             tls.mqmd = this.env.newMQMD();
/*     */           }
/* 177 */           mqmd = tls.mqmd;
/* 178 */           mqmd.readFromBuffer(mqmd_buf, 0, ptrSize, swap, nativeCp, jTls);
/*     */         } else {
/* 180 */           mqmd = null;
/*     */         } 
/*     */         
/* 183 */         if (mqgmo_buf != null) {
/* 184 */           if (tls.mqgmo == null) {
/* 185 */             tls.mqgmo = this.env.newMQGMO();
/*     */           }
/* 187 */           mqgmo = tls.mqgmo;
/* 188 */           mqgmo.readFromBuffer(mqgmo_buf, 0, ptrSize, swap, nativeCp, jTls);
/*     */         } else {
/* 190 */           mqgmo = null;
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 199 */       if (this.threadClassloader != null) {
/* 200 */         JmqiTools.setThreadContextClassLoader(this.threadClassloader);
/*     */       }
/*     */ 
/*     */       
/* 204 */       if (CallJmqiConsumeMethod) {
/*     */         try {
/* 206 */           MQConsumer mqConsumer = this.mqcbd.getCallbackFunction();
/* 207 */           mqConsumer.consumer((Hconn)this.localHconn, mqmd, mqgmo, byteBuf, mqcbc);
/*     */         }
/* 209 */         catch (Throwable throwable) {}
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 220 */       jTls.setAsyncConsumeThread(null);
/*     */     
/*     */     }
/* 223 */     catch (Throwable throwable) {
/*     */ 
/*     */       
/*     */       try {
/*     */         
/* 228 */         HashMap<String, Object> ffstInfo = new HashMap<>();
/* 229 */         ffstInfo.put("Throwable class", throwable.getClass().getName());
/* 230 */         ffstInfo.put("Throwable message", throwable.getMessage());
/* 231 */         ffstInfo.put("Description", "unexpected throwable caught");
/* 232 */         Trace.ffst(this, "jmqiConsumerMethod(byte[],byte[],byte[],byte[])", "1", ffstInfo, null);
/*     */       }
/* 234 */       catch (Throwable throwable1) {}
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 239 */     if (Trace.isOn) {
/* 240 */       Trace.data(this, "jmqiConsumerMethod(byte[],byte[],byte[],byte[])", "__________");
/* 241 */       Trace.data(this, "jmqiConsumerMethod(byte[],byte[],byte[],byte[])", "JmqiProxyConsumer.jmqiConsumerMethod <<");
/* 242 */       Trace.data(this, "jmqiConsumerMethod(byte[],byte[],byte[],byte[])", "__________");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOptions(int options) {
/* 250 */     if (Trace.isOn) {
/* 251 */       Trace.data(this, "com.ibm.mq.jmqi.local.internal.LocalProxyConsumer", "setOptions(int)", "setter", 
/* 252 */           Integer.valueOf(options));
/*     */     }
/* 254 */     this.options = options;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMQCBD(MQCBD mqcbd) {
/* 261 */     if (Trace.isOn) {
/* 262 */       Trace.data(this, "com.ibm.mq.jmqi.local.internal.LocalProxyConsumer", "setMQCBD(MQCBD)", "setter", mqcbd);
/*     */     }
/*     */     
/* 265 */     this.mqcbd = mqcbd;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setThreadClassloader(ClassLoader threadClassloader) {
/* 272 */     if (Trace.isOn) {
/* 273 */       Trace.data(this, "com.ibm.mq.jmqi.local.internal.LocalProxyConsumer", "setThreadClassloader(ClassLoader)", "setter", threadClassloader);
/*     */     }
/*     */     
/* 276 */     this.threadClassloader = threadClassloader;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\local\internal\LocalProxyConsumer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */