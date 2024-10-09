package com.ibm.msg.client.provider;

import java.io.Serializable;
import javax.jms.JMSException;

public interface ProviderSelfSerializingObjectMessage extends ProviderObjectMessage {
  public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.provider/src/com/ibm/msg/client/provider/ProviderSelfSerializingObjectMessage.java";
  
  void setSelfSerializedObject(Serializable paramSerializable) throws JMSException;
  
  Serializable getSelfSerializedObject() throws JMSException;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\provider\ProviderSelfSerializingObjectMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */