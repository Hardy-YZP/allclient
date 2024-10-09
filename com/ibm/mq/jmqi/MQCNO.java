/*      */ package com.ibm.mq.jmqi;
/*      */ 
/*      */ import com.ibm.mq.constants.MQConstants;
/*      */ import com.ibm.mq.exits.MQCD;
/*      */ import com.ibm.mq.exits.MQCSP;
/*      */ import com.ibm.mq.jmqi.internal.AbstractMqiStructure;
/*      */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*      */ import com.ibm.mq.jmqi.internal.JmqiStructureFormatter;
/*      */ import com.ibm.mq.jmqi.internal.JmqiTools;
/*      */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*      */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*      */ import com.ibm.mq.jmqi.system.JmqiTls;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.util.Arrays;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class MQCNO
/*      */   extends AbstractMqiStructure
/*      */   implements Cloneable
/*      */ {
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQCNO.java";
/*      */   private static final int SIZEOF_STRUCID = 4;
/*      */   private static final int SIZEOF_VERSION = 4;
/*      */   private static final int SIZEOF_OPTIONS = 4;
/*      */   private static final int SIZEOF_CLIENT_CONN_OFFSET = 4;
/*      */   private static final int SIZEOF_CONN_TAG = 128;
/*      */   private static final int SIZEOF_SSL_CONFIG_OFFSET = 4;
/*      */   private static final int SIZEOF_CONNECTION_ID = 24;
/*      */   private static final int SIZEOF_SECURITY_PARMS_OFFSET = 4;
/*      */   private static final int SIZEOF_CCDT_URL_OFFSET = 4;
/*      */   private static final int SIZEOF_CCDT_URL_LENGTH = 4;
/*      */   private static final int SIZEOF_CCDT_RESERVED = 8;
/*      */   private static final int SIZEOF_APPL_NAME = 28;
/*      */   private static final int SIZEOF_RESERVED2 = 4;
/*      */   private static final int SIZEOF_BALANCE_PARMS_OFFSET = 4;
/*      */   private static final int SIZEOF_RESERVED3 = 4;
/*      */   
/*      */   static {
/*   46 */     if (Trace.isOn) {
/*   47 */       Trace.data("com.ibm.mq.jmqi.MQCNO", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQCNO.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getSizeV1(int sizeofNativePointer) {
/*  121 */     if (Trace.isOn)
/*  122 */       Trace.entry("com.ibm.mq.jmqi.MQCNO", "getSizeV1(int)", new Object[] {
/*  123 */             Integer.valueOf(sizeofNativePointer)
/*      */           }); 
/*  125 */     int size = 12;
/*  126 */     if (Trace.isOn) {
/*  127 */       Trace.exit("com.ibm.mq.jmqi.MQCNO", "getSizeV1(int)", Integer.valueOf(size));
/*      */     }
/*  129 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getSizeV2(int sizeofNativePointer) {
/*  139 */     int size = getSizeV1(sizeofNativePointer) + 4 + sizeofNativePointer;
/*  140 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getSizeV3(int sizeofNativePointer) {
/*  149 */     int size = getSizeV2(sizeofNativePointer) + 128;
/*  150 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getSizeV4(int sizeofNativePointer) {
/*  159 */     int size = getSizeV3(sizeofNativePointer) + sizeofNativePointer + 4;
/*  160 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getSizeV5(int sizeofNativePointer) {
/*  169 */     int size = getSizeV4(sizeofNativePointer) + 24 + 4 + sizeofNativePointer;
/*  170 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getSizeV6(int sizeofNativePointer) {
/*  180 */     int size = getSizeV5(sizeofNativePointer) + sizeofNativePointer + 4 + 4 + 8;
/*  181 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getSizeV7(int sizeofNativePointer) {
/*  191 */     int size = getSizeV6(sizeofNativePointer) + 28 + 4;
/*  192 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getSizeV8(int sizeofNativePointer) {
/*  202 */     int size = getSizeV7(sizeofNativePointer) + sizeofNativePointer + 4 + 4;
/*  203 */     size += JmqiTools.alignToGrain(sizeofNativePointer, size);
/*      */     
/*  205 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getSize(JmqiEnvironment env, int version, int sizeofNativePointer) throws JmqiException {
/*  218 */     int size = 0;
/*  219 */     switch (version) {
/*      */       case 1:
/*  221 */         size = getSizeV1(sizeofNativePointer);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  251 */         return size;case 2: size = getSizeV2(sizeofNativePointer); return size;case 3: size = getSizeV3(sizeofNativePointer); return size;case 4: size = getSizeV4(sizeofNativePointer); return size;case 5: size = getSizeV5(sizeofNativePointer); return size;case 6: size = getSizeV6(sizeofNativePointer); return size;case 7: size = getSizeV7(sizeofNativePointer); return size;case 8: size = getSizeV8(sizeofNativePointer); return size;
/*      */     } 
/*      */     JmqiException e = new JmqiException(env, -1, null, 2, 2139, null);
/*      */     throw e;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getRequiredBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/*  260 */     int size = getSize(this.env, this.version, ptrSize);
/*  261 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*      */     
/*  263 */     if (this.clientConn != null) {
/*  264 */       size += this.clientConn.getRequiredBufferSize(ptrSize, cp);
/*      */     }
/*      */     
/*  267 */     if (this.sslConfig != null) {
/*  268 */       size += this.sslConfig.getRequiredBufferSize(ptrSize, cp);
/*      */     }
/*      */     
/*  271 */     if (this.securityParms != null) {
/*  272 */       size += this.securityParms.getRequiredBufferSize(ptrSize, cp);
/*      */     }
/*      */     
/*  275 */     if (this.ccdtUrl != null) {
/*  276 */       this.ccdtUrl_cachedBytes = dc.getStrBytes(this.ccdtUrl, cp);
/*  277 */       size += this.ccdtUrl_cachedBytes.length;
/*      */     } 
/*      */     
/*  280 */     if (this.mqbno != null) {
/*  281 */       size += this.mqbno.getRequiredBufferSize(ptrSize, cp);
/*      */     }
/*      */     
/*  284 */     return size;
/*      */   }
/*      */   
/*  287 */   private int version = 1;
/*  288 */   private int options = 0;
/*  289 */   private MQCD clientConn = null;
/*  290 */   private byte[] connTag = new byte[128];
/*  291 */   private MQSCO sslConfig = null;
/*  292 */   private byte[] connectionId = new byte[24];
/*  293 */   private MQCSP securityParms = null;
/*  294 */   private String ccdtUrl = null;
/*  295 */   private int ccdtUrlLength = 0;
/*      */ 
/*      */   
/*      */   private byte[] ccdtUrl_cachedBytes;
/*      */   
/*  300 */   private String applName = null;
/*      */ 
/*      */ 
/*      */   
/*      */   private MQBNO mqbno;
/*      */ 
/*      */ 
/*      */   
/*      */   protected MQCNO(JmqiEnvironment env) {
/*  309 */     super(env);
/*  310 */     if (Trace.isOn) {
/*  311 */       Trace.entry(this, "com.ibm.mq.jmqi.MQCNO", "<init>(JmqiEnvironment)", new Object[] { env });
/*      */     }
/*  313 */     Arrays.fill(this.connTag, (byte)0);
/*  314 */     Arrays.fill(this.connectionId, (byte)0);
/*      */     
/*  316 */     if (Trace.isOn) {
/*  317 */       Trace.exit(this, "com.ibm.mq.jmqi.MQCNO", "<init>(JmqiEnvironment)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQCD getClientConn() {
/*  327 */     if (Trace.isOn) {
/*  328 */       Trace.data(this, "com.ibm.mq.jmqi.MQCNO", "getClientConn()", "getter", this.clientConn);
/*      */     }
/*  330 */     return this.clientConn;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setClientConn(MQCD clientConn) {
/*  340 */     if (Trace.isOn) {
/*  341 */       Trace.data(this, "com.ibm.mq.jmqi.MQCNO", "setClientConn(MQCD)", "setter", clientConn);
/*      */     }
/*  343 */     this.clientConn = clientConn;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getConnectionId() {
/*  353 */     if (Trace.isOn) {
/*  354 */       Trace.data(this, "getConnectionId()", this.connectionId);
/*      */     }
/*  356 */     return this.connectionId;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setConnectionId(byte[] connectionId) {
/*  370 */     if (Trace.isOn) {
/*  371 */       Trace.data(this, "setConnectionId(byte [ ])", connectionId);
/*      */     }
/*  373 */     JmqiTools.byteArrayCopy(connectionId, 0, this.connectionId, 0, 24);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getOptions() {
/*  380 */     if (Trace.isOn) {
/*  381 */       Trace.data(this, "com.ibm.mq.jmqi.MQCNO", "getOptions()", "getter", Integer.valueOf(this.options));
/*      */     }
/*  383 */     return this.options;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setOptions(int options) {
/*  393 */     if (Trace.isOn) {
/*  394 */       Trace.data(this, "com.ibm.mq.jmqi.MQCNO", "setOptions(int)", "setter", 
/*  395 */           Integer.valueOf(options));
/*      */     }
/*  397 */     this.options = options;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQCSP getSecurityParms() {
/*  405 */     if (Trace.isOn) {
/*  406 */       Trace.data(this, "com.ibm.mq.jmqi.MQCNO", "getSecurityParms()", "getter", this.securityParms);
/*      */     }
/*  408 */     return this.securityParms;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSecurityParms(MQCSP securityParms) {
/*  418 */     if (Trace.isOn) {
/*  419 */       Trace.data(this, "com.ibm.mq.jmqi.MQCNO", "setSecurityParms(MQCSP)", "setter", securityParms);
/*      */     }
/*  421 */     this.securityParms = securityParms;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQSCO getSslConfig() {
/*  429 */     if (Trace.isOn) {
/*  430 */       Trace.data(this, "com.ibm.mq.jmqi.MQCNO", "getSslConfig()", "getter", this.sslConfig);
/*      */     }
/*  432 */     return this.sslConfig;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSslConfig(MQSCO sslConfig) {
/*  442 */     if (Trace.isOn) {
/*  443 */       Trace.data(this, "com.ibm.mq.jmqi.MQCNO", "setSslConfig(MQSCO)", "setter", sslConfig);
/*      */     }
/*  445 */     this.sslConfig = sslConfig;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getVersion() {
/*  454 */     if (Trace.isOn) {
/*  455 */       Trace.data(this, "com.ibm.mq.jmqi.MQCNO", "getVersion()", "getter", Integer.valueOf(this.version));
/*      */     }
/*  457 */     return this.version;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setVersion(int version) {
/*  468 */     if (Trace.isOn) {
/*  469 */       Trace.data(this, "com.ibm.mq.jmqi.MQCNO", "setVersion(int)", "setter", 
/*  470 */           Integer.valueOf(version));
/*      */     }
/*  472 */     this.version = version;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getConnTag() {
/*  482 */     if (Trace.isOn) {
/*  483 */       Trace.data(this, "getConnTag()", this.connTag);
/*      */     }
/*  485 */     return this.connTag;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setConnTag(byte[] connTag) {
/*  498 */     if (Trace.isOn) {
/*  499 */       Trace.data(this, "setConnTag(byte [ ])", connTag);
/*      */     }
/*  501 */     JmqiTools.byteArrayCopy(connTag, 0, this.connTag, 0, 128);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getCCDTUrl() {
/*  508 */     if (Trace.isOn) {
/*  509 */       Trace.data(this, "getCCDTUrl()", this.ccdtUrl);
/*      */     }
/*  511 */     return this.ccdtUrl;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCCDTUrl(String ccdtUrl) {
/*  518 */     if (Trace.isOn) {
/*  519 */       Trace.data(this, "setCCDTUrl(String)", ccdtUrl);
/*      */     }
/*  521 */     this.ccdtUrl = ccdtUrl;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getApplName() {
/*  528 */     if (Trace.isOn) {
/*  529 */       Trace.data(this, "getApplName()", this.applName);
/*      */     }
/*  531 */     return this.applName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setApplName(String applName) {
/*  538 */     if (Trace.isOn) {
/*  539 */       Trace.data(this, "setApplName(String)", applName);
/*      */     }
/*  541 */     this.applName = applName;
/*  542 */     if (this.version < 7) {
/*  543 */       setVersion(7);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQBNO getMqbno() {
/*  551 */     if (Trace.isOn) {
/*  552 */       Trace.data(this, "getMqbno()", "getter", this.mqbno);
/*      */     }
/*  554 */     return this.mqbno;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMqbno(MQBNO mqbno) {
/*  561 */     if (Trace.isOn) {
/*  562 */       Trace.data(this, "setMqbno()", "setter", mqbno);
/*      */     }
/*      */     
/*  565 */     if (mqbno != null) {
/*  566 */       this.mqbno = mqbno;
/*  567 */       if (this.version < 8) {
/*  568 */         setVersion(8);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int writeToTraceBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/*  581 */     return writeToBuffer(buffer, offset, true, ptrSize, swap, cp, tls);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/*  591 */     return writeToBuffer(buffer, offset, false, ptrSize, swap, cp, tls);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int writeToBuffer(byte[] buffer, int offset, boolean obscure, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/*  609 */     if (Trace.isOn) {
/*  610 */       Trace.entry(this, "com.ibm.mq.jmqi.MQCNO", "writeToBuffer(byte [ ],int,boolean,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*      */             
/*  612 */             Integer.valueOf(offset), Boolean.valueOf(obscure), Integer.valueOf(ptrSize), 
/*  613 */             Boolean.valueOf(swap), cp, tls });
/*      */     }
/*  615 */     int pos = offset;
/*  616 */     int startPos = offset;
/*  617 */     int clientConnOffsetPos = -1;
/*  618 */     int sslConfigOffsetPos = -1;
/*  619 */     int securityParmsOffsetPos = -1;
/*  620 */     int ccdtUrlOffsetPos = -1;
/*  621 */     int ccdtUrlLengthOffsetPos = -1;
/*  622 */     int balanceParmsOffsetPos = -1;
/*  623 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*      */     
/*  625 */     dc.writeMQField("CNO ", buffer, pos, 4, cp, tls);
/*  626 */     pos += 4;
/*      */     
/*  628 */     dc.writeI32(this.version, buffer, pos, swap);
/*  629 */     pos += 4;
/*      */     
/*  631 */     dc.writeI32(this.options, buffer, pos, swap);
/*  632 */     pos += 4;
/*  633 */     if (this.version >= 2) {
/*      */       
/*  635 */       clientConnOffsetPos = pos;
/*  636 */       pos += 4;
/*      */       
/*  638 */       dc.clear(buffer, pos, ptrSize);
/*  639 */       pos += ptrSize;
/*      */     } 
/*  641 */     if (this.version >= 3) {
/*      */       
/*  643 */       System.arraycopy(this.connTag, 0, buffer, pos, 128);
/*  644 */       pos += 128;
/*      */     } 
/*  646 */     if (this.version >= 4) {
/*      */       
/*  648 */       dc.clear(buffer, pos, ptrSize);
/*  649 */       pos += ptrSize;
/*      */       
/*  651 */       sslConfigOffsetPos = pos;
/*  652 */       pos += 4;
/*      */     } 
/*  654 */     if (this.version >= 5) {
/*      */       
/*  656 */       pos += 24;
/*      */       
/*  658 */       securityParmsOffsetPos = pos;
/*  659 */       pos += 4;
/*      */       
/*  661 */       dc.clear(buffer, pos, ptrSize);
/*  662 */       pos += ptrSize;
/*      */     } 
/*  664 */     if (this.version >= 6) {
/*      */       
/*  666 */       dc.clear(buffer, pos, ptrSize);
/*  667 */       pos += ptrSize;
/*      */       
/*  669 */       ccdtUrlOffsetPos = pos;
/*  670 */       pos += 4;
/*      */       
/*  672 */       ccdtUrlLengthOffsetPos = pos;
/*  673 */       pos += 4;
/*      */       
/*  675 */       dc.clear(buffer, pos, 8);
/*  676 */       pos += 8;
/*      */     } 
/*  678 */     if (this.version >= 7) {
/*      */       
/*  680 */       dc.writeField(this.applName, buffer, pos, 28, cp, tls);
/*  681 */       pos += 28;
/*      */       
/*  683 */       dc.clear(buffer, pos, 4);
/*  684 */       pos += 4;
/*      */     } 
/*  686 */     if (this.version >= 8) {
/*  687 */       balanceParmsOffsetPos = pos;
/*  688 */       dc.clear(buffer, pos, 4);
/*  689 */       pos += 4;
/*  690 */       dc.clear(buffer, pos, ptrSize);
/*  691 */       pos += ptrSize;
/*      */     } 
/*      */     
/*  694 */     if (this.version == 8) {
/*  695 */       int padding = JmqiTools.alignToGrain(ptrSize, pos);
/*  696 */       dc.clear(buffer, pos, padding);
/*  697 */       pos += padding;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  703 */     if (clientConnOffsetPos > 0) {
/*  704 */       if (this.clientConn == null) {
/*  705 */         dc.clear(buffer, clientConnOffsetPos, 4);
/*      */       } else {
/*      */         
/*  708 */         dc.writeI32(pos - startPos, buffer, clientConnOffsetPos, swap);
/*  709 */         pos = this.clientConn.writeToBuffer(buffer, pos, ptrSize, swap, cp, tls);
/*      */       } 
/*      */     }
/*      */     
/*  713 */     if (sslConfigOffsetPos > 0) {
/*  714 */       if (this.sslConfig == null) {
/*  715 */         dc.clear(buffer, sslConfigOffsetPos, 4);
/*      */       } else {
/*      */         
/*  718 */         dc.writeI32(pos - startPos, buffer, sslConfigOffsetPos, swap);
/*  719 */         pos = this.sslConfig.writeToBuffer(buffer, pos, ptrSize, swap, cp, tls);
/*      */       } 
/*      */     }
/*      */     
/*  723 */     if (securityParmsOffsetPos > 0) {
/*  724 */       if (this.securityParms == null) {
/*  725 */         dc.clear(buffer, securityParmsOffsetPos, 4);
/*      */       } else {
/*      */         
/*  728 */         dc.writeI32(pos - startPos, buffer, securityParmsOffsetPos, swap);
/*  729 */         pos = this.securityParms.writeToBuffer(buffer, pos, obscure, ptrSize, swap, cp, tls);
/*      */       } 
/*      */     }
/*      */     
/*  733 */     if (ccdtUrlOffsetPos > 0) {
/*      */       byte[] ccdtUrlBytes;
/*      */       
/*  736 */       if (this.ccdtUrl_cachedBytes != null) {
/*  737 */         ccdtUrlBytes = this.ccdtUrl_cachedBytes;
/*  738 */         this.ccdtUrl_cachedBytes = null;
/*      */       } else {
/*      */         
/*  741 */         ccdtUrlBytes = dc.getStrBytes(this.ccdtUrl, cp);
/*      */       } 
/*      */       
/*  744 */       if (ccdtUrlBytes.length > 0) {
/*  745 */         dc.writeI32(pos - offset, buffer, ccdtUrlOffsetPos, swap);
/*      */       } else {
/*      */         
/*  748 */         dc.clear(buffer, ccdtUrlOffsetPos, 4);
/*      */       } 
/*  750 */       dc.writeI32(ccdtUrlBytes.length, buffer, ccdtUrlLengthOffsetPos, swap);
/*      */       
/*  752 */       System.arraycopy(ccdtUrlBytes, 0, buffer, pos, ccdtUrlBytes.length);
/*  753 */       pos += ccdtUrlBytes.length;
/*      */     } 
/*      */ 
/*      */     
/*  757 */     if (balanceParmsOffsetPos > 0 && this.mqbno != null) {
/*  758 */       dc.writeI32(pos - startPos, buffer, balanceParmsOffsetPos, swap);
/*  759 */       pos = this.mqbno.writeToBuffer(buffer, pos, ptrSize, swap, cp, tls);
/*      */     } 
/*      */     
/*  762 */     if (Trace.isOn) {
/*  763 */       Trace.exit(this, "com.ibm.mq.jmqi.MQCNO", "writeToBuffer(byte [ ],int,boolean,int,boolean,JmqiCodepage,JmqiTls)", 
/*      */           
/*  765 */           Integer.valueOf(pos));
/*      */     }
/*  767 */     return pos;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/*  782 */     return readFromBuffer(buffer, offset, false, ptrSize, swap, cp, tls);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int readFromBuffer(byte[] buffer, int offset, boolean readAllFields, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/*  799 */     if (Trace.isOn) {
/*  800 */       Trace.entry(this, "com.ibm.mq.jmqi.MQCNO", "readFromBuffer(byte [ ],int,boolean,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*      */             
/*  802 */             Integer.valueOf(offset), Boolean.valueOf(readAllFields), Integer.valueOf(ptrSize), 
/*  803 */             Boolean.valueOf(swap), cp, tls });
/*      */     }
/*      */     
/*  806 */     int pos = offset;
/*  807 */     int startPos = offset;
/*  808 */     int clientConnOffset = 0;
/*  809 */     int sslConfigOffset = 0;
/*  810 */     int securityParmsOffset = 0;
/*  811 */     int ccdtUrlOffset = 0;
/*  812 */     int balanceParmsOffsetPos = 0;
/*      */     
/*  814 */     int ccdtUrlLength = 0;
/*  815 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*      */     
/*  817 */     String strucId = dc.readMQField(buffer, pos, 4, cp, tls);
/*  818 */     if (!strucId.equals("CNO ")) {
/*      */       
/*  820 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2139, null);
/*      */ 
/*      */       
/*  823 */       if (Trace.isOn) {
/*  824 */         Trace.throwing(this, "com.ibm.mq.jmqi.MQCNO", "readFromBuffer(byte [ ],int,boolean,int,boolean,JmqiCodepage,JmqiTls)", traceRet1);
/*      */       }
/*      */       
/*  827 */       throw traceRet1;
/*      */     } 
/*  829 */     pos += 4;
/*      */     
/*  831 */     if (readAllFields) {
/*      */       
/*  833 */       this.version = dc.readI32(buffer, pos, swap);
/*  834 */       pos += 4;
/*      */ 
/*      */       
/*  837 */       this.options = dc.readI32(buffer, pos, swap);
/*  838 */       pos += 4;
/*      */     }
/*      */     else {
/*      */       
/*  842 */       pos += 8;
/*      */     } 
/*      */     
/*  845 */     if (this.version >= 2) {
/*      */       
/*  847 */       clientConnOffset = dc.readI32(buffer, pos, swap);
/*  848 */       pos += 4;
/*      */ 
/*      */       
/*  851 */       pos += ptrSize;
/*      */     } 
/*      */     
/*  854 */     if (this.version >= 3) {
/*      */       
/*  856 */       System.arraycopy(buffer, pos, this.connTag, 0, 128);
/*  857 */       pos += 128;
/*      */     } 
/*      */     
/*  860 */     if (this.version >= 4) {
/*      */       
/*  862 */       pos += ptrSize;
/*      */ 
/*      */       
/*  865 */       sslConfigOffset = dc.readI32(buffer, pos, swap);
/*  866 */       pos += 4;
/*      */     } 
/*      */     
/*  869 */     if (this.version >= 5) {
/*      */       
/*  871 */       System.arraycopy(buffer, pos, this.connectionId, 0, 24);
/*  872 */       pos += 24;
/*      */ 
/*      */       
/*  875 */       securityParmsOffset = dc.readI32(buffer, pos, swap);
/*  876 */       pos += 4;
/*      */ 
/*      */       
/*  879 */       pos += ptrSize;
/*      */     } 
/*      */     
/*  882 */     if (this.version >= 6) {
/*      */       
/*  884 */       pos += ptrSize;
/*      */ 
/*      */       
/*  887 */       ccdtUrlOffset = dc.readI32(buffer, pos, swap);
/*  888 */       pos += 4;
/*      */ 
/*      */       
/*  891 */       ccdtUrlLength = dc.readI32(buffer, pos, swap);
/*  892 */       pos += 4;
/*      */ 
/*      */       
/*  895 */       pos += 8;
/*      */     } 
/*      */     
/*  898 */     if (this.version >= 7) {
/*      */       
/*  900 */       this.applName = dc.readField(buffer, pos, 28, cp, tls);
/*  901 */       pos += 28;
/*      */ 
/*      */       
/*  904 */       pos += 4;
/*      */     } 
/*      */     
/*  907 */     if (this.version >= 8) {
/*      */       
/*  909 */       balanceParmsOffsetPos = dc.readI32(buffer, pos, swap);
/*  910 */       pos += 4;
/*      */ 
/*      */       
/*  913 */       pos += ptrSize;
/*      */     } 
/*      */     
/*  916 */     if (this.version == 8) {
/*  917 */       int padding = JmqiTools.alignToGrain(ptrSize, pos);
/*  918 */       pos += padding;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  925 */     if (clientConnOffset > 0) {
/*  926 */       this.clientConn = this.env.newMQCD();
/*  927 */       this.clientConn.readFromBuffer(buffer, startPos + clientConnOffset, ptrSize, swap, cp, tls);
/*      */     }
/*      */     else {
/*      */       
/*  931 */       this.clientConn = null;
/*      */     } 
/*      */ 
/*      */     
/*  935 */     if (sslConfigOffset > 0) {
/*  936 */       this.sslConfig = this.env.newMQSCO();
/*  937 */       this.sslConfig.readFromBuffer(buffer, startPos + sslConfigOffset, ptrSize, swap, cp, tls);
/*      */     }
/*      */     else {
/*      */       
/*  941 */       this.sslConfig = null;
/*      */     } 
/*      */ 
/*      */     
/*  945 */     if (securityParmsOffset > 0) {
/*  946 */       this.securityParms = this.env.newMQCSP();
/*  947 */       this.securityParms.readFromBuffer(buffer, startPos + securityParmsOffset, ptrSize, swap, cp, tls);
/*      */     }
/*      */     else {
/*      */       
/*  951 */       this.securityParms = null;
/*      */     } 
/*      */ 
/*      */     
/*  955 */     if (ccdtUrlOffset > 0) {
/*  956 */       this.ccdtUrl = dc.readField(buffer, ccdtUrlOffset, ccdtUrlLength, cp, tls);
/*      */     } else {
/*      */       
/*  959 */       this.ccdtUrl = null;
/*      */     } 
/*      */ 
/*      */     
/*  963 */     if (balanceParmsOffsetPos > 0) {
/*  964 */       this.mqbno = this.env.newMQBNO();
/*  965 */       this.mqbno.readFromBuffer(buffer, balanceParmsOffsetPos, ptrSize, swap, cp, tls);
/*      */     } else {
/*      */       
/*  968 */       this.mqbno = null;
/*      */     } 
/*      */     
/*  971 */     if (Trace.isOn) {
/*  972 */       Trace.exit(this, "com.ibm.mq.jmqi.MQCNO", "readFromBuffer(byte [ ],int,boolean,int,boolean,JmqiCodepage,JmqiTls)", 
/*      */           
/*  974 */           Integer.valueOf(pos));
/*      */     }
/*  976 */     return pos;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object clone() throws CloneNotSupportedException {
/*  988 */     if (Trace.isOn) {
/*  989 */       Trace.entry(this, "com.ibm.mq.jmqi.MQCNO", "clone()");
/*      */     }
/*      */     
/*  992 */     Object newObject = super.clone();
/*  993 */     MQCNO newMQCNO = (MQCNO)newObject;
/*      */     
/*  995 */     newMQCNO.setVersion(this.version);
/*  996 */     newMQCNO.setOptions(this.options);
/*  997 */     if (this.clientConn != null) {
/*  998 */       newMQCNO.clientConn = (MQCD)this.clientConn.clone();
/*      */     }
/* 1000 */     newMQCNO.connTag = cloneByteArray(this.connTag);
/* 1001 */     if (this.sslConfig != null) {
/* 1002 */       newMQCNO.sslConfig = (MQSCO)this.sslConfig.clone();
/*      */     }
/* 1004 */     newMQCNO.connectionId = cloneByteArray(this.connectionId);
/* 1005 */     if (this.securityParms != null) {
/* 1006 */       newMQCNO.securityParms = (MQCSP)this.securityParms.clone();
/*      */     }
/* 1008 */     newMQCNO.setCCDTUrl(this.ccdtUrl);
/* 1009 */     newMQCNO.ccdtUrlLength = this.ccdtUrlLength;
/*      */     
/* 1011 */     if (Trace.isOn) {
/* 1012 */       Trace.exit(this, "com.ibm.mq.jmqi.MQCNO", "clone()", newObject);
/*      */     }
/* 1014 */     return newObject;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private byte[] cloneByteArray(byte[] toClone) {
/* 1020 */     if (Trace.isOn) {
/* 1021 */       Trace.entry(this, "com.ibm.mq.jmqi.MQCNO", "cloneByteArray(byte [ ])", new Object[] { toClone });
/*      */     }
/*      */     
/* 1024 */     if (toClone != null) {
/* 1025 */       byte[] tempByte = new byte[toClone.length];
/* 1026 */       System.arraycopy(toClone, 0, tempByte, 0, toClone.length);
/* 1027 */       if (Trace.isOn) {
/* 1028 */         Trace.exit(this, "com.ibm.mq.jmqi.MQCNO", "cloneByteArray(byte [ ])", tempByte, 1);
/*      */       }
/* 1030 */       return tempByte;
/*      */     } 
/* 1032 */     if (Trace.isOn) {
/* 1033 */       Trace.exit(this, "com.ibm.mq.jmqi.MQCNO", "cloneByteArray(byte [ ])", null, 2);
/*      */     }
/* 1035 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addFieldsToFormatter(JmqiStructureFormatter fmt) {
/* 1047 */     fmt.add("version", this.version);
/* 1048 */     fmt.add("options", this.options);
/* 1049 */     String optionDescription = MQConstants.decodeOptionsForTrace(this.options, "MQCNO_.*");
/* 1050 */     fmt.add("option flags", optionDescription);
/* 1051 */     fmt.add("clientConn", this.clientConn);
/* 1052 */     fmt.add("connTag", this.connTag);
/* 1053 */     fmt.add("sslConfig", this.sslConfig);
/* 1054 */     fmt.add("connectionId", this.connectionId);
/* 1055 */     fmt.add("securityParms", this.securityParms);
/* 1056 */     fmt.add("ccdtUrl", this.ccdtUrl);
/* 1057 */     fmt.add("ccdtUrlLength", this.ccdtUrlLength);
/* 1058 */     fmt.add("applName", this.applName);
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\MQCNO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */