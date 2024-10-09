package com.ibm.msg.client.wmq.compat.jms.internal;

import com.ibm.msg.client.provider.ProviderConnectionBrowser;
import javax.jms.JMSException;

public interface ConnectionBrowser extends ProviderConnectionBrowser {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/ConnectionBrowser.java";
  
  void close() throws JMSException;
  
  void update(MessageReference paramMessageReference) throws JMSException;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\ConnectionBrowser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */