package com.example.a89370;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 89370 on 2018/4/23.
 */

public class HomeActivity extends AppCompatActivity {

    private Context mContext;
    private GridView home_gv;
    private String[] mTitleStr;
    private int[] mDrawableIDs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mContext = this;

        initUI();
        initData();

    }


    private void initData() {
        //准备数据(文字，图片)
        mTitleStr = new String[]{"员工签到", "计算器", "考勤查询", "个人信息","设置中心"};
        mDrawableIDs = new int[]{R.mipmap.beach, R.mipmap.ice, R.mipmap.rain, R.mipmap.street,R.mipmap.black};

        home_gv.setAdapter(new MyAdapter());

        home_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent intent0 = new Intent(mContext,SignActivity.class);
                        startActivity(intent0);
                        break;
                    case 1:
                        Intent intent1 = new Intent(mContext,CalcuterActivity.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        break;
                    case 3:
                        Intent intent2 = new Intent(mContext, WorkinfoActivity.class);
                        startActivity(intent2);
                        break;
                    case 4:
                        Intent intent3 = new Intent(mContext,SettingActivity.class);
                        startActivity(intent3);
                        break;


                }
            }
        });

    }

    class MyAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return mTitleStr.length;
        }

        @Override
        public Object getItem(int position) {
            return mTitleStr[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = null;
            if (convertView != null) {
                view = convertView;
            } else {
                view = View.inflate(mContext, R.layout.gridview_item, null);
            }

            TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
            ImageView iv_icon = (ImageView) view.findViewById(R.id.iv_icon);

            tv_name.setText(mTitleStr[position]);
            iv_icon.setBackgroundResource(mDrawableIDs[position]);
            return view;
        }

    }


    private void initUI() {
        home_gv = (GridView) findViewById(R.id.home_gv);
    }
}
