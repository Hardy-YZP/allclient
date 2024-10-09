package com.ibm.disthub2.impl.formats;

public interface MessageEncrypter {
  void random(byte[] paramArrayOfbyte, int paramInt1, int paramInt2);
  
  int keySize();
  
  Object generateKey(byte[] paramArrayOfbyte, int paramInt1, int paramInt2);
  
  void encrypt(Object paramObject, byte[] paramArrayOfbyte1, int paramInt1, int paramInt2, byte[] paramArrayOfbyte2, int paramInt3);
  
  void decrypt(Object paramObject, byte[] paramArrayOfbyte1, int paramInt1, int paramInt2, byte[] paramArrayOfbyte2, int paramInt3);
  
  int digestLength();
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\MessageEncrypter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */