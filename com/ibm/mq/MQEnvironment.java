/*      */ package com.ibm.mq;
/*      */ 
/*      */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*      */ import com.ibm.mq.jmqi.JmqiObject;
/*      */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.io.OutputStream;
/*      */ import java.net.URL;
/*      */ import java.nio.charset.CharacterCodingException;
/*      */ import java.nio.charset.Charset;
/*      */ import java.security.AccessControlException;
/*      */ import java.security.AccessController;
/*      */ import java.security.PrivilegedAction;
/*      */ import java.util.Collection;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Vector;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class MQEnvironment
/*      */   extends JmqiObject
/*      */ {
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQEnvironment.java";
/*      */   public static final String version_notice = "IBM MQ classes for Java v9.3.2.0";
/*      */   
/*      */   static {
/*   64 */     if (Trace.isOn) {
/*   65 */       Trace.data("com.ibm.mq.MQEnvironment", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQEnvironment.java");
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
/*      */   public MQEnvironment() {
/*   77 */     super(MQSESSION.getJmqiEnv());
/*   78 */     if (Trace.isOn) {
/*   79 */       Trace.entry(this, "com.ibm.mq.MQEnvironment", "<init>()");
/*      */     }
/*   81 */     if (Trace.isOn) {
/*   82 */       Trace.exit(this, "com.ibm.mq.MQEnvironment", "<init>()");
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
/*      */   @Deprecated
/*  115 */   public static MQSecurityExit securityExit = null;
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*  120 */   public static String securityExitUserData = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  135 */   public static Object channelSecurityExit = null;
/*      */   
/*  137 */   public static String channelSecurityExitUserData = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*  154 */   public static MQSendExit sendExit = null;
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*  159 */   public static String sendExitUserData = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  175 */   public static Object channelSendExit = null;
/*      */   
/*  177 */   public static String channelSendExitUserData = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*  194 */   public static MQReceiveExit receiveExit = null;
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*  199 */   public static String receiveExitUserData = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  215 */   public static Object channelReceiveExit = null;
/*      */   
/*  217 */   public static String channelReceiveExitUserData = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  226 */   public static String exitClasspath = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  235 */   public static String hostname = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  244 */   public static int port = 1414;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  251 */   public static String channel = "";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  272 */   public static String userID = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  294 */   public static String password = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  301 */   public static URL ccdtUrlProperty = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  334 */   public static int CCSID = 819;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  345 */   public static Collection hdrCompList = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  358 */   public static Collection msgCompList = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  369 */   public static String sslCipherSuite = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  379 */   public static String sslPeerName = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  390 */   public static Collection sslCertStores = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  413 */   public static Object sslSocketFactory = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  420 */   public static int sslResetCount = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean sslFipsRequired = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  454 */   public static String localAddressSetting = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  461 */   public static byte[] connTag = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  478 */   public static int connOptions = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  484 */   public static int sharingConversations = 10;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  564 */   public static Hashtable properties = new MQEnvironmentPropertiesHashtable();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  569 */   static Vector<MQPoolToken> poolTokenSet = new Vector<>();
/*      */ 
/*      */   
/*  572 */   static Vector<MQPoolServices> poolServices = new Vector<>();
/*      */ 
/*      */   
/*  575 */   static MQConnectionManager defaultMQCxManager = new MQSimpleConnectionManager();
/*      */ 
/*      */   
/*  578 */   static MQConnectionManager defaultCxManager = null;
/*      */ 
/*      */ 
/*      */   
/*      */   static boolean xaClientEnabled = true;
/*      */ 
/*      */   
/*  585 */   static int xaLicenseMsg = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static boolean forceAllowClient = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static boolean runningInWebSphere = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static class PrivilegedActionImpl
/*      */     implements PrivilegedAction<String>
/*      */   {
/*      */     private PrivilegedActionImpl() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String run() {
/*      */       try {
/*  613 */         return System.getProperty("com.ibm.mq.localAddress");
/*      */       }
/*  615 */       catch (AccessControlException ace) {
/*  616 */         return "";
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   static {
/*  622 */     if (Trace.isOn) {
/*  623 */       Trace.entry("com.ibm.mq.MQEnvironment", "static()");
/*      */     }
/*      */ 
/*      */     
/*  627 */     String fwprop = AccessController.<String>doPrivileged(new PrivilegedActionImpl());
/*      */ 
/*      */     
/*  630 */     if (fwprop != null && !fwprop.trim().equals(""))
/*      */     {
/*      */ 
/*      */       
/*  634 */       localAddressSetting = fwprop;
/*      */     }
/*      */ 
/*      */     
/*  638 */     if (Trace.isOn) {
/*  639 */       Trace.exit("com.ibm.mq.MQEnvironment", "static()");
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
/*      */   public static final String getVersionNotice() {
/*  653 */     if (Trace.isOn) {
/*  654 */       Trace.data("com.ibm.mq.MQEnvironment", "getVersionNotice()", "getter", "IBM MQ classes for Java v9.3.2.0");
/*      */     }
/*  656 */     return "IBM MQ classes for Java v9.3.2.0";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void enableTracing(int level) {
/*  666 */     if (Trace.isOn)
/*  667 */       Trace.entry("com.ibm.mq.MQEnvironment", "enableTracing(int)", new Object[] {
/*  668 */             Integer.valueOf(level)
/*      */           }); 
/*  670 */     Trace.setOn(true);
/*      */     
/*  672 */     if (Trace.isOn) {
/*  673 */       Trace.exit("com.ibm.mq.MQEnvironment", "enableTracing(int)");
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
/*      */   public static void enableTracing(int level, OutputStream stream) {
/*  685 */     if (Trace.isOn)
/*  686 */       Trace.entry("com.ibm.mq.MQEnvironment", "enableTracing(int,OutputStream)", new Object[] {
/*  687 */             Integer.valueOf(level), stream
/*      */           }); 
/*  689 */     Trace.setOn(true);
/*      */     
/*  691 */     if (Trace.isOn) {
/*  692 */       Trace.exit("com.ibm.mq.MQEnvironment", "enableTracing(int,OutputStream)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void disableTracing() {
/*  701 */     if (Trace.isOn) {
/*  702 */       Trace.entry("com.ibm.mq.MQEnvironment", "disableTracing()");
/*      */     }
/*      */     
/*  705 */     Trace.setOn(false);
/*      */     
/*  707 */     if (Trace.isOn) {
/*  708 */       Trace.exit("com.ibm.mq.MQEnvironment", "disableTracing()");
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
/*      */   public static void setDefaultConnectionManager(MQConnectionManager mqCxMan) {
/*  725 */     if (Trace.isOn) {
/*  726 */       Trace.data("com.ibm.mq.MQEnvironment", "setDefaultConnectionManager(MQConnectionManager)", "setter", mqCxMan);
/*      */     }
/*      */ 
/*      */     
/*  730 */     if (mqCxMan == null) {
/*      */       
/*  732 */       NullPointerException traceRet1 = new NullPointerException(MQException.getNLSMsg("MQNULLPOINTER", "MQConnectionManager"));
/*  733 */       if (Trace.isOn) {
/*  734 */         Trace.throwing("com.ibm.mq.MQEnvironment", "setDefaultConnectionManager(MQConnectionManager)", traceRet1);
/*      */       }
/*      */       
/*  737 */       throw traceRet1;
/*      */     } 
/*  739 */     synchronized (poolTokenSet) {
/*  740 */       defaultMQCxManager = mqCxMan;
/*  741 */       defaultCxManager = null;
/*  742 */       poolTokenSet.removeAllElements();
/*      */     } 
/*      */ 
/*      */     
/*  746 */     Vector v = (Vector)poolServices.clone();
/*  747 */     Enumeration<MQPoolServices> e = v.elements();
/*  748 */     while (e.hasMoreElements()) {
/*  749 */       MQPoolServices ps = e.nextElement();
/*  750 */       ps.fireDefaultConnectionManagerChanged();
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
/*      */   public static MQConnectionManager getDefaultConnectionManager() {
/*  768 */     synchronized (poolTokenSet) {
/*  769 */       if (Trace.isOn) {
/*  770 */         Trace.data("com.ibm.mq.MQEnvironment", "getDefaultConnectionManager()", "getter", defaultCxManager);
/*      */       }
/*      */       
/*  773 */       return defaultCxManager;
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
/*      */   public static void addConnectionPoolToken(MQPoolToken token) {
/*  788 */     if (Trace.isOn) {
/*  789 */       Trace.entry("com.ibm.mq.MQEnvironment", "addConnectionPoolToken(MQPoolToken)", new Object[] { token });
/*      */     }
/*      */ 
/*      */     
/*  793 */     if (token == null) {
/*      */       
/*  795 */       NullPointerException traceRet1 = new NullPointerException(MQException.getNLSMsg("MQNULLPOINTER", "MQPoolToken"));
/*  796 */       if (Trace.isOn) {
/*  797 */         Trace.throwing("com.ibm.mq.MQEnvironment", "addConnectionPoolToken(MQPoolToken)", traceRet1);
/*      */       }
/*      */       
/*  800 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/*  804 */     poolTokenSet.addElement(token);
/*      */ 
/*      */ 
/*      */     
/*  808 */     Vector<MQPoolServices> v = (Vector<MQPoolServices>)poolServices.clone();
/*  809 */     for (MQPoolServices ps : v) {
/*  810 */       ps.fireTokenAdded(token);
/*      */     }
/*      */     
/*  813 */     if (Trace.isOn) {
/*  814 */       Trace.exit("com.ibm.mq.MQEnvironment", "addConnectionPoolToken(MQPoolToken)");
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
/*      */   public static MQPoolToken addConnectionPoolToken() {
/*  828 */     if (Trace.isOn) {
/*  829 */       Trace.entry("com.ibm.mq.MQEnvironment", "addConnectionPoolToken()");
/*      */     }
/*      */ 
/*      */     
/*  833 */     MQPoolToken token = new MQPoolToken();
/*  834 */     poolTokenSet.addElement(token);
/*      */ 
/*      */ 
/*      */     
/*  838 */     Vector<MQPoolServices> v = (Vector<MQPoolServices>)poolServices.clone();
/*  839 */     for (MQPoolServices ps : v) {
/*  840 */       ps.fireTokenAdded(token);
/*      */     }
/*      */     
/*  843 */     if (Trace.isOn) {
/*  844 */       Trace.exit("com.ibm.mq.MQEnvironment", "addConnectionPoolToken()", token);
/*      */     }
/*  846 */     return token;
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
/*      */   public static void removeConnectionPoolToken(MQPoolToken token) {
/*  860 */     if (Trace.isOn) {
/*  861 */       Trace.entry("com.ibm.mq.MQEnvironment", "removeConnectionPoolToken(MQPoolToken)", new Object[] { token });
/*      */     }
/*      */ 
/*      */     
/*  865 */     if (token == null) {
/*      */ 
/*      */       
/*  868 */       NullPointerException traceRet1 = new NullPointerException(MQException.getNLSMsg("MQNULLPOINTER", "MQPoolToken"));
/*  869 */       if (Trace.isOn) {
/*  870 */         Trace.throwing("com.ibm.mq.MQEnvironment", "removeConnectionPoolToken(MQPoolToken)", traceRet1);
/*      */       }
/*      */       
/*  873 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  879 */     boolean fire = false;
/*  880 */     synchronized (poolTokenSet) {
/*  881 */       int oldSize = poolTokenSet.size();
/*  882 */       poolTokenSet.removeElement(token);
/*  883 */       if (poolTokenSet.size() < oldSize) {
/*  884 */         fire = true;
/*      */       }
/*      */     } 
/*      */     
/*  888 */     if (fire) {
/*      */       
/*  890 */       Vector v = (Vector)poolServices.clone();
/*  891 */       Enumeration<MQPoolServices> e = v.elements();
/*  892 */       while (e.hasMoreElements()) {
/*  893 */         MQPoolServices ps = e.nextElement();
/*  894 */         ps.fireTokenRemoved(token);
/*      */       } 
/*      */     } 
/*      */     
/*  898 */     if (Trace.isOn) {
/*  899 */       Trace.exit("com.ibm.mq.MQEnvironment", "removeConnectionPoolToken(MQPoolToken)");
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
/*      */   public static Thread createThread(Runnable r, boolean daemon) throws SecurityException {
/*  913 */     if (Trace.isOn) {
/*  914 */       Trace.entry("com.ibm.mq.MQEnvironment", "createThread(Runnable,boolean)", new Object[] { r, 
/*  915 */             Boolean.valueOf(daemon) });
/*      */     }
/*  917 */     Thread traceRet1 = createThread(r, null, daemon);
/*  918 */     if (Trace.isOn) {
/*  919 */       Trace.exit("com.ibm.mq.MQEnvironment", "createThread(Runnable,boolean)", traceRet1);
/*      */     }
/*  921 */     return traceRet1;
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
/*      */   public static Thread createThread(final Runnable r, final String name, final boolean daemon) throws SecurityException {
/*  941 */     Thread t = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  947 */       t = AccessController.<Thread>doPrivileged(new PrivilegedAction<Thread>()
/*      */           {
/*      */             
/*      */             public Thread run()
/*      */             {
/*  952 */               Thread t2 = new Thread(r, name);
/*  953 */               t2.setDaemon(daemon);
/*  954 */               return t2;
/*      */             }
/*      */           });
/*  957 */       return t;
/*      */     }
/*  959 */     catch (SecurityException e) {
/*  960 */       throw e;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean forceAllowClientConnection() {
/*  969 */     if (Trace.isOn) {
/*  970 */       Trace.entry("com.ibm.mq.MQEnvironment", "forceAllowClientConnection()");
/*      */     }
/*  972 */     if (Trace.isOn) {
/*  973 */       Trace.exit("com.ibm.mq.MQEnvironment", "forceAllowClientConnection()", 
/*  974 */           Boolean.valueOf(forceAllowClient));
/*      */     }
/*  976 */     return forceAllowClient;
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
/*      */   @Deprecated
/*      */   public static MQQueueManager getQueueManagerReference(int scope) {
/* 1008 */     if (Trace.isOn) {
/* 1009 */       Trace.entry("com.ibm.mq.MQEnvironment", "getQueueManagerReference(int)", new Object[] {
/* 1010 */             Integer.valueOf(scope)
/*      */           });
/*      */     }
/* 1013 */     MQQueueManager traceRet1 = getQueueManagerReference(scope, null);
/* 1014 */     if (Trace.isOn) {
/* 1015 */       Trace.exit("com.ibm.mq.MQEnvironment", "getQueueManagerReference(int)", traceRet1);
/*      */     }
/* 1017 */     return traceRet1;
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
/*      */   @Deprecated
/*      */   public static MQQueueManager getQueueManagerReference(int scope, Object context) {
/* 1058 */     if (Trace.isOn) {
/* 1059 */       Trace.entry("com.ibm.mq.MQEnvironment", "getQueueManagerReference(int,Object)", new Object[] {
/* 1060 */             Integer.valueOf(scope), context
/*      */           });
/*      */     }
/*      */     
/* 1064 */     MQQueueManager mgr = null;
/* 1065 */     Hashtable<String, Object> h = new Hashtable<>();
/*      */ 
/*      */     
/*      */     try {
/* 1069 */       if (scope == 8 || scope == 32 || scope == 0) {
/*      */ 
/*      */         
/* 1072 */         h.put("QMgr_Association", Integer.valueOf(scope ^ 0xFFFFFFFF));
/*      */       } else {
/*      */         
/* 1075 */         if (Trace.isOn) {
/* 1076 */           Trace.exit("com.ibm.mq.MQEnvironment", "getQueueManagerReference(int,Object)", null, 1);
/*      */         }
/*      */         
/* 1079 */         return null;
/*      */       } 
/* 1081 */       if (context == null) {
/*      */         
/* 1083 */         mgr = new MQQueueManager(null, h);
/* 1084 */       } else if (context instanceof String) {
/*      */ 
/*      */         
/* 1087 */         mgr = new MQQueueManager((String)context, h);
/*      */       }
/*      */     
/* 1090 */     } catch (MQException me) {
/* 1091 */       if (Trace.isOn) {
/* 1092 */         Trace.catchBlock("com.ibm.mq.MQEnvironment", "getQueueManagerReference(int,Object)", me);
/*      */       }
/*      */     } 
/*      */     
/* 1096 */     if (Trace.isOn) {
/* 1097 */       Trace.exit("com.ibm.mq.MQEnvironment", "getQueueManagerReference(int,Object)", mgr, 2);
/*      */     }
/* 1099 */     return mgr;
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
/*      */   protected static final String stringFromBytes(byte[] bData) {
/*      */     String retVal;
/* 1116 */     if (Trace.isOn) {
/* 1117 */       Trace.entry("com.ibm.mq.MQEnvironment", "stringFromBytes(byte [ ])", new Object[] { bData });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1127 */       JmqiEnvironment env = MQSESSION.getJmqiEnv();
/* 1128 */       JmqiCodepage cp = env.getNativeCharSet();
/* 1129 */       retVal = cp.bytesToString(bData);
/*      */     }
/* 1131 */     catch (CharacterCodingException ex) {
/* 1132 */       if (Trace.isOn) {
/* 1133 */         Trace.catchBlock("com.ibm.mq.MQEnvironment", "stringFromBytes(byte [ ])", ex);
/*      */       }
/*      */       
/* 1136 */       retVal = new String(bData, Charset.defaultCharset());
/*      */     } 
/*      */     
/* 1139 */     if (Trace.isOn) {
/* 1140 */       Trace.exit("com.ibm.mq.MQEnvironment", "stringFromBytes(byte [ ])", retVal);
/*      */     }
/* 1142 */     return retVal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final byte[] bytesFromString(String aValue) {
/* 1152 */     if (Trace.isOn) {
/* 1153 */       Trace.entry("com.ibm.mq.MQEnvironment", "bytesFromString(String)", new Object[] { aValue });
/*      */     }
/* 1155 */     byte[] retVal = null;
/*      */     try {
/* 1157 */       JmqiEnvironment env = MQSESSION.getJmqiEnv();
/* 1158 */       JmqiCodepage cp = env.getNativeCharSet();
/* 1159 */       retVal = cp.stringToBytes(aValue);
/*      */     }
/* 1161 */     catch (CharacterCodingException ex) {
/* 1162 */       if (Trace.isOn) {
/* 1163 */         Trace.catchBlock("com.ibm.mq.MQEnvironment", "bytesFromString(String)", ex);
/*      */       }
/*      */       
/* 1166 */       retVal = aValue.getBytes(Charset.defaultCharset());
/*      */     } 
/*      */     
/* 1169 */     if (Trace.isOn) {
/* 1170 */       Trace.exit("com.ibm.mq.MQEnvironment", "bytesFromString(String)", retVal);
/*      */     }
/* 1172 */     return retVal;
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
/*      */   static void registerPoolServices(MQPoolServices ps) {
/* 1184 */     if (Trace.isOn) {
/* 1185 */       Trace.entry("com.ibm.mq.MQEnvironment", "registerPoolServices(MQPoolServices)", new Object[] { ps });
/*      */     }
/*      */ 
/*      */     
/* 1189 */     poolServices.addElement(ps);
/* 1190 */     if (Trace.isOn) {
/* 1191 */       Trace.exit("com.ibm.mq.MQEnvironment", "registerPoolServices(MQPoolServices)");
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
/*      */   static void deregisterPoolServices(MQPoolServices ps) {
/* 1206 */     if (Trace.isOn) {
/* 1207 */       Trace.entry("com.ibm.mq.MQEnvironment", "deregisterPoolServices(MQPoolServices)", new Object[] { ps });
/*      */     }
/*      */ 
/*      */     
/* 1211 */     poolServices.removeElement(ps);
/* 1212 */     if (Trace.isOn) {
/* 1213 */       Trace.exit("com.ibm.mq.MQEnvironment", "deregisterPoolServices(MQPoolServices)");
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
/*      */   static Object getDefaultProperty(Object key) {
/* 1245 */     if (Trace.isOn) {
/* 1246 */       Trace.entry("com.ibm.mq.MQEnvironment", "getDefaultProperty(Object)", new Object[] { key });
/*      */     }
/*      */     
/* 1249 */     Object property = properties.get(key);
/*      */     
/* 1251 */     if (property == null) {
/*      */ 
/*      */       
/* 1254 */       if (key.equals("CCDT URL")) {
/* 1255 */         property = ccdtUrlProperty;
/*      */       }
/*      */ 
/*      */       
/* 1259 */       if (key.equals("CCSID")) {
/* 1260 */         property = Integer.valueOf(CCSID);
/*      */       }
/*      */ 
/*      */       
/* 1264 */       if (key.equals("channel")) {
/* 1265 */         property = channel;
/*      */       }
/*      */ 
/*      */       
/* 1269 */       if (key.equals("connectOptions")) {
/* 1270 */         property = Integer.valueOf(connOptions);
/*      */       }
/*      */ 
/*      */       
/* 1274 */       if (key.equals("hostname")) {
/* 1275 */         property = hostname;
/*      */       }
/*      */ 
/*      */       
/* 1279 */       if (key.equals("password")) {
/* 1280 */         property = password;
/*      */       }
/*      */ 
/*      */       
/* 1284 */       if (key.equals("port")) {
/* 1285 */         property = Integer.valueOf(port);
/*      */       }
/*      */ 
/*      */       
/* 1289 */       if (key.equals("receiveExit")) {
/* 1290 */         property = receiveExit;
/*      */       }
/*      */ 
/*      */       
/* 1294 */       if (key.equals("channelReceiveExit")) {
/* 1295 */         property = channelReceiveExit;
/*      */       }
/*      */ 
/*      */       
/* 1299 */       if (key.equals("securityExit")) {
/* 1300 */         property = securityExit;
/*      */       }
/*      */ 
/*      */       
/* 1304 */       if (key.equals("channelSecurityExit")) {
/* 1305 */         property = channelSecurityExit;
/*      */       }
/*      */ 
/*      */       
/* 1309 */       if (key.equals("sendExit")) {
/* 1310 */         property = sendExit;
/*      */       }
/*      */ 
/*      */       
/* 1314 */       if (key.equals("channelSendExit")) {
/* 1315 */         property = channelSendExit;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1320 */       if (key.equals("receiveExitUserData")) {
/* 1321 */         property = receiveExitUserData;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1326 */       if (key.equals("channelReceiveExitUserData")) {
/* 1327 */         property = channelReceiveExitUserData;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1332 */       if (key.equals("securityExitUserData")) {
/* 1333 */         property = securityExitUserData;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1338 */       if (key.equals("channelSecurityExitUserData")) {
/* 1339 */         property = channelSecurityExitUserData;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1344 */       if (key.equals("securityExitUserData")) {
/* 1345 */         property = securityExitUserData;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1350 */       if (key.equals("sendExitUserData")) {
/* 1351 */         property = sendExitUserData;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1356 */       if (key.equals("channelSendExitUserData")) {
/* 1357 */         property = channelSendExitUserData;
/*      */       }
/*      */ 
/*      */       
/* 1361 */       if (key.equals("exitClasspath")) {
/* 1362 */         property = exitClasspath;
/*      */       }
/*      */ 
/*      */       
/* 1366 */       if (key.equals("transport")) {
/* 1367 */         property = "MQSeries";
/*      */       }
/*      */ 
/*      */       
/* 1371 */       if (key.equals("userID")) {
/* 1372 */         property = userID;
/*      */       }
/*      */       
/* 1375 */       if (key.equals("SSL Cipher Suite")) {
/* 1376 */         property = sslCipherSuite;
/*      */       }
/*      */       
/* 1379 */       if (key.equals("SSL Peer Name")) {
/* 1380 */         property = sslPeerName;
/*      */       }
/*      */       
/* 1383 */       if (key.equals("SSL CertStores")) {
/* 1384 */         property = sslCertStores;
/*      */       }
/*      */       
/* 1387 */       if (key.equals("SSL Socket Factory")) {
/* 1388 */         property = sslSocketFactory;
/*      */       }
/*      */       
/* 1391 */       if (key.equals("Local Address Property")) {
/* 1392 */         property = localAddressSetting;
/*      */       }
/*      */       
/* 1395 */       if (key.equals("Header Compression Property")) {
/* 1396 */         property = hdrCompList;
/*      */       }
/* 1398 */       if (key.equals("Message Compression Property")) {
/* 1399 */         property = msgCompList;
/*      */       }
/* 1401 */       if (key.equals("ConnTag Property")) {
/* 1402 */         property = connTag;
/*      */       }
/* 1404 */       if (key.equals("KeyResetCount")) {
/* 1405 */         property = Integer.valueOf(sslResetCount);
/*      */       }
/* 1407 */       if (key.equals("SSL Fips Required")) {
/* 1408 */         property = Boolean.valueOf(sslFipsRequired);
/*      */       }
/* 1410 */       if (key.equals("sharingConversations")) {
/* 1411 */         property = Integer.valueOf(sharingConversations);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1416 */     if (Trace.isOn) {
/* 1417 */       Trace.exit("com.ibm.mq.MQEnvironment", "getDefaultProperty(Object)", property);
/*      */     }
/* 1419 */     return property;
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
/*      */   static Object getObjectProperty(String key, Hashtable properties) {
/* 1436 */     if (Trace.isOn) {
/* 1437 */       Trace.entry("com.ibm.mq.MQEnvironment", "getObjectProperty(String,Hashtable)", new Object[] { key, 
/* 1438 */             Trace.sanitizeMap(properties, "password", "XXXXXXXXXXXX") });
/*      */     }
/* 1440 */     Object property = null;
/*      */ 
/*      */     
/* 1443 */     if (properties != null) {
/* 1444 */       property = properties.get(key);
/*      */     }
/*      */ 
/*      */     
/* 1448 */     if (property == null) {
/* 1449 */       property = getDefaultProperty(key);
/*      */     }
/*      */     
/* 1452 */     Object propertyToTrace = key.equals("password") ? "XXXXXXXXXXXX" : property;
/* 1453 */     if (Trace.isOn) {
/* 1454 */       Trace.exit("com.ibm.mq.MQEnvironment", "getObjectProperty(String,Hashtable)", propertyToTrace);
/*      */     }
/* 1456 */     return property;
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
/*      */   static String getStringProperty(String key, Hashtable properties) {
/* 1474 */     if (Trace.isOn) {
/* 1475 */       Trace.entry("com.ibm.mq.MQEnvironment", "getStringProperty(String,Hashtable)", new Object[] { key, 
/* 1476 */             Trace.sanitizeMap(properties, "password", "XXXXXXXXXXXX") });
/*      */     }
/* 1478 */     Object object = getObjectProperty(key, properties);
/*      */     
/* 1480 */     if (object instanceof String) {
/* 1481 */       String property = (String)object;
/* 1482 */       if (Trace.isOn) {
/* 1483 */         Trace.exit("com.ibm.mq.MQEnvironment", "getStringProperty(String,Hashtable)", 
/* 1484 */             key.equals("password") ? "XXXXXXXXXXXX" : property, 1);
/*      */       }
/* 1486 */       return property;
/*      */     } 
/*      */     
/* 1489 */     if (Trace.isOn) {
/* 1490 */       Trace.exit("com.ibm.mq.MQEnvironment", "getStringProperty(String,Hashtable)", null, 2);
/*      */     }
/*      */     
/* 1493 */     return null;
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
/*      */   static int getIntegerProperty(String key, Hashtable properties) {
/* 1510 */     if (Trace.isOn) {
/* 1511 */       Trace.entry("com.ibm.mq.MQEnvironment", "getIntegerProperty(String,Hashtable)", new Object[] { key, 
/* 1512 */             Trace.sanitizeMap(properties, "password", "XXXXXXXXXXXX") });
/*      */     }
/* 1514 */     Object object = getObjectProperty(key, properties);
/*      */     
/* 1516 */     if (object instanceof Integer) {
/* 1517 */       int traceRet1 = ((Integer)object).intValue();
/* 1518 */       if (Trace.isOn) {
/* 1519 */         Trace.exit("com.ibm.mq.MQEnvironment", "getIntegerProperty(String,Hashtable)", 
/* 1520 */             Integer.valueOf(traceRet1), 1);
/*      */       }
/* 1522 */       return traceRet1;
/*      */     } 
/*      */     
/* 1525 */     if (Trace.isOn) {
/* 1526 */       Trace.exit("com.ibm.mq.MQEnvironment", "getIntegerProperty(String,Hashtable)", 
/* 1527 */           Integer.valueOf(0), 2);
/*      */     }
/* 1529 */     return 0;
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
/*      */   public static void traceSystemProperties() {
/* 1541 */     if (Trace.isOn) {
/* 1542 */       Trace.entry("com.ibm.mq.MQEnvironment", "traceSystemProperties()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1547 */     if (Trace.isOn)
/* 1548 */       Trace.exit("com.ibm.mq.MQEnvironment", "traceSystemProperties()"); 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQEnvironment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */