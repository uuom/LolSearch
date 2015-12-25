package com.asiainfo.yangxp5.musicpalyer;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.asiainfo.yangxp5.musicpalyer.utils.CommUtils;
import com.asiainfo.yangxp5.musicpalyer.utils.Contant;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.p_mainAty_serverType) Spinner p_mainAty_serverType;
    @Bind(R.id.p_mainAty_serverName) Spinner p_mainAty_serverName;
    @Bind(R.id.et_mainAty_playerName) EditText et_mainAty_playerName;
    @Bind(R.id.btn_mainAty_search) Button btn_mainAty_search;

    String[] serverTypes;
    String[] serverNames;

    ArrayAdapter<String> serverTypeAdapter;
    ArrayAdapter<String> serverNameAdapter;

    private String currentServerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        setTranslucentStatus();

        serverTypes = getResources().getStringArray(R.array.serverTypeStringArray);
        serverNames = getResources().getStringArray(R.array.dianXinServerNameStringArray);

        serverTypeAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, serverTypes);
        p_mainAty_serverType.setAdapter(serverTypeAdapter);
        serverNameAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, serverNames);
        p_mainAty_serverName.setAdapter(serverNameAdapter);

        //网络下拉菜单
        p_mainAty_serverType.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String serverType = serverTypes[i];
                if ("电信".equals(serverType)) {
                    serverNames = getResources().getStringArray(R.array.dianXinServerNameStringArray);
                } else if ("网通".equals(serverType)) {
                    serverNames = getResources().getStringArray(R.array.wangTongServerNameStringArray);
                }
                serverNameAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, serverNames);
                p_mainAty_serverName.setAdapter(serverNameAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        p_mainAty_serverName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currentServerName = serverNames[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        //查询按钮
        btn_mainAty_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String playerName = et_mainAty_playerName.getText().toString();
                if ("".equals(playerName)){
                    Toast.makeText(MainActivity.this,"请输入角色名称",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(MainActivity.this,SearchResultActivity.class);
                    intent.putExtra(Contant.INTENT_EXTRA_SERVERNAME, currentServerName);
                    intent.putExtra(Contant.INTENT_EXTRA_PLAYERNAME, playerName);
                    startActivity(intent);
                }
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
}
