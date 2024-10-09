/*     */ package com.ibm.mq.headers;
/*     */ 
/*     */ import com.ibm.mq.headers.pcf.PCFHeaderFactory;
/*     */ import com.ibm.mq.internal.MQCommonServices;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.DataInput;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQHeaderRegistry
/*     */   extends JmqiObject
/*     */   implements MQHeaderFactory.Registry
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQHeaderRegistry.java";
/*     */   private static final String FORMAT_PREFIX = "f:";
/*     */   private static final String TYPE_PREFIX = "t:";
/*     */   private static MQHeaderRegistry DEFAULT;
/*     */   private final Map<String, MQHeaderFactory> map;
/*     */   
/*     */   static {
/*  70 */     if (Trace.isOn) {
/*  71 */       Trace.data("com.ibm.mq.headers.MQHeaderRegistry", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQHeaderRegistry.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private MQHeaderRegistry() {
/*  79 */     super(MQCommonServices.jmqiEnv);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  96 */     this.map = new HashMap<>();
/*     */     if (Trace.isOn)
/*     */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderRegistry", "<init>()"); 
/*     */     if (Trace.isOn)
/*     */       Trace.exit(this, "com.ibm.mq.headers.MQHeaderRegistry", "<init>()"); 
/*     */   } public static synchronized MQHeaderRegistry getDefault() {
/* 102 */     if (DEFAULT == null) {
/* 103 */       MQHeaderRegistry registry = new MQHeaderRegistry();
/*     */       
/* 105 */       registry.register("MQDEAD  ", "MQDLH", MQDLH.class);
/* 106 */       registry.register("MQXMIT  ", "MQXQH", MQXQH.class);
/* 107 */       registry.register("MQHMDE  ", "MQMDE", MQMDE.class);
/* 108 */       registry.register("MQHDIST ", "MQDH", MQDH.class);
/* 109 */       registry.register("MQCICS  ", "MQCIH", MQCIH.class);
/* 110 */       registry.register("MQIMS   ", "MQIIH", MQIIH.class);
/* 111 */       registry.register("MQHRF2  ", "MQRFH2", MQRFH2.class);
/* 112 */       registry.register("MQHRF   ", "MQRFH", MQRFH.class);
/* 113 */       registry.register("MQHREF  ", "MQRMH", MQRMH.class);
/* 114 */       registry.register("MQTRIG  ", "MQTM", MQTM.class);
/* 115 */       registry.register("MQHWIH  ", "MQWIH", MQWIH.class);
/* 116 */       registry.register((MQHeaderFactory)new PCFHeaderFactory());
/*     */       
/* 118 */       DEFAULT = registry;
/*     */     } 
/*     */     
/* 121 */     if (Trace.isOn) {
/* 122 */       Trace.data("com.ibm.mq.headers.MQHeaderRegistry", "getDefault()", "getter", DEFAULT);
/*     */     }
/* 124 */     return DEFAULT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQHeaderFactory getFactoryForFormat(String format) {
/* 132 */     if (Trace.isOn) {
/* 133 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderRegistry", "getFactoryForFormat(String)", new Object[] { format });
/*     */     }
/*     */     
/* 136 */     MQHeaderFactory traceRet1 = this.map.get("f:" + format.trim());
/* 137 */     if (Trace.isOn) {
/* 138 */       Trace.exit(this, "com.ibm.mq.headers.MQHeaderRegistry", "getFactoryForFormat(String)", traceRet1);
/*     */     }
/*     */     
/* 141 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQHeaderFactory getFactoryForType(String type) {
/* 149 */     if (Trace.isOn) {
/* 150 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderRegistry", "getFactoryForType(String)", new Object[] { type });
/*     */     }
/*     */     
/* 153 */     MQHeaderFactory traceRet1 = this.map.get("t:" + type.trim());
/* 154 */     if (Trace.isOn) {
/* 155 */       Trace.exit(this, "com.ibm.mq.headers.MQHeaderRegistry", "getFactoryForType(String)", traceRet1);
/*     */     }
/*     */     
/* 158 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void register(MQHeaderFactory factory) {
/* 166 */     if (Trace.isOn) {
/* 167 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderRegistry", "register(MQHeaderFactory)", new Object[] { factory });
/*     */     }
/*     */     
/* 170 */     Iterator<?> formats = factory.getSupportedFormats().iterator();
/*     */     
/* 172 */     while (formats.hasNext()) {
/* 173 */       this.map.put("f:" + ((String)formats.next()).trim(), factory);
/*     */     }
/*     */     
/* 176 */     Iterator<?> types = factory.getSupportedTypes().iterator();
/*     */     
/* 178 */     while (types.hasNext()) {
/* 179 */       this.map.put("t:" + ((String)types.next()).trim(), factory);
/*     */     }
/* 181 */     if (Trace.isOn) {
/* 182 */       Trace.exit(this, "com.ibm.mq.headers.MQHeaderRegistry", "register(MQHeaderFactory)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void register(String format, String type, Class<?> headerClass) {
/* 193 */     if (Trace.isOn) {
/* 194 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderRegistry", "register(String,String,Class<?>)", new Object[] { format, type, headerClass });
/*     */     }
/*     */     
/* 197 */     register(new DefaultHeaderFactory(format, type, headerClass));
/* 198 */     if (Trace.isOn) {
/* 199 */       Trace.exit(this, "com.ibm.mq.headers.MQHeaderRegistry", "register(String,String,Class<?>)");
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
/*     */   public void register(String format, String type, String className) throws ClassNotFoundException {
/* 211 */     if (Trace.isOn) {
/* 212 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderRegistry", "register(String,String,String)", new Object[] { format, type, className });
/*     */     }
/*     */     
/* 215 */     register(new DefaultHeaderFactory(format, type, className));
/* 216 */     if (Trace.isOn) {
/* 217 */       Trace.exit(this, "com.ibm.mq.headers.MQHeaderRegistry", "register(String,String,String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<?> getRegisteredFormats() {
/* 227 */     ArrayList<String> formats = new ArrayList<>();
/* 228 */     Iterator<String> it = this.map.keySet().iterator();
/*     */     
/* 230 */     while (it.hasNext()) {
/* 231 */       String key = it.next();
/*     */       
/* 233 */       if (key.startsWith("f:")) {
/* 234 */         formats.add(key.substring("f:".length()));
/*     */       }
/*     */     } 
/*     */     
/* 238 */     if (Trace.isOn) {
/* 239 */       Trace.data(this, "com.ibm.mq.headers.MQHeaderRegistry", "getRegisteredFormats()", "getter", formats);
/*     */     }
/*     */     
/* 242 */     return formats;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<?> getRegisteredTypes() {
/* 250 */     ArrayList<String> types = new ArrayList<>();
/* 251 */     Iterator<String> it = this.map.keySet().iterator();
/*     */     
/* 253 */     while (it.hasNext()) {
/* 254 */       String key = it.next();
/*     */       
/* 256 */       if (key.startsWith("t:")) {
/* 257 */         types.add(key.substring("t:".length()));
/*     */       }
/*     */     } 
/*     */     
/* 261 */     if (Trace.isOn) {
/* 262 */       Trace.data(this, "com.ibm.mq.headers.MQHeaderRegistry", "getRegisteredTypes()", "getter", types);
/*     */     }
/*     */     
/* 265 */     return types;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQHeaderIterator createIterator(DataInput message) {
/* 274 */     if (Trace.isOn) {
/* 275 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderRegistry", "createIterator(DataInput)", new Object[] { message });
/*     */     }
/*     */     
/* 278 */     MQHeaderIterator it = new MQHeaderIterator(message);
/*     */     
/* 280 */     it.registry = this;
/*     */     
/* 282 */     if (Trace.isOn) {
/* 283 */       Trace.exit(this, "com.ibm.mq.headers.MQHeaderRegistry", "createIterator(DataInput)", it);
/*     */     }
/* 285 */     return it;
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
/*     */   public MQHeaderIterator createIterator(DataInput message, String format, int encoding, int characterSet) {
/* 297 */     if (Trace.isOn) {
/* 298 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderRegistry", "createIterator(DataInput,String,int,int)", new Object[] { message, format, 
/*     */             
/* 300 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*     */     }
/* 302 */     MQHeaderIterator it = new MQHeaderIterator(message, format, encoding, characterSet);
/*     */     
/* 304 */     it.registry = this;
/*     */     
/* 306 */     if (Trace.isOn) {
/* 307 */       Trace.exit(this, "com.ibm.mq.headers.MQHeaderRegistry", "createIterator(DataInput,String,int,int)", it);
/*     */     }
/*     */     
/* 310 */     return it;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 319 */     if (Trace.isOn) {
/* 320 */       Trace.entry(this, "com.ibm.mq.headers.MQHeaderRegistry", "toString()");
/*     */     }
/*     */ 
/*     */     
/* 324 */     String traceRet1 = getClass().getName() + "[Formats: " + getRegisteredFormats() + ", types: " + getRegisteredTypes() + "]";
/* 325 */     if (Trace.isOn) {
/* 326 */       Trace.exit(this, "com.ibm.mq.headers.MQHeaderRegistry", "toString()", traceRet1);
/*     */     }
/* 328 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\MQHeaderRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */