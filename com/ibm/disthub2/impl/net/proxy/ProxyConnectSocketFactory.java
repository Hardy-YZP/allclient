package com.ibm.disthub2.impl.net.proxy;

import com.ibm.disthub2.impl.net.SocketFactory;
import java.io.IOException;
import java.net.Socket;

public interface ProxyConnectSocketFactory extends SocketFactory {
  Socket createSocket(String paramString1, int paramInt1, String paramString2, int paramInt2) throws IOException;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\net\proxy\ProxyConnectSocketFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */