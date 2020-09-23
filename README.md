说明:

这是一个封装了一个自定义协调者布局，开发者可以自己修改xml来布局toolbar和一个悬停的标签布局tagbar。

开发背景

一些商城的app的首页因为都用到头部一块大的viewgroup，下面是一个可横向切换tab的fragment容器，这种需求大多都采用协调者布局来实现，但是原生的协调者和behavior存在2个不好的特性，
1.对于fling的时候，有时候会滑动误判，导致appbar和下面滚动viewgroup位置错位，2.当滑动上面appbar的时候，滑动或者fling到尽头的时候，不能传到下面的可滚动viewgroup.现在淘宝京东都
克服了这个特性，苏宁商城还没克服。本人为方便解决这个难点制作这个库方便开发者使用。

   

导入说明

  implementation 'com.github.lwh912:EasyCoordinatorLayout:1.0.2'
  
  
使用


    <com.lwh912.easycoordinatorlayout.EasyTagCorrdinatorLayout
        android:id="@+id/easy_tag_coordinator"
        android:layout_width="match_parent"
        android:layout_height="match_parent">


        <LinearLayout
            android:id="@+id/ll_tool"
            android:tag="easy_corrdinator_toolbar"
            android:layout_width="match_parent"
            android:layout_height="50dp"
            android:orientation="vertical"
            android:background="#9264a5"
            >
            <TextView
                android:id="@+id/tv_ll_tagbar"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text=""
                android:textStyle="bold"
                android:layout_gravity="center_vertical"
                android:textColor="#fff"
                android:layout_margin="5dp"
                android:textSize="15sp"
                />
        </LinearLayout>

        <LinearLayout
            android:id="@+id/ll_tagbar"
            android:tag="easy_corrdinator_tagbar"
            android:layout_width="match_parent"
            android:layout_height="50dp"
            android:orientation="horizontal"
            android:background="#ff5500"
            >


            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:textColor="#fff"
                android:text="这是一个悬停的view,可以xml进行布局"
                android:layout_margin="5dp"
                android:textStyle="bold"
                android:textSize="15sp"
                />

        </LinearLayout>


    </com.lwh912.easycoordinatorlayout.EasyTagCorrdinatorLayout>



上面EasyTagCorrdinatorLayout有两个子view，可以不写，如果想作为toolbar需要写tag = easy_corrdinator_toolbar,想作为tabbar需要写成tag = easy_corrdinator_tagbar
这样EasyTagCorrdinatorLayout会自己计算高度，而里面有个viewpager容器，需要入参一个fragment数组，而fragment里面需要有个高度撑满高度的recycleview,这个recycleView需要tag = fling,
如下面所示



       <androidx.recyclerview.widget.RecyclerView
                android:id="@+id/recyclerView"
                android:layout_width="match_parent"
                android:tag="fling"
                android:layout_height="match_parent"
                android:background="#fff"
                android:overScrollMode="never" />



如果有需要在recycleview头部加内容可以多type的形式或者用一些第三方的adapter，如"com.github.CymChad:BaseRecyclerViewAdapterHelper:3.0.4"可以adapter.addheader的形式添加头部


