/*     */ package com.ibm.mq;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.mq.jmqi.handles.Phconn;
/*     */ import com.ibm.mq.jmqi.handles.Phobj;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class MQManagedConnectionMetaData
/*     */   extends JmqiObject
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQManagedConnectionMetaData.java";
/*     */   private MQManagedConnectionJ11 mancon;
/*     */   private static final String NLS_PRODUCT_NAME = "MID_ProductName";
/*     */   private static final String NLS_COMMAND_LEVEL = "MID_MngCon_CmdLvl";
/*     */   
/*     */   static {
/*  49 */     if (Trace.isOn) {
/*  50 */       Trace.data("com.ibm.mq.MQManagedConnectionMetaData", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQManagedConnectionMetaData.java");
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
/*  68 */   private final String productName = MQException.getNLSMsg("MID_ProductName");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  74 */   private int commandLevel = 0;
/*  75 */   private String productVersion = null;
/*  76 */   private Object productVersionLock = new Object();
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int maxConnections = 0;
/*     */ 
/*     */ 
/*     */   
/*  84 */   private String userName = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   MQManagedConnectionMetaData(MQManagedConnectionJ11 mancon) {
/*  92 */     super(MQSESSION.getJmqiEnv());
/*  93 */     if (Trace.isOn) {
/*  94 */       Trace.entry(this, "com.ibm.mq.MQManagedConnectionMetaData", "<init>(MQManagedConnectionJ11)", new Object[] { mancon });
/*     */     }
/*     */     
/*  97 */     this.mancon = mancon;
/*  98 */     this.userName = mancon.getStringProperty("userID");
/*  99 */     if (this.userName == null) {
/* 100 */       this.userName = "";
/*     */     }
/* 102 */     if (Trace.isOn) {
/* 103 */       Trace.data(this, "<init>(MQManagedConnectionJ11)", "userName = ", this.userName);
/*     */     }
/*     */     
/* 106 */     if (Trace.isOn) {
/* 107 */       Trace.exit(this, "com.ibm.mq.MQManagedConnectionMetaData", "<init>(MQManagedConnectionJ11)");
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
/*     */   public String getEISProductName() throws MQException {
/* 121 */     if (Trace.isOn) {
/* 122 */       Trace.entry(this, "com.ibm.mq.MQManagedConnectionMetaData", "getEISProductName()");
/*     */     }
/* 124 */     if (this.mancon.isConnected()) {
/*     */       
/* 126 */       if (Trace.isOn) {
/* 127 */         Trace.exit(this, "com.ibm.mq.MQManagedConnectionMetaData", "getEISProductName()", this.productName);
/*     */       }
/*     */       
/* 130 */       return this.productName;
/*     */     } 
/* 132 */     MQException traceRet1 = new MQException(2, 6124, this, "MQJI040");
/* 133 */     if (Trace.isOn) {
/* 134 */       Trace.throwing(this, "com.ibm.mq.MQManagedConnectionMetaData", "getEISProductName()", traceRet1);
/*     */     }
/*     */     
/* 137 */     throw traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEISProductVersion() throws MQResourceException {
/* 148 */     if (!this.mancon.isConnected()) {
/* 149 */       MQResourceException traceRet1 = new MQResourceException(2, 6124, this, "MQJI040");
/* 150 */       if (Trace.isOn) {
/* 151 */         Trace.throwing(this, "com.ibm.mq.MQManagedConnectionMetaData", "getEISProductVersion()", traceRet1);
/*     */       }
/*     */       
/* 154 */       throw traceRet1;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 160 */     if (this.productVersion == null) {
/*     */       
/* 162 */       int level = getCommandLevel();
/*     */       
/* 164 */       this.productVersion = MQException.getNLSMsg("MID_MngCon_CmdLvl", "" + level);
/*     */     } 
/*     */     
/* 167 */     if (Trace.isOn) {
/* 168 */       Trace.data(this, "com.ibm.mq.MQManagedConnectionMetaData", "getEISProductVersion()", "getter", this.productVersion);
/*     */     }
/*     */     
/* 171 */     return this.productVersion;
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
/*     */   public int getMaxConnections() throws MQException {
/* 184 */     if (Trace.isOn) {
/* 185 */       Trace.entry(this, "com.ibm.mq.MQManagedConnectionMetaData", "getMaxConnections()");
/*     */     }
/* 187 */     if (this.mancon.isConnected()) {
/*     */       
/* 189 */       if (Trace.isOn) {
/* 190 */         Trace.exit(this, "com.ibm.mq.MQManagedConnectionMetaData", "getMaxConnections()", 
/* 191 */             Integer.valueOf(0));
/*     */       }
/* 193 */       return 0;
/*     */     } 
/* 195 */     MQException traceRet1 = new MQException(2, 6124, this, "MQJI040");
/* 196 */     if (Trace.isOn) {
/* 197 */       Trace.throwing(this, "com.ibm.mq.MQManagedConnectionMetaData", "getMaxConnections()", traceRet1);
/*     */     }
/*     */     
/* 200 */     throw traceRet1;
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
/*     */   public String getUserName() throws MQException {
/* 212 */     if (Trace.isOn) {
/* 213 */       Trace.entry(this, "com.ibm.mq.MQManagedConnectionMetaData", "getUserName()");
/*     */     }
/* 215 */     if (this.mancon.isConnected()) {
/* 216 */       if (Trace.isOn) {
/* 217 */         Trace.exit(this, "com.ibm.mq.MQManagedConnectionMetaData", "getUserName()", this.userName);
/*     */       }
/* 219 */       return this.userName;
/*     */     } 
/* 221 */     MQException traceRet1 = new MQException(2, 6124, this, "MQJI040");
/* 222 */     if (Trace.isOn) {
/* 223 */       Trace.throwing(this, "com.ibm.mq.MQManagedConnectionMetaData", "getUserName()", traceRet1);
/*     */     }
/* 225 */     throw traceRet1;
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
/*     */   public int getCommandLevel() throws MQResourceException {
/* 237 */     if (!this.mancon.isConnected()) {
/* 238 */       MQResourceException traceRet1 = new MQResourceException(2, 6124, this, "MQJI040");
/* 239 */       if (Trace.isOn) {
/* 240 */         Trace.throwing(this, "com.ibm.mq.MQManagedConnectionMetaData", "getCommandLevel()", traceRet1, 1);
/*     */       }
/*     */       
/* 243 */       throw traceRet1;
/*     */     } 
/*     */     
/* 246 */     int level = 0;
/* 247 */     synchronized (this.productVersionLock) {
/*     */ 
/*     */ 
/*     */       
/* 251 */       if (this.commandLevel == 0) {
/*     */         
/* 253 */         MQSESSION session = this.mancon.getMQSESSION();
/* 254 */         Phconn hConn = this.mancon.getHConn();
/* 255 */         Phobj hObj = MQSESSION.getJmqiEnv().newPhobj();
/* 256 */         Pint compcode = new Pint();
/* 257 */         Pint reason = new Pint();
/*     */         
/* 259 */         if (Trace.isOn) {
/* 260 */           Trace.data(this, "getCommandLevel()", "Determining Queue Manager command level", "");
/*     */         }
/*     */ 
/*     */         
/* 264 */         MQOD objDesc = new MQOD();
/* 265 */         objDesc.ObjectType = 5;
/* 266 */         session.MQOPEN(hConn.getHconn(), objDesc, 32, hObj, compcode, reason);
/* 267 */         if (compcode.x != 0) {
/* 268 */           MQException mqe = new MQException(compcode.x, reason.x, this, session.getLastJmqiException());
/* 269 */           MQResourceException re = ReasonCodeInfo.getResourceException(mqe);
/* 270 */           if (Trace.isOn) {
/* 271 */             Trace.throwing(this, "com.ibm.mq.MQManagedConnectionMetaData", "getCommandLevel()", re, 2);
/*     */           }
/*     */           
/* 274 */           throw re;
/*     */         } 
/*     */ 
/*     */         
/* 278 */         int[] selectors = new int[1];
/* 279 */         selectors[0] = 31;
/* 280 */         int[] intattr = new int[1];
/* 281 */         session.MQINQ(hConn.getHconn(), hObj.getHobj(), 1, selectors, 1, intattr, 0, null, compcode, reason);
/* 282 */         if (compcode.x != 0) {
/* 283 */           MQException mqe = new MQException(compcode.x, reason.x, this, session.getLastJmqiException());
/* 284 */           MQResourceException re = ReasonCodeInfo.getResourceException(mqe);
/*     */ 
/*     */           
/* 287 */           session.MQCLOSE(hConn.getHconn(), hObj, 0, compcode, reason);
/*     */           
/* 289 */           if (Trace.isOn) {
/* 290 */             Trace.throwing(this, "com.ibm.mq.MQManagedConnectionMetaData", "getCommandLevel()", re, 3);
/*     */           }
/*     */           
/* 293 */           throw re;
/*     */         } 
/* 295 */         level = intattr[0];
/*     */ 
/*     */         
/* 298 */         session.MQCLOSE(hConn.getHconn(), hObj, 0, compcode, reason);
/* 299 */         if (compcode.x != 0) {
/* 300 */           MQException mqe = new MQException(compcode.x, reason.x, this, session.getLastJmqiException());
/* 301 */           MQResourceException re = ReasonCodeInfo.getResourceException(mqe);
/*     */           
/* 303 */           if (Trace.isOn) {
/* 304 */             Trace.throwing(this, "com.ibm.mq.MQManagedConnectionMetaData", "getCommandLevel()", re, 4);
/*     */           }
/*     */           
/* 307 */           throw re;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 313 */     this.commandLevel = level;
/* 314 */     if (Trace.isOn) {
/* 315 */       Trace.data(this, "com.ibm.mq.MQManagedConnectionMetaData", "getCommandLevel()", "getter", 
/* 316 */           Integer.valueOf(level));
/*     */     }
/* 318 */     return level;
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
/*     */   public String toString() {
/* 332 */     if (Trace.isOn) {
/* 333 */       Trace.entry(this, "com.ibm.mq.MQManagedConnectionMetaData", "toString()");
/*     */     }
/* 335 */     String buffer = "[commandLevel = " + this.commandLevel + ", ";
/* 336 */     buffer = buffer + "manCon = " + this.mancon + ", ";
/* 337 */     buffer = buffer + "maxConnections = " + Character.MIN_VALUE + ", ";
/* 338 */     buffer = buffer + "productName = " + this.productName + ", ";
/* 339 */     buffer = buffer + "productVersion = " + this.productVersion + ", ";
/* 340 */     buffer = buffer + "productVersionLock = " + this.productVersionLock + ", ";
/* 341 */     buffer = buffer + "userName = " + this.userName + "]";
/*     */     
/* 343 */     if (Trace.isOn) {
/* 344 */       Trace.exit(this, "com.ibm.mq.MQManagedConnectionMetaData", "toString()", buffer);
/*     */     }
/* 346 */     return buffer;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQManagedConnectionMetaData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */