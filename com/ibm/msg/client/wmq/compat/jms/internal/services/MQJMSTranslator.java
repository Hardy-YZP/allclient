/*     */ package com.ibm.msg.client.wmq.compat.jms.internal.services;
/*     */ 
/*     */ import com.ibm.disthub2.spi.ClientTranslate;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.wmq.compat.jms.internal.ConfigEnvironment;
/*     */ import java.util.Hashtable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQJMSTranslator
/*     */   extends ClientTranslate
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/services/MQJMSTranslator.java";
/*     */   
/*     */   static {
/*  70 */     if (Trace.isOn) {
/*  71 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.services.MQJMSTranslator", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/services/MQJMSTranslator.java");
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
/*  83 */   private static final Hashtable disthubMsgMap = new Hashtable<>();
/*     */   
/*     */   static {
/*  86 */     if (Trace.isOn) {
/*  87 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.services.MQJMSTranslator", "static()");
/*     */     }
/*     */ 
/*     */     
/*  91 */     disthubMsgMap.put(Integer.valueOf(-228824350), "MQJMS0007");
/*     */     
/*  93 */     disthubMsgMap.put(Integer.valueOf(2144856379), "MQJMS0008");
/*     */     
/*  95 */     disthubMsgMap.put(Integer.valueOf(1019955564), "MQJMS0005");
/*     */     
/*  97 */     disthubMsgMap.put(Integer.valueOf(-1603263993), "MQJMS0006");
/*     */     
/*  99 */     disthubMsgMap.put(Integer.valueOf(-181372107), "MQJMS6079");
/*     */     
/* 101 */     disthubMsgMap.put(Integer.valueOf(1967595332), "MQJMS0006");
/*     */     
/* 103 */     disthubMsgMap.put(Integer.valueOf(-881562132), "MQJMS6081");
/*     */     
/* 105 */     disthubMsgMap.put(Integer.valueOf(66471145), "MQJMS6083");
/*     */     
/* 107 */     disthubMsgMap.put(Integer.valueOf(-1442602204), "MQJMS6085");
/*     */     
/* 109 */     disthubMsgMap.put(Integer.valueOf(-1676719413), "MQJMS6088");
/*     */     
/* 111 */     disthubMsgMap.put(Integer.valueOf(-1509413953), "MQJMS3038");
/*     */     
/* 113 */     disthubMsgMap.put(Integer.valueOf(1839392006), "MQJMS6090");
/*     */     
/* 115 */     disthubMsgMap.put(Integer.valueOf(-1165683931), "MQJMS6091");
/*     */     
/* 117 */     disthubMsgMap.put(Integer.valueOf(1984288331), "MQJMS6093");
/*     */     
/* 119 */     disthubMsgMap.put(Integer.valueOf(-1305206872), "MQJMS1042");
/*     */     
/* 121 */     disthubMsgMap.put(Integer.valueOf(-540408008), "MQJMS1010");
/*     */     
/* 123 */     disthubMsgMap.put(Integer.valueOf(-522107999), "MQJMS6096");
/*     */     
/* 125 */     disthubMsgMap.put(Integer.valueOf(-297750435), "MQJMS6097");
/*     */     
/* 127 */     disthubMsgMap.put(Integer.valueOf(-597439962), "MQJMS0003");
/*     */     
/* 129 */     disthubMsgMap.put(Integer.valueOf(556454374), "MQJMS6105");
/*     */     
/* 131 */     disthubMsgMap.put(Integer.valueOf(-1422303942), "MQJMS6106");
/*     */     
/* 133 */     disthubMsgMap.put(Integer.valueOf(1923708604), "MQJMS6115");
/*     */     
/* 135 */     disthubMsgMap.put(Integer.valueOf(-1924738140), "MQJMS6116");
/*     */     
/* 137 */     disthubMsgMap.put(Integer.valueOf(231319338), "MQJMS6117");
/*     */     
/* 139 */     disthubMsgMap.put(Integer.valueOf(166257773), "MQJMS6118");
/*     */     
/* 141 */     disthubMsgMap.put(Integer.valueOf(800870265), "MQJMS6119");
/*     */     
/* 143 */     disthubMsgMap.put(Integer.valueOf(793507705), "MQJMS6120");
/*     */     
/* 145 */     disthubMsgMap.put(Integer.valueOf(-179745901), "MQJMS6121");
/*     */     
/* 147 */     disthubMsgMap.put(Integer.valueOf(-873358984), "MQJMS6228");
/*     */     
/* 149 */     disthubMsgMap.put(Integer.valueOf(-1015179332), "MQJMS6229");
/*     */     
/* 151 */     disthubMsgMap.put(Integer.valueOf(-210787062), "MQJMS6232");
/*     */     
/* 153 */     disthubMsgMap.put(Integer.valueOf(-1200843245), "MQJMS6233");
/*     */     
/* 155 */     disthubMsgMap.put(Integer.valueOf(-1617888306), "MQJMS6234");
/*     */     
/* 157 */     disthubMsgMap.put(Integer.valueOf(-980208654), "MQJMS6235");
/*     */     
/* 159 */     disthubMsgMap.put(Integer.valueOf(-1374292958), "MQJMS6236");
/*     */     
/* 161 */     disthubMsgMap.put(Integer.valueOf(-1090247690), "MQJMS6237");
/*     */     
/* 163 */     disthubMsgMap.put(Integer.valueOf(475213519), "MQJMS6238");
/*     */     
/* 165 */     disthubMsgMap.put(Integer.valueOf(447680256), "MQJMS6239");
/*     */     
/* 167 */     disthubMsgMap.put(Integer.valueOf(906763490), "MQJMS6240");
/*     */     
/* 169 */     disthubMsgMap.put(Integer.valueOf(404727568), "MQJMS6241");
/*     */     
/* 171 */     disthubMsgMap.put(Integer.valueOf(973908005), "MQJMS6242");
/*     */     
/* 173 */     disthubMsgMap.put(Integer.valueOf(-1954331796), "MQJMS6243");
/*     */     
/* 175 */     disthubMsgMap.put(Integer.valueOf(61872650), "MQJMS6244");
/*     */     
/* 177 */     disthubMsgMap.put(Integer.valueOf(142186165), "MQJMS6245");
/*     */     
/* 179 */     disthubMsgMap.put(Integer.valueOf(1462961341), "MQJMS6246");
/*     */     
/* 181 */     disthubMsgMap.put(Integer.valueOf(-2052046540), "MQJMS6247");
/*     */     
/* 183 */     disthubMsgMap.put(Integer.valueOf(1090878551), "MQJMS6248");
/*     */     
/* 185 */     disthubMsgMap.put(Integer.valueOf(1817627243), "MQJMS6249");
/*     */     
/* 187 */     disthubMsgMap.put(Integer.valueOf(848340020), "MQJMS6250");
/*     */     
/* 189 */     disthubMsgMap.put(Integer.valueOf(1005717551), "MQJMS6251");
/*     */     
/* 191 */     disthubMsgMap.put(Integer.valueOf(-943238968), "MQJMS6252");
/*     */     
/* 193 */     disthubMsgMap.put(Integer.valueOf(269306113), "MQJMS6040");
/*     */     
/* 195 */     disthubMsgMap.put(Integer.valueOf(1266173347), "MQJMS6041");
/*     */     
/* 197 */     disthubMsgMap.put(Integer.valueOf(347943087), "MQJMS6056");
/*     */     
/* 199 */     disthubMsgMap.put(Integer.valueOf(754669422), "MQJMS6057");
/*     */     
/* 201 */     disthubMsgMap.put(Integer.valueOf(1104995892), "MQJMS6058");
/*     */     
/* 203 */     disthubMsgMap.put(Integer.valueOf(111981380), "MQJMS6059");
/*     */     
/* 205 */     disthubMsgMap.put(Integer.valueOf(322992620), "MQJMS6060");
/*     */     
/* 207 */     disthubMsgMap.put(Integer.valueOf(32172136), "MQJMS6061");
/*     */     
/* 209 */     disthubMsgMap
/* 210 */       .put(Integer.valueOf(-359372290), "MQJMS6062");
/* 211 */     disthubMsgMap.put(Integer.valueOf(1024325086), "MQJMS6063");
/*     */     
/* 213 */     disthubMsgMap.put(Integer.valueOf(-1124040193), "MQJMS6064");
/*     */     
/* 215 */     disthubMsgMap.put(Integer.valueOf(1612396596), "MQJMS6065");
/*     */     
/* 217 */     disthubMsgMap.put(Integer.valueOf(1714011970), "MQJMS6066");
/*     */     
/* 219 */     disthubMsgMap.put(Integer.valueOf(-405169512), "MQJMS6067");
/*     */     
/* 221 */     disthubMsgMap.put(Integer.valueOf(1623779279), "MQJMS6350");
/*     */     
/* 223 */     disthubMsgMap.put(Integer.valueOf(1368123826), "MQJMS6068");
/*     */     
/* 225 */     disthubMsgMap.put(Integer.valueOf(-412851445), "MQJMS6069");
/*     */     
/* 227 */     disthubMsgMap.put(Integer.valueOf(-784342957), "MQJMS6070");
/*     */     
/* 229 */     disthubMsgMap.put(Integer.valueOf(-402291761), "MQJMS6071");
/*     */     
/* 231 */     disthubMsgMap.put(Integer.valueOf(698806460), "MQJMS6072");
/*     */     
/* 233 */     disthubMsgMap.put(Integer.valueOf(1472973823), "MQJMS6073");
/*     */     
/* 235 */     disthubMsgMap.put(Integer.valueOf(-2048318799), "MQJMS6351");
/*     */     
/* 237 */     disthubMsgMap.put(Integer.valueOf(-1155560158), "MQJMS6312");
/*     */     
/* 239 */     disthubMsgMap.put(Integer.valueOf(-117071853), "MQJMS1106");
/*     */     
/* 241 */     if (Trace.isOn) {
/* 242 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.services.MQJMSTranslator", "static()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQJMSTranslator() {
/* 251 */     if (Trace.isOn) {
/* 252 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.services.MQJMSTranslator", "<init>()");
/*     */     }
/*     */ 
/*     */     
/* 256 */     if (Trace.isOn) {
/* 257 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.services.MQJMSTranslator", "<init>()");
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
/*     */   public String translateReasonString(int type, Object[] args) {
/* 277 */     if (Trace.isOn) {
/* 278 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.services.MQJMSTranslator", "translateReasonString(int,Object [ ])", new Object[] {
/* 279 */             Integer.valueOf(type), args
/*     */           });
/*     */     }
/* 282 */     String reasonString = null;
/*     */ 
/*     */     
/* 285 */     Integer lookup = Integer.valueOf(type);
/* 286 */     String key = (String)disthubMsgMap.get(lookup);
/*     */ 
/*     */ 
/*     */     
/* 290 */     if (key != null)
/*     */     {
/* 292 */       if (args == null || args.length == 0) {
/* 293 */         reasonString = ConfigEnvironment.getErrorMessage(key);
/*     */       }
/* 295 */       else if (args.length == 1) {
/* 296 */         reasonString = ConfigEnvironment.getErrorMessage(key, args[0]);
/*     */       }
/* 298 */       else if (args.length == 2) {
/* 299 */         reasonString = ConfigEnvironment.getErrorMessage(key, args[0], args[1]);
/*     */       } else {
/* 301 */         reasonString = ConfigEnvironment.getErrorMessage(key, args[0], args[1], args[2]);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 311 */     if (reasonString == null || reasonString.endsWith("<message not found>")) {
/* 312 */       if (Trace.isOn) {
/* 313 */         Trace.traceData(this, "Error code is " + type, null);
/*     */       }
/*     */       
/* 316 */       reasonString = ConfigEnvironment.getErrorMessage("MQJMS1031");
/*     */     } 
/*     */     
/* 319 */     if (Trace.isOn) {
/* 320 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.services.MQJMSTranslator", "translateReasonString(int,Object [ ])", reasonString);
/*     */     }
/*     */     
/* 323 */     return reasonString;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\services\MQJMSTranslator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */