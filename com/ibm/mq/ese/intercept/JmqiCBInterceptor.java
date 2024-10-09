package com.ibm.mq.ese.intercept;

import com.ibm.mq.ese.service.EseMQException;
import com.ibm.mq.jmqi.MQCBD;
import com.ibm.mq.jmqi.MQGMO;
import com.ibm.mq.jmqi.MQMD;
import com.ibm.mq.jmqi.handles.Hconn;
import com.ibm.mq.jmqi.handles.Hobj;
import com.ibm.mq.jmqi.handles.Pint;

public interface JmqiCBInterceptor extends JmqiInterceptor {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/intercept/JmqiCBInterceptor.java";
  
  void beforeMQCB(Hconn paramHconn, int paramInt, MQCBD paramMQCBD, Hobj paramHobj, MQMD paramMQMD, MQGMO paramMQGMO, Pint paramPint1, Pint paramPint2);
  
  void afterMQCB(Hconn paramHconn, int paramInt, MQCBD paramMQCBD, Hobj paramHobj, MQMD paramMQMD, MQGMO paramMQGMO, Pint paramPint1, Pint paramPint2) throws EseMQException;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\intercept\JmqiCBInterceptor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */