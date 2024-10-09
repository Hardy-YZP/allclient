/*     */ package com.ibm.mq;
/*     */ 
/*     */ import com.ibm.mq.exits.MQCD;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.mq.jmqi.JmqiUtils;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.Collection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/MQChannelDefinition.java";
/*     */   
/*     */   static {
/*  58 */     if (Trace.isOn) {
/*  59 */       Trace.data("com.ibm.mq.MQChannelDefinition", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/MQChannelDefinition.java");
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
/*  74 */   private MQCD jmqiStructure = JmqiSESSION.getJmqiEnv().newMQCD();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String channelName;
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
/*     */   public int maxMessageLength;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String securityUserData;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String sendUserData;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String receiveUserData;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String connectionName;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String remoteUserId;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String remotePassword;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String sslPeerName;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String localAddress;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 139 */   public Collection<Integer> hdrCompList = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 144 */   public Collection<Integer> msgCompList = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String sslCipherSuite;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 154 */   public int sharingConversations = 10;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQChannelDefinition() {
/* 160 */     super(JmqiSESSION.getJmqiEnv());
/* 161 */     if (Trace.isOn) {
/* 162 */       Trace.entry(this, "com.ibm.mq.MQChannelDefinition", "<init>()");
/*     */     }
/* 164 */     if (Trace.isOn) {
/* 165 */       Trace.exit(this, "com.ibm.mq.MQChannelDefinition", "<init>()");
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
/*     */   private int calcMinimumVersion() {
/* 178 */     if (Trace.isOn) {
/* 179 */       Trace.entry(this, "com.ibm.mq.MQChannelDefinition", "calcMinimumVersion()");
/*     */     }
/* 181 */     int version = 2;
/*     */     
/* 183 */     if (this.remoteUserId != null && this.remoteUserId.length() > 12) {
/* 184 */       version = 6;
/*     */     }
/* 186 */     if (this.remotePassword != null && this.remotePassword.length() > 12) {
/* 187 */       version = 6;
/*     */     }
/*     */     
/* 190 */     if (this.localAddress != null && this.localAddress.length() > 0) {
/* 191 */       version = 7;
/*     */     }
/* 193 */     if (this.sslCipherSuite != null && this.sslCipherSuite.length() > 0) {
/* 194 */       version = 7;
/*     */     }
/* 196 */     if (this.sslPeerName != null && this.sslPeerName.length() > 0) {
/* 197 */       version = 7;
/*     */     }
/*     */     
/* 200 */     if (this.hdrCompList != null && this.hdrCompList.size() > 0) {
/* 201 */       version = 8;
/*     */     }
/* 203 */     if (this.msgCompList != null && this.msgCompList.size() > 0) {
/* 204 */       version = 8;
/*     */     }
/*     */     
/* 207 */     if (Trace.isOn) {
/* 208 */       Trace.exit(this, "com.ibm.mq.MQChannelDefinition", "calcMinimumVersion()", 
/* 209 */           Integer.valueOf(version));
/*     */     }
/* 211 */     return version;
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
/*     */   protected MQCD getJMQIStructure(boolean fipsRequired) {
/* 228 */     if (Trace.isOn)
/* 229 */       Trace.entry(this, "com.ibm.mq.MQChannelDefinition", "getJMQIStructure(boolean)", new Object[] {
/* 230 */             Boolean.valueOf(fipsRequired)
/*     */           }); 
/* 232 */     this.jmqiStructure.setVersion(calcMinimumVersion());
/* 233 */     this.jmqiStructure.setChannelName(this.channelName);
/* 234 */     this.jmqiStructure.setQMgrName(this.queueManagerName);
/* 235 */     this.jmqiStructure.setMaxMsgLength(this.maxMessageLength);
/* 236 */     this.jmqiStructure.setConnectionName(this.connectionName);
/* 237 */     this.jmqiStructure.setRemoteUserIdentifier(this.remoteUserId);
/* 238 */     this.jmqiStructure.setRemotePassword(this.remotePassword);
/* 239 */     this.jmqiStructure.setSslPeerName(this.sslPeerName);
/* 240 */     this.jmqiStructure.setLocalAddress(this.localAddress);
/* 241 */     this.jmqiStructure.setSharingConversations(this.sharingConversations);
/* 242 */     String cipherSpec = null;
/* 243 */     if (this.sslCipherSuite != null) {
/* 244 */       cipherSpec = JmqiUtils.toCipherSpec(this.sslCipherSuite, fipsRequired);
/*     */     }
/* 246 */     this.jmqiStructure.setSslCipherSpec(cipherSpec);
/*     */     
/* 248 */     this.jmqiStructure.setSecurityUserData(this.securityUserData);
/* 249 */     this.jmqiStructure.setSendUserData(this.sendUserData);
/* 250 */     this.jmqiStructure.setReceiveUserData(this.receiveUserData);
/*     */     
/* 252 */     this.jmqiStructure.setHdrCompList(compListToArray(this.hdrCompList));
/*     */ 
/*     */     
/* 255 */     this.jmqiStructure.setMsgCompList(compListToArray(this.msgCompList));
/*     */     
/* 257 */     if (Trace.isOn) {
/* 258 */       Trace.exit(this, "com.ibm.mq.MQChannelDefinition", "getJMQIStructure(boolean)", this.jmqiStructure);
/*     */     }
/*     */     
/* 261 */     return this.jmqiStructure;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int[] compListToArray(Collection<Integer> compList) {
/* 267 */     if (Trace.isOn) {
/* 268 */       Trace.entry(this, "com.ibm.mq.MQChannelDefinition", "compListToArray(Collection<Integer>)", new Object[] { compList });
/*     */     }
/*     */     
/* 271 */     int[] result = null;
/* 272 */     if (compList != null) {
/* 273 */       result = new int[compList.size()];
/* 274 */       int entryIndex = 0;
/* 275 */       for (Integer compressionCode : compList) {
/* 276 */         result[entryIndex++] = compressionCode.intValue();
/*     */       }
/*     */     } 
/* 279 */     if (Trace.isOn) {
/* 280 */       Trace.exit(this, "com.ibm.mq.MQChannelDefinition", "compListToArray(Collection<Integer>)", result);
/*     */     }
/*     */     
/* 283 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQChannelDefinition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */