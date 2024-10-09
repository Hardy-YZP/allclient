/*     */ package com.ibm.msg.client.wmq.compat.base.internal;
/*     */ 
/*     */ import com.ibm.mq.exits.MQCD;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQChannelDefinition
/*     */   extends JmqiObject
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQChannelDefinition.java";
/*     */   public String channelName;
/*     */   public String connectionName;
/*     */   
/*     */   static {
/*  59 */     if (Trace.isOn) {
/*  60 */       Trace.data("com.ibm.msg.client.wmq.compat.base.internal.MQChannelDefinition", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQChannelDefinition.java");
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
/*     */   
/*  82 */   public Collection<Integer> hdrCompList = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  90 */   private MQCD jmqiStructure = MQSESSION.getJmqiEnv().newMQCD();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String localAddress;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int maxMessageLength;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 105 */   public Collection<Integer> msgCompList = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int port;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String queueManagerName;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String receiveUserData;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String remotePassword;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String remoteUserId;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String securityUserData;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String sendUserData;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 149 */   public int sharingConversations = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int SHARING_CONVERSATIONS_NOT_SET = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String sslPeerName;
/*     */ 
/*     */ 
/*     */   
/* 163 */   public String sslCipherSpec = null;
/*     */ 
/*     */   
/*     */   public MQChannelDefinition() {
/* 167 */     super(MQSESSION.getJmqiEnv());
/* 168 */     if (Trace.isOn) {
/* 169 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQChannelDefinition", "<init>()");
/*     */     }
/*     */     
/* 172 */     if (Trace.isOn) {
/* 173 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQChannelDefinition", "<init>()");
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
/*     */   private int calcMinimumVersion() {
/* 185 */     if (Trace.isOn) {
/* 186 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQChannelDefinition", "calcMinimumVersion()");
/*     */     }
/*     */     
/* 189 */     int version = 2;
/*     */ 
/*     */     
/* 192 */     if (this.remoteUserId != null && this.remoteUserId.length() > 12) {
/* 193 */       version = 6;
/*     */     }
/* 195 */     if (this.remotePassword != null && this.remotePassword.length() > 12) {
/* 196 */       version = 6;
/*     */     }
/*     */ 
/*     */     
/* 200 */     if (this.localAddress != null && this.localAddress.length() > 0) {
/* 201 */       version = 7;
/*     */     }
/* 203 */     if (this.sslCipherSpec != null && this.sslCipherSpec.length() > 0) {
/* 204 */       version = 7;
/*     */     }
/* 206 */     if (this.sslPeerName != null && this.sslPeerName.length() > 0) {
/* 207 */       version = 7;
/*     */     }
/*     */ 
/*     */     
/* 211 */     if (this.hdrCompList != null && this.hdrCompList.size() > 0) {
/* 212 */       version = 8;
/*     */     }
/* 214 */     if (this.msgCompList != null && this.msgCompList.size() > 0) {
/* 215 */       version = 8;
/*     */     }
/*     */ 
/*     */     
/* 219 */     if (this.sharingConversations != -1) {
/* 220 */       version = 9;
/*     */     }
/*     */     
/* 223 */     if (Trace.isOn) {
/* 224 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQChannelDefinition", "calcMinimumVersion()", 
/* 225 */           Integer.valueOf(version));
/*     */     }
/* 227 */     return version;
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
/*     */   protected MQCD getJMQIStructure() {
/* 240 */     this.jmqiStructure.setVersion(calcMinimumVersion());
/* 241 */     this.jmqiStructure.setChannelName(this.channelName);
/* 242 */     this.jmqiStructure.setQMgrName(this.queueManagerName);
/* 243 */     this.jmqiStructure.setMaxMsgLength(this.maxMessageLength);
/* 244 */     this.jmqiStructure.setConnectionName(this.connectionName);
/* 245 */     this.jmqiStructure.setRemoteUserIdentifier(this.remoteUserId);
/* 246 */     this.jmqiStructure.setRemotePassword(this.remotePassword);
/* 247 */     this.jmqiStructure.setSslPeerName(this.sslPeerName);
/* 248 */     this.jmqiStructure.setLocalAddress(this.localAddress);
/* 249 */     this.jmqiStructure.setSslCipherSpec(this.sslCipherSpec);
/*     */ 
/*     */     
/* 252 */     this.jmqiStructure.setSecurityUserData(this.securityUserData);
/* 253 */     this.jmqiStructure.setSendUserData(this.sendUserData);
/* 254 */     this.jmqiStructure.setReceiveUserData(this.receiveUserData);
/*     */ 
/*     */     
/* 257 */     Collection<Integer> localHdrCompList = this.hdrCompList;
/* 258 */     if (localHdrCompList != null) {
/* 259 */       int[] hdrCompListArray = new int[localHdrCompList.size()];
/* 260 */       Iterator<Integer> iterator = localHdrCompList.iterator();
/* 261 */       int entryIndex = 0;
/* 262 */       while (iterator.hasNext()) {
/* 263 */         hdrCompListArray[entryIndex] = ((Integer)iterator.next()).intValue();
/* 264 */         entryIndex++;
/*     */       } 
/* 266 */       this.jmqiStructure.setHdrCompList(hdrCompListArray);
/*     */     } else {
/* 268 */       this.jmqiStructure.setHdrCompList(null);
/*     */     } 
/*     */     
/* 271 */     Collection<Integer> localMsgCompList = this.msgCompList;
/* 272 */     if (localMsgCompList != null) {
/* 273 */       int[] msgCompListArray = new int[localMsgCompList.size()];
/* 274 */       Iterator<Integer> iterator = localMsgCompList.iterator();
/* 275 */       int entryIndex = 0;
/* 276 */       while (iterator.hasNext()) {
/* 277 */         msgCompListArray[entryIndex] = ((Integer)iterator.next()).intValue();
/* 278 */         entryIndex++;
/*     */       } 
/* 280 */       this.jmqiStructure.setMsgCompList(msgCompListArray);
/*     */     } else {
/* 282 */       this.jmqiStructure.setMsgCompList(null);
/*     */     } 
/*     */ 
/*     */     
/* 286 */     if (this.sharingConversations != -1) {
/* 287 */       this.jmqiStructure.setSharingConversations(this.sharingConversations);
/*     */     }
/*     */     
/* 290 */     if (Trace.isOn) {
/* 291 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQChannelDefinition", "getJMQIStructure()", "getter", this.jmqiStructure);
/*     */     }
/*     */     
/* 294 */     return this.jmqiStructure;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\base\internal\MQChannelDefinition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */