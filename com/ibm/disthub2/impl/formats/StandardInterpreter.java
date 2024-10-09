/*     */ package com.ibm.disthub2.impl.formats;
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
/*     */ public final class StandardInterpreter
/*     */   implements SchemaInterpreter
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   public static final short ID = 1;
/*     */   public static final short LEGACY_ID = 0;
/*     */   public boolean legacy;
/*  61 */   private static FlatSchema thisFlatSchema = ReleaseTable.envelopSchema.getFlatSchema();
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
/*     */   public StandardInterpreter(boolean legacy) {
/*  74 */     this.legacy = legacy;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MessageHandle decode(FlatSchema schema, byte[] contents, int offset, int length, MessageEncrypter encrypter, int sksl) {
/*  82 */     return new StandardMessageHandle(this.legacy, schema, contents, offset, length, encrypter, sksl);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MessageHandle newMessage(FlatSchema schema) {
/*  90 */     return new StandardMessageHandle(this.legacy, schema);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MessageHandle reEncode(MessageHandle oldHandle) {
/*  97 */     ((StandardMessageHandle)oldHandle).legacy = this.legacy;
/*  98 */     return oldHandle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static MessageHandle fastDecode(byte[] contents, int offset, int length, MessageEncrypter encrypter, int sksl) {
/* 106 */     return new StandardMessageHandle(false, thisFlatSchema, contents, offset, length, encrypter, sksl);
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
/*     */   public static MessageMap getMessageMap(FlatSchema sfs, long code) {
/* 118 */     MessageMap[] maps = (MessageMap[])sfs.getInterpreterCache(1);
/* 119 */     if (maps == null) {
/* 120 */       maps = new MessageMap[(int)(sfs.variants[0]).multiCount];
/* 121 */       sfs.setInterpreterCache(1, maps);
/*     */     } 
/* 123 */     MessageMap ans = maps[(int)code];
/* 124 */     if (ans == null)
/* 125 */       maps[(int)code] = ans = new MessageMap(sfs, code); 
/* 126 */     return ans;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static MessageMap getMessageMap(FlatSchema sfs, int[] choices) {
/* 135 */     long multiChoice = MessageMap.getMultiChoice(choices, sfs.variants, 0);
/* 136 */     return getMessageMap(sfs, multiChoice);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\StandardInterpreter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */