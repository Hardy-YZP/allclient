package com.ibm.mq.jmqi;

import com.ibm.mq.jmqi.handles.Hconn;
import com.ibm.mq.jmqi.handles.Pint;
import javax.transaction.xa.Xid;

public interface JmqiXA {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/JmqiXA.java";
  
  public static final int XIDDATASIZE = 128;
  
  public static final int MAXGTRIDSIZE = 64;
  
  public static final int MAXBQUALSIZE = 64;
  
  public static final int RMNAMESZ = 32;
  
  public static final int MAXINFOSIZE = 256;
  
  public static final int TMNOFLAGS = 0;
  
  public static final int TMREGISTER = 1;
  
  public static final int TMNOMIGRATE = 2;
  
  public static final int TMUSEASYNC = 4;
  
  public static final int TMASYNC = -2147483648;
  
  public static final int TMONEPHASE = 1073741824;
  
  public static final int TMFAIL = 536870912;
  
  public static final int TMNOWAIT = 268435456;
  
  public static final int TMRESUME = 134217728;
  
  public static final int TMSUCCESS = 67108864;
  
  public static final int TMSUSPEND = 33554432;
  
  public static final int TMSTARTRSCAN = 16777216;
  
  public static final int TMENDRSCAN = 8388608;
  
  public static final int TMMULTIPLE = 4194304;
  
  public static final int TMJOIN = 2097152;
  
  public static final int TMMIGRATE = 1048576;
  
  public static final int TM_JOIN = 2;
  
  public static final int TM_RESUME = 1;
  
  public static final int TM_OK = 0;
  
  public static final int TMER_TMERR = -1;
  
  public static final int TMER_INVAL = -2;
  
  public static final int TMER_PROTO = -3;
  
  public static final int XA_RBBASE = 100;
  
  public static final int XA_RBROLLBACK = 100;
  
  public static final int XA_RBCOMMFAIL = 101;
  
  public static final int XA_RBDEADLOCK = 102;
  
  public static final int XA_RBINTEGRITY = 103;
  
  public static final int XA_RBOTHER = 104;
  
  public static final int XA_RBPROTO = 105;
  
  public static final int XA_RBTIMEOUT = 106;
  
  public static final int XA_RBTRANSIENT = 107;
  
  public static final int XA_RBEND = 107;
  
  public static final int XA_NOMIGRATE = 9;
  
  public static final int XA_HEURHAZ = 8;
  
  public static final int XA_HEURCOM = 7;
  
  public static final int XA_HEURRB = 6;
  
  public static final int XA_HEURMIX = 5;
  
  public static final int XA_RETRY = 4;
  
  public static final int XA_RDONLY = 3;
  
  public static final int XA_OK = 0;
  
  public static final int XAER_ASYNC = -2;
  
  public static final int XAER_RMERR = -3;
  
  public static final int XAER_NOTA = -4;
  
  public static final int XAER_INVAL = -5;
  
  public static final int XAER_PROTO = -6;
  
  public static final int XAER_RMFAIL = -7;
  
  public static final int XAER_DUPID = -8;
  
  public static final int XAER_OUTSIDE = -9;
  
  int xa_close(Hconn paramHconn, String paramString, int paramInt1, int paramInt2);
  
  int xa_commit(Hconn paramHconn, Xid paramXid, int paramInt1, int paramInt2);
  
  int xa_complete(Hconn paramHconn, Pint paramPint1, Pint paramPint2, int paramInt1, int paramInt2);
  
  int xa_end(Hconn paramHconn, Xid paramXid, int paramInt1, int paramInt2);
  
  int xa_forget(Hconn paramHconn, Xid paramXid, int paramInt1, int paramInt2);
  
  int xa_open(Hconn paramHconn, String paramString, int paramInt1, int paramInt2);
  
  int xa_open_tm(Hconn paramHconn, String paramString, int paramInt1, int paramInt2);
  
  int xa_prepare(Hconn paramHconn, Xid paramXid, int paramInt1, int paramInt2);
  
  int xa_recover(Hconn paramHconn, Xid[] paramArrayOfXid, int paramInt1, int paramInt2);
  
  int xa_rollback(Hconn paramHconn, Xid paramXid, int paramInt1, int paramInt2);
  
  int xa_start(Hconn paramHconn, Xid paramXid, int paramInt1, int paramInt2);
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\JmqiXA.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */