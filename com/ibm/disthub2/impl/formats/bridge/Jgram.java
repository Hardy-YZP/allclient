/*      */ package com.ibm.disthub2.impl.formats.bridge;
/*      */ 
/*      */ import com.ibm.disthub2.client.MessageBodyHandle;
/*      */ import com.ibm.disthub2.client.SchemaViolationException;
/*      */ import com.ibm.disthub2.impl.client.BaseConfig;
/*      */ import com.ibm.disthub2.impl.client.DebugObject;
/*      */ import com.ibm.disthub2.impl.client.MessageImpl;
/*      */ import com.ibm.disthub2.impl.client.SessionConfig;
/*      */ import com.ibm.disthub2.impl.formats.Envelop;
/*      */ import com.ibm.disthub2.impl.formats.Framing;
/*      */ import com.ibm.disthub2.impl.formats.MessageDataHandle;
/*      */ import com.ibm.disthub2.impl.formats.MessageEncrypter;
/*      */ import com.ibm.disthub2.impl.formats.MessageHandle;
/*      */ import com.ibm.disthub2.impl.formats.ReleaseTable;
/*      */ import com.ibm.disthub2.impl.formats.Schema;
/*      */ import com.ibm.disthub2.impl.formats.SchemaRegistry;
/*      */ import com.ibm.disthub2.impl.formats.StandardMessageDataHandle;
/*      */ import com.ibm.disthub2.impl.matching.MatchingContext;
/*      */ import com.ibm.disthub2.impl.security.IntegrityCompromisedException;
/*      */ import com.ibm.disthub2.impl.security.MessageProtection;
/*      */ import com.ibm.disthub2.impl.security.Qop;
/*      */ import com.ibm.disthub2.impl.security.SecurityContext;
/*      */ import com.ibm.disthub2.impl.security.SecurityGeneralException;
/*      */ import com.ibm.disthub2.impl.server.InitialStateProcessor;
/*      */ import com.ibm.disthub2.impl.server.MgramLike;
/*      */ import com.ibm.disthub2.impl.server.RoutableMessage;
/*      */ import com.ibm.disthub2.impl.util.ArrayUtil;
/*      */ import com.ibm.disthub2.impl.util.Assert;
/*      */ import com.ibm.disthub2.impl.util.Hex;
/*      */ import com.ibm.disthub2.impl.util.SegmentReader;
/*      */ import com.ibm.disthub2.spi.ClientExceptionConstants;
/*      */ import com.ibm.disthub2.spi.ClientLogConstants;
/*      */ import com.ibm.disthub2.spi.ExceptionBuilder;
/*      */ import java.io.IOException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class Jgram
/*      */   implements Envelop.Constants, MgramLike, Cloneable, ClientLogConstants, ClientExceptionConstants, RoutableMessage
/*      */ {
/*      */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*      */   public static final byte NORMAL_PRIORITY = 4;
/*      */   public static final byte HIGH_PRIORITY = 9;
/*      */   public static final byte GD_CONTROL_PRIORITY = 20;
/*      */   public static final byte RELIABLE_PRIORITY = 30;
/*      */   public static final byte CONTROL_PRIORITY = 40;
/*  139 */   private static final DebugObject debug = new DebugObject("Jgram");
/*      */ 
/*      */ 
/*      */   
/*  143 */   private static String nullTopic = "";
/*  144 */   private static byte[] nullMdt = new byte[0];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private byte[] frame;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean topicsArePrefixed = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String topic;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  167 */   private byte qop = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private MessageHandle cursor;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Payload payload;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  185 */   private int sksl = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  194 */   private byte[] digestCache = null;
/*      */ 
/*      */ 
/*      */   
/*  198 */   public long recvTime = 0L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean doneProcessing = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Jgram create(SegmentReader rdr, SecurityContext sc, boolean client) throws IOException, SchemaViolationException, IntegrityCompromisedException, SecurityGeneralException {
/*  248 */     if (debug.debugIt(32)) {
/*  249 */       debug.debug(-165922073994779L, "create", rdr, sc, Boolean.valueOf(client));
/*      */     }
/*      */     
/*  252 */     byte[] data = rdr.get();
/*  253 */     if (data == null) {
/*  254 */       throw new IOException(ExceptionBuilder.buildReasonString(-543289165, null));
/*      */     }
/*  256 */     rdr.prepGet(null);
/*      */     
/*  258 */     Jgram result = createFromReceivedBytes(data, sc, client);
/*      */     
/*  260 */     if (debug.debugIt(64)) {
/*  261 */       debug.debug(-142394261359015L, "create", result);
/*      */     }
/*      */     
/*  264 */     return result;
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
/*      */   public static Jgram createFromReceivedBytes(byte[] lframe, SecurityContext sc, boolean client) throws SchemaViolationException, IntegrityCompromisedException, SecurityGeneralException {
/*      */     Jgram result;
/*  299 */     if (debug.debugIt(32)) {
/*  300 */       debug.debug(-165922073994779L, "createFromReceivedBytes", lframe, sc, Boolean.valueOf(client));
/*      */     }
/*      */     
/*  303 */     if (debug.debugIt(16)) {
/*  304 */       debug.debug(-153415734321212L, "createFromReceivedBytes", "Processing frame: " + Hex.toString(lframe));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  309 */     Qop.checkIntegrity(lframe, sc, client, (BaseConfig.getBaseConfig()).ENABLE_QOP_SECURITY);
/*      */ 
/*      */ 
/*      */     
/*  313 */     byte qop = Framing.qop(lframe);
/*      */ 
/*      */     
/*  316 */     MessageProtection mp = null;
/*  317 */     if (qop == 14) {
/*      */       
/*  319 */       mp = sc.getMP();
/*  320 */       Object key = client ? sc.getClientKey() : sc.getServerKey();
/*  321 */       Qop.sessionDecrypt(lframe, mp, key, sc.getDecryptIV());
/*      */     } 
/*      */ 
/*      */     
/*  325 */     byte[] digestCache = null;
/*  326 */     if ((qop & 0x6) == 6) {
/*  327 */       digestCache = Qop.extractDigest(lframe, sc.getMP());
/*      */     }
/*      */ 
/*      */     
/*  331 */     MessageHandle handle = SchemaRegistry.getMessageHandle(lframe, (MessageEncrypter)mp);
/*      */ 
/*      */ 
/*      */     
/*  335 */     if (handle == null) {
/*      */       
/*  337 */       result = null;
/*      */     }
/*      */     else {
/*      */       
/*  341 */       result = new Jgram(handle, qop, digestCache);
/*      */     } 
/*      */     
/*  344 */     if (debug.debugIt(64)) {
/*  345 */       debug.debug(-142394261359015L, "createFromReceivedBytes", result);
/*      */     }
/*      */     
/*  348 */     return result;
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
/*      */   public static Jgram deserialize(byte[] serial) {
/*  360 */     byte qop = serial[0];
/*  361 */     short interpId = ArrayUtil.readShort(serial, 1);
/*  362 */     long schemaId = ArrayUtil.readLong(serial, 3);
/*  363 */     MessageHandle cursor = SchemaRegistry.getMessageHandle(interpId, schemaId, serial, 11, serial.length - 11);
/*      */     
/*  365 */     return new Jgram(cursor, qop, null);
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
/*      */   public Jgram(MessageHandle cursor, byte qop, byte[] digestCache) {
/*  382 */     this(cursor);
/*  383 */     if (debug.debugIt(32)) {
/*  384 */       debug.debug(-165922073994779L, "Jgram", cursor, digestCache);
/*      */     }
/*      */     
/*  387 */     this.digestCache = digestCache;
/*  388 */     this.qop = qop;
/*      */     
/*  390 */     if (debug.debugIt(64)) {
/*  391 */       debug.debug(-142394261359015L, "Jgram");
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
/*      */   public Jgram(int type) {
/*  404 */     if (debug.debugIt(32)) {
/*  405 */       debug.debug(-165922073994779L, "Jgram", Integer.valueOf(type));
/*      */     }
/*      */     
/*  408 */     this.cursor = SchemaRegistry.getMessageHandle();
/*  409 */     this.cursor.setChoice(145, 0);
/*  410 */     this.cursor.setChoice(147, 1);
/*  411 */     this.cursor.setLong(5, 0L);
/*  412 */     this.cursor.setLong(6, 0L);
/*  413 */     this.cursor.setBoolean(7, false);
/*  414 */     this.cursor.setBoolean(8, false);
/*  415 */     this.cursor.setBoolean(9, false);
/*  416 */     this.cursor.setBoolean(10, false);
/*  417 */     this.cursor.setBoolean(3, false);
/*  418 */     setPriority((byte)4);
/*  419 */     setTopic(nullTopic);
/*  420 */     setMdt(nullMdt);
/*  421 */     setQopQuery(false);
/*  422 */     this.cursor.setChoice(149, type);
/*  423 */     if (this.cursor.getChoice(149) == 1 && this.cursor
/*  424 */       .getChoice(152) == 4) {
/*  425 */       this.cursor.setChoice(158, 0);
/*      */     }
/*      */ 
/*      */     
/*  429 */     if (debug.debugIt(64)) {
/*  430 */       debug.debug(-142394261359015L, "Jgram");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Jgram(MessageHandle cursor) {
/*  437 */     if (debug.debugIt(32)) {
/*  438 */       debug.debug(-165922073994779L, "Jgram", cursor);
/*      */     }
/*      */     
/*  441 */     this.cursor = cursor;
/*      */     
/*  443 */     if (cursor.isPresent(147)) {
/*  444 */       this.topicsArePrefixed = (cursor.getChoice(147) == 0);
/*      */     } else {
/*      */       
/*  447 */       this.topicsArePrefixed = true;
/*      */     } 
/*      */     
/*  450 */     if (debug.debugIt(64)) {
/*  451 */       debug.debug(-142394261359015L, "Jgram");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized Jgram duplicate() {
/*  461 */     return new Jgram(this.cursor.duplicate(), this.qop, this.digestCache);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean areTopicsPrefixed() {
/*  467 */     if (debug.debugIt(32)) {
/*  468 */       debug.debug(-165922073994779L, "areTopicsPrefixed");
/*      */     }
/*      */     
/*  471 */     if (debug.debugIt(64)) {
/*  472 */       debug.debug(-142394261359015L, "areTopicsPrefixed", Boolean.valueOf(this.topicsArePrefixed));
/*      */     }
/*      */     
/*  475 */     return this.topicsArePrefixed;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public byte getPriority() {
/*  481 */     if (debug.debugIt(32)) {
/*  482 */       debug.debug(-165922073994779L, "getPriority");
/*      */     }
/*      */     
/*  485 */     byte priority = this.cursor.getByte(2);
/*      */     
/*  487 */     if (debug.debugIt(64)) {
/*  488 */       debug.debug(-142394261359015L, "getPriority", Byte.valueOf(priority));
/*      */     }
/*      */     
/*  491 */     return priority;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPriority(byte prio) {
/*  497 */     if (debug.debugIt(32)) {
/*  498 */       debug.debug(-165922073994779L, "setPriority", Byte.valueOf(prio));
/*      */     }
/*      */     
/*  501 */     this.cursor.setByte(2, prio);
/*  502 */     this.frame = null;
/*      */     
/*  504 */     if (debug.debugIt(64)) {
/*  505 */       debug.debug(-142394261359015L, "setPriority");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getTopic() {
/*  512 */     if (debug.debugIt(32)) {
/*  513 */       debug.debug(-165922073994779L, "getTopic");
/*      */     }
/*      */     
/*  516 */     if (this.topic == null) {
/*  517 */       this.topic = this.cursor.getString(4);
/*  518 */       if (this.topicsArePrefixed && this.topic.length() > 0) {
/*  519 */         this.topic = MessageImpl.toDefaultInternalTopic(this.topic);
/*      */       }
/*      */     } 
/*      */     
/*  523 */     String result = null;
/*  524 */     if (this.topic.length() > 0) {
/*  525 */       result = this.topic;
/*      */     }
/*      */     
/*  528 */     if (debug.debugIt(64)) {
/*  529 */       debug.debug(-142394261359015L, "getTopic", result);
/*      */     }
/*      */     
/*  532 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTopic(String val) {
/*  538 */     if (debug.debugIt(32)) {
/*  539 */       debug.debug(-165922073994779L, "setTopic", val);
/*      */     }
/*      */     
/*  542 */     String xTopic = this.topic = (val == null) ? nullTopic : val;
/*  543 */     if (this.topicsArePrefixed && xTopic.length() > 0) {
/*  544 */       xTopic = MessageImpl.toDefaultExternalTopic(xTopic);
/*      */     }
/*      */ 
/*      */     
/*  548 */     this.cursor.setString(4, xTopic);
/*  549 */     this.frame = null;
/*      */     
/*  551 */     if (debug.debugIt(64)) {
/*  552 */       debug.debug(-142394261359015L, "setTopic");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getMdt() {
/*  559 */     if (debug.debugIt(32)) {
/*  560 */       debug.debug(-165922073994779L, "getMdt");
/*      */     }
/*      */     
/*  563 */     byte[] result = this.cursor.getByteArray(0);
/*  564 */     if (result.length == 0) {
/*  565 */       result = null;
/*      */     }
/*      */     
/*  568 */     if (debug.debugIt(64)) {
/*  569 */       debug.debug(-142394261359015L, "getMdt", result);
/*      */     }
/*      */     
/*  572 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMdt(byte[] val) {
/*  578 */     if (debug.debugIt(32)) {
/*  579 */       debug.debug(-165922073994779L, "setMdt", val);
/*      */     }
/*      */     
/*  582 */     if (val == null) {
/*  583 */       val = nullMdt;
/*      */     }
/*  585 */     this.cursor.setByteArray(0, val);
/*  586 */     this.frame = null;
/*      */     
/*  588 */     if (debug.debugIt(64)) {
/*  589 */       debug.debug(-142394261359015L, "setMdt");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getQopQuery() {
/*  599 */     if (debug.debugIt(32)) {
/*  600 */       debug.debug(-165922073994779L, "getQopQuery");
/*      */     }
/*      */     
/*  603 */     boolean qopQuery = this.cursor.getBoolean(1);
/*      */     
/*  605 */     if (debug.debugIt(64)) {
/*  606 */       debug.debug(-142394261359015L, "getQopQuery", Boolean.valueOf(qopQuery));
/*      */     }
/*      */     
/*  609 */     return qopQuery;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setQopQuery(boolean val) {
/*  618 */     if (debug.debugIt(32)) {
/*  619 */       debug.debug(-165922073994779L, "setQopQuery", Boolean.valueOf(val));
/*      */     }
/*      */     
/*  622 */     this.cursor.setBoolean(1, val);
/*  623 */     this.frame = null;
/*      */     
/*  625 */     if (debug.debugIt(64)) {
/*  626 */       debug.debug(-142394261359015L, "setQopQuery");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Payload getPayload() {
/*  633 */     if (debug.debugIt(32)) {
/*  634 */       debug.debug(-165922073994779L, "getPayload");
/*      */     }
/*      */     
/*  637 */     if (this.payload == null) {
/*  638 */       this.payload = Payload.construct(this.cursor.getChoice(149), (MessageDataHandle)this.cursor, this);
/*      */     }
/*      */ 
/*      */     
/*  642 */     if (debug.debugIt(64)) {
/*  643 */       debug.debug(-142394261359015L, "getPayload", this.payload);
/*      */     }
/*      */     
/*  646 */     return this.payload;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized int length() {
/*  656 */     if (debug.debugIt(32)) {
/*  657 */       debug.debug(-165922073994779L, "length");
/*      */     }
/*      */ 
/*      */     
/*  661 */     if (this.frame != null) {
/*  662 */       if (debug.debugIt(16)) {
/*  663 */         debug.debug(-153415734321212L, "length", "Computing length of non-null frame");
/*      */       }
/*  665 */       int toReturn = Framing.fullLength(this.frame);
/*  666 */       if (debug.debugIt(64)) {
/*  667 */         debug.debug(-142394261359015L, "length", "returning: ", Integer.valueOf(toReturn));
/*      */       }
/*  669 */       return toReturn;
/*      */     } 
/*      */     
/*  672 */     if (debug.debugIt(16)) {
/*  673 */       debug.debug(-153415734321212L, "length", "Computing length from MessageHandle");
/*      */     }
/*  675 */     int result = this.cursor.getEncodedLength(null);
/*      */ 
/*      */     
/*  678 */     if (debug.debugIt(64)) {
/*  679 */       debug.debug(-142394261359015L, "length", Integer.valueOf(result));
/*      */     }
/*      */     
/*  682 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void setRelease(int release) {
/*  688 */     short oldInterp = this.cursor.getInterpreterId();
/*  689 */     short newInterp = oldInterp;
/*  690 */     for (int i = 0; i < ReleaseTable.releases.length; i++) {
/*  691 */       if ((ReleaseTable.releases[i]).release == release) {
/*  692 */         newInterp = (ReleaseTable.releases[i]).interp;
/*      */ 
/*      */ 
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  701 */     if (newInterp < oldInterp) {
/*  702 */       this.cursor = SchemaRegistry.reEncode(newInterp, this.cursor);
/*  703 */       this.frame = null;
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
/*      */   public synchronized void prepareForTransmission(int release, SecurityContext sc) throws SecurityGeneralException {
/*  727 */     if (debug.debugIt(32)) {
/*  728 */       debug.debug(-165922073994779L, "prepareForTransmission", Integer.valueOf(release), sc);
/*      */     }
/*      */     
/*  731 */     setRelease(release);
/*      */     
/*  733 */     if (this.frame != null) {
/*      */ 
/*      */       
/*  736 */       if (debug.debugIt(64)) {
/*  737 */         debug.debug(-142394261359015L, "prepareForTransmission");
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  747 */     MessageProtection messageProtection = (sc != null) ? sc.getMP() : null;
/*  748 */     int overhead = Framing.overhead(this.qop, (MessageEncrypter)messageProtection, false);
/*  749 */     if (this.qop != 14) {
/*  750 */       messageProtection = null;
/*      */     }
/*  752 */     int length = this.cursor.getEncodedLength((MessageEncrypter)messageProtection);
/*  753 */     this.frame = new byte[overhead + length];
/*  754 */     if (debug.debugIt(16)) {
/*  755 */       debug.debug(-153415734321212L, "prepareForTransmission", "overhead=" + overhead + ",length=" + length);
/*      */     }
/*      */     
/*  758 */     this.sksl = this.cursor.toByteArray(this.frame, overhead, length, (MessageEncrypter)messageProtection);
/*  759 */     this.sksl = (this.qop == 14) ? this.sksl : -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  766 */     if (this.qop == 1) {
/*  767 */       Framing.frameMessage(this.frame, this.cursor.getInterpreterId(), this.cursor
/*  768 */           .getEncodingSchema().getId(), overhead + length);
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */       
/*  776 */       if (debug.debugIt(16)) {
/*  777 */         debug.debug(-153415734321212L, "prepareForTransmission", "preframe interpID: " + this.cursor.getInterpreterId() + " schemaID: " + this.cursor
/*  778 */             .getEncodingSchema().getId());
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  783 */       if ((this.qop & 0x6) == 6 && (this.digestCache == null || this.cursor.changed())) {
/*  784 */         int localSKSL = (this.qop == 14) ? this.sksl : 0;
/*  785 */         this.digestCache = Qop.computeDigest(this.frame, overhead + localSKSL, length - localSKSL, sc
/*  786 */             .getMP());
/*  787 */         if (debug.debugIt(16)) {
/*  788 */           debug.debug(-153415734321212L, "prepareForTransmission", "digesting region: " + Hex.toString(this.frame, overhead + localSKSL, length - localSKSL));
/*  789 */           debug.debug(-153415734321212L, "prepareForTransmission", "computed digestCache: " + Hex.toString(this.digestCache));
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  798 */       Qop.frameMessage(this.frame, this.cursor.getInterpreterId(), this.cursor
/*  799 */           .getEncodingSchema().getId(), (byte)2, 0, sc, null, false, length + overhead);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  804 */     if (debug.debugIt(16)) {
/*  805 */       debug.debug(-153415734321212L, "prepareForTransmission", "prepared frame: " + Hex.toString(this.frame, 0, this.frame.length));
/*      */     }
/*      */     
/*  808 */     this.payload = null;
/*      */     
/*  810 */     if (debug.debugIt(64)) {
/*  811 */       debug.debug(-142394261359015L, "prepareForTransmission");
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
/*      */   public byte[] serialize() {
/*  823 */     int len = this.cursor.getEncodedLength(null);
/*  824 */     byte[] ans = new byte[len + 11];
/*  825 */     ans[0] = 0;
/*  826 */     ArrayUtil.writeShort(ans, 1, this.cursor.getInterpreterId());
/*  827 */     ArrayUtil.writeLong(ans, 3, this.cursor.getEncodingSchema().getId());
/*  828 */     this.cursor.toByteArray(ans, 11, len, null);
/*  829 */     return ans;
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
/*      */   public synchronized byte[] getTransmissionBytes(int release, SecurityContext C, boolean client) throws SecurityGeneralException {
/*  854 */     if (debug.debugIt(32)) {
/*  855 */       debug.debug(-165922073994779L, "getTransmissionBytes");
/*      */     }
/*      */     
/*  858 */     if (this.frame == null) {
/*  859 */       prepareForTransmission(release, C);
/*      */     }
/*      */     
/*  862 */     byte[] result = this.frame;
/*  863 */     if (this.qop != 1) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  871 */       result = new byte[this.frame.length];
/*  872 */       System.arraycopy(this.frame, 0, result, 0, this.frame.length);
/*      */       
/*  874 */       if (debug.debugIt(16)) {
/*  875 */         debug.debug(-153415734321212L, "getTransmissionBytes", "computing final frame, interpID: " + Framing.interpId(this.frame) + " schemaID: " + Framing.schemaId(this.frame));
/*      */       }
/*      */       
/*  878 */       Assert.condition(((this.qop & 0x6) != 6 || this.digestCache != null));
/*      */ 
/*      */       
/*  881 */       Qop.frameMessage(result, Framing.interpId(this.frame), Framing.schemaId(this.frame), this.qop, this.sksl, C, this.digestCache, client, result.length);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  886 */       Qop.channelProtect(result, C.getMP(), C.getNextSendCount(), client ? C.getClientMAC() : C.getServerMAC());
/*      */     } 
/*      */     
/*  889 */     if (debug.debugIt(64)) {
/*  890 */       debug.debug(-142394261359015L, "getTransmissionBytes", result);
/*      */     }
/*      */     
/*  893 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized Schema[] getSchemata() {
/*  904 */     if (debug.debugIt(32)) {
/*  905 */       debug.debug(-165922073994779L, "getSchemata");
/*      */     }
/*      */     
/*  908 */     Schema[] schemata = this.cursor.getSchemata();
/*      */     
/*  910 */     if (debug.debugIt(64)) {
/*  911 */       debug.debug(-142394261359015L, "getSchemata", schemata);
/*      */     }
/*      */     
/*  914 */     return schemata;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setQop(byte qop) {
/*  925 */     if (debug.debugIt(32)) {
/*  926 */       debug.debug(-165922073994779L, "setQop", Byte.valueOf(qop));
/*      */     }
/*      */ 
/*      */     
/*  930 */     if ((SessionConfig.getBaseConfig()).ENABLE_QOP_SECURITY) {
/*  931 */       if (qop != this.qop)
/*      */       {
/*      */         
/*  934 */         this.digestCache = null;
/*      */       }
/*  936 */       this.qop = qop;
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  941 */       if (qop != 1) {
/*  942 */         Assert.failure("setting qop to non_null vlaue while Qop not enabled");
/*      */       }
/*  944 */       this.qop = qop;
/*      */     } 
/*      */     
/*  947 */     if (debug.debugIt(64)) {
/*  948 */       debug.debug(-142394261359015L, "setQop");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte getQop() {
/*  959 */     if (debug.debugIt(32)) {
/*  960 */       debug.debug(-165922073994779L, "getQop");
/*      */     }
/*      */     
/*  963 */     if (debug.debugIt(64)) {
/*  964 */       debug.debug(-142394261359015L, "getQop", Byte.valueOf(this.qop));
/*      */     }
/*      */     
/*  967 */     return this.qop;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getType() {
/*  977 */     if (debug.debugIt(32)) {
/*  978 */       debug.debug(-165922073994779L, "getType");
/*      */     }
/*      */     
/*  981 */     int result = this.cursor.getChoice(149);
/*      */     
/*  983 */     if (debug.debugIt(64)) {
/*  984 */       debug.debug(-142394261359015L, "getType", Integer.valueOf(result));
/*      */     }
/*      */     
/*  987 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isGuaranteed() {
/*  993 */     return (this.cursor.isPresent(5) && this.cursor.getLong(5) != 0L);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public long getPubendID() {
/*  999 */     return this.cursor.getLong(5);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPubendID(long id) {
/* 1005 */     this.cursor.setLong(5, id);
/* 1006 */     this.frame = null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public long getStamp() {
/* 1012 */     return this.cursor.getLong(6);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setStamp(long stamp) {
/* 1018 */     this.cursor.setLong(6, stamp);
/* 1019 */     this.frame = null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getForce() {
/* 1025 */     return this.cursor.getBoolean(7);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setForce(boolean force) {
/* 1031 */     this.cursor.setBoolean(7, force);
/* 1032 */     this.frame = null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getCuriousOnly() {
/* 1038 */     return this.cursor.getBoolean(8);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCuriousOnly(boolean co) {
/* 1044 */     this.cursor.setBoolean(8, co);
/* 1045 */     this.frame = null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getCuriousD() {
/* 1051 */     return this.cursor.getBoolean(9);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCuriousD(boolean cd) {
/* 1057 */     this.cursor.setBoolean(9, cd);
/* 1058 */     this.frame = null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getPersistent() {
/* 1064 */     return (this.cursor.isPresent(10) && this.cursor.getBoolean(10));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPersistent(boolean p) {
/* 1070 */     this.cursor.setBoolean(10, p);
/* 1071 */     this.frame = null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getJMSExpiration() {
/* 1078 */     if (this.cursor.getChoice(149) != 1 || this.cursor
/* 1079 */       .getChoice(152) != 4) {
/* 1080 */       return 0L;
/*      */     }
/*      */     
/* 1083 */     return this.cursor.getLong(21);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getTarget() {
/* 1092 */     if (!this.cursor.isPresent(32)) {
/* 1093 */       return -1;
/*      */     }
/* 1095 */     return this.cursor.getInt(32);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTarget(int tid) {
/* 1103 */     if (this.cursor.getChoice(149) != 1 || this.cursor
/* 1104 */       .getChoice(152) != 4) {
/* 1105 */       Assert.failure("Setting a jms msg target on a non V!!");
/*      */     }
/*      */     
/* 1108 */     if (tid < 0) {
/* 1109 */       this.cursor.setChoice(158, 0);
/*      */       
/* 1111 */       this.frame = null;
/*      */       return;
/*      */     } 
/* 1114 */     this.cursor.setInt(32, tid);
/* 1115 */     this.frame = null;
/*      */   }
/*      */   
/*      */   public boolean isEndOfCatchup() {
/* 1119 */     if (this.cursor.getChoice(149) != 10) {
/* 1120 */       return false;
/*      */     }
/*      */     
/* 1123 */     if (this.cursor.getChoice(162) == 28) {
/* 1124 */       return true;
/*      */     }
/*      */     
/* 1127 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getEndOfCatchupTargetId() {
/* 1132 */     if (this.cursor.getChoice(149) != 10) {
/* 1133 */       return -1;
/*      */     }
/*      */     
/* 1136 */     if (this.cursor.getChoice(162) != 28) {
/* 1137 */       return -1;
/*      */     }
/*      */     
/* 1140 */     return this.cursor.getInt(89);
/*      */   }
/*      */   
/*      */   public long[] getEndOfCatchupPubends() {
/* 1144 */     if (this.cursor.getChoice(149) != 10) {
/* 1145 */       return null;
/*      */     }
/*      */     
/* 1148 */     if (this.cursor.getChoice(162) != 28) {
/* 1149 */       return null;
/*      */     }
/*      */     
/* 1152 */     MessageBodyHandle[] peTbl = this.cursor.getTable(90);
/* 1153 */     long[] rslt = new long[peTbl.length];
/* 1154 */     for (int i = 0; i < peTbl.length; i++) {
/* 1155 */       rslt[i] = peTbl[i].getLong(0);
/*      */     }
/* 1157 */     return rslt;
/*      */   }
/*      */   
/*      */   public boolean isTargetedSilence() {
/* 1161 */     if (this.cursor.getChoice(149) != 10) {
/* 1162 */       return false;
/*      */     }
/*      */     
/* 1165 */     if (this.cursor.getChoice(162) == 30) {
/* 1166 */       if (this.cursor.getInt(95) >= 0) {
/* 1167 */         return true;
/*      */       }
/* 1169 */       return false;
/*      */     } 
/*      */     
/* 1172 */     return false;
/*      */   }
/*      */   
/*      */   public int getSilenceTargetId() {
/* 1176 */     if (this.cursor.getChoice(149) != 10) {
/* 1177 */       return -1;
/*      */     }
/*      */     
/* 1180 */     if (this.cursor.getChoice(162) == 30) {
/* 1181 */       return this.cursor.getInt(95);
/*      */     }
/*      */     
/* 1184 */     return -1;
/*      */   }
/*      */   
/*      */   public long getSilencePubend() {
/* 1188 */     if (this.cursor.getChoice(149) != 10) {
/* 1189 */       return 0L;
/*      */     }
/*      */     
/* 1192 */     if (this.cursor.getChoice(149) == 10 && this.cursor
/* 1193 */       .getChoice(162) == 30) {
/* 1194 */       return this.cursor.getLong(96);
/*      */     }
/*      */     
/* 1197 */     return 0L;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/* 1204 */     return "Topic=" + getTopic() + ",Qop=" + this.qop + ",Prio=" + getPriority() + ",Type=" + 
/* 1205 */       getType();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MessageHandle getMessageHandle() {
/* 1212 */     if (debug.debugIt(32)) {
/* 1213 */       debug.debug(-165922073994779L, "getMessageHandle");
/*      */     }
/*      */     
/* 1216 */     if (debug.debugIt(64)) {
/* 1217 */       debug.debug(-142394261359015L, "getMessageHandle", this.cursor);
/*      */     }
/*      */     
/* 1220 */     return this.cursor;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public InitialStateProcessor getISP() {
/* 1228 */     return null;
/*      */   }
/*      */   
/*      */   public void free() {}
/*      */   
/*      */   public Jgram getJgram() {
/* 1234 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getSubId() {
/* 1239 */     throw Assert.failureError(ExceptionBuilder.buildReasonString(486164081, null));
/*      */   }
/*      */   
/*      */   public int getVersion() {
/* 1243 */     throw Assert.failureError(ExceptionBuilder.buildReasonString(486164081, null));
/*      */   }
/*      */   
/*      */   public void setVersion(int version) {
/* 1247 */     throw Assert.failureError(ExceptionBuilder.buildReasonString(486164081, null));
/*      */   }
/*      */   
/*      */   public byte[] getFrame() {
/* 1251 */     return this.frame;
/*      */   }
/*      */ 
/*      */   
/*      */   public void bindToMatchingContext(MatchingContext context) {
/* 1256 */     context.setMessage(this.cursor);
/*      */   }
/*      */   
/*      */   public Jgram convertToJgram() {
/* 1260 */     return this;
/*      */   }
/*      */   
/*      */   public String getSubPoint() {
/* 1264 */     return "";
/*      */   }
/*      */ 
/*      */   
/*      */   public final synchronized void setDoneProcessing() {
/* 1269 */     this.doneProcessing = true;
/*      */   }
/*      */   
/*      */   public final synchronized boolean getDoneProcessing() {
/* 1273 */     return this.doneProcessing;
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
/*      */   public void promoteQop(byte qop) {
/* 1288 */     if (debug.debugIt(32)) {
/* 1289 */       debug.debug(-165922073994779L, "promoteQop", Byte.valueOf(qop));
/*      */     }
/*      */ 
/*      */     
/* 1293 */     if ((SessionConfig.getSessionConfig()).MULTICAST_ENABLED) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1303 */       if (qop > 1 || qop != this.qop) {
/*      */ 
/*      */         
/* 1306 */         this.digestCache = null;
/* 1307 */         if (this.cursor != null && this.cursor instanceof StandardMessageDataHandle) {
/* 1308 */           ((StandardMessageDataHandle)this.cursor).getVals();
/*      */         }
/*      */       } 
/* 1311 */       this.qop = qop;
/*      */     } 
/*      */     
/* 1314 */     if (debug.debugIt(64))
/* 1315 */       debug.debug(-142394261359015L, "promoteQop"); 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\bridge\Jgram.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */