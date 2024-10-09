package com.ibm.msg.client.commonservices.passwordprotection;

public interface MQPasswordCipher {
  EncodedPasswordAbstract encode(int paramInt, char[] paramArrayOfchar, String paramString) throws PBEException;
  
  char[] decode(EncodedPasswordAbstract paramEncodedPasswordAbstract) throws PBEException;
  
  boolean verify(char[] paramArrayOfchar, EncodedPasswordAbstract paramEncodedPasswordAbstract, String paramString) throws PBEException;
  
  void reInitializeIfNecessary(char[] paramArrayOfchar, byte[] paramArrayOfbyte) throws PBEException;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\passwordprotection\MQPasswordCipher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */