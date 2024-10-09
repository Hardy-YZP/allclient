/*      */ package com.ibm.mq.jmqi;
/*      */ 
/*      */ import com.ibm.mq.jmqi.handles.Hconn;
/*      */ import com.ibm.mq.jmqi.internal.Configuration;
/*      */ import com.ibm.mq.jmqi.internal.JmqiResource;
/*      */ import com.ibm.mq.jmqi.internal.JmqiTools;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.WeakHashMap;
/*      */ import javax.transaction.xa.XAException;
/*      */ import javax.transaction.xa.XAResource;
/*      */ import javax.transaction.xa.Xid;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class JmqiXAResource
/*      */   extends JmqiObject
/*      */   implements XAResource
/*      */ {
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/JmqiXAResource.java";
/*      */   private JmqiXA xa;
/*      */   private Hconn hconn;
/*      */   private int rmid;
/*      */   private boolean isClosed;
/*      */   private String qmgrName;
/*      */   private String qmgrUid;
/*      */   
/*      */   static {
/*   48 */     if (Trace.isOn) {
/*   49 */       Trace.data("com.ibm.mq.jmqi.JmqiXAResource", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/JmqiXAResource.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   64 */   private int refCount = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int nextAvailableRmid;
/*      */ 
/*      */ 
/*      */   
/*   73 */   public static final Object nextAvailableRmidLock = new Object();
/*      */ 
/*      */   
/*   76 */   private static Map<Hconn, JmqiXAResource> XAResourceCache = new WeakHashMap<>();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isActive = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   88 */   private Xid inProgressXid = null;
/*      */   
/*   90 */   private Object syncLock = new Object();
/*      */ 
/*      */ 
/*      */   
/*      */   boolean rScanInProgress = false;
/*      */ 
/*      */ 
/*      */   
/*      */   private String qsgName;
/*      */ 
/*      */ 
/*      */   
/*      */   protected static JmqiXAResource getInstance(JmqiEnvironment env, JmqiXA xa, Hconn hconn) throws XAException {
/*  103 */     if (Trace.isOn) {
/*  104 */       Trace.entry("com.ibm.mq.jmqi.JmqiXAResource", "getInstance(JmqiEnvironment,JmqiXA,Hconn)", new Object[] { env, xa, hconn });
/*      */     }
/*      */     
/*  107 */     JmqiXAResource result = null;
/*  108 */     synchronized (XAResourceCache) {
/*  109 */       result = XAResourceCache.get(hconn);
/*  110 */       if (result == null) {
/*  111 */         result = new JmqiXAResource(env, xa, hconn, nextAvailableRmid++);
/*  112 */         XAResourceCache.put(hconn, result);
/*      */       } 
/*  114 */       result.refCount++;
/*      */     } 
/*      */     
/*  117 */     if (Trace.isOn) {
/*  118 */       Trace.exit("com.ibm.mq.jmqi.JmqiXAResource", "getInstance(JmqiEnvironment,JmqiXA,Hconn)", result);
/*      */     }
/*      */     
/*  121 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   private static void uncacheJmqiXAResource(JmqiXAResource resource, Hconn hconn) {
/*  126 */     if (Trace.isOn) {
/*  127 */       Trace.entry("com.ibm.mq.jmqi.JmqiXAResource", "uncacheJmqiXAResource(JmqiXAResource,Hconn)", new Object[] { resource, hconn });
/*      */     }
/*      */     
/*  130 */     synchronized (XAResourceCache) {
/*  131 */       JmqiXAResource result = XAResourceCache.get(hconn);
/*  132 */       if (result != null && result == resource) {
/*  133 */         XAResourceCache.remove(hconn);
/*      */       }
/*      */     } 
/*  136 */     if (Trace.isOn) {
/*  137 */       Trace.exit("com.ibm.mq.jmqi.JmqiXAResource", "uncacheJmqiXAResource(JmqiXAResource,Hconn)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getActiveState() {
/*  147 */     if (Trace.isOn) {
/*  148 */       Trace.data(this, "com.ibm.mq.jmqi.JmqiXAResource", "getActiveState()", "getter", 
/*  149 */           Boolean.valueOf(this.isActive));
/*      */     }
/*  151 */     return this.isActive;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected JmqiXAResource(JmqiEnvironment env, JmqiXA xa, Hconn hconn, int rmid) throws XAException {
/*  168 */     super(env);
/*  169 */     if (Trace.isOn) {
/*  170 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiXAResource", "<init>(JmqiEnvironment,JmqiXA,Hconn,int)", new Object[] { env, xa, hconn, 
/*      */             
/*  172 */             Integer.valueOf(rmid) });
/*      */     }
/*  174 */     this.xa = xa;
/*  175 */     this.hconn = hconn;
/*  176 */     this.rmid = rmid;
/*  177 */     String xaOpenString = null;
/*      */     
/*      */     try {
/*  180 */       if (xa == null || hconn == null) {
/*      */         
/*  182 */         HashMap<String, Object> ffstInfo = new HashMap<>();
/*  183 */         ffstInfo.put("xa", xa);
/*  184 */         ffstInfo.put("hconn", hconn);
/*  185 */         ffstInfo.put("Description", "No connection to queue manager (null Hconn or JmqiXA)");
/*      */         
/*  187 */         Trace.ffst(this, "<init>(JmqiEnvironment,JmqiXA,Hconn,int)", "01", ffstInfo, null);
/*      */         
/*  189 */         JmqiException e = new JmqiException(env, -1, null, 2, 2195, null);
/*      */ 
/*      */ 
/*      */         
/*  193 */         if (Trace.isOn) {
/*  194 */           Trace.throwing(this, "com.ibm.mq.jmqi.JmqiXAResource", "<init>(JmqiEnvironment,JmqiXA,Hconn,int)", e, 1);
/*      */         }
/*      */         
/*  197 */         throw e;
/*      */       } 
/*  199 */       if (!hconn.isXASupported()) {
/*      */         
/*  201 */         HashMap<String, Object> ffstInfo = new HashMap<>();
/*  202 */         ffstInfo.put("xa", xa);
/*  203 */         ffstInfo.put("hconn", hconn);
/*  204 */         ffstInfo.put("Description", "XA verbs (with an object context) are not supported by the connection");
/*      */         
/*  206 */         Trace.ffst(this, "<init>(JmqiEnvironment,JmqiXA,Hconn,int)", "02", ffstInfo, null);
/*      */         
/*  208 */         JmqiException e = new JmqiException(env, -1, null, 2, 2195, null);
/*      */ 
/*      */         
/*  211 */         if (Trace.isOn) {
/*  212 */           Trace.throwing(this, "com.ibm.mq.jmqi.JmqiXAResource", "<init>(JmqiEnvironment,JmqiXA,Hconn,int)", e, 2);
/*      */         }
/*      */         
/*  215 */         throw e;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  223 */       this.qsgName = hconn.getQsgName();
/*  224 */       this.qmgrName = hconn.getName();
/*  225 */       xaOpenString = "qmname=" + ((this.qsgName != null && !this.qsgName.isEmpty()) ? this.qsgName : this.qmgrName).trim();
/*      */ 
/*      */       
/*  228 */       this.qmgrUid = hconn.getUid();
/*      */     }
/*  230 */     catch (JmqiException e) {
/*  231 */       if (Trace.isOn) {
/*  232 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.JmqiXAResource", "<init>(JmqiEnvironment,JmqiXA,Hconn,int)", e);
/*      */       }
/*      */ 
/*      */       
/*  236 */       String causeMessage = null;
/*  237 */       Throwable cause = e.getCause();
/*  238 */       if (cause != null) {
/*  239 */         causeMessage = cause.getMessage();
/*      */       }
/*      */       
/*  242 */       HashMap<String, Object> ffstInfo = new HashMap<>();
/*  243 */       ffstInfo.put("Exception summary", JmqiTools.getExSumm(e));
/*  244 */       ffstInfo.put("causeMessage", causeMessage);
/*  245 */       ffstInfo.put("Description", "Unexpected Exception");
/*  246 */       Trace.ffst(this, "<init>(JmqiEnvironment,JmqiXA,Hconn,int)", "03", ffstInfo, null);
/*      */ 
/*      */       
/*  249 */       String[] inserts = { "xa_open" };
/*  250 */       String msg = JmqiResource.getString("MQJE999", inserts);
/*      */       
/*  252 */       XAException xae = new XAException(msg);
/*  253 */       if (Trace.isOn) {
/*  254 */         Trace.throwing(this, "com.ibm.mq.jmqi.JmqiXAResource", "<init>(JmqiEnvironment,JmqiXA,Hconn,int)", xae, 3);
/*      */       }
/*      */       
/*  257 */       throw xae;
/*      */     } 
/*      */ 
/*      */     
/*  261 */     int returncode = xa.xa_open(hconn, xaOpenString, rmid, 0);
/*      */ 
/*      */ 
/*      */     
/*  265 */     if (returncode != 0 && returncode != 3) {
/*  266 */       String[] inserts = { "xa_open", Integer.toString(returncode) };
/*  267 */       String msg = JmqiResource.getString("MQJE114", inserts);
/*      */       
/*  269 */       XAException e = new XAException(msg);
/*  270 */       e.errorCode = returncode;
/*  271 */       if (Trace.isOn) {
/*  272 */         Trace.throwing(this, "com.ibm.mq.jmqi.JmqiXAResource", "<init>(JmqiEnvironment,JmqiXA,Hconn,int)", e, 4);
/*      */       }
/*      */       
/*  275 */       throw e;
/*      */     } 
/*  277 */     if (Trace.isOn) {
/*  278 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiXAResource", "<init>(JmqiEnvironment,JmqiXA,Hconn,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void close_internal(boolean calledByFinalize) throws XAException {
/*  290 */     if (Trace.isOn) {
/*  291 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiXAResource", "close_internal(boolean)", new Object[] {
/*  292 */             Boolean.valueOf(calledByFinalize)
/*      */           });
/*      */     }
/*  295 */     int returncode = 0;
/*      */ 
/*      */     
/*  298 */     boolean endFailed = false;
/*      */ 
/*      */     
/*  301 */     synchronized (this.syncLock) {
/*  302 */       boolean uncachedSelf = false;
/*      */       
/*  304 */       synchronized (XAResourceCache) {
/*  305 */         this.refCount--;
/*  306 */         if (this.refCount == 0) {
/*  307 */           uncacheJmqiXAResource(this, this.hconn);
/*  308 */           uncachedSelf = true;
/*      */         } 
/*      */       } 
/*  311 */       if (uncachedSelf)
/*      */       {
/*      */ 
/*      */         
/*  315 */         if (this.isActive) {
/*      */           
/*  317 */           returncode = this.xa.xa_end(this.hconn, this.inProgressXid, this.rmid, 536870912);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  326 */           if (returncode >= 0) {
/*  327 */             this.isActive = false;
/*  328 */             this.inProgressXid = null;
/*      */           } else {
/*      */             
/*  331 */             endFailed = true;
/*      */           } 
/*      */         } 
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  341 */       if (endFailed && !calledByFinalize) {
/*  342 */         returncode = -6;
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/*  348 */         if (!this.isActive && uncachedSelf) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  353 */           String xaCloseString = this.qmgrName;
/*      */           try {
/*  355 */             if (this.hconn.getPlatform() == 1) {
/*      */               
/*  357 */               String originalQmgrName = this.hconn.getOriginalQueueManagerName();
/*  358 */               if (originalQmgrName != null)
/*      */               {
/*      */                 
/*  361 */                 if (originalQmgrName.trim().length() != 0)
/*      */                 {
/*      */ 
/*      */                   
/*  365 */                   xaCloseString = originalQmgrName;
/*      */                 }
/*      */               }
/*      */             } 
/*  369 */           } catch (Exception e) {
/*  370 */             if (Trace.isOn) {
/*  371 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.JmqiXAResource", "close_internal(boolean)", e);
/*      */             }
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  378 */           returncode = this.xa.xa_close(this.hconn, xaCloseString, this.rmid, 0);
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  384 */         if (returncode == 0 || calledByFinalize) {
/*      */           
/*  386 */           this.isClosed = true;
/*      */ 
/*      */           
/*  389 */           this.hconn = null;
/*      */         } else {
/*      */           
/*  392 */           String[] inserts = { "xa_close", Integer.toString(returncode) };
/*  393 */           String msg = JmqiResource.getString("MQJE114", inserts);
/*      */           
/*  395 */           XAException e = new XAException(msg);
/*  396 */           e.errorCode = returncode;
/*      */           
/*  398 */           if (Trace.isOn) {
/*  399 */             Trace.throwing(this, "com.ibm.mq.jmqi.JmqiXAResource", "close_internal(boolean)", e);
/*      */           }
/*  401 */           throw e;
/*      */         } 
/*      */       } 
/*      */     } 
/*  405 */     if (Trace.isOn) {
/*  406 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiXAResource", "close_internal(boolean)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void close() throws XAException {
/*  418 */     if (Trace.isOn) {
/*  419 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiXAResource", "close()");
/*      */     }
/*  421 */     close(false);
/*  422 */     if (Trace.isOn) {
/*  423 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiXAResource", "close()");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void close(boolean calledByFinalize) throws XAException {
/*  429 */     if (Trace.isOn)
/*  430 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiXAResource", "close(boolean)", new Object[] {
/*  431 */             Boolean.valueOf(calledByFinalize)
/*      */           }); 
/*  433 */     if (!this.isClosed)
/*      */     {
/*  435 */       close_internal(calledByFinalize);
/*      */     }
/*      */     
/*  438 */     if (Trace.isOn) {
/*  439 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiXAResource", "close(boolean)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void finalize() throws Throwable {
/*  453 */     if (Trace.isOn) {
/*  454 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiXAResource", "finalize()");
/*      */     }
/*  456 */     close(true);
/*      */     
/*  458 */     if (Trace.isOn) {
/*  459 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiXAResource", "finalize()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void commit(Xid xid, boolean onePhase) throws XAException {
/*      */     int returncode;
/*  471 */     if (Trace.isOn) {
/*  472 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiXAResource", "commit(Xid,boolean)", new Object[] { xid, Boolean.valueOf(onePhase) });
/*      */     }
/*      */     
/*  475 */     int flags = onePhase ? 1073741824 : 0;
/*      */     
/*  477 */     String msg = null;
/*  478 */     synchronized (this.syncLock) {
/*  479 */       if (this.isClosed) {
/*      */ 
/*      */         
/*  482 */         returncode = -7;
/*  483 */         String[] inserts = { "xa_commit", Integer.toString(returncode) };
/*  484 */         msg = JmqiResource.getString("MQJE118", inserts);
/*      */       }
/*      */       else {
/*      */         
/*  488 */         returncode = this.xa.xa_commit(this.hconn, xid, this.rmid, flags);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  493 */     if (returncode != 0 && returncode != 3) {
/*  494 */       if (msg == null) {
/*  495 */         String[] inserts = { "xa_commit", Integer.toString(returncode) };
/*      */         
/*  497 */         msg = JmqiResource.getString("MQJE114", inserts);
/*      */       } 
/*  499 */       XAException e = new XAException(msg);
/*  500 */       e.errorCode = returncode;
/*      */       
/*  502 */       if (Trace.isOn) {
/*  503 */         Trace.throwing(this, "com.ibm.mq.jmqi.JmqiXAResource", "commit(Xid,boolean)", e);
/*      */       }
/*  505 */       throw e;
/*      */     } 
/*  507 */     if (Trace.isOn) {
/*  508 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiXAResource", "commit(Xid,boolean)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void end(Xid xid, int flags) throws XAException {
/*  518 */     if (Trace.isOn) {
/*  519 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiXAResource", "end(Xid,int)", new Object[] { xid, 
/*  520 */             Integer.valueOf(flags) });
/*      */     }
/*      */     
/*      */     try {
/*      */       int returncode;
/*  525 */       String msg = null;
/*      */ 
/*      */ 
/*      */       
/*  529 */       synchronized (this.syncLock) {
/*      */         
/*  531 */         if (this.isClosed) {
/*  532 */           returncode = 100;
/*  533 */           String[] inserts = { "xa_end", Integer.toString(returncode) };
/*  534 */           msg = JmqiResource.getString("MQJE118", inserts);
/*      */         }
/*      */         else {
/*      */           
/*  538 */           returncode = this.xa.xa_end(this.hconn, xid, this.rmid, flags);
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  550 */         if (returncode >= 0) {
/*  551 */           this.isActive = false;
/*  552 */           this.inProgressXid = null;
/*      */         } 
/*      */       } 
/*      */       
/*  556 */       if (returncode != 0) {
/*  557 */         if (msg == null) {
/*  558 */           String[] inserts = { "xa_end", Integer.toString(returncode) };
/*      */           
/*  560 */           msg = JmqiResource.getString("MQJE114", inserts);
/*      */         } 
/*  562 */         XAException e = new XAException(msg);
/*  563 */         e.errorCode = returncode;
/*      */         
/*  565 */         if (Trace.isOn) {
/*  566 */           Trace.throwing(this, "com.ibm.mq.jmqi.JmqiXAResource", "end(Xid,int)", e);
/*      */         }
/*  568 */         throw e;
/*      */       } 
/*      */     } finally {
/*      */       
/*  572 */       if (Trace.isOn) {
/*  573 */         Trace.exit(this, "com.ibm.mq.jmqi.JmqiXAResource", "end(Xid,int)");
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void forget(Xid xid) throws XAException {
/*      */     int returncode;
/*  585 */     if (Trace.isOn) {
/*  586 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiXAResource", "forget(Xid)", new Object[] { xid });
/*      */     }
/*      */ 
/*      */     
/*  590 */     String msg = null;
/*  591 */     synchronized (this.syncLock) {
/*  592 */       if (this.isClosed) {
/*      */ 
/*      */         
/*  595 */         returncode = -7;
/*  596 */         String[] inserts = { "xa_forget", Integer.toString(returncode) };
/*  597 */         msg = JmqiResource.getString("MQJE118", inserts);
/*      */       }
/*      */       else {
/*      */         
/*  601 */         returncode = this.xa.xa_forget(this.hconn, xid, this.rmid, 0);
/*      */       } 
/*      */     } 
/*      */     
/*  605 */     if (returncode != 0) {
/*  606 */       if (msg == null) {
/*  607 */         String[] inserts = { "xa_forget", Integer.toString(returncode) };
/*      */         
/*  609 */         msg = JmqiResource.getString("MQJE114", inserts);
/*      */       } 
/*  611 */       XAException e = new XAException(msg);
/*  612 */       e.errorCode = returncode;
/*      */       
/*  614 */       if (Trace.isOn) {
/*  615 */         Trace.throwing(this, "com.ibm.mq.jmqi.JmqiXAResource", "forget(Xid)", e);
/*      */       }
/*  617 */       throw e;
/*      */     } 
/*  619 */     if (Trace.isOn) {
/*  620 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiXAResource", "forget(Xid)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getTransactionTimeout() throws XAException {
/*  632 */     int traceRet1 = -1;
/*  633 */     if (Trace.isOn) {
/*  634 */       Trace.data(this, "com.ibm.mq.jmqi.JmqiXAResource", "getTransactionTimeout()", "getter", 
/*  635 */           Integer.valueOf(traceRet1));
/*      */     }
/*  637 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSameRM(XAResource xares) throws XAException {
/*  646 */     if (Trace.isOn) {
/*  647 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiXAResource", "isSameRM(XAResource)", new Object[] { xares });
/*      */     }
/*      */     
/*  650 */     boolean result = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  671 */     if (this.env != null) {
/*  672 */       Configuration cfg = this.env.getConfiguration();
/*  673 */       if (cfg != null) {
/*  674 */         String xaTransactionManager = cfg.getStringValue(Configuration.XA_TRANSACTION_MANAGER);
/*      */         
/*  676 */         if (xaTransactionManager != null && xaTransactionManager.equals("bitronix")) {
/*  677 */           if (Trace.isOn) {
/*  678 */             Trace.data(this, "com.ibm.mq.jmqi.JmqiXAResource", "isSameRM(XAResource)", "Running inside of bitronix, so check to see if the two XA resources point to the same queue manager");
/*      */           }
/*      */           
/*  681 */           if (xares instanceof JmqiXAResource) {
/*  682 */             JmqiXAResource jmqiXAres = (JmqiXAResource)xares;
/*      */             
/*  684 */             if (Trace.isOn) {
/*  685 */               Trace.data(this, "com.ibm.mq.jmqi.JmqiXAResource", "isSameRM(XAResource)", "qmgrUid for this JmqiXAResource: " + this.qmgrUid);
/*      */               
/*  687 */               Trace.data(this, "com.ibm.mq.jmqi.JmqiXAResource", "isSameRM(XAResource)", "qmgrUid for the JmqiXAResource passed into the method: " + jmqiXAres.qmgrUid);
/*      */             } 
/*      */ 
/*      */             
/*  691 */             result = this.qmgrUid.equals(jmqiXAres.qmgrUid);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  697 */     if (Trace.isOn) {
/*  698 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiXAResource", "isSameRM(XAResource)", 
/*  699 */           Boolean.valueOf(result));
/*      */     }
/*  701 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int prepare(Xid xid) throws XAException {
/*      */     int returncode;
/*  710 */     if (Trace.isOn) {
/*  711 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiXAResource", "prepare(Xid)", new Object[] { xid });
/*      */     }
/*      */ 
/*      */     
/*  715 */     String msg = null;
/*  716 */     synchronized (this.syncLock) {
/*  717 */       if (this.isClosed) {
/*  718 */         returncode = -4;
/*  719 */         String[] inserts = { "xa_prepare", Integer.toString(returncode) };
/*  720 */         msg = JmqiResource.getString("MQJE118", inserts);
/*      */       }
/*      */       else {
/*      */         
/*  724 */         returncode = this.xa.xa_prepare(this.hconn, xid, this.rmid, 0);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  729 */     if (returncode != 0 && returncode != 3) {
/*  730 */       if (msg == null) {
/*  731 */         String[] inserts = { "xa_prepare", Integer.toString(returncode) };
/*      */         
/*  733 */         msg = JmqiResource.getString("MQJE114", inserts);
/*      */       } 
/*  735 */       XAException e = new XAException(msg);
/*  736 */       e.errorCode = returncode;
/*      */       
/*  738 */       if (Trace.isOn) {
/*  739 */         Trace.throwing(this, "com.ibm.mq.jmqi.JmqiXAResource", "prepare(Xid)", e);
/*      */       }
/*  741 */       throw e;
/*      */     } 
/*      */     
/*  744 */     if (Trace.isOn) {
/*  745 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiXAResource", "prepare(Xid)", 
/*  746 */           Integer.valueOf(returncode));
/*      */     }
/*  748 */     return returncode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Xid[] recover(int flags) throws XAException {
/*      */     List<Xid> list;
/*  757 */     if (Trace.isOn) {
/*  758 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiXAResource", "recover(int)", new Object[] {
/*  759 */             Integer.valueOf(flags)
/*      */           });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  768 */     int returncode = 0;
/*  769 */     String msg = null;
/*  770 */     boolean doScan = false;
/*      */ 
/*      */     
/*  773 */     synchronized (this.syncLock) {
/*  774 */       if (this.isClosed) {
/*  775 */         returncode = -7;
/*  776 */         String[] inserts = { "xa_recover", Integer.toString(returncode) };
/*  777 */         msg = JmqiResource.getString("MQJE118", inserts);
/*      */       
/*      */       }
/*  780 */       else if (flags == 16777216) {
/*      */         
/*  782 */         doScan = true;
/*  783 */         this.rScanInProgress = true;
/*      */       }
/*  785 */       else if (flags == 25165824) {
/*      */         
/*  787 */         doScan = true;
/*  788 */         this.rScanInProgress = false;
/*      */       }
/*  790 */       else if (flags == 0) {
/*      */ 
/*      */         
/*  793 */         doScan = false;
/*      */         
/*  795 */         if (!this.rScanInProgress) {
/*  796 */           if (Trace.isOn) {
/*  797 */             Trace.data(this, "recover(int)", "TMNOFLAGS when no scan marked in-progress");
/*      */           }
/*      */           
/*  800 */           returncode = -5;
/*      */         }
/*      */       
/*  803 */       } else if (flags == 8388608) {
/*      */         
/*  805 */         doScan = false;
/*  806 */         if (!this.rScanInProgress) {
/*  807 */           if (Trace.isOn) {
/*  808 */             Trace.data(this, "recover(int)", "TMENDRSCAN when no scan marked in-progress");
/*      */           }
/*      */           
/*  811 */           returncode = -5;
/*      */         }
/*      */         else {
/*      */           
/*  815 */           this.rScanInProgress = false;
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  820 */         doScan = false;
/*  821 */         if (Trace.isOn) {
/*  822 */           Trace.data(this, "recover(int)", "Flag set unsupported: 0x" + Integer.toHexString(flags));
/*      */         }
/*  824 */         returncode = -5;
/*      */       } 
/*  826 */       list = new ArrayList<>();
/*  827 */       if (doScan) {
/*      */ 
/*      */ 
/*      */         
/*  831 */         int xa_flags = 16777216;
/*  832 */         int limit = 15;
/*  833 */         Xid[] xids = new Xid[limit];
/*  834 */         boolean more = true;
/*      */         
/*  836 */         while (more) {
/*  837 */           returncode = this.xa.xa_recover(this.hconn, xids, this.rmid, xa_flags);
/*  838 */           for (int j = 0; j < returncode; j++) {
/*  839 */             list.add(xids[j]);
/*      */           }
/*  841 */           if (returncode <= 0) {
/*  842 */             more = false; continue;
/*      */           } 
/*  844 */           if (returncode < limit) {
/*  845 */             more = false;
/*      */             continue;
/*      */           } 
/*  848 */           xa_flags = 0;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  854 */     if (returncode < 0) {
/*  855 */       if (msg == null) {
/*  856 */         String[] inserts = { "xa_recover", Integer.toString(returncode) };
/*      */         
/*  858 */         msg = JmqiResource.getString("MQJE114", inserts);
/*      */       } 
/*  860 */       XAException e = new XAException(msg);
/*  861 */       e.errorCode = returncode;
/*      */       
/*  863 */       if (Trace.isOn) {
/*  864 */         Trace.throwing(this, "com.ibm.mq.jmqi.JmqiXAResource", "recover(int)", e);
/*      */       }
/*  866 */       throw e;
/*      */     } 
/*      */ 
/*      */     
/*  870 */     Xid[] array = null;
/*  871 */     array = new Xid[list.size()];
/*  872 */     for (int i = 0; i < list.size(); i++) {
/*  873 */       array[i] = list.get(i);
/*      */     }
/*      */     
/*  876 */     if (Trace.isOn) {
/*  877 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiXAResource", "recover(int)", array);
/*      */     }
/*  879 */     return array;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void rollback(Xid xid) throws XAException {
/*  888 */     if (Trace.isOn) {
/*  889 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiXAResource", "rollback(Xid)", new Object[] { xid });
/*      */     }
/*      */     
/*      */     try {
/*  893 */       int returncode, flags = 0;
/*      */       
/*  895 */       String msg = null;
/*  896 */       synchronized (this.syncLock) {
/*  897 */         if (this.isClosed) {
/*      */ 
/*      */           
/*  900 */           returncode = -7;
/*  901 */           String[] inserts = { "xa_rollback", Integer.toString(returncode) };
/*  902 */           msg = JmqiResource.getString("MQJE118", inserts);
/*      */         }
/*      */         else {
/*      */           
/*  906 */           returncode = this.xa.xa_rollback(this.hconn, xid, this.rmid, flags);
/*      */         } 
/*      */       } 
/*      */       
/*  910 */       if (returncode != 0) {
/*  911 */         if (msg == null) {
/*  912 */           String[] inserts = { "xa_rollback", Integer.toString(returncode) };
/*      */           
/*  914 */           msg = JmqiResource.getString("MQJE114", inserts);
/*      */         } 
/*  916 */         XAException e = new XAException(msg);
/*  917 */         e.errorCode = returncode;
/*      */         
/*  919 */         if (Trace.isOn) {
/*  920 */           Trace.throwing(this, "com.ibm.mq.jmqi.JmqiXAResource", "rollback(Xid)", e);
/*      */         }
/*  922 */         throw e;
/*      */       } 
/*      */     } finally {
/*      */       
/*  926 */       if (Trace.isOn) {
/*  927 */         Trace.exit(this, "com.ibm.mq.jmqi.JmqiXAResource", "rollback(Xid)");
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean setTransactionTimeout(int seconds) throws XAException {
/*  940 */     if (Trace.isOn)
/*  941 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiXAResource", "setTransactionTimeout(int)", new Object[] {
/*  942 */             Integer.valueOf(seconds)
/*      */           }); 
/*  944 */     if (Trace.isOn) {
/*  945 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiXAResource", "setTransactionTimeout(int)", 
/*  946 */           Boolean.valueOf(false));
/*      */     }
/*  948 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void start(Xid xid, int flags) throws XAException {
/*      */     int returncode;
/*  957 */     if (Trace.isOn) {
/*  958 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiXAResource", "start(Xid,int)", new Object[] { xid, 
/*  959 */             Integer.valueOf(flags) });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  987 */     String msg = null;
/*      */ 
/*      */ 
/*      */     
/*  991 */     synchronized (this.syncLock) {
/*      */       
/*  993 */       if (this.isClosed) {
/*  994 */         returncode = -7;
/*  995 */         String[] inserts = { "xa_start", Integer.toString(returncode) };
/*  996 */         msg = JmqiResource.getString("MQJE118", inserts);
/*      */       }
/*      */       else {
/*      */         
/* 1000 */         returncode = this.xa.xa_start(this.hconn, xid, this.rmid, flags);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1007 */       if (returncode == 0) {
/* 1008 */         this.isActive = true;
/* 1009 */         this.inProgressXid = xid;
/*      */       } 
/*      */     } 
/*      */     
/* 1013 */     if (returncode != 0) {
/* 1014 */       if (msg == null) {
/* 1015 */         String[] inserts = { "xa_start", Integer.toString(returncode) };
/* 1016 */         msg = JmqiResource.getString("MQJE114", inserts);
/*      */       } 
/*      */       
/* 1019 */       XAException e = new XAException(msg);
/* 1020 */       e.errorCode = returncode;
/*      */       
/* 1022 */       if (Trace.isOn) {
/* 1023 */         Trace.throwing(this, "com.ibm.mq.jmqi.JmqiXAResource", "start(Xid,int)", e);
/*      */       }
/* 1025 */       throw e;
/*      */     } 
/* 1027 */     if (Trace.isOn)
/* 1028 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiXAResource", "start(Xid,int)"); 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\JmqiXAResource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */