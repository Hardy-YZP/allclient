package com.ibm.mq.ese.core;

import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.List;

public interface KeyStoreAccess {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/core/KeyStoreAccess.java";
  
  X509Certificate getCertificate(String paramString) throws AMBIException;
  
  PrivateKey getPrivateKey(String paramString) throws AMBIException;
  
  PrivateKey getPrivateKey(String paramString1, String paramString2) throws AMBIException;
  
  Enumeration<String> aliases() throws AMBIException;
  
  boolean containsAlias(String paramString) throws AMBIException;
  
  Certificate[] getCertificateChain(String paramString) throws AMBIException;
  
  String getType();
  
  KeyStore getKeyStore();
  
  boolean isCertificateEntry(String paramString) throws AMBIException;
  
  boolean isKeyEntry(String paramString) throws AMBIException;
  
  X509Certificate[] getCertificates(List<String> paramList, boolean paramBoolean) throws AMBIException;
  
  String getProvider();
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\core\KeyStoreAccess.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */