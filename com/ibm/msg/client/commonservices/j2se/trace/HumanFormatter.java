/*      */ package com.ibm.msg.client.commonservices.j2se.trace;
/*      */ 
/*      */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*      */ import com.ibm.msg.client.commonservices.trace.TableBuilder;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.commonservices.trace.TraceFormatter;
/*      */ import com.ibm.msg.client.commonservices.trace.TraceHandler;
/*      */ import com.ibm.msg.client.commonservices.trace.TraceRecord;
/*      */ import java.io.PrintWriter;
/*      */ import java.io.StringWriter;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.security.AccessControlException;
/*      */ import java.security.AccessController;
/*      */ import java.security.PrivilegedAction;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Calendar;
/*      */ import java.util.Date;
/*      */ import java.util.GregorianCalendar;
/*      */ import java.util.HashMap;
/*      */ import java.util.Map;
/*      */ import java.util.Properties;
/*      */ import java.util.TreeMap;
/*      */ import java.util.WeakHashMap;
/*      */ import java.util.logging.Formatter;
/*      */ import java.util.logging.LogRecord;
/*      */ import java.util.regex.Matcher;
/*      */ import java.util.regex.Pattern;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class HumanFormatter
/*      */   extends Formatter
/*      */   implements TraceFormatter
/*      */ {
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices.j2se/src/com/ibm/msg/client/commonservices/j2se/trace/HumanFormatter.java";
/*   80 */   private static final char[] base36chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
/*      */   
/*   82 */   private static final Calendar calendar = new GregorianCalendar();
/*      */ 
/*      */   
/*      */   private static final String spacesBetweenColumns = "  ";
/*      */   
/*      */   private static String lineSeparator;
/*      */   
/*      */   private boolean firstRecord = true;
/*      */   
/*      */   private HashMap<String, MessageFormatAdapter> messageFormatAdapters;
/*      */   
/*   93 */   private int maxTraceBytes = -1;
/*      */ 
/*      */ 
/*      */   
/*   97 */   private FastHashMap cachedClassNames = null;
/*      */   
/*   99 */   private char[] cachedHourMinuteSecond = null;
/*      */   
/*      */   private int cachedHour;
/*      */   
/*      */   private int cachedMilliSecond;
/*      */   
/*      */   private int cachedMinute;
/*      */   
/*      */   private int cachedSecond;
/*      */   
/*  109 */   private int subMilliSecsCounter = 0;
/*      */   
/*  111 */   private int tid = 0;
/*      */   
/*  113 */   private char[] cachedDateStamp = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int cachedDay;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  124 */   private String cachedMultiLinePrimitive = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void writeHeaderInfo(StringBuilder buffer) {
/*  135 */     Date date = new Date();
/*  136 */     buffer.append("Date: ");
/*  137 */     buffer.append(date.toString());
/*  138 */     buffer.append(lineSeparator);
/*  139 */     buffer.append(lineSeparator);
/*      */ 
/*      */     
/*  142 */     buffer.append("Process ID:").append(DefaultTracer.generatePID());
/*  143 */     if (DefaultTracer.PID.startsWith("f")) {
/*  144 */       buffer.append("  (fake process id)");
/*      */     }
/*      */     
/*  147 */     buffer.append(lineSeparator);
/*  148 */     buffer.append(lineSeparator);
/*      */ 
/*      */     
/*  151 */     Map<Object, Object> properties = getSystemProperties();
/*      */     
/*  153 */     TableBuilder table = new TableBuilder();
/*      */     
/*  155 */     buffer.append("System properties (list may be incomplete due to security policy):");
/*  156 */     buffer.append(lineSeparator);
/*  157 */     for (Map.Entry<Object, Object> entry : properties.entrySet()) {
/*  158 */       String key = entry.getKey().toString();
/*  159 */       String value = String.valueOf(entry.getValue());
/*      */ 
/*      */       
/*  162 */       boolean isInPropertyBlacklist = Trace.propertyShouldBeMasked(key);
/*  163 */       if (isInPropertyBlacklist) {
/*  164 */         value = "********";
/*      */       }
/*      */       
/*  167 */       if ("sun.java.command".equalsIgnoreCase(key)) {
/*      */         
/*  169 */         String regexPattern = "(?<prePasswordValue>^\\s*com\\.ibm\\.wmqfte\\.agent\\.bootstrap\\.impl\\.BootstrapMain.*(-sp|-servicePassword|-mqpassword|-newmqpassword|-cdp)\\s+)(?<passwordValue>[^ ]*)(?<postPasswordValue>.*$)";
/*      */ 
/*      */ 
/*      */         
/*  173 */         Pattern p = Pattern.compile(regexPattern);
/*  174 */         Matcher m = p.matcher(value);
/*  175 */         if (m.matches()) {
/*  176 */           value = m.group("prePasswordValue") + "********" + m.group("postPasswordValue");
/*      */         }
/*      */       }
/*  179 */       else if ("line.separator".equals(key)) {
/*  180 */         StringBuilder quotedValue = new StringBuilder();
/*  181 */         for (char c : value.toCharArray()) {
/*  182 */           switch (c) {
/*      */             case '\n':
/*  184 */               quotedValue.append("\\n");
/*      */               break;
/*      */             case '\r':
/*  187 */               quotedValue.append("\\r");
/*      */               break;
/*      */             default:
/*  190 */               quotedValue.append(c); break;
/*      */           } 
/*      */         } 
/*  193 */         value = quotedValue.toString();
/*      */       } 
/*      */       
/*  196 */       table.append(key, value);
/*      */     } 
/*  198 */     buffer.append(table.toStringBuilder());
/*      */     
/*  200 */     buffer.append(lineSeparator);
/*  201 */     buffer.append(lineSeparator);
/*      */ 
/*      */     
/*  204 */     Runtime runtime = Runtime.getRuntime();
/*  205 */     buffer.append(lineSeparator);
/*  206 */     buffer.append("Runtime properties:");
/*  207 */     buffer.append(lineSeparator);
/*  208 */     buffer.append("  Available processors: ");
/*  209 */     buffer.append(runtime.availableProcessors());
/*  210 */     buffer.append(lineSeparator);
/*  211 */     buffer.append("  Total memory in bytes (now): ");
/*  212 */     buffer.append(runtime.totalMemory());
/*  213 */     buffer.append(lineSeparator);
/*  214 */     buffer.append("  Free memory in bytes (now): ");
/*  215 */     buffer.append(runtime.freeMemory());
/*  216 */     buffer.append(lineSeparator);
/*  217 */     buffer.append("  Max memory in bytes: ");
/*  218 */     buffer.append(runtime.maxMemory());
/*  219 */     buffer.append(lineSeparator);
/*  220 */     buffer.append(lineSeparator);
/*      */ 
/*      */     
/*  223 */     buffer.append("Stack trace of initiating call:").append(lineSeparator);
/*  224 */     Exception e = new Exception();
/*  225 */     StackTraceElement[] stacktrace = e.getStackTrace();
/*  226 */     for (int count = 0; count < stacktrace.length; count++) {
/*  227 */       buffer.append("  ").append(stacktrace[count]).append(lineSeparator);
/*      */     }
/*      */     
/*  230 */     buffer.append("Version information in main body of trace");
/*  231 */     buffer.append(lineSeparator);
/*      */     
/*  233 */     buffer.append("TimeStamp                   TID       ObjectId   Class                                                                                      Data");
/*  234 */     buffer.append(lineSeparator);
/*  235 */     buffer.append("==========================================================================================================================================================================");
/*  236 */     buffer.append(lineSeparator);
/*      */   }
/*      */ 
/*      */   
/*  240 */   private static final String[] IMPORTANT_PROPERTIES = new String[] { "user.name", "os.name", "user.dir", "line.separator", "path.separator", "file.separator", "com.ibm.msg.client.commonservices.log.*", "com.ibm.msg.client.commonservices.trace.*", "com.ibm.msg.client.commonservices.trace.startup", "Diagnostics.Java.Errors.Destination.Filename", "com.ibm.mq.commonservices", "com.ibm.mq.cfg.*" };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private ThreadLocal<ThreadContext> localThreadContext;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Map<Object, Object> getSystemProperties() {
/*  260 */     Map<Object, Object> result = AccessController.<Map<Object, Object>>doPrivileged(new PrivilegedAction<Map<Object, Object>>()
/*      */         {
/*      */           public TreeMap<Object, Object> run()
/*      */           {
/*  264 */             TreeMap<Object, Object> systemProperties = new TreeMap<>();
/*      */             try {
/*  266 */               Properties properties = System.getProperties();
/*  267 */               for (Map.Entry<Object, Object> e : properties.entrySet()) {
/*  268 */                 systemProperties.put(e.getKey(), e.getValue());
/*      */               }
/*      */             }
/*  271 */             catch (AccessControlException ace) {
/*      */               
/*  273 */               for (String property : HumanFormatter.IMPORTANT_PROPERTIES) {
/*      */                 try {
/*  275 */                   String value = System.getProperty(property);
/*  276 */                   if (value != null) {
/*  277 */                     systemProperties.put(property, value);
/*      */                   }
/*      */                 }
/*  280 */                 catch (AccessControlException accessControlException) {}
/*      */               } 
/*      */             } 
/*      */ 
/*      */             
/*  285 */             return systemProperties;
/*      */           }
/*      */         });
/*      */     
/*  289 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static {
/*  296 */     String lineSeparatorProperty = "line.separator";
/*      */ 
/*      */     
/*  299 */     PropertyStore.register(lineSeparatorProperty, "\n");
/*      */ 
/*      */     
/*  302 */     lineSeparator = PropertyStore.getStringProperty(lineSeparatorProperty);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String format(LogRecord record) {
/*  341 */     StringBuilder output = new StringBuilder(256);
/*      */ 
/*      */     
/*  344 */     MessageFormatAdapter adapter = this.messageFormatAdapters.get(record.getMessage());
/*      */     
/*  346 */     if (adapter == null) {
/*  347 */       adapter = this.messageFormatAdapters.get("TRACE_DATA");
/*      */     }
/*      */     
/*      */     try {
/*  351 */       if (this.firstRecord) {
/*  352 */         this.firstRecord = false;
/*  353 */         writeHeaderInfo(output);
/*      */       } 
/*  355 */       adapter.formatRecord(record, output);
/*      */     }
/*  357 */     catch (Throwable t) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  363 */       output.append(lineSeparator);
/*      */ 
/*      */ 
/*      */       
/*  367 */       output.append(" *** Problem occurred formatting trace line: ");
/*      */       try {
/*  369 */         output.append(t);
/*  370 */         StringWriter sw = new StringWriter();
/*  371 */         PrintWriter pw = new PrintWriter(sw);
/*  372 */         t.printStackTrace(pw);
/*  373 */         pw.flush();
/*  374 */         sw.flush();
/*  375 */         output.append(sw.toString());
/*  376 */         pw.close();
/*  377 */         sw.close();
/*      */       }
/*  379 */       catch (Throwable t2) {
/*  380 */         output.append(" Could not retrieve message from Throwable");
/*      */       } 
/*  382 */       output.append(" ***");
/*  383 */       output.append(lineSeparator);
/*      */     } 
/*      */     
/*  386 */     String result = output.toString();
/*  387 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String format(TraceRecord record) {
/*  396 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getHead(TraceHandler h) {
/*  405 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getTail(TraceHandler h) {
/*  414 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   class CatchFormatAdapter
/*      */     extends SimpleFormatAdapter
/*      */   {
/*      */     private void appendClassLoaderInfo(Object object) {
/*      */       try {
/*  430 */         ClassLoader objectClassLoader = object.getClass().getClassLoader();
/*  431 */         ClassLoader cl = Thread.currentThread().getContextClassLoader();
/*  432 */         String ccl = "<ContextClassLoader is null>";
/*  433 */         if (cl != null)
/*      */         {
/*  435 */           ccl = cl.toString();
/*      */         }
/*      */         
/*  438 */         this.buffer.append(this.prefix);
/*  439 */         this.buffer.append("Object ClassLoader = ");
/*  440 */         this.buffer.append(objectClassLoader);
/*  441 */         this.buffer.append(HumanFormatter.lineSeparator);
/*      */         
/*  443 */         this.buffer.append(this.prefix);
/*  444 */         this.buffer.append("CurrentThread ClassLoader = ");
/*  445 */         this.buffer.append(ccl);
/*  446 */         this.buffer.append(HumanFormatter.lineSeparator);
/*      */       }
/*  448 */       catch (Exception exception) {}
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void appendStackTrace(Throwable t) {
/*  459 */       StackTraceElement[] stack = t.getStackTrace();
/*      */       
/*  461 */       for (int i = 0; i < stack.length; i++) {
/*  462 */         this.buffer.append(this.prefix);
/*  463 */         this.buffer.append("  ");
/*  464 */         this.buffer.append(stack[i]);
/*  465 */         this.buffer.append(HumanFormatter.lineSeparator);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void formatRecord() {
/*  475 */       this.prefix = newStartOfRecord(true);
/*      */       
/*  477 */       this.buffer.append("  X  ");
/*  478 */       this.buffer.append(this.record.getSourceMethodName());
/*  479 */       this.buffer.append(HumanFormatter.lineSeparator);
/*      */       
/*  481 */       Throwable thrown = this.record.getThrown();
/*  482 */       if (thrown == null) {
/*  483 */         thrown = new Throwable("Unknown Throwable");
/*      */       }
/*      */       
/*  486 */       this.buffer.append(this.prefix);
/*  487 */       appendMessage(thrown.getMessage());
/*  488 */       this.buffer.append(" [");
/*  489 */       this.buffer.append(thrown.getClass().getName());
/*  490 */       this.buffer.append("] at:");
/*  491 */       this.buffer.append(HumanFormatter.lineSeparator);
/*  492 */       appendStackTrace(thrown);
/*  493 */       appendClassLoaderInfo(thrown);
/*      */       
/*  495 */       Throwable cause = thrown.getCause();
/*      */       
/*  497 */       while (cause != null) {
/*  498 */         this.buffer.append(this.prefix);
/*  499 */         this.buffer.append(" Cause:");
/*  500 */         this.buffer.append(HumanFormatter.lineSeparator);
/*      */         
/*  502 */         this.buffer.append(this.prefix);
/*  503 */         appendMessage(cause.getMessage());
/*  504 */         this.buffer.append(" [");
/*  505 */         this.buffer.append(cause.getClass().getName());
/*  506 */         this.buffer.append("] at:");
/*  507 */         this.buffer.append(HumanFormatter.lineSeparator);
/*  508 */         appendStackTrace(cause);
/*  509 */         appendClassLoaderInfo(cause);
/*      */         
/*  511 */         cause = cause.getCause();
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   class DataFormatAdapter
/*      */     extends SimpleFormatAdapter
/*      */   {
/*      */     private byte[] cachedDirectByteBufferBytes;
/*      */ 
/*      */     
/*      */     private int cachedDirectByteBufferBytesLength;
/*      */ 
/*      */ 
/*      */     
/*      */     DataFormatAdapter() {
/*  530 */       this.cachedDirectByteBufferBytes = null;
/*      */       
/*  532 */       this.cachedDirectByteBufferBytesLength = 32758;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void dumpBytes(byte[] data, int start, int count, String comment) {
/*  544 */       if (comment == null) {
/*  545 */         this.buffer.append(" <data>");
/*  546 */         this.buffer.append(HumanFormatter.lineSeparator);
/*      */       } else {
/*      */         
/*  549 */         this.buffer.append(" <data>");
/*  550 */         this.buffer.append(" ");
/*  551 */         this.buffer.append(comment);
/*  552 */         this.buffer.append(HumanFormatter.lineSeparator);
/*      */       } 
/*      */       
/*      */       try {
/*  556 */         if (data != null) {
/*  557 */           int len = data.length;
/*      */           
/*  559 */           this.buffer.append(this.prefix);
/*  560 */           this.buffer.append("Length = 0x");
/*  561 */           this.buffer.append(Integer.toHexString(len));
/*  562 */           this.buffer.append(" (");
/*  563 */           this.buffer.append(len);
/*  564 */           this.buffer.append(") bytes start=");
/*  565 */           this.buffer.append(start);
/*  566 */           this.buffer.append(" count=");
/*  567 */           this.buffer.append(count);
/*  568 */           this.buffer.append(HumanFormatter.lineSeparator);
/*  569 */           this.buffer.append(this.prefix);
/*  570 */           this.buffer.append("            offset    : 0 1 2 3  4 5 6 7  8 9 A B  C D E F");
/*  571 */           this.buffer.append(HumanFormatter.lineSeparator);
/*      */ 
/*      */ 
/*      */           
/*  575 */           int suppress = 0;
/*  576 */           int end = start + count;
/*  577 */           String[] c = new String[16];
/*  578 */           String[] p = new String[16];
/*      */ 
/*      */           
/*  581 */           char[] textBuffer = null;
/*      */           
/*  583 */           for (int j = 0; j < 16; j++) {
/*  584 */             c[j] = null;
/*      */           }
/*      */           int i;
/*  587 */           for (i = 0; i < len; i += 16) {
/*  588 */             boolean skip = true;
/*      */             
/*  590 */             for (int k = 0; k < 16; k++) {
/*  591 */               int t = i + k;
/*      */               
/*  593 */               if (t >= start && t < end && t < len) {
/*  594 */                 c[k] = pad(Integer.toHexString(data[t]), 2);
/*  595 */                 skip = false;
/*      */               } else {
/*      */                 
/*  598 */                 c[k] = "  ";
/*      */               } 
/*      */             } 
/*      */             
/*  602 */             if (skip) {
/*  603 */               if (suppress > 0) {
/*  604 */                 duplicateLinesSuppressed(suppress);
/*      */               }
/*  606 */               suppress = 0;
/*  607 */               c[0] = null;
/*      */             
/*      */             }
/*  610 */             else if (c[0].equals(p[0]) && c[1].equals(p[1]) && c[2].equals(p[2]) && c[3].equals(p[3]) && c[4].equals(p[4]) && c[5].equals(p[5]) && c[6].equals(p[6]) && c[7]
/*  611 */               .equals(p[7]) && c[8].equals(p[8]) && c[9].equals(p[9]) && c[10].equals(p[10]) && c[11].equals(p[11]) && c[12].equals(p[12]) && c[13].equals(p[13]) && c[14]
/*  612 */               .equals(p[14]) && c[15].equals(p[15])) {
/*  613 */               suppress++;
/*      */             } else {
/*      */               
/*  616 */               if (suppress > 0) {
/*  617 */                 duplicateLinesSuppressed(suppress);
/*      */               }
/*  619 */               this.buffer.append(this.prefix);
/*  620 */               this.buffer.append("0x");
/*  621 */               this.buffer.append(pad(Integer.toHexString(i), 8));
/*  622 */               this.buffer.append(" (");
/*  623 */               this.buffer.append(pad(Integer.toString(i), 8, ' '));
/*  624 */               this.buffer.append(") : ");
/*  625 */               this.buffer.append(c[0]);
/*  626 */               this.buffer.append(c[1]);
/*  627 */               this.buffer.append(c[2]);
/*  628 */               this.buffer.append(c[3]);
/*  629 */               this.buffer.append(" ");
/*  630 */               this.buffer.append(c[4]);
/*  631 */               this.buffer.append(c[5]);
/*  632 */               this.buffer.append(c[6]);
/*  633 */               this.buffer.append(c[7]);
/*  634 */               this.buffer.append(" ");
/*  635 */               this.buffer.append(c[8]);
/*  636 */               this.buffer.append(c[9]);
/*  637 */               this.buffer.append(c[10]);
/*  638 */               this.buffer.append(c[11]);
/*  639 */               this.buffer.append(" ");
/*  640 */               this.buffer.append(c[12]);
/*  641 */               this.buffer.append(c[13]);
/*  642 */               this.buffer.append(c[14]);
/*  643 */               this.buffer.append(c[15]);
/*  644 */               this.buffer.append(" : ");
/*      */ 
/*      */               
/*  647 */               textBuffer = (new String(data, i, (i + 16 <= len) ? 16 : (len - i), "US-ASCII")).toCharArray();
/*      */ 
/*      */               
/*  650 */               for (int x = i; x < i + 16; x++) {
/*  651 */                 if (x < len && x - start < count) {
/*  652 */                   if (x >= start) {
/*  653 */                     byte curByte = data[x];
/*      */ 
/*      */                     
/*  656 */                     if (curByte >= 32 && curByte <= 126) {
/*  657 */                       this.buffer.append(textBuffer[x % 16]);
/*      */                     } else {
/*      */                       
/*  660 */                       this.buffer.append(".");
/*      */                     }
/*      */                   
/*      */                   } else {
/*      */                     
/*  665 */                     this.buffer.append(" ");
/*      */                   } 
/*      */                 }
/*      */               } 
/*  669 */               this.buffer.append(HumanFormatter.lineSeparator);
/*      */ 
/*      */               
/*  672 */               for (int m = 0; m < 16; m++) {
/*  673 */                 p[m] = c[m];
/*      */               }
/*  675 */               suppress = 0;
/*      */             } 
/*      */           } 
/*      */ 
/*      */           
/*  680 */           if (suppress > 0) {
/*  681 */             duplicateLinesSuppressed(suppress);
/*      */           }
/*      */         } else {
/*      */           
/*  685 */           this.buffer.append(this.prefix);
/*  686 */           this.buffer.append("data is null");
/*      */         }
/*      */       
/*  689 */       } catch (Exception exception) {}
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void duplicateLinesSuppressed(int i) {
/*  700 */       this.buffer.append(this.prefix);
/*  701 */       this.buffer.append("                          ");
/*  702 */       this.buffer.append(Integer.toString(i));
/*  703 */       this.buffer.append(" duplicate line(s) suppressed");
/*  704 */       this.buffer.append(HumanFormatter.lineSeparator);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private String pad(String s, int l) {
/*  715 */       return pad(s, l, '0');
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private String pad(String s, int l, char pad) {
/*  727 */       String result = null;
/*      */       
/*  729 */       if (s.length() < l) {
/*  730 */         char[] rc = new char[l];
/*  731 */         int padCount = l - s.length();
/*  732 */         Arrays.fill(rc, 0, padCount, pad);
/*  733 */         System.arraycopy(s.toCharArray(), 0, rc, padCount, s.length());
/*  734 */         result = new String(rc);
/*      */       } else {
/*      */         
/*  737 */         result = s.substring(s.length() - l);
/*      */       } 
/*      */       
/*  740 */       return result;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private boolean processByteBuffers(Object parameter) {
/*  752 */       boolean justDumpedBytes = false;
/*      */       
/*  754 */       String dumpComment = null;
/*  755 */       byte[] dumpByteArray = null;
/*  756 */       int dumpLength = 0;
/*  757 */       int dumpOffet = 0;
/*      */       
/*  759 */       if (parameter instanceof byte[]) {
/*  760 */         dumpByteArray = (byte[])parameter;
/*  761 */         dumpLength = dumpByteArray.length;
/*  762 */         dumpOffet = 0;
/*      */       }
/*  764 */       else if (parameter instanceof ByteBuffer) {
/*  765 */         ByteBuffer byteBuffer = (ByteBuffer)parameter;
/*  766 */         dumpComment = byteBuffer.toString();
/*      */         
/*  768 */         if (byteBuffer.isDirect()) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  773 */           ByteBuffer byteBufferCopy = byteBuffer.duplicate();
/*  774 */           int limit = byteBufferCopy.limit();
/*  775 */           int offset = byteBufferCopy.position();
/*  776 */           int length = limit - offset;
/*      */ 
/*      */ 
/*      */           
/*  780 */           if (limit == this.cachedDirectByteBufferBytesLength) {
/*      */             
/*  782 */             if (this.cachedDirectByteBufferBytes == null) {
/*  783 */               this.cachedDirectByteBufferBytes = new byte[this.cachedDirectByteBufferBytesLength];
/*      */             }
/*      */             
/*  786 */             dumpByteArray = this.cachedDirectByteBufferBytes;
/*      */           
/*      */           }
/*      */           else {
/*      */             
/*  791 */             dumpByteArray = new byte[limit];
/*      */           } 
/*      */           
/*  794 */           dumpLength = length;
/*      */ 
/*      */ 
/*      */           
/*  798 */           dumpOffet = offset;
/*      */ 
/*      */           
/*  801 */           byteBufferCopy.rewind();
/*  802 */           byteBufferCopy.get(dumpByteArray, 0, length);
/*      */         }
/*  804 */         else if (byteBuffer.hasArray()) {
/*  805 */           int limit = byteBuffer.limit();
/*  806 */           dumpByteArray = byteBuffer.array();
/*  807 */           dumpLength = (limit < dumpByteArray.length) ? limit : dumpByteArray.length;
/*  808 */           dumpOffet = 0;
/*      */         } 
/*      */       } 
/*      */       
/*  812 */       if (dumpByteArray != null) {
/*      */ 
/*      */ 
/*      */         
/*  816 */         int max = (HumanFormatter.this.maxTraceBytes == -1) ? dumpLength : HumanFormatter.this.maxTraceBytes;
/*      */         
/*  818 */         dumpBytes(dumpByteArray, dumpOffet, max, dumpComment);
/*  819 */         justDumpedBytes = true;
/*      */       } 
/*      */       
/*  822 */       return justDumpedBytes;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void formatRecord() {
/*  830 */       this.prefix = newStartOfRecord(true);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  836 */       int level = this.record.getLevel().intValue();
/*  837 */       String tag = "";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  843 */       if (level >= 900) {
/*  844 */         tag = "  W  ";
/*      */       }
/*  846 */       else if (level >= 800) {
/*  847 */         tag = "  i  ";
/*      */       } else {
/*      */         
/*  850 */         tag = "  d  ";
/*      */       } 
/*      */       
/*  853 */       this.buffer.append(tag);
/*  854 */       this.buffer.append(this.record.getSourceMethodName());
/*  855 */       this.buffer.append(" ");
/*  856 */       appendMessage(this.record.getMessage());
/*      */       
/*  858 */       boolean justDumpedBytes = false;
/*      */       
/*  860 */       Object[] parameters = this.record.getParameters();
/*      */       
/*  862 */       if (parameters != null) {
/*      */         
/*  864 */         Object parameter = null;
/*  865 */         StringBuilder value = null;
/*      */         
/*  867 */         for (int i = 0; i < parameters.length; i++) {
/*  868 */           parameter = parameters[i];
/*  869 */           justDumpedBytes = false;
/*      */           
/*  871 */           if (parameter != null) {
/*  872 */             if (parameter instanceof byte[] || parameter instanceof ByteBuffer) {
/*  873 */               justDumpedBytes = processByteBuffers(parameter);
/*      */             }
/*  875 */             else if (parameter instanceof int[]) {
/*  876 */               appendIntArrayInfo((int[])parameter);
/*      */             }
/*  878 */             else if (parameter instanceof Object[]) {
/*  879 */               appendObjectArrayInfo((Object[])parameter);
/*      */             }
/*      */             else {
/*      */               
/*  883 */               value = TraceUtils.formatObject(parameter, HumanFormatter.this.maxTraceBytes);
/*  884 */               this.buffer.append(' ');
/*  885 */               appendPrimitiveInfo(value.toString());
/*      */             } 
/*      */           } else {
/*      */             
/*  889 */             this.buffer.append(" <null>");
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/*  894 */       if (!justDumpedBytes) {
/*  895 */         appendEndOfRecord();
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   class EntryFormatAdapter
/*      */     extends SimpleFormatAdapter
/*      */   {
/*      */     void formatRecord() {
/*  912 */       this.prefix = newStartOfRecord(true);
/*      */       
/*  914 */       this.buffer.append("  {  ");
/*  915 */       this.buffer.append(this.record.getSourceMethodName());
/*      */       
/*  917 */       Object[] parameters = this.record.getParameters();
/*  918 */       if (parameters != null) {
/*      */         
/*  920 */         Object parameter = null;
/*  921 */         StringBuilder value = null;
/*      */         
/*  923 */         for (int i = 0; i < parameters.length; i++) {
/*  924 */           parameter = parameters[i];
/*  925 */           if (parameter != null) {
/*  926 */             if (parameter instanceof byte[]) {
/*  927 */               appendByteArrayInfo((byte[])parameter);
/*      */             }
/*  929 */             else if (parameter instanceof int[]) {
/*  930 */               appendIntArrayInfo((int[])parameter);
/*      */             }
/*  932 */             else if (parameter instanceof Object[]) {
/*  933 */               appendObjectArrayInfo((Object[])parameter);
/*      */             
/*      */             }
/*      */             else {
/*      */               
/*  938 */               value = TraceUtils.formatObject(parameter, HumanFormatter.this.maxTraceBytes);
/*  939 */               this.buffer.append(' ');
/*  940 */               appendPrimitiveInfo(value.toString());
/*      */             } 
/*      */           } else {
/*      */             
/*  944 */             this.buffer.append(" <null>");
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/*  949 */       appendEndOfRecord();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   class ExitFormatAdapter
/*      */     extends SimpleFormatAdapter
/*      */   {
/*      */     void formatRecord() {
/*  965 */       this.prefix = newStartOfRecord(true);
/*      */       
/*  967 */       this.buffer.append("  }  ");
/*  968 */       this.buffer.append(this.record.getSourceMethodName());
/*      */ 
/*      */ 
/*      */       
/*  972 */       Object[] parameters = this.record.getParameters();
/*      */       
/*  974 */       if (parameters != null && parameters.length > 0 && parameters[0] != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  980 */         Object parameter = parameters[0];
/*  981 */         StringBuilder value = null;
/*      */         
/*  983 */         if (parameter instanceof byte[]) {
/*  984 */           this.buffer.append(" returns ");
/*  985 */           appendByteArrayInfo((byte[])parameter);
/*      */         }
/*  987 */         else if (parameter instanceof int[]) {
/*  988 */           this.buffer.append(" returns ");
/*  989 */           appendIntArrayInfo((int[])parameter);
/*      */         }
/*  991 */         else if (parameter instanceof Object[]) {
/*  992 */           this.buffer.append(" returns ");
/*  993 */           appendObjectArrayInfo((Object[])parameter);
/*      */         
/*      */         }
/*      */         else {
/*      */           
/*  998 */           value = TraceUtils.formatObject(parameter, HumanFormatter.this.maxTraceBytes);
/*  999 */           this.buffer.append(" returns ");
/* 1000 */           appendPrimitiveInfo(value.toString());
/*      */         } 
/*      */         
/* 1003 */         Class<?> parameterClass = parameter.getClass();
/* 1004 */         String name = parameterClass.getName();
/* 1005 */         int index = name.indexOf("java.lang.");
/* 1006 */         if (index != -1) {
/* 1007 */           name = name.substring(10);
/* 1008 */           this.buffer.append(' ');
/* 1009 */           this.buffer.append(name);
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1015 */       this.buffer.append(HumanFormatter.lineSeparator);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class FastHashMap
/*      */   {
/* 1029 */     private String cachedKey = null;
/*      */     
/* 1031 */     private String cachedValue = null;
/*      */     
/* 1033 */     protected Map<String, String> map = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public FastHashMap() {
/* 1044 */       this.map = new WeakHashMap<>();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     String get(String key) {
/* 1063 */       if (key == null) {
/* 1064 */         throw new NullPointerException();
/*      */       }
/*      */       
/* 1067 */       if (!keyCached(key)) {
/* 1068 */         this.cachedKey = key;
/* 1069 */         this.cachedValue = this.map.get(key);
/*      */       } 
/*      */       
/* 1072 */       return this.cachedValue;
/*      */     }
/*      */     
/*      */     private boolean keyCached(String key) {
/* 1076 */       return (this.cachedKey != null && this.cachedKey.equals(key));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void put(String key, String value) {
/* 1086 */       if (key == null) {
/* 1087 */         throw new NullPointerException();
/*      */       }
/*      */       
/* 1090 */       this.cachedKey = key;
/* 1091 */       this.cachedValue = value;
/*      */       
/* 1093 */       this.map.put(this.cachedKey, this.cachedValue);
/*      */     }
/*      */   }
/*      */   
/*      */   static class ThreadContext
/*      */   {
/* 1099 */     int collapseRequests = 0;
/* 1100 */     int collapsedLevels = 0;
/*      */     boolean shownThreadData = false;
/*      */     boolean seenThread = false;
/* 1103 */     ArrayList<String> stack = new ArrayList<>();
/* 1104 */     String numericName = null;
/*      */     private static final int THREAD_ID_LENGTH = 8;
/*      */     private static final String THREAD_ID_FORMAT = "%08d";
/* 1107 */     private int numericNameStartIndex = 0;
/*      */     
/*      */     ThreadContext(int localTid) {
/* 1110 */       this.numericName = String.format("%08d", new Object[] { Integer.valueOf(localTid) });
/* 1111 */       this.numericNameStartIndex = this.numericName.length() - 8;
/*      */     } }
/*      */   
/*      */   public HumanFormatter() {
/* 1115 */     this.localThreadContext = new ThreadLocal<ThreadContext>()
/*      */       {
/*      */         protected HumanFormatter.ThreadContext initialValue()
/*      */         {
/* 1119 */           return new HumanFormatter.ThreadContext(++HumanFormatter.this.tid); }
/*      */       };
/*      */     if (this.messageFormatAdapters == null) {
/*      */       this.messageFormatAdapters = new HashMap<>();
/*      */       this.messageFormatAdapters.put("CATCH_BLOCK", new CatchFormatAdapter());
/*      */       this.messageFormatAdapters.put("TRACE_DATA", new DataFormatAdapter());
/*      */       this.messageFormatAdapters.put("METHOD_ENTRY", new EntryFormatAdapter());
/*      */       this.messageFormatAdapters.put("METHOD_EXIT", new ExitFormatAdapter());
/*      */       this.messageFormatAdapters.put("FINALLY_BLOCK", new FinallyFormatAdapter());
/*      */       this.messageFormatAdapters.put("THROWING", new ThrowFormatAdapter());
/*      */     } 
/*      */     this.cachedClassNames = new FastHashMap();
/*      */     this.maxTraceBytes = PropertyStore.getLongPropertyObject("com.ibm.msg.client.commonservices.trace.maxBytes").intValue();
/*      */   } class FinallyFormatAdapter extends SimpleFormatAdapter { void formatRecord() {
/* 1133 */       appendStartOfRecord();
/*      */       
/* 1135 */       this.buffer.append("  f  ");
/* 1136 */       this.buffer.append(this.record.getSourceMethodName());
/*      */       
/* 1138 */       this.buffer.append(HumanFormatter.lineSeparator);
/*      */     } }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract class MessageFormatAdapter
/*      */   {
/*      */     LogRecord record;
/*      */ 
/*      */     
/*      */     StringBuilder buffer;
/*      */ 
/*      */     
/*      */     char[] prefix;
/*      */ 
/*      */     
/*      */     protected static final String catchTag = "  X  ";
/*      */ 
/*      */     
/*      */     protected static final String warningTag = "  W  ";
/*      */ 
/*      */     
/*      */     protected static final String infoTag = "  i  ";
/*      */ 
/*      */     
/*      */     protected static final String dataTag = "  d  ";
/*      */ 
/*      */     
/*      */     protected static final String entryTag = "  {  ";
/*      */ 
/*      */     
/*      */     protected static final String exitTag = "  }  ";
/*      */ 
/*      */     
/*      */     protected static final String finallyTag = "  f  ";
/*      */ 
/*      */     
/*      */     protected static final String throwTag = "  !  ";
/*      */ 
/*      */     
/* 1180 */     final String[] packageNamesFolded = new String[] { "c.i.m.c", "c.i" };
/*      */     
/* 1182 */     final String[] packageNamesToFold = new String[] { "com.ibm.msg.client", "com.ibm" };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1190 */     final int[] packageNamesToFoldLength = new int[] { 18, 7 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void appendByteArrayInfo(byte[] parameter) {
/* 1198 */       this.buffer.append(" <len=");
/* 1199 */       this.buffer.append(parameter.length);
/* 1200 */       this.buffer.append(" bytes>");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void appendIntArrayInfo(int[] parameter) {
/* 1209 */       this.buffer.append(" <len=");
/* 1210 */       this.buffer.append(parameter.length);
/* 1211 */       this.buffer.append(" array=");
/* 1212 */       for (int j = 0; j < parameter.length; j++) {
/* 1213 */         this.buffer.append(parameter[j] + ",");
/*      */       }
/* 1215 */       this.buffer.append(">");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void appendObjectArrayInfo(Object[] parameter) {
/* 1224 */       this.buffer.append(" <len=");
/* 1225 */       this.buffer.append(parameter.length);
/* 1226 */       this.buffer.append(" array=");
/* 1227 */       for (int j = 0; j < parameter.length; j++) {
/* 1228 */         this.buffer.append(", " + parameter[j]);
/*      */       }
/* 1230 */       this.buffer.append(">");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void appendPrimitiveInfo(String parameter) {
/* 1242 */       String primitive = parameter;
/*      */ 
/*      */       
/* 1245 */       if (primitive.indexOf("@") != -1)
/*      */       {
/* 1247 */         for (int i = 0; i < this.packageNamesToFold.length; i++) {
/* 1248 */           if (primitive.startsWith(this.packageNamesToFold[i])) {
/*      */             
/* 1250 */             primitive = this.packageNamesFolded[i] + primitive.substring(this.packageNamesToFoldLength[i]);
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       }
/* 1256 */       if (primitive.indexOf("\n") == -1) {
/* 1257 */         this.buffer.append("[");
/* 1258 */         this.buffer.append(primitive);
/* 1259 */         this.buffer.append("]");
/*      */       
/*      */       }
/* 1262 */       else if (HumanFormatter.this.cachedMultiLinePrimitive == null || !HumanFormatter.this.cachedMultiLinePrimitive.equals(primitive)) {
/* 1263 */         HumanFormatter.this.cachedMultiLinePrimitive = primitive;
/*      */ 
/*      */         
/* 1266 */         String replacedText = primitive.replaceAll(HumanFormatter.lineSeparator, HumanFormatter.lineSeparator + new String(this.prefix));
/* 1267 */         this.buffer.append(HumanFormatter.lineSeparator);
/* 1268 */         this.buffer.append(this.prefix);
/* 1269 */         this.buffer.append("[");
/* 1270 */         this.buffer.append(replacedText);
/* 1271 */         this.buffer.append(HumanFormatter.lineSeparator);
/* 1272 */         this.buffer.append(this.prefix);
/* 1273 */         this.buffer.append("]");
/*      */       }
/*      */       else {
/*      */         
/* 1277 */         this.buffer.append("<as-before>");
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void appendMessage(String message) {
/* 1290 */       if (null == message) {
/* 1291 */         this.buffer.append("<null>");
/*      */       }
/* 1293 */       else if (message.indexOf("\n") == -1) {
/* 1294 */         this.buffer.append(message);
/*      */       } else {
/*      */         
/* 1297 */         this.buffer.append(HumanFormatter.lineSeparator);
/* 1298 */         this.buffer.append(this.prefix);
/* 1299 */         this.buffer.append("[");
/*      */         
/* 1301 */         String replacedText = message.replaceAll("\n", HumanFormatter.lineSeparator + new String(this.prefix));
/* 1302 */         this.buffer.append(replacedText);
/* 1303 */         this.buffer.append(HumanFormatter.lineSeparator);
/* 1304 */         this.buffer.append(this.prefix);
/* 1305 */         this.buffer.append("]");
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     abstract void formatRecord();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void formatRecord(LogRecord record, StringBuilder buffer) {
/* 1325 */       this.record = record;
/* 1326 */       this.buffer = buffer;
/* 1327 */       this.prefix = null;
/*      */ 
/*      */       
/* 1330 */       formatRecord();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract class SimpleFormatAdapter
/*      */     extends MessageFormatAdapter
/*      */   {
/*      */     private static final int majorUnit = 25;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private static final int maxClassNameLength = 80;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     static final int recordStartSize = 120;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void appendClassName(char[] recordStart, int pos, int maxLength, String callSource) {
/* 1360 */       String className = callSource;
/* 1361 */       String superClass = null;
/* 1362 */       String objectId = null;
/* 1363 */       int spaceLeft = maxLength;
/* 1364 */       int currPos = pos;
/*      */       
/* 1366 */       if (className == null) {
/* 1367 */         className = "Unknown Class";
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1372 */       int atIndex = className.indexOf('@');
/* 1373 */       if (atIndex != -1) {
/* 1374 */         objectId = className.substring(atIndex);
/* 1375 */         className = className.substring(0, atIndex);
/*      */       } else {
/*      */         
/* 1378 */         objectId = "   static";
/*      */       } 
/*      */       
/* 1381 */       int braceIndex = className.indexOf('(');
/* 1382 */       if (braceIndex != -1) {
/* 1383 */         superClass = className.substring(braceIndex);
/* 1384 */         className = className.substring(0, braceIndex);
/*      */       } 
/*      */       
/* 1387 */       className = fold(className);
/*      */       
/* 1389 */       System.arraycopy(objectId.toCharArray(), 0, recordStart, currPos, Math.min(objectId.length(), 9));
/* 1390 */       currPos += 11;
/* 1391 */       spaceLeft -= 11;
/*      */       
/* 1393 */       int copyableLength = Math.min(className.length(), spaceLeft);
/* 1394 */       System.arraycopy(className.toCharArray(), 0, recordStart, currPos, copyableLength);
/* 1395 */       currPos += copyableLength;
/* 1396 */       spaceLeft -= copyableLength;
/*      */       
/* 1398 */       if (superClass != null && spaceLeft > 2) {
/* 1399 */         System.arraycopy(superClass.toCharArray(), 0, recordStart, currPos, Math.min(spaceLeft, superClass.length()));
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     private String fold(String className) {
/* 1406 */       String result = HumanFormatter.this.cachedClassNames.get(className);
/* 1407 */       if (result != null) {
/* 1408 */         return result;
/*      */       }
/*      */       
/* 1411 */       String cachedClassName = TraceUtils.foldPackageName(className);
/*      */       
/* 1413 */       HumanFormatter.this.cachedClassNames.put(className, cachedClassName);
/*      */       
/* 1415 */       return cachedClassName;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void appendIndentationString(int depth) {
/* 1440 */       int currentDepth = depth;
/*      */ 
/*      */       
/* 1443 */       HumanFormatter.ThreadContext threadContext = HumanFormatter.this.localThreadContext.get();
/* 1444 */       int localCollapseRequests = threadContext.collapseRequests;
/* 1445 */       int localCollapsedLevels = threadContext.collapsedLevels;
/*      */       
/* 1447 */       if (currentDepth >= 25 * (localCollapsedLevels + 1)) {
/*      */         
/* 1449 */         localCollapseRequests++;
/*      */       } else {
/*      */         
/* 1452 */         localCollapseRequests--;
/*      */       } 
/*      */       
/* 1455 */       if (localCollapseRequests < 0) {
/* 1456 */         localCollapseRequests = 0;
/*      */       }
/*      */ 
/*      */       
/* 1460 */       if (localCollapseRequests >= 32) {
/* 1461 */         localCollapseRequests = 0;
/* 1462 */         localCollapsedLevels++;
/*      */       } 
/*      */ 
/*      */       
/* 1466 */       if (localCollapsedLevels * 25 > currentDepth) {
/* 1467 */         localCollapsedLevels--;
/*      */       }
/*      */       
/* 1470 */       int localWorkingCopy = localCollapsedLevels;
/* 1471 */       while (localWorkingCopy > 0 && currentDepth >= 25) {
/* 1472 */         localWorkingCopy--;
/* 1473 */         currentDepth -= 25;
/* 1474 */         this.buffer.append(":");
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1480 */       while (currentDepth > 0) {
/* 1481 */         int localDepth = (currentDepth <= 5) ? currentDepth : 5;
/*      */         
/* 1483 */         switch (localDepth) {
/*      */           case 1:
/* 1485 */             this.buffer.append("-");
/* 1486 */             currentDepth--;
/*      */           
/*      */           case 2:
/* 1489 */             this.buffer.append("--");
/* 1490 */             currentDepth -= 2;
/*      */           
/*      */           case 3:
/* 1493 */             this.buffer.append("---");
/* 1494 */             currentDepth -= 3;
/*      */           
/*      */           case 4:
/* 1497 */             this.buffer.append("----");
/* 1498 */             currentDepth -= 4;
/*      */           
/*      */           case 5:
/* 1501 */             this.buffer.append("----+");
/* 1502 */             currentDepth -= 5;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       } 
/* 1510 */       threadContext.collapseRequests = localCollapseRequests;
/* 1511 */       threadContext.collapsedLevels = localCollapsedLevels;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void appendThreadFullName(String numericName, Thread thread) {
/* 1522 */       String threadName = thread.getName();
/* 1523 */       int priority = thread.getPriority();
/* 1524 */       String threadGroup = thread.getThreadGroup().getName();
/* 1525 */       ClassLoader cl = thread.getContextClassLoader();
/* 1526 */       String ccl = "<ContextClassLoader is null>";
/* 1527 */       if (cl != null)
/*      */       {
/* 1529 */         ccl = cl.toString();
/*      */       }
/*      */       
/* 1532 */       this.buffer.append(" (thread '");
/* 1533 */       this.buffer.append(numericName);
/* 1534 */       this.buffer.append("'='");
/* 1535 */       this.buffer.append(threadName);
/* 1536 */       this.buffer.append("' priority=");
/* 1537 */       this.buffer.append(priority);
/* 1538 */       this.buffer.append(" group=");
/* 1539 */       this.buffer.append(threadGroup);
/* 1540 */       this.buffer.append(" ");
/* 1541 */       this.buffer.append(ccl);
/* 1542 */       this.buffer.append(")");
/* 1543 */       this.buffer.append(HumanFormatter.lineSeparator);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void appendTimeStamp(char[] myBuffer) {
/* 1552 */       int h = HumanFormatter.calendar.get(11);
/* 1553 */       int m = HumanFormatter.calendar.get(12);
/* 1554 */       int s = HumanFormatter.calendar.get(13);
/* 1555 */       int ms = HumanFormatter.calendar.get(14);
/* 1556 */       int yy = HumanFormatter.calendar.get(1);
/*      */       
/* 1558 */       int mm = HumanFormatter.calendar.get(2) + 1;
/* 1559 */       int dd = HumanFormatter.calendar.get(5);
/*      */       
/* 1561 */       int pos = 0;
/*      */ 
/*      */ 
/*      */       
/* 1565 */       if (dd == HumanFormatter.this.cachedDay && HumanFormatter.this.cachedDateStamp != null) {
/* 1566 */         pos = HumanFormatter.this.cachedDateStamp.length;
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/* 1572 */         HumanFormatter.this.cachedDay = dd;
/* 1573 */         HumanFormatter.this.cachedDateStamp = new char[10];
/* 1574 */         int cdsPos = 0;
/*      */         
/* 1576 */         HumanFormatter.this.cachedDateStamp[cdsPos++] = '[';
/* 1577 */         HumanFormatter.this.cachedDateStamp[cdsPos++] = (char)(48 + dd / 10);
/* 1578 */         dd %= 10;
/* 1579 */         HumanFormatter.this.cachedDateStamp[cdsPos++] = (char)(48 + dd);
/* 1580 */         HumanFormatter.this.cachedDateStamp[cdsPos++] = '/';
/* 1581 */         HumanFormatter.this.cachedDateStamp[cdsPos++] = (char)(48 + mm / 10);
/* 1582 */         mm %= 10;
/* 1583 */         HumanFormatter.this.cachedDateStamp[cdsPos++] = (char)(48 + mm);
/* 1584 */         HumanFormatter.this.cachedDateStamp[cdsPos++] = '/';
/* 1585 */         yy %= 1000;
/* 1586 */         yy %= 100;
/* 1587 */         HumanFormatter.this.cachedDateStamp[cdsPos++] = (char)(48 + yy / 10);
/* 1588 */         yy %= 10;
/* 1589 */         HumanFormatter.this.cachedDateStamp[cdsPos++] = (char)(48 + yy);
/* 1590 */         HumanFormatter.this.cachedDateStamp[cdsPos++] = ' ';
/* 1591 */         pos = HumanFormatter.this.cachedDateStamp.length;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1596 */       if (s == HumanFormatter.this.cachedSecond && m == HumanFormatter.this.cachedMinute && h == HumanFormatter.this.cachedHour && HumanFormatter.this.cachedHourMinuteSecond != null) {
/* 1597 */         pos = HumanFormatter.this.cachedDateStamp.length + HumanFormatter.this.cachedHourMinuteSecond.length;
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 1602 */         HumanFormatter.this.cachedSecond = s;
/* 1603 */         HumanFormatter.this.cachedMinute = m;
/* 1604 */         HumanFormatter.this.cachedHour = h;
/* 1605 */         HumanFormatter.this.cachedHourMinuteSecond = new char[8];
/* 1606 */         int chmsPos = 0;
/*      */         
/* 1608 */         HumanFormatter.this.cachedHourMinuteSecond[chmsPos++] = (char)(48 + h / 10);
/* 1609 */         h %= 10;
/* 1610 */         HumanFormatter.this.cachedHourMinuteSecond[chmsPos++] = (char)(48 + h);
/* 1611 */         HumanFormatter.this.cachedHourMinuteSecond[chmsPos++] = ':';
/*      */         
/* 1613 */         HumanFormatter.this.cachedHourMinuteSecond[chmsPos++] = (char)(48 + m / 10);
/* 1614 */         m %= 10;
/* 1615 */         HumanFormatter.this.cachedHourMinuteSecond[chmsPos++] = (char)(48 + m);
/* 1616 */         HumanFormatter.this.cachedHourMinuteSecond[chmsPos++] = ':';
/*      */         
/* 1618 */         HumanFormatter.this.cachedHourMinuteSecond[chmsPos++] = (char)(48 + s / 10);
/* 1619 */         s %= 10;
/* 1620 */         HumanFormatter.this.cachedHourMinuteSecond[chmsPos++] = (char)(48 + s);
/* 1621 */         pos = HumanFormatter.this.cachedDateStamp.length + HumanFormatter.this.cachedHourMinuteSecond.length;
/*      */       } 
/*      */ 
/*      */       
/* 1625 */       System.arraycopy(HumanFormatter.this.cachedDateStamp, 0, myBuffer, 0, HumanFormatter.this.cachedDateStamp.length);
/* 1626 */       System.arraycopy(HumanFormatter.this.cachedHourMinuteSecond, 0, myBuffer, HumanFormatter.this.cachedDateStamp.length, HumanFormatter.this.cachedHourMinuteSecond.length);
/*      */       
/* 1628 */       myBuffer[pos++] = '.';
/*      */       
/* 1630 */       myBuffer[pos++] = (char)(48 + ms / 100);
/* 1631 */       ms %= 100;
/* 1632 */       myBuffer[pos++] = (char)(48 + ms / 10);
/* 1633 */       ms %= 10;
/* 1634 */       myBuffer[pos++] = (char)(48 + ms);
/*      */       
/* 1636 */       if (ms == HumanFormatter.this.cachedMilliSecond) {
/* 1637 */         HumanFormatter.this.subMilliSecsCounter++;
/*      */         
/* 1639 */         if (HumanFormatter.this.subMilliSecsCounter >= 1296) {
/* 1640 */           HumanFormatter.this.subMilliSecsCounter = 0;
/*      */         }
/*      */       } else {
/*      */         
/* 1644 */         HumanFormatter.this.subMilliSecsCounter = 0;
/*      */       } 
/*      */       
/* 1647 */       char firstSymbol = HumanFormatter.base36chars[HumanFormatter.this.subMilliSecsCounter / 36];
/* 1648 */       char secondSymbol = HumanFormatter.base36chars[HumanFormatter.this.subMilliSecsCounter % 36];
/*      */       
/* 1650 */       myBuffer[pos++] = '.';
/* 1651 */       myBuffer[pos++] = firstSymbol;
/* 1652 */       myBuffer[pos++] = secondSymbol;
/*      */       
/* 1654 */       myBuffer[pos++] = ']';
/*      */       
/* 1656 */       HumanFormatter.this.cachedMilliSecond = ms;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private int updateAndGetDepthCounter(String verb, String signature) {
/* 1668 */       HumanFormatter.ThreadContext threadContext = HumanFormatter.this.localThreadContext.get();
/* 1669 */       if (!threadContext.seenThread) {
/*      */         
/* 1671 */         if ("METHOD_ENTRY".equals(verb)) {
/* 1672 */           threadContext.stack.add(signature);
/*      */         }
/* 1674 */         threadContext.seenThread = true;
/* 1675 */         return 0;
/*      */       } 
/*      */       
/* 1678 */       int depth = 0;
/* 1679 */       if (!threadContext.stack.contains(signature)) {
/*      */         
/* 1681 */         depth = threadContext.stack.size();
/* 1682 */         if ("METHOD_ENTRY".equals(verb)) {
/* 1683 */           threadContext.stack.add(signature);
/*      */         }
/* 1685 */         return depth;
/*      */       } 
/*      */       
/* 1688 */       if ("METHOD_ENTRY".equals(verb)) {
/*      */         
/* 1690 */         depth = threadContext.stack.size();
/* 1691 */         threadContext.stack.add(signature);
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/* 1697 */         for (int idx = threadContext.stack.size() - 1; idx >= 0; idx--) {
/* 1698 */           String signature2 = threadContext.stack.get(idx);
/* 1699 */           if (signature2.equals(signature)) {
/*      */             break;
/*      */           }
/* 1702 */           threadContext.stack.remove(idx);
/*      */         } 
/* 1704 */         if ("METHOD_EXIT".equals(verb))
/*      */         {
/* 1706 */           threadContext.stack.remove(threadContext.stack.size() - 1);
/*      */         }
/* 1708 */         depth = threadContext.stack.size();
/*      */       } 
/* 1710 */       return depth;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void appendEndOfRecord() {
/* 1719 */       HumanFormatter.ThreadContext threadContext = HumanFormatter.this.localThreadContext.get();
/* 1720 */       if (threadContext.shownThreadData) {
/* 1721 */         this.buffer.append(HumanFormatter.lineSeparator);
/*      */       } else {
/*      */         
/* 1724 */         appendThreadFullName(threadContext.numericName, Thread.currentThread());
/* 1725 */         threadContext.shownThreadData = true;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void appendStartOfRecord() {
/* 1734 */       newStartOfRecord(false);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     char[] newStartOfRecord(boolean newBuffer) {
/* 1750 */       char[] recordStart = new char[120];
/* 1751 */       Arrays.fill(recordStart, ' ');
/*      */ 
/*      */       
/* 1754 */       HumanFormatter.calendar.setTimeInMillis(this.record.getMillis());
/* 1755 */       appendTimeStamp(recordStart);
/*      */ 
/*      */       
/* 1758 */       String threadNumericName = (HumanFormatter.this.localThreadContext.get()).numericName;
/* 1759 */       System.arraycopy(threadNumericName.toCharArray(), (HumanFormatter.this.localThreadContext.get()).numericNameStartIndex, recordStart, 28, 8);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1764 */       String callSource = this.record.getSourceClassName();
/* 1765 */       appendClassName(recordStart, 38, 80, callSource);
/*      */ 
/*      */       
/* 1768 */       this.buffer.append(recordStart);
/*      */ 
/*      */ 
/*      */       
/* 1772 */       String signature = this.record.getSourceMethodName();
/* 1773 */       if (null == signature)
/*      */       {
/*      */         
/* 1776 */         signature = "";
/*      */       }
/* 1778 */       if (!signature.endsWith(")") && -1 != signature.indexOf(")"))
/*      */       {
/* 1780 */         signature = signature.substring(0, signature.indexOf(")") + 1);
/*      */       }
/* 1782 */       signature = callSource + signature;
/* 1783 */       int depth = updateAndGetDepthCounter(this.record.getMessage(), signature);
/*      */ 
/*      */       
/* 1786 */       appendIndentationString(depth);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1791 */       char[] result = null;
/* 1792 */       if (newBuffer) {
/* 1793 */         int PREFIX_LENGTH = 37;
/* 1794 */         result = new char[PREFIX_LENGTH];
/* 1795 */         System.arraycopy(recordStart, 0, result, 0, PREFIX_LENGTH);
/*      */       } 
/* 1797 */       return result;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   class ThrowFormatAdapter
/*      */     extends SimpleFormatAdapter
/*      */   {
/*      */     void formatRecord() {
/* 1811 */       this.prefix = newStartOfRecord(true);
/*      */       
/* 1813 */       this.buffer.append("  !  ");
/* 1814 */       this.buffer.append(this.record.getSourceMethodName());
/* 1815 */       this.buffer.append(", ");
/*      */       
/* 1817 */       Throwable thrown = this.record.getThrown();
/* 1818 */       if (thrown == null) {
/* 1819 */         thrown = new Throwable("Unknown Throwable");
/*      */       }
/*      */       
/* 1822 */       appendMessage(thrown.getMessage());
/* 1823 */       this.buffer.append(" [");
/* 1824 */       this.buffer.append(thrown.getClass().getName());
/* 1825 */       this.buffer.append("]");
/* 1826 */       this.buffer.append(HumanFormatter.lineSeparator);
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\j2se\trace\HumanFormatter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */