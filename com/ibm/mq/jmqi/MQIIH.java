/*     */ package com.ibm.mq.jmqi;
/*     */ 
/*     */ import com.ibm.mq.constants.CMQC;
/*     */ import com.ibm.mq.jmqi.internal.AbstractMqHeaderStructure;
/*     */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*     */ import com.ibm.mq.jmqi.internal.JmqiStructureFormatter;
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQIIH
/*     */   extends AbstractMqHeaderStructure
/*     */ {
/*     */   public static final String sccsid3 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQIIH.java";
/*     */   private static final int SIZEOF_LTERMOVERRIDE = 4;
/*     */   private static final int SIZEOF_MFSMAPNAME = 4;
/*     */   private static final int SIZEOF_REPLYTOFORMAT = 4;
/*     */   private static final int SIZEOF_AUTHENTICATOR = 4;
/*     */   private static final int SIZEOF_TRANINSTANCEID = 4;
/*     */   private static final int SIZEOF_TRANSTATE = 1;
/*     */   private static final int SIZEOF_COMMITMODE = 1;
/*     */   private static final int SIZEOF_SECURITYSCOPE = 1;
/*     */   private static final int SIZEOF_RESERVED = 1;
/*     */   private static final String BLANK8 = "        ";
/*     */   
/*     */   static {
/*  61 */     if (Trace.isOn) {
/*  62 */       Trace.data("com.ibm.mq.jmqi.MQIIH", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQIIH.java");
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
/*  82 */   private String lTermOverride = "        ";
/*  83 */   private String mfsMapName = "        ";
/*  84 */   private String replyToFormat = "        ";
/*  85 */   private String authenticator = "        ";
/*  86 */   private byte[] tranInstanceId = CMQC.MQITII_NONE;
/*  87 */   private char tranState = ' ';
/*  88 */   private char commitMode = '0';
/*  89 */   private char securityScope = 'C';
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected MQIIH(JmqiEnvironment env) {
/*  97 */     super(env);
/*  98 */     if (Trace.isOn) {
/*  99 */       Trace.entry(this, "com.ibm.mq.jmqi.MQIIH", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/* 101 */     MQHeader mqHeader = env.newMQHeader();
/* 102 */     mqHeader.setStrucId("IIH ");
/* 103 */     mqHeader.setVersion(1);
/* 104 */     mqHeader.setStrucLength(84);
/* 105 */     setMqHeader(mqHeader);
/*     */     
/* 107 */     if (Trace.isOn) {
/* 108 */       Trace.exit(this, "com.ibm.mq.jmqi.MQIIH", "<init>(JmqiEnvironment)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected MQIIH(JmqiEnvironment env, MQHeader mqHeader) {
/* 119 */     super(env, mqHeader);
/* 120 */     if (Trace.isOn) {
/* 121 */       Trace.entry(this, "com.ibm.mq.jmqi.MQIIH", "<init>(JmqiEnvironment,MQHeader)", new Object[] { env, mqHeader });
/*     */     }
/*     */     
/* 124 */     if (Trace.isOn) {
/* 125 */       Trace.exit(this, "com.ibm.mq.jmqi.MQIIH", "<init>(JmqiEnvironment,MQHeader)");
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
/*     */   public static int getSizeV1(JmqiEnvironment env, int ptrSize) {
/* 138 */     if (Trace.isOn) {
/* 139 */       Trace.entry("com.ibm.mq.jmqi.MQIIH", "getSizeV1(JmqiEnvironment,int)", new Object[] { env, 
/* 140 */             Integer.valueOf(ptrSize) });
/*     */     }
/* 142 */     int size = MQHeader.getSize(env, ptrSize);
/* 143 */     size += 24;
/*     */ 
/*     */     
/* 146 */     if (Trace.isOn) {
/* 147 */       Trace.exit("com.ibm.mq.jmqi.MQIIH", "getSizeV1(JmqiEnvironment,int)", 
/* 148 */           Integer.valueOf(size));
/*     */     }
/* 150 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSize(JmqiEnvironment env, int version, int ptrsize) throws JmqiException {
/*     */     int size;
/*     */     JmqiException e;
/* 163 */     if (Trace.isOn) {
/* 164 */       Trace.entry("com.ibm.mq.jmqi.MQIIH", "getSize(JmqiEnvironment,int,int)", new Object[] { env, 
/* 165 */             Integer.valueOf(version), Integer.valueOf(ptrsize) });
/*     */     }
/*     */     
/* 168 */     switch (version) {
/*     */       case 1:
/* 170 */         size = getSizeV1(env, ptrsize);
/*     */         break;
/*     */       default:
/* 173 */         e = new JmqiException(env, -1, null, 2, 2142, null);
/*     */         
/* 175 */         if (Trace.isOn) {
/* 176 */           Trace.throwing("com.ibm.mq.jmqi.MQIIH", "getSize(JmqiEnvironment,int,int)", e);
/*     */         }
/* 178 */         throw e;
/*     */     } 
/*     */     
/* 181 */     if (Trace.isOn) {
/* 182 */       Trace.exit("com.ibm.mq.jmqi.MQIIH", "getSize(JmqiEnvironment,int,int)", 
/* 183 */           Integer.valueOf(size));
/*     */     }
/* 185 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/* 193 */     if (Trace.isOn)
/* 194 */       Trace.entry(this, "com.ibm.mq.jmqi.MQIIH", "getRequiredBufferSize(int,JmqiCodepage)", new Object[] {
/* 195 */             Integer.valueOf(ptrSize), cp
/*     */           }); 
/* 197 */     int size = getSize(this.env, getMqHeader().getVersion(), ptrSize);
/*     */     
/* 199 */     if (Trace.isOn) {
/* 200 */       Trace.exit(this, "com.ibm.mq.jmqi.MQIIH", "getRequiredBufferSize(int,JmqiCodepage)", 
/* 201 */           Integer.valueOf(size));
/*     */     }
/* 203 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLTermOverride() {
/* 210 */     if (Trace.isOn) {
/* 211 */       Trace.data(this, "com.ibm.mq.jmqi.MQIIH", "getLTermOverride()", "getter", this.lTermOverride);
/*     */     }
/* 213 */     return this.lTermOverride;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLTermOverride(String termOverride) {
/* 220 */     if (Trace.isOn) {
/* 221 */       Trace.data(this, "com.ibm.mq.jmqi.MQIIH", "setLTermOverride(String)", "setter", termOverride);
/*     */     }
/*     */     
/* 224 */     this.lTermOverride = termOverride;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMfsMapName() {
/* 231 */     if (Trace.isOn) {
/* 232 */       Trace.data(this, "com.ibm.mq.jmqi.MQIIH", "getMfsMapName()", "getter", this.mfsMapName);
/*     */     }
/* 234 */     return this.mfsMapName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMfsMapName(String mfsMapName) {
/* 241 */     if (Trace.isOn) {
/* 242 */       Trace.data(this, "com.ibm.mq.jmqi.MQIIH", "setMfsMapName(String)", "setter", mfsMapName);
/*     */     }
/* 244 */     this.mfsMapName = mfsMapName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getReplyToFormat() {
/* 251 */     if (Trace.isOn) {
/* 252 */       Trace.data(this, "com.ibm.mq.jmqi.MQIIH", "getReplyToFormat()", "getter", this.replyToFormat);
/*     */     }
/* 254 */     return this.replyToFormat;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReplyToFormat(String replyToFormat) {
/* 261 */     if (Trace.isOn) {
/* 262 */       Trace.data(this, "com.ibm.mq.jmqi.MQIIH", "setReplyToFormat(String)", "setter", replyToFormat);
/*     */     }
/*     */     
/* 265 */     this.replyToFormat = replyToFormat;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAuthenticator() {
/* 272 */     if (Trace.isOn) {
/* 273 */       Trace.data(this, "com.ibm.mq.jmqi.MQIIH", "getAuthenticator()", "getter", this.authenticator);
/*     */     }
/* 275 */     return this.authenticator;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAuthenticator(String authenticator) {
/* 282 */     if (Trace.isOn) {
/* 283 */       Trace.data(this, "com.ibm.mq.jmqi.MQIIH", "setAuthenticator(String)", "setter", authenticator);
/*     */     }
/*     */     
/* 286 */     this.authenticator = authenticator;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getTranInstanceId() {
/* 293 */     if (Trace.isOn) {
/* 294 */       Trace.data(this, "com.ibm.mq.jmqi.MQIIH", "getTranInstanceId()", "getter", this.tranInstanceId);
/*     */     }
/* 296 */     return this.tranInstanceId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTranInstanceId(byte[] tranInstanceId) {
/* 303 */     if (Trace.isOn) {
/* 304 */       Trace.data(this, "com.ibm.mq.jmqi.MQIIH", "setTranInstanceId(byte [ ])", "setter", tranInstanceId);
/*     */     }
/*     */     
/* 307 */     this.tranInstanceId = tranInstanceId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char getTranState() {
/* 314 */     if (Trace.isOn) {
/* 315 */       Trace.data(this, "com.ibm.mq.jmqi.MQIIH", "getTranState()", "getter", 
/* 316 */           Character.valueOf(this.tranState));
/*     */     }
/* 318 */     return this.tranState;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTranState(char tranState) {
/* 325 */     if (Trace.isOn) {
/* 326 */       Trace.data(this, "com.ibm.mq.jmqi.MQIIH", "setTranState(char)", "setter", 
/* 327 */           Character.valueOf(tranState));
/*     */     }
/* 329 */     this.tranState = tranState;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char getCommitMode() {
/* 336 */     if (Trace.isOn) {
/* 337 */       Trace.data(this, "com.ibm.mq.jmqi.MQIIH", "getCommitMode()", "getter", 
/* 338 */           Character.valueOf(this.commitMode));
/*     */     }
/* 340 */     return this.commitMode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCommitMode(char commitMode) {
/* 347 */     if (Trace.isOn) {
/* 348 */       Trace.data(this, "com.ibm.mq.jmqi.MQIIH", "setCommitMode(char)", "setter", 
/* 349 */           Character.valueOf(commitMode));
/*     */     }
/* 351 */     this.commitMode = commitMode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char getSecurityScope() {
/* 358 */     if (Trace.isOn) {
/* 359 */       Trace.data(this, "com.ibm.mq.jmqi.MQIIH", "getSecurityScope()", "getter", 
/* 360 */           Character.valueOf(this.securityScope));
/*     */     }
/* 362 */     return this.securityScope;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSecurityScope(char securityScope) {
/* 369 */     if (Trace.isOn) {
/* 370 */       Trace.data(this, "com.ibm.mq.jmqi.MQIIH", "setSecurityScope(char)", "setter", 
/* 371 */           Character.valueOf(securityScope));
/*     */     }
/* 373 */     this.securityScope = securityScope;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 381 */     if (Trace.isOn) {
/* 382 */       Trace.entry(this, "com.ibm.mq.jmqi.MQIIH", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 384 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 386 */     int pos = offset;
/* 387 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 389 */     MQHeader mqHeader = getMqHeader();
/* 390 */     int version = mqHeader.getVersion();
/* 391 */     int strucLength = getSize(this.env, version, ptrSize);
/* 392 */     mqHeader.setStrucLength(strucLength);
/* 393 */     pos += mqHeader.writeToBuffer(buffer, offset, ptrSize, swap, cp, tls);
/*     */     
/* 395 */     dc.writeMQField(this.lTermOverride, buffer, pos, 4, cp, tls);
/* 396 */     pos += 4;
/*     */     
/* 398 */     dc.writeMQField(this.mfsMapName, buffer, pos, 4, cp, tls);
/* 399 */     pos += 4;
/*     */     
/* 401 */     dc.writeMQField(this.replyToFormat, buffer, pos, 4, cp, tls);
/* 402 */     pos += 4;
/*     */     
/* 404 */     dc.writeMQField(this.authenticator, buffer, pos, 4, cp, tls);
/* 405 */     pos += 4;
/*     */     
/* 407 */     System.arraycopy(buffer, pos, this.tranInstanceId, 0, 4);
/* 408 */     pos += 4;
/*     */     
/* 410 */     char[] chars = { this.tranState, this.commitMode, this.securityScope, ' ' };
/* 411 */     int charsSize = 4;
/* 412 */     dc.writeMQField(new String(chars), buffer, pos, charsSize, cp, tls);
/* 413 */     pos += charsSize;
/*     */     
/* 415 */     if (Trace.isOn) {
/* 416 */       Trace.exit(this, "com.ibm.mq.jmqi.MQIIH", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 417 */           Integer.valueOf(pos));
/*     */     }
/* 419 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 427 */     if (Trace.isOn) {
/* 428 */       Trace.entry(this, "com.ibm.mq.jmqi.MQIIH", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 430 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/*     */     
/* 433 */     MQHeader mqHeader = getMqHeader();
/* 434 */     int pos = mqHeader.readFromBuffer(buffer, offset, ptrSize, swap, cp, tls);
/*     */     
/* 436 */     String strucId = mqHeader.getStrucId();
/* 437 */     if (!strucId.equals("IIH ")) {
/* 438 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2148, null);
/*     */       
/* 440 */       if (Trace.isOn) {
/* 441 */         Trace.throwing(this, "com.ibm.mq.jmqi.MQIIH", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", traceRet1);
/*     */       }
/*     */       
/* 444 */       throw traceRet1;
/*     */     } 
/*     */ 
/*     */     
/* 448 */     pos += readBodyFromBuffer(buffer, pos, ptrSize, swap, cp, tls);
/*     */     
/* 450 */     if (Trace.isOn) {
/* 451 */       Trace.exit(this, "com.ibm.mq.jmqi.MQIIH", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 452 */           Integer.valueOf(pos));
/*     */     }
/* 454 */     return pos;
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
/*     */   public int readBodyFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 470 */     if (Trace.isOn) {
/* 471 */       Trace.entry(this, "com.ibm.mq.jmqi.MQIIH", "readBodyFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 473 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 475 */     int pos = offset;
/* 476 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 477 */     MQHeader mqHeader = getMqHeader();
/*     */     
/* 479 */     this.lTermOverride = dc.readMQField(buffer, pos, 4, cp, tls);
/* 480 */     pos += 4;
/*     */     
/* 482 */     this.mfsMapName = dc.readMQField(buffer, pos, 4, cp, tls);
/* 483 */     pos += 4;
/*     */     
/* 485 */     this.replyToFormat = dc.readMQField(buffer, pos, 4, cp, tls);
/* 486 */     pos += 4;
/*     */     
/* 488 */     this.authenticator = dc.readMQField(buffer, pos, 4, cp, tls);
/* 489 */     pos += 4;
/*     */     
/* 491 */     System.arraycopy(this.tranInstanceId, 0, buffer, pos, 4);
/* 492 */     pos += 4;
/*     */     
/* 494 */     int charsSize = 4;
/* 495 */     String string = dc.readMQField(buffer, pos, charsSize, cp, tls);
/* 496 */     this.tranState = string.charAt(0);
/* 497 */     this.commitMode = string.charAt(1);
/* 498 */     this.securityScope = string.charAt(2);
/* 499 */     pos += charsSize;
/* 500 */     if (pos - offset + MQHeader.getSize(this.env, ptrSize) != mqHeader.getStrucLength()) {
/*     */       
/* 502 */       JmqiException e = new JmqiException(this.env, -1, null, 2, 2142, null);
/*     */       
/* 504 */       if (Trace.isOn) {
/* 505 */         Trace.throwing(this, "com.ibm.mq.jmqi.MQIIH", "readBodyFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", e);
/*     */       }
/*     */       
/* 508 */       throw e;
/*     */     } 
/*     */     
/* 511 */     if (Trace.isOn) {
/* 512 */       Trace.exit(this, "com.ibm.mq.jmqi.MQIIH", "readBodyFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 513 */           Integer.valueOf(pos));
/*     */     }
/* 515 */     return pos;
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
/*     */   public void addFieldsToFormatter(JmqiStructureFormatter fmt) {
/* 527 */     getMqHeader().addFieldsToFormatter(fmt);
/* 528 */     fmt.add("lTermOverride", this.lTermOverride);
/* 529 */     fmt.add("mfsMapName", this.mfsMapName);
/* 530 */     fmt.add("replyToFormat", this.replyToFormat);
/* 531 */     fmt.add("authenticator", this.authenticator);
/* 532 */     fmt.add("tranInstanceId", this.tranInstanceId);
/* 533 */     fmt.add("tranState", this.tranState);
/* 534 */     fmt.add("commitMode", this.commitMode);
/* 535 */     fmt.add("securityScope", this.securityScope);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\MQIIH.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */