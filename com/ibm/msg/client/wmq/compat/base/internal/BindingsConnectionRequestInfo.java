/*     */ package com.ibm.msg.client.wmq.compat.base.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class BindingsConnectionRequestInfo
/*     */   extends MQConnectionRequestInfo
/*     */   implements Cloneable
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/BindingsConnectionRequestInfo.java";
/*     */   public String appName;
/*     */   public Object asyncCmt;
/*     */   public Object authenticateBindings;
/*     */   public int connectOptions;
/*     */   
/*     */   static {
/*  55 */     if (Trace.isOn) {
/*  56 */       Trace.data("com.ibm.msg.client.wmq.compat.base.internal.BindingsConnectionRequestInfo", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/BindingsConnectionRequestInfo.java");
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
/*  75 */   public byte[] connTag = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object group;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object inheritTx;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String multiThread;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String password;
/*     */ 
/*     */ 
/*     */   
/*     */   public String spi;
/*     */ 
/*     */ 
/*     */   
/*     */   public Object threadAffinity;
/*     */ 
/*     */ 
/*     */   
/*     */   public String userName;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   BindingsConnectionRequestInfo(Map<String, Object> mqProperties) {
/* 113 */     if (Trace.isOn) {
/* 114 */       if (mqProperties.containsKey("password")) {
/* 115 */         Map<String, Object> propsNotPasswd = new HashMap<>(mqProperties);
/* 116 */         propsNotPasswd.put("password", "********");
/* 117 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.BindingsConnectionRequestInfo", "<init>(Map)", new Object[] { propsNotPasswd });
/*     */       }
/*     */       else {
/*     */         
/* 121 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.BindingsConnectionRequestInfo", "<init>(Map)", new Object[] { mqProperties });
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 127 */     this.hasVariablePortion = false;
/*     */     
/* 129 */     this.connectOptions = MQEnvironment.getIntegerProperty("connectOptions", mqProperties);
/* 130 */     this.multiThread = MQEnvironment.getStringProperty("Thread access", mqProperties);
/* 131 */     this.group = MQEnvironment.getObjectProperty("Group", mqProperties);
/* 132 */     this.threadAffinity = MQEnvironment.getObjectProperty("Thread affinity", mqProperties);
/* 133 */     this.userName = MQEnvironment.getStringProperty("userID", mqProperties);
/* 134 */     this.userName = (this.userName == null) ? "" : this.userName;
/*     */     
/* 136 */     this.password = MQEnvironment.getStringProperty("password", mqProperties);
/* 137 */     this.password = (this.password == null) ? "" : this.password;
/*     */     
/* 139 */     this.spi = MQEnvironment.getStringProperty("SPI", mqProperties);
/*     */ 
/*     */     
/* 142 */     this.inheritTx = MQEnvironment.getObjectProperty("SPI_INHERIT_TX", mqProperties);
/* 143 */     this.asyncCmt = MQEnvironment.getObjectProperty("SPI_ASYNC_CMIT", mqProperties);
/*     */     
/* 145 */     if (Trace.isOn) {
/* 146 */       Trace.traceData(this, "inheritTx = " + this.inheritTx, null);
/* 147 */       Trace.traceData(this, "asyncCmt = " + this.asyncCmt, null);
/*     */     } 
/*     */     
/* 150 */     this.authenticateBindings = MQEnvironment.getObjectProperty("Bindings Authentication", mqProperties);
/* 151 */     this.connTag = (byte[])MQEnvironment.getObjectProperty("ConnTag Property", mqProperties);
/*     */     
/* 153 */     Object object = mqProperties.get("APPNAME");
/* 154 */     if (object != null && object instanceof String) {
/* 155 */       this.appName = (String)object;
/*     */     }
/*     */     
/* 158 */     if (Trace.isOn) {
/* 159 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.BindingsConnectionRequestInfo", "<init>(Map)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean fixedEquals(Object obj) {
/* 168 */     if (Trace.isOn) {
/* 169 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.BindingsConnectionRequestInfo", "fixedEquals(Object)", new Object[] { obj });
/*     */     }
/*     */ 
/*     */     
/* 173 */     if (this == obj) {
/* 174 */       if (Trace.isOn) {
/* 175 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.BindingsConnectionRequestInfo", "fixedEquals(Object)", 
/*     */             
/* 177 */             Boolean.valueOf(true), 1);
/*     */       }
/* 179 */       return true;
/*     */     } 
/*     */     
/* 182 */     if (obj == null) {
/* 183 */       if (Trace.isOn) {
/* 184 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.BindingsConnectionRequestInfo", "fixedEquals(Object)", 
/*     */             
/* 186 */             Boolean.valueOf(false), 2);
/*     */       }
/* 188 */       return false;
/*     */     } 
/*     */     
/* 191 */     if (!obj.getClass().equals(getClass())) {
/* 192 */       if (Trace.isOn) {
/* 193 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.BindingsConnectionRequestInfo", "fixedEquals(Object)", 
/*     */             
/* 195 */             Boolean.valueOf(false), 3);
/*     */       }
/* 197 */       return false;
/*     */     } 
/*     */     
/* 200 */     BindingsConnectionRequestInfo other = (BindingsConnectionRequestInfo)obj;
/*     */     
/* 202 */     if (!objEquals(this.group, other.group)) {
/* 203 */       if (Trace.isOn) {
/* 204 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.BindingsConnectionRequestInfo", "fixedEquals(Object)", 
/* 205 */             Boolean.valueOf(false), 5);
/*     */       }
/* 207 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 211 */     if (!objEquals(this.threadAffinity, other.threadAffinity)) {
/* 212 */       if (Trace.isOn) {
/* 213 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.BindingsConnectionRequestInfo", "fixedEquals(Object)", 
/* 214 */             Boolean.valueOf(false), 6);
/*     */       }
/* 216 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 220 */     if (!objEquals(this.multiThread, other.multiThread)) {
/* 221 */       if (Trace.isOn) {
/* 222 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.BindingsConnectionRequestInfo", "fixedEquals(Object)", 
/* 223 */             Boolean.valueOf(false), 7);
/*     */       }
/* 225 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 229 */     if (!objEquals(this.userName, other.userName)) {
/* 230 */       if (Trace.isOn) {
/* 231 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.BindingsConnectionRequestInfo", "fixedEquals(Object)", 
/* 232 */             Boolean.valueOf(false), 8);
/*     */       }
/* 234 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 238 */     if (!objEquals(this.password, other.password)) {
/* 239 */       if (Trace.isOn) {
/* 240 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.BindingsConnectionRequestInfo", "fixedEquals(Object)", 
/* 241 */             Boolean.valueOf(false), 9);
/*     */       }
/* 243 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 247 */     if (this.connectOptions != other.connectOptions) {
/* 248 */       if (Trace.isOn) {
/* 249 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.BindingsConnectionRequestInfo", "fixedEquals(Object)", 
/* 250 */             Boolean.valueOf(false), 10);
/*     */       }
/* 252 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 256 */     if (!Arrays.equals(this.connTag, other.connTag)) {
/* 257 */       if (Trace.isOn) {
/* 258 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.BindingsConnectionRequestInfo", "fixedEquals(Object)", 
/* 259 */             Boolean.valueOf(false), 11);
/*     */       }
/* 261 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 265 */     if (!objEquals(this.spi, other.spi)) {
/* 266 */       if (Trace.isOn) {
/* 267 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.BindingsConnectionRequestInfo", "fixedEquals(Object)", 
/* 268 */             Boolean.valueOf(false), 12);
/*     */       }
/* 270 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 274 */     if (!objEquals(this.inheritTx, other.inheritTx)) {
/* 275 */       if (Trace.isOn) {
/* 276 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.BindingsConnectionRequestInfo", "fixedEquals(Object)", 
/* 277 */             Boolean.valueOf(false), 13);
/*     */       }
/* 279 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 283 */     if (!objEquals(this.asyncCmt, other.asyncCmt)) {
/* 284 */       if (Trace.isOn) {
/* 285 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.BindingsConnectionRequestInfo", "fixedEquals(Object)", 
/* 286 */             Boolean.valueOf(false), 14);
/*     */       }
/* 288 */       return false;
/*     */     } 
/*     */     
/* 291 */     if (Trace.isOn) {
/* 292 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.BindingsConnectionRequestInfo", "fixedEquals(Object)", 
/* 293 */           Boolean.valueOf(true), 15);
/*     */     }
/* 295 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int fixedHashCode() {
/* 300 */     if (Trace.isOn) {
/* 301 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.BindingsConnectionRequestInfo", "fixedHashCode()");
/*     */     }
/*     */ 
/*     */     
/* 305 */     int out = 43 * this.connectOptions;
/* 306 */     if (this.userName != null) {
/* 307 */       out += 37 * this.userName.hashCode();
/*     */     }
/* 309 */     if (this.multiThread != null) {
/* 310 */       out += 47 * this.multiThread.hashCode();
/*     */     }
/* 312 */     if (this.spi != null) {
/* 313 */       out += 53 * this.spi.hashCode();
/*     */     }
/* 315 */     if (this.connTag != null) {
/* 316 */       out += 59 * Arrays.hashCode(this.connTag);
/*     */     }
/* 318 */     if (Trace.isOn) {
/* 319 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.BindingsConnectionRequestInfo", "fixedHashCode()", 
/*     */           
/* 321 */           Integer.valueOf(out));
/*     */     }
/* 323 */     return out;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\base\internal\BindingsConnectionRequestInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */