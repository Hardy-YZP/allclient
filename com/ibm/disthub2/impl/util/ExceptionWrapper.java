/*    */ package com.ibm.disthub2.impl.util;
/*    */ 
/*    */ import java.io.PrintWriter;
/*    */ import java.io.StringWriter;
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
/*    */ public class ExceptionWrapper
/*    */ {
/*    */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*    */   public Throwable x;
/*    */   Object sup;
/*    */   
/*    */   public ExceptionWrapper(Throwable x) {
/* 52 */     this(x, null); } public ExceptionWrapper(Throwable x, Object sup) {
/* 53 */     this.x = x; this.sup = sup;
/*    */   } public String toString() {
/* 55 */     StringWriter sw = new StringWriter();
/* 56 */     PrintWriter pw = new PrintWriter(sw);
/* 57 */     if (this.x != null) {
/* 58 */       this.x.printStackTrace(pw);
/*    */     } else {
/* 60 */       pw.print("null");
/* 61 */     }  if (this.sup != null) {
/* 62 */       pw.println(); pw.print(this.sup);
/*    */     } 
/* 64 */     return sw.toString();
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\imp\\util\ExceptionWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */