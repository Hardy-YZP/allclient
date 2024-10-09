package com.ibm.disthub2.client;

public interface SubscriptionListener {
  void onMessage(Message paramMessage, int paramInt);
  
  void onMessage(Message paramMessage, String paramString);
  
  void gap(Event paramEvent, String paramString);
  
  void exception(Exception paramException);
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\client\SubscriptionListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */