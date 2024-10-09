package com.ibm.disthub2.impl.formats;

import com.ibm.disthub2.client.MessageBodyHandle;

public interface MessageDataHandle extends MessageBodyHandle {
  Schema getInternalSchema();
  
  Schema getEncodingSchema();
  
  byte[] getEncodedObject(int paramInt);
  
  Object getInternalValue(int paramInt);
  
  void setEncodedObject(int paramInt, byte[] paramArrayOfbyte);
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\MessageDataHandle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */