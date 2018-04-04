package com.cdye.clipview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by cdy on 2018/4/4.
 */

@SuppressLint("AppCompatCustomView")
public class ClipImageView extends ImageView {
    private Path mPath;
    private RectF mRectF;
    /**
     * 利用clip剪切的四个角半径，八个数据分别代表左上角（x轴半径，y轴半径），右上角（**），右下角（**），左下角（**）
     */
    private float[] rids = new float[8];
    private PaintFlagsDrawFilter paintFlagsDrawFilter;
    public ClipImageView(Context context) {
        this(context, null,0);
    }

    public ClipImageView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public ClipImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ClipImageView);
        /**
         * 此处圆角半径为四个角的半径
         */
        float mRadiusLeftTop = array.getDimension(R.styleable.ClipImageView_radius_left_top, 0);
        float mRadiusRightTop = array.getDimension(R.styleable.ClipImageView_radius_right_top, 0);
        float mRadiusRightBottom = array.getDimension(R.styleable.ClipImageView_radius_right_bottom, 0);
        float mRadiusLeftBottom = array.getDimension(R.styleable.ClipImageView_radius_left_bottom, 0);
        rids[0] = mRadiusLeftTop;
        rids[1] = mRadiusLeftTop;
        rids[2] = mRadiusRightTop;
        rids[3] = mRadiusRightTop;
        rids[4] = mRadiusRightBottom;
        rids[5] = mRadiusRightBottom;
        rids[6] = mRadiusLeftBottom;
        rids[7] = mRadiusLeftBottom;
        array.recycle();
        mPath = new Path();
        //抗锯齿
        paintFlagsDrawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
        //硬件加速
        setLayerType(View.LAYER_TYPE_HARDWARE, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPath.reset();
        mPath.addRoundRect(mRectF, rids, Path.Direction.CW);
        canvas.setDrawFilter(paintFlagsDrawFilter);
        canvas.save();
        canvas.clipPath(mPath);
        super.onDraw(canvas);
        canvas.restore();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRectF = new RectF(0, 0, w, h);
    }
}
