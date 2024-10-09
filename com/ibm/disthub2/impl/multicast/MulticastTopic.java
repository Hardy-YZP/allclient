/*     */ package com.ibm.disthub2.impl.multicast;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
/*     */ import com.ibm.disthub2.impl.security.CryptoInstantiationException;
/*     */ import com.ibm.disthub2.impl.security.MulticastSecurityContext;
/*     */ import com.ibm.disthub2.spi.ClientExceptionConstants;
/*     */ import com.ibm.disthub2.spi.ClientLogConstants;
/*     */ import com.ibm.disthub2.spi.ExceptionBuilder;
/*     */ import com.ibm.disthub2.spi.Principal;
/*     */ import java.io.IOException;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MulticastTopic
/*     */   implements ClientExceptionConstants, ClientLogConstants
/*     */ {
/*  63 */   private static final DebugObject debug = new DebugObject("MulticastTopic");
/*     */   
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   
/*     */   public String topicId;
/*     */   
/*     */   public String topic;
/*     */   public String partitionLabel;
/*     */   public boolean reliable = false;
/*     */   public String groupAddress;
/*     */   public String groupAddressIpv6;
/*     */   public boolean enabled = true;
/*  75 */   public byte[] key = null;
/*  76 */   public long timeStamp = 0L;
/*  77 */   public byte qop = 1;
/*     */   
/*     */   private KeyManager keyManager;
/*  80 */   public long newKey = 0L;
/*     */   
/*     */   public boolean newCounter = false;
/*     */   public boolean rekey = false;
/*     */   public MulticastSecurityContext securityContext;
/*     */   public Hashtable principals;
/*     */   public Object consolidatedPermissions;
/*  87 */   static final RealSecUsername multicastUser = new RealSecUsername("Multicast");
/*     */   
/*     */   public MulticastTopic(String topic, String groupAddress, boolean reliable, byte qop) {
/*  90 */     if (debug.debugIt(32)) {
/*  91 */       debug.debug(-165922073994779L, "MulticastTopic", topic, groupAddress, Boolean.valueOf(reliable), Byte.valueOf(qop));
/*     */     }
/*     */     
/*  94 */     this.topic = topic;
/*  95 */     this.enabled = true;
/*  96 */     this.reliable = reliable;
/*  97 */     this.groupAddress = (groupAddress != null) ? groupAddress.trim() : null;
/*  98 */     this.qop = qop;
/*     */     
/* 100 */     if (debug.debugIt(64)) {
/* 101 */       debug.debug(-142394261359015L, "MulticastTopic");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public MulticastTopic(String topic, String groupAddress, String groupAddressIpv6, boolean reliable, byte qop) {
/* 107 */     if (debug.debugIt(32)) {
/* 108 */       debug.debug(-165922073994779L, "MulticastTopic", topic, groupAddress, Boolean.valueOf(reliable), Byte.valueOf(qop));
/*     */     }
/*     */     
/* 111 */     this.topic = topic;
/* 112 */     this.enabled = true;
/* 113 */     this.reliable = reliable;
/* 114 */     this.groupAddress = (groupAddress != null) ? groupAddress.trim() : null;
/* 115 */     this.groupAddressIpv6 = (groupAddressIpv6 != null) ? groupAddressIpv6.trim() : null;
/* 116 */     this.qop = qop;
/*     */     
/* 118 */     if (debug.debugIt(64)) {
/* 119 */       debug.debug(-142394261359015L, "MulticastTopic");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public MulticastTopic(String topicId, String topic, String groupAddress, String groupAddressIpv6, boolean enabled, boolean reliable, boolean secure) {
/* 125 */     this(topic, groupAddress, reliable, (byte)1);
/* 126 */     if (debug.debugIt(32)) {
/* 127 */       debug.debug(-165922073994779L, "MulticastTopic", topic, groupAddress, Boolean.valueOf(enabled), Boolean.valueOf(reliable), Boolean.valueOf(secure));
/*     */     }
/*     */     
/* 130 */     this.topicId = (topicId != null) ? topicId.trim() : null;
/* 131 */     this.groupAddressIpv6 = (groupAddressIpv6 != null) ? groupAddressIpv6.trim() : null;
/* 132 */     this.enabled = enabled;
/* 133 */     this.qop = secure ? 14 : 1;
/*     */     
/* 135 */     if (debug.debugIt(64)) {
/* 136 */       debug.debug(-142394261359015L, "MulticastTopic");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public MulticastTopic(String topicId, String topic, String groupAddress, boolean enabled, boolean reliable, boolean secure) {
/* 142 */     this(topic, groupAddress, reliable, (byte)1);
/* 143 */     if (debug.debugIt(32)) {
/* 144 */       debug.debug(-165922073994779L, "MulticastTopic", topic, groupAddress, Boolean.valueOf(enabled), Boolean.valueOf(reliable), Boolean.valueOf(secure));
/*     */     }
/*     */     
/* 147 */     this.topicId = (topicId != null) ? topicId.trim() : null;
/* 148 */     this.enabled = enabled;
/* 149 */     this.qop = secure ? 14 : 1;
/*     */     
/* 151 */     if (debug.debugIt(64)) {
/* 152 */       debug.debug(-142394261359015L, "MulticastTopic");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public MulticastTopic(String partitionLabel, String topic, String groupAddress, boolean enabled, boolean reliable, byte qop, byte[] key, long timeStamp) {
/* 158 */     this(topic, groupAddress, reliable, qop);
/* 159 */     if (debug.debugIt(32)) {
/* 160 */       debug.debug(-165922073994779L, "MulticastTopic", new Object[] { partitionLabel, topic, groupAddress, Boolean.valueOf(enabled), Boolean.valueOf(reliable), Byte.valueOf(qop), key, 
/* 161 */             Long.valueOf(timeStamp) });
/*     */     }
/*     */     
/* 164 */     this.key = key;
/* 165 */     this.timeStamp = timeStamp;
/* 166 */     this.partitionLabel = partitionLabel;
/* 167 */     this.enabled = enabled;
/*     */     
/* 169 */     if (debug.debugIt(64)) {
/* 170 */       debug.debug(-142394261359015L, "MulticastTopic");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public MulticastTopic(String partitionLabel, String topic, String groupAddress, String groupAddressIpv6, boolean enabled, boolean reliable, byte qop, byte[] key, long timeStamp) {
/* 177 */     this(topic, groupAddress, groupAddressIpv6, reliable, qop);
/* 178 */     if (debug.debugIt(32)) {
/* 179 */       debug.debug(-165922073994779L, "MulticastTopic", new Object[] { partitionLabel, topic, groupAddress, Boolean.valueOf(enabled), Boolean.valueOf(reliable), Byte.valueOf(qop), key, 
/* 180 */             Long.valueOf(timeStamp) });
/*     */     }
/*     */     
/* 183 */     this.key = key;
/* 184 */     this.timeStamp = timeStamp;
/* 185 */     this.partitionLabel = partitionLabel;
/* 186 */     this.enabled = enabled;
/*     */     
/* 188 */     if (debug.debugIt(64)) {
/* 189 */       debug.debug(-142394261359015L, "MulticastTopic");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public MulticastTopic(String partitionLabel, String topic, boolean enabled) {
/* 195 */     if (debug.debugIt(32)) {
/* 196 */       debug.debug(-165922073994779L, "MulticastTopic", partitionLabel, topic, Boolean.valueOf(enabled));
/*     */     }
/*     */     
/* 199 */     this.partitionLabel = partitionLabel;
/* 200 */     this.topic = topic;
/* 201 */     this.enabled = enabled;
/*     */     
/* 203 */     if (debug.debugIt(64)) {
/* 204 */       debug.debug(-142394261359015L, "MulticastTopic");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MulticastTopic() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTopic() {
/* 216 */     return this.topic;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getGroupAddress() {
/* 224 */     return this.groupAddress;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getIpv6GroupAddress() {
/* 232 */     return this.groupAddressIpv6;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isReliable() {
/* 240 */     return this.reliable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSecure() {
/* 248 */     return (this.qop == 14);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasQop() {
/* 253 */     return (this.qop > 1);
/*     */   }
/*     */   
/*     */   public boolean isEnabled() {
/* 257 */     return this.enabled;
/*     */   }
/*     */   
/*     */   public byte[] getKey() {
/* 261 */     return this.key;
/*     */   }
/*     */   
/*     */   public long getTimeStamp() {
/* 265 */     return this.timeStamp;
/*     */   }
/*     */   
/*     */   public void setTopic(String topic) {
/* 269 */     this.topic = topic;
/*     */   }
/*     */   
/*     */   public void setTopicId(String topicId) {
/* 273 */     this.topicId = topicId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGroupAddress(String groupAddress) {
/* 281 */     this.groupAddress = groupAddress;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReliable(boolean reliable) {
/* 289 */     this.reliable = reliable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSecure(boolean secure) {
/* 297 */     this.qop = secure ? 14 : 1;
/*     */   }
/*     */   
/*     */   public void setTimeStamp(long timeStamp) {
/* 301 */     this.timeStamp = timeStamp;
/*     */   }
/*     */   
/*     */   public void setPrincipals(Hashtable principals) {
/* 305 */     this.principals = principals;
/*     */   }
/*     */   
/*     */   public void setConsolidatedPermissions(Object consolidatedPermissions) {
/* 309 */     this.consolidatedPermissions = consolidatedPermissions;
/*     */   }
/*     */   
/*     */   public Hashtable getPrincipals() {
/* 313 */     return this.principals;
/*     */   }
/*     */   
/*     */   public void setPartitionLabel(String partitionLabel) {
/* 317 */     this.partitionLabel = partitionLabel;
/*     */   }
/*     */   
/*     */   public String getPartitionLabel() {
/* 321 */     return this.partitionLabel;
/*     */   }
/*     */ 
/*     */   
/*     */   public void newKey(byte[] key, long timeStamp) {
/* 326 */     if (debug.debugIt(32)) {
/* 327 */       debug.debug(-165922073994779L, "newKey", key, Long.valueOf(timeStamp));
/*     */     }
/* 329 */     if (key != null) {
/* 330 */       this.keyManager = getKeyManager();
/* 331 */       this.keyManager.addKey(key, timeStamp);
/*     */     } 
/* 333 */     if (debug.debugIt(64)) {
/* 334 */       debug.debug(-142394261359015L, "newKey");
/*     */     }
/*     */   }
/*     */   
/*     */   public KeyManager getKeyManager() {
/* 339 */     if (this.keyManager == null) {
/* 340 */       this.keyManager = new KeyManager();
/*     */     }
/* 342 */     return this.keyManager;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setNewKey() throws IOException {
/* 347 */     if (debug.debugIt(32)) {
/* 348 */       debug.debug(-165922073994779L, "setNewKey");
/*     */     }
/* 350 */     boolean result = false;
/* 351 */     if (this.keyManager != null) {
/* 352 */       if (this.keyManager.keys.containsKey(Long.valueOf(this.newKey))) {
/* 353 */         this.timeStamp = this.newKey;
/* 354 */         setSecurity(this.keyManager.getKey(this.newKey), false);
/* 355 */         this.newKey = 0L;
/* 356 */         result = true;
/*     */       } else {
/*     */         
/* 359 */         this.newKey = 0L;
/* 360 */         result = false;
/*     */       } 
/*     */     } else {
/*     */       
/* 364 */       this.newKey = 0L;
/* 365 */       result = false;
/*     */     } 
/* 367 */     if (debug.debugIt(64)) {
/* 368 */       debug.debug(-142394261359015L, "setNewKey", Boolean.valueOf(result));
/*     */     }
/* 370 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSecurity(byte[] key, boolean server) throws IOException {
/* 375 */     if (debug.debugIt(32)) {
/* 376 */       debug.debug(-165922073994779L, "setSecurity", key, Boolean.valueOf(server));
/*     */     }
/* 378 */     this.key = key;
/* 379 */     if (key != null) {
/*     */       try {
/* 381 */         if (server) {
/* 382 */           this.timeStamp = System.currentTimeMillis();
/*     */         }
/* 384 */         this.securityContext = MulticastSecurityContext.createMulticastSecurityContext(multicastUser, key, true, this);
/*     */       }
/* 386 */       catch (CryptoInstantiationException e) {
/* 387 */         debug.debug(-175584356845707L, "setSecurity", debug.debugX((Throwable)e));
/* 388 */         throw new IOException(ExceptionBuilder.buildReasonString(-911376984, new Object[] { e }));
/*     */       }
/* 390 */       catch (Exception e) {
/* 391 */         debug.debug(-175584356845707L, "setSecurity", debug.debugX(e));
/* 392 */         throw new IOException(ExceptionBuilder.buildReasonString(-873358984, new Object[] { e }));
/*     */       } 
/*     */     } else {
/* 395 */       this.securityContext = null;
/*     */     } 
/* 397 */     this.rekey = false;
/*     */     
/* 399 */     if (debug.debugIt(64)) {
/* 400 */       debug.debug(-142394261359015L, "setSecurity");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class RealSecUsername
/*     */     implements Principal
/*     */   {
/*     */     protected String m_login;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public RealSecUsername(String login) {
/* 416 */       this.m_login = login;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 420 */       return this.m_login;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 424 */       return this.m_login.hashCode();
/*     */     }
/*     */     
/*     */     public String getName() {
/* 428 */       return this.m_login;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean equals(Object o) {
/*     */       try {
/* 436 */         RealSecUsername u = (RealSecUsername)o;
/* 437 */         return this.m_login.equals(u.m_login);
/*     */       }
/* 439 */       catch (Exception exception) {
/*     */ 
/*     */         
/* 442 */         return false;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public class KeyManager
/*     */   {
/* 455 */     public Vector keyStack = new Vector();
/* 456 */     public Hashtable keys = new Hashtable<>();
/*     */ 
/*     */     
/*     */     public byte[] getKey(long timeStamp) {
/* 460 */       if (this.keyStack.size() > 0) {
/* 461 */         Long newTimeStamp = this.keyStack.remove(0);
/* 462 */         if (timeStamp == newTimeStamp.longValue()) {
/* 463 */           return (byte[])this.keys.remove(newTimeStamp);
/*     */         }
/* 465 */         this.keys.remove(newTimeStamp);
/*     */       } 
/*     */       
/* 468 */       return null;
/*     */     }
/*     */     
/*     */     public void addKey(byte[] key, long timestamp) {
/* 472 */       synchronized (MulticastTopic.this) {
/* 473 */         Long stamp = Long.valueOf(timestamp);
/* 474 */         this.keys.put(stamp, key);
/* 475 */         this.keyStack.add(stamp);
/* 476 */         MulticastTopic.this.notifyAll();
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean newKeys() {
/* 481 */       return (this.keyStack.size() > 0);
/*     */     }
/*     */     
/*     */     public boolean hasKey(long timeStamp) {
/* 485 */       synchronized (MulticastTopic.this) {
/* 486 */         if (this.keys.containsKey(Long.valueOf(timeStamp))) {
/* 487 */           return true;
/*     */         }
/*     */ 
/*     */         
/*     */         try {
/* 492 */           MulticastTopic.this.wait(15000L);
/*     */         }
/* 494 */         catch (InterruptedException interruptedException) {}
/* 495 */         return this.keys.containsKey(Long.valueOf(timeStamp));
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\multicast\MulticastTopic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */