/*     */ package com.ibm.mq.jms.admin;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.HashMap;
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
/*     */ public abstract class AP
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/AP.java";
/*     */   
/*     */   static {
/*  52 */     if (Trace.isOn) {
/*  53 */       Trace.data("com.ibm.mq.jms.admin.AP", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/AP.java");
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
/*  66 */   private static final AP[] ALLPROPS = new AP[] { new APCCS(), new APDESC(), new APENC(), new APEXP(), new APAPPNAME(), new APPER(), new APPRI(), new APTC(), new APTCM(), new APVER(), new APQU(), new APQMGR(), new APTOP(), new APBDSUB(), new APCCDSUB(), new APCCDT(), new APTRAN(), new APPORT(), new APMBS(), new APPINT(), new APCHAN(), new APTM(), new APCID(), new APHOST(), new APRCX(), new APSCX(), new APSDX(), new APRCXI(), new APSCXI(), new APSDXI(), new APSPAG(), new APUAMQ(), new APUCP(), new APMRET(), new APBCON(), new APBPUB(), new APBSUB(), new APBQM(), new APCCSUB(), new APBVER(), new APMSEL(), new APSRI(), new APPAI(), new APSS(), new APCL(), new APCLINT(), new APCLS(), new APSCRL(), new APSPEER(), new APSCPHS(), new APFIQ(), new APRESCAN(), new APLA(), new APTBPUBQ(), new APTBQM(), new APTQP(), new APSBRWS(), new APMCAST(), new APDAUTH(), new APPHOST(), new APPPORT(), new APMBSZ(), new APSRC(), new APHC(), new APMC(), new APCNTAG(), new APCNOPT(), new APRCISO(), new APPRDUR(), new APNTFY(), new APOPPUB(), new APSFIPS(), new APSCALD(), new APPVER(), new APSCC(), new APTTP(), new APWCFMT(), new APRACP(), new APRAALD(), new APPAALD(), new APMNST(), new APAEX(), new APWMB(), new APWMRE(), new APWMWE(), new APWMMC(), new APRTOST(), new APCRT(), new APCNLIST(), new APCROPT(), new APRCCS(), new APRCNV(), new APUMA(), new APUMR() };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String YES = "YES";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String NO = "NO";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 168 */   private static Map<String, String> short2longMap = null;
/* 169 */   private static Map<String, String> long2shortMap = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Object getProperty(String propName, Map<String, Object> props) {
/* 208 */     if (Trace.isOn) {
/* 209 */       Trace.entry("com.ibm.mq.jms.admin.AP", "getProperty(String,Map<String , Object>)", new Object[] { propName, props });
/*     */     }
/*     */     
/* 212 */     Object value = props.get(propName);
/* 213 */     if (value == null) {
/*     */       
/* 215 */       String longName = getLongName(propName);
/* 216 */       if (longName != null) {
/* 217 */         value = props.get(longName);
/*     */       }
/*     */     } 
/* 220 */     if (Trace.isOn) {
/* 221 */       Trace.exit("com.ibm.mq.jms.admin.AP", "getProperty(String,Map<String , Object>)", value);
/*     */     }
/* 223 */     return value;
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
/*     */   public static String getLongName(String shortName) {
/* 235 */     if (Trace.isOn) {
/* 236 */       Trace.entry("com.ibm.mq.jms.admin.AP", "getLongName(String)", new Object[] { shortName });
/*     */     }
/*     */     
/* 239 */     createMapsIfNecessary();
/*     */     
/* 241 */     String longName = short2longMap.get(shortName);
/*     */     
/* 243 */     if (longName == null)
/*     */     {
/* 245 */       if (long2shortMap.containsKey(shortName))
/*     */       {
/* 247 */         longName = shortName;
/*     */       }
/*     */     }
/*     */     
/* 251 */     if (Trace.isOn) {
/* 252 */       Trace.exit("com.ibm.mq.jms.admin.AP", "getLongName(String)", longName);
/*     */     }
/* 254 */     return longName;
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
/*     */   public static String getShortName(String longName) {
/* 269 */     if (Trace.isOn) {
/* 270 */       Trace.entry("com.ibm.mq.jms.admin.AP", "getShortName(String)", new Object[] { longName });
/*     */     }
/* 272 */     createMapsIfNecessary();
/*     */     
/* 274 */     String shortName = long2shortMap.get(longName);
/*     */     
/* 276 */     if (shortName == null)
/*     */     {
/* 278 */       if (short2longMap.containsKey(longName))
/*     */       {
/* 280 */         shortName = longName;
/*     */       }
/*     */     }
/*     */     
/* 284 */     if (Trace.isOn) {
/* 285 */       Trace.exit("com.ibm.mq.jms.admin.AP", "getShortName(String)", shortName);
/*     */     }
/* 287 */     return shortName;
/*     */   }
/*     */ 
/*     */   
/*     */   private static synchronized void createMapsIfNecessary() {
/* 292 */     if (Trace.isOn) {
/* 293 */       Trace.entry("com.ibm.mq.jms.admin.AP", "createMapsIfNecessary()");
/*     */     }
/*     */     
/* 296 */     if (long2shortMap == null) {
/* 297 */       long2shortMap = new HashMap<>();
/* 298 */       short2longMap = new HashMap<>();
/* 299 */       for (AP prop : ALLPROPS) {
/* 300 */         long2shortMap.put(prop.longName(), prop.shortName());
/* 301 */         short2longMap.put(prop.shortName(), prop.longName());
/*     */       } 
/*     */     } 
/* 304 */     if (Trace.isOn)
/* 305 */       Trace.exit("com.ibm.mq.jms.admin.AP", "createMapsIfNecessary()"); 
/*     */   }
/*     */   
/*     */   public abstract void setObjectFromProperty(Object paramObject, Map<String, Object> paramMap) throws BAOException, JMSException;
/*     */   
/*     */   public abstract void setPropertyFromObject(Map<String, Object> paramMap, Object paramObject) throws JMSException;
/*     */   
/*     */   public abstract String longName();
/*     */   
/*     */   public abstract String shortName();
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\AP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */