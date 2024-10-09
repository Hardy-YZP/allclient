/*     */ package com.ibm.mq.jmqi.system;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.handles.Hconn;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.nio.charset.CharsetDecoder;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JmqiTls
/*     */   extends JmqiComponentTls
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/JmqiTls.java";
/*     */   public CharsetDecoder cachedDecoder;
/*  48 */   public char[] ca = new char[64];
/*     */ 
/*     */   
/*     */   public int caPos;
/*     */ 
/*     */   
/*     */   public byte[] rfhNameValueDataBuf;
/*     */   
/*  56 */   public int rfhNameValueDataBuf_Length = -1;
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<String> rfhNameValueData_List;
/*     */ 
/*     */ 
/*     */   
/*     */   public JmqiException lastException;
/*     */ 
/*     */   
/*     */   private Hconn asyncConsumeHconn;
/*     */ 
/*     */   
/*  70 */   private HashMap<String, byte[]> connections = (HashMap)new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  75 */   private long wasTranID = -1L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   private final HashMap<String, IMSState> imsStateMap = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean classesForJavaMQGET = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean classesForJavaMQGETCalledWithMaxMsgLength = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAsyncConsumeThread(Hconn hconn) {
/* 105 */     this.asyncConsumeHconn = hconn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAsyncConsumeThread(Hconn hconn) {
/* 114 */     return (this.asyncConsumeHconn == hconn);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, byte[]> getConnections() {
/* 121 */     if (Trace.isOn) {
/* 122 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiTls", "getConnections()", "getter", this.connections);
/*     */     }
/*     */     
/* 125 */     return (Map<String, byte[]>)this.connections;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWASTranID(long wasTranID) {
/* 132 */     if (Trace.isOn) {
/* 133 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiTls", "setWASTranID(long)", "setter", 
/* 134 */           Long.valueOf(wasTranID));
/*     */     }
/* 136 */     this.wasTranID = wasTranID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getWASTranID() {
/* 143 */     if (Trace.isOn) {
/* 144 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiTls", "getWASTranID()", "getter", 
/* 145 */           Long.valueOf(this.wasTranID));
/*     */     }
/* 147 */     return this.wasTranID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setClassesForJavaMQGET() {
/* 157 */     if (Trace.isOn) {
/* 158 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiTls", "setClassesForJavaMQGET()");
/*     */     }
/* 160 */     this.classesForJavaMQGET = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unsetClassesForJavaMQGET() {
/* 168 */     if (Trace.isOn) {
/* 169 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiTls", "unsetClassesForJavaMQGET()");
/*     */     }
/* 171 */     this.classesForJavaMQGET = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getClassesForJavaMQGET() {
/* 178 */     if (Trace.isOn) {
/* 179 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiTls", "getClassesForJavaMQGET()", "getter", 
/* 180 */           Boolean.valueOf(this.classesForJavaMQGET));
/*     */     }
/* 182 */     return this.classesForJavaMQGET;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setClassesForJavaMQGETCalledWithMaxMsgLength() {
/* 193 */     if (Trace.isOn) {
/* 194 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiTls", "setClassesForJavaMQGETCalledWithMaxMsgLength()");
/*     */     }
/* 196 */     this.classesForJavaMQGETCalledWithMaxMsgLength = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unsetClassesForJavaMQGETCalledWithMaxMsgLength() {
/* 205 */     if (Trace.isOn) {
/* 206 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiTls", "unsetClassesForJavaMQGETCalledWithMaxMsgLength()");
/*     */     }
/* 208 */     this.classesForJavaMQGETCalledWithMaxMsgLength = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getClassesForJavaMQGETCalledWithMaxMsgLength() {
/* 216 */     if (Trace.isOn) {
/* 217 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiTls", "getClassesForJavaMQGETCalledWithMaxMsgLength()", "getter", 
/* 218 */           Boolean.valueOf(this.classesForJavaMQGETCalledWithMaxMsgLength));
/*     */     }
/* 220 */     return this.classesForJavaMQGETCalledWithMaxMsgLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetIMSState(Hconn handle) throws JmqiException {
/* 231 */     if (Trace.isOn) {
/* 232 */       Trace.entry(this, "com.ibm.mq.jmqi.system.JmqiTls", "resetIMSState(Hconn)", new Object[] { handle });
/*     */     }
/*     */     
/* 235 */     String name = handle.getName();
/* 236 */     IMSState imsState = this.imsStateMap.get(name);
/*     */     
/* 238 */     if (imsState != null) {
/* 239 */       imsState.reset();
/*     */     } else {
/*     */       
/* 242 */       imsState = new IMSState(name, true);
/* 243 */       this.imsStateMap.put(name, imsState);
/*     */     } 
/*     */     
/* 246 */     imsState.addHConn(handle);
/*     */     
/* 248 */     if (Trace.isOn) {
/* 249 */       Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiTls", "resetIMSState(Hconn)");
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
/*     */   public boolean removeHConnFromIMSState(Hconn handle) throws JmqiException {
/* 263 */     if (Trace.isOn) {
/* 264 */       Trace.entry(this, "com.ibm.mq.jmqi.system.JmqiTls", "removeHConnFromIMSState(Hconn)", new Object[] { handle });
/*     */     }
/*     */     
/* 267 */     String name = handle.getName();
/* 268 */     boolean disconnect = false;
/*     */     
/* 270 */     IMSState imsState = this.imsStateMap.get(name);
/*     */     
/* 272 */     if (imsState != null) {
/* 273 */       if (imsState.removeHConn(handle))
/*     */       {
/*     */ 
/*     */         
/* 277 */         if (imsState.shouldDisconnect()) {
/* 278 */           disconnect = true;
/* 279 */           this.imsStateMap.remove(name);
/*     */ 
/*     */         
/*     */         }
/*     */ 
/*     */       
/*     */       }
/*     */       else
/*     */       {
/*     */         
/* 289 */         disconnect = false;
/*     */       
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 296 */       disconnect = false;
/*     */     } 
/*     */     
/* 299 */     if (Trace.isOn) {
/* 300 */       Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiTls", "removeHConnFromIMSState(Hconn)", Boolean.valueOf(disconnect));
/*     */     }
/* 302 */     return disconnect;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addHConnToIMSState(Hconn handle) throws JmqiException {
/* 313 */     if (Trace.isOn) {
/* 314 */       Trace.entry(this, "com.ibm.mq.jmqi.system.JmqiTls", "addHConnToIMSState(Hconn)", new Object[] { handle });
/*     */     }
/*     */     
/* 317 */     String name = handle.getName();
/*     */     
/* 319 */     IMSState imsState = this.imsStateMap.get(name);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 327 */     if (imsState == null) {
/* 328 */       imsState = new IMSState(name, false);
/* 329 */       this.imsStateMap.put(name, imsState);
/*     */     } 
/*     */     
/* 332 */     imsState.addHConn(handle);
/*     */     
/* 334 */     if (Trace.isOn) {
/* 335 */       Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiTls", "addHConnToIMSState(Hconn)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final class IMSState
/*     */   {
/*     */     private final String qmgr;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 352 */     private Set<Hconn> handles = new HashSet<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private boolean connectedByJMS;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public IMSState(String qmgr, boolean connectedByJMS) {
/* 367 */       if (Trace.isOn) {
/* 368 */         Trace.entry(this, "com.ibm.mq.jmqi.system.JmqiTls.IMSState", "<init>", new Object[] { qmgr, Boolean.valueOf(connectedByJMS) });
/*     */       }
/*     */       
/* 371 */       this.qmgr = qmgr;
/* 372 */       this.connectedByJMS = connectedByJMS;
/*     */       
/* 374 */       if (Trace.isOn) {
/* 375 */         Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiTls.IMSState", "<init>");
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void reset() {
/* 383 */       if (Trace.isOn) {
/* 384 */         Trace.entry(this, "com.ibm.mq.jmqi.system.JmqiTls.IMSState", "reset()");
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 389 */       for (Hconn handle : this.handles) {
/* 390 */         handle.invalidate();
/*     */       }
/*     */       
/* 393 */       this.handles.clear();
/*     */ 
/*     */       
/* 396 */       this.connectedByJMS = true;
/*     */       
/* 398 */       if (Trace.isOn) {
/* 399 */         Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiTls.IMSState", "reset()");
/*     */       }
/*     */     }
/*     */     
/*     */     public boolean removeHConn(Hconn handle) {
/* 404 */       if (Trace.isOn) {
/* 405 */         Trace.entry(this, "com.ibm.mq.jmqi.system.JmqiTls.IMSState", "removeHConn()", new Object[] { handle });
/*     */       }
/*     */       
/* 408 */       boolean rc = this.handles.remove(handle);
/*     */       
/* 410 */       if (Trace.isOn) {
/* 411 */         Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiTls.IMSState", "removeHConn()", Boolean.valueOf(rc));
/*     */       }
/*     */       
/* 414 */       return rc;
/*     */     }
/*     */     
/*     */     public void addHConn(Hconn handle) {
/* 418 */       if (Trace.isOn) {
/* 419 */         Trace.entry(this, "com.ibm.mq.jmqi.system.JmqiTls.IMSState", "addHConn()", new Object[] { handle });
/*     */       }
/*     */       
/* 422 */       if (!this.handles.add(handle)) {
/*     */         
/* 424 */         HashMap<String, Object> ffstInfo = new HashMap<>();
/* 425 */         ffstInfo.put("Description", "Handle already exists");
/* 426 */         ffstInfo.put("handle", handle);
/* 427 */         ffstInfo.put("handles", this.handles);
/*     */         
/* 429 */         Trace.ffst(this, "addHConn", "02", ffstInfo, null);
/*     */       } 
/*     */       
/* 432 */       if (Trace.isOn) {
/* 433 */         Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiTls.IMSState", "addHConn()");
/*     */       }
/*     */     }
/*     */     
/*     */     public boolean shouldDisconnect() {
/* 438 */       if (Trace.isOn) {
/* 439 */         Trace.entry(this, "com.ibm.mq.jmqi.system.JmqiTls.IMSState", "shouldDisconnect()");
/*     */       }
/*     */       
/* 442 */       boolean rc = this.connectedByJMS ? ((this.handles.size() == 0)) : false;
/*     */       
/* 444 */       if (Trace.isOn) {
/* 445 */         Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiTls.IMSState", "shouldDisconnect()", Boolean.valueOf(rc));
/*     */       }
/* 447 */       return rc;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 452 */       return "IMSState[qmgr = " + this.qmgr + ", connectedByJMS = " + this.connectedByJMS + ", handles = " + this.handles + "]";
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\JmqiTls.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */