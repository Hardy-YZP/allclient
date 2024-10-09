/*     */ package com.ibm.msg.client.commonservices.util;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GeneralTree<T>
/*     */ {
/*     */   static class Node<T>
/*     */   {
/*     */     T element;
/*     */     Map<T, Node<T>> subNodes;
/*     */     
/*     */     Node(T element) {
/*  60 */       this.element = element;
/*  61 */       this.subNodes = new HashMap<>();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     Node<T> subNodeForelement(T element1, boolean add) {
/*  71 */       Node<T> result = this.subNodes.get(element1);
/*  72 */       if (result == null && add) {
/*  73 */         result = new Node(element1);
/*  74 */         this.subNodes.put(element1, result);
/*     */       } 
/*  76 */       return result;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void dump(int indent, PrintStream p) {
/*  85 */       if (this.element != null) {
/*  86 */         char[] indentChars = new char[indent];
/*  87 */         for (int i = 0; i < indentChars.length; i++) {
/*  88 */           indentChars[i] = (i % 5 == 0) ? '+' : '-';
/*     */         }
/*  90 */         p.format("%s%s%n", new Object[] { new String(indentChars), String.valueOf(this.element) });
/*     */       } 
/*  92 */       for (Node<T> subNode : this.subNodes.values()) {
/*  93 */         subNode.dump(indent + 2, p);
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean isEmpty() {
/* 102 */       return this.subNodes.isEmpty();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/* 107 */   Node<T> rootNode = null;
/*     */ 
/*     */   
/* 110 */   T wildCard = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GeneralTree(T wildCard) {
/* 117 */     if (wildCard == null) {
/* 118 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/* 121 */     this.rootNode = new Node<>(null);
/* 122 */     this.wildCard = wildCard;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GeneralTree() {
/* 129 */     this.rootNode = new Node<>(null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addSubElements(List<T> subElements) {
/* 137 */     validate(subElements, true);
/*     */     
/* 139 */     Node<T> currentNode = this.rootNode;
/* 140 */     for (T subElement : subElements) {
/* 141 */       currentNode = currentNode.subNodeForelement(subElement, true);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void validate(List<T> subElements, boolean wildCardPermitted) {
/* 151 */     if (subElements == null) {
/* 152 */       throw new NullPointerException();
/*     */     }
/*     */     
/* 155 */     boolean seenWildCard = false;
/* 156 */     for (T subElement : subElements) {
/* 157 */       if (subElement == null) {
/* 158 */         throw new NullPointerException();
/*     */       }
/* 160 */       if (seenWildCard) {
/* 161 */         throw new IllegalArgumentException();
/*     */       }
/* 163 */       if (subElement.equals(this.wildCard)) {
/* 164 */         if (wildCardPermitted) {
/* 165 */           seenWildCard = true;
/*     */           continue;
/*     */         } 
/* 168 */         throw new IllegalArgumentException();
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
/*     */   public boolean contains(List<T> subElements) {
/* 180 */     validate(subElements, false);
/*     */     
/* 182 */     Node<T> currentNode = this.rootNode;
/* 183 */     Iterator<T> it = subElements.iterator();
/* 184 */     while (it.hasNext()) {
/* 185 */       T subElement = it.next();
/* 186 */       Node<T> nextNode = currentNode.subNodeForelement(subElement, false);
/* 187 */       if (nextNode == null) {
/* 188 */         if (this.wildCard != null && !it.hasNext()) {
/* 189 */           nextNode = currentNode.subNodeForelement(this.wildCard, false);
/* 190 */           if (nextNode != null) {
/* 191 */             return true;
/*     */           }
/* 193 */           return false;
/*     */         } 
/* 195 */         return false;
/*     */       } 
/* 197 */       currentNode = nextNode;
/*     */     } 
/*     */     
/* 200 */     return currentNode.isEmpty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dump(PrintStream ps) {
/* 208 */     this.rootNode.dump(0, ps);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservice\\util\GeneralTree.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */