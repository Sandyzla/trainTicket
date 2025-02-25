package com.ticket.entity;

public class Carriage {
    private String trainId;
    private int carr;
    private String seatType;

    @Override
    public String toString() {
        return "Carriage{" +
                "trainId='" + trainId + '\'' +
                ", carr=" + carr +
                ", seatType='" + seatType + '\'' +
                '}';
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public int getCarr() {
        return carr;
    }

    public void setCarr(int carr) {
        this.carr = carr;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }
}
