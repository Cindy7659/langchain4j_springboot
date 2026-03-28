package com.yeyao.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    /**
     * 主键 ID
     */
    private Long id;

    /**
     * 考生姓名
     */
    private String name;

    /**
     * 考生性别
     */
    private String gender;

    /**
     * 考生手机号
     */
    private String phone;

    /**
     * 沟通时间
     */
    private LocalDateTime communicationTime;

    /**
     * 考生所处的省份
     */
    private String province;

    /**
     * 考生预估分数
     */
    private Integer estimatedScore;
}

