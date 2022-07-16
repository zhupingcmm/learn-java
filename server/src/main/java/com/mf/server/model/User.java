package com.mf.server.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class User {

    private Long id;
    private String account;
    private String name;
    private String password;
    private int role;
    private int status;
    private Date createTime;
    private Date updateTime;
}
