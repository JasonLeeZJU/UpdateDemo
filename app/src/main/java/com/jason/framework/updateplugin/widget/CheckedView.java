package com.jason.framework.updateplugin.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jason.framework.updateplugin.R;

public class CheckedView extends LinearLayout{

    TextView titleTv;
    RadioGroup group;
    RadioButton defRb;
    RadioButton customRb;

    public CheckedView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.VERTICAL);
        initViews(context);
        TypedArray ta = context.obtainStyledAttributes(attrs, com.jason.framework.updateplugin.R.styleable.CheckedView);
        setTitle(ta.getText(com.jason.framework.updateplugin.R.styleable.CheckedView_title));
        setSubTitle(ta.getText(com.jason.framework.updateplugin.R.styleable.CheckedView_subtitle));
        ta.recycle();
    }

    public void setTitle (CharSequence title) {
        titleTv.setText(title);
    }

    public void setSubTitle (CharSequence subTitle) {
        customRb.setText(subTitle);
    }

    public boolean isDefaultSelected () {
        return group.getCheckedRadioButtonId() == com.jason.framework.updateplugin.R.id.def;
    }

    private void initViews(Context context) {
        removeAllViews();
        LayoutInflater.from(context).inflate(com.jason.framework.updateplugin.R.layout.layout_checked,this);
        titleTv = (TextView) findViewById(com.jason.framework.updateplugin.R.id.title);
        group = (RadioGroup) findViewById(com.jason.framework.updateplugin.R.id.group);
        defRb = (RadioButton) findViewById(com.jason.framework.updateplugin.R.id.def);
        customRb = (RadioButton) findViewById(com.jason.framework.updateplugin.R.id.custom);
    }

}
