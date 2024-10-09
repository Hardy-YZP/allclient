/*     */ package com.ibm.disthub2.impl.client;
/*     */ 
/*     */ import com.ibm.disthub2.impl.formats.Framing;
/*     */ import com.ibm.disthub2.impl.formats.MessageEncrypter;
/*     */ import com.ibm.disthub2.impl.formats.MessageHandle;
/*     */ import com.ibm.disthub2.impl.util.DoPrivileged;
/*     */ import com.ibm.disthub2.impl.util.ExceptionWrapper;
/*     */ import com.ibm.disthub2.spi.ExceptionBuilder;
/*     */ import com.ibm.disthub2.spi.ExceptionConstants;
/*     */ import com.ibm.disthub2.spi.LogConstants;
/*     */ import com.ibm.disthub2.spi.Principal;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.net.Socket;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class NoSecurity
/*     */   implements Security, LogConstants, ExceptionConstants
/*     */ {
/*  81 */   private static final DebugObject debug = new DebugObject("NoSecurity");
/*     */   
/*  83 */   private static final byte[] hello = new byte[] { 0, 1, -34, -19, 88, 72, 0, 2, 0, 99, 0, 67 };
/*     */ 
/*     */ 
/*     */   
/*  87 */   private static final byte[] reply = new byte[] { 0, 1, -34, -19, 0, 85 };
/*     */ 
/*     */   
/*  90 */   protected Socket toAuth = null;
/*     */   
/*     */   private BaseConfig baseConfig;
/*     */ 
/*     */   
/*     */   public NoSecurity(BaseConfig config) {
/*  96 */     this.baseConfig = config;
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
/*     */   public Principal createPrincipal(String user, String passwd) {
/* 110 */     return new NoSecUsername((user == null) ? "" : user, (passwd == null) ? "" : passwd);
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
/*     */   public int authorize(Socket socket, Principal creds) throws IOException {
/* 122 */     if (debug.debugIt(32)) {
/* 123 */       debug.debug(-165922073994779L, "authorize", socket);
/*     */     }
/*     */     
/* 126 */     if (this.baseConfig.AUTH_PROTOCOLS.indexOf('P') < 0)
/* 127 */       throw new IOException(ExceptionBuilder.buildReasonString(999632461, null)); 
/* 128 */     if (!(creds instanceof NoSecUsername)) {
/* 129 */       throw new IOException(ExceptionBuilder.buildReasonString(-23657438, new Object[] { "NoSecUsername", creds
/* 130 */               .getClass().toString() }));
/*     */     }
/* 132 */     NoSecUsername ID = (NoSecUsername)creds;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 137 */     if (this.baseConfig.AUTH_TIMEOUT > 0L)
/* 138 */       synchronized (this) {
/* 139 */         this.toAuth = socket;
/* 140 */         if (this.baseConfig.THREADER != null) {
/* 141 */           this.baseConfig.THREADER.schedule(new AuthTimer(this, this.baseConfig.AUTH_TIMEOUT));
/*     */         } else {
/* 143 */           DoPrivileged.createAndStartThread(new AuthTimer(this, this.baseConfig.AUTH_TIMEOUT), true);
/*     */         } 
/*     */       }  
/* 146 */     DataInputStream in = new DataInputStream(socket.getInputStream());
/* 147 */     DataOutputStream out = new DataOutputStream(socket.getOutputStream());
/* 148 */     out.write(hello);
/* 149 */     if (in.readInt() != 122605)
/* 150 */       throw new IOException(ExceptionBuilder.buildReasonString(-402291761, null)); 
/* 151 */     short protocol = in.readShort();
/* 152 */     if (protocol != 99 && protocol != 67)
/* 153 */       throw new IOException(ExceptionBuilder.buildReasonString(698806460, null)); 
/* 154 */     out.write(reply);
/* 155 */     out.writeUTF(ID.m_login);
/* 156 */     out.writeUTF(ID.m_passwd);
/*     */     
/* 158 */     out.writeUTF("[release=1.2]");
/* 159 */     if (in.readInt() != 122605)
/* 160 */       throw new IOException(ExceptionBuilder.buildReasonString(-402291761, null)); 
/* 161 */     short answer = in.readShort();
/* 162 */     if (answer != 111 && answer != 79) {
/* 163 */       throw new IOException(ExceptionBuilder.buildReasonString(1472973823, null));
/*     */     }
/*     */ 
/*     */     
/* 167 */     if (this.baseConfig.AUTH_TIMEOUT > 0L) {
/* 168 */       synchronized (this) {
/* 169 */         this.toAuth = null;
/* 170 */         notifyAll();
/*     */       } 
/*     */     }
/*     */     
/* 174 */     int result = 65536;
/* 175 */     if (answer == 111) {
/*     */       
/* 177 */       in.readUTF();
/* 178 */       String xid = in.readUTF();
/* 179 */       int start = xid.indexOf("[release=");
/* 180 */       if (start < 0) return 65536; 
/* 181 */       start += "[release=".length();
/* 182 */       int end = xid.indexOf('.', start);
/* 183 */       int major = Integer.parseInt(xid.substring(start, end));
/* 184 */       start = end + 1;
/* 185 */       end = xid.indexOf(']', start);
/* 186 */       int minor = Integer.parseInt(xid.substring(start, end));
/* 187 */       result = major << 16 | minor;
/*     */     } 
/*     */     
/* 190 */     if (debug.debugIt(64)) {
/* 191 */       debug.debug(-142394261359015L, "authorize", Integer.valueOf(result));
/*     */     }
/* 193 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MessageEncrypter incoming(byte[] msg) throws IOException {
/* 200 */     if (debug.debugIt(32)) {
/* 201 */       debug.debug(-165922073994779L, "incoming", msg);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 206 */     if (debug.debugIt(64)) {
/* 207 */       debug.debug(-142394261359015L, "incoming", (Object)null);
/*     */     }
/* 209 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] outgoing(MessageHandle msg, byte qop) throws IOException {
/* 216 */     if (debug.debugIt(32)) {
/* 217 */       debug.debug(-165922073994779L, "outgoing", msg, Byte.valueOf(qop));
/*     */     }
/*     */ 
/*     */     
/* 221 */     if (qop != 1)
/* 222 */       throw new IOException(ExceptionBuilder.buildReasonString(999632461, null)); 
/* 223 */     int overhead = Framing.overhead(qop, null, false);
/* 224 */     byte[] result = Framing.preFrame(msg, overhead, null);
/* 225 */     Framing.frameMessage(result, msg.getInterpreterId(), msg.getEncodingSchema().getId(), result.length);
/*     */ 
/*     */     
/* 228 */     if (debug.debugIt(64)) {
/* 229 */       debug.debug(-142394261359015L, "outgoing", result);
/*     */     }
/* 231 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] framePropagationMessage(byte[] msg) throws IOException {
/* 238 */     if (debug.debugIt(32)) {
/* 239 */       debug.debug(-165922073994779L, "framePropagationMessage", msg);
/*     */     }
/*     */     
/* 242 */     int overhead = Framing.overhead((byte)1, null, true);
/* 243 */     byte[] result = new byte[msg.length + overhead];
/* 244 */     System.arraycopy(msg, 0, result, overhead, msg.length);
/* 245 */     Framing.framePropagationMessage(result, result.length);
/*     */     
/* 247 */     if (debug.debugIt(64)) {
/* 248 */       debug.debug(-142394261359015L, "framePropagationMessage", result);
/*     */     }
/* 250 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte getQop(MessageHandle msg) {
/* 257 */     if (debug.debugIt(32)) {
/* 258 */       debug.debug(-165922073994779L, "getQop", msg);
/*     */     }
/* 260 */     byte result = 1;
/*     */     
/* 262 */     if (debug.debugIt(64)) {
/* 263 */       debug.debug(-142394261359015L, "getQop", Byte.valueOf(result));
/*     */     }
/* 265 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void qopUpdate(MessageHandle msg) throws IOException {
/* 272 */     if (debug.debugIt(32)) {
/* 273 */       debug.debug(-165922073994779L, "qopUpdate", msg);
/*     */     }
/* 275 */     throw new IOException(ExceptionBuilder.buildReasonString(999632461, null));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public class NoSecUsername
/*     */     implements Principal
/*     */   {
/*     */     protected String m_login;
/*     */ 
/*     */     
/*     */     protected String m_passwd;
/*     */ 
/*     */     
/*     */     public NoSecUsername(String login, String passwd) {
/* 290 */       this.m_login = login; this.m_passwd = passwd;
/* 291 */     } public String toString() { return this.m_login; }
/* 292 */     public int hashCode() { return this.m_login.hashCode(); } public String getName() {
/* 293 */       return this.m_login;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean equals(Object o) {
/*     */       try {
/* 300 */         NoSecUsername u = (NoSecUsername)o;
/* 301 */         return this.m_login.equals(u.m_login);
/* 302 */       } catch (Exception exception) {
/*     */ 
/*     */         
/* 305 */         return false;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static class AuthTimer
/*     */     implements Runnable
/*     */   {
/*     */     private NoSecurity instance;
/*     */ 
/*     */     
/*     */     private long time;
/*     */ 
/*     */     
/*     */     public AuthTimer(NoSecurity I, long T) {
/* 322 */       this.instance = I; this.time = T;
/*     */     }
/*     */     
/*     */     public void run() {
/*     */       try {
/* 327 */         synchronized (this.instance) {
/* 328 */           this.instance.wait(this.time);
/* 329 */           if (this.instance.toAuth != null) {
/*     */             
/* 331 */             try { this.instance.toAuth.shutdownInput(); } catch (Throwable throwable) {} 
/* 332 */             try { this.instance.toAuth.shutdownOutput(); } catch (Throwable throwable) {}
/* 333 */             this.instance.toAuth.close();
/*     */           } 
/*     */         } 
/* 336 */       } catch (Throwable x) {
/* 337 */         if (Logger.logIt(1198397518584002L))
/* 338 */           Logger.log(1198397518584002L, "NoSecurity", new Object[] { new ExceptionWrapper(x) }); 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\client\NoSecurity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */