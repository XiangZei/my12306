package edu.neu.my12306.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentTabHost;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;


import com.example.my12306.R;
import edu.neu.my12306.database.UserDao;
import edu.neu.my12306.entity.Account;
import edu.neu.my12306.util.MainTabs;

public class MainActivity extends AppCompatActivity {
    FragmentTabHost tabHost;
    UserDao userDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        userDao = new UserDao(MainActivity.this);
        String userName = intent.getStringExtra("username");
        Account account = userDao.queryAccountByName(userName);
        Account.setAccount(account);

        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);

        tabHost.setup(getApplicationContext(),getSupportFragmentManager(),R.id.realtabcontent);


        MainTabs[] tabs =MainTabs.values();
        for(int tabIndex=0;tabIndex<tabs.length;tabIndex++){
            MainTabs mainTabs = tabs[tabIndex];
            //新建tab
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(mainTabs.getName());
            //获取tab的view
            View indicator = View.inflate(getApplicationContext(), R.layout.tab, null);
            //获取底部导航栏的TextView
            TextView tv_indicator = (TextView) indicator.findViewById(R.id.tv_indicator);
            //获取maintabs中的tab名称和图标
            Drawable drawable = getApplicationContext().getResources().getDrawable(mainTabs.getIcon());
            tv_indicator.setText(mainTabs.getName());
            //将自定义的图标插入到导肮栏
            tv_indicator.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
            //设置导航栏
            tabSpec.setIndicator(indicator);
            //将tab增加到tabhost中
            tabHost.addTab(tabSpec, mainTabs.getCla(), null);
        }




    }
}
