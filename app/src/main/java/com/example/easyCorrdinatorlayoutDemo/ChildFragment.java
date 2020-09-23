package com.example.easyCorrdinatorlayoutDemo;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ChildFragment extends Fragment {
    private View rootView;
    private boolean isRootViewCreated;
    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null != rootView) {
            isRootViewCreated = true;
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (null != parent) {
                parent.removeView(rootView);
            }
        } else {
            isRootViewCreated = false;
            rootView = inflater.inflate(R.layout.fragment_test_4, container, false);
        }
        initialize();
        return rootView;
    }

    private void initialize() {
        recyclerView = rootView.findViewById(R.id.recyclerView);
        List<Bean> list2 = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            String s = i + 1 + "";
            Bean bean = new Bean();
            bean.index = i;
            bean.name = s;
            list2.add(bean);
        }
//        TypeAdapter adapter2 = new TypeAdapter(getContext(), list2, false);
        Adapter adapter2 = new Adapter(R.layout.item_layout);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        layoutManager2.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager2);
        recyclerView.setAdapter(adapter2);
        rootView.findViewById(R.id.rl_error).setVisibility(View.GONE);
        rootView.findViewById(R.id.rl_loading).setVisibility(View.GONE);
        rootView.findViewById(R.id.refresh_view).setVisibility(View.VISIBLE);
        adapter2.addData(list2);
        adapter2.notifyDataSetChanged();
//        View view = new View(getContext());
//        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Dp.dip2px(getContext(),100)));
//        adapter2.addHeaderView(view);
    }


    public static class Bean{
        public int index;
        public String name;
    }

    static public class Adapter extends BaseQuickAdapter<Bean,Adapter.ViewHolder>{
        int[] c = new int[]{
            Color.parseColor("#33FF0000"),
                    Color.parseColor("#3300FF00"),
                    Color.parseColor("#330000FF")};
        public Adapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(@NotNull ViewHolder viewHolder, Bean item) {
            viewHolder.item_tv.setText(item.name);
            viewHolder.item_tv.setBackgroundColor(c[item.index%3]);
        }

        class ViewHolder extends BaseViewHolder {
            TextView item_tv;
            public ViewHolder(@NotNull View view) {
                super(view);
                item_tv = view.findViewById(R.id.item_tv);
            }
        }

    }
}
