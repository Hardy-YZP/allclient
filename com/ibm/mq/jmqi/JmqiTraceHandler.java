package com.ibm.mq.jmqi;

@Deprecated
public interface JmqiTraceHandler {
  public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/JmqiTraceHandler.java";
  
  public static final int SEVERE_TRACE_LEVEL = 7;
  
  public static final int WARNING_TRACE_LEVEL = 6;
  
  public static final int INFO_TRACE_LEVEL = 5;
  
  public static final int CONFIG_TRACE_LEVEL = 4;
  
  public static final int FINE_TRACE_LEVEL = 3;
  
  public static final int FINER_TRACE_LEVEL = 2;
  
  public static final int FINEST_TRACE_LEVEL = 1;
  
  public static final int TRACE_USAGE_ENTRY = 5;
  
  public static final int TRACE_USAGE_EXIT = 5;
  
  public static final int TRACE_USAGE_THROW = 7;
  
  public static final int TRACE_USAGE_CATCH = 7;
  
  public static final int TRACE_USAGE_FINALLY = 5;
  
  public static final int TRACE_USAGE_WARNING = 6;
  
  public static final int TRACE_USAGE_INFO = 5;
  
  public static final int TRACE_USAGE_DATA = 6;
  
  @Deprecated
  void dataFmt(JmqiEnvironment paramJmqiEnvironment, Object paramObject1, int paramInt1, int paramInt2, String paramString, Object paramObject2);
  
  @Deprecated
  void dataFmt(JmqiEnvironment paramJmqiEnvironment, int paramInt1, int paramInt2, String paramString, Object paramObject);
  
  @Deprecated
  void entry(int paramInt1, int paramInt2);
  
  @Deprecated
  void entry(int paramInt1, int paramInt2, Object[] paramArrayOfObject);
  
  @Deprecated
  void entry(Object paramObject, int paramInt1, int paramInt2);
  
  @Deprecated
  void entry(Object paramObject, int paramInt1, int paramInt2, Object[] paramArrayOfObject);
  
  @Deprecated
  int entry_OO(int paramInt1, int paramInt2);
  
  @Deprecated
  int entry_OO(int paramInt1, int paramInt2, Object[] paramArrayOfObject);
  
  @Deprecated
  int entry_OO(Object paramObject, int paramInt1, int paramInt2);
  
  @Deprecated
  int entry_OO(Object paramObject, int paramInt1, int paramInt2, Object[] paramArrayOfObject);
  
  @Deprecated
  void exit(int paramInt1, int paramInt2);
  
  @Deprecated
  void exit(int paramInt1, int paramInt2, int paramInt3);
  
  @Deprecated
  void exit(int paramInt1, int paramInt2, Object paramObject);
  
  @Deprecated
  void exit(int paramInt1, int paramInt2, Object paramObject, int paramInt3);
  
  @Deprecated
  void exit(Object paramObject, int paramInt1, int paramInt2);
  
  @Deprecated
  void exit(Object paramObject, int paramInt1, int paramInt2, int paramInt3);
  
  @Deprecated
  void exit(Object paramObject1, int paramInt1, int paramInt2, Object paramObject2);
  
  @Deprecated
  void exit(Object paramObject1, int paramInt1, int paramInt2, Object paramObject2, int paramInt3);
  
  @Deprecated
  void exit_OO(int paramInt1, int paramInt2, int paramInt3);
  
  @Deprecated
  void exit(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  @Deprecated
  void exit(int paramInt1, int paramInt2, int paramInt3, Object paramObject);
  
  @Deprecated
  void exit(int paramInt1, int paramInt2, int paramInt3, Object paramObject, int paramInt4);
  
  @Deprecated
  void exit(int paramInt1, Object paramObject, int paramInt2, int paramInt3);
  
  @Deprecated
  void exit(int paramInt1, Object paramObject, int paramInt2, int paramInt3, int paramInt4);
  
  @Deprecated
  void exit(int paramInt1, Object paramObject1, int paramInt2, int paramInt3, Object paramObject2);
  
  @Deprecated
  void exit(int paramInt1, Object paramObject1, int paramInt2, int paramInt3, Object paramObject2, int paramInt4);
  
  @Deprecated
  void data(int paramInt1, Object paramObject1, int paramInt2, int paramInt3, String paramString, Object paramObject2);
  
  @Deprecated
  void data(Object paramObject1, int paramInt1, int paramInt2, String paramString, Object paramObject2);
  
  @Deprecated
  void trace(Object paramObject, int paramInt1, int paramInt2, String paramString);
  
  @Deprecated
  void data(int paramInt1, int paramInt2, String paramString, Object paramObject);
  
  @Deprecated
  void catchBlock(int paramInt1, int paramInt2, Throwable paramThrowable);
  
  @Deprecated
  void catchBlock(int paramInt1, int paramInt2, Throwable paramThrowable, int paramInt3);
  
  @Deprecated
  void catchBlock(Object paramObject, int paramInt1, int paramInt2, Throwable paramThrowable);
  
  @Deprecated
  void catchBlock(Object paramObject, int paramInt1, int paramInt2, Throwable paramThrowable, int paramInt3);
  
  @Deprecated
  void throwing(int paramInt1, int paramInt2, Throwable paramThrowable);
  
  @Deprecated
  void throwing(int paramInt1, int paramInt2, Throwable paramThrowable, int paramInt3);
  
  @Deprecated
  void throwing(Object paramObject, int paramInt1, int paramInt2, Throwable paramThrowable);
  
  @Deprecated
  void throwing(Object paramObject, int paramInt1, int paramInt2, Throwable paramThrowable, int paramInt3);
  
  @Deprecated
  void finallyBlock(int paramInt1, int paramInt2);
  
  @Deprecated
  void finallyBlock(int paramInt1, int paramInt2, int paramInt3);
  
  @Deprecated
  void finallyBlock(Object paramObject, int paramInt1, int paramInt2);
  
  @Deprecated
  void finallyBlock(Object paramObject, int paramInt1, int paramInt2, int paramInt3);
  
  @Deprecated
  void ffst(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, String paramString1, String paramString2, String paramString3);
  
  @Deprecated
  void ffst(Object paramObject, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, String paramString1, String paramString2, String paramString3);
  
  @Deprecated
  void ffst(Object paramObject, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, String paramString1, String paramString2, String paramString3, Throwable paramThrowable);
  
  @Deprecated
  void setOn(boolean paramBoolean);
}


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\JmqiTraceHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */