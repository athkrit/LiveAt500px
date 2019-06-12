package com.example.liveat500px.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;

import com.example.liveat500px.DataType.MutableInteger;
import com.example.liveat500px.R;
import com.example.liveat500px.dao.PhotoItemCollection;
import com.example.liveat500px.dao.PhotoItemDao;
import com.example.liveat500px.view.PhotoListItem;

public class PhotoListAdapter extends BaseAdapter {

    PhotoItemCollection dao;

    MutableInteger lastPositionInteger;

    public PhotoListAdapter(MutableInteger lastPositionInteger) {
        this.lastPositionInteger = lastPositionInteger;
    }

    public void setDao(PhotoItemCollection dao) {
        this.dao = dao;
    }

    @Override
    public int getCount() {
        if (dao == null)
            return 1;
        if (dao.getData() == null)
            return 1;
        return dao.getData().size()+1;
    }

    @Override
    public Object getItem(int position) {
        return dao.getData().get(position);
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return position == getCount()-1? 1:0;
    }
    // use singleton
//    @Override
//    public int getCount() {
//        if (PhotoListManagerOld.getDao() == null)
//            return 0;
//        if (PhotoListManagerOld.getDao().getData() == null)
//            return 0;
//        return PhotoListManagerOld.getDao().getData().size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return PhotoListManagerOld.getDao().getData().get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//
//        return 0;
//    }
    //ใช้ listview หลายๆประเภท
//    @Override
//    public int getViewTypeCount() {
//        return 2;
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        return position % 2 == 0 ?0:1;
//    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        if(getItemViewType(position)==0) {
        if(position == getCount()-1){
            ProgressBar item;
            if(convertView != null){
                item = (ProgressBar) convertView;
            }
            else {
                item = new ProgressBar(parent.getContext());
            }
            return item;
        }

        PhotoListItem item;
        if (convertView != null) {
            item = (PhotoListItem) convertView;
        } else {
            item = new PhotoListItem(parent.getContext());
        }
        PhotoItemDao dao = (PhotoItemDao) getItem(position);
        item.setNameText(dao.getCaption());
        item.setDescriptionText(dao.getUserName() + "\n" + dao.getCamera());
        item.setImageUrl(dao.getImageUrl());

        if(position > lastPositionInteger.getValue() ) {
            Animation anim = AnimationUtils.loadAnimation(parent.getContext(),
                    R.anim.up_from_bottom);
            item.startAnimation(anim);
            lastPositionInteger.setValue(position);
        }

        return item;
//        }
//        else {
//            TextView item;
//            if (convertView != null) {
//                item = (TextView) convertView;
//            } else {
//                item = new TextView(parent.getContext());
//            }
//            item.setText("position"+position);
//            return item;
//        }
    }
    public void increaseLastPosition(int amount){
        lastPositionInteger.setValue(lastPositionInteger.getValue()+amount);
    }
}
