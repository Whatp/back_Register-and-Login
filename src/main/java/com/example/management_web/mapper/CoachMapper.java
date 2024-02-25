package com.example.management_web.mapper;

import com.example.management_web.pojo.Coach;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CoachMapper {
    /***
     * 查询全部数据
     * @return
     */
    @Select("select id, course, coachname, coachgender from web_system.coach")
    List<Coach> list();

    /**
     * 插入数据
     *
     * @param coach
     * @return
     */
    @Insert("insert into web_system.coach(course, coachname,coachpassword, coachgender) values(#{course},#{coachname},#{coachpassword},#{coachgender})")
    int insert(Coach coach);

    @Update("update web_system.coach set coachname=#{coachName},coachgender=#{coachGender},course=#{course} where id=#{id}")
    int update(Coach coach);

    /**
     * 删除数据
     *
     * @param id
     */
    @Delete("delete from web_system.coach where id=#{id}")
    void deleteById(Integer id);

    @Select("select count(*) from web_system.coach where coachname like #{coachname}")
    Integer selectTotal(String coachname);

    @Select("select id,coachname,course,coachgender from web_system.coach where coachname like #{coachname} limit #{pageNum},#{pageSize}")
    List selectPage(Integer pageNum, Integer pageSize, String coachname);


    void deleteByIds(List<Integer> ids);
}
