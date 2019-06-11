package com.example.liveat500px.manager;

import android.content.Context;

import com.example.liveat500px.dao.PhotoItemCollection;
import com.example.liveat500px.dao.PhotoItemDao;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

import java.util.ArrayList;

public class PhotoListManager {

    private Context mContext;
    private static PhotoItemCollection dao;

    public PhotoListManager() {
        mContext = Contextor.getInstance().getContext();
    }

    public PhotoItemCollection getDao() {
        return dao;
    }

    public void setDao(PhotoItemCollection dao) {
        this.dao = dao;

    }
    public void insertDaoAtTopPosition(PhotoItemCollection newDao){
        if(dao == null)
            dao = new PhotoItemCollection();
        if(dao.getData() == null)
            dao.setData(new ArrayList<PhotoItemDao>());
        dao.getData().addAll(0,newDao.getData());
    }

    public int getMaximumId() {
        if (dao == null)
            return 0;
        if (dao.getData() == null)
            return 0;
        if (dao.getData().size() == 0)
            return 0;
        int maxId = dao.getData().get(0).getId();
        for (int i = 0; i < dao.getData().size(); i++) {
            maxId = Math.max(maxId, dao.getData().get(i).getId());
        }
        return maxId;
    }

    public int getCount() {
        if (dao == null)
            return 0;
        if (dao.getData() == null)
            return 0;
        return dao.getData().size();
    }
}