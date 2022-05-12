package com.dun.controller;


import com.dun.common.lang.Result;
import com.dun.entity.Diary;
import com.dun.service.DiaryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/diary")
public class DiaryController {


    @Resource
    DiaryService diaryService;


    @PostMapping("/add")
    public Result addDiary(@RequestBody Diary diary){
        try {
            diary.setCreatorId(2);
            return Result.succ(diaryService.save(diary));
        }catch (Exception e){
            return Result.fail(e.getMessage());
        }
    }

    @PostMapping("/delete")
    public Result deleteDiary(@RequestBody Diary diary){
        try {
            boolean result = diaryService.removeById(diary.getId());
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
    public Result editDiary(@RequestBody Diary diary){
        try {
            diary.setCreatorId(2);

            boolean result = diaryService.saveOrUpdate(diary);
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
    public Result getDiaryList(){
        try{
            return Result.succ(diaryService.list());
        }catch (Exception e){
            return Result.fail(e.getMessage());
        }
    }

    @GetMapping("/get")
    public Result getDiary(@RequestParam(value = "diaryId") Integer categoryId){
        try{
            Diary diary = diaryService.getById(categoryId);

            return Result.succ(diary);

        }catch (Exception e){
            return Result.fail(e.getMessage());
        }
    }
}
