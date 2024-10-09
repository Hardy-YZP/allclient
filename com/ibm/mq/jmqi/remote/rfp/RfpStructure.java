/*     */ package com.ibm.mq.jmqi.remote.rfp;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*     */ import com.ibm.mq.jmqi.remote.rfp.spi.RfpVERBSTRUCT;
/*     */ import com.ibm.mq.jmqi.remote.util.RemoteConstantDecoder;
/*     */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.lang.ref.Reference;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class RfpStructure
/*     */   extends JmqiObject
/*     */ {
/*     */   private static Reference<Field[]> fieldsRef;
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpStructure.java";
/*     */   public static final int _RFP_TSH = 0;
/*     */   public static final int _RFP_MQAPI = 1;
/*     */   public static final int _RFP_ID = 2;
/*     */   public static final int _RFP_UID = 3;
/*     */   public static final int _RFP_MQCONN = 4;
/*     */   public static final int _RFP_MQCLOSE = 5;
/*     */   public static final int _RFP_MQINQ = 6;
/*     */   public static final int _RFP_MQSET = 7;
/*     */   public static final int _RFP_FAPMQCNO = 8;
/*     */   public static final int _RFP_MQOPEN_PRIV = 9;
/*     */   public static final int _RFP_VERBSTRUCT = 10;
/*     */   public static final int _RFP_XA_COMPLETE = 11;
/*     */   public static final int _RFP_XA_INFO = 12;
/*     */   public static final int _RFP_XAID = 13;
/*     */   public static final int _RFP_XAIDS = 14;
/*     */   public static final int _RFP_MQSTAT = 15;
/*     */   public static final int _RFP_SOCKACT = 16;
/*     */   public static final int _RFP_SET_SELECTION = 17;
/*     */   public static final int _RFP_ASYNC_MESSAGE = 18;
/*     */   public static final int _RFP_REQUEST_MSGS = 19;
/*     */   public static final int _RFP_NOTIFICATION = 20;
/*     */   public static final int _RFP_ESH = 21;
/*     */   public static final int _RFP_MPH = 22;
/*     */   public static final int _RFP_MQNOTIFY = 23;
/*     */   public static final int _RFP_MAX = 23;
/*     */   protected JmqiDC dc;
/*     */   
/*     */   static {
/*  52 */     if (Trace.isOn) {
/*  53 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.RfpStructure", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpStructure.java");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static RfpStructure newRfp(JmqiEnvironment env, int index, byte[] buffer, int offset) throws JmqiException {
/*     */     RfpVERBSTRUCT rfpVERBSTRUCT;
/*     */     RfpXA_COMPLETE rfpXA_COMPLETE;
/*     */     RfpXA_INFO rfpXA_INFO;
/*     */     RfpXAID rfpXAID;
/*     */     RfpXAIDS rfpXAIDS;
/*     */     RfpMQSTAT rfpMQSTAT;
/*     */     RfpSOCKACT rfpSOCKACT;
/*     */     RfpASYNC_MESSAGE rfpASYNC_MESSAGE;
/*     */     RfpREQUEST_MSGS rfpREQUEST_MSGS;
/*     */     RfpNOTIFICATION rfpNOTIFICATION;
/*     */     RfpESH rfpESH;
/*     */     RfpMPH rfpMPH;
/*     */     RfpMQAPI rfpMQAPI;
/*     */     HashMap<String, Object> info;
/*     */     JmqiException traceRet1;
/* 147 */     if (Trace.isOn) {
/* 148 */       Trace.entry("com.ibm.mq.jmqi.remote.rfp.RfpStructure", "newRfp(JmqiEnvironment,int,byte [ ],int)", new Object[] { env, 
/* 149 */             Integer.valueOf(index), buffer, 
/* 150 */             Integer.valueOf(offset) });
/*     */     }
/* 152 */     RfpStructure newStructure = null;
/* 153 */     switch (index) {
/*     */       case 0:
/* 155 */         newStructure = new RfpTSH(env, buffer, offset);
/*     */         break;
/*     */       
/*     */       case 1:
/* 159 */         newStructure = new RfpMQAPI(env, buffer, offset);
/*     */         break;
/*     */       
/*     */       case 3:
/* 163 */         newStructure = new RfpUID(env, buffer, offset);
/*     */         break;
/*     */       
/*     */       case 4:
/* 167 */         newStructure = new RfpMQCONN(env, buffer, offset);
/*     */         break;
/*     */       
/*     */       case 5:
/* 171 */         newStructure = new RfpMQCLOSE(env, buffer, offset);
/*     */         break;
/*     */       
/*     */       case 6:
/* 175 */         newStructure = new RfpMQINQ(env, buffer, offset);
/*     */         break;
/*     */       
/*     */       case 7:
/* 179 */         newStructure = new RfpMQSET(env, buffer, offset);
/*     */         break;
/*     */       
/*     */       case 8:
/* 183 */         newStructure = new RfpFAPMQCNO(env, buffer, offset);
/*     */         break;
/*     */       
/*     */       case 9:
/* 187 */         newStructure = new RfpMQOPEN_PRIV(env, buffer, offset);
/*     */         break;
/*     */       
/*     */       case 10:
/* 191 */         rfpVERBSTRUCT = new RfpVERBSTRUCT(env, buffer, offset);
/*     */         break;
/*     */       
/*     */       case 11:
/* 195 */         rfpXA_COMPLETE = new RfpXA_COMPLETE(env, buffer, offset);
/*     */         break;
/*     */       
/*     */       case 12:
/* 199 */         rfpXA_INFO = new RfpXA_INFO(env, buffer, offset);
/*     */         break;
/*     */       
/*     */       case 13:
/* 203 */         rfpXAID = new RfpXAID(env, buffer, offset);
/*     */         break;
/*     */       
/*     */       case 14:
/* 207 */         rfpXAIDS = new RfpXAIDS(env, buffer, offset);
/*     */         break;
/*     */       
/*     */       case 15:
/* 211 */         rfpMQSTAT = new RfpMQSTAT(env, buffer, offset);
/*     */         break;
/*     */       
/*     */       case 16:
/* 215 */         rfpSOCKACT = new RfpSOCKACT(env, buffer, offset);
/*     */         break;
/*     */       
/*     */       case 18:
/* 219 */         rfpASYNC_MESSAGE = new RfpASYNC_MESSAGE(env, buffer, offset);
/*     */         break;
/*     */       
/*     */       case 19:
/* 223 */         rfpREQUEST_MSGS = new RfpREQUEST_MSGS(env, buffer, offset);
/*     */         break;
/*     */       
/*     */       case 20:
/* 227 */         rfpNOTIFICATION = new RfpNOTIFICATION(env, buffer, offset);
/*     */         break;
/*     */       
/*     */       case 21:
/* 231 */         rfpESH = new RfpESH(env, buffer, offset);
/*     */         break;
/*     */       
/*     */       case 22:
/* 235 */         rfpMPH = new RfpMPH(env, buffer, offset);
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 23:
/* 241 */         rfpMQAPI = new RfpMQAPI(env, buffer, offset);
/*     */         break;
/*     */       
/*     */       default:
/* 245 */         info = new HashMap<>();
/* 246 */         info.put("index", Integer.valueOf(index));
/* 247 */         info.put("Description", "Unexpected flow received from server");
/* 248 */         Trace.ffst("com.ibm.mq.jmqi.remote.rfp.RfpStructure", "newRfp(JmqiEnvironment,int,byte [ ],int)", "01", info, null);
/* 249 */         traceRet1 = new JmqiException(env, -1, null, 2, 2195, null);
/* 250 */         if (Trace.isOn) {
/* 251 */           Trace.throwing("com.ibm.mq.jmqi.remote.rfp.RfpStructure", "newRfp(JmqiEnvironment,int,byte [ ],int)", (Throwable)traceRet1);
/*     */         }
/*     */         
/* 254 */         throw traceRet1;
/*     */     } 
/*     */ 
/*     */     
/* 258 */     if (Trace.isOn) {
/* 259 */       Trace.exit("com.ibm.mq.jmqi.remote.rfp.RfpStructure", "newRfp(JmqiEnvironment,int,byte [ ],int)", rfpMQAPI);
/*     */     }
/*     */     
/* 262 */     return rfpMQAPI;
/*     */   }
/*     */   
/* 265 */   protected byte[] buffer = null;
/*     */ 
/*     */   
/* 268 */   public int offset = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RfpStructure(JmqiEnvironment env, byte[] buffer, int offset) {
/* 280 */     super(env);
/* 281 */     this.buffer = buffer;
/* 282 */     this.offset = offset;
/* 283 */     if (env instanceof JmqiSystemEnvironment) {
/* 284 */       this.dc = ((JmqiSystemEnvironment)env).getDC();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRfpOffset() {
/* 293 */     return this.offset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRfpOffset(int offset) {
/* 301 */     this.offset = offset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getRfpBuffer() {
/* 309 */     return this.buffer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRfpBuffer(byte[] buffer) {
/* 317 */     this.buffer = buffer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String decode(int value) {
/* 325 */     if (Trace.isOn)
/* 326 */       Trace.entry("com.ibm.mq.jmqi.remote.rfp.RfpStructure", "decode(int)", new Object[] {
/* 327 */             Integer.valueOf(value)
/*     */           }); 
/* 329 */     String traceRet1 = RemoteConstantDecoder.formatSingleOption(value, getFields(), "_RFP_");
/* 330 */     if (Trace.isOn) {
/* 331 */       Trace.exit("com.ibm.mq.jmqi.remote.rfp.RfpStructure", "decode(int)", traceRet1);
/*     */     }
/* 333 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static synchronized Field[] getFields() {
/* 341 */     Reference<Field[]> ref = fieldsRef;
/*     */     
/*     */     Field[] fields;
/* 344 */     if (ref == null || (fields = ref.get()) == null) {
/* 345 */       fieldsRef = (Reference)new SoftReference<>(fields = RfpStructure.class.getFields());
/*     */     }
/*     */     
/* 348 */     if (Trace.isOn) {
/* 349 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.RfpStructure", "getFields()", "getter", fields);
/*     */     }
/* 351 */     return fields;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\RfpStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */