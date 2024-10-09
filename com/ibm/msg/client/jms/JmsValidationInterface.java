package com.ibm.msg.client.jms;

import java.util.Vector;
import javax.jms.JMSException;

public interface JmsValidationInterface {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms/src/com/ibm/msg/client/jms/JmsValidationInterface.java";
  
  Vector<Object> mapToCanonical(String paramString, Object paramObject);
  
  Vector<Object> mapToCanonical(JmsPropertyContext paramJmsPropertyContext, String paramString, Object paramObject);
  
  String getCanonicalKey(String paramString);
  
  Object mapFromCanonical(String paramString, Object paramObject);
  
  Object mapFromCanonical(JmsPropertyContext paramJmsPropertyContext, String paramString, Object paramObject);
  
  boolean validate(Object paramObject1, Object paramObject2) throws JMSException;
  
  String crossPropertyValidate();
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\JmsValidationInterface.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */