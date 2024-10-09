/*     */ package com.ibm.msg.client.commonservices.tools;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.lang.reflect.Method;
/*     */ import java.text.MessageFormat;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JakartaMainController
/*     */ {
/*     */   private static final String USAGE_MESSAGE = "JakartaMainController.1";
/*     */   private static final String DEFAULT_OPTION = "JakartaMainController.2";
/*     */   private static final String INVALID_OPTION = "JakartaMainController.8";
/*     */   
/*     */   static {
/*  40 */     if (Trace.isOn) {
/*  41 */       Trace.data("com.ibm.msg.client.commonservices.tools.JakartaMainController", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/tools/JakartaMainController.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private enum CommandDef
/*     */   {
/*  53 */     TRACECONTROL("com.ibm.msg.client.commonservices.trace.TraceController")
/*     */     {
/*     */       void execute(String[] args)
/*     */       {
/*  57 */         String[] newArgs = Arrays.<String>copyOfRange(args, 0, args.length);
/*  58 */         newArgs[0] = "-jakartaclient";
/*  59 */         JakartaMainController.call(this.command, newArgs);
/*     */       }
/*     */     },
/*  62 */     JMSVERSION("com.ibm.mq.jakarta.jms.MQJMSLevel")
/*     */     {
/*     */       void execute(String[] args)
/*     */       {
/*  66 */         String[] newArgs = Arrays.<String>copyOfRange(args, 1, args.length);
/*  67 */         JakartaMainController.call(this.command, newArgs);
/*     */       }
/*     */     },
/*  70 */     JMSADMIN("com.ibm.mq.jakarta.jms.admin.JMSAdmin")
/*     */     {
/*     */       void execute(String[] args)
/*     */       {
/*  74 */         String[] newArgs = Arrays.<String>copyOfRange(args, 1, args.length);
/*  75 */         JakartaMainController.call(this.command, newArgs);
/*     */       }
/*     */     },
/*  78 */     HELP("usage()")
/*     */     {
/*     */       void execute(String[] args)
/*     */       {
/*  82 */         JakartaMainController.usage();
/*     */       }
/*     */     },
/*  85 */     H("usage()")
/*     */     {
/*     */       void execute(String[] args)
/*     */       {
/*  89 */         JakartaMainController.usage();
/*     */       }
/*     */     },
/*  92 */     USAGE("Usage")
/*     */     {
/*     */       void execute(String[] args)
/*     */       {
/*  96 */         JakartaMainController.usage();
/*     */       }
/*     */     };
/*     */     
/*     */     String command;
/*     */     
/*     */     CommandDef(String command) {
/* 103 */       this.command = command;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     abstract void execute(String[] param1ArrayOfString);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] arg) {
/* 114 */     if (Trace.isOn) {
/* 115 */       Trace.entry("com.ibm.msg.client.commonservices.tools.JakartaMainController", "main(String [ ])", new Object[] { arg });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 120 */     if (arg.length == 0) {
/* 121 */       System.out.println(getMessageString("JakartaMainController.2"));
/*     */     }
/* 123 */     else if (arg[0].equals("-?")) {
/* 124 */       CommandDef.USAGE.execute(arg);
/*     */     }
/* 126 */     else if (arg[0].startsWith("-")) {
/*     */       try {
/* 128 */         CommandDef cmd = Enum.<CommandDef>valueOf(CommandDef.class, arg[0].substring(1).toUpperCase());
/* 129 */         cmd.execute(arg);
/*     */       }
/* 131 */       catch (IllegalArgumentException e) {
/* 132 */         System.out.println(getMessageString("JakartaMainController.8", new Object[] { arg[0] }));
/*     */       } 
/*     */     } else {
/*     */       
/* 136 */       System.out.println(getMessageString("JakartaMainController.8", new Object[] { arg[0] }));
/*     */     } 
/*     */     
/* 139 */     if (Trace.isOn) {
/* 140 */       Trace.exit("com.ibm.msg.client.commonservices.tools.JakartaMainController", "main(String [ ])");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static void call(String cmd, String[] args) {
/* 146 */     if (Trace.isOn) {
/* 147 */       Trace.entry("com.ibm.msg.client.commonservices.tools.JakartaMainController", "call(String,String [ ])", new Object[] { cmd, args });
/*     */     }
/*     */ 
/*     */     
/* 151 */     if (cmd.endsWith("usage()")) {
/* 152 */       usage();
/*     */     } else {
/*     */       
/*     */       try {
/* 156 */         Class<?> clazz = Class.forName(cmd);
/* 157 */         if (clazz != null) {
/* 158 */           Method mid = clazz.getMethod("main", new Class[] { String[].class });
/*     */           
/* 160 */           mid.invoke(null, new Object[] { args });
/*     */         }
/*     */       
/* 163 */       } catch (ClassNotFoundException|NoSuchMethodException|SecurityException|IllegalAccessException|IllegalArgumentException|java.lang.reflect.InvocationTargetException e) {
/*     */         
/* 165 */         if (Trace.isOn) {
/* 166 */           Trace.catchBlock("com.ibm.msg.client.commonservices.tools.JakartaMainController", "call(String,String [ ])", e);
/*     */         }
/*     */         
/* 169 */         abort(getMessageString("JakartaMainController.1"), e);
/*     */       } 
/*     */     } 
/*     */     
/* 173 */     if (Trace.isOn) {
/* 174 */       Trace.exit("com.ibm.msg.client.commonservices.tools.JakartaMainController", "call(String,String [ ])");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void usage() {
/* 181 */     if (Trace.isOn) {
/* 182 */       Trace.entry("com.ibm.msg.client.commonservices.tools.JakartaMainController", "usage()");
/*     */     }
/* 184 */     System.out.println(getMessageString("JakartaMainController.1"));
/* 185 */     if (Trace.isOn) {
/* 186 */       Trace.exit("com.ibm.msg.client.commonservices.tools.JakartaMainController", "usage()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String getMessageString(String tag) {
/* 196 */     if (Trace.isOn) {
/* 197 */       Trace.entry("com.ibm.msg.client.commonservices.tools.JakartaMainController", "getMessageString(String)", new Object[] { tag });
/*     */     }
/*     */     
/* 200 */     String traceRet1 = MainControllerMessages.getString(tag);
/* 201 */     if (Trace.isOn) {
/* 202 */       Trace.exit("com.ibm.msg.client.commonservices.tools.JakartaMainController", "getMessageString(String)", traceRet1);
/*     */     }
/*     */     
/* 205 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String getMessageString(String tag, Object... inserts) {
/* 214 */     if (Trace.isOn) {
/* 215 */       Trace.entry("com.ibm.msg.client.commonservices.tools.JakartaMainController", "getMessageString(String,Object...)", new Object[] { tag, inserts });
/*     */     }
/*     */     
/* 218 */     String traceRet1 = MessageFormat.format(getMessageString(tag), inserts);
/* 219 */     if (Trace.isOn) {
/* 220 */       Trace.exit("com.ibm.msg.client.commonservices.tools.JakartaMainController", "getMessageString(String,Object...)", traceRet1);
/*     */     }
/*     */     
/* 223 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void abort(String errorMessage, Throwable e) {
/* 232 */     if (Trace.isOn) {
/* 233 */       Trace.entry("com.ibm.msg.client.commonservices.tools.JakartaMainController", "abort(String,Throwable)", new Object[] { errorMessage, e });
/*     */     }
/*     */ 
/*     */     
/* 237 */     System.err.println(errorMessage);
/* 238 */     for (Throwable t = e; t != null; t = t.getCause()) {
/* 239 */       t.printStackTrace();
/*     */     }
/* 241 */     System.err.println(getMessageString("JakartaMainController.1"));
/* 242 */     System.exit(0);
/* 243 */     if (Trace.isOn)
/* 244 */       Trace.exit("com.ibm.msg.client.commonservices.tools.JakartaMainController", "abort(String,Throwable)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\tools\JakartaMainController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */