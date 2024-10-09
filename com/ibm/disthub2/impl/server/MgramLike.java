package com.ibm.disthub2.impl.server;

import com.ibm.disthub2.impl.formats.bridge.Jgram;

public interface MgramLike {
  int getSubId();
  
  InitialStateProcessor getISP();
  
  int getVersion();
  
  void setVersion(int paramInt);
  
  Jgram getJgram();
  
  void free();
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\server\MgramLike.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */