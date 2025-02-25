package com.ticket.entity;

public class TrainSeat {
    private String trainId;
    private int staNo;
    private int carr;
    private int sRow;
    private int sCol;
    private String sale;

    @Override
    public String toString() {
        return "Seat{" +
                "trainId='" + trainId + '\'' +
                ", staNo=" + staNo +
                ", carr=" + carr +
                ", sRow=" + sRow +
                ", sCol=" + sCol +
                ", sale='" + sale + '\'' +
                '}';
    }
}
