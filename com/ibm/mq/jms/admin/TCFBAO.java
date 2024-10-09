/*     */ package com.ibm.mq.jms.admin;
/*     */ 
/*     */ import com.ibm.mq.jms.MQConnectionFactory;
/*     */ import com.ibm.mq.jms.MQTopicConnectionFactory;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.List;
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
/*     */ 
/*     */ 
/*     */ public class TCFBAO
/*     */   extends CFBAO
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/TCFBAO.java";
/*     */   
/*     */   static {
/*  52 */     if (Trace.isOn) {
/*  53 */       Trace.data("com.ibm.mq.jms.admin.TCFBAO", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/TCFBAO.java");
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
/*  65 */   static final AP[] PROPERTIES = new AP[] { new APBCON(), new APBPUB(), new APBSUB(), new APCCSUB(), new APBQM(), new APBVER(), new APMSEL(), new APPAI(), new APSRI(), new APSS(), new APCL(), new APCLINT(), new APCLS(), new APSBRWS(), new APMCAST(), new APDAUTH(), new APPHOST(), new APPPORT(), new APMBSZ(), new APRCISO(), new APPRDUR(), new APNTFY(), new APOPPUB(), new APTTP() };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TCFBAO() {
/*  97 */     if (Trace.isOn) {
/*  98 */       Trace.entry(this, "com.ibm.mq.jms.admin.TCFBAO", "<init>()");
/*     */     }
/* 100 */     if (Trace.isOn) {
/* 101 */       Trace.exit(this, "com.ibm.mq.jms.admin.TCFBAO", "<init>()");
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
/*     */   public void setFromProperties(Map<String, Object> props) throws BAOException, JMSException {
/* 118 */     if (Trace.isOn) {
/* 119 */       Trace.data(this, "com.ibm.mq.jms.admin.TCFBAO", "setFromProperties(Map<String , Object>)", "setter", props);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 127 */       this.cf = (MQConnectionFactory)new MQTopicConnectionFactory();
/*     */ 
/*     */ 
/*     */       
/* 131 */       _setFromProperties(props);
/*     */     }
/*     */     finally {
/*     */       
/* 135 */       if (Trace.isOn) {
/* 136 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.TCFBAO", "setFromProperties(Map<String , Object>)");
/*     */       }
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
/*     */   public List<String> supportedProperties() {
/* 150 */     if (Trace.isOn) {
/* 151 */       Trace.entry(this, "com.ibm.mq.jms.admin.TCFBAO", "supportedProperties()");
/*     */     }
/*     */     
/* 154 */     List<String> result = commonSupportedProperties();
/*     */ 
/*     */     
/* 157 */     for (AP prop : PROPERTIES) {
/* 158 */       result.add(prop.shortName());
/*     */     }
/*     */     
/* 161 */     if (Trace.isOn) {
/* 162 */       Trace.exit(this, "com.ibm.mq.jms.admin.TCFBAO", "supportedProperties()", result);
/*     */     }
/* 164 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Object> getProperties() {
/* 175 */     if (Trace.isOn) {
/* 176 */       Trace.entry(this, "com.ibm.mq.jms.admin.TCFBAO", "getProperties()");
/*     */     }
/*     */     
/*     */     try {
/* 180 */       if (this.cf == null) {
/* 181 */         if (Trace.isOn) {
/* 182 */           Trace.exit(this, "com.ibm.mq.jms.admin.TCFBAO", "getProperties()", null, 1);
/*     */         }
/* 184 */         return null;
/*     */       } 
/*     */       
/* 187 */       MQTopicConnectionFactory tcf = (MQTopicConnectionFactory)this.cf;
/*     */       
/* 189 */       Map<String, Object> props = getCommonProperties();
/*     */       
/* 191 */       for (AP prop : PROPERTIES) {
/*     */         try {
/* 193 */           prop.setPropertyFromObject(props, tcf);
/*     */         }
/* 195 */         catch (JMSException e) {
/* 196 */           if (Trace.isOn) {
/* 197 */             Trace.catchBlock(this, "com.ibm.mq.jms.admin.TCFBAO", "getProperties()", (Throwable)e);
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 203 */       if (Trace.isOn) {
/* 204 */         Trace.exit(this, "com.ibm.mq.jms.admin.TCFBAO", "getProperties()", props, 2);
/*     */       }
/* 206 */       return props;
/*     */     }
/*     */     finally {
/*     */       
/* 210 */       if (Trace.isOn) {
/* 211 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.TCFBAO", "getProperties()");
/*     */       }
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
/*     */   public int getType() {
/* 224 */     if (Trace.isOn) {
/* 225 */       Trace.data(this, "com.ibm.mq.jms.admin.TCFBAO", "getType()", "getter", 
/* 226 */           Integer.valueOf(2));
/*     */     }
/* 228 */     return 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFromObject(Object obj) throws BAOException {
/* 239 */     if (Trace.isOn) {
/* 240 */       Trace.data(this, "com.ibm.mq.jms.admin.TCFBAO", "setFromObject(Object)", "setter", obj);
/*     */     }
/* 242 */     if (obj instanceof MQTopicConnectionFactory) {
/* 243 */       this.cf = (MQConnectionFactory)obj;
/*     */     } else {
/*     */       
/* 246 */       BAOException be = new BAOException(10, null, null);
/* 247 */       if (Trace.isOn) {
/* 248 */         Trace.throwing(this, "com.ibm.mq.jms.admin.TCFBAO", "setFromObject(Object)", be);
/*     */       }
/* 250 */       throw be;
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
/*     */   public void semanticCheck(Map<String, Object> props) throws BAOException, JMSException {
/*     */     int msel;
/* 273 */     if (Trace.isOn) {
/* 274 */       Trace.entry(this, "com.ibm.mq.jms.admin.TCFBAO", "semanticCheck(Map<String , Object>)", new Object[] { props });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 280 */     commonSemanticCheck(props);
/*     */ 
/*     */ 
/*     */     
/* 284 */     Object value = AP.getProperty("MSEL", props);
/*     */ 
/*     */     
/* 287 */     if (value != null) {
/* 288 */       msel = APMSEL.objToInt(value);
/*     */     }
/*     */     else {
/*     */       
/* 292 */       msel = 0;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 297 */     if (msel == 1) {
/*     */       int bver;
/*     */ 
/*     */       
/* 301 */       value = AP.getProperty("BVER", props);
/*     */ 
/*     */       
/* 304 */       if (value != null) {
/* 305 */         bver = APBVER.objToInt(value);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 310 */         bver = 1;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 315 */       if (bver != 1) {
/*     */         
/* 317 */         String s1 = " TopicConnectionFactory attributes clash";
/* 318 */         BAOException traceRet1 = new BAOException(3, s1, null);
/* 319 */         if (Trace.isOn) {
/* 320 */           Trace.throwing(this, "com.ibm.mq.jms.admin.TCFBAO", "semanticCheck(Map<String , Object>)", traceRet1);
/*     */         }
/*     */         
/* 323 */         throw traceRet1;
/*     */       } 
/*     */     } 
/*     */     
/* 327 */     if (Trace.isOn) {
/* 328 */       Trace.exit(this, "com.ibm.mq.jms.admin.TCFBAO", "semanticCheck(Map<String , Object>)");
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
/*     */   public String name() {
/* 340 */     if (Trace.isOn) {
/* 341 */       Trace.entry(this, "com.ibm.mq.jms.admin.TCFBAO", "name()");
/*     */     }
/* 343 */     if (Trace.isOn) {
/* 344 */       Trace.exit(this, "com.ibm.mq.jms.admin.TCFBAO", "name()", "TCF");
/*     */     }
/* 346 */     return "TCF";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void _setFromProperties(Map<String, Object> props) throws BAOException, JMSException {
/* 355 */     if (Trace.isOn) {
/* 356 */       Trace.entry(this, "com.ibm.mq.jms.admin.TCFBAO", "_setFromProperties(Map<String , Object>)", new Object[] { props });
/*     */     }
/*     */ 
/*     */     
/* 360 */     _commonSetFromProperties(props);
/*     */ 
/*     */     
/* 363 */     for (AP prop : PROPERTIES) {
/* 364 */       prop.setObjectFromProperty(this.cf, props);
/*     */     }
/*     */     
/* 367 */     if (Trace.isOn)
/* 368 */       Trace.exit(this, "com.ibm.mq.jms.admin.TCFBAO", "_setFromProperties(Map<String , Object>)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\TCFBAO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */