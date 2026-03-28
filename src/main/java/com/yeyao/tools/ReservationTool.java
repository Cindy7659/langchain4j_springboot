package com.yeyao.tools;

import com.yeyao.Service.ReservationService;
import com.yeyao.pojo.Reservation;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ReservationTool {

    @Autowired
    private ReservationService reservationService;

    // 1.工具方法：添加预约信息
    @Tool("添加预约信息")
    public void addReservation(
            @P("考生姓名") String name,
            @P("考生性别") String gender,
            @P("考生手机号") String phone,
            @P("预约时间： 系统当前时间") String communicationTime,
            @P("考生所处的省份") String province,
            @P("预估分数") Integer estimatedScore
    ) {
        /*Reservation time = new Reservation(null, name, gender, phone,
                LocalDateTime.parse(communicationTime), province, estimatedScore);*/
        Reservation time = new Reservation(null, name, gender, phone,
                LocalDateTime.now(), province, estimatedScore);
        reservationService.addReservation(time);
    }

    // 2.工具方法：根据手机号查询预约信息
    @Tool("根据手机号查询预约信息")
    public Reservation selectByPhone(@P("手机号") String phone) {
        return reservationService.selectByPhone(phone);
    }
}
