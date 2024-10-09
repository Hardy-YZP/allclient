package com.ibm.disthub2.client;

import java.io.IOException;
import java.io.Serializable;
import java.util.Dictionary;
import java.util.Vector;

public interface Message extends Event {
  public static final int MESSAGE = 1;
  
  public static final int BYTES_MESSAGE = 3;
  
  public static final int MAP_MESSAGE = 6;
  
  public static final int TEXT_MESSAGE = 4;
  
  public static final int STREAM_MESSAGE = 5;
  
  public static final int OBJECT_MESSAGE = 2;
  
  public static final int SCHEMATIZED_MESSAGE = 7;
  
  Object clone();
  
  int getMessageType();
  
  void clearBody();
  
  byte[] getBytesBody();
  
  void setBytesBody(byte[] paramArrayOfbyte);
  
  String getTextBody();
  
  void setTextBody(String paramString);
  
  Serializable getObjectBody() throws IOException, ClassNotFoundException;
  
  void setObjectBody(Serializable paramSerializable) throws IOException;
  
  Vector getFields();
  
  void setFields(Vector paramVector);
  
  Dictionary getNamedFields();
  
  void setNamedFields(Dictionary paramDictionary);
  
  MessageBodyHandle getSchematizedBody(Schema paramSchema);
  
  MessageBodyHandle setSchematizedBody(Schema paramSchema);
  
  Dictionary getProperties();
  
  void setProperties(Dictionary paramDictionary);
  
  void clearProperties();
  
  String getCorrelationID();
  
  void setCorrelationID(String paramString);
  
  long getMessageID();
  
  int getPriority();
  
  void setPriority(int paramInt);
  
  long getTimestamp();
  
  void setTimestamp(long paramLong);
  
  String getJMSTypeField();
  
  void setJMSTypeField(String paramString);
  
  boolean getPersistent();
  
  void setPersistent(boolean paramBoolean);
  
  Topic getReplyTopic();
  
  void setReplyTopic(Topic paramTopic);
  
  Topic getTopic();
  
  void setTopic(Topic paramTopic);
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\client\Message.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */