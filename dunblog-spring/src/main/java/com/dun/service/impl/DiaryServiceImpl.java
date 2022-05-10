package com.dun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dun.entity.Diary;
import com.dun.mapper.DiaryMapper;
import com.dun.service.DiaryService;
import org.springframework.stereotype.Service;


@Service("diaryService")
public class DiaryServiceImpl extends ServiceImpl<DiaryMapper, Diary> implements DiaryService {
}
