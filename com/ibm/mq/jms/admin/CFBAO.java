/*     */ package com.ibm.mq.jms.admin;
/*     */ 
/*     */ import com.ibm.mq.jms.MQConnectionFactory;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CFBAO
/*     */   extends BAO
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/CFBAO.java";
/*     */   
/*     */   static {
/*  56 */     if (Trace.isOn) {
/*  57 */       Trace.data("com.ibm.mq.jms.admin.CFBAO", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/CFBAO.java");
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
/*  73 */   static final AP[] COMMONPROPERTIES = new AP[] { new APVER(), new APDESC(), new APTRAN(), new APQMGR(), new APPORT(), new APCHAN(), new APCCS(), new APSPAG(), new APMBS(), new APPINT(), new APUAMQ(), new APUCP(), new APCID(), new APAPPNAME(), new APHOST(), new APRCX(), new APRCXI(), new APSCX(), new APSCXI(), new APSDX(), new APSDXI(), new APSCPHS(), new APSPEER(), new APSCRL(), new APFIQ(), new APRESCAN(), new APLA(), new APHC(), new APMC(), new APSRC(), new APCNTAG(), new APCNOPT(), new APSFIPS(), new APTCM(), new APCCDT(), new APSCALD(), new APPVER(), new APSCC(), new APWCFMT(), new APMNST(), new APAEX(), new APCROPT(), new APCRT(), new APCNLIST(), new APCROPT(), new APCRT() };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 120 */   static final AP[] PROPERTIES = new AP[] { new APBCON(), new APBPUB(), new APBSUB(), new APCCSUB(), new APBQM(), new APBVER(), new APMSEL(), new APPAI(), new APSRI(), new APSS(), new APMCAST(), new APDAUTH(), new APCL(), new APCLINT(), new APCLS(), new APSBRWS(), new APPHOST(), new APPPORT(), new APRCISO(), new APPRDUR(), new APNTFY(), new APOPPUB(), new APMBSZ(), new APTTP(), new APTM(), new APMRET(), new APTQP() };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   MQConnectionFactory cf;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CFBAO() {
/* 165 */     if (Trace.isOn) {
/* 166 */       Trace.entry(this, "com.ibm.mq.jms.admin.CFBAO", "<init>()");
/*     */     }
/* 168 */     if (Trace.isOn) {
/* 169 */       Trace.exit(this, "com.ibm.mq.jms.admin.CFBAO", "<init>()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Object> getCommonProperties() {
/* 178 */     if (Trace.isOn) {
/* 179 */       Trace.entry(this, "com.ibm.mq.jms.admin.CFBAO", "getCommonProperties()");
/*     */     }
/*     */     try {
/* 182 */       if (this.cf == null) {
/* 183 */         if (Trace.isOn) {
/* 184 */           Trace.exit(this, "com.ibm.mq.jms.admin.CFBAO", "getCommonProperties()", null, 1);
/*     */         }
/* 186 */         return null;
/*     */       } 
/*     */       
/* 189 */       HashMap<String, Object> props = new HashMap<>();
/*     */       
/* 191 */       for (AP prop : COMMONPROPERTIES) {
/*     */         try {
/* 193 */           prop.setPropertyFromObject(props, this.cf);
/*     */         }
/* 195 */         catch (JMSException e) {
/* 196 */           if (Trace.isOn) {
/* 197 */             Trace.catchBlock(this, "com.ibm.mq.jms.admin.CFBAO", "getCommonProperties()", (Throwable)e);
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 203 */       if (Trace.isOn) {
/* 204 */         Trace.exit(this, "com.ibm.mq.jms.admin.CFBAO", "getCommonProperties()", props, 2);
/*     */       }
/* 206 */       return props;
/*     */     }
/*     */     finally {
/*     */       
/* 210 */       if (Trace.isOn) {
/* 211 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.CFBAO", "getCommonProperties()");
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Object> getProperties() {
/* 221 */     if (Trace.isOn) {
/* 222 */       Trace.entry(this, "com.ibm.mq.jms.admin.CFBAO", "getProperties()");
/*     */     }
/*     */     try {
/* 225 */       if (this.cf == null) {
/* 226 */         if (Trace.isOn) {
/* 227 */           Trace.exit(this, "com.ibm.mq.jms.admin.CFBAO", "getProperties()", null, 1);
/*     */         }
/* 229 */         return null;
/*     */       } 
/*     */       
/* 232 */       MQConnectionFactory tcf = this.cf;
/*     */       
/* 234 */       Map<String, Object> props = getCommonProperties();
/*     */       
/* 236 */       for (AP prop : PROPERTIES) {
/*     */         try {
/* 238 */           prop.setPropertyFromObject(props, tcf);
/*     */         }
/* 240 */         catch (JMSException e) {
/* 241 */           if (Trace.isOn) {
/* 242 */             Trace.catchBlock(this, "com.ibm.mq.jms.admin.CFBAO", "getProperties()", (Throwable)e);
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 248 */       if (Trace.isOn) {
/* 249 */         Trace.exit(this, "com.ibm.mq.jms.admin.CFBAO", "getProperties()", props, 2);
/*     */       }
/* 251 */       return props;
/*     */     }
/*     */     finally {
/*     */       
/* 255 */       if (Trace.isOn) {
/* 256 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.CFBAO", "getProperties()");
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
/*     */ 
/*     */   
/*     */   public void setFromProperties(Map<String, Object> props) throws BAOException, JMSException {
/* 272 */     if (Trace.isOn) {
/* 273 */       Trace.data(this, "com.ibm.mq.jms.admin.CFBAO", "setFromProperties(Map<String , Object>)", "setter", props);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 279 */       if (this.cf == null) {
/* 280 */         this.cf = new MQConnectionFactory();
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 285 */       _setFromProperties(props);
/*     */     }
/*     */     finally {
/*     */       
/* 289 */       if (Trace.isOn) {
/* 290 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.CFBAO", "setFromProperties(Map<String , Object>)");
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
/*     */   public int getType() {
/* 304 */     if (Trace.isOn) {
/* 305 */       Trace.data(this, "com.ibm.mq.jms.admin.CFBAO", "getType()", "getter", 
/* 306 */           Integer.valueOf(8));
/*     */     }
/* 308 */     return 8;
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
/* 319 */     if (Trace.isOn) {
/* 320 */       Trace.data(this, "com.ibm.mq.jms.admin.CFBAO", "setFromObject(Object)", "setter", obj);
/*     */     }
/*     */ 
/*     */     
/* 324 */     if (obj instanceof MQConnectionFactory) {
/* 325 */       this.cf = (MQConnectionFactory)obj;
/*     */     } else {
/*     */       
/* 328 */       BAOException be = new BAOException(10, null, null);
/* 329 */       if (Trace.isOn) {
/* 330 */         Trace.throwing(this, "com.ibm.mq.jms.admin.CFBAO", "setFromObject(Object)", be);
/*     */       }
/* 332 */       throw be;
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
/*     */   public Object getObject() {
/* 344 */     if (Trace.isOn) {
/* 345 */       Trace.data(this, "com.ibm.mq.jms.admin.CFBAO", "getObject()", "getter", this.cf);
/*     */     }
/* 347 */     return this.cf;
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
/*     */   public void semanticCheck(Map<String, Object> props) throws BAOException, JMSException {
/*     */     int msel;
/* 361 */     if (Trace.isOn) {
/* 362 */       Trace.entry(this, "com.ibm.mq.jms.admin.CFBAO", "semanticCheck(Map<String , Object>)", new Object[] { props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 367 */     commonSemanticCheck(props);
/*     */ 
/*     */ 
/*     */     
/* 371 */     Object value = AP.getProperty("MSEL", props);
/*     */ 
/*     */     
/* 374 */     if (value != null) {
/* 375 */       msel = APMSEL.objToInt(value);
/*     */     }
/*     */     else {
/*     */       
/* 379 */       msel = 0;
/*     */     } 
/*     */     
/* 382 */     if (msel == 1) {
/*     */       int bver;
/*     */ 
/*     */       
/* 386 */       value = AP.getProperty("BVER", props);
/*     */ 
/*     */       
/* 389 */       if (value != null) {
/* 390 */         bver = APBVER.objToInt(value);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 395 */         bver = 1;
/*     */       } 
/*     */ 
/*     */       
/* 399 */       if (bver != 1) {
/*     */         
/* 401 */         String s1 = " ConnectionFactory attributes clash";
/* 402 */         BAOException traceRet1 = new BAOException(3, s1, null);
/* 403 */         if (Trace.isOn) {
/* 404 */           Trace.throwing(this, "com.ibm.mq.jms.admin.CFBAO", "semanticCheck(Map<String , Object>)", traceRet1);
/*     */         }
/*     */         
/* 407 */         throw traceRet1;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 412 */     if (Trace.isOn) {
/* 413 */       Trace.exit(this, "com.ibm.mq.jms.admin.CFBAO", "semanticCheck(Map<String , Object>)");
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
/* 425 */     if (Trace.isOn) {
/* 426 */       Trace.entry(this, "com.ibm.mq.jms.admin.CFBAO", "name()");
/*     */     }
/* 428 */     if (Trace.isOn) {
/* 429 */       Trace.exit(this, "com.ibm.mq.jms.admin.CFBAO", "name()", "CF");
/*     */     }
/* 431 */     return "CF";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void commonSemanticCheck(Map<String, Object> props) throws BAOException, JMSException {
/*     */     int transportType;
/* 442 */     if (Trace.isOn) {
/* 443 */       Trace.entry(this, "com.ibm.mq.jms.admin.CFBAO", "commonSemanticCheck(Map<String , Object>)", new Object[] { props });
/*     */     }
/*     */ 
/*     */     
/* 447 */     Object value = AP.getProperty("TRAN", props);
/*     */ 
/*     */ 
/*     */     
/* 451 */     if (value != null) {
/*     */       
/* 453 */       transportType = APTRAN.objToInt(value);
/*     */     }
/*     */     else {
/*     */       
/* 457 */       transportType = 0;
/*     */     } 
/*     */     
/* 460 */     if (transportType == 0)
/*     */     {
/* 462 */       if (AP.getProperty("HOST", props) != null || AP.getProperty("PORT", props) != null || AP.getProperty("CHAN", props) != null || 
/* 463 */         AP.getProperty("CCS", props) != null || AP.getProperty("RCX", props) != null || AP.getProperty("SCX", props) != null || 
/* 464 */         AP.getProperty("SDX", props) != null || AP.getProperty("SCPHS", props) != null || AP.getProperty("SPEER", props) != null || 
/* 465 */         AP.getProperty("SCRL", props) != null || AP.getProperty("LA", props) != null || AP.getProperty("SRC", props) != null || 
/* 466 */         AP.getProperty("CCDT", props) != null || AP.getProperty("SFIPS", props) != null) {
/*     */         
/* 468 */         String s1 = ConfigEnvironment.getMessage("JMSADM4132");
/* 469 */         BAOException traceRet1 = new BAOException(3, s1, null);
/* 470 */         if (Trace.isOn) {
/* 471 */           Trace.throwing(this, "com.ibm.mq.jms.admin.CFBAO", "commonSemanticCheck(Map<String , Object>)", traceRet1, 1);
/*     */         }
/*     */         
/* 474 */         throw traceRet1;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 480 */     if ((AP.getProperty("RCXI", props) != null && AP.getProperty("RCX", props) == null) || (
/* 481 */       AP.getProperty("SCXI", props) != null && AP.getProperty("SCX", props) == null) || (
/* 482 */       AP.getProperty("SDXI", props) != null && AP.getProperty("SDX", props) == null)) {
/*     */       
/* 484 */       String s1 = ConfigEnvironment.getMessage("JMSADM4133");
/* 485 */       BAOException traceRet4 = new BAOException(3, s1, null);
/* 486 */       if (Trace.isOn) {
/* 487 */         Trace.throwing(this, "com.ibm.mq.jms.admin.CFBAO", "commonSemanticCheck(Map<String , Object>)", traceRet4, 2);
/*     */       }
/*     */       
/* 490 */       throw traceRet4;
/*     */     } 
/* 492 */     if (Trace.isOn) {
/* 493 */       Trace.exit(this, "com.ibm.mq.jms.admin.CFBAO", "commonSemanticCheck(Map<String , Object>)");
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
/*     */   public List<String> commonSupportedProperties() {
/* 505 */     if (Trace.isOn) {
/* 506 */       Trace.entry(this, "com.ibm.mq.jms.admin.CFBAO", "commonSupportedProperties()");
/*     */     }
/* 508 */     List<String> result = new ArrayList<>();
/* 509 */     for (AP prop : COMMONPROPERTIES) {
/* 510 */       result.add(prop.shortName());
/*     */     }
/*     */     
/* 513 */     if (Trace.isOn) {
/* 514 */       Trace.exit(this, "com.ibm.mq.jms.admin.CFBAO", "commonSupportedProperties()", result);
/*     */     }
/* 516 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<String> supportedProperties() {
/* 527 */     if (Trace.isOn) {
/* 528 */       Trace.entry(this, "com.ibm.mq.jms.admin.CFBAO", "supportedProperties()");
/*     */     }
/* 530 */     List<String> result = commonSupportedProperties();
/* 531 */     for (AP prop : PROPERTIES) {
/* 532 */       result.add(prop.shortName());
/*     */     }
/*     */     
/* 535 */     if (Trace.isOn) {
/* 536 */       Trace.exit(this, "com.ibm.mq.jms.admin.CFBAO", "supportedProperties()", result);
/*     */     }
/* 538 */     return result;
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
/*     */   void _commonSetFromProperties(Map<String, Object> props) throws BAOException, JMSException {
/* 552 */     if (Trace.isOn) {
/* 553 */       Trace.entry(this, "com.ibm.mq.jms.admin.CFBAO", "_commonSetFromProperties(Map<String , Object>)", new Object[] { props });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 558 */       for (AP prop : COMMONPROPERTIES) {
/* 559 */         prop.setObjectFromProperty(this.cf, props);
/*     */       }
/*     */     }
/*     */     finally {
/*     */       
/* 564 */       if (Trace.isOn) {
/* 565 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.CFBAO", "_commonSetFromProperties(Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 570 */     if (Trace.isOn) {
/* 571 */       Trace.exit(this, "com.ibm.mq.jms.admin.CFBAO", "_commonSetFromProperties(Map<String , Object>)");
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
/*     */   void _setFromProperties(Map<String, Object> props) throws BAOException, JMSException {
/* 584 */     if (Trace.isOn) {
/* 585 */       Trace.entry(this, "com.ibm.mq.jms.admin.CFBAO", "_setFromProperties(Map<String , Object>)", new Object[] { props });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 591 */       _commonSetFromProperties(props);
/*     */       
/* 593 */       for (AP prop : PROPERTIES) {
/* 594 */         prop.setObjectFromProperty(this.cf, props);
/*     */       }
/*     */     }
/*     */     finally {
/*     */       
/* 599 */       if (Trace.isOn) {
/* 600 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.CFBAO", "_setFromProperties(Map<String , Object>)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 605 */     if (Trace.isOn)
/* 606 */       Trace.exit(this, "com.ibm.mq.jms.admin.CFBAO", "_setFromProperties(Map<String , Object>)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\CFBAO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */