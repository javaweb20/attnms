package com.newedu.attnms.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {
    private int id;
    private String useranme;
    private String pwd;
}
