/*     */ package com.ibm.mq.jmqi.system.internal;
/*     */ 
/*     */ import com.ibm.mq.exits.MQCD;
/*     */ import java.util.Iterator;
/*     */ import org.json.JSONArray;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ConnectionManagementAttributes
/*     */   extends ChannelDefinitionAttributes
/*     */ {
/*     */   public ConnectionManagementAttributes(MQCD channelDefinition) {
/* 509 */     super("connectionManagement", channelDefinition, true);
/*     */   }
/*     */   public void setAttributes(String key, JSONObject obj) throws JSONException {
/*     */     String affinityStr;
/*     */     String defaultReconnectStr;
/*     */     StringBuilder sb;
/*     */     JSONArray localAddresses;
/*     */     int i;
/* 517 */     switch (key) {
/*     */       case "sharingConversations":
/* 519 */         this.channelDefinition.setSharingConversations(getIntegerFromJSONObject(obj, "sharingConversations").intValue());
/*     */       
/*     */       case "clientWeight":
/* 522 */         this.channelDefinition.setClientChannelWeight(getIntegerFromJSONObject(obj, "clientWeight").intValue());
/*     */       
/*     */       case "affinity":
/* 525 */         affinityStr = obj.getString("affinity");
/* 526 */         switch (affinityStr) {
/*     */           case "none":
/* 528 */             this.channelDefinition.setConnectionAffinity(0);
/*     */           
/*     */           case "preferred":
/* 531 */             this.channelDefinition.setConnectionAffinity(1);
/*     */         } 
/*     */         
/* 534 */         throw JSONExceptionFactory.InvalidAffinityValue(affinityStr);
/*     */ 
/*     */       
/*     */       case "defaultReconnect":
/* 538 */         defaultReconnectStr = obj.getString("defaultReconnect");
/*     */         
/* 540 */         switch (defaultReconnectStr) {
/*     */           case "no":
/*     */           case "yes":
/*     */           case "disabled":
/*     */           case "queueManager":
/*     */             return;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 554 */         throw JSONExceptionFactory.InvalidDefaultReconnectValue(defaultReconnectStr);
/*     */ 
/*     */       
/*     */       case "disconnectInterval":
/* 558 */         this.channelDefinition.setDiscInterval(getIntegerFromJSONObject(obj, "disconnectInterval").intValue());
/*     */       
/*     */       case "heartbeatInterval":
/* 561 */         this.channelDefinition.setHeartbeatInterval(getIntegerFromJSONObject(obj, "heartbeatInterval").intValue());
/*     */       
/*     */       case "keepAliveInterval":
/* 564 */         this.channelDefinition.setKeepAliveInterval(getIntegerFromJSONObject(obj, "keepAliveInterval").intValue());
/*     */       
/*     */       case "localAddress":
/* 567 */         sb = new StringBuilder();
/* 568 */         localAddresses = obj.getJSONArray("localAddress");
/* 569 */         for (i = 0; i < localAddresses.length(); i++) {
/* 570 */           JSONObject localAddress = localAddresses.getJSONObject(i);
/* 571 */           String addressString = getAddressString(localAddress);
/* 572 */           if (i > 0) {
/* 573 */             sb.append(",");
/*     */           }
/* 575 */           sb.append(addressString);
/*     */         } 
/* 577 */         this.channelDefinition.setLocalAddress(sb.toString());
/*     */     } 
/*     */     
/* 580 */     throw JSONExceptionFactory.UnsupportedKey(this.name, key);
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
/*     */   private String getAddressString(JSONObject obj) throws JSONException {
/* 592 */     String connectionString, host = "";
/* 593 */     Integer port = null;
/* 594 */     String portRangeStr = null;
/* 595 */     Iterator<?> it = obj.keys();
/* 596 */     while (it.hasNext()) {
/* 597 */       JSONObject portRange; String key = (String)it.next();
/* 598 */       switch (key) {
/*     */         case "host":
/* 600 */           host = obj.getString("host");
/*     */           continue;
/*     */         case "port":
/* 603 */           port = getIntegerFromJSONObject(obj, "port");
/*     */           continue;
/*     */         case "portRange":
/* 606 */           portRange = obj.getJSONObject("portRange");
/* 607 */           portRangeStr = getPortRangeString(portRange);
/*     */           continue;
/*     */       } 
/* 610 */       throw JSONExceptionFactory.UnsupportedKey(this.name, key);
/*     */     } 
/*     */ 
/*     */     
/* 614 */     if (port != null && portRangeStr != null) {
/* 615 */       throw JSONExceptionFactory.IncompatibleDataForLocalAddress();
/*     */     }
/*     */ 
/*     */     
/* 619 */     if (port != null) {
/* 620 */       connectionString = host + "(" + port + ")";
/*     */     }
/* 622 */     else if (portRangeStr != null) {
/* 623 */       connectionString = host + "(" + portRangeStr + ")";
/*     */     } else {
/*     */       
/* 626 */       connectionString = host;
/*     */     } 
/*     */     
/* 629 */     return connectionString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String getPortRangeString(JSONObject obj) throws JSONException {
/* 640 */     Integer low = null;
/* 641 */     Integer high = null;
/* 642 */     Iterator<?> it = obj.keys();
/* 643 */     while (it.hasNext()) {
/* 644 */       String key = (String)it.next();
/* 645 */       switch (key) {
/*     */         case "low":
/* 647 */           low = getIntegerFromJSONObject(obj, "low");
/*     */           continue;
/*     */         case "high":
/* 650 */           high = getIntegerFromJSONObject(obj, "high");
/*     */           continue;
/*     */       } 
/* 653 */       throw JSONExceptionFactory.UnsupportedKey(this.name, key);
/*     */     } 
/*     */ 
/*     */     
/* 657 */     return (low != null && high != null) ? (low + "," + high) : null;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\internal\ConnectionManagementAttributes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */