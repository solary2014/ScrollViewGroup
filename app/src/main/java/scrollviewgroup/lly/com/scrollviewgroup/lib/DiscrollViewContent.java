package scrollviewgroup.lly.com.scrollviewgroup.lib;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import scrollviewgroup.lly.com.scrollviewgroup.R;

/**
 * Created by Administrator on 2016/11/17.
 */

public class DiscrollViewContent extends LinearLayout {

    public DiscrollViewContent(Context context) {
        super(context);
        setOrientation(VERTICAL);
    }

    public DiscrollViewContent(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
    }

    public DiscrollViewContent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
    }

    /**
     * 重写addView
     * @param child
     * @param index
     * @param params
     */
    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(asDiscrollvable(child,(MyLayoutParams)params), index, params);
    }

    private View asDiscrollvable(View child, MyLayoutParams params) {
        if(!isDiscrollvable(params)){
            return child;
        }
        DiscrollvableView discrollvableChild = new DiscrollvableView(getContext());
        discrollvableChild.setDiscrollveAlpha(params.mDiscrollveAlpha);
        discrollvableChild.setDiscrollveTranslation(params.mDiscrollveTranslation);
        discrollvableChild.setDiscrollveScaleX(params.mDiscrollveScaleX);
        discrollvableChild.setDiscrollveScaleY(params.mDiscrollveScaleY);
        discrollvableChild.setDiscrollveThreshold(params.mDiscrollveThreshold);
        discrollvableChild.setDiscrollveFromBgColor(params.mDiscrollveFromBgColor);
        discrollvableChild.setDiscrollveToBgColor(params.mDiscrollveToBgColor);
        discrollvableChild.addView(child);
        return discrollvableChild;
    }

    /**
     * 判断是否是我们定义的LayoutParams
     * @param lp
     * @return
     */
    private boolean isDiscrollvable(MyLayoutParams lp) {
        return lp.mDiscrollveAlpha ||
                lp.mDiscrollveTranslation != -1 ||
                lp.mDiscrollveScaleX ||
                lp.mDiscrollveScaleY ||
                (lp.mDiscrollveFromBgColor != -1 && lp.mDiscrollveToBgColor != -1);
    }

    /**
     * 重写checkLayoutParams
     * @param p
     * @return
     */
    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof MyLayoutParams;
    }

    /**
     * 重写generateDefaultLayoutParams
     * @return
     */
    @Override
    protected LinearLayout.LayoutParams generateDefaultLayoutParams() {
        return new MyLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    /**
     * 重写generateLayoutParams
     * @param attrs
     * @return
     */
    @Override
    public LinearLayout.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MyLayoutParams(getContext(), attrs);
    }

    /**
     * 重写generateLayoutParams
     * @param p
     * @return
     */
    @Override
    protected LinearLayout.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new MyLayoutParams(p.width, p.height);
    }

    /**
     * 自定义LinearLayout.LayoutParams
     */
    class MyLayoutParams extends LinearLayout.LayoutParams {

        private int mDiscrollveFromBgColor;
        private int mDiscrollveToBgColor;
        private float mDiscrollveThreshold;
        public boolean mDiscrollveAlpha;
        public boolean mDiscrollveScaleX;
        public boolean mDiscrollveScaleY;
        private int mDiscrollveTranslation;
        public MyLayoutParams(Context context, AttributeSet attrs) {
            super(context, attrs);
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DiscrollView_LayoutParams);
            try {
                mDiscrollveAlpha = a.getBoolean(R.styleable.DiscrollView_LayoutParams_discrollve_alpha, false);
                mDiscrollveScaleX = a.getBoolean(R.styleable.DiscrollView_LayoutParams_discrollve_scaleX, false);
                mDiscrollveScaleY = a.getBoolean(R.styleable.DiscrollView_LayoutParams_discrollve_scaleY, false);
                mDiscrollveTranslation = a.getInt(R.styleable.DiscrollView_LayoutParams_discrollve_translation, -1);
                mDiscrollveThreshold = a.getFloat(R.styleable.DiscrollView_LayoutParams_discrollve_threshold, 0.0f);
                mDiscrollveFromBgColor = a.getColor(R.styleable.DiscrollView_LayoutParams_discrollve_fromBgColor, -1);
                mDiscrollveToBgColor = a.getColor(R.styleable.DiscrollView_LayoutParams_discrollve_toBgColor, -1);
            } finally {
                a.recycle();
            }
        }

        public MyLayoutParams(int width, int height) {
            super(width, height);
        }

    }
}
