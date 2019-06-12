package com.example.liveat500px.flagment;

import android.os.Binder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.liveat500px.DataType.MutableInteger;
import com.example.liveat500px.R;
import com.example.liveat500px.adapter.PhotoListAdapter;
import com.example.liveat500px.dao.PhotoItemCollection;
import com.example.liveat500px.manager.HttpManager;
import com.example.liveat500px.manager.PhotoListManager;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment {

    ListView listView;
    PhotoListAdapter listAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    PhotoListManager photoListManager;
    Button btnPhoto;
    Boolean isLoadMore = false;
    MutableInteger lastPositionInteger;

    /**************
     * function Zone
     **************/

    public MainFragment() {
        super();
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initialize fragment
        init(savedInstanceState);
        if (savedInstanceState != null) {
            //restore instancee state
            onRestoreInstanceState(savedInstanceState);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        photoListManager = new PhotoListManager();
        lastPositionInteger = new MutableInteger(-1);

        File dir = getContext().getFilesDir();
        Log.d("fileTest",String.valueOf(dir));
        File file = new File(dir,"testfile.txt");
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write("Hello".getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here

        btnPhoto = (Button) rootView.findViewById(R.id.btnPhoto);

        btnPhoto.setOnClickListener(btnClickListener);

        listView = (ListView) rootView.findViewById(R.id.listView);
        listAdapter = new PhotoListAdapter(lastPositionInteger);
        listAdapter.setDao(photoListManager.getDao());
        listView.setAdapter(listAdapter);

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(pullToRefreshListener);

        listView.setOnScrollListener(lisViewScrollListener);
        if (savedInstanceState == null)
            refreshData();
    }

    private void refreshData() {
        if (photoListManager.getCount() == 0) {
            loadDataFromUrl();
        } else
            loadDataFromUrlNewer();
    }


    private void loadDataFromUrlNewer() {
        int maxId = photoListManager.getMaximumId();
        Call<PhotoItemCollection> call = HttpManager.getInstance().
                getService().
                loadPhotolistAfterId(maxId);
        call.enqueue(new PhotoListLoadCallBack(PhotoListLoadCallBack.MODE_RELOAD_NEWER));
    }

    private void loadDataFromUrl() {
        Call<PhotoItemCollection> call = HttpManager.getInstance().getService().loadPhotolist();
        call.enqueue(new PhotoListLoadCallBack(PhotoListLoadCallBack.MODE_RELOAD));
    }

    private void loadMoreDataFromUrlNewer() {
        if (isLoadMore)
            return;
        isLoadMore = true;
        int minId = photoListManager.getMinimumId();
        Call<PhotoItemCollection> call = HttpManager.getInstance().
                getService().
                loadPhotolistBeforeId(minId);
        call.enqueue(
                new PhotoListLoadCallBack(PhotoListLoadCallBack.MODE_LOAD_MORE));
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
        outState.putBundle("photoListManager",
                photoListManager.onSaveInstanceState());
        outState.putBundle("lastPositionInteger",
                lastPositionInteger.onSaveInstanceState());
    }

    /*
     * Restore Instance State Here
     */
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        //restore instance state
        photoListManager.onRestoreInstanceState(
                savedInstanceState.getBundle("photoListManager"));
        lastPositionInteger.onRestoreInstanceState(savedInstanceState.getBundle("lastPositionInteger"));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //
        if (savedInstanceState != null) {
            // Restore Instance State here
        }
    }

    private void showButtonNewPhotos() {
        btnPhoto.setVisibility(View.VISIBLE);
        Animation anim = AnimationUtils.loadAnimation(
                Contextor.getInstance().getContext(), R.anim.zoom_fade_in
        );
        btnPhoto.setAnimation(anim);
    }

    private void hideButtonNewPhotos() {
        btnPhoto.setVisibility(View.GONE);
        Animation anim = AnimationUtils.loadAnimation(
                Contextor.getInstance().getContext(), R.anim.zoom_fade_out
        );
        btnPhoto.setAnimation(anim);
    }

    private void showToast(String text) {
        Toast.makeText(Contextor.getInstance().getContext(),
                text,
                Toast.LENGTH_LONG).show();
    }

    /*************
     * listener Zone
     *************/

    final View.OnClickListener btnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == btnPhoto) {
                listView.smoothScrollToPosition(0);
                hideButtonNewPhotos();
            }
        }
    };

    SwipeRefreshLayout.OnRefreshListener pullToRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            refreshData();
        }
    };

    AbsListView.OnScrollListener lisViewScrollListener = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {

        }

        @Override
        public void onScroll(AbsListView view,
                             int firstVisibleItem,
                             int visibleItemCount,
                             int totalItemCount) {
            swipeRefreshLayout.setEnabled(firstVisibleItem == 0);
            if (firstVisibleItem + visibleItemCount >= totalItemCount) {
                if (photoListManager.getCount() > 0) {
                    loadMoreDataFromUrlNewer();
                }
            }
        }
    };

    /*************
     * inner class
     *************/
    class PhotoListLoadCallBack implements Callback<PhotoItemCollection> {
        public static final int MODE_RELOAD = 1;
        public static final int MODE_RELOAD_NEWER = 2;
        public static final int MODE_LOAD_MORE = 3;

        int mode;

        public PhotoListLoadCallBack(int mode) {
            this.mode = mode;
        }

        @Override
        public void onResponse(Call<PhotoItemCollection> call, Response<PhotoItemCollection> response) {
            swipeRefreshLayout.setRefreshing(false);
            if (response.isSuccessful()) {
                PhotoItemCollection dao = response.body();

                int firstVisiblePosition = listView.getFirstVisiblePosition();

                View c = listView.getChildAt(0);
                int top = c == null ? 0 : c.getTop();

                if (mode == MODE_RELOAD_NEWER) {
                    photoListManager.insertDaoAtTopPosition(dao);
                } else if (mode == MODE_LOAD_MORE) {
                    photoListManager.appendDaoAtTopPosition(dao);
                } else {
                    photoListManager.setDao(dao);
                }
                clearLoadingMoreFlagCapable(mode);
//                   PhotoListManagerOld.setDao(dao); //use singleton save data
                listAdapter.setDao(photoListManager.getDao());
                listAdapter.notifyDataSetChanged();

                if (mode == MODE_RELOAD_NEWER) {
                    int additionalSize =
                            (dao != null && dao.getData() != null) ? dao.getData().size() : 0;
                    listAdapter.increaseLastPosition(additionalSize);
                    listView.setSelectionFromTop(firstVisiblePosition + additionalSize
                            , top);
                    if (additionalSize > 0)
                        showButtonNewPhotos();
                    if (additionalSize == 0)
                        hideButtonNewPhotos();

                }
                showToast("load Completed");
            } else {
                clearLoadingMoreFlagCapable(mode);
                try {
                    showToast(response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onFailure(Call<PhotoItemCollection> call, Throwable t) {
            swipeRefreshLayout.setRefreshing(false);
            clearLoadingMoreFlagCapable(mode);
            showToast(t.toString());
        }

        private void clearLoadingMoreFlagCapable(int mode) {
            if (mode == MODE_LOAD_MORE)
                isLoadMore = false;
        }
    }

}
