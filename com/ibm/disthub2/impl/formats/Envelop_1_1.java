/*     */ package com.ibm.disthub2.impl.formats;
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
/*     */ public final class Envelop_1_1
/*     */   extends Schema
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   
/*     */   public Envelop_1_1() {
/* 472 */     super(new byte[] { 1, 1, 8, -100, -4, -100, 1, -100, 0, 1, 1, 0, -101, 2, -101, 1, -101, -2, 0, 1, 2, 0, 1, 0, 1, 2, 0, 0, 0, 1, 12, 0, 4, 6, 0, 1, 2, 0, 1, -2, 0, 1, 2, 0, 1, 5, 0, 1, 6, 0, 0, 2, -2, -2, 1, 5, 8, 0, 1, 2, 0, 1, -2, 0, 1, 2, 0, 1, 6, 0, 1, 2, 0, 1, -2, 1, 6, 1, 0, 1, 3, 0, -1, 2, -2, 0, 1, 9, 0, 1, 1, 1, 2, 1, 3, 1, 5, 1, 6, 1, 7, 1, 8, 1, -2, 1, -1, 0, 1, 8, 0, 0, 1, -3, 1, -4, 1, -2, -1, 1, 0, 1, 11, 0, 1, 1, 1, 2, 1, 3, 1, 4, 1, 5, 1, 6, 1, 7, 1, 8, 1, -2, 1, -4, -1, 2, -2, 0, 1, 11, 0, 1, 1, 1, 2, 1, 3, 1, 4, 1, 5, 1, 6, 1, 7, 1, 8, 1, -2, 1, -4, 1, -1, 1, -1, 0, 0, 2, 6, 5, 3, 5, 5, 5, 2, 5, 5, 2, 1, 0, 1, 1, -1, 3, -2, -2, -2, 2, 2, -2, 1, 5, 2, 0, 1, 2, 0, 1, 5, 0, 1, 13, 0, 2, -2, -2, 1, 5, 1, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, -1 }, "com.ibm.disthub.impl.formats.Envelop");
/*     */   }
/*     */   
/*     */   public static final class Symbols extends SymbolTable { public Symbols() {
/* 476 */       super(new Object[] { "mdt", "qopQuery", "priority", "nonStop", "topic", "mid", "present_reply", "normal_track_present_track", "normal_body_subscribeReq_subject", "normal_body_subscribeReq_query", "body_id", "present_JMSCorrelationID", "present_JMSTimestamp", "present_JMSType", "JMSDeliveryMode", "JMSExpiration", "JMSRedelivered", new SymbolTable(new Object[] { "name", "booleanType", "byteType", "shortType", "intType", "longType", "floatType", "doubleType", "stringType", "nextRow", "primaryType", "value" }, new String[][] { { null, "std" }, , { "unknown", "booleanType", "byteType", "shortType", "intType", "longType", "floatType", "doubleType", "stringType" },  }, "std"), "schematized", "ObjectMessage", "BytesMessage", "TextMessage", new SymbolTable(new Object[] { "booleanType", "byteType", "shortType", "charType", "intType", "longType", "floatType", "doubleType", "stringType", "bytesType", "nextRow", "primaryType", "value" }, new String[][] { { null, "StreamMessage" }, , { "unknown", "booleanType", "byteType", "shortType", "charType", "intType", "longType", "floatType", "doubleType", "stringType", "bytesType" },  }, "StreamMessage"), new SymbolTable(new Object[] { "name", "booleanType", "byteType", "shortType", "charType", "intType", "longType", "floatType", "doubleType", "stringType", "bytesType", "nextRow", "primaryType", "value" }, new String[][] { { null, "MapMessage" }, , { "unknown", "booleanType", "byteType", "shortType", "charType", "intType", "longType", "floatType", "doubleType", "stringType", "bytesType" },  }, "MapMessage"), "SchematizedJMSMessage", "SchematizedNormalMessage", "error_id", "error_code", "server", "connection", "client", "connFail_code", "version", "featureExchange_query", new SymbolTable(new Object[] { "featureName", "paramName", "paramValue", "nextRow", "primaryType" }, new String[][] { { null, "featureTable" },  }, "featureTable"), "qopCode", "qopUpdate_subject", "notUnderstood", "singleHopCtl_track_present_track", "singleHopCtl_body_subscribeReq_subject", "singleHopCtl_body_subscribeReq_query", "subscribeReply", "unsubscribeReq", "state", "primaryType", "extendh", "extenda", "topicFormat", "payload", "normal_reply", "normal_track", "normal_body", "jms_JMSCorrelationID", "jms_JMSTimestamp", "jms_JMSType", "properties", "JMSbody", "feature", "singleHopCtl_track", "singleHopCtl_body" }new String[][] { { null }, , { null }, , { "rel1_0", "rel1_1" }, , { "prefixed", "unprefixed" }, , { "unknown", "normal", "pingReq", "pingReply", "error", "connGrant", "connFail", "featureExchange", "qopUpdate", "notUnderstood", "singleHopCtl", "multi" }, , { "absent", "present" }, , { "absent", "present" }, , { "unknown", "simplectl", "subscribeReq", "id", "jms", "SchematizedNormalMessage" }, , { "absent", "present" }, , { "absent", "present" }, , { "absent", "present" }, , { "absent", "std", "schematized" }, , { "unknown", "Message", "ObjectMessage", "BytesMessage", "TextMessage", "StreamMessage", "MapMessage", "SchematizedJMSMessage" }, , { "featureTable" }, , { "absent", "present" }, , { "unknown", "subscribeReq", "subscribeReply", "unsubscribeReq", "unsubscribeReply", "startDeliveryReq", "startDeliveryReply", "stopDeliveryReq", "stopDeliveryReply", "disconnectReq", "disconnectReply", "VMX", "notUnderstood" },  }, null);
/*     */     } }
/*     */ 
/*     */   
/*     */   public static interface Constants {
/*     */     public static final int mdt = 0;
/*     */     public static final int qopQuery = 1;
/*     */     public static final int priority = 2;
/*     */     public static final int nonStop = 3;
/*     */     public static final int topic = 4;
/*     */     public static final int mid = 5;
/*     */     public static final int normal_mid = 5;
/*     */     public static final int payload_normal_mid = 5;
/*     */     public static final int present_reply = 6;
/*     */     public static final int reply_present_reply = 6;
/*     */     public static final int normal_reply_present_reply = 6;
/*     */     public static final int payload_normal_reply_present_reply = 6;
/*     */     public static final int normal_track_present_track = 7;
/*     */     public static final int payload_normal_track_present_track = 7;
/*     */     public static final int normal_body_subscribeReq_subject = 8;
/*     */     public static final int payload_normal_body_subscribeReq_subject = 8;
/*     */     public static final int normal_body_subscribeReq_query = 9;
/*     */     public static final int payload_normal_body_subscribeReq_query = 9;
/*     */     public static final int body_id = 10;
/*     */     public static final int normal_body_id = 10;
/*     */     public static final int payload_normal_body_id = 10;
/*     */     public static final int present_JMSCorrelationID = 11;
/*     */     public static final int JMSCorrelationID_present_JMSCorrelationID = 11;
/*     */     public static final int jms_JMSCorrelationID_present_JMSCorrelationID = 11;
/*     */     public static final int body_jms_JMSCorrelationID_present_JMSCorrelationID = 11;
/*     */     public static final int normal_body_jms_JMSCorrelationID_present_JMSCorrelationID = 11;
/*     */     public static final int payload_normal_body_jms_JMSCorrelationID_present_JMSCorrelationID = 11;
/*     */     public static final int present_JMSTimestamp = 12;
/*     */     public static final int JMSTimestamp_present_JMSTimestamp = 12;
/*     */     public static final int jms_JMSTimestamp_present_JMSTimestamp = 12;
/*     */     public static final int body_jms_JMSTimestamp_present_JMSTimestamp = 12;
/*     */     public static final int normal_body_jms_JMSTimestamp_present_JMSTimestamp = 12;
/*     */     public static final int payload_normal_body_jms_JMSTimestamp_present_JMSTimestamp = 12;
/*     */     public static final int present_JMSType = 13;
/*     */     public static final int JMSType_present_JMSType = 13;
/*     */     public static final int jms_JMSType_present_JMSType = 13;
/*     */     public static final int body_jms_JMSType_present_JMSType = 13;
/*     */     public static final int normal_body_jms_JMSType_present_JMSType = 13;
/*     */     public static final int payload_normal_body_jms_JMSType_present_JMSType = 13;
/*     */     public static final int JMSDeliveryMode = 14;
/*     */     public static final int jms_JMSDeliveryMode = 14;
/*     */     public static final int body_jms_JMSDeliveryMode = 14;
/*     */     public static final int normal_body_jms_JMSDeliveryMode = 14;
/*     */     public static final int payload_normal_body_jms_JMSDeliveryMode = 14;
/*     */     public static final int JMSExpiration = 15;
/*     */     public static final int jms_JMSExpiration = 15;
/*     */     public static final int body_jms_JMSExpiration = 15;
/*     */     public static final int normal_body_jms_JMSExpiration = 15;
/*     */     public static final int payload_normal_body_jms_JMSExpiration = 15;
/*     */     public static final int JMSRedelivered = 16;
/*     */     public static final int jms_JMSRedelivered = 16;
/*     */     public static final int body_jms_JMSRedelivered = 16;
/*     */     public static final int normal_body_jms_JMSRedelivered = 16;
/*     */     public static final int payload_normal_body_jms_JMSRedelivered = 16;
/*     */     public static final int std = 17;
/*     */     public static final int properties_std = 17;
/*     */     public static final int jms_properties_std = 17;
/*     */     public static final int body_jms_properties_std = 17;
/*     */     public static final int normal_body_jms_properties_std = 17;
/*     */     public static final int payload_normal_body_jms_properties_std = 17;
/*     */     public static final int schematized = 18;
/*     */     public static final int properties_schematized = 18;
/*     */     public static final int jms_properties_schematized = 18;
/*     */     public static final int body_jms_properties_schematized = 18;
/*     */     public static final int normal_body_jms_properties_schematized = 18;
/*     */     public static final int payload_normal_body_jms_properties_schematized = 18;
/*     */     public static final int ObjectMessage = 19;
/*     */     public static final int JMSbody_ObjectMessage = 19;
/*     */     public static final int jms_JMSbody_ObjectMessage = 19;
/*     */     public static final int body_jms_JMSbody_ObjectMessage = 19;
/*     */     public static final int normal_body_jms_JMSbody_ObjectMessage = 19;
/*     */     public static final int payload_normal_body_jms_JMSbody_ObjectMessage = 19;
/*     */     public static final int BytesMessage = 20;
/*     */     public static final int JMSbody_BytesMessage = 20;
/*     */     public static final int jms_JMSbody_BytesMessage = 20;
/*     */     public static final int body_jms_JMSbody_BytesMessage = 20;
/*     */     public static final int normal_body_jms_JMSbody_BytesMessage = 20;
/*     */     public static final int payload_normal_body_jms_JMSbody_BytesMessage = 20;
/*     */     public static final int TextMessage = 21;
/*     */     public static final int JMSbody_TextMessage = 21;
/*     */     public static final int jms_JMSbody_TextMessage = 21;
/*     */     public static final int body_jms_JMSbody_TextMessage = 21;
/*     */     public static final int normal_body_jms_JMSbody_TextMessage = 21;
/*     */     public static final int payload_normal_body_jms_JMSbody_TextMessage = 21;
/*     */     public static final int StreamMessage = 22;
/*     */     public static final int JMSbody_StreamMessage = 22;
/*     */     public static final int jms_JMSbody_StreamMessage = 22;
/*     */     public static final int body_jms_JMSbody_StreamMessage = 22;
/*     */     public static final int normal_body_jms_JMSbody_StreamMessage = 22;
/*     */     public static final int payload_normal_body_jms_JMSbody_StreamMessage = 22;
/*     */     public static final int MapMessage = 23;
/*     */     public static final int JMSbody_MapMessage = 23;
/*     */     public static final int jms_JMSbody_MapMessage = 23;
/*     */     public static final int body_jms_JMSbody_MapMessage = 23;
/*     */     public static final int normal_body_jms_JMSbody_MapMessage = 23;
/*     */     public static final int payload_normal_body_jms_JMSbody_MapMessage = 23;
/*     */     public static final int SchematizedJMSMessage = 24;
/*     */     public static final int JMSbody_SchematizedJMSMessage = 24;
/*     */     public static final int jms_JMSbody_SchematizedJMSMessage = 24;
/*     */     public static final int body_jms_JMSbody_SchematizedJMSMessage = 24;
/*     */     public static final int normal_body_jms_JMSbody_SchematizedJMSMessage = 24;
/*     */     public static final int payload_normal_body_jms_JMSbody_SchematizedJMSMessage = 24;
/*     */     public static final int SchematizedNormalMessage = 25;
/*     */     public static final int body_SchematizedNormalMessage = 25;
/*     */     public static final int normal_body_SchematizedNormalMessage = 25;
/*     */     public static final int payload_normal_body_SchematizedNormalMessage = 25;
/*     */     public static final int error_id = 26;
/*     */     public static final int payload_error_id = 26;
/*     */     public static final int error_code = 27;
/*     */     public static final int payload_error_code = 27;
/*     */     public static final int server = 28;
/*     */     public static final int connGrant_server = 28;
/*     */     public static final int payload_connGrant_server = 28;
/*     */     public static final int connection = 29;
/*     */     public static final int connGrant_connection = 29;
/*     */     public static final int payload_connGrant_connection = 29;
/*     */     public static final int client = 30;
/*     */     public static final int connGrant_client = 30;
/*     */     public static final int payload_connGrant_client = 30;
/*     */     public static final int connFail_code = 31;
/*     */     public static final int payload_connFail_code = 31;
/*     */     public static final int version = 32;
/*     */     public static final int connFail_version = 32;
/*     */     public static final int payload_connFail_version = 32;
/*     */     public static final int featureExchange_query = 33;
/*     */     public static final int payload_featureExchange_query = 33;
/*     */     public static final int featureTable = 34;
/*     */     public static final int feature_featureTable = 34;
/*     */     public static final int featureExchange_feature_featureTable = 34;
/*     */     public static final int payload_featureExchange_feature_featureTable = 34;
/*     */     public static final int qopCode = 35;
/*     */     public static final int qopUpdate_qopCode = 35;
/*     */     public static final int payload_qopUpdate_qopCode = 35;
/*     */     public static final int qopUpdate_subject = 36;
/*     */     public static final int payload_qopUpdate_subject = 36;
/*     */     public static final int notUnderstood = 37;
/*     */     public static final int payload_notUnderstood = 37;
/*     */     public static final int singleHopCtl_track_present_track = 38;
/*     */     public static final int payload_singleHopCtl_track_present_track = 38;
/*     */     public static final int singleHopCtl_body_subscribeReq_subject = 39;
/*     */     public static final int payload_singleHopCtl_body_subscribeReq_subject = 39;
/*     */     public static final int singleHopCtl_body_subscribeReq_query = 40;
/*     */     public static final int payload_singleHopCtl_body_subscribeReq_query = 40;
/*     */     public static final int subscribeReply = 41;
/*     */     public static final int body_subscribeReply = 41;
/*     */     public static final int singleHopCtl_body_subscribeReply = 41;
/*     */     public static final int payload_singleHopCtl_body_subscribeReply = 41;
/*     */     public static final int unsubscribeReq = 42;
/*     */     public static final int body_unsubscribeReq = 42;
/*     */     public static final int singleHopCtl_body_unsubscribeReq = 42;
/*     */     public static final int payload_singleHopCtl_body_unsubscribeReq = 42;
/*     */     public static final int state = 43;
/*     */     public static final int multi_state = 43;
/*     */     public static final int payload_multi_state = 43;
/*     */     public static final int primaryType = 44;
/*     */     public static final int extendh = 45;
/*     */     public static final int extenda = 46;
/*     */     public static final int topicFormat = 47;
/*     */     public static final int rel1_1_topicFormat = 47;
/*     */     public static final int extenda_rel1_1_topicFormat = 47;
/*     */     public static final int payload = 48;
/*     */     public static final int normal_reply = 49;
/*     */     public static final int payload_normal_reply = 49;
/*     */     public static final int normal_track = 50;
/*     */     public static final int payload_normal_track = 50;
/*     */     public static final int normal_body = 51;
/*     */     public static final int payload_normal_body = 51;
/*     */     public static final int jms_JMSCorrelationID = 52;
/*     */     public static final int body_jms_JMSCorrelationID = 52;
/*     */     public static final int normal_body_jms_JMSCorrelationID = 52;
/*     */     public static final int payload_normal_body_jms_JMSCorrelationID = 52;
/*     */     public static final int jms_JMSTimestamp = 53;
/*     */     public static final int body_jms_JMSTimestamp = 53;
/*     */     public static final int normal_body_jms_JMSTimestamp = 53;
/*     */     public static final int payload_normal_body_jms_JMSTimestamp = 53;
/*     */     public static final int jms_JMSType = 54;
/*     */     public static final int body_jms_JMSType = 54;
/*     */     public static final int normal_body_jms_JMSType = 54;
/*     */     public static final int payload_normal_body_jms_JMSType = 54;
/*     */     public static final int properties = 55;
/*     */     public static final int jms_properties = 55;
/*     */     public static final int body_jms_properties = 55;
/*     */     public static final int normal_body_jms_properties = 55;
/*     */     public static final int payload_normal_body_jms_properties = 55;
/*     */     public static final int JMSbody = 56;
/*     */     public static final int jms_JMSbody = 56;
/*     */     public static final int body_jms_JMSbody = 56;
/*     */     public static final int normal_body_jms_JMSbody = 56;
/*     */     public static final int payload_normal_body_jms_JMSbody = 56;
/*     */     public static final int feature = 57;
/*     */     public static final int featureExchange_feature = 57;
/*     */     public static final int payload_featureExchange_feature = 57;
/*     */     public static final int singleHopCtl_track = 58;
/*     */     public static final int payload_singleHopCtl_track = 58;
/*     */     public static final int singleHopCtl_body = 59;
/*     */     public static final int payload_singleHopCtl_body = 59;
/*     */     public static final int extenda_is_rel1_0 = 0;
/*     */     public static final int extenda_is_rel1_1 = 1;
/*     */     public static final int topicFormat_is_prefixed = 0;
/*     */     public static final int rel1_1_topicFormat_is_prefixed = 0;
/*     */     public static final int extenda_rel1_1_topicFormat_is_prefixed = 0;
/*     */     public static final int topicFormat_is_unprefixed = 1;
/*     */     public static final int rel1_1_topicFormat_is_unprefixed = 1;
/*     */     public static final int extenda_rel1_1_topicFormat_is_unprefixed = 1;
/*     */     public static final int payload_is_unknown = 0;
/*     */     public static final int payload_is_normal = 1;
/*     */     public static final int payload_is_pingReq = 2;
/*     */     public static final int payload_is_pingReply = 3;
/*     */     public static final int payload_is_error = 4;
/*     */     public static final int payload_is_connGrant = 5;
/*     */     public static final int payload_is_connFail = 6;
/*     */     public static final int payload_is_featureExchange = 7;
/*     */     public static final int payload_is_qopUpdate = 8;
/*     */     public static final int payload_is_notUnderstood = 9;
/*     */     public static final int payload_is_singleHopCtl = 10;
/*     */     public static final int payload_is_multi = 11;
/*     */     public static final int normal_reply_is_absent = 0;
/*     */     public static final int payload_normal_reply_is_absent = 0;
/*     */     public static final int normal_reply_is_present = 1;
/*     */     public static final int payload_normal_reply_is_present = 1;
/*     */     public static final int normal_track_is_absent = 0;
/*     */     public static final int payload_normal_track_is_absent = 0;
/*     */     public static final int normal_track_is_present = 1;
/*     */     public static final int payload_normal_track_is_present = 1;
/*     */     public static final int normal_body_is_unknown = 0;
/*     */     public static final int payload_normal_body_is_unknown = 0;
/*     */     public static final int normal_body_is_simplectl = 1;
/*     */     public static final int payload_normal_body_is_simplectl = 1;
/*     */     public static final int normal_body_is_subscribeReq = 2;
/*     */     public static final int payload_normal_body_is_subscribeReq = 2;
/*     */     public static final int normal_body_is_id = 3;
/*     */     public static final int payload_normal_body_is_id = 3;
/*     */     public static final int normal_body_is_jms = 4;
/*     */     public static final int payload_normal_body_is_jms = 4;
/*     */     public static final int normal_body_is_SchematizedNormalMessage = 5;
/*     */     public static final int payload_normal_body_is_SchematizedNormalMessage = 5;
/*     */     public static final int jms_JMSCorrelationID_is_absent = 0;
/*     */     public static final int body_jms_JMSCorrelationID_is_absent = 0;
/*     */     public static final int normal_body_jms_JMSCorrelationID_is_absent = 0;
/*     */     public static final int payload_normal_body_jms_JMSCorrelationID_is_absent = 0;
/*     */     public static final int jms_JMSCorrelationID_is_present = 1;
/*     */     public static final int body_jms_JMSCorrelationID_is_present = 1;
/*     */     public static final int normal_body_jms_JMSCorrelationID_is_present = 1;
/*     */     public static final int payload_normal_body_jms_JMSCorrelationID_is_present = 1;
/*     */     public static final int jms_JMSTimestamp_is_absent = 0;
/*     */     public static final int body_jms_JMSTimestamp_is_absent = 0;
/*     */     public static final int normal_body_jms_JMSTimestamp_is_absent = 0;
/*     */     public static final int payload_normal_body_jms_JMSTimestamp_is_absent = 0;
/*     */     public static final int jms_JMSTimestamp_is_present = 1;
/*     */     public static final int body_jms_JMSTimestamp_is_present = 1;
/*     */     public static final int normal_body_jms_JMSTimestamp_is_present = 1;
/*     */     public static final int payload_normal_body_jms_JMSTimestamp_is_present = 1;
/*     */     public static final int jms_JMSType_is_absent = 0;
/*     */     public static final int body_jms_JMSType_is_absent = 0;
/*     */     public static final int normal_body_jms_JMSType_is_absent = 0;
/*     */     public static final int payload_normal_body_jms_JMSType_is_absent = 0;
/*     */     public static final int jms_JMSType_is_present = 1;
/*     */     public static final int body_jms_JMSType_is_present = 1;
/*     */     public static final int normal_body_jms_JMSType_is_present = 1;
/*     */     public static final int payload_normal_body_jms_JMSType_is_present = 1;
/*     */     public static final int properties_is_absent = 0;
/*     */     public static final int jms_properties_is_absent = 0;
/*     */     public static final int body_jms_properties_is_absent = 0;
/*     */     public static final int normal_body_jms_properties_is_absent = 0;
/*     */     public static final int payload_normal_body_jms_properties_is_absent = 0;
/*     */     public static final int properties_is_std = 1;
/*     */     public static final int jms_properties_is_std = 1;
/*     */     public static final int body_jms_properties_is_std = 1;
/*     */     public static final int normal_body_jms_properties_is_std = 1;
/*     */     public static final int payload_normal_body_jms_properties_is_std = 1;
/*     */     public static final int properties_is_schematized = 2;
/*     */     public static final int jms_properties_is_schematized = 2;
/*     */     public static final int body_jms_properties_is_schematized = 2;
/*     */     public static final int normal_body_jms_properties_is_schematized = 2;
/*     */     public static final int payload_normal_body_jms_properties_is_schematized = 2;
/*     */     public static final int JMSbody_is_unknown = 0;
/*     */     public static final int jms_JMSbody_is_unknown = 0;
/*     */     public static final int body_jms_JMSbody_is_unknown = 0;
/*     */     public static final int normal_body_jms_JMSbody_is_unknown = 0;
/*     */     public static final int payload_normal_body_jms_JMSbody_is_unknown = 0;
/*     */     public static final int JMSbody_is_Message = 1;
/*     */     public static final int jms_JMSbody_is_Message = 1;
/*     */     public static final int body_jms_JMSbody_is_Message = 1;
/*     */     public static final int normal_body_jms_JMSbody_is_Message = 1;
/*     */     public static final int payload_normal_body_jms_JMSbody_is_Message = 1;
/*     */     public static final int JMSbody_is_ObjectMessage = 2;
/*     */     public static final int jms_JMSbody_is_ObjectMessage = 2;
/*     */     public static final int body_jms_JMSbody_is_ObjectMessage = 2;
/*     */     public static final int normal_body_jms_JMSbody_is_ObjectMessage = 2;
/*     */     public static final int payload_normal_body_jms_JMSbody_is_ObjectMessage = 2;
/*     */     public static final int JMSbody_is_BytesMessage = 3;
/*     */     public static final int jms_JMSbody_is_BytesMessage = 3;
/*     */     public static final int body_jms_JMSbody_is_BytesMessage = 3;
/*     */     public static final int normal_body_jms_JMSbody_is_BytesMessage = 3;
/*     */     public static final int payload_normal_body_jms_JMSbody_is_BytesMessage = 3;
/*     */     public static final int JMSbody_is_TextMessage = 4;
/*     */     public static final int jms_JMSbody_is_TextMessage = 4;
/*     */     public static final int body_jms_JMSbody_is_TextMessage = 4;
/*     */     public static final int normal_body_jms_JMSbody_is_TextMessage = 4;
/*     */     public static final int payload_normal_body_jms_JMSbody_is_TextMessage = 4;
/*     */     public static final int JMSbody_is_StreamMessage = 5;
/*     */     public static final int jms_JMSbody_is_StreamMessage = 5;
/*     */     public static final int body_jms_JMSbody_is_StreamMessage = 5;
/*     */     public static final int normal_body_jms_JMSbody_is_StreamMessage = 5;
/*     */     public static final int payload_normal_body_jms_JMSbody_is_StreamMessage = 5;
/*     */     public static final int JMSbody_is_MapMessage = 6;
/*     */     public static final int jms_JMSbody_is_MapMessage = 6;
/*     */     public static final int body_jms_JMSbody_is_MapMessage = 6;
/*     */     public static final int normal_body_jms_JMSbody_is_MapMessage = 6;
/*     */     public static final int payload_normal_body_jms_JMSbody_is_MapMessage = 6;
/*     */     public static final int JMSbody_is_SchematizedJMSMessage = 7;
/*     */     public static final int jms_JMSbody_is_SchematizedJMSMessage = 7;
/*     */     public static final int body_jms_JMSbody_is_SchematizedJMSMessage = 7;
/*     */     public static final int normal_body_jms_JMSbody_is_SchematizedJMSMessage = 7;
/*     */     public static final int payload_normal_body_jms_JMSbody_is_SchematizedJMSMessage = 7;
/*     */     public static final int feature_is_featureTable = 0;
/*     */     public static final int featureExchange_feature_is_featureTable = 0;
/*     */     public static final int payload_featureExchange_feature_is_featureTable = 0;
/*     */     public static final int singleHopCtl_track_is_absent = 0;
/*     */     public static final int payload_singleHopCtl_track_is_absent = 0;
/*     */     public static final int singleHopCtl_track_is_present = 1;
/*     */     public static final int payload_singleHopCtl_track_is_present = 1;
/*     */     public static final int singleHopCtl_body_is_unknown = 0;
/*     */     public static final int payload_singleHopCtl_body_is_unknown = 0;
/*     */     public static final int singleHopCtl_body_is_subscribeReq = 1;
/*     */     public static final int payload_singleHopCtl_body_is_subscribeReq = 1;
/*     */     public static final int singleHopCtl_body_is_subscribeReply = 2;
/*     */     public static final int payload_singleHopCtl_body_is_subscribeReply = 2;
/*     */     public static final int singleHopCtl_body_is_unsubscribeReq = 3;
/*     */     public static final int payload_singleHopCtl_body_is_unsubscribeReq = 3;
/*     */     public static final int singleHopCtl_body_is_unsubscribeReply = 4;
/*     */     public static final int payload_singleHopCtl_body_is_unsubscribeReply = 4;
/*     */     public static final int singleHopCtl_body_is_startDeliveryReq = 5;
/*     */     public static final int payload_singleHopCtl_body_is_startDeliveryReq = 5;
/*     */     public static final int singleHopCtl_body_is_startDeliveryReply = 6;
/*     */     public static final int payload_singleHopCtl_body_is_startDeliveryReply = 6;
/*     */     public static final int singleHopCtl_body_is_stopDeliveryReq = 7;
/*     */     public static final int payload_singleHopCtl_body_is_stopDeliveryReq = 7;
/*     */     public static final int singleHopCtl_body_is_stopDeliveryReply = 8;
/*     */     public static final int payload_singleHopCtl_body_is_stopDeliveryReply = 8;
/*     */     public static final int singleHopCtl_body_is_disconnectReq = 9;
/*     */     public static final int payload_singleHopCtl_body_is_disconnectReq = 9;
/*     */     public static final int singleHopCtl_body_is_disconnectReply = 10;
/*     */     public static final int payload_singleHopCtl_body_is_disconnectReply = 10;
/*     */     public static final int singleHopCtl_body_is_VMX = 11;
/*     */     public static final int payload_singleHopCtl_body_is_VMX = 11;
/*     */     public static final int singleHopCtl_body_is_notUnderstood = 12;
/*     */     public static final int payload_singleHopCtl_body_is_notUnderstood = 12;
/*     */     
/*     */     public static interface featureTable_type {
/*     */       public static final int featureName = 0;
/*     */       public static final int paramName = 1;
/*     */       public static final int paramValue = 2;
/*     */       public static final int nextRow = 3;
/*     */       public static final int primaryType = 4;
/*     */       public static final int primaryType_is_featureTable = 1;
/*     */     }
/*     */     
/*     */     public static interface MapMessage_type {
/*     */       public static final int name = 0;
/*     */       public static final int booleanType = 1;
/*     */       public static final int value_booleanType = 1;
/*     */       public static final int byteType = 2;
/*     */       public static final int value_byteType = 2;
/*     */       public static final int shortType = 3;
/*     */       public static final int value_shortType = 3;
/*     */       public static final int charType = 4;
/*     */       public static final int value_charType = 4;
/*     */       public static final int intType = 5;
/*     */       public static final int value_intType = 5;
/*     */       public static final int longType = 6;
/*     */       public static final int value_longType = 6;
/*     */       public static final int floatType = 7;
/*     */       public static final int value_floatType = 7;
/*     */       public static final int doubleType = 8;
/*     */       public static final int value_doubleType = 8;
/*     */       public static final int stringType = 9;
/*     */       public static final int value_stringType = 9;
/*     */       public static final int bytesType = 10;
/*     */       public static final int value_bytesType = 10;
/*     */       public static final int nextRow = 11;
/*     */       public static final int primaryType = 12;
/*     */       public static final int value = 13;
/*     */       public static final int primaryType_is_MapMessage = 1;
/*     */       public static final int value_is_unknown = 0;
/*     */       public static final int value_is_booleanType = 1;
/*     */       public static final int value_is_byteType = 2;
/*     */       public static final int value_is_shortType = 3;
/*     */       public static final int value_is_charType = 4;
/*     */       public static final int value_is_intType = 5;
/*     */       public static final int value_is_longType = 6;
/*     */       public static final int value_is_floatType = 7;
/*     */       public static final int value_is_doubleType = 8;
/*     */       public static final int value_is_stringType = 9;
/*     */       public static final int value_is_bytesType = 10;
/*     */     }
/*     */     
/*     */     public static interface StreamMessage_type {
/*     */       public static final int booleanType = 0;
/*     */       public static final int value_booleanType = 0;
/*     */       public static final int byteType = 1;
/*     */       public static final int value_byteType = 1;
/*     */       public static final int shortType = 2;
/*     */       public static final int value_shortType = 2;
/*     */       public static final int charType = 3;
/*     */       public static final int value_charType = 3;
/*     */       public static final int intType = 4;
/*     */       public static final int value_intType = 4;
/*     */       public static final int longType = 5;
/*     */       public static final int value_longType = 5;
/*     */       public static final int floatType = 6;
/*     */       public static final int value_floatType = 6;
/*     */       public static final int doubleType = 7;
/*     */       public static final int value_doubleType = 7;
/*     */       public static final int stringType = 8;
/*     */       public static final int value_stringType = 8;
/*     */       public static final int bytesType = 9;
/*     */       public static final int value_bytesType = 9;
/*     */       public static final int nextRow = 10;
/*     */       public static final int primaryType = 11;
/*     */       public static final int value = 12;
/*     */       public static final int primaryType_is_StreamMessage = 1;
/*     */       public static final int value_is_unknown = 0;
/*     */       public static final int value_is_booleanType = 1;
/*     */       public static final int value_is_byteType = 2;
/*     */       public static final int value_is_shortType = 3;
/*     */       public static final int value_is_charType = 4;
/*     */       public static final int value_is_intType = 5;
/*     */       public static final int value_is_longType = 6;
/*     */       public static final int value_is_floatType = 7;
/*     */       public static final int value_is_doubleType = 8;
/*     */       public static final int value_is_stringType = 9;
/*     */       public static final int value_is_bytesType = 10;
/*     */     }
/*     */     
/*     */     public static interface std_type {
/*     */       public static final int name = 0;
/*     */       public static final int booleanType = 1;
/*     */       public static final int value_booleanType = 1;
/*     */       public static final int byteType = 2;
/*     */       public static final int value_byteType = 2;
/*     */       public static final int shortType = 3;
/*     */       public static final int value_shortType = 3;
/*     */       public static final int intType = 4;
/*     */       public static final int value_intType = 4;
/*     */       public static final int longType = 5;
/*     */       public static final int value_longType = 5;
/*     */       public static final int floatType = 6;
/*     */       public static final int value_floatType = 6;
/*     */       public static final int doubleType = 7;
/*     */       public static final int value_doubleType = 7;
/*     */       public static final int stringType = 8;
/*     */       public static final int value_stringType = 8;
/*     */       public static final int nextRow = 9;
/*     */       public static final int primaryType = 10;
/*     */       public static final int value = 11;
/*     */       public static final int primaryType_is_std = 1;
/*     */       public static final int value_is_unknown = 0;
/*     */       public static final int value_is_booleanType = 1;
/*     */       public static final int value_is_byteType = 2;
/*     */       public static final int value_is_shortType = 3;
/*     */       public static final int value_is_intType = 4;
/*     */       public static final int value_is_longType = 5;
/*     */       public static final int value_is_floatType = 6;
/*     */       public static final int value_is_doubleType = 7;
/*     */       public static final int value_is_stringType = 8;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\Envelop_1_1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */