/*     */ package com.ibm.mq;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.msg.client.commonservices.CSIException;
/*     */ import com.ibm.msg.client.commonservices.componentmanager.Component;
/*     */ import com.ibm.msg.client.commonservices.componentmanager.ComponentManager;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQJavaLevel
/*     */   extends JmqiObject
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQJavaLevel.java";
/*     */   private static final String VERSION_TITLE = "Version:     ";
/*     */   private static final String LEVEL_TITLE = "Level:       ";
/*     */   private static final String BUILDTYPE_TITLE = "BuildType:   ";
/*     */   private static final String NAME_TITLE = "Name:        ";
/*     */   private static final String LOCATION_TITLE = "Location:    ";
/*     */   private static final String LOCATION_BUILTIN = "built-in";
/*     */   
/*     */   static {
/*  66 */     if (Trace.isOn) {
/*  67 */       Trace.data("com.ibm.mq.MQJavaLevel", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQJavaLevel.java");
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
/*  84 */   private static final String[] TITLES = new String[] { "Name:        ", "Version:     ", "Level:       ", "BuildType:   ", "Location:    " };
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int mqjCMVCLEVEL = 2;
/*     */ 
/*     */ 
/*     */   
/*  92 */   private static String[] values = new String[TITLES.length];
/*     */ 
/*     */   
/*     */   private static boolean valuesDefined = false;
/*     */   
/*     */   private static final int DEFAULT_FIELDS = 31;
/*     */   
/*  99 */   private static int fields = 31;
/*     */ 
/*     */   
/*     */   private static boolean withTitles = true;
/*     */ 
/*     */   
/*     */   protected static boolean hideLocation = false;
/*     */ 
/*     */   
/*     */   public MQJavaLevel() {
/* 109 */     super(MQSESSION.getJmqiEnv());
/* 110 */     if (Trace.isOn) {
/* 111 */       Trace.entry(this, "com.ibm.mq.MQJavaLevel", "<init>()");
/*     */     }
/* 113 */     if (Trace.isOn) {
/* 114 */       Trace.exit(this, "com.ibm.mq.MQJavaLevel", "<init>()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 125 */     if (Trace.isOn) {
/* 126 */       Trace.entry("com.ibm.mq.MQJavaLevel", "main(String [ ])", new Object[] { args });
/*     */     }
/*     */     
/* 129 */     parseCommandLine(args);
/* 130 */     printMQVERInfo();
/*     */     
/* 132 */     if (Trace.isOn) {
/* 133 */       Trace.exit("com.ibm.mq.MQJavaLevel", "main(String [ ])");
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
/*     */   protected static final void parseCommandLine(String[] args) {
/* 145 */     if (Trace.isOn) {
/* 146 */       Trace.entry("com.ibm.mq.MQJavaLevel", "parseCommandLine(String [ ])", new Object[] { args });
/*     */     }
/*     */     
/* 149 */     if (args.length == 0) {
/*     */       
/* 151 */       fields = 31;
/*     */     } else {
/*     */       
/* 154 */       int i = 0;
/* 155 */       boolean fDefined = false;
/*     */       
/* 157 */       while (i < args.length) {
/*     */         
/* 159 */         String thisArg = args[i];
/*     */         
/* 161 */         if (thisArg.startsWith("-")) {
/* 162 */           if (thisArg.charAt(1) == 'b') {
/*     */             
/* 164 */             withTitles = false;
/*     */           }
/* 166 */           else if (thisArg.charAt(1) == 'f') {
/* 167 */             fDefined = true;
/* 168 */             String num = null;
/* 169 */             if (thisArg.length() > 2) {
/*     */ 
/*     */               
/* 172 */               num = thisArg.substring(2);
/*     */             
/*     */             }
/*     */             else {
/*     */               
/* 177 */               num = args[i + 1];
/*     */             } 
/*     */ 
/*     */ 
/*     */             
/*     */             try {
/* 183 */               fields = Integer.parseInt(num);
/*     */             }
/* 185 */             catch (NumberFormatException nfe) {
/* 186 */               if (Trace.isOn) {
/* 187 */                 Trace.catchBlock("com.ibm.mq.MQJavaLevel", "parseCommandLine(String [ ])", nfe);
/*     */               }
/*     */ 
/*     */               
/* 191 */               fields = 31;
/*     */             }
/*     */           
/* 194 */           } else if (thisArg.charAt(1) == 'h') {
/*     */             
/* 196 */             hideLocation = true;
/*     */           } 
/*     */         }
/*     */         
/* 200 */         i++;
/*     */       } 
/*     */       
/* 203 */       if (!fDefined) {
/* 204 */         fields = 31;
/*     */       }
/*     */     } 
/* 207 */     if (Trace.isOn) {
/* 208 */       Trace.exit("com.ibm.mq.MQJavaLevel", "parseCommandLine(String [ ])");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static void printMQVERInfo() {
/* 219 */     if (Trace.isOn) {
/* 220 */       Trace.entry("com.ibm.mq.MQJavaLevel", "printMQVERInfo()");
/*     */     }
/*     */     
/* 223 */     for (int i = 0, j = 1; i < 5; i++, j *= 2) {
/* 224 */       if ((fields & j) != 0) {
/* 225 */         if (withTitles) {
/* 226 */           System.out.print(TITLES[i]);
/*     */         }
/* 228 */         String value = null;
/* 229 */         if (hideLocation && TITLES[i] == "Location:    ") {
/* 230 */           value = "built-in";
/*     */         } else {
/*     */           
/* 233 */           value = queryValueWithComponent(i);
/*     */         } 
/* 235 */         System.out.println(value);
/*     */       } 
/*     */     } 
/* 238 */     if (Trace.isOn) {
/* 239 */       Trace.exit("com.ibm.mq.MQJavaLevel", "printMQVERInfo()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void traceBuildInfo() {
/* 250 */     if (Trace.isOn) {
/* 251 */       Trace.entry("com.ibm.mq.MQJavaLevel", "traceBuildInfo()");
/*     */     }
/*     */     
/* 254 */     if (Trace.isOn) {
/* 255 */       Trace.exit("com.ibm.mq.MQJavaLevel", "traceBuildInfo()");
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
/*     */   protected static String queryValue(int id) {
/* 269 */     if (Trace.isOn) {
/* 270 */       Trace.entry("com.ibm.mq.MQJavaLevel", "queryValue(int)", new Object[] { Integer.valueOf(id) });
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
/* 281 */     Package thisPackage = Package.getPackage("com.ibm.mq");
/*     */ 
/*     */     
/* 284 */     if (thisPackage != null) {
/*     */       
/* 286 */       String traceRet1 = queryValue(id, thisPackage);
/* 287 */       if (Trace.isOn) {
/* 288 */         Trace.exit("com.ibm.mq.MQJavaLevel", "queryValue(int)", traceRet1, 1);
/*     */       }
/* 290 */       return traceRet1;
/*     */     } 
/*     */     
/* 293 */     if (Trace.isOn) {
/* 294 */       Trace.exit("com.ibm.mq.MQJavaLevel", "queryValue(int)", "package error", 2);
/*     */     }
/* 296 */     return "package error";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static final String queryValue(int id, Package thisPackage) {
/* 307 */     if (Trace.isOn) {
/* 308 */       Trace.entry("com.ibm.mq.MQJavaLevel", "queryValue(int,Package)", new Object[] {
/* 309 */             Integer.valueOf(id), thisPackage
/*     */           });
/*     */     }
/* 312 */     String buffer = null;
/*     */     
/* 314 */     if (thisPackage != null) {
/*     */       
/* 316 */       String versionInfo = thisPackage.getImplementationVersion();
/*     */       
/* 318 */       if (versionInfo == null) {
/* 319 */         buffer = "Unable to retrieve ImplementationVersion data";
/*     */       }
/*     */       else {
/*     */         
/* 323 */         if (!valuesDefined) {
/*     */ 
/*     */           
/*     */           try {
/*     */ 
/*     */ 
/*     */             
/* 330 */             int firstDash = versionInfo.indexOf(" - ");
/* 331 */             String fullVersion = versionInfo.substring(0, firstDash);
/* 332 */             String cmvcLevel = versionInfo.substring(firstDash + 3);
/*     */ 
/*     */             
/* 335 */             values[0] = thisPackage.getImplementationTitle();
/* 336 */             values[1] = fullVersion;
/* 337 */             values[2] = cmvcLevel;
/* 338 */             values[3] = "Production";
/*     */           }
/* 340 */           catch (NoSuchElementException nsee) {
/* 341 */             if (Trace.isOn) {
/* 342 */               Trace.catchBlock("com.ibm.mq.MQJavaLevel", "queryValue(int,Package)", nsee);
/*     */             }
/*     */ 
/*     */             
/* 346 */             for (int j = 0; j < TITLES.length; j++) {
/* 347 */               if (values[j] == null) {
/* 348 */                 values[j] = "UNKNOWN";
/*     */               }
/*     */             } 
/*     */           } 
/*     */           
/* 353 */           for (int i = 0; i < values.length; i++) {
/* 354 */             if (values[i] != null) {
/* 355 */               values[i] = values[i].trim();
/*     */             }
/*     */           } 
/*     */           
/* 359 */           valuesDefined = true;
/*     */         } 
/*     */         
/* 362 */         if (Trace.isOn) {
/* 363 */           Trace.exit("com.ibm.mq.MQJavaLevel", "queryValue(int,Package)", values[id], 1);
/*     */         }
/* 365 */         return values[id];
/*     */       } 
/*     */     } else {
/*     */       
/* 369 */       buffer = "missing package data";
/*     */     } 
/*     */     
/* 372 */     if (Trace.isOn) {
/* 373 */       Trace.exit("com.ibm.mq.MQJavaLevel", "queryValue(int,Package)", buffer, 2);
/*     */     }
/* 375 */     return buffer;
/*     */   }
/*     */ 
/*     */   
/* 379 */   private static Component javaComponent = null;
/*     */   
/*     */   protected static String queryValueWithComponent(int id) {
/*     */     String traceRet1;
/*     */     String traceRet2;
/*     */     Map<String, String> info;
/*     */     String traceRet4;
/* 386 */     if (Trace.isOn) {
/* 387 */       Trace.entry("com.ibm.mq.MQJavaLevel", "queryValueWithComponent(int)", new Object[] {
/* 388 */             Integer.valueOf(id)
/*     */           });
/*     */     }
/* 391 */     if (javaComponent == null) {
/* 392 */       ComponentManager compMgr = ComponentManager.getInstance();
/* 393 */       HashMap<Object, Object> filter = new HashMap<>();
/* 394 */       filter.put("XMSC_PROVIDER_NAME", "com.ibm.mq");
/*     */       try {
/* 396 */         javaComponent = compMgr.getComponent("INFRA", filter);
/*     */       }
/* 398 */       catch (CSIException e) {
/* 399 */         if (Trace.isOn) {
/* 400 */           Trace.catchBlock("com.ibm.mq.MQJavaLevel", "queryValueWithComponent(int)", (Throwable)e);
/*     */         }
/* 402 */         if (Trace.isOn) {
/* 403 */           Trace.exit("com.ibm.mq.MQJavaLevel", "queryValueWithComponent(int)", "missing component data", 1);
/*     */         }
/* 405 */         return "missing component data";
/*     */       } 
/*     */     } 
/*     */     
/* 409 */     switch (id) {
/*     */       case 0:
/* 411 */         traceRet1 = javaComponent.getTitle();
/* 412 */         if (Trace.isOn) {
/* 413 */           Trace.exit("com.ibm.mq.MQJavaLevel", "queryValueWithComponent(int)", traceRet1, 2);
/*     */         }
/*     */         
/* 416 */         return traceRet1;
/*     */       
/*     */       case 1:
/* 419 */         traceRet2 = javaComponent.getVersionString();
/* 420 */         if (Trace.isOn) {
/* 421 */           Trace.exit("com.ibm.mq.MQJavaLevel", "queryValueWithComponent(int)", traceRet2, 3);
/*     */         }
/*     */         
/* 424 */         return traceRet2;
/*     */       
/*     */       case 2:
/* 427 */         info = javaComponent.getImplementationInfo();
/* 428 */         if (info != null) {
/* 429 */           String cmvcLevel = info.get("CMVC");
/* 430 */           String mqjbndLevel = info.get("mqjbnd level");
/* 431 */           String basedOnLevel = info.get("BasedOn");
/* 432 */           String tracks = info.get("APARs");
/* 433 */           String title = info.get("title");
/*     */           
/* 435 */           StringBuffer returnStr = new StringBuffer();
/* 436 */           if (cmvcLevel != null) {
/* 437 */             returnStr.append(cmvcLevel);
/*     */           }
/*     */           
/* 440 */           if (mqjbndLevel != null) {
/* 441 */             returnStr.append(" mqjbnd=").append(mqjbndLevel);
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 446 */           boolean extended = false;
/*     */           
/* 448 */           if (extended && 
/* 449 */             basedOnLevel != null && tracks != null) {
/* 450 */             if (title != null) {
/* 451 */               returnStr.append(" ").append(title);
/*     */             }
/* 453 */             tracks = tracks.replace(" ", ",");
/* 454 */             returnStr.append(" [= " + basedOnLevel + " + " + tracks + "]");
/*     */           } 
/*     */ 
/*     */           
/* 458 */           String traceRet3 = returnStr.toString();
/* 459 */           if (Trace.isOn) {
/* 460 */             Trace.exit("com.ibm.mq.MQJavaLevel", "queryValueWithComponent(int)", traceRet3, 4);
/*     */           }
/* 462 */           return traceRet3;
/*     */         } 
/*     */         
/* 465 */         if (Trace.isOn) {
/* 466 */           Trace.exit("com.ibm.mq.MQJavaLevel", "queryValueWithComponent(int)", "missing build level", 5);
/*     */         }
/* 468 */         return "missing build level";
/*     */       
/*     */       case 3:
/* 471 */         if (Trace.isOn) {
/* 472 */           Trace.exit("com.ibm.mq.MQJavaLevel", "queryValueWithComponent(int)", "Production", 6);
/*     */         }
/* 474 */         return "Production";
/*     */       
/*     */       case 4:
/* 477 */         traceRet4 = javaComponent.getJarLocation();
/* 478 */         if (Trace.isOn) {
/* 479 */           Trace.exit("com.ibm.mq.MQJavaLevel", "queryValueWithComponent(int)", traceRet4, 7);
/*     */         }
/*     */         
/* 482 */         return traceRet4;
/*     */     } 
/*     */     
/* 485 */     if (Trace.isOn) {
/* 486 */       Trace.exit("com.ibm.mq.MQJavaLevel", "queryValueWithComponent(int)", "missing package data", 8);
/*     */     }
/* 488 */     return "missing package data";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQJavaLevel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */