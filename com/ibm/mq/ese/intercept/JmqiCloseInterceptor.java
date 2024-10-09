package com.ibm.mq.ese.intercept;

import com.ibm.mq.jmqi.handles.Hconn;
import com.ibm.mq.jmqi.handles.Hobj;
import com.ibm.mq.jmqi.handles.Phobj;
import com.ibm.mq.jmqi.handles.Pint;

public interface JmqiCloseInterceptor extends JmqiInterceptor {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/intercept/JmqiCloseInterceptor.java";
  
  void afterMQCLOSE(Hobj paramHobj, Hconn paramHconn, Phobj paramPhobj, int paramInt, Pint paramPint1, Pint paramPint2);
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\intercept\JmqiCloseInterceptor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */