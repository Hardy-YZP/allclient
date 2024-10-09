/*     */ package com.ibm.mq.jmqi.system;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.internal.AbstractMqiStructure;
/*     */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*     */ import com.ibm.mq.jmqi.internal.JmqiStructureFormatter;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class LpiCHLAUTHQuery
/*     */   extends AbstractMqiStructure
/*     */   implements Cloneable
/*     */ {
/*     */   private String strucId;
/*     */   private int version;
/*     */   private int functionType;
/*     */   private byte allow;
/*     */   private byte checkClient;
/*     */   private String channelName;
/*     */   private String iPAddress;
/*     */   private String clientUserId;
/*     */   private String certDN;
/*     */   private String certIssuerDN;
/*     */   private static final int MQ_EXTRN_CONN_NAME_LENGTH = 48;
/*     */   private static final int SIZEOF_StrucId = 4;
/*     */   private static final int SIZEOF_Version = 4;
/*     */   private static final int SIZEOF_FunctionType = 4;
/*     */   private static final int SIZEOF_Allow = 1;
/*     */   private static final int SIZEOF_ObjectType = 1;
/*     */   private static final int SIZEOF_WarnObjectType = 1;
/*     */   private static final int SIZEOF_CheckClient = 1;
/*     */   private static final int SIZEOF_WarnProfileLength = 2;
/*     */   private static final int SIZEOF_WarnObjectLength = 2;
/*     */   private static final int SIZEOF_ProfileLength = 2;
/*     */   private static final int SIZEOF_ObjectLength = 2;
/*     */   private static final int SIZEOF_DataLength = 4;
/*     */   private static final int SIZEOF_MqReasonCode = 4;
/*     */   private static final int SIZEOF_RrcEValue = 4;
/*     */   private static final int SIZEOF_HostnameCount = 4;
/*     */   private static final int SIZEOF_ChannelName = 21;
/*     */   private static final int SIZEOF_IPAddress = 49;
/* 106 */   private static final int SIZEOF_ClientUserId = ((JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_WINDOWS) ? 64 : 12) + 1;
/*     */   
/*     */   private static final int SIZEOF_Hostnames = 49;
/*     */   
/*     */   private static final int SIZEOF_QmgrName = 49;
/*     */   
/*     */   private static final int SIZEOF_CertDN = 1025;
/*     */   private static final int SIZEOF_CertIssuerDN = 1025;
/* 114 */   private static final int SIZE_V1 = 110 + SIZEOF_ClientUserId + 49 + 49 + 1025 + 1025;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int lpiCHLAUTHQUERY_V1 = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int lpiCHLAUTHQUERY_CURRENT_VERSION = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String lpiCHLAUTHQUERY_STRUCID = "QCHA";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int lpiCHLAUTH_FUNC_TYPE_ADDR = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int lpiCHLAUTH_FUNC_TYPE_MAP = 2;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int lpiCHLAUTH_FUNC_TYPE_USER = 3;
/*     */ 
/*     */ 
/*     */   
/* 147 */   private JmqiDC dc = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LpiCHLAUTHQuery(JmqiEnvironment env, int version) {
/* 154 */     super(env);
/* 155 */     if (Trace.isOn) {
/* 156 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiCHLAUTHQuery", "<init>(JmqiEnvironment,int)", new Object[] { env, 
/* 157 */             Integer.valueOf(version) });
/*     */     }
/* 159 */     this.strucId = "QCHA";
/* 160 */     this.version = version;
/* 161 */     if (env instanceof JmqiSystemEnvironment) {
/* 162 */       this.dc = ((JmqiSystemEnvironment)env).getDC();
/*     */     }
/* 164 */     if (Trace.isOn) {
/* 165 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiCHLAUTHQuery", "<init>(JmqiEnvironment,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LpiCHLAUTHQuery(JmqiEnvironment env) {
/* 174 */     this(env, 1);
/* 175 */     if (Trace.isOn) {
/* 176 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiCHLAUTHQuery", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/*     */     
/* 179 */     if (Trace.isOn) {
/* 180 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiCHLAUTHQuery", "<init>(JmqiEnvironment)");
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
/*     */   public int getSize(JmqiEnvironment env, int ptrSize) throws JmqiException {
/* 193 */     if (Trace.isOn) {
/* 194 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiCHLAUTHQuery", "getSize(JmqiEnvironment,int)", new Object[] { env, 
/* 195 */             Integer.valueOf(ptrSize) });
/*     */     }
/* 197 */     switch (this.version) {
/*     */       case 1:
/* 199 */         if (Trace.isOn) {
/* 200 */           Trace.exit(this, "com.ibm.mq.jmqi.system.LpiCHLAUTHQuery", "getSize(JmqiEnvironment,int)", 
/* 201 */               Integer.valueOf(SIZE_V1), 1);
/*     */         }
/* 203 */         return SIZE_V1;
/*     */     } 
/*     */     
/* 206 */     if (Trace.isOn) {
/* 207 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiCHLAUTHQuery", "getSize(JmqiEnvironment,int)", 
/* 208 */           Integer.valueOf(SIZE_V1), 2);
/*     */     }
/* 210 */     return SIZE_V1;
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
/*     */   public int getRequiredBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/* 224 */     if (Trace.isOn)
/* 225 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiCHLAUTHQuery", "getRequiredBufferSize(int,JmqiCodepage)", new Object[] {
/* 226 */             Integer.valueOf(ptrSize), cp
/*     */           }); 
/* 228 */     int traceRet1 = getSize(this.env, ptrSize);
/* 229 */     if (Trace.isOn) {
/* 230 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiCHLAUTHQuery", "getRequiredBufferSize(int,JmqiCodepage)", 
/* 231 */           Integer.valueOf(traceRet1));
/*     */     }
/* 233 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addFieldsToFormatter(JmqiStructureFormatter fmt) {
/* 241 */     fmt.add("StrucId", this.strucId);
/* 242 */     fmt.add("Version", this.version);
/* 243 */     fmt.add("functionType", this.functionType);
/* 244 */     fmt.add("allow", this.allow);
/* 245 */     fmt.add("checkClient", this.checkClient);
/* 246 */     fmt.add("channelName", this.channelName);
/* 247 */     fmt.add("iPAddress", this.iPAddress);
/* 248 */     fmt.add("clientUserId", this.clientUserId);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFunctionType() {
/* 256 */     if (Trace.isOn) {
/* 257 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCHLAUTHQuery", "getFunctionType()", "getter", 
/* 258 */           Integer.valueOf(this.functionType));
/*     */     }
/* 260 */     return this.functionType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFunctionType(int functionType) {
/* 267 */     if (Trace.isOn) {
/* 268 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCHLAUTHQuery", "setFunctionType(int)", "setter", 
/* 269 */           Integer.valueOf(functionType));
/*     */     }
/* 271 */     this.functionType = functionType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean accessAllowed() {
/* 278 */     if (Trace.isOn) {
/* 279 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiCHLAUTHQuery", "accessAllowed()");
/*     */     }
/* 281 */     boolean traceRet1 = (this.allow == 1);
/* 282 */     if (Trace.isOn) {
/* 283 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiCHLAUTHQuery", "accessAllowed()", 
/* 284 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 286 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCheckClient() {
/* 293 */     if (Trace.isOn) {
/* 294 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCHLAUTHQuery", "getCheckClient()", "getter", 
/* 295 */           Integer.valueOf(this.checkClient));
/*     */     }
/* 297 */     return this.checkClient;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getChannelName() {
/* 304 */     if (Trace.isOn) {
/* 305 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCHLAUTHQuery", "getChannelName()", "getter", this.channelName);
/*     */     }
/*     */     
/* 308 */     return this.channelName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setChannelName(String channelName) {
/* 315 */     if (Trace.isOn) {
/* 316 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCHLAUTHQuery", "setChannelName(String)", "setter", channelName);
/*     */     }
/*     */     
/* 319 */     this.channelName = safeTrim(channelName);
/*     */   }
/*     */   
/*     */   private String safeTrim(String s) {
/* 323 */     if (Trace.isOn) {
/* 324 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiCHLAUTHQuery", "safeTrim(String)", new Object[] { s });
/*     */     }
/*     */     
/* 327 */     String traceRet1 = (s == null) ? null : s.trim();
/* 328 */     if (Trace.isOn) {
/* 329 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiCHLAUTHQuery", "safeTrim(String)", traceRet1);
/*     */     }
/* 331 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getIPAddress() {
/* 338 */     if (Trace.isOn) {
/* 339 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCHLAUTHQuery", "getIPAddress()", "getter", this.iPAddress);
/*     */     }
/*     */     
/* 342 */     return this.iPAddress;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIPAddress(String iPAddress) {
/* 349 */     if (Trace.isOn) {
/* 350 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCHLAUTHQuery", "setIPAddress(String)", "setter", iPAddress);
/*     */     }
/*     */     
/* 353 */     this.iPAddress = safeTrim(iPAddress);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getClientUserId() {
/* 360 */     if (Trace.isOn) {
/* 361 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCHLAUTHQuery", "getClientUserId()", "getter", this.clientUserId);
/*     */     }
/*     */     
/* 364 */     return this.clientUserId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setClientUserId(String clientUserId) {
/* 371 */     if (Trace.isOn) {
/* 372 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCHLAUTHQuery", "setClientUserId(String)", "setter", clientUserId);
/*     */     }
/*     */     
/* 375 */     this.clientUserId = safeTrim(clientUserId);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 384 */     if (Trace.isOn) {
/* 385 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiCHLAUTHQuery", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 387 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 389 */     int pos = offset;
/*     */ 
/*     */     
/* 392 */     this.dc.writeMQField("QCHA", buffer, pos, 4, cp, tls);
/* 393 */     pos += 4;
/*     */ 
/*     */     
/* 396 */     this.dc.writeI32(this.version, buffer, pos, swap);
/* 397 */     pos += 4;
/*     */ 
/*     */     
/* 400 */     this.dc.writeI32(this.functionType, buffer, pos, swap);
/* 401 */     pos += 4;
/*     */ 
/*     */     
/* 404 */     this.dc.writeI8(this.allow, buffer, pos, swap);
/* 405 */     pos++;
/*     */ 
/*     */     
/* 408 */     this.dc.writeI8((byte)0, buffer, pos, swap);
/* 409 */     pos++;
/*     */ 
/*     */     
/* 412 */     this.dc.writeI8((byte)0, buffer, pos, swap);
/* 413 */     pos++;
/*     */ 
/*     */     
/* 416 */     this.dc.writeI8((byte)0, buffer, pos, swap);
/* 417 */     pos++;
/*     */ 
/*     */     
/* 420 */     this.dc.writeI16((short)0, buffer, pos, swap);
/* 421 */     pos += 2;
/*     */ 
/*     */     
/* 424 */     this.dc.writeI16((short)0, buffer, pos, swap);
/* 425 */     pos += 2;
/*     */ 
/*     */     
/* 428 */     this.dc.writeI16((short)0, buffer, pos, swap);
/* 429 */     pos += 2;
/*     */ 
/*     */     
/* 432 */     this.dc.writeI16((short)0, buffer, pos, swap);
/* 433 */     pos += 2;
/*     */ 
/*     */     
/* 436 */     this.dc.writeI32(0, buffer, pos, swap);
/* 437 */     pos += 4;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 442 */     this.dc.writeI32(0, buffer, pos, swap);
/* 443 */     pos += 4;
/*     */ 
/*     */     
/* 446 */     this.dc.writeI32(0, buffer, pos, swap);
/* 447 */     pos += 4;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 452 */     this.dc.writeI32(0, buffer, pos, swap);
/* 453 */     pos += 4;
/*     */ 
/*     */     
/* 456 */     this.dc.writeNullTerminatedField(this.channelName, buffer, pos, cp, tls);
/* 457 */     pos += 21;
/*     */ 
/*     */     
/* 460 */     this.dc.writeNullTerminatedField(this.iPAddress, buffer, pos, cp, tls);
/* 461 */     pos += 49;
/*     */ 
/*     */     
/* 464 */     this.dc.writeNullTerminatedField(this.clientUserId, buffer, pos, cp, tls);
/* 465 */     pos += SIZEOF_ClientUserId;
/*     */ 
/*     */     
/* 468 */     this.dc.writeNullTerminatedField("", buffer, pos, cp, tls);
/* 469 */     pos += 49;
/*     */ 
/*     */     
/* 472 */     this.dc.writeNullTerminatedField("", buffer, pos, cp, tls);
/* 473 */     pos += 49;
/*     */ 
/*     */     
/* 476 */     this.dc.writeNullTerminatedField(this.certDN, buffer, pos, cp, tls);
/* 477 */     pos += 1025;
/*     */ 
/*     */     
/* 480 */     this.dc.writeNullTerminatedField(this.certIssuerDN, buffer, pos, cp, tls);
/* 481 */     pos += 1025;
/*     */ 
/*     */ 
/*     */     
/* 485 */     if (Trace.isOn) {
/* 486 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiCHLAUTHQuery", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 487 */           Integer.valueOf(pos));
/*     */     }
/* 489 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 498 */     if (Trace.isOn) {
/* 499 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiCHLAUTHQuery", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 501 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 503 */     int pos = offset;
/*     */ 
/*     */     
/* 506 */     String strucId1 = this.dc.readMQField(buffer, pos, 4, cp, tls);
/* 507 */     if (!strucId1.equals("QCHA")) {
/* 508 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 6107, null);
/* 509 */       if (Trace.isOn) {
/* 510 */         Trace.throwing(this, "com.ibm.mq.jmqi.system.LpiCHLAUTHQuery", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", (Throwable)traceRet1);
/*     */       }
/*     */       
/* 513 */       throw traceRet1;
/*     */     } 
/* 515 */     pos += 4;
/*     */ 
/*     */     
/* 518 */     this.version = this.dc.readI32(buffer, pos, swap);
/* 519 */     pos += 4;
/*     */ 
/*     */     
/* 522 */     this.functionType = this.dc.readI32(buffer, pos, swap);
/* 523 */     pos += 4;
/*     */ 
/*     */     
/* 526 */     this.allow = this.dc.readI8(buffer, pos, swap);
/* 527 */     pos++;
/*     */ 
/*     */     
/* 530 */     pos++;
/*     */ 
/*     */     
/* 533 */     pos++;
/*     */ 
/*     */     
/* 536 */     this.checkClient = this.dc.readI8(buffer, pos, swap);
/* 537 */     pos++;
/*     */ 
/*     */     
/* 540 */     pos += 2;
/*     */ 
/*     */     
/* 543 */     pos += 2;
/*     */ 
/*     */     
/* 546 */     pos += 2;
/*     */ 
/*     */     
/* 549 */     pos += 2;
/*     */ 
/*     */     
/* 552 */     pos += 4;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 557 */     pos += 4;
/*     */ 
/*     */     
/* 560 */     pos += 4;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 565 */     pos += 4;
/*     */ 
/*     */     
/* 568 */     setChannelName(this.dc.readNullTerminatedField(buffer, pos, 21, cp, tls));
/* 569 */     pos += 21;
/*     */ 
/*     */     
/* 572 */     setIPAddress(this.dc.readNullTerminatedField(buffer, pos, 49, cp, tls));
/* 573 */     pos += 49;
/*     */ 
/*     */     
/* 576 */     setClientUserId(this.dc.readNullTerminatedField(buffer, pos, SIZEOF_ClientUserId, cp, tls));
/* 577 */     pos += SIZEOF_ClientUserId;
/*     */ 
/*     */     
/* 580 */     pos += 49;
/*     */ 
/*     */     
/* 583 */     pos += 49;
/*     */ 
/*     */     
/* 586 */     setCertDN(this.dc.readNullTerminatedField(buffer, pos, 1025, cp, tls));
/* 587 */     pos += 1025;
/*     */ 
/*     */     
/* 590 */     setCertIssuerDN(this.dc.readNullTerminatedField(buffer, pos, 1025, cp, tls));
/* 591 */     pos += 1025;
/*     */ 
/*     */ 
/*     */     
/* 595 */     if (Trace.isOn) {
/* 596 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiCHLAUTHQuery", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 597 */           Integer.valueOf(pos));
/*     */     }
/* 599 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 608 */     if (Trace.isOn) {
/* 609 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiCHLAUTHQuery", "hashCode()");
/*     */     }
/* 611 */     int hashCode = 0;
/* 612 */     hashCode += safeStringHashCode(this.strucId);
/* 613 */     hashCode += 29 * this.version;
/* 614 */     hashCode += 31 * this.functionType;
/* 615 */     hashCode += 37 * this.allow;
/* 616 */     hashCode += 43 * this.checkClient;
/* 617 */     hashCode += 107 * safeStringHashCode(this.channelName);
/* 618 */     hashCode += 109 * safeStringHashCode(this.iPAddress);
/* 619 */     hashCode += 113 * safeStringHashCode(this.clientUserId);
/* 620 */     hashCode += 127 * safeStringHashCode(this.certDN);
/* 621 */     hashCode += 131 * safeStringHashCode(this.certIssuerDN);
/*     */     
/* 623 */     if (Trace.isOn) {
/* 624 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiCHLAUTHQuery", "hashCode()", 
/* 625 */           Integer.valueOf(hashCode));
/*     */     }
/* 627 */     return hashCode;
/*     */   }
/*     */   
/*     */   private int safeStringHashCode(String s) {
/* 631 */     if (Trace.isOn) {
/* 632 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiCHLAUTHQuery", "safeStringHashCode(String)", new Object[] { s });
/*     */     }
/*     */     
/* 635 */     int traceRet1 = (s == null) ? 0 : s.hashCode();
/* 636 */     if (Trace.isOn) {
/* 637 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiCHLAUTHQuery", "safeStringHashCode(String)", 
/* 638 */           Integer.valueOf(traceRet1));
/*     */     }
/* 640 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 648 */     if (Trace.isOn) {
/* 649 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiCHLAUTHQuery", "equals(Object)", new Object[] { o });
/*     */     }
/*     */     
/* 652 */     if (!(o instanceof LpiCHLAUTHQuery)) {
/* 653 */       if (Trace.isOn) {
/* 654 */         Trace.exit(this, "com.ibm.mq.jmqi.system.LpiCHLAUTHQuery", "equals(Object)", 
/* 655 */             Boolean.valueOf(false), 1);
/*     */       }
/* 657 */       return false;
/*     */     } 
/*     */     
/* 660 */     LpiCHLAUTHQuery other = (LpiCHLAUTHQuery)o;
/*     */     
/* 662 */     boolean retVal = true;
/* 663 */     retVal = (retVal && safeStringEquals(this.strucId, other.strucId));
/* 664 */     retVal = (retVal && this.version == other.version);
/* 665 */     retVal = (retVal && this.functionType == other.functionType);
/* 666 */     retVal = (retVal && this.allow == other.allow);
/* 667 */     retVal = (retVal && this.checkClient == other.checkClient);
/* 668 */     retVal = (retVal && safeStringEquals(this.channelName, other.channelName));
/* 669 */     retVal = (retVal && safeStringEquals(this.iPAddress, other.iPAddress));
/* 670 */     retVal = (retVal && safeStringEquals(this.clientUserId, other.clientUserId));
/* 671 */     retVal = (retVal && safeStringEquals(this.certDN, other.certDN));
/* 672 */     retVal = (retVal && safeStringEquals(this.certIssuerDN, other.certIssuerDN));
/*     */     
/* 674 */     if (Trace.isOn) {
/* 675 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiCHLAUTHQuery", "equals(Object)", 
/* 676 */           Boolean.valueOf(retVal), 2);
/*     */     }
/* 678 */     return retVal;
/*     */   }
/*     */   
/*     */   private boolean safeStringEquals(String s1, String s2) {
/* 682 */     if (Trace.isOn) {
/* 683 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiCHLAUTHQuery", "safeStringEquals(String,String)", new Object[] { s1, s2 });
/*     */     }
/*     */     
/* 686 */     if (s1 == null && s2 == null) {
/* 687 */       if (Trace.isOn) {
/* 688 */         Trace.exit(this, "com.ibm.mq.jmqi.system.LpiCHLAUTHQuery", "safeStringEquals(String,String)", 
/* 689 */             Boolean.valueOf(true), 1);
/*     */       }
/* 691 */       return true;
/*     */     } 
/*     */     
/* 694 */     if (s1 == null || s2 == null) {
/* 695 */       if (Trace.isOn) {
/* 696 */         Trace.exit(this, "com.ibm.mq.jmqi.system.LpiCHLAUTHQuery", "safeStringEquals(String,String)", 
/* 697 */             Boolean.valueOf(false), 2);
/*     */       }
/* 699 */       return false;
/*     */     } 
/*     */     
/* 702 */     boolean traceRet1 = s1.equals(s2);
/* 703 */     if (Trace.isOn) {
/* 704 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiCHLAUTHQuery", "safeStringEquals(String,String)", 
/* 705 */           Boolean.valueOf(traceRet1), 3);
/*     */     }
/* 707 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() {
/* 716 */     if (Trace.isOn) {
/* 717 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiCHLAUTHQuery", "clone()");
/*     */     }
/* 719 */     LpiCHLAUTHQuery theClone = new LpiCHLAUTHQuery(this.env, this.version);
/*     */     
/* 721 */     theClone.setFunctionType(this.functionType);
/* 722 */     theClone.setChannelName(this.channelName);
/* 723 */     theClone.setIPAddress(this.iPAddress);
/* 724 */     theClone.setClientUserId(this.clientUserId);
/* 725 */     theClone.setCertDN(this.certDN);
/* 726 */     theClone.setCertIssuerDN(this.certIssuerDN);
/*     */     
/* 728 */     if (Trace.isOn) {
/* 729 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiCHLAUTHQuery", "clone()", theClone);
/*     */     }
/* 731 */     return theClone;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCertDN() {
/* 738 */     if (Trace.isOn) {
/* 739 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCHLAUTHQuery", "getCertDN()", "getter", this.certDN);
/*     */     }
/* 741 */     return this.certDN;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCertDN(String certDN) {
/* 748 */     if (Trace.isOn) {
/* 749 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCHLAUTHQuery", "setCertDN(String)", "setter", certDN);
/*     */     }
/*     */     
/* 752 */     this.certDN = certDN;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCertIssuerDN() {
/* 759 */     if (Trace.isOn) {
/* 760 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCHLAUTHQuery", "getCertIssuerDN()", "getter", this.certIssuerDN);
/*     */     }
/*     */     
/* 763 */     return this.certIssuerDN;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCertIssuerDN(String certIssuerDN) {
/* 770 */     if (Trace.isOn) {
/* 771 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCHLAUTHQuery", "setCertIssuerDN(String)", "setter", certIssuerDN);
/*     */     }
/*     */     
/* 774 */     this.certIssuerDN = certIssuerDN;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\LpiCHLAUTHQuery.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */