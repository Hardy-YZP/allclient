package com.ibm.mq.ese.intercept;

import com.ibm.mq.constants.QmgrSplCapability;
import com.ibm.mq.jmqi.MQCNO;
import com.ibm.mq.jmqi.handles.Hconn;
import com.ibm.mq.jmqi.handles.Phconn;
import com.ibm.mq.jmqi.handles.Pint;
import com.ibm.mq.jmqi.system.JmqiConnectOptions;
import com.ibm.mq.jmqi.system.SpiConnectOptions;

public interface JmqiConnInterceptor extends JmqiInterceptor {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/intercept/JmqiConnInterceptor.java";
  
  void beforeMQCONN(String paramString, Phconn paramPhconn, Pint paramPint1, Pint paramPint2);
  
  void afterMQCONN(QmgrSplCapability paramQmgrSplCapability, String paramString, Phconn paramPhconn, Pint paramPint1, Pint paramPint2);
  
  void beforeMQCONNX(String paramString, MQCNO paramMQCNO, Phconn paramPhconn, Pint paramPint1, Pint paramPint2);
  
  void afterMQCONNX(QmgrSplCapability paramQmgrSplCapability, String paramString, MQCNO paramMQCNO, Phconn paramPhconn, Pint paramPint1, Pint paramPint2);
  
  void beforeJmqiConnect(String paramString, JmqiConnectOptions paramJmqiConnectOptions, MQCNO paramMQCNO, Hconn paramHconn, Phconn paramPhconn, Pint paramPint1, Pint paramPint2);
  
  void afterJmqiConnect(QmgrSplCapability paramQmgrSplCapability, String paramString, JmqiConnectOptions paramJmqiConnectOptions, MQCNO paramMQCNO, Hconn paramHconn, Phconn paramPhconn, Pint paramPint1, Pint paramPint2);
  
  void beforeSpiConnect(String paramString, SpiConnectOptions paramSpiConnectOptions, MQCNO paramMQCNO, Phconn paramPhconn, Pint paramPint1, Pint paramPint2);
  
  void afterSpiConnect(QmgrSplCapability paramQmgrSplCapability, String paramString, SpiConnectOptions paramSpiConnectOptions, MQCNO paramMQCNO, Phconn paramPhconn, Pint paramPint1, Pint paramPint2);
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\intercept\JmqiConnInterceptor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */