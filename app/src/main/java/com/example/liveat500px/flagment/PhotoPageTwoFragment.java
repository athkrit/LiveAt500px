package com.example.liveat500px.flagment;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.liveat500px.R;
import com.example.liveat500px.dao.PhotoItemDao;

/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class PhotoPageTwoFragment extends Fragment {

    PhotoItemDao dao;
    TextView tvName, tvDescription, tvDetailCamera, tvId;
    ImageView imgView;

    public PhotoPageTwoFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static PhotoPageTwoFragment newInstance(PhotoItemDao dao) {
        PhotoPageTwoFragment fragment = new PhotoPageTwoFragment();
        Bundle args = new Bundle();

        args.putParcelable("daopage2", dao);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        dao = getArguments().getParcelable("daopage2");

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_photo_page2, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        tvId = (TextView) rootView.findViewById(R.id.tvId);
        tvDescription = (TextView) rootView.findViewById(R.id.tvDescription);
        tvDetailCamera = (TextView) rootView.findViewById(R.id.tvDetailCamera);
        tvName = (TextView) rootView.findViewById(R.id.tvName);
        imgView = (ImageView) rootView.findViewById(R.id.imgView);

        tvId.setText(String.valueOf(dao.getId()));
        tvDescription.setText(dao.getCaption());
        tvDetailCamera.setText("Camera :"+dao.getCamera()
                +"\n Len :"
                +dao.getLens()
                +"\n Speed Shutter :"
                +dao.getShutterSpeed()
                +"\n ISO :"
                +dao.getIso()
                +"\n FocusLength :"
                +dao.getFocusLength());
        tvName.setText(dao.getUserName());
        Glide.with(PhotoPageTwoFragment.this)
                .load(dao.getImageUrl())
                .placeholder(R.drawable.mock)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgView);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance State here
    }

}
