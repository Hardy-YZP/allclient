/*
 *   <copyright 
 *   notice="lm-source-program" 
 *   pids="5724-H72,5655-R36,5655-L82,5724-L26," 
 *   years="2008,2015" 
 *   crc="2318247419" >
 *   Licensed Materials - Property of IBM  
 *    
 *   5724-H72,5655-R36,5655-L82,5724-L26, 
 *    
 *   (C) Copyright IBM Corp. 2008, 2015 All Rights Reserved.  
 *    
 *   US Government Users Restricted Rights - Use, duplication or  
 *   disclosure restricted by GSA ADP Schedule Contract with  
 *   IBM Corp.  
 *   </copyright> 
 */

/**
 * "JmqiBrowse" is passed the name of a queue manager and a queue. 
 * It then reads each message from the ueue and outputs the following 
 * to the stdout        
 *      -  Formatted message descriptor fields          
 *      -  Optionally any other formatted message       
 *         properties                                   
 *      -  Message data (dumped in hex and, where       
 *         possible, character format)                                                            
 *
 *
 *
 * "JmqiBrowse" has the following parameters                          
 *       required:                                                  
 *                 (1) The name of the source queue                 
 *       optional:                                                  
 *                 (2) Queue manager name                           
 *                 (3) The open options                             
 *                 (4) The close options                            
 *                                                                                                         
 *
 * To connect to the Queue manager in Client mode, specify "MQSERVER" as an environment variable 
 * or a system parameter as follows:
 *     MQSERVER=ChannelName/TransportType/ConnectionName                                                                                                         
 *                                                                                                         
 *                                                      
 *                                                                                                         
 * For example:
 *     bindings mode:
 *         java com.ibm.mq.jmqi.samples.JmqiBrowse QUEUE QMGR
 *         
 *     client mode:
 *         java -DMQSERVER=SYSTEM.DEF.SVRCONN/TCP/localhost(1414) com.ibm.mq.jmqi.samples.JmqiBrowse QUEUE QMGR          
 */
package com.ibm.mq.jmqi.samples;

// Trace instrumented 28-May-2015 16:00:35  // AUTOINSERTEDTRACE

import java.nio.ByteBuffer;

import com.ibm.mq.constants.CMQC;
import com.ibm.mq.jmqi.JmqiDefaultPropertyHandler;
import com.ibm.mq.jmqi.JmqiDefaultThreadPoolFactory;
import com.ibm.mq.jmqi.JmqiEnvironment;
import com.ibm.mq.jmqi.JmqiFactory;
import com.ibm.mq.jmqi.JmqiMQ;
import com.ibm.mq.jmqi.JmqiPropertyHandler;
import com.ibm.mq.jmqi.JmqiThreadPoolFactory;
import com.ibm.mq.jmqi.JmqiUtils;
import com.ibm.mq.jmqi.MQCNO;
import com.ibm.mq.jmqi.MQGMO;
import com.ibm.mq.jmqi.MQMD;
import com.ibm.mq.jmqi.MQOD;
import com.ibm.mq.jmqi.handles.Hconn;
import com.ibm.mq.jmqi.handles.Hobj;
import com.ibm.mq.jmqi.handles.Phconn;
import com.ibm.mq.jmqi.handles.Phobj;
import com.ibm.mq.jmqi.handles.Pint;
import com.ibm.msg.client.commonservices.trace.Trace;

/**
 * Sample program to put a message to a queue
 */
public class JmqiBrowse extends SampleFramework {

  static { // AUTOINSERTEDTRACE
    if (Trace.isOn) {
      Trace.data("com.ibm.mq.jmqi.samples.JmqiBrowse", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/samples/JmqiBrowse.java"
          );
    }
  }
  // AUTOINSERTEDTRACE

  /** The SCCSID which is expanded when the file is extracted from CMVC */
  public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/samples/JmqiBrowse.java";

  /**
   * Run the sample JmqiBrowse program
   * 
   * @param args
   */
  public static void main(String[] args) {
    if (Trace.isOn) { // AUTOINSERTEDTRACE
      Trace.entry("com.ibm.mq.jmqi.samples.JmqiBrowse", "main(String [ ])", new Object[]{args});
    }

    System.out.println("JmqiBrowse Start");

    try {
      JmqiBrowse program = new JmqiBrowse();
      program.perform(args);
    }
    catch (Exception e) {
      if (Trace.isOn) { // AUTOINSERTEDTRACE
        Trace.catchBlock("com.ibm.mq.jmqi.samples.JmqiBrowse", "main(String [ ])", e);
      }
      e.printStackTrace();
    }

    System.out.println("JmqiBrowse End");
    if (Trace.isOn) { // AUTOINSERTEDTRACE
      Trace.exit("com.ibm.mq.jmqi.samples.JmqiBrowse", "main(String [ ])");
    }
    return;
  }

  /**
   * Run the program
   * 
   * @param args
   * @throws Exception
   */
  public void perform(String[] args) throws Exception {
    if (Trace.isOn) { // AUTOINSERTEDTRACE
      Trace.entry(this, "com.ibm.mq.jmqi.samples.JmqiBrowse", "perform(String [ ])", new Object[]{
          args});
    }

    // ****************************************************************
    // * Get the input parameters
    // ****************************************************************
    setOpenOptions(CMQC.MQOO_BROWSE);
    parseCommandLineArgs(args, 1, 4);
    parseSystemProperties();

    // ****************************************************************
    // * Initialise the Jmqi
    // ****************************************************************
    JmqiThreadPoolFactory threadPool = new JmqiDefaultThreadPoolFactory();
    JmqiPropertyHandler propertyHandler = new JmqiDefaultPropertyHandler();
    JmqiEnvironment env = JmqiFactory.getInstance(threadPool, propertyHandler);

    JmqiMQ mq = getMQInstance(env);

    Pint cc = env.newPint(0);
    Pint rc = env.newPint(0);

    byte[] buffer = new byte[4096];
    ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
    int bufferLength = buffer.length;
    Pint dataLength = env.newPint(0);

    // ****************************************************************
    // * Connect to queue manager
    // ****************************************************************
    Phconn phconn = env.newPhconn();
    String qmname = getQueueManagerName();
    MQCNO connectOptions = getConnectOptions(env);
    mq.MQCONNX(qmname, connectOptions, phconn, cc, rc);
    if (rc.x != CMQC.MQRC_NONE) {
      Exception traceRet1 = new Exception("MQCONN ended with reason code " + rc.x);
      if (Trace.isOn) { // AUTOINSERTEDTRACE
        Trace.throwing(this, "com.ibm.mq.jmqi.samples.JmqiBrowse", "perform(String [ ])",
            traceRet1, 1);
      }
      throw traceRet1;
    }
    Hconn hconn = phconn.getHconn();

    try {
      // ****************************************************************
      // * Open the named message queue for input; exclusive or shared
      // * use of the queue is controlled by the queue definition here
      // ****************************************************************
      MQOD mqod = env.newMQOD();
      mqod.setObjectName(getQueueName());
      int options = getOpenOptions();
      Phobj phobj = env.newPhobj();
      mq.MQOPEN(hconn, mqod, options, phobj, cc, rc);
      if (rc.x != CMQC.MQRC_NONE) {
        Exception traceRet2 = new Exception("MQOPEN ended with reason code " + rc.x);
        if (Trace.isOn) { // AUTOINSERTEDTRACE
          Trace.throwing(this, "com.ibm.mq.jmqi.samples.JmqiBrowse", "perform(String [ ])",
              traceRet2, 2);
        }
        throw traceRet2;
      }
      Hobj hobj = phobj.getHobj();

      // ****************************************************************
      // * Get messages from the message queue
      // * Loop until there is a failure
      // ****************************************************************
      MQMD mqmd = env.newMQMD();
      mqmd.setFormat(CMQC.MQFMT_STRING);

      MQGMO mqgmo = env.newMQGMO();
      mqgmo.setVersion(CMQC.MQGMO_VERSION_2);
      mqgmo.setMatchOptions(CMQC.MQMO_NONE);
      mqgmo.setOptions(CMQC.MQGMO_NO_WAIT | CMQC.MQGMO_BROWSE_NEXT);

      int count = 1;
      boolean more = true;
      while (more) {
        mq.MQGET(hconn, hobj, mqmd, mqgmo, bufferLength, byteBuffer, dataLength, cc, rc);
        switch (rc.x) {
          case CMQC.MQRC_NONE :
            System.out.println("");
            System.out.println("MQGET of message number " + count++);
            System.out.println("****Message descriptor****");
            System.out.println(mqmd.toStringMultiLine());
            System.out.println("****   Message      ****");
            StringBuffer dumpBuffer = new StringBuffer();
            JmqiUtils.hexDump(null, byteBuffer, 0, dataLength.x, dumpBuffer);
            System.out.println(dumpBuffer.toString());
            break;
          case CMQC.MQRC_NO_MSG_AVAILABLE :
            System.out.println("no more messages");
            more = false;
            break;
          default : {
            Exception traceRet3 = new Exception("MQGET ended with reason code " + rc.x);
            if (Trace.isOn) { // AUTOINSERTEDTRACE
              Trace.throwing(this, "com.ibm.mq.jmqi.samples.JmqiBrowse", "perform(String [ ])",
                  traceRet3, 3);
            }
            throw traceRet3;
          }
        }
        byteBuffer.clear();
      }

      // ****************************************************************
      // * Close the source queue (if it was opened)
      // ****************************************************************
      options = getCloseOptions();
      mq.MQCLOSE(hconn, phobj, options, cc, rc);
      if (rc.x != CMQC.MQRC_NONE) {
        Exception traceRet4 = new Exception("MQCLOSE ended with reason code " + rc.x);
        if (Trace.isOn) { // AUTOINSERTEDTRACE
          Trace.throwing(this, "com.ibm.mq.jmqi.samples.JmqiBrowse", "perform(String [ ])",
              traceRet4, 4);
        }
        throw traceRet4;
      }
    }
    finally {
      if (Trace.isOn) { // AUTOINSERTEDTRACE
        Trace.finallyBlock(this, "com.ibm.mq.jmqi.samples.JmqiBrowse", "perform(String [ ])");
      }
      // ****************************************************************
      // * Make sure to disconnect from the Queue Manager
      // ****************************************************************
      mq.MQDISC(phconn, cc, rc);
      if (rc.x != CMQC.MQRC_NONE) {
        Exception traceRet5 = new Exception("MQDISC ended with reason code " + rc.x);
        if (Trace.isOn) { // AUTOINSERTEDTRACE
          Trace.throwing(this, "com.ibm.mq.jmqi.samples.JmqiBrowse", "perform(String [ ])",
              traceRet5, 5);
        }
        throw traceRet5;
      }
    }
    if (Trace.isOn) { // AUTOINSERTEDTRACE
      Trace.exit(this, "com.ibm.mq.jmqi.samples.JmqiBrowse", "perform(String [ ])");
    }
    return;
  }
}
