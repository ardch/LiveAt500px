package com.example.arach.liveat500px.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.arach.liveat500px.R;
import com.example.arach.liveat500px.dao.PhotoItemDao;
import com.inthecheesefactory.thecheeselibrary.view.SlidingTabLayout;

/**
 * Created by nuuneoi on 11/16/2014.
 */
public class MoreInfoFragment extends Fragment {

    ViewPager viewPager;
    private SlidingTabLayout slidingTabLayout;
    PhotoItemDao dao;

    public MoreInfoFragment() {
        super();
    }

    public static MoreInfoFragment newInstance(PhotoItemDao dao) {
        MoreInfoFragment fragment = new MoreInfoFragment();
        Bundle args = new Bundle();    // Fragment's variable -If fragment destroyed these not lose
        args.putParcelable("dao", dao);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init(savedInstanceState);

        dao = getArguments().getParcelable("dao");

        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_moreinfo, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void init(Bundle savedInstanceState){
        setHasOptionsMenu(true);
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        viewPager = rootView.findViewById(R.id.viewPager);
        viewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                switch (i){
                    case 0 : return PhotoSummaryFragment.newInstance(dao);
                    case 1 : return PhotoInfoFragment.newInstance(dao);
                    case 2 : return PhotoTagFragment.newInstance(dao);
                    default: return null;
                }
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                switch(position){
                    case 0 : return "Summary";
                    case 1 : return "Info";
                    case 2 : return "Tags";
                    default: return null;
                }
            }
        });
        slidingTabLayout = (SlidingTabLayout) rootView.findViewById(R.id.slidingLayout);
        slidingTabLayout.setViewPager(viewPager);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_more_info, menu);

        MenuItem menuItem =  menu.findItem(R.id.action_share);
        ShareActionProvider shareActionProvider =
                (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        shareActionProvider.setShareIntent(getShareIntent());
    }

    private Intent getShareIntent(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        intent.putExtra(Intent.EXTRA_TEXT, "Extra Text");
        return intent;
    }
}
