/*     */ package com.ibm.disthub2.impl.client;
/*     */ 
/*     */ import com.ibm.disthub2.client.Checkpoint;
/*     */ import com.ibm.disthub2.impl.util.StampPair;
/*     */ import com.ibm.disthub2.impl.util.VectorClock;
/*     */ import com.ibm.disthub2.spi.ExceptionBuilder;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.util.Enumeration;
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
/*     */ public class CheckpointImpl
/*     */   extends Checkpoint
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  64 */   String topic = null;
/*  65 */   String selector = null;
/*  66 */   VectorClock vc = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Checkpoint create(byte[] storedForm) throws IOException {
/*     */     try {
/*  77 */       ByteArrayInputStream bais = new ByteArrayInputStream(storedForm);
/*  78 */       DataInputStream dis = new DataInputStream(bais);
/*  79 */       String sanity = dis.readUTF();
/*     */       
/*  81 */       if (!sanity.equals("gCT")) {
/*  82 */         throw new IOException(ExceptionBuilder.buildReasonString(338437231, null));
/*     */       }
/*     */       
/*  85 */       String topic = dis.readUTF();
/*  86 */       String selector = dis.readUTF();
/*     */       
/*  88 */       VectorClock vc = new VectorClock();
/*  89 */       vc.read(dis);
/*     */       
/*  91 */       return new CheckpointImpl(topic, selector, vc);
/*     */     }
/*  93 */     catch (Exception e) {
/*  94 */       throw new IOException(ExceptionBuilder.buildReasonString(338437231, null));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected CheckpointImpl(String topic, String selector, VectorClock vc) {
/* 100 */     this.topic = topic;
/* 101 */     this.selector = selector;
/* 102 */     this.vc = vc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] toByteArray() {
/*     */     try {
/* 111 */       ByteArrayOutputStream bos = new ByteArrayOutputStream();
/* 112 */       DataOutputStream dos = new DataOutputStream(bos);
/*     */       
/* 114 */       dos.writeUTF("gCT");
/* 115 */       dos.writeUTF(this.topic);
/* 116 */       if (this.selector == null) {
/* 117 */         dos.writeUTF("");
/*     */       } else {
/* 119 */         dos.writeUTF(this.selector);
/*     */       } 
/* 121 */       this.vc.write(dos);
/*     */       
/* 123 */       byte[] retBytes = bos.toByteArray();
/* 124 */       return retBytes;
/*     */     }
/* 126 */     catch (IOException iOException) {
/*     */ 
/*     */       
/* 129 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static class IncrementImpl
/*     */     extends Checkpoint.Increment {
/*     */     VectorClock incVC;
/*     */     
/*     */     public static Checkpoint.Increment create(byte[] storedForm) throws IOException {
/*     */       try {
/* 139 */         ByteArrayInputStream bais = new ByteArrayInputStream(storedForm);
/* 140 */         DataInputStream dis = new DataInputStream(bais);
/*     */         
/* 142 */         String sanity = dis.readUTF();
/* 143 */         if (!sanity.equals("incCT"))
/* 144 */           throw new IOException(ExceptionBuilder.buildReasonString(338437231, null)); 
/* 145 */         VectorClock vc = new VectorClock();
/* 146 */         vc.read(dis);
/* 147 */         return new IncrementImpl(vc);
/*     */       }
/* 149 */       catch (Exception e) {
/* 150 */         throw new IOException(ExceptionBuilder.buildReasonString(338437231, null));
/*     */       } 
/*     */     }
/*     */     
/*     */     public byte[] toByteArray() {
/*     */       try {
/* 156 */         ByteArrayOutputStream bos = new ByteArrayOutputStream();
/* 157 */         DataOutputStream dos = new DataOutputStream(bos);
/* 158 */         dos.writeUTF("incCT");
/* 159 */         this.incVC.write(dos);
/*     */         
/* 161 */         byte[] retBytes = bos.toByteArray();
/* 162 */         return retBytes;
/*     */       }
/* 164 */       catch (IOException iOException) {
/*     */ 
/*     */         
/* 167 */         return null;
/*     */       } 
/*     */     }
/*     */     protected IncrementImpl(VectorClock incVC) {
/* 171 */       this.incVC = incVC;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void update(Checkpoint.Increment incrs) {
/* 178 */     IncrementImpl increment = (IncrementImpl)incrs;
/* 179 */     Enumeration<StampPair> e = increment.incVC.allElements();
/* 180 */     while (e.hasMoreElements()) {
/* 181 */       StampPair sp = e.nextElement();
/* 182 */       this.vc.set(Long.valueOf(sp.pid), sp.stamp);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 188 */     return "topic=" + this.topic + ",selector=" + this.selector + "," + this.vc.toString();
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\client\CheckpointImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */