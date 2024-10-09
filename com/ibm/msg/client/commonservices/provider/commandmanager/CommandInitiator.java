package com.ibm.msg.client.commonservices.provider.commandmanager;

import com.ibm.msg.client.commonservices.commandmanager.Command;

public interface CommandInitiator {
  void start() throws Exception;
  
  void stop();
  
  void commandCompleted(Command paramCommand);
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\provider\commandmanager\CommandInitiator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */