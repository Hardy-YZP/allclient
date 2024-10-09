/*      */ package com.ibm.mq.ese.core;
/*      */ 
/*      */ import com.ibm.mq.ese.nls.AmsErrorMessages;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.DataInputStream;
/*      */ import java.io.DataOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.io.UnsupportedEncodingException;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class AMBIHeader
/*      */   implements Serializable
/*      */ {
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/core/AMBIHeader.java";
/*      */   private static final long serialVersionUID = 2513958014915913449L;
/*      */   @Deprecated
/*      */   private static final byte AMBI_JMSMESSAGE_TYPE_NONE_BYTE = 6;
/*      */   protected byte[] structid;
/*      */   protected byte versionMajor;
/*      */   protected byte versionMinor;
/*      */   protected byte headerEncoding;
/*      */   protected byte dynamicQueueInfo;
/*      */   protected int headerSize;
/*      */   protected int dataOffset;
/*      */   protected int protType;
/*      */   protected boolean reuseKey_set;
/*      */   protected int headerCCSID;
/*      */   protected int origSize;
/*      */   protected int reserved1;
/*      */   protected byte[] origFormat;
/*      */   protected byte[] flag;
/*      */   protected byte[] reserved;
/*      */   protected byte[] dynamicQueueName;
/*      */   
/*      */   static {
/*   52 */     if (Trace.isOn) {
/*   53 */       Trace.data("com.ibm.mq.ese.core.AMBIHeader", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/core/AMBIHeader.java");
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
/*  110 */   protected int keyBlockSize = 0;
/*  111 */   private short tagBlockSize = 0;
/*  112 */   protected short IVBlockSize = 0;
/*  113 */   protected int EncBlockSize = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final byte AMBI_HEADER_QOP_SEAL = 8;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final byte AMBI_HEADER_QOP_SIGN = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final byte AMBI_HEADER_QOP_ENC_ONLY = 64;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final byte AMBI_HEADER_NULL_MSG_FLAG = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int AMBI_HEADER_DYNAMIC_Q_LENGTH = 48;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final byte AMBI_HEADER_ENCODING_REVERSED = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final byte AMBI_HEADER_DEFAULT_ENCODING = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final byte AMBI_HEADER_VERSION_MINOR = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final byte AMBI_HEADER_VERSION_MAJOR_2 = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final byte AMBI_HEADER_VERSION_MAJOR_3 = 3;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final byte AMBI_HEADER_VERSION_MAJOR_MIN = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final byte AMBI_HEADER_VERSION_MAJOR_MAX = 3;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int AMBI_HEADER_DEFAULT_CCSID = 1208;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int AMBI_HEADER_STRUCTID_LENGTH = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  188 */   public static final byte[] AMBI_HEADER_STRUCTID = new byte[] { 80, 68, 77, 81 };
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int AMBI_HEADER_DEFAULT_FIELD_LENGTH = 8;
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int AMBI_HEADER_SIZE_V3 = 112;
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int AMBI_HEADER_SIZE_V2 = 104;
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int PROT_REUSE_KEY = 32;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AMBIHeader() {
/*  210 */     if (Trace.isOn) {
/*  211 */       Trace.entry(this, "com.ibm.mq.ese.core.AMBIHeader", "<init>()");
/*      */     }
/*      */     
/*  214 */     this.structid = Arrays.copyOf(AMBI_HEADER_STRUCTID, 1208);
/*      */     
/*  216 */     this.versionMajor = 2;
/*  217 */     this.versionMinor = 0;
/*      */     
/*  219 */     this.dynamicQueueInfo = 0;
/*  220 */     this.headerEncoding = 1;
/*  221 */     this.headerSize = 104;
/*  222 */     this.dataOffset = 104;
/*      */ 
/*      */     
/*  225 */     this.protType = 4;
/*  226 */     this.headerCCSID = 1208;
/*  227 */     this.origSize = 0;
/*  228 */     this.reserved1 = 0;
/*  229 */     this.origFormat = new byte[8];
/*  230 */     this.flag = new byte[8];
/*  231 */     this.reserved = new byte[8];
/*  232 */     this.dynamicQueueName = new byte[48];
/*      */     
/*  234 */     setKeyBlockSize(0);
/*  235 */     setTagBlockSize((short)0);
/*  236 */     setIVBlockSize((short)0);
/*  237 */     setEncBlockSize(0);
/*      */     
/*  239 */     if (Trace.isOn) {
/*  240 */       Trace.exit(this, "com.ibm.mq.ese.core.AMBIHeader", "<init>()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AMBIHeader(int qop) {
/*  250 */     this(); IllegalArgumentException traceRet1;
/*  251 */     if (Trace.isOn)
/*  252 */       Trace.entry(this, "com.ibm.mq.ese.core.AMBIHeader", "<init>(int)", new Object[] {
/*  253 */             Integer.valueOf(qop)
/*      */           }); 
/*  255 */     switch (qop) {
/*      */       case 2:
/*  257 */         setProtType(8);
/*      */         break;
/*      */       case 3:
/*  260 */         setProtType(64);
/*  261 */         this.versionMajor = 3;
/*  262 */         this.headerSize = 112;
/*  263 */         this.dataOffset = 112;
/*      */         break;
/*      */       case 1:
/*  266 */         setProtType(4);
/*      */         break;
/*      */       default:
/*  269 */         traceRet1 = new IllegalArgumentException("qop");
/*  270 */         if (Trace.isOn) {
/*  271 */           Trace.throwing(this, "com.ibm.mq.ese.core.AMBIHeader", "<init>(int)", traceRet1);
/*      */         }
/*  273 */         throw traceRet1;
/*      */     } 
/*  275 */     if (Trace.isOn) {
/*  276 */       Trace.exit(this, "com.ibm.mq.ese.core.AMBIHeader", "<init>(int)");
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
/*      */   public AMBIHeader(byte[] b) throws IncorrectHeaderException {
/*  291 */     this();
/*  292 */     if (Trace.isOn) {
/*  293 */       Trace.entry(this, "com.ibm.mq.ese.core.AMBIHeader", "<init>(byte [ ])", new Object[] { b });
/*      */     }
/*      */     
/*  296 */     ByteArrayInputStream bais = new ByteArrayInputStream(b);
/*  297 */     DataInputStream dis = new DataInputStream(bais);
/*      */     try {
/*  299 */       int read = dis.read(this.structid, 0, 4);
/*  300 */       if (read != 4) {
/*  301 */         IncorrectHeaderException ex = new IncorrectHeaderException(AmsErrorMessages.mju_ambi_header_invalid);
/*  302 */         if (Trace.isOn) {
/*  303 */           Trace.throwing(this, "com.ibm.mq.ese.core.AMBIHeader", "<init>(byte [ ])", ex, 2);
/*      */         }
/*  305 */         throw ex;
/*      */       } 
/*  307 */       this.versionMajor = dis.readByte();
/*  308 */       if (b.length != getSize()) {
/*      */         
/*  310 */         IncorrectHeaderException ex = new IncorrectHeaderException(AmsErrorMessages.mju_ambi_header_invalid);
/*  311 */         if (Trace.isOn) {
/*  312 */           Trace.throwing(this, "com.ibm.mq.ese.core.AMBIHeader", "<init>(byte [ ])", ex, 1);
/*      */         }
/*  314 */         throw ex;
/*      */       } 
/*  316 */       this.versionMinor = dis.readByte();
/*  317 */       this.headerEncoding = dis.readByte();
/*  318 */       this.dynamicQueueInfo = dis.readByte();
/*  319 */       this.headerSize = dis.readInt();
/*  320 */       this.dataOffset = dis.readInt();
/*  321 */       this.protType = dis.readInt();
/*  322 */       this.headerCCSID = dis.readInt();
/*  323 */       this.origSize = dis.readInt();
/*  324 */       if (this.versionMajor >= 3) {
/*  325 */         setKeyBlockSize(dis.readInt());
/*      */       } else {
/*      */         
/*  328 */         this.reserved1 = dis.readInt();
/*      */       } 
/*      */       
/*  331 */       read = dis.read(this.origFormat, 0, 8);
/*  332 */       if (read != 8) {
/*  333 */         IncorrectHeaderException ex = new IncorrectHeaderException(AmsErrorMessages.mju_ambi_header_invalid);
/*  334 */         if (Trace.isOn) {
/*  335 */           Trace.throwing(this, "com.ibm.mq.ese.core.AMBIHeader", "<init>(byte [ ])", ex, 3);
/*      */         }
/*  337 */         throw ex;
/*      */       } 
/*  339 */       read = dis.read(this.flag, 0, 8);
/*  340 */       if (read != 8) {
/*  341 */         IncorrectHeaderException ex = new IncorrectHeaderException(AmsErrorMessages.mju_ambi_header_invalid);
/*  342 */         if (Trace.isOn) {
/*  343 */           Trace.throwing(this, "com.ibm.mq.ese.core.AMBIHeader", "<init>(byte [ ])", ex, 4);
/*      */         }
/*  345 */         throw ex;
/*      */       } 
/*  347 */       read = dis.read(this.reserved, 0, 8);
/*  348 */       if (read != 8) {
/*  349 */         IncorrectHeaderException ex = new IncorrectHeaderException(AmsErrorMessages.mju_ambi_header_invalid);
/*  350 */         if (Trace.isOn) {
/*  351 */           Trace.throwing(this, "com.ibm.mq.ese.core.AMBIHeader", "<init>(byte [ ])", ex, 5);
/*      */         }
/*  353 */         throw ex;
/*      */       } 
/*  355 */       read = dis.read(this.dynamicQueueName, 0, 48);
/*  356 */       if (read != 48) {
/*  357 */         IncorrectHeaderException ex = new IncorrectHeaderException(AmsErrorMessages.mju_ambi_header_invalid);
/*  358 */         if (Trace.isOn) {
/*  359 */           Trace.throwing(this, "com.ibm.mq.ese.core.AMBIHeader", "<init>(byte [ ])", ex, 6);
/*      */         }
/*  361 */         throw ex;
/*      */       } 
/*  363 */       if (this.versionMajor >= 3) {
/*  364 */         setIVBlockSize(dis.readShort());
/*  365 */         setTagBlockSize(dis.readShort());
/*  366 */         setEncBlockSize(dis.readInt());
/*      */       }
/*      */     
/*  369 */     } catch (Exception e) {
/*  370 */       if (Trace.isOn) {
/*  371 */         Trace.catchBlock(this, "com.ibm.mq.ese.core.AMBIHeader", "<init>(byte [ ])", e);
/*      */       }
/*  373 */       IncorrectHeaderException ex = new IncorrectHeaderException(AmsErrorMessages.mju_ambi_header_invalid);
/*  374 */       if (Trace.isOn) {
/*  375 */         Trace.throwing(this, "com.ibm.mq.ese.core.AMBIHeader", "<init>(byte [ ])", ex, 7);
/*      */       }
/*  377 */       throw ex;
/*      */     } 
/*      */ 
/*      */     
/*  381 */     if (!verifyHeaderID(this.structid)) {
/*  382 */       IncorrectHeaderException ex = new IncorrectHeaderException(AmsErrorMessages.mju_ambi_header_invalid);
/*  383 */       if (Trace.isOn) {
/*  384 */         Trace.throwing(this, "com.ibm.mq.ese.core.AMBIHeader", "<init>(byte [ ])", ex, 8);
/*      */       }
/*  386 */       throw ex;
/*      */     } 
/*      */ 
/*      */     
/*  390 */     if (this.headerEncoding == 2) {
/*  391 */       this.headerSize = convertInt(this.headerSize);
/*  392 */       this.dataOffset = convertInt(this.dataOffset);
/*  393 */       this.protType = convertInt(this.protType);
/*  394 */       this.headerCCSID = convertInt(this.headerCCSID);
/*  395 */       this.origSize = convertInt(this.origSize);
/*  396 */       this.reserved1 = convertInt(this.reserved1);
/*  397 */       if (this.versionMajor >= 3) {
/*  398 */         setKeyBlockSize(convertInt(getKeyBlockSize()));
/*  399 */         setIVBlockSize(convertShort(getIVBlockSize()));
/*  400 */         setEncBlockSize(convertInt(getEncBlockSize()));
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  405 */     if ((this.protType & 0x20) != 0) {
/*  406 */       this.reuseKey_set = true;
/*  407 */       this.protType &= 0xFFFFFFDF;
/*      */     } 
/*      */     
/*  410 */     if (Trace.isOn) {
/*  411 */       Trace.exit(this, "com.ibm.mq.ese.core.AMBIHeader", "<init>(byte [ ])");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public int getSize() {
/*  417 */     int result = 0;
/*  418 */     switch (this.versionMajor)
/*      */     { case 3:
/*  420 */         result = 112;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  427 */         return result; }  result = 104; return result;
/*      */   }
/*      */   
/*      */   public static boolean verifyHeaderID(byte[] id) {
/*  431 */     if (Trace.isOn) {
/*  432 */       Trace.entry("com.ibm.mq.ese.core.AMBIHeader", "verifyHeaderID(byte [ ])", new Object[] { id });
/*      */     }
/*  434 */     for (int i = 0; i < 4; i++) {
/*  435 */       if (id[i] != AMBI_HEADER_STRUCTID[i]) {
/*  436 */         if (Trace.isOn) {
/*  437 */           Trace.exit("com.ibm.mq.ese.core.AMBIHeader", "verifyHeaderID(byte [ ])", 
/*  438 */               Boolean.valueOf(false), 1);
/*      */         }
/*  440 */         return false;
/*      */       } 
/*      */     } 
/*  443 */     if (Trace.isOn) {
/*  444 */       Trace.exit("com.ibm.mq.ese.core.AMBIHeader", "verifyHeaderID(byte [ ])", Boolean.valueOf(true), 2);
/*      */     }
/*      */     
/*  447 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getOrigFormat() {
/*      */     String ret;
/*      */     try {
/*  460 */       ret = new String(this.origFormat, "UTF8");
/*  461 */     } catch (UnsupportedEncodingException e) {
/*      */       
/*  463 */       ret = new String(this.origFormat);
/*  464 */       if (Trace.isOn) {
/*  465 */         Trace.catchBlock(this, "com.ibm.mq.ese.core.AMBIHeader", "getOrigFormat() cannot get in UTF8, using native", e);
/*      */       }
/*      */     } 
/*  468 */     if (Trace.isOn) {
/*  469 */       Trace.data(this, "com.ibm.mq.ese.core.AMBIHeader", "getOrigFormat()", "getter", ret);
/*      */     }
/*  471 */     return ret;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getOrigFormatPlatform() {
/*  480 */     String ret = new String(this.origFormat);
/*  481 */     if (Trace.isOn) {
/*  482 */       Trace.data(this, "com.ibm.mq.ese.core.AMBIHeader", "getOrigFormatPlatform()", "getter", ret);
/*      */     }
/*  484 */     return ret;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getOrigFormatData() {
/*  492 */     if (Trace.isOn) {
/*  493 */       Trace.data(this, "com.ibm.mq.ese.core.AMBIHeader", "getOrigFormatData()", "getter", this.origFormat);
/*      */     }
/*      */     
/*  496 */     return this.origFormat;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setOrigFormat(byte[] format) {
/*  505 */     if (Trace.isOn) {
/*  506 */       Trace.data(this, "com.ibm.mq.ese.core.AMBIHeader", "setOrigFormat(byte [ ])", "setter", format);
/*      */     }
/*      */     
/*  509 */     for (int i = 0; i < 8; i++) {
/*  510 */       this.origFormat[i] = 32;
/*      */     }
/*      */     
/*  513 */     int len = 0;
/*  514 */     if (format.length > 8) {
/*  515 */       len = 8;
/*      */     } else {
/*      */       
/*  518 */       len = format.length;
/*      */     } 
/*  520 */     System.arraycopy(format, 0, this.origFormat, 0, len);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public String getDynamicQueueName() {
/*  531 */     String ret = new String(this.dynamicQueueName);
/*  532 */     if (Trace.isOn) {
/*  533 */       Trace.data(this, "com.ibm.mq.ese.core.AMBIHeader", "getDynamicQueueName()", "getter", ret);
/*      */     }
/*  535 */     return ret;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public byte[] getDynamicQueueNameData() {
/*  545 */     if (Trace.isOn) {
/*  546 */       Trace.data(this, "com.ibm.mq.ese.core.AMBIHeader", "getDynamicQueueNameData()", "getter", this.dynamicQueueName);
/*      */     }
/*      */     
/*  549 */     return this.dynamicQueueName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] toByteArray() throws AMBIException {
/*  559 */     if (Trace.isOn) {
/*  560 */       Trace.entry(this, "com.ibm.mq.ese.core.AMBIHeader", "toByteArray()");
/*      */     }
/*  562 */     ByteArrayOutputStream baos = new ByteArrayOutputStream(getSize());
/*  563 */     baos.write(this.structid, 0, 4);
/*  564 */     baos.write(this.versionMajor);
/*  565 */     baos.write(this.versionMinor);
/*  566 */     baos.write(this.headerEncoding);
/*  567 */     baos.write(this.dynamicQueueInfo);
/*      */     
/*  569 */     DataOutputStream dos = new DataOutputStream(baos);
/*      */     try {
/*  571 */       dos.writeInt(this.headerSize);
/*  572 */       dos.writeInt(this.dataOffset);
/*  573 */       dos.writeInt(this.protType | (reuseKeyIsset() ? 32 : 0));
/*  574 */       dos.writeInt(this.headerCCSID);
/*  575 */       dos.writeInt(this.origSize);
/*  576 */       if (this.versionMajor >= 3) {
/*  577 */         dos.writeInt(getKeyBlockSize());
/*      */       } else {
/*      */         
/*  580 */         dos.writeInt(this.reserved1);
/*      */       } 
/*  582 */       dos.write(this.origFormat, 0, 8);
/*  583 */       dos.write(this.flag, 0, 8);
/*  584 */       dos.write(this.reserved, 0, 8);
/*  585 */       dos.write(this.dynamicQueueName, 0, 48);
/*  586 */       if (this.versionMajor >= 3) {
/*  587 */         dos.writeShort(getIVBlockSize());
/*  588 */         dos.writeShort(getTagBlockSize());
/*  589 */         dos.writeInt(getEncBlockSize());
/*      */       }
/*      */     
/*  592 */     } catch (Exception e) {
/*  593 */       if (Trace.isOn) {
/*  594 */         Trace.catchBlock(this, "com.ibm.mq.ese.core.AMBIHeader", "toByteArray()", e);
/*      */       }
/*  596 */       AMBIException ex = new AMBIException(AmsErrorMessages.mju_ambi_header_io_error);
/*  597 */       if (Trace.isOn) {
/*  598 */         Trace.throwing(this, "com.ibm.mq.ese.core.AMBIHeader", "toByteArray()", ex);
/*      */       }
/*  600 */       throw ex;
/*      */     } 
/*      */     
/*  603 */     byte[] retval = baos.toByteArray();
/*  604 */     if (Trace.isOn) {
/*  605 */       Trace.exit(this, "com.ibm.mq.ese.core.AMBIHeader", "toByteArray()", retval);
/*      */     }
/*  607 */     return retval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/*  615 */     if (Trace.isOn) {
/*  616 */       Trace.entry(this, "com.ibm.mq.ese.core.AMBIHeader", "toString()");
/*      */     }
/*  618 */     int i = 0;
/*  619 */     StringBuilder s = new StringBuilder("structid ");
/*  620 */     for (i = 0; i < 4; i++) {
/*  621 */       s.append((char)this.structid[i]);
/*      */     }
/*  623 */     s.append(" versionMajor ");
/*  624 */     s.append(this.versionMajor);
/*  625 */     s.append("versionMinor ");
/*  626 */     s.append(this.versionMinor);
/*  627 */     s.append("headerEncoding ");
/*  628 */     s.append(this.headerEncoding);
/*  629 */     s.append("dynamicQueueInfo ");
/*  630 */     s.append(this.dynamicQueueInfo);
/*  631 */     s.append("headerSize ");
/*  632 */     s.append(this.headerSize);
/*  633 */     s.append("dataOffset ");
/*  634 */     s.append(this.dataOffset);
/*  635 */     s.append("protType ");
/*  636 */     s.append(this.protType);
/*  637 */     s.append("headerCCSID ");
/*  638 */     s.append(this.headerCCSID);
/*  639 */     s.append("origSize ");
/*  640 */     s.append(this.origSize);
/*  641 */     s.append("reserved1 ");
/*  642 */     s.append(this.reserved1);
/*  643 */     s.append("origFormat ");
/*  644 */     for (i = 0; i < 8; i++) {
/*  645 */       s.append((char)this.origFormat[i]);
/*      */     }
/*      */     
/*  648 */     s.append(" flag ");
/*  649 */     for (i = 0; i < 8; i++) {
/*  650 */       s.append((char)this.flag[i]);
/*      */     }
/*      */     
/*  653 */     s.append(" reserved ");
/*  654 */     for (i = 0; i < 8; i++) {
/*  655 */       s.append((char)this.reserved[i]);
/*      */     }
/*      */     
/*  658 */     s.append(" dynamicQueueName ");
/*  659 */     for (i = 0; i < 48; i++) {
/*  660 */       s.append((char)this.dynamicQueueName[i]);
/*      */     }
/*      */     
/*  663 */     s.append(" keyBlockSize ");
/*  664 */     s.append(getKeyBlockSize());
/*  665 */     s.append(" IVBlockSize ");
/*  666 */     s.append(getIVBlockSize());
/*  667 */     s.append(" EncBlockSize ");
/*  668 */     s.append(getEncBlockSize());
/*      */     
/*  670 */     String traceRet1 = s.toString();
/*  671 */     if (Trace.isOn) {
/*  672 */       Trace.exit(this, "com.ibm.mq.ese.core.AMBIHeader", "toString()", traceRet1);
/*      */     }
/*  674 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte getHeaderEncoding() {
/*  683 */     if (Trace.isOn) {
/*  684 */       Trace.data(this, "com.ibm.mq.ese.core.AMBIHeader", "getHeaderEncoding()", "getter", 
/*  685 */           Byte.valueOf(this.headerEncoding));
/*      */     }
/*  687 */     return this.headerEncoding;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte getDynamicQueueInfo() {
/*  696 */     if (Trace.isOn) {
/*  697 */       Trace.data(this, "com.ibm.mq.ese.core.AMBIHeader", "getDynamicQueueInfo()", "getter", 
/*  698 */           Byte.valueOf(this.dynamicQueueInfo));
/*      */     }
/*  700 */     return this.dynamicQueueInfo;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getHeaderSize() {
/*  709 */     if (Trace.isOn) {
/*  710 */       Trace.data(this, "com.ibm.mq.ese.core.AMBIHeader", "getHeaderSize()", "getter", 
/*  711 */           Integer.valueOf(this.headerSize));
/*      */     }
/*  713 */     return this.headerSize;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getDataOffset() {
/*  722 */     if (Trace.isOn) {
/*  723 */       Trace.data(this, "com.ibm.mq.ese.core.AMBIHeader", "getDataOffset()", "getter", 
/*  724 */           Integer.valueOf(this.dataOffset));
/*      */     }
/*  726 */     return this.dataOffset;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getProtType() {
/*  735 */     if (Trace.isOn) {
/*  736 */       Trace.data(this, "com.ibm.mq.ese.core.AMBIHeader", "getProtType()", "getter", 
/*  737 */           Integer.valueOf(this.protType));
/*      */     }
/*  739 */     return this.protType;
/*      */   }
/*      */   
/*      */   public int getQop() {
/*  743 */     int qop = 0;
/*  744 */     switch (this.protType) {
/*      */       case 8:
/*  746 */         qop = 2;
/*      */         break;
/*      */       case 4:
/*  749 */         qop = 1;
/*      */         break;
/*      */       case 64:
/*  752 */         qop = 3;
/*      */         break;
/*      */     } 
/*      */ 
/*      */     
/*  757 */     if (Trace.isOn) {
/*  758 */       Trace.data(this, "com.ibm.mq.ese.core.AMBIHeader", "getQop()", "getter", 
/*  759 */           Integer.valueOf(qop));
/*      */     }
/*  761 */     return qop;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getOrigSize() {
/*  770 */     if (Trace.isOn) {
/*  771 */       Trace.data(this, "com.ibm.mq.ese.core.AMBIHeader", "getOrigSize()", "getter", 
/*  772 */           Integer.valueOf(this.origSize));
/*      */     }
/*  774 */     return this.origSize;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getJMSFormat() {
/*  783 */     String traceRet1 = getStringFromByteFormat();
/*  784 */     if (Trace.isOn) {
/*  785 */       Trace.data(this, "com.ibm.mq.ese.core.AMBIHeader", "getJMSFormat()", "getter", traceRet1);
/*      */     }
/*  787 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getHeaderCCSID() {
/*  796 */     if (Trace.isOn) {
/*  797 */       Trace.data(this, "com.ibm.mq.ese.core.AMBIHeader", "getHeaderCCSID()", "getter", 
/*  798 */           Integer.valueOf(this.headerCCSID));
/*      */     }
/*  800 */     return this.headerCCSID;
/*      */   }
/*      */   
/*      */   public byte getVersionMajor() {
/*  804 */     if (Trace.isOn) {
/*  805 */       Trace.data(this, "com.ibm.mq.ese.core.AMBIHeader", "getVersionMajor()", "getter", 
/*  806 */           Byte.valueOf(this.versionMajor));
/*      */     }
/*  808 */     return this.versionMajor;
/*      */   }
/*      */   
/*      */   public byte getVersionMinor() {
/*  812 */     if (Trace.isOn) {
/*  813 */       Trace.data(this, "com.ibm.mq.ese.core.AMBIHeader", "getVersionMinor()", "getter", 
/*  814 */           Byte.valueOf(this.versionMinor));
/*      */     }
/*  816 */     return this.versionMinor;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setHeaderEncoding(byte encoding) {
/*  825 */     if (Trace.isOn) {
/*  826 */       Trace.data(this, "com.ibm.mq.ese.core.AMBIHeader", "setHeaderEncoding(byte)", "setter", 
/*  827 */           Byte.valueOf(encoding));
/*      */     }
/*  829 */     this.headerEncoding = encoding;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDynamicQueueInfo(byte a) {
/*  838 */     if (Trace.isOn) {
/*  839 */       Trace.data(this, "com.ibm.mq.ese.core.AMBIHeader", "setDynamicQueueInfo(byte)", "setter", 
/*  840 */           Byte.valueOf(a));
/*      */     }
/*  842 */     this.dynamicQueueInfo = a;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setHeaderSize(int a) {
/*  851 */     if (Trace.isOn) {
/*  852 */       Trace.data(this, "com.ibm.mq.ese.core.AMBIHeader", "setHeaderSize(int)", "setter", 
/*  853 */           Integer.valueOf(a));
/*      */     }
/*  855 */     this.headerSize = a;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDataOffset(int a) {
/*  864 */     if (Trace.isOn) {
/*  865 */       Trace.data(this, "com.ibm.mq.ese.core.AMBIHeader", "setDataOffset(int)", "setter", 
/*  866 */           Integer.valueOf(a));
/*      */     }
/*  868 */     this.dataOffset = a;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setProtType(int prot) {
/*  877 */     if (Trace.isOn) {
/*  878 */       Trace.data(this, "com.ibm.mq.ese.core.AMBIHeader", "setProtType(int)", "setter", 
/*  879 */           Integer.valueOf(prot));
/*      */     }
/*  881 */     this.protType = prot;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setOrigSize(int a) {
/*  890 */     if (Trace.isOn) {
/*  891 */       Trace.data(this, "com.ibm.mq.ese.core.AMBIHeader", "setOrigSize(int)", "setter", 
/*  892 */           Integer.valueOf(a));
/*      */     }
/*  894 */     this.origSize = a;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setHeaderCCSID(int a) {
/*  903 */     if (Trace.isOn) {
/*  904 */       Trace.data(this, "com.ibm.mq.ese.core.AMBIHeader", "setHeaderCCSID(int)", "setter", 
/*  905 */           Integer.valueOf(a));
/*      */     }
/*  907 */     this.headerCCSID = a;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setOrigFormat(String s) throws UnsupportedEncodingException {
/*  918 */     if (Trace.isOn) {
/*  919 */       Trace.data(this, "com.ibm.mq.ese.core.AMBIHeader", "setOrigFormat(String)", "setter", s);
/*      */     }
/*      */     
/*  922 */     for (int i = 0; i < 8; i++) {
/*  923 */       this.origFormat[i] = 32;
/*      */     }
/*      */     
/*  926 */     int len = 0;
/*  927 */     if (s.length() > 8) {
/*  928 */       len = 8;
/*      */     } else {
/*      */       
/*  931 */       len = s.length();
/*      */     } 
/*  933 */     System.arraycopy(s.getBytes("UTF8"), 0, this.origFormat, 0, len);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setJMSFormat(String s) {
/*  943 */     if (Trace.isOn) {
/*  944 */       Trace.data(this, "com.ibm.mq.ese.core.AMBIHeader", "setJMSFormat(String)", "setter", s);
/*      */     }
/*  946 */     this.reserved[0] = getByteFromStringFormat(s);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDynamicQueueName(String s) throws UnsupportedEncodingException {
/*  957 */     if (Trace.isOn) {
/*  958 */       Trace.data(this, "com.ibm.mq.ese.core.AMBIHeader", "setDynamicQueueName(String)", "setter", s);
/*      */     }
/*      */     
/*  961 */     int len = 0;
/*  962 */     if (s.length() > 48) {
/*  963 */       len = 48;
/*      */     } else {
/*      */       
/*  966 */       len = s.length();
/*      */     } 
/*  968 */     System.arraycopy(s.getBytes("UTF8"), 0, this.dynamicQueueName, 0, len);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDynamicQueueName(byte[] s) {
/*  978 */     if (Trace.isOn) {
/*  979 */       Trace.data(this, "com.ibm.mq.ese.core.AMBIHeader", "setDynamicQueueName(byte [ ])", "setter", s);
/*      */     }
/*      */     
/*  982 */     for (int i = 0; i < 48; i++) {
/*  983 */       this.dynamicQueueName[i] = 32;
/*      */     }
/*  985 */     int len = 0;
/*  986 */     if (s.length > 48) {
/*  987 */       len = 48;
/*      */     } else {
/*      */       
/*  990 */       len = s.length;
/*      */     } 
/*  992 */     System.arraycopy(s, 0, this.dynamicQueueName, 0, len);
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
/*      */   public void setPolicyname(String name) throws UnsupportedEncodingException {
/* 1004 */     if (Trace.isOn) {
/* 1005 */       Trace.data(this, "com.ibm.mq.ese.core.AMBIHeader", "setPolicyname(String)", "setter", name);
/*      */     }
/* 1007 */     setDynamicQueueName(name);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFlag(byte[] newFlag) {
/* 1016 */     if (Trace.isOn) {
/* 1017 */       Trace.data(this, "com.ibm.mq.ese.core.AMBIHeader", "setFlag(byte [ ])", "setter", newFlag);
/*      */     }
/* 1019 */     this.flag = newFlag;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getFlag() {
/* 1028 */     if (Trace.isOn) {
/* 1029 */       Trace.data(this, "com.ibm.mq.ese.core.AMBIHeader", "getFlag()", "getter", this.flag);
/*      */     }
/* 1031 */     return this.flag;
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
/*      */   @Deprecated
/*      */   private byte getByteFromStringFormat(String s) {
/* 1044 */     if (Trace.isOn) {
/* 1045 */       Trace.entry(this, "com.ibm.mq.ese.core.AMBIHeader", "getByteFromStringFormat(String)", new Object[] { s });
/*      */     }
/*      */     
/* 1048 */     byte b = 6;
/* 1049 */     if (Trace.isOn) {
/* 1050 */       Trace.exit(this, "com.ibm.mq.ese.core.AMBIHeader", "getByteFromStringFormat(String)", 
/* 1051 */           Byte.valueOf(b));
/*      */     }
/* 1053 */     return b;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   private String getStringFromByteFormat() {
/* 1064 */     String s = "NONE";
/*      */     
/* 1066 */     if (Trace.isOn) {
/* 1067 */       Trace.data(this, "com.ibm.mq.ese.core.AMBIHeader", "getStringFromByteFormat()", "getter", s);
/*      */     }
/* 1069 */     return s;
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
/*      */   private int convertInt(int v) {
/* 1084 */     if (Trace.isOn)
/* 1085 */       Trace.entry(this, "com.ibm.mq.ese.core.AMBIHeader", "convertInt(int)", new Object[] {
/* 1086 */             Integer.valueOf(v)
/*      */           }); 
/* 1088 */     int traceRet1 = v >>> 24 | v << 24 | v << 8 & 0xFF0000 | v >> 8 & 0xFF00;
/*      */     
/* 1090 */     if (Trace.isOn) {
/* 1091 */       Trace.exit(this, "com.ibm.mq.ese.core.AMBIHeader", "convertInt(int)", 
/* 1092 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1094 */     return traceRet1;
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
/*      */   private short convertShort(short v) {
/* 1108 */     if (Trace.isOn)
/* 1109 */       Trace.entry(this, "com.ibm.mq.ese.core.AMBIHeader", "convertShort(short)", new Object[] {
/* 1110 */             Short.valueOf(v)
/*      */           }); 
/* 1112 */     short traceRet1 = (short)(v << 8 & 0xFF00 | v >> 8 & 0xFF);
/*      */     
/* 1114 */     if (Trace.isOn) {
/* 1115 */       Trace.exit(this, "com.ibm.mq.ese.core.AMBIHeader", "convertShort(short)", 
/* 1116 */           Short.valueOf(traceRet1));
/*      */     }
/* 1118 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isNullMessage() {
/* 1125 */     boolean traceRet1 = (this.flag != null && this.flag[0] == 1);
/* 1126 */     if (Trace.isOn) {
/* 1127 */       Trace.data(this, "com.ibm.mq.ese.core.AMBIHeader", "isNullMessage()", "getter", 
/* 1128 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 1130 */     return traceRet1;
/*      */   }
/*      */   
/*      */   public boolean reuseKeyIsset() {
/* 1134 */     if (Trace.isOn) {
/* 1135 */       Trace.data(this, "com.ibm.mq.ese.intercept.SmqiObject", "reuseKeyIsset()", "getter", Boolean.valueOf(this.reuseKey_set));
/*      */     }
/* 1137 */     return this.reuseKey_set;
/*      */   }
/*      */   
/*      */   public void setReuseKey(boolean reuseKey_set) {
/* 1141 */     if (Trace.isOn) {
/* 1142 */       Trace.data(this, "com.ibm.mq.ese.intercept.SmqiObject", "setReuseKey()", "setter", Boolean.valueOf(reuseKey_set));
/*      */     }
/* 1144 */     this.reuseKey_set = reuseKey_set;
/*      */   }
/*      */   
/*      */   public int getKeyBlockSize() {
/* 1148 */     if (Trace.isOn) {
/* 1149 */       Trace.data(this, "com.ibm.mq.ese.intercept.SmqiObject", "getKeyBlockSize()", "getter", Integer.valueOf(this.keyBlockSize));
/*      */     }
/* 1151 */     return this.keyBlockSize;
/*      */   }
/*      */   
/*      */   public void setKeyBlockSize(int keyBlockSize) {
/* 1155 */     if (Trace.isOn) {
/* 1156 */       Trace.data(this, "com.ibm.mq.ese.intercept.SmqiObject", "setKeyBlockSize()", "setter", Integer.valueOf(keyBlockSize));
/*      */     }
/* 1158 */     this.keyBlockSize = keyBlockSize;
/*      */   }
/*      */   
/*      */   public short getIVBlockSize() {
/* 1162 */     if (Trace.isOn) {
/* 1163 */       Trace.data(this, "com.ibm.mq.ese.intercept.SmqiObject", "getIVBlockSize()", "getter", Short.valueOf(this.IVBlockSize));
/*      */     }
/* 1165 */     return this.IVBlockSize;
/*      */   }
/*      */   
/*      */   public void setIVBlockSize(short iVBlockSize) {
/* 1169 */     if (Trace.isOn) {
/* 1170 */       Trace.data(this, "com.ibm.mq.ese.intercept.SmqiObject", "setIVBlockSize()", "setter", Short.valueOf(iVBlockSize));
/*      */     }
/* 1172 */     this.IVBlockSize = iVBlockSize;
/*      */   }
/*      */   
/*      */   public int getEncBlockSize() {
/* 1176 */     if (Trace.isOn) {
/* 1177 */       Trace.data(this, "com.ibm.mq.ese.intercept.SmqiObject", "getEncBlockSize()", "getter", Integer.valueOf(this.EncBlockSize));
/*      */     }
/* 1179 */     return this.EncBlockSize;
/*      */   }
/*      */   
/*      */   public void setEncBlockSize(int encBlockSize) {
/* 1183 */     if (Trace.isOn) {
/* 1184 */       Trace.data(this, "com.ibm.mq.ese.intercept.SmqiObject", "setEncBlockSize()", "setter", Integer.valueOf(encBlockSize));
/*      */     }
/* 1186 */     this.EncBlockSize = encBlockSize;
/*      */   }
/*      */   
/*      */   public void setVersionMajor(byte versionMajor) {
/* 1190 */     this.versionMajor = versionMajor;
/*      */   }
/*      */   
/*      */   public static byte getVersion(byte[] buf, int offset) {
/* 1194 */     byte version = buf[offset + 4];
/* 1195 */     if (Trace.isOn) {
/* 1196 */       Trace.data("AMBIHEader", "com.ibm.mq.ese.intercept.SmqiObject", "getVersion()", "getter", Byte.valueOf(version));
/*      */     }
/* 1198 */     return version;
/*      */   }
/*      */   
/*      */   public short getTagBlockSize() {
/* 1202 */     if (Trace.isOn) {
/* 1203 */       Trace.data(this, "com.ibm.mq.ese.intercept.SmqiObject", "getTagBlockSize()", "getter", Short.valueOf(this.tagBlockSize));
/*      */     }
/* 1205 */     return this.tagBlockSize;
/*      */   }
/*      */   
/*      */   public void setTagBlockSize(short tagBlockSize) {
/* 1209 */     if (Trace.isOn) {
/* 1210 */       Trace.data(this, "com.ibm.mq.ese.intercept.SmqiObject", "setTagBlockSize()", "setter", Short.valueOf(tagBlockSize));
/*      */     }
/* 1212 */     this.tagBlockSize = tagBlockSize;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\core\AMBIHeader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */