package com.example.management_web.controller;

import com.example.management_web.mapper.UserMapper;
import com.example.management_web.pojo.Result;
import com.example.management_web.pojo.User;
import com.example.management_web.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教练管理
 */

@CrossOrigin
@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    /**
     * 查
     *
     * @return
     */
    @GetMapping("/users/user")
    public Result list() {
        log.info("查询全部数据");

        List<User> userList = userService.list();
        return Result.success(userList);
    }

    /**
     * 增
     *
     * @param user
     * @return
     */
    @PostMapping("/users/user")
    public Result add(@RequestBody User user) {
        log.info("新增");

        if (user.getPassword() == null) {
            user.setPassword("123456");
        }
//        调用service中的方法
        userService.save(user);
        return Result.success();
    }

    /**
     * 删
     */
    @DeleteMapping("/users/user/{id}")
    public Result delete(@PathVariable Integer id) {
        userService.delete(id);
        return Result.success();
    }

    /**
     * 批量删除
     *    {ids}路径参数  要加@PathVariable
     */
    @DeleteMapping("/users/batch/{ids}")
    public Result deleteBatch(@PathVariable List<Integer> ids) {
        userService.deleteIds(ids);
        return Result.success();
    }

    /**
     * 模糊查询
     */
    @GetMapping("/users/page")
    public Map<String, Object> findPage(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize,
                                        @RequestParam String name) {
        name = "%" + name + "%";
        pageNum = (pageNum - 1) * pageSize;
        List data = userService.selectPage(pageNum, pageSize, name);
        Integer total = userMapper.selectTotal(name);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }


}
