package edu.neu.my12306.entity;

import java.util.List;
import java.util.Map;

public class TicketItem {
    String trainID;
    String trainType;
    String startStation;
    String arriveStation;
    String startTime;
    String arriveTime;
    String totalTime;
    List<Map<String,String>> fee;

    public TicketItem(String trainID, String trainType, String startStation, String arriveStation, String startTime, String arriveTime, String totalTime) {
        this.trainID = trainID;
        this.trainType = trainType;
        this.startStation = startStation;
        this.arriveStation = arriveStation;
        this.startTime = startTime;
        this.arriveTime = arriveTime;
        this.totalTime = totalTime;
    }

    public String getTrainID() {
        return trainID;
    }

    public void setTrainID(String trainID) {
        this.trainID = trainID;
    }

    public String getTrainType() {
        return trainType;
    }

    public void setTrainType(String trainType) {
        this.trainType = trainType;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getArriveStation() {
        return arriveStation;
    }

    public void setArriveStation(String arriveStation) {
        this.arriveStation = arriveStation;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public List<Map<String, String>> getFee() {
        return fee;
    }

    public void setFee(List<Map<String, String>> fee) {
        this.fee = fee;
    }
}
