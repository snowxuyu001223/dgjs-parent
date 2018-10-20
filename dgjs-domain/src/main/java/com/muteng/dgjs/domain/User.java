package com.muteng.dgjs.domain;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * date: 2018/10/20 00:07
 * author: snowxuyu
 * describe:
 */
@Data
@ToString
public class User implements Serializable {

    private Long id;

    private String createName;

    private Date createTime;

    private String updateName;

    private Date updateTime;

    private String phone;

    private String address;

    private String userName;

    private String passWord;
}
