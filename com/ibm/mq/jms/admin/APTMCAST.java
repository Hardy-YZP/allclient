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
/*     */ public class APTMCAST
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APTMCAST.java";
/*     */   public static final String LONGNAME = "MULTICAST";
/*     */   public static final String SHORTNAME = "MCAST";
/*     */   public static final String MCAST_DISABLED = "DISABLED";
/*     */   public static final String MCAST_ASCF = "ASCF";
/*     */   public static final String MCAST_ENABLED = "ENABLED";
/*     */   public static final String MCAST_RELIABLE = "RELIABLE";
/*     */   public static final String MCAST_NOT_RELIABLE = "NOTR";
/*     */   public static final String MCAST_AS_CF = "AS_CF";
/*     */   
/*     */   static {
/*  52 */     if (Trace.isOn) {
/*  53 */       Trace.data("com.ibm.mq.jms.admin.APTMCAST", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APTMCAST.java");
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
/*     */   
/*     */   public void setObjectFromProperty(Object obj, Map<String, Object> props) throws BAOException, JMSException {
/*  98 */     if (Trace.isOn) {
/*  99 */       Trace.entry(this, "com.ibm.mq.jms.admin.APTMCAST", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 106 */     if (Trace.isOn) {
/* 107 */       Trace.exit(this, "com.ibm.mq.jms.admin.APTMCAST", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 121 */     if (Trace.isOn) {
/* 122 */       Trace.entry(this, "com.ibm.mq.jms.admin.APTMCAST", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 129 */     if (Trace.isOn) {
/* 130 */       Trace.exit(this, "com.ibm.mq.jms.admin.APTMCAST", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 143 */     if (Trace.isOn) {
/* 144 */       Trace.entry(this, "com.ibm.mq.jms.admin.APTMCAST", "longName()");
/*     */     }
/* 146 */     if (Trace.isOn) {
/* 147 */       Trace.exit(this, "com.ibm.mq.jms.admin.APTMCAST", "longName()", "MULTICAST");
/*     */     }
/* 149 */     return "MULTICAST";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 157 */     if (Trace.isOn) {
/* 158 */       Trace.entry(this, "com.ibm.mq.jms.admin.APTMCAST", "shortName()");
/*     */     }
/* 160 */     if (Trace.isOn) {
/* 161 */       Trace.exit(this, "com.ibm.mq.jms.admin.APTMCAST", "shortName()", "MCAST");
/*     */     }
/* 163 */     return "MCAST";
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
/* 174 */     if (Trace.isOn)
/* 175 */       Trace.entry("com.ibm.mq.jms.admin.APTMCAST", "valToString(int)", new Object[] {
/* 176 */             Integer.valueOf(iVal)
/*     */           }); 
/* 178 */     if (iVal == 0) {
/* 179 */       if (Trace.isOn) {
/* 180 */         Trace.exit("com.ibm.mq.jms.admin.APTMCAST", "valToString(int)", "DISABLED", 1);
/*     */       }
/*     */       
/* 183 */       return "DISABLED";
/*     */     } 
/* 185 */     if (iVal == -1) {
/* 186 */       if (Trace.isOn) {
/* 187 */         Trace.exit("com.ibm.mq.jms.admin.APTMCAST", "valToString(int)", "ASCF", 2);
/*     */       }
/* 189 */       return "ASCF";
/*     */     } 
/* 191 */     if (iVal == 5) {
/* 192 */       if (Trace.isOn) {
/* 193 */         Trace.exit("com.ibm.mq.jms.admin.APTMCAST", "valToString(int)", "RELIABLE", 3);
/*     */       }
/*     */       
/* 196 */       return "RELIABLE";
/*     */     } 
/* 198 */     if (iVal == 3) {
/* 199 */       if (Trace.isOn) {
/* 200 */         Trace.exit("com.ibm.mq.jms.admin.APTMCAST", "valToString(int)", "NOTR", 4);
/*     */       }
/*     */       
/* 203 */       return "NOTR";
/*     */     } 
/* 205 */     if (iVal == 7) {
/* 206 */       if (Trace.isOn) {
/* 207 */         Trace.exit("com.ibm.mq.jms.admin.APTMCAST", "valToString(int)", "ENABLED", 5);
/*     */       }
/*     */       
/* 210 */       return "ENABLED";
/*     */     } 
/*     */     
/* 213 */     String key = "JMSADM1016";
/* 214 */     String msg = ConfigEnvironment.getErrorMessage(key, "Unexpected value: " + iVal);
/* 215 */     JMSException traceRet1 = new JMSException(msg, key);
/* 216 */     if (Trace.isOn) {
/* 217 */       Trace.throwing("com.ibm.mq.jms.admin.APTMCAST", "valToString(int)", (Throwable)traceRet1);
/*     */     }
/* 219 */     throw traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int stringToVal(String s) throws BAOException {
/* 229 */     if (Trace.isOn) {
/* 230 */       Trace.entry("com.ibm.mq.jms.admin.APTMCAST", "stringToVal(String)", new Object[] { s });
/*     */     }
/* 232 */     String str = s.toUpperCase();
/*     */     
/* 234 */     if (str.equals("DISABLED")) {
/* 235 */       if (Trace.isOn) {
/* 236 */         Trace.exit("com.ibm.mq.jms.admin.APTMCAST", "stringToVal(String)", 
/* 237 */             Integer.valueOf(0), 1);
/*     */       }
/* 239 */       return 0;
/*     */     } 
/* 241 */     if (str.equals("ASCF") || str.equals("AS_CF")) {
/* 242 */       if (Trace.isOn) {
/* 243 */         Trace.exit("com.ibm.mq.jms.admin.APTMCAST", "stringToVal(String)", 
/* 244 */             Integer.valueOf(-1), 2);
/*     */       }
/* 246 */       return -1;
/*     */     } 
/* 248 */     if (str.equals("RELIABLE")) {
/* 249 */       if (Trace.isOn) {
/* 250 */         Trace.exit("com.ibm.mq.jms.admin.APTMCAST", "stringToVal(String)", 
/* 251 */             Integer.valueOf(5), 3);
/*     */       }
/* 253 */       return 5;
/*     */     } 
/* 255 */     if (str.equals("NOTR")) {
/* 256 */       if (Trace.isOn) {
/* 257 */         Trace.exit("com.ibm.mq.jms.admin.APTMCAST", "stringToVal(String)", 
/* 258 */             Integer.valueOf(3), 4);
/*     */       }
/* 260 */       return 3;
/*     */     } 
/* 262 */     if (str.equals("ENABLED")) {
/* 263 */       if (Trace.isOn) {
/* 264 */         Trace.exit("com.ibm.mq.jms.admin.APTMCAST", "stringToVal(String)", 
/* 265 */             Integer.valueOf(7), 5);
/*     */       }
/* 267 */       return 7;
/*     */     } 
/*     */     
/* 270 */     BAOException traceRet1 = new BAOException(4, "MCAST", str);
/* 271 */     if (Trace.isOn) {
/* 272 */       Trace.throwing("com.ibm.mq.jms.admin.APTMCAST", "stringToVal(String)", traceRet1);
/*     */     }
/* 274 */     throw traceRet1;
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
/*     */   public static int objToInt(Object value) throws BAOException, JMSException {
/* 287 */     if (Trace.isOn) {
/* 288 */       Trace.entry("com.ibm.mq.jms.admin.APTMCAST", "objToInt(Object)", new Object[] { value });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       int iVal;
/*     */       
/* 295 */       if (value instanceof Integer) {
/* 296 */         iVal = ((Integer)value).intValue();
/*     */       
/*     */       }
/* 299 */       else if (value instanceof String) {
/* 300 */         String str = (String)value;
/* 301 */         iVal = stringToVal(str);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 306 */         String detail = "value supplied as an unexpected object type";
/* 307 */         String key = "JMSADM1016";
/* 308 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 309 */         JMSException iee = new JMSException(msg, key);
/* 310 */         if (Trace.isOn) {
/* 311 */           Trace.throwing("com.ibm.mq.jms.admin.APTMCAST", "objToInt(Object)", (Throwable)iee);
/*     */         }
/* 313 */         throw iee;
/*     */       } 
/*     */       
/* 316 */       if (Trace.isOn) {
/* 317 */         Trace.exit("com.ibm.mq.jms.admin.APTMCAST", "objToInt(Object)", Integer.valueOf(iVal));
/*     */       }
/* 319 */       return iVal;
/*     */     }
/*     */     finally {
/*     */       
/* 323 */       if (Trace.isOn)
/* 324 */         Trace.finallyBlock("com.ibm.mq.jms.admin.APTMCAST", "objToInt(Object)"); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APTMCAST.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */