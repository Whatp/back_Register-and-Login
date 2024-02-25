package com.example.management_web.controller;

import com.example.management_web.mapper.CoachMapper;
import com.example.management_web.pojo.Coach;
import com.example.management_web.pojo.Result;
import com.example.management_web.service.CoachService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 教练管理
 * Slf4j   日志记录
 */
@CrossOrigin
@Slf4j
@RestController
public class CoachController {

    @Autowired
    private CoachService coachService;

    @Autowired
    private CoachMapper coachMapper;
    /**
     * 查
     * @return
     */
    @GetMapping("/coaches/coach")
    public Result list(){
        log.info("查看全部部门数据");

        List<Coach> coachtList =  coachService.list();
        return Result.success(coachtList);
    }

    /**
     * 增或改
     * @param coach
     * @return
     * RequestBody 主要用来接收前端传递给后端的json字符串中的数据
     */
    @PostMapping("/coaches/coach")
    public Result add(@RequestBody Coach coach){
        log.info("新增");
        if(coach.getCoachpassword()==null)
            coach.setCoachpassword("admin");

//        调用service中的方法
        coachService.save(coach);
        return Result.success();
    }

    /**
     * 删
     * @param id
     * @return
     */
    @DeleteMapping("/coaches/coach/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除教练:{}",id);
        coachService.delete(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/coaches/batch/{ids}")
    public Result deleteBatch(@PathVariable List<Integer>ids){
        coachService.deleteIds(ids);
        return Result.success();
    }

    /**
     *
     * 分页条件查询
     *
     */
    @GetMapping("/coaches/page")
    public Map<String, Object> findPage(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize,
                                        @RequestParam String coachname) {
        coachname = "%" + coachname + "%";
        pageNum = (pageNum - 1) * pageSize;
        List data = coachService.selectPage(pageNum, pageSize, coachname);
        Integer total = coachMapper.selectTotal(coachname);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }

}
