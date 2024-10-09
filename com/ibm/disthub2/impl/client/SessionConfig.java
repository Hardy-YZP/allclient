/*     */ package com.ibm.disthub2.impl.client;
/*     */ 
/*     */ import com.ibm.disthub2.impl.util.Assert;
/*     */ import com.ibm.disthub2.impl.util.DoPrivileged;
/*     */ import com.ibm.disthub2.impl.util.FeatureSet;
/*     */ import com.ibm.disthub2.spi.ClientExceptionConstants;
/*     */ import com.ibm.disthub2.spi.ClientLogConstants;
/*     */ import com.ibm.disthub2.spi.ExceptionBuilder;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Properties;
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
/*     */ public class SessionConfig
/*     */   extends BaseConfig
/*     */   implements ClientLogConstants, ClientExceptionConstants, Cloneable
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   public static String[] parameters;
/*     */   public static String[] undocParameters;
/*     */   public static final int RELEASE_VERSION = 65538;
/*     */   public static final String SUBTOPIC_MATCHMANY = "#";
/*     */   public static final String SUBTOPIC_MATCHONE = "+";
/*     */   public static final char SUBTOPIC_SEPARATOR_CHAR = '/';
/*     */   public static final char SUBTOPIC_MATCHMANY_CHAR = '#';
/*     */   public static final char SUBTOPIC_MATCHONE_CHAR = '+';
/*     */   public static final String DEFAULT_ADMIN_TOPIC_PREFIX = "Gryphon/Admin/";
/*     */   
/*     */   public static SessionConfig getSessionConfig() {
/* 125 */     return (SessionConfig)global;
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
/*     */   static {
/* 145 */     String[] init = { "MAX_CLIENT_READ_THREADS", "CLIENT_THREAD_POLLING_INTERVAL", "MY_BROKER_PORT", "ENABLE_SOCKS", "MAX_MESSAGE_QUEUE_SIZE", "LOG_MAX_QUEUE", "MATCH_CACHE_INITIAL_CAPACITY", "MATCH_CACHE_INITIAL_CAPACITY", "MULTICAST_ENABLED", "MULTICAST_DATA_PORT", "MULTICAST_LOG_ERROR_REPORTS", "MULTICAST_TRACING_LEVEL", "MULTICAST_LOG_FILE", "MULTICAST_BACKOFF_TIME_MILLIS", "MULTICAST_NACK_CHECK_PERIOD_MILLIS", "MULTICAST_SOCKET_BUFFER_SIZE_KBYTE", "MULTICAST_MULTICAST_INTERFACE", "MULTICAST_PACKET_BUFFERS", "MULTICAST_PROTOCOL_TYPE", "MULTICAST_MAX_MEMORY_ALLOWED_K_BYTES" };
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
/* 170 */     String[] initUndoc = new String[0];
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 176 */       Class<? super SessionConfig> parent = SessionConfig.class.getSuperclass();
/* 177 */       String[] newParams = (String[])parent.getField("parameters").get(null);
/* 178 */       parameters = new String[newParams.length + init.length];
/* 179 */       System.arraycopy(newParams, 0, parameters, 0, newParams.length);
/* 180 */       System.arraycopy(init, 0, parameters, newParams.length, init.length);
/* 181 */       String[] newUndocParams = (String[])parent.getField("undocParameters").get(null);
/* 182 */       undocParameters = new String[newUndocParams.length + initUndoc.length];
/* 183 */       System.arraycopy(newUndocParams, 0, undocParameters, 0, newUndocParams.length);
/* 184 */       System.arraycopy(initUndoc, 0, undocParameters, newUndocParams.length, initUndoc.length);
/* 185 */     } catch (Exception e) {
/*     */       
/* 187 */       Assert.failure(e);
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
/* 230 */   public int MAX_CLIENT_READ_THREADS = 0;
/*     */ 
/*     */ 
/*     */   
/* 234 */   public int CLIENT_THREAD_POLLING_INTERVAL = 100;
/*     */ 
/*     */   
/* 237 */   public int MY_BROKER_PORT = 1506;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 243 */   public String HTTP_PROXY = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 249 */   public int HTTP_PROXY_PORT = 8080;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 255 */   public int HTTP_PROXY_TIMEOUT = 10000;
/*     */ 
/*     */   
/* 258 */   public int MAX_MESSAGE_QUEUE_SIZE = 1000;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean ENABLE_SOCKS = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 269 */   public final byte REPLY_TOPIC_QOP = 14;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 276 */   public int LOG_MAX_QUEUE = 1000;
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
/* 287 */   public int MATCH_CACHE_INITIAL_CAPACITY = 10000;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean MULTICAST_ENABLED = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 306 */   public static int MULTICAST_DATA_PORT = 34343;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean MULTICAST_LOG_ERROR_REPORTS = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 322 */   public static String MULTICAST_TRACING_LEVEL = "basic";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 329 */   public static String MULTICAST_LOG_FILE = "std";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 334 */   public static int MULTICAST_BACKOFF_TIME_MILLIS = 100;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 339 */   public static int MULTICAST_NACK_CHECK_PERIOD_MILLIS = 1000;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 344 */   public static int MULTICAST_SOCKET_BUFFER_SIZE_KBYTE = 3000;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 349 */   public static int MULTICAST_PACKET_BUFFERS = 500;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 355 */   public String MULTICAST_MULTICAST_INTERFACE = "none";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 361 */   public String MULTICAST_PROTOCOL_TYPE = "PTL";
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
/* 374 */   public static int MULTICAST_MAX_MEMORY_ALLOWED_K_BYTES = 262144;
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
/*     */   public static long checkConsistency() {
/* 393 */     SessionConfig sc = (SessionConfig)global;
/*     */     
/* 395 */     if (sc.MAX_CLIENT_READ_THREADS < 0 || (sc.CLIENT_THREAD_POLLING_INTERVAL <= 0 && sc.MAX_CLIENT_READ_THREADS != 0))
/*     */     {
/*     */       
/* 398 */       return 18054161409129114L;
/*     */     }
/* 400 */     if (sc.CLIENT_PING_INTERVAL > 0) {
/* 401 */       if (sc.PING_TIMEOUT_MULTIPLE <= 0)
/* 402 */         return 18063217281625150L; 
/* 403 */       if (sc.PING_MIN <= 0) {
/* 404 */         return 18054007676077718L;
/*     */       }
/*     */     } 
/* 407 */     if (sc.EXPECTED_MESSAGE_SIZE <= 0)
/* 408 */       return 18079543986522840L; 
/* 409 */     if (sc.MAX_MESSAGE_SIZE <= 0)
/* 410 */       return 18073413546909307L; 
/* 411 */     if (sc.MESSAGE_SIZE_EXTRA_ALLOWANCE < 0) {
/* 412 */       return 18049976867431774L;
/*     */     }
/* 414 */     return 0L;
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
/*     */   public static synchronized void initialize() {
/* 437 */     initialize(SessionConfig.class);
/*     */ 
/*     */ 
/*     */     
/* 441 */     long err = checkConsistency();
/* 442 */     if (err != 0L) {
/* 443 */       throw new IllegalArgumentException(ExceptionBuilder.buildReasonString(-175978758, new Object[] { String.valueOf(err) }));
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
/*     */   public static synchronized void initialize(Properties props) {
/* 455 */     String name = null;
/* 456 */     String val = null;
/*     */     try {
/* 458 */       for (int i = 0; i < parameters.length; i++) {
/* 459 */         name = parameters[i];
/* 460 */         val = props.getProperty(name);
/* 461 */         if (val != null) {
/* 462 */           setParameter(SessionConfig.class, name, val);
/* 463 */           props.remove(name);
/*     */         }
/*     */       
/*     */       } 
/* 467 */     } catch (NumberFormatException e) {
/* 468 */       throw new NumberFormatException(ExceptionBuilder.buildReasonString(-1422303942, new Object[] { name, e }));
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 473 */     Properties config = null;
/*     */ 
/*     */ 
/*     */     
/* 477 */     Properties explicit = DoPrivileged.getProperties();
/*     */ 
/*     */     
/* 480 */     if (explicit != null) {
/* 481 */       String conf = explicit.getProperty("DistHub.Config");
/* 482 */       if (conf != null) {
/* 483 */         config = new Properties();
/*     */         try {
/* 485 */           FileInputStream istr = new FileInputStream(conf);
/* 486 */           config.load(istr);
/* 487 */         } catch (IOException e) {
/* 488 */           System.err.println("Warning: could not read configuration file " + conf);
/* 489 */           config = null;
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 494 */       for (int i = 0; i < parameters.length; i++) {
/* 495 */         name = parameters[i];
/* 496 */         val = explicit.getProperty("DistHub.Parameter." + name);
/* 497 */         if (val == null && config != null)
/* 498 */           val = config.getProperty(name); 
/* 499 */         if (val != null) {
/* 500 */           setParameter(SessionConfig.class, name, val);
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 507 */     long err = checkConsistency();
/* 508 */     if (err != 0L) {
/* 509 */       throw new IllegalArgumentException(ExceptionBuilder.buildReasonString(-175978758, new Object[] { String.valueOf(err) }));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized void setParameter(String name, String val) throws IllegalArgumentException, IllegalStateException {
/* 516 */     setParameter(SessionConfig.class, name, val);
/*     */ 
/*     */ 
/*     */     
/* 520 */     long err = checkConsistency();
/* 521 */     if (err != 0L) {
/* 522 */       throw new IllegalArgumentException(ExceptionBuilder.buildReasonString(-175978758, new Object[] { String.valueOf(err) }));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized void setParameter(Properties P) throws IllegalArgumentException, IllegalStateException {
/* 533 */     setParameter(SessionConfig.class, P);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getParameter(String name) throws IllegalArgumentException {
/* 543 */     return getParameter(SessionConfig.class, name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Properties getParameter() throws IllegalArgumentException {
/* 552 */     return getParameter(SessionConfig.class);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Properties getAllParameters() throws IllegalArgumentException {
/* 561 */     return getAllParameters(SessionConfig.class);
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
/*     */   public static void populate(String feature, FeatureSet set) {
/* 574 */     populateAux(SessionConfig.class, feature, set);
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
/*     */   protected static void populateAux(Class cls, String feature, FeatureSet set) {
/* 589 */     String[] parameters = null;
/*     */     
/*     */     try {
/* 592 */       parameters = (String[])cls.getField("parameters").get(null);
/* 593 */     } catch (Exception e) {
/*     */       
/* 595 */       Assert.condition(false);
/*     */     } 
/*     */     
/* 598 */     int len = parameters.length;
/*     */     
/* 600 */     for (int i = 0; i < len; i++) {
/* 601 */       String val, p = parameters[i];
/*     */ 
/*     */       
/*     */       try {
/* 605 */         val = getParameter(cls, p);
/* 606 */       } catch (IllegalArgumentException e) {
/* 607 */         val = "********field not accessible********";
/*     */       } 
/*     */       
/* 610 */       set.put(feature, p, "" + val);
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
/*     */   public Properties getMulticastProperties() {
/* 622 */     Properties props = new Properties();
/* 623 */     Properties allProps = getAllParameters();
/* 624 */     Enumeration<Object> enum1 = allProps.keys();
/*     */     
/* 626 */     while (enum1.hasMoreElements()) {
/*     */       
/* 628 */       String key = (String)enum1.nextElement();
/*     */       
/* 630 */       if (key.startsWith("MULTICAST"))
/*     */       {
/* 632 */         props.put(key, allProps.get(key));
/*     */       }
/*     */     } 
/*     */     
/* 636 */     props.put("MULTICAST_MULTICAST_INTERFACE", this.MULTICAST_MULTICAST_INTERFACE);
/* 637 */     props.put("MULTICAST_PROTOCOL_TYPE", this.MULTICAST_PROTOCOL_TYPE);
/*     */     
/* 639 */     return props;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\client\SessionConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */