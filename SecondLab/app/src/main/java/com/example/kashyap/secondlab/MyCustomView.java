package com.example.kashyap.secondlab;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class MyCustomView extends View {

    private static  final int SQUARE_SIZE=200;
    private Rect mRectSquare;
    private Paint mPaintSquare;

    public MyCustomView(Context context) {
        super(context);
        init(null);
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    public void SwapColor()
    {
        mPaintSquare.setColor(mPaintSquare.getColor()==Color.GREEN?Color.RED:Color.GREEN);
        postInvalidate();
    }

    private void init(@Nullable AttributeSet set) {
        mRectSquare=new Rect();
        mPaintSquare=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintSquare.setColor(Color.GREEN);
    }

    @Override
    protected  void onDraw(Canvas canvas)
    {

        Rect rect=new Rect();
        mRectSquare.left=50;
        mRectSquare.top=50;
        mRectSquare.right=mRectSquare.left+SQUARE_SIZE;
        mRectSquare.bottom=mRectSquare.top+SQUARE_SIZE;
        canvas.drawRect(mRectSquare,mPaintSquare);

    }

}
