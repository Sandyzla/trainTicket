package com.ticket.service;

import com.ticket.collection.AdminCarriage;
import com.ticket.dao.CarriageDao;
import com.ticket.dao.SeatDao;
import com.ticket.dao.TimeListDao;
import com.ticket.entity.Carriage;
import com.ticket.entity.TimeList;
import com.ticket.help.BaseResponse;
import com.ticket.help.BaseResponseArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminSeatServiceImpl implements AdminSeatService {
    @Autowired
    private CarriageDao carriageDao;
    @Autowired
    private SeatDao seatDao;
    @Autowired
    private TimeListService timeListService;

    @Autowired
    private TimeListDao timeListDao;
    @Override
    public BaseResponseArray<AdminCarriage> getAdminUpdateSeat(String trainId) {
        BaseResponseArray<AdminCarriage> response = new BaseResponseArray<>();

        TimeList timeListFirst = timeListDao.getTimeListByTrainId(trainId);
        if(timeListFirst == null)
        {
            response = new BaseResponseArray<>("error_1","未找到" + trainId +"的车次信息");
        }
        else{
            List<AdminCarriage> lists = new ArrayList<>();
            List<Carriage> carriages = carriageDao.selectByTrainId(trainId);
            if(carriages.isEmpty())
            {
                response = new BaseResponseArray<>("tip_1", "数据库未设置车厢数据,请在下面编辑");
            }
            else{
                for (Carriage item : carriages) {
                    Integer numOfSeat = 0;
                    //找到该车厢的座位信息,并转换为排数
                    numOfSeat = seatDao.findNumOfSeatInOneCarriage(trainId, item.getCarr());
                    if (numOfSeat == null) {
                        response = new BaseResponseArray<>("error_2","数据库已含车厢数据但未设置座位数据");
                    }
                    int numOfRow = switch (item.getSeatType()) {
                        case "商务座" -> numOfSeat / 3;
                        case "一等座" -> numOfSeat / 4;
                        case "二等座" -> numOfSeat / 5;
                        default -> 0;
                    };
                    lists.add(new AdminCarriage(trainId, item.getCarr(), item.getSeatType(), numOfRow));
                    response = new BaseResponseArray<>("success", lists);
                }
            }
        }
        return response;
    }

    int getMaxCol(String seatType) {
        return switch (seatType) {
            case "商务座" -> 3;
            case "一等座" -> 4;
            case "二等座" -> 5;
            default -> 0;
        };
    }

    @Override
    public BaseResponse adminRectifySeat(List<AdminCarriage> lists) {
        BaseResponse response = new BaseResponse();
        if(lists.isEmpty()){
            response = new BaseResponse("fail","不能提交空数据");
        }
        else{
            clearSeatCarr(lists.get(0).getTrainId()); //先清空seat,carriage表的对应车次数据,防止插入数据时因重复数据被MySQL中止
            for (AdminCarriage updateSeat : lists) {
                int maxCol = getMaxCol(updateSeat.getSeatType());
                int maxRow = updateSeat.getsRow();
                int maxSta = timeListService.findCountByOneTrain(updateSeat.getTrainId());
                for (int row = 1; row <= maxRow; row++) {
                    for (int col = 1; col <= maxCol; col++) {
                        for (int staNo = 1; staNo < maxSta; staNo++) {
                            seatDao.insertSeat(updateSeat.getTrainId(), staNo, updateSeat.getCarr(), row, col, "N");
                        }
                    }
                }
                carriageDao.insertCarriage(updateSeat.getTrainId(), updateSeat.getCarr(), updateSeat.getSeatType());
            }
            response = new BaseResponse("success");
        }
        return response;
    }

    @Override
    public void clearSeatCarr(String trainId) {
        seatDao.deleteAllByOneTrainId(trainId);
        carriageDao.deleteAllByTrainId(trainId);
    }
}
