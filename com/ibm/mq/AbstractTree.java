/*     */ package com.ibm.mq;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ abstract class AbstractTree
/*     */   extends JmqiObject
/*     */ {
/*     */   static {
/* 181 */     if (Trace.isOn) {
/* 182 */       Trace.data("com.ibm.mq.AbstractTree", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/QMTree.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 189 */   private final Collection<TreeElt> leaves = new ArrayList<>();
/*     */ 
/*     */   
/* 192 */   private final Collection<TreeElt> nodes = new ArrayList<>();
/*     */ 
/*     */   
/* 195 */   private TreeElt root = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   AbstractTree(TreeElt root) {
/* 203 */     super(MQSESSION.getJmqiEnv());
/* 204 */     if (Trace.isOn) {
/* 205 */       Trace.entry(this, "com.ibm.mq.AbstractTree", "<init>(TreeElt)", new Object[] { root });
/*     */     }
/* 207 */     if (root == null) {
/* 208 */       NullPointerException traceRet1 = new NullPointerException();
/* 209 */       if (Trace.isOn) {
/* 210 */         Trace.throwing(this, "com.ibm.mq.AbstractTree", "<init>(TreeElt)", traceRet1);
/*     */       }
/* 212 */       throw traceRet1;
/*     */     } 
/*     */ 
/*     */     
/* 216 */     this.root = root;
/*     */     
/* 218 */     this.nodes.add(root);
/*     */     
/* 220 */     this.leaves.add(root);
/*     */     
/* 222 */     if (Trace.isOn) {
/* 223 */       Trace.exit(this, "com.ibm.mq.AbstractTree", "<init>(TreeElt)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   TreeElt root() {
/* 234 */     if (Trace.isOn) {
/* 235 */       Trace.entry(this, "com.ibm.mq.AbstractTree", "root()");
/*     */     }
/* 237 */     if (Trace.isOn) {
/* 238 */       Trace.exit(this, "com.ibm.mq.AbstractTree", "root()", this.root);
/*     */     }
/* 240 */     return this.root;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void addChild(TreeElt elt, Object o) throws QMTreeException {
/* 251 */     if (Trace.isOn) {
/* 252 */       Trace.entry(this, "com.ibm.mq.AbstractTree", "addChild(TreeElt,Object)", new Object[] { elt, o });
/*     */     }
/*     */     
/* 255 */     synchronized (this.root) {
/*     */       
/* 257 */       if (leafContains(o)) {
/*     */         
/* 259 */         DuplicateLeafException traceRet1 = new DuplicateLeafException();
/*     */         
/* 261 */         if (Trace.isOn) {
/* 262 */           Trace.throwing(this, "com.ibm.mq.AbstractTree", "addChild(TreeElt,Object)", traceRet1);
/*     */         }
/* 264 */         throw traceRet1;
/*     */       } 
/*     */ 
/*     */       
/* 268 */       TreeElt newElt = new TreeElt(elt, o);
/*     */       
/* 270 */       elt.addChild(newElt);
/*     */       
/* 272 */       if (elt.children().size() == 1)
/*     */       {
/*     */         
/* 275 */         this.leaves.remove(elt);
/*     */       }
/*     */       
/* 278 */       this.nodes.add(newElt);
/*     */       
/* 280 */       this.leaves.add(newElt);
/*     */     } 
/* 282 */     if (Trace.isOn) {
/* 283 */       Trace.exit(this, "com.ibm.mq.AbstractTree", "addChild(TreeElt,Object)");
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
/*     */   TreeElt getChild(TreeElt elt, Object obj) {
/* 298 */     if (Trace.isOn) {
/* 299 */       Trace.entry(this, "com.ibm.mq.AbstractTree", "getChild(TreeElt,Object)", new Object[] { elt, obj });
/*     */     }
/*     */     
/* 302 */     TreeElt found = null;
/* 303 */     synchronized (this.root) {
/*     */       
/* 305 */       if (elt != null && obj != null)
/*     */       {
/* 307 */         for (TreeElt t : elt.children()) {
/* 308 */           Object o = t.getElement();
/*     */           
/* 310 */           if (obj.equals(o)) {
/* 311 */             found = t;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 319 */     if (Trace.isOn) {
/* 320 */       Trace.exit(this, "com.ibm.mq.AbstractTree", "getChild(TreeElt,Object)", found);
/*     */     }
/* 322 */     return found;
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
/*     */   void removeChild(TreeElt elt, Object o) throws QMTreeException {
/* 335 */     if (Trace.isOn) {
/* 336 */       Trace.entry(this, "com.ibm.mq.AbstractTree", "removeChild(TreeElt,Object)", new Object[] { elt, o });
/*     */     }
/*     */ 
/*     */     
/* 340 */     if (o != null) {
/* 341 */       synchronized (this.root) {
/*     */         
/* 343 */         for (TreeElt t : elt.children()) {
/*     */ 
/*     */           
/* 346 */           if (o.equals(t.getElement())) {
/*     */             
/* 348 */             removeDescendants(t);
/*     */             
/* 350 */             elt.removeChild(t);
/*     */             
/* 352 */             this.nodes.remove(t);
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/* 359 */     if (Trace.isOn) {
/* 360 */       Trace.exit(this, "com.ibm.mq.AbstractTree", "removeChild(TreeElt,Object)");
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
/*     */   boolean leafContains(Object obj) {
/* 373 */     if (Trace.isOn) {
/* 374 */       Trace.entry(this, "com.ibm.mq.AbstractTree", "leafContains(Object)", new Object[] { obj });
/*     */     }
/* 376 */     boolean found = false;
/* 377 */     if (obj != null) {
/* 378 */       synchronized (this.root) {
/*     */         
/* 380 */         for (TreeElt te : this.leaves) {
/* 381 */           Object o = te.getElement();
/* 382 */           if (obj.equals(o)) {
/* 383 */             found = true;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 391 */     if (Trace.isOn) {
/* 392 */       Trace.exit(this, "com.ibm.mq.AbstractTree", "leafContains(Object)", Boolean.valueOf(found));
/*     */     }
/* 394 */     return found;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int size() {
/* 403 */     if (Trace.isOn) {
/* 404 */       Trace.entry(this, "com.ibm.mq.AbstractTree", "size()");
/*     */     }
/* 406 */     int traceRet1 = this.nodes.size();
/*     */     
/* 408 */     if (Trace.isOn) {
/* 409 */       Trace.exit(this, "com.ibm.mq.AbstractTree", "size()", Integer.valueOf(traceRet1));
/*     */     }
/* 411 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 419 */     if (Trace.isOn) {
/* 420 */       Trace.entry(this, "com.ibm.mq.AbstractTree", "toString()");
/*     */     }
/*     */     
/* 423 */     StringBuffer strbf = new StringBuffer();
/* 424 */     synchronized (this.root) {
/* 425 */       strbf.append("Root  : " + this.root + "\n");
/* 426 */       strbf.append("Leaves: " + this.leaves + "\n");
/* 427 */       strbf.append("Nodes : " + this.nodes + "\n\n");
/* 428 */       printChildren(this.root, strbf);
/*     */     } 
/* 430 */     String traceRet1 = strbf.toString();
/* 431 */     if (Trace.isOn) {
/* 432 */       Trace.exit(this, "com.ibm.mq.AbstractTree", "toString()", traceRet1);
/*     */     }
/* 434 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void removeDescendants(TreeElt t) {
/* 444 */     if (Trace.isOn) {
/* 445 */       Trace.entry(this, "com.ibm.mq.AbstractTree", "removeDescendants(TreeElt)", new Object[] { t });
/*     */     }
/*     */ 
/*     */     
/* 449 */     for (TreeElt tn : t.children())
/*     */     {
/* 451 */       removeDescendants(tn);
/*     */     }
/*     */ 
/*     */     
/* 455 */     this.nodes.remove(t);
/*     */     
/* 457 */     if (this.leaves.remove(t))
/*     */     {
/* 459 */       if (t.getParent() != null)
/*     */       {
/* 461 */         if (t.getParent().children().size() == 1) {
/* 462 */           this.leaves.add(t.getParent());
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 467 */     if (Trace.isOn) {
/* 468 */       Trace.exit(this, "com.ibm.mq.AbstractTree", "removeDescendants(TreeElt)");
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
/*     */   private void printChildren(TreeElt t, StringBuffer b) {
/* 480 */     if (Trace.isOn) {
/* 481 */       Trace.entry(this, "com.ibm.mq.AbstractTree", "printChildren(TreeElt,StringBuffer)", new Object[] { t, b });
/*     */     }
/*     */ 
/*     */     
/* 485 */     b.append("node '" + t + "', depth " + t.depth() + " ");
/*     */     
/* 487 */     Collection<TreeElt> c = t.children();
/*     */     
/* 489 */     if (c.size() == 0) {
/* 490 */       b.append(", no children\n");
/*     */     } else {
/*     */       
/* 493 */       b.append(", " + c.size() + " children " + c + "\n");
/* 494 */       for (TreeElt nt : c) {
/* 495 */         printChildren(nt, b);
/*     */       }
/*     */     } 
/*     */     
/* 499 */     if (Trace.isOn)
/* 500 */       Trace.exit(this, "com.ibm.mq.AbstractTree", "printChildren(TreeElt,StringBuffer)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\AbstractTree.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */