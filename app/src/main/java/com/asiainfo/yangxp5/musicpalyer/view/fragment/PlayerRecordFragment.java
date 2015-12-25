package com.asiainfo.yangxp5.musicpalyer.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asiainfo.yangxp5.musicpalyer.R;

public class PlayerRecordFragment extends Fragment {

    public static PlayerRecordFragment newInstance() {
        PlayerRecordFragment fragment = new PlayerRecordFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player_record, container, false);
    }

}
