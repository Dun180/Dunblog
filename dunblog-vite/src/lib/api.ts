import http from "@/utils/http";
import {BlogProfile, BlogProfiles} from "@/models/blog";
import {Result} from "@/models/result";

//获取博客列表
export async function getBlogList(currentPage:number,pageSize:number){
    const resp = await http.get<Result<BlogProfiles>>('/blog/list',{currentPage:currentPage,pageSize:pageSize})
    return resp
}

//获取博客详细信息
export async function getBlogDetailById(blogId:number){
    const resp = await http.get<Result<BlogProfile>>("/blog/get",{blogId:blogId})
    return resp

}

