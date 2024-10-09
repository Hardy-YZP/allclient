package com.ibm.msg.client.commonservices.provider.commandmanager;

import com.ibm.msg.client.commonservices.CSIException;
import com.ibm.msg.client.commonservices.commandmanager.Command;

public interface CSPCommandManager {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/provider/commandmanager/CSPCommandManager.java";
  
  void addCommandHandler(CommandHandler paramCommandHandler, String paramString) throws CSIException;
  
  void removeCommandHandler(CommandHandler paramCommandHandler) throws CSIException;
  
  void start() throws CSIException;
  
  void stop() throws CSIException;
  
  void runCommand(CommandInitiator paramCommandInitiator, Command paramCommand) throws Exception;
  
  Command runCommand(Command paramCommand) throws Exception;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\provider\commandmanager\CSPCommandManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */