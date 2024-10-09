package com.ibm.disthub2.impl.formats;

public interface MessageHandle extends MessageDataHandle {
  short getInterpreterId();
  
  Schema[] getSchemata();
  
  boolean changed();
  
  int getEncodedLength(MessageEncrypter paramMessageEncrypter);
  
  int toByteArray(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, MessageEncrypter paramMessageEncrypter);
  
  MessageHandle duplicate();
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\MessageHandle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */