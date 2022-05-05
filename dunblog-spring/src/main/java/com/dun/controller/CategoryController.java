package com.dun.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dun.common.lang.Result;
import com.dun.entity.Blog;
import com.dun.entity.Category;
import com.dun.service.BlogService;
import com.dun.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {


    @Resource
    CategoryService categoryService;

    @Resource
    BlogService blogService;

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
            //默认分组无法删除
            if(category.getId()==1){
                return Result.fail("删除失败");
            }
            //其他分组删除前将其中的博客移入默认分组
            List<Blog> blogList = blogService.list(new QueryWrapper<Blog>().eq("category_id", category.getId()));
            for (Blog blog:blogList) {
                blog.setCategoryId(1);
                blogService.saveOrUpdate(blog);
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

    @GetMapping("/get")
    public Result getCategoryInfoById(@RequestParam(value = "categoryId") Integer categoryId){
        try{
            Category category = categoryService.getById(categoryId);

            return Result.succ(category);

        }catch (Exception e){
            return Result.fail(e.getMessage());
        }
    }

    //根据分类id获取博客列表
    @GetMapping("/{categoryId}/blogs")
    public Result getBlogListByCategoryId(@PathVariable("categoryId") Integer categoryId,@RequestParam(value = "currentPage") Long currentPage,@RequestParam(value = "pageSize") Long pageSize){

        try{
            if(currentPage == null || currentPage < 1) currentPage = (long)1;
            Page<Map<String, Object>> page = new Page<>(currentPage,pageSize);
            IPage<Map<String, Object>> iPage = blogService.getBlogListByCategoryId(categoryId,page);

            return Result.succ(iPage);
        }catch (Exception e){
            return Result.fail(e.getMessage());
        }

    }
}
