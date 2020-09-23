package com.example.easyCorrdinatorlayoutDemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.lwh912.easycoordinatorlayout.EasyTagCorrdinatorLayout;
import com.lwh912.easycoordinatorlayout.listener.OffsetUpdateListener;

import java.util.ArrayList;
import java.util.List;

public class DemoActivity extends FragmentActivity implements ViewPager.OnPageChangeListener, OffsetUpdateListener {
    EasyTagCorrdinatorLayout easy_tag_coordinator;
    TextView tv_ll_tagbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        Util.setNavigationBarLucency((Activity) getActivity(),null);
        initView();
    }

    private void initView() {
        easy_tag_coordinator = findViewById(R.id.easy_tag_coordinator);
        TextView appBarContent = new TextView(getActivity());
        appBarContent.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Util.dip2px(getActivity(),
                1000)));
        appBarContent.setTextSize(15);
        appBarContent.setTextColor(Color.parseColor("#ffffff"));
        appBarContent.setPadding(10,300,10,10);
        appBarContent.setText("这是一个appbar内容，向上fling可以把view传到下面recyclerview");
        appBarContent.setBackgroundColor(Color.parseColor("#8267ea"));
        ArrayList<Fragment> fragmentList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            fragmentList.add(new ChildFragment());
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        List<ChildFragment.Bean> list2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String s = i + 1 + "";
            ChildFragment.Bean bean = new ChildFragment.Bean();
            bean.index = i;
            bean.name = s;
            list2.add(bean);
        }
        tv_ll_tagbar = findViewById(R.id.tv_ll_tagbar);
        ChildFragment.Adapter adapter2 = new ChildFragment.Adapter(R.layout.item_layout);
        easy_tag_coordinator.addAppBarContent(appBarContent)
                .setViewpagerData(fragmentList)
                .setViewpagerNoScroll(false)
                .addOnPageChangeListener(this)
                .setOffsetUpdateListener(this);
        adapter2.addData(list2);

        //设置高度
        int toolbarHeight = Integer.valueOf(getIntent().getStringExtra("toolbarHeight"));
        int tagbarHeight = Integer.valueOf(getIntent().getStringExtra("tagbarHeight"));
        easy_tag_coordinator.findViewWithTag("easy_corrdinator_toolbar").getLayoutParams().height = Util.dip2px(getActivity(),toolbarHeight);
        easy_tag_coordinator.findViewWithTag("easy_corrdinator_tagbar").getLayoutParams().height = Util.dip2px(getActivity(),tagbarHeight);
        easy_tag_coordinator.invalidate();

    }



    private Context getActivity() {
        return DemoActivity.this;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.d("DemoActivity","position = " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onOffsetSetChange(int yOffset) {
        tv_ll_tagbar.setText("滑动高度 :" + String.valueOf(yOffset));
    }
}
