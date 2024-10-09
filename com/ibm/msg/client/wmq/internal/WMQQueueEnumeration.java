/*     */ package com.ibm.msg.client.wmq.internal;
/*     */ 
/*     */ import com.ibm.mq.MQException;
/*     */ import com.ibm.mq.jmqi.system.SpiOpenOptions;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*     */ import com.ibm.msg.client.provider.ProviderMessage;
/*     */ import com.ibm.msg.client.wmq.common.internal.WMQDestination;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.NoSuchElementException;
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
/*     */ public class WMQQueueEnumeration
/*     */   implements Enumeration<Object>
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq/src/com/ibm/msg/client/wmq/internal/WMQQueueEnumeration.java";
/*     */   private WMQSyncBrowserShadow browserShadow;
/*     */   
/*     */   static {
/*  54 */     if (Trace.isOn) {
/*  55 */       Trace.data("com.ibm.msg.client.wmq.internal.WMQQueueEnumeration", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq/src/com/ibm/msg/client/wmq/internal/WMQQueueEnumeration.java");
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
/*     */   protected static class WMQSyncBrowserShadow
/*     */     extends WMQSyncConsumerShadow
/*     */   {
/*     */     WMQSyncBrowserShadow(JmsPropertyContext jmsProps, WMQSession session, WMQDestination queue, String selector, String subscriptionName) {
/*  71 */       super(jmsProps, session, queue, selector, false, false, false, subscriptionName);
/*  72 */       if (Trace.isOn) {
/*  73 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSyncBrowserShadow", "<init>(JmsPropertyContext,WMQSession,WMQDestination,String,String)", new Object[] { jmsProps, session, queue, selector, subscriptionName });
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*  78 */       this.isBrowser = true;
/*  79 */       if (Trace.isOn) {
/*  80 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSyncBrowserShadow", "<init>(JmsPropertyContext,WMQSession,WMQDestination,String,String)");
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     WMQGMO computeGMO(int waitTime) throws JMSException {
/*  99 */       if (Trace.isOn) {
/* 100 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSyncBrowserShadow", "computeGMO(int)", new Object[] {
/* 101 */               Integer.valueOf(waitTime)
/*     */             });
/*     */       }
/* 104 */       WMQGMO gmo = new WMQGMO(this.env);
/*     */ 
/*     */       
/* 107 */       if (gmo.getVersion() < 3) {
/* 108 */         gmo.setVersion(3);
/*     */       }
/*     */       
/* 111 */       int getOptions = 0;
/*     */ 
/*     */       
/* 114 */       int fiq = this.destination.getIntProperty("failIfQuiesce");
/*     */       
/* 116 */       if (fiq == 1) {
/* 117 */         if (Trace.isOn) {
/* 118 */           Trace.data(this, "Get fail-if-quiesce = yes", null);
/*     */         }
/* 120 */         getOptions |= 0x2000;
/*     */       } 
/*     */ 
/*     */       
/* 124 */       getOptions |= 0x2000024;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 134 */       getOptions |= this.gmoConvertOption;
/*     */       
/* 136 */       gmo.setOptions(getOptions);
/*     */       
/* 138 */       setMatchOptions(this.selectionDetails, gmo);
/*     */       
/* 140 */       if (Trace.isOn) {
/* 141 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSyncBrowserShadow", "computeGMO(int)", gmo);
/*     */       }
/*     */       
/* 144 */       return gmo;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     int computeQueueOpenOptions() throws JMSException {
/* 152 */       if (Trace.isOn) {
/* 153 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSyncBrowserShadow", "computeQueueOpenOptions()");
/*     */       }
/*     */ 
/*     */       
/* 157 */       int options = super.computeQueueOpenOptions();
/*     */ 
/*     */       
/* 160 */       options &= 0xFFFFFF7F;
/* 161 */       options &= 0xFFFFFFFE;
/* 162 */       options |= 0x8;
/*     */       
/* 164 */       if (Trace.isOn) {
/* 165 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSyncBrowserShadow", "computeQueueOpenOptions()", 
/* 166 */             Integer.valueOf(options));
/*     */       }
/* 168 */       return options;
/*     */     }
/*     */ 
/*     */     
/*     */     SpiOpenOptions computeSpiOpenOptions(int queueOpenOptions) {
/* 173 */       if (Trace.isOn)
/* 174 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSyncBrowserShadow", "computeSpiOpenOptions(int)", new Object[] {
/* 175 */               Integer.valueOf(queueOpenOptions)
/*     */             }); 
/* 177 */       SpiOpenOptions spiOO = super.computeSpiOpenOptions(queueOpenOptions);
/* 178 */       int qoo = spiOO.getLpiOptions();
/* 179 */       qoo &= 0xFFFFFFFE;
/* 180 */       qoo &= 0xFFFFFFFD;
/* 181 */       spiOO.setLpiOptions(qoo);
/* 182 */       if (Trace.isOn) {
/* 183 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSyncBrowserShadow", "computeSpiOpenOptions(int)", spiOO);
/*     */       }
/*     */       
/* 186 */       return spiOO;
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
/*     */   private boolean bufferFilled = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ProviderMessage message;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   WMQQueueEnumeration(JmsPropertyContext jmsProps, WMQSession session, WMQDestination queue, String selector) throws JMSException {
/* 215 */     if (Trace.isOn) {
/* 216 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQQueueEnumeration", "<init>(JmsPropertyContext,WMQSession,WMQDestination,String)", new Object[] { jmsProps, session, queue, selector });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 222 */     this.browserShadow = new WMQSyncBrowserShadow(jmsProps, session, queue, selector, null);
/* 223 */     this.browserShadow.initialize();
/* 224 */     if (Trace.isOn) {
/* 225 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQQueueEnumeration", "<init>(JmsPropertyContext,WMQSession,WMQDestination,String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void close() throws JMSException {
/* 235 */     if (Trace.isOn) {
/* 236 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQQueueEnumeration", "close()");
/*     */     }
/* 238 */     if (this.browserShadow != null) {
/* 239 */       this.browserShadow.close();
/* 240 */       this.browserShadow = null;
/*     */     } 
/* 242 */     if (Trace.isOn) {
/* 243 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQQueueEnumeration", "close()");
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
/*     */   public boolean hasMoreElements() {
/*     */     boolean result;
/* 264 */     if (Trace.isOn) {
/* 265 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQQueueEnumeration", "hasMoreElements()");
/*     */     }
/*     */     
/* 268 */     if (this.browserShadow == null) {
/* 269 */       if (Trace.isOn) {
/* 270 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQQueueEnumeration", "hasMoreElements()", 
/* 271 */             Boolean.valueOf(false), 1);
/*     */       }
/* 273 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 279 */     if (this.bufferFilled) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 284 */       if (Trace.isOn) {
/* 285 */         Trace.traceData(this, "hasMoreElements found previously cached message", null);
/*     */       }
/* 287 */       result = true;
/*     */     }
/*     */     else {
/*     */       
/* 291 */       this.message = retrieveMessage();
/* 292 */       if (this.message == null) {
/* 293 */         result = false;
/*     */       } else {
/*     */         
/* 296 */         this.bufferFilled = true;
/* 297 */         result = true;
/*     */         
/* 299 */         if (Trace.isOn) {
/* 300 */           Trace.traceData(this, "hasMoreElements put message in cache", null);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 305 */     if (Trace.isOn) {
/* 306 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQQueueEnumeration", "hasMoreElements()", 
/* 307 */           Boolean.valueOf(result), 2);
/*     */     }
/* 309 */     return result;
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
/*     */   public Object nextElement() throws NoSuchElementException {
/* 322 */     if (Trace.isOn) {
/* 323 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQQueueEnumeration", "nextElement()");
/*     */     }
/* 325 */     if (this.browserShadow == null) {
/* 326 */       NoSuchElementException nsee = new NoSuchElementException();
/* 327 */       if (Trace.isOn) {
/* 328 */         Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQQueueEnumeration", "nextElement()", nsee, 1);
/*     */       }
/*     */       
/* 331 */       throw nsee;
/*     */     } 
/*     */     
/* 334 */     ProviderMessage result = null;
/* 335 */     if (this.bufferFilled) {
/*     */       
/* 337 */       result = this.message;
/* 338 */       this.bufferFilled = false;
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 344 */       result = retrieveMessage();
/*     */     } 
/*     */ 
/*     */     
/* 348 */     if (result == null) {
/* 349 */       NoSuchElementException nsee = new NoSuchElementException();
/* 350 */       if (Trace.isOn) {
/* 351 */         Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQQueueEnumeration", "nextElement()", nsee, 2);
/*     */       }
/*     */       
/* 354 */       throw nsee;
/*     */     } 
/*     */     
/* 357 */     if (Trace.isOn) {
/* 358 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQQueueEnumeration", "nextElement()", result);
/*     */     }
/*     */     
/* 361 */     return result;
/*     */   }
/*     */   
/*     */   private ProviderMessage retrieveMessage() {
/* 365 */     if (Trace.isOn) {
/* 366 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQQueueEnumeration", "retrieveMessage()");
/*     */     }
/*     */     
/*     */     try {
/* 370 */       ProviderMessage m = this.browserShadow.receive(-1L);
/* 371 */       if (Trace.isOn) {
/* 372 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQQueueEnumeration", "retrieveMessage()", m, 1);
/*     */       }
/*     */       
/* 375 */       return m;
/*     */     }
/* 377 */     catch (JMSException je) {
/* 378 */       if (Trace.isOn) {
/* 379 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQQueueEnumeration", "retrieveMessage()", (Throwable)je, 1);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 387 */       Exception e = je.getLinkedException();
/* 388 */       if (e instanceof MQException) {
/* 389 */         MQException wex = (MQException)e;
/* 390 */         int reason = wex.getReason();
/* 391 */         if (reason == 2033) {
/*     */           try {
/* 393 */             close();
/*     */           }
/* 395 */           catch (JMSException je2) {
/* 396 */             if (Trace.isOn) {
/* 397 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQQueueEnumeration", "retrieveMessage()", (Throwable)je2, 2);
/*     */             }
/*     */           }
/*     */         
/*     */         }
/*     */       }
/*     */     
/*     */     }
/* 405 */     catch (Exception e) {
/* 406 */       if (Trace.isOn) {
/* 407 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQQueueEnumeration", "retrieveMessage()", e, 3);
/*     */       }
/*     */       
/* 410 */       HashMap<String, Object> ffstData = new HashMap<>();
/* 411 */       ffstData.put("Exception", e);
/* 412 */       ffstData.put("browserShadow", this.browserShadow);
/* 413 */       Trace.ffst(this, "nextElement()", "XN00H001", ffstData, null);
/*     */     } 
/*     */     
/* 416 */     if (Trace.isOn) {
/* 417 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQQueueEnumeration", "retrieveMessage()", null, 2);
/*     */     }
/*     */     
/* 420 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\internal\WMQQueueEnumeration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */