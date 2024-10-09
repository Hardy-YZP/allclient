package com.ibm.disthub2.impl.net;

import com.ibm.disthub2.impl.util.SocketThreadPool;
import java.io.IOException;
import java.net.ServerSocket;

public interface ServerSocketFactory {
  ServerSocket createServerSocket(int paramInt) throws IOException;
  
  ServerSocket createServerSocket(int paramInt, SocketThreadPool paramSocketThreadPool) throws IOException;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\net\ServerSocketFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */