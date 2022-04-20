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
            category.setCreatorId(2);
            return Result.succ(categoryService.save(category));
        }catch (Exception e){
            return Result.fail(e.getMessage());
        }
    }

    @PostMapping("/delete")
    public Result deleteCategory(@RequestBody Category category){
        try {
            if(category.getId()==1){
                return Result.fail("删除失败");
            }
            boolean result = categoryService.removeById(category.getId());
            if (result){
                return Result.succ("操作成功");
            }else {
                return Result.fail("删除失败");
            }
        }catch (Exception e){
            return Result.fail(e.getMessage());
        }
    }

    @PostMapping("/edit")
    public Result editCategory(@RequestBody Category category){
        try {
            if(category.getId()==1){
                return Result.fail("编辑失败");
            }
            boolean result = categoryService.saveOrUpdate(category);
            if (result){
                return Result.succ("操作成功");
            }else {
                return Result.fail("编辑失败");
            }
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
