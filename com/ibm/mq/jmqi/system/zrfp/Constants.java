/*    */ package com.ibm.mq.jmqi.system.zrfp;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface Constants
/*    */ {
/*    */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/zrfp/Constants.java";
/*    */   public static final int zrfTYPE_STRING = 0;
/*    */   public static final int zrfTYPE_BOOLEAN = 1;
/*    */   public static final int zrfTYPE_BYTE_STRING = 2;
/*    */   public static final int zrfTYPE_INT8 = 3;
/*    */   public static final int zrfTYPE_INT16 = 4;
/*    */   public static final int zrfTYPE_INT32 = 5;
/*    */   public static final int zrfTYPE_INT64 = 6;
/*    */   public static final int zrfTYPE_FLOAT32 = 7;
/*    */   public static final int zrfTYPE_FLOAT64 = 8;
/*    */   public static final int zrfTYPE_NULL = 9;
/*    */   public static final int zrfTYPE_PARENT = 10;
/*    */   public static final int zrfTYPE_UNKNOWN = 11;
/*    */   public static final int zrfTYPE_LAST = 12;
/*    */   public static final int zrfTYPE_MAX = 255;
/* 67 */   public static final Integer TYPE_STRING = Integer.valueOf(0);
/*    */   
/* 69 */   public static final Integer TYPE_BOOLEAN = Integer.valueOf(1);
/*    */   
/* 71 */   public static final Integer TYPE_BYTE_STRING = Integer.valueOf(2);
/*    */   
/* 73 */   public static final Integer TYPE_INT8 = Integer.valueOf(3);
/*    */   
/* 75 */   public static final Integer TYPE_INT16 = Integer.valueOf(4);
/*    */   
/* 77 */   public static final Integer TYPE_INT32 = Integer.valueOf(5);
/*    */   
/* 79 */   public static final Integer TYPE_INT64 = Integer.valueOf(6);
/*    */   
/* 81 */   public static final Integer TYPE_FLOAT32 = Integer.valueOf(7);
/*    */   
/* 83 */   public static final Integer TYPE_FLOAT64 = Integer.valueOf(8);
/*    */   
/* 85 */   public static final Integer TYPE_NULL = Integer.valueOf(9);
/*    */   
/* 87 */   public static final Integer TYPE_PARENT = Integer.valueOf(10);
/*    */   
/* 89 */   public static final Integer TYPE_UNKNOWN = Integer.valueOf(11);
/*    */   
/* 91 */   public static final Integer TYPE_LAST = Integer.valueOf(12);
/*    */   
/* 93 */   public static final Integer TYPE_MAX = Integer.valueOf(255);
/*    */   public static final int zrfPROPERTY_FLAGS_NONE = 0;
/*    */   public static final int zrfPROPERTY_FLAGS_AS_STRING = 1;
/*    */   public static final int zrfPROPERTY_FLAGS_ESCAPED = 2;
/*    */   public static final int zrfPROPERTY_FLAGS_NAME_ATTR = 4;
/*    */   public static final int zrfPROPERTY_FLAGS_FOR_DELETION = 8;
/*    */   public static final int zrfPROPERTY_FLAGS_USER_CONTEXT = 16;
/*    */   public static final int zrfPROPERTY_FLAGS_RECOGNIZED = 32;
/*    */   public static final int zrfPROPERTY_FLAGS_FROM_MQRFH2 = 64;
/*    */   public static final int zrfPROPERTY_FLAGS_GROUPS_ALLOWED = 128;
/*    */   public static final int zrfPROPERTY_FLAGS_MQ_FOLDER = 256;
/*    */   public static final int zrfPROPERTY_FLAGS_COMPATIBLE = 512;
/*    */   public static final int zrfPROPERTY_FLAGS_HIDDEN = 1024;
/*    */   public static final int zrfPROPERTY_FLAGS_ALL = 2047;
/*    */   public static final int flagsForKnownFolder = 544;
/*    */   public static final int zrfPROPERTIES_STRUC_LENGTH = 24;
/*    */   public static final long zrfPROPS_STRUC_ID = 1515341392L;
/*    */   public static final int zrfPROPS_ENCODING_REVERSED = 1;
/*    */   public static final int zrfPROPS_INCLUDES_MQ_FOLDER = 2;
/*    */   public static final int zrfPROPS_VERIFY = 4;
/*    */   public static final int zrfPROPS_VERSION_1 = 1;
/*    */   public static final int zrfPROPS_NONE = 0;
/*    */   public static final int zrfPROPS_ALL = 7;
/*    */   public static final int xcsEBCDIC = 1;
/*    */   public static final int xcsASCII = 2;
/*    */   public static final int xcsISO = 3;
/*    */   public static final int xcsUNICODE = 4;
/*    */   public static final int zrfPARENT_STRUC_LENGTH_FIXED = 10;
/*    */   public static final int zrfPROP_STR_STRUC_LENGTH_FIXED = 15;
/*    */   public static final int zrfPROP_8_STRUC_LENGTH_FIXED = 12;
/*    */   public static final int zrfPROP_16_STRUC_LENGTH_FIXED = 13;
/*    */   public static final int zrfPROP_32_STRUC_LENGTH_FIXED = 15;
/*    */   public static final int zrfPROP_64_STRUC_LENGTH_FIXED = 19;
/*    */   public static final int zrfPROP_NULL_STRUC_LENGTH_FIXED = 11;
/*    */   public static final int zrfPROP_UNKNOWN_STRUC_LENGTH_FIXED = 23;
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\zrfp\Constants.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */