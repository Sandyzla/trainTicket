package com.ticket.service;

import com.ticket.collection.AdminCarriage;
import com.ticket.help.BaseResponse;
import com.ticket.help.BaseResponseArray;

import java.util.List;

public interface AdminSeatService {
    public BaseResponseArray<AdminCarriage> getAdminUpdateSeat(String trainId);

    public BaseResponse adminRectifySeat(List <AdminCarriage> updateSeat);

    public void clearSeatCarr(String trainId);
}
