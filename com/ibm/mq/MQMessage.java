/*      */ package com.ibm.mq;
/*      */ 
/*      */ import com.ibm.mq.headers.MQChainable;
/*      */ import com.ibm.mq.headers.MQDataException;
/*      */ import com.ibm.mq.headers.MQHeaderList;
/*      */ import com.ibm.mq.headers.MQMD1;
/*      */ import com.ibm.mq.headers.MQRFH2;
/*      */ import com.ibm.mq.headers.MQXQH;
/*      */ import com.ibm.mq.headers.internal.Header;
/*      */ import com.ibm.mq.headers.internal.MessageWrapper;
/*      */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*      */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.io.DataInput;
/*      */ import java.io.DataInputStream;
/*      */ import java.io.DataOutput;
/*      */ import java.io.DataOutputStream;
/*      */ import java.io.EOFException;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.InvalidClassException;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.OptionalDataException;
/*      */ import java.io.OutputStream;
/*      */ import java.io.StreamCorruptedException;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.ByteOrder;
/*      */ import java.nio.CharBuffer;
/*      */ import java.nio.charset.CharacterCodingException;
/*      */ import java.nio.charset.CharsetDecoder;
/*      */ import java.nio.charset.CoderResult;
/*      */ import java.nio.charset.UnsupportedCharsetException;
/*      */ import java.text.MessageFormat;
/*      */ import java.util.Arrays;
/*      */ import java.util.Enumeration;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.Vector;
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
/*      */ public class MQMessage
/*      */   extends MQMD
/*      */   implements DataInput, DataOutput
/*      */ {
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQMessage.java";
/*      */   protected static final int READ_MODE = 1;
/*      */   protected static final int WRITE_MODE = 2;
/*      */   private static final int CODESET_UCS = 1200;
/*      */   
/*      */   static {
/*  139 */     if (Trace.isOn) {
/*  140 */       Trace.data("com.ibm.mq.MQMessage", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQMessage.java");
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
/*      */   private static class MQMessageHeaderList
/*      */     extends MQHeaderList
/*      */   {
/*      */     public MQMessageHeaderList(DataInput message, boolean readBody) throws MQDataException, IOException {
/*  198 */       super(message, readBody);
/*      */     }
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
/*      */     public int write(DataOutput message, boolean writeBody, boolean writeStringBodyUsingCharacterSetInLastHeader) throws IOException {
/*  242 */       if (Trace.isOn) {
/*  243 */         Trace.entry(this, "com.ibm.mq.headers.MQMessageHeaderList", "write(DataOutput,boolean,boolean)", new Object[] { message, 
/*  244 */               Boolean.valueOf(writeBody) });
/*      */       }
/*  246 */       int traceRet1 = write(message, 
/*  247 */           MessageWrapper.wrap(message).getEncoding(), 
/*  248 */           MessageWrapper.wrap(message).getCharacterSet(), writeBody, writeStringBodyUsingCharacterSetInLastHeader);
/*      */ 
/*      */       
/*  251 */       if (Trace.isOn) {
/*  252 */         Trace.exit(this, "com.ibm.mq.headers.MQMessageHeaderList", "write(DataOutput,boolean,boolean)", 
/*  253 */             Integer.valueOf(traceRet1));
/*      */       }
/*  255 */       return traceRet1;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static class MQMessageOutputStream
/*      */     extends OutputStream
/*      */   {
/*      */     private MQMessage parent;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public MQMessageOutputStream(MQMessage parent) {
/*  277 */       if (Trace.isOn) {
/*  278 */         Trace.entry(this, "com.ibm.mq.MQMessageOutputStream", "<init>(MQMessage)", new Object[] { parent });
/*      */       }
/*      */       
/*  281 */       this.parent = parent;
/*      */       
/*  283 */       if (Trace.isOn) {
/*  284 */         Trace.exit(this, "com.ibm.mq.MQMessageOutputStream", "<init>(MQMessage)");
/*      */       }
/*      */     }
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
/*      */     public void write(byte[] arg0, int arg1, int arg2) throws IOException {
/*  301 */       if (Trace.isOn) {
/*  302 */         Trace.entry(this, "com.ibm.mq.MQMessageOutputStream", "write(byte [ ],int,int)", new Object[] { arg0, 
/*  303 */               Integer.valueOf(arg1), Integer.valueOf(arg2) });
/*      */       }
/*  305 */       this.parent.write(arg0, arg1, arg2);
/*      */       
/*  307 */       if (Trace.isOn) {
/*  308 */         Trace.exit(this, "com.ibm.mq.MQMessageOutputStream", "write(byte [ ],int,int)");
/*      */       }
/*      */     }
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
/*      */     public void write(byte[] arg0) throws IOException {
/*  322 */       if (Trace.isOn) {
/*  323 */         Trace.entry(this, "com.ibm.mq.MQMessageOutputStream", "write(byte [ ])", new Object[] { arg0 });
/*      */       }
/*      */       
/*  326 */       this.parent.write(arg0);
/*      */       
/*  328 */       if (Trace.isOn) {
/*  329 */         Trace.exit(this, "com.ibm.mq.MQMessageOutputStream", "write(byte [ ])");
/*      */       }
/*      */     }
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
/*      */     public void write(int arg0) throws IOException {
/*  343 */       if (Trace.isOn)
/*  344 */         Trace.entry(this, "com.ibm.mq.MQMessageOutputStream", "write(int)", new Object[] {
/*  345 */               Integer.valueOf(arg0)
/*      */             }); 
/*  347 */       this.parent.write(arg0);
/*      */       
/*  349 */       if (Trace.isOn) {
/*  350 */         Trace.exit(this, "com.ibm.mq.MQMessageOutputStream", "write(int)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  358 */   private MQMessageOutputStream proxyOutputStream = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static class MQMessageInputStream
/*      */     extends InputStream
/*      */   {
/*      */     private MQMessage parent;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public MQMessageInputStream(MQMessage parent) {
/*  376 */       if (Trace.isOn) {
/*  377 */         Trace.entry(this, "com.ibm.mq.MQMessageInputStream", "<init>(MQMessage)", new Object[] { parent });
/*      */       }
/*      */       
/*  380 */       this.parent = parent;
/*      */       
/*  382 */       if (Trace.isOn) {
/*  383 */         Trace.exit(this, "com.ibm.mq.MQMessageInputStream", "<init>(MQMessage)");
/*      */       }
/*      */     }
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
/*      */     public int read() throws IOException {
/*  397 */       if (Trace.isOn) {
/*  398 */         Trace.entry(this, "com.ibm.mq.MQMessageInputStream", "read()");
/*      */       }
/*  400 */       int traceRet1 = this.parent.readUnsignedByte();
/*      */       
/*  402 */       if (Trace.isOn) {
/*  403 */         Trace.exit(this, "com.ibm.mq.MQMessageInputStream", "read()", Integer.valueOf(traceRet1));
/*      */       }
/*  405 */       return traceRet1;
/*      */     }
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
/*      */     public int read(byte[] arg0, int arg1, int arg2) throws IOException {
/*  420 */       if (Trace.isOn) {
/*  421 */         Trace.entry(this, "com.ibm.mq.MQMessageInputStream", "read(byte [ ],int,int)", new Object[] { arg0, 
/*  422 */               Integer.valueOf(arg1), Integer.valueOf(arg2) });
/*      */       }
/*  424 */       this.parent.readFully(arg0, arg1, arg2);
/*      */       
/*  426 */       if (Trace.isOn) {
/*  427 */         Trace.exit(this, "com.ibm.mq.MQMessageInputStream", "read(byte [ ],int,int)", 
/*  428 */             Integer.valueOf(arg2));
/*      */       }
/*  430 */       return arg2;
/*      */     }
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
/*      */     public int read(byte[] arg0) throws IOException {
/*  443 */       if (Trace.isOn) {
/*  444 */         Trace.entry(this, "com.ibm.mq.MQMessageInputStream", "read(byte [ ])", new Object[] { arg0 });
/*      */       }
/*      */       
/*  447 */       this.parent.readFully(arg0);
/*      */       
/*  449 */       if (Trace.isOn) {
/*  450 */         Trace.exit(this, "com.ibm.mq.MQMessageInputStream", "read(byte [ ])", 
/*  451 */             Integer.valueOf(arg0.length));
/*      */       }
/*  453 */       return arg0.length;
/*      */     }
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
/*      */     public long skip(long arg0) throws IOException {
/*  466 */       if (Trace.isOn)
/*  467 */         Trace.entry(this, "com.ibm.mq.MQMessageInputStream", "skip(long)", new Object[] {
/*  468 */               Long.valueOf(arg0)
/*      */             }); 
/*  470 */       int skipLength = 0;
/*  471 */       if (arg0 > 2147483647L) {
/*  472 */         skipLength = Integer.MAX_VALUE;
/*  473 */       } else if (arg0 < 0L) {
/*  474 */         skipLength = 0;
/*      */       } else {
/*  476 */         skipLength = (int)arg0;
/*      */       } 
/*  478 */       if (this.parent.skipBytes(skipLength) != skipLength) {
/*      */         
/*  480 */         EOFException traceRet1 = new EOFException();
/*  481 */         if (Trace.isOn) {
/*  482 */           Trace.throwing(this, "com.ibm.mq.MQMessageInputStream", "skip(long)", traceRet1);
/*      */         }
/*  484 */         throw traceRet1;
/*      */       } 
/*      */       
/*  487 */       if (Trace.isOn) {
/*  488 */         Trace.exit(this, "com.ibm.mq.MQMessageInputStream", "skip(long)", Long.valueOf(arg0));
/*      */       }
/*  490 */       return arg0;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  497 */   private MQMessageInputStream proxyInputStream = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private ByteBuffer dataBuffer;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int ALLOCATION_STEP = 128;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  516 */   private static final ByteBuffer EMPTY_BUFFER = ByteBuffer.allocate(0);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  521 */   private int _totalMessageLength = 0;
/*  522 */   private int _bufferSizeHint = -1;
/*      */ 
/*      */ 
/*      */   
/*      */   private String formatBeforePut;
/*      */ 
/*      */   
/*      */   private ByteBuffer dataBufferBeforePut;
/*      */ 
/*      */   
/*      */   private int characterSetBeforePut;
/*      */ 
/*      */   
/*      */   private int encodingBeforePut;
/*      */ 
/*      */   
/*  538 */   private Map<String, Vector<Object>> properties = new HashMap<>();
/*      */ 
/*      */   
/*      */   private boolean removeRfh2PropertyHeaders;
/*      */   
/*  543 */   private Map<String, Vector<MQPropertyDescriptor>> propertyDescTable = new HashMap<>();
/*      */ 
/*      */   
/*  546 */   private static Map<String, String> JMSPropertySynonyms = null; private int propertyValidation; private static final Set<String> PROPERTIES_FOLDER_NAMES; private static final Set<String> SETTABLE_JMS_PROPERTY_NAMES; private static final Set<String> QUERY_ONLY_JMS_PROPERTY_NAMES; private static final Set<String> ALL_JMS_PROPERTY_NAMES; private static final Set<String> TYPE_MCD_ELEMENTS; private static final Set<String> RESERVED_SQL_PROPERTY_NAMES; private static final Set<String> RESERVED_HIERARCHY_PROPERTY_NAME_PREFIXES; private static final Set<String> ALLOWED_HIERARCHY_PROPERTY_NAMES; private static final Set<String> RESTRICTED_HIERARCHY_FOLDER_NAMES; private static final String replyToURI = "queue://"; private static final String mcdURI = "mcd://"; private static final char[] BIN2HEX; private static final long JMS_UNLIMITED_EXPIRATION = 0L; private static final Long UNLIMITED_EXPIRY;
/*      */   private static final int JMS_DEFAULT_PRIORITY = 4;
/*      */   
/*  549 */   static { if (Trace.isOn) {
/*  550 */       Trace.entry("com.ibm.mq.MQMessage", "static()");
/*      */     }
/*  552 */     JMSPropertySynonyms = new HashMap<>();
/*  553 */     JMSPropertySynonyms.put("jms.Cid", "JMSCorrelationID");
/*  554 */     JMSPropertySynonyms.put("jms.Dlv", "JMSDeliveryMode");
/*  555 */     JMSPropertySynonyms.put("jms.Dst", "JMSDestination");
/*  556 */     JMSPropertySynonyms.put("jms.Exp", "JMSExpiration");
/*  557 */     JMSPropertySynonyms.put("jms.Pri", "JMSPriority");
/*  558 */     JMSPropertySynonyms.put("jms.Rto", "JMSReplyTo");
/*  559 */     JMSPropertySynonyms.put("jms.Tms", "JMSTimestamp");
/*  560 */     JMSPropertySynonyms.put("jms.Gid", "JMSXGroupID");
/*  561 */     JMSPropertySynonyms.put("jms.Seq", "JMSXGroupSeq");
/*  562 */     if (Trace.isOn) {
/*  563 */       Trace.exit("com.ibm.mq.MQMessage", "static()");
/*      */     }
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
/*  575 */     PROPERTIES_FOLDER_NAMES = new HashSet<>(Arrays.asList(new String[] { "mq", "mq_usr", "mqext", "jms", "mcd", "usr", "sib", "sib_usr", "sib_context", "mqema", "mqps" }));
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
/*  588 */     SETTABLE_JMS_PROPERTY_NAMES = new HashSet<>(Arrays.asList(new String[] { "JMSCorrelationID", "JMSReplyTo", "JMSType", "JMSXGroupID", "JMSXGroupSeq" }));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  595 */     QUERY_ONLY_JMS_PROPERTY_NAMES = new HashSet<>(Arrays.asList(new String[] { "JMSDeliveryMode", "JMSXDeliveryCount", "JMSDestination", "JMSExpiration", "JMSMessageID", "JMSPriority", "JMSRedelivered", "JMSTimestamp", "JMSXAppID", "JMSXUserID" }));
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
/*  607 */     ALL_JMS_PROPERTY_NAMES = new HashSet<>();
/*      */     
/*  609 */     if (Trace.isOn) {
/*  610 */       Trace.entry("com.ibm.mq.MQMessage", "static()");
/*      */     }
/*  612 */     ALL_JMS_PROPERTY_NAMES.addAll(SETTABLE_JMS_PROPERTY_NAMES);
/*  613 */     ALL_JMS_PROPERTY_NAMES.addAll(QUERY_ONLY_JMS_PROPERTY_NAMES);
/*  614 */     if (Trace.isOn) {
/*  615 */       Trace.exit("com.ibm.mq.MQMessage", "static()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  620 */     TYPE_MCD_ELEMENTS = new HashSet<>(Arrays.asList(new String[] { "mcd.Type", "mcd.Fmt", "mcd.Set" }));
/*      */ 
/*      */ 
/*      */     
/*  624 */     RESERVED_SQL_PROPERTY_NAMES = new HashSet<>(Arrays.asList(new String[] { "NULL", "TRUE", "FALSE", "NOT", "AND", "OR", "BETWEEN", "LIKE", "IN", "IS", "ESCAPE" }));
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
/*  636 */     RESERVED_HIERARCHY_PROPERTY_NAME_PREFIXES = new HashSet<>(Arrays.asList(new String[] { "Body.", "Root." }));
/*      */ 
/*      */     
/*  639 */     ALLOWED_HIERARCHY_PROPERTY_NAMES = new HashSet<>(Arrays.asList(new String[] { "Root.MQMD" }));
/*      */ 
/*      */ 
/*      */     
/*  643 */     RESTRICTED_HIERARCHY_FOLDER_NAMES = new HashSet<>(Arrays.asList(new String[] { "mq", "jms", "mcd", "usr", "sib" }));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  652 */     BIN2HEX = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
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
/* 4878 */     UNLIMITED_EXPIRY = Long.valueOf(-1L); } public MQMessage() { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "<init>()");  this.dataBuffer = EMPTY_BUFFER; this.dataBuffer.position(0); this.dataBuffer.limit(0); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "<init>()");  } public int getTotalMessageLength() { int traceRet1 = Math.max(this.dataBuffer.limit(), this._totalMessageLength); if (Trace.isOn) Trace.data(this, "com.ibm.mq.MQMessage", "getTotalMessageLength()", "getter", Integer.valueOf(traceRet1));  return traceRet1; } public int getMessageLength() throws IOException { int traceRet1 = this.dataBuffer.limit(); if (Trace.isOn) Trace.data(this, "com.ibm.mq.MQMessage", "getMessageLength()", "getter", Integer.valueOf(traceRet1));  return traceRet1; } private void ensureWriteSpace(int bytesToWrite) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "ensureWriteSpace(int)", new Object[] { Integer.valueOf(bytesToWrite) });  int requiredSize = this.dataBuffer.position() + bytesToWrite; if (requiredSize > this.dataBuffer.limit()) if (requiredSize < this.dataBuffer.capacity()) { this.dataBuffer.limit(requiredSize); } else { int multiplesInRequired = requiredSize / 128; int newMultiple = multiplesInRequired + 1; if (requiredSize % 128 > 64) newMultiple++;  ByteBuffer newDataBuffer = ByteBuffer.allocate(newMultiple * 128); int oldPosition = this.dataBuffer.position(); this.dataBuffer.position(0); newDataBuffer.put(this.dataBuffer); this.dataBuffer = newDataBuffer; this.dataBuffer.position(oldPosition); this.dataBuffer.limit(requiredSize); }   if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "ensureWriteSpace(int)");  } public int getDataLength() throws IOException { int traceRet1 = this.dataBuffer.remaining(); if (Trace.isOn) Trace.data(this, "com.ibm.mq.MQMessage", "getDataLength()", "getter", Integer.valueOf(traceRet1));  return traceRet1; } public void seek(int seekOffset) throws EOFException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "seek(int)", new Object[] { Integer.valueOf(seekOffset) });  if (seekOffset > this.dataBuffer.limit()) { EOFException traceRet1 = new EOFException(MQException.getNLSMsg("MQEOFEXCEPTION", "MQMessage.seek()")); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "seek(int)", traceRet1);  throw traceRet1; }  this.dataBuffer.position(seekOffset); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "seek(int)");  } public void setDataOffset(int offset) throws EOFException { if (Trace.isOn) Trace.data(this, "com.ibm.mq.MQMessage", "setDataOffset(int)", "setter", Integer.valueOf(offset));  seek(offset); } public int getDataOffset() throws IOException { int traceRet1 = this.dataBuffer.position(); if (Trace.isOn) Trace.data(this, "com.ibm.mq.MQMessage", "getDataOffset()", "getter", Integer.valueOf(traceRet1));  return traceRet1; } public void clearMessage() throws IOException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "clearMessage()");  this.dataBuffer = EMPTY_BUFFER; this.dataBuffer.position(0); this.dataBuffer.limit(0); this._totalMessageLength = 0; if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "clearMessage()");  } public void resizeBuffer(int size) throws IOException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "resizeBuffer(int)", new Object[] { Integer.valueOf(size) });  if (size < this.dataBuffer.limit()) { ByteBuffer tmpBuffer = ByteBuffer.allocate(size); int oldPosition = this.dataBuffer.position(); this.dataBuffer.position(0); this.dataBuffer.limit(size); tmpBuffer.put(this.dataBuffer); this.dataBuffer = tmpBuffer; if (oldPosition >= size) oldPosition = size;  this.dataBuffer.limit(size); this.dataBuffer.position(oldPosition); } else if (size > this.dataBuffer.limit()) { this._bufferSizeHint = size; }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "resizeBuffer(int)");  } public boolean readBoolean() throws IOException, EOFException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "readBoolean()");  if (this.dataBuffer.remaining() < 1) { EOFException traceRet1 = new EOFException(MQException.getNLSMsg("MQEOFEXCEPTION", "MQMessage.readBoolean()")); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "readBoolean()", traceRet1);  throw traceRet1; }  boolean retVal = (this.dataBuffer.get() != 0); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "readBoolean()", Boolean.valueOf(retVal));  return retVal; } public byte readByte() throws IOException, EOFException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "readByte()");  if (this.dataBuffer.remaining() < 1) { EOFException traceRet1 = new EOFException(MQException.getNLSMsg("MQEOFEXCEPTION", "MQMessage.readByte()")); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "readByte()", traceRet1);  throw traceRet1; }  byte retVal = this.dataBuffer.get(); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "readByte()", Byte.valueOf(retVal));  return retVal; } public char readChar() throws IOException, EOFException { IOException traceRet2; if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "readChar()");  char retVal = Character.MIN_VALUE; if (this.dataBuffer.remaining() < 2) { EOFException traceRet1 = new EOFException(MQException.getNLSMsg("MQEOFEXCEPTION", "MQMessage.readChar()")); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "readChar()", traceRet1, 1);  throw traceRet1; }  switch (this.encoding & 0xF) { case 0: case 1: this.dataBuffer.order(ByteOrder.BIG_ENDIAN); break;case 2: this.dataBuffer.order(ByteOrder.LITTLE_ENDIAN); break;default: if (Trace.isOn) Trace.data(this, "readChar()", "Invalid encoding : ", Integer.toString(this.encoding & 0xF00));  traceRet2 = new IOException(MQException.getNLSMsg("MQIOEXCEPTION", "MQMessage.readChar()")); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "readChar()", traceRet2, 2);  throw traceRet2; }  retVal = this.dataBuffer.getChar(); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "readChar()", Character.valueOf(retVal));  return retVal; } public double readDouble() throws IOException, EOFException { long doubleBits; IOException traceRet2; if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "readDouble()");  double retVal = 0.0D; if (this.dataBuffer.remaining() < 8) { EOFException traceRet1 = new EOFException(MQException.getNLSMsg("MQEOFEXCEPTION", "MQMessage.readDouble()")); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "readDouble()", traceRet1, 1);  throw traceRet1; }  switch (this.encoding & 0xF00) { case 0: case 256: this.dataBuffer.order(ByteOrder.BIG_ENDIAN); retVal = this.dataBuffer.getDouble(); break;case 512: this.dataBuffer.order(ByteOrder.LITTLE_ENDIAN); retVal = this.dataBuffer.getDouble(); break;case 768: doubleBits = this.dataBuffer.getLong(); retVal = MQS390FloatSupport.longS390BitsToDouble(doubleBits); break;default: if (Trace.isOn) Trace.data(this, "readDouble()", "Invalid encoding : ", Integer.toString(this.encoding & 0xF00));  traceRet2 = new IOException(MQException.getNLSMsg("MQIOEXCEPTION", "MQMessage.readDouble()")); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "readDouble()", traceRet2, 2);  throw traceRet2; }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "readDouble()", Double.valueOf(retVal));  return retVal; } public float readFloat() throws IOException, EOFException { int floatBits; IOException traceRet2; if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "readFloat()");  float retVal = 0.0F; if (this.dataBuffer.remaining() < 4) { EOFException traceRet1 = new EOFException(MQException.getNLSMsg("MQEOFEXCEPTION", "MQMessage.readFloat()")); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "readFloat()", traceRet1, 1);  throw traceRet1; }  switch (this.encoding & 0xF00) { case 0: case 256: this.dataBuffer.order(ByteOrder.BIG_ENDIAN); retVal = this.dataBuffer.getFloat(); break;case 512: this.dataBuffer.order(ByteOrder.LITTLE_ENDIAN); retVal = this.dataBuffer.getFloat(); break;case 768: floatBits = this.dataBuffer.getInt(); retVal = MQS390FloatSupport.intS390BitsToFloat(floatBits); break;default: if (Trace.isOn) Trace.data(this, "readFloat()", "Invalid encoding : ", Integer.toString(this.encoding & 0xF00));  traceRet2 = new IOException(MQException.getNLSMsg("MQIOEXCEPTION", "MQMessage.readFloat()")); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "readFloat()", traceRet2, 2);  throw traceRet2; }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "readFloat()", Float.valueOf(retVal));  return retVal; } public void readFully(byte[] b) throws IOException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "readFully(byte [ ])", new Object[] { b });  if (this.dataBuffer.remaining() < b.length) { EOFException traceRet1 = new EOFException(MQException.getNLSMsg("MQEOFEXCEPTION", "MQMessage.readFully()")); if (Trace.isOn) { Trace.data(this, "com.ibm.mq.MQMessage", "Not enough space in data buffer! Will throw the following exception: ", traceRet1.getMessage()); Trace.throwing(this, "com.ibm.mq.MQMessage", "readFully(byte [ ])", traceRet1); }  throw traceRet1; }  this.dataBuffer.get(b); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "readFully(byte [ ])");  } public void readFully(byte[] b, int off, int len) throws IOException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "readFully(byte [ ],int,int)", new Object[] { b, Integer.valueOf(off), Integer.valueOf(len) });  if (this.dataBuffer.remaining() < len) { EOFException traceRet1 = new EOFException(MQException.getNLSMsg("MQEOFEXCEPTION", "MQMessage.readFully()")); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "readFully(byte [ ],int,int)", traceRet1);  throw traceRet1; }  this.dataBuffer.get(b, off, len); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "readFully(byte [ ],int,int)");  } public String readStringOfByteLength(int numberOfBytes) throws IOException, EOFException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "readStringOfByteLength(int)", new Object[] { Integer.valueOf(numberOfBytes) });  if (this.dataBuffer.remaining() < numberOfBytes) { EOFException traceRet1 = new EOFException(MQException.getNLSMsg("MQEOFEXCEPTION", "MQMessage.readString()")); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "readStringOfByteLength(int)", traceRet1, 1);  throw traceRet1; }  byte[] b = new byte[numberOfBytes]; String result = null; int originalDataOffset = this.dataBuffer.position(); try { this.dataBuffer.get(b, 0, numberOfBytes); result = getCodepage(this.characterSet).bytesToString(b); } catch (IOException e) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.mq.MQMessage", "readStringOfByteLength(int)", e, 1);  this.dataBuffer.position(originalDataOffset); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "readStringOfByteLength(int)", e, 2);  throw e; } catch (UnsupportedCharsetException e) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.mq.MQMessage", "readStringOfByteLength(int)", e, 2);  UnsupportedEncodingException traceRet1 = new UnsupportedEncodingException(Integer.toString(this.characterSet)); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "readStringOfByteLength(int)", traceRet1, 3);  throw traceRet1; }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "readStringOfByteLength(int)", result);  return result; } public String readStringOfCharLength(int numberOfChars) throws IOException, EOFException { String retVal; if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "readStringOfCharLength(int)", new Object[] { Integer.valueOf(numberOfChars) });  String fid = "readStringOfCharLength(int)"; if (Trace.isOn) Trace.data(this, "readStringOfCharLength(int)", "numberOfChars = ", Integer.toString(numberOfChars));  int originalDataOffset = getDataOffset(); try { char[] c; int i; switch (this.characterSet) { case 1200: c = new char[numberOfChars]; for (i = 0; i < numberOfChars; i++) c[i] = readChar();  retVal = new String(c); break;default: retVal = readConvertedString(numberOfChars); break; }  } catch (EOFException e) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.mq.MQMessage", "readStringOfCharLength(int)", e);  setDataOffset(originalDataOffset); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "readStringOfCharLength(int)", e);  throw e; }  if (Trace.isOn) Trace.data(this, "readStringOfCharLength(int)", "Returning ", retVal);  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "readStringOfCharLength(int)", retVal);  return retVal; } public int readInt() throws IOException { IOException traceRet2; if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "readInt()");  int retVal = 0; if (this.dataBuffer.remaining() < 4) { EOFException traceRet1 = new EOFException(MQException.getNLSMsg("MQEOFEXCEPTION", "MQMessage.readInt()")); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "readInt()", traceRet1, 1);  throw traceRet1; }  switch (this.encoding & 0xF) { case 0: case 1: this.dataBuffer.order(ByteOrder.BIG_ENDIAN); break;case 2: this.dataBuffer.order(ByteOrder.LITTLE_ENDIAN); break;default: if (Trace.isOn) Trace.data(this, "readInt()", "Invalid encoding : ", Integer.toString(this.encoding & 0xF00));  traceRet2 = new IOException(MQException.getNLSMsg("MQIOEXCEPTION", "MQMessage.readInt()")); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "readInt()", traceRet2, 2);  throw traceRet2; }  retVal = this.dataBuffer.getInt(); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "readInt()", Integer.valueOf(retVal));  return retVal; } public int readInt4() throws IOException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "readInt4()");  int retVal = readInt(); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "readInt4()", Integer.valueOf(retVal));  return retVal; } public String readLine() throws IOException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "readLine()");  try { String traceRet1; switch (this.characterSet) { case 1200: traceRet1 = readUnicodeLine(); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "readLine()", traceRet1, 1);  str1 = traceRet1; return str1; }  String traceRet3 = readConvertedLine(); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "readLine()", traceRet3, 2);  String str1 = traceRet3; return str1; } finally { if (Trace.isOn) Trace.finallyBlock(this, "com.ibm.mq.MQMessage", "readLine()");  }  } private String readUnicodeLine() throws IOException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "readUnicodeLine()");  StringBuffer retVal = new StringBuffer(getDataLength() / 2); try { boolean endOfLine = false; while (!endOfLine) { char thisChar = readChar(); switch (thisChar) { case '\n': endOfLine = true; continue;case '\r': endOfLine = true; if (getDataLength() >= 2) { int anotherChar = readChar(); if (anotherChar != 10) seek(getDataOffset() - 2);  }  continue; }  retVal.append(thisChar); }  } catch (EOFException ex) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.mq.MQMessage", "readUnicodeLine()", ex);  }  String traceRet1 = retVal.toString(); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "readUnicodeLine()", traceRet1);  return traceRet1; } public long readLong() throws IOException { IOException traceRet2; if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "readLong()");  if (this.dataBuffer.remaining() < 8) { EOFException traceRet1 = new EOFException(MQException.getNLSMsg("MQEOFEXCEPTION", "MQMessage.readLong()")); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "readLong()", traceRet1, 1);  throw traceRet1; }  switch (this.encoding & 0xF) { case 0: case 1: this.dataBuffer.order(ByteOrder.BIG_ENDIAN); break;case 2: this.dataBuffer.order(ByteOrder.LITTLE_ENDIAN); break;default: if (Trace.isOn) Trace.data(this, "readLong()", "Invalid encoding : ", Integer.toString(this.encoding & 0xF));  traceRet2 = new IOException(MQException.getNLSMsg("MQIOEXCEPTION", "MQMessage.readLong()")); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "readLong()", traceRet2, 2);  throw traceRet2; }  long retVal = this.dataBuffer.getLong(); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "readLong()", Long.valueOf(retVal));  return retVal; } public long readInt8() throws IOException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "readInt8()");  long retVal = readLong(); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "readInt8()", Long.valueOf(retVal));  return retVal; } public short readShort() throws IOException { IOException traceRet2; if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "readShort()");  if (this.dataBuffer.remaining() < 2) { EOFException traceRet1 = new EOFException(MQException.getNLSMsg("MQEOFEXCEPTION", "MQMessage.readShort()")); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "readShort()", traceRet1, 1);  throw traceRet1; }  switch (this.encoding & 0xF) { case 0: case 1: this.dataBuffer.order(ByteOrder.BIG_ENDIAN); break;case 2: this.dataBuffer.order(ByteOrder.LITTLE_ENDIAN); break;default: if (Trace.isOn) Trace.data(this, "readShort()", "Invalid encoding : ", Integer.toString(this.encoding & 0xF));  traceRet2 = new IOException(MQException.getNLSMsg("MQIOEXCEPTION", "MQMessage.readShort()")); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "readShort()", traceRet2, 2);  throw traceRet2; }  short retVal = this.dataBuffer.getShort(); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "readShort()", Short.valueOf(retVal));  return retVal; } public short readInt2() throws IOException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "readInt2()");  short retVal = readShort(); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "readInt2()", Short.valueOf(retVal));  return retVal; } public String readUTF() throws IOException { String retVal; if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "readUTF()");  if (this.dataBuffer.remaining() < 2) { EOFException traceRet1 = new EOFException(MQException.getNLSMsg("MQEOFEXCEPTION", "MQMessage.readUTF()")); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "readUTF()", traceRet1, 1);  throw traceRet1; }  this.dataBuffer.order(ByteOrder.BIG_ENDIAN); char utfLength = this.dataBuffer.getChar(); if (utfLength > this.dataBuffer.remaining()) { EOFException traceRet2 = new EOFException(MQException.getNLSMsg("MQEOFEXCEPTION", "MQMessage.readUTF()")); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "readUTF()", traceRet2, 2);  throw traceRet2; }  byte[] utfBytes = new byte[utfLength]; this.dataBuffer.get(utfBytes); try { retVal = new String(utfBytes, "UTF-8J"); } catch (UnsupportedCharsetException e) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.mq.MQMessage", "readUTF()", e, 1);  this.dataBuffer.position(this.dataBuffer.position() - utfLength - 2); if (this.proxyInputStream == null) this.proxyInputStream = new MQMessageInputStream(this);  DataInputStream dataStream = new DataInputStream(this.proxyInputStream); retVal = dataStream.readUTF(); } catch (UnsupportedEncodingException e) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.mq.MQMessage", "readUTF()", e, 2);  this.dataBuffer.position(this.dataBuffer.position() - utfLength - 2); if (this.proxyInputStream == null) this.proxyInputStream = new MQMessageInputStream(this);  DataInputStream dataStream = new DataInputStream(this.proxyInputStream); retVal = dataStream.readUTF(); }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "readUTF()", retVal);  return retVal; } public int readUnsignedByte() throws IOException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "readUnsignedByte()");  if (this.dataBuffer.remaining() < 1) { EOFException traceRet1 = new EOFException(MQException.getNLSMsg("MQEOFEXCEPTION", "MQMessage.readUnsignedByte()")); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "readUnsignedByte()", traceRet1);  throw traceRet1; }  int retVal = this.dataBuffer.get() & 0xFF; if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "readUnsignedByte()", Integer.valueOf(retVal));  return retVal; } public int readUnsignedShort() throws IOException { IOException traceRet2; if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "readUnsignedShort()");  if (this.dataBuffer.remaining() < 2) { EOFException traceRet1 = new EOFException(MQException.getNLSMsg("MQEOFEXCEPTION", "MQMessage.readUnsignedShort()")); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "readUnsignedShort()", traceRet1, 1);  throw traceRet1; }  switch (this.encoding & 0xF) { case 0: case 1: this.dataBuffer.order(ByteOrder.BIG_ENDIAN); break;case 2: this.dataBuffer.order(ByteOrder.LITTLE_ENDIAN); break;default: if (Trace.isOn) Trace.data(this, "readUnsignedShort()", "Invalid encoding : ", Integer.toString(this.encoding & 0xF));  traceRet2 = new IOException(MQException.getNLSMsg("MQIOEXCEPTION", "MQMessage.readUnsignedShort()")); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "readUnsignedShort()", traceRet2, 2);  throw traceRet2; }  int retVal = this.dataBuffer.getChar(); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "readUnsignedShort()", Integer.valueOf(retVal));  return retVal; } public int readUInt2() throws IOException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "readUInt2()");  int retVal = readUnsignedShort(); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "readUInt2()", Integer.valueOf(retVal));  return retVal; } @Deprecated public String readString(int length) throws IOException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "readString(int)", new Object[] { Integer.valueOf(length) });  if (Trace.isOn) Trace.data(this, "readString(int)", "length = ", Integer.toString(length));  String retVal = readStringOfCharLength(length); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "readString(int)", retVal);  return retVal; } public short readDecimal2() throws IOException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "readDecimal2()");  if (this.dataBuffer.remaining() < 2) { EOFException traceRet1 = new EOFException(MQException.getNLSMsg("MQEOFEXCEPTION", "MQMessage.readDecimal2()")); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "readDecimal2()", traceRet1);  throw traceRet1; }  byte[] packedDecimalBytes = new byte[2]; this.dataBuffer.get(packedDecimalBytes); if ((this.encoding & 0xF0) == 32) { byte reverseByte = packedDecimalBytes[0]; packedDecimalBytes[0] = packedDecimalBytes[1]; packedDecimalBytes[1] = reverseByte; }  long packedDecimal = MQS390PackedDecimalSupport.convertToBinary(packedDecimalBytes); short traceRet2 = (short)(int)packedDecimal; if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "readDecimal2()", Short.valueOf(traceRet2));  return traceRet2; } public int readDecimal4() throws IOException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "readDecimal4()");  if (this.dataBuffer.remaining() < 4) { EOFException traceRet1 = new EOFException(MQException.getNLSMsg("MQEOFEXCEPTION", "MQMessage.readDecimal4()")); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "readDecimal4()", traceRet1);  throw traceRet1; }  byte[] packedDecimalBytes = new byte[4]; this.dataBuffer.get(packedDecimalBytes); if ((this.encoding & 0xF0) == 32) { byte reverseByte = packedDecimalBytes[0]; packedDecimalBytes[0] = packedDecimalBytes[3]; packedDecimalBytes[3] = reverseByte; reverseByte = packedDecimalBytes[1]; packedDecimalBytes[1] = packedDecimalBytes[2]; packedDecimalBytes[2] = reverseByte; }  long packedDecimal = MQS390PackedDecimalSupport.convertToBinary(packedDecimalBytes); int traceRet2 = (int)packedDecimal; if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "readDecimal4()", Integer.valueOf(traceRet2));  return traceRet2; } public long readDecimal8() throws IOException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "readDecimal8()");  if (this.dataBuffer.remaining() < 8) { EOFException traceRet1 = new EOFException(MQException.getNLSMsg("MQEOFEXCEPTION", "MQMessage.readDecimal8()")); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "readDecimal8()", traceRet1);  throw traceRet1; }  byte[] packedDecimalBytes = new byte[8]; this.dataBuffer.get(packedDecimalBytes); if ((this.encoding & 0xF0) == 32) { byte reverseByte = packedDecimalBytes[0]; packedDecimalBytes[0] = packedDecimalBytes[7]; packedDecimalBytes[7] = reverseByte; reverseByte = packedDecimalBytes[1]; packedDecimalBytes[1] = packedDecimalBytes[6]; packedDecimalBytes[6] = reverseByte; reverseByte = packedDecimalBytes[2]; packedDecimalBytes[2] = packedDecimalBytes[5]; packedDecimalBytes[5] = reverseByte; reverseByte = packedDecimalBytes[3]; packedDecimalBytes[3] = packedDecimalBytes[4]; packedDecimalBytes[4] = reverseByte; }  long packedDecimal = MQS390PackedDecimalSupport.convertToBinary(packedDecimalBytes); long traceRet2 = packedDecimal; if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "readDecimal8()", Long.valueOf(traceRet2));  return traceRet2; } public Object readObject() throws ClassNotFoundException, InvalidClassException, StreamCorruptedException, OptionalDataException, IOException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "readObject()");  Object retVal = null; if (this.proxyInputStream == null) this.proxyInputStream = new MQMessageInputStream(this);  ClassLoader classloader = Thread.currentThread().getContextClassLoader(); MQObjectInputStream objectInputStream = new MQObjectInputStream(this.proxyInputStream, classloader); retVal = objectInputStream.readObject(); objectInputStream.close(); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "readObject()", retVal);  return retVal; } public int skipBytes(int n) throws IOException, EOFException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "skipBytes(int)", new Object[] { Integer.valueOf(n) });  if (n == 0) { if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "skipBytes(int)", Integer.valueOf(0), 1);  return 0; }  int requestedPosition = this.dataBuffer.position() + n; int actualPosition = (requestedPosition < this.dataBuffer.limit()) ? requestedPosition : this.dataBuffer.limit(); if (actualPosition >= 0) this.dataBuffer.position(actualPosition);  if (actualPosition < 0 || actualPosition != requestedPosition) { EOFException traceRet1 = new EOFException(MQException.getNLSMsg("MQEOFEXCEPTION", "MQMessage.skipBytes()")); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "skipBytes(int)", traceRet1);  throw traceRet1; }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "skipBytes(int)", Integer.valueOf(n), 2);  return n; } public void write(int b) throws IOException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "write(int)", new Object[] { Integer.valueOf(b) });  ensureWriteSpace(1); this.dataBuffer.put((byte)(b & 0xFF)); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "write(int)");  } public void write(byte[] b) throws IOException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "write(byte [ ])", new Object[] { b });  ensureWriteSpace(b.length); this.dataBuffer.put(b); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "write(byte [ ])");  } public void write(byte[] b, int off, int len) throws IOException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "write(byte [ ],int,int)", new Object[] { b, Integer.valueOf(off), Integer.valueOf(len) });  ensureWriteSpace(len); this.dataBuffer.put(b, off, len); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "write(byte [ ],int,int)");  } public void writeBoolean(boolean v) throws IOException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "writeBoolean(boolean)", new Object[] { Boolean.valueOf(v) });  ensureWriteSpace(1); this.dataBuffer.put(v ? 1 : 0); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "writeBoolean(boolean)");  } public void writeByte(int v) throws IOException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "writeByte(int)", new Object[] { Integer.valueOf(v) });  ensureWriteSpace(1); this.dataBuffer.put((byte)v); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "writeByte(int)");  } @Deprecated public void writeBytes(String s) throws IOException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "writeBytes(String)", new Object[] { s });  char[] stringChars = s.toCharArray(); ensureWriteSpace(stringChars.length); for (int i = 0; i < stringChars.length; i++) this.dataBuffer.put((byte)(stringChars[i] & 0xFF));  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "writeBytes(String)");  } public void writeChar(int v) throws IOException { IOException traceRet1; if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "writeChar(int)", new Object[] { Integer.valueOf(v) });  ensureWriteSpace(2); switch (this.encoding & 0xF) { case 0: case 1: this.dataBuffer.order(ByteOrder.BIG_ENDIAN); break;case 2: this.dataBuffer.order(ByteOrder.LITTLE_ENDIAN); break;default: if (Trace.isOn) Trace.data(this, "writeChar(int)", "Unsupported encoding: ", Integer.toString(this.encoding & 0xF));  traceRet1 = new IOException(MQException.getNLSMsg("MQIOEXCEPTION", "MQMessage.writeChar()")); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "writeChar(int)", traceRet1);  throw traceRet1; }  this.dataBuffer.putChar((char)v); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "writeChar(int)");  } public void writeChars(String s) throws IOException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "writeChars(String)", new Object[] { s });  char[] stringChars = s.toCharArray(); ensureWriteSpace(stringChars.length * 2); this.dataBuffer.order(ByteOrder.BIG_ENDIAN); for (int i = 0; i < stringChars.length; i++) this.dataBuffer.putChar(stringChars[i]);  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "writeChars(String)");  } public void writeDouble(double v) throws IOException { long s390Double; IOException traceRet1; if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "writeDouble(double)", new Object[] { Double.valueOf(v) });  ensureWriteSpace(8); switch (this.encoding & 0xF00) { case 0: case 256: this.dataBuffer.order(ByteOrder.BIG_ENDIAN); this.dataBuffer.putDouble(v); break;case 512: this.dataBuffer.order(ByteOrder.LITTLE_ENDIAN); this.dataBuffer.putDouble(v); break;case 768: s390Double = MQS390FloatSupport.doubleToS390LongBits(v); this.dataBuffer.order(ByteOrder.BIG_ENDIAN); this.dataBuffer.putLong(s390Double); break;default: if (Trace.isOn) Trace.data(this, "writeDouble(double)", "Unsupported encoding: ", Integer.toString(this.encoding & 0xF00));  traceRet1 = new IOException(MQException.getNLSMsg("MQIOEXCEPTION", "MQMessage.writeDouble()")); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "writeDouble(double)", traceRet1);  throw traceRet1; }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "writeDouble(double)");  } public void writeFloat(float v) throws IOException { int s390Float; IOException traceRet1; if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "writeFloat(float)", new Object[] { Float.valueOf(v) });  ensureWriteSpace(4); switch (this.encoding & 0xF00) { case 0: case 256: this.dataBuffer.order(ByteOrder.BIG_ENDIAN); this.dataBuffer.putFloat(v); break;case 512: this.dataBuffer.order(ByteOrder.LITTLE_ENDIAN); this.dataBuffer.putFloat(v); break;case 768: s390Float = MQS390FloatSupport.floatToS390IntBits(v); this.dataBuffer.order(ByteOrder.BIG_ENDIAN); this.dataBuffer.putInt(s390Float); break;default: if (Trace.isOn) Trace.data(this, "writeFloat(float)", "Unsupported encoding: ", Integer.toString(this.encoding & 0xF00));  traceRet1 = new IOException(MQException.getNLSMsg("MQIOEXCEPTION", "MQMessage.writeFloat()")); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "writeFloat(float)", traceRet1);  throw traceRet1; }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "writeFloat(float)");  }
/*      */   public void writeInt(int v) throws IOException { IOException traceRet1; if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "writeInt(int)", new Object[] { Integer.valueOf(v) });  ensureWriteSpace(4); switch (this.encoding & 0xF) { case 0: case 1: this.dataBuffer.order(ByteOrder.BIG_ENDIAN); break;case 2: this.dataBuffer.order(ByteOrder.LITTLE_ENDIAN); break;default: if (Trace.isOn) Trace.data(this, "writeInt(int)", "Unsupported encoding: ", Integer.toString(this.encoding & 0xF));  traceRet1 = new IOException(MQException.getNLSMsg("MQIOEXCEPTION", "MQMessage.writeInt()")); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "writeInt(int)", traceRet1);  throw traceRet1; }  this.dataBuffer.putInt(v); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "writeInt(int)");  }
/*      */   public void writeInt4(int v) throws IOException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "writeInt4(int)", new Object[] { Integer.valueOf(v) });  writeInt(v); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "writeInt4(int)");  }
/* 4881 */   private void setJMSExpiration(Object value) throws MQException { if (Trace.isOn) {
/* 4882 */       Trace.data(this, "com.ibm.mq.MQMessage", "setJMSExpiration(Object)", "setter", value);
/*      */     }
/*      */     
/*      */     try { long ttl;
/* 4886 */       if (value instanceof Long) {
/* 4887 */         ttl = ((Long)value).longValue();
/*      */       } else {
/* 4889 */         MQException traceRet1 = new MQException(2, 2473, this);
/*      */         
/* 4891 */         if (Trace.isOn) {
/* 4892 */           Trace.throwing(this, "com.ibm.mq.MQMessage", "setJMSExpiration(Object)", traceRet1, 1);
/*      */         }
/* 4894 */         throw traceRet1;
/*      */       } 
/* 4896 */       if (ttl == 0L)
/*      */       {
/* 4898 */         setMQMDProperty("expiry", UNLIMITED_EXPIRY);
/*      */       }
/*      */       else
/*      */       {
/* 4902 */         long timeToLive = ttl - System.currentTimeMillis();
/* 4903 */         if (timeToLive < 0L || timeToLive > 214748364700L) {
/* 4904 */           setMQMDProperty("expiry", UNLIMITED_EXPIRY);
/*      */         } else {
/* 4906 */           setMQMDProperty("expiry", Long.valueOf((int)((timeToLive + 100L) / 100L)));
/*      */         } 
/* 4908 */         Vector<Object> valueList = new Vector();
/* 4909 */         valueList.addElement(value);
/* 4910 */         this.properties.put("jms.Exp", valueList);
/*      */       }
/*      */        }
/* 4913 */     catch (Exception ex)
/* 4914 */     { Exception exception1; if (Trace.isOn) {
/* 4915 */         Trace.catchBlock(this, "com.ibm.mq.MQMessage", "setJMSExpiration(Object)", exception1);
/*      */       }
/* 4917 */       MQException traceRet1 = new MQException(2, 2473, this);
/* 4918 */       if (Trace.isOn) {
/* 4919 */         Trace.throwing(this, "com.ibm.mq.MQMessage", "setJMSExpiration(Object)", traceRet1, 2);
/*      */       }
/* 4921 */       throw traceRet1; }  }
/*      */   public void writeLong(long v) throws IOException { IOException traceRet1; if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "writeLong(long)", new Object[] { Long.valueOf(v) });  ensureWriteSpace(8); switch (this.encoding & 0xF) { case 0: case 1: this.dataBuffer.order(ByteOrder.BIG_ENDIAN); break;case 2: this.dataBuffer.order(ByteOrder.LITTLE_ENDIAN); break;default: if (Trace.isOn) Trace.data(this, "writeLong(long)", "Unsupported encoding: ", Integer.toString(this.encoding & 0xF00));  traceRet1 = new IOException(MQException.getNLSMsg("MQIOEXCEPTION", "MQMessage.writeLong()")); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "writeLong(long)", traceRet1);  throw traceRet1; }  this.dataBuffer.putLong(v); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "writeLong(long)");  }
/*      */   public void writeInt8(long v) throws IOException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "writeInt8(long)", new Object[] { Long.valueOf(v) });  writeLong(v); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "writeInt8(long)");  }
/*      */   public void writeShort(int v) throws IOException { IOException traceRet1; if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "writeShort(int)", new Object[] { Integer.valueOf(v) });  ensureWriteSpace(2); switch (this.encoding & 0xF) { case 0: case 1: this.dataBuffer.order(ByteOrder.BIG_ENDIAN); break;case 2: this.dataBuffer.order(ByteOrder.LITTLE_ENDIAN); break;default: if (Trace.isOn) Trace.data(this, "writeShort(int)", "Unsupported encoding: ", Integer.toString(this.encoding & 0xF00));  traceRet1 = new IOException(MQException.getNLSMsg("MQIOEXCEPTION", "MQMessage.writeShort()")); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "writeShort(int)", traceRet1);  throw traceRet1; }  this.dataBuffer.putShort((short)v); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "writeShort(int)");  }
/*      */   public void writeInt2(int v) throws IOException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "writeInt2(int)", new Object[] { Integer.valueOf(v) });  writeShort(v); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "writeInt2(int)");  }
/*      */   public void writeDecimal2(short v) throws IOException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "writeDecimal2(short)", new Object[] { Short.valueOf(v) });  ensureWriteSpace(2); byte[] packedDecimalBytes = MQS390PackedDecimalSupport.convertToPackedDecimal(v); if ((this.encoding & 0xF0) == 32) { byte reverseByte = packedDecimalBytes[0]; packedDecimalBytes[0] = packedDecimalBytes[1]; packedDecimalBytes[1] = reverseByte; }  this.dataBuffer.put(packedDecimalBytes); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "writeDecimal2(short)");  }
/*      */   public void writeDecimal4(int v) throws IOException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "writeDecimal4(int)", new Object[] { Integer.valueOf(v) });  ensureWriteSpace(4); byte[] packedDecimalBytes = MQS390PackedDecimalSupport.convertToPackedDecimal(v); if ((this.encoding & 0xF0) == 32) { byte reverseByte = packedDecimalBytes[0]; packedDecimalBytes[0] = packedDecimalBytes[3]; packedDecimalBytes[3] = reverseByte; reverseByte = packedDecimalBytes[1]; packedDecimalBytes[1] = packedDecimalBytes[2]; packedDecimalBytes[2] = reverseByte; }  this.dataBuffer.put(packedDecimalBytes); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "writeDecimal4(int)");  }
/* 4928 */   public void writeDecimal8(long v) throws IOException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "writeDecimal8(long)", new Object[] { Long.valueOf(v) });  ensureWriteSpace(8); byte[] packedDecimalBytes = MQS390PackedDecimalSupport.convertToPackedDecimal(v); if ((this.encoding & 0xF0) == 32) { byte reverseByte = packedDecimalBytes[0]; packedDecimalBytes[0] = packedDecimalBytes[7]; packedDecimalBytes[7] = reverseByte; reverseByte = packedDecimalBytes[1]; packedDecimalBytes[1] = packedDecimalBytes[6]; packedDecimalBytes[6] = reverseByte; reverseByte = packedDecimalBytes[2]; packedDecimalBytes[2] = packedDecimalBytes[5]; packedDecimalBytes[5] = reverseByte; reverseByte = packedDecimalBytes[3]; packedDecimalBytes[3] = packedDecimalBytes[4]; packedDecimalBytes[4] = reverseByte; }  this.dataBuffer.put(packedDecimalBytes); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "writeDecimal8(long)");  } public void writeUTF(String str) throws IOException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "writeUTF(String)", new Object[] { str });  try { byte[] utfBytes = str.getBytes("UTF-8J"); char utfLength = (char)utfBytes.length; ensureWriteSpace(utfLength + 2); this.dataBuffer.order(ByteOrder.BIG_ENDIAN); this.dataBuffer.putChar(utfLength); this.dataBuffer.put(utfBytes); } catch (UnsupportedCharsetException e) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.mq.MQMessage", "writeUTF(String)", e, 1);  if (this.proxyOutputStream == null) this.proxyOutputStream = new MQMessageOutputStream(this);  DataOutputStream dataStream = new DataOutputStream(this.proxyOutputStream); dataStream.writeUTF(str); } catch (UnsupportedEncodingException e) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.mq.MQMessage", "writeUTF(String)", e, 2);  if (this.proxyOutputStream == null) this.proxyOutputStream = new MQMessageOutputStream(this);  DataOutputStream dataStream = new DataOutputStream(this.proxyOutputStream); dataStream.writeUTF(str); }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "writeUTF(String)");  } public void writeString(String s) throws IOException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "writeString(String)", new Object[] { s });  switch (this.characterSet) { case 1200: writeChars(s); break;case 0: this.characterSet = MQSESSION.getDefaultCCSID();default: try { JmqiCodepage cp = getCodepage(this.characterSet); byte[] convertedBytes = cp.stringToBytes(s); ensureWriteSpace(convertedBytes.length); this.dataBuffer.put(convertedBytes); } catch (UnsupportedCharsetException e) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.mq.MQMessage", "writeString(String)", e);  UnsupportedEncodingException traceRet1 = new UnsupportedEncodingException(Integer.toString(this.characterSet)); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "writeString(String)", traceRet1);  throw traceRet1; }  break; }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "writeString(String)");  } public void writeObject(Object obj) throws IOException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "writeObject(Object)", new Object[] { obj });  if (this.proxyOutputStream == null) this.proxyOutputStream = new MQMessageOutputStream(this);  ObjectOutputStream objectOutputStream = new ObjectOutputStream(this.proxyOutputStream); objectOutputStream.writeObject(obj); objectOutputStream.close(); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "writeObject(Object)");  } protected final ByteBuffer getBuffer() { if (Trace.isOn) Trace.data(this, "com.ibm.mq.MQMessage", "getBuffer()", "getter", this.dataBuffer);  return this.dataBuffer; } protected final void setMessageData(ByteBuffer messageData, int messageLength, int totalMessageLength) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "setMessageData(ByteBuffer,int,int)", new Object[] { messageData, Integer.valueOf(messageLength), Integer.valueOf(totalMessageLength) });  this._totalMessageLength = totalMessageLength; ByteBuffer newBuffer = ByteBuffer.allocate(messageLength); int originalPosition = messageData.position(); int originalLimit = messageData.limit(); messageData.position(0); messageData.limit(messageLength); newBuffer.put(messageData); this.dataBuffer = newBuffer; this.dataBuffer.position(0); this.dataBuffer.limit(messageLength); messageData.limit(originalLimit); messageData.position(originalPosition); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "setMessageData(ByteBuffer,int,int)");  } protected int getBufferSizeHint() { if (Trace.isOn) Trace.data(this, "com.ibm.mq.MQMessage", "getBufferSizeHint()", "getter", Integer.valueOf(this._bufferSizeHint));  return this._bufferSizeHint; } protected void setTotalMessageLength(int tMsgLength) { if (Trace.isOn) Trace.data(this, "com.ibm.mq.MQMessage", "setTotalMessageLength(int)", "setter", Integer.valueOf(tMsgLength));  this._totalMessageLength = tMsgLength; } private String readConvertedLine() throws IOException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "readConvertedLine()");  CharBuffer charBuffer = CharBuffer.allocate(256); CharsetDecoder decoder = getCodepage(this.characterSet).getDecoder(); int charsRead = 0; int stringLength = 0; boolean readLine = false; boolean carriageReturnFound = false; int crReadPos = -1; while (!readLine) { if (charsRead >= charBuffer.capacity()) { CharBuffer newCharBuffer = CharBuffer.allocate(charBuffer.capacity() * 2); charBuffer.position(0); newCharBuffer.put(charBuffer); charBuffer = newCharBuffer; }  if (this.dataBuffer.remaining() > 0) { charBuffer.limit(charsRead + 1); CoderResult result = decoder.decode(this.dataBuffer, charBuffer, false); if (result.isError()) { IOException traceRet1 = new IOException(MQException.getNLSMsg("MQDECODEERROR", "MQMessag2.readConvertedLine():" + result.toString())); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "readConvertedLine()", traceRet1);  throw traceRet1; }  char aChar = charBuffer.get(charsRead); switch (aChar) { case '\n': readLine = true; break;case '\r': carriageReturnFound = true; crReadPos = this.dataBuffer.position(); break;default: if (carriageReturnFound) { readLine = true; this.dataBuffer.position(crReadPos); break; }  stringLength++; break; }  charsRead++; continue; }  readLine = true; }  charBuffer.position(0); String traceRet2 = charBuffer.subSequence(0, stringLength).toString(); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "readConvertedLine()", traceRet2);  return traceRet2; } private String readConvertedString(int length) throws IOException, EOFException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "readConvertedString(int)", new Object[] { Integer.valueOf(length) });  CharBuffer charBuffer = CharBuffer.allocate(length); CharsetDecoder decoder = getCodepage(this.characterSet).getDecoder(); int charsRead = 0; while (charsRead < length) { if (this.dataBuffer.remaining() > 0) { charBuffer.limit(charsRead + 1); CoderResult result = decoder.decode(this.dataBuffer, charBuffer, false); if (result.isError()) { IOException traceRet1 = new IOException(MQException.getNLSMsg("MQDECODEERROR", "MQMessag2.readConvertedString():" + result.toString())); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "readConvertedString(int)", traceRet1, 1);  throw traceRet1; }  charsRead++; continue; }  EOFException traceRet2 = new EOFException(MQException.getNLSMsg("MQEOFEXCEPTION", "MQMessag2.readConvertedString()")); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "readConvertedString(int)", traceRet2, 2);  throw traceRet2; }  charBuffer.position(0); String traceRet3 = charBuffer.toString(); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "readConvertedString(int)", traceRet3);  return traceRet3; } protected JmqiCodepage getCodepage(int charSetP) { JmqiCodepage jmqiCodepage; if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "getCodepage(int)", new Object[] { Integer.valueOf(charSetP) });  int charSet = (charSetP == 0) ? MQSESSION.getDefaultCCSID() : charSetP; try { jmqiCodepage = JmqiCodepage.getJmqiCodepage(this.env, charSet, this.unmappableAction, this.unMappableReplacement, this.encoding); } catch (UnsupportedEncodingException e) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.mq.MQMessage", "getCodepage(int)", e);  UnsupportedCharsetException traceRet1 = new UnsupportedCharsetException(Integer.toString(charSet)); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "getCodepage(int)", traceRet1);  throw traceRet1; }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "getCodepage(int)", jmqiCodepage);  return jmqiCodepage; } public int getPropertyValidation() { if (Trace.isOn) Trace.data(this, "com.ibm.mq.MQMessage", "getPropertyValidation()", "getter", Integer.valueOf(this.propertyValidation));  return this.propertyValidation; } public void setPropertyValidation(int propertyValidation) { if (Trace.isOn) Trace.data(this, "com.ibm.mq.MQMessage", "setPropertyValidation(int)", "setter", Integer.valueOf(propertyValidation));  this.propertyValidation = propertyValidation; } protected void performProcessingBeforePut(int ccsid) throws MQException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "performProcessingBeforePut(int)", new Object[] { Integer.valueOf(ccsid) });  int numberOfProperties = this.properties.size(); if (numberOfProperties > 0) { this.formatBeforePut = this.format; this.characterSetBeforePut = this.characterSet; this.encodingBeforePut = this.encoding; this.dataBufferBeforePut = copyDataBuffer(this.dataBuffer); writePropertiesRfh2(ccsid); }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "performProcessingBeforePut(int)");  } private ByteBuffer copyDataBuffer(ByteBuffer oldBuffer) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "copyDataBuffer(ByteBuffer)", new Object[] { oldBuffer });  int dataBufferPosition = oldBuffer.position(); int dataBufferLimit = oldBuffer.limit(); int dataBufferCapacity = oldBuffer.capacity(); ByteBuffer newBuffer = ByteBuffer.allocate(dataBufferCapacity); newBuffer.limit(dataBufferLimit); oldBuffer.position(0); newBuffer.put(oldBuffer); newBuffer.position(dataBufferPosition); oldBuffer.position(dataBufferPosition); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "copyDataBuffer(ByteBuffer)", newBuffer);  return newBuffer; } protected void performProcessingAfterPut() throws MQException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "performProcessingAfterPut()");  int numberOfProperties = this.properties.size(); if (numberOfProperties > 0) { this.format = this.formatBeforePut; this.characterSet = this.characterSetBeforePut; this.encoding = this.encodingBeforePut; this.dataBuffer = this.dataBufferBeforePut; }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "performProcessingAfterPut()");  } protected void performProcessingBeforeGet() throws MQException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "performProcessingBeforeGet()");  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "performProcessingBeforeGet()");  } protected void performProcessingAfterGet() throws MQException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "performProcessingAfterGet()");  this.properties.clear(); this.propertyDescTable.clear(); readPropertiesRfh2(); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "performProcessingAfterGet()");  } protected void setRemoveRfh2AfterGet(boolean value) { if (Trace.isOn) Trace.data(this, "com.ibm.mq.MQMessage", "setRemoveRfh2AfterGet(boolean)", "setter", Boolean.valueOf(value));  this.removeRfh2PropertyHeaders = value; } private Object getJMSExpiration() { Object result; if (this.properties.containsKey("jms.Exp")) {
/* 4929 */       Vector<Object> valueList = this.properties.get("jms.Exp");
/* 4930 */       result = valueList.elementAt(0);
/*      */     }
/*      */     else {
/*      */       
/* 4934 */       result = Long.valueOf(0L);
/*      */     } 
/*      */     
/* 4937 */     if (Trace.isOn) {
/* 4938 */       Trace.data(this, "com.ibm.mq.MQMessage", "getJMSExpiration()", "getter", result);
/*      */     }
/* 4940 */     return result; }
/*      */   private void writePropertiesRfh2(int ccsid) throws MQException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "writePropertiesRfh2(int)", new Object[] { Integer.valueOf(ccsid) });  String fid = "writePropertiesRfh2(int)"; if (this.characterSet == 0) this.characterSet = ccsid;  try { seek(0); MQMessageHeaderList headers = null; try { headers = new MQMessageHeaderList(this, true); } catch (Exception e) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.mq.MQMessage", "writePropertiesRfh2(int)", e, 1);  MQException traceRet1 = new MQException(2, 2142, this, e); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "writePropertiesRfh2(int)", traceRet1, 1);  throw traceRet1; }  MQRFH2 rfh2 = createPropertiesRfh2(); String mqxqhType = (new MQXQH()).type(); int positionOfMQXQH = headers.indexOf(mqxqhType); if (positionOfMQXQH == -1) { if (Trace.isOn) { Trace.data(this, "writePropertiesRfh2(int)", "No MQXQH header detected on the message. Adding RFH2 as the first header in the header list", null); Trace.data(this, "writePropertiesRfh2(int)", "Copying CCSID=" + this.characterSet + " and Encoding=" + this.encoding + " from the MQMD to the RFH2 header", null); }  rfh2.setCodedCharSetId(this.characterSet); rfh2.setEncoding(this.encoding); if (Trace.isOn) Trace.data(this, "writePropertiesRfh2(int)", "Setting CCSID=1208 and Encoding=273 in the MQMD to allow header chaining to work correctly", null);  this.characterSet = 1208; this.encoding = 273; if (Trace.isOn) Trace.data(this, "writePropertiesRfh2(int)", "Modified RFH2", rfh2);  headers.add(0, rfh2); } else { if (Trace.isOn) Trace.data(this, "writePropertiesRfh2(int)", "MQXQH header detected at position " + positionOfMQXQH + " in the header list. Adding the RFH2 as the next header", null);  MQXQH mqxqhHeader = (MQXQH)headers.remove(positionOfMQXQH); MQMD1 mqxqhMqmd = mqxqhHeader.getMsgDesc(); int mqxqhMqmdCharacterSet = mqxqhMqmd.getCodedCharSetId(); int mqxqhMqmdEncoding = mqxqhMqmd.getEncoding(); if (Trace.isOn) Trace.data(this, "writePropertiesRfh2(int)", "Copying CCSID=" + mqxqhMqmdCharacterSet + " and Encoding=" + mqxqhMqmdEncoding + " from the MQMD in the MQXQH into the RFH2 header", null);  rfh2.setCodedCharSetId(mqxqhMqmdCharacterSet); rfh2.setEncoding(mqxqhMqmdEncoding); if (Trace.isOn) Trace.data(this, "writePropertiesRfh2(int)", "Setting CCSID=1208 and Encoding=273 in the MQMD in the MQXQH to allow header chaining to work correctly", null);  mqxqhMqmd.setCodedCharSetId(1208); mqxqhMqmd.setEncoding(273); mqxqhHeader.setMsgDesc(mqxqhMqmd); if (Trace.isOn) { Trace.data(this, "writePropertiesRfh2(int)", "Modified MQXQH", mqxqhHeader); Trace.data(this, "writePropertiesRfh2(int)", "Modified RFH2", rfh2); }  headers.add(positionOfMQXQH, mqxqhHeader); headers.add(positionOfMQXQH + 1, rfh2); }  this.format = headers.updateHeaderChaining(); seek(0); PropertyStore.register("com.ibm.mq.enableWriteMessageBodyUsingCCSIDInLastHeader", false); headers.write(this, true, PropertyStore.getBooleanPropertyObject("com.ibm.mq.enableWriteMessageBodyUsingCCSIDInLastHeader").booleanValue()); } catch (MQException e) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.mq.MQMessage", "writePropertiesRfh2(int)", e, 2);  if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "writePropertiesRfh2(int)", e, 2);  throw e; } catch (Exception e) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.mq.MQMessage", "writePropertiesRfh2(int)", e, 3);  HashMap<String, Object> ffstInfo = new HashMap<>(); ffstInfo.put("Exception", e.toString()); ffstInfo.put("Description", "An unexpected error occurred while creating the message properties RFH2 header"); Trace.ffst(this, "writePropertiesRfh2(int)", "1", ffstInfo, null); MQException traceRet2 = new MQException(2, 2195, this); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "writePropertiesRfh2(int)", traceRet2, 3);  throw traceRet2; } finally { if (Trace.isOn) Trace.finallyBlock(this, "com.ibm.mq.MQMessage", "writePropertiesRfh2(int)");  }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "writePropertiesRfh2(int)");  }
/*      */   private MQRFH2 createPropertiesRfh2() throws MQException, IOException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "createPropertiesRfh2()");  int count = 0; MQRFH2 rfh2 = new MQRFH2(); for (Map.Entry<String, Vector<Object>> property : this.properties.entrySet()) { String name = property.getKey(); Vector<Object> valueList = property.getValue(); Vector<MQPropertyDescriptor> pdList = null; if (this.propertyDescTable.containsKey(name)) pdList = this.propertyDescTable.get(name);  String folder = getPropertyFolder(name); name = name.substring(folder.length() + ".".length()); MQRFH2.Element rfh2Folder = rfh2.getFolder(folder, true); StringTokenizer tok = new StringTokenizer(name, "."); MQRFH2.Element currentFolder = rfh2Folder; while (tok.hasMoreTokens()) { String currentName = tok.nextToken(); if (tok.hasMoreTokens()) { currentFolder = currentFolder.getElement(currentName, true); continue; }  name = currentName; }  if (valueList != null) count = valueList.size();  if (count == 0) currentFolder.addElement(name, null, true);  for (int i = 0; i < count; i++) currentFolder.addElement(name, valueList.elementAt(i), true);  if (!PROPERTIES_FOLDER_NAMES.contains(folder)) rfh2Folder.setAttributeValue("content", "properties");  MQRFH2.Element element = rfh2Folder.getElement(name, false); if (pdList != null && pdList.size() > 0) { MQPropertyDescriptor pd = pdList.elementAt(0); if (pdList.size() > 1) { pdList.remove(pd); pdList.addElement(pd); }  if (pd != null) appendMQPDToRfhElement(element, name, folder, pd);  }  }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "createPropertiesRfh2()", rfh2);  return rfh2; }
/*      */   private void appendMQPDToRfhElement(MQRFH2.Element element, String name, String folder, MQPropertyDescriptor pd) throws MQException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "appendMQPDToRfhElement(Element,String,String,MQPropertyDescriptor)", new Object[] { element, name, folder, pd });  if (element != null) { if (folder.equals("mq")) { int value = pd.support; byte[] b = new byte[4]; for (int i = 0; i < 4; i++) { int shift = (b.length - 1 - i) * 8; b[i] = (byte)(value >>> shift & 0xFF); }  StringBuffer hex = new StringBuffer(); binToHex(b, 0, b.length, hex); element.setAttributeValue("support", hex.toString()); }  if (pd.context == 0) { element.setAttributeValue("context", "none"); } else if (pd.context == 1) { element.setAttributeValue("context", "user"); } else { MQException traceRet1 = new MQException(2, 2482, this); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "appendMQPDToRfhElement(Element,String,String,MQPropertyDescriptor)", traceRet1);  throw traceRet1; }  int copyOption = pd.copyOptions; String copyValue = null; if (copyOption - 22 != 0) if (copyOption == 0) { copyValue = "none"; } else if ((copyOption & 0x1) != 0) { copyValue = "all"; } else { if ((copyOption & 0x2) != 0) copyValue = "forward";  if ((copyOption & 0x4) != 0) if (copyValue == null) { copyValue = "publish"; } else { copyValue = copyValue + "," + "publish"; }   if ((copyOption & 0x10) != 0) if (copyValue == null) { copyValue = "report"; } else { copyValue = copyValue + "," + "report"; }   if ((copyOption & 0x8) != 0) if (copyValue == null) { copyValue = "reply"; } else { copyValue = copyValue + "," + "reply"; }   }   if (copyValue != null) element.setAttributeValue("copy", copyValue.trim());  }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "appendMQPDToRfhElement(Element,String,String,MQPropertyDescriptor)");  }
/* 4944 */   private String getPropertyFolder(String name) throws MQException { String folder; if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "getPropertyFolder(String)", new Object[] { name });  int firstFolderSeparatorIndex = name.indexOf("."); if (firstFolderSeparatorIndex >= 0) { folder = name.substring(0, firstFolderSeparatorIndex); } else { MQException traceRet1 = new MQException(2, 2195, this); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "getPropertyFolder(String)", traceRet1);  throw traceRet1; }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "getPropertyFolder(String)", folder);  return folder; } private void readPropertiesRfh2() throws MQException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "readPropertiesRfh2()");  String fid = "readPropertiesRfh2()"; try { if (this.format != null && (this.format.equals("MQHRF2  ") || (!this.format.equals("MQADMIN ") && !this.format.equals("MQPCF   ") && !this.format.equals("        ")))) { seek(0); MQMessageHeaderList headers = null; boolean headersModified = false; try { MQChainable lastHeader = null; headers = new MQMessageHeaderList(this, true); Iterator<?> headersIterator = headers.iterator(); while (headersIterator.hasNext()) { try { Header header = (Header)headersIterator.next(); if (header instanceof MQRFH2) { MQRFH2 headerAsMQRFH2 = (MQRFH2)header; boolean modified = parsePropertiesRfh2(headerAsMQRFH2); if (modified) { MQRFH2.Element[] folders = headerAsMQRFH2.getFolders(); boolean containsOnlyProperties = (folders == null || folders.length == 0); if (this.removeRfh2PropertyHeaders && containsOnlyProperties) { headersIterator.remove(); if (lastHeader != null) { lastHeader.nextCharacterSet(headerAsMQRFH2.nextCharacterSet()); lastHeader.nextEncoding(headerAsMQRFH2.nextEncoding()); lastHeader.nextFormat(headerAsMQRFH2.nextFormat()); } else { this.characterSet = headerAsMQRFH2.nextCharacterSet(); this.encoding = headerAsMQRFH2.nextEncoding(); headers.setFormat(headerAsMQRFH2.nextFormat()); }  } else { MQRFH2 mQRFH2 = headerAsMQRFH2; }  headersModified = true; }  continue; }  if (header instanceof MQChainable) lastHeader = (MQChainable)header;  } catch (Exception e) { if (Trace.isOn) { Trace.catchBlock(this, "com.ibm.mq.MQMessage", "readPropertiesRfh2()", e, 1); Trace.data(this, "readPropertiesRfh2()", "Failed to parse a message header while retrieving properties", e); }  if (e.getMessage().contains("MQHDR0016")) { MQException mqe = new MQException(2, 2421, e); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "readPropertiesRfh2()", mqe, 4);  throw mqe; }  }  }  } catch (Exception e) { if (Trace.isOn) { Trace.catchBlock(this, "com.ibm.mq.MQMessage", "readPropertiesRfh2()", e, 2); Trace.data(this, "readPropertiesRfh2()", "Failed to parse the message header list while retrieving properties", e); }  if (e instanceof MQException && ((MQException)e).getReason() == 2421) { if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "readPropertiesRfh2()", e, 5);  throw e; }  }  if (headersModified) { if (Trace.isOn) Trace.data(this, "readPropertiesRfh2()", "The message headers have been changed. Updating the header chaining within the message header list.");  this.format = headers.updateHeaderChaining(); seek(0); if (Trace.isOn) Trace.data(this, "readPropertiesRfh2()", "Rewriting the message headers and message body back into the message");  headers.write(this, true, true); }  seek(0); }  } catch (RuntimeException rte) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.mq.MQMessage", "readPropertiesRfh2()", rte, 3);  if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "readPropertiesRfh2()", rte, 1);  throw rte; } catch (UnsupportedEncodingException uee) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.mq.MQMessage", "readPropertiesRfh2()", uee, 5);  MQException mqe = new MQException(2, 2111, uee); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "readPropertiesRfh2()", mqe, 3);  throw mqe; } catch (Exception e) { MQException mqe; if (Trace.isOn) Trace.catchBlock(this, "com.ibm.mq.MQMessage", "readPropertiesRfh2()", e, 4);  if (e instanceof MQException && ((MQException)e).getReason() == 2421) { mqe = (MQException)e; } else { HashMap<String, Object> ffstInfo = new HashMap<>(); ffstInfo.put("Exception", e.toString()); ffstInfo.put("Description", "An unexpected error occurred while parsing the message properties RFH2 header"); Trace.ffst(this, "readPropertiesRfh2()", "1", ffstInfo, null); mqe = new MQException(2, 2195, e); }  if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "readPropertiesRfh2()", mqe, 2);  throw mqe; }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "readPropertiesRfh2()");  } private boolean parsePropertiesRfh2(MQRFH2 rfh2) throws IOException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "parsePropertiesRfh2(MQRFH2)", new Object[] { rfh2 });  boolean modified = false; MQRFH2.Element[] elements = rfh2.getFolders(); for (int i = 0; i < elements.length; i++) { try { if (elements[i].getName().equals("ibm_rfp")) { rfh2.setFolderContent(elements[i].getName(), null); modified = true; } else { boolean isPropertiesFolder = parsePropertiesRfh2Folder(elements[i]); if (isPropertiesFolder && this.removeRfh2PropertyHeaders) { rfh2.setFolderContent(elements[i].getName(), null); modified = true; }  }  } catch (Exception e) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.mq.MQMessage", "parsePropertiesRfh2(MQRFH2)", e);  Trace.data(this, "parsePropertiesRfh2(MQRFH2)", "Failed to parse an RFH2 folder while retrieving properties", e); }  }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "parsePropertiesRfh2(MQRFH2)", Boolean.valueOf(modified));  return modified; } private boolean parsePropertiesRfh2Folder(MQRFH2.Element element) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "parsePropertiesRfh2Folder(Element)", new Object[] { element });  String name = element.getName(); String contentAttribute = element.getAttributeValue("content"); boolean isPropertiesFolder = ((contentAttribute != null && contentAttribute.equals("properties")) || PROPERTIES_FOLDER_NAMES.contains(name) || name.startsWith("mq")); if (isPropertiesFolder) { MQRFH2.Element[] elements = element.getChildren(); for (int i = 0; i < elements.length; i++) { try { parsePropertiesRfh2Element(name, elements[i]); } catch (Exception e) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.mq.MQMessage", "parsePropertiesRfh2Folder(Element)", e);  Trace.data(this, "parsePropertiesRfh2Folder(Element)", "Failed to parse an RFH2 element while retrieving properties", e); }  }  }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "parsePropertiesRfh2Folder(Element)", Boolean.valueOf(isPropertiesFolder));  return isPropertiesFolder; } private void parsePropertiesRfh2Element(String folder, MQRFH2.Element element) throws MQException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "parsePropertiesRfh2Element(String,Element)", new Object[] { folder, element });  Vector<Object> valueList = null; Vector<MQPropertyDescriptor> pdList = null; String name = element.getName(); Object value = element.getValue(); MQRFH2.Element[] elements = element.getChildren(); MQPropertyDescriptor pd = getMQPDFromRfhElement(element); if (name != null && (elements == null || elements.length == 0)) { String fullName = folder + "." + name; if (!this.properties.containsKey(fullName)) { valueList = new Vector(); valueList.addElement(value); this.properties.put(fullName, valueList); } else { valueList = this.properties.get(fullName); if (valueList != null) { valueList.add(value); this.properties.put(fullName, valueList); }  }  if (!this.propertyDescTable.containsKey(fullName)) { pdList = new Vector<>(); pdList.addElement(pd); this.propertyDescTable.put(fullName, pdList); } else { pdList = this.propertyDescTable.get(fullName); if (pdList != null) { pdList.addElement(pd); this.propertyDescTable.put(fullName, pdList); }  }  }  if (elements != null) for (int i = 0; i < elements.length; i++) { try { parsePropertiesRfh2Element(folder + "." + name, elements[i]); } catch (Exception e) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.mq.MQMessage", "parsePropertiesRfh2Element(String,Element)", e);  Trace.data(this, "parsePropertiesRfh2Element(String,Element)", "Failed to parse an RFH2 element while retrieving properties", e); }  }   if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "parsePropertiesRfh2Element(String,Element)");  } private MQPropertyDescriptor getMQPDFromRfhElement(MQRFH2.Element element) throws MQException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "getMQPDFromRfhElement(Element)", new Object[] { element });  MQPropertyDescriptor pd = new MQPropertyDescriptor(); String[] list = null; String support = element.getAttributeValue("support"); String context = element.getAttributeValue("context"); String copy = element.getAttributeValue("copy"); if (copy != null) list = copy.split(",");  if (support != null) { byte[] b = hexToBin(support, 0); int value = 0; for (int i = 0; i < 4; i++) { int shift = (3 - i) * 8; value += (b[i + this.offset] & 0xFF) << shift; }  pd.support = value; }  if (context != null) if (context.trim().equalsIgnoreCase("none")) { pd.context = 0; } else if (context.trim().equalsIgnoreCase("user")) { pd.context = 1; }   if (list != null) { int value = 0; int size = Arrays.<String>asList(list).size(); for (int i = 0; i < size; i++) { if (list[i].trim().equals("none")) { value = 0; break; }  if (list[i].trim().equals("all")) { value = 1; break; }  if (list[i].trim().equals("forward")) value += 2;  if (list[i].trim().equals("reply")) value += 8;  if (list[i].trim().equals("publish")) value += 4;  if (list[i].trim().equals("report")) value += 16;  }  pd.copyOptions = value; }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "getMQPDFromRfhElement(Element)", pd);  return pd; } private void setProperty(String name, MQPropertyDescriptor descriptor, Object value) throws MQException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "setProperty(String,MQPropertyDescriptor,Object)", new Object[] { name, descriptor, value });  Vector<Object> valueList = null; String propertyName = name; if (propertyName.startsWith("mq")) { propertyName = makeMqFolderExplicit(propertyName); } else { if (TYPE_MCD_ELEMENTS.contains(propertyName)) { int MQRC_PROPERTY_READ_ONLY = 2471; MQException traceRet1 = new MQException(2, MQRC_PROPERTY_READ_ONLY, this); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "setProperty(String,MQPropertyDescriptor,Object)", traceRet1);  throw traceRet1; }  if (!propertyName.toLowerCase().startsWith("jms")) propertyName = makeUsrFolderExplicit(name);  }  if (this.propertyValidation != 1) validatePropertyName(propertyName);  if (descriptor != null) validatePropertyDescriptor(descriptor);  if (descriptor != null) if (this.propertyDescTable.containsKey(propertyName)) { Vector<MQPropertyDescriptor> pdList = this.propertyDescTable.get(propertyName); if (pdList != null) { pdList.addElement(descriptor); this.propertyDescTable.put(propertyName, pdList); }  } else { Vector<MQPropertyDescriptor> pdList = new Vector<>(); pdList.addElement(descriptor); this.propertyDescTable.put(propertyName, pdList); }   if (propertyName.startsWith("Root.MQMD.")) { setMQMDProperty(propertyName.substring(10), value); } else if (ALL_JMS_PROPERTY_NAMES.contains(propertyName)) { setJmsProperty(propertyName, value); } else { if (this.properties.containsKey(propertyName)) { valueList = this.properties.get(propertyName); if (valueList != null) valueList.addElement(value);  } else { valueList = new Vector(); valueList.addElement(value); }  this.properties.put(propertyName, valueList); }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "setProperty(String,MQPropertyDescriptor,Object)");  } private void validatePropertyDescriptor(MQPropertyDescriptor descriptor) throws MQException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "validatePropertyDescriptor(MQPropertyDescriptor)", new Object[] { descriptor });  int version = descriptor.version; boolean throwException = false; if (version > 1) throwException = true;  if (throwException == true) { MQException traceRet1 = new MQException(2, 2482, this); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "validatePropertyDescriptor(MQPropertyDescriptor)", traceRet1);  throw traceRet1; }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "validatePropertyDescriptor(MQPropertyDescriptor)");  } private void setMQMDProperty(String propName, Object value) throws MQException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "setMQMDProperty(String,Object)", new Object[] { propName, value });  if (propName.equalsIgnoreCase("report")) { this.report = ((Integer)value).intValue(); } else if (propName.equalsIgnoreCase("msgType")) { this.messageType = ((Integer)value).intValue(); } else if (propName.equalsIgnoreCase("expiry")) { this.expiry = ((Integer)value).intValue(); } else if (propName.equalsIgnoreCase("feedback")) { this.feedback = ((Integer)value).intValue(); } else if (propName.equalsIgnoreCase("encoding")) { this.encoding = ((Integer)value).intValue(); } else if (propName.equalsIgnoreCase("characterSet")) { this.characterSet = ((Integer)value).intValue(); } else if (propName.equalsIgnoreCase("format")) { this.format = value.toString(); } else if (propName.equalsIgnoreCase("priority")) { this.priority = ((Integer)value).intValue(); } else if (propName.equalsIgnoreCase("persistence")) { this.persistence = ((Integer)value).intValue(); } else if (propName.equalsIgnoreCase("MsgId")) { setMessageId((byte[])value); } else if (propName.equalsIgnoreCase("correlId")) { setCorrelationId((byte[])value); } else if (propName.equalsIgnoreCase("ReplyToQ")) { this.replyToQueueName = value.toString(); } else if (propName.equalsIgnoreCase("ReplyToQMgr")) { this.replyToQueueManagerName = value.toString(); } else if (propName.equalsIgnoreCase("UserIdentifier")) { setJMSXUserID(value); } else if (propName.equalsIgnoreCase("accoundingToken")) { this.accountingToken = (byte[])value; } else if (propName.equalsIgnoreCase("applicationIdData")) { this.applicationIdData = value.toString(); } else if (propName.equalsIgnoreCase("putApplicationType")) { this.putApplicationType = ((Integer)value).intValue(); } else if (propName.equalsIgnoreCase("putApplName")) { this.putApplicationName = (String)value; } else if (propName.equalsIgnoreCase("applicationOrginData")) { this.applicationOriginData = value.toString(); } else if (propName.equalsIgnoreCase("groupId")) { if (value instanceof byte[]) { byte[] valueArray = (byte[])value; System.arraycopy(valueArray, 0, this.groupId, 0, Math.min(valueArray.length, this.groupId.length)); }  } else if (propName.equalsIgnoreCase("MsgSeqNumber")) { this.messageSequenceNumber = ((Integer)value).intValue(); } else if (propName.equalsIgnoreCase("offset")) { this.offset = ((Integer)value).intValue(); } else if (propName.equalsIgnoreCase("messageFlags")) { this.messageFlags = ((Integer)value).intValue(); } else if (propName.equalsIgnoreCase("originalLength")) { this.originalLength = ((Integer)value).intValue(); } else if (propName.equalsIgnoreCase("BackoutCount")) { this.backoutCount = ((Integer)value).intValue(); } else { MQException traceRet1 = new MQException(2, 2442, this); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "setMQMDProperty(String,Object)", traceRet1);  throw traceRet1; }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "setMQMDProperty(String,Object)");  } private Object getMQMDProperty(String propName) throws MQException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "getMQMDProperty(String)", new Object[] { propName });  Object retvalue = null; if (propName.equalsIgnoreCase("report")) { retvalue = Integer.valueOf(this.report); } else if (propName.equalsIgnoreCase("msgType")) { retvalue = Integer.valueOf(this.messageType); } else if (propName.equalsIgnoreCase("expiry")) { retvalue = Long.valueOf(this.expiry); } else if (propName.equalsIgnoreCase("feedback")) { retvalue = Integer.valueOf(this.feedback); } else if (propName.equalsIgnoreCase("encoding")) { retvalue = Integer.valueOf(this.encoding); } else if (propName.equalsIgnoreCase("characterSet")) { retvalue = Integer.valueOf(this.characterSet); } else if (propName.equalsIgnoreCase("format")) { retvalue = Integer.valueOf(this.format); } else if (propName.equalsIgnoreCase("priority")) { retvalue = Integer.valueOf(this.priority); } else if (propName.equalsIgnoreCase("persistence")) { retvalue = Integer.valueOf(this.persistence); } else if (propName.equalsIgnoreCase("MsgId")) { retvalue = this.messageId; } else if (propName.equalsIgnoreCase("correlId")) { retvalue = this.correlationId; } else if (propName.equalsIgnoreCase("ReplyToQ")) { retvalue = this.replyToQueueName; } else if (propName.equalsIgnoreCase("ReplyToQMgr")) { retvalue = this.replyToQueueManagerName; } else if (propName.equalsIgnoreCase("UserIdentifier")) { retvalue = this.userId; } else if (propName.equalsIgnoreCase("accoundingToken")) { retvalue = this.accountingToken; } else if (propName.equalsIgnoreCase("applicationIdData")) { retvalue = this.applicationIdData; } else if (propName.equalsIgnoreCase("putApplicationType")) { retvalue = Integer.valueOf(this.putApplicationType); } else if (propName.equalsIgnoreCase("putApplName")) { retvalue = this.putApplicationName; } else if (propName.equalsIgnoreCase("putDate")) { retvalue = getDate(this.putDateTime); } else if (propName.equalsIgnoreCase("putTime")) { retvalue = getTime(this.putDateTime); } else if (propName.equalsIgnoreCase("applicationOrginData")) { retvalue = this.applicationOriginData; } else if (propName.equalsIgnoreCase("groupId")) { retvalue = this.groupId; } else if (propName.equalsIgnoreCase("MsgSeqNumber")) { retvalue = Integer.valueOf(this.messageSequenceNumber); } else if (propName.equalsIgnoreCase("offset")) { retvalue = Integer.valueOf(this.offset); } else if (propName.equalsIgnoreCase("messageFlags")) { retvalue = Integer.valueOf(this.messageFlags); } else if (propName.equalsIgnoreCase("originalLength")) { retvalue = Integer.valueOf(this.originalLength); } else if (propName.equalsIgnoreCase("BackoutCount")) { retvalue = Integer.valueOf(this.backoutCount); } else { MQException traceRet1 = new MQException(2, 2442, this); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "getMQMDProperty(String)", traceRet1);  throw traceRet1; }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "getMQMDProperty(String)", retvalue);  return retvalue; } private byte[] hexToBin(String hex, int start) throws MQException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "hexToBin(String,int)", new Object[] { hex, Integer.valueOf(start) });  int length = hex.length() - start; if (length == 0) { byte[] result = new byte[0]; if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "hexToBin(String,int)", result, 1);  return result; }  if (length < 0 || length % 2 != 0) { MQException traceRet1 = new MQException(2, 2207, this); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "hexToBin(String,int)", traceRet1, 1);  throw traceRet1; }  length /= 2; byte[] retval = new byte[length]; for (int i = 0; i < length; i++) { int digit1 = Character.digit(hex.charAt(2 * i + start), 16) << 4; int digit2 = Character.digit(hex.charAt(2 * i + start + 1), 16); if (digit1 < 0 || digit2 < 0) { MQException traceRet1 = new MQException(2, 2207, this); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "hexToBin(String,int)", traceRet1, 2);  throw traceRet1; }  retval[i] = (byte)(digit1 + digit2); }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "hexToBin(String,int)", retval, 2);  return retval; } private int binToHex(byte[] bin, int start, int length, StringBuffer hex) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "binToHex(byte [ ],int,int,StringBuffer)", new Object[] { bin, Integer.valueOf(start), Integer.valueOf(length), hex });  int binByte = 0; int sum = 0; for (int i = start; i < start + length; i++) { binByte = bin[i]; if (binByte < 0) binByte += 256;  sum += binByte; hex.append(BIN2HEX[binByte / 16]); hex.append(BIN2HEX[binByte % 16]); }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "binToHex(byte [ ],int,int,StringBuffer)", Integer.valueOf(sum));  return sum; } private void setMessageId(byte[] msgId) { if (Trace.isOn) Trace.data(this, "com.ibm.mq.MQMessage", "setMessageId(byte [ ])", "setter", msgId);  byte[] mid = new byte[24]; int length = msgId.length; if (length > 24) length = 24;  int i; for (i = 0; i < length; i++) mid[i] = msgId[i];  for (i = length; i < 24; i++) mid[i] = 0;  this.messageId = mid; } private void setCorrelationId(byte[] correlId) { if (Trace.isOn) Trace.data(this, "com.ibm.mq.MQMessage", "setCorrelationId(byte [ ])", "setter", correlId);  byte[] cid = new byte[24]; int length = correlId.length; if (length > 24) length = 24;  int i; for (i = 0; i < length; i++) cid[i] = correlId[i];  for (i = length; i < 24; i++) cid[i] = 0;  this.correlationId = cid; } private void setJmsProperty(String name, Object value) throws MQException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "setJmsProperty(String,Object)", new Object[] { name, value });  if (SETTABLE_JMS_PROPERTY_NAMES.contains(name)) { if (name.equalsIgnoreCase("JMSCorrelationID")) { setJMSCorrelationID(value); } else if (name.equalsIgnoreCase("JMSDeliveryMode")) { setJMSDeliveryMode(value); } else if (name.equalsIgnoreCase("JMSDestination")) { setJMSDestination(value); } else if (name.equalsIgnoreCase("JMSExpiration")) { setJMSExpiration(value); } else if (name.equalsIgnoreCase("JMSMessageID")) { setJMSMessageID(value); } else if (name.equalsIgnoreCase("JMSPriority")) { setJMSPriority(value); } else if (name.equalsIgnoreCase("JMSReplyTo")) { setJMSReplyTo(value); } else if (name.equalsIgnoreCase("JMSType")) { setJMSType(value); } else if (name.equalsIgnoreCase("JMSTimestamp")) { setJMSTimeStamp(value); } else if (name.equalsIgnoreCase("JMSXAppID")) { setJMSXAppID(value); } else if (name.equalsIgnoreCase("JMSXDeliveryCount")) { setJMSXDeliveryCount(value); } else if (name.equalsIgnoreCase("JMSXGroupID")) { setJMSXGroupID(value); } else if (name.equalsIgnoreCase("JMSXGroupSeq")) { setJMSXGroupSeq(value); } else if (name.equalsIgnoreCase("JMSXUserID")) { setJMSXUserID(value); } else { MQException traceRet1 = new MQException(2, 2442, this); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "setJmsProperty(String,Object)", traceRet1, 1);  throw traceRet1; }  } else { int MQRC_PROPERTY_READ_ONLY = 2471; MQException traceRet1 = new MQException(2, MQRC_PROPERTY_READ_ONLY, this); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "setJmsProperty(String,Object)", traceRet1, 2);  throw traceRet1; }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "setJmsProperty(String,Object)");  } private Object getJmsProperty(String name) throws MQException { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.MQMessage", "getJmsProperty(String)", new Object[] { name });  Object result = null; if (ALL_JMS_PROPERTY_NAMES.contains(name)) { if (name.equalsIgnoreCase("JMSXAppID")) { result = getJMSXAppID(); } else if (name.equalsIgnoreCase("JMSXDeliveryCount")) { result = getJMSXDeliveryCount(); } else if (name.equalsIgnoreCase("JMSXUserID")) { result = getJMSXUserID(); } else if (name.equalsIgnoreCase("JMSCorrelationID")) { result = getJMSCorrelationID(); } else if (name.equalsIgnoreCase("JMSDeliveryMode")) { result = getJMSDeliveryMode(); } else if (name.equalsIgnoreCase("JMSDestination")) { result = getJMSDestination(); } else if (name.equalsIgnoreCase("JMSExpiration")) { result = getJMSExpiration(); } else if (name.equalsIgnoreCase("JMSMessageID")) { result = getJMSMessageID(); } else if (name.equalsIgnoreCase("JMSPriority")) { result = getJMSPriority(); } else if (name.equalsIgnoreCase("JMSReplyTo")) { result = getJMSReplyTo(); } else if (name.equalsIgnoreCase("JMSTimestamp")) { result = getJMSTimestamp(); } else if (name.equalsIgnoreCase("JMSType")) { result = getJMSType(); } else if (name.equalsIgnoreCase("JMSXGroupID")) { result = getJMSXGroupID(); } else if (name.equalsIgnoreCase("JMSXGroupSeq")) { result = getJMSXGroupSeq(); }  } else { MQException traceRet1 = new MQException(2, 2442, this); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "getJmsProperty(String)", traceRet1);  throw traceRet1; }  if (Trace.isOn) Trace.exit(this, "com.ibm.mq.MQMessage", "getJmsProperty(String)", result);  return result; } private void setJMSCorrelationID(Object value) throws MQException { if (Trace.isOn) Trace.data(this, "com.ibm.mq.MQMessage", "setJMSCorrelationID(Object)", "setter", value);  try { if (value instanceof String) { String jmsCorrelId = value.toString(); if (jmsCorrelId.length() >= 3 && jmsCorrelId.substring(0, 3).equalsIgnoreCase("id:")) { jmsCorrelId = jmsCorrelId.substring(3, jmsCorrelId.length() - 3); setMQMDProperty("correlId", hexToBin(jmsCorrelId, 0)); } else { try { setMQMDProperty("correlId", jmsCorrelId.getBytes("UTF-8")); Vector<Object> valueList = new Vector(); valueList.addElement(value); this.properties.put("jms.Cid", valueList); } catch (UnsupportedEncodingException ex) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.mq.MQMessage", "setJMSCorrelationID(Object)", ex, 1);  MQException traceRet1 = new MQException(2, 6106, this); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "setJMSCorrelationID(Object)", traceRet1, 1);  throw traceRet1; }  }  } else if (value instanceof byte[]) { setMQMDProperty("correlId", value); } else { MQException traceRet1 = new MQException(2, 2207, this); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "setJMSCorrelationID(Object)", traceRet1, 2);  throw traceRet1; }  } catch (Exception ex) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.mq.MQMessage", "setJMSCorrelationID(Object)", ex, 2);  MQException traceRet1 = new MQException(2, 2473, this); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "setJMSCorrelationID(Object)", traceRet1, 3);  throw traceRet1; }  } private Object getJMSCorrelationID() throws MQException { Object result; if (this.properties.containsKey("jms.Cid")) { Vector<?> valueList = this.properties.get("jms.Cid"); result = valueList.elementAt(0); } else { StringBuffer hex = new StringBuffer(); byte[] correlId = (byte[])getMQMDProperty("correlId"); binToHex(correlId, 0, correlId.length, hex); hex.insert(0, "ID:"); result = new String(hex); }  if (Trace.isOn) Trace.data(this, "com.ibm.mq.MQMessage", "getJMSCorrelationID()", "getter", result);  return result; } private void setJMSDeliveryMode(Object value) throws MQException { if (Trace.isOn) Trace.data(this, "com.ibm.mq.MQMessage", "setJMSDeliveryMode(Object)", "setter", value);  if (value instanceof Integer) { Integer valueAsInteger = (Integer)value; if (valueAsInteger.intValue() == 1) { setMQMDProperty("persistence", value); } else if (valueAsInteger.intValue() == 0) { setMQMDProperty("persistence", value); } else { MQException traceRet1 = new MQException(2, 2047, this); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "setJMSDeliveryMode(Object)", traceRet1, 1);  throw traceRet1; }  Vector<Object> valueList = new Vector(); valueList.addElement(value); this.properties.put("jms.Dlv", valueList); } else { MQException traceRet1 = new MQException(2, 2473, this); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "setJMSDeliveryMode(Object)", traceRet1, 2);  throw traceRet1; }  } private Object getJMSDeliveryMode() throws MQException { Object result; if (this.properties.containsKey("jms.Dlv")) { Vector<Object> valueList = this.properties.get("jms.Dlv"); result = valueList.elementAt(0); } else { result = getMQMDProperty("persistence"); }  if (Trace.isOn) Trace.data(this, "com.ibm.mq.MQMessage", "getJMSDeliveryMode()", "getter", result);  return result; } private void setJMSDestination(Object value) { if (Trace.isOn) Trace.data(this, "com.ibm.mq.MQMessage", "setJMSDestination(Object)", "setter", value);  Vector<Object> valueList = new Vector(); valueList.addElement(value); this.properties.put("jms.Dst", valueList); } private Object getJMSDestination() throws MQException { Object result; if (this.properties.containsKey("jms.Dst")) { Vector<Object> valueList = this.properties.get("jms.Dst"); result = valueList.elementAt(0); } else { MQException traceRet1 = new MQException(2, 2471, this); if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.MQMessage", "getJMSDestination()", traceRet1);  throw traceRet1; }  if (Trace.isOn) Trace.data(this, "com.ibm.mq.MQMessage", "getJMSDestination()", "getter", result);  return result; } private void setJMSMessageID(Object value) throws MQException { if (Trace.isOn) {
/* 4945 */       Trace.data(this, "com.ibm.mq.MQMessage", "setJMSMessageID(Object)", "setter", value);
/*      */     }
/*      */     try {
/* 4948 */       if (value instanceof String) {
/* 4949 */         String jmsMsgId = value.toString();
/* 4950 */         if (jmsMsgId.length() >= 3 && jmsMsgId.substring(0, 3).equalsIgnoreCase("ID:")) {
/*      */           
/* 4952 */           jmsMsgId = jmsMsgId.substring(3, jmsMsgId.length() - 3);
/* 4953 */           setMQMDProperty("MsgId", hexToBin(jmsMsgId, 0));
/*      */         } 
/* 4955 */       } else if (value instanceof byte[]) {
/* 4956 */         setMQMDProperty("MsgId", value);
/*      */       } else {
/* 4958 */         MQException traceRet1 = new MQException(2, 2473, this);
/*      */         
/* 4960 */         if (Trace.isOn) {
/* 4961 */           Trace.throwing(this, "com.ibm.mq.MQMessage", "setJMSMessageID(Object)", traceRet1, 1);
/*      */         }
/* 4963 */         throw traceRet1;
/*      */       }
/*      */     
/* 4966 */     } catch (Exception ex) {
/* 4967 */       if (Trace.isOn) {
/* 4968 */         Trace.catchBlock(this, "com.ibm.mq.MQMessage", "setJMSMessageID(Object)", ex);
/*      */       }
/* 4970 */       MQException traceRet1 = new MQException(2, 2206, this);
/* 4971 */       if (Trace.isOn) {
/* 4972 */         Trace.throwing(this, "com.ibm.mq.MQMessage", "setJMSMessageID(Object)", traceRet1, 2);
/*      */       }
/* 4974 */       throw traceRet1;
/*      */     }  }
/*      */ 
/*      */ 
/*      */   
/*      */   private Object getJMSMessageID() throws MQException {
/* 4980 */     StringBuffer hex = new StringBuffer();
/* 4981 */     byte[] msgId = (byte[])getMQMDProperty("MsgId");
/* 4982 */     binToHex(msgId, 0, msgId.length, hex);
/* 4983 */     hex.insert(0, "ID:");
/* 4984 */     Object result = new String(hex);
/*      */     
/* 4986 */     if (Trace.isOn) {
/* 4987 */       Trace.data(this, "com.ibm.mq.MQMessage", "getJMSMessageID()", "getter", result);
/*      */     }
/* 4989 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void setJMSPriority(Object value) throws MQException {
/* 4995 */     if (Trace.isOn) {
/* 4996 */       Trace.data(this, "com.ibm.mq.MQMessage", "setJMSPriority(Object)", "setter", value);
/*      */     }
/* 4998 */     if (value instanceof Integer) {
/* 4999 */       setMQMDProperty("priority", value);
/* 5000 */       if (((Integer)value).intValue() != 4) {
/* 5001 */         Vector<Object> valueList = new Vector();
/* 5002 */         valueList.addElement(value);
/* 5003 */         this.properties.put("jms.Pri", valueList);
/*      */       } 
/*      */     } else {
/* 5006 */       MQException traceRet1 = new MQException(2, 2473, this);
/*      */       
/* 5008 */       if (Trace.isOn) {
/* 5009 */         Trace.throwing(this, "com.ibm.mq.MQMessage", "setJMSPriority(Object)", traceRet1);
/*      */       }
/* 5011 */       throw traceRet1;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private Object getJMSPriority() throws MQException {
/*      */     Object result;
/* 5018 */     if (this.properties.containsKey("jms.Pri")) {
/* 5019 */       Vector<Object> valueList = this.properties.get("jms.Pri");
/* 5020 */       result = valueList.elementAt(0);
/*      */     } else {
/* 5022 */       result = getMQMDProperty("priority");
/*      */     } 
/*      */     
/* 5025 */     if (Trace.isOn) {
/* 5026 */       Trace.data(this, "com.ibm.mq.MQMessage", "getJMSPriority()", "getter", result);
/*      */     }
/* 5028 */     return result;
/*      */   }
/*      */   
/*      */   private void setJMSReplyTo(Object value) throws MQException {
/* 5032 */     if (Trace.isOn) {
/* 5033 */       Trace.data(this, "com.ibm.mq.MQMessage", "setJMSReplyTo(Object)", "setter", value);
/*      */     }
/* 5035 */     String uri = value.toString();
/*      */     try {
/* 5037 */       if (uri.startsWith("queue://")) {
/*      */ 
/*      */         
/* 5040 */         String[] uriSubstrings = uri.trim().split("/");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 5047 */         if (uriSubstrings[2].length() != 0) {
/* 5048 */           setMQMDProperty("ReplyToQMgr", uriSubstrings[2]);
/*      */         }
/* 5050 */         setMQMDProperty("ReplyToQ", uriSubstrings[3]);
/*      */       } else {
/* 5052 */         setMQMDProperty("ReplyToQ", uri.trim());
/*      */       } 
/* 5054 */       Vector<Object> valueList = new Vector();
/* 5055 */       valueList.addElement(value);
/* 5056 */       this.properties.put("jms.Rto", valueList);
/*      */     }
/* 5058 */     catch (Exception ex) {
/* 5059 */       if (Trace.isOn) {
/* 5060 */         Trace.catchBlock(this, "com.ibm.mq.MQMessage", "setJMSReplyTo(Object)", ex);
/*      */       }
/*      */       
/* 5063 */       MQException traceRet1 = new MQException(2, 2473, this);
/* 5064 */       if (Trace.isOn) {
/* 5065 */         Trace.throwing(this, "com.ibm.mq.MQMessage", "setJMSReplyTo(Object)", traceRet1);
/*      */       }
/* 5067 */       throw traceRet1;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private Object getJMSReplyTo() throws MQException {
/* 5073 */     Object result = null;
/*      */     
/* 5075 */     if (this.properties.containsKey("jms.Rto")) {
/* 5076 */       Vector<Object> valueList = this.properties.get("jms.Rto");
/* 5077 */       result = valueList.elementAt(0);
/*      */     } else {
/* 5079 */       MQException traceRet1 = new MQException(2, 2471, this);
/*      */       
/* 5081 */       if (Trace.isOn) {
/* 5082 */         Trace.throwing(this, "com.ibm.mq.MQMessage", "getJMSReplyTo()", traceRet1);
/*      */       }
/* 5084 */       throw traceRet1;
/*      */     } 
/* 5086 */     if (Trace.isOn) {
/* 5087 */       Trace.data(this, "com.ibm.mq.MQMessage", "getJMSReplyTo()", "getter", result);
/*      */     }
/* 5089 */     return result;
/*      */   }
/*      */   
/*      */   private void setJMSTimeStamp(Object value) throws MQException {
/* 5093 */     if (Trace.isOn) {
/* 5094 */       Trace.data(this, "com.ibm.mq.MQMessage", "setJMSTimeStamp(Object)", "setter", value);
/*      */     }
/* 5096 */     if (value instanceof Long) {
/* 5097 */       Vector<Object> valueList = new Vector();
/* 5098 */       valueList.addElement(value);
/* 5099 */       this.properties.put("jms.Tms", valueList);
/*      */     } else {
/* 5101 */       MQException traceRet1 = new MQException(2, 2473, this);
/*      */       
/* 5103 */       if (Trace.isOn) {
/* 5104 */         Trace.throwing(this, "com.ibm.mq.MQMessage", "setJMSTimeStamp(Object)", traceRet1);
/*      */       }
/* 5106 */       throw traceRet1;
/*      */     } 
/*      */   }
/*      */   
/*      */   private Object getJMSTimestamp() {
/* 5111 */     Object result = null;
/*      */     
/* 5113 */     if (this.properties.containsKey("jms.Tms")) {
/* 5114 */       Vector<Object> valueList = this.properties.get("jms.Tms");
/* 5115 */       Object jts = valueList.elementAt(0);
/* 5116 */       if (jts instanceof Long) {
/* 5117 */         result = jts;
/* 5118 */       } else if (jts instanceof String) {
/* 5119 */         String jtsAsString = (String)jts;
/* 5120 */         result = Long.valueOf(Long.parseLong(jtsAsString));
/*      */       } 
/*      */     } else {
/* 5123 */       result = Long.valueOf(this.putDateTime.getTimeInMillis());
/*      */     } 
/*      */     
/* 5126 */     if (Trace.isOn) {
/* 5127 */       Trace.data(this, "com.ibm.mq.MQMessage", "getJMSTimestamp()", "getter", result);
/*      */     }
/* 5129 */     return result;
/*      */   }
/*      */   
/*      */   private void setJMSType(Object value) throws MQException {
/* 5133 */     if (Trace.isOn) {
/* 5134 */       Trace.data(this, "com.ibm.mq.MQMessage", "setJMSType(Object)", "setter", value);
/*      */     }
/* 5136 */     String uri = value.toString();
/* 5137 */     if (!uri.startsWith("mcd://")) {
/* 5138 */       Vector<Object> valueList = new Vector();
/* 5139 */       valueList.addElement(value);
/* 5140 */       String domain = "";
/* 5141 */       Vector<Object> domainVector = new Vector();
/* 5142 */       this.properties.put("mcd.Type", valueList);
/* 5143 */       if ("MQSTR   ".equals(this.format)) {
/* 5144 */         domain = "jms_text";
/*      */       } else {
/* 5146 */         domain = "jms_bytes";
/*      */       } 
/* 5148 */       domainVector.addElement(domain);
/* 5149 */       this.properties.put("mcd.Msd", domainVector);
/*      */     } else {
/*      */ 
/*      */       
/*      */       try {
/* 5154 */         String[] uriSubstrings = uri.split("[/?=]");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 5164 */         String domain = uriSubstrings[2];
/* 5165 */         String set = uriSubstrings[3];
/* 5166 */         String type = uriSubstrings[4];
/* 5167 */         String myFormat = uriSubstrings[5].equalsIgnoreCase("format") ? uriSubstrings[6] : null;
/* 5168 */         if (domain.length() == 0) {
/* 5169 */           if ("MQSTR   ".equals(myFormat)) {
/* 5170 */             domain = "jms_text";
/*      */           } else {
/* 5172 */             domain = "jms_bytes";
/*      */           } 
/*      */         }
/* 5175 */         Vector<Object> valueList = new Vector();
/* 5176 */         valueList.addElement(type);
/* 5177 */         this.properties.put("mcd.Type", valueList);
/* 5178 */         Vector<Object> valueListSet = new Vector();
/* 5179 */         valueListSet.addElement(set);
/* 5180 */         this.properties.put("mcd.Set", valueListSet);
/* 5181 */         Vector<Object> valueListFmt = new Vector();
/* 5182 */         valueListFmt.addElement(myFormat);
/* 5183 */         this.properties.put("mcd.Fmt", valueListFmt);
/* 5184 */         Vector<Object> domainVector = new Vector();
/* 5185 */         domainVector.addElement(domain);
/* 5186 */         this.properties.put("mcd.Msd", domainVector);
/*      */       }
/* 5188 */       catch (Exception ex) {
/* 5189 */         if (Trace.isOn) {
/* 5190 */           Trace.catchBlock(this, "com.ibm.mq.MQMessage", "setJMSType(Object)", ex);
/*      */         }
/*      */         
/* 5193 */         MQException traceRet1 = new MQException(2, 2473, this);
/* 5194 */         if (Trace.isOn) {
/* 5195 */           Trace.throwing(this, "com.ibm.mq.MQMessage", "setJMSType(Object)", traceRet1);
/*      */         }
/* 5197 */         throw traceRet1;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private Object getJMSType() throws MQException {
/* 5203 */     Object result = null;
/*      */     
/* 5205 */     String domain = null;
/* 5206 */     String type = null;
/* 5207 */     String set = null;
/* 5208 */     String fmt = null;
/* 5209 */     if (this.properties.containsKey("mcd.Type")) {
/* 5210 */       Vector<Object> valueList = this.properties.get("mcd.Type");
/* 5211 */       type = (String)valueList.elementAt(0);
/* 5212 */       result = type;
/*      */     } else {
/* 5214 */       MQException traceRet1 = new MQException(2, 2471, this);
/*      */       
/* 5216 */       if (Trace.isOn) {
/* 5217 */         Trace.throwing(this, "com.ibm.mq.MQMessage", "getJMSType()", traceRet1);
/*      */       }
/* 5219 */       throw traceRet1;
/*      */     } 
/*      */     
/* 5222 */     if (this.properties.containsKey("mcd.Msd")) {
/* 5223 */       Vector<Object> valueList = this.properties.get("mcd.Msd");
/* 5224 */       domain = (String)valueList.elementAt(0);
/*      */     }
/* 5226 */     else if ("MQSTR   ".equals(this.format)) {
/* 5227 */       domain = "jms_text";
/*      */     } else {
/* 5229 */       domain = "jms_bytes";
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 5234 */     if (this.properties.containsKey("mcd.Set")) {
/* 5235 */       Vector<String> vector = (Vector)this.properties.get("mcd.Set");
/* 5236 */       set = vector.elementAt(0);
/*      */     } 
/* 5238 */     if (this.properties.containsKey("mcd.Fmt")) {
/* 5239 */       Vector<String> vector = (Vector)this.properties.get("mcd.Fmt");
/* 5240 */       fmt = vector.elementAt(0);
/*      */     } 
/* 5242 */     if (set != null || fmt != null) {
/* 5243 */       String mcdFormatString = (fmt == null || fmt.equals("")) ? "mcd://{0}/{1}/{2}" : "mcd://{0}/{1}/{2}?format={3}";
/* 5244 */       result = MessageFormat.format(mcdFormatString, new Object[] { domain, set, type, fmt });
/*      */     } 
/* 5246 */     if (Trace.isOn) {
/* 5247 */       Trace.data(this, "com.ibm.mq.MQMessage", "getJMSType()", "getter", result);
/*      */     }
/* 5249 */     return result;
/*      */   }
/*      */   
/*      */   private void setJMSXAppID(Object value) throws MQException {
/* 5253 */     if (Trace.isOn) {
/* 5254 */       Trace.data(this, "com.ibm.mq.MQMessage", "setJMSXAppID(Object)", "setter", value);
/*      */     }
/* 5256 */     setMQMDProperty("putApplName", value);
/*      */   }
/*      */   
/*      */   private Object getJMSXAppID() throws MQException {
/* 5260 */     Object traceRet1 = getMQMDProperty("putApplName");
/* 5261 */     if (Trace.isOn) {
/* 5262 */       Trace.data(this, "com.ibm.mq.MQMessage", "getJMSXAppID()", "getter", traceRet1);
/*      */     }
/* 5264 */     return traceRet1;
/*      */   }
/*      */   
/*      */   private void setJMSXDeliveryCount(Object value) throws MQException {
/* 5268 */     if (Trace.isOn) {
/* 5269 */       Trace.data(this, "com.ibm.mq.MQMessage", "setJMSXDeliveryCount(Object)", "setter", value);
/*      */     }
/* 5271 */     if (value instanceof Integer) {
/* 5272 */       setMQMDProperty("BackoutCount", value);
/*      */     } else {
/* 5274 */       MQException traceRet1 = new MQException(2, 2473, this);
/*      */       
/* 5276 */       if (Trace.isOn) {
/* 5277 */         Trace.throwing(this, "com.ibm.mq.MQMessage", "setJMSXDeliveryCount(Object)", traceRet1);
/*      */       }
/* 5279 */       throw traceRet1;
/*      */     } 
/*      */   }
/*      */   
/*      */   private Object getJMSXDeliveryCount() throws MQException {
/* 5284 */     Object traceRet1 = getMQMDProperty("BackoutCount");
/* 5285 */     if (Trace.isOn) {
/* 5286 */       Trace.data(this, "com.ibm.mq.MQMessage", "getJMSXDeliveryCount()", "getter", traceRet1);
/*      */     }
/* 5288 */     return traceRet1;
/*      */   }
/*      */   
/*      */   private void setJMSXGroupID(Object value) throws MQException {
/* 5292 */     if (Trace.isOn) {
/* 5293 */       Trace.data(this, "com.ibm.mq.MQMessage", "setJMSXGroupID(Object)", "setter", value);
/*      */     }
/* 5295 */     if (value instanceof String) {
/* 5296 */       Vector<Object> valueList = new Vector();
/* 5297 */       valueList.add(value);
/* 5298 */       this.properties.put("jms.Gid", valueList);
/*      */       try {
/* 5300 */         byte[] stringBytes = getCodepage(this.characterSet).stringToBytes((String)value);
/*      */         
/* 5302 */         setMQMDProperty("groupId", stringBytes);
/*      */       }
/* 5304 */       catch (CharacterCodingException e) {
/* 5305 */         if (Trace.isOn) {
/* 5306 */           Trace.catchBlock(this, "com.ibm.mq.MQMessage", "setJMSXGroupID(Object)", e, 1);
/*      */         }
/*      */         
/* 5309 */         MQException traceRet1 = new MQException(2, 2330, this);
/*      */         
/* 5311 */         if (Trace.isOn) {
/* 5312 */           Trace.throwing(this, "com.ibm.mq.MQMessage", "setJMSXGroupID(Object)", traceRet1, 1);
/*      */         }
/* 5314 */         throw traceRet1;
/*      */       }
/* 5316 */       catch (UnsupportedCharsetException e) {
/* 5317 */         if (Trace.isOn) {
/* 5318 */           Trace.catchBlock(this, "com.ibm.mq.MQMessage", "setJMSXGroupID(Object)", e, 2);
/*      */         }
/* 5320 */         MQException traceRet1 = new MQException(2, 2330, this);
/*      */         
/* 5322 */         if (Trace.isOn) {
/* 5323 */           Trace.throwing(this, "com.ibm.mq.MQMessage", "setJMSXGroupID(Object)", traceRet1, 2);
/*      */         }
/* 5325 */         throw traceRet1;
/*      */       } 
/*      */     } else {
/* 5328 */       MQException traceRet1 = new MQException(2, 2473, this);
/*      */       
/* 5330 */       if (Trace.isOn) {
/* 5331 */         Trace.throwing(this, "com.ibm.mq.MQMessage", "setJMSXGroupID(Object)", traceRet1, 3);
/*      */       }
/* 5333 */       throw traceRet1;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private Object getJMSXGroupID() throws MQException {
/* 5339 */     Object result = null;
/* 5340 */     if (this.properties.containsKey("jms.Gid")) {
/*      */       
/* 5342 */       Vector<Object> valueList = this.properties.get("jms.Gid");
/* 5343 */       result = valueList.elementAt(0);
/*      */     } else {
/* 5345 */       byte[] groupIdProperty = (byte[])getMQMDProperty("groupId");
/* 5346 */       if (groupIdProperty != null) {
/*      */         try {
/* 5348 */           result = getCodepage(this.characterSet).bytesToString(groupIdProperty);
/*      */         }
/* 5350 */         catch (CharacterCodingException e) {
/* 5351 */           if (Trace.isOn) {
/* 5352 */             Trace.catchBlock(this, "com.ibm.mq.MQMessage", "getJMSXGroupID()", e);
/*      */           }
/*      */           
/* 5355 */           MQException traceRet1 = new MQException(2, 2330, this);
/*      */           
/* 5357 */           if (Trace.isOn) {
/* 5358 */             Trace.throwing(this, "com.ibm.mq.MQMessage", "getJMSXGroupID()", traceRet1);
/*      */           }
/* 5360 */           throw traceRet1;
/*      */         } 
/*      */       }
/*      */     } 
/* 5364 */     if (Trace.isOn) {
/* 5365 */       Trace.data(this, "com.ibm.mq.MQMessage", "getJMSXGroupID()", "getter", result);
/*      */     }
/* 5367 */     return result;
/*      */   }
/*      */   
/*      */   private void setJMSXGroupSeq(Object value) throws MQException {
/* 5371 */     if (Trace.isOn) {
/* 5372 */       Trace.data(this, "com.ibm.mq.MQMessage", "setJMSXGroupSeq(Object)", "setter", value);
/*      */     }
/* 5374 */     if (value instanceof Integer) {
/* 5375 */       Vector<Object> valueList = new Vector();
/* 5376 */       valueList.add(value);
/* 5377 */       this.properties.put("jms.Seq", valueList);
/* 5378 */       setMQMDProperty("MsgSeqNumber", value);
/*      */     } else {
/* 5380 */       MQException traceRet1 = new MQException(2, 2473, this);
/*      */       
/* 5382 */       if (Trace.isOn) {
/* 5383 */         Trace.throwing(this, "com.ibm.mq.MQMessage", "setJMSXGroupSeq(Object)", traceRet1);
/*      */       }
/* 5385 */       throw traceRet1;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private Object getJMSXGroupSeq() throws MQException {
/*      */     Object result;
/* 5392 */     if (this.properties.containsKey("jms.Seq")) {
/* 5393 */       Vector<Object> valueList = this.properties.get("jms.Seq");
/* 5394 */       result = valueList.elementAt(0);
/*      */     } else {
/* 5396 */       result = getMQMDProperty("MsgSeqNumber");
/*      */     } 
/*      */     
/* 5399 */     if (Trace.isOn) {
/* 5400 */       Trace.data(this, "com.ibm.mq.MQMessage", "getJMSXGroupSeq()", "getter", result);
/*      */     }
/* 5402 */     return result;
/*      */   }
/*      */   
/*      */   private void setJMSXUserID(Object value) throws MQException {
/* 5406 */     if (Trace.isOn) {
/* 5407 */       Trace.data(this, "com.ibm.mq.MQMessage", "setJMSXUserID(Object)", "setter", value);
/*      */     }
/* 5409 */     setMQMDProperty("UserIdentifier", value);
/*      */   }
/*      */   
/*      */   private Object getJMSXUserID() throws MQException {
/* 5413 */     Object traceRet1 = getMQMDProperty("UserIdentifier");
/* 5414 */     if (Trace.isOn) {
/* 5415 */       Trace.data(this, "com.ibm.mq.MQMessage", "getJMSXUserID()", "getter", traceRet1);
/*      */     }
/* 5417 */     return traceRet1;
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
/*      */   private String makeUsrFolderExplicit(String name) throws MQException {
/* 5429 */     if (Trace.isOn) {
/* 5430 */       Trace.entry(this, "com.ibm.mq.MQMessage", "makeUsrFolderExplicit(String)", new Object[] { name });
/*      */     }
/*      */     
/* 5433 */     if (name == null || name.length() <= 0) {
/* 5434 */       MQException traceRet1 = new MQException(2, 2442, this);
/*      */       
/* 5436 */       if (Trace.isOn) {
/* 5437 */         Trace.throwing(this, "com.ibm.mq.MQMessage", "makeUsrFolderExplicit(String)", traceRet1);
/*      */       }
/* 5439 */       throw traceRet1;
/*      */     } 
/* 5441 */     String returnName = null;
/* 5442 */     if (name.indexOf(".") < 0) {
/* 5443 */       returnName = "usr." + name;
/*      */     } else {
/* 5445 */       returnName = name;
/*      */     } 
/* 5447 */     if (Trace.isOn) {
/* 5448 */       Trace.exit(this, "com.ibm.mq.MQMessage", "makeUsrFolderExplicit(String)", returnName);
/*      */     }
/* 5450 */     return returnName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String makeMqFolderExplicit(String name) throws MQException {
/* 5461 */     if (Trace.isOn) {
/* 5462 */       Trace.entry(this, "com.ibm.mq.MQMessage", "makeMqFolderExplicit(String)", new Object[] { name });
/*      */     }
/*      */     
/* 5465 */     if (name == null || name.length() <= 0) {
/* 5466 */       MQException traceRet1 = new MQException(2, 2442, this);
/*      */       
/* 5468 */       if (Trace.isOn) {
/* 5469 */         Trace.throwing(this, "com.ibm.mq.MQMessage", "makeMqFolderExplicit(String)", traceRet1);
/*      */       }
/* 5471 */       throw traceRet1;
/*      */     } 
/*      */     
/* 5474 */     String returnName = null;
/* 5475 */     if (name.equalsIgnoreCase("top") || name.equalsIgnoreCase("topicstring")) {
/* 5476 */       returnName = "mqps." + name;
/* 5477 */     } else if (name.indexOf(".") < 0) {
/* 5478 */       returnName = "mq." + name;
/*      */     } else {
/* 5480 */       returnName = name;
/*      */     } 
/* 5482 */     if (Trace.isOn) {
/* 5483 */       Trace.exit(this, "com.ibm.mq.MQMessage", "makeMqFolderExplicit(String)", returnName);
/*      */     }
/* 5485 */     return returnName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String removeExplicitUsrFolder(String name) throws MQException {
/* 5496 */     if (Trace.isOn) {
/* 5497 */       Trace.entry(this, "com.ibm.mq.MQMessage", "removeExplicitUsrFolder(String)", new Object[] { name });
/*      */     }
/*      */     
/* 5500 */     if (name == null || name.length() <= 0) {
/* 5501 */       MQException traceRet1 = new MQException(2, 2442, this);
/*      */       
/* 5503 */       if (Trace.isOn) {
/* 5504 */         Trace.throwing(this, "com.ibm.mq.MQMessage", "removeExplicitUsrFolder(String)", traceRet1);
/*      */       }
/*      */       
/* 5507 */       throw traceRet1;
/*      */     } 
/*      */     
/* 5510 */     String returnName = null;
/* 5511 */     if (name.startsWith("usr.")) {
/* 5512 */       returnName = name.substring("usr".length() + ".".length());
/*      */     } else {
/* 5514 */       returnName = name;
/*      */     } 
/* 5516 */     if (Trace.isOn) {
/* 5517 */       Trace.exit(this, "com.ibm.mq.MQMessage", "removeExplicitUsrFolder(String)", returnName);
/*      */     }
/* 5519 */     return returnName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void validatePropertyName(String name) throws MQException {
/* 5529 */     if (Trace.isOn) {
/* 5530 */       Trace.entry(this, "com.ibm.mq.MQMessage", "validatePropertyName(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/* 5534 */     if (!ALL_JMS_PROPERTY_NAMES.contains(name)) {
/*      */       
/* 5536 */       validatePropertyNameReservedJmsNames(name);
/*      */       
/* 5538 */       validatePropertyNameReservedJmsInternalNames(name);
/*      */       
/* 5540 */       validatePropertyNameReservedSqlNames(name);
/*      */       
/* 5542 */       validatePropertyNameReservedHierarchyNames(name);
/*      */       
/* 5544 */       validatePropertyNameJavaIdentifiers(name);
/*      */       
/* 5546 */       validatePropertyNameForNestedGroupsInReservedFolders(name);
/*      */       
/* 5548 */       validatePropertyNameSeperators(name);
/*      */       
/* 5550 */       validatePropertyNameMixedContent(name);
/*      */     } 
/*      */     
/* 5553 */     if (Trace.isOn) {
/* 5554 */       Trace.exit(this, "com.ibm.mq.MQMessage", "validatePropertyName(String)");
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
/*      */   private void validatePropertyNameReservedJmsNames(String name) throws MQException {
/* 5567 */     if (Trace.isOn) {
/* 5568 */       Trace.entry(this, "com.ibm.mq.MQMessage", "validatePropertyNameReservedJmsNames(String)", new Object[] { name });
/*      */     }
/*      */     
/* 5571 */     String propertyName = removeExplicitUsrFolder(name);
/* 5572 */     if (propertyName.startsWith("JMS") && 
/* 5573 */       !ALL_JMS_PROPERTY_NAMES.contains(propertyName)) {
/* 5574 */       MQException traceRet1 = new MQException(2, 2442, this);
/*      */       
/* 5576 */       if (Trace.isOn) {
/* 5577 */         Trace.throwing(this, "com.ibm.mq.MQMessage", "validatePropertyNameReservedJmsNames(String)", traceRet1);
/*      */       }
/*      */       
/* 5580 */       throw traceRet1;
/*      */     } 
/*      */     
/* 5583 */     if (Trace.isOn) {
/* 5584 */       Trace.exit(this, "com.ibm.mq.MQMessage", "validatePropertyNameReservedJmsNames(String)");
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
/*      */   private void validatePropertyNameReservedJmsInternalNames(String name) throws MQException {
/* 5597 */     if (Trace.isOn) {
/* 5598 */       Trace.entry(this, "com.ibm.mq.MQMessage", "validatePropertyNameReservedJmsInternalNames(String)", new Object[] { name });
/*      */     }
/*      */     
/* 5601 */     String propertyName = removeExplicitUsrFolder(name);
/* 5602 */     if (propertyName.startsWith("jms.") && 
/* 5603 */       !JMSPropertySynonyms.containsKey(propertyName)) {
/* 5604 */       MQException traceRet1 = new MQException(2, 2442, this);
/*      */       
/* 5606 */       if (Trace.isOn) {
/* 5607 */         Trace.throwing(this, "com.ibm.mq.MQMessage", "validatePropertyNameReservedJmsInternalNames(String)", traceRet1);
/*      */       }
/*      */       
/* 5610 */       throw traceRet1;
/*      */     } 
/*      */     
/* 5613 */     if (Trace.isOn) {
/* 5614 */       Trace.exit(this, "com.ibm.mq.MQMessage", "validatePropertyNameReservedJmsInternalNames(String)");
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
/*      */   private void validatePropertyNameReservedSqlNames(String name) throws MQException {
/* 5628 */     if (Trace.isOn) {
/* 5629 */       Trace.entry(this, "com.ibm.mq.MQMessage", "validatePropertyNameReservedSqlNames(String)", new Object[] { name });
/*      */     }
/*      */     
/* 5632 */     StringTokenizer tokenizer = new StringTokenizer(name, ".");
/* 5633 */     while (tokenizer.hasMoreTokens()) {
/* 5634 */       String currentToken = tokenizer.nextToken();
/* 5635 */       if (RESERVED_SQL_PROPERTY_NAMES.contains(currentToken.toUpperCase())) {
/* 5636 */         MQException traceRet1 = new MQException(2, 2442, this);
/*      */         
/* 5638 */         if (Trace.isOn) {
/* 5639 */           Trace.throwing(this, "com.ibm.mq.MQMessage", "validatePropertyNameReservedSqlNames(String)", traceRet1);
/*      */         }
/*      */         
/* 5642 */         throw traceRet1;
/*      */       } 
/*      */     } 
/* 5645 */     if (Trace.isOn) {
/* 5646 */       Trace.exit(this, "com.ibm.mq.MQMessage", "validatePropertyNameReservedSqlNames(String)");
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
/*      */   private void validatePropertyNameReservedHierarchyNames(String name) throws MQException {
/* 5658 */     if (Trace.isOn) {
/* 5659 */       Trace.entry(this, "com.ibm.mq.MQMessage", "validatePropertyNameReservedHierarchyNames(String)", new Object[] { name });
/*      */     }
/*      */     
/* 5662 */     if (!ALLOWED_HIERARCHY_PROPERTY_NAMES.contains(name)) {
/* 5663 */       for (String reservedHierarchyNamePrefix : RESERVED_HIERARCHY_PROPERTY_NAME_PREFIXES) {
/* 5664 */         if (name.startsWith(reservedHierarchyNamePrefix) && 
/* 5665 */           !name.startsWith("Root.MQMD.")) {
/* 5666 */           MQException traceRet1 = new MQException(2, 2442, this);
/*      */           
/* 5668 */           if (Trace.isOn) {
/* 5669 */             Trace.throwing(this, "com.ibm.mq.MQMessage", "validatePropertyNameReservedHierarchyNames(String)", traceRet1);
/*      */           }
/*      */           
/* 5672 */           throw traceRet1;
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/* 5677 */     if (Trace.isOn) {
/* 5678 */       Trace.exit(this, "com.ibm.mq.MQMessage", "validatePropertyNameReservedHierarchyNames(String)");
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
/*      */   private void validatePropertyNameJavaIdentifiers(String name) throws MQException {
/* 5691 */     if (Trace.isOn) {
/* 5692 */       Trace.entry(this, "com.ibm.mq.MQMessage", "validatePropertyNameJavaIdentifiers(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/* 5696 */     StringTokenizer tokenizer = new StringTokenizer(name, ".");
/* 5697 */     while (tokenizer.hasMoreTokens()) {
/* 5698 */       String currentToken = tokenizer.nextToken();
/*      */       
/* 5700 */       if (!Character.isJavaIdentifierStart(currentToken.charAt(0))) {
/* 5701 */         MQException traceRet1 = new MQException(2, 2442, this);
/*      */         
/* 5703 */         if (Trace.isOn) {
/* 5704 */           Trace.throwing(this, "com.ibm.mq.MQMessage", "validatePropertyNameJavaIdentifiers(String)", traceRet1, 1);
/*      */         }
/*      */         
/* 5707 */         throw traceRet1;
/*      */       } 
/*      */       
/* 5710 */       for (int i = 1; i < currentToken.length(); i++) {
/* 5711 */         if (!Character.isJavaIdentifierPart(currentToken.charAt(i))) {
/* 5712 */           MQException traceRet2 = new MQException(2, 2442, this);
/* 5713 */           if (Trace.isOn) {
/* 5714 */             Trace.throwing(this, "com.ibm.mq.MQMessage", "validatePropertyNameJavaIdentifiers(String)", traceRet2, 2);
/*      */           }
/*      */           
/* 5717 */           throw traceRet2;
/*      */         } 
/*      */       } 
/*      */     } 
/* 5721 */     if (Trace.isOn) {
/* 5722 */       Trace.exit(this, "com.ibm.mq.MQMessage", "validatePropertyNameJavaIdentifiers(String)");
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
/*      */   private void validatePropertyNameForNestedGroupsInReservedFolders(String name) throws MQException {
/* 5734 */     if (Trace.isOn) {
/* 5735 */       Trace.entry(this, "com.ibm.mq.MQMessage", "validatePropertyNameForNestedGroupsInReservedFolders(String)", new Object[] { name });
/*      */     }
/*      */     
/* 5738 */     String folder = getPropertyFolder(name);
/* 5739 */     for (String currentRestrictedHierarchyFolderName : RESTRICTED_HIERARCHY_FOLDER_NAMES) {
/* 5740 */       if (folder.startsWith(currentRestrictedHierarchyFolderName)) {
/* 5741 */         int hierarchyDepth = (new StringTokenizer(name, ".")).countTokens() - 1;
/* 5742 */         if (hierarchyDepth > 1) {
/* 5743 */           MQException traceRet1 = new MQException(2, 2442, this);
/*      */           
/* 5745 */           if (Trace.isOn) {
/* 5746 */             Trace.throwing(this, "com.ibm.mq.MQMessage", "validatePropertyNameForNestedGroupsInReservedFolders(String)", traceRet1);
/*      */           }
/*      */           
/* 5749 */           throw traceRet1;
/*      */         } 
/*      */       } 
/*      */     } 
/* 5753 */     if (Trace.isOn) {
/* 5754 */       Trace.exit(this, "com.ibm.mq.MQMessage", "validatePropertyNameForNestedGroupsInReservedFolders(String)");
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
/*      */   private void validatePropertyNameSeperators(String name) throws MQException {
/* 5767 */     if (Trace.isOn) {
/* 5768 */       Trace.entry(this, "com.ibm.mq.MQMessage", "validatePropertyNameSeperators(String)", new Object[] { name });
/*      */     }
/*      */     
/* 5771 */     if (name.indexOf("..") >= 0 || name.startsWith(".") || name
/* 5772 */       .endsWith(".")) {
/* 5773 */       MQException traceRet1 = new MQException(2, 2442, this);
/*      */       
/* 5775 */       if (Trace.isOn) {
/* 5776 */         Trace.throwing(this, "com.ibm.mq.MQMessage", "validatePropertyNameSeperators(String)", traceRet1);
/*      */       }
/*      */       
/* 5779 */       throw traceRet1;
/*      */     } 
/* 5781 */     if (Trace.isOn) {
/* 5782 */       Trace.exit(this, "com.ibm.mq.MQMessage", "validatePropertyNameSeperators(String)");
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
/*      */   private void validatePropertyNameMixedContent(String name) throws MQException {
/* 5794 */     if (Trace.isOn) {
/* 5795 */       Trace.entry(this, "com.ibm.mq.MQMessage", "validatePropertyNameMixedContent(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/* 5799 */     for (String currentProperty : this.properties.keySet()) {
/* 5800 */       if (!name.equalsIgnoreCase(currentProperty)) {
/*      */         
/* 5802 */         String newPropertyAsGroup = name + ".";
/*      */         
/* 5804 */         String newPropertyOwningGroup = name.substring(0, name.lastIndexOf("."));
/* 5805 */         if (currentProperty.startsWith(newPropertyAsGroup) || currentProperty.equals(newPropertyOwningGroup)) {
/* 5806 */           MQException traceRet1 = new MQException(2, 2498, this);
/*      */           
/* 5808 */           if (Trace.isOn) {
/* 5809 */             Trace.throwing(this, "com.ibm.mq.MQMessage", "validatePropertyNameMixedContent(String)", traceRet1);
/*      */           }
/*      */           
/* 5812 */           throw traceRet1;
/*      */         } 
/*      */       } 
/*      */     } 
/* 5816 */     if (Trace.isOn) {
/* 5817 */       Trace.exit(this, "com.ibm.mq.MQMessage", "validatePropertyNameMixedContent(String)");
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
/*      */   private Object getProperty(String name, MQPropertyDescriptor descriptor) throws MQException {
/* 5832 */     if (Trace.isOn) {
/* 5833 */       Trace.entry(this, "com.ibm.mq.MQMessage", "getProperty(String,MQPropertyDescriptor)", new Object[] { name, descriptor });
/*      */     }
/*      */     
/* 5836 */     String propertyName = name;
/* 5837 */     Object result = null;
/* 5838 */     Vector<Object> valueList = null;
/* 5839 */     MQPropertyDescriptor pd = null;
/*      */     
/* 5841 */     if (propertyName == null) {
/* 5842 */       MQException traceRet1 = new MQException(2, 2471, this);
/*      */       
/* 5844 */       if (Trace.isOn) {
/* 5845 */         Trace.throwing(this, "com.ibm.mq.MQMessage", "getProperty(String,MQPropertyDescriptor)", traceRet1, 1);
/*      */       }
/*      */       
/* 5848 */       throw traceRet1;
/*      */     } 
/*      */     
/* 5851 */     if (propertyName.startsWith("Root.MQMD.")) {
/*      */       
/* 5853 */       result = getMQMDProperty(propertyName.substring("Root.MQMD.".length()));
/* 5854 */     } else if (ALL_JMS_PROPERTY_NAMES.contains(propertyName)) {
/* 5855 */       result = getJmsProperty(propertyName);
/* 5856 */     } else if (propertyName.toLowerCase().startsWith("mq")) {
/* 5857 */       propertyName = makeMqFolderExplicit(propertyName);
/* 5858 */     } else if (!TYPE_MCD_ELEMENTS.contains(propertyName)) {
/*      */       
/* 5860 */       if (!JMSPropertySynonyms.containsKey(propertyName)) {
/*      */ 
/*      */ 
/*      */         
/* 5864 */         propertyName = makeUsrFolderExplicit(propertyName);
/*      */ 
/*      */         
/* 5867 */         if (!this.properties.containsKey(propertyName)) {
/* 5868 */           MQException traceRet1 = new MQException(2, 2471, this);
/* 5869 */           if (Trace.isOn) {
/* 5870 */             Trace.throwing(this, "com.ibm.mq.MQMessage", "getProperty(String,MQPropertyDescriptor)", traceRet1, 2);
/*      */           }
/*      */           
/* 5873 */           throw traceRet1;
/*      */         } 
/*      */       } 
/*      */     } 
/* 5877 */     valueList = this.properties.get(propertyName);
/* 5878 */     if (valueList != null && valueList.size() > 0) {
/* 5879 */       result = valueList.elementAt(0);
/* 5880 */       valueList.remove(result);
/* 5881 */       valueList.addElement(result);
/*      */     } 
/*      */     
/* 5884 */     if (this.propertyDescTable.containsKey(propertyName)) {
/* 5885 */       Vector<MQPropertyDescriptor> pdList = this.propertyDescTable.get(propertyName);
/* 5886 */       if (pdList != null && pdList.size() > 0) {
/* 5887 */         pd = pdList.elementAt(0);
/* 5888 */         if (pdList.size() > 1) {
/* 5889 */           pdList.remove(descriptor);
/* 5890 */           pdList.addElement(descriptor);
/*      */         } 
/* 5892 */         if (descriptor != null) {
/* 5893 */           descriptor.context = pd.context;
/* 5894 */           descriptor.copyOptions = pd.copyOptions;
/* 5895 */           descriptor.support = pd.support;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 5900 */     if (Trace.isOn) {
/* 5901 */       Trace.exit(this, "com.ibm.mq.MQMessage", "getProperty(String,MQPropertyDescriptor)", result);
/*      */     }
/*      */     
/* 5904 */     return result;
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
/*      */   public void setBooleanProperty(String name, boolean value) throws MQException {
/* 5916 */     if (Trace.isOn) {
/* 5917 */       Trace.entry(this, "com.ibm.mq.MQMessage", "setBooleanProperty(String,boolean)", new Object[] { name, 
/* 5918 */             Boolean.valueOf(value) });
/*      */     }
/* 5920 */     setBooleanProperty(name, (MQPropertyDescriptor)null, value);
/*      */     
/* 5922 */     if (Trace.isOn) {
/* 5923 */       Trace.exit(this, "com.ibm.mq.MQMessage", "setBooleanProperty(String,boolean)");
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
/*      */   public void setBooleanProperty(String name, MQPropertyDescriptor descriptor, boolean value) throws MQException {
/* 5938 */     if (Trace.isOn) {
/* 5939 */       Trace.entry(this, "com.ibm.mq.MQMessage", "setBooleanProperty(String,MQPropertyDescriptor,boolean)", new Object[] { name, descriptor, 
/*      */             
/* 5941 */             Boolean.valueOf(value) });
/*      */     }
/* 5943 */     setProperty(name, descriptor, Boolean.valueOf(value));
/*      */     
/* 5945 */     if (Trace.isOn) {
/* 5946 */       Trace.exit(this, "com.ibm.mq.MQMessage", "setBooleanProperty(String,MQPropertyDescriptor,boolean)");
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
/*      */   public void setBytesProperty(String name, byte[] value) throws MQException {
/* 5961 */     if (Trace.isOn) {
/* 5962 */       Trace.entry(this, "com.ibm.mq.MQMessage", "setBytesProperty(String,byte [ ])", new Object[] { name, value });
/*      */     }
/*      */     
/* 5965 */     setBytesProperty(name, (MQPropertyDescriptor)null, value);
/*      */     
/* 5967 */     if (Trace.isOn) {
/* 5968 */       Trace.exit(this, "com.ibm.mq.MQMessage", "setBytesProperty(String,byte [ ])");
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
/*      */   public void setBytesProperty(String name, MQPropertyDescriptor descriptor, byte[] value) throws MQException {
/* 5983 */     if (Trace.isOn) {
/* 5984 */       Trace.entry(this, "com.ibm.mq.MQMessage", "setBytesProperty(String,MQPropertyDescriptor,byte [ ])", new Object[] { name, descriptor, value });
/*      */     }
/*      */ 
/*      */     
/* 5988 */     setProperty(name, descriptor, value);
/*      */     
/* 5990 */     if (Trace.isOn) {
/* 5991 */       Trace.exit(this, "com.ibm.mq.MQMessage", "setBytesProperty(String,MQPropertyDescriptor,byte [ ])");
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
/*      */   public void setByteProperty(String name, byte value) throws MQException {
/* 6006 */     if (Trace.isOn) {
/* 6007 */       Trace.entry(this, "com.ibm.mq.MQMessage", "setByteProperty(String,byte)", new Object[] { name, 
/* 6008 */             Byte.valueOf(value) });
/*      */     }
/* 6010 */     setByteProperty(name, (MQPropertyDescriptor)null, value);
/*      */     
/* 6012 */     if (Trace.isOn) {
/* 6013 */       Trace.exit(this, "com.ibm.mq.MQMessage", "setByteProperty(String,byte)");
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
/*      */   public void setByteProperty(String name, MQPropertyDescriptor descriptor, byte value) throws MQException {
/* 6028 */     if (Trace.isOn) {
/* 6029 */       Trace.entry(this, "com.ibm.mq.MQMessage", "setByteProperty(String,MQPropertyDescriptor,byte)", new Object[] { name, descriptor, 
/*      */             
/* 6031 */             Byte.valueOf(value) });
/*      */     }
/* 6033 */     setProperty(name, descriptor, Byte.valueOf(value));
/*      */     
/* 6035 */     if (Trace.isOn) {
/* 6036 */       Trace.exit(this, "com.ibm.mq.MQMessage", "setByteProperty(String,MQPropertyDescriptor,byte)");
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
/*      */   public void setShortProperty(String name, short value) throws MQException {
/* 6051 */     if (Trace.isOn) {
/* 6052 */       Trace.entry(this, "com.ibm.mq.MQMessage", "setShortProperty(String,short)", new Object[] { name, 
/* 6053 */             Short.valueOf(value) });
/*      */     }
/* 6055 */     setShortProperty(name, (MQPropertyDescriptor)null, value);
/*      */     
/* 6057 */     if (Trace.isOn) {
/* 6058 */       Trace.exit(this, "com.ibm.mq.MQMessage", "setShortProperty(String,short)");
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
/*      */   public void setShortProperty(String name, MQPropertyDescriptor descriptor, short value) throws MQException {
/* 6073 */     if (Trace.isOn) {
/* 6074 */       Trace.entry(this, "com.ibm.mq.MQMessage", "setShortProperty(String,MQPropertyDescriptor,short)", new Object[] { name, descriptor, 
/*      */             
/* 6076 */             Short.valueOf(value) });
/*      */     }
/* 6078 */     setProperty(name, descriptor, Short.valueOf(value));
/*      */     
/* 6080 */     if (Trace.isOn) {
/* 6081 */       Trace.exit(this, "com.ibm.mq.MQMessage", "setShortProperty(String,MQPropertyDescriptor,short)");
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
/*      */   public void setInt2Property(String name, short value) throws MQException {
/* 6096 */     if (Trace.isOn) {
/* 6097 */       Trace.entry(this, "com.ibm.mq.MQMessage", "setInt2Property(String,short)", new Object[] { name, 
/* 6098 */             Short.valueOf(value) });
/*      */     }
/* 6100 */     setShortProperty(name, value);
/*      */     
/* 6102 */     if (Trace.isOn) {
/* 6103 */       Trace.exit(this, "com.ibm.mq.MQMessage", "setInt2Property(String,short)");
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
/*      */   public void setInt2Property(String name, MQPropertyDescriptor descriptor, short value) throws MQException {
/* 6118 */     if (Trace.isOn) {
/* 6119 */       Trace.entry(this, "com.ibm.mq.MQMessage", "setInt2Property(String,MQPropertyDescriptor,short)", new Object[] { name, descriptor, 
/*      */             
/* 6121 */             Short.valueOf(value) });
/*      */     }
/* 6123 */     setShortProperty(name, descriptor, value);
/*      */     
/* 6125 */     if (Trace.isOn) {
/* 6126 */       Trace.exit(this, "com.ibm.mq.MQMessage", "setInt2Property(String,MQPropertyDescriptor,short)");
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
/*      */   public void setIntProperty(String name, int value) throws MQException {
/* 6141 */     if (Trace.isOn) {
/* 6142 */       Trace.entry(this, "com.ibm.mq.MQMessage", "setIntProperty(String,int)", new Object[] { name, 
/* 6143 */             Integer.valueOf(value) });
/*      */     }
/* 6145 */     setIntProperty(name, (MQPropertyDescriptor)null, value);
/*      */     
/* 6147 */     if (Trace.isOn) {
/* 6148 */       Trace.exit(this, "com.ibm.mq.MQMessage", "setIntProperty(String,int)");
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
/*      */   public void setIntProperty(String name, MQPropertyDescriptor descriptor, int value) throws MQException {
/* 6163 */     if (Trace.isOn) {
/* 6164 */       Trace.entry(this, "com.ibm.mq.MQMessage", "setIntProperty(String,MQPropertyDescriptor,int)", new Object[] { name, descriptor, 
/* 6165 */             Integer.valueOf(value) });
/*      */     }
/* 6167 */     setProperty(name, descriptor, Integer.valueOf(value));
/*      */     
/* 6169 */     if (Trace.isOn) {
/* 6170 */       Trace.exit(this, "com.ibm.mq.MQMessage", "setIntProperty(String,MQPropertyDescriptor,int)");
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
/*      */   public void setInt4Property(String name, int value) throws MQException {
/* 6184 */     if (Trace.isOn) {
/* 6185 */       Trace.entry(this, "com.ibm.mq.MQMessage", "setInt4Property(String,int)", new Object[] { name, 
/* 6186 */             Integer.valueOf(value) });
/*      */     }
/* 6188 */     setIntProperty(name, value);
/*      */     
/* 6190 */     if (Trace.isOn) {
/* 6191 */       Trace.exit(this, "com.ibm.mq.MQMessage", "setInt4Property(String,int)");
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
/*      */   public void setInt4Property(String name, MQPropertyDescriptor descriptor, int value) throws MQException {
/* 6206 */     if (Trace.isOn) {
/* 6207 */       Trace.entry(this, "com.ibm.mq.MQMessage", "setInt4Property(String,MQPropertyDescriptor,int)", new Object[] { name, descriptor, 
/* 6208 */             Integer.valueOf(value) });
/*      */     }
/* 6210 */     setIntProperty(name, descriptor, value);
/*      */     
/* 6212 */     if (Trace.isOn) {
/* 6213 */       Trace.exit(this, "com.ibm.mq.MQMessage", "setInt4Property(String,MQPropertyDescriptor,int)");
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
/*      */   public void setLongProperty(String name, long value) throws MQException {
/* 6228 */     if (Trace.isOn) {
/* 6229 */       Trace.entry(this, "com.ibm.mq.MQMessage", "setLongProperty(String,long)", new Object[] { name, 
/* 6230 */             Long.valueOf(value) });
/*      */     }
/* 6232 */     setLongProperty(name, (MQPropertyDescriptor)null, value);
/*      */     
/* 6234 */     if (Trace.isOn) {
/* 6235 */       Trace.exit(this, "com.ibm.mq.MQMessage", "setLongProperty(String,long)");
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
/*      */   public void setLongProperty(String name, MQPropertyDescriptor descriptor, long value) throws MQException {
/* 6250 */     if (Trace.isOn) {
/* 6251 */       Trace.entry(this, "com.ibm.mq.MQMessage", "setLongProperty(String,MQPropertyDescriptor,long)", new Object[] { name, descriptor, 
/*      */             
/* 6253 */             Long.valueOf(value) });
/*      */     }
/* 6255 */     setProperty(name, descriptor, Long.valueOf(value));
/*      */     
/* 6257 */     if (Trace.isOn) {
/* 6258 */       Trace.exit(this, "com.ibm.mq.MQMessage", "setLongProperty(String,MQPropertyDescriptor,long)");
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
/*      */   public void setInt8Property(String name, long value) throws MQException {
/* 6273 */     if (Trace.isOn) {
/* 6274 */       Trace.entry(this, "com.ibm.mq.MQMessage", "setInt8Property(String,long)", new Object[] { name, 
/* 6275 */             Long.valueOf(value) });
/*      */     }
/* 6277 */     setLongProperty(name, value);
/*      */     
/* 6279 */     if (Trace.isOn) {
/* 6280 */       Trace.exit(this, "com.ibm.mq.MQMessage", "setInt8Property(String,long)");
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
/*      */   public void setInt8Property(String name, MQPropertyDescriptor descriptor, long value) throws MQException {
/* 6295 */     if (Trace.isOn) {
/* 6296 */       Trace.entry(this, "com.ibm.mq.MQMessage", "setInt8Property(String,MQPropertyDescriptor,long)", new Object[] { name, descriptor, 
/*      */             
/* 6298 */             Long.valueOf(value) });
/*      */     }
/* 6300 */     setLongProperty(name, descriptor, value);
/*      */     
/* 6302 */     if (Trace.isOn) {
/* 6303 */       Trace.exit(this, "com.ibm.mq.MQMessage", "setInt8Property(String,MQPropertyDescriptor,long)");
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
/*      */   public void setFloatProperty(String name, float value) throws MQException {
/* 6318 */     if (Trace.isOn) {
/* 6319 */       Trace.entry(this, "com.ibm.mq.MQMessage", "setFloatProperty(String,float)", new Object[] { name, 
/* 6320 */             Float.valueOf(value) });
/*      */     }
/* 6322 */     setFloatProperty(name, (MQPropertyDescriptor)null, value);
/*      */     
/* 6324 */     if (Trace.isOn) {
/* 6325 */       Trace.exit(this, "com.ibm.mq.MQMessage", "setFloatProperty(String,float)");
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
/*      */   public void setFloatProperty(String name, MQPropertyDescriptor descriptor, float value) throws MQException {
/* 6340 */     if (Trace.isOn) {
/* 6341 */       Trace.entry(this, "com.ibm.mq.MQMessage", "setFloatProperty(String,MQPropertyDescriptor,float)", new Object[] { name, descriptor, 
/*      */             
/* 6343 */             Float.valueOf(value) });
/*      */     }
/* 6345 */     setProperty(name, descriptor, Float.valueOf(value));
/*      */     
/* 6347 */     if (Trace.isOn) {
/* 6348 */       Trace.exit(this, "com.ibm.mq.MQMessage", "setFloatProperty(String,MQPropertyDescriptor,float)");
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
/*      */   public void setDoubleProperty(String name, double value) throws MQException {
/* 6363 */     if (Trace.isOn) {
/* 6364 */       Trace.entry(this, "com.ibm.mq.MQMessage", "setDoubleProperty(String,double)", new Object[] { name, 
/* 6365 */             Double.valueOf(value) });
/*      */     }
/* 6367 */     setDoubleProperty(name, (MQPropertyDescriptor)null, value);
/*      */     
/* 6369 */     if (Trace.isOn) {
/* 6370 */       Trace.exit(this, "com.ibm.mq.MQMessage", "setDoubleProperty(String,double)");
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
/*      */   public void setDoubleProperty(String name, MQPropertyDescriptor descriptor, double value) throws MQException {
/* 6385 */     if (Trace.isOn) {
/* 6386 */       Trace.entry(this, "com.ibm.mq.MQMessage", "setDoubleProperty(String,MQPropertyDescriptor,double)", new Object[] { name, descriptor, 
/*      */             
/* 6388 */             Double.valueOf(value) });
/*      */     }
/* 6390 */     setProperty(name, descriptor, Double.valueOf(value));
/*      */     
/* 6392 */     if (Trace.isOn) {
/* 6393 */       Trace.exit(this, "com.ibm.mq.MQMessage", "setDoubleProperty(String,MQPropertyDescriptor,double)");
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
/*      */   public void setStringProperty(String name, String value) throws MQException {
/* 6408 */     if (Trace.isOn) {
/* 6409 */       Trace.entry(this, "com.ibm.mq.MQMessage", "setStringProperty(String,String)", new Object[] { name, value });
/*      */     }
/*      */     
/* 6412 */     setStringProperty(name, (MQPropertyDescriptor)null, value);
/*      */     
/* 6414 */     if (Trace.isOn) {
/* 6415 */       Trace.exit(this, "com.ibm.mq.MQMessage", "setStringProperty(String,String)");
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
/*      */   public void setStringProperty(String name, MQPropertyDescriptor descriptor, String value) throws MQException {
/* 6430 */     if (Trace.isOn) {
/* 6431 */       Trace.entry(this, "com.ibm.mq.MQMessage", "setStringProperty(String,MQPropertyDescriptor,String)", new Object[] { name, descriptor, value });
/*      */     }
/*      */ 
/*      */     
/* 6435 */     setProperty(name, descriptor, value);
/*      */     
/* 6437 */     if (Trace.isOn) {
/* 6438 */       Trace.exit(this, "com.ibm.mq.MQMessage", "setStringProperty(String,MQPropertyDescriptor,String)");
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
/*      */   public void setObjectProperty(String name, Object value) throws MQException {
/* 6458 */     if (Trace.isOn) {
/* 6459 */       Trace.entry(this, "com.ibm.mq.MQMessage", "setObjectProperty(String,Object)", new Object[] { name, value });
/*      */     }
/*      */     
/* 6462 */     setObjectProperty(name, (MQPropertyDescriptor)null, value);
/*      */     
/* 6464 */     if (Trace.isOn) {
/* 6465 */       Trace.exit(this, "com.ibm.mq.MQMessage", "setObjectProperty(String,Object)");
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
/*      */   public void setObjectProperty(String name, MQPropertyDescriptor descriptor, Object value) throws MQException {
/* 6485 */     if (Trace.isOn) {
/* 6486 */       Trace.entry(this, "com.ibm.mq.MQMessage", "setObjectProperty(String,MQPropertyDescriptor,Object)", new Object[] { name, descriptor, value });
/*      */     }
/*      */ 
/*      */     
/* 6490 */     setProperty(name, descriptor, value);
/*      */     
/* 6492 */     if (Trace.isOn) {
/* 6493 */       Trace.exit(this, "com.ibm.mq.MQMessage", "setObjectProperty(String,MQPropertyDescriptor,Object)");
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
/*      */   public boolean getBooleanProperty(String name) throws MQException {
/* 6508 */     if (Trace.isOn) {
/* 6509 */       Trace.entry(this, "com.ibm.mq.MQMessage", "getBooleanProperty(String)", new Object[] { name });
/*      */     }
/*      */     
/* 6512 */     boolean traceRet1 = getBooleanProperty(name, (MQPropertyDescriptor)null);
/*      */     
/* 6514 */     if (Trace.isOn) {
/* 6515 */       Trace.exit(this, "com.ibm.mq.MQMessage", "getBooleanProperty(String)", 
/* 6516 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 6518 */     return traceRet1;
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
/*      */   public boolean getBooleanProperty(String name, MQPropertyDescriptor descriptor) throws MQException {
/*      */     boolean result;
/* 6532 */     if (Trace.isOn) {
/* 6533 */       Trace.entry(this, "com.ibm.mq.MQMessage", "getBooleanProperty(String,MQPropertyDescriptor)", new Object[] { name, descriptor });
/*      */     }
/*      */ 
/*      */     
/* 6537 */     Object resultObject = getProperty(name, descriptor);
/* 6538 */     if (resultObject instanceof Boolean) {
/* 6539 */       result = ((Boolean)resultObject).booleanValue();
/*      */     } else {
/* 6541 */       MQException traceRet1 = new MQException(2, 2473, this);
/*      */       
/* 6543 */       if (Trace.isOn) {
/* 6544 */         Trace.throwing(this, "com.ibm.mq.MQMessage", "getBooleanProperty(String,MQPropertyDescriptor)", traceRet1);
/*      */       }
/*      */       
/* 6547 */       throw traceRet1;
/*      */     } 
/* 6549 */     if (Trace.isOn) {
/* 6550 */       Trace.exit(this, "com.ibm.mq.MQMessage", "getBooleanProperty(String,MQPropertyDescriptor)", 
/* 6551 */           Boolean.valueOf(result));
/*      */     }
/* 6553 */     return result;
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
/*      */   public byte[] getBytesProperty(String name) throws MQException {
/* 6565 */     if (Trace.isOn) {
/* 6566 */       Trace.entry(this, "com.ibm.mq.MQMessage", "getBytesProperty(String)", new Object[] { name });
/*      */     }
/* 6568 */     byte[] traceRet1 = getBytesProperty(name, (MQPropertyDescriptor)null);
/*      */     
/* 6570 */     if (Trace.isOn) {
/* 6571 */       Trace.exit(this, "com.ibm.mq.MQMessage", "getBytesProperty(String)", traceRet1);
/*      */     }
/* 6573 */     return traceRet1;
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
/*      */   public byte[] getBytesProperty(String name, MQPropertyDescriptor descriptor) throws MQException {
/*      */     byte[] result;
/* 6587 */     if (Trace.isOn) {
/* 6588 */       Trace.entry(this, "com.ibm.mq.MQMessage", "getBytesProperty(String,MQPropertyDescriptor)", new Object[] { name, descriptor });
/*      */     }
/*      */ 
/*      */     
/* 6592 */     Object resultObject = getProperty(name, descriptor);
/* 6593 */     if (resultObject == null) {
/* 6594 */       result = null;
/* 6595 */     } else if (resultObject instanceof byte[]) {
/* 6596 */       result = (byte[])resultObject;
/*      */     } else {
/* 6598 */       MQException traceRet1 = new MQException(2, 2473, this);
/*      */       
/* 6600 */       if (Trace.isOn) {
/* 6601 */         Trace.throwing(this, "com.ibm.mq.MQMessage", "getBytesProperty(String,MQPropertyDescriptor)", traceRet1);
/*      */       }
/*      */       
/* 6604 */       throw traceRet1;
/*      */     } 
/* 6606 */     if (Trace.isOn) {
/* 6607 */       Trace.exit(this, "com.ibm.mq.MQMessage", "getBytesProperty(String,MQPropertyDescriptor)", result);
/*      */     }
/*      */     
/* 6610 */     return result;
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
/*      */   public byte getByteProperty(String name) throws MQException {
/* 6622 */     if (Trace.isOn) {
/* 6623 */       Trace.entry(this, "com.ibm.mq.MQMessage", "getByteProperty(String)", new Object[] { name });
/*      */     }
/* 6625 */     byte traceRet1 = getByteProperty(name, (MQPropertyDescriptor)null);
/*      */     
/* 6627 */     if (Trace.isOn) {
/* 6628 */       Trace.exit(this, "com.ibm.mq.MQMessage", "getByteProperty(String)", Byte.valueOf(traceRet1));
/*      */     }
/*      */     
/* 6631 */     return traceRet1;
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
/*      */   public byte getByteProperty(String name, MQPropertyDescriptor descriptor) throws MQException {
/*      */     byte result;
/* 6645 */     if (Trace.isOn) {
/* 6646 */       Trace.entry(this, "com.ibm.mq.MQMessage", "getByteProperty(String,MQPropertyDescriptor)", new Object[] { name, descriptor });
/*      */     }
/*      */ 
/*      */     
/* 6650 */     Object resultObject = getProperty(name, descriptor);
/* 6651 */     if (resultObject instanceof Byte) {
/* 6652 */       result = ((Byte)resultObject).byteValue();
/*      */     } else {
/* 6654 */       MQException traceRet1 = new MQException(2, 2473, this);
/*      */       
/* 6656 */       if (Trace.isOn) {
/* 6657 */         Trace.throwing(this, "com.ibm.mq.MQMessage", "getByteProperty(String,MQPropertyDescriptor)", traceRet1);
/*      */       }
/*      */       
/* 6660 */       throw traceRet1;
/*      */     } 
/* 6662 */     if (Trace.isOn) {
/* 6663 */       Trace.exit(this, "com.ibm.mq.MQMessage", "getByteProperty(String,MQPropertyDescriptor)", 
/* 6664 */           Byte.valueOf(result));
/*      */     }
/* 6666 */     return result;
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
/*      */   public short getShortProperty(String name) throws MQException {
/* 6678 */     if (Trace.isOn) {
/* 6679 */       Trace.entry(this, "com.ibm.mq.MQMessage", "getShortProperty(String)", new Object[] { name });
/*      */     }
/* 6681 */     short traceRet1 = getShortProperty(name, (MQPropertyDescriptor)null);
/*      */     
/* 6683 */     if (Trace.isOn) {
/* 6684 */       Trace.exit(this, "com.ibm.mq.MQMessage", "getShortProperty(String)", 
/* 6685 */           Short.valueOf(traceRet1));
/*      */     }
/* 6687 */     return traceRet1;
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
/*      */   public short getShortProperty(String name, MQPropertyDescriptor descriptor) throws MQException {
/*      */     short result;
/* 6701 */     if (Trace.isOn) {
/* 6702 */       Trace.entry(this, "com.ibm.mq.MQMessage", "getShortProperty(String,MQPropertyDescriptor)", new Object[] { name, descriptor });
/*      */     }
/*      */ 
/*      */     
/* 6706 */     Object resultObject = getProperty(name, descriptor);
/* 6707 */     if (resultObject instanceof Short) {
/* 6708 */       result = ((Short)resultObject).shortValue();
/*      */     } else {
/* 6710 */       MQException traceRet1 = new MQException(2, 2473, this);
/*      */       
/* 6712 */       if (Trace.isOn) {
/* 6713 */         Trace.throwing(this, "com.ibm.mq.MQMessage", "getShortProperty(String,MQPropertyDescriptor)", traceRet1);
/*      */       }
/*      */       
/* 6716 */       throw traceRet1;
/*      */     } 
/* 6718 */     if (Trace.isOn) {
/* 6719 */       Trace.exit(this, "com.ibm.mq.MQMessage", "getShortProperty(String,MQPropertyDescriptor)", 
/* 6720 */           Short.valueOf(result));
/*      */     }
/* 6722 */     return result;
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
/*      */   public short getInt2Property(String name) throws MQException {
/* 6735 */     if (Trace.isOn) {
/* 6736 */       Trace.entry(this, "com.ibm.mq.MQMessage", "getInt2Property(String)", new Object[] { name });
/*      */     }
/* 6738 */     short traceRet1 = getShortProperty(name);
/*      */     
/* 6740 */     if (Trace.isOn) {
/* 6741 */       Trace.exit(this, "com.ibm.mq.MQMessage", "getInt2Property(String)", Short.valueOf(traceRet1));
/*      */     }
/*      */     
/* 6744 */     return traceRet1;
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
/*      */   public short getInt2Property(String name, MQPropertyDescriptor descriptor) throws MQException {
/* 6758 */     if (Trace.isOn) {
/* 6759 */       Trace.entry(this, "com.ibm.mq.MQMessage", "getInt2Property(String,MQPropertyDescriptor)", new Object[] { name, descriptor });
/*      */     }
/*      */     
/* 6762 */     short traceRet1 = getShortProperty(name, descriptor);
/*      */     
/* 6764 */     if (Trace.isOn) {
/* 6765 */       Trace.exit(this, "com.ibm.mq.MQMessage", "getInt2Property(String,MQPropertyDescriptor)", 
/* 6766 */           Short.valueOf(traceRet1));
/*      */     }
/* 6768 */     return traceRet1;
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
/*      */   public int getIntProperty(String name) throws MQException {
/* 6780 */     if (Trace.isOn) {
/* 6781 */       Trace.entry(this, "com.ibm.mq.MQMessage", "getIntProperty(String)", new Object[] { name });
/*      */     }
/* 6783 */     int traceRet1 = getIntProperty(name, (MQPropertyDescriptor)null);
/*      */     
/* 6785 */     if (Trace.isOn) {
/* 6786 */       Trace.exit(this, "com.ibm.mq.MQMessage", "getIntProperty(String)", 
/* 6787 */           Integer.valueOf(traceRet1));
/*      */     }
/* 6789 */     return traceRet1;
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
/*      */   public int getIntProperty(String name, MQPropertyDescriptor descriptor) throws MQException {
/*      */     int result;
/* 6803 */     if (Trace.isOn) {
/* 6804 */       Trace.entry(this, "com.ibm.mq.MQMessage", "getIntProperty(String,MQPropertyDescriptor)", new Object[] { name, descriptor });
/*      */     }
/*      */ 
/*      */     
/* 6808 */     Object resultObject = getProperty(name, descriptor);
/* 6809 */     if (resultObject instanceof Integer) {
/* 6810 */       result = ((Integer)resultObject).intValue();
/*      */     } else {
/* 6812 */       MQException traceRet1 = new MQException(2, 2473, this);
/*      */       
/* 6814 */       if (Trace.isOn) {
/* 6815 */         Trace.throwing(this, "com.ibm.mq.MQMessage", "getIntProperty(String,MQPropertyDescriptor)", traceRet1);
/*      */       }
/*      */       
/* 6818 */       throw traceRet1;
/*      */     } 
/* 6820 */     if (Trace.isOn) {
/* 6821 */       Trace.exit(this, "com.ibm.mq.MQMessage", "getIntProperty(String,MQPropertyDescriptor)", 
/* 6822 */           Integer.valueOf(result));
/*      */     }
/* 6824 */     return result;
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
/*      */   public int getInt4Property(String name) throws MQException {
/* 6837 */     if (Trace.isOn) {
/* 6838 */       Trace.entry(this, "com.ibm.mq.MQMessage", "getInt4Property(String)", new Object[] { name });
/*      */     }
/* 6840 */     int traceRet1 = getIntProperty(name);
/*      */     
/* 6842 */     if (Trace.isOn) {
/* 6843 */       Trace.exit(this, "com.ibm.mq.MQMessage", "getInt4Property(String)", 
/* 6844 */           Integer.valueOf(traceRet1));
/*      */     }
/* 6846 */     return traceRet1;
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
/*      */   public int getInt4Property(String name, MQPropertyDescriptor descriptor) throws MQException {
/* 6860 */     if (Trace.isOn) {
/* 6861 */       Trace.entry(this, "com.ibm.mq.MQMessage", "getInt4Property(String,MQPropertyDescriptor)", new Object[] { name, descriptor });
/*      */     }
/*      */     
/* 6864 */     int traceRet1 = getIntProperty(name, descriptor);
/*      */     
/* 6866 */     if (Trace.isOn) {
/* 6867 */       Trace.exit(this, "com.ibm.mq.MQMessage", "getInt4Property(String,MQPropertyDescriptor)", 
/* 6868 */           Integer.valueOf(traceRet1));
/*      */     }
/* 6870 */     return traceRet1;
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
/*      */   public long getLongProperty(String name) throws MQException {
/* 6882 */     if (Trace.isOn) {
/* 6883 */       Trace.entry(this, "com.ibm.mq.MQMessage", "getLongProperty(String)", new Object[] { name });
/*      */     }
/* 6885 */     long traceRet1 = getLongProperty(name, (MQPropertyDescriptor)null);
/*      */     
/* 6887 */     if (Trace.isOn) {
/* 6888 */       Trace.exit(this, "com.ibm.mq.MQMessage", "getLongProperty(String)", Long.valueOf(traceRet1));
/*      */     }
/*      */     
/* 6891 */     return traceRet1;
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
/*      */   public long getLongProperty(String name, MQPropertyDescriptor descriptor) throws MQException {
/*      */     long result;
/* 6905 */     if (Trace.isOn) {
/* 6906 */       Trace.entry(this, "com.ibm.mq.MQMessage", "getLongProperty(String,MQPropertyDescriptor)", new Object[] { name, descriptor });
/*      */     }
/*      */ 
/*      */     
/* 6910 */     Object resultObject = getProperty(name, descriptor);
/* 6911 */     if (resultObject instanceof Long) {
/* 6912 */       result = ((Long)resultObject).longValue();
/*      */     } else {
/* 6914 */       MQException traceRet1 = new MQException(2, 2473, this);
/*      */       
/* 6916 */       if (Trace.isOn) {
/* 6917 */         Trace.throwing(this, "com.ibm.mq.MQMessage", "getLongProperty(String,MQPropertyDescriptor)", traceRet1);
/*      */       }
/*      */       
/* 6920 */       throw traceRet1;
/*      */     } 
/* 6922 */     if (Trace.isOn) {
/* 6923 */       Trace.exit(this, "com.ibm.mq.MQMessage", "getLongProperty(String,MQPropertyDescriptor)", 
/* 6924 */           Long.valueOf(result));
/*      */     }
/* 6926 */     return result;
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
/*      */   public long getInt8Property(String name) throws MQException {
/* 6939 */     if (Trace.isOn) {
/* 6940 */       Trace.entry(this, "com.ibm.mq.MQMessage", "getInt8Property(String)", new Object[] { name });
/*      */     }
/* 6942 */     long traceRet1 = getLongProperty(name);
/*      */     
/* 6944 */     if (Trace.isOn) {
/* 6945 */       Trace.exit(this, "com.ibm.mq.MQMessage", "getInt8Property(String)", Long.valueOf(traceRet1));
/*      */     }
/*      */     
/* 6948 */     return traceRet1;
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
/*      */   public long getInt8Property(String name, MQPropertyDescriptor descriptor) throws MQException {
/* 6962 */     if (Trace.isOn) {
/* 6963 */       Trace.entry(this, "com.ibm.mq.MQMessage", "getInt8Property(String,MQPropertyDescriptor)", new Object[] { name, descriptor });
/*      */     }
/*      */     
/* 6966 */     long traceRet1 = getLongProperty(name, descriptor);
/*      */     
/* 6968 */     if (Trace.isOn) {
/* 6969 */       Trace.exit(this, "com.ibm.mq.MQMessage", "getInt8Property(String,MQPropertyDescriptor)", 
/* 6970 */           Long.valueOf(traceRet1));
/*      */     }
/* 6972 */     return traceRet1;
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
/*      */   public float getFloatProperty(String name) throws MQException {
/* 6984 */     if (Trace.isOn) {
/* 6985 */       Trace.entry(this, "com.ibm.mq.MQMessage", "getFloatProperty(String)", new Object[] { name });
/*      */     }
/* 6987 */     float traceRet1 = getFloatProperty(name, (MQPropertyDescriptor)null);
/*      */     
/* 6989 */     if (Trace.isOn) {
/* 6990 */       Trace.exit(this, "com.ibm.mq.MQMessage", "getFloatProperty(String)", 
/* 6991 */           Float.valueOf(traceRet1));
/*      */     }
/* 6993 */     return traceRet1;
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
/*      */   public float getFloatProperty(String name, MQPropertyDescriptor descriptor) throws MQException {
/*      */     float result;
/* 7007 */     if (Trace.isOn) {
/* 7008 */       Trace.entry(this, "com.ibm.mq.MQMessage", "getFloatProperty(String,MQPropertyDescriptor)", new Object[] { name, descriptor });
/*      */     }
/*      */ 
/*      */     
/* 7012 */     Object resultObject = getProperty(name, descriptor);
/* 7013 */     if (resultObject instanceof Float) {
/* 7014 */       result = ((Float)resultObject).floatValue();
/*      */     } else {
/* 7016 */       MQException traceRet1 = new MQException(2, 2473, this);
/*      */       
/* 7018 */       if (Trace.isOn) {
/* 7019 */         Trace.throwing(this, "com.ibm.mq.MQMessage", "getFloatProperty(String,MQPropertyDescriptor)", traceRet1);
/*      */       }
/*      */       
/* 7022 */       throw traceRet1;
/*      */     } 
/* 7024 */     if (Trace.isOn) {
/* 7025 */       Trace.exit(this, "com.ibm.mq.MQMessage", "getFloatProperty(String,MQPropertyDescriptor)", 
/* 7026 */           Float.valueOf(result));
/*      */     }
/* 7028 */     return result;
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
/*      */   public double getDoubleProperty(String name) throws MQException {
/* 7040 */     if (Trace.isOn) {
/* 7041 */       Trace.entry(this, "com.ibm.mq.MQMessage", "getDoubleProperty(String)", new Object[] { name });
/*      */     }
/* 7043 */     double traceRet1 = getDoubleProperty(name, (MQPropertyDescriptor)null);
/*      */     
/* 7045 */     if (Trace.isOn) {
/* 7046 */       Trace.exit(this, "com.ibm.mq.MQMessage", "getDoubleProperty(String)", 
/* 7047 */           Double.valueOf(traceRet1));
/*      */     }
/* 7049 */     return traceRet1;
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
/*      */   public double getDoubleProperty(String name, MQPropertyDescriptor descriptor) throws MQException {
/*      */     double result;
/* 7063 */     if (Trace.isOn) {
/* 7064 */       Trace.entry(this, "com.ibm.mq.MQMessage", "getDoubleProperty(String,MQPropertyDescriptor)", new Object[] { name, descriptor });
/*      */     }
/*      */ 
/*      */     
/* 7068 */     Object resultObject = getProperty(name, descriptor);
/* 7069 */     if (resultObject instanceof Double) {
/* 7070 */       result = ((Double)resultObject).doubleValue();
/*      */     } else {
/* 7072 */       MQException traceRet1 = new MQException(2, 2473, this);
/*      */       
/* 7074 */       if (Trace.isOn) {
/* 7075 */         Trace.throwing(this, "com.ibm.mq.MQMessage", "getDoubleProperty(String,MQPropertyDescriptor)", traceRet1);
/*      */       }
/*      */       
/* 7078 */       throw traceRet1;
/*      */     } 
/* 7080 */     if (Trace.isOn) {
/* 7081 */       Trace.exit(this, "com.ibm.mq.MQMessage", "getDoubleProperty(String,MQPropertyDescriptor)", 
/* 7082 */           Double.valueOf(result));
/*      */     }
/* 7084 */     return result;
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
/*      */   public String getStringProperty(String name) throws MQException {
/* 7096 */     if (Trace.isOn) {
/* 7097 */       Trace.entry(this, "com.ibm.mq.MQMessage", "getStringProperty(String)", new Object[] { name });
/*      */     }
/* 7099 */     String traceRet1 = getStringProperty(name, (MQPropertyDescriptor)null);
/*      */     
/* 7101 */     if (Trace.isOn) {
/* 7102 */       Trace.exit(this, "com.ibm.mq.MQMessage", "getStringProperty(String)", traceRet1);
/*      */     }
/* 7104 */     return traceRet1;
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
/*      */   public String getStringProperty(String name, MQPropertyDescriptor descriptor) throws MQException {
/*      */     String result;
/* 7118 */     if (Trace.isOn) {
/* 7119 */       Trace.entry(this, "com.ibm.mq.MQMessage", "getStringProperty(String,MQPropertyDescriptor)", new Object[] { name, descriptor });
/*      */     }
/*      */ 
/*      */     
/* 7123 */     Object resultObject = getProperty(name, descriptor);
/* 7124 */     if (resultObject == null) {
/* 7125 */       result = null;
/* 7126 */     } else if (resultObject instanceof String) {
/* 7127 */       result = (String)resultObject;
/*      */     } else {
/* 7129 */       MQException traceRet1 = new MQException(2, 2473, this);
/*      */       
/* 7131 */       if (Trace.isOn) {
/* 7132 */         Trace.throwing(this, "com.ibm.mq.MQMessage", "getStringProperty(String,MQPropertyDescriptor)", traceRet1);
/*      */       }
/*      */       
/* 7135 */       throw traceRet1;
/*      */     } 
/* 7137 */     if (Trace.isOn) {
/* 7138 */       Trace.exit(this, "com.ibm.mq.MQMessage", "getStringProperty(String,MQPropertyDescriptor)", result);
/*      */     }
/*      */     
/* 7141 */     return result;
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
/*      */   public Object getObjectProperty(String name) throws MQException {
/* 7158 */     if (Trace.isOn) {
/* 7159 */       Trace.entry(this, "com.ibm.mq.MQMessage", "getObjectProperty(String)", new Object[] { name });
/*      */     }
/* 7161 */     Object traceRet1 = getObjectProperty(name, (MQPropertyDescriptor)null);
/*      */     
/* 7163 */     if (Trace.isOn) {
/* 7164 */       Trace.exit(this, "com.ibm.mq.MQMessage", "getObjectProperty(String)", traceRet1);
/*      */     }
/* 7166 */     return traceRet1;
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
/*      */   public Object getObjectProperty(String name, MQPropertyDescriptor descriptor) throws MQException {
/* 7185 */     if (Trace.isOn) {
/* 7186 */       Trace.entry(this, "com.ibm.mq.MQMessage", "getObjectProperty(String,MQPropertyDescriptor)", new Object[] { name, descriptor });
/*      */     }
/*      */     
/* 7189 */     Object result = getProperty(name, descriptor);
/*      */     
/* 7191 */     if (Trace.isOn) {
/* 7192 */       Trace.exit(this, "com.ibm.mq.MQMessage", "getObjectProperty(String,MQPropertyDescriptor)", result);
/*      */     }
/*      */     
/* 7195 */     return result;
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
/*      */   public Enumeration<String> getPropertyNames(String nameP) throws MQException {
/* 7209 */     if (Trace.isOn) {
/* 7210 */       Trace.entry(this, "com.ibm.mq.MQMessage", "getPropertyNames(String)", new Object[] { nameP });
/*      */     }
/* 7212 */     String name = nameP;
/* 7213 */     Vector<String> matchingPropertyNames = new Vector<>();
/*      */     
/* 7215 */     if (!name.equals("%")) {
/* 7216 */       name = makeUsrFolderExplicit(name);
/*      */     }
/*      */     
/* 7219 */     for (String propertyName : this.properties.keySet()) {
/* 7220 */       boolean matching = false;
/* 7221 */       if (name.endsWith("%")) {
/*      */         
/* 7223 */         String nameWithoutWildcard = name.substring(0, name.length() - 1);
/* 7224 */         if (propertyName.startsWith(nameWithoutWildcard)) {
/* 7225 */           matching = true;
/*      */         
/*      */         }
/*      */       }
/* 7229 */       else if (propertyName.equals(name)) {
/* 7230 */         matching = true;
/*      */       } 
/*      */       
/* 7233 */       if (matching) {
/*      */         
/* 7235 */         propertyName = removeExplicitUsrFolder(propertyName);
/* 7236 */         if (propertyName.toLowerCase().startsWith("jms") || propertyName.toLowerCase().startsWith("mcd")) {
/* 7237 */           Object namedProperty_ = JMSPropertySynonyms.get(propertyName);
/* 7238 */           if (namedProperty_ != null) {
/* 7239 */             propertyName = namedProperty_.toString();
/*      */           }
/*      */         } 
/*      */         
/* 7243 */         if (!matchingPropertyNames.contains(propertyName)) {
/* 7244 */           matchingPropertyNames.add(propertyName);
/*      */         }
/*      */       } 
/*      */     } 
/* 7248 */     Enumeration<String> traceRet1 = matchingPropertyNames.elements();
/*      */     
/* 7250 */     if (Trace.isOn) {
/* 7251 */       Trace.exit(this, "com.ibm.mq.MQMessage", "getPropertyNames(String)", traceRet1);
/*      */     }
/* 7253 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void deleteProperty(String nameP) throws MQException {
/* 7263 */     if (Trace.isOn) {
/* 7264 */       Trace.entry(this, "com.ibm.mq.MQMessage", "deleteProperty(String)", new Object[] { nameP });
/*      */     }
/* 7266 */     String name = nameP;
/*      */     
/* 7268 */     name = makeUsrFolderExplicit(name);
/*      */     
/* 7270 */     Object<Object> deletedProperty = (Object<Object>)this.properties.remove(name);
/* 7271 */     if (deletedProperty == null) {
/* 7272 */       MQException traceRet1 = new MQException(2, 2471, this);
/*      */       
/* 7274 */       if (Trace.isOn) {
/* 7275 */         Trace.throwing(this, "com.ibm.mq.MQMessage", "deleteProperty(String)", traceRet1);
/*      */       }
/* 7277 */       throw traceRet1;
/*      */     } 
/* 7279 */     if (Trace.isOn)
/* 7280 */       Trace.exit(this, "com.ibm.mq.MQMessage", "deleteProperty(String)"); 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */