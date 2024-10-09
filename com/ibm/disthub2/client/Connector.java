package com.ibm.disthub2.client;

import java.io.IOException;

public interface Connector {
  public static final int BEST_EFFORT_SUBSCRIPTION = 1;
  
  public static final int JMS_COMPLIANT_SUBSCRIPTION = 2;
  
  public static final int GUARANTEED_SUBSCRIPTION = 3;
  
  public static final int DURABLE_SUBSCRIPTION = 4;
  
  public static final int NON_DS = -1;
  
  public static final int LWDS = 0;
  
  public static final int JMSDS = 1;
  
  Exception getException();
  
  Topic createTopic(String paramString) throws IOException;
  
  Topic createTemporaryTopic(String paramString) throws IOException;
  
  void startDelivery() throws IOException;
  
  void stopDelivery() throws IOException;
  
  int subscribe(Topic paramTopic, String paramString, int paramInt) throws IOException;
  
  int subscribe(Topic paramTopic, String paramString) throws IOException;
  
  void unsubscribe(int paramInt) throws IOException;
  
  void close(boolean paramBoolean);
  
  void send(Message paramMessage) throws IOException;
  
  String getClientId();
  
  void setClientId(String paramString) throws IOException;
  
  String getPublisherId();
  
  void setPublisherId(String paramString) throws IOException;
  
  String newSubscription(Topic paramTopic, String paramString) throws IOException;
  
  String newSubscription(Topic paramTopic, String paramString, Checkpoint paramCheckpoint) throws IOException;
  
  String newSubscription(Topic paramTopic, String paramString, long paramLong) throws IOException;
  
  boolean isInitiallyGapless(String paramString) throws IOException;
  
  void reactivate(String paramString, Checkpoint paramCheckpoint) throws IOException;
  
  void deactivate(String paramString) throws IOException;
  
  void unsubscribe(String paramString) throws IOException;
  
  String[] querySubscriptions(Topic paramTopic, String paramString) throws IOException;
  
  void release(String paramString, Checkpoint paramCheckpoint) throws IOException;
  
  Checkpoint checkpoint(Event paramEvent, String paramString) throws IOException;
  
  Checkpoint.Increment checkpoint(Event paramEvent, String paramString, Checkpoint paramCheckpoint) throws IOException;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\client\Connector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */