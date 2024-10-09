package com.ibm.mq.ese.service;

import com.ibm.mq.ese.config.ConfigException;
import com.ibm.mq.ese.config.KeyStoreConfig;
import com.ibm.mq.ese.core.AMBIException;
import com.ibm.mq.ese.core.EseUser;
import java.io.File;

public interface UserMapService {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/service/UserMapService.java";
  
  String getExternalUsername() throws UserMapException;
  
  KeyStoreConfig readKeystoreConfig(File paramFile) throws ConfigException;
  
  EseUser getCredentials() throws ConfigException, UserMapException, AMBIException;
  
  EseUser getCredentials(String paramString) throws ConfigException, AMBIException;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\service\UserMapService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */