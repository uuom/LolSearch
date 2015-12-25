package com.asiainfo.yangxp5.musicpalyer;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.asiainfo.yangxp5.musicpalyer.utils.CommUtils;
import com.asiainfo.yangxp5.musicpalyer.utils.Contant;
import com.asiainfo.yangxp5.musicpalyer.view.fragment.HeroRecordFragment;
import com.asiainfo.yangxp5.musicpalyer.view.fragment.PlayerDetailFragment;
import com.asiainfo.yangxp5.musicpalyer.view.fragment.PlayerRecordFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchResultActivity extends AppCompatActivity {

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.tl_searchAty_tabs) TabLayout tl_searchAty_tabs;
    @Bind(R.id.vp_searchAty_viewPager) ViewPager vp_searchAty_viewPager;

    private String serverName;
    private String palyeName;

    private String[] tabTitles;

    private FragmentPagerAdapter mFragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        ButterKnife.bind(this);

        serverName = getIntent().getStringExtra(Contant.INTENT_EXTRA_SERVERNAME);
        palyeName = getIntent().getStringExtra(Contant.INTENT_EXTRA_PLAYERNAME);

        setTranslucentStatus();
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(palyeName);

        tabTitles = getResources().getStringArray(R.array.searchResultTabTitleStringArray);
        mFragmentPagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager());
        vp_searchAty_viewPager.setAdapter(mFragmentPagerAdapter);
        tl_searchAty_tabs.setupWithViewPager(vp_searchAty_viewPager);
    }




    class SimpleFragmentPagerAdapter extends FragmentPagerAdapter{

        public SimpleFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return tabTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position){
                case 0:
                    fragment = PlayerDetailFragment.newInstance();
                    break;
                case 1:
                    fragment = PlayerRecordFragment.newInstance();
                    break;
                case 2:
                    fragment = HeroRecordFragment.newInstance();
                    break;
            }
            return fragment;
        }
    }



    private void setTranslucentStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        LinearLayout linear_bar = (LinearLayout) findViewById(R.id.linear_bar);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) linear_bar.getLayoutParams();
        params.height = new CommUtils().getStatusBarHeight(this);
        linear_bar.setLayoutParams(params);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_result, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
