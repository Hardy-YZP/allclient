package com.ibm.disthub2.impl.server;

import com.ibm.disthub2.impl.formats.bridge.Jgram;
import com.ibm.disthub2.impl.matching.MatchingContext;

public interface RoutableMessage {
  String getTopic();
  
  byte[] getMdt();
  
  void setMdt(byte[] paramArrayOfbyte);
  
  byte getQop();
  
  void setQop(byte paramByte);
  
  boolean getQopQuery();
  
  void setQopQuery(boolean paramBoolean);
  
  void bindToMatchingContext(MatchingContext paramMatchingContext);
  
  Jgram convertToJgram();
  
  String getSubPoint();
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\server\RoutableMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */