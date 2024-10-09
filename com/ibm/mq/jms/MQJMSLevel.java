/*     */ package com.ibm.mq.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.services.Version;
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQJMSLevel
/*     */ {
/*     */   private static final int DEFAULT_FIELDS = 31;
/*     */   
/*     */   static {
/*  55 */     if (Trace.isOn) {
/*  56 */       Trace.data("com.ibm.mq.jms.MQJMSLevel", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQJMSLevel.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   static int fields = 31;
/*     */   
/*     */   private static final String NAME_TITLE = "Name:        ";
/*     */   private static final String VERSION_TITLE = "Version:     ";
/*     */   private static final String BUILDTYPE_TITLE = "BuildType:   ";
/*     */   private static final String LEVEL_TITLE = "Level:       ";
/*     */   private static final String LOCATION_TITLE = "Location:    ";
/*     */   private static final String LOCATION_BUILTIN = "built-in";
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQJMSLevel.java";
/*  72 */   static final String[] TITLES = new String[] { "Name:        ", "Version:     ", "Level:       ", "BuildType:   ", "Location:    " };
/*     */   
/*     */   static boolean withTitles = true;
/*     */   
/*     */   static boolean quiet = false;
/*     */   
/*     */   static boolean extended = true;
/*     */   
/*     */   protected static boolean hideLocation = false;
/*     */ 
/*     */   
/*     */   static {
/*  84 */     if (Trace.isOn) {
/*  85 */       Trace.entry("com.ibm.mq.jms.MQJMSLevel", "static()");
/*     */     }
/*  87 */     NLSServices.addCatalogue("com.ibm.mq.jms.resources.JMSMQ_MessageResourceBundle", "JMSMQ");
/*  88 */     if (Trace.isOn) {
/*  89 */       Trace.exit("com.ibm.mq.jms.MQJMSLevel", "static()");
/*     */     }
/*     */   }
/*     */   
/*     */   protected MQJMSLevel() {
/*  94 */     if (Trace.isOn) {
/*  95 */       Trace.entry(this, "com.ibm.mq.jms.MQJMSLevel", "<init>()");
/*     */     }
/*     */     
/*  98 */     if (Trace.isOn) {
/*  99 */       Trace.exit(this, "com.ibm.mq.jms.MQJMSLevel", "<init>()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 109 */     if (Trace.isOn) {
/* 110 */       Trace.entry("com.ibm.mq.jms.MQJMSLevel", "main(String [ ])", new Object[] { args });
/*     */     }
/*     */ 
/*     */     
/* 114 */     parseCommandLine(args);
/* 115 */     printMQVERInfo();
/*     */     
/* 117 */     if (Trace.isOn) {
/* 118 */       Trace.exit("com.ibm.mq.jms.MQJMSLevel", "main(String [ ])");
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
/* 130 */     if (Trace.isOn) {
/* 131 */       Trace.entry("com.ibm.mq.jms.MQJMSLevel", "parseCommandLine(String [ ])", new Object[] { args });
/*     */     }
/*     */ 
/*     */     
/* 135 */     if (args.length == 0) {
/*     */       
/* 137 */       fields = 31;
/*     */     } else {
/*     */       
/* 140 */       int i = 0;
/* 141 */       boolean fDefined = false;
/*     */       
/* 143 */       while (i < args.length) {
/*     */         
/* 145 */         String thisArg = args[i];
/*     */         
/* 147 */         if (thisArg.startsWith("-")) {
/* 148 */           if (thisArg.charAt(1) == 'b') {
/*     */             
/* 150 */             withTitles = false;
/*     */           }
/* 152 */           else if (thisArg.charAt(1) == 'f') {
/* 153 */             fDefined = true;
/* 154 */             String num = null;
/* 155 */             if (thisArg.length() > 2) {
/*     */ 
/*     */               
/* 158 */               num = thisArg.substring(2);
/*     */             
/*     */             }
/*     */             else {
/*     */               
/* 163 */               num = args[i + 1];
/*     */             } 
/*     */ 
/*     */             
/*     */             try {
/* 168 */               fields = Integer.parseInt(num);
/*     */             }
/* 170 */             catch (NumberFormatException nfe) {
/* 171 */               if (Trace.isOn) {
/* 172 */                 Trace.catchBlock("com.ibm.mq.jms.MQJMSLevel", "parseCommandLine(String [ ])", nfe);
/*     */               }
/*     */ 
/*     */ 
/*     */               
/* 177 */               fields = 31;
/*     */             }
/*     */           
/* 180 */           } else if (thisArg.charAt(1) == 'q') {
/* 181 */             quiet = true;
/*     */           }
/* 183 */           else if (thisArg.charAt(1) == 'x') {
/* 184 */             extended = true;
/*     */           }
/* 186 */           else if (thisArg.charAt(1) == 'h') {
/*     */             
/* 188 */             hideLocation = true;
/*     */           } 
/*     */         }
/*     */         
/* 192 */         i++;
/*     */       } 
/*     */       
/* 195 */       if (!fDefined) {
/* 196 */         fields = 31;
/*     */       }
/*     */     } 
/* 199 */     if (Trace.isOn) {
/* 200 */       Trace.exit("com.ibm.mq.jms.MQJMSLevel", "parseCommandLine(String [ ])");
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
/* 211 */     if (Trace.isOn) {
/* 212 */       Trace.entry("com.ibm.mq.jms.MQJMSLevel", "printMQVERInfo()");
/*     */     }
/*     */     
/* 215 */     Version.Component[] components = Version.getComponents();
/*     */     
/* 217 */     if (components != null && components.length > 0) {
/*     */       
/* 219 */       Arrays.sort(components, new Comparator<Version.Component>()
/*     */           {
/*     */             public int compare(Version.Component c1, Version.Component c2) {
/* 222 */               return c1.getName().compareTo(c2.getName());
/*     */             }
/*     */           });
/*     */ 
/*     */       
/* 227 */       for (int c = 0; c < components.length; c++) {
/*     */         
/* 229 */         String name = components[c].getName();
/* 230 */         if (name.equals("com.ibm.msg.client.commonservices.j2se") || name
/* 231 */           .equals("com.ibm.mq.jms") || name
/* 232 */           .equals("com.ibm.msg.client.wmq") || name
/* 233 */           .equals("com.ibm.msg.client.jms") || name
/* 234 */           .equals("com.ibm.mq.jakarta.jms") || name
/* 235 */           .equals("com.ibm.msg.client.jakarta.wmq") || name
/* 236 */           .equals("com.ibm.msg.client.jakarta.jms")) {
/*     */           int k;
/* 238 */           for (int i = 0; i < 5; i++, k *= 2) {
/* 239 */             if ((fields & k) != 0) {
/* 240 */               if (withTitles) {
/* 241 */                 System.out.print(TITLES[i]);
/*     */               }
/* 243 */               String value = null;
/* 244 */               if (hideLocation && TITLES[i] == "Location:    ") {
/* 245 */                 value = "built-in";
/*     */               } else {
/*     */                 
/* 248 */                 value = queryValue(i, components[c]);
/*     */               } 
/* 250 */               System.out.println(value);
/*     */             } 
/*     */           } 
/* 253 */           System.out.println("");
/*     */         } 
/*     */       } 
/*     */     } else {
/*     */       
/* 258 */       System.out.println(NLSServices.getMessage("JMSMQ1115"));
/*     */     } 
/*     */     
/* 261 */     if (Trace.isOn)
/* 262 */       Trace.exit("com.ibm.mq.jms.MQJMSLevel", "printMQVERInfo()"); 
/*     */   }
/*     */   protected static String queryValue(int id, Version.Component component) {
/*     */     String traceRet1;
/*     */     String traceRet2;
/*     */     Map<String, String> info;
/*     */     StringBuilder builder;
/*     */     String traceRet4;
/*     */     Map<String, String> map1;
/*     */     String traceRet5;
/* 272 */     if (Trace.isOn)
/* 273 */       Trace.entry("com.ibm.mq.jms.MQJMSLevel", "queryValue(int,Component)", new Object[] {
/* 274 */             Integer.valueOf(id), component
/*     */           }); 
/* 276 */     switch (id) {
/*     */       case 0:
/* 278 */         traceRet1 = component.getTitle();
/* 279 */         if (Trace.isOn) {
/* 280 */           Trace.exit("com.ibm.mq.jms.MQJMSLevel", "queryValue(int,Component)", traceRet1, 1);
/*     */         }
/*     */         
/* 283 */         return traceRet1;
/*     */       
/*     */       case 1:
/* 286 */         traceRet2 = component.getVersionString();
/* 287 */         if (Trace.isOn) {
/* 288 */           Trace.exit("com.ibm.mq.jms.MQJMSLevel", "queryValue(int,Component)", traceRet2, 2);
/*     */         }
/*     */         
/* 291 */         return traceRet2;
/*     */       
/*     */       case 2:
/* 294 */         info = component.getImplementationInfo(quiet);
/* 295 */         if (info != null) {
/* 296 */           String cmvcLevel = info.get("CMVC");
/* 297 */           String mqjbndLevel = info.get("mqjbnd level");
/* 298 */           String basedOnLevel = info.get("BasedOn");
/* 299 */           String tracks = info.get("APARs");
/* 300 */           String title = info.get("title");
/* 301 */           String formFactor = info.get("FormFactor");
/*     */           
/* 303 */           StringBuffer returnStr = new StringBuffer();
/* 304 */           if (formFactor != null) {
/* 305 */             returnStr.append(formFactor).append(" ");
/*     */           }
/*     */           
/* 308 */           if (cmvcLevel != null) {
/* 309 */             returnStr.append(cmvcLevel);
/*     */           }
/*     */           
/* 312 */           if (mqjbndLevel != null && 
/* 313 */             !mqjbndLevel.contains("RC=2495"))
/*     */           {
/*     */             
/* 316 */             returnStr.append(" mqjbnd=").append(mqjbndLevel);
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 321 */           if (extended && 
/* 322 */             basedOnLevel != null && tracks != null) {
/* 323 */             if (title != null) {
/* 324 */               returnStr.append(" ").append(title);
/*     */             }
/* 326 */             tracks = tracks.replace(" ", ",");
/* 327 */             returnStr.append(" [= " + basedOnLevel + " + " + tracks + "]");
/*     */           } 
/*     */ 
/*     */           
/* 331 */           String traceRet3 = returnStr.toString();
/* 332 */           if (Trace.isOn) {
/* 333 */             Trace.exit("com.ibm.mq.jms.MQJMSLevel", "queryValue(int,Component)", traceRet3, 3);
/*     */           }
/*     */           
/* 336 */           return traceRet3;
/*     */         } 
/*     */         
/* 339 */         if (Trace.isOn) {
/* 340 */           Trace.exit("com.ibm.mq.jms.MQJMSLevel", "queryValue(int,Component)", "missing build level", 4);
/*     */         }
/*     */         
/* 343 */         return "missing build level";
/*     */ 
/*     */ 
/*     */       
/*     */       case 3:
/* 348 */         builder = new StringBuilder("Production");
/* 349 */         map1 = component.getImplementationInfo(quiet);
/* 350 */         if (map1 != null) {
/* 351 */           String buildwith = map1.get("BuiltWith");
/* 352 */           if (buildwith != null) {
/* 353 */             builder.append(" [Built on ").append(buildwith).append("]");
/*     */           }
/*     */         } 
/* 356 */         traceRet5 = builder.toString();
/* 357 */         if (Trace.isOn) {
/* 358 */           Trace.exit("com.ibm.mq.jms.MQJMSLevel", "queryValue(int,Component)", traceRet5, 5);
/*     */         }
/*     */         
/* 361 */         return traceRet5;
/*     */       
/*     */       case 4:
/* 364 */         traceRet4 = component.getLocation();
/* 365 */         if (Trace.isOn) {
/* 366 */           Trace.exit("com.ibm.mq.jms.MQJMSLevel", "queryValue(int,Component)", traceRet4, 6);
/*     */         }
/*     */         
/* 369 */         return traceRet4;
/*     */     } 
/*     */     
/* 372 */     if (Trace.isOn) {
/* 373 */       Trace.exit("com.ibm.mq.jms.MQJMSLevel", "queryValue(int,Component)", "missing package data", 7);
/*     */     }
/*     */     
/* 376 */     return "missing package data";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void traceBuildInfo() {
/* 386 */     if (Trace.isOn) {
/* 387 */       Trace.entry("com.ibm.mq.jms.MQJMSLevel", "traceBuildInfo()");
/*     */     }
/*     */     
/* 390 */     if (Trace.isOn) {
/*     */       
/* 392 */       Version.Component[] components = Version.getComponents();
/* 393 */       if (components != null) {
/* 394 */         for (int c = 0; c < components.length; c++) {
/* 395 */           String title = queryValue(0, components[c]);
/* 396 */           String version = queryValue(1, components[c]);
/* 397 */           String cmvcLevel = queryValue(2, components[c]);
/* 398 */           String buildType = queryValue(3, components[c]);
/* 399 */           String location = queryValue(4, components[c]);
/*     */           
/* 401 */           Trace.traceData("*** BuildInfo ***", title + " (" + version + ")", null);
/* 402 */           Trace.traceData("*** BuildInfo ***", cmvcLevel + " (" + buildType + ")", null);
/* 403 */           Trace.traceData("*** BuildInfo ***", location, null);
/*     */         } 
/*     */       }
/*     */     } 
/* 407 */     if (Trace.isOn)
/* 408 */       Trace.exit("com.ibm.mq.jms.MQJMSLevel", "traceBuildInfo()"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQJMSLevel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */