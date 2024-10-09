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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RfpCAUT
/*     */   extends RfpStructure
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpCAUT.java";
/*     */   
/*     */   static {
/*  40 */     if (Trace.isOn) {
/*  41 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.RfpCAUT", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpCAUT.java");
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
/*     */   public RfpCAUT(JmqiEnvironment env, byte[] buffer, int offset) {
/*  58 */     super(env, buffer, offset);
/*     */   }
/*     */   
/*  61 */   static final byte[] rfpCAUT_ID = new byte[] { 67, 65, 85, 84 };
/*  62 */   static final byte[] rfpCAUT_ID_ASCII = new byte[] { 67, 65, 85, 84 };
/*  63 */   static final byte[] rfpCAUT_ID_EBCDIC = new byte[] { -61, -63, -28, -29 };
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int AUTHTYPE_OFFSET = 4;
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int USERIDMAXLEN_OFFSET = 8;
/*     */ 
/*     */   
/*     */   private static final int PASSWDMAXLEN_OFFSET = 12;
/*     */ 
/*     */   
/*     */   private static final int USERIDLEN_OFFSET = 16;
/*     */ 
/*     */   
/*     */   private static final int PASSWORDLEN_OFFSET = 20;
/*     */ 
/*     */   
/*     */   private static final int USERID_OFFSET = 24;
/*     */ 
/*     */   
/*     */   public static final int SIZE_CURRENT = 24;
/*     */ 
/*     */   
/*     */   private int pwo;
/*     */ 
/*     */ 
/*     */   
/*     */   public void initEyecatcher() {
/*  94 */     System.arraycopy(rfpCAUT_ID, 0, this.buffer, this.offset, rfpCAUT_ID.length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAuthType(int authType, boolean swap) {
/* 101 */     if (Trace.isOn)
/* 102 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpCAUT", "setAuthType(int,boolean)", new Object[] {
/* 103 */             Integer.valueOf(authType), Boolean.valueOf(swap)
/*     */           }); 
/* 105 */     this.dc.writeI32(authType, this.buffer, this.offset + 4, swap);
/*     */     
/* 107 */     if (Trace.isOn) {
/* 108 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpCAUT", "setAuthType(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUserIDMaxLen(int userIDMaxLen, boolean swap) {
/* 117 */     if (Trace.isOn)
/* 118 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpCAUT", "setUserIDMaxLen(int,boolean)", new Object[] {
/* 119 */             Integer.valueOf(userIDMaxLen), Boolean.valueOf(swap)
/*     */           }); 
/* 121 */     this.dc.writeI32(userIDMaxLen, this.buffer, this.offset + 8, swap);
/*     */     
/* 123 */     if (Trace.isOn) {
/* 124 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpCAUT", "setUserIDMaxLen(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPasswdMaxLen(int passwdMaxLen, boolean swap) {
/* 133 */     if (Trace.isOn)
/* 134 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpCAUT", "setPasswdMaxLen(int,boolean)", new Object[] {
/* 135 */             Integer.valueOf(passwdMaxLen), Boolean.valueOf(swap)
/*     */           }); 
/* 137 */     this.dc.writeI32(passwdMaxLen, this.buffer, this.offset + 12, swap);
/*     */     
/* 139 */     if (Trace.isOn) {
/* 140 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpCAUT", "setPasswdMaxLen(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUserIDLen(int userIDLen, boolean swap) {
/* 149 */     if (Trace.isOn)
/* 150 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpCAUT", "setUserIDLen(int,boolean)", new Object[] {
/* 151 */             Integer.valueOf(userIDLen), Boolean.valueOf(swap)
/*     */           }); 
/* 153 */     this.dc.writeI32(userIDLen, this.buffer, this.offset + 16, swap);
/*     */     
/* 155 */     if (Trace.isOn) {
/* 156 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpCAUT", "setUserIDLen(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int setPasswordLen(int passwordLen, boolean swap) {
/* 167 */     if (Trace.isOn)
/* 168 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpCAUT", "setPasswordLen(int,boolean)", new Object[] {
/* 169 */             Integer.valueOf(passwordLen), Boolean.valueOf(swap)
/*     */           }); 
/* 171 */     int pwlo = this.offset + 20;
/* 172 */     this.dc.writeI32(passwordLen, this.buffer, pwlo, swap);
/*     */     
/* 174 */     if (Trace.isOn) {
/* 175 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpCAUT", "setPasswordLen(int,boolean)", 
/* 176 */           Integer.valueOf(pwlo));
/*     */     }
/* 178 */     return pwlo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUserId(String userID, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 188 */     if (Trace.isOn) {
/* 189 */       Trace.data(this, "setUserId(String, JmqiCodepage, JmqiTls)", userID);
/*     */     }
/*     */     
/* 192 */     this.dc.writeMQField(userID, this.buffer, this.offset + 24, userID.length(), cp, tls);
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
/*     */   public int setPassword(String password, int passwdOffset, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 204 */     if (Trace.isOn)
/*     */     {
/* 206 */       Trace.data(this, "setPassword(String, JmqiCodepage, JmqiTls)", "********");
/*     */     }
/*     */     
/* 209 */     this.pwo = this.offset + 24 + passwdOffset;
/* 210 */     this.dc.writeMQField(password, true, this.buffer, this.pwo, password.length(), cp, tls);
/*     */     
/* 212 */     return this.offset + 24 + passwdOffset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPasswordLen(boolean swap) {
/* 221 */     int result = this.dc.readI32(this.buffer, this.offset + 20, swap);
/* 222 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\RfpCAUT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */