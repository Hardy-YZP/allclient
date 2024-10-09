/*     */ package com.ibm.mq.jmqi.system.internal;
/*     */ 
/*     */ import com.ibm.mq.exits.MQCD;
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.internal.JmqiTools;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.net.URL;
/*     */ import java.util.Iterator;
/*     */ import org.json.JSONArray;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ import org.json.JSONTokener;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class JSONCCDTFileParser
/*     */   extends CCDTFileParser
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/internal/JSONCCDTFileParser.java";
/*  60 */   private static final Class<JSONCCDTFileParser> cclass = JSONCCDTFileParser.class;
/*     */   
/*     */   static {
/*  63 */     if (Trace.isOn) {
/*  64 */       Trace.data(cclass.getName(), "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/internal/JSONCCDTFileParser.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   JSONCCDTFileParser(JmqiEnvironment env, URL url, InputStream ccdtInputStream) {
/*  74 */     super(env, url, ccdtInputStream);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void parse() throws JmqiException {
/*  82 */     String methodSignature = "parse()";
/*  83 */     if (Trace.isOn) {
/*  84 */       Trace.entry(this, cclass.getName(), "parse()");
/*     */     }
/*     */     
/*  87 */     if (this.ccdtInputStream == null) {
/*  88 */       this.ccdtInputStream = open(this.env, this.ccdtUrl);
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*  93 */       InputStreamReader reader = new InputStreamReader(this.ccdtInputStream);
/*  94 */       JSONObject ccdt = new JSONObject(new JSONTokener(reader));
/*     */       
/*  96 */       Iterator<?> it = ccdt.keys();
/*  97 */       while (it.hasNext()) {
/*  98 */         JSONArray channels; int i; String key = (String)it.next();
/*  99 */         switch (key) {
/*     */           case "channel":
/* 101 */             channels = ccdt.getJSONArray("channel");
/* 102 */             if (channels.length() == 0) {
/* 103 */               throw JSONExceptionFactory.NoChannelsDefined();
/*     */             }
/* 105 */             for (i = 0; i < channels.length(); i++) {
/* 106 */               JSONObject channel = channels.getJSONObject(i);
/* 107 */               processJSONChannelDefinition(channel);
/*     */             } 
/*     */             continue;
/*     */         } 
/* 111 */         throw JSONExceptionFactory.UnsupportedObject(key);
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 116 */     catch (JSONFileFormatException e) {
/* 117 */       if (Trace.isOn) {
/* 118 */         Trace.catchBlock(this, cclass.getName(), "parse()", (Throwable)e);
/*     */       }
/* 120 */       JmqiException exception = new JmqiException(this.env, 9695, new String[] { null, null, this.ccdtUrl.toString(), e.getAttrib(), JmqiTools.getExSumm((Throwable)e) }, 2, 2278, (Throwable)e);
/*     */       
/* 122 */       if (Trace.isOn) {
/* 123 */         Trace.throwing(this, cclass.getName(), "parse()", (Throwable)exception, 1);
/*     */       }
/* 125 */       throw exception;
/*     */     
/*     */     }
/* 128 */     catch (JSONAttributeException e) {
/* 129 */       if (Trace.isOn) {
/* 130 */         Trace.catchBlock(this, cclass.getName(), "parse()", (Throwable)e);
/*     */       }
/* 132 */       JmqiException exception = new JmqiException(this.env, 9696, new String[] { null, null, this.ccdtUrl.toString(), e.getAttrib(), e.getBadValue() }, 2, 2278, (Throwable)e);
/*     */       
/* 134 */       if (Trace.isOn) {
/* 135 */         Trace.throwing(this, cclass.getName(), "parse()", (Throwable)exception, 1);
/*     */       }
/* 137 */       throw exception;
/*     */     
/*     */     }
/* 140 */     catch (JSONException e) {
/* 141 */       if (Trace.isOn) {
/* 142 */         Trace.catchBlock(this, cclass.getName(), "parse()", (Throwable)e);
/*     */       }
/* 144 */       JmqiException exception = new JmqiException(this.env, 9695, new String[] { null, null, this.ccdtUrl.toString(), null, JmqiTools.getExSumm((Throwable)e) }, 2, 2278, (Throwable)e);
/*     */       
/* 146 */       if (Trace.isOn) {
/* 147 */         Trace.throwing(this, cclass.getName(), "parse()", (Throwable)exception, 1);
/*     */       }
/* 149 */       throw exception;
/*     */     }
/*     */     finally {
/*     */       
/* 153 */       if (Trace.isOn) {
/* 154 */         Trace.finallyBlock(this, cclass.getName(), "parse()");
/*     */       }
/*     */       try {
/* 157 */         this.ccdtInputStream.close();
/* 158 */         this.ccdtInputStream = null;
/*     */       }
/* 160 */       catch (IOException e) {
/* 161 */         if (Trace.isOn) {
/* 162 */           Trace.catchBlock(this, cclass.getName(), "parse()", e, 3);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 167 */     if (Trace.isOn) {
/* 168 */       Trace.exit(this, cclass.getName(), "parse()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void processJSONChannelDefinition(JSONObject channel) throws JSONException {
/* 179 */     String methodSignature = "processJSONChannelDefinition(JSONObject)";
/* 180 */     if (Trace.isOn) {
/* 181 */       Trace.entry(this, cclass.getName(), "processJSONChannelDefinition(JSONObject)", new Object[] { channel });
/*     */     }
/*     */ 
/*     */     
/* 185 */     String channelName = null;
/* 186 */     String channelType = null;
/*     */     try {
/* 188 */       channelName = channel.getString("name");
/* 189 */       channelType = channel.getString("type");
/*     */     }
/* 191 */     catch (JSONException jSONException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 196 */     if (channelName == null) {
/* 197 */       throw JSONExceptionFactory.MissingChannelDefinitionKey("name");
/*     */     }
/*     */     
/* 200 */     if (channelType == null) {
/* 201 */       throw JSONExceptionFactory.MissingChannelDefinitionKey("type");
/*     */     }
/*     */     
/* 204 */     if (channelName.length() > 20) {
/* 205 */       throw JSONExceptionFactory.InvalidValue("name", channelName);
/*     */     }
/* 207 */     if (!channelType.equals("clientConnection")) {
/* 208 */       throw JSONExceptionFactory.UnsupportedChannelDefinitionType(channelType);
/*     */     }
/*     */     
/* 211 */     MQCD channelDefinition = this.env.newMQCD();
/* 212 */     channelDefinition.setChannelName(channelName);
/* 213 */     channelDefinition.setChannelType(6);
/*     */ 
/*     */     
/* 216 */     Iterator<?> it = channel.keys();
/* 217 */     while (it.hasNext()) {
/* 218 */       String key = (String)it.next();
/* 219 */       if (key.equals("name") || key.equals("type")) {
/*     */         continue;
/*     */       }
/*     */       
/* 223 */       ChannelDefinitionAttributes.processChannelDefinition(channelDefinition, key, channel);
/*     */     } 
/*     */ 
/*     */     
/* 227 */     if (Trace.isOn) {
/* 228 */       Trace.data(this, cclass.getName(), "processJSONChannelDefinition(JSONObject)", "CHLNAME('" + channelDefinition
/* 229 */           .getChannelName() + "') CHLTYPE(CLNTCONN) QMNAME('" + channelDefinition
/* 230 */           .getQMgrName() + "') CONNAME('" + channelDefinition.getConnectionName() + "')");
/*     */     }
/*     */ 
/*     */     
/* 234 */     if (channelDefinition.getConnectionName() != null && channelDefinition
/* 235 */       .getConnectionName().indexOf(",") != -1) {
/* 236 */       String[] connectionNames = channelDefinition.getConnectionName().split(",");
/* 237 */       for (int i = 0; i < connectionNames.length; i++) {
/*     */         try {
/* 239 */           MQCDWrapper subDefinition = new MQCDWrapper((MQCD)channelDefinition.clone(), this.nextSeq++);
/* 240 */           subDefinition.mqcd.setConnectionName(connectionNames[i]);
/* 241 */           this.channelDefinitions.add(subDefinition);
/*     */         }
/* 243 */         catch (CloneNotSupportedException e) {
/* 244 */           if (Trace.isOn) {
/* 245 */             Trace.catchBlock(this, cclass.getName(), "processJSONChannelDefinition(JSONObject)", e, 1);
/*     */           }
/*     */         }
/*     */       
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 253 */       this.channelDefinitions.add(new MQCDWrapper(channelDefinition, this.nextSeq++));
/*     */     } 
/*     */     
/* 256 */     if (Trace.isOn)
/* 257 */       Trace.exit(this, cclass.getName(), "processJSONChannelDefinition(JSONObject)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\internal\JSONCCDTFileParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */