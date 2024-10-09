package com.ibm.mq.ese.pki;

import com.ibm.mq.ese.core.KeyStoreAccess;
import com.ibm.mq.ese.core.PkiSpec;
import com.ibm.mq.jmqi.JmqiException;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.List;

public interface CertAccess {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/pki/CertAccess.java";
  
  X509Certificate[] loadCertificates(KeyStoreAccess paramKeyStoreAccess, PkiSpec paramPkiSpec, List<String> paramList) throws MissingCertificateException, CertAccessException;
  
  X509CRL[] loadCRLs(KeyStoreAccess paramKeyStoreAccess, PkiSpec paramPkiSpec, X509Certificate[] paramArrayOfX509Certificate) throws CrlAccessException;
  
  boolean initialise() throws JmqiException;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\pki\CertAccess.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */