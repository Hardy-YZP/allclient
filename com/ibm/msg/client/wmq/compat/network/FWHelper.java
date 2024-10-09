/*     */ package com.ibm.msg.client.wmq.compat.network;
/*     */ 
/*     */ import com.ibm.mq.MQException;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.wmq.compat.base.internal.MQChannelDefinition;
/*     */ import com.ibm.msg.client.wmq.compat.base.internal.MQManagedConnectionJ11;
/*     */ import com.ibm.msg.client.wmq.compat.base.internal.MQSESSION;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.net.InetAddress;
/*     */ import java.util.Hashtable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class FWHelper
/*     */ {
/*     */   static final String CLSNAME = "FWHelper";
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/network/FWHelper.java";
/*     */   
/*     */   static {
/*  59 */     if (Trace.isOn) {
/*  60 */       Trace.data("com.ibm.msg.client.wmq.compat.network.FWHelper", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/network/FWHelper.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  70 */   static PrintWriter debug_out = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean FW_DEBUG_ENABLED = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String FW_END_PORT = "END_PORT";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String FW_HOST = "HOST";
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String FW_OBJECT = "FIREWALL_SETTINGS";
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String FW_START_PORT = "START_PORT";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static InetAddress decodeLocalAddress(Object encoded) {
/*  99 */     if (Trace.isOn) {
/* 100 */       Trace.entry("com.ibm.msg.client.wmq.compat.network.FWHelper", "decodeLocalAddress(Object)", new Object[] { encoded });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 107 */       if (encoded instanceof Hashtable) {
/*     */ 
/*     */         
/* 110 */         Hashtable encHash = (Hashtable)encoded;
/*     */ 
/*     */         
/* 113 */         InetAddress data = (InetAddress)encHash.get("HOST");
/*     */         
/* 115 */         if (Trace.isOn && FW_DEBUG_ENABLED) {
/* 116 */           Trace.traceData("FWHelper", "Retrieved: " + data, null);
/*     */         }
/* 118 */         if (Trace.isOn) {
/* 119 */           Trace.exit("com.ibm.msg.client.wmq.compat.network.FWHelper", "decodeLocalAddress(Object)", data, 1);
/*     */         }
/*     */         
/* 122 */         return data;
/*     */       } 
/*     */       
/* 125 */       if (Trace.isOn && FW_DEBUG_ENABLED) {
/* 126 */         Trace.traceData("FWHelper", "Encoded object is not a hashtable - return null", null);
/*     */       }
/* 128 */       if (Trace.isOn) {
/* 129 */         Trace.exit("com.ibm.msg.client.wmq.compat.network.FWHelper", "decodeLocalAddress(Object)", null, 2);
/*     */       }
/*     */       
/* 132 */       return null;
/*     */     
/*     */     }
/* 135 */     catch (Exception e) {
/* 136 */       if (Trace.isOn) {
/* 137 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.network.FWHelper", "decodeLocalAddress(Object)", e);
/*     */       }
/*     */ 
/*     */       
/* 141 */       if (Trace.isOn) {
/* 142 */         Trace.traceData("FWHelper", "Exception occurred during decoding", null);
/*     */       }
/* 144 */       if (Trace.isOn) {
/* 145 */         Trace.traceData("FWHelper", e.toString(), null);
/*     */       }
/* 147 */       if (Trace.isOn) {
/* 148 */         Trace.exit("com.ibm.msg.client.wmq.compat.network.FWHelper", "decodeLocalAddress(Object)", null, 3);
/*     */       }
/*     */       
/* 151 */       return null;
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
/*     */   private static int decodePort(Object encoded, String portType) {
/* 163 */     if (Trace.isOn) {
/* 164 */       Trace.entry("com.ibm.msg.client.wmq.compat.network.FWHelper", "decodePort(Object,String)", new Object[] { encoded, portType });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 171 */       if (encoded instanceof Hashtable) {
/*     */ 
/*     */         
/* 174 */         Hashtable encHash = (Hashtable)encoded;
/*     */ 
/*     */         
/* 177 */         Integer data = (Integer)encHash.get(portType);
/*     */         
/* 179 */         if (Trace.isOn && FW_DEBUG_ENABLED) {
/* 180 */           Trace.traceData("FWHelper", "Retrieved: " + data, null);
/*     */         }
/* 182 */         if (data != null) {
/* 183 */           if (Trace.isOn && FW_DEBUG_ENABLED) {
/* 184 */             Trace.traceData("FWHelper", "Returning: " + data.intValue(), null);
/*     */           }
/* 186 */           int traceRet1 = data.intValue();
/* 187 */           if (Trace.isOn) {
/* 188 */             Trace.exit("com.ibm.msg.client.wmq.compat.network.FWHelper", "decodePort(Object,String)", 
/* 189 */                 Integer.valueOf(traceRet1), 1);
/*     */           }
/* 191 */           return traceRet1;
/*     */         } 
/*     */         
/* 194 */         if (Trace.isOn && FW_DEBUG_ENABLED) {
/* 195 */           Trace.traceData("FWHelper", "Returning: 0", null);
/*     */         }
/* 197 */         if (Trace.isOn) {
/* 198 */           Trace.exit("com.ibm.msg.client.wmq.compat.network.FWHelper", "decodePort(Object,String)", 
/* 199 */               Integer.valueOf(0), 2);
/*     */         }
/* 201 */         return 0;
/*     */       } 
/*     */ 
/*     */       
/* 205 */       if (Trace.isOn && FW_DEBUG_ENABLED) {
/* 206 */         Trace.traceData("FWHelper", "Encoded object is not a hashtable - return 0", null);
/*     */       }
/* 208 */       if (Trace.isOn) {
/* 209 */         Trace.exit("com.ibm.msg.client.wmq.compat.network.FWHelper", "decodePort(Object,String)", 
/* 210 */             Integer.valueOf(0), 3);
/*     */       }
/* 212 */       return 0;
/*     */     
/*     */     }
/* 215 */     catch (Exception e) {
/* 216 */       if (Trace.isOn) {
/* 217 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.network.FWHelper", "decodePort(Object,String)", e);
/*     */       }
/*     */ 
/*     */       
/* 221 */       if (Trace.isOn) {
/* 222 */         Trace.traceData("FWHelper", "Exception occurred during decoding - return 0", null);
/*     */       }
/* 224 */       if (Trace.isOn) {
/* 225 */         Trace.traceData("FWHelper", e.toString(), null);
/*     */       }
/* 227 */       if (Trace.isOn) {
/* 228 */         Trace.exit("com.ibm.msg.client.wmq.compat.network.FWHelper", "decodePort(Object,String)", 
/* 229 */             Integer.valueOf(0), 4);
/*     */       }
/* 231 */       return 0;
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
/*     */   public static Object encodeToObject(String setting) throws MQException {
/* 248 */     if (Trace.isOn) {
/* 249 */       Trace.entry("com.ibm.msg.client.wmq.compat.network.FWHelper", "encodeToObject(String)", new Object[] { setting });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 256 */       Hashtable<Object, Object> encoded = new Hashtable<>();
/*     */ 
/*     */ 
/*     */       
/* 260 */       InetAddress address = getLocalIP(setting);
/* 261 */       if (address != null) {
/* 262 */         encoded.put("HOST", address);
/*     */       }
/*     */ 
/*     */       
/* 266 */       encoded.put("START_PORT", Integer.valueOf(getStartPort(setting)));
/*     */ 
/*     */       
/* 269 */       encoded.put("END_PORT", Integer.valueOf(getEndPort(setting)));
/*     */       
/* 271 */       if (Trace.isOn) {
/* 272 */         Trace.exit("com.ibm.msg.client.wmq.compat.network.FWHelper", "encodeToObject(String)", encoded);
/*     */       }
/*     */       
/* 275 */       return encoded;
/*     */     
/*     */     }
/* 278 */     catch (MQException m) {
/* 279 */       if (Trace.isOn) {
/* 280 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.network.FWHelper", "encodeToObject(String)", (Throwable)m, 1);
/*     */       }
/*     */       
/* 283 */       if (Trace.isOn) {
/* 284 */         Trace.throwing("com.ibm.msg.client.wmq.compat.network.FWHelper", "encodeToObject(String)", (Throwable)m, 1);
/*     */       }
/*     */       
/* 287 */       throw m;
/*     */     }
/* 289 */     catch (Exception e) {
/* 290 */       if (Trace.isOn) {
/* 291 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.network.FWHelper", "encodeToObject(String)", e, 2);
/*     */       }
/*     */ 
/*     */       
/* 295 */       if (Trace.isOn) {
/* 296 */         Trace.traceData("FWHelper", "Exception occurred during encoding", null);
/*     */       }
/* 298 */       if (Trace.isOn) {
/* 299 */         Trace.traceData("FWHelper", e.toString(), null);
/*     */       }
/* 301 */       MQException traceRet1 = new MQException(2, 2059, "FWHelper encodeToObject", "MQJE071");
/* 302 */       if (Trace.isOn) {
/* 303 */         Trace.throwing("com.ibm.msg.client.wmq.compat.network.FWHelper", "encodeToObject(String)", (Throwable)traceRet1, 2);
/*     */       }
/*     */       
/* 306 */       throw traceRet1;
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
/*     */   public static boolean fuzzyCompare(MQManagedConnectionJ11 mc, Object settingObject) {
/*     */     MQChannelDefinition clientConn;
/* 320 */     if (Trace.isOn) {
/* 321 */       Trace.entry("com.ibm.msg.client.wmq.compat.network.FWHelper", "fuzzyCompare(MQManagedConnectionJ11,Object)", new Object[] { mc, settingObject });
/*     */     }
/*     */ 
/*     */     
/* 325 */     String localInetAddress = null;
/* 326 */     int localPort = 0;
/*     */ 
/*     */ 
/*     */     
/* 330 */     MQSESSION sess = mc.getMQSESSION();
/*     */     
/* 332 */     switch (sess.getJmqiBindType()) {
/*     */       
/*     */       case 1:
/*     */       case 2:
/* 336 */         clientConn = sess.getMQCD();
/*     */ 
/*     */         
/* 339 */         localInetAddress = clientConn.localAddress;
/* 340 */         localPort = clientConn.port;
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       default:
/* 348 */         if (Trace.isOn) {
/* 349 */           Trace.exit("com.ibm.msg.client.wmq.compat.network.FWHelper", "fuzzyCompare(MQManagedConnectionJ11,Object)", 
/* 350 */               Boolean.valueOf(false), 1);
/*     */         }
/* 352 */         return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 357 */     InetAddress decoded = decodeLocalAddress(settingObject);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 365 */     if (decoded == null || (localInetAddress != null && localInetAddress.equals(decoded.toString()))) {
/*     */       
/* 367 */       if (Trace.isOn) {
/* 368 */         Trace.traceData("FWHelper", "Local address object matches.", null);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 374 */       int lower = decodePort(settingObject, "START_PORT");
/* 375 */       int upper = decodePort(settingObject, "END_PORT");
/*     */ 
/*     */ 
/*     */       
/* 379 */       if (upper == 0) {
/*     */         
/* 381 */         if (Trace.isOn) {
/* 382 */           Trace.traceData("FWHelper", "No port restrictrictions set.", null);
/*     */         }
/* 384 */         if (Trace.isOn) {
/* 385 */           Trace.exit("com.ibm.msg.client.wmq.compat.network.FWHelper", "fuzzyCompare(MQManagedConnectionJ11,Object)", 
/* 386 */               Boolean.valueOf(true), 2);
/*     */         }
/* 388 */         return true;
/*     */       } 
/*     */       
/* 391 */       if (localPort >= lower && localPort <= upper) {
/*     */         
/* 393 */         if (Trace.isOn) {
/* 394 */           Trace.traceData("FWHelper", "Bound port is within range", null);
/*     */         }
/* 396 */         if (Trace.isOn) {
/* 397 */           Trace.exit("com.ibm.msg.client.wmq.compat.network.FWHelper", "fuzzyCompare(MQManagedConnectionJ11,Object)", 
/* 398 */               Boolean.valueOf(true), 3);
/*     */         }
/* 400 */         return true;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 405 */       if (Trace.isOn) {
/* 406 */         Trace.traceData("FWHelper", "Bound port " + localPort + " is outside range [" + lower + ", " + upper + "]", null);
/*     */       }
/* 408 */       if (Trace.isOn) {
/* 409 */         Trace.exit("com.ibm.msg.client.wmq.compat.network.FWHelper", "fuzzyCompare(MQManagedConnectionJ11,Object)", 
/* 410 */             Boolean.valueOf(false), 4);
/*     */       }
/* 412 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 417 */     if (Trace.isOn) {
/* 418 */       Trace.traceData("FWHelper", "Local address object does not match", null);
/*     */     }
/* 420 */     if (Trace.isOn) {
/* 421 */       Trace.exit("com.ibm.msg.client.wmq.compat.network.FWHelper", "fuzzyCompare(MQManagedConnectionJ11,Object)", 
/* 422 */           Boolean.valueOf(false), 5);
/*     */     }
/* 424 */     return false;
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
/*     */   public static int getEndPort(String setting) throws MQException {
/* 442 */     if (Trace.isOn) {
/* 443 */       Trace.entry("com.ibm.msg.client.wmq.compat.network.FWHelper", "getEndPort(String)", new Object[] { setting });
/*     */     }
/*     */ 
/*     */     
/* 447 */     int returnCode = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 453 */       String stringSetting = null;
/*     */ 
/*     */ 
/*     */       
/* 457 */       if (setting != null) {
/*     */ 
/*     */         
/* 460 */         int commaIndex = setting.indexOf(",");
/*     */         
/* 462 */         if (commaIndex == -1) {
/*     */ 
/*     */           
/* 465 */           if (Trace.isOn && FW_DEBUG_ENABLED) {
/* 466 */             Trace.traceData("FWHelper", "No comma specified - default to getStartPort", null);
/*     */           }
/* 468 */           int traceRet1 = getStartPort(setting);
/* 469 */           if (Trace.isOn) {
/* 470 */             Trace.exit("com.ibm.msg.client.wmq.compat.network.FWHelper", "getEndPort(String)", 
/* 471 */                 Integer.valueOf(traceRet1), 1);
/*     */           }
/* 473 */           return traceRet1;
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 478 */         int endChar = 0;
/*     */ 
/*     */         
/* 481 */         if ((endChar = setting.indexOf(")", commaIndex)) != -1) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 500 */           stringSetting = setting.substring(commaIndex + 1, endChar);
/*     */           
/* 502 */           if (Trace.isOn && FW_DEBUG_ENABLED) {
/* 503 */             Trace.traceData("FWHelper", "String form of end port: " + stringSetting, null);
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*     */           try {
/* 509 */             if (stringSetting != null) {
/* 510 */               stringSetting = stringSetting.trim();
/*     */             }
/*     */ 
/*     */             
/* 514 */             if (stringSetting.length() == 0) {
/* 515 */               stringSetting = "0";
/*     */             }
/*     */ 
/*     */             
/* 519 */             int portEnd = Integer.parseInt(stringSetting);
/*     */             
/* 521 */             if (portEnd >= 0) {
/* 522 */               if (Trace.isOn && FW_DEBUG_ENABLED) {
/* 523 */                 Trace.traceData("FWHelper", "Converted to int successfully: " + portEnd, null);
/*     */               }
/* 525 */               if (Trace.isOn) {
/* 526 */                 Trace.exit("com.ibm.msg.client.wmq.compat.network.FWHelper", "getEndPort(String)", 
/* 527 */                     Integer.valueOf(portEnd), 2);
/*     */               }
/* 529 */               return portEnd;
/*     */             } 
/*     */ 
/*     */             
/* 533 */             if (Trace.isOn) {
/* 534 */               Trace.traceData("FWHelper", "Negative port number: " + portEnd, null);
/*     */             }
/* 536 */             MQException traceRet3 = new MQException(2, 2059, "FWHelper getEndPort", "MQJE071");
/* 537 */             if (Trace.isOn) {
/* 538 */               Trace.throwing("com.ibm.msg.client.wmq.compat.network.FWHelper", "getEndPort(String)", (Throwable)traceRet3, 2);
/*     */             }
/*     */             
/* 541 */             throw traceRet3;
/*     */           
/*     */           }
/* 544 */           catch (MQException m) {
/* 545 */             MQException traceRet2; if (Trace.isOn) {
/* 546 */               Trace.catchBlock("com.ibm.msg.client.wmq.compat.network.FWHelper", "getEndPort(String)", (Throwable)traceRet2, 1);
/*     */             }
/*     */             
/* 549 */             if (Trace.isOn) {
/* 550 */               Trace.throwing("com.ibm.msg.client.wmq.compat.network.FWHelper", "getEndPort(String)", (Throwable)traceRet2, 3);
/*     */             }
/*     */             
/* 553 */             throw traceRet2;
/*     */           
/*     */           }
/* 556 */           catch (Exception g) {
/* 557 */             if (Trace.isOn) {
/* 558 */               Trace.catchBlock("com.ibm.msg.client.wmq.compat.network.FWHelper", "getEndPort(String)", g, 2);
/*     */             }
/*     */ 
/*     */             
/* 562 */             if (Trace.isOn) {
/* 563 */               Trace.traceData("FWHelper", "Exception occurred converting string to int - return " + returnCode, null);
/*     */             }
/* 565 */             if (Trace.isOn) {
/* 566 */               Trace.traceData("FWHelper", g.toString(), null);
/*     */             }
/* 568 */             MQException traceRet4 = new MQException(2, 2059, "FWHelper getEndPort", "MQJE071");
/* 569 */             if (Trace.isOn) {
/* 570 */               Trace.throwing("com.ibm.msg.client.wmq.compat.network.FWHelper", "getEndPort(String)", (Throwable)traceRet4, 4);
/*     */             }
/*     */             
/* 573 */             throw traceRet4;
/*     */           } 
/*     */         }  if (Trace.isOn && FW_DEBUG_ENABLED)
/*     */           Trace.traceData("FWHelper", "Malformed setting string. No closing bracket found - return " + returnCode, null);  MQException mQException = new MQException(2, 2059, "FWHelper getEndPort", "MQJE071"); if (Trace.isOn)
/*     */           Trace.throwing("com.ibm.msg.client.wmq.compat.network.FWHelper", "getEndPort(String)", (Throwable)mQException, 1); 
/*     */         throw mQException;
/*     */       } 
/* 580 */       if (Trace.isOn && FW_DEBUG_ENABLED) {
/* 581 */         Trace.traceData("FWHelper", "setting string is null - default to 0", null);
/*     */       }
/* 583 */       int traceRet5 = 0;
/* 584 */       if (Trace.isOn) {
/* 585 */         Trace.exit("com.ibm.msg.client.wmq.compat.network.FWHelper", "getEndPort(String)", 
/* 586 */             Integer.valueOf(traceRet5), 3);
/*     */       }
/* 588 */       return traceRet5;
/*     */     
/*     */     }
/* 591 */     catch (MQException m) {
/* 592 */       if (Trace.isOn) {
/* 593 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.network.FWHelper", "getEndPort(String)", (Throwable)m, 3);
/*     */       }
/*     */       
/* 596 */       if (Trace.isOn) {
/* 597 */         Trace.throwing("com.ibm.msg.client.wmq.compat.network.FWHelper", "getEndPort(String)", (Throwable)m, 5);
/*     */       }
/*     */       
/* 600 */       throw m;
/*     */     }
/* 602 */     catch (Exception g) {
/* 603 */       if (Trace.isOn) {
/* 604 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.network.FWHelper", "getEndPort(String)", g, 4);
/*     */       }
/*     */       
/* 607 */       if (Trace.isOn) {
/* 608 */         Trace.traceData("FWHelper", "Exception occurred - return " + returnCode, null);
/*     */       }
/* 610 */       if (Trace.isOn) {
/* 611 */         Trace.traceData("FWHelper", g.toString(), null);
/*     */       }
/* 613 */       MQException traceRet6 = new MQException(2, 2059, "FWHelper getEndPort", "MQJE071");
/* 614 */       if (Trace.isOn) {
/* 615 */         Trace.throwing("com.ibm.msg.client.wmq.compat.network.FWHelper", "getEndPort(String)", (Throwable)traceRet6, 6);
/*     */       }
/*     */       
/* 618 */       throw traceRet6;
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
/*     */   public static InetAddress getLocalIP(String setting) throws MQException {
/* 637 */     if (Trace.isOn) {
/* 638 */       Trace.entry("com.ibm.msg.client.wmq.compat.network.FWHelper", "getLocalIP(String)", new Object[] { setting });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 644 */       InetAddress fw_localip = null;
/* 645 */       String hostSetting = null;
/*     */ 
/*     */ 
/*     */       
/* 649 */       if (setting != null) {
/*     */ 
/*     */         
/* 652 */         int bracketIndex = setting.indexOf("(");
/*     */ 
/*     */         
/* 655 */         if (bracketIndex == -1) {
/* 656 */           bracketIndex = setting.length();
/*     */         }
/*     */ 
/*     */         
/* 660 */         hostSetting = setting.substring(0, bracketIndex);
/*     */       }
/*     */       else {
/*     */         
/* 664 */         hostSetting = null;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 672 */         if (hostSetting != null && hostSetting.trim().length() == 0) {
/* 673 */           hostSetting = null;
/*     */         }
/* 675 */         if (Trace.isOn && FW_DEBUG_ENABLED) {
/* 676 */           Trace.traceData("FWHelper", "Attempt to get local ip setting from this string: '" + hostSetting + "'", null);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 682 */         if (hostSetting == null || hostSetting.equalsIgnoreCase("localhost") || hostSetting.equalsIgnoreCase("127.0.0.1")) {
/* 683 */           fw_localip = InetAddress.getLocalHost();
/*     */         } else {
/* 685 */           fw_localip = InetAddress.getByName(hostSetting);
/*     */         } 
/*     */         
/* 688 */         if (fw_localip != null) {
/* 689 */           if (Trace.isOn && FW_DEBUG_ENABLED) {
/* 690 */             Trace.traceData("FWHelper", "Parsed local IP setting: " + fw_localip.getHostAddress(), null);
/*     */           }
/*     */         }
/* 693 */         else if (Trace.isOn && FW_DEBUG_ENABLED) {
/* 694 */           Trace.traceData("FWHelper", "Parsed local IP setting is null", null);
/*     */         } 
/*     */ 
/*     */         
/* 698 */         if (Trace.isOn) {
/* 699 */           Trace.exit("com.ibm.msg.client.wmq.compat.network.FWHelper", "getLocalIP(String)", fw_localip);
/*     */         }
/*     */         
/* 702 */         return fw_localip;
/*     */       
/*     */       }
/* 705 */       catch (IOException e) {
/* 706 */         if (Trace.isOn) {
/* 707 */           Trace.catchBlock("com.ibm.msg.client.wmq.compat.network.FWHelper", "getLocalIP(String)", e, 1);
/*     */         }
/*     */         
/* 710 */         if (Trace.isOn) {
/* 711 */           Trace.traceData("FWHelper", "IO Error determining local InetAddress", null);
/*     */         }
/*     */         
/* 714 */         MQException traceRet1 = new MQException(2, 2059, "FWHelper getLocalIP", "MQJE071");
/* 715 */         if (Trace.isOn) {
/* 716 */           Trace.throwing("com.ibm.msg.client.wmq.compat.network.FWHelper", "getLocalIP(String)", (Throwable)traceRet1, 1);
/*     */         }
/*     */         
/* 719 */         throw traceRet1;
/*     */       
/*     */       }
/* 722 */       catch (Exception f) {
/* 723 */         if (Trace.isOn) {
/* 724 */           Trace.catchBlock("com.ibm.msg.client.wmq.compat.network.FWHelper", "getLocalIP(String)", f, 2);
/*     */         }
/*     */         
/* 727 */         if (Trace.isOn) {
/* 728 */           Trace.traceData("FWHelper", "Error determining local InetAddress", null);
/*     */         }
/*     */         
/* 731 */         MQException traceRet2 = new MQException(2, 2059, "FWHelper getLocalIP", "MQJE071");
/* 732 */         if (Trace.isOn) {
/* 733 */           Trace.throwing("com.ibm.msg.client.wmq.compat.network.FWHelper", "getLocalIP(String)", (Throwable)traceRet2, 2);
/*     */         }
/*     */         
/* 736 */         throw traceRet2;
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 741 */     catch (MQException m) {
/* 742 */       if (Trace.isOn) {
/* 743 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.network.FWHelper", "getLocalIP(String)", (Throwable)m, 3);
/*     */       }
/*     */       
/* 746 */       if (Trace.isOn) {
/* 747 */         Trace.throwing("com.ibm.msg.client.wmq.compat.network.FWHelper", "getLocalIP(String)", (Throwable)m, 3);
/*     */       }
/*     */       
/* 750 */       throw m;
/*     */     }
/* 752 */     catch (Exception g) {
/* 753 */       if (Trace.isOn) {
/* 754 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.network.FWHelper", "getLocalIP(String)", g, 4);
/*     */       }
/*     */       
/* 757 */       if (Trace.isOn) {
/* 758 */         Trace.traceData("FWHelper", "Exception occurred", null);
/*     */       }
/*     */       
/* 761 */       MQException traceRet3 = new MQException(2, 2059, "FWHelper getLocalIP", "MQJE071");
/* 762 */       if (Trace.isOn) {
/* 763 */         Trace.throwing("com.ibm.msg.client.wmq.compat.network.FWHelper", "getLocalIP(String)", (Throwable)traceRet3, 4);
/*     */       }
/*     */       
/* 766 */       throw traceRet3;
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
/*     */   public static int getStartPort(String setting) throws MQException {
/* 785 */     if (Trace.isOn) {
/* 786 */       Trace.entry("com.ibm.msg.client.wmq.compat.network.FWHelper", "getStartPort(String)", new Object[] { setting });
/*     */     }
/*     */ 
/*     */     
/* 790 */     int returnCode = 0;
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 795 */       String stringSetting = null;
/*     */ 
/*     */ 
/*     */       
/* 799 */       if (setting != null) {
/*     */ 
/*     */         
/* 802 */         int bracketIndex = setting.indexOf("(");
/*     */         
/* 804 */         if (bracketIndex == -1) {
/*     */ 
/*     */           
/* 807 */           if (Trace.isOn && FW_DEBUG_ENABLED) {
/* 808 */             Trace.traceData("FWHelper", "No starting bracket specified - default to 0", null);
/*     */           }
/* 810 */           if (Trace.isOn) {
/* 811 */             Trace.exit("com.ibm.msg.client.wmq.compat.network.FWHelper", "getStartPort(String)", 
/* 812 */                 Integer.valueOf(0), 1);
/*     */           }
/* 814 */           return 0;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 820 */         int endChar = 0;
/*     */ 
/*     */         
/* 823 */         if ((endChar = setting.indexOf(",", bracketIndex)) == -1)
/*     */         {
/*     */ 
/*     */           
/* 827 */           if ((endChar = setting.indexOf(")", bracketIndex)) == -1) {
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 832 */             if (Trace.isOn && FW_DEBUG_ENABLED) {
/* 833 */               Trace.traceData("FWHelper", "Malformed setting string. No comma or closing bracket found - return " + returnCode, null);
/*     */             }
/* 835 */             MQException traceRet1 = new MQException(2, 2059, "FWHelper getStartPort", "MQJE071");
/* 836 */             if (Trace.isOn) {
/* 837 */               Trace.throwing("com.ibm.msg.client.wmq.compat.network.FWHelper", "getStartPort(String)", (Throwable)traceRet1, 1);
/*     */             }
/*     */             
/* 840 */             throw traceRet1;
/*     */           } 
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 846 */         stringSetting = setting.substring(bracketIndex + 1, endChar);
/*     */         
/* 848 */         if (Trace.isOn && FW_DEBUG_ENABLED) {
/* 849 */           Trace.traceData("FWHelper", "String form of start port: '" + stringSetting + "'", null);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         try {
/* 855 */           if (stringSetting != null) {
/* 856 */             stringSetting = stringSetting.trim();
/*     */           }
/*     */ 
/*     */           
/* 860 */           if (stringSetting.length() == 0) {
/* 861 */             stringSetting = "0";
/*     */           }
/*     */ 
/*     */           
/* 865 */           int portStart = Integer.parseInt(stringSetting);
/*     */           
/* 867 */           if (portStart >= 0) {
/* 868 */             if (Trace.isOn && FW_DEBUG_ENABLED) {
/* 869 */               Trace.traceData("FWHelper", "Converted to int successfully: " + portStart, null);
/*     */             }
/* 871 */             if (Trace.isOn) {
/* 872 */               Trace.exit("com.ibm.msg.client.wmq.compat.network.FWHelper", "getStartPort(String)", 
/* 873 */                   Integer.valueOf(portStart), 2);
/*     */             }
/* 875 */             return portStart;
/*     */           } 
/*     */           
/* 878 */           if (Trace.isOn) {
/* 879 */             Trace.traceData("FWHelper", "Negative port number: " + portStart, null);
/*     */           }
/* 881 */           MQException traceRet2 = new MQException(2, 2059, "FWHelper getStartPort", "MQJE071");
/* 882 */           if (Trace.isOn) {
/* 883 */             Trace.throwing("com.ibm.msg.client.wmq.compat.network.FWHelper", "getStartPort(String)", (Throwable)traceRet2, 2);
/*     */           }
/*     */           
/* 886 */           throw traceRet2;
/*     */         
/*     */         }
/* 889 */         catch (MQException m) {
/* 890 */           if (Trace.isOn) {
/* 891 */             Trace.catchBlock("com.ibm.msg.client.wmq.compat.network.FWHelper", "getStartPort(String)", (Throwable)m, 1);
/*     */           }
/*     */           
/* 894 */           if (Trace.isOn) {
/* 895 */             Trace.throwing("com.ibm.msg.client.wmq.compat.network.FWHelper", "getStartPort(String)", (Throwable)m, 3);
/*     */           }
/*     */           
/* 898 */           throw m;
/*     */         }
/* 900 */         catch (Exception g) {
/* 901 */           if (Trace.isOn) {
/* 902 */             Trace.catchBlock("com.ibm.msg.client.wmq.compat.network.FWHelper", "getStartPort(String)", g, 2);
/*     */           }
/*     */           
/* 905 */           if (Trace.isOn) {
/* 906 */             Trace.traceData("FWHelper", "Exception occurred converting string to int - return " + returnCode, null);
/*     */           }
/* 908 */           if (Trace.isOn) {
/* 909 */             Trace.traceData("FWHelper", g.toString(), null);
/*     */           }
/* 911 */           MQException traceRet3 = new MQException(2, 2059, "FWHelper getStartPort", "MQJE071");
/* 912 */           if (Trace.isOn) {
/* 913 */             Trace.throwing("com.ibm.msg.client.wmq.compat.network.FWHelper", "getStartPort(String)", (Throwable)traceRet3, 4);
/*     */           }
/*     */           
/* 916 */           throw traceRet3;
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 924 */       if (Trace.isOn && FW_DEBUG_ENABLED) {
/* 925 */         Trace.traceData("FWHelper", "setting string is null - default to 0", null);
/*     */       }
/* 927 */       if (Trace.isOn) {
/* 928 */         Trace.exit("com.ibm.msg.client.wmq.compat.network.FWHelper", "getStartPort(String)", 
/* 929 */             Integer.valueOf(0), 3);
/*     */       }
/* 931 */       return 0;
/*     */     
/*     */     }
/* 934 */     catch (MQException m) {
/* 935 */       if (Trace.isOn) {
/* 936 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.network.FWHelper", "getStartPort(String)", (Throwable)m, 3);
/*     */       }
/*     */       
/* 939 */       if (Trace.isOn) {
/* 940 */         Trace.throwing("com.ibm.msg.client.wmq.compat.network.FWHelper", "getStartPort(String)", (Throwable)m, 5);
/*     */       }
/*     */       
/* 943 */       throw m;
/*     */     }
/* 945 */     catch (Exception g) {
/* 946 */       if (Trace.isOn) {
/* 947 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.network.FWHelper", "getStartPort(String)", g, 4);
/*     */       }
/*     */       
/* 950 */       if (Trace.isOn) {
/* 951 */         Trace.traceData("FWHelper", "Exception occurred - return " + returnCode, null);
/*     */       }
/* 953 */       if (Trace.isOn) {
/* 954 */         Trace.traceData("FWHelper", g.toString(), null);
/*     */       }
/* 956 */       MQException traceRet4 = new MQException(2, 2059, "FWHelper getStartPort", "MQJE071");
/* 957 */       if (Trace.isOn) {
/* 958 */         Trace.throwing("com.ibm.msg.client.wmq.compat.network.FWHelper", "getStartPort(String)", (Throwable)traceRet4, 6);
/*     */       }
/*     */       
/* 961 */       throw traceRet4;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\network\FWHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */