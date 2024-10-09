/*     */ package com.ibm.msg.client.commonservices.tools;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.lang.reflect.Method;
/*     */ import java.text.MessageFormat;
/*     */ import java.util.Arrays;
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
/*     */ public class MainController
/*     */ {
/*     */   private static final String USAGE = "MainController.1";
/*     */   private static final String DEFAULT_OPTION = "MainController.2";
/*     */   private static final String INVALID_OPTION = "MainController.8";
/*     */   
/*     */   static {
/*  41 */     if (Trace.isOn) {
/*  42 */       Trace.data("com.ibm.msg.client.commonservices.tools.MainController", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/tools/MainController.java");
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
/*  54 */   private static HashMap<String, String[]> tools = new HashMap<String, String[]>()
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] arg) {
/*     */     String[] cmd;
/*  72 */     if (Trace.isOn) {
/*  73 */       Trace.entry("com.ibm.msg.client.commonservices.tools.MainController", "main(String [ ])", new Object[] { arg });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  78 */     String[] newArgs = new String[0];
/*     */     
/*  80 */     if (arg.length == 0) {
/*  81 */       cmd = tools.get("-version");
/*  82 */       System.out.println(getMessageString("MainController.2"));
/*     */     } else {
/*     */       
/*  85 */       newArgs = Arrays.<String>copyOfRange(arg, 0, arg.length);
/*  86 */       newArgs[0] = "-allclient";
/*     */       
/*  88 */       cmd = tools.get(arg[0].toLowerCase());
/*     */     } 
/*     */     
/*  91 */     if (cmd == null) {
/*  92 */       System.out.println(getMessageString("MainController.8", new Object[] { arg[0] }));
/*  93 */       cmd = tools.get("-version");
/*     */     } 
/*     */ 
/*     */     
/*  97 */     for (String string : cmd)
/*     */     {
/*  99 */       call(string, newArgs);
/*     */     }
/*     */     
/* 102 */     if (Trace.isOn) {
/* 103 */       Trace.exit("com.ibm.msg.client.commonservices.tools.MainController", "main(String [ ])");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static void call(String cmd, String[] args) {
/* 109 */     if (Trace.isOn) {
/* 110 */       Trace.entry("com.ibm.msg.client.commonservices.tools.MainController", "call(String,String [ ])", new Object[] { cmd, args });
/*     */     }
/*     */ 
/*     */     
/* 114 */     if (cmd.endsWith("usage()")) {
/* 115 */       usage();
/*     */     } else {
/*     */       
/*     */       try {
/* 119 */         Class<?> clazz = Class.forName(cmd);
/* 120 */         if (clazz != null) {
/* 121 */           Method mid = clazz.getMethod("main", new Class[] { String[].class });
/*     */           
/* 123 */           mid.invoke(null, new Object[] { args });
/*     */         }
/*     */       
/* 126 */       } catch (ClassNotFoundException|NoSuchMethodException|SecurityException|IllegalAccessException|IllegalArgumentException|java.lang.reflect.InvocationTargetException e) {
/*     */         
/* 128 */         if (Trace.isOn) {
/* 129 */           Trace.catchBlock("com.ibm.msg.client.commonservices.tools.MainController", "call(String,String [ ])", e);
/*     */         }
/*     */         
/* 132 */         abort(getMessageString("MainController.1"), e);
/*     */       } 
/*     */     } 
/*     */     
/* 136 */     if (Trace.isOn) {
/* 137 */       Trace.exit("com.ibm.msg.client.commonservices.tools.MainController", "call(String,String [ ])");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void usage() {
/* 144 */     if (Trace.isOn) {
/* 145 */       Trace.entry("com.ibm.msg.client.commonservices.tools.MainController", "usage()");
/*     */     }
/* 147 */     System.out.println(getMessageString("MainController.1"));
/* 148 */     if (Trace.isOn) {
/* 149 */       Trace.exit("com.ibm.msg.client.commonservices.tools.MainController", "usage()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String getMessageString(String tag) {
/* 159 */     if (Trace.isOn) {
/* 160 */       Trace.entry("com.ibm.msg.client.commonservices.tools.MainController", "getMessageString(String)", new Object[] { tag });
/*     */     }
/*     */     
/* 163 */     String traceRet1 = MainControllerMessages.getString(tag);
/* 164 */     if (Trace.isOn) {
/* 165 */       Trace.exit("com.ibm.msg.client.commonservices.tools.MainController", "getMessageString(String)", traceRet1);
/*     */     }
/*     */     
/* 168 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String getMessageString(String tag, Object... inserts) {
/* 177 */     if (Trace.isOn) {
/* 178 */       Trace.entry("com.ibm.msg.client.commonservices.tools.MainController", "getMessageString(String,Object...)", new Object[] { tag, inserts });
/*     */     }
/*     */     
/* 181 */     String traceRet1 = MessageFormat.format(getMessageString(tag), inserts);
/* 182 */     if (Trace.isOn) {
/* 183 */       Trace.exit("com.ibm.msg.client.commonservices.tools.MainController", "getMessageString(String,Object...)", traceRet1);
/*     */     }
/*     */     
/* 186 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void abort(String errorMessage, Throwable e) {
/* 195 */     if (Trace.isOn) {
/* 196 */       Trace.entry("com.ibm.msg.client.commonservices.tools.MainController", "abort(String,Throwable)", new Object[] { errorMessage, e });
/*     */     }
/*     */ 
/*     */     
/* 200 */     System.err.println(errorMessage);
/* 201 */     for (Throwable t = e; t != null; t = t.getCause()) {
/* 202 */       t.printStackTrace();
/*     */     }
/* 204 */     System.err.println(getMessageString("MainController.1"));
/* 205 */     System.exit(0);
/* 206 */     if (Trace.isOn)
/* 207 */       Trace.exit("com.ibm.msg.client.commonservices.tools.MainController", "abort(String,Throwable)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\tools\MainController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */