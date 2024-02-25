package com.example.management_web.service;

import com.example.management_web.exception.ServiceException;
import com.example.management_web.mapper.UserMapper;
import com.example.management_web.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 增
     * @param user
     */
    public void add(User user) {
        userMapper.insert(user);
    }

    /**
     * 查
     *
     * @return
     */
    public List<User> list() {
        return userMapper.list();
    }

    /**
     * 分页查询,改造之后进行模糊查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List selectPage(Integer pageNum, Integer pageSize, String name) {
        return userMapper.selectPage(pageNum, pageSize, name);
    }

    /**
     * 单个删除
     * @param id
     */
    public void delete(Integer id) {
        userMapper.deleteById(id);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    public void deleteIds(List<Integer> ids) {
        userMapper.deleteByIds(ids);
    }

    /**
     * 更改数据
     * @param user
     * @return
     */
    public int save(User user){
        //如果user没有id则表明是新增
        if(user.getId()==null){
            return userMapper.insert(user);
        }
        //否则就是更新
        else {
            return userMapper.update(user);
        }
    }


    public User register(User user) {
        User dbUser = userMapper.selectByUsername(user);
        if (dbUser != null) {
            // 抛出一个自定义的异常
            throw new ServiceException("用户名已存在");
        }
//        user.setUsername(user.getUsername());
//        user.setPassword(user.getPassword());
        userMapper.insert(user);
        return user;
    }

    public User login(User user) {
        // 根据用户名查询数据库的用户信息
        User dbUser = userMapper.selectByUsername(user);
        if (dbUser == null) {
            // 抛出一个自定义的异常
            throw new ServiceException("用户名或密码错误");
        }
        if (!user.getPassword().equals(dbUser.getPassword())) {
            throw new ServiceException("用户名或密码错误");
        }
        return dbUser;
    }
}
