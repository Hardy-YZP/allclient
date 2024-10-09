package com.ibm.mq.ese.prot;

import com.ibm.mq.ese.core.AMBIHeader;
import com.ibm.mq.ese.core.EseUser;
import com.ibm.mq.ese.core.SecurityPolicy;
import com.ibm.mq.ese.intercept.SmqiObject;
import com.ibm.mq.jmqi.JmqiException;

public interface MessageProtection {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/prot/MessageProtection.java";
  
  byte[] protect(byte[] paramArrayOfbyte, SmqiObject paramSmqiObject, AMBIHeader paramAMBIHeader, EseUser paramEseUser) throws MessageProtectionException;
  
  MessageUnprotectInfo unprotect(byte[] paramArrayOfbyte, SecurityPolicy paramSecurityPolicy, AMBIHeader paramAMBIHeader, SmqiObject paramSmqiObject, EseUser paramEseUser) throws MessageProtectionException;
  
  boolean isValid();
  
  boolean initialise() throws JmqiException;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\prot\MessageProtection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */