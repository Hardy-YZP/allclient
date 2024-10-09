package com.ibm.msg.client.commonservices.trace;

import java.io.PrintWriter;

public interface DumpableComponent {
  void dump(PrintWriter paramPrintWriter, int paramInt);
  
  String getComponentName();
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\trace\DumpableComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */