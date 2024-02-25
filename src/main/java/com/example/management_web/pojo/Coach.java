package com.example.management_web.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 教练表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Coach {

    private Integer id;  //id
    private String coachusername; //教练用户名
    private String coachpassword;
    private String course;
    private String coachname;
    private String coachgender;


//    private LocalDateTime createTime;
//    private LocalDateTime updateTime;

}
