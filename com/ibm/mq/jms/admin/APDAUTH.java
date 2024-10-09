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
/*     */ public class APDAUTH
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APDAUTH.java";
/*     */   public static final String LONGNAME = "DIRECTAUTH";
/*     */   public static final String SHORTNAME = "DAUTH";
/*     */   public static final String DAUTH_BASIC = "BASIC";
/*     */   public static final String DAUTH_CERTIFICATE = "CERTIFICATE";
/*     */   
/*     */   static {
/*  48 */     if (Trace.isOn) {
/*  49 */       Trace.data("com.ibm.mq.jms.admin.APDAUTH", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APDAUTH.java");
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
/*     */   public void setObjectFromProperty(Object obj, Map<String, Object> props) throws BAOException, JMSException {
/*  84 */     if (Trace.isOn) {
/*  85 */       Trace.entry(this, "com.ibm.mq.jms.admin.APDAUTH", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  91 */     if (Trace.isOn) {
/*  92 */       Trace.exit(this, "com.ibm.mq.jms.admin.APDAUTH", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 106 */     if (Trace.isOn) {
/* 107 */       Trace.entry(this, "com.ibm.mq.jms.admin.APDAUTH", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 113 */     if (Trace.isOn) {
/* 114 */       Trace.exit(this, "com.ibm.mq.jms.admin.APDAUTH", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 127 */     if (Trace.isOn) {
/* 128 */       Trace.entry(this, "com.ibm.mq.jms.admin.APDAUTH", "longName()");
/*     */     }
/* 130 */     if (Trace.isOn) {
/* 131 */       Trace.exit(this, "com.ibm.mq.jms.admin.APDAUTH", "longName()", "DIRECTAUTH");
/*     */     }
/* 133 */     return "DIRECTAUTH";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 141 */     if (Trace.isOn) {
/* 142 */       Trace.entry(this, "com.ibm.mq.jms.admin.APDAUTH", "shortName()");
/*     */     }
/* 144 */     if (Trace.isOn) {
/* 145 */       Trace.exit(this, "com.ibm.mq.jms.admin.APDAUTH", "shortName()", "DAUTH");
/*     */     }
/* 147 */     return "DAUTH";
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
/* 158 */     if (Trace.isOn)
/* 159 */       Trace.entry("com.ibm.mq.jms.admin.APDAUTH", "valToString(int)", new Object[] {
/* 160 */             Integer.valueOf(iVal)
/*     */           }); 
/* 162 */     if (iVal == 0) {
/* 163 */       if (Trace.isOn) {
/* 164 */         Trace.exit("com.ibm.mq.jms.admin.APDAUTH", "valToString(int)", "BASIC", 1);
/*     */       }
/* 166 */       return "BASIC";
/*     */     } 
/* 168 */     if (iVal == 1) {
/* 169 */       if (Trace.isOn) {
/* 170 */         Trace.exit("com.ibm.mq.jms.admin.APDAUTH", "valToString(int)", "CERTIFICATE", 2);
/*     */       }
/*     */       
/* 173 */       return "CERTIFICATE";
/*     */     } 
/*     */     
/* 176 */     String key = "JMSADM1016";
/* 177 */     String msg = ConfigEnvironment.getErrorMessage(key, "Unexpected value: " + iVal);
/* 178 */     JMSException traceRet1 = new JMSException(msg, key);
/* 179 */     if (Trace.isOn) {
/* 180 */       Trace.throwing("com.ibm.mq.jms.admin.APDAUTH", "valToString(int)", (Throwable)traceRet1);
/*     */     }
/* 182 */     throw traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int stringToVal(String s) throws BAOException {
/* 192 */     if (Trace.isOn) {
/* 193 */       Trace.entry("com.ibm.mq.jms.admin.APDAUTH", "stringToVal(String)", new Object[] { s });
/*     */     }
/* 195 */     String str = s.toUpperCase();
/* 196 */     if (str.equals("BASIC")) {
/* 197 */       if (Trace.isOn) {
/* 198 */         Trace.exit("com.ibm.mq.jms.admin.APDAUTH", "stringToVal(String)", 
/* 199 */             Integer.valueOf(0), 1);
/*     */       }
/* 201 */       return 0;
/*     */     } 
/* 203 */     if (str.equals("CERTIFICATE")) {
/* 204 */       if (Trace.isOn) {
/* 205 */         Trace.exit("com.ibm.mq.jms.admin.APDAUTH", "stringToVal(String)", 
/* 206 */             Integer.valueOf(1), 2);
/*     */       }
/* 208 */       return 1;
/*     */     } 
/*     */     
/* 211 */     BAOException traceRet1 = new BAOException(4, "DAUTH", str);
/* 212 */     if (Trace.isOn) {
/* 213 */       Trace.throwing("com.ibm.mq.jms.admin.APDAUTH", "stringToVal(String)", traceRet1);
/*     */     }
/* 215 */     throw traceRet1;
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
/*     */   public static int objToInt(Object value) throws BAOException, JMSException {
/* 230 */     if (Trace.isOn) {
/* 231 */       Trace.entry("com.ibm.mq.jms.admin.APDAUTH", "objToInt(Object)", new Object[] { value });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       int iVal;
/*     */       
/* 238 */       if (value instanceof Integer) {
/* 239 */         iVal = ((Integer)value).intValue();
/*     */       
/*     */       }
/* 242 */       else if (value instanceof String) {
/* 243 */         String str = (String)value;
/* 244 */         iVal = stringToVal(str);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 249 */         String detail = "value supplied as an unexpected object type";
/* 250 */         String key = "JMSADM1016";
/* 251 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 252 */         JMSException iee = new JMSException(msg, key);
/* 253 */         if (Trace.isOn) {
/* 254 */           Trace.throwing("com.ibm.mq.jms.admin.APDAUTH", "objToInt(Object)", (Throwable)iee);
/*     */         }
/* 256 */         throw iee;
/*     */       } 
/*     */       
/* 259 */       if (Trace.isOn) {
/* 260 */         Trace.exit("com.ibm.mq.jms.admin.APDAUTH", "objToInt(Object)", Integer.valueOf(iVal));
/*     */       }
/* 262 */       return iVal;
/*     */     }
/*     */     finally {
/*     */       
/* 266 */       if (Trace.isOn)
/* 267 */         Trace.finallyBlock("com.ibm.mq.jms.admin.APDAUTH", "objToInt(Object)"); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APDAUTH.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */