/*     */ package com.ibm.mq.jms.admin;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class JMSADM_Messages
/*     */ {
/*     */   static final String sccsid = "@(#) com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/JMSADM_Messages.java, jmscc.admin, k000  1.5 07/07/02 20:40:06";
/*     */   public static final String MQJMS_ADMIN_START = "JMSADM4000";
/*     */   public static final String MQJMS_ADMIN_STOP = "JMSADM4001";
/*     */   public static final String MQJMS_ADMIN_INIT = "JMSADM4002";
/*     */   public static final String MQJMS_ADMIN_DONE = "JMSADM4003";
/*     */   public static final String MQJMS_ADMIN_INITCTX = "JMSADM4007";
/*     */   public static final String MQJMS_ADMIN_ERROR = "JMSADM4008";
/*     */   public static final String MQJMS_ADMIN_CTX_NOT_EMPTY = "JMSADM4009";
/*     */   public static final String MQJMS_ADMIN_TRANS_TYPE = "JMSADM4053";
/*     */   public static final String MQJMS_ADMIN_WELCOME_CLI = "JMSADM4085";
/*     */   public static final String MQJMS_ADMIN_ERROR_CMD = "JMSADM4086";
/*     */   public static final String MQJMS_ADMIN_UNKNOWN_CMD = "JMSADM4087";
/*     */   public static final String MQJMS_ADMIN_CONTENTS_OF = "JMSADM4089";
/*     */   public static final String MQJMS_ADMIN_ICF_ID = "JMSADM4094";
/*     */   public static final String MQJMS_ADMIN_PURL_ID = "JMSADM4095";
/*     */   public static final String MQJMS_ADMIN_BND_NONADMIN = "JMSADM4096";
/*     */   public static final String MQJMS_ADMIN_CTX_NOTFND = "JMSADM4097";
/*     */   public static final String MQJMS_ADMIN_OBJ_S = "JMSADM4098";
/*     */   public static final String MQJMS_ADMIN_CTX_S = "JMSADM4099";
/*     */   public static final String MQJMS_ADMIN_BND_S = "JMSADM4100";
/*     */   public static final String MQJMS_ADMIN_ADMINISTERED = "JMSADM4101";
/*     */   public static final String MQJMS_ADMIN_OBJ_INACTIVE = "JMSADM4104";
/*     */   public static final String MQJMS_ADMIN_CONF_MISSING = "JMSADM4105";
/*     */   public static final String MQJMS_ADMIN_NON_MQJMS = "JMSADM4106";
/*     */   public static final String MQJMS_ADMIN_JNDI_INITFAIL = "JMSADM4110";
/*     */   public static final String MQJMS_ADMIN_NEW_CTX_FAIL = "JMSADM4111";
/*     */   public static final String MQJMS_ADMIN_VAL_OBJ_FAIL = "JMSADM4112";
/*     */   
/*     */   static {
/*  44 */     if (Trace.isOn) {
/*  45 */       Trace.data("com.ibm.mq.jms.admin.JMSADM_Messages", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/JMSADM_Messages.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String MQJMS_ADMIN_BIND_FAIL = "JMSADM4113";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String MQJMS_ADMIN_INVALID_NAME = "JMSADM4115";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String MQJMS_ADMIN_FOR_MORE_INFO = "JMSADM4116";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String MQJMS_NAME_NOT_RESOLVED = "JMSADM4117";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String MQJMS_ADMIN_SYN_ERR = "JMSADM4120";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String MQJMS_ADMIN_CNT_OPEN_CFG = "JMSADM4121";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String MQJMS_ADMIN_MV_SEMIFAIL = "JMSADM4122";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String MQJMS_ADMIN_LEXERR = "JMSADM4123";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String MQJMS_ADMIN_PROPVAL_NULL = "JMSADM4124";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String MQJMS_ADMIN_INV_PROP = "JMSADM4125";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String MQJMS_ADMIN_INV_PROP_CTX = "JMSADM4127";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String MQJMS_ADMIN_INV_PROP_VAL = "JMSADM4128";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String MQJMS_ADMIN_PROP_UNK = "JMSADM4129";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String MQJMS_ADMIN_CTX_NOTFNDU = "JMSADM4130";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String MQJMS_ADMIN_OBJTYPE_MISMATCH = "JMSADM4131";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String MQJMS_ADMIN_CLASH_CLIENT = "JMSADM4132";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String MQJMS_ADMIN_CLASH_EXITINIT = "JMSADM4133";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String MQJMS_ADMIN_USERDN = "JMSADM4134";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String MQJMS_ADMIN_USERPW = "JMSADM4135";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String MQJMS_ADMIN_WS_INST = "JMSADM4137";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String MQJMS_ADMIN_INVALID_AUTH_TYPE = "JMSADM4139";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String MQJMS_ADMIN_CONVERT_CIPHER = "JMSADM4140";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String MQJMS_ADMIN_ICF_NOT_FOUND = "JMSADM4142";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String MQJMS_E_INTERNAL_ERROR = "JMSADM1016";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String MQJMS_E_NO_UTF8 = "JMSADM1059";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String MQJMS_PRODUCT_COPYRIGHT = "JMSADM1003";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String MQJMS_UTIL_IGNOREUNK = "JMSADM5003";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private JMSADM_Messages() {
/* 387 */     if (Trace.isOn) {
/* 388 */       Trace.entry(this, "com.ibm.mq.jms.admin.JMSADM_Messages", "<init>()");
/*     */     }
/* 390 */     if (Trace.isOn)
/* 391 */       Trace.exit(this, "com.ibm.mq.jms.admin.JMSADM_Messages", "<init>()"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\JMSADM_Messages.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */