/*     */ package com.ibm.msg.client.commonservices.monitor;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.CSIException;
/*     */ import com.ibm.msg.client.commonservices.CommonServices;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.lang.management.ManagementFactory;
/*     */ import java.security.AccessControlException;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ import java.security.PrivilegedActionException;
/*     */ import java.security.PrivilegedExceptionAction;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import javax.management.InstanceAlreadyExistsException;
/*     */ import javax.management.InstanceNotFoundException;
/*     */ import javax.management.MBeanRegistrationException;
/*     */ import javax.management.MBeanServer;
/*     */ import javax.management.MalformedObjectNameException;
/*     */ import javax.management.NotCompliantMBeanException;
/*     */ import javax.management.ObjectInstance;
/*     */ import javax.management.ObjectName;
/*     */ import javax.management.StandardMBean;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MonitorAgent
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/monitor/MonitorAgent.java";
/*     */   
/*     */   static {
/*  57 */     if (Trace.isOn) {
/*  58 */       Trace.data("com.ibm.msg.client.commonservices.monitor.MonitorAgent", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/monitor/MonitorAgent.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   private static MBeanServer mBeanServer = null;
/*  67 */   private static String productRootName = null;
/*     */   
/*     */   static {
/*  70 */     if (Trace.isOn) {
/*  71 */       Trace.entry("com.ibm.msg.client.commonservices.monitor.MonitorAgent", "static()");
/*     */     }
/*     */     try {
/*  74 */       initialize();
/*     */     }
/*  76 */     catch (CSIException csie) {
/*  77 */       if (Trace.isOn) {
/*  78 */         Trace.catchBlock("com.ibm.msg.client.commonservices.monitor.MonitorAgent", "static()", (Throwable)csie);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  83 */     if (Trace.isOn) {
/*  84 */       Trace.exit("com.ibm.msg.client.commonservices.monitor.MonitorAgent", "static()");
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
/*     */   public static void initialize() throws CSIException {
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.entry("com.ibm.msg.client.commonservices.monitor.MonitorAgent", "initialize()");
/*     */     }
/*     */ 
/*     */     
/* 101 */     mBeanServer = AccessController.<MBeanServer>doPrivileged(new PrivilegedAction<MBeanServer>()
/*     */         {
/*     */           public MBeanServer run()
/*     */           {
/* 105 */             if (Trace.isOn) {
/* 106 */               Trace.entry(this, "com.ibm.msg.client.commonservices.monitor.MonitorAgent.initializeInnerClass", "run()1");
/*     */             }
/*     */             try {
/* 109 */               MBeanServer traceRet1 = ManagementFactory.getPlatformMBeanServer();
/* 110 */               if (Trace.isOn) {
/* 111 */                 Trace.exit(this, "com.ibm.msg.client.commonservices.monitor.MonitorAgent.initializeInnerClass", "run()1", traceRet1, 1);
/*     */               }
/* 113 */               return traceRet1;
/*     */             }
/* 115 */             catch (AccessControlException ace) {
/* 116 */               if (Trace.isOn) {
/* 117 */                 Trace.catchBlock(this, "com.ibm.msg.client.commonservices.monitor.MonitorAgent.initializeInnerClass", "run()1", ace);
/*     */               }
/* 119 */               if (Trace.isOn) {
/* 120 */                 Trace.exit(this, "com.ibm.msg.client.commonservices.monitor.MonitorAgent.initializeInnerClass", "run()1", null, 2);
/*     */               }
/* 122 */               return null;
/*     */             } 
/*     */           }
/*     */         });
/*     */     
/* 127 */     Properties productization = CommonServices.getProductization();
/* 128 */     productRootName = (String)productization.get("ProductName");
/*     */     
/* 130 */     if (Trace.isOn) {
/* 131 */       Trace.exit("com.ibm.msg.client.commonservices.monitor.MonitorAgent", "initialize()");
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
/*     */   public static void registerMBean(final Object newMBean, String type, String newBeanName) {
/*     */     final ObjectName newMBeanObjectName;
/* 147 */     if (Trace.isOn) {
/* 148 */       Trace.entry("com.ibm.msg.client.commonservices.monitor.MonitorAgent", "registerMBean(final Object,String,String)", new Object[] { newMBean, type, newBeanName });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 166 */       StringBuffer fullName = new StringBuffer("IBM MQ");
/* 167 */       fullName.append(':');
/* 168 */       if (type != null) {
/* 169 */         fullName.append("type=" + type + ",");
/*     */       }
/* 171 */       fullName.append("name=" + newBeanName);
/*     */       
/* 173 */       newMBeanObjectName = new ObjectName(fullName.toString());
/*     */     }
/* 175 */     catch (MalformedObjectNameException e) {
/* 176 */       if (Trace.isOn) {
/* 177 */         Trace.catchBlock("com.ibm.msg.client.commonservices.monitor.MonitorAgent", "registerMBean(final Object,String,String)", e, 1);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 184 */       if (Trace.isOn) {
/* 185 */         Trace.exit("com.ibm.msg.client.commonservices.monitor.MonitorAgent", "registerMBean(final Object,String,String)", 1);
/*     */       }
/*     */ 
/*     */       
/*     */       return;
/* 190 */     } catch (NullPointerException e) {
/* 191 */       if (Trace.isOn) {
/* 192 */         Trace.catchBlock("com.ibm.msg.client.commonservices.monitor.MonitorAgent", "registerMBean(final Object,String,String)", e, 2);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 199 */       if (Trace.isOn) {
/* 200 */         Trace.exit("com.ibm.msg.client.commonservices.monitor.MonitorAgent", "registerMBean(final Object,String,String)", 2);
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*     */     try {
/*     */       try {
/* 208 */         AccessController.doPrivileged(new PrivilegedExceptionAction<ObjectInstance>()
/*     */             {
/*     */               public ObjectInstance run() throws Exception
/*     */               {
/* 212 */                 if (Trace.isOn) {
/* 213 */                   Trace.entry(this, "com.ibm.msg.client.commonservices.monitor.MonitorAgent.registerMBeanInnerClass", "run()2");
/*     */                 }
/*     */                 try {
/* 216 */                   ObjectInstance traceRet1 = MonitorAgent.mBeanServer.registerMBean(newMBean, newMBeanObjectName);
/* 217 */                   if (Trace.isOn) {
/* 218 */                     Trace.exit(this, "com.ibm.msg.client.commonservices.monitor.MonitorAgent.registerMBeanInnerClass", "run()2", traceRet1, 1);
/*     */                   }
/*     */                   
/* 221 */                   return traceRet1;
/*     */                 }
/* 223 */                 catch (AccessControlException ace) {
/* 224 */                   if (Trace.isOn) {
/* 225 */                     Trace.catchBlock(this, "com.ibm.msg.client.commonservices.monitor.MonitorAgent.registerMBeanInnerClass", "run()2", ace, 1);
/*     */                   }
/*     */ 
/*     */                   
/* 229 */                   if (Trace.isOn) {
/* 230 */                     Trace.exit(this, "com.ibm.msg.client.commonservices.monitor.MonitorAgent.registerMBeanInnerClass", "run()2", null, 2);
/*     */                   }
/* 232 */                   return null;
/*     */                 }
/* 234 */                 catch (InstanceAlreadyExistsException e) {
/* 235 */                   if (Trace.isOn) {
/* 236 */                     Trace.catchBlock(this, "com.ibm.msg.client.commonservices.monitor.MonitorAgent.registerMBeanInnerClass", "run()2", e, 2);
/*     */                   }
/*     */ 
/*     */ 
/*     */ 
/*     */                   
/* 242 */                   if (Trace.isOn) {
/* 243 */                     Trace.exit(this, "com.ibm.msg.client.commonservices.monitor.MonitorAgent.registerMBeanInnerClass", "run()2", null, 3);
/*     */                   }
/* 245 */                   return null;
/*     */                 }
/*     */               
/*     */               }
/*     */             });
/*     */       }
/* 251 */       catch (PrivilegedActionException pae) {
/* 252 */         if (Trace.isOn) {
/* 253 */           Trace.catchBlock("com.ibm.msg.client.commonservices.monitor.MonitorAgent", "registerMBean(final Object,String,String)", pae, 3);
/*     */         }
/*     */ 
/*     */         
/* 257 */         Throwable t = pae.getCause();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 267 */         if (Trace.isOn) {
/* 268 */           Trace.throwing("com.ibm.msg.client.commonservices.monitor.MonitorAgent", "registerMBean(final Object,String,String)", t);
/*     */         }
/*     */         
/* 271 */         throw t;
/*     */       }
/*     */     
/*     */     }
/* 275 */     catch (InstanceAlreadyExistsException e) {
/* 276 */       if (Trace.isOn) {
/* 277 */         Trace.catchBlock("com.ibm.msg.client.commonservices.monitor.MonitorAgent", "registerMBean(final Object,String,String)", e, 4);
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 282 */     catch (MBeanRegistrationException e) {
/* 283 */       if (Trace.isOn) {
/* 284 */         Trace.catchBlock("com.ibm.msg.client.commonservices.monitor.MonitorAgent", "registerMBean(final Object,String,String)", e, 5);
/*     */ 
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 290 */     catch (NotCompliantMBeanException e) {
/* 291 */       if (Trace.isOn) {
/* 292 */         Trace.catchBlock("com.ibm.msg.client.commonservices.monitor.MonitorAgent", "registerMBean(final Object,String,String)", e, 6);
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 297 */     catch (Throwable t) {
/* 298 */       if (Trace.isOn) {
/* 299 */         Trace.catchBlock("com.ibm.msg.client.commonservices.monitor.MonitorAgent", "registerMBean(final Object,String,String)", t, 7);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 304 */     if (Trace.isOn) {
/* 305 */       Trace.exit("com.ibm.msg.client.commonservices.monitor.MonitorAgent", "registerMBean(final Object,String,String)", 3);
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
/*     */   public static ObjectName registerMBean(StandardMBean newMBean, Map<String, String> properties) {
/* 317 */     if (Trace.isOn) {
/* 318 */       Trace.entry("com.ibm.msg.client.commonservices.monitor.MonitorAgent", "registerMBean(StandardMBean,Map<String , String>)", new Object[] { newMBean, properties });
/*     */     }
/*     */     
/* 321 */     ObjectName traceRet1 = registerMBean(newMBean, (String)null, properties);
/* 322 */     if (Trace.isOn) {
/* 323 */       Trace.exit("com.ibm.msg.client.commonservices.monitor.MonitorAgent", "registerMBean(StandardMBean,Map<String , String>)", traceRet1);
/*     */     }
/*     */     
/* 326 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ObjectName registerMBean(final StandardMBean newMBean, String rootName, Map<String, String> properties) {
/*     */     final ObjectName newMBeanObjectName;
/* 336 */     if (Trace.isOn) {
/* 337 */       Trace.entry("com.ibm.msg.client.commonservices.monitor.MonitorAgent", "registerMBean(final StandardMBean,String,Map<String , String>)", new Object[] { newMBean, rootName, properties });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 343 */     StringBuffer fullName = new StringBuffer();
/*     */     
/*     */     try {
/* 346 */       if (rootName == null) {
/* 347 */         fullName.append(productRootName);
/*     */       } else {
/*     */         
/* 350 */         fullName.append(rootName);
/*     */       } 
/* 352 */       fullName.append(':');
/*     */       
/* 354 */       boolean repeating = false;
/*     */       
/* 356 */       for (Map.Entry<String, String> entry : properties.entrySet()) {
/* 357 */         if (repeating) {
/* 358 */           fullName.append(",");
/*     */         } else {
/*     */           
/* 361 */           repeating = true;
/*     */         } 
/* 363 */         fullName.append(entry.getKey());
/* 364 */         fullName.append("=");
/* 365 */         fullName.append(entry.getValue());
/*     */       } 
/*     */       
/* 368 */       newMBeanObjectName = new ObjectName(fullName.toString());
/*     */     }
/* 370 */     catch (MalformedObjectNameException e) {
/* 371 */       if (Trace.isOn) {
/* 372 */         Trace.catchBlock("com.ibm.msg.client.commonservices.monitor.MonitorAgent", "registerMBean(final StandardMBean,String,Map<String , String>)", e, 1);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 379 */       if (Trace.isOn) {
/* 380 */         Trace.exit("com.ibm.msg.client.commonservices.monitor.MonitorAgent", "registerMBean(final StandardMBean,String,Map<String , String>)", null, 1);
/*     */       }
/*     */       
/* 383 */       return null;
/*     */     
/*     */     }
/* 386 */     catch (NullPointerException e) {
/* 387 */       if (Trace.isOn) {
/* 388 */         Trace.catchBlock("com.ibm.msg.client.commonservices.monitor.MonitorAgent", "registerMBean(final StandardMBean,String,Map<String , String>)", e, 2);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 395 */       if (Trace.isOn) {
/* 396 */         Trace.exit("com.ibm.msg.client.commonservices.monitor.MonitorAgent", "registerMBean(final StandardMBean,String,Map<String , String>)", null, 2);
/*     */       }
/*     */       
/* 399 */       return null;
/*     */     } 
/*     */ 
/*     */     
/*     */     try {
/*     */       try {
/* 405 */         AccessController.doPrivileged(new PrivilegedExceptionAction<ObjectInstance>()
/*     */             {
/*     */               public ObjectInstance run() throws Exception
/*     */               {
/* 409 */                 if (Trace.isOn) {
/* 410 */                   Trace.entry(this, "com.ibm.msg.client.commonservices.monitor.MonitorAgent.registerMBeanInnerClass", "run()3");
/*     */                 }
/*     */                 try {
/* 413 */                   ObjectInstance traceRet1 = MonitorAgent.mBeanServer.registerMBean(newMBean, newMBeanObjectName);
/* 414 */                   if (Trace.isOn) {
/* 415 */                     Trace.exit(this, "com.ibm.msg.client.commonservices.monitor.MonitorAgent.registerMBeanInnerClass", "run()3", traceRet1, 1);
/*     */                   }
/*     */                   
/* 418 */                   return traceRet1;
/*     */                 }
/* 420 */                 catch (AccessControlException ace) {
/* 421 */                   if (Trace.isOn) {
/* 422 */                     Trace.catchBlock(this, "com.ibm.msg.client.commonservices.monitor.MonitorAgent.registerMBeanInnerClass", "run()3", ace, 1);
/*     */                   }
/*     */ 
/*     */                   
/* 426 */                   if (Trace.isOn) {
/* 427 */                     Trace.exit(this, "com.ibm.msg.client.commonservices.monitor.MonitorAgent.registerMBeanInnerClass", "run()3", null, 2);
/*     */                   }
/*     */                   
/* 430 */                   return null;
/*     */                 }
/* 432 */                 catch (InstanceAlreadyExistsException e) {
/* 433 */                   if (Trace.isOn) {
/* 434 */                     Trace.catchBlock(this, "com.ibm.msg.client.commonservices.monitor.MonitorAgent.registerMBeanInnerClass", "run()3", e, 2);
/*     */                   }
/*     */ 
/*     */ 
/*     */                   
/* 439 */                   if (Trace.isOn) {
/* 440 */                     Trace.exit(this, "com.ibm.msg.client.commonservices.monitor.MonitorAgent.registerMBeanInnerClass", "run()3", null, 3);
/*     */                   }
/*     */                   
/* 443 */                   return null;
/*     */                 }
/*     */               
/*     */               }
/*     */             });
/*     */       }
/* 449 */       catch (PrivilegedActionException pae) {
/* 450 */         if (Trace.isOn) {
/* 451 */           Trace.catchBlock("com.ibm.msg.client.commonservices.monitor.MonitorAgent", "registerMBean(final StandardMBean,String,Map<String , String>)", pae, 3);
/*     */         }
/*     */ 
/*     */         
/* 455 */         Throwable t = pae.getCause();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 465 */         if (Trace.isOn) {
/* 466 */           Trace.throwing("com.ibm.msg.client.commonservices.monitor.MonitorAgent", "registerMBean(final StandardMBean,String,Map<String , String>)", t);
/*     */         }
/*     */         
/* 469 */         throw t;
/*     */       }
/*     */     
/*     */     }
/* 473 */     catch (InstanceAlreadyExistsException e) {
/* 474 */       if (Trace.isOn) {
/* 475 */         Trace.catchBlock("com.ibm.msg.client.commonservices.monitor.MonitorAgent", "registerMBean(final StandardMBean,String,Map<String , String>)", e, 4);
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 480 */     catch (MBeanRegistrationException e) {
/* 481 */       if (Trace.isOn) {
/* 482 */         Trace.catchBlock("com.ibm.msg.client.commonservices.monitor.MonitorAgent", "registerMBean(final StandardMBean,String,Map<String , String>)", e, 5);
/*     */ 
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 488 */     catch (NotCompliantMBeanException e) {
/* 489 */       if (Trace.isOn) {
/* 490 */         Trace.catchBlock("com.ibm.msg.client.commonservices.monitor.MonitorAgent", "registerMBean(final StandardMBean,String,Map<String , String>)", e, 6);
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 495 */     catch (Throwable t) {
/* 496 */       if (Trace.isOn) {
/* 497 */         Trace.catchBlock("com.ibm.msg.client.commonservices.monitor.MonitorAgent", "registerMBean(final StandardMBean,String,Map<String , String>)", t, 7);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 507 */     if (Trace.isOn) {
/* 508 */       Trace.exit("com.ibm.msg.client.commonservices.monitor.MonitorAgent", "registerMBean(final StandardMBean,String,Map<String , String>)", newMBeanObjectName, 3);
/*     */     }
/*     */     
/* 511 */     return newMBeanObjectName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void unregisterMBean(final ObjectName name) {
/* 518 */     if (Trace.isOn) {
/* 519 */       Trace.entry("com.ibm.msg.client.commonservices.monitor.MonitorAgent", "unregisterMBean(final ObjectName)", new Object[] { name });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       try {
/* 525 */         AccessController.doPrivileged(new PrivilegedExceptionAction()
/*     */             {
/*     */               public Object run() throws Exception
/*     */               {
/* 529 */                 if (Trace.isOn) {
/* 530 */                   Trace.entry(this, "com.ibm.msg.client.commonservices.monitor.MonitorAgent", "run()");
/*     */                 }
/*     */                 
/* 533 */                 MonitorAgent.mBeanServer.unregisterMBean(name);
/* 534 */                 if (Trace.isOn) {
/* 535 */                   Trace.exit(this, "com.ibm.msg.client.commonservices.monitor.null", "run()", null);
/*     */                 }
/* 537 */                 return null;
/*     */               }
/*     */             });
/*     */       }
/* 541 */       catch (PrivilegedActionException pae) {
/* 542 */         if (Trace.isOn) {
/* 543 */           Trace.catchBlock("com.ibm.msg.client.commonservices.monitor.MonitorAgent", "unregisterMBean(final ObjectName)", pae, 1);
/*     */         }
/*     */         
/* 546 */         Throwable t = pae.getCause();
/* 547 */         if (Trace.isOn) {
/* 548 */           Trace.throwing("com.ibm.msg.client.commonservices.monitor.MonitorAgent", "unregisterMBean(final ObjectName)", t);
/*     */         }
/*     */         
/* 551 */         throw t;
/*     */       }
/*     */     
/*     */     }
/* 555 */     catch (MBeanRegistrationException e) {
/* 556 */       if (Trace.isOn) {
/* 557 */         Trace.catchBlock("com.ibm.msg.client.commonservices.monitor.MonitorAgent", "unregisterMBean(final ObjectName)", e, 2);
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 562 */     catch (InstanceNotFoundException e) {
/* 563 */       if (Trace.isOn) {
/* 564 */         Trace.catchBlock("com.ibm.msg.client.commonservices.monitor.MonitorAgent", "unregisterMBean(final ObjectName)", e, 3);
/*     */ 
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 570 */     catch (Throwable t) {
/* 571 */       if (Trace.isOn) {
/* 572 */         Trace.catchBlock("com.ibm.msg.client.commonservices.monitor.MonitorAgent", "unregisterMBean(final ObjectName)", t, 4);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 578 */     if (Trace.isOn) {
/* 579 */       Trace.exit("com.ibm.msg.client.commonservices.monitor.MonitorAgent", "unregisterMBean(final ObjectName)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void unregisterMBean(String beanName, String type) {
/*     */     ObjectName mBeanObjectName;
/* 590 */     if (Trace.isOn) {
/* 591 */       Trace.entry("com.ibm.msg.client.commonservices.monitor.MonitorAgent", "unregisterMBean(String,String)", new Object[] { beanName, type });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 599 */       StringBuffer fullName = new StringBuffer("IBM MQ");
/* 600 */       fullName.append(':');
/* 601 */       if (type != null) {
/* 602 */         fullName.append("type=" + type + ",");
/*     */       }
/* 604 */       fullName.append("name=" + beanName);
/*     */       
/* 606 */       mBeanObjectName = new ObjectName(fullName.toString());
/*     */     }
/* 608 */     catch (MalformedObjectNameException e) {
/* 609 */       if (Trace.isOn) {
/* 610 */         Trace.catchBlock("com.ibm.msg.client.commonservices.monitor.MonitorAgent", "unregisterMBean(String,String)", e, 1);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 616 */       if (Trace.isOn) {
/* 617 */         Trace.exit("com.ibm.msg.client.commonservices.monitor.MonitorAgent", "unregisterMBean(String,String)", 1);
/*     */       }
/*     */ 
/*     */       
/*     */       return;
/* 622 */     } catch (NullPointerException e) {
/* 623 */       if (Trace.isOn) {
/* 624 */         Trace.catchBlock("com.ibm.msg.client.commonservices.monitor.MonitorAgent", "unregisterMBean(String,String)", e, 2);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 630 */       if (Trace.isOn) {
/* 631 */         Trace.exit("com.ibm.msg.client.commonservices.monitor.MonitorAgent", "unregisterMBean(String,String)", 2);
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 637 */     unregisterMBean(mBeanObjectName);
/*     */     
/* 639 */     if (Trace.isOn)
/* 640 */       Trace.exit("com.ibm.msg.client.commonservices.monitor.MonitorAgent", "unregisterMBean(String,String)", 3); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\monitor\MonitorAgent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */