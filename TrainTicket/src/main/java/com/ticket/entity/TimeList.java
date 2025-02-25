package com.ticket.entity;

import java.sql.Time;

public class TimeList {

    private String trainId;
    private String staBegin;
    private String staEnd;
    private Time tBegin;
    private Time tEnd;
    private String type;
    private int no;
    private String staName;
    private Time tArrive;
    private Time tGo;
    private String tMid;
    private String open;
    private int grow;

    public TimeList() {
    }
    public TimeList(String trainId, String staBegin, String staEnd, Time tBegin, Time tEnd, String type, int no, String staName, Time tArrive, Time tGo, String tMid, String open, int grow) {
        this.trainId = trainId;
        this.staBegin = staBegin;
        this.staEnd = staEnd;
        this.tBegin = tBegin;
        this.tEnd = tEnd;
        this.type = type;
        this.no = no;
        this.staName = staName;
        this.tArrive = tArrive;
        this.tGo = tGo;
        this.tMid = tMid;
        this.open = open;
        this.grow = grow;
    }

    @Override
    public String toString() {
        return "TimeList{" +
                "trainId='" + trainId + '\'' +
                ", staBegin='" + staBegin + '\'' +
                ", staEnd='" + staEnd + '\'' +
                ", tBegin=" + tBegin +
                ", tEnd=" + tEnd +
                ", type='" + type + '\'' +
                ", no=" + no +
                ", staName='" + staName + '\'' +
                ", tArrive=" + tArrive +
                ", tGo=" + tGo +
                ", tMid='" + tMid + '\'' +
                ", open='" + open + '\'' +
                ", grow=" + grow +
                '}';
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getStaBegin() {
        return staBegin;
    }

    public void setStaBegin(String staBegin) {
        this.staBegin = staBegin;
    }

    public String getStaEnd() {
        return staEnd;
    }

    public void setStaEnd(String staEnd) {
        this.staEnd = staEnd;
    }

    public Time gettBegin() {
        return tBegin;
    }

    public void settBegin(Time tBegin) {
        this.tBegin = tBegin;
    }

    public Time gettEnd() {
        return tEnd;
    }

    public void settEnd(Time tEnd) {
        this.tEnd = tEnd;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getStaName() {
        return staName;
    }

    public void setStaName(String staName) {
        this.staName = staName;
    }

    public Time gettArrive() {
        return tArrive;
    }

    public void settArrive(Time tArrive) {
        this.tArrive = tArrive;
    }

    public Time gettGo() {
        return tGo;
    }

    public void settGo(Time tGo) {
        this.tGo = tGo;
    }

    public String gettMid() {
        return tMid;
    }

    public void settMid(String tMid) {
        this.tMid = tMid;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public int getGrow() {
        return grow;
    }

    public void setGrow(int grow) {
        this.grow = grow;
    }

}
