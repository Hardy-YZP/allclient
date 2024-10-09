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
/*     */ 
/*     */ public class APMNST
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APMNST.java";
/*     */   private static final String CLASSNAME = "APMNST";
/*     */   public static final String LONGNAME = "MAPNAMESTYLE";
/*     */   public static final String SHORTNAME = "MNST";
/*     */   public static final String MS_STANDARD = "STANDARD";
/*     */   public static final String MS_COMPATIBLE = "COMPATIBLE";
/*     */   
/*     */   static {
/*  53 */     if (Trace.isOn) {
/*  54 */       Trace.data("com.ibm.mq.jms.admin.APMNST", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APMNST.java");
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
/*     */   public void setObjectFromProperty(Object obj, Map<String, Object> props) throws BAOException, JMSException {
/*  88 */     if (Trace.isOn) {
/*  89 */       Trace.entry(this, "com.ibm.mq.jms.admin.APMNST", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  95 */       Object value = getProperty("MNST", props);
/*     */ 
/*     */       
/*  98 */       if (value != null) {
/*  99 */         boolean bVal; if (value instanceof Boolean) {
/* 100 */           bVal = ((Boolean)value).booleanValue();
/*     */         } else {
/*     */           
/* 103 */           bVal = stringToVal((String)value);
/*     */         } 
/*     */ 
/*     */         
/* 107 */         if (obj instanceof MQConnectionFactory) {
/* 108 */           ((MQConnectionFactory)obj).setMapNameStyle(bVal);
/*     */         }
/*     */         else {
/*     */           
/* 112 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 113 */           String key = "JMSADM1016";
/* 114 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 115 */           JMSException iee = new JMSException(msg, key);
/* 116 */           if (Trace.isOn) {
/* 117 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APMNST", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 1);
/*     */           }
/*     */           
/* 120 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 125 */     } catch (BAOException e) {
/* 126 */       if (Trace.isOn) {
/* 127 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APMNST", "setObjectFromProperty(Object,Map<String , Object>)", e, 1);
/*     */       }
/*     */       
/* 130 */       if (Trace.isOn) {
/* 131 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APMNST", "setObjectFromProperty(Object,Map<String , Object>)", e, 2);
/*     */       }
/*     */       
/* 134 */       throw e;
/*     */     
/*     */     }
/* 137 */     catch (JMSException e) {
/* 138 */       if (Trace.isOn) {
/* 139 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APMNST", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 2);
/*     */       }
/*     */       
/* 142 */       if (Trace.isOn) {
/* 143 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APMNST", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 3);
/*     */       }
/*     */       
/* 146 */       throw e;
/*     */     } 
/*     */     
/* 149 */     if (Trace.isOn) {
/* 150 */       Trace.exit(this, "com.ibm.mq.jms.admin.APMNST", "setObjectFromProperty(Object,Map<String , Object>)");
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
/*     */   public void setPropertyFromObject(Map<String, Object> props, Object obj) throws JMSException {
/*     */     boolean bVal;
/* 163 */     if (Trace.isOn) {
/* 164 */       Trace.entry(this, "com.ibm.mq.jms.admin.APMNST", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 170 */     if (obj instanceof MQConnectionFactory) {
/* 171 */       bVal = ((MQConnectionFactory)obj).getMapNameStyle();
/*     */     }
/*     */     else {
/*     */       
/* 175 */       String detail = "object is an unexpected type";
/* 176 */       String key = "JMSADM1016";
/* 177 */       String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 178 */       JMSException iee = new JMSException(msg, key);
/* 179 */       if (Trace.isOn) {
/* 180 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APMNST", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */       }
/*     */       
/* 183 */       throw iee;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 190 */     String sVal = "STANDARD";
/* 191 */     if (!bVal) {
/* 192 */       sVal = "COMPATIBLE";
/*     */     }
/* 194 */     props.put("MAPNAMESTYLE", sVal);
/*     */     
/* 196 */     if (Trace.isOn) {
/* 197 */       Trace.exit(this, "com.ibm.mq.jms.admin.APMNST", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String longName() {
/* 208 */     if (Trace.isOn) {
/* 209 */       Trace.entry(this, "com.ibm.mq.jms.admin.APMNST", "longName()");
/*     */     }
/* 211 */     if (Trace.isOn) {
/* 212 */       Trace.exit(this, "com.ibm.mq.jms.admin.APMNST", "longName()", "MAPNAMESTYLE");
/*     */     }
/* 214 */     return "MAPNAMESTYLE";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 222 */     if (Trace.isOn) {
/* 223 */       Trace.entry(this, "com.ibm.mq.jms.admin.APMNST", "shortName()");
/*     */     }
/* 225 */     if (Trace.isOn) {
/* 226 */       Trace.exit(this, "com.ibm.mq.jms.admin.APMNST", "shortName()", "MNST");
/*     */     }
/* 228 */     return "MNST";
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
/*     */   public static boolean stringToVal(String s) throws BAOException {
/*     */     boolean bVal;
/* 241 */     if (Trace.isOn) {
/* 242 */       Trace.entry("com.ibm.mq.jms.admin.APMNST", "stringToVal(String)", new Object[] { s });
/*     */     }
/*     */     
/* 245 */     if (Trace.isOn) {
/* 246 */       Trace.entry("APMNST", "stringToVal");
/*     */     }
/* 248 */     String str = s.toUpperCase();
/* 249 */     if (str.equals("COMPATIBLE")) {
/* 250 */       bVal = false;
/*     */     }
/* 252 */     else if (str.equals("STANDARD")) {
/* 253 */       bVal = true;
/*     */     } else {
/*     */       
/* 256 */       BAOException traceRet1 = new BAOException(4, "MNST", str);
/* 257 */       if (Trace.isOn) {
/* 258 */         Trace.throwing("com.ibm.mq.jms.admin.APMNST", "stringToVal(String)", traceRet1);
/*     */       }
/* 260 */       throw traceRet1;
/*     */     } 
/* 262 */     if (Trace.isOn) {
/* 263 */       Trace.exit("APMNST", "stringToVal");
/*     */     }
/* 265 */     if (Trace.isOn) {
/* 266 */       Trace.exit("com.ibm.mq.jms.admin.APMNST", "stringToVal(String)", Boolean.valueOf(bVal));
/*     */     }
/* 268 */     return bVal;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APMNST.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */