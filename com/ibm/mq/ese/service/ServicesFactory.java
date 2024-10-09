package com.ibm.mq.ese.service;

import com.ibm.mq.ese.conv.CcsidConverter;
import com.ibm.mq.ese.pki.CertAccess;
import com.ibm.mq.ese.pki.X509CertificateValidator;
import com.ibm.mq.ese.prot.MessageProtection;
import com.ibm.mq.jmqi.JmqiEnvironment;
import com.ibm.mq.jmqi.JmqiMQ;
import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;

public interface ServicesFactory {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/service/ServicesFactory.java";
  
  EseMQService createMQService(JmqiMQ paramJmqiMQ, JmqiEnvironment paramJmqiEnvironment);
  
  UserMapService createIdentityService();
  
  CcsidConverter createCcsidConverter(JmqiMQ paramJmqiMQ, JmqiSystemEnvironment paramJmqiSystemEnvironment);
  
  PolicyService createPolicyService(JmqiMQ paramJmqiMQ, JmqiSystemEnvironment paramJmqiSystemEnvironment, EseMQService paramEseMQService);
  
  CertAccess createCertAccess(JmqiEnvironment paramJmqiEnvironment);
  
  MessageProtection createProtectionService(JmqiEnvironment paramJmqiEnvironment, X509CertificateValidator paramX509CertificateValidator);
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\service\ServicesFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */