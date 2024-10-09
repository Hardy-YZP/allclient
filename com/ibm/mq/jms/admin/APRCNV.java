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
/*     */ public class APRCNV
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APRCNV.java";
/*     */   public static final String LONGNAME = "RECEIVECONVERSION";
/*     */   public static final String SHORTNAME = "RCNV";
/*     */   public static final String QMGR = "QMGR";
/*     */   public static final String CLIENT_MSG = "CLIENT_MSG";
/*     */   
/*     */   static {
/*  48 */     if (Trace.isOn) {
/*  49 */       Trace.data("com.ibm.mq.jms.admin.APRCNV", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APRCNV.java");
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
/*  83 */     if (Trace.isOn) {
/*  84 */       Trace.entry(this, "com.ibm.mq.jms.admin.APRCNV", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  90 */       Object value = getProperty("RCNV", props);
/*     */ 
/*     */       
/*  93 */       if (value != null) {
/*  94 */         int iVal; if (value instanceof Integer) {
/*  95 */           iVal = ((Integer)value).intValue();
/*     */         }
/*     */         else {
/*     */           
/*  99 */           String str = ((String)value).toUpperCase();
/*     */           
/* 101 */           if (str.equals("QMGR")) {
/* 102 */             iVal = 2;
/*     */           }
/* 104 */           else if (str.equals("CLIENT_MSG")) {
/* 105 */             iVal = 1;
/*     */           } else {
/*     */             
/* 108 */             BAOException traceRet1 = new BAOException(4, "RCNV", str);
/* 109 */             if (Trace.isOn) {
/* 110 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APRCNV", "setObjectFromProperty(Object,Map<String , Object>)", traceRet1, 1);
/*     */             }
/*     */             
/* 113 */             throw traceRet1;
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 118 */         if (obj instanceof MQDestination) {
/*     */           try {
/* 120 */             ((MQDestination)obj).setReceiveConversion(iVal);
/*     */           }
/* 122 */           catch (JMSException e) {
/* 123 */             if (Trace.isOn) {
/* 124 */               Trace.catchBlock(this, "com.ibm.mq.jms.admin.APRCNV", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 1);
/*     */             }
/*     */ 
/*     */             
/* 128 */             BAOException be = new BAOException(4, "RCNV", Integer.toString(iVal));
/* 129 */             if (Trace.isOn) {
/* 130 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APRCNV", "setObjectFromProperty(Object,Map<String , Object>)", be, 2);
/*     */             }
/*     */             
/* 133 */             throw be;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 138 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 139 */           String key = "JMSADM1016";
/* 140 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 141 */           JMSException iee = new JMSException(msg, key);
/* 142 */           if (Trace.isOn) {
/* 143 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APRCNV", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 3);
/*     */           }
/*     */           
/* 146 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 151 */     } catch (BAOException e) {
/* 152 */       if (Trace.isOn) {
/* 153 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APRCNV", "setObjectFromProperty(Object,Map<String , Object>)", e, 2);
/*     */       }
/*     */       
/* 156 */       if (Trace.isOn) {
/* 157 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APRCNV", "setObjectFromProperty(Object,Map<String , Object>)", e, 4);
/*     */       }
/*     */       
/* 160 */       throw e;
/*     */     
/*     */     }
/* 163 */     catch (JMSException e) {
/* 164 */       if (Trace.isOn) {
/* 165 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APRCNV", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 3);
/*     */       }
/*     */       
/* 168 */       if (Trace.isOn) {
/* 169 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APRCNV", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 5);
/*     */       }
/*     */       
/* 172 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 176 */       if (Trace.isOn) {
/* 177 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APRCNV", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 181 */     if (Trace.isOn) {
/* 182 */       Trace.exit(this, "com.ibm.mq.jms.admin.APRCNV", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 197 */     if (Trace.isOn) {
/* 198 */       Trace.entry(this, "com.ibm.mq.jms.admin.APRCNV", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */     
/*     */     try {
/*     */       int iVal;
/*     */       
/* 204 */       String sVal = null;
/*     */       
/* 206 */       if (obj instanceof MQDestination) {
/* 207 */         iVal = ((MQDestination)obj).getReceiveConversion();
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 212 */         String detail = "object is an unexpected type";
/* 213 */         String key = "JMSADM1016";
/* 214 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 215 */         JMSException iee = new JMSException(msg, key);
/* 216 */         if (Trace.isOn) {
/* 217 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APRCNV", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 220 */         throw iee;
/*     */       } 
/*     */ 
/*     */       
/* 224 */       if (iVal == 1) {
/* 225 */         sVal = "CLIENT_MSG";
/*     */       }
/* 227 */       else if (iVal == 2) {
/* 228 */         sVal = "QMGR";
/*     */       } 
/*     */ 
/*     */       
/* 232 */       props.put("RECEIVECONVERSION", sVal);
/*     */     }
/*     */     finally {
/*     */       
/* 236 */       if (Trace.isOn) {
/* 237 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APRCNV", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 242 */     if (Trace.isOn) {
/* 243 */       Trace.exit(this, "com.ibm.mq.jms.admin.APRCNV", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 256 */     if (Trace.isOn) {
/* 257 */       Trace.entry(this, "com.ibm.mq.jms.admin.APRCNV", "longName()");
/*     */     }
/* 259 */     if (Trace.isOn) {
/* 260 */       Trace.exit(this, "com.ibm.mq.jms.admin.APRCNV", "longName()", "RECEIVECONVERSION");
/*     */     }
/* 262 */     return "RECEIVECONVERSION";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 270 */     if (Trace.isOn) {
/* 271 */       Trace.entry(this, "com.ibm.mq.jms.admin.APRCNV", "shortName()");
/*     */     }
/* 273 */     if (Trace.isOn) {
/* 274 */       Trace.exit(this, "com.ibm.mq.jms.admin.APRCNV", "shortName()", "RCNV");
/*     */     }
/* 276 */     return "RCNV";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APRCNV.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */