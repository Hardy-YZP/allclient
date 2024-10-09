/*     */ package com.ibm.mq.ese.util;
/*     */ 
/*     */ import com.ibm.mq.jmqi.MQCBD;
/*     */ import com.ibm.mq.jmqi.MQGMO;
/*     */ import com.ibm.mq.jmqi.MQMD;
/*     */ import com.ibm.mq.jmqi.MQOD;
/*     */ import com.ibm.mq.jmqi.MQPMO;
/*     */ import com.ibm.mq.jmqi.handles.Hconn;
/*     */ import com.ibm.mq.jmqi.handles.Hobj;
/*     */ import com.ibm.mq.jmqi.handles.Phconn;
/*     */ import com.ibm.mq.jmqi.handles.Phobj;
/*     */ import com.ibm.mq.jmqi.handles.Pint;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TraceUtil
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/util/TraceUtil.java";
/*     */   private static final int MAX_ARRAY_ELEMENTS_TO_SHOW = 20;
/*     */   private static final String NULL = "<null>";
/*     */   private static final String ENTRY = "Entry ";
/*     */   private static final String EXIT = "Exit ";
/*     */   
/*     */   static {
/*  44 */     if (Trace.isOn) {
/*  45 */       Trace.data("com.ibm.mq.ese.util.TraceUtil", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/util/TraceUtil.java");
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
/*     */   public static String paramsMQCONNasString(boolean entry, String qmgr, Phconn phconn, Pint cc, Pint rc) {
/*  79 */     if (Trace.isOn) {
/*  80 */       Trace.entry("com.ibm.mq.ese.util.TraceUtil", "paramsMQCONNasString(boolean,String,Phconn,Pint,Pint)", new Object[] {
/*  81 */             Boolean.valueOf(entry), qmgr, phconn, cc, rc
/*     */           });
/*     */     }
/*  84 */     String s = entry ? "Entry " : "Exit ";
/*  85 */     StringBuilder sb = (new StringBuilder(s)).append("Args: qmgr = '");
/*  86 */     sb.append(qmgr).append("'; hconn = '").append(phconn);
/*  87 */     sb.append("'; CC = '").append(cc).append("'; RC = '").append(rc)
/*  88 */       .append("'");
/*  89 */     String traceRet1 = sb.toString();
/*  90 */     if (Trace.isOn) {
/*  91 */       Trace.exit("com.ibm.mq.ese.util.TraceUtil", "paramsMQCONNasString(boolean,String,Phconn,Pint,Pint)", traceRet1);
/*     */     }
/*     */     
/*  94 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String join(Object[] array) {
/* 105 */     if (Trace.isOn) {
/* 106 */       Trace.entry("com.ibm.mq.ese.util.TraceUtil", "join(Object [ ])", new Object[] { array });
/*     */     }
/* 108 */     String traceRet1 = join(array, ", ");
/* 109 */     if (Trace.isOn) {
/* 110 */       Trace.exit("com.ibm.mq.ese.util.TraceUtil", "join(Object [ ])", traceRet1);
/*     */     }
/* 112 */     return traceRet1;
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
/*     */   public static String join(Object[] array, String delimiter) {
/* 125 */     if (Trace.isOn) {
/* 126 */       Trace.entry("com.ibm.mq.ese.util.TraceUtil", "join(Object [ ],String)", new Object[] { array, delimiter });
/*     */     }
/*     */     
/* 129 */     StringBuilder bfr = new StringBuilder();
/*     */     
/* 131 */     for (int i = 0; i < array.length; i++) {
/* 132 */       if (i > 0) {
/* 133 */         bfr.append(delimiter);
/*     */       }
/* 135 */       bfr.append(array[i]);
/*     */     } 
/*     */     
/* 138 */     String traceRet1 = bfr.toString();
/* 139 */     if (Trace.isOn) {
/* 140 */       Trace.exit("com.ibm.mq.ese.util.TraceUtil", "join(Object [ ],String)", traceRet1);
/*     */     }
/* 142 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String objectsAsString(Object[] objects) {
/* 152 */     if (Trace.isOn) {
/* 153 */       Trace.entry("com.ibm.mq.ese.util.TraceUtil", "objectsAsString(Object [ ])", new Object[] { objects });
/*     */     }
/*     */     
/* 156 */     if (objects == null) {
/* 157 */       if (Trace.isOn) {
/* 158 */         Trace.exit("com.ibm.mq.ese.util.TraceUtil", "objectsAsString(Object [ ])", "<null>", 1);
/*     */       }
/* 160 */       return "<null>";
/*     */     } 
/* 162 */     StringBuilder buf = new StringBuilder();
/* 163 */     for (int i = 0; i < objects.length; i++) {
/* 164 */       Object object = objects[i];
/* 165 */       if (object instanceof int[]) {
/* 166 */         StringBuilder str = new StringBuilder();
/* 167 */         int[] array = (int[])object;
/* 168 */         for (int j = 0; j < array.length; j++) {
/* 169 */           str.append(array[j]).append(", ");
/* 170 */           if (j > 20) {
/* 171 */             str.append("...");
/*     */             break;
/*     */           } 
/*     */         } 
/* 175 */         buf.append(str).append(';');
/* 176 */       } else if (object instanceof String[]) {
/* 177 */         StringBuilder str = new StringBuilder();
/* 178 */         String[] array = (String[])object;
/* 179 */         for (int j = 0; j < array.length; j++) {
/* 180 */           str.append(array[j]).append(", ");
/* 181 */           if (j > 20) {
/* 182 */             str.append("...");
/*     */             break;
/*     */           } 
/*     */         } 
/* 186 */         buf.append(str).append(';');
/*     */       } else {
/* 188 */         buf.append(object).append(';');
/*     */       } 
/*     */     } 
/* 191 */     String traceRet1 = buf.toString();
/* 192 */     if (Trace.isOn) {
/* 193 */       Trace.exit("com.ibm.mq.ese.util.TraceUtil", "objectsAsString(Object [ ])", traceRet1, 2);
/*     */     }
/*     */     
/* 196 */     return traceRet1;
/*     */   }
/*     */   
/*     */   public static String trim(String s) {
/* 200 */     if (Trace.isOn) {
/* 201 */       Trace.entry("com.ibm.mq.ese.util.TraceUtil", "trim(String)", new Object[] { s });
/*     */     }
/* 203 */     String traceRet1 = (s == null) ? "<null>" : s.trim();
/* 204 */     if (Trace.isOn) {
/* 205 */       Trace.exit("com.ibm.mq.ese.util.TraceUtil", "trim(String)", traceRet1);
/*     */     }
/* 207 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String paramsMQOPENasString(boolean entry, Hconn hconn, MQOD objDesc, Phobj phobj, Pint cc, Pint rc) {
/* 212 */     if (Trace.isOn)
/* 213 */       Trace.entry("com.ibm.mq.ese.util.TraceUtil", "paramsMQOPENasString(boolean,Hconn,MQOD,Phobj,Pint,Pint)", new Object[] {
/*     */             
/* 215 */             Boolean.valueOf(entry), hconn, objDesc, phobj, cc, rc
/*     */           }); 
/* 217 */     String s = entry ? "Entry " : "Exit ";
/* 218 */     StringBuilder sb = (new StringBuilder(s)).append("Args: hconn = '");
/* 219 */     sb.append(hconn).append("'; objDesc = '").append(objDesc);
/* 220 */     sb.append("'; phobj = '").append(phobj);
/* 221 */     sb.append("'; CC = '").append(cc).append("'; RC = '").append(rc)
/* 222 */       .append("'");
/* 223 */     String traceRet1 = sb.toString();
/* 224 */     if (Trace.isOn) {
/* 225 */       Trace.exit("com.ibm.mq.ese.util.TraceUtil", "paramsMQOPENasString(boolean,Hconn,MQOD,Phobj,Pint,Pint)", traceRet1);
/*     */     }
/*     */     
/* 228 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String paramsMQPUTasString(boolean entry, Hconn hconn, Hobj hobj, MQMD msgDesc, MQPMO putMsgOpts, Pint cc, Pint rc) {
/* 233 */     if (Trace.isOn)
/* 234 */       Trace.entry("com.ibm.mq.ese.util.TraceUtil", "paramsMQPUTasString(boolean,Hconn,Hobj,MQMD,MQPMO,Pint,Pint)", new Object[] {
/*     */             
/* 236 */             Boolean.valueOf(entry), hconn, hobj, msgDesc, putMsgOpts, cc, rc
/*     */           }); 
/* 238 */     String s = entry ? "Entry " : "Exit ";
/* 239 */     StringBuilder sb = (new StringBuilder(s)).append("Args: hconn = '");
/* 240 */     sb.append(hconn).append("'; hobj = '").append(hobj);
/* 241 */     sb.append("'; msgDesc = '").append(msgDesc).append("'; putMsgOpts = '");
/* 242 */     sb.append(putMsgOpts).append("'; CC = '").append(cc);
/* 243 */     sb.append("'; RC = '").append(rc).append("'");
/* 244 */     String traceRet1 = sb.toString();
/* 245 */     if (Trace.isOn) {
/* 246 */       Trace.exit("com.ibm.mq.ese.util.TraceUtil", "paramsMQPUTasString(boolean,Hconn,Hobj,MQMD,MQPMO,Pint,Pint)", traceRet1);
/*     */     }
/*     */     
/* 249 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String paramsMQGETasString(boolean entry, Hconn hconn, Hobj hobj, MQMD md, MQGMO gmo, Pint dataLength, Pint cc, Pint rc) {
/* 254 */     if (Trace.isOn)
/* 255 */       Trace.entry("com.ibm.mq.ese.util.TraceUtil", "paramsMQGETasString(boolean,Hconn,Hobj,MQMD,MQGMO,Pint,Pint,Pint)", new Object[] {
/*     */             
/* 257 */             Boolean.valueOf(entry), hconn, hobj, md, gmo, dataLength, cc, rc
/*     */           }); 
/* 259 */     String s = entry ? "Entry " : "Exit ";
/* 260 */     StringBuilder sb = (new StringBuilder(s)).append("Args: hconn = '");
/* 261 */     sb.append(hconn).append("'; hobj = '").append(hobj);
/* 262 */     sb.append("'; msgDesc = '").append(md).append("'; getMsgOpts = '");
/* 263 */     sb.append(gmo).append("'; dataLength = '").append(dataLength);
/* 264 */     sb.append("'; CC = '").append(cc);
/* 265 */     sb.append("'; RC = '").append(rc).append("'");
/* 266 */     String traceRet1 = sb.toString();
/* 267 */     if (Trace.isOn) {
/* 268 */       Trace.exit("com.ibm.mq.ese.util.TraceUtil", "paramsMQGETasString(boolean,Hconn,Hobj,MQMD,MQGMO,Pint,Pint,Pint)", traceRet1);
/*     */     }
/*     */     
/* 271 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String paramsMQCBasString(boolean entry, Hconn hconn, int operation, MQCBD callbackDesc, Hobj hobj, MQMD msgDesc, MQGMO getMsgOpts, Pint cc, Pint rc) {
/* 277 */     if (Trace.isOn) {
/* 278 */       Trace.entry("com.ibm.mq.ese.util.TraceUtil", "paramsMQCBasString(boolean,Hconn,int,MQCBD,Hobj,MQMD,MQGMO,Pint,Pint)", new Object[] {
/*     */             
/* 280 */             Boolean.valueOf(entry), hconn, Integer.valueOf(operation), callbackDesc, hobj, msgDesc, getMsgOpts, cc, rc
/*     */           });
/*     */     }
/* 283 */     String s = entry ? "Entry " : "Exit ";
/* 284 */     StringBuilder sb = (new StringBuilder(s)).append("Args: hconn = '");
/* 285 */     sb.append(hconn).append("'; operation = '").append(operation);
/* 286 */     sb.append("'; callbackDesc = '").append(callbackDesc);
/* 287 */     sb.append("'; hobj = '").append(hobj);
/* 288 */     sb.append("'; msgDesc = '").append(msgDesc);
/* 289 */     sb.append("'; getMsgOpts = '").append(getMsgOpts);
/* 290 */     sb.append("'; CC = '").append(cc);
/* 291 */     sb.append("'; RC = '").append(rc).append("'");
/* 292 */     String traceRet1 = sb.toString();
/* 293 */     if (Trace.isOn) {
/* 294 */       Trace.exit("com.ibm.mq.ese.util.TraceUtil", "paramsMQCBasString(boolean,Hconn,int,MQCBD,Hobj,MQMD,MQGMO,Pint,Pint)", traceRet1);
/*     */     }
/*     */     
/* 297 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String paramsMQPUT1asString(boolean entry, Hconn hconn, MQOD objDesc, MQMD msgDesc, MQPMO putMsgOpts, Pint cc, Pint rc) {
/* 302 */     if (Trace.isOn)
/* 303 */       Trace.entry("com.ibm.mq.ese.util.TraceUtil", "paramsMQPUT1asString(boolean,Hconn,MQOD,MQMD,MQPMO,Pint,Pint)", new Object[] {
/*     */             
/* 305 */             Boolean.valueOf(entry), hconn, objDesc, msgDesc, putMsgOpts, cc, rc
/*     */           }); 
/* 307 */     String s = entry ? "Entry " : "Exit ";
/* 308 */     StringBuilder sb = (new StringBuilder(s)).append("Args: hconn = '");
/* 309 */     sb.append(hconn).append("'; objDesc = '").append(objDesc);
/* 310 */     sb.append("'; msgDesc = '").append(msgDesc).append("'; putMsgOpts = '");
/* 311 */     sb.append(putMsgOpts).append("'; CC = '").append(cc);
/* 312 */     sb.append("'; RC = '").append(rc).append("'");
/* 313 */     String traceRet1 = sb.toString();
/* 314 */     if (Trace.isOn) {
/* 315 */       Trace.exit("com.ibm.mq.ese.util.TraceUtil", "paramsMQPUT1asString(boolean,Hconn,MQOD,MQMD,MQPMO,Pint,Pint)", traceRet1);
/*     */     }
/*     */     
/* 318 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\es\\util\TraceUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */