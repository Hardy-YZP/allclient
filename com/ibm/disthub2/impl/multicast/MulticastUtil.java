/*     */ package com.ibm.disthub2.impl.multicast;
/*     */ 
/*     */ import com.ibm.disthub2.client.MessageBodyHandle;
/*     */ import com.ibm.disthub2.impl.formats.Envelop;
/*     */ import com.ibm.disthub2.impl.formats.MessageDataHandle;
/*     */ import java.util.Iterator;
/*     */ import java.util.Properties;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MulticastUtil
/*     */   implements Envelop.Constants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   public static final String propertyPrefix = "MULTICAST";
/*     */   public static final String MULTICAST_PROTOCOL_TYPE = "MUTLICAST_PROTOCOL_TYPE";
/*     */   public static final String MULTICAST_PROTOCOL_PTL = "PTL";
/*     */   public static final String MULTICAST_PROTOCOL_PGM_IP = "PGM/IP";
/*     */   public static final String MULTICAST_PROTOCOL_PGM_UDP = "PGM/UDP";
/*     */   
/*     */   public static Properties brokerPropertiesToRMMProperties(Properties brokerProperties) {
/*  49 */     Properties rmmProperties = new Properties();
/*  50 */     Iterator<String> it = brokerProperties.keySet().iterator();
/*     */     
/*  52 */     while (it.hasNext()) {
/*     */       
/*  54 */       String name = it.next();
/*  55 */       if (name.startsWith("MULTICAST"))
/*     */       {
/*  57 */         rmmProperties.put(brokerNameToRMMName(name), brokerProperties
/*  58 */             .get(name));
/*     */       }
/*     */     } 
/*  61 */     return rmmProperties;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String brokerNameToRMMName(String brokerName) {
/*  72 */     StringBuffer rmmName = new StringBuffer();
/*     */ 
/*     */     
/*  75 */     for (int i = "MULTICAST".length(); i < brokerName.length(); i++) {
/*     */       
/*  77 */       char c = brokerName.charAt(i);
/*  78 */       if (c == '_') {
/*     */         
/*  80 */         rmmName.append(brokerName.charAt(++i));
/*     */       }
/*     */       else {
/*     */         
/*  84 */         rmmName.append(Character.toLowerCase(c));
/*     */       } 
/*     */     } 
/*  87 */     return rmmName.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static MulticastTopic[] getMulticastTopicsUpdate(MessageDataHandle cursor) {
/*  95 */     MessageBodyHandle[] topicTable = cursor.getTable(103);
/*  96 */     MulticastTopic[] multicastTopics = new MulticastTopic[topicTable.length];
/*  97 */     for (int i = 0; i < topicTable.length; i++) {
/*  98 */       String partitionLabel = topicTable[i].getString(1);
/*  99 */       String topic = topicTable[i].getString(0);
/* 100 */       String groupAddress = topicTable[i].getString(2);
/* 101 */       boolean enabled = topicTable[i].getBoolean(3);
/* 102 */       boolean reliable = topicTable[i].getBoolean(4);
/* 103 */       byte qop = topicTable[i].getByte(5);
/* 104 */       byte[] key = topicTable[i].getByteArray(6);
/* 105 */       long timeStamp = topicTable[i].getLong(7);
/* 106 */       multicastTopics[i] = new MulticastTopic(partitionLabel, topic, groupAddress, enabled, reliable, qop, key, timeStamp);
/*     */     } 
/* 108 */     return multicastTopics;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static MulticastTopic[] getMulticastTopics(MessageDataHandle cursor) {
/* 115 */     if (cursor.getBoolean(58)) {
/*     */       
/* 117 */       MessageBodyHandle[] rows = cursor.getTable(59);
/* 118 */       MulticastTopic[] multicastTopics = new MulticastTopic[rows.length];
/*     */       
/* 120 */       for (int i = 0; i < multicastTopics.length; i++) {
/* 121 */         String partitionLabel = rows[i].getString(1);
/* 122 */         String topic = rows[i].getString(0);
/* 123 */         String groupAddress = rows[i].getString(2);
/* 124 */         boolean enabled = rows[i].getBoolean(3);
/* 125 */         boolean reliable = rows[i].getBoolean(4);
/* 126 */         byte qop = rows[i].getByte(5);
/* 127 */         byte[] key = rows[i].getByteArray(6);
/* 128 */         long timeStamp = rows[i].getLong(7);
/* 129 */         multicastTopics[i] = new MulticastTopic(partitionLabel, topic, groupAddress, enabled, reliable, qop, key, timeStamp);
/*     */       } 
/* 131 */       return multicastTopics;
/*     */     } 
/* 133 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\multicast\MulticastUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */