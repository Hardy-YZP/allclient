/*     */ package com.ibm.msg.client.commonservices.cssystem;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.security.AccessControlException;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CSSystem
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/cssystem/CSSystem.java";
/*     */   public static final int ALL_CLASSLOADERS = 255;
/*     */   public static final int THREAD_CONTEXT_CLASSLOADER = 1;
/*     */   public static final int SYSTEM_CLASSLOADER = 2;
/*     */   public static final int CLASS_CLASSLOADER = 4;
/*     */   public static final int CSSYSTEM_CLASSLOADER = 8;
/*     */   
/*     */   static {
/*  47 */     if (Trace.isOn) {
/*  48 */       Trace.data("com.ibm.msg.client.commonservices.cssystem.CSSystem", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/cssystem/CSSystem.java");
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
/*     */   public static Class<?> dynamicLoadClass(String name, Class<?> loadingClass, boolean doSecurityCheck) throws ClassNotFoundException {
/*  87 */     if (Trace.isOn) {
/*  88 */       Trace.entry("com.ibm.msg.client.commonservices.cssystem.CSSystem", "dynamicLoadClass(final String,final Class<?>,boolean)", new Object[] { name, loadingClass, 
/*     */             
/*  90 */             Boolean.valueOf(doSecurityCheck) });
/*     */     }
/*     */     
/*  93 */     Class<?> traceRet1 = dynamicLoadClass(name, loadingClass, true, 255);
/*  94 */     if (Trace.isOn) {
/*  95 */       Trace.exit("com.ibm.msg.client.commonservices.cssystem.CSSystem", "dynamicLoadClass(final String,final Class<?>,boolean)", traceRet1);
/*     */     }
/*     */     
/*  98 */     return traceRet1;
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
/*     */   public static Class<?> dynamicLoadClass(String name, Class<?> loadingClass) throws ClassNotFoundException {
/* 117 */     if (Trace.isOn) {
/* 118 */       Trace.entry("com.ibm.msg.client.commonservices.cssystem.CSSystem", "dynamicLoadClass(final String,final Class<?>)", new Object[] { name, loadingClass });
/*     */     }
/*     */ 
/*     */     
/* 122 */     Class<?> traceRet1 = dynamicLoadClass(name, loadingClass, true);
/* 123 */     if (Trace.isOn) {
/* 124 */       Trace.exit("com.ibm.msg.client.commonservices.cssystem.CSSystem", "dynamicLoadClass(final String,final Class<?>)", traceRet1);
/*     */     }
/*     */     
/* 127 */     return traceRet1;
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
/*     */   
/*     */   public static Class<?> dynamicLoadClass(final String name, final Class<?> loadingClass, boolean doSecurityCheck, final int classLoadersInUse) throws ClassNotFoundException {
/*     */     Object[] results;
/* 172 */     if (Trace.isOn) {
/* 173 */       Trace.entry("com.ibm.msg.client.commonservices.cssystem.CSSystem", "dynamicLoadClass(final String,final Class<?>,boolean,final int)", new Object[] { name, loadingClass, 
/*     */             
/* 175 */             Boolean.valueOf(doSecurityCheck), Integer.valueOf(classLoadersInUse) });
/*     */     }
/*     */ 
/*     */     
/* 179 */     if (doSecurityCheck) {
/*     */       
/* 181 */       results = AccessController.<Object[]>doPrivileged(new PrivilegedAction<Object[]>()
/*     */           {
/*     */             public Object[] run()
/*     */             {
/* 185 */               if (Trace.isOn) {
/* 186 */                 Trace.entry(this, "com.ibm.msg.client.commonservices.cssystem.CSSystem", "run()");
/*     */               }
/* 188 */               Object[] traceRet1 = CSSystem.loadClassInternal(name, loadingClass, classLoadersInUse);
/* 189 */               if (Trace.isOn) {
/* 190 */                 Trace.exit(this, "com.ibm.msg.client.commonservices.cssystem.null", "run()", traceRet1);
/*     */               }
/*     */               
/* 193 */               return traceRet1;
/*     */             }
/*     */           });
/*     */     } else {
/*     */       
/* 198 */       results = loadClassInternal(name, loadingClass, classLoadersInUse);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 205 */     if (results[1] != null && results[1] instanceof ClassNotFoundException) {
/* 206 */       ClassNotFoundException traceRet2 = (ClassNotFoundException)results[1];
/* 207 */       if (Trace.isOn) {
/* 208 */         Trace.throwing("com.ibm.msg.client.commonservices.cssystem.CSSystem", "dynamicLoadClass(final String,final Class<?>,boolean,final int)", traceRet2);
/*     */       }
/*     */       
/* 211 */       throw traceRet2;
/*     */     } 
/* 213 */     if (results[0] instanceof Class) {
/* 214 */       Class<?> traceRet3 = (Class)results[0];
/* 215 */       if (Trace.isOn) {
/* 216 */         Trace.exit("com.ibm.msg.client.commonservices.cssystem.CSSystem", "dynamicLoadClass(final String,final Class<?>,boolean,final int)", traceRet3, 1);
/*     */       }
/*     */       
/* 219 */       return traceRet3;
/*     */     } 
/*     */     
/* 222 */     if (Trace.isOn) {
/* 223 */       Trace.exit("com.ibm.msg.client.commonservices.cssystem.CSSystem", "dynamicLoadClass(final String,final Class<?>,boolean,final int)", null, 2);
/*     */     }
/*     */     
/* 226 */     return null;
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
/*     */   private static Object[] loadClassInternal(String name, final Class<?> loadingClass, int classLoadersInUse) {
/* 241 */     if (Trace.isOn) {
/* 242 */       Trace.entry("com.ibm.msg.client.commonservices.cssystem.CSSystem", "loadClassInternal(final String,final Class<?>,final int)", new Object[] { name, loadingClass, 
/*     */             
/* 244 */             Integer.valueOf(classLoadersInUse) });
/*     */     }
/*     */ 
/*     */     
/* 248 */     Class<?> cls = null;
/*     */ 
/*     */     
/* 251 */     Exception exception = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 256 */     ClassLoader[] classLoaders = AccessController.<ClassLoader[]>doPrivileged((PrivilegedAction)new PrivilegedAction<ClassLoader[]>()
/*     */         {
/*     */           public ClassLoader[] run()
/*     */           {
/* 260 */             if (Trace.isOn) {
/* 261 */               Trace.entry(this, "com.ibm.msg.client.commonservices.cssystem.CSSystem", "run()");
/*     */             }
/* 263 */             ClassLoader[] classLoadersInner = new ClassLoader[4];
/*     */             try {
/* 265 */               classLoadersInner[0] = Thread.currentThread().getContextClassLoader();
/*     */             }
/* 267 */             catch (SecurityException se) {
/* 268 */               if (Trace.isOn) {
/* 269 */                 Trace.catchBlock(this, "com.ibm.msg.client.commonservices.cssystem.null", "run()", se, 1);
/*     */               }
/*     */               
/* 272 */               classLoadersInner[0] = null;
/*     */             } 
/*     */             try {
/* 275 */               classLoadersInner[1] = loadingClass.getClassLoader();
/*     */             }
/* 277 */             catch (SecurityException se) {
/* 278 */               if (Trace.isOn) {
/* 279 */                 Trace.catchBlock(this, "com.ibm.msg.client.commonservices.cssystem.null", "run()", se, 2);
/*     */               }
/*     */               
/* 282 */               classLoadersInner[1] = null;
/*     */             } 
/*     */             try {
/* 285 */               classLoadersInner[2] = CSSystem.class.getClassLoader();
/*     */             }
/* 287 */             catch (SecurityException se) {
/* 288 */               if (Trace.isOn) {
/* 289 */                 Trace.catchBlock(this, "com.ibm.msg.client.commonservices.cssystem.null", "run()", se, 3);
/*     */               }
/*     */               
/* 292 */               classLoadersInner[2] = null;
/*     */             } 
/*     */             try {
/* 295 */               classLoadersInner[3] = ClassLoader.getSystemClassLoader();
/*     */             }
/* 297 */             catch (SecurityException se) {
/* 298 */               if (Trace.isOn) {
/* 299 */                 Trace.catchBlock(this, "com.ibm.msg.client.commonservices.cssystem.null", "run()", se, 4);
/*     */               }
/*     */               
/* 302 */               classLoadersInner[3] = null;
/*     */             } 
/* 304 */             if (Trace.isOn) {
/* 305 */               Trace.exit(this, "com.ibm.msg.client.commonservices.cssystem.null", "run()", classLoadersInner);
/*     */             }
/*     */             
/* 308 */             return classLoadersInner;
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 313 */     ClassLoader threadClassloader = classLoaders[0];
/* 314 */     ClassLoader classClassloader = classLoaders[1];
/* 315 */     ClassLoader csSystemClassloader = classLoaders[2];
/* 316 */     ClassLoader systemClassloader = classLoaders[3];
/*     */ 
/*     */     
/* 319 */     if (threadClassloader != null && (classLoadersInUse & 0x1) != 0) {
/*     */       
/*     */       try {
/* 322 */         if (Trace.isOn) {
/* 323 */           Trace.data("CommonClassLoading", "dynamicLoadClass()", "Classload Step 1: Threadcontext Classloader", threadClassloader);
/*     */         }
/*     */ 
/*     */         
/* 327 */         cls = Class.forName(name, true, threadClassloader);
/* 328 */         if (Trace.isOn) {
/* 329 */           Trace.data("CommonClassLoading", "dynamicLoadClass()", "Step 1 After load:", cls);
/*     */         }
/*     */       }
/* 332 */       catch (ClassNotFoundException e) {
/* 333 */         if (Trace.isOn) {
/* 334 */           Trace.data("com.ibm.msg.client.commonservices.cssystem.CSSystem", "loadClassInternal(final String,final Class<?>,final int)", "Caught expected exception at catch index 1", e);
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 343 */     if (cls == null && classClassloader != null && (classLoadersInUse & 0x4) != 0) {
/*     */       
/*     */       try {
/* 346 */         if (Trace.isOn) {
/* 347 */           Trace.data("CommonClassLoading", "dynamicLoadClass()", "Classload Step 2: Loading Class Classlodaer ", classClassloader);
/*     */         }
/*     */         
/* 350 */         cls = Class.forName(name, true, classClassloader);
/* 351 */         if (Trace.isOn) {
/* 352 */           Trace.data("CommonClassLoading", "dynamicLoadClass()", "Step 2 After load:", cls);
/*     */         }
/*     */       }
/* 355 */       catch (ClassNotFoundException e) {
/* 356 */         if (Trace.isOn) {
/* 357 */           Trace.data("com.ibm.msg.client.commonservices.cssystem.CSSystem", "loadClassInternal(final String,final Class<?>,final int)", "Caught expected exception at catch index 2", e);
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 367 */     if (cls == null && csSystemClassloader != null && (classLoadersInUse & 0x8) != 0) {
/*     */       
/*     */       try {
/* 370 */         if (Trace.isOn) {
/* 371 */           Trace.data("CommonClassLoading", "dynamicLoadClass()", "Classload Step 3: CSSystem Classloader ", csSystemClassloader);
/*     */         }
/*     */         
/* 374 */         cls = Class.forName(name, true, csSystemClassloader);
/* 375 */         if (Trace.isOn) {
/* 376 */           Trace.data("CommonClassLoading", "dynamicLoadClass()", "Step 3 After load:", cls);
/*     */         }
/*     */       }
/* 379 */       catch (ClassNotFoundException e) {
/* 380 */         if (Trace.isOn) {
/* 381 */           Trace.data("com.ibm.msg.client.commonservices.cssystem.CSSystem", "loadClassInternal(final String,final Class<?>,final int)", "Caught expected exception at catch index 3", e);
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 391 */     if (cls == null && systemClassloader != null && (classLoadersInUse & 0x2) != 0) {
/*     */       
/*     */       try {
/* 394 */         if (Trace.isOn) {
/* 395 */           Trace.data("CommonClassLoading", "dynamicLoadClass()", "Classload Step 4: class.forName()", "using system classloader");
/*     */         }
/*     */         
/* 398 */         cls = Class.forName(name, true, systemClassloader);
/* 399 */         if (Trace.isOn) {
/* 400 */           Trace.data("CommonClassLoading", "dynamicLoadClass()", "Step 4 After class:", cls);
/* 401 */           Trace.data("CommonClassLoading", "dynamicLoadClass()", "Step 4 Classloader", (cls == null) ? "n/a" : cls
/*     */ 
/*     */               
/* 404 */               .getClassLoader().toString());
/*     */         }
/*     */       
/* 407 */       } catch (ClassNotFoundException e) {
/* 408 */         if (Trace.isOn) {
/* 409 */           Trace.data("com.ibm.msg.client.commonservices.cssystem.CSSystem", "loadClassInternal(final String,final Class<?>,final int)", "Caught expected exception at catch index 4", e);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 415 */         exception = e;
/*     */       } 
/*     */     }
/*     */     
/* 419 */     String info = "No exception thrown";
/* 420 */     if (exception != null) {
/* 421 */       info = exception.toString();
/*     */     }
/* 423 */     Object[] traceRet1 = { cls, info };
/* 424 */     if (Trace.isOn) {
/* 425 */       Trace.exit("com.ibm.msg.client.commonservices.cssystem.CSSystem", "loadClassInternal(final String,final Class<?>,final int)", traceRet1);
/*     */     }
/*     */     
/* 428 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum Platform
/*     */   {
/* 437 */     OS_UNKNOWN("unknown"),
/*     */     
/* 439 */     OS_ZSERIES("z/OS"),
/*     */     
/* 441 */     OS_ISERIES("iSeries"),
/*     */     
/* 443 */     OS_WINDOWS("Windows"),
/*     */     
/* 445 */     OS_AIX("AIX"),
/*     */     
/* 447 */     OS_HP("HP"),
/*     */     
/* 449 */     OS_LINUX("Linux"),
/*     */     
/* 451 */     OS_NSS("NonStop Server");
/*     */ 
/*     */     
/*     */     private final String name;
/*     */ 
/*     */     
/*     */     Platform(String name) {
/* 458 */       if (Trace.isOn) {
/* 459 */         Trace.entry(this, "com.ibm.msg.client.commonservices.cssystem.Platform", "<init>(String)", new Object[] { name });
/*     */       }
/*     */       
/* 462 */       this.name = name;
/* 463 */       if (Trace.isOn) {
/* 464 */         Trace.exit(this, "com.ibm.msg.client.commonservices.cssystem.Platform", "<init>(String)");
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean initialisedOSData = false;
/*     */ 
/*     */ 
/*     */   
/* 475 */   private static Platform currentPlatform = null;
/* 476 */   private static String bitmode = "unknown";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Platform currentPlatform() {
/* 482 */     if (Trace.isOn) {
/* 483 */       Trace.entry("com.ibm.msg.client.commonservices.cssystem.CSSystem", "currentPlatform()");
/*     */     }
/* 485 */     if (!initialisedOSData) {
/* 486 */       initialiseOSData();
/*     */     }
/*     */     
/* 489 */     if (Trace.isOn) {
/* 490 */       Trace.exit("com.ibm.msg.client.commonservices.cssystem.CSSystem", "currentPlatform()", currentPlatform);
/*     */     }
/*     */     
/* 493 */     return currentPlatform;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getBitmode() {
/* 501 */     if (!initialisedOSData) {
/* 502 */       initialiseOSData();
/*     */     }
/* 504 */     if (Trace.isOn) {
/* 505 */       Trace.data("com.ibm.msg.client.commonservices.cssystem.CSSystem", "getBitmode()", "getter", bitmode);
/*     */     }
/*     */     
/* 508 */     return bitmode;
/*     */   }
/*     */ 
/*     */   
/*     */   private static synchronized void initialiseOSData() {
/* 513 */     if (initialisedOSData) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 521 */     String osName = PropertyStore.os_name;
/*     */     
/* 523 */     if (osName.equalsIgnoreCase("OS/390") || osName
/* 524 */       .equalsIgnoreCase("z/OS")) {
/* 525 */       currentPlatform = Platform.OS_ZSERIES;
/* 526 */     } else if (osName.equalsIgnoreCase("OS/400") || osName
/* 527 */       .equalsIgnoreCase("OS400")) {
/* 528 */       currentPlatform = Platform.OS_ISERIES;
/* 529 */     } else if (osName.startsWith("Windows")) {
/* 530 */       currentPlatform = Platform.OS_WINDOWS;
/* 531 */     } else if (osName.equalsIgnoreCase("AIX")) {
/* 532 */       currentPlatform = Platform.OS_AIX;
/* 533 */     } else if (osName.equalsIgnoreCase("HP")) {
/* 534 */       currentPlatform = Platform.OS_HP;
/* 535 */     } else if (osName.equalsIgnoreCase("Linux")) {
/* 536 */       currentPlatform = Platform.OS_LINUX;
/* 537 */     } else if (osName.equalsIgnoreCase("NONSTOP_KERNEL")) {
/* 538 */       currentPlatform = Platform.OS_NSS;
/*     */     } else {
/* 540 */       currentPlatform = Platform.OS_UNKNOWN;
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
/*     */     
/*     */     try {
/* 559 */       PropertyStore.register("com.ibm.vm.bitmode", null);
/* 560 */       PropertyStore.register("sun.arch.data.model", null);
/* 561 */       PropertyStore.register("java.vm.name", null);
/*     */       
/* 563 */       bitmode = PropertyStore.getStringProperty("com.ibm.vm.bitmode");
/* 564 */       if (bitmode == null) {
/*     */         
/* 566 */         bitmode = PropertyStore.getStringProperty("sun.arch.data.model");
/* 567 */         if (bitmode == null)
/*     */         {
/* 569 */           String vmName = PropertyStore.getStringProperty("java.vm.name");
/* 570 */           if (vmName != null && vmName.contains("64-bit")) {
/* 571 */             bitmode = "64";
/*     */           } else {
/* 573 */             bitmode = "32";
/*     */           }
/*     */         
/*     */         }
/*     */       
/*     */       } 
/* 579 */     } catch (AccessControlException ace) {
/* 580 */       if (currentPlatform == Platform.OS_ZSERIES) {
/* 581 */         throw ace;
/*     */       }
/*     */     } 
/*     */     
/* 585 */     initialisedOSData = true;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\cssystem\CSSystem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */