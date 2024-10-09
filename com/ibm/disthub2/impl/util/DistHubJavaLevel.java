/*     */ package com.ibm.disthub2.impl.util;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DistHubJavaLevel
/*     */ {
/*     */   private static final String copyrightNotice = "Licensed Materials - Property of IBM (c) Copyright IBM Corp. 2004. All Rights Reserved. US Government Users Restricted Rights - Use, duplication or disclosure restricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   private static String title;
/*     */   private static String spec;
/*     */   private static String vendor;
/*     */   private static String version;
/*     */   private static String disthubversion;
/*     */   private static String cmvclevel;
/*     */   private static String buildtype;
/*     */   public static final String PACKAGE_TITLE = "Package:     ";
/*     */   public static final String SPEC_TITLE = "Implements:  ";
/*     */   public static final String VENDOR_TITLE = "By:          ";
/*     */   public static final String VERSION_TITLE = "Version      ";
/*     */   public static final String CMVCLEVEL_TITLE = "Build Level: ";
/*     */   public static final String BUILDTYPE_TITLE = "Build Type:  ";
/*     */   public static final String NAME_TITLE = "Name:        ";
/*     */   private static boolean printUsage = false;
/*     */   private static boolean printVerbose = false;
/*     */   private static boolean printLevel = false;
/*     */   private static boolean printType = false;
/*     */   private static boolean printTitle = false;
/*     */   private static boolean printRelease = false;
/*     */   
/*     */   static {
/* 139 */     title = "Webscale Distribution Hub Core";
/* 140 */     spec = "Webscale Distribution Hub Core";
/* 141 */     vendor = "IBM Corporation";
/* 142 */     version = "DH000-L50930";
/* 143 */     disthubversion = "DH000";
/* 144 */     cmvclevel = "DH000-L50930";
/* 145 */     buildtype = "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void printData() {
/* 154 */     if (printUsage) {
/* 155 */       System.out.println("com.ibm.disthub2.impl.util.DistHubJavaLevel");
/* 156 */       System.out.println();
/* 157 */       System.out.println("  Reports DistHub component build details");
/* 158 */       System.out.println();
/* 159 */       System.out.println("   Command line parameters supported are:");
/* 160 */       System.out.println();
/* 161 */       System.out.println("\t-v will print entire text of the manifest and extract the RELEASE");
/* 162 */       System.out.println("\t   LEVEL and BTYPE from the version string");
/* 163 */       System.out.println("\t-r for the RELEASE");
/* 164 */       System.out.println("\t-l for the LEVEL");
/* 165 */       System.out.println("\t-b for the BTYPE");
/* 166 */       System.out.println("\t-? for a usage message");
/*     */     
/*     */     }
/* 169 */     else if (printVerbose) {
/* 170 */       System.out.println("Package:     " + title);
/* 171 */       System.out.println("Implements:  " + spec);
/* 172 */       System.out.println("By:          " + vendor);
/* 173 */       System.out.println("Version      " + version);
/* 174 */       System.out.println("Build Level: " + disthubversion);
/* 175 */       System.out.println("Build Type:  " + buildtype);
/* 176 */       System.out.println("Build Level: " + cmvclevel);
/*     */     }
/*     */     else {
/*     */       
/* 180 */       if (printRelease) {
/* 181 */         System.out.println("DistHub Release: " + disthubversion);
/*     */       }
/* 183 */       if (printType) {
/* 184 */         System.out.println("DistHub Build Type: " + buildtype);
/*     */       }
/* 186 */       if (printLevel) {
/* 187 */         System.out.println("DistHub Build Level: " + cmvclevel);
/*     */       }
/* 189 */       if (printTitle) {
/* 190 */         System.out.println(title);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void parseCommandLine(String[] args) {
/* 209 */     if (args == null || args.length == 0 || args[0] == null || args[0].trim().length() == 0) {
/*     */       
/* 211 */       printVerbose = true;
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 217 */     int i = 0;
/*     */     
/* 219 */     while (i < args.length) {
/*     */       
/* 221 */       String thisArg = args[i];
/*     */       
/* 223 */       if (thisArg.startsWith("-")) {
/* 224 */         switch (thisArg.charAt(1)) {
/*     */           case 'v':
/* 226 */             printVerbose = true;
/*     */             break;
/*     */           
/*     */           case '?':
/* 230 */             printUsage = true;
/*     */             break;
/*     */           
/*     */           case 'r':
/* 234 */             printRelease = true;
/*     */             break;
/*     */           
/*     */           case 'l':
/* 238 */             printLevel = true;
/*     */             break;
/*     */           
/*     */           case 'b':
/* 242 */             printType = true;
/*     */             break;
/*     */           
/*     */           case 'p':
/* 246 */             printTitle = true;
/*     */             break;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/* 254 */       i++;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 262 */     parseCommandLine(args);
/* 263 */     printData();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getBuildType() {
/* 271 */     return buildtype;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setBuildType(String buildtype) {
/* 278 */     DistHubJavaLevel.buildtype = buildtype;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getBuildLevel() {
/* 285 */     return cmvclevel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setCmvcLevel(String cmvclevel) {
/* 292 */     DistHubJavaLevel.cmvclevel = cmvclevel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getDisthubVersion() {
/* 299 */     return disthubversion;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setDisthubVersion(String disthubversion) {
/* 306 */     DistHubJavaLevel.disthubversion = disthubversion;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getSpec() {
/* 313 */     return spec;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setSpec(String spec) {
/* 320 */     DistHubJavaLevel.spec = spec;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getTitle() {
/* 327 */     return title;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setTitle(String title) {
/* 334 */     DistHubJavaLevel.title = title;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getVendor() {
/* 341 */     return vendor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setVendor(String vendor) {
/* 348 */     DistHubJavaLevel.vendor = vendor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getVersion() {
/* 355 */     return version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setVersion(String version) {
/* 362 */     DistHubJavaLevel.version = version;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\imp\\util\DistHubJavaLevel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */