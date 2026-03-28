package com.yeyao.Service;


import com.yeyao.mapper.ReservationMapper;
import com.yeyao.pojo.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @Autowired
    private ReservationMapper reservationMapper;

    // 添加预约信息
    public void addReservation(Reservation reservation) {
        reservationMapper.insertReservation(reservation);
    }

    // 根据手机号查询预约信息
    public Reservation selectByPhone(String phone) {
        return reservationMapper.selectByPhone(phone);
    }
}
