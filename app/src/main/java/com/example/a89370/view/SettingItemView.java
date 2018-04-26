package com.example.a89370.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.a89370.R;

/**
 * Created by 89370 on 2018/4/25.
 */

public class SettingItemView extends RelativeLayout {

    private TextView setting_tv_title;
    private CheckBox setting_cb;
    private TextView setting_tv_des;

    public SettingItemView(Context context) {
        this(context, null);
    }

    public SettingItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SettingItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public SettingItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        View view = View.inflate(context, R.layout.setting_item_view, this);

        setting_tv_title = (TextView) this.findViewById(R.id.setting_tv_title);
        setting_cb = (CheckBox) this.findViewById(R.id.setting_cb);
        setting_tv_des = (TextView) this.findViewById(R.id.setting_tv_des);



    }

    /**
     * @return  判断checkbox是否选中
     */
    public boolean isCheck(){
        return setting_cb.isChecked();
    }

    public void setCheck(boolean isCheck){
        setting_cb.setChecked(isCheck);
        if(isCheck){
            setting_tv_des.setText("自动更新已开启");
        }else {
            setting_tv_des.setText("自动更新已关闭");
        }
    }

}
