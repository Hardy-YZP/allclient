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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class APRACP
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APRACP.java";
/*     */   public static final String LONGNAME = "READAHEADCLOSEPOLICY";
/*     */   public static final String SHORTNAME = "RACP";
/*     */   public static final String DELIVER_ALL = "DELIVER_ALL";
/*     */   public static final String DELIVER_CURRENT = "DELIVER_CURRENT";
/*     */   
/*     */   static {
/*  49 */     if (Trace.isOn) {
/*  50 */       Trace.data("com.ibm.mq.jms.admin.APRACP", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APRACP.java");
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
/*     */   public void setObjectFromProperty(Object obj, Map<String, Object> props) throws BAOException, JMSException {
/*  83 */     if (Trace.isOn) {
/*  84 */       Trace.entry(this, "com.ibm.mq.jms.admin.APRACP", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  89 */     Object value = getProperty("RACP", props);
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  94 */       if (value != null) {
/*  95 */         int iVal; if (value instanceof Integer) {
/*  96 */           iVal = ((Integer)value).intValue();
/*     */         } else {
/*     */           
/*  99 */           String str = ((String)value).toUpperCase();
/*     */           
/* 101 */           if (str.equals("DELIVER_ALL")) {
/* 102 */             iVal = 2;
/*     */           }
/* 104 */           else if (str.equals("DELIVER_CURRENT")) {
/* 105 */             iVal = 1;
/*     */           } else {
/*     */             
/* 108 */             BAOException traceRet1 = new BAOException(4, "RACP", str);
/* 109 */             if (Trace.isOn) {
/* 110 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APRACP", "setObjectFromProperty(Object,Map<String , Object>)", traceRet1, 1);
/*     */             }
/*     */             
/* 113 */             throw traceRet1;
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 118 */         if (obj instanceof MQDestination) {
/* 119 */           ((MQDestination)obj).setReadAheadClosePolicy(iVal);
/*     */         }
/*     */         else {
/*     */           
/* 123 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 124 */           String key = "JMSADM1016";
/* 125 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 126 */           JMSException iee = new JMSException(msg, key);
/* 127 */           if (Trace.isOn) {
/* 128 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APRACP", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 2);
/*     */           }
/*     */           
/* 131 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 136 */     } catch (JMSException e) {
/* 137 */       JMSException jMSException1; if (Trace.isOn) {
/* 138 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APRACP", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)jMSException1);
/*     */       }
/*     */       
/* 141 */       BAOException traceRet2 = new BAOException(4, "RACP", value);
/* 142 */       if (Trace.isOn) {
/* 143 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APRACP", "setObjectFromProperty(Object,Map<String , Object>)", traceRet2, 3);
/*     */       }
/*     */       
/* 146 */       throw traceRet2;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 153 */     if (Trace.isOn) {
/* 154 */       Trace.exit(this, "com.ibm.mq.jms.admin.APRACP", "setObjectFromProperty(Object,Map<String , Object>)");
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
/*     */     int iVal;
/* 167 */     if (Trace.isOn) {
/* 168 */       Trace.entry(this, "com.ibm.mq.jms.admin.APRACP", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/* 172 */     String sVal = null;
/*     */     
/* 174 */     if (obj instanceof MQDestination) {
/* 175 */       iVal = ((MQDestination)obj).getReadAheadClosePolicy();
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 180 */       String detail = "object is an unexpected type";
/* 181 */       String key = "JMSADM1016";
/* 182 */       String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 183 */       JMSException iee = new JMSException(msg, key);
/* 184 */       if (Trace.isOn) {
/* 185 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APRACP", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */       }
/*     */       
/* 188 */       throw iee;
/*     */     } 
/*     */     
/* 191 */     if (iVal == 2) {
/* 192 */       sVal = "DELIVER_ALL";
/*     */     }
/* 194 */     else if (iVal == 1) {
/* 195 */       sVal = "DELIVER_CURRENT";
/*     */     } 
/*     */     
/* 198 */     if (sVal != null)
/*     */     {
/* 200 */       props.put("READAHEADCLOSEPOLICY", sVal);
/*     */     }
/*     */     
/* 203 */     if (Trace.isOn) {
/* 204 */       Trace.exit(this, "com.ibm.mq.jms.admin.APRACP", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 217 */     if (Trace.isOn) {
/* 218 */       Trace.entry(this, "com.ibm.mq.jms.admin.APRACP", "longName()");
/*     */     }
/* 220 */     if (Trace.isOn) {
/* 221 */       Trace.exit(this, "com.ibm.mq.jms.admin.APRACP", "longName()", "READAHEADCLOSEPOLICY");
/*     */     }
/* 223 */     return "READAHEADCLOSEPOLICY";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 231 */     if (Trace.isOn) {
/* 232 */       Trace.entry(this, "com.ibm.mq.jms.admin.APRACP", "shortName()");
/*     */     }
/* 234 */     if (Trace.isOn) {
/* 235 */       Trace.exit(this, "com.ibm.mq.jms.admin.APRACP", "shortName()", "RACP");
/*     */     }
/* 237 */     return "RACP";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APRACP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */