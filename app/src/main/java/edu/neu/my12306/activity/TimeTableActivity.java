package edu.neu.my12306.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.my12306.R;
import edu.neu.my12306.entity.TicketItem;
import edu.neu.my12306.adapter.TicketAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import javax.net.ssl.HttpsURLConnection;

public class TimeTableActivity extends AppCompatActivity {
    private String startStation;
    private String arriveStation;
    private String date1;
    private boolean isHigh;
    private String stringUrl;
    private String acceptData ="";
    List<TicketItem> tickets = new ArrayList<>();
    UpdateHandler handler;
    TextView startEnd;
    private static final String TAG = "TimeTableActivity";
    CountDownLatch latch = new CountDownLatch(1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);
        startEnd = findViewById(R.id.startEnd);
        getSupportActionBar().hide();
        Intent intent = getIntent();
        startStation = intent.getStringExtra("from");
        arriveStation = intent.getStringExtra("to");
        startEnd.setText(startStation+"-"+arriveStation);
        date1 = intent.getStringExtra("data1");
        isHigh = intent.getBooleanExtra("isHigh",false);
        int ishigh = isHigh?1:0;

        stringUrl = "https://api.jisuapi.com/train/station2s?appkey=894be8686f26eed6&start="+startStation
                +"&end="+arriveStation+"&ishigh="+ishigh+"&date="+date1;
        handler = new UpdateHandler();
        latch.countDown();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    latch.await();//确保信息地点信息加载完毕在开始查询
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try{
                    URL url  = new URL(stringUrl);
                    HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();
                    Log.d(TAG, "run: "+connection.getResponseCode());
                    if(connection.getResponseCode()== HttpURLConnection.HTTP_OK){
                        InputStream inputStream = connection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
                        String line;
                        while((line=bufferedReader.readLine())!=null){
                            acceptData+=line;
                        }
                    }
                }catch (Exception ex ){
                    ex.printStackTrace();
                }
                try {
                    JSONObject o = new JSONObject(acceptData);

                    JSONArray o1 = o.getJSONObject("result").getJSONArray("list");
                    for(int i=0;i<o1.length();i++){
                        JSONObject o2 = (JSONObject) o1.get(i);
                        String trainID = o2.getString("trainno");
                        String trainType = o2.getString("typename");
                        String startStation = o2.getString("station");
                        String arriveStation = o2.getString("endstation");
                        String startTime = o2.getString("departuretime");
                        String arriveTime = o2.getString("arrivaltime");
                        String totalTime = o2.getString("costtime");
                        TicketItem item = new TicketItem(trainID,trainType,startStation,arriveStation,startTime,arriveTime,totalTime);
                        tickets.add(item);
                        Log.d(TAG, "run: "+o2.toString());
                    }
                    handler.sendMessage(new Message());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    class UpdateHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            TicketAdapter adapter = new TicketAdapter(TimeTableActivity.this,R.layout.ticket_item,tickets);
            ListView ticketList = findViewById(R.id.ticketlist);
            ticketList.setAdapter(adapter);
        }
    }


}
