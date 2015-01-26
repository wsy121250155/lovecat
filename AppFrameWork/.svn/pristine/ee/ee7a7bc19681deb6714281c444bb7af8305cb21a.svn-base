/*     */ package com.mobile.base.tools;
/*     */ 
/*     */ import android.app.Activity;
/*     */ import android.content.Context;
/*     */ import android.view.Display;
/*     */ import android.view.ViewConfiguration;
/*     */ import java.util.UUID;
/*     */ 
/*     */ public class Device
/*     */ {
/*  26 */   public static String LOGID = UUID.randomUUID().toString();
/*     */   private static String METICS_S;
/*  30 */   private static int NETWORKSPEED = 0; private static int TOUCHSLOP = 10;
/*  31 */   private static long LASTGETNETWORK = 0L;
/*     */ 
/*  33 */   private static int METICS_W = 800; private static int METICS_H = 600;
/*  34 */   private static float DENSITY = 2.0F;
/*     */ 
/*     */   public static synchronized int getMeticsW(Context mcontext)
/*     */   {
/*  48 */     Context context = mcontext;
/*  49 */     if ((METICS_S == null) && ((context instanceof Activity))) {
/*  50 */       Activity act = (Activity)context;
/*  51 */       Display dis = act.getWindowManager().getDefaultDisplay();
/*  52 */       METICS_W = dis.getWidth();
/*  53 */       METICS_H = dis.getHeight();
/*  54 */       METICS_S = "";
/*     */     }
/*  56 */     if (METICS_S == null) {
/*  57 */       DENSITY = context.getResources().getDisplayMetrics().density;
/*     */     }
/*  59 */     ViewConfiguration configuration = ViewConfiguration.get(context);
/*  60 */     TOUCHSLOP = configuration.getScaledTouchSlop();
/*  61 */     return METICS_W;
/*     */   }

/*     */ }