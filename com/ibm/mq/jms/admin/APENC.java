/*     */ package com.ibm.mq.jms.admin;
/*     */ 
/*     */ import com.ibm.mq.jms.MQDestination;
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
/*     */ public class APENC
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APENC.java";
/*     */   public static final String LONGNAME = "ENCODING";
/*     */   public static final String SHORTNAME = "ENC";
/*     */   public static final String NATIVE = "NATIVE";
/*     */   public static final char NORMAL = 'N';
/*     */   public static final char REVERSED = 'R';
/*     */   public static final char S390 = '3';
/*     */   public static final char UNKNOWN = '?';
/*     */   
/*     */   static {
/*  49 */     if (Trace.isOn) {
/*  50 */       Trace.data("com.ibm.mq.jms.admin.APENC", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APENC.java");
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
/*     */   public static String valToString(int iVal) throws JMSException {
/*  89 */     if (Trace.isOn)
/*  90 */       Trace.entry("com.ibm.mq.jms.admin.APENC", "valToString(int)", new Object[] {
/*  91 */             Integer.valueOf(iVal)
/*     */           }); 
/*  93 */     StringBuffer strBuf = new StringBuffer();
/*  94 */     if (iVal == 273) {
/*  95 */       strBuf.append("NATIVE");
/*     */     } else {
/*     */       
/*  98 */       if ((iVal & 0x1) != 0) {
/*  99 */         strBuf.append('N');
/*     */       }
/* 101 */       else if ((iVal & 0x2) != 0) {
/* 102 */         strBuf.append('R');
/*     */       } else {
/*     */         
/* 105 */         strBuf.append('?');
/*     */       } 
/*     */       
/* 108 */       if ((iVal & 0x10) != 0) {
/* 109 */         strBuf.append('N');
/*     */       }
/* 111 */       else if ((iVal & 0x20) != 0) {
/* 112 */         strBuf.append('R');
/*     */       } else {
/*     */         
/* 115 */         strBuf.append('?');
/*     */       } 
/*     */       
/* 118 */       if ((iVal & 0x300) == 768) {
/* 119 */         strBuf.append('3');
/*     */       }
/* 121 */       else if ((iVal & 0x100) == 256) {
/* 122 */         strBuf.append('N');
/*     */       }
/* 124 */       else if ((iVal & 0x200) == 512) {
/* 125 */         strBuf.append('R');
/*     */       } else {
/*     */         
/* 128 */         strBuf.append('?');
/*     */       } 
/*     */     } 
/*     */     
/* 132 */     String traceRet1 = strBuf.toString();
/* 133 */     if (Trace.isOn) {
/* 134 */       Trace.exit("com.ibm.mq.jms.admin.APENC", "valToString(int)", traceRet1);
/*     */     }
/* 136 */     return traceRet1;
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
/*     */   public static int stringToVal(String s) throws BAOException {
/*     */     int iVal;
/* 149 */     if (Trace.isOn) {
/* 150 */       Trace.entry("com.ibm.mq.jms.admin.APENC", "stringToVal(String)", new Object[] { s });
/*     */     }
/*     */     
/* 153 */     String str = s.toUpperCase();
/*     */     
/* 155 */     if (str.equals("NATIVE")) {
/* 156 */       iVal = 273;
/*     */     }
/* 158 */     else if (str.length() == 3) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 163 */       iVal = 0;
/* 164 */       if (str.charAt(0) == 'N') {
/* 165 */         iVal |= 0x1;
/*     */       }
/* 167 */       else if (str.charAt(0) == 'R') {
/* 168 */         iVal |= 0x2;
/*     */       } 
/*     */       
/* 171 */       if (str.charAt(1) == 'N') {
/* 172 */         iVal |= 0x10;
/*     */       }
/* 174 */       else if (str.charAt(1) == 'R') {
/* 175 */         iVal |= 0x20;
/*     */       } 
/*     */       
/* 178 */       if (str.charAt(2) == 'N') {
/* 179 */         iVal |= 0x100;
/*     */       }
/* 181 */       else if (str.charAt(2) == 'R') {
/* 182 */         iVal |= 0x200;
/*     */       }
/* 184 */       else if (str.charAt(2) == '3') {
/* 185 */         iVal |= 0x300;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 190 */       BAOException traceRet1 = new BAOException(4, "ENC", str);
/* 191 */       if (Trace.isOn) {
/* 192 */         Trace.throwing("com.ibm.mq.jms.admin.APENC", "stringToVal(String)", traceRet1);
/*     */       }
/* 194 */       throw traceRet1;
/*     */     } 
/*     */     
/* 197 */     if (Trace.isOn) {
/* 198 */       Trace.exit("com.ibm.mq.jms.admin.APENC", "stringToVal(String)", Integer.valueOf(iVal));
/*     */     }
/* 200 */     return iVal;
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
/*     */   public void setObjectFromProperty(Object obj, Map<String, Object> props) throws BAOException, JMSException {
/* 214 */     if (Trace.isOn) {
/* 215 */       Trace.entry(this, "com.ibm.mq.jms.admin.APENC", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 221 */       Object value = getProperty("ENC", props);
/*     */ 
/*     */       
/* 224 */       if (value != null) {
/* 225 */         int iVal; if (value instanceof Integer) {
/* 226 */           iVal = ((Integer)value).intValue();
/*     */         }
/*     */         else {
/*     */           
/* 230 */           iVal = stringToVal((String)value);
/*     */         } 
/*     */ 
/*     */         
/* 234 */         if (obj instanceof MQDestination) {
/*     */           try {
/* 236 */             ((MQDestination)obj).setEncoding(iVal);
/*     */           }
/* 238 */           catch (JMSException e) {
/* 239 */             if (Trace.isOn) {
/* 240 */               Trace.catchBlock(this, "com.ibm.mq.jms.admin.APENC", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 1);
/*     */             }
/*     */ 
/*     */             
/* 244 */             BAOException be = new BAOException(4, "ENC", Integer.toString(iVal));
/* 245 */             if (Trace.isOn) {
/* 246 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APENC", "setObjectFromProperty(Object,Map<String , Object>)", be, 1);
/*     */             }
/*     */             
/* 249 */             throw be;
/*     */           }
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 255 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 256 */           String key = "JMSADM1016";
/* 257 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 258 */           JMSException iee = new JMSException(msg, key);
/* 259 */           if (Trace.isOn) {
/* 260 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APENC", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 2);
/*     */           }
/*     */           
/* 263 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 268 */     } catch (BAOException e) {
/* 269 */       if (Trace.isOn) {
/* 270 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APENC", "setObjectFromProperty(Object,Map<String , Object>)", e, 2);
/*     */       }
/*     */       
/* 273 */       if (Trace.isOn) {
/* 274 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APENC", "setObjectFromProperty(Object,Map<String , Object>)", e, 3);
/*     */       }
/*     */       
/* 277 */       throw e;
/*     */     
/*     */     }
/* 280 */     catch (JMSException e) {
/* 281 */       if (Trace.isOn) {
/* 282 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APENC", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 3);
/*     */       }
/*     */       
/* 285 */       if (Trace.isOn) {
/* 286 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APENC", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 4);
/*     */       }
/*     */       
/* 289 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 293 */       if (Trace.isOn) {
/* 294 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APENC", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 298 */     if (Trace.isOn) {
/* 299 */       Trace.exit(this, "com.ibm.mq.jms.admin.APENC", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 313 */     if (Trace.isOn) {
/* 314 */       Trace.entry(this, "com.ibm.mq.jms.admin.APENC", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       int iVal;
/*     */       
/* 321 */       if (obj instanceof MQDestination) {
/* 322 */         iVal = ((MQDestination)obj).getEncoding();
/*     */       }
/*     */       else {
/*     */         
/* 326 */         String detail = "object is an unexpected type";
/* 327 */         String key = "JMSADM1016";
/* 328 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 329 */         JMSException iee = new JMSException(msg, key);
/* 330 */         if (Trace.isOn) {
/* 331 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APENC", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 334 */         throw iee;
/*     */       } 
/*     */ 
/*     */       
/* 338 */       String value = valToString(iVal);
/*     */ 
/*     */       
/* 341 */       props.put("ENCODING", value);
/*     */     }
/*     */     finally {
/*     */       
/* 345 */       if (Trace.isOn) {
/* 346 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APENC", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 351 */     if (Trace.isOn) {
/* 352 */       Trace.exit(this, "com.ibm.mq.jms.admin.APENC", "setPropertyFromObject(Map<String , Object>,Object)");
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
/*     */   public String longName() {
/* 366 */     if (Trace.isOn) {
/* 367 */       Trace.entry(this, "com.ibm.mq.jms.admin.APENC", "longName()");
/*     */     }
/* 369 */     if (Trace.isOn) {
/* 370 */       Trace.exit(this, "com.ibm.mq.jms.admin.APENC", "longName()", "ENCODING");
/*     */     }
/* 372 */     return "ENCODING";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 383 */     if (Trace.isOn) {
/* 384 */       Trace.entry(this, "com.ibm.mq.jms.admin.APENC", "shortName()");
/*     */     }
/* 386 */     if (Trace.isOn) {
/* 387 */       Trace.exit(this, "com.ibm.mq.jms.admin.APENC", "shortName()", "ENC");
/*     */     }
/* 389 */     return "ENC";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APENC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */