/*     */ package com.ibm.msg.client.wmq.common.internal;
/*     */ 
/*     */ import com.ibm.msg.client.jms.JmsReadablePropertyContext;
/*     */ import javax.jms.JMSException;
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
/*     */ public enum StringableProperty
/*     */ {
/*  49 */   ObjectId("Object")
/*     */   {
/*     */     void resolve(JmsReadablePropertyContext context, JSONObject json)
/*     */     {
/*     */       try {
/*  54 */         json.put(name(), context.stringifyMe());
/*     */       }
/*  56 */       catch (JSONException jSONException) {}
/*     */     }
/*     */   },
/*     */ 
/*     */ 
/*     */   
/*  62 */   Channel("XMSC_WMQ_CHANNEL"),
/*     */   
/*  64 */   ConnectionMode("XMSC_WMQ_CONNECTION_MODE")
/*     */   {
/*     */     void resolve(JmsReadablePropertyContext context, JSONObject json)
/*     */     {
/*     */       String propertyValue;
/*     */       try {
/*  70 */         propertyValue = context.getStringProperty(this.propertyName);
/*     */       }
/*  72 */       catch (JMSException e) {
/*     */         return;
/*     */       } 
/*  75 */       if (propertyValue != null && propertyValue.length() > 0) {
/*     */         try {
/*  77 */           json.put(name(), decode(propertyValue));
/*     */         }
/*  79 */         catch (JSONException jSONException) {}
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     String decode(String propertyValue) {
/*     */       try {
/*  87 */         int propertyAsInt = Integer.parseInt(propertyValue);
/*  88 */         switch (propertyAsInt) {
/*     */           case 0:
/*  90 */             return "WMQ_CM_BINDINGS";
/*     */           case 1:
/*  92 */             return "WMQ_CM_CLIENT";
/*     */           case 8:
/*  94 */             return "WMQ_CM_BINDINGS_THEN_CLIENT";
/*     */         } 
/*  96 */         return String.format("Unrecognised value(%d)", new Object[] { Integer.valueOf(propertyAsInt) });
/*     */       
/*     */       }
/*  99 */       catch (NumberFormatException npe) {
/* 100 */         return String.format("Unrecognised value(%s)", new Object[] { propertyValue });
/*     */       }
/*     */     
/*     */     }
/*     */   },
/* 105 */   Host("XMSC_WMQ_HOST_NAME"),
/*     */   
/* 107 */   Port("XMSC_WMQ_PORT"),
/*     */   
/* 109 */   ResolvedQueueManager("XMSC_WMQ_RESOLVED_QUEUE_MANAGER"),
/*     */   
/* 111 */   ConnectionId("XMSC_WMQ_CONNECTION_ID"),
/*     */   
/* 113 */   QueueManager("XMSC_WMQ_QUEUE_MANAGER");
/*     */   
/*     */   String propertyName;
/*     */   
/*     */   StringableProperty(String propertyName) {
/* 118 */     this.propertyName = propertyName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static JSONObject jsonIfy(JmsReadablePropertyContext context) {
/* 127 */     JSONObject json = new JSONObject();
/* 128 */     for (StringableProperty stringableProperty : values()) {
/* 129 */       stringableProperty.resolve(context, json);
/*     */     }
/* 131 */     return json;
/*     */   }
/*     */   
/*     */   void resolve(JmsReadablePropertyContext context, JSONObject json) {
/*     */     String propertyValue;
/*     */     try {
/* 137 */       propertyValue = context.getStringProperty(this.propertyName);
/*     */     }
/* 139 */     catch (JMSException e) {
/*     */       return;
/*     */     } 
/* 142 */     if (propertyValue != null && propertyValue.length() > 0)
/*     */       try {
/* 144 */         json.put(name(), propertyValue);
/*     */       }
/* 146 */       catch (JSONException jSONException) {} 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\common\internal\StringableProperty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */