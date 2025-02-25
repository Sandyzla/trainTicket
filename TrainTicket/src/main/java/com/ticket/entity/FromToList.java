package com.ticket.entity;

import com.ticket.collection.SeatNumAndPrice;

import java.sql.Time;
import java.util.Map;

//fromTo界面的完整列车信息
public class FromToList {
    private String trainId;
    private String staFrom;
    private int noFrom;
    private String staTo;
    private int noTo;
    private Time tGo;
    private Time tArrive;
    private int duration;
    private Map<String, SeatNumAndPrice> seatMap;

    public FromToList(String trainId, String staFrom, int noFrom, String staTo, int noTo, Time tGo, Time tArrive) {
        this.trainId = trainId;
        this.staFrom = staFrom;
        this.noFrom = noFrom;
        this.staTo = staTo;
        this.noTo = noTo;
        this.tGo = tGo;
        this.tArrive = tArrive;
    }

    public FromToList(String trainId, String staFrom, int noFrom, String staTo, int noTo, Time tGo, Time tArrive, int duration, Map<String, SeatNumAndPrice> seatMap) {
        this.trainId = trainId;
        this.staFrom = staFrom;
        this.noFrom = noFrom;
        this.staTo = staTo;
        this.noTo = noTo;
        this.tGo = tGo;
        this.tArrive = tArrive;
        this.duration = duration;
        this.seatMap = seatMap;
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getStaFrom() {
        return staFrom;
    }

    public void setStaFrom(String staFrom) {
        this.staFrom = staFrom;
    }

    public int getNoFrom() {
        return noFrom;
    }

    public void setNoFrom(int noFrom) {
        this.noFrom = noFrom;
    }

    public String getStaTo() {
        return staTo;
    }

    public void setStaTo(String staTo) {
        this.staTo = staTo;
    }

    public int getNoTo() {
        return noTo;
    }

    public void setNoTo(int noTo) {
        this.noTo = noTo;
    }

    public Time gettGo() {
        return tGo;
    }

    public void settGo(Time tGo) {
        this.tGo = tGo;
    }

    public Time gettArrive() {
        return tArrive;
    }

    public void settArrive(Time tArrive) {
        this.tArrive = tArrive;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Map<String, SeatNumAndPrice> getSeatMap() {
        return seatMap;
    }

    public void setSeatMap(Map<String, SeatNumAndPrice> seatMap) {
        this.seatMap = seatMap;
    }
}
