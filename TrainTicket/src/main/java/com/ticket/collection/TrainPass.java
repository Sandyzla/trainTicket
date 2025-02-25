package com.ticket.collection;

public class TrainPass {
    private String trainId;
    private int noFrom;
    private int noTo;

    @Override
    public String toString() {
        return "TrainPass{" +
                "trainId='" + trainId + '\'' +
                ", noFrom=" + noFrom +
                ", noTo=" + noTo +
                '}';
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public int getNoFrom() {
        return noFrom;
    }

    public void setNoFrom(int noFrom) {
        this.noFrom = noFrom;
    }

    public int getNoTo() {
        return noTo;
    }

    public void setNoTo(int noTo) {
        this.noTo = noTo;
    }
}
