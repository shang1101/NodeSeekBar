package com.shang.nodeseekbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.SeekBar;


import java.util.ArrayList;

/**
 * Created by wangyan-pd on 2016/10/17.
 */

public class NodeSeekBar extends SeekBar {

    private  int mNodeColor = Color.GRAY;
    private int mNodeRadius = 10;
    private boolean mIsNodeDrawed = false;
    private int mNodeCount = 4;

    public NodeSeekBar(Context context) {
        this(context,null);
    }


    public NodeSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttr(context,attrs);
        initView();
    }

    public NodeSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs);
        initView();
    }

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.nodeseekbar_attrs);
        mNodeColor = typedArray.getColor(R.styleable.nodeseekbar_attrs_node_color, Color.GRAY);
        mNodeCount = typedArray.getInteger(R.styleable.nodeseekbar_attrs_node_number,4);
        mNodeRadius =typedArray.getInteger(R.styleable.nodeseekbar_attrs_node_radius,5);
    }


    public interface OnSeekChoiceListener{
        void onSeekChoosen(int index);
    }
    private OnSeekChoiceListener mSeekChoiceListener;
    public void setOnChoiceListener(OnSeekChoiceListener onChoiceListener){
        mSeekChoiceListener = onChoiceListener;
    }
    private ArrayList<Integer> mGreps = new ArrayList<Integer>();
    private void initView() {
        initGreps();
        setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            public int mIndex;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.e("shang","progress:"+progress);
                mIndex = getClosedNodeIndex(progress);
                Log.e("shang","index:"+mIndex);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                setProgress( mIndex * getMax()/(mNodeCount -1));
                if(mSeekChoiceListener != null)
                    mSeekChoiceListener.onSeekChoosen(mIndex);
            }
        });
    }

    private int getClosedNodeIndex(int progress) {
        for(int i = 0 ;i < mGreps.size()-1 ;i++){
            int min = mGreps.get(i);
            int max = mGreps.get(i+1);
            if(min <= progress  && progress < max){
                return i;
            }else if(progress == getMax()){
                return mGreps.size() - 2;
            }
        }
        return 0;
    }

    public void setNodeRadius(int radius){
        mNodeRadius = radius;
    }
    public void setNodeCount(int count){
        if(count < 2)
            count = 2;
        mNodeCount = count;
        initGreps();
    }

    private void initGreps() {
        mGreps.clear();
        mGreps.add(0);
        int step = getMax()/(mNodeCount -1)/2;
        for(int i = 0; i < mNodeCount - 1;i++){
            mGreps.add(getMax()/(mNodeCount -1) * i + step );
        }
        mGreps.add(getMax());
    }
   public void setSeekBarPosition(int index){
       setProgress(index * getMax()/(mNodeCount -1));
   }
    @Override
    protected synchronized void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(mNodeColor);
        Log.e("shang","canvas-height:"+canvas.getHeight()+" canvas-width:"+canvas.getWidth());
        if(!mIsNodeDrawed && getMeasuredWidth() != 0 && getMeasuredHeight() != 0){
            int y = canvas.getHeight()/2;
//            final int saveCount = canvas.save();
//            // Translate the padding. For the x, we need to allow the thumb to
//            // draw in its extra space
//            canvas.translate( getPaddingLeft() - mNodeRadius, getPaddingTop());
            for(int i = 0; i < mNodeCount ;i++){
                int x;
                if(i == 0){
                    final int saveCount = canvas.save();
                    canvas.translate( getPaddingLeft(), getPaddingTop());
                    canvas.drawCircle(0,y,mNodeRadius,paint);
                    canvas.restoreToCount(saveCount);

                }else if( i == mNodeCount -1){
                    final int saveCount = canvas.save();
                    canvas.translate( - getPaddingRight() , getPaddingTop());
                    x = getMeasuredWidth() ;
                    canvas.drawCircle(x,y,mNodeRadius,paint);
                    canvas.restoreToCount(saveCount);
                }else {
                    int step = (getMeasuredWidth()- getPaddingLeft() - getPaddingRight() )/(mNodeCount -1);
                    x = step * i + getPaddingLeft();
                    canvas.drawCircle(x,y,mNodeRadius,paint);
                }
            }


        }
        super.onDraw(canvas);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("shang","getMeasuredHeight:"+ getMeasuredHeight()+" getMeasuredWidth:"+getMeasuredWidth() + " getPaddingLeft:"+ getPaddingLeft());

    }
}
