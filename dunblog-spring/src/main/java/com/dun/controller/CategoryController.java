package com.dun.controller;


import com.dun.common.lang.Result;
import com.dun.entity.Category;
import com.dun.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/category")
public class CategoryController {


    @Resource
    CategoryService categoryService;

    @PostMapping("/add")
    public Result addCategory(@RequestBody Category category){
        try {
            return Result.succ(categoryService.save(category));
        }catch (Exception e){
            return Result.fail(e.getMessage());
        }
    }

    @GetMapping("/list")
    public Result getCategoryList(){
        try{
            return Result.succ(categoryService.list());
        }catch (Exception e){
            return Result.fail(e.getMessage());
        }
    }
}
