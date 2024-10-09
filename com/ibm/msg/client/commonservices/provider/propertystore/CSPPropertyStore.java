/*     */ package com.ibm.msg.client.commonservices.provider.propertystore;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.propertystore.PropertiesException;
/*     */ import com.ibm.msg.client.commonservices.propertystore.PropertiesFileException;
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
/*     */ 
/*     */ public interface CSPPropertyStore
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/provider/propertystore/CSPPropertyStore.java";
/*     */   
/*     */   String getStringProperty(String paramString);
/*     */   
/*     */   long getLongProperty(String paramString);
/*     */   
/*     */   boolean getBooleanProperty(String paramString);
/*     */   
/*     */   Object getObjectProperty(String paramString);
/*     */   
/*     */   void addPropertiesFile(String paramString1, String paramString2, boolean paramBoolean) throws PropertiesFileException;
/*     */   
/*     */   void set(String paramString1, String paramString2);
/*     */   
/*     */   void set(String paramString, Object paramObject);
/*     */   
/*     */   void set(String paramString, Object paramObject, PropertySource paramPropertySource);
/*     */   
/*     */   HashMap<String, Object> getAll();
/*     */   
/*     */   void register(String paramString1, String paramString2, boolean paramBoolean);
/*     */   
/*     */   void register(String paramString, boolean paramBoolean1, boolean paramBoolean2);
/*     */   
/*     */   void register(String paramString, long paramLong, Long paramLong1, Long paramLong2, boolean paramBoolean);
/*     */   
/*     */   void register(String paramString, Object paramObject, boolean paramBoolean);
/*     */   
/*     */   @Deprecated
/*     */   boolean wasOverridden(String paramString, StringBuffer paramStringBuffer);
/*     */   
/*     */   PropertySource howHasPropertyBeenSet(String paramString) throws PropertiesException;
/*     */   
/*     */   public enum PropertySource
/*     */   {
/* 189 */     SYS_PROP, CONFIG_FILE, CONFIG_URL, API, API_ADMIN, UNKNOWN_PROP, REGISTERED_ONLY;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\provider\propertystore\CSPPropertyStore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */