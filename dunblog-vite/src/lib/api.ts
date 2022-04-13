import http from "@/utils/http";
import {BlogProfiles} from "@/models/blog";
import {Result} from "@/models/result";

//获取博客列表
export async function getBlogList(currentPage:number,pageSize:number){
    const resp = await http.get<Result<BlogProfiles>>('/blog/list',{currentPage:currentPage,pageSize:pageSize})
    return resp
}

