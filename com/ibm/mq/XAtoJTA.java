/*      */ package com.ibm.mq;
/*      */ 
/*      */ import com.ibm.mq.internal.MQCommonServices;
/*      */ import com.ibm.mq.internal.MQThreadLocalStorage;
/*      */ import com.ibm.mq.jmqi.JmqiObject;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.lang.reflect.Constructor;
/*      */ import java.security.AccessControlException;
/*      */ import java.security.AccessController;
/*      */ import java.security.PrivilegedAction;
/*      */ import java.sql.SQLException;
/*      */ import java.util.ArrayDeque;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Deque;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import javax.sql.ConnectionEvent;
/*      */ import javax.sql.ConnectionEventListener;
/*      */ import javax.sql.XAConnection;
/*      */ import javax.sql.XADataSource;
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
/*      */ public class XAtoJTA
/*      */   extends JmqiObject
/*      */   implements ConnectionEventListener
/*      */ {
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/XAtoJTA.java";
/*      */   private static final int INITIAL_BEGINRC = 2121;
/*      */   public static final int TMNOFLAGS = 0;
/*      */   public static final int XA_NOMIGRATE = 9;
/*      */   public static final int XA_HEURHAZ = 8;
/*      */   public static final int XA_HEURCOM = 7;
/*      */   public static final int XA_HEURRB = 6;
/*      */   public static final int XA_HEURMIX = 5;
/*      */   public static final int XA_RETRY = 4;
/*      */   public static final int XA_RDONLY = 3;
/*      */   public static final int XA_OK = 0;
/*      */   public static final int XAER_ASYNC = -2;
/*      */   public static final int XAER_RMERR = -3;
/*      */   private static boolean useOra816;
/*      */   
/*      */   static {
/*  101 */     if (Trace.isOn) {
/*  102 */       Trace.data("com.ibm.mq.XAtoJTA", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/XAtoJTA.java");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static class RR
/*      */   {
/*      */     public XAResource res;
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
/*      */     public int rmid;
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
/*      */     public Xid xid;
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
/*      */     private RR() {}
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
/*      */ 
/*      */ 
/*      */   
/*  203 */   private Map<Integer, RR> resourcesByRmid = new HashMap<>(10);
/*      */ 
/*      */ 
/*      */   
/*  207 */   private Map<XAConnection, RR> resourcesByConn = new HashMap<>(10);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  217 */   private Map<Integer, RR> spareRmids = new HashMap<>();
/*      */   
/*  219 */   private Deque<XAConnection> spareXAConns = new ArrayDeque<>();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  224 */   private List<XAResource> dirtyResources = new ArrayList<>();
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean inTransaction = false;
/*      */ 
/*      */   
/*  231 */   private static int XATransactionTimeout = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int beginRC;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static {
/*  247 */     String ora816fix = AccessController.<String>doPrivileged(new PrivilegedAction<String>()
/*      */         {
/*      */           
/*      */           public String run()
/*      */           {
/*      */             try {
/*  253 */               return System.getProperty("MQ_JDBC_ORA816");
/*      */             }
/*  255 */             catch (AccessControlException ace) {
/*  256 */               return null;
/*      */             } 
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  264 */     if (ora816fix != null) {
/*  265 */       useOra816 = true;
/*      */     } else {
/*      */       
/*  268 */       useOra816 = false;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  273 */     Integer XATransact = AccessController.<Integer>doPrivileged(new PrivilegedAction<Integer>()
/*      */         {
/*      */ 
/*      */ 
/*      */           
/*      */           public Integer run()
/*      */           {
/*      */             try {
/*  281 */               return 
/*  282 */                 Integer.getInteger("com.ibm.mq.jdbc.XATransactionTimeoutPeriod");
/*      */             }
/*  284 */             catch (AccessControlException ace) {
/*  285 */               return null;
/*      */             } 
/*      */           }
/*      */         });
/*      */     
/*  290 */     if (XATransact != null) {
/*  291 */       XATransactionTimeout = XATransact.intValue();
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
/*      */   private XAtoJTA() {
/*  303 */     super(MQSESSION.getJmqiEnv());
/*  304 */     if (Trace.isOn) {
/*  305 */       Trace.entry(this, "com.ibm.mq.XAtoJTA", "<init>()");
/*      */     }
/*  307 */     if (Trace.isOn) {
/*  308 */       Trace.data(this, "value of XATransactionTimeout: ", 
/*  309 */           Integer.valueOf(XATransactionTimeout));
/*      */     }
/*  311 */     if (Trace.isOn) {
/*  312 */       Trace.exit(this, "com.ibm.mq.XAtoJTA", "<init>()");
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
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized void connectionClosed(ConnectionEvent event) {
/*  331 */     if (Trace.isOn) {
/*  332 */       Trace.entry(this, "com.ibm.mq.XAtoJTA", "connectionClosed(javax.sql.ConnectionEvent)", new Object[] { event });
/*      */     }
/*      */     
/*  335 */     String fid = "connectionClosed(javax.sql.ConnectionEvent)";
/*      */     try {
/*  337 */       if (useOra816) {
/*  338 */         if (Trace.isOn) {
/*  339 */           Trace.data(this, "connectionClosed(javax.sql.ConnectionEvent)", "ignoring event because useOra816 flag is set", "");
/*      */         }
/*      */       }
/*      */       else {
/*      */         
/*  344 */         XAConnection conn = (XAConnection)event.getSource();
/*  345 */         _deregisterResource(conn);
/*      */ 
/*      */         
/*  348 */         conn.close();
/*      */       }
/*      */     
/*  351 */     } catch (Exception e) {
/*  352 */       if (Trace.isOn) {
/*  353 */         Trace.catchBlock(this, "com.ibm.mq.XAtoJTA", "connectionClosed(javax.sql.ConnectionEvent)", e);
/*      */       
/*      */       }
/*      */     
/*      */     }
/*      */     finally {
/*      */ 
/*      */       
/*  361 */       if (Trace.isOn) {
/*  362 */         Trace.finallyBlock(this, "com.ibm.mq.XAtoJTA", "connectionClosed(javax.sql.ConnectionEvent)");
/*      */       }
/*      */     } 
/*      */     
/*  366 */     if (Trace.isOn) {
/*  367 */       Trace.exit(this, "com.ibm.mq.XAtoJTA", "connectionClosed(javax.sql.ConnectionEvent)");
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
/*      */   public synchronized void connectionErrorOccurred(ConnectionEvent event) {
/*  383 */     if (Trace.isOn) {
/*  384 */       Trace.entry(this, "com.ibm.mq.XAtoJTA", "connectionErrorOccurred(javax.sql.ConnectionEvent)", new Object[] { event });
/*      */     }
/*  386 */     if (Trace.isOn) {
/*  387 */       Trace.exit(this, "com.ibm.mq.XAtoJTA", "connectionErrorOccurred(javax.sql.ConnectionEvent)");
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
/*      */   protected static XAtoJTA getThreadInstance() {
/*  416 */     MQThreadLocalStorage tls = MQCommonServices.getTls();
/*  417 */     XAtoJTA instance = tls.getXaToJta();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  422 */     if (instance == null) {
/*  423 */       instance = new XAtoJTA();
/*      */     }
/*  425 */     tls.setXaToJta(instance);
/*      */     
/*  427 */     if (Trace.isOn) {
/*  428 */       Trace.data("com.ibm.mq.XAtoJTA", "getThreadInstance()", "getter", instance);
/*      */     }
/*  430 */     return instance;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static XAConnection createRegisteredConnection(XADataSource xads, String user, String password) throws MQException, SQLException, XAException {
/*  456 */     if (Trace.isOn) {
/*  457 */       Trace.entry("com.ibm.mq.XAtoJTA", "createRegisteredConnection(XADataSource,String,String)", new Object[] { xads, user, (password == null) ? password : 
/*      */             
/*  459 */             Integer.valueOf(password.length()) });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  466 */     XAConnection xaconn = null;
/*  467 */     if (user != null) {
/*  468 */       xaconn = xads.getXAConnection(user, password);
/*      */     } else {
/*      */       
/*  471 */       xaconn = xads.getXAConnection();
/*      */     } 
/*      */     
/*  474 */     XAtoJTA instance = getThreadInstance();
/*  475 */     instance._registerResource(xaconn);
/*      */     
/*  477 */     if (Trace.isOn) {
/*  478 */       Trace.exit("com.ibm.mq.XAtoJTA", "createRegisteredConnection(XADataSource,String,String)", xaconn);
/*      */     }
/*      */ 
/*      */     
/*  482 */     return xaconn;
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
/*      */   public static void deregisterResource(XAConnection xaconn) throws XAException {
/*  494 */     if (Trace.isOn) {
/*  495 */       Trace.entry("com.ibm.mq.XAtoJTA", "deregisterResource(XAConnection)", new Object[] { xaconn });
/*      */     }
/*      */     
/*  498 */     XAtoJTA instance = getThreadInstance();
/*  499 */     instance._deregisterResource(xaconn);
/*      */     
/*  501 */     if (Trace.isOn) {
/*  502 */       Trace.exit("com.ibm.mq.XAtoJTA", "deregisterResource(XAConnection)");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static synchronized int XAOpen(String openString, int rmid, int flags, int pid) {
/*  527 */     if (Trace.isOn) {
/*  528 */       Trace.entry("com.ibm.mq.XAtoJTA", "XAOpen(String,int,int,int)", new Object[] { openString, 
/*  529 */             Integer.valueOf(rmid), Integer.valueOf(flags), Integer.valueOf(pid) });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  534 */     XAtoJTA instance = getThreadInstance();
/*  535 */     if (instance == null) {
/*  536 */       if (Trace.isOn) {
/*  537 */         Trace.exit("com.ibm.mq.XAtoJTA", "XAOpen(String,int,int,int)", Integer.valueOf(-3), 1);
/*      */       }
/*  539 */       return -3;
/*      */     } 
/*      */ 
/*      */     
/*  543 */     int traceRet1 = instance.xa_open(rmid, flags);
/*  544 */     if (Trace.isOn) {
/*  545 */       Trace.exit("com.ibm.mq.XAtoJTA", "XAOpen(String,int,int,int)", Integer.valueOf(traceRet1), 2);
/*      */     }
/*  547 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static synchronized int XAClose(String closeString, int rmid, int flags) {
/*  555 */     if (Trace.isOn) {
/*  556 */       Trace.entry("com.ibm.mq.XAtoJTA", "XAClose(String,int,int)", new Object[] { closeString, 
/*  557 */             Integer.valueOf(rmid), Integer.valueOf(flags) });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  562 */     XAtoJTA instance = getThreadInstance();
/*  563 */     int traceRet1 = instance.xa_close(rmid, flags);
/*      */     
/*  565 */     if (Trace.isOn) {
/*  566 */       Trace.exit("com.ibm.mq.XAtoJTA", "XAClose(String,int,int)", Integer.valueOf(traceRet1), 2);
/*      */     }
/*  568 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static synchronized int XAStart(byte[] gtrid, byte[] bqual, int format, int rmid, int flags) {
/*  577 */     if (Trace.isOn) {
/*  578 */       Trace.entry("com.ibm.mq.XAtoJTA", "XAStart(byte [ ],byte [ ],int,int,int)", new Object[] { gtrid, bqual, 
/*  579 */             Integer.valueOf(format), Integer.valueOf(rmid), Integer.valueOf(flags) });
/*      */     }
/*      */ 
/*      */     
/*  583 */     XAtoJTA instance = getThreadInstance();
/*  584 */     int traceRet1 = instance.xa_start(gtrid, bqual, format, rmid, flags);
/*      */     
/*  586 */     if (Trace.isOn) {
/*  587 */       Trace.exit("com.ibm.mq.XAtoJTA", "XAStart(byte [ ],byte [ ],int,int,int)", 
/*  588 */           Integer.valueOf(traceRet1), 2);
/*      */     }
/*  590 */     return traceRet1;
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
/*      */   public static synchronized int XAEnd(byte[] gtrid, byte[] bqual, int format, int rmid, int flags) {
/*  607 */     if (Trace.isOn) {
/*  608 */       Trace.entry("com.ibm.mq.XAtoJTA", "XAEnd(byte [ ],byte [ ],int,int,int)", new Object[] { gtrid, bqual, 
/*  609 */             Integer.valueOf(format), Integer.valueOf(rmid), Integer.valueOf(flags) });
/*      */     }
/*      */ 
/*      */     
/*  613 */     XAtoJTA instance = getThreadInstance();
/*  614 */     int traceRet1 = instance.xa_end(gtrid, bqual, format, rmid, flags);
/*      */     
/*  616 */     if (Trace.isOn) {
/*  617 */       Trace.exit("com.ibm.mq.XAtoJTA", "XAEnd(byte [ ],byte [ ],int,int,int)", 
/*  618 */           Integer.valueOf(traceRet1), 2);
/*      */     }
/*  620 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static synchronized int XARollback(byte[] gtrid, byte[] bqual, int format, int rmid, int flags) {
/*  627 */     if (Trace.isOn) {
/*  628 */       Trace.entry("com.ibm.mq.XAtoJTA", "XARollback(byte [ ],byte [ ],int,int,int)", new Object[] { gtrid, bqual, 
/*  629 */             Integer.valueOf(format), Integer.valueOf(rmid), Integer.valueOf(flags) });
/*      */     }
/*      */ 
/*      */     
/*  633 */     XAtoJTA instance = getThreadInstance();
/*  634 */     int traceRet1 = instance.xa_rollback(gtrid, bqual, format, rmid, flags);
/*      */     
/*  636 */     if (Trace.isOn) {
/*  637 */       Trace.exit("com.ibm.mq.XAtoJTA", "XARollback(byte [ ],byte [ ],int,int,int)", 
/*  638 */           Integer.valueOf(traceRet1), 2);
/*      */     }
/*  640 */     return traceRet1;
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
/*      */   private synchronized int xa_open(int rmid, int flags) {
/*  676 */     if (Trace.isOn)
/*  677 */       Trace.entry(this, "com.ibm.mq.XAtoJTA", "xa_open(int,int)", new Object[] {
/*  678 */             Integer.valueOf(rmid), Integer.valueOf(flags)
/*      */           }); 
/*  680 */     String fid = "xa_open(int,int)";
/*  681 */     int xa_rc = -3;
/*      */     
/*      */     try {
/*  684 */       RR rr = new RR();
/*  685 */       rr.rmid = rmid;
/*  686 */       this.spareRmids.put(Integer.valueOf(rmid), rr);
/*  687 */       xa_rc = 0;
/*      */     }
/*  689 */     catch (Exception e) {
/*  690 */       if (Trace.isOn) {
/*  691 */         Trace.catchBlock(this, "com.ibm.mq.XAtoJTA", "xa_open(int,int)", e);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  697 */     if (Trace.isOn) {
/*  698 */       Trace.exit(this, "com.ibm.mq.XAtoJTA", "xa_open(int,int)", Integer.valueOf(xa_rc));
/*      */     }
/*  700 */     return xa_rc;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private synchronized int xa_close(int rmid, int flags) {
/*  708 */     if (Trace.isOn)
/*  709 */       Trace.entry(this, "com.ibm.mq.XAtoJTA", "xa_close(int,int)", new Object[] {
/*  710 */             Integer.valueOf(rmid), Integer.valueOf(flags)
/*      */           }); 
/*  712 */     int xa_rc = -3;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  719 */     for (XAConnection con : this.spareXAConns) {
/*  720 */       if (Trace.isOn) {
/*  721 */         Trace.data(this, "xa_close(int,int)", "Closing spare XAConnection", con);
/*      */       }
/*      */       try {
/*  724 */         con.removeConnectionEventListener(this);
/*      */       }
/*  726 */       catch (Exception e) {
/*  727 */         if (Trace.isOn) {
/*  728 */           Trace.catchBlock(this, "com.ibm.mq.XAtoJTA", "xa_close(int,int)", e, 1);
/*      */         }
/*      */       } 
/*      */       
/*      */       try {
/*  733 */         con.close();
/*      */       }
/*  735 */       catch (SQLException e) {
/*  736 */         if (Trace.isOn) {
/*  737 */           Trace.catchBlock(this, "com.ibm.mq.XAtoJTA", "xa_close(int,int)", e, 2);
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  743 */     for (XAConnection con : this.resourcesByConn.keySet()) {
/*  744 */       if (Trace.isOn) {
/*  745 */         Trace.data(this, "xa_close(int,int)", "Closing XAConnection associated with an RR", con);
/*      */       }
/*      */       try {
/*  748 */         con.close();
/*      */       }
/*  750 */       catch (SQLException e) {
/*  751 */         if (Trace.isOn) {
/*  752 */           Trace.catchBlock(this, "com.ibm.mq.XAtoJTA", "xa_close(int,int)", e, 3);
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  757 */     this.spareRmids.clear();
/*  758 */     this.spareXAConns.clear();
/*  759 */     this.dirtyResources.clear();
/*  760 */     this.resourcesByRmid.clear();
/*  761 */     this.resourcesByConn.clear();
/*      */     
/*  763 */     xa_rc = 0;
/*      */     
/*  765 */     if (Trace.isOn) {
/*  766 */       Trace.exit(this, "com.ibm.mq.XAtoJTA", "xa_close(int,int)", Integer.valueOf(xa_rc));
/*      */     }
/*  768 */     return xa_rc;
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private synchronized int xa_start(byte[] gtrid, byte[] bqual, int format, int rmid, int flags) {
/*  789 */     if (Trace.isOn) {
/*  790 */       Trace.entry(this, "com.ibm.mq.XAtoJTA", "xa_start(byte [ ],byte [ ],int,int,int)", new Object[] { gtrid, bqual, 
/*  791 */             Integer.valueOf(format), Integer.valueOf(rmid), 
/*  792 */             Integer.valueOf(flags) });
/*      */     }
/*  794 */     String fid = "xa_start(byte [ ],byte [ ],int,int,int)";
/*      */     
/*  796 */     int xa_rc = -3;
/*      */     
/*      */     try {
/*      */       Xid newXID;
/*      */       
/*  801 */       if (this.beginRC == 2121) {
/*  802 */         if (Trace.isOn) {
/*  803 */           Trace.data(this, "xa_start(byte [ ],byte [ ],int,int,int)", "resetting beginRc to MQRC_NONE", "");
/*      */         }
/*  805 */         this.beginRC = 0;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  811 */       if (!this.inTransaction && 
/*  812 */         this.spareXAConns.size() > this.spareRmids.size()) {
/*  813 */         if (Trace.isOn) {
/*  814 */           Trace.data(this, "xa_start(byte [ ],byte [ ],int,int,int)", "not enough rmids for registered connections", "");
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  820 */         this.beginRC = 2372;
/*      */ 
/*      */ 
/*      */         
/*  824 */         if (Trace.isOn) {
/*  825 */           Trace.exit(this, "com.ibm.mq.XAtoJTA", "xa_start(byte [ ],byte [ ],int,int,int)", 
/*  826 */               Integer.valueOf(-3), 1);
/*      */         }
/*  828 */         return -3;
/*      */       } 
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
/*  844 */       if (flags != 0) {
/*  845 */         if (Trace.isOn) {
/*  846 */           Trace.data(this, "xa_start(byte [ ],byte [ ],int,int,int)", "unexpected value of flags: ", flags + ", returning RMERR");
/*      */         }
/*  848 */         if (Trace.isOn) {
/*  849 */           Trace.exit(this, "com.ibm.mq.XAtoJTA", "xa_start(byte [ ],byte [ ],int,int,int)", 
/*  850 */               Integer.valueOf(-3), 2);
/*      */         }
/*  852 */         return -3;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  859 */       this.inTransaction = true;
/*      */       
/*  861 */       Integer rmId = Integer.valueOf(rmid);
/*      */ 
/*      */ 
/*      */       
/*  865 */       if (useOra816) {
/*      */ 
/*      */         
/*      */         try {
/*      */           
/*  870 */           if (Trace.isOn) {
/*  871 */             Trace.data(this, "xa_start(byte [ ],byte [ ],int,int,int)", "creating oracle xid", "");
/*      */           }
/*      */           
/*  874 */           Class<?> oraXidClass = Class.forName("oracle.jdbc.xa.OracleXid");
/*  875 */           Class<?>[] paramTypes = new Class[] { int.class, byte[].class, byte[].class };
/*      */           
/*  877 */           Constructor<?> con = oraXidClass.getConstructor(paramTypes);
/*  878 */           Object[] params = { Integer.valueOf(format), gtrid, bqual };
/*  879 */           newXID = (Xid)con.newInstance(params);
/*      */         
/*      */         }
/*  882 */         catch (Exception e) {
/*  883 */           if (Trace.isOn) {
/*  884 */             Trace.catchBlock(this, "com.ibm.mq.XAtoJTA", "xa_start(byte [ ],byte [ ],int,int,int)", e, 1);
/*      */           }
/*      */           
/*  887 */           if (Trace.isOn) {
/*  888 */             Trace.exit(this, "com.ibm.mq.XAtoJTA", "xa_start(byte [ ],byte [ ],int,int,int)", 
/*  889 */                 Integer.valueOf(-3), 3);
/*      */           }
/*  891 */           return -3;
/*      */         }
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  897 */         if (Trace.isOn) {
/*  898 */           Trace.data(this, "xa_start(byte [ ],byte [ ],int,int,int)", "creating a standard xid", "");
/*      */         }
/*      */ 
/*      */         
/*  902 */         newXID = new MQXid(format, gtrid, bqual);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  908 */       RR res = this.resourcesByRmid.get(rmId);
/*      */       
/*  910 */       if (Trace.isOn) {
/*  911 */         Trace.data(this, "xa_start(byte [ ],byte [ ],int,int,int)", "RR", 
/*  912 */             String.valueOf(res));
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  917 */       if (res == null) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  922 */         if (this.spareXAConns.isEmpty()) {
/*  923 */           if (Trace.isOn) {
/*  924 */             Trace.data(this, "xa_start(byte [ ],byte [ ],int,int,int)", "no connection available for rmid ", rmid + ", must be spare");
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  929 */           RR rr = this.spareRmids.get(rmId);
/*  930 */           if (rr == null) {
/*      */             
/*  932 */             if (Trace.isOn) {
/*  933 */               Trace.data(this, "xa_start(byte [ ],byte [ ],int,int,int)", "problem: rmId ", rmId + " missing from the spareRmids list - returning error");
/*      */             }
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  939 */             if (Trace.isOn) {
/*  940 */               Trace.exit(this, "com.ibm.mq.XAtoJTA", "xa_start(byte [ ],byte [ ],int,int,int)", 
/*  941 */                   Integer.valueOf(-3), 4);
/*      */             }
/*  943 */             return -3;
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/*  948 */           rr.xid = newXID;
/*      */           
/*  950 */           if (Trace.isOn) {
/*  951 */             Trace.exit(this, "com.ibm.mq.XAtoJTA", "xa_start(byte [ ],byte [ ],int,int,int)", 
/*  952 */                 Integer.valueOf(0), 5);
/*      */           }
/*  954 */           return 0;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  964 */         XAConnection con = this.spareXAConns.removeLast();
/*      */ 
/*      */         
/*  967 */         res = this.spareRmids.remove(rmId);
/*  968 */         if (res == null) {
/*      */           
/*  970 */           if (Trace.isOn) {
/*  971 */             Trace.data(this, "xa_start(byte [ ],byte [ ],int,int,int)", "can't find rmid on spareRmids list (2nd position)", 
/*      */ 
/*      */ 
/*      */                 
/*  975 */                 Integer.toString(rmid));
/*      */           }
/*  977 */           if (Trace.isOn) {
/*  978 */             Trace.exit(this, "com.ibm.mq.XAtoJTA", "xa_start(byte [ ],byte [ ],int,int,int)", 
/*  979 */                 Integer.valueOf(-3), 6);
/*      */           }
/*  981 */           return -3;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  986 */         res.res = con.getXAResource();
/*  987 */         res.rmid = rmid;
/*      */ 
/*      */ 
/*      */         
/*  991 */         this.resourcesByRmid.put(rmId, res);
/*  992 */         this.resourcesByConn.put(con, res);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  998 */       XAResource xares = res.res;
/*      */       
/* 1000 */       if (Trace.isOn) {
/* 1001 */         Trace.data(this, "xa_start(byte [ ],byte [ ],int,int,int)", "xaRes", 
/* 1002 */             String.valueOf(xares));
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1008 */       res.xid = newXID;
/*      */ 
/*      */       
/* 1011 */       if (Trace.isOn) {
/* 1012 */         Trace.data(this, "xa_start(byte [ ],byte [ ],int,int,int)", "XAStart, xid '" + newXID + "', flags " + flags, "");
/*      */       }
/* 1014 */       xares.start(newXID, flags);
/*      */       
/* 1016 */       xa_rc = 0;
/*      */     
/*      */     }
/* 1019 */     catch (XAException e) {
/* 1020 */       if (Trace.isOn) {
/* 1021 */         Trace.catchBlock(this, "com.ibm.mq.XAtoJTA", "xa_start(byte [ ],byte [ ],int,int,int)", e, 2);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1028 */       xa_rc = e.errorCode;
/*      */     
/*      */     }
/* 1031 */     catch (Exception e) {
/* 1032 */       if (Trace.isOn) {
/* 1033 */         Trace.catchBlock(this, "com.ibm.mq.XAtoJTA", "xa_start(byte [ ],byte [ ],int,int,int)", e, 3);
/*      */       }
/*      */ 
/*      */       
/* 1037 */       xa_rc = -3;
/*      */     }
/*      */     finally {
/*      */       
/* 1041 */       if (Trace.isOn) {
/* 1042 */         Trace.finallyBlock(this, "com.ibm.mq.XAtoJTA", "xa_start(byte [ ],byte [ ],int,int,int)");
/*      */       }
/*      */     } 
/*      */     
/* 1046 */     if (Trace.isOn) {
/* 1047 */       Trace.exit(this, "com.ibm.mq.XAtoJTA", "xa_start(byte [ ],byte [ ],int,int,int)", 
/* 1048 */           Integer.valueOf(xa_rc), 7);
/*      */     }
/* 1050 */     return xa_rc;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private synchronized int xa_end(byte[] gtrid, byte[] bqual, int format, int rmid, int flags) {
/* 1059 */     if (Trace.isOn) {
/* 1060 */       Trace.entry(this, "com.ibm.mq.XAtoJTA", "xa_end(byte [ ],byte [ ],int,int,int)", new Object[] { gtrid, bqual, 
/* 1061 */             Integer.valueOf(format), Integer.valueOf(rmid), 
/* 1062 */             Integer.valueOf(flags) });
/*      */     }
/* 1064 */     String fid = "xa_end(byte [ ],byte [ ],int,int,int)";
/* 1065 */     int xa_rc = -3;
/*      */     try {
/* 1067 */       this.inTransaction = false;
/*      */       
/* 1069 */       this.dirtyResources.clear();
/* 1070 */       Integer rmId = Integer.valueOf(rmid);
/*      */       
/* 1072 */       RR rr = this.resourcesByRmid.get(rmId);
/* 1073 */       if (rr == null) {
/*      */         
/* 1075 */         if (Trace.isOn) {
/* 1076 */           Trace.data(this, "xa_end(byte [ ],byte [ ],int,int,int)", "rmid not assigned", "");
/*      */         }
/*      */         
/* 1079 */         if (Trace.isOn) {
/* 1080 */           Trace.exit(this, "com.ibm.mq.XAtoJTA", "xa_end(byte [ ],byte [ ],int,int,int)", 
/* 1081 */               Integer.valueOf(0), 1);
/*      */         }
/* 1083 */         return 0;
/*      */       } 
/*      */ 
/*      */       
/* 1087 */       XAResource xares = rr.res;
/*      */ 
/*      */       
/* 1090 */       if (xares == null) {
/* 1091 */         if (Trace.isOn) {
/* 1092 */           Trace.data(this, "xa_end(byte [ ],byte [ ],int,int,int)", "odd: assigned rmid with null XAResouce", "");
/*      */         }
/*      */         
/* 1095 */         if (Trace.isOn) {
/* 1096 */           Trace.exit(this, "com.ibm.mq.XAtoJTA", "xa_end(byte [ ],byte [ ],int,int,int)", 
/* 1097 */               Integer.valueOf(0), 2);
/*      */         }
/* 1099 */         return 0;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1104 */       Xid xid = rr.xid;
/*      */       
/* 1106 */       if (xid == null) {
/* 1107 */         if (Trace.isOn) {
/* 1108 */           Trace.data(this, "xa_end(byte [ ],byte [ ],int,int,int)", "odd: assigned rmid with null xid", "");
/*      */         }
/*      */         
/* 1111 */         if (Trace.isOn) {
/* 1112 */           Trace.exit(this, "com.ibm.mq.XAtoJTA", "xa_end(byte [ ],byte [ ],int,int,int)", 
/* 1113 */               Integer.valueOf(0), 3);
/*      */         }
/* 1115 */         return 0;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1120 */       if (Trace.isOn) {
/* 1121 */         Trace.data(this, "xa_end(byte [ ],byte [ ],int,int,int)", "xid is '" + xid + "', flags are " + flags, "");
/* 1122 */         Trace.data(this, "xa_end(byte [ ],byte [ ],int,int,int)", "xares is ", String.valueOf(xares));
/*      */       } 
/* 1124 */       xares.end(xid, flags);
/*      */       
/* 1126 */       xa_rc = 0;
/*      */     
/*      */     }
/* 1129 */     catch (XAException e) {
/* 1130 */       if (Trace.isOn) {
/* 1131 */         Trace.catchBlock(this, "com.ibm.mq.XAtoJTA", "xa_end(byte [ ],byte [ ],int,int,int)", e, 1);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1140 */       xa_rc = e.errorCode;
/*      */     
/*      */     }
/* 1143 */     catch (Exception e) {
/* 1144 */       if (Trace.isOn) {
/* 1145 */         Trace.catchBlock(this, "com.ibm.mq.XAtoJTA", "xa_end(byte [ ],byte [ ],int,int,int)", e, 2);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1150 */       xa_rc = -3;
/*      */     }
/*      */     finally {
/*      */       
/* 1154 */       if (Trace.isOn) {
/* 1155 */         Trace.finallyBlock(this, "com.ibm.mq.XAtoJTA", "xa_end(byte [ ],byte [ ],int,int,int)");
/*      */       }
/*      */     } 
/*      */     
/* 1159 */     if (Trace.isOn) {
/* 1160 */       Trace.exit(this, "com.ibm.mq.XAtoJTA", "xa_end(byte [ ],byte [ ],int,int,int)", 
/* 1161 */           Integer.valueOf(xa_rc), 4);
/*      */     }
/* 1163 */     return xa_rc;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private synchronized int xa_rollback(byte[] gtrid, byte[] bqual, int format, int rmid, int flags) {
/* 1171 */     if (Trace.isOn) {
/* 1172 */       Trace.entry(this, "com.ibm.mq.XAtoJTA", "xa_rollback(byte [ ],byte [ ],int,int,int)", new Object[] { gtrid, bqual, 
/* 1173 */             Integer.valueOf(format), Integer.valueOf(rmid), 
/* 1174 */             Integer.valueOf(flags) });
/*      */     }
/* 1176 */     String fid = "xa_rollback(byte [ ],byte [ ],int,int,int)";
/*      */     
/* 1178 */     int xa_rc = -3;
/*      */     try {
/*      */       Xid newXID;
/* 1181 */       if (flags != 0) {
/* 1182 */         if (Trace.isOn) {
/* 1183 */           Trace.data(this, "xa_rollback(byte [ ],byte [ ],int,int,int)", "unexpected value of flags: 0x" + Integer.toHexString(flags) + ", returning RMERR", 
/* 1184 */               Integer.valueOf(flags));
/*      */         }
/* 1186 */         if (Trace.isOn) {
/* 1187 */           Trace.exit(this, "com.ibm.mq.XAtoJTA", "xa_rollback(byte [ ],byte [ ],int,int,int)", 
/* 1188 */               Integer.valueOf(-3), 1);
/*      */         }
/* 1190 */         return -3;
/*      */       } 
/*      */ 
/*      */       
/* 1194 */       Integer rmId = Integer.valueOf(rmid);
/*      */ 
/*      */ 
/*      */       
/* 1198 */       if (useOra816) {
/*      */ 
/*      */         
/*      */         try {
/*      */ 
/*      */           
/* 1204 */           if (Trace.isOn) {
/* 1205 */             Trace.data(this, "xa_rollback(byte [ ],byte [ ],int,int,int)", "creating oracle xid", "");
/*      */           }
/*      */           
/* 1208 */           Class<?> oraXidClass = AccessController.<Class<?>>doPrivileged(new PrivilegedAction<Class<?>>()
/*      */               {
/*      */                 public Class<?> run()
/*      */                 {
/* 1212 */                   if (Trace.isOn) {
/* 1213 */                     Trace.entry(this, "com.ibm.mq.XAtoJTA", "run()");
/*      */                   }
/*      */ 
/*      */ 
/*      */ 
/*      */                   
/*      */                   try {
/* 1220 */                     Class<?> oraXidClass = Thread.currentThread().getContextClassLoader().loadClass("oracle.jdbc.xa.OracleXid");
/*      */ 
/*      */                     
/* 1223 */                     if (Trace.isOn) {
/* 1224 */                       Trace.exit(this, "com.ibm.mq.null", "run()", oraXidClass, 1);
/*      */                     }
/* 1226 */                     return oraXidClass;
/*      */                   }
/* 1228 */                   catch (AccessControlException ace) {
/* 1229 */                     if (Trace.isOn) {
/* 1230 */                       Trace.catchBlock(this, "com.ibm.mq.null", "run()", ace, 1);
/*      */                     }
/* 1232 */                     if (Trace.isOn) {
/* 1233 */                       Trace.exit(this, "com.ibm.mq.null", "run()", null, 2);
/*      */                     }
/* 1235 */                     return null;
/*      */                   }
/* 1237 */                   catch (ClassNotFoundException e) {
/* 1238 */                     if (Trace.isOn) {
/* 1239 */                       Trace.catchBlock(this, "com.ibm.mq.null", "run()", e, 2);
/*      */                     }
/*      */                     
/* 1242 */                     if (Trace.isOn) {
/* 1243 */                       Trace.exit(this, "com.ibm.mq.null", "run()", null, 3);
/*      */                     }
/* 1245 */                     return null;
/*      */                   } 
/*      */                 }
/*      */               });
/*      */ 
/*      */ 
/*      */           
/* 1252 */           Class<?>[] paramTypes = new Class[] { int.class, byte[].class, byte[].class };
/*      */           
/* 1254 */           Constructor<?> con = oraXidClass.getConstructor(paramTypes);
/* 1255 */           Object[] params = { Integer.valueOf(format), gtrid, bqual };
/* 1256 */           newXID = (Xid)con.newInstance(params);
/*      */         }
/* 1258 */         catch (RuntimeException rte) {
/* 1259 */           if (Trace.isOn) {
/* 1260 */             Trace.catchBlock(this, "com.ibm.mq.XAtoJTA", "xa_rollback(byte [ ],byte [ ],int,int,int)", rte, 1);
/*      */           }
/*      */           
/* 1263 */           if (Trace.isOn) {
/* 1264 */             Trace.throwing(this, "com.ibm.mq.XAtoJTA", "xa_rollback(byte [ ],byte [ ],int,int,int)", rte);
/*      */           }
/*      */           
/* 1267 */           throw rte;
/*      */         }
/* 1269 */         catch (Exception e) {
/* 1270 */           if (Trace.isOn) {
/* 1271 */             Trace.catchBlock(this, "com.ibm.mq.XAtoJTA", "xa_rollback(byte [ ],byte [ ],int,int,int)", e, 2);
/*      */           }
/*      */           
/* 1274 */           if (Trace.isOn) {
/* 1275 */             Trace.exit(this, "com.ibm.mq.XAtoJTA", "xa_rollback(byte [ ],byte [ ],int,int,int)", 
/* 1276 */                 Integer.valueOf(-3), 2);
/*      */           }
/* 1278 */           return -3;
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 1283 */         if (Trace.isOn) {
/* 1284 */           Trace.data(this, "xa_rollback(byte [ ],byte [ ],int,int,int)", "creating a standard xid", "");
/*      */         }
/* 1286 */         newXID = new MQXid(format, gtrid, bqual);
/*      */       } 
/*      */ 
/*      */       
/* 1290 */       RR res = this.resourcesByRmid.get(rmId);
/*      */       
/* 1292 */       if (res == null) {
/*      */         
/* 1294 */         if (Trace.isOn) {
/* 1295 */           Trace.data(this, "xa_rollback(byte [ ],byte [ ],int,int,int)", "rmid not assigned", "");
/*      */         }
/* 1297 */         if (Trace.isOn) {
/* 1298 */           Trace.exit(this, "com.ibm.mq.XAtoJTA", "xa_rollback(byte [ ],byte [ ],int,int,int)", 
/* 1299 */               Integer.valueOf(-3), 3);
/*      */         }
/* 1301 */         return -3;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1306 */       XAResource xares = res.res;
/*      */ 
/*      */       
/* 1309 */       if (Trace.isOn) {
/* 1310 */         Trace.data(this, "xa_rollback(byte [ ],byte [ ],int,int,int)", "XARollback, xid '" + newXID + "'", "");
/*      */       }
/* 1312 */       xares.rollback(newXID);
/* 1313 */       xa_rc = 0;
/*      */     }
/* 1315 */     catch (XAException e) {
/* 1316 */       if (Trace.isOn) {
/* 1317 */         Trace.catchBlock(this, "com.ibm.mq.XAtoJTA", "xa_rollback(byte [ ],byte [ ],int,int,int)", e, 3);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1324 */       xa_rc = e.errorCode;
/*      */     }
/* 1326 */     catch (Exception e) {
/* 1327 */       if (Trace.isOn) {
/* 1328 */         Trace.catchBlock(this, "com.ibm.mq.XAtoJTA", "xa_rollback(byte [ ],byte [ ],int,int,int)", e, 4);
/*      */       }
/*      */ 
/*      */       
/* 1332 */       xa_rc = -3;
/*      */     } finally {
/*      */       
/* 1335 */       if (Trace.isOn) {
/* 1336 */         Trace.finallyBlock(this, "com.ibm.mq.XAtoJTA", "xa_rollback(byte [ ],byte [ ],int,int,int)");
/*      */       }
/*      */     } 
/*      */     
/* 1340 */     if (Trace.isOn) {
/* 1341 */       Trace.exit(this, "com.ibm.mq.XAtoJTA", "xa_rollback(byte [ ],byte [ ],int,int,int)", 
/* 1342 */           Integer.valueOf(xa_rc), 4);
/*      */     }
/* 1344 */     return xa_rc;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private synchronized void _deregisterResource(XAConnection xaconn) throws XAException {
/* 1351 */     if (Trace.isOn) {
/* 1352 */       Trace.entry(this, "com.ibm.mq.XAtoJTA", "_deregisterResource(XAConnection)", new Object[] { xaconn });
/*      */     }
/*      */     
/* 1355 */     String fid = "_deregisterResource(XAConnection)";
/*      */     
/* 1357 */     XAException exceptionToRethrow = null;
/*      */ 
/*      */     
/* 1360 */     RR rs = this.resourcesByConn.get(xaconn);
/* 1361 */     if (rs == null) {
/* 1362 */       if (Trace.isOn) {
/* 1363 */         Trace.data(this, "_deregisterResource(XAConnection)", "conn not assigned, checking spares", "");
/*      */       }
/* 1365 */       if (!this.spareXAConns.remove(xaconn)) {
/* 1366 */         if (Trace.isOn) {
/* 1367 */           Trace.data(this, "_deregisterResource(XAConnection)", "conn not found in spare list either", "");
/*      */         
/*      */         }
/*      */       
/*      */       }
/* 1372 */       else if (Trace.isOn) {
/* 1373 */         Trace.data(this, "_deregisterResource(XAConnection)", "found conn in spares list", "");
/*      */       
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 1380 */       if (Trace.isOn) {
/* 1381 */         Trace.data(this, "_deregisterResource(XAConnection)", "found RR for xaconn", "");
/*      */       }
/* 1383 */       XAResource xares = rs.res;
/* 1384 */       int rmid = rs.rmid;
/* 1385 */       if (this.inTransaction) {
/*      */         
/* 1387 */         Xid xid = rs.xid;
/* 1388 */         if (xid == null) {
/* 1389 */           if (Trace.isOn) {
/* 1390 */             Trace.data(this, "_deregisterResource(XAConnection)", "odd - no xid for rmid ", Integer.valueOf(rmid));
/*      */           }
/*      */         } else {
/*      */           
/* 1394 */           if (Trace.isOn) {
/* 1395 */             Trace.data(this, "_deregisterResource(XAConnection)", "doing xa_end", "");
/*      */           }
/*      */ 
/*      */           
/* 1399 */           if (Trace.isOn) {
/* 1400 */             Trace.data(this, "_deregisterResource(XAConnection)", "xid is '" + xid + "'", "");
/* 1401 */             Trace.data(this, "_deregisterResource(XAConnection)", "xares is ", String.valueOf(xares));
/*      */           } 
/*      */ 
/*      */           
/*      */           try {
/* 1406 */             xares.end(xid, 67108864);
/*      */           }
/* 1408 */           catch (XAException xae) {
/* 1409 */             if (Trace.isOn) {
/* 1410 */               Trace.catchBlock(this, "com.ibm.mq.XAtoJTA", "_deregisterResource(XAConnection)", xae);
/*      */             }
/*      */ 
/*      */             
/* 1414 */             exceptionToRethrow = xae;
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1420 */           this.dirtyResources.add(xares);
/*      */         }
/*      */       
/*      */       }
/* 1424 */       else if (Trace.isOn) {
/* 1425 */         Trace.data(this, "_deregisterResource(XAConnection)", "not in transaction, skipping xa_end", "");
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1430 */       this.resourcesByConn.remove(xaconn);
/* 1431 */       this.resourcesByRmid.remove(Integer.valueOf(rmid));
/*      */       
/* 1433 */       if (Trace.isOn) {
/* 1434 */         Trace.data(this, "_deregisterResource(XAConnection)", "putting rmId " + rmid + " back onto spares list", "");
/*      */       }
/* 1436 */       this.spareRmids.put(Integer.valueOf(rmid), rs);
/*      */     } 
/*      */     
/* 1439 */     if (exceptionToRethrow != null) {
/* 1440 */       if (Trace.isOn) {
/* 1441 */         Trace.throwing(this, "com.ibm.mq.XAtoJTA", "_deregisterResource(XAConnection)", exceptionToRethrow);
/*      */       }
/* 1443 */       throw exceptionToRethrow;
/*      */     } 
/*      */     
/* 1446 */     if (Trace.isOn) {
/* 1447 */       Trace.exit(this, "com.ibm.mq.XAtoJTA", "_deregisterResource(XAConnection)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private synchronized void _registerResource(XAConnection xaconn) throws MQException, SQLException, XAException {
/* 1457 */     if (Trace.isOn) {
/* 1458 */       Trace.entry(this, "com.ibm.mq.XAtoJTA", "_registerResource(XAConnection)", new Object[] { xaconn });
/*      */     }
/*      */     
/* 1461 */     String fid = "_registerResource(XAConnection)";
/*      */     
/* 1463 */     RR res = null;
/*      */ 
/*      */     
/*      */     try {
/* 1467 */       XAResource xaRes = xaconn.getXAResource();
/*      */       
/* 1469 */       if (Trace.isOn) {
/* 1470 */         Trace.data(this, "_registerResource(XAConnection) - xaRes", xaRes
/* 1471 */             .toString());
/*      */       }
/*      */ 
/*      */       
/* 1475 */       if (XATransactionTimeout != -1) {
/*      */         
/* 1477 */         boolean result = xaRes.setTransactionTimeout(XATransactionTimeout);
/*      */         
/* 1479 */         if (Trace.isOn) {
/* 1480 */           Trace.data(this, "is XATransactionTimeout set successfully?: " + result + ", value of XATransactionTimeout: " + XATransactionTimeout, "");
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1491 */       for (XAConnection con : this.spareXAConns) {
/* 1492 */         XAResource other = con.getXAResource();
/* 1493 */         if (xaRes.isSameRM(other)) {
/* 1494 */           if (Trace.isOn) {
/* 1495 */             Trace.data(this, "_registerResource(XAConnection)", "xaResource matches previously registered connection, returning an error", "");
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1501 */           MQException traceRet1 = new MQException(2, 2195, this);
/*      */ 
/*      */ 
/*      */           
/* 1505 */           if (Trace.isOn) {
/* 1506 */             Trace.throwing(this, "com.ibm.mq.XAtoJTA", "_registerResource(XAConnection)", traceRet1, 1);
/*      */           }
/*      */           
/* 1509 */           throw traceRet1;
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 1514 */       for (XAConnection con : this.resourcesByConn.keySet()) {
/* 1515 */         XAResource other = con.getXAResource();
/* 1516 */         if (xaRes.isSameRM(other)) {
/* 1517 */           if (Trace.isOn) {
/* 1518 */             Trace.data(this, "_registerResource(XAConnection)", "xaResource matches previously registered connection, returning an error", "");
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1524 */           MQException traceRet2 = new MQException(2, 2195, this);
/*      */ 
/*      */           
/* 1527 */           if (Trace.isOn) {
/* 1528 */             Trace.throwing(this, "com.ibm.mq.XAtoJTA", "_registerResource(XAConnection)", traceRet2, 2);
/*      */           }
/*      */           
/* 1531 */           throw traceRet2;
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1537 */       if (!this.inTransaction) {
/* 1538 */         if (Trace.isOn) {
/* 1539 */           Trace.data(this, "_registerResource(XAConnection)", "not in transaction, adding to spare conns", "");
/*      */         }
/*      */ 
/*      */         
/* 1543 */         this.spareXAConns.add(xaconn);
/*      */         
/* 1545 */         xaconn.addConnectionEventListener(this);
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */ 
/*      */         
/* 1554 */         for (XAResource dirty : this.dirtyResources) {
/* 1555 */           if (Trace.isOn) {
/* 1556 */             Trace.data(this, "_registerResource(XAConnection)", "comparing '" + 
/*      */ 
/*      */                 
/* 1559 */                 String.valueOf(xaRes) + "' with '" + String.valueOf(dirty) + "'", "");
/*      */           }
/*      */           
/* 1562 */           if (xaRes.isSameRM(dirty)) {
/* 1563 */             if (Trace.isOn) {
/* 1564 */               Trace.data(this, "_registerResource(XAConnection)", "XAResource found on dirty list - app confused - throwing exception to tell MQSESSION to rollback", "");
/*      */             }
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1570 */             MQException traceRet3 = new MQException(2, 2003, this);
/*      */ 
/*      */             
/* 1573 */             if (Trace.isOn) {
/* 1574 */               Trace.throwing(this, "com.ibm.mq.XAtoJTA", "_registerResource(XAConnection)", traceRet3, 3);
/*      */             }
/*      */             
/* 1577 */             throw traceRet3;
/*      */           } 
/*      */         } 
/*      */         
/* 1581 */         if (Trace.isOn) {
/* 1582 */           Trace.data(this, "_registerResource(XAConnection)", "XAResource not matched", "");
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1590 */         if (this.spareRmids.isEmpty()) {
/* 1591 */           if (Trace.isOn) {
/* 1592 */             Trace.data(this, "_registerResource(XAConnection)", "no spare rmids available, throwing an exception", "");
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1598 */           MQException traceRet4 = new MQException(2, 2372, this);
/*      */ 
/*      */           
/* 1601 */           if (Trace.isOn) {
/* 1602 */             Trace.throwing(this, "com.ibm.mq.XAtoJTA", "_registerResource(XAConnection)", traceRet4, 4);
/*      */           }
/*      */           
/* 1605 */           throw traceRet4;
/*      */         } 
/*      */ 
/*      */         
/* 1609 */         Integer rmId = this.spareRmids.keySet().iterator().next();
/* 1610 */         res = this.spareRmids.remove(rmId);
/*      */ 
/*      */         
/* 1613 */         if (Trace.isOn) {
/* 1614 */           Trace.data(this, "_registerResource(XAConnection)", "in transaction, driving xa_start with Xid", res.xid);
/*      */         }
/*      */         
/* 1617 */         res.res = xaRes;
/*      */         try {
/* 1619 */           xaRes.start(res.xid, 0);
/*      */         }
/* 1621 */         catch (XAException e) {
/* 1622 */           if (e.errorCode == -8) {
/* 1623 */             MQException traceRet = new MQException(2, 2003, this);
/*      */ 
/*      */             
/* 1626 */             if (Trace.isOn) {
/* 1627 */               Trace.throwing(this, "com.ibm.mq.XAtoJTA", "_registerResource(XAConnection)", traceRet, 8);
/*      */             }
/*      */             
/* 1630 */             throw traceRet;
/*      */           } 
/*      */           
/* 1633 */           throw e;
/*      */         } 
/*      */ 
/*      */         
/* 1637 */         this.resourcesByRmid.put(rmId, res);
/* 1638 */         this.resourcesByConn.put(xaconn, res);
/*      */ 
/*      */         
/* 1641 */         xaconn.addConnectionEventListener(this);
/*      */       }
/*      */     
/*      */     }
/* 1645 */     catch (XAException e) {
/* 1646 */       if (Trace.isOn) {
/* 1647 */         Trace.catchBlock(this, "com.ibm.mq.XAtoJTA", "_registerResource(XAConnection)", e, 1);
/*      */       }
/* 1649 */       if (Trace.isOn) {
/* 1650 */         Trace.throwing(this, "com.ibm.mq.XAtoJTA", "_registerResource(XAConnection)", e, 5);
/*      */       }
/* 1652 */       throw e;
/*      */     
/*      */     }
/* 1655 */     catch (SQLException e) {
/* 1656 */       if (Trace.isOn) {
/* 1657 */         Trace.catchBlock(this, "com.ibm.mq.XAtoJTA", "_registerResource(XAConnection)", e, 2);
/*      */       }
/* 1659 */       if (Trace.isOn) {
/* 1660 */         Trace.throwing(this, "com.ibm.mq.XAtoJTA", "_registerResource(XAConnection)", e, 6);
/*      */       }
/* 1662 */       throw e;
/*      */     
/*      */     }
/* 1665 */     catch (MQException e) {
/* 1666 */       if (Trace.isOn) {
/* 1667 */         Trace.catchBlock(this, "com.ibm.mq.XAtoJTA", "_registerResource(XAConnection)", e, 3);
/*      */       }
/* 1669 */       if (Trace.isOn) {
/* 1670 */         Trace.throwing(this, "com.ibm.mq.XAtoJTA", "_registerResource(XAConnection)", e, 7);
/*      */       }
/* 1672 */       throw e;
/*      */     }
/*      */     finally {
/*      */       
/* 1676 */       if (Trace.isOn) {
/* 1677 */         Trace.finallyBlock(this, "com.ibm.mq.XAtoJTA", "_registerResource(XAConnection)");
/*      */       }
/*      */     } 
/*      */     
/* 1681 */     if (Trace.isOn) {
/* 1682 */       Trace.exit(this, "com.ibm.mq.XAtoJTA", "_registerResource(XAConnection)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected synchronized void _resetRC() {
/* 1690 */     if (Trace.isOn) {
/* 1691 */       Trace.entry(this, "com.ibm.mq.XAtoJTA", "_resetRC()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1700 */     this.beginRC = 2121;
/*      */     
/* 1702 */     if (Trace.isOn) {
/* 1703 */       Trace.exit(this, "com.ibm.mq.XAtoJTA", "_resetRC()");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected synchronized int _getRC() {
/* 1709 */     if (Trace.isOn) {
/* 1710 */       Trace.entry(this, "com.ibm.mq.XAtoJTA", "_getRC()");
/*      */     }
/*      */     
/* 1713 */     if (Trace.isOn) {
/* 1714 */       Trace.exit(this, "com.ibm.mq.XAtoJTA", "_getRC()", Integer.valueOf(this.beginRC));
/*      */     }
/* 1716 */     return this.beginRC;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\XAtoJTA.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */