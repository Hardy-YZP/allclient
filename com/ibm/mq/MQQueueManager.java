/*      */ package com.ibm.mq;
/*      */ 
/*      */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*      */ import com.ibm.mq.jmqi.JmqiException;
/*      */ import com.ibm.mq.jmqi.JmqiMQ;
/*      */ import com.ibm.mq.jmqi.MQMD;
/*      */ import com.ibm.mq.jmqi.MQOD;
/*      */ import com.ibm.mq.jmqi.MQPMO;
/*      */ import com.ibm.mq.jmqi.handles.Hconn;
/*      */ import com.ibm.mq.jmqi.system.JmqiRunnable;
/*      */ import com.ibm.mq.jmqi.system.JmqiSP;
/*      */ import com.ibm.msg.client.commonservices.cssystem.CSSystem;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.net.URL;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.security.AccessControlException;
/*      */ import java.security.AccessController;
/*      */ import java.security.PrivilegedAction;
/*      */ import java.sql.Connection;
/*      */ import java.sql.SQLException;
/*      */ import java.util.Collection;
/*      */ import java.util.HashMap;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Vector;
/*      */ import javax.sql.XAConnection;
/*      */ import javax.sql.XADataSource;
/*      */ import javax.transaction.xa.XAException;
/*      */ import org.json.JSONException;
/*      */ import org.json.JSONObject;
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
/*      */ public class MQQueueManager
/*      */   extends MQManagedObject
/*      */ {
/*      */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQQueueManager.java";
/*      */   private static final int MQOT_PROCESS = 3;
/*      */   private static final int MQOT_Q_MGR = 5;
/*      */   protected static final int MQ_Q_NAME_LENGTH = 48;
/*      */   protected static final int MQIA_CODED_CHAR_SET_ID = 2;
/*      */   protected static final int MQIA_MAX_MSG_LENGTH = 13;
/*      */   protected static final int MQIA_COMMAND_LEVEL = 31;
/*      */   protected static final int MQCA_COMMAND_INPUT_Q_NAME = 2003;
/*      */   protected static final int MQIA_MAX_PRIORITY = 14;
/*      */   protected static final int MQIA_SYNCPOINT = 30;
/*      */   private Vector<MQQueue> queues;
/*      */   private Collection<MQTopic> topics;
/*      */   private Vector<MQProcess> processes;
/*      */   private Vector<MQDistributionList> distributionLists;
/*      */   
/*      */   static {
/*   84 */     if (Trace.isOn) {
/*   85 */       Trace.data("com.ibm.mq.MQQueueManager", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQQueueManager.java");
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
/*  111 */   private Pint completionCode = new Pint();
/*  112 */   private Pint reason = new Pint();
/*      */   
/*  114 */   private MQManagedConnectionJ11 mqManCon = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  123 */   private MQQueueManager copy = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  129 */   private MQQueueManager original = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  135 */   private String originalName = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  141 */   private volatile MQException exceptionForDisconnect = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean allowErrorEvents = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean supportsQAT2 = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  159 */   private static MQQueueManagerFactory factory = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  165 */   int association = 0;
/*      */ 
/*      */   
/*      */   static {
/*  169 */     if (Trace.isOn) {
/*  170 */       Trace.entry("com.ibm.mq.MQQueueManager", "static()");
/*      */     }
/*  172 */     if (factory == null) {
/*  173 */       factory = MQQueueManagerFactory.getInstance();
/*      */     }
/*      */     
/*  176 */     if (Trace.isOn) {
/*  177 */       Trace.exit("com.ibm.mq.MQQueueManager", "static()");
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
/*      */   @Deprecated
/*      */   public boolean isConnected = false;
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
/*      */   protected boolean connectStatus = false;
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
/*      */   private URL cdUrl;
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
/*      */   public Connection getJDBCConnection(XADataSource xads) throws MQException, SQLException, XAException {
/*  228 */     Connection traceRet1 = null;
/*      */     
/*  230 */     if (Trace.isOn) {
/*  231 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "getJDBCConnection(javax.sql.XADataSource)", new Object[] { xads });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  237 */       traceRet1 = getJDBCConnection(xads, (String)null, (String)null);
/*      */     }
/*  239 */     catch (MQException mqe) {
/*  240 */       if (Trace.isOn) {
/*  241 */         Trace.catchBlock(this, "com.ibm.mq.MQQueueManager", "getJDBCConnection(javax.sql.XADataSource)", mqe);
/*      */       }
/*  243 */       throw mqe;
/*      */     }
/*  245 */     catch (SQLException sqle) {
/*  246 */       if (Trace.isOn) {
/*  247 */         Trace.catchBlock(this, "com.ibm.mq.MQQueueManager", "getJDBCConnection(javax.sql.XADataSource)", sqle);
/*      */       }
/*  249 */       throw sqle;
/*      */     }
/*  251 */     catch (XAException xae) {
/*  252 */       if (Trace.isOn) {
/*  253 */         Trace.catchBlock(this, "com.ibm.mq.MQQueueManager", "getJDBCConnection(javax.sql.XADataSource)", xae);
/*      */       }
/*  255 */       throw xae;
/*      */     } finally {
/*      */       
/*  258 */       if (Trace.isOn) {
/*  259 */         Trace.exit(this, "com.ibm.mq.MQQueueManager", "getJDBCConnection(javax.sql.XADataSource)", traceRet1);
/*      */       }
/*      */     } 
/*      */     
/*  263 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final class XAConnectionGetter
/*      */     extends JmqiRunnable
/*      */   {
/*      */     private XAConnection xaconn;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private XADataSource xads;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private String user;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private String password;
/*      */ 
/*      */ 
/*      */     
/*      */     private Exception exception;
/*      */ 
/*      */ 
/*      */     
/*      */     private boolean isConnectionAttemptComplete = false;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XAConnectionGetter(JmqiEnvironment env, XADataSource xads, String user, String password) {
/*  302 */       super(env);
/*  303 */       if (Trace.isOn) {
/*  304 */         Trace.entry(this, "com.ibm.mq.XAConnectionGetter", "<init>(JmqiEnvironment,XADataSource,String,String)", new Object[] { env, xads, user, (password == null) ? null : "********" });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  309 */       this.xads = xads;
/*  310 */       this.user = user;
/*  311 */       this.password = password;
/*      */       
/*  313 */       if (Trace.isOn) {
/*  314 */         Trace.exit(this, "com.ibm.mq.XAConnectionGetter", "<init>(JmqiEnvironment,XADataSource,String,String)");
/*      */       }
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
/*      */     public void run() throws Exception {
/*  328 */       if (Trace.isOn) {
/*  329 */         Trace.entry(this, "com.ibm.mq.XAConnectionGetter", "run()");
/*      */       }
/*  331 */       synchronized (this) {
/*      */         try {
/*  333 */           this.xaconn = XAtoJTA.createRegisteredConnection(this.xads, this.user, this.password);
/*      */         
/*      */         }
/*  336 */         catch (Exception ex) {
/*  337 */           if (Trace.isOn) {
/*  338 */             Trace.catchBlock(this, "com.ibm.mq.XAConnectionGetter", "run()", ex);
/*      */           }
/*      */ 
/*      */           
/*  342 */           this.exception = ex;
/*      */         } finally {
/*      */           
/*  345 */           if (Trace.isOn) {
/*  346 */             Trace.finallyBlock(this, "com.ibm.mq.XAConnectionGetter", "run()");
/*      */           }
/*      */           
/*  349 */           this.isConnectionAttemptComplete = true;
/*  350 */           notifyAll();
/*      */         } 
/*      */       } 
/*      */       
/*  354 */       if (Trace.isOn) {
/*  355 */         Trace.exit(this, "com.ibm.mq.XAConnectionGetter", "run()");
/*      */       }
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
/*      */     public synchronized XAConnection getXaConn() throws Exception {
/*  368 */       if (Trace.isOn) {
/*  369 */         Trace.entry(this, "com.ibm.mq.XAConnectionGetter", "getXaConn()");
/*      */       }
/*      */       
/*  372 */       while (!this.isConnectionAttemptComplete) {
/*      */         try {
/*  374 */           wait();
/*      */         }
/*  376 */         catch (InterruptedException e) {
/*  377 */           if (Trace.isOn) {
/*  378 */             Trace.catchBlock(this, "com.ibm.mq.XAConnectionGetter", "getXaConn()", e);
/*      */           }
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  385 */       if (this.exception != null) {
/*  386 */         if (Trace.isOn) {
/*  387 */           Trace.throwing(this, "com.ibm.mq.XAConnectionGetter", "getXaConn()", this.exception);
/*      */         }
/*      */         
/*  390 */         throw this.exception;
/*      */       } 
/*      */       
/*  393 */       if (Trace.isOn) {
/*  394 */         Trace.exit(this, "com.ibm.mq.XAConnectionGetter", "getXaConn()", this.xaconn);
/*      */       }
/*      */       
/*  397 */       return this.xaconn;
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Connection getJDBCConnection(XADataSource xads, String userid, String password) throws MQException, SQLException, XAException {
/*  430 */     if (Trace.isOn) {
/*  431 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "getJDBCConnection(javax.sql.XADataSource,String,String)", new Object[] { xads, userid, (password == null) ? password : "********" });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  439 */     if (xads == null) {
/*  440 */       MQException traceRet1 = new MQException(2, 2321, this, "MQNULLPOINTER", "XADataSource");
/*      */ 
/*      */ 
/*      */       
/*  444 */       if (Trace.isOn) {
/*  445 */         Trace.throwing(this, "com.ibm.mq.MQQueueManager", "getJDBCConnection(final javax.sql.XADataSource,String,String)", traceRet1, 0);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  451 */       throw traceRet1;
/*      */     } 
/*      */     
/*  454 */     String fid = "getJDBCConnection(javax.sql.XADataSource,String,String)";
/*  455 */     Connection conn = null;
/*      */ 
/*      */     
/*      */     try {
/*  459 */       if (!this.connectStatus) {
/*  460 */         MQException traceRet1 = new MQException(2, 2018, this, "MQJI002");
/*      */ 
/*      */ 
/*      */         
/*  464 */         if (Trace.isOn) {
/*  465 */           Trace.throwing(this, "com.ibm.mq.MQQueueManager", "getJDBCConnection( javax.sql.XADataSource,String,String)", traceRet1, 1);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  471 */         throw traceRet1;
/*      */       } 
/*      */       
/*  474 */       if (this.mqManCon.getIntegerProperty("connectOptions", 0) == 1) {
/*      */         
/*  476 */         MQException traceRet2 = new MQException(2, 2012, this);
/*      */ 
/*      */         
/*  479 */         if (Trace.isOn) {
/*  480 */           Trace.throwing(this, "com.ibm.mq.MQQueueManager", "getJDBCConnection(javax.sql.XADataSource,String,String)", traceRet2, 2);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  486 */         throw traceRet2;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  491 */       String transport = this.mqManCon.getStringProperty("transport");
/*  492 */       if (transport.equals("MQSeries Client")) {
/*  493 */         MQException traceRet3 = new MQException(2, 2012, this);
/*      */ 
/*      */         
/*  496 */         if (Trace.isOn) {
/*  497 */           Trace.throwing(this, "com.ibm.mq.MQQueueManager", "getJDBCConnection(javax.sql.XADataSource,String,String)", traceRet3, 3);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  503 */         throw traceRet3;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  509 */       XAConnectionGetter xaconnGetter = new XAConnectionGetter(this.env, xads, userid, password);
/*      */       
/*  511 */       ((JmqiSP)this.osession.getJmqi()).executeRunnable(this.Hconn.getHconn(), xaconnGetter);
/*      */       
/*  513 */       conn = xaconnGetter.getXaConn().getConnection();
/*      */       
/*  515 */       if (Trace.isOn) {
/*  516 */         Trace.exit(this, "com.ibm.mq.MQQueueManager", "getJDBCConnection(javax.sql.XADataSource,String,String)", conn);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  522 */       return conn;
/*      */     }
/*  524 */     catch (SQLException e) {
/*  525 */       if (Trace.isOn) {
/*  526 */         Trace.catchBlock(this, "com.ibm.mq.MQQueueManager", "getJDBCConnection(javax.sql.XADataSource,String,String)", e, 1);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  532 */       if (Trace.isOn) {
/*  533 */         Trace.throwing(this, "com.ibm.mq.MQQueueManager", "getJDBCConnection(javax.sql.XADataSource,String,String)", e, 4);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  539 */       throw e;
/*      */     }
/*  541 */     catch (MQException e) {
/*  542 */       if (Trace.isOn) {
/*  543 */         Trace.catchBlock(this, "com.ibm.mq.MQQueueManager", "getJDBCConnection(javax.sql.XADataSource,String,String)", e, 2);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  549 */       if (e.reasonCode == 2003) {
/*      */         
/*      */         try {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  556 */           if (Trace.isOn) {
/*  557 */             Trace.data(this, "getJDBCConnection(javax.sql.XADataSource,String,String)", "Attempting backout after registerResource failed to reregister existing connection", "");
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  563 */           backout();
/*  564 */           if (Trace.isOn) {
/*  565 */             Trace.data(this, "getJDBCConnection(javax.sql.XADataSource,String,String)", "Backout successful - throwing rollback error back to application", "");
/*      */ 
/*      */           
/*      */           }
/*      */ 
/*      */         
/*      */         }
/*  572 */         catch (MQException me) {
/*  573 */           if (Trace.isOn) {
/*  574 */             Trace.catchBlock(this, "com.ibm.mq.MQQueueManager", "getJDBCConnection(javax.sql.XADataSource,String,String)", me, 3);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  580 */           if (Trace.isOn) {
/*  581 */             Trace.throwing(this, "com.ibm.mq.MQQueueManager", "getJDBCConnection(javax.sql.XADataSource,String,String)", me, 5);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  587 */           throw me;
/*      */         } 
/*      */       }
/*  590 */       if (Trace.isOn) {
/*  591 */         Trace.throwing(this, "com.ibm.mq.MQQueueManager", "getJDBCConnection(javax.sql.XADataSource,String,String)", e, 6);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  597 */       throw e;
/*      */     }
/*  599 */     catch (XAException e) {
/*  600 */       if (Trace.isOn) {
/*  601 */         Trace.catchBlock(this, "com.ibm.mq.MQQueueManager", "getJDBCConnection(javax.sql.XADataSource,String,String)", e, 4);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  607 */       if (Trace.isOn) {
/*  608 */         Trace.throwing(this, "com.ibm.mq.MQQueueManager", "getJDBCConnection(javax.sql.XADataSource,String,String)", e, 7);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  614 */       throw e;
/*      */     }
/*  616 */     catch (Exception e) {
/*  617 */       RuntimeException re; if (Trace.isOn) {
/*  618 */         Trace.catchBlock(this, "com.ibm.mq.MQQueueManager", "getJDBCConnection(javax.sql.XADataSource,String,String)", e, 5);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  626 */       HashMap<String, Object> ffstInfo = new HashMap<>();
/*  627 */       ffstInfo.put("Exception", e.toString());
/*  628 */       ffstInfo.put("Description", "Unexpected exception");
/*  629 */       Trace.ffst(this, "getJDBCConnection(javax.sql.XADataSource,String,String)", "1", ffstInfo, null);
/*      */       
/*  631 */       if (e instanceof RuntimeException) {
/*  632 */         re = (RuntimeException)e;
/*      */       } else {
/*      */         
/*  635 */         re = new RuntimeException(e);
/*      */       } 
/*  637 */       if (Trace.isOn) {
/*  638 */         Trace.throwing(this, "com.ibm.mq.MQQueueManager", "getJDBCConnection(javax.sql.XADataSource,String,String)", re, 8);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  644 */       throw re;
/*      */     } finally {
/*      */       
/*  647 */       if (Trace.isOn) {
/*  648 */         Trace.finallyBlock(this, "com.ibm.mq.MQQueueManager", "getJDBCConnection(javax.sql.XADataSource,String,String)");
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
/*      */   
/*      */   public boolean isConnected() {
/*  662 */     if (Trace.isOn) {
/*  663 */       Trace.data(this, "com.ibm.mq.MQQueueManager", "isConnected()", "getter", 
/*  664 */           Boolean.valueOf(this.connectStatus));
/*      */     }
/*  666 */     return this.connectStatus;
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
/*      */   public int getCharacterSet() throws MQException {
/*  682 */     int traceRet1 = 0;
/*      */     try {
/*  684 */       traceRet1 = getInt(2);
/*      */     } finally {
/*      */       
/*  687 */       if (Trace.isOn) {
/*  688 */         Trace.data(this, "com.ibm.mq.MQQueueManager", "getCharacterSet()", "getter", 
/*  689 */             Integer.valueOf(traceRet1));
/*      */       }
/*      */     } 
/*  692 */     return traceRet1;
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
/*      */   public int getMaximumMessageLength() throws MQException {
/*  707 */     int traceRet1 = 0;
/*      */     try {
/*  709 */       traceRet1 = getInt(13);
/*      */     } finally {
/*      */       
/*  712 */       if (Trace.isOn) {
/*  713 */         Trace.data(this, "com.ibm.mq.MQQueueManager", "getMaximumMessageLength()", "getter", Integer.valueOf(traceRet1));
/*      */       }
/*      */     } 
/*  716 */     return traceRet1;
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
/*      */   public int getCommandLevel() throws MQException {
/*  736 */     int traceRet1 = 0;
/*      */     try {
/*  738 */       traceRet1 = getInt(31);
/*      */     } finally {
/*      */       
/*  741 */       if (Trace.isOn) {
/*  742 */         Trace.data(this, "com.ibm.mq.MQQueueManager", "getCommandLevel()", "getter", Integer.valueOf(traceRet1));
/*      */       }
/*      */     } 
/*  745 */     return traceRet1;
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
/*      */   public String getCommandInputQueueName() throws MQException {
/*  761 */     String traceRet1 = null;
/*      */     try {
/*  763 */       traceRet1 = getString(2003, 48);
/*      */     }
/*      */     finally {
/*      */       
/*  767 */       if (Trace.isOn) {
/*  768 */         Trace.data(this, "com.ibm.mq.MQQueueManager", "getCommandInputQueueName()", "getter", traceRet1);
/*      */       }
/*      */     } 
/*      */     
/*  772 */     return traceRet1;
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
/*      */   public int getMaximumPriority() throws MQException {
/*  787 */     int traceRet1 = 0;
/*      */     try {
/*  789 */       traceRet1 = getInt(14);
/*      */     } finally {
/*      */       
/*  792 */       if (Trace.isOn) {
/*  793 */         Trace.data(this, "com.ibm.mq.MQQueueManager", "getMaximumPriority()", "getter", 
/*  794 */             Integer.valueOf(traceRet1));
/*      */       }
/*      */     } 
/*  797 */     return traceRet1;
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
/*      */   public int getSyncpointAvailability() throws MQException {
/*  815 */     int traceRet1 = 0;
/*      */     try {
/*  817 */       traceRet1 = getInt(30);
/*      */     } finally {
/*      */       
/*  820 */       if (Trace.isOn) {
/*  821 */         Trace.data(this, "com.ibm.mq.MQQueueManager", "getSyncpointAvailability()", "getter", 
/*      */             
/*  823 */             Integer.valueOf(traceRet1));
/*      */       }
/*      */     } 
/*  826 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getDistributionListCapable() {
/*  836 */     boolean traceRet1 = this.osession.distributionListCapable(this.Hconn.getHconn());
/*  837 */     if (Trace.isOn) {
/*  838 */       Trace.data(this, "com.ibm.mq.MQQueueManager", "getDistributionListCapable()", "getter", 
/*      */           
/*  840 */           Boolean.valueOf(traceRet1));
/*      */     }
/*  842 */     return traceRet1;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQQueueManager(String queueManagerName) throws MQException {
/*  884 */     if (Trace.isOn) {
/*  885 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "<init>(String)", new Object[] { queueManagerName });
/*      */     }
/*      */     
/*      */     try {
/*  889 */       this.originalName = queueManagerName;
/*  890 */       QueueManagerFactoryProperties props = new QueueManagerFactoryProperties();
/*  891 */       props.setName(queueManagerName);
/*  892 */       props.setMgr(this);
/*  893 */       initialize(factory.createQueueManager(0, props, null));
/*      */       
/*  895 */       this.allowErrorEvents = true;
/*      */     } finally {
/*      */       
/*  898 */       if (Trace.isOn) {
/*  899 */         Trace.finallyBlock(this, "com.ibm.mq.MQQueueManager", "<init>(String)");
/*      */       }
/*      */     } 
/*      */     
/*  903 */     if (Trace.isOn) {
/*  904 */       Trace.exit(this, "com.ibm.mq.MQQueueManager", "<init>(String)");
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
/*      */   public MQQueueManager(String queueManagerName, int options) throws MQException {
/*  933 */     if (Trace.isOn) {
/*  934 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "<init>(String,int)", new Object[] { queueManagerName, 
/*      */             
/*  936 */             Integer.valueOf(options) });
/*      */     }
/*      */     try {
/*  939 */       this.originalName = queueManagerName;
/*  940 */       QueueManagerFactoryProperties props = new QueueManagerFactoryProperties();
/*  941 */       props.setName(queueManagerName);
/*  942 */       props.setMgr(this);
/*  943 */       Hashtable<String, Object> mqprops = new Hashtable<>();
/*  944 */       mqprops.put("connectOptions", Integer.valueOf(options));
/*  945 */       props.setProperties(mqprops);
/*  946 */       initialize(factory.createQueueManager(0, props, null));
/*      */       
/*  948 */       this.allowErrorEvents = true;
/*      */     } finally {
/*      */       
/*  951 */       if (Trace.isOn) {
/*  952 */         Trace.finallyBlock(this, "com.ibm.mq.MQQueueManager", "<init>(String,int)");
/*      */       }
/*      */     } 
/*      */     
/*  956 */     if (Trace.isOn) {
/*  957 */       Trace.exit(this, "com.ibm.mq.MQQueueManager", "<init>(String,int)");
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
/*      */   public MQQueueManager(String queueManagerName, Hashtable properties) throws MQException {
/*  979 */     if (Trace.isOn) {
/*  980 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "<init>(String,Hashtable)", new Object[] { queueManagerName, 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  986 */             Trace.sanitizeMap(properties, "password", "XXXXXXXXXXXX") });
/*      */     }
/*      */     
/*      */     try {
/*  990 */       this.originalName = queueManagerName;
/*  991 */       QueueManagerFactoryProperties props = new QueueManagerFactoryProperties();
/*  992 */       props.setName(queueManagerName);
/*  993 */       props.setProperties(properties);
/*  994 */       props.setMgr(this);
/*  995 */       URL url = (URL)MQEnvironment.getObjectProperty("CCDT URL", properties);
/*      */       
/*  997 */       this.cdUrl = url;
/*  998 */       if (properties.containsKey("QMgr_Association")) {
/*  999 */         int i = MQEnvironment.getIntegerProperty("QMgr_Association", properties);
/*      */         
/* 1001 */         initialize(factory.createQueueManager(i, props, url));
/*      */       } else {
/*      */         
/* 1004 */         initialize(factory.createQueueManager(0, props, url));
/*      */       } 
/*      */       
/* 1007 */       this.allowErrorEvents = true;
/*      */     } finally {
/*      */       
/* 1010 */       if (Trace.isOn) {
/* 1011 */         Trace.finallyBlock(this, "com.ibm.mq.MQQueueManager", "<init>(String,Hashtable)");
/*      */       }
/*      */     } 
/*      */     
/* 1015 */     if (Trace.isOn) {
/* 1016 */       Trace.exit(this, "com.ibm.mq.MQQueueManager", "<init>(String,Hashtable)");
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
/*      */   public MQQueueManager(String queueManagerName, MQConnectionManager connectionManager) throws MQException {
/* 1038 */     if (Trace.isOn) {
/* 1039 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "<init>(String,MQConnectionManager)", new Object[] { queueManagerName, connectionManager });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 1044 */       this.originalName = queueManagerName;
/* 1045 */       QueueManagerFactoryProperties props = new QueueManagerFactoryProperties();
/* 1046 */       props.setName(queueManagerName);
/* 1047 */       props.setConMgr(connectionManager);
/* 1048 */       props.setMgr(this);
/* 1049 */       initialize(factory.createQueueManager(0, props, null));
/*      */       
/* 1051 */       this.allowErrorEvents = true;
/*      */     } finally {
/*      */       
/* 1054 */       if (Trace.isOn) {
/* 1055 */         Trace.finallyBlock(this, "com.ibm.mq.MQQueueManager", "<init>(String,MQConnectionManager)");
/*      */       }
/*      */     } 
/*      */     
/* 1059 */     if (Trace.isOn) {
/* 1060 */       Trace.exit(this, "com.ibm.mq.MQQueueManager", "<init>(String,MQConnectionManager)");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQQueueManager(String queueManagerName, int options, MQConnectionManager connectionManager) throws MQException {
/* 1095 */     if (Trace.isOn) {
/* 1096 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "<init>(String,int,MQConnectionManager)", new Object[] { queueManagerName, 
/*      */             
/* 1098 */             Integer.valueOf(options), connectionManager });
/*      */     }
/*      */     
/*      */     try {
/* 1102 */       this.originalName = queueManagerName;
/* 1103 */       QueueManagerFactoryProperties props = new QueueManagerFactoryProperties();
/* 1104 */       props.setName(queueManagerName);
/* 1105 */       props.setMgr(this);
/* 1106 */       Hashtable<String, Object> mqprops = new Hashtable<>();
/* 1107 */       mqprops.put("connectOptions", Integer.valueOf(options));
/* 1108 */       props.setProperties(mqprops);
/* 1109 */       props.setConMgr(connectionManager);
/* 1110 */       initialize(factory.createQueueManager(0, props, null));
/*      */       
/* 1112 */       this.allowErrorEvents = true;
/*      */     } finally {
/*      */       
/* 1115 */       if (Trace.isOn) {
/* 1116 */         Trace.finallyBlock(this, "com.ibm.mq.MQQueueManager", "<init>(String,int,MQConnectionManager)");
/*      */       }
/*      */     } 
/*      */     
/* 1120 */     if (Trace.isOn) {
/* 1121 */       Trace.exit(this, "com.ibm.mq.MQQueueManager", "<init>(String,int,MQConnectionManager)");
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
/*      */   public MQQueueManager(String queueManagerName, Hashtable properties, MQConnectionManager connectionManager) throws MQException {
/* 1147 */     if (Trace.isOn) {
/* 1148 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "<init>(String,Hashtable,MQConnectionManager)", new Object[] { queueManagerName, 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1154 */             Trace.sanitizeMap(properties, "password", "XXXXXXXXXXXX"), connectionManager });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 1159 */       this.originalName = queueManagerName;
/* 1160 */       QueueManagerFactoryProperties props = new QueueManagerFactoryProperties();
/* 1161 */       props.setMgr(this);
/* 1162 */       props.setName(queueManagerName);
/* 1163 */       props.setProperties(properties);
/* 1164 */       props.setConMgr(connectionManager);
/* 1165 */       URL url = (URL)MQEnvironment.getObjectProperty("CCDT URL", properties);
/*      */       
/* 1167 */       this.cdUrl = url;
/* 1168 */       if (properties.containsKey("QMgr_Association")) {
/* 1169 */         int i = MQEnvironment.getIntegerProperty("QMgr_Association", properties);
/*      */         
/* 1171 */         initialize(factory.createQueueManager(i, props, url));
/*      */       } else {
/*      */         
/* 1174 */         initialize(factory.createQueueManager(0, props, url));
/*      */       } 
/*      */       
/* 1177 */       this.allowErrorEvents = true;
/*      */     } finally {
/*      */       
/* 1180 */       if (Trace.isOn) {
/* 1181 */         Trace.finallyBlock(this, "com.ibm.mq.MQQueueManager", "<init>(String,Hashtable,MQConnectionManager)");
/*      */       }
/*      */     } 
/*      */     
/* 1185 */     if (Trace.isOn) {
/* 1186 */       Trace.exit(this, "com.ibm.mq.MQQueueManager", "<init>(String,Hashtable,MQConnectionManager)");
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
/*      */   public MQQueueManager(String queueManagerName, URL url) throws MQException {
/* 1226 */     if (Trace.isOn) {
/* 1227 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "<init>(String,URL)", new Object[] { queueManagerName, url });
/*      */     }
/*      */ 
/*      */     
/* 1231 */     this.cdUrl = url;
/*      */     try {
/* 1233 */       this.originalName = queueManagerName;
/* 1234 */       QueueManagerFactoryProperties props = new QueueManagerFactoryProperties();
/* 1235 */       props.setName(queueManagerName);
/* 1236 */       props.setMgr(this);
/* 1237 */       Hashtable<String, Object> mqprops = new Hashtable<>();
/* 1238 */       mqprops.put("CCDT URL", url);
/* 1239 */       mqprops.put("transport", "MQSeries Client");
/* 1240 */       props.setProperties(mqprops);
/* 1241 */       initialize(factory.createQueueManager(0, props, url));
/*      */       
/* 1243 */       this.allowErrorEvents = true;
/*      */     } finally {
/*      */       
/* 1246 */       if (Trace.isOn) {
/* 1247 */         Trace.finallyBlock(this, "com.ibm.mq.MQQueueManager", "<init>(String,URL)");
/*      */       }
/*      */     } 
/*      */     
/* 1251 */     if (Trace.isOn) {
/* 1252 */       Trace.exit(this, "com.ibm.mq.MQQueueManager", "<init>(String,URL)");
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
/*      */   public MQQueueManager(String queueManagerName, Hashtable<String, URL> properties, URL url) throws MQException {
/* 1301 */     if (Trace.isOn) {
/* 1302 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "<init>(String,Hashtable,URL)", new Object[] { queueManagerName, 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1308 */             Trace.sanitizeMap(properties, "password", "XXXXXXXXXXXX"), url });
/*      */     }
/*      */ 
/*      */     
/* 1312 */     this.cdUrl = url;
/*      */     try {
/* 1314 */       this.originalName = queueManagerName;
/* 1315 */       QueueManagerFactoryProperties props = new QueueManagerFactoryProperties();
/* 1316 */       props.setName(queueManagerName);
/* 1317 */       props.setMgr(this);
/* 1318 */       properties.put("CCDT URL", url);
/* 1319 */       properties.put("transport", "MQSeries Client");
/*      */       
/* 1321 */       props.setProperties(properties);
/* 1322 */       if (properties.containsKey("QMgr_Association")) {
/* 1323 */         int i = MQEnvironment.getIntegerProperty("QMgr_Association", properties);
/*      */         
/* 1325 */         initialize(factory.createQueueManager(i, props, url));
/*      */       } else {
/*      */         
/* 1328 */         initialize(factory.createQueueManager(0, props, url));
/*      */       } 
/*      */       
/* 1331 */       this.allowErrorEvents = true;
/*      */     } finally {
/*      */       
/* 1334 */       if (Trace.isOn) {
/* 1335 */         Trace.finallyBlock(this, "com.ibm.mq.MQQueueManager", "<init>(String,Hashtable,URL)");
/*      */       }
/*      */     } 
/*      */     
/* 1339 */     if (Trace.isOn) {
/* 1340 */       Trace.exit(this, "com.ibm.mq.MQQueueManager", "<init>(String,Hashtable,URL)");
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
/*      */   public MQQueueManager(String queueManagerName, URL url, MQConnectionManager connectionManager) throws MQException {
/* 1385 */     if (Trace.isOn) {
/* 1386 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "<init>(String,URL,MQConnectionManager)", new Object[] { queueManagerName, url, connectionManager });
/*      */     }
/*      */ 
/*      */     
/* 1390 */     this.cdUrl = url;
/*      */     try {
/* 1392 */       this.originalName = queueManagerName;
/* 1393 */       QueueManagerFactoryProperties props = new QueueManagerFactoryProperties();
/* 1394 */       props.setName(queueManagerName);
/* 1395 */       props.setConMgr(connectionManager);
/* 1396 */       props.setMgr(this);
/* 1397 */       Hashtable<String, Object> mqprops = new Hashtable<>();
/* 1398 */       mqprops.put("CCDT URL", url);
/* 1399 */       mqprops.put("transport", "MQSeries Client");
/* 1400 */       props.setProperties(mqprops);
/* 1401 */       initialize(factory.createQueueManager(0, props, url));
/*      */       
/* 1403 */       this.allowErrorEvents = true;
/*      */     } finally {
/*      */       
/* 1406 */       if (Trace.isOn) {
/* 1407 */         Trace.finallyBlock(this, "com.ibm.mq.MQQueueManager", "<init>(String,URL,MQConnectionManager)");
/*      */       }
/*      */     } 
/*      */     
/* 1411 */     if (Trace.isOn) {
/* 1412 */       Trace.exit(this, "com.ibm.mq.MQQueueManager", "<init>(String,URL,MQConnectionManager)");
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
/*      */   MQQueueManager(MQManagedConnectionJ11 mancon) throws MQException {
/* 1659 */     if (Trace.isOn) {
/* 1660 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "<init>(MQManagedConnectionJ11)", new Object[] { mancon });
/*      */     }
/*      */     
/* 1663 */     String fid = "<init>(MQManagedConnectionJ11)";
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1668 */     if (CSSystem.currentPlatform() == CSSystem.Platform.OS_ZSERIES && esafDetected()) {
/*      */       
/* 1670 */       MQException e = new MQException(2, 2012, this, "MQJE089");
/*      */ 
/*      */ 
/*      */       
/* 1674 */       if (Trace.isOn) {
/* 1675 */         Trace.throwing(this, "com.ibm.mq.MQQueueManager", "<init>(MQManagedConnectionJ11)", e, 2);
/*      */       }
/*      */       
/* 1678 */       throw e;
/*      */     } 
/*      */     
/*      */     try {
/* 1682 */       this.pszName = mancon.getQmgrName();
/* 1683 */       this.name = this.pszName;
/* 1684 */       this.queues = new Vector<>(5);
/* 1685 */       this.topics = new Vector<>();
/* 1686 */       this.processes = new Vector<>(5);
/* 1687 */       this.distributionLists = new Vector<>(5);
/* 1688 */       this.mqManCon = mancon;
/* 1689 */       this.osession = mancon.getMQSESSION();
/* 1690 */       this.Hconn = mancon.getHConn();
/* 1691 */       this.connected = this.mqManCon.isConnected();
/* 1692 */       this.isConnected = this.connected;
/* 1693 */       this.connectStatus = this.connected;
/* 1694 */       this.connectionReference = this;
/* 1695 */       this.parentQmgr = this;
/* 1696 */       this.resourceOpen = false;
/*      */       
/* 1698 */       this.openStatus = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1708 */       if (this.connected) {
/* 1709 */         if (Trace.isOn) {
/* 1710 */           Trace.data(this, "<init>(MQManagedConnectionJ11)", "Opening Qmgr for inquire", "");
/*      */         }
/* 1712 */         MQOD mqod = new MQOD();
/* 1713 */         mqod.ObjectType = 5;
/* 1714 */         this.osession.MQOPEN(this.Hconn.getHconn(), mqod, 32, this.Hobj, this.completionCode, this.reason);
/*      */         
/* 1716 */         if (this.completionCode.x != 0 || this.reason.x != 0) {
/*      */           
/* 1718 */           MQException traceRet1 = new MQException(this.completionCode.x, this.reason.x, this, this.osession.getLastJmqiException());
/*      */           
/* 1720 */           if (Trace.isOn) {
/* 1721 */             Trace.throwing(this, "com.ibm.mq.MQQueueManager", "<init>(MQManagedConnectionJ11)", traceRet1, 1);
/*      */           }
/*      */           
/* 1724 */           throw traceRet1;
/*      */         } 
/*      */         
/* 1727 */         this.resourceOpen = true;
/*      */         
/* 1729 */         this.openStatus = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*      */     finally {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1754 */       if (Trace.isOn) {
/* 1755 */         Trace.finallyBlock(this, "com.ibm.mq.MQQueueManager", "<init>(MQManagedConnectionJ11)");
/*      */       }
/*      */     } 
/*      */     
/* 1759 */     if (Trace.isOn) {
/* 1760 */       Trace.exit(this, "com.ibm.mq.MQQueueManager", "<init>(MQManagedConnectionJ11)");
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
/*      */   private boolean esafDetected() {
/* 1772 */     String esaf = AccessController.<String>doPrivileged(new PrivilegedAction<String>()
/*      */         {
/*      */           
/*      */           public String run()
/*      */           {
/*      */             try {
/* 1778 */               return System.getProperty("com.ibm.ims.esaf");
/*      */             }
/* 1780 */             catch (AccessControlException ace) {
/* 1781 */               return null;
/*      */             } 
/*      */           }
/*      */         });
/*      */     
/* 1786 */     return (esaf != null);
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
/*      */   private void initialize(MQQueueManager qmgr) {
/* 1806 */     if (Trace.isOn) {
/* 1807 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "initialize(MQQueueManager)", new Object[] { qmgr });
/*      */     }
/*      */     
/* 1810 */     synchronized (qmgr) {
/* 1811 */       this.pszName = qmgr.pszName;
/* 1812 */       this.name = qmgr.name;
/* 1813 */       this.queues = qmgr.queues;
/* 1814 */       this.topics = qmgr.topics;
/* 1815 */       this.processes = qmgr.processes;
/* 1816 */       this.distributionLists = qmgr.distributionLists;
/* 1817 */       this.mqManCon = qmgr.mqManCon;
/* 1818 */       this.osession = qmgr.osession;
/* 1819 */       this.Hconn = qmgr.Hconn;
/* 1820 */       this.Hobj = qmgr.Hobj;
/* 1821 */       this.connected = qmgr.connected;
/* 1822 */       this.isConnected = qmgr.isConnected;
/* 1823 */       this.connectStatus = qmgr.connectStatus;
/* 1824 */       this.resourceOpen = qmgr.resourceOpen;
/*      */       
/* 1826 */       this.openStatus = qmgr.openStatus;
/*      */       
/* 1828 */       setSupportsQAT2(qmgr.getSupportsQAT2());
/* 1829 */       this.parentQmgr = this;
/* 1830 */       this.connectionReference = this;
/* 1831 */       this.original = qmgr;
/* 1832 */       qmgr.copy = this;
/*      */     } 
/*      */     
/* 1835 */     if (Trace.isOn) {
/* 1836 */       Trace.exit(this, "com.ibm.mq.MQQueueManager", "initialize(MQQueueManager)");
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
/*      */   public void disconnect() throws MQException {
/* 1852 */     if (Trace.isOn) {
/* 1853 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "disconnect()");
/*      */     }
/*      */     try {
/* 1856 */       disconnect(false);
/*      */     } finally {
/*      */       
/* 1859 */       if (Trace.isOn) {
/* 1860 */         Trace.exit(this, "com.ibm.mq.MQQueueManager", "disconnect()");
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void disconnect(boolean calledFromFinalizer) throws MQException {
/* 1867 */     if (Trace.isOn) {
/* 1868 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "disconnect(boolean)", new Object[] {
/*      */             
/* 1870 */             Boolean.valueOf(calledFromFinalizer)
/*      */           });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1878 */       if (!MQQueueManagerFactory.isLastReference(this)) {
/* 1879 */         if (factory != null) {
/* 1880 */           factory.remove(this);
/*      */         }
/*      */       } else {
/*      */         
/* 1884 */         this.allowErrorEvents = false;
/* 1885 */         synchronized (this) {
/* 1886 */           if (!this.connected) {
/*      */             
/* 1888 */             if (Trace.isOn) {
/* 1889 */               Trace.exit(this, "com.ibm.mq.MQQueueManager", "disconnect(boolean)", 1);
/*      */             }
/*      */ 
/*      */             
/*      */             return;
/*      */           } 
/*      */           
/* 1896 */           cleanup();
/*      */         } 
/*      */ 
/*      */         
/* 1900 */         synchronized (this) {
/* 1901 */           if (this.original != null) {
/* 1902 */             this.mqManCon.fireConnectionClosedEvent(this.original, calledFromFinalizer);
/*      */           }
/*      */           else {
/*      */             
/* 1906 */             this.mqManCon.fireConnectionClosedEvent(this, calledFromFinalizer);
/*      */           } 
/*      */           
/* 1909 */           if (this.exceptionForDisconnect != null) {
/*      */             
/* 1911 */             if (Trace.isOn) {
/* 1912 */               Trace.throwing(this, "com.ibm.mq.MQQueueManager", "disconnect(boolean)", this.exceptionForDisconnect);
/*      */             }
/*      */ 
/*      */             
/* 1916 */             throw this.exceptionForDisconnect;
/*      */           } 
/*      */           
/* 1919 */           if (factory != null) {
/* 1920 */             factory.remove(this);
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } finally {
/*      */       
/* 1926 */       if (Trace.isOn) {
/* 1927 */         Trace.finallyBlock(this, "com.ibm.mq.MQQueueManager", "disconnect(boolean)");
/*      */       }
/*      */     } 
/*      */     
/* 1931 */     if (Trace.isOn) {
/* 1932 */       Trace.exit(this, "com.ibm.mq.MQQueueManager", "disconnect(boolean)", 2);
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
/*      */   public synchronized void commit() throws MQException {
/* 1954 */     if (Trace.isOn) {
/* 1955 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "commit()");
/*      */     }
/*      */     try {
/* 1958 */       if (!this.connected) {
/* 1959 */         MQException traceRet1 = new MQException(2, 2018, this, "MQJI002");
/*      */ 
/*      */ 
/*      */         
/* 1963 */         if (Trace.isOn) {
/* 1964 */           Trace.throwing(this, "com.ibm.mq.MQQueueManager", "commit()", traceRet1, 1);
/*      */         }
/*      */         
/* 1967 */         throw traceRet1;
/*      */       } 
/*      */       
/* 1970 */       this.osession.MQCMIT(this.Hconn.getHconn(), this.completionCode, this.reason);
/*      */       
/* 1972 */       if (this.completionCode.x == 1 && this.reason.x == 2408) {
/*      */ 
/*      */ 
/*      */         
/* 1976 */         this.completionCode.x = 0;
/* 1977 */         this.reason.x = 0;
/*      */       } 
/*      */       
/* 1980 */       if (this.completionCode.x != 0 || this.reason.x != 0) {
/*      */         
/* 1982 */         MQException mqe = new MQException(this.completionCode.x, this.reason.x, this, this.osession.getLastJmqiException());
/* 1983 */         errorOccurred(mqe);
/* 1984 */         if (Trace.isOn) {
/* 1985 */           Trace.throwing(this, "com.ibm.mq.MQQueueManager", "commit()", mqe, 2);
/*      */         }
/*      */         
/* 1988 */         throw mqe;
/*      */       } 
/*      */     } finally {
/*      */       
/* 1992 */       if (Trace.isOn) {
/* 1993 */         Trace.finallyBlock(this, "com.ibm.mq.MQQueueManager", "commit()");
/*      */       }
/*      */     } 
/*      */     
/* 1997 */     if (Trace.isOn) {
/* 1998 */       Trace.exit(this, "com.ibm.mq.MQQueueManager", "commit()");
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
/*      */   public synchronized void backout() throws MQException {
/* 2018 */     if (Trace.isOn) {
/* 2019 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "backout()");
/*      */     }
/*      */     try {
/* 2022 */       if (!this.connected) {
/* 2023 */         MQException traceRet1 = new MQException(2, 2018, this, "MQJI002");
/*      */ 
/*      */         
/* 2026 */         if (Trace.isOn) {
/* 2027 */           Trace.throwing(this, "com.ibm.mq.MQQueueManager", "backout()", traceRet1, 1);
/*      */         }
/*      */         
/* 2030 */         throw traceRet1;
/*      */       } 
/*      */       
/* 2033 */       this.osession.MQBACK(this.Hconn.getHconn(), this.completionCode, this.reason);
/*      */       
/* 2035 */       if (this.completionCode.x != 0 || this.reason.x != 0) {
/*      */         
/* 2037 */         MQException mqe = new MQException(this.completionCode.x, this.reason.x, this, this.osession.getLastJmqiException());
/* 2038 */         errorOccurred(mqe);
/* 2039 */         if (Trace.isOn) {
/* 2040 */           Trace.throwing(this, "com.ibm.mq.MQQueueManager", "backout()", mqe, 2);
/*      */         }
/*      */         
/* 2043 */         throw mqe;
/*      */       } 
/*      */     } finally {
/*      */       
/* 2047 */       if (Trace.isOn) {
/* 2048 */         Trace.finallyBlock(this, "com.ibm.mq.MQQueueManager", "backout()");
/*      */       }
/*      */     } 
/*      */     
/* 2052 */     if (Trace.isOn) {
/* 2053 */       Trace.exit(this, "com.ibm.mq.MQQueueManager", "backout()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final class XaToJtaGetter
/*      */     extends JmqiRunnable
/*      */   {
/*      */     private XAtoJTA instance;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XaToJtaGetter(JmqiEnvironment env) {
/* 2075 */       super(env);
/* 2076 */       if (Trace.isOn) {
/* 2077 */         Trace.entry(this, "com.ibm.mq.XaToJtaGetter", "<init>(JmqiEnvironment)", new Object[] { env });
/*      */       }
/*      */       
/* 2080 */       if (Trace.isOn) {
/* 2081 */         Trace.exit(this, "com.ibm.mq.XaToJtaGetter", "<init>(JmqiEnvironment)");
/*      */       }
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
/*      */     public void run() throws Exception {
/* 2095 */       if (Trace.isOn) {
/* 2096 */         Trace.entry(this, "com.ibm.mq.XaToJtaGetter", "run()");
/*      */       }
/* 2098 */       synchronized (this) {
/* 2099 */         this.instance = XAtoJTA.getThreadInstance();
/* 2100 */         notifyAll();
/*      */       } 
/*      */       
/* 2103 */       if (Trace.isOn) {
/* 2104 */         Trace.exit(this, "com.ibm.mq.XaToJtaGetter", "run()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public synchronized XAtoJTA getInstance() {
/* 2115 */       if (Trace.isOn) {
/* 2116 */         Trace.entry(this, "com.ibm.mq.XaToJtaGetter", "getInstance()");
/*      */       }
/* 2118 */       while (this.instance == null) {
/*      */         try {
/* 2120 */           wait();
/*      */         }
/* 2122 */         catch (InterruptedException e) {
/* 2123 */           if (Trace.isOn) {
/* 2124 */             Trace.catchBlock(this, "com.ibm.mq.XaToJtaGetter", "getInstance()", e);
/*      */           }
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2131 */       if (Trace.isOn) {
/* 2132 */         Trace.exit(this, "com.ibm.mq.XaToJtaGetter", "getInstance()", this.instance);
/*      */       }
/*      */       
/* 2135 */       return this.instance;
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
/*      */   public synchronized void begin() throws MQException {
/* 2156 */     if (Trace.isOn) {
/* 2157 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "begin()");
/*      */     }
/* 2159 */     String fid = "begin()";
/*      */     try {
/* 2161 */       if (!this.connected) {
/* 2162 */         MQException traceRet1 = new MQException(2, 2018, this, "MQJI002");
/*      */ 
/*      */ 
/*      */         
/* 2166 */         if (Trace.isOn) {
/* 2167 */           Trace.throwing(this, "com.ibm.mq.MQQueueManager", "begin()", traceRet1, 1);
/*      */         }
/*      */         
/* 2170 */         throw traceRet1;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2176 */       XAtoJTA xaToJta = null;
/*      */       try {
/* 2178 */         XaToJtaGetter xaToJtaGetter = new XaToJtaGetter(this.env);
/* 2179 */         ((JmqiSP)this.osession.getJmqi()).executeRunnable(this.Hconn.getHconn(), xaToJtaGetter);
/*      */         
/* 2181 */         xaToJta = xaToJtaGetter.getInstance();
/*      */       }
/* 2183 */       catch (Exception e) {
/* 2184 */         if (Trace.isOn) {
/* 2185 */           Trace.catchBlock(this, "com.ibm.mq.MQQueueManager", "begin()", e);
/*      */         }
/*      */ 
/*      */         
/* 2189 */         MQException traceRet2 = new MQException(2, 2195, this);
/*      */ 
/*      */         
/* 2192 */         if (Trace.isOn) {
/* 2193 */           Trace.throwing(this, "com.ibm.mq.MQQueueManager", "begin()", traceRet2, 2);
/*      */         }
/*      */         
/* 2196 */         throw traceRet2;
/*      */       } 
/* 2198 */       xaToJta._resetRC();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2204 */       this.osession.MQBEGIN(this.Hconn.getHconn(), this.completionCode, this.reason);
/*      */ 
/*      */ 
/*      */       
/* 2208 */       String transport = this.mqManCon.getStringProperty("transport");
/* 2209 */       if (!transport.equals("MQSeries Client")) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2215 */         int rc = xaToJta._getRC();
/* 2216 */         if (rc != 0 && this.reason.x == 2018) {
/* 2217 */           Trace.data(this, "begin()", "overriding reason code from " + this.reason.x + " to " + rc);
/* 2218 */           this.completionCode.x = 2;
/* 2219 */           this.reason.x = rc;
/*      */         } 
/*      */       } 
/*      */       
/* 2223 */       if (this.completionCode.x != 0 || this.reason.x != 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2231 */         if (this.reason.x == 2122) {
/* 2232 */           this.reason.x = 2195;
/* 2233 */           this.completionCode.x = 2;
/*      */         } 
/*      */ 
/*      */         
/* 2237 */         MQException mqe = new MQException(this.completionCode.x, this.reason.x, this, this.osession.getLastJmqiException());
/* 2238 */         if (Trace.isOn) {
/* 2239 */           Trace.throwing(this, "com.ibm.mq.MQQueueManager", "begin()", mqe, 3);
/*      */         }
/*      */         
/* 2242 */         throw mqe;
/*      */       } 
/*      */     } finally {
/*      */       
/* 2246 */       if (Trace.isOn) {
/* 2247 */         Trace.finallyBlock(this, "com.ibm.mq.MQQueueManager", "begin()");
/*      */       }
/*      */     } 
/* 2250 */     if (Trace.isOn) {
/* 2251 */       Trace.exit(this, "com.ibm.mq.MQQueueManager", "begin()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   MQSESSION getSession() {
/* 2262 */     if (Trace.isOn) {
/* 2263 */       Trace.data(this, "com.ibm.mq.MQQueueManager", "getSession()", "getter", this.osession);
/*      */     }
/*      */     
/* 2266 */     return this.osession;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   MQManagedConnectionJ11 getMQManagedConnection() {
/* 2276 */     if (Trace.isOn) {
/* 2277 */       Trace.data(this, "com.ibm.mq.MQQueueManager", "getMQManagedConnection()", "getter", this.mqManCon);
/*      */     }
/*      */     
/* 2280 */     return this.mqManCon;
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
/*      */   public synchronized void put(String qName, String qmName, MQMessage msg, MQPutMessageOptions pmo, String altUserId) throws MQException {
/* 2306 */     if (Trace.isOn) {
/* 2307 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "put(String,String,MQMessage,MQPutMessageOptions,String)", new Object[] { qName, qmName, msg, pmo, altUserId });
/*      */     }
/*      */ 
/*      */     
/* 2311 */     put(1, qName, qmName, (String)null, msg, pmo, altUserId);
/*      */     
/* 2313 */     if (Trace.isOn) {
/* 2314 */       Trace.exit(this, "com.ibm.mq.MQQueueManager", "put(String,String,MQMessage,MQPutMessageOptions,String)");
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
/*      */   public synchronized void put(String qName, String qmName, MQMessage msg, MQPutMessageOptions pmo) throws MQException {
/* 2342 */     if (Trace.isOn) {
/* 2343 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "put(String,String,MQMessage,MQPutMessageOptions)", new Object[] { qName, qmName, msg, pmo });
/*      */     }
/*      */ 
/*      */     
/* 2347 */     put(1, qName, qmName, (String)null, msg, pmo);
/*      */     
/* 2349 */     if (Trace.isOn) {
/* 2350 */       Trace.exit(this, "com.ibm.mq.MQQueueManager", "put(String,String,MQMessage,MQPutMessageOptions)");
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
/*      */   public synchronized void put(String qName, String qmName, MQMessage msg) throws MQException {
/* 2375 */     if (Trace.isOn) {
/* 2376 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "put(String,String,MQMessage)", new Object[] { qName, qmName, msg });
/*      */     }
/*      */ 
/*      */     
/* 2380 */     put(1, qName, qmName, (String)null, msg);
/*      */     
/* 2382 */     if (Trace.isOn) {
/* 2383 */       Trace.exit(this, "com.ibm.mq.MQQueueManager", "put(String,String,MQMessage)");
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
/*      */   public synchronized void put(String qName, MQMessage msg, MQPutMessageOptions pmo) throws MQException {
/* 2408 */     if (Trace.isOn) {
/* 2409 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "put(String,MQMessage,MQPutMessageOptions)", new Object[] { qName, msg, pmo });
/*      */     }
/*      */ 
/*      */     
/* 2413 */     put(1, qName, msg, pmo);
/*      */     
/* 2415 */     if (Trace.isOn) {
/* 2416 */       Trace.exit(this, "com.ibm.mq.MQQueueManager", "put(String,MQMessage,MQPutMessageOptions)");
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
/*      */   public synchronized void put(String qName, MQMessage msg) throws MQException {
/* 2439 */     if (Trace.isOn) {
/* 2440 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "put(String,MQMessage)", new Object[] { qName, msg });
/*      */     }
/*      */     
/* 2443 */     put(1, qName, msg);
/*      */     
/* 2445 */     if (Trace.isOn) {
/* 2446 */       Trace.exit(this, "com.ibm.mq.MQQueueManager", "put(String,MQMessage)");
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
/*      */   public synchronized void put(int type, String destinationName, MQMessage message) throws MQException {
/* 2505 */     if (Trace.isOn) {
/* 2506 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "put(int,String,MQMessage)", new Object[] {
/*      */             
/* 2508 */             Integer.valueOf(type), destinationName, message
/*      */           });
/*      */     }
/* 2511 */     MQPutMessageOptions putMessageOptions = new MQPutMessageOptions();
/* 2512 */     put(type, destinationName, message, putMessageOptions);
/*      */     
/* 2514 */     if (Trace.isOn) {
/* 2515 */       Trace.exit(this, "com.ibm.mq.MQQueueManager", "put(int,String,MQMessage)");
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
/*      */   public synchronized void put(int type, String destinationName, MQMessage message, MQPutMessageOptions putMessageOptions) throws MQException {
/* 2583 */     if (Trace.isOn) {
/* 2584 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "put(int,String,MQMessage,MQPutMessageOptions)", new Object[] {
/*      */             
/* 2586 */             Integer.valueOf(type), destinationName, message, putMessageOptions
/*      */           });
/*      */     }
/*      */     try {
/* 2590 */       put(type, destinationName, "", (String)null, message, putMessageOptions);
/*      */     } finally {
/*      */       
/* 2593 */       if (Trace.isOn) {
/* 2594 */         Trace.exit(this, "com.ibm.mq.MQQueueManager", "put(int,String,MQMessage,MQPutMessageOptions)");
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
/*      */   public synchronized void put(int type, String destinationName, String queueManagerName, String topicString, MQMessage message) throws MQException {
/* 2659 */     MQPutMessageOptions putMessageOptions = null;
/*      */     
/* 2661 */     if (Trace.isOn) {
/* 2662 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "put(int,String,String,String,MQMessage)", new Object[] {
/*      */             
/* 2664 */             Integer.valueOf(type), destinationName, queueManagerName, topicString, message
/*      */           });
/*      */     }
/*      */     try {
/* 2668 */       putMessageOptions = new MQPutMessageOptions();
/* 2669 */       put(type, destinationName, queueManagerName, topicString, message, putMessageOptions);
/*      */     
/*      */     }
/* 2672 */     catch (MQException mqe) {
/* 2673 */       if (Trace.isOn) {
/* 2674 */         Trace.catchBlock(this, "com.ibm.mq.MQQueueManager", "put(int, String, String, String, MQMessage)", mqe);
/*      */       }
/* 2676 */       throw mqe;
/*      */     } finally {
/*      */       
/* 2679 */       if (Trace.isOn) {
/* 2680 */         Trace.exit(this, "com.ibm.mq.MQQueueManager", "put(int,String,String,String,MQMessage)");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized void put(int type, String destinationName, String queueManagerName, String topicString, MQMessage message, MQPutMessageOptions putMessageOptions) throws MQException {
/* 2756 */     if (Trace.isOn) {
/* 2757 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "put(int,String,String,String,MQMessage,MQPutMessageOptions)", new Object[] {
/*      */ 
/*      */ 
/*      */             
/* 2761 */             Integer.valueOf(type), destinationName, queueManagerName, topicString, message, putMessageOptions
/*      */           });
/*      */     }
/*      */     
/* 2765 */     put(type, destinationName, queueManagerName, topicString, message, putMessageOptions, "");
/*      */ 
/*      */     
/* 2768 */     if (Trace.isOn) {
/* 2769 */       Trace.exit(this, "com.ibm.mq.MQQueueManager", "put(int,String,String,String,MQMessage,MQPutMessageOptions)");
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
/*      */   public synchronized void put(int type, String destinationName, String queueManagerName, String topicString, MQMessage message, MQPutMessageOptions putMessageOptions, String altUserId) throws MQException {
/* 2852 */     if (Trace.isOn) {
/* 2853 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "put(int,String,String,String,MQMessage,MQPutMessageOptions,String)", new Object[] {
/*      */ 
/*      */ 
/*      */             
/* 2857 */             Integer.valueOf(type), destinationName, queueManagerName, topicString, message, putMessageOptions, altUserId
/*      */           });
/*      */     }
/*      */     
/* 2861 */     String fid = "put(int,String,String,String,MQMessage,MQPutMessageOptions,String)";
/* 2862 */     if (message == null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2868 */       MQException exception = new MQException(2, 2004, this, "MQJI028");
/*      */ 
/*      */       
/* 2871 */       if (Trace.isOn) {
/* 2872 */         Trace.throwing(this, "com.ibm.mq.MQQueueManager", "put(int,String,String,String,MQMessage,MQPutMessageOptions,String)", exception, 1);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2878 */       throw exception;
/*      */     } 
/*      */     
/* 2881 */     if (putMessageOptions == null) {
/* 2882 */       MQException exception = new MQException(2, 2173, this, "MQJI029");
/*      */       
/* 2884 */       if (Trace.isOn) {
/* 2885 */         Trace.throwing(this, "com.ibm.mq.MQQueueManager", "put(int,String,String,String,MQMessage,MQPutMessageOptions,String)", exception, 2);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2891 */       throw exception;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2899 */     synchronized (message) {
/* 2900 */       MQException exception = null;
/*      */       
/*      */       try {
/*      */         int qmCcsid;
/*      */         try {
/* 2905 */           qmCcsid = this.Hconn.getHconn().getCcsid();
/*      */         }
/* 2907 */         catch (JmqiException e) {
/* 2908 */           if (Trace.isOn) {
/* 2909 */             Trace.catchBlock(this, "com.ibm.mq.MQQueueManager", "put(int,String,String,String,MQMessage,MQPutMessageOptions,String)", (Throwable)e, 1);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2915 */           qmCcsid = 0;
/*      */         } 
/* 2917 */         message.performProcessingBeforePut(qmCcsid);
/*      */ 
/*      */         
/* 2920 */         MQOD od = this.env.newMQOD();
/* 2921 */         od.setObjectType(type);
/* 2922 */         if (type == 8) {
/* 2923 */           od.setVersion(4);
/*      */         }
/* 2925 */         od.setObjectName(destinationName);
/* 2926 */         od.setObjectQMgrName(queueManagerName);
/* 2927 */         od.getObjectString().setVsString(topicString);
/* 2928 */         od.setAlternateUserId(altUserId);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2934 */         JmqiMQ jmqi = this.osession.getJmqi();
/*      */         
/* 2936 */         MQPMO jmqiPutMessageOptions = putMessageOptions.getJMQIStructure();
/* 2937 */         int options = jmqiPutMessageOptions.getOptions();
/*      */         
/* 2939 */         MQMD jmqiMessage = message.getJMQIStructure(options);
/*      */ 
/*      */         
/* 2942 */         if ((options & 0x6) == 0) {
/* 2943 */           if (Trace.isOn) {
/* 2944 */             Trace.data(this, "put(int,String,String,String,MQMessage,MQPutMessageOptions,String)", "No SyncPoint action specified- implicit MQPMO_NO_SYNCPOINT...", "");
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2950 */           jmqiPutMessageOptions.setOptions(options | 0x4);
/*      */         } 
/*      */ 
/*      */         
/* 2954 */         ByteBuffer messageData = null;
/* 2955 */         int bufferLength = 0;
/* 2956 */         if (message != null) {
/*      */           
/* 2958 */           messageData = message.getBuffer();
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2963 */           bufferLength = messageData.limit();
/*      */         } 
/*      */ 
/*      */         
/* 2967 */         jmqi.MQPUT1(this.Hconn.getHconn(), od, jmqiMessage, jmqiPutMessageOptions, bufferLength, messageData, this.completionCode, this.reason);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2972 */         if (this.completionCode.x != 0 || this.reason.x != 0) {
/*      */           
/* 2974 */           exception = new MQException(this.completionCode.x, this.reason.x, this, this.osession.getLastJmqiException());
/* 2975 */           errorOccurred(exception);
/*      */           
/* 2977 */           if (Trace.isOn) {
/* 2978 */             Trace.throwing(this, "com.ibm.mq.MQQueueManager", "put(int,String,String,String,MQMessage,MQPutMessageOptions,String)", exception, 3);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2984 */           throw exception;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2991 */         message.updateFromJMQIStructure(options);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2996 */         jmqiPutMessageOptions.setOptions(options);
/* 2997 */         putMessageOptions.updateFromJMQIStructure();
/*      */       }
/* 2999 */       catch (MQException e) {
/* 3000 */         if (Trace.isOn) {
/* 3001 */           Trace.catchBlock(this, "com.ibm.mq.MQQueueManager", "put(int,String,String,String,MQMessage,MQPutMessageOptions,String)", e, 2);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3008 */         exception = e;
/*      */       } finally {
/*      */         
/* 3011 */         if (Trace.isOn) {
/* 3012 */           Trace.finallyBlock(this, "com.ibm.mq.MQQueueManager", "put(int,String,String,String,MQMessage,MQPutMessageOptions,String)");
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         try {
/* 3020 */           message.performProcessingAfterPut();
/*      */         }
/* 3022 */         catch (MQException e) {
/* 3023 */           if (Trace.isOn) {
/* 3024 */             Trace.catchBlock(this, "com.ibm.mq.MQQueueManager", "put(int,String,String,String,MQMessage,MQPutMessageOptions,String)", e, 3);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 3031 */           if (exception == null) {
/* 3032 */             exception = e;
/*      */           }
/*      */         } 
/*      */         
/* 3036 */         if (exception != null) {
/* 3037 */           if (Trace.isOn) {
/* 3038 */             Trace.throwing(this, "com.ibm.mq.MQQueueManager", "put(int,String,String,String,MQMessage,MQPutMessageOptions,String)", exception, 4);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 3044 */           throw exception;
/*      */         } 
/*      */       } 
/*      */     } 
/* 3048 */     if (Trace.isOn) {
/* 3049 */       Trace.exit(this, "com.ibm.mq.MQQueueManager", "put(int,String,String,String,MQMessage,MQPutMessageOptions,String)");
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
/*      */   
/*      */   public synchronized void putMsg2(String qName, String qmName, MQMsg2 msg2, MQPutMessageOptions pmo, String altUserId) throws MQException {
/* 3079 */     if (Trace.isOn) {
/* 3080 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "putMsg2(String,String,MQMsg2,MQPutMessageOptions,String)", new Object[] { qName, qmName, msg2, pmo, altUserId });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 3085 */       if (!this.connected) {
/* 3086 */         MQException traceRet1 = new MQException(2, 2018, this);
/*      */ 
/*      */ 
/*      */         
/* 3090 */         if (Trace.isOn) {
/* 3091 */           Trace.throwing(this, "com.ibm.mq.MQQueueManager", "putMsg2(String,String,MQMsg2,MQPutMessageOptions,String)", traceRet1, 1);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3097 */         throw traceRet1;
/*      */       } 
/*      */       
/* 3100 */       MQOD od = new MQOD();
/* 3101 */       if (qName != null && qName.length() > 0) {
/* 3102 */         od.ObjectName = qName;
/*      */       }
/*      */       
/* 3105 */       if (qmName != null && qmName.length() > 0) {
/* 3106 */         od.ObjectQMgrName = qmName;
/*      */       }
/*      */       
/* 3109 */       if (altUserId != null && altUserId.length() > 0) {
/* 3110 */         od.AlternateUserId = altUserId;
/*      */       }
/*      */       
/* 3113 */       ByteBuffer msgData = null;
/* 3114 */       if (msg2 != null) {
/* 3115 */         msgData = msg2.getInternalBuffer();
/* 3116 */         this.osession.MQPUT1(this.Hconn.getHconn(), od, msg2, pmo, msg2
/* 3117 */             .getMessageDataLength(), msgData, this.completionCode, this.reason);
/*      */       }
/*      */       else {
/*      */         
/* 3121 */         this.osession.MQPUT1(this.Hconn.getHconn(), od, (MQMsg2)null, pmo, 0, (ByteBuffer)null, this.completionCode, this.reason);
/*      */       } 
/*      */ 
/*      */       
/* 3125 */       if (this.completionCode.x != 0 || this.reason.x != 0) {
/*      */         
/* 3127 */         MQException mqe = new MQException(this.completionCode.x, this.reason.x, this, this.osession.getLastJmqiException());
/* 3128 */         errorOccurred(mqe);
/* 3129 */         if (Trace.isOn) {
/* 3130 */           Trace.throwing(this, "com.ibm.mq.MQQueueManager", "putMsg2(String,String,MQMsg2,MQPutMessageOptions,String)", mqe, 2);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3136 */         throw mqe;
/*      */       } 
/*      */     } finally {
/*      */       
/* 3140 */       if (Trace.isOn) {
/* 3141 */         Trace.finallyBlock(this, "com.ibm.mq.MQQueueManager", "putMsg2(String,String,MQMsg2,MQPutMessageOptions,String)");
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 3146 */     if (Trace.isOn) {
/* 3147 */       Trace.exit(this, "com.ibm.mq.MQQueueManager", "putMsg2(String,String,MQMsg2,MQPutMessageOptions,String)");
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
/*      */   public synchronized void putMsg2(String qName, String qmName, MQMsg2 msg2, MQPutMessageOptions pmo) throws MQException {
/* 3170 */     if (Trace.isOn) {
/* 3171 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "putMsg2(String,String,MQMsg2,MQPutMessageOptions)", new Object[] { qName, qmName, msg2, pmo });
/*      */     }
/*      */ 
/*      */     
/* 3175 */     putMsg2(qName, qmName, msg2, pmo, "");
/*      */     
/* 3177 */     if (Trace.isOn) {
/* 3178 */       Trace.exit(this, "com.ibm.mq.MQQueueManager", "putMsg2(String,String,MQMsg2,MQPutMessageOptions)");
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
/*      */   public synchronized void putMsg2(String qName, String qmName, MQMsg2 msg2) throws MQException {
/* 3203 */     if (Trace.isOn) {
/* 3204 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "putMsg2(String,String,MQMsg2)", new Object[] { qName, qmName, msg2 });
/*      */     }
/*      */ 
/*      */     
/* 3208 */     putMsg2(qName, qmName, msg2, new MQPutMessageOptions(), (String)null);
/*      */     
/* 3210 */     if (Trace.isOn) {
/* 3211 */       Trace.exit(this, "com.ibm.mq.MQQueueManager", "putMsg2(String,String,MQMsg2)");
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
/*      */   public synchronized void putMsg2(String qName, MQMsg2 msg2, MQPutMessageOptions pmo) throws MQException {
/* 3236 */     if (Trace.isOn) {
/* 3237 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "putMsg2(String,MQMsg2,MQPutMessageOptions)", new Object[] { qName, msg2, pmo });
/*      */     }
/*      */ 
/*      */     
/* 3241 */     putMsg2(qName, "", msg2, pmo, "");
/*      */     
/* 3243 */     if (Trace.isOn) {
/* 3244 */       Trace.exit(this, "com.ibm.mq.MQQueueManager", "putMsg2(String,MQMsg2,MQPutMessageOptions)");
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
/*      */   public synchronized void putMsg2(String qName, MQMsg2 msg2) throws MQException {
/* 3267 */     if (Trace.isOn) {
/* 3268 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "putMsg2(String,MQMsg2)", new Object[] { qName, msg2 });
/*      */     }
/*      */     
/* 3271 */     putMsg2(qName, "", msg2, new MQPutMessageOptions(), (String)null);
/*      */     
/* 3273 */     if (Trace.isOn) {
/* 3274 */       Trace.exit(this, "com.ibm.mq.MQQueueManager", "putMsg2(String,MQMsg2)");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized MQQueue accessQueue(String queueName, int openOptionsArg, String queueManagerName, String dynamicQueueName, String alternateUserIdArg) throws MQException {
/* 3365 */     MQQueue queue = null;
/*      */     
/* 3367 */     if (Trace.isOn) {
/* 3368 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "accessQueue(String,int,String,String,String)", new Object[] { queueName, 
/*      */             
/* 3370 */             Integer.valueOf(openOptionsArg), queueManagerName, dynamicQueueName, alternateUserIdArg });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 3377 */       if (queueName.equals("SERVICE1.DUMP") || queueName.equals("SERVICE2.DUMP")) {
/* 3378 */         queue = new ZOSDumpQmgrQueue(this, queueName, openOptionsArg, queueManagerName);
/*      */       }
/*      */       else {
/*      */         
/* 3382 */         queue = new MQQueue(this, queueName, openOptionsArg, queueManagerName, dynamicQueueName, alternateUserIdArg);
/*      */       }
/*      */     
/*      */     }
/* 3386 */     catch (MQException mqe) {
/* 3387 */       if (Trace.isOn) {
/* 3388 */         Trace.catchBlock(this, "com.ibm.mq.MQQueueManager", "accessQueue(String, int, String, String, String)", mqe);
/*      */       }
/*      */       
/* 3391 */       throw mqe;
/*      */     } finally {
/*      */       
/* 3394 */       if (Trace.isOn) {
/* 3395 */         Trace.exit(this, "com.ibm.mq.MQQueueManager", "accessQueue(String,int,String,String,String)", queue);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 3400 */     return queue;
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
/*      */   public synchronized MQQueue accessQueue(String queueName, int openOptionsArg) throws MQException {
/* 3422 */     MQQueue queue = null;
/*      */     
/* 3424 */     if (Trace.isOn) {
/* 3425 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "accessQueue(String,int)", new Object[] { queueName, 
/*      */             
/* 3427 */             Integer.valueOf(openOptionsArg) });
/*      */     }
/*      */     
/*      */     try {
/* 3431 */       queue = accessQueue(queueName, openOptionsArg, (String)null, (String)null, (String)null);
/*      */     }
/* 3433 */     catch (MQException mqe) {
/* 3434 */       if (Trace.isOn) {
/* 3435 */         Trace.catchBlock(this, "com.ibm.mq.MQQueueManager", "accessQueue(String, int)", mqe);
/*      */       }
/*      */       
/* 3438 */       throw mqe;
/*      */     } finally {
/*      */       
/* 3441 */       if (Trace.isOn) {
/* 3442 */         Trace.exit(this, "com.ibm.mq.MQQueueManager", "accessQueue(String,int)", queue);
/*      */       }
/*      */     } 
/*      */     
/* 3446 */     return queue;
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
/*      */   public synchronized MQTopic accessTopic(String topicName, String topicObject, int openAs, int options) throws MQException {
/* 3565 */     MQTopic topic = null;
/*      */     
/* 3567 */     if (Trace.isOn) {
/* 3568 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "accessTopic(String,String,int,int)", new Object[] { topicName, topicObject, 
/*      */             
/* 3570 */             Integer.valueOf(openAs), 
/* 3571 */             Integer.valueOf(options) });
/*      */     }
/*      */     try {
/* 3574 */       topic = new MQTopic(this, topicName, topicObject, openAs, options);
/*      */     }
/* 3576 */     catch (MQException mqe) {
/* 3577 */       if (Trace.isOn) {
/* 3578 */         Trace.catchBlock(this, "com.ibm.mq.MQQueueManager", "accessTopic(String,String,int,int)", mqe);
/*      */       }
/* 3580 */       throw mqe;
/*      */     } finally {
/*      */       
/* 3583 */       if (Trace.isOn) {
/* 3584 */         Trace.exit(this, "com.ibm.mq.MQQueueManager", "accessTopic(String,String,int,int)", topic);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 3589 */     return topic;
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
/*      */   public synchronized MQTopic accessTopic(String topicName, String topicObject, int openAs, int options, String altUserId) throws MQException {
/* 3724 */     MQTopic topic = null;
/*      */     
/* 3726 */     if (Trace.isOn) {
/* 3727 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "accessTopic(String,String,int,int,String)", new Object[] { topicName, topicObject, 
/*      */             
/* 3729 */             Integer.valueOf(openAs), 
/* 3730 */             Integer.valueOf(options), altUserId });
/*      */     }
/*      */     try {
/* 3733 */       topic = new MQTopic(this, topicName, topicObject, openAs, options, altUserId);
/*      */     }
/* 3735 */     catch (MQException mqe) {
/* 3736 */       if (Trace.isOn) {
/* 3737 */         Trace.catchBlock(this, "com.ibm.mq.MQQueueManager", "accessTopic(String, String, int, int, String)", mqe);
/*      */       }
/* 3739 */       throw mqe;
/*      */     } finally {
/*      */       
/* 3742 */       if (Trace.isOn) {
/* 3743 */         Trace.exit(this, "com.ibm.mq.MQQueueManager", "accessTopic(String,String,int,int,String)", topic);
/*      */       }
/*      */     } 
/*      */     
/* 3747 */     return topic;
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
/*      */   public synchronized MQTopic accessTopic(String topicName, String topicObject, int options, String altUserId, String subscriptionName) throws MQException {
/* 3859 */     MQTopic topic = null;
/*      */     
/* 3861 */     if (Trace.isOn) {
/* 3862 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "accessTopic(String,String,int,String,String)", new Object[] { topicName, topicObject, 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 3867 */             Integer.valueOf(options), altUserId, subscriptionName });
/*      */     }
/*      */     
/*      */     try {
/* 3871 */       topic = new MQTopic(this, topicName, topicObject, options, altUserId, subscriptionName);
/*      */     
/*      */     }
/* 3874 */     catch (MQException mqe) {
/* 3875 */       if (Trace.isOn) {
/* 3876 */         Trace.catchBlock(this, "com.ibm.com.mq.MQQueueManager", "accessTopic(String, String, int, String, String)", mqe);
/*      */       }
/* 3878 */       throw mqe;
/*      */     } finally {
/*      */       
/* 3881 */       if (Trace.isOn) {
/* 3882 */         Trace.exit(this, "com.ibm.mq.MQQueueManager", "accessTopic(String,String,int,String,String)", topic);
/*      */       }
/*      */     } 
/*      */     
/* 3886 */     return topic;
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
/*      */   public synchronized MQTopic accessTopic(String topicName, String topicObject, int options, String altUserId, String subscriptionName, Hashtable<String, Object> parameters) throws MQException {
/* 4026 */     MQTopic topic = null;
/*      */     
/* 4028 */     if (Trace.isOn) {
/* 4029 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "accessTopic(String,String,int,String,String,Hashtable<String , Object>)", new Object[] { topicName, topicObject, 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 4034 */             Integer.valueOf(options), altUserId, subscriptionName, parameters });
/*      */     }
/*      */     
/*      */     try {
/* 4038 */       topic = new MQTopic(this, topicName, topicObject, options, altUserId, subscriptionName, parameters);
/*      */ 
/*      */     
/*      */     }
/* 4042 */     catch (MQException mqe) {
/* 4043 */       if (Trace.isOn) {
/* 4044 */         Trace.catchBlock(this, "com.ibm.mq.MQQueueManager", "accessTopic(String, String, int, String, String, Hashtable<String, Object>)", mqe);
/*      */       }
/* 4046 */       throw mqe;
/*      */     } finally {
/*      */       
/* 4049 */       if (Trace.isOn) {
/* 4050 */         Trace.exit(this, "com.ibm.mq.MQQueueManager", "accessTopic(String,String,int,String,String,Hashtable<String , Object>)", topic);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4057 */     return topic;
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
/*      */   public synchronized MQTopic accessTopic(MQDestination destination, String topicName, String topicObject, int options) throws MQException {
/* 4165 */     MQTopic topic = null;
/*      */     
/* 4167 */     if (Trace.isOn) {
/* 4168 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "accessTopic(MQDestination,String,String,int)", new Object[] { destination, topicName, topicObject, 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 4173 */             Integer.valueOf(options) });
/*      */     }
/*      */     try {
/* 4176 */       topic = new MQTopic(this, destination, topicName, topicObject, options);
/*      */     }
/* 4178 */     catch (MQException mqe) {
/* 4179 */       if (Trace.isOn) {
/* 4180 */         Trace.catchBlock(this, "com.ibm.mq.MQQueueManager", "accessTopic(MQDestination, String, String, int", mqe);
/*      */       }
/* 4182 */       throw mqe;
/*      */     } finally {
/*      */       
/* 4185 */       if (Trace.isOn) {
/* 4186 */         Trace.exit(this, "com.ibm.mq.MQQueueManager", "accessTopic(MQDestination,String,String,int)", topic);
/*      */       }
/*      */     } 
/*      */     
/* 4190 */     return topic;
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
/*      */   public synchronized MQTopic accessTopic(MQDestination destination, String topicName, String topicObject, int options, String altUserId) throws MQException {
/* 4311 */     MQTopic topic = null;
/*      */     
/* 4313 */     if (Trace.isOn) {
/* 4314 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "accessTopic(MQDestination,String,String,int,String)", new Object[] { destination, topicName, topicObject, 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 4319 */             Integer.valueOf(options), altUserId });
/*      */     }
/*      */     try {
/* 4322 */       topic = new MQTopic(this, destination, topicName, topicObject, options, altUserId);
/*      */     }
/* 4324 */     catch (MQException mqe) {
/* 4325 */       if (Trace.isOn) {
/* 4326 */         Trace.catchBlock(this, "com.ibm.mq.MQQueueManager", "accessTopic(MQDestination,String,String,int,String)", mqe);
/*      */       }
/*      */       
/* 4329 */       throw mqe;
/*      */     } finally {
/*      */       
/* 4332 */       if (Trace.isOn) {
/* 4333 */         Trace.exit(this, "com.ibm.mq.MQQueueManager", "accessTopic(MQDestination,String,String,int,String)", topic);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 4338 */     return topic;
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
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized MQTopic accessTopic(MQDestination destination, String topicName, String topicObject, int options, String altUserId, String subscriptionName) throws MQException {
/* 4489 */     MQTopic topic = null;
/*      */     
/* 4491 */     if (Trace.isOn) {
/* 4492 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "accessTopic(MQDestination,String,String,int,String,String)", new Object[] { destination, topicName, topicObject, 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 4497 */             Integer.valueOf(options), altUserId, subscriptionName });
/*      */     }
/*      */     
/*      */     try {
/* 4501 */       topic = new MQTopic(this, destination, topicName, topicObject, options, altUserId, subscriptionName);
/*      */     }
/* 4503 */     catch (MQException mqe) {
/* 4504 */       if (Trace.isOn) {
/* 4505 */         Trace.catchBlock(this, "com.ibm.mq.MQQueueManager", "accessTopic(MQDestination,String,String,int,String,String)", mqe);
/*      */       }
/*      */       
/* 4508 */       throw mqe;
/*      */     } finally {
/*      */       
/* 4511 */       if (Trace.isOn) {
/* 4512 */         Trace.exit(this, "com.ibm.mq.MQQueueManager", "accessTopic(MQDestination,String,String,int,String,String)", topic);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4519 */     return topic;
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
/*      */   public synchronized MQTopic accessTopic(MQDestination destination, String topicName, String topicObject, int options, String altUserId, String subscriptionName, Hashtable<String, Object> parameters) throws MQException {
/* 4697 */     MQTopic topic = null;
/*      */     
/* 4699 */     if (Trace.isOn) {
/* 4700 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "accessTopic(MQDestination,String,String,int,String,String,Hashtable<String , Object>)", new Object[] { destination, topicName, topicObject, 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 4705 */             Integer.valueOf(options), altUserId, subscriptionName, parameters });
/*      */     }
/*      */     
/*      */     try {
/* 4709 */       topic = new MQTopic(this, destination, topicName, topicObject, options, altUserId, subscriptionName, parameters);
/*      */     
/*      */     }
/* 4712 */     catch (MQException mqe) {
/* 4713 */       if (Trace.isOn) {
/* 4714 */         Trace.catchBlock(this, "com.ibm.mq.MQQueueManager", "accessTopic(MQDestination,String,String,int,String,String,Hashtable<String , Object>)", mqe);
/*      */       }
/*      */       
/* 4717 */       throw mqe;
/*      */     } finally {
/*      */       
/* 4720 */       if (Trace.isOn) {
/* 4721 */         Trace.exit(this, "com.ibm.mq.MQQueueManager", "accessTopic(MQDestination,String,String,int,String,String,Hashtable<String , Object>)", topic);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4728 */     return topic;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized MQProcess accessProcess(String processName, int openOptionsArg, String queueManagerName, String alternateUserIdArg) throws MQException {
/* 4773 */     if (Trace.isOn) {
/* 4774 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "accessProcess(String,int,String,String)", new Object[] { processName, 
/*      */             
/* 4776 */             Integer.valueOf(openOptionsArg), queueManagerName, alternateUserIdArg });
/*      */     }
/*      */     
/* 4779 */     String fid = "accessProcess(String,int,String,String)";
/* 4780 */     int openOptionsVar = openOptionsArg;
/* 4781 */     if (!this.connected) {
/* 4782 */       MQException traceRet1 = new MQException(2, 2018, this, "MQJI002");
/*      */ 
/*      */       
/* 4785 */       if (Trace.isOn) {
/* 4786 */         Trace.throwing(this, "com.ibm.mq.MQQueueManager", "accessProcess(String,int,String,String)", traceRet1, 1);
/*      */       }
/*      */       
/* 4789 */       throw traceRet1;
/*      */     } 
/*      */     
/* 4792 */     MQOD od = new MQOD();
/* 4793 */     od.ObjectType = 3;
/*      */     
/* 4795 */     if (processName != null && processName.length() > 0) {
/* 4796 */       od.ObjectName = processName;
/*      */     }
/*      */     
/* 4799 */     if (queueManagerName != null && queueManagerName.length() > 0) {
/* 4800 */       od.ObjectQMgrName = queueManagerName;
/*      */     }
/*      */     
/* 4803 */     if (alternateUserIdArg != null && alternateUserIdArg.length() > 0) {
/* 4804 */       od.AlternateUserId = alternateUserIdArg;
/*      */     }
/*      */     
/* 4807 */     MQProcess p = new MQProcess();
/* 4808 */     p.Hconn = this.Hconn;
/* 4809 */     p.connected = this.connected;
/*      */     
/* 4811 */     openOptionsVar |= 0x20;
/*      */     
/* 4813 */     if (Trace.isOn) {
/* 4814 */       Trace.data(this, "accessProcess(String,int,String,String)", "process = " + od.ObjectName + "\nqueue manager = " + od.ObjectQMgrName + "\nalternate user id = " + od.AlternateUserId + "\noptions = " + openOptionsVar, "");
/*      */     }
/*      */ 
/*      */     
/* 4818 */     this.osession.MQOPEN(p.Hconn.getHconn(), od, openOptionsVar, p.Hobj, this.completionCode, this.reason);
/*      */ 
/*      */     
/* 4821 */     if (this.completionCode.x != 0 || this.reason.x != 0) {
/* 4822 */       p.resourceOpen = false;
/*      */       
/* 4824 */       MQException mqe = new MQException(this.completionCode.x, this.reason.x, this, this.osession.getLastJmqiException());
/* 4825 */       errorOccurred(mqe);
/* 4826 */       if (Trace.isOn) {
/* 4827 */         Trace.throwing(this, "com.ibm.mq.MQQueueManager", "accessProcess(String,int,String,String)", mqe, 2);
/*      */       }
/*      */       
/* 4830 */       throw mqe;
/*      */     } 
/*      */     
/* 4833 */     p.resourceOpen = true;
/*      */     
/* 4835 */     this.processes.addElement(p);
/*      */ 
/*      */     
/* 4838 */     p.name = processName;
/* 4839 */     p.openOptions = openOptionsVar;
/*      */ 
/*      */     
/* 4842 */     p.openStatus = true;
/*      */     
/* 4844 */     p.parentQmgr = this;
/* 4845 */     p.connectionReference = this;
/* 4846 */     if (alternateUserIdArg != null) {
/* 4847 */       p.alternateUserId = alternateUserIdArg;
/*      */     }
/* 4849 */     p.mqca_description = 2011;
/*      */     
/* 4851 */     if (Trace.isOn) {
/* 4852 */       Trace.exit(this, "com.ibm.mq.MQQueueManager", "accessProcess(String,int,String,String)", p);
/*      */     }
/*      */     
/* 4855 */     return p;
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
/*      */   public synchronized MQProcess accessProcess(String processName, int openOptionsArg) throws MQException {
/* 4877 */     MQProcess traceRet1 = null;
/*      */     
/* 4879 */     if (Trace.isOn) {
/* 4880 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "accessProcess(String,int)", new Object[] { processName, 
/*      */             
/* 4882 */             Integer.valueOf(openOptionsArg) });
/*      */     }
/*      */     try {
/* 4885 */       traceRet1 = accessProcess(processName, openOptionsArg, (String)null, (String)null);
/*      */     }
/* 4887 */     catch (MQException mqe) {
/* 4888 */       if (Trace.isOn) {
/* 4889 */         Trace.catchBlock(this, "com.ibm.mq.MQQueueManager", "accessProcess(String,int)", mqe);
/*      */       }
/*      */       
/* 4892 */       throw mqe;
/*      */     } finally {
/*      */       
/* 4895 */       if (Trace.isOn) {
/* 4896 */         Trace.exit(this, "com.ibm.mq.MQQueueManager", "accessProcess(String,int)", traceRet1);
/*      */       }
/*      */     } 
/*      */     
/* 4900 */     return traceRet1;
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
/*      */   public synchronized MQDistributionList accessDistributionList(MQDistributionListItem[] items, int options, String id) throws MQException {
/* 4920 */     if (Trace.isOn) {
/* 4921 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "accessDistributionList(MQDistributionListItem [ ],int,String)", new Object[] { items, 
/*      */ 
/*      */ 
/*      */             
/* 4925 */             Integer.valueOf(options), id });
/*      */     }
/*      */     try {
/* 4928 */       MQDistributionList list = new MQDistributionList(this, items, options, id);
/*      */ 
/*      */       
/* 4931 */       if (Trace.isOn) {
/* 4932 */         Trace.exit(this, "com.ibm.mq.MQQueueManager", "accessDistributionList(MQDistributionListItem [ ],int,String)", list);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4938 */       return list;
/*      */     } finally {
/*      */       
/* 4941 */       if (Trace.isOn) {
/* 4942 */         Trace.finallyBlock(this, "com.ibm.mq.MQQueueManager", "accessDistributionList(MQDistributionListItem [ ],int,String)");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQDistributionList accessDistributionList(MQDistributionListItem[] items, int options) throws MQException {
/* 4962 */     MQDistributionList traceRet1 = null;
/*      */     
/* 4964 */     if (Trace.isOn) {
/* 4965 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "accessDistributionList(MQDistributionListItem [ ],int)", new Object[] { items, 
/*      */             
/* 4967 */             Integer.valueOf(options) });
/*      */     }
/*      */     try {
/* 4970 */       traceRet1 = accessDistributionList(items, options, (String)null);
/*      */     }
/* 4972 */     catch (MQException mqe) {
/* 4973 */       if (Trace.isOn) {
/* 4974 */         Trace.catchBlock(this, "com.ibm.mq.MQQueueManager", "accessDistributionList(MQDistributionListItem [ ],int)", mqe);
/*      */       }
/*      */       
/* 4977 */       throw mqe;
/*      */     } finally {
/*      */       
/* 4980 */       if (Trace.isOn) {
/* 4981 */         Trace.exit(this, "com.ibm.mq.MQQueueManager", "accessDistributionList(MQDistributionListItem [ ],int)", traceRet1);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 4986 */     return traceRet1;
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
/*      */   public MQAsyncStatus getAsyncStatus() throws MQException {
/* 5004 */     MQAsyncStatus asyncStatus = null;
/*      */     
/*      */     try {
/* 5007 */       asyncStatus = new MQAsyncStatus(this);
/* 5008 */       asyncStatus.updateAsyncStatus();
/*      */     } finally {
/*      */       
/* 5011 */       if (Trace.isOn) {
/* 5012 */         Trace.data(this, "com.ibm.mq.MQQueueManager", "getAsyncStatus()", "getter", asyncStatus);
/*      */       }
/*      */     } 
/*      */     
/* 5016 */     return asyncStatus;
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
/*      */   void cleanup() {
/* 5028 */     if (Trace.isOn) {
/* 5029 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "cleanup()");
/*      */     }
/* 5031 */     String fid = "cleanup()";
/* 5032 */     if (this.copy != null) {
/*      */ 
/*      */       
/* 5035 */       synchronized (this.copy) {
/* 5036 */         if (Trace.isOn) {
/* 5037 */           Trace.data(this, "cleanup()", "Delegating cleanup to " + this.copy, "");
/*      */         }
/* 5039 */         this.copy.cleanup();
/* 5040 */         this.isConnected = false;
/* 5041 */         this.connectStatus = false;
/* 5042 */         this.connected = false;
/*      */         
/* 5044 */         this.openStatus = false;
/* 5045 */         this.resourceOpen = false;
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 5050 */       int counter = 0;
/* 5051 */       for (MQQueue q : (MQQueue[])this.queues.<MQQueue>toArray(new MQQueue[this.queues.size()])) {
/*      */         
/* 5053 */         try { if (Trace.isOn) {
/* 5054 */             Trace.data(this, "cleanup()", "Queue[" + counter + "]: " + q, "");
/*      */           }
/*      */           
/* 5057 */           counter++;
/* 5058 */           if (q.resourceOpen) {
/* 5059 */             if (Trace.isOn) {
/* 5060 */               Trace.data(this, "cleanup()", "Closing queue " + q, "");
/*      */             }
/* 5062 */             q.close();
/*      */           
/*      */           }
/* 5065 */           else if (Trace.isOn) {
/* 5066 */             Trace.data(this, "cleanup()", "Queue " + q + " has already been closed", "");
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 5077 */           q.connected = false; } catch (Exception ex) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.mq.MQQueueManager", "cleanup()", ex, 1);  } finally { q.connected = false; }
/*      */       
/*      */       } 
/*      */       
/* 5081 */       this.queues.removeAllElements();
/* 5082 */       if (Trace.isOn) {
/* 5083 */         Trace.data(this, "cleanup()", "All queues closed.", "");
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 5093 */       counter = 0;
/* 5094 */       for (MQTopic topic : (MQTopic[])this.topics.<MQTopic>toArray(new MQTopic[this.topics.size()])) {
/*      */         
/* 5096 */         try { if (Trace.isOn) {
/* 5097 */             Trace.data(this, "cleanup()", "Topic[" + counter + "]: " + topic, "");
/*      */           }
/* 5099 */           counter++;
/* 5100 */           if (topic.resourceOpen) {
/* 5101 */             if (Trace.isOn) {
/* 5102 */               Trace.data(this, "cleanup()", "Closing topic " + topic, "");
/*      */             }
/*      */             
/* 5105 */             topic.close();
/*      */           
/*      */           }
/* 5108 */           else if (Trace.isOn) {
/* 5109 */             Trace.data(this, "cleanup()", "Topic " + topic + " has already been closed", "");
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 5119 */           topic.connected = false; } catch (MQException e) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.mq.MQQueueManager", "cleanup()", e, 2);  } finally { topic.connected = false; }
/*      */       
/*      */       } 
/*      */       
/* 5123 */       this.topics.clear();
/* 5124 */       if (Trace.isOn) {
/* 5125 */         Trace.data(this, "cleanup()", "All topics closed.", "");
/*      */       }
/*      */ 
/*      */       
/* 5129 */       counter = 0;
/*      */       
/* 5131 */       for (MQProcess p : (MQProcess[])this.processes.<MQProcess>toArray(new MQProcess[this.processes.size()])) {
/*      */         
/* 5133 */         try { if (Trace.isOn) {
/* 5134 */             Trace.data(this, "cleanup()", "Process[" + counter + "]: " + p, "");
/*      */           }
/*      */           
/* 5137 */           counter++;
/* 5138 */           if (p.resourceOpen) {
/*      */             
/* 5140 */             if (Trace.isOn) {
/* 5141 */               Trace.data(this, "cleanup()", "Closing process:" + p, "");
/*      */             }
/*      */             
/* 5144 */             p.close();
/*      */           
/*      */           }
/* 5147 */           else if (Trace.isOn) {
/* 5148 */             Trace.data(this, "cleanup()", "Process " + p + " has already been closed", "");
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 5159 */           p.connected = false; } catch (Exception ex) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.mq.MQQueueManager", "cleanup()", ex, 3);  } finally { p.connected = false; }
/*      */       
/*      */       } 
/*      */       
/* 5163 */       this.processes.removeAllElements();
/* 5164 */       if (Trace.isOn) {
/* 5165 */         Trace.data(this, "cleanup()", "All processes closed.", "");
/*      */       }
/*      */ 
/*      */       
/* 5169 */       counter = 0;
/*      */       
/* 5171 */       for (MQDistributionList dl : (MQDistributionList[])this.distributionLists.<MQDistributionList>toArray(new MQDistributionList[0])) {
/*      */         
/* 5173 */         try { if (Trace.isOn) {
/* 5174 */             Trace.data(this, "cleanup()", "Distribution list[" + counter + "]: " + dl, "");
/*      */           }
/* 5176 */           counter++;
/* 5177 */           if (dl.resourceOpen) {
/* 5178 */             if (Trace.isOn) {
/* 5179 */               Trace.data(this, "cleanup()", "Closing distribution list:" + dl, "");
/*      */             }
/* 5181 */             dl.close();
/*      */           
/*      */           }
/* 5184 */           else if (Trace.isOn) {
/* 5185 */             Trace.data(this, "cleanup()", "Distribution list " + dl + " has already been closed", "");
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 5196 */           dl.connected = false; } catch (Exception ex) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.mq.MQQueueManager", "cleanup()", ex, 4);  } finally { dl.connected = false; }
/*      */       
/*      */       } 
/*      */       
/* 5200 */       this.distributionLists.removeAllElements();
/* 5201 */       if (Trace.isOn) {
/* 5202 */         Trace.data(this, "cleanup()", "All distribution lists closed.", "");
/*      */       }
/*      */       
/*      */       try {
/* 5206 */         close();
/*      */       }
/* 5208 */       catch (MQException ex) {
/* 5209 */         if (Trace.isOn) {
/* 5210 */           Trace.catchBlock(this, "com.ibm.mq.MQQueueManager", "cleanup()", ex, 5);
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 5216 */       this.isConnected = false;
/* 5217 */       this.connectStatus = false;
/* 5218 */       this.connected = false;
/* 5219 */       this.connectionReference = null;
/*      */     } 
/*      */     
/* 5222 */     if (Trace.isOn) {
/* 5223 */       Trace.exit(this, "com.ibm.mq.MQQueueManager", "cleanup()");
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
/*      */   protected final void registerQueue(MQQueue q) {
/* 5236 */     if (Trace.isOn) {
/* 5237 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "registerQueue(MQQueue)", new Object[] { q });
/*      */     }
/*      */     
/* 5240 */     this.queues.addElement(q);
/*      */     
/* 5242 */     if (Trace.isOn) {
/* 5243 */       Trace.exit(this, "com.ibm.mq.MQQueueManager", "registerQueue(MQQueue)");
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
/*      */   protected final void registerTopic(MQTopic topic) {
/* 5257 */     if (Trace.isOn) {
/* 5258 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "registerTopic(MQTopic)", new Object[] { topic });
/*      */     }
/*      */     
/* 5261 */     this.topics.add(topic);
/*      */     
/* 5263 */     if (Trace.isOn) {
/* 5264 */       Trace.exit(this, "com.ibm.mq.MQQueueManager", "registerTopic(MQTopic)");
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
/*      */   protected final void registerProcess(MQProcess p) {
/* 5277 */     if (Trace.isOn) {
/* 5278 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "registerProcess(MQProcess)", new Object[] { p });
/*      */     }
/*      */     
/* 5281 */     this.processes.addElement(p);
/*      */     
/* 5283 */     if (Trace.isOn) {
/* 5284 */       Trace.exit(this, "com.ibm.mq.MQQueueManager", "registerProcess(MQProcess)");
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
/*      */   protected final void registerDistributionList(MQDistributionList dl) {
/* 5299 */     if (Trace.isOn) {
/* 5300 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "registerDistributionList(MQDistributionList)", new Object[] { dl });
/*      */     }
/*      */ 
/*      */     
/* 5304 */     this.distributionLists.addElement(dl);
/*      */     
/* 5306 */     if (Trace.isOn) {
/* 5307 */       Trace.exit(this, "com.ibm.mq.MQQueueManager", "registerDistributionList(MQDistributionList)");
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
/*      */   protected final void unregisterQueue(MQQueue q) {
/* 5321 */     if (Trace.isOn) {
/* 5322 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "unregisterQueue(MQQueue)", new Object[] { q });
/*      */     }
/*      */     
/* 5325 */     this.queues.removeElement(q);
/*      */     
/* 5327 */     if (Trace.isOn) {
/* 5328 */       Trace.exit(this, "com.ibm.mq.MQQueueManager", "unregisterQueue(MQQueue)");
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
/*      */   protected final void unregisterTopic(MQTopic topic) {
/* 5343 */     if (Trace.isOn) {
/* 5344 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "unregisterTopic(MQTopic)", new Object[] { topic });
/*      */     }
/*      */     
/* 5347 */     this.topics.remove(topic);
/*      */     
/* 5349 */     if (Trace.isOn) {
/* 5350 */       Trace.exit(this, "com.ibm.mq.MQQueueManager", "unregisterTopic(MQTopic)");
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
/*      */   protected final void unregisterProcess(MQProcess p) {
/* 5363 */     if (Trace.isOn) {
/* 5364 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "unregisterProcess(MQProcess)", new Object[] { p });
/*      */     }
/*      */     
/* 5367 */     this.processes.removeElement(p);
/*      */     
/* 5369 */     if (Trace.isOn) {
/* 5370 */       Trace.exit(this, "com.ibm.mq.MQQueueManager", "unregisterProcess(MQProcess)");
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
/*      */   protected final void unregisterDistributionList(MQDistributionList dl) {
/* 5385 */     if (Trace.isOn) {
/* 5386 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "unregisterDistributionList(MQDistributionList)", new Object[] { dl });
/*      */     }
/*      */ 
/*      */     
/* 5390 */     this.distributionLists.removeElement(dl);
/*      */     
/* 5392 */     if (Trace.isOn) {
/* 5393 */       Trace.exit(this, "com.ibm.mq.MQQueueManager", "unregisterDistributionList(MQDistributionList)");
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
/*      */   void errorOccurred(MQException mqe) {
/* 5408 */     if (Trace.isOn) {
/* 5409 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "errorOccurred(MQException)", new Object[] { mqe });
/*      */     }
/*      */     
/* 5412 */     String fid = "errorOccurred(MQException)";
/* 5413 */     if (this.allowErrorEvents && mqe.completionCode == 2) {
/* 5414 */       int type = ReasonCodeInfo.getReasonCodeCategory(mqe.reasonCode);
/* 5415 */       switch (type) {
/*      */         case 0:
/*      */         case 1:
/*      */         case 2:
/*      */         case 5:
/*      */           break;
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
/*      */         case 3:
/*      */         case 4:
/*      */         case 15:
/* 5435 */           this.mqManCon.setNotReusable();
/* 5436 */           if (this.original != null) {
/* 5437 */             this.mqManCon.fireConnectionErrorEvent(this.original, mqe);
/*      */             break;
/*      */           } 
/* 5440 */           this.mqManCon.fireConnectionErrorEvent(this, mqe);
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         default:
/* 5446 */           if (Trace.isOn) {
/* 5447 */             Trace.data(this, "errorOccurred(MQException)", "Bad Reason Code Category encountered", "");
/*      */           }
/*      */           
/* 5450 */           this.mqManCon.setNotReusable();
/* 5451 */           if (this.original != null) {
/* 5452 */             this.mqManCon.fireConnectionErrorEvent(this.original, mqe);
/*      */             break;
/*      */           } 
/* 5455 */           this.mqManCon.fireConnectionErrorEvent(this, mqe);
/*      */           break;
/*      */       } 
/*      */ 
/*      */     
/*      */     } 
/* 5461 */     if (Trace.isOn) {
/* 5462 */       Trace.exit(this, "com.ibm.mq.MQQueueManager", "errorOccurred(MQException)");
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
/*      */   void setExceptionForDisconnect(MQException mqe) {
/* 5478 */     if (Trace.isOn) {
/* 5479 */       Trace.data(this, "com.ibm.mq.MQQueueManager", "setExceptionForDisconnect(MQException)", "setter", mqe);
/*      */     }
/*      */     
/* 5482 */     this.exceptionForDisconnect = mqe;
/* 5483 */     if (this.copy != null) {
/* 5484 */       this.copy.exceptionForDisconnect = mqe;
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
/*      */   synchronized void honourRRS() throws MQException {
/* 5500 */     if (Trace.isOn) {
/* 5501 */       Trace.entry(this, "com.ibm.mq.MQQueueManager", "honourRRS()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5509 */     this.mqManCon.setNotReusable();
/*      */     
/* 5511 */     this.osession.honourRRS(this.Hconn.getHconn(), this.completionCode, this.reason);
/* 5512 */     if (this.completionCode.x != 0 || this.reason.x != 0) {
/*      */       
/* 5514 */       MQException mqe = new MQException(this.completionCode.x, this.reason.x, this, this.osession.getLastJmqiException());
/*      */       
/* 5516 */       if (Trace.isOn) {
/* 5517 */         Trace.throwing(this, "com.ibm.mq.MQQueueManager", "honourRRS()", mqe);
/*      */       }
/*      */       
/* 5520 */       throw mqe;
/*      */     } 
/* 5522 */     if (Trace.isOn) {
/* 5523 */       Trace.exit(this, "com.ibm.mq.MQQueueManager", "honourRRS()");
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
/*      */   public URL getCCDTURL() {
/* 5539 */     if (Trace.isOn) {
/* 5540 */       Trace.data(this, "com.ibm.mq.MQQueueManager", "getCCDTURL()", "getter", this.cdUrl);
/*      */     }
/*      */     
/* 5543 */     return this.cdUrl;
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
/*      */   public boolean getSupportsQAT2() {
/* 5555 */     if (Trace.isOn) {
/* 5556 */       Trace.data(this, "com.ibm.mq.MQQueueManager", "getSupportsQAT2()", "getter", 
/* 5557 */           Boolean.valueOf(this.supportsQAT2));
/*      */     }
/* 5559 */     return this.supportsQAT2;
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
/*      */   public void setSupportsQAT2(boolean b) {
/* 5573 */     if (Trace.isOn) {
/* 5574 */       Trace.data(this, "com.ibm.mq.MQQueueManager", "setSupportsQAT2(boolean)", "setter", 
/* 5575 */           Boolean.valueOf(b));
/*      */     }
/* 5577 */     this.supportsQAT2 = b;
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
/*      */   public String toString() {
/* 5603 */     Hconn actualHconn = this.Hconn.getHconn();
/* 5604 */     if (actualHconn != null) {
/* 5605 */       JSONObject json = new JSONObject();
/*      */       
/*      */       try {
/* 5608 */         String connectionId = actualHconn.getConnectionIdAsString();
/* 5609 */         if (connectionId != null) {
/* 5610 */           json.put("ConnectionId", connectionId);
/*      */         }
/*      */         
/* 5613 */         json.put("QueueManager", this.originalName);
/* 5614 */         if (this.mqManCon != null) {
/* 5615 */           json.put("ResolvedQueueManager", this.mqManCon.getQmgrName());
/* 5616 */           json.put("ConnectionMode", this.mqManCon.getStringProperty("transport"));
/* 5617 */           String hostName = this.mqManCon.getStringProperty("hostname");
/* 5618 */           if (hostName != null) {
/* 5619 */             json.put("Host", hostName);
/* 5620 */             int port = this.mqManCon.getIntegerProperty("port");
/* 5621 */             json.put("Port", port);
/* 5622 */             String channel = this.mqManCon.getStringProperty("channel");
/* 5623 */             json.put("Channel", channel);
/*      */           } 
/*      */         } 
/* 5626 */         json.put("ObjectId", String.format("%s@%x", new Object[] { getClass().getName(), Integer.valueOf(hashCode()) }));
/* 5627 */         return json.toString();
/*      */       }
/* 5629 */       catch (JSONException|JmqiException jSONException) {}
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 5634 */     return super.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static class ZOSDumpQmgrQueue
/*      */     extends MQQueue
/*      */   {
/*      */     protected static final String ZOS_SERVICE1_DUMP_QUEUE = "SERVICE1.DUMP";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected static final String ZOS_SERVICE2_DUMP_QUEUE = "SERVICE2.DUMP";
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private static final int MQOO_DUMP_QMGR = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private static final int MQOO_DUMP_CHIN = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private static final int MQOO_DUMP_QMGRANDCHIN = 3;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ZOSDumpQmgrQueue(MQQueueManager qMgr, String queueName, int openOptions, String queueManagerName) throws MQException {
/* 5673 */       super(qMgr, queueName, setDumpOptions(queueName), queueManagerName, (String)null, (String)null, 999);
/*      */       
/* 5675 */       if (Trace.isOn) {
/* 5676 */         Trace.entry(this, "com.ibm.mq.ZOSDumpQmgrQueue", "<init>(MQQueueManager,String,int,String)", new Object[] { qMgr, queueName, 
/*      */               
/* 5678 */               Integer.valueOf(openOptions), queueManagerName });
/*      */       }
/*      */       
/* 5681 */       if (Trace.isOn) {
/* 5682 */         Trace.exit(this, "com.ibm.mq.ZOSDumpQmgrQueue", "<init>(MQQueueManager,String,int,String)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     private static int setDumpOptions(String queueName) {
/* 5689 */       int options = 0;
/*      */       
/* 5691 */       if (queueName.equals("SERVICE1.DUMP")) {
/* 5692 */         options = 1;
/*      */       }
/* 5694 */       else if (queueName.equals("SERVICE2.DUMP")) {
/* 5695 */         options = 3;
/*      */       } 
/*      */       
/* 5698 */       return options;
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQQueueManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */