package com.example.management_web.mapper;

import com.example.management_web.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 增
     *
     * @param user
     */
    @Insert("insert into web_system.user(name, password, course, gender) values (#{name},#{password},#{course},#{gender})")
    int insert(User user);

    /**
     * 普通查询
     *
     * @return
     */
    @Select("select id, name,  course, gender from web_system.user")
    List<User> list();

    /**
     * 分页查询
     * 改造成为模糊查询
     *
     * @param
     */
    @Select("select id, name, course, gender from web_system.user where name like #{name} limit #{pageNum},#{pageSize}")
    List<User> selectPage(Integer pageNum, Integer pageSize, String name);

    /**
     * 删
     *
     * @param id
     */
    @Delete("delete from web_system.user where id=#{id}")
    void deleteById(Integer id);

    /**
     * 批量删除
     *
     * @param ids
     */
//    @Delete("delete from user where id in (#{ids})")
    void deleteByIds(List<Integer> ids);

    /**
     * 改
     */
    @Update("update web_system.user set name=#{name}, gender=#{gender}, course=#{course} where id=#{id}")
    int update(User user);

    /**
     * 统计汇总方法
     */
    @Select("select count(*) from web_system.user where name like #{name} ")
    Integer selectTotal(String name);


    @Select("select  * from web_system.user where username=#{username} and password=#{password}")
    User selectByUsername(User user);
}
