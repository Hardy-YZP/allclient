/*      */ package com.ibm.mq.jmqi.system;
/*      */ 
/*      */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*      */ import com.ibm.mq.jmqi.JmqiObject;
/*      */ import com.ibm.mq.jmqi.JmqiReconnectionListener;
/*      */ import com.ibm.mq.jmqi.RebalancingListener;
/*      */ import com.ibm.mq.jmqi.StringJoiner;
/*      */ import com.ibm.mq.jmqi.internal.JmqiTools;
/*      */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.net.URL;
/*      */ import java.util.Collection;
/*      */ import javax.net.ssl.SSLSocketFactory;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class JmqiConnectOptions
/*      */   extends JmqiObject
/*      */ {
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/JmqiConnectOptions.java";
/*      */   public static final int TRUST_SOCKET_FACTORY = 16;
/*      */   public static final int OVERRIDE_CCDT_SHARECNV = 64;
/*      */   public static final int USER_AUTHENTICATION_MQCSP = 128;
/*      */   public static final int V6_LEG = 256;
/*   68 */   private static final String userDataFormat = String.format("%%-%ds", new Object[] { Integer.valueOf(32) });
/*      */ 
/*      */   
/*      */   private static ChannelSharingMode channelSharingMode;
/*      */   
/*   73 */   private static String channelSharingProperty = "com.ibm.mq.jms.channel.sharing";
/*      */   private int flags;
/*      */   
/*      */   public enum ChannelSharingMode {
/*   77 */     CONNECTION,
/*   78 */     GLOBAL;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static {
/*   85 */     if (Trace.isOn) {
/*   86 */       Trace.entry("com.ibm.mq.jmqi.system.JmqiConnectOptions", "static()");
/*      */     }
/*      */ 
/*      */     
/*   90 */     PropertyStore.register(channelSharingProperty, null);
/*      */     
/*   92 */     String channelSharingPropertyValue = PropertyStore.getStringProperty(channelSharingProperty);
/*   93 */     if (channelSharingPropertyValue != null) {
/*   94 */       switch (channelSharingPropertyValue.toUpperCase()) {
/*      */         case "CONNECTION":
/*   96 */           channelSharingMode = ChannelSharingMode.CONNECTION;
/*      */           break;
/*      */         default:
/*   99 */           channelSharingMode = ChannelSharingMode.GLOBAL;
/*      */           break;
/*      */       } 
/*      */     }
/*  103 */     if (Trace.isOn) {
/*  104 */       Trace.data("com.ibm.mq.jmqi.system.JmqiConnectOptions", "static()", "channelSharingMode : ", channelSharingMode);
/*      */     }
/*      */     
/*  107 */     if (Trace.isOn) {
/*  108 */       Trace.exit("com.ibm.mq.jmqi.system.JmqiConnectOptions", "static()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  115 */   private URL ccdtUrl = null;
/*  116 */   private SSLSocketFactory sslSocketFactory = null;
/*  117 */   private Collection<?> crlCertStores = null;
/*  118 */   private int queueManagerCCSID = -1;
/*  119 */   private String userIdentifier = null;
/*  120 */   private String password = null;
/*  121 */   private int cmdLevel = -1;
/*  122 */   private int platform = -1;
/*  123 */   private int sharingConversations = 0;
/*  124 */   private int fapLevel = 0;
/*      */ 
/*      */   
/*  127 */   private int reconnectionTimeout = 1800;
/*      */   
/*  129 */   private byte[] reconnectionId = null;
/*      */   
/*  131 */   private String reconnectionQmId = null;
/*      */   
/*  133 */   private String reconnectionQsgName = null;
/*      */   
/*  135 */   private JmqiReconnectionListener reconnectionListener = null;
/*      */   
/*  137 */   private RebalancingListener rebalancingListener = null;
/*      */   
/*  139 */   private int rebalancingApplicationType = 0;
/*      */   
/*  141 */   private JmqiOptionAdapter wasLocalUOWIDAdapter = null;
/*      */   private boolean wasReconnectQmgr = false;
/*      */   private String applicationName;
/*      */   private boolean webAdminConnection = false;
/*      */   private boolean isJms = false;
/*  146 */   private ChannelSharingMode jmsChannelSharingMode = ChannelSharingMode.GLOBAL;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  152 */   private Object securityExit = null;
/*      */   
/*  154 */   private String securityExitUserData = null;
/*      */ 
/*      */   
/*  157 */   private Object sendExits = null;
/*      */   
/*  159 */   private String sendExitsUserData = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  165 */   private Object receiveExits = null;
/*      */   
/*  167 */   private String receiveExitsUserData = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  172 */   private String exitClassPath = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getFlags() {
/*  178 */     if (Trace.isOn) {
/*  179 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "getFlags()", "getter", 
/*  180 */           Integer.valueOf(this.flags));
/*      */     }
/*  182 */     return this.flags;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFlags(int flags) {
/*  189 */     if (Trace.isOn) {
/*  190 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "setFlags(int)", "setter", 
/*  191 */           Integer.valueOf(flags));
/*      */     }
/*  193 */     this.flags = flags;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFlag(int flag) {
/*  200 */     if (Trace.isOn) {
/*  201 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "setFlag(int)", "setter", 
/*  202 */           Integer.valueOf(flag));
/*      */     }
/*  204 */     this.flags |= flag;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unsetFlag(int flag) {
/*  211 */     if (Trace.isOn) {
/*  212 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "unsetFlag(int)", "setter", 
/*  213 */           Integer.valueOf(flag));
/*      */     }
/*  215 */     this.flags &= flag ^ 0xFFFFFFFF;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSet(int flag) {
/*  223 */     boolean result = ((this.flags & flag) != 0);
/*  224 */     if (Trace.isOn) {
/*  225 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "isSet(int)", "getter", "Flag: " + flag + (result ? "[ON]" : "[OFF]"));
/*      */     }
/*      */     
/*  228 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCmdLevel() {
/*  235 */     if (Trace.isOn) {
/*  236 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "getCmdLevel()", "getter", 
/*  237 */           Integer.valueOf(this.cmdLevel));
/*      */     }
/*  239 */     return this.cmdLevel;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCmdLevel(int cmdLevel) {
/*  247 */     if (Trace.isOn) {
/*  248 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "setCmdLevel(int)", "setter", 
/*  249 */           Integer.valueOf(cmdLevel));
/*      */     }
/*  251 */     this.cmdLevel = cmdLevel;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPlatform() {
/*  259 */     if (Trace.isOn) {
/*  260 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "getPlatform()", "getter", 
/*  261 */           Integer.valueOf(this.platform));
/*      */     }
/*  263 */     return this.platform;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPlatform(int platform) {
/*  271 */     if (Trace.isOn) {
/*  272 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "setPlatform(int)", "setter", 
/*  273 */           Integer.valueOf(platform));
/*      */     }
/*  275 */     this.platform = platform;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected JmqiConnectOptions(JmqiEnvironment env) {
/*  285 */     super(env);
/*  286 */     if (Trace.isOn) {
/*  287 */       Trace.entry(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "<init>(JmqiEnvironment)", new Object[] { env });
/*      */     }
/*      */     
/*  290 */     if (Trace.isOn) {
/*  291 */       Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "<init>(JmqiEnvironment)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public URL getCcdtUrl() {
/*  301 */     if (Trace.isOn) {
/*  302 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "getCcdtUrl()", "getter", this.ccdtUrl);
/*      */     }
/*      */     
/*  305 */     return this.ccdtUrl;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCcdtUrl(URL url) {
/*  313 */     if (Trace.isOn) {
/*  314 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "setCcdtUrl(URL)", "setter", url);
/*      */     }
/*      */     
/*  317 */     this.ccdtUrl = url;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SSLSocketFactory getSslSocketFactory() {
/*  325 */     if (Trace.isOn) {
/*  326 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "getSslSocketFactory()", "getter", this.sslSocketFactory);
/*      */     }
/*      */     
/*  329 */     return this.sslSocketFactory;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSslSocketFactory(SSLSocketFactory sslSocketFactory) {
/*  337 */     if (Trace.isOn) {
/*  338 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "setSslSocketFactory(SSLSocketFactory)", "setter", sslSocketFactory);
/*      */     }
/*      */     
/*  341 */     this.sslSocketFactory = sslSocketFactory;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<?> getCrlCertStores() {
/*  349 */     if (Trace.isOn) {
/*  350 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "getCrlCertStores()", "getter", this.crlCertStores);
/*      */     }
/*      */     
/*  353 */     return this.crlCertStores;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCrlCertStores(Collection<?> crlCertStores) {
/*  361 */     if (Trace.isOn) {
/*  362 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "setCrlCertStores(Collection<?>)", "setter", crlCertStores);
/*      */     }
/*      */     
/*  365 */     this.crlCertStores = crlCertStores;
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
/*      */   public int getQueueManagerCCSID() {
/*  378 */     if (Trace.isOn) {
/*  379 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "getQueueManagerCCSID()", "getter", 
/*  380 */           Integer.valueOf(this.queueManagerCCSID));
/*      */     }
/*  382 */     return this.queueManagerCCSID;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setQueueManagerCCSID(int ccsid) {
/*  392 */     if (Trace.isOn) {
/*  393 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "setQueueManagerCCSID(int)", "setter", 
/*  394 */           Integer.valueOf(ccsid));
/*      */     }
/*  396 */     this.queueManagerCCSID = ccsid;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getPassword() {
/*  407 */     if (Trace.isOn) {
/*  408 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "getPassword()", "getter", (this.password == null) ? null : "********");
/*      */     }
/*      */     
/*  411 */     return this.password;
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
/*      */   public void setPassword(String password) {
/*  423 */     if (Trace.isOn) {
/*  424 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "setPassword(String)", "setter", (password == null) ? null : "********");
/*      */     }
/*      */     
/*  427 */     this.password = password;
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
/*      */   public String getUserIdentifier() {
/*  439 */     if (Trace.isOn) {
/*  440 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "getUserIdentifier()", "getter", this.userIdentifier);
/*      */     }
/*      */     
/*  443 */     return this.userIdentifier;
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
/*      */   public void setUserIdentifier(String userIdentifier) {
/*  455 */     if (Trace.isOn) {
/*  456 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "setUserIdentifier(String)", "setter", userIdentifier);
/*      */     }
/*      */     
/*  459 */     this.userIdentifier = userIdentifier;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getReceiveExits() {
/*  467 */     if (Trace.isOn) {
/*  468 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "getReceiveExits()", "getter", this.receiveExits);
/*      */     }
/*      */     
/*  471 */     return this.receiveExits;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReceiveExits(Object receiveExits) {
/*  481 */     if (Trace.isOn) {
/*  482 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "setReceiveExits(Object)", "setter", receiveExits);
/*      */     }
/*      */     
/*  485 */     this.receiveExits = receiveExits;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getSecurityExit() {
/*  493 */     if (Trace.isOn) {
/*  494 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "getSecurityExit()", "getter", this.securityExit);
/*      */     }
/*      */     
/*  497 */     return this.securityExit;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSecurityExit(Object securityExit) {
/*  507 */     if (Trace.isOn) {
/*  508 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "setSecurityExit(Object)", "setter", securityExit);
/*      */     }
/*      */     
/*  511 */     this.securityExit = securityExit;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getSendExits() {
/*  519 */     if (Trace.isOn) {
/*  520 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "getSendExits()", "getter", this.sendExits);
/*      */     }
/*      */     
/*  523 */     return this.sendExits;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSendExits(Object sendExits) {
/*  533 */     if (Trace.isOn) {
/*  534 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "setSendExits(Object)", "setter", sendExits);
/*      */     }
/*      */     
/*  537 */     this.sendExits = sendExits;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getReceiveExitsUserData() {
/*  545 */     if (Trace.isOn) {
/*  546 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "getReceiveExitsUserData()", "getter", this.receiveExitsUserData);
/*      */     }
/*      */     
/*  549 */     return this.receiveExitsUserData;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReceiveExitsUserData(String receiveExitsUserData) {
/*  556 */     if (Trace.isOn) {
/*  557 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "setReceiveExitsUserData(String)", "setter", receiveExitsUserData);
/*      */     }
/*      */     
/*  560 */     this.receiveExitsUserData = nullToSpace(receiveExitsUserData);
/*      */   }
/*      */   
/*      */   private String nullToSpace(String userData) {
/*  564 */     return String.format(userDataFormat, new Object[] { (userData == null) ? " " : userData });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getSecurityExitUserData() {
/*  571 */     if (Trace.isOn) {
/*  572 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "getSecurityExitUserData()", "getter", this.securityExitUserData);
/*      */     }
/*      */     
/*  575 */     return this.securityExitUserData;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSecurityExitUserData(String securityExitUserData) {
/*  582 */     if (Trace.isOn) {
/*  583 */       Trace.entry(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "setSecurityExitUserData(String)", new Object[] { securityExitUserData });
/*      */     }
/*      */ 
/*      */     
/*  587 */     this.securityExitUserData = nullToSpace(securityExitUserData);
/*      */     
/*  589 */     if (Trace.isOn) {
/*  590 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "setSecurityExitUserData(String)", "Formatted user data: [" + this.securityExitUserData + "]");
/*      */       
/*  592 */       Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "setSecurityExitUserData(String)");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getSendExitsUserData() {
/*  600 */     if (Trace.isOn) {
/*  601 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "getSendExitsUserData()", "getter", this.sendExitsUserData);
/*      */     }
/*      */     
/*  604 */     return this.sendExitsUserData;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSendExitsUserData(String sendExitsUserData) {
/*  611 */     if (Trace.isOn) {
/*  612 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "setSendExitsUserData(String)", "setter", sendExitsUserData);
/*      */     }
/*      */     
/*  615 */     this.sendExitsUserData = nullToSpace(sendExitsUserData);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/*  624 */     StringBuilder buffer = new StringBuilder();
/*  625 */     buffer.append("ApplicationName:").append(this.applicationName);
/*  626 */     buffer.append(" webAdminConnection:").append(this.webAdminConnection);
/*  627 */     buffer.append(" ccdtUrl:").append(this.ccdtUrl);
/*  628 */     buffer.append(" sslSocketFactory:").append(this.sslSocketFactory);
/*  629 */     buffer.append(" queueManagerCCSID:").append(this.queueManagerCCSID);
/*  630 */     buffer.append(" userIdentifier:").append(JmqiTools.safeString(this.userIdentifier));
/*  631 */     buffer.append(" password:").append(JmqiTools.tracePassword(this.password));
/*  632 */     buffer.append(" cmdLevel:").append(this.cmdLevel);
/*  633 */     buffer.append(" flags:").append(String.format("%d(0x%x) - %s", new Object[] { Integer.valueOf(this.flags), Integer.valueOf(this.flags), decodeFlags() }));
/*  634 */     buffer.append(" platform:").append(this.platform);
/*  635 */     buffer.append(" reconnectionListener:").append(this.reconnectionListener);
/*  636 */     buffer.append(" rebalancingListener:").append(this.rebalancingListener);
/*  637 */     buffer.append(" securityExit:").append(this.securityExit);
/*  638 */     buffer.append(" securityExitUserData:").append(JmqiTools.safeString(this.securityExitUserData));
/*  639 */     buffer.append(" sendExits:").append(this.sendExits);
/*  640 */     buffer.append(" sendExitsUserData:").append(JmqiTools.safeString(this.sendExitsUserData));
/*  641 */     buffer.append(" receiveExits:").append(this.receiveExits);
/*  642 */     buffer.append(" receiveExitsUserData:").append(JmqiTools.safeString(this.receiveExitsUserData));
/*  643 */     return buffer.toString();
/*      */   }
/*      */   
/*      */   private String decodeFlags() {
/*  647 */     StringJoiner result = new StringJoiner(",");
/*  648 */     if ((this.flags & 0x10) != 0) {
/*  649 */       result.add("TRUST_SOCKET_FACTORY");
/*      */     }
/*  651 */     if ((this.flags & 0x40) != 0) {
/*  652 */       result.add("OVERRIDE_CCDT_SHARECNV");
/*      */     }
/*  654 */     if ((this.flags & 0x80) != 0) {
/*  655 */       result.add("USER_AUTHENTICATION_MQCSP");
/*      */     }
/*  657 */     if ((this.flags & 0x100) != 0) {
/*  658 */       result.add("V6_LEG");
/*      */     }
/*  660 */     return result.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getSharingConversations() {
/*  667 */     if (Trace.isOn) {
/*  668 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "getSharingConversations()", "getter", 
/*  669 */           Integer.valueOf(this.sharingConversations));
/*      */     }
/*  671 */     return this.sharingConversations;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSharingConversations(int sharingConversations) {
/*  678 */     if (Trace.isOn) {
/*  679 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "setSharingConversations(int)", "setter", 
/*  680 */           Integer.valueOf(sharingConversations));
/*      */     }
/*  682 */     this.sharingConversations = sharingConversations;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getFapLevel() {
/*  689 */     if (Trace.isOn) {
/*  690 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "getFapLevel()", "getter", 
/*  691 */           Integer.valueOf(this.fapLevel));
/*      */     }
/*  693 */     return this.fapLevel;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFapLevel(int fapLevel) {
/*  700 */     if (Trace.isOn) {
/*  701 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "setFapLevel(int)", "setter", 
/*  702 */           Integer.valueOf(fapLevel));
/*      */     }
/*  704 */     this.fapLevel = fapLevel;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setExitClassPath(String exitClassPath) {
/*  711 */     if (Trace.isOn) {
/*  712 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "setExitClassPath(String)", "setter", exitClassPath);
/*      */     }
/*      */     
/*  715 */     this.exitClassPath = exitClassPath;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getExitClassPath() {
/*  722 */     if (Trace.isOn) {
/*  723 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "getExitClassPath()", "getter", this.exitClassPath);
/*      */     }
/*      */     
/*  726 */     return this.exitClassPath;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReconnectionTimeout(int timeout) {
/*  735 */     if (Trace.isOn) {
/*  736 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "setReconnectionTimeout(int)", "setter", 
/*  737 */           Integer.valueOf(timeout));
/*      */     }
/*  739 */     this.reconnectionTimeout = timeout;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getReconnectionTimeout() {
/*  746 */     if (Trace.isOn) {
/*  747 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "getReconnectionTimeout()", "getter", 
/*  748 */           Integer.valueOf(this.reconnectionTimeout));
/*      */     }
/*  750 */     return this.reconnectionTimeout;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReconnectionId(byte[] rcnId) {
/*  759 */     if (Trace.isOn) {
/*  760 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "setReconnectionId(byte [ ])", "setter", rcnId);
/*      */     }
/*      */     
/*  763 */     this.reconnectionId = rcnId;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getReconnectionId() {
/*  770 */     if (Trace.isOn) {
/*  771 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "getReconnectionId()", "getter", this.reconnectionId);
/*      */     }
/*      */     
/*  774 */     return this.reconnectionId;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReconnectionQmId(String rcnQmId) {
/*  783 */     if (Trace.isOn) {
/*  784 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "setReconnectionQmId(String)", "setter", rcnQmId);
/*      */     }
/*      */     
/*  787 */     this.reconnectionQmId = rcnQmId;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getReconnectionQmId() {
/*  794 */     if (Trace.isOn) {
/*  795 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "getReconnectionQmId()", "getter", this.reconnectionQmId);
/*      */     }
/*      */     
/*  798 */     return this.reconnectionQmId;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReconnectionQsgName(String rcnQsgName) {
/*  807 */     if (Trace.isOn) {
/*  808 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "setReconnectionQsgName(String)", "setter", rcnQsgName);
/*      */     }
/*      */     
/*  811 */     this.reconnectionQsgName = rcnQsgName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getReconnectionQsgName() {
/*  818 */     if (Trace.isOn) {
/*  819 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "setReconnectionQsgName()", "getter", this.reconnectionQsgName);
/*      */     }
/*      */     
/*  822 */     return this.reconnectionQsgName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setWASLocalUOWID(JmqiOptionAdapter uowIDAdapter) {
/*  830 */     if (Trace.isOn) {
/*  831 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "setWASLocalUOWID(JmqiOptionAdapter)", "setter", uowIDAdapter);
/*      */     }
/*      */     
/*  834 */     this.wasLocalUOWIDAdapter = uowIDAdapter;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getWASLocalUOWID() {
/*  841 */     if (Trace.isOn) {
/*  842 */       Trace.entry(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "getWASLocalUOWID()");
/*      */     }
/*  844 */     if (this.wasLocalUOWIDAdapter != null) {
/*  845 */       long traceRet1 = this.wasLocalUOWIDAdapter.getLongOption();
/*      */       
/*  847 */       if (Trace.isOn) {
/*  848 */         Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "getWASLocalUOWID()", 
/*  849 */             Long.valueOf(traceRet1), 1);
/*      */       }
/*  851 */       return traceRet1;
/*      */     } 
/*      */     
/*  854 */     long traceRet2 = -1L;
/*  855 */     if (Trace.isOn) {
/*  856 */       Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "getWASLocalUOWID()", 
/*  857 */           Long.valueOf(traceRet2), 2);
/*      */     }
/*  859 */     return traceRet2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setWasReconnectQmgr(boolean wasReconnectQmgr) {
/*  866 */     if (Trace.isOn) {
/*  867 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "setWasReconnectQmgr(boolean)", "setter", 
/*  868 */           Boolean.valueOf(wasReconnectQmgr));
/*      */     }
/*  870 */     this.wasReconnectQmgr = wasReconnectQmgr;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isWasReconnectQmgr() {
/*  877 */     if (Trace.isOn) {
/*  878 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "isWasReconnectQmgr()", "getter", 
/*  879 */           Boolean.valueOf(this.wasReconnectQmgr));
/*      */     }
/*  881 */     return this.wasReconnectQmgr;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setApplicationName(String applicationName) {
/*  889 */     if (Trace.isOn) {
/*  890 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "setApplicationName(String)", "setter", applicationName);
/*      */     }
/*      */     
/*  893 */     this.applicationName = applicationName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getApplicationName() {
/*  900 */     if (Trace.isOn) {
/*  901 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "getApplicationName()", "getter", this.applicationName);
/*      */     }
/*      */     
/*  904 */     return this.applicationName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setWebAdminConnection(boolean webAdminConnection) {
/*  911 */     if (Trace.isOn) {
/*  912 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "setWebAdminConnection(boolean)", "setter", 
/*  913 */           Boolean.valueOf(webAdminConnection));
/*      */     }
/*  915 */     this.webAdminConnection = webAdminConnection;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isWebAdminConnection() {
/*  922 */     if (Trace.isOn) {
/*  923 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "isWebAdminConnection()", "getter", 
/*  924 */           Boolean.valueOf(this.webAdminConnection));
/*      */     }
/*  926 */     return this.webAdminConnection;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JmqiReconnectionListener getReconnectionListener() {
/*  933 */     if (Trace.isOn) {
/*  934 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "getReconnectionListener(JmqiReconnectionListener)", "getter", this.reconnectionListener);
/*      */     }
/*      */     
/*  937 */     return this.reconnectionListener;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReconnectionListener(JmqiReconnectionListener reconnectionListener) {
/*  944 */     if (Trace.isOn) {
/*  945 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "setReconnectionListener()", "setter", reconnectionListener);
/*      */     }
/*      */     
/*  948 */     this.reconnectionListener = reconnectionListener;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RebalancingListener getRebalancingListener() {
/*  955 */     if (Trace.isOn) {
/*  956 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "getRebalancingListener()", "getter", this.rebalancingListener);
/*      */     }
/*      */     
/*  959 */     return this.rebalancingListener;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRebalancingListener(RebalancingListener rebalancingListener) {
/*  966 */     if (Trace.isOn) {
/*  967 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "setRebalancingListener(RebalancingListener)", "setter", rebalancingListener);
/*      */     }
/*      */     
/*  970 */     this.rebalancingListener = rebalancingListener;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isJms() {
/*  977 */     if (Trace.isOn) {
/*  978 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "isJms()", "getter", 
/*  979 */           Boolean.valueOf(this.isJms));
/*      */     }
/*  981 */     return this.isJms;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setJms(boolean isJms) {
/*  988 */     if (Trace.isOn) {
/*  989 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "isJms(boolean)", "getter", 
/*  990 */           Boolean.valueOf(isJms));
/*      */     }
/*  992 */     this.isJms = isJms;
/*  993 */     setJmsChannelSharingMode(channelSharingMode);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getRebalancingApplicationType() {
/* 1000 */     if (Trace.isOn) {
/* 1001 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "getRebalancingApplicationType()", "getter", 
/* 1002 */           Integer.valueOf(this.rebalancingApplicationType));
/*      */     }
/* 1004 */     return this.rebalancingApplicationType;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRebalancingApplicationType(int rebalancingApplicationType) {
/* 1011 */     if (Trace.isOn) {
/* 1012 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "setRebalancingApplicationType(int)", "setter", 
/* 1013 */           Integer.valueOf(rebalancingApplicationType));
/*      */     }
/* 1015 */     this.rebalancingApplicationType = rebalancingApplicationType;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ChannelSharingMode jmsChannelSharingMode() {
/* 1022 */     if (Trace.isOn) {
/* 1023 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "jmsChannelSharingMode()", "getter", this.jmsChannelSharingMode);
/*      */     }
/*      */     
/* 1026 */     return this.jmsChannelSharingMode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setJmsChannelSharingMode(ChannelSharingMode jmsChannelSharingMode) {
/* 1033 */     if (Trace.isOn) {
/* 1034 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiConnectOptions", "setUseFastConnect()", "setter", jmsChannelSharingMode);
/*      */     }
/*      */     
/* 1037 */     this.jmsChannelSharingMode = jmsChannelSharingMode;
/*      */   }
/*      */   
/*      */   public static interface JmqiOptionAdapter {
/*      */     long getLongOption();
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\JmqiConnectOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */