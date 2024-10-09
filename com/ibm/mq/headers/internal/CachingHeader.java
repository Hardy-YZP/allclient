package com.ibm.mq.headers.internal;

import java.io.IOException;

public interface CachingHeader {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/CachingHeader.java";
  
  void readCachedContent() throws Exception;
  
  void writeCachedContent() throws IOException;
  
  void discardCachedContent();
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\internal\CachingHeader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */