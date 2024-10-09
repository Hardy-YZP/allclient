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
/*     */ public class APWMMC
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APWMMC.java";
/*     */   public static final String LONGNAME = "MDMSGCTX";
/*     */   public static final String SHORTNAME = "MDCTX";
/*     */   public static final String DEFAULT = "DEFAULT";
/*     */   public static final String SET_IDENTITY_CONTEXT = "SET_IDENTITY_CONTEXT";
/*     */   public static final String SET_ALL_CONTEXT = "SET_ALL_CONTEXT";
/*     */   
/*     */   static {
/*  38 */     if (Trace.isOn) {
/*  39 */       Trace.data("com.ibm.mq.jms.admin.APWMMC", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APWMMC.java");
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
/*     */   public void setObjectFromProperty(Object obj, Map<String, Object> props) throws BAOException {
/*  74 */     if (Trace.isOn) {
/*  75 */       Trace.entry(this, "com.ibm.mq.jms.admin.APWMMC", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */     
/*  79 */     Object value = getProperty("MDCTX", props);
/*     */     try {
/*  81 */       int iVal = 0;
/*  82 */       if (value != null) {
/*     */         
/*  84 */         if (value instanceof Integer) {
/*  85 */           iVal = ((Integer)value).intValue();
/*     */         } else {
/*     */           
/*  88 */           String str = ((String)value).toUpperCase();
/*  89 */           if (str.equals("DEFAULT")) {
/*  90 */             iVal = 0;
/*     */           }
/*  92 */           else if (str.equals("SET_IDENTITY_CONTEXT")) {
/*  93 */             iVal = 1;
/*     */           }
/*  95 */           else if (str.equals("SET_ALL_CONTEXT")) {
/*  96 */             iVal = 2;
/*     */           } else {
/*     */             
/*  99 */             BAOException traceRet1 = new BAOException(4, "MDCTX", str);
/* 100 */             if (Trace.isOn) {
/* 101 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APWMMC", "setObjectFromProperty(Object,Map<String , Object>)", traceRet1, 1);
/*     */             }
/*     */             
/* 104 */             throw traceRet1;
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 109 */         if (obj instanceof MQDestination) {
/* 110 */           ((MQDestination)obj).setMQMDMessageContext(iVal);
/*     */         }
/*     */         else {
/*     */           
/* 114 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 115 */           String key = "JMSADM1016";
/* 116 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 117 */           JMSException iee = new JMSException(msg, key);
/* 118 */           if (Trace.isOn) {
/* 119 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APWMMC", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 2);
/*     */           }
/*     */           
/* 122 */           throw iee;
/*     */         }
/*     */       
/*     */       } 
/* 126 */     } catch (JMSException e) {
/* 127 */       if (Trace.isOn) {
/* 128 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APWMMC", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e);
/*     */       }
/*     */       
/* 131 */       BAOException traceRet2 = new BAOException(4, "MDCTX", value);
/* 132 */       if (Trace.isOn) {
/* 133 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APWMMC", "setObjectFromProperty(Object,Map<String , Object>)", traceRet2, 3);
/*     */       }
/*     */       
/* 136 */       throw traceRet2;
/*     */     } 
/* 138 */     if (Trace.isOn) {
/* 139 */       Trace.exit(this, "com.ibm.mq.jms.admin.APWMMC", "setObjectFromProperty(Object,Map<String , Object>)");
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
/*     */   public void setPropertyFromObject(Map<String, Object> props, Object obj) throws JMSException {
/* 157 */     if (Trace.isOn) {
/* 158 */       Trace.entry(this, "com.ibm.mq.jms.admin.APWMMC", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/* 162 */     String sVal = null;
/*     */     
/* 164 */     if (obj instanceof MQDestination) {
/* 165 */       int iVal = ((MQDestination)obj).getMQMDMessageContext();
/* 166 */       if (iVal == 1) {
/* 167 */         sVal = "SET_IDENTITY_CONTEXT";
/*     */       }
/* 169 */       else if (iVal == 2) {
/* 170 */         sVal = "SET_ALL_CONTEXT";
/*     */       }
/* 172 */       else if (iVal == 0) {
/* 173 */         sVal = "DEFAULT";
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 178 */       String detail = "object is an unexpected type";
/* 179 */       String key = "JMSADM1016";
/* 180 */       String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 181 */       JMSException iee = new JMSException(msg, key);
/* 182 */       if (Trace.isOn) {
/* 183 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APWMMC", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */       }
/*     */       
/* 186 */       throw iee;
/*     */     } 
/* 188 */     if (sVal != null)
/*     */     {
/* 190 */       props.put("MDMSGCTX", sVal);
/*     */     }
/* 192 */     if (Trace.isOn) {
/* 193 */       Trace.exit(this, "com.ibm.mq.jms.admin.APWMMC", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 206 */     if (Trace.isOn) {
/* 207 */       Trace.entry(this, "com.ibm.mq.jms.admin.APWMMC", "longName()");
/*     */     }
/* 209 */     if (Trace.isOn) {
/* 210 */       Trace.exit(this, "com.ibm.mq.jms.admin.APWMMC", "longName()", "MDMSGCTX");
/*     */     }
/* 212 */     return "MDMSGCTX";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 222 */     if (Trace.isOn) {
/* 223 */       Trace.entry(this, "com.ibm.mq.jms.admin.APWMMC", "shortName()");
/*     */     }
/* 225 */     if (Trace.isOn) {
/* 226 */       Trace.exit(this, "com.ibm.mq.jms.admin.APWMMC", "shortName()", "MDCTX");
/*     */     }
/* 228 */     return "MDCTX";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APWMMC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */