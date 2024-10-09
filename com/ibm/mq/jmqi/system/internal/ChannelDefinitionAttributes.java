/*     */ package com.ibm.mq.jmqi.system.internal;
/*     */ 
/*     */ import com.ibm.mq.exits.MQCD;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.Iterator;
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
/*     */ abstract class ChannelDefinitionAttributes
/*     */ {
/* 268 */   private static final Class<JSONCCDTFileParser> cclass = JSONCCDTFileParser.class;
/*     */ 
/*     */   
/*     */   protected final String name;
/*     */ 
/*     */   
/*     */   protected final MQCD channelDefinition;
/*     */   
/*     */   protected boolean valid;
/*     */ 
/*     */   
/*     */   protected static Integer getIntegerFromJSONObject(JSONObject obj, String key) throws JSONException {
/* 280 */     Object value = obj.get(key);
/* 281 */     if (value instanceof Integer) {
/* 282 */       return (Integer)value;
/*     */     }
/* 284 */     throw JSONExceptionFactory.InvalidIntValue(key, value.toString());
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
/*     */   ChannelDefinitionAttributes(String name, MQCD channelDefinition, boolean valid) {
/* 301 */     this.name = name;
/* 302 */     this.channelDefinition = channelDefinition;
/* 303 */     this.valid = valid;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void processChannelDefinition(MQCD cd, String key, JSONObject channel) throws JSONException {
/*     */     JSONObject obj;
/*     */     ChannelDefinitionAttributes attributes;
/* 315 */     String methodSignature = "processGeneralDefinition(MQCD, JSONObject)";
/* 316 */     if (Trace.isOn) {
/* 317 */       Trace.entry(cclass.getName(), "processGeneralDefinition(MQCD, JSONObject)", new Object[] { cd, key, channel });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 323 */     switch (key) {
/*     */       case "clientConnection":
/* 325 */         obj = channel.getJSONObject("clientConnection");
/* 326 */         attributes = new ClientConnectionAttributes(cd);
/*     */         break;
/*     */       case "compression":
/* 329 */         obj = channel.getJSONObject("compression");
/* 330 */         attributes = new CompressionAttributes(cd);
/*     */         break;
/*     */       case "connectionManagement":
/* 333 */         obj = channel.getJSONObject("connectionManagement");
/* 334 */         attributes = new ConnectionManagementAttributes(cd);
/*     */         break;
/*     */       case "exits":
/* 337 */         obj = channel.getJSONObject("exits");
/* 338 */         attributes = new ExitsAttributes(cd);
/*     */         break;
/*     */       case "general":
/* 341 */         obj = channel.getJSONObject("general");
/* 342 */         attributes = new GeneralAttributes(cd);
/*     */         break;
/*     */       case "timestamps":
/* 345 */         obj = channel.getJSONObject("timestamps");
/* 346 */         attributes = new TimestampsAttributes(cd);
/*     */         break;
/*     */       case "transmissionSecurity":
/* 349 */         obj = channel.getJSONObject("transmissionSecurity");
/* 350 */         attributes = new TransmissionSecurityAttributes(cd);
/*     */         break;
/*     */       default:
/* 353 */         throw JSONExceptionFactory.UnsupportedKey("channel", key);
/*     */     } 
/*     */     
/* 356 */     attributes.processDefinition(obj);
/* 357 */     attributes.validate();
/*     */     
/* 359 */     if (Trace.isOn) {
/* 360 */       Trace.exit(cclass.getName(), "processGeneralDefinition(MQCD, JSONObject)");
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
/*     */   protected abstract void setAttributes(String paramString, JSONObject paramJSONObject) throws JSONException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void processDefinition(JSONObject obj) throws JSONException {
/* 380 */     String methodSignature = "processDefinition(JSONObject)";
/* 381 */     if (Trace.isOn) {
/* 382 */       Trace.entry(this, cclass.getName(), "processDefinition(JSONObject)", new Object[] { obj });
/*     */     }
/*     */ 
/*     */     
/* 386 */     Iterator<?> it = obj.keys();
/* 387 */     while (it.hasNext()) {
/* 388 */       String key = (String)it.next();
/* 389 */       setAttributes(key, obj);
/*     */     } 
/*     */     
/* 392 */     if (Trace.isOn) {
/* 393 */       Trace.exit(this, cclass.getName(), "processDefinition(JSONObject)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void validate() throws JSONException {
/* 403 */     if (!checkValid()) {
/* 404 */       throw JSONExceptionFactory.InvalidAttributes(this.name);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean checkValid() {
/* 412 */     return this.valid;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\internal\ChannelDefinitionAttributes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */