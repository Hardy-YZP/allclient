/*     */ package com.ibm.disthub2.impl.client;
/*     */ 
/*     */ import com.ibm.disthub2.impl.formats.MessageHandle;
/*     */ import com.ibm.disthub2.impl.formats.SchemaRegistry;
/*     */ import com.ibm.disthub2.spi.ClientExceptionConstants;
/*     */ import com.ibm.disthub2.spi.ClientLogConstants;
/*     */ import com.ibm.disthub2.spi.ExceptionBuilder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ConnectionMonitor
/*     */   implements Runnable, ClientExceptionConstants, ClientLogConstants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  88 */   private static final DebugObject debug = new DebugObject("ConnectionMonitor");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   ConnectorImpl conn;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean die;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int PING_PRIORITY = 40;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConnectionMonitor(ConnectorImpl conn) {
/* 110 */     this.die = false;
/*     */     this.conn = conn;
/*     */     this.conn.lastMRTime = conn.pingClock;
/*     */   }
/*     */   
/*     */   public void run() {
/* 116 */     if (debug.debugIt(32)) {
/* 117 */       debug.debug(-165922073994779L, "run");
/*     */     }
/* 119 */     MessageHandle ping = SchemaRegistry.getMessageHandle();
/* 120 */     ping.setByteArray(0, new byte[0]);
/* 121 */     ping.setBoolean(1, false);
/* 122 */     ping.setChoice(145, 0);
/* 123 */     ping.setByte(2, (byte)40);
/* 124 */     ping.setBoolean(3, false);
/* 125 */     ping.setString(4, "");
/* 126 */     ping.setChoice(146, 0);
/* 127 */     ping.setChoice(147, 1);
/* 128 */     ping.setChoice(148, 0);
/* 129 */     ping.setChoice(149, 2);
/*     */     
/* 131 */     String overridePing = System.getProperty("CLIENT_PING_INTERVAL");
/* 132 */     if (overridePing != null) {
/* 133 */       this.conn.baseConfig.CLIENT_PING_INTERVAL = Integer.parseInt(overridePing);
/*     */     }
/*     */     
/* 136 */     long x = 0L, don = 0L, now = 0L, next = 0L;
/*     */ 
/*     */ 
/*     */     
/*     */     while (true) {
/* 141 */       int pingInterval = this.conn.baseConfig.CLIENT_PING_INTERVAL;
/* 142 */       int minsleep = this.conn.baseConfig.PING_MIN;
/* 143 */       int pingtom = this.conn.baseConfig.PING_TIMEOUT_MULTIPLE;
/*     */       
/* 145 */       if (pingInterval <= 0) {
/*     */         break;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 159 */         now = System.currentTimeMillis();
/* 160 */         next = now + pingInterval;
/* 161 */         if (this.conn.isActive()) {
/* 162 */           this.conn.pingClock = now;
/* 163 */           if ((x = now - this.conn.lastMRTime) > pingInterval)
/* 164 */             if (++this.conn.pingCnt <= pingtom) {
/*     */ 
/*     */ 
/*     */               
/* 168 */               if (debug.debugIt(16))
/* 169 */                 debug.debug(-153415734321212L, "pinging " + this.conn.hostandportString()); 
/* 170 */               this.conn.send(ping);
/*     */ 
/*     */ 
/*     */             
/*     */             }
/*     */             else {
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 180 */               if (debug.debugIt(16)) {
/* 181 */                 debug.debug(-153415734321212L, "reaping " + this.conn.hostandportString());
/*     */               }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 189 */               this.conn.setException(new MinimalIOException(-30, 
/* 190 */                     ExceptionBuilder.buildReasonString(-1224227548, null)));
/*     */               break;
/*     */             }  
/* 193 */           don = System.currentTimeMillis();
/*     */           
/* 195 */           x = next - don;
/*     */           
/* 197 */           this.conn.pingClock = don;
/*     */           
/* 199 */           if (x >= minsleep) {
/* 200 */             xwait(x);
/*     */           }
/*     */ 
/*     */           
/*     */           continue;
/*     */         } 
/*     */ 
/*     */         
/*     */         break;
/* 209 */       } catch (KillMon e) {
/* 210 */         if (debug.debugIt(16)) {
/* 211 */           debug.debug(-153415734321212L, "decommissioned");
/*     */         }
/* 213 */         if (debug.debugIt(64)) {
/* 214 */           debug.debug(-142394261359015L, "run");
/*     */         }
/*     */         return;
/* 217 */       } catch (Throwable e) {
/* 218 */         if (debug.debugIt(16)) {
/* 219 */           debug.debug(-153415734321212L, "caught: " + e);
/*     */         }
/*     */       } 
/*     */     } 
/* 223 */     if (debug.debugIt(64)) {
/* 224 */       debug.debug(-142394261359015L, "run");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private final synchronized void xwait(long millis) throws KillMon {
/* 231 */     if (debug.debugIt(32)) {
/* 232 */       debug.debug(-165922073994779L, "xwait", Long.valueOf(millis));
/*     */     }
/* 234 */     if (!this.die)
/* 235 */       try { wait(millis); } catch (Exception exception) {} 
/* 236 */     if (this.die) {
/* 237 */       throw new KillMon(ExceptionBuilder.buildReasonString(-1676719413, null));
/*     */     }
/* 239 */     if (debug.debugIt(64))
/* 240 */       debug.debug(-142394261359015L, "xwait"); 
/*     */   } public synchronized void stop() { if (debug.debugIt(32))
/*     */       debug.debug(-165922073994779L, "stop"); 
/*     */     this.die = true;
/*     */     notify();
/*     */     if (debug.debugIt(64))
/* 246 */       debug.debug(-142394261359015L, "stop");  } private class KillMon extends Exception { protected KillMon(String reason) { super(reason); }
/*     */      }
/*     */ 
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\client\ConnectionMonitor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */