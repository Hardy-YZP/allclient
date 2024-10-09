package com.ibm.disthub2.impl.formats;

public interface SchemaInterpreter {
  public static final int NUM_INTERPRETERS = 2;
  
  MessageHandle decode(FlatSchema paramFlatSchema, byte[] paramArrayOfbyte, int paramInt1, int paramInt2, MessageEncrypter paramMessageEncrypter, int paramInt3);
  
  MessageHandle newMessage(FlatSchema paramFlatSchema);
  
  MessageHandle reEncode(MessageHandle paramMessageHandle);
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\SchemaInterpreter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */