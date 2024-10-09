/*     */ package com.ibm.mq.jms.admin;
/*     */ 
/*     */ import com.ibm.mq.jms.MQConnectionFactory;
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
/*     */ public class APTCM
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APTCM.java";
/*     */   public static final String LONGNAME = "TARGCLIENTMATCHING";
/*     */   public static final String SHORTNAME = "TCM";
/*     */   
/*     */   static {
/*  46 */     if (Trace.isOn) {
/*  47 */       Trace.data("com.ibm.mq.jms.admin.APTCM", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APTCM.java");
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
/*     */   public static String valToString(boolean bVal) throws JMSException {
/*  70 */     if (Trace.isOn)
/*  71 */       Trace.entry("com.ibm.mq.jms.admin.APTCM", "valToString(boolean)", new Object[] {
/*  72 */             Boolean.valueOf(bVal)
/*     */           }); 
/*  74 */     String sVal = bVal ? "YES" : "NO";
/*     */     
/*  76 */     if (Trace.isOn) {
/*  77 */       Trace.exit("com.ibm.mq.jms.admin.APTCM", "valToString(boolean)", sVal);
/*     */     }
/*  79 */     return sVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean stringToVal(String s) throws BAOException {
/*     */     boolean bVal;
/*  91 */     if (Trace.isOn) {
/*  92 */       Trace.entry("com.ibm.mq.jms.admin.APTCM", "stringToVal(String)", new Object[] { s });
/*     */     }
/*     */     
/*  95 */     String str = s.toUpperCase();
/*     */     
/*  97 */     if (str.equals("NO")) {
/*  98 */       bVal = false;
/*     */     }
/* 100 */     else if (str.equals("YES")) {
/* 101 */       bVal = true;
/*     */     } else {
/*     */       
/* 104 */       BAOException traceRet1 = new BAOException(4, "TCM", str);
/* 105 */       if (Trace.isOn) {
/* 106 */         Trace.throwing("com.ibm.mq.jms.admin.APTCM", "stringToVal(String)", traceRet1);
/*     */       }
/* 108 */       throw traceRet1;
/*     */     } 
/*     */     
/* 111 */     if (Trace.isOn) {
/* 112 */       Trace.exit("com.ibm.mq.jms.admin.APTCM", "stringToVal(String)", Boolean.valueOf(bVal));
/*     */     }
/* 114 */     return bVal;
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
/*     */   public void setObjectFromProperty(Object obj, Map<String, Object> props) throws BAOException, JMSException {
/* 127 */     if (Trace.isOn) {
/* 128 */       Trace.entry(this, "com.ibm.mq.jms.admin.APTCM", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 134 */       Object value = getProperty("TCM", props);
/*     */       
/* 136 */       boolean bVal = false;
/*     */       
/* 138 */       if (value != null) {
/* 139 */         if (value instanceof Boolean) {
/* 140 */           bVal = ((Boolean)value).booleanValue();
/*     */         } else {
/*     */           
/* 143 */           bVal = stringToVal((String)value);
/*     */         } 
/*     */         
/* 146 */         if (obj instanceof MQConnectionFactory)
/*     */         {
/*     */           
/* 149 */           ((MQConnectionFactory)obj).setTargetClientMatching(bVal);
/*     */         }
/*     */         else
/*     */         {
/* 153 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 154 */           String key = "JMSADM1016";
/* 155 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 156 */           JMSException iee = new JMSException(msg, key);
/* 157 */           if (Trace.isOn) {
/* 158 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APTCM", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 1);
/*     */           }
/*     */           
/* 161 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 166 */     } catch (JMSException e) {
/* 167 */       if (Trace.isOn) {
/* 168 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APTCM", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e);
/*     */       }
/*     */       
/* 171 */       if (Trace.isOn) {
/* 172 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APTCM", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 2);
/*     */       }
/*     */       
/* 175 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 179 */       if (Trace.isOn) {
/* 180 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APTCM", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 184 */     if (Trace.isOn) {
/* 185 */       Trace.exit(this, "com.ibm.mq.jms.admin.APTCM", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 199 */     if (Trace.isOn) {
/* 200 */       Trace.entry(this, "com.ibm.mq.jms.admin.APTCM", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       String sVal;
/*     */       
/* 207 */       if (obj instanceof MQConnectionFactory) {
/* 208 */         boolean bVal = ((MQConnectionFactory)obj).getTargetClientMatching();
/* 209 */         sVal = valToString(bVal);
/*     */       }
/*     */       else {
/*     */         
/* 213 */         String detail = "object is an unexpected type";
/* 214 */         String key = "JMSADM1016";
/* 215 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 216 */         JMSException iee = new JMSException(msg, key);
/* 217 */         if (Trace.isOn) {
/* 218 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APTCM", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 221 */         throw iee;
/*     */       } 
/*     */       
/* 224 */       if (sVal != null)
/*     */       {
/* 226 */         props.put("TARGCLIENTMATCHING", sVal);
/*     */       
/*     */       }
/*     */     }
/*     */     finally {
/*     */       
/* 232 */       if (Trace.isOn) {
/* 233 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APTCM", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 238 */     if (Trace.isOn) {
/* 239 */       Trace.exit(this, "com.ibm.mq.jms.admin.APTCM", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 253 */     if (Trace.isOn) {
/* 254 */       Trace.entry(this, "com.ibm.mq.jms.admin.APTCM", "longName()");
/*     */     }
/* 256 */     if (Trace.isOn) {
/* 257 */       Trace.exit(this, "com.ibm.mq.jms.admin.APTCM", "longName()", "TARGCLIENTMATCHING");
/*     */     }
/* 259 */     return "TARGCLIENTMATCHING";
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
/* 270 */     if (Trace.isOn) {
/* 271 */       Trace.entry(this, "com.ibm.mq.jms.admin.APTCM", "shortName()");
/*     */     }
/* 273 */     if (Trace.isOn) {
/* 274 */       Trace.exit(this, "com.ibm.mq.jms.admin.APTCM", "shortName()", "TCM");
/*     */     }
/* 276 */     return "TCM";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APTCM.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */