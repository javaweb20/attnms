package com.newedu.attnms.entity;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@ToString
//@NoArgsConstructor
//@AllArgsConstructor
public class LoginRecord {
    private int id;
    @NotNull
    private String username;
    @NotNull
    private String locked;
    @NotNull
    private int times;
    private String date;
    public LoginRecord(){

    }
    public LoginRecord(String username,String locked,int times){
        this.username = username;
        this.locked = locked;
        this.times = times;
    }

}
