/*     */ package com.ibm.mq;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.Arrays;
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
/*     */ class BindingsConnectionRequestInfo
/*     */   extends MQConnectionRequestInfo
/*     */   implements Cloneable
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/BindingsConnectionRequestInfo.java";
/*     */   public String userName;
/*     */   public String password;
/*     */   public int jmqiFlags;
/*     */   
/*     */   static {
/*  57 */     if (Trace.isOn) {
/*  58 */       Trace.data("com.ibm.mq.BindingsConnectionRequestInfo", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/BindingsConnectionRequestInfo.java");
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   BindingsConnectionRequestInfo(Hashtable props) {
/*  85 */     if (Trace.isOn) {
/*  86 */       Trace.entry(this, "com.ibm.mq.BindingsConnectionRequestInfo", "<init>(Hashtable)", new Object[] {
/*  87 */             Trace.sanitizeMap(props, "password", "XXXXXXXXXXXX")
/*     */           });
/*     */     }
/*  90 */     this.hasVariablePortion = false;
/*  91 */     this.connectOptions = MQEnvironment.getIntegerProperty("connectOptions", props);
/*  92 */     this.multiThread = MQEnvironment.getStringProperty("Thread access", props);
/*  93 */     this.group = MQEnvironment.getObjectProperty("Group", props);
/*  94 */     this.threadAffinity = MQEnvironment.getObjectProperty("Thread affinity", props);
/*  95 */     this.userName = MQEnvironment.getStringProperty("userID", props);
/*  96 */     this.userName = (this.userName == null) ? "" : this.userName;
/*  97 */     this.password = MQEnvironment.getStringProperty("password", props);
/*  98 */     this.password = (this.password == null) ? "" : this.password;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 103 */     this.authenticateBindings = MQEnvironment.getObjectProperty("Bindings Authentication", props);
/* 104 */     this.connTag = (byte[])MQEnvironment.getObjectProperty("ConnTag Property", props);
/* 105 */     this.appName = MQEnvironment.getStringProperty("APPNAME", props);
/* 106 */     this.useMQCSP = (Boolean)MQEnvironment.getObjectProperty("Use MQCSP authentication", props);
/*     */     
/* 108 */     if (props != null) {
/* 109 */       Object jmqiFlagsObj = props.get("JMQI FLAGS");
/* 110 */       if (jmqiFlagsObj != null && jmqiFlagsObj instanceof Integer) {
/* 111 */         this.jmqiFlags = ((Integer)jmqiFlagsObj).intValue();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 120 */   public byte[] connTag = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String appName;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Boolean useMQCSP;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int connectOptions;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String multiThread;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object group;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object threadAffinity;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object authenticateBindings;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int fixedHashCode() {
/* 164 */     if (Trace.isOn) {
/* 165 */       Trace.entry(this, "com.ibm.mq.BindingsConnectionRequestInfo", "fixedHashCode()");
/*     */     }
/* 167 */     int out = 43 * this.connectOptions;
/* 168 */     if (this.userName != null) {
/* 169 */       out += 37 * this.userName.hashCode();
/*     */     }
/* 171 */     if (this.multiThread != null) {
/* 172 */       out += 47 * this.multiThread.hashCode();
/*     */     }
/*     */     
/* 175 */     if (this.connTag != null) {
/* 176 */       out += 59 * Arrays.hashCode(this.connTag);
/*     */     }
/*     */     
/* 179 */     out += 61 * this.jmqiFlags;
/* 180 */     if (this.appName != null) {
/* 181 */       out += 67 * this.appName.hashCode();
/*     */     }
/* 183 */     if (this.useMQCSP != null) {
/* 184 */       out += 71 * this.useMQCSP.hashCode();
/*     */     }
/*     */     
/* 187 */     if (Trace.isOn) {
/* 188 */       Trace.exit(this, "com.ibm.mq.BindingsConnectionRequestInfo", "fixedHashCode()", 
/* 189 */           Integer.valueOf(out));
/*     */     }
/* 191 */     return out;
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
/*     */   public boolean fixedEquals(Object obj) {
/* 203 */     if (Trace.isOn) {
/* 204 */       Trace.entry(this, "com.ibm.mq.BindingsConnectionRequestInfo", "fixedEquals(Object)", new Object[] { obj });
/*     */     }
/*     */     
/* 207 */     if (this == obj) {
/* 208 */       if (Trace.isOn) {
/* 209 */         Trace.exit(this, "com.ibm.mq.BindingsConnectionRequestInfo", "fixedEquals(Object)", 
/* 210 */             Boolean.valueOf(true), 1);
/*     */       }
/* 212 */       return true;
/*     */     } 
/*     */     
/* 215 */     if (obj == null) {
/* 216 */       if (Trace.isOn) {
/* 217 */         Trace.exit(this, "com.ibm.mq.BindingsConnectionRequestInfo", "fixedEquals(Object)", 
/* 218 */             Boolean.valueOf(false), 2);
/*     */       }
/* 220 */       return false;
/*     */     } 
/*     */     
/* 223 */     if (!obj.getClass().equals(getClass())) {
/* 224 */       if (Trace.isOn) {
/* 225 */         Trace.exit(this, "com.ibm.mq.BindingsConnectionRequestInfo", "fixedEquals(Object)", 
/* 226 */             Boolean.valueOf(false), 3);
/*     */       }
/* 228 */       return false;
/*     */     } 
/*     */     
/* 231 */     BindingsConnectionRequestInfo other = (BindingsConnectionRequestInfo)obj;
/*     */ 
/*     */     
/* 234 */     if (this.jmqiFlags != other.jmqiFlags) {
/* 235 */       if (Trace.isOn) {
/* 236 */         Trace.exit(this, "com.ibm.mq.BindingsConnectionRequestInfo", "fixedEquals(Object)", 
/* 237 */             Boolean.valueOf(false), 4);
/*     */       }
/* 239 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 243 */     if (!objEquals(this.group, other.group)) {
/* 244 */       if (Trace.isOn) {
/* 245 */         Trace.exit(this, "com.ibm.mq.BindingsConnectionRequestInfo", "fixedEquals(Object)", 
/* 246 */             Boolean.valueOf(false), 5);
/*     */       }
/* 248 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 253 */     if (!objEquals(this.threadAffinity, other.threadAffinity)) {
/* 254 */       if (Trace.isOn) {
/* 255 */         Trace.exit(this, "com.ibm.mq.BindingsConnectionRequestInfo", "fixedEquals(Object)", 
/* 256 */             Boolean.valueOf(false), 6);
/*     */       }
/* 258 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 262 */     if (!objEquals(this.multiThread, other.multiThread)) {
/* 263 */       if (Trace.isOn) {
/* 264 */         Trace.exit(this, "com.ibm.mq.BindingsConnectionRequestInfo", "fixedEquals(Object)", 
/* 265 */             Boolean.valueOf(false), 7);
/*     */       }
/* 267 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 271 */     if (!objEquals(this.userName, other.userName)) {
/* 272 */       if (Trace.isOn) {
/* 273 */         Trace.exit(this, "com.ibm.mq.BindingsConnectionRequestInfo", "fixedEquals(Object)", 
/* 274 */             Boolean.valueOf(false), 8);
/*     */       }
/* 276 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 280 */     if (!objEquals(this.password, other.password)) {
/* 281 */       if (Trace.isOn) {
/* 282 */         Trace.exit(this, "com.ibm.mq.BindingsConnectionRequestInfo", "fixedEquals(Object)", 
/* 283 */             Boolean.valueOf(false), 9);
/*     */       }
/* 285 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 289 */     if (this.connectOptions != other.connectOptions) {
/* 290 */       if (Trace.isOn) {
/* 291 */         Trace.exit(this, "com.ibm.mq.BindingsConnectionRequestInfo", "fixedEquals(Object)", 
/* 292 */             Boolean.valueOf(false), 10);
/*     */       }
/* 294 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 298 */     if (!Arrays.equals(this.connTag, other.connTag)) {
/* 299 */       if (Trace.isOn) {
/* 300 */         Trace.exit(this, "com.ibm.mq.BindingsConnectionRequestInfo", "fixedEquals(Object)", 
/* 301 */             Boolean.valueOf(false), 11);
/*     */       }
/* 303 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 307 */     if (!objEquals(this.appName, other.appName)) {
/* 308 */       if (Trace.isOn) {
/* 309 */         Trace.exit(this, "com.ibm.mq.BindingsConnectionRequestInfo", "fixedEquals(Object)", 
/* 310 */             Boolean.valueOf(false), 12);
/*     */       }
/* 312 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 316 */     if (!objEquals(this.useMQCSP, other.useMQCSP)) {
/* 317 */       if (Trace.isOn) {
/* 318 */         Trace.exit(this, "com.ibm.mq.BindingsConnectionRequestInfo", "fixedEquals(Object)", 
/* 319 */             Boolean.valueOf(false), 13);
/*     */       }
/* 321 */       return false;
/*     */     } 
/*     */     
/* 324 */     if (Trace.isOn) {
/* 325 */       Trace.exit(this, "com.ibm.mq.BindingsConnectionRequestInfo", "fixedEquals(Object)", 
/* 326 */           Boolean.valueOf(true), 13);
/*     */     }
/* 328 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\BindingsConnectionRequestInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */