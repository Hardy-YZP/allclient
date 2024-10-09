package com.ibm.msg.client.commonservices.provider.nls;

import java.util.HashMap;
import java.util.MissingResourceException;

public interface CSPNLSServices {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/provider/nls/CSPNLSServices.java";
  
  void addCatalogue(String paramString1, String paramString2, Class<?> paramClass) throws MissingResourceException;
  
  String getMessage(String paramString, Object... paramVarArgs);
  
  Exception createException(String paramString, HashMap<String, ? extends Object> paramHashMap);
  
  String getMessage(String paramString, HashMap<String, ? extends Object> paramHashMap);
  
  String getExplanation(String paramString, HashMap<String, ? extends Object> paramHashMap);
  
  String getUserAction(String paramString, HashMap<String, ? extends Object> paramHashMap);
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\provider\nls\CSPNLSServices.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */