package com.dun.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dun.entity.ToDoList;
import com.dun.mapper.ToDoListMapper;
import com.dun.service.ToDoListService;
import org.springframework.stereotype.Service;

@Service("toDoListServiceImpl")
public class ToDoListServiceImpl extends ServiceImpl<ToDoListMapper, ToDoList> implements ToDoListService {
}
