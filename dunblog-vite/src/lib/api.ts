import http from "@/utils/http";
import {BlogProfiles} from "@/models/blog";


//获取博客列表
export async function getBlogList(currentPage:number,pageSize:number){
    const resp = await http.get<{data:BlogProfiles}>('/blog/list',{currentPage:currentPage,pageSize:pageSize})
    return resp
}

