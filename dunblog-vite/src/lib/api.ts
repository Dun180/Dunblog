import http from "@/utils/http";
import axios from "axios";
import {BlogProfile, BlogProfiles} from "@/models/blog";
import {Response, Result} from "@/models/result";
import {CategoryInfo} from "@/models/category";


//登录
export async function login(form:any){
    const resp = new Promise<Response>(async (resolve, reject) => {
        await axios.post("/login", form)
            .then((res) => {
                resolve(res);
            })
            .catch((err) => {
                reject(err);
            });
    })
    return resp

}

//检查token
export async function checkToken(token:any){
    const resp = await http.get<Result<{ checkResult:boolean }>>('/checkToken', null,{token:token})
    return resp
}


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

//删除博客
export async function deleteBlog(data:any){
    const resp = await http.post<Result<Object>>("/blog/delete",data)
    return resp
}

//上传图片
export async function uploadImg(param:any,config:any){
    const resp = await http.post<Result<Object>>("/upload",param,config)
    return resp
}

//博客编辑
export async function blogEdit(form:any){
    const resp = await http.post<Result<Object>>("/blog/edit",form)
    return resp
}

//分类列表
export async function getCategoryList(){
    const resp = await http.get<Result<CategoryInfo[]>>("/category/list")
    return resp
}

//通过id获取分类信息
export async function getCategoryInfoById(id:number){
    const resp = await http.get<Result<CategoryInfo>>("/category/get",{categoryId:id})
    return resp
}

//分类编辑
export async function categoryEdit(data:any){
    const resp = await http.post<Result<Object>>("/category/edit",data)
    return resp
}

//删除分类
export async function deleteCategory(data:any){
    const resp = await http.post<Result<Object>>("/category/delete",data)
    return resp
}

//添加分类
export async function addCategory(data:any){
    const resp = await http.post<Result<Object>>("/category/add",data)
    return resp
}


//根据分类id获取博客列表
export async function getBlogListByCategoryId(categoryId:number, currentPage:number, pageSize:number) {
    const resp = await http.get<Result<BlogProfiles>>('/category/'+categoryId+'/blogs', {
        currentPage: currentPage,
        pageSize: pageSize
    })
    return resp
}

