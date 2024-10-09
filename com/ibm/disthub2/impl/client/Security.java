package com.ibm.disthub2.impl.client;

import com.ibm.disthub2.impl.formats.MessageEncrypter;
import com.ibm.disthub2.impl.formats.MessageHandle;
import com.ibm.disthub2.spi.Principal;
import java.io.IOException;
import java.net.Socket;

public interface Security {
  Principal createPrincipal(String paramString1, String paramString2);
  
  int authorize(Socket paramSocket, Principal paramPrincipal) throws IOException;
  
  MessageEncrypter incoming(byte[] paramArrayOfbyte) throws IOException;
  
  byte[] outgoing(MessageHandle paramMessageHandle, byte paramByte) throws IOException;
  
  byte[] framePropagationMessage(byte[] paramArrayOfbyte) throws IOException;
  
  byte getQop(MessageHandle paramMessageHandle);
  
  void qopUpdate(MessageHandle paramMessageHandle) throws IOException;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\client\Security.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */