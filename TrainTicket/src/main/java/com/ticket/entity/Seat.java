package com.ticket.entity;

public class Seat {
    //carr car1,s_row,s_col
    private String trainId;
    private String staNo;
    private int carr;
    private int sRow;
    private int sCol;
    private String sale;
    private int version; //版本控制,用于实现乐观锁

    public Seat(String trainId, String staNo, int carr, int sRow, int sCol, String sale, int version) {
        this.trainId = trainId;
        this.staNo = staNo;
        this.carr = carr;
        this.sRow = sRow;
        this.sCol = sCol;
        this.sale = sale;
        this.version = version;
    }

    @Override
    public String toString() {
        return "Seat{" +
              "trainId='" + trainId + '\'' +
              ", staNo='" + staNo + '\'' +
              ", carr=" + carr +
              ", sRow=" + sRow +
              ", sCol=" + sCol +
              ", sale='" + sale + '\'' +
              ", version=" + version +
              '}';
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getStaNo() {
        return staNo;
    }

    public void setStaNo(String staNo) {
        this.staNo = staNo;
    }

    public int getCarr() {
        return carr;
    }

    public void setCarr(int carr) {
        this.carr = carr;
    }

    public int getsRow() {
        return sRow;
    }

    public void setsRow(int sRow) {
        this.sRow = sRow;
    }

    public int getsCol() {
        return sCol;
    }

    public void setsCol(int sCol) {
        this.sCol = sCol;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
