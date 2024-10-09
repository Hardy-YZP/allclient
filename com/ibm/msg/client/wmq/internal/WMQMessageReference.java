/*     */ package com.ibm.msg.client.wmq.internal;
/*     */ 
/*     */ import com.ibm.mq.jmqi.internal.JmqiTools;
/*     */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.provider.ProviderDestination;
/*     */ import com.ibm.msg.client.provider.ProviderMessage;
/*     */ import com.ibm.msg.client.provider.ProviderMessageReference;
/*     */ import com.ibm.msg.client.wmq.common.internal.WMQDestination;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import javax.jms.JMSException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WMQMessageReference
/*     */   implements ProviderMessageReference
/*     */ {
/*     */   private static final String ASCII = "ASCII";
/*     */   private static final String EYECATCHER = "MQMR";
/*     */   private static final int MSGREF_VERSION_4 = 4;
/*     */   private static final long prime1 = 2147483587L;
/*     */   private static final long prime2 = 2147483647L;
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq/src/com/ibm/msg/client/wmq/internal/WMQMessageReference.java";
/*     */   private WMQDestination destination;
/*     */   private String uriDestinationReadFrom;
/*     */   
/*     */   static {
/*  79 */     if (Trace.isOn) {
/*  80 */       Trace.data("com.ibm.msg.client.wmq.internal.WMQMessageReference", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq/src/com/ibm/msg/client/wmq/internal/WMQMessageReference.java");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 121 */   private String fqSubName = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 126 */   private int hashcode = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 131 */   private ProviderMessage message = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 137 */   private int msgLength = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private byte[] token;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 147 */   private String managedQName = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 152 */   private String qMgrName = null;
/*     */ 
/*     */ 
/*     */   
/*     */   private byte[] subID;
/*     */ 
/*     */   
/* 159 */   private int dataQuantity = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   WMQMessageReference(byte[] flattened, ProviderDestination dest) throws JMSException {
/* 169 */     if (Trace.isOn) {
/* 170 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQMessageReference", "<init>(byte [ ],ProviderDestination)", new Object[] { flattened, dest });
/*     */     }
/*     */     
/*     */     try {
/* 174 */       ByteArrayInputStream bais = new ByteArrayInputStream(flattened);
/* 175 */       DataInputStream dis = new DataInputStream(bais);
/* 176 */       checkEyeCatcherAndVersion(dis);
/*     */       
/* 178 */       this.uriDestinationReadFrom = reinflateString(dis);
/* 179 */       this.destination = (WMQDestination)dest;
/*     */       
/* 181 */       this.token = reinflateBytes(dis);
/* 182 */       this.msgLength = dis.readInt();
/* 183 */       this.fqSubName = reinflateString(dis);
/* 184 */       this.managedQName = reinflateString(dis);
/* 185 */       this.qMgrName = reinflateString(dis);
/* 186 */       this.subID = reinflateBytes(dis);
/*     */       
/* 188 */       dis.close();
/* 189 */       bais.close();
/* 190 */       calculateHashCode();
/*     */     }
/* 192 */     catch (IOException ioe) {
/* 193 */       if (Trace.isOn) {
/* 194 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQMessageReference", "<init>(byte [ ],ProviderDestination)", ioe);
/*     */       }
/*     */       
/* 197 */       JMSException je = (JMSException)NLSServices.createException("JMSWMQ1096", null);
/* 198 */       je.setLinkedException(ioe);
/* 199 */       if (Trace.isOn) {
/* 200 */         Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQMessageReference", "<init>(byte [ ],ProviderDestination)", (Throwable)je);
/*     */       }
/*     */       
/* 203 */       throw je;
/*     */     } 
/* 205 */     if (Trace.isOn) {
/* 206 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQMessageReference", "<init>(byte [ ],ProviderDestination)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void checkEyeCatcherAndVersion(DataInputStream dis) throws IOException {
/* 213 */     if (Trace.isOn) {
/* 214 */       Trace.entry("com.ibm.msg.client.wmq.internal.WMQMessageReference", "checkEyeCatcherAndVersion(DataInputStream)", new Object[] { dis });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 219 */     byte[] eyeBytes = new byte[4];
/* 220 */     dis.readFully(eyeBytes);
/* 221 */     String eyecatcher = new String(eyeBytes, "ASCII");
/* 222 */     if (!"MQMR".equals(eyecatcher)) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 227 */       HashMap<String, Object> info = new HashMap<>();
/* 228 */       info.put("eyecatcher", eyecatcher);
/* 229 */       Trace.ffst("WMQMessageReference", "checkEyeCatcherAndVersion", "XN00F005", info, IOException.class);
/*     */     } 
/*     */     
/* 232 */     int version = dis.readInt();
/* 233 */     if (4 != version) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 238 */       HashMap<String, Object> info = new HashMap<>();
/* 239 */       info.put("version", Integer.valueOf(version));
/* 240 */       Trace.ffst("WMQMessageReference", "checkEyeCatcherAndVersion", "XN00F006", info, IOException.class);
/*     */     } 
/* 242 */     if (Trace.isOn) {
/* 243 */       Trace.exit("com.ibm.msg.client.wmq.internal.WMQMessageReference", "checkEyeCatcherAndVersion(DataInputStream)");
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
/*     */   static String getDestinationURI(byte[] flattened) throws JMSException {
/* 260 */     if (Trace.isOn) {
/* 261 */       Trace.entry("com.ibm.msg.client.wmq.internal.WMQMessageReference", "getDestinationURI(byte [ ])", new Object[] { flattened });
/*     */     }
/*     */     
/*     */     try {
/* 265 */       ByteArrayInputStream bais = new ByteArrayInputStream(flattened);
/* 266 */       DataInputStream dis = new DataInputStream(bais);
/*     */       
/* 268 */       checkEyeCatcherAndVersion(dis);
/* 269 */       String destUri = reinflateString(dis);
/*     */       
/* 271 */       dis.close();
/* 272 */       bais.close();
/* 273 */       if (Trace.isOn) {
/* 274 */         Trace.exit("com.ibm.msg.client.wmq.internal.WMQMessageReference", "getDestinationURI(byte [ ])", destUri);
/*     */       }
/*     */       
/* 277 */       return destUri;
/*     */     }
/* 279 */     catch (IOException ioe) {
/* 280 */       if (Trace.isOn) {
/* 281 */         Trace.catchBlock("com.ibm.msg.client.wmq.internal.WMQMessageReference", "getDestinationURI(byte [ ])", ioe);
/*     */       }
/*     */       
/* 284 */       JMSException je = (JMSException)NLSServices.createException("JMSWMQ1096", null);
/* 285 */       je.setLinkedException(ioe);
/* 286 */       if (Trace.isOn) {
/* 287 */         Trace.throwing("com.ibm.msg.client.wmq.internal.WMQMessageReference", "getDestinationURI(byte [ ])", (Throwable)je);
/*     */       }
/*     */       
/* 290 */       throw je;
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
/*     */   WMQMessageReference(byte[] token, WMQDestination destination, int msgLength, byte[] subID) {
/* 303 */     this(token, destination, null, null, null, msgLength, subID);
/* 304 */     if (Trace.isOn) {
/* 305 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQMessageReference", "<init>(byte [ ],WMQDestination,int,byte [ ])", new Object[] { token, destination, 
/*     */             
/* 307 */             Integer.valueOf(msgLength), subID });
/*     */     }
/*     */     
/* 310 */     if (Trace.isOn) {
/* 311 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQMessageReference", "<init>(byte [ ],WMQDestination,int,byte [ ])");
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
/*     */   WMQMessageReference(byte[] token, WMQDestination destination, String managedQName, String qMgrName, int msgLength, byte[] subID) {
/* 328 */     this(token, destination, managedQName, qMgrName, null, msgLength, subID);
/* 329 */     if (Trace.isOn) {
/* 330 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQMessageReference", "<init>(byte [ ],WMQDestination,String,String,int,byte [ ])", new Object[] { token, destination, managedQName, qMgrName, 
/*     */             
/* 332 */             Integer.valueOf(msgLength), subID });
/*     */     }
/*     */     
/* 335 */     if (Trace.isOn) {
/* 336 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQMessageReference", "<init>(byte [ ],WMQDestination,String,String,int,byte [ ])");
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
/*     */   
/*     */   WMQMessageReference(byte[] token, WMQDestination destination, String managedQName, String qMgrName, String fqSubName, int msgLength, byte[] subID) {
/* 354 */     if (Trace.isOn) {
/* 355 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQMessageReference", "<init>(byte [ ],WMQDestination,String,String,String,int,byte [ ])", new Object[] { token, destination, managedQName, qMgrName, fqSubName, 
/*     */             
/* 357 */             Integer.valueOf(msgLength), subID });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 362 */     this.token = new byte[token.length];
/* 363 */     System.arraycopy(token, 0, this.token, 0, token.length);
/*     */     
/* 365 */     if (subID != null) {
/* 366 */       this.subID = new byte[subID.length];
/* 367 */       System.arraycopy(subID, 0, this.subID, 0, subID.length);
/*     */     } 
/*     */     
/* 370 */     this.destination = destination;
/* 371 */     this.fqSubName = fqSubName;
/* 372 */     this.msgLength = msgLength;
/* 373 */     this.managedQName = managedQName;
/* 374 */     this.qMgrName = qMgrName;
/*     */     try {
/* 376 */       this.uriDestinationReadFrom = destination.toURI();
/*     */     }
/* 378 */     catch (JMSException e) {
/* 379 */       if (Trace.isOn) {
/* 380 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQMessageReference", "<init>(byte [ ],WMQDestination,String,String,String,int,byte [ ])", (Throwable)e);
/*     */       }
/*     */       
/* 383 */       HashMap<String, JMSException> data = new HashMap<>();
/* 384 */       data.put("Exception", e);
/* 385 */       Trace.ffst(this, "<init>", "XN00F007", data, null);
/*     */     } 
/*     */     
/* 388 */     calculateHashCode();
/*     */     
/* 390 */     if (Trace.isOn) {
/* 391 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQMessageReference", "<init>(byte [ ],WMQDestination,String,String,String,int,byte [ ])");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void calculateHashCode() {
/* 398 */     if (Trace.isOn) {
/* 399 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQMessageReference", "calculateHashCode()");
/*     */     }
/*     */     
/* 402 */     this.hashcode = 0;
/* 403 */     byte[] temp = new byte[8];
/* 404 */     for (int i = 0; i + 8 <= this.token.length; i += 8) {
/* 405 */       System.arraycopy(this.token, i, temp, 0, 8);
/* 406 */       this.hashcode ^= hash8bytes(temp);
/*     */     } 
/* 408 */     if (Trace.isOn) {
/* 409 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQMessageReference", "calculateHashCode()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() {
/* 420 */     if (Trace.isOn) {
/* 421 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQMessageReference", "clone()");
/*     */     }
/*     */     
/* 424 */     WMQMessageReference clone = null;
/*     */     
/*     */     try {
/* 427 */       clone = (WMQMessageReference)super.clone();
/*     */ 
/*     */ 
/*     */       
/* 431 */       clone.token = new byte[this.token.length];
/* 432 */       System.arraycopy(this.token, 0, clone.token, 0, this.token.length);
/*     */ 
/*     */       
/* 435 */       if (this.subID != null) {
/* 436 */         clone.subID = new byte[this.subID.length];
/* 437 */         System.arraycopy(this.subID, 0, clone.subID, 0, this.subID.length);
/*     */       }
/*     */     
/* 440 */     } catch (CloneNotSupportedException e) {
/* 441 */       if (Trace.isOn) {
/* 442 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQMessageReference", "clone()", e);
/*     */       }
/*     */ 
/*     */       
/* 446 */       HashMap<String, Object> data = new HashMap<>();
/* 447 */       data.put("exception", e);
/* 448 */       Trace.ffst(this, "clone()", "XN00F008", data, null);
/*     */     } 
/*     */     
/* 451 */     if (Trace.isOn) {
/* 452 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQMessageReference", "clone()", clone);
/*     */     }
/* 454 */     return clone;
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
/*     */   public boolean equals(Object other) {
/* 468 */     if (null == other || !(other instanceof WMQMessageReference)) {
/* 469 */       return false;
/*     */     }
/* 471 */     WMQMessageReference wmr = (WMQMessageReference)other;
/* 472 */     if (null == wmr.token || !Arrays.equals(this.token, wmr.token)) {
/* 473 */       return false;
/*     */     }
/* 475 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] flatten() throws JMSException {
/* 486 */     if (Trace.isOn) {
/* 487 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQMessageReference", "flatten()");
/*     */     }
/*     */     try {
/* 490 */       ByteArrayOutputStream baos = new ByteArrayOutputStream();
/*     */       
/* 492 */       DataOutputStream dos = new DataOutputStream(baos);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 497 */       dos.write("MQMR".getBytes("ASCII"));
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 502 */       dos.writeInt(4);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 513 */       flattenString(dos, this.destination.toURI());
/* 514 */       flattenBytes(dos, this.token);
/* 515 */       dos.writeInt(this.msgLength);
/* 516 */       flattenString(dos, this.fqSubName);
/* 517 */       flattenString(dos, this.managedQName);
/* 518 */       flattenString(dos, this.qMgrName);
/* 519 */       flattenBytes(dos, this.subID);
/*     */       
/* 521 */       dos.flush();
/* 522 */       baos.flush();
/* 523 */       byte[] bytearray = baos.toByteArray();
/* 524 */       dos.close();
/* 525 */       baos.close();
/* 526 */       if (Trace.isOn) {
/* 527 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQMessageReference", "flatten()", bytearray);
/*     */       }
/*     */       
/* 530 */       return bytearray;
/*     */     }
/* 532 */     catch (IOException ioe) {
/* 533 */       if (Trace.isOn) {
/* 534 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQMessageReference", "flatten()", ioe);
/*     */       }
/*     */       
/* 537 */       JMSException je = (JMSException)NLSServices.createException("JMSWMQ1096", null);
/* 538 */       je.setLinkedException(ioe);
/* 539 */       if (Trace.isOn) {
/* 540 */         Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQMessageReference", "flatten()", (Throwable)je);
/*     */       }
/*     */       
/* 543 */       throw je;
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
/*     */   private static void flattenBytes(DataOutputStream dos, byte[] bytes) throws IOException {
/* 556 */     if (Trace.isOn) {
/* 557 */       Trace.entry("com.ibm.msg.client.wmq.internal.WMQMessageReference", "flattenBytes(DataOutputStream,byte [ ])", new Object[] { dos, bytes });
/*     */     }
/*     */     
/* 560 */     if (null == bytes) {
/* 561 */       dos.writeInt(0);
/* 562 */       if (Trace.isOn) {
/* 563 */         Trace.exit("com.ibm.msg.client.wmq.internal.WMQMessageReference", "flattenBytes(DataOutputStream,byte [ ])", 1);
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 569 */     dos.writeInt(bytes.length);
/* 570 */     dos.write(bytes);
/* 571 */     if (Trace.isOn) {
/* 572 */       Trace.exit("com.ibm.msg.client.wmq.internal.WMQMessageReference", "flattenBytes(DataOutputStream,byte [ ])", 2);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void flattenString(DataOutputStream dos, String text) throws IOException {
/* 579 */     if (Trace.isOn) {
/* 580 */       Trace.entry("com.ibm.msg.client.wmq.internal.WMQMessageReference", "flattenString(DataOutputStream,String)", new Object[] { dos, text });
/*     */     }
/*     */     
/* 583 */     byte[] bytes = null;
/* 584 */     if (null != text) {
/* 585 */       bytes = text.getBytes("ASCII");
/*     */     }
/* 587 */     flattenBytes(dos, bytes);
/* 588 */     if (Trace.isOn) {
/* 589 */       Trace.exit("com.ibm.msg.client.wmq.internal.WMQMessageReference", "flattenString(DataOutputStream,String)");
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
/*     */   public int getDataQuantity() throws JMSException {
/* 601 */     if (Trace.isOn) {
/* 602 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQMessageReference", "getDataQuantity()", "getter", 
/* 603 */           Integer.valueOf(this.dataQuantity));
/*     */     }
/* 605 */     return this.dataQuantity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setDataQuantity(int dataQuantity) {
/* 612 */     if (Trace.isOn) {
/* 613 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQMessageReference", "setDataQuantity(int)", "setter", 
/* 614 */           Integer.valueOf(dataQuantity));
/*     */     }
/* 616 */     this.dataQuantity = dataQuantity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   WMQDestination getDestination() {
/* 623 */     if (Trace.isOn) {
/* 624 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQMessageReference", "getDestination()", "getter", this.destination);
/*     */     }
/*     */     
/* 627 */     return this.destination;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDestination(ProviderDestination dest) {
/* 637 */     if (Trace.isOn) {
/* 638 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQMessageReference", "setDestination(ProviderDestination)", "setter", dest);
/*     */     }
/*     */     
/* 641 */     this.destination = (WMQDestination)dest;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDestinationReadFromAsString() {
/* 648 */     if (Trace.isOn) {
/* 649 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQMessageReference", "getDestinationReadFromAsString()", "getter", this.uriDestinationReadFrom);
/*     */     }
/*     */     
/* 652 */     return this.uriDestinationReadFrom;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDestinationAsString() {
/* 661 */     if (Trace.isOn) {
/* 662 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQMessageReference", "getDestinationAsString()");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 669 */     String retVal = (this.managedQName != null) ? ("queue:///" + this.managedQName) : this.uriDestinationReadFrom;
/*     */     
/* 671 */     if (Trace.isOn) {
/* 672 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQMessageReference", "getDestinationAsString()", retVal);
/*     */     }
/*     */     
/* 675 */     return retVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   String getFqSubName() {
/* 683 */     if (Trace.isOn) {
/* 684 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQMessageReference", "getFqSubName()", "getter", this.fqSubName);
/*     */     }
/*     */     
/* 687 */     return this.fqSubName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   String getManagedQName() {
/* 694 */     if (Trace.isOn) {
/* 695 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQMessageReference", "getManagedQName()", "getter", this.managedQName);
/*     */     }
/*     */     
/* 698 */     return this.managedQName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   String getQMgrName() {
/* 705 */     if (Trace.isOn) {
/* 706 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQMessageReference", "getQMgrName()", "getter", this.qMgrName);
/*     */     }
/*     */     
/* 709 */     return this.qMgrName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProviderMessage getMessage() throws JMSException {
/* 718 */     if (Trace.isOn) {
/* 719 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQMessageReference", "getMessage()", "getter", this.message);
/*     */     }
/*     */     
/* 722 */     return this.message;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int getMsgLength() {
/* 729 */     if (Trace.isOn) {
/* 730 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQMessageReference", "getMsgLength()", "getter", 
/* 731 */           Integer.valueOf(this.msgLength));
/*     */     }
/* 733 */     return this.msgLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   byte[] getToken() {
/* 740 */     if (Trace.isOn) {
/* 741 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQMessageReference", "getToken()", "getter", this.token);
/*     */     }
/*     */     
/* 744 */     return this.token;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   byte[] getSubID() {
/* 751 */     if (Trace.isOn) {
/* 752 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQMessageReference", "getSubID()", "getter", this.subID);
/*     */     }
/*     */     
/* 755 */     return this.subID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int hash8bytes(byte[] bytes) {
/* 765 */     if (Trace.isOn) {
/* 766 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQMessageReference", "hash8bytes(byte [ ])", new Object[] { bytes });
/*     */     }
/*     */ 
/*     */     
/* 770 */     int i1 = bytes[0] & 0xFF;
/* 771 */     int i2 = (bytes[1] & 0xFF) << 8;
/* 772 */     int i3 = (bytes[2] & 0xFF) << 16;
/* 773 */     long l4 = (bytes[3] & 0xFF) << 24L;
/*     */     
/* 775 */     long w1 = (i1 + i2 + i3) + l4 | 0x40404040L;
/*     */ 
/*     */     
/* 778 */     w1 *= 2147483587L;
/*     */     
/* 780 */     w1 = w1 >>> 32L ^ w1 & 0xFFFFFFFFL;
/*     */ 
/*     */     
/* 783 */     i1 = bytes[4] & 0xFF;
/* 784 */     i2 = (bytes[5] & 0xFF) << 8;
/* 785 */     i3 = (bytes[6] & 0xFF) << 16;
/* 786 */     l4 = (bytes[7] & 0xFF) << 24L;
/*     */     
/* 788 */     long w2 = (i1 + i2 + i3) + l4 | 0x40404040L;
/*     */ 
/*     */     
/* 791 */     w2 *= 2147483647L;
/*     */     
/* 793 */     w2 = w2 >>> 32L ^ w2 & 0xFFFFFFFFL;
/*     */ 
/*     */     
/* 796 */     w1 *= w2;
/*     */     
/* 798 */     w1 = w1 >>> 32L ^ w1 & 0xFFFFFFFFL;
/*     */ 
/*     */     
/* 801 */     int traceRet1 = (int)w1;
/* 802 */     if (Trace.isOn) {
/* 803 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQMessageReference", "hash8bytes(byte [ ])", 
/* 804 */           Integer.valueOf(traceRet1));
/*     */     }
/* 806 */     return traceRet1;
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
/*     */   public int hashCode() {
/* 820 */     return this.hashcode;
/*     */   }
/*     */   
/*     */   private static byte[] reinflateBytes(DataInputStream dis) throws IOException {
/* 824 */     if (Trace.isOn) {
/* 825 */       Trace.entry("com.ibm.msg.client.wmq.internal.WMQMessageReference", "reinflateBytes(DataInputStream)", new Object[] { dis });
/*     */     }
/*     */     
/* 828 */     int len = dis.readInt();
/* 829 */     if (0 > len) {
/*     */       
/* 831 */       HashMap<String, Object> info = new HashMap<>();
/* 832 */       info.put("len", Integer.valueOf(len));
/* 833 */       Trace.ffst("WMQMessageReference", "reinflateString", "XN00F004", info, IOException.class);
/*     */     } 
/*     */     
/* 836 */     if (0 == len) {
/* 837 */       if (Trace.isOn) {
/* 838 */         Trace.exit("com.ibm.msg.client.wmq.internal.WMQMessageReference", "reinflateBytes(DataInputStream)", null, 1);
/*     */       }
/*     */       
/* 841 */       return null;
/*     */     } 
/*     */     
/* 844 */     byte[] bytes = new byte[len];
/* 845 */     dis.readFully(bytes);
/* 846 */     if (Trace.isOn) {
/* 847 */       Trace.exit("com.ibm.msg.client.wmq.internal.WMQMessageReference", "reinflateBytes(DataInputStream)", bytes, 2);
/*     */     }
/*     */     
/* 850 */     return bytes;
/*     */   }
/*     */   
/*     */   private static String reinflateString(DataInputStream dis) throws IOException {
/* 854 */     if (Trace.isOn) {
/* 855 */       Trace.entry("com.ibm.msg.client.wmq.internal.WMQMessageReference", "reinflateString(DataInputStream)", new Object[] { dis });
/*     */     }
/*     */     
/* 858 */     byte[] bytes = reinflateBytes(dis);
/* 859 */     String text = null;
/* 860 */     if (null != bytes) {
/* 861 */       text = new String(bytes, "ASCII");
/*     */     }
/* 863 */     if (Trace.isOn) {
/* 864 */       Trace.exit("com.ibm.msg.client.wmq.internal.WMQMessageReference", "reinflateString(DataInputStream)", text);
/*     */     }
/*     */     
/* 867 */     return text;
/*     */   }
/*     */   
/*     */   void setMessage(ProviderMessage message) {
/* 871 */     if (Trace.isOn) {
/* 872 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQMessageReference", "setMessage(ProviderMessage)", "setter", message);
/*     */     }
/*     */     
/* 875 */     this.message = message;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 885 */     StringBuffer buff = new StringBuffer("WMQMessageReference ");
/*     */     try {
/* 887 */       buff.append(Integer.toHexString(System.identityHashCode(this)));
/* 888 */       buff.append(" destination=");
/* 889 */       if (null == this.destination) {
/* 890 */         buff.append("<null>");
/*     */       } else {
/*     */         
/* 893 */         buff.append(this.destination.getName());
/*     */       } 
/*     */       
/* 896 */       buff.append(" messagetoken=");
/* 897 */       if (this.token == null) {
/* 898 */         buff.append("<null>");
/*     */       } else {
/*     */         
/* 901 */         buff.append(JmqiTools.arrayToHexString(this.token));
/*     */       } 
/*     */       
/* 904 */       buff.append(" messagelength=");
/* 905 */       buff.append(this.msgLength);
/*     */       
/* 907 */       buff.append(" fqSubName=");
/* 908 */       if (this.fqSubName == null) {
/* 909 */         buff.append("<null>");
/*     */       } else {
/*     */         
/* 912 */         buff.append(this.fqSubName);
/*     */       } 
/*     */       
/* 915 */       buff.append(" managedQName=");
/* 916 */       if (this.managedQName == null) {
/* 917 */         buff.append("<null>");
/*     */       } else {
/*     */         
/* 920 */         buff.append(this.managedQName);
/*     */       } 
/*     */       
/* 923 */       buff.append(" qMgrName=");
/* 924 */       if (this.qMgrName == null) {
/* 925 */         buff.append("<null>");
/*     */       } else {
/*     */         
/* 928 */         buff.append(this.qMgrName);
/*     */       } 
/*     */       
/* 931 */       buff.append(" subID=");
/* 932 */       if (this.subID == null) {
/* 933 */         buff.append("<null>");
/*     */       } else {
/*     */         
/* 936 */         JmqiTools.arrayToHexString(this.subID);
/*     */       } 
/*     */       
/* 939 */       buff.append(" message=");
/* 940 */       if (this.message == null) {
/* 941 */         buff.append("<null>");
/*     */       } else {
/*     */         
/* 944 */         buff.append("<notnull>");
/*     */       }
/*     */     
/* 947 */     } catch (Throwable t) {
/* 948 */       buff.append("<ERROR>");
/*     */     } 
/* 950 */     return buff.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isManagedQueue() {
/* 957 */     boolean retVal = (this.managedQName != null);
/* 958 */     if (Trace.isOn) {
/* 959 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQMessageReference", "isManagedQueue()", "getter", 
/* 960 */           Boolean.valueOf(retVal));
/*     */     }
/* 962 */     if (Trace.isOn) {
/* 963 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQMessageReference", "isManagedQueue()", "getter", 
/* 964 */           Boolean.valueOf(retVal));
/*     */     }
/* 966 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\internal\WMQMessageReference.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */