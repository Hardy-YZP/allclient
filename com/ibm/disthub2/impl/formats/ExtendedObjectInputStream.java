/*    */ package com.ibm.disthub2.impl.formats;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.ObjectInputStream;
/*    */ import java.io.ObjectStreamClass;
/*    */ import java.io.StreamCorruptedException;
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
/*    */ public class ExtendedObjectInputStream
/*    */   extends ObjectInputStream
/*    */ {
/*    */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/* 50 */   ClassLoader cl = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ExtendedObjectInputStream(ClassLoader cl) throws IOException, SecurityException {
/* 58 */     this.cl = cl;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ExtendedObjectInputStream(InputStream in, ClassLoader cl) throws IOException, StreamCorruptedException {
/* 64 */     super(in);
/* 65 */     this.cl = cl;
/*    */   }
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
/*    */   protected Class resolveClass(ObjectStreamClass v) throws IOException, ClassNotFoundException {
/*    */     try {
/* 79 */       return this.cl.loadClass(v.getName());
/*    */     }
/* 81 */     catch (ClassNotFoundException e) {
/* 82 */       return Class.forName(v.getName());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\ExtendedObjectInputStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */