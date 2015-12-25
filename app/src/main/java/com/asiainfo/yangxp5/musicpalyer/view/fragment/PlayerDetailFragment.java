package com.asiainfo.yangxp5.musicpalyer.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asiainfo.yangxp5.musicpalyer.R;

/**
 * Created by uuom on 15-12-24.
 */
public class PlayerDetailFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_palyer_detail, container ,false);
        return view;
    }

    public static Fragment newInstance() {
        return new PlayerDetailFragment();
    }
}
