/*     */ package com.ibm.mq.ese.nls;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.Log.Log;
/*     */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.HashMap;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AmsErrorMessages
/*     */ {
/*     */   static {
/*  39 */     if (Trace.isOn) {
/*  40 */       Trace.data("com.ibm.mq.ese.nls.AmsErrorMessages", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/nls/AmsErrorMessages.java");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  47 */     if (Trace.isOn) {
/*  48 */       Trace.entry("com.ibm.mq.ese.nls.AmsErrorMessages", "static()");
/*     */     }
/*     */     
/*  51 */     if (Trace.isOn) {
/*  52 */       Trace.entry(AmsErrorMessages.class.getCanonicalName(), "static()");
/*     */     }
/*     */     
/*  55 */     NLSServices.addCatalogue("com.ibm.mq.ese.nls.AMS_MessageResourceBundle", "AMS", AmsErrorMessages.class);
/*     */   }
/*  57 */   public static final String empty_message = "AMS0000";
/*  58 */   public static final String show_object = "AMS0001";
/*  59 */   public static final String mjs_msg_error_failed_to_obtain_policy = "AMS1000";
/*  60 */   public static final String mjm_msg_error_unprotect = "AMS1010";
/*  61 */   public static final String mjm_msg_error_recv_getting_ccsid_and_encoding = "AMS1011";
/*  62 */   public static final String mjc_keystoreprotect_usage = "AMS1020";
/*  63 */   public static final String mju_cfg_ambi_cfg_err_getting_system_properties = "AMS1030";
/*  64 */   public static final String mju_ras_unknown_msgcode = "AMS1035";
/*  65 */   public static final String mju_cannot_read_keystore_properties = "AMS1040";
/*  66 */   public static final String mju_user_certificate_not_found = "AMS1041";
/*  67 */   public static final String mju_user_certificate_not_found_MessageProtectionException = "AMS1042";
/*  68 */   public static final String mju_user_certificate_not_found_MissingCertificateException = "AMS1043";
/*  69 */   public static final String mju_user_privatekey_not_found = "AMS1044";
/*  70 */   public static final String mju_user_privatekey_not_found_MessageProtectionException = "AMS1045";
/*  71 */   public static final String mju_keystore_aliases_not_found = "AMS1046";
/*  72 */   public static final String mju_keystore_alias_verify = "AMS1047";
/*  73 */   public static final String mju_keystore_certificate_chain_not_found = "AMS1048";
/*  74 */   public static final String mju_error_keystore_certificate_entry_verify = "AMS1049";
/*  75 */   public static final String mju_error_keystore_privatekey_entry_verify = "AMS1050";
/*  76 */   public static final String mju_error_keystore_init_failed = "AMS1051";
/*  77 */   public static final String mju_keystore_password_protection_failure = "AMS1052";
/*  78 */   public static final String mju_keystore_password_unprotection_failure = "AMS1053";
/*  79 */   public static final String mju_policy_failed_to_get_receiver_certs = "AMS1054";
/*  80 */   public static final String mju_policy_error_get_receiver_certs = "AMS1055";
/*  81 */   public static final String mju_ambi_header_invalid = "AMS1056";
/*  82 */   public static final String mju_ambi_header_convert_error = "AMS1057";
/*  83 */   public static final String mju_ambi_header_convert_error_EseMQException = "AMS1058";
/*  84 */   public static final String mju_ambi_header_io_error = "AMS1059";
/*  85 */   public static final String mju_policy_failed_to_create_ambix500Name_object = "AMS1060";
/*  86 */   public static final String mju_credential_alias_not_found_keystore = "AMS1061";
/*  87 */   public static final String mju_credential_alias_not_found_keystore_MissingCertificateException = "AMS1062";
/*  88 */   public static final String mju_credential_alias_not_key_entry = "AMS1063";
/*  89 */   public static final String mju_keystore_password_not_ascii = "AMS1064";
/*  90 */   public static final String mju_wrong_key = "AMS1065";
/*  91 */   public static final String mju_pkcs11_keystore_init = "AMS1066";
/*  92 */   public static final String mqo_s_usermap_error_parsing_config_file = "AMS1100";
/*  93 */   public static final String mqo_s_usermap_error_duplicate_key = "AMS1101";
/*  94 */   public static final String mqo_s_usermap_error_relative_path = "AMS1102";
/*  95 */   public static final String mjp_msg_error_invalid_quality_of_protection = "AMS1120";
/*  96 */   public static final String mjp_msg_error_invalid_quality_of_protection_IllegalProtectionTypeException = "AMS1121";
/*  97 */   public static final String mjp_msg_error_invalid_encryption_algorithm = "AMS1122";
/*  98 */   public static final String mjp_msg_error_invalid_signature_algorithm = "AMS1123";
/*  99 */   public static final String mjp_msg_error_invalid_signature_algorithm_IllegalAlgorithmNameException = "AMS1124";
/* 100 */   public static final String mjp_msg_error_msg_protection_failed = "AMS1125";
/* 101 */   public static final String mjp_msg_error_msg_protection_failed_IllegalAlgorithmNameException = "AMS1126";
/* 102 */   public static final String mjp_msg_error_invalid_amount_of_sender_certificate = "AMS1127";
/* 103 */   public static final String mjp_msg_error_sender_certificate_not_found = "AMS1128";
/* 104 */   public static final String mjp_msg_error_invalid_protected_message_type = "AMS1129";
/* 105 */   public static final String mjp_msg_error_msg_unprotection_failed = "AMS1130";
/* 106 */   public static final String mjp_msg_error_invalid_recipients = "AMS1131";
/* 107 */   public static final String mjp_msg_error_msg_sender_cert_not_valid = "AMS1132";
/* 108 */   public static final String mjp_msg_error_msg_sender_cert_not_valid_InvalidCertificateException = "AMS1133";
/* 109 */   public static final String mjp_msg_error_msg_recipient_cert_not_valid = "AMS1134";
/* 110 */   public static final String mjp_msg_error_qop_mismatch = "AMS1135";
/* 111 */   public static final String mjp_msg_error_msg_decrytion_error = "AMS1136";
/* 112 */   public static final String mjp_msg_error_failed_to_verify_msg_signature = "AMS1137";
/* 113 */   public static final String mjp_msg_error_failed_to_verify_cert_chain = "AMS1138";
/* 114 */   public static final String mjp_msg_error_encryption_strength_mismatch = "AMS1139"; public static final String mjp_certvalid_error_cert_not_valid_yet; public static final String mjp_certvalid_error_cert_expired; public static final String mjp_certvalid_error_checking_cert_validity; public static final String mjp_certvalid_error_cert_keyusage_not_match; public static final String mjp_msg_error_getting_algname_from_pkcs7; public static final String mjp_msg_error_getting_no_recipient_cert; public static final String mjp_msg_error_getting_no_recipient_cert_MissingCertificateException;
/* 115 */   public static final String mjp_msg_error_user_not_in_recipient = "AMS1140"; public static final String mjp_msg_error_getting_sig_algname_from_pkcs7; public static final String mjp_certvalid_error_cert_no_keyusage_bit_matches; public static final String mjp_msg_error_verify_crl_signature; public static final String mjp_certvalid_error_crl_not_found; public static final String mjp_certvalid_error_crl_failed_to_retrieve; public static final String mjp_certvalid_ca_used_as_ee; public static final String mjp_certvalid_cert_revoked;
/* 116 */   public static final String mjp_msg_error_invalid_key_size = "AMS1141"; public static final String mjp_MessageProtectionBCImpl_not_loaded; public static final String mjp_CertAccessImpl_not_loaded1; public static final String mjp_CertAccessImpl_not_loaded2; public static final String mjp_failed_to_load_BC_dependant_class; public static final String mjp_failed_to_load_BC_dependant_class_zos; public static final String mqm_s_conn_cant_save_qmname; public static final String mqm_s_open_resolve_qname_err; public static final String mqm_s_open_real_open_error; public static final String mqm_s_open_hobj_close; public static final String mqm_s_open_protect_sign_alg_failed; public static final String mqm_s_put_env_not_found; static {
/* 117 */     mjp_certvalid_error_cert_not_valid_yet = "AMS1200";
/* 118 */     mjp_certvalid_error_cert_expired = "AMS1201";
/* 119 */     mjp_certvalid_error_checking_cert_validity = "AMS1202";
/* 120 */     mjp_certvalid_error_cert_keyusage_not_match = "AMS1203";
/* 121 */     mjp_msg_error_getting_algname_from_pkcs7 = "AMS1204";
/* 122 */     mjp_msg_error_getting_no_recipient_cert = "AMS1205";
/* 123 */     mjp_msg_error_getting_no_recipient_cert_MissingCertificateException = "AMS1206";
/* 124 */     mjp_msg_error_getting_sig_algname_from_pkcs7 = "AMS1207";
/* 125 */     mjp_certvalid_error_cert_no_keyusage_bit_matches = "AMS1208";
/* 126 */     mjp_msg_error_verify_crl_signature = "AMS1209";
/* 127 */     mjp_certvalid_error_crl_not_found = "AMS1210";
/* 128 */     mjp_certvalid_error_crl_failed_to_retrieve = "AMS1211";
/* 129 */     mjp_certvalid_ca_used_as_ee = "AMS1212";
/* 130 */     mjp_certvalid_cert_revoked = "AMS1213";
/* 131 */     mqm_s_conn_cant_save_qmname = "AMS1300";
/* 132 */     mqm_s_open_resolve_qname_err = "AMS1310";
/* 133 */     mqm_s_open_real_open_error = "AMS1311";
/* 134 */     mqm_s_open_hobj_close = "AMS1312";
/* 135 */     mqm_s_open_protect_sign_alg_failed = "AMS1313";
/* 136 */     mqm_s_put_env_not_found = "AMS1325";
/* 137 */     mqm_s_put_could_not_find_local_ccsid = "AMS1326";
/* 138 */     mqm_s_put_invalid_qop = "AMS1327";
/* 139 */     mqm_s_put_plain = "AMS1328";
/* 140 */     mqm_s_put_sign = "AMS1329";
/* 141 */     mqm_s_put_seal = "AMS1330";
/* 142 */     mqm_s_put_could_not_inquire_qmgr_property = "AMS1331";
/* 143 */     mqm_s_get_cant_open_qinfo = "AMS1340";
/* 144 */     mqm_s_get_ivmqhdr_ok = "AMS1341";
/* 145 */     mqm_s_get_ivmqhdr_invalid = "AMS1342";
/* 146 */     mqm_s_get_qop_mismatch = "AMS1343";
/* 147 */     mqm_s_get_sign = "AMS1344";
/* 148 */     mqm_s_get_seal = "AMS1345";
/* 149 */     mqm_s_get_invalid_protection = "AMS1346";
/* 150 */     mqm_s_get_error_q_ok = "AMS1347";
/* 151 */     mqm_s_get_error_q_failed = "AMS1348";
/* 152 */     mqm_s_get_data_conversion_failed = "AMS1349";
/* 153 */     mqm_s_get_retrieve_msg_failed = "AMS1350";
/* 154 */     mqm_s_get_unprotect_size_mismatch = "AMS1351";
/* 155 */     mqm_s_get_unprotect_qop_mismatch = "AMS1352";
/* 156 */     mqm_s_get_qop_acceptable_mismatch = "AMS1353";
/* 157 */     mqm_s_get_signer_dn_mismatch = "AMS1354";
/* 158 */     mqm_s_get_dlq_failed_mqget = "AMS1355";
/* 159 */     mqm_s_get_converted_msg_too_big = "AMS1356";
/* 160 */     mjp_MessageProtectionBCImpl_not_loaded = "AMS1357";
/* 161 */     mjp_CertAccessImpl_not_loaded1 = "AMS1358";
/* 162 */     mjp_CertAccessImpl_not_loaded2 = "AMS1359";
/* 163 */     mjp_failed_to_load_BC_dependant_class = "AMS1360";
/* 164 */     mjp_failed_to_load_BC_dependant_class_zos = "AMS1361";
/* 165 */     mjp_bad_initial_keyfile = "AMS1362";
/* 166 */     mjp_password_decryption_failure = "AMS1363";
/* 167 */     mjp_password_incorrect_format = "AMS1364";
/* 168 */     mju_keystore_password_protection_failure_new = "AMS1365";
/* 169 */     mju_config_already_protected = "AMS1366";
/* 170 */     mju_password_protection_unexpected_error = "AMS1367";
/* 171 */     mju_password_protection_complete = "AMS1368";
/* 172 */     mju_default_key_used = "AMS1369";
/* 173 */     mju_plain_passwords = "AMS1370";
/* 174 */     mju_incorrect_keystore_type_password_protection = "AMS1371";
/* 175 */     mju_password_prompt = "AMS1372";
/* 176 */     mju_initial_key_prompt = "AMS1373";
/* 177 */     mju_keyfile_used_runamscred = "AMS1374";
/*     */     
/* 179 */     if (Trace.isOn) {
/* 180 */       Trace.entry(AmsErrorMessages.class.getCanonicalName(), "static()");
/*     */     }
/*     */     
/* 183 */     if (Trace.isOn) {
/* 184 */       Trace.exit("com.ibm.mq.ese.nls.AmsErrorMessages", "static()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String mqm_s_put_could_not_find_local_ccsid;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String mqm_s_put_invalid_qop;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String mqm_s_put_plain;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String mqm_s_put_sign;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String mqm_s_put_seal;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String mqm_s_put_could_not_inquire_qmgr_property;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String mqm_s_get_cant_open_qinfo;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String mqm_s_get_ivmqhdr_ok;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String mqm_s_get_ivmqhdr_invalid;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String mqm_s_get_qop_mismatch;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String mqm_s_get_sign;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String mqm_s_get_seal;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String mqm_s_get_invalid_protection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String mqm_s_get_error_q_ok;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String mqm_s_get_error_q_failed;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String mqm_s_get_data_conversion_failed;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String mqm_s_get_retrieve_msg_failed;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String mqm_s_get_unprotect_size_mismatch;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String mqm_s_get_unprotect_qop_mismatch;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String mqm_s_get_qop_acceptable_mismatch;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String mqm_s_get_signer_dn_mismatch;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String mqm_s_get_dlq_failed_mqget;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String mqm_s_get_converted_msg_too_big;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String mjp_bad_initial_keyfile;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String mjp_password_decryption_failure;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String mjp_password_incorrect_format;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String mju_keystore_password_protection_failure_new;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String mju_config_already_protected;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String mju_password_protection_unexpected_error;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String mju_password_protection_complete;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String mju_default_key_used;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String mju_plain_passwords;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String mju_incorrect_keystore_type_password_protection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String mju_password_prompt;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String mju_initial_key_prompt;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String mju_keyfile_used_runamscred;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void logException(String CLASS, String method, Throwable t) {
/* 437 */     if (Trace.isOn) {
/* 438 */       Trace.entry("com.ibm.mq.ese.nls.AmsErrorMessages", "logException(String,String,Throwable)", new Object[] { CLASS, method, t });
/*     */     }
/*     */     
/* 441 */     Log.logNLS(CLASS, method, t.toString());
/* 442 */     if (Trace.isOn) {
/* 443 */       Trace.exit("com.ibm.mq.ese.nls.AmsErrorMessages", "logException(String,String,Throwable)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void logProtectionException(String CLASS, String method, Throwable t) {
/* 454 */     if (Trace.isOn) {
/* 455 */       Trace.entry("com.ibm.mq.ese.nls.AmsErrorMessages", "logProtectionException(String,String,Throwable)", new Object[] { CLASS, method, t });
/*     */     }
/*     */     
/* 458 */     for (Throwable t1 = t; t1 != null; t1 = t1.getCause()) {
/* 459 */       logException(CLASS, method, t1);
/*     */     }
/* 461 */     if (Trace.isOn) {
/* 462 */       Trace.exit("com.ibm.mq.ese.nls.AmsErrorMessages", "logProtectionException(String,String,Throwable)");
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
/*     */   public static void log(String CLASS, String methodSignature, String key, HashMap<String, ? extends Object> inserts) {
/* 475 */     if (Trace.isOn) {
/* 476 */       Trace.entry("com.ibm.mq.ese.nls.AmsErrorMessages", "log(String,String,String,HashMap<String , ? extends Object>)", new Object[] { CLASS, methodSignature, key, inserts });
/*     */     }
/*     */ 
/*     */     
/* 480 */     Log.log(CLASS, methodSignature, key, inserts);
/* 481 */     if (Trace.isOn) {
/* 482 */       Trace.exit("com.ibm.mq.ese.nls.AmsErrorMessages", "log(String,String,String,HashMap<String , ? extends Object>)");
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
/*     */   public static void log(String CLASS, String methodSignature, String key) {
/* 494 */     if (Trace.isOn) {
/* 495 */       Trace.entry("com.ibm.mq.ese.nls.AmsErrorMessages", "log(String,String,String)", new Object[] { CLASS, methodSignature, key });
/*     */     }
/*     */     
/* 498 */     Log.log(CLASS, methodSignature, key, null);
/* 499 */     if (Trace.isOn)
/* 500 */       Trace.exit("com.ibm.mq.ese.nls.AmsErrorMessages", "log(String,String,String)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\nls\AmsErrorMessages.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */