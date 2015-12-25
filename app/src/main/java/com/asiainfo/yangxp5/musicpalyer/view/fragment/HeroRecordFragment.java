package com.asiainfo.yangxp5.musicpalyer.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asiainfo.yangxp5.musicpalyer.R;

public class HeroRecordFragment extends Fragment {


    public static HeroRecordFragment newInstance() {
        HeroRecordFragment fragment = new HeroRecordFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hero_record, container, false);
    }

}
