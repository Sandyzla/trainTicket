package com.ticket.entity;
//仅用于返回给前端的余票信息,如{’一等座‘：3张, '二等座':无票}
public class RemainTicket {
    private String ticketType;
    private int ticketNum;

    @Override
    public String toString() {
        return "RemainTicket{" +
                "ticketType='" + ticketType + '\'' +
                ", ticketNum=" + ticketNum +
                '}';
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public int getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(int ticketNum) {
        this.ticketNum = ticketNum;
    }
}
