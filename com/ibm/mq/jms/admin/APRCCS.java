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
/*     */ public class APRCCS
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APRCCS.java";
/*     */   public static final String LONGNAME = "RECEIVECCSID";
/*     */   public static final String SHORTNAME = "RCCS";
/*     */   
/*     */   static {
/*  47 */     if (Trace.isOn) {
/*  48 */       Trace.data("com.ibm.mq.jms.admin.APRCCS", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APRCCS.java");
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
/*     */   public void setObjectFromProperty(Object obj, Map<String, Object> props) throws BAOException, JMSException {
/*  77 */     if (Trace.isOn) {
/*  78 */       Trace.entry(this, "com.ibm.mq.jms.admin.APRCCS", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  84 */       Object value = getProperty("RCCS", props);
/*     */ 
/*     */       
/*  87 */       if (value != null) {
/*  88 */         int iVal; if (value instanceof Integer) {
/*  89 */           iVal = ((Integer)value).intValue();
/*     */         } else {
/*     */           
/*     */           try {
/*  93 */             iVal = Integer.parseInt((String)value);
/*     */           }
/*  95 */           catch (NumberFormatException e) {
/*  96 */             if (Trace.isOn) {
/*  97 */               Trace.catchBlock(this, "com.ibm.mq.jms.admin.APRCCS", "setObjectFromProperty(Object,Map<String , Object>)", e, 1);
/*     */             }
/*     */             
/* 100 */             BAOException traceRet1 = new BAOException(4, "RCCS", value);
/* 101 */             if (Trace.isOn) {
/* 102 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APRCCS", "setObjectFromProperty(Object,Map<String , Object>)", traceRet1, 1);
/*     */             }
/*     */             
/* 105 */             throw traceRet1;
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 110 */         if (obj instanceof MQDestination) {
/*     */           try {
/* 112 */             ((MQDestination)obj).setReceiveCCSID(iVal);
/*     */           }
/* 114 */           catch (JMSException e) {
/* 115 */             if (Trace.isOn) {
/* 116 */               Trace.catchBlock(this, "com.ibm.mq.jms.admin.APRCCS", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 2);
/*     */             }
/*     */ 
/*     */             
/* 120 */             BAOException be = new BAOException(4, "RCCS", Integer.toString(iVal));
/* 121 */             if (Trace.isOn) {
/* 122 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APRCCS", "setObjectFromProperty(Object,Map<String , Object>)", be, 2);
/*     */             }
/*     */             
/* 125 */             throw be;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 130 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 131 */           String key = "JMSADM1016";
/* 132 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 133 */           JMSException iee = new JMSException(msg, key);
/* 134 */           if (Trace.isOn) {
/* 135 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APRCCS", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 3);
/*     */           }
/*     */           
/* 138 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 143 */     } catch (BAOException e) {
/* 144 */       if (Trace.isOn) {
/* 145 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APRCCS", "setObjectFromProperty(Object,Map<String , Object>)", e, 3);
/*     */       }
/*     */       
/* 148 */       if (Trace.isOn) {
/* 149 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APRCCS", "setObjectFromProperty(Object,Map<String , Object>)", e, 4);
/*     */       }
/*     */       
/* 152 */       throw e;
/*     */     
/*     */     }
/* 155 */     catch (JMSException e) {
/* 156 */       if (Trace.isOn) {
/* 157 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APRCCS", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 4);
/*     */       }
/*     */       
/* 160 */       if (Trace.isOn) {
/* 161 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APRCCS", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 5);
/*     */       }
/*     */       
/* 164 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 168 */       if (Trace.isOn) {
/* 169 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APRCCS", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 173 */     if (Trace.isOn) {
/* 174 */       Trace.exit(this, "com.ibm.mq.jms.admin.APRCCS", "setObjectFromProperty(Object,Map<String , Object>)");
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
/*     */   public void setPropertyFromObject(Map<String, Object> props, Object obj) throws JMSException {
/* 189 */     if (Trace.isOn) {
/* 190 */       Trace.entry(this, "com.ibm.mq.jms.admin.APRCCS", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       int iVal;
/*     */       
/* 197 */       if (obj instanceof MQDestination) {
/* 198 */         iVal = ((MQDestination)obj).getReceiveCCSID();
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 203 */         String detail = "object is an unexpected type";
/* 204 */         String key = "JMSADM1016";
/* 205 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 206 */         JMSException iee = new JMSException(msg, key);
/* 207 */         if (Trace.isOn) {
/* 208 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APRCCS", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 211 */         throw iee;
/*     */       } 
/*     */ 
/*     */       
/* 215 */       String value = String.valueOf(iVal);
/*     */ 
/*     */       
/* 218 */       props.put("RECEIVECCSID", value);
/*     */     }
/*     */     finally {
/*     */       
/* 222 */       if (Trace.isOn) {
/* 223 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APRCCS", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 228 */     if (Trace.isOn) {
/* 229 */       Trace.exit(this, "com.ibm.mq.jms.admin.APRCCS", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 242 */     if (Trace.isOn) {
/* 243 */       Trace.entry(this, "com.ibm.mq.jms.admin.APRCCS", "longName()");
/*     */     }
/* 245 */     if (Trace.isOn) {
/* 246 */       Trace.exit(this, "com.ibm.mq.jms.admin.APRCCS", "longName()", "RECEIVECCSID");
/*     */     }
/* 248 */     return "RECEIVECCSID";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 256 */     if (Trace.isOn) {
/* 257 */       Trace.entry(this, "com.ibm.mq.jms.admin.APRCCS", "shortName()");
/*     */     }
/* 259 */     if (Trace.isOn) {
/* 260 */       Trace.exit(this, "com.ibm.mq.jms.admin.APRCCS", "shortName()", "RCCS");
/*     */     }
/* 262 */     return "RCCS";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APRCCS.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */