/*    */ package com.ibm.msg.client.commonservices.trace;
/*    */ 
/*    */ import com.ibm.msg.client.commonservices.commandmanager.Command;
/*    */ import com.ibm.msg.client.commonservices.provider.commandmanager.CommandHandler;
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
/*    */ public class TraceCommandHandler
/*    */   implements CommandHandler, TraceCommandConstants
/*    */ {
/*    */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/trace/TraceCommandHandler.java";
/*    */   
/*    */   public Command handleCommand(Command command) throws Exception {
/* 41 */     String type = (String)command.parameters.get("cmd");
/*    */     
/* 43 */     if (type.equals("trc")) {
/* 44 */       String traceState = (String)command.parameters.get("trc_st");
/* 45 */       if (traceState.equalsIgnoreCase("on")) {
/* 46 */         Trace.setOn(true);
/* 47 */       } else if (traceState.equalsIgnoreCase("off")) {
/* 48 */         Trace.setOn(false);
/*    */       } else {
/*    */         
/* 51 */         command.setFailed(null);
/* 52 */         return command;
/*    */       } 
/*    */       
/* 55 */       command.results.put("trc_rslt", traceState);
/* 56 */       command.setState(3);
/*    */     } 
/* 58 */     return command;
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\trace\TraceCommandHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */