package com.example.yrchoi.yurist.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yrchoi.yurist.DragnDrop.ItemModel;
import com.example.yrchoi.yurist.DragnDrop.MainMenu;
import com.example.yrchoi.yurist.Interface.ItemTouchHelperAdapter;
import com.example.yrchoi.yurist.Interface.ItemTouchHelperViewHolder;
import com.example.yrchoi.yurist.Interface.OnStartDragListener;
import com.example.yrchoi.yurist.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Adapter_dragAndDrop extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ItemTouchHelperAdapter {

    private static List<ItemModel> mMenuList;
    OnItemClickListener mItemClickListener;
    private static final int TYPE_ITEM = 0;
    private final LayoutInflater mInflater;
    private final OnStartDragListener mDragStartListener;
    private Context mContext;
    private String type;
    private boolean flag = true;
    ArrayList<MainMenu> mainMenu;

    public Adapter_dragAndDrop(Context context, List<ItemModel> list, OnStartDragListener dragListner, ArrayList<MainMenu> mainMenu, String type) {
        this.mMenuList = list;
        this.mInflater = LayoutInflater.from(context);
        mDragStartListener = dragListner;
        mContext = context;
        this.mainMenu = mainMenu;
        this.type = type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        if (viewType == TYPE_ITEM) {
            //inflate your layout and pass it to view holder
            View v = mInflater.inflate(R.layout.list_item_set_option, viewGroup, false);
            RelativeLayout item = (RelativeLayout) v.findViewById(R.id.set_option_item);

            return new VHItem(v);
        }


        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");

    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_ITEM;

        //스크롤하고 내리는 도중에 탐
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int i) {

        if (viewHolder instanceof VHItem) {

            final VHItem holder = (VHItem) viewHolder;
            ((VHItem) viewHolder).title.setText(mMenuList.get(i).getName());
            if (mMenuList.get(i).getChecked().equals("Y")) {
                ((VHItem) viewHolder).imageView.setImageResource(R.drawable.ic_txt_del_press);
                mMenuList.get(i).setChecked("Y");
            } else {
                ((VHItem) viewHolder).imageView.setImageResource(R.drawable.ic_txt_del_press_bk);
                mMenuList.get(i).setChecked("N");
            }


            ((VHItem) viewHolder).click_zone.setOnTouchListener((v, event) -> {
                final ImageView check_img = (ImageView) v.findViewById(R.id.list_option_check_img);

                int cnt=0;
                for(ItemModel itemModel : mMenuList){
                    if(itemModel.getChecked().equals("Y")){
                        cnt++;
                    }
                }

                for(int j =0 ; j< mMenuList.size() ; j++){
                    if(mMenuList.get(j).getList_position() == i){
                        //클릭한게 활성화 "Y"이고
                        if (mMenuList.get(j).getChecked().equals("Y")) {
                                //2개 이상일 때 img 바꿔줌
                                check_img.setImageResource(R.drawable.ic_txt_del_press_bk);
                                mMenuList.get(j).setChecked("N");

                        } else {
                            //이미지 바꿔줌
                            check_img.setImageResource(R.drawable.ic_txt_del_press);
                            mMenuList.get(j).setChecked("Y");
                        }
                        break;
                    }
                }
                return false;
            });


            if (mMenuList.get(i).getChecked().equals("N")) {
                ((VHItem) viewHolder).imageView.setImageResource(R.drawable.ic_txt_del_press_bk);
            } else {
                ((VHItem) viewHolder).imageView.setImageResource(R.drawable.ic_txt_del_press);
            }

            ((VHItem) viewHolder).image_menu.setOnTouchListener(new View.OnTouchListener() {

                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    int code = event.getAction() & MotionEvent.ACTION_MASK;
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            mDragStartListener.onStartDrag(holder);
                            v.getVerticalScrollbarPosition();
                            break;

                        default:
                            break;
                    }
                    //터치할 때 탐

                    return true;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mMenuList.size();
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public class VHItem extends RecyclerView.ViewHolder implements View.OnClickListener, ItemTouchHelperViewHolder {
        public TextView title;
        private ImageView imageView;
        private LinearLayout image_menu;
        private RelativeLayout click_zone;

        public VHItem(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.list_option_txt);
            image_menu = (LinearLayout) itemView.findViewById(R.id.list_option_locate_img);
            imageView = (ImageView) itemView.findViewById(R.id.list_option_check_img);
            click_zone = (RelativeLayout) itemView.findViewById(R.id.list_option_click_zone);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getPosition());
            }

        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemVClear() {

        }

    }

    @Override
    public void onItemDismiss(int position) {
        mMenuList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        //Log.v("", "Log position" + fromPosition + " " + toPosition);
        if (fromPosition < mMenuList.size() && toPosition < mMenuList.size()) {
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(mMenuList, i, i + 1);
//                    mPersonList.get(i + 1).setList_position(i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(mMenuList, i, i - 1);
//                    mPersonList.get(i - 1).setList_position(i - 1);
                }
            }
//            mPersonList.get(fromPosition).setList_position(fromPosition);
//            //Log.d("CYR_Tag","index="+toPosition+"  title="+mPersonList.get(toPosition).getName());

//            notifyItemMoved(fromPosition, toPosition);
//            notifyDataSetChanged();
        }
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }


    public void updateList(List<ItemModel> list) {
        mMenuList = list;
        notifyDataSetChanged();
    }

    public ArrayList<ArrayList> getList_order() {
        ArrayList<ArrayList> list = new ArrayList<ArrayList>();
        ArrayList list_order = new ArrayList();
        ArrayList list_check = new ArrayList();
        for (int i = 0; i < mMenuList.size(); i++) {
            list_order.add(i, mMenuList.get(i).getName());
            list_check.add(i, mMenuList.get(i).getChecked());
        }
        list.add(0, list_order);
        list.add(1, list_check);
        return list;
    }
}