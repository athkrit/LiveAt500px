package com.example.liveat500px.manager;

import android.content.Context;

import com.example.liveat500px.dao.PhotoItemCollection;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

public class PhotoListManagerOld {

    private static PhotoListManagerOld instance;

    private static PhotoItemCollection dao;

    public static PhotoItemCollection getDao() {
        return dao;
    }

    public static void setDao(PhotoItemCollection dao) {
        PhotoListManagerOld.dao = dao;
    }

    public static PhotoListManagerOld getInstance() {
        if (instance == null)
            instance = new PhotoListManagerOld();
        return instance;
    }

    private Context mContext;


    private PhotoListManagerOld() {
        mContext = Contextor.getInstance().getContext();
    }

}
