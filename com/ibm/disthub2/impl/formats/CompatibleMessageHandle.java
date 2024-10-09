/*    */ package com.ibm.disthub2.impl.formats;
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
/*    */ public final class CompatibleMessageHandle
/*    */   extends CompatibleMessageDataHandle
/*    */   implements MessageHandle
/*    */ {
/*    */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*    */   
/*    */   public CompatibleMessageHandle(CompatibilityMap map, MessageHandle encoding, FlatSchema schema) {
/* 46 */     super(map, encoding, schema);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public MessageHandle duplicate() {
/* 52 */     return new CompatibleMessageHandle(this.map, ((MessageHandle)this.encoding).duplicate(), this.schema);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public short getInterpreterId() {
/* 58 */     return ((MessageHandle)this.encoding).getInterpreterId();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Schema[] getSchemata() {
/* 65 */     return ((MessageHandle)this.encoding).getSchemata();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean changed() {
/* 72 */     return ((MessageHandle)this.encoding).changed();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getEncodedLength(MessageEncrypter encrypter) {
/* 79 */     return ((MessageHandle)this.encoding).getEncodedLength(encrypter);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int toByteArray(byte[] frame, int offset, int length, MessageEncrypter encrypter) {
/* 86 */     return ((MessageHandle)this.encoding).toByteArray(frame, offset, length, encrypter);
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\CompatibleMessageHandle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */