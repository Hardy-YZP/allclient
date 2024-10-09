/*     */ package com.ibm.mq.ese.intercept;
/*     */ 
/*     */ import com.ibm.mq.ese.conv.CcsidConverter;
/*     */ import com.ibm.mq.ese.nls.AmsErrorMessages;
/*     */ import com.ibm.mq.ese.pki.CertAccess;
/*     */ import com.ibm.mq.ese.prot.MessageProtection;
/*     */ import com.ibm.mq.ese.service.EseMQException;
/*     */ import com.ibm.mq.ese.service.EseMQService;
/*     */ import com.ibm.mq.ese.service.PolicyService;
/*     */ import com.ibm.mq.ese.service.UserMapService;
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiMQ;
/*     */ import com.ibm.mq.jmqi.handles.Hconn;
/*     */ import com.ibm.mq.jmqi.handles.Hobj;
/*     */ import com.ibm.mq.jmqi.handles.Pint;
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
/*     */ public abstract class AbstractJmqiInterceptor
/*     */   implements JmqiInterceptor
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/intercept/AbstractJmqiInterceptor.java";
/*     */   
/*     */   static {
/*  56 */     if (Trace.isOn) {
/*  57 */       Trace.data("com.ibm.mq.ese.intercept.AbstractJmqiInterceptor", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/intercept/AbstractJmqiInterceptor.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   private static final String CLASS = AbstractJmqiInterceptor.class.getName();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected EseMQService mqService;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected UserMapService identityService;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected MessageProtection cryptoService;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected JmqiContextContainer contextContainer;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected JmqiEnvironment env;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected JmqiMQ jmqi;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected CcsidConverter ccsidConverter;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected PolicyService policyService;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected CertAccess certAccess;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setErrorReasonCode(Pint pCompCode, Pint pReason, int rc) {
/* 120 */     if (Trace.isOn) {
/* 121 */       Trace.entry(this, "com.ibm.mq.ese.intercept.AbstractJmqiInterceptor", "setErrorReasonCode(Pint,Pint,int)", new Object[] { pCompCode, pReason, 
/* 122 */             Integer.valueOf(rc) });
/*     */     }
/* 124 */     if (pCompCode != null) {
/* 125 */       pCompCode.x = 2;
/*     */     }
/* 127 */     if (pReason != null) {
/* 128 */       pReason.x = rc;
/*     */     }
/* 130 */     if (Trace.isOn) {
/* 131 */       Trace.exit(this, "com.ibm.mq.ese.intercept.AbstractJmqiInterceptor", "setErrorReasonCode(Pint,Pint,int)");
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
/*     */   protected ConnectionContext getContext(Hconn hconn) throws EseMQException {
/* 147 */     if (Trace.isOn) {
/* 148 */       Trace.entry(this, "com.ibm.mq.ese.intercept.AbstractJmqiInterceptor", "getContext(final Hconn)", new Object[] { hconn });
/*     */     }
/*     */     
/* 151 */     ConnectionContext context = getContextContainer().getContext(hconn);
/* 152 */     if (context == null) {
/* 153 */       HashMap<String, Object> inserts = new HashMap<>();
/* 154 */       inserts.put("AMS_INSERT_MQ_ERROR_CODE", Integer.valueOf(2018).toString());
/* 155 */       EseMQException ex = new EseMQException(AmsErrorMessages.mqm_s_conn_cant_save_qmname, inserts);
/* 156 */       ex.setReason(2018);
/* 157 */       if (Trace.isOn) {
/* 158 */         Trace.throwing(this, "com.ibm.mq.ese.intercept.AbstractJmqiInterceptor", "getContext(final Hconn)", (Throwable)ex);
/*     */       }
/*     */       
/* 161 */       throw ex;
/*     */     } 
/* 163 */     if (Trace.isOn) {
/* 164 */       Trace.exit(this, "com.ibm.mq.ese.intercept.AbstractJmqiInterceptor", "getContext(final Hconn)", context);
/*     */     }
/*     */     
/* 167 */     return context;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void flipBufferIfNecessary(ByteBuffer byteBuffer) {
/* 176 */     if (Trace.isOn) {
/* 177 */       Trace.entry(this, "com.ibm.mq.ese.intercept.AbstractJmqiInterceptor", "flipBufferIfNecessary(ByteBuffer)", new Object[] { byteBuffer });
/*     */     }
/*     */     
/* 180 */     if (byteBuffer != null && byteBuffer.position() == byteBuffer.limit()) {
/* 181 */       byteBuffer.flip();
/*     */     }
/* 183 */     if (Trace.isOn) {
/* 184 */       Trace.exit(this, "com.ibm.mq.ese.intercept.AbstractJmqiInterceptor", "flipBufferIfNecessary(ByteBuffer)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected JmqiTls getTls() {
/* 194 */     JmqiSystemEnvironment sysenv = (JmqiSystemEnvironment)this.env;
/* 195 */     int componentId = this.jmqi.getTlsComponentId();
/* 196 */     JmqiComponentTls componentTls = sysenv.getComponentTls(componentId);
/* 197 */     JmqiTls jTls = sysenv.getJmqiTls(componentTls);
/* 198 */     if (Trace.isOn) {
/* 199 */       Trace.data(this, "com.ibm.mq.ese.intercept.AbstractJmqiInterceptor", "getTls()", "getter", jTls);
/*     */     }
/* 201 */     return jTls;
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
/*     */   protected SmqiObject getSmqiObject(Hconn hconn, Hobj hobj, String meth) throws EseMQException {
/* 216 */     if (Trace.isOn) {
/* 217 */       Trace.entry(this, "com.ibm.mq.ese.intercept.AbstractJmqiInterceptor", "getSmqiObject(final Hconn,final Hobj,String)", new Object[] { hconn, hobj, meth });
/*     */     }
/*     */     
/* 220 */     ConnectionContext context = getContext(hconn);
/* 221 */     SmqiObject smqiObject = this.contextContainer.getModelQueueInfo(context
/* 222 */         .getQmgrName(), hobj);
/* 223 */     if (smqiObject == null) {
/* 224 */       smqiObject = context.findObject(hobj);
/*     */     }
/* 226 */     if (smqiObject == null) {
/* 227 */       HashMap<String, Object> inserts = new HashMap<>();
/* 228 */       inserts.put("AMS_INSERT_INTERNAL_ERROR_CODE", Integer.valueOf(2019).toString());
/* 229 */       AmsErrorMessages.log(CLASS, meth, AmsErrorMessages.mqm_s_get_cant_open_qinfo, inserts);
/* 230 */       if (Trace.isOn) {
/* 231 */         Trace.traceInfo(this, meth, "Could not resolve object information from Hobj", hobj);
/*     */       }
/* 233 */       EseMQException ex = new EseMQException(AmsErrorMessages.mqm_s_get_cant_open_qinfo, inserts);
/* 234 */       ex.setReason(2019);
/* 235 */       if (Trace.isOn) {
/* 236 */         Trace.throwing(this, "com.ibm.mq.ese.intercept.AbstractJmqiInterceptor", "getSmqiObject(final Hconn,final Hobj,String)", (Throwable)ex, 1);
/*     */       }
/*     */       
/* 239 */       throw ex;
/*     */     } 
/* 241 */     if (Trace.isOn) {
/* 242 */       Trace.traceInfo(this, meth, "Queue information resolved from Hobj", hobj);
/*     */     }
/* 244 */     if (smqiObject.getSecPolicy() == null) {
/* 245 */       if (Trace.isOn) {
/* 246 */         Trace.traceInfo(this, meth, "Could not find security policy information in Hobj", hobj);
/*     */       }
/* 248 */       HashMap<String, Object> inserts = new HashMap<>();
/* 249 */       inserts.put("AMS_INSERT_INTERNAL_ERROR_CODE", Integer.valueOf(2063).toString());
/* 250 */       EseMQException ex = new EseMQException(AmsErrorMessages.mqm_s_get_cant_open_qinfo, inserts);
/* 251 */       ex.setReason(2063);
/* 252 */       if (Trace.isOn) {
/* 253 */         Trace.throwing(this, "com.ibm.mq.ese.intercept.AbstractJmqiInterceptor", "getSmqiObject(final Hconn,final Hobj,String)", (Throwable)ex, 2);
/*     */       }
/*     */       
/* 256 */       throw ex;
/*     */     } 
/* 258 */     if (Trace.isOn) {
/* 259 */       Trace.traceInfo(this, meth, "Security policy information found in Hobj " + smqiObject.getSecPolicy(), hobj);
/*     */     }
/* 261 */     if (Trace.isOn) {
/* 262 */       Trace.exit(this, "com.ibm.mq.ese.intercept.AbstractJmqiInterceptor", "getSmqiObject(final Hconn,final Hobj,String)", smqiObject);
/*     */     }
/*     */     
/* 265 */     return smqiObject;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isOptionSet(int options, int value) {
/* 275 */     if (Trace.isOn)
/* 276 */       Trace.entry(this, "com.ibm.mq.ese.intercept.AbstractJmqiInterceptor", "isOptionSet(int,int)", new Object[] {
/* 277 */             Integer.valueOf(options), Integer.valueOf(value)
/*     */           }); 
/* 279 */     boolean traceRet1 = ((options & value) == value);
/* 280 */     if (Trace.isOn) {
/* 281 */       Trace.exit(this, "com.ibm.mq.ese.intercept.AbstractJmqiInterceptor", "isOptionSet(int,int)", 
/* 282 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 284 */     return traceRet1;
/*     */   }
/*     */   
/*     */   public void setMQService(EseMQService eseMQService) {
/* 288 */     if (Trace.isOn) {
/* 289 */       Trace.data(this, "com.ibm.mq.ese.intercept.AbstractJmqiInterceptor", "setMQService(EseMQService)", "setter", eseMQService);
/*     */     }
/*     */     
/* 292 */     this.mqService = eseMQService;
/*     */   }
/*     */   
/*     */   public void setIdentityService(UserMapService identityService) {
/* 296 */     if (Trace.isOn) {
/* 297 */       Trace.data(this, "com.ibm.mq.ese.intercept.AbstractJmqiInterceptor", "setIdentityService(UserMapService)", "setter", identityService);
/*     */     }
/*     */     
/* 300 */     this.identityService = identityService;
/*     */   }
/*     */   
/*     */   public void setCryptoService(MessageProtection cryptoService) {
/* 304 */     if (Trace.isOn) {
/* 305 */       Trace.data(this, "com.ibm.mq.ese.intercept.AbstractJmqiInterceptor", "setCryptoService(MessageProtection)", "setter", cryptoService);
/*     */     }
/*     */     
/* 308 */     this.cryptoService = cryptoService;
/*     */   }
/*     */   
/*     */   public void setContextContainer(JmqiContextContainer contextContainer) {
/* 312 */     if (Trace.isOn) {
/* 313 */       Trace.data(this, "com.ibm.mq.ese.intercept.AbstractJmqiInterceptor", "setContextContainer(JmqiContextContainer)", "setter", contextContainer);
/*     */     }
/*     */     
/* 316 */     this.contextContainer = contextContainer;
/*     */   }
/*     */   
/*     */   public JmqiContextContainer getContextContainer() {
/* 320 */     if (Trace.isOn) {
/* 321 */       Trace.data(this, "com.ibm.mq.ese.intercept.AbstractJmqiInterceptor", "getContextContainer()", "getter", this.contextContainer);
/*     */     }
/*     */     
/* 324 */     return this.contextContainer;
/*     */   }
/*     */   
/*     */   public JmqiEnvironment getEnv() {
/* 328 */     if (Trace.isOn) {
/* 329 */       Trace.data(this, "com.ibm.mq.ese.intercept.AbstractJmqiInterceptor", "getEnv()", "getter", this.env);
/*     */     }
/* 331 */     return this.env;
/*     */   }
/*     */   
/*     */   public void setEnv(JmqiEnvironment env) {
/* 335 */     if (Trace.isOn) {
/* 336 */       Trace.data(this, "com.ibm.mq.ese.intercept.AbstractJmqiInterceptor", "setEnv(JmqiEnvironment)", "setter", env);
/*     */     }
/*     */     
/* 339 */     this.env = env;
/*     */   }
/*     */   
/*     */   public JmqiMQ getJmqi() {
/* 343 */     if (Trace.isOn) {
/* 344 */       Trace.data(this, "com.ibm.mq.ese.intercept.AbstractJmqiInterceptor", "getJmqi()", "getter", this.jmqi);
/*     */     }
/*     */     
/* 347 */     return this.jmqi;
/*     */   }
/*     */   
/*     */   public void setJmqi(JmqiMQ jmqi) {
/* 351 */     if (Trace.isOn) {
/* 352 */       Trace.data(this, "com.ibm.mq.ese.intercept.AbstractJmqiInterceptor", "setJmqi(JmqiMQ)", "setter", jmqi);
/*     */     }
/*     */     
/* 355 */     this.jmqi = jmqi;
/*     */   }
/*     */   
/*     */   public void setCcsidConverter(CcsidConverter ccsidConverter) {
/* 359 */     if (Trace.isOn) {
/* 360 */       Trace.data(this, "com.ibm.mq.ese.intercept.AbstractJmqiInterceptor", "setCcsidConverter(CcsidConverter)", "setter", ccsidConverter);
/*     */     }
/*     */     
/* 363 */     this.ccsidConverter = ccsidConverter;
/*     */   }
/*     */   
/*     */   public void setPolicyService(PolicyService policyService) {
/* 367 */     if (Trace.isOn) {
/* 368 */       Trace.data(this, "com.ibm.mq.ese.intercept.AbstractJmqiInterceptor", "setPolicyService(PolicyService)", "setter", policyService);
/*     */     }
/*     */     
/* 371 */     this.policyService = policyService;
/*     */   }
/*     */   
/*     */   public void setCertAccess(CertAccess certAccess) {
/* 375 */     if (Trace.isOn) {
/* 376 */       Trace.data(this, "com.ibm.mq.ese.intercept.AbstractJmqiInterceptor", "setCertAccess(CertAccess)", "setter", certAccess);
/*     */     }
/*     */     
/* 379 */     this.certAccess = certAccess;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\intercept\AbstractJmqiInterceptor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */