package com.example.management_web.service;


import com.example.management_web.exception.ServiceException;
import com.example.management_web.mapper.CoachMapper;
import com.example.management_web.pojo.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoachService {
    @Autowired
    private CoachMapper coachMapper;

    public void add(Coach coach) {
        coachMapper.insert(coach);
    }


    public List<Coach> list() {
        return coachMapper.list();
    }


    public List selectPage(Integer pageNum, Integer pageSize, String coachname) {
        return coachMapper.selectPage(pageNum,pageSize,coachname);
    }


    public void delete(Integer id) {
        coachMapper.deleteById(id);
    }

    public int save(Coach coach) {
        if(coach.getId()==null){
            return coachMapper.insert(coach);
        }
        //否则就是更新
        else {
            return coachMapper.update(coach);
        }
    }

    public void deleteIds(List<Integer> ids) {
        coachMapper.deleteByIds(ids);
    }



}
