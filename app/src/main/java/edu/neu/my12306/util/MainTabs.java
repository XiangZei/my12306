package edu.neu.my12306.util;

import com.example.my12306.R;
import edu.neu.my12306.activity.AccountFragment;
import edu.neu.my12306.activity.BookingFragment;
import edu.neu.my12306.activity.OrderFragment;

public enum MainTabs {
    Home(0,"首页", R.drawable.selector_tab_home, BookingFragment.class),
    Order(1,"订单",R.drawable.selector_tab_home, OrderFragment.class),
    Account(2,"我的", R.drawable.selector_tab_home, AccountFragment.class);


    private int i;
    private String name;
    private int icon;
    private Class<?> cla;

    MainTabs(int i, String name, int icon, Class<?> cla) {
        this.i = i;
        this.name = name;
        this.icon = icon;
        this.cla = cla;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public Class<?> getCla() {
        return cla;
    }

    public void setCla(Class<?> cla) {
        this.cla = cla;
    }
}
