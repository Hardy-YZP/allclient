/*     */ package com.ibm.msg.client.commonservices.util;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.Log.Log;
/*     */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*     */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.InvalidClassException;
/*     */ import java.io.PrintStream;
/*     */ import java.io.Writer;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedActionException;
/*     */ import java.security.PrivilegedExceptionAction;
/*     */ import java.util.EnumSet;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
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
/*     */ public enum SerializationValidator
/*     */ {
/*  64 */   THE_INSTANCE;
/*     */   private final String MY_CLASS_NAME = SerializationValidator.class.getCanonicalName(); private InvalidClassException initializationException = null; public static SerializationValidator getInstance() throws InvalidClassException { if (Trace.isOn)
/*     */       Trace.entry(MY_CLASS_NAME_STATIC, "getInstance()");  if (THE_INSTANCE.initializationException != null) {
/*     */       if (Trace.isOn)
/*     */         Trace.throwing(MY_CLASS_NAME_STATIC, "getInstance()", THE_INSTANCE.initializationException);  throw THE_INSTANCE.initializationException;
/*     */     }  if (Trace.isOn)
/*  70 */       Trace.exit(MY_CLASS_NAME_STATIC, "getInstance()", THE_INSTANCE);  return THE_INSTANCE; } static { MY_CLASS_NAME_STATIC = SerializationValidator.class.getCanonicalName();
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
/* 594 */     primitiveClasses = new HashMap<>();
/*     */ 
/*     */     
/* 597 */     primitiveClasses.put("boolean", boolean.class);
/* 598 */     primitiveClasses.put("char", char.class);
/* 599 */     primitiveClasses.put("byte", byte.class);
/* 600 */     primitiveClasses.put("short", short.class);
/* 601 */     primitiveClasses.put("int", int.class);
/* 602 */     primitiveClasses.put("long", long.class);
/* 603 */     primitiveClasses.put("float", float.class);
/* 604 */     primitiveClasses.put("double", double.class);
/* 605 */     primitiveClasses.put("void", void.class);
/*     */ 
/*     */ 
/*     */     
/* 609 */     primitiveWrappers = new HashSet<>();
/*     */ 
/*     */     
/* 612 */     primitiveWrappers.add(Boolean.class.getCanonicalName());
/* 613 */     primitiveWrappers.add(Character.class.getCanonicalName());
/* 614 */     primitiveWrappers.add(Byte.class.getCanonicalName());
/* 615 */     primitiveWrappers.add(Short.class.getCanonicalName());
/* 616 */     primitiveWrappers.add(Integer.class.getCanonicalName());
/* 617 */     primitiveWrappers.add(Long.class.getCanonicalName());
/* 618 */     primitiveWrappers.add(Float.class.getCanonicalName());
/* 619 */     primitiveWrappers.add(Number.class.getCanonicalName());
/* 620 */     primitiveWrappers.add(Double.class.getCanonicalName());
/* 621 */     primitiveWrappers.add(String.class.getCanonicalName()); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private AllowList allowList = null;
/*     */   
/*     */   private Writer allowListWriter = null;
/*     */ 
/*     */   
/*     */   public void validateClassName(String className, Mode mode) throws InvalidClassException {
/* 632 */     if (Trace.isOn) {
/* 633 */       Trace.entry(this, this.MY_CLASS_NAME, "validateClassName(String,String)", new Object[] { className, mode });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 638 */     if (primitiveWrappers.contains(className)) {
/* 639 */       if (Trace.isOn) {
/* 640 */         Trace.exit(this, this.MY_CLASS_NAME, "validateClassName(String,String)", 0);
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/* 645 */     if (this.allowList != null && this.enforceModes
/* 646 */       .contains(mode) && 
/* 647 */       !this.allowList.contains(className)) {
/* 648 */       HashMap<String, String> inserts = new HashMap<>();
/* 649 */       inserts.put("XMSC_ALLOWLIST", this.allowListPropertyValue);
/*     */       
/* 651 */       InvalidClassException e = new InvalidClassException(className, NLSServices.getMessage("JMSCS0020", inserts));
/* 652 */       if (Trace.isOn) {
/* 653 */         Trace.throwing(this, this.MY_CLASS_NAME, "validateClassName(String,String)", e, 0);
/*     */       }
/* 655 */       throw e;
/*     */     } 
/*     */     
/* 658 */     if (this.allowListWriter != null) {
/* 659 */       String logEntry = String.format("%s # %s", new Object[] { className, mode.name() });
/* 660 */       if (!this.seenClasses.contains(logEntry)) {
/* 661 */         this.seenClasses.add(logEntry);
/*     */         try {
/* 663 */           this.allowListWriter.write(logEntry);
/* 664 */           this.allowListWriter.write(10);
/* 665 */           this.allowListWriter.flush();
/*     */         }
/* 667 */         catch (IOException e) {
/*     */           
/* 669 */           InvalidClassException e2 = (InvalidClassException)(new InvalidClassException(className, NLSServices.getMessage("JMSCS0024"))).initCause(e);
/* 670 */           if (Trace.isOn) {
/* 671 */             Trace.throwing(this, this.MY_CLASS_NAME, "validateClassName(String,String)", e2, 1);
/*     */           }
/* 673 */           throw e2;
/*     */         } 
/*     */       } 
/*     */     } 
/* 677 */     if (Trace.isOn)
/* 678 */       Trace.exit(this, this.MY_CLASS_NAME, "validateClassName(String,String)"); 
/*     */   }
/*     */   public enum Mode {
/*     */     SERIALIZE, DESERIALIZE; } private EnumSet<Mode> enforceModes = EnumSet.allOf(Mode.class); private static final String MY_CLASS_NAME_STATIC;
/*     */   private String allowListPropertyValue;
/*     */   private Set<String> seenClasses;
/*     */   private static HashMap<String, Class<?>> primitiveClasses;
/*     */   private static Set<String> primitiveWrappers;
/*     */   
/*     */   public static Class<?> getPrimitive(String className) {
/* 688 */     return primitiveClasses.get(className);
/*     */   }
/*     */   
/*     */   SerializationValidator() {
/*     */     if (Trace.isOn)
/*     */       Trace.entry(this.MY_CLASS_NAME, "<init>"); 
/*     */     try {
/*     */       boolean discover = getBooleanFromPropertyList(new String[] { "com.ibm.mq.jms.allowlist.discover", "com.ibm.mq.jms.whitelist.discover" });
/*     */       String[] allowListModePropertyNames = { "com.ibm.mq.jms.allowlist.mode", "com.ibm.mq.jms.whitelist.mode" };
/*     */       String mode = getStringFromPropertyList(allowListModePropertyNames);
/*     */       if (mode != null)
/*     */         switch (mode.toUpperCase()) {
/*     */           case "SERIALIZE":
/*     */             this.enforceModes.remove(Mode.DESERIALIZE);
/*     */             break;
/*     */           case "DESERIALIZE":
/*     */             this.enforceModes.remove(Mode.SERIALIZE);
/*     */             break;
/*     */         }  
/*     */       String[] allowListPropertyNames = { "com.ibm.mq.jms.allowlist", "com.ibm.mq.jms.whitelist" };
/*     */       this.allowListPropertyValue = getStringFromPropertyList(allowListPropertyNames);
/*     */       if (this.allowListPropertyValue == null) {
/*     */         Log.log(this, "<init>", "JMSCS0029", new HashMap<>());
/*     */         if (Trace.isOn)
/*     */           Trace.data(this, "<init>", "No allow list property - no action", null); 
/*     */       } else if (this.allowListPropertyValue.startsWith("file:")) {
/*     */         URI allowListUri = getUri(this.allowListPropertyValue);
/*     */         File allowListFile = getAllowListFile(allowListUri);
/*     */         if (discover) {
/*     */           intialiseForDiscover(allowListFile);
/*     */         } else {
/*     */           initializeForEnforceFromFile(allowListFile);
/*     */         } 
/*     */       } else {
/*     */         if (discover)
/*     */           rejectDiscoverWithNoFile(); 
/*     */         initializeForEnforcementFromList();
/*     */       } 
/*     */     } catch (InvalidClassException ice) {
/*     */       this.initializationException = ice;
/*     */     } 
/*     */     if (Trace.isOn)
/*     */       Trace.exit(this.MY_CLASS_NAME, "<init>"); 
/*     */   }
/*     */   
/*     */   private boolean getBooleanFromPropertyList(String[] propertyNames) {
/*     */     if (Trace.isOn)
/*     */       Trace.entry(this.MY_CLASS_NAME, "getBooleanFromPropertyList(String[])", (Object[])propertyNames); 
/*     */     boolean result = false;
/*     */     for (String propertyName : propertyNames) {
/*     */       PropertyStore.register(propertyName, false);
/*     */       if (PropertyStore.wasOverridden(propertyName, null)) {
/*     */         result = PropertyStore.getBooleanPropertyObject(propertyName).booleanValue();
/*     */         break;
/*     */       } 
/*     */     } 
/*     */     if (Trace.isOn)
/*     */       Trace.exit(this.MY_CLASS_NAME, "getBooleanFromPropertyList(String[])", Boolean.valueOf(result)); 
/*     */     return result;
/*     */   }
/*     */   
/*     */   private String getStringFromPropertyList(String[] propertyNames) {
/*     */     if (Trace.isOn)
/*     */       Trace.entry(this.MY_CLASS_NAME, "getStringFromPropertyList(String[])", (Object[])propertyNames); 
/*     */     String result = null;
/*     */     for (String propertyName : propertyNames) {
/*     */       PropertyStore.register(propertyName, false);
/*     */       if (PropertyStore.wasOverridden(propertyName, null)) {
/*     */         result = PropertyStore.getStringProperty(propertyName);
/*     */         break;
/*     */       } 
/*     */     } 
/*     */     if (Trace.isOn)
/*     */       Trace.exit(this.MY_CLASS_NAME, "getStringFromPropertyList(String[])", result); 
/*     */     return result;
/*     */   }
/*     */   
/*     */   private void initializeForEnforcementFromList() throws InvalidClassException {
/*     */     if (Trace.isOn)
/*     */       Trace.entry(this.MY_CLASS_NAME, "initializeForEnforcementFromList()"); 
/*     */     String[] allowListClassArray = this.allowListPropertyValue.split(",");
/*     */     try {
/*     */       this.allowList = new AllowList(allowListClassArray);
/*     */     } catch (IllegalArgumentException e) {
/*     */       HashMap<String, String> hashMap = new HashMap<>();
/*     */       hashMap.put("XMSC_ALLOWLIST", this.allowListPropertyValue);
/*     */       InvalidClassException failed = new InvalidClassException("", NLSServices.getMessage("JMSCS0023", hashMap));
/*     */       failed.initCause(e);
/*     */       if (Trace.isOn)
/*     */         Trace.throwing(this, this.MY_CLASS_NAME, "initializeForEnforcementFromList()", failed, 5); 
/*     */       throw failed;
/*     */     } 
/*     */     HashMap<String, String> inserts = new HashMap<>();
/*     */     inserts.put("XMSC_ALLOWLIST", this.allowListPropertyValue);
/*     */     Log.log(this, "<init>", "JMSCS0027", inserts);
/*     */     if (Trace.isOn)
/*     */       Trace.data(this, "initializeForEnforcementFromList()", "ENFORCEMENT mode - allow list set as list", this.allowListPropertyValue); 
/*     */     if (Trace.isOn)
/*     */       Trace.exit(this.MY_CLASS_NAME, "initializeForEnforcementFromList()"); 
/*     */   }
/*     */   
/*     */   private void rejectDiscoverWithNoFile() throws InvalidClassException {
/*     */     if (Trace.isOn)
/*     */       Trace.entry(this.MY_CLASS_NAME, "rejectDiscoverWithNoFile()"); 
/*     */     HashMap<String, String> inserts = new HashMap<>();
/*     */     inserts.put("XMSC_ALLOWLIST", this.allowListPropertyValue);
/*     */     InvalidClassException failed = new InvalidClassException("", NLSServices.getMessage("JMSCS0028", inserts));
/*     */     if (Trace.isOn)
/*     */       Trace.throwing(this, this.MY_CLASS_NAME, "rejectDiscoverWithNoFile()", failed, 4); 
/*     */     throw failed;
/*     */   }
/*     */   
/*     */   private void initializeForEnforceFromFile(File allowListFile) throws InvalidClassException {
/*     */     if (Trace.isOn)
/*     */       Trace.entry(this.MY_CLASS_NAME, "initializeForEnforceFromFile(File)", new Object[] { allowListFile }); 
/*     */     this.allowList = loadAllowList(allowListFile);
/*     */     ByteArrayOutputStream baos = new ByteArrayOutputStream();
/*     */     PrintStream ps = new PrintStream(baos);
/*     */     this.allowList.dump(ps);
/*     */     String contents = String.format("%n%s", new Object[] { baos.toString() });
/*     */     HashMap<String, String> inserts = new HashMap<>();
/*     */     inserts.put("XMSC_ALLOWLIST", this.allowListPropertyValue);
/*     */     inserts.put("XMSC_ALLOWLIST_CONTENTS", contents);
/*     */     Log.log(this, "<init>", "JMSCS0025", inserts);
/*     */     if (Trace.isOn)
/*     */       Trace.data(this, "initializeForEnforceFromFile(File)", "ENFORCEMENT mode - allow list read from file", this.allowListPropertyValue); 
/*     */     if (Trace.isOn)
/*     */       Trace.exit(this.MY_CLASS_NAME, "initializeForEnforceFromFile(File)"); 
/*     */   }
/*     */   
/*     */   private void intialiseForDiscover(File allowListFile) throws InvalidClassException {
/*     */     if (Trace.isOn)
/*     */       Trace.entry(this.MY_CLASS_NAME, "intialiseForDiscover(File)", new Object[] { allowListFile }); 
/*     */     this.allowListWriter = getAllowListWriter(allowListFile);
/*     */     this.seenClasses = new HashSet<>();
/*     */     HashMap<String, String> inserts = new HashMap<>();
/*     */     inserts.put("XMSC_ALLOWLIST", this.allowListPropertyValue);
/*     */     Log.log(this, "<init>", "JMSCS0026", inserts);
/*     */     if (Trace.isOn)
/*     */       Trace.data(this, "intialiseForDiscover()", "DISCOVERY mode - writing to file", this.allowListPropertyValue); 
/*     */     if (Trace.isOn)
/*     */       Trace.exit(this.MY_CLASS_NAME, "intialiseForDiscover(File)"); 
/*     */   }
/*     */   
/*     */   private AllowList loadAllowList(final File allowListFile) throws InvalidClassException {
/*     */     if (Trace.isOn)
/*     */       Trace.entry(this.MY_CLASS_NAME, "loadAllowList(File)", new Object[] { allowListFile }); 
/*     */     AllowList allowListResult = null;
/*     */     try {
/*     */       allowListResult = AccessController.<AllowList>doPrivileged(new PrivilegedExceptionAction<AllowList>() {
/*     */             public AllowList run() throws InvalidClassException {
/*     */               AllowList allowList1 = null;
/*     */               if (allowListFile.exists() && allowListFile.isFile()) {
/*     */                 allowList1 = new AllowList();
/*     */                 BufferedReader bir = null;
/*     */                 try {
/*     */                   bir = new BufferedReader(new FileReader(allowListFile));
/*     */                   allowList1.load(bir);
/*     */                 } catch (IOException e) {
/*     */                   HashMap<String, String> inserts = new HashMap<>();
/*     */                   inserts.put("XMSC_ALLOWLIST", SerializationValidator.this.allowListPropertyValue);
/*     */                   InvalidClassException failed = new InvalidClassException("", NLSServices.getMessage("JMSCS0021", inserts));
/*     */                   failed.initCause(e);
/*     */                   if (Trace.isOn)
/*     */                     Trace.throwing(SerializationValidator.this.MY_CLASS_NAME, "loadAllowList(File)", failed, 0); 
/*     */                   throw failed;
/*     */                 } catch (NullPointerException e) {
/*     */                   HashMap<String, String> inserts = new HashMap<>();
/*     */                   inserts.put("XMSC_ALLOWLIST", SerializationValidator.this.allowListPropertyValue);
/*     */                   InvalidClassException failed = new InvalidClassException("", NLSServices.getMessage("JMSCS0021", inserts));
/*     */                   failed.initCause(e);
/*     */                   if (Trace.isOn)
/*     */                     Trace.throwing(SerializationValidator.this.MY_CLASS_NAME, "loadAllowList(File)", failed, 1); 
/*     */                   throw failed;
/*     */                 } catch (IllegalArgumentException e) {
/*     */                   HashMap<String, String> inserts = new HashMap<>();
/*     */                   inserts.put("XMSC_ALLOWLIST", SerializationValidator.this.allowListPropertyValue);
/*     */                   InvalidClassException failed = new InvalidClassException("", NLSServices.getMessage("JMSCS0023", inserts));
/*     */                   failed.initCause(e);
/*     */                   if (Trace.isOn)
/*     */                     Trace.throwing(SerializationValidator.this.MY_CLASS_NAME, "loadAllowList(File)", failed, 2); 
/*     */                   throw failed;
/*     */                 } finally {
/*     */                   if (bir != null)
/*     */                     try {
/*     */                       bir.close();
/*     */                     } catch (IOException iOException) {} 
/*     */                 } 
/*     */               } else {
/*     */                 HashMap<String, String> inserts = new HashMap<>();
/*     */                 inserts.put("XMSC_ALLOWLIST", SerializationValidator.this.allowListPropertyValue);
/*     */                 InvalidClassException failed = new InvalidClassException("", NLSServices.getMessage("JMSCS0021", inserts));
/*     */                 if (Trace.isOn)
/*     */                   Trace.throwing(SerializationValidator.this.MY_CLASS_NAME, "loadAllowList(File)", failed, 3); 
/*     */                 throw failed;
/*     */               } 
/*     */               return allowList1;
/*     */             }
/*     */           });
/*     */     } catch (PrivilegedActionException e) {
/*     */       throw (InvalidClassException)e.getException();
/*     */     } 
/*     */     if (Trace.isOn)
/*     */       Trace.exit(this.MY_CLASS_NAME, "loadAllowList(File)", allowListResult); 
/*     */     return allowListResult;
/*     */   }
/*     */   
/*     */   private Writer getAllowListWriter(final File allowListFile) throws InvalidClassException {
/*     */     if (Trace.isOn)
/*     */       Trace.entry(this.MY_CLASS_NAME, "getAllowListWriter(File)", new Object[] { allowListFile }); 
/*     */     Writer allowListWriterResult = null;
/*     */     try {
/*     */       allowListWriterResult = AccessController.<Writer>doPrivileged(new PrivilegedExceptionAction<Writer>() {
/*     */             public Writer run() throws InvalidClassException {
/*     */               if (allowListFile.exists()) {
/*     */                 HashMap<String, String> inserts = new HashMap<>();
/*     */                 inserts.put("XMSC_ALLOWLIST", SerializationValidator.this.allowListPropertyValue);
/*     */                 InvalidClassException failed = new InvalidClassException("", NLSServices.getMessage("JMSCS0022", inserts));
/*     */                 if (Trace.isOn)
/*     */                   Trace.throwing(SerializationValidator.this.MY_CLASS_NAME, "getAllowListWriter(File)", failed, 0); 
/*     */                 throw failed;
/*     */               } 
/*     */               try {
/*     */                 return new BufferedWriter(new FileWriter(allowListFile));
/*     */               } catch (IOException e) {
/*     */                 HashMap<String, String> inserts = new HashMap<>();
/*     */                 inserts.put("XMSC_ALLOWLIST", SerializationValidator.this.allowListPropertyValue);
/*     */                 InvalidClassException failed = new InvalidClassException("", NLSServices.getMessage("JMSCS0021", inserts));
/*     */                 failed.initCause(e);
/*     */                 if (Trace.isOn)
/*     */                   Trace.throwing(SerializationValidator.this.MY_CLASS_NAME, "getAllowListWriter(File)", failed, 0); 
/*     */                 throw failed;
/*     */               } 
/*     */             }
/*     */           });
/*     */     } catch (PrivilegedActionException e) {
/*     */       throw (InvalidClassException)e.getException();
/*     */     } 
/*     */     if (Trace.isOn)
/*     */       Trace.exit(this.MY_CLASS_NAME, "getAllowListWriter(File)", allowListWriterResult); 
/*     */     return allowListWriterResult;
/*     */   }
/*     */   
/*     */   private File getAllowListFile(URI allowListUri) throws InvalidClassException {
/*     */     if (Trace.isOn)
/*     */       Trace.entry(this.MY_CLASS_NAME, "getAllowListFile(URI)", new Object[] { allowListUri }); 
/*     */     File result = null;
/*     */     try {
/*     */       result = new File(allowListUri);
/*     */     } catch (IllegalArgumentException e) {
/*     */       HashMap<String, String> inserts = new HashMap<>();
/*     */       inserts.put("XMSC_ALLOWLIST", this.allowListPropertyValue);
/*     */       InvalidClassException failed = new InvalidClassException("", NLSServices.getMessage("JMSCS0021", inserts));
/*     */       failed.initCause(e);
/*     */       if (Trace.isOn)
/*     */         Trace.throwing(this, this.MY_CLASS_NAME, "getAllowListFile(URI)", failed, 1); 
/*     */       throw failed;
/*     */     } 
/*     */     if (Trace.isOn)
/*     */       Trace.exit(this.MY_CLASS_NAME, "getAllowListFile(URI)", result); 
/*     */     return result;
/*     */   }
/*     */   
/*     */   private URI getUri(String allowListPropertyValueParam) throws InvalidClassException {
/*     */     if (Trace.isOn)
/*     */       Trace.entry(this.MY_CLASS_NAME, "getUri(String)", new Object[] { allowListPropertyValueParam }); 
/*     */     URI result = null;
/*     */     try {
/*     */       result = new URI(allowListPropertyValueParam);
/*     */     } catch (URISyntaxException e) {
/*     */       HashMap<String, String> inserts = new HashMap<>();
/*     */       inserts.put("XMSC_ALLOWLIST", allowListPropertyValueParam);
/*     */       InvalidClassException failed = new InvalidClassException("", NLSServices.getMessage("JMSCS0021", inserts));
/*     */       failed.initCause(e);
/*     */       if (Trace.isOn)
/*     */         Trace.throwing(this, this.MY_CLASS_NAME, "getUri(String)", failed, 0); 
/*     */       throw failed;
/*     */     } 
/*     */     if (Trace.isOn)
/*     */       Trace.exit(this.MY_CLASS_NAME, "getUri(String)", result); 
/*     */     return result;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservice\\util\SerializationValidator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */