package com.baidu.fileb.callback;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class SimpleTouchHelperCallBack extends ItemTouchHelper.Callback {
    private TabTouchCallBack tabTouchCallBack;
    private boolean mSwipeEnable =true;

    public SimpleTouchHelperCallBack(TabTouchCallBack tabTouchCallBack) {
        this.tabTouchCallBack = tabTouchCallBack;
    }

    //返回可以滑动的方向，一般使用getMovementFlags
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int drag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipw = ItemTouchHelper.LEFT;
        return makeMovementFlags(drag,swipw);
        //drag  拖拽的方向  滑动的方向
    }

    /*
     * 拖动item时 可以调用Adapter 的NotifyItemMoved 方法来交换两个ViewHolder
     * */
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        tabTouchCallBack.onItemExChange(viewHolder.getAdapterPosition(),viewHolder1.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        tabTouchCallBack.onItemDelete(viewHolder.getAdapterPosition());
    }

    //支持长按拖动
    @Override
    public boolean isLongPressDragEnabled() {
        return super.isLongPressDragEnabled();
    }

    public void setmSwipeEnable(boolean mSwipeEnable) {
        this.mSwipeEnable = mSwipeEnable;
    }
}
