/*     */ package com.ibm.msg.client.wmq.factories;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.provider.ProviderBytesMessage;
/*     */ import com.ibm.msg.client.provider.ProviderMapMessage;
/*     */ import com.ibm.msg.client.provider.ProviderMessage;
/*     */ import com.ibm.msg.client.provider.ProviderMessageFactory;
/*     */ import com.ibm.msg.client.provider.ProviderObjectMessage;
/*     */ import com.ibm.msg.client.provider.ProviderSession;
/*     */ import com.ibm.msg.client.provider.ProviderStreamMessage;
/*     */ import com.ibm.msg.client.provider.ProviderTextMessage;
/*     */ import com.ibm.msg.client.wmq.common.internal.messages.WMQBytesMessage;
/*     */ import com.ibm.msg.client.wmq.common.internal.messages.WMQMapMessage;
/*     */ import com.ibm.msg.client.wmq.common.internal.messages.WMQNullMessage;
/*     */ import com.ibm.msg.client.wmq.common.internal.messages.WMQObjectMessage;
/*     */ import com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage;
/*     */ import com.ibm.msg.client.wmq.common.internal.messages.WMQTextMessage;
/*     */ import com.ibm.msg.client.wmq.compat.jms.internal.MQSession;
/*     */ import com.ibm.msg.client.wmq.internal.WMQSession;
/*     */ import com.ibm.msg.client.wmq.messages.TransientBytesMessage;
/*     */ import com.ibm.msg.client.wmq.messages.TransientMapMessage;
/*     */ import com.ibm.msg.client.wmq.messages.TransientMessage;
/*     */ import com.ibm.msg.client.wmq.messages.TransientObjectMessage;
/*     */ import com.ibm.msg.client.wmq.messages.TransientStreamMessage;
/*     */ import com.ibm.msg.client.wmq.messages.TransientTextMessage;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
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
/*     */ public class WMQMessageFactory
/*     */   implements ProviderMessageFactory
/*     */ {
/*     */   static {
/*  61 */     if (Trace.isOn) {
/*  62 */       Trace.data("com.ibm.msg.client.wmq.factories.WMQMessageFactory", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.factories/src/com/ibm/msg/client/wmq/factories/WMQMessageFactory.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  69 */   private static Vector<String> jmsXPropertyNames = new Vector<>();
/*     */   
/*     */   static {
/*  72 */     if (Trace.isOn) {
/*  73 */       Trace.entry("com.ibm.msg.client.wmq.factories.WMQMessageFactory", "static()");
/*     */     }
/*     */     
/*  76 */     jmsXPropertyNames.add("JMSXUserID");
/*  77 */     jmsXPropertyNames.add("JMSXAppID");
/*  78 */     jmsXPropertyNames.add("JMSXDeliveryCount");
/*  79 */     jmsXPropertyNames.add("JMSXGroupID");
/*  80 */     jmsXPropertyNames.add("JMSXGroupSeq");
/*  81 */     if (Trace.isOn) {
/*  82 */       Trace.exit("com.ibm.msg.client.wmq.factories.WMQMessageFactory", "static()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.factories/src/com/ibm/msg/client/wmq/factories/WMQMessageFactory.java";
/*     */ 
/*     */ 
/*     */   
/*     */   public ProviderBytesMessage createBytesMessage(ProviderSession session) throws JMSException {
/*  93 */     if (Trace.isOn) {
/*  94 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQMessageFactory", "createBytesMessage(ProviderSession)", new Object[] { session });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  99 */     if (session instanceof WMQSession) {
/* 100 */       WMQBytesMessage wMQBytesMessage1 = new WMQBytesMessage();
/* 101 */       if (Trace.isOn) {
/* 102 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQMessageFactory", "createBytesMessage(ProviderSession)", wMQBytesMessage1, 1);
/*     */       }
/*     */       
/* 105 */       return (ProviderBytesMessage)wMQBytesMessage1;
/*     */     } 
/*     */     
/* 108 */     if (session instanceof MQSession) {
/* 109 */       ProviderBytesMessage traceRet2 = ((MQSession)session).createBytesMessage();
/* 110 */       if (Trace.isOn) {
/* 111 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQMessageFactory", "createBytesMessage(ProviderSession)", traceRet2, 2);
/*     */       }
/*     */       
/* 114 */       return traceRet2;
/*     */     } 
/*     */     
/* 117 */     if (session == null) {
/* 118 */       TransientBytesMessage transientBytesMessage = new TransientBytesMessage();
/* 119 */       if (Trace.isOn) {
/* 120 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQMessageFactory", "createBytesMessage(ProviderSession)", transientBytesMessage, 3);
/*     */       }
/*     */       
/* 123 */       return (ProviderBytesMessage)transientBytesMessage;
/*     */     } 
/*     */ 
/*     */     
/* 127 */     WMQBytesMessage wMQBytesMessage = new WMQBytesMessage();
/* 128 */     if (Trace.isOn) {
/* 129 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQMessageFactory", "createBytesMessage(ProviderSession)", wMQBytesMessage, 4);
/*     */     }
/*     */     
/* 132 */     return (ProviderBytesMessage)wMQBytesMessage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProviderMapMessage createMapMessage(ProviderSession session) throws JMSException {
/* 139 */     if (Trace.isOn) {
/* 140 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQMessageFactory", "createMapMessage(ProviderSession)", new Object[] { session });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 145 */     if (session instanceof WMQSession) {
/* 146 */       WMQMapMessage wMQMapMessage1 = new WMQMapMessage();
/* 147 */       boolean mapNameStyle = ((WMQSession)session).getBooleanProperty("XMSC_WMQ_MAP_NAME_STYLE");
/* 148 */       wMQMapMessage1.setMapNameStyle(mapNameStyle);
/* 149 */       if (Trace.isOn) {
/* 150 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQMessageFactory", "createMapMessage(ProviderSession)", wMQMapMessage1, 1);
/*     */       }
/*     */       
/* 153 */       return (ProviderMapMessage)wMQMapMessage1;
/*     */     } 
/*     */     
/* 156 */     if (session instanceof MQSession) {
/* 157 */       ProviderMapMessage traceRet2 = ((MQSession)session).createMapMessage();
/* 158 */       if (Trace.isOn) {
/* 159 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQMessageFactory", "createMapMessage(ProviderSession)", traceRet2, 2);
/*     */       }
/*     */       
/* 162 */       return traceRet2;
/*     */     } 
/*     */     
/* 165 */     if (session == null) {
/* 166 */       TransientMapMessage transientMapMessage = new TransientMapMessage();
/* 167 */       if (Trace.isOn) {
/* 168 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQMessageFactory", "createMapMessage(ProviderSession)", transientMapMessage, 3);
/*     */       }
/*     */       
/* 171 */       return (ProviderMapMessage)transientMapMessage;
/*     */     } 
/*     */     
/* 174 */     WMQMapMessage wMQMapMessage = new WMQMapMessage();
/* 175 */     if (Trace.isOn) {
/* 176 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQMessageFactory", "createMapMessage(ProviderSession)", wMQMapMessage, 4);
/*     */     }
/*     */     
/* 179 */     return (ProviderMapMessage)wMQMapMessage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProviderMessage createMessage(ProviderSession session) throws JMSException {
/* 186 */     if (Trace.isOn) {
/* 187 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQMessageFactory", "createMessage(ProviderSession)", new Object[] { session });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 192 */     if (session instanceof WMQSession) {
/* 193 */       WMQNullMessage wMQNullMessage1 = new WMQNullMessage();
/* 194 */       if (Trace.isOn) {
/* 195 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQMessageFactory", "createMessage(ProviderSession)", wMQNullMessage1, 1);
/*     */       }
/*     */       
/* 198 */       return (ProviderMessage)wMQNullMessage1;
/*     */     } 
/*     */     
/* 201 */     if (session instanceof MQSession) {
/* 202 */       ProviderMessage traceRet2 = ((MQSession)session).createMessage();
/* 203 */       if (Trace.isOn) {
/* 204 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQMessageFactory", "createMessage(ProviderSession)", traceRet2, 2);
/*     */       }
/*     */       
/* 207 */       return traceRet2;
/*     */     } 
/*     */     
/* 210 */     if (session == null) {
/* 211 */       TransientMessage transientMessage = new TransientMessage();
/* 212 */       if (Trace.isOn) {
/* 213 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQMessageFactory", "createMessage(ProviderSession)", transientMessage, 3);
/*     */       }
/*     */       
/* 216 */       return (ProviderMessage)transientMessage;
/*     */     } 
/*     */ 
/*     */     
/* 220 */     WMQNullMessage wMQNullMessage = new WMQNullMessage();
/* 221 */     if (Trace.isOn) {
/* 222 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQMessageFactory", "createMessage(ProviderSession)", wMQNullMessage, 4);
/*     */     }
/*     */     
/* 225 */     return (ProviderMessage)wMQNullMessage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProviderObjectMessage createObjectMessage(ProviderSession session) throws JMSException {
/* 232 */     if (Trace.isOn) {
/* 233 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQMessageFactory", "createObjectMessage(ProviderSession)", new Object[] { session });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 238 */     if (session instanceof WMQSession) {
/* 239 */       WMQObjectMessage wMQObjectMessage1 = new WMQObjectMessage();
/* 240 */       if (Trace.isOn) {
/* 241 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQMessageFactory", "createObjectMessage(ProviderSession)", wMQObjectMessage1, 1);
/*     */       }
/*     */       
/* 244 */       return (ProviderObjectMessage)wMQObjectMessage1;
/*     */     } 
/*     */     
/* 247 */     if (session instanceof MQSession) {
/* 248 */       ProviderObjectMessage traceRet2 = ((MQSession)session).createObjectMessage();
/* 249 */       if (Trace.isOn) {
/* 250 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQMessageFactory", "createObjectMessage(ProviderSession)", traceRet2, 2);
/*     */       }
/*     */       
/* 253 */       return traceRet2;
/*     */     } 
/*     */     
/* 256 */     if (session == null) {
/* 257 */       TransientObjectMessage transientObjectMessage = new TransientObjectMessage();
/* 258 */       if (Trace.isOn) {
/* 259 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQMessageFactory", "createObjectMessage(ProviderSession)", transientObjectMessage, 3);
/*     */       }
/*     */       
/* 262 */       return (ProviderObjectMessage)transientObjectMessage;
/*     */     } 
/*     */     
/* 265 */     WMQObjectMessage wMQObjectMessage = new WMQObjectMessage();
/* 266 */     if (Trace.isOn) {
/* 267 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQMessageFactory", "createObjectMessage(ProviderSession)", wMQObjectMessage, 4);
/*     */     }
/*     */     
/* 270 */     return (ProviderObjectMessage)wMQObjectMessage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProviderStreamMessage createStreamMessage(ProviderSession session) throws JMSException {
/* 277 */     if (Trace.isOn) {
/* 278 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQMessageFactory", "createStreamMessage(ProviderSession)", new Object[] { session });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 283 */     if (session instanceof WMQSession) {
/* 284 */       WMQStreamMessage wMQStreamMessage1 = new WMQStreamMessage();
/* 285 */       if (Trace.isOn) {
/* 286 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQMessageFactory", "createStreamMessage(ProviderSession)", wMQStreamMessage1, 1);
/*     */       }
/*     */       
/* 289 */       return (ProviderStreamMessage)wMQStreamMessage1;
/*     */     } 
/*     */     
/* 292 */     if (session instanceof MQSession) {
/* 293 */       ProviderStreamMessage traceRet2 = ((MQSession)session).createStreamMessage();
/* 294 */       if (Trace.isOn) {
/* 295 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQMessageFactory", "createStreamMessage(ProviderSession)", traceRet2, 2);
/*     */       }
/*     */       
/* 298 */       return traceRet2;
/*     */     } 
/*     */     
/* 301 */     if (session == null) {
/* 302 */       TransientStreamMessage transientStreamMessage = new TransientStreamMessage();
/* 303 */       if (Trace.isOn) {
/* 304 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQMessageFactory", "createStreamMessage(ProviderSession)", transientStreamMessage, 3);
/*     */       }
/*     */       
/* 307 */       return (ProviderStreamMessage)transientStreamMessage;
/*     */     } 
/*     */     
/* 310 */     WMQStreamMessage wMQStreamMessage = new WMQStreamMessage();
/* 311 */     if (Trace.isOn) {
/* 312 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQMessageFactory", "createStreamMessage(ProviderSession)", wMQStreamMessage, 4);
/*     */     }
/*     */     
/* 315 */     return (ProviderStreamMessage)wMQStreamMessage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProviderTextMessage createTextMessage(ProviderSession session) throws JMSException {
/* 322 */     if (Trace.isOn) {
/* 323 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQMessageFactory", "createTextMessage(ProviderSession)", new Object[] { session });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 328 */     if (session instanceof WMQSession) {
/* 329 */       WMQTextMessage wMQTextMessage1 = new WMQTextMessage();
/* 330 */       if (Trace.isOn) {
/* 331 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQMessageFactory", "createTextMessage(ProviderSession)", wMQTextMessage1, 1);
/*     */       }
/*     */       
/* 334 */       return (ProviderTextMessage)wMQTextMessage1;
/*     */     } 
/*     */     
/* 337 */     if (session instanceof MQSession) {
/* 338 */       ProviderTextMessage traceRet2 = ((MQSession)session).createTextMessage();
/* 339 */       if (Trace.isOn) {
/* 340 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQMessageFactory", "createTextMessage(ProviderSession)", traceRet2, 2);
/*     */       }
/*     */       
/* 343 */       return traceRet2;
/*     */     } 
/*     */     
/* 346 */     if (session == null) {
/* 347 */       TransientTextMessage transientTextMessage = new TransientTextMessage();
/* 348 */       if (Trace.isOn) {
/* 349 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQMessageFactory", "createTextMessage(ProviderSession)", transientTextMessage, 3);
/*     */       }
/*     */       
/* 352 */       return (ProviderTextMessage)transientTextMessage;
/*     */     } 
/*     */     
/* 355 */     WMQTextMessage wMQTextMessage = new WMQTextMessage();
/* 356 */     if (Trace.isOn) {
/* 357 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQMessageFactory", "createTextMessage(ProviderSession)", wMQTextMessage, 4);
/*     */     }
/*     */     
/* 360 */     return (ProviderTextMessage)wMQTextMessage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProviderMessage convertMessageUsingSession(ProviderMessage message, ProviderSession session) throws JMSException {
/* 368 */     if (Trace.isOn) {
/* 369 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.WMQMessageFactory", "convertMessageUsingSession(ProviderMessage,ProviderSession)", new Object[] { message, session });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 374 */     if (!(message instanceof TransientMessage)) {
/*     */       
/* 376 */       if (Trace.isOn) {
/* 377 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQMessageFactory", "convertMessageUsingSession(ProviderMessage,ProviderSession)", message, 1);
/*     */       }
/*     */       
/* 380 */       return message;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 386 */     if (session instanceof WMQSession) {
/* 387 */       ProviderMessage traceRet1 = ((TransientMessage)message).convertIntoWMQMessage(session);
/* 388 */       if (Trace.isOn) {
/* 389 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQMessageFactory", "convertMessageUsingSession(ProviderMessage,ProviderSession)", traceRet1, 2);
/*     */       }
/*     */       
/* 392 */       return traceRet1;
/*     */     } 
/*     */     
/* 395 */     if (session instanceof MQSession) {
/* 396 */       ProviderMessage traceRet2 = ((TransientMessage)message).convertIntoMQMessage(session);
/* 397 */       if (Trace.isOn) {
/* 398 */         Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQMessageFactory", "convertMessageUsingSession(ProviderMessage,ProviderSession)", traceRet2, 3);
/*     */       }
/*     */       
/* 401 */       return traceRet2;
/*     */     } 
/*     */ 
/*     */     
/* 405 */     ProviderMessage traceRet4 = ((TransientMessage)message).convertIntoWMQMessage(session);
/* 406 */     if (Trace.isOn) {
/* 407 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.WMQMessageFactory", "convertMessageUsingSession(ProviderMessage,ProviderSession)", traceRet4, 4);
/*     */     }
/*     */     
/* 410 */     return traceRet4;
/*     */   }
/*     */ 
/*     */   
/*     */   public Enumeration<String> getJMSXPropertyNames() throws JMSException {
/* 415 */     Enumeration<String> traceRet1 = jmsXPropertyNames.elements();
/* 416 */     if (Trace.isOn) {
/* 417 */       Trace.data(this, "com.ibm.msg.client.wmq.factories.WMQMessageFactory", "getJMSXPropertyNames()", "getter", traceRet1);
/*     */     }
/*     */     
/* 420 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\factories\WMQMessageFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */