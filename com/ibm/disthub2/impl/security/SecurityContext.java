/*     */ package com.ibm.disthub2.impl.security;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.BaseConfig;
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
/*     */ import com.ibm.disthub2.impl.util.Assert;
/*     */ import com.ibm.disthub2.impl.util.Hex;
/*     */ import com.ibm.disthub2.spi.ClientExceptionConstants;
/*     */ import com.ibm.disthub2.spi.ExceptionBuilder;
/*     */ import com.ibm.disthub2.spi.LogConstants;
/*     */ import com.ibm.disthub2.spi.Principal;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SecurityContext
/*     */   implements Cloneable, LogConstants, ClientExceptionConstants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/* 104 */   private static final DebugObject debug = new DebugObject("SecurityContext");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean useQOP;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Principal m_principal;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int m_clientId;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String m_cid;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String m_pid;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public volatile boolean canChangeCPId = true;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean m_isClientIdValid;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private byte[] m_masterSecret;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 152 */   private Object[] serverMACKey = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 160 */   private Object[] clientMACKey = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 166 */   private Object serverEDKey = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 172 */   private Object clientEDKey = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private MessageProtection mp;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 187 */   private static byte[] serverMAC_KM = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 192 */   private static byte[] clientMAC_KM = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 197 */   private static byte[] serverED_KM = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 202 */   private static byte[] clientED_KM = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 208 */   private long sendIntegrityCounter = 1L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 214 */   private long receiveIntegrityCounter = 1L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private byte[] encryptIV;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private byte[] decryptIV;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SecurityContext(Principal principal, byte[] masterKey, boolean QOP) throws CryptoInstantiationException {
/* 233 */     this(principal, masterKey, QOP, null);
/*     */ 
/*     */     
/* 236 */     if (debug.debugIt(32)) {
/* 237 */       debug.debug(-165922073994779L, "SecurityContext", principal, masterKey, Boolean.valueOf(QOP));
/*     */     }
/*     */ 
/*     */     
/* 241 */     if (debug.debugIt(64)) {
/* 242 */       debug.debug(-142394261359015L, "SecurityContext");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SecurityContext(Principal principal, byte[] masterKey, boolean QOP, MessageProtection messageProtection) throws CryptoInstantiationException {
/* 250 */     if (debug.debugIt(32)) {
/* 251 */       debug.debug(-165922073994779L, "SecurityContext", principal, masterKey, Boolean.valueOf(QOP), messageProtection);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 260 */       serverMAC_KM = "Auth-Server".getBytes("UTF-8");
/* 261 */       clientMAC_KM = "Auth-Client".getBytes("UTF-8");
/* 262 */       serverED_KM = "Encrypt-Server".getBytes("UTF-8");
/* 263 */       clientED_KM = "Encrypt-Client".getBytes("UTF-8");
/*     */     }
/* 265 */     catch (UnsupportedEncodingException e) {
/*     */       
/* 267 */       serverMAC_KM = "Auth-Server".getBytes();
/* 268 */       clientMAC_KM = "Auth-Client".getBytes();
/* 269 */       serverED_KM = "Encrypt-Server".getBytes();
/* 270 */       clientED_KM = "Encrypt-Client".getBytes();
/*     */     } 
/*     */     
/* 273 */     this.m_principal = principal;
/* 274 */     this.m_isClientIdValid = false;
/* 275 */     this.mp = messageProtection;
/* 276 */     this.useQOP = QOP;
/* 277 */     if (QOP) {
/* 278 */       if (masterKey == null) {
/* 279 */         throw new CryptoInstantiationException(ExceptionBuilder.buildReasonString(501365583, null));
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 284 */       if (this.mp == null) {
/* 285 */         this.mp = MessageProtection.instantiateLibrary((BaseConfig.getBaseConfig()).QOP_MSGHANDLER_LIBRARY, 
/* 286 */             (BaseConfig.getBaseConfig()).QOP_MSGHANDLER_OPTIONS);
/*     */       }
/* 288 */       buildKeySet(masterKey);
/*     */     } 
/*     */ 
/*     */     
/* 292 */     if (debug.debugIt(64)) {
/* 293 */       debug.debug(-142394261359015L, "SecurityContext");
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
/*     */   private void buildKeySet(byte[] mKey) throws CryptoInstantiationException {
/* 315 */     if (debug.debugIt(32)) {
/* 316 */       debug.debug(-165922073994779L, "buildKeySet", mKey);
/*     */     }
/*     */ 
/*     */     
/* 320 */     this.m_masterSecret = new byte[mKey.length];
/* 321 */     System.arraycopy(mKey, 0, this.m_masterSecret, 0, mKey.length);
/*     */     
/* 323 */     if (this.useQOP) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 329 */       BaseConfig config = BaseConfig.getBaseConfig();
/* 330 */       MessageProtection keyBuilder = MessageProtection.instantiateLibrary(config.QOP_KEYSETUP_LIBRARY, config.QOP_KEYSETUP_OPTIONS);
/*     */       
/* 332 */       byte[] rawServerMACKey = new byte[keyBuilder.digestLength()];
/* 333 */       byte[] rawClientMACKey = new byte[keyBuilder.digestLength()];
/*     */       
/* 335 */       byte[] rawServerEDKey = new byte[keyBuilder.digestLength()];
/* 336 */       byte[] rawClientEDKey = new byte[keyBuilder.digestLength()];
/*     */       
/* 338 */       Object[] cookedmkey = keyBuilder.hmacPrepareKey(mKey);
/*     */ 
/*     */ 
/*     */       
/* 342 */       keyBuilder.hmac(cookedmkey, serverED_KM, 0, serverED_KM.length, rawServerEDKey, 0);
/* 343 */       keyBuilder.hmac(cookedmkey, clientED_KM, 0, clientED_KM.length, rawClientEDKey, 0);
/*     */       
/* 345 */       keyBuilder.hmac(cookedmkey, serverMAC_KM, 0, serverMAC_KM.length, rawServerMACKey, 0);
/* 346 */       keyBuilder.hmac(cookedmkey, clientMAC_KM, 0, clientMAC_KM.length, rawClientMACKey, 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 355 */       this.serverMACKey = this.mp.hmacPrepareKey(rawServerMACKey);
/* 356 */       this.clientMACKey = this.mp.hmacPrepareKey(rawClientMACKey);
/*     */ 
/*     */       
/* 359 */       int ekeylength = this.mp.keySize();
/*     */       
/* 361 */       if (ekeylength > rawServerEDKey.length) {
/* 362 */         ekeylength = rawServerEDKey.length;
/*     */       }
/*     */       
/* 365 */       this.serverEDKey = this.mp.generateKey(rawServerEDKey, 0, ekeylength);
/* 366 */       this.clientEDKey = this.mp.generateKey(rawClientEDKey, 0, ekeylength);
/*     */ 
/*     */       
/* 369 */       this.encryptIV = new byte[this.mp.blockSize()];
/* 370 */       this.decryptIV = new byte[this.mp.blockSize()];
/*     */ 
/*     */       
/* 373 */       if (debug.debugIt(16)) {
/* 374 */         debug.debug(-153415734321212L, "buildKeySet", "mKey=", Hex.toString(mKey));
/* 375 */         debug.debug(-153415734321212L, "buildKeySet", "serverkeys=", Hex.toString(rawServerEDKey, 0, ekeylength), Hex.toString(rawServerMACKey));
/* 376 */         debug.debug(-153415734321212L, "buildKeySet", "clientkeys=", Hex.toString(rawClientEDKey, 0, ekeylength), Hex.toString(rawClientMACKey));
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 382 */     if (debug.debugIt(64)) {
/* 383 */       debug.debug(-142394261359015L, "buildKeySet");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCId() {
/* 391 */     return this.m_cid;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void setCId(String cid) throws InvalidCPIDException {
/* 399 */     int i = cid.indexOf("@");
/* 400 */     if (i < 0 || !cid.substring(0, i).equals(this.m_principal.getName())) {
/* 401 */       throw new InvalidCPIDException();
/*     */     }
/* 403 */     this.m_cid = cid;
/*     */   }
/*     */   
/*     */   public String getPId() {
/* 407 */     return this.m_pid;
/*     */   }
/*     */   
/*     */   public synchronized void setPId(String pid) throws InvalidCPIDException {
/* 411 */     int i = pid.indexOf("@");
/* 412 */     if (!pid.substring(0, i).equals(this.m_principal.getName())) {
/* 413 */       throw new InvalidCPIDException();
/*     */     }
/* 415 */     this.m_pid = pid;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isValidReconnId(String reconnId) {
/* 422 */     int i = 6 + "@".length();
/* 423 */     if (reconnId.substring(0, reconnId.length() - i).equals(this.m_cid)) {
/* 424 */       return true;
/*     */     }
/* 426 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void setClientId(int id) {
/* 437 */     if (debug.debugIt(32)) {
/* 438 */       debug.debug(-165922073994779L, "setClientId", Integer.valueOf(id));
/*     */     }
/*     */ 
/*     */     
/* 442 */     Assert.condition(!this.m_isClientIdValid);
/* 443 */     this.m_clientId = id;
/* 444 */     this.m_isClientIdValid = true;
/*     */ 
/*     */     
/* 447 */     if (debug.debugIt(64)) {
/* 448 */       debug.debug(-142394261359015L, "setClientId");
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
/*     */   public MessageProtection getMP() {
/* 463 */     return this.mp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isClientIdValid() {
/* 474 */     return this.m_isClientIdValid;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Principal getPrincipal() {
/* 483 */     return this.m_principal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getClientId() {
/* 492 */     return this.m_clientId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getNextSendCount() {
/* 503 */     return this.sendIntegrityCounter++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getNextRcvCount() {
/* 514 */     return this.receiveIntegrityCounter++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object[] getServerMAC() {
/* 525 */     return this.serverMACKey;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object[] getClientMAC() {
/* 536 */     return this.clientMACKey;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getServerKey() {
/* 545 */     return this.serverEDKey;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getClientKey() {
/* 554 */     return this.clientEDKey;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getEncryptIV() {
/* 561 */     return this.encryptIV;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getDecryptIV() {
/* 568 */     return this.decryptIV;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized SecurityContext copy() {
/* 577 */     SecurityContext copy = null;
/*     */     
/*     */     try {
/* 580 */       copy = new SecurityContext(this.m_principal, this.m_masterSecret, this.useQOP);
/*     */     }
/* 582 */     catch (CryptoInstantiationException e) {
/*     */ 
/*     */       
/* 585 */       Assert.condition(false);
/*     */     } 
/* 587 */     copy.m_clientId = this.m_clientId;
/* 588 */     copy.m_isClientIdValid = this.m_isClientIdValid;
/* 589 */     copy.sendIntegrityCounter = this.sendIntegrityCounter;
/* 590 */     copy.receiveIntegrityCounter = this.receiveIntegrityCounter;
/*     */     
/* 592 */     return copy;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String int2chars(int i) {
/* 598 */     char c1 = (char)(i >> 16);
/* 599 */     char c2 = (char)i;
/* 600 */     return "" + c1 + c2;
/*     */   }
/*     */   
/*     */   public static int chars2Int(String s) {
/* 604 */     Assert.condition((s.length() == 2));
/* 605 */     int i1 = s.charAt(0);
/* 606 */     int i2 = s.charAt(1);
/* 607 */     return (i1 << 16) + i2;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String long2chars(long l) {
/* 613 */     char c1 = (char)(int)(l >> 48L);
/* 614 */     char c2 = (char)(int)(l >> 32L);
/* 615 */     char c3 = (char)(int)(l >> 16L);
/* 616 */     char c4 = (char)(int)l;
/* 617 */     return "" + c1 + c2 + c3 + c4;
/*     */   }
/*     */   
/*     */   public static long chars2Long(String s) {
/* 621 */     Assert.condition((s.length() == 4));
/* 622 */     long l1 = s.charAt(0);
/* 623 */     long l2 = s.charAt(1);
/* 624 */     long l3 = s.charAt(2);
/* 625 */     long l4 = s.charAt(3);
/* 626 */     return (l1 << 48L) + (l2 << 32L) + (l3 << 16L) + l4;
/*     */   }
/*     */   
/*     */   public static String cid2Native(String cid) {
/* 630 */     int len0 = cid.length() - 6;
/* 631 */     String rslt = cid.substring(0, len0);
/* 632 */     rslt = rslt + Integer.toHexString(chars2Int(cid.substring(len0, len0 + 2)));
/* 633 */     rslt = rslt + Long.toHexString(chars2Long(cid.substring(len0 + 2)));
/* 634 */     return rslt;
/*     */   }
/*     */   
/*     */   public static String rid2Native(String rid) {
/* 638 */     if (rid == null) {
/* 639 */       return "";
/*     */     }
/* 641 */     int len0 = rid.length() - 6;
/* 642 */     String rslt = cid2Native(rid.substring(0, len0 - 1));
/* 643 */     rslt = rslt + "@";
/* 644 */     rslt = rslt + Integer.toHexString(chars2Int(rid.substring(len0, len0 + 2)));
/* 645 */     rslt = rslt + Long.toHexString(chars2Long(rid.substring(len0 + 2)));
/* 646 */     return rslt;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\security\SecurityContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */