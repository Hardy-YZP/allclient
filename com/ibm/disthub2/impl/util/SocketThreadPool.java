/*    */ package com.ibm.disthub2.impl.util;
/*    */ 
/*    */ import com.ibm.disthub2.impl.client.DebugObject;
/*    */ import java.net.Socket;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class SocketThreadPool
/*    */ {
/*    */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/* 51 */   private static final DebugObject debug = new DebugObject("SocketThreadPool");
/*    */   
/*    */   public abstract SocketThreadPoolClientHndl registerClient(Socket paramSocket, SocketThreadPoolClient paramSocketThreadPoolClient) throws SocketThreadPoolException;
/*    */   
/*    */   public abstract void setMaxThreads(int paramInt1, int paramInt2);
/*    */   
/*    */   public abstract int[] getActiveThreads();
/*    */   
/*    */   public abstract void setPollingInterval(int paramInt);
/*    */   
/*    */   public void start() {}
/*    */   
/*    */   public void suspend() {}
/*    */   
/*    */   public void resume() {}
/*    */   
/*    */   public void stop() {}
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\imp\\util\SocketThreadPool.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */