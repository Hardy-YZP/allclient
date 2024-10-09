/*     */ package com.ibm.mq.jms.admin;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.Map;
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
/*     */ 
/*     */ public class APMCAST
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APMCAST.java";
/*     */   public static final String LONGNAME = "MULTICAST";
/*     */   public static final String SHORTNAME = "MCAST";
/*     */   public static final String MCAST_DISABLED = "DISABLED";
/*     */   public static final String MCAST_ENABLED = "ENABLED";
/*     */   public static final String MCAST_RELIABLE = "RELIABLE";
/*     */   public static final String MCAST_NOT_RELIABLE = "NOTR";
/*     */   public static final String MCAST_ASCF = "ASCF";
/*     */   
/*     */   static {
/*  52 */     if (Trace.isOn) {
/*  53 */       Trace.data("com.ibm.mq.jms.admin.APMCAST", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APMCAST.java");
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
/*     */   public void setObjectFromProperty(Object obj, Map<String, Object> props) throws BAOException, JMSException {
/*  94 */     if (Trace.isOn) {
/*  95 */       Trace.entry(this, "com.ibm.mq.jms.admin.APMCAST", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 101 */     if (Trace.isOn) {
/* 102 */       Trace.exit(this, "com.ibm.mq.jms.admin.APMCAST", "setObjectFromProperty(Object,Map<String , Object>)");
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
/*     */   public void setPropertyFromObject(Map<String, Object> props, Object obj) throws JMSException {
/* 116 */     if (Trace.isOn) {
/* 117 */       Trace.entry(this, "com.ibm.mq.jms.admin.APMCAST", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 123 */     if (Trace.isOn) {
/* 124 */       Trace.exit(this, "com.ibm.mq.jms.admin.APMCAST", "setPropertyFromObject(Map<String , Object>,Object)");
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
/*     */   public String longName() {
/* 137 */     if (Trace.isOn) {
/* 138 */       Trace.entry(this, "com.ibm.mq.jms.admin.APMCAST", "longName()");
/*     */     }
/* 140 */     if (Trace.isOn) {
/* 141 */       Trace.exit(this, "com.ibm.mq.jms.admin.APMCAST", "longName()", "MULTICAST");
/*     */     }
/* 143 */     return "MULTICAST";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 151 */     if (Trace.isOn) {
/* 152 */       Trace.entry(this, "com.ibm.mq.jms.admin.APMCAST", "shortName()");
/*     */     }
/* 154 */     if (Trace.isOn) {
/* 155 */       Trace.exit(this, "com.ibm.mq.jms.admin.APMCAST", "shortName()", "MCAST");
/*     */     }
/* 157 */     return "MCAST";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String valToString(int iVal) throws JMSException {
/* 168 */     if (Trace.isOn)
/* 169 */       Trace.entry("com.ibm.mq.jms.admin.APMCAST", "valToString(int)", new Object[] {
/* 170 */             Integer.valueOf(iVal)
/*     */           }); 
/* 172 */     if (iVal == 0) {
/* 173 */       if (Trace.isOn) {
/* 174 */         Trace.exit("com.ibm.mq.jms.admin.APMCAST", "valToString(int)", "DISABLED", 1);
/*     */       }
/*     */       
/* 177 */       return "DISABLED";
/*     */     } 
/* 179 */     if (iVal == 5) {
/* 180 */       if (Trace.isOn) {
/* 181 */         Trace.exit("com.ibm.mq.jms.admin.APMCAST", "valToString(int)", "RELIABLE", 2);
/*     */       }
/*     */       
/* 184 */       return "RELIABLE";
/*     */     } 
/* 186 */     if (iVal == 3) {
/* 187 */       if (Trace.isOn) {
/* 188 */         Trace.exit("com.ibm.mq.jms.admin.APMCAST", "valToString(int)", "NOTR", 3);
/*     */       }
/*     */       
/* 191 */       return "NOTR";
/*     */     } 
/* 193 */     if (iVal == 7) {
/* 194 */       if (Trace.isOn) {
/* 195 */         Trace.exit("com.ibm.mq.jms.admin.APMCAST", "valToString(int)", "ENABLED", 4);
/*     */       }
/* 197 */       return "ENABLED";
/*     */     } 
/* 199 */     if (iVal == -1) {
/* 200 */       if (Trace.isOn) {
/* 201 */         Trace.exit("com.ibm.mq.jms.admin.APMCAST", "valToString(int)", "ASCF", 5);
/*     */       }
/* 203 */       return "ASCF";
/*     */     } 
/*     */ 
/*     */     
/* 207 */     String key = "JMSADM1016";
/* 208 */     String msg = ConfigEnvironment.getErrorMessage(key, "Unexpected value: " + iVal);
/* 209 */     JMSException traceRet1 = new JMSException(msg, key);
/* 210 */     if (Trace.isOn) {
/* 211 */       Trace.throwing("com.ibm.mq.jms.admin.APMCAST", "valToString(int)", (Throwable)traceRet1);
/*     */     }
/* 213 */     throw traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int stringToVal(String s) throws BAOException {
/* 223 */     if (Trace.isOn) {
/* 224 */       Trace.entry("com.ibm.mq.jms.admin.APMCAST", "stringToVal(String)", new Object[] { s });
/*     */     }
/* 226 */     String str = s.toUpperCase();
/*     */     
/* 228 */     if (str.equals("DISABLED")) {
/* 229 */       if (Trace.isOn) {
/* 230 */         Trace.exit("com.ibm.mq.jms.admin.APMCAST", "stringToVal(String)", 
/* 231 */             Integer.valueOf(0), 1);
/*     */       }
/* 233 */       return 0;
/*     */     } 
/* 235 */     if (str.equals("RELIABLE")) {
/* 236 */       if (Trace.isOn) {
/* 237 */         Trace.exit("com.ibm.mq.jms.admin.APMCAST", "stringToVal(String)", 
/* 238 */             Integer.valueOf(5), 2);
/*     */       }
/* 240 */       return 5;
/*     */     } 
/* 242 */     if (str.equals("NOTR")) {
/* 243 */       if (Trace.isOn) {
/* 244 */         Trace.exit("com.ibm.mq.jms.admin.APMCAST", "stringToVal(String)", 
/* 245 */             Integer.valueOf(3), 3);
/*     */       }
/* 247 */       return 3;
/*     */     } 
/* 249 */     if (str.equals("ENABLED")) {
/* 250 */       if (Trace.isOn) {
/* 251 */         Trace.exit("com.ibm.mq.jms.admin.APMCAST", "stringToVal(String)", 
/* 252 */             Integer.valueOf(7), 4);
/*     */       }
/* 254 */       return 7;
/*     */     } 
/* 256 */     if (str.equals("ASCF")) {
/* 257 */       if (Trace.isOn) {
/* 258 */         Trace.exit("com.ibm.mq.jms.admin.APMCAST", "stringToVal(String)", 
/* 259 */             Integer.valueOf(-1), 5);
/*     */       }
/* 261 */       return -1;
/*     */     } 
/*     */     
/* 264 */     BAOException traceRet1 = new BAOException(4, "MCAST", str);
/* 265 */     if (Trace.isOn) {
/* 266 */       Trace.throwing("com.ibm.mq.jms.admin.APMCAST", "stringToVal(String)", traceRet1);
/*     */     }
/* 268 */     throw traceRet1;
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
/*     */   public static int objToInt(Object value) throws BAOException, JMSException {
/* 282 */     if (Trace.isOn) {
/* 283 */       Trace.entry("com.ibm.mq.jms.admin.APMCAST", "objToInt(Object)", new Object[] { value });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       int iVal;
/*     */       
/* 290 */       if (value instanceof Integer) {
/* 291 */         iVal = ((Integer)value).intValue();
/*     */       
/*     */       }
/* 294 */       else if (value instanceof String) {
/* 295 */         String str = (String)value;
/* 296 */         iVal = stringToVal(str);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 301 */         String detail = "value supplied as an unexpected object type";
/* 302 */         String key = "JMSADM1016";
/* 303 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 304 */         JMSException iee = new JMSException(msg, key);
/* 305 */         if (Trace.isOn) {
/* 306 */           Trace.throwing("com.ibm.mq.jms.admin.APMCAST", "objToInt(Object)", (Throwable)iee);
/*     */         }
/* 308 */         throw iee;
/*     */       } 
/*     */       
/* 311 */       if (Trace.isOn) {
/* 312 */         Trace.exit("com.ibm.mq.jms.admin.APMCAST", "objToInt(Object)", Integer.valueOf(iVal));
/*     */       }
/* 314 */       return iVal;
/*     */     }
/*     */     finally {
/*     */       
/* 318 */       if (Trace.isOn)
/* 319 */         Trace.finallyBlock("com.ibm.mq.jms.admin.APMCAST", "objToInt(Object)"); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APMCAST.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */