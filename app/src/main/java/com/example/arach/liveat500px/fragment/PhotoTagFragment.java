package com.example.arach.liveat500px.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arach.liveat500px.R;
import com.example.arach.liveat500px.dao.PhotoItemDao;

import org.w3c.dom.Text;

/**
 * Created by nuuneoi on 11/16/2014.
 */
public class PhotoTagFragment extends Fragment {

    PhotoItemDao dao;
    TextView tvTag;

    public PhotoTagFragment() {
        super();
    }

    public static PhotoTagFragment newInstance(PhotoItemDao dao) {
        PhotoTagFragment fragment = new PhotoTagFragment();
        Bundle args = new Bundle();
        args.putParcelable("dao",dao);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        dao = getArguments().getParcelable("dao");

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tags_summary, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        tvTag = rootView.findViewById(R.id.tv_tags);

        String tag = "";

        for (int i=0; i<dao.getTags().size(); i++){
            tag = tag + ", " + dao.getTags().get(i);
        }

        tvTag.setText(tag);
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

    private void onRestoreInstanceState(Bundle savedInstanceState) {
    }

    /*
     * Restore Instance State Here
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore Instance State here
        }
    }
}
