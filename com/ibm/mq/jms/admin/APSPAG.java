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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class APSPAG
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APSPAG.java";
/*     */   public static final String LONGNAME = "SYNCPOINTALLGETS";
/*     */   public static final String SHORTNAME = "SPAG";
/*     */   
/*     */   static {
/*  49 */     if (Trace.isOn) {
/*  50 */       Trace.data("com.ibm.mq.jms.admin.APSPAG", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APSPAG.java");
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
/*     */   public static String valToString(boolean spag) throws JMSException {
/*     */     String sVal;
/*  72 */     if (Trace.isOn) {
/*  73 */       Trace.entry("com.ibm.mq.jms.admin.APSPAG", "valToString(boolean)", new Object[] {
/*  74 */             Boolean.valueOf(spag)
/*     */           });
/*     */     }
/*     */     
/*  78 */     if (spag) {
/*  79 */       sVal = "YES";
/*     */     } else {
/*  81 */       sVal = "NO";
/*     */     } 
/*     */     
/*  84 */     if (Trace.isOn) {
/*  85 */       Trace.exit("com.ibm.mq.jms.admin.APSPAG", "valToString(boolean)", sVal);
/*     */     }
/*  87 */     return sVal;
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
/*  99 */     if (Trace.isOn) {
/* 100 */       Trace.entry("com.ibm.mq.jms.admin.APSPAG", "stringToVal(String)", new Object[] { s });
/*     */     }
/*     */     
/* 103 */     String str = s.toUpperCase();
/*     */     
/* 105 */     if (str.equals("NO")) {
/* 106 */       bVal = false;
/* 107 */     } else if (str.equals("YES")) {
/* 108 */       bVal = true;
/*     */     } else {
/* 110 */       BAOException traceRet1 = new BAOException(4, "SPAG", str);
/* 111 */       if (Trace.isOn) {
/* 112 */         Trace.throwing("com.ibm.mq.jms.admin.APSPAG", "stringToVal(String)", traceRet1);
/*     */       }
/* 114 */       throw traceRet1;
/*     */     } 
/*     */     
/* 117 */     if (Trace.isOn) {
/* 118 */       Trace.exit("com.ibm.mq.jms.admin.APSPAG", "stringToVal(String)", Boolean.valueOf(bVal));
/*     */     }
/* 120 */     return bVal;
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
/* 134 */     if (Trace.isOn) {
/* 135 */       Trace.entry(this, "com.ibm.mq.jms.admin.APSPAG", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 141 */       Object value = getProperty("SPAG", props);
/*     */ 
/*     */       
/* 144 */       if (value != null) {
/*     */         boolean bVal;
/* 146 */         if (value instanceof Integer) {
/* 147 */           int intVal = ((Integer)value).intValue();
/* 148 */           bVal = (intVal == 1);
/* 149 */         } else if (value instanceof Boolean) {
/* 150 */           bVal = ((Boolean)value).booleanValue();
/*     */         } else {
/* 152 */           bVal = stringToVal((String)value);
/*     */         } 
/*     */         
/* 155 */         if (obj instanceof MQConnectionFactory) {
/* 156 */           ((MQConnectionFactory)obj).setSyncpointAllGets(bVal);
/*     */         }
/*     */         else {
/*     */           
/* 160 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 161 */           String key = "JMSADM1016";
/* 162 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 163 */           JMSException iee = new JMSException(msg, key);
/* 164 */           if (Trace.isOn) {
/* 165 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APSPAG", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 1);
/*     */           }
/*     */           
/* 168 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 173 */     } catch (JMSException e) {
/* 174 */       if (Trace.isOn) {
/* 175 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APSPAG", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e);
/*     */       }
/*     */       
/* 178 */       if (Trace.isOn) {
/* 179 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APSPAG", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 2);
/*     */       }
/*     */       
/* 182 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 186 */       if (Trace.isOn) {
/* 187 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APSPAG", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 191 */     if (Trace.isOn) {
/* 192 */       Trace.exit(this, "com.ibm.mq.jms.admin.APSPAG", "setObjectFromProperty(Object,Map<String , Object>)");
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
/*     */   public void setPropertyFromObject(Map<String, Object> props, Object obj) throws JMSException {
/* 205 */     if (Trace.isOn) {
/* 206 */       Trace.entry(this, "com.ibm.mq.jms.admin.APSPAG", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       String sVal;
/*     */       
/* 213 */       if (obj instanceof MQConnectionFactory) {
/* 214 */         boolean spag = ((MQConnectionFactory)obj).getSyncpointAllGets();
/* 215 */         sVal = valToString(spag);
/*     */       }
/*     */       else {
/*     */         
/* 219 */         String detail = "object is an unexpected type";
/* 220 */         String key = "JMSADM1016";
/* 221 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 222 */         JMSException iee = new JMSException(msg, key);
/* 223 */         if (Trace.isOn) {
/* 224 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APSPAG", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 227 */         throw iee;
/*     */       } 
/*     */       
/* 230 */       if (sVal != null)
/*     */       {
/* 232 */         props.put("SYNCPOINTALLGETS", sVal);
/*     */       }
/*     */     }
/*     */     finally {
/*     */       
/* 237 */       if (Trace.isOn) {
/* 238 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APSPAG", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 243 */     if (Trace.isOn) {
/* 244 */       Trace.exit(this, "com.ibm.mq.jms.admin.APSPAG", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 258 */     if (Trace.isOn) {
/* 259 */       Trace.entry(this, "com.ibm.mq.jms.admin.APSPAG", "longName()");
/*     */     }
/* 261 */     if (Trace.isOn) {
/* 262 */       Trace.exit(this, "com.ibm.mq.jms.admin.APSPAG", "longName()", "SYNCPOINTALLGETS");
/*     */     }
/* 264 */     return "SYNCPOINTALLGETS";
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
/* 275 */     if (Trace.isOn) {
/* 276 */       Trace.entry(this, "com.ibm.mq.jms.admin.APSPAG", "shortName()");
/*     */     }
/* 278 */     if (Trace.isOn) {
/* 279 */       Trace.exit(this, "com.ibm.mq.jms.admin.APSPAG", "shortName()", "SPAG");
/*     */     }
/* 281 */     return "SPAG";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APSPAG.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */