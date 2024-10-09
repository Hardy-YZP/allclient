/*     */ package com.ibm.msg.client.wmq.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.Log.Log;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*     */ import com.ibm.msg.client.provider.ProviderMessage;
/*     */ import com.ibm.msg.client.provider.ProviderMessageListener;
/*     */ import com.ibm.msg.client.wmq.common.internal.WMQDestination;
/*     */ import com.ibm.msg.client.wmq.common.internal.messages.WMQMessage;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Iterator;
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
/*     */ public class WMQMessageRetentionProcessor
/*     */ {
/*     */   private static final String NULL_SELECTOR = "<NULL>";
/*     */   
/*     */   static {
/*  49 */     if (Trace.isOn) {
/*  50 */       Trace.data("com.ibm.msg.client.wmq.internal.WMQMessageRetentionProcessor", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq/src/com/ibm/msg/client/wmq/internal/WMQMessageRetentionProcessor.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   private static Hashtable<String, WMQMessageRetentionAgent> agents = new Hashtable<>();
/*     */   private static WMQMessageRetentionProcessor retentionProcessor;
/*     */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq/src/com/ibm/msg/client/wmq/internal/WMQMessageRetentionProcessor.java";
/*     */   
/*     */   private static class WMQMessageRetentionAgent implements ProviderMessageListener {
/*  62 */     private Hashtable<WMQConnectionBrowser, String> browsers = new Hashtable<>();
/*     */     private WMQMessageConsumer consumer;
/*     */     private WMQDestination destination;
/*  65 */     private int nullSelectors = 0;
/*     */ 
/*     */     
/*     */     private WMQPoison poisonHandler;
/*     */ 
/*     */     
/*     */     private WMQSession session;
/*     */ 
/*     */ 
/*     */     
/*     */     public WMQMessageRetentionAgent(WMQDestination destination) {
/*  76 */       if (Trace.isOn) {
/*  77 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQMessageRetentionAgent", "<init>(WMQDestination)", new Object[] { destination });
/*     */       }
/*     */       
/*  80 */       this.destination = destination;
/*  81 */       if (Trace.isOn) {
/*  82 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQMessageRetentionAgent", "<init>(WMQDestination)");
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
/*     */     public void addBrowser(WMQConnectionBrowser browser, String selector) throws JMSException {
/*  97 */       if (Trace.isOn) {
/*  98 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQMessageRetentionAgent", "addBrowser(WMQConnectionBrowser,String)", new Object[] { browser, selector });
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 107 */       String selectorString = selector;
/* 108 */       if (selector == null) {
/* 109 */         if (Trace.isOn) {
/* 110 */           Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQMessageRetentionAgent", "addBrowser(WMQConnectionBrowser,String)", "The connection browser is not using a selector. Adding it to the hashtable using the special selector string <NULL>");
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 115 */         selectorString = "<NULL>";
/*     */         
/* 117 */         this.nullSelectors++;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 122 */       this.browsers.put(browser, selectorString);
/*     */ 
/*     */       
/* 125 */       updateSelector();
/* 126 */       if (Trace.isOn) {
/* 127 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQMessageRetentionAgent", "addBrowser(WMQConnectionBrowser,String)");
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
/*     */     public int removeBrowser(WMQConnectionBrowser browser) throws JMSException {
/* 143 */       if (Trace.isOn) {
/* 144 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQMessageRetentionAgent", "removeBrowser(WMQConnectionBrowser)", new Object[] { browser });
/*     */       }
/*     */ 
/*     */       
/* 148 */       String selector = this.browsers.get(browser);
/* 149 */       if ("<NULL>".equals(selector)) {
/* 150 */         this.nullSelectors--;
/*     */       }
/*     */ 
/*     */       
/* 154 */       this.browsers.remove(browser);
/*     */ 
/*     */       
/* 157 */       updateSelector();
/*     */ 
/*     */       
/* 160 */       int traceRet1 = this.browsers.size();
/* 161 */       if (Trace.isOn) {
/* 162 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQMessageRetentionAgent", "removeBrowser(WMQConnectionBrowser)", 
/* 163 */             Integer.valueOf(traceRet1));
/*     */       }
/* 165 */       return traceRet1;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private void updateSelector() throws JMSException {
/* 174 */       if (Trace.isOn) {
/* 175 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQMessageRetentionAgent", "updateSelector()");
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 181 */       if (this.nullSelectors == 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 186 */         Iterator<String> it = this.browsers.values().iterator();
/*     */ 
/*     */         
/* 189 */         if (it.hasNext()) {
/*     */           WMQConnectionBrowser wMQConnectionBrowser1;
/* 191 */           StringBuilder sb = new StringBuilder("NOT((");
/* 192 */           sb.append(it.next());
/* 193 */           sb.append(")");
/* 194 */           while (it.hasNext()) {
/* 195 */             sb.append(" AND (");
/* 196 */             sb.append(it.next());
/* 197 */             sb.append(")");
/*     */           } 
/* 199 */           sb.append(")");
/*     */ 
/*     */           
/* 202 */           if (this.consumer != null) {
/* 203 */             this.consumer.close(false);
/*     */           }
/*     */ 
/*     */           
/* 207 */           if (this.session != null) {
/* 208 */             this.session.close(false);
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 213 */           WMQConnectionBrowser firstBrowser = this.browsers.keys().nextElement();
/* 214 */           WMQConnection conn = firstBrowser.getConnection();
/* 215 */           JmsPropertyContext properties = null;
/*     */ 
/*     */ 
/*     */           
/*     */           try {
/* 220 */             properties = (JmsPropertyContext)firstBrowser.clone();
/*     */           }
/* 222 */           catch (CloneNotSupportedException e) {
/* 223 */             if (Trace.isOn) {
/* 224 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQMessageRetentionAgent", "updateSelector()", e);
/*     */             }
/*     */             
/* 227 */             if (Trace.isOn) {
/* 228 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQMessageRetentionAgent", "updateSelector()", e);
/*     */             }
/*     */ 
/*     */             
/* 232 */             wMQConnectionBrowser1 = firstBrowser;
/*     */           } 
/*     */ 
/*     */           
/* 236 */           wMQConnectionBrowser1.setIntProperty("XMSC_ACKNOWLEDGE_MODE", 0);
/*     */           
/* 238 */           this.session = (WMQSession)conn.createSession((JmsPropertyContext)wMQConnectionBrowser1);
/* 239 */           this.session.start();
/*     */ 
/*     */           
/* 242 */           this.consumer = new WMQMessageConsumer(this.destination, this.session, null, sb.toString(), false, false, false, (JmsPropertyContext)firstBrowser);
/*     */           
/* 244 */           this.consumer.setMessageListener(this);
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 249 */           if (this.consumer != null) {
/* 250 */             this.consumer.close(false);
/*     */           }
/*     */           
/* 253 */           if (this.session != null) {
/* 254 */             this.session.close(false);
/*     */           }
/*     */         } 
/*     */       } 
/* 258 */       if (Trace.isOn) {
/* 259 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQMessageRetentionAgent", "updateSelector()");
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
/*     */     public void onMessage(ProviderMessage message) {
/* 273 */       if (Trace.isOn) {
/* 274 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQMessageRetentionAgent", "onMessage(ProviderMessage)", new Object[] { message });
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 283 */         if (this.poisonHandler == null) {
/* 284 */           this.poisonHandler = new WMQPoison(this.session, this.destination, this.consumer.getHobj(), null);
/*     */         }
/* 286 */         this.poisonHandler.handlePoisonMessage((WMQMessage)message, 2363);
/*     */       }
/* 288 */       catch (JMSException e) {
/* 289 */         if (Trace.isOn) {
/* 290 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQMessageRetentionAgent", "onMessage(ProviderMessage)", (Throwable)e);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 297 */         String exceptionMsg = e.getMessage();
/* 298 */         int substringTo = exceptionMsg.indexOf(":");
/* 299 */         Log.log(this, "onMessage", exceptionMsg.substring(0, substringTo), null);
/*     */       } 
/* 301 */       if (Trace.isOn) {
/* 302 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQMessageRetentionAgent", "onMessage(ProviderMessage)");
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private WMQMessageRetentionProcessor() {
/* 313 */     if (Trace.isOn) {
/* 314 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQMessageRetentionProcessor", "<init>()");
/*     */     }
/*     */ 
/*     */     
/* 318 */     if (Trace.isOn) {
/* 319 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQMessageRetentionProcessor", "<init>()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static WMQMessageRetentionProcessor getInstance() {
/* 329 */     if (retentionProcessor == null) {
/* 330 */       retentionProcessor = new WMQMessageRetentionProcessor();
/*     */     }
/*     */     
/* 333 */     if (Trace.isOn) {
/* 334 */       Trace.data("com.ibm.msg.client.wmq.internal.WMQMessageRetentionProcessor", "getInstance()", "getter", retentionProcessor);
/*     */     }
/*     */     
/* 337 */     return retentionProcessor;
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
/*     */   public void registerConnectionBrowser(WMQConnectionBrowser connectionBrowser, WMQDestination destination, String selector) throws JMSException {
/* 354 */     if (Trace.isOn) {
/* 355 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQMessageRetentionProcessor", "registerConnectionBrowser(WMQConnectionBrowser,WMQDestination,String)", new Object[] { connectionBrowser, destination, selector });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 361 */     String destinationName = destination.getStringProperty("XMSC_WMQ_QUEUE_MANAGER") + "/" + destination.getName();
/*     */ 
/*     */     
/* 364 */     if (agents.containsKey(destinationName)) {
/*     */       
/* 366 */       WMQMessageRetentionAgent consumer = agents.get(destinationName);
/*     */ 
/*     */       
/* 369 */       consumer.addBrowser(connectionBrowser, selector);
/*     */     }
/*     */     else {
/*     */       
/* 373 */       WMQMessageRetentionAgent consumer = new WMQMessageRetentionAgent(destination);
/*     */ 
/*     */       
/* 376 */       consumer.addBrowser(connectionBrowser, selector);
/*     */ 
/*     */       
/* 379 */       agents.put(destinationName, consumer);
/*     */     } 
/* 381 */     if (Trace.isOn) {
/* 382 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQMessageRetentionProcessor", "registerConnectionBrowser(WMQConnectionBrowser,WMQDestination,String)");
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
/*     */   public void removeConnectionBrowser(WMQConnectionBrowser connectionBrowser, WMQDestination destination) throws JMSException {
/* 399 */     if (Trace.isOn) {
/* 400 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQMessageRetentionProcessor", "removeConnectionBrowser(WMQConnectionBrowser,WMQDestination)", new Object[] { connectionBrowser, destination });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 406 */     String destinationName = destination.getStringProperty("XMSC_WMQ_QUEUE_MANAGER") + "/" + destination.getName();
/*     */ 
/*     */     
/* 409 */     if (agents.containsKey(destinationName)) {
/*     */       
/* 411 */       WMQMessageRetentionAgent consumer = agents.get(destinationName);
/* 412 */       int numLeft = consumer.removeBrowser(connectionBrowser);
/*     */ 
/*     */ 
/*     */       
/* 416 */       if (numLeft <= 0) {
/* 417 */         agents.remove(destinationName);
/*     */       }
/*     */     } 
/*     */     
/* 421 */     if (Trace.isOn)
/* 422 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQMessageRetentionProcessor", "removeConnectionBrowser(WMQConnectionBrowser,WMQDestination)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\internal\WMQMessageRetentionProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */