/*      */ package com.ibm.disthub2.impl.formats;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class Envelop
/*      */   extends Schema
/*      */ {
/*      */   public Envelop() {
/* 1204 */     super(new byte[] { 1, 1, 8, -100, -4, -100, 1, -100, 0, 1, 1, 0, -101, 2, -101, 1, -101, -2, 0, 1, 2, 0, 2, 0, 1, 2, 0, 0, 0, 1, 2, 0, 6, -101, 6, -101, 6, -101, 1, -101, 1, -101, 1, 1, 0, 1, 19, 0, 5, 6, 0, 1, 2, 0, 1, -2, 0, 1, 2, 0, 1, 5, 0, 1, 6, 0, 0, 2, -2, -2, 1, 5, 8, 0, 1, 2, 0, 1, -2, 0, 1, 2, 0, 1, 6, 0, 1, 2, 0, 1, -2, 1, 6, 1, 0, 1, 3, 0, -1, 2, -2, 0, 1, 9, 0, 1, 1, 1, 2, 1, 3, 1, 5, 1, 6, 1, 7, 1, 8, 1, -2, 1, -1, 0, 1, 8, 0, 0, 1, -3, 1, -4, 1, -2, -1, 1, 0, 1, 11, 0, 1, 1, 1, 2, 1, 3, 1, 4, 1, 5, 1, 6, 1, 7, 1, 8, 1, -2, 1, -4, -1, 2, -2, 0, 1, 11, 0, 1, 1, 1, 2, 1, 3, 1, 4, 1, 5, 1, 6, 1, 7, 1, 8, 1, -2, 1, -4, 1, -1, 1, -1, 0, 1, 2, 0, 1, 5, 0, 0, 2, 6, 5, 4, 5, 5, 5, 0, 1, 2, 0, 2, -2, -2, 2, 5, 5, 2, 1, 0, 1, 1, -1, 3, -2, -2, -2, 2, 2, -2, 1, 5, 2, 0, 1, 2, 0, 1, 5, 0, 1, 36, 0, 4, -2, -2, 0, 1, 2, 0, 2, 5, -2, 0, 1, 2, 0, 2, 1, 1, 3, 5, 0, 1, 2, 0, 3, -2, 0, 1, 1, -1, 2, 6, 6, 0, 1, 1, -1, 2, 6, 6, 0, 1, 2, 0, 2, 1, 0, 1, 1, -1, 8, -2, -2, -2, 1, 1, 2, -4, 6, 2, 5, 0, 1, 2, 0, 2, 5, -2, 1, 0, 1, 2, 0, 1, 5, 0, 0, 0, 0, 0, 0, 0, 0, 1, 5, 2, -2, 0, 1, 1, -1, 1, -2, 2, -2, 0, 1, 1, -1, 2, -2, -2, 1, -2, 1, 5, 1, -2, 1, 5, 1, -2, 1, 5, 4, -2, -2, -2, 0, 1, 1, -1, 2, 6, 6, 3, -2, 5, 0, 1, 1, -1, 2, 6, 6, 2, -2, -2, -1, 3, -2, -2, -2, 3, -2, 1, 0, 1, 1, -1, 2, 6, 6, 1, 5, 2, 5, 0, 1, 1, -1, 1, 6, 4, 5, 6, 6, 6, 4, 5, 6, 6, 6, 2, 6, 6, 1, -4, 1, -4, 1, 0, 1, 1, -1, 8, -2, -2, -2, 1, 1, 2, -4, 6, 1, -2, 1, -1, 6, 6, 6, 6, 6, -2, -2, 5, 6, -2, -2, -2, -2, 5, 6, -2, -2, -2, -2, 9, 6, 6, 1, 1, 1, -2, -2, -2, -2, 7, 6, 6, 6, 6, 1, -2, -2, 3, 6, -2, -2, 3, 6, -2, -2 }, "com.ibm.disthub.impl.formats.Envelop");
/*      */   }
/*      */   
/*      */   public static final class Symbols extends SymbolTable { public Symbols() {
/* 1208 */       super(new Object[] { "mdt", "qopQuery", "priority", "nonStop", "topic", "pubendID", "timestamp", "rel1_2_force", "curiousOnly", "rel1_2_curiousD", "persistent", "mid", "present_reply", "present_oldTrack", "oldSubscribeReq_subject", "oldSubscribeReq_query", "body_id", "present_JMSCorrelationID", "present_JMSTimestamp", "present_JMSType", "JMSDeliveryMode", "JMSExpiration", "JMSRedelivered", new SymbolTable(new Object[] { "name", "booleanType", "byteType", "shortType", "intType", "longType", "floatType", "doubleType", "stringType", "nextRow", "primaryType", "value" }, new String[][] { { null, "stdProperties" }, , { "unknown", "booleanType", "byteType", "shortType", "intType", "longType", "floatType", "doubleType", "stringType" },  }, "stdProperties"), "schematizedProperties", "ObjectMessage", "BytesMessage", "TextMessage", new SymbolTable(new Object[] { "booleanType", "byteType", "shortType", "charType", "intType", "longType", "floatType", "doubleType", "stringType", "bytesType", "nextRow", "primaryType", "value" }, new String[][] { { null, "StreamMessage" }, , { "unknown", "booleanType", "byteType", "shortType", "charType", "intType", "longType", "floatType", "doubleType", "stringType", "bytesType" },  }, "StreamMessage"), new SymbolTable(new Object[] { "name", "booleanType", "byteType", "shortType", "charType", "intType", "longType", "floatType", "doubleType", "stringType", "bytesType", "nextRow", "primaryType", "value" }, new String[][] { { null, "MapMessage" }, , { "unknown", "booleanType", "byteType", "shortType", "charType", "intType", "longType", "floatType", "doubleType", "stringType", "bytesType" },  }, "MapMessage"), "SchematizedJMSMessage", "SchematizedNormalMessage", "present_tid", "error_id", "error_code", "server", "connection", "client", "clientId", "publisherId", "connFail_code", "version", "featureExchange_query", new SymbolTable(new Object[] { "featureName", "paramName", "paramValue", "nextRow", "primaryType" }, new String[][] { { null, "featureTable" },  }, "featureTable"), "qopCode", "qopUpdate_subject", "notUnderstood", "present_track", "subscribeReq_subject", "subscribeReq_query", "gdflags_present_mode", "dsName", "enable", "reliable", "subscribeReply_id", "present_reconnId", new SymbolTable(new Object[] { "pubend", "tmin", "nextRow", "primaryType" }, new String[][] { { null, "srTminsTable" },  }, "srTminsTable"), new SymbolTable(new Object[] { "pubend", "timestamp", "nextRow", "primaryType" }, new String[][] { { null, "srStartTimeTable" },  }, "srStartTimeTable"), "enabled", new SymbolTable(new Object[] { "topic", "partitionLabel", "groupAddress", "enabled", "reliable", "qop", "key", "timeStamp", "nextRow", "primaryType" }, new String[][] { { null, "topicsTable" },  }, "topicsTable"), "unsubscribeReq_id", "dsflags_present_mode", "name", "present_URStatus", "publishReply", "topicQuery_topicKey", new SymbolTable(new Object[] { "name", "nextRow", "primaryType" }, new String[][] { { null, "propertyKeys" },  }, "propertyKeys"), "topicQueryReply_topicKey", new SymbolTable(new Object[] { "name", "value", "nextRow", "primaryType" }, new String[][] { { null, "propertyValues" },  }, "propertyValues"), "setClientIdReq", "setClientIdReply", "setPublisherIdReq", "setPublisherIdReply", "deactivateReq", "deactivateReply", "reactivateReq_reconnId", "reactivateReq_topic", "reactivateReq_query", new SymbolTable(new Object[] { "pubend", "timestamp", "nextRow", "primaryType" }, new String[][] { { null, "rrStartTimeTable" },  }, "rrStartTimeTable"), "reactivateReply_reconnId", "reactivateReply_id", new SymbolTable(new Object[] { "pubend", "timestamp", "nextRow", "primaryType" }, new String[][] { { null, "rrTminsTable" },  }, "rrTminsTable"), "querySubscriptionsReq_topic", "querySubscriptionsReq_query", new SymbolTable(new Object[] { "topic", "query", "reconnId", "nextRow", "primaryType" }, new String[][] { { null, "querySubscriptionsReply" },  }, "querySubscriptionsReply"), "releaseReq_reconnId", "replyExpected", new SymbolTable(new Object[] { "pubend", "timestamp", "nextRow", "primaryType" }, new String[][] { { null, "rrReleaseTimeTable" },  }, "rrReleaseTimeTable"), "releaseReply", "endOfCatchup_id", new SymbolTable(new Object[] { "pubendID", "nextRow", "primaryType" }, new String[][] { { null, "pubendsTable" },  }, "pubendsTable"), "gap_id", "gap_pubend", "gap_beginTS", "gap_endTS", "silence_id", "silence_pubend", "silence_beginTS", "silence_endTS", "start", "numAvail", "multicastControlReq_message", "multicastControlReply_message", new SymbolTable(new Object[] { "topic", "partitionLabel", "groupAddress", "enabled", "reliable", "qop", "key", "timeStamp", "nextRow", "primaryType" }, new String[][] { { null, "topicsUpdateTable" },  }, "topicsUpdateTable"), "multicastTopicsUpdateReq_topic", "state", "preValue_lPrefix", "preValue_finalPrefix", "preValue_startstamp", "preValue_endstamp", "preValue_srcCellFrom", "preValue_srcCellTo", "ackPrefix", "ack_srcCellFrom", "ack_srcCellTo", "ack_dstCellFrom", "ack_dstCellTo", "releasePrefix", "releaseReply_srcCellFrom", "releaseReply_srcCellTo", "releaseReply_dstCellFrom", "releaseReply_dstCellTo", "nack_startstamp", "nack_endstamp", "nack_force", "isCs", "nack_curiousD", "nack_srcCellFrom", "nack_srcCellTo", "nack_dstCellFrom", "nack_dstCellTo", "silence_lPrefix", "silence_finalPrefix", "silence_startstamp", "silence_endstamp", "silence_force", "silence_srcCellFrom", "silence_srcCellTo", "ackExpected_stamp", "ackExpected_srcCellFrom", "ackExpected_srcCellTo", "releaseExpected_stamp", "releaseExpected_srcCellFrom", "releaseExpected_srcCellTo", "primaryType", "extendh", "extend0", "topicFormat", "extend1", "payload", "normal_reply", "normal_oldTrack", "normal_body", "jms_JMSCorrelationID", "jms_JMSTimestamp", "jms_JMSType", "properties", "JMSbody", "normal_tid", "cpId", "feature", "singleHopCtl_track", "singleHopCtl_body", "gdflags", "subscribeReq_multicastflags", "gdsFlags", "present_tmins", "present_startTime", "subscribeReply_multicastflags", "present_topics", "dsflags", "unsubscribeReply_URStatus", "topicQuery", "topicQueryReply", "reactivateReq_startTime", "reactivateReply_tmins", "releaseTime", "pubends", "multicastTopicsUpdate_topics" }new String[][] { { null }, , { null }, , { null, "rel1_1" }, , { "prefixed", "unprefixed" }, , { "absent", "rel1_2" }, , { "unknown", "normal", "pingReq", "pingReply", "error", "connGrant", "connFail", "featureExchange", "qopUpdate", "notUnderstood", "singleHopCtl", "multi", "preValue", "ack", "releaseReply", "nack", "silence", "ackExpected", "releaseExpected" }, , { "absent", "present" }, , { "absent", "present" }, , { "unknown", "simplectl", "oldSubscribeReq", "id", "jms", "SchematizedNormalMessage" }, , { "absent", "present" }, , { "absent", "present" }, , { "absent", "present" }, , { "absent", "stdProperties", "schematizedProperties" }, , { "unknown", "Message", "ObjectMessage", "BytesMessage", "TextMessage", "StreamMessage", "MapMessage", "SchematizedJMSMessage" }, , { "absent", "present" }, , { "absent", "present" }, , { "featureTable" }, , { "absent", "present" }, , { "unknown", "subscribeReq", "subscribeReply", "unsubscribeReq", "unsubscribeReply", "startDeliveryReq", "startDeliveryReply", "stopDeliveryReq", "stopDeliveryReply", "disconnectReq", "disconnectReply", "VMX", "notUnderstood", "publishReply", "topicQuery", "topicQueryReply", "setClientIdReq", "setClientIdReply", "setPublisherIdReq", "setPublisherIdReply", "deactivateReq", "deactivateReply", "reactivateReq", "reactivateReply", "querySubscriptionsReq", "querySubscriptionsReply", "releaseReq", "releaseReply", "endOfCatchup", "gap", "silence", "freeWindowAdvertisement", "multicastControlReq", "multicastControlReply", "multicastTopicsUpdate", "multicastTopicsUpdateReq" }, , { "absent", "present" }, , { "absent", "present" }, , { "absent", "present" }, , { "srTminsTable" }, , { "srStartTimeTable" }, , { "absent", "present" }, , { "topicsTable" }, , { "absent", "present" }, , { "absent", "present" }, , { "propertyKeys" }, , { "propertyValues" }, , { "rrStartTimeTable" }, , { "rrTminsTable" }, , { "rrReleaseTimeTable" }, , { "pubendsTable" }, , { "topicsUpdateTable" },  }, null);
/*      */     } }
/*      */ 
/*      */   
/*      */   public static interface Constants {
/*      */     public static final int mdt = 0;
/*      */     public static final int qopQuery = 1;
/*      */     public static final int priority = 2;
/*      */     public static final int nonStop = 3;
/*      */     public static final int topic = 4;
/*      */     public static final int pubendID = 5;
/*      */     public static final int rel1_2_pubendID = 5;
/*      */     public static final int extend1_rel1_2_pubendID = 5;
/*      */     public static final int rel1_1_extend1_rel1_2_pubendID = 5;
/*      */     public static final int extend0_rel1_1_extend1_rel1_2_pubendID = 5;
/*      */     public static final int timestamp = 6;
/*      */     public static final int rel1_2_timestamp = 6;
/*      */     public static final int extend1_rel1_2_timestamp = 6;
/*      */     public static final int rel1_1_extend1_rel1_2_timestamp = 6;
/*      */     public static final int extend0_rel1_1_extend1_rel1_2_timestamp = 6;
/*      */     public static final int rel1_2_force = 7;
/*      */     public static final int extend1_rel1_2_force = 7;
/*      */     public static final int rel1_1_extend1_rel1_2_force = 7;
/*      */     public static final int extend0_rel1_1_extend1_rel1_2_force = 7;
/*      */     public static final int curiousOnly = 8;
/*      */     public static final int rel1_2_curiousOnly = 8;
/*      */     public static final int extend1_rel1_2_curiousOnly = 8;
/*      */     public static final int rel1_1_extend1_rel1_2_curiousOnly = 8;
/*      */     public static final int extend0_rel1_1_extend1_rel1_2_curiousOnly = 8;
/*      */     public static final int rel1_2_curiousD = 9;
/*      */     public static final int extend1_rel1_2_curiousD = 9;
/*      */     public static final int rel1_1_extend1_rel1_2_curiousD = 9;
/*      */     public static final int extend0_rel1_1_extend1_rel1_2_curiousD = 9;
/*      */     public static final int persistent = 10;
/*      */     public static final int rel1_2_persistent = 10;
/*      */     public static final int extend1_rel1_2_persistent = 10;
/*      */     public static final int rel1_1_extend1_rel1_2_persistent = 10;
/*      */     public static final int extend0_rel1_1_extend1_rel1_2_persistent = 10;
/*      */     public static final int mid = 11;
/*      */     public static final int normal_mid = 11;
/*      */     public static final int payload_normal_mid = 11;
/*      */     public static final int present_reply = 12;
/*      */     public static final int reply_present_reply = 12;
/*      */     public static final int normal_reply_present_reply = 12;
/*      */     public static final int payload_normal_reply_present_reply = 12;
/*      */     public static final int present_oldTrack = 13;
/*      */     public static final int oldTrack_present_oldTrack = 13;
/*      */     public static final int normal_oldTrack_present_oldTrack = 13;
/*      */     public static final int payload_normal_oldTrack_present_oldTrack = 13;
/*      */     public static final int oldSubscribeReq_subject = 14;
/*      */     public static final int body_oldSubscribeReq_subject = 14;
/*      */     public static final int normal_body_oldSubscribeReq_subject = 14;
/*      */     public static final int payload_normal_body_oldSubscribeReq_subject = 14;
/*      */     public static final int oldSubscribeReq_query = 15;
/*      */     public static final int body_oldSubscribeReq_query = 15;
/*      */     public static final int normal_body_oldSubscribeReq_query = 15;
/*      */     public static final int payload_normal_body_oldSubscribeReq_query = 15;
/*      */     public static final int body_id = 16;
/*      */     public static final int normal_body_id = 16;
/*      */     public static final int payload_normal_body_id = 16;
/*      */     public static final int present_JMSCorrelationID = 17;
/*      */     public static final int JMSCorrelationID_present_JMSCorrelationID = 17;
/*      */     public static final int jms_JMSCorrelationID_present_JMSCorrelationID = 17;
/*      */     public static final int body_jms_JMSCorrelationID_present_JMSCorrelationID = 17;
/*      */     public static final int normal_body_jms_JMSCorrelationID_present_JMSCorrelationID = 17;
/*      */     public static final int payload_normal_body_jms_JMSCorrelationID_present_JMSCorrelationID = 17;
/*      */     public static final int present_JMSTimestamp = 18;
/*      */     public static final int JMSTimestamp_present_JMSTimestamp = 18;
/*      */     public static final int jms_JMSTimestamp_present_JMSTimestamp = 18;
/*      */     public static final int body_jms_JMSTimestamp_present_JMSTimestamp = 18;
/*      */     public static final int normal_body_jms_JMSTimestamp_present_JMSTimestamp = 18;
/*      */     public static final int payload_normal_body_jms_JMSTimestamp_present_JMSTimestamp = 18;
/*      */     public static final int present_JMSType = 19;
/*      */     public static final int JMSType_present_JMSType = 19;
/*      */     public static final int jms_JMSType_present_JMSType = 19;
/*      */     public static final int body_jms_JMSType_present_JMSType = 19;
/*      */     public static final int normal_body_jms_JMSType_present_JMSType = 19;
/*      */     public static final int payload_normal_body_jms_JMSType_present_JMSType = 19;
/*      */     public static final int JMSDeliveryMode = 20;
/*      */     public static final int jms_JMSDeliveryMode = 20;
/*      */     public static final int body_jms_JMSDeliveryMode = 20;
/*      */     public static final int normal_body_jms_JMSDeliveryMode = 20;
/*      */     public static final int payload_normal_body_jms_JMSDeliveryMode = 20;
/*      */     public static final int JMSExpiration = 21;
/*      */     public static final int jms_JMSExpiration = 21;
/*      */     public static final int body_jms_JMSExpiration = 21;
/*      */     public static final int normal_body_jms_JMSExpiration = 21;
/*      */     public static final int payload_normal_body_jms_JMSExpiration = 21;
/*      */     public static final int JMSRedelivered = 22;
/*      */     public static final int jms_JMSRedelivered = 22;
/*      */     public static final int body_jms_JMSRedelivered = 22;
/*      */     public static final int normal_body_jms_JMSRedelivered = 22;
/*      */     public static final int payload_normal_body_jms_JMSRedelivered = 22;
/*      */     public static final int stdProperties = 23;
/*      */     public static final int properties_stdProperties = 23;
/*      */     public static final int jms_properties_stdProperties = 23;
/*      */     public static final int body_jms_properties_stdProperties = 23;
/*      */     public static final int normal_body_jms_properties_stdProperties = 23;
/*      */     public static final int payload_normal_body_jms_properties_stdProperties = 23;
/*      */     public static final int schematizedProperties = 24;
/*      */     public static final int properties_schematizedProperties = 24;
/*      */     public static final int jms_properties_schematizedProperties = 24;
/*      */     public static final int body_jms_properties_schematizedProperties = 24;
/*      */     public static final int normal_body_jms_properties_schematizedProperties = 24;
/*      */     public static final int payload_normal_body_jms_properties_schematizedProperties = 24;
/*      */     public static final int ObjectMessage = 25;
/*      */     public static final int JMSbody_ObjectMessage = 25;
/*      */     public static final int jms_JMSbody_ObjectMessage = 25;
/*      */     public static final int body_jms_JMSbody_ObjectMessage = 25;
/*      */     public static final int normal_body_jms_JMSbody_ObjectMessage = 25;
/*      */     public static final int payload_normal_body_jms_JMSbody_ObjectMessage = 25;
/*      */     public static final int BytesMessage = 26;
/*      */     public static final int JMSbody_BytesMessage = 26;
/*      */     public static final int jms_JMSbody_BytesMessage = 26;
/*      */     public static final int body_jms_JMSbody_BytesMessage = 26;
/*      */     public static final int normal_body_jms_JMSbody_BytesMessage = 26;
/*      */     public static final int payload_normal_body_jms_JMSbody_BytesMessage = 26;
/*      */     public static final int TextMessage = 27;
/*      */     public static final int JMSbody_TextMessage = 27;
/*      */     public static final int jms_JMSbody_TextMessage = 27;
/*      */     public static final int body_jms_JMSbody_TextMessage = 27;
/*      */     public static final int normal_body_jms_JMSbody_TextMessage = 27;
/*      */     public static final int payload_normal_body_jms_JMSbody_TextMessage = 27;
/*      */     public static final int StreamMessage = 28;
/*      */     public static final int JMSbody_StreamMessage = 28;
/*      */     public static final int jms_JMSbody_StreamMessage = 28;
/*      */     public static final int body_jms_JMSbody_StreamMessage = 28;
/*      */     public static final int normal_body_jms_JMSbody_StreamMessage = 28;
/*      */     public static final int payload_normal_body_jms_JMSbody_StreamMessage = 28;
/*      */     public static final int MapMessage = 29;
/*      */     public static final int JMSbody_MapMessage = 29;
/*      */     public static final int jms_JMSbody_MapMessage = 29;
/*      */     public static final int body_jms_JMSbody_MapMessage = 29;
/*      */     public static final int normal_body_jms_JMSbody_MapMessage = 29;
/*      */     public static final int payload_normal_body_jms_JMSbody_MapMessage = 29;
/*      */     public static final int SchematizedJMSMessage = 30;
/*      */     public static final int JMSbody_SchematizedJMSMessage = 30;
/*      */     public static final int jms_JMSbody_SchematizedJMSMessage = 30;
/*      */     public static final int body_jms_JMSbody_SchematizedJMSMessage = 30;
/*      */     public static final int normal_body_jms_JMSbody_SchematizedJMSMessage = 30;
/*      */     public static final int payload_normal_body_jms_JMSbody_SchematizedJMSMessage = 30;
/*      */     public static final int SchematizedNormalMessage = 31;
/*      */     public static final int body_SchematizedNormalMessage = 31;
/*      */     public static final int normal_body_SchematizedNormalMessage = 31;
/*      */     public static final int payload_normal_body_SchematizedNormalMessage = 31;
/*      */     public static final int present_tid = 32;
/*      */     public static final int tid_present_tid = 32;
/*      */     public static final int normal_tid_present_tid = 32;
/*      */     public static final int payload_normal_tid_present_tid = 32;
/*      */     public static final int error_id = 33;
/*      */     public static final int payload_error_id = 33;
/*      */     public static final int error_code = 34;
/*      */     public static final int payload_error_code = 34;
/*      */     public static final int server = 35;
/*      */     public static final int connGrant_server = 35;
/*      */     public static final int payload_connGrant_server = 35;
/*      */     public static final int connection = 36;
/*      */     public static final int connGrant_connection = 36;
/*      */     public static final int payload_connGrant_connection = 36;
/*      */     public static final int client = 37;
/*      */     public static final int connGrant_client = 37;
/*      */     public static final int payload_connGrant_client = 37;
/*      */     public static final int clientId = 38;
/*      */     public static final int present_clientId = 38;
/*      */     public static final int cpId_present_clientId = 38;
/*      */     public static final int connGrant_cpId_present_clientId = 38;
/*      */     public static final int payload_connGrant_cpId_present_clientId = 38;
/*      */     public static final int publisherId = 39;
/*      */     public static final int present_publisherId = 39;
/*      */     public static final int cpId_present_publisherId = 39;
/*      */     public static final int connGrant_cpId_present_publisherId = 39;
/*      */     public static final int payload_connGrant_cpId_present_publisherId = 39;
/*      */     public static final int connFail_code = 40;
/*      */     public static final int payload_connFail_code = 40;
/*      */     public static final int version = 41;
/*      */     public static final int connFail_version = 41;
/*      */     public static final int payload_connFail_version = 41;
/*      */     public static final int featureExchange_query = 42;
/*      */     public static final int payload_featureExchange_query = 42;
/*      */     public static final int featureTable = 43;
/*      */     public static final int feature_featureTable = 43;
/*      */     public static final int featureExchange_feature_featureTable = 43;
/*      */     public static final int payload_featureExchange_feature_featureTable = 43;
/*      */     public static final int qopCode = 44;
/*      */     public static final int qopUpdate_qopCode = 44;
/*      */     public static final int payload_qopUpdate_qopCode = 44;
/*      */     public static final int qopUpdate_subject = 45;
/*      */     public static final int payload_qopUpdate_subject = 45;
/*      */     public static final int notUnderstood = 46;
/*      */     public static final int payload_notUnderstood = 46;
/*      */     public static final int present_track = 47;
/*      */     public static final int track_present_track = 47;
/*      */     public static final int singleHopCtl_track_present_track = 47;
/*      */     public static final int payload_singleHopCtl_track_present_track = 47;
/*      */     public static final int subscribeReq_subject = 48;
/*      */     public static final int body_subscribeReq_subject = 48;
/*      */     public static final int singleHopCtl_body_subscribeReq_subject = 48;
/*      */     public static final int payload_singleHopCtl_body_subscribeReq_subject = 48;
/*      */     public static final int subscribeReq_query = 49;
/*      */     public static final int body_subscribeReq_query = 49;
/*      */     public static final int singleHopCtl_body_subscribeReq_query = 49;
/*      */     public static final int payload_singleHopCtl_body_subscribeReq_query = 49;
/*      */     public static final int gdflags_present_mode = 50;
/*      */     public static final int subscribeReq_gdflags_present_mode = 50;
/*      */     public static final int body_subscribeReq_gdflags_present_mode = 50;
/*      */     public static final int singleHopCtl_body_subscribeReq_gdflags_present_mode = 50;
/*      */     public static final int payload_singleHopCtl_body_subscribeReq_gdflags_present_mode = 50;
/*      */     public static final int dsName = 51;
/*      */     public static final int present_dsName = 51;
/*      */     public static final int gdflags_present_dsName = 51;
/*      */     public static final int subscribeReq_gdflags_present_dsName = 51;
/*      */     public static final int body_subscribeReq_gdflags_present_dsName = 51;
/*      */     public static final int singleHopCtl_body_subscribeReq_gdflags_present_dsName = 51;
/*      */     public static final int payload_singleHopCtl_body_subscribeReq_gdflags_present_dsName = 51;
/*      */     public static final int enable = 52;
/*      */     public static final int present_enable = 52;
/*      */     public static final int multicastflags_present_enable = 52;
/*      */     public static final int subscribeReq_multicastflags_present_enable = 52;
/*      */     public static final int body_subscribeReq_multicastflags_present_enable = 52;
/*      */     public static final int singleHopCtl_body_subscribeReq_multicastflags_present_enable = 52;
/*      */     public static final int payload_singleHopCtl_body_subscribeReq_multicastflags_present_enable = 52;
/*      */     public static final int reliable = 53;
/*      */     public static final int present_reliable = 53;
/*      */     public static final int multicastflags_present_reliable = 53;
/*      */     public static final int subscribeReq_multicastflags_present_reliable = 53;
/*      */     public static final int body_subscribeReq_multicastflags_present_reliable = 53;
/*      */     public static final int singleHopCtl_body_subscribeReq_multicastflags_present_reliable = 53;
/*      */     public static final int payload_singleHopCtl_body_subscribeReq_multicastflags_present_reliable = 53;
/*      */     public static final int subscribeReply_id = 54;
/*      */     public static final int body_subscribeReply_id = 54;
/*      */     public static final int singleHopCtl_body_subscribeReply_id = 54;
/*      */     public static final int payload_singleHopCtl_body_subscribeReply_id = 54;
/*      */     public static final int present_reconnId = 55;
/*      */     public static final int gdsFlags_present_reconnId = 55;
/*      */     public static final int subscribeReply_gdsFlags_present_reconnId = 55;
/*      */     public static final int body_subscribeReply_gdsFlags_present_reconnId = 55;
/*      */     public static final int singleHopCtl_body_subscribeReply_gdsFlags_present_reconnId = 55;
/*      */     public static final int payload_singleHopCtl_body_subscribeReply_gdsFlags_present_reconnId = 55;
/*      */     public static final int srTminsTable = 56;
/*      */     public static final int tmins_srTminsTable = 56;
/*      */     public static final int present_tmins_srTminsTable = 56;
/*      */     public static final int gdsFlags_present_tmins_srTminsTable = 56;
/*      */     public static final int subscribeReply_gdsFlags_present_tmins_srTminsTable = 56;
/*      */     public static final int body_subscribeReply_gdsFlags_present_tmins_srTminsTable = 56;
/*      */     public static final int singleHopCtl_body_subscribeReply_gdsFlags_present_tmins_srTminsTable = 56;
/*      */     public static final int payload_singleHopCtl_body_subscribeReply_gdsFlags_present_tmins_srTminsTable = 56;
/*      */     public static final int srStartTimeTable = 57;
/*      */     public static final int startTime_srStartTimeTable = 57;
/*      */     public static final int present_startTime_srStartTimeTable = 57;
/*      */     public static final int gdsFlags_present_startTime_srStartTimeTable = 57;
/*      */     public static final int subscribeReply_gdsFlags_present_startTime_srStartTimeTable = 57;
/*      */     public static final int body_subscribeReply_gdsFlags_present_startTime_srStartTimeTable = 57;
/*      */     public static final int singleHopCtl_body_subscribeReply_gdsFlags_present_startTime_srStartTimeTable = 57;
/*      */     public static final int payload_singleHopCtl_body_subscribeReply_gdsFlags_present_startTime_srStartTimeTable = 57;
/*      */     public static final int enabled = 58;
/*      */     public static final int present_enabled = 58;
/*      */     public static final int multicastflags_present_enabled = 58;
/*      */     public static final int subscribeReply_multicastflags_present_enabled = 58;
/*      */     public static final int body_subscribeReply_multicastflags_present_enabled = 58;
/*      */     public static final int singleHopCtl_body_subscribeReply_multicastflags_present_enabled = 58;
/*      */     public static final int payload_singleHopCtl_body_subscribeReply_multicastflags_present_enabled = 58;
/*      */     public static final int topicsTable = 59;
/*      */     public static final int topics_topicsTable = 59;
/*      */     public static final int present_topics_topicsTable = 59;
/*      */     public static final int multicastflags_present_topics_topicsTable = 59;
/*      */     public static final int subscribeReply_multicastflags_present_topics_topicsTable = 59;
/*      */     public static final int body_subscribeReply_multicastflags_present_topics_topicsTable = 59;
/*      */     public static final int singleHopCtl_body_subscribeReply_multicastflags_present_topics_topicsTable = 59;
/*      */     public static final int payload_singleHopCtl_body_subscribeReply_multicastflags_present_topics_topicsTable = 59;
/*      */     public static final int unsubscribeReq_id = 60;
/*      */     public static final int body_unsubscribeReq_id = 60;
/*      */     public static final int singleHopCtl_body_unsubscribeReq_id = 60;
/*      */     public static final int payload_singleHopCtl_body_unsubscribeReq_id = 60;
/*      */     public static final int dsflags_present_mode = 61;
/*      */     public static final int unsubscribeReq_dsflags_present_mode = 61;
/*      */     public static final int body_unsubscribeReq_dsflags_present_mode = 61;
/*      */     public static final int singleHopCtl_body_unsubscribeReq_dsflags_present_mode = 61;
/*      */     public static final int payload_singleHopCtl_body_unsubscribeReq_dsflags_present_mode = 61;
/*      */     public static final int name = 62;
/*      */     public static final int present_name = 62;
/*      */     public static final int dsflags_present_name = 62;
/*      */     public static final int unsubscribeReq_dsflags_present_name = 62;
/*      */     public static final int body_unsubscribeReq_dsflags_present_name = 62;
/*      */     public static final int singleHopCtl_body_unsubscribeReq_dsflags_present_name = 62;
/*      */     public static final int payload_singleHopCtl_body_unsubscribeReq_dsflags_present_name = 62;
/*      */     public static final int present_URStatus = 63;
/*      */     public static final int URStatus_present_URStatus = 63;
/*      */     public static final int unsubscribeReply_URStatus_present_URStatus = 63;
/*      */     public static final int body_unsubscribeReply_URStatus_present_URStatus = 63;
/*      */     public static final int singleHopCtl_body_unsubscribeReply_URStatus_present_URStatus = 63;
/*      */     public static final int payload_singleHopCtl_body_unsubscribeReply_URStatus_present_URStatus = 63;
/*      */     public static final int publishReply = 64;
/*      */     public static final int body_publishReply = 64;
/*      */     public static final int singleHopCtl_body_publishReply = 64;
/*      */     public static final int payload_singleHopCtl_body_publishReply = 64;
/*      */     public static final int topicQuery_topicKey = 65;
/*      */     public static final int body_topicQuery_topicKey = 65;
/*      */     public static final int singleHopCtl_body_topicQuery_topicKey = 65;
/*      */     public static final int payload_singleHopCtl_body_topicQuery_topicKey = 65;
/*      */     public static final int propertyKeys = 66;
/*      */     public static final int topicQuery_propertyKeys = 66;
/*      */     public static final int body_topicQuery_propertyKeys = 66;
/*      */     public static final int singleHopCtl_body_topicQuery_propertyKeys = 66;
/*      */     public static final int payload_singleHopCtl_body_topicQuery_propertyKeys = 66;
/*      */     public static final int topicQueryReply_topicKey = 67;
/*      */     public static final int body_topicQueryReply_topicKey = 67;
/*      */     public static final int singleHopCtl_body_topicQueryReply_topicKey = 67;
/*      */     public static final int payload_singleHopCtl_body_topicQueryReply_topicKey = 67;
/*      */     public static final int propertyValues = 68;
/*      */     public static final int topicQueryReply_propertyValues = 68;
/*      */     public static final int body_topicQueryReply_propertyValues = 68;
/*      */     public static final int singleHopCtl_body_topicQueryReply_propertyValues = 68;
/*      */     public static final int payload_singleHopCtl_body_topicQueryReply_propertyValues = 68;
/*      */     public static final int setClientIdReq = 69;
/*      */     public static final int body_setClientIdReq = 69;
/*      */     public static final int singleHopCtl_body_setClientIdReq = 69;
/*      */     public static final int payload_singleHopCtl_body_setClientIdReq = 69;
/*      */     public static final int setClientIdReply = 70;
/*      */     public static final int body_setClientIdReply = 70;
/*      */     public static final int singleHopCtl_body_setClientIdReply = 70;
/*      */     public static final int payload_singleHopCtl_body_setClientIdReply = 70;
/*      */     public static final int setPublisherIdReq = 71;
/*      */     public static final int body_setPublisherIdReq = 71;
/*      */     public static final int singleHopCtl_body_setPublisherIdReq = 71;
/*      */     public static final int payload_singleHopCtl_body_setPublisherIdReq = 71;
/*      */     public static final int setPublisherIdReply = 72;
/*      */     public static final int body_setPublisherIdReply = 72;
/*      */     public static final int singleHopCtl_body_setPublisherIdReply = 72;
/*      */     public static final int payload_singleHopCtl_body_setPublisherIdReply = 72;
/*      */     public static final int deactivateReq = 73;
/*      */     public static final int body_deactivateReq = 73;
/*      */     public static final int singleHopCtl_body_deactivateReq = 73;
/*      */     public static final int payload_singleHopCtl_body_deactivateReq = 73;
/*      */     public static final int deactivateReply = 74;
/*      */     public static final int body_deactivateReply = 74;
/*      */     public static final int singleHopCtl_body_deactivateReply = 74;
/*      */     public static final int payload_singleHopCtl_body_deactivateReply = 74;
/*      */     public static final int reactivateReq_reconnId = 75;
/*      */     public static final int body_reactivateReq_reconnId = 75;
/*      */     public static final int singleHopCtl_body_reactivateReq_reconnId = 75;
/*      */     public static final int payload_singleHopCtl_body_reactivateReq_reconnId = 75;
/*      */     public static final int reactivateReq_topic = 76;
/*      */     public static final int body_reactivateReq_topic = 76;
/*      */     public static final int singleHopCtl_body_reactivateReq_topic = 76;
/*      */     public static final int payload_singleHopCtl_body_reactivateReq_topic = 76;
/*      */     public static final int reactivateReq_query = 77;
/*      */     public static final int body_reactivateReq_query = 77;
/*      */     public static final int singleHopCtl_body_reactivateReq_query = 77;
/*      */     public static final int payload_singleHopCtl_body_reactivateReq_query = 77;
/*      */     public static final int rrStartTimeTable = 78;
/*      */     public static final int startTime_rrStartTimeTable = 78;
/*      */     public static final int reactivateReq_startTime_rrStartTimeTable = 78;
/*      */     public static final int body_reactivateReq_startTime_rrStartTimeTable = 78;
/*      */     public static final int singleHopCtl_body_reactivateReq_startTime_rrStartTimeTable = 78;
/*      */     public static final int payload_singleHopCtl_body_reactivateReq_startTime_rrStartTimeTable = 78;
/*      */     public static final int reactivateReply_reconnId = 79;
/*      */     public static final int body_reactivateReply_reconnId = 79;
/*      */     public static final int singleHopCtl_body_reactivateReply_reconnId = 79;
/*      */     public static final int payload_singleHopCtl_body_reactivateReply_reconnId = 79;
/*      */     public static final int reactivateReply_id = 80;
/*      */     public static final int body_reactivateReply_id = 80;
/*      */     public static final int singleHopCtl_body_reactivateReply_id = 80;
/*      */     public static final int payload_singleHopCtl_body_reactivateReply_id = 80;
/*      */     public static final int rrTminsTable = 81;
/*      */     public static final int tmins_rrTminsTable = 81;
/*      */     public static final int reactivateReply_tmins_rrTminsTable = 81;
/*      */     public static final int body_reactivateReply_tmins_rrTminsTable = 81;
/*      */     public static final int singleHopCtl_body_reactivateReply_tmins_rrTminsTable = 81;
/*      */     public static final int payload_singleHopCtl_body_reactivateReply_tmins_rrTminsTable = 81;
/*      */     public static final int querySubscriptionsReq_topic = 82;
/*      */     public static final int body_querySubscriptionsReq_topic = 82;
/*      */     public static final int singleHopCtl_body_querySubscriptionsReq_topic = 82;
/*      */     public static final int payload_singleHopCtl_body_querySubscriptionsReq_topic = 82;
/*      */     public static final int querySubscriptionsReq_query = 83;
/*      */     public static final int body_querySubscriptionsReq_query = 83;
/*      */     public static final int singleHopCtl_body_querySubscriptionsReq_query = 83;
/*      */     public static final int payload_singleHopCtl_body_querySubscriptionsReq_query = 83;
/*      */     public static final int querySubscriptionsReply = 84;
/*      */     public static final int body_querySubscriptionsReply = 84;
/*      */     public static final int singleHopCtl_body_querySubscriptionsReply = 84;
/*      */     public static final int payload_singleHopCtl_body_querySubscriptionsReply = 84;
/*      */     public static final int releaseReq_reconnId = 85;
/*      */     public static final int body_releaseReq_reconnId = 85;
/*      */     public static final int singleHopCtl_body_releaseReq_reconnId = 85;
/*      */     public static final int payload_singleHopCtl_body_releaseReq_reconnId = 85;
/*      */     public static final int replyExpected = 86;
/*      */     public static final int releaseReq_replyExpected = 86;
/*      */     public static final int body_releaseReq_replyExpected = 86;
/*      */     public static final int singleHopCtl_body_releaseReq_replyExpected = 86;
/*      */     public static final int payload_singleHopCtl_body_releaseReq_replyExpected = 86;
/*      */     public static final int rrReleaseTimeTable = 87;
/*      */     public static final int releaseTime_rrReleaseTimeTable = 87;
/*      */     public static final int releaseReq_releaseTime_rrReleaseTimeTable = 87;
/*      */     public static final int body_releaseReq_releaseTime_rrReleaseTimeTable = 87;
/*      */     public static final int singleHopCtl_body_releaseReq_releaseTime_rrReleaseTimeTable = 87;
/*      */     public static final int payload_singleHopCtl_body_releaseReq_releaseTime_rrReleaseTimeTable = 87;
/*      */     public static final int releaseReply = 88;
/*      */     public static final int body_releaseReply = 88;
/*      */     public static final int singleHopCtl_body_releaseReply = 88;
/*      */     public static final int payload_singleHopCtl_body_releaseReply = 88;
/*      */     public static final int endOfCatchup_id = 89;
/*      */     public static final int body_endOfCatchup_id = 89;
/*      */     public static final int singleHopCtl_body_endOfCatchup_id = 89;
/*      */     public static final int payload_singleHopCtl_body_endOfCatchup_id = 89;
/*      */     public static final int pubendsTable = 90;
/*      */     public static final int pubends_pubendsTable = 90;
/*      */     public static final int endOfCatchup_pubends_pubendsTable = 90;
/*      */     public static final int body_endOfCatchup_pubends_pubendsTable = 90;
/*      */     public static final int singleHopCtl_body_endOfCatchup_pubends_pubendsTable = 90;
/*      */     public static final int payload_singleHopCtl_body_endOfCatchup_pubends_pubendsTable = 90;
/*      */     public static final int gap_id = 91;
/*      */     public static final int body_gap_id = 91;
/*      */     public static final int singleHopCtl_body_gap_id = 91;
/*      */     public static final int payload_singleHopCtl_body_gap_id = 91;
/*      */     public static final int gap_pubend = 92;
/*      */     public static final int body_gap_pubend = 92;
/*      */     public static final int singleHopCtl_body_gap_pubend = 92;
/*      */     public static final int payload_singleHopCtl_body_gap_pubend = 92;
/*      */     public static final int gap_beginTS = 93;
/*      */     public static final int body_gap_beginTS = 93;
/*      */     public static final int singleHopCtl_body_gap_beginTS = 93;
/*      */     public static final int payload_singleHopCtl_body_gap_beginTS = 93;
/*      */     public static final int gap_endTS = 94;
/*      */     public static final int body_gap_endTS = 94;
/*      */     public static final int singleHopCtl_body_gap_endTS = 94;
/*      */     public static final int payload_singleHopCtl_body_gap_endTS = 94;
/*      */     public static final int silence_id = 95;
/*      */     public static final int body_silence_id = 95;
/*      */     public static final int singleHopCtl_body_silence_id = 95;
/*      */     public static final int payload_singleHopCtl_body_silence_id = 95;
/*      */     public static final int silence_pubend = 96;
/*      */     public static final int body_silence_pubend = 96;
/*      */     public static final int singleHopCtl_body_silence_pubend = 96;
/*      */     public static final int payload_singleHopCtl_body_silence_pubend = 96;
/*      */     public static final int silence_beginTS = 97;
/*      */     public static final int body_silence_beginTS = 97;
/*      */     public static final int singleHopCtl_body_silence_beginTS = 97;
/*      */     public static final int payload_singleHopCtl_body_silence_beginTS = 97;
/*      */     public static final int silence_endTS = 98;
/*      */     public static final int body_silence_endTS = 98;
/*      */     public static final int singleHopCtl_body_silence_endTS = 98;
/*      */     public static final int payload_singleHopCtl_body_silence_endTS = 98;
/*      */     public static final int start = 99;
/*      */     public static final int freeWindowAdvertisement_start = 99;
/*      */     public static final int body_freeWindowAdvertisement_start = 99;
/*      */     public static final int singleHopCtl_body_freeWindowAdvertisement_start = 99;
/*      */     public static final int payload_singleHopCtl_body_freeWindowAdvertisement_start = 99;
/*      */     public static final int numAvail = 100;
/*      */     public static final int freeWindowAdvertisement_numAvail = 100;
/*      */     public static final int body_freeWindowAdvertisement_numAvail = 100;
/*      */     public static final int singleHopCtl_body_freeWindowAdvertisement_numAvail = 100;
/*      */     public static final int payload_singleHopCtl_body_freeWindowAdvertisement_numAvail = 100;
/*      */     public static final int multicastControlReq_message = 101;
/*      */     public static final int body_multicastControlReq_message = 101;
/*      */     public static final int singleHopCtl_body_multicastControlReq_message = 101;
/*      */     public static final int payload_singleHopCtl_body_multicastControlReq_message = 101;
/*      */     public static final int multicastControlReply_message = 102;
/*      */     public static final int body_multicastControlReply_message = 102;
/*      */     public static final int singleHopCtl_body_multicastControlReply_message = 102;
/*      */     public static final int payload_singleHopCtl_body_multicastControlReply_message = 102;
/*      */     public static final int topicsUpdateTable = 103;
/*      */     public static final int topics_topicsUpdateTable = 103;
/*      */     public static final int multicastTopicsUpdate_topics_topicsUpdateTable = 103;
/*      */     public static final int body_multicastTopicsUpdate_topics_topicsUpdateTable = 103;
/*      */     public static final int singleHopCtl_body_multicastTopicsUpdate_topics_topicsUpdateTable = 103;
/*      */     public static final int payload_singleHopCtl_body_multicastTopicsUpdate_topics_topicsUpdateTable = 103;
/*      */     public static final int multicastTopicsUpdateReq_topic = 104;
/*      */     public static final int body_multicastTopicsUpdateReq_topic = 104;
/*      */     public static final int singleHopCtl_body_multicastTopicsUpdateReq_topic = 104;
/*      */     public static final int payload_singleHopCtl_body_multicastTopicsUpdateReq_topic = 104;
/*      */     public static final int state = 105;
/*      */     public static final int multi_state = 105;
/*      */     public static final int payload_multi_state = 105;
/*      */     public static final int preValue_lPrefix = 106;
/*      */     public static final int payload_preValue_lPrefix = 106;
/*      */     public static final int preValue_finalPrefix = 107;
/*      */     public static final int payload_preValue_finalPrefix = 107;
/*      */     public static final int preValue_startstamp = 108;
/*      */     public static final int payload_preValue_startstamp = 108;
/*      */     public static final int preValue_endstamp = 109;
/*      */     public static final int payload_preValue_endstamp = 109;
/*      */     public static final int preValue_srcCellFrom = 110;
/*      */     public static final int payload_preValue_srcCellFrom = 110;
/*      */     public static final int preValue_srcCellTo = 111;
/*      */     public static final int payload_preValue_srcCellTo = 111;
/*      */     public static final int ackPrefix = 112;
/*      */     public static final int ack_ackPrefix = 112;
/*      */     public static final int payload_ack_ackPrefix = 112;
/*      */     public static final int ack_srcCellFrom = 113;
/*      */     public static final int payload_ack_srcCellFrom = 113;
/*      */     public static final int ack_srcCellTo = 114;
/*      */     public static final int payload_ack_srcCellTo = 114;
/*      */     public static final int ack_dstCellFrom = 115;
/*      */     public static final int payload_ack_dstCellFrom = 115;
/*      */     public static final int ack_dstCellTo = 116;
/*      */     public static final int payload_ack_dstCellTo = 116;
/*      */     public static final int releasePrefix = 117;
/*      */     public static final int releaseReply_releasePrefix = 117;
/*      */     public static final int payload_releaseReply_releasePrefix = 117;
/*      */     public static final int releaseReply_srcCellFrom = 118;
/*      */     public static final int payload_releaseReply_srcCellFrom = 118;
/*      */     public static final int releaseReply_srcCellTo = 119;
/*      */     public static final int payload_releaseReply_srcCellTo = 119;
/*      */     public static final int releaseReply_dstCellFrom = 120;
/*      */     public static final int payload_releaseReply_dstCellFrom = 120;
/*      */     public static final int releaseReply_dstCellTo = 121;
/*      */     public static final int payload_releaseReply_dstCellTo = 121;
/*      */     public static final int nack_startstamp = 122;
/*      */     public static final int payload_nack_startstamp = 122;
/*      */     public static final int nack_endstamp = 123;
/*      */     public static final int payload_nack_endstamp = 123;
/*      */     public static final int nack_force = 124;
/*      */     public static final int payload_nack_force = 124;
/*      */     public static final int isCs = 125;
/*      */     public static final int nack_isCs = 125;
/*      */     public static final int payload_nack_isCs = 125;
/*      */     public static final int nack_curiousD = 126;
/*      */     public static final int payload_nack_curiousD = 126;
/*      */     public static final int nack_srcCellFrom = 127;
/*      */     public static final int payload_nack_srcCellFrom = 127;
/*      */     public static final int nack_srcCellTo = 128;
/*      */     public static final int payload_nack_srcCellTo = 128;
/*      */     public static final int nack_dstCellFrom = 129;
/*      */     public static final int payload_nack_dstCellFrom = 129;
/*      */     public static final int nack_dstCellTo = 130;
/*      */     public static final int payload_nack_dstCellTo = 130;
/*      */     public static final int silence_lPrefix = 131;
/*      */     public static final int payload_silence_lPrefix = 131;
/*      */     public static final int silence_finalPrefix = 132;
/*      */     public static final int payload_silence_finalPrefix = 132;
/*      */     public static final int silence_startstamp = 133;
/*      */     public static final int payload_silence_startstamp = 133;
/*      */     public static final int silence_endstamp = 134;
/*      */     public static final int payload_silence_endstamp = 134;
/*      */     public static final int silence_force = 135;
/*      */     public static final int payload_silence_force = 135;
/*      */     public static final int silence_srcCellFrom = 136;
/*      */     public static final int payload_silence_srcCellFrom = 136;
/*      */     public static final int silence_srcCellTo = 137;
/*      */     public static final int payload_silence_srcCellTo = 137;
/*      */     public static final int ackExpected_stamp = 138;
/*      */     public static final int payload_ackExpected_stamp = 138;
/*      */     public static final int ackExpected_srcCellFrom = 139;
/*      */     public static final int payload_ackExpected_srcCellFrom = 139;
/*      */     public static final int ackExpected_srcCellTo = 140;
/*      */     public static final int payload_ackExpected_srcCellTo = 140;
/*      */     public static final int releaseExpected_stamp = 141;
/*      */     public static final int payload_releaseExpected_stamp = 141;
/*      */     public static final int releaseExpected_srcCellFrom = 142;
/*      */     public static final int payload_releaseExpected_srcCellFrom = 142;
/*      */     public static final int releaseExpected_srcCellTo = 143;
/*      */     public static final int payload_releaseExpected_srcCellTo = 143;
/*      */     public static final int primaryType = 144;
/*      */     public static final int extendh = 145;
/*      */     public static final int extend0 = 146;
/*      */     public static final int topicFormat = 147;
/*      */     public static final int rel1_1_topicFormat = 147;
/*      */     public static final int extend0_rel1_1_topicFormat = 147;
/*      */     public static final int extend1 = 148;
/*      */     public static final int rel1_1_extend1 = 148;
/*      */     public static final int extend0_rel1_1_extend1 = 148;
/*      */     public static final int payload = 149;
/*      */     public static final int normal_reply = 150;
/*      */     public static final int payload_normal_reply = 150;
/*      */     public static final int normal_oldTrack = 151;
/*      */     public static final int payload_normal_oldTrack = 151;
/*      */     public static final int normal_body = 152;
/*      */     public static final int payload_normal_body = 152;
/*      */     public static final int jms_JMSCorrelationID = 153;
/*      */     public static final int body_jms_JMSCorrelationID = 153;
/*      */     public static final int normal_body_jms_JMSCorrelationID = 153;
/*      */     public static final int payload_normal_body_jms_JMSCorrelationID = 153;
/*      */     public static final int jms_JMSTimestamp = 154;
/*      */     public static final int body_jms_JMSTimestamp = 154;
/*      */     public static final int normal_body_jms_JMSTimestamp = 154;
/*      */     public static final int payload_normal_body_jms_JMSTimestamp = 154;
/*      */     public static final int jms_JMSType = 155;
/*      */     public static final int body_jms_JMSType = 155;
/*      */     public static final int normal_body_jms_JMSType = 155;
/*      */     public static final int payload_normal_body_jms_JMSType = 155;
/*      */     public static final int properties = 156;
/*      */     public static final int jms_properties = 156;
/*      */     public static final int body_jms_properties = 156;
/*      */     public static final int normal_body_jms_properties = 156;
/*      */     public static final int payload_normal_body_jms_properties = 156;
/*      */     public static final int JMSbody = 157;
/*      */     public static final int jms_JMSbody = 157;
/*      */     public static final int body_jms_JMSbody = 157;
/*      */     public static final int normal_body_jms_JMSbody = 157;
/*      */     public static final int payload_normal_body_jms_JMSbody = 157;
/*      */     public static final int normal_tid = 158;
/*      */     public static final int payload_normal_tid = 158;
/*      */     public static final int cpId = 159;
/*      */     public static final int connGrant_cpId = 159;
/*      */     public static final int payload_connGrant_cpId = 159;
/*      */     public static final int feature = 160;
/*      */     public static final int featureExchange_feature = 160;
/*      */     public static final int payload_featureExchange_feature = 160;
/*      */     public static final int singleHopCtl_track = 161;
/*      */     public static final int payload_singleHopCtl_track = 161;
/*      */     public static final int singleHopCtl_body = 162;
/*      */     public static final int payload_singleHopCtl_body = 162;
/*      */     public static final int gdflags = 163;
/*      */     public static final int subscribeReq_gdflags = 163;
/*      */     public static final int body_subscribeReq_gdflags = 163;
/*      */     public static final int singleHopCtl_body_subscribeReq_gdflags = 163;
/*      */     public static final int payload_singleHopCtl_body_subscribeReq_gdflags = 163;
/*      */     public static final int subscribeReq_multicastflags = 164;
/*      */     public static final int body_subscribeReq_multicastflags = 164;
/*      */     public static final int singleHopCtl_body_subscribeReq_multicastflags = 164;
/*      */     public static final int payload_singleHopCtl_body_subscribeReq_multicastflags = 164;
/*      */     public static final int gdsFlags = 165;
/*      */     public static final int subscribeReply_gdsFlags = 165;
/*      */     public static final int body_subscribeReply_gdsFlags = 165;
/*      */     public static final int singleHopCtl_body_subscribeReply_gdsFlags = 165;
/*      */     public static final int payload_singleHopCtl_body_subscribeReply_gdsFlags = 165;
/*      */     public static final int present_tmins = 166;
/*      */     public static final int gdsFlags_present_tmins = 166;
/*      */     public static final int subscribeReply_gdsFlags_present_tmins = 166;
/*      */     public static final int body_subscribeReply_gdsFlags_present_tmins = 166;
/*      */     public static final int singleHopCtl_body_subscribeReply_gdsFlags_present_tmins = 166;
/*      */     public static final int payload_singleHopCtl_body_subscribeReply_gdsFlags_present_tmins = 166;
/*      */     public static final int present_startTime = 167;
/*      */     public static final int gdsFlags_present_startTime = 167;
/*      */     public static final int subscribeReply_gdsFlags_present_startTime = 167;
/*      */     public static final int body_subscribeReply_gdsFlags_present_startTime = 167;
/*      */     public static final int singleHopCtl_body_subscribeReply_gdsFlags_present_startTime = 167;
/*      */     public static final int payload_singleHopCtl_body_subscribeReply_gdsFlags_present_startTime = 167;
/*      */     public static final int subscribeReply_multicastflags = 168;
/*      */     public static final int body_subscribeReply_multicastflags = 168;
/*      */     public static final int singleHopCtl_body_subscribeReply_multicastflags = 168;
/*      */     public static final int payload_singleHopCtl_body_subscribeReply_multicastflags = 168;
/*      */     public static final int present_topics = 169;
/*      */     public static final int multicastflags_present_topics = 169;
/*      */     public static final int subscribeReply_multicastflags_present_topics = 169;
/*      */     public static final int body_subscribeReply_multicastflags_present_topics = 169;
/*      */     public static final int singleHopCtl_body_subscribeReply_multicastflags_present_topics = 169;
/*      */     public static final int payload_singleHopCtl_body_subscribeReply_multicastflags_present_topics = 169;
/*      */     public static final int dsflags = 170;
/*      */     public static final int unsubscribeReq_dsflags = 170;
/*      */     public static final int body_unsubscribeReq_dsflags = 170;
/*      */     public static final int singleHopCtl_body_unsubscribeReq_dsflags = 170;
/*      */     public static final int payload_singleHopCtl_body_unsubscribeReq_dsflags = 170;
/*      */     public static final int unsubscribeReply_URStatus = 171;
/*      */     public static final int body_unsubscribeReply_URStatus = 171;
/*      */     public static final int singleHopCtl_body_unsubscribeReply_URStatus = 171;
/*      */     public static final int payload_singleHopCtl_body_unsubscribeReply_URStatus = 171;
/*      */     public static final int topicQuery = 172;
/*      */     public static final int body_topicQuery = 172;
/*      */     public static final int singleHopCtl_body_topicQuery = 172;
/*      */     public static final int payload_singleHopCtl_body_topicQuery = 172;
/*      */     public static final int topicQueryReply = 173;
/*      */     public static final int body_topicQueryReply = 173;
/*      */     public static final int singleHopCtl_body_topicQueryReply = 173;
/*      */     public static final int payload_singleHopCtl_body_topicQueryReply = 173;
/*      */     public static final int reactivateReq_startTime = 174;
/*      */     public static final int body_reactivateReq_startTime = 174;
/*      */     public static final int singleHopCtl_body_reactivateReq_startTime = 174;
/*      */     public static final int payload_singleHopCtl_body_reactivateReq_startTime = 174;
/*      */     public static final int reactivateReply_tmins = 175;
/*      */     public static final int body_reactivateReply_tmins = 175;
/*      */     public static final int singleHopCtl_body_reactivateReply_tmins = 175;
/*      */     public static final int payload_singleHopCtl_body_reactivateReply_tmins = 175;
/*      */     public static final int releaseTime = 176;
/*      */     public static final int releaseReq_releaseTime = 176;
/*      */     public static final int body_releaseReq_releaseTime = 176;
/*      */     public static final int singleHopCtl_body_releaseReq_releaseTime = 176;
/*      */     public static final int payload_singleHopCtl_body_releaseReq_releaseTime = 176;
/*      */     public static final int pubends = 177;
/*      */     public static final int endOfCatchup_pubends = 177;
/*      */     public static final int body_endOfCatchup_pubends = 177;
/*      */     public static final int singleHopCtl_body_endOfCatchup_pubends = 177;
/*      */     public static final int payload_singleHopCtl_body_endOfCatchup_pubends = 177;
/*      */     public static final int multicastTopicsUpdate_topics = 178;
/*      */     public static final int body_multicastTopicsUpdate_topics = 178;
/*      */     public static final int singleHopCtl_body_multicastTopicsUpdate_topics = 178;
/*      */     public static final int payload_singleHopCtl_body_multicastTopicsUpdate_topics = 178;
/*      */     public static final int extend0_is_rel1_1 = 1;
/*      */     public static final int topicFormat_is_prefixed = 0;
/*      */     public static final int rel1_1_topicFormat_is_prefixed = 0;
/*      */     public static final int extend0_rel1_1_topicFormat_is_prefixed = 0;
/*      */     public static final int topicFormat_is_unprefixed = 1;
/*      */     public static final int rel1_1_topicFormat_is_unprefixed = 1;
/*      */     public static final int extend0_rel1_1_topicFormat_is_unprefixed = 1;
/*      */     public static final int extend1_is_absent = 0;
/*      */     public static final int rel1_1_extend1_is_absent = 0;
/*      */     public static final int extend0_rel1_1_extend1_is_absent = 0;
/*      */     public static final int extend1_is_rel1_2 = 1;
/*      */     public static final int rel1_1_extend1_is_rel1_2 = 1;
/*      */     public static final int extend0_rel1_1_extend1_is_rel1_2 = 1;
/*      */     public static final int payload_is_unknown = 0;
/*      */     public static final int payload_is_normal = 1;
/*      */     public static final int payload_is_pingReq = 2;
/*      */     public static final int payload_is_pingReply = 3;
/*      */     public static final int payload_is_error = 4;
/*      */     public static final int payload_is_connGrant = 5;
/*      */     public static final int payload_is_connFail = 6;
/*      */     public static final int payload_is_featureExchange = 7;
/*      */     public static final int payload_is_qopUpdate = 8;
/*      */     public static final int payload_is_notUnderstood = 9;
/*      */     public static final int payload_is_singleHopCtl = 10;
/*      */     public static final int payload_is_multi = 11;
/*      */     public static final int payload_is_preValue = 12;
/*      */     public static final int payload_is_ack = 13;
/*      */     public static final int payload_is_releaseReply = 14;
/*      */     public static final int payload_is_nack = 15;
/*      */     public static final int payload_is_silence = 16;
/*      */     public static final int payload_is_ackExpected = 17;
/*      */     public static final int payload_is_releaseExpected = 18;
/*      */     public static final int normal_reply_is_absent = 0;
/*      */     public static final int payload_normal_reply_is_absent = 0;
/*      */     public static final int normal_reply_is_present = 1;
/*      */     public static final int payload_normal_reply_is_present = 1;
/*      */     public static final int normal_oldTrack_is_absent = 0;
/*      */     public static final int payload_normal_oldTrack_is_absent = 0;
/*      */     public static final int normal_oldTrack_is_present = 1;
/*      */     public static final int payload_normal_oldTrack_is_present = 1;
/*      */     public static final int normal_body_is_unknown = 0;
/*      */     public static final int payload_normal_body_is_unknown = 0;
/*      */     public static final int normal_body_is_simplectl = 1;
/*      */     public static final int payload_normal_body_is_simplectl = 1;
/*      */     public static final int normal_body_is_oldSubscribeReq = 2;
/*      */     public static final int payload_normal_body_is_oldSubscribeReq = 2;
/*      */     public static final int normal_body_is_id = 3;
/*      */     public static final int payload_normal_body_is_id = 3;
/*      */     public static final int normal_body_is_jms = 4;
/*      */     public static final int payload_normal_body_is_jms = 4;
/*      */     public static final int normal_body_is_SchematizedNormalMessage = 5;
/*      */     public static final int payload_normal_body_is_SchematizedNormalMessage = 5;
/*      */     public static final int jms_JMSCorrelationID_is_absent = 0;
/*      */     public static final int body_jms_JMSCorrelationID_is_absent = 0;
/*      */     public static final int normal_body_jms_JMSCorrelationID_is_absent = 0;
/*      */     public static final int payload_normal_body_jms_JMSCorrelationID_is_absent = 0;
/*      */     public static final int jms_JMSCorrelationID_is_present = 1;
/*      */     public static final int body_jms_JMSCorrelationID_is_present = 1;
/*      */     public static final int normal_body_jms_JMSCorrelationID_is_present = 1;
/*      */     public static final int payload_normal_body_jms_JMSCorrelationID_is_present = 1;
/*      */     public static final int jms_JMSTimestamp_is_absent = 0;
/*      */     public static final int body_jms_JMSTimestamp_is_absent = 0;
/*      */     public static final int normal_body_jms_JMSTimestamp_is_absent = 0;
/*      */     public static final int payload_normal_body_jms_JMSTimestamp_is_absent = 0;
/*      */     public static final int jms_JMSTimestamp_is_present = 1;
/*      */     public static final int body_jms_JMSTimestamp_is_present = 1;
/*      */     public static final int normal_body_jms_JMSTimestamp_is_present = 1;
/*      */     public static final int payload_normal_body_jms_JMSTimestamp_is_present = 1;
/*      */     public static final int jms_JMSType_is_absent = 0;
/*      */     public static final int body_jms_JMSType_is_absent = 0;
/*      */     public static final int normal_body_jms_JMSType_is_absent = 0;
/*      */     public static final int payload_normal_body_jms_JMSType_is_absent = 0;
/*      */     public static final int jms_JMSType_is_present = 1;
/*      */     public static final int body_jms_JMSType_is_present = 1;
/*      */     public static final int normal_body_jms_JMSType_is_present = 1;
/*      */     public static final int payload_normal_body_jms_JMSType_is_present = 1;
/*      */     public static final int properties_is_absent = 0;
/*      */     public static final int jms_properties_is_absent = 0;
/*      */     public static final int body_jms_properties_is_absent = 0;
/*      */     public static final int normal_body_jms_properties_is_absent = 0;
/*      */     public static final int payload_normal_body_jms_properties_is_absent = 0;
/*      */     public static final int properties_is_stdProperties = 1;
/*      */     public static final int jms_properties_is_stdProperties = 1;
/*      */     public static final int body_jms_properties_is_stdProperties = 1;
/*      */     public static final int normal_body_jms_properties_is_stdProperties = 1;
/*      */     public static final int payload_normal_body_jms_properties_is_stdProperties = 1;
/*      */     public static final int properties_is_schematizedProperties = 2;
/*      */     public static final int jms_properties_is_schematizedProperties = 2;
/*      */     public static final int body_jms_properties_is_schematizedProperties = 2;
/*      */     public static final int normal_body_jms_properties_is_schematizedProperties = 2;
/*      */     public static final int payload_normal_body_jms_properties_is_schematizedProperties = 2;
/*      */     public static final int JMSbody_is_unknown = 0;
/*      */     public static final int jms_JMSbody_is_unknown = 0;
/*      */     public static final int body_jms_JMSbody_is_unknown = 0;
/*      */     public static final int normal_body_jms_JMSbody_is_unknown = 0;
/*      */     public static final int payload_normal_body_jms_JMSbody_is_unknown = 0;
/*      */     public static final int JMSbody_is_Message = 1;
/*      */     public static final int jms_JMSbody_is_Message = 1;
/*      */     public static final int body_jms_JMSbody_is_Message = 1;
/*      */     public static final int normal_body_jms_JMSbody_is_Message = 1;
/*      */     public static final int payload_normal_body_jms_JMSbody_is_Message = 1;
/*      */     public static final int JMSbody_is_ObjectMessage = 2;
/*      */     public static final int jms_JMSbody_is_ObjectMessage = 2;
/*      */     public static final int body_jms_JMSbody_is_ObjectMessage = 2;
/*      */     public static final int normal_body_jms_JMSbody_is_ObjectMessage = 2;
/*      */     public static final int payload_normal_body_jms_JMSbody_is_ObjectMessage = 2;
/*      */     public static final int JMSbody_is_BytesMessage = 3;
/*      */     public static final int jms_JMSbody_is_BytesMessage = 3;
/*      */     public static final int body_jms_JMSbody_is_BytesMessage = 3;
/*      */     public static final int normal_body_jms_JMSbody_is_BytesMessage = 3;
/*      */     public static final int payload_normal_body_jms_JMSbody_is_BytesMessage = 3;
/*      */     public static final int JMSbody_is_TextMessage = 4;
/*      */     public static final int jms_JMSbody_is_TextMessage = 4;
/*      */     public static final int body_jms_JMSbody_is_TextMessage = 4;
/*      */     public static final int normal_body_jms_JMSbody_is_TextMessage = 4;
/*      */     public static final int payload_normal_body_jms_JMSbody_is_TextMessage = 4;
/*      */     public static final int JMSbody_is_StreamMessage = 5;
/*      */     public static final int jms_JMSbody_is_StreamMessage = 5;
/*      */     public static final int body_jms_JMSbody_is_StreamMessage = 5;
/*      */     public static final int normal_body_jms_JMSbody_is_StreamMessage = 5;
/*      */     public static final int payload_normal_body_jms_JMSbody_is_StreamMessage = 5;
/*      */     public static final int JMSbody_is_MapMessage = 6;
/*      */     public static final int jms_JMSbody_is_MapMessage = 6;
/*      */     public static final int body_jms_JMSbody_is_MapMessage = 6;
/*      */     public static final int normal_body_jms_JMSbody_is_MapMessage = 6;
/*      */     public static final int payload_normal_body_jms_JMSbody_is_MapMessage = 6;
/*      */     public static final int JMSbody_is_SchematizedJMSMessage = 7;
/*      */     public static final int jms_JMSbody_is_SchematizedJMSMessage = 7;
/*      */     public static final int body_jms_JMSbody_is_SchematizedJMSMessage = 7;
/*      */     public static final int normal_body_jms_JMSbody_is_SchematizedJMSMessage = 7;
/*      */     public static final int payload_normal_body_jms_JMSbody_is_SchematizedJMSMessage = 7;
/*      */     public static final int normal_tid_is_absent = 0;
/*      */     public static final int payload_normal_tid_is_absent = 0;
/*      */     public static final int normal_tid_is_present = 1;
/*      */     public static final int payload_normal_tid_is_present = 1;
/*      */     public static final int cpId_is_absent = 0;
/*      */     public static final int connGrant_cpId_is_absent = 0;
/*      */     public static final int payload_connGrant_cpId_is_absent = 0;
/*      */     public static final int cpId_is_present = 1;
/*      */     public static final int connGrant_cpId_is_present = 1;
/*      */     public static final int payload_connGrant_cpId_is_present = 1;
/*      */     public static final int feature_is_featureTable = 0;
/*      */     public static final int featureExchange_feature_is_featureTable = 0;
/*      */     public static final int payload_featureExchange_feature_is_featureTable = 0;
/*      */     public static final int singleHopCtl_track_is_absent = 0;
/*      */     public static final int payload_singleHopCtl_track_is_absent = 0;
/*      */     public static final int singleHopCtl_track_is_present = 1;
/*      */     public static final int payload_singleHopCtl_track_is_present = 1;
/*      */     public static final int singleHopCtl_body_is_unknown = 0;
/*      */     public static final int payload_singleHopCtl_body_is_unknown = 0;
/*      */     public static final int singleHopCtl_body_is_subscribeReq = 1;
/*      */     public static final int payload_singleHopCtl_body_is_subscribeReq = 1;
/*      */     public static final int singleHopCtl_body_is_subscribeReply = 2;
/*      */     public static final int payload_singleHopCtl_body_is_subscribeReply = 2;
/*      */     public static final int singleHopCtl_body_is_unsubscribeReq = 3;
/*      */     public static final int payload_singleHopCtl_body_is_unsubscribeReq = 3;
/*      */     public static final int singleHopCtl_body_is_unsubscribeReply = 4;
/*      */     public static final int payload_singleHopCtl_body_is_unsubscribeReply = 4;
/*      */     public static final int singleHopCtl_body_is_startDeliveryReq = 5;
/*      */     public static final int payload_singleHopCtl_body_is_startDeliveryReq = 5;
/*      */     public static final int singleHopCtl_body_is_startDeliveryReply = 6;
/*      */     public static final int payload_singleHopCtl_body_is_startDeliveryReply = 6;
/*      */     public static final int singleHopCtl_body_is_stopDeliveryReq = 7;
/*      */     public static final int payload_singleHopCtl_body_is_stopDeliveryReq = 7;
/*      */     public static final int singleHopCtl_body_is_stopDeliveryReply = 8;
/*      */     public static final int payload_singleHopCtl_body_is_stopDeliveryReply = 8;
/*      */     public static final int singleHopCtl_body_is_disconnectReq = 9;
/*      */     public static final int payload_singleHopCtl_body_is_disconnectReq = 9;
/*      */     public static final int singleHopCtl_body_is_disconnectReply = 10;
/*      */     public static final int payload_singleHopCtl_body_is_disconnectReply = 10;
/*      */     public static final int singleHopCtl_body_is_VMX = 11;
/*      */     public static final int payload_singleHopCtl_body_is_VMX = 11;
/*      */     public static final int singleHopCtl_body_is_notUnderstood = 12;
/*      */     public static final int payload_singleHopCtl_body_is_notUnderstood = 12;
/*      */     public static final int singleHopCtl_body_is_publishReply = 13;
/*      */     public static final int payload_singleHopCtl_body_is_publishReply = 13;
/*      */     public static final int singleHopCtl_body_is_topicQuery = 14;
/*      */     public static final int payload_singleHopCtl_body_is_topicQuery = 14;
/*      */     public static final int singleHopCtl_body_is_topicQueryReply = 15;
/*      */     public static final int payload_singleHopCtl_body_is_topicQueryReply = 15;
/*      */     public static final int singleHopCtl_body_is_setClientIdReq = 16;
/*      */     public static final int payload_singleHopCtl_body_is_setClientIdReq = 16;
/*      */     public static final int singleHopCtl_body_is_setClientIdReply = 17;
/*      */     public static final int payload_singleHopCtl_body_is_setClientIdReply = 17;
/*      */     public static final int singleHopCtl_body_is_setPublisherIdReq = 18;
/*      */     public static final int payload_singleHopCtl_body_is_setPublisherIdReq = 18;
/*      */     public static final int singleHopCtl_body_is_setPublisherIdReply = 19;
/*      */     public static final int payload_singleHopCtl_body_is_setPublisherIdReply = 19;
/*      */     public static final int singleHopCtl_body_is_deactivateReq = 20;
/*      */     public static final int payload_singleHopCtl_body_is_deactivateReq = 20;
/*      */     public static final int singleHopCtl_body_is_deactivateReply = 21;
/*      */     public static final int payload_singleHopCtl_body_is_deactivateReply = 21;
/*      */     public static final int singleHopCtl_body_is_reactivateReq = 22;
/*      */     public static final int payload_singleHopCtl_body_is_reactivateReq = 22;
/*      */     public static final int singleHopCtl_body_is_reactivateReply = 23;
/*      */     public static final int payload_singleHopCtl_body_is_reactivateReply = 23;
/*      */     public static final int singleHopCtl_body_is_querySubscriptionsReq = 24;
/*      */     public static final int payload_singleHopCtl_body_is_querySubscriptionsReq = 24;
/*      */     public static final int singleHopCtl_body_is_querySubscriptionsReply = 25;
/*      */     public static final int payload_singleHopCtl_body_is_querySubscriptionsReply = 25;
/*      */     public static final int singleHopCtl_body_is_releaseReq = 26;
/*      */     public static final int payload_singleHopCtl_body_is_releaseReq = 26;
/*      */     public static final int singleHopCtl_body_is_releaseReply = 27;
/*      */     public static final int payload_singleHopCtl_body_is_releaseReply = 27;
/*      */     public static final int singleHopCtl_body_is_endOfCatchup = 28;
/*      */     public static final int payload_singleHopCtl_body_is_endOfCatchup = 28;
/*      */     public static final int singleHopCtl_body_is_gap = 29;
/*      */     public static final int payload_singleHopCtl_body_is_gap = 29;
/*      */     public static final int singleHopCtl_body_is_silence = 30;
/*      */     public static final int payload_singleHopCtl_body_is_silence = 30;
/*      */     public static final int singleHopCtl_body_is_freeWindowAdvertisement = 31;
/*      */     public static final int payload_singleHopCtl_body_is_freeWindowAdvertisement = 31;
/*      */     public static final int singleHopCtl_body_is_multicastControlReq = 32;
/*      */     public static final int payload_singleHopCtl_body_is_multicastControlReq = 32;
/*      */     public static final int singleHopCtl_body_is_multicastControlReply = 33;
/*      */     public static final int payload_singleHopCtl_body_is_multicastControlReply = 33;
/*      */     public static final int singleHopCtl_body_is_multicastTopicsUpdate = 34;
/*      */     public static final int payload_singleHopCtl_body_is_multicastTopicsUpdate = 34;
/*      */     public static final int singleHopCtl_body_is_multicastTopicsUpdateReq = 35;
/*      */     public static final int payload_singleHopCtl_body_is_multicastTopicsUpdateReq = 35;
/*      */     public static final int gdflags_is_absent = 0;
/*      */     public static final int subscribeReq_gdflags_is_absent = 0;
/*      */     public static final int body_subscribeReq_gdflags_is_absent = 0;
/*      */     public static final int singleHopCtl_body_subscribeReq_gdflags_is_absent = 0;
/*      */     public static final int payload_singleHopCtl_body_subscribeReq_gdflags_is_absent = 0;
/*      */     public static final int gdflags_is_present = 1;
/*      */     public static final int subscribeReq_gdflags_is_present = 1;
/*      */     public static final int body_subscribeReq_gdflags_is_present = 1;
/*      */     public static final int singleHopCtl_body_subscribeReq_gdflags_is_present = 1;
/*      */     public static final int payload_singleHopCtl_body_subscribeReq_gdflags_is_present = 1;
/*      */     public static final int subscribeReq_multicastflags_is_absent = 0;
/*      */     public static final int body_subscribeReq_multicastflags_is_absent = 0;
/*      */     public static final int singleHopCtl_body_subscribeReq_multicastflags_is_absent = 0;
/*      */     public static final int payload_singleHopCtl_body_subscribeReq_multicastflags_is_absent = 0;
/*      */     public static final int subscribeReq_multicastflags_is_present = 1;
/*      */     public static final int body_subscribeReq_multicastflags_is_present = 1;
/*      */     public static final int singleHopCtl_body_subscribeReq_multicastflags_is_present = 1;
/*      */     public static final int payload_singleHopCtl_body_subscribeReq_multicastflags_is_present = 1;
/*      */     public static final int gdsFlags_is_absent = 0;
/*      */     public static final int subscribeReply_gdsFlags_is_absent = 0;
/*      */     public static final int body_subscribeReply_gdsFlags_is_absent = 0;
/*      */     public static final int singleHopCtl_body_subscribeReply_gdsFlags_is_absent = 0;
/*      */     public static final int payload_singleHopCtl_body_subscribeReply_gdsFlags_is_absent = 0;
/*      */     public static final int gdsFlags_is_present = 1;
/*      */     public static final int subscribeReply_gdsFlags_is_present = 1;
/*      */     public static final int body_subscribeReply_gdsFlags_is_present = 1;
/*      */     public static final int singleHopCtl_body_subscribeReply_gdsFlags_is_present = 1;
/*      */     public static final int payload_singleHopCtl_body_subscribeReply_gdsFlags_is_present = 1;
/*      */     public static final int present_tmins_is_srTminsTable = 0;
/*      */     public static final int gdsFlags_present_tmins_is_srTminsTable = 0;
/*      */     public static final int subscribeReply_gdsFlags_present_tmins_is_srTminsTable = 0;
/*      */     public static final int body_subscribeReply_gdsFlags_present_tmins_is_srTminsTable = 0;
/*      */     public static final int singleHopCtl_body_subscribeReply_gdsFlags_present_tmins_is_srTminsTable = 0;
/*      */     public static final int payload_singleHopCtl_body_subscribeReply_gdsFlags_present_tmins_is_srTminsTable = 0;
/*      */     public static final int present_startTime_is_srStartTimeTable = 0;
/*      */     public static final int gdsFlags_present_startTime_is_srStartTimeTable = 0;
/*      */     public static final int subscribeReply_gdsFlags_present_startTime_is_srStartTimeTable = 0;
/*      */     public static final int body_subscribeReply_gdsFlags_present_startTime_is_srStartTimeTable = 0;
/*      */     public static final int singleHopCtl_body_subscribeReply_gdsFlags_present_startTime_is_srStartTimeTable = 0;
/*      */     public static final int payload_singleHopCtl_body_subscribeReply_gdsFlags_present_startTime_is_srStartTimeTable = 0;
/*      */     public static final int subscribeReply_multicastflags_is_absent = 0;
/*      */     public static final int body_subscribeReply_multicastflags_is_absent = 0;
/*      */     public static final int singleHopCtl_body_subscribeReply_multicastflags_is_absent = 0;
/*      */     public static final int payload_singleHopCtl_body_subscribeReply_multicastflags_is_absent = 0;
/*      */     public static final int subscribeReply_multicastflags_is_present = 1;
/*      */     public static final int body_subscribeReply_multicastflags_is_present = 1;
/*      */     public static final int singleHopCtl_body_subscribeReply_multicastflags_is_present = 1;
/*      */     public static final int payload_singleHopCtl_body_subscribeReply_multicastflags_is_present = 1;
/*      */     public static final int present_topics_is_topicsTable = 0;
/*      */     public static final int multicastflags_present_topics_is_topicsTable = 0;
/*      */     public static final int subscribeReply_multicastflags_present_topics_is_topicsTable = 0;
/*      */     public static final int body_subscribeReply_multicastflags_present_topics_is_topicsTable = 0;
/*      */     public static final int singleHopCtl_body_subscribeReply_multicastflags_present_topics_is_topicsTable = 0;
/*      */     public static final int payload_singleHopCtl_body_subscribeReply_multicastflags_present_topics_is_topicsTable = 0;
/*      */     public static final int dsflags_is_absent = 0;
/*      */     public static final int unsubscribeReq_dsflags_is_absent = 0;
/*      */     public static final int body_unsubscribeReq_dsflags_is_absent = 0;
/*      */     public static final int singleHopCtl_body_unsubscribeReq_dsflags_is_absent = 0;
/*      */     public static final int payload_singleHopCtl_body_unsubscribeReq_dsflags_is_absent = 0;
/*      */     public static final int dsflags_is_present = 1;
/*      */     public static final int unsubscribeReq_dsflags_is_present = 1;
/*      */     public static final int body_unsubscribeReq_dsflags_is_present = 1;
/*      */     public static final int singleHopCtl_body_unsubscribeReq_dsflags_is_present = 1;
/*      */     public static final int payload_singleHopCtl_body_unsubscribeReq_dsflags_is_present = 1;
/*      */     public static final int unsubscribeReply_URStatus_is_absent = 0;
/*      */     public static final int body_unsubscribeReply_URStatus_is_absent = 0;
/*      */     public static final int singleHopCtl_body_unsubscribeReply_URStatus_is_absent = 0;
/*      */     public static final int payload_singleHopCtl_body_unsubscribeReply_URStatus_is_absent = 0;
/*      */     public static final int unsubscribeReply_URStatus_is_present = 1;
/*      */     public static final int body_unsubscribeReply_URStatus_is_present = 1;
/*      */     public static final int singleHopCtl_body_unsubscribeReply_URStatus_is_present = 1;
/*      */     public static final int payload_singleHopCtl_body_unsubscribeReply_URStatus_is_present = 1;
/*      */     public static final int topicQuery_is_propertyKeys = 0;
/*      */     public static final int body_topicQuery_is_propertyKeys = 0;
/*      */     public static final int singleHopCtl_body_topicQuery_is_propertyKeys = 0;
/*      */     public static final int payload_singleHopCtl_body_topicQuery_is_propertyKeys = 0;
/*      */     public static final int topicQueryReply_is_propertyValues = 0;
/*      */     public static final int body_topicQueryReply_is_propertyValues = 0;
/*      */     public static final int singleHopCtl_body_topicQueryReply_is_propertyValues = 0;
/*      */     public static final int payload_singleHopCtl_body_topicQueryReply_is_propertyValues = 0;
/*      */     public static final int reactivateReq_startTime_is_rrStartTimeTable = 0;
/*      */     public static final int body_reactivateReq_startTime_is_rrStartTimeTable = 0;
/*      */     public static final int singleHopCtl_body_reactivateReq_startTime_is_rrStartTimeTable = 0;
/*      */     public static final int payload_singleHopCtl_body_reactivateReq_startTime_is_rrStartTimeTable = 0;
/*      */     public static final int reactivateReply_tmins_is_rrTminsTable = 0;
/*      */     public static final int body_reactivateReply_tmins_is_rrTminsTable = 0;
/*      */     public static final int singleHopCtl_body_reactivateReply_tmins_is_rrTminsTable = 0;
/*      */     public static final int payload_singleHopCtl_body_reactivateReply_tmins_is_rrTminsTable = 0;
/*      */     public static final int releaseTime_is_rrReleaseTimeTable = 0;
/*      */     public static final int releaseReq_releaseTime_is_rrReleaseTimeTable = 0;
/*      */     public static final int body_releaseReq_releaseTime_is_rrReleaseTimeTable = 0;
/*      */     public static final int singleHopCtl_body_releaseReq_releaseTime_is_rrReleaseTimeTable = 0;
/*      */     public static final int payload_singleHopCtl_body_releaseReq_releaseTime_is_rrReleaseTimeTable = 0;
/*      */     public static final int pubends_is_pubendsTable = 0;
/*      */     public static final int endOfCatchup_pubends_is_pubendsTable = 0;
/*      */     public static final int body_endOfCatchup_pubends_is_pubendsTable = 0;
/*      */     public static final int singleHopCtl_body_endOfCatchup_pubends_is_pubendsTable = 0;
/*      */     public static final int payload_singleHopCtl_body_endOfCatchup_pubends_is_pubendsTable = 0;
/*      */     public static final int multicastTopicsUpdate_topics_is_topicsUpdateTable = 0;
/*      */     public static final int body_multicastTopicsUpdate_topics_is_topicsUpdateTable = 0;
/*      */     public static final int singleHopCtl_body_multicastTopicsUpdate_topics_is_topicsUpdateTable = 0;
/*      */     public static final int payload_singleHopCtl_body_multicastTopicsUpdate_topics_is_topicsUpdateTable = 0;
/*      */     
/*      */     public static interface topicsUpdateTable_type {
/*      */       public static final int topic = 0;
/*      */       public static final int partitionLabel = 1;
/*      */       public static final int groupAddress = 2;
/*      */       public static final int enabled = 3;
/*      */       public static final int reliable = 4;
/*      */       public static final int qop = 5;
/*      */       public static final int key = 6;
/*      */       public static final int timeStamp = 7;
/*      */       public static final int nextRow = 8;
/*      */       public static final int primaryType = 9;
/*      */       public static final int primaryType_is_topicsUpdateTable = 1;
/*      */     }
/*      */     
/*      */     public static interface pubendsTable_type {
/*      */       public static final int pubendID = 0;
/*      */       public static final int nextRow = 1;
/*      */       public static final int primaryType = 2;
/*      */       public static final int primaryType_is_pubendsTable = 1;
/*      */     }
/*      */     
/*      */     public static interface rrReleaseTimeTable_type {
/*      */       public static final int pubend = 0;
/*      */       public static final int timestamp = 1;
/*      */       public static final int nextRow = 2;
/*      */       public static final int primaryType = 3;
/*      */       public static final int primaryType_is_rrReleaseTimeTable = 1;
/*      */     }
/*      */     
/*      */     public static interface querySubscriptionsReply_type {
/*      */       public static final int topic = 0;
/*      */       public static final int query = 1;
/*      */       public static final int reconnId = 2;
/*      */       public static final int nextRow = 3;
/*      */       public static final int primaryType = 4;
/*      */       public static final int primaryType_is_querySubscriptionsReply = 1;
/*      */     }
/*      */     
/*      */     public static interface rrTminsTable_type {
/*      */       public static final int pubend = 0;
/*      */       public static final int timestamp = 1;
/*      */       public static final int nextRow = 2;
/*      */       public static final int primaryType = 3;
/*      */       public static final int primaryType_is_rrTminsTable = 1;
/*      */     }
/*      */     
/*      */     public static interface rrStartTimeTable_type {
/*      */       public static final int pubend = 0;
/*      */       public static final int timestamp = 1;
/*      */       public static final int nextRow = 2;
/*      */       public static final int primaryType = 3;
/*      */       public static final int primaryType_is_rrStartTimeTable = 1;
/*      */     }
/*      */     
/*      */     public static interface propertyValues_type {
/*      */       public static final int name = 0;
/*      */       public static final int value = 1;
/*      */       public static final int nextRow = 2;
/*      */       public static final int primaryType = 3;
/*      */       public static final int primaryType_is_propertyValues = 1;
/*      */     }
/*      */     
/*      */     public static interface propertyKeys_type {
/*      */       public static final int name = 0;
/*      */       public static final int nextRow = 1;
/*      */       public static final int primaryType = 2;
/*      */       public static final int primaryType_is_propertyKeys = 1;
/*      */     }
/*      */     
/*      */     public static interface topicsTable_type {
/*      */       public static final int topic = 0;
/*      */       public static final int partitionLabel = 1;
/*      */       public static final int groupAddress = 2;
/*      */       public static final int enabled = 3;
/*      */       public static final int reliable = 4;
/*      */       public static final int qop = 5;
/*      */       public static final int key = 6;
/*      */       public static final int timeStamp = 7;
/*      */       public static final int nextRow = 8;
/*      */       public static final int primaryType = 9;
/*      */       public static final int primaryType_is_topicsTable = 1;
/*      */     }
/*      */     
/*      */     public static interface srStartTimeTable_type {
/*      */       public static final int pubend = 0;
/*      */       public static final int timestamp = 1;
/*      */       public static final int nextRow = 2;
/*      */       public static final int primaryType = 3;
/*      */       public static final int primaryType_is_srStartTimeTable = 1;
/*      */     }
/*      */     
/*      */     public static interface srTminsTable_type {
/*      */       public static final int pubend = 0;
/*      */       public static final int tmin = 1;
/*      */       public static final int nextRow = 2;
/*      */       public static final int primaryType = 3;
/*      */       public static final int primaryType_is_srTminsTable = 1;
/*      */     }
/*      */     
/*      */     public static interface featureTable_type {
/*      */       public static final int featureName = 0;
/*      */       public static final int paramName = 1;
/*      */       public static final int paramValue = 2;
/*      */       public static final int nextRow = 3;
/*      */       public static final int primaryType = 4;
/*      */       public static final int primaryType_is_featureTable = 1;
/*      */     }
/*      */     
/*      */     public static interface MapMessage_type {
/*      */       public static final int name = 0;
/*      */       public static final int booleanType = 1;
/*      */       public static final int value_booleanType = 1;
/*      */       public static final int byteType = 2;
/*      */       public static final int value_byteType = 2;
/*      */       public static final int shortType = 3;
/*      */       public static final int value_shortType = 3;
/*      */       public static final int charType = 4;
/*      */       public static final int value_charType = 4;
/*      */       public static final int intType = 5;
/*      */       public static final int value_intType = 5;
/*      */       public static final int longType = 6;
/*      */       public static final int value_longType = 6;
/*      */       public static final int floatType = 7;
/*      */       public static final int value_floatType = 7;
/*      */       public static final int doubleType = 8;
/*      */       public static final int value_doubleType = 8;
/*      */       public static final int stringType = 9;
/*      */       public static final int value_stringType = 9;
/*      */       public static final int bytesType = 10;
/*      */       public static final int value_bytesType = 10;
/*      */       public static final int nextRow = 11;
/*      */       public static final int primaryType = 12;
/*      */       public static final int value = 13;
/*      */       public static final int primaryType_is_MapMessage = 1;
/*      */       public static final int value_is_unknown = 0;
/*      */       public static final int value_is_booleanType = 1;
/*      */       public static final int value_is_byteType = 2;
/*      */       public static final int value_is_shortType = 3;
/*      */       public static final int value_is_charType = 4;
/*      */       public static final int value_is_intType = 5;
/*      */       public static final int value_is_longType = 6;
/*      */       public static final int value_is_floatType = 7;
/*      */       public static final int value_is_doubleType = 8;
/*      */       public static final int value_is_stringType = 9;
/*      */       public static final int value_is_bytesType = 10;
/*      */     }
/*      */     
/*      */     public static interface StreamMessage_type {
/*      */       public static final int booleanType = 0;
/*      */       public static final int value_booleanType = 0;
/*      */       public static final int byteType = 1;
/*      */       public static final int value_byteType = 1;
/*      */       public static final int shortType = 2;
/*      */       public static final int value_shortType = 2;
/*      */       public static final int charType = 3;
/*      */       public static final int value_charType = 3;
/*      */       public static final int intType = 4;
/*      */       public static final int value_intType = 4;
/*      */       public static final int longType = 5;
/*      */       public static final int value_longType = 5;
/*      */       public static final int floatType = 6;
/*      */       public static final int value_floatType = 6;
/*      */       public static final int doubleType = 7;
/*      */       public static final int value_doubleType = 7;
/*      */       public static final int stringType = 8;
/*      */       public static final int value_stringType = 8;
/*      */       public static final int bytesType = 9;
/*      */       public static final int value_bytesType = 9;
/*      */       public static final int nextRow = 10;
/*      */       public static final int primaryType = 11;
/*      */       public static final int value = 12;
/*      */       public static final int primaryType_is_StreamMessage = 1;
/*      */       public static final int value_is_unknown = 0;
/*      */       public static final int value_is_booleanType = 1;
/*      */       public static final int value_is_byteType = 2;
/*      */       public static final int value_is_shortType = 3;
/*      */       public static final int value_is_charType = 4;
/*      */       public static final int value_is_intType = 5;
/*      */       public static final int value_is_longType = 6;
/*      */       public static final int value_is_floatType = 7;
/*      */       public static final int value_is_doubleType = 8;
/*      */       public static final int value_is_stringType = 9;
/*      */       public static final int value_is_bytesType = 10;
/*      */     }
/*      */     
/*      */     public static interface stdProperties_type {
/*      */       public static final int name = 0;
/*      */       public static final int booleanType = 1;
/*      */       public static final int value_booleanType = 1;
/*      */       public static final int byteType = 2;
/*      */       public static final int value_byteType = 2;
/*      */       public static final int shortType = 3;
/*      */       public static final int value_shortType = 3;
/*      */       public static final int intType = 4;
/*      */       public static final int value_intType = 4;
/*      */       public static final int longType = 5;
/*      */       public static final int value_longType = 5;
/*      */       public static final int floatType = 6;
/*      */       public static final int value_floatType = 6;
/*      */       public static final int doubleType = 7;
/*      */       public static final int value_doubleType = 7;
/*      */       public static final int stringType = 8;
/*      */       public static final int value_stringType = 8;
/*      */       public static final int nextRow = 9;
/*      */       public static final int primaryType = 10;
/*      */       public static final int value = 11;
/*      */       public static final int primaryType_is_stdProperties = 1;
/*      */       public static final int value_is_unknown = 0;
/*      */       public static final int value_is_booleanType = 1;
/*      */       public static final int value_is_byteType = 2;
/*      */       public static final int value_is_shortType = 3;
/*      */       public static final int value_is_intType = 4;
/*      */       public static final int value_is_longType = 5;
/*      */       public static final int value_is_floatType = 6;
/*      */       public static final int value_is_doubleType = 7;
/*      */       public static final int value_is_stringType = 8;
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\Envelop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */