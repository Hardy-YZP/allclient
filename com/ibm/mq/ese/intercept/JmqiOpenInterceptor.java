package com.ibm.mq.ese.intercept;

import com.ibm.mq.jmqi.MQOD;
import com.ibm.mq.jmqi.handles.Hconn;
import com.ibm.mq.jmqi.handles.Phobj;
import com.ibm.mq.jmqi.handles.Pint;
import com.ibm.mq.jmqi.system.SpiOpenOptions;

public interface JmqiOpenInterceptor extends JmqiInterceptor {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/intercept/JmqiOpenInterceptor.java";
  
  SmqiObject beforeMQOPEN(Hconn paramHconn, MQOD paramMQOD, int paramInt, Phobj paramPhobj, Pint paramPint1, Pint paramPint2);
  
  void afterMQOPEN(SmqiObject paramSmqiObject, Hconn paramHconn, MQOD paramMQOD, int paramInt, Phobj paramPhobj, Pint paramPint1, Pint paramPint2);
  
  SmqiObject beforeSpiOpen(Hconn paramHconn, MQOD paramMQOD, SpiOpenOptions paramSpiOpenOptions, Phobj paramPhobj, Pint paramPint1, Pint paramPint2);
  
  void afterSpiOpen(SmqiObject paramSmqiObject, Hconn paramHconn, MQOD paramMQOD, SpiOpenOptions paramSpiOpenOptions, Phobj paramPhobj, Pint paramPint1, Pint paramPint2);
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\intercept\JmqiOpenInterceptor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */