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
/*     */ 
/*     */ public class APUMR
/*     */   extends AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APUMR.java";
/*     */   public static final String LONGNAME = "UNMAPPABLEREPLACEMENT";
/*     */   public static final String SHORTNAME = "UMR";
/*     */   
/*     */   static {
/*  48 */     if (Trace.isOn) {
/*  49 */       Trace.data("com.ibm.mq.jms.admin.APUMR", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/APUMR.java");
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
/*     */   public void setObjectFromProperty(Object obj, Map<String, Object> props) throws BAOException, JMSException {
/*  76 */     if (Trace.isOn) {
/*  77 */       Trace.entry(this, "com.ibm.mq.jms.admin.APUMR", "setObjectFromProperty(Object,Map<String , Object>)", new Object[] { obj, props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  83 */       Object value = getProperty("UMR", props);
/*     */       
/*  85 */       if (value != null)
/*     */       {
/*     */         
/*     */         try {
/*     */           
/*  90 */           if (!(value instanceof Byte))
/*     */           {
/*     */ 
/*     */             
/*  94 */             short byteValue = Short.parseShort(value.toString());
/*  95 */             if (byteValue < 0 || byteValue > 255) {
/*     */               
/*  97 */               BAOException be = new BAOException(4, "UMR", value);
/*  98 */               if (Trace.isOn) {
/*  99 */                 Trace.throwing(this, "com.ibm.mq.jms.admin.APUMR", "setObjectFromProperty(Object,Map<String , Object>)", be, 5);
/*     */               }
/*     */               
/* 102 */               throw be;
/*     */             } 
/* 104 */             value = Byte.valueOf((byte)byteValue);
/*     */           }
/*     */         
/* 107 */         } catch (NumberFormatException nfe) {
/* 108 */           if (Trace.isOn && 
/* 109 */             Trace.isOn) {
/* 110 */             Trace.catchBlock(this, "com.ibm.mq.jms.admin.APUMR", "setObjectFromProperty(Object,Map<String , Object>)", nfe, 4);
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 115 */           String detail = "Object value could not be converted to a Byte object.  Class type given: " + value.getClass() + ", value: " + value.toString();
/* 116 */           String key = "JMSADM1016";
/* 117 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 118 */           JMSException iee = new JMSException(msg, key);
/*     */           
/* 120 */           throw iee;
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 125 */         if (obj instanceof MQDestination) {
/*     */           try {
/* 127 */             ((MQDestination)obj).setUnmappableReplacement((Byte)value);
/*     */           }
/* 129 */           catch (JMSException e) {
/* 130 */             if (Trace.isOn) {
/* 131 */               Trace.catchBlock(this, "com.ibm.mq.jms.admin.APUMR", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 1);
/*     */             }
/*     */ 
/*     */             
/* 135 */             BAOException be = new BAOException(4, "UMR", value);
/* 136 */             if (Trace.isOn) {
/* 137 */               Trace.throwing(this, "com.ibm.mq.jms.admin.APUMR", "setObjectFromProperty(Object,Map<String , Object>)", be, 1);
/*     */             }
/*     */             
/* 140 */             throw be;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 145 */           String detail = "object supplied as an unexpected type " + obj.getClass();
/* 146 */           String key = "JMSADM1016";
/* 147 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 148 */           JMSException iee = new JMSException(msg, key);
/* 149 */           if (Trace.isOn) {
/* 150 */             Trace.throwing(this, "com.ibm.mq.jms.admin.APUMR", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)iee, 2);
/*     */           }
/*     */           
/* 153 */           throw iee;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 158 */     } catch (BAOException e) {
/* 159 */       if (Trace.isOn) {
/* 160 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APUMR", "setObjectFromProperty(Object,Map<String , Object>)", e, 2);
/*     */       }
/*     */       
/* 163 */       if (Trace.isOn) {
/* 164 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APUMR", "setObjectFromProperty(Object,Map<String , Object>)", e, 3);
/*     */       }
/*     */       
/* 167 */       throw e;
/*     */     
/*     */     }
/* 170 */     catch (JMSException e) {
/* 171 */       if (Trace.isOn) {
/* 172 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.APUMR", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 3);
/*     */       }
/*     */       
/* 175 */       if (Trace.isOn) {
/* 176 */         Trace.throwing(this, "com.ibm.mq.jms.admin.APUMR", "setObjectFromProperty(Object,Map<String , Object>)", (Throwable)e, 4);
/*     */       }
/*     */       
/* 179 */       throw e;
/*     */     }
/*     */     finally {
/*     */       
/* 183 */       if (Trace.isOn) {
/* 184 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APUMR", "setObjectFromProperty(Object,Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */     
/* 188 */     if (Trace.isOn) {
/* 189 */       Trace.exit(this, "com.ibm.mq.jms.admin.APUMR", "setObjectFromProperty(Object,Map<String , Object>)");
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
/* 204 */     if (Trace.isOn) {
/* 205 */       Trace.entry(this, "com.ibm.mq.jms.admin.APUMR", "setPropertyFromObject(Map<String , Object>,Object)", new Object[] { props, obj });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       Byte value;
/*     */       
/* 212 */       if (obj instanceof MQDestination) {
/* 213 */         value = Byte.valueOf(((MQDestination)obj).getUnmappableReplacement());
/*     */       }
/*     */       else {
/*     */         
/* 217 */         String detail = "object is an unexpected type";
/* 218 */         String key = "JMSADM1016";
/* 219 */         String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 220 */         JMSException iee = new JMSException(msg, key);
/* 221 */         if (Trace.isOn) {
/* 222 */           Trace.throwing(this, "com.ibm.mq.jms.admin.APUMR", "setPropertyFromObject(Map<String , Object>,Object)", (Throwable)iee);
/*     */         }
/*     */         
/* 225 */         throw iee;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 239 */       String newValue = value.toString();
/* 240 */       if (value.byteValue() < 0) {
/* 241 */         newValue = Integer.valueOf(value.byteValue() + 256).toString();
/*     */       }
/*     */       
/* 244 */       props.put("UNMAPPABLEREPLACEMENT", newValue);
/*     */     }
/*     */     finally {
/*     */       
/* 248 */       if (Trace.isOn) {
/* 249 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.APUMR", "setPropertyFromObject(Map<String , Object>,Object)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 254 */     if (Trace.isOn) {
/* 255 */       Trace.exit(this, "com.ibm.mq.jms.admin.APUMR", "setPropertyFromObject(Map<String , Object>,Object)");
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
/* 268 */     if (Trace.isOn) {
/* 269 */       Trace.entry(this, "com.ibm.mq.jms.admin.APUMR", "longName()");
/*     */     }
/* 271 */     if (Trace.isOn) {
/* 272 */       Trace.exit(this, "com.ibm.mq.jms.admin.APUMR", "longName()", "UNMAPPABLEREPLACEMENT");
/*     */     }
/* 274 */     return "UNMAPPABLEREPLACEMENT";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String shortName() {
/* 282 */     if (Trace.isOn) {
/* 283 */       Trace.entry(this, "com.ibm.mq.jms.admin.APUMR", "shortName()");
/*     */     }
/* 285 */     if (Trace.isOn) {
/* 286 */       Trace.exit(this, "com.ibm.mq.jms.admin.APUMR", "shortName()", "UMR");
/*     */     }
/* 288 */     return "UMR";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\APUMR.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */