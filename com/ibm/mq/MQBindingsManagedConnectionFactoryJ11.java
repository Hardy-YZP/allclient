/*     */ package com.ibm.mq;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.IOException;
/*     */ import java.io.NotSerializableException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.util.Enumeration;
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
/*     */ class MQBindingsManagedConnectionFactoryJ11
/*     */   extends JmqiObject
/*     */   implements MQManagedConnectionFactory
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQBindingsManagedConnectionFactoryJ11.java";
/*     */   static final long serialVersionUID = -3221376675852376439L;
/*     */   private String fieldQMGR;
/*     */   
/*     */   static {
/*  56 */     if (Trace.isOn) {
/*  57 */       Trace.data("com.ibm.mq.MQBindingsManagedConnectionFactoryJ11", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQBindingsManagedConnectionFactoryJ11.java");
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
/*     */   MQBindingsManagedConnectionFactoryJ11(String qmgr, Hashtable props) {
/*  78 */     super(MQSESSION.getJmqiEnv());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  93 */     this.fieldQMGR = "";
/*     */     if (Trace.isOn)
/*     */       Trace.entry(this, "com.ibm.mq.MQBindingsManagedConnectionFactoryJ11", "<init>(String,Hashtable)", new Object[] { qmgr, Trace.sanitizeMap(props, "password", "XXXXXXXXXXXX") }); 
/*     */     setQMGR(qmgr);
/*     */     if (Trace.isOn)
/*     */       Trace.exit(this, "com.ibm.mq.MQBindingsManagedConnectionFactoryJ11", "<init>(String,Hashtable)"); 
/*     */   }
/*     */   public void setQMGR(String value) {
/* 101 */     if (Trace.isOn) {
/* 102 */       Trace.data(this, "com.ibm.mq.MQBindingsManagedConnectionFactoryJ11", "setQMGR(String)", "setter", value);
/*     */     }
/*     */     
/* 105 */     this.fieldQMGR = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getQMGR() {
/* 115 */     if (Trace.isOn) {
/* 116 */       Trace.data(this, "com.ibm.mq.MQBindingsManagedConnectionFactoryJ11", "getQMGR()", "getter", this.fieldQMGR);
/*     */     }
/*     */     
/* 119 */     return this.fieldQMGR;
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
/*     */   MQManagedConnectionJ11 _createManagedConnection(MQConnectionRequestInfo cxRequestInfo, boolean mode) throws MQResourceException {
/*     */     MQManagedConnectionJ11 mancon;
/* 133 */     if (Trace.isOn) {
/* 134 */       Trace.entry(this, "com.ibm.mq.MQBindingsManagedConnectionFactoryJ11", "_createManagedConnection(MQConnectionRequestInfo,boolean)", new Object[] { cxRequestInfo, 
/*     */             
/* 136 */             Boolean.valueOf(mode) });
/*     */     }
/*     */     
/* 139 */     if (!(cxRequestInfo instanceof BindingsConnectionRequestInfo)) {
/* 140 */       MQResourceException traceRet1 = new MQResourceException(2, 2043, this, "MQJI039");
/*     */       
/* 142 */       if (Trace.isOn) {
/* 143 */         Trace.throwing(this, "com.ibm.mq.MQBindingsManagedConnectionFactoryJ11", "_createManagedConnection(MQConnectionRequestInfo,boolean)", traceRet1, 1);
/*     */       }
/*     */       
/* 146 */       throw traceRet1;
/*     */     } 
/*     */     
/* 149 */     BindingsConnectionRequestInfo bCxReqInf = (BindingsConnectionRequestInfo)cxRequestInfo;
/*     */ 
/*     */     
/* 152 */     Hashtable<String, Object> properties = new Hashtable<>();
/* 153 */     properties.put("transport", "MQSeries Bindings");
/* 154 */     properties.put("connectOptions", Integer.valueOf(bCxReqInf.connectOptions));
/* 155 */     if (bCxReqInf.multiThread != null) {
/* 156 */       properties.put("Thread access", bCxReqInf.multiThread);
/*     */     }
/*     */     
/* 159 */     if (bCxReqInf.threadAffinity != null) {
/* 160 */       properties.put("Thread affinity", bCxReqInf.threadAffinity);
/*     */     }
/*     */ 
/*     */     
/* 164 */     if (bCxReqInf.userName != null) {
/* 165 */       properties.put("userID", bCxReqInf.userName);
/*     */     }
/* 167 */     if (bCxReqInf.password != null) {
/* 168 */       properties.put("password", bCxReqInf.password);
/*     */     }
/*     */     
/* 171 */     if (bCxReqInf.authenticateBindings != null) {
/* 172 */       properties.put("Bindings Authentication", bCxReqInf.authenticateBindings);
/*     */     }
/*     */     
/* 175 */     if (bCxReqInf.connTag != null) {
/* 176 */       properties.put("ConnTag Property", bCxReqInf.connTag);
/*     */     }
/*     */     
/* 179 */     if (bCxReqInf.appName != null) {
/* 180 */       properties.put("APPNAME", bCxReqInf.appName);
/*     */     }
/*     */     
/* 183 */     if (bCxReqInf.useMQCSP != null) {
/* 184 */       properties.put("Use MQCSP authentication", bCxReqInf.useMQCSP);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 190 */       mancon = new MQManagedConnectionJ11(this.fieldQMGR, properties, cxRequestInfo, this);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 197 */       Pint pCompCode = new Pint();
/* 198 */       Pint pReason = new Pint();
/*     */       
/* 200 */       mancon.authenticate(pCompCode, pReason);
/*     */     }
/* 202 */     catch (MQException mqe) {
/* 203 */       if (Trace.isOn) {
/* 204 */         Trace.catchBlock(this, "com.ibm.mq.MQBindingsManagedConnectionFactoryJ11", "_createManagedConnection(MQConnectionRequestInfo,boolean)", mqe);
/*     */       }
/*     */       
/* 207 */       MQResourceException re = ReasonCodeInfo.getResourceException(mqe);
/* 208 */       if (Trace.isOn) {
/* 209 */         Trace.throwing(this, "com.ibm.mq.MQBindingsManagedConnectionFactoryJ11", "_createManagedConnection(MQConnectionRequestInfo,boolean)", re, 2);
/*     */       }
/*     */       
/* 212 */       throw re;
/*     */     } 
/* 214 */     if (Trace.isOn) {
/* 215 */       Trace.exit(this, "com.ibm.mq.MQBindingsManagedConnectionFactoryJ11", "_createManagedConnection(MQConnectionRequestInfo,boolean)", mancon);
/*     */     }
/*     */     
/* 218 */     return mancon;
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
/*     */   public MQManagedConnectionJ11 createManagedConnection(MQConnectionRequestInfo cxRequestInfo) throws MQResourceException {
/* 231 */     if (Trace.isOn) {
/* 232 */       Trace.entry(this, "com.ibm.mq.MQBindingsManagedConnectionFactoryJ11", "createManagedConnection(MQConnectionRequestInfo)", new Object[] { cxRequestInfo });
/*     */     }
/*     */ 
/*     */     
/* 236 */     MQManagedConnectionJ11 traceRet1 = _createManagedConnection(cxRequestInfo, true);
/*     */     
/* 238 */     if (Trace.isOn) {
/* 239 */       Trace.exit(this, "com.ibm.mq.MQBindingsManagedConnectionFactoryJ11", "createManagedConnection(MQConnectionRequestInfo)", traceRet1);
/*     */     }
/*     */     
/* 242 */     return traceRet1;
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
/*     */   public MQManagedConnectionJ11 matchManagedConnections(Vector connectionSet, MQConnectionRequestInfo cxRequestInfo) throws MQResourceException {
/* 256 */     if (Trace.isOn) {
/* 257 */       Trace.entry(this, "com.ibm.mq.MQBindingsManagedConnectionFactoryJ11", "matchManagedConnections(Vector,MQConnectionRequestInfo)", new Object[] { connectionSet, cxRequestInfo });
/*     */     }
/*     */ 
/*     */     
/* 261 */     MQManagedConnectionJ11 out = null;
/*     */     
/* 263 */     if (!(cxRequestInfo instanceof BindingsConnectionRequestInfo)) {
/* 264 */       MQResourceException traceRet1 = new MQResourceException(2, 2043, this, "MQJI039");
/*     */       
/* 266 */       if (Trace.isOn) {
/* 267 */         Trace.throwing(this, "com.ibm.mq.MQBindingsManagedConnectionFactoryJ11", "matchManagedConnections(Vector,MQConnectionRequestInfo)", traceRet1);
/*     */       }
/*     */       
/* 270 */       throw traceRet1;
/*     */     } 
/*     */     
/* 273 */     Enumeration<MQManagedConnectionJ11> enumVar = connectionSet.elements();
/* 274 */     while (enumVar.hasMoreElements() && out == null) {
/*     */       try {
/* 276 */         MQManagedConnectionJ11 candidate = enumVar.nextElement();
/* 277 */         if (candidate.isSuitable(cxRequestInfo, this)) {
/* 278 */           out = candidate;
/*     */         }
/*     */       }
/* 281 */       catch (ClassCastException e) {
/* 282 */         if (Trace.isOn) {
/* 283 */           Trace.catchBlock(this, "com.ibm.mq.MQBindingsManagedConnectionFactoryJ11", "matchManagedConnections(Vector,MQConnectionRequestInfo)", e, 1);
/*     */ 
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 289 */       catch (NullPointerException e) {
/* 290 */         if (Trace.isOn) {
/* 291 */           Trace.catchBlock(this, "com.ibm.mq.MQBindingsManagedConnectionFactoryJ11", "matchManagedConnections(Vector,MQConnectionRequestInfo)", e, 2);
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 298 */     if (Trace.isOn) {
/* 299 */       if (out == null) {
/* 300 */         Trace.data(this, "matchManagedConnections(java.util.Vector,MQConnectionRequestInfo)", "No suitable MQManagedConnection found", "");
/*     */       } else {
/* 302 */         Trace.data(this, "matchManagedConnections(java.util.Vector,MQConnectionRequestInfo)", "Suitable MQManagedConnection found: ", out);
/*     */       } 
/*     */     }
/*     */     
/* 306 */     if (Trace.isOn) {
/* 307 */       Trace.exit(this, "com.ibm.mq.MQBindingsManagedConnectionFactoryJ11", "matchManagedConnections(Vector,MQConnectionRequestInfo)", out);
/*     */     }
/*     */     
/* 310 */     return out;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 321 */     if (Trace.isOn) {
/* 322 */       Trace.entry(this, "com.ibm.mq.MQBindingsManagedConnectionFactoryJ11", "hashCode()");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 330 */     int traceRet1 = getClass().hashCode();
/*     */     
/* 332 */     if (Trace.isOn) {
/* 333 */       Trace.exit(this, "com.ibm.mq.MQBindingsManagedConnectionFactoryJ11", "hashCode()", 
/* 334 */           Integer.valueOf(traceRet1));
/*     */     }
/* 336 */     return traceRet1;
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
/*     */   public boolean equals(Object obj) {
/* 348 */     if (Trace.isOn) {
/* 349 */       Trace.entry(this, "com.ibm.mq.MQBindingsManagedConnectionFactoryJ11", "equals(Object)", new Object[] { obj });
/*     */     }
/*     */     
/* 352 */     if (this == obj) {
/*     */       
/* 354 */       if (Trace.isOn) {
/* 355 */         Trace.exit(this, "com.ibm.mq.MQBindingsManagedConnectionFactoryJ11", "equals(Object)", 
/* 356 */             Boolean.valueOf(true), 1);
/*     */       }
/* 358 */       return true;
/*     */     } 
/*     */     
/* 361 */     if (obj == null) {
/* 362 */       if (Trace.isOn) {
/* 363 */         Trace.exit(this, "com.ibm.mq.MQBindingsManagedConnectionFactoryJ11", "equals(Object)", 
/* 364 */             Boolean.valueOf(false), 2);
/*     */       }
/* 366 */       return false;
/*     */     } 
/*     */     
/* 369 */     if (!obj.getClass().equals(getClass())) {
/* 370 */       if (Trace.isOn) {
/* 371 */         Trace.exit(this, "com.ibm.mq.MQBindingsManagedConnectionFactoryJ11", "equals(Object)", 
/* 372 */             Boolean.valueOf(false), 3);
/*     */       }
/* 374 */       return false;
/*     */     } 
/*     */     
/* 377 */     MQBindingsManagedConnectionFactoryJ11 other = (MQBindingsManagedConnectionFactoryJ11)obj;
/*     */ 
/*     */     
/* 380 */     if (twoStringsEqual(this.fieldQMGR, other.fieldQMGR)) {
/* 381 */       if (Trace.isOn) {
/* 382 */         Trace.exit(this, "com.ibm.mq.MQBindingsManagedConnectionFactoryJ11", "equals(Object)", 
/* 383 */             Boolean.valueOf(true), 4);
/*     */       }
/* 385 */       return true;
/*     */     } 
/*     */     
/* 388 */     if (Trace.isOn) {
/* 389 */       Trace.exit(this, "com.ibm.mq.MQBindingsManagedConnectionFactoryJ11", "equals(Object)", 
/* 390 */           Boolean.valueOf(false), 5);
/*     */     }
/* 392 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() {
/* 402 */     if (Trace.isOn) {
/* 403 */       Trace.entry(this, "com.ibm.mq.MQBindingsManagedConnectionFactoryJ11", "clone()");
/*     */     }
/*     */     try {
/* 406 */       Object traceRet1 = super.clone();
/*     */       
/* 408 */       if (Trace.isOn) {
/* 409 */         Trace.exit(this, "com.ibm.mq.MQBindingsManagedConnectionFactoryJ11", "clone()", traceRet1);
/*     */       }
/*     */       
/* 412 */       return traceRet1;
/*     */     }
/* 414 */     catch (CloneNotSupportedException e) {
/* 415 */       if (Trace.isOn) {
/* 416 */         Trace.catchBlock(this, "com.ibm.mq.MQBindingsManagedConnectionFactoryJ11", "clone()", e);
/*     */       }
/*     */       
/* 419 */       IllegalAccessError traceRet2 = new IllegalAccessError();
/* 420 */       if (Trace.isOn) {
/* 421 */         Trace.throwing(this, "com.ibm.mq.MQBindingsManagedConnectionFactoryJ11", "clone()", traceRet2);
/*     */       }
/*     */       
/* 424 */       throw traceRet2;
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
/*     */   static boolean twoStringsEqual(String a, String b) {
/*     */     boolean result;
/* 438 */     if (Trace.isOn) {
/* 439 */       Trace.entry("com.ibm.mq.MQBindingsManagedConnectionFactoryJ11", "twoStringsEqual(String,String)", new Object[] { a, b });
/*     */     }
/*     */ 
/*     */     
/* 443 */     if (a == null && b == null) {
/* 444 */       result = true;
/* 445 */     } else if (a == null || b == null) {
/* 446 */       result = false;
/*     */     } else {
/* 448 */       result = a.equals(b);
/*     */     } 
/* 450 */     if (Trace.isOn) {
/* 451 */       Trace.exit("com.ibm.mq.MQBindingsManagedConnectionFactoryJ11", "twoStringsEqual(String,String)", 
/* 452 */           Boolean.valueOf(result));
/*     */     }
/* 454 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 462 */     if (Trace.isOn) {
/* 463 */       Trace.entry(this, "com.ibm.mq.MQBindingsManagedConnectionFactoryJ11", "writeObject(java.io.ObjectOutputStream)", new Object[] { out });
/*     */     }
/*     */     
/* 466 */     NotSerializableException traceRet1 = new NotSerializableException();
/*     */     
/* 468 */     if (Trace.isOn) {
/* 469 */       Trace.throwing(this, "com.ibm.mq.MQBindingsManagedConnectionFactoryJ11", "writeObject(java.io.ObjectOutputStream)", traceRet1);
/*     */     }
/*     */     
/* 472 */     throw traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 480 */     if (Trace.isOn) {
/* 481 */       Trace.entry(this, "com.ibm.mq.MQBindingsManagedConnectionFactoryJ11", "readObject(java.io.ObjectInputStream)", new Object[] { in });
/*     */     }
/*     */     
/* 484 */     NotSerializableException traceRet1 = new NotSerializableException();
/*     */     
/* 486 */     if (Trace.isOn) {
/* 487 */       Trace.throwing(this, "com.ibm.mq.MQBindingsManagedConnectionFactoryJ11", "readObject(java.io.ObjectInputStream)", traceRet1);
/*     */     }
/*     */     
/* 490 */     throw traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQBindingsManagedConnectionFactoryJ11.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */