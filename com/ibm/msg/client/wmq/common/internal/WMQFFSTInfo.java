/*     */ package com.ibm.msg.client.wmq.common.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.DumpableObject;
/*     */ import com.ibm.msg.client.commonservices.trace.TableBuilder;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.commonservices.trace.TraceFFSTInfo;
/*     */ import com.ibm.msg.client.commonservices.util.ConcurrentWeakHashSet;
/*     */ import com.ibm.msg.client.jms.internal.JmsContextImpl;
/*     */ import com.ibm.msg.client.provider.ProviderConnection;
/*     */ import com.ibm.msg.client.provider.ProviderMessageConsumer;
/*     */ import com.ibm.msg.client.provider.ProviderMessageProducer;
/*     */ import com.ibm.msg.client.provider.ProviderSession;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WMQFFSTInfo
/*     */   implements TraceFFSTInfo
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/WMQFFSTInfo.java";
/*     */   
/*     */   static {
/*  52 */     if (Trace.isOn) {
/*  53 */       Trace.data("com.ibm.msg.client.wmq.common.internal.WMQFFSTInfo", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/WMQFFSTInfo.java");
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
/*  64 */   private static ConcurrentWeakHashSet<JmsContextImpl> contexts = new ConcurrentWeakHashSet();
/*  65 */   private static ConcurrentWeakHashSet<ProviderConnection> connections = new ConcurrentWeakHashSet();
/*  66 */   private static ConcurrentWeakHashSet<ProviderSession> sessions = new ConcurrentWeakHashSet();
/*  67 */   private static ConcurrentWeakHashSet<ProviderMessageConsumer> consumers = new ConcurrentWeakHashSet();
/*  68 */   private static ConcurrentWeakHashSet<ProviderMessageProducer> producers = new ConcurrentWeakHashSet();
/*  69 */   private static ConcurrentHashMap<Class<? extends DumpableObject>, ConcurrentWeakHashSet<DumpableObject>> allDumpableClasses = new ConcurrentHashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String produceInformation(Object sourceObject) {
/*  76 */     if (Trace.isOn) {
/*  77 */       Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQFFSTInfo", "produceInformation(Object)", new Object[] { sourceObject });
/*     */     }
/*     */ 
/*     */     
/*  81 */     StringBuilder output = new StringBuilder();
/*  82 */     output.append("Overview of JMS System").append(Trace.lineSeparator);
/*  83 */     output.append("Num. Contexts    : ").append(contexts.size()).append(Trace.lineSeparator);
/*  84 */     output.append("Num. Connections : ").append(connections.size()).append(Trace.lineSeparator);
/*  85 */     output.append("Num. Sessions    : ").append(sessions.size()).append(Trace.lineSeparator);
/*  86 */     output.append("Num. Consumers   : ").append(consumers.size()).append(Trace.lineSeparator);
/*  87 */     output.append("Num. Producers   : ").append(producers.size()).append(Trace.lineSeparator);
/*     */     
/*  89 */     output.append(Trace.lineSeparator).append("Detailed JMS System Information").append(Trace.lineSeparator);
/*     */ 
/*     */     
/*  92 */     output.append("Contexts      : ").append(Trace.lineSeparator);
/*  93 */     Iterator<JmsContextImpl> contIt = contexts.iterator();
/*  94 */     while (contIt.hasNext()) {
/*     */       
/*  96 */       JmsContextImpl context = contIt.next();
/*  97 */       if (context != null) {
/*  98 */         output.append(context.toString()).append(Trace.lineSeparator);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 103 */     output.append("Connections      : ").append(Trace.lineSeparator);
/* 104 */     Iterator<ProviderConnection> connIt = connections.iterator();
/* 105 */     while (connIt.hasNext()) {
/*     */       
/* 107 */       ProviderConnection conn = connIt.next();
/* 108 */       if (conn != null) {
/* 109 */         output.append(conn.toString()).append(Trace.lineSeparator);
/* 110 */         output.append(formatProps((Map<String, Object>)conn)).append(Trace.lineSeparator).append(Trace.lineSeparator);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 115 */     output.append("Sessions         : ").append(Trace.lineSeparator);
/* 116 */     Iterator<ProviderSession> sessIt = sessions.iterator();
/* 117 */     while (sessIt.hasNext()) {
/*     */       
/* 119 */       ProviderSession sess = sessIt.next();
/* 120 */       if (sess != null) {
/* 121 */         output.append(sess.toString()).append(Trace.lineSeparator);
/* 122 */         output.append(formatProps((Map<String, Object>)sess)).append(Trace.lineSeparator).append(Trace.lineSeparator);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 127 */     output.append("Consumers        : ").append(Trace.lineSeparator);
/* 128 */     Iterator<ProviderMessageConsumer> consumersIt = consumers.iterator();
/* 129 */     while (consumersIt.hasNext()) {
/*     */       
/* 131 */       ProviderMessageConsumer con = consumersIt.next();
/* 132 */       if (con != null) {
/* 133 */         output.append(con.toString()).append(Trace.lineSeparator);
/* 134 */         output.append(formatProps((Map<String, Object>)con)).append(Trace.lineSeparator).append(Trace.lineSeparator);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 139 */     output.append("Producers        : ").append(Trace.lineSeparator);
/* 140 */     Iterator<ProviderMessageProducer> producersIt = producers.iterator();
/* 141 */     while (producersIt.hasNext()) {
/*     */       
/* 143 */       ProviderMessageProducer prod = producersIt.next();
/* 144 */       if (prod != null) {
/* 145 */         output.append(prod.toString()).append(Trace.lineSeparator);
/* 146 */         output.append(formatProps((Map<String, Object>)prod)).append(Trace.lineSeparator).append(Trace.lineSeparator);
/*     */       } 
/*     */     } 
/*     */     
/* 150 */     String traceRet1 = output.toString();
/*     */     
/* 152 */     if (Trace.isOn) {
/* 153 */       Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQFFSTInfo", "produceInformation(Object)", traceRet1);
/*     */     }
/*     */     
/* 156 */     return traceRet1;
/*     */   }
/*     */   
/*     */   private static String formatProps(Map<String, Object> props) {
/* 160 */     if (Trace.isOn) {
/* 161 */       Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQFFSTInfo", "formatProps(Map<String , Object>)", new Object[] { props });
/*     */     }
/*     */ 
/*     */     
/* 165 */     String traceRet1 = formatProps(props, 3, '|');
/*     */     
/* 167 */     if (Trace.isOn) {
/* 168 */       Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQFFSTInfo", "formatProps(Map<String , Object>)", traceRet1);
/*     */     }
/*     */     
/* 171 */     return traceRet1;
/*     */   }
/*     */   
/*     */   private static String formatProps(Map<String, Object> props, int indent, char prefix) {
/* 175 */     if (Trace.isOn) {
/* 176 */       Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQFFSTInfo", "formatProps(Map<String , Object>,int,char)", new Object[] { props, 
/* 177 */             Integer.valueOf(indent), 
/* 178 */             Character.valueOf(prefix) });
/*     */     }
/* 180 */     TableBuilder builder = new TableBuilder(indent, true, prefix);
/*     */     
/*     */     try {
/* 183 */       for (Map.Entry<String, Object> entry : props.entrySet()) {
/* 184 */         String key = entry.getKey();
/* 185 */         Object value = entry.getValue();
/*     */         
/* 187 */         if (key.equals("XMSC_PASSWORD") && value != null) {
/* 188 */           builder.append(key, "********");
/*     */           continue;
/*     */         } 
/* 191 */         builder.append(key, (value == null) ? "<null>" : entry.getValue().toString());
/*     */       }
/*     */     
/*     */     }
/* 195 */     catch (ConcurrentModificationException|NullPointerException e) {
/* 196 */       if (Trace.isOn) {
/* 197 */         Trace.catchBlock("com.ibm.msg.client.wmq.common.internal.WMQFFSTInfo", "dump(PrintWriter,int, char)", e, 1);
/*     */       }
/*     */ 
/*     */       
/* 201 */       builder.append("ERROR", String.format(" %s %s encountered during processing - ignored%n", new Object[] { Character.valueOf(prefix), e.toString() }));
/*     */     } 
/*     */     
/* 204 */     String traceRet1 = builder.toStringBuilder().toString();
/*     */     
/* 206 */     if (Trace.isOn) {
/* 207 */       Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQFFSTInfo", "formatProps(Map<String , Object>,int,char)", traceRet1);
/*     */     }
/*     */     
/* 210 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String providerInformation(Object sourceObject) {
/* 218 */     if (Trace.isOn) {
/* 219 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQFFSTInfo", "providerInformation(Object)", new Object[] { sourceObject });
/*     */     }
/*     */     
/* 222 */     String traceRet1 = produceInformation(sourceObject);
/* 223 */     if (Trace.isOn) {
/* 224 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQFFSTInfo", "providerInformation(Object)", traceRet1);
/*     */     }
/*     */     
/* 227 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dump(PrintWriter pw, int levelP) {
/* 235 */     if (Trace.isOn) {
/* 236 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQFFSTInfo", "dump(PrintWriter,int)", new Object[] { pw, 
/* 237 */             Integer.valueOf(levelP) });
/*     */     }
/* 239 */     StringBuilder prefix = new StringBuilder(Trace.buildPrefix(levelP));
/* 240 */     pw.format("%s[JMS Summary]%n", new Object[] { prefix });
/*     */     
/* 242 */     int level = levelP + 1;
/* 243 */     prefix.append("  ");
/*     */     
/* 245 */     int level2 = level + 1;
/* 246 */     int tableIndent = (level2 + 1) * 2;
/*     */ 
/*     */     
/* 249 */     pw.format("%sContexts         : %n", new Object[] { prefix });
/*     */     try {
/* 251 */       for (JmsContextImpl context : contexts) {
/* 252 */         if (context != null) {
/* 253 */           context.dump(pw, level2);
/* 254 */           pw.println();
/*     */         }
/*     */       
/*     */       } 
/* 258 */     } catch (ConcurrentModificationException|NullPointerException e) {
/* 259 */       if (Trace.isOn) {
/* 260 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.common.internal.WMQFFSTInfo", "dump(PrintWriter,int)", e, 1);
/*     */       }
/*     */ 
/*     */       
/* 264 */       pw.format(" %s %s encountered during processing - ignored%n", new Object[] { prefix, e.toString() });
/*     */     } 
/*     */ 
/*     */     
/* 268 */     pw.format("%sConnections         : %n", new Object[] { prefix });
/*     */     try {
/* 270 */       for (ProviderConnection conn : connections) {
/* 271 */         if (conn != null) {
/* 272 */           conn.dump(pw, level2);
/* 273 */           pw.println(formatProps((Map<String, Object>)conn, tableIndent, false));
/* 274 */           pw.println();
/*     */         }
/*     */       
/*     */       } 
/* 278 */     } catch (ConcurrentModificationException|NullPointerException e) {
/* 279 */       if (Trace.isOn) {
/* 280 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.common.internal.WMQFFSTInfo", "dump(PrintWriter,int)", e, 2);
/*     */       }
/*     */ 
/*     */       
/* 284 */       pw.format(" %s %s encountered during processing - ignored%n", new Object[] { prefix, e.toString() });
/*     */     } 
/*     */ 
/*     */     
/* 288 */     pw.format("%sSessions         : %n", new Object[] { prefix });
/*     */     try {
/* 290 */       for (ProviderSession sess : sessions) {
/* 291 */         if (sess != null) {
/* 292 */           sess.dump(pw, level2);
/* 293 */           pw.println(formatProps((Map<String, Object>)sess, tableIndent, false));
/* 294 */           pw.println();
/*     */         }
/*     */       
/*     */       } 
/* 298 */     } catch (ConcurrentModificationException|NullPointerException e) {
/* 299 */       if (Trace.isOn) {
/* 300 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.common.internal.WMQFFSTInfo", "dump(PrintWriter,int)", e, 3);
/*     */       }
/*     */ 
/*     */       
/* 304 */       pw.format(" %s %s encountered during processing - ignored%n", new Object[] { prefix, e.toString() });
/*     */     } 
/*     */ 
/*     */     
/* 308 */     pw.format("%sConsumers         : %n", new Object[] { prefix });
/*     */     try {
/* 310 */       for (ProviderMessageConsumer con : consumers) {
/* 311 */         if (con != null) {
/* 312 */           con.dump(pw, level2);
/* 313 */           pw.println(formatProps((Map<String, Object>)con, tableIndent, false));
/* 314 */           pw.println();
/*     */         }
/*     */       
/*     */       } 
/* 318 */     } catch (ConcurrentModificationException|NullPointerException e) {
/* 319 */       if (Trace.isOn) {
/* 320 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.common.internal.WMQFFSTInfo", "dump(PrintWriter,int)", e, 4);
/*     */       }
/*     */ 
/*     */       
/* 324 */       pw.format(" %s %s encountered during processing - ignored%n", new Object[] { prefix, e.toString() });
/*     */     } 
/*     */ 
/*     */     
/* 328 */     pw.format("%sProducers         : %n", new Object[] { prefix });
/*     */     try {
/* 330 */       for (ProviderMessageProducer prod : producers) {
/* 331 */         if (prod != null) {
/* 332 */           prod.dump(pw, level2);
/* 333 */           pw.println(formatProps((Map<String, Object>)prod, tableIndent, false));
/* 334 */           pw.println();
/*     */         }
/*     */       
/*     */       } 
/* 338 */     } catch (ConcurrentModificationException|NullPointerException e) {
/* 339 */       if (Trace.isOn) {
/* 340 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.common.internal.WMQFFSTInfo", "dump(PrintWriter,int)", e, 5);
/*     */       }
/*     */ 
/*     */       
/* 344 */       pw.format(" %s %s encountered during processing - ignored%n", new Object[] { prefix, e.toString() });
/*     */     } 
/*     */     
/* 347 */     pw.format("%sArbritary DumpableObjects :%n", new Object[] { prefix });
/* 348 */     for (Map.Entry<Class<? extends DumpableObject>, ConcurrentWeakHashSet<DumpableObject>> entry : allDumpableClasses.entrySet()) {
/*     */       
/*     */       try {
/* 351 */         pw.format("%s  %s:%n", new Object[] { prefix, ((Class)entry.getKey()).getSimpleName() });
/* 352 */         ConcurrentWeakHashSet<DumpableObject> dumpables = entry.getValue();
/* 353 */         for (DumpableObject dumpable : dumpables) {
/* 354 */           if (dumpable != null) {
/* 355 */             dumpable.dump(pw, level + 2);
/*     */           }
/*     */         }
/*     */       
/* 359 */       } catch (ConcurrentModificationException|NullPointerException e) {
/* 360 */         if (Trace.isOn) {
/* 361 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.common.internal.WMQFFSTInfo", "dump(PrintWriter,int)", e, 6);
/*     */         }
/*     */ 
/*     */         
/* 365 */         pw.format(" %s %s encountered during processing - ignored%n", new Object[] { prefix, e.toString() });
/*     */       } 
/* 367 */       pw.println();
/*     */     } 
/*     */     
/* 370 */     if (Trace.isOn) {
/* 371 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQFFSTInfo", "dump(PrintWriter,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerObject(Object object) {
/* 382 */     if (Trace.isOn) {
/* 383 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQFFSTInfo", "registerObject(Object)", new Object[] { object });
/*     */     }
/*     */     
/* 386 */     if (object instanceof JmsContextImpl) {
/* 387 */       contexts.add(object);
/*     */     }
/* 389 */     else if (object instanceof ProviderConnection) {
/* 390 */       connections.add(object);
/*     */     }
/* 392 */     else if (object instanceof ProviderSession) {
/* 393 */       sessions.add(object);
/*     */     }
/* 395 */     else if (object instanceof ProviderMessageProducer) {
/* 396 */       producers.add(object);
/*     */     }
/* 398 */     else if (object instanceof ProviderMessageConsumer) {
/* 399 */       consumers.add(object);
/*     */     } 
/* 401 */     if (Trace.isOn) {
/* 402 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQFFSTInfo", "registerObject(Object)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deRegisterObject(Object object) {
/* 413 */     if (Trace.isOn) {
/* 414 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQFFSTInfo", "deRegisterObject(Object)", new Object[] { object });
/*     */     }
/*     */     
/* 417 */     if (object instanceof JmsContextImpl) {
/* 418 */       contexts.remove(object);
/*     */     }
/* 420 */     else if (object instanceof ProviderConnection) {
/* 421 */       connections.remove(object);
/*     */     }
/* 423 */     else if (object instanceof ProviderSession) {
/* 424 */       sessions.remove(object);
/*     */     }
/* 426 */     else if (object instanceof ProviderMessageProducer) {
/* 427 */       producers.remove(object);
/*     */     }
/* 429 */     else if (object instanceof ProviderMessageConsumer) {
/* 430 */       consumers.remove(object);
/*     */     } 
/* 432 */     if (Trace.isOn) {
/* 433 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQFFSTInfo", "deRegisterObject(Object)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerDumpable(DumpableObject object) {
/* 444 */     if (Trace.isOn) {
/* 445 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQFFSTInfo", "registerDumpable(DumpableObject)", new Object[] { object });
/*     */     }
/*     */     
/* 448 */     ConcurrentWeakHashSet<DumpableObject> dumpables = allDumpableClasses.get(object.getClass());
/* 449 */     if (dumpables == null) {
/* 450 */       dumpables = new ConcurrentWeakHashSet();
/* 451 */       allDumpableClasses.put(object.getClass(), dumpables);
/*     */     } 
/* 453 */     dumpables.add(object);
/* 454 */     if (Trace.isOn) {
/* 455 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQFFSTInfo", "registerDumpable(DumpableObject)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deRegisterDumpable(DumpableObject object) {
/* 466 */     if (Trace.isOn) {
/* 467 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQFFSTInfo", "deRegisterDumpable(DumpableObject)", new Object[] { object });
/*     */     }
/*     */     
/* 470 */     ConcurrentWeakHashSet<DumpableObject> dumpables = allDumpableClasses.get(object.getClass());
/* 471 */     if (dumpables != null) {
/* 472 */       dumpables.remove(object);
/*     */     }
/* 474 */     if (Trace.isOn)
/* 475 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQFFSTInfo", "deRegisterDumpable(DumpableObject)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\common\internal\WMQFFSTInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */