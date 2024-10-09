package com.ibm.disthub2.impl.matching.selector;

import java.io.IOException;

public interface CharStream {
  char readChar() throws IOException;
  
  int getColumn();
  
  int getLine();
  
  int getEndColumn();
  
  int getEndLine();
  
  int getBeginColumn();
  
  int getBeginLine();
  
  void backup(int paramInt);
  
  char BeginToken() throws IOException;
  
  String GetImage();
  
  char[] GetSuffix(int paramInt);
  
  void Done();
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\selector\CharStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */