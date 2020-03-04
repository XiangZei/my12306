package edu.neu.my12306.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.my12306.R;
import edu.neu.my12306.entity.TicketItem;

import java.util.List;

public class TicketAdapter extends ArrayAdapter<TicketItem> {
    private int resourceId;
    public TicketAdapter(Context context, int textViewResourceId, List<TicketItem> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }


    @Override
    public View getView(int position,  View convertView,ViewGroup parent) {
        TicketItem item = getItem(position);
        View view;
        if(convertView==null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        }else{
            view = convertView;
        }
        TextView tv_trainID = view.findViewById(R.id.trainID);
        tv_trainID.setText(item.getTrainID());
        TextView tv_trainType =view.findViewById(R.id.trainType);
        tv_trainType.setText(item.getTrainType());
        TextView tv_startStation = view.findViewById(R.id.startStation);
        tv_startStation.setText(item.getStartStation());
        TextView tv_arriveStation = view.findViewById(R.id.arriveStation);
        tv_arriveStation.setText(item.getArriveStation());
        TextView tv_startTime = view.findViewById(R.id.startTime);
        tv_startTime.setText(item.getStartTime());
        TextView tv_arriveTime = view.findViewById(R.id.arriveTime);
        tv_arriveTime.setText(item.getArriveTime());
        TextView tv_totalTime = view.findViewById(R.id.totalTime);
        tv_totalTime.setText(item.getTotalTime());

        return view;
    }


}
