/*     */ package com.ibm.mq.ese.config;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
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
/*     */ public class PasswordObject
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/config/PasswordObject.java";
/*     */   private char[] password;
/*     */   
/*     */   static {
/*  36 */     if (Trace.isOn) {
/*  37 */       Trace.data("com.ibm.mq.ese.config.PasswordObject", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/config/PasswordObject.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   private PasswordType protectionType = PasswordType.NULL;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char[] getPassword() {
/*  52 */     return this.password;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPassword(char[] password) {
/*  62 */     this.password = new char[password.length];
/*  63 */     System.arraycopy(password, 0, this.password, 0, password.length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PasswordType getProtectionType() {
/*  70 */     return this.protectionType;
/*     */   }
/*     */   
/*     */   public void setProtectionType(PasswordType protectionType) {
/*  74 */     this.protectionType = protectionType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum PasswordType
/*     */   {
/*  82 */     NULL, PLAINTEXT, OLDPROTECTED, NEWPROTECTED;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void wipeData() {
/*  89 */     Arrays.fill(this.password, false);
/*  90 */     this.protectionType = PasswordType.NULL;
/*     */   }
/*     */   
/*     */   public PasswordObject(char[] password, PasswordType type) {
/*  94 */     setPassword(password);
/*  95 */     this.protectionType = type;
/*     */   }
/*     */   
/*     */   public PasswordObject() {
/*  99 */     setPassword(new char[0]);
/* 100 */     this.protectionType = PasswordType.NULL;
/*     */   }
/*     */   
/*     */   public PasswordObject(char[] password) {
/* 104 */     setPassword(password);
/* 105 */     this.protectionType = PasswordType.PLAINTEXT;
/*     */   }
/*     */ 
/*     */   
/*     */   public PasswordObject(PasswordObject passwordObject) {
/* 110 */     setPassword(passwordObject.getPassword());
/* 111 */     this.protectionType = passwordObject.getProtectionType();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void replace(PasswordObject passwordObject) {
/* 121 */     setPassword(passwordObject.getPassword());
/* 122 */     this.protectionType = passwordObject.getProtectionType();
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\config\PasswordObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */