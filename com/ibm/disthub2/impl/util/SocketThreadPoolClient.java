package com.ibm.disthub2.impl.util;

public interface SocketThreadPoolClient {
  boolean doRead();
  
  boolean doWrite();
  
  void culled(SocketThreadPoolClientHndl paramSocketThreadPoolClientHndl);
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\imp\\util\SocketThreadPoolClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */