/*     */ package com.ibm.msg.client.wmq.common.internal;
/*     */ 
/*     */ import com.ibm.mq.MQException;
/*     */ import com.ibm.mq.constants.CMQC;
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.system.JmqiSP;
/*     */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.DetailedIllegalStateException;
/*     */ import com.ibm.msg.client.jms.DetailedInvalidDestinationException;
/*     */ import com.ibm.msg.client.jms.DetailedInvalidSelectorException;
/*     */ import com.ibm.msg.client.jms.DetailedJMSException;
/*     */ import com.ibm.msg.client.jms.DetailedJMSSecurityException;
/*     */ import com.ibm.msg.client.jms.DetailedMessageEOFException;
/*     */ import com.ibm.msg.client.jms.DetailedMessageFormatException;
/*     */ import com.ibm.msg.client.jms.DetailedResourceAllocationException;
/*     */ import com.ibm.msg.client.jms.DetailedTransactionInProgressException;
/*     */ import com.ibm.msg.client.jms.DetailedTransactionRolledBackException;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.HashMap;
/*     */ import javax.jms.JMSException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Reason
/*     */ {
/*     */   static {
/*  58 */     if (Trace.isOn) {
/*  59 */       Trace.data("com.ibm.msg.client.wmq.common.internal.Reason", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/Reason.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  68 */   private static HashMap<Integer, String> compcodeMap = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  77 */   private static int[] impossibleReasons = new int[] { 2004, 2018, 2019, 2020, 2021, 2022, 2023, 2026, 6125, 2036, 2037, 2038, 2039, 2093, 2094, 2043, 2044, 2062, 2065, 2067, 2075, 2076, 2078, 2090, 2097, 2099, 2134, 2135, 2139, 2141, 2148, 2149, 2154, 2155, 2156, 2158, 2159, 2173, 2186, 2191, 2194, 2219, 2220, 2235, 2236, 2237, 2238, 2239, 2247, 2248, 2253, 2260, 2265, 2277, 2299, 2300, 2301, 2307, 2310, 2312, 2314, 2319, 2320, 2321, 2323, 2327, 2331, 2333, 2334, 2335, 2336, 2337, 2338, 2339, 2356, 2358, 2380, 2383, 2384, 2385, 2386, 2389, 2390, 2392, 2395, 2396, 2414, 2415, 2416, 2420, 2422, 2424, 2426, 2002, 2066, 2257 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 106 */   private static int[] connectionBrokenReasons = new int[] { 2009, 2195, 2059, 2273, 2161, 2162, 2279, 2203, 2202, 2223, 2373, 2538, 2537, 2539, 2041, 2546 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 114 */   private static HashMap<Integer, String> reasonMap = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/Reason.java";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/* 126 */     if (Trace.isOn) {
/* 127 */       Trace.entry("com.ibm.msg.client.wmq.common.internal.Reason", "static()");
/*     */     }
/* 129 */     NLSServices.addCatalogue("com.ibm.msg.client.wmq.common.internal.resources.JMSCMQ_MessageResourceBundle", "JMSCMQ", Reason.class);
/*     */     
/* 131 */     String name = null;
/* 132 */     int idx = 0;
/* 133 */     Field field = null;
/*     */     try {
/* 135 */       Field[] fields = CMQC.class.getFields();
/* 136 */       for (idx = 0; idx < fields.length; idx++) {
/* 137 */         field = fields[idx];
/* 138 */         name = field.getName();
/* 139 */         if (name.startsWith("MQRC_")) {
/* 140 */           reasonMap.put(Integer.valueOf(field.getInt(null)), name);
/*     */         }
/* 142 */         else if (name.startsWith("MQCC_")) {
/* 143 */           compcodeMap.put(Integer.valueOf(field.getInt(null)), name);
/*     */         }
/*     */       
/*     */       } 
/* 147 */     } catch (Exception e) {
/* 148 */       if (Trace.isOn) {
/* 149 */         Trace.catchBlock("com.ibm.msg.client.wmq.common.internal.Reason", "static()", e);
/*     */       }
/*     */       
/* 152 */       HashMap<String, Object> info = new HashMap<>();
/* 153 */       info.put("exception", e);
/* 154 */       info.put("index", Integer.valueOf(idx));
/* 155 */       info.put("field", field);
/* 156 */       Trace.ffst("Reason", "getSymbol(int)", "XM001001", info, null);
/*     */     } 
/* 158 */     if (Trace.isOn) {
/* 159 */       Trace.exit("com.ibm.msg.client.wmq.common.internal.Reason", "static()");
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
/*     */   public static JMSException createException(String messageid, HashMap<String, ? extends Object> inserts, int reason, int compcode, JmqiEnvironment environment) {
/*     */     JmqiException jmqiException1;
/* 179 */     if (Trace.isOn) {
/* 180 */       Trace.entry("com.ibm.msg.client.wmq.common.internal.Reason", "createException(String,HashMap<String , ? extends Object>,int,int,JmqiEnvironment)", new Object[] { messageid, inserts, 
/*     */             
/* 182 */             Integer.valueOf(reason), Integer.valueOf(compcode), environment });
/*     */     }
/*     */     
/* 185 */     if (0 == reason) {
/* 186 */       if (Trace.isOn) {
/* 187 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.Reason", "createException(String,HashMap<String , ? extends Object>,int,int,JmqiEnvironment)", null, 1);
/*     */       }
/*     */ 
/*     */       
/* 191 */       return null;
/*     */     } 
/* 193 */     Exception jmqiException = null;
/* 194 */     if (null != environment) {
/* 195 */       jmqiException1 = environment.getLastException();
/*     */     }
/*     */     
/* 198 */     StringBuffer wmqMsgbuff = new StringBuffer("JMSCMQ0001");
/* 199 */     wmqMsgbuff.append(": ");
/* 200 */     wmqMsgbuff.append(NLSServices.getMessage("JMSCMQ0001", new Object[] { Integer.toString(compcode), getCompCodeSymbol(compcode), Integer.toString(reason), 
/* 201 */             getReasonSymbol(reason) }));
/* 202 */     String wmqMsg = wmqMsgbuff.toString();
/* 203 */     MQException wmqex = new MQException(wmqMsg, "JMSCMQ0001", reason, compcode);
/*     */     
/* 205 */     if (null != jmqiException1) {
/* 206 */       wmqex.initCause((Throwable)jmqiException1);
/*     */     }
/*     */     
/* 209 */     StringBuffer msgbuff = new StringBuffer(messageid);
/* 210 */     msgbuff.append(": ");
/* 211 */     msgbuff.append(NLSServices.getMessage(messageid, inserts));
/* 212 */     String message = msgbuff.toString();
/* 213 */     String explanation = NLSServices.getExplanation(messageid, inserts);
/* 214 */     String useraction = NLSServices.getUserAction(messageid, inserts);
/* 215 */     JMSException je = reasonToException(reason, message, messageid, explanation, useraction, inserts);
/* 216 */     je.setLinkedException((Exception)wmqex);
/* 217 */     if (Trace.isOn) {
/* 218 */       Trace.exit("com.ibm.msg.client.wmq.common.internal.Reason", "createException(String,HashMap<String , ? extends Object>,int,int,JmqiEnvironment)", je, 2);
/*     */     }
/*     */ 
/*     */     
/* 222 */     return je;
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
/*     */   public static JMSException createException(String method, int reason, int compcode, JmqiEnvironment environment) {
/* 239 */     if (Trace.isOn) {
/* 240 */       Trace.entry("com.ibm.msg.client.wmq.common.internal.Reason", "createException(String,int,int,JmqiEnvironment)", new Object[] { method, 
/*     */             
/* 242 */             Integer.valueOf(reason), Integer.valueOf(compcode), environment });
/*     */     }
/* 244 */     if (0 == reason) {
/* 245 */       if (Trace.isOn) {
/* 246 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.Reason", "createException(String,int,int,JmqiEnvironment)", null, 1);
/*     */       }
/*     */       
/* 249 */       return null;
/*     */     } 
/* 251 */     HashMap<String, Object> inserts = new HashMap<>();
/* 252 */     inserts.put("XMSC_INSERT_METHOD", method);
/* 253 */     JMSException traceRet1 = createException("JMSCMQ0002", inserts, reason, compcode, environment);
/* 254 */     if (Trace.isOn) {
/* 255 */       Trace.exit("com.ibm.msg.client.wmq.common.internal.Reason", "createException(String,int,int,JmqiEnvironment)", traceRet1, 2);
/*     */     }
/*     */     
/* 258 */     return traceRet1;
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
/*     */   private static String getCompCodeSymbol(int compcode) {
/* 270 */     if (Trace.isOn)
/* 271 */       Trace.entry("com.ibm.msg.client.wmq.common.internal.Reason", "getCompCodeSymbol(int)", new Object[] {
/* 272 */             Integer.valueOf(compcode)
/*     */           }); 
/* 274 */     String symbol = compcodeMap.get(Integer.valueOf(compcode));
/* 275 */     if (null == symbol) {
/*     */ 
/*     */       
/* 278 */       HashMap<String, Integer> info = new HashMap<>();
/* 279 */       info.put("compcode", Integer.valueOf(compcode));
/* 280 */       Trace.ffst("Reason", "getCompCodeSymbol(int)", "XM001003", info, null);
/*     */     } 
/* 282 */     if (Trace.isOn) {
/* 283 */       Trace.exit("com.ibm.msg.client.wmq.common.internal.Reason", "getCompCodeSymbol(int)", symbol);
/*     */     }
/*     */     
/* 286 */     return symbol;
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
/*     */   private static String getReasonSymbol(int reason) {
/* 298 */     if (Trace.isOn)
/* 299 */       Trace.entry("com.ibm.msg.client.wmq.common.internal.Reason", "getReasonSymbol(int)", new Object[] {
/* 300 */             Integer.valueOf(reason)
/*     */           }); 
/* 302 */     String symbol = reasonMap.get(Integer.valueOf(reason));
/* 303 */     if (null == symbol)
/*     */     {
/*     */       
/* 306 */       symbol = "";
/*     */     }
/* 308 */     if (Trace.isOn) {
/* 309 */       Trace.exit("com.ibm.msg.client.wmq.common.internal.Reason", "getReasonSymbol(int)", symbol);
/*     */     }
/*     */     
/* 312 */     return symbol;
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
/*     */   public static boolean isConnectionBroken(int reason) {
/* 324 */     if (Trace.isOn) {
/* 325 */       Trace.entry("com.ibm.msg.client.wmq.common.internal.Reason", "isConnectionBroken(int)", new Object[] {
/* 326 */             Integer.valueOf(reason)
/*     */           });
/*     */     }
/* 329 */     for (int i = 0; i < connectionBrokenReasons.length; i++) {
/* 330 */       if (connectionBrokenReasons[i] == reason) {
/* 331 */         if (Trace.isOn) {
/* 332 */           Trace.exit("com.ibm.msg.client.wmq.common.internal.Reason", "isConnectionBroken(int)", 
/* 333 */               Boolean.valueOf(true), 1);
/*     */         }
/* 335 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 339 */     if (Trace.isOn) {
/* 340 */       Trace.exit("com.ibm.msg.client.wmq.common.internal.Reason", "isConnectionBroken(int)", 
/* 341 */           Boolean.valueOf(false), 2);
/*     */     }
/* 343 */     return false;
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
/*     */   public static boolean isImpossibleReason(int reason, int compcode, JmqiSP sp) {
/* 364 */     if (Trace.isOn) {
/* 365 */       Trace.entry("com.ibm.msg.client.wmq.common.internal.Reason", "isImpossibleReason(int,int,JmqiSP)", new Object[] {
/* 366 */             Integer.valueOf(reason), Integer.valueOf(compcode), sp
/*     */           });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 372 */     if (reason == 2018 && sp != null && sp.isIMS()) {
/* 373 */       if (Trace.isOn) {
/* 374 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.Reason", "isImpossibleReason(int,int,JmqiSP)", Boolean.FALSE, 1);
/*     */       }
/* 376 */       return false;
/*     */     } 
/*     */     
/* 379 */     for (int i = 0; i < impossibleReasons.length; i++) {
/* 380 */       if (impossibleReasons[i] == reason) {
/* 381 */         if (Trace.isOn) {
/* 382 */           Trace.exit("com.ibm.msg.client.wmq.common.internal.Reason", "isImpossibleReason(int,int)", Boolean.TRUE, 2);
/*     */         }
/*     */         
/* 385 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 389 */     if (0 != compcode && 1 != compcode && 2 != compcode) {
/* 390 */       if (Trace.isOn) {
/* 391 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.Reason", "isImpossibleReason(int,int)", Boolean.TRUE, 3);
/*     */       }
/*     */       
/* 394 */       return true;
/*     */     } 
/*     */     
/* 397 */     if (Trace.isOn) {
/* 398 */       Trace.exit("com.ibm.msg.client.wmq.common.internal.Reason", "isImpossibleReason(int,int)", Boolean.FALSE, 4);
/*     */     }
/*     */     
/* 401 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static JMSException reasonToException(int reason, String message, String messageid, String explanation, String useraction, HashMap<String, ? extends Object> inserts) {
/*     */     DetailedIllegalStateException detailedIllegalStateException;
/*     */     DetailedInvalidDestinationException detailedInvalidDestinationException;
/*     */     DetailedInvalidSelectorException detailedInvalidSelectorException;
/*     */     DetailedJMSSecurityException detailedJMSSecurityException;
/*     */     DetailedMessageEOFException detailedMessageEOFException;
/*     */     DetailedMessageFormatException detailedMessageFormatException;
/*     */     DetailedResourceAllocationException detailedResourceAllocationException;
/*     */     DetailedTransactionInProgressException detailedTransactionInProgressException;
/*     */     DetailedTransactionRolledBackException detailedTransactionRolledBackException;
/*     */     DetailedJMSException detailedJMSException;
/* 419 */     if (Trace.isOn) {
/* 420 */       Trace.entry("com.ibm.msg.client.wmq.common.internal.Reason", "reasonToException(int,String,String,String,String,HashMap<String , ? extends Object>)", new Object[] {
/*     */             
/* 422 */             Integer.valueOf(reason), message, messageid, explanation, useraction, inserts
/*     */           });
/*     */     }
/* 425 */     JMSException je = null;
/* 426 */     switch (reason) {
/*     */ 
/*     */       
/*     */       case 2013:
/*     */       case 2014:
/*     */       case 2016:
/*     */       case 2027:
/*     */       case 2029:
/*     */       case 2033:
/*     */       case 2034:
/*     */       case 2040:
/*     */       case 2041:
/*     */       case 2042:
/*     */       case 2047:
/*     */       case 2048:
/*     */       case 2049:
/*     */       case 2050:
/*     */       case 2051:
/*     */       case 2055:
/*     */       case 2059:
/*     */       case 2061:
/*     */       case 2062:
/*     */       case 2069:
/*     */       case 2095:
/*     */       case 2096:
/*     */       case 2097:
/*     */       case 2098:
/*     */       case 2104:
/*     */       case 2106:
/*     */       case 2142:
/*     */       case 2157:
/*     */       case 2160:
/*     */       case 2161:
/*     */       case 2162:
/*     */       case 2185:
/*     */       case 2187:
/*     */       case 2202:
/*     */       case 2203:
/*     */       case 2209:
/*     */       case 2232:
/*     */       case 2249:
/*     */       case 2250:
/*     */       case 2251:
/*     */       case 2252:
/*     */       case 2255:
/*     */       case 2258:
/*     */       case 2259:
/*     */       case 2271:
/*     */       case 2285:
/*     */       case 2298:
/*     */       case 2306:
/*     */       case 2308:
/*     */       case 2317:
/*     */       case 2322:
/*     */       case 2330:
/*     */       case 2344:
/*     */       case 2354:
/*     */       case 2391:
/*     */       case 2429:
/*     */       case 2538:
/*     */       case 6104:
/*     */       case 6105:
/*     */       case 6124:
/* 489 */         detailedIllegalStateException = new DetailedIllegalStateException(message, messageid, explanation, useraction, inserts);
/*     */         break;
/*     */       
/*     */       case 2011:
/*     */       case 2052:
/*     */       case 2053:
/*     */       case 2082:
/*     */       case 2085:
/*     */       case 2100:
/*     */       case 2137:
/*     */       case 2152:
/*     */       case 2153:
/*     */       case 2184:
/*     */       case 2189:
/*     */       case 2196:
/*     */       case 2197:
/*     */       case 2198:
/*     */       case 2201:
/*     */       case 2270:
/*     */       case 2288:
/*     */       case 2290:
/*     */       case 2343:
/*     */       case 2425:
/*     */       case 2428:
/* 513 */         detailedInvalidDestinationException = new DetailedInvalidDestinationException(message, messageid, explanation, useraction, inserts);
/*     */         break;
/*     */       
/*     */       case 2459:
/*     */       case 2504:
/*     */       case 2519:
/*     */       case 2551:
/* 520 */         detailedInvalidSelectorException = new DetailedInvalidSelectorException(message, messageid, explanation, useraction, inserts);
/*     */         break;
/*     */       
/*     */       case 2035:
/*     */       case 2063:
/*     */       case 2217:
/*     */       case 2348:
/*     */       case 2381:
/*     */       case 2382:
/*     */       case 2388:
/*     */       case 2401:
/* 531 */         detailedJMSSecurityException = new DetailedJMSSecurityException(message, messageid, explanation, useraction, inserts);
/*     */         break;
/*     */       
/*     */       case 2079:
/*     */       case 2080:
/*     */       case 2143:
/*     */       case 2144:
/*     */       case 2190:
/* 539 */         detailedMessageEOFException = new DetailedMessageEOFException(message, messageid, explanation, useraction, inserts);
/*     */         break;
/*     */       
/*     */       case 2110:
/*     */       case 2111:
/*     */       case 2112:
/*     */       case 2113:
/*     */       case 2114:
/*     */       case 2115:
/*     */       case 2116:
/*     */       case 2117:
/*     */       case 2118:
/*     */       case 2119:
/*     */       case 2145:
/*     */       case 2146:
/*     */       case 2150:
/*     */       case 2228:
/*     */       case 2243:
/*     */       case 2244:
/*     */       case 2272:
/*     */       case 2364:
/*     */       case 2421:
/* 561 */         detailedMessageFormatException = new DetailedMessageFormatException(message, messageid, explanation, useraction, inserts);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 2017:
/*     */       case 2024:
/*     */       case 2025:
/*     */       case 2056:
/*     */       case 2071:
/*     */       case 2102:
/*     */       case 2127:
/*     */       case 2183:
/*     */       case 2192:
/*     */       case 2204:
/*     */       case 2208:
/*     */       case 2267:
/*     */       case 2269:
/* 578 */         detailedResourceAllocationException = new DetailedResourceAllocationException(message, messageid, explanation, useraction, inserts);
/*     */         break;
/*     */       
/*     */       case 2128:
/*     */       case 2351:
/*     */       case 2352:
/*     */       case 2353:
/*     */       case 2355:
/* 586 */         detailedTransactionInProgressException = new DetailedTransactionInProgressException(message, messageid, explanation, useraction, inserts);
/*     */         break;
/*     */       
/*     */       case 2003:
/*     */       case 2297:
/* 591 */         detailedTransactionRolledBackException = new DetailedTransactionRolledBackException(message, messageid, explanation, useraction, inserts);
/*     */         break;
/*     */       
/*     */       default:
/* 595 */         detailedJMSException = new DetailedJMSException(message, messageid, explanation, useraction, inserts);
/*     */         break;
/*     */     } 
/* 598 */     if (Trace.isOn) {
/* 599 */       Trace.exit("com.ibm.msg.client.wmq.common.internal.Reason", "reasonToException(int,String,String,String,String,HashMap<String , ? extends Object>)", detailedJMSException);
/*     */     }
/*     */ 
/*     */     
/* 603 */     return (JMSException)detailedJMSException;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Reason() {
/* 610 */     if (Trace.isOn) {
/* 611 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.Reason", "<init>()");
/*     */     }
/* 613 */     if (Trace.isOn)
/* 614 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.Reason", "<init>()"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\common\internal\Reason.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */