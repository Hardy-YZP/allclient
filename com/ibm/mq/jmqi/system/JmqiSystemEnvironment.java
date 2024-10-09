/*     */ package com.ibm.mq.jmqi.system;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.JmqiPropertyHandler;
/*     */ import com.ibm.mq.jmqi.JmqiThreadPoolFactory;
/*     */ import com.ibm.mq.jmqi.MQCNO;
/*     */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*     */ import com.ibm.mq.jmqi.system.internal.CCDT;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.net.URL;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JmqiSystemEnvironment
/*     */   extends JmqiEnvironment
/*     */   implements JmqiComponent
/*     */ {
/*     */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/JmqiSystemEnvironment.java";
/*  48 */   private ThreadLocal<Object> componentData = new ThreadLocal();
/*     */ 
/*     */   
/*  51 */   private JmqiComponent[] components = new JmqiComponent[10];
/*     */   
/*  53 */   private int nextCompoment = 0;
/*     */   
/*  55 */   private Object componentSync = new Object();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private JmqiDC dc;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmqiSystemEnvironment(JmqiThreadPoolFactory threadPoolFactory, JmqiPropertyHandler propertyHandler) throws JmqiException {
/*  70 */     super(threadPoolFactory, propertyHandler);
/*  71 */     if (Trace.isOn) {
/*  72 */       Trace.entry(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "<init>(JmqiThreadPoolFactory,JmqiPropertyHandler)", new Object[] { threadPoolFactory, propertyHandler });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  77 */     registerComponent(this);
/*  78 */     this.dc = new JmqiDC(this);
/*     */     
/*  80 */     if (Trace.isOn) {
/*  81 */       Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "<init>(JmqiThreadPoolFactory,JmqiPropertyHandler)");
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
/*     */   public String getJmqiComponentName() {
/*  94 */     if (Trace.isOn) {
/*  95 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "getJmqiComponentName()", "getter", "com.ibm.mq.jmqi");
/*     */     }
/*     */     
/*  98 */     return "com.ibm.mq.jmqi";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmqiComponentTls newTlsObject() {
/* 108 */     if (Trace.isOn) {
/* 109 */       Trace.entry(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newTlsObject()");
/*     */     }
/* 111 */     JmqiComponentTls traceRet1 = new JmqiTls();
/*     */     
/* 113 */     if (Trace.isOn) {
/* 114 */       Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newTlsObject()", traceRet1);
/*     */     }
/*     */     
/* 117 */     return traceRet1;
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
/*     */   public JmqiTls getJmqiTls(JmqiComponentTls componentTls) {
/* 129 */     if (componentTls == null) {
/* 130 */       return (JmqiTls)getComponentTls(0);
/*     */     }
/*     */     
/* 133 */     JmqiTls tls = (JmqiTls)componentTls.allComponentsTls[0];
/*     */     
/* 135 */     if (tls == null) {
/* 136 */       tls = (JmqiTls)newTlsObject();
/* 137 */       componentTls.allComponentsTls[0] = tls;
/*     */     } 
/* 139 */     return tls;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int registerComponent(JmqiComponent newComponent) {
/* 149 */     if (Trace.isOn) {
/* 150 */       Trace.entry(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "registerComponent(JmqiComponent)", new Object[] { newComponent });
/*     */     }
/*     */ 
/*     */     
/* 154 */     int componentId = -1;
/* 155 */     synchronized (this.componentSync) {
/*     */       
/* 157 */       String newComponentName = newComponent.getJmqiComponentName();
/* 158 */       for (int index = 0; index < this.components.length; index++) {
/* 159 */         JmqiComponent component = this.components[index];
/* 160 */         if (component != null) {
/* 161 */           String componentName = component.getJmqiComponentName();
/* 162 */           if (componentName.equals(newComponentName)) {
/* 163 */             componentId = index;
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 169 */       if (componentId < 0) {
/* 170 */         componentId = this.nextCompoment++;
/* 171 */         if (componentId >= this.components.length) {
/*     */           
/* 173 */           int newLength = this.components.length * 2;
/* 174 */           JmqiComponent[] newComponents = new JmqiComponent[newLength];
/* 175 */           for (int i = 0; i < this.components.length; i++) {
/* 176 */             newComponents[i] = this.components[i];
/*     */           }
/* 178 */           this.components = newComponents;
/*     */         } 
/*     */         
/* 181 */         this.components[componentId] = newComponent;
/*     */       } 
/*     */     } 
/*     */     
/* 185 */     if (Trace.isOn) {
/* 186 */       Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "registerComponent(JmqiComponent)", 
/* 187 */           Integer.valueOf(componentId));
/*     */     }
/* 189 */     return componentId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmqiComponentTls getComponentTls(int componentId) {
/* 199 */     if (Trace.isOn)
/* 200 */       Trace.entry(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "getComponentTls(int)", new Object[] {
/* 201 */             Integer.valueOf(componentId)
/*     */           }); 
/* 203 */     JmqiComponentTls[] tlsArray = (JmqiComponentTls[])this.componentData.get();
/*     */     
/* 205 */     if (tlsArray == null) {
/* 206 */       tlsArray = new JmqiComponentTls[this.components.length];
/* 207 */       this.componentData.set(tlsArray);
/*     */ 
/*     */     
/*     */     }
/* 211 */     else if (componentId > tlsArray.length) {
/* 212 */       JmqiComponentTls[] newTlsArray = new JmqiComponentTls[this.components.length];
/* 213 */       for (int i = 0; i < tlsArray.length; i++) {
/* 214 */         newTlsArray[i] = tlsArray[i];
/* 215 */         if (tlsArray[i] != null) {
/* 216 */           (tlsArray[i]).allComponentsTls = newTlsArray;
/*     */         }
/*     */       } 
/* 219 */       tlsArray = newTlsArray;
/* 220 */       this.componentData.set(tlsArray);
/*     */     } 
/*     */     
/* 223 */     if (tlsArray[componentId] == null) {
/* 224 */       tlsArray[componentId] = this.components[componentId].newTlsObject();
/* 225 */       (tlsArray[componentId]).allComponentsTls = tlsArray;
/*     */     } 
/* 227 */     if (Trace.isOn) {
/* 228 */       Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "getComponentTls(int)", tlsArray[componentId]);
/*     */     }
/*     */     
/* 231 */     return tlsArray[componentId];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmqiException getLastException() {
/* 239 */     JmqiException traceRet1 = (getJmqiTls(null)).lastException;
/* 240 */     if (Trace.isOn) {
/* 241 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "getLastException()", "getter", traceRet1);
/*     */     }
/*     */     
/* 244 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLastException(JmqiException e) {
/* 252 */     if (Trace.isOn) {
/* 253 */       Trace.data(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "setLastException(JmqiException)", "setter", e);
/*     */     }
/*     */     
/* 256 */     (getJmqiTls(null)).lastException = e;
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
/*     */   public JmqiException getLastException(JmqiComponentTls tls) {
/* 268 */     if (Trace.isOn) {
/* 269 */       Trace.entry(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "getLastException(JmqiComponentTls)", new Object[] { tls });
/*     */     }
/*     */     
/* 272 */     JmqiException traceRet1 = (getJmqiTls(tls)).lastException;
/*     */     
/* 274 */     if (Trace.isOn) {
/* 275 */       Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "getLastException(JmqiComponentTls)", traceRet1);
/*     */     }
/*     */     
/* 278 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmqiConnectOptions newJmqiConnectOptions() {
/* 287 */     if (Trace.isOn) {
/* 288 */       Trace.entry(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newJmqiConnectOptions()");
/*     */     }
/*     */     
/* 291 */     JmqiConnectOptions traceRet1 = new JmqiConnectOptions(this);
/*     */     
/* 293 */     if (Trace.isOn) {
/* 294 */       Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newJmqiConnectOptions()", traceRet1);
/*     */     }
/*     */     
/* 297 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SpiActivate newSpiActivate() {
/* 307 */     if (Trace.isOn) {
/* 308 */       Trace.entry(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newSpiActivate()");
/*     */     }
/* 310 */     SpiActivate traceRet1 = new SpiActivate(this);
/*     */     
/* 312 */     if (Trace.isOn) {
/* 313 */       Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newSpiActivate()", traceRet1);
/*     */     }
/*     */     
/* 316 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LpiPrivConnStruct newSpiConnectOptions() {
/* 325 */     if (Trace.isOn) {
/* 326 */       Trace.entry(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newSpiConnectOptions()");
/*     */     }
/* 328 */     LpiPrivConnStruct traceRet1 = new LpiPrivConnStruct(this);
/*     */     
/* 330 */     if (Trace.isOn) {
/* 331 */       Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newSpiConnectOptions()", traceRet1);
/*     */     }
/*     */     
/* 334 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SpiSyncPointOptions newSpiSyncPointOptions() {
/* 343 */     if (Trace.isOn) {
/* 344 */       Trace.entry(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newSpiSyncPointOptions()");
/*     */     }
/*     */     
/* 347 */     SpiSyncPointOptions traceRet1 = new SpiSyncPointOptions(this);
/*     */     
/* 349 */     if (Trace.isOn) {
/* 350 */       Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newSpiSyncPointOptions()", traceRet1);
/*     */     }
/*     */     
/* 353 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SpiGetOptions newSpiGetOptions() {
/* 363 */     if (Trace.isOn) {
/* 364 */       Trace.entry(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newSpiGetOptions()");
/*     */     }
/* 366 */     SpiGetOptions traceRet1 = new SpiGetOptions(this);
/*     */     
/* 368 */     if (Trace.isOn) {
/* 369 */       Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newSpiGetOptions()", traceRet1);
/*     */     }
/*     */     
/* 372 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SpiPutOptions newSpiPutOptions() {
/* 382 */     if (Trace.isOn) {
/* 383 */       Trace.entry(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newSpiPutOptions()");
/*     */     }
/* 385 */     SpiPutOptions traceRet1 = new SpiPutOptions(this);
/*     */     
/* 387 */     if (Trace.isOn) {
/* 388 */       Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newSpiPutOptions()", traceRet1);
/*     */     }
/*     */     
/* 391 */     return traceRet1;
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
/*     */   public CCDT newCCDT(URL url) throws JmqiException {
/* 404 */     if (Trace.isOn) {
/* 405 */       Trace.entry(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newCCDT(URL)", new Object[] { url });
/*     */     }
/*     */     
/* 408 */     CCDT traceRet1 = new CCDT(this, url);
/*     */     
/* 410 */     if (Trace.isOn) {
/* 411 */       Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newCCDT(URL)", traceRet1);
/*     */     }
/* 413 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LpiSD newSpiSD() {
/* 422 */     if (Trace.isOn) {
/* 423 */       Trace.entry(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newSpiSD()");
/*     */     }
/* 425 */     LpiSD result = new LpiSD(this);
/*     */     
/* 427 */     if (Trace.isOn) {
/* 428 */       Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newSpiSD()", result);
/*     */     }
/* 430 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LpiUSD newLpiUSD() {
/* 439 */     if (Trace.isOn) {
/* 440 */       Trace.entry(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newLpiUSD()");
/*     */     }
/* 442 */     LpiUSD traceRet1 = new LpiUSD(this);
/*     */     
/* 444 */     if (Trace.isOn) {
/* 445 */       Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newLpiUSD()", traceRet1);
/*     */     }
/* 447 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmqiMetaData newJmqiMetaData() {
/* 456 */     if (Trace.isOn) {
/* 457 */       Trace.entry(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newJmqiMetaData()");
/*     */     }
/* 459 */     JmqiMetaData result = new JmqiMetaData(this);
/*     */     
/* 461 */     if (Trace.isOn) {
/* 462 */       Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newJmqiMetaData()", result);
/*     */     }
/*     */     
/* 465 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RXPB newRXPB() {
/* 474 */     if (Trace.isOn) {
/* 475 */       Trace.entry(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newRXPB()");
/*     */     }
/* 477 */     RXPB result = new RXPB(this);
/*     */     
/* 479 */     if (Trace.isOn) {
/* 480 */       Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newRXPB()", result);
/*     */     }
/* 482 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RXPBWithCNO newRXPBWithCNO(MQCNO cno) {
/* 493 */     if (Trace.isOn) {
/* 494 */       Trace.entry(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newRXPBWithCNO(MQCNO)", new Object[] { cno });
/*     */     }
/* 496 */     RXPBWithCNO result = new RXPBWithCNO(this, cno);
/*     */     
/* 498 */     if (Trace.isOn) {
/* 499 */       Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newRXPBWithCNO(MQCNO)", result);
/*     */     }
/* 501 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LpiSDSubProps newLpiSDSubProps() {
/* 510 */     if (Trace.isOn) {
/* 511 */       Trace.entry(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newLpiSDSubProps()");
/*     */     }
/* 513 */     LpiSDSubProps traceRet1 = new LpiSDSubProps(this);
/*     */     
/* 515 */     if (Trace.isOn) {
/* 516 */       Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newLpiSDSubProps()", traceRet1);
/*     */     }
/*     */     
/* 519 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LpiNotifyDetails newLpiNotifyDetails() {
/* 528 */     if (Trace.isOn) {
/* 529 */       Trace.entry(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newLpiNotifyDetails()");
/*     */     }
/* 531 */     LpiNotifyDetails traceRet1 = new LpiNotifyDetails(this);
/*     */     
/* 533 */     if (Trace.isOn) {
/* 534 */       Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newLpiNotifyDetails()", traceRet1);
/*     */     }
/*     */     
/* 537 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SpiOpenOptions newSpiOpenOptions() {
/* 546 */     if (Trace.isOn) {
/* 547 */       Trace.entry(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newSpiOpenOptions()");
/*     */     }
/* 549 */     SpiOpenOptions result = new SpiOpenOptions(this);
/*     */     
/* 551 */     if (Trace.isOn) {
/* 552 */       Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newSpiOpenOptions()", result);
/*     */     }
/*     */     
/* 555 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LexContext newLexContext() {
/* 564 */     if (Trace.isOn) {
/* 565 */       Trace.entry(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newLexContext()");
/*     */     }
/* 567 */     LexContext traceRet1 = new LexContext(this);
/*     */     
/* 569 */     if (Trace.isOn) {
/* 570 */       Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newLexContext()", traceRet1);
/*     */     }
/*     */     
/* 573 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LexCommandContext newLexCommandContext() {
/* 582 */     if (Trace.isOn) {
/* 583 */       Trace.entry(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newLexCommandContext()");
/*     */     }
/* 585 */     LexCommandContext traceRet1 = new LexCommandContext(this);
/*     */     
/* 587 */     if (Trace.isOn) {
/* 588 */       Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newLexCommandContext()", traceRet1);
/*     */     }
/*     */     
/* 591 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LexObjectSelector newLexObjectSelector() {
/* 600 */     if (Trace.isOn) {
/* 601 */       Trace.entry(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newLexObjectSelector()");
/*     */     }
/* 603 */     LexObjectSelector traceRet1 = new LexObjectSelector(this);
/*     */     
/* 605 */     if (Trace.isOn) {
/* 606 */       Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newLexObjectSelector()", traceRet1);
/*     */     }
/*     */     
/* 609 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LexFilterElement newLexFilterElement() {
/* 618 */     if (Trace.isOn) {
/* 619 */       Trace.entry(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newLexFilterElement()");
/*     */     }
/* 621 */     LexFilterElement traceRet1 = new LexFilterElement(this);
/*     */     
/* 623 */     if (Trace.isOn) {
/* 624 */       Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newLexFilterElement()", traceRet1);
/*     */     }
/*     */     
/* 627 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JmqiDC getDC() {
/* 633 */     return this.dc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LpiAdoptUserOptions newLpiAdoptUserOptions() {
/* 640 */     if (Trace.isOn) {
/* 641 */       Trace.entry(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newLpiAdoptUserOptions()");
/*     */     }
/*     */     
/* 644 */     LpiAdoptUserOptions result = new LpiAdoptUserOptions(this);
/*     */     
/* 646 */     if (Trace.isOn) {
/* 647 */       Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newLpiAdoptUserOptions()", result);
/*     */     }
/*     */     
/* 650 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LpiCHLAUTHQuery newLpiCHLAUTHQuery() {
/* 657 */     if (Trace.isOn) {
/* 658 */       Trace.entry(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newLpiCHLAUTHQuery()");
/*     */     }
/* 660 */     LpiCHLAUTHQuery result = new LpiCHLAUTHQuery(this);
/*     */     
/* 662 */     if (Trace.isOn) {
/* 663 */       Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newLpiCHLAUTHQuery()", result);
/*     */     }
/*     */     
/* 666 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LpiSRD newLpiSRD() {
/* 673 */     if (Trace.isOn) {
/* 674 */       Trace.entry(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newLpiSRD()");
/*     */     }
/* 676 */     LpiSRD result = new LpiSRD(this);
/*     */     
/* 678 */     if (Trace.isOn) {
/* 679 */       Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newLpiSRD()", result);
/*     */     }
/* 681 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LpiCALLOPT newLpiCALLOPT() {
/* 688 */     if (Trace.isOn) {
/* 689 */       Trace.entry(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newLpiCALLOPT()");
/*     */     }
/* 691 */     LpiCALLOPT result = new LpiCALLOPT(this);
/*     */     
/* 693 */     if (Trace.isOn) {
/* 694 */       Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiSystemEnvironment", "newLpiCALLOPT()", result);
/*     */     }
/* 696 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\JmqiSystemEnvironment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */