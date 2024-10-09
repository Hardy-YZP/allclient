/*    */ package com.ibm.msg.client.commonservices.passwordprotection;
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
/*    */ public class PBEException
/*    */   extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = -9009547163724402611L;
/*    */   private PBERC pbrc;
/*    */   
/*    */   public PBEException(String message, PBERC RC) {
/* 31 */     super(message);
/* 32 */     this.pbrc = RC;
/*    */   }
/*    */   
/*    */   public PBERC getPBERC() {
/* 36 */     return this.pbrc;
/*    */   }
/*    */   
/*    */   public enum PBERC {
/* 40 */     OK,
/* 41 */     ENCRYPTFAILURE,
/* 42 */     DECRYPTFAILURE,
/* 43 */     UNKNOWNPASSWORDENCODING,
/* 44 */     INITKEYFAILURE,
/* 45 */     INVALIDINITIALKEY,
/* 46 */     INVALIDFIXEDSALT,
/* 47 */     INITCIPHERFAILURE,
/* 48 */     UNRECOGNIZEDALGORITHM,
/* 49 */     ALGORITHMTOOOLD,
/* 50 */     NOSHAREDCIPHER,
/* 51 */     MISSINGEYECATCHER,
/* 52 */     ENCODEDSTRINGINCORRECTFORMAT,
/* 53 */     INITALGORITHMFAILURE,
/* 54 */     INITIALKEYFILEBADFORMAT,
/* 55 */     CANNOTREADINITIALKEYFILE;
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\passwordprotection\PBEException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */