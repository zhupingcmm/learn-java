package com.mf.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDto {
    private Long id;
    private Long orderNo;
    private Integer amount;
    private Boolean status;
    private Long userId;
    private Long couponRecordId;
    private Date createTime;
    private Date updateTime;
}
