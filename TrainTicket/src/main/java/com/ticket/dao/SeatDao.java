package com.ticket.dao;

import com.ticket.entity.RemainTicket;
import com.ticket.entity.Seat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SeatDao {

    List<RemainTicket> getRemainTicket(@Param("trainId") String trainId,
                                       @Param("noFrom") int noFrom, @Param("noTo") int noTo);

    /***
     * 警告：seat表记录包括sta_no,因此在获取实际可用座位时需要注意一个座位对应若干条记录
     * @param trainId 车次号
     * @param noFrom 旅客的出发站号（相对于火车始发站）
     * @param noTo 旅客的到达站号（相对于火车始发站）
     * @param ticketType 请求的票型
     * @return Seat表中满足旅客出行的所有记录
     */
    List<Seat> getSeatsBySeatType(@Param("trainId") String trainId,
                                @Param("noFrom") int noFrom, @Param("noTo") int noTo,
                                @Param("ticketType") String ticketType);

    List<String> getExistSeatTypeByCarriage(@Param("trainId") String trainId);

    /***
     * 使用乐观锁的方式解决，写之前检查version是否为预期值（读时的值），若不是则说明在读写之间存在另一个线程写，若是则更新，并将version++
     * @return 受影响的行数，若返回0说明update失败
     */
    int updateSeatBySetY(@Param("trainId") String trainId,  @Param("staNo") int staNo, @Param("carr") int carr,
                               @Param("sRow") int sRow, @Param("sCol") int sCol, @Param("version") int version);

    void updateSeatBySetN(@Param("trainId") String trainId, @Param("staNo") int staNo, @Param("carr") int carr,
                          @Param("sRow") int sRow, @Param("sCol") int sCol);

    void insertSeat(@Param("trainId") String trainId, @Param("staNo") int staNo,
                    @Param("carr") int carr, @Param("sRow") int sRow, @Param("sCol") int sCol,
                    @Param("sale") String sale);

    Integer findNumOfSeatInOneCarriage(@Param("trainId") String trainId, @Param("carr") int carr);

    void deleteAllByOneTrainId(@Param("trainId") String trainId);
}
