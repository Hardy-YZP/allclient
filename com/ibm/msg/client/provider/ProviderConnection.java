package com.ibm.msg.client.provider;

import com.ibm.msg.client.jms.JmsPropertyContext;
import java.io.PrintWriter;
import javax.jms.JMSException;

public interface ProviderConnection extends JmsPropertyContext {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.provider/src/com/ibm/msg/client/provider/ProviderConnection.java";
  
  ProviderSession createSession(JmsPropertyContext paramJmsPropertyContext) throws JMSException;
  
  void start() throws JMSException;
  
  void stop() throws JMSException;
  
  void close() throws JMSException;
  
  void setExceptionListener(ProviderExceptionListener paramProviderExceptionListener) throws JMSException;
  
  ProviderConnectionBrowser createConnectionBrowser(ProviderDestination paramProviderDestination, String paramString, ProviderMessageReferenceHandler paramProviderMessageReferenceHandler, int paramInt) throws JMSException;
  
  ProviderConnectionBrowser createDurableConnectionBrowser(ProviderDestination paramProviderDestination, String paramString1, String paramString2, String paramString3, ProviderMessageReferenceHandler paramProviderMessageReferenceHandler, int paramInt, boolean paramBoolean) throws JMSException;
  
  ProviderConnectionBrowser createSharedConnectionBrowser(ProviderDestination paramProviderDestination, String paramString1, String paramString2, String paramString3, ProviderMessageReferenceHandler paramProviderMessageReferenceHandler, int paramInt, boolean paramBoolean) throws JMSException;
  
  ProviderConnectionBrowser createSharedDurableConnectionBrowser(ProviderDestination paramProviderDestination, String paramString1, String paramString2, String paramString3, ProviderMessageReferenceHandler paramProviderMessageReferenceHandler, int paramInt, boolean paramBoolean) throws JMSException;
  
  ProviderMetaData getMetaData(JmsPropertyContext paramJmsPropertyContext) throws JMSException;
  
  void dump(PrintWriter paramPrintWriter, int paramInt);
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\provider\ProviderConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */