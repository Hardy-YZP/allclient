/*     */ package com.ibm.msg.client.wmq.messages;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.provider.ProviderMapMessage;
/*     */ import com.ibm.msg.client.provider.ProviderMessage;
/*     */ import com.ibm.msg.client.provider.ProviderSession;
/*     */ import com.ibm.msg.client.wmq.common.internal.WMQUtils;
/*     */ import com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage;
/*     */ import com.ibm.msg.client.wmq.compat.jms.internal.JMSMapMessage;
/*     */ import com.ibm.msg.client.wmq.compat.jms.internal.MQSession;
/*     */ import java.util.Collections;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.jms.JMSException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TransientMapMessage
/*     */   extends TransientMessage
/*     */   implements ProviderMapMessage
/*     */ {
/*     */   private static final long serialVersionUID = -7169725963210841081L;
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.factories/src/com/ibm/msg/client/wmq/messages/TransientMapMessage.java";
/*     */   
/*     */   static {
/*  45 */     if (Trace.isOn) {
/*  46 */       Trace.data("com.ibm.msg.client.wmq.messages.TransientMapMessage", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.factories/src/com/ibm/msg/client/wmq/messages/TransientMapMessage.java");
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
/*  61 */   private Map<String, Object> mapBody = Collections.synchronizedMap(new HashMap<>());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TransientMapMessage() throws JMSException {
/*  68 */     if (Trace.isOn) {
/*  69 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientMapMessage", "<init>()");
/*     */     }
/*  71 */     this.messageClass = "jms_map";
/*  72 */     if (Trace.isOn) {
/*  73 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientMapMessage", "<init>()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearBody() throws JMSException {
/*  83 */     if (Trace.isOn) {
/*  84 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientMapMessage", "clearBody()");
/*     */     }
/*  86 */     this.mapBody.clear();
/*  87 */     if (Trace.isOn) {
/*  88 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientMapMessage", "clearBody()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Enumeration<String> getMapNames() throws JMSException {
/*  97 */     Enumeration<String> e = WMQUtils.enumerationFromIterable(this.mapBody.keySet());
/*  98 */     if (Trace.isOn) {
/*  99 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMapMessage", "getMapNames()", "getter", e);
/*     */     }
/*     */     
/* 102 */     return e;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getObject(String name) throws JMSException {
/* 109 */     if (Trace.isOn) {
/* 110 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientMapMessage", "getObject(String)", new Object[] { name });
/*     */     }
/*     */     
/* 113 */     Object traceRet1 = this.mapBody.get(name);
/* 114 */     if (Trace.isOn) {
/* 115 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientMapMessage", "getObject(String)", traceRet1);
/*     */     }
/*     */     
/* 118 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean itemExists(String name) throws JMSException {
/* 125 */     if (Trace.isOn) {
/* 126 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientMapMessage", "itemExists(String)", new Object[] { name });
/*     */     }
/*     */     
/* 129 */     boolean traceRet1 = this.mapBody.containsKey(name);
/* 130 */     if (Trace.isOn) {
/* 131 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientMapMessage", "itemExists(String)", 
/* 132 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 134 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBoolean(String name, boolean value) throws JMSException {
/* 141 */     if (Trace.isOn) {
/* 142 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientMapMessage", "setBoolean(String,boolean)", new Object[] { name, 
/* 143 */             Boolean.valueOf(value) });
/*     */     }
/* 145 */     this.mapBody.put(name, Boolean.valueOf(value));
/* 146 */     if (Trace.isOn) {
/* 147 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientMapMessage", "setBoolean(String,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setByte(String name, byte value) throws JMSException {
/* 157 */     if (Trace.isOn) {
/* 158 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientMapMessage", "setByte(String,byte)", new Object[] { name, 
/* 159 */             Byte.valueOf(value) });
/*     */     }
/* 161 */     this.mapBody.put(name, Byte.valueOf(value));
/* 162 */     if (Trace.isOn) {
/* 163 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientMapMessage", "setByte(String,byte)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBytes(String name, byte[] value) throws JMSException {
/* 173 */     if (Trace.isOn) {
/* 174 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientMapMessage", "setBytes(String,byte [ ])", new Object[] { name, value });
/*     */     }
/*     */     
/* 177 */     this.mapBody.put(name, value);
/* 178 */     if (Trace.isOn) {
/* 179 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientMapMessage", "setBytes(String,byte [ ])");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBytes(String name, byte[] value, int offset, int length) throws JMSException {
/* 189 */     if (Trace.isOn) {
/* 190 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientMapMessage", "setBytes(String,byte [ ],int,int)", new Object[] { name, value, 
/* 191 */             Integer.valueOf(offset), 
/* 192 */             Integer.valueOf(length) });
/*     */     }
/* 194 */     byte[] bytes = new byte[length];
/* 195 */     System.arraycopy(value, offset, bytes, 0, length);
/* 196 */     this.mapBody.put(name, bytes);
/* 197 */     if (Trace.isOn) {
/* 198 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientMapMessage", "setBytes(String,byte [ ],int,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setChar(String name, char value) throws JMSException {
/* 208 */     if (Trace.isOn) {
/* 209 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientMapMessage", "setChar(String,char)", new Object[] { name, 
/* 210 */             Character.valueOf(value) });
/*     */     }
/* 212 */     this.mapBody.put(name, Character.valueOf(value));
/* 213 */     if (Trace.isOn) {
/* 214 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientMapMessage", "setChar(String,char)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDouble(String name, double value) throws JMSException {
/* 224 */     if (Trace.isOn) {
/* 225 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientMapMessage", "setDouble(String,double)", new Object[] { name, 
/* 226 */             Double.valueOf(value) });
/*     */     }
/* 228 */     this.mapBody.put(name, Double.valueOf(value));
/* 229 */     if (Trace.isOn) {
/* 230 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientMapMessage", "setDouble(String,double)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFloat(String name, float value) throws JMSException {
/* 240 */     if (Trace.isOn) {
/* 241 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientMapMessage", "setFloat(String,float)", new Object[] { name, 
/* 242 */             Float.valueOf(value) });
/*     */     }
/* 244 */     this.mapBody.put(name, Float.valueOf(value));
/* 245 */     if (Trace.isOn) {
/* 246 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientMapMessage", "setFloat(String,float)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInt(String name, int value) throws JMSException {
/* 256 */     if (Trace.isOn) {
/* 257 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientMapMessage", "setInt(String,int)", new Object[] { name, 
/* 258 */             Integer.valueOf(value) });
/*     */     }
/* 260 */     this.mapBody.put(name, Integer.valueOf(value));
/* 261 */     if (Trace.isOn) {
/* 262 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientMapMessage", "setInt(String,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLong(String name, long value) throws JMSException {
/* 272 */     if (Trace.isOn) {
/* 273 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientMapMessage", "setLong(String,long)", new Object[] { name, 
/* 274 */             Long.valueOf(value) });
/*     */     }
/* 276 */     this.mapBody.put(name, Long.valueOf(value));
/* 277 */     if (Trace.isOn) {
/* 278 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientMapMessage", "setLong(String,long)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setObject(String name, Object value) throws JMSException {
/* 289 */     if (Trace.isOn) {
/* 290 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientMapMessage", "setObject(String,Object)", new Object[] { name, value });
/*     */     }
/*     */     
/* 293 */     this.mapBody.put(name, value);
/* 294 */     if (Trace.isOn) {
/* 295 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientMapMessage", "setObject(String,Object)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setShort(String name, short value) throws JMSException {
/* 305 */     if (Trace.isOn) {
/* 306 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientMapMessage", "setShort(String,short)", new Object[] { name, 
/* 307 */             Short.valueOf(value) });
/*     */     }
/* 309 */     this.mapBody.put(name, Short.valueOf(value));
/* 310 */     if (Trace.isOn) {
/* 311 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientMapMessage", "setShort(String,short)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setString(String name, String value) throws JMSException {
/* 322 */     if (Trace.isOn) {
/* 323 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientMapMessage", "setString(String,String)", new Object[] { name, value });
/*     */     }
/*     */     
/* 326 */     this.mapBody.put(name, value);
/* 327 */     if (Trace.isOn) {
/* 328 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientMapMessage", "setString(String,String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProviderMessage convertIntoWMQMessage(ProviderSession session) throws JMSException {
/* 339 */     if (Trace.isOn) {
/* 340 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientMapMessage", "convertIntoWMQMessage(ProviderSession)", new Object[] { session });
/*     */     }
/*     */     
/* 343 */     WMQMapMessage wMQMapMessage = new WMQMapMessage();
/* 344 */     setJMSPropsOnProviderMessage((ProviderMessage)wMQMapMessage);
/* 345 */     setPCPropertiesOnProviderMessage((ProviderMessage)wMQMapMessage);
/*     */ 
/*     */     
/* 348 */     for (Map.Entry<String, Object> entry : this.mapBody.entrySet()) {
/* 349 */       wMQMapMessage.setObject(entry.getKey(), entry.getValue());
/*     */     }
/* 351 */     if (Trace.isOn) {
/* 352 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientMapMessage", "convertIntoWMQMessage(ProviderSession)", wMQMapMessage);
/*     */     }
/*     */     
/* 355 */     return (ProviderMessage)wMQMapMessage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProviderMessage convertIntoMQMessage(ProviderSession session) throws JMSException {
/* 363 */     if (Trace.isOn) {
/* 364 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientMapMessage", "convertIntoMQMessage(ProviderSession)", new Object[] { session });
/*     */     }
/*     */     
/* 367 */     ProviderMapMessage providerMapMessage = ((MQSession)session).createMapMessage();
/* 368 */     setJMSPropsOnProviderMessage((ProviderMessage)providerMapMessage);
/* 369 */     setPCPropertiesOnProviderMessage((ProviderMessage)providerMapMessage);
/*     */ 
/*     */     
/* 372 */     for (Map.Entry<String, Object> entry : this.mapBody.entrySet()) {
/* 373 */       ((JMSMapMessage)providerMapMessage).setObject(entry.getKey(), entry.getValue());
/*     */     }
/* 375 */     if (Trace.isOn) {
/* 376 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientMapMessage", "convertIntoMQMessage(ProviderSession)", providerMapMessage);
/*     */     }
/*     */     
/* 379 */     return (ProviderMessage)providerMapMessage;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<?, ?> getMap(boolean deepClone) throws JMSException {
/* 384 */     if (Trace.isOn) {
/* 385 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientMapMessage", "getMap(boolean)", new Object[] {
/* 386 */             Boolean.valueOf(deepClone)
/*     */           });
/*     */     }
/* 389 */     if (Trace.isOn) {
/* 390 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientMapMessage", "getMap(boolean)", null);
/*     */     }
/*     */     
/* 393 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\messages\TransientMapMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */