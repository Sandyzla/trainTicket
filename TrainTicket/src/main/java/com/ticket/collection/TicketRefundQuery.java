package com.ticket.collection;

public class TicketRefundQuery {
    private String idNum;
    private String trainId;

    @Override
    public String toString() {
        return "TicketRefundQuery{" +
                "idNum='" + idNum + '\'' +
                ", trainId='" + trainId + '\'' +
                '}';
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }
}
