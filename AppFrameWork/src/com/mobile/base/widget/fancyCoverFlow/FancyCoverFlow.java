package com.mobile.base.widget.fancyCoverFlow;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Transformation;
import android.widget.Gallery;
import android.widget.SpinnerAdapter;

import com.mobile.base.R;

public class FancyCoverFlow extends Gallery
{
  private boolean mIsBeingDragged = false; private boolean misFirstDragged = false;
  protected float mLastMotionY;
  protected float mLastMotionX;
  public static final int ACTION_DISTANCE_AUTO = 2147483647;
  public static final float SCALEDOWN_GRAVITY_TOP = 0.0F;
  public static final float SCALEDOWN_GRAVITY_CENTER = 0.5F;
  public static final float SCALEDOWN_GRAVITY_BOTTOM = 1.0F;
  private int mTouchSlop;
  private float reflectionRatio = 0.4F;

  private int reflectionGap = 20;

  private boolean reflectionEnabled = false;
  private float unselectedAlpha;
  private Camera transformationCamera;
  private int maxRotation = 75;
  private float unselectedScale;
  private float scaleDownGravity = 0.5F;
  private int actionDistance;
  private float unselectedSaturation;

  public FancyCoverFlow(Context context)
  {
    super(context);
    initialize();
    init(context);
  }

  public FancyCoverFlow(Context context, AttributeSet attrs) {
    super(context, attrs);
    initialize();
    applyXmlAttributes(attrs);
    init(context);
  }

  public FancyCoverFlow(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    initialize();
    applyXmlAttributes(attrs);
    init(context);
  }

  private void initialize() {
    this.transformationCamera = new Camera();
    setSpacing(0);
  }

  protected void init(Context context) {
    ViewConfiguration configuration = ViewConfiguration.get(getContext());
    this.mTouchSlop = configuration.getScaledTouchSlop();
  }
  @SuppressLint({"Recycle"})
  private void applyXmlAttributes(AttributeSet attrs) {
    TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.FancyCoverFlow);
    this.actionDistance = a.getInteger(5, 2147483647);
    this.scaleDownGravity = a.getFloat(4, 1.0F);
    this.maxRotation = a.getInteger(3, 45);
    this.unselectedAlpha = a.getFloat(0, 0.3F);
    this.unselectedSaturation = a.getFloat(1, 0.0F);
    this.unselectedScale = a.getFloat(2, 0.75F);
  }

  public float getReflectionRatio()
  {
    return this.reflectionRatio;
  }

  public void setReflectionRatio(float reflectionRatio) {
    if ((reflectionRatio <= 0.0F) || (reflectionRatio > 0.5F)) {
      throw new IllegalArgumentException("reflectionRatio may only be in the interval (0, 0.5]");
    }

    this.reflectionRatio = reflectionRatio;

    if (getAdapter() != null)
      ((FancyCoverFlowAdapter)getAdapter()).notifyDataSetChanged();
  }

  public int getReflectionGap()
  {
    return this.reflectionGap;
  }

  public void setReflectionGap(int reflectionGap) {
    this.reflectionGap = reflectionGap;

    if (getAdapter() != null)
      ((FancyCoverFlowAdapter)getAdapter()).notifyDataSetChanged();
  }

  public boolean isReflectionEnabled()
  {
    return this.reflectionEnabled;
  }

  public void setReflectionEnabled(boolean reflectionEnabled) {
    this.reflectionEnabled = reflectionEnabled;

    if (getAdapter() != null)
      ((FancyCoverFlowAdapter)getAdapter()).notifyDataSetChanged();
  }

  public void setAdapter(SpinnerAdapter adapter)
  {
    if (!(adapter instanceof FancyCoverFlowAdapter)) {
      throw new ClassCastException(FancyCoverFlow.class.getSimpleName() + " only works in conjunction with a " + 
        FancyCoverFlowAdapter.class.getSimpleName());
    }

    super.setAdapter(adapter);
  }

  public int getMaxRotation()
  {
    return this.maxRotation;
  }

  public void setMaxRotation(int maxRotation)
  {
    this.maxRotation = maxRotation;
  }

  public float getUnselectedAlpha()
  {
    return this.unselectedAlpha;
  }

  public float getUnselectedScale()
  {
    return this.unselectedScale;
  }

  public void setUnselectedScale(float unselectedScale)
  {
    this.unselectedScale = unselectedScale;
  }

  public float getScaleDownGravity()
  {
    return this.scaleDownGravity;
  }

  public void setScaleDownGravity(float scaleDownGravity)
  {
    this.scaleDownGravity = scaleDownGravity;
  }

  public int getActionDistance()
  {
    return this.actionDistance;
  }

  public void setActionDistance(int actionDistance)
  {
    this.actionDistance = actionDistance;
  }

  public void setUnselectedAlpha(float unselectedAlpha)
  {
    super.setUnselectedAlpha(unselectedAlpha);
    this.unselectedAlpha = unselectedAlpha;
  }

  public float getUnselectedSaturation()
  {
    return this.unselectedSaturation;
  }

  public void setUnselectedSaturation(float unselectedSaturation)
  {
    this.unselectedSaturation = unselectedSaturation;
  }

  protected boolean getChildStaticTransformation(View child, Transformation t)
  {
    FancyCoverFlowItemWrapper item = (FancyCoverFlowItemWrapper)child;

    if (Build.VERSION.SDK_INT >= 16) {
      item.invalidate();
    }

    int coverFlowWidth = getWidth();
    int coverFlowCenter = coverFlowWidth / 2;
    int childWidth = item.getWidth();
    int childHeight = item.getHeight();
    int childCenter = item.getLeft() + childWidth / 2;

    int actionDistance = this.actionDistance == 2147483647 ? (int)((coverFlowWidth + childWidth) / 2.0F) : 
      this.actionDistance;

    float effectsAmount = Math.min(1.0F, 
      Math.max(-1.0F, 1.0F / actionDistance * (childCenter - coverFlowCenter)));

    t.clear();
    t.setTransformationType(3);

    if (this.unselectedAlpha != 1.0F) {
      float alphaAmount = (this.unselectedAlpha - 1.0F) * Math.abs(effectsAmount) + 1.0F;
      t.setAlpha(alphaAmount);
    }

    if (this.unselectedSaturation != 1.0F)
    {
      float saturationAmount = (this.unselectedSaturation - 1.0F) * Math.abs(effectsAmount) + 1.0F;
      item.setSaturation(saturationAmount);
    }

    Matrix imageMatrix = t.getMatrix();

    if (this.maxRotation != 0) {
      int rotationAngle = (int)(-effectsAmount * this.maxRotation);
      this.transformationCamera.save();
      this.transformationCamera.rotateY(rotationAngle);
      this.transformationCamera.getMatrix(imageMatrix);
      this.transformationCamera.restore();
    }

    if (this.unselectedScale != 1.0F) {
      float zoomAmount = (this.unselectedScale - 1.0F) * Math.abs(effectsAmount) + 1.0F;

      float translateX = childWidth / 2.0F;
      float translateY = childHeight * this.scaleDownGravity;
      imageMatrix.preTranslate(-translateX, -translateY);
      imageMatrix.postScale(zoomAmount, zoomAmount);
      imageMatrix.postTranslate(translateX, translateY);
    }

    return true;
  }

  public boolean onTouchEvent(MotionEvent event)
  {
    if (this.misFirstDragged) {
      this.misFirstDragged = false;
      cancleTouch(event);
      return true;
    }
    return super.onTouchEvent(event);
  }

  public boolean onInterceptTouchEvent(MotionEvent ev)
  {
    int action = ev.getAction();
    if ((action == 2) && (this.mIsBeingDragged)) {
      return true;
    }
    switch (action & 0xFF)
    {
    case 2:
      float y = ev.getY(0);
      float x = ev.getX(0);
      int xDiff = (int)(x - this.mLastMotionX);

      if (Math.abs(xDiff) <= this.mTouchSlop) break;
      this.mIsBeingDragged = true;
      this.mLastMotionY = y;
      this.mLastMotionX = x;

      break;
    case 0:
      y = ev.getY();
      x = ev.getX();
      this.mLastMotionY = y;
      this.mLastMotionX = x;
      break;
    case 1:
    case 3:
      endDrag();
      break;
    case 4:
    case 5:
    case 6:
    }if (!this.mIsBeingDragged) {
      return super.onInterceptTouchEvent(ev);
    }
    endDrag();
    this.misFirstDragged = true;
    return true;
  }

  @SuppressLint({"Recycle"})
  public void cancleTouch(MotionEvent ev) {
    MotionEvent event = MotionEvent.obtain(System.currentTimeMillis(), System.currentTimeMillis(), 0, ev.getX(), ev.getY(), ev.getMetaState());
    onTouchEvent(event);
  }

  protected void endDrag() {
    this.mIsBeingDragged = false;
  }

  public static class LayoutParams extends Gallery.LayoutParams {
      public LayoutParams(Context c, AttributeSet attrs) {
          super(c, attrs);
      }

      public LayoutParams(int w, int h) {
          super(w, h);
      }

      public LayoutParams(ViewGroup.LayoutParams source) {
          super(source);
      }
  }
}