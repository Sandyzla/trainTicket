package com.ticket.collection;

public class AdminCarriage {
    private String trainId;
    private int carr;
    private String seatType;
    private int sRow;

    public AdminCarriage(String trainId, int carr, String seatType, int sRow) {
        this.trainId = trainId;
        this.carr = carr;
        this.seatType = seatType;
        this.sRow = sRow;
    }

    @Override
    public String toString() {
        return "Admin_updateSeat{" +
                "trainId='" + trainId + '\'' +
                ", carr=" + carr +
                ", seatType='" + seatType + '\'' +
                ", sRow=" + sRow +
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

    public int getsRow() {
        return sRow;
    }

    public void setsRow(int sRow) {
        this.sRow = sRow;
    }
}
