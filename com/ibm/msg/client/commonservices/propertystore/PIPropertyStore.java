/*     */ package com.ibm.msg.client.commonservices.propertystore;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*     */ import com.ibm.msg.client.commonservices.provider.propertystore.CSPPropertyStore;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.security.AccessControlException;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ import java.util.Collections;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PIPropertyStore
/*     */   implements CSPPropertyStore
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/propertystore/PIPropertyStore.java";
/*     */   
/*     */   static {
/*  47 */     if (Trace.isOn) {
/*  48 */       Trace.data("com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/propertystore/PIPropertyStore.java");
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
/*  60 */   private static final Map<String, Object> props = Collections.synchronizedMap(new HashMap<>());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*     */     try {
/*  70 */       if (Trace.isOn) {
/*  71 */         Trace.entry("com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "static()");
/*     */       }
/*     */ 
/*     */       
/*  75 */       Object sysprops = AccessController.doPrivileged(new PrivilegedAction()
/*     */           {
/*     */             public Object run()
/*     */             {
/*  79 */               if (Trace.isOn) {
/*  80 */                 Trace.entry(this, "com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "run()");
/*     */               }
/*     */               try {
/*  83 */                 Object traceRet1 = System.getProperties();
/*  84 */                 if (Trace.isOn) {
/*  85 */                   Trace.exit(this, "com.ibm.msg.client.commonservices.propertystore.null", "run()", traceRet1, 1);
/*     */                 }
/*  87 */                 return traceRet1;
/*     */               }
/*  89 */               catch (AccessControlException ace) {
/*  90 */                 if (Trace.isOn) {
/*  91 */                   Trace.catchBlock(this, "com.ibm.msg.client.commonservices.propertystore.null", "run()", ace);
/*     */                 }
/*  93 */                 if (Trace.isOn) {
/*  94 */                   Trace.exit(this, "com.ibm.msg.client.commonservices.propertystore.null", "run()", ace, 2);
/*     */                 }
/*  96 */                 return ace;
/*     */               } 
/*     */             }
/*     */           });
/*     */ 
/*     */       
/* 102 */       if (sysprops instanceof Properties) {
/* 103 */         Enumeration<?> keys = ((Properties)sysprops).keys();
/* 104 */         while (keys.hasMoreElements()) {
/* 105 */           String key = (String)keys.nextElement();
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 110 */           if (key != null && !props.containsKey(key)) {
/* 111 */             String value = ((Properties)sysprops).getProperty(key);
/* 112 */             props.put(key, value);
/*     */           }
/*     */         
/*     */         }
/*     */       
/* 117 */       } else if (!(sysprops instanceof AccessControlException)) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 123 */         HashMap<String, Object> data = new HashMap<>();
/* 124 */         data.put("property", sysprops);
/* 125 */         Trace.ffst("PIPropertyStore", "static initializer", "XC005001", data, null);
/*     */       } 
/*     */ 
/*     */       
/* 129 */       if (Trace.isOn) {
/* 130 */         Trace.exit("com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "static()");
/*     */       }
/*     */     }
/* 133 */     catch (Throwable t) {
/* 134 */       System.err.println("Serious problem encountered in <clinit> for class PIPropertyStore - Contact IBM Service");
/* 135 */       t.printStackTrace(System.err);
/*     */ 
/*     */       
/* 138 */       if (t instanceof Error) {
/* 139 */         throw (Error)t;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStringProperty(String name) {
/* 151 */     if (Trace.isOn) {
/* 152 */       Trace.entry(this, "com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "getStringProperty(String)", new Object[] { name });
/*     */     }
/*     */     
/* 155 */     String retVal = (String)props.get(name);
/* 156 */     if (Trace.isOn) {
/* 157 */       Trace.exit(this, "com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "getStringProperty(String)", retVal);
/*     */     }
/*     */     
/* 160 */     return retVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getLongProperty(String name) {
/* 171 */     if (Trace.isOn) {
/* 172 */       Trace.entry(this, "com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "getLongProperty(String)", new Object[] { name });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 178 */     long retVal = 0L;
/*     */     try {
/* 180 */       retVal = ((Long)props.get(name)).longValue();
/*     */     }
/* 182 */     catch (ClassCastException e) {
/* 183 */       if (Trace.isOn) {
/* 184 */         Trace.catchBlock(this, "com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "getLongProperty(String)", e);
/*     */       }
/*     */       
/* 187 */       HashMap<String, Object> data = new HashMap<>();
/* 188 */       data.put("property", name);
/* 189 */       data.put("exception", e);
/* 190 */       Trace.ffst("PIPropertyStore", "getLongProperty", "XC005002", data, null);
/*     */     } 
/* 192 */     if (Trace.isOn) {
/* 193 */       Trace.exit(this, "com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "getLongProperty(String)", 
/* 194 */           Long.valueOf(retVal));
/*     */     }
/* 196 */     return retVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getBooleanProperty(String name) {
/* 207 */     if (Trace.isOn) {
/* 208 */       Trace.entry(this, "com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "getBooleanProperty(String)", new Object[] { name });
/*     */     }
/*     */     
/* 211 */     boolean retVal = false;
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 216 */       retVal = ((Boolean)props.get(name)).booleanValue();
/*     */     }
/* 218 */     catch (ClassCastException e) {
/* 219 */       if (Trace.isOn) {
/* 220 */         Trace.catchBlock(this, "com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "getBooleanProperty(String)", e);
/*     */       }
/*     */       
/* 223 */       HashMap<String, Object> data = new HashMap<>();
/* 224 */       data.put("property", name);
/* 225 */       data.put("exception", e);
/* 226 */       Trace.ffst("PIPropertyStore", "getBooleanProperty", "XC005003", data, null);
/*     */     } 
/* 228 */     if (Trace.isOn) {
/* 229 */       Trace.exit(this, "com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "getBooleanProperty(String)", 
/* 230 */           Boolean.valueOf(retVal));
/*     */     }
/* 232 */     return retVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getObjectProperty(String name) {
/* 243 */     if (Trace.isOn) {
/* 244 */       Trace.entry(this, "com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "getObjectProperty(String)", new Object[] { name });
/*     */     }
/*     */     
/* 247 */     Object traceRet1 = props.get(name);
/*     */     
/* 249 */     if (Trace.isOn) {
/* 250 */       Trace.exit(this, "com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "getObjectProperty(String)", traceRet1);
/*     */     }
/*     */     
/* 253 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addPropertiesFile(String filename, String namespace, boolean optional) throws PropertiesFileException {
/* 263 */     if (Trace.isOn) {
/* 264 */       Trace.entry(this, "com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "addPropertiesFile(String,String,boolean)", new Object[] { filename, namespace, 
/*     */             
/* 266 */             Boolean.valueOf(optional) });
/*     */     }
/*     */     
/* 269 */     String msg = NLSServices.getMessage("JMSCS0002");
/* 270 */     PropertiesFileException traceRet1 = new PropertiesFileException(msg, null);
/* 271 */     if (Trace.isOn) {
/* 272 */       Trace.throwing(this, "com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "addPropertiesFile(String,String,boolean)", (Throwable)traceRet1);
/*     */     }
/*     */     
/* 275 */     throw traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(String name, String value) {
/* 286 */     if (Trace.isOn) {
/* 287 */       Trace.entry(this, "com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "set(String,String)", new Object[] { name, value });
/*     */     }
/*     */ 
/*     */     
/* 291 */     if (name == null) {
/*     */       
/* 293 */       NullPointerException e = new NullPointerException("Null name");
/* 294 */       if (Trace.isOn) {
/* 295 */         Trace.throwing(this, "com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "set(String,String)", e);
/*     */       }
/*     */       
/* 298 */       throw e;
/*     */     } 
/*     */     
/* 301 */     props.put(name, value);
/* 302 */     if (Trace.isOn) {
/* 303 */       Trace.exit(this, "com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "set(String,String)");
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
/*     */   public void set(String name, Object value) {
/* 315 */     if (Trace.isOn) {
/* 316 */       Trace.entry(this, "com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "set(String,Object)", new Object[] { name, value });
/*     */     }
/*     */ 
/*     */     
/* 320 */     if (name == null) {
/*     */       
/* 322 */       NullPointerException e = new NullPointerException("Null name");
/* 323 */       if (Trace.isOn) {
/* 324 */         Trace.throwing(this, "com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "set(String,Object)", e);
/*     */       }
/*     */       
/* 327 */       throw e;
/*     */     } 
/*     */     
/* 330 */     props.put(name, value);
/* 331 */     if (Trace.isOn) {
/* 332 */       Trace.exit(this, "com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "set(String,Object)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HashMap<String, Object> getAll() {
/* 343 */     if (Trace.isOn) {
/* 344 */       Trace.entry(this, "com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "getAll()");
/*     */     }
/*     */ 
/*     */     
/* 348 */     HashMap<String, Object> ret = new HashMap<>();
/* 349 */     Set<Map.Entry<String, Object>> entries = props.entrySet();
/*     */     
/* 351 */     for (Map.Entry<String, Object> entry : entries) {
/* 352 */       ret.put(entry.getKey(), entry.getValue());
/*     */     }
/*     */     
/* 355 */     if (Trace.isOn) {
/* 356 */       Trace.exit(this, "com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "getAll()", ret);
/*     */     }
/*     */     
/* 359 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void register(String name, String defaultValue) {
/* 368 */     if (Trace.isOn) {
/* 369 */       Trace.entry(this, "com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "register(String,String)", new Object[] { name, defaultValue });
/*     */     }
/*     */ 
/*     */     
/* 373 */     if (name == null) {
/*     */       
/* 375 */       NullPointerException e = new NullPointerException("Null name");
/* 376 */       if (Trace.isOn) {
/* 377 */         Trace.throwing(this, "com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "register(String,String)", e);
/*     */       }
/*     */       
/* 380 */       throw e;
/*     */     } 
/*     */     
/* 383 */     props.put(name, defaultValue);
/*     */     
/* 385 */     if (Trace.isOn) {
/* 386 */       Trace.exit(this, "com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "register(String,String)");
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
/*     */   public void register(String name, boolean defaultValue) {
/* 398 */     if (Trace.isOn) {
/* 399 */       Trace.entry(this, "com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "register(String,boolean)", new Object[] { name, 
/* 400 */             Boolean.valueOf(defaultValue) });
/*     */     }
/*     */     
/* 403 */     if (name == null) {
/*     */       
/* 405 */       NullPointerException e = new NullPointerException("Null name");
/* 406 */       if (Trace.isOn) {
/* 407 */         Trace.throwing(this, "com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "register(String,boolean)", e);
/*     */       }
/*     */       
/* 410 */       throw e;
/*     */     } 
/*     */     
/* 413 */     props.put(name, Boolean.valueOf(defaultValue));
/*     */     
/* 415 */     if (Trace.isOn) {
/* 416 */       Trace.exit(this, "com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "register(String,boolean)");
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
/*     */   public void register(String name, Object defaultValue) {
/* 428 */     if (Trace.isOn) {
/* 429 */       Trace.entry(this, "com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "register(String,Object)", new Object[] { name, defaultValue });
/*     */     }
/*     */ 
/*     */     
/* 433 */     if (name == null) {
/*     */       
/* 435 */       NullPointerException e = new NullPointerException("Null name");
/* 436 */       if (Trace.isOn) {
/* 437 */         Trace.throwing(this, "com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "register(String,Object)", e);
/*     */       }
/*     */       
/* 440 */       throw e;
/*     */     } 
/*     */     
/* 443 */     props.put(name, defaultValue);
/* 444 */     if (Trace.isOn) {
/* 445 */       Trace.exit(this, "com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "register(String,Object)");
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
/*     */   public void register(String name, long defaultValue, Long minValid, Long maxValid) {
/* 458 */     if (Trace.isOn) {
/* 459 */       Trace.entry(this, "com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "register(String,long,Long,Long)", new Object[] { name, 
/* 460 */             Long.valueOf(defaultValue), minValid, maxValid });
/*     */     }
/*     */ 
/*     */     
/* 464 */     if (name == null) {
/*     */       
/* 466 */       NullPointerException e = new NullPointerException("Null name");
/* 467 */       if (Trace.isOn) {
/* 468 */         Trace.throwing(this, "com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "register(String,long,Long,Long)", e);
/*     */       }
/*     */       
/* 471 */       throw e;
/*     */     } 
/*     */     
/* 474 */     props.put(name, Long.valueOf(defaultValue));
/*     */     
/* 476 */     if (Trace.isOn) {
/* 477 */       Trace.exit(this, "com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "register(String,long,Long,Long)");
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
/*     */   public boolean wasOverridden(String name, StringBuffer unparsableUserValue) {
/* 492 */     if (Trace.isOn) {
/* 493 */       Trace.entry(this, "com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "wasOverridden(String,StringBuffer)", new Object[] { name, unparsableUserValue });
/*     */     }
/*     */ 
/*     */     
/* 497 */     if (Trace.isOn) {
/* 498 */       Trace.exit(this, "com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "wasOverridden(String,StringBuffer)", 
/* 499 */           Boolean.valueOf(false));
/*     */     }
/* 501 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void register(String name, String defaultValue, boolean controlled) {
/* 511 */     if (Trace.isOn) {
/* 512 */       Trace.entry(this, "com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "register(String,String,boolean)", new Object[] { name, defaultValue, 
/*     */             
/* 514 */             Boolean.valueOf(controlled) });
/*     */     }
/* 516 */     register(name, defaultValue);
/*     */     
/* 518 */     if (Trace.isOn) {
/* 519 */       Trace.exit(this, "com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "register(String,String,boolean)");
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
/*     */   public void register(String name, boolean defaultValue, boolean controlled) {
/* 531 */     if (Trace.isOn) {
/* 532 */       Trace.entry(this, "com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "register(String,boolean,boolean)", new Object[] { name, 
/* 533 */             Boolean.valueOf(defaultValue), 
/* 534 */             Boolean.valueOf(controlled) });
/*     */     }
/* 536 */     register(name, defaultValue);
/* 537 */     if (Trace.isOn) {
/* 538 */       Trace.exit(this, "com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "register(String,boolean,boolean)");
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
/*     */   public void register(String name, long defaultValue, Long minValid, Long maxValid, boolean controlled) {
/* 550 */     if (Trace.isOn) {
/* 551 */       Trace.entry(this, "com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "register(String,long,Long,Long,boolean)", new Object[] { name, 
/* 552 */             Long.valueOf(defaultValue), minValid, maxValid, 
/* 553 */             Boolean.valueOf(controlled) });
/*     */     }
/* 555 */     register(name, defaultValue, minValid, maxValid);
/* 556 */     if (Trace.isOn) {
/* 557 */       Trace.exit(this, "com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "register(String,long,Long,Long,boolean)");
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
/*     */   public void register(String name, Object defaultValue, boolean controlled) {
/* 569 */     if (Trace.isOn) {
/* 570 */       Trace.entry(this, "com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "register(String,Object,boolean)", new Object[] { name, defaultValue, 
/*     */             
/* 572 */             Boolean.valueOf(controlled) });
/*     */     }
/* 574 */     register(name, defaultValue);
/* 575 */     if (Trace.isOn) {
/* 576 */       Trace.exit(this, "com.ibm.msg.client.commonservices.propertystore.PIPropertyStore", "register(String,Object,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CSPPropertyStore.PropertySource howHasPropertyBeenSet(String name) throws PropertiesException {
/* 585 */     return CSPPropertyStore.PropertySource.SYS_PROP;
/*     */   }
/*     */ 
/*     */   
/*     */   public void set(String name, Object value, CSPPropertyStore.PropertySource source) {
/* 590 */     set(name, value);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\propertystore\PIPropertyStore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */