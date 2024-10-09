/*     */ package com.ibm.msg.client.commonservices.commandmanager;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.CSIException;
/*     */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Command
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 7543533149884228613L;
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/commandmanager/Command.java";
/*     */   public static final String COMMAND = "cmd";
/*     */   public static final String EXCEPTION = "exc";
/*     */   public static final int UNSUBMITED = 1;
/*     */   public static final int PENDING = 2;
/*     */   public static final int SUCCESS = 3;
/*     */   public static final int FAIL = 4;
/*     */   
/*     */   static {
/*  51 */     if (Trace.isOn) {
/*  52 */       Trace.data("com.ibm.msg.client.commonservices.commandmanager.Command", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/commandmanager/Command.java");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 100 */   public HashMap<Object, Object> parameters = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 105 */   public Map<Object, Object> results = Collections.synchronizedMap(new HashMap<>());
/*     */   
/* 107 */   private int state = 1;
/*     */ 
/*     */   
/* 110 */   private Object stateLock = new Object();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String uid;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Command createCommand(String commandType) {
/* 121 */     if (Trace.isOn) {
/* 122 */       Trace.entry("com.ibm.msg.client.commonservices.commandmanager.Command", "createCommand(String)", new Object[] { commandType });
/*     */     }
/*     */     
/* 125 */     Command command = new Command();
/* 126 */     command.parameters.put("cmd", commandType);
/* 127 */     command.uid = System.currentTimeMillis() + commandType;
/*     */     
/* 129 */     if (Trace.isOn) {
/* 130 */       Trace.exit("com.ibm.msg.client.commonservices.commandmanager.Command", "createCommand(String)", command);
/*     */     }
/*     */     
/* 133 */     return command;
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
/*     */   public int getState() {
/*     */     int tempState;
/* 152 */     synchronized (this.stateLock) {
/* 153 */       tempState = this.state;
/*     */     } 
/* 155 */     if (Trace.isOn) {
/* 156 */       Trace.data(this, "com.ibm.msg.client.commonservices.commandmanager.Command", "getState()", "getter", 
/* 157 */           Integer.valueOf(tempState));
/*     */     }
/* 159 */     return tempState;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUID() {
/* 170 */     if (Trace.isOn) {
/* 171 */       Trace.data(this, "com.ibm.msg.client.commonservices.commandmanager.Command", "getUID()", "getter", this.uid);
/*     */     }
/*     */     
/* 174 */     return this.uid;
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
/*     */   public void setState(int state) throws Exception {
/* 186 */     if (Trace.isOn) {
/* 187 */       Trace.data(this, "com.ibm.msg.client.commonservices.commandmanager.Command", "setState(int)", "setter", 
/* 188 */           Integer.valueOf(state));
/*     */     }
/* 190 */     synchronized (this.stateLock) {
/* 191 */       if (state > this.state) {
/* 192 */         this.state = state;
/*     */       }
/*     */       else {
/*     */         
/* 196 */         String msg = NLSServices.getMessage("JMSCS0015");
/*     */         
/* 198 */         CSIException cSIException = new CSIException(msg);
/* 199 */         if (Trace.isOn) {
/* 200 */           Trace.throwing(this, "com.ibm.msg.client.commonservices.commandmanager.Command", "setState(int)", (Throwable)cSIException);
/*     */         }
/*     */         
/* 203 */         throw cSIException;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFailed(Exception exception) {
/* 215 */     if (Trace.isOn) {
/* 216 */       Trace.data(this, "com.ibm.msg.client.commonservices.commandmanager.Command", "setFailed(Exception)", "setter", exception);
/*     */     }
/*     */     
/* 219 */     synchronized (this.stateLock) {
/* 220 */       if (this.state < 4) {
/* 221 */         this.results.put("exc", exception);
/* 222 */         this.state = 4;
/*     */       } 
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
/*     */   public byte[] flatten() {
/* 235 */     if (Trace.isOn) {
/* 236 */       Trace.entry(this, "com.ibm.msg.client.commonservices.commandmanager.Command", "flatten()");
/*     */     }
/* 238 */     byte[] bytes = null;
/*     */     
/*     */     try {
/* 241 */       ByteArrayOutputStream bos = new ByteArrayOutputStream();
/* 242 */       ObjectOutputStream oos = new ObjectOutputStream(bos);
/*     */       
/* 244 */       oos.writeObject(this.parameters);
/*     */       
/* 246 */       oos.writeObject(this.results);
/*     */       
/* 248 */       oos.writeInt(getState());
/*     */       
/* 250 */       oos.writeObject(this.uid);
/*     */       
/* 252 */       bytes = bos.toByteArray();
/*     */     }
/* 254 */     catch (Exception e) {
/* 255 */       if (Trace.isOn) {
/* 256 */         Trace.catchBlock(this, "com.ibm.msg.client.commonservices.commandmanager.Command", "flatten()", e);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 265 */     if (Trace.isOn) {
/* 266 */       Trace.exit(this, "com.ibm.msg.client.commonservices.commandmanager.Command", "flatten()", bytes);
/*     */     }
/*     */     
/* 269 */     return bytes;
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
/*     */   public void unFlatten(byte[] bytes) throws Exception {
/* 284 */     if (Trace.isOn) {
/* 285 */       Trace.entry(this, "com.ibm.msg.client.commonservices.commandmanager.Command", "unFlatten(byte [ ])", new Object[] { bytes });
/*     */     }
/*     */     
/*     */     try {
/* 289 */       ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
/* 290 */       ObjectInputStream ois = new ObjectInputStream(bis);
/*     */       
/* 292 */       this.parameters = (HashMap<Object, Object>)ois.readObject();
/*     */       
/* 294 */       this.results = (HashMap)ois.readObject();
/*     */       
/* 296 */       setState(ois.readInt());
/*     */       
/* 298 */       this.uid = (String)ois.readObject();
/*     */     }
/* 300 */     catch (Exception e) {
/* 301 */       if (Trace.isOn) {
/* 302 */         Trace.catchBlock(this, "com.ibm.msg.client.commonservices.commandmanager.Command", "unFlatten(byte [ ])", e);
/*     */       }
/*     */       
/* 305 */       Exception x = new Exception("Problem with Comand Object send accross network");
/* 306 */       x.setStackTrace(e.getStackTrace());
/* 307 */       if (Trace.isOn) {
/* 308 */         Trace.throwing(this, "com.ibm.msg.client.commonservices.commandmanager.Command", "unFlatten(byte [ ])", x);
/*     */       }
/*     */       
/* 311 */       throw x;
/*     */     } 
/* 313 */     if (Trace.isOn)
/* 314 */       Trace.exit(this, "com.ibm.msg.client.commonservices.commandmanager.Command", "unFlatten(byte [ ])"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\commandmanager\Command.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */