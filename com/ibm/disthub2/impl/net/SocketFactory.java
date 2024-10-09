package com.ibm.disthub2.impl.net;

import java.io.IOException;
import java.net.Socket;

public interface SocketFactory {
  Socket createSocket(String paramString, int paramInt) throws IOException;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\net\SocketFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */