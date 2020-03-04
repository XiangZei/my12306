package edu.neu.my12306.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.my12306.R;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class BookingFragment extends Fragment {
    private Button btSearch;
    private TextView tvD,tvM,tvY;
    private TextView spFrom,spTo;
    private LinearLayout ll;
    private Calendar mycalendar;
    private String StartStation;
    private String ArriveStation;
    private String date1;
    private CheckBox isHigh;
    private String currentCity="沈阳市";
    private LocationClient mLocationClient;



    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_booking,container,false);
        btSearch = view.findViewById(R.id.search_time_table);
        tvY = (TextView) view.findViewById(R.id.tvYear);
        tvM = (TextView) view.findViewById(R.id.tvMouth);
        tvD = (TextView) view.findViewById(R.id.tvDay);
        ll = (LinearLayout) view.findViewById(R.id.linearLayout_startDate);//日期选择
        spFrom = view.findViewById(R.id.spinner1);
        spTo = view.findViewById(R.id.spinner2);
        isHigh  = view.findViewById(R.id.isHigh);

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        new FetchCurrentCity().execute(1);
        Date date = new Date();


        mycalendar = Calendar.getInstance(Locale.CHINA);
        tvY.setText( mycalendar.get(Calendar.YEAR)+"");
        tvM.setText( ( mycalendar.get(Calendar.MONTH)+1)+"");
        tvD.setText( mycalendar.get(Calendar.DAY_OF_MONTH)+"");
        //日期布局管理器 绑定单击事件
        ll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // 设置当前日期
                DatePickerDialog dialog = new DatePickerDialog(Objects.requireNonNull(getContext()),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // 每次保存设置的日期
                                mycalendar.set(Calendar.YEAR, year);
                                mycalendar.set(Calendar.MONTH, monthOfYear);
                                mycalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                tvY.setText(year+ "");
                                tvM.setText((monthOfYear+1) + "");
                                tvD.setText(dayOfMonth + "");
                                date1 = year+"-"+(monthOfYear+1)+"-"+dayOfMonth;
                            }
                        },
                        mycalendar.get(Calendar.YEAR),//默认当前日期
                        mycalendar.get(Calendar.MONTH),
                        mycalendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });


        btSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),TimeTableActivity.class);
                intent.putExtra("from",StartStation);
                intent.putExtra("to",ArriveStation);
                intent.putExtra("date1", date1);
                intent.putExtra("isHigh",isHigh.isChecked());
                startActivity(intent);
            }
        });

        spFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CityPickerActivity.class);
                intent.putExtra("current_city",currentCity);
                startActivityForResult(intent,1);
            }
        });
        spTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CityPickerActivity.class);
                intent.putExtra("current_city",currentCity);
                startActivityForResult(intent,2);
            }
        });


    }



    public void initLocation(){
        mLocationClient = new LocationClient(getContext());
        LocationClientOption option = new LocationClientOption();
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
        mLocationClient.registerLocationListener(new MyLocationListener());
    }

    public class MyLocationListener extends BDAbstractLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if(bdLocation.getCity()!=null){
                currentCity = bdLocation.getCity();
            }
            currentCity = currentCity.substring(0,currentCity.length()-1);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case 1:
                if(resultCode== Activity.RESULT_OK ){
                    String city = data.getStringExtra("address");
                    spFrom.setText(city);
                    StartStation = city;
                }
                break;
            case 2:
                if(resultCode== Activity.RESULT_OK ){
                    String city = data.getStringExtra("address");
                    spTo.setText(city);
                    ArriveStation = city;
                }
                break;
        }
    }

    class FetchCurrentCity extends AsyncTask<Integer,Integer,String>{
        @Override
        protected void onPostExecute(String s) {
            if(s!=null){
                mLocationClient.stop();
            }
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(Integer... integers) {
            initLocation();
            mLocationClient.start();
            try{

                Thread.sleep(500);
                while(currentCity==null){
                    Thread.sleep(500);
                    if(currentCity!=null){
                        return currentCity;
                    }
                }

            }catch (InterruptedException ex){
                ex.printStackTrace();
            }
            return null;
        }
    }
}
