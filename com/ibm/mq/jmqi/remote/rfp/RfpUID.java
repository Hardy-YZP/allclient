/*     */ package com.ibm.mq.jmqi.remote.rfp;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.mq.jmqi.system.JmqiTls;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RfpUID
/*     */   extends RfpStructure
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpUID.java";
/*     */   
/*     */   static {
/*  36 */     if (Trace.isOn) {
/*  37 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.RfpUID", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpUID.java");
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
/*     */   public RfpUID(JmqiEnvironment env, byte[] buffer, int offset) {
/*  55 */     super(env, buffer, offset);
/*     */   }
/*     */   
/*  58 */   private static final byte[] rfpUID_ID = new byte[] { 85, 73, 68, 32 };
/*  59 */   private static final byte[] rfpUID_ID_ASCII = new byte[] { 85, 73, 68, 32 };
/*  60 */   private static final byte[] rfpUID_ID_EBCDIC = new byte[] { -28, -55, -60, 64 };
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int USER_IDENTIFIER_OFFSET = 4;
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int PASSWORD_OFFSET = 16;
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int LONG_USER_ID_OFFSET = 28;
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int USER_SECURITY_ID_OFFSET = 92;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int SIZE_FAP4 = 28;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int SIZE_CURRENT = 132;
/*     */ 
/*     */ 
/*     */   
/*     */   public void initEyecatcher() {
/*  89 */     System.arraycopy(rfpUID_ID, 0, this.buffer, this.offset, rfpUID_ID.length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearUIDStructure(int fapLevel) {
/*  96 */     if (fapLevel <= 4) {
/*  97 */       this.dc.clear(this.buffer, this.offset, 28);
/*     */     } else {
/*     */       
/* 100 */       this.dc.clear(this.buffer, this.offset, 132);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUserIdentifier(String userIdentifier, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 110 */     if (Trace.isOn) {
/* 111 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpUID", "setUserIdentifier(String,JmqiCodepage,JmqiTls)", new Object[] { userIdentifier, cp, tls });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 116 */     String upperCaseUserIdentifier = userIdentifier.toUpperCase();
/*     */     
/*     */     try {
/* 119 */       this.dc.writeField(upperCaseUserIdentifier, this.buffer, this.offset + 4, 12, cp, tls);
/*     */     }
/* 121 */     catch (JmqiException jmqie) {
/*     */       JmqiException toThrowJmqie;
/*     */       
/* 124 */       int reasonCode = jmqie.getReason();
/*     */       
/* 126 */       if (reasonCode == 2330) {
/*     */         
/* 128 */         String[] inserts = { null, Integer.toString(cp.getCCSID()), upperCaseUserIdentifier };
/*     */ 
/*     */         
/* 131 */         toThrowJmqie = new JmqiException(this.env, 9567, inserts, 2, 2330, (Throwable)jmqie);
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */         
/* 140 */         toThrowJmqie = jmqie;
/*     */       } 
/*     */       
/* 143 */       if (Trace.isOn) {
/* 144 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.rfp.RfpUID", "setUserIdentifier(String,JmqiCodepage,JmqiTls)", (Throwable)toThrowJmqie);
/*     */       }
/*     */       
/* 147 */       throw toThrowJmqie;
/*     */     } 
/*     */     
/* 150 */     if (Trace.isOn) {
/* 151 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpUID", "setUserIdentifier(String,JmqiCodepage,JmqiTls)");
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
/*     */   public void setPassword(String password, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 164 */     if (Trace.isOn) {
/*     */       int passwordLength;
/*     */       
/* 167 */       if (password == null) {
/* 168 */         passwordLength = -1;
/*     */       } else {
/* 170 */         passwordLength = password.length();
/*     */       } 
/* 172 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpUID", "setPassword(String,JmqiCodepage,JmqiTls)", new Object[] { passwordLength + " characters", cp, tls });
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 180 */       this.dc.writeField(password, this.buffer, this.offset + 16, 12, cp, tls, true);
/*     */     }
/* 182 */     catch (JmqiException jmqie) {
/*     */       JmqiException toThrowJmqie;
/*     */ 
/*     */       
/* 186 */       int reasonCode = jmqie.getReason();
/*     */       
/* 188 */       if (reasonCode == 2330) {
/*     */         
/* 190 */         String[] inserts = { null, Integer.toString(cp.getCCSID()) };
/*     */         
/* 192 */         toThrowJmqie = new JmqiException(this.env, 9568, inserts, 2, 2330, (Throwable)jmqie);
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */         
/* 200 */         toThrowJmqie = jmqie;
/*     */       } 
/*     */       
/* 203 */       if (Trace.isOn) {
/* 204 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.rfp.RfpUID", "setPassword(String,JmqiCodepage,JmqiTls)", (Throwable)toThrowJmqie);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 209 */       throw toThrowJmqie;
/*     */     } 
/*     */ 
/*     */     
/* 213 */     if (Trace.isOn) {
/* 214 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpUID", "setPassword(String,JmqiCodepage,JmqiTls)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPasswordOffset() {
/* 225 */     int traceRet1 = this.offset + 16;
/* 226 */     if (Trace.isOn) {
/* 227 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.RfpUID", "getPasswordOffset()", "getter", 
/* 228 */           Integer.valueOf(traceRet1));
/*     */     }
/* 230 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLongUserId(String longUserId, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 239 */     if (Trace.isOn) {
/* 240 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpUID", "setLongUserId(String,JmqiCodepage,JmqiTls)", new Object[] { longUserId, cp, tls });
/*     */     }
/*     */     
/* 243 */     this.dc.writeField(longUserId, this.buffer, this.offset + 28, 64, cp, tls);
/*     */     
/* 245 */     if (Trace.isOn) {
/* 246 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpUID", "setLongUserId(String,JmqiCodepage,JmqiTls)");
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
/*     */   public void setUserSecurityId(String userSecurityId, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 258 */     if (Trace.isOn) {
/* 259 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpUID", "setUserSecurityId(String,JmqiCodepage,JmqiTls)", new Object[] { userSecurityId, cp, tls });
/*     */     }
/*     */     
/* 262 */     this.dc.writeField(userSecurityId, this.buffer, this.offset + 92, 40, cp, tls);
/*     */     
/* 264 */     if (Trace.isOn) {
/* 265 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpUID", "setUserSecurityId(String,JmqiCodepage,JmqiTls)");
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
/*     */   public String getUserIdentifier(JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 277 */     if (Trace.isOn) {
/* 278 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpUID", "getUserIdentifier(JmqiCodepage,JmqiTls)", new Object[] { cp, tls });
/*     */     }
/*     */     
/* 281 */     String traceRet1 = this.dc.readField(this.buffer, this.offset + 4, 12, cp, tls);
/*     */     
/* 283 */     if (Trace.isOn) {
/* 284 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpUID", "getUserIdentifier(JmqiCodepage,JmqiTls)", traceRet1);
/*     */     }
/*     */     
/* 287 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPassword(JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 298 */     String traceRet1 = this.dc.readField(this.buffer, this.offset + 16, 12, cp, tls);
/*     */     
/* 300 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLongUserId(JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 310 */     if (Trace.isOn) {
/* 311 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpUID", "getLongUserId(JmqiCodepage,JmqiTls)", new Object[] { cp, tls });
/*     */     }
/*     */     
/* 314 */     String traceRet1 = this.dc.readField(this.buffer, this.offset + 28, 64, cp, tls);
/*     */     
/* 316 */     if (Trace.isOn) {
/* 317 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpUID", "getLongUserId(JmqiCodepage,JmqiTls)", traceRet1);
/*     */     }
/*     */     
/* 320 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String readStringFromBuffer(JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 330 */     if (Trace.isOn) {
/* 331 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpUID", "readStringFromBuffer(JmqiCodepage,JmqiTls)", new Object[] { cp, tls });
/*     */     }
/*     */     
/* 334 */     String traceRet1 = this.dc.readField(this.buffer, this.offset + 92, 40, cp, tls);
/*     */     
/* 336 */     if (Trace.isOn) {
/* 337 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpUID", "readStringFromBuffer(JmqiCodepage,JmqiTls)", traceRet1);
/*     */     }
/*     */     
/* 340 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkID() {
/* 347 */     if (Trace.isOn) {
/* 348 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpUID", "checkID()");
/*     */     }
/* 350 */     boolean traceRet1 = ((this.buffer[this.offset] == rfpUID_ID_ASCII[0] && this.buffer[this.offset + 1] == rfpUID_ID_ASCII[1] && this.buffer[this.offset + 2] == rfpUID_ID_ASCII[2] && this.buffer[this.offset + 3] == rfpUID_ID_ASCII[3]) || (this.buffer[this.offset] == rfpUID_ID_EBCDIC[0] && this.buffer[this.offset + 1] == rfpUID_ID_EBCDIC[1] && this.buffer[this.offset + 2] == rfpUID_ID_EBCDIC[2] && this.buffer[this.offset + 3] == rfpUID_ID_EBCDIC[3]));
/*     */ 
/*     */ 
/*     */     
/* 354 */     if (Trace.isOn) {
/* 355 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpUID", "checkID()", 
/*     */ 
/*     */ 
/*     */           
/* 359 */           Boolean.valueOf(traceRet1));
/*     */     }
/*     */     
/* 362 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\RfpUID.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */