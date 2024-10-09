/*     */ package com.ibm.mq.ese.prot;
/*     */ 
/*     */ import com.ibm.mq.ese.core.AMBIException;
/*     */ import com.ibm.mq.ese.core.AMBIHeader;
/*     */ import com.ibm.mq.ese.core.EseUser;
/*     */ import com.ibm.mq.ese.core.SecurityPolicy;
/*     */ import com.ibm.mq.ese.intercept.SmqiObject;
/*     */ import com.ibm.mq.ese.nls.AmsErrorMessages;
/*     */ import com.ibm.mq.ese.pki.X509CertificateValidator;
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.internal.JmqiTools;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.lang.reflect.Constructor;
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
/*     */ public class MessageProtectionWrapper
/*     */   implements MessageProtection
/*     */ {
/*  62 */   private JmqiEnvironment env = null;
/*  63 */   private X509CertificateValidator certificateValidator = null;
/*  64 */   private MessageProtection delegate = null;
/*     */   
/*     */   public MessageProtectionWrapper(X509CertificateValidator validator, JmqiEnvironment env) {
/*  67 */     this.env = env;
/*  68 */     this.certificateValidator = validator;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] protect(byte[] msg, SmqiObject smqiObject, AMBIHeader header, EseUser userInfo) throws MessageProtectionException {
/*  74 */     if (!isInitialised())
/*     */     {
/*     */ 
/*     */       
/*  78 */       throw new MessageProtectionException(AmsErrorMessages.mjp_MessageProtectionBCImpl_not_loaded);
/*     */     }
/*  80 */     if (Trace.isOn) {
/*  81 */       Trace.data(this, "com.ibm.mq.ese.prot.MessageProtectionWrapper", "protect(byte[],SmqiObject,AMBIHeader,EseUser)", "Delegating call to: " + this.delegate
/*     */           
/*  83 */           .getClass().getCanonicalName(), null);
/*     */     }
/*  85 */     return this.delegate.protect(msg, smqiObject, header, userInfo);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public MessageUnprotectInfo unprotect(byte[] protMsg, SecurityPolicy policy, AMBIHeader hdr, SmqiObject smqiObject, EseUser userInfo) throws MessageProtectionException {
/*  91 */     if (!isInitialised()) {
/*  92 */       throw new MessageProtectionException(AmsErrorMessages.mjp_MessageProtectionBCImpl_not_loaded);
/*     */     }
/*  94 */     if (Trace.isOn) {
/*  95 */       Trace.data(this, "com.ibm.mq.ese.prot.MessageProtectionWrapper", "unprotect(byte[],SecurityPolicy,AMBIHeader,SmqiObject,EseUser)", "Delegating call to: " + this.delegate
/*     */           
/*  97 */           .getClass().getCanonicalName(), null);
/*     */     }
/*  99 */     return this.delegate.unprotect(protMsg, policy, hdr, smqiObject, userInfo);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValid() {
/* 104 */     boolean retVal = false;
/* 105 */     if (this.delegate != null) {
/* 106 */       retVal = this.delegate.isValid();
/*     */     }
/* 108 */     if (Trace.isOn) {
/* 109 */       Trace.data(this, "com.ibm.mq.ese.prot.MessageProtectionWrapper", "isValid()", "getter", 
/* 110 */           Boolean.valueOf(retVal));
/*     */     }
/* 112 */     return retVal;
/*     */   }
/*     */   
/*     */   private boolean isInitialised() {
/* 116 */     return (this.delegate != null);
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
/*     */   public boolean initialise() throws JmqiException {
/* 128 */     if (Trace.isOn) {
/* 129 */       Trace.entry(this, "com.ibm.mq.ese.prot.MessageProtectionWrapper", "initialise()", null);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 134 */     if (isInitialised()) {
/* 135 */       if (Trace.isOn) {
/* 136 */         Trace.exit(this, "com.ibm.mq.ese.prot.MessageProtectionWrapper", "initialise()", "Returning false. Message protection service has already been initialised : " + this.delegate
/*     */ 
/*     */             
/* 139 */             .getClass().getCanonicalName(), 1);
/*     */       }
/* 141 */       return false;
/*     */     } 
/*     */     
/* 144 */     synchronized (this) {
/*     */ 
/*     */       
/* 147 */       if (isInitialised()) {
/* 148 */         if (Trace.isOn) {
/* 149 */           Trace.exit(this, "com.ibm.mq.ese.prot.MessageProtectionWrapper", "initialise()", "Returning false. Message protection service has already been initialised : " + this.delegate
/*     */ 
/*     */               
/* 152 */               .getClass().getCanonicalName(), 2);
/*     */         }
/* 154 */         return false;
/*     */       } 
/*     */ 
/*     */       
/*     */       try {
/* 159 */         Class<?> msgProtectionClass = JmqiTools.dynamicLoadClass(this.env, "com.ibm.mq.ese.prot.MessageProtectionBCImpl", getClass());
/* 160 */         Class<?>[] paramTypes = new Class[] { X509CertificateValidator.class };
/* 161 */         Constructor<?> constructor = msgProtectionClass.getConstructor(paramTypes);
/* 162 */         Object[] params = { this.certificateValidator };
/* 163 */         this.delegate = (MessageProtection)constructor.newInstance(params);
/* 164 */         this.delegate.initialise();
/*     */       }
/* 166 */       catch (ClassNotFoundException|NoClassDefFoundError|SecurityException|NoSuchMethodException|IllegalArgumentException|InstantiationException|IllegalAccessException|java.lang.reflect.InvocationTargetException ex) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 176 */         if (Trace.isOn) {
/* 177 */           Trace.catchBlock(this, "com.ibm.mq.ese.prot.MessageProtectionWrapper", "initialise()", ex, 1);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 183 */         AMBIException ambiEx = new AMBIException(AmsErrorMessages.mjp_failed_to_load_BC_dependant_class, ex);
/*     */ 
/*     */ 
/*     */         
/* 187 */         JmqiException traceRet1 = new JmqiException(this.env, 9546, new String[] { JmqiTools.getExSumm(ex), null, JmqiTools.getFailingCall(ex) }, 2, 2195, (Throwable)ambiEx);
/*     */         
/* 189 */         if (Trace.isOn) {
/* 190 */           Trace.throwing(this, "com.ibm.mq.ese.prot.MessageProtectionWrapper", "initialise()", (Throwable)traceRet1, 1);
/*     */         }
/*     */         
/* 193 */         throw traceRet1;
/*     */       }
/* 195 */       catch (RuntimeException e) {
/* 196 */         if (Trace.isOn) {
/* 197 */           Trace.catchBlock(this, "com.ibm.mq.ese.prot.MessageProtectionWrapper", "initialise()", e, 2);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 204 */         Throwable cause = e.getCause();
/*     */         
/* 206 */         if (cause instanceof ClassNotFoundException) {
/* 207 */           AMBIException ambiEx = new AMBIException(AmsErrorMessages.mjp_failed_to_load_BC_dependant_class, e);
/*     */ 
/*     */ 
/*     */           
/* 211 */           JmqiException jmqie = new JmqiException(this.env, 9546, new String[] { JmqiTools.getExSumm(e), null, JmqiTools.getFailingCall(e) }, 2, 2195, (Throwable)ambiEx);
/*     */           
/* 213 */           if (Trace.isOn) {
/* 214 */             Trace.throwing(this, "com.ibm.mq.ese.prot.MessageProtectionWrapper", "initialise()", (Throwable)jmqie, 2);
/*     */           }
/*     */           
/* 217 */           throw jmqie;
/*     */         } 
/* 219 */         if (Trace.isOn) {
/* 220 */           Trace.throwing(this, "com.ibm.mq.ese.prot.MessageProtectionWrapper", "initialise()", e, 3);
/*     */         }
/*     */         
/* 223 */         throw e;
/*     */       } 
/*     */     } 
/*     */     
/* 227 */     if (Trace.isOn) {
/* 228 */       Trace.exit(this, "com.ibm.mq.ese.prot.MessageProtectionWrapper", "initialise()", "Returning true as the message protection service has now been initialised: " + this.delegate
/*     */ 
/*     */           
/* 231 */           .getClass().getCanonicalName());
/*     */     }
/* 233 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\prot\MessageProtectionWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */