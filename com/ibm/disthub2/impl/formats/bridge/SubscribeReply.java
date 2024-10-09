/*     */ package com.ibm.disthub2.impl.formats.bridge;
/*     */ 
/*     */ import com.ibm.disthub2.client.MessageBodyHandle;
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
/*     */ import com.ibm.disthub2.impl.formats.MessageDataHandle;
/*     */ import com.ibm.disthub2.impl.multicast.MulticastTopic;
/*     */ import com.ibm.disthub2.impl.security.SecurityContext;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class SubscribeReply
/*     */   extends ControlMessageBody
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  70 */   private static final DebugObject debug = new DebugObject("SubscribeReply");
/*     */ 
/*     */ 
/*     */   
/*     */   boolean ipv6 = false;
/*     */ 
/*     */   
/*     */   MulticastTopic[] multicastTopics;
/*     */ 
/*     */ 
/*     */   
/*     */   public static SubscribeReply create() {
/*  82 */     Jgram jg = new Jgram(10);
/*  83 */     SingleHopControl msg = (SingleHopControl)jg.getPayload();
/*  84 */     SubscribeReply ans = (SubscribeReply)msg.setBody(2);
/*  85 */     ans.cursor.setChoice(168, 0);
/*  86 */     ans.setGdsFlags((String)null, (Hashtable)null, (Hashtable)null);
/*  87 */     return ans;
/*     */   }
/*     */ 
/*     */   
/*     */   SubscribeReply(int type, MessageDataHandle cursor, SingleHopControl msg) {
/*  92 */     super(type, cursor, msg);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private SubscribeReply(SubscribeId compat) {
/* 101 */     this(-1, (MessageDataHandle)null, (SingleHopControl)null);
/* 102 */     this.msg = new SingleHopControl(this, compat.getNormalMessage());
/* 103 */     this.topicCompatible = compat;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getId() {
/* 108 */     if (this.topicCompatible != null)
/*     */     {
/* 110 */       return ((SubscribeId)this.topicCompatible).getId();
/*     */     }
/*     */     
/* 113 */     return this.cursor.getInt(54);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(int val) {
/* 119 */     if (this.topicCompatible != null) {
/*     */       
/* 121 */       ((SubscribeId)this.topicCompatible).setId(val);
/*     */     } else {
/*     */       
/* 124 */       this.cursor.setInt(54, val);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIpv6Connection() {
/* 130 */     this.ipv6 = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean ipv6Connection() {
/* 135 */     return this.ipv6;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void rebuildMulticastTable() {
/* 143 */     if (this.multicastTopics[0] == null) {
/* 144 */       this.cursor.setChoice(168, 0);
/*     */     } else {
/*     */       
/* 147 */       this.cursor.setChoice(168, 1);
/* 148 */       this.cursor.setBoolean(58, (this.multicastTopics[0] != null));
/*     */ 
/*     */       
/* 151 */       int numRows = 0;
/* 152 */       if (this.multicastTopics != null) {
/* 153 */         numRows = this.multicastTopics.length;
/*     */       }
/* 155 */       MessageBodyHandle[] rows = new MessageBodyHandle[this.multicastTopics.length];
/* 156 */       if (this.multicastTopics != null && 
/* 157 */         this.multicastTopics[0] != null) {
/* 158 */         for (int i = 0; i < rows.length; i++) {
/* 159 */           rows[i] = this.cursor.newTableRow(59);
/* 160 */           rows[i].setString(0, (this.multicastTopics[i]).topic);
/* 161 */           rows[i].setString(1, (this.multicastTopics[i]).partitionLabel);
/*     */           
/* 163 */           if (ipv6Connection() && (this.multicastTopics[i]).groupAddressIpv6 != null) {
/* 164 */             rows[i].setString(2, (this.multicastTopics[i]).groupAddressIpv6);
/*     */           } else {
/*     */             
/* 167 */             rows[i].setString(2, (this.multicastTopics[i]).groupAddress);
/*     */           } 
/* 169 */           rows[i].setBoolean(3, (this.multicastTopics[i]).enabled);
/* 170 */           rows[i].setBoolean(4, (this.multicastTopics[i]).reliable);
/* 171 */           rows[i].setByte(5, (this.multicastTopics[i]).qop);
/* 172 */           rows[i].setByteArray(6, this.multicastTopics[i].getKey());
/* 173 */           rows[i].setLong(7, (this.multicastTopics[i]).timeStamp);
/*     */         } 
/*     */       }
/*     */       
/* 177 */       this.cursor.setTable(59, rows);
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
/*     */   public void setGdsFlags(String reconnId, Hashtable tmins, Hashtable startTimes) {
/* 195 */     if (reconnId == null && tmins == null && startTimes == null) {
/* 196 */       this.cursor.setChoice(165, 0);
/*     */       return;
/*     */     } 
/* 199 */     this.cursor.setChoice(165, 1);
/* 200 */     this.cursor.setString(55, reconnId);
/*     */ 
/*     */     
/* 203 */     MessageBodyHandle[] tminTab = (tmins != null && tmins.size() != 0) ? new MessageBodyHandle[tmins.size()] : null;
/*     */ 
/*     */     
/* 206 */     if (tmins != null) {
/* 207 */       Enumeration<Long> peIds = tmins.keys();
/* 208 */       int i = 0;
/* 209 */       while (peIds.hasMoreElements()) {
/* 210 */         tminTab[i] = this.cursor.newTableRow(56);
/* 211 */         Long pe = peIds.nextElement();
/* 212 */         long pid = pe.longValue();
/* 213 */         long stamp = ((Long)tmins.get(pe)).longValue();
/* 214 */         tminTab[i].setLong(0, pid);
/* 215 */         tminTab[i].setLong(1, stamp);
/* 216 */         i++;
/*     */       } 
/*     */     } 
/* 219 */     this.cursor.setTable(56, tminTab);
/*     */ 
/*     */     
/* 222 */     MessageBodyHandle[] startTab = (startTimes != null && startTimes.size() != 0) ? new MessageBodyHandle[startTimes.size()] : null;
/* 223 */     if (startTimes != null) {
/* 224 */       Enumeration<Long> peIds = startTimes.keys();
/* 225 */       int i = 0;
/* 226 */       while (peIds.hasMoreElements()) {
/* 227 */         startTab[i] = this.cursor.newTableRow(57);
/* 228 */         Long pe = peIds.nextElement();
/* 229 */         long pid = pe.longValue();
/* 230 */         long stamp = ((Long)startTimes.get(pe)).longValue();
/* 231 */         startTab[i].setLong(0, pid);
/* 232 */         startTab[i].setLong(1, stamp);
/* 233 */         i++;
/*     */       } 
/*     */     } 
/* 236 */     this.cursor.setTable(57, startTab);
/*     */   }
/*     */ 
/*     */   
/*     */   public Hashtable getTmins() {
/* 241 */     if (!this.cursor.isPresent(166)) {
/* 242 */       return null;
/*     */     }
/*     */     
/* 245 */     Hashtable<Object, Object> ans = new Hashtable<>();
/* 246 */     MessageBodyHandle[] tminTab = this.cursor.getTable(56);
/* 247 */     for (int i = 0; i < tminTab.length; i++) {
/* 248 */       long pubendId = tminTab[i].getLong(0);
/* 249 */       long tmin = tminTab[i].getLong(1);
/* 250 */       ans.put(Long.valueOf(pubendId), Long.valueOf(tmin));
/*     */     } 
/*     */     
/* 253 */     return ans;
/*     */   }
/*     */ 
/*     */   
/*     */   public Hashtable getStartTime() {
/* 258 */     if (!this.cursor.isPresent(167)) {
/* 259 */       return null;
/*     */     }
/*     */     
/* 262 */     Hashtable<Object, Object> ans = new Hashtable<>();
/* 263 */     MessageBodyHandle[] startTimeTab = this.cursor.getTable(57);
/* 264 */     for (int i = 0; i < startTimeTab.length; i++) {
/* 265 */       long pubendId = startTimeTab[i].getLong(0);
/* 266 */       long ts = startTimeTab[i].getLong(1);
/* 267 */       ans.put(Long.valueOf(pubendId), Long.valueOf(ts));
/*     */     } 
/*     */     
/* 270 */     return ans;
/*     */   }
/*     */   
/*     */   public String getReconnId(String reconnId) {
/* 274 */     if (!this.cursor.isPresent(55)) {
/* 275 */       return null;
/*     */     }
/*     */     
/* 278 */     return this.cursor.getString(55);
/*     */   }
/*     */   
/*     */   public void setMulticastTopics(MulticastTopic[] multicastTopics) {
/* 282 */     this.multicastTopics = multicastTopics;
/* 283 */     rebuildMulticastTable();
/*     */   }
/*     */   
/*     */   public boolean getMulticastEnabled() {
/* 287 */     if (this.cursor.getChoice(168) == 1) {
/* 288 */       return this.cursor.getBoolean(58);
/*     */     }
/*     */     
/* 291 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 296 */     String gdsFlags = "";
/* 297 */     if (this.cursor.getChoice(165) == 1) {
/* 298 */       String tmp = this.cursor.getString(55);
/* 299 */       if (tmp != null) {
/* 300 */         gdsFlags = gdsFlags + SecurityContext.rid2Native(tmp) + ",";
/*     */       }
/* 302 */       gdsFlags = gdsFlags + "[";
/* 303 */       MessageBodyHandle[] tab = this.cursor.getTable(56); int i;
/* 304 */       for (i = 0; i < tab.length; i++) {
/* 305 */         gdsFlags = gdsFlags + "(";
/* 306 */         gdsFlags = gdsFlags + tab[i].getLong(0) + ",";
/* 307 */         gdsFlags = gdsFlags + tab[i].getLong(1);
/* 308 */         gdsFlags = gdsFlags + ")";
/*     */       } 
/*     */       
/* 311 */       gdsFlags = gdsFlags + "],[";
/* 312 */       tab = this.cursor.getTable(57);
/* 313 */       for (i = 0; i < tab.length; i++) {
/* 314 */         gdsFlags = gdsFlags + "(";
/* 315 */         gdsFlags = gdsFlags + tab[i].getLong(0) + ",";
/* 316 */         gdsFlags = gdsFlags + tab[i].getLong(1);
/* 317 */         gdsFlags = gdsFlags + ")";
/*     */       } 
/* 319 */       gdsFlags = gdsFlags + "]";
/*     */     } 
/* 321 */     return "id=" + (this.cursor.isPresent(54) ? (this.cursor.getInt(54) + "") : "UNKNOWN") + "," + gdsFlags;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\bridge\SubscribeReply.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */