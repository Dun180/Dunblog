package com.dun.controller;


import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dun.common.lang.Result;
import com.dun.entity.ToDoList;
import com.dun.service.ToDoListService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/todo")
public class ToDoListController {
    @Resource
    ToDoListService toDoListService;

    @PostMapping("/add")
    public Result addToDoList(@RequestBody ToDoList toDoList){
        try {
            return Result.succ(toDoListService.save(toDoList));
        }catch (Exception e){
            return Result.fail(e.getMessage());
        }
    }

    @GetMapping("/today")
    public Result getToDoToday() {
        try {
                String today= DateUtil.today()+" 08:00:00";
            Date date = DateUtil.parse(today);
            return Result.succ(toDoListService.list(new QueryWrapper<ToDoList>()
                    .eq("start_time",date)
                    .eq("state",0)));
//            return Result.succ(today+"  "+date.toString());
        }catch (Exception e){
            return Result.fail(e.getMessage());
        }
    }

    @PostMapping("/edit")
    public Result editToDoList(@RequestBody ToDoList toDoList){
        try {
            return Result.succ(toDoListService.saveOrUpdate(toDoList));
        }catch (Exception e){
            return Result.fail(e.getMessage());
        }
    }
}
