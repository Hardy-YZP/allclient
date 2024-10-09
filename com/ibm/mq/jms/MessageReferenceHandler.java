package com.ibm.mq.jms;

public interface MessageReferenceHandler {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MessageReferenceHandler.java";
  
  void handleMessageReference(MessageReference paramMessageReference);
  
  void endDeliver();
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MessageReferenceHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */