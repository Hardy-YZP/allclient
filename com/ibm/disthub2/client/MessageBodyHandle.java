package com.ibm.disthub2.client;

import java.io.IOException;
import java.io.Serializable;

public interface MessageBodyHandle {
  Schema getSchema();
  
  boolean getBoolean(int paramInt);
  
  byte getByte(int paramInt);
  
  short getShort(int paramInt);
  
  char getChar(int paramInt);
  
  int getInt(int paramInt);
  
  long getLong(int paramInt);
  
  float getFloat(int paramInt);
  
  double getDouble(int paramInt);
  
  String getString(int paramInt);
  
  Serializable getObject(int paramInt) throws IOException, ClassNotFoundException;
  
  byte[] getByteArray(int paramInt);
  
  Object getValue(int paramInt) throws ClassNotFoundException, IOException;
  
  MessageBodyHandle getDynamic(int paramInt, Schema paramSchema);
  
  MessageBodyHandle[] getTable(int paramInt);
  
  MessageBodyHandle getTableRow(int paramInt1, int paramInt2);
  
  MessageBodyHandle getSuccessor();
  
  int getChoice(int paramInt);
  
  boolean isPresent(int paramInt);
  
  void setBoolean(int paramInt, boolean paramBoolean);
  
  void setByte(int paramInt, byte paramByte);
  
  void setShort(int paramInt, short paramShort);
  
  void setChar(int paramInt, char paramChar);
  
  void setInt(int paramInt1, int paramInt2);
  
  void setLong(int paramInt, long paramLong);
  
  void setFloat(int paramInt, float paramFloat);
  
  void setDouble(int paramInt, double paramDouble);
  
  void setString(int paramInt, String paramString);
  
  void setObject(int paramInt, Serializable paramSerializable) throws IOException;
  
  void setByteArray(int paramInt, byte[] paramArrayOfbyte);
  
  void setValue(int paramInt, Object paramObject) throws IOException;
  
  MessageBodyHandle newDynamic(Schema paramSchema);
  
  void setDynamic(int paramInt, MessageBodyHandle paramMessageBodyHandle);
  
  MessageBodyHandle newTableRow(int paramInt);
  
  void setTable(int paramInt, MessageBodyHandle[] paramArrayOfMessageBodyHandle);
  
  void setChoice(int paramInt1, int paramInt2);
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\client\MessageBodyHandle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */