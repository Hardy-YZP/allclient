package com.ibm.disthub2.impl.client;

import com.ibm.disthub2.client.Topic;
import com.ibm.disthub2.impl.formats.MessageHandle;
import java.io.IOException;

public interface MinimalMatchMaker {
  void removeSubscription(SubscriptionInfo paramSubscriptionInfo);
  
  void addMatchTarget(Topic paramTopic, String paramString, SubscriptionInfo paramSubscriptionInfo) throws IOException;
  
  boolean doesThisMatch(ConnectorImpl paramConnectorImpl, SubscriptionInfo paramSubscriptionInfo, MessageHandle paramMessageHandle) throws IOException;
  
  void matchAndDeliver(ConnectorImpl paramConnectorImpl, boolean paramBoolean, long paramLong1, long paramLong2, MessageHandle paramMessageHandle) throws IOException;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\client\MinimalMatchMaker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */