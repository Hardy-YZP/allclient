package com.ibm.msg.client.wmq.compat.base.internal;

import javax.transaction.xa.Xid;

public interface MQXAVerbs {
  int XACLOSE(String paramString, int paramInt1, int paramInt2) throws Exception;
  
  int XACOMMIT(Xid paramXid, int paramInt1, int paramInt2) throws Exception;
  
  int XAEND(Xid paramXid, int paramInt1, int paramInt2) throws Exception;
  
  int XAFORGET(Xid paramXid, int paramInt1, int paramInt2) throws Exception;
  
  int XAOPEN(String paramString, int paramInt1, int paramInt2) throws Exception;
  
  int XAPREPARE(Xid paramXid, int paramInt1, int paramInt2) throws Exception;
  
  int XARECOVER(Xid[] paramArrayOfXid, int paramInt1, int paramInt2) throws Exception;
  
  int XAROLLBACK(Xid paramXid, int paramInt1, int paramInt2) throws Exception;
  
  int XASTART(Xid paramXid, int paramInt1, int paramInt2) throws Exception;
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\base\internal\MQXAVerbs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */